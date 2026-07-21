package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Complements a 10" (Maths CP). */
class ComplementDixGeneratorTest {

    private static final int SAMPLES = 500;

    @Test
    void producesConformingComplementItems() {
        ComplementDixGenerator generator = new ComplementDixGenerator(
                new ContentDescriptor("Mathématiques", "CP", "compléments à 10"));
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Equation affichee : « n + ⬚ = 10 » (5 symboles).
            assertThat(item.reelSymbols()).hasSize(5);
            int n = Integer.parseInt(item.reelSymbols().get(0));
            assertThat(n).isBetween(0, 10);
            assertThat(item.reelSymbols().get(0)).isEqualTo(item.meta().get("nombre"));
            assertThat(item.reelSymbols().get(1)).isEqualTo("+");
            assertThat(item.reelSymbols().get(2)).isEqualTo("⬚");
            assertThat(item.reelSymbols().get(3)).isEqualTo("=");
            assertThat(item.reelSymbols().get(4)).isEqualTo("10");

            assertThat(item.question()).isEqualTo("Combien pour aller à 10 ?");

            // Regle : expected = 10 - n.
            int expected = Integer.parseInt(item.expected().value());
            assertThat(expected).isEqualTo(10 - n);

            // 2 distracteurs, dans [0,10], distincts, != reponse.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotHaveDuplicates();
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (Answer d : item.distractors()) {
                assertThat(Integer.parseInt(d.value())).isBetween(0, 10);
            }
        }
    }
}
