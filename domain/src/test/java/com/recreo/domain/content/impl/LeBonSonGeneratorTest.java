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

/**
 * Tests déterministes de "Le bon son" (Français CP, décodage graphème/phonème). Vérifie
 * que chaque item produit est conforme : le mot est affiché sur le rouleau
 * ({@code reelSymbols = [MOT]}), la consigne est fixe, la bonne réponse est le son
 * attendu du mot, et les distracteurs sont exactement les deux sons prévus (distincts,
 * sans le bon son). Vérifie aussi le mode session (anti-doublon + épuisement).
 */
class LeBonSonGeneratorTest {

    private static final String QUESTION = "Quel son entends-tu ?";
    private static final int SAMPLES = 400;

    // Référence : MOT -> [bon son, distracteur, distracteur].
    private static final Map<String, String[]> REFERENCE = Map.ofEntries(
            Map.entry("MAMAN", new String[] {"[an]", "[on]", "[in]"}),
            Map.entry("DENT", new String[] {"[an]", "[oi]", "[ou]"}),
            Map.entry("PONT", new String[] {"[on]", "[an]", "[ou]"}),
            Map.entry("BALLON", new String[] {"[on]", "[in]", "[oi]"}),
            Map.entry("LAPIN", new String[] {"[in]", "[an]", "[on]"}),
            Map.entry("JARDIN", new String[] {"[in]", "[ou]", "[oi]"}),
            Map.entry("LOUP", new String[] {"[ou]", "[on]", "[oi]"}),
            Map.entry("GENOU", new String[] {"[ou]", "[oi]", "[an]"}),
            Map.entry("ROI", new String[] {"[oi]", "[ou]", "[on]"}),
            Map.entry("BOIS", new String[] {"[oi]", "[ou]", "[in]"}),
            Map.entry("CHAT", new String[] {"[ch]", "[oi]", "[ou]"}),
            Map.entry("VACHE", new String[] {"[ch]", "[an]", "[on]"}));

    @Test
    void producesConformingItems() {
        LeBonSonGenerator generator =
                new LeBonSonGenerator(new ContentDescriptor("Français", "CP", "Décodage"));
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Le mot est sur le rouleau ; la consigne est fixe.
            assertThat(item.reelSymbols()).hasSize(1);
            String mot = item.reelSymbols().get(0);
            assertThat(mot).as("mot connu").isIn(REFERENCE.keySet());
            assertThat(item.question()).isEqualTo(QUESTION);

            String[] expected = REFERENCE.get(mot);
            String bonSon = expected[0];
            assertThat(item.expected().value()).isEqualTo(bonSon);

            List<String> distractors = item.distractors().stream().map(Answer::value).toList();
            assertThat(distractors).hasSize(2);
            assertThat(distractors).doesNotHaveDuplicates();
            assertThat(distractors).doesNotContain(bonSon);
            assertThat(distractors).containsExactlyInAnyOrder(expected[1], expected[2]);
        }
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        LeBonSonGenerator generator =
                new LeBonSonGenerator(new ContentDescriptor("Français", "CP", "Décodage"));
        RandomSource random = new SeededRandomSource(7L);

        Set<String> mastered = new LinkedHashSet<>();
        int pool = REFERENCE.size();
        for (int i = 0; i < pool; i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un item doit rester (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).as("aucun item réussi ne revient").doesNotContain(key);
            mastered.add(key);
        }
        assertThat(mastered).hasSize(pool);
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void distinctPoolSizeMatchesTheVerifiedList() {
        assertThat(new LeBonSonGenerator(new ContentDescriptor("Français", "CP", "Décodage"))
                .distinctPoolSize()).hasValue(REFERENCE.size());
    }
}
