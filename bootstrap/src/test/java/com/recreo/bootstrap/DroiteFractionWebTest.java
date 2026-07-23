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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Jeu « Fraction sur une droite » (s23) : MEME mécanique « droite graduée » (8e mécanique,
 * îlot JS, cf. ADR-0018), mais les graduations sont des FRACTIONS ({@code 0, 1/4, 1/2, 3/4, 1}).
 * L'entrée {@code droite-fraction-cm1} rend la vue droite graduée avec des ticks cliquables
 * portant les libellés de fractions ; cliquer la BONNE graduation (la fraction lue dans la
 * consigne « Place la fraction … ») score « Bravo ». La cible est une graduation INTERNE
 * (jamais « 0 » ni « 1 »), donc toujours l'une des fractions cliquables.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DroiteFractionWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    /** Fraction à placer, lue dans la consigne « Place la fraction X » (chiffres et « / », ex. « 1/2 » ;
     *  on borne aux chiffres/slash pour NE PAS capter la balise fermante « </p> » qui suit). */
    private static final Pattern CIBLE = Pattern.compile("Place la fraction ([0-9/]+)");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startingDroiteFractionRendersTheLineWithFractionTicks() throws Exception {
        String html = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "droite-fraction-cm1")
                        .param("from", "/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id=\"game-area\"")))
                .andExpect(content().string(containsString("class=\"droite-graduee\"")))
                .andExpect(content().string(containsString("class=\"droite-tick\"")))
                .andReturn().getResponse().getContentAsString();

        // Les graduations portent les libellés de fractions (au moins une fraction interne visible).
        assertThat(html).contains("1/2");
        // La consigne emploie bien le nom « fraction » (pas « nombre »).
        assertThat(html).contains("Place la fraction");
    }

    @Test
    void clickingTheRightFractionScores() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "droite-fraction-cm1"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);
        // Bonne graduation = la fraction à placer, lue dans la consigne.
        String cible = firstMatch(CIBLE, html);
        // La cible est une graduation INTERNE (jamais une extrémité).
        // La cible est une graduation INTERNE (jamais une extremite « 0 » / « 1 »).
        assertThat(cible).isNotIn("0", "1");

        mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", cible))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bravo")));
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
