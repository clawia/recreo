package com.recreo.adapter.content;

import com.recreo.adapter.content.PaquetLu.Item;
import com.recreo.adapter.content.PaquetLu.Paquet;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-QLM-CE1-06, le paquet {@code ce1-qlm-aimant}, ligne {@code QLM-CE1-06} - reconnaitre les objets
 * qu'un aimant attire et ceux qu'il n'attire pas. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, 3.6 :
 * {@code AIM-CE1-1}, {@code AIM-CE1-2}) et regles de contenu du critere 10 de la story
 * ({@code arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md}, 1.6, corrige par
 * {@code arbitrage-nadia-contradictions-ce1-qlm-06-08.md},
 * {@code arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md} et
 * {@code arbitrage-nadia-trois-points-ce1-qlm-04-06-10.md}). Les conditions transverses sont gardees une
 * fois pour les neuf lots par {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit les gardes</b> ({@code avis-theo-palier-5-gardes-ce1-qlm-06-08.md},
 * {@code g1} a {@code g10} ; {@code avis-theo-palier-5-ajustements-gardes-ce1-qlm-06-08.md}, {@code g11} ;
 * {@code avis-theo-palier-5-dernier-ajustement-gardes-ce1-qlm-05-a-12.md}, {@code G9}, {@code G10} ;
 * {@code avis-theo-palier-5-exemption-aim-ce1-2.md}). La tache d'un item (A, l'objet ; B, le groupe) se
 * lit sur son enonce, jamais sur une declaration ; la liste fermee de seize objets est dans ce test avec
 * sa classe, et l'en-tete du paquet la relit avec la source de chaque objet. Un enonce hors des deux
 * formes fermees est refuse ; un objet hors de la liste aussi.
 *
 * <p><b>Ce que ce test ne garde pas</b> ({@code QC9}, lu par nadia-prof) : l'exactitude de chaque objet
 * contre sa classe ({@code AIM-CE1-1}, {@code QMT-CE1-2}) ; la forme, la taille et l'usage que deux
 * distracteurs de A partagent sans les partager avec la bonne reponse ; le partage d'objets entre les
 * groupes de B ; qu'un indice soit une methode, qu'il dise d'ecarter ce qui n'est pas en metal ; que
 * {@code fil de fer} ne devienne pas le signe du bon groupe ; {@code dore} ; les quatre cas
 * d'{@code AIM-CE1-2} que la garde ne voit pas (paraphrases sans {@code attirer}, metaux nommes presentes
 * comme attires, designation hors des quatre formes, negation fausse sans designation) ; l'operation
 * mentale de {@code FER-3}.
 */
class PaquetCe1QlmAimantTest {

    private static final String PAQUET = "ce1-qlm-aimant";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmAimantTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    private enum Classe {
        FER("attire"), METAL("metal non attire"), SANS_METAL("sans metal");

        private final String libelle;

        Classe(String libelle) {
            this.libelle = libelle;
        }
    }

    private enum Tache {
        OBJET("objet"), GROUPE("groupe");

        private final String cle;

        Tache(String cle) {
            this.cle = cle;
        }
    }

    /**
     * QMT-CE1-2, regle 6 : la liste fermee des seize objets, nom sans article et sans accent vers sa classe.
     * Elle recopie la liste de nadia-prof ; le paquet l'ecrit dans son en-tete avec la source de chacun.
     */
    private static final Map<String, Classe> OBJETS = Map.ofEntries(
            Map.entry("clou", Classe.FER), Map.entry("trombone", Classe.FER), Map.entry("epingle", Classe.FER),
            Map.entry("aiguille", Classe.FER), Map.entry("agrafe", Classe.FER), Map.entry("fil de fer", Classe.FER),
            Map.entry("papier d'aluminium", Classe.METAL), Map.entry("barquette en aluminium", Classe.METAL),
            Map.entry("fil de cuivre", Classe.METAL),
            Map.entry("gomme", Classe.SANS_METAL), Map.entry("bouchon en liege", Classe.SANS_METAL),
            Map.entry("regle en plastique", Classe.SANS_METAL), Map.entry("bille en verre", Classe.SANS_METAL),
            Map.entry("feuille de papier", Classe.SANS_METAL), Map.entry("elastique", Classe.SANS_METAL),
            Map.entry("pate a modeler", Classe.SANS_METAL));

    private static final String PREMISSE = "(?:ces trois objets sont en metal|tous ces objets sont en metal)\\.";

    private static final Pattern DEBUT_DE_PREMISSE = Pattern.compile("^" + PREMISSE, FLAGS);

    private static final Pattern FORME_A = Pattern.compile("^(?:" + PREMISSE
            + " )?lequel(?: de ces objets)? l'aimant (attire-t-il|n'attire-t-il pas) ?\\?$", FLAGS);

    private static final Pattern FORME_B = Pattern.compile(
            "^tous ces objets sont en metal\\. dans quel groupe l'aimant attire-t-il les trois objets ?\\?$", FLAGS);

