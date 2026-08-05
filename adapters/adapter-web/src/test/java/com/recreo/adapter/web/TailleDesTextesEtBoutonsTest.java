package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S2-1 : aucun texte lu par l'enfant ni aucun bouton actionnable ne doit rester sous la taille de
 * .consigne (1,25rem), reserves 6 et 7 de la fiche bandit-manchot-additions-cp. Lecture directe de
 * recreo.css par decoupage de blocs { }, jamais par expression reguliere (ADR-0008,
 * AssertionsWebTest).
 *
 * <p>Correction demandee par nadia-prof sur le refus de S2-1
 * (squad/conformite/FICHE-bandit-manchot-additions-cp.md) : la liste des boutons actionnables
 * n'est plus ecrite en dur ici, elle est relevee dans les gabarits eux-memes (tout &lt;button&gt;
 * et tout &lt;a href&gt;), pour qu'un bouton nouveau ou oublie soit attrape sans qu'on pense a
 * l'ajouter a une liste.
 *
 * <p>Deuxieme defaut demontre par nadia-prof (meme fiche, meme forme d'erreur qu'en S1) : la
 * premiere version de cette correction derivait la liste des boutons a verifier a partir de
 * leurs classes CSS. Un bouton reellement rendu sans aucune classe (donc invisible pour la
 * regle et retombant au defaut du navigateur, environ 13px) n'apparaissait dans aucune classe et
 * passait la regle sans etre controle. La regle part maintenant des elements &lt;button&gt; et
 * &lt;a href&gt; eux-memes ; pour chacun, elle verifie les regles CSS qui s'appliquent reellement
 * a lui (ses classes si elles existent, la balise nue sinon), pas une classe qu'on suppose
 * presente.
 */
class TailleDesTextesEtBoutonsTest {

    private static final Path CSS = Path.of("src/main/resources/static/css/recreo.css");
    private static final Path TEMPLATES = Path.of("src/main/resources/templates");
    private static final double CIBLE_TACTILE_REM = 2.75;

    /** Un commentaire CSS peut contenir des virgules ; il ne doit jamais entrer dans un selecteur. */
    private static String sansCommentaires(String source) {
        StringBuilder resultat = new StringBuilder();
        int position = 0;
        while (position < source.length()) {
            int debut = source.indexOf("/*", position);
            if (debut < 0) {
                resultat.append(source, position, source.length());
                break;
            }
            resultat.append(source, position, debut);
            int fin = source.indexOf("*/", debut);
            position = fin < 0 ? source.length() : fin + 2;
        }
        return resultat.toString();
    }

    private static Map<String, String> blocsParSelecteur() throws IOException {
        String source = sansCommentaires(Files.readString(CSS));
        Map<String, String> blocs = new HashMap<>();
        for (String bloc : source.split("\\}")) {
            int accolade = bloc.indexOf('{');
            if (accolade < 0) {
                continue;
            }
            String selecteurs = bloc.substring(0, accolade);
            String corps = bloc.substring(accolade + 1);
            for (String selecteur : selecteurs.split(",")) {
                blocs.merge(selecteur.strip(), corps, (ancien, nouveau) -> ancien + nouveau);
            }
        }
        return blocs;
    }

    private static double tailleEnRem(Map<String, String> blocs, String selecteur) {
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur %s doit exister dans recreo.css", selecteur).isNotNull();
        int position = corps.indexOf("font-size:");
        assertThat(position).as("%s doit declarer explicitement font-size", selecteur).isNotNegative();
        int finValeur = corps.indexOf(';', position);
        String valeur = corps.substring(position + "font-size:".length(), finValeur).strip();
        assertThat(valeur).as("%s : la taille doit etre exprimee en rem", selecteur).endsWith("rem");
        return Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
    }

    private static double proprieteEnRem(Map<String, String> blocs, String selecteur, String propriete) {
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur %s doit exister dans recreo.css", selecteur).isNotNull();
        String etiquette = propriete + ":";
        int position = corps.indexOf(etiquette);
        assertThat(position).as("%s doit declarer explicitement %s", selecteur, propriete).isNotNegative();
        int finValeur = corps.indexOf(';', position);
        String valeur = corps.substring(position + etiquette.length(), finValeur).strip();
        assertThat(valeur).as("%s : %s doit etre exprime en rem", selecteur, propriete).endsWith("rem");
        return Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
    }

