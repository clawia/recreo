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
import com.recreo.domain.content.ExigenceDeForme;
import com.recreo.domain.content.ReponseAEcrire;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.saisie.Saisie;
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
import org.springframework.mock.web.MockHttpServletRequest;
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
    private static final String JEU_MOT_DESIGNE_ID = "jeu-mot-designe";

    /** S24 : le jeu de la seconde mecanique du catalogue de test (voir JeuDeTestConfiguration). */
    static final String JEU_SAISIE_ID = "jeu-saisie";

    /** ADR-0075 : le jeu dont l'item et l'entree de catalogue declarent la famille a voix. */
    private static final String JEU_A_VOIX_ID = "jeu-a-voix";

    /** Le seul niveau du catalogue de test (JeuDeTestConfiguration). */
    private static final String NIVEAU = "CP";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partieUseCase;

    /** Lit l'etat de partie d'une session sans passer par une requete HTTP (ADR-0019). */
    private static EtatDePartie etatDeLaSession(MockHttpSession session) {
        MockHttpServletRequest requete = new MockHttpServletRequest();
        requete.setSession(session);
        return new EtatDePartieEnSession(requete).lire();
    }

    @Test
    void laPageDAccueilProposeDeDemarrerSansCompteNiDonneePersonnelle() throws Exception {
        String html = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        assertThat(page.select("button.demarrer")).isNotEmpty();
        assertThat(page.select("input[type=text], input[type=email], input[name=nom]")).isEmpty();
    }

    /**
     * S14-1, critere 8 (avis-faisabilite de theo-archi, section 7.8) : au premier chargement, la
     * liste affichee est exactement jeuxDisponibles(), sans filtre - aucun effectif ecrit ici.
     */
    @Test
    void auPremierChargementLaListeEstExactementLeCatalogueSansFiltre() throws Exception {
        String html = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        List<String> titresRendus = page.select("button.demarrer").eachText();
        List<String> titresDuCatalogue = partieUseCase.jeuxDisponibles().stream()
                .map(GameDefinition::titre).toList();
        assertThat(titresRendus).containsExactlyElementsOf(titresDuCatalogue);
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

    /**
     * ADR-0062 : un item qui declare une part designee la fait atteindre l'ecran, portee par un
     * element distinct - {@code .mot-designe} - dans le texte exact declare, sans que l'enonce
     * change de valeur pour autant.
     */
    @Test
    void demarrerAfficheUnElementDistinctPourLItemQuiDeclareUnePartDesignee() throws Exception {
        MockHttpSession session = new MockHttpSession();

        String html = mockMvc.perform(post("/parties/{jeuId}", JEU_MOT_DESIGNE_ID).session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce .mot-designe")).hasSize(1);
        assertThat(fragment.select(".enonce .mot-designe").text()).isEqualTo("chat");
        assertThat(fragment.select(".enonce").text()).isEqualTo("Le chat dort");
    }

    /** Le cas ordinaire : un item qui ne declare rien ne porte aucun element de marquage. */
    @Test
    void demarrerNAfficheAucunElementDeMarquagePourUnItemQuiNeDeclareRien() throws Exception {
        MockHttpSession session = new MockHttpSession();

        String html = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce .mot-designe")).isEmpty();
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

    /**
     * S1-4, 3e critere, <b>retournee</b> par S15-3 (troisieme arbitrage de l'utilisatrice,
     * DEC-0034, 2026-08-18). <b>Motif ecrit du retournement, exige par ADR-0013</b> (releve de
     * theo-archi, squad/sprints/s15-cinq-arbitrages-de-l-en-tete/) : ce test affirmait
     * {@code button.retour-accueil} present sur la manche - faux des le retrait de ce bouton de
     * {@code fragments/manche.html}. Son sujet a change d'ecran : l'utilisatrice demande la sortie
     * dans l'en-tete y compris pendant une partie (renversement de l'arbitrage de nadia-prof du
     * 2026-08-07, S6-9), et "l'enfant peut sortir depuis une manche" n'est pas affaiblie mais
     * deplacee - c'est desormais le masquage declaratif qui la garde
     * ({@code LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest}, clauses A, C et D), pas ce fichier. Ce
     * test-ci epingle ce qui reste vrai depuis ce controleur : la manche rendue ne porte plus
     * elle-meme aucune sortie.
     */
    @Test
    void laManchePosteeNePorteAucuneSortieDansSaZone() throws Exception {
        MockHttpSession session = new MockHttpSession();

        String html = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).select("[data-role=sortie]")).isEmpty();
    }

    /** Meme retournement que ci-dessus, apres une reponse : la manche ne re-rend jamais de sortie. */
    @Test
    void laMancheNePorteAucuneSortieDansSaZoneApresUneReponse() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", "1"))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).select("[data-role=sortie]")).isEmpty();
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

    /**
     * ADR-0038, DEC-0026 (story 2, s11) : le bouton de retour ne clot plus la partie d'un seul
     * geste. Sur une partie en cours, il rend l'ecran de confirmation, et la partie survit tant que
     * l'enfant n'a pas confirme.
     */
    @Test
    void leBoutonDeRetourDemandeConfirmationSansClorePartieEnCours() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String html = mockMvc.perform(post("/retour").session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("button.demarrer")).isEmpty();
        assertThat(fragment.select("[data-role=sortie]")).isNotEmpty();

        String reprise = mockMvc.perform(get("/").session(session))
                .andReturn().getResponse().getContentAsString();
        assertThat(Jsoup.parse(reprise).select(".enonce").text())
                .as("la partie n'a pas ete abandonnee par la seule demande de confirmation")
                .isEqualTo("un");
    }

    /**
     * S16-2, critere 3, sens negatif (ADR-0048) : la meme reponse ne declare aucune adresse quand
     * une partie en cours fait rendre la confirmation - sinon l'adresse mentirait sur ce que
     * l'enfant vient de quitter, qui n'est rien.
     */
    @Test
    void laReponseDeRetourNeDeclareAucuneAdresseAvecUnePartieEnCours() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        MvcResult confirmation = mockMvc.perform(post("/retour").session(session)).andReturn();

        assertThat(confirmation.getResponse().getHeader("HX-Push-Url")).isNull();
    }

    /** Confirmer l'abandon depuis l'ecran de confirmation clot bien la partie. */
    @Test
    void confirmerLAbandonClotLaPartieEtRevientALaListeDesJeux() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/retour").session(session));

        String html = mockMvc.perform(post("/retour/confirmer").session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("button.demarrer")).isNotEmpty();
        assertThat(fragment.select(".enonce")).isEmpty();
    }

    /** Annuler l'abandon depuis l'ecran de confirmation revient a la manche, rien n'est perdu. */
    @Test
    void annulerLAbandonRevientALaMancheSansRienPerdre() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));
        mockMvc.perform(post("/retour").session(session));

        String html = mockMvc.perform(post("/retour/annuler").session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce").text()).isEqualTo("deux");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 1");
    }

    /**
     * S14-1, critere 3 (avis-faisabilite de theo-archi, section 7.3) : sans partie en cours, la
     * liste se filtre dans la meme reponse, sans passer par le fragment de confirmation.
     */
    @Test
    void unNiveauSansPartieEnCoursFiltreImmediatementSansPasserParLaConfirmation() throws Exception {
        String html = mockMvc.perform(get("/niveaux/{niveau}", NIVEAU))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".question-confirmation")).isEmpty();
        assertThat(fragment.select("button.demarrer")).isNotEmpty();
    }

    /**
     * S14-1, critere 4, forme renforcee (avis-faisabilite de theo-archi, section 7.4) : la
     * confirmation existante s'affiche avant tout changement, et l'etat lu par
     * EtatDePartieEnSession est identique avant et apres - jeuId et compte de manches resolues
     * compris, jamais une assertion sur le balisage (doctrine ADR-0038 point 3).
     */
    @Test
    void unAppuiSurUnePuceDeNiveauPendantUnePartieNeModifieRienAvantConfirmation() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        EtatDePartie avant = etatDeLaSession(session);

        String html = mockMvc.perform(get("/niveaux/{niveau}", NIVEAU).session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".question-confirmation")).isNotEmpty();
        EtatDePartie apres = etatDeLaSession(session);
        assertThat(apres.jeuId()).isEqualTo(avant.jeuId());
        assertThat(apres.session().score().tentatives()).isEqualTo(avant.session().score().tentatives());
    }

    /**
     * S14-1, critere 5 (avis-faisabilite de theo-archi, section 7.5) : continuer depuis la
     * confirmation ouverte par une puce de niveau reprend exactement la manche affichee.
     * "Aucun filtre applique" est garanti par construction (ADR-0044 point 4 : le niveau ne vit
     * que dans l'URL) et n'a donc rien de plus a asserter ici.
     */
    @Test
    void continuerDepuisLaConfirmationOuverteParUneNiveauReprendExactementLaManche() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(get("/niveaux/{niveau}", NIVEAU).session(session));

        String html = mockMvc.perform(post("/retour/annuler").session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select(".enonce").text()).isEqualTo("un");
        assertThat(fragment.select(".score").text()).isEqualTo("Score : 0");
    }

    /**
     * S14-1, critere 6, "meme comportement" explicite (avis-faisabilite de theo-archi, section
     * 7.6) : confirmer depuis une confirmation ouverte par une puce de niveau abandonne la
     * partie comme un abandon ordinaire (session sans partie en cours, marqueur navigateur
     * efface) et rend l'accueil filtre sur ce niveau.
     */
    @Test
    void confirmerDepuisUneConfirmationDeNiveauAbandonneLaPartieEtRendLaListeFiltree() throws Exception {
        MockHttpSession session = new MockHttpSession();
        MvcResult demarrage = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session))
                .andReturn();
        mockMvc.perform(get("/niveaux/{niveau}", NIVEAU).session(session));

        MvcResult confirmation = mockMvc.perform(
                        post("/niveaux/{niveau}/confirmer", NIVEAU).session(session))
                .andReturn();
        Document fragment = Jsoup.parse(confirmation.getResponse().getContentAsString());

        assertThat(fragment.select("button.demarrer")).isNotEmpty();
        assertThat(etatDeLaSession(session).portePartieEnCours())
                .as("meme comportement qu'un abandon ordinaire : plus de partie en cours")
                .isFalse();
        Cookie marqueurEfface = confirmation.getResponse().getCookie("recreo-partie-en-cours");
        assertThat(marqueurEfface)
                .as("meme comportement qu'un abandon ordinaire : le marqueur navigateur est efface")
                .isNotNull();
        assertThat(marqueurEfface.getMaxAge()).isZero();
        assertThat(demarrage.getResponse().getCookie("recreo-partie-en-cours")).isNotNull();
    }

    /** Sans partie en cours (fin de partie ou accueil), le retour reste immediat, sans confirmation. */
    @Test
    void leRetourApresLaFinDePartieNeDemandeAucuneConfirmation() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "1"));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                .session(session).param("propositionChoisie", "2"));

        String html = mockMvc.perform(post("/retour").session(session))
                .andReturn().getResponse().getContentAsString();
        Document fragment = Jsoup.parse(html);

        assertThat(fragment.select("button.demarrer")).isNotEmpty();
    }

    /**
     * S16-2, critere 1, reecrit par theo-archi (ADR-0048) : sans partie en cours, la reponse de
     * /retour declare, par une en-tete de reponse et non par un attribut du declencheur, l'adresse
     * du catalogue complet qu'elle vient de rendre - la meme branche qui choisit le fragment.
     */
    @Test
    void laReponseDeRetourDeclareLAdresseDuCatalogueCompletSansPartieEnCours() throws Exception {
        MockHttpSession session = new MockHttpSession();

        MvcResult resultat = mockMvc.perform(post("/retour").session(session)).andReturn();

        assertThat(resultat.getResponse().getHeader("HX-Push-Url")).isEqualTo("/");
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
     * S16-1, critere 1, ADR-0047 : la duree de validite declaree du marqueur est egale a la duree
     * d'inactivite maximale de la session HTTP de cette meme requete - lue sur la session jugee,
     * jamais recopiee dans ce test (assertion contre {@code session.getMaxInactiveInterval()}, pas
     * contre un nombre litteral). Dette 8.1 (s17) : une seule duree ne refute pas une constante
     * cablee, qui satisferait l'egalite tout aussi bien - l'epreuve pose donc deux durees
     * differentes et relit le marqueur sur chacune.
     */
    @Test
    void laDureeDeVieDuMarqueurEstLueSurLaSessionEtJamaisRecopiee() throws Exception {
        MockHttpSession premiereSession = new MockHttpSession();
        premiereSession.setMaxInactiveInterval(900);
        MvcResult premierDemarrage =
                mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(premiereSession)).andReturn();
        Cookie premierMarqueur = premierDemarrage.getResponse().getCookie("recreo-partie-en-cours");
        assertThat(premierMarqueur.getMaxAge()).isEqualTo(premiereSession.getMaxInactiveInterval());

        MockHttpSession secondeSession = new MockHttpSession();
        secondeSession.setMaxInactiveInterval(1800);
        MvcResult secondDemarrage =
                mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(secondeSession)).andReturn();
        Cookie secondMarqueur = secondDemarrage.getResponse().getCookie("recreo-partie-en-cours");
        assertThat(secondMarqueur.getMaxAge()).isEqualTo(secondeSession.getMaxInactiveInterval());
    }

    /**
     * S16-1, critere 2, reecrit par theo-archi (ADR-0047) : une requete qui ne porte pas le
     * marqueur, quel qu'en soit le motif, obtient le catalogue complet sans le message - la
     * consequence observable de l'invariant de DEC-0036, sur le cas le plus simple : aucun cookie
     * du tout.
     */
    @Test
    void uneRequeteSansMarqueurObtientLeCatalogueCompletSansLeMessage() throws Exception {
        String html = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        assertThat(page.select(".message-reprise")).isEmpty();
        List<String> titresRendus = page.select("button.demarrer").eachText();
        List<String> titresDuCatalogue = partieUseCase.jeuxDisponibles().stream()
                .map(GameDefinition::titre).toList();
        assertThat(titresRendus).containsExactlyElementsOf(titresDuCatalogue);
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

    /**
     * ADR-0075 section 7, VOI-12 : cable jusqu'a l'ecran reel, par le controleur de production et
     * non par un double de test (a la difference de
     * {@code LeBlocAdulteEstUnSeulObjetContiguATousLesEtatsTest}, qui ne verifie que le gabarit).
     * Point de vigilance 1 du raccord (sofia-sm) : {@code texteAVoixHaute} vient de l'item joue,
     * {@code consigneAdulte} vient de l'entree de catalogue fixee au demarrage - les deux doivent
     * apparaitre ensemble dans le meme document des que le controleur les recoit ensemble.
     */
    @Test
    void leBlocAdulteAtteintLEcranReelQuandLItemEtLeCatalogueDeclarentLaVoix() throws Exception {
        MockHttpSession session = new MockHttpSession();
        String html = mockMvc.perform(post("/parties/{jeuId}", JEU_A_VOIX_ID).session(session))
                .andReturn().getResponse().getContentAsString();
        Document ecran = Jsoup.parse(html);

        Elements bloc = ecran.select(".bloc-adulte");
        assertThat(bloc).hasSize(1);
        assertThat(bloc.select(".stimulus-a-lire").text()).isEqualTo("Le mot est chapeau.");
        assertThat(bloc.select(".consigne-adulte").text())
                .isEqualTo("Dites le mot normalement, sans le couper.");
    }

    /**
     * Point de vigilance 2 du raccord (sofia-sm) : le bloc reste affiche, avec le meme stimulus,
     * apres un echec et sur l'etat de revelation de {@code manche.html} - le chemin que
     * {@code theo-archi} a mesure comme n'etant jamais {@code retour.html}. Le cablage ne doit pas
     * vider le modele au changement d'etat.
     */
    @Test
    void leBlocAdulteResteAfficheApresUnEchecEtSurLaRevelationEcranReel() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_A_VOIX_ID).session(session));

        String htmlEchec = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_A_VOIX_ID)
                        .session(session).param("propositionChoisie", "au milieu"))
                .andReturn().getResponse().getContentAsString();
        Document ecranEchec = Jsoup.parse(htmlEchec);
        assertThat(ecranEchec.select(".bloc-adulte")).hasSize(1);
        assertThat(ecranEchec.select(".bloc-adulte .stimulus-a-lire").text())
                .isEqualTo("Le mot est chapeau.");

        // M5 : deuxieme echec, la manche entre en revelation au troisieme.
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_A_VOIX_ID)
                .session(session).param("propositionChoisie", "au milieu"));
        String htmlRevelation = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_A_VOIX_ID)
                        .session(session).param("propositionChoisie", "au milieu"))
                .andReturn().getResponse().getContentAsString();
        Document ecranRevelation = Jsoup.parse(htmlRevelation);
        assertThat(ecranRevelation.select(".verdict").text())
                .as("la manche doit etre entree en revelation pour que ce test prouve quelque chose")
                .isEqualTo("C'est au debut. Appuie sur au debut.");
        assertThat(ecranRevelation.select(".bloc-adulte"))
                .as("VOI-8 : le bloc-adulte reste affiche sur l'etat de revelation de manche.html")
                .hasSize(1);
        assertThat(ecranRevelation.select(".bloc-adulte .stimulus-a-lire").text())
                .isEqualTo("Le mot est chapeau.");
        assertThat(ecranRevelation.select(".bloc-adulte .consigne-adulte").text())
                .isEqualTo("Dites le mot normalement, sans le couper.");
    }

    /**
     * Regression, sur le controleur reel cette fois (lea-dev l'a deja prouvee sur le gabarit seul,
     * {@code sansTexteAVoixHauteAucunBlocAdulteNApparaitSurAucunFragment}) : un jeu qui ne declare
     * aucune voix ne rend aucun {@code .bloc-adulte}, sur les deux mecaniques du catalogue de test.
     */
    @Test
    void aucunBlocAdulteNApparaitSurUnJeuSansVoixEcranReel() throws Exception {
        MockHttpSession sessionChoix = new MockHttpSession();
        String htmlChoix = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(sessionChoix))
                .andReturn().getResponse().getContentAsString();
        assertThat(Jsoup.parse(htmlChoix).select(".bloc-adulte")).isEmpty();

        MockHttpSession sessionSaisie = new MockHttpSession();
        String htmlSaisie = mockMvc.perform(post("/parties/{jeuId}", JEU_SAISIE_ID).session(sessionSaisie))
                .andReturn().getResponse().getContentAsString();
        assertThat(Jsoup.parse(htmlSaisie).select(".bloc-adulte")).isEmpty();
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

                @Override
                public boolean indicesNommentLaReponse() {
                    return false;
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
                            10),
                    // ADR-0062 : un item qui declare une part designee.
                    new GameDefinition(JEU_MOT_DESIGNE_ID, "Jeu avec mot designe",
                            "Choisis la bonne reponse", BanditManchot.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(
                                    new Question(new ItemId("q-designe"), "Le chat dort", "nom",
                                            List.of("verbe"), Optional.empty(), Optional.empty(),
                                            Optional.of("chat")))),
                            10),
                    // S24 : un jeu de la seconde mecanique. Trois items, donc trois manches
                    // resolues epuisent le vivier et terminent la partie ; le premier declare la
                    // forme libre et porte une forme acceptee, le deuxieme declare la forme exacte -
                    // de quoi exercer SAI-8 et SAI-2c sur le meme jeu - et le troisieme existe pour
                    // qu'un ecran de manche suive la resolution du deuxieme.
                    new GameDefinition(JEU_SAISIE_ID, "Jeu de saisie", "Ecris ta reponse.",
                            Saisie.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(
                                    new ReponseAEcrire(new ItemId("s1"), "3 + ? = 10", "7",
                                            List.of("sept"), Optional.of("Compte ceux qui manquent."),
                                            Optional.of("explication de s1"),
                                            ExigenceDeForme.FORME_LIBRE),
                                    new ReponseAEcrire(new ItemId("s2"), "Ecris le mot", "elle",
                                            List.of(), Optional.of("Deux l et un e."),
                                            Optional.empty(), ExigenceDeForme.FORME_EXACTE),
                                    new ReponseAEcrire(new ItemId("s3"), "5 + ? = 10", "5",
                                            List.of(), Optional.of("Autant qu'il y en a deja."),
                                            Optional.empty(), ExigenceDeForme.FORME_LIBRE))),
                            10),
                    // ADR-0075 : un item declare texteAVoixHaute, l'entree de catalogue declare
                    // consigneAdulte - le couple que LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarent-
                    // EnsembleTest garde cote contenu. Une seule manche : les tests de ce jeu
                    // exercent la persistance du bloc-adulte a travers l'echec et la revelation.
                    new GameDefinition(JEU_A_VOIX_ID, "Jeu a voix", "Choisis ce que tu entends.",
                            BanditManchot.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of(
                                    new Question(new ItemId("qv-1"), "Choisis ce que tu entends.",
                                            "au debut", List.of("au milieu", "a la fin"),
                                            Optional.empty(), Optional.empty(), Optional.empty(),
                                            Optional.empty(), Optional.empty(), Optional.empty(),
                                            Optional.of("Le mot est chapeau.")))),
                            1, Optional.of("Dites le mot normalement, sans le couper.")));
            MechanicRegistryPort mecaniques =
                    id -> Saisie.ID.equals(id) ? new Saisie() : new BanditManchot();
            RandomSource hasard = bound -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
