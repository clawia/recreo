package com.recreo.adapter.web;

import com.recreo.application.port.in.PartieUseCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
 * <p>Ce que ce test ne prouve pas : que l'entree de menu reste moins saillante qu'un bouton de
 * reponse ou "Encore !". Depuis l'arbitrage de nadia-prof du 2026-08-07 sur S6-9, cette entree n'est
 * plus visible sur les cinq etats de l'ecran de jeu, et la question ne se pose donc plus que sur
 * l'accueil - ou {@link LActionNeDeclarePasMoinsQueLaSortieTest} la confronte a {@code .demarrer}
 * (ADR-0022, second amendement du meme jour a ADR-0017). Ce test-ci verifie que l'entree
 * <b>existe</b> et qu'elle est unique, jamais qu'elle est visible : une visibilite est une propriete
 * du rendu. Le couple declaratif qui la produit est garde par
 * {@link LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest}. Ce qui reste hors de portee de la CI et
 * n'appartient qu'a nadia-prof, en jouant : la saillance rendue, qui depend de la couleur, de la
 * position et de la largeur des libelles (ADR-0017, points 1 a 3 de la meme section), et
 * l'invisibilite effective de l'entree sur les cinq etats de l'ecran de jeu.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class EnTeteEtMenuTest {

    private static final String JEU_ID = "un-jeu";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partieUseCase;

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

    /**
     * S14-1 (avis-faisabilite de theo-archi, section 7.1) : remplace le comptage fixe a un seul
     * element actionnable, qui epinglait la taille du depot au jour ou il a ete ecrit plutot qu'une
     * propriete. La forme retenue interdit trois choses a la fois : un libelle de niveau ecrit en
     * dur, un niveau du catalogue sans point d'entree, et un point d'entree sans niveau au
     * catalogue - sans jamais ecrire d'effectif dans ce test (ADR-0006, ADR-0044).
     */
    @Test
    void leMenuDeLEnTeteContientUnPointDEntreeParNiveauEtUneSeuleSortieVersLAccueil() throws Exception {
        Document page = page(new MockHttpSession());

        Elements sorties = page.select("header [data-role=sortie]");
        assertThat(sorties)
                .as("ADR-0022 / S6-9 : le menu de l'en-tete ne contient qu'une seule sortie")
                .hasSize(1);
        assertThat(sorties.first().text())
                .as("ADR-0022 / S6-9 : le libelle est \"Les jeux\", et c'est le geste par lequel "
                        + "nadia-prof leve sa reserve 3 du 2026-08-07")
                .isEqualTo("Les jeux");

        List<String> niveauxDuCatalogue = partieUseCase.niveauxDuCatalogue();
        Elements pointsDEntree = page.select("header [data-role=entree]");
        assertThat(pointsDEntree.eachText())
                .as("ADR-0043 / ADR-0044 : exactement un point d'entree par niveau distinct du "
                        + "catalogue, dans l'ordre du catalogue - le nombre n'est pas ecrit ici, il "
                        + "est celui que niveauxDuCatalogue() rend")
                .containsExactlyElementsOf(niveauxDuCatalogue);
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
