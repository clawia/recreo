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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S27-2, le paquet {@code cp-qlm-corps-sens}, deuxieme paquet de "Questionner le monde" (CP),
 * matiere neuve du produit.</b> Referentiel : ligne 3 de
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md}, section
 * 4. Ce paquet n'a pas une distribution binaire (huit organes distincts, un par item) : ce test
 * encode le plafond de vingt items et l'absence de reponse repetee, seules proprietes calculables
 * sur ce paquet.
 */
class PaquetCpQlmCorpsSensTest {

    private static final String PAQUET = "cp-qlm-corps-sens";

    private static final int PLAFOND_ITEMS = 20;

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

    /**
     * Chaque organe n'est bonne reponse que d'un seul item : aucune question ne se repete sous une
     * autre formulation.
     */
    @Test
    void aucunOrganeNEstBonneReponseDePlusDUnItem() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Set<String> dejaVues = new HashSet<>();
        for (Question item : items) {
            assertThat(dejaVues.add(item.bonneReponse()))
                    .as("item %s : la reponse \"%s\" est deja la bonne reponse d'un autre item de"
                            + " ce paquet", item.id().value(), item.bonneReponse())
                    .isTrue();
        }
    }

    /** Chaque item propose exactement trois choix (une bonne reponse, deux distracteurs). */
    @Test
    void chaqueItemProposeExactementTroisChoix() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }
}
