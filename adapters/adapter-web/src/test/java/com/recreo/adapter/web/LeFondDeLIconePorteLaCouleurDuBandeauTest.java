package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.recreo.adapter.web.FaviconTest.entreeIco;
import static com.recreo.adapter.web.FaviconTest.taillesDeclarees;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S6-10 : le bandeau change de couleur (arbitrage de `nadia-prof` du 2026-08-07,
 * `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
 * developpement - S6-10") et le fond opaque des trois vignettes de {@code favicon.ico} est
 * recolore a l'identique dans le meme commit, pour que le carre de fond ne redevienne pas visible.
 *
 * <p>Motif nomme par `nadia-prof` : <b>ce couplage entre 1855 pixels d'un binaire et une variable
 * CSS est invisible dans une relecture de diff.</b> Sans garde-fou, la verification qu'elle vient
 * de faire a la main est a usage unique, et le prochain changement de couleur de bandeau referait
 * la meme erreur.
 *
 * <p>La propriete tenue, exactement dans ses mots : le fond opaque de chacune des trois vignettes
 * porte exactement la couleur declaree par {@code --recreo-entete-fond} dans {@code recreo.css},
 * et aucun pixel n'y porte plus {@code --recreo-accent}. Les deux valeurs sont lues dans la
 * feuille a chaque execution - jamais ecrites en dur ici - pour qu'un futur changement de teinte
 * du bandeau reste couvert sans toucher ce fichier ; seuls les <b>noms</b> des deux variables sont
 * fixes, parce que c'est leur identite que `nadia-prof` nomme, pas leur valeur du jour.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : les tailles de vignette a controler viennent de l'en-tete
 *       ICONDIR du fichier ({@link FaviconTest#taillesDeclarees}), jamais d'une liste ecrite ici -
 *       une quatrieme vignette entrerait automatiquement dans le controle ;</li>
 *   <li><b>derivation totale</b> : aucune vignette declaree, {@code :root} absent, une des deux
 *       variables non declaree, ou {@code .entete} qui ne pointe plus vers la variable de fond
 *       sont des <b>echecs</b>, jamais des cas ignores ;</li>
 *   <li><b>mise a l'epreuve</b> : deux jeux fautifs neufs, l'un ou l'icone n'est pas recoloree,
 *       l'autre ou la feuille cesse de relier {@code .entete} a la variable, et par mutation
 *       reelle du depot.</li>
 * </ul>
 */
class LeFondDeLIconePorteLaCouleurDuBandeauTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LeFondDeLIconePorteLaCouleurDuBandeauTest");

    private static final String VARIABLE_FOND_ENTETE = "--recreo-entete-fond";
    private static final String VARIABLE_ACCENT = "--recreo-accent";
    private static final String SELECTEUR_ENTETE = ".entete";
    private static final String PROPRIETE_FOND = "background-color";
    private static final String ICONE = "static/favicon.ico";

    /** Une couleur RGB, telle que declaree en hexadecimal par une variable CSS maison. */
    record Couleur(int rouge, int vert, int bleu) {

        /** Le pixel ARGB (alpha 255) que cette couleur produit sur une vignette opaque. */
        int argbOpaque() {
            return (255 << 24) | (rouge << 16) | (vert << 8) | bleu;
        }
    }

    private static int composante(String chiffresHexa, int position) {
        return Integer.parseInt(chiffresHexa.substring(position, position + 2), 16);
    }

    /** ADR-0008 : lu caractere par caractere, sans expression reguliere, comme le reste du module. */
    static Couleur depuisHex(String valeur) {
        String hex = valeur.strip();
        assertThat(hex).as("une couleur de variable doit etre ecrite en #rrggbb").startsWith("#");
        String chiffres = hex.substring(1);
        assertThat(chiffres).as("#rrggbb attend exactement six chiffres hexadecimaux").hasSize(6);
        return new Couleur(composante(chiffres, 0), composante(chiffres, 2), composante(chiffres, 4));
    }

    static Couleur couleurDeVariable(Path racineDesFeuilles, String nomVariable) {
        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        String racine = maison.get(":root");
        assertThat(racine).as(":root doit exister dans le CSS maison pour declarer %s", nomVariable)
                .isNotNull();
        int position = debutDeDeclaration(racine, nomVariable);
        assertThat(position).as(":root doit declarer %s", nomVariable).isNotNegative();
        return depuisHex(valeurDeclaree(racine, nomVariable));
    }

    /** Vrai seulement si .entete lit litteralement la variable de fond, pas une couleur en dur. */
    static boolean enteteUtiliseLaVariableDeFond(Path racineDesFeuilles) {
        Map<String, String> maison = blocsMaison(racineDesFeuilles);
        String corps = maison.get(SELECTEUR_ENTETE);
        if (corps == null) {
            return false;
        }
        int position = debutDeDeclaration(corps, PROPRIETE_FOND);
        if (position < 0) {
            return false;
        }
        String valeur = valeurDeclaree(corps, PROPRIETE_FOND).strip();
        return valeur.equals("var(" + VARIABLE_FOND_ENTETE + ")");
    }

    private static Set<Integer> couleursOpaques(BufferedImage vignette) {
        Set<Integer> couleurs = new HashSet<>();
        for (int y = 0; y < vignette.getHeight(); y++) {
            for (int x = 0; x < vignette.getWidth(); x++) {
                int argb = vignette.getRGB(x, y);
                if ((argb >>> 24) == 255) {
                    couleurs.add(argb);
                }
            }
        }
        return couleurs;
    }

    private static String enHexa(Couleur couleur) {
        return String.format("#%02x%02x%02x", couleur.rouge(), couleur.vert(), couleur.bleu());
    }

    static void verifierQueLeFondDeLIconeSuitLaVariable(Path racine) throws IOException {
        assertThat(enteteUtiliseLaVariableDeFond(racine))
                .as("%s doit declarer %s: var(%s), sinon la propriete controlee plus bas ne dit "
                        + "rien du bandeau reellement rendu",
                        SELECTEUR_ENTETE, PROPRIETE_FOND, VARIABLE_FOND_ENTETE)
                .isTrue();

        Couleur fond = couleurDeVariable(racine, VARIABLE_FOND_ENTETE);
        Couleur accent = couleurDeVariable(racine, VARIABLE_ACCENT);

        byte[] ico = Files.readAllBytes(racine.resolve(ICONE));
        List<Integer> tailles = taillesDeclarees(ico);
        assertThat(tailles)
                .as("cette regle ne prouve rien si favicon.ico ne declare aucune vignette")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>();
        for (int taille : tailles) {
            BufferedImage vignette = entreeIco(ico, taille);
            Set<Integer> couleurs = couleursOpaques(vignette);
            if (couleurs.contains(accent.argbOpaque())) {
                defauts.add("la vignette " + taille + "x" + taille + " porte encore "
                        + VARIABLE_ACCENT + " (" + enHexa(accent) + ") : le fond n'a pas ete "
                        + "recolore sur toutes les tailles");
            }
            if (!couleurs.contains(fond.argbOpaque())) {
                defauts.add("la vignette " + taille + "x" + taille + " ne porte pas "
                        + VARIABLE_FOND_ENTETE + " (" + enHexa(fond) + ") : le fond ne suit pas la "
                        + "couleur du bandeau");
            }
        }
        assertThat(defauts).isEmpty();
    }

    @Test
    void leFondDeLIconeSuitLaVariableDeFondSurLaProduction() throws IOException {
        verifierQueLeFondDeLIconeSuitLaVariable(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : jeu fautif neuf, feuille correcte mais favicon.ico jamais recolore -
     * la forme exacte de l'oubli que ce garde-fou existe pour attraper.
     */
    @Test
    void uneVignetteQuiPorteEncoreLancienAccentFaitEchouerLeGardeFou() throws IOException {
        Path racine = CONTRE_EXEMPLES.resolve("vignette-non-recoloree");
        assertThatThrownBy(() -> verifierQueLeFondDeLIconeSuitLaVariable(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("porte encore " + VARIABLE_ACCENT);
    }

    /**
     * L'autre sens de la meme faute : l'icone est recoloree, mais .entete ne pointe plus vers la
     * variable de fond (revenu a l'accent, ou fige en couleur litterale) - le couplage que la
     * regle promet de tenir ne dirait alors plus rien du bandeau reellement rendu.
     */
    @Test
    void uneFeuilleQuiNePointePlusVersLaVariableDeFondFaitEchouerLeGardeFou() throws IOException {
        Path racine = CONTRE_EXEMPLES.resolve("entete-sans-variable");
        assertThatThrownBy(() -> verifierQueLeFondDeLIconeSuitLaVariable(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(SELECTEUR_ENTETE + " doit declarer " + PROPRIETE_FOND);
    }
}
