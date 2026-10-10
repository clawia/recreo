package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.blocsMaison;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.lire;
import static com.recreo.adapter.web.TailleDesTextesEtBoutonsTest.sansCommentaires;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <b>S56-ART-CP-10-ECHEC : la marque de l'oeuvre deja essayee ne depend pas de l'image.</b> Condition
 * de {@code nadia-prof} (cloture de s55, 4.2 : l'attenuation a 50 % seule est <i>"pas acceptable pour un
 * paquet servi"</i>), voie O1 retenue le 2026-10-09 (21:37), critere recopie par {@code camille-po}
 * dans le backlog (S56-ART-CP-10-ECHEC, criteres 1 a 5, 7 et 8 ; le critere 6 est une clause de rendu
 * du garde-fou 4, dans {@code bootstrap/rendu}). La marque est une pastille portant une croix, le
 * {@code ::before} du bouton {@code .proposition.deja-essayee}, posee sous la case.
 *
 * <p>Ce test lit {@code recreo.css} et le fichier SVG que la regle de la marque designe. Ce qu'il ne
 * juge pas, a {@code nadia-prof} seule, en jouant : que la croix se reconnaisse a 40 px et se lise
 * <i>"pas celle-la"</i>, que la marque se voie autour d'une reproduction pale et d'une sombre, qu'elle
 * ne se confonde ni avec l'anneau de focus ni avec celui de la revelation.</p>
 *
 * <p><b>Lectures que ce test fait, et qu'il nomme parce que le critere les laisse ouvertes</b> :</p>
 * <ul>
 *   <li><b>Sujet</b> : le dernier compose du selecteur, pseudo-element compris, hors des parentheses
 *       d'un {@code :has(...)} (critere 7, bloc de nadia-prof du 22:07).</li>
 *   <li><b>Critere 1</b> : le {@code :has(.proposition.deja-essayee)} de la rangee n'est pas une regle de la
 *       marque. Le motif de la regle est lu hors des parentheses ; sans cela, le
 *       {@code padding-bottom} de la rangee suffirait a le satisfaire et la mutation E1 (regle
 *       {@code ::before} retiree) resterait verte.</li>
 *   <li><b>Critere 4, le bord exterieur</b> : la pastille est a deux tons, un disque clair a anneau d'encre
 *       (le disque se voit sur le meuble sombre, l'anneau sur la page claire). Le bord est donc
 *       juge sur le meilleur des deux contre chaque entourage ; lu comme "l'anneau seul", il vaudrait
 *       1,14:1 contre le meuble et refuserait la forme que {@code nadia-prof} a retenue. L'entourage est lu
 *       dans {@code :root} (fond du meuble, fond de page), jamais en constante.</li>
 *   <li><b>Critere 8</b> : lignes de la feuille maison, hors commentaires, qui contiennent
 *       {@code line-through} (le {@code grep -c} du critere, sans les lignes de commentaire).</li>
 * </ul>
 *
 * <p>ADR-0013 : perimetre derive du selecteur (tout selecteur qui contient {@code deja-essayee}),
 * population non vide exigee, mise a l'epreuve sur la feuille et le SVG reels mutes en memoire
 * (mutations E1 a E7 de {@code theo-archi}, plus celles de {@code nadia-prof} pour le critere 7), et
 * deux jeux fautifs commites. Ce que ce test ne voit pas : une regle de la feuille vendorisee, une regle
 * sous {@code @media}, un attribut {@code style} du gabarit. E8 (le gabarit retire {@code deja-essayee})
 * et E9 ({@code content: "x"}, clause 2 d'ADR-0027) sont gardees ailleurs.</p>
 */
class LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest {

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");

    private static final Path CONTRE_EXEMPLES = Path.of("src", "test", "resources", "contreexemple",
            "LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest");

    private static final String RANGEE = "propositions-oeuvres";
    private static final String ESSAYEE = "deja-essayee";
    private static final String REVELEE = "data-oeuvre-revelee";

    private static final String C1 = "critere 1";
    private static final String C2 = "critere 2";
    private static final String C3 = "critere 3";
    private static final String C4 = "critere 4";
    private static final String C5 = "critere 5";
    private static final String C7 = "critere 7";
    private static final String C8 = "critere 8";

    private static final double CONTRASTE_MINIMUM = 3.0;

    private static final String MARQUE = ".propositions-oeuvres .proposition.deja-essayee::before";
    private static final String IMAGE_ESSAYEE = ".propositions-oeuvres .proposition.deja-essayee img";
    private static final String IMAGE_DE_BASE = ".propositions-oeuvres .proposition img";
    private static final String CHEMIN_DE_LA_MARQUE = "/images/marque-echec.svg";

    // ------------------------------------------------------------------------------------------
    // Lecture des selecteurs
    // ------------------------------------------------------------------------------------------

    /** Le selecteur prive de ce qu'il y a entre parentheses : {@code :has(...)} ne compte pas comme sujet. */
    static String horsParentheses(String selecteur) {
        StringBuilder dehors = new StringBuilder();
        int profondeur = 0;
        for (char c : selecteur.toCharArray()) {
            if (c == '(') {
                profondeur++;
            } else if (c == ')') {
                profondeur--;
            } else if (profondeur == 0) {
                dehors.append(c);
            }
        }
        return dehors.toString();
    }

    /** Le dernier compose, pseudo-element compris : ce qui suit le dernier espace ou combinateur hors parentheses. */
    static String sujet(String selecteur) {
        int profondeur = 0;
        int coupe = -1;
        for (int i = 0; i < selecteur.length(); i++) {
            char c = selecteur.charAt(i);
            if (c == '(') {
                profondeur++;
            } else if (c == ')') {
                profondeur--;
            } else if (profondeur == 0 && (c == ' ' || c == '>' || c == '+' || c == '~')) {
                coupe = i;
            }
        }
        return selecteur.substring(coupe + 1);
    }

    static boolean sujetEstUnPseudoElement(String selecteur) {
        String sujet = horsParentheses(sujet(selecteur));
        return sujet.contains("::") || sujet.contains(":before") || sujet.contains(":after");
    }

    static Set<String> classesDuSujet(String selecteur) {
        String sujet = horsParentheses(sujet(selecteur));
        Set<String> classes = new LinkedHashSet<>();
        int i = 0;
        while ((i = sujet.indexOf('.', i)) >= 0) {
            int fin = i + 1;
            while (fin < sujet.length() && (Character.isLetterOrDigit(sujet.charAt(fin))
                    || sujet.charAt(fin) == '-' || sujet.charAt(fin) == '_')) {
                fin++;
            }
            classes.add(sujet.substring(i + 1, fin));
            i = fin;
        }
        return classes;
    }

    static boolean sujetEstUneImage(String selecteur) {
        String sujet = horsParentheses(sujet(selecteur));
        return sujet.equals("img") || (sujet.startsWith("img") && !Character.isLetter(sujet.charAt(3)));
    }

    /** Les declarations d'un corps de regle, dans l'ordre : nom puis valeur, espaces retires aux bords. */
    static List<String[]> declarations(String corps) {
        List<String[]> declarations = new ArrayList<>();
        for (String morceau : corps.split(";")) {
            int deuxPoints = morceau.indexOf(':');
            if (deuxPoints > 0) {
                declarations.add(new String[] {morceau.substring(0, deuxPoints).strip(),
                        morceau.substring(deuxPoints + 1).strip()});
            }
        }
        return declarations;
    }

    static Optional<String> valeur(String corps, String propriete) {
        String derniere = null;
        for (String[] declaration : declarations(corps)) {
            if (declaration[0].equals(propriete)) {
                derniere = declaration[1];
            }
        }
        return Optional.ofNullable(derniere);
    }

    static boolean declare(String corps, String propriete) {
        return valeur(corps, propriete).isPresent();
    }

    // ------------------------------------------------------------------------------------------
    // Les regles de la marque, derivees de la feuille
    // ------------------------------------------------------------------------------------------

    /** Les selecteurs qui contiennent {@code deja-essayee} et la rangee d'oeuvres, sans tenir compte des parentheses. */
    private static List<String> reglesDeLEtat(Map<String, String> blocs) {
        return blocs.keySet().stream()
                .filter(selecteur -> selecteur.contains(ESSAYEE) && selecteur.contains(RANGEE))
                .sorted()
                .toList();
    }

    /**
     * La marque : une regle dont le sujet est un pseudo-element, sous la rangee d'oeuvres, qui pose une
     * image (un {@code url(}). Derivee du corps et non du nom {@code deja-essayee} : une marque dont le
     * selecteur a perdu sa classe doit rester vue, pour que le critere 5 la refuse.
     */
    static List<String> reglesDeLaMarque(Map<String, String> blocs) {
        return blocs.keySet().stream()
                .filter(selecteur -> selecteur.contains(RANGEE))
                .filter(LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest::sujetEstUnPseudoElement)
                .filter(selecteur -> blocs.get(selecteur).contains("url("))
                .sorted()
                .toList();
    }

    private static String cheminDeLImage(String corps) {
        int debut = corps.indexOf("url(");
        if (debut < 0) {
            return "";
        }
        int fin = corps.indexOf(')', debut);
        return corps.substring(debut + "url(".length(), fin).replace("'", "").replace("\"", "").strip();
    }

    // ------------------------------------------------------------------------------------------
    // Les couleurs : luminance relative WCAG 2.x, calculee sur les valeurs hexadecimales
    // ------------------------------------------------------------------------------------------

    static double luminance(String hexadecimal) {
        String hex = hexadecimal.strip();
        if (hex.length() == 4) {
            hex = "#" + hex.charAt(1) + hex.charAt(1) + hex.charAt(2) + hex.charAt(2) + hex.charAt(3)
                    + hex.charAt(3);
        }
        if (hex.length() != 7 || hex.charAt(0) != '#') {
            throw new IllegalArgumentException("couleur hexadecimale attendue, lu \"" + hexadecimal + "\"");
        }
        double[] canaux = new double[3];
        for (int i = 0; i < 3; i++) {
            double c = Integer.parseInt(hex.substring(1 + 2 * i, 3 + 2 * i), 16) / 255.0;
            canaux[i] = c <= 0.03928 ? c / 12.92 : Math.pow((c + 0.055) / 1.055, 2.4);
        }
        return 0.2126 * canaux[0] + 0.7152 * canaux[1] + 0.0722 * canaux[2];
    }

    static double contraste(String a, String b) {
        double la = luminance(a);
        double lb = luminance(b);
        return (Math.max(la, lb) + 0.05) / (Math.min(la, lb) + 0.05);
    }

    // ------------------------------------------------------------------------------------------
    // Le juge, pur : il ne lit ni disque ni classpath
    // ------------------------------------------------------------------------------------------

    /**
     * Les griefs sur la feuille aplatie et sur le fichier de la marque (criteres 1 a 5 et 7) ; vide
     * quand tout tient. {@code image} rend le contenu du fichier designe par son chemin web.
     */
    static List<String> griefs(Map<String, String> blocs, Function<String, Optional<String>> image) {
        List<String> griefs = new ArrayList<>();
        griefsDuCritere1(blocs, griefs);
        griefsDuCritere2(blocs, griefs);
        griefsDuCritere3(blocs, griefs);
        griefsDuCritere4(blocs, image, griefs);
        griefsDuCritere5(blocs, griefs);
        griefsDuCritere7(blocs, griefs);
        return griefs;
    }

    private static void griefsDuCritere1(Map<String, String> blocs, List<String> griefs) {
        boolean uneRegleDeMarque = blocs.keySet().stream()
                .filter(selecteur -> horsParentheses(selecteur).contains(".proposition.deja-essayee")
                        && selecteur.contains("." + RANGEE))
                .anyMatch(selecteur -> declarations(blocs.get(selecteur)).stream()
                        .anyMatch(declaration -> !declaration[0].equals("opacity")));
        if (!uneRegleDeMarque) {
            griefs.add(C1 + " : aucune regle dont le selecteur contient .propositions-oeuvres et"
                    + " .proposition.deja-essayee ne declare une propriete autre que opacity : l'oeuvre"
                    + " essayee ne porte que l'attenuation, qui depend de l'image (N1, N4)");
        }
    }

    private static void griefsDuCritere2(Map<String, String> blocs, List<String> griefs) {
        for (String selecteur : blocs.keySet()) {
            if (!selecteur.contains(ESSAYEE) || !sujetEstUneImage(selecteur)) {
                continue;
            }
            for (String propriete : List.of("filter", "background", "background-image", "mix-blend-mode",
                    "content", "mask")) {
                if (declare(blocs.get(selecteur), propriete)) {
                    griefs.add(C2 + " : " + selecteur + " declare " + propriete + " : quelque chose serait"
                            + " dessine sur l'image (X3, N1)");
                }
            }
        }
    }

    private static void griefsDuCritere3(Map<String, String> blocs, List<String> griefs) {
        Set<String> pseudoElements = new LinkedHashSet<>(reglesDeLaMarque(blocs));
        blocs.keySet().stream()
                .filter(selecteur -> selecteur.contains(ESSAYEE) && selecteur.contains(RANGEE))
                .filter(LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest::sujetEstUnPseudoElement)
                .forEach(pseudoElements::add);
        if (pseudoElements.isEmpty()) {
            griefs.add(C3 + " : aucun pseudo-element de marque sous .propositions-oeuvres");
        }
        for (String selecteur : pseudoElements) {
            String corps = blocs.get(selecteur);
            if (!"none".equals(valeur(corps, "pointer-events").orElse(""))) {
                griefs.add(C3 + " : " + selecteur + " ne declare pas pointer-events: none : la pastille"
                        + " represserait l'oeuvre au doigt et etendrait la case essayee (N1)");
            }
            if (!estSousLeBordDuBouton(valeur(corps, "top").orElse(""))) {
                griefs.add(C3 + " : " + selecteur + " declare top \"" + valeur(corps, "top").orElse("")
                        + "\", attendu calc(100% + <longueur positive>) : la marque doit commencer sous le"
                        + " bord du bouton (N1)");
            }
        }
    }

    /** {@code calc(100% + <longueur positive>)}, espaces ignores. */
    private static boolean estSousLeBordDuBouton(String top) {
        String compact = top.replace(" ", "");
        String debut = "calc(100%+";
        if (!compact.startsWith(debut) || !compact.endsWith(")")) {
            return false;
        }
        String longueur = compact.substring(debut.length(), compact.length() - 1);
        for (String unite : List.of("rem", "px", "em")) {
            if (longueur.endsWith(unite)) {
                try {
                    return Double.parseDouble(longueur.substring(0, longueur.length() - unite.length())) > 0;
                } catch (NumberFormatException illisible) {
                    return false;
                }
            }
        }
        return false;
    }

    private static void griefsDuCritere4(Map<String, String> blocs, Function<String, Optional<String>> image,
            List<String> griefs) {
        List<String> marques = reglesDeLaMarque(blocs);
        if (marques.isEmpty()) {
            griefs.add(C4 + " : aucune regle de marque ne designe un fichier SVG, il n'y a aucune couleur a lire");
            return;
        }
        String chemin = cheminDeLImage(blocs.get(marques.get(0)));
        Optional<String> svg = image.apply(chemin);
        if (svg.isEmpty()) {
            griefs.add(C4 + " : la regle " + marques.get(0) + " designe " + chemin + ", qui n'existe pas");
            return;
        }
        Document document = Jsoup.parse(svg.get(), "", Parser.xmlParser());
        Element disque = document.select("circle").first();
        Element croix = document.select("path").first();
        if (disque == null || croix == null) {
            griefs.add(C4 + " : " + chemin + " doit porter un disque (circle) et un signe (path)");
            return;
        }
        String fond = disque.attr("fill");
        String anneau = disque.attr("stroke");
        String signe = croix.attr("stroke");
        String corpsRacine = blocs.get(":root");
        if (corpsRacine == null) {
            griefs.add(C4 + " : :root est absent de la feuille, le fond du meuble et celui de la page"
                    + " ne se lisent pas");
            return;
        }
        Map<String, String> entourages = new LinkedHashMap<>();
        entourages.put("le fond du meuble (--recreo-cabinet-fond)",
                valeur(corpsRacine, "--recreo-cabinet-fond").orElse(""));
        entourages.put("le fond de page (--recreo-fond)", valeur(corpsRacine, "--recreo-fond").orElse(""));
        try {
            for (Map.Entry<String, String> entourage : entourages.entrySet()) {
                double disqueContreLEntourage = contraste(fond, entourage.getValue());
                double anneauContreLEntourage = contraste(anneau, entourage.getValue());
                if (Math.max(disqueContreLEntourage, anneauContreLEntourage) < CONTRASTE_MINIMUM) {
                    griefs.add(C4 + " : le bord exterieur de la marque (disque " + fond + ", anneau " + anneau
                            + ") ne se detache pas de " + entourage.getKey() + " " + entourage.getValue()
                            + " : " + arrondi(disqueContreLEntourage) + ":1 et "
                            + arrondi(anneauContreLEntourage) + ":1, moins de 3:1 (N2)");
                }
            }
            double signeContreLeFond = contraste(signe, fond);
            if (signeContreLeFond < CONTRASTE_MINIMUM) {
                griefs.add(C4 + " : le signe " + signe + " ne se detache pas du disque " + fond + " : "
                        + arrondi(signeContreLeFond) + ":1, moins de 3:1 (N2)");
            }
        } catch (IllegalArgumentException illisible) {
            griefs.add(C4 + " : " + illisible.getMessage());
        }
    }

    private static String arrondi(double valeur) {
        return String.format(java.util.Locale.ROOT, "%.2f", valeur);
    }

    private static void griefsDuCritere5(Map<String, String> blocs, List<String> griefs) {
        List<String> marques = reglesDeLaMarque(blocs);
        if (marques.isEmpty()) {
            griefs.add(C5 + " : aucune regle de marque : rien ne dit qu'elle suit deja-essayee (N3)");
        }
        for (String selecteur : marques) {
            if (!selecteur.contains(ESSAYEE) || selecteur.contains(REVELEE)) {
                griefs.add(C5 + " : le selecteur de la marque " + selecteur + " doit contenir " + ESSAYEE
                        + " et pas " + REVELEE + " : la marque est celle de l'essai, pas de la revelation (N3)");
            }
        }
        Set<String> fichiers = new LinkedHashSet<>();
        for (String selecteur : marques) {
            String chemin = cheminDeLImage(blocs.get(selecteur));
            fichiers.add(chemin.substring(chemin.lastIndexOf('/') + 1));
        }
        for (String selecteur : blocs.keySet()) {
            if (!selecteur.contains(REVELEE)) {
                continue;
            }
            for (String fichier : fichiers) {
                if (blocs.get(selecteur).contains(fichier)) {
                    griefs.add(C5 + " : la regle " + selecteur + " reference " + fichier + ", le fichier de la"
                            + " marque d'echec : la revelation ne reprend pas la marque (N3)");
                }
            }
        }
    }

    private static final List<String> DIMENSIONS_DE_LA_CASE_ET_DE_L_IMAGE = List.of("width", "height",
            "min-width", "min-height", "max-width", "max-height", "padding", "margin", "aspect-ratio");

    private static void griefsDuCritere7(Map<String, String> blocs, List<String> griefs) {
        for (String selecteur : reglesDeLEtat(blocs)) {
            Set<String> classes = classesDuSujet(selecteur);
            String corps = blocs.get(selecteur);
            if (classes.contains(RANGEE)) {
                continue;
            }
            if (sujetEstUnPseudoElement(selecteur)) {
                if (classes.contains("proposition")
                        && !"absolute".equals(valeur(corps, "position").orElse(""))) {
                    griefs.add(C7 + " (b) : " + selecteur + " est un pseudo-element de .proposition et ne"
                            + " declare pas position: absolute : la marque entrerait dans le flux et"
                            + " deplacerait la case ou l'image (N6)");
                }
                continue;
            }
            if (!classes.contains("proposition") && !sujetEstUneImage(selecteur)) {
                continue;
            }
            for (String[] declaration : declarations(corps)) {
                String nom = declaration[0];
                boolean dimension = DIMENSIONS_DE_LA_CASE_ET_DE_L_IMAGE.stream()
                        .anyMatch(interdite -> nom.equals(interdite) || nom.startsWith(interdite + "-"));
                if (dimension) {
                    griefs.add(C7 + " (a) : " + selecteur + " declare " + nom + " : la case ou l'image"
                            + " changerait de taille ou de place quand la marque arrive (N6)");
                }
            }
            String largeur = largeurDeBordure(corps);
            String largeurDeBase = largeurDeBordure(blocs.getOrDefault(selecteurSansEtat(selecteur), ""));
            if (largeur != null && !largeur.equals(largeurDeBase)) {
                griefs.add(C7 + " (a) : " + selecteur + " declare une bordure de " + largeur + ", la regle de"
                        + " meme sujet sans " + ESSAYEE + " en declare " + largeurDeBase + " : l'image"
                        + " changerait de taille quand la marque arrive (N6)");
            }
        }
    }

    private static String selecteurSansEtat(String selecteur) {
        return selecteur.replace("." + ESSAYEE, "");
    }

    /** La largeur de bordure qu'un corps declare ({@code border} ou {@code border-width}), ou null. */
    private static String largeurDeBordure(String corps) {
        String largeur = null;
        for (String[] declaration : declarations(corps)) {
            if (declaration[0].equals("border") || declaration[0].equals("border-width")
                    || (declaration[0].startsWith("border-") && declaration[0].endsWith("-width"))) {
                largeur = declaration[1].split("\\s+")[0];
            }
        }
        return largeur;
    }

    /** Critere 8 : les lignes de la feuille maison, hors commentaires, qui portent {@code line-through}. */
    static List<String> griefsDeLaBrancheTexte(String feuille) {
        long lignes = sansCommentaires(feuille).lines().filter(ligne -> ligne.contains("line-through")).count();
        return lignes == 2 ? List.of() : List.of(C8 + " : " + lignes + " ligne(s) de recreo.css portent"
                + " line-through, 2 attendues : la branche texte (le mot barre) est inchangee");
    }

    // ------------------------------------------------------------------------------------------
    // La production et ses mutations
    // ------------------------------------------------------------------------------------------

    private static Function<String, Optional<String>> imagesDe(Path racine) {
        return chemin -> {
            Path fichier = racine.resolve("static").resolve(chemin.startsWith("/") ? chemin.substring(1) : chemin);
            return Files.isRegularFile(fichier) ? Optional.of(lire(fichier)) : Optional.empty();
        };
    }

    private static String feuilleDe(Path racine) {
        try {
            return Files.readString(racine.resolve("static/css/recreo.css"));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<String> griefsDe(Path racine) {
        List<String> griefs = new ArrayList<>(griefs(blocsMaison(racine), imagesDe(racine)));
        griefs.addAll(griefsDeLaBrancheTexte(feuilleDe(racine)));
        return griefs;
    }

    private static Map<String, String> feuilleReelle() {
        return new LinkedHashMap<>(blocsMaison(RACINE_PRODUCTION));
    }

    private static Map<String, String> feuilleMutee(String selecteur, UnaryOperator<String> mutation) {
        Map<String, String> blocs = feuilleReelle();
        String corps = blocs.get(selecteur);
        assertThat(corps).as("le selecteur mute %s doit exister dans la feuille reelle", selecteur).isNotNull();
        String mute = mutation.apply(corps);
        assertThat(mute).as("la mutation de %s doit changer la feuille, sans quoi elle ne prouve rien",
                selecteur).isNotEqualTo(corps);
        blocs.put(selecteur, mute);
        return blocs;
    }

    private static String svgReel() {
        return imagesDe(RACINE_PRODUCTION).apply(CHEMIN_DE_LA_MARQUE).orElseThrow();
    }

    private static Function<String, Optional<String>> svgMute(UnaryOperator<String> mutation) {
        String mute = mutation.apply(svgReel());
        assertThat(mute).as("la mutation du SVG doit le changer").isNotEqualTo(svgReel());
        return chemin -> Optional.of(mute);
    }

    private static List<String> griefsAvec(Map<String, String> blocs) {
        return griefs(blocs, imagesDe(RACINE_PRODUCTION));
    }

    @Test
    void laFeuilleEtLeFichierReelsTiennentLesCriteres() {
        Map<String, String> blocs = feuilleReelle();

        assertThat(griefsDe(RACINE_PRODUCTION)).isEmpty();
        assertThat(reglesDeLaMarque(blocs))
                .as("ADR-0003 : ce garde-fou ne prouve rien s'il ne lit aucune regle de marque")
                .isNotEmpty();
        assertThat(reglesDeLEtat(blocs)).contains(MARQUE, IMAGE_ESSAYEE);
    }

    /** Les valeurs mesurees par ines-design (1.5) : le calcul reproduit leurs chiffres. */
    @Test
    void leCalculDeContrasteReproduitLesValeursMesureesParInesDesign() {
        assertThat(contraste("#ffffff", "#000000")).isEqualTo(21.0, org.assertj.core.data.Offset.offset(0.001));
        assertThat(contraste("#c62828", "#f4f7fb")).isBetween(5.22, 5.24);
        assertThat(contraste("#f4f7fb", "#081c2c")).isBetween(16.10, 16.12);
        assertThat(contraste("#1c2733", "#f4f7fb")).isBetween(14.08, 14.10);
        assertThat(contraste("#1c2733", "#081c2c"))
                .as("l'anneau seul contre le meuble : la lecture que ce test ne retient pas")
                .isBetween(1.13, 1.15);
    }

    /** E1 : la regle ::before est retiree. */
    @Test
    void laRegleDeLaMarqueRetireeFaitEchouerLeCritere1() {
        Map<String, String> blocs = feuilleReelle();
        blocs.remove(MARQUE);

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C1));
    }

    /** E2 : un filtre sur l'image essayee dessinerait sur l'image. */
    @Test
    void unFiltreSurLImageEssayeeFaitEchouerLeCritere2() {
        Map<String, String> blocs = feuilleMutee(IMAGE_ESSAYEE, corps -> corps + ";filter: grayscale(1)");

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C2) && grief.contains("filter"));
    }

    /** E3 : sans pointer-events, la pastille represse l'oeuvre au doigt. */
    @Test
    void uneMarqueSansPointerEventsNoneFaitEchouerLeCritere3() {
        Map<String, String> blocs = feuilleMutee(MARQUE, corps -> corps.replace("pointer-events: none", ""));

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C3)
                && grief.contains("pointer-events"));
    }

    /** E4 : top: 0 pose la marque sur le bouton et non dessous. */
    @Test
    void uneMarquePoseeADuTopZeroFaitEchouerLeCritere3() {
        Map<String, String> blocs = feuilleMutee(MARQUE,
                corps -> corps.replace("top: calc(100% + 0.25rem)", "top: 0"));

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C3) && grief.contains("top"));
    }

    @Test
    void uneMarqueDontLeTopNEstPasSousLeBordFaitEchouerLeCritere3() {
        Map<String, String> blocs = feuilleMutee(MARQUE,
                corps -> corps.replace("top: calc(100% + 0.25rem)", "top: calc(100% - 0.25rem)"));

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C3) && grief.contains("top"));
    }

    /** E5 : le remplissage du disque passe a #c62828, la croix est a 1:1 sur son fond. */
    @Test
    void unDisqueDeLaCouleurDeLaCroixFaitEchouerLeCritere4() {
        Function<String, Optional<String>> image = svgMute(svg -> svg.replace("fill=\"#f4f7fb\"", "fill=\"#c62828\""));

        assertThat(griefs(feuilleReelle(), image)).anyMatch(grief -> grief.startsWith(C4)
                && grief.contains("le signe"));
    }

    /** Le critere 4 lit le fond du meuble dans :root : meuble et disque identiques, anneau identique au disque. */
    @Test
    void unFondDeMeubleLuDansRootFaitEchouerLeCritere4() {
        Map<String, String> blocs = feuilleMutee(":root", corps -> corps.replace(
                "--recreo-cabinet-fond: #081c2c", "--recreo-cabinet-fond: #f4f7fb"));
        Function<String, Optional<String>> image = svgMute(svg -> svg.replace("stroke=\"#1c2733\" stroke-width=\"2\"",
                "stroke=\"#f4f7fb\" stroke-width=\"2\""));

        assertThat(griefs(blocs, image)).anyMatch(grief -> grief.startsWith(C4)
                && grief.contains("le fond du meuble"));
    }

    @Test
    void unFichierDeMarqueIntrouvableFaitEchouerLeCritere4() {
        assertThat(griefs(feuilleReelle(), chemin -> Optional.empty()))
                .anyMatch(grief -> grief.startsWith(C4) && grief.contains("n'existe pas"));
    }

    /** E6 : la marque suit la revelation et non l'essai. */
    @Test
    void uneMarqueSurLaRevelationFaitEchouerLeCritere5() {
        Map<String, String> blocs = feuilleReelle();
        String corps = blocs.remove(MARQUE);
        blocs.put(".propositions-oeuvres .proposition[data-oeuvre-revelee=true]::before", corps);

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C5));
    }

    /** Critere 5, seconde moitie : la regle de la revelation ne reprend pas le fichier de la marque. */
    @Test
    void uneRevelationQuiReprendLeFichierDeLaMarqueFaitEchouerLeCritere5() {
        Map<String, String> blocs = feuilleMutee(".propositions-oeuvres .proposition[data-oeuvre-revelee=true] img",
                corps -> corps + ";background: url('/images/marque-echec.svg')");

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C5) && grief.contains("revelation"));
    }

    /** E7 : un plafond de hauteur sur l'image essayee la ferait changer de taille. */
    @Test
    void unMaxHeightSurLImageEssayeeFaitEchouerLeCritere7a() {
        Map<String, String> blocs = feuilleMutee(IMAGE_ESSAYEE, corps -> corps + ";max-height: 9rem");

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C7 + " (a)")
                && grief.contains("max-height"));
    }

    /** nadia-prof, critere 7 reecrit : un padding sur la case essayee la deplace. */
    @Test
    void unPaddingSurLaCaseEssayeeFaitEchouerLeCritere7a() {
        Map<String, String> blocs = feuilleReelle();
        blocs.put(".propositions-oeuvres .proposition.deja-essayee", "padding: 1rem");

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C7 + " (a)")
                && grief.contains("padding"));
    }

    @Test
    void uneBordureDeLargeurDifferenteSurLImageEssayeeFaitEchouerLeCritere7a() {
        Map<String, String> blocs = feuilleMutee(IMAGE_ESSAYEE, corps -> corps + ";border: 0.25rem dashed #000000");

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C7 + " (a)")
                && grief.contains("bordure"));
    }

    @Test
    void uneBordureDeLaMemeLargeurSurLImageEssayeeNeFaitPasEchouerLeCritere7() {
        Map<String, String> blocs = feuilleMutee(IMAGE_ESSAYEE, corps -> corps + ";border: 0.125rem dashed #1c2733");

        assertThat(griefsAvec(blocs)).noneMatch(grief -> grief.startsWith(C7));
    }

    /** nadia-prof, critere 7 reecrit, clause (b) : position: absolute retire du ::before. */
    @Test
    void uneMarqueSansPositionAbsolueFaitEchouerLeCritere7b() {
        Map<String, String> blocs = feuilleMutee(MARQUE, corps -> corps.replace("position: absolute", ""));

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C7 + " (b)"));
    }

    /** Clause (c) : la rangee n'est pas visee, elle deplace ce qui est sous elle. */
    @Test
    void laRegleDeLaRangeeNEstPasViseeParLeCritere7() {
        Map<String, String> blocs = feuilleReelle();
        blocs.put(".propositions-oeuvres:has(.proposition.deja-essayee)", "padding-bottom: 5rem;height: 20rem");

        assertThat(griefsAvec(blocs)).noneMatch(grief -> grief.startsWith(C7));
    }

    @Test
    void laMarqueEtLaRangeeReellesSontLuesCommeCritere7bEt7c() {
        assertThat(sujet(".propositions-oeuvres:has(.proposition.deja-essayee)"))
                .isEqualTo(".propositions-oeuvres:has(.proposition.deja-essayee)");
        assertThat(classesDuSujet(".propositions-oeuvres:has(.proposition.deja-essayee)"))
                .containsExactly(RANGEE);
        assertThat(classesDuSujet(MARQUE)).containsExactlyInAnyOrder("proposition", ESSAYEE);
        assertThat(sujetEstUnPseudoElement(MARQUE)).isTrue();
        assertThat(sujetEstUneImage(IMAGE_ESSAYEE)).isTrue();
    }

    /** ADR-0013 obligation 1 : un selecteur neuf est lu sans etre liste. */
    @Test
    void lePerimetreSeDeriveDuSelecteurEtPasDUneListe() {
        Map<String, String> blocs = feuilleReelle();
        blocs.put(".propositions-oeuvres .proposition.deja-essayee.autre img", "mask: none");

        assertThat(griefsAvec(blocs)).anyMatch(grief -> grief.startsWith(C2) && grief.contains("autre"));
    }

    /** Critere 8 : un mot barre de moins, ou de plus, change la branche texte. */
    @Test
    void uneLigneLineThroughDeMoinsOuDePlusFaitEchouerLeCritere8() {
        String feuille = feuilleDe(RACINE_PRODUCTION);
        int position = feuille.indexOf("text-decoration: line-through");
        assertThat(position).isNotNegative();

        assertThat(griefsDeLaBrancheTexte(feuille)).isEmpty();
        assertThat(griefsDeLaBrancheTexte(feuille.replaceFirst("text-decoration: line-through;", "")))
                .anyMatch(grief -> grief.startsWith(C8));
        assertThat(griefsDeLaBrancheTexte(feuille + "\n.x { text-decoration: line-through; }\n"))
                .anyMatch(grief -> grief.startsWith(C8));
    }

    /**
     * ADR-0013 obligation 3 : un jeu fautif commite, sans marque. L'oeuvre essayee ne porte que
     * l'attenuation : les criteres 1, 3, 4, 5 et 8 rougissent.
     */
    @Test
    void leJeuFautifSansMarqueEstRefuse() {
        Path racine = CONTRE_EXEMPLES.resolve("sans-marque");

        assertThatThrownBy(() -> assertThat(griefsDe(racine)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(C1).hasMessageContaining(C3).hasMessageContaining(C4)
                .hasMessageContaining(C5).hasMessageContaining(C8);
    }

    /**
     * ADR-0013 obligation 3 : un jeu fautif commite, une marque mal faite. Filtre sur l'image essayee,
     * top a zero et sans pointer-events, disque de la couleur de la croix, selecteur de la
     * revelation, largeur sur l'image : les criteres 2, 3, 4, 5 et 7 rougissent.
     */
    @Test
    void leJeuFautifAMarqueMalFaiteEstRefuse() {
        Path racine = CONTRE_EXEMPLES.resolve("marque-mal-faite");

        assertThatThrownBy(() -> assertThat(griefsDe(racine)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(C2).hasMessageContaining(C3).hasMessageContaining(C4)
                .hasMessageContaining(C5).hasMessageContaining(C7 + " (a)").hasMessageContaining(C7 + " (b)");
    }
}
