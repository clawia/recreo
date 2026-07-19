package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Selection de la vue selon la mecanique (cf. ADR-0007) : une entree "texte a trous"
 * rend la vue phrase-a-trou (phrase + trou + mots a cliquer, PAS de machine) ; une
 * entree bandit manchot rend toujours la machine a sous ; le mode session (fin, sans
 * doublon) fonctionne sur la nouvelle mecanique.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TexteATrouWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startingConjugaisonRendersTheTexteATrouView() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "conjugaison-cm1")
                        .param("from", "/niveau/CM1"))
                .andExpect(status().isOk())
                // Page complete avec la zone de jeu.
                .andExpect(content().string(containsString("<!DOCTYPE html>")))
                .andExpect(content().string(containsString("id=\"game-area\"")))
                // Presentation "texte a trous" : phrase a completer + trou + mots.
                .andExpect(content().string(containsString("class=\"phrase-a-trou\"")))
                .andExpect(content().string(containsString("class=\"trou\"")))
                .andExpect(content().string(containsString("class=\"word-btn\"")))
                // PAS de machine a sous.
                .andExpect(content().string(not(containsString("class=\"machine\""))))
                .andExpect(content().string(not(containsString("BANDIT MANCHOT"))));
    }

    @Test
    void startingCalculMentalStillRendersTheSlotMachine() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "calcul-mental-cp")
                        .param("from", "/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("class=\"machine\"")))
                .andExpect(content().string(containsString("BANDIT MANCHOT")))
                // Pas de vue texte a trous.
                .andExpect(content().string(not(containsString("class=\"phrase-a-trou\""))));
    }

    @Test
    void aMoveOnTexteATrouReturnsTheTexteATrouFragment() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "accords-cm1"))
                .andExpect(status().isOk())
                .andReturn();
        String sessionId = firstMatch(SESSION_ID, start.getResponse().getContentAsString());

        // Le coup renvoie le FRAGMENT texte a trous (pas la machine), avec le score.
        mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", "jolies"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Score")))
                .andExpect(content().string(not(containsString("BANDIT MANCHOT"))));
    }

    @Test
    void texteATrouSessionReachesTheEndScreen() throws Exception {
        // Session courte (cible = 5) : apres 5 reponses, ecran de fin commun.
        MvcResult start = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "egalite-cp")
                        .param("questionsTarget", "5"))
                .andExpect(status().isOk())
                .andReturn();
        String sessionId = firstMatch(SESSION_ID, start.getResponse().getContentAsString());

        String lastHtml = null;
        for (int i = 0; i < 5; i++) {
            lastHtml = mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", "0"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        }

        // Ecran de fin mutualise : Bravo + Rejouer ; plus de mots a cliquer.
        assertThat(lastHtml).contains("Bravo");
        assertThat(lastHtml).contains("Rejouer");
        assertThat(lastHtml).doesNotContain("class=\"word-btn\"");
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
