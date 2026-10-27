package com.recreo.adapter.content;

import com.recreo.adapter.content.PaquetLu.Item;
import com.recreo.adapter.content.PaquetLu.Paquet;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S57-BR-QLM-CE1-05, le paquet {@code ce1-qlm-eau-trois-etats}, ligne {@code QLM-CE1-05} - reconnaitre
 * l'eau sous ses trois etats, relier la fonte et le gel a la temperature. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, 3.5 :
 * {@code ETE-CE1-1}, {@code ETE-CE1-2}) et regles de contenu du critere 12 de la story
 * ({@code arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md}, 5.2, corrige par
 * {@code arbitrage-nadia-contradictions-ce1-qlm-04-05-et-02.md} et
 * {@code arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md}). Les conditions transverses sont gardees une
 * fois pour les neuf lots par {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit {@code FER-3}</b> ({@code avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md},
 * {@code T05-1}, complete par {@code avis-theo-palier-5-ajustements-gardes-ce1-qlm-04-05.md}, {@code F1}).
 * La tache d'un item est calculee sur sa bonne reponse (et sur l'enonce pour de l'eau liquide), jamais
 * declaree : la vapeur d'eau, ou de l'eau liquide avec un element de la buee dans l'enonce, pose la tache
 * vapeur ; la fonte, le gel, la glace, de l'eau liquide avec une temperature dans l'enonce, ou
 * {@code rien ne change} ecrit a l'identique, posent la tache temperature ; tout autre item est refuse.
 * Les deux lignes de compte de l'en-tete sont relues contre le calcul.
 *
 * <p><b>Une methode par garde, un message par defaut.</b> Chaque garde est une methode {@code defauts...}
 * qui rend la liste de ses messages : le paquet reel doit la laisser vide, et chaque sonde du jeu fautif
 * ou du paquet mute la juge seule ({@code hasSize(1)}) sur le fragment de son message.
 *
 * <p><b>Ce que ce test ne garde pas</b> : l'exactitude de chaque fait contre l'instruction 3.5 et contre
 * {@code cm1-sciences-etats-matiere} ({@code QMT-CE1-2}, {@code QC9}) ; {@code CYC-1} ; {@code NRC-CE1-1}
 * au-dela du texte, c'est-a-dire qu'un item de temperature ne se resolve pas avec les indices de
 * {@code cp-qlm-etats-eau} ; de l'eau liquide decrite sans le mot eau (une flaque, une mare), que le froid
 * dure toute la duree decrite et que la glace soit seule, pour un item a {@code rien ne change}
 * ({@code ETE-CE1-2}) ; le changement de l'eau liquide propose quand l'enonce ne decrit pas d'eau liquide
 * ({@code ETE-CE1-2}, {@code G7}, cote eau) ; un distracteur paraphrase de {@code rien ne change} et le
 * rapport entre items ou il est distracteur et items ou il est juste ({@code TRV-CE1-2}) ; la
 * plausibilite de chaque distracteur, le distracteur que la situation produit, l'etat que la temperature
 * rend impossible ({@code TRV-CE1-2}) ; qu'un indice soit une methode ({@code TRV-CE1-5}) ; une buee
 * presentee comme vapeur hors d'une phrase, une temperature sans le mot degre, une duree chiffree
 * ({@code ETE-CE1-2}, {@code QMT-CE1-2}) ; que les deux taches soient deux operations distinctes item par
 * item ({@code FER-3}). Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1QlmEauTroisEtatsTest {

    private static final String PAQUET = "ce1-qlm-eau-trois-etats";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmEauTroisEtatsTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String VAPEUR = "vapeur";

    private static final String TEMPERATURE = "temperature";

    /** FER-1 : les cinq elements du libelle, chacun la bonne reponse d'au moins un item. */
    private static final List<String> ELEMENTS = List.of("glace", "eau liquide", "vapeur d'eau", "fonte", "gel");

    private static final int TACHE_AU_MOINS = 3;

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    /** Un seul article de tete retire, sur une liste fermee, du plus long au plus court. */
    private static final Pattern ARTICLE_DE_TETE =
            Pattern.compile("^(de la |de l'|des |les |du |de |d'|l'|le |la |un |une )");

    private static final Pattern TACHE_DE_L_ENTETE =
            Pattern.compile("^#\\s+(vapeur|temperature) : .*\\((\\d+) items?");

    private static final Pattern MOT_VAPEUR = motif("vapeurs?");

    private static final Pattern MOT_BUEE = motif("buees?|nuages?|brouillards?|fumees?");

    private static final Pattern NUAGE_OU_FUMEE = motif("nuages?|fumees?");

    private static final Pattern MOT_DISPARITION = motif("dispar(?:ai|u|ition)\\p{L}*");

    private static final Pattern MOT_FONTE = motif("fond|fondent|fondre|fondu|fondue|fondus|fondues|fonte|fondra"
            + "|fondait");

    private static final Pattern MOT_GEL = motif("gel|gele|gelent|geler|gelee|gelees|geles|gelera|gelait");

    private static final Pattern MOT_GLACE = motif("glaces?|glacons?");

    private static final Pattern MOT_TEMPERATURE = motif("chaud\\p{L}*|chauff\\p{L}*|rechauff\\p{L}*|chaleur"
            + "|froid\\p{L}*|refroidi\\p{L}*|congelateurs?|soleil|degres?");

    private static final Pattern EAU_LIQUIDE = motif("eau liquide");

    /** Regle 2 : sous 0 degre s'ecrit de trois facons, ecrites sans accent (G6 : au-dessous de). */
    private static final Pattern SOUS_ZERO = motif("(?:sous|en dessous de|au-dessous de) (?:0|zero) degres?");

    private static final Pattern MOT_EAU = motif("eaux?");

    private static final Pattern SOLIDE_OU_LIQUIDE = motif("solide ou liquide|liquide ou solide");

    private static final Pattern COMMENT_S_APPELLE = motif("comment (?:s'appelle|appelle-t-on|on appelle|nomme-t-on"
            + "|on nomme)");

    private static final Pattern DECOUPE_EN_PHRASES = Pattern.compile("(?<=[.?!])\\s+");

    private static final Pattern NEGATION = Pattern.compile("(?<!\\p{L})(?:ne |n')");

    private static final Pattern PAS = motif("pas");

    private static final Pattern VOIR = motif("voi[st]|voient|voyons|voyez|voir|vu|vue|visibles?|apercoi\\p{L}*");

    /** BOUILLIR : le verbe, l'ebullition, et bout qui n'est pas le nom (au bout de, le bout). */
    private static final Pattern BOUILLIR = Pattern.compile("(?<!\\p{L})(?:bouill\\p{L}*|ebullition|bouent)(?!\\p{L})"
            + "|(?<!(?<!\\p{L})(?:au|le|du|un|ce|son|a) )(?<!\\p{L})bout(?!\\p{L})", FLAGS);

    /** Une restriction : ne ... que, seulement, uniquement, il faut, doit. */
    private static final Pattern RESTRICTION = Pattern.compile(
            "(?<!\\p{L})(?:ne |n')[^.?!]*?(?<!\\p{L})(?:que(?!\\p{L})|qu')"
                    + "|(?<!\\p{L})(?:seulement|uniquement|il faut|doit|doivent)(?!\\p{L})", FLAGS);

    /** Une temperature chiffree, en chiffres ou en lettres, avec son signe eventuel. */
    private static final Pattern DEGRES = Pattern.compile("(?<![\\p{L}\\p{N}])(moins |- ?|\\u2212 ?)?"
            + "(\\d+(?:[.,]\\d+)?|zero|un|deux|trois|quatre|cinq|six|sept|huit|neuf|dix|onze|douze|treize"
            + "|quatorze|quinze|seize|vingts?|trente|quarante|cinquante|soixante|cents?|mille)"
            + " ?(?:degres?|°)", FLAGS);

    private static final Pattern ZERO_DEGRE = Pattern.compile("(?<![\\p{L}\\p{N}])(?:0|zero) ?degres?", FLAGS);

    private static final Pattern INVISIBLE = motif("invisibles?|(?:ne |n')(?:se |la |le |les |l')?voi\\p{L}* pas"
            + "|pas visibles?|ne peu\\p{L}* pas (?:la |le |l')?voir");

    private static final Pattern SENS_DE_LA_TEMPERATURE = motif("froid\\p{L}*|chaleur|chaud\\p{L}*|chauff\\p{L}*");

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final String ESPACE_ORDINAIRE_AVANT_PONCTUATION = "[^\\u00A0] [?:;!]";

    private static Pattern motif(String alternatives) {
        return Pattern.compile("(?<!\\p{L})(?:" + alternatives + ")(?!\\p{L})", FLAGS);
    }

    private static Paquet paquet() {
        return PaquetLu.lirePaquet(FICHIER_CONTENU);
    }

    private static Paquet fautif() {
        return PaquetLu.lirePaquet(CONTRE_EXEMPLE.resolve("eau-fautive.yaml"));
    }

    /** Les items du jeu fautif qui portent ce marqueur en commentaire, pour mordre un verificateur a la fois. */
    private static List<Item> sonde(Paquet fautif, String marqueur) {
        return fautif.items().stream().filter(item -> item.commentaires().stream()
                .anyMatch(ligne -> ligne.equals(marqueur) || ligne.startsWith(marqueur + " "))).toList();
    }

    private static Item uneSonde(String marqueur) {
        List<Item> trouvees = sonde(fautif(), marqueur);
        assertThat(trouvees).as(marqueur).hasSize(1);
        return trouvees.get(0);
    }

    // ----- normalisation

    /** Minuscule, sans accent, apostrophe droite, traits d'union ordinaires, blancs ramenes a une espace. */
    static String plat(String texte) {
        String plat = PaquetLu.sansAccent(texte).replace('’', '\'').replace('‐', '-').replace('‑', '-');
        return BLANCS.matcher(plat).replaceAll(" ").strip();
    }

    private static String sansArticle(String texte) {
        return ARTICLE_DE_TETE.matcher(texte.strip()).replaceFirst("").strip();
    }

    private static String sansPointFinal(String texte) {
        return texte.endsWith(".") || texte.endsWith("!") ? texte.substring(0, texte.length() - 1).strip() : texte;
    }

    private static boolean contient(Pattern motif, String texte) {
        return motif.matcher(plat(texte)).find();
    }

    private static List<String> phrases(String champ) {
        return List.of(DECOUPE_EN_PHRASES.split(plat(champ)));
    }

    private static boolean nie(String phrase) {
        return NEGATION.matcher(phrase).find() && PAS.matcher(phrase).find();
    }

    /** Regle 4 et F1 : rien ne change a l'identique, au point final pres, apres normalisation. */
    static boolean rienNeChange(String proposition) {
        return sansPointFinal(plat(proposition)).equals("rien ne change");
    }

    /** Les champs que l'enfant lit et dans lesquels une phrase est lue : l'enonce, l'indice, la bonne reponse. */
    private static List<String> champsDePhrases(Item item) {
        return List.of(item.enonce(), item.indice(), item.bonneReponse());
    }

    // ----- FER-3 : la tache est une fonction de la bonne reponse (T05-1, F1)

    /**
     * Les cinq etapes de T05-1 : vapeur d'eau ; de l'eau liquide avec un element de la buee dans l'enonce ;
     * de l'eau liquide avec une temperature dans l'enonce ; la fonte, le gel ou la glace ; rien ne change.
     */
    static Optional<String> tache(Item item) {
        String bonne = plat(item.bonneReponse());
        String enonce = plat(item.enonce());
        if (MOT_VAPEUR.matcher(bonne).find()) {
            return Optional.of(VAPEUR);
        }
        boolean eauLiquide = EAU_LIQUIDE.matcher(bonne).find();
        if (eauLiquide && sansArticle(sansPointFinal(bonne)).equals("eau liquide")
                && MOT_BUEE.matcher(enonce).find()) {
            return Optional.of(VAPEUR);
        }
        if (eauLiquide) {
            return MOT_TEMPERATURE.matcher(enonce).find() ? Optional.of(TEMPERATURE) : Optional.empty();
        }
        if (MOT_FONTE.matcher(bonne).find() || MOT_GEL.matcher(bonne).find() || MOT_GLACE.matcher(bonne).find()
                || rienNeChange(item.bonneReponse())) {
            return Optional.of(TEMPERATURE);
        }
        return Optional.empty();
    }

    private static Map<String, Integer> comptesDesTaches(List<Item> items) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(tache(item).orElse("refus"), 1, Integer::sum));
        return comptes;
    }

    /** FER-3 : un item qui n'est ni de vapeur ni de temperature est refuse. */
    static List<String> defautsDeLaTache(Item item) {
        return tache(item).isPresent() ? List.of() : List.of("FER-3 : '" + item.enonce()
                + "' n'est ni un item de vapeur (la vapeur d'eau, ou de l'eau liquide avec la buee dans l'énoncé) "
                + "ni un item de température");
    }

    /**
     * FER-3 : chaque tache porte au moins trois items. Les comptes sont calcules d'abord sur les items,
     * puis seulement compares a ceux que l'en-tete ecrit : un en-tete ne rattrape jamais un paquet.
     */
    static List<String> defautsDesComptes(List<Item> items, String entete) {
        List<String> defauts = new ArrayList<>();
        Map<String, Integer> comptes = comptesDesTaches(items);
        for (String tache : List.of(VAPEUR, TEMPERATURE)) {
            if (comptes.getOrDefault(tache, 0) < TACHE_AU_MOINS) {
                defauts.add(String.format("FER-3 : la tâche %s porte %d items, il en faut au moins %d", tache,
                        comptes.getOrDefault(tache, 0), TACHE_AU_MOINS));
            }
        }
        Map<String, Integer> ecrits = new TreeMap<>();
        for (String ligne : entete.split("\\R")) {
            Matcher tache = TACHE_DE_L_ENTETE.matcher(ligne);
            if (tache.find()) {
                ecrits.put(tache.group(1), Integer.parseInt(tache.group(2)));
            }
        }
        Map<String, Integer> calcules = new TreeMap<>(comptes);
        calcules.remove("refus");
        if (!ecrits.equals(calcules)) {
            defauts.add("FER-3 : les comptes que le commentaire du paquet écrit (" + ecrits
                    + ") ne sont pas ceux du paquet (" + calcules + ")");
        }
        return defauts;
    }

    /**
     * ETE-CE1-1 : la vapeur d'eau est la bonne reponse d'au moins un tiers des items. Le numerateur ne
     * compte que les bonnes reponses a la vapeur d'eau, pas la tache vapeur entiere.
     */
    static List<String> defautsDuTiers(List<Item> items) {
        long vapeur = items.stream().filter(item -> contient(MOT_VAPEUR, item.bonneReponse())).count();
        return 3 * vapeur < items.size()
                ? List.of(String.format("ETE-CE1-1 : %d item(s) ont la vapeur d'eau pour bonne réponse sur %d, "
                        + "moins du tiers", vapeur, items.size()))
                : List.of();
    }

    // ----- FER-1 : les cinq elements du libelle en bonne reponse

    private static Optional<String> element(Item item) {
        String bonne = plat(item.bonneReponse());
        if (MOT_VAPEUR.matcher(bonne).find()) {
            return Optional.of("vapeur d'eau");
        }
        if (MOT_FONTE.matcher(bonne).find()) {
            return Optional.of("fonte");
        }
        if (MOT_GEL.matcher(bonne).find()) {
            return Optional.of("gel");
        }
        if (EAU_LIQUIDE.matcher(bonne).find()) {
            return Optional.of("eau liquide");
        }
        return MOT_GLACE.matcher(bonne).find() ? Optional.of("glace") : Optional.empty();
    }

    /** FER-1 : glace, eau liquide, vapeur d'eau, la fonte et le gel sont chacun la bonne reponse d'un item. */
    static List<String> defautsDesCinqElements(List<Item> items) {
        Set<String> presents = new TreeSet<>();
        items.forEach(item -> element(item).ifPresent(presents::add));
        List<String> defauts = new ArrayList<>();
        ELEMENTS.stream().filter(element -> !presents.contains(element)).forEach(element -> defauts.add(
                "FER-1 : « " + element + " » n'est la bonne réponse d'aucun item (" + presents + ")"));
        return defauts;
    }

    // ----- regles 2 et 4 : rien ne change, et le changement sans objet

    /**
     * Regle 4, F2 : rien ne change n'est propose en distracteur que si au moins un item du paquet l'a pour
     * bonne reponse, a l'identique. La regle porte sur le paquet : un message par paquet, et les deux
     * taches sont lues (la regle parle de B, le motif vaut pour A).
     */
    static List<String> defautsDeLaRegleQuatre(List<Item> items) {
        long distracteur = items.stream().filter(item -> item.distracteurs().stream()
                .anyMatch(PaquetCe1QlmEauTroisEtatsTest::rienNeChange)).count();
        boolean bonneReponse = items.stream().anyMatch(item -> rienNeChange(item.bonneReponse()));
        return distracteur > 0 && !bonneReponse
                ? List.of(String.format("regle 4 : rien ne change est distracteur de %d item(s) et la bonne réponse "
                        + "d'aucun", distracteur))
                : List.of();
    }

    /**
     * Regle 2, F3, G6 : un item dont la bonne reponse est rien ne change dit sous 0 degre (sous, en
     * dessous de, au-dessous de) et n'emploie pas le mot eau, meme pour de l'eau gelee.
     */
    static List<String> defautsDeLaRegleDeux(Item item) {
        List<String> defauts = new ArrayList<>();
        if (!rienNeChange(item.bonneReponse())) {
            return defauts;
        }
        if (!contient(SOUS_ZERO, item.enonce())) {
            defauts.add("regle 2 : l'énoncé de '" + item.enonce() + "' ne dit pas sous 0 degré (ni en dessous de, "
                    + "ni au-dessous de) alors que rien ne change est la bonne réponse");
        }
        if (contient(MOT_EAU, item.enonce())) {
            defauts.add("regle 2 : l'énoncé de '" + item.enonce() + "' emploie le mot eau alors que rien ne "
                    + "change est la bonne réponse");
        }
        return defauts;
    }

    /**
     * Regle 2, G7, cote glace seulement : dans un item de temperature, une proposition qui nomme une glace
     * exige que l'enonce porte la glace ou le gel. Le cote eau (l'eau gele sans eau liquide decrite) est
     * non garde : ETE-CE1-2, lu par nadia-prof.
     */
    static List<String> defautsDuChangementSansObjet(Item item) {
        if (!tache(item).equals(Optional.of(TEMPERATURE))) {
            return List.of();
        }
        boolean nommeUneGlace = item.propositions().stream().anyMatch(proposition -> contient(MOT_GLACE, proposition));
        boolean decritUneGlace = contient(MOT_GLACE, item.enonce()) || contient(MOT_GEL, item.enonce());
        return nommeUneGlace && !decritUneGlace
                ? List.of("regle 2 : '" + item.enonce() + "' nomme une glace que l'énoncé ne décrit pas")
                : List.of();
    }

    // ----- T05-2 : ce que le CP a servi

    static List<String> defautsDuCp(Item item) {
        List<String> defauts = new ArrayList<>();
        if (item.propositions().stream().map(proposition -> sansArticle(sansPointFinal(plat(proposition))))
                .anyMatch(cle -> cle.equals("solide") || cle.equals("liquide"))) {
            defauts.add("T05-2a : une proposition de '" + item.enonce() + "' est solide ou liquide, la forme du CP");
        }
        if (contient(SOLIDE_OU_LIQUIDE, item.enonce())) {
            defauts.add("T05-2b : l'énoncé '" + item.enonce() + "' demande solide ou liquide, la forme du CP");
        }
        if (contient(COMMENT_S_APPELLE, item.enonce())) {
            defauts.add("T05-2c : l'énoncé '" + item.enonce() + "' demande comment s'appelle, la forme du CP");
        }
        return defauts;
    }

    // ----- T05-3 : buee, bouillir, nombre, intrus, indice

    /**
     * ETE-CE1-2, T05-3a, b et c : la buee, le nuage, le brouillard et la fumee ne sont pas presentes comme
     * de la vapeur d'eau, la vapeur d'eau ne se voit pas, et la bonne reponse a la question de la buee
     * n'est pas la vapeur d'eau. La negation est exemptee, les distracteurs ne sont pas lus.
     */
    static List<String> defautsDeLaBuee(Item item) {
        List<String> defauts = new ArrayList<>();
        for (String champ : champsDePhrases(item)) {
            for (String phrase : phrases(champ)) {
                if (MOT_BUEE.matcher(phrase).find() && MOT_VAPEUR.matcher(phrase).find() && !nie(phrase)) {
                    defauts.add("T05-3a : « " + phrase + " » présente la buée, le nuage, le brouillard ou la fumée "
                            + "comme de la vapeur d'eau");
                }
                if (MOT_VAPEUR.matcher(phrase).find() && VOIR.matcher(phrase).find() && !nie(phrase)) {
                    defauts.add("T05-3c : « " + phrase + " » dit qu'on voit la vapeur d'eau");
                }
            }
        }
        if (contient(MOT_BUEE, item.enonce()) && contient(MOT_VAPEUR, item.bonneReponse())) {
            defauts.add("T05-3b : la bonne réponse de '" + item.enonce() + "' est la vapeur d'eau alors que "
                    + "l'énoncé nomme la buée");
        }
        return defauts;
    }

    /** ETE-CE1-2, T05-3d : aucune phrase ne dit que l'eau ne devient vapeur qu'en bouillant. */
    static List<String> defautsDeBouillir(Item item) {
        List<String> defauts = new ArrayList<>();
        for (String champ : champsDePhrases(item)) {
            for (String phrase : phrases(champ)) {
                if (MOT_VAPEUR.matcher(phrase).find() && BOUILLIR.matcher(phrase).find()
                        && RESTRICTION.matcher(phrase).find()) {
                    defauts.add("T05-3d : « " + phrase + " » dit que l'eau ne devient vapeur qu'en bouillant");
                }
            }
        }
        return defauts;
    }

    /** ETE-CE1-2, critere 6, X5 : les items dont la bonne reponse est la vapeur d'eau ne bouillent pas tous. */
    static List<String> defautsDeLaDiversiteDEbullition(List<Item> items) {
        List<Item> vapeur = items.stream().filter(item -> contient(MOT_VAPEUR, item.bonneReponse())).toList();
        return !vapeur.isEmpty() && vapeur.stream().allMatch(item -> contient(BOUILLIR, item.enonce()))
                ? List.of("X5 : les " + vapeur.size() + " items dont la bonne réponse est la vapeur d'eau sont "
                        + "tous des situations où l'eau bout")
                : List.of();
    }

    /**
     * ETE-CE1-2, critere 6, T05-3e et T05-3f : toute temperature chiffree autre que 0 degre est refusee,
     * en chiffres ou en lettres, signee ou non ; 0 degre ne figure que dans un item de la tache B. Un
     * nombre qui n'est pas une temperature n'est pas vise.
     */
    static List<String> defautsDesNombres(Item item) {
        List<String> defauts = new ArrayList<>();
        for (String champ : item.champsLus()) {
            Matcher degres = DEGRES.matcher(plat(champ));
            while (degres.find()) {
                String nombre = degres.group(2);
                if (degres.group(1) != null || !(nombre.equals("0") || nombre.equals("zero"))) {
                    defauts.add("T05-3e : « " + champ + " » cite une température autre que 0 degré : "
                            + degres.group().strip());
                }
            }
            if (!tache(item).equals(Optional.of(TEMPERATURE)) && ZERO_DEGRE.matcher(plat(champ)).find()) {
                defauts.add("T05-3f : « " + champ + " » cite 0 degré hors de la tâche B, 0 degré ne figure que "
                        + "dans un item de température");
            }
        }
        return defauts;
    }

    /** ETE-CE1-2, regle 3, T05-3g : en vapeur, au plus un distracteur de la famille de la buee. */
    static List<String> defautsDesDistracteursDeVapeur(Item item) {
        long dansLaFamille = item.distracteurs().stream().filter(distracteur -> contient(MOT_BUEE, distracteur))
                .count();
        return tache(item).equals(Optional.of(VAPEUR)) && dansLaFamille > 1
                ? List.of("T05-3g : les deux distracteurs de '" + item.enonce() + "' sont de la famille de la buée")
                : List.of();
    }

    /** Regle 3 : sous une ebullition, le nuage et la fumee sont ce que l'eau qui bout produit, jamais un distracteur. */
    static List<String> defautsDeLaFumeeQuiBout(Item item) {
        List<String> defauts = new ArrayList<>();
        if (tache(item).equals(Optional.of(VAPEUR)) && contient(BOUILLIR, item.enonce())) {
            for (String distracteur : item.distracteurs()) {
                if (contient(NUAGE_OU_FUMEE, distracteur)) {
                    defauts.add("regle 3 : '" + distracteur + "' est ce que l'eau qui bout produit, distracteur de '"
                            + item.enonce() + "'");
                }
            }
        }
        return defauts;
    }

    /** TRV-CE1-2 : quand la bonne reponse est la fonte, disparaitre est vrai de la glace, jamais un distracteur. */
    static List<String> defautsDeLaDisparitionDeLaGlace(Item item) {
        List<String> defauts = new ArrayList<>();
        if (tache(item).equals(Optional.of(TEMPERATURE)) && contient(MOT_FONTE, item.bonneReponse())) {
            for (String distracteur : item.distracteurs()) {
                if (contient(MOT_DISPARITION, distracteur)) {
                    defauts.add("TRV-CE1-2 : '" + distracteur + "' est vrai quand la glace fond, distracteur de '"
                            + item.enonce() + "'");
                }
            }
        }
        return defauts;
    }

    /**
     * Regle 5, T05-3h et X4 : en vapeur, aucun indice ne dit que la vapeur d'eau est invisible ou ne se voit
     * pas ; en temperature, aucun indice n'enonce le sens de la temperature (le froid fait geler, la chaleur
     * fait fondre).
     */
    static List<String> defautsDesIndices(Item item) {
        List<String> defauts = new ArrayList<>();
        Optional<String> tache = tache(item);
        if (tache.equals(Optional.of(VAPEUR)) && contient(INVISIBLE, item.indice())) {
            defauts.add("T05-3h : l'indice « " + item.indice() + " » dit que la vapeur d'eau ne se voit pas");
        }
        if (tache.equals(Optional.of(TEMPERATURE))) {
            for (String phrase : phrases(item.indice())) {
                if (SENS_DE_LA_TEMPERATURE.matcher(phrase).find()
                        && (MOT_GEL.matcher(phrase).find() || MOT_FONTE.matcher(phrase).find())) {
                    defauts.add("X4 : l'indice « " + item.indice() + " » énonce le sens de la température");
                }
            }
        }
        return defauts;
    }

    private static List<String> defautsDeTousLesItems(Function<Item, List<String>> garde) {
        List<String> defauts = new ArrayList<>();
        paquet().items().forEach(item -> defauts.addAll(garde.apply(item)));
        return defauts;
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe105EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-05"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-05");
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
    void fer3ChaqueItemEstDeVapeurOuDeTemperatureEtLEnTeteDitLesComptes() {
        Paquet paquet = paquet();
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaTache)).isEmpty();
        assertThat(defautsDesComptes(paquet.items(), paquet.entete())).isEmpty();
    }

    @Test
    void ete1LaVapeurDEauEstLaBonneReponseDAuMoinsLeTiersDesItems() {
        assertThat(defautsDuTiers(paquet().items())).isEmpty();
    }

    @Test
    void fer1LesCinqElementsSontChacunLaBonneReponseDUnItem() {
        assertThat(defautsDesCinqElements(paquet().items())).isEmpty();
    }

    @Test
    void regle4RienNeChangeEstDistracteurSeulementSiUnItemLAPourBonneReponse() {
        List<Item> items = paquet().items();

        assertThat(defautsDeLaRegleQuatre(items)).isEmpty();
        assertThat(items).as("le paquet porte rien ne change en distracteur et en bonne réponse")
                .anyMatch(item -> item.distracteurs().stream().anyMatch(PaquetCe1QlmEauTroisEtatsTest::rienNeChange))
                .anyMatch(item -> rienNeChange(item.bonneReponse()));
    }

    @Test
    void regle2UnItemARienNeChangeDitSousZeroDegreSansLeMotEau() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaRegleDeux)).isEmpty();
    }

    @Test
    void regle2AucunItemNeNommeUneGlaceQueLEnonceNeDecritPas() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDuChangementSansObjet)).isEmpty();
    }

    @Test
    void ete1AucunItemNeReprendLesFormesDeCpEtDesSolideOuLiquide() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDuCp)).isEmpty();
    }

    @Test
    void ete2LaBueeLeNuageLeBrouillardEtLaFumeeNeSontJamaisPresentesCommeDeLaVapeur() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaBuee)).isEmpty();
    }

    @Test
    void ete2AucunItemNeDitQueLEauNeDevientVapeurQuEnBouillantEtTousNeBouillentPas() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDeBouillir)).isEmpty();
        assertThat(defautsDeLaDiversiteDEbullition(paquet().items())).isEmpty();
    }

    @Test
    void ete2LeSeulNombreEstZeroDegreEtIlNeSertQueLaTacheTemperature() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDesNombres)).isEmpty();
    }

    @Test
    void ete2UnItemDeVapeurNePorteQuUnDistracteurDeLaFamilleDeLaBuee() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDesDistracteursDeVapeur)).isEmpty();
    }

    @Test
    void ete2AucunItemDeVapeurQuiBoutNePorteLeNuageOuLaFumeeEnDistracteur() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaFumeeQuiBout)).isEmpty();
    }

    @Test
    void trv2AucunItemDeFonteNePorteLaDisparitionEnDistracteur() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaDisparitionDeLaGlace)).isEmpty();
    }

    @Test
    void ete2LesIndicesNeDisentNiQueLaVapeurEstInvisibleNiLeSensDeLaTemperature() {
        assertThat(defautsDeTousLesItems(PaquetCe1QlmEauTroisEtatsTest::defautsDesIndices)).isEmpty();
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

    // ----- ADR-0013 obligation 3 : un jeu fautif commite, et le paquet reel mute, mordent chaque garde

    private static void assertUnSeulDefaut(List<String> defauts, String marqueur, String fragment) {
        assertThat(defauts).as(marqueur).hasSize(1).allSatisfy(defaut -> assertThat(defaut).contains(fragment));
    }

    private static void assertAucunDefaut(Function<Item, List<String>> garde, String... marqueurs) {
        for (String marqueur : marqueurs) {
            assertThat(garde.apply(uneSonde(marqueur))).as(marqueur).isEmpty();
        }
    }

    private static List<Item> sans(List<Item> items, java.util.function.Predicate<Item> aRetirer) {
        List<Item> reste = items.stream().filter(aRetirer.negate()).toList();
        assertThat(reste).hasSizeLessThan(items.size());
        return reste;
    }

    @Test
    void s1UnItemDeLEauLiquideSansLaBueeNiTemperatureEstRefuse() {
        assertUnSeulDefaut(defautsDeLaTache(uneSonde("sonde 01")), "sonde 01", "ni un item de température");
    }

    @Test
    void s2UneParaphraseDeRienNeChangeEstRefuseeEtRienNeChangeAvecSonPointFinalPasse() {
        assertUnSeulDefaut(defautsDeLaTache(uneSonde("sonde 03")), "sonde 03", "ni un item de vapeur");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaTache, "sonde 04", "sonde 05", "sonde 06",
                "sonde 07", "sonde 08");
        assertThat(tache(uneSonde("sonde 04"))).contains(TEMPERATURE);
        assertThat(tache(uneSonde("sonde 07"))).contains(TEMPERATURE);
    }

    @Test
    void s3UnItemDontLaBonneReponseEstLaVapeurSeCompteEnVapeurJamaisEnTemperature() {
        Item sonde = uneSonde("sonde 02");

        assertThat(tache(sonde)).contains(VAPEUR);
        assertThat(defautsDeLaTache(sonde)).isEmpty();
    }

    @Test
    void s4UnPaquetQuiNePoseQueLaVapeurEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> vapeur = paquet.items().stream().filter(item -> tache(item).equals(Optional.of(VAPEUR))).toList();

        assertThat(vapeur).hasSizeLessThan(paquet.items().size());
        assertThat(defautsDesComptes(vapeur, paquet.entete()))
                .anySatisfy(defaut -> assertThat(defaut).contains("la tâche temperature porte 0 items"));
    }

    @Test
    void s5UnPaquetDontLaTemperatureNePorteQueDeuxItemsEstRefuse() {
        Paquet paquet = paquet();
        List<Item> vapeur = paquet.items().stream().filter(item -> tache(item).equals(Optional.of(VAPEUR))).toList();
        List<Item> avecDeux = new ArrayList<>(vapeur);
        avecDeux.addAll(paquet.items().stream().filter(item -> tache(item).equals(Optional.of(TEMPERATURE)))
                .limit(2).toList());

        assertThat(defautsDesComptes(avecDeux, paquet.entete()))
                .anySatisfy(defaut -> assertThat(defaut).contains("la tâche temperature porte 2 items"));
    }

    @Test
    void s6UnEnTeteQuiEcritUnMauvaisCompteEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replaceFirst("\\(6 items\\)", "(5 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertUnSeulDefaut(defautsDesComptes(paquet.items(), faux), "en-tete", "les comptes que le commentaire");
    }

    @Test
    void s7UnPaquetDontLaVapeurEstSousLeTiersEstRefuse() {
        List<Item> sans = sans(paquet().items(), item -> item.bonneReponse().equals("elle devient de la vapeur d'eau")
                && item.enonce().startsWith("L'eau bout"));

        assertUnSeulDefaut(defautsDuTiers(sans), "tiers", "moins du tiers");
    }

    @Test
    void s8LeTiersSeLitSurLaBonneReponseALaVapeurEtNonSurLaTacheVapeurEntiere() {
        List<Item> sans = sans(paquet().items(), item -> item.bonneReponse().equals("elle devient de la vapeur d'eau")
                && (item.enonce().startsWith("Après la pluie") || item.enonce().startsWith("L'eau bout")));

        assertThat(sans.stream().filter(item -> tache(item).equals(Optional.of(VAPEUR))).count())
                .as("la tâche vapeur entière, buée comprise, porte encore 4 items sur 10").isEqualTo(4);
        assertUnSeulDefaut(defautsDuTiers(sans), "tiers", "2 item(s) ont la vapeur d'eau pour bonne réponse sur 10");
    }

    @Test
    void s9UnPaquetPriveDUnElementDuLibelleEnBonneReponseEstRefuse() {
        for (String element : ELEMENTS) {
            List<Item> sansElement = sans(paquet().items(), item -> element(item).equals(Optional.of(element)));

            assertUnSeulDefaut(defautsDesCinqElements(sansElement), element, "« " + element + " »");
        }
    }

    @Test
    void s10RienNeChangeEnDistracteurSansAucuneBonneReponseEstRefuseUneFoisParPaquet() {
        List<Item> exemples = List.of(uneSonde("sonde 05"), uneSonde("sonde 06"));

        assertUnSeulDefaut(defautsDeLaRegleQuatre(exemples), "S1, les deux exemples de nadia-prof seuls",
                "regle 4 : rien ne change est distracteur de 2 item(s)");
    }

    @Test
    void s11UneFormeEquivalenteDeRienNeChangeNeTientPasLieuDeBonneReponse() {
        List<Item> avecLaForme = List.of(uneSonde("sonde 05"), uneSonde("sonde 06"), uneSonde("sonde 07"));

        assertUnSeulDefaut(defautsDeLaRegleQuatre(avecLaForme), "S2", "regle 4");
        assertThat(tache(uneSonde("sonde 07"))).contains(TEMPERATURE);
    }

    @Test
    void s12LePaquetReelPriveDeSesItemsARienNeChangeEstRefuse() {
        List<Item> sans = sans(paquet().items(), item -> rienNeChange(item.bonneReponse()));

        assertUnSeulDefaut(defautsDeLaRegleQuatre(sans), "S3", "regle 4 : rien ne change est distracteur de 3");
    }

    @Test
    void p1UnPaquetOuRienNeChangeEstDistracteurEtBonneReponsePasseAvecOuSansPointFinal() {
        for (String bonne : List.of("sonde 08", "sonde 04")) {
            List<Item> items = List.of(uneSonde("sonde 05"), uneSonde("sonde 06"), uneSonde(bonne));

            assertThat(defautsDeLaRegleQuatre(items)).as(bonne).isEmpty();
        }
    }

    @Test
    void s13RienNeChangeSansSousZeroDegreEstRefuseSeulEtLesTroisFormesDeSousZeroPassent() {
        assertUnSeulDefaut(defautsDeLaRegleDeux(uneSonde("sonde 09")), "sonde 09", "ne dit pas sous 0 degré");
        assertUnSeulDefaut(defautsDeLaRegleDeux(uneSonde("sonde 10")), "sonde 10", "ne dit pas sous 0 degré");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaRegleDeux, "sonde 04", "sonde 08", "sonde 13",
                "sonde 14", "sonde 15");
    }

    @Test
    void s14RienNeChangeAvecLeMotEauDansLEnonceEstRefuseSeulMemePourUneBouteilleGelee() {
        assertUnSeulDefaut(defautsDeLaRegleDeux(uneSonde("sonde 11")), "sonde 11", "emploie le mot eau");
        assertUnSeulDefaut(defautsDeLaRegleDeux(uneSonde("sonde 16")), "sonde 16", "emploie le mot eau");
    }

    @Test
    void p2UneEauLiquideDecriteSansLeMotEauResteNonGardeeEtPasse() {
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaRegleDeux, "sonde 12");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDuChangementSansObjet, "sonde 12");
    }

    @Test
    void s15UnChangementDeLaGlaceSansGlaceDecriteEstRefuseSeulPourLesTroisFormes() {
        for (String marqueur : List.of("sonde 17", "sonde 18", "sonde 19")) {
            assertThat(tache(uneSonde(marqueur))).as(marqueur).contains(TEMPERATURE);
            assertUnSeulDefaut(defautsDuChangementSansObjet(uneSonde(marqueur)), marqueur, "nomme une glace");
        }
    }

    @Test
    void p3LesExemplesDeNadiaDontLaMareGeleeDecriteParLeGelPassentLaGardeDeLaGlace() {
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDuChangementSansObjet, "sonde 05", "sonde 06",
                "sonde 08", "sonde 04");
        assertThat(contient(MOT_GLACE, uneSonde("sonde 06").enonce())).as("la mare n'écrit ni glace ni glaçon")
                .isFalse();
    }

    @Test
    void s16SolideEtLiquideEnPropositionsOuEnEnonceEtCommentSAppelleSontRefusesSeuls() {
        assertUnSeulDefaut(defautsDuCp(uneSonde("sonde 20")), "sonde 20", "T05-2a");
        assertUnSeulDefaut(defautsDuCp(uneSonde("sonde 21")), "sonde 21", "T05-2b");
        assertUnSeulDefaut(defautsDuCp(uneSonde("sonde 22")), "sonde 22", "T05-2c");
        assertUnSeulDefaut(defautsDuCp(uneSonde("sonde 23")), "sonde 23", "T05-2c");
    }

    @Test
    void p4LeVerreAuCongelateurDeNadiaEtDeLEauLiquidePassentLesFormesDuCp() {
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDuCp, "sonde 05", "sonde 31");
    }

    @Test
    void s17LaBueeLeNuageLeBrouillardEtLaFumeeBlanchePresentesCommeVapeurSontRefuses() {
        assertUnSeulDefaut(defautsDeLaBuee(uneSonde("sonde 24")), "sonde 24", "T05-3a");
        assertUnSeulDefaut(defautsDeLaBuee(uneSonde("sonde 25")), "sonde 25", "T05-3a");
        assertUnSeulDefaut(defautsDeLaBuee(uneSonde("sonde 26")), "sonde 26", "T05-3a");
        assertUnSeulDefaut(defautsDeLaBuee(uneSonde("sonde 27")), "sonde 27", "T05-3a");
    }

    @Test
    void s18LaBonneReponseALaQuestionDeLaBueeNeSauraitEtreLaVapeurDEau() {
        assertUnSeulDefaut(defautsDeLaBuee(uneSonde("sonde 28")), "sonde 28", "T05-3b");
    }

    @Test
    void p5LaNegationLaBueeEnGouttesLaBueeEnDistracteurEtUneSituationPassent() {
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDeLaBuee, "sonde 29", "sonde 30", "sonde 31",
                "sonde 33");
    }

    @Test
    void s19OnVoitLaVapeurDEauEstRefuseSeul() {
        assertUnSeulDefaut(defautsDeLaBuee(uneSonde("sonde 32")), "sonde 32", "T05-3c");
    }

    @Test
    void s20LaVapeurSeulementQuandLEauBoutEstRefuseEtBoutPasseDansAuBoutDe() {
        assertUnSeulDefaut(defautsDeBouillir(uneSonde("sonde 34")), "sonde 34", "T05-3d");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDeBouillir, "sonde 35", "sonde 36");
    }

    @Test
    void s21UnPaquetOuToutesLesVapeursSontDesCasseroleQuiBoutEstRefuse() {
        List<Item> sans = sans(paquet().items(), item -> contient(MOT_VAPEUR, item.bonneReponse())
                && !contient(BOUILLIR, item.enonce()));

        assertUnSeulDefaut(defautsDeLaDiversiteDEbullition(sans), "X5", "tous des situations où l'eau bout");
    }

    @Test
    void s22UneTemperatureAutreQueZeroDegreEstRefuseeEnChiffresEnLettresEtAvecUnSigne() {
        for (String marqueur : List.of("sonde 37", "sonde 38", "sonde 39", "sonde 40")) {
            assertUnSeulDefaut(defautsDesNombres(uneSonde(marqueur)), marqueur, "T05-3e");
        }
    }

    @Test
    void p6ZeroDegreSousZeroAuDessusDeZeroEtDeuxGlaconsPassent() {
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDesNombres, "sonde 41", "sonde 42", "sonde 43",
                "sonde 44", "sonde 45");
    }

    @Test
    void s23ZeroDegreHorsDeLaTacheTemperatureEstRefuseSeul() {
        assertUnSeulDefaut(defautsDesNombres(uneSonde("sonde 46")), "sonde 46", "T05-3f");
        assertUnSeulDefaut(defautsDesNombres(uneSonde("sonde 47")), "sonde 47", "T05-3f");
        assertThat(tache(uneSonde("sonde 47"))).contains(VAPEUR);
    }

    @Test
    void s24UnItemDeVapeurAvecDeuxDistracteursDeLaFamilleDeLaBueeEstRefuseEtUnSeulPasse() {
        assertUnSeulDefaut(defautsDesDistracteursDeVapeur(uneSonde("sonde 48")), "sonde 48", "T05-3g");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDesDistracteursDeVapeur, "sonde 49");
    }

    @Test
    void s25UnIndiceDeVapeurQuiDitInvisibleOuNeSeVoitPasEstRefuseEtUneSituationDansUnEnoncePasse() {
        assertUnSeulDefaut(defautsDesIndices(uneSonde("sonde 50")), "sonde 50", "T05-3h");
        assertUnSeulDefaut(defautsDesIndices(uneSonde("sonde 51")), "sonde 51", "T05-3h");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDesIndices, "sonde 33");
    }

    @Test
    void s26UnIndiceDeTemperatureQuiEnonceLeSensDeLaTemperatureEstRefuseSeulEtPasseEnVapeur() {
        assertUnSeulDefaut(defautsDesIndices(uneSonde("sonde 52")), "sonde 52", "X4");
        assertAucunDefaut(PaquetCe1QlmEauTroisEtatsTest::defautsDesIndices, "sonde 53", "sonde 05");
    }

    @Test
    void s27LeNuageOuLaFumeeEnDistracteurSousUneEbullitionSontRefusesSeulsEtLeReelPasse() {
        assertUnSeulDefaut(defautsDeLaFumeeQuiBout(uneSonde("sonde 54")), "sonde 54",
                "regle 3 : 'elle devient un nuage'");
        assertUnSeulDefaut(defautsDeLaFumeeQuiBout(uneSonde("sonde 55")), "sonde 55",
                "regle 3 : 'elle devient de la fumée'");
        assertThat(paquet().items()).filteredOn(item -> contient(NUAGE_OU_FUMEE, String.join(" ", item.distracteurs())))
                .isNotEmpty().allSatisfy(item -> assertThat(defautsDeLaFumeeQuiBout(item)).isEmpty());
    }

    @Test
    void s28LaDisparitionEnDistracteurDUnItemDeFonteEstRefuseeSeuleEtLesItemsReelsPassent() {
        assertUnSeulDefaut(defautsDeLaDisparitionDeLaGlace(uneSonde("sonde 56")), "sonde 56",
                "TRV-CE1-2 : 'les glaçons disparaissent'");
        assertUnSeulDefaut(defautsDeLaDisparitionDeLaGlace(uneSonde("sonde 57")), "sonde 57",
                "TRV-CE1-2 : 'la glace disparaît'");
        assertThat(paquet().items()).filteredOn(item -> contient(MOT_DISPARITION, String.join(" ", item.distracteurs())))
                .isNotEmpty().allSatisfy(item -> assertThat(defautsDeLaDisparitionDeLaGlace(item)).isEmpty());
    }
}
