package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Le bon symbole" (Maths CP). */
class BonSymboleGeneratorTest {

    private static final int SAMPLES = 500;
    private static final List<String> SIGNS = List.of("<", ">", "=");

    @Test
    void producesConformingSignItems() {
        BonSymboleGenerator generator = new BonSymboleGenerator(
                new ContentDescriptor("Mathématiques", "CP", "comparer des nombres"));
        RandomSource random = new SeededRandomSource(7L);

        boolean sawEquality = false;

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Trois symboles : a ? b (le "?" au milieu = le signe a trouver).
            assertThat(item.reelSymbols()).hasSize(3);
            assertThat(item.reelSymbols().get(1)).isEqualTo("?");
            int a = Integer.parseInt(item.reelSymbols().get(0));
            int b = Integer.parseInt(item.reelSymbols().get(2));
            assertThat(a).isBetween(0, 20);
            assertThat(b).isBetween(0, 20);

            assertThat(item.question()).isEqualTo("Quel signe ?");

            // Regle : expected = signe coherent avec a et b.
            String expected = item.expected().value();
            assertThat(expected).isIn(SIGNS);
            String awaited = a < b ? "<" : a > b ? ">" : "=";
            assertThat(expected).isEqualTo(awaited);
            if ("=".equals(expected)) {
                sawEquality = true;
            }

            // Exactement 2 distracteurs = les deux autres signes.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (var d : item.distractors()) {
                assertThat(d.value()).isIn(SIGNS);
            }
            assertThat(item.distractors()).doesNotHaveDuplicates();
        }

        // Au moins un cas d'egalite observe sur l'echantillon.
        assertThat(sawEquality).isTrue();
    }
}
