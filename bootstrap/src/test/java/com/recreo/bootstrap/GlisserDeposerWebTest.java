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
        List<String> correct = correctOrder(html); // sens lu dans la consigne (aleatoire par item)

        mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("arrangement", correct.toArray(new String[0])))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bravo")))
                .andExpect(content().string(not(containsString("class=\"machine\""))));
    }

    @Test
    void wrongArrangementKeepsWellPlacedTilesLockedAndReturnsTheRest() throws Exception {
        // UX "ranger" (DEC-0008) : un arrangement faux ne fait PAS avancer -> meme puzzle,
        // pas d'ecran de fin. Nouveaute : les tuiles DEJA bien placees restent VERROUILLEES
        // dans leur emplacement ; seules les mal placees repartent en reserve. La bonne
        // reponse n'est PAS revelee (on ne montre que les positions reussies par l'enfant).
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "range-nombres-cp"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        // Arrangement AVEC des positions correctes ET des fausses : on part de l'ordre trie
        // (correct) puis on echange les DEUX DERNIERES tuiles -> les premieres positions
        // restent correctes (verrouillees), les deux dernieres sont fausses (reserve).
        List<String> correct = correctOrder(html);
        assertThat(correct.size()).as("puzzle avec au moins 3 tuiles").isGreaterThanOrEqualTo(3);
        List<String> attempt = new ArrayList<>(correct);
        java.util.Collections.swap(attempt, attempt.size() - 1, attempt.size() - 2);

        String after = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("arrangement", attempt.toArray(new String[0])))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(after).contains("Presque");
        assertThat(after).contains("class=\"dnd-slot\"");
        assertThat(after).doesNotContain("Rejouer");         // pas d'ecran de fin
        // Meme puzzle : les memes nombres sont reproposes (verrouilles + reserve).
        assertThat(tilesSorted(after)).isEqualTo(tilesSorted(html));

        // La 1re valeur de l'ordre correct est bien placee -> dans un emplacement rempli.
        String firstValue = correct.get(0);
        Pattern lockedSlot = Pattern.compile(
                "class=\"dnd-slot\">\\s*<div class=\"dnd-tuile\"[^>]*data-value=\"" + firstValue + "\"");
        assertThat(lockedSlot.matcher(after).find())
                .as("1re valeur de l'ordre correct verrouillee dans un .dnd-slot").isTrue();

        // Les deux tuiles echangees (mal placees) repartent en RESERVE (.dnd-tuiles).
        String reserve = reserveBlock(after);
        String lastValue = correct.get(correct.size() - 1);
        String beforeLastValue = correct.get(correct.size() - 2);
        assertThat(reserve).contains("data-value=\"" + lastValue + "\"");
        assertThat(reserve).contains("data-value=\"" + beforeLastValue + "\"");
    }

    /** Contenu de la reserve (.dnd-tuiles), coupe avant les emplacements (.dnd-slots). */
    private String reserveBlock(String html) {
        Matcher m = Pattern.compile("class=\"dnd-tuiles\">(.*?)<div class=\"dnd-slots\">",
                Pattern.DOTALL).matcher(html);
        assertThat(m.find()).as("bloc reserve .dnd-tuiles introuvable").isTrue();
        return m.group(1);
    }

    @Test
    void sessionReachesTheEndScreenWithCorrectArrangements() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "range-nombres-cp")
                        .param("questionsTarget", "5"))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        String lastHtml = null;
        for (int i = 0; i < 5; i++) {
            // Chaque puzzle est resolu correctement (ordre selon la consigne) -> on avance.
            List<String> correct = correctOrder(html);
            lastHtml = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                            .param("arrangement", correct.toArray(new String[0])))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            html = lastHtml;
        }

        assertThat(lastHtml).contains("Rejouer");
        assertThat(lastHtml).contains("class=\"game-over\"");
        assertThat(lastHtml).doesNotContain("class=\"dnd-slot\"");
    }

    /** Valeurs des tuiles triées croissant (comparaison d'ENSEMBLE, indépendante du sens). */
    private List<String> tilesSorted(String html) {
        List<Integer> values = tileValues(html);
        values.sort(Comparator.naturalOrder());
        return values.stream().map(String::valueOf).toList();
    }

    /**
     * L'ordre ATTENDU pour ce puzzle, déduit de la consigne (le sens est aléatoire par
     * question) : croissant si « du plus petit au plus grand », sinon décroissant.
     */
    private List<String> correctOrder(String html) {
        List<Integer> values = tileValues(html);
        Comparator<Integer> order = html.contains("du plus petit au plus grand")
                ? Comparator.naturalOrder()
                : Comparator.reverseOrder();
        values.sort(order);
        return values.stream().map(String::valueOf).toList();
    }

    private List<Integer> tileValues(String html) {
        Matcher m = TILE.matcher(html);
        List<Integer> values = new ArrayList<>();
        while (m.find()) {
            String v = m.group(1) != null ? m.group(1) : m.group(2);
            values.add(Integer.parseInt(v));
        }
        assertThat(values).as("tuiles data-value trouvées").isNotEmpty();
        return values;
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
