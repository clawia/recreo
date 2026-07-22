package com.recreo.adapter.web.view;

import java.util.List;

/**
 * View-model dedie au fragment de jeu. Ne reprend jamais les records du domaine :
 * uniquement des types prets a afficher par Thymeleaf.
 *
 * <p>{@code catalogEntryId} permet au bouton « Rejouer » de l'ecran de fin de
 * relancer une session du meme jeu ; {@code answered} est le nombre de questions
 * jouees (affiche dans le score de fin, cf. ADR-0006).</p>
 *
 * <p><b>Jokers (DEC-0009)</b> : {@code jokers} = coeurs restants (affiches dans le
 * bandeau de score) ; {@code disabledOptions} = reponses deja tentees a tort sur l'item
 * courant, a rendre NON cliquables sur les jeux a clic.</p>
 *
 * <p><b>Associer/classer & memory (ADR-0016)</b> : {@code associationCards} = elements
 * a associer (items a classer, ou cartes a apparier) ; {@code associationGroups} =
 * identifiant de paire PARALLELE a {@code associationCards} (memory uniquement — vide
 * pour « classer », pour ne jamais reveler la bonne reponse) ; {@code associationTargets}
 * = zones-cibles (categories pour « classer », vide pour memory).</p>
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
        List<String> arrangementReserve,
        int jokers,
        List<String> disabledOptions,
        List<String> associationTargets,
        List<String> associationCards,
        List<String> associationGroups,
        List<String> associationLockedItems,
        List<String> associationLockedTargets,
        String hint,
        List<String> lineTicks) {

    public static GameView initial(String sessionId, String mechanicView, String catalogEntryId,
                                   List<String> reelSymbols, String question, List<String> options,
                                   int score, int round, int answered, int questionsTarget, boolean finished,
                                   int jokers, List<String> disabledOptions) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, false, false, null, null, null,
                jokers, disabledOptions == null ? List.of() : disabledOptions, null, null, null,
                List.of(), List.of(), null, List.of());
    }

    public static GameView withFeedback(String sessionId, String mechanicView, String catalogEntryId,
                                        boolean correct, String feedbackMessage, List<String> reelSymbols,
                                        String question, List<String> options, int score, int round,
                                        int answered, int questionsTarget, boolean finished,
                                        int jokers, List<String> disabledOptions) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, true, correct, feedbackMessage, null, null,
                jokers, disabledOptions == null ? List.of() : disabledOptions, null, null, null,
                List.of(), List.of(), null, List.of());
    }

    /**
     * Copie de cette vue avec le PLATEAU glisser-deposer renseigne (tuiles verrouillees par
     * emplacement + reserve a replacer). Records immuables : on reconstruit. Les autres
     * mecaniques laissent ces deux champs {@code null} (plateau non pertinent).
     */
    public GameView withArrangementBoard(List<String> slots, List<String> reserve) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, hasFeedback, correct, feedbackMessage, slots, reserve,
                jokers, disabledOptions, associationTargets, associationCards, associationGroups,
                associationLockedItems, associationLockedTargets, hint, lineTicks);
    }

    /**
     * Copie de cette vue avec le PLATEAU d'associations renseigne (cf. ADR-0016) :
     * {@code targets} = zones-cibles (classer), {@code cards} = elements a associer,
     * {@code groups} = identifiants de paire paralleles aux cartes (memory).
     */
    public GameView withAssociationBoard(List<String> targets, List<String> cards, List<String> groups) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, hasFeedback, correct, feedbackMessage,
                arrangementSlots, arrangementReserve,
                jokers, disabledOptions, targets, cards, groups,
                associationLockedItems, associationLockedTargets, hint, lineTicks);
    }

    /**
     * Copie de cette vue avec les elements VERROUILLES (bien classes, cf. AssociationBoard) :
     * {@code items} sont deja places dans leur cible {@code targets} (paralleles) et ne
     * reviennent PAS en reserve (UX « ranger », DEC-0008). Mecanique associer/classer.
     */
    public GameView withAssociationLocked(List<String> items, List<String> targets) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, hasFeedback, correct, feedbackMessage,
                arrangementSlots, arrangementReserve,
                jokers, disabledOptions, associationTargets, associationCards, associationGroups,
                items, targets, hint, lineTicks);
    }

    /** Copie de cette vue avec un INDICE (image/emoji/definition) affiche — mecanique puzzle. */
    public GameView withHint(String newHint) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, hasFeedback, correct, feedbackMessage,
                arrangementSlots, arrangementReserve,
                jokers, disabledOptions, associationTargets, associationCards, associationGroups,
                associationLockedItems, associationLockedTargets, newHint, lineTicks);
    }

    /** Copie de cette vue avec les GRADUATIONS de la droite (etiquettes : entiers ou fractions) — mecanique droite-graduee. */
    public GameView withTicks(List<String> ticks) {
        return new GameView(sessionId, mechanicView, catalogEntryId, reelSymbols, question, options, score, round,
                answered, questionsTarget, finished, hasFeedback, correct, feedbackMessage,
                arrangementSlots, arrangementReserve,
                jokers, disabledOptions, associationTargets, associationCards, associationGroups,
                associationLockedItems, associationLockedTargets, hint, ticks == null ? List.of() : ticks);
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
