package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * MUL-CE1-5 (nadia-prof, arbitrage prealable de s43, lot 1, section 6) et les contraintes de dessin
 * d'ines-design (s52, section 3) : <b>un reseau de points est un reseau, et il montre ce que l'item
 * qui le nomme affirme.</b>
 *
 * <p><b>Ce que ce garde-fou ajoute.</b> {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} juge
 * qu'un identifiant resout et qu'aucun fichier ne dort ; il ne regarde pas ce que le fichier trace.
 * Ici, pour chaque item d'un paquet qui sert {@link #LIGNE_DE_LA_MULTIPLICATION} et qui declare un
 * dessin : le dessin est un rectangle complet de {@code <circle>} identiques, a pas egal en largeur et en
 * hauteur et identique d'un dessin a l'autre, d'au plus dix lignes et dix colonnes, sans autre forme ;
 * et son nombre de lignes et de colonnes est celui que la bonne reponse de l'item ecrit (convention du
 * paquet : "lignes x points par ligne" pour la lecture par lignes, "colonnes x points par colonne" pour la
 * lecture par colonnes).</p>
 *
 * <p><b>Perimetre derive</b> (ADR-0013 obligation 1) : un code de ligne du referentiel, jamais un nom de
 * paquet ni un prefixe de fichier (ADR-0077). Les dessins viennent du repertoire des illustrations de
 * contenu.</p>
 *
 * <p><b>Ce qu'il ne juge pas</b> : qu'un reseau se compte a l'oeil sans se confondre a la taille servie
 * (nadia-prof, en jouant) ni que la convention d'ecriture soit celle de la classe.</p>
 */
class LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest {

    private static final String LIGNE_DE_LA_MULTIPLICATION = "MAT-CE1-06";

    private static final String ILLUSTRATIONS_DE_CONTENU =
            "src/main/resources/static/illustrations";

    private static final String PAR_LIGNES = "Compte les points ligne par ligne.";

    private static final String PAR_COLONNES = "Compte les points colonne par colonne.";

    /** MUL-CE1-5 : au plus dix lignes et dix colonnes. */
    private static final int MAXIMUM_PAR_AXE = 10;

    private static final Pattern POINT = Pattern.compile(
            "<circle cx=\"(\\d+)\" cy=\"(\\d+)\" r=\"(\\d+)\"/>");

    private static final Pattern PRODUIT = Pattern.compile("^(\\d+) × (\\d+)$");

    private static final Pattern CADRE = Pattern.compile("viewBox=\"0 0 (\\d+) (\\d+)\" width=\"(\\d+)\""
            + " height=\"(\\d+)\"");

