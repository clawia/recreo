package com.recreo.application.port.in;

import java.util.Map;

/**
 * Commande : demarrer une session pour une entree de catalogue donnee, avec
 * d'eventuels reglages de session (regles par un adulte, appliques a la session
 * courante, non stockes durablement — cf. ADR-0005). {@code parameters} vide =
 * comportement par defaut (identique a s02).
 */
public record StartSessionCommand(String catalogEntryId, Map<String, String> parameters) {

    public StartSessionCommand {
        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
    }

    /** Demarrage sans reglage : valeurs par defaut de l'entree. */
    public StartSessionCommand(String catalogEntryId) {
        this(catalogEntryId, Map.of());
    }
}
