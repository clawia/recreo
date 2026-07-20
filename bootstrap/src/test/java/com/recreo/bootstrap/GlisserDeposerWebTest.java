package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mécanique « glisser-déposer » (1er îlot JS, cf. ADR-0013) : une entrée
 * {@code range-nombres-cp} rend la vue glisser-déposer (tuiles + emplacements, PAS de
 * machine à sous) ; soumettre l'arrangement TRIÉ score « Bravo », un arrangement faux
 * donne « Presque » ; le mode session (écran de fin) fonctionne comme les autres mécaniques.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GlisserDeposerWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    private static final Pattern TILE = Pattern.compile("class=\"dnd-tuile\"[^>]*data-value=\"(\\d+)\"|data-value=\"(\\d+)\"[^>]*class=\"dnd-tuile\"");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startingRangeNombresRendersTheDragDropView() throws Exception {
        mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "range-nombres-cp")
                        .param("from", "/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<!DOCTYPE html>")))
                .andExpect(content().string(containsString("id=\"game-area\"")))
                .andExpect(content().string(containsString("class=\"glisser-deposer\"")))
                .andExpect(content().string(containsString("class=\"dnd-slot\"")))
                .andExpect(content().string(containsString("Range les nombres")))
                // PAS de machine a sous.
                .andExpect(content().string(not(containsString("class=\"machine\""))));
    }

    @Test
    void sortedArrangementScores() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "range-nombres-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);
        List<String> sorted = tilesSorted(html);

        mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("arrangement", sorted.toArray(new String[0])))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bravo")))
                .andExpect(content().string(not(containsString("class=\"machine\""))));
    }

    @Test
    void wrongArrangementIsRejected() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "range-nombres-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);
        List<String> descending = tilesSorted(html);
        java.util.Collections.reverse(descending); // ordre décroissant : faux (4 nombres distincts)

        mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("arrangement", descending.toArray(new String[0])))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Presque")));
    }

    @Test
    void sessionReachesTheEndScreen() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "range-nombres-cp")
                        .param("questionsTarget", "5"))
                .andExpect(status().isOk()).andReturn();
        String sessionId = firstMatch(SESSION_ID, start.getResponse().getContentAsString());

        String lastHtml = null;
        for (int i = 0; i < 5; i++) {
            lastHtml = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                            .param("arrangement", "1", "2", "3", "4"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        }

        assertThat(lastHtml).contains("Rejouer");
        assertThat(lastHtml).contains("class=\"game-over\"");
        assertThat(lastHtml).doesNotContain("class=\"dnd-slot\"");
    }

    /** Extrait les valeurs des tuiles et les trie numériquement (= l'ordre attendu). */
    private List<String> tilesSorted(String html) {
        Matcher m = TILE.matcher(html);
        List<Integer> values = new ArrayList<>();
        while (m.find()) {
            String v = m.group(1) != null ? m.group(1) : m.group(2);
            values.add(Integer.parseInt(v));
        }
        assertThat(values).as("tuiles data-value trouvées").isNotEmpty();
        values.sort(Comparator.naturalOrder());
        List<String> sorted = new ArrayList<>();
        for (int v : values) {
            sorted.add(String.valueOf(v));
        }
        return sorted;
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
