package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Range les nombres" (Maths CP), sens tire ALEATOIREMENT. */
class RangeNombresGeneratorTest {

    private static final int SAMPLES = 500;

    @Test
    void producesConformingArrangementItems() {
        RangeNombresGenerator generator = new RangeNombresGenerator(
                new ContentDescriptor("Mathématiques", "CP", "ordre"));
        RandomSource random = new SeededRandomSource(7L);

        boolean sawAscending = false;
        boolean sawDescending = false;

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Quatre tuiles : entiers distincts dans [0, 20].
            assertThat(item.reelSymbols()).hasSize(4);
            List<Integer> numbers = new ArrayList<>();
            for (String symbol : item.reelSymbols()) {
                int value = Integer.parseInt(symbol);
                assertThat(value).isBetween(0, 20);
                numbers.add(value);
            }
            assertThat(numbers).doesNotHaveDuplicates();

            // Les memes nombres tries croissant / decroissant, joints par ", ".
            List<Integer> ascendingInts = new ArrayList<>(numbers);
            ascendingInts.sort(Integer::compareTo);
            List<Integer> descendingInts = new ArrayList<>(numbers);
            descendingInts.sort((x, y) -> Integer.compare(y, x));
            String ascending = join(ascendingInts);
            String descending = join(descendingInts);

            String expected = item.expected().value();
            boolean isAscending = expected.equals(ascending);
            boolean isDescending = expected.equals(descending);

            // Attendu = SOIT l'ordre croissant SOIT l'ordre decroissant.
            assertThat(isAscending || isDescending)
                    .as("expected must be ascending or descending order")
                    .isTrue();

            // La question correspond au sens.
            if (isAscending) {
                sawAscending = true;
                assertThat(item.question()).contains("du plus petit au plus grand");
            }
            if (isDescending) {
                sawDescending = true;
                assertThat(item.question()).contains("du plus grand au plus petit");
            }

            // Item non trivial : les tuiles NE sont PAS deja dans l'ordre trie.
            assertThat(String.join(", ", item.reelSymbols())).isNotEqualTo(expected);

            assertThat(item.distractors()).isEmpty();
        }

        // Les DEUX sens apparaissent sur l'echantillon.
        assertThat(sawAscending).as("ascending sense must appear").isTrue();
        assertThat(sawDescending).as("descending sense must appear").isTrue();
    }

    @Test
    void isDeterministicForAGivenSeed() {
        ContentDescriptor descriptor = new ContentDescriptor("Mathématiques", "CP", "ordre");

        Item first = new RangeNombresGenerator(descriptor).next(new SeededRandomSource(42L));
        Item second = new RangeNombresGenerator(descriptor).next(new SeededRandomSource(42L));

        assertThat(first.reelSymbols()).isEqualTo(second.reelSymbols());
        assertThat(first.expected().value()).isEqualTo(second.expected().value());
        assertThat(first.question()).isEqualTo(second.question());
    }

    private static String join(List<Integer> ints) {
        List<String> parts = new ArrayList<>();
        for (int value : ints) {
            parts.add(String.valueOf(value));
        }
        return String.join(", ", parts);
    }
}
