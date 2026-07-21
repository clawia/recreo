package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Le plus grand / plus petit nombre" (Maths CP), sens ALEATOIRE. */
class ComparaisonGeneratorTest {

    private static final int SAMPLES = 500;

    @Test
    void producesConformingComparisonItems() {
        ComparaisonGenerator generator = new ComparaisonGenerator(
                new ContentDescriptor("Mathématiques", "CP", "comparer des nombres"));
        RandomSource random = new SeededRandomSource(7L);

        boolean sawLargest = false;
        boolean sawSmallest = false;

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Deux rouleaux : les deux nombres, distincts, dans [0,20].
            assertThat(item.reelSymbols()).hasSize(2);
            int a = Integer.parseInt(item.reelSymbols().get(0));
            int b = Integer.parseInt(item.reelSymbols().get(1));
            assertThat(a).isBetween(0, 20);
            assertThat(b).isBetween(0, 20);
            assertThat(a).isNotEqualTo(b);

            int max = Math.max(a, b);
            int min = Math.min(a, b);
            int expected = Integer.parseInt(item.expected().value());

            // expected = max OU min, coherent avec la question.
            boolean plusGrand = item.question().equals("Quel est le plus grand ?");
            boolean plusPetit = item.question().equals("Quel est le plus petit ?");
            assertThat(plusGrand || plusPetit)
                    .as("question must ask for largest or smallest")
                    .isTrue();

            assertThat(item.distractors()).hasSize(1);
            int distractor = Integer.parseInt(item.distractors().get(0).value());

            if (plusGrand) {
                sawLargest = true;
                assertThat(expected).isEqualTo(max);
                assertThat(distractor).isEqualTo(min);
            } else {
                sawSmallest = true;
                assertThat(expected).isEqualTo(min);
                assertThat(distractor).isEqualTo(max);
            }
            assertThat(item.distractors()).doesNotContain(item.expected());
        }

        // Les DEUX sens apparaissent sur l'echantillon.
        assertThat(sawLargest).as("largest sense must appear").isTrue();
        assertThat(sawSmallest).as("smallest sense must appear").isTrue();
    }
}
