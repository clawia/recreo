package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Navigation a deux axes (US-s03-1) et ecran de parametres (US-s03-2) via MockMvc.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TwoAxisNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void niveauxListsTheLevels() throws Exception {
        mockMvc.perform(get("/niveaux"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("CP")))
                .andExpect(content().string(containsString("CM1")))
                .andExpect(content().string(containsString("href=\"/niveau/CP\"")))
                .andExpect(content().string(containsString("href=\"/niveau/CM1\"")));
    }

    @Test
    void niveauCpListsItsGamesAsLeaves() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                // Les jeux CP, affiches « Matiere — Nom du jeu » (le niveau est l'en-tete de page).
                .andExpect(content().string(containsString("Mathématiques — Calcul mental")))
                .andExpect(content().string(containsString("Français — Les syllabes")))
                // Chaque feuille propose Jouer et Parametres.
                .andExpect(content().string(containsString("Jouer")))
                .andExpect(content().string(containsString("/parametres")))
                // Origine propagee pour la fleche retour contextuelle.
                .andExpect(content().string(containsString("value=\"/niveau/CP\"")));
    }

    @Test
    void jeuxListsDistinctGames() throws Exception {
        mockMvc.perform(get("/jeux"))
                .andExpect(status().isOk())
                // Axe « par jeu » : liste plate des jeux au format « Matiere Niveau — Nom ».
                .andExpect(content().string(containsString("Mathématiques CP — Calcul mental")))
                .andExpect(content().string(containsString("Français CP — Les syllabes")))
                .andExpect(content().string(containsString("value=\"calcul-mental-cp\"")));
    }

    @Test
    void jeuCalculMentalListsTheLevelsWhereItExists() throws Exception {
        mockMvc.perform(get("/jeu/calcul-mental"))
                .andExpect(status().isOk())
                // Le jeu existe en CP et CM1 : deux feuilles (axe « par jeu » -> prefixe Matiere Niveau).
                .andExpect(content().string(containsString("Mathématiques CP — Calcul mental")))
                .andExpect(content().string(containsString("Mathématiques CM1 — Calcul mental")))
                .andExpect(content().string(containsString("value=\"/jeu/calcul-mental\"")));
    }

    @Test
    void parametresShowsMathForm() throws Exception {
        mockMvc.perform(get("/parametres").param("entryId", "calcul-mental-cp"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("adulte")))
                // Champs min/max et cases a cocher des operations.
                .andExpect(content().string(containsString("name=\"minOperand\"")))
                .andExpect(content().string(containsString("name=\"maxOperand\"")))
                .andExpect(content().string(containsString("name=\"operations\"")));
    }

    @Test
    void parametresShowsSyllabesForm() throws Exception {
        mockMvc.perform(get("/parametres").param("entryId", "francais-cp"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name=\"maxSyllabes\"")));
    }

    @Test
    void startingWithMathParametersReturnsGamePage() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "calcul-mental-cp")
                        .param("from", "/niveau/CP")
                        .param("minOperand", "1")
                        .param("maxOperand", "5")
                        .param("operations", "ADDITION"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<!DOCTYPE html>")))
                .andExpect(content().string(containsString("id=\"game-area\"")))
                .andExpect(content().string(containsString("data-session-id")))
                .andExpect(content().string(containsString("= ?")));
    }
}
