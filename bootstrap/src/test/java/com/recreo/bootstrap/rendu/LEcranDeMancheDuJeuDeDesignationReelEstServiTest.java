package com.recreo.bootstrap.rendu;

import com.recreo.adapter.content.YamlContentPackLoader;
import com.recreo.adapter.web.PartieController;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameMechanic;
import com.recreo.domain.mechanic.MechanicId;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.designation.Designation;
import com.recreo.domain.mechanics.saisie.Saisie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0092, mecanisme 1 : pour chaque entree du catalogue reel dont la mecanique est
 * {@code designation}, l'ecran de manche reellement servi rejoue les criteres des garde-fous 1 et 3
 * d'ADR-0080</b> - qui, jusqu'ici, ne mordaient que sur un jeu fabrique de {@code adapter-web}
 * ({@code ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest},
 * {@code AucuneZoneDesignableNeDescendSousLaCibleTactileTest}), jamais sur le catalogue reellement
 * servi. C'est exactement le defaut qu'{@code ADR-0088} clause C a paye sur cinq autres garde-fous de
 * rendu : sept garde-fous de {@code bootstrap/rendu} ecartent deja la designation a s34, et aucun ne
 * sert son ecran de manche.
 *
 * <p><b>Pourquoi ce module, et pourquoi les criteres sont reecrits ici plutot qu'appeles.</b> Ce
 * bootstrap est le seul module qui voie a la fois le contenu ({@code adapter-content}) et le rendu
 * web ({@code adapter-web}) - meme motif d'emplacement que
 * {@link LIllustrationDUnItemServiAtteintLEcranDeMancheTest}. Les deux garde-fous d'ADR-0080 cites
 * vivent dans les <b>sources de test</b> d'{@code adapter-web}, qu'aucun module ne publie en
 * dependance (pas de {@code test-jar}) : leurs criteres sont donc rejoues ici sur le meme materiau
 * (le document HTML reellement rendu, {@code recreo.css}), et non appeles a travers une frontiere de
 * module qui n'existe pas. Ajouter cette frontiere serait une decision d'architecture qui n'appartient
 * pas a ce lot.</p>
 *
 * <p>ADR-0013, les trois obligations : <b>perimetre derive</b> - les jeux juges viennent du catalogue
 * reel ({@link YamlContentPackLoader}), filtres sur {@link Designation#ID}, jamais un nom de paquet
 * ecrit ici ; <b>derivation totale</b> - chaque bouton de zone de chaque jeu de designation servi est
 * juge sur les quatre criteres du garde-fou 1 et sur la cible tactile du garde-fou 3 ; <b>mise a
 * l'epreuve</b> - les memes morsures que les deux garde-fous d'origine, rejouees sur le document
 * reellement servi par ce catalogue.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LEcranDeMancheDuJeuDeDesignationReelEstServiTest.JeuxDeTestConfiguration.class)
class LEcranDeMancheDuJeuDeDesignationReelEstServiTest {

    private static final String SELECTEUR_IMAGE_DESIGNATION = ".image-designation";
    private static final String PROPRIETE_PLAFOND = "max-height";
    private static final String FEUILLE_MAISON = "/static/css/recreo.css";

    /**
     * Meme plancher que {@code TailleDesTextesEtBoutonsTest.CIBLE_TACTILE_REM} (module
     * {@code adapter-web}), epingle une seconde fois ici pour le motif ecrit dans la Javadoc de
     * classe : aucun {@code test-jar} ne publie cette constante hors de son module. Une seule valeur
     * gouverne les deux garde-fous ; elle est dupliquee, pas divergente.
     */
    private static final double PLANCHER_DE_CIBLE_TACTILE_REM = 2.75;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private static List<GameDefinition> jeuxDeDesignationDuCatalogueReel(PartieUseCase partie) {
        return partie.jeuxDisponibles().stream()
                .filter(jeu -> Designation.ID.equals(jeu.mecanique()))
                .toList();
    }

    private static Document ecran(MockMvc mockMvc, String jeuId) throws Exception {
        String corps = mockMvc.perform(post("/parties/{jeuId}", jeuId).session(new MockHttpSession()))
                .andReturn().getResponse().getContentAsString();
        return Jsoup.parseBodyFragment(corps);
    }

    // --- Garde-fou 1 d'ADR-0080 : chaque zone est un et un seul bouton de soumission du coup ---

