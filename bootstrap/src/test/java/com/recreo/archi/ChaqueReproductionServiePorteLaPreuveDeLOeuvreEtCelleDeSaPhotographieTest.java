package com.recreo.archi;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
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
 * ADR-0082 : une reproduction d'oeuvre servie par ce produit porte deux preuves, jamais une seule,
 * parce que les objets sont deux - l'oeuvre, et la photographie qui la reproduit.
 *
 * <p><b>Le piege que ce garde-fou existe pour fermer</b>, et il est reel dans les deux sens : une
 * oeuvre peut etre dans le domaine public sans que la photographie qui la reproduit le soit (celui
 * qui l'a prise peut y revendiquer un droit), et une institution peut renoncer a ses droits sur sa
 * photographie d'une oeuvre <b>encore protegee</b> (elle renonce a ce qu'elle a, pas a ce qu'elle
 * n'a pas). Une preuve unique, quel que soit le cote qu'elle couvre, laisse l'autre ouvert. Les
 * deux moities sont donc verifiees separement :</p>
 * <ul>
 *   <li><b>l'oeuvre</b> : {@code anneeDeMortDeLAuteur} strictement anterieure au pivot de 1900, ou
 *       oeuvre anonyme dont {@code anneeDeLOeuvre} est strictement anterieure au meme pivot. Le
 *       pivot n'est pas la duree legale : c'est la marge qui rend tout calcul de duree inutile -
 *       prorogations de guerre, mention "mort pour la France", durees etrangeres plus longues
 *       comprises. Il vaut aussi que ce garde-fou <b>ne depende pas de l'horloge</b> : un critere
 *       calcule sur l'annee courante rend un verdict qui change sans qu'aucun fichier ait bouge ;</li>
 *   <li><b>la reproduction</b> : {@code licenceDeLaReproduction} vaut exactement {@code CC0-1.0},
 *       et le registre porte de quoi <b>refaire</b> la verification - lieu de conservation,
 *       identifiant d'objet dans la collection, adresse de la fiche publiee. Une preuve qu'on ne
 *       peut pas refaire n'est pas une preuve, c'est une affirmation.</li>
 * </ul>
 *
 * <p><b>Ce que ce garde-fou interdit reellement a quelqu'un qui essaierait de mal faire</b> - la
 * question qui separe une regle d'une decoration : deposer une image d'oeuvre sans preuve (aucune
 * entree), ecrire une preuve sans image (aucun fichier), laisser un champ de preuve vide, entrer un
 * auteur mort au vingtieme siecle, entrer une oeuvre anonyme recente, nommer un auteur sans dire
 * quand il est mort, ecrire "libre de droits" ou "domaine public" a la place d'une licence
 * identifiable, deposer un fichier qui n'est pas une image JPEG, et confondre les trois populations
 * d'images du depot. Aucune de ces fautes ne se remarque a la lecture d'un diff de contenu ; toutes
 * rendent la CI rouge.</p>
 *
 * <p><b>Trois populations d'images, trois repertoires, aucune liste de noms</b> (ADR-0073 clause 2,
 * etendue par ADR-0082 decision 2). {@code static/images/} est le decor, dont le jeu ne depend pas
 * de l'item ; {@code static/illustrations/} sont les dessins maison, en SVG, juges sur leur
 * geometrie et sur leur poids ; {@code static/oeuvres/} sont les reproductions, en JPEG, jugees
 * ici. Chacune est <b>un repertoire</b>, donc derivable, et le prefixe {@code oeuvre-} d'un
 * identifiant est l'ombre de ce repertoire du cote du rendu : le gabarit ne peut pas lire un
 * repertoire - {@code ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers} interdit
 * {@code java.nio.file} hors de l'adaptateur de contenu -, donc la population se lit sur
 * l'identifiant. Ce garde-fou tient les deux bouts ensemble : aucun fichier des deux autres
 * repertoires ne porte ce prefixe, et aucun nom de base n'appartient a deux populations.</p>
 *
 * <p><b>Perimetre derive, jamais enumere</b> (ADR-0013 obligation 1). Les modules viennent du pom
 * agregateur, par {@link LesGardeFousSontMisALEpreuveTest#modulesDeclares(Path)}, reutilisee et non
 * reecrite ; les fichiers viennent des trois repertoires d'images de chaque module, jamais d'une
 * liste ; les entrees viennent de SnakeYAML, la bibliotheque avec laquelle le chargeur de
 * production lit les paquets. Le juge est une <b>fonction pure</b>,
 * {@link #verifier(List, Map, Set, Set)}, qui ne lit ni disque ni classpath : c'est ce qui permet
 * de le mordre sur du materiau mute en memoire.</p>
 *
 * <p><b>Ce que ce garde-fou examine aujourd'hui, honnetement</b> (ADR-0051). Le repertoire
 * {@code static/oeuvres/} n'existe dans aucun module et le registre ne declare aucune oeuvre :
 * <b>la population est vide</b>, et toutes les clauses portant sur une entree ou sur un fichier
 * portent donc sur rien. Ce n'est pas cache dans un vert -
 * {@link #laPopulationDeReproductionsEstVideEtCeGardeFouLeDeclare()} l'affirme, et le <b>cliquet a
 * plafond zero</b> rend cet etat intenable en silence : le jour ou une premiere reproduction entre,
 * ce fichier rougit, et le lot qui la livre releve le plafond sur la valeur qu'il installe, dans
 * son propre diff.</p>
 *
 * <p><b>Ce que ce garde-fou ne juge pas</b>, ecrit avant qu'on le lui reproche : que l'attribution
 * soit exacte - qu'un tableau soit bien de qui on le dit -, que la reproduction soit fidele a
 * l'oeuvre, et que le corpus tienne ART-5 (ni une seule epoque, ni une seule aire culturelle, ni
 * des artistes d'un seul genre). Une entree qui ment passe. Ces trois-la sont la main de
 * {@code nadia-prof}, elle l'a ecrit elle-meme, et aucun automate ne les verifie a sa place.</p>
 *
 * <p><b>T4 d'ADR-0082 (sofia-sm, s49) : le plafond de pixels paie la dette nommee sans alarme a la
 * section 7 de l'ADR.</b> {@link #dimensionsJpeg(byte[])} lit le segment SOFn d'un JPEG pour en tirer
 * sa largeur et sa hauteur declarees, et le juge refuse tout fichier dont le plus grand cote depasse
 * {@link #PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS} px. <b>Ce que T4 ne paie pas, et c'est ecrit plutot
 * que tu</b> : le remplacement du materiau de reference des morsures de ce fichier par du materiau
 * reel reste du, parce qu'aucune reproduction reelle n'existe encore a verifier - ce fichier n'a
 * aucun acces web, et T5 (choix verifie par nadia-prof, sourcing par un developpeur) n'est pas livre.
 * Les deux morsures de pixels ci-dessous mordent donc, comme les treize morsures existantes, sur un
 * en-tete JPEG synthetique fabrique pour exercer le lecteur de dimensions - jamais sur un fichier
 * qui pretendrait representer une oeuvre reelle ni sur une preuve de domaine public inventee.</p>
 */
class ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest {

    /**
     * Ecrits sous leur forme a barre oblique : c'est celle que
     * {@link LesGardeFousSontMisALEpreuveTest} cherche pour recenser un garde-fou de balayage.
     */
    private static final String REPRODUCTIONS = "src/main/resources/static/oeuvres";

    private static final String ILLUSTRATIONS_DE_CONTENU = "src/main/resources/static/illustrations";

    private static final String DECOR = "src/main/resources/static/images";

    /** Le registre vit hors des modules : rien ne le sert, et il ne pese rien dans le fat jar. */
    private static final String REGISTRE = "squad/corpus/oeuvres-domaine-public.yaml";

    private static final String CLE_OEUVRES = "oeuvres";

    private static final String EXTENSION = ".jpg";

    /** ADR-0082 decision 2 : l'identifiant porte sa population, faute de pouvoir lire un repertoire. */
    private static final String PREFIXE = "oeuvre-";

    private static final String ALPHABET = "oeuvre-[a-z0-9-]+";

    /**
     * ADR-0082 decision 3. Une oeuvre dont l'auteur est mort avant cette annee, ou une oeuvre
     * anonyme anterieure a cette annee, est libre partout et sans calcul. Ce nombre paie une
     * amputation nommee dans l'ADR - le vingtieme siecle entier - et il ne descend pas sans un
     * ADR : le baisser rouvre les prorogations de guerre et les durees etrangeres plus longues.
     */
    private static final int PIVOT = 1900;

    private static final String AUTEUR_ANONYME = "anonyme";

    private static final String LICENCE_ADMISE = "CC0-1.0";

    private static final String CHAMP_IDENTIFIANT = "identifiant";

    private static final String CHAMP_AUTEUR = "auteur";

    private static final String CHAMP_ANNEE_DE_L_OEUVRE = "anneeDeLOeuvre";

    private static final String CHAMP_ANNEE_DE_MORT = "anneeDeMortDeLAuteur";

    private static final String CHAMP_LICENCE = "licenceDeLaReproduction";

    /**
     * Les champs de texte que toute entree porte, non vides. Le titre, l'auteur et le lieu de
     * conservation portent ART-3 de {@code nadia-prof} ; l'identifiant d'objet et l'adresse de la
     * fiche portent la refaisabilite de la preuve, sans laquelle l'entree n'est qu'une affirmation.
     */
    private static final List<String> CHAMPS_DE_TEXTE = List.of(
            CHAMP_IDENTIFIANT,
            "titre",
            CHAMP_AUTEUR,
            "lieuDeConservation",
            "identifiantDObjet",
            "urlDeLaSource",
            CHAMP_LICENCE);

    /**
     * Cliquet exact, ADR-0082 decision 6 : la taille de la population servie au 2026-09-05, qui est
     * zero. Ce n'est pas un plafond de confort - un cliquet dont le plafond depasse la dette ne
     * cliquette rien. Le lot qui livre les premieres reproductions releve ces deux nombres sur la
     * valeur qu'il installe, et <b>mesure</b> le poids au lieu de l'estimer.
     */
    private static final int REPRODUCTIONS_SERVIES = 0;

    private static final int POIDS_TOTAL_MAXIMUM = 0;

    /**
     * T4 d'ADR-0082 (sofia-sm, s49) : l'analogue matriciel du {@code viewBox} des dessins -
     * {@code ADR-0082}, section "Le cout et le decoupage en taches", table du poids : "Reproduction
     * servie, plus grand cote : 352 px vise (deux fois le plafond, pour un ecran a double densite)".
     * Un JPEG ne declare pas ses dimensions dans un attribut lisible comme un SVG ; elles vivent dans
     * son segment SOFn, lu par {@link #dimensionsJpeg(byte[])}. Ce plafond evite qu'un fichier a la
     * resolution ou les musees le publient (3 000 a 6 000 px selon l'ADR) n'entre dans le fat jar :
     * c'est lui qui separe 2 Mo de 200 pour le corpus complet.
     */
    private static final int PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS = 352;

    /** ADR-0013 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest");

    private static byte[] octets(Path fichier) {
        try {
            return Files.readAllBytes(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Path> repertoires(Path racine, String sousArbre) {
        List<Path> trouves = new ArrayList<>();
        for (String module : LesGardeFousSontMisALEpreuveTest.modulesDeclares(racine)) {
            Path repertoire = racine.resolve(module).resolve(sousArbre);
            if (Files.isDirectory(repertoire)) {
                trouves.add(repertoire);
            }
        }
        return trouves;
    }

    /** Les fichiers d'un repertoire d'images, avec leur nom complet comme cle. */
    static Map<String, byte[]> fichiersIndexesParNom(List<Path> repertoires) {
        Map<String, byte[]> fichiers = new LinkedHashMap<>();
        for (Path repertoire : repertoires) {
            if (!Files.isDirectory(repertoire)) {
                continue;
            }
            try (Stream<Path> arborescence = Files.walk(repertoire)) {
                arborescence.filter(Files::isRegularFile).sorted()
                        .forEach(fichier -> fichiers.putIfAbsent(
                                fichier.getFileName().toString(), octets(fichier)));
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return fichiers;
    }

    /** Les noms de base des deux autres populations d'images, pour la clause de non-confusion. */
    static Set<String> nomsDeBase(List<Path> repertoires) {
        Set<String> noms = new LinkedHashSet<>();
        for (Path repertoire : repertoires) {
            try (Stream<Path> arborescence = Files.walk(repertoire)) {
                arborescence.filter(Files::isRegularFile).sorted().forEach(fichier -> {
                    String nom = fichier.getFileName().toString();
                    int point = nom.lastIndexOf('.');
                    noms.add(point < 0 ? nom : nom.substring(0, point));
                });
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return noms;
    }

    /** Les entrees du registre, lues par la bibliotheque avec laquelle le chargeur lit les paquets. */
    static List<Map<String, Object>> registre(Path fichier) {
        Object corps = new Yaml().load(new String(octets(fichier), StandardCharsets.UTF_8));
        if (!(corps instanceof Map<?, ?> racine)) {
            throw new AssertionError(fichier + " : la racine du registre doit etre une correspondance");
        }
        if (!(racine.get(CLE_OEUVRES) instanceof List<?> oeuvres)) {
            throw new AssertionError(fichier + " : le registre doit porter une liste '" + CLE_OEUVRES
                    + "', vide tant qu'aucune oeuvre n'est servie");
        }
        List<Map<String, Object>> entrees = new ArrayList<>();
        for (Object entree : oeuvres) {
            if (!(entree instanceof Map<?, ?> champs)) {
                throw new AssertionError(fichier + " : chaque entree du registre doit etre une"
                        + " correspondance");
            }
            Map<String, Object> copie = new LinkedHashMap<>();
            champs.forEach((cle, valeur) -> copie.put(String.valueOf(cle), valeur));
            entrees.add(copie);
        }
        return entrees;
    }

    /**
     * Le juge, fonction pure. Il ne lit ni disque ni classpath : ses quatre entrees sont deja lues,
     * ce qui permet de le mordre sur du materiau mute en memoire.
     */
    static void verifier(List<Map<String, Object>> registre,
                         Map<String, byte[]> reproductions,
                         Set<String> illustrations,
                         Set<String> decor) {
        Set<String> declarees = new LinkedHashSet<>();
        for (Map<String, Object> entree : registre) {
            String identifiant = String.valueOf(entree.get(CHAMP_IDENTIFIANT));
            if (!identifiant.matches(ALPHABET)) {
                throw new AssertionError("ADR-0082 : l'identifiant '" + identifiant + "' du registre"
                        + " sort de l'alphabet " + ALPHABET + " ; un identifiant de reproduction"
                        + " porte le prefixe de sa population");
            }
            if (!declarees.add(identifiant)) {
                throw new AssertionError("ADR-0082 : l'oeuvre '" + identifiant + "' est declaree deux"
                        + " fois dans le registre ; deux preuves pour un meme fichier divergeront");
            }
            for (String champ : CHAMPS_DE_TEXTE) {
                Object valeur = entree.get(champ);
                if (!(valeur instanceof String texte) || texte.isBlank()) {
                    throw new AssertionError("ADR-0082 : l'oeuvre '" + identifiant + "' n'a pas de '"
                            + champ + "' ; une preuve incomplete ne prouve rien");
                }
            }
            verifierLaPreuveDeLOeuvre(entree, identifiant);
            verifierLaPreuveDeLaReproduction(entree, identifiant);
        }
        for (String identifiant : declarees) {
            if (!reproductions.containsKey(identifiant + EXTENSION)) {
                throw new AssertionError("ADR-0082 : le registre declare '" + identifiant + "' mais"
                        + " aucun fichier " + identifiant + EXTENSION + " n'est servi ; une preuve"
                        + " sans image est une entree morte");
            }
        }
        for (Map.Entry<String, byte[]> fichier : reproductions.entrySet()) {
            String nom = fichier.getKey();
            if (!nom.endsWith(EXTENSION)) {
                throw new AssertionError("ADR-0082 : '" + nom + "' est servi comme reproduction sans"
                        + " porter l'extension " + EXTENSION + " ; le gabarit ecrit cette extension"
                        + " en clair et n'en connait pas d'autre");
            }
            String base = nom.substring(0, nom.length() - EXTENSION.length());
            if (!declarees.contains(base)) {
                throw new AssertionError("ADR-0082 : la reproduction '" + nom + "' n'a aucune entree"
                        + " dans " + REGISTRE + " ; le depot ne peut pas dire pourquoi elle est libre"
                        + " de droits");
            }
            if (!estUnJpeg(fichier.getValue())) {
                throw new AssertionError("ADR-0082 : '" + nom + "' n'est pas une image JPEG ; ses"
                        + " premiers octets ne sont pas ceux d'un JPEG");
            }
            int[] dimensions = dimensionsJpeg(fichier.getValue());
            if (dimensions.length == 2) {
                int plusGrandCote = Math.max(dimensions[0], dimensions[1]);
                if (plusGrandCote > PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS) {
                    throw new AssertionError("ADR-0082 : '" + nom + "' declare " + dimensions[0] + "x"
                            + dimensions[1] + " px, au-dessus du plafond de "
                            + PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS + " px sur son plus grand cote ;"
                            + " un fichier a une resolution de musee pese cent fois plus et n'apporte"
                            + " rien a un rendu qui ne depasse jamais ce plafond");
                }
            }
            if (illustrations.contains(base) || decor.contains(base)) {
                throw new AssertionError("ADR-0082 : '" + base + "' appartient a deux populations"
                        + " d'images a la fois ; les trois repertoires restent disjoints, sinon un"
                        + " identifiant servi ne designe plus une image et une seule");
            }
        }
        for (String dessin : illustrations) {
            if (dessin.startsWith(PREFIXE)) {
                throw new AssertionError("ADR-0082 : le dessin maison '" + dessin + "' porte le"
                        + " prefixe '" + PREFIXE + "', reserve aux reproductions ; le rendu lit la"
                        + " population sur le prefixe et irait chercher ce dessin dans le mauvais"
                        + " repertoire");
            }
        }
        for (String image : decor) {
            if (image.startsWith(PREFIXE)) {
                throw new AssertionError("ADR-0082 : l'image de decor '" + image + "' porte le"
                        + " prefixe '" + PREFIXE + "', reserve aux reproductions ; le rendu lit la"
                        + " population sur le prefixe");
            }
        }
    }

    /** Premiere moitie : l'oeuvre elle-meme est dans le domaine public, sans calcul de duree. */
    private static void verifierLaPreuveDeLOeuvre(Map<String, Object> entree, String identifiant) {
        if (!(entree.get(CHAMP_ANNEE_DE_L_OEUVRE) instanceof Integer anneeDeLOeuvre)) {
            throw new AssertionError("ADR-0082 : l'oeuvre '" + identifiant + "' n'a pas de '"
                    + CHAMP_ANNEE_DE_L_OEUVRE + "' entier ; ART-3 exige la date");
        }
        if (AUTEUR_ANONYME.equals(entree.get(CHAMP_AUTEUR))) {
            if (entree.containsKey(CHAMP_ANNEE_DE_MORT)) {
                throw new AssertionError("ADR-0082 : l'oeuvre anonyme '" + identifiant + "' porte un '"
                        + CHAMP_ANNEE_DE_MORT + "' ; un auteur anonyme n'a pas d'annee de mort"
                        + " connue, et l'ecrire ferait passer pour verifiee une preuve qui ne l'est"
                        + " pas");
            }
            if (anneeDeLOeuvre >= PIVOT) {
                throw new AssertionError("ADR-0082 : l'oeuvre anonyme '" + identifiant + "' date de "
                        + anneeDeLOeuvre + ", au pivot de " + PIVOT + " ou apres ; la protection"
                        + " d'une oeuvre anonyme court depuis sa publication, et rien ici ne prouve"
                        + " qu'elle est echue");
            }
            return;
        }
        if (!(entree.get(CHAMP_ANNEE_DE_MORT) instanceof Integer anneeDeMort)) {
            throw new AssertionError("ADR-0082 : l'oeuvre '" + identifiant + "' nomme son auteur sans"
                    + " porter un '" + CHAMP_ANNEE_DE_MORT + "' entier ; c'est cette annee, et elle"
                    + " seule, qui prouve que l'oeuvre est libre");
        }
        if (anneeDeMort >= PIVOT) {
            throw new AssertionError("ADR-0082 : l'auteur de '" + identifiant + "' est mort en "
                    + anneeDeMort + ", au pivot de " + PIVOT + " ou apres ; le depot ne calcule"
                    + " aucune duree de protection, il exige une marge qui rend le calcul inutile");
        }
    }

    /** Seconde moitie : la photographie qui reproduit l'oeuvre est libre, et la preuve se refait. */
    private static void verifierLaPreuveDeLaReproduction(Map<String, Object> entree,
                                                         String identifiant) {
        Object licence = entree.get(CHAMP_LICENCE);
        if (!LICENCE_ADMISE.equals(licence)) {
            throw new AssertionError("ADR-0082 : la reproduction de '" + identifiant + "' porte la"
                    + " licence '" + licence + "' ; la seule valeur admise est '" + LICENCE_ADMISE
                    + "'. Une oeuvre du domaine public ne rend pas libre la photographie qui la"
                    + " reproduit : il faut que celui qui l'a prise y ait renonce");
        }
    }

    private static boolean estUnJpeg(byte[] octets) {
        return octets.length >= 3
                && (octets[0] & 0xFF) == 0xFF
                && (octets[1] & 0xFF) == 0xD8
                && (octets[2] & 0xFF) == 0xFF;
    }

    /**
     * T4 d'ADR-0082 : la largeur et la hauteur intrinseques d'un JPEG, lues dans son segment SOFn
     * (Start Of Frame - les marqueurs 0xC0 a 0xCF, hors 0xC4, 0xC8 et 0xCC qui ne sont pas des
     * marqueurs de trame). Balayage de segments depuis l'octet qui suit le marqueur SOI (0xFFD8), sans
     * aucune bibliotheque d'image : ce depot ne decode jamais un JPEG, il lit seulement l'en-tete qui
     * declare sa taille. Renvoie un tableau vide si aucun segment SOFn n'est trouve, ce que le juge
     * traite comme "rien a comparer au plafond" plutot que comme une faute - un fichier dont l'en-tete
     * ne se laisse pas lire est deja refuse par {@link #estUnJpeg(byte[])} ou par la clause JPEG
     * ci-dessus s'il n'en est pas un ; cette methode ne juge que la taille d'un JPEG par ailleurs
     * valide.
     */
    static int[] dimensionsJpeg(byte[] octets) {
        int position = 2;
        while (position + 4 <= octets.length) {
            if ((octets[position] & 0xFF) != 0xFF) {
                position++;
                continue;
            }
            int marqueur = octets[position + 1] & 0xFF;
            if (marqueur == 0xD8 || marqueur == 0xD9 || (marqueur >= 0xD0 && marqueur <= 0xD7)) {
                position += 2;
                continue;
            }
            boolean estUnSof = marqueur >= 0xC0 && marqueur <= 0xCF
                    && marqueur != 0xC4 && marqueur != 0xC8 && marqueur != 0xCC;
            if (estUnSof && position + 9 <= octets.length) {
                int hauteur = ((octets[position + 5] & 0xFF) << 8) | (octets[position + 6] & 0xFF);
                int largeur = ((octets[position + 7] & 0xFF) << 8) | (octets[position + 8] & 0xFF);
                return new int[] {largeur, hauteur};
            }
            int longueurSegment = ((octets[position + 2] & 0xFF) << 8) | (octets[position + 3] & 0xFF);
            position += 2 + longueurSegment;
        }
        return new int[0];
    }

    /** Cliquet exact : ni la population ni son poids ne grandissent sans un diff qui le dit. */
    static void verifierLeCliquet(Map<String, byte[]> reproductions, int plafondDeFichiers,
                                  int plafondDOctets) {
        if (reproductions.size() > plafondDeFichiers) {
            throw new AssertionError("ADR-0082 : " + reproductions.size() + " reproductions servies"
                    + " pour un plafond de " + plafondDeFichiers + " ; le lot qui en ajoute releve"
                    + " ce plafond sur la valeur qu'il installe, dans son propre commit");
        }
        int poids = reproductions.values().stream().mapToInt(octets -> octets.length).sum();
        if (poids > plafondDOctets) {
            throw new AssertionError("ADR-0082 : " + poids + " octets de reproductions pour un"
                    + " plafond de " + plafondDOctets + " ; un corpus d'images entre dans le fat jar"
                    + " et n'en sort plus");
        }
    }

    private static Map<String, byte[]> reproductionsDuDepot(Path racine) {
        return fichiersIndexesParNom(repertoires(racine, REPRODUCTIONS));
    }

    /** Deux entrees saines, l'une a auteur nomme, l'autre anonyme : la reference des morsures. */
    private static List<Map<String, Object>> registreSain() {
        Map<String, Object> nommee = new LinkedHashMap<>();
        nommee.put(CHAMP_IDENTIFIANT, "oeuvre-sujet-nomme");
        nommee.put("titre", "Titre de reference");
        nommee.put(CHAMP_AUTEUR, "Auteur De Reference");
        nommee.put(CHAMP_ANNEE_DE_L_OEUVRE, 1831);
        nommee.put(CHAMP_ANNEE_DE_MORT, 1849);
        nommee.put("lieuDeConservation", "Institution de reference");
        nommee.put("identifiantDObjet", "REF-1");
        nommee.put("urlDeLaSource", "https://exemple.invalid/collection/reference-un");
        nommee.put(CHAMP_LICENCE, LICENCE_ADMISE);

        Map<String, Object> anonyme = new LinkedHashMap<>();
        anonyme.put(CHAMP_IDENTIFIANT, "oeuvre-sujet-anonyme");
        anonyme.put("titre", "Autre titre de reference");
        anonyme.put(CHAMP_AUTEUR, AUTEUR_ANONYME);
        anonyme.put(CHAMP_ANNEE_DE_L_OEUVRE, 1450);
        anonyme.put("lieuDeConservation", "Autre institution de reference");
        anonyme.put("identifiantDObjet", "REF-2");
        anonyme.put("urlDeLaSource", "https://exemple.invalid/collection/reference-deux");
        anonyme.put(CHAMP_LICENCE, LICENCE_ADMISE);

        return new ArrayList<>(List.of(nommee, anonyme));
    }

    /** Les octets d'un debut de JPEG : ce que la clause de format juge, et rien de plus. */
    private static Map<String, byte[]> reproductionsSaines() {
        byte[] jpeg = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, 0x00, 0x10};
        Map<String, byte[]> fichiers = new LinkedHashMap<>();
        fichiers.put("oeuvre-sujet-nomme" + EXTENSION, jpeg);
        fichiers.put("oeuvre-sujet-anonyme" + EXTENSION, jpeg);
        return fichiers;
    }

    @Test
    void leDepotReelTientLaRegle() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();

        verifier(registre(racine.resolve(REGISTRE)),
                reproductionsDuDepot(racine),
                nomsDeBase(repertoires(racine, ILLUSTRATIONS_DE_CONTENU)),
                nomsDeBase(repertoires(racine, DECOR)));
    }

    @Test
    void laPopulationDeReproductionsEstVideEtCeGardeFouLeDeclare() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();

        assertThat(reproductionsDuDepot(racine))
                .as("ADR-0051 : aucune reproduction n'est servie au 2026-09-05, donc toutes les"
                        + " clauses portant sur un fichier portent sur une population vide. Le"
                        + " cliquet a plafond zero est ce qui empeche cet etat de changer en"
                        + " silence")
                .isEmpty();
        assertThat(registre(racine.resolve(REGISTRE)))
                .as("ADR-0051 : le registre existe, il se lit, et il ne declare aucune oeuvre")
                .isEmpty();
    }

    @Test
    void leCliquetVautExactementLaTailleDeLaPopulationServie() {
        verifierLeCliquet(reproductionsDuDepot(LesGardeFousSontMisALEpreuveTest.racineDuDepot()),
                REPRODUCTIONS_SERVIES, POIDS_TOTAL_MAXIMUM);
    }

    @Test
    void uneReproductionDePlusQueLePlafondFaitRougirLeCliquet() {
        assertThatThrownBy(() -> verifierLeCliquet(reproductionsSaines(), REPRODUCTIONS_SERVIES,
                POIDS_TOTAL_MAXIMUM))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("reproductions servies");
    }

    @Test
    void unMaterielSainPasse() {
        verifier(registreSain(), reproductionsSaines(), Set.of("figure-carre-droit"),
                Set.of("cabinet-fronton"));
    }

    @Test
    void uneEntreeSansFichierEstRefusee() {
        Map<String, byte[]> ampute = new LinkedHashMap<>(reproductionsSaines());
        ampute.remove("oeuvre-sujet-anonyme" + EXTENSION);

        assertThatThrownBy(() -> verifier(registreSain(), ampute, Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une preuve sans image est une entree morte");
    }

    @Test
    void unFichierSansEntreeEstRefuse() {
        List<Map<String, Object>> ampute = new ArrayList<>(registreSain());
        ampute.remove(1);

        assertThatThrownBy(() -> verifier(ampute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'a aucune entree");
    }

    @Test
    void unChampDePreuveVideEstRefuse() {
        List<Map<String, Object>> mute = registreSain();
        mute.get(0).put("identifiantDObjet", "   ");

        assertThatThrownBy(() -> verifier(mute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une preuve incomplete ne prouve rien");
    }

    @Test
    void unAuteurMortApresLePivotEstRefuse() {
        List<Map<String, Object>> mute = registreSain();
        mute.get(0).put(CHAMP_ANNEE_DE_MORT, 1954);

        assertThatThrownBy(() -> verifier(mute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("est mort en 1954");
    }

    @Test
    void unAuteurNommeSansAnneeDeMortEstRefuse() {
        List<Map<String, Object>> mute = registreSain();
        mute.get(0).remove(CHAMP_ANNEE_DE_MORT);

        assertThatThrownBy(() -> verifier(mute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nomme son auteur sans");
    }

    @Test
    void uneOeuvreAnonymePosterieureAuPivotEstRefusee() {
        List<Map<String, Object>> mute = registreSain();
        mute.get(1).put(CHAMP_ANNEE_DE_L_OEUVRE, 1930);

        assertThatThrownBy(() -> verifier(mute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("l'oeuvre anonyme");
    }

    @Test
    void uneLicenceQuiNEstPasUneRenonciationEstRefusee() {
        List<Map<String, Object>> mute = registreSain();
        mute.get(0).put(CHAMP_LICENCE, "domaine public");

        assertThatThrownBy(() -> verifier(mute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne rend pas libre la photographie");
    }

    @Test
    void unIdentifiantSansSonPrefixeEstRefuse() {
        List<Map<String, Object>> mute = registreSain();
        mute.get(0).put(CHAMP_IDENTIFIANT, "grande-vague");

        assertThatThrownBy(() -> verifier(mute, reproductionsSaines(), Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("sort de l'alphabet");
    }

    @Test
    void unDessinMaisonQuiPorteLePrefixeDesReproductionsEstRefuse() {
        assertThatThrownBy(() -> verifier(registreSain(), reproductionsSaines(),
                Set.of("oeuvre-sujet-tiers"), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le rendu lit la population sur le prefixe");
    }

    @Test
    void unNomDeBasePartageParDeuxPopulationsEstRefuse() {
        assertThatThrownBy(() -> verifier(registreSain(), reproductionsSaines(), Set.of(),
                Set.of("oeuvre-sujet-nomme")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("deux populations");
    }

    /**
     * T4 d'ADR-0082, morsure sur du materiau synthetique en memoire : un JPEG dont le segment SOF0
     * declare 1000x1000 px, au-dessus du plafond de 352 px. Le fichier n'est pas une fixture qui
     * pretend representer une oeuvre - ce sont les quinze octets minimaux d'un en-tete JPEG valide,
     * construits pour exercer le lecteur de dimensions, jamais pour simuler une preuve de domaine
     * public.
     */
    @Test
    void uneReproductionAuDelaDuPlafondDePixelsEstRefusee() {
        byte[] grandJpeg = {
                (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xC0,
                0x00, 0x0B, 0x08, 0x03, (byte) 0xE8, 0x03, (byte) 0xE8,
                0x01, 0x01, 0x11, 0x00};
        Map<String, byte[]> reproductions = new LinkedHashMap<>(reproductionsSaines());
        reproductions.put("oeuvre-sujet-nomme" + EXTENSION, grandJpeg);

        assertThatThrownBy(() -> verifier(registreSain(), reproductions, Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("plafond de " + PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS);
    }

    /**
     * T4 d'ADR-0082, preuve positive sur le meme materiau synthetique : un en-tete JPEG declarant
     * 100x100 px, sous le plafond, passe la clause sans la declencher. Sans cette moitie, la morsure
     * precedente pourrait etre verte parce que le lecteur de dimensions ne lirait jamais rien.
     */
    @Test
    void uneReproductionSousLePlafondDePixelsPasseLaClause() {
        byte[] petitJpeg = {
                (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xC0,
                0x00, 0x0B, 0x08, 0x00, 0x64, 0x00, 0x64,
                0x01, 0x01, 0x11, 0x00};
        Map<String, byte[]> reproductions = new LinkedHashMap<>(reproductionsSaines());
        reproductions.put("oeuvre-sujet-nomme" + EXTENSION, petitJpeg);

        verifier(registreSain(), reproductions, Set.of(), Set.of());
    }

    @Test
    void leJeuFautifCommiteEstRefuse() {
        Map<String, byte[]> fichiers =
                fichiersIndexesParNom(List.of(CONTRE_EXEMPLE.resolve("oeuvres")));

        assertThatThrownBy(() -> verifier(registre(CONTRE_EXEMPLE.resolve("registre-fautif.yaml")),
                fichiers, Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("est mort en 1954");
    }

    @Test
    void leFichierDuJeuFautifQuiNEstPasUnJpegEstRefuse() {
        Map<String, byte[]> fichiers =
                fichiersIndexesParNom(List.of(CONTRE_EXEMPLE.resolve("oeuvres")));
        List<Map<String, Object>> corrige = registre(CONTRE_EXEMPLE.resolve("registre-fautif.yaml"));
        corrige.get(0).put(CHAMP_ANNEE_DE_MORT, 1849);

        assertThatThrownBy(() -> verifier(corrige, fichiers, Set.of(), Set.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas une image JPEG");
    }

    @Test
    void leRegistreQueCeGardeFouJugeExisteEtEstLeSien() {
        assertThat(Files.isRegularFile(
                LesGardeFousSontMisALEpreuveTest.racineDuDepot().resolve(REGISTRE)))
                .as("ADR-0082 : la preuve du depot est ce fichier ; son absence est une faute, pas"
                        + " une population vide")
                .isTrue();
        assertThat(CHAMPS_DE_TEXTE)
                .as("ADR-0051 : un juge qui n'exigerait aucun champ ne prouverait rien")
                .isNotEmpty();
    }
}
