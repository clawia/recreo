package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Generateur « droite graduee » (cf. ADR-0018) : sur N tirages deterministes, la cible a
 * placer reste dans les bornes {@code [min, max]}, les bornes sont exposees dans les meta
 * (lues par la vue pour dessiner la droite), la consigne est « Place le nombre N », et il
 * n'y a NI symboles de rouleau NI distracteurs (la droite fournit toutes les positions).
 */
class DroiteGradueeGeneratorTest {

    private static final int SAMPLES = 500;

    @Test
    void cpProducesTargetsWithinZeroToTwenty() {
        int min = 0;
        int max = 20;
        DroiteGradueeGenerator generator = new DroiteGradueeGenerator(
                new ContentDescriptor("Mathématiques", "CP", "Nombres et calculs — situer un nombre"),
                min, max);
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // La cible (reponse attendue) est un entier dans [min, max].
            int cible = Integer.parseInt(item.expected().value());
            assertThat(cible).isBetween(min, max);

            // Bornes exposees dans les meta (lues par la vue).
            assertThat(item.meta().get("min")).isEqualTo(String.valueOf(min));
            assertThat(item.meta().get("max")).isEqualTo(String.valueOf(max));

            // Consigne « Place le nombre N ».
            assertThat(item.question()).startsWith("Place le nombre");

            // Ni symboles de rouleau ni distracteurs : la droite fournit les positions.
            assertThat(item.reelSymbols()).isEmpty();
            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void cm1ProducesTargetsWithinZeroToHundred() {
        int min = 0;
        int max = 100;
        DroiteGradueeGenerator generator = new DroiteGradueeGenerator(
                new ContentDescriptor("Mathématiques", "CM1", "Nombres et calculs — situer un nombre"),
                min, max);
        RandomSource random = new SeededRandomSource(7L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            int cible = Integer.parseInt(item.expected().value());
            assertThat(cible).isBetween(min, max);
            assertThat(item.meta().get("min")).isEqualTo(String.valueOf(min));
            assertThat(item.meta().get("max")).isEqualTo(String.valueOf(max));
            assertThat(item.question()).startsWith("Place le nombre");
            assertThat(item.reelSymbols()).isEmpty();
            assertThat(item.distractors()).isEmpty();
        }
    }
}
