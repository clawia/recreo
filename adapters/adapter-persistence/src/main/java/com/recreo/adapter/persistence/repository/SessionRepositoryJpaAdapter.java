package com.recreo.adapter.persistence.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recreo.adapter.persistence.entity.SessionEntity;
import com.recreo.application.port.out.SessionRepository;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanic.SessionId;
import com.recreo.domain.mechanic.SessionStatus;

import java.util.Optional;

/**
 * Adapter du port {@link SessionRepository} : traduit entre {@link GameSession}
 * (domaine) et {@link SessionEntity} (JPA), avec serialisation JSON de l'etat
 * riche via Jackson.
 */
public class SessionRepositoryJpaAdapter implements SessionRepository {

    private final SpringDataSessionRepository delegate;
    private final ObjectMapper objectMapper;

    public SessionRepositoryJpaAdapter(SpringDataSessionRepository delegate, ObjectMapper objectMapper) {
        this.delegate = delegate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(GameSession session) {
        SessionEntity entity = new SessionEntity(
                session.id().value(),
                session.mechanic().value(),
                session.catalogEntryId(),
                session.score(),
                session.round(),
                session.status().name(),
                writeState(session));
        delegate.save(entity);
    }

    @Override
    public Optional<GameSession> findById(SessionId id) {
        return delegate.findById(id.value()).map(this::toDomain);
    }

    private GameSession toDomain(SessionEntity entity) {
        PersistedState state = readState(entity.getStateJson());
        // content et random restent null : re-attaches par l'application au besoin.
        return new GameSession(
                new SessionId(entity.getId()),
                new MechanicId(entity.getMechanicId()),
                entity.getCatalogEntryId(),
                state.currentItem(),
                state.reels(),
                entity.getScore(),
                entity.getRound(),
                SessionStatus.valueOf(entity.getStatus()),
                state.questionsTarget(),
                state.answered(),
                state.masteredKeys(),
                state.parameters(),
                null,
                null);
    }

    private String writeState(GameSession session) {
        try {
            return objectMapper.writeValueAsString(new PersistedState(
                    session.currentItem(), session.reels(), session.parameters(),
                    session.questionsTarget(), session.answered(), session.masteredKeys()));
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de serialiser l'etat de session", e);
        }
    }

    private PersistedState readState(String json) {
        try {
            return objectMapper.readValue(json, PersistedState.class);
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de deserialiser l'etat de session", e);
        }
    }
}
