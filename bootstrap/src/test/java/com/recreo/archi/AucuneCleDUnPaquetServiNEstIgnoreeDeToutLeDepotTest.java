package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0070 : une cle de schema qu'aucune source du depot ne nomme est une cle que personne ne lit.
 * Elle ne fait pas echouer le chargement - {@code QuestionContentSourceFactory} ignore sans un mot
 * toute cle qu'elle ne connait pas, dette nommee par ADR-0024 et jamais fermee - donc la CI reste
 * verte et l'enfant recoit un item ampute de ce que son auteur avait ecrit.
 *
 * <p><b>Les deux fautes que ce garde-fou attrape</b>, et ce sont les deux que l'ouverture d'une
 * forme d'item neuve rend probables : une cle <b>mal orthographiee</b> par rapport a celle que le
 * code lit, et une cle <b>ecrite avant que le champ existe</b> - le paquet est ecrit sur une
 * conception, la fabrique ne l'a pas encore rejointe, et rien ne le dit.
 *
 * <p><b>Perimetre derive, jamais enumere (ADR-0013 obligation 1).</b> Les modules viennent du pom
 * agregateur, par {@link LesGardeFousSontMisALEpreuveTest#modulesDeclares(Path)} - deux notions de
 * "quels modules existent" divergeraient un jour. Les paquets viennent du repertoire de contenu de
 * chaque module qui en porte un, jamais du catalogue : un paquet retire du catalogue mais laisse
 * sur disque reste juge, comme {@code ChaquePaquetPorteUnIndiceTest} l'a etabli en s05. Les cles
 * viennent de SnakeYAML, la bibliotheque avec laquelle le chargeur de production lit ces memes
 * fichiers, jamais d'une seconde lecture ecrite a la main.
 *
 * <p><b>Ce qui compte comme "lue" est un litteral de chaine, jamais une sous-chaine.</b> Le
 * criterion compare des chaines entieres : "graphi" n'est pas "graphie". C'est la faute mesuree en
 * s05 - une detection par sous-chaine qui passe sa propre epreuve et echoue dans le monde - et elle
 * est fermee par construction ici. Les commentaires sont retires avant l'extraction : une cle
 * nommee dans une Javadoc ou dans un ADR recopie en commentaire est precisement le cas de la cle
 * ecrite avant son code, et l'accepter viderait ce garde-fou de son objet.
 *
 * <p><b>Le perimetre des sources comprend les tests, et c'est mesure et non concede.</b> Trois cles
 * reellement servies aujourd'hui - "graphie" (cp-le-bon-son), "relationBonneReponse" et
 * "relationsDistracteurs" (cm1-mots-de-liaison) - sont des declarations d'auteur qu'aucune fabrique
 * ne lit et que des garde-fous de contenu relisent. Restreindre le balayage aux sources de
 * production les rendrait orphelines toutes les trois : le garde-fou refuserait trois paquets sains
 * au lieu d'attraper une cle morte.
 *
 * <p><b>Ce garde-fou se retire lui-meme du balayage des sources</b>, par son propre nom de fichier
 * et non par une liste, sur le patron de {@link LesGardeFousSontMisALEpreuveTest}. Il ecrit
 * necessairement quelques cles en litteraux - celles de l'enveloppe de schema, celle de sa clause
 * de non-vacuite - et se porter garant de ce qu'il juge rendrait ces cles-la vertes sans qu'aucune
 * fabrique ne les lise.
 *
 * <p><b>Ce que ce garde-fou ne prouve pas</b>, ecrit avant qu'on le lui reproche : qu'une cle citee
 * quelque part soit <b>lue au bon endroit</b>. Un litteral pose dans un test qui n'ouvre aucun
 * paquet le satisferait. Il tient la propriete faible - personne ne nomme cette cle - parce que
 * c'est celle qu'une machine peut tenir ; la propriete forte reste a la relecture.
 */
class AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest {

    /**
     * Ecrits sous leur forme a barre oblique et non par {@code Path.of("src", "main", ...)} :
     * c'est cette forme que {@link LesGardeFousSontMisALEpreuveTest} cherche pour recenser un
     * garde-fou de balayage. Un chemin compose composante par composante aurait fait sortir ce
     * fichier du recensement sans que personne ne l'ait decide.
     */
    private static final String CONTENU_DE_PRODUCTION = "src/main/resources/contenu";

    private static final List<String> RACINES_DE_SOURCES = List.of("src/main/java", "src/test/java");

    /** ADR-0013 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest");

    /**
     * Les deux blocs du schema dont les sous-cles sont du schema et non de la donnee. La cle
     * "relationsDistracteurs" porte, elle, une correspondance dont les cles sont des mots de
     * liaison ecrits par l'auteur : ce sont des valeurs, et les descendre serait juger le contenu.
     */
    private static final List<String> BLOCS_A_SOUS_CLES = List.of("descripteur", "parametres");

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Path> fichiers(Path repertoire, String extension) {
        if (!Files.isDirectory(repertoire)) {
            return List.of();
        }
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(extension))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les repertoires de contenu des modules declares, aucun nom de module ecrit ici. */
    static List<Path> repertoiresDeContenu(Path racine) {
        List<Path> repertoires = new ArrayList<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path contenu = racine.resolve(module).resolve(CONTENU_DE_PRODUCTION);
            if (Files.isDirectory(contenu)) {
                repertoires.add(contenu);
            }
        }
        return repertoires;
    }

    /** Les arbres de sources Java des modules declares, production et tests. */
    static List<Path> arbresDeSources(Path racine) {
        List<Path> arbres = new ArrayList<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            for (String sousArbre : RACINES_DE_SOURCES) {
                Path source = racine.resolve(module).resolve(sousArbre);
                if (Files.isDirectory(source)) {
                    arbres.add(source);
                }
            }
        }
        return arbres;
    }

    /**
     * Les cles de schema de chaque paquet trouve : les cles de la racine, celles de chaque item, et
     * celles des deux blocs a sous-cles. La valeur est le nom du fichier, pour que le message
     * nomme le paquet a corriger et pas seulement la cle.
     */
    static Map<String, String> clesDeSchema(List<Path> repertoiresDeContenu) {
        Map<String, String> cles = new LinkedHashMap<>();
        for (Path repertoire : repertoiresDeContenu) {
            for (Path paquet : fichiers(repertoire, ".yaml")) {
                String nom = paquet.getFileName().toString();
                Object charge = new Yaml().load(lire(paquet));
                if (!(charge instanceof Map<?, ?> corps)) {
                    throw new IllegalStateException(nom + " : la racine du fichier doit etre une correspondance");
                }
                corps.keySet().forEach(cle -> cles.putIfAbsent(String.valueOf(cle), nom));
                if (corps.get("items") instanceof List<?> items) {
                    for (Object item : items) {
                        if (item instanceof Map<?, ?> champs) {
                            champs.keySet().forEach(cle -> cles.putIfAbsent(String.valueOf(cle), nom));
                        }
                    }
                }
                for (String bloc : BLOCS_A_SOUS_CLES) {
                    if (corps.get(bloc) instanceof Map<?, ?> sousCles) {
                        sousCles.keySet().forEach(cle -> cles.putIfAbsent(String.valueOf(cle), nom));
                    }
                }
            }
        }
        return cles;
    }

    /**
     * Les litteraux de chaine ecrits dans les sources, commentaires exclus. L'analyse suit les
     * quatre etats d'un fichier Java - code, litteral de chaine, litteral de caractere, commentaire
     * de ligne ou de bloc - plutot que de decouper a l'expression reguliere : une adresse "http://"
     * dans une chaine ferait disparaitre la fin de sa ligne, et le garde-fou rougirait sur une cle
     * saine. Le bloc de texte ({@code """}) n'est pas traite : le depot n'en contient aucun, mesure
     * le 2026-08-30, et le jour ou il en portera un, cette methode devra l'apprendre.
     */
    static Set<String> litterauxDeChaine(List<Path> arbresDeSources) {
        String soiMeme = AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest.class.getSimpleName() + ".java";
        Set<String> litteraux = new LinkedHashSet<>();
        for (Path arbre : arbresDeSources) {
            for (Path source : fichiers(arbre, ".java")) {
                if (source.getFileName().toString().equals(soiMeme)) {
                    continue;
                }
                litterauxDe(lire(source), litteraux);
            }
        }
        return litteraux;
    }

    private static void litterauxDe(String source, Set<String> litteraux) {
        int position = 0;
        while (position < source.length()) {
            char caractere = source.charAt(position);
            if (caractere == '"') {
                StringBuilder litteral = new StringBuilder();
                position++;
                while (position < source.length() && source.charAt(position) != '"') {
                    if (source.charAt(position) == '\\' && position + 1 < source.length()) {
                        litteral.append(source.charAt(position + 1));
                        position += 2;
                    } else {
                        litteral.append(source.charAt(position));
                        position++;
                    }
                }
                litteraux.add(litteral.toString());
                position++;
            } else if (caractere == '\'') {
                position++;
                while (position < source.length() && source.charAt(position) != '\'') {
                    position += source.charAt(position) == '\\' ? 2 : 1;
                }
                position++;
            } else if (caractere == '/' && position + 1 < source.length()
                    && source.charAt(position + 1) == '/') {
                int fin = source.indexOf('\n', position);
                position = fin < 0 ? source.length() : fin;
            } else if (caractere == '/' && position + 1 < source.length()
                    && source.charAt(position + 1) == '*') {
                int fin = source.indexOf("*/", position + 2);
                position = fin < 0 ? source.length() : fin + 2;
            } else {
                position++;
            }
        }
    }

    /**
     * ADR-0051 : un garde-fou qui n'examine aucun de ses objets le declare. Sans cette clause, un
     * repertoire de contenu deplace rendrait ce test vert sur zero paquet.
     */
    static void verifier(Map<String, String> clesDeSchema, Set<String> litteraux) {
        if (clesDeSchema.isEmpty()) {
            throw new AssertionError("ADR-0070 : aucune cle de schema examinee - le perimetre de ce"
                    + " garde-fou est vide, il ne garde rien");
        }
        List<String> orphelines = clesDeSchema.entrySet().stream()
                .filter(entree -> !litteraux.contains(entree.getKey()))
                .map(entree -> entree.getKey() + " (" + entree.getValue() + ")")
                .toList();
        if (!orphelines.isEmpty()) {
            throw new AssertionError("ADR-0070 : ces cles de schema sont ecrites dans un paquet servi"
                    + " et ne sont nommees par aucune source du depot, donc lues par personne. Soit"
                    + " la fabrique du type de paquet les lit, soit un garde-fou de contenu les"
                    + " relit, soit elles sortent du paquet : " + orphelines);
        }
    }

    @Test
    void aucuneCleDeSchemaDUnPaquetServiNEstIgnoreeDeToutLeDepot() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> cles = clesDeSchema(repertoiresDeContenu(racine));

        assertThat(cles)
                .as("ADR-0013 : ce garde-fou ne prouve rien s'il ne trouve aucune cle de schema")
                .isNotEmpty();

        verifier(cles, litterauxDeChaine(arbresDeSources(racine)));
    }

    /**
     * ADR-0013 obligation 3, premiere morsure, <b>sur le materiau reel</b> : les paquets servis et
     * les sources du depot, plus une cle injectee en memoire. Rien n'est ecrit sur disque, donc rien
     * ne pourrit le jour ou la cle injectee deviendrait un vrai champ - son nom n'a aucune chance
     * d'etre lu un jour.
     */
    @Test
    void uneCleQueNulNeNommeDansUnPaquetReelFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        Map<String, String> avecUneCleMorte = new LinkedHashMap<>(clesDeSchema(repertoiresDeContenu(racine)));
        avecUneCleMorte.put("cleQueNulNeLit", "mutation-en-memoire");

        assertThatThrownBy(() -> verifier(avecUneCleMorte, litterauxDeChaine(arbresDeSources(racine))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cleQueNulNeLit")
                .hasMessageContaining("mutation-en-memoire");
    }

    /**
     * ADR-0013 obligation 3, seconde morsure : la cle est nommee, entre guillemets, dans la Javadoc
     * de la seule source du jeu fautif, et nulle part ailleurs. C'est la forme exacte d'une cle
     * ecrite d'apres une conception que le code n'a pas encore rejointe. Le jeu fautif nomme en
     * litteraux toutes les autres cles de son paquet : le message ne doit designer qu'elle.
     */
    @Test
    void uneCleNommeeSeulementDansUnCommentaireFaitEchouerLeGardeFou() {
        Path jeuFautif = CONTRE_EXEMPLE.resolve("cle-nommee-seulement-dans-un-commentaire");
        Map<String, String> cles = clesDeSchema(List.of(jeuFautif.resolve("contenu")));
        Set<String> litteraux = litterauxDeChaine(List.of(jeuFautif.resolve("src/main/java")));

        assertThatThrownBy(() -> verifier(cles, litteraux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("modeDeComparaisonAnnonce")
                .hasMessageNotContaining("bonneReponse");
    }

    /** ADR-0051 : la clause de non-vacuite mord, elle aussi. */
    @Test
    void unPerimetreSansAucunPaquetFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(Map.of(), Set.of("enonce")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("perimetre");
    }
}
