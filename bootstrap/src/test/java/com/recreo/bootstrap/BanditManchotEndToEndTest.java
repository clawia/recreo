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
                .andExpect(content().string(containsString("Manche")));
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
