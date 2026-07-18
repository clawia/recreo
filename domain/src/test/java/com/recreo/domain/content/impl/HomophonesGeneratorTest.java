package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "Le bon petit mot" (Francais CM1, homophones). */
class HomophonesGeneratorTest {

    private static final int SAMPLES = 500;

    /** Reference phrase -> (bon, mauvais). */
    private static final Map<String, String[]> REFERENCE = Map.ofEntries(
            Map.entry("Il ___ mangé une pomme.", new String[] {"a", "à"}),
            Map.entry("Je vais ___ l'école.", new String[] {"à", "a"}),
            Map.entry("Papa ___ maman font la cuisine.", new String[] {"et", "est"}),
            Map.entry("Le chat ___ noir.", new String[] {"est", "et"}),
            Map.entry("Les enfants ___ partis.", new String[] {"sont", "son"}),
            Map.entry("Il prend ___ cartable.", new String[] {"son", "sont"}),
            Map.entry("Ils ___ un chien.", new String[] {"ont", "on"}),
            Map.entry("___ joue dans la cour.", new String[] {"On", "Ont"}),
            Map.entry("___ vas-tu ?", new String[] {"Où", "Ou"}),
            Map.entry("Tu veux du thé ___ du café ?", new String[] {"ou", "où"}));

    @Test
    void producesConformingHomophoneItems() {
        HomophonesGenerator generator = new HomophonesGenerator(
                new ContentDescriptor("Français", "CM1", "homophones grammaticaux"));
        RandomSource random = new SeededRandomSource(9L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.reelSymbols()).hasSize(1);
            String phrase = item.reelSymbols().get(0);
            assertThat(REFERENCE).containsKey(phrase);

            assertThat(item.question()).isEqualTo("Quel mot complète la phrase ?");

            // Regle : expected = bon homophone ; distracteur = piege.
            String[] pair = REFERENCE.get(phrase);
            assertThat(item.expected().value()).isEqualTo(pair[0]);
            assertThat(item.distractors()).hasSize(1);
            assertThat(item.distractors().get(0).value()).isEqualTo(pair[1]);
            assertThat(item.distractors()).doesNotContain(item.expected());
        }
    }
}
