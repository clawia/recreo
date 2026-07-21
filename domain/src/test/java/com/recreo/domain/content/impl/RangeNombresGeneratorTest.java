package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Range les nombres" (Maths CP). */
class RangeNombresGeneratorTest {

    private static final int SAMPLES = 500;
    private static final String QUESTION = "Range les nombres du plus petit au plus grand";

    @Test
    void producesConformingArrangementItems() {
        RangeNombresGenerator generator = new RangeNombresGenerator(
                new ContentDescriptor("Mathématiques", "CP", "ordre"));
        RandomSource random = new SeededRandomSource(7L);

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

            // Attendu = les memes nombres tries croissant puis joints par ", ".
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            sortedNumbers.sort(Integer::compareTo);
            List<String> sorted = new ArrayList<>();
            for (int value : sortedNumbers) {
                sorted.add(String.valueOf(value));
            }
            assertThat(item.expected().value()).isEqualTo(String.join(", ", sorted));

            // Item non trivial : les tuiles NE sont PAS deja dans l'ordre trie.
            assertThat(String.join(", ", item.reelSymbols())).isNotEqualTo(item.expected().value());

            assertThat(item.question()).isEqualTo(QUESTION);
            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void producesConformingDescendingArrangementItems() {
        ContentDescriptor descriptor = new ContentDescriptor("Mathématiques", "CP", "ordre");
        RangeNombresGenerator generator = new RangeNombresGenerator(descriptor, 4, 20, false);
        RandomSource random = new SeededRandomSource(7L);

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

            // Attendu = les memes nombres tries DECROISSANT puis joints par ", ".
            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            sortedNumbers.sort((x, y) -> Integer.compare(y, x));
            List<String> sorted = new ArrayList<>();
            for (int value : sortedNumbers) {
                sorted.add(String.valueOf(value));
            }
            assertThat(item.expected().value()).isEqualTo(String.join(", ", sorted));

            // Item non trivial : les tuiles NE sont PAS deja dans l'ordre trie.
            assertThat(String.join(", ", item.reelSymbols())).isNotEqualTo(item.expected().value());

            assertThat(item.question()).contains("du plus grand au plus petit");
            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void isDeterministicForAGivenSeed() {
        ContentDescriptor descriptor = new ContentDescriptor("Mathématiques", "CP", "ordre");

        Item first = new RangeNombresGenerator(descriptor).next(new SeededRandomSource(42L));
        Item second = new RangeNombresGenerator(descriptor).next(new SeededRandomSource(42L));

        assertThat(first.reelSymbols()).isEqualTo(second.reelSymbols());
        assertThat(first.expected().value()).isEqualTo(second.expected().value());
    }
}
