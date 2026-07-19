package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test deterministe : "Complète l'égalité" (Maths CP, mecanique "texte a trous").
 * Verifie que chaque item est conforme a la regle {@code a + ___ = c} : consigne avec
 * le trou {@code ___}, {@code reelSymbols} VIDE, bonne reponse = {@code c - a}, deux
 * distracteurs valides (>= 0, distincts, sans la bonne reponse).
 */
class EgaliteGeneratorTest {

    private static final int SAMPLES = 1000;

    @Test
    void producesConformingEqualityItems() {
        EgaliteGenerator generator = new EgaliteGenerator(
                new ContentDescriptor("Mathématiques", "CP", "compléter une égalité"));
        RandomSource random = new SeededRandomSource(21L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Pas de rouleaux : l'egalite est portee par la consigne, avec le trou.
            assertThat(item.reelSymbols()).isEmpty();
            String question = item.question();
            assertThat(question).contains("___");
            assertThat(question).matches("\\d+ \\+ ___ = \\d+");

            // a + ___ = c  ->  a dans [1,9], c dans [a+1,10], reponse = c - a >= 1.
            String[] parts = question.replace(" + ___ = ", " ").split(" ");
            int a = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            assertThat(a).isBetween(1, 9);
            assertThat(c).isBetween(a + 1, 10);

            int answer = c - a;
            assertThat(item.expected().value()).isEqualTo(String.valueOf(answer));

            // Deux distracteurs : entiers >= 0, distincts, sans la bonne reponse.
            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(2);
            assertThat(distractors).doesNotHaveDuplicates();
            assertThat(distractors).doesNotContain(String.valueOf(answer));
            for (String d : distractors) {
                assertThat(Integer.parseInt(d)).isGreaterThanOrEqualTo(0);
            }
        }
    }

    @Test
    void isCombinatorialSoHasNoDistinctPoolSize() {
        EgaliteGenerator generator = new EgaliteGenerator(
                new ContentDescriptor("Mathématiques", "CP", "compléter une égalité"));
        assertThat(generator.distinctPoolSize()).isEmpty();
    }
}
