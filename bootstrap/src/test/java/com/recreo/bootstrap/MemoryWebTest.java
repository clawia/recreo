package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mecanique « memory » (6e mecanique, ilot JS, cf. ADR-0016) : une entree
 * {@code memory-quantite-cp} rend une grille de cartes {@code .memory-carte} portant leur
 * groupe de paire {@code data-group} (ex. {@code 0g},{@code 0d}) et leur valeur
 * {@code data-value}. Le memory EXPOSE les paires via {@code data-group} (necessaire au JS),
 * donc le test PEUT reconstituer la bonne reponse : la SOLUTION COMPLETE (item = valeur du
 * cote « g », cible = valeur du cote « d », appariee par index) score « Bravo » et, comme un
 * plateau = 1 item (cible de session = 1), termine la session (ecran de fin). Une soumission
 * FAUSSE (deux cotes « droite » echanges) donne « Presque » et laisse la session ACTIVE (un
 * coeur consomme, pas d'ecran de fin).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MemoryWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    private static final Pattern CARTE = Pattern.compile("<div class=\"memory-carte\"([^>]*)>");
    private static final Pattern DATA_GROUP = Pattern.compile("data-group=\"([^\"]+)\"");
    private static final Pattern DATA_VALUE = Pattern.compile("data-value=\"([^\"]+)\"");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void completeSolutionScoresAndEndsTheSession() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "memory-quantite-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        List<String> items = new ArrayList<>();
        List<String> targets = new ArrayList<>();
        pairs(html, items, targets);
        assertThat(items).as("le memory expose au moins une paire").isNotEmpty();

        String after = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("assocItem", items.toArray(new String[0]))
                        .param("assocTarget", targets.toArray(new String[0])))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Un plateau = 1 item, cible de session = 1 : la bonne solution termine la partie.
        assertThat(after).contains("Bravo");
        assertThat(after).contains("class=\"game-over\"");
        assertThat(after).contains("Rejouer");
        assertThat(after).doesNotContain("class=\"memory-carte\"");
    }

    @Test
    void wrongSolutionKeepsTheSessionActiveAndConsumesOneHeart() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "memory-quantite-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        List<String> items = new ArrayList<>();
        List<String> targets = new ArrayList<>();
        pairs(html, items, targets);
        assertThat(targets.size()).as("au moins deux paires pour echanger deux droites")
                .isGreaterThanOrEqualTo(2);

        // Echange deux cotes « droite » : au moins deux items sont mal apparies -> coup faux.
        List<String> wrongTargets = new ArrayList<>(targets);
        java.util.Collections.swap(wrongTargets, 0, 1);

        String after = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("assocItem", items.toArray(new String[0]))
                        .param("assocTarget", wrongTargets.toArray(new String[0])))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(after).contains("Presque");
        assertThat(after).doesNotContain("class=\"game-over\"");
        assertThat(after).doesNotContain("Rejouer");
        // Session encore active : la grille est toujours la (un coeur a ete consomme).
        assertThat(after).contains("class=\"memory-carte\"");
    }

    /**
     * Reconstruit les paires attendues depuis le DOM : chaque {@code .memory-carte} porte
     * {@code data-group} (« &lt;index&gt;&lt;cote&gt; », ex. {@code 0g}/{@code 0d}) et
     * {@code data-value}. Pour chaque index, l'item = valeur du cote « g », la cible = valeur
     * du cote « d ». Remplit {@code items}/{@code targets} PARALLELES, tries par index.
     */
    private void pairs(String html, List<String> items, List<String> targets) {
        Map<String, String> byGroup = new LinkedHashMap<>();
        Matcher carte = CARTE.matcher(html);
        while (carte.find()) {
            String attrs = carte.group(1);
            String group = attributeIn(DATA_GROUP, attrs);
            String value = attributeIn(DATA_VALUE, attrs);
            byGroup.put(group, value);
        }
        int index = 0;
        while (byGroup.containsKey(index + "g") && byGroup.containsKey(index + "d")) {
            items.add(byGroup.get(index + "g"));
            targets.add(byGroup.get(index + "d"));
            index++;
        }
    }

    private String attributeIn(Pattern pattern, String attrs) {
        Matcher matcher = pattern.matcher(attrs);
        assertThat(matcher.find()).as("attribut introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
