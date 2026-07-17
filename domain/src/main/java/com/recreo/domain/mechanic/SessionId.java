package com.recreo.domain.mechanic;

/** Identifiant d'une session de jeu. */
public record SessionId(String value) {
    public SessionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("value must not be blank");
        }
    }
}
