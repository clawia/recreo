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
import com.recreo.domain.mechanics.glisserdeposer.GlisserDeposerMechanic;
import com.recreo.domain.mechanics.quizaventure.QuizAventureMechanic;
import com.recreo.domain.mechanics.texteatrou.TexteATrouMechanic;
import com.recreo.domain.mechanics.associer.AssocierMechanic;
import com.recreo.domain.mechanics.memory.MemoryMechanic;
import com.recreo.domain.mechanics.puzzle.PuzzleMechanic;
import com.recreo.domain.mechanics.droitegraduee.DroiteGradueeMechanic;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Support MULTI-MECANIQUES (cf. ADR-0007) : une entree {@code TEXTE_A_TROU} demarre
 * avec {@link TexteATrouMechanic} et une entree {@code BANDIT_MANCHOT} avec
 * {@link BanditManchotMechanic} ; le view-model porte le bon {@code mechanicView}. Le
 * catalogue resout les deux mecaniques et chaque entree reference la sienne.
 */
class MultiMechanicTest {

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

    private final GameCatalog catalog =
            new BeansConfig().gameCatalog(new BanditManchotMechanic(), new TexteATrouMechanic(), new QuizAventureMechanic(), new GlisserDeposerMechanic(), new AssocierMechanic(), new MemoryMechanic(), new PuzzleMechanic(), new DroiteGradueeMechanic());

    private SessionService service() {
        RandomSource shared = new Random(11L)::nextInt;
        return new SessionService(catalog, new InMemoryRepo(), () -> shared);
    }

    @Test
    void catalogResolvesBothRegisteredMechanics() {
        assertThat(catalog.mechanicFor(BanditManchotMechanic.ID)).isInstanceOf(BanditManchotMechanic.class);
        assertThat(catalog.mechanicFor(TexteATrouMechanic.ID)).isInstanceOf(TexteATrouMechanic.class);
    }

    @Test
    void texteATrouEntryStartsWithTexteATrouMechanicAndView() {
        SessionView view = service().start(new StartSessionCommand("conjugaison-cm1"));

        assertThat(view.mechanicId()).isEqualTo("texte-a-trou");
        assertThat(view.mechanicView()).isEqualTo("texteatrou");
        // Contenu "texte a trous" : pas de rouleaux, consigne = phrase avec le trou.
        assertThat(view.reelSymbols()).isEmpty();
        assertThat(view.question()).contains("___");
        assertThat(view.options()).isNotEmpty();
    }

    @Test
    void banditManchotEntryStartsWithBanditManchotMechanicAndView() {
        SessionView view = service().start(new StartSessionCommand("calcul-mental-cp"));

        assertThat(view.mechanicId()).isEqualTo("bandit-manchot");
        assertThat(view.mechanicView()).isEqualTo("banditmanchot");
        // Calcul mental : rouleaux [a, op, b].
        assertThat(view.reelSymbols()).hasSize(3);
    }

    @Test
    void everyTexteATrouGameStartsCleanly() {
        for (String entryId : new String[] {"conjugaison-cm1", "accords-cm1", "mot-manquant-cp", "egalite-cp"}) {
            SessionView view = service().start(new StartSessionCommand(entryId));
            assertThat(view.mechanicView()).as("%s -> texteatrou", entryId).isEqualTo("texteatrou");
            assertThat(view.question()).as("%s a un trou", entryId).contains("___");
            assertThat(view.reelSymbols()).as("%s sans rouleaux", entryId).isEmpty();
            assertThat(view.options()).as("%s a des options", entryId).isNotEmpty();
        }
    }
}
