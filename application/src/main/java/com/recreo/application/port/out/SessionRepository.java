package com.recreo.application.port.out;

import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.SessionId;

import java.util.Optional;

/** Port de sortie : persistance des sessions de jeu. */
public interface SessionRepository {
    void save(GameSession session);

    Optional<GameSession> findById(SessionId id);
}
