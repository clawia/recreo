package com.recreo.adapter.persistence.repository;

import com.recreo.domain.content.Item;
import com.recreo.domain.mechanic.Reel;

import java.util.List;
import java.util.Map;

/**
 * Partie "riche" de l'etat de session serialisee en JSON : l'item courant, les
 * rouleaux et les reglages de session (necessaires pour reconstruire le generateur
 * parametre a chaque coup — cf. ADR-0005).
 */
record PersistedState(Item currentItem, List<Reel> reels, Map<String, String> parameters) {

    PersistedState {
        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
    }
}
