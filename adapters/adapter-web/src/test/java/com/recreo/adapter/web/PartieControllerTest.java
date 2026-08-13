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
import org.jsoup.select.Elements;
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
    private static final String JEU_QUATRE_PROPOSITIONS_ID = "jeu-quatre-propositions";

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

    /**
     * M7 : l'explication n'apparait qu'apres que la reponse est acquise, jamais sur le chemin juste
     * - meme quand l'item joue en porte une (perte assumee par nadia-prof, reserve B de son
     * arbitrage de mecanique : l'ancienne regle d'ADR-0024, "systematiquement, juste ou faux", ne
     * peut plus etre tenue sans l'ecran de retour intermediaire).
     */
    @Test
    void lExplicationDisparaitSurLeCheminJusteMemeQuandLItemEnPorteUne() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        MvcResult juste = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1")).andReturn();

        assertThat(juste.getModelAndView().getModel())
                .as("q1 porte une explication ; la reussite immediate ne l'affiche pas")
                .containsEntry("explication", null);
    }

    /**
     * M7 : l'explication survit du cote faux, avec la revelation du troisieme echec - c'est la
     * seule place ou "systematiquement, juste ou faux" devient "juste ou faux" tout court.
     */
    @Test
    void lExplicationSurvitDuCoteFauxAvecLaRevelation() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "2"));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "2"));

        MvcResult revelation = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "2")).andReturn();

        assertThat(revelation.getModelAndView().getModel())
                .containsEntry("explication", "explication de q1");
    }

    @Test
    void laMancheSuivanteAfficheUneNouvelleQuestionEtConserveLeScoreCumule() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce").text()).isEqualTo("deux");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 1");
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

    /** M12 : le texte exact d'un echec avec des essais restants, sans reveler la bonne reponse. */
    @Test
    void leMessageDEchecEstLeTexteExactDeNadiaProfEtNeRevelePasLaBonneReponse() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        String verdict = Jsoup.parse(html).select(".verdict").text();

        assertThat(verdict).isEqualTo("Ce n'est pas ça. Essaie encore !");
    }

    /**
     * S1-1 et S1-8 : l'ecran de fin de partie n'est plus d'un registre adulte. S02, reformulation
     * de nadia-prof (FICHE-bandit-manchot-additions-cp.md, verdict REFUSE sur ce libelle) : le
     * message de cloture ne felicite plus inconditionnellement, quel que soit le score.
     */
    @Test
    void lEcranDeFinDePartieNeFeliciteJamaisInconditionnellement() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("form")).isEmpty();
        assertThat(fragment.text()).contains("C'est fini !");
        assertThat(html).doesNotContain("Merci d'avoir", "Bravo, tu as bien joué");
    }

    /**
     * ADR-0019 : le second appui d'un double appui tombe sur une partie deja terminee. Avant cette
     * decision, il rendait "Oups ! Le jeu a un souci." a l'enfant. Il revoit desormais l'ecran de fin
     * de partie que son premier appui venait de produire, score compris. Ce que l'enfant doit voir
     * dans ce cas est une question pedagogique, adressee a nadia-prof ; en attendant son avis, c'est
     * le comportement le moins surprenant, et le seul qui ne perde rien.
     */
    @Test
    void unSecondAppuiSurLaDerniereMancheRevoitLEcranDeFinDePartie() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "2"));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.text()).doesNotContain("Oups ! Le jeu a un souci.");
        assertThat(fragment.select(".fin-de-partie").text()).isEqualTo("C'est fini !");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 2");
    }

    /**
     * S3-6 : sur l'ecran de fin de partie, la sortie est la seule action possible, elle doit donc
     * etre le bouton plein (.sortie-pleine), pas le contour discret de l'ecran de jeu.
     */
    @Test
    void lEcranDeFinDePartieRendLaSortieCommeUnBoutonPlein() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "2"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("button.sortie-pleine")).isNotEmpty();
        assertThat(fragment.select("button.retour-accueil")).isEmpty();
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
     * S1-4, 1er et 2e criteres, M3 : la partie se termine exactement au nombre de manches annonce,
     * meme si le contenu est combinatoire et ne s'epuise jamais, et meme si l'enfant echoue toutes
     * les manches - jamais au milieu d'une question (M3), toujours par la revelation du troisieme
     * echec (M5). Le jeu infini, a hasard fixe, represente toujours la meme addition (0 + 1) : sa
     * bonne reponse, "1", resout chaque manche par revelation.
     */
    @Test
    void unEcranDeFinSAfficheReellementApresLeNombreDeManchesMemeAvecUnContenuInfini() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_INFINI_ID).session(session));

        String html = "";
        for (int manche = 0; manche < 2; manche++) {
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID)
                    .session(session).param("propositionChoisie", "0"));
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID)
                    .session(session).param("propositionChoisie", "0"));
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID)
                    .session(session).param("propositionChoisie", "0"));
            html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID)
                            .session(session).param("propositionChoisie", "1"))
                    .andReturn().getResponse().getContentAsString();
        }
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".fin-de-partie")).isNotEmpty();
        // Les deux manches sont resolues par revelation, jamais par une reussite (M11 clause 3) :
        // le score final ne compte que les reussites, il vaut donc zero.
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

    /**
     * S7-2 : le nombre de colonnes rendues suit exactement le nombre de propositions du contenu,
     * jamais une constante de la mecanique. Trois contenus distincts (deux, trois et quatre
     * propositions) sont confrontes dans le meme test pour qu'aucun des trois ne puisse passer
     * pour la seule valeur que la mecanique sait rendre.
     */
    @Test
    void leNombreDePropositionsRenduSuitExactementLeContenuQuelQueSoitLeNombre() throws Exception {
        assertThat(propositionsRendues(JEU_ID)).hasSize(2);
        assertThat(propositionsRendues(JEU_INFINI_ID)).hasSize(3);
        assertThat(propositionsRendues(JEU_QUATRE_PROPOSITIONS_ID)).hasSize(4);
    }

    private Elements propositionsRendues(String jeuId) throws Exception {
        MockHttpSession session = new MockHttpSession();
        String html = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parse(html).select(".proposition");
    }

    /** M1 : l'indice de la manche bloquante apparait des le premier echec, pas apres trois. */
    @Test
    void unIndiceApparaitDesLePremierEchecSurLaMancheBloquante() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_REPETE_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_REPETE_ID)
                        .session(session).param("propositionChoisie", "mauvaise"))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).select(".indice").text()).isEqualTo("une astuce");
    }

    /**
     * M14 (nadia-prof, arbitrage du 2026-08-13) : sur un appui a cote apres la revelation, la
     * proposition pressee recoit la marque barree de M9, et seulement elle - rien d'autre ne
     * change, alors que {@code BanditManchotSession.jouer} ne l'ajoute pas a
     * {@code propositionsEssayees} pour ce geste precis (voir son javadoc, branche {@code revelee}).
     * "faux1" est presse trois fois pour atteindre la revelation sans jamais essayer "faux2", qui
     * recoit ensuite l'appui a cote que M14 gouverne.
     */
    @Test
    void unAppuiACoteApresLaRevelationMarqueSeulementLaPropositionPresseeCommeDejaEssayee() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_QUATRE_PROPOSITIONS_ID).session(session));

        // M5 : trois echecs sur "faux1", jamais sur "faux2", pour atteindre la revelation en
        // laissant "faux2" non essaye - c'est lui que M14 gouverne ensuite.
        for (int essai = 0; essai < 2; essai++) {
            mockMvc.perform(post("/parties/{jeuId}/coups", JEU_QUATRE_PROPOSITIONS_ID)
                    .session(session).param("propositionChoisie", "faux1"));
        }
        String htmlRevelation = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_QUATRE_PROPOSITIONS_ID)
                        .session(session).param("propositionChoisie", "faux1"))
                .andReturn().getResponse().getContentAsString();
        String messageDeRevelation = Jsoup.parse(htmlRevelation).select(".verdict").text();
        assertThat(messageDeRevelation).isEqualTo("C'est bonne. Appuie sur bonne.");

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_QUATRE_PROPOSITIONS_ID)
                        .session(session).param("propositionChoisie", "faux2"))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("button.proposition[value=faux2]"))
                .as("M14 : la proposition pressee a cote recoit la marque barree de M9")
                .hasSize(1)
                .allMatch(bouton -> bouton.hasClass("deja-essayee"));
        assertThat(fragment.select(".verdict").text())
                .as("M14 : le message ne change pas")
                .isEqualTo(messageDeRevelation);
        assertThat(fragment.select(".score").text()).as("M14 : aucun essai compte").isEqualTo("Score : 0");
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
                                    new Question(new ItemId("q1"), "un", "1", List.of("2"),
                                            Optional.empty(), Optional.of("explication de q1")),
                                    new Question(new ItemId("q2"), "deux", "2", List.of("3")))),
                            10),
                    new GameDefinition(JEU_INFINI_ID, "Jeu infini", "Choisis la bonne reponse",
                            BanditManchot.ID, DESCRIPTEUR,
                            new AdditionsBorneesContentSource(DESCRIPTEUR, 10, 3, "Compte sur tes doigts."),
                            2),
                    new GameDefinition(JEU_REPETE_ID, "Jeu repete", "Choisis la bonne reponse",
                            BanditManchot.ID, DESCRIPTEUR,
                            contenuRepete(new Question(new ItemId("q-repete"), "repete", "bonne",
                                    List.of("mauvaise"), Optional.of("une astuce"))),
                            10),
                    // S7-2 : un contenu a quatre propositions, pour prouver que le rendu ne fige
                    // jamais "trois" comme un invariant de la mecanique.
                    new GameDefinition(JEU_QUATRE_PROPOSITIONS_ID, "Jeu a quatre propositions",
                            "Choisis la bonne reponse", BanditManchot.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(
                                    new Question(new ItemId("q-quatre"), "question", "bonne",
                                            List.of("faux1", "faux2", "faux3")))),
                            10));
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = bound -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
