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
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-QLM-CE1-10, le paquet {@code ce1-qlm-paysages}, ligne {@code QLM-CE1-10} - identifier quelques
 * paysages a partir de leurs elements. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, 3.10 :
 * {@code PAY-CE1-1}, {@code PAY-CE1-2} ; section 2 : {@code FER-1}, {@code FER-3}) et regles de contenu du
 * critere 9 de la story ({@code arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md}, 1.1, corrige par
 * {@code arbitrage-nadia-contradictions-ce1-qlm-10-12.md}, {@code arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md}
 * et {@code arbitrage-nadia-trois-points-ce1-qlm-04-06-10.md}). Gardes de theo-archi : {@code G10-1} a
 * {@code G10-7}, {@code G11} a {@code G13} ({@code avis-theo-palier-5-gardes-ce1-qlm-10-12.md},
 * {@code avis-theo-palier-5-ajustements-gardes-ce1-qlm-10-12.md} et
 * {@code avis-theo-palier-5-dernier-ajustement-gardes-ce1-qlm-05-a-12.md}). Les conditions transverses sont
 * gardees une fois pour les neuf lots par {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Les tables sont celles de ce test, pas celles de l'en-tete du paquet.</b> Les deux listes de
 * {@code R10-1} et la table de {@code R10-3} sont des constantes ci-dessous, source
 * {@code arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md}, section 1 ({@code R10-8}) : un element neuf est un
 * diff de ce fichier, visible en revue. La reconnaissance est une egalite apres normalisation, jamais une
 * sous-chaine ; les pluriels sont enumeres.
 *
 * <p><b>Comment ce test lit {@code FER-3}.</b> La tache d'un item est une fonction de sa seule bonne
 * reponse : un des quatre paysages pose la tache {@code paysage}, un element des listes pose la tache
 * {@code element}, tout autre item est refuse. Aucune declaration de tache par item. Les deux lignes de
 * compte de l'en-tete sont relues contre le calcul.
 *
 * <p><b>Ce que ce test ne garde pas</b> : que chaque element et chaque paysage soit exact au CE1
 * ({@code R10-1}, {@code QC9}) ; un jugement hors des 48 formes de {@code G10-5} ; un lieu reel ; qu'un indice soit une
 * methode et non une periphrase par un mot hors des listes ; qu'un adjectif hors de l'exemple d'un indice
 * de B sur le littoral soit absent ({@code PAY-CE1-2}, {@code G13}) ; que l'explication de {@code littoral}
 * soit faite d'exemples et non d'une definition ; que l'en-tete ecrive les listes de {@code R10-1} ; que
 * deux operations distinctes servent les deux taches ({@code FER-3}). Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1QlmPaysagesTest {

    private static final String PAQUET = "ce1-qlm-paysages";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmPaysagesTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String PAYSAGE = "paysage";

    private static final String ELEMENT = "element";

    private static final String LITTORAL = "littoral";

    private static final String MONTAGNE = "montagne";

    private static final String CAMPAGNE = "campagne";

    private static final String VILLE = "ville";

    /** FORME_PAYSAGE : chaque forme ecrite, sans accent, vers son paysage. */
    private static final Map<String, String> FORME_PAYSAGE = Map.of(
            "littoral", LITTORAL,
            "littoraux", LITTORAL,
            "montagne", MONTAGNE,
            "montagnes", MONTAGNE,
            "campagne", CAMPAGNE,
            "campagnes", CAMPAGNE,
            "ville", VILLE,
            "villes", VILLE);

    private static final List<String> PAYSAGES = List.of(LITTORAL, MONTAGNE, CAMPAGNE, VILLE);

    /** R10-1, elements propres : l'element vers son seul paysage. */
    private static final Map<String, String> PROPRE = Map.of(
            "plage", LITTORAL,
            "mer", LITTORAL,
            "phare", LITTORAL,
            "sommet", MONTAGNE,
            "pistes de ski", MONTAGNE,
            "champ", CAMPAGNE,
            "ble", CAMPAGNE,
            "immeubles", VILLE,
            "metro", VILLE);

    /** R10-1, elements partages : l'element vers ses paysages. */
    private static final Map<String, Set<String>> PARTAGE = Map.of(
            "foret", Set.of(LITTORAL, MONTAGNE, CAMPAGNE),
            "riviere", Set.of(MONTAGNE, CAMPAGNE, VILLE),
            "neige", Set.of(MONTAGNE, CAMPAGNE, VILLE),
            "port", Set.of(LITTORAL, VILLE),
            "village", Set.of(LITTORAL, MONTAGNE, CAMPAGNE),
            "falaise", Set.of(LITTORAL, MONTAGNE),
            "ferme", Set.of(MONTAGNE, CAMPAGNE),
            "rue", Set.of(LITTORAL, MONTAGNE, CAMPAGNE, VILLE));

    /** R10-3 : la bonne reponse de B vers les paysages nommes admis, quatorze couples. */
    private static final Map<String, Set<String>> TABLE_B = Map.of(
            "mer", Set.of(MONTAGNE, CAMPAGNE),
            "phare", Set.of(MONTAGNE, CAMPAGNE),
            "plage", Set.of(CAMPAGNE),
            "pistes de ski", Set.of(LITTORAL, CAMPAGNE, VILLE),
            "sommet", Set.of(VILLE),
            "champ", Set.of(VILLE),
            "ble", Set.of(VILLE),
            "immeubles", Set.of(CAMPAGNE),
            "metro", Set.of(MONTAGNE, CAMPAGNE));

    /** FORME_ELEMENT : chaque forme ecrite, sans accent, singulier et pluriel enumeres, vers son element. */
    private static final Map<String, String> FORME_ELEMENT = new LinkedHashMap<>();

    static {
        formes("plage", "plage", "plages");
        formes("mer", "mer", "mers");
        formes("phare", "phare", "phares");
        formes("sommet", "sommet", "sommets");
        formes("pistes de ski", "piste de ski", "pistes de ski");
        formes("champ", "champ", "champs");
        formes("ble", "ble", "bles");
        formes("immeubles", "immeuble", "immeubles");
        formes("metro", "metro", "metros");
        formes("foret", "foret", "forets");
        formes("riviere", "riviere", "rivieres");
        formes("neige", "neige", "neiges");
        formes("port", "port", "ports");
        formes("village", "village", "villages");
        formes("falaise", "falaise", "falaises");
        formes("ferme", "ferme", "fermes");
        formes("rue", "rue", "rues");
    }

    private static void formes(String element, String... formes) {
        for (String forme : formes) {
            FORME_ELEMENT.put(forme, element);
        }
    }

    /** G10-4, regle (iii) : les seuls exemples admis apres "par exemple". */
    private static final Set<String> PROPRES_DU_LITTORAL = Set.of("plage", "mer", "phare");

    /** Forme A : le sujet est libre, la liste est coupee sur ", " et " et ". */
    private static final Pattern FORME_A = Pattern.compile(
            "^\\p{L}+ voit (?<liste>.+?)\\. (?:quel est ce paysage|dans quel paysage est-on) \\?$");

    private static final Pattern FORME_B = Pattern.compile(
            "^lequel ne voit-on pas (?<lieu>a la montagne|a la campagne|en ville|sur le littoral) \\?$");

    /** Forme B : le lieu vers son paysage. */
    private static final Map<String, String> LIEU = Map.of(
            "a la montagne", MONTAGNE,
            "a la campagne", CAMPAGNE,
            "en ville", VILLE,
            "sur le littoral", LITTORAL);

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    /** Un seul determinant de tete retire, sur une liste fermee, du plus long au plus court. */
    private static final Pattern DETERMINANT_DE_TETE =
            Pattern.compile("^(de la |de l'|un |une |des |les |le |la |l'|du |d'|de )");

    private static final Pattern SEGMENTS = Pattern.compile(", | et ");

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(paysage|element) : "
            + "(?:identifier le paysage a partir de ses elements|l'element qui n'appartient pas a un paysage nomme)"
            + " \\((\\d+) items?\\)");

    private static final Pattern MOT_LITTORAL = motifBorne(List.of("littoral"));

    private static final Pattern PAR_EXEMPLE = motifBorne(List.of("par exemple"));

    private static final Pattern PAYSAGE_DANS_UN_INDICE = motifBorne(FORME_PAYSAGE.keySet());

    /** G11, G10-4 B : formes cherchees dans un indice seulement, jamais admises dans un enonce ni une proposition. */
    private static final List<String> FORMES_DE_L_INDICE_SEULEMENT = List.of("piste", "pistes", "ski");

    private static final Pattern ELEMENT_DANS_UN_INDICE = motifBorne(java.util.stream.Stream.concat(
            FORME_ELEMENT.keySet().stream(), FORMES_DE_L_INDICE_SEULEMENT.stream()).toList());

    /** G10-5 : les dix-sept mots de R10-5, en quarante-huit formes enumerees, sur le texte garde accentue. */
    private static final List<String> JUGEMENT = List.of(
            "beau", "bel", "belle", "beaux", "belles", "joli", "jolie", "jolis", "jolies",
            "laid", "laide", "laids", "laides", "calme", "calmes", "tranquille", "tranquilles",
            "bruyant", "bruyante", "bruyants", "bruyantes", "dur", "dure", "durs", "dures",
            "facile", "faciles", "pauvre", "pauvres", "riche", "riches", "triste", "tristes",
            "sale", "sales", "dangereux", "dangereuse", "dangereuses", "ennuyeux", "ennuyeuse", "ennuyeuses",
            "mieux", "meilleur", "meilleure", "meilleurs", "meilleures", "pire", "pires");

    private static final Pattern MOT_DE_JUGEMENT = motifBorne(JUGEMENT);

    /** G10-6 : la deuxieme personne et les vacances, sur le texte garde accentue ; le tiret n'est pas une lettre. */
    private static final Pattern DEUXIEME_PERSONNE =
            Pattern.compile("(?<!\\p{L})(tu|toi|ton|ta|tes|vacances?)(?!\\p{L})", FLAGS);

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final String ESPACE_ORDINAIRE_AVANT_PONCTUATION = "[^\\u00A0] [?:;!]";

    /** FER-3 : la tache element porte au moins ce nombre d'items. */
    private static final int ITEMS_DE_LA_TACHE_ELEMENT = 3;

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

    private static String marqueur(int numero) {
        return String.format("sonde %02d", numero);
    }

    /** Une sonde jugee seule : le verificateur la refuse avec chacun de ces fragments dans son message. */
    private static void refusee(Paquet fautif, int numero, Consumer<List<Item>> verificateur, String... fragments) {
        List<Item> sonde = sonde(fautif, marqueur(numero));
        assertThat(sonde).as(marqueur(numero)).hasSize(1);
        var echec = assertThatThrownBy(() -> verificateur.accept(sonde)).as(marqueur(numero))
                .isInstanceOf(AssertionError.class);
        for (String fragment : fragments) {
            echec.hasMessageContaining(fragment);
        }
    }

    /** Une sonde jugee seule : le verificateur la laisse passer. */
    private static void passee(Paquet fautif, int numero, Consumer<List<Item>> verificateur) {
        List<Item> sonde = sonde(fautif, marqueur(numero));
        assertThat(sonde).as(marqueur(numero)).hasSize(1);
        verificateur.accept(sonde);
    }

    // ----- normalisation

    private static Pattern motifBorne(java.util.Collection<String> formes) {
        String alternatives = formes.stream().sorted(Comparator.comparingInt(String::length).reversed())
                .map(Pattern::quote).collect(java.util.stream.Collectors.joining("|"));
        return Pattern.compile("(?<!\\p{L})(?:" + alternatives + ")(?!\\p{L})", FLAGS);
    }

    /** Minuscule, sans accent, apostrophe droite, blancs (insecables compris) ramenes a une espace. */
    static String plat(String texte) {
        String plat = PaquetLu.sansAccent(texte).replace('\u2019', '\'');
        return BLANCS.matcher(plat).replaceAll(" ").strip();
    }

    /** Minuscule avec accents, sans retrait d'accent : "a duré" ne devient pas "a dure". */
    static String accentue(String texte) {
        String accentue = Normalizer.normalize(texte.toLowerCase(Locale.FRENCH), Normalizer.Form.NFC)
                .replace('\u2019', '\'');
        return BLANCS.matcher(accentue).replaceAll(" ").strip();
    }

    private static String sansPonctuationFinale(String texte) {
        return texte.endsWith(".") || texte.endsWith("!") ? texte.substring(0, texte.length() - 1).strip() : texte;
    }

    private static String sansDeterminant(String texte) {
        return DETERMINANT_DE_TETE.matcher(texte.strip()).replaceFirst("").strip();
    }

    /** Une proposition ou un segment : plat, sans point final, sans determinant de tete. */
    static String normalise(String texte) {
        return sansDeterminant(sansPonctuationFinale(plat(texte)));
    }

    // ----- la reconnaissance, une egalite et jamais une sous-chaine

    static Optional<String> element(String texte) {
        return Optional.ofNullable(FORME_ELEMENT.get(normalise(texte)));
    }

    static Optional<String> paysage(String texte) {
        return Optional.ofNullable(FORME_PAYSAGE.get(normalise(texte)));
    }

    /** FER-3 : un paysage en bonne reponse pose la tache paysage, un element la tache element, rien d'autre. */
    static Optional<String> tache(Item item) {
        if (paysage(item.bonneReponse()).isPresent()) {
            return Optional.of(PAYSAGE);
        }
        return element(item.bonneReponse()).isPresent() ? Optional.of(ELEMENT) : Optional.empty();
    }

    /** Le paysage nomme par l'enonce d'un item B, ou rien si l'enonce n'a pas la forme de B. */
    static Optional<String> lieu(Item item) {
        Matcher forme = FORME_B.matcher(plat(item.enonce()));
        return forme.matches() ? Optional.of(LIEU.get(forme.group("lieu"))) : Optional.empty();
    }

    private static boolean estSurLeLittoral(Item item) {
        return tache(item).equals(Optional.of(ELEMENT)) && lieu(item).equals(Optional.of(LITTORAL));
    }

    /** Les elements que l'enonce d'un item A nomme, un par segment de la liste ; refus si un segment n'en est pas un. */
    static List<String> elementsNommes(Item item) {
        Matcher forme = FORME_A.matcher(plat(item.enonce()));
        assertThat(forme.matches())
                .as("G10-1 : l'énoncé « %s » n'a pas la forme de la tâche paysage", item.enonce()).isTrue();
        List<String> elements = new ArrayList<>();
        for (String segment : SEGMENTS.split(forme.group("liste"))) {
            Optional<String> element = element(segment);
            assertThat(element)
                    .as("G10-1 : « %s » (élément nommé par '%s') n'est pas un élément de R10-1", segment,
                            item.enonce())
                    .isPresent();
            elements.add(element.get());
        }
        return elements;
    }

    private static List<String> formesDe(String element) {
        return FORME_ELEMENT.entrySet().stream().filter(forme -> forme.getValue().equals(element))
                .map(Map.Entry::getKey).toList();
    }

    // ----- G10-1 : les elements et les paysages sont ceux des listes

    static void verifierLesElementsEtLesPaysages(List<Item> items) {
        for (Item item : items) {
            Optional<String> tache = tache(item);
            assertThat(tache)
                    .as("G10-1 : « %s » (bonne réponse de '%s') n'est ni un des quatre paysages ni un élément de "
                            + "R10-1", item.bonneReponse(), item.enonce())
                    .isPresent();
            for (String distracteur : item.distracteurs()) {
                assertThat(paysage(distracteur).isPresent() || element(distracteur).isPresent())
                        .as("G10-1 : « %s » (proposition de '%s') n'est ni un des quatre paysages ni un élément de "
                                + "R10-1", distracteur, item.enonce())
                        .isTrue();
            }
            if (tache.get().equals(PAYSAGE)) {
                elementsNommes(item);
            } else {
                assertThat(lieu(item))
                        .as("G10-1 : l'énoncé « %s » n'a pas la forme de la tâche élément", item.enonce())
                        .isPresent();
            }
        }
    }

    // ----- G10-2 : un item de la tache paysage

    static void verifierLesItemsDeLaTachePaysage(List<Item> items) {
        for (Item item : items) {
            if (!tache(item).equals(Optional.of(PAYSAGE))) {
                continue;
            }
            assertThat(item.propositions().stream().map(PaquetCe1QlmPaysagesTest::paysage)
                    .flatMap(Optional::stream).distinct().count())
                    .as("G10-2 : les propositions de '%s' ne sont pas trois des quatre paysages", item.enonce())
                    .isEqualTo(3);
            String bonne = paysage(item.bonneReponse()).orElseThrow();
            Set<String> distracteurs = new TreeSet<>();
            item.distracteurs().forEach(distracteur -> distracteurs.add(paysage(distracteur).orElseThrow()));

            List<String> elements = elementsNommes(item).stream().distinct().toList();
            assertThat(elements.size())
                    .as("G10-2 : '%s' nomme moins de deux éléments distincts", item.enonce())
                    .isGreaterThanOrEqualTo(2);
            List<String> propres = elements.stream().filter(PROPRE::containsKey).toList();
            assertThat(propres.stream().anyMatch(element -> PROPRE.get(element).equals(bonne)))
                    .as("G10-2 : '%s' ne nomme aucun élément propre du paysage attendu (%s)", item.enonce(), bonne)
                    .isTrue();
            for (String propre : propres) {
                assertThat(PROPRE.get(propre))
                        .as("G10-2 : '%s' nomme un élément propre d'un autre paysage : %s", item.enonce(), propre)
                        .isEqualTo(bonne);
            }
            for (String element : elements) {
                if (PARTAGE.containsKey(element)) {
                    assertThat(PARTAGE.get(element))
                            .as("G10-2 : '%s' nomme l'élément partagé %s, que le paysage de la bonne réponse (%s) "
                                    + "n'a pas", item.enonce(), element, bonne)
                            .contains(bonne);
                    assertThat(distracteurs.stream().anyMatch(PARTAGE.get(element)::contains))
                            .as("G10-2 : '%s' nomme l'élément partagé %s, et aucun distracteur n'est un paysage de "
                                    + "cet élément", item.enonce(), element)
                            .isTrue();
                }
            }
            Set<String> proposes = new TreeSet<>();
            item.propositions().forEach(proposition -> paysage(proposition).ifPresent(proposes::add));
            assertThat(proposes.containsAll(List.of(LITTORAL, VILLE)))
                    .as("G10-2 (R10-2) : '%s' propose le littoral et la ville ensemble", item.enonce())
                    .isFalse();
        }
    }

    // ----- G10-3 : un item de la tache element

    private static boolean estUnProprePourLePaysage(Optional<String> element, String paysage) {
        return element.filter(propre -> paysage.equals(PROPRE.get(propre))).isPresent();
    }

    private static boolean estUnPartageDesDeuxPaysages(Optional<String> element, String premier, String second) {
        return element.filter(partage -> PARTAGE.containsKey(partage)
                && PARTAGE.get(partage).containsAll(List.of(premier, second))).isPresent();
    }

    static void verifierLesItemsDeLaTacheElement(List<Item> items) {
        for (Item item : items) {
            if (!tache(item).equals(Optional.of(ELEMENT))) {
                continue;
            }
            Optional<String> lieu = lieu(item);
            assertThat(lieu).as("G10-3 : l'énoncé « %s » n'a pas la forme de la tâche élément", item.enonce())
                    .isPresent();
            String nomme = lieu.get();
            String bonne = element(item.bonneReponse()).orElseThrow();
            assertThat(PROPRE.containsKey(bonne) && TABLE_B.get(bonne).contains(nomme))
                    .as("G10-3 : « %s » sous le paysage nommé %s (énoncé de '%s') n'est pas un couple de la table "
                            + "de R10-3", item.bonneReponse(), nomme, item.enonce())
                    .isTrue();
            String paysageDeLaBonne = PROPRE.get(bonne);
            Optional<String> premier = element(item.distracteurs().get(0));
            Optional<String> second = element(item.distracteurs().get(1));
            boolean conforme =
                    (estUnProprePourLePaysage(premier, nomme)
                            && estUnPartageDesDeuxPaysages(second, nomme, paysageDeLaBonne))
                    || (estUnProprePourLePaysage(second, nomme)
                            && estUnPartageDesDeuxPaysages(premier, nomme, paysageDeLaBonne));
            assertThat(conforme)
                    .as("G10-3 : les distracteurs de '%s' ne sont pas un élément propre du paysage nommé et un "
                            + "élément partagé avec le paysage de la bonne réponse", item.enonce())
                    .isTrue();
        }
    }

    // ----- G10-4, G11, G12, G13 : les indices

    /**
     * Tache paysage : aucun des quatre paysages, aucun element de R10-1, propre ou partage (G11, arbitrage
     * des trois points, 5.3). Tache element : aucune forme d'un element des trois propositions ; sur le
     * littoral, l'explication de littoral (G10-4, G13) ; hors du littoral, aucun littoral (G12).
     */
    static void verifierLesIndices(List<Item> items) {
        for (Item item : items) {
            String indice = plat(item.indice());
            Optional<String> tache = tache(item);
            if (tache.equals(Optional.of(PAYSAGE))) {
                Matcher paysage = PAYSAGE_DANS_UN_INDICE.matcher(indice);
                boolean nommePaysage = paysage.find();
                assertThat(nommePaysage)
                        .as("G10-4 : l'indice « %s » (tâche paysage) nomme un des quatre paysages : %s",
                                item.indice(), nommePaysage ? paysage.group() : "")
                        .isFalse();
                Matcher element = ELEMENT_DANS_UN_INDICE.matcher(indice);
                boolean nommeElement = element.find();
                assertThat(nommeElement)
                        .as("G11 : l'indice « %s » (tâche paysage) nomme un élément de R10-1 : %s", item.indice(),
                                nommeElement ? element.group() : "")
                        .isFalse();
            } else if (tache.equals(Optional.of(ELEMENT))) {
                for (String proposition : item.propositions()) {
                    Optional<String> element = element(proposition);
                    if (element.isPresent()) {
                        Matcher nomme = motifBorne(formesDe(element.get())).matcher(indice);
                        boolean trouve = nomme.find();
                        assertThat(trouve)
                                .as("G10-4 : l'indice « %s » (tâche élément) nomme une proposition de son item : %s",
                                        item.indice(), trouve ? nomme.group() : "")
                                .isFalse();
                    }
                }
                Optional<String> lieu = lieu(item);
                if (lieu.equals(Optional.of(LITTORAL))) {
                    verifierLIndiceSurLeLittoral(item, indice);
                } else if (lieu.isPresent()) {
                    assertThat(MOT_LITTORAL.matcher(indice).find())
                            .as("G12 : l'indice « %s » (tâche élément, hors du littoral) écrit littoral",
                                    item.indice())
                            .isFalse();
                }
                if (lieu.isPresent()) {
                    String horsExemple = lieu.get().equals(LITTORAL) ? sansLExemple(indice) : indice;
                    Matcher autre = ELEMENT_DANS_UN_INDICE.matcher(horsExemple);
                    boolean nommeAutre = autre.find();
                    assertThat(nommeAutre)
                            .as("G10-4 (R10-4 B) : l'indice « %s » (tâche élément) nomme un élément de R10-1 hors de "
                                    + "l'exemple : %s", item.indice(), nommeAutre ? autre.group() : "")
                            .isFalse();
                }
            }
        }
    }

    /** Le rang du premier ., ! ou ? de la suite, ou sa longueur s'il n'y en a pas. */
    private static int finDeLaZone(String suite) {
        int fin = suite.length();
        for (char ponctuation : new char[] {'.', '!', '?'}) {
            int rang = suite.indexOf(ponctuation);
            fin = rang >= 0 ? Math.min(fin, rang) : fin;
        }
        return fin;
    }

    /** L'indice prive de la zone qui va de "par exemple" au premier point, la meme que lit G13. */
    private static String sansLExemple(String indice) {
        Matcher parExemple = PAR_EXEMPLE.matcher(indice);
        if (!parExemple.find()) {
            return indice;
        }
        String suite = indice.substring(parExemple.end());
        return indice.substring(0, parExemple.start()) + suite.substring(finDeLaZone(suite));
    }

    private static void verifierLIndiceSurLeLittoral(Item item, String indice) {
        assertThat(MOT_LITTORAL.matcher(indice).find())
                .as("G10-4 : l'indice « %s » (tâche élément, sur le littoral) n'écrit pas littoral", item.indice())
                .isTrue();
        Matcher parExemple = PAR_EXEMPLE.matcher(indice);
        assertThat(parExemple.find())
                .as("G10-4 : l'indice « %s » (tâche élément, sur le littoral) n'écrit pas « par exemple »",
                        item.indice())
                .isTrue();
        String suite = indice.substring(parExemple.end()).strip();
        int fin = finDeLaZone(suite);
        List<String> exemples = Arrays.stream(SEGMENTS.split(suite.substring(0, fin).strip()))
                .map(String::strip).filter(exemple -> !exemple.isBlank()).toList();
        assertThat(exemples.size())
                .as("G10-4 : l'indice « %s » (tâche élément, sur le littoral) n'écrit pas un ou deux exemples "
                        + "après « par exemple »", item.indice())
                .isBetween(1, 2);
        for (String exemple : exemples) {
            assertThat(Optional.ofNullable(FORME_ELEMENT.get(sansDeterminant(exemple)))
                    .filter(PROPRES_DU_LITTORAL::contains).isPresent())
                    .as("G13 : l'indice « %s » (tâche élément, sur le littoral) donne un exemple qui n'est ni la "
                            + "plage, ni la mer, ni le phare : « %s »", item.indice(), exemple)
                    .isTrue();
        }
    }

    // ----- G10-5 et G10-6 : aucun jugement, aucune deuxieme personne ni vacances

    static void verifierAucunJugement(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher mot = MOT_DE_JUGEMENT.matcher(accentue(champ));
                boolean trouve = mot.find();
                assertThat(trouve)
                        .as("G10-5 : « %s » (champ de '%s') porte un mot de jugement : %s", champ, item.enonce(),
                                trouve ? mot.group() : "")
                        .isFalse();
            }
        }
    }

    static void verifierAucuneDeuxiemePersonneNiVacances(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher mot = DEUXIEME_PERSONNE.matcher(accentue(champ));
                boolean trouve = mot.find();
                assertThat(trouve)
                        .as("G10-6 : « %s » (champ de '%s') porte un mot interdit : %s", champ, item.enonce(),
                                trouve ? mot.group() : "")
                        .isFalse();
            }
        }
    }

    // ----- G10-7 : FER-1 et FER-3

    private static Map<String, Integer> comptesDesTaches(List<Item> items) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(tache(item).orElse("refus"), 1, Integer::sum));
        return comptes;
    }

    /**
     * Les comptes sont calcules d'abord sur les items, dans cet ordre : deux taches, au moins un item B sur le
     * littoral, la tache element au moins trois items, les quatre paysages en bonne reponse, puis seulement
     * les comptes que l'en-tete ecrit. Un en-tete ne rattrape jamais un paquet.
     */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        Map<String, Integer> comptes = comptesDesTaches(items);
        assertThat(comptes.keySet())
                .as("G10-7 (FER-3) : le paquet ne sert pas deux tâches distinctes (%s)", comptes)
                .containsExactlyInAnyOrder(PAYSAGE, ELEMENT);
        assertThat(items.stream().anyMatch(PaquetCe1QlmPaysagesTest::estSurLeLittoral))
                .as("G10-7 (QMT-CE1-1) : aucun item B « sur le littoral », où l'indice explique littoral")
                .isTrue();
        assertThat(comptes.get(ELEMENT))
                .as("G10-7 (FER-3) : la tâche élément porte %d items, la moins servie en porte au moins %d",
                        comptes.get(ELEMENT), ITEMS_DE_LA_TACHE_ELEMENT)
                .isGreaterThanOrEqualTo(ITEMS_DE_LA_TACHE_ELEMENT);
        Set<String> enBonneReponse = new TreeSet<>();
        items.stream().filter(item -> tache(item).equals(Optional.of(PAYSAGE)))
                .forEach(item -> paysage(item.bonneReponse()).ifPresent(enBonneReponse::add));
        PAYSAGES.forEach(paysage -> assertThat(enBonneReponse)
                .as("G10-7 (FER-1) : « %s » n'est la bonne réponse d'aucun item de la tâche paysage (%s)", paysage,
                        enBonneReponse)
                .contains(paysage));
        Map<String, Integer> ecrits = new TreeMap<>();
        for (String ligne : entete.split("\\R")) {
            Matcher tache = TACHE_DE_L_ENTETE.matcher(PaquetLu.sansAccent(ligne));
            if (tache.find()) {
                ecrits.put(tache.group(1), Integer.parseInt(tache.group(2)));
            }
        }
        assertThat(ecrits)
                .as("G10-7 (FER-3) : les comptes que le commentaire du paquet écrit ne sont pas ceux du paquet")
                .isEqualTo(comptes);
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe110EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-10"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-10");
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
    void typ1LEspaceAvantLePointDInterrogationEstInsecable() {
        for (Item item : paquet().items()) {
            for (String champ : item.champsLus()) {
                assertThat(champ).as("TYP-1 : « %s » porte une espace ordinaire avant ?, :, ; ou !", champ)
                        .doesNotContainPattern(ESPACE_ORDINAIRE_AVANT_PONCTUATION);
            }
        }
    }

    @Test
    void g101ChaqueElementEtChaquePaysageDuPaquetEstUneFormeDesListes() {
        verifierLesElementsEtLesPaysages(paquet().items());
    }

    @Test
    void g102ChaqueItemDeLaTachePaysageNommeUnElementQuiTrancheEtSesPartagesSontServis() {
        verifierLesItemsDeLaTachePaysage(paquet().items());
    }

    @Test
    void g103ChaqueItemDeLaTacheElementEstUnCoupleDeLaTableAvecSesDeuxDistracteurs() {
        verifierLesItemsDeLaTacheElement(paquet().items());
    }

    @Test
    void g104EtG11G12G13ChaqueIndiceNeNommeNiLaReponseNiUneProposition() {
        verifierLesIndices(paquet().items());
    }

    @Test
    void g105AucunJugementDeValeurDansLePaquet() {
        verifierAucunJugement(paquet().items());
    }

    @Test
    void g106AucuneDeuxiemePersonneNiVacancesDansLePaquet() {
        verifierAucuneDeuxiemePersonneNiVacances(paquet().items());
    }

    @Test
    void g107DeuxTachesUnItemBSurLeLittoralLesQuatrePaysagesEtLesComptesDeLEnTete() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    // ----- ADR-0013 obligation 3, G10-1 : un jeu fautif commite

    @Test
    void g101UnElementHorsDesListesUnLieuHorsDeLaTableOuUneFormeHorsDesDeuxTachesSontRefuses() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 1, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1", "cascade");
        refusee(fautif, 2, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1", "désert");
        refusee(fautif, 6, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1",
                "forme de la tâche élément");
        refusee(fautif, 7, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1", "cascade");
        refusee(fautif, 8, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1",
                "forme de la tâche paysage");
        refusee(fautif, 9, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1",
                "forme de la tâche paysage");
        passee(fautif, 3, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages);
    }

    @Test
    void g101UneTailleDevantUnElementEstRefuseeParG101EtPasParG105() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 4, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1", "grand champ");
        refusee(fautif, 5, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1", "grande ville");
        passee(fautif, 4, PaquetCe1QlmPaysagesTest::verifierAucunJugement);
        passee(fautif, 5, PaquetCe1QlmPaysagesTest::verifierAucunJugement);
    }

    // ----- G10-2

    @Test
    void g102UnItemQuiNeNommeQuUnElementEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 10, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "moins de deux éléments");
    }

    @Test
    void g102UnItemQuiNommeDeuxFoisLeMemeElementEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 19, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "moins de deux éléments");
    }

    @Test
    void g102UnItemSansAucunElementProprePourLePaysageAttenduEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 11, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "aucun élément propre du paysage attendu");
    }

    @Test
    void g102UnItemQuiNommeUnElementProprePourUnAutrePaysageEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 12, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "élément propre d'un autre paysage", "immeubles");
    }

    @Test
    void g102UnElementPartageNommeSansDistracteurDeSesPaysagesEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 13, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "élément partagé falaise");
    }

    @Test
    void g102ChaqueElementPartageNommeAUnDistracteurDeSesPaysages() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 15, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage, "G10-2",
                "élément partagé falaise");
        passee(fautif, 17, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage);
    }

    @Test
    void g102UnePropositionQuiNEstPasUnPaysageEstRefusee() {
        refusee(fautif("paysages-fautifs.yaml"), 16, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "trois des quatre paysages");
    }

    @Test
    void g102DeuxPropositionsIdentiquesSontRefusees() {
        refusee(fautif("paysages-fautifs.yaml"), 18, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "trois des quatre paysages");
    }

    @Test
    void g102LExempleDeNadiaProfPasse() {
        passee(fautif("paysages-fautifs.yaml"), 14, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage);
    }

    @Test
    void g102UnElementPartageNommeQueLePaysageDeLaBonneReponseNAPasEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 70, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage,
                "G10-2", "élément partagé port", "que le paysage de la bonne réponse");
    }

    @Test
    void g102LeLittoralEtLaVilleProposesEnsembleSontRefuses() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 71, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage, "G10-2",
                "propose le littoral et la ville ensemble");
        refusee(fautif, 72, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTachePaysage, "G10-2",
                "propose le littoral et la ville ensemble");
    }

    // ----- G10-3

    @Test
    void g103UnCoupleHorsDeLaTableEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        for (int sonde : List.of(20, 21, 27, 28)) {
            refusee(fautif, sonde, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTacheElement, "G10-3",
                    "couple de la table");
        }
    }

    @Test
    void g103UnDistracteurPartageQuiNEstPasAuxDeuxPaysagesOuQuiNEstPasUnPartageEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        for (int sonde : List.of(22, 23, 29)) {
            refusee(fautif, sonde, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTacheElement, "G10-3",
                    "élément propre du paysage nommé et un élément partagé");
        }
    }

    @Test
    void g103UnDistracteurProprePourLePaysageNommeEstExige() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        for (int sonde : List.of(25, 26)) {
            refusee(fautif, sonde, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTacheElement, "G10-3",
                    "élément propre du paysage nommé et un élément partagé");
        }
    }

    @Test
    void g103LExempleDeNadiaProfPasse() {
        passee(fautif("paysages-fautifs.yaml"), 24, PaquetCe1QlmPaysagesTest::verifierLesItemsDeLaTacheElement);
    }

    // ----- G10-4 et G11, tache paysage

    @Test
    void g11UnIndiceDeAQuiNommeUnElementProprePourUnPaysageProposeEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 30, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "mer");
        refusee(fautif, 32, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "immeubles");
        refusee(fautif, 33, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "sommet");
    }

    @Test
    void g11UnIndiceDeAQuiNommeUnElementProprePourLePaysageNonProposeEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 31, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "mer");
        refusee(fautif, 35, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "plage");
    }

    @Test
    void g11UnIndiceDeAQuiNommeUnElementPartageEstRefuseAuSingulierEtAuPluriel() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 34, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "foret");
        refusee(fautif, 39, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "forets");
    }

    @Test
    void g104UnIndiceDeAQuiNommeUnPaysageEstRefuseMemeSiLeLittoralEstExpliqueParDesExemples() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 37, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme un des quatre paysages",
                "littoral");
        refusee(fautif, 38, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme un des quatre paysages",
                "littoral");
    }

    @Test
    void g104UnPlurielDeCampagneOuDeLittoralDansUnIndiceDeAEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 76, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme un des quatre paysages",
                "campagnes");
        refusee(fautif, 77, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme un des quatre paysages",
                "littoraux");
    }

    @Test
    void g11PisteEtSkiSontCherchesDansUnIndiceSeulementEtPasAdmisDansUnEnonce() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 78, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "pistes");
        refusee(fautif, 79, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G11", "ski");
        refusee(fautif, 80, PaquetCe1QlmPaysagesTest::verifierLesElementsEtLesPaysages, "G10-1", "pistes");
    }

    @Test
    void g104LaMethodeDeNadiaProfPasseEtLaRechercheEstBornee() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        passee(fautif, 36, PaquetCe1QlmPaysagesTest::verifierLesIndices);
        passee(fautif, 40, PaquetCe1QlmPaysagesTest::verifierLesIndices);
    }

    // ----- G10-4, G12 et G13, tache element

    @Test
    void g104UnIndiceDeBQuiNommeUneDeSesPropositionsAuSingulierOuAuPlurielEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 41, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme une proposition",
                "forets");
        refusee(fautif, 42, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme une proposition",
                "sommet");
        refusee(fautif, 50, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "nomme une proposition", "plage");
        passee(fautif, 43, PaquetCe1QlmPaysagesTest::verifierLesIndices);
    }

    @Test
    void g104UnIndiceDeBQuiNommeUnElementHorsDeLExempleEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 73, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "hors de l'exemple", "neige");
        refusee(fautif, 74, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "hors de l'exemple", "neige");
        refusee(fautif, 75, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4", "hors de l'exemple", "pistes");
    }

    @Test
    void g12LittoralDansLIndiceDUnItemBHorsDuLittoralEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 44, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G12", "littoral");
        passee(fautif, 45, PaquetCe1QlmPaysagesTest::verifierLesIndices);
    }

    @Test
    void g104UnIndiceDeBSurLeLittoralQuiNEcritPasLittoralEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 47, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4",
                "n'écrit pas littoral");
    }

    @Test
    void g104UnIndiceDeBSurLeLittoralSansParExempleEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 48, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4",
                "n'écrit pas « par exemple »");
    }

    @Test
    void g104UnIndiceDeBSurLeLittoralSansAucunExempleEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 55, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G10-4",
                "un ou deux exemples");
    }

    @Test
    void g104UnIndiceDeBSurLeLittoralAUnOuDeuxExemplesQuiPassent() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        passee(fautif, 45, PaquetCe1QlmPaysagesTest::verifierLesIndices);
        passee(fautif, 46, PaquetCe1QlmPaysagesTest::verifierLesIndices);
    }

    @Test
    void g13UnExempleQuiNEstNiLaPlageNiLaMerNiLePhareEstRefuse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        refusee(fautif, 49, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G13", "port");
        refusee(fautif, 54, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G13", "neige");
    }

    @Test
    void g13UnAdjectifSurLExempleEstRefuse() {
        refusee(fautif("paysages-fautifs.yaml"), 52, PaquetCe1QlmPaysagesTest::verifierLesIndices, "G13",
                "grande mer");
    }

    @Test
    void g13CeQueLaGardeNeLitPasPasse() {
        Paquet fautif = fautif("paysages-fautifs.yaml");

        passee(fautif, 51, PaquetCe1QlmPaysagesTest::verifierLesIndices);
        passee(fautif, 53, PaquetCe1QlmPaysagesTest::verifierLesIndices);
    }

    // ----- G10-5 et G10-6

    @Test
    void g105ChacuneDesQuaranteHuitFormesDeJugementEstRefuseeDansUnIndice() {
        Paquet jugements = fautif("jugements-fautifs.yaml");
        List<Item> formes = jugements.items().subList(0, JUGEMENT.size());
        Set<String> vues = new TreeSet<>();

        assertThat(formes).hasSize(48);
        for (Item item : formes) {
            String forme = item.indice().substring(item.indice().lastIndexOf(' ') + 1).replace(".", "");
            vues.add(forme);
            assertThatThrownBy(() -> verifierAucunJugement(List.of(item))).as(forme)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("G10-5")
                    .hasMessageContaining("mot de jugement : " + forme);
        }
        assertThat(vues).as("le jeu fautif porte chacune des 48 formes, une fois").hasSize(48);
    }

    @Test
    void g105UnJugementDansUnEnonceUnePropositionUnPlusJoliOuUnNomEstRefuse() {
        Paquet jugements = fautif("jugements-fautifs.yaml");

        refusee(jugements, 49, PaquetCe1QlmPaysagesTest::verifierAucunJugement, "G10-5", "beau");
        refusee(jugements, 50, PaquetCe1QlmPaysagesTest::verifierAucunJugement, "G10-5", "tranquille");
        refusee(jugements, 51, PaquetCe1QlmPaysagesTest::verifierAucunJugement, "G10-5", "joli");
        refusee(jugements, 52, PaquetCe1QlmPaysagesTest::verifierAucunJugement, "G10-5", "calme");
    }

    @Test
    void g105BeaucoupDurentEtADureNeSontPasDesJugements() {
        Paquet jugements = fautif("jugements-fautifs.yaml");

        for (int sonde : List.of(53, 54, 55)) {
            passee(jugements, sonde, PaquetCe1QlmPaysagesTest::verifierAucunJugement);
        }
    }

    @Test
    void g106LaDeuxiemePersonneEtLesVacancesSontRefusees() {
        Paquet fautif = fautif("paysages-fautifs.yaml");
        Map<Integer, String> mots = new TreeMap<>(Map.of(60, "tu", 61, "tes", 62, "vacances", 63, "toi", 64, "ton",
                65, "tu", 66, "ta"));

        mots.forEach((sonde, mot) -> refusee(fautif, sonde,
                PaquetCe1QlmPaysagesTest::verifierAucuneDeuxiemePersonneNiVacances, "G10-6",
                "mot interdit : " + mot));
    }

    @Test
    void g106UnImperatifSansPronomPasseEtLaMontagneEtLesPistesDeSkiAussi() {
        passee(fautif("paysages-fautifs.yaml"), 67,
                PaquetCe1QlmPaysagesTest::verifierAucuneDeuxiemePersonneNiVacances);
    }

    // ----- G10-7 : le paquet reel mute, en-tete inchange

    private static List<Item> sans(List<Item> items, java.util.function.Predicate<Item> aRetirer) {
        return items.stream().filter(aRetirer.negate()).toList();
    }

    @Test
    void g107UnPaquetQuiNePoseQueLaTachePaysageEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> paysages = sans(paquet.items(), item -> tache(item).equals(Optional.of(ELEMENT)));

        assertThat(paysages).hasSizeLessThan(paquet.items().size());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paysages, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("G10-7")
                .hasMessageContaining("deux tâches");
    }

    @Test
    void g107UnPaquetSansItemEstRefuse() {
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(List.of(), paquet().entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("G10-7")
                .hasMessageContaining("deux tâches");
    }

    @Test
    void g107UnPaquetPriveDeSesItemsBSurLeLittoralEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> sansLittoral = sans(paquet.items(), PaquetCe1QlmPaysagesTest::estSurLeLittoral);

        assertThat(sansLittoral).hasSizeLessThan(paquet.items().size());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(sansLittoral, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("G10-7")
                .hasMessageContaining("sur le littoral");
    }

    @Test
    void g107UnPaquetDontLaTacheElementNePorteQueDeuxItemsEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> avecDeuxElements = new ArrayList<>(
                sans(paquet.items(), item -> tache(item).equals(Optional.of(ELEMENT))));
        avecDeuxElements.addAll(paquet.items().stream().filter(item -> tache(item).equals(Optional.of(ELEMENT)))
                .sorted(Comparator.comparing(item -> !estSurLeLittoral(item))).limit(2).toList());

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxElements, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("G10-7")
                .hasMessageContaining("la moins servie");
    }

    @Test
    void g107UnPaquetPriveDUnPaysageEnBonneReponseEstRefuse() {
        for (String paysage : PAYSAGES) {
            List<Item> sansLePaysage = sans(paquet().items(),
                    item -> paysage(item.bonneReponse()).equals(Optional.of(paysage)));

            assertThat(sansLePaysage).as(paysage).hasSizeLessThan(paquet().items().size());
            assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(sansLePaysage, paquet().entete()))
                    .as(paysage)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("G10-7")
                    .hasMessageContaining("FER-1")
                    .hasMessageContaining("« " + paysage + " »");
        }
    }

    @Test
    void g107UnEnTeteQuiEcritUnMauvaisCompteDeTachesEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replaceFirst("\\(5 items\\)", "(4 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("G10-7")
                .hasMessageContaining("comptes");
    }

    @Test
    void g107UnEnTeteSansLesLignesDeCompteEstRefuse() {
        Paquet paquet = paquet();
        String sansComptes = paquet.entete().replaceAll("(?m)^#\\s+(paysage|element) : .*$", "#");

        assertThat(sansComptes).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), sansComptes))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("G10-7")
                .hasMessageContaining("comptes");
    }

    @Test
    void laTableDeLaTacheElementPorteQuatorzeCouples() {
        assertThat(TABLE_B.values().stream().mapToInt(Set::size).sum()).isEqualTo(14);
    }
}
