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
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : classer une
 * situation comme moquerie repetee ou accident isole ("...Qu'est-ce que c'est ?"), et identifier
 * l'adulte a qui en parler selon le lieu decrit ("À qui ... doit-il/elle en parler ?") - tache
 * inchangee sur le fond, seule sa troisieme proposition au rabais ("a personne, ...") est retiree.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-8}, {@code EMC-10} et
 * {@code CAT-1} demandent une lecture semantique, non gardees ici. {@code FAB-1} est verifiee depot
 * entier par {@code LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest} (ADR-0086).</p>
 */
class PaquetCpEmcExclusionTest {

    private static final String PAQUET = "cp-emc-exclusion";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Tache de classement : les deux seules categories attendues en bonne reponse. */
    private static final Set<String> CLASSEMENTS_VALIDES = Set.of(
            "une moquerie qui se répète", "un accident isolé");

    /** Tache d'identification : les seuls adultes valides selon le lieu decrit. */
    private static final Set<String> ADULTES_VALIDES = Set.of(
            "à un surveillant de la cour", "à sa maîtresse", "à l'adulte qui surveille la cantine");

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

    /** Tache B : un enonce qui demande a quel adulte s'adresser. */
    private static boolean estDeTacheAdulte(Question item) {
        return item.enonce().contains("À qui");
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(item -> !estDeTacheAdulte(item)).toList();
    }

    private static List<Question> itemsDAdulte() {
        return items().stream().filter(PaquetCpEmcExclusionTest::estDeTacheAdulte).toList();
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
        int adulte = itemsDAdulte().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(adulte).as("la tache d'identification de l'adulte est servie").isGreaterThan(0);
        assertThat(Math.min(classement, adulte))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * FAB-1a, garde-fou de regression sur le motif exact du refus du 2026-09-07 : la tache de
     * classement sert ses deux categories, et aucune des deux ne domine au point de rendre la tache
     * invariante (chacune porte au moins trois items, comme la tache livree aujourd'hui).
     */
    @Test
    void fab1aLaTacheDeClassementSertSesDeuxCategoriesSansReponseInvariante() {
        List<Question> classement = itemsDeClassement();

        assertThat(classement)
                .as("chaque item de la tache de classement porte l'une des deux categories connues")
                .extracting(Question::bonneReponse)
                .allMatch(CLASSEMENTS_VALIDES::contains);

        long moquerie = classement.stream()
                .filter(item -> item.bonneReponse().equals("une moquerie qui se répète")).count();
        long accident = classement.stream()
                .filter(item -> item.bonneReponse().equals("un accident isolé")).count();

        assertThat(moquerie)
                .as("FAB-1a : la categorie \"moquerie qui se répète\" ne peut pas a elle seule"
                        + " porter toute la tache")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
        assertThat(accident)
                .as("FAB-1a : la categorie \"accident isolé\" est reellement servie, pas seulement"
                        + " illustree une fois")
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * CAT-1/PLA-1, garde-fou de regression : la troisieme proposition au rabais ("a personne, ...")
     * que nadia-prof a fait retirer de la tache d'identification n'y reapparait pas, et chaque
     * bonne reponse nomme toujours un adulte reel selon le lieu.
     */
    @Test
    void laTacheDAdulteNeSertPlusDeCandidatAuRabais() {
        List<Question> adulte = itemsDAdulte();

        assertThat(adulte)
                .as("chaque bonne reponse de la tache d'identification nomme un adulte reel")
                .extracting(Question::bonneReponse)
                .allMatch(ADULTES_VALIDES::contains);

        for (Question item : adulte) {
            assertThat(item.distracteurs())
                    .as("item %s : aucun distracteur n'est le bouton au rabais \"a personne\" que"
                            + " nadia-prof a fait retirer", item.id().value())
                    .allMatch(ADULTES_VALIDES::contains);
        }
    }

    /**
     * FER-3, condition de discrimination reelle : les deux categories de la tache de classement
     * different sur au moins trois items chacune (deja verifie ci-dessus), et les trois adultes de
     * la tache d'identification sont tous les trois representes en bonne reponse au moins une fois.
     */
    @Test
    void laTacheDAdulteRepresenteLesTroisAdultesEnBonneReponse() {
        Set<String> reponses = itemsDAdulte().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("les trois adultes valides doivent tous etre representes en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(ADULTES_VALIDES);
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
}
