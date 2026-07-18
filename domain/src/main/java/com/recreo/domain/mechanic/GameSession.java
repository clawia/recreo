package com.recreo.domain.mechanic;

import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;

import java.util.List;
import java.util.Map;

/**
 * Etat immuable d'une session de jeu.
 *
 * <p>Les champs {@code content} et {@code random} sont des dependances d'execution
 * (abstractions pures du domaine) : elles permettent a {@code apply(session, move)}
 * de generer l'item du round suivant sans que la mecanique n'ait a connaitre le
 * contenu concret. Elles ne sont PAS persistees ; la couche persistence ne
 * serialise que l'etat ({@code id, mechanic, catalogEntryId, currentItem, reels,
 * score, round, status, parameters}) et l'application les re-attache au chargement.</p>
 *
 * <p>{@code parameters} porte les reglages de session (regles par un adulte, cf.
 * ADR-0005) : ils permettent a l'application de RE-CONSTRUIRE le meme generateur
 * parametre a chaque coup. Ils font partie de l'etat de la session courante (donc
 * serialises avec elle) ; il n'existe aucun stockage de reglages independant.</p>
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
        Map<String, String> parameters,
        ContentGenerator content,
        RandomSource random) {

    public GameSession {
        reels = List.copyOf(reels);
        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
    }

    /**
     * Constructeur historique (sans reglages) : les mecaniques demarrent une
     * session avec des parametres vides. Preserve la signature d'origine pour que
     * les mecaniques restent inchangees ; les reglages sont attaches ensuite par
     * l'application via {@link #withParameters(Map)}.
     */
    public GameSession(SessionId id, MechanicId mechanic, String catalogEntryId, Item currentItem,
                       List<Reel> reels, int score, int round, SessionStatus status,
                       ContentGenerator content, RandomSource random) {
        this(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, Map.of(), content, random);
    }

    public GameSession withCurrentItem(Item newItem) {
        return new GameSession(id, mechanic, catalogEntryId, newItem, reels, score, round, status, parameters, content, random);
    }

    public GameSession withReels(List<Reel> newReels) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, newReels, score, round, status, parameters, content, random);
    }

    public GameSession withScore(int newScore) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, newScore, round, status, parameters, content, random);
    }

    public GameSession withRound(int newRound) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, newRound, status, parameters, content, random);
    }

    public GameSession withStatus(SessionStatus newStatus) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, newStatus, parameters, content, random);
    }

    public GameSession withCatalogEntryId(String newCatalogEntryId) {
        return new GameSession(id, mechanic, newCatalogEntryId, currentItem, reels, score, round, status, parameters, content, random);
    }

    public GameSession withParameters(Map<String, String> newParameters) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, newParameters, content, random);
    }

    /** Re-attache les dependances d'execution apres un chargement depuis la persistence. */
    public GameSession withRuntime(ContentGenerator newContent, RandomSource newRandom) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, parameters, newContent, newRandom);
    }
}
