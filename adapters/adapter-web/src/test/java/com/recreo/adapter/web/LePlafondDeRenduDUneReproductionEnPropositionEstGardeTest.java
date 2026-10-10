package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.declarationsDe;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.proprieteEnRemSiExiste;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurALaPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>ADR-0104 decision 4, clause 3, et ADR-0082 : le plafond de rendu d'une reproduction proposee ne
 * monte pas sans qu'un test rougisse.</b> Dette F4 ouverte a s55, due avec le premier paquet a
 * propositions en image. Jusqu'ici le plafond de {@code .image-item} (11rem, 176 px) n'etait garde
 * par rien : le test de poids porte 176 en constante et ne lit pas la feuille.
 *
 * <p>La forme (avis de faisabilite de s56, A.2) : le plafond est porte <b>sur l'image</b>,
 * {@code max-width} et {@code max-height} de la forme {@code min(100%, <x>rem)}. La case peut
 * grandir sans que le dessin grandisse. Trois clauses, lues sur la feuille maison :</p>
 * <ol>
 *   <li>le {@code max-height} de {@code .image-item}, en pixels, egale {@link #PLAFOND_DE_RENDU_EN_PIXELS} :
 *       l'ancre qui lie la feuille au nombre decide, au lieu de suivre la feuille ;</li>
 *   <li>toute regle dont le selecteur contient {@code propositions-oeuvres} et dont le sujet est
 *       {@code img} ne declare {@code max-width} et {@code max-height} que sous la forme
 *       {@code min(100%, <x>rem)} avec {@code x} fois 16 au plus egal au plafond, et l'une de ces
 *       regles declare les deux ;</li>
 *   <li>aucune de ces regles ne declare {@code min-width}, {@code min-height}, ni {@code width} ou
 *       {@code height} autres que {@code auto}.</li>
 * </ol>
 *
 * <p>ADR-0013 : perimetre derive du selecteur et non d'une liste (clauses 2 et 3), population non
 * vide exigee, et mise a l'epreuve sur la feuille reelle mutee en memoire, jamais sur une fixture.
 * Ce que ce test ne voit pas : une regle de la feuille vendorisee, une regle sous {@code @media}, une
 * regle dont le selecteur atteint l'image sans nommer {@code propositions-oeuvres}
 * (par exemple {@code .cabinet img}), un attribut {@code style} du gabarit. Le rendu reel se juge a
 * l'ecran (releve {@code Q7}, audit de {@code nadia-prof}).</p>
 */
class LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    /**
     * ADR-0082, ADR-0104 decision 4.3 : 11rem, soit 176 px CSS. Meme nombre que
     * {@code ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest} ; un
     * plafond qui monte demande des fichiers de plus de 352 px et amende ADR-0082.
     */
    private static final int PLAFOND_DE_RENDU_EN_PIXELS = 176;

    /** ADR-0013 obligation 3 : le jeu fautif commite de ce garde-fou, sa propre feuille. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest");

    private static final String ANCRE = ".image-item";

    private static final String CLAUSE_1 = "clause 1";
    private static final String CLAUSE_2 = "clause 2";
    private static final String CLAUSE_3 = "clause 3";

    private static final String BASE = ".propositions-oeuvres .proposition img";
    private static final String ETAT = ".propositions-oeuvres .proposition.deja-essayee img";

    /** Les selecteurs de la feuille dont le sujet est une image rendue dans la rangee d'oeuvres. */
    static List<String> selecteursDeLImage(Map<String, String> blocs) {
        return blocs.keySet().stream()
                .filter(selecteur -> selecteur.contains("propositions-oeuvres"))
                .filter(selecteur -> sujetEstUneImage(selecteur))
                .sorted()
                .toList();
    }

    /** Le dernier compose du selecteur : ce qui suit le dernier espace ou combinateur. */
    private static boolean sujetEstUneImage(String selecteur) {
        int coupe = Math.max(Math.max(selecteur.lastIndexOf(' '), selecteur.lastIndexOf('>')),
                Math.max(selecteur.lastIndexOf('+'), selecteur.lastIndexOf('~')));
        String sujet = selecteur.substring(coupe + 1);
        return sujet.equals("img") || (sujet.startsWith("img") && !Character.isLetter(sujet.charAt(3)));
    }

    /**
     * Le juge, pur : rend la liste des griefs, vide quand les trois clauses tiennent. Recoit la
     * feuille aplatie par selecteur ({@code TailleDesTextesEtBoutonsTest.blocsMaison}), jamais un
     * fichier.
     */
    static List<String> griefs(Map<String, String> blocs, int plafondEnPixels) {
        List<String> griefs = new ArrayList<>();
        griefsDeLAncre(blocs, plafondEnPixels, griefs);
        List<String> selecteurs = selecteursDeLImage(blocs);
        boolean uneRegleDeclareLesDeux = false;
        for (String selecteur : selecteurs) {
            String corps = blocs.get(selecteur);
            boolean largeur = !declarationsDe(corps, "max-width").isEmpty();
            boolean hauteur = !declarationsDe(corps, "max-height").isEmpty();
            uneRegleDeclareLesDeux |= largeur && hauteur;
            for (String propriete : List.of("max-width", "max-height")) {
                for (int position : declarationsDe(corps, propriete)) {
                    String valeur = valeurALaPosition(corps, position, propriete);
                    if (!estUnPlafondSousLeNombreDecide(valeur, plafondEnPixels)) {
                        griefs.add(CLAUSE_2 + " : " + selecteur + " declare " + propriete + ": " + valeur
                                + ", qui n'est pas min(100%, <x>rem) avec x fois 16 au plus egal a "
                                + plafondEnPixels + " px (ADR-0104 decision 4.3)");
                    }
                }
            }
            for (String propriete : List.of("min-width", "min-height")) {
                if (!declarationsDe(corps, propriete).isEmpty()) {
                    griefs.add(CLAUSE_3 + " : " + selecteur + " declare " + propriete
                            + ", qui contourne le plafond porte par max-width et max-height");
                }
            }
            for (String propriete : List.of("width", "height")) {
                for (int position : declarationsDe(corps, propriete)) {
                    String valeur = valeurALaPosition(corps, position, propriete);
                    if (!valeur.equals("auto")) {
                        griefs.add(CLAUSE_3 + " : " + selecteur + " declare " + propriete + ": " + valeur
                                + " au lieu de auto, ce qui contourne le plafond");
                    }
                }
            }
        }
        if (!uneRegleDeclareLesDeux) {
            griefs.add(CLAUSE_2 + " : aucune regle de la feuille ne declare a la fois max-width et max-height"
                    + " sur l'image d'une proposition (selecteurs lus : " + selecteurs + ")");
        }
        return griefs;
    }

    private static void griefsDeLAncre(Map<String, String> blocs, int plafondEnPixels, List<String> griefs) {
        String corps = blocs.get(ANCRE);
        if (corps == null) {
            griefs.add(CLAUSE_1 + " : " + ANCRE + " n'existe pas dans la feuille maison");
            return;
        }
        Optional<Double> rem = proprieteEnRemSiExiste(corps, "max-height");
        if (rem.isEmpty()) {
            griefs.add(CLAUSE_1 + " : " + ANCRE + " ne declare pas max-height en rem");
        } else if (Math.round(rem.get() * 16) != plafondEnPixels) {
            griefs.add(CLAUSE_1 + " : le max-height de " + ANCRE + " vaut " + rem.get() + "rem, soit "
                    + Math.round(rem.get() * 16) + " px, et non " + plafondEnPixels
                    + " px (un plafond de base qui change est un amendement d'ADR-0082)");
        }
    }

    /** {@code min(100%, <x>rem)}, espaces ignores, avec x fois 16 au plus egal au plafond. */
    private static boolean estUnPlafondSousLeNombreDecide(String valeur, int plafondEnPixels) {
        String compacte = valeur.replace(" ", "");
        String debut = "min(100%,";
        if (!compacte.startsWith(debut) || !compacte.endsWith("rem)")) {
            return false;
        }
        String nombre = compacte.substring(debut.length(), compacte.length() - "rem)".length());
        try {
            return Double.parseDouble(nombre) * 16 <= plafondEnPixels;
        } catch (NumberFormatException illisible) {
            return false;
        }
    }

    /** La feuille reelle, aplatie, avec une mutation appliquee au corps du selecteur nomme. */
    private static Map<String, String> feuilleMutee(String selecteur, UnaryOperator<String> mutation) {
        Map<String, String> blocs = new LinkedHashMap<>(blocsMaison(RACINE_PRODUCTION));
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur mute %s doit exister dans la feuille reelle", selecteur).isNotNull();
        String mute = mutation.apply(corps);
        assertThat(mute).as("la mutation de %s doit changer la feuille, sans quoi elle ne prouve rien",
                selecteur).isNotEqualTo(corps);
        blocs.put(selecteur, mute);
        return blocs;
    }

    @Test
    void laFeuilleReelleTientLesTroisClauses() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);

        assertThat(selecteursDeLImage(blocs))
                .as("ADR-0003 : ce garde-fou ne prouve rien s'il ne lit aucune regle d'image de proposition")
                .contains(BASE, ETAT);
        assertThat(griefs(blocs, PLAFOND_DE_RENDU_EN_PIXELS)).isEmpty();
    }

    /** P1' : le plafond de l'image monte a 14rem. */
    @Test
    void unPlafondDeImageQuiMonteFaitEchouerLaClause2() {
        Map<String, String> mute = feuilleMutee(BASE, corps -> corps.replace("min(100%, 11rem)", "min(100%, 14rem)"));

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).anyMatch(grief -> grief.startsWith(CLAUSE_2)
                && grief.contains("min(100%, 14rem)"));
    }

    /** P2' : le plafond de l'image disparait, il ne reste que 100% de la case. */
    @Test
    void unPlafondDeImageRemplaceParCentPourCentFaitEchouerLaClause2() {
        Map<String, String> mute = feuilleMutee(BASE, corps -> corps.replace("min(100%, 11rem)", "100%"));

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).anyMatch(grief -> grief.startsWith(CLAUSE_2));
    }

    /** P3' : une regle d'etat declare un max-height hors de la forme. */
    @Test
    void unMaxHeightHorsDeLaFormeSurLEtatEssayeFaitEchouerLaClause2() {
        Map<String, String> mute = feuilleMutee(ETAT, corps -> corps + ";max-height: 14rem");

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).anyMatch(grief -> grief.startsWith(CLAUSE_2)
                && grief.contains(ETAT));
    }

    /** P4' : un min-height qui force l'image au-dela du plafond. */
    @Test
    void unMinHeightSurLImageFaitEchouerLaClause3() {
        Map<String, String> mute = feuilleMutee(BASE, corps -> corps + ";min-height: 14rem");

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).anyMatch(grief -> grief.startsWith(CLAUSE_3)
                && grief.contains("min-height"));
    }

    /** Clause 3, seconde moitie : une hauteur autre que auto contourne aussi le plafond. */
    @Test
    void uneHauteurAutreQueAutoSurLImageFaitEchouerLaClause3() {
        Map<String, String> mute = feuilleMutee(BASE, corps -> corps.replace("height: auto", "height: 14rem"));

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).anyMatch(grief -> grief.startsWith(CLAUSE_3)
                && grief.contains("height: 14rem"));
    }

    /** P5' : le plafond de base monte a 14rem. */
    @Test
    void unPlafondDeBaseQuiMonteFaitEchouerLaClause1() {
        Map<String, String> mute = feuilleMutee(ANCRE, corps -> corps.replace("max-height: 11rem", "max-height: 14rem"));

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).anyMatch(grief -> grief.startsWith(CLAUSE_1));
    }

    /** La regle de base doit declarer les deux plafonds : une image sans max-height n'est pas bornee. */
    @Test
    void uneRegleDeBaseSansMaxHeightFaitEchouerLaClause2() {
        Map<String, String> mute = new LinkedHashMap<>(blocsMaison(RACINE_PRODUCTION));
        mute.put(BASE, mute.get(BASE).replace("max-height: min(100%, 11rem)", ""));

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS))
                .anyMatch(grief -> grief.startsWith(CLAUSE_2) && grief.contains("aucune regle"));
    }

    /** Le juge reste muet sur une feuille en regle : la seule borne est le plafond, pas la case. */
    @Test
    void unPlafondPlusBasQueLeNombreDecideNeFaitPasEchouerLeGardeFou() {
        Map<String, String> mute = feuilleMutee(BASE, corps -> corps.replace("min(100%, 11rem)", "min(100%, 9rem)"));

        assertThat(griefs(mute, PLAFOND_DE_RENDU_EN_PIXELS)).isEmpty();
    }

    /**
     * ADR-0013 obligation 3, sur le jeu fautif commite : sa feuille viole les trois clauses, et le juge
     * les nomme toutes les trois.
     */
    @Test
    void leJeuFautifCommiteEstRefuse() {
        Map<String, String> blocs = blocsMaison(CONTRE_EXEMPLE);

        assertThat(selecteursDeLImage(blocs))
                .as("le jeu fautif ne prouve rien s'il ne porte aucune regle d'image de proposition")
                .isNotEmpty();
        assertThatThrownBy(() -> assertThat(griefs(blocs, PLAFOND_DE_RENDU_EN_PIXELS)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(CLAUSE_1)
                .hasMessageContaining(CLAUSE_2)
                .hasMessageContaining(CLAUSE_3);
    }

    @Test
    void lePerimetreSeDeriveDuSelecteurEtPasDUneListe() {
        Map<String, String> blocs = new LinkedHashMap<>(blocsMaison(RACINE_PRODUCTION));
        blocs.put(".propositions-oeuvres .proposition.nouvelle-regle img", "min-height: 14rem");

        assertThatThrownBy(() -> assertThat(griefs(blocs, PLAFOND_DE_RENDU_EN_PIXELS)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(CLAUSE_3)
                .hasMessageContaining("nouvelle-regle");
    }
}
