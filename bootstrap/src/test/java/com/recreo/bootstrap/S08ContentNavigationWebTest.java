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
 * Navigation et réglages des jeux de CONTENU ajoutés en s08 (aucune nouvelle mécanique) :
 * "Le bon son" (Français CP, bandit manchot), "Vivre ensemble" (EMC CP) / "Citoyenneté &
 * respect" (EMC CM1) / "Vrai ou Faux — sciences" (quiz-aventure), et "Tables de
 * multiplication" (CM1, calcul mental borné à la seule multiplication).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class S08ContentNavigationWebTest {

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
    void niveauCpListsTheNewCpGames() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Le bon son")))
                .andExpect(content().string(containsString("Vivre ensemble")));
    }

    @Test
    void niveauCm1ListsTheNewCm1Games() throws Exception {
        mockMvc.perform(get("/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tables de multiplication")))
                .andExpect(content().string(containsString("Citoyenneté &amp; respect")))
                .andExpect(content().string(containsString("Vrai ou Faux")));
    }

    @Test
    void jeuxListsTheNewGamesByGameKey() throws Exception {
        mockMvc.perform(get("/jeux"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("href=\"/jeu/le-bon-son\"")))
                .andExpect(content().string(containsString("href=\"/jeu/tables-multiplication\"")))
                .andExpect(content().string(containsString("href=\"/jeu/vivre-ensemble\"")))
                .andExpect(content().string(containsString("href=\"/jeu/citoyennete\"")))
                .andExpect(content().string(containsString("href=\"/jeu/vrai-faux-sciences\"")));
    }

    @Test
    void tablesAreCappedAtFifty() {
        // Tables = combinatoire (multiplication) -> plafond 50, indépendant du contenu.
        // (Le cap des jeux FINIS externalisés est couvert, sans littéral de contenu, par
        // SessionQuestionsTest + un test relatif dans S04 — cf. PLAN-refacto-tests-contenu.md.)
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
