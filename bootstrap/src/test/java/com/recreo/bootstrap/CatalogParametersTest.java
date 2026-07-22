package com.recreo.bootstrap;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.bootstrap.config.BeansConfig;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.Operation;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameConfig;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import com.recreo.domain.mechanics.glisserdeposer.GlisserDeposerMechanic;
import com.recreo.domain.mechanics.quizaventure.QuizAventureMechanic;
import com.recreo.domain.mechanics.texteatrou.TexteATrouMechanic;
import com.recreo.domain.mechanics.associer.AssocierMechanic;
import com.recreo.domain.mechanics.memory.MemoryMechanic;
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

    private final GameCatalog catalog = new BeansConfig().gameCatalog(new BanditManchotMechanic(), new TexteATrouMechanic(), new QuizAventureMechanic(), new GlisserDeposerMechanic(), new AssocierMechanic(), new MemoryMechanic());

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

    // ---- Sens ALEATOIRE par question (s13, retour utilisatrice : pas de reglage) ----

    @Test
    void rangeNombresDefaultsToThreeQuestions() {
        // Defaut 3 questions (plancher abaisse a 1 pour ce jeu).
        assertThat(catalog.configFor("range-nombres-cp").settings().get("questionsTarget")).isEqualTo(3);
        assertThat(catalog.settingsFor("range-nombres-cp").questions().defaultValue()).isEqualTo(3);
        assertThat(catalog.settingsFor("range-nombres-cp").questions().min()).isEqualTo(1);
    }

    @Test
    void rangeNombresDirectionIsRandomPerQuestion() {
        GameConfig config = catalog.configFor("range-nombres-cp");
        RandomSource random = seeded(31L); // un SEUL flux continu (comme en session reelle)
        boolean sawCroissant = false;
        boolean sawDecroissant = false;
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            List<Integer> reel = item.reelSymbols().stream().map(Integer::parseInt).sorted().toList();
            List<Integer> desc = new java.util.ArrayList<>(reel);
            java.util.Collections.reverse(desc);
            String asc = reel.stream().map(String::valueOf).reduce((a, b) -> a + ", " + b).orElse("");
            String dsc = desc.stream().map(String::valueOf).reduce((a, b) -> a + ", " + b).orElse("");
            if (item.question().contains("du plus petit au plus grand")) {
                assertThat(item.expected().value()).isEqualTo(asc);
                sawCroissant = true;
            } else {
                assertThat(item.question()).contains("du plus grand au plus petit");
                assertThat(item.expected().value()).isEqualTo(dsc);
                sawDecroissant = true;
            }
        }
        assertThat(sawCroissant).as("des questions croissantes apparaissent").isTrue();
        assertThat(sawDecroissant).as("des questions decroissantes apparaissent").isTrue();
    }

    @Test
    void comparaisonDirectionIsRandomPerQuestion() {
        GameConfig config = catalog.configFor("comparaison");
        RandomSource random = seeded(41L); // un SEUL flux continu
        boolean sawGrand = false;
        boolean sawPetit = false;
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            int a = Integer.parseInt(item.meta().get("a"));
            int b = Integer.parseInt(item.meta().get("b"));
            if (item.question().contains("plus grand")) {
                assertThat(item.expected().value()).isEqualTo(String.valueOf(Math.max(a, b)));
                sawGrand = true;
            } else {
                assertThat(item.question()).contains("plus petit");
                assertThat(item.expected().value()).isEqualTo(String.valueOf(Math.min(a, b)));
                sawPetit = true;
            }
        }
        assertThat(sawGrand).as("des questions « plus grand » apparaissent").isTrue();
        assertThat(sawPetit).as("des questions « plus petit » apparaissent").isTrue();
    }

    @Test
    void bonSymboleProducesAValidSign() {
        GameConfig config = catalog.configFor("bon-symbole");
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(seeded(200L + i));
            assertThat(item.expected().value()).isIn("<", ">", "=");
            assertThat(item.reelSymbols()).hasSize(3);
            assertThat(item.reelSymbols().get(1)).isEqualTo("?");
        }
    }

    // ---- s14 : décomposition + déclinaisons glisser-déposer ----

    @Test
    void decompositionGivesThePositionalDigit() {
        assertPositionalDigit("decomposition-cp", 10, 99);
        assertPositionalDigit("decomposition-cm1", 100, 999);
    }

    private void assertPositionalDigit(String entryId, int lo, int hi) {
        GameConfig config = catalog.configFor(entryId);
        RandomSource random = seeded(51L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.reelSymbols()).hasSize(1);
            int n = Integer.parseInt(item.reelSymbols().get(0));
            assertThat(n).isBetween(lo, hi);
            int digit;
            if (item.question().contains("unités")) {
                digit = n % 10;
            } else if (item.question().contains("dizaines")) {
                digit = (n / 10) % 10;
            } else {
                assertThat(item.question()).contains("centaines");
                digit = (n / 100) % 10;
            }
            assertThat(item.expected().value()).isEqualTo(String.valueOf(digit));
            assertThat(item.distractors()).hasSize(2).doesNotContain(item.expected());
        }
    }

    @Test
    void friseOrdersEventsChronologicallyAndDefaultsToThreeQuestions() {
        Map<String, Integer> rang = Map.of(
                "La Préhistoire", 1, "Les Gaulois", 2, "Le Moyen Âge", 3,
                "La Révolution française", 4, "La tour Eiffel", 5, "Aujourd'hui", 6);
        GameConfig config = catalog.configFor("frise-cm1");
        assertThat(config.settings().get("questionsTarget")).isEqualTo(3);
        RandomSource random = seeded(61L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.question()).contains("du plus ancien au plus récent");
            assertThat(item.reelSymbols()).hasSize(3);
            List<String> ordre = List.of(item.expected().value().split(", "));
            int prev = 0;
            for (String label : ordre) {
                assertThat(rang).containsKey(label);
                assertThat(rang.get(label)).isGreaterThan(prev); // strictement croissant
                prev = rang.get(label);
            }
        }
    }

    @Test
    void rangeNombresCm1DefaultsToThreeQuestions() {
        assertThat(catalog.configFor("range-nombres-cm1").settings().get("questionsTarget")).isEqualTo(3);
    }

    // ---- s15 : toggle « centaines » + fractions ----

    @Test
    void decompositionCentainesToggleOverridesTheLevelDefault() {
        // Le toggle explicite prime sur le défaut du niveau.
        assertNumberRange(catalog.configFor("decomposition-cp", Map.of("centaines", "true")), 100, 999);
        assertNumberRange(catalog.configFor("decomposition-cm1", Map.of("centaines", "false")), 10, 99);
        // Le toggle est exposé dans les réglages, pré-coché selon le niveau.
        assertThat(catalog.settingsFor("decomposition-cp").toggles()).singleElement()
                .satisfies(t -> {
                    assertThat(t.paramName()).isEqualTo("centaines");
                    assertThat(t.on()).isFalse();
                });
        assertThat(catalog.settingsFor("decomposition-cm1").toggles().get(0).on()).isTrue();
    }

    private void assertNumberRange(GameConfig config, int lo, int hi) {
        RandomSource random = seeded(71L);
        for (int i = 0; i < SAMPLES; i++) {
            int n = Integer.parseInt(config.content().next(random).reelSymbols().get(0));
            assertThat(n).isBetween(lo, hi);
        }
    }

    @Test
    void compareFractionsGivesTheCorrectSign() {
        GameConfig config = catalog.configFor("fractions-cm1");
        RandomSource random = seeded(81L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.reelSymbols()).hasSize(3);
            assertThat(item.reelSymbols().get(1)).isEqualTo("?");
            String[] a = item.reelSymbols().get(0).split("/");
            String[] b = item.reelSymbols().get(2).split("/");
            long left = (long) Integer.parseInt(a[0]) * Integer.parseInt(b[1]);
            long right = (long) Integer.parseInt(b[0]) * Integer.parseInt(a[1]);
            String attendu = left < right ? "<" : left > right ? ">" : "=";
            assertThat(item.expected().value()).isEqualTo(attendu);
            assertThat(item.distractors()).hasSize(2).doesNotContain(item.expected());
        }
    }
}
