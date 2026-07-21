package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Frise chronologique" (Histoire CM1), ordre du plus ancien au plus recent. */
class FriseChronologiqueGeneratorTest {

    private static final int SAMPLES = 300;

    /** Rang chronologique de reference (recalcule cote test, independant du generateur). */
    private static final Map<String, Integer> RANG = Map.of(
            "La Préhistoire", 1,
            "Les Gaulois", 2,
            "Le Moyen Âge", 3,
            "La Révolution française", 4,
            "La tour Eiffel", 5,
            "Aujourd'hui", 6);

    @Test
    void producesConformingChronologicalArrangements() {
        FriseChronologiqueGenerator generator = new FriseChronologiqueGenerator(
                new ContentDescriptor("Histoire", "CM1", "temps"));
        // SeededRandomSource CONTINU : un seul flux pour tout l'echantillon.
        RandomSource random = new SeededRandomSource(11L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Trois tuiles : des labels de la liste connue, distincts.
            assertThat(item.reelSymbols()).hasSize(3);
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
            assertThat(item.question()).isEqualTo("Range du plus ancien au plus récent");

            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void isDeterministicForAGivenSeed() {
        ContentDescriptor descriptor = new ContentDescriptor("Histoire", "CM1", "temps");

        Item first = new FriseChronologiqueGenerator(descriptor).next(new SeededRandomSource(42L));
        Item second = new FriseChronologiqueGenerator(descriptor).next(new SeededRandomSource(42L));

        assertThat(first.reelSymbols()).isEqualTo(second.reelSymbols());
        assertThat(first.expected().value()).isEqualTo(second.expected().value());
        assertThat(first.question()).isEqualTo(second.question());
    }
}
