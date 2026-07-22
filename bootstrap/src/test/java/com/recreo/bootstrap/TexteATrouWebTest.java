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

    private static final Pattern PHRASE = Pattern.compile("phrase-a-trou\"[^>]*>(.*?)</p>", Pattern.DOTALL);
    private static final Pattern INT = Pattern.compile("\\d+");

    @Test
    void texteATrouSessionReachesTheEndScreen() throws Exception {
        // Session courte (cible = 5). Depuis les jokers (DEC-0009), une reponse FAUSSE ne fait
        // plus avancer : pour ATTEINDRE la cible on complete correctement l'egalite "a + ___ = c"
        // (reponse = c - a), calculee a partir de la phrase affichee.
        MvcResult start = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "egalite-cp")
                        .param("questionsTarget", "5"))
                .andExpect(status().isOk())
                .andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        for (int i = 0; i < 5; i++) {
            html = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                            .param("chosenAnswer", egaliteAnswer(html)))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        }

        // Ecran de fin mutualise : Bravo + Rejouer ; plus de mots a cliquer.
        assertThat(html).contains("Bravo");
        assertThat(html).contains("Rejouer");
        assertThat(html).doesNotContain("class=\"word-btn\"");
    }

    /**
     * Calcule le complement "a + ___ = c" -> c - a a partir de la phrase affichee. On isole le
     * bloc {@code .phrase-a-trou} (pour ne pas capter un chiffre du <head>, ex. viewport), on
     * retire les balises et on lit les deux entiers "a" et "c" (le trou vaut "?", pas un chiffre).
     */
    private String egaliteAnswer(String html) {
        Matcher pm = PHRASE.matcher(html);
        assertThat(pm.find()).as("bloc phrase-a-trou").isTrue();
        String text = pm.group(1).replaceAll("<[^>]*>", " ");
        Matcher im = INT.matcher(text);
        java.util.List<Integer> nums = new java.util.ArrayList<>();
        while (im.find()) {
            nums.add(Integer.parseInt(im.group()));
        }
        assertThat(nums).as("les deux operandes a et c").hasSize(2);
        return String.valueOf(nums.get(1) - nums.get(0));
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
