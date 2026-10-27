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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-QLM-CE1-08, le paquet {@code ce1-qlm-dangers-electricite}, ligne {@code QLM-CE1-08} - connaitre
 * les dangers de l'electricite de la maison et les gestes qui les evitent. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, 3.8 :
 * {@code DEL-CE1-1}, {@code DEL-CE1-2}) et regles de contenu du critere 9 de la story
 * ({@code arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md}, corrige par
 * {@code arbitrage-nadia-contradictions-ce1-qlm-06-08.md} et
 * {@code arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md}). Les formes des gardes sont celles de
 * theo-archi ({@code avis-theo-palier-5-gardes-ce1-qlm-06-08.md}, ajuste par
 * {@code avis-theo-palier-5-ajustements-gardes-ce1-qlm-06-08.md} et
 * {@code avis-theo-palier-5-dernier-ajustement-gardes-ce1-qlm-05-a-12.md} et
 * {@code avis-theo-palier-5-gardes-apres-lecture-ce1-qlm-05-08.md}, section -08). Les conditions transverses
 * sont gardees une fois pour les neuf lots par
 * {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit les deux taches.</b> Chaque item declare a cote de lui sa tache
 * ({@code danger} ou {@code geste}) et sa situation ({@code prise}, {@code eau}, {@code fil}) : les
 * declarations sont verifiees, jamais crues. En tache geste la situation est lue dans l'enonce, en tache
 * danger dans la bonne reponse ; une bonne reponse de geste est l'une des neuf formes de {@link #FORMES},
 * une bonne reponse de danger n'en est jamais une. Les comptes des deux taches sont calcules sur les items
 * puis compares a ceux de l'en-tete.
 *
 * <p><b>Les gardes lexicales</b> lisent {@code Item.champsLus()} (enonce, indice, bonne reponse,
 * distracteurs) sur le texte normalise par {@link #plat}, jamais les commentaires. Les formes sont des
 * egalites ou des motifs a bornes de mot ; la forme {@code blesse} accentuee ne se lit que sur le texte
 * qui garde ses accents.
 *
 * <p><b>Ce que ce test ne garde pas</b> (lu par nadia-prof, {@code QC9}) : qu'une situation de la tache
 * danger soit vraiment dangereuse et ses distracteurs vraiment sans danger ; qu'un distracteur de la tache
 * geste soit plausible et faux, qu'il ne nomme pas l'imprudence par un mot de jugement et que les
 * distracteurs negatifs ne se repartissent pas en signe du bon geste ; une abstention ecrite hors de ses
 * cinq formes ou apres le debut de la proposition ; qu'un enonce {@code eau} dise <i>deja branche</i> sous
 * {@code ne pas toucher l'appareil} et <i>veut poser</i> sous {@code ne pas l'approcher de l'eau} ; qu'un
 * indice soit une methode, qu'il ne designe pas la bonne reponse par un autre mot que ceux des deux listes
 * de {@link #verifierLesIndices} ; les traits partages par deux distracteurs de la tache danger hors du mot
 * ({@link #verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot} ne lit que les mots) ; une alerte a
 * quelqu'un d'autre qu'un adulte, ou apres un debut en {@code ne} ; un personnage nomme blesse par une
 * periphrase ; {@code TRV-CE1-6} au-dela de ses quatre mots.
 */
class PaquetCe1QlmDangersElectriciteTest {

    private static final String PAQUET = "ce1-qlm-dangers-electricite";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmDangersElectriciteTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String DANGER = "danger";

    private static final String GESTE = "geste";

    private static final String PRISE = "prise";

    private static final String EAU = "eau";

    private static final String FIL = "fil";

    /** FER-1 : les trois situations de la parenthese de DEL-CE1-1. */
    private static final List<String> SITUATIONS = List.of(PRISE, EAU, FIL);

    /** Regle 1, tache geste : les trois gestes de DEL-CE1-1, tels que l'arbitrage du 2026-10-27 les nomme. */
    private static final String TOUCHER = "ne pas toucher";

    private static final String APPROCHER = "ne pas approcher de l'eau";

    private static final String MAINS = "ne pas brancher avec les mains mouillees";

    private static final List<String> GESTES = List.of(TOUCHER, APPROCHER, MAINS);

    /** Une forme admise de la regle 2 : son geste, ses situations, ce que l'enonce doit porter. */
    record Forme(String geste, Set<String> situations, boolean mouillees, boolean lampe) {
    }

    /** Regle 2 : les neuf formes admises, sur le texte normalise, sans point final. */
    static final Map<String, Forme> FORMES = Map.ofEntries(
            Map.entry("ne pas toucher le fil", new Forme(TOUCHER, Set.of(FIL), false, false)),
            Map.entry("laisser le fil sans le toucher", new Forme(TOUCHER, Set.of(FIL), false, false)),
            Map.entry("ne pas toucher la prise", new Forme(TOUCHER, Set.of(PRISE), false, false)),
            Map.entry("ne rien mettre dans la prise", new Forme(TOUCHER, Set.of(PRISE), false, false)),
            Map.entry("ne pas toucher l'appareil", new Forme(TOUCHER, Set.of(EAU), false, false)),
            Map.entry("ne pas l'approcher de l'eau", new Forme(APPROCHER, Set.of(EAU), false, false)),
            Map.entry("attendre d'avoir les mains seches", new Forme(MAINS, Set.of(EAU), true, false)),
            Map.entry("se secher d'abord les mains", new Forme(MAINS, Set.of(EAU), true, false)),
            Map.entry("ne pas brancher la lampe", new Forme(MAINS, Set.of(EAU), true, true)));

    private static Pattern motif(String alternatives) {
        return Pattern.compile("(?<!\\p{L})(?:" + alternatives + ")(?!\\p{L})", FLAGS);
    }

    /** Le repere de chaque situation dans un texte normalise : les mots des regles 2 et 7 de nadia-prof. */
    private static final Map<String, Pattern> REPERE_DE_SITUATION = Map.of(
            PRISE, motif("prises?"),
            FIL, motif("fils?"),
            EAU, motif("eau|lavabos?|eviers?|mouill\\p{L}*"));

    private static final Pattern MOUILLEES = motif("mouill\\p{L}*");

    private static final Pattern LAMPE = motif("lampes?");

    private static final Pattern TACHE_DECLAREE = Pattern.compile("^DEL-CE1-1 tache : (danger|geste)$");

    private static final Pattern SITUATION_DECLAREE = Pattern.compile("^DEL-CE1-1 situation : (prise|eau|fil)$");

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(danger|geste) : .*\\((\\d+) items?");

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    /** DEL-CE1-1 : aucune pile, dans aucun sens, pile ou face compris ; empiler et pilote ne sont pas lus. */
    private static final Pattern PILE = motif("piles?");

    /** DEL-CE1-2 : les trois familles, sur le texte sans accent. */
    private static final Pattern ELECTROCUTION = motif("electrocut\\p{L}*");

    private static final Pattern BRULURE = motif("brul\\p{L}*");

    private static final Pattern MORT = motif("mort|morte|morts|mortes|mortel|mortelle|mortels|mortelles|mourir"
            + "|mourra|mourrait|mourront|meurt|meurent|meurs|tue|tues|tuee|tuees|tuer|tuent|tuera|tuerait");

    /**
     * DEL-CE1-2, regle 5 : se blesser et se faire mal conjugues a un temps simple, sauf apres on, on ne ou
     * personne ne (le present generique). L'infinitif n'est jamais lu.
     */
    private static final Pattern SE_BLESSE_CONJUGUE = Pattern.compile("(?<!(?<!\\p{L})on )(?<!(?<!\\p{L})on ne )"
            + "(?<!(?<!\\p{L})personne ne )(?<!\\p{L})se (?:bless(?:e|es|ent|ait|aient|a|erent|era|erait|eront"
            + "|eraient)|(?:fait|font|faisait|faisaient|fit|firent|fera|ferait|feront|feraient) mal)(?!\\p{L})",
            FLAGS);

    /** DEL-CE1-2 : le participe, lu sur le texte qui garde ses accents (blesse, au present, est generique). */
    private static final Pattern BLESSE_PARTICIPE = motif("bless(?:é|ée|és|ées)");

    private static final Pattern S_EST_FAIT_MAL = motif("(?:s'est|s'était|se sont|s'étaient) fait(?:e|s|es)? mal");

    /** TRV-CE1-6 : les quatre mots refuses. */
    private static final Pattern VECU_DE_L_ENFANT = motif("chez toi|ta maison|ta chambre|baignoires?");

    private static final Pattern ADULTE = motif("adultes?");

    private static final Pattern NE_DEBUT = Pattern.compile("^ne(?!\\p{L})", FLAGS);

    /** Regle 3 : les cinq abstentions, au debut de la proposition, {@code attendre} compris. */
    private static final Pattern ABSTENTION = Pattern.compile("^(?:s'eloigner|attendre|ne rien toucher"
            + "|ne pas s'approcher|continuer a jouer ailleurs)(?!\\p{L})", FLAGS);

    /** Regle 4, ce que l'indice d'un item geste ne nomme pas : le geste et l'adulte. {@code sech} exclut le seche-cheveux. */
    private static final Pattern INDICE_DE_GESTE = motif("touch\\p{L}*|approch\\p{L}*|branch\\p{L}*"
            + "|sech\\p{L}*(?!-cheveux)|prev(?:en|ien)\\p{L}*|adultes?");

    /** Regle 4, ce que l'indice d'un item danger ne nomme pas : l'etat dangereux et l'eau. */
    private static final Pattern INDICE_DE_DANGER = motif("abim\\p{L}*|cass(?:e|es|ee|ees|er|ent)|eaux?");

    private static final Pattern NE_EN_TETE = Pattern.compile("^ne (?:pas|rien|jamais)(?!\\p{L})", FLAGS);

    /** Regle 3 : ne pas ou ne jamais suivi d'une forme de la regle 2 nie la precaution du meme item. */
    private static final Pattern PRECAUTION_NIEE = Pattern.compile("^ne (?:pas|jamais) (.+)$", FLAGS);

    private static final String ESPACE_ORDINAIRE_AVANT_PONCTUATION = "[^\\u00A0] [?:;!]";

    /** FER-3 : la tache la moins servie porte au moins ce nombre d'items. */
    private static final int ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE = 3;

    private static Paquet paquet() {
        return PaquetLu.lirePaquet(FICHIER_CONTENU);
    }

    private static Paquet fautif() {
        return PaquetLu.lirePaquet(CONTRE_EXEMPLE.resolve("dangers-fautifs.yaml"));
    }

    /** Les items du jeu fautif qui portent ce marqueur en commentaire, pour mordre un verificateur a la fois. */
    private static List<Item> sonde(Paquet fautif, String marqueur) {
        return fautif.items().stream().filter(item -> item.commentaires().stream()
                .anyMatch(ligne -> ligne.equals(marqueur) || ligne.startsWith(marqueur + " "))).toList();
    }

    // ----- normalisation

    /** Minuscule, sans accent, apostrophe droite, blancs ramenes a une espace. */
    static String plat(String texte) {
        return BLANCS.matcher(PaquetLu.sansAccent(texte).replace('’', '\'')).replaceAll(" ").strip();
    }

    /** Minuscule et NFC, sans retrait d'accent : blesse (present) ne se distingue de blesse (participe) que la. */
    static String nfc(String texte) {
        String nfc = Normalizer.normalize(texte.toLowerCase(Locale.FRENCH), Normalizer.Form.NFC).replace('’', '\'');
        return BLANCS.matcher(nfc).replaceAll(" ").strip();
    }

    /** Pour une egalite : le texte normalise sans le point ou le point d'exclamation final. */
    static String egalite(String texte) {
        String plat = plat(texte);
        return plat.endsWith(".") || plat.endsWith("!") ? plat.substring(0, plat.length() - 1).strip() : plat;
    }

    // ----- les declarations

    private static List<String> trouvees(Item item, Pattern declaration) {
        return item.commentaires().stream().map(declaration::matcher).filter(Matcher::matches)
                .map(trouvee -> trouvee.group(1)).toList();
    }

    private static String declaration(Item item, Pattern declaration) {
        List<String> trouvees = trouvees(item, declaration);
        assertThat(trouvees)
                .as("DEL-CE1-1 : '%s' ne déclare pas exactement une fois %s", item.enonce(), declaration.pattern())
                .hasSize(1);
        return trouvees.get(0);
    }

    private static boolean estUnItem(Item item, String tache) {
        return declaration(item, TACHE_DECLAREE).equals(tache);
    }

    /** TRV-CE1-1 : un item ne porte que les champs que ce test lit, aucune explication, aucun champ servi en plus. */
    static void verifierLesCles(List<Item> items) {
        for (Item item : items) {
            assertThat(item.cles())
                    .as("TRV-CE1-1 : '%s' ne porte que les champs que ce test lit", item.enonce())
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    /**
     * Regle 1 : tache et situation declarees une fois chacune et vraies. En tache geste le repere de la
     * situation est dans l'enonce, en tache danger il est dans la bonne reponse, qui n'est jamais un geste.
     */
    static void verifierLesDeclarations(List<Item> items) {
        for (Item item : items) {
            String tache = declaration(item, TACHE_DECLAREE);
            String situation = declaration(item, SITUATION_DECLAREE);
            String lu = tache.equals(GESTE) ? item.enonce() : item.bonneReponse();
            assertThat(REPERE_DE_SITUATION.get(situation).matcher(plat(lu)).find())
                    .as("DEL-CE1-1 : '%s' déclare la situation %s, mais %s ne porte pas son repère", item.enonce(),
                            situation, tache.equals(GESTE) ? "son énoncé" : "sa bonne réponse")
                    .isTrue();
            if (tache.equals(DANGER)) {
                assertThat(FORMES)
                        .as("DEL-CE1-1 : la bonne réponse « %s » de '%s' est un geste, une tâche danger a une "
                                + "situation pour bonne réponse", item.bonneReponse(), item.enonce())
                        .doesNotContainKey(egalite(item.bonneReponse()));
            }
        }
    }

    // ----- regle 2, DEL-CE1-1 : les gestes

    /**
     * Regle 2 : la bonne reponse d'un item geste est, par egalite, l'une des neuf formes, dans sa situation
     * declaree ; les trois formes des mains exigent des mains mouillees dans l'enonce, et la lampe l'enonce
     * qui la nomme.
     */
    static void verifierLesGestes(List<Item> items) {
        for (Item item : items) {
            if (!estUnItem(item, GESTE)) {
                continue;
            }
            Forme forme = FORMES.get(egalite(item.bonneReponse()));
            assertThat(forme)
                    .as("DEL-CE1-1 : la bonne réponse « %s » de '%s' n'est pas une forme admise de la règle 2",
                            item.bonneReponse(), item.enonce())
                    .isNotNull();
            String situation = declaration(item, SITUATION_DECLAREE);
            assertThat(forme.situations())
                    .as("DEL-CE1-1 : la forme « %s » de '%s' est hors de la situation déclarée %s",
                            item.bonneReponse(), item.enonce(), situation)
                    .contains(situation);
            if (forme.mouillees()) {
                assertThat(MOUILLEES.matcher(plat(item.enonce())).find())
                        .as("DEL-CE1-1 : la forme « %s » de '%s' est sans mains mouillées dans l'énoncé",
                                item.bonneReponse(), item.enonce())
                        .isTrue();
            }
            if (forme.lampe()) {
                assertThat(LAMPE.matcher(plat(item.enonce())).find())
                        .as("DEL-CE1-1 : la forme « %s » de '%s' nomme une lampe que l'énoncé ne nomme pas",
                                item.bonneReponse(), item.enonce())
                        .isTrue();
            }
        }
    }

    /** Regle 3 : jamais deux formes de la regle 2 dans un meme item, sur tout item. */
    static void verifierUnSeulGeste(List<Item> items) {
        for (Item item : items) {
            long formes = item.propositions().stream().filter(proposition -> FORMES.containsKey(egalite(proposition)))
                    .count();
            assertThat(formes)
                    .as("TRV-CE1-2 : '%s' porte %d propositions qui sont des formes de la règle 2, un seul geste "
                            + "par item", item.enonce(), formes)
                    .isLessThanOrEqualTo(1);
        }
    }

    /** Regle 3 : la bonne reponse n'est jamais la seule proposition en ne pas, ne rien ou ne jamais. */
    static void verifierLaGrammaire(List<Item> items) {
        for (Item item : items) {
            boolean bonneNegative = NE_EN_TETE.matcher(plat(item.bonneReponse())).find();
            boolean distracteurNegatif = item.distracteurs().stream()
                    .anyMatch(distracteur -> NE_EN_TETE.matcher(plat(distracteur)).find());
            assertThat(bonneNegative && !distracteurNegatif)
                    .as("TRV-CE1-2 : la bonne réponse « %s » de '%s' est la seule proposition en ne pas, ne rien "
                            + "ou ne jamais", item.bonneReponse(), item.enonce())
                    .isFalse();
        }
    }

    // ----- FER-3, FER-1, regle 1 : les comptes et les trois gestes

    /** FER-3 : deux taches, la moins servie au moins trois items, et l'en-tete dit les comptes calcules. */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        verifierLesDeclarations(items);
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(declaration(item, TACHE_DECLAREE), 1, Integer::sum));
        assertThat(comptes.keySet()).as("FER-3 : le paquet ne sert pas deux tâches distinctes (%s)", comptes)
                .containsExactlyInAnyOrder(DANGER, GESTE);
        comptes.forEach((tache, compte) -> assertThat(compte)
                .as("FER-3 : la tâche %s porte %d items, la moins servie en porte au moins %d", tache, compte,
                        ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE)
                .isGreaterThanOrEqualTo(ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE));
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

    /** FER-1 : chaque situation est celle d'au moins un item danger et d'au moins un item geste. */
    static void verifierLesTroisSituationsEnDangerEtEnGeste(List<Item> items) {
        verifierLesDeclarations(items);
        for (String tache : List.of(DANGER, GESTE)) {
            Set<String> servies = new TreeSet<>();
            items.stream().filter(item -> estUnItem(item, tache))
                    .forEach(item -> servies.add(declaration(item, SITUATION_DECLAREE)));
            for (String situation : SITUATIONS) {
                assertThat(servies)
                        .as("FER-1 : la situation %s n'est celle d'aucun item %s (%s)", situation, tache, servies)
                        .contains(situation);
            }
        }
    }

    /** Regle 1 : au moins un item geste de situation eau ne parle pas de mains mouillees. */
    static void verifierQuUnItemDEauNeParlePasDesMains(List<Item> items) {
        verifierLesDeclarations(items);
        assertThat(items.stream().anyMatch(item -> estUnItem(item, GESTE)
                && declaration(item, SITUATION_DECLAREE).equals(EAU)
                && !MOUILLEES.matcher(plat(item.enonce())).find()))
                .as("DEL-CE1-1 : aucun item geste de situation eau ne se passe de mains mouillées")
                .isTrue();
    }

    /**
     * Regle 1, 6.4 : chacun des trois gestes est le geste de la bonne reponse d'au moins un item geste.
     * Les formes de fil et de prise sont toutes ne pas toucher, celles de l'eau sont l'approche et les
     * mains : avec les situations de FER-1, cette clause porte B a quatre items au moins.
     */
    static void verifierQueChaqueGesteEstLaBonneReponseDUnItemGeste(List<Item> items) {
        verifierLesDeclarations(items);
        Set<String> servis = new TreeSet<>();
        for (Item item : items) {
            Forme forme = estUnItem(item, GESTE) ? FORMES.get(egalite(item.bonneReponse())) : null;
            if (forme != null) {
                servis.add(forme.geste());
            }
        }
        for (String geste : GESTES) {
            assertThat(servis)
                    .as("6.4 : aucun item de B n'a pour bonne réponse le geste %s (B au moins 4 items) (%s)", geste,
                            servis)
                    .contains(geste);
        }
    }

    // ----- les mots

    /** DEL-CE1-1 : le mot pile n'est employe dans aucun champ lu, dans aucun sens. */
    static void verifierQueLeMotPileNEstJamaisEcrit(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                assertThat(PILE.matcher(plat(champ)).find())
                        .as("DEL-CE1-1 : « %s » (champ de '%s') porte le mot pile", champ, item.enonce())
                        .isFalse();
            }
        }
    }

    /** DEL-CE1-2 : aucune electrocution, brulure ou mort, aucun personnage nomme blesse. */
    static void verifierQueRienNeDecritUnAccident(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Optional<String> trouve = unAccident(champ);
                assertThat(trouve)
                        .as("DEL-CE1-2 : « %s » (champ de '%s') décrit un accident ou une blessure", champ,
                                item.enonce())
                        .isEmpty();
            }
        }
    }

    private static Optional<String> unAccident(String champ) {
        String plat = plat(champ);
        for (Pattern famille : List.of(ELECTROCUTION, BRULURE, MORT, SE_BLESSE_CONJUGUE)) {
            Matcher trouve = famille.matcher(plat);
            if (trouve.find()) {
                return Optional.of(trouve.group());
            }
        }
        String accentue = nfc(champ);
        for (Pattern famille : List.of(BLESSE_PARTICIPE, S_EST_FAIT_MAL)) {
            Matcher trouve = famille.matcher(accentue);
            if (trouve.find()) {
                return Optional.of(trouve.group());
            }
        }
        return Optional.empty();
    }

    /** TRV-CE1-6 : ni chez toi, ni ta maison, ni ta chambre, ni la baignoire. */
    static void verifierQueLaSituationNEstPasCelleDeLEnfant(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher trouve = VECU_DE_L_ENFANT.matcher(plat(champ));
                boolean refuse = trouve.find();
                assertThat(refuse)
                        .as("TRV-CE1-6 : « %s » (champ de '%s') porte un mot refusé : %s", champ, item.enonce(),
                                refuse ? trouve.group() : "")
                        .isFalse();
            }
        }
    }

    // ----- les propositions

    /**
     * Regle 2 : aucune alerte a un adulte, ni en bonne reponse ni en distracteur (NRC-CE1-1) : toute
     * proposition qui nomme un adulte sans commencer par ne est refusee, le refus de signaler commence par ne.
     */
    static void verifierQueAucuneAlerteNEstUneProposition(List<Item> items) {
        for (Item item : items) {
            for (String proposition : item.propositions()) {
                String plat = plat(proposition);
                assertThat(ADULTE.matcher(plat).find() && !NE_DEBUT.matcher(plat).find())
                        .as("NRC-CE1-1 : « %s » (proposition de '%s') est une alerte à un adulte, la bonne réponse "
                                + "de cp-emc-alerte", proposition, item.enonce())
                        .isFalse();
            }
        }
    }

    /** Regle 3 : aucune des cinq abstentions en distracteur, ancree au debut de la proposition. */
    static void verifierQuAucuneAbstentionNEstUnDistracteur(List<Item> items) {
        for (Item item : items) {
            for (String distracteur : item.distracteurs()) {
                assertThat(ABSTENTION.matcher(plat(distracteur)).find())
                        .as("TRV-CE1-2 : « %s » (distracteur de '%s') est une abstention, une seconde réponse",
                                distracteur, item.enonce())
                        .isFalse();
            }
        }
    }

    /** Regle 3 : un distracteur ne nie jamais, par ne pas ou ne jamais, une forme de la regle 2, quel que soit l'item. */
    static void verifierQuAucunDistracteurNeNieUneForme(List<Item> items) {
        for (Item item : items) {
            for (String distracteur : item.distracteurs()) {
                Matcher niee = PRECAUTION_NIEE.matcher(egalite(distracteur));
                boolean refuse = niee.matches() && FORMES.containsKey(niee.group(1));
                assertThat(refuse)
                        .as("TRV-CE1-2 : « %s » (distracteur de '%s') nie une forme de la règle 2, la paire de "
                                + "contraires donne la réponse", distracteur, item.enonce())
                        .isFalse();
            }
        }
    }

    /** Regle 1 : un enonce de geste porte les reperes d'une seule situation ; lavabo et eau sont la meme. */
    static void verifierQuUnEnonceNePorteQuUneSituation(List<Item> items) {
        for (Item item : items) {
            if (!estUnItem(item, GESTE)) {
                continue;
            }
            Set<String> portees = new TreeSet<>();
            REPERE_DE_SITUATION.forEach((situation, repere) -> {
                if (repere.matcher(plat(item.enonce())).find()) {
                    portees.add(situation);
                }
            });
            assertThat(portees.size())
                    .as("DEL-CE1-1 : l'énoncé de '%s' porte les repères de plusieurs situations : %s, une seule "
                            + "situation par énoncé", item.enonce(), portees)
                    .isLessThanOrEqualTo(1);
        }
    }

    /**
     * Regle 4, partie lexicale : l'indice ne nomme ni le geste (item geste) ni l'etat dangereux (item danger),
     * selon la tache declaree par l'item.
     */
    static void verifierLesIndices(List<Item> items) {
        for (Item item : items) {
            Pattern interdits = estUnItem(item, GESTE) ? INDICE_DE_GESTE : INDICE_DE_DANGER;
            Matcher trouve = interdits.matcher(plat(item.indice()));
            boolean refuse = trouve.find();
            assertThat(refuse)
                    .as("TRV-CE1-5 : l'indice '%s' de '%s' nomme %s", item.indice(), item.enonce(),
                            refuse ? trouve.group() : "")
                    .isFalse();
        }
    }

    private static Set<String> mots(String proposition) {
        Set<String> mots = new TreeSet<>();
        for (String segment : egalite(proposition).split(" ")) {
            if (segment.chars().anyMatch(Character::isLetterOrDigit)) {
                mots.add(segment);
            }
        }
        return mots;
    }

    /**
     * Regle 3 en tache danger : un mot commun aux deux distracteurs et absent de la bonne reponse les
     * designe tous deux comme faux ; les articles comptent. La tache geste n'est pas lue.
     */
    static void verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot(List<Item> items) {
        for (Item item : items) {
            if (!estUnItem(item, DANGER)) {
                continue;
            }
            Set<String> communs = new TreeSet<>(mots(item.distracteurs().get(0)));
            communs.retainAll(mots(item.distracteurs().get(1)));
            communs.removeAll(mots(item.bonneReponse()));
            assertThat(communs)
                    .as("TRV-CE1-2 : les deux distracteurs de '%s' ont en commun %s, que la bonne réponse n'a pas",
                            item.enonce(), communs)
                    .isEmpty();
        }
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe108EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-08"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-08");
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
    void trv1ChaqueItemNePorteQueLesChampsQueCeTestLit() {
        verifierLesCles(paquet().items());
    }

    @Test
    void del1ChaqueItemDeclareSaTacheEtSaSituationEtLesDeclarationsSontVraies() {
        verifierLesDeclarations(paquet().items());
    }

    @Test
    void del1LaBonneReponseDeChaqueItemGesteEstUneFormeDeLaRegle2DansSaSituation() {
        verifierLesGestes(paquet().items());
    }

    @Test
    void trv2AucunItemNePorteDeuxFormesDeLaRegle2() {
        verifierUnSeulGeste(paquet().items());
    }

    @Test
    void trv2LaBonneReponseNEstJamaisLaSeuleEnNePasNeRienOuNeJamais() {
        verifierLaGrammaire(paquet().items());
    }

    @Test
    void fer3DeuxTachesLaMoinsServiePorteTroisItemsEtLeCommentaireDitLesComptes() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    @Test
    void fer1ChaqueSituationEstServieEnDangerEtEnGeste() {
        verifierLesTroisSituationsEnDangerEtEnGeste(paquet().items());
    }

    @Test
    void del1UnItemGesteDeSituationEauNeParlePasDesMainsMouillees() {
        verifierQuUnItemDEauNeParlePasDesMains(paquet().items());
    }

    @Test
    void del1ChaqueGesteEstLaBonneReponseDUnItemGeste() {
        verifierQueChaqueGesteEstLaBonneReponseDUnItemGeste(paquet().items());
    }

    @Test
    void del1AucunChampNePorteLeMotPile() {
        verifierQueLeMotPileNEstJamaisEcrit(paquet().items());
    }

    @Test
    void del2AucunChampNeDecritUnAccidentNiNeBlesseUnPersonnageNomme() {
        verifierQueRienNeDecritUnAccident(paquet().items());
    }

    @Test
    void trv6AucunChampNeSituePeLEnfantChezLuiNiNeNommeLaBaignoire() {
        verifierQueLaSituationNEstPasCelleDeLEnfant(paquet().items());
    }

    @Test
    void nrc1AucuneAlerteAUnAdulteNEstUneProposition() {
        verifierQueAucuneAlerteNEstUneProposition(paquet().items());
    }

    @Test
    void del1UnEnonceDeGesteNePorteQuUneSituation() {
        verifierQuUnEnonceNePorteQuUneSituation(paquet().items());
    }

    @Test
    void trv2AucuneAbstentionNEstUnDistracteur() {
        verifierQuAucuneAbstentionNEstUnDistracteur(paquet().items());
    }

    @Test
    void trv2AucunDistracteurNeNieUneFormeDeLaRegle2() {
        verifierQuAucunDistracteurNeNieUneForme(paquet().items());
    }

    @Test
    void trv5AucunIndiceNeNommeLeGesteNiLEtatDangereux() {
        verifierLesIndices(paquet().items());
    }

    @Test
    void trv2LesDeuxDistracteursDUnItemDangerNePartagentAucunMotAbsentDeLaBonneReponse() {
        verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot(paquet().items());
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

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    /** Une sonde du jeu fautif, jugee seule, est refusee par cette garde avec ces extraits de message. */
    private static void estRefusee(String sonde, Consumer<List<Item>> garde, String... extraits) {
        List<Item> trouvees = sonde(fautif(), sonde);
        assertThat(trouvees).as(sonde).hasSize(1);
        var attendu = assertThatThrownBy(() -> garde.accept(trouvees)).as(sonde).isInstanceOf(AssertionError.class);
        for (String extrait : extraits) {
            attendu.hasMessageContaining(extrait);
        }
    }

    /** Une sonde du jeu fautif, jugee seule, passe cette garde. */
    @SafeVarargs
    private static void passe(String sonde, Consumer<List<Item>>... gardes) {
        List<Item> trouvees = sonde(fautif(), sonde);
        assertThat(trouvees).as(sonde).hasSize(1);
        for (Consumer<List<Item>> garde : gardes) {
            garde.accept(trouvees);
        }
    }

    private static void sontRefusees(List<String> sondes, Consumer<List<Item>> garde, String extrait) {
        sondes.forEach(sonde -> estRefusee(sonde, garde, extrait));
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnChampQueLeTestNeLitPas() {
        estRefusee("sonde 01", PaquetCe1QlmDangersElectriciteTest::verifierLesCles, "TRV-CE1-1",
                "ne porte que les champs");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneDeclarationFausseOuAbsente() {
        estRefusee("sonde 02", PaquetCe1QlmDangersElectriciteTest::verifierLesDeclarations, "DEL-CE1-1",
                "déclare la situation fil", "son énoncé ne porte pas son repère");
        estRefusee("sonde 05", PaquetCe1QlmDangersElectriciteTest::verifierLesDeclarations, "DEL-CE1-1",
                "déclare la situation fil", "sa bonne réponse ne porte pas son repère");
        estRefusee("sonde 03", PaquetCe1QlmDangersElectriciteTest::verifierLesDeclarations, "DEL-CE1-1",
                "est un geste");
        estRefusee("sonde 04", PaquetCe1QlmDangersElectriciteTest::verifierLesDeclarations, "DEL-CE1-1",
                "ne déclare pas exactement une fois");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasUneFormeAdmise() {
        for (String sonde : List.of("sonde 06", "sonde 07", "sonde 08", "sonde 11", "sonde 14")) {
            estRefusee(sonde, PaquetCe1QlmDangersElectriciteTest::verifierLesGestes, "DEL-CE1-1",
                    "n'est pas une forme admise de la règle 2");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFormeHorsDeSaSituationOuSansMainsMouillees() {
        estRefusee("sonde 10", PaquetCe1QlmDangersElectriciteTest::verifierLesGestes, "est hors de la situation déclarée");
        estRefusee("sonde 12", PaquetCe1QlmDangersElectriciteTest::verifierLesGestes, "est hors de la situation déclarée");
        estRefusee("sonde 13", PaquetCe1QlmDangersElectriciteTest::verifierLesGestes, "est hors de la situation déclarée");
        estRefusee("sonde 09", PaquetCe1QlmDangersElectriciteTest::verifierLesGestes,
                "est sans mains mouillées dans l'énoncé");
        estRefusee("sonde 77", PaquetCe1QlmDangersElectriciteTest::verifierLesGestes,
                "nomme une lampe que l'énoncé ne nomme pas");
    }

    @Test
    void leJeuFautifCommiteLaisseChaqueFormeAdmisePasserAvecSaCasseSaPonctuationEtSonApostrophe() {
        for (String sonde : List.of("sonde 15", "sonde 16", "sonde 17", "sonde 18", "sonde 19")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierLesGestes,
                    PaquetCe1QlmDangersElectriciteTest::verifierLesDeclarations);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDeuxFormesDansUnMemeItemEtLaIsoleDesAutresGardes() {
        estRefusee("sonde 20", PaquetCe1QlmDangersElectriciteTest::verifierUnSeulGeste, "TRV-CE1-2",
                "un seul geste par item");
        estRefusee("sonde 21", PaquetCe1QlmDangersElectriciteTest::verifierUnSeulGeste, "TRV-CE1-2",
                "un seul geste par item");
        passe("sonde 20", PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire,
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur,
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucunDistracteurNeNieUneForme,
                PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseEnNePasSeuleEnNeEtLaisseLesAutresFormesPasser() {
        estRefusee("sonde 22", PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire, "TRV-CE1-2",
                "la seule proposition en ne pas, ne rien ou ne jamais");
        passe("sonde 23", PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire);
        passe("sonde 24", PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire,
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur,
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucunDistracteurNeNieUneForme,
                PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLeMotPileDansChaqueChampEtLaisseEmpilerPasser() {
        sontRefusees(List.of("sonde 25", "sonde 26", "sonde 27", "sonde 28", "sonde 29"),
                PaquetCe1QlmDangersElectriciteTest::verifierQueLeMotPileNEstJamaisEcrit, "porte le mot pile");
        passe("sonde 30", PaquetCe1QlmDangersElectriciteTest::verifierQueLeMotPileNEstJamaisEcrit);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneElectrocutionUneBrulureUneMortOuUnPersonnageBlesse() {
        sontRefusees(List.of("sonde 31", "sonde 32", "sonde 33", "sonde 34", "sonde 35", "sonde 43"),
                PaquetCe1QlmDangersElectriciteTest::verifierQueRienNeDecritUnAccident,
                "décrit un accident ou une blessure");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurSeBlesserEtSeFaireMalConjuguesPourUnePersonneDeLaScene() {
        sontRefusees(List.of("sonde 36", "sonde 37", "sonde 38", "sonde 39", "sonde 40", "sonde 41", "sonde 42"),
                PaquetCe1QlmDangersElectriciteTest::verifierQueRienNeDecritUnAccident,
                "décrit un accident ou une blessure");
    }

    @Test
    void leJeuFautifCommiteLaisseLePresentGeneriqueAvecOnOnNePersonneNeEtLInfinitifPasser() {
        for (String sonde : List.of("sonde 44", "sonde 45", "sonde 46", "sonde 47", "sonde 48")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQueRienNeDecritUnAccident);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTaChambreChezToiTaMaisonEtLaBaignoire() {
        sontRefusees(List.of("sonde 49", "sonde 50", "sonde 51", "sonde 52"),
                PaquetCe1QlmDangersElectriciteTest::verifierQueLaSituationNEstPasCelleDeLEnfant,
                "TRV-CE1-6");
        passe("sonde 53", PaquetCe1QlmDangersElectriciteTest::verifierQueLaSituationNEstPasCelleDeLEnfant);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneAlerteAUnAdulteEnBonneReponseOuEnDistracteur() {
        for (String sonde : List.of("sonde 14", "sonde 54", "sonde 55", "sonde 56")) {
            estRefusee(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition,
                    "NRC-CE1-1", "est une alerte à un adulte");
        }
        for (String sonde : List.of("sonde 54", "sonde 55", "sonde 56")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierLesGestes);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurToutAdulteSansNeEnTeteEnBonneReponseOuEnDistracteur() {
        for (String sonde : List.of("sonde 89", "sonde 90", "sonde 91", "sonde 92", "sonde 93")) {
            estRefusee(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition,
                    "NRC-CE1-1", "est une alerte à un adulte");
        }
        estRefusee("sonde 91", PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur,
                "est une abstention");
    }

    @Test
    void leJeuFautifCommiteLaisseLesRefusDeSignalerQuiNommentUnAdultePasserEnCommencantParNe() {
        passe("sonde 57", PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition,
                PaquetCe1QlmDangersElectriciteTest::verifierLesGestes,
                PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire);
        passe("sonde 58", PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition,
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucunDistracteurNeNieUneForme);
        passe("sonde 94", PaquetCe1QlmDangersElectriciteTest::verifierQueAucuneAlerteNEstUneProposition);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeGesteQuiPorteDeuxSituations() {
        for (String sonde : List.of("sonde 59", "sonde 12", "sonde 13")) {
            estRefusee(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQuUnEnonceNePorteQuUneSituation,
                    "DEL-CE1-1", "plusieurs situations");
        }
    }

    @Test
    void leJeuFautifCommiteLaisseUneSeuleSituationPasserMemeSiElleSeDitDeDeuxManieres() {
        for (String sonde : List.of("sonde 19", "sonde 60", "sonde 15", "sonde 18", "sonde 61")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQuUnEnonceNePorteQuUneSituation);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurChacuneDesCinqAbstentionsEnDistracteur() {
        sontRefusees(List.of("sonde 62", "sonde 63", "sonde 64", "sonde 65", "sonde 66", "sonde 67", "sonde 71"),
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur,
                "est une abstention");
        passe("sonde 62", PaquetCe1QlmDangersElectriciteTest::verifierUnSeulGeste,
                PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire);
        passe("sonde 63", PaquetCe1QlmDangersElectriciteTest::verifierUnSeulGeste,
                PaquetCe1QlmDangersElectriciteTest::verifierQuAucunDistracteurNeNieUneForme);
    }

    @Test
    void leJeuFautifCommiteLaisseLesRefusDeSignalerLAbstentionHorsDuDebutEtAttendrirPasser() {
        for (String sonde : List.of("sonde 68", "sonde 69", "sonde 70")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur,
                    PaquetCe1QlmDangersElectriciteTest::verifierUnSeulGeste,
                    PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurQuiNieUneFormeDeLaRegle2DansTousLesItems() {
        for (String sonde : List.of("sonde 72", "sonde 73", "sonde 74")) {
            estRefusee(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQuAucunDistracteurNeNieUneForme,
                    "TRV-CE1-2", "nie une forme de la règle 2");
        }
        for (String sonde : List.of("sonde 72", "sonde 73")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierLaGrammaire,
                    PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur);
        }
    }

    @Test
    void leJeuFautifCommiteLaisseNePasEnParlerEtNePasLaisserLeFilPasser() {
        for (String sonde : List.of("sonde 75", "sonde 76")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQuAucunDistracteurNeNieUneForme,
                    PaquetCe1QlmDangersElectriciteTest::verifierQuAucuneAbstentionNEstUnDistracteur);
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiNommeLeGesteOuLEtatDangereux() {
        for (String sonde : List.of("sonde 78", "sonde 79", "sonde 80", "sonde 81", "sonde 82", "sonde 83")) {
            estRefusee(sonde, PaquetCe1QlmDangersElectriciteTest::verifierLesIndices, "TRV-CE1-5", "nomme");
        }
    }

    @Test
    void leJeuFautifCommiteLaisseUnIndiceSansLeGesteNiLEtatDangereuxPasser() {
        passe("sonde 84", PaquetCe1QlmDangersElectriciteTest::verifierLesIndices);
        passe("sonde 85", PaquetCe1QlmDangersElectriciteTest::verifierLesIndices);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotCommunAuxDeuxDistracteursDUnItemDanger() {
        estRefusee("sonde 86",
                PaquetCe1QlmDangersElectriciteTest::verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot,
                "TRV-CE1-2", "ont en commun [loin]");
        estRefusee("sonde 87",
                PaquetCe1QlmDangersElectriciteTest::verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot,
                "TRV-CE1-2", "ont en commun [debranche]");
        estRefusee("sonde 88",
                PaquetCe1QlmDangersElectriciteTest::verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot,
                "TRV-CE1-2", "ont en commun [un]");
    }

    @Test
    void leJeuFautifCommiteLaisseUnMotCommunAuxDeuxDistracteursPasserEnTacheGeste() {
        for (String sonde : List.of("sonde 54", "sonde 55")) {
            passe(sonde, PaquetCe1QlmDangersElectriciteTest::verifierQueLesDeuxDistracteursDeDangerNePartagentPasDeMot);
        }
    }

    // ----- le paquet reel, prive de ses items : les gardes de paquet mordent sur le materiau

    private static List<Item> sans(Predicate<Item> retires) {
        List<Item> restants = paquet().items().stream().filter(retires.negate()).toList();
        assertThat(restants).as("la sonde retire au moins un item du paquet").hasSizeLessThan(paquet().items().size());
        return restants;
    }

    private static boolean estUnGesteDe(Item item, String geste) {
        Forme forme = estUnItem(item, GESTE) ? FORMES.get(egalite(item.bonneReponse())) : null;
        return forme != null && forme.geste().equals(geste);
    }

    @Test
    void unPaquetQuiNePorteQueDeuxItemsDeLaTacheDangerEstRefuse() {
        Paquet paquet = paquet();
        List<Item> danger = paquet.items().stream().filter(item -> estUnItem(item, DANGER)).toList();
        List<Item> avecDeuxDangers = new ArrayList<>(paquet.items().stream()
                .filter(item -> estUnItem(item, GESTE)).toList());
        avecDeuxDangers.addAll(danger.subList(0, 2));

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxDangers, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("la moins servie");
    }

    @Test
    void unPaquetQuiNeServQuUneTacheEstRefuse() {
        Paquet paquet = paquet();
        List<Item> gestesSeuls = paquet.items().stream().filter(item -> estUnItem(item, GESTE)).toList();

        assertThat(gestesSeuls).hasSizeLessThan(paquet.items().size());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(gestesSeuls, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("ne sert pas deux tâches distinctes");
    }

    @Test
    void unCommentaireQuiEcritUnMauvaisCompteDeTachesEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replace("(4 items)", "(3 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("comptes");
    }

    @Test
    void unPaquetPriveDUneSituationEnDangerOuEnGesteEstRefuse() {
        for (String situation : SITUATIONS) {
            for (String tache : List.of(DANGER, GESTE)) {
                List<Item> restants = sans(item -> estUnItem(item, tache)
                        && declaration(item, SITUATION_DECLAREE).equals(situation));

                assertThatThrownBy(() -> verifierLesTroisSituationsEnDangerEtEnGeste(restants))
                        .as(situation + " en " + tache)
                        .isInstanceOf(AssertionError.class)
                        .hasMessageContaining("FER-1")
                        .hasMessageContaining("la situation " + situation + " n'est celle d'aucun item " + tache);
            }
        }
    }

    @Test
    void unPaquetPriveDeSesItemsGesteDEauSansMainsMouilleesEstRefuse() {
        List<Item> restants = sans(item -> estUnItem(item, GESTE) && declaration(item, SITUATION_DECLAREE).equals(EAU)
                && !MOUILLEES.matcher(plat(item.enonce())).find());

        assertThatThrownBy(() -> verifierQuUnItemDEauNeParlePasDesMains(restants))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DEL-CE1-1")
                .hasMessageContaining("se passe de mains mouillées");
        verifierLesTroisSituationsEnDangerEtEnGeste(restants);
    }

    @Test
    void unPaquetPriveDesItemsDUnDesTroisGestesEstRefuse() {
        for (String geste : GESTES) {
            List<Item> restants = sans(item -> estUnGesteDe(item, geste));

            assertThatThrownBy(() -> verifierQueChaqueGesteEstLaBonneReponseDUnItemGeste(restants))
                    .as(geste)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("6.4")
                    .hasMessageContaining("aucun item de B n'a pour bonne réponse le geste " + geste)
                    .hasMessageContaining("B au moins 4 items");
        }
    }

    @Test
    void unPaquetPriveDeSesItemsApprocherOuMainsNeLeveQueLaGardeDesTroisGestes() {
        for (String geste : List.of(APPROCHER, MAINS)) {
            List<Item> restants = sans(item -> estUnGesteDe(item, geste));

            verifierLesTroisSituationsEnDangerEtEnGeste(restants);
            verifierQuUnItemDEauNeParlePasDesMains(restants);
            verifierLesGestes(restants);
        }
    }
}
