package com.recreo.domain.mechanic;

import java.util.List;

/**
 * Coup « glisser-déposer » : l'enfant soumet un ARRANGEMENT ordonné d'éléments (l'ordre
 * dans lequel il a rangé les tuiles), et non une réponse unique. Le moteur de session
 * ({@link SessionEngine}) reste agnostique : il compare l'encodage canonique de cet ordre
 * (cf. {@link com.recreo.domain.content.Item#encodeArrangement(List)}) à la réponse
 * attendue de l'item — il ne trie rien et n'interprète pas le contenu.
 */
public record SubmitArrangement(List<String> order) implements Move {
    public SubmitArrangement {
        if (order == null || order.isEmpty()) {
            throw new IllegalArgumentException("order must not be empty");
        }
        order = List.copyOf(order);
    }
}
