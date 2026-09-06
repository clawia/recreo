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
 * <b>S30-1, le paquet {@code cm1-sciences-chaine-alimentaire}, ligne {@code SCI-CM1-17} de
 * "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-
 * s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md}, section 2.8. Encode le
 * plancher de dix items (FER-2), les deux elements du libelle en bonne reponse (FER-1) et les deux
 * taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de la bonne reponse : <b>qui mange
 * qui</b> ("Dans la forêt, qui mange... ?" ou "que mange... ?" -&gt; le nom d'un être vivant du
 * milieu, servant l'element "les êtres vivants du milieu") et la <b>consequence d'une disparition</b>
 * ("que risquerait-il d'arriver... ?" -&gt; une phrase decrivant l'effet du lien alimentaire,
 * servant l'element "la relation alimentaire").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque relation reste la garde
 * de {@code nadia-prof}, de meme que {@code V3} (aucune chaine entiere construite) et
 * {@code CYC-1} (coherence avec {@code cp-qlm-vivant-non-vivant.yaml}).</p>
 */
class PaquetCm1SciencesChaineAlimentaireTest {

    private static final String PAQUET = "cm1-sciences-chaine-alimentaire";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent l'element "les êtres vivants du milieu" du libelle. */
    private static final Set<String> ETRES_VIVANTS_DU_MILIEU = Set.of(
            "l'écureuil", "le renard", "de l'herbe", "le mulot", "la chenille");

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

    private static boolean estDeQuiMangeQui(Question item) {
        return ETRES_VIVANTS_DU_MILIEU.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeQuiMangeQui() {
        return items().stream().filter(PaquetCm1SciencesChaineAlimentaireTest::estDeQuiMangeQui).toList();
    }

    private static List<Question> itemsDeConsequence() {
        return items().stream().filter(item -> !estDeQuiMangeQui(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S30-1). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1, element "les êtres vivants du milieu" : au moins cinq êtres vivants distincts sont en
     * bonne reponse de la tache "qui mange qui".
     */
    @Test
    void fer1LElementEtresVivantsEstServiParPlusieursEspecesDistinctes() {
        Set<String> reponses = itemsDeQuiMangeQui().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : plusieurs êtres vivants distincts doivent être en bonne réponse")
                .hasSizeGreaterThanOrEqualTo(3);
    }

    /**
     * FER-1, element "la relation alimentaire" : les items de consequence repondent tous par une
     * phrase decrivant un effet, jamais par le seul nom d'un être vivant du milieu.
     */
    @Test
    void fer1LElementRelationAlimentaireEstServiParDesReponsesDeConsequence() {
        List<Question> items = itemsDeConsequence();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(ETRES_VIVANTS_DU_MILIEU)
                    .as("item %s : un item de consequence ne repond pas par le seul nom d'un être"
                            + " vivant", item.id().value())
                    .doesNotContain(item.bonneReponse());
        }
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int quiMangeQui = itemsDeQuiMangeQui().size();
        int consequence = itemsDeConsequence().size();

        assertThat(quiMangeQui).as("la tache qui mange qui est servie").isGreaterThan(0);
        assertThat(consequence).as("la tache de consequence est servie").isGreaterThan(0);
        assertThat(Math.min(quiMangeQui, consequence))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
