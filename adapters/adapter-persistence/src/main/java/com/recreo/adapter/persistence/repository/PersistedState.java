package com.recreo.adapter.persistence.repository;

import com.recreo.domain.content.Item;
import com.recreo.domain.mechanic.Reel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Partie "riche" de l'etat de session serialisee en JSON : l'item courant, les
 * rouleaux, les reglages de session (necessaires pour reconstruire le generateur
 * parametre a chaque coup — cf. ADR-0005) et l'etat du mode session
 * ({@code questionsTarget}, {@code answered}, {@code masteredKeys} — cf. ADR-0006,
 * necessaire pour que l'exclusion des items reussis tienne d'un coup au suivant).
 */
record PersistedState(Item currentItem, List<Reel> reels, Map<String, String> parameters,
                      int questionsTarget, int answered, Set<String> masteredKeys) {

    PersistedState {
        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
        masteredKeys = masteredKeys == null ? Set.of() : Set.copyOf(masteredKeys);
    }
}
