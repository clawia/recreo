package com.recreo.adapter.persistence.repository;

import com.recreo.domain.content.Item;
import com.recreo.domain.mechanic.Reel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Partie "riche" de l'etat de session serialisee en JSON : l'item courant, les
 * rouleaux, les reglages de session (necessaires pour reconstruire le generateur
 * parametre a chaque coup — cf. ADR-0005), l'etat du mode session
 * ({@code questionsTarget}, {@code answered}, {@code masteredKeys} — cf. ADR-0006,
 * necessaire pour que l'exclusion des items reussis tienne d'un coup au suivant) et
 * l'etat des jokers ({@code jokers}, {@code triedWrong} — cf. DEC-0009 : le capital
 * de coeurs et les options deja grisees doivent survivre d'un coup au suivant, sinon
 * ils seraient reinitialises a chaque clic puisque la session est rechargee du depot).
 */
record PersistedState(Item currentItem, List<Reel> reels, Map<String, String> parameters,
                      int questionsTarget, int answered, Set<String> masteredKeys,
                      int jokers, Set<String> triedWrong) {

    PersistedState {
        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
        masteredKeys = masteredKeys == null ? Set.of() : Set.copyOf(masteredKeys);
        triedWrong = triedWrong == null ? Set.of() : Set.copyOf(triedWrong);
    }
}
