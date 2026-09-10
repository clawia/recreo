package com.recreo.domain.mechanics.designation;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.Zone;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanic.SessionStatus;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * L'etat d'une partie de designation : la manche en attente de reponse, les essais deja joues sur
 * cette manche, le verdict de la derniere manche resolue, le score cumule et le tirage deja vu
 * (ADR-0004 : l'etat de session appartient a la mecanique).
 *
 * <p>ADR-0080, decision 3 : cette classe reprend, sans les melanges de l'ordre, exactement la boucle
 * de partie de {@code BanditManchotSession} - trois essais avant revelation, une reponse fausse
 * laisse l'enfant sur la meme manche, une manche resolue par revelation ne compte pas comme une
 * reussite. La designation n'a rien a ajouter a cette boucle : ce qui varie d'un cas d'usage a
 * l'autre (un plan, une carte, un quadrillage) est entierement du contenu.</p>
 *
 * <p>Elle ne melange jamais l'ordre des {@link Zone} d'un item : ADR-0080 decision 3 le refuse
 * explicitement - la place d'une zone est une donnee du contenu, pas un tirage - et decision 4 :
 * l'ordre de declaration des zones est le seul ordre de tabulation disponible.</p>
 *
 * <p>Une zone inconnue (un identifiant qui ne correspond a aucune {@link Zone} de la manche
 * courante) est traitee comme n'importe quelle designation fausse, sans chemin d'erreur neuf
 * (ADR-0080, decision 3) : elle ne peut venir que d'une requete forgee, jamais de l'ecran servi.</p>
 */
public final class DesignationSession implements GameSession {

    /** Meme valeur que les deux mecaniques voisines : trois essais avant revelation. */
    static final int ESSAIS_AVANT_REVELATION = 3;

    /** Meme motif que les deux mecaniques voisines : l'indice se declenche des le premier echec. */
    static final int LONGUEUR_MINIMALE_POUR_AIDE = 1;

    private final ContentSource contenu;
    private final RandomSource hasard;
    private final int nombreDeManches;
    private final Set<ItemId> dejaTirees;
    private final ZoneADesigner mancheCourante;
    private final int essaisSurLaManche;
    private final Set<String> zonesEssayees;
    private final Optional<String> derniereTentative;
    private final boolean revelee;
    private final Optional<DesignationVerdict> dernierVerdict;
    private final Score score;
    private final SessionStatus status;

    private DesignationSession(ContentSource contenu, RandomSource hasard, int nombreDeManches,
                                Set<ItemId> dejaTirees, ZoneADesigner mancheCourante,
                                int essaisSurLaManche, Set<String> zonesEssayees,
                                Optional<String> derniereTentative, boolean revelee,
                                Optional<DesignationVerdict> dernierVerdict, Score score,
                                SessionStatus status) {
        this.contenu = contenu;
        this.hasard = hasard;
        this.nombreDeManches = nombreDeManches;
        this.dejaTirees = Set.copyOf(dejaTirees);
        this.mancheCourante = mancheCourante;
        this.essaisSurLaManche = essaisSurLaManche;
        this.zonesEssayees = Set.copyOf(zonesEssayees);
        this.derniereTentative = derniereTentative;
        this.revelee = revelee;
        this.dernierVerdict = dernierVerdict;
        this.score = score;
        this.status = status;
    }

    static DesignationSession demarrer(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(), Optional.empty(),
                new Score(0, 0));
    }

    /**
     * Trois branches, meme forme que {@code BanditManchotSession.jouer} : sur une manche revelee,
     * seule la designation de la bonne zone fait avancer la partie (sans compter comme une reussite)
     * et toute autre designation ne change rien ; sur une manche non revelee, une designation
     * correcte la resout, une designation fausse utilise un essai et revele au troisieme echec.
     */
    DesignationSession jouer(DesignationMove coup) {
        if (mancheCourante == null) {
            throw new IllegalStateException("la partie est terminee, plus aucune manche a jouer");
        }
        String choix = coup.zoneDesignee();
        boolean estLaBonneZone = mancheCourante.bonneZone().equals(choix);

        if (revelee) {
            return estLaBonneZone ? resoudre(false) : this;
        }
        if (estLaBonneZone) {
            return resoudre(true);
        }

        int essaisSuivants = essaisSurLaManche + 1;
        Set<String> essayeesSuivantes = new LinkedHashSet<>(zonesEssayees);
        essayeesSuivantes.add(choix);
        return new DesignationSession(contenu, hasard, nombreDeManches, dejaTirees, mancheCourante,
                essaisSuivants, essayeesSuivantes, Optional.of(choix),
                essaisSuivants >= ESSAIS_AVANT_REVELATION, dernierVerdict, score,
                SessionStatus.EN_COURS);
    }

    /**
     * Resout la manche courante - juste ou par revelation - et tire la suivante, ou termine la
     * partie. C'est ce geste, et lui seul, qui fait avancer {@link Score#tentatives()}.
     *
     * <p>DEC-0061, instruction 1, famille A (S34-SCO1-REMEDE) : {@link Score#reussites()} ne monte
     * que si la manche est resolue par une designation juste <b>et</b> qu'aucun echec ne l'a
     * precede sur cette meme manche - l'enfant garde ses trois essais, mais seule une reponse
     * trouvee du premier coup est comptee comme une reussite.</p>
     */
    private DesignationSession resoudre(boolean juste) {
        DesignationVerdict verdict = new DesignationVerdict(juste, mancheCourante.explication(),
                mancheCourante.enonce());
        boolean reussieDuPremierCoup = juste && essaisSurLaManche == 0;
        Score nouveauScore = new Score(score.reussites() + (reussieDuPremierCoup ? 1 : 0),
                score.tentatives() + 1);
        if (nouveauScore.tentatives() >= nombreDeManches) {
            return new DesignationSession(contenu, hasard, nombreDeManches, dejaTirees, null, 0,
                    Set.of(), Optional.empty(), false, Optional.of(verdict), nouveauScore,
                    SessionStatus.TERMINEE);
        }
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(dejaTirees),
                Optional.of(verdict), nouveauScore);
    }

    private static DesignationSession tirerManche(ContentSource contenu, RandomSource hasard,
                                                   int nombreDeManches, HashSet<ItemId> tirees,
                                                   Optional<DesignationVerdict> verdict, Score score) {
        Optional<Item> tiree = contenu.next(hasard, tirees);
        if (tiree.isEmpty()) {
            return new DesignationSession(contenu, hasard, nombreDeManches, tirees, null, 0, Set.of(),
                    Optional.empty(), false, verdict, score, SessionStatus.TERMINEE);
        }
        ZoneADesigner manche = zoneADesignerDe(tiree.orElseThrow());
        tirees.add(manche.id());
        return new DesignationSession(contenu, hasard, nombreDeManches, tirees, manche, 0, Set.of(),
                Optional.empty(), false, verdict, score, SessionStatus.EN_COURS);
    }

    /**
     * ADR-0004, couture scellee d'{@link Item} : cette mecanique presente des items sur lesquels on
     * designe une zone, et elle dit son ignorance des deux autres formes plutot que de les deviner.
     */
    private static ZoneADesigner zoneADesignerDe(Item item) {
        return switch (item) {
            case ZoneADesigner zone -> zone;
            case Question question -> throw new IllegalArgumentException(
                    "item inconnu de la mecanique de designation : " + question.id().value()
                            + " offre des propositions, cette mecanique attend une designation sur"
                            + " image");
            case ReponseAEcrire reponse -> throw new IllegalArgumentException(
                    "item inconnu de la mecanique de designation : " + reponse.id().value()
                            + " attend une reponse ecrite, cette mecanique attend une designation sur"
                            + " image");
        };
    }

    public Optional<String> enonceManche() {
        return Optional.ofNullable(mancheCourante).map(ZoneADesigner::enonce);
    }

    /** ADR-0080 : l'identifiant de l'image que la manche courante montre. */
    public Optional<String> illustrationManche() {
        return Optional.ofNullable(mancheCourante).map(ZoneADesigner::illustration);
    }

    /** ADR-0080 : ce qu'un lecteur d'ecran annonce de l'image de la manche courante. */
    public Optional<String> illustrationTexteAlternatifManche() {
        return Optional.ofNullable(mancheCourante).map(ZoneADesigner::illustrationTexteAlternatif);
    }

    /**
     * ADR-0080, decision 3 et 4 : les zones designables de la manche courante, dans l'ordre exact ou
     * le contenu les declare - jamais melangees, cet ordre etant aussi l'ordre de tabulation.
     */
    public List<Zone> zonesManche() {
        return Optional.ofNullable(mancheCourante).map(ZoneADesigner::zones).orElseGet(List::of);
    }

    /**
     * ADR-0075 : le texte que l'adulte lit a voix haute pour la manche courante, vide quand l'item
     * n'en declare pas - le cas ordinaire. Cette mecanique ne le lit pas : elle le transporte,
     * exactement comme sur les deux mecaniques voisines.
     */
    public Optional<String> texteAVoixHauteManche() {
        return Optional.ofNullable(mancheCourante).flatMap(ZoneADesigner::texteAVoixHaute);
    }

    /**
     * Les zones deja essayees sur la manche courante - marquees, jamais retirees, meme motif que
     * {@code BanditManchotSession.propositionsEssayees}.
     */
    public Set<String> zonesEssayees() {
        return zonesEssayees;
    }

    /** La derniere zone designee sur la manche courante, pour son point de focus. */
    public Optional<String> derniereTentative() {
        return derniereTentative;
    }

    /**
     * L'indice de la manche bloquante, affiche des le premier echec sur cette manche et jusqu'a sa
     * resolution. Vide sur une manche fraiche, sur une partie terminee, et quel que soit le nombre
     * d'echecs quand le paquet declare que ses indices nomment la reponse (M21).
     */
    public Optional<String> indice() {
        if (essaisSurLaManche == 0 || contenu.indicesNommentLaReponse()) {
            return Optional.empty();
        }
        return mancheCourante.indice();
    }

    /** Vrai des le troisieme echec sur la manche courante - la bonne zone est desormais revelee. */
    public boolean revelee() {
        return revelee;
    }

    /**
     * L'identifiant de la bonne zone de la manche courante, expose seulement une fois revele. Avant
     * ce moment, aucune methode de cette classe ne le designe.
     */
    public Optional<String> bonneZoneRevelee() {
        return revelee ? Optional.of(mancheCourante.bonneZone()) : Optional.empty();
    }

    /** L'explication de la manche courante, exposee seulement une fois revelee. */
    public Optional<String> explicationRevelee() {
        return revelee ? mancheCourante.explication() : Optional.empty();
    }

    public Optional<DesignationVerdict> dernierVerdict() {
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
