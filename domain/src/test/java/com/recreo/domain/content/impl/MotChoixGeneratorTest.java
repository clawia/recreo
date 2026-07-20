package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.MotChoixGenerator.Entree;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire du générateur générique « mot → réponse » (le contenu vit dans des
 * fichiers YAML éditables — ADR-0010 ; ce test vérifie la MÉCANIQUE, avec des données en
 * ligne). Couvre les deux modes de distracteurs (explicite / vivier), le prompt sur le
 * rouleau, l'anti-doublon en mode session et l'épuisement.
 */
class MotChoixGeneratorTest {

    private static final String QUESTION = "Quelle réponse ?";
    private static final int SAMPLES = 400;

    private static final List<Entree> EXPLICIT = List.of(
            new Entree("MOT1", "A", List.of("B", "C")),
            new Entree("MOT2", "D", List.of("E", "F")),
            new Entree("MOT3", "G", List.of("H")));

    private static final List<Entree> POOL = List.of(
            new Entree("chat", "nom", List.of()),
            new Entree("manger", "verbe", List.of()),
            new Entree("rouge", "adjectif", List.of()),
            new Entree("le", "déterminant", List.of()));

    @Test
    void explicitMode_usesTheItemDistractors() {
        MotChoixGenerator generator =
                MotChoixGenerator.explicit(new ContentDescriptor("Test", "CP", "d"), QUESTION, EXPLICIT);
        RandomSource random = new SeededRandomSource(42L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);
            assertThat(item.reelSymbols()).hasSize(1);
            assertThat(item.question()).isEqualTo(QUESTION);
            String prompt = item.reelSymbols().get(0);
            Entree ref = EXPLICIT.stream().filter(e -> e.prompt().equals(prompt)).findFirst().orElseThrow();
            assertThat(item.expected().value()).isEqualTo(ref.bonne());
            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).containsExactlyInAnyOrderElementsOf(ref.distracteurs());
        }
    }

    @Test
    void poolMode_drawsDistinctDistractorsFromOtherAnswers() {
        MotChoixGenerator generator = MotChoixGenerator.fromPool(
                new ContentDescriptor("Test", "CM1", "d"), QUESTION, POOL, 2);
        Set<String> allAnswers = POOL.stream()
                .map(Entree::bonne).collect(LinkedHashSet::new, Set::add, Set::addAll);
        RandomSource random = new SeededRandomSource(7L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);
            String bonne = item.expected().value();
            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(2);
            assertThat(distractors).doesNotHaveDuplicates();
            assertThat(distractors).doesNotContain(bonne);
            assertThat(allAnswers).containsAll(distractors); // distracteurs issus du vivier
        }
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        MotChoixGenerator generator =
                MotChoixGenerator.explicit(new ContentDescriptor("Test", "CP", "d"), QUESTION, EXPLICIT);
        RandomSource random = new SeededRandomSource(3L);
        Set<String> mastered = new LinkedHashSet<>();
        for (int i = 0; i < EXPLICIT.size(); i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un item doit rester (tirage %s)", i + 1).isPresent();
            mastered.add(item.get().key());
        }
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void distinctPoolSizeMatchesEntries() {
        MotChoixGenerator generator =
                MotChoixGenerator.explicit(new ContentDescriptor("Test", "CP", "d"), QUESTION, EXPLICIT);
        assertThat(generator.distinctPoolSize()).hasValue(EXPLICIT.size());
    }
}
