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
 * Couvre le flux de navigation pleine page (US-s02-1) et l'entree francais CP
 * (US-s02-2) : ecran de selection SANS zone de jeu, puis page de jeu complete
 * avec machine centree, fleche retour et consigne du contenu.
 *
 * <p>Le smoke test navigateur (Playwright) du flux selection -> jeu reste a faire
 * (pas de Node/navigateur dans cet environnement) ; couvert ici via MockMvc.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SelectionNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void selectionScreenListsCardsWithoutGameArea() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                // Les cartes, dont Francais CP.
                .andExpect(content().string(containsString("Les jeux")))
                .andExpect(content().string(containsString("Français CP — Les syllabes")))
                .andExpect(content().string(containsString("Calcul mental — CP")))
                // Cartes = formulaires POST /sessions en navigation classique.
                .andExpect(content().string(containsString("action=\"/sessions\"")))
                // AUCUNE zone de jeu sur l'ecran de selection.
                .andExpect(content().string(not(containsString("game-area"))))
                .andExpect(content().string(not(containsString("data-session-id"))));
    }

    @Test
    void startingFrancaisCpReturnsFullGamePage() throws Exception {
        mockMvc.perform(post("/sessions").param("catalogEntryId", "francais-cp"))
                .andExpect(status().isOk())
                // Page complete (pas un simple fragment).
                .andExpect(content().string(containsString("<!DOCTYPE html>")))
                .andExpect(content().string(containsString("id=\"game-area\"")))
                // La machine (bandit manchot) centree.
                .andExpect(content().string(containsString("class=\"machine\"")))
                .andExpect(content().string(containsString("BANDIT MANCHOT")))
                // La fleche retour unique.
                .andExpect(content().string(containsString("class=\"back-btn\"")))
                // La consigne fournie par le contenu francais.
                .andExpect(content().string(containsString("Combien de syllabes ?")))
                .andExpect(content().string(containsString("data-session-id")));
    }
}
