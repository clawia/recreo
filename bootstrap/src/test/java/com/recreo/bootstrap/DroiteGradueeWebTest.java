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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mécanique « droite graduée » (8e mécanique, îlot JS, cf. ADR-0018) : l'entrée
 * {@code droite-graduee-cp} rend la vue droite graduée (une droite de graduations cliquables,
 * PAS de machine à sous) ; cliquer la BONNE graduation (la valeur lue dans la consigne « Place
 * le nombre N ») score « Bravo » ; une valeur clairement fausse donne « Presque » sans terminer
 * la session (la mécanique réutilise {@code SpinAndPick} et bénéficie des jokers).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DroiteGradueeWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    /** Cible à placer, lue dans la consigne « Place le nombre N ». */
    private static final Pattern CIBLE = Pattern.compile("Place le nombre (\\d+)");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startingDroiteGradueeRendersTheLineWithClickableTicks() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "droite-graduee-cp")
                        .param("from", "/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<!DOCTYPE html>")))
                .andExpect(content().string(containsString("id=\"game-area\"")))
                .andExpect(content().string(containsString("class=\"droite-graduee\"")))
                .andExpect(content().string(containsString("class=\"droite-tick\"")))
                // PAS de machine a sous.
                .andExpect(content().string(not(containsString("class=\"machine\""))));
    }

    @Test
    void clickingTheRightGraduationScores() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "droite-graduee-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);
        // Bonne graduation = le nombre a placer, lu dans la consigne.
        String cible = firstMatch(CIBLE, html);

        mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", cible))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bravo")))
                .andExpect(content().string(not(containsString("class=\"machine\""))));
    }

    @Test
    void clearlyWrongValueKeepsTheSessionActive() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "droite-graduee-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        // Valeur clairement fausse : hors [0, 20], donc aucune graduation ne correspond.
        String after = mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", "999"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(after).contains("Presque");
        // Session toujours active : on reste en jeu (droite affichee), PAS d'ecran de fin.
        assertThat(after).contains("class=\"droite-tick\"");
        assertThat(after).doesNotContain("Rejouer");
        assertThat(after).doesNotContain("class=\"game-over\"");
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
