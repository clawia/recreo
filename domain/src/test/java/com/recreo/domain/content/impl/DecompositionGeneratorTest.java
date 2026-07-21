package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Decompose le nombre" (valeur positionnelle), position ALEATOIRE. */
class DecompositionGeneratorTest {

    private static final int SAMPLES = 500;

    /** Recalcule le chiffre positionnel attendu depuis le nombre et la position. */
    private static int chiffrePositionnel(int nombre, String position) {
        return switch (position) {
            case "unités" -> nombre % 10;
            case "dizaines" -> (nombre / 10) % 10;
            case "centaines" -> (nombre / 100) % 10;
            default -> throw new IllegalArgumentException("position inconnue : " + position);
        };
    }

    private static void assertDistractorsConforming(Item item, int expected) {
        assertThat(item.distractors()).hasSize(2);
        int d0 = Integer.parseInt(item.distractors().get(0).value());
        int d1 = Integer.parseInt(item.distractors().get(1).value());
        assertThat(d0).isBetween(0, 9);
        assertThat(d1).isBetween(0, 9);
        // Distincts entre eux et differents de expected.
        assertThat(d0).isNotEqualTo(d1);
        assertThat(d0).isNotEqualTo(expected);
        assertThat(d1).isNotEqualTo(expected);
        assertThat(item.distractors()).doesNotContain(item.expected());
    }

    @Test
    void producesConformingCpItems() {
        DecompositionGenerator generator = new DecompositionGenerator(
                new ContentDescriptor("Mathématiques", "CP", "valeur positionnelle des chiffres"),
                false);
        // Un seul flux aleatoire CONTINU pour tout l'echantillon.
        RandomSource random = new SeededRandomSource(11L);

        boolean sawUnites = false;
        boolean sawDizaines = false;

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Un seul rouleau : un nombre de [10, 99].
            assertThat(item.reelSymbols()).hasSize(1);
            int nombre = Integer.parseInt(item.reelSymbols().get(0));
            assertThat(nombre).isBetween(10, 99);
            assertThat(nombre).isEqualTo(Integer.parseInt(item.meta().get("nombre")));

            String position = item.meta().get("position");
            // Jamais centaines en CP.
            assertThat(position).isIn("unités", "dizaines");
            if (position.equals("unités")) {
                sawUnites = true;
                assertThat(item.question()).isEqualTo("Quel est le chiffre des unités ?");
            } else {
                sawDizaines = true;
                assertThat(item.question()).isEqualTo("Quel est le chiffre des dizaines ?");
            }

            int expected = Integer.parseInt(item.expected().value());
            assertThat(expected).isEqualTo(chiffrePositionnel(nombre, position));

            assertDistractorsConforming(item, expected);
        }

        // Les DEUX positions apparaissent sur l'echantillon.
        assertThat(sawUnites).as("unités position must appear").isTrue();
        assertThat(sawDizaines).as("dizaines position must appear").isTrue();
    }

    @Test
    void producesConformingCm1Items() {
        DecompositionGenerator generator = new DecompositionGenerator(
                new ContentDescriptor("Mathématiques", "CM1", "valeur positionnelle des chiffres"),
                true);
        // Un seul flux aleatoire CONTINU pour tout l'echantillon.
        RandomSource random = new SeededRandomSource(29L);

        boolean sawUnites = false;
        boolean sawDizaines = false;
        boolean sawCentaines = false;

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Un seul rouleau : un nombre de [100, 999].
            assertThat(item.reelSymbols()).hasSize(1);
            int nombre = Integer.parseInt(item.reelSymbols().get(0));
            assertThat(nombre).isBetween(100, 999);
            assertThat(nombre).isEqualTo(Integer.parseInt(item.meta().get("nombre")));

            String position = item.meta().get("position");
            assertThat(position).isIn("unités", "dizaines", "centaines");
            switch (position) {
                case "unités" -> {
                    sawUnites = true;
                    assertThat(item.question()).isEqualTo("Quel est le chiffre des unités ?");
                }
                case "dizaines" -> {
                    sawDizaines = true;
                    assertThat(item.question()).isEqualTo("Quel est le chiffre des dizaines ?");
                }
                default -> {
                    sawCentaines = true;
                    assertThat(item.question()).isEqualTo("Quel est le chiffre des centaines ?");
                }
            }

            int expected = Integer.parseInt(item.expected().value());
            assertThat(expected).isEqualTo(chiffrePositionnel(nombre, position));

            assertDistractorsConforming(item, expected);
        }

        // Les TROIS positions apparaissent sur l'echantillon.
        assertThat(List.of(sawUnites, sawDizaines, sawCentaines))
                .as("all three positions must appear")
                .containsOnly(true);
    }
}
