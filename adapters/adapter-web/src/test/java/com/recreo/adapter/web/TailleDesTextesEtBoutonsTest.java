package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S2-1 : aucun texte lu par l'enfant ni aucun bouton actionnable ne doit rester sous la taille de
 * .consigne (1,25rem), reserves 6 et 7 de la fiche bandit-manchot-additions-cp. Lecture directe du
 * CSS par decoupage de blocs { }, jamais par expression reguliere (ADR-0008, AssertionsWebTest).
 *
 * <p>Correction demandee par nadia-prof sur le refus de S2-1
 * (squad/conformite/FICHE-bandit-manchot-additions-cp.md) : la liste des boutons actionnables
 * n'est pas ecrite ici, elle est relevee dans les gabarits eux-memes (tout &lt;button&gt; et tout
 * &lt;a href&gt;), pour qu'un bouton nouveau ou oublie soit attrape sans qu'on pense a l'ajouter.
 * Son deuxieme defaut, meme forme d'erreur : une derivation qui passait par les classes CSS
 * laissait sortir un bouton sans classe. La regle part des elements eux-memes et, pour chacun,
 * verifie les regles qui s'appliquent reellement a lui (ses classes si elles existent, la balise
 * nue sinon).
 *
 * <p>ADR-0014, entree d'une bibliotheque CSS tierce. Trois choses changent, et une seule question
 * les commande : ou ce garde-fou prend-il son perimetre, maintenant que tout le CSS servi n'est
 * plus ecrit ici ?
 * <ul>
 *   <li>les gabarits balayes viennent du repertoire de gabarits et non plus de quatre chemins
 *       ecrits a la main (ADR-0013, obligation 1) ;</li>
 *   <li>les regles maison viennent de toutes les feuilles de static/ hors static/vendor/, et non
 *       plus d'un fichier nomme ;</li>
 *   <li>le plancher exige par nadia-prof doit etre porte par une regle <b>maison</b>. Une
 *       bibliotheque tierce peut porter le rendu de base ; elle ne porte jamais une garantie
 *       pedagogique, parce que sa prochaine version pourrait la retirer sans que ce depot le
 *       sache. Un bouton qui n'a qu'une classe tierce echoue.</li>
 * </ul>
 * Le CSS tiers n'est jamais juge pour lui-meme. Il n'est lu que sur un point, parce que c'est le
 * seul par lequel il peut defaire un plancher declare ici : une declaration !important, qui gagne
 * quel que soit l'ordre de chargement.
 */
class TailleDesTextesEtBoutonsTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/TailleDesTextesEtBoutonsTest");

    private static final String STATIQUE = "static";
    private static final String TIERS = "vendor";
    private static final double PLANCHER_DE_LISIBILITE_REM = 1.25;
    private static final double CIBLE_TACTILE_REM = 2.75;

    /** Les proprietes dont depend une garantie donnee par nadia-prof sur un bouton. */
    private static final List<String> PROPRIETES_GARDEES = List.of("font-size", "min-height");

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

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

    private static List<Path> feuilles(Path racine, boolean tierces) {
        Path statique = racine.resolve(STATIQUE);
        if (!Files.isDirectory(statique)) {
            return List.of();
        }
        Path repertoireTiers = statique.resolve(TIERS);
        try (Stream<Path> arborescence = Files.walk(statique)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".css"))
                    .filter(fichier -> fichier.startsWith(repertoireTiers) == tierces)
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static Map<String, String> blocsParSelecteur(List<Path> feuilles) {
        Map<String, String> blocs = new HashMap<>();
        for (Path feuille : feuilles) {
            String source = sansCommentaires(lire(feuille));
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
        }
        return blocs;
    }

    static Map<String, String> blocsMaison(Path racine) {
        return blocsParSelecteur(feuilles(racine, false));
    }

    static Map<String, String> blocsTiers(Path racine) {
        return blocsParSelecteur(feuilles(racine, true));
    }

    private static double tailleEnRem(Map<String, String> blocs, String selecteur) {
        return proprieteEnRem(blocs, selecteur, "font-size");
    }

    private static double proprieteEnRem(Map<String, String> blocs, String selecteur, String propriete) {
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur %s doit exister dans le CSS maison", selecteur).isNotNull();
        String etiquette = propriete + ":";
        int position = corps.indexOf(etiquette);
        assertThat(position).as("%s doit declarer explicitement %s", selecteur, propriete).isNotNegative();
        int finValeur = corps.indexOf(';', position);
        String valeur = corps.substring(position + etiquette.length(), finValeur).strip();
        assertThat(valeur).as("%s : %s doit etre exprime en rem", selecteur, propriete).endsWith("rem");
        return Double.parseDouble(valeur.substring(0, valeur.length() - "rem".length()));
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

    /** ADR-0013 obligation 1 : les gabarits balayes viennent du repertoire, jamais d'une liste. */
    private static List<Path> gabarits(Path racine) {
        Path repertoire = racine.resolve("templates");
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Tout bouton et tout lien-bouton reellement rendu, releve dans le balisage. */
    static List<Element> boutonsActionnables(Path racine) {
        List<Element> boutons = new ArrayList<>();
        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            boutons.addAll(document.select("button, a[href]"));
        }
        return boutons;
    }

    /**
     * Les regles qui s'appliquent reellement a cet element : ses classes si l'element en porte, la
     * balise nue sinon. Un element sans classe qui ne correspond a aucune regle de balise nue tombe
     * au defaut du navigateur : c'est exactement ce que cette derivation doit detecter, plutot que
     * de le laisser passer parce qu'il n'a pas de classe a controler.
     */
    private static List<String> selecteursApplicables(Element element) {
        List<String> classes = element.classNames().stream().map(classe -> "." + classe).toList();
        return classes.isEmpty() ? List.of(element.tagName()) : classes;
    }

    /**
     * ADR-0014 : parmi les regles applicables a ce bouton, celles que ce depot ecrit. La liste doit
     * etre non vide - c'est la ou une bibliotheque tierce cesse de suffire.
     */
    private static List<String> selecteursMaisonApplicables(Map<String, String> maison, Element bouton) {
        List<String> applicables = selecteursApplicables(bouton);
        List<String> maisonnee = applicables.stream().filter(maison::containsKey).toList();
        assertThat(maisonnee)
                .as("%s : aucune regle maison ne s'applique a ce bouton (selecteurs vus : %s). "
                        + "ADR-0014 : une bibliotheque tierce porte le rendu de base, elle ne porte "
                        + "jamais un plancher exige par nadia-prof - il faut une classe maison qui "
                        + "le declare", etiquette(bouton), applicables)
                .isNotEmpty();
        return maisonnee;
    }

    private static String etiquette(Element element) {
        String description = element.classNames().isEmpty()
                ? "<" + element.tagName() + "> sans classe"
                : "." + String.join(".", element.classNames());
        return description + " \"" + element.text() + "\" (" + element.ownerDocument().location() + ")";
    }

    private static List<Element> boutonsPassesAuCrible(Path racine) {
        List<Element> boutons = boutonsActionnables(racine);
        assertThat(boutons)
                .as("cette regle ne prouve rien si aucun bouton n'est passe au crible")
                .isNotEmpty();
        return boutons;
    }

    /**
     * S2-1, motif du refus : "au minimum" est un plancher, pas une liste fermee, et un bouton de
     * reponse est un bouton actionnable comme un autre. La liste testee vient des gabarits.
     */
    static void verifierPlancherDeLisibilite(Path racine) {
        Map<String, String> maison = blocsMaison(racine);
        for (Element bouton : boutonsPassesAuCrible(racine)) {
            for (String selecteur : selecteursMaisonApplicables(maison, bouton)) {
                Optional<Double> taille = proprieteEnRemSiExiste(maison, selecteur, "font-size");
                assertThat(taille)
                        .as("%s : la regle %s doit fixer une taille de police en rem, sinon le bouton "
                                + "retombe au defaut du navigateur (environ 13px)",
                                etiquette(bouton), selecteur)
                        .isPresent();
                assertThat(taille.get())
                        .as("%s ne doit jamais retomber au defaut du navigateur (environ 13px)",
                                etiquette(bouton))
                        .isGreaterThanOrEqualTo(PLANCHER_DE_LISIBILITE_REM);
            }
        }
    }

    /**
     * Deuxieme motif du refus : une cible tactile de 46 x 41 px avec 8 px d'espacement est sous le
     * minimum de 44 px. min-height/min-width garantissent un plancher independant du contenu.
     */
    static void verifierCibleTactile(Path racine) {
        Map<String, String> maison = blocsMaison(racine);
        for (Element bouton : boutonsPassesAuCrible(racine)) {
            for (String selecteur : selecteursMaisonApplicables(maison, bouton)) {
                Optional<Double> hauteur = proprieteEnRemSiExiste(maison, selecteur, "min-height");
                assertThat(hauteur)
                        .as("%s : la regle %s doit fixer min-height en rem, sinon la cible tactile "
                                + "retombe au defaut du navigateur", etiquette(bouton), selecteur)
                        .isPresent();
                assertThat(hauteur.get())
                        .as("%s : la hauteur minimale doit atteindre au moins 44px (2,75rem)",
                                etiquette(bouton))
                        .isGreaterThanOrEqualTo(CIBLE_TACTILE_REM);
            }
        }
    }

    /**
     * ADR-0014 : la feuille maison est chargee en dernier (RessourcesTiercesTest), donc une regle
     * maison l'emporte sur une regle tierce de meme specificite. Une seule chose renverse cet ordre :
     * !important. Une classe utilitaire tierce posee sur un bouton peut ainsi defaire un plancher
     * declare ici sans qu'aucune feuille maison ne change. C'est le seul motif pour lequel ce
     * garde-fou lit le CSS tiers, et il ne lui reproche rien en soi : il reproche a <b>notre</b>
     * bouton de porter une classe qui rend notre garantie inoperante.
     */
    static void verifierQueLeTiersNeDefaitPasLesPlanchers(Path racine) {
        Map<String, String> tiers = blocsTiers(racine);
        for (Element bouton : boutonsPassesAuCrible(racine)) {
            for (String selecteur : selecteursApplicables(bouton)) {
                String corps = tiers.get(selecteur);
                if (corps == null) {
                    continue;
                }
                for (String propriete : PROPRIETES_GARDEES) {
                    assertThat(declareEnImportant(corps, propriete))
                            .as("%s : la classe tierce %s impose %s en !important et prend le pas sur "
                                    + "la regle maison ; le plancher exige par nadia-prof serait "
                                    + "declare mais pas rendu", etiquette(bouton), selecteur, propriete)
                            .isFalse();
                }
            }
        }
    }

    private static boolean declareEnImportant(String corps, String propriete) {
        int position = corps.indexOf(propriete + ":");
        if (position < 0) {
            return false;
        }
        int fin = corps.indexOf(';', position);
        String valeur = corps.substring(position, fin < 0 ? corps.length() : fin);
        return valeur.contains("!important");
    }

    @Test
    void auMoinsUnBoutonEstReleveDansLesGabarits() {
        assertThat(boutonsActionnables(RACINE_PRODUCTION))
                .as("cette regle ne prouve rien si aucun bouton n'est passe au crible")
                .isNotEmpty();
    }

    @Test
    void aucunTexteNePasseSousLaTailleDeLaConsigne() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
        double consigne = tailleEnRem(blocs, ".consigne");
        assertThat(consigne).isEqualTo(PLANCHER_DE_LISIBILITE_REM);

        for (String selecteur : new String[] {
                ".score", ".indice", ".fin-de-partie", ".erreur", ".pas-de-jeu", ".message-reprise"
        }) {
            assertThat(tailleEnRem(blocs, selecteur))
                    .as("%s ne doit jamais passer sous la consigne (%.2frem)", selecteur, consigne)
                    .isGreaterThanOrEqualTo(consigne);
        }
    }

    @Test
    void toutBoutonReellementRenduEstAuMoinsAUnRemVingtCinq() {
        verifierPlancherDeLisibilite(RACINE_PRODUCTION);
    }

    @Test
    void toutBoutonReellementRenduAUneCibleTactileDAuMoinsQuaranteQuatrePixels() {
        verifierCibleTactile(RACINE_PRODUCTION);
    }

    @Test
    void aucuneClasseTierceNeDefaitUnPlancherSurUnBoutonRendu() {
        verifierQueLeTiersNeDefaitPasLesPlanchers(RACINE_PRODUCTION);
    }

    @Test
    void lIndiceEstAuMoinsAussiGrandQueLaConsigne() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
        assertThat(tailleEnRem(blocs, ".indice"))
                .isGreaterThanOrEqualTo(tailleEnRem(blocs, ".consigne"));
    }

    @Test
    void h1PorteUneRegleDeTailleExplicite() {
        assertThat(tailleEnRem(blocsMaison(RACINE_PRODUCTION), "h1")).isGreaterThan(0);
    }

    /**
     * S3-4, reserve 1 de nadia-prof sur S2-1 : le chiffre d'un bouton de reponse est ce qui
     * distingue les trois propositions, il doit donc peser au moins autant que le calcul affiche
     * a l'ecran (.enonce), et non rester le detail le plus petit d'une large barre.
     */
    @Test
    void leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
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
    void lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRem() {
        Map<String, String> blocs = blocsMaison(RACINE_PRODUCTION);
        assertThat(proprieteEnRem(blocs, ".propositions", "gap")).isGreaterThanOrEqualTo(1.0);
    }

    /**
     * ADR-0013 obligation 3 : l'objet neuf est un bouton que personne n'a inscrit nulle part, habille
     * par la seule bibliotheque tierce. C'est la forme que prendrait le defaut le jour ou quelqu'un
     * s'appuierait sur Bootstrap pour habiller un bouton, et c'est le mode de defaillance que
     * l'entree d'une bibliotheque tierce rend possible.
     */
    @Test
    void unBoutonHabilleParLaSeuleBibliothequeTierceFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("bouton-sans-regle-maison");
        assertThatThrownBy(() -> verifierPlancherDeLisibilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison");
        assertThatThrownBy(() -> verifierCibleTactile(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune regle maison");
    }

    @Test
    void uneClasseTierceEnImportantFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("plancher-defait-par-le-tiers");
        assertThatThrownBy(() -> verifierQueLeTiersNeDefaitPasLesPlanchers(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("!important");
    }
}
