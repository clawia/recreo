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
 * <p><b>DEC-0034 (s15) : l'utilisatrice retablit le lien "Les jeux" dans l'en-tete</b>, dans son
 * etat d'avant s14, en connaissance du motif BARRE-7/DEC-0027 que S14-2 (BARRE-7, DEC-0033) avait
 * ferme en le retirant. Ce refus pedagogique de {@code nadia-prof} reste lisible tel quel dans
 * {@code FICHE-barre-entree-niveau.md} et n'est ni efface ni corrige ici : l'arbitrage de
 * l'utilisatrice le surplombe, il ne le repare pas. L'en-tete porte donc de nouveau une seule
 * sortie (le lien "Les jeux") aux cotes des points d'entree par niveau (ADR-0043, ADR-0044).
 *
 * <p>Ce que ce test ne prouve toujours pas : la saillance rendue de ces points d'entree et de la
 * sortie, qui depend de la couleur, de la position et de la largeur des libelles (ADR-0017) - hors
 * de portee de la CI, gardee par {@link LActionNeDeclarePasMoinsQueLaSortieTest} pour ce qui est
 * declaratif, et par {@code nadia-prof} en jouant pour le reste.
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
     * DEC-0034 (s15) : remplace {@code leMenuDeLEnTeteNePorteQuUnPointDEntreeParNiveauEtPlusAucuneSortie}
     * (S14-2, 2026-08-17), qui affirmait qu'aucune sortie ne vivait plus dans l'en-tete. <b>Motif
     * ecrit du retrait, exige par ADR-0013</b> : cette assertion encodait DEC-0033/BARRE-7, que
     * l'utilisatrice vient de renverser en connaissance de cause (elle retablit le lien "Les jeux"
     * dans l'en-tete, dans son etat d'avant s14) - un garde-fou qui encode un arbitrage pedagogique
     * doit rougir, puis etre corrige, quand cet arbitrage tombe. Ce que cette assertion gardait :
     * qu'aucun {@code [data-role=sortie]} n'apparaissait dans l'en-tete ; ce que cette methode garde
     * desormais a la place, restaure a l'identique de sa forme S14-1 (2026-08-17, avis de
     * faisabilite de theo-archi, section 7.1) : exactement une sortie, "Les jeux".
     *
     * <p>Le reste de la methode - un point d'entree par niveau distinct du catalogue, dans l'ordre
     * du catalogue, sans effectif ecrit ici (ADR-0006, ADR-0044) - est inchange par ce retrait.</p>
     */
    @Test
    void leMenuDeLEnTeteContientUnPointDEntreeParNiveauEtUneSeuleSortieVersLAccueil() throws Exception {
        Document page = page(new MockHttpSession());

        Elements sorties = page.select("header [data-role=sortie]");
        assertThat(sorties)
                .as("ADR-0022 / S6-9, DEC-0034 : le menu de l'en-tete contient de nouveau une seule "
                        + "sortie")
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

    /**
     * DEC-0034 (s15) : retiree. <b>Motif ecrit du retrait, exige par ADR-0013</b> : cette methode
     * (S14-2, critere 2) affirmait que l'ensemble des actionnables de l'en-tete de l'accueil non
     * filtre egalait exactement l'ensemble de ses {@code [data-role=entree]} - "rien d'actionnable
     * dans l'en-tete hors de ses points d'entree par niveau". L'utilisatrice retablit le lien
     * "Les jeux" ({@code [data-role=sortie]}) dans ce meme en-tete (DEC-0034) : un actionnable de
     * plus qui n'est pas un point d'entree existe desormais deliberement, et cette assertion
     * deviendrait fausse par construction plutot que par defaut. Son sujet - "aucune troisieme
     * boite actionnable a cote des puces de niveau" - est exactement ce que DEC-0034 renverse ;
     * elle n'est pas remplacee par une assertion equivalente, la propriete qu'elle gardait n'est
     * plus celle du produit.
     *
     * <p>Deux assertions incidentes de cette methode disparaissent avec elle sans etre reportees
     * ailleurs : la presence de {@code .icone-entete} (deja couverte par
     * {@link #lIconeDeLEnTeteEstServieHorsLigneDepuisLesRessourcesStatiques}) et le texte exact de
     * {@code .nom-site} ("Recreo"), qui n'est garde par aucun autre test de ce fichier apres ce
     * retrait - une perte de couverture mineure et signalee, pas comblee ici par une assertion que
     * ni S15-1 ni S15-2 ne demandent.</p>
     */

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
