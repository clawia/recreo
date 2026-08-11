package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S8-1 (cabinet visible autour des colonnes, retrait du disque decoratif de .demarrer et .encore)
 * et S8-3 (distinction visuelle de l'explication) - DEC-0017, nadia-prof arbitrage prealable du
 * 2026-08-11, ADR-0030.
 *
 * <p>Les proprietes visuelles que seule nadia-prof peut juger en jouant (C1 a C6, section 7 de son
 * arbitrage, et les cinq proprietes de l'explication section 6) ne sont pas ici : elles restent
 * hors de portee d'un automate (ADR-0025). Ce fichier couvre les criteres d'acceptation de S8-1 et
 * S8-3 qui se lisent sur le balisage et sur la feuille servie.
 */
class CabinetDeLaMachineTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/CabinetDeLaMachineTest");

    /** Les cinq selecteurs que le cabinet livre par S8-1 et sur lesquels aucun pixel n'est admis. */
    private static final List<String> SELECTEURS_DU_CABINET = List.of(
            ".cabinet", ".cabinet > .propositions", ".cabinet-bandeau",
            ".cabinet-bandeau::before", ".cabinet-socle");

    private static Path gabarit(String nomFichier) {
        return gabarits(RACINE_PRODUCTION).stream()
                .filter(fichier -> fichier.getFileName().toString().equals(nomFichier))
                .findFirst()
                .orElseThrow(() -> new AssertionError("gabarit introuvable : " + nomFichier));
    }

    private static Document document(String nomFichier) {
        Path chemin = gabarit(nomFichier);
        return Jsoup.parse(lire(chemin), chemin.toString());
    }

    /**
     * Critere d'acceptation S8-1 : le cabinet contient les colonnes de reponses et rien d'autre qui
     * se presse (C2 de nadia-prof) - "Les jeux" (data-role="sortie") reste hors du cadre.
     */
    @Test
    void leCabinetEntoureLesPropositionsEtRienDAutreQuiSePresse() {
        Document manche = document("manche.html");
        Elements cabinets = manche.select(".cabinet");
        assertThat(cabinets).as("le fragment de manche doit porter un .cabinet").hasSize(1);
        Element cabinet = cabinets.first();

        assertThat(cabinet.select(".propositions"))
                .as("le cabinet doit contenir le formulaire de propositions")
                .hasSize(1);
        assertThat(cabinet.select("[data-role=sortie]"))
                .as("C2 : aucun element de sortie ne se presse a l'interieur du cabinet")
                .isEmpty();

        Element sortie = manche.select("[data-role=sortie]").first();
        assertThat(sortie).as("l'ecran de manche doit porter une sortie").isNotNull();
        assertThat(sortie.parents())
                .as("\"Les jeux\" reste hors du cabinet")
                .doesNotContain(cabinet);
    }

    /**
     * ADR-0030 point 1.c : aucun th:if ni th:unless sur le cabinet lui-meme, pour ne pas faire
     * entrer une condition dans l'appariement de saillance des boutons qu'il contient.
     */
    @Test
    void leCabinetNePorteAucuneConditionDeRendu() {
        Element cabinet = document("manche.html").select(".cabinet").first();
        assertThat(cabinet).as("le cabinet doit exister dans le fragment de manche").isNotNull();

        assertThat(cabinet.hasAttr("th:if")).as("le cabinet ne doit pas porter th:if").isFalse();
        assertThat(cabinet.hasAttr("th:unless")).as("le cabinet ne doit pas porter th:unless").isFalse();
    }

    /**
     * ADR-0030 point 1.d, nadia-prof section 6 de son arbitrage : le cabinet n'est du balisage que
     * de fragments/manche.html - jamais de fragments/retour.html ni d'accueil.html. C'est cette
     * absence ailleurs, et non une regle de masquage, qui garantit que le cabinet n'atteint ni
     * l'ecran de retour ni l'ecran de fin de partie.
     */
    @Test
    void leCabinetNAtteintNiLEcranDeRetourNiLAccueil() {
        assertThat(lire(gabarit("retour.html")))
                .as("le fragment de retour ne doit porter aucune trace du cabinet")
                .doesNotContain("cabinet");
        assertThat(lire(gabarit("accueil.html")))
                .as("l'accueil ne doit porter aucune trace du cabinet")
                .doesNotContain("cabinet");
    }

    /**
     * Critere d'acceptation S8-1 : les dimensions du decor ajoute sont exprimees en rem ou en %,
     * jamais en pixels. ADR-0013 : perimetre derive de {@link #SELECTEURS_DU_CABINET}, methode
     * parametree par une racine pour se mettre a l'epreuve sur un jeu fautif.
     */
    static void verifierAucunePixelSurLeCabinet(Path racine) {
        Map<String, String> maison = blocsMaison(racine);
        for (String selecteur : SELECTEURS_DU_CABINET) {
            String corps = maison.get(selecteur);
            assertThat(corps).as("le selecteur %s doit exister dans le CSS maison", selecteur).isNotNull();
            assertThat(corps.toLowerCase(Locale.ROOT))
                    .as("%s ne doit declarer aucune dimension en pixels", selecteur)
                    .doesNotContain("px");
        }
    }

    @Test
    void lesDimensionsDuCabinetSontExprimeesEnRemOuEnPourcentJamaisEnPixels() {
        verifierAucunePixelSurLeCabinet(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est la meme feuille de cabinet, avec une seule dimension
     * du bandeau ecrite en pixels plutot qu'en rem.
     */
    @Test
    void uneDimensionDuCabinetEnPixelsFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifierAucunePixelSurLeCabinet(
                CONTRE_EXEMPLES.resolve("dimension-en-pixels")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".cabinet-bandeau::before ne doit declarer aucune dimension en "
                        + "pixels");
    }

    /**
     * Critere d'acceptation S8-1 : le decor de levier (le disque decoratif de la regle S7-4) quitte
     * les deux boutons (nadia-prof, arbitrage prealable du 2026-08-11, section 4 : "le disque quitte
     * .demarrer et il quitte aussi .encore"). La forme ronde et l'ombre de relief, elles, restent sur
     * les deux - ce n'est pas la meme regle, et ce n'est pas retire.
     */
    @Test
    void leDisqueDecoratifAQuitteDemarrerEtEncoreMaisPasLaFormeRondeNiLOmbre() {
        Map<String, String> maison = blocsMaison(RACINE_PRODUCTION);

        assertThat(maison)
                .as("aucune regle ne doit plus cibler .demarrer::before")
                .doesNotContainKey(".demarrer::before");
        assertThat(maison)
                .as("aucune regle ne doit plus cibler .encore::before")
                .doesNotContainKey(".encore::before");

        String demarrer = maison.get(".demarrer");
        assertThat(demarrer).as(".demarrer doit exister dans le CSS maison").isNotNull();
        assertThat(debutDeDeclaration(demarrer, "border-radius"))
                .as(".demarrer garde sa forme ronde")
                .isNotNegative();
        assertThat(debutDeDeclaration(demarrer, "box-shadow"))
                .as(".demarrer garde son ombre de relief")
                .isNotNegative();

        String encore = maison.get(".encore");
        assertThat(encore).as(".encore doit exister dans le CSS maison").isNotNull();
        assertThat(debutDeDeclaration(encore, "border-radius"))
                .as(".encore garde sa forme ronde")
                .isNotNegative();
        assertThat(debutDeDeclaration(encore, "box-shadow"))
                .as(".encore garde son ombre de relief")
                .isNotNegative();
    }

    /**
     * Critere d'acceptation S8-3 : l'explication porte desormais un signal non textuel qui la
     * distingue du corps de page - un aplat de fond et un cadre complet sur les quatre cotes, jamais
     * un simple liseret lateral (nadia-prof, section 6, propriete 1 : "meme motif que pour .indice,
     * ou j'ai deja refuse le liseret au profit du cadre complet").
     */
    @Test
    void lExplicationPorteUnAplatEtUnCadreCompletJamaisUnLiseret() {
        Map<String, String> maison = blocsMaison(RACINE_PRODUCTION);
        String corps = maison.get(".explication");
        assertThat(corps).as(".explication doit exister dans le CSS maison").isNotNull();

        assertThat(debutDeDeclaration(corps, "background-color"))
                .as(".explication doit declarer un aplat de fond")
                .isNotNegative();

        int borderPos = debutDeDeclaration(corps, "border");
        assertThat(borderPos).as(".explication doit declarer un cadre").isNotNegative();
        String bordure = valeurDeclaree(corps, borderPos, "border");
        assertThat(mots(bordure))
                .as(".explication : border doit declarer largeur, style et couleur ensemble - un "
                        + "cadre complet sur les quatre cotes, pas seulement border-left ou "
                        + "border-bottom")
                .hasSize(3);
    }

    /**
     * Critere d'acceptation S8-3 : l'aplat de l'explication est neutre, identique apres une bonne et
     * apres une mauvaise reponse - aucune regle ne conditionne .explication au verdict de
     * fragments/retour.html.
     */
    @Test
    void lExplicationResteNeutreQuelQueSoitLeVerdict() {
        String css = lire(Path.of("src/main/resources/static/css/recreo.css"));
        assertThat(css)
                .as("aucune regle ne doit conditionner .explication a un coup juste")
                .doesNotContain(".signal.est-juste .explication")
                .doesNotContain(".est-juste .explication");
        assertThat(css)
                .as("aucune regle ne doit conditionner .explication a un coup faux")
                .doesNotContain(".signal.est-faux .explication")
                .doesNotContain(".est-faux .explication");
    }

    private static List<String> mots(String valeur) {
        return List.of(valeur.strip().split("\\s+"));
    }
}
