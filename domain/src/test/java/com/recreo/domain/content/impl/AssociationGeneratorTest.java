package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.AssociationGenerator.Association;
import com.recreo.domain.content.impl.AssociationGenerator.Puzzle;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire du generateur "associer / classer" (cf. ADR-0016) : forme de l'item
 * (elements dans l'ordre du contenu, cibles distinctes en meta, appariement attendu encode
 * trie), taille de vivier et anti-doublon en mode session. Contenu en ligne, deterministe.
 */
class AssociationGeneratorTest {

    private static final ContentDescriptor DESCRIPTOR =
            new ContentDescriptor("Sciences", "CP", "classer");
    private static final String CONSIGNE = "Range chaque animal dans sa famille";

    private static final Puzzle P1 = new Puzzle(List.of(
            new Association("chien", "mammifère"),
            new Association("aigle", "oiseau")));
    private static final Puzzle P2 = new Puzzle(List.of(
            new Association("saumon", "poisson"),
            new Association("truite", "poisson"))); // categorisation : cible repetee

    private static AssociationGenerator generator(Puzzle... puzzles) {
        return new AssociationGenerator(DESCRIPTOR, CONSIGNE, List.of(puzzles));
    }

    @Test
    void producesItemWithSymbolsInContentOrderTargetsAndEncodedExpected() {
        RandomSource random = new SeededRandomSource(42L);
        Item item = generator(P1).next(random);

        // reelSymbols = les elements du puzzle, DANS L'ORDRE du contenu.
        assertThat(item.reelSymbols()).containsExactly("chien", "aigle");
        assertThat(item.question()).isEqualTo(CONSIGNE);

        // meta["targets"] = les cibles DISTINCTES (ordre melange -> comparaison ensembliste).
        assertThat(item.meta().get(AssociationGenerator.META_TARGETS).split("\\|"))
                .containsExactlyInAnyOrder("mammifère", "oiseau");

        // expected = appariement item->cible encode canoniquement (paires TRIEES par cle).
        assertThat(item.expected().value()).isEqualTo("aigle=oiseau, chien=mammifère");
    }

    @Test
    void categorizationSharesARepeatedTargetOnlyOnce() {
        RandomSource random = new SeededRandomSource(1L);
        Item item = generator(P2).next(random);

        // Cible "poisson" repetee dans le contenu -> une SEULE zone-cible distincte.
        assertThat(item.meta().get(AssociationGenerator.META_TARGETS).split("\\|"))
                .containsExactly("poisson");
        assertThat(item.expected().value()).isEqualTo("saumon=poisson, truite=poisson");
    }

    @Test
    void distinctPoolSizeMatchesTheNumberOfPuzzles() {
        assertThat(generator(P1, P2).distinctPoolSize()).hasValue(2);
    }

    @Test
    void excludingAPuzzleKeyRemovesItAndExhaustionSignalsEmpty() {
        AssociationGenerator generator = generator(P1, P2);
        RandomSource random = new SeededRandomSource(3L);

        String key1 = Item.keyOf(List.of("chien", "aigle"), CONSIGNE);
        String key2 = Item.keyOf(List.of("saumon", "truite"), CONSIGNE);

        // Exclure la cle de P1 le retire : seul P2 peut encore etre tire.
        Optional<Item> remaining = generator.next(random, Set.of(key1));
        assertThat(remaining).isPresent();
        assertThat(remaining.get().reelSymbols()).containsExactly("saumon", "truite");

        // Tout exclu -> vivier epuise.
        assertThat(generator.next(random, Set.of(key1, key2))).isEmpty();
    }
}
