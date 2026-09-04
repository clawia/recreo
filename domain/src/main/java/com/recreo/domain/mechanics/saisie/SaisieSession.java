package com.recreo.domain.mechanics.saisie;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanic.SessionStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * L'etat d'une partie de saisie : la manche en attente de reponse, ce que l'enfant a deja ecrit sur
 * cette manche, le verdict de la derniere manche resolue, le score cumule et le tirage deja vu
 * (ADR-0004 : l'etat de session appartient a la mecanique).
 *
 * <p>Les conditions de mecanique deja arbitrees valent ici sans changement : trois essais avant la
 * revelation (M5), le score compte des manches resolues et jamais des essais bruts (M3), et une
 * reponse fausse laisse l'enfant sur la meme manche.</p>
 *
 * <p><b>SAI-12 : la session porte {@code List<String> tentatives}, dans l'ordre d'ecriture</b>, et
 * {@link #derniereTentative()} en est derive plutot que d'etre un second champ (ADR-0058,
 * amendement du 2026-08-24). Le motif est mesure et non suppose : {@code Set.copyOf} rend un
 * ensemble dont l'ordre d'iteration n'est pas specifie et varie d'une machine virtuelle a l'autre.
 * Chez la mecanique voisine cela ne coute rien - l'ordre affiche y est celui des propositions
 * melangees, et l'ensemble n'est interroge que par appartenance. Ici, l'ordre de ce que l'enfant a
 * ecrit ne vit nulle part ailleurs que dans la collection elle-meme.</p>
 *
 * <p><b>La revelation ne resout pas la manche, elle attend un geste.</b> Au troisieme echec,
 * l'enfant doit lire ce qu'il a ecrit a cote de la reponse attendue (SAI-9b) ; il n'a plus rien a
 * ecrire, et c'est {@link ContinuerMove} qui resout alors la manche. Chez la mecanique voisine, ce
 * geste-la est l'appui sur la reponse nommee ; il ne compte pas davantage comme une reussite ici
 * que la-bas.</p>
 */
public final class SaisieSession implements GameSession {

    /** M5 : nombre d'essais au maximum sur une manche avant que la reponse ne soit revelee. */
    static final int ESSAIS_AVANT_REVELATION = 3;

    /**
     * M1 : l'indice se declenche des le premier echec sur la manche courante - une partie d'une
     * seule manche peut deja le montrer. CatalogueChargeableIT compare cette valeur a
     * nombreDeManches pour chaque jeu du catalogue reel plutot que de l'ecrire en dur.
     */
    static final int LONGUEUR_MINIMALE_POUR_AIDE = 1;

    private final ContentSource contenu;
    private final RandomSource hasard;
    private final int nombreDeManches;
    private final Set<ItemId> dejaTirees;
    private final ReponseAEcrire mancheCourante;
    private final List<String> tentatives;
    private final boolean revelee;
    private final Optional<SaisieVerdict> dernierVerdict;
    private final Score score;
    private final SessionStatus status;

    private SaisieSession(ContentSource contenu, RandomSource hasard, int nombreDeManches,
                           Set<ItemId> dejaTirees, ReponseAEcrire mancheCourante,
                           List<String> tentatives, boolean revelee,
                           Optional<SaisieVerdict> dernierVerdict, Score score,
                           SessionStatus status) {
        this.contenu = contenu;
        this.hasard = hasard;
        this.nombreDeManches = nombreDeManches;
        this.dejaTirees = Set.copyOf(dejaTirees);
        this.mancheCourante = mancheCourante;
        this.tentatives = List.copyOf(tentatives);
        this.revelee = revelee;
        this.dernierVerdict = dernierVerdict;
        this.score = score;
        this.status = status;
    }

    static SaisieSession demarrer(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(), Optional.empty(),
                new Score(0, 0));
    }

    /**
     * Une reponse ecrite. Trois branches, et l'ordre entre elles est ce qui tient SEUIL-2 : la
     * reponse est confrontee au bareme <b>avant</b> qu'un essai soit compte, donc une reponse juste
     * ecrite sous une forme que le paquet accepte ne coute jamais un essai, quelle que soit sa
     * forme.
     *
     * <p>Sur une manche deja revelee, une reponse ecrite ne change rien - {@code this} est rendu tel
     * quel, sans essai compte ni tentative ajoutee. Il n'y a plus de champ a l'ecran a ce
     * moment-la ; cette branche existe pour la requete qui arriverait quand meme.</p>
     */
    SaisieSession jouer(SaisieMove coup) {
        if (mancheCourante == null) {
            throw new IllegalStateException("la partie est terminee, plus aucune manche a jouer");
        }
        if (revelee) {
            return this;
        }
        String texteEcrit = coup.texteEcrit();
        if (leBaremeAccepte(texteEcrit)) {
            return resoudre(true, texteEcrit);
        }

        List<String> tentativesSuivantes = new ArrayList<>(tentatives);
        tentativesSuivantes.add(texteEcrit);
        return new SaisieSession(contenu, hasard, nombreDeManches, dejaTirees, mancheCourante,
                tentativesSuivantes, tentativesSuivantes.size() >= ESSAIS_AVANT_REVELATION,
                dernierVerdict, score, SessionStatus.EN_COURS);
    }

    /**
     * Le geste qui suit la revelation. Il resout la manche sans la compter comme une reussite (M11
     * clause 3) et porte au verdict la derniere chaine ecrite : SAI-9b et la seconde moitie de R-D
     * demandent que la revelation porte la chaine autant que la reussite. Hors revelation, il ne
     * change rien.
     */
    SaisieSession poursuivre() {
        if (mancheCourante == null) {
            throw new IllegalStateException("la partie est terminee, plus aucune manche a jouer");
        }
        if (!revelee) {
            return this;
        }
        return resoudre(false, tentatives.get(tentatives.size() - 1));
    }

    /**
     * SAI-6, SAI-7 : ce que le paquet accepte, et lui seul. La forme de reference est acceptee sans
     * qu'un paquet ait a la repeter dans sa liste - refuser la reponse la mieux ecrite serait le
     * pire cas de SEUIL-2, et aucun paquet n'a a s'en premunir item par item.
     *
     * <p>Ce que le mode declare par l'item change est la <b>facon de comparer</b>, jamais l'etendue
     * du bareme : en forme exacte les chaines sont confrontees telles qu'elles sont ecrites, en
     * forme libre elles le sont apres le repliage de SAI-1, SAI-3 et SAI-4. La liste, elle, reste
     * entierement dans la donnee - c'est elle le bareme, et rien ici ne la retouche.</p>
     *
     * <p><b>SAI-5 : aucune distance, aucune tolerance derivee du nombre de signes differents,
     * nulle part.</b> Cette methode ne compare que des chaines entieres, et {@code R-B} le tient sur
     * la table de couples que nadia-prof a nommee.</p>
     */
    private boolean leBaremeAccepte(String texteEcrit) {
        if (mancheCourante.exigenceDeForme() == ExigenceDeForme.FORME_EXACTE) {
            return formesDuBareme().stream().anyMatch(forme -> forme.equals(texteEcrit));
        }
        String replie = NormalisationDeSaisie.replier(texteEcrit);
        return formesDuBareme().stream()
                .anyMatch(forme -> NormalisationDeSaisie.replier(forme).equals(replie));
    }

    private List<String> formesDuBareme() {
        List<String> formes = new ArrayList<>();
        formes.add(mancheCourante.formeDeReference());
        formes.addAll(mancheCourante.formesAcceptees());
        return formes;
    }

    /**
     * Resout la manche courante - juste ou par revelation - et tire la suivante, ou termine la
     * partie. M3 : c'est ce geste, et lui seul, qui fait avancer {@link Score#tentatives()}.
     */
    private SaisieSession resoudre(boolean juste, String chaineJugee) {
        SaisieVerdict verdict = new SaisieVerdict(juste, mancheCourante.formeDeReference(),
                mancheCourante.explication(), chaineJugee, mancheCourante.exigenceDeForme());
        Score nouveauScore = new Score(score.reussites() + (juste ? 1 : 0), score.tentatives() + 1);
        if (nouveauScore.tentatives() >= nombreDeManches) {
            return new SaisieSession(contenu, hasard, nombreDeManches, dejaTirees, null, List.of(),
                    false, Optional.of(verdict), nouveauScore, SessionStatus.TERMINEE);
        }
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(dejaTirees),
                Optional.of(verdict), nouveauScore);
    }

    private static SaisieSession tirerManche(ContentSource contenu, RandomSource hasard,
                                              int nombreDeManches, HashSet<ItemId> tirees,
                                              Optional<SaisieVerdict> verdict, Score score) {
        Optional<Item> tiree = contenu.next(hasard, tirees);
        if (tiree.isEmpty()) {
            return new SaisieSession(contenu, hasard, nombreDeManches, tirees, null, List.of(),
                    false, verdict, score, SessionStatus.TERMINEE);
        }
        ReponseAEcrire manche = reponseDe(tiree.orElseThrow());
        tirees.add(manche.id());
        return new SaisieSession(contenu, hasard, nombreDeManches, tirees, manche, List.of(), false,
                verdict, score, SessionStatus.EN_COURS);
    }

    /**
     * ADR-0004, couture scellee d'{@link Item} : cette mecanique presente des items auxquels on
     * repond en ecrivant, et elle dit son ignorance de l'autre forme plutot que de la deviner. Le
     * cas est celui d'une entree de catalogue qui associerait un paquet de choix a cette
     * mecanique-ci.
     */
    private static ReponseAEcrire reponseDe(Item item) {
        return switch (item) {
            case ReponseAEcrire reponse -> reponse;
            case Question question -> throw new IllegalArgumentException(
                    "item inconnu de la mecanique de saisie : " + question.id().value()
                            + " offre des propositions, cette mecanique attend une reponse ecrite");
            case ZoneADesigner zone -> throw new IllegalArgumentException(
                    "item inconnu de la mecanique de saisie : " + zone.id().value()
                            + " attend une designation sur image, cette mecanique attend une reponse"
                            + " ecrite");
        };
    }

    public Optional<String> enonceManche() {
        return Optional.ofNullable(mancheCourante).map(ReponseAEcrire::enonce);
    }

    /**
     * ADR-0075 : le texte que l'adulte lit a voix haute pour la manche courante, vide quand l'item
     * n'en declare pas - le cas ordinaire. Meme motif que sur {@code BanditManchotSession} : cette
     * mecanique transporte le champ sans le lire, et {@code mancheCourante} reste le meme tant que
     * la manche n'est pas resolue, donc cette valeur ne varie pas entre un item frais, un refus et
     * une revelation (VOI-8).
     */
    public Optional<String> texteAVoixHauteManche() {
        return Optional.ofNullable(mancheCourante).flatMap(ReponseAEcrire::texteAVoixHaute);
    }

    /**
     * SAI-12 : tout ce que l'enfant a ecrit sur la manche courante, dans l'ordre ou il l'a ecrit.
     * Une chaine retapee a l'identique y figure deux fois, et c'est voulu : si sa correction n'a
     * rien corrige, il doit le voir.
     */
    public List<String> tentatives() {
        return tentatives;
    }

    /**
     * SAI-9a : la derniere chaine ecrite sur la manche courante, celle que l'enfant garde sous les
     * yeux pour la corriger. Derivee de {@link #tentatives()} et jamais tenue a part.
     */
    public Optional<String> derniereTentative() {
        return tentatives.isEmpty() ? Optional.empty()
                : Optional.of(tentatives.get(tentatives.size() - 1));
    }

    /**
     * M1 : l'indice de la manche bloquante, affiche des le premier echec sur cette manche et
     * jusqu'a sa resolution. Vide sur une manche fraiche comme sur une partie terminee.
     *
     * <p>M21 : vide egalement, quel que soit le nombre d'echecs, quand le paquet declare que ses
     * indices nomment la reponse.</p>
     */
    public Optional<String> indice() {
        if (tentatives.isEmpty() || contenu.indicesNommentLaReponse()) {
            return Optional.empty();
        }
        return mancheCourante.indice();
    }

    /** M5 : vrai des le troisieme echec sur la manche courante - la reponse est desormais revelee. */
    public boolean revelee() {
        return revelee;
    }

    /**
     * M5, M9 clause 5, SAI-9a : la forme de reference de la manche courante, exposee seulement une
     * fois revelee. Avant ce moment, aucune methode de cette classe ne la designe - le retour dit a
     * l'enfant qu'il s'est trompe, il ne lui nomme jamais la reponse.
     */
    public Optional<String> formeDeReferenceRevelee() {
        return revelee ? Optional.of(mancheCourante.formeDeReference()) : Optional.empty();
    }

    /**
     * M7 : l'explication de la manche courante survit du cote faux avec la revelation - exposee
     * seulement une fois revelee, comme {@link #formeDeReferenceRevelee()}.
     */
    public Optional<String> explicationRevelee() {
        return revelee ? mancheCourante.explication() : Optional.empty();
    }

    /** Le mode declare par l'item de la manche courante, vide sur une partie terminee. */
    public Optional<ExigenceDeForme> exigenceDeFormeManche() {
        return Optional.ofNullable(mancheCourante).map(ReponseAEcrire::exigenceDeForme);
    }

    public Optional<SaisieVerdict> dernierVerdict() {
        return dernierVerdict;
    }

    @Override
    public SessionStatus status() {
        return status;
    }

    @Override
    public Score score() {
        return score;
    }
}
