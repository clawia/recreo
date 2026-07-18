package com.recreo.application.service;

import com.recreo.application.port.in.CatalogEntryView;
import com.recreo.application.port.in.CatalogueQuery;
import com.recreo.application.port.in.MoveOutcomeView;
import com.recreo.application.port.in.SessionView;
import com.recreo.application.port.in.StartSessionCommand;
import com.recreo.application.port.in.StartSessionUseCase;
import com.recreo.application.port.in.SubmitMoveCommand;
import com.recreo.application.port.in.SubmitMoveUseCase;
import com.recreo.application.port.out.CatalogEntry;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.application.port.out.SessionRepository;
import com.recreo.domain.content.Answer;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.Reel;
import com.recreo.domain.mechanic.SessionId;
import com.recreo.domain.mechanic.SpinAndPick;

import java.util.List;
import java.util.function.Supplier;

/**
 * Orchestre le catalogue, le depot de sessions et la mecanique. Sans aucune
 * annotation ni dependance framework : le wiring est fait en bootstrap.
 */
public final class SessionService implements StartSessionUseCase, SubmitMoveUseCase, CatalogueQuery {

    private final GameCatalog catalog;
    private final SessionRepository repository;
    private final Supplier<RandomSource> randomFactory;

    public SessionService(GameCatalog catalog, SessionRepository repository, Supplier<RandomSource> randomFactory) {
        this.catalog = catalog;
        this.repository = repository;
        this.randomFactory = randomFactory;
    }

    @Override
    public SessionView start(StartSessionCommand command) {
        GameConfig config = catalog.configFor(command.catalogEntryId());
        GameMechanic mechanic = catalog.mechanicFor(config.mechanic());
        GameSession session = mechanic.start(config, randomFactory.get())
                .withCatalogEntryId(command.catalogEntryId());
        repository.save(session);
        return toSessionView(session);
    }

    @Override
    public MoveOutcomeView submit(SubmitMoveCommand command) {
        SessionId sessionId = new SessionId(command.sessionId());
        GameSession stored = repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session inconnue : " + command.sessionId()));

        // Re-attache les dependances d'execution perdues a la serialisation.
        GameConfig config = catalog.configFor(stored.catalogEntryId());
        GameSession session = stored.withRuntime(config.content(), randomFactory.get());

        GameMechanic mechanic = catalog.mechanicFor(session.mechanic());
        MoveResult result = mechanic.apply(session, new SpinAndPick(new Answer(command.chosenAnswer())));
        repository.save(result.session());
        return toMoveOutcomeView(result);
    }

    @Override
    public List<CatalogEntryView> entries() {
        return catalog.entries().stream()
                .map(this::toCatalogEntryView)
                .toList();
    }

    private CatalogEntryView toCatalogEntryView(CatalogEntry entry) {
        return new CatalogEntryView(entry.entryId(), entry.title(), entry.description());
    }

    private SessionView toSessionView(GameSession session) {
        return new SessionView(
                session.id().value(),
                session.mechanic().value(),
                session.currentItem().reelSymbols(),
                session.currentItem().question(),
                options(session),
                session.score(),
                session.round(),
                session.status().name());
    }

    private MoveOutcomeView toMoveOutcomeView(MoveResult result) {
        GameSession session = result.session();
        return new MoveOutcomeView(
                session.id().value(),
                result.correct(),
                result.feedback().message(),
                session.currentItem().reelSymbols(),
                session.currentItem().question(),
                options(session),
                session.score(),
                session.round(),
                session.status().name());
    }

    private List<String> options(GameSession session) {
        return session.reels().stream()
                .map(Reel::symbols)
                .flatMap(List::stream)
                .map(Answer::value)
                .toList();
    }
}
