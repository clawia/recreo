package com.recreo.bootstrap;

import com.recreo.application.port.in.GameSettingsView;
import com.recreo.application.port.out.GameCatalog;
import com.recreo.bootstrap.config.BeansConfig;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMechanic;
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

    private final GameCatalog catalog = new BeansConfig().gameCatalog(new BanditManchotMechanic());

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
    void gameWithoutSettingsHasNoParametresButton() throws Exception {
        // La page du jeu "homophones" ne contient QUE ce jeu (sans reglage) : pas de bouton Paramètres.
        mockMvc.perform(get("/jeu/homophones"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Le bon petit mot")))
                .andExpect(content().string(containsString("Jouer")))
                .andExpect(content().string(not(containsString("/parametres"))))
                .andExpect(content().string(not(containsString("Paramètres"))));
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
    void newGamesExposeNoSettings() {
        for (String entryId : new String[] {
                "complement-dix", "comparaison", "premiere-lettre",
                "homophones", "nature-mot", "anglais-lexique"}) {
            GameSettingsView settings = catalog.settingsFor(entryId);
            assertThat(settings.hasSettings()).as("%s sans reglage", entryId).isFalse();
            assertThat(settings.math()).isNull();
            assertThat(settings.syllabes()).isNull();
        }
    }

    @Test
    void existingGamesStillExposeSettings() {
        assertThat(catalog.settingsFor("calcul-mental-cp").hasSettings()).isTrue();
        assertThat(catalog.settingsFor("francais-cp").hasSettings()).isTrue();
    }
}
