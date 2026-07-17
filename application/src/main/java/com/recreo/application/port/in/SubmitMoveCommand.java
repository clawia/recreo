package com.recreo.application.port.in;

/** Commande : appliquer un coup (reponse choisie) sur une session existante. */
public record SubmitMoveCommand(String sessionId, String chosenAnswer) {
}
