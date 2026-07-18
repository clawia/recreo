package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Couvre l'accueil a deux entrees (US-s03-1) et la page de jeu pleine page
 * (heritee de US-s02-1) : l'accueil ne contient AUCUNE zone de jeu, puis le
 * demarrage renvoie la page de jeu complete (machine centree, fleche retour,
 * consigne du contenu).
 *
 * <p>Pas de smoke navigateur (Playwright) : couvert ici via MockMvc.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SelectionNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeOffersTheTwoNavigationAxesWithoutGameArea() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                // Les deux entrees de navigation.
                .andExpect(content().string(containsString("Par niveau")))
                .andExpect(content().string(containsString("Par jeu")))
                .andExpect(content().string(containsString("href=\"/niveaux\"")))
                .andExpect(content().string(containsString("href=\"/jeux\"")))
                // AUCUNE zone de jeu sur l'accueil.
                .andExpect(content().string(not(containsString("game-area"))))
                .andExpect(content().string(not(containsString("data-session-id"))));
    }

    @Test
    void startingFrancaisCpReturnsFullGamePage() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "francais-cp")
                        .param("from", "/niveau/CP"))
                .andExpect(status().isOk())
                // Page complete (pas un simple fragment).
                .andExpect(content().string(containsString("<!DOCTYPE html>")))
                .andExpect(content().string(containsString("id=\"game-area\"")))
                // La machine (bandit manchot) centree.
                .andExpect(content().string(containsString("class=\"machine\"")))
                .andExpect(content().string(containsString("BANDIT MANCHOT")))
                // La fleche retour contextuelle (revient a l'origine du parcours).
                .andExpect(content().string(containsString("class=\"back-btn\"")))
                .andExpect(content().string(containsString("href=\"/niveau/CP\"")))
                // La consigne fournie par le contenu francais.
                .andExpect(content().string(containsString("Combien de syllabes ?")))
                .andExpect(content().string(containsString("data-session-id")));
    }
}
