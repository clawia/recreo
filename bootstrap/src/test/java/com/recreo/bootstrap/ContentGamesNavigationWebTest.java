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
import com.recreo.domain.mechanics.puzzle.PuzzleMechanic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Navigation des jeux de CONTENU (batis sur des mecaniques existantes) et contraintes des
 * « Tables de multiplication ». Ces jeux ("Le bon son" en Francais CP, EMC "Vivre ensemble" /
 * "Citoyennete & respect", "Vrai ou Faux — sciences", et les tables) apparaissent aux bons
 * niveaux et dans l'axe par jeu ; les tables restent bornees a la seule multiplication
 * (operandes 1..10) et plafonnees a 50 questions.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ContentGamesNavigationWebTest {

    private static final int SAMPLES = 300;

    @Autowired
    private MockMvc mockMvc;

    private final GameCatalog catalog = new BeansConfig().gameCatalog(
            new BanditManchotMechanic(), new TexteATrouMechanic(), new QuizAventureMechanic(), new GlisserDeposerMechanic(),
            new AssocierMechanic(), new MemoryMechanic(), new PuzzleMechanic());

    private static RandomSource seeded(long seed) {
        Random random = new Random(seed);
        return random::nextInt;
    }

    @Test
    void niveauCpListsContentGames() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Le bon son")))
                .andExpect(content().string(containsString("Vivre ensemble")));
    }

    @Test
    void niveauCm1ListsContentGames() throws Exception {
        mockMvc.perform(get("/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tables de multiplication")))
                .andExpect(content().string(containsString("Citoyenneté &amp; respect")))
                .andExpect(content().string(containsString("Vrai ou Faux")));
    }

    @Test
    void jeuxListsContentGames() throws Exception {
        // Axe « par jeu » : liste plate des jeux, reperes par leur nom court (gameLabel).
        mockMvc.perform(get("/jeux"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Le bon son")))
                .andExpect(content().string(containsString("Tables de multiplication")))
                .andExpect(content().string(containsString("Vivre ensemble")))
                .andExpect(content().string(containsString("Citoyenneté &amp; respect")))
                .andExpect(content().string(containsString("Vrai ou Faux")));
    }

    @Test
    void tablesAreCappedAtFifty() {
        // Tables = combinatoire (multiplication) -> plafond 50, indépendant du contenu.
        // (Le cap des jeux FINIS externalisés est couvert, sans littéral de contenu, par
        // SessionQuestionsTest + un test relatif dans GamesNavigationAndSettingsWebTest.)
        assertThat(catalog.settingsFor("tables-multiplication-cm1").questions().max()).isEqualTo(50);
    }

    @Test
    void tablesSettingsOfferOnlyMultiplication() {
        GameSettingsView settings = catalog.settingsFor("tables-multiplication-cm1");
        assertThat(settings.math()).isNotNull();
        List<String> ops = settings.math().operations().stream().map(o -> o.name()).toList();
        assertThat(ops).containsExactly(Operation.MULTIPLICATION.name());
        assertThat(settings.math().operandCeiling()).isEqualTo(20);
    }

    @Test
    void tablesAlwaysProduceMultiplicationsWithinOneToTen() {
        GameConfig config = catalog.configFor("tables-multiplication-cm1");
        RandomSource random = seeded(9L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.meta().get("operation")).isEqualTo(Operation.MULTIPLICATION.name());
            assertThat(Integer.parseInt(item.meta().get("operandA"))).isBetween(1, 10);
            assertThat(Integer.parseInt(item.meta().get("operandB"))).isBetween(1, 10);
        }
    }

    @Test
    void tablesRejectNonMultiplicationSetting() {
        // Demander l'addition sur les tables : ignore (borne allowedOperations = MULTIPLICATION).
        GameConfig config = catalog.configFor(
                "tables-multiplication-cm1", Map.of("operations", "ADDITION"));
        RandomSource random = seeded(10L);
        for (int i = 0; i < SAMPLES; i++) {
            Item item = config.content().next(random);
            assertThat(item.meta().get("operation")).isEqualTo(Operation.MULTIPLICATION.name());
        }
    }
}
