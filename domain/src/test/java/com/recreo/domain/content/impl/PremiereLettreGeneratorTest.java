package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** Test deterministe : "La premiere lettre" (Francais CP). */
class PremiereLettreGeneratorTest {

    private static final int SAMPLES = 500;

    private static final List<String> MOTS = List.of(
            "BATEAU", "MAISON", "CHAT", "POMME", "LAPIN", "SOLEIL",
            "TABLE", "RENARD", "VELO", "FLEUR", "ROBOT", "CANARD");

    @Test
    void producesConformingFirstLetterItems() {
        PremiereLettreGenerator generator = new PremiereLettreGenerator(
                new ContentDescriptor("Français", "CP", "principe alphabétique"));
        RandomSource random = new SeededRandomSource(3L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator.next(random);

            assertThat(item.reelSymbols()).hasSize(1);
            String mot = item.reelSymbols().get(0);
            assertThat(MOTS).contains(mot);

            assertThat(item.question()).isEqualTo("Par quelle lettre commence ce mot ?");

            // Regle : expected = premiere lettre du mot.
            String expected = item.expected().value();
            assertThat(expected).isEqualTo(mot.substring(0, 1));

            // 2 distracteurs : lettres majuscules A..Z, distinctes, != reponse.
            assertThat(item.distractors()).hasSize(2);
            assertThat(item.distractors()).doesNotHaveDuplicates();
            assertThat(item.distractors()).doesNotContain(item.expected());
            for (Answer d : item.distractors()) {
                assertThat(d.value()).matches("[A-Z]");
            }
        }
    }
}
