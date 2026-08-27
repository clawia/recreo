package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsTiers;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.debutDeDeclaration;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.gabarits;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.valeurDeclaree;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S6-8 : un element qui porte a la fois une classe de conteneur tiers - une classe declaree par une
 * feuille de {@code static/vendor/} avec un rembourrage lateral (padding-left/padding-right)
 * explicite et non nul, inconditionnel, comme {@code .container} de Bootstrap - et une classe
 * maison ne doit jamais voir ce rembourrage lateral ecrase par un raccourci {@code padding} ou une
 * declaration explicite maison qui le remet a zero.
 *
 * <p>Motif : {@code main#jeu} porte {@code class="container zone-jeu"}. {@code .zone-jeu} declarait
 * {@code padding: 2rem 0}, un raccourci qui fixe aussi padding-left et padding-right a 0. Comme les
 * deux selecteurs sont de meme specificite (classe unique) et que la feuille maison charge apres la
 * feuille tierce (ADR-0014 point 5), c'est cette valeur nulle qui gagnait - a toute largeur d'ecran,
 * pas seulement sous 1200 px, puisque le rembourrage de {@code .container} n'est conditionne par
 * aucune {@code @media} (`squad/sprints/s06-ouvrir-la-conjugaison-et-solder-la-dette/proposition-ines-s6-8-s6-10.md`).
 * `nadia-prof` a mesure la disparition de cette marge et l'a nommee dans
 * `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (verdict du 2026-08-07, section A.2,
 * point 1).
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : la classe de conteneur tiers n'est pas ecrite en dur ("container") -
 *       elle est reperee en lisant les feuilles de {@code static/vendor/} pour toute classe qui
 *       declare padding-left et padding-right non nuls. La population d'elements controles vient des
 *       gabarits, pour toute classe qui porte a la fois une telle classe tierce et une classe
 *       maison ;</li>
 *   <li><b>derivation totale</b> : aucune classe tierce a rembourrage lateral, ou aucun element ne
 *       combinant une classe tierce et une classe maison, sont des <b>echecs</b>, jamais des cas
 *       ignores - ce garde-fou ne prouverait alors rien ;</li>
 *   <li><b>mise a l'epreuve</b> : sur un jeu fautif neuf, avec des noms de classes differents de
 *       ceux de la production, pour prouver que la regle n'est pas pinnee sur "container"/"zone-jeu"
 *       litteralement.</li>
 * </ul>
 *
 * <p>Ce que cette regle ne couvre pas, meme limite documentee par
 * {@code TailleDesTextesEtBoutonsTest} : une meme regle qui declarerait deux fois la meme propriete
 * est mal lue (la premiere declaration est prise, jamais la derniere) ; un rembourrage exprime en
 * {@code calc()}, {@code %} ou variable CSS n'est pas resolu numeriquement, seulement compare a la
 * forme litterale d'un zero.
 */
class LeConteneurTierGardeSonRembourrageLateralTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLES =
            Path.of("src/test/resources/contreexemple/LeConteneurTierGardeSonRembourrageLateralTest");

    /** Unites de longueur ou de pourcentage qu'une valeur nulle peut porter apres son chiffre. */
    private static final Set<String> UNITES_DE_ZERO = Set.of("rem", "px", "em", "%", "vw", "vh");

    /** Les deux valeurs laterales (gauche, droite) qu'assigne un raccourci padding a 1 a 4 valeurs. */
    record ValeursLaterales(String gauche, String droite) {
    }

    private static List<String> jetons(String valeur) {
        return Arrays.stream(valeur.strip().split("\\s+")).filter(jeton -> !jeton.isEmpty()).toList();
    }

    static Optional<ValeursLaterales> lateralesDuRaccourci(String valeur) {
        List<String> jetons = jetons(valeur);
        return switch (jetons.size()) {
            case 1 -> Optional.of(new ValeursLaterales(jetons.get(0), jetons.get(0)));
            case 2, 3 -> Optional.of(new ValeursLaterales(jetons.get(1), jetons.get(1)));
            case 4 -> Optional.of(new ValeursLaterales(jetons.get(3), jetons.get(1)));
            default -> Optional.empty();
        };
    }

    /**
     * ADR-0008 : aucune expression reguliere dans ce module, y compris pour lire une valeur CSS.
     * Une valeur nulle est un chiffre qui ne vaut que des zeros (avec au plus un point decimal),
     * suivi d'une unite connue ou d'aucune unite ("0" seul est valide en CSS).
     */
    static boolean estZero(String valeur) {
        String source = valeur.strip();
        int position = 0;
        boolean chiffreVu = false;
        while (position < source.length()) {
            char caractere = source.charAt(position);
            if (caractere == '.') {
                position++;
                continue;
            }
            if (!Character.isDigit(caractere)) {
                break;
            }
            if (caractere != '0') {
                return false;
            }
            chiffreVu = true;
            position++;
        }
        if (!chiffreVu) {
            return false;
        }
        String unite = source.substring(position);
        return unite.isEmpty() || UNITES_DE_ZERO.contains(unite);
    }

    /** Un selecteur qui ne designe qu'une seule classe, sans combinateur ni pseudo-classe. */
    static boolean estSelecteurDeClasseUnique(String selecteur) {
        if (selecteur.length() < 2 || selecteur.charAt(0) != '.') {
            return false;
        }
        for (int position = 1; position < selecteur.length(); position++) {
            char caractere = selecteur.charAt(position);
            if (!Character.isLetterOrDigit(caractere) && caractere != '-' && caractere != '_') {
                return false;
            }
        }
        return true;
    }

    /**
     * Le rembourrage lateral qu'une regle declare pour elle-meme : la valeur explicite de
     * padding-left/padding-right si elle existe, sinon celle deduite du raccourci padding. Une
     * declaration explicite l'emporte sur le raccourci du meme bloc.
     */
    static ValeursLaterales lateralDeclare(String corps) {
        String gauche = null;
        String droite = null;

        int raccourci = debutDeDeclaration(corps, "padding");
        if (raccourci >= 0) {
            Optional<ValeursLaterales> laterales =
                    lateralesDuRaccourci(valeurDeclaree(corps, "padding"));
            if (laterales.isPresent()) {
                gauche = laterales.get().gauche();
                droite = laterales.get().droite();
            }
        }
        int gaucheExplicite = debutDeDeclaration(corps, "padding-left");
        if (gaucheExplicite >= 0) {
            gauche = valeurDeclaree(corps, "padding-left");
        }
        int droiteExplicite = debutDeDeclaration(corps, "padding-right");
        if (droiteExplicite >= 0) {
            droite = valeurDeclaree(corps, "padding-right");
        }
        return new ValeursLaterales(gauche, droite);
    }

    /**
     * Les classes tierces (feuilles de static/vendor/) qui declarent un rembourrage lateral non nul
     * et inconditionnel - le seul type de classe dont l'ecrasement fait disparaitre une marge que
     * l'enfant voyait. Derive des feuilles servies, jamais d'un nom ecrit ici.
     */
    static Set<String> classesTiercesARembourrageLateral(Path racineDesFeuilles) {
        Set<String> classes = new LinkedHashSet<>();
        for (Map.Entry<String, String> bloc : blocsTiers(racineDesFeuilles).entrySet()) {
            String selecteur = bloc.getKey().strip();
            if (!estSelecteurDeClasseUnique(selecteur)) {
                continue;
            }
            ValeursLaterales laterales = lateralDeclare(bloc.getValue());
            if (laterales.gauche() != null && laterales.droite() != null
                    && !estZero(laterales.gauche()) && !estZero(laterales.droite())) {
                classes.add(selecteur.substring(1));
            }
        }
        return classes;
    }

    static void verifierQueLeConteneurTierGardeSonRembourrageLateral(Path racine) {
        Set<String> classesTierces = classesTiercesARembourrageLateral(racine);
        assertThat(classesTierces)
                .as("cette regle ne prouve rien si aucune classe tierce ne declare un rembourrage "
                        + "lateral non nul et inconditionnel")
                .isNotEmpty();

        Map<String, String> maison = blocsMaison(racine);
        List<String> defauts = new ArrayList<>();
        int elementsControles = 0;

        for (Path gabarit : gabarits(racine)) {
            Document document = Jsoup.parse(lire(gabarit), gabarit.toString());
            for (Element element : document.select("[class]")) {
                List<String> classes = element.classNames().stream().toList();
                boolean porteUnConteneurTiers = classes.stream().anyMatch(classesTierces::contains);
                if (!porteUnConteneurTiers) {
                    continue;
                }
                for (String classe : classes) {
                    if (classesTierces.contains(classe)) {
                        continue;
                    }
                    String corpsMaison = maison.get("." + classe);
                    if (corpsMaison == null) {
                        continue;
                    }
                    elementsControles++;
                    ValeursLaterales laterales = lateralDeclare(corpsMaison);
                    if (laterales.gauche() != null && estZero(laterales.gauche())) {
                        defauts.add("." + classe + " (" + gabarit + ") declare un rembourrage gauche "
                                + "a " + laterales.gauche() + " sur un element qui porte aussi une "
                                + "classe de conteneur tiers a rembourrage lateral non nul : elle "
                                + "l'ecrase, a toute largeur d'ecran");
                    }
                    if (laterales.droite() != null && estZero(laterales.droite())) {
                        defauts.add("." + classe + " (" + gabarit + ") declare un rembourrage droit "
                                + "a " + laterales.droite() + " sur un element qui porte aussi une "
                                + "classe de conteneur tiers a rembourrage lateral non nul : elle "
                                + "l'ecrase, a toute largeur d'ecran");
                    }
                }
            }
        }

        assertThat(elementsControles)
                .as("cette regle ne prouve rien si aucun element ne combine une classe de conteneur "
                        + "tiers et une classe maison")
                .isGreaterThan(0);
        assertThat(defauts).isEmpty();
    }

    @Test
    void leConteneurTierGardeSonRembourrageLateralSurLaProduction() {
        verifierQueLeConteneurTierGardeSonRembourrageLateral(RACINE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : jeu fautif neuf, avec des noms de classes et de feuilles differents de
     * la production (".enveloppe-tierce" et ".contenu-large" au lieu de ".container" et
     * ".zone-jeu"), pour prouver que la regle est bien derivee et non pinnee sur les noms reels.
     */
    @Test
    void unRaccourciMaisonQuiEcraseLeRembourrageLateralDUnConteneurTierFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("raccourci-ecrase-le-rembourrage-lateral");
        assertThatThrownBy(() -> verifierQueLeConteneurTierGardeSonRembourrageLateral(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(".contenu-large")
                .hasMessageContaining("l'ecrase, a toute largeur d'ecran");
    }

    /**
     * Meme faute, ecrite en declaration explicite plutot qu'en raccourci - les deux formes doivent
     * etre attrapees.
     */
    @Test
    void unPaddingLateralExpliciteAZeroFaitEchouerLeGardeFou() {
        Path racine = CONTRE_EXEMPLES.resolve("rembourrage-lateral-explicite-a-zero");
        assertThatThrownBy(() -> verifierQueLeConteneurTierGardeSonRembourrageLateral(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("rembourrage droit");
    }
}
