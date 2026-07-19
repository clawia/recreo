package com.recreo.bootstrap;

import com.recreo.application.port.in.SessionView;
import com.recreo.application.port.in.StartSessionCommand;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.application.port.out.SessionRepository;
import com.recreo.application.service.SessionService;
import com.recreo.bootstrap.config.BeansConfig;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.SessionId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import com.recreo.domain.mechanics.quizaventure.QuizAventureMechanic;
import com.recreo.domain.mechanics.texteatrou.TexteATrouMechanic;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Application (use case StartSession) : demarrer avec des reglages construit le bon
 * generateur ; sans reglage = comportement par defaut. Depot en memoire (pas de
 * Spring/H2) pour isoler la couche application + le wiring du catalogue.
 */
class StartSessionParametersTest {

    /** Depot en memoire minimal. */
    private static final class InMemoryRepo implements SessionRepository {
        private final Map<String, GameSession> store = new HashMap<>();

        @Override
        public void save(GameSession session) {
            store.put(session.id().value(), session);
        }

        @Override
        public Optional<GameSession> findById(SessionId id) {
            return Optional.ofNullable(store.get(id.value()));
        }
    }

    private SessionService service() {
        GameCatalog catalog = new BeansConfig().gameCatalog(new BanditManchotMechanic(), new TexteATrouMechanic(), new QuizAventureMechanic());
        RandomSource shared = new Random(7L)::nextInt;
        return new SessionService(catalog, new InMemoryRepo(), () -> shared);
    }

    @Test
    void startWithMathParametersBuildsNarrowedGenerator() {
        SessionService service = service();
        for (int i = 0; i < 50; i++) {
            SessionView view = service.start(new StartSessionCommand(
                    "calcul-mental-cp", Map.of("maxOperand", "5", "operations", "ADDITION")));
            // reelSymbols = [a, "+", b] : l'operateur est l'addition, operandes <= 5.
            assertThat(view.reelSymbols()).hasSize(3);
            assertThat(view.reelSymbols().get(1)).isEqualTo("+");
            assertThat(Integer.parseInt(view.reelSymbols().get(0))).isBetween(0, 5);
            assertThat(Integer.parseInt(view.reelSymbols().get(2))).isBetween(0, 5);
        }
    }

    @Test
    void startWithoutParametersUsesDefaults() {
        SessionView view = service().start(new StartSessionCommand("calcul-mental-cp"));
        assertThat(view.question()).isEqualTo("= ?");
        assertThat(view.reelSymbols()).hasSize(3);
        assertThat(view.reelSymbols().get(1)).isIn("+", "-");
        assertThat(view.options()).isNotEmpty();
    }

    @Test
    void startFrancaisWithMaxSyllabesStartsSession() {
        // Le filtrage effectif est prouve au niveau du catalogue/generateur
        // (CatalogParametersTest) ; ici on verifie que le use case demarre bien
        // une session francais parametree.
        SessionView view = service().start(new StartSessionCommand(
                "francais-cp", Map.of("maxSyllabes", "2")));
        assertThat(view.question()).isEqualTo("Combien de syllabes ?");
        assertThat(view.reelSymbols()).hasSize(1);
        assertThat(view.options()).isNotEmpty();
    }
}
