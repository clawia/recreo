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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S27-2, S28-1 puis S30-3, le paquet {@code cp-qlm-vivant-non-vivant}, ligne 1 de "Questionner
 * le monde" (CP).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-
 * une-ligne-et-le-prix-des-quatre-lots.md}, section 3.1. Encode ce qui est calculable sur ce
 * paquet : le plafond de vingt items, l'espace de reponse des trois taches qu'il porte depuis
 * S30-3, et les conditions de distribution ecrites dans son commentaire.
 *
 * <p><b>Trois taches distinctes depuis S30-3 (FER-3)</b>, distinguees par la forme de la bonne
 * reponse : la <b>categorisation</b> d'origine ("X, qu'est-ce que c'est ?" -&gt; "un être vivant"
 * ou "un objet"), la <b>selection</b> ("laquelle des trois choses est vivante ?" -&gt; le nom de la
 * chose, jamais une categorie) et la <b>verification</b> neuve sur les trois items de matiere
 * inerte ("X est-il un être vivant ?" -&gt; "oui" ou "non", `VIV-R2`).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : les conditions de biais ({@code BIA-*}) ne s'appliquent
 * pas a cette ligne (elles portent sur les lignes 4, 15 et 16 du referentiel, non servies ici),
 * l'exactitude factuelle ("qu'est-ce qui distingue un etre vivant") reste la garde de
 * {@code nadia-prof}, et savoir si un item de selection sert vraiment sa ligne reste un jugement
 * de sens, pas une propriete structurelle.</p>
 */
class PaquetCpQlmVivantNonVivantTest {

    private static final String PAQUET = "cp-qlm-vivant-non-vivant";

    private static final int PLAFOND_ITEMS = 20;

    /**
     * Espace de reponse de la tache de categorisation. L'opposition reste binaire (vivant / non
     * vivant, nadia-prof, 2026-09-04), servie par deux libelles : "un être vivant" et "un objet"
     * pour les quatre etres vivants et les quatre objets fabriques. Depuis S30-3 (`VIV-R2`), les
     * trois items de matiere inerte ne portent plus un troisieme libelle de categorisation : ils
     * sont passes a la tache de verification, ci-dessous.
     */
    private static final Set<String> CATEGORIES = Set.of("un être vivant", "un objet");

    /** Espace de reponse de la tache de verification, neuve depuis S30-3 (`VIV-R2`). */
    private static final Set<String> REPONSES_DE_VERIFICATION = Set.of("oui", "non");

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

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

    /**
     * Un item de categorisation se reconnait a sa bonne reponse : une des deux categories du
     * referentiel.
     */
    private static boolean estDeCategorisation(Question item) {
        return CATEGORIES.contains(item.bonneReponse());
    }

