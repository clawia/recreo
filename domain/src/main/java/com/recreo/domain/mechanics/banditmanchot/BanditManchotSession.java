package com.recreo.domain.mechanics.banditmanchot;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.content.TableauSupport;
import com.recreo.domain.content.ZoneADesigner;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanic.SessionStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * L'etat d'une partie de bandit manchot : la manche en attente de reponse, les essais deja
 * joues sur cette manche, le verdict de la derniere manche resolue, le score cumule et le
 * tirage deja vu (ADR-0004 : l'etat de session appartient a la mecanique, pas a un moteur
 * partage).
 *
 * <p>S10, arbitrage de mecanique de nadia-prof (conditions M1 a M13 de sa fiche de conformite) :
 * une mauvaise reponse ne tire plus la manche suivante, elle laisse l'enfant reessayer la meme
 * manche. Trois consequences de fond sur cette classe :</p>
 *
 * <ul>
 *   <li><b>M3, amendement d'ADR-0012.</b> {@link Score#tentatives()} compte desormais les
 *   <b>manches resolues</b>, jamais les essais bruts : une partie de {@code nombreDeManches}
 *   manches en presente {@code nombreDeManches} distinctes, quel que soit le nombre d'essais
 *   depenses sur chacune, et ne se termine jamais au milieu d'une question ;</li>
 *   <li><b>M5.</b> Trois essais au maximum par manche. Aux deux premiers echecs, la manche reste
 *   la meme. Au troisieme, elle entre en revelation : le jeu nomme la bonne reponse et seul un
 *   appui dessus la resout - un geste qui ne compte pas comme une reussite (M11 clause 3) ;</li>
 *   <li><b>M1, remplacement de l'aide de S3-2 (DEC-0004 point 4).</b> L'ancienne aide - indice
 *   apres trois echecs consecutifs sur des items differents, manche simplifiee representant un
 *   item deja reussi - se declenchait dans le tirage de la manche suivante ; elle devient
 *   inatteignable des qu'une reponse fausse ne tire plus de manche (avis de faisabilite de
 *   theo-archi, s10, section 2.b). Elle disparait : l'indice est desormais une propriete de la
 *   manche bloquante elle-meme, affiche des le premier echec sur cette manche et jusqu'a sa
 *   resolution. Il n'y a plus de "manche plus simple".</li>
 * </ul>
 */
public final class BanditManchotSession implements GameSession {

    /** M5 : nombre d'essais au maximum sur une manche avant que la reponse ne soit revelee. */
    private static final int ESSAIS_AVANT_REVELATION = 3;

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
    private final Question mancheCourante;
    private final List<String> propositions;
    private final int essaisSurLaManche;
    private final Set<String> propositionsEssayees;
    private final Optional<String> derniereTentative;
    private final boolean revelee;
    private final Optional<Verdict> dernierVerdict;
    private final Score score;
    private final SessionStatus status;

    private BanditManchotSession(ContentSource contenu, RandomSource hasard, int nombreDeManches,
                                  Set<ItemId> dejaTirees, Question mancheCourante,
                                  List<String> propositions, int essaisSurLaManche,
                                  Set<String> propositionsEssayees, Optional<String> derniereTentative,
                                  boolean revelee, Optional<Verdict> dernierVerdict, Score score,
                                  SessionStatus status) {
        this.contenu = contenu;
        this.hasard = hasard;
        this.nombreDeManches = nombreDeManches;
        this.dejaTirees = Set.copyOf(dejaTirees);
        this.mancheCourante = mancheCourante;
        this.propositions = List.copyOf(propositions);
        this.essaisSurLaManche = essaisSurLaManche;
        this.propositionsEssayees = Set.copyOf(propositionsEssayees);
        this.derniereTentative = derniereTentative;
        this.revelee = revelee;
        this.dernierVerdict = dernierVerdict;
        this.score = score;
        this.status = status;
    }

