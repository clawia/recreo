package com.recreo.domain.mechanic;

import com.recreo.domain.content.Answer;

/** Coup : l'enfant lance les rouleaux et choisit une reponse. */
public record SpinAndPick(Answer chosen) implements Move {
    public SpinAndPick {
        if (chosen == null) {
            throw new IllegalArgumentException("chosen must not be null");
        }
    }
}
