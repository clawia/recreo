package com.recreo.application.service;

import com.recreo.application.port.in.CatalogEntryView;
import com.recreo.application.port.in.CatalogueQuery;
import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.in.GameSummaryView;
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
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanic.MoveResult;
import com.recreo.domain.mechanic.Reel;
import com.recreo.domain.mechanic.SessionId;
import com.recreo.domain.mechanic.SpinAndPick;
import com.recreo.domain.mechanic.SubmitArrangement;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
        // Reglages de session appliques sur la config de base au demarrage.
        GameConfig config = catalog.configFor(command.catalogEntryId(), command.parameters());
        GameMechanic mechanic = catalog.mechanicFor(config.mechanic());
        GameSession session = mechanic.start(config, randomFactory.get())
                .withCatalogEntryId(command.catalogEntryId())
                // Les reglages font partie de l'etat de session : ils permettent de
                // reconstruire le meme generateur parametre a chaque coup.
                .withParameters(command.parameters());
        repository.save(session);
        return toSessionView(session, mechanic.view());
    }

    @Override
    public MoveOutcomeView submit(SubmitMoveCommand command) {
        SessionId sessionId = new SessionId(command.sessionId());
        GameSession stored = repository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session inconnue : " + command.sessionId()));

        // Re-attache les dependances d'execution perdues a la serialisation, en
        // reappliquant les reglages memorises dans la session.
        GameConfig config = catalog.configFor(stored.catalogEntryId(), stored.parameters());
        GameSession session = stored.withRuntime(config.content(), randomFactory.get());

        GameMechanic mechanic = catalog.mechanicFor(session.mechanic());
        Move move = (command.arrangement() != null && !command.arrangement().isEmpty())
                ? new SubmitArrangement(command.arrangement())
                : new SpinAndPick(new Answer(command.chosenAnswer()));
        MoveResult result = mechanic.apply(session, move);
        repository.save(result.session());
        return toMoveOutcomeView(result, mechanic.view());
    }

    @Override
    public List<CatalogEntryView> entries() {
        return catalog.entries().stream()
                .map(this::toCatalogEntryView)
                .toList();
    }

    @Override
    public List<String> niveaux() {
        Set<String> distinct = new LinkedHashSet<>();
        for (CatalogEntry entry : catalog.entries()) {
            distinct.add(entry.niveau());
        }
        return List.copyOf(distinct);
    }

    @Override
    public List<GameSummaryView> games() {
        Set<String> seen = new LinkedHashSet<>();
        return catalog.entries().stream()
                .filter(e -> seen.add(e.gameKey()))
                .map(e -> new GameSummaryView(e.gameKey(), e.gameLabel()))
                .toList();
    }

    @Override
    public List<CatalogEntryView> byNiveau(String niveau) {
        return catalog.entries().stream()
                .filter(e -> e.niveau().equals(niveau))
                .map(this::toCatalogEntryView)
                .toList();
    }

    @Override
    public List<CatalogEntryView> byGameKey(String gameKey) {
        return catalog.entries().stream()
                .filter(e -> e.gameKey().equals(gameKey))
                .map(this::toCatalogEntryView)
                .toList();
    }

    @Override
    public GameSettingsView settingsFor(String entryId) {
        return catalog.settingsFor(entryId);
    }

    private CatalogEntryView toCatalogEntryView(CatalogEntry entry) {
        return new CatalogEntryView(
                entry.entryId(),
                entry.matiere(),
                entry.niveau(),
                entry.gameKey(),
                entry.gameLabel(),
                entry.title(),
                entry.description(),
                entry.hasSettings());
    }

    private SessionView toSessionView(GameSession session, String mechanicView) {
        return new SessionView(
                session.id().value(),
                session.mechanic().value(),
                mechanicView,
                session.catalogEntryId(),
                session.currentItem().reelSymbols(),
                session.currentItem().question(),
                options(session),
                session.score(),
                session.round(),
                session.answered(),
                session.questionsTarget(),
                session.status().name());
    }

    private MoveOutcomeView toMoveOutcomeView(MoveResult result, String mechanicView) {
        GameSession session = result.session();
        return new MoveOutcomeView(
                session.id().value(),
                mechanicView,
                session.catalogEntryId(),
                result.correct(),
                result.feedback().message(),
                session.currentItem().reelSymbols(),
                session.currentItem().question(),
                options(session),
                session.score(),
                session.round(),
                session.answered(),
                session.questionsTarget(),
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
