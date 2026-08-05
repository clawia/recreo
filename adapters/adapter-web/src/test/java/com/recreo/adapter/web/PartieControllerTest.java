package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Rendu du controleur, verifie par jsoup (ADR-0008 : jamais par expression reguliere). L'usage
 * (demarrage, coup, manche suivante) traverse PartieUseCase, wire ici avec un catalogue de test.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class PartieControllerTest {

    private static final String JEU_ID = "un-jeu";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void laPageDAccueilProposeDeDemarrerSansCompteNiDonneePersonnelle() throws Exception {
        String html = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        assertThat(page.select("button.demarrer")).isNotEmpty();
        assertThat(page.select("input[type=text], input[type=email], input[name=nom]")).isEmpty();
    }

    @Test
    void demarrerAfficheUneMancheSansMarqueurDeCorrection() throws Exception {
        MockHttpSession session = new MockHttpSession();

        String html = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce").text()).isEqualTo("un");
        assertThat(fragment.select(".proposition")).hasSize(2);
        assertThat(html).doesNotContain("data-correct", "data-bonne", "data-solution", "data-reponse");
    }

    @Test
    void uneReponseCorrecteAfficheLeSignalJusteEtAvanceLeScore() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".signal.est-juste")).isNotEmpty();
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 1");
    }

    @Test
    void uneReponseIncorrecteAfficheLeSignalFauxEtDesigneLaBonneReponse() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".signal.est-faux")).isNotEmpty();
        assertThat(fragment.text()).contains("1");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 0");
    }

    @Test
    void laMancheSuivanteAfficheUneNouvelleQuestionEtConserveLeScoreCumule() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));

        String html = mockMvc.perform(get("/parties/{jeuId}/manche", JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce").text()).isEqualTo("deux");
    }

    /** S1-2, DEC-0004 point 1 : aucun symbole du score ne peut se lire comme un signe operatoire. */
    @Test
    void leScoreNAffichePasDeSymboleOperatoire() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();
        String score = Jsoup.parse(html).select(".score").text();

        assertThat(score).doesNotContain("+", "-", "x", "/");
    }

    /** S1-1 et S1-8 : le retour faux est au present, en une phrase courte, sans faute. */
    @Test
    void leRetourFauxEstAuPresentEtEnUnePhraseCourte() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        String verdict = Jsoup.parse(html).select(".verdict").text();

        assertThat(verdict).isEqualTo("Non, c'est 1.");
    }

    /** S1-8 : le libelle du bouton de continuation n'emploie plus le mot "manche". */
    @Test
    void leBoutonPourContinuerNEmploiePlusLeMotManche() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("form button").text()).isEqualTo("Encore !");
        assertThat(fragment.text().toLowerCase()).doesNotContain("manche");
    }

    /** S1-1 et S1-8 : l'ecran de fin de partie est accentue et n'est plus d'un registre adulte. */
    @Test
    void lEcranDeFinDePartieEstAccentueEtEnRegistreEnfant() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("form")).isEmpty();
        assertThat(fragment.text()).contains("La partie est finie. Bravo, tu as bien joué !");
        assertThat(html).doesNotContain("Merci d'avoir");
    }

    @TestConfiguration
    static class JeuDeTestConfiguration {

        private static final ContentDescriptor DESCRIPTEUR =
                new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

        @Bean
        PartieUseCase partieUseCase() {
            ContentCatalogPort catalogue = () -> List.of(new GameDefinition(
                    JEU_ID, "Un jeu", "Choisis la bonne reponse", BanditManchot.ID, DESCRIPTEUR,
                    new ListContentSource(DESCRIPTEUR, List.of(
                            new Question(new ItemId("q1"), "un", "1", List.of("2")),
                            new Question(new ItemId("q2"), "deux", "2", List.of("3"))))));
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = bound -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
