package com.recreo.domain.mechanic;

import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Etat immuable d'une session de jeu.
 *
 * <p>Les champs {@code content} et {@code random} sont des dependances d'execution
 * (abstractions pures du domaine) : elles permettent a {@code apply(session, move)}
 * de generer l'item du round suivant sans que la mecanique n'ait a connaitre le
 * contenu concret. Elles ne sont PAS persistees ; la couche persistence ne
 * serialise que l'etat ({@code id, mechanic, catalogEntryId, currentItem, reels,
 * score, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong,
 * parameters}) et l'application les re-attache au chargement.</p>
 *
 * <p><b>Mode session (cf. ADR-0006)</b> : {@code questionsTarget} est la longueur
 * cible de la session (nombre de questions, reglee par un adulte, defaut 20) ;
 * {@code answered} compte les questions repondues ; {@code masteredKeys} contient
 * les {@link Item#key()} des items REUSSIS (exclus des tirages suivants).</p>
 *
 * <p><b>Jokers / 3 coeurs (cf. DEC-0009, ADR-0016)</b> : {@code jokers} est le
 * CAPITAL de retentes de la session (defaut 3, decremente sur chaque erreur, jamais
 * remis a zero) ; a 0 joker, une erreur termine la session. {@code triedWrong}
 * contient, pour l'item COURANT uniquement, les reponses deja tentees a tort (pour
 * griser les options sur les jeux a clic) ; il est remis a vide a chaque nouvel item.</p>
 *
 * <p>{@code parameters} porte les reglages de session (regles par un adulte, cf.
 * ADR-0005) : ils permettent a l'application de RE-CONSTRUIRE le meme generateur
 * parametre a chaque coup.</p>
 */
public record GameSession(
        SessionId id,
        MechanicId mechanic,
        String catalogEntryId,
        Item currentItem,
        List<Reel> reels,
        int score,
        int round,
        SessionStatus status,
        int questionsTarget,
        int answered,
        Set<String> masteredKeys,
        int jokers,
        Set<String> triedWrong,
        Map<String, String> parameters,
        ContentGenerator content,
        RandomSource random) {

    /** Longueur de session par defaut si aucun reglage n'est fourni (cf. ADR-0006). */
    public static final int DEFAULT_QUESTIONS_TARGET = 20;

    /** Capital de retentes par defaut (3 coeurs, cf. DEC-0009). */
    public static final int DEFAULT_JOKERS = 3;

    public GameSession {
        reels = List.copyOf(reels);
        masteredKeys = masteredKeys == null ? Set.of() : Set.copyOf(masteredKeys);
        triedWrong = triedWrong == null ? Set.of() : Set.copyOf(triedWrong);
        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
    }

    /**
     * Constructeur "leger" (sans etat de session ni reglages) : cible par defaut,
     * {@code answered=0}, {@code masteredKeys} vide, {@code jokers=3}, {@code triedWrong}
     * vide, {@code parameters} vide. Preserve la simplicite d'appel ; les reglages/cible
     * sont attaches ensuite par l'application ou fournis par la mecanique via les vues
     * {@code with*}.
     */
    public GameSession(SessionId id, MechanicId mechanic, String catalogEntryId, Item currentItem,
                       List<Reel> reels, int score, int round, SessionStatus status,
                       ContentGenerator content, RandomSource random) {
        this(id, mechanic, catalogEntryId, currentItem, reels, score, round, status,
                DEFAULT_QUESTIONS_TARGET, 0, Set.of(), DEFAULT_JOKERS, Set.of(), Map.of(), content, random);
    }

    public GameSession withCurrentItem(Item newItem) {
        return new GameSession(id, mechanic, catalogEntryId, newItem, reels, score, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withReels(List<Reel> newReels) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, newReels, score, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withScore(int newScore) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, newScore, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withRound(int newRound) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, newRound, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withStatus(SessionStatus newStatus) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, newStatus, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withQuestionsTarget(int newTarget) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, newTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withAnswered(int newAnswered) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, questionsTarget, newAnswered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withMasteredKeys(Set<String> newMasteredKeys) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, questionsTarget, answered, newMasteredKeys, jokers, triedWrong, parameters, content, random);
    }

    /** Fixe le capital de jokers restant (3 coeurs, decrementes sur erreur, cf. DEC-0009). */
    public GameSession withJokers(int newJokers) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, questionsTarget, answered, masteredKeys, newJokers, triedWrong, parameters, content, random);
    }

    /** Fixe les reponses deja tentees a tort sur l'item COURANT (remis a vide a chaque nouvel item). */
    public GameSession withTriedWrong(Set<String> newTriedWrong) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, questionsTarget, answered, masteredKeys, jokers, newTriedWrong, parameters, content, random);
    }

    public GameSession withCatalogEntryId(String newCatalogEntryId) {
        return new GameSession(id, mechanic, newCatalogEntryId, currentItem, reels, score, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, content, random);
    }

    public GameSession withParameters(Map<String, String> newParameters) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, newParameters, content, random);
    }

    /** Re-attache les dependances d'execution apres un chargement depuis la persistence. */
    public GameSession withRuntime(ContentGenerator newContent, RandomSource newRandom) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, questionsTarget, answered, masteredKeys, jokers, triedWrong, parameters, newContent, newRandom);
    }
}
