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

/** Test deterministe : "Range les fractions" (Maths CM1), ordre croissant par valeur. */
class FractionsGeneratorTest {

    private static final int SAMPLES = 300;

    /** Valeur de reference de chaque fraction (recalculee cote test, independante du generateur). */
    private static final Map<String, Double> VALEUR = Map.of(
            "1/5", 1.0 / 5,
            "1/4", 1.0 / 4,
            "1/3", 1.0 / 3,
            "1/2", 1.0 / 2,
            "2/3", 2.0 / 3,
            "3/4", 3.0 / 4,
            "4/5", 4.0 / 5);

    @Test
    void producesConformingAscendingArrangements() {
        FractionsGenerator generator = new FractionsGenerator(
                new ContentDescriptor("Maths", "CM1", "fractions"));
        // SeededRandomSource CONTINU : un seul flux pour tout l'echantillon.
        RandomSource random = new SeededRandomSource(11L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Trois tuiles : des labels de fractions connues, distincts.
            assertThat(item.reelSymbols()).hasSize(3);
            for (String symbol : item.reelSymbols()) {
                assertThat(VALEUR).containsKey(symbol);
            }
            assertThat(item.reelSymbols()).doesNotHaveDuplicates();

            // Attendu = ces memes labels tries par valeur CROISSANTE.
            List<String> expectedOrder = new ArrayList<>(item.reelSymbols());
            expectedOrder.sort((a, b) -> Double.compare(VALEUR.get(a), VALEUR.get(b)));

            List<String> expectedParts = Arrays.asList(item.expected().value().split(", "));
            assertThat(expectedParts).isEqualTo(expectedOrder);

            // Item non trivial : les tuiles NE sont PAS deja dans l'ordre croissant.
            assertThat(item.reelSymbols()).isNotEqualTo(expectedOrder);

            // Consigne fixe.
            assertThat(item.question()).isEqualTo("Range les fractions de la plus petite à la plus grande");

            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void isDeterministicForAGivenSeed() {
        ContentDescriptor descriptor = new ContentDescriptor("Maths", "CM1", "fractions");

        Item first = new FractionsGenerator(descriptor).next(new SeededRandomSource(42L));
        Item second = new FractionsGenerator(descriptor).next(new SeededRandomSource(42L));

        assertThat(first.reelSymbols()).isEqualTo(second.reelSymbols());
        assertThat(first.expected().value()).isEqualTo(second.expected().value());
        assertThat(first.question()).isEqualTo(second.question());
    }
}
