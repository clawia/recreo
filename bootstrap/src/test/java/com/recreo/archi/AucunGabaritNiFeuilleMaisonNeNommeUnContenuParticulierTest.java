package com.recreo.archi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
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
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0094, decision 6 : <b>aucun gabarit, aucune regle de la feuille maison, aucun script maison et
 * aucune source de production ne nomme litteralement un jeu, un paquet ou une illustration du
 * catalogue.</b> C'est la seule chose que cet ADR interdise - ses quatre relais transportent une
 * valeur, ils n'empechent personne de rien.
 *
 * <p><b>Ce que ce garde-fou interdit reellement</b>, c'est-a-dire ce qu'il refuserait a quelqu'un
 * qui essaierait de mal faire, et non ce qu'il constate :
 * {@code th:if="${illustration == 'plan-classe'}"} dans le fragment generique de designation ;
 * {@code th:classappend="${jeuId == 'designation-plan-de-la-classe'} ? ... "} ;
 * {@code .illustration-plan-classe { ... }} dans la feuille maison ;
 * {@code if (jeuId.equals("designation-plan-de-la-classe"))} dans un controleur, qui est le meme
 * defaut une couche plus haut. Une mecanique generique qui connait un contenu particulier n'est plus
 * generique : c'est un jeu deguise en mecanique, et c'est le principe directeur du produit qui tombe.
 *
 * <p><b>Ce qu'il ne peut pas interdire, et c'est ecrit</b> : un contournement qui n'emploie pas
 * l'identifiant - un paquet reconnu par son nombre de zones, par son texte alternatif, par son
 * niveau. Le perimetre est la <b>forme litterale</b> du couplage, pas toutes ses formes possibles.
 * Il ferme celle qui se presente, qui est aussi la seule qu'on ecrit sans y penser.
 *
 * <p><b>Perimetre derive, jamais enumere (ADR-0013 obligation 1).</b> Les modules viennent du pom
 * agregateur, par {@link LesGardeFousSontMisALEpreuveTest#modulesDeclares(Path)} - deux notions de
 * "quels modules existent" divergeraient un jour. Les identifiants juges sont lus sur le contenu
 * servi lui-meme, par SnakeYAML, la bibliotheque avec laquelle le chargeur de production lit ces
 * memes fichiers : l'{@code id} et le {@code paquet} de chaque entree de catalogue, l'{@code id} de
 * chaque paquet servi, la valeur {@code illustration} de chaque item. Aucun identifiant n'est ecrit
 * dans ce fichier, pas meme dans une morsure - le jour ou un paquet est renomme, ce garde-fou juge
 * le nom neuf sans qu'on l'ait touche.
 *
 * <p><b>Le perimetre juge est toute la production, pas seulement {@code adapter-web}.</b> ADR-0094
 * ecrivait les {@code .java} de {@code adapter-web} ; la mesure du 2026-09-21 (amendement du meme
 * jour, section <i>Regle executable</i>) montre que l'etendre a la production des cinq modules et
 * aux scripts maison est <b>gratuit</b> - 0 occurrence hors commentaire sur les 69 fichiers - et
 * strictement plus fort : un identifiant de jeu cable dans {@code domain} est le meme defaut, plus
 * profond. La feuille tierce et le JavaScript tiers ({@code static/vendor/}) sont hors perimetre :
 * ils ne sont pas ecrits ici, et ADR-0046 les garde autrement.
 *
 * <p><b>Les commentaires sont exclus, et ce n'est pas une commodite : c'est une mesure.</b> Sur
 * l'arbre du 2026-09-21, 175 identifiants derives, 69 fichiers dans le perimetre, <b>12 occurrences
 * litterales - et les douze sont dans des commentaires</b>, toutes des citations de fiches de
 * conformite ou de paquets d'exemple. Hors commentaire : 0. Un juge naif par sous-chaine serait donc
 * rouge a sa premiere execution, pour douze motifs tous legitimes - c'est exactement la lecon de s05,
 * le {@code !important} cherche par sous-chaine dans un nom de variable de la bibliotheque, et elle
 * est payee ici en mesurant sur le materiau reel avant d'ecrire, plutot qu'en fabriquant une fixture
 * a ma main. Le gabarit est donc analyse par Jsoup et seuls ses noeuds de texte, ses noms
 * d'attributs et ses valeurs d'attributs sont juges ; la feuille, le script et le Java sont juges
 * commentaires retires.
 *
 * <p><b>Ce qu'un commentaire exclu coute</b>, ecrit avant qu'on le reproche : un identifiant cache
 * dans un commentaire n'est pas juge - et il n'a pas a l'etre, un commentaire ne couple rien. Ce qui
 * n'est pas juge non plus, faute de pouvoir l'etre : le meme identifiant assemble a l'execution
 * ({@code "plan-" + "classe"}). Le juge lit du texte, il n'evalue pas d'expression.
 */
class AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest {

    /**
     * Ecrits sous leur forme a barre oblique et non par {@code Path.of("src", "main", ...)} : c'est
     * cette forme que {@link LesGardeFousSontMisALEpreuveTest} cherche pour recenser un garde-fou de
     * balayage. Un chemin compose composante par composante aurait fait sortir ce fichier du
     * recensement sans que personne ne l'ait decide.
     */
    private static final String RESSOURCES_DE_PRODUCTION = "src/main/resources";

    private static final String SOURCES_DE_PRODUCTION = "src/main/java";

    /** Les trois sous-arbres de ressources qui sont ecrits ici, par opposition a static/vendor/. */
    private static final String GABARITS = "templates";
    private static final String FEUILLES_MAISON = "static/css";
    private static final String SCRIPTS_MAISON = "static/js";

    /** Les cles par lesquelles le contenu servi se nomme lui-meme (ADR-0073, ADR-0080). */
    private static final String CLE_JEUX = "jeux";
    private static final String CLE_ID = "id";
    private static final String CLE_PAQUET = "paquet";
    private static final String CLE_ITEMS = "items";
    private static final String CLE_ILLUSTRATION = "illustration";

    /**
     * ADR-0106 : la donnee de matiere se reconnait a sa cle, comme le catalogue. Le segment d'adresse
     * n'y est pas juge, et c'est mesure le 2026-10-11 sur la production des cinq modules : "francais"
     * figure dans 5 fichiers et "sport" dans 10 ("transport"), en prose comme dans des mots, la faute
     * de s05 si on les jugeait par sous-chaine.
     */
    private static final String CLE_MATIERES = "matieres";
    private static final List<String> CHAMPS_DE_MATIERE_JUGES =
            List.of("matiere", "libelle", "pictogramme", "teinteFond", "teinteContour");

    /** ADR-0013 obligation 3 : le jeu fautif commite de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest");

    /** Ce qu'est un identifiant de contenu : sa valeur, et de quoi il est le nom. */
    enum Genre {
        JEU_DU_CATALOGUE("identifiant de jeu du catalogue"),
        PAQUET_D_UNE_ENTREE("paquet d'une entree de catalogue"),
        PAQUET_SERVI("identifiant d'un paquet servi"),
        ILLUSTRATION_D_ITEM("illustration declaree par un item"),
        DONNEE_DE_MATIERE("valeur de la donnee de matiere, ADR-0106");

        private final String libelle;

        Genre(String libelle) {
            this.libelle = libelle;
        }
    }

    record IdentifiantDeContenu(String valeur, Genre genre, String fichier) {

        String origine() {
            return genre.libelle + ", " + fichier;
        }
    }

    /** Ce qui distingue une nature de source : la maniere dont on lui retire ses commentaires. */
    enum Nature { GABARIT, FEUILLE, SCRIPT, JAVA }

    record SourceJugee(String chemin, Nature nature, String contenu) {

        SourceJugee avec(String ajout) {
            return new SourceJugee(chemin, nature, contenu + ajout);
        }
    }

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

    // --- Les identifiants juges, lus sur le contenu servi ---

    /**
     * L'{@code id} et le {@code paquet} de chaque entree de catalogue, l'{@code id} de chaque paquet,
     * et la valeur {@code illustration} de chaque item. Le catalogue se reconnait a sa forme - une
     * cle {@code jeux} portant une liste - et non a son nom de fichier : le jour ou il demenage,
     * ce garde-fou le suit au lieu de juger sur zero identifiant de jeu.
     */
    static List<IdentifiantDeContenu> identifiants(List<Path> repertoiresDeRessources) {
        List<IdentifiantDeContenu> identifiants = new ArrayList<>();
        for (Path repertoire : repertoiresDeRessources) {
            for (Path fichier : fichiers(repertoire, ".yaml")) {
                String nom = fichier.getFileName().toString();
                if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)) {
                    continue;
                }
                if (corps.get(CLE_JEUX) instanceof List<?> jeux) {
                    for (Object jeu : jeux) {
                        if (jeu instanceof Map<?, ?> entree) {
                            ajouter(identifiants, entree.get(CLE_ID), Genre.JEU_DU_CATALOGUE, nom);
                            ajouter(identifiants, entree.get(CLE_PAQUET), Genre.PAQUET_D_UNE_ENTREE, nom);
                        }
                    }
                    continue;
                }
                if (corps.get(CLE_MATIERES) instanceof List<?> lignes) {
                    for (Object ligne : lignes) {
                        if (ligne instanceof Map<?, ?> champs) {
                            CHAMPS_DE_MATIERE_JUGES.forEach(champ ->
                                    ajouter(identifiants, champs.get(champ), Genre.DONNEE_DE_MATIERE, nom));
                        }
                    }
                    continue;
                }
                ajouter(identifiants, corps.get(CLE_ID), Genre.PAQUET_SERVI, nom);
                if (corps.get(CLE_ITEMS) instanceof List<?> items) {
                    for (Object item : items) {
                        if (item instanceof Map<?, ?> champs) {
                            ajouter(identifiants, champs.get(CLE_ILLUSTRATION), Genre.ILLUSTRATION_D_ITEM, nom);
                        }
                    }
                }
            }
        }
        return identifiants;
    }

    private static void ajouter(List<IdentifiantDeContenu> identifiants, Object valeur, Genre genre,
                                String fichier) {
        if (valeur instanceof String texte && !texte.isBlank()
                && identifiants.stream().noneMatch(connu -> connu.valeur().equals(texte))) {
            identifiants.add(new IdentifiantDeContenu(texte, genre, fichier));
        }
    }

    // --- Le materiau juge ---

    /** Les gabarits, les feuilles maison et les scripts maison d'une racine de ressources. */
    static List<SourceJugee> sourcesDeRessources(List<Path> repertoiresDeRessources) {
        List<SourceJugee> sources = new ArrayList<>();
        for (Path racine : repertoiresDeRessources) {
            for (Path gabarit : fichiers(racine.resolve(GABARITS), ".html")) {
                sources.add(new SourceJugee(gabarit.toString(), Nature.GABARIT, lire(gabarit)));
            }
            for (Path feuille : fichiers(racine.resolve(FEUILLES_MAISON), ".css")) {
                sources.add(new SourceJugee(feuille.toString(), Nature.FEUILLE, lire(feuille)));
            }
            for (Path script : fichiers(racine.resolve(SCRIPTS_MAISON), ".js")) {
                sources.add(new SourceJugee(script.toString(), Nature.SCRIPT, lire(script)));
            }
        }
        return sources;
    }

    static List<SourceJugee> sourcesJava(List<Path> arbresDeSources) {
        List<SourceJugee> sources = new ArrayList<>();
        for (Path arbre : arbresDeSources) {
            for (Path source : fichiers(arbre, ".java")) {
                sources.add(new SourceJugee(source.toString(), Nature.JAVA, lire(source)));
            }
        }
        return sources;
    }

    /** Les racines de ressources de production des modules declares, aucun nom de module ecrit ici. */
    static List<Path> ressourcesDeProduction(Path racine) {
        return sousArbresDeclares(racine, RESSOURCES_DE_PRODUCTION);
    }

    /** Les arbres de sources Java de production des modules declares. */
    static List<Path> javaDeProduction(Path racine) {
        return sousArbresDeclares(racine, SOURCES_DE_PRODUCTION);
    }

    private static List<Path> sousArbresDeclares(Path racine, String sousArbre) {
        List<Path> arbres = new ArrayList<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path chemin = racine.resolve(module).resolve(sousArbre);
            if (Files.isDirectory(chemin)) {
                arbres.add(chemin);
            }
        }
        return arbres;
    }

    // --- Ce qui, dans une source, est jugeable : tout sauf ses commentaires ---

    static String texteJugeable(SourceJugee source) {
        return switch (source.nature()) {
            case GABARIT -> texteJugeableDuGabarit(source.contenu());
            case FEUILLE -> sansCommentairesDeBloc(source.contenu());
            case SCRIPT, JAVA -> sansCommentaires(source.contenu());
        };
    }

    /**
     * Les noeuds de texte, les noms d'attributs et les valeurs d'attributs - jamais les commentaires,
     * que Jsoup rend comme des noeuds d'une autre classe et que cette descente ne visite pas. Les
     * noeuds de donnees ({@code <script>}, {@code <style>}) sont juges comme du texte : un gabarit
     * n'a pas le droit d'en porter (ADR-0074), et s'il en portait un, un identifiant cable dedans
     * serait le meme defaut.
     */
    private static String texteJugeableDuGabarit(String contenu) {
        StringBuilder jugeable = new StringBuilder();
        for (Node noeud : Jsoup.parse(contenu, "", Parser.htmlParser()).getAllElements()) {
            collecter(noeud, jugeable);
        }
        return jugeable.toString();
    }

    private static void collecter(Node noeud, StringBuilder jugeable) {
        if (noeud instanceof Element element) {
            for (Attribute attribut : element.attributes()) {
                jugeable.append(attribut.getKey()).append('\n')
                        .append(attribut.getValue()).append('\n');
            }
            for (Node enfant : element.childNodes()) {
                if (enfant instanceof TextNode texte) {
                    jugeable.append(texte.getWholeText()).append('\n');
                } else if (enfant instanceof DataNode donnees) {
                    jugeable.append(donnees.getWholeData()).append('\n');
                }
            }
        }
    }

    /** La feuille : ses commentaires de bloc retires, le reste juge tel quel. */
    private static String sansCommentairesDeBloc(String contenu) {
        StringBuilder net = new StringBuilder(contenu.length());
        int position = 0;
        while (position < contenu.length()) {
            if (contenu.startsWith("/*", position)) {
                int fin = contenu.indexOf("*/", position + 2);
                position = fin < 0 ? contenu.length() : fin + 2;
            } else {
                net.append(contenu.charAt(position));
                position++;
            }
        }
        return net.toString();
    }

    /**
     * Java et JavaScript : commentaires de ligne et de bloc retires, chaines conservees. L'analyse
     * suit les etats d'un fichier - code, chaine, caractere, gabarit de chaine, commentaire - plutot
     * que de decouper a l'expression reguliere, exactement pour le motif ecrit dans
     * {@link AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest} : une adresse {@code http://} dans
     * une chaine ferait disparaitre la fin de sa ligne, et le garde-fou deviendrait vert sur un
     * identifiant ecrit juste apres. Le bloc de texte Java ({@code """}) n'est pas traite : le depot
     * n'en porte aucun en production, mesure le 2026-09-21.
     */
    private static String sansCommentaires(String contenu) {
        StringBuilder net = new StringBuilder(contenu.length());
        int position = 0;
        while (position < contenu.length()) {
            char caractere = contenu.charAt(position);
            if (caractere == '"' || caractere == '\'' || caractere == '`') {
                int fin = position + 1;
                while (fin < contenu.length() && contenu.charAt(fin) != caractere) {
                    fin += contenu.charAt(fin) == '\\' ? 2 : 1;
                }
                fin = Math.min(fin + 1, contenu.length());
                net.append(contenu, position, fin);
                position = fin;
            } else if (contenu.startsWith("//", position)) {
                int fin = contenu.indexOf('\n', position);
                position = fin < 0 ? contenu.length() : fin;
            } else if (contenu.startsWith("/*", position)) {
                int fin = contenu.indexOf("*/", position + 2);
                position = fin < 0 ? contenu.length() : fin + 2;
            } else {
                net.append(caractere);
                position++;
            }
        }
        return net.toString();
    }

    // --- Le juge, pur : il ne lit ni disque ni classpath ---

    static List<String> griefs(List<SourceJugee> sources, List<IdentifiantDeContenu> identifiants) {
        List<String> griefs = new ArrayList<>();
        for (SourceJugee source : sources) {
            String jugeable = texteJugeable(source);
            for (IdentifiantDeContenu identifiant : identifiants) {
                if (jugeable.contains(identifiant.valeur())) {
                    griefs.add(source.chemin() + " nomme litteralement '" + identifiant.valeur()
                            + "' (" + identifiant.origine() + ")");
                }
            }
        }
        return griefs;
    }

    static void verifier(List<SourceJugee> sources, List<IdentifiantDeContenu> identifiants) {
        if (identifiants.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun identifiant de contenu derive - ce garde-fou"
                    + " ne juge rien et resterait vert sur n'importe quel gabarit. Le mode de"
                    + " defaillance le plus probable de ce balayage, qui lit des fichiers par leur"
                    + " chemin.");
        }
        if (sources.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun fichier dans le perimetre - ce garde-fou ne"
                    + " juge rien et resterait vert sur n'importe quel identifiant.");
        }
        List<String> griefs = griefs(sources, identifiants);
        if (!griefs.isEmpty()) {
            throw new AssertionError("ADR-0094 decision 6 : un gabarit, une feuille maison, un script"
                    + " maison ou une source de production nomme litteralement un contenu"
                    + " particulier. Une mecanique generique qui connait un contenu particulier"
                    + " n'est plus generique. Le fait dont la vue a besoin se declare par le paquet"
                    + " et voyage par le contrat de contenu (patron emplacementDuLibelleDeZone,"
                    + " ADR-0094 decision 4), il ne se devine pas sur un identifiant : " + griefs);
        }
    }

    // --- Le garde-fou tel qu'il tourne en CI ---

    @Test
    void aucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulier() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));
        List<SourceJugee> sources = populationDeProduction(racine);

        assertThat(identifiants)
                .as("ADR-0003 : ce garde-fou ne prouve rien s'il ne derive aucun identifiant")
                .isNotEmpty();
        assertThat(sources)
                .as("ADR-0003 : ce garde-fou ne prouve rien s'il ne juge aucun fichier")
                .isNotEmpty();

        verifier(sources, identifiants);
    }

    private static List<SourceJugee> populationDeProduction(Path racine) {
        List<SourceJugee> sources = new ArrayList<>(sourcesDeRessources(ressourcesDeProduction(racine)));
        sources.addAll(sourcesJava(javaDeProduction(racine)));
        return sources;
    }

    /**
     * La population reelle, avec la premiere source de la nature demandee remplacee par elle-meme
     * plus le texte fautif. La mutation porte sur le materiau reel et non sur une fixture, et le
     * chemin mute est rendu pour que la morsure exige que le message le nomme.
     */
    private static SourceJugee muter(List<SourceJugee> population, Nature nature, String ajout) {
        for (int rang = 0; rang < population.size(); rang++) {
            SourceJugee source = population.get(rang);
            if (source.nature() == nature) {
                SourceJugee mutee = source.avec(ajout);
                population.set(rang, mutee);
                return mutee;
            }
        }
        throw new IllegalStateException("aucune source de nature " + nature + " dans la population");
    }

    private static IdentifiantDeContenu premier(List<IdentifiantDeContenu> identifiants, Genre genre) {
        return identifiants.stream()
                .filter(identifiant -> identifiant.genre() == genre)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("aucun identifiant de genre " + genre));
    }

    /**
     * <b>Morsure 1 (ADR-0094).</b> Un gabarit reel recoit la condition que l'alternative (a) de
     * l'ADR nomme comme la moins chere a ecrire : {@code th:if="${illustration == '...'}"}.
     * L'identifiant est <b>lu dans un paquet servi</b> et jamais ecrit ici - le jour ou ce paquet est
     * renomme, cette morsure juge le nom neuf.
     */
    @Test
    void unGabaritQuiNommeUneIllustrationServieFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));
        String illustration = premier(identifiants, Genre.ILLUSTRATION_D_ITEM).valeur();
        List<SourceJugee> population = populationDeProduction(racine);
        SourceJugee mutee = muter(population, Nature.GABARIT,
                "<div th:if=\"${illustration == '" + illustration + "'}\">dessin connu</div>\n");

        assertThatThrownBy(() -> verifier(population, identifiants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(illustration)
                .hasMessageContaining(mutee.chemin())
                .hasMessageContaining("ADR-0094");
    }

    /**
     * <b>Morsure 2.</b> La feuille maison recoit une regle dont le selecteur porte l'identifiant d'un
     * jeu servi, lu dans le catalogue reel. C'est la forme que prendrait une mise en forme taillee
     * pour un seul jeu, et elle rend le renommage de ce jeu muettement inoperant.
     */
    @Test
    void uneFeuilleMaisonDontLeSelecteurPorteUnJeuServiFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));
        String jeu = premier(identifiants, Genre.JEU_DU_CATALOGUE).valeur();
        List<SourceJugee> population = populationDeProduction(racine);
        SourceJugee mutee = muter(population, Nature.FEUILLE,
                "\n.jeu-" + jeu + " .zone { border-color: #123456; }\n");

        assertThatThrownBy(() -> verifier(population, identifiants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(jeu)
                .hasMessageContaining(mutee.chemin());
    }

    /**
     * <b>Morsure 3.</b> Une source de production compare un identifiant de jeu a un litteral - le
     * meme defaut qu'au gabarit, une couche plus haut, et celui que personne ne verrait en relecture
     * de vue.
     */
    @Test
    void uneSourceDeProductionQuiCompareUnJeuServiAUnLitteralFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));
        String jeu = premier(identifiants, Genre.JEU_DU_CATALOGUE).valeur();
        List<SourceJugee> population = populationDeProduction(racine);
        SourceJugee mutee = muter(population, Nature.JAVA,
                "\n// mutation\nboolean particulier = jeuId.equals(\"" + jeu + "\");\n");

        assertThatThrownBy(() -> verifier(population, identifiants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(jeu)
                .hasMessageContaining(mutee.chemin());
    }

    /**
     * <b>Morsure 4 (ADR-0003, ADR-0051).</b> Les deux cotes de la population sont juges non vides, et
     * les deux clauses mordent. Sans elles, un recensement qui ne lit rien serait vert par accident -
     * c'est le mode de defaillance le plus probable d'un garde-fou qui lit des fichiers par leur
     * chemin.
     */
    @Test
    void unePopulationVideDUnCoteOuDeLAutreFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));
        List<SourceJugee> sources = populationDeProduction(racine);

        assertThatThrownBy(() -> verifier(sources, List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun identifiant de contenu derive");
        assertThatThrownBy(() -> verifier(List.of(), identifiants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucun fichier dans le perimetre");
    }

    /**
     * <b>Morsure 5, sur le jeu fautif commite</b> plutot que sur une mutation en memoire : la chaine
     * disque -> SnakeYAML -> Jsoup -> juge fonctionne reellement, et l'exclusion des commentaires
     * mord dans les deux sens. Le gabarit fautif nomme une illustration dans un {@code th:if} et un
     * paquet dans un commentaire ; la feuille fautive nomme un jeu dans son selecteur. Le message
     * doit nommer les deux premiers et <b>jamais</b> le paquet cite en commentaire - sans quoi ce
     * garde-fou serait la faute de s05 recommencee.
     */
    @Test
    void leJeuFautifCommiteEstRefuseEtSonCommentaireNeLEstPas() {
        List<Path> ressources = List.of(CONTRE_EXEMPLE.resolve("ressources"));
        List<IdentifiantDeContenu> identifiants = identifiants(ressources);
        List<SourceJugee> sources = sourcesDeRessources(ressources);

        assertThat(identifiants).as("le jeu fautif ne prouve rien sans identifiant").isNotEmpty();
        assertThat(sources).as("le jeu fautif ne prouve rien sans fichier juge").isNotEmpty();

        assertThatThrownBy(() -> verifier(sources, identifiants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dessin-nomme-par-le-gabarit")
                .hasMessageContaining("jeu-nomme-par-la-feuille")
                .hasMessageNotContaining("paquet-cite-en-commentaire-seulement");
    }

    /**
     * L'autre sens du jeu fautif : le meme mecanisme, sur le <b>corrige</b> du meme jeu, est vert. Une
     * morsure qui ne montre que le rouge ne dit pas si le juge sait dire non - elle dit seulement
     * qu'il dit oui a tout.
     */
    @Test
    void leCorrigeDuMemeJeuFautifEstVert() {
        List<Path> ressources = List.of(CONTRE_EXEMPLE.resolve("corrige"));
        List<IdentifiantDeContenu> identifiants = identifiants(ressources);
        List<SourceJugee> sources = sourcesDeRessources(ressources);

        assertThat(identifiants).as("le corrige ne prouve rien sans identifiant").isNotEmpty();
        assertThat(sources).as("le corrige ne prouve rien sans fichier juge").isNotEmpty();

        verifier(sources, identifiants);
    }

    /**
     * ADR-0013 : le perimetre derive est aussi une propriete a prouver. Les deux genres sur lesquels
     * les morsures 1 a 3 s'appuient sont derives du contenu servi - sans quoi {@link #premier}
     * leverait au lieu de juger, et trois morsures ne mordraient plus rien.
     *
     * <p>{@code PAQUET_SERVI} ne figure pas dans cette clause, et c'est mesure : un identifiant
     * n'est retenu qu'une fois, sous le genre du premier fichier qui le nomme, et le catalogue est
     * lu avant les paquets - les 74 paquets servis sont donc tous retenus sous
     * {@code PAQUET_D_UNE_ENTREE}. Le genre {@code PAQUET_SERVI} ne nomme qu'un paquet reste sur
     * disque qu'aucune entree de catalogue ne reference ; il n'y en a aucun ce jour, et il est juge
     * quand meme le jour ou il y en aura un - c'est la lecon de {@code ChaquePaquetPorteUnIndiceTest}
     * (s05) : un paquet retire du catalogue mais laisse sur disque reste juge.
     */
    @Test
    void lesGenresDIdentifiantsSurLesquelsLesMorsuresSAppuientSontDerivesDuContenuServi() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));

        assertThat(identifiants).extracting(IdentifiantDeContenu::genre)
                .as("ADR-0094 : les identifiants juges sont ceux du catalogue, des paquets et des"
                        + " illustrations - un genre absent serait un pan du perimetre non juge")
                .contains(Genre.JEU_DU_CATALOGUE, Genre.PAQUET_D_UNE_ENTREE, Genre.ILLUSTRATION_D_ITEM,
                        Genre.DONNEE_DE_MATIERE);
    }

    /**
     * <b>Morsure 6 (ADR-0106, APM-5).</b> La feuille maison recoit la regle par emplacement que la
     * proposition d'ines-design ecrivait ({@code [data-teinte="1"]}), avec la teinte reelle lue dans la
     * donnee de matiere : c'est le chemin le moins cher d'une couleur par matiere, et il ferait d'une
     * matiere neuve une modification de feuille (ADR-0006). La teinte vit dans la donnee, et nulle part
     * ailleurs.
     */
    @Test
    void uneFeuilleMaisonQuiEcritUneTeinteDeMatiereFaitEchouerLeGardeFou() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        List<IdentifiantDeContenu> identifiants = identifiants(ressourcesDeProduction(racine));
        String teinte = identifiants.stream()
                .filter(identifiant -> identifiant.genre() == Genre.DONNEE_DE_MATIERE)
                .map(IdentifiantDeContenu::valeur)
                .filter(valeur -> valeur.startsWith("#"))
                .findFirst().orElseThrow(() -> new IllegalStateException("aucune teinte de matiere derivee"));
        List<SourceJugee> population = populationDeProduction(racine);
        SourceJugee mutee = muter(population, Nature.FEUILLE,
                "\n[data-teinte=\"1\"] { --teinte-fond: " + teinte + "; }\n");

        assertThatThrownBy(() -> verifier(population, identifiants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining(teinte)
                .hasMessageContaining("ADR-0106")
                .hasMessageContaining(mutee.chemin());
    }
}
