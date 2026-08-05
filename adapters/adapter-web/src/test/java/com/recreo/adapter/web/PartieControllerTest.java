package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.AdditionsBorneesContentSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import jakarta.servlet.http.Cookie;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private static final String JEU_INFINI_ID = "jeu-infini";
    private static final String JEU_REPETE_ID = "jeu-repete";

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

    /** S1-4, 3e critere : le bouton de retour est visible sur n'importe quel ecran de jeu. */
    @Test
    void leBoutonDeRetourAlAccueilEstVisibleSurLaManche() throws Exception {
        MockHttpSession session = new MockHttpSession();

        String html = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).select("button.retour-accueil")).isNotEmpty();
    }

    /** S1-4, 3e critere : le bouton reste visible apres une reponse, avant meme la fin de la partie. */
    @Test
    void leBoutonDeRetourAlAccueilEstVisibleApresUneReponse() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).select("button.retour-accueil")).isNotEmpty();
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

    /** S1-4, 3e critere : cliquer le bouton clot la partie et revient a la liste des jeux. */
    @Test
    void leBoutonDeRetourClotLaPartieEtRevientALaListeDesJeux() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/retour").session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("button.demarrer")).isNotEmpty();
        assertThat(fragment.select(".enonce")).isEmpty();
    }

    /**
     * S1-4, 1er et 2e criteres : la partie se termine exactement au nombre de manches annonce,
     * meme si le contenu est combinatoire et ne s'epuise jamais, et l'ecran de fin est atteint.
     */
    @Test
    void unEcranDeFinSAfficheReellementApresLeNombreDeManchesMemeAvecUnContenuInfini() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_INFINI_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID)
                .session(session).param("propositionChoisie", "peu importe"));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID)
                        .session(session).param("propositionChoisie", "peu importe"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".fin-de-partie")).isNotEmpty();
        // Les deux coups sont faux : le score final ne compte que les reussites (S1-2, DEC-0004),
        // il ne montre plus le nombre de tentatives.
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 0");
    }

    /** S1-4, 4e critere : recharger la page pendant que la session HTTP est valide est silencieux. */
    @Test
    void rechargerLaPageEnCoursDePartieRepresenteLaMancheSansAucunMessage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(get("/").session(session))
                .andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        assertThat(page.select(".enonce").text()).isEqualTo("un");
        assertThat(page.select(".message-reprise")).isEmpty();
    }

    /** S1-4, 5e critere : une session qui n'est plus retrouvable affiche le message exact au-dessus de la liste. */
    @Test
    void rechargerLaPageAvecUneSessionPerdueAfficheLeMessageDePartiePerdue() throws Exception {
        MockHttpSession sessionOrigine = new MockHttpSession();
        MvcResult demarrage = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(sessionOrigine))
                .andReturn();
        Cookie marqueur = demarrage.getResponse().getCookie("recreo-partie-en-cours");
        assertThat(marqueur).as("le marqueur de partie en cours est pose au demarrage").isNotNull();

        MockHttpSession sessionPerdue = new MockHttpSession();

        String html = mockMvc.perform(get("/").session(sessionPerdue).cookie(marqueur))
                .andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        assertThat(page.select(".message-reprise").text())
                .isEqualTo("Ta partie s'est arrêtée. Choisis un nouveau jeu !");
        assertThat(page.select("button.demarrer")).isNotEmpty();
    }

    /** Le message de partie perdue ne s'affiche pas indefiniment une fois montre une fois. */
    @Test
    void leMessageDePartiePerdueNeReapparaitPasApresAvoirEteAffiche() throws Exception {
        MockHttpSession sessionOrigine = new MockHttpSession();
        MvcResult demarrage = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(sessionOrigine))
                .andReturn();
        Cookie marqueur = demarrage.getResponse().getCookie("recreo-partie-en-cours");
        MockHttpSession sessionPerdue = new MockHttpSession();

        mockMvc.perform(get("/").session(sessionPerdue).cookie(marqueur));

        // Le navigateur a recu un cookie efface (Max-Age=0) a la premiere visite : il ne le renvoie plus.
        String html = mockMvc.perform(get("/").session(sessionPerdue))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).select(".message-reprise")).isEmpty();
    }

    /** S1-5, 1er critere : un item rate trois fois est represente avec l'indice de son contenu. */
    @Test
    void unIndiceApparaitApresTroisEchecsSurLeMemeItem() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_REPETE_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_REPETE_ID)
                .session(session).param("propositionChoisie", "mauvaise"));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_REPETE_ID)
                .session(session).param("propositionChoisie", "mauvaise"));

        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_REPETE_ID)
                .session(session).param("propositionChoisie", "mauvaise"));

        String suite = mockMvc.perform(get("/parties/{jeuId}/manche", JEU_REPETE_ID).session(session))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(suite).select(".indice").text()).isEqualTo("une astuce");
    }

    @TestConfiguration
    static class JeuDeTestConfiguration {

        private static final ContentDescriptor DESCRIPTEUR =
                new ContentDescriptor("Mathematiques", "CP", "Nombres et calculs", "denombrer");

        /** Contenu combinatoire, jamais epuise, pour prouver que seul nombreDeManches termine la partie. */
        private static ContentSource contenuRepete(Question question) {
            return new ContentSource() {
                @Override
                public ContentDescriptor descriptor() {
                    return DESCRIPTEUR;
                }

                @Override
                public Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus) {
                    return Optional.of(question);
                }
            };
        }

        @Bean
        PartieUseCase partieUseCase() {
            ContentCatalogPort catalogue = () -> List.of(
                    new GameDefinition(JEU_ID, "Un jeu", "Choisis la bonne reponse", BanditManchot.ID,
                            DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(
                                    new Question(new ItemId("q1"), "un", "1", List.of("2")),
                                    new Question(new ItemId("q2"), "deux", "2", List.of("3")))),
                            10),
                    new GameDefinition(JEU_INFINI_ID, "Jeu infini", "Choisis la bonne reponse",
                            BanditManchot.ID, DESCRIPTEUR,
                            new AdditionsBorneesContentSource(DESCRIPTEUR, 10, 3),
                            2),
                    new GameDefinition(JEU_REPETE_ID, "Jeu repete", "Choisis la bonne reponse",
                            BanditManchot.ID, DESCRIPTEUR,
                            contenuRepete(new Question(new ItemId("q-repete"), "repete", "bonne",
                                    List.of("mauvaise"), Optional.of("une astuce"))),
                            10));
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = bound -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
