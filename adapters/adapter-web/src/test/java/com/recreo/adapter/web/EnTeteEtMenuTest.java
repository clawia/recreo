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
 * S6-1, S6-2 (DEC-0012) : un en-tete permanent porte l'icone, le titre et le contenu de chaque
 * ecran est centre dans une largeur maximale.
 *
 * <p>Rendu par le vrai moteur Thymeleaf via {@link MockMvc}, comme {@code PartieControllerTest} :
 * ce n'est pas un balayage de fichiers de production lus depuis le disque (ADR-0013), donc ce
 * test n'entre pas dans le recensement de {@code LesGardeFousSontMisALEpreuveTest} - il vise une
 * propriete du gabarit d'accueil rendu, pas une population derivee du systeme de fichiers.
 *
 * <p>S14-2 (BARRE-7, DEC-0033) : l'en-tete ne porte plus de lien de sortie vers l'accueil - le
 * lien "Les jeux" qui y vivait est retire de tout ecran, jamais remplace par un comportement neuf.
 * Seuls les points d'entree par niveau (ADR-0043, ADR-0044) restent actionnables dans l'en-tete.
 * Ce que ce test ne prouve toujours pas : la saillance rendue de ces points d'entree, qui depend
 * de la couleur, de la position et de la largeur des libelles (ADR-0017) - hors de portee de la
 * CI, gardee par {@link LActionNeDeclarePasMoinsQueLaSortieTest} pour ce qui est declaratif, et
 * par {@code nadia-prof} en jouant pour le reste.
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
     * S14-2 (BARRE-7, DEC-0033) : remplace
     * {@code leMenuDeLEnTeteContientUnPointDEntreeParNiveauEtUneSeuleSortieVersLAccueil} (S14-1,
     * 2026-08-17), qui affirmait une sortie dans le menu de l'en-tete. Cette sortie - le lien
     * "Les jeux" - est retiree de tout ecran par S14-2, sans etre remplacee par un comportement
     * neuf (DEC-0033) : elle n'etait deja visible que sur l'accueil et, sur l'accueil non filtre,
     * un appui dessus rendait le meme document. Le menu de l'en-tete ne porte donc plus qu'un
     * point d'entree par niveau distinct du catalogue, dans l'ordre du catalogue - toujours sans
     * effectif ecrit ici (ADR-0006, ADR-0044).
     *
     * <p><b>Propose pour confirmation ecrite de theo-archi (via sofia-sm), pas tranche seul</b> :
     * remplacement d'un test que la story d'hier m'a fait ecrire, motive par le meme geste que
     * {@code LActionNeDeclarePasMoinsQueLaSortieTest}.</p>
     */
    @Test
    void leMenuDeLEnTeteNePorteQuUnPointDEntreeParNiveauEtPlusAucuneSortie() throws Exception {
        Document page = page(new MockHttpSession());

        assertThat(page.select("header [data-role=sortie]"))
                .as("DEC-0033 / BARRE-7 : le lien \"Les jeux\" de l'en-tete est retire, jamais "
                        + "remplace par un comportement neuf")
                .isEmpty();

        List<String> niveauxDuCatalogue = partieUseCase.niveauxDuCatalogue();
        Elements pointsDEntree = page.select("header [data-role=entree]");
        assertThat(pointsDEntree.eachText())
                .as("ADR-0043 / ADR-0044 : exactement un point d'entree par niveau distinct du "
                        + "catalogue, dans l'ordre du catalogue - le nombre n'est pas ecrit ici, il "
                        + "est celui que niveauxDuCatalogue() rend")
                .containsExactlyElementsOf(niveauxDuCatalogue);
    }

    /**
     * S14-2, critere 2 (docs/backlog/backlog.md), forme retenue par theo-archi apres relecture de
     * ce lot : l'ensemble des actionnables de l'en-tete egale l'ensemble de ses
     * {@code [data-role=entree]} - sans libelle, sans catalogue, sans effectif. Elle interdit
     * exactement ce que le critere nomme (aucune troisieme boite actionnable a cote des puces de
     * niveau) sans dependre de ce que {@code niveauxDuCatalogue()} rend ni de ce qu'un bouton dit.
     */
    @Test
    void surLAccueilNonFiltreLesActionnablesDeLEnTeteSontExactementSesPointsDEntree() throws Exception {
        Document page = page(new MockHttpSession());

        Elements actionnables = page.select("header button, header a[href]");
        Elements pointsDEntree = page.select("header [data-role=entree]");
        assertThat(actionnables)
                .as("S14-2 critere 2 : rien d'actionnable dans l'en-tete de l'accueil non filtre "
                        + "hors de ses points d'entree par niveau")
                .containsExactlyInAnyOrderElementsOf(pointsDEntree);
        assertThat(page.select("header .icone-entete")).isNotEmpty();
        assertThat(page.select("header .nom-site").text()).isEqualTo("Recreo");
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
