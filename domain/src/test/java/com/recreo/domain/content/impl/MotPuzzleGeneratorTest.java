package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.MotPuzzleGenerator.Mot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Générateur « Reconstitue le mot » (mécanique {@code puzzle}, cf. ADR-0017). Vérifie la
 * MÉCANIQUE de tirage indépendamment du contenu (pool en ligne) : les tuiles affichées sont
 * les lettres TRIÉES, la réponse attendue est l'arrangement des lettres DANS L'ORDRE du mot,
 * l'indice est porté par {@code meta["indice"]}, la taille du vivier vaut le nombre de mots,
 * et l'anti-doublon épuise le vivier une fois toutes les clés exclues.
 */
class MotPuzzleGeneratorTest {

    private static final int SAMPLES = 300;

    private static final String CONSIGNE = "Remets les lettres dans l'ordre";

    /** Pool en ligne SANS anagramme (lettres triees distinctes -> cles stables distinctes). */
    private static final List<Mot> POOL = List.of(
            new Mot("chat", "🐱"),
            new Mot("lune", "🌙"));

    private MotPuzzleGenerator generator() {
        return new MotPuzzleGenerator(
                new ContentDescriptor("Français", "CP", "lecture"), CONSIGNE, POOL);
    }

    /** Les lettres d'un mot, dans l'ordre. */
    private static List<String> lettres(String mot) {
        List<String> lettres = new ArrayList<>();
        for (int i = 0; i < mot.length(); i++) {
            lettres.add(String.valueOf(mot.charAt(i)));
        }
        return lettres;
    }

    /** Les lettres d'un mot, TRIEES (ce que le generateur affiche sur les tuiles). */
    private static List<String> lettresTriees(String mot) {
        List<String> lettres = lettres(mot);
        Collections.sort(lettres);
        return lettres;
    }

    @Test
    void producesConformingItems() {
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator().next(random);

            // Identifie le mot tire via ses lettres triees (les tuiles affichees).
            Mot ref = POOL.stream()
                    .filter(m -> item.reelSymbols().equals(lettresTriees(m.mot())))
                    .findFirst().orElseThrow();

            // Tuiles = lettres TRIEES (ne revele pas l'ordre du mot).
            assertThat(item.reelSymbols()).isEqualTo(lettresTriees(ref.mot()));

            // Attendu = l'arrangement des lettres DANS L'ORDRE du mot, encode canoniquement.
            assertThat(item.expected().value())
                    .isEqualTo(Item.encodeArrangement(lettres(ref.mot())));

            // Consigne + indice.
            assertThat(item.question()).isEqualTo(CONSIGNE);
            assertThat(item.meta().get("indice")).isEqualTo(ref.indice());

            // Pas de distracteurs (arrangement pur).
            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        MotPuzzleGenerator generator = generator();
        RandomSource random = new SeededRandomSource(3L);

        Set<String> mastered = new LinkedHashSet<>();
        for (int i = 0; i < POOL.size(); i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("un mot doit rester (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).doesNotContain(key);
            mastered.add(key);
        }
        // Vivier epuise : toutes les cles exclues -> plus aucun mot.
        assertThat(mastered).hasSize(POOL.size());
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void distinctPoolSizeMatchesTheNumberOfWords() {
        assertThat(generator().distinctPoolSize()).hasValue(POOL.size());
    }
}
