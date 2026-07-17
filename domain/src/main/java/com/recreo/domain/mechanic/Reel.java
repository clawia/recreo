package com.recreo.domain.mechanic;

import com.recreo.domain.content.Answer;

import java.util.List;

/**
 * Un rouleau du bandit manchot : la liste des symboles (reponses) affiches et
 * selectionnables par l'enfant.
 */
public record Reel(List<Answer> symbols) {
    public Reel {
        symbols = List.copyOf(symbols);
    }
}
