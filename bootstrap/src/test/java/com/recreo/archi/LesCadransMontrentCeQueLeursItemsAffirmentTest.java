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
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * HOR-1 (nadia-prof, arbitrage prealable de s25, palier 1, section 2.1) et les conditions de
 * l'horloge de CE1 (arbitrage de s57, palier 5, section 3.5) : <b>la position des aiguilles est
 * celle de l'heure que l'item affirme, jamais posee "a peu pres".</b> Grande aiguille a 6 degres par
 * minute, petite aiguille a 30 degres par heure plus 0,5 degre par minute.
 *
 * <p><b>Ce que ce garde-fou ajoute.</b> {@code LIllustrationDeContenuResoutEtAucuneNeDortTest} juge
 * qu'un identifiant resout et qu'aucun fichier ne dort ; le garde de geometrie juge le cadre et le
 * poids. Aucun ne regarde ce que le cadran montre. Ici, pour chaque item illustre d'un paquet qui
 * sert {@link #LIGNE_DE_L_HORLOGE} : les angles des deux aiguilles du dessin sont ceux de la bonne
 * reponse ; la grande aiguille est sur 12, 3, 6 ou 9 ; les deux aiguilles different par la longueur
 * (HOR-3) ; les douze nombres sont traces (HOR-2), compte fait sur les glyphes instancies ; aucune
 * proposition de l'item ne lit deux fois la meme position.</p>
 *
 * <p>Sur le paquet entier : au moins douze items, jamais deux fois le meme cadran, au moins trois
 * items par forme (heure pleine, demi-heure, quart d'heure a 3, quart d'heure a 9), aucune heure
 * plus de deux fois (HOR-6 transposee).</p>
 *
 * <p><b>Perimetre derive</b> (ADR-0013 obligation 1) : un code de ligne du referentiel, jamais un
 * nom de paquet ni un prefixe de fichier (ADR-0077). Un paquet de la ligne qui ne declare aucune
 * illustration (le paquet de dates) n'entre pas dans le recensement.</p>
 *
 * <p><b>Ce qu'il ne juge pas</b> : que la petite aiguille se lise entre deux nombres a 176 px, que
 * les chiffres traces se lisent comme des chiffres, et que le texte alternatif ne livre ni l'heure
 * ni la place d'une aiguille (CON-5). C'est l'ecran et nadia-prof.</p>
 */
class LesCadransMontrentCeQueLeursItemsAffirmentTest {

    private static final String LIGNE_DE_L_HORLOGE = "MAT-CE1-11";

    private static final String ILLUSTRATIONS_DE_CONTENU =
            "src/main/resources/static/illustrations";

    /** Arbitrage de s57, palier 5, section 3.5 : au moins douze items illustres. */
    private static final int MINIMUM_D_ITEMS = 12;

    /** Au moins trois items de chacune des quatre formes : 0, 15, 30 et 45 minutes. */
    private static final int MINIMUM_PAR_FORME = 3;

    /** HOR-6 transposee : aucune heure plus de deux fois. */
    private static final int MAXIMUM_PAR_HEURE = 2;

    private static final Set<Integer> MINUTES_DES_FORMES = Set.of(0, 15, 30, 45);

    private static final Set<Integer> ANGLES_DE_LA_GRANDE_AIGUILLE = Set.of(0, 90, 180, 270);

    private static final Pattern LECTURE = Pattern.compile("^(\\d{1,2}) h(?: (\\d{2}))?$");

    private static final Pattern AIGUILLE = Pattern.compile("<line x1=\"(\\d+)\" y1=\"(\\d+)\""
            + " x2=\"(\\d+)\" y2=\"(\\d+)\" stroke-width=\"[\\d.]+\""
            + " transform=\"rotate\\(([\\d.]+) (\\d+) (\\d+)\\)\"/>");

    private static final Pattern NOMBRE_TRACE = Pattern.compile("<use xlink:href=\"#g(\\d)\"");

    /** Un item illustre d'un paquet de la ligne, avec ce qu'il affirme et ce qu'il propose. */
    record ItemDeCadran(String paquet, String enonce, String dessin, String bonneReponse,
                        List<String> distracteurs) {
    }

    /** Les deux aiguilles lues dans le dessin : angle en degres depuis midi, longueur. */
    record Aiguille(double angle, double longueur) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier).replace("\r\n", "\n");
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    static List<ItemDeCadran> itemsDeCadran(List<Path> repertoiresDeContenu) {
        List<ItemDeCadran> items = new ArrayList<>();
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

    private static void ajouter(Path paquet, List<ItemDeCadran> items) {
        if (!(new Yaml().load(lire(paquet)) instanceof Map<?, ?> corps)
                || !(corps.get("lignesDeReferentiel") instanceof List<?> lignes)
                || lignes.stream().noneMatch(l -> LIGNE_DE_L_HORLOGE.equals(String.valueOf(l)))
                || !(corps.get("items") instanceof List<?> declares)) {
            return;
        }
        for (Object declare : declares) {
            if (!(declare instanceof Map<?, ?> champs) || !(champs.get("illustration") instanceof String dessin)) {
                continue;
            }
            List<String> distracteurs = new ArrayList<>();
            if (champs.get("distracteurs") instanceof List<?> propositions) {
                propositions.forEach(proposition -> distracteurs.add(String.valueOf(proposition)));
            }
            items.add(new ItemDeCadran(paquet.getFileName().toString(),
                    String.valueOf(champs.get("enonce")), dessin,
                    String.valueOf(champs.get("bonneReponse")), distracteurs));
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

    /**
     * La position d'une lecture, en minutes depuis midi sur un cadran de douze heures, ou -1 si le
     * texte n'est pas une lecture d'heure. "15 h" et "3 h" sont la meme position.
     */
    static int position(String lecture) {
        Matcher trouve = LECTURE.matcher(lecture);
        if (!trouve.matches()) {
            return -1;
        }
        int heures = Integer.parseInt(trouve.group(1)) % 12;
        int minutes = trouve.group(2) == null ? 0 : Integer.parseInt(trouve.group(2));
        return minutes > 59 ? -1 : heures * 60 + minutes;
    }

    /** Les aiguilles du dessin, la plus courte en premier, ou {@code null} si ce n'est pas deux aiguilles droites. */
    static Aiguille[] aiguilles(String dessin) {
        List<Aiguille> trouvees = new ArrayList<>();
        Matcher trouve = AIGUILLE.matcher(dessin);
        while (trouve.find()) {
            int x1 = Integer.parseInt(trouve.group(1));
            int y1 = Integer.parseInt(trouve.group(2));
            int x2 = Integer.parseInt(trouve.group(3));
            int y2 = Integer.parseInt(trouve.group(4));
            boolean verticaleVersMidiAutourDeSonPied = x1 == x2 && y2 < y1
                    && Integer.parseInt(trouve.group(6)) == x1 && Integer.parseInt(trouve.group(7)) == y1;
            if (!verticaleVersMidiAutourDeSonPied) {
                return null;
            }
            trouvees.add(new Aiguille(Double.parseDouble(trouve.group(5)), y1 - y2));
        }
        if (trouvees.size() != 2) {
            return null;
        }
        Aiguille premiere = trouvees.get(0);
        Aiguille seconde = trouvees.get(1);
        return premiere.longueur() <= seconde.longueur()
                ? new Aiguille[] {premiere, seconde} : new Aiguille[] {seconde, premiere};
    }

    /** Les chiffres que douze nombres, de 1 a 12, demandent au total : un glyphe par chiffre ecrit. */
    private static Map<Integer, Integer> chiffresAttendus() {
        Map<Integer, Integer> attendus = new TreeMap<>();
        for (int nombre = 1; nombre <= 12; nombre++) {
            for (char chiffre : String.valueOf(nombre).toCharArray()) {
                attendus.merge(chiffre - '0', 1, Integer::sum);
            }
        }
        return attendus;
    }

    private static Map<Integer, Integer> chiffresTraces(String dessin) {
        Map<Integer, Integer> traces = new TreeMap<>();
        Matcher trouve = NOMBRE_TRACE.matcher(dessin);
        while (trouve.find()) {
            traces.merge(Integer.parseInt(trouve.group(1)), 1, Integer::sum);
        }
        return traces;
    }

    /** Le juge, pur : ce que chaque item affirme contre ce que le dessin qu'il nomme trace. */
    static List<String> defauts(List<ItemDeCadran> items, Map<String, String> dessins) {
        List<String> defauts = new ArrayList<>();
        Map<Integer, Integer> parForme = new TreeMap<>();
        Map<Integer, Integer> parHeure = new TreeMap<>();
        Set<String> dessinsDejaServis = new HashSet<>();
        Set<String> cadransDejaServis = new HashSet<>();
        for (ItemDeCadran item : items) {
            String ou = item.paquet() + ", dessin '" + item.dessin() + "', bonne reponse \""
                    + item.bonneReponse() + "\"";
            if (!dessinsDejaServis.add(item.dessin())) {
                defauts.add(ou + " : ce dessin est deja montre par un autre item (jamais deux"
                        + " fois le meme cadran)");
            }
            lecturesDe(item, ou, defauts);
            int position = position(item.bonneReponse());
            String source = dessins.get(item.dessin());
            if (source == null) {
                defauts.add(ou + " : aucun fichier");
                continue;
            }
            if (position < 0) {
                continue;
            }
            parForme.merge(position % 60, 1, Integer::sum);
            parHeure.merge(position / 60, 1, Integer::sum);
            Aiguille[] aiguilles = aiguilles(source);
            if (aiguilles == null) {
                defauts.add(ou + " : le dessin ne trace pas exactement deux aiguilles, chacune un"
                        + " <line> droit vers midi tourne autour de son pied (HOR-1)");
                continue;
            }
            Aiguille petite = aiguilles[0];
            Aiguille grande = aiguilles[1];
            if (petite.longueur() == grande.longueur()) {
                defauts.add(ou + " : les deux aiguilles ont la meme longueur " + petite.longueur()
                        + " (HOR-3 : elles se distinguent par la longueur)");
                continue;
            }
            double petiteAttendue = position / 2.0;
            double grandeAttendue = (position % 60) * 6.0;
            if (petite.angle() != petiteAttendue) {
                defauts.add(ou + " : la petite aiguille est a " + petite.angle() + " degres, la bonne"
                        + " reponse la veut a " + petiteAttendue + " (30 x heures + 0,5 x minutes)");
            }
            if (grande.angle() != grandeAttendue) {
                defauts.add(ou + " : la grande aiguille est a " + grande.angle() + " degres, la bonne"
                        + " reponse la veut a " + grandeAttendue + " (6 x minutes)");
            }
            if (!ANGLES_DE_LA_GRANDE_AIGUILLE.contains((int) grande.angle())) {
                defauts.add(ou + " : la grande aiguille est a " + grande.angle() + " degres, sur 12,"
                        + " 3, 6 ou 9 seulement");
            }
            if (!cadransDejaServis.add(petite.angle() + "/" + grande.angle())) {
                defauts.add(ou + " : un autre item montre deja ce cadran (" + petite.angle() + " et "
                        + grande.angle() + " degres)");
            }
            if (!chiffresTraces(source).equals(chiffresAttendus())) {
                defauts.add(ou + " : les douze nombres ne sont pas tous traces, glyphes instancies "
                        + chiffresTraces(source) + " au lieu de " + chiffresAttendus() + " (HOR-2)");
            }
        }
        if (items.size() < MINIMUM_D_ITEMS) {
            defauts.add(items.size() + " items illustres, au moins " + MINIMUM_D_ITEMS + " attendus");
        }
        for (int minutes : MINUTES_DES_FORMES) {
            if (parForme.getOrDefault(minutes, 0) < MINIMUM_PAR_FORME) {
                defauts.add("la forme a " + minutes + " minutes sort " + parForme.getOrDefault(minutes, 0)
                        + " fois, au moins " + MINIMUM_PAR_FORME + " attendues");
            }
        }
        parForme.keySet().stream().filter(minutes -> !MINUTES_DES_FORMES.contains(minutes))
                .forEach(minutes -> defauts.add("une bonne reponse lit " + minutes
                        + " minutes, hors des quatre formes servies"));
        parHeure.forEach((heure, fois) -> {
            if (fois > MAXIMUM_PAR_HEURE) {
                defauts.add("l'heure " + (heure == 0 ? 12 : heure) + " sort " + fois + " fois, au plus "
                        + MAXIMUM_PAR_HEURE + " (HOR-6 transposee)");
            }
        });
        return defauts;
    }

    /** Aucune proposition ne lit deux fois la meme position, la bonne reponse comprise. */
    private static void lecturesDe(ItemDeCadran item, String ou, List<String> defauts) {
        List<String> propositions = new ArrayList<>();
        propositions.add(item.bonneReponse());
        propositions.addAll(item.distracteurs());
        Set<Integer> positions = new HashSet<>();
        for (String proposition : propositions) {
            int position = position(proposition);
            if (position < 0) {
                defauts.add(ou + " : la proposition \"" + proposition + "\" n'est pas une lecture"
                        + " d'heure que ce garde-fou sait lire");
            } else if (!positions.add(position)) {
                defauts.add(ou + " : deux propositions lisent la meme position, dont \"" + proposition
                        + "\" (jamais 3 h et 15 h dans le meme item)");
            }
        }
    }

    private static List<ItemDeCadran> recensement(Path racine) {
        return itemsDeCadran(AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest
                .repertoiresDeContenu(racine));
    }

    @Test
    void chaqueCadranServiMontreCeQueSesItemsAffirment() {
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
    void uneAiguilleDeplaceeFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = recensement(racine);
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        String nom = items.get(0).dessin();
        dessins.put(nom, dessins.get(nom).replaceFirst("rotate\\(([\\d.]+) 88 88\\)", "rotate(1 88 88)"));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains(nom));
    }

    @Test
    void deuxAiguillesDeLaMemeLongueurFontEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = recensement(racine);
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        String nom = items.get(0).dessin();
        dessins.put(nom, dessins.get(nom).replace("y2=\"60\"", "y2=\"46\""));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains("meme longueur"));
    }

    @Test
    void unNombreDeMoinsFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = recensement(racine);
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        String nom = items.get(0).dessin();
        dessins.put(nom, dessins.get(nom).replaceFirst("<use xlink:href=\"#g7\"[^>]*/>", ""));

        assertThat(defauts(items, dessins)).anyMatch(defaut -> defaut.contains("douze nombres"));
    }

    @Test
    void unItemQuiAffirmeAutreChoseQueSonDessinFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = new ArrayList<>(recensement(racine));
        ItemDeCadran premier = items.get(0);
        items.set(0, new ItemDeCadran(premier.paquet(), premier.enonce(), premier.dessin(),
                "12 h 30", premier.distracteurs()));

        assertThat(defauts(items, dessinsDe(racine))).anyMatch(defaut -> defaut.contains(premier.dessin()));
    }

    @Test
    void deuxItemsSurLeMemeDessinFontEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = new ArrayList<>(recensement(racine));
        ItemDeCadran premier = items.get(0);
        ItemDeCadran second = items.get(1);
        items.set(1, new ItemDeCadran(second.paquet(), second.enonce(), premier.dessin(),
                premier.bonneReponse(), premier.distracteurs()));

        assertThat(defauts(items, dessinsDe(racine))).anyMatch(defaut -> defaut.contains("deja montre"));
    }

    @Test
    void uneHeureQuiSortTroisFoisFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = recensement(racine);
        Map<String, String> dessins = new LinkedHashMap<>(dessinsDe(racine));
        ItemDeCadran modele = items.get(0);
        List<ItemDeCadran> avecTrois = new ArrayList<>(items);
        for (int rang = 0; rang < 3; rang++) {
            String nom = "cadran-fictif-" + rang;
            dessins.put(nom, dessins.get(modele.dessin()));
            avecTrois.add(new ItemDeCadran("fictif.yaml", modele.enonce(), nom, modele.bonneReponse(),
                    modele.distracteurs()));
        }

        assertThat(defauts(avecTrois, dessins)).anyMatch(defaut -> defaut.contains("HOR-6"));
    }

    @Test
    void deuxPropositionsQuiLisentLaMemePositionFontEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<ItemDeCadran> items = new ArrayList<>(recensement(racine));
        ItemDeCadran premier = items.get(0);
        items.set(0, new ItemDeCadran(premier.paquet(), premier.enonce(), premier.dessin(),
                "3 h", List.of("15 h", "4 h")));

        assertThat(defauts(items, dessinsDe(racine))).anyMatch(defaut -> defaut.contains("meme position"));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif commite, lu sur disque. Il traverse le balayage du
     * repertoire de contenu, la lecture de {@code lignesDeReferentiel} et la resolution du dessin.
     */
    @Test
    void leJeuFautifCommiteEstRefuse() {
        Path jeuFautif = Path.of("src", "test", "resources", "contreexemple",
                "LesCadransMontrentCeQueLeursItemsAffirmentTest", "cadran-fautif");
        List<ItemDeCadran> items = itemsDeCadran(List.of(jeuFautif.resolve("contenu")));
        Map<String, String> dessins = LIllustrationDeContenuResoutEtAucuneNeDortTest
                .fichiersIndexesParNomDeBase(List.of(jeuFautif.resolve("static/illustrations")));

        assertThat(items).as("ADR-0051 : le recensement lit l'item du jeu fautif").hasSize(1);
        assertThatThrownBy(() -> assertThat(defauts(items, dessins)).isEmpty())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("la petite aiguille est a 90.0 degres")
                .hasMessageContaining("la grande aiguille est a 0.0 degres")
                .hasMessageContaining("meme position")
                .hasMessageContaining("douze nombres");
    }
}
