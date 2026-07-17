package com.recreo.domain.mechanic;

import com.recreo.domain.content.RandomSource;

/**
 * Contrat d'une mecanique de jeu reutilisable. Ne connait rien du contenu
 * concret : elle consomme des {@link com.recreo.domain.content.ContentGenerator}.
 */
public interface GameMechanic {
    MechanicId id();

    GameSession start(GameConfig config, RandomSource random);

    MoveResult apply(GameSession session, Move move);
}
