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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Jokers « 3 coeurs » (cf. DEC-0009) sur un jeu a clic (bandit manchot, {@code calcul-mental-cp}) :
 * a chaque reponse FAUSSE, s'il reste un coeur, on RETENTE le meme item (feedback « Presque »,
 * session ACTIVE, coeurs affiches) ; a 0 coeur, la reponse fausse termine la session (ecran de
 * fin). Depart = 3 coeurs, donc les 3 premieres reponses fausses laissent la session active et
 * la 4e affiche l'ecran de fin ({@code class="game-over"}). On soumet une valeur volontairement
 * hors options ({@code chosenAnswer=zzz}) : toujours fausse, robuste quel que soit le contenu.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JokersWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void threeWrongClicksRetryThenTheFourthEndsTheSession() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", "calcul-mental-cp"))
                .andExpect(status().isOk()).andReturn();
        String sessionId = firstMatch(SESSION_ID, start.getResponse().getContentAsString());

        // 3 reponses fausses : retente a chaque fois (coeur consomme, session active).
        for (int i = 1; i <= 3; i++) {
            String html = wrongClick(sessionId);
            assertThat(html).as("coup faux %d : retente", i).contains("Presque");
            assertThat(html).doesNotContain("class=\"game-over\"");
            assertThat(html).doesNotContain("Rejouer");
            // Les coeurs (jokers) restants sont affiches dans le scoreboard.
            assertThat(html).contains("class=\"lives\"");
        }

        // 4e reponse fausse (0 coeur) : la partie se termine -> ecran de fin.
        String end = wrongClick(sessionId);
        assertThat(end).contains("class=\"game-over\"");
        assertThat(end).contains("Rejouer");
    }

    private String wrongClick(String sessionId) throws Exception {
        return mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", "zzz"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
