package com.recreo.bootstrap;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.bootstrap.config.BeansConfig;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.Operation;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import com.recreo.domain.mechanics.quizaventure.QuizAventureMechanic;
import com.recreo.domain.mechanics.texteatrou.TexteATrouMechanic;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifie le wiring "reglages de session -> generateur configurable" (ADR-0005) au
 * niveau du catalogue : les parametres construisent le bon generateur, les defauts
 * reproduisent le comportement s02, et un reglage hors-programme est impossible.
 */
class CatalogParametersTest {

    private static final int SAMPLES = 300;

    private final GameCatalog catalog = new BeansConfig().gameCatalog(new BanditManchotMechanic(), new TexteATrouMechanic(), new QuizAventureMechanic());

    private static RandomSource seeded(long seed) {
        Random random = new Random(seed);
        return random::nextInt;
    }

    @Test
    void mathDefaultsReproduceBaseBehaviour() {
        GameConfig config = catalog.configFor("calcul-mental-cp");
        RandomSource random = seeded(1L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.meta().get("operation"))
                    .isIn(Operation.ADDITION.name(), Operation.SOUSTRACTION.name());
            assertThat(Integer.parseInt(item.meta().get("operandA"))).isBetween(0, 10);
            assertThat(Integer.parseInt(item.meta().get("operandB"))).isBetween(0, 10);
        }
    }

    @Test
    void mathParametersNarrowWindowAndOperations() {
        GameConfig config = catalog.configFor(
                "calcul-mental-cp", Map.of("maxOperand", "5", "operations", "ADDITION"));
        RandomSource random = seeded(2L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.meta().get("operation")).isEqualTo(Operation.ADDITION.name());
            assertThat(Integer.parseInt(item.meta().get("operandA"))).isBetween(0, 5);
            assertThat(Integer.parseInt(item.meta().get("operandB"))).isBetween(0, 5);
        }
    }

    @Test
    void mathOutOfProgrammeSettingIsRejected() {
        // CP ne permet pas la multiplication : demandee, elle est ignoree (repli sur la base).
        GameConfig config = catalog.configFor(
                "calcul-mental-cp", Map.of("operations", "MULTIPLICATION"));
        RandomSource random = seeded(3L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.meta().get("operation"))
                    .isIn(Operation.ADDITION.name(), Operation.SOUSTRACTION.name());
        }
    }

    @Test
    void mathOperandCeilingIsClamped() {
        // Reglage au-dela de la borne CP (20) : borne appliquee malgre la demande.
        GameConfig config = catalog.configFor(
                "calcul-mental-cp", Map.of("maxOperand", "999", "operations", "ADDITION"));
        RandomSource random = seeded(4L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(Integer.parseInt(item.meta().get("operandA"))).isBetween(0, 20);
            assertThat(Integer.parseInt(item.meta().get("operandB"))).isBetween(0, 20);
        }
    }

    @Test
    void syllabesParameterFiltersWords() {
        GameConfig config = catalog.configFor("francais-cp", Map.of("maxSyllabes", "2"));
        RandomSource random = seeded(5L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(Integer.parseInt(item.expected().value())).isLessThanOrEqualTo(2);
        }
    }

    @Test
    void settingsExposeMathSchemaWithinBounds() {
        GameSettingsView settings = catalog.settingsFor("calcul-mental-cp");
        assertThat(settings.gameKey()).isEqualTo("calcul-mental");
        assertThat(settings.syllabes()).isNull();
        assertThat(settings.math()).isNotNull();
        assertThat(settings.math().operandCeiling()).isEqualTo(20);
        List<String> ops = settings.math().operations().stream()
                .map(o -> o.name()).toList();
        // CP : seulement addition/soustraction proposees (pas de multiplication).
        assertThat(ops).containsExactlyInAnyOrder(
                Operation.ADDITION.name(), Operation.SOUSTRACTION.name());
    }

    @Test
    void settingsExposeSyllabesSchema() {
        GameSettingsView settings = catalog.settingsFor("francais-cp");
        assertThat(settings.gameKey()).isEqualTo("syllabes");
        assertThat(settings.math()).isNull();
        assertThat(settings.syllabes()).isNotNull();
        assertThat(settings.syllabes().options()).containsExactly(2, 3, 4);
    }

    // ---- Reglage commun "nombre de questions" (ADR-0006) ----

    @Test
    void questionsTargetDefaultsToTwentyWhenAbsent() {
        // Combinatoire : plafond 50, defaut 20.
        assertThat(catalog.configFor("calcul-mental-cp").settings().get("questionsTarget"))
                .isEqualTo(20);
    }

    @Test
    void questionsTargetIsClampedToFiveAndFifty() {
        assertThat(catalog.configFor("calcul-mental-cp", Map.of("questionsTarget", "1"))
                .settings().get("questionsTarget")).isEqualTo(5);
        assertThat(catalog.configFor("calcul-mental-cp", Map.of("questionsTarget", "999"))
                .settings().get("questionsTarget")).isEqualTo(50);
        assertThat(catalog.configFor("calcul-mental-cp", Map.of("questionsTarget", "30"))
                .settings().get("questionsTarget")).isEqualTo(30);
    }

    // Le plafonnement par le vivier d'un jeu FINI (ex-homophones/anglais) est désormais
    // testé sans littéral de contenu par SessionQuestionsTest (logique en isolation) —
    // cf. PLAN-refacto-tests-contenu.md. Le clamp général reste couvert ci-dessus sur un
    // jeu combinatoire (calcul-mental-cp).
}
