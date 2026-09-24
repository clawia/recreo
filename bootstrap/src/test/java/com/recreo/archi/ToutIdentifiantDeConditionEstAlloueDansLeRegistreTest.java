package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
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
 *       nomme ;</li>
 *   <li>annoncer un <b>compte courant</b> que la table dement - clause 5 ;</li>
 *   <li>allouer ici un nom qu'<b>ADR-0081 alloue deja</b> comme code de ligne de referentiel -
 *       clause 6, ajoutee le 2026-09-08 par ADR-0088. C'est la collision du 2026-09-03 a
 *       l'echelle de deux espaces de noms au lieu de deux instances ;</li>
 *   <li>ecrire un dixieme jeton dont le <b>marqueur de reserve n'est pas dans son dernier
 *       segment</b> - clauses 7 et 8, ajoutees le 2026-09-24 par ADR-0100. Le marqueur en tete
 *       donnait un silence, le marqueur au milieu un faux rouge ; les deux sont la meme convention
 *       violee, et les deux sont desormais comptees et gelees.</li>
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
 * fautif.
 *
 * <p><b>Les familles voisines, et comment chacune est ecartee - corrige le 2026-09-08
 * (ADR-0088, clause D).</b> Cette Javadoc disait que les <b>quatre</b> familles voisines
 * l'etaient <i>"par leur forme"</i>, codes de ligne de referentiel comprises. C'etait vrai de
 * trois d'entre elles et faux de la quatrieme, et la difference n'est pas academique : ecarter
 * les codes de ligne par la forme ecartait <b>toute</b> forme a trois segments, donc aussi les
 * identifiants de condition qui en portent une. Etat exact :
 * <ul>
 *   <li>un <b>code de story</b> porte un chiffre dans son prefixe, un <b>rang interne</b> n'a
 *       qu'une lettre de prefixe : ecartes par la forme, aucun des deux motifs ne les lit ;</li>
 *   <li>une <b>decision</b> est ecartee par son <b>prefixe</b>, parce qu'ADR-0066 l'alloue deja ;
 *   </li>
 *   <li>un <b>code de ligne de referentiel</b> est lu par
 *       {@link #IDENTIFIANT_A_TROIS_SEGMENTS} et ecarte par son <b>proprietaire</b> : ADR-0081
 *       l'alloue dans les referentiels, et ce garde-fou va y lire la liste plutot que d'en tenir
 *       une copie (ADR-0061).</li>
 * </ul>
 * {@link #lesFormesVoisinesNeSontPasDesIdentifiantsDeCondition()} constate chacun de ces trois
 * mecanismes plutot que de les supposer.
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
     * Un segment qui porte le marqueur de reserve et son numero, vu <b>suivi d'un autre segment</b>
     * - la place ou aucun identifiant de condition ne met son marqueur (ADR-0100). Ecrit une fois
     * et lu par trois motifs : la place du marqueur est la propriete, et trois copies d'une meme
     * propriete divergeraient.
     */
    private static final String SEGMENT_DE_MARQUEUR = "R[0-9]{1,2}-";

    /**
     * La forme d'un identifiant de condition : un prefixe de deux a cinq lettres majuscules, un
     * {@code R} facultatif qui distingue une reserve d'une condition, un numero d'un ou deux
     * chiffres. Les bornes interdisent qu'il soit colle a un mot, a un chiffre ou a un tiret : c'est
     * ce qui ecarte un code de ligne et un code de story sans avoir a les nommer.
     */
    private static final Pattern IDENTIFIANT = Pattern.compile(
            "(?<![A-Za-z0-9_/-])([A-Z]{2,5})-(R?[0-9]{1,2})(?![A-Za-z0-9_-])");

    /**
     * <b>La forme d'un identifiant a trois segments, et le trou qu'elle ferme</b> (ADR-0088,
     * clause D).
     *
     * <p><b>Le defaut, trouve le 2026-09-08 par nadia-prof en reparant un rouge que son propre
     * commit avait cause.</b> {@link #IDENTIFIANT} borne son prefixe et son numero par des
     * lookarounds qui refusent un tiret de part et d'autre - c'est ce qui ecarte un code de ligne
     * de referentiel sans avoir a le nommer. Effet de bord non vu : <b>aucun identifiant a trois
     * segments n'etait examine du tout.</b> Elle voulait glisser le niveau au milieu de
     * l'identifiant de sa barre de longueur d'enonce - donc trois segments, sur la forme d'un
     * {@code QLM-CP-07} ; un tel identifiant aurait ete <b>silencieusement ignore</b> par ce
     * garde-fou, c'est-a-dire non alloue sans que rien ne le dise - le faux vert exact que
     * {@code PAR-5} et ADR-0085 existent pour tuer. Elle a pris deux identifiants a deux segments
     * pour passer a cote du trou ; le trou est ferme ici plutot que contourne une seconde fois.</p>
     *
     * <p><b>Ce que la forme seule ne peut pas decider, et qui le decide.</b> Un identifiant de
     * condition a trois segments est ecrit <b>exactement</b> comme un code de ligne de referentiel,
     * {@code QLM-CP-07} : aucun trait de forme ne les distingue de facon fiable - le nombre de
     * chiffres du dernier segment n'en est pas un, rien n'interdisant a une condition d'atteindre
     * deux chiffres. Le partage se fait donc par <b>proprietaire</b> et non par forme :
     * un code que les referentiels declarent appartient a ADR-0081, tout autre code a trois segments
     * doit etre alloue ici. Les codes de ligne sont lus chez leur proprietaire
     * ({@code ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest.codesDeLigneAlloues}),
     * jamais recopies (ADR-0061), et la clause 6 refuse qu'un meme code ait les deux
     * proprietaires.</p>
     *
     * <p><b>Mesure du 2026-09-08 sur l'arbre du worktree, avant ce diff</b> : 209 codes a trois
     * segments distincts ecrits dans le depot, 209 declares par un referentiel, <b>zero</b> hors
     * referentiel. La population en faute de la clause 2 sur cette forme est donc vide aujourd'hui,
     * et c'est un cliquet a plafond zero, pas une regle a population vide : le balayage examine bien
     * 209 objets et il en trouve zero en faute (ADR-0051, ADR-0067).</p>
     *
     * <p><b>Le segment intermediaire est un niveau, et un niveau ne porte jamais le marqueur de
     * reserve</b> (ADR-0100, clause 8, le 2026-09-24). Sans cette borne, ce motif lisait
     * {@code <PREFIXE>-R<n>-<m>} comme un identifiant a trois segments - la notation d'<b>intervalle
     * collee</b> par laquelle {@code review.md} de s42 designe les reserves {@code HG-R23} a
     * {@code HG-R25} et {@code MOB-R1} a {@code MOB-R4}. <b>Ce n'etait pas un identifiant, et le
     * garde-fou en exigeait l'allocation : il a rougi sur {@code round-two} tout le temps qu'a dure
     * s42 apres le commit de la review.</b> Aucun des 689 identifiants alloues n'a un segment
     * intermediaire de cette forme - les niveaux servis s'ecrivent {@code CP}, {@code CE1},
     * {@code CM1} - donc la borne ne retranche aucun objet reel de la clause 2. Ce que la clause 2
     * cesse de lire, le cliquet d'ADR-0100 le compte : la notation n'est pas exemptee, elle change
     * de juge.</p>
     */
    private static final Pattern IDENTIFIANT_A_TROIS_SEGMENTS = Pattern.compile(
            "(?<![A-Za-z0-9_/-])([A-Z]{2,5})-(?!" + SEGMENT_DE_MARQUEUR + ")([A-Z]{1,3}[0-9]{0,2})"
                    + "-(R?[0-9]{1,2})(?![A-Za-z0-9_-])");

    /**
     * <b>La forme qu'aucun identifiant de condition ne prend : le marqueur de reserve en tete</b>
     * (ADR-0100, clause 7).
     *
     * <p><b>Le fait, du 2026-09-24 (s42).</b> Les deux verdicts de l'instance B de {@code
     * nadia-prof} ont ouvert leurs reserves sous la forme {@code R-<MATIERE>-<NIVEAU>-<ligne>-<n>},
     * cinq segments et le {@code R} en <b>premier</b> segment. Ni {@link #IDENTIFIANT} ni {@link
     * #IDENTIFIANT_A_TROIS_SEGMENTS} n'en lisent une seule, pour deux raisons independantes : le
     * premier segment {@code R} est en dessous du plancher {@code [A-Z]{2,5}}, et les lookarounds
     * des deux motifs refusent un tiret de part et d'autre, donc aucun sous-segment n'y est lu non
     * plus. <b>Ces jetons n'etaient donc pas non alloues : ils n'etaient pas examines du tout</b> -
     * le faux vert exact que {@code PAR-5} existe pour tuer, et le meme trou que la clause D
     * d'ADR-0088 a ferme pour trois segments. Les sept jetons de l'instance A du meme jour
     * ({@code HG-R23} a {@code HG-R25}, {@code MOB-R1} a {@code MOB-R4}) portent le marqueur dans
     * leur segment de numero, la forme que le registre alloue depuis toujours : ils ont rougi, et
     * ils ont ete alloues.
     *
     * <p><b>Ce motif ne rend pas cette forme allouable, et c'est la decision.</b> {@link
     * #FORME_ALLOUABLE} ne l'accueille pas : le registre ne sait pas ecrire une ligne pour un tel
     * jeton, donc la seule sortie verte pour une reserve neuve reste la forme conforme. Ce motif
     * sert a <b>compter</b> la dette existante et a la geler, sur le precedent d'ADR-0067 : on
     * arrete une dette avant de la corriger, et la corriger appartient a son auteur.
     */
    private static final Pattern MARQUEUR_DE_RESERVE_EN_TETE = Pattern.compile(
            "(?<![A-Za-z0-9_/-])R(?:-[A-Z0-9]{1,4}){2,}(?![A-Za-z0-9_-])");

    /**
     * <b>La seconde place ou le marqueur de reserve n'a rien a faire : au milieu</b> (ADR-0100,
     * clause 8).
     *
     * <p><b>Le fait, du 2026-09-24, et il est d'un autre auteur que le precedent.</b>
     * {@code review.md} de s42 designe deux plages de reserves par une <b>notation d'intervalle
     * collee</b>, {@code <PREFIXE>-R<premier>-<dernier>}. Ce n'est pas un identifiant, mais c'est
     * ecrit exactement comme un identifiant a trois segments, et {@link
     * #IDENTIFIANT_A_TROIS_SEGMENTS} en exigeait l'allocation : <b>la clause 2 a rougi sur
     * {@code round-two} des le commit de cette review</b>, et le rouge a survecu a la cloture du
     * sprint - reproduit sur {@code HEAD} sans aucune modification locale avant d'ecrire cette
     * ligne. C'est le symetrique exact du defaut precedent : la meme convention violee, la meme
     * classe de garde-fou, et un faux rouge la ou l'autre donnait un silence.
     *
     * <p><b>Pourquoi cette forme n'est pas rendue lisible a la clause 2, ni exemptee.</b> La rendre
     * allouable ferait entrer une notation dans l'espace des identifiants ; l'exempter la rendrait
     * invisible, c'est-a-dire la ramenerait au defaut de l'instance B. Elle est donc <b>retiree a
     * la clause 2 et donnee au cliquet</b> : elle change de juge, elle ne disparait pas.
     */
    private static final Pattern MARQUEUR_DE_RESERVE_AU_MILIEU = Pattern.compile(
            "(?<![A-Za-z0-9_/-])[A-Z]{2,5}-" + SEGMENT_DE_MARQUEUR
                    + "(?:[A-Z0-9]{1,4}-)*[A-Z0-9]{1,4}(?![A-Za-z0-9_-])");

    /**
     * Le plafond du cliquet d'ADR-0100 : <b>7</b> depuis le renommage de {@code nadia-prof} du
     * 2026-09-24, les deux places confondues. <b>Descendu de 9 a 7</b>, et il ne remonte jamais.
     *
     * <p><b>Methode du chiffre, dans le meme paragraphe.</b> Perimetre : les fichiers texte du
     * depot que {@link #fichiersDuDepot(Path)} enumere - repertoires de sortie de build, arbres de
     * contre-exemple et binaires exclus, ces derniers reconnus a l'octet nul (ADR-0036). Critere :
     * {@link #MARQUEUR_DE_RESERVE_EN_TETE} et {@link #MARQUEUR_DE_RESERVE_AU_MILIEU}. Resultat,
     * arbre du 2026-09-24 <b>apres le renommage et avant ce diff</b> : <b>7 jetons distincts, 38
     * occurrences, 7 fichiers</b> - cinq en tete ({@code R-EMC-CM1-05-1}, {@code R-EMC-CM1-05-2},
     * {@code R-MAT-CP-10-1} a {@code -3}) et deux au milieu (les notations d'intervalle de
     * {@code review.md}). Aucun ne vit plus dans une fiche comme identifiant courant : ils y sont
     * cites par les tables de correspondance, par deux ceremonies closes, par le registre et par
     * ADR-0100. <b>Mesure faite deux fois et par deux chemins</b> - un balayage independant des
     * sources, puis l'execution de ce test - parce qu'un plafond est un chiffre et qu'un chiffre
     * porte sa methode.
     *
     * <p><b>Etat precedent : 9, le 2026-09-24 avant le renommage</b> - 7 en tete (23 occurrences, 5
     * fichiers) et 2 au milieu (2 occurrences, 1 fichier). La mention {@code R-MAT-CP-10-1 bis}
     * n'etait pas un jeton de plus : l'espace ferme la chaine, et {@code bis} qualifie le jeton qui
     * precede.
     *
     * <p><b>L'ecart avec le 6 annonce, et il porte sa cause.</b> La note de renommage du registre
     * predit <b>6</b> et nomme {@code R-EMC-CM1-05-2} parmi les jetons <i>"qui disparaissent"</i> -
     * dans la phrase meme qui l'ecrit, donc qui le maintient vivant. Le compte etait juste sur
     * l'arbre d'avant son propre diff, et faux sur l'arbre qu'il produit. <b>C'est la troisieme fois
     * en deux jours qu'un chiffre de ce depot est mesure avant le diff qui le change</b> : la porte
     * technique de la review de s42, l'enumeration des sept jetons a la cloture, et celui-ci. Le
     * plafond vaut ce que le balayage trouve, jamais ce qu'un auteur prevoit.
     *
     * <p><b>Ce que ce nombre garde.</b> Il vaut exactement la dette, il n'y a pas une place de
     * libre (ADR-0013, amendement du 2026-08-15) : un huitieme jeton, sous l'une ou l'autre place,
     * ecrit n'importe ou dans le depot, fait rougir la CI. Le descendre demande un diff aussi
     * visible que le monter - <b>et ce diff-ci est la preuve que le cliquet fonctionne dans les deux
     * sens</b> : le renommage l'a fait rougir, il ne s'est pas efface tout seul.
     */
    private static final int PLAFOND_DE_JETONS_A_MARQUEUR_DEPLACE = 7;

    /** Les deux prefixes qu'ADR-0066 alloue deja, sur les noms de fichiers de squad/decisions/. */
    private static final Set<String> PREFIXES_DE_DECISION = Set.of("ADR", "DEC");

    /**
     * La forme d'un identifiant tel que le registre l'ecrit dans ses deux tables : deux segments, ou
     * trois quand la condition porte un niveau. Le segment intermediaire est facultatif depuis
     * ADR-0088 clause D - sans lui, un identifiant a trois segments n'aurait pas pu etre alloue,
     * donc n'aurait eu aucune sortie verte, et le garde-fou aurait interdit une forme au lieu de la
     * gouverner.
     *
     * <p><b>La meme borne de niveau que {@link #IDENTIFIANT_A_TROIS_SEGMENTS}</b> (ADR-0100, clause
     * 8) : sans elle, une notation d'intervalle collee comme celle de {@code review.md} de s42
     * etait <b>allouable</b>, et la facon la moins couteuse d'eteindre le rouge qu'elle causait
     * aurait ete de lui ajouter une ligne de registre - c'est-a-dire de faire entrer une notation
     * dans l'espace des identifiants.
     */
    private static final String FORME_ALLOUABLE =
            "[A-Z]{2,5}(?:-(?!" + SEGMENT_DE_MARQUEUR + ")[A-Z]{1,3}[0-9]{0,2})?-R?[0-9]{1,2}";

    /** Une ligne d'allocation du registre : un identifiant, un chemin de document. */
    private static final Pattern ALLOCATION = Pattern.compile(
            "^\\| `(" + FORME_ALLOUABLE + ")` \\| `([^`|]+)` \\|$");

    /** Une ligne d'homonyme ecarte : un identifiant apparent, et le motif qui l'ecarte. */
    private static final Pattern HOMONYME = Pattern.compile(
            "^\\| `(" + FORME_ALLOUABLE + ")` \\| ([^|]+) \\|$");

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

    /**
     * Tout identifiant de condition ecrit dans le depot, avec le fichier qui l'ecrit - <b>les deux
     * formes</b>, a deux et a trois segments (ADR-0088, clause D). Les deux motifs ne peuvent pas se
     * chevaucher : le lookbehind du motif a deux segments refuse un tiret devant le prefixe, donc les
     * deux derniers segments d'un {@code QLM-CP-07} n'y sont jamais lus comme un identifiant a deux
     * segments.
     */
    static List<Citation> citationsDuDepot(Path racine) {
        List<Citation> citations = new ArrayList<>();
        for (Path fichier : fichiersDuDepot(racine)) {
            String contenu = lireSiTexte(fichier);
            if (contenu == null) {
                continue;
            }
            for (String identifiant : identifiantsDe(contenu)) {
                citations.add(new Citation(identifiant, racine.relativize(fichier)));
            }
        }
        return citations;
    }

    /**
     * Les identifiants qu'un texte ecrit, sous les deux formes, les deux prefixes de decision
     * ecartes. Un seul endroit lit les motifs : sans cela, la forme a trois segments serait entree
     * dans un balayage et pas dans l'autre - c'est-a-dire dans le perimetre reel et pas dans le trou
     * de contre-exemple, ou l'inverse.
     */
    private static List<String> identifiantsDe(String contenu) {
        List<String> trouves = new ArrayList<>();
        for (Pattern motif : List.of(IDENTIFIANT, IDENTIFIANT_A_TROIS_SEGMENTS)) {
            Matcher trouve = motif.matcher(contenu);
            while (trouve.find()) {
                if (PREFIXES_DE_DECISION.contains(trouve.group(1))) {
                    continue;
                }
                StringBuilder identifiant = new StringBuilder(trouve.group(1));
                for (int groupe = 2; groupe <= trouve.groupCount(); groupe++) {
                    identifiant.append('-').append(trouve.group(groupe));
                }
                trouves.add(identifiant.toString());
            }
        }
        return trouves;
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
        return desContreExemples(racine,
                ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest::identifiantsDe);
    }

    /**
     * La meme derivation, appliquee a ce que la lecture passee en argument trouve. Les deux familles
     * de jetons - ceux de la clause 2 et ceux du cliquet d'ADR-0100 - partagent cette autorisation
     * sans partager leur lecture : un jeton a marqueur en tete ne doit pas entrer dans les citations
     * de la clause 2, qui exigerait alors son allocation sous une forme que le registre ne sait pas
     * ecrire.
     */
    private static Map<String, Set<String>> desContreExemples(
            Path racine, Function<String, Collection<String>> lecture) {
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
                        identifiants.addAll(lecture.apply(texte));
                    }
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return parTest;
    }

    /**
     * Les jetons distincts qui portent leur marqueur de reserve en tete, sur le meme perimetre de
     * fichiers que le balayage des identifiants - ADR-0100, clause 7. La lecture du perimetre n'est
     * pas dupliquee : un second avis sur ce qu'est un fichier du depot divergerait du premier.
     */
    static Set<String> jetonsAMarqueurDeReserveDeplace(Path racine) {
        Map<String, Set<String>> parContreExemple = desContreExemples(
                racine, ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest::jetonsDe);
        Set<String> jetons = new TreeSet<>();
        for (Path fichier : fichiersDuDepot(racine)) {
            String contenu = lireSiTexte(fichier);
            if (contenu == null) {
                continue;
            }
            String nomDuFichier = fichier.getFileName().toString();
            Set<String> permis = nomDuFichier.endsWith(".java")
                    ? parContreExemple.getOrDefault(
                            nomDuFichier.substring(0, nomDuFichier.length() - ".java".length()),
                            Set.of())
                    : Set.of();
            for (String jeton : jetonsDe(contenu)) {
                if (!permis.contains(jeton)) {
                    jetons.add(jeton);
                }
            }
        }
        return jetons;
    }

    /** Les jetons dont le marqueur de reserve n'est pas a sa place, les deux places confondues. */
    private static Collection<String> jetonsDe(String contenu) {
        List<String> trouves = new ArrayList<>();
        for (Pattern motif : List.of(MARQUEUR_DE_RESERVE_EN_TETE, MARQUEUR_DE_RESERVE_AU_MILIEU)) {
            Matcher trouve = motif.matcher(contenu);
            while (trouve.find()) {
                trouves.add(trouve.group());
            }
        }
        return trouves;
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
        Set<String> codesDeLigne =
                ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest.codesDeLigneAlloues(racine);
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
                    || codesDeLigne.contains(citation.identifiant())
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

        for (String identifiant : alloues.keySet()) {
            if (codesDeLigne.contains(identifiant)) {
                defauts.add(String.format(
                        "%s alloue %s, que les referentiels de squad/programme declarent deja comme"
                                + " code de ligne. Deux proprietaires pour un meme nom est la"
                                + " collision du 2026-09-03 a l'echelle de deux espaces de noms :"
                                + " ADR-0081 alloue les codes de ligne, ADR-0085 les identifiants de"
                                + " condition, et un nom ne peut pas etre gouverne par les deux"
                                + " (ADR-0085, clause 6)",
                        REGISTRE, identifiant));
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
        assertThat(citationsDuDepot(racine).stream()
                .map(Citation::identifiant)
                .filter(identifiant -> identifiant.chars().filter(lettre -> lettre == '-').count() == 2)
                .distinct()
                .count())
                .as("ADR-0088 clause D : la forme a trois segments doit etre reellement examinee."
                        + " Sans cette borne, elargir le motif et ne rien lire du tout rendrait le"
                        + " meme vert qu'avant l'elargissement - le faux vert exact que ce diff paie")
                .isGreaterThan(100L);
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
                .as("un code de ligne a trois segments : le motif a deux segments ne le lit pas, et"
                        + " c'est ce qui laissait un identifiant de condition a trois segments hors"
                        + " de tout balayage jusqu'au 2026-09-08")
                .isFalse();
        assertThat(IDENTIFIANT_A_TROIS_SEGMENTS.matcher("QLM-CP-07").find())
                .as("ADR-0088 clause D : un code a trois segments est desormais lu, puis attribue a"
                        + " son proprietaire - ce qu'aucun trait de forme ne saurait faire, un"
                        + " identifiant de condition portant un segment de niveau s'ecrivant"
                        + " exactement comme ce code de ligne-ci")
                .isTrue();
        assertThat(IDENTIFIANT_A_TROIS_SEGMENTS.matcher("HG-R23-25 MOB-R1-4").find())
                .as("ADR-0100 clause 8 : une notation d'intervalle collee n'est pas un identifiant a"
                        + " trois segments. Son segment intermediaire porte le marqueur de reserve,"
                        + " la ou un identifiant porte un niveau - c'est le faux rouge reel de s42")
                .isFalse();
        assertThat(IDENTIFIANT_A_TROIS_SEGMENTS.matcher("S15-RETRO-1 S26-DETTE-2").find())
                .as("un code de story reste ecarte par sa forme, sous les deux motifs")
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
     * <b>ADR-0013 obligation 3, et c'est la morsure qui paie le trou du 2026-09-08.</b> Le depot
     * fautif porte un arbitrage qui ecrit un identifiant a <b>trois segments</b> que son registre
     * n'alloue pas et qu'aucun referentiel ne declare - il n'a d'ailleurs pas de referentiel du tout.
     * Avant ce diff, ce fichier passait <b>vert</b> : le motif ne lisait aucune forme a trois
     * segments, donc le fautif n'etait meme pas examine.
     *
     * <p>Le jour ou quelqu'un remet une borne de forme qui ecarte les trois segments, ce test
     * tombe.</p>
     */
    @Test
    void unIdentifiantATroisSegmentsNonAlloueEstRefuse() {
        List<String> defauts = defautsDuRegistre(CONTRE_EXEMPLE.resolve("depot-fautif"));
        assertThat(defauts)
                .as("ADR-0085 clause 2, ADR-0088 clause D : un identifiant de condition a trois"
                        + " segments est alloue comme n'importe quel autre, ou il n'existe pas")
                .anySatisfy(defaut -> assertThat(defaut)
                        .contains("ARBITRAGE-fautif.md")
                        .contains("ecrit l'identifiant ZZZ-CP-3"));
    }

    /**
     * <b>ADR-0100 : le cliquet exact sur les jetons dont le marqueur de reserve n'est pas a sa
     * place.</b>
     *
     * <p>Ce test ne juge pas une forme imaginee : il compte, sur l'arbre reel, les jetons de deux
     * formes que la clause 2 ne lit pas, et il refuse le dixieme. C'est la morsure qui manquait le
     * 2026-09-24, ou sept jetons ont traverse un sprint entier sans qu'un seul rouge ne les nomme -
     * trouves a la main a la cloture, par deux agents separement, et pas par la CI.
     *
     * <p><b>Le renommage n'est pas ici, et c'est voulu.</b> Les jetons appartiennent a des fiches
     * de conformite et a des fichiers de ceremonie que d'autres agents signent ; ce fichier gele
     * leur nombre, il ne reecrit rien.
     */
    @Test
    void aucunJetonNeDeplaceSonMarqueurDeReserveAuDelaDeLaDetteGelee() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(jetonsAMarqueurDeReserveDeplace(racine))
                .as("ADR-0100 : un identifiant de condition porte son marqueur de reserve dans son"
                        + " dernier segment, jamais en tete ni au milieu. La dette du 2026-09-24 est"
                        + " gelee a sa taille exacte ; un jeton de plus se decide dans ce fichier de"
                        + " test, jamais dans une fiche ni dans un rapport seul")
                .hasSize(PLAFOND_DE_JETONS_A_MARQUEUR_DEPLACE);
    }

    /**
     * <b>Pourquoi ces jetons echappaient, constate et non affirme - les deux places.</b> Un jeton a
     * marqueur en tete n'est lu par aucun motif de la clause 2 et n'est pas allouable : il n'avait
     * ni rouge, ni sortie verte, ni existence. Un jeton a marqueur au milieu etait, lui, lu <b>et
     * allouable</b> : il donnait un faux rouge, que la ligne de registre la moins couteuse aurait
     * eteint en faisant entrer une notation dans l'espace des identifiants. La forme conforme reste
     * lue et allouable dans les deux cas. Le jour ou quelqu'un elargit {@link #FORME_ALLOUABLE}
     * pour accueillir l'une des deux formes deviantes, ce test tombe.
     */
    @Test
    void unMarqueurDeplaceNEstNiLuNiAllouableAlorsQueLaFormeConformeLEst() {
        for (String deviant : List.of("R-EMC-CM1-05-1", "HG-R23-25", "MOB-R1-4")) {
            assertThat(identifiantsDe(deviant))
                    .as("aucun motif de la clause 2 ne lit %s : le marqueur n'y est pas a sa place,"
                            + " et un jeton dont le marqueur n'est pas a sa place n'est pas un"
                            + " identifiant", deviant)
                    .isEmpty();
            assertThat(deviant.matches(FORME_ALLOUABLE))
                    .as("le registre ne sait pas ecrire une ligne pour %s : il n'a aucune sortie"
                            + " verte, et c'est ce qui en fait un renommage plutot qu'une"
                            + " allocation", deviant)
                    .isFalse();
            assertThat(jetonsDe(deviant))
                    .as("ADR-0100 : %s est desormais compte, donc gele - il change de juge, il ne"
                            + " disparait pas", deviant)
                    .containsExactly(deviant);
        }
        assertThat(identifiantsDe("ZZZ-CP-R1 HG-R23 MOB-R1 ADD-CE1-R1"))
                .as("la forme conforme, marqueur dans le dernier segment, reste lue par la clause"
                        + " 2 - a deux comme a trois segments. Les trois derniers sont des jetons"
                        + " reels du 2026-09-24 et de s38, pas des fixtures de ma main")
                .containsExactlyInAnyOrder("HG-R23", "MOB-R1", "ZZZ-CP-R1", "ADD-CE1-R1");
        assertThat("ZZZ-CP-R1".matches(FORME_ALLOUABLE))
                .as("et elle est allouable, donc elle a une sortie verte")
                .isTrue();
        assertThat(jetonsDe("ZZZ-CP-R1 HG-R23 ADD-CE1-R1"))
                .as("le cliquet ne compte aucune forme conforme, sans quoi son plafond ne"
                        + " mesurerait pas la dette")
                .isEmpty();
    }

    /**
     * ADR-0013 obligation 3 : la morsure sur un arbre de contre-exemple. Le depot fautif porte une
     * fiche qui ouvre une reserve sous chacune des deux formes deviantes ; le compteur du cliquet
     * doit voir les deux. Sans ce constat, un plafond de neuf pourrait etre tenu par un compteur
     * qui ne lit rien du tout.
     */
    @Test
    void lesDeuxFormesAMarqueurDeplaceSontVuesDansUnDepotFautif() {
        assertThat(jetonsAMarqueurDeReserveDeplace(CONTRE_EXEMPLE.resolve("depot-fautif")))
                .as("ADR-0100 : le compteur lit les deux places la ou elles sont ecrites")
                .containsExactly("R-ZZZ-CP-01-1", "ZZZ-R9-9");
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
