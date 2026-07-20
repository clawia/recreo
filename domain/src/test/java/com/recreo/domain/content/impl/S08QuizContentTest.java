package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests des jeux de contenu "quiz-aventure" ajoutés en s08 : EMC CP (Vivre ensemble),
 * EMC CM1 (Citoyenneté & respect) et le "Vrai ou Faux" de sciences CM1. Vérifie les
 * invariants structurels (carte-question sans rouleaux, bonne réponse hors distracteurs,
 * distracteurs distincts), le mode session (anti-doublon + épuisement) et, pour le
 * Vrai/Faux, qu'il n'y a bien que deux options « Vrai » / « Faux ».
 */
class S08QuizContentTest {

    private static final int SAMPLES = 400;

    private QuizGenerator emcCp() {
        return new EmcCpGenerator(new ContentDescriptor("EMC", "CP", "Règles de vie"));
    }

    private QuizGenerator emcCm1() {
        return new EmcCm1Generator(new ContentDescriptor("EMC", "CM1", "Citoyenneté"));
    }

    private QuizGenerator vraiFaux() {
        return new VraiFauxSciencesCm1Generator(
                new ContentDescriptor("Sciences et technologie", "CM1", "Vrai ou faux"));
    }

    @Test
    void emcCpIsWellFormed() {
        assertWellFormedQuiz(emcCp(), 3);
        assertThat(emcCp().distinctPoolSize()).hasValue(10);
    }

    @Test
    void emcCm1IsWellFormed() {
        assertWellFormedQuiz(emcCm1(), 3);
        assertThat(emcCm1().distinctPoolSize()).hasValue(10);
    }

    @Test
    void vraiFauxIsWellFormed() {
        assertWellFormedQuiz(vraiFaux(), 2);
        assertThat(vraiFaux().distinctPoolSize()).hasValue(10);
    }

    /** Chaque affirmation « Vrai ou Faux » n'a que deux options : Vrai et Faux. */
    @Test
    void vraiFauxHasExactlyTwoOptionsVraiOrFaux() {
        RandomSource random = new SeededRandomSource(11L);
        QuizGenerator generator = vraiFaux();
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);
            assertThat(item.distractors()).hasSize(1);
            String bonne = item.expected().value();
            assertThat(bonne).isIn("Vrai", "Faux");
            String distracteur = item.distractors().get(0).value();
            assertThat(distracteur).isIn("Vrai", "Faux");
            assertThat(distracteur).isNotEqualTo(bonne);
        }
    }

    @Test
    void sessionsNeverRepeatMasteredItemsThenExhaust() {
        assertExhausts(emcCp(), 10, 1L);
        assertExhausts(emcCm1(), 10, 2L);
        assertExhausts(vraiFaux(), 10, 3L);
    }

    /**
     * Invariants d'un quiz : pas de rouleaux (carte-question), consigne non vide,
     * {@code optionCount} options en tout (bonne + distracteurs), distracteurs distincts
     * et sans la bonne réponse.
     */
    private void assertWellFormedQuiz(QuizGenerator generator, int optionCount) {
        RandomSource random = new SeededRandomSource(5L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);
            assertThat(item.reelSymbols()).isEmpty();
            assertThat(item.question()).isNotBlank();

            String bonne = item.expected().value();
            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(optionCount - 1);
            assertThat(distractors).doesNotHaveDuplicates();
            assertThat(distractors).doesNotContain(bonne);
        }
    }

    private void assertExhausts(QuizGenerator generator, int pool, long seed) {
        RandomSource random = new SeededRandomSource(seed);
        Set<String> mastered = new LinkedHashSet<>();
        for (int i = 0; i < pool; i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un item doit rester (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).doesNotContain(key);
            mastered.add(key);
        }
        assertThat(mastered).hasSize(pool);
        assertThat(generator.next(random, mastered)).isEmpty();
    }
}
