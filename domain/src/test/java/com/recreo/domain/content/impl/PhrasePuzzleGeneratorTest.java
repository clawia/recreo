package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.PhrasePuzzleGenerator.Phrase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Générateur « Reconstitue la phrase » (mécanique {@code puzzle}, cf. ADR-0017) — déclinaison
 * de {@link MotPuzzleGenerator} où l'unité rangée n'est plus la LETTRE mais le MOT. Vérifie la
 * MÉCANIQUE de tirage indépendamment du contenu : les tuiles affichées sont les mots TRIÉS, la
 * réponse attendue est l'arrangement des mots DANS L'ORDRE de la phrase, l'indice (facultatif)
 * est porté par {@code meta["indice"]}, la taille du vivier vaut le nombre de phrases, et
 * l'anti-doublon épuise le vivier une fois toutes les clés exclues. Les mots en double sont
 * gérés naturellement (deux tuiles identiques, validation position par position).
 */
class PhrasePuzzleGeneratorTest {

    private static final int SAMPLES = 300;

    private static final String CONSIGNE = "Remets les mots dans l'ordre";

    /** Pool en ligne : jeux de mots triés distincts -> clés stables distinctes. */
    private static final List<Phrase> POOL = List.of(
            new Phrase("le chat dort", "🐱"),
            new Phrase("papa lit un livre", "📖"));

    private PhrasePuzzleGenerator generator() {
        return new PhrasePuzzleGenerator(
                new ContentDescriptor("Français", "CP", "grammaire"), CONSIGNE, POOL);
    }

    /** Les mots d'une phrase, dans l'ordre. */
    private static List<String> mots(String phrase) {
        return new ArrayList<>(List.of(phrase.trim().split("\\s+")));
    }

    /** Les mots d'une phrase, TRIÉS (ce que le générateur affiche sur les tuiles). */
    private static List<String> motsTries(String phrase) {
        List<String> mots = mots(phrase);
        Collections.sort(mots);
        return mots;
    }

    @Test
    void producesConformingItems() {
        RandomSource random = new SeededRandomSource(42L);

        for (int i = 0; i < SAMPLES; i++) {
            Item item = generator().next(random);

            // Identifie la phrase tirée via ses mots triés (les tuiles affichées).
            Phrase ref = POOL.stream()
                    .filter(p -> item.reelSymbols().equals(motsTries(p.phrase())))
                    .findFirst().orElseThrow();

            // Tuiles = mots TRIÉS (ne révèle pas l'ordre de la phrase).
            assertThat(item.reelSymbols()).isEqualTo(motsTries(ref.phrase()));

            // Attendu = l'arrangement des mots DANS L'ORDRE de la phrase, encodé canoniquement
            // (mots joints par « , » via Item.encodeArrangement).
            assertThat(item.expected().value())
                    .isEqualTo(Item.encodeArrangement(mots(ref.phrase())));

            // Consigne + indice.
            assertThat(item.question()).isEqualTo(CONSIGNE);
            assertThat(item.meta().get("indice")).isEqualTo(ref.indice());

            // Pas de distracteurs (arrangement pur).
            assertThat(item.distractors()).isEmpty();
        }
    }

    @Test
    void duplicateWordYieldsTwoIdenticalTilesAndStillValidatesByPosition() {
        // « le chat et le chien » : deux tuiles « le » (mots en double). Les tuiles affichées
        // sont les mots TRIÉS (« le » y apparaît deux fois) ; la réponse attendue reste
        // l'arrangement DANS L'ORDRE — ArrangementBoard compare position par position.
        Phrase phrase = new Phrase("le chat et le chien", "");
        PhrasePuzzleGenerator generator = new PhrasePuzzleGenerator(
                new ContentDescriptor("Français", "CP", "grammaire"), CONSIGNE, List.of(phrase));

        Item item = generator.next(new SeededRandomSource(7L));

        // Deux tuiles identiques « le » dans la réserve triée.
        assertThat(item.reelSymbols()).isEqualTo(motsTries(phrase.phrase()));
        assertThat(item.reelSymbols()).filteredOn(w -> w.equals("le")).hasSize(2);

        // L'ordre attendu conserve les deux « le » à leurs positions respectives.
        assertThat(item.expected().value())
                .isEqualTo(Item.encodeArrangement(mots(phrase.phrase())));

        // Indice facultatif absent -> chaîne vide.
        assertThat(item.meta().get("indice")).isEqualTo("");
    }

    @Test
    void sessionNeverRepeatsAMasteredItemThenSignalsExhaustion() {
        PhrasePuzzleGenerator generator = generator();
        RandomSource random = new SeededRandomSource(3L);

        Set<String> mastered = new LinkedHashSet<>();
        for (int i = 0; i < POOL.size(); i++) {
            Optional<Item> item = generator.next(random, mastered);
            assertThat(item).as("une phrase doit rester (tirage %s)", i + 1).isPresent();
            String key = item.get().key();
            assertThat(mastered).doesNotContain(key);
            mastered.add(key);
        }
        // Vivier épuisé : toutes les clés exclues -> plus aucune phrase.
        assertThat(mastered).hasSize(POOL.size());
        assertThat(generator.next(random, mastered)).isEmpty();
    }

    @Test
    void distinctPoolSizeMatchesTheNumberOfPhrases() {
        assertThat(generator().distinctPoolSize()).hasValue(POOL.size());
    }
}
