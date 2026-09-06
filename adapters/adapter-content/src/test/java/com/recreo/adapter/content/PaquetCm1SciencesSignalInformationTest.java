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
 * <b>S30-1, le paquet {@code cm1-sciences-signal-information}, ligne {@code SCI-CM1-05} de
 * "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-
 * s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md}, section 2.8. Encode le
 * plancher de dix items (FER-2), les deux elements du libelle en bonne reponse (FER-1) et les deux
 * taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de la bonne reponse : la
 * <b>reconnaissance du signal</b> ("Quel type de signal est-ce ?" -&gt; "un signal visuel" ou "un
 * signal sonore") et le <b>sens de l'information</b> qu'il porte (des phrases distinctes par
 * item).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque signal reste la garde de
 * {@code nadia-prof}, de meme que {@code SCI-1} (l'indice corrige la conception fausse sans nommer
 * la reponse).</p>
 */
class PaquetCm1SciencesSignalInformationTest {

    private static final String PAQUET = "cm1-sciences-signal-information";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent l'element "un signal" du libelle. */
    private static final Set<String> REPONSES_DE_SIGNAL = Set.of("un signal visuel", "un signal sonore");

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

    private static boolean estDeReconnaissanceDuSignal(Question item) {
        return REPONSES_DE_SIGNAL.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeSignal() {
        return items().stream()
                .filter(PaquetCm1SciencesSignalInformationTest::estDeReconnaissanceDuSignal).toList();
    }

    private static List<Question> itemsDInformation() {
        return items().stream()
                .filter(item -> !estDeReconnaissanceDuSignal(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S30-1). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, element "un signal" : les deux types de signal sont tous deux en bonne reponse. */
    @Test
    void fer1LElementSignalEstRepresenteParSesDeuxTypes() {
        Set<String> reponses = itemsDeSignal().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les deux types de signal doivent etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_SIGNAL);
    }

    /**
     * FER-1, element "une information" : les items de la tache B repondent tous par une phrase
     * distincte, jamais par un type de signal.
     */
    @Test
    void fer1LElementInformationEstServiParDesReponsesDistinctesDuTypeDeSignal() {
        List<Question> items = itemsDInformation();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(REPONSES_DE_SIGNAL)
                    .as("item %s : un item d'information ne repond pas par un type de signal",
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
        int signal = itemsDeSignal().size();
        int information = itemsDInformation().size();

        assertThat(signal).as("la tache de reconnaissance du signal est servie").isGreaterThan(0);
        assertThat(information).as("la tache du sens de l'information est servie").isGreaterThan(0);
        assertThat(Math.min(signal, information))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
