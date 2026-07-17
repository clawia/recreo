package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.DifficultyConfig;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.Operation;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

class CalculMentalGeneratorTest {

    private static final int SAMPLES = 500;

    @Test
    void cpProducesOnlyAdditionsAndSubtractionsWithinTen() {
        DifficultyConfig cp = new DifficultyConfig(
                0, 10, EnumSet.of(Operation.ADDITION, Operation.SOUSTRACTION), 2);
        CalculMentalGenerator generator = new CalculMentalGenerator(
                cp, new ContentDescriptor("mathematiques", "CP", "Calcul mental"));
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.meta().get("operation"))
                    .isIn(Operation.ADDITION.name(), Operation.SOUSTRACTION.name());

            int a = Integer.parseInt(item.meta().get("operandA"));
            int b = Integer.parseInt(item.meta().get("operandB"));
            assertThat(a).isBetween(0, 10);
            assertThat(b).isBetween(0, 10);

            // Verifie la coherence enonce / reponse attendue.
            Operation op = Operation.valueOf(item.meta().get("operation"));
            assertThat(Integer.parseInt(item.expected().value())).isEqualTo(op.apply(a, b));

            // Distracteurs : bon nombre, distincts, differents de la reponse.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotContain(item.expected());
            assertThat(item.distractors()).doesNotHaveDuplicates();
        }
    }

    @Test
    void cm1IncludesMultiplicationsAndLargerOperands() {
        DifficultyConfig cm1 = new DifficultyConfig(
                2, 12,
                EnumSet.of(Operation.MULTIPLICATION, Operation.ADDITION, Operation.SOUSTRACTION),
                3);
        CalculMentalGenerator generator = new CalculMentalGenerator(
                cm1, new ContentDescriptor("mathematiques", "CM1", "Calcul mental"));
        RandomSource random = new SeededRandomSource(7L);

        boolean sawMultiplication = false;
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            int a = Integer.parseInt(item.meta().get("operandA"));
            int b = Integer.parseInt(item.meta().get("operandB"));
            assertThat(a).isBetween(2, 12);
            assertThat(b).isBetween(2, 12);

            assertThat(item.distractors()).hasSize(3);

            if (Operation.MULTIPLICATION.name().equals(item.meta().get("operation"))) {
                sawMultiplication = true;
            }
        }

        assertThat(sawMultiplication)
                .as("CM1 doit inclure des multiplications")
                .isTrue();
    }
}
