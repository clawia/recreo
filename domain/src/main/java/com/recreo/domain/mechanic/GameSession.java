package com.recreo.domain.mechanic;

import com.recreo.domain.content.ContentGenerator;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.RandomSource;

import java.util.List;

/**
 * Etat immuable d'une session de jeu.
 *
 * <p>Les champs {@code content} et {@code random} sont des dependances d'execution
 * (abstractions pures du domaine) : elles permettent a {@code apply(session, move)}
 * de generer l'item du round suivant sans que la mecanique n'ait a connaitre le
 * contenu concret. Elles ne sont PAS persistees ; la couche persistence ne
 * serialise que l'etat ({@code id, mechanic, catalogEntryId, currentItem, reels,
 * score, round, status}) et l'application les re-attache au chargement.</p>
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
        ContentGenerator content,
        RandomSource random) {

    public GameSession {
        reels = List.copyOf(reels);
    }

    public GameSession withCurrentItem(Item newItem) {
        return new GameSession(id, mechanic, catalogEntryId, newItem, reels, score, round, status, content, random);
    }

    public GameSession withReels(List<Reel> newReels) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, newReels, score, round, status, content, random);
    }

    public GameSession withScore(int newScore) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, newScore, round, status, content, random);
    }

    public GameSession withRound(int newRound) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, newRound, status, content, random);
    }

    public GameSession withStatus(SessionStatus newStatus) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, newStatus, content, random);
    }

    public GameSession withCatalogEntryId(String newCatalogEntryId) {
        return new GameSession(id, mechanic, newCatalogEntryId, currentItem, reels, score, round, status, content, random);
    }

    /** Re-attache les dependances d'execution apres un chargement depuis la persistence. */
    public GameSession withRuntime(ContentGenerator newContent, RandomSource newRandom) {
        return new GameSession(id, mechanic, catalogEntryId, currentItem, reels, score, round, status, newContent, newRandom);
    }
}
