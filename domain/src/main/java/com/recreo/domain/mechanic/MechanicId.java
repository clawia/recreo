package com.recreo.domain.mechanic;

/** Identifiant d'une mecanique de jeu (ex. "bandit-manchot"). */
public record MechanicId(String value) {
    public MechanicId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("value must not be blank");
        }
    }
}
