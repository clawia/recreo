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
 * <b>S30-2, le paquet {@code cm1-sciences-melanges}, ligne {@code SCI-CM1-02} de "Sciences et
 * technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-
 * ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md}, section 2.8. Encode le plancher
 * de dix items (FER-2), les trois elements du libelle en bonne reponse (FER-1) et les deux taches
 * distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de la bonne reponse : le
 * <b>classement</b> ("Quel type de mélange est-ce ?" -&gt; "un mélange homogène" ou "un mélange
 * hétérogène") et le <b>choix du moyen de séparation</b> ("Quel moyen utiliser ?" -&gt; une phrase
 * d'action).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque mélange reste la garde de
 * {@code nadia-prof}, de meme que {@code SCI-1} (l'indice corrige la conception fausse sans nommer
 * la reponse).</p>
 */
class PaquetCm1SciencesMelangesTest {

    private static final String PAQUET = "cm1-sciences-melanges";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent les elements "homogène" et "hétérogène" du libelle. */
    private static final Set<String> REPONSES_DE_CLASSEMENT =
            Set.of("un mélange homogène", "un mélange hétérogène");

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

    private static boolean estDeClassement(Question item) {
        return REPONSES_DE_CLASSEMENT.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(PaquetCm1SciencesMelangesTest::estDeClassement).toList();
    }

    private static List<Question> itemsDeSeparation() {
        return items().stream().filter(item -> !estDeClassement(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S30-2). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, elements "homogène" et "hétérogène" : les deux sont en bonne reponse. */
    @Test
    void fer1LesDeuxTypesDeMelangeSontRepresentesEnBonneReponse() {
        Set<String> reponses = itemsDeClassement().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les deux types de mélange doivent être en bonne réponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_CLASSEMENT);
    }

    /**
     * FER-1, element "un moyen de séparation" : au moins un moyen distinct est en bonne reponse de
     * la tache de separation - le libelle est au singulier, un seul moyen suffit.
     */
    @Test
    void fer1LElementMoyenDeSeparationEstServi() {
        List<Question> items = itemsDeSeparation();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(REPONSES_DE_CLASSEMENT)
                    .as("item %s : un item de séparation ne repond pas par un type de mélange",
                            item.id().value())
                    .doesNotContain(item.bonneReponse());
        }
    }

    /** Chaque item propose entre un et deux distracteurs (`AuPlusTroisPropositionsParItemTest`). */
    @Test
    void chaqueItemAAuMoinsUnEtAuPlusDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs().size())
                    .as("item %s : entre un et deux distracteurs attendus", item.id().value())
                    .isBetween(1, 2);
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int classement = itemsDeClassement().size();
        int separation = itemsDeSeparation().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(separation).as("la tache de séparation est servie").isGreaterThan(0);
        assertThat(Math.min(classement, separation))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
