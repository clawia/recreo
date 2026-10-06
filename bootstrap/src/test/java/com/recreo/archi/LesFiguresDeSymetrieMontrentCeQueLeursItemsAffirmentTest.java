package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * SYM-CE1-1 a SYM-CE1-3 (nadia-prof, arbitrage prealable de s43, lot 3, section 3), DEP-CE1-2 et les
 * contraintes de dessin d'ines-design (s52, sections 2 et 4) : <b>le dessin d'un item de symetrie montre
 * ce que l'item affirme.</b>
 *
 * <p><b>Ce que ce garde-fou ajoute.</b> Les garde-fous de designation jugent des cadres de zone, jamais
 * la geometrie du dessin qu'ils recouvrent, et {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} ne
 * regarde pas ce qu'un fichier trace. Ici, pour chaque item d'un paquet qui sert
 * {@link #LIGNE_DE_LA_SYMETRIE} :</p>
 * <ul>
 *   <li><b>une figure a lignes candidates</b> (un {@code <polygon>}) : une seule ligne est un axe de
 *       symetrie du contour (calcule par reflexion des sommets), les lignes ont toutes le meme trait,
 *       chaque ligne s'arrete au bord du cadre de sa zone, la bonne zone est celle de l'axe, les cadres
 *       ne se frolent pas (5 unites), aucune ligne etrangere ni le contour ne passe a moins de 5 et 4
 *       unites d'un cadre, deux points singuliers ne sont jamais a moins de 7 unites l'un de l'autre, et
 *       au moins la moitie des figures portent une ligne qui coupe le contour en deux parties de meme
 *       aire sans etre un axe (SYM-CE1-2) ;</li>
 *   <li><b>un quadrillage</b> (des {@code <rect>} pleins) : de 4 x 4 a 6 x 6 cases de meme taille, n
 *       pair, l'axe est trace sur la ligne centrale, la figure est symetrique a une case pres, cette case
 *       est la bonne zone, toutes les zones sont des cases vides entieres, et la bonne case n'est pas la
 *       seule zone qui touche la figure (SYM-CE1-3).</li>
 * </ul>
 *
 * <p><b>Perimetre derive</b> (ADR-0013 obligation 1) : un code de ligne du referentiel, jamais un nom de
 * paquet ni un prefixe de fichier (ADR-0077) ; le genre d'un dessin (figure ou quadrillage) est lu dans
 * le fichier. <b>Ce qu'il ne juge pas</b> : que la figure se lise a la taille servie, que le leurre
 * parallele se distingue de l'axe sans mesurer, que chaque onglet appartienne a sa seule ligne a l'oeil
 * (ZON-9), et le choix pedagogique des cases candidates - c'est nadia-prof, en jouant.</p>
 */
class LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest {

    private static final String LIGNE_DE_LA_SYMETRIE = "MAT-CE1-17";

    private static final String ILLUSTRATIONS_DE_CONTENU = "src/main/resources/static/illustrations";

    private static final Pattern POLYGONE = Pattern.compile("<polygon points=\"([^\"]*)\"");

    private static final Pattern LIGNE = Pattern.compile(
            "<line x1=\"([\\d.]+)\" y1=\"([\\d.]+)\" x2=\"([\\d.]+)\" y2=\"([\\d.]+)\"([^>]*)/>");

    private static final Pattern CASE_PLEINE = Pattern.compile(
            "<rect x=\"(\\d+)\" y=\"(\\d+)\" width=\"(\\d+)\" height=\"(\\d+)\" fill=\"#[0-9a-f]{6}\"");

    /** Un item de designation d'un paquet de la ligne, avec ce que le contenu en dit. */
    record ItemDeSymetrie(String paquet, String dessin, String bonneZone, List<Zone> zones) {
    }

    record Zone(String id, int x, int y, int largeur, int hauteur) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier).replace("\r\n", "\n");
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static List<ItemDeSymetrie> itemsDeSymetrie(List<Path> repertoiresDeContenu) {
        List<ItemDeSymetrie> items = new ArrayList<>();
        for (Path repertoire : repertoiresDeContenu) {
            try (Stream<Path> arborescence = Files.walk(repertoire)) {
                for (Path paquet : arborescence.filter(Files::isRegularFile)
                        .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                        .sorted().toList()) {
                    ajouter(paquet, items);
                }
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return items;
    }

    private static void ajouter(Path paquet, List<ItemDeSymetrie> items) {
        if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> corps)
                || !(corps.get("lignesDeReferentiel") instanceof List<?> lignes)
                || lignes.stream().noneMatch(l -> LIGNE_DE_LA_SYMETRIE.equals(String.valueOf(l)))
                || !(corps.get("items") instanceof List<?> declares)) {
            return;
        }
        for (Object declare : declares) {
            if (!(declare instanceof Map<?, ?> champs) || !(champs.get("zones") instanceof List<?> brutes)) {
                continue;
            }
            List<Zone> zones = new ArrayList<>();
            for (Object brute : brutes) {
                Map<?, ?> zone = (Map<?, ?>) brute;
                Map<?, ?> cadre = (Map<?, ?>) zone.get("cadre");
                zones.add(new Zone(String.valueOf(zone.get("id")), (Integer) cadre.get("x"),
                        (Integer) cadre.get("y"), (Integer) cadre.get("largeur"),
                        (Integer) cadre.get("hauteur")));
            }
            items.add(new ItemDeSymetrie(paquet.getFileName().toString(),
                    String.valueOf(champs.get("illustration")), String.valueOf(champs.get("bonneZone")),
                    zones));
        }
    }

    /**
     * Fins de ligne normalisees en LF : un depot extrait sous core.autocrlf=true ecrit les dessins en
     * CRLF, et une mutation ou un motif qui suppose un saut de ligne seul devient alors un no-op muet.
     */
    private static String enLf(String source) {
        return source.replace("\r\n", "\n");
    }

    private static Map<String, String> dessinsDe(Path racine) {
        Map<String, String> dessins = new LinkedHashMap<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path repertoire = racine.resolve(module).resolve(ILLUSTRATIONS_DE_CONTENU);
            if (Files.isDirectory(repertoire)) {
                LIllustrationDeContenuResoutEtAucuneNeDortTest
                        .fichiersIndexesParNomDeBase(List.of(repertoire))
                        .forEach((nom, source) -> dessins.put(nom, enLf(source)));
            }
        }
        return dessins;
    }

    // ----- geometrie

    private record Segment(double x1, double y1, double x2, double y2, String trait) {
    }

    private static double[][] sommets(String source) {
        Matcher polygone = POLYGONE.matcher(source);
        if (!polygone.find()) {
            return new double[0][];
        }
        String[] paires = polygone.group(1).strip().split(" ");
        double[][] points = new double[paires.length][];
        for (int rang = 0; rang < paires.length; rang++) {
            String[] xy = paires[rang].split(",");
            points[rang] = new double[] {Double.parseDouble(xy[0]), Double.parseDouble(xy[1])};
        }
        return points;
    }

    private static List<Segment> lignes(String source) {
        List<Segment> lignes = new ArrayList<>();
        Matcher trouve = LIGNE.matcher(source);
        while (trouve.find()) {
            lignes.add(new Segment(Double.parseDouble(trouve.group(1)), Double.parseDouble(trouve.group(2)),
                    Double.parseDouble(trouve.group(3)), Double.parseDouble(trouve.group(4)),
                    trouve.group(5).strip()));
        }
        return lignes;
    }

    private static double distance(double[] a, double[] b) {
        return Math.hypot(a[0] - b[0], a[1] - b[1]);
    }

    private static double[] reflechi(double[] p, Segment axe) {
        double dx = axe.x2() - axe.x1();
        double dy = axe.y2() - axe.y1();
        double t = ((p[0] - axe.x1()) * dx + (p[1] - axe.y1()) * dy) / (dx * dx + dy * dy);
        double fx = axe.x1() + t * dx;
        double fy = axe.y1() + t * dy;
        return new double[] {2 * fx - p[0], 2 * fy - p[1]};
    }

    private static boolean estUnAxe(double[][] contour, Segment ligne) {
        for (double[] sommet : contour) {
            double[] image = reflechi(sommet, ligne);
            boolean trouve = false;
            for (double[] autre : contour) {
                trouve |= distance(image, autre) < 0.05;
            }
            if (!trouve) {
                return false;
            }
        }
        return true;
    }

    private static double aire(List<double[]> polygone) {
        double somme = 0;
        for (int rang = 0; rang < polygone.size(); rang++) {
            double[] a = polygone.get(rang);
            double[] b = polygone.get((rang + 1) % polygone.size());
            somme += a[0] * b[1] - b[0] * a[1];
        }
        return Math.abs(somme) / 2;
    }

    /** Sutherland-Hodgman pour un contour convexe : la partie du contour du cote {@code signe} de la droite. */
    private static List<double[]> decoupe(double[][] contour, Segment ligne, int signe) {
        List<double[]> sortie = new ArrayList<>();
        for (int rang = 0; rang < contour.length; rang++) {
            double[] p = contour[rang];
            double[] q = contour[(rang + 1) % contour.length];
            boolean pDedans = signe * cote(ligne, p) >= -1e-9;
            boolean qDedans = signe * cote(ligne, q) >= -1e-9;
            if (qDedans) {
                if (!pDedans) {
                    sortie.add(intersection(ligne, p, q));
                }
                sortie.add(q);
            } else if (pDedans) {
                sortie.add(intersection(ligne, p, q));
            }
        }
        return sortie;
    }

    private static double cote(Segment ligne, double[] p) {
        return (ligne.x2() - ligne.x1()) * (p[1] - ligne.y1()) - (ligne.y2() - ligne.y1()) * (p[0] - ligne.x1());
    }

    private static double[] intersection(Segment ligne, double[] p, double[] q) {
        double cp = cote(ligne, p);
        double cq = cote(ligne, q);
        double t = cp / (cp - cq);
        return new double[] {p[0] + t * (q[0] - p[0]), p[1] + t * (q[1] - p[1])};
    }

    private static boolean coupeEnDeuxPartiesDeMemeAire(double[][] contour, Segment ligne) {
        List<double[]> un = decoupe(contour, ligne, 1);
        List<double[]> deux = decoupe(contour, ligne, -1);
        return un.size() > 2 && deux.size() > 2 && Math.abs(aire(un) - aire(deux)) < 0.5;
    }

    private static double distancePointSegment(double[] p, double[] a, double[] b) {
        double dx = b[0] - a[0];
        double dy = b[1] - a[1];
        double longueur2 = dx * dx + dy * dy;
        double t = longueur2 == 0 ? 0
                : Math.max(0, Math.min(1, ((p[0] - a[0]) * dx + (p[1] - a[1]) * dy) / longueur2));
        return distance(p, new double[] {a[0] + t * dx, a[1] + t * dy});
    }

    private static boolean secoupent(double[] p1, double[] p2, double[] q1, double[] q2) {
        double d1 = orientation(q1, q2, p1);
        double d2 = orientation(q1, q2, p2);
        double d3 = orientation(p1, p2, q1);
        double d4 = orientation(p1, p2, q2);
        return d1 * d2 < 0 && d3 * d4 < 0;
    }

    private static double orientation(double[] a, double[] b, double[] c) {
        return (c[1] - a[1]) * (b[0] - a[0]) - (b[1] - a[1]) * (c[0] - a[0]);
    }

    private static double distanceSegmentSegment(double[] p1, double[] p2, double[] q1, double[] q2) {
        if (secoupent(p1, p2, q1, q2)) {
            return 0;
        }
        return Math.min(Math.min(distancePointSegment(p1, q1, q2), distancePointSegment(p2, q1, q2)),
                Math.min(distancePointSegment(q1, p1, p2), distancePointSegment(q2, p1, p2)));
    }

    private static double distanceSegmentCadre(double[] a, double[] b, Zone zone) {
        double[][] coins = {{zone.x(), zone.y()}, {zone.x() + zone.largeur(), zone.y()},
                {zone.x() + zone.largeur(), zone.y() + zone.hauteur()}, {zone.x(), zone.y() + zone.hauteur()}};
        for (double[] extremite : List.of(a, b)) {
            if (extremite[0] > zone.x() && extremite[0] < zone.x() + zone.largeur()
                    && extremite[1] > zone.y() && extremite[1] < zone.y() + zone.hauteur()) {
                return 0;
            }
        }
        double minimum = Double.MAX_VALUE;
        for (int rang = 0; rang < 4; rang++) {
            minimum = Math.min(minimum, distanceSegmentSegment(a, b, coins[rang], coins[(rang + 1) % 4]));
        }
        return minimum;
    }

    private static double ecartEntreCadres(Zone a, Zone b) {
        double ex = Math.max(a.x() - (b.x() + b.largeur()), b.x() - (a.x() + a.largeur()));
        double ey = Math.max(a.y() - (b.y() + b.hauteur()), b.y() - (a.y() + a.hauteur()));
        return Math.max(ex, ey);
    }

    private static boolean surLeBord(double[] p, Zone zone) {
        double tolerance = 0.6;
        boolean dansLeCadreElargi = p[0] >= zone.x() - tolerance && p[0] <= zone.x() + zone.largeur() + tolerance
                && p[1] >= zone.y() - tolerance && p[1] <= zone.y() + zone.hauteur() + tolerance;
        boolean dansLeCadreReduit = p[0] > zone.x() + tolerance && p[0] < zone.x() + zone.largeur() - tolerance
                && p[1] > zone.y() + tolerance && p[1] < zone.y() + zone.hauteur() - tolerance;
        return dansLeCadreElargi && !dansLeCadreReduit;
    }

    private static double[] pointDeFin(Segment ligne, Zone zone) {
        double[] un = {ligne.x1(), ligne.y1()};
        double[] deux = {ligne.x2(), ligne.y2()};
        boolean unSurLeBord = surLeBord(un, zone);
        boolean deuxSurLeBord = surLeBord(deux, zone);
        if (unSurLeBord == deuxSurLeBord) {
            return null;
        }
        return unSurLeBord ? un : deux;
    }

    /** Ce qu'un juge rend pour une figure : les griefs, et si elle porte une ligne qui coupe sans etre axe. */
    record Verdict(List<String> griefs, boolean porteUnLeurreDeMemeAire) {
    }

    static Verdict juge(ItemDeSymetrie item, String source) {
        List<String> griefs = new ArrayList<>();
        double[][] contour = sommets(source);
        List<Segment> lignes = lignes(source);
        List<Zone> zones = item.zones();
        String ou = item.paquet() + ", figure '" + item.dessin() + "'";
        if (lignes.size() < 3 || lignes.size() != zones.size()) {
            griefs.add(ou + " : " + lignes.size() + " lignes pour " + zones.size()
                    + " zones (ZON-8 : trois au moins, une zone par ligne)");
            return new Verdict(griefs, false);
        }
        if (lignes.stream().map(Segment::trait).distinct().count() != 1) {
            griefs.add(ou + " : les lignes candidates n'ont pas toutes le meme trait - un trait propre a une"
                    + " ligne la designerait (ZON-9)");
        }
        List<Integer> axes = new ArrayList<>();
        boolean leurre = false;
        for (int rang = 0; rang < lignes.size(); rang++) {
            if (estUnAxe(contour, lignes.get(rang))) {
                axes.add(rang);
            } else {
                leurre |= coupeEnDeuxPartiesDeMemeAire(contour, lignes.get(rang));
            }
        }
        if (axes.size() != 1) {
            griefs.add(ou + " : " + axes.size() + " lignes sont un axe de symetrie du contour, une seule"
                    + " est attendue (SYM-CE1-1)");
        }
        Zone[] zoneDeLaLigne = new Zone[lignes.size()];
        for (int rang = 0; rang < lignes.size(); rang++) {
            List<Zone> candidates = new ArrayList<>();
            for (Zone zone : zones) {
                if (pointDeFin(lignes.get(rang), zone) != null) {
                    candidates.add(zone);
                }
            }
            if (candidates.size() != 1) {
                griefs.add(ou + " : la ligne " + rang + " s'arrete au bord de " + candidates.size()
                        + " cadres de zone, un seul est attendu (l'onglet de la ligne)");
            } else {
                zoneDeLaLigne[rang] = candidates.get(0);
            }
        }
        if (axes.size() == 1 && zoneDeLaLigne[axes.get(0)] != null
                && !zoneDeLaLigne[axes.get(0)].id().equals(item.bonneZone())) {
            griefs.add(ou + " : la bonne zone est '" + item.bonneZone() + "', l'onglet de l'axe est '"
                    + zoneDeLaLigne[axes.get(0)].id() + "'");
        }
        for (Zone zone : zones) {
            if (zone.x() + zone.largeur() > 100 || zone.y() + zone.hauteur() > 100) {
                griefs.add(ou + " : le cadre '" + zone.id() + "' sort du cadre de vue");
            }
        }
        for (int i = 0; i < zones.size(); i++) {
            for (int j = i + 1; j < zones.size(); j++) {
                if (ecartEntreCadres(zones.get(i), zones.get(j)) < 5) {
                    griefs.add(ou + " : les cadres '" + zones.get(i).id() + "' et '" + zones.get(j).id()
                            + "' sont a moins de 5 unites l'un de l'autre");
                }
            }
        }
        for (int rang = 0; rang < lignes.size(); rang++) {
            for (Zone zone : zones) {
                if (zone == zoneDeLaLigne[rang]) {
                    continue;
                }
                Segment ligne = lignes.get(rang);
                if (distanceSegmentCadre(new double[] {ligne.x1(), ligne.y1()},
                        new double[] {ligne.x2(), ligne.y2()}, zone) < 5) {
                    griefs.add(ou + " : la ligne " + rang + " passe a moins de 5 unites du cadre '"
                            + zone.id() + "' qui n'est pas son onglet (ZON-9)");
                }
            }
        }
        for (Zone zone : zones) {
            for (int rang = 0; rang < contour.length; rang++) {
                if (distanceSegmentCadre(contour[rang], contour[(rang + 1) % contour.length], zone) < 4) {
                    griefs.add(ou + " : le contour passe a moins de 4 unites du cadre '" + zone.id() + "'");
                }
            }
        }
        List<double[]> singuliers = new ArrayList<>(List.of(contour));
        for (int i = 0; i < lignes.size(); i++) {
            double[] a = {lignes.get(i).x1(), lignes.get(i).y1()};
            double[] b = {lignes.get(i).x2(), lignes.get(i).y2()};
            for (int j = i + 1; j < lignes.size(); j++) {
                double[] c = {lignes.get(j).x1(), lignes.get(j).y1()};
                double[] d = {lignes.get(j).x2(), lignes.get(j).y2()};
                if (secoupent(a, b, c, d)) {
                    singuliers.add(intersectionDeSegments(a, b, c, d));
                }
            }
            for (int k = 0; k < contour.length; k++) {
                double[] c = contour[k];
                double[] d = contour[(k + 1) % contour.length];
                if (secoupent(a, b, c, d)) {
                    singuliers.add(intersectionDeSegments(a, b, c, d));
                }
            }
        }
        List<double[]> distincts = new ArrayList<>();
        for (double[] point : singuliers) {
            if (distincts.stream().noneMatch(autre -> distance(point, autre) < 0.5)) {
                distincts.add(point);
            }
        }
        for (int i = 0; i < distincts.size(); i++) {
            for (int j = i + 1; j < distincts.size(); j++) {
                if (distance(distincts.get(i), distincts.get(j)) < 7) {
                    griefs.add(ou + " : deux points singuliers a moins de 7 unites l'un de l'autre ("
                            + Math.round(distincts.get(i)[0]) + "," + Math.round(distincts.get(i)[1]) + " et "
                            + Math.round(distincts.get(j)[0]) + "," + Math.round(distincts.get(j)[1]) + ")");
                }
            }
        }
        return new Verdict(griefs, leurre);
    }

    private static double[] intersectionDeSegments(double[] a, double[] b, double[] c, double[] d) {
        double den = (a[0] - b[0]) * (c[1] - d[1]) - (a[1] - b[1]) * (c[0] - d[0]);
        double t = ((a[0] - c[0]) * (c[1] - d[1]) - (a[1] - c[1]) * (c[0] - d[0])) / den;
        return new double[] {a[0] + t * (b[0] - a[0]), a[1] + t * (b[1] - a[1])};
    }

    // ----- quadrillage

    private static Set<List<Integer>> casesPleines(String source, int cote) {
        Set<List<Integer>> cases = new HashSet<>();
        Matcher trouve = CASE_PLEINE.matcher(source);
        while (trouve.find()) {
            int x = Integer.parseInt(trouve.group(1));
            int y = Integer.parseInt(trouve.group(2));
            cases.add(List.of((y - 2 - 2) / cote, (x - 2 - 2) / cote));
        }
        return cases;
    }

    static List<String> jugeUnQuadrillage(ItemDeSymetrie item, String source) {
        List<String> griefs = new ArrayList<>();
        String ou = item.paquet() + ", quadrillage '" + item.dessin() + "'";
        int verticales = 0;
        Matcher traits = LIGNE.matcher(source);
        boolean axeVertical = false;
        boolean axeHorizontal = false;
        while (traits.find()) {
            String trait = traits.group(5);
            if (trait.contains("stroke-width=\"2.4\"")) {
                axeVertical = traits.group(1).equals("50") && traits.group(3).equals("50");
                axeHorizontal = traits.group(2).equals("50") && traits.group(4).equals("50");
            } else if (traits.group(1).equals(traits.group(3))) {
                verticales++;
            }
        }
        int n = verticales - 1;
        if (n != 4 && n != 6) {
            griefs.add(ou + " : un quadrillage de " + n + " x " + n + ", de 4 x 4 a 6 x 6 et n pair attendus"
                    + " (DEP-CE1-2, SYM-CE1-3)");
            return griefs;
        }
        if (axeVertical == axeHorizontal) {
            griefs.add(ou + " : l'axe n'est pas un trait de 2,4 unites sur la ligne centrale, vertical ou"
                    + " horizontal, et un seul (SYM-CE1-3)");
            return griefs;
        }
        int cote = 96 / n;
        Set<List<Integer>> pleines = casesPleines(source, cote);
        Set<List<Integer>> miroir = new HashSet<>();
        for (List<Integer> cellule : pleines) {
            miroir.add(axeVertical ? List.of(cellule.get(0), n - 1 - cellule.get(1))
                    : List.of(n - 1 - cellule.get(0), cellule.get(1)));
        }
        Set<List<Integer>> manquantes = new HashSet<>(miroir);
        manquantes.removeAll(pleines);
        Set<List<Integer>> enTrop = new HashSet<>(pleines);
        enTrop.removeAll(miroir);
        if (manquantes.size() != 1) {
            griefs.add(ou + " : la figure doit etre symetrique a une seule case pres, il manque "
                    + manquantes.size() + " cases de l'autre cote (SYM-CE1-3)");
            return griefs;
        }
        List<Integer> attendue = manquantes.iterator().next();
        List<Integer> origine = axeVertical ? List.of(attendue.get(0), n - 1 - attendue.get(1))
                : List.of(n - 1 - attendue.get(0), attendue.get(1));
        if (!enTrop.equals(Set.of(origine))) {
            griefs.add(ou + " : " + enTrop.size() + " cases pleines n'ont pas de symetrique, la seule"
                    + " attendue est la source de la case manquante (SYM-CE1-3)");
            return griefs;
        }
        Set<List<Integer>> zonesDeCases = new HashSet<>();
        List<List<Integer>> zonesQuiTouchent = new ArrayList<>();
        List<Integer> caseDeLaBonneZone = null;
        for (Zone zone : item.zones()) {
            List<Integer> cellule = List.of((zone.y() - 2) / cote, (zone.x() - 2) / cote);
            if (zone.largeur() != cote || zone.hauteur() != cote || (zone.x() - 2) % cote != 0
                    || (zone.y() - 2) % cote != 0) {
                griefs.add(ou + " : le cadre '" + zone.id() + "' n'est pas une case entiere du quadrillage");
            }
            if (pleines.contains(cellule)) {
                griefs.add(ou + " : la zone '" + zone.id() + "' est une case pleine - seules les cases vides"
                        + " candidates sont des zones (ines-design, 4.3)");
            }
            zonesDeCases.add(cellule);
            if (zone.id().equals(item.bonneZone())) {
                caseDeLaBonneZone = cellule;
            }
            for (int[] voisin : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
                if (pleines.contains(List.of(cellule.get(0) + voisin[0], cellule.get(1) + voisin[1]))) {
                    zonesQuiTouchent.add(cellule);
                    break;
                }
            }
        }
        if (!attendue.equals(caseDeLaBonneZone)) {
            griefs.add(ou + " : la bonne zone est la case " + caseDeLaBonneZone + ", la case manquante est "
                    + attendue);
        }
        if (zonesQuiTouchent.size() < 2 || !zonesQuiTouchent.contains(attendue)) {
            griefs.add(ou + " : la case attendue ne doit pas etre la seule zone qui touche la figure"
                    + " (SYM-CE1-3), " + zonesQuiTouchent.size() + " zone(s) la touchent");
        }
        if (zonesDeCases.size() != item.zones().size() || item.zones().size() < 3) {
            griefs.add(ou + " : trois zones distinctes au moins (ZON-8)");
        }
        return griefs;
    }

    /** Tous les griefs d'un depot de contenu et de dessins, et le compte de figures a leurre de meme aire. */
    static List<String> defauts(List<ItemDeSymetrie> items, Map<String, String> dessins) {
        List<String> defauts = new ArrayList<>();
        int figures = 0;
        int figuresALeurre = 0;
        for (ItemDeSymetrie item : items) {
            String source = dessins.get(item.dessin());
            if (source == null) {
                defauts.add(item.paquet() + " : aucun fichier pour '" + item.dessin() + "'");
                continue;
            }
            if (source.contains("<polygon")) {
                Verdict verdict = juge(item, source);
                defauts.addAll(verdict.griefs());
                figures++;
                if (verdict.porteUnLeurreDeMemeAire()) {
                    figuresALeurre++;
                }
            } else {
                defauts.addAll(jugeUnQuadrillage(item, source));
            }
        }
        if (2 * figuresALeurre < figures) {
            defauts.add("SYM-CE1-2 : " + figuresALeurre + " figures sur " + figures + " portent une ligne qui"
                    + " coupe le contour en deux parties de meme aire sans etre un axe, la moitie au moins"
                    + " est attendue");
        }
        return defauts;
    }

    private static List<ItemDeSymetrie> recensement(Path racine) {
        return itemsDeSymetrie(AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                .repertoiresDeContenu(racine));
    }

    @Test
    void chaqueDessinDeSymetrieServiMontreCeQueSonItemAffirme() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(defauts(recensement(racine), dessinsDe(racine))).isEmpty();
    }

    @Test
    void leRecensementTrouveLesFiguresEtLesQuadrillagesDeLaLigne() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> dessins = dessinsDe(racine);
        List<ItemDeSymetrie> items = recensement(racine);
        assertThat(items.stream().filter(item -> dessins.get(item.dessin()).contains("<polygon")))
                .as("ADR-0051 : trois figures au moins").hasSizeGreaterThanOrEqualTo(3);
        assertThat(items.stream().filter(item -> !dessins.get(item.dessin()).contains("<polygon")))
                .as("ADR-0051 : trois quadrillages au moins").hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    void uneBonneZoneQuiNEstPasLOngletDeLAxeFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> dessins = dessinsDe(racine);
        List<ItemDeSymetrie> items = new ArrayList<>(recensement(racine));
        int rang = 0;
        while (!dessins.get(items.get(rang).dessin()).contains("<polygon")) {
            rang++;
        }
        ItemDeSymetrie figure = items.get(rang);
        String autre = figure.zones().stream().map(Zone::id).filter(id -> !id.equals(figure.bonneZone()))
                .findFirst().orElseThrow();
        items.set(rang, new ItemDeSymetrie(figure.paquet(), figure.dessin(), autre, figure.zones()));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains(figure.dessin())
                && defaut.contains("l'onglet de l'axe"));
    }

    @Test
    void unAxeQuiNEnEstPlusUnFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        List<ItemDeSymetrie> items = recensement(racine);
        ItemDeSymetrie figure = items.stream().filter(item -> dessins.get(item.dessin()).contains("<polygon"))
                .findFirst().orElseThrow();
        String source = dessins.get(figure.dessin());
        dessins.put(figure.dessin(), source.replaceFirst("<polygon points=\"([\\d.]+),([\\d.]+)",
                "<polygon points=\"$1,$2 90,5"));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains(figure.dessin())
                && defaut.contains("axe de symetrie"));
    }

    @Test
    void uneCaseQuiManqueDeTropFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        List<ItemDeSymetrie> items = recensement(racine);
        ItemDeSymetrie grille = items.stream().filter(item -> !dessins.get(item.dessin()).contains("<polygon"))
                .findFirst().orElseThrow();
        String avant = dessins.get(grille.dessin());
        dessins.put(grille.dessin(), avant.replaceFirst("<rect [^>]*/>\n", ""));
        assertThat(dessins.get(grille.dessin())).as("la mutation retire bien une case pleine")
                .isNotEqualTo(avant);

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains(grille.dessin())
                && defaut.contains("SYM-CE1-3"));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif commite, lu sur disque. Il porte une figure dont aucune
     * ligne n'est un axe, et un quadrillage dont la figure n'est pas symetrique.
     */
    @Test
    void leJeuFautifCommiteEstRefuse() {
        Path jeuFautif = Path.of("src", "test", "resources", "contreexemple",
                "LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest", "symetrie-fautive");
        List<ItemDeSymetrie> items = itemsDeSymetrie(List.of(jeuFautif.resolve("contenu")));
        Map<String, String> dessins = LIllustrationDeContenuResoutEtAucuneNeDortTest
                .fichiersIndexesParNomDeBase(List.of(jeuFautif.resolve("static/illustrations")));

        assertThat(items).as("ADR-0051 : le recensement lit les deux items du jeu fautif").hasSize(2);
        assertThatThrownBy(() -> assertThat(defauts(items, dessins)).isEmpty())
                .isInstanceOf(AssertionError.class);
        assertThat(defauts(items, dessins))
                .anyMatch(defaut -> defaut.contains("figure-fautive") && defaut.contains("axe de symetrie"))
                .anyMatch(defaut -> defaut.contains("grille-fautive") && defaut.contains("SYM-CE1-3"));
    }
}