    private static final Pattern OBJET_AVEC_ARTICLE = Pattern.compile("^(?:le |la |l')(.+)$");

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(objet|groupe) : .*\\((\\d+) items?");

    private static final Pattern LIGNE_DE_CLASSE = Pattern.compile(
            "^#\\s+classe (.+?) : (attire|metal non attire|sans metal) ; source : (\\S.*)$");

    private static final Pattern LIGNE_DE_CLASSE_OUVERTE = Pattern.compile("^#\\s+classe\\s");

    /** Regle 6 : une source qui est un test du depot est circulaire. */
    private static final Pattern SOURCE_D_UN_TEST = Pattern.compile("Test(?![\\p{L}\\p{N}])");

    /** Regle 5 : les mots des objets exclus, sur le texte sans accent, dans tout sens. */
    private static final Pattern EXCLU = Pattern.compile("(?<!\\p{L})(pieces?|monnaies?|canettes?|cles?|clefs?"
            + "|couverts?|cuilleres?|cuillers?|fourchettes?|couteaux?|casseroles?|bijoux?|bagues?|colliers?"
            + "|bracelets?|or|aciers?)(?!\\p{L})", FLAGS);

    private static final Pattern INDICE_INTRUS = Pattern.compile(
            "(?<!\\p{L})(?:intrus|different(?:e|s|es)? des (?:deux )?autres)(?!\\p{L})", FLAGS);

    private static final Set<String> MOTS_VIDES = Set.of("de", "d", "en", "a", "du", "au", "l", "le", "la");

    // AIM-CE1-2, forme de avis-theo-palier-5-exemption-aim-ce1-2.md, section 1. Ces motifs lisent le texte
    // accentue : les formes sans accent de metal sont refusees par AucunMotServiNeSEcarteDeSonAccentTest.

    private static final Pattern ATTRACTION = Pattern.compile("(?<!\\p{L})attir\\p{L}*", FLAGS);

    private static final String DESIGNATION_GENERALE = "(?:le\\s+métal|métaux|métalliques"
            + "|ce\\s+qui\\s+est\\s+(?:en\\s+métal|métallique)|(?:les|des)\\s+\\p{L}+\\s+en\\s+métal)";

    private static final Pattern DESIGNATION = Pattern.compile(
            "(?<!\\p{L})" + DESIGNATION_GENERALE + "(?!\\p{L})", FLAGS);

    private static final Pattern NEGATION_DU_TOUT = Pattern.compile(
            "(?<!\\p{L})pas\\s+(?:tous|toutes|tout)\\s+(?:les\\s+(?:\\p{L}+\\s+)?)?" + DESIGNATION_GENERALE
                    + "(?!\\p{L})"
                    + "|(?<!\\p{L})" + DESIGNATION_GENERALE + "\\s+ne\\s+sont\\s+pas\\s+(?:tous|toutes)(?!\\p{L})",
            FLAGS);

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final String ESPACE_ORDINAIRE_AVANT_PONCTUATION = "[^\\u00A0] [?:;!]";

    private static final Set<String> CLES_SERVIES = Set.of("enonce", "bonneReponse", "distracteurs", "indice");

    private static final int LA_MOINS_SERVIE_AU_MOINS = 3;

    private static Paquet paquet() {
        return PaquetLu.lirePaquet(FICHIER_CONTENU);
    }

    private static Paquet fautif(String nom) {
        return PaquetLu.lirePaquet(CONTRE_EXEMPLE.resolve(nom));
    }

    /** Les items du jeu fautif qui portent ce marqueur en commentaire, pour mordre un verificateur a la fois. */
    private static List<Item> sonde(Paquet fautif, String marqueur) {
        return fautif.items().stream().filter(item -> item.commentaires().stream()
                .anyMatch(ligne -> ligne.equals(marqueur) || ligne.startsWith(marqueur + " "))).toList();
    }

    private static Item uneSonde(Paquet fautif, String marqueur) {
        List<Item> trouvees = sonde(fautif, marqueur);
        assertThat(trouvees).as(marqueur).hasSize(1);
        return trouvees.get(0);
    }

    private static Item sondeDuJeu(String marqueur) {
        return uneSonde(fautif("aimant-fautif.yaml"), marqueur);
    }

    private static void refuse(ThrowingCallable appel, String... morceaux) {
        var assertion = assertThatThrownBy(appel).isInstanceOf(AssertionError.class);
        for (String morceau : morceaux) {
            assertion.hasMessageContaining(morceau);
        }
    }

    private static Item unItem(String enonce, String bonneReponse, String premier, String second, String indice) {
        return new Item(enonce, bonneReponse, List.of(premier, second), indice, CLES_SERVIES, List.of());
    }

    private static List<Item> repete(Item item, int fois) {
        return new ArrayList<>(Collections.nCopies(fois, item));
    }

    // ----- normalisation

    /** Minuscule, sans accent, apostrophe droite, blancs (insecables compris) ramenes a une espace. */
    static String plat(String texte) {
        String plat = PaquetLu.sansAccent(texte).replace('’', '\'');
        return BLANCS.matcher(plat).replaceAll(" ").strip();
    }

    private static String sansPointFinal(String texte) {
        return texte.endsWith(".") || texte.endsWith("!") ? texte.substring(0, texte.length() - 1).strip() : texte;
    }

    // ----- la tache et les objets, lus sur l'enonce et les propositions

    static Optional<Tache> tache(Item item) {
        String enonce = plat(item.enonce());
        if (FORME_A.matcher(enonce).matches()) {
            return Optional.of(Tache.OBJET);
        }
        if (FORME_B.matcher(enonce).matches()) {
            return Optional.of(Tache.GROUPE);
        }
        return Optional.empty();
    }

    private static boolean estA(Item item) {
        return tache(item).equals(Optional.of(Tache.OBJET));
    }

    private static boolean estB(Item item) {
        return tache(item).equals(Optional.of(Tache.GROUPE));
    }

    /** La polarite d'un item de A : vrai pour "attire-t-il", faux pour "n'attire-t-il pas". */
    static boolean demandeLAttire(Item item) {
        Matcher forme = FORME_A.matcher(plat(item.enonce()));
        assertThat(forme.matches()).as("'%s' n'est pas un item de A", item.enonce()).isTrue();
        return forme.group(1).equals("attire-t-il");
    }

    /** Les noms d'objets d'une proposition : un en A, sans son article ; trois en B. */
    static List<String> nomsDe(Item item, String proposition) {
        String texte = sansPointFinal(plat(proposition));
        if (estA(item)) {
            Matcher objet = OBJET_AVEC_ARTICLE.matcher(texte);
            return List.of(objet.matches() ? objet.group(1) : texte);
        }
        return Arrays.stream(texte.split("\\s*,\\s*")).toList();
    }

    private static Classe classe(String nom) {
        Classe classe = OBJETS.get(nom);
        if (classe == null) {
            throw new AssertionError("g1 : « " + nom + " » n'est pas dans la liste fermée des objets");
        }
        return classe;
    }

    static List<Classe> classesDe(Item item, String proposition) {
        return nomsDe(item, proposition).stream().map(PaquetCe1QlmAimantTest::classe).toList();
    }

    private static Classe classeDeLaBonneReponse(Item item) {
        return classesDe(item, item.bonneReponse()).get(0);
    }

    private static String matiere(String nom) {
        return switch (classe(nom)) {
            case FER -> "fer";
            case METAL -> nom.contains("aluminium") ? "aluminium" : "cuivre";
            case SANS_METAL -> "";
        };
    }

    // ----- g1 : la liste fermee, dans les propositions et dans l'en-tete

    /** g1 : chaque proposition a la forme de sa tache, et chaque nom est un objet de la liste, par egalite. */
    static void verifierLaListeFermee(List<Item> items) {
        for (Item item : items) {
            for (String proposition : item.propositions()) {
                String texte = sansPointFinal(plat(proposition));
                if (estA(item)) {
                    Matcher objet = OBJET_AVEC_ARTICLE.matcher(texte);
                    assertThat(objet.matches() && OBJETS.containsKey(objet.group(1)))
                            .as("g1 : « %s » (proposition de '%s') n'est pas un objet de la liste fermée, avec son "
                                    + "article", proposition, item.enonce())
                            .isTrue();
                } else {
                    String[] noms = texte.split("\\s*,\\s*");
                    assertThat(noms.length == 3 && Arrays.stream(noms).allMatch(OBJETS::containsKey))
                            .as("g1 : « %s » (proposition de '%s') n'est pas trois objets de la liste fermée, sans "
                                    + "article, séparés par des virgules", proposition, item.enonce())
                            .isTrue();
                }
            }
        }
    }

    /**
     * g1, QMT-CE1-2 : chaque ligne de classe de l'en-tete nomme un objet de la liste avec la classe du test et
     * une source qui n'est pas un test ; chaque objet employe par un item a sa ligne.
     */
    static void verifierLesLignesDeClasse(String entete, List<Item> items) {
        Map<String, String> lignes = new LinkedHashMap<>();
        for (String ligne : entete.split("\\R")) {
            if (!LIGNE_DE_CLASSE_OUVERTE.matcher(ligne).find()) {
                continue;
            }
            Matcher classe = LIGNE_DE_CLASSE.matcher(ligne);
            assertThat(classe.matches())
                    .as("g1 : la ligne « %s » de la liste des objets n'a pas le format « classe nom : classe ; "
                            + "source : ... »", ligne)
                    .isTrue();
            String nom = plat(classe.group(1));
            assertThat(OBJETS).as("g1 : l'en-tête nomme « %s », hors de la liste fermée des objets", nom)
                    .containsKey(nom);
            assertThat(classe.group(2))
                    .as("g1 : la ligne de « %s » dit « %s », la liste fermée le range « %s »", nom, classe.group(2),
                            OBJETS.get(nom).libelle)
                    .isEqualTo(OBJETS.get(nom).libelle);
            assertThat(SOURCE_D_UN_TEST.matcher(classe.group(3)).find())
                    .as("g1 : la source de « %s » (%s) nomme un test, elle est circulaire", nom, classe.group(3))
                    .isFalse();
            lignes.put(nom, classe.group(3));
        }
        assertThat(lignes).as("g1 : l'en-tête du paquet n'écrit aucune liste des objets").isNotEmpty();
        Set<String> employes = new TreeSet<>();
        for (Item item : items) {
            for (String proposition : item.propositions()) {
                employes.addAll(nomsDe(item, proposition));
            }
        }
        employes.forEach(nom -> assertThat(lignes)
                .as("g1 : « %s » est employé par un item et n'a pas de ligne de classe dans l'en-tête", nom)
                .containsKey(nom));
    }

    // ----- g2 : les objets exclus

    /** g2 : aucun mot d'un objet exclu, dans aucun sens, dans l'enonce, l'indice ou une proposition. */
    static void verifierLesExclus(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher exclu = EXCLU.matcher(plat(champ));
                boolean trouve = exclu.find();
                assertThat(trouve)
                        .as("g2 : « %s » (champ de '%s') porte un mot de la liste des objets exclus : %s", champ,
                                item.enonce(), trouve ? exclu.group(1) : "")
                        .isFalse();
            }
        }
    }

    // ----- g3 : une seule proposition acceptable en A

    /** g3 : en A, une seule proposition est attiree (ou non attiree, selon la polarite), et c'est la bonne reponse. */
    static void verifierUneSeuleAcceptable(List<Item> items) {
        for (Item item : items) {
            if (!estA(item)) {
                continue;
            }
            boolean attire = demandeLAttire(item);
            long acceptables = item.propositions().stream()
                    .filter(proposition -> (classesDe(item, proposition).get(0) == Classe.FER) == attire).count();
            boolean bonneAcceptable = (classeDeLaBonneReponse(item) == Classe.FER) == attire;
            assertThat(acceptables == 1 && bonneAcceptable)
                    .as("g3 : '%s' (%s) n'a pas une seule proposition acceptable, ou ce n'est pas la bonne réponse",
                            item.enonce(), attire ? "attire" : "n'attire pas")
                    .isTrue();
        }
    }

    // ----- g4, G9 : le quart

    /**
     * Regle 1 : un item compte pour le quart quand l'enonce porte la premisse, que tous les objets de toutes les
     * propositions sont en metal, et qu'au moins un est en aluminium ou en cuivre. Une seule fonction pour A et B.
     */
    static boolean compte(Item item) {
        if (!DEBUT_DE_PREMISSE.matcher(plat(item.enonce())).find()) {
            return false;
        }
        List<Classe> classes = item.propositions().stream()
                .flatMap(proposition -> classesDe(item, proposition).stream()).toList();
        return classes.stream().noneMatch(classe -> classe == Classe.SANS_METAL) && classes.contains(Classe.METAL);
    }

    /** g4 : au moins un quart des items comptent. G9 : au moins un item de A compte, A n'est pas fait de croyances. */
    static void verifierLeQuart(List<Item> items) {
        long comptes = items.stream().filter(PaquetCe1QlmAimantTest::compte).count();
        long attendus = (items.size() + 3) / 4;
        assertThat(comptes)
                .as("AIM-CE1-2 : %d items comptent pour le quart sur %d, il en faut au moins %d", comptes,
                        items.size(), attendus)
                .isGreaterThanOrEqualTo(attendus);
        long deA = items.stream().filter(PaquetCe1QlmAimantTest::estA).filter(PaquetCe1QlmAimantTest::compte).count();
        assertThat(deA)
                .as("regle 1 : aucun item de A ne compte pour le quart")
                .isGreaterThanOrEqualTo(1);
    }

    // ----- g5 : FER-1

    /** FER-1 : parmi les items de A, un a pour bonne reponse un objet attire, un autre un objet non attire. */
    static void verifierFer1(List<Item> items) {
        List<Item> deA = items.stream().filter(PaquetCe1QlmAimantTest::estA).toList();
        assertThat(deA.stream().anyMatch(item -> classeDeLaBonneReponse(item) == Classe.FER))
                .as("FER-1 : aucun item de A n'a pour bonne réponse un objet que l'aimant attire").isTrue();
        assertThat(deA.stream().anyMatch(item -> classeDeLaBonneReponse(item) != Classe.FER))
                .as("FER-1 : aucun item de A n'a pour bonne réponse un objet que l'aimant n'attire pas").isTrue();
    }

    // ----- g6 : FER-3

    private static Map<String, Integer> comptesDesTaches(List<Item> items) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> tache(item).ifPresent(tache -> comptes.merge(tache.cle, 1, Integer::sum)));
        return comptes;
    }

    /**
     * FER-3 : tout item est un item de A ou de B, chacune des deux taches porte au moins trois items. Les comptes
     * sont calcules d'abord sur les items, puis seulement compares a ceux que l'en-tete ecrit.
     */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        for (Item item : items) {
            assertThat(tache(item))
                    .as("FER-3 : '%s' n'est ni un item de A (l'objet) ni un item de B (le groupe)", item.enonce())
                    .isPresent();
        }
        Map<String, Integer> comptes = comptesDesTaches(items);
        for (Tache tache : Tache.values()) {
            assertThat(comptes.getOrDefault(tache.cle, 0))
                    .as("FER-3 : la tâche %s porte %d items, elle en porte au moins %d", tache.cle,
                            comptes.getOrDefault(tache.cle, 0), LA_MOINS_SERVIE_AU_MOINS)
                    .isGreaterThanOrEqualTo(LA_MOINS_SERVIE_AU_MOINS);
        }
        Map<String, Integer> ecrits = new TreeMap<>();
        for (String ligne : entete.split("\\R")) {
            Matcher tache = TACHE_DE_L_ENTETE.matcher(ligne);
            if (tache.find()) {
                ecrits.put(tache.group(1), Integer.parseInt(tache.group(2)));
            }
        }
        assertThat(ecrits)
                .as("FER-3 : les comptes que le commentaire du paquet écrit ne sont pas ceux du paquet")
                .isEqualTo(comptes);
    }

    // ----- g7 : la composition de B, G10 : la longueur du bon groupe

    /** g7 : le bon groupe est trois objets en fer ; chaque groupe distracteur, deux en fer et un en aluminium ou cuivre. */
    static void verifierLaCompositionDeB(List<Item> items) {
        for (Item item : items) {
            if (!estB(item)) {
                continue;
            }
            List<Classe> bonne = classesDe(item, item.bonneReponse());
            assertThat(bonne.size() == 3 && bonne.stream().allMatch(classe -> classe == Classe.FER))
                    .as("g7 : le bon groupe « %s » n'est pas trois objets en fer", item.bonneReponse()).isTrue();
            for (String distracteur : item.distracteurs()) {
                List<Classe> classes = classesDe(item, distracteur);
                assertThat(classes.stream().filter(classe -> classe == Classe.FER).count() == 2
                        && classes.stream().filter(classe -> classe == Classe.METAL).count() == 1)
                        .as("g7 : le groupe distracteur « %s » ne porte pas exactement deux objets en fer et un "
                                + "objet en aluminium ou en cuivre", distracteur)
                        .isTrue();
            }
        }
    }

    /**
     * Regle 2, tache B (G10) : le bon groupe n'est le seul plus court des trois, en mots, que dans la moitie des
     * items de B au plus. Strictement plus court que chacun des deux : une egalite n'est pas "seul plus court".
     */
    static void verifierQueLeBonGroupeNEstPasLeSeulPlusCourt(List<Item> items) {
        List<Item> deB = items.stream().filter(PaquetCe1QlmAimantTest::estB).toList();
        long seul = deB.stream().filter(item -> item.distracteurs().stream()
                .allMatch(distracteur -> PaquetLu.mots(item.bonneReponse()) < PaquetLu.mots(distracteur))).count();
        assertThat(2 * seul)
                .as("regle 2 B : le bon groupe est le seul plus court dans %d item(s) sur %d", seul, deB.size())
                .isLessThanOrEqualTo(deB.size());
    }

    // ----- g8 : l'indice

    /** g8 : l'indice ne nomme pas la matiere d'un objet de la bonne reponse, ni l'intrus, ni "different des autres". */
    static void verifierLesIndices(List<Item> items) {
        for (Item item : items) {
            String indice = plat(item.indice());
            for (String nom : nomsDe(item, item.bonneReponse())) {
                String matiere = matiere(nom);
                assertThat(!matiere.isEmpty()
                        && Pattern.compile("(?<!\\p{L})" + matiere + "(?!\\p{L})", FLAGS).matcher(indice).find())
                        .as("g8 : l'indice « %s » de '%s' nomme la matière (%s) d'un objet de la bonne réponse",
                                item.indice(), item.enonce(), matiere)
                        .isFalse();
            }
            assertThat(INDICE_INTRUS.matcher(indice).find())
                    .as("g8 : l'indice « %s » de '%s' dit de chercher l'intrus ou l'objet différent des autres",
                            item.indice(), item.enonce())
                    .isFalse();
        }
    }

    // ----- g9 : matiere partagee, partie lexicale

    static Set<String> motsDuNom(String nom) {
        Set<String> mots = new TreeSet<>();
        for (String mot : nom.split("[^\\p{L}]+")) {
            if (!mot.isBlank() && !MOTS_VIDES.contains(mot)) {
                mots.add(mot);
            }
        }
        return mots;
    }

    /** g9 : en A, les deux distracteurs n'ont en commun aucun mot de leur nom que la bonne reponse n'a pas. */
    static void verifierLaMatierePartagee(List<Item> items) {
        for (Item item : items) {
            if (!estA(item)) {
                continue;
            }
            Set<String> commun = new TreeSet<>(motsDuNom(nomsDe(item, item.distracteurs().get(0)).get(0)));
            commun.retainAll(motsDuNom(nomsDe(item, item.distracteurs().get(1)).get(0)));
            commun.removeAll(motsDuNom(nomsDe(item, item.bonneReponse()).get(0)));
            assertThat(commun)
                    .as("g9 : les deux distracteurs de '%s' ont en commun le mot %s, que la bonne réponse n'a pas",
                            item.enonce(), commun)
                    .isEmpty();
        }
    }

    // ----- g11 : la premisse est vraie

    /** g11 : un enonce qui porte la premisse "en metal" n'a aucun objet sans metal dans ses propositions. */
    static void verifierQueLaPremisseEstVraie(List<Item> items) {
        for (Item item : items) {
            if (!DEBUT_DE_PREMISSE.matcher(plat(item.enonce())).find()) {
                continue;
            }
            for (String proposition : item.propositions()) {
                List<String> noms = nomsDe(item, proposition);
                for (String nom : noms) {
                    assertThat(classe(nom))
                            .as("g11 : l'énoncé de '%s' affirme que les objets sont en métal, « %s » n'en est pas",
                                    item.enonce(), nom)
                            .isNotEqualTo(Classe.SANS_METAL);
                }
            }
        }
    }

    // ----- g10 : AIM-CE1-2, la phrase interdite

    private static String neutralise(String texte) {
        return NEGATION_DU_TOUT.matcher(texte).replaceAll(" ");
    }

    /** Une phrase est refusee si elle porte une forme d'attirer et une designation generale du metal. */
    static boolean phraseInterdite(String phrase) {
        return ATTRACTION.matcher(phrase).find() && DESIGNATION.matcher(neutralise(phrase)).find();
    }

    /** La bonne reponse est refusee si l'enonce porte une forme d'attirer et qu'elle designe le metal en general. */
    static boolean bonneReponseInterdite(String enonce, String bonneReponse) {
        return ATTRACTION.matcher(enonce).find() && DESIGNATION.matcher(neutralise(bonneReponse)).find();
    }

    private static List<String> phrases(String champ) {
        return Arrays.stream(champ.split("(?<=[.?!])")).map(String::strip).filter(phrase -> !phrase.isEmpty())
                .toList();
    }

    /** AIM-CE1-2 : aucune phrase de l'enonce ou de l'indice n'attribue l'attraction au metal, bonne reponse comprise. */
    static void verifierLaPhraseInterdite(List<Item> items) {
        for (Item item : items) {
            for (String champ : List.of(item.enonce(), item.indice())) {
                for (String phrase : phrases(champ)) {
                    assertThat(phraseInterdite(phrase))
                            .as("AIM-CE1-2 : « %s » (champ de '%s') dit que l'aimant attire les métaux", phrase,
                                    item.enonce())
                            .isFalse();
                }
            }
            assertThat(bonneReponseInterdite(item.enonce(), item.bonneReponse()))
                    .as("AIM-CE1-2 : la bonne réponse « %s » de '%s' désigne les métaux en général",
                            item.bonneReponse(), item.enonce())
                    .isFalse();
        }
    }

    // ----- TRV-CE1-1 : l'ensemble ferme de cles

    /** TRV-CE1-1 : un item ne porte que les champs que ce test lit ; texteSupport et texteAVoixHaute sont refuses. */
    static void verifierLesCles(List<Item> items) {
        for (Item item : items) {
            assertThat(item.cles())
                    .as("TRV-CE1-1 : '%s' ne porte que les champs que ce test lit", item.enonce())
                    .containsExactlyInAnyOrderElementsOf(CLES_SERVIES);
        }
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe106EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-06"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-06");
    }

    @Test
    void lIdentifiantDuPaquetEstLeNomDeSonFichier() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("id")).as("le chargeur ne compare pas le champ id au nom de fichier")
                .isEqualTo(PAQUET);
    }

    @Test
    void qc5LeChargeurLitTousLesItemsEtIlsSontEntreOnzeEtVingt() {
        ContentSource source = new YamlContentPackLoader().chargerPaquet(PAQUET);
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> charges = new ArrayList<>();
        for (var item = source.next(hasard, dejaVus).orElse(null); item != null;
             item = source.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            charges.add(question);
            dejaVus.add(question.id());
        }

        assertThat(charges).hasSameSizeAs(paquet().items());
        assertThat(paquet().items().size())
                .as("QC5 : entre 11 (plus que les dix manches, DEC-0120 A.258) et 20 items")
                .isBetween(11, 20);
    }

    @Test
    void trv1LesItemsNePortentQueLesClesQueCeTestLit() {
        verifierLesCles(paquet().items());
    }

    @Test
    void fer3ChaqueItemEstDeAOuDeBEtLEnTeteDitLesComptes() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    @Test
    void g1LaListeFermeeDansLesPropositionsEtDansLEnTete() {
        Paquet paquet = paquet();
        verifierLaListeFermee(paquet.items());
        verifierLesLignesDeClasse(paquet.entete(), paquet.items());
    }

    @Test
    void g2AucunMotDUnObjetExclusDansUnChampDuPaquet() {
        verifierLesExclus(paquet().items());
    }

    @Test
    void g3UneSeulePropositionEstAcceptableEnA() {
        verifierUneSeuleAcceptable(paquet().items());
    }

    @Test
    void g4EtG9LeQuartEtAuMoinsUnItemDeAQuiCompte() {
        verifierLeQuart(paquet().items());
    }

    @Test
    void g5Fer1LaBonneReponseDeAEstUnObjetAttireEtUnObjetNonAttire() {
        verifierFer1(paquet().items());
    }

    @Test
    void g7LaCompositionDesGroupesDeB() {
        verifierLaCompositionDeB(paquet().items());
    }

    @Test
    void g10LeBonGroupeNEstLeSeulPlusCourtQueDansLaMoitieDesItemsDeBAuPlus() {
        verifierQueLeBonGroupeNEstPasLeSeulPlusCourt(paquet().items());
    }

    @Test
    void g8LIndiceNeNommePasLaMatiereDeLaBonneReponseNiLIntrus() {
        verifierLesIndices(paquet().items());
    }

    @Test
    void g9LesDeuxDistracteursDeANOntAucunMotEnCommunQueLaBonneReponseNAPas() {
        verifierLaMatierePartagee(paquet().items());
    }

    @Test
    void g11LaPremisseNEstEcriteQueSiLesTroisObjetsSontEnMetal() {
        verifierQueLaPremisseEstVraie(paquet().items());
    }

    @Test
    void aim2AucuneDesPhrasesDuPaquetNeDitQueLAimantAttireLesMetaux() {
        verifierLaPhraseInterdite(paquet().items());
    }

    @Test
    void typ1LEspaceAvantLePointDInterrogationEstInsecable() {
        for (Item item : paquet().items()) {
            for (String champ : item.champsLus()) {
                assertThat(champ).as("TYP-1 : « %s » porte une espace ordinaire avant ?, :, ; ou !", champ)
                        .doesNotContainPattern(ESPACE_ORDINAIRE_AVANT_PONCTUATION);
            }
        }
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite, et le paquet reel mute, mordent chaque verificateur

    // g1

    @Test
    void s1UnObjetHorsListeEnAEnBEtSansArticleEstRefuse() {
        refuse(() -> verifierLaListeFermee(List.of(sondeDuJeu("sonde 01"))), "g1", "la vis");
        refuse(() -> verifierLaListeFermee(List.of(sondeDuJeu("sonde 02"))), "g1", "clou, vis, aiguille");
        refuse(() -> verifierLaListeFermee(List.of(sondeDuJeu("sonde 03"))), "g1", "article");
        refuse(() -> verifierLaListeFermee(List.of(sondeDuJeu("sonde 04"))), "g1", "séparés par des virgules");
        refuse(() -> verifierLaListeFermee(List.of(sondeDuJeu("sonde 49"))), "g1", "clou, trombone", "trois objets");
    }

    @Test
    void p1UnObjetDeLaListeAvecUneApostropheTypographiqueEtUnGroupeDeTroisPassent() {
        verifierLaListeFermee(List.of(sondeDuJeu("sonde 05"), sondeDuJeu("sonde 06")));
        assertThat(nomsDe(sondeDuJeu("sonde 05"), "l’épingle")).containsExactly("epingle");
    }

    @Test
    void s2UnEnTeteSansLaLigneDUnObjetEmployeOuQuiNommeUnObjetHorsListeEstRefuse() {
        Paquet paquet = paquet();
        String sansLigne = paquet.entete().replaceFirst("(?m)^#   classe fil de cuivre : .*\\R", "");
        String horsListe = paquet.entete() + "\n#   classe vis : attire ; source : AIM-CE1-1, 3.6\n";

        assertThat(sansLigne).isNotEqualTo(paquet.entete());
        refuse(() -> verifierLesLignesDeClasse(sansLigne, paquet.items()), "g1", "fil de cuivre",
                "n'a pas de ligne de classe");
        refuse(() -> verifierLesLignesDeClasse(horsListe, paquet.items()), "g1", "vis",
                "hors de la liste fermée");
    }

    @Test
    void s3UneLigneDeClasseQuiContreditLaListeOuQuiCiteUnTestOuSansFormeEstRefusee() {
        Paquet paquet = paquet();
        String mauvaiseClasse = paquet.entete().replaceFirst("(classe clou : )attire", "$1sans metal");
        String sourceCirculaire = paquet.entete().replaceFirst("(classe clou : attire ; source : )[^\\n]*",
                "$1PaquetCe1QlmAimantTest");
        String sansSource = paquet.entete().replaceFirst("(classe clou : attire ; source :)[^\\n]*", "$1");

        assertThat(mauvaiseClasse).isNotEqualTo(paquet.entete());
        refuse(() -> verifierLesLignesDeClasse(mauvaiseClasse, paquet.items()), "g1", "clou", "la liste fermée");
        refuse(() -> verifierLesLignesDeClasse(sourceCirculaire, paquet.items()), "g1", "circulaire");
        refuse(() -> verifierLesLignesDeClasse(sansSource, paquet.items()), "g1", "n'a pas le format");
        refuse(() -> verifierLesLignesDeClasse("#\n# rien\n", paquet.items()), "g1", "aucune liste des objets");
    }

    // g2

    @Test
    void s4UnObjetExclusEstRefuseDansUnEnonceUnDistracteurUnIndiceEtDansTousLesSens() {
        refuse(() -> verifierLesExclus(List.of(sondeDuJeu("sonde 07"))), "g2", "cuillere");
        refuse(() -> verifierLesExclus(List.of(sondeDuJeu("sonde 08"))), "g2", "piece");
        refuse(() -> verifierLesExclus(List.of(sondeDuJeu("sonde 09"))), "g2", "cles");
        refuse(() -> verifierLesExclus(List.of(sondeDuJeu("sonde 10"))), "g2", "bague");
        refuse(() -> verifierLesExclus(List.of(sondeDuJeu("sonde 11"))), "g2", ": or");
        refuse(() -> verifierLesExclus(List.of(sondeDuJeu("sonde 12"))), "g2", "acier");
    }

    @Test
    void p2LesMotsQuiPortentCesLettresSansEtreLeMotPassent() {
        verifierLesExclus(List.of(sondeDuJeu("sonde 13")));
    }

    // g3

    @Test
    void s5DeuxPropositionsAcceptablesOuUneBonneReponseQuiNEnEstPasUneSontRefusees() {
        refuse(() -> verifierUneSeuleAcceptable(List.of(sondeDuJeu("sonde 14"))), "g3");
        refuse(() -> verifierUneSeuleAcceptable(List.of(sondeDuJeu("sonde 15"))), "g3");
        refuse(() -> verifierUneSeuleAcceptable(List.of(sondeDuJeu("sonde 16"))), "g3");
        refuse(() -> verifierUneSeuleAcceptable(List.of(sondeDuJeu("sonde 17"))), "g3");
    }

    @Test
    void p3UneSeuleAcceptablePasseDansLesDeuxPolarites() {
        verifierUneSeuleAcceptable(List.of(sondeDuJeu("sonde 18"), sondeDuJeu("sonde 19")));
    }

    // g4, G9

    @Test
    void s6UnPaquetDeDouzeItemsDontDeuxComptentEtUnDeTreizeDontTroisComptentSontRefuses() {
        List<Item> comptent = List.of(sondeDuJeu("sonde 22"), sondeDuJeu("sonde 23"));
        List<Item> douze = new ArrayList<>(comptent);
        douze.addAll(repete(sondeDuJeu("sonde 20"), 10));
        List<Item> treize = new ArrayList<>(comptent);
        treize.add(sondeDuJeu("sonde 06"));
        treize.addAll(repete(sondeDuJeu("sonde 20"), 10));

        assertThat(douze).hasSize(12);
        assertThat(treize).hasSize(13);
        refuse(() -> verifierLeQuart(douze), "AIM-CE1-2", "2 items comptent pour le quart sur 12");
        refuse(() -> verifierLeQuart(treize), "AIM-CE1-2", "3 items comptent pour le quart sur 13");
    }

    @Test
    void p4UnPaquetDeDouzeItemsDontTroisComptentPasseEtL1ItemDeNadiaCompte() {
        List<Item> douze = new ArrayList<>(List.of(sondeDuJeu("sonde 22"), sondeDuJeu("sonde 23"),
                sondeDuJeu("sonde 06")));
        douze.addAll(repete(sondeDuJeu("sonde 20"), 9));

        assertThat(douze).hasSize(12);
        verifierLeQuart(douze);
        assertThat(compte(sondeDuJeu("sonde 22"))).as("l'exemple de 1.2 de nadia-prof compte").isTrue();
        assertThat(compte(sondeDuJeu("sonde 23"))).as("l'item au negatif de nadia-prof compte").isTrue();
        assertThat(compte(sondeDuJeu("sonde 06"))).as("un item de B compte").isTrue();
        assertThat(compte(sondeDuJeu("sonde 44"))).as("T1-p compte").isTrue();
    }

    @Test
    void s7LItemDeCamilleEtLItemSansPremisseNeComptentPas() {
        assertThat(compte(sondeDuJeu("sonde 18"))).as("le trombone ; le papier d'aluminium, la gomme").isFalse();
        assertThat(compte(sondeDuJeu("sonde 20")))
                .as("tous en métal, un en cuivre, mais l'énoncé ne le dit pas").isFalse();
        assertThat(compte(sondeDuJeu("sonde 21"))).as("sans premisse, sans metal").isFalse();
        assertThat(compte(sondeDuJeu("sonde 38")))
                .as("un objet en cuivre a cote d'un objet sans metal ne fait pas compter l'item").isFalse();
        assertThat(compte(sondeDuJeu("sonde 50")))
                .as("la premisse et trois objets en fer, aucun en aluminium ni en cuivre").isFalse();
    }

    @Test
    void s8UnPaquetOuAucunItemDeACompteEstRefuseSeulParG9EtLePaquetReelPriveDeSesItemsDeAQuiComptentAussi() {
        List<Item> sansItemDeAQuiCompte = new ArrayList<>(repete(sondeDuJeu("sonde 21"), 9));
        sansItemDeAQuiCompte.addAll(repete(sondeDuJeu("sonde 06"), 3));
        List<Item> avecUn = new ArrayList<>(repete(sondeDuJeu("sonde 21"), 8));
        avecUn.add(sondeDuJeu("sonde 44"));
        avecUn.addAll(repete(sondeDuJeu("sonde 06"), 3));
        Paquet paquet = paquet();
        List<Item> reelSansLesItemsDeAQuiComptent = paquet.items().stream()
                .filter(item -> !(estA(item) && compte(item))).toList();

        assertThat(sansItemDeAQuiCompte).hasSize(12);
        refuse(() -> verifierLeQuart(sansItemDeAQuiCompte), "regle 1", "aucun item de A ne compte");
        verifierLeQuart(avecUn);
        assertThat(reelSansLesItemsDeAQuiComptent).hasSizeLessThan(paquet.items().size());
        refuse(() -> verifierLeQuart(reelSansLesItemsDeAQuiComptent), "regle 1", "aucun item de A ne compte");
    }

    // g5

    @Test
    void s9UnPaquetDontToutesLesBonnesReponsesDeASontAttireesOuToutesNonAttireesEstRefuse() {
        List<Item> sansAttiree = paquet().items().stream()
                .filter(item -> !(estA(item) && classeDeLaBonneReponse(item) == Classe.FER)).toList();
        List<Item> sansNonAttiree = paquet().items().stream()
                .filter(item -> !(estA(item) && classeDeLaBonneReponse(item) != Classe.FER)).toList();

        assertThat(sansAttiree).hasSizeLessThan(paquet().items().size());
        assertThat(sansNonAttiree).hasSizeLessThan(paquet().items().size());
        refuse(() -> verifierFer1(sansAttiree), "FER-1", "que l'aimant attire");
        refuse(() -> verifierFer1(sansNonAttiree), "FER-1", "n'attire pas");
    }

    // g6

    @Test
    void s10UnPaquetDontBNePorteQueDeuxItemsEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> avecDeuxDeB = new ArrayList<>(paquet.items().stream().filter(PaquetCe1QlmAimantTest::estA)
                .toList());
        avecDeuxDeB.addAll(paquet.items().stream().filter(PaquetCe1QlmAimantTest::estB).limit(2).toList());

        refuse(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxDeB, paquet.entete()), "FER-3",
                "la tâche groupe porte 2 items");
    }

    @Test
    void s11UnPaquetDontANePorteQueDeuxItemsEstRefuse() {
        Paquet paquet = paquet();
        List<Item> avecDeuxDeA = new ArrayList<>(paquet.items().stream().filter(PaquetCe1QlmAimantTest::estA)
                .limit(2).toList());
        avecDeuxDeA.addAll(paquet.items().stream().filter(PaquetCe1QlmAimantTest::estB).toList());

        refuse(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxDeA, paquet.entete()), "FER-3",
                "la tâche objet porte 2 items");
    }

    @Test
    void s12UnItemQuiNEstNiDeANiDeBEstRefuse() {
        refuse(() -> verifierLesDeuxTachesEtLeursComptes(List.of(sondeDuJeu("sonde 24")), paquet().entete()),
                "FER-3", "ni un item de A");
    }

    @Test
    void s13UnEnTeteQuiEcritUnMauvaisCompteEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replaceFirst("\\(8 items\\)", "(7 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        refuse(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux), "FER-3", "comptes");
    }

    // g7

    @Test
    void s14UnGroupeDeBQuiNEstPasComposeCommeLaRegleLeDitEstRefuse() {
        refuse(() -> verifierLaCompositionDeB(List.of(sondeDuJeu("sonde 25"))), "g7", "clou, gomme, aiguille");
        refuse(() -> verifierLaCompositionDeB(List.of(sondeDuJeu("sonde 26"))), "g7", "le bon groupe");
        refuse(() -> verifierLaCompositionDeB(List.of(sondeDuJeu("sonde 27"))), "g7",
                "clou, fil de cuivre, papier d'aluminium");
        refuse(() -> verifierLaCompositionDeB(List.of(sondeDuJeu("sonde 53"))), "g7", "clou, fil de cuivre, gomme");
    }

    @Test
    void p5LesTroisGroupesDeLaRegleDeBPassent() {
        verifierLaCompositionDeB(List.of(sondeDuJeu("sonde 06")));
        assertThat(PaquetLu.mots("fil de fer, clou, agrafe")).isEqualTo(5);
    }

    // g8

    @Test
    void s15UnIndiceQuiNommeLaMatiereDeLaBonneReponseOuLIntrusEstRefuse() {
        refuse(() -> verifierLesIndices(List.of(sondeDuJeu("sonde 28"))), "g8", "matière (fer)");
        refuse(() -> verifierLesIndices(List.of(sondeDuJeu("sonde 29"))), "g8", "intrus");
        refuse(() -> verifierLesIndices(List.of(sondeDuJeu("sonde 30"))), "g8", "intrus");
        refuse(() -> verifierLesIndices(List.of(sondeDuJeu("sonde 33"))), "g8", "matière (fer)");
        refuse(() -> verifierLesIndices(List.of(sondeDuJeu("sonde 34"))), "g8", "matière (aluminium)");
        refuse(() -> verifierLesIndices(List.of(sondeDuJeu("sonde 35"))), "g8", "matière (cuivre)");
    }

    @Test
    void p6UnIndiceMethodeEtLaMatiereDesDistracteursPassent() {
        verifierLesIndices(List.of(sondeDuJeu("sonde 31"), sondeDuJeu("sonde 32")));
    }

    // g9

    @Test
    void s16DeuxDistracteursQuiPartagentUnMotDeLeurNomSansLaBonneReponseSontRefuses() {
        refuse(() -> verifierLaMatierePartagee(List.of(sondeDuJeu("sonde 36"))), "g9", "aluminium");
        refuse(() -> verifierLaMatierePartagee(List.of(sondeDuJeu("sonde 37"))), "g9", "papier");
    }

    @Test
    void p7UnMotPartageAvecLaBonneReponseNEstPasUnTraitPartage() {
        verifierLaMatierePartagee(List.of(sondeDuJeu("sonde 22")));
        verifierLaMatierePartagee(List.of(sondeDuJeu("sonde 51"), sondeDuJeu("sonde 52")));
        assertThat(motsDuNom("pate a modeler")).containsExactly("modeler", "pate");
    }

    // g11

    @Test
    void s17UnePremisseDevantUnObjetSansMetalEstRefuseeDansLesDeuxPolaritesEtDeuxFormes() {
        for (String marqueur : List.of("sonde 38", "sonde 39", "sonde 40")) {
            Item sonde = sondeDuJeu(marqueur);

            refuse(() -> verifierQueLaPremisseEstVraie(List.of(sonde)), "g11", "n'en est pas");
            verifierUneSeuleAcceptable(List.of(sonde));
        }
    }

    @Test
    void p8LaMemePremisseDevantTroisObjetsEnMetalPasseEtCompte() {
        verifierQueLaPremisseEstVraie(List.of(sondeDuJeu("sonde 44")));
        verifierQueLaPremisseEstVraie(List.of(sondeDuJeu("sonde 21")));
        assertThat(compte(sondeDuJeu("sonde 44"))).isTrue();
    }

    // g10 : G10, la longueur du bon groupe

    @Test
    void s18UnPaquetDeBOuLeBonGroupeEstLeSeulPlusCourtDansDeuxItemsSurTroisEstRefuse() {
        List<Item> deuxSurTrois = List.of(sondeDuJeu("sonde 46"), sondeDuJeu("sonde 47"), sondeDuJeu("sonde 45"));

        refuse(() -> verifierQueLeBonGroupeNEstPasLeSeulPlusCourt(deuxSurTrois), "regle 2 B",
                "dans 2 item(s) sur 3");
    }

    @Test
    void p9UnItemSurTroisEtDeuxSurQuatrePassentEtLExempleDeNadiaPasseSeul() {
        verifierQueLeBonGroupeNEstPasLeSeulPlusCourt(List.of(sondeDuJeu("sonde 45"), sondeDuJeu("sonde 46"),
                sondeDuJeu("sonde 48")));
        verifierQueLeBonGroupeNEstPasLeSeulPlusCourt(List.of(sondeDuJeu("sonde 46"), sondeDuJeu("sonde 47"),
                sondeDuJeu("sonde 45"), sondeDuJeu("sonde 48")));
        verifierQueLeBonGroupeNEstPasLeSeulPlusCourt(List.of(sondeDuJeu("sonde 45")));
        assertThat(List.of(PaquetLu.mots("fil de fer, clou, agrafe"),
                PaquetLu.mots("fil de fer, épingle, papier d'aluminium"),
                PaquetLu.mots("clou, épingle, fil de cuivre"))).containsExactly(5, 6, 5);
    }

    // AIM-CE1-2 : dix-sept sondes, douze de nadia-prof, trois de theo-archi, deux de nadia-prof

    @Test
    void s19LesSeptPhrasesRefuseesDeAim2LeSontEtLesNeufPhrasesQuiPassentPassent() {
        List<String> refusees = List.of(
                "L'aimant attire les métaux.",
                "Un aimant attire tout ce qui est en métal.",
                "Les objets en métal sont attirés par l'aimant.",
                "L'aimant n'attire pas les métaux.",
                "L'aimant n'attire pas tous les objets en plastique, mais il attire les métaux.",
                "L'aimant n'attire pas tous les métaux, mais il attire les métaux.",
                "L'aimant n'attire pas tous les jours les métaux.");
        List<String> passent = List.of(
                "L'aimant attire-t-il le papier d'aluminium ?",
                "Le clou est en fer. L'aimant l'attire-t-il ?",
                "Quel objet l'aimant attire-t-il ?",
                "Ce trombone est en métal : l'aimant l'attire-t-il ?",
                "L'aimant n'attire pas tous les métaux.",
                "Les objets en métal ne sont pas tous attirés par l'aimant.",
                "Ces trois objets sont en métal. Lequel l'aimant n'attire-t-il pas ?",
                "Tous ces objets sont en métal. Dans quel groupe l'aimant attire-t-il les trois objets ?",
                "Les objets en métal sont solides. L'aimant attire le clou.");

        for (String phrase : refusees) {
            boolean refusee = phrases(phrase).stream().anyMatch(PaquetCe1QlmAimantTest::phraseInterdite);
            assertThat(refusee).as(phrase).isTrue();
        }
        for (String phrase : passent) {
            boolean refusee = phrases(phrase).stream().anyMatch(PaquetCe1QlmAimantTest::phraseInterdite);
            assertThat(refusee).as(phrase).isFalse();
        }
    }

    @Test
    void s20UneBonneReponseQuiDesigneLesMetauxEnGeneralEstRefuseEtLeTrombonePasse() {
        assertThat(bonneReponseInterdite("Qu'attire un aimant ?", "les métaux")).isTrue();
        assertThat(bonneReponseInterdite("Lequel de ces objets l'aimant attire-t-il ?", "le trombone")).isFalse();
        assertThat(bonneReponseInterdite("Lequel de ces objets l'aimant attire-t-il ?", "les métaux")).isTrue();
        refuse(() -> verifierLaPhraseInterdite(List.of(unItem("Qu'attire un aimant ?", "les métaux", "le clou",
                "la gomme", "Pense à chaque objet."))), "AIM-CE1-2", "désigne les métaux");
        verifierLaPhraseInterdite(List.of(unItem("Lequel de ces objets l'aimant attire-t-il ?", "le trombone",
                "le papier d'aluminium", "la gomme", "Pense à chaque objet.")));
    }

    @Test
    void s21UneDesignationDuMetalDansUnIndiceOuUnEnonceEstRefuseeSurItem() {
        refuse(() -> verifierLaPhraseInterdite(List.of(unItem("Lequel de ces objets l'aimant attire-t-il ?",
                "le clou", "le papier d'aluminium", "la gomme", "L'aimant attire les métaux."))),
                "AIM-CE1-2", "dit que l'aimant attire les métaux");
        refuse(() -> verifierLaPhraseInterdite(List.of(unItem("Les objets en métal sont attirés. Lequel ?",
                "le clou", "le papier d'aluminium", "la gomme", "Pense à chaque objet."))),
                "AIM-CE1-2", "dit que l'aimant attire les métaux");
    }

    // TRV-CE1-1 : texteSupport et texteAVoixHaute, presence refusee (reponse-nadia-aim-ce1-2-q7.md)

    @Test
    void s22UneCleExplicationUnTexteSupportEtUnTexteAVoixHauteSontRefuses() {
        refuse(() -> verifierLesCles(List.of(sondeDuJeu("sonde 41"))), "TRV-CE1-1", "ne porte que les champs");
        refuse(() -> verifierLesCles(List.of(sondeDuJeu("sonde 42"))), "TRV-CE1-1", "ne porte que les champs");
        refuse(() -> verifierLesCles(List.of(sondeDuJeu("sonde 43"))), "TRV-CE1-1", "ne porte que les champs");
        assertThat(sondeDuJeu("sonde 42").cles()).contains("texteSupport");
        assertThat(sondeDuJeu("sonde 43").cles()).contains("texteAVoixHaute");
        assertThat(sondeDuJeu("sonde 41").cles()).contains("explication");
    }
}
