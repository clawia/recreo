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
 * <b>S27-2, le paquet {@code cp-qlm-vivant-non-vivant}, premier paquet de "Questionner le monde"
 * (CP), matiere neuve du produit.</b> Referentiel : ligne 1 de
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md}, section
 * 4. Encode ce qui est calculable sur ce paquet : le plafond de vingt items et la condition de
 * distribution ecrite dans son commentaire - deux categories, chacune reponse sur la moitie des
 * items.
 *
 * <p><b>Ce que ce test ne juge pas</b> : les conditions de biais ({@code BIA-*}) ne s'appliquent
 * pas a cette ligne (elles portent sur les lignes 4, 15 et 16 du referentiel, non servies ici), et
 * l'exactitude factuelle ("qu'est-ce qui distingue un etre vivant") reste la garde de
 * {@code nadia-prof}, comme pour tout contenu de cette matiere (section 8.2 de son arbitrage).</p>
 */
class PaquetCpQlmVivantNonVivantTest {

    private static final String PAQUET = "cp-qlm-vivant-non-vivant";

    private static final int PLAFOND_ITEMS = 20;

    private static final Set<String> CATEGORIES = Set.of("un être vivant", "un objet");

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

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /** Espace de reponse : exactement les deux categories du referentiel. */
    @Test
    void lEspaceDeReponseEstExactementLesDeuxCategories() {
        for (Question item : items()) {
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
     * Condition de distribution ecrite dans le commentaire du paquet : chaque categorie est
     * reponse sur la moitie des items. Compte reel, jamais affirme.
     */
    @Test
    void chacuneDesDeuxCategoriesEstReponseSurLaMoitieDesItems() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("les deux categories doivent toutes etre representees comme reponse")
                .containsExactlyInAnyOrderElementsOf(CATEGORIES);

        int total = items.size();
        assertThat(total % comptes.size())
                .as("une moitie exacte suppose un total pair")
                .isZero();
        int moitie = total / comptes.size();
        for (Map.Entry<String, Integer> entree : comptes.entrySet()) {
            assertThat(entree.getValue())
                    .as("la categorie \"%s\" doit etre reponse sur exactement la moitie des %d"
                            + " items, soit %d", entree.getKey(), total, moitie)
                    .isEqualTo(moitie);
        }
    }
}
