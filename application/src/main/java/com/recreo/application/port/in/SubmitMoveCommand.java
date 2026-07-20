package com.recreo.application.port.in;

/** Commande : appliquer un coup (reponse choisie ou arrangement) sur une session existante. */
public record SubmitMoveCommand(String sessionId, String chosenAnswer, java.util.List<String> arrangement) {

    /** Coup classique : une reponse choisie (spin & pick). */
    public static SubmitMoveCommand pick(String sessionId, String chosenAnswer) {
        return new SubmitMoveCommand(sessionId, chosenAnswer, null);
    }

    /** Coup d'arrangement : un ordre propose (glisser-deposer). */
    public static SubmitMoveCommand arrangement(String sessionId, java.util.List<String> order) {
        return new SubmitMoveCommand(sessionId, null, order);
    }
}
