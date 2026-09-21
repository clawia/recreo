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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0080, garde-fou 1 : sur l'ecran de manche reellement servi d'un jeu de designation, chaque
 * zone de l'item est un et un seul {@code <button type="submit" name="zoneDesignee">}, dans le
 * formulaire du coup - jamais une image cliquable, jamais un bouton simule.</b>
 *
 * <p>C'est la moitie du refus de conception d'ADR-0080 (decision 4, alternative 1 ecartee) qu'aucune
 * {@code ArchRule} ne peut garder : la clause 10 de {@code DecouplageMecaniqueContenuTest} interdit a
 * une mecanique de lire un {@code CadreDeZone}, mais elle ne voit ni {@code adapter-web} ni le
 * document HTML qu'il sert. Deux formes concretes rendraient la designation injouable au clavier
 * sans que la clause 10 le sache : une image {@code usemap}/{@code <area>} (le navigateur ne
 * tabule pas dans une carte d'image), et un {@code <div>} simulant un bouton par un gestionnaire de
 * clic (rien ne le focalise sans un {@code tabindex} declare, et rien ne le soumet a Entree ou a
 * Espace sans reecrire ce que le navigateur fait deja pour un vrai bouton).
 *
 * <p>ADR-0013, les trois obligations :</p>
 * <ul>
 *   <li><b>Perimetre derive</b> : les zones jugees sont celles que le document rend reellement -
 *       tout element retenu par {@code form.zones-designables button}, jamais une liste d'identifiants
 *       ecrite ici. Le formulaire lui-meme est retrouve par son attribut {@code hx-post}, jamais par
 *       son nom de classe.</li>
 *   <li><b>Derivation totale</b> : chaque bouton trouve est juge sur les quatre criteres a la fois -
 *       balise, type, valeur non vide, nom accessible non vide - et le document entier est balaye pour
 *       les trois formes interdites (usemap, map, area), pas seulement le voisinage du formulaire.</li>
 *   <li><b>Mise a l'epreuve</b> : sept morsures sur la sortie reelle du serveur, mutee - jamais une
 *       fixture ecrite de ma main.</li>
 * </ul>
 *
 * <p><b>ADR-0093, clause 1 : le nom accessible d'un bouton de zone est resolu</b>, il n'est plus
 * assimile au texte du bouton - un bouton nomme par {@code aria-label} ou par
 * {@code aria-labelledby} a un nom accessible et peut n'avoir aucun texte. Ce module ne compare pas
 * le nom resolu au libelle declare par le paquet (clause 2) : il ne voit pas {@code adapter-content},
 * et ce fabrique de test ne charge aucun contenu reel. Cette moitie-la est rejouee dans
 * {@code bootstrap}, seul module qui voie les deux cotes
 * ({@code LEcranDeMancheDuJeuDeDesignationReelEstServiTest}).</p>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class ChaqueZoneDesignableEstUnBoutonDuFormulaireDeCoupTest {

    private static final String JEU_DESIGNATION_ID = PartieControllerTest.JEU_DESIGNATION_ID;

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
     * ADR-0093 clause 1 : le nom accessible est resolu, il n'est plus assimile au texte du bouton.
     * Ordre corrige le 2026-09-21, dans le sens du calcul reel du nom accessible - aria-labelledby,
     * puis aria-label, puis le texte du sous-arbre. L'ordre inverse rendait rouge un bouton
     * correctement nomme par aria-label et vert un bouton renomme a l'insu du contenu.
     */
    static String nomAccessible(Element bouton, Document document) {
        String references = bouton.attr("aria-labelledby").trim();
        if (!references.isBlank()) {
            StringBuilder nom = new StringBuilder();
            for (String identifiant : references.split("\\s+")) {
                Element cible = document.getElementById(identifiant);
                if (cible == null || cible.text().isBlank()) {
                    return "";
                }
                nom.append(nom.isEmpty() ? "" : " ").append(cible.text());
            }
            return nom.toString();
        }
        if (!bouton.attr("aria-label").isBlank()) {
            return bouton.attr("aria-label");
        }
        return bouton.text();
    }

    /**
     * Le critere, ecrit comme une fonction du document qu'on lui donne : rend la liste des griefs,
     * vide quand le document est sain. C'est ce qui permet a la mise a l'epreuve de lui presenter le
     * meme document, mute.
     */
    static List<String> griefs(Document document) {
        List<String> griefs = new ArrayList<>();

        List<Element> formulaires = document.select("form[hx-post]").stream()
                .filter(formulaire -> formulaire.attr("hx-post").contains("/designations"))
                .toList();
        if (formulaires.size() != 1) {
            griefs.add("le document doit porter exactement un formulaire dont hx-post contient "
                    + "/designations, il en porte " + formulaires.size());
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
            if (nomAccessible(bouton, document).isBlank()) {
                griefs.add("le bouton de zone " + bouton.outerHtml() + " ne porte aucun nom"
                        + " accessible : ni texte, ni aria-label, ni aria-labelledby resolvant vers"
                        + " un texte non vide");
            }
        }

        // Toute forme de designation par image cliquable est interdite, ou qu'elle vive dans le
        // document - pas seulement dans le formulaire du coup.
        if (!document.select("img[usemap], map, area").isEmpty()) {
            griefs.add("le document porte une image cliquable (usemap/map/area), interdite par "
                    + "ADR-0080 : le client n'envoie jamais une coordonnee");
        }
        // Un bouton simule par un <div> ou un <span> porteur d'un gestionnaire de clic en ligne
        // serait, comme un vrai bouton, dans le formulaire du coup ; mais lui seul n'est pas
        // atteignable par tabulation ni actionnable par Entree/Espace sans reecrire ce qu'un bouton
        // fait deja nativement.
        for (Element simule : formulaire.select("div[onclick], span[onclick]")) {
            griefs.add("le document simule une zone par " + simule.outerHtml()
                    + " au lieu d'un vrai bouton de formulaire");
        }

        return griefs;
    }

    @Test
    void chaqueZoneDesignableEstUnBoutonDuFormulaireDeCoup() throws Exception {
        Document document = ecranDeManche();

        assertThat(document.select("form[hx-post] button"))
                .as("ADR-0003 : ce garde-fou ne prouve rien si aucun bouton de zone n'est servi")
                .isNotEmpty();
        assertThat(griefs(document)).isEmpty();
    }

    /**
     * Morsure 1 : le premier bouton de zone est remplace par un {@code <div>} qui simule un clic en
     * ligne - la forme exacte qu'ADR-0080 refuse pour l'accessibilite au clavier.
     */
    @Test
    void unBoutonDeZoneSimuleParUneDivFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.tagName("div");
        bouton.removeAttr("type");
        bouton.attr("onclick", "this.form.requestSubmit()");

        assertThat(griefs(document)).isNotEmpty();
    }

    /** Morsure 2 : le type du bouton passe de {@code submit} a {@code button}, sur le document reel. */
    @Test
    void unBoutonDeZoneDeTypeBoutonFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.attr("type", "button");

        assertThat(griefs(document)).isNotEmpty();
    }

    /** Morsure 3 : la valeur du bouton est videe, sur le document reel. */
    @Test
    void unBoutonDeZoneSansValeurFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.attr("value", "");

        assertThat(griefs(document)).isNotEmpty();
    }

    /**
     * Morsure 4 : une image cliquable (usemap/area) ajoutee au document reel - la conception
     * qu'ADR-0080 ecarte explicitement (alternative 1, le client envoie une coordonnee).
     */
    @Test
    void uneImageCliquableAjouteeAuDocumentFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element image = document.selectFirst("img.image-designation");
        assertThat(image).as("l'ecran de manche doit porter l'image de designation").isNotNull();
        image.attr("usemap", "#plan");
        image.after("<map name=\"plan\"><area shape=\"rect\" coords=\"0,0,10,10\" href=\"#\"></map>");

        assertThat(griefs(document)).isNotEmpty();
    }

    /**
     * ADR-0093, morsure 1 : un bouton sans aucun des trois porteurs de nom accessible est rouge - la
     * propriete d'origine ne s'affaiblit pas.
     */
    @Test
    void unBoutonSansAucunPorteurDeNomAccessibleFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.text("");

        assertThat(griefs(document)).isNotEmpty();
    }

    /**
     * ADR-0093, morsure 2 : un bouton nomme par aria-label seul (texte vide) est vert sous le juge
     * neuf. La morsure porte les deux verdicts dans la meme assertion : sans elle, le juge neuf
     * pourrait etre aussi strict que l'ancien sans que rien ne le montre.
     */
    @Test
    void unBoutonNommeParAriaLabelSeulEstAccepteParLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.text("");
        bouton.attr("aria-label", "une zone");

        assertThat(bouton.text())
                .as("l'ancien critere (bouton.text().isBlank()) aurait refuse ce bouton")
                .isBlank();
        assertThat(griefs(document)).isEmpty();
    }

    /**
     * ADR-0093, morsure 3 : un aria-labelledby qui designe un identifiant absent du document est
     * rouge - un nom accessible vide qui se croit rempli.
     */
    @Test
    void unAriaLabelledbyVersUnIdentifiantAbsentFaitEchouerLeGardeFou() throws Exception {
        Document document = ecranDeManche();
        Element bouton = document.select("form[hx-post] button").first();
        assertThat(bouton).as("un bouton de zone doit exister avant mutation").isNotNull();
        bouton.attr("aria-labelledby", "identifiant-absent-du-document");

        assertThat(griefs(document)).isNotEmpty();
    }
}
