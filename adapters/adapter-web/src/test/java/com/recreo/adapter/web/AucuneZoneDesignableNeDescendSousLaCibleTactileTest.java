package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0080, garde-fou 3 : la taille rendue d'une zone designable atteint le plancher de cible
 * tactile.</b> Patron d'ADR-0054 - une fonction epinglee -, et le motif d'ADR-0055 pour epingler les
 * <b>deux</b> membres de la comparaison plutot qu'un seul : le plancher (
 * {@code TailleDesTextesEtBoutonsTest.CIBLE_TACTILE_REM}, deja epingle par un autre garde-fou) et le
 * plafond de rendu de l'image ({@code .image-designation} dans {@code recreo.css}, jamais celui de
 * {@code .image-item}, que la designation ne partage plus depuis cette regle).
 *
 * <p><b>La mesure.</b> {@code .image-designation} borne la <b>hauteur</b> rendue de l'image ; la
 * largeur suit l'intrinseque du dessin jusqu'a cette hauteur (object-fit: contain, ADR-0080 section
 * 7.3). Une zone carree occupant une fraction {@code p} du cote de l'image rend donc {@code p} fois
 * cette hauteur, quel que soit le cote - c'est exactement l'arithmetique 44/176 = 25 % qu'ADR-0080
 * ecrit pour justifier le plafond du quadrillage a 4x4, et ce garde-fou l'applique zone par zone :
 * pour chaque zone, le plus petit des deux pourcentages de son {@code CadreDeZone} (largeur, hauteur)
 * multiplie par le plafond de hauteur doit atteindre le plancher de cible tactile.
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>Perimetre derive</b> : les zones jugees sont celles que l'ecran de manche reellement servi
 *       rend - chaque {@code button.zone}, son cadre lu dans l'attribut {@code style} que le gabarit
 *       calcule depuis {@code CadreDeZone} (ADR-0080, decision 4 : le pourcentage EST la valeur
 *       declaree, jamais recalcule ici). Le plafond de hauteur est lu dans {@code recreo.css}, jamais
 *       ecrit en dur.</li>
 *   <li><b>Derivation totale</b> : toute zone du document servi est jugee, aucune n'est exemptee.</li>
 *   <li><b>Mise a l'epreuve</b> : la sortie reelle du serveur, mutee - un cadre retreci sous le
 *       plancher, jamais une fixture.</li>
 * </ul>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class AucuneZoneDesignableNeDescendSousLaCibleTactileTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final String JEU_DESIGNATION_ID = PartieControllerTest.JEU_DESIGNATION_ID;
    private static final String SELECTEUR_IMAGE_DESIGNATION = ".image-designation";
    private static final String PROPRIETE_PLAFOND = "max-height";

    /** ADR-0013 obligation 3 : le jeu fautif commite de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "AucuneZoneDesignableNeDescendSousLaCibleTactileTest");

    @Autowired
    private MockMvc mockMvc;

    private String corps(String jeuId) throws Exception {
        return mockMvc.perform(post("/parties/{jeuId}", jeuId).session(new MockHttpSession()))
                .andReturn().getResponse().getContentAsString();
    }

    private Document ecranDeManche() throws Exception {
        return Jsoup.parseBodyFragment(corps(JEU_DESIGNATION_ID));
    }

    /**
     * Le plafond de rendu, <b>derive</b> de {@code recreo.css} : le {@code max-height} en rem de
     * {@code .image-designation}, jamais une valeur recopiee a la main.
     */
    static double plafondDeHauteurEnRem(Path racineDeProduction) {
        Map<String, String> maison = TailleDesTextesEtBoutonsTest.blocsMaison(racineDeProduction);
        String corps = maison.get(SELECTEUR_IMAGE_DESIGNATION);
        assertThat(corps)
                .as("%s doit exister dans la feuille maison servie", SELECTEUR_IMAGE_DESIGNATION)
                .isNotNull();
        return TailleDesTextesEtBoutonsTest.proprieteEnRemSiExiste(corps, PROPRIETE_PLAFOND)
                .orElseThrow(() -> new AssertionError(SELECTEUR_IMAGE_DESIGNATION + " doit declarer "
                        + PROPRIETE_PLAFOND + " en rem"));
    }

    /** Le plancher de cible tactile, deja epingle ailleurs (ADR-0054) : jamais recopie en dur. */
    static double plancherDeCibleTactileEnRem() {
        return TailleDesTextesEtBoutonsTest.CIBLE_TACTILE_REM;
    }

    private record ZoneRendue(String id, double largeurPourcent, double hauteurPourcent) {
    }

    /**
     * Les zones du document servi, lues sur l'attribut {@code style} que le gabarit calcule depuis
     * {@code CadreDeZone} - jamais recalculees, jamais lues sur le domaine : c'est la <b>sortie</b>
     * que l'enfant recoit qui est jugee. ADR-0008 : aucune expression reguliere, une lecture par
     * position comme {@code TailleDesTextesEtBoutonsTest.valeurALaPosition}.
     */
    static List<ZoneRendue> zonesRendues(Document document) {
        List<ZoneRendue> zones = new ArrayList<>();
        for (Element bouton : document.select("form[hx-post] button")) {
            String style = bouton.attr("style");
            zones.add(new ZoneRendue(bouton.attr("value"),
                    pourcentDeclare(style, "width", bouton), pourcentDeclare(style, "height", bouton)));
        }
        return zones;
    }

    private static double pourcentDeclare(String style, String propriete, Element bouton) {
        String etiquette = propriete + ":";
        int debut = style.indexOf(etiquette);
        if (debut < 0) {
            throw new AssertionError("le bouton de zone " + bouton.outerHtml()
                    + " ne declare pas " + propriete + " dans son style");
        }
        debut += etiquette.length();
        int fin = style.indexOf('%', debut);
        if (fin < 0) {
            throw new AssertionError("le bouton de zone " + bouton.outerHtml()
                    + " ne declare pas " + propriete + " en pourcentage dans son style");
        }
        return Double.parseDouble(style.substring(debut, fin).strip());
    }

    /**
     * Le juge, pur : rend la liste des griefs, vide quand toutes les zones atteignent le plancher.
     */
    static List<String> griefs(List<ZoneRendue> zones, double plafondDeHauteurRem, double plancherRem) {
        List<String> griefs = new ArrayList<>();
        for (ZoneRendue zone : zones) {
            double pourcentLePlusPetit = Math.min(zone.largeurPourcent(), zone.hauteurPourcent());
            double tailleRendueRem = pourcentLePlusPetit / 100.0 * plafondDeHauteurRem;
            if (tailleRendueRem < plancherRem) {
                griefs.add("la zone \"" + zone.id() + "\" rend au plus " + tailleRendueRem
                        + "rem (plafond de hauteur " + plafondDeHauteurRem + "rem, plus petit cote "
                        + pourcentLePlusPetit + "%), sous le plancher de cible tactile de "
                        + plancherRem + "rem (ADR-0080, garde-fou 3)");
            }
        }
        return griefs;
    }

    @Test
    void aucuneZoneDesignableNeDescendSousLaCibleTactile() throws Exception {
        List<ZoneRendue> zones = zonesRendues(ecranDeManche());

        assertThat(zones)
                .as("ADR-0003 : ce garde-fou ne prouve rien si aucune zone n'est servie")
                .isNotEmpty();
        assertThat(griefs(zones, plafondDeHauteurEnRem(RACINE_PRODUCTION), plancherDeCibleTactileEnRem()))
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : la sortie reelle du serveur, mutee - un cadre retreci a 5% x 5%, sous
     * le plancher a n'importe quel plafond de hauteur raisonnable de cette application.
     */
    @Test
    void uneZoneRetrecieSousLePlancherFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.attr("style", "left:0%; top:0%; width:5%; height:5%;");

        List<ZoneRendue> zones = zonesRendues(document);
        assertThatThrownBy(() -> assertThat(griefs(zones, plafondDeHauteurEnRem(RACINE_PRODUCTION),
                plancherDeCibleTactileEnRem())).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sous le plancher de cible tactile");
    }

    /** L'autre borne : une zone assez grande pour le meme plafond reste muette. */
    @Test
    void unePlusGrandeZoneNeFaitPasEchouerLeGardeFou() {
        List<ZoneRendue> zones = List.of(new ZoneRendue("large", 60.0, 60.0));

        assertThat(griefs(zones, 22.0, 2.75)).isEmpty();
    }

    /**
     * ADR-0013 obligation 3, sur le <b>jeu fautif commite</b> plutot que sur une mutation en memoire
     * seule : {@code CONTRE_EXEMPLE} porte sa propre {@code static/css/recreo.css}, dont
     * {@code .image-designation} declare un plafond de hauteur volontairement trop bas (5rem). Applique
     * aux zones reellement servies par le catalogue de test - jamais a une zone fabriquee -, ce
     * plafond fautif les fait toutes descendre sous le plancher de cible tactile.
     */
    @Test
    void leJeuFautifCommiteEstRefuse() throws Exception {
        List<ZoneRendue> zones = zonesRendues(ecranDeManche());
        assertThat(zones).as("le jeu fautif ne prouve rien sans zone reelle").isNotEmpty();
        double plafondFautif = plafondDeHauteurEnRem(CONTRE_EXEMPLE);

        assertThatThrownBy(() -> assertThat(griefs(zones, plafondFautif, plancherDeCibleTactileEnRem()))
                .isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sous le plancher de cible tactile");
    }
}
