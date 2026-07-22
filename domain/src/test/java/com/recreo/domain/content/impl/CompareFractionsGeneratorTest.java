package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.CompareFractionsGenerator.Fraction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * « Compare les fractions » (Maths CM1, bandit manchot). Vérifie que la réponse attendue
 * ({@code <}/{@code >}/{@code =}) est cohérente avec le produit croisé des deux fractions
 * affichées, qu'il y a exactement 2 distracteurs (les autres signes), et que des cas
 * d'égalité apparaissent (le pool inclut des fractions équivalentes : 1/2 = 2/4, 1/3 = 2/6).
 */
class CompareFractionsGeneratorTest {

    private static final int SAMPLES = 500;

    private static final String CONSIGNE = "Quel signe ?";

    /** Pool VERIFIE passe au generateur : inclut des EQUIVALENTES (1/2=2/4, 1/3=2/6) pour « = ». */
    private static final List<Fraction> POOL = List.of(
            new Fraction(1, 2, "1/2"),
            new Fraction(2, 4, "2/4"),
            new Fraction(1, 3, "1/3"),
            new Fraction(2, 6, "2/6"),
            new Fraction(1, 4, "1/4"),
            new Fraction(3, 4, "3/4"),
            new Fraction(2, 3, "2/3"),
            new Fraction(5, 6, "5/6"));

    private static int num(String label) {
        return Integer.parseInt(label.split("/")[0]);
    }

    private static int den(String label) {
        return Integer.parseInt(label.split("/")[1]);
    }

    @Test
    void producesTheCorrectSignForTwoFractions() {
        CompareFractionsGenerator generator = new CompareFractionsGenerator(
                new ContentDescriptor("Mathématiques", "CM1", "fractions"), POOL, CONSIGNE);
        RandomSource random = new SeededRandomSource(11L);
        boolean sawEqual = false;

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.reelSymbols()).hasSize(3);
            assertThat(item.reelSymbols().get(1)).isEqualTo("?");
            String a = item.reelSymbols().get(0);
            String b = item.reelSymbols().get(2);
            assertThat(item.question()).isEqualTo(CONSIGNE);

            long left = (long) num(a) * den(b);
            long right = (long) num(b) * den(a);
            String attendu = left < right ? "<" : left > right ? ">" : "=";
            assertThat(item.expected().value()).isEqualTo(attendu);
            if ("=".equals(attendu)) {
                sawEqual = true;
            }

            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(2).doesNotContain(attendu);
            assertThat(distractors).allSatisfy(d -> assertThat(d).isIn("<", ">", "="));
        }
        assertThat(sawEqual).as("des cas d'égalité (fractions équivalentes) apparaissent").isTrue();
    }
}
