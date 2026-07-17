package com.recreo.application.port.in;

/** Commande : demarrer une session pour une entree de catalogue donnee. */
public record StartSessionCommand(String catalogEntryId) {
}