    private static List<String> griefsDeFormulaire(Document document) {
        List<String> griefs = new ArrayList<>();

        List<Element> formulaires = document.select("form[hx-post]").stream()
                .filter(formulaire -> formulaire.attr("hx-post").contains("/designations"))
                .toList();
        if (formulaires.size() != 1) {
            griefs.add("le document doit porter exactement un formulaire dont hx-post contient"
                    + " /designations, il en porte " + formulaires.size());
            return griefs;
        }
        Element formulaire = formulaires.get(0);

        List<Element> boutonsDeZone = formulaire.select("button");
        if (boutonsDeZone.isEmpty()) {
            griefs.add("le formulaire du coup de designation ne porte aucun bouton de zone");
        }
        for (Element bouton : boutonsDeZone) {
            if (!"submit".equals(bouton.attr("type"))) {
                griefs.add("le bouton de zone " + bouton.outerHtml() + " n'est pas type=\"submit\"");
            }
            if (!"zoneDesignee".equals(bouton.attr("name"))) {
                griefs.add("le bouton de zone " + bouton.outerHtml()
                        + " ne porte pas name=\"zoneDesignee\"");
            }
            if (bouton.attr("value").isBlank()) {
                griefs.add("le bouton de zone " + bouton.outerHtml() + " porte une valeur vide");
            }
            if (bouton.text().isBlank()) {
                griefs.add("le bouton de zone " + bouton.outerHtml()
                        + " ne porte aucun nom accessible (texte vide)");
            }
        }

        if (!document.select("img[usemap], map, area").isEmpty()) {
            griefs.add("le document porte une image cliquable (usemap/map/area), interdite par"
                    + " ADR-0080 : le client n'envoie jamais une coordonnee");
        }
        for (Element simule : formulaire.select("div[onclick], span[onclick]")) {
            griefs.add("le document simule une zone par " + simule.outerHtml()
                    + " au lieu d'un vrai bouton de formulaire");
        }

        return griefs;
    }

    // --- Garde-fou 3 d'ADR-0080 : la taille rendue de chaque zone atteint la cible tactile ---

    private record ZoneRendue(String id, double largeurPourcent, double hauteurPourcent) {
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

    private static List<ZoneRendue> zonesRendues(Document document) {
        List<ZoneRendue> zones = new ArrayList<>();
        for (Element bouton : document.select("form[hx-post] button")) {
            String style = bouton.attr("style");
            zones.add(new ZoneRendue(bouton.attr("value"),
                    pourcentDeclare(style, "width", bouton), pourcentDeclare(style, "height", bouton)));
        }
        return zones;
    }

