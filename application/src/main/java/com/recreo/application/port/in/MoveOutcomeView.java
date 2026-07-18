package com.recreo.application.port.in;

import java.util.List;

/**
 * Vue de sortie d'un coup : le resultat (correct + message) et l'etat du round
 * suivant, pret a etre re-affiche.
 */
public record MoveOutcomeView(
        String sessionId,
        boolean correct,
        String feedbackMessage,
        List<String> reelSymbols,
        String question,
        List<String> options,
        int score,
        int round,
        String status) {
}
