package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mecanique « associer / classer » (5e mecanique, ilot JS, cf. ADR-0016) : une entree
 * {@code vivant-non-vivant-cp} rend une reserve d'elements {@code .assoc-item}
 * ({@code data-value}) a classer dans des zones-cibles {@code .assoc-slot}
 * ({@code data-target}). Contrairement au memory, la bonne reponse n'est PAS revelee dans le
 * DOM : on teste donc le flux JOKER avec une soumission volontairement FAUSSE, robuste quel
 * que soit le contenu — on assigne TOUS les items au MEME target (le premier). Comme le
 * puzzle utilise 2 categories, au moins un item est mal classe -> le coup entier est faux :
 * « Presque » + session ACTIVE. Depart = 3 coeurs, donc il faut 4 coups faux : les 3 premiers
 * laissent la session active, le 4e affiche l'ecran de fin ({@code class="game-over"}).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AssocierWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    private static final Pattern ITEM = Pattern.compile("class=\"assoc-item\"[^>]*data-value=\"([^\"]+)\"");
    private static final Pattern TARGET = Pattern.compile("class=\"assoc-slot\"[^>]*data-target=\"([^\"]+)\"");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void wrongAssignmentKeepsTheSessionActiveThenEndsAtTheFourthMistake() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "vivant-non-vivant-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        // 4 coups FAUX d'affilee : les 3 premiers laissent la session active (coeur consomme),
        // le 4e (0 coeur) termine la partie -> ecran de fin.
        for (int attempt = 1; attempt <= 4; attempt++) {
            List<String> items = allMatches(ITEM, html);
            List<String> targets = allMatches(TARGET, html);
            assertThat(items).as("des elements a associer sont presents").isNotEmpty();
            assertThat(targets.size()).as("au moins deux categories utilisees").isGreaterThanOrEqualTo(2);

            // Assignation FORCEMENT FAUSSE : tous les items sur le PREMIER target.
            String firstTarget = targets.get(0);
            List<String> assocTargets = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                assocTargets.add(firstTarget);
            }

            html = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                            .param("assocItem", items.toArray(new String[0]))
                            .param("assocTarget", assocTargets.toArray(new String[0])))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            if (attempt < 4) {
                assertThat(html).as("coup faux %d : retente", attempt).contains("Presque");
                assertThat(html).doesNotContain("class=\"game-over\"");
                assertThat(html).doesNotContain("Rejouer");
            } else {
                assertThat(html).as("4e coup faux : ecran de fin").contains("class=\"game-over\"");
                assertThat(html).contains("Rejouer");
            }
        }
    }

    private List<String> allMatches(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        List<String> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return values;
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
