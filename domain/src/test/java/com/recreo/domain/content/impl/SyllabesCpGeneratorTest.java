package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.SyllabesCpGenerator.Mot;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire déterministe du générateur « dénombrer les syllabes ». Le contenu réel
 * vit désormais dans un fichier YAML éditable (ADR-0011) ; ce test vérifie la MÉCANIQUE
 * avec une liste EN LIGNE (indépendante du contenu livré) : items conformes, distracteurs
 * plausibles ≥ 1, filtre maxSyllabes.
 */
class SyllabesCpGeneratorTest {

    private static final int SAMPLES = 500;

    private static final ContentDescriptor DESC =
            new ContentDescriptor("Français", "CP", "Phonologie — dénombrer les syllabes");

    /** Fixture en ligne (mot → syllabes), volontairement découplée du contenu livré. */
    private static final List<Mot> MOTS = List.of(
            new Mot("chat", 1), new Mot("loup", 1),
            new Mot("papa", 2), new Mot("lapin", 2), new Mot("maison", 2),
            new Mot("chocolat", 3), new Mot("éléphant", 3),
            new Mot("ordinateur", 4));

    private static int syllabesOf(String mot) {
        return MOTS.stream().filter(m -> m.mot().equals(mot)).findFirst().orElseThrow().syllabes();
    }

    @Test
    void producesConformingSyllableItems() {
        SyllabesCpGenerator generator = new SyllabesCpGenerator(DESC, MOTS);
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.reelSymbols()).hasSize(1);
            String affiche = item.reelSymbols().get(0);
            assertThat(affiche).isNotBlank();
            assertThat(affiche).isEqualTo(affiche.toUpperCase(Locale.FRENCH));
            assertThat(item.question()).isEqualTo("Combien de syllabes ?");

            String mot = item.meta().get("mot");
            assertThat(affiche).isEqualTo(mot.toUpperCase(Locale.FRENCH));
            assertThat(Integer.parseInt(item.expected().value())).isEqualTo(syllabesOf(mot));

            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotHaveDuplicates();
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (Answer d : item.distractors()) {
                assertThat(Integer.parseInt(d.value())).isGreaterThanOrEqualTo(1);
            }
        }
    }

    @Test
    void oneSyllableWordsNeverGetDistractorBelowOne() {
        SyllabesCpGenerator generator = new SyllabesCpGenerator(DESC, MOTS);
        RandomSource random = new SeededRandomSource(1L);

        boolean sawOne = false;
        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);
            if ("1".equals(item.expected().value())) {
                sawOne = true;
                for (Answer d : item.distractors()) {
                    assertThat(Integer.parseInt(d.value())).isGreaterThanOrEqualTo(1);
                }
            }
        }
        assertThat(sawOne).as("des mots d'une syllabe doivent être tirés").isTrue();
    }

    @Test
    void maxSyllabesFilterDrawsOnlyConformingWords() {
        SyllabesCpGenerator generator = new SyllabesCpGenerator(DESC, MOTS, 2);
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            int syllabes = Integer.parseInt(generator.next(random).expected().value());
            assertThat(syllabes).isLessThanOrEqualTo(2);
        }
    }

    @Test
    void defaultBehaviourDrawsWordsBeyondTwoSyllables() {
        SyllabesCpGenerator generator = new SyllabesCpGenerator(DESC, MOTS);
        RandomSource random = new SeededRandomSource(42L);

        boolean sawLongWord = false;
        for (int i = 0; i < SAMPLES; i++) {
            if (Integer.parseInt(generator.next(random).expected().value()) >= 3) {
                sawLongWord = true;
                break;
            }
        }
        assertThat(sawLongWord).as("le comportement par défaut ne filtre pas").isTrue();
    }
}
