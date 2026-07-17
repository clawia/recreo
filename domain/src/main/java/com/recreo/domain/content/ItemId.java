package com.recreo.domain.content;

/** Identifiant d'un {@link Item} de contenu. */
public record ItemId(String value) {
    public ItemId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("value must not be blank");
        }
    }
}
