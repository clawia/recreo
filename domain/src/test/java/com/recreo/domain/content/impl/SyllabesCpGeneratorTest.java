package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire deterministe du generateur francais CP (denombrer les syllabes) :
 * items conformes (mot de la liste, expected = bon nombre, distracteurs plausibles
 * >= 1, reelSymbols/question remplis).
 */
class SyllabesCpGeneratorTest {

    private static final int SAMPLES = 500;

    /** Liste de reference (mot -> syllabes orales), miroir de celle du generateur. */
    private static final Map<String, Integer> REFERENCE = Map.ofEntries(
            Map.entry("chat", 1), Map.entry("riz", 1), Map.entry("loup", 1),
            Map.entry("papa", 2), Map.entry("lapin", 2), Map.entry("bateau", 2),
            Map.entry("cadeau", 2), Map.entry("maison", 2),
            Map.entry("chocolat", 3), Map.entry("éléphant", 3), Map.entry("parapluie", 3),
            Map.entry("dinosaure", 3),
            Map.entry("ordinateur", 4), Map.entry("hélicoptère", 4));

    @Test
    void producesConformingSyllableItems() {
        SyllabesCpGenerator generator = new SyllabesCpGenerator(
                new ContentDescriptor("Français", "CP", "Phonologie — dénombrer les syllabes"));
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            // Un seul rouleau : le mot en majuscules, non vide.
            assertThat(item.reelSymbols()).hasSize(1);
            String affiche = item.reelSymbols().get(0);
            assertThat(affiche).isNotBlank();
            assertThat(affiche).isEqualTo(affiche.toUpperCase(Locale.FRENCH));

            // Consigne remplie.
            assertThat(item.question()).isEqualTo("Combien de syllabes ?");

            // Le mot appartient a la liste verifiee et expected = bon nombre.
            String mot = item.meta().get("mot");
            assertThat(REFERENCE).containsKey(mot);
            assertThat(affiche).isEqualTo(mot.toUpperCase(Locale.FRENCH));
            int expected = Integer.parseInt(item.expected().value());
            assertThat(expected).isEqualTo(REFERENCE.get(mot));

            // Distracteurs : 2, plausibles (>= 1), sans doublon, != reponse.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotHaveDuplicates();
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (Answer d : item.distractors()) {
                assertThat(Integer.parseInt(d.value())).isGreaterThanOrEqualTo(1);
            }
        }
    }

    @Test
    void expectedIsAmongPlayableOptionsWithMinimumOne() {
        // expected = 1 : verifie qu'aucun distracteur ne descend sous 1.
        SyllabesCpGenerator generator = new SyllabesCpGenerator(
                new ContentDescriptor("Français", "CP", "Phonologie — dénombrer les syllabes"));
        RandomSource random = new SeededRandomSource(1L);

        boolean sawOneSyllable = false;
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);
            if ("1".equals(item.expected().value())) {
                sawOneSyllable = true;
                for (Answer d : item.distractors()) {
                    assertThat(Integer.parseInt(d.value())).isGreaterThanOrEqualTo(1);
                }
            }
        }
        assertThat(sawOneSyllable).as("des mots d'une syllabe doivent etre tires").isTrue();
    }
}
