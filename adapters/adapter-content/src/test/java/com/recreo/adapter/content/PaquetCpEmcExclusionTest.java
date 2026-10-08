package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S31-2, le paquet {@code cp-emc-exclusion}, ligne {@code EMC-CP-03} de l'enseignement moral et
 * civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}.
 *
 * <p><b>Reecriture complete apres REFUS de nadia-prof le 2026-09-07</b>
 * ({@code squad/conformite/FICHE-bandit-manchot-exclusion-cp.md}) : la premiere version faisait de
 * la tache A une reponse invariante ("en parler a un adulte", six fois mot pour mot - {@code
 * FAB-1a}), et ne travaillait jamais la moitie "reconnaitre" du libelle. Cette version en fait une
 * vraie discrimination : reconnaitre qu'une situation est une moquerie qui se repete (items 1 a 3)
 * ou un accident isole (items 4 a 6) - la bonne reponse est un <b>classement</b>, jamais une action,
 * conformement a la lecture de {@code EMC-3} que nadia-prof a ecrite dans ce meme verdict :
 * "interdiction portant sur les propositions, pas prescription portant sur la bonne reponse".</p>
 *
 * <p><b>S34-EXC-TACHEB (2026-09-10, {@code marc-dev}) : la tache B est refondue, et son critere
 * d'appartenance change de nature.</b> Jusqu'a ce jour, un item entrait dans la tache B si sa bonne
 * reponse etait l'un de trois adultes nommes d'avance ({@code ADULTES_VALIDES}). {@code theo-archi}
 * a mesure (avis du 2026-09-10, {@code squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/
 * avis-theo-quatre-tests-de-cp-emc-exclusion.md}) que ce critere litteral rendait quatre methodes
 * plus dures que les clauses qu'elles pretendaient mesurer, dont une qui <b>devient verte sur une
 * population vide</b> des que la tache B ne porte plus ces trois valeurs - un faux vert par
 * ecartement silencieux, la meme famille que celle qu'{@code ADR-0088} a deja payee. <b>La tache B
 * se definit desormais comme le complement de la tache A</b>, elle-meme identifiee par la forme de
 * son enonce ("Qu'est-ce que c'est ?"), exactement la forme employee ce meme jour sur
 * {@code cp-emc-regle-souhait-loi} ({@code PaquetCpEmcRegleSouhaitLoiTest}, {@code S34-EMCCP07}) :
 * deux signaux independants - la forme de l'enonce pour l'appartenance, la bonne reponse pour ce
 * qu'une clause y verifie - au lieu d'un seul, qui rendait la verification tautologique.</p>
 *
 * <p><b>Objet de la tache B refondue</b> (arbitrage de {@code nadia-prof}, {@code
 * arbitrage-emccp07-nadia.md}, sections 4 a 4.6) : devant une moquerie qui se repete, l'enfant
 * reconnait, parmi trois conduites qu'un enfant de son age a reellement, celle qui consiste a en
 * parler a un adulte - jamais laquelle des trois adultes de l'ecole choisir. La bonne reponse varie
 * d'un item a l'autre ({@code C-2}, {@code FAB-1a}) pour ne jamais devenir invariante ; les deux
 * autres propositions sont des conduites neutres, jamais une conduite vilaine qui ferait gagner
 * l'item au ton ({@code C-1}, {@code CAT-1}).</p>
 *
 * <p><b>Ce que le partage par complement garde, et ce qu'il ne pretend plus garder.</b> Il rend
 * l'exhaustivite du partage vraie par construction : aucun item ne peut plus tomber hors des deux
 * taches, quel que soit ce que la tache B ecrit. Ce que
 * {@code chaqueItemAppartientAExactementUneDesDeuxTaches()} verifiait de reel - qu'aucune bonne
 * reponse de la tache B ne se replie sur un second classement - est repris par
 * {@link #aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement()}, qui mord reellement puisque
 * son critere (la bonne reponse) est independant de celui qui definit la tache (l'enonce).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-8}, {@code EMC-10} et
 * {@code CAT-1} demandent une lecture semantique, non gardees ici. {@code FAB-1} est verifiee depot
 * entier par {@code LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest} (ADR-0086). Le degre de
 * variation de la bonne reponse de la tache B au-dela de "pas unique" reste la garde de
 * {@code nadia-prof}, en jouant une partie complete (elle l'ecrit elle-meme, section 4.6).</p>
 */
class PaquetCpEmcExclusionTest {

    private static final String PAQUET = "cp-emc-exclusion";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    private static final String MOQUERIE_REPETEE = "une moquerie qui se répète";

    private static final String MISE_A_L_ECART_REPETEE = "une mise à l'écart qui se répète";

    private static final String ACCIDENT_ISOLE = "un accident isolé";

    /** Tache de classement : les seules categories attendues en bonne reponse. */
    private static final Set<String> CLASSEMENTS_VALIDES = Set.of(
            MOQUERIE_REPETEE, MISE_A_L_ECART_REPETEE, ACCIDENT_ISOLE);

    /**
     * Le bouton au rabais que nadia-prof a fait retirer de la tache B a s31 ("a personne, ...").
     * Garde-fou de regression derive du paquet entier (theo-archi, section 3, point 4 de son avis),
     * insensible a toute reecriture ulterieure de la tache B.
     */
    private static final String BOUTON_AU_RABAIS = "à personne";

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    /** Tache A : un item dont l'enonce pose la question de classement. */
    private static boolean estDeTacheClassement(Question item) {
        return item.enonce().contains("Qu'est-ce que c'est");
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(PaquetCpEmcExclusionTest::estDeTacheClassement).toList();
    }

    /** Tache B : le complement de la tache A. Exhaustif par construction. */
    private static List<Question> itemsDeLaTacheB() {
        return items().stream()
                .filter(item -> !estDeTacheClassement(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int classement = itemsDeClassement().size();
        int tacheB = itemsDeLaTacheB().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(tacheB).as("la tache B est servie").isGreaterThan(0);
        assertThat(Math.min(classement, tacheB))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Ce que {@code chaqueItemAppartientAExactementUneDesDeuxTaches()} verifiait de reel, sous un
     * critere d'appartenance devenu independant de la bonne reponse (theo-archi, section 3, point 3
     * de son avis) : aucune bonne reponse de la tache B ne se replie sur un second classement.
     */
    @Test
    void aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement() {
        List<Question> tacheB = itemsDeLaTacheB();
        assertThat(tacheB).as("la tache B est servie").isNotEmpty();
        for (Question item : tacheB) {
            assertThat(item.bonneReponse())
                    .as("item %s : la tache B ne reclasse pas la situation, elle porte une"
                            + " conduite", item.id().value())
                    .isNotIn(CLASSEMENTS_VALIDES);
        }
    }

    /**
     * FAB-1a, garde-fou de regression sur le motif exact du refus du 2026-09-07 : la tache de
     * classement sert ses deux categories, et aucune des deux ne domine au point de rendre la tache
     * invariante (chacune porte au moins trois items, comme la tache livree aujourd'hui).
     */
    @Test
    void fab1aLaTacheDeClassementSertSesDeuxCategoriesSansReponseInvariante() {
        List<Question> classement = itemsDeClassement();

        long moquerie = classement.stream()
                .filter(item -> item.bonneReponse().equals(MOQUERIE_REPETEE)).count();
        long miseALEcart = classement.stream()
                .filter(item -> item.bonneReponse().equals(MISE_A_L_ECART_REPETEE)).count();
        long accident = classement.stream()
                .filter(item -> item.bonneReponse().equals(ACCIDENT_ISOLE)).count();

        assertThat(moquerie + miseALEcart)
                .as("FAB-1a : le cote \"qui se répète\" (moquerie ou mise à l'écart, deux noms"
                        + " justes du meme cote de la decision) ne peut pas a lui seul porter"
                        + " toute la tache")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
        assertThat(miseALEcart)
                .as("FAB-1a : \"mise à l'écart qui se répète\" est servie (EXC-R15, garde-fou de"
                        + " regression sur le nom exact)")
                .isGreaterThanOrEqualTo(1);
        assertThat(accident)
                .as("FAB-1a : la categorie \"accident isolé\" est reellement servie, pas seulement"
                        + " illustree une fois")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * FAB-1a/C-2, encode sans seuil invente (theo-archi, section 3, point 5 ; nadia-prof, section
     * 4.6, Q2 : "je ne pose aucun seuil, [...] le minimum encodable reste [...] la tache B ne porte
     * pas une bonne reponse unique"). Tout degre de variation au-dela reste la garde de
     * {@code nadia-prof}, en jouant une partie complete.
     */
    @Test
    void laTacheBNePorteJamaisUneBonneReponseUnique() {
        Set<String> reponses = itemsDeLaTacheB().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FAB-1a/C-2 : la tache B ne porte pas une seule bonne reponse sur tous ses items")
                .hasSizeGreaterThan(1);
    }

    /**
     * CAT-1/PLA-1, garde-fou de regression derive du paquet entier plutot que d'une tache (theo-
     * archi, section 3, point 4 de son avis) : la proposition au rabais ("a personne, ...") que
     * nadia-prof a fait retirer a s31 ne reapparait dans aucune proposition, de aucune tache, quelle
     * que soit sa forme future.
     */
    @Test
    void laPropositionAuRabaisAPersonneNApparaitDansAucuneTache() {
        for (Question item : items()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item %s : aucune proposition n'est le bouton au rabais \"%s\" que"
                            + " nadia-prof a fait retirer", item.id().value(), BOUTON_AU_RABAIS)
                    .noneMatch(proposition -> proposition.startsWith(BOUTON_AU_RABAIS));
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix au total). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Les trois facons de nommer un adulte de l'ecole sur la tache B ("en parler a un
     * surveillant de la cour" / "... a sa maitresse" / "... a l'adulte qui surveille la
     * cantine"), plus les deux distracteurs qui evoquent un adulte sans agir ("croire qu'un
     * adulte a deja vu cela", "penser que la situation est deja connue d'un adulte").
     */
    private static boolean nommeUnAdulte(String proposition) {
        String texte = proposition.toLowerCase(Locale.ROOT);
        return texte.contains("adulte") || texte.contains("maîtresse") || texte.contains("surveillant");
    }

    /**
     * EXC-R11 (S35-EXC-INDICES, non bloquante mais soldee dans le meme geste - {@code
     * verdicts-nadia-instance-B.md}, section 3 ; {@code rapport-qualite.md} de yanis-qa, section 7).
     * A la cloture de s34, sur trois items de la tache B sur cinq (7, 9, 10), aucun distracteur ne
     * nommait un adulte : la bonne reponse etait donc la seule proposition a le faire, un raccourci
     * qu'un enfant peut presser sans rien comprendre a la situation. Sur ces trois items exactement,
     * ce test est rouge avant le correctif du commit {@code 5fd3ec59} (contenu ecrit avant cette
     * histoire) et vert apres : chaque item de la tache B porte desormais au moins un distracteur
     * qui nomme lui aussi un adulte.
     */
    @Test
    void exc11AucuneBonneReponseDeLaTacheBNEstLaSeulePropositionANommerUnAdulte() {
        List<Question> tacheB = itemsDeLaTacheB();
        assertThat(tacheB).as("la tache B est servie").isNotEmpty();
        for (Question item : tacheB) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            long nommentUnAdulte = propositions.stream()
                    .filter(PaquetCpEmcExclusionTest::nommeUnAdulte)
                    .count();
            assertThat(nommentUnAdulte)
                    .as("item %s : EXC-R11, la bonne reponse ne doit pas etre la seule"
                            + " proposition a nommer un adulte", item.id().value())
                    .isGreaterThan(1);
        }
    }

    /**
     * EXC-R12 (S35-EXC-INDICES, non bloquante mais soldee dans le meme geste - {@code
     * verdicts-nadia-instance-B.md}, section 3 ; {@code rapport-qualite.md} de yanis-qa, section 7).
     * Garde-fou de regression sur la forme exacte refusee, du meme genre que
     * {@link #laPropositionAuRabaisAPersonneNApparaitDansAucuneTache()} : le subjonctif passe a
     * pronom elide sans antecedent ("esperer qu'un adulte l'ait remarque(e)") ne reapparait dans
     * aucune proposition, quelle que soit la reecriture future de la tache B.
     */
    private static final String FORME_REFUSEE_EXC_R12 = "l'ait remarqu";

    @Test
    void exc12AucuneOppositionNeReprendLeSubjonctifPasseAPronomElideRefuseParExcR12() {
        for (Question item : items()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item %s : EXC-R12, aucune proposition ne reprend la forme refusee"
                            + " \"%s...\"", item.id().value(), FORME_REFUSEE_EXC_R12)
                    .noneMatch(proposition ->
                            proposition.toLowerCase(Locale.ROOT).contains(FORME_REFUSEE_EXC_R12));
        }
    }
}