    /**
     * Un item de verification se reconnait a sa bonne reponse : "oui" ou "non" (S30-3, `VIV-R2`).
     */
    private static boolean estDeVerification(Question item) {
        return REPONSES_DE_VERIFICATION.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeCategorisation() {
        return items().stream().filter(PaquetCpQlmVivantNonVivantTest::estDeCategorisation).toList();
    }

    private static List<Question> itemsDeVerification() {
        return items().stream().filter(PaquetCpQlmVivantNonVivantTest::estDeVerification).toList();
    }

    private static List<Question> itemsDeSelection() {
        return items().stream()
                .filter(item -> !estDeCategorisation(item) && !estDeVerification(item))
                .toList();
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * Espace de reponse des items de categorisation : exactement les deux libelles connus depuis
     * S30-3 (la matiere inerte n'y repond plus).
     */
    @Test
    void lEspaceDeReponseDesItemsDeCategorisationEstExactementLesReponsesConnues() {
        for (Question item : itemsDeCategorisation()) {
            assertThat(CATEGORIES)
                    .as("item %s : la bonne reponse doit etre l'un des libelles connus",
                            item.id().value())
                    .contains(item.bonneReponse());
            assertThat(CATEGORIES)
                    .as("item %s : chaque distracteur doit etre l'un des libelles connus",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Condition de distribution ecrite dans le commentaire du paquet : la categorisation garde
     * quatre etres vivants et quatre objets fabriques, inchange depuis S27-2. Compte reel, jamais
     * affirme.
     */
    @Test
    void chacuneDesDeuxReponsesEstRepresenteeParmiLesItemsDeCategorisation() {
        List<Question> items = itemsDeCategorisation();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("les deux reponses de categorisation doivent toutes etre representees")
                .containsExactlyInAnyOrderElementsOf(CATEGORIES);
        assertThat(comptes.get("un être vivant"))
                .as("quatre etres vivants, compte inchange depuis S27-2")
                .isEqualTo(4);
        assertThat(comptes.get("un objet"))
                .as("quatre objets fabriques, compte inchange depuis S27-2")
                .isEqualTo(4);
    }

    /**
     * S30-3 (`VIV-R2`) : les trois items de matiere inerte (caillou, sable, neige) sont passes de
     * la categorisation a la verification, et repondent desormais "non" - "est-il un être vivant ?"
     * appelle oui ou non, jamais un libelle de categorie.
     */
    @Test
    void lesTroisItemsDeMatiereInerteRepondentNonALaVerification() {
        Set<String> enoncesDeMatiereInerte = Set.of(
                "Un caillou est-il un être vivant ?",
                "Le sable est-il un être vivant ?",
                "La neige est-elle un être vivant ?");

        List<Question> itemsDeMatiereInerte = itemsDeVerification().stream()
                .filter(item -> enoncesDeMatiereInerte.contains(item.enonce()))
                .toList();

        assertThat(itemsDeMatiereInerte)
                .as("les trois items de matiere inerte sont bien presents dans le paquet")
                .hasSize(3);
        for (Question item : itemsDeMatiereInerte) {
            assertThat(item.bonneReponse())
                    .as("item %s : la matiere inerte n'est pas un être vivant", item.id().value())
                    .isEqualTo("non");
            assertThat(item.distracteurs())
                    .as("item %s : un seul distracteur, \"oui\"", item.id().value())
                    .containsExactly("oui");
        }
    }

    /**
     * Les items de verification ne repondent que par oui ou par non, jamais par un libelle de
     * categorie ni par le nom d'une chose.
     */
    @Test
    void lesItemsDeVerificationRepondentUniquementParOuiOuNon() {
        List<Question> items = itemsDeVerification();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(REPONSES_DE_VERIFICATION)
                    .as("item %s : une verification repond oui ou non", item.id().value())
                    .contains(item.bonneReponse());
            assertThat(REPONSES_DE_VERIFICATION)
                    .as("item %s : le distracteur d'une verification est oui ou non",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Les items de selection ne repondent jamais par une categorie ni par oui/non : leur bonne
     * reponse et leurs distracteurs nomment la chose elle-meme.
     */
    @Test
    void lesItemsDeSelectionNomentDesChosesEtJamaisUneCategorieNiOuiOuNon() {
        List<Question> items = itemsDeSelection();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(CATEGORIES)
                    .as("item %s : une selection nomme une chose, pas une categorie",
                            item.id().value())
                    .doesNotContain(item.bonneReponse());
            assertThat(REPONSES_DE_VERIFICATION)
                    .as("item %s : une selection ne repond pas oui ou non", item.id().value())
                    .doesNotContain(item.bonneReponse());
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
            for (String distracteur : item.distracteurs()) {
                assertThat(CATEGORIES)
                        .as("item %s : un distracteur de selection nomme une chose, pas une"
                                + " categorie", item.id().value())
                        .doesNotContain(distracteur);
            }
        }
    }

    /**
     * FER-3 (nadia-prof, 2026-09-03, etendue le 2026-09-06 a une troisieme tache par `VIV-R2`) : au
     * moins deux taches distinctes, la moins servie portant au moins trois items. Trois taches en
     * portent desormais au moins trois : categorisation (huit), selection (trois), verification
     * (trois). Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void auMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int categorisation = itemsDeCategorisation().size();
        int selection = itemsDeSelection().size();
        int verification = itemsDeVerification().size();

        assertThat(categorisation).as("la tache de categorisation est servie").isGreaterThan(0);
        assertThat(selection).as("la tache de selection est servie").isGreaterThan(0);
        assertThat(verification).as("la tache de verification est servie").isGreaterThan(0);
        assertThat(Math.min(categorisation, Math.min(selection, verification)))
                .as("FER-3 : la tache la moins servie porte au moins %d items",
                        MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
