package com.recreo.application.port.in;

import java.util.List;

/**
 * Vue de sortie d'un use case decrivant l'etat jouable d'une session, en types
 * primitifs (aucun record du domaine n'est expose au-dela de l'application).
 */
public record SessionView(
        String sessionId,
        String mechanicId,
        String prompt,
        List<String> options,
        int score,
        int round,
        String status) {
}
