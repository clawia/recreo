package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Le bon mot en anglais" (Anglais CM1, lexique). */
class AnglaisLexiqueGeneratorTest {

    private static final int SAMPLES = 500;

    private static final Map<String, String> REFERENCE = Map.ofEntries(
            Map.entry("chien", "dog"), Map.entry("chat", "cat"), Map.entry("maison", "house"),
            Map.entry("rouge", "red"), Map.entry("bleu", "blue"), Map.entry("pomme", "apple"),
            Map.entry("eau", "water"), Map.entry("livre", "book"), Map.entry("école", "school"),
            Map.entry("ami", "friend"), Map.entry("soleil", "sun"), Map.entry("main", "hand"));

    private static final List<String> ALL_EN = List.copyOf(REFERENCE.values());

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
