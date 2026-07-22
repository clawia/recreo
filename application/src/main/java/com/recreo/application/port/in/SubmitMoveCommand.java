package com.recreo.application.port.in;

import java.util.List;
import java.util.Map;

/** Commande : appliquer un coup (reponse choisie, arrangement ou associations) sur une session. */
public record SubmitMoveCommand(String sessionId, String chosenAnswer, List<String> arrangement,
                                Map<String, String> associations) {

    /** Coup classique : une reponse choisie (spin & pick). */
    public static SubmitMoveCommand pick(String sessionId, String chosenAnswer) {
        return new SubmitMoveCommand(sessionId, chosenAnswer, null, null);
    }

    /** Coup d'arrangement : un ordre propose (glisser-deposer). */
    public static SubmitMoveCommand arrangement(String sessionId, List<String> order) {
        return new SubmitMoveCommand(sessionId, null, order, null);
    }

    /** Coup d'associations : un appariement cle->valeur (associer/classer, memory). */
    public static SubmitMoveCommand associations(String sessionId, Map<String, String> associations) {
        return new SubmitMoveCommand(sessionId, null, null, associations);
    }
}
