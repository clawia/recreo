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
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
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
 * S57-BR-QLM-CE1-04, le paquet {@code ce1-qlm-familles-d-aliments}, ligne {@code QLM-CE1-04} - classer
 * un aliment dans sa famille, reconnaitre un repas varie. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, 3.4 :
 * {@code FAL-CE1-1}, {@code FAL-CE1-2}, {@code BIA-4}) et regles de contenu du critere 11 de la story
 * ({@code arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md}, 5.1, corrige par
 * {@code arbitrage-nadia-contradictions-ce1-qlm-04-05-et-02.md} et
 * {@code arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md}). Les conditions transverses sont gardees une
 * fois pour les neuf lots par {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit {@code FER-3}</b> ({@code avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md},
 * {@code T04-1}). La tache d'un item est calculee sur ses trois propositions, jamais declaree : trois
 * familles = classement, trois menus (au moins deux aliments separes par des virgules) = menu, tout autre
 * item est refuse. Les deux lignes de compte de l'en-tete sont relues contre le calcul.
 *
 * <p><b>Comment ce test lit {@code FAL-CE1-1} et {@code FAL-CE1-2}</b> ({@code T04-2}, {@code T04-3}). La
 * liste fermee des aliments est dans l'en-tete, un aliment par ligne avec sa famille et sa source ; les
 * familles d'un menu sont calculees par cette liste. Un menu qui ne passe pas la regle 3 est refuse par
 * {@link #defautsDuMenu}, dont chaque regle leve un message, et que chaque sonde juge seule.
 *
 * <p><b>Ce que ce test ne garde pas</b> : que la source nommee existe et porte chaque famille, et que
 * chaque aliment soit range comme elle le range ({@code QMT-CE1-2}, {@code QC9}) ; une source du depot
 * nommee sans extension, sans {@code Test} ni nadia ({@code QMT-CE1-2}) ; un aliment nomme dans un
 * indice sans figurer dans la liste ; les paraphrases de mauvais ({@code le moins bon}), de
 * {@code BIA-4} ; un menu qui suppose un foyer ({@code TRV-CE1-6}) ; qu'un menu soit une operation
 * mentale distincte du classement ({@code FER-3}, {@code QC9}) ; la definition d'une famille dans un
 * indice. Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1QlmFamillesDAlimentsTest {

    private static final String PAQUET = "ce1-qlm-familles-d-aliments";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmFamillesDAlimentsTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String CLASSEMENT = "classement";

    private static final String MENU = "menu";

    private static final String FRUITS_ET_LEGUMES = "fruits et legumes";

    private static final String FECULENTS = "feculents";

    private static final String LAITIERS = "produits laitiers";

    private static final String VIANDE = "viande, poisson et oeufs";

    private static final String SUCRES = "produits sucres";

    /** FER-1 : les cinq familles, ecrites sans accent. Viande, poisson et oeufs est une seule famille. */
    private static final List<String> FAMILLES = List.of(FRUITS_ET_LEGUMES, FECULENTS, LAITIERS, VIANDE, SUCRES);

    /** Un seul article de tete retire, sur une liste fermee, du plus long au plus court. */
    private static final Pattern ARTICLE_DE_TETE =
            Pattern.compile("^(de la |de l'|des |les |du |de |d'|l'|le |la |un |une )");

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(classement|menu) : .*\\((\\d+) items?");

    /** QMT-CE1-2 : une ligne de la liste fermee des aliments, dans l'en-tete du paquet. */
    private static final Pattern LIGNE_D_ALIMENT = Pattern.compile("^#\\s+aliment (.+?) : (fruits et legumes|feculents"
            + "|produits laitiers|viande, poisson et oeufs|produits sucres) ; source : (\\S.*)$");

    private static final Pattern LIGNE_D_ALIMENT_OUVERTE = Pattern.compile("^#\\s+aliment\\s");

    private static final Pattern ALIMENT_DECLARE = Pattern.compile("^FAL-CE1-1 aliment : (.+)$");

    /** X2 : une source qui est un fichier du depot, lue sur la source brute. */
    private static final Pattern SOURCE_FICHIER_OU_NADIA = Pattern.compile(
            "\\.(?:md|java|yaml|yml)(?![\\p{L}\\p{N}])|nadia", FLAGS);

    /** X2 : le suffixe d'un test, casse respectee ("attestation" et "contestee" passent). */
    private static final Pattern SOURCE_D_UN_TEST = Pattern.compile("Test(?![\\p{L}\\p{N}])");

    /** FAL-CE1-1 : les treize exclus, sur le texte sans accent. */
    private static final Pattern EXCLU = Pattern.compile("(?<!\\p{L})(lentilles?|pois chiches?|petits? pois|avocats?"
            + "|olives?|fruits? a coque|beurres?|cremes?|jus de fruits?|pizzas?|sandwich(?:e?s)?|gratins?)(?!\\p{L})",
            FLAGS);

    /** FAL-CE1-1 : le mais cereale, sur le texte garde accentue, pour ne pas refuser la conjonction. */
    private static final Pattern MAIS_CEREALE = Pattern.compile("(?<!\\p{L})maïs(?!\\p{L})", FLAGS);

    private static final Pattern POURQUOI = Pattern.compile("(?<!\\p{L})pourquoi(?!\\p{L})", FLAGS);

    private static final Pattern VARIER_OU_MANGER = Pattern.compile("(?<!\\p{L})(vari\\p{L}*|mang\\p{L}*)(?!\\p{L})",
            FLAGS);

    private static final Pattern MAUVAIS = Pattern.compile("(?<!\\p{L})mauvai(?:s|se|ses)(?!\\p{L})", FLAGS);

    /** BIA-4 : une question a l'enfant sur ce qu'il mange, ses repas ou sa maison. */
    private static final List<Pattern> QUESTION_A_L_ENFANT = List.of(
            Pattern.compile("(?<!\\p{L})tu (?:manges?|as mange|aimes?|preferes?|bois|choisis)(?!\\p{L})", FLAGS),
            Pattern.compile("(?<!\\p{L})(?:manges|as|aimes|preferes|bois|choisis|choisirais|prends)-tu(?!\\p{L})",
                    FLAGS),
            Pattern.compile("(?<!\\p{L})(?:ton|ta|tes) (?:repas|assiettes?|menus?|dejeuners?|diners?|gouters?"
                    + "|petits?-dejeuners?|cantine|maison|famille|parents)(?!\\p{L})", FLAGS));

    /** Regle 6 : les mots des cinq noms de famille et leurs formes de genre et de nombre. */
    private static final Pattern MOT_DE_FAMILLE = Pattern.compile("(?<!\\p{L})(poissons?|oeufs?|viandes?|laits?"
            + "|laitiers?|laitieres?|fruits?|legumes?|sucres?|sucrees?|feculents?)(?!\\p{L})", FLAGS);

    /** Regle 7 : un motif par famille attendue, des formes et non des racines (laitue est un legume). */
    private static final Map<String, Pattern> MOT_DE_LA_FAMILLE_ATTENDUE = Map.of(
            FRUITS_ET_LEGUMES, motif("fruits?|fruitiers?|fruitees?|legumes?"),
            FECULENTS, motif("feculents?"),
            LAITIERS, motif("laits?|laitiers?|laitieres?"),
            VIANDE, motif("viandes?|poissons?|oeufs?"),
            SUCRES, motif("sucres?|sucrees?|sucrer|sucreries?"));

    private static final Pattern PLUS_VARIE = Pattern.compile("(?<!\\p{L})plus varie(?!\\p{L})", FLAGS);

    private static final Pattern FAMILLE_DANS_L_ENONCE = Pattern.compile("(?<!\\p{L})familles?(?!\\p{L})", FLAGS);

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final String ESPACE_ORDINAIRE_AVANT_PONCTUATION = "[^\\u00A0] [?:;!]";

    private static final int CLASSEMENT_AU_MOINS = 5;

    private static final int MENU_AU_MOINS = 3;

    private static Pattern motif(String alternatives) {
        return Pattern.compile("(?<!\\p{L})(?:" + alternatives + ")(?!\\p{L})", FLAGS);
    }

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

    // ----- normalisation

    /** Minuscule, sans accent, apostrophe droite, traits d'union ordinaires, blancs ramenes a une espace. */
    static String plat(String texte) {
        String plat = PaquetLu.sansAccent(texte).replace('’', '\'').replace('‐', '-').replace('‑', '-');
        return BLANCS.matcher(plat).replaceAll(" ").strip();
    }

    /** Minuscule et NFC, sans retrait d'accent : la forme ou le mais cereale se distingue de la conjonction. */
    static String nfc(String texte) {
        String nfc = Normalizer.normalize(texte.toLowerCase(Locale.FRENCH), Normalizer.Form.NFC)
                .replace('’', '\'').replace('‐', '-').replace('‑', '-');
        return BLANCS.matcher(nfc).replaceAll(" ").strip();
    }

    private static String sansArticle(String texte) {
        return ARTICLE_DE_TETE.matcher(texte.strip()).replaceFirst("").strip();
    }

    private static String sansPointFinal(String texte) {
        return texte.endsWith(".") || texte.endsWith("!") ? texte.substring(0, texte.length() - 1).strip() : texte;
    }

    // ----- FER-3 : la tache est une fonction des trois propositions

    /** La famille que cette proposition ecrit : une egalite apres normalisation, jamais une sous-chaine. */
    static Optional<String> famille(String proposition) {
        String cle = sansArticle(sansPointFinal(plat(proposition)));
        return FAMILLES.contains(cle) ? Optional.of(cle) : Optional.empty();
    }

    /** Les aliments d'un menu : au moins deux, separes par des virgules ; une famille n'est jamais un menu. */
    static List<String> elementsDuMenu(String proposition) {
        if (famille(proposition).isPresent()) {
            return List.of();
        }
        List<String> elements = Arrays.stream(sansPointFinal(plat(proposition)).split(","))
                .map(PaquetCe1QlmFamillesDAlimentsTest::sansArticle).toList();
        return elements.size() >= 2 && elements.stream().noneMatch(String::isBlank) ? elements : List.of();
    }

    static Optional<String> tache(Item item) {
        List<String> propositions = item.propositions();
        if (propositions.stream().allMatch(proposition -> famille(proposition).isPresent())) {
            return Optional.of(CLASSEMENT);
        }
        if (propositions.stream().noneMatch(proposition -> elementsDuMenu(proposition).isEmpty())) {
            return Optional.of(MENU);
        }
        return Optional.empty();
    }

    private static boolean estClassement(Item item) {
        return tache(item).equals(Optional.of(CLASSEMENT));
    }

    private static boolean estMenu(Item item) {
        return tache(item).equals(Optional.of(MENU));
    }

    private static Map<String, Integer> comptesDesTaches(List<Item> items) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(tache(item).orElse("refus"), 1, Integer::sum));
        return comptes;
    }

    /**
     * FER-3 : tout item est un classement ou un menu, le classement porte au moins cinq items et le menu au
     * moins trois. Les comptes sont calcules d'abord sur les items, puis seulement compares a ceux que
     * l'en-tete ecrit : un en-tete ne rattrape jamais un paquet.
     */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        for (Item item : items) {
            assertThat(tache(item))
                    .as("FER-3 : '%s' n'est ni un classement (trois familles) ni un menu (trois menus)",
                            item.enonce())
                    .isPresent();
        }
        Map<String, Integer> comptes = comptesDesTaches(items);
        assertThat(comptes.getOrDefault(CLASSEMENT, 0))
                .as("FER-3 : le classement porte %d items, il en porte au moins %d", comptes.getOrDefault(CLASSEMENT, 0),
                        CLASSEMENT_AU_MOINS)
                .isGreaterThanOrEqualTo(CLASSEMENT_AU_MOINS);
        assertThat(comptes.getOrDefault(MENU, 0))
                .as("FER-3 : le menu porte %d items, il en porte au moins %d", comptes.getOrDefault(MENU, 0),
                        MENU_AU_MOINS)
                .isGreaterThanOrEqualTo(MENU_AU_MOINS);
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

    /** FER-1 : les cinq familles sont chacune la bonne reponse d'au moins un item de classement. */
    static void verifierLesCinqFamillesEnBonneReponse(List<Item> items) {
        Set<String> enBonneReponse = new TreeSet<>();
        items.stream().filter(PaquetCe1QlmFamillesDAlimentsTest::estClassement)
                .forEach(item -> famille(item.bonneReponse()).ifPresent(enBonneReponse::add));
        FAMILLES.forEach(famille -> assertThat(enBonneReponse)
                .as("FER-1 : « %s » n'est la bonne réponse d'aucun item de classement (%s)", famille, enBonneReponse)
                .contains(famille));
    }

    // ----- QMT-CE1-2 : la liste fermee des aliments, sa famille et sa source

    /** Un aliment de la liste : sa famille et la source que sa ligne ecrit. */
    record Aliment(String famille, String source) {
    }

    /** La liste fermee que l'en-tete ecrit : nom normalise, sans article, vers l'aliment. */
    static Map<String, Aliment> listeDesAliments(String entete) {
        Map<String, Aliment> liste = new LinkedHashMap<>();
        for (String ligne : entete.split("\\R")) {
            Matcher aliment = LIGNE_D_ALIMENT.matcher(ligne);
            if (aliment.matches()) {
                liste.put(sansArticle(plat(aliment.group(1))), new Aliment(aliment.group(2), aliment.group(3)));
            }
        }
        return liste;
    }

    /**
     * QMT-CE1-2 et FAL-CE1-1 : une liste, chaque ligne au format, aucun doublon, aucune source qui soit un
     * fichier du depot (X2), aucun des treize exclus, la pomme de terre dans les feculents.
     */
    static void verifierLaListeDesAliments(String entete) {
        long lignes = 0;
        for (String ligne : entete.split("\\R")) {
            if (LIGNE_D_ALIMENT_OUVERTE.matcher(ligne).find()) {
                lignes++;
                assertThat(LIGNE_D_ALIMENT.matcher(ligne).matches())
                        .as("QMT-CE1-2 : la ligne « %s » de la liste des aliments n'a pas le format « aliment nom : "
                                + "famille ; source : ... » avec l'une des cinq familles", ligne)
                        .isTrue();
            }
        }
        Map<String, Aliment> liste = listeDesAliments(entete);
        assertThat(liste).as("QMT-CE1-2 : l'en-tête du paquet n'écrit aucune liste des aliments").isNotEmpty();
        assertThat((long) liste.size()).as("QMT-CE1-2 : la liste des aliments porte un aliment deux fois")
                .isEqualTo(lignes);
        liste.forEach((nom, aliment) -> {
            assertThat(SOURCE_FICHIER_OU_NADIA.matcher(aliment.source()).find()
                    || SOURCE_D_UN_TEST.matcher(aliment.source()).find())
                    .as("QMT-CE1-2 : la source de « %s » (%s) est un fichier du dépôt ou une lecture de nadia-prof, "
                            + "elle doit être un document nommé hors du dépôt", nom, aliment.source())
                    .isFalse();
            Matcher exclu = EXCLU.matcher(plat(nom));
            boolean trouve = exclu.find();
            assertThat(trouve || MAIS_CEREALE.matcher(nfc(nom)).find())
                    .as("FAL-CE1-1 : la liste porte « %s », un des treize aliments exclus", nom).isFalse();
        });
        if (liste.containsKey("pomme de terre")) {
            assertThat(liste.get("pomme de terre").famille())
                    .as("FAL-CE1-1 : la pomme de terre est rangée avec les féculents")
                    .isEqualTo(FECULENTS);
        }
    }

    private static Optional<String> alimentDeclare(Item item) {
        List<String> declares = item.commentaires().stream().map(ALIMENT_DECLARE::matcher)
                .filter(Matcher::matches).map(trouve -> sansArticle(plat(trouve.group(1)))).toList();
        assertThat(declares).as("FAL-CE1-1 : '%s' ne déclare pas exactement un aliment", item.enonce())
                .hasSizeLessThanOrEqualTo(1);
        return declares.stream().findFirst();
    }

    /** FAL-CE1-1 : au moins un item range la pomme de terre avec les feculents (critere 2). */
    static void verifierLaPommeDeTerre(List<Item> items) {
        long rangees = items.stream().filter(PaquetCe1QlmFamillesDAlimentsTest::estClassement)
                .filter(item -> alimentDeclare(item).equals(Optional.of("pomme de terre")))
                .filter(item -> famille(item.bonneReponse()).equals(Optional.of(FECULENTS))).count();
        assertThat(rangees)
                .as("FAL-CE1-1 : aucun item de classement ne range la pomme de terre avec les féculents")
                .isGreaterThanOrEqualTo(1);
    }

    /**
     * QMT-CE1-2 et FAL-CE1-1 : l'aliment que declare un item de classement est dans la liste, l'enonce le
     * nomme, la bonne reponse est sa famille ; chaque aliment d'un menu est dans la liste.
     */
    static void verifierLesAlimentsDesItems(List<Item> items, String entete) {
        Map<String, Aliment> liste = listeDesAliments(entete);
        for (Item item : items) {
            if (estClassement(item)) {
                String nom = alimentDeclare(item).orElseThrow(() -> new AssertionError(
                        "FAL-CE1-1 : '" + item.enonce() + "' ne déclare aucun aliment"));
                assertThat(liste).as("QMT-CE1-2 : l'aliment « %s » de '%s' n'est pas dans la liste fermée des "
                        + "aliments, avec sa famille et sa source", nom, item.enonce()).containsKey(nom);
                assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(nom) + "(?!\\p{L})", FLAGS)
                        .matcher(plat(item.enonce())).find())
                        .as("FAL-CE1-1 : l'énoncé de '%s' ne nomme pas l'aliment qu'il déclare (%s)", item.enonce(),
                                nom)
                        .isTrue();
                assertThat(famille(item.bonneReponse()).orElse("?"))
                        .as("FAL-CE1-1 : la bonne réponse « %s » de '%s' n'est pas la famille que la liste donne à "
                                + "%s (%s)", item.bonneReponse(), item.enonce(), nom,
                                liste.containsKey(nom) ? liste.get(nom).famille() : "?")
                        .isEqualTo(liste.containsKey(nom) ? liste.get(nom).famille() : "?");
            } else if (estMenu(item)) {
                for (String proposition : item.propositions()) {
                    for (String element : elementsDuMenu(proposition)) {
                        assertThat(liste).as("QMT-CE1-2 : l'aliment « %s » du menu « %s » n'est pas dans la liste "
                                + "fermée des aliments, avec sa famille et sa source", element, proposition)
                                .containsKey(element);
                    }
                }
            }
        }
    }

    // ----- FAL-CE1-2 : la regle 3 des trois menus d'un item

    private static Set<String> famillesDuMenu(String menu, Map<String, Aliment> liste) {
        Set<String> familles = new TreeSet<>();
        elementsDuMenu(menu).forEach(element -> {
            if (liste.containsKey(element)) {
                familles.add(liste.get(element).famille());
            }
        });
        return familles;
    }

    /**
     * Regle 3 : les defauts d'un item de menu, un message par regle. Le menu attendu a strictement plus de
     * familles que chacun des deux autres ; les trois menus ont le meme nombre d'aliments ; chaque famille
     * de l'attendu est dans l'un des deux autres ; aucune famille n'est dans les deux autres sans etre dans
     * l'attendu ; aucun menu n'est fait seulement de produits sucres, l'attendu compris.
     */
    static List<String> defautsDuMenu(Item item, Map<String, Aliment> liste) {
        List<String> defauts = new ArrayList<>();
        String attendu = item.bonneReponse();
        String premier = item.distracteurs().get(0);
        String second = item.distracteurs().get(1);
        Set<String> deAttendu = famillesDuMenu(attendu, liste);
        Set<String> dePremier = famillesDuMenu(premier, liste);
        Set<String> deSecond = famillesDuMenu(second, liste);
        if (deAttendu.size() <= dePremier.size() || deAttendu.size() <= deSecond.size()) {
            defauts.add(String.format("regle 3 : le menu attendu '%s' n'a pas strictement plus de familles que chacun "
                    + "des deux autres (%d contre %d et %d)", attendu, deAttendu.size(), dePremier.size(),
                    deSecond.size()));
        }
        Set<Integer> tailles = new TreeSet<>(List.of(elementsDuMenu(attendu).size(), elementsDuMenu(premier).size(),
                elementsDuMenu(second).size()));
        if (tailles.size() > 1) {
            defauts.add("regle 3 : les trois menus de '" + item.enonce() + "' n'ont pas le même nombre d'aliments");
        }
        Set<String> seulesDansAttendu = new TreeSet<>(deAttendu);
        seulesDansAttendu.removeAll(dePremier);
        seulesDansAttendu.removeAll(deSecond);
        if (!seulesDansAttendu.isEmpty()) {
            defauts.add("regle 3 : la famille " + seulesDansAttendu + " n'est que dans le menu attendu");
        }
        Set<String> communesAuxAutres = new TreeSet<>(dePremier);
        communesAuxAutres.retainAll(deSecond);
        communesAuxAutres.removeAll(deAttendu);
        if (!communesAuxAutres.isEmpty()) {
            defauts.add("regle 3 : la famille " + communesAuxAutres
                    + " est dans les deux autres menus et absente du menu attendu");
        }
        for (String menu : item.propositions()) {
            if (famillesDuMenu(menu, liste).equals(Set.of(SUCRES))) {
                defauts.add("regle 3 : le menu '" + menu + "' n'est fait que de produits sucrés");
            }
        }
        return defauts;
    }

    private static List<String> defautsDeTousLesMenus(List<Item> items, String entete) {
        Map<String, Aliment> liste = listeDesAliments(entete);
        List<String> defauts = new ArrayList<>();
        items.stream().filter(PaquetCe1QlmFamillesDAlimentsTest::estMenu)
                .forEach(item -> defauts.addAll(defautsDuMenu(item, liste)));
        return defauts;
    }

    // ----- FAL-CE1-1, FAL-CE1-2, BIA-4 : les exclus et les formes

    /** FAL-CE1-1 : aucun des treize exclus dans l'enonce, une proposition ou l'indice. */
    static void verifierQueLesTreizeExclusSontAbsents(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher exclu = EXCLU.matcher(plat(champ));
                boolean trouve = exclu.find();
                assertThat(trouve)
                        .as("FAL-CE1-1 : « %s » (champ de '%s') porte un des treize aliments exclus : %s", champ,
                                item.enonce(), trouve ? exclu.group(1) : "")
                        .isFalse();
                assertThat(MAIS_CEREALE.matcher(nfc(champ)).find())
                        .as("FAL-CE1-1 : « %s » (champ de '%s') porte un des treize aliments exclus : maïs", champ,
                                item.enonce())
                        .isFalse();
            }
        }
    }

    /**
     * FAL-CE1-2, BIA-4, regles 2, 6 et 7 : pas de "pourquoi manger varie", pas de menu mauvais, aucune
     * question a l'enfant ; l'enonce de menu demande le plus varie sans dire famille ; l'enonce de
     * classement ne porte aucun mot d'un nom de famille ; l'indice de classement ne nomme ni l'aliment de
     * l'enonce ni un mot de la famille attendue.
     */
    static void verifierLesFormes(List<Item> items) {
        for (Item item : items) {
            String enonce = plat(item.enonce());
            assertThat(POURQUOI.matcher(enonce).find() && VARIER_OU_MANGER.matcher(enonce).find())
                    .as("FAL-CE1-2 : '%s' demande pourquoi manger varié, c'est l'item de cp-qlm-hygiene",
                            item.enonce())
                    .isFalse();
            for (String champ : item.champsLus()) {
                assertThat(MAUVAIS.matcher(plat(champ)).find())
                        .as("FAL-CE1-2 : « %s » (champ de '%s') qualifie un menu de mauvais", champ, item.enonce())
                        .isFalse();
                for (Pattern question : QUESTION_A_L_ENFANT) {
                    assertThat(question.matcher(plat(champ)).find())
                            .as("BIA-4 : « %s » (champ de '%s') interroge l'enfant sur ce qu'il mange", champ,
                                    item.enonce())
                            .isFalse();
                }
            }
            if (estMenu(item)) {
                assertThat(PLUS_VARIE.matcher(enonce).find() && !FAMILLE_DANS_L_ENONCE.matcher(enonce).find())
                        .as("FAL-CE1-2 : l'énoncé de menu '%s' ne demande pas le plus varié, ou donne le critère "
                                + "(famille)", item.enonce())
                        .isTrue();
            }
            if (estClassement(item)) {
                Matcher mot = MOT_DE_FAMILLE.matcher(enonce);
                boolean porte = mot.find();
                assertThat(porte)
                        .as("FAL-CE1-1 : l'énoncé de '%s' porte un mot d'un nom de famille : %s", item.enonce(),
                                porte ? mot.group(1) : "")
                        .isFalse();
                String indice = plat(item.indice());
                Pattern motDeLaFamille = MOT_DE_LA_FAMILLE_ATTENDUE.get(famille(item.bonneReponse()).orElseThrow());
                assertThat(motDeLaFamille.matcher(indice).find())
                        .as("FAL-CE1-1 : l'indice « %s » de '%s' contient un mot de la famille attendue", item.indice(),
                                item.enonce())
                        .isFalse();
                alimentDeclare(item).ifPresent(nom -> assertThat(
                        Pattern.compile("(?<!\\p{L})" + Pattern.quote(nom) + "(?!\\p{L})", FLAGS).matcher(indice).find())
                        .as("FAL-CE1-1 : l'indice « %s » nomme l'aliment de son énoncé (%s)", item.indice(), nom)
                        .isFalse());
            }
        }
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe104EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-04"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-04");
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
    void fer3ChaqueItemEstUnClassementOuUnMenuEtLEnTeteDitLesComptes() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    @Test
    void fer1LesCinqFamillesSontChacuneLaBonneReponseDUnItemDeClassement() {
        verifierLesCinqFamillesEnBonneReponse(paquet().items());
    }

    @Test
    void fal1LaPommeDeTerreEstRangeeAvecLesFeculents() {
        verifierLaPommeDeTerre(paquet().items());
    }

    @Test
    void qmt2LaListeDesAlimentsEstFermeeEtSesSourcesSontHorsDuDepot() {
        verifierLaListeDesAliments(paquet().entete());
    }

    @Test
    void qmt2LAlimentDeChaqueItemEstDansLaListeAvecSaFamille() {
        Paquet paquet = paquet();
        verifierLesAlimentsDesItems(paquet.items(), paquet.entete());
    }

    @Test
    void fal2LesTroisMenusDeChaqueItemTiennentLaRegleTrois() {
        Paquet paquet = paquet();
        assertThat(defautsDeTousLesMenus(paquet.items(), paquet.entete())).isEmpty();
    }

    @Test
    void fal1AucunDesTreizeExclusDansUnChampDuPaquet() {
        verifierQueLesTreizeExclusSontAbsents(paquet().items());
    }

    @Test
    void fal2Bia4EtLesReglesDeFormeDuPaquet() {
        verifierLesFormes(paquet().items());
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

    @Test
    void s1UnPaquetQuiNePoseQueLeClassementEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> classements = paquet.items().stream()
                .filter(PaquetCe1QlmFamillesDAlimentsTest::estClassement).toList();

        assertThat(classements).hasSizeLessThan(paquet.items().size());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(classements, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("le menu porte 0 items");
    }

    @Test
    void s2UnPaquetDontLeMenuNePorteQueDeuxItemsEstRefuse() {
        Paquet paquet = paquet();
        List<Item> avecDeuxMenus = new ArrayList<>(paquet.items().stream()
                .filter(PaquetCe1QlmFamillesDAlimentsTest::estClassement).toList());
        avecDeuxMenus.addAll(paquet.items().stream().filter(PaquetCe1QlmFamillesDAlimentsTest::estMenu).limit(2)
                .toList());

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxMenus, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("le menu porte 2 items");
    }

    @Test
    void s3UnPaquetDontLeClassementNePorteQueQuatreItemsEstRefuse() {
        Paquet paquet = paquet();
        List<Item> avecQuatre = new ArrayList<>(paquet.items().stream()
                .filter(PaquetCe1QlmFamillesDAlimentsTest::estClassement).limit(4).toList());
        avecQuatre.addAll(paquet.items().stream().filter(PaquetCe1QlmFamillesDAlimentsTest::estMenu).toList());

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecQuatre, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("le classement porte 4 items");
    }

    @Test
    void s4UnEnTeteQuiEcritUnMauvaisCompteEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replaceFirst("\\(8 items\\)", "(7 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("comptes");
    }

    @Test
    void s5UnPaquetPriveDUneFamilleEnBonneReponseEstRefuse() {
        for (String famille : FAMILLES) {
            List<Item> sansLaFamille = paquet().items().stream()
                    .filter(item -> !(estClassement(item) && famille(item.bonneReponse()).equals(Optional.of(famille))))
                    .toList();

            assertThat(sansLaFamille).as(famille).hasSizeLessThan(paquet().items().size());
            assertThatThrownBy(() -> verifierLesCinqFamillesEnBonneReponse(sansLaFamille))
                    .as(famille)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("FER-1")
                    .hasMessageContaining(famille);
        }
    }

    @Test
    void s6UnItemQuiDemandeLequelDeCesAlimentsEstUnFeculentEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");
        Item sonde = uneSonde(fautif, "sonde 01");

        assertThat(tache(sonde)).isEmpty();
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(List.of(sonde), fautif.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("ni un classement");
    }

    @Test
    void s7UnItemDeClassementDontUnePropositionEstUnAlimentEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");
        Item sonde = uneSonde(fautif, "sonde 02");

        assertThat(tache(sonde)).isEmpty();
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(List.of(sonde), fautif.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ni un classement");
    }

    @Test
    void s8UneLignePommeDeTerreRangeeAilleursQueDansLesFeculentsEstRefusee() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replaceFirst("(aliment pomme de terre : )feculents", "$1fruits et legumes");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLaListeDesAliments(faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAL-CE1-1")
                .hasMessageContaining("pomme de terre");
    }

    @Test
    void s9UnPaquetSansItemQuiRangeLaPommeDeTerreAvecLesFeculentsEstRefuse() {
        List<Item> sans = paquet().items().stream()
                .filter(item -> !alimentDeclare(item).equals(Optional.of("pomme de terre"))).toList();

        assertThat(sans).hasSizeLessThan(paquet().items().size());
        assertThatThrownBy(() -> verifierLaPommeDeTerre(sans))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("pomme de terre");
    }

    @Test
    void s10UnAlimentQuiNEstPasDansLaListeFermeeEstRefuseEnClassementEtEnMenu() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        for (String marqueur : List.of("sonde 03", "sonde 05")) {
            assertThatThrownBy(() -> verifierLesAlimentsDesItems(List.of(uneSonde(fautif, marqueur)), fautif.entete()))
                    .as(marqueur)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("QMT-CE1-2")
                    .hasMessageContaining("aubergine")
                    .hasMessageContaining("n'est pas dans la liste fermée");
        }
    }

    @Test
    void s11UneBonneReponseQuiNEstPasLaFamilleDeLaListeEstRefusee() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesAlimentsDesItems(List.of(uneSonde(fautif, "sonde 04")), fautif.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAL-CE1-1")
                .hasMessageContaining("n'est pas la famille que la liste donne");
    }

    @Test
    void s12LeMenuAttenduQuiNaPasStrictementPlusDeFamillesEstRefuseSeul() {
        assertUnSeulDefaut("sonde 06", "n'a pas strictement plus de familles");
    }

    @Test
    void s13TroisMenusDeTaillesDifferentesSontRefusesSeuls() {
        assertUnSeulDefaut("sonde 07", "pas le même nombre d'aliments");
    }

    @Test
    void s14UneFamilleDansLeSeulMenuAttenduEstRefuseeSeule() {
        assertUnSeulDefaut("sonde 08", "n'est que dans le menu attendu");
    }

    @Test
    void s15UneFamilleDansLesDeuxAutresEtAbsenteDeLAttenduEstRefuseeSeule() {
        assertUnSeulDefaut("sonde 09", "est dans les deux autres menus et absente du menu attendu");
    }

    @Test
    void s16UnMenuFaitSeulementDeProduitsSucresEstRefuseSeulMemeSiLAttenduEnPorte() {
        Paquet fautif = fautif("aliments-fautifs.yaml");
        Item sonde = uneSonde(fautif, "sonde 10");

        assertThat(elementsDuMenu(sonde.bonneReponse())).contains("gateau");
        assertUnSeulDefaut("sonde 10", "n'est fait que de produits sucrés");
    }

    @Test
    void p1UnMenuQuiTientLaRegleTroisNeLeveAucunDefaut() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        assertThat(defautsDuMenu(uneSonde(fautif, "sonde 11"), listeDesAliments(fautif.entete()))).isEmpty();
        verifierLaListeDesAliments(fautif.entete());
        verifierLesAlimentsDesItems(List.of(uneSonde(fautif, "sonde 11")), fautif.entete());
    }

    @Test
    void p2UneFamilleEcriteAvecDesVirgulesNEstJamaisLueCommeUnMenu() {
        assertThat(elementsDuMenu("viande, poisson et œufs")).isEmpty();
        assertThat(famille("viande, poisson et œufs")).contains(VIANDE);
        assertThat(elementsDuMenu("riz, poisson")).containsExactly("riz", "poisson");
        assertThat(elementsDuMenu("les pâtes")).isEmpty();
    }

    private static void assertUnSeulDefaut(String marqueur, String message) {
        Paquet fautif = fautif("aliments-fautifs.yaml");
        Item sonde = uneSonde(fautif, marqueur);

        assertThat(estMenu(sonde)).as(marqueur).isTrue();
        assertThat(defautsDuMenu(sonde, listeDesAliments(fautif.entete())))
                .as(marqueur)
                .hasSize(1)
                .allSatisfy(defaut -> assertThat(defaut).contains(message));
    }

    @Test
    void s17UnEnonceDeMenuQuiDonneLeCritereOuNeDemandePasLePlusVarieEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, "sonde 12"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAL-CE1-2")
                .hasMessageContaining("le plus varié");
        verifierLesFormes(List.of(uneSonde(fautif, "sonde 11")));
    }

    @Test
    void s18PourquoiMangerVarieEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, "sonde 13"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cp-qlm-hygiene");
    }

    @Test
    void s19UnMenuQualifieDeMauvaisEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, "sonde 14"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FAL-CE1-2")
                .hasMessageContaining("mauvais");
    }

    @Test
    void s20UneQuestionALEnfantSurCeQuIlMangeEstRefuseEnEnonceEtEnIndiceEtUnImperatifPasse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        for (String marqueur : List.of("sonde 15", "sonde 16")) {
            assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, marqueur))))
                    .as(marqueur)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("BIA-4");
        }
        verifierLesFormes(List.of(uneSonde(fautif, "sonde 17")));
    }

    @Test
    void s21UnEnonceDeClassementQuiPorteUnMotDeFamilleEstRefuseEtLaPommeLaCarotteLeRizPassent() {
        Paquet fautif = fautif("aliments-fautifs.yaml");
        List<String> refusees = List.of("sonde 18", "sonde 19", "sonde 20", "sonde 21", "sonde 26");

        for (String marqueur : refusees) {
            assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, marqueur))))
                    .as(marqueur)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("porte un mot d'un nom de famille");
        }
        for (String marqueur : List.of("sonde 22", "sonde 23", "sonde 24", "sonde 25")) {
            verifierLesFormes(List.of(uneSonde(fautif, marqueur)));
        }
    }

    @Test
    void s22UnEnonceDeClassementSurLeLaitSeulEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");
        Item sonde = uneSonde(fautif, "sonde 20");

        assertThat(sonde.enonce()).contains("le lait");
        assertThatThrownBy(() -> verifierLesFormes(List.of(sonde)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("lait");
    }

    @Test
    void s23UnIndiceQuiPorteUnMotDeLaFamilleAttendueOuNommeLAlimentEstRefuse() {
        Paquet fautif = fautif("aliments-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, "sonde 27"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("mot de la famille attendue");
        assertThatThrownBy(() -> verifierLesFormes(List.of(uneSonde(fautif, "sonde 29"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nomme l'aliment de son énoncé");
        verifierLesFormes(List.of(uneSonde(fautif, "sonde 28")));
        verifierLesFormes(List.of(uneSonde(fautif, "sonde 30")));
    }

    @Test
    void s24ChacunDesTreizeExclusEstRefuseDansUnChampDifferentEtLaConjonctionMaisPasse() {
        Paquet exclus = fautif("exclus.yaml");
        List<String> attendus = List.of("lentilles", "pois chiches", "maïs", "petits pois", "avocat", "olives",
                "fruits a coque", "beurre", "creme", "jus de fruits", "pizza", "sandwich", "gratin");

        assertThat(exclus.items()).as("un item fautif par exclu, plus la clause de la conjonction")
                .hasSize(attendus.size() + 1);
        for (int rang = 0; rang < attendus.size(); rang++) {
            Item item = exclus.items().get(rang);
            assertThatThrownBy(() -> verifierQueLesTreizeExclusSontAbsents(List.of(item)))
                    .as("l'exclu de l'item %d (%s) doit être refusé", rang + 1, item.enonce())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("FAL-CE1-1")
                    .hasMessageContaining(attendus.get(rang));
        }
        verifierQueLesTreizeExclusSontAbsents(List.of(exclus.items().get(attendus.size())));
    }

    @Test
    void s25UnExcluDansLaListeDesAlimentsEstRefuse() {
        String entete = paquet().entete() + "\n#   aliment lentilles : feculents ; source : Alimentarium, Les groupes\n";

        assertThatThrownBy(() -> verifierLaListeDesAliments(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("treize aliments exclus");
    }

    @Test
    void s26UneSourceQuiEstUnFichierDuDepotOuUneLectureDeNadiaEstRefusee() {
        List<String> refusees = List.of("lecture-nadia",
                "ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md, 3.5",
                "PaquetCe1QlmFamillesDAlimentsTest",
                "contenu/ce1-qlm-familles-d-aliments.yaml");
        for (String source : refusees) {
            assertThatThrownBy(() -> verifierLaListeDesAliments(uneLigne(source)))
                    .as(source)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("document nommé hors du dépôt");
        }
        for (String source : List.of("Programme national nutrition santé, Santé publique France",
                "Attestation de l'organisme, édition contestée", "l'instruction de s57",
                "Alimentarium, Les groupes alimentaires")) {
            verifierLaListeDesAliments(uneLigne(source));
        }
    }

    private static String uneLigne(String source) {
        return "#\n#   aliment yaourt : produits laitiers ; source : " + source + "\n";
    }

    @Test
    void s27UneLigneDeLaListeSansFamilleConnueOuSansSourceEstRefusee() {
        for (String ligne : List.of("#   aliment yaourt : legumes ; source : Alimentarium",
                "#   aliment yaourt : produits laitiers ; source :")) {
            assertThatThrownBy(() -> verifierLaListeDesAliments("#\n" + ligne + "\n"))
                    .as(ligne)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("n'a pas le format");
        }
        assertThatThrownBy(() -> verifierLaListeDesAliments("#\n# rien\n"))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("aucune liste des aliments");
    }

    @Test
    void s28UnAlimentEcritDeuxFoisDansLaListeEstRefuse() {
        String entete = uneLigne("Alimentarium") + "#   aliment yaourt : produits laitiers ; source : Alimentarium\n";

        assertThatThrownBy(() -> verifierLaListeDesAliments(entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("deux fois");
    }
}
