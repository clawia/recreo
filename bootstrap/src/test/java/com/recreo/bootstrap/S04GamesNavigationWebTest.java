package com.recreo.bootstrap;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.bootstrap.config.BeansConfig;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
import com.recreo.domain.mechanics.glisserdeposer.GlisserDeposerMechanic;
import com.recreo.domain.mechanics.quizaventure.QuizAventureMechanic;
import com.recreo.domain.mechanics.texteatrou.TexteATrouMechanic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Navigation des 6 nouveaux jeux s04 : les entrees apparaissent au bon niveau et
 * par jeu (deux axes), un jeu sans reglage n'expose pas de bouton « Paramètres »,
 * et les jeux existants (avec reglages) restent inchanges.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class S04GamesNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    private final GameCatalog catalog = new BeansConfig().gameCatalog(new BanditManchotMechanic(), new TexteATrouMechanic(), new QuizAventureMechanic(), new GlisserDeposerMechanic());

    @Test
    void niveauCpListsTheNewCpGames() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Compléments à 10")))
                .andExpect(content().string(containsString("Le plus grand nombre")))
                .andExpect(content().string(containsString("La première lettre")));
    }

    @Test
    void niveauCm1ListsTheNewCm1Games() throws Exception {
        mockMvc.perform(get("/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Le bon petit mot")))
                .andExpect(content().string(containsString("La nature du mot")))
                .andExpect(content().string(containsString("Le bon mot en anglais")));
    }

    @Test
    void jeuxListsTheSixNewGamesByGameKey() throws Exception {
        mockMvc.perform(get("/jeux"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("href=\"/jeu/complement-dix\"")))
                .andExpect(content().string(containsString("href=\"/jeu/comparaison\"")))
                .andExpect(content().string(containsString("href=\"/jeu/premiere-lettre\"")))
                .andExpect(content().string(containsString("href=\"/jeu/homophones\"")))
                .andExpect(content().string(containsString("href=\"/jeu/nature-mot\"")))
                .andExpect(content().string(containsString("href=\"/jeu/anglais-lexique\"")));
    }

    @Test
    void everyGameNowExposesTheQuestionsSetting() throws Exception {
        // Depuis s05 (ADR-0006), TOUS les jeux ont le reglage commun "nombre de
        // questions" : le jeu "homophones" (jadis sans reglage) affiche desormais
        // aussi le bouton Paramètres.
        mockMvc.perform(get("/jeu/homophones"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Le bon petit mot")))
                .andExpect(content().string(containsString("Jouer")))
                .andExpect(content().string(containsString("/parametres")))
                .andExpect(content().string(containsString("Paramètres")));
    }

    @Test
    void questionsSettingScreenIsShownForASimpleGame() throws Exception {
        // L'ecran de reglages d'un jeu simple ne montre QUE le champ "nombre de questions".
        mockMvc.perform(get("/parametres").param("entryId", "homophones"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name=\"questionsTarget\"")))
                .andExpect(content().string(not(containsString("name=\"minOperand\""))))
                .andExpect(content().string(not(containsString("name=\"maxSyllabes\""))));
    }

    @Test
    void gameWithSettingsStillHasParametresButton() throws Exception {
        // Regression : calcul mental (avec reglages) affiche toujours le bouton Paramètres.
        mockMvc.perform(get("/jeu/calcul-mental"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/parametres")));
    }

    @Test
    void newGameStartsASessionWithoutParameters() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "complement-dix")
                        .param("from", "/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id=\"game-area\"")))
                .andExpect(content().string(containsString("Combien pour aller à 10 ?")));
    }

    @Test
    void newGamesExposeOnlyTheCommonQuestionsSetting() {
        // Depuis s05 : ces jeux n'ont pas de reglage math/syllabes, mais ils ont le
        // reglage commun "nombre de questions" -> hasSettings devient vrai.
        for (String entryId : new String[] {
                "complement-dix", "comparaison", "premiere-lettre",
                "homophones", "nature-mot", "anglais-lexique"}) {
            GameSettingsView settings = catalog.settingsFor(entryId);
            assertThat(settings.hasSettings()).as("%s a le reglage questions", entryId).isTrue();
            assertThat(settings.math()).as("%s sans bloc math", entryId).isNull();
            assertThat(settings.syllabes()).as("%s sans bloc syllabes", entryId).isNull();
            assertThat(settings.questions()).as("%s a un bloc questions", entryId).isNotNull();
        }
    }

    @Test
    void combinatorialGamesAreCappedAtFifty() {
        // Jeux COMBINATOIRES (vivier infini) : plafond 50, indépendant de tout contenu.
        // (Le plafonnement par le vivier des jeux FINIS est testé, sans contenu figé,
        // par SessionQuestionsTest — cf. PLAN-refacto-tests-contenu.md.)
        assertThat(catalog.settingsFor("comparaison").questions().max()).isEqualTo(50);
        assertThat(catalog.settingsFor("complement-dix").questions().max()).isEqualTo(50);
    }

    @Test
    void aFiniteGameCapStaysWithinSaneBounds() {
        // Assertion RELATIVE (pas de littéral de contenu) : le cap d'un jeu fini reste
        // dans [5, 50] quelle que soit l'édition du fichier -> robuste.
        assertThat(catalog.settingsFor("anglais-lexique").questions().max()).isBetween(5, 50);
    }

    @Test
    void existingGamesStillExposeSettings() {
        assertThat(catalog.settingsFor("calcul-mental-cp").hasSettings()).isTrue();
        assertThat(catalog.settingsFor("francais-cp").hasSettings()).isTrue();
    }
}
