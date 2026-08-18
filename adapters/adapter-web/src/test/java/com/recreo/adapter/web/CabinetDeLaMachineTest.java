package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.classesDuSelecteur;
import static com.recreo.adapter.web.LAnimationRetardeMaisNeDetermineRienTest.reglesMaison;
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

    /**
     * Le prefixe qui derive la population du cabinet (correctif S10, theo-archi) : toute classe qui
     * commence par ce mot vit sous le controle "aucun pixel", sans qu'une liste ecrite a la main ait
     * besoin d'etre tenue a jour a chaque decor ajoute.
     */
    private static final String PREFIXE_CABINET = "cabinet";

    /**
     * Les selecteurs du cabinet, derives (ADR-0013 obligation 1) et non plus enumeres : toute regle
     * maison dont le selecteur nomme une classe commencant par {@link #PREFIXE_CABINET}.
     *
     * <p>Jusqu'a S10, cette population etait une liste Java ecrite a la main (S8-1 en avait livre
     * cinq). Un decor ajoute au cabinet sans qu'on pense a l'ajouter a cette liste - le visage, son
     * enseigne, le levier de flanc et ses etoiles de socle en ont ajoute dix d'un coup - echappait au
     * controle "aucun pixel" sans qu'aucun rouge ne le dise. La derivation ferme ce trou : elle relit
     * la feuille maison a chaque appel, donc un decor neuf entre dans le controle des que son
     * selecteur y est ecrit, sans que ce fichier change.
     *
     * <p>Toujours derivee de {@link #RACINE_PRODUCTION}, quelle que soit la racine sur laquelle
     * {@link #verifierAucunePixelSurLeCabinet} est ensuite appelee : c'est ce qui permet de
     * reutiliser la meme reference de selecteurs pour eprouver un jeu fautif (obligation 3) sans que
     * ce jeu, en se relisant lui-meme, se contente de confirmer sa propre liste.
     */
    static List<String> selecteursDuCabinet(Path racine) {
        Set<String> selecteurs = new LinkedHashSet<>();
        for (LAnimationRetardeMaisNeDetermineRienTest.Regle regle : reglesMaison(racine)) {
            boolean visePrefixeCabinet = classesDuSelecteur(regle.selecteur()).stream()
                    .anyMatch(classe -> classe.startsWith(PREFIXE_CABINET));
            if (visePrefixeCabinet) {
                selecteurs.add(regle.selecteur());
            }
        }
        return List.copyOf(selecteurs);
    }

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
     * se presse (C2 de nadia-prof).
     *
     * <p>S15-3 (troisieme arbitrage de l'utilisatrice, DEC-0034) : la seconde moitie de ce test
     * affirmait que le fragment de manche portait lui-meme une sortie ({@code [data-role=sortie]})
     * et que celle-ci restait hors du cabinet. <b>Motif ecrit du retrait, exige par ADR-0013</b> :
     * "Les jeux" quitte entierement ce fragment - l'utilisatrice demande la sortie dans l'en-tete,
     * y compris pendant une partie, et {@code fragments/manche.html} ne porte donc plus aucun
     * bouton {@code .retour-accueil}. La propriete "hors du cabinet" ne se pose plus dans ce
     * fragment : il n'y a plus de sortie a y confronter au cabinet. Ce que gardait cette moitie -
     * une sortie visible et atteignable pendant la manche - est repris par l'en-tete permanent
     * (accueil.html, {@code .lien-accueil}) ; sa regle de masquage sur cet ecran precis est en
     * cours de revision par theo-archi et n'est pas couverte ici.</p>
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
    }

    /**
     * S15-3 (DEC-0034) : epingle l'etat nouveau plutot que de laisser une propriete disparaitre en
     * silence - le fragment de manche ne porte plus aucune sortie du tout, la seule qui subsiste
     * pendant une partie est celle de l'en-tete permanent.
     */
    @Test
    void leFragmentDeMancheNePorteDesormaisAucuneSortie() {
        Document manche = document("manche.html");

        assertThat(manche.select("[data-role=sortie]"))
                .as("S15-3 : \"Les jeux\" a quitte ce fragment pour l'en-tete permanent, y compris "
                        + "pendant une partie en cours")
                .isEmpty();
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
     * jamais en pixels. ADR-0013 : perimetre derive de {@link #selecteursDuCabinet}, methode
     * parametree par une racine pour se mettre a l'epreuve sur un jeu fautif - la reference des
     * selecteurs a verifier, elle, vient toujours de {@link #RACINE_PRODUCTION} (voir sa Javadoc).
     *
     * <p>Tous les selecteurs fautifs sont rassembles avant d'echouer, et cela n'est pas un confort de
     * lecture. Ecrite en echec au premier fautif, cette boucle ne laissait le jeu fautif prouver qu'un
     * seul selecteur - celui que l'ordre de la liste faisait rencontrer d'abord - et un selecteur
     * ajoute apres lui n'etait jamais eprouve, sans qu'aucun rouge le dise (ADR-0033 point 7.a, ecart
     * mesure). En rassemblant, le message nomme chaque fautif, ce qui rend l'ordre de la liste sans
     * effet sur ce que le contre-exemple prouve.
     */
    static void verifierAucunePixelSurLeCabinet(Path racine) {
        Map<String, String> maison = blocsMaison(racine);
        List<String> motifs = new ArrayList<>();
        for (String selecteur : selecteursDuCabinet(RACINE_PRODUCTION)) {
            String corps = maison.get(selecteur);
            if (corps == null) {
                motifs.add("le selecteur " + selecteur + " doit exister dans le CSS maison");
            } else if (corps.toLowerCase(Locale.ROOT).contains("px")) {
                motifs.add(selecteur + " ne doit declarer aucune dimension en pixels");
            }
        }
        assertThat(motifs)
                .as("le cabinet se dimensionne en rem ou en pourcent, jamais en pixels (S8-1)")
                .isEmpty();
    }

    @Test
    void lesDimensionsDuCabinetSontExprimeesEnRemOuEnPourcentJamaisEnPixels() {
        verifierAucunePixelSurLeCabinet(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est la meme feuille de cabinet, ou chaque selecteur epingle
     * porte une dimension en pixels.
     *
     * <p>L'attente est derivee de {@link #selecteursDuCabinet}, jamais ecrite en clair : un selecteur
     * ajoute a la production sans recevoir son bloc fautif rend ce test rouge, en le nommant, et c'est
     * ce qui empeche un selecteur d'entrer dans le perimetre sans y etre eprouve (ADR-0033 point 7.a).
     */
    @Test
    void chaqueSelecteurEpingleEstEprouveParLeJeuFautif() {
        List<String> selecteurs = selecteursDuCabinet(RACINE_PRODUCTION);
        assertThat(selecteurs)
                .as("ADR-0003 : cette clause ne prouve rien si aucune regle maison de %s ne nomme "
                        + "une classe commencant par \"%s\"", RACINE_PRODUCTION, PREFIXE_CABINET)
                .isNotEmpty();

        var echec = assertThatThrownBy(() -> verifierAucunePixelSurLeCabinet(
                CONTRE_EXEMPLES.resolve("dimension-en-pixels")))
                .isInstanceOf(AssertionError.class);
        for (String selecteur : selecteurs) {
            echec.hasMessageContaining(selecteur + " ne doit declarer aucune dimension en pixels");
        }
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
