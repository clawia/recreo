package com.recreo.bootstrap;

import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.bootstrap.catalogue.CatalogueDeTest;
import com.recreo.domain.content.RandomSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Parcours HTTP de bout en bout de S0-2, avec un catalogue de test (aucun paquet de contenu reel
 * n'existe encore, ADR-0007). Le contexte Spring complet demarre : c'est aussi la preuve
 * d'integration exigee par S0-1. Un test par critere d'acceptation, au minimum.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(PartieHttpTest.CatalogueDeTestConfiguration.class)
class PartieHttpTest {

    @Autowired
    private MockMvc mockMvc;

    /** Critere 1 : demarrer une partie sans compte ni information personnelle. */
    @Test
    void lAccueilPermetDeDemarrerUnePartieSansCompte() throws Exception {
        String accueil = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(accueil);
        assertThat(page.select("button.demarrer")).isNotEmpty();
        assertThat(page.select("input[type=text], input[type=email], input[name=nom]")).isEmpty();

        MockHttpSession session = new MockHttpSession();
        String manche = mockMvc.perform(post("/parties/{jeuId}", CatalogueDeTest.JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(manche);
        assertThat(fragment.select(".enonce").text()).isNotBlank();
        assertThat(fragment.select(".proposition")).isNotEmpty();
    }

    /** Critere 2 : reponse correcte -> retour correct et score +1. */
    @Test
    void uneReponseCorrecteDonneUnRetourCorrectEtAvanceLeScore() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", CatalogueDeTest.JEU_ID).session(session));

        String retour = mockMvc.perform(post("/parties/{jeuId}/coups", CatalogueDeTest.JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();

        Document fragment = Jsoup.parse(retour);
        assertThat(fragment.select(".signal.est-juste")).isNotEmpty();
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 1");
    }

    /** Critere 3 : reponse incorrecte -> retour incorrect qui designe la bonne reponse, score inchange. */
    @Test
    void uneReponseIncorrecteDesigneLaBonneReponseEtNAvancePasLeScore() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", CatalogueDeTest.JEU_ID).session(session));

        String retour = mockMvc.perform(post("/parties/{jeuId}/coups", CatalogueDeTest.JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();

        Document fragment = Jsoup.parse(retour);
        assertThat(fragment.select(".signal.est-faux")).isNotEmpty();
        assertThat(fragment.text()).contains("1");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 0");
        assertThat(retour).doesNotContain("data-correct", "data-bonne", "data-solution", "data-reponse");
    }

    /** Critere 4 : la manche suivante s'affiche et le score cumule reste visible. */
    @Test
    void laMancheSuivanteAfficheUneNouvelleQuestionEtLeScoreCumule() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", CatalogueDeTest.JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", CatalogueDeTest.JEU_ID)
                .session(session).param("propositionChoisie", "1"));

        String suite = mockMvc.perform(get("/parties/{jeuId}/manche", CatalogueDeTest.JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();

        Document fragment = Jsoup.parse(suite);
        assertThat(fragment.select(".enonce").text()).isEqualTo("deux");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 1");
    }

    @TestConfiguration
    static class CatalogueDeTestConfiguration {

        @Bean
        ContentCatalogPort catalogueDeTest() {
            return new CatalogueDeTest();
        }

        /** Tirage rejouable : chaque assertion sur l'enonce ou la reponse reste deterministe. */
        @Bean
        RandomSource hasard() {
            return bound -> 0;
        }
    }
}
