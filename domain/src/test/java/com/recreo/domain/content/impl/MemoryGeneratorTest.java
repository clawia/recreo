package com.recreo.domain.content.impl;

import com.recreo.domain.SeededRandomSource;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.impl.MemoryGenerator.Paire;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test unitaire du generateur "memory" (cf. ADR-0016) : le contenu decrit des paires ;
 * le generateur produit UN plateau (un seul item, {@code distinctPoolSize = 1}). On verifie
 * l'ordre des faces {@code g0,d0,g1,d1,...}, le meta "groups" PARALLELE, l'appariement
 * attendu encode, et l'epuisement quand le plateau est exclu. Contenu en ligne, deterministe.
 */
class MemoryGeneratorTest {

    private static final ContentDescriptor DESCRIPTOR =
            new ContentDescriptor("Anglais", "CP", "vocabulaire");
    private static final String CONSIGNE = "Retrouve les paires";

    private static final List<Paire> PAIRES = List.of(
            new Paire("chat", "cat"),
            new Paire("chien", "dog"));

    private static MemoryGenerator generator() {
        return new MemoryGenerator(DESCRIPTOR, CONSIGNE, PAIRES);
    }

    @Test
    void producesABoardWithCardsAndParallelGroupsAndEncodedExpected() {
        RandomSource random = new SeededRandomSource(42L);
        Item board = generator().next(random);

        // reelSymbols = les faces dans l'ordre g0,d0,g1,d1.
        assertThat(board.reelSymbols()).containsExactly("chat", "cat", "chien", "dog");
        assertThat(board.question()).isEqualTo(CONSIGNE);

        // meta["groups"] = identifiant de paire + cote, PARALLELE aux faces.
        assertThat(board.meta().get(MemoryGenerator.META_GROUPS)).isEqualTo("0g|0d|1g|1d");

        // expected = appariement gauche->droite encode canoniquement (paires TRIEES par cle).
        assertThat(board.expected().value()).isEqualTo("chat=cat, chien=dog");
    }

    @Test
    void distinctPoolSizeIsAlwaysOne() {
        // Un seul plateau par jeu (toutes les paires) : borne la cible de session a 1.
        assertThat(generator().distinctPoolSize()).hasValue(1);
    }

    @Test
    void excludingTheBoardKeySignalsExhaustion() {
        MemoryGenerator generator = generator();
        RandomSource random = new SeededRandomSource(3L);

        // La cle du plateau = les faces (dans l'ordre) + la consigne.
        String boardKey = Item.keyOf(List.of("chat", "cat", "chien", "dog"), CONSIGNE);

        // Plateau deja reussi -> vivier epuise (pas de second plateau).
        assertThat(generator.next(random, Set.of(boardKey))).isEmpty();
    }
}
