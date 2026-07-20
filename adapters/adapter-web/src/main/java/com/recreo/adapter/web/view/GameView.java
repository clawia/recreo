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
        String mechanicView,
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
        String feedbackMessage,
        List<String> arrangementSlots,
        List<String> arrangementReserve) {

    public static GameView initial(String sessionId, String mechanicView, String catalogEntryId,
                                   List<String> reelSymbols, String question, List<String> options,
                                   int score, int round, int answered, int questionsTarget, boolean finished) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, false, false, null, null, null);
    }

    public static GameView withFeedback(String sessionId, String mechanicView, String catalogEntryId,
                                        boolean correct, String feedbackMessage, List<String> reelSymbols,
                                        String question, List<String> options, int score, int round,
                                        int answered, int questionsTarget, boolean finished) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, true, correct, feedbackMessage, null, null);
    }

    /**
     * Copie de cette vue avec le PLATEAU glisser-deposer renseigne (tuiles verrouillees par
     * emplacement + reserve a replacer). Records immuables : on reconstruit. Les autres
     * mecaniques laissent ces deux champs {@code null} (plateau non pertinent).
     */
    public GameView withArrangementBoard(List<String> slots, List<String> reserve) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, hasFeedback, correct, feedbackMessage, slots, reserve);
    }

    /**
     * Score MAXIMUM atteignable pour les questions jouees : 10 points par bonne reponse
     * (cf. {@code SessionEngine}) x nombre de questions jouees. Sert au score de fin
     * « score / score max » (et non « score / nombre de questions »).
     */
    public int scoreMax() {
        return answered * 10;
    }
}
