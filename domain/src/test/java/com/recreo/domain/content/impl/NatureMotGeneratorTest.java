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

/** Test deterministe : "La nature du mot" (Francais CM1, classes de mots). */
class NatureMotGeneratorTest {

    private static final int SAMPLES = 500;

    private static final List<String> NATURES = List.of("nom", "verbe", "adjectif", "déterminant");

    private static final Map<String, String> REFERENCE = Map.ofEntries(
            Map.entry("chat", "nom"), Map.entry("manger", "verbe"), Map.entry("rouge", "adjectif"),
            Map.entry("le", "déterminant"), Map.entry("courir", "verbe"), Map.entry("maison", "nom"),
            Map.entry("grand", "adjectif"), Map.entry("une", "déterminant"), Map.entry("chanter", "verbe"),
            Map.entry("joli", "adjectif"), Map.entry("table", "nom"), Map.entry("mon", "déterminant"),
            Map.entry("sauter", "verbe"), Map.entry("petit", "adjectif"), Map.entry("la", "déterminant"),
            Map.entry("fleur", "nom"));

    @Test
    void producesConformingNatureItems() {
        NatureMotGenerator generator = new NatureMotGenerator(
                new ContentDescriptor("Français", "CM1", "classes de mots"));
        RandomSource random = new SeededRandomSource(11L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.reelSymbols()).hasSize(1);
            String mot = item.reelSymbols().get(0);
            assertThat(REFERENCE).containsKey(mot);

            assertThat(item.question()).isEqualTo("Quelle est la nature de ce mot ?");

            // Regle : expected = nature verifiee du mot.
            assertThat(item.expected().value()).isEqualTo(REFERENCE.get(mot));

            // 2 distracteurs : natures valides, distinctes, != reponse.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotHaveDuplicates();
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (Answer d : item.distractors()) {
                assertThat(NATURES).contains(d.value());
            }
        }
    }
}
