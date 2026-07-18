package com.recreo.adapter.web.view;

import java.util.List;

/**
 * View-model dedie au fragment de jeu. Ne reprend jamais les records du domaine :
 * uniquement des types prets a afficher par Thymeleaf.
 */
public record GameView(
        String sessionId,
        List<String> reelSymbols,
        String question,
        List<String> options,
        int score,
        int round,
        boolean finished,
        boolean hasFeedback,
        boolean correct,
        String feedbackMessage) {

    public static GameView initial(String sessionId, List<String> reelSymbols, String question,
                                   List<String> options, int score, int round, boolean finished) {
        return new GameView(sessionId, reelSymbols, question, options, score, round, finished, false, false, null);
    }

    public static GameView withFeedback(String sessionId, boolean correct, String feedbackMessage,
                                        List<String> reelSymbols, String question, List<String> options,
                                        int score, int round, boolean finished) {
        return new GameView(sessionId, reelSymbols, question, options, score, round, finished, true, correct,
                feedbackMessage);
    }
}
