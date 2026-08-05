package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    /**
     * Tout bouton et tout lien-bouton reellement rendu par un gabarit, releve dans le balisage et
     * non dans une liste maintenue a la main (motif du refus de nadia-prof sur ce test).
     */
    private static Set<String> classesDeBoutonsActionnables() throws IOException {
        Set<String> classes = new TreeSet<>();
        for (Path gabarit : List.of(
                TEMPLATES.resolve("accueil.html"),
                TEMPLATES.resolve("fragments/manche.html"),
                TEMPLATES.resolve("fragments/retour.html"),
                TEMPLATES.resolve("fragments/erreur.html"))) {
            Document document = Jsoup.parse(Files.readString(gabarit));
            for (Element element : document.select("button, a[href]")) {
                for (String classe : element.classNames()) {
                    classes.add("." + classe);
                }
            }
        }
        return classes;
    }

    @Test
    void auMoinsUnBoutonEstReleveDansLesGabarits() throws IOException {
        assertThat(classesDeBoutonsActionnables())
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
        Set<String> boutons = classesDeBoutonsActionnables();
        assertThat(boutons).isNotEmpty();

        for (String bouton : boutons) {
            assertThat(tailleEnRem(blocs, bouton))
                    .as("%s ne doit jamais retomber au defaut du navigateur (environ 13px)", bouton)
                    .isGreaterThanOrEqualTo(1.25);
        }
    }

    /**
     * Deuxieme motif du refus : une cible tactile de 46 x 41 px avec 8 px d'espacement est sous le
     * minimum de 44 px. min-height/min-width garantissent un plancher independant du contenu.
     */
    @Test
    void toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels() throws IOException {
        Map<String, String> blocs = blocsParSelecteur();
        Set<String> boutons = classesDeBoutonsActionnables();
        assertThat(boutons).isNotEmpty();

        for (String bouton : boutons) {
            assertThat(proprieteEnRem(blocs, bouton, "min-height"))
                    .as("%s : la hauteur minimale doit atteindre au moins 44px (2,75rem)", bouton)
                    .isGreaterThanOrEqualTo(CIBLE_TACTILE_REM);
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
}
