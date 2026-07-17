package com.recreo.domain.mechanic;

/** Resultat de l'application d'un coup : la session mise a jour et le retour. */
public record MoveResult(GameSession session, boolean correct, Feedback feedback) {
}
