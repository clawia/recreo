package com.recreo.application.port.in;

import java.util.List;
import java.util.Map;

/**
 * Vue de sortie d'un coup : le resultat (correct + message) et l'etat du round
 * suivant, pret a etre re-affiche.
 *
 * <p>{@code jokers} = coeurs restants (cf. DEC-0009) ; {@code triedWrong} = reponses
 * deja tentees a tort sur l'item courant (a griser sur les jeux a clic) ; {@code meta}
 * = meta-donnees de l'item courant (cibles/paires associer-classer et memory, ADR-0016).</p>
 */
public record MoveOutcomeView(
        String sessionId,
        String mechanicView,
        String catalogEntryId,
        boolean correct,
        String feedbackMessage,
        List<String> reelSymbols,
        String question,
        List<String> options,
        int score,
        int round,
        int answered,
        int questionsTarget,
        int jokers,
        List<String> triedWrong,
        Map<String, String> meta,
        String status) {
}
