package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Le plus grand nombre" (Maths CP). */
class ComparaisonGeneratorTest {

    private static final int SAMPLES = 500;

    @Test
    void producesConformingComparisonItems() {
        ComparaisonGenerator generator = new ComparaisonGenerator(
                new ContentDescriptor("Mathématiques", "CP", "comparer des nombres"));
        RandomSource random = new SeededRandomSource(7L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Deux rouleaux : les deux nombres, distincts, dans [0,20].
            assertThat(item.reelSymbols()).hasSize(2);
            int a = Integer.parseInt(item.reelSymbols().get(0));
            int b = Integer.parseInt(item.reelSymbols().get(1));
            assertThat(a).isBetween(0, 20);
            assertThat(b).isBetween(0, 20);
            assertThat(a).isNotEqualTo(b);

            assertThat(item.question()).isEqualTo("Quel est le plus grand ?");

            // Regle : expected = max ; un seul distracteur = min.
            int expected = Integer.parseInt(item.expected().value());
            assertThat(expected).isEqualTo(Math.max(a, b));
            assertThat(item.distractors()).hasSize(1);
            assertThat(Integer.parseInt(item.distractors().get(0).value()))
                    .isEqualTo(Math.min(a, b));
            assertThat(item.distractors()).doesNotContain(item.expected());
        }
    }
}
