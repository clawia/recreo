package com.recreo.adapter.web.view;

import java.util.List;

/**
 * View-model dedie au fragment de jeu. Ne reprend jamais les records du domaine :
 * uniquement des types prets a afficher par Thymeleaf.
 *
 * <p>{@code catalogEntryId} permet au bouton « Rejouer » de l'ecran de fin de
 * relancer une session du meme jeu ; {@code answered} est le nombre de questions
 * jouees (affiche dans le score de fin, cf. ADR-0006).</p>
 */
public record GameView(
        String sessionId,
        String catalogEntryId,
        List<String> reelSymbols,
        String question,
        List<String> options,
        int score,
        int round,
        int answered,
        int questionsTarget,
        boolean finished,
        boolean hasFeedback,
        boolean correct,
        String feedbackMessage) {

    public static GameView initial(String sessionId, String catalogEntryId, List<String> reelSymbols,
                                   String question, List<String> options, int score, int round,
                                   int answered, int questionsTarget, boolean finished) {
        return new GameView(sessionId, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, false, false, null);
    }

    public static GameView withFeedback(String sessionId, String catalogEntryId, boolean correct,
                                        String feedbackMessage, List<String> reelSymbols, String question,
                                        List<String> options, int score, int round, int answered,
                                        int questionsTarget, boolean finished) {
        return new GameView(sessionId, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, true, correct, feedbackMessage);
    }
}