    private static Optional<Double> tailleEnRemSiExiste(Map<String, String> blocs, String selecteur) {
        String corps = blocs.get(selecteur);
        if (corps == null) {
            return Optional.empty();
        }
        int position = corps.indexOf("font-size:");
        if (position < 0) {
            return Optional.empty();
        }
        int finValeur = corps.indexOf(';', position);
        String valeur = corps.substring(position + "font-size:".length(), finValeur).strip();
        if (!valeur.endsWith("rem")) {
            return Optional.empty();
        }
        return Optional.of(Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length())));
    }

    private static Optional<Double> proprieteEnRemSiExiste(
            Map<String, String> blocs, String selecteur, String propriete) {
        String corps = blocs.get(selecteur);
        if (corps == null) {
            return Optional.empty();
        }
        String etiquette = propriete + ":";
        int position = corps.indexOf(etiquette);
        if (position < 0) {
            return Optional.empty();
        }
        int finValeur = corps.indexOf(';', position);
        String valeur = corps.substring(position + etiquette.length(), finValeur).strip();
        if (!valeur.endsWith("rem")) {
            return Optional.empty();
        }
        return Optional.of(Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length())));
    }

    /**
     * Tout bouton et tout lien-bouton reellement rendu par un gabarit, releve dans le balisage et
     * non dans une liste maintenue a la main (motif du refus de nadia-prof sur ce test).
     */
    private static List<Element> boutonsActionnables() throws IOException {
        List<Element> boutons = new ArrayList<>();
        for (Path gabarit : List.of(
                TEMPLATES.resolve("accueil.html"),
                TEMPLATES.resolve("fragments/manche.html"),
                TEMPLATES.resolve("fragments/retour.html"),
                TEMPLATES.resolve("fragments/erreur.html"))) {
            Document document = Jsoup.parse(Files.readString(gabarit), gabarit.toString());
            boutons.addAll(document.select("button, a[href]"));
        }
        return boutons;
    }

    /**
     * Les regles CSS qui s'appliquent reellement a cet element : ses classes si l'element en
     * porte, la balise nue sinon. Un element sans classe qui ne correspond a aucune regle de
     * balise nue tombe au defaut du navigateur : c'est exactement ce que cette derivation doit
     * detecter, plutot que de le laisser passer parce qu'il n'a pas de classe a controler.
     */
    private static List<String> selecteursApplicables(Element element) {
        List<String> classes = element.classNames().stream().map(classe -> "." + classe).toList();
        return classes.isEmpty() ? List.of(element.tagName()) : classes;
    }

    private static String etiquette(Element element) {
        String description = element.classNames().isEmpty()
                ? "<" + element.tagName() + "> sans classe"
                : "." + String.join(".", element.classNames());
        return description + " \"" + element.text() + "\" (" + element.ownerDocument().location() + ")";
    }

    @Test
    void auMoinsUnBoutonEstReleveDansLesGabarits() throws IOException {
        assertThat(boutonsActionnables())
                .as("cette regle ne prouve rien si aucun bouton n'est passe au crible")
                .isNotEmpty();
    }

    @Test
    void aucunTexteNePasseSousLaTailleDeLaConsigne() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        double consigne = tailleEnRem(blocs, ".consigne");
        assertThat(consigne).isEqualTo(1.25);

        for (String selecteur : new String[] {
                ".score", ".indice", ".fin-de-partie", ".erreur", ".pas-de-jeu", ".message-reprise"
        }) {
            assertThat(tailleEnRem(blocs, selecteur))
                    .as("%s ne doit jamais passer sous la consigne (%.2frem)", selecteur, consigne)
                    .isGreaterThanOrEqualTo(consigne);
        }
    }

    /**
     * S2-1, motif du refus : "au minimum" est un plancher, pas une liste fermee, et un bouton de
     * reponse est un bouton actionnable comme un autre. La liste testee vient des gabarits.
     */
    @Test
    void toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        List<Element> boutons = boutonsActionnables();
        assertThat(boutons).isNotEmpty();

        for (Element bouton : boutons) {
            for (String selecteur : selecteursApplicables(bouton)) {
                Optional<Double> taille = tailleEnRemSiExiste(blocs, selecteur);
                assertThat(taille)
                        .as("%s : la regle %s doit fixer une taille de police en rem, "
                                        + "sinon le bouton retombe au defaut du navigateur (environ 13px)",
                                etiquette(bouton), selecteur)
                        .isPresent();
                assertThat(taille.get())
                        .as("%s ne doit jamais retomber au defaut du navigateur (environ 13px)", etiquette(bouton))
                        .isGreaterThanOrEqualTo(1.25);
            }
        }
    }

    /**
     * Deuxieme motif du refus : une cible tactile de 46 x 41 px avec 8 px d'espacement est sous le
     * minimum de 44 px. min-height/min-width garantissent un plancher independant du contenu.
     */
    @Test
    void toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        List<Element> boutons = boutonsActionnables();
        assertThat(boutons).isNotEmpty();

        for (Element bouton : boutons) {
            for (String selecteur : selecteursApplicables(bouton)) {
                Optional<Double> hauteur = proprieteEnRemSiExiste(blocs, selecteur, "min-height");
                assertThat(hauteur)
                        .as("%s : la regle %s doit fixer min-height en rem, sinon la cible tactile "
                                        + "retombe au defaut du navigateur",
                                etiquette(bouton), selecteur)
                        .isPresent();
                assertThat(hauteur.get())
                        .as("%s : la hauteur minimale doit atteindre au moins 44px (2,75rem)", etiquette(bouton))
                        .isGreaterThanOrEqualTo(CIBLE_TACTILE_REM);
            }
        }
    }

    @Test
    void lIndiceEstAuMoinsAussiGrandQueLaConsigne() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        assertThat(tailleEnRem(blocs, ".indice"))
                .isGreaterThanOrEqualTo(tailleEnRem(blocs, ".consigne"));
    }

    @Test
    void h1PorteUneRegleDeTailleExplicite() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        assertThat(tailleEnRem(blocs, "h1")).isGreaterThan(0);
    }

    /**
     * S3-4, reserve 1 de nadia-prof sur S2-1 : le chiffre d'un bouton de reponse est ce qui
     * distingue les trois propositions, il doit donc peser au moins autant que le calcul affiche
     * a l'ecran (.enonce), et non rester le detail le plus petit d'une large barre.
     */
    @Test
    void leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        double proposition = tailleEnRem(blocs, ".proposition");

        assertThat(proposition).isGreaterThanOrEqualTo(2.0);
        assertThat(proposition).isGreaterThanOrEqualTo(tailleEnRem(blocs, ".enonce"));
    }

    /**
     * S3-5, reserve 2 de nadia-prof sur S2-1 : un ecart d'au moins 1 rem entre deux boutons de
     * reponse, au-dessus du plancher de 0,75rem constate en s02, pour un geste d'enfant encore
     * imprecis.
     */
    @Test
    void lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRem() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        assertThat(proprieteEnRem(blocs, ".propositions", "gap")).isGreaterThanOrEqualTo(1.0);
    }
}
