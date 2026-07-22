package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.FriseChronologiqueGenerator.Evenement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Frise chronologique" (Histoire CM1), ordre du plus ancien au plus recent. */
class FriseChronologiqueGeneratorTest {

    private static final int SAMPLES = 300;

    private static final String CONSIGNE = "Range du plus ancien au plus récent";

    /** Nombre d'evenements tires par item (tuiles a ranger). */
    private static final int COUNT = 3;

    /** Rang chronologique de reference (recalcule cote test, independant du generateur). */
    private static final Map<String, Integer> RANG = Map.of(
            "La Préhistoire", 1,
            "Les Gaulois", 2,
            "Le Moyen Âge", 3,
            "La Révolution française", 4,
            "La tour Eiffel", 5,
            "Aujourd'hui", 6);

    /** Liste VERIFIEE d'evenements passee au generateur (label + rang chronologique croissant). */
    private static final List<Evenement> EVENEMENTS = List.of(
            new Evenement("La Préhistoire", 1),
            new Evenement("Les Gaulois", 2),
            new Evenement("Le Moyen Âge", 3),
            new Evenement("La Révolution française", 4),
            new Evenement("La tour Eiffel", 5),
            new Evenement("Aujourd'hui", 6));

    private static FriseChronologiqueGenerator generator() {
        return new FriseChronologiqueGenerator(
                new ContentDescriptor("Histoire", "CM1", "temps"), EVENEMENTS, CONSIGNE, COUNT);
    }

    @Test
    void producesConformingChronologicalArrangements() {
        FriseChronologiqueGenerator generator = generator();
        // SeededRandomSource CONTINU : un seul flux pour tout l'echantillon.
        RandomSource random = new SeededRandomSource(11L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Trois tuiles : des labels de la liste connue, distincts.
            assertThat(item.reelSymbols()).hasSize(COUNT);
            for (String symbol : item.reelSymbols()) {
                assertThat(RANG).containsKey(symbol);
            }
            assertThat(item.reelSymbols()).doesNotHaveDuplicates();

            // Attendu = ces memes labels tries par rang chronologique CROISSANT.
            List<String> expectedOrder = new ArrayList<>(item.reelSymbols());
            expectedOrder.sort((a, b) -> Integer.compare(RANG.get(a), RANG.get(b)));

            List<String> expectedParts = Arrays.asList(item.expected().value().split(", "));
            assertThat(expectedParts).isEqualTo(expectedOrder);

            // Item non trivial : les tuiles NE sont PAS deja dans l'ordre chronologique.
            assertThat(item.reelSymbols()).isNotEqualTo(expectedOrder);

            // Consigne fixe.
            assertThat(item.question()).isEqualTo(CONSIGNE);

            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void isDeterministicForAGivenSeed() {
        Item first = generator().next(new SeededRandomSource(42L));
        Item second = generator().next(new SeededRandomSource(42L));

        assertThat(first.reelSymbols()).isEqualTo(second.reelSymbols());
        assertThat(first.expected().value()).isEqualTo(second.expected().value());
        assertThat(first.question()).isEqualTo(second.question());
    }
}
