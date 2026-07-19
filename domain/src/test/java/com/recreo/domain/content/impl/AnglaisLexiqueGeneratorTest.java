package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Le bon mot en anglais" (Anglais CM1, lexique). */
class AnglaisLexiqueGeneratorTest {

    private static final int SAMPLES = 500;

    private static final Map<String, String> REFERENCE = Map.ofEntries(
            Map.entry("chien", "dog"), Map.entry("chat", "cat"), Map.entry("maison", "house"),
            Map.entry("rouge", "red"), Map.entry("bleu", "blue"), Map.entry("vert", "green"),
            Map.entry("jaune", "yellow"), Map.entry("pomme", "apple"), Map.entry("eau", "water"),
            Map.entry("lait", "milk"), Map.entry("pain", "bread"), Map.entry("livre", "book"),
            Map.entry("école", "school"), Map.entry("ami", "friend"), Map.entry("soleil", "sun"),
            Map.entry("lune", "moon"), Map.entry("main", "hand"), Map.entry("pied", "foot"),
            Map.entry("tête", "head"), Map.entry("arbre", "tree"), Map.entry("fleur", "flower"),
            Map.entry("oiseau", "bird"), Map.entry("poisson", "fish"), Map.entry("cheval", "horse"),
            Map.entry("vache", "cow"), Map.entry("mouton", "sheep"), Map.entry("cochon", "pig"),
            Map.entry("gâteau", "cake"), Map.entry("chaise", "chair"), Map.entry("porte", "door"),
            Map.entry("fenêtre", "window"), Map.entry("voiture", "car"), Map.entry("vélo", "bike"),
            Map.entry("balle", "ball"), Map.entry("garçon", "boy"), Map.entry("fille", "girl"),
            Map.entry("mère", "mother"), Map.entry("père", "father"), Map.entry("frère", "brother"),
            Map.entry("sœur", "sister"), Map.entry("jour", "day"), Map.entry("nuit", "night"),
            Map.entry("chaud", "hot"), Map.entry("froid", "cold"), Map.entry("grand", "big"),
            Map.entry("petit", "small"), Map.entry("blanc", "white"), Map.entry("noir", "black"),
            Map.entry("lit", "bed"), Map.entry("chapeau", "hat"));

    private static final List<String> ALL_EN = List.copyOf(REFERENCE.values());

    @Test
    void lexiconHasAtLeastFiftyPairs() {
        assertThat(REFERENCE).hasSize(50);
    }

    @Test
    void aFiftyQuestionSessionNeverRepeatsAWord() {
        // Simule une session de 50 bonnes reponses : chaque mot reussi est exclu du
        // tirage suivant. On doit obtenir 50 cles distinctes, puis l'epuisement.
        AnglaisLexiqueGenerator generator = new AnglaisLexiqueGenerator(
                new ContentDescriptor("Anglais", "CM1", "lexique LV"));
        RandomSource random = new SeededRandomSource(7L);

        Set<String> mastered = new LinkedHashSet<>();
        for (int i = 0; i < 50; i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un mot doit rester disponible (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).as("aucun mot deja reussi ne revient").doesNotContain(key);
            mastered.add(key);
        }
        assertThat(mastered).hasSize(50);
        // 51e tirage : tout le vivier est maitrise -> epuisement signale.
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void producesConformingLexiconItems() {
        AnglaisLexiqueGenerator generator = new AnglaisLexiqueGenerator(
                new ContentDescriptor("Anglais", "CM1", "lexique LV"));
        RandomSource random = new SeededRandomSource(13L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.reelSymbols()).hasSize(1);
            String fr = item.reelSymbols().get(0);
            assertThat(REFERENCE).containsKey(fr);

            assertThat(item.question()).isEqualTo("Comment dit-on en anglais ?");

            // Regle : expected = bonne traduction anglaise.
            assertThat(item.expected().value()).isEqualTo(REFERENCE.get(fr));

            // 2 distracteurs : mots anglais de la liste, distincts, != reponse.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotHaveDuplicates();
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (Answer d : item.distractors()) {
                assertThat(ALL_EN).contains(d.value());
            }
        }
    }
}
