package com.recreo.domain.content;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

/**
 * Produit du contenu ({@link Item}) a la demande, pilote par des regles. La
 * mecanique de jeu consomme UNIQUEMENT cette abstraction : elle ne connait ni
 * "addition" ni "calcul mental".
 */
public interface ContentGenerator {

    /**
     * Tire un item en EXCLUANT les items dont la {@link Item#key()} figure dans
     * {@code excludeKeys} (cf. ADR-0006). Un generateur a liste finie renvoie
     * {@link Optional#empty()} quand tout son vivier est exclu (signal d'epuisement) ;
     * un generateur combinatoire fait de l'exclusion "best-effort" et renvoie
     * toujours un item.
     */
    Optional<Item> next(RandomSource random, Set<String> excludeKeys);

    /**
     * Tirage sans exclusion (comportement historique) : delegue avec un ensemble
     * vide. Un vivier non filtre n'est jamais vide, d'ou {@code orElseThrow}.
     */
    default Item next(RandomSource random) {
        return next(random, Set.of())
                .orElseThrow(() -> new IllegalStateException("Generateur epuise des le premier item"));
    }

    /**
     * Taille du vivier distinct pour les generateurs a liste finie (permet au
     * bootstrap de borner "nombre de questions"). Vide pour les generateurs
     * combinatoires. La mecanique ne l'utilise pas.
     */
    default OptionalInt distinctPoolSize() {
        return OptionalInt.empty();
    }

    ContentDescriptor descriptor();
}
