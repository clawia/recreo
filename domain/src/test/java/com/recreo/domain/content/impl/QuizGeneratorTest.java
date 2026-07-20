package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.QuizGenerator.Question;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire du générateur quiz GÉNÉRIQUE (le contenu vit désormais dans des fichiers
 * YAML éditables — cf. ADR-0009 ; ce test vérifie la MÉCANIQUE de tirage, indépendamment
 * du contenu). Un pool en ligne suffit : items sans rouleaux (carte-question), bonne
 * réponse + distracteurs, anti-doublon en mode session, épuisement, taille de vivier.
 */
class QuizGeneratorTest {

    private static final int SAMPLES = 400;

    private static final List<Question> POOL = List.of(
            new Question("Question A ?", "Bonne A", List.of("Faux A1", "Faux A2")),
            new Question("Question B ?", "Bonne B", List.of("Faux B1", "Faux B2")),
            new Question("Question C (2 options) ?", "Vrai", List.of("Faux")));

    private QuizGenerator generator() {
        return new QuizGenerator(new ContentDescriptor("Test", "CP", "quiz"), POOL);
    }

    @Test
    void producesConformingItems() {
        RandomSource random = new SeededRandomSource(42L);
        Set<String> questions = POOL.stream()
                .map(Question::question).collect(LinkedHashSet::new, Set::add, Set::addAll);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator().next(random);
            // Pas de rouleaux : la question est portée par la consigne (carte-quiz).
            assertThat(item.reelSymbols()).isEmpty();
            assertThat(item.question()).isIn(questions);

            Question ref = POOL.stream().filter(q -> q.question().equals(item.question())).findFirst().orElseThrow();
            assertThat(item.expected().value()).isEqualTo(ref.bonne());

            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).containsExactlyInAnyOrderElementsOf(ref.distracteurs());
            assertThat(distractors).doesNotContain(ref.bonne());
        }
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        QuizGenerator generator = generator();
        RandomSource random = new SeededRandomSource(3L);

        Set<String> mastered = new LinkedHashSet<>();
        for (int i = 0; i < POOL.size(); i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un item doit rester (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).doesNotContain(key);
            mastered.add(key);
        }
        assertThat(mastered).hasSize(POOL.size());
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void distinctPoolSizeMatchesTheGivenPool() {
        assertThat(generator().distinctPoolSize()).hasValue(POOL.size());
    }
}
