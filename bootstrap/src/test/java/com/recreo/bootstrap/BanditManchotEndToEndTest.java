package com.recreo.bootstrap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Prouve le decouplage a l'execution : la MEME {@code BanditManchotMechanic} sert
 * CP puis CM1 (test parametre), de bout en bout sur H2, via les fragments HTML.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BanditManchotEndToEndTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    private static final Pattern CHOSEN_ANSWER = Pattern.compile("name=\"chosenAnswer\" value=\"([^\"]+)\"");

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {"calcul-mental-cp", "calcul-mental-cm1"})
    void playsAStartThenAMoveThroughTheSharedMechanic(String catalogEntryId) throws Exception {
        // Le catalogue liste bien l'entree.
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Recreo")));

        // Demarrage : fragment de jeu avec un enonce et des options.
        MvcResult startResult = mockMvc.perform(post("/sessions").param("catalogEntryId", catalogEntryId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("= ?")))
                .andExpect(content().string(containsString("data-session-id")))
                // Regression : au demarrage la session n'est PAS terminee -> pas d'ecran de fin.
                // (marqueur unique a l'ecran de fin : "Rejouer" ; "Bravo" apparait aussi dans le
                //  message de bonne reponse.)
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Rejouer"))))
                .andReturn();

        String startHtml = startResult.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, startHtml);
        String chosenAnswer = firstMatch(CHOSEN_ANSWER, startHtml);
        assertThat(sessionId).isNotBlank();
        assertThat(chosenAnswer).isNotBlank();

        // Un coup : renvoie le fragment resultat + round suivant.
        mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", chosenAnswer))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Score")))
                .andExpect(content().string(containsString("Manche")))
                // Regression : cible par defaut (20) non atteinte apres 1 coup -> pas d'ecran de fin.
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Rejouer"))));
    }

    private static final Pattern REEL = Pattern.compile("class=\"reel\"[^>]*data-value=\"([^\"]+)\"");

    @org.junit.jupiter.api.Test
    void reachingTheTargetShowsTheEndOfSessionScreen() throws Exception {
        // Session courte (cible = 5 questions). Depuis les jokers (DEC-0009), une reponse
        // FAUSSE ne fait plus avancer (elle consomme un coeur) : pour ATTEINDRE la cible on
        // soumet les BONNES reponses, calculees a partir de l'equation affichee sur les rouleaux.
        MvcResult start = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "calcul-mental-cp")
                        .param("questionsTarget", "5"))
                .andExpect(status().isOk())
                .andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        for (int i = 0; i < 5; i++) {
            html = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                            .param("chosenAnswer", correctAnswer(html)))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        }

        // Ecran de fin (Lea) : Bravo, score, Rejouer ; plus de rouleaux ni de reponses.
        assertThat(html).contains("Bravo");
        assertThat(html).contains("Rejouer");
        assertThat(html).doesNotContain("BANDIT MANCHOT");
        // 5 bonnes reponses : score = 50 / score max = 50 (5 questions x 10 pts).
        assertThat(html).contains("<b>50</b>");
        assertThat(html).contains("<span>50</span>");
    }

    /** Calcule la bonne reponse "a op b" a partir des trois rouleaux affiches. */
    private String correctAnswer(String html) {
        Matcher m = REEL.matcher(html);
        java.util.List<String> reels = new java.util.ArrayList<>();
        while (m.find()) {
            reels.add(m.group(1));
        }
        assertThat(reels).as("les trois rouleaux de l'equation").hasSize(3);
        int a = Integer.parseInt(reels.get(0));
        String op = reels.get(1);
        int b = Integer.parseInt(reels.get(2));
        int result = switch (op) {
            case "+" -> a + b;
            case "×" -> a * b;
            default -> a - b; // "-"
        };
        return String.valueOf(result);
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
