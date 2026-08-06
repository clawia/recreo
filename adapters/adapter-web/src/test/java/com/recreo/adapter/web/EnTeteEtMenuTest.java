package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * S6-1, S6-2 (DEC-0012) : un en-tete permanent porte l'icone et une unique entree de menu vers
 * l'accueil, et le contenu de chaque ecran est centre dans une largeur maximale.
 *
 * <p>Rendu par le vrai moteur Thymeleaf via {@link MockMvc}, comme {@code PartieControllerTest} :
 * ce n'est pas un balayage de fichiers de production lus depuis le disque (ADR-0013), donc ce
 * test n'entre pas dans le recensement de {@code LesGardeFousSontMisALEpreuveTest} - il vise une
 * propriete du gabarit d'accueil rendu, pas une population derivee du systeme de fichiers.
 *
 * <p>Ce que ce test ne prouve pas, et que la CI ne peut pas prouver a sa place (ADR-0017) : que le
 * lien "Accueil" reste moins saillant qu'un bouton de reponse ou "Encore !" une fois les deux
 * affiches ensemble sur l'ecran de jeu. Aucun garde-fou du depot ne confronte aujourd'hui un
 * bouton d'{@code accueil.html} a un bouton de {@code fragments/manche.html} ou
 * {@code fragments/retour.html} - DEC-0012 le signale comme prealable technique a instruire par
 * theo-archi. Le choix de style de {@code .lien-accueil} (memes composantes de boite que
 * {@code .retour-accueil}, deja plus petites que celles de tout bouton d'action) est fait pour ne
 * pas s'appuyer sur ce trou, mais seule nadia-prof, en jouant, en verifie le rendu reel.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class EnTeteEtMenuTest {

    private static final String JEU_ID = "un-jeu";

    @Autowired
    private MockMvc mockMvc;

    private Document page(MockHttpSession session) throws Exception {
        String html = mockMvc.perform(get("/").session(session))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parse(html);
    }

    @Test
    void lEnTeteNEstJamaisUnDescendantDeLaZoneDeJeu() throws Exception {
        Document page = page(new MockHttpSession());

        Elements jeu = page.select("#jeu");
        assertThat(jeu).as("la page doit porter la zone #jeu remplacee par htmx").isNotEmpty();
        assertThat(jeu.select("header"))
                .as("l'en-tete ne doit jamais etre un descendant de #jeu, sinon il disparaitrait a "
                        + "chaque fragment de jeu rendu (DEC-0012)")
                .isEmpty();
    }

    @Test
    void lEnTeteResteVisibleQuandUnePartieEstRepriseSansEtreDuplique() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        Document page = page(session);

        assertThat(page.select("header"))
                .as("l'en-tete reste present, une seule fois, meme quand la reprise remplace le "
                        + "contenu de #jeu")
                .hasSize(1);
    }

    @Test
    void leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil() throws Exception {
        Document page = page(new MockHttpSession());

        Elements liensDeNavigation = page.select("header button, header a[href]");
        assertThat(liensDeNavigation)
                .as("DEC-0012 : le menu de l'en-tete contient exactement une entree")
                .hasSize(1);
        assertThat(liensDeNavigation.first().text()).isEqualTo("Accueil");
    }

    @Test
    void lIconeDeLEnTeteEstServieHorsLigneDepuisLesRessourcesStatiques() throws Exception {
        Document page = page(new MockHttpSession());

        Elements icones = page.select("header img");
        assertThat(icones).as("l'en-tete porte un dessin d'icone").isNotEmpty();
        assertThat(icones.first().attr("src"))
                .as("l'icone doit venir des ressources statiques empaquetees, jamais du reseau")
                .isEqualTo("/favicon.ico");
    }

    @Test
    void leContenuDeLAccueilEstCentreDansUneLargeurMaximale() throws Exception {
        Document page = page(new MockHttpSession());

        assertThat(page.select("main#jeu.container"))
                .as("S6-2 : la zone de jeu porte .container, qui centre et borne sa largeur")
                .isNotEmpty();
        assertThat(page.select("header .container"))
                .as("S6-1/S6-2 : le contenu de l'en-tete suit le meme centrage que le reste de la "
                        + "page")
                .isNotEmpty();
    }
}
