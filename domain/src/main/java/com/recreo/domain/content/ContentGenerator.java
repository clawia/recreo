package com.recreo.domain.content;

/**
 * Produit du contenu ({@link Item}) a la demande, pilote par des regles. La
 * mecanique de jeu consomme UNIQUEMENT cette abstraction : elle ne connait ni
 * "addition" ni "calcul mental".
 */
public interface ContentGenerator {
    Item next(RandomSource random);

    ContentDescriptor descriptor();
}