    /**
     * Le plafond de rendu, derive de {@code recreo.css} - jamais recopie a la main : le
     * {@code max-height} en rem de {@code .image-designation}, lu par decoupage de bloc, sans
     * expression reguliere (ADR-0008).
     */
    private static double plafondDeHauteurEnRem() {
        String feuille;
        try (InputStream flux = LEcranDeMancheDuJeuDeDesignationReelEstServiTest.class
                .getResourceAsStream(FEUILLE_MAISON)) {
            assertThat(flux).as("%s introuvable sur le classpath", FEUILLE_MAISON).isNotNull();
            feuille = new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        String etiquetteDeBloc = SELECTEUR_IMAGE_DESIGNATION + " {";
        int debutDuBloc = feuille.indexOf(etiquetteDeBloc);
        assertThat(debutDuBloc)
                .as("%s doit exister dans la feuille maison servie", SELECTEUR_IMAGE_DESIGNATION)
                .isNotNegative();
        int finDuBloc = feuille.indexOf('}', debutDuBloc);
        String bloc = feuille.substring(debutDuBloc + etiquetteDeBloc.length(), finDuBloc);

        String etiquetteDePropriete = PROPRIETE_PLAFOND + ":";
        int debut = bloc.indexOf(etiquetteDePropriete);
        if (debut < 0) {
            throw new AssertionError(SELECTEUR_IMAGE_DESIGNATION + " doit declarer " + PROPRIETE_PLAFOND
                    + " en rem");
        }
        debut += etiquetteDePropriete.length();
        int fin = bloc.indexOf("rem", debut);
        return Double.parseDouble(bloc.substring(debut, fin).strip());
    }

    private static List<String> griefsDeCibleTactile(List<ZoneRendue> zones, double plafondDeHauteurRem,
                                                       double plancherRem) {
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

    private static List<String> griefsDuDocument(Document document) {
        List<String> griefs = new ArrayList<>(griefsDeFormulaire(document));
        griefs.addAll(griefsDeCibleTactile(zonesRendues(document), plafondDeHauteurEnRem(),
                PLANCHER_DE_CIBLE_TACTILE_REM));
        return griefs;
    }

    @Test
    void surChaqueJeuDeDesignationDuCatalogueReelLEcranDeMancheEstConforme() throws Exception {
        List<GameDefinition> jeuxDeDesignation = jeuxDeDesignationDuCatalogueReel(partie);
        assertThat(jeuxDeDesignation)
                .as("ADR-0051 : ce garde-fou ne prouve rien si le catalogue reel ne sert aucun jeu de"
                        + " designation")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (GameDefinition jeu : jeuxDeDesignation) {
            defauts.addAll(griefsDuDocument(ecran(mockMvc, jeu.id())));
        }

        assertThat(defauts)
                .as("ADR-0080, garde-fous 1 et 3, rejoues sur l'ecran de manche reellement servi du"
                        + " catalogue reel")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3, morsure du garde-fou 1 : un bouton de zone simule par un {@code <div>}
     * sur l'ecran reellement servi.
     */
    @Test
    void unBoutonDeZoneSimuleParUneDivFaitEchouerLeGardeFou() throws Exception {
        GameDefinition jeu = jeuxDeDesignationDuCatalogueReel(partie).stream().findFirst().orElseThrow();
        Document document = ecran(mockMvc, jeu.id());
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.tagName("div");
        bouton.removeAttr("type");
        bouton.attr("onclick", "this.form.requestSubmit()");

        assertThat(griefsDeFormulaire(document)).isNotEmpty();
    }

    /** Morsure du garde-fou 1 : le type du bouton passe de {@code submit} a {@code button}. */
    @Test
    void unBoutonDeZoneDeTypeBoutonFaitEchouerLeGardeFou() throws Exception {
        GameDefinition jeu = jeuxDeDesignationDuCatalogueReel(partie).stream().findFirst().orElseThrow();
        Document document = ecran(mockMvc, jeu.id());
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.attr("type", "button");

        assertThat(griefsDeFormulaire(document)).isNotEmpty();
    }

    /** Morsure du garde-fou 1 : une image cliquable ajoutee au document reel. */
    @Test
    void uneImageCliquableAjouteeAuDocumentFaitEchouerLeGardeFou() throws Exception {
        GameDefinition jeu = jeuxDeDesignationDuCatalogueReel(partie).stream().findFirst().orElseThrow();
        Document document = ecran(mockMvc, jeu.id());
        Element image = document.selectFirst("img.image-designation");
        assertThat(image).as("l'ecran de manche doit porter l'image de designation").isNotNull();
        image.attr("usemap", "#plan");
        image.after("<map name=\"plan\"><area shape=\"rect\" coords=\"0,0,10,10\" href=\"#\"></map>");

        assertThat(griefsDeFormulaire(document)).isNotEmpty();
    }

    /**
     * ADR-0013 obligation 3, morsure du garde-fou 3 : une zone retrecie a 5% x 5%, sous le plancher a
     * n'importe quel plafond de hauteur raisonnable de cette application.
     */
    @Test
    void uneZoneRetrecieSousLePlancherFaitEchouerLeGardeFou() throws Exception {
        GameDefinition jeu = jeuxDeDesignationDuCatalogueReel(partie).stream().findFirst().orElseThrow();
        Document document = ecran(mockMvc, jeu.id());
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.attr("style", "left:0%; top:0%; width:5%; height:5%;");

        List<ZoneRendue> zones = zonesRendues(document);
        assertThatThrownBy(() -> assertThat(griefsDeCibleTactile(zones, plafondDeHauteurEnRem(),
                PLANCHER_DE_CIBLE_TACTILE_REM)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sous le plancher de cible tactile");
    }

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        private static GameMechanic mecaniquePour(MechanicId id) {
            if (Designation.ID.equals(id)) {
                return new Designation();
            }
            if (BanditManchot.ID.equals(id)) {
                return new BanditManchot();
            }
            if (Saisie.ID.equals(id)) {
                return new Saisie();
            }
            throw new IllegalStateException("mecanique inconnue : " + id);
        }

        /**
         * Le catalogue reel, charge par le meme chargeur que la production - jamais un jeu
         * fabrique : c'est exactement ce que ce garde-fou doit servir (ADR-0092, mecanisme 1).
         */
        @Bean
        PartieUseCase partieUseCase() {
            ContentCatalogPort catalogue = new YamlContentPackLoader();
            MechanicRegistryPort mecaniques = JeuxDeTestConfiguration::mecaniquePour;
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
