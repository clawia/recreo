package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0011 : reproduit exactement le scenario de nadia-prof (coup joue sans partie en cours,
 * squad/conformite/FICHE-bandit-manchot-additions-cp.md, motif de refus 3) et verifie que
 * l'enfant recoit un fragment HTML francais, jamais le corps technique brut d'une erreur.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class ErreurDeSessionPerdueTest {

    private static final String JEU_ID = "un-jeu";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unCoupJoueSansPartieEnCoursAfficheUnEcranDErreurFrancais() throws Exception {
        MockHttpSession session = new MockHttpSession();

        var reponse = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        assertThat(reponse.getContentType()).startsWith(MediaType.TEXT_HTML_VALUE);

        String html = reponse.getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.text()).contains("Oups ! Le jeu a un souci.");
        assertThat(fragment.select("a[href='/']")).isNotEmpty();
        assertThat(fragment.select("a[href='/']").text()).isEqualTo("Recommencer");
        assertThat(html).doesNotContain("\"status\"", "\"timestamp\"", "Internal Server Error");
    }

    @Test
    void demarrerUnJeuInconnuAfficheUnEcranDErreurFrancais() throws Exception {
        var reponse = mockMvc.perform(post("/parties/{jeuId}", "jeu-inconnu")).andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        String html = reponse.getContentAsString();

        assertThat(Jsoup.parse(html).text()).contains("Oups ! Le jeu a un souci.");
        assertThat(html).doesNotContain("\"status\"", "\"timestamp\"", "Internal Server Error");
    }
}
