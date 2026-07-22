package com.recreo.application.port.in;

import java.util.List;
import java.util.Map;

/**
 * Vue de sortie d'un use case decrivant l'etat jouable d'une session, en types
 * primitifs (aucun record du domaine n'est expose au-dela de l'application).
 *
 * <p>{@code jokers} = coeurs restants (cf. DEC-0009) ; {@code triedWrong} = reponses
 * deja tentees a tort sur l'item courant (a griser sur les jeux a clic) ; {@code meta}
 * = meta-donnees de l'item courant (passthrough : porte notamment les cibles/paires des
 * mecaniques associer-classer et memory, cf. ADR-0016).</p>
 */
public record SessionView(
        String sessionId,
        String mechanicId,
        String mechanicView,
        String catalogEntryId,
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
