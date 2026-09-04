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
 * <b>S27-2 puis S28-1, le paquet {@code cp-qlm-vivant-non-vivant}, ligne 1 de "Questionner le
 * monde" (CP).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-
 * ligne-et-le-prix-des-quatre-lots.md}, section 3.1. Encode ce qui est calculable sur ce paquet :
 * le plafond de vingt items, l'espace de reponse des deux taches qu'il porte depuis S28-1, et les
 * conditions de distribution ecrites dans son commentaire.
 *
 * <p><b>Deux taches distinctes depuis S28-1 (FER-3)</b>, distinguees par la forme de la bonne
 * reponse : la <b>categorisation</b> d'origine ("X, qu'est-ce que c'est ?" -&gt; une des deux
 * categories du referentiel) et la <b>selection</b> neuve ("laquelle des trois choses est
 * vivante ?" -&gt; le nom de la chose, jamais une categorie).</p>
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

    /** Espace de reponse de la tache de categorisation, seule tache que porte le premier paquet. */
    private static final Set<String> CATEGORIES = Set.of("un être vivant", "un objet");

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
     * referentiel. Tout le reste est un item de selection.
     */
    private static boolean estDeCategorisation(Question item) {
        return CATEGORIES.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeCategorisation() {
        return items().stream().filter(PaquetCpQlmVivantNonVivantTest::estDeCategorisation).toList();
    }

    private static List<Question> itemsDeSelection() {
        return items().stream().filter(item -> !estDeCategorisation(item)).toList();
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /** Espace de reponse des items de categorisation : exactement les deux categories. */
    @Test
    void lEspaceDeReponseDesItemsDeCategorisationEstExactementLesDeuxCategories() {
        for (Question item : itemsDeCategorisation()) {
            assertThat(CATEGORIES)
                    .as("item %s : la bonne reponse doit etre l'une des deux categories",
                            item.id().value())
                    .contains(item.bonneReponse());
            assertThat(CATEGORIES)
                    .as("item %s : chaque distracteur doit etre l'une des deux categories",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Condition de distribution ecrite dans le commentaire du paquet : depuis S28-1, la moitie
     * "objet" porte a la fois les objets fabriques et la matiere inerte, donc le compte n'est plus
     * une moitie exacte. Compte reel, jamais affirme.
     */
    @Test
    void chacuneDesDeuxCategoriesEstRepresenteeParmiLesItemsDeCategorisation() {
        List<Question> items = itemsDeCategorisation();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("les deux categories doivent toutes etre representees comme reponse")
                .containsExactlyInAnyOrderElementsOf(CATEGORIES);
        assertThat(comptes.get("un être vivant"))
                .as("quatre etres vivants, compte inchange depuis S27-2")
                .isEqualTo(4);
        assertThat(comptes.get("un objet"))
                .as("sept objets depuis S28-1 : quatre fabriques (S27-2) et trois de matiere"
                        + " inerte (S28-1)")
                .isEqualTo(7);
    }

    /**
     * Les items de selection ne repondent jamais par une categorie : leur bonne reponse et leurs
     * distracteurs nomment la chose elle-meme, jamais "un être vivant" ou "un objet".
     */
    @Test
    void lesItemsDeSelectionNomentDesChosesEtJamaisUneCategorie() {
        List<Question> items = itemsDeSelection();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(CATEGORIES)
                    .as("item %s : une selection nomme une chose, pas une categorie",
                            item.id().value())
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
     * FER-3 (nadia-prof, 2026-09-03) : au moins deux taches distinctes, la moins servie portant au
     * moins trois items. Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void auMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int categorisation = itemsDeCategorisation().size();
        int selection = itemsDeSelection().size();

        assertThat(categorisation).as("la tache de categorisation est servie").isGreaterThan(0);
        assertThat(selection).as("la tache de selection est servie").isGreaterThan(0);
        assertThat(Math.min(categorisation, selection))
                .as("FER-3 : la tache la moins servie porte au moins %d items",
                        MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