    static BanditManchotSession demarrer(ContentSource contenu, RandomSource hasard, int nombreDeManches) {
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(), Optional.empty(),
                new Score(0, 0));
    }

    /**
     * M5, M9, M11 : trois branches. Sur une manche revelee, seul un appui sur la reponse nommee
     * fait avancer la partie (et ne compte pas comme une reussite) ; tout autre appui ne change
     * rien - {@code this} est renvoye tel quel, sans essai compte ni proposition marquee. Sur une
     * manche non revelee, une reponse correcte la resout ; une reponse fausse utilise un essai,
     * marque la proposition et, au troisieme echec, revele la reponse sans resoudre la manche.
     *
     * <p>M14 (nadia-prof, 2026-08-13) : un appui a cote apres la revelation doit malgre tout
     * recevoir la marque barree de M9 a l'affichage, sans que rien d'autre ne bouge ici - avis de
     * faisabilite de theo-archi, s10, correction 2. Plutot que d'ajouter un champ a cette classe
     * pour un seul geste sans consequence de jeu, {@code PartieController.propositionsEssayeesAffichees}
     * duplique la condition {@code revelee && !choix.equals(bonneReponse)} pour l'affichage seul.</p>
     */
    BanditManchotSession jouer(BanditManchotMove coup) {
        if (mancheCourante == null) {
            throw new IllegalStateException("la partie est terminee, plus aucune manche a jouer");
        }
        String choix = coup.propositionChoisie();
        boolean estLaBonneReponse = mancheCourante.bonneReponse().equals(choix);

        if (revelee) {
            return estLaBonneReponse ? resoudre(false) : this;
        }
        if (estLaBonneReponse) {
            return resoudre(true);
        }

        int essaisSuivants = essaisSurLaManche + 1;
        Set<String> essayeesSuivantes = new LinkedHashSet<>(propositionsEssayees);
        essayeesSuivantes.add(choix);
        return new BanditManchotSession(contenu, hasard, nombreDeManches, dejaTirees, mancheCourante,
                propositions, essaisSuivants, essayeesSuivantes, Optional.of(choix),
                essaisSuivants >= ESSAIS_AVANT_REVELATION, dernierVerdict, score, SessionStatus.EN_COURS);
    }

    /**
     * Resout la manche courante - juste ou par revelation - et tire la suivante, ou termine la
     * partie. M3 : c'est ce geste, et lui seul, qui fait avancer {@link Score#tentatives()}.
     *
     * <p>DEC-0061, instruction 1, famille A (S34-SCO1-REMEDE) : {@link Score#reussites()} ne monte
     * que si la manche est resolue par un appui juste <b>et</b> qu'aucun echec ne l'a precede sur
     * cette meme manche - l'enfant garde ses trois essais, mais seule une reponse trouvee du
     * premier coup est comptee comme une reussite.</p>
     */
    private BanditManchotSession resoudre(boolean juste) {
        Verdict verdict = new Verdict(juste, mancheCourante.bonneReponse(),
                mancheCourante.explication(), mancheCourante.enonce());
        boolean reussieDuPremierCoup = juste && essaisSurLaManche == 0;
        Score nouveauScore = new Score(score.reussites() + (reussieDuPremierCoup ? 1 : 0),
                score.tentatives() + 1);
        if (nouveauScore.tentatives() >= nombreDeManches) {
            return new BanditManchotSession(contenu, hasard, nombreDeManches, dejaTirees, null,
                    List.of(), 0, Set.of(), Optional.empty(), false, Optional.of(verdict), nouveauScore,
                    SessionStatus.TERMINEE);
        }
        return tirerManche(contenu, hasard, nombreDeManches, new HashSet<>(dejaTirees),
                Optional.of(verdict), nouveauScore);
    }

    private static BanditManchotSession tirerManche(ContentSource contenu, RandomSource hasard,
                                                      int nombreDeManches, HashSet<ItemId> tirees,
                                                      Optional<Verdict> verdict, Score score) {
        Optional<Item> tiree = contenu.next(hasard, tirees);
        if (tiree.isEmpty()) {
            return new BanditManchotSession(contenu, hasard, nombreDeManches, tirees, null, List.of(),
                    0, Set.of(), Optional.empty(), false, verdict, score, SessionStatus.TERMINEE);
        }
        Question manche = questionDe(tiree.orElseThrow());
        tirees.add(manche.id());
        return new BanditManchotSession(contenu, hasard, nombreDeManches, tirees, manche,
                melanger(manche, hasard), 0, Set.of(), Optional.empty(), false, verdict, score,
                SessionStatus.EN_COURS);
    }

    /**
     * ADR-0004, couture scellee d'{@link Item} : depuis s24 le contrat de contenu porte une seconde
     * forme d'item, celle de la mecanique de saisie. Cette mecanique-ci ne sait pas la presenter -
     * elle distribue des propositions, et un item auquel on repond en ecrivant n'en porte aucune -
     * et elle le dit plutot que de l'ignorer. Le cas est celui d'une entree de catalogue qui
     * associerait un paquet de saisie a cette mecanique ; le message nomme les deux.
     */
    private static Question questionDe(Item item) {
        return switch (item) {
            case Question question -> question;
            case ReponseAEcrire reponse -> throw new IllegalArgumentException(
                    "item inconnu de la mecanique bandit manchot : " + reponse.id().value()
                            + " attend une reponse ecrite, cette mecanique ne presente que des"
                            + " propositions");
            case ZoneADesigner zone -> throw new IllegalArgumentException(
                    "item inconnu de la mecanique bandit manchot : " + zone.id().value()
                            + " attend une designation sur image, cette mecanique ne presente que"
                            + " des propositions");
        };
    }

    private static List<String> melanger(Question question, RandomSource hasard) {
        List<String> propositions = new ArrayList<>();
        propositions.add(question.bonneReponse());
        propositions.addAll(question.distracteurs());
        for (int i = propositions.size() - 1; i > 0; i--) {
            int j = hasard.nextInt(i + 1);
            String tampon = propositions.get(i);
            propositions.set(i, propositions.get(j));
            propositions.set(j, tampon);
        }
        return propositions;
    }

    public Optional<String> enonceManche() {
        return Optional.ofNullable(mancheCourante).map(Question::enonce);
    }

    /**
     * ADR-0062 : la part de {@link #enonceManche()} que le contenu declare devoir etre distinguee
     * visuellement, vide quand l'item n'en designe aucune - c'est le cas ordinaire, jamais un cas
     * particulier a verifier en plus.
     */
    public Optional<String> partDesigneeManche() {
        return Optional.ofNullable(mancheCourante).flatMap(Question::partDesignee);
    }

    /**
     * ADR-0050 : le texte-support de la manche courante, vide quand l'item n'en porte pas - le cas
     * ordinaire, jamais un cas particulier a verifier en plus.
     */
    public Optional<String> texteSupportManche() {
        return Optional.ofNullable(mancheCourante).flatMap(Question::texteSupport);
    }

    /**
     * ADR-0099 : la grille que la manche courante donne a lire, vide quand l'item n'en porte pas -
     * le cas ordinaire. Cette mecanique ne la lit pas, elle la transporte, exactement comme le
     * texte-support : elle ne change ni la facon de repondre ni le verdict (ADR-0058).
     */
    public Optional<TableauSupport> tableauSupportManche() {
        return Optional.ofNullable(mancheCourante).flatMap(Question::tableauSupport);
    }

    /**
     * ADR-0073 : l'identifiant de l'image que la manche courante montre, vide quand l'item n'en
     * declare aucune - le cas ordinaire, jamais un cas particulier a verifier en plus. Cette
     * mecanique ne le lit pas : elle le transporte, exactement comme le texte-support et la part
     * designee, et ce qu'il designe ne change ni la facon de repondre ni le verdict.
     */
    public Optional<String> illustrationManche() {
        return Optional.ofNullable(mancheCourante).flatMap(Question::illustration);
    }

    /**
     * ADR-0073 clause 5 : ce qu'un lecteur d'ecran annonce de l'image de la manche courante. Vide
     * exactement quand {@link #illustrationManche()} l'est - les deux cles se gardent l'une l'autre
     * des la construction de l'item.
     */
    public Optional<String> illustrationTexteAlternatifManche() {
        return Optional.ofNullable(mancheCourante).flatMap(Question::illustrationTexteAlternatif);
    }

    /**
     * ADR-0075 : le texte que l'adulte lit a voix haute pour la manche courante, vide quand l'item
     * n'en declare pas - le cas ordinaire, jamais un cas particulier a verifier en plus. Cette
     * mecanique ne le lit pas, elle le transporte, exactement comme le texte-support et
     * l'illustration : il ne change ni la facon de repondre ni le verdict (ADR-0058).
     *
     * <p>VOI-8 : cette methode ne depend d'aucun etat d'essai ni de revelation - {@code
     * mancheCourante} reste le meme tant que la manche n'est pas resolue, donc cette valeur est
     * identique sur un item frais, un echec et une revelation. C'est ce qui permet a l'adulte de
     * redire le stimulus autant de fois que l'enfant le demande, sur le meme fragment.</p>
     */
    public Optional<String> texteAVoixHauteManche() {
        return Optional.ofNullable(mancheCourante).flatMap(Question::texteAVoixHaute);
    }

    public List<String> propositions() {
        return propositions;
    }

    /**
     * M1 : l'indice de la manche bloquante, affiche des le premier echec sur cette manche et
     * jusqu'a sa resolution. Vide sur une manche fraiche, comme sur une partie terminee.
     *
     * <p>M21 : vide egalement, quel que soit le nombre d'echecs, quand {@link
     * ContentSource#indicesNommentLaReponse()} vaut vrai pour ce paquet - un indice qui nomme la
     * reponse n'est pas une aide, il est une revelation deguisee des le premier essai.</p>
     */
    public Optional<String> indice() {
        if (essaisSurLaManche == 0 || contenu.indicesNommentLaReponse()) {
            return Optional.empty();
        }
        return mancheCourante.indice();
    }

    /** M9 : les propositions deja essayees sur la manche courante - marquees, jamais retirees. */
    public Set<String> propositionsEssayees() {
        return propositionsEssayees;
    }

    /** M13 : la derniere proposition pressee sur la manche courante, pour son point de focus. */
    public Optional<String> derniereTentative() {
        return derniereTentative;
    }

    /** M5 : vrai des le troisieme echec sur la manche courante - la reponse est desormais revelee. */
    public boolean revelee() {
        return revelee;
    }

    /**
     * M5, M9 clause 5 : la bonne reponse de la manche courante, exposee seulement une fois
     * revelee. Avant ce moment, aucune methode de cette classe ne la designe.
     */
    public Optional<String> reponseRevelee() {
        return revelee ? Optional.of(mancheCourante.bonneReponse()) : Optional.empty();
    }

    /**
     * M7 : l'explication de la manche courante survit du cote faux avec la revelation - exposee
     * seulement une fois revelee, jamais avant, comme {@link #reponseRevelee()}.
     */
    public Optional<String> explicationRevelee() {
        return revelee ? mancheCourante.explication() : Optional.empty();
    }

    public Optional<Verdict> dernierVerdict() {
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
