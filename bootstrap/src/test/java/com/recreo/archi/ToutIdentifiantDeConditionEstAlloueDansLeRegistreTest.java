package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0085, condition {@code PAR-5} de nadia-prof (2026-09-05) : <b>un identifiant de condition
 * designe une condition et une seule, dans tout le depot, et son allocation est visible avant
 * d'ecrire.</b>
 *
 * <p><b>Le fait, et il est d'elle.</b> Le 2026-09-03, deux instances d'elle-meme ecrivant dix
 * referentiels ont alloue <b>le meme numero a deux conditions</b>. La collision n'a ete vue que
 * parce que la seconde instance est allee ecrire dans le meme espace de noms et a trouve le numero
 * pris : <i>"si les deux fichiers avaient ete ecrits par deux instances dont aucune ne lisait
 * l'autre, personne ne l'aurait vue"</i>. Depuis, elle a refait la mesure <b>a la main deux sprints
 * de suite</b> et a route le registre trois fois. C'est l'une des deux conditions qui bloquent la
 * levee de sa retenue de 1,0 sur la borne d'audit.
 *
 * <p><b>Ce que ce garde-fou interdit reellement</b>, ecrit avant qu'on demande a quoi il sert :
 * <ol>
 *   <li>ecrire un identifiant de condition neuf <b>sans l'allouer</b> - clause 2. C'est la friction
 *       qui rend l'allocation visible : pour ajouter sa ligne, il faut ouvrir le registre, et en
 *       l'ouvrant on voit ce qui est pris ;</li>
 *   <li><b>allouer deux fois le meme identifiant</b> - clause 1. C'est la collision elle-meme, et
 *       c'est le seul point ou deux instances qui ne se lisent pas se rencontrent : chacune ajoute
 *       sa ligne, et le depot en porte deux ;</li>
 *   <li>laisser <b>pourrir</b> une allocation - clause 3 : un identifiant alloue dont le document
 *       nomme ne parle plus. Un registre qui pointe vers rien est de la meme famille qu'une
 *       reference qui pointe vers rien (ADR-0066) ;</li>
 *   <li>laisser <b>dormir</b> un homonyme ecarte - clause 4 : la section 3 du registre est une
 *       enumeration, donc un cliquet, et son plafond vaut exactement la taille de ce qu'elle
 *       nomme.</li>
 * </ol>
 *
 * <p><b>Ce qu'il ne juge pas, et c'est la moitie de ce qu'il faut savoir.</b> Il ne verifie pas
 * qu'un identifiant <b>designe</b> ce que le registre dit : deux lignes distinctes peuvent porter
 * deux fois la meme condition sous deux noms, et il passera. Le sens d'une condition se lit ;
 * l'identite se tient. C'est la meme frontiere qu'ADR-0081 trace pour les codes de ligne, et pour la
 * meme raison. <b>Le registre ne recopie donc aucun sens</b> : il nomme un document ou l'identifiant
 * est ecrit, jamais ce qu'il veut dire - un second exemplaire du sens rancirait (ADR-0061).
 *
 * <p><b>Perimetre derive, jamais enumere.</b> Les identifiants cites viennent de tous les fichiers
 * texte du depot, les repertoires de sortie de build, les arbres de contre-exemple et les binaires
 * exclus - ces derniers reconnus a l'octet nul plutot qu'a leur extension (ADR-0036). Aucun
 * identifiant, aucun prefixe et aucun fichier n'est nomme dans cette source hors de son depot
 * fautif. Les quatre familles voisines - codes de ligne de referentiel, decisions, codes de story,
 * rangs internes a un document - sont ecartees <b>par leur forme</b> et non par une liste : leur
 * ecriture ne produit aucune correspondance avec {@link #IDENTIFIANT}, ce que
 * {@link #lesFormesVoisinesNeSontPasDesIdentifiantsDeCondition()} constate plutot que de le
 * supposer. Les deux prefixes {@code ADR} et {@code DEC} sont la seule exception, et ils sont
 * ecartes parce qu'<b>une autre decision les alloue deja</b> (ADR-0066), pas parce qu'ils genent.
 *
 * <p><b>Ce n'est pas une {@code ArchRule}, et le compte de regles ArchUnit du depot est
 * inchange.</b> ArchUnit lit du bytecode ; un identifiant ecrit dans un texte de gouvernance n'y est
 * pas une propriete lisible. Le mecanisme est un balayage de sources, sur le precedent de
 * {@link ToutesLesReferencesDeDecisionResolventTest}, dont il reprend la lecture de perimetre plutot
 * que d'en tenir une seconde notion.
 */
class ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest {

    /** Le registre, seule source de l'allocation d'un identifiant de condition. */
    private static final String REGISTRE = "squad/decisions/REGISTRE-identifiants.md";

    /**
     * La forme d'un identifiant de condition : un prefixe de deux a cinq lettres majuscules, un
     * {@code R} facultatif qui distingue une reserve d'une condition, un numero d'un ou deux
     * chiffres. Les bornes interdisent qu'il soit colle a un mot, a un chiffre ou a un tiret : c'est
     * ce qui ecarte un code de ligne et un code de story sans avoir a les nommer.
     */
    private static final Pattern IDENTIFIANT = Pattern.compile(
            "(?<![A-Za-z0-9_/-])([A-Z]{2,5})-(R?[0-9]{1,2})(?![A-Za-z0-9_-])");

    /** Les deux prefixes qu'ADR-0066 alloue deja, sur les noms de fichiers de squad/decisions/. */
    private static final Set<String> PREFIXES_DE_DECISION = Set.of("ADR", "DEC");

    /** Une ligne d'allocation du registre : un identifiant, un chemin de document. */
    private static final Pattern ALLOCATION = Pattern.compile(
            "^\\| `([A-Z]{2,5}-R?[0-9]{1,2})` \\| `([^`|]+)` \\|$");

    /** Une ligne d'homonyme ecarte : un identifiant apparent, et le motif qui l'ecarte. */
    private static final Pattern HOMONYME = Pattern.compile(
            "^\\| `([A-Z]{2,5}-R?[0-9]{1,2})` \\| ([^|]+) \\|$");

    /** Le titre qui ouvre la table des homonymes, et celui qui ouvre la table des allocations. */
    private static final String SECTION_DES_HOMONYMES = "## 3.";

    private static final String SECTION_DES_ALLOCATIONS = "## 4.";

    /**
     * La seule ligne du registre qui pretende dire son etat courant - clause 5. Une forme figee, et
     * non une phrase libre : le fichier porte plusieurs mesures datees, chacune juste sur son
     * perimetre, et un motif qui attraperait de la prose confondrait un etat courant avec l'histoire
     * d'un comptage. C'est la lecon d'ADR-0015 appliquee au motif lui-meme.
     */
    private static final Pattern COMPTE_COURANT = Pattern.compile(
            "^\\*\\*Compte courant, verifie par la CI\\*\\* : `([0-9]+)` identifiants alloues,"
                    + " `([0-9]+)` prefixes distincts\\.$", Pattern.MULTILINE);

    /**
     * Le plafond du cliquet de la section 3 du registre, decide par l'amendement du 2026-09-08.
     * Etat a cette date : deux formes, l'homonyme d'encodage et l'identifiant fictif du depot fautif
     * de ce test, cite verbatim hors de sa propre source par le rapport de qualite de s31. Monte de
     * 1 a 2 ce jour-la.
     *
     * <p><b>Ce que ce nombre garde, et c'est la seule raison de l'ecrire.</b> Une ligne de la
     * section 3 <b>eteint la clause 2</b> pour l'identifiant qu'elle nomme, dans tout le depot. Sans
     * plafond, la reponse la moins couteuse a n'importe quel rouge de clause 2 serait d'ajouter une
     * ligne au registre - un fichier de gouvernance, sans diff de code, sans ADR. Avec lui, chaque
     * ecart de plus passe par ce fichier de test. Le plafond vaut exactement la taille de la dette
     * (ADR-0013, amendement du 2026-08-15) : il n'y a pas une place de libre, et le baisser demande
     * un diff aussi visible que le monter.
     */
    private static final int PLAFOND_D_HOMONYMES = 2;

    /** ADR-0013 obligation 3 : le depot fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src", "test", "resources", "contreexemple",
            "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest");

    private record Citation(String identifiant, Path fichier) {
    }

    private static String lireSiTexte(Path fichier) {
        byte[] octets;
        try {
            octets = Files.readAllBytes(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        for (byte octet : octets) {
            if (octet == 0) {
                return null;
            }
        }
        return new String(octets, StandardCharsets.UTF_8);
    }

    private static boolean estDansLePerimetre(Path racine, Path fichier) {
        for (Path partie : racine.relativize(fichier)) {
            String nom = partie.toString();
            if (nom.equals("target") || nom.equals("contreexemple") || nom.equals(".git")) {
                return false;
            }
        }
        return true;
    }

    private static List<Path> fichiersDuDepot(Path racine) {
        try (Stream<Path> arborescence = Files.walk(racine)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> estDansLePerimetre(racine, fichier))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Tout identifiant de condition ecrit dans le depot, avec le fichier qui l'ecrit. */
    static List<Citation> citationsDuDepot(Path racine) {
        List<Citation> citations = new ArrayList<>();
        for (Path fichier : fichiersDuDepot(racine)) {
            String contenu = lireSiTexte(fichier);
            if (contenu == null) {
                continue;
            }
            Matcher trouve = IDENTIFIANT.matcher(contenu);
            while (trouve.find()) {
                if (PREFIXES_DE_DECISION.contains(trouve.group(1))) {
                    continue;
                }
                citations.add(new Citation(trouve.group(1) + "-" + trouve.group(2),
                        racine.relativize(fichier)));
            }
        }
        return citations;
    }

    /**
     * Les identifiants qu'un fichier de test a le droit d'ecrire sans qu'ils soient alloues : ceux
     * que porte <b>son propre</b> arbre de contre-exemple. Un depot fictif ne peut pas etre fautif
     * si ses identifiants doivent etre alloues, et la source du garde-fou doit pouvoir les attendre
     * dans son assertion.
     *
     * <p>Ce trou est derive, jamais enumere : l'autorisation vaut pour le fichier {@code <Nom>.java}
     * et pour les seuls identifiants trouves sous {@code contreexemple/<Nom>/}. Elle ne s'etend a
     * aucun autre fichier, ni a un identifiant que le contre-exemple ne porte pas. <b>Defaut trouve
     * par ce garde-fou sur sa propre source, a sa premiere execution</b> - exactement comme
     * {@link ToutesLesReferencesDeDecisionResolventTest} avant lui, dont ce trou reprend la forme
     * plutot que d'en inventer une seconde.
     */
    private static Map<String, Set<String>> identifiantsDesContreExemples(Path racine) {
        Map<String, Set<String>> parTest = new LinkedHashMap<>();
        try (Stream<Path> arborescence = Files.walk(racine)) {
            List<Path> arbres = arborescence
                    .filter(Files::isDirectory)
                    .filter(chemin -> chemin.getParent() != null
                            && chemin.getParent().getFileName() != null
                            && chemin.getParent().getFileName().toString().equals("contreexemple"))
                    .toList();
            for (Path arbre : arbres) {
                Set<String> identifiants = parTest.computeIfAbsent(
                        arbre.getFileName().toString(), cle -> new TreeSet<>());
                try (Stream<Path> contenu = Files.walk(arbre)) {
                    for (Path fichier : contenu.filter(Files::isRegularFile).toList()) {
                        String texte = lireSiTexte(fichier);
                        if (texte == null) {
                            continue;
                        }
                        Matcher trouve = IDENTIFIANT.matcher(texte);
                        while (trouve.find()) {
                            identifiants.add(trouve.group(1) + "-" + trouve.group(2));
                        }
                    }
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return parTest;
    }

    /** Les allocations du registre, dans l'ordre du fichier, doublons compris - c'est la clause 1. */
    private static List<Map.Entry<String, String>> allocations(String registre) {
        List<Map.Entry<String, String>> lues = new ArrayList<>();
        boolean dansLaSection = false;
        for (String ligne : registre.split("\n", -1)) {
            if (ligne.startsWith("## ")) {
                dansLaSection = ligne.startsWith(SECTION_DES_ALLOCATIONS);
            }
            if (!dansLaSection) {
                continue;
            }
            Matcher trouve = ALLOCATION.matcher(ligne.strip());
            if (trouve.matches()) {
                lues.add(Map.entry(trouve.group(1), trouve.group(2)));
            }
        }
        return lues;
    }

    /** Les homonymes ecartes par la section 3, avec le motif qui les ecarte. */
    private static Map<String, String> homonymes(String registre) {
        Map<String, String> ecartes = new LinkedHashMap<>();
        boolean dansLaSection = false;
        for (String ligne : registre.split("\n", -1)) {
            if (ligne.startsWith("## ")) {
                dansLaSection = ligne.startsWith(SECTION_DES_HOMONYMES);
            }
            if (!dansLaSection) {
                continue;
            }
            Matcher trouve = HOMONYME.matcher(ligne.strip());
            if (trouve.matches()) {
                ecartes.put(trouve.group(1), trouve.group(2).strip());
            }
        }
        return ecartes;
    }

    static List<String> defautsDuRegistre(Path racine) {
        Path fichierDuRegistre = racine.resolve(REGISTRE);
        assertThat(fichierDuRegistre)
                .as("PAR-5 : le registre est la seule source de l'allocation ; sans lui, ce"
                        + " garde-fou ne juge rien (ADR-0051)")
                .isRegularFile();
        String registre = lireSiTexte(fichierDuRegistre);
        List<Map.Entry<String, String>> lues = allocations(registre);
        Map<String, String> ecartes = homonymes(registre);

        List<String> defauts = new ArrayList<>();

        Map<String, String> alloues = new TreeMap<>();
        for (Map.Entry<String, String> allocation : lues) {
            String precedent = alloues.putIfAbsent(allocation.getKey(), allocation.getValue());
            if (precedent != null) {
                defauts.add(String.format(
                        "%s alloue deux fois %s, a %s puis a %s. Un identifiant designe une condition"
                                + " et une seule : c'est exactement la collision du 2026-09-03, que"
                                + " seule une instance allant lire le meme espace de noms avait vue"
                                + " (PAR-5 ; ADR-0085, clause 1)",
                        REGISTRE, allocation.getKey(), precedent, allocation.getValue()));
            }
        }

        Map<String, Set<String>> parContreExemple = identifiantsDesContreExemples(racine);
        Set<String> deja = new TreeSet<>();
        for (Citation citation : citationsDuDepot(racine)) {
            String nomDuFichier = citation.fichier().getFileName().toString();
            if (nomDuFichier.endsWith(".java")) {
                Set<String> permis = parContreExemple.getOrDefault(
                        nomDuFichier.substring(0, nomDuFichier.length() - ".java".length()),
                        Set.of());
                if (permis.contains(citation.identifiant())) {
                    continue;
                }
            }
            if (alloues.containsKey(citation.identifiant())
                    || ecartes.containsKey(citation.identifiant())
                    || !deja.add(citation.identifiant())) {
                continue;
            }
            defauts.add(String.format(
                    "%s ecrit l'identifiant %s, que %s n'alloue pas. Un identifiant dont l'allocation"
                            + " n'est pas visible avant d'etre ecrit est un identifiant que la"
                            + " prochaine instance peut reprendre sans le savoir (PAR-5 ; ADR-0085,"
                            + " clause 2)",
                    citation.fichier(), citation.identifiant(), REGISTRE));
        }

        for (Map.Entry<String, String> allocation : alloues.entrySet()) {
            Path document = racine.resolve(allocation.getValue());
            if (!Files.isRegularFile(document)) {
                defauts.add(String.format(
                        "%s alloue %s dans %s, qui n'existe pas (ADR-0085, clause 3)",
                        REGISTRE, allocation.getKey(), allocation.getValue()));
                continue;
            }
            String contenu = lireSiTexte(document);
            if (contenu == null || !contenu.contains(allocation.getKey())) {
                defauts.add(String.format(
                        "%s alloue %s dans %s, qui ne l'ecrit pas. Une allocation qui pointe vers un"
                                + " document muet ne rend pas l'identifiant lisible, et elle pourrit"
                                + " sans que rien ne le dise (ADR-0085, clause 3)",
                        REGISTRE, allocation.getKey(), allocation.getValue()));
            }
        }

        Set<String> vivants = new TreeSet<>();
        for (Path fichier : fichiersDuDepot(racine)) {
            if (racine.relativize(fichier).toString().replace('\\', '/').equals(REGISTRE)) {
                continue;
            }
            String contenu = lireSiTexte(fichier);
            if (contenu == null) {
                continue;
            }
            for (String ecarte : ecartes.keySet()) {
                if (contenu.contains(ecarte)) {
                    vivants.add(ecarte);
                }
            }
        }
        for (String ecarte : ecartes.keySet()) {
            if (!vivants.contains(ecarte)) {
                defauts.add(String.format(
                        "%s ecarte %s comme homonyme, mais plus rien dans le depot ne l'ecrit. Une"
                                + " exemption qui ne nomme plus rien de reel n'est pas un cliquet"
                                + " (ADR-0085, clause 4)",
                        REGISTRE, ecarte));
            }
        }

        defauts.addAll(defautsDuCompteCourant(registre, alloues));
        return defauts;
    }

    /**
     * Clause 5 : le compte courant que le registre declare vaut celui de sa table. Deux instances
     * qui ne se lisent pas ne peuvent pas verifier ce nombre depuis leur moitie ; il est donc verifie
     * a la fusion, ou les deux moities existent, et nulle part ailleurs.
     */
    private static List<String> defautsDuCompteCourant(String registre, Map<String, String> alloues) {
        List<String> defauts = new ArrayList<>();
        Matcher declare = COMPTE_COURANT.matcher(registre);
        if (!declare.find()) {
            defauts.add(String.format(
                    "%s n'ecrit aucun compte courant sous la forme que la CI verifie. Un registre"
                            + " sans compte verifiable laisse a chaque instance le soin d'annoncer un"
                            + " nombre que sa moitie ne permet pas de calculer (ADR-0085, clause 5)",
                    REGISTRE));
            return defauts;
        }
        int identifiantsDeclares = Integer.parseInt(declare.group(1));
        int prefixesDeclares = Integer.parseInt(declare.group(2));
        long prefixes = alloues.keySet().stream()
                .map(identifiant -> identifiant.substring(0, identifiant.indexOf('-')))
                .distinct()
                .count();
        if (identifiantsDeclares != alloues.size() || prefixesDeclares != prefixes) {
            defauts.add(String.format(
                    "%s declare %d identifiants alloues et %d prefixes distincts, sa table en porte"
                            + " %d et %d. Un compte qu'aucune moitie ne peut verifier depuis sa"
                            + " branche est verifie a la fusion, ou les deux moities existent"
                            + " (ADR-0085, clause 5)",
                    REGISTRE, identifiantsDeclares, prefixesDeclares, alloues.size(), prefixes));
        }
        if (declare.find()) {
            defauts.add(String.format(
                    "%s ecrit deux comptes courants ; le second est le geste exact d'une instance qui"
                            + " ajoute le sien plutot que de corriger celui qu'elle trouve"
                            + " (ADR-0085, clause 5)",
                    REGISTRE));
        }
        return defauts;
    }

    static void verifierLeRegistre(Path racine) {
        assertThat(citationsDuDepot(racine))
                .as("ADR-0051 : sans identifiant dans l'arbre, ce garde-fou n'examine aucun objet")
                .isNotEmpty();
        assertThat(defautsDuRegistre(racine))
                .as("PAR-5 : tout identifiant de condition du depot est alloue une fois et une"
                        + " seule, et son allocation resout")
                .isEmpty();
    }

    @Test
    void leDepotReelEstCoherent() {
        verifierLeRegistre(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Les bornes sont
     * larges et basses : elles ne mesurent pas la dette, elles constatent qu'un vert est un vert sur
     * le tissu reel du depot et non sur un balayage qui n'a rien trouve a lire.
     */
    @Test
    void leBalayageExamineLeTissuReelDuDepot() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(citationsDuDepot(racine))
                .as("PAR-5 : le depot porte plus de dix mille occurrences d'identifiants de"
                        + " condition ; un balayage qui en trouverait une poignee ne lit pas ce"
                        + " qu'il croit lire")
                .hasSizeGreaterThan(10_000);
        assertThat(citationsDuDepot(racine).stream().map(Citation::identifiant).distinct().count())
                .as("PAR-5 : plusieurs centaines d'identifiants distincts")
                .isGreaterThan(400L);
    }

    /**
     * La derivation qui remplace une enumeration, constatee et non supposee. Les quatre familles
     * voisines - code de ligne de referentiel, decision, code de story, rang interne a un document -
     * sont ecrites ici sous leur forme reelle ; aucune ne doit produire de correspondance, sauf la
     * decision, qui en produit une des que son numero tient sur deux chiffres et que le prefixe
     * ecarte ensuite. C'est ce constat qui autorise le registre a ne pas les enumerer.
     */
    @Test
    void lesFormesVoisinesNeSontPasDesIdentifiantsDeCondition() {
        assertThat(IDENTIFIANT.matcher("HG-CM1-11 EMC-CP-02 QLM-CP-07 SCI-CM1-20").find())
                .as("ADR-0081 : un code de ligne a trois segments et n'entre pas dans ce registre")
                .isFalse();
        assertThat(IDENTIFIANT.matcher("S30-4 S6-14 S15-RETRO-1 S26-DETTE-2").find())
                .as("un code de story porte un chiffre dans son prefixe")
                .isFalse();
        assertThat(IDENTIFIANT.matcher("M5 C4 V3 R1").find())
                .as("un rang interne a un document n'a qu'une lettre de prefixe")
                .isFalse();
        assertThat(IDENTIFIANT.matcher("ADR-0085 DEC-0079").find())
                .as("une decision porte quatre chiffres, que cette forme ne reconnait pas")
                .isFalse();
        Matcher courte = IDENTIFIANT.matcher("ADR-85");
        assertThat(courte.find() && PREFIXES_DE_DECISION.contains(courte.group(1)))
                .as("ADR-0066 : c'est le prefixe, et non la seule forme, qui ecarte une decision")
                .isTrue();
    }

    /**
     * Le cliquet de la section 3 du registre. Ce test ne dit pas <i>quelles</i> formes sont ecartees
     * - le registre le dit, et un second exemplaire de cette liste rancirait (ADR-0061) - il dit
     * seulement <b>combien</b>. C'est ce qui empeche qu'un rouge de clause 2 se referme par une ligne
     * ajoutee au registre seul, sans diff de code et sans decision.
     */
    @Test
    void laTableDesHomonymesEcartesResteSousSonPlafond() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        String registre = lireSiTexte(racine.resolve(REGISTRE));
        assertThat(homonymes(registre))
                .as("ADR-0085, clause 4 : une ligne de la section 3 eteint la clause 2 pour"
                        + " l'identifiant qu'elle nomme. Le plafond vaut exactement la taille de la"
                        + " dette ; un ecart de plus se decide dans ce fichier de test, jamais dans"
                        + " le registre seul")
                .hasSize(PLAFOND_D_HOMONYMES);
    }

    /**
     * <b>La distinction que ce garde-fou refuse de faire, prouvee plutot qu'affirmee.</b> Le rapport
     * de qualite de s31 a fait rougir la clause 2 en citant verbatim, dans un bloc de code, le
     * message d'erreur d'une mutation - donc un identifiant fictif jamais alloue. L'amendement du
     * 2026-09-08 a tranche que <b>citer n'est pas moins qu'employer</b> : aucun contexte textuel -
     * bloc de code, guillemets, voisinage du mot "identifiant" - n'exempte quoi que ce soit, parce
     * qu'un tel contexte est exactement l'endroit ou un identifiant reellement non alloue passerait
     * sans bruit.
     *
     * <p>Le depot fautif porte donc un rapport de sprint qui ne fait <b>que citer</b>, sous la forme
     * meme du rapport reel, un identifiant qui n'est ni alloue ni ecarte. Il doit rougir. Le jour ou
     * quelqu'un ajoute une exemption de contexte a la clause 2, ce test tombe.
     */
    @Test
    void uneCitationDansUnMessageDErreurNEstPasExemptee() {
        List<String> defauts = defautsDuRegistre(CONTRE_EXEMPLE.resolve("depot-fautif"));
        assertThat(defauts)
                .as("ADR-0085, clause 2 et amendement du 2026-09-08 : un identifiant non alloue cite"
                        + " dans un message d'erreur reproduit verbatim est refuse comme n'importe"
                        + " quel autre")
                .anySatisfy(defaut -> assertThat(defaut)
                        .contains("rapport-fautif.md")
                        .contains("ecrit l'identifiant ZZZ-7"));
    }

    /**
     * ADR-0013 obligation 3. Le depot fautif porte les cinq defauts a la fois : un identifiant
     * ecrit sans etre alloue, un identifiant alloue deux fois, une allocation qui nomme un document
     * qui ne l'ecrit pas, un homonyme ecarte que plus rien ne porte, et un compte courant que sa
     * propre table dement.
     */
    @Test
    void unDepotFautifFaitEchouerLesCinqClauses() {
        assertThatThrownBy(() -> verifierLeRegistre(CONTRE_EXEMPLE.resolve("depot-fautif")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("alloue deux fois ZZZ-2")
                .hasMessageContaining("ecrit l'identifiant ZZZ-9")
                .hasMessageContaining("qui ne l'ecrit pas")
                .hasMessageContaining("ecarte ZZZ-8 comme homonyme")
                .hasMessageContaining("declare 9 identifiants alloues et 2 prefixes distincts, sa"
                        + " table en porte 3 et 1");
    }
}