    /** Un item illustre d'un paquet de la ligne, avec ce que sa bonne reponse dit du reseau. */
    record ItemDeReseau(String paquet, String enonce, String dessin, int lignes, int colonnes) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier).replace("\r\n", "\n");
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static List<ItemDeReseau> itemsDeReseau(List<Path> repertoiresDeContenu) {
        List<ItemDeReseau> items = new ArrayList<>();
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

    private static void ajouter(Path paquet, List<ItemDeReseau> items) {
        if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> corps)
                || !(corps.get("lignesDeReferentiel") instanceof List<?> lignes)
                || lignes.stream().noneMatch(l -> LIGNE_DE_LA_MULTIPLICATION.equals(String.valueOf(l)))
                || !(corps.get("items") instanceof List<?> declares)) {
            return;
        }
        for (Object declare : declares) {
            if (!(declare instanceof Map<?, ?> champs) || !(champs.get("illustration") instanceof String dessin)) {
                continue;
            }
            String enonce = String.valueOf(champs.get("enonce"));
            Matcher bonne = PRODUIT.matcher(String.valueOf(champs.get("bonneReponse")));
            boolean parLignes = enonce.contains(PAR_LIGNES);
            boolean parColonnes = enonce.contains(PAR_COLONNES);
            if (!bonne.matches() || parLignes == parColonnes) {
                items.add(new ItemDeReseau(paquet.getFileName().toString(), enonce, dessin, -1, -1));
                continue;
            }
            int n = Integer.parseInt(bonne.group(1));
            int m = Integer.parseInt(bonne.group(2));
            items.add(new ItemDeReseau(paquet.getFileName().toString(), enonce, dessin,
                    parLignes ? n : m, parLignes ? m : n));
        }
    }

    private static Map<String, String> dessinsDe(Path racine) {
        Map<String, String> dessins = new LinkedHashMap<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path repertoire = racine.resolve(module).resolve(ILLUSTRATIONS_DE_CONTENU);
            if (Files.isDirectory(repertoire)) {
                dessins.putAll(LIllustrationDeContenuResoutEtAucuneNeDortTest
                        .fichiersIndexesParNomDeBase(List.of(repertoire)));
            }
        }
        return dessins;
    }

    private static List<int[]> points(String dessin) {
        List<int[]> points = new ArrayList<>();
        Matcher trouve = POINT.matcher(dessin);
        while (trouve.find()) {
            points.add(new int[] {Integer.parseInt(trouve.group(1)), Integer.parseInt(trouve.group(2)),
                    Integer.parseInt(trouve.group(3))});
        }
        return points;
    }

    private static int ecartRegulier(TreeSet<Integer> positions) {
        Integer precedent = null;
        Integer ecart = null;
        for (int position : positions) {
            if (precedent != null) {
                int courant = position - precedent;
                if (ecart != null && ecart != courant) {
                    return -1;
                }
                ecart = courant;
            }
            precedent = position;
        }
        return ecart == null ? 0 : ecart;
    }

    /** Le juge, pur : ce que chaque item affirme contre ce que le dessin qu'il nomme trace. */
    static List<String> defauts(List<ItemDeReseau> items, Map<String, String> dessins) {
        List<String> defauts = new ArrayList<>();
        Map<String, int[]> lignesEtColonnesParDessin = new LinkedHashMap<>();
        Set<Integer> rayons = new TreeSet<>();
        Set<Integer> pas = new TreeSet<>();
        for (ItemDeReseau item : items) {
            String ou = item.paquet() + ", dessin '" + item.dessin() + "', item \"" + item.enonce() + "\"";
            if (item.lignes() < 0) {
                defauts.add(ou + " : ni lecture par lignes ni lecture par colonnes avec une bonne"
                        + " reponse \"n x m\" - ce garde-fou ne sait pas ce que l'item affirme");
                continue;
            }
            int[] affirme = {item.lignes(), item.colonnes()};
            int[] deja = lignesEtColonnesParDessin.putIfAbsent(item.dessin(), affirme);
            if (deja != null && (deja[0] != affirme[0] || deja[1] != affirme[1])) {
                defauts.add(ou + " : ce dessin est lu " + affirme[0] + " x " + affirme[1]
                        + " ici et " + deja[0] + " x " + deja[1] + " par un autre item");
            }
        }
        lignesEtColonnesParDessin.forEach((nom, affirme) -> {
            String source = dessins.get(nom);
            if (source == null) {
                defauts.add(nom + " : aucun fichier");
                return;
            }
            List<int[]> points = points(source);
            int lignes = affirme[0];
            int colonnes = affirme[1];
            if (lignes > MAXIMUM_PAR_AXE || colonnes > MAXIMUM_PAR_AXE) {
                defauts.add(nom + " : " + lignes + " lignes et " + colonnes + " colonnes, au plus "
                        + MAXIMUM_PAR_AXE + " de chaque (MUL-CE1-5)");
            }
            if (LIllustrationDeContenuResoutEtAucuneNeDortTest.formesRendues(source) != points.size()) {
                defauts.add(nom + " : une forme autre qu'un <circle> nu (un cercle par point, aucun"
                        + " attribut de plus, aucun chemin, aucun cadre, aucune ligne de grille)");
            }
            TreeSet<Integer> xs = new TreeSet<>();
            TreeSet<Integer> ys = new TreeSet<>();
            Set<String> positions = new TreeSet<>();
            for (int[] point : points) {
                xs.add(point[0]);
                ys.add(point[1]);
                positions.add(point[0] + "," + point[1]);
                rayons.add(point[2]);
            }
            if (xs.size() != colonnes || ys.size() != lignes || points.size() != lignes * colonnes
                    || positions.size() != points.size()) {
                defauts.add(nom + " : l'item affirme " + lignes + " lignes de " + colonnes
                        + " points, le dessin trace " + points.size() + " points sur " + ys.size()
                        + " lignes et " + xs.size() + " colonnes (rectangle complet attendu)");
            }
            int pasX = ecartRegulier(xs);
            int pasY = ecartRegulier(ys);
            if (pasX < 0 || pasY < 0 || (pasX != 0 && pasY != 0 && pasX != pasY)) {
                defauts.add(nom + " : le pas n'est pas regulier, ou differe entre lignes et colonnes"
                        + " (" + pasX + " et " + pasY + ")");
            }
            pas.add(Math.max(pasX, pasY));
            Matcher cadre = CADRE.matcher(source);
            if (!cadre.find() || Integer.parseInt(cadre.group(1)) != Integer.parseInt(cadre.group(3))
                    || Integer.parseInt(cadre.group(2)) != Integer.parseInt(cadre.group(4))
                    || Integer.parseInt(cadre.group(3)) != colonnes * Math.max(pasX, pasY)
                    || Integer.parseInt(cadre.group(4)) != lignes * Math.max(pasX, pasY)) {
                defauts.add(nom + " : le cadre de vue n'est pas colonnes x pas par lignes x pas, a"
                        + " l'echelle 1 pour 1");
            }
            if (source.replaceAll("(?s)<!--.*?-->", "").contains("fill=\"none\"")
                    || POINT.matcher(source).replaceAll("").contains("stroke-width")) {
                defauts.add(nom + " : un point n'est ni un contour ni un trait");
            }
        });
        if (rayons.size() > 1) {
            defauts.add("les points n'ont pas tous le meme rayon d'un dessin a l'autre : " + rayons);
        }
        if (pas.size() > 1) {
            defauts.add("le pas differe d'un dessin a l'autre : " + pas);
        }
        return defauts;
    }

    private static List<ItemDeReseau> recensement(Path racine) {
        return itemsDeReseau(AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                .repertoiresDeContenu(racine));
    }

    @Test
    void chaqueReseauServiMontreCeQueSesItemsAffirment() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(defauts(recensement(racine), dessinsDe(racine))).isEmpty();
    }

    @Test
    void leRecensementTrouveLesItemsIllustresDeLaLigne() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(recensement(racine))
                .as("ADR-0051 : sans item illustre sur la ligne, les clauses portent sur rien")
                .isNotEmpty();
    }

    @Test
    void unPointDeMoinsFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeReseau> items = recensement(racine);
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        String nom = items.get(0).dessin();
        dessins.put(nom, dessins.get(nom).replaceFirst("<circle [^>]*/>", ""));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains(nom));
    }

    @Test
    void unPointDUnAutreRayonFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeReseau> items = recensement(racine);
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        String nom = items.get(0).dessin();
        dessins.put(nom, dessins.get(nom).replaceFirst("r=\"5\"", "r=\"6\""));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains("rayon"));
    }

    @Test
    void unItemQuiAffirmeAutreChoseQueSonDessinFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeReseau> items = new ArrayList<>(recensement(racine));
        ItemDeReseau premier = items.get(0);
        items.set(0, new ItemDeReseau(premier.paquet(), premier.enonce(), premier.dessin(),
                premier.lignes() + 1, premier.colonnes()));

        assertThat(defauts(items, dessinsDe(racine))).anyMatch(defaut -> defaut.contains(premier.dessin()));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif commite, lu sur disque. Il traverse le balayage du
     * repertoire de contenu, la lecture de {@code lignesDeReferentiel} et la resolution du dessin.
     */
    @Test
    void leJeuFautifCommiteEstRefuse() {
        Path jeuFautif = Path.of("src", "test", "resources", "contreexemple",
                "LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest", "reseau-fautif");
        List<ItemDeReseau> items = itemsDeReseau(List.of(jeuFautif.resolve("contenu")));
        Map<String, String> dessins = LIllustrationDeContenuResoutEtAucuneNeDortTest
                .fichiersIndexesParNomDeBase(List.of(jeuFautif.resolve("static/illustrations")));

        assertThat(items).as("ADR-0051 : le recensement lit l'item du jeu fautif").hasSize(1);
        assertThat(defauts(items, dessins))
                .anyMatch(defaut -> defaut.contains("14 points"))
                .anyMatch(defaut -> defaut.contains("rayon"))
                .anyMatch(defaut -> defaut.contains("une forme autre qu'un <circle>"));
    }

    @Test
    void unReseauDeOnzeLignesFaitEchouerLeGardeFou() {
        List<ItemDeReseau> items = List.of(new ItemDeReseau("fictif.yaml", PAR_LIGNES, "reseau-fictif", 11, 2));
        StringBuilder points = new StringBuilder();
        for (int ligne = 0; ligne < 11; ligne++) {
            for (int colonne = 0; colonne < 2; colonne++) {
                points.append("<circle cx=\"").append(16 * colonne + 8).append("\" cy=\"")
                        .append(16 * ligne + 8).append("\" r=\"5\"/>");
            }
        }
        Map<String, String> dessins = Map.of("reseau-fictif",
                "<svg viewBox=\"0 0 32 176\" width=\"32\" height=\"176\" fill=\"#1c2733\">" + points + "</svg>");

        assertThatThrownBy(() -> assertThat(defauts(items, dessins)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("au plus 10");
    }
}
