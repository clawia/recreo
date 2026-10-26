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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

/**
 * S57-BR-QLM-CE1-01 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-qlm-etapes-de-developpement}, ligne {@code QLM-CE1-01} - ordonner les etapes du
 * developpement d'un animal ou d'une plante familiers. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section 3.1 :
 * {@code DVP-CE1-1}, {@code DVP-CE1-2} ; section 2 : {@code FER-1}, {@code FER-3}).
 * Les conditions transverses ({@code TRV-CE1-1}, {@code -2}, {@code -5}, {@code LON-CE1-1}, {@code -2},
 * {@code -5}, {@code QMT-CE1-1}, {@code NRC-CE1-1} sur le texte) sont gardees une fois pour les neuf lots par
 * {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit {@code DVP-CE1-1}.</b> La liste fermee des quatre especes et de leurs etapes,
 * dans l'ordre, est ecrite ici ; chaque enonce a l'une des deux formes ({@code voisine} : l'etape juste
 * avant ou juste apres une etape nommee ; {@code ordre} : la suite de trois etapes dans le bon ordre),
 * nomme une espece de la liste et declare, dans le commentaire qui le precede, l'espece et la tache. Le
 * test recalcule la bonne reponse sur la liste : une etape voisine, ou trois etapes en ordre croissant.
 * Aucun item ne demande ce qui vient apres la derniere etape ni avant la premiere, et aucun enonce, indice
 * ou proposition ne porte <i>premier</i> ou <i>dernier</i>.
 *
 * <p><b>Comment ce test lit {@code DVP-CE1-2}.</b> Chaque distracteur est classe par le test : une etape de
 * la meme espece (famille a), une etape d'une autre espece de la liste (famille b), ou les memes trois
 * etapes dans un autre ordre (famille c). Le classement doit etre celui que l'item declare. <b>Une etape de
 * la meme espece voisine de la bonne reponse est refusee</b> : c'est la forme choisie pour <i>jamais deux
 * etapes defendables</i> (jeune grenouille et grenouille apres le tetard) : forme confirmee,
 * {@code avis-theo-palier-5-premier-jet-ce1-qlm-01.md}, V1.
 *
 * <p><b>Ce que ce test ne garde pas</b> : que chaque ordre soit celui de la source de nadia-prof
 * ({@code QMT-CE1-2}), que le sujet d'un item ne reprenne pas celui d'un item de CP ({@code NRC-CE1-1},
 * au-dela de l'egalite des enonces et des propositions, gardee par le test transverse), qu'un indice soit
 * une methode, que {@code CYC-1} tienne. Cela reste a la lecture et au jeu de
 * nadia-prof ({@code QC9}). {@code BPL-CE1-2} est garde sur la phrase qui dit qu'une graine a besoin de
 * lumiere, pas sur ses paraphrases.
 */
class PaquetCe1QlmEtapesDeDeveloppementTest {

    private static final String PAQUET = "ce1-qlm-etapes-de-developpement";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmEtapesDeDeveloppementTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    /** DVP-CE1-1 : les especes et leurs etapes, dans l'ordre, ecrites par nadia-prof (3.1). */
    private static final Map<String, List<String>> ESPECES = new LinkedHashMap<>();

    static {
        ESPECES.put("grenouille", List.of("œuf", "têtard", "jeune grenouille", "grenouille"));
        ESPECES.put("papillon", List.of("œuf", "chenille", "chrysalide", "papillon"));
        ESPECES.put("poule", List.of("œuf", "poussin", "poule"));
        ESPECES.put("plante", List.of("graine", "jeune pousse", "plante", "fleur", "fruit"));
    }

    /** La facon dont l'enonce nomme chaque espece. */
    private static final Map<String, String> ESPECE_NOMMEE = Map.of(
            "de la grenouille", "grenouille", "du papillon", "papillon",
            "de la poule", "poule", "d'une plante", "plante");

    private static final String ESPECES_ECRITES = "(" + String.join("|", ESPECE_NOMMEE.keySet()) + ")";

    private static final String TACHE_VOISINE = "voisine";

    private static final String TACHE_ORDRE = "ordre";

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final Pattern FORME_VOISINE = Pattern.compile(
            "^Dans la vie " + ESPECES_ECRITES + ", quelle étape vient juste (avant|après) (.+) \\?$", FLAGS);

    private static final Pattern FORME_ORDRE = Pattern.compile(
            "^Dans la vie " + ESPECES_ECRITES + ", quelle suite est dans le bon ordre \\?$", FLAGS);

    private static final Pattern ARTICLE = Pattern.compile("^(l'|le |la )", FLAGS);

    private static final Pattern ESPECE_DECLAREE = Pattern.compile("^DVP-CE1-1 espece : (\\p{L}+)$");

    private static final Pattern TACHE_DECLAREE = Pattern.compile("^DVP-CE1-1 tache : (voisine|ordre)$");

    private static final Pattern FAMILLES_DECLAREES = Pattern.compile("^TRV-CE1-2 : ([a-z]), ([a-z])$");

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(voisine|ordre) : .*\\((\\d+) items?");

    private static final Pattern PREMIERE_OU_DERNIERE = Pattern.compile(
            "(?<!\\p{L})(premier|première|premiers|premières|dernier|dernière|derniers|dernières)(?!\\p{L})",
            FLAGS);

    /** BPL-CE1-2 : la graine et la germination, la lumiere, dans une meme phrase. */
    private static final Pattern GRAINE = Pattern.compile("(?<!\\p{L})(graines?|germ\\p{L}*)", FLAGS);

    private static final Pattern LUMIERE = Pattern.compile("(?<!\\p{L})(lumière|soleil)(?!\\p{L})", FLAGS);

    /** FER-1 : les six etapes de la parenthese du libelle, chacune en bonne reponse d'un item. */
    private static final List<String> ETAPES_DE_LA_PARENTHESE =
            List.of("œuf", "têtard", "grenouille", "graine", "jeune pousse", "plante");

    /** FER-3 : la tache la moins servie porte au moins ce nombre d'items. */
    private static final int ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE = 3;

    /** Ce que l'enonce dit d'un item : son espece, sa tache, et pour une tache voisine le sens et l'etape. */
    private record Enonce(String espece, String tache, String sens, String etapeNommee) {
    }

    private static Paquet paquet() {
        return PaquetLu.lirePaquet(FICHIER_CONTENU);
    }

    private static Paquet fautif(String nom) {
        return PaquetLu.lirePaquet(CONTRE_EXEMPLE.resolve(nom));
    }

    /** Les items du jeu fautif qui portent ce marqueur en commentaire, pour mordre un verificateur a la fois. */
    private static List<Item> sonde(Paquet fautif, String marqueur) {
        return fautif.items().stream().filter(item -> item.commentaires().contains(marqueur)).toList();
    }

    private static String etape(String proposition) {
        return ARTICLE.matcher(proposition.strip()).replaceFirst("").strip();
    }

    private static int rangDans(List<String> etapes, String etape) {
        for (int rang = 0; rang < etapes.size(); rang++) {
            if (etapes.get(rang).equalsIgnoreCase(etape)) {
                return rang;
            }
        }
        return -1;
    }

    private static boolean estUneEtape(String etape) {
        return ESPECES.values().stream().anyMatch(etapes -> rangDans(etapes, etape) >= 0);
    }

    private static String declaration(Item item, Pattern declaration) {
        List<Matcher> trouvees = item.commentaires().stream().map(declaration::matcher)
                .filter(Matcher::matches).toList();
        assertThat(trouvees)
                .as("DVP-CE1-1 : '%s' ne déclare pas exactement une fois %s", item.enonce(), declaration.pattern())
                .hasSize(1);
        return trouvees.get(0).group(1);
    }

    private static Enonce analyser(Item item) {
        Matcher voisine = FORME_VOISINE.matcher(item.enonce());
        if (voisine.matches()) {
            return new Enonce(ESPECE_NOMMEE.get(voisine.group(1).toLowerCase(Locale.FRENCH)), TACHE_VOISINE,
                    voisine.group(2).toLowerCase(Locale.FRENCH), etape(voisine.group(3)));
        }
        Matcher ordre = FORME_ORDRE.matcher(item.enonce());
        if (ordre.matches()) {
            return new Enonce(ESPECE_NOMMEE.get(ordre.group(1).toLowerCase(Locale.FRENCH)), TACHE_ORDRE, "", "");
        }
        return fail("DVP-CE1-1 : l'énoncé « %s » ne nomme aucune espèce de la liste fermée (%s) sous l'une des "
                + "deux formes de la ligne", item.enonce(), ESPECES.keySet());
    }

    private static List<String> suite(String proposition) {
        return Arrays.stream(proposition.split(",")).map(PaquetCe1QlmEtapesDeDeveloppementTest::etape).toList();
    }

    // ----- DVP-CE1-1

    /** DVP-CE1-1 : l'espece et l'etape sont de la liste fermee, declarees et dites, jamais un cycle. */
    static void verifierLaListeFermee(List<Item> items) {
        for (Item item : items) {
            Enonce enonce = analyser(item);
            assertThat(declaration(item, ESPECE_DECLAREE))
                    .as("DVP-CE1-1 : '%s' déclare une espèce qui n'est pas celle de son énoncé", item.enonce())
                    .isEqualTo(enonce.espece());
            assertThat(declaration(item, TACHE_DECLAREE))
                    .as("DVP-CE1-1 : '%s' déclare une tâche qui n'est pas celle de son énoncé", item.enonce())
                    .isEqualTo(enonce.tache());
            if (enonce.tache().equals(TACHE_VOISINE)) {
                List<String> etapes = ESPECES.get(enonce.espece());
                int rang = rangDans(etapes, enonce.etapeNommee());
                assertThat(rang)
                        .as("DVP-CE1-1 : l'étape « %s » de '%s' n'est pas dans la liste fermée de la %s %s",
                                enonce.etapeNommee(), item.enonce(), enonce.espece(), etapes)
                        .isGreaterThanOrEqualTo(0);
                if (enonce.sens().equals("après")) {
                    assertThat(rang)
                            .as("DVP-CE1-1 : '%s' demande ce qui vient après la dernière étape, un cycle",
                                    item.enonce())
                            .isLessThan(etapes.size() - 1);
                } else {
                    assertThat(rang)
                            .as("DVP-CE1-1 : '%s' demande ce qui vient avant la première étape, un cycle",
                                    item.enonce())
                            .isGreaterThan(0);
                }
            }
        }
    }

    /** DVP-CE1-1 : jamais la premiere ni la derniere, dans aucun champ lu. */
    static void verifierQueNiLaPremiereNiLaDerniereNEstDemandee(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                assertThat(PREMIERE_OU_DERNIERE.matcher(champ).find())
                        .as("DVP-CE1-1 : « %s » (champ de '%s') parle de la première ou de la dernière étape, "
                                + "un développement est un cycle", champ, item.enonce())
                        .isFalse();
            }
        }
    }

    // ----- DVP-CE1-2 et TRV-CE1-2

    /**
     * DVP-CE1-1 et DVP-CE1-2 : la bonne reponse est l'etape voisine ou la suite croissante, chaque
     * distracteur est classe dans la famille que l'item declare, et aucun n'est defendable.
     */
    static void verifierLesReponsesEtLesDistracteurs(List<Item> items) {
        for (Item item : items) {
            Enonce enonce = analyser(item);
            List<String> etapes = ESPECES.get(enonce.espece());
            List<String> declarees = declarationDesFamilles(item);
            List<String> calculees = new ArrayList<>();
            if (enonce.tache().equals(TACHE_VOISINE)) {
                int rang = rangDans(etapes, enonce.etapeNommee());
                int attendu = enonce.sens().equals("après") ? rang + 1 : rang - 1;
                assertThat(etape(item.bonneReponse()))
                        .as("DVP-CE1-1 : la bonne réponse « %s » de '%s' n'est pas l'étape qui vient juste %s %s "
                                + "dans la liste fermée de la %s", item.bonneReponse(), item.enonce(),
                                enonce.sens(), enonce.etapeNommee(), enonce.espece())
                        .isEqualToIgnoringCase(etapes.get(attendu));
                for (String distracteur : item.distracteurs()) {
                    calculees.add(familleDuDistracteur(item, enonce, etapes, attendu, distracteur));
                }
            } else {
                List<String> bonne = suite(item.bonneReponse());
                assertThat(bonne)
                        .as("DVP-CE1-1 : la bonne réponse « %s » de '%s' n'est pas trois étapes de la %s",
                                item.bonneReponse(), item.enonce(), enonce.espece())
                        .hasSize(3).allMatch(e -> rangDans(etapes, e) >= 0);
                assertThat(rangsDe(etapes, bonne))
                        .as("DVP-CE1-1 : la bonne réponse « %s » de '%s' n'est pas dans le bon ordre",
                                item.bonneReponse(), item.enonce())
                        .isSorted();
                for (String distracteur : item.distracteurs()) {
                    List<String> autre = suite(distracteur);
                    assertThat(autre)
                            .as("DVP-CE1-2 : « %s » (distracteur de '%s') n'est pas les trois mêmes étapes dans un "
                                    + "autre ordre", distracteur, item.enonce())
                            .containsExactlyInAnyOrderElementsOf(bonne)
                            .isNotEqualTo(bonne);
                    assertThat(rotationsDe(bonne))
                            .as("DVP-CE1-2 : « %s » (distracteur de '%s') est la bonne suite qu'on fait tourner dans "
                                    + "le cycle : deux suites défendables", distracteur, item.enonce())
                            .doesNotContain(autre);
                    calculees.add("c");
                }
            }
            assertThat(declarees)
                    .as("TRV-CE1-2 : '%s' déclare les familles %s, ses distracteurs sont de familles %s",
                            item.enonce(), declarees, calculees)
                    .isEqualTo(calculees);
        }
    }

    private static List<String> declarationDesFamilles(Item item) {
        List<Matcher> trouvees = item.commentaires().stream().map(FAMILLES_DECLAREES::matcher)
                .filter(Matcher::matches).toList();
        assertThat(trouvees).as("TRV-CE1-2 : '%s' ne déclare pas exactement une paire de familles",
                item.enonce()).hasSize(1);
        return List.of(trouvees.get(0).group(1), trouvees.get(0).group(2));
    }

    private static List<List<String>> rotationsDe(List<String> bonne) {
        List<List<String>> rotations = new ArrayList<>();
        for (int pas = 1; pas < bonne.size(); pas++) {
            List<String> tournee = new ArrayList<>(bonne);
            Collections.rotate(tournee, pas);
            rotations.add(tournee);
        }
        return rotations;
    }

    private static List<Integer> rangsDe(List<String> etapes, List<String> choisies) {
        return choisies.stream().map(choisie -> rangDans(etapes, choisie)).toList();
    }

    private static String familleDuDistracteur(Item item, Enonce enonce, List<String> etapes, int attendu,
                                               String distracteur) {
        String etape = etape(distracteur);
        int rang = rangDans(etapes, etape);
        if (rang >= 0) {
            assertThat(etape)
                    .as("DVP-CE1-2 : « %s » (distracteur de '%s') est l'étape que l'énoncé nomme", distracteur,
                            item.enonce())
                    .isNotEqualToIgnoringCase(enonce.etapeNommee());
            assertThat(Math.abs(rang - attendu))
                    .as("DVP-CE1-2 : « %s » (distracteur de '%s') est voisine de la bonne réponse « %s » : deux "
                            + "étapes défendables", distracteur, item.enonce(), item.bonneReponse())
                    .isGreaterThanOrEqualTo(2);
            return "a";
        }
        assertThat(estUneEtape(etape))
                .as("DVP-CE1-1 : « %s » (distracteur de '%s') est hors de la liste fermée des étapes",
                        distracteur, item.enonce())
                .isTrue();
        return "b";
    }

    // ----- FER-1, FER-3

    /** FER-1 : chacune des six etapes de la parenthese est la bonne reponse d'au moins un item. */
    static void verifierLesSixEtapesDeLaParentheseEnBonneReponse(List<Item> items) {
        Set<String> enBonneReponse = new HashSet<>();
        for (Item item : items) {
            if (analyser(item).tache().equals(TACHE_VOISINE)) {
                enBonneReponse.add(etape(item.bonneReponse()).toLowerCase(Locale.FRENCH));
            }
        }
        for (String etape : ETAPES_DE_LA_PARENTHESE) {
            assertThat(enBonneReponse)
                    .as("FER-1 : l'étape « %s » n'est la bonne réponse d'aucun item (%s)", etape, enBonneReponse)
                    .contains(etape);
        }
    }

    /** FER-3 : deux taches, la moins servie au moins trois items, et le commentaire dit les comptes. */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(analyser(item).tache(), 1, Integer::sum));
        assertThat(comptes.keySet()).as("FER-3 : le paquet ne sert pas deux tâches distinctes (%s)", comptes)
                .containsExactlyInAnyOrder(TACHE_VOISINE, TACHE_ORDRE);
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

    // ----- BPL-CE1-2

    /** BPL-CE1-2 : aucune phrase ne dit qu'une graine a besoin de lumiere, ni une question dont la reponse le dit. */
    static void verifierQueLaGraineNAPasBesoinDeLumiere(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                for (String phrase : champ.split("(?<=[.?!])[\\s\\u00A0]+")) {
                    assertThat(GRAINE.matcher(phrase).find() && LUMIERE.matcher(phrase).find())
                            .as("BPL-CE1-2 : « %s » (champ de '%s') lie la graine à la lumière", phrase,
                                    item.enonce())
                            .isFalse();
                }
            }
            assertThat(GRAINE.matcher(item.enonce() + " " + item.indice()).find()
                    && LUMIERE.matcher(item.bonneReponse()).find())
                    .as("BPL-CE1-2 : la bonne réponse « %s » de '%s' donne la lumière à une graine",
                            item.bonneReponse(), item.enonce())
                    .isFalse();
        }
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe101EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-01"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-01");
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
    void dvp1ChaqueItemNommeUneEspeceDeLaListeFermeeEtUneEtapeQuiNEstPasUnCycle() {
        verifierLaListeFermee(paquet().items());
    }

    @Test
    void dvp1AucunChampNeParleDeLaPremiereNiDeLaDerniereEtape() {
        verifierQueNiLaPremiereNiLaDerniereNEstDemandee(paquet().items());
    }

    @Test
    void dvp1EtDvp2LaBonneReponseEstCalculeeSurLaListeEtChaqueDistracteurEstDansSaFamille() {
        Paquet paquet = paquet();
        verifierLesReponsesEtLesDistracteurs(paquet.items());
    }

    @Test
    void fer1LesSixEtapesDeLaParentheseSontChacuneEnBonneReponse() {
        verifierLesSixEtapesDeLaParentheseEnBonneReponse(paquet().items());
    }

    @Test
    void fer3DeuxTachesLaMoinsServiePorteTroisItemsEtLeCommentaireDitLesComptes() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    @Test
    void bpl2AucuneGraineNAPasBesoinDeLumiere() {
        verifierQueLaGraineNAPasBesoinDeLumiere(paquet().items());
    }

    @Test
    void typ1LEspaceAvantLePointDInterrogationEstInsecable() {
        for (Item item : paquet().items()) {
            for (String champ : item.champsLus()) {
                assertThat(champ).as("TYP-1 : « %s » porte une espace ordinaire avant ?, :, ; ou !", champ)
                        .doesNotContainPattern("[^\\u00A0] [?:;!]");
            }
        }
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurUneQuestionDeLaPremiereEtape() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThat(sonde(fautif, "sonde 01")).hasSize(1);
        assertThatThrownBy(() -> verifierQueNiLaPremiereNiLaDerniereNEstDemandee(sonde(fautif, "sonde 01")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DVP-CE1-1")
                .hasMessageContaining("première");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEspeceHorsDeLaListeFermee() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThat(sonde(fautif, "sonde 02")).hasSize(1);
        assertThatThrownBy(() -> verifierLaListeFermee(sonde(fautif, "sonde 02")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DVP-CE1-1")
                .hasMessageContaining("aucune espèce de la liste fermée");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEtapeQuiEstUnCycle() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThatThrownBy(() -> verifierLaListeFermee(sonde(fautif, "sonde 04")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("après la dernière étape");
        assertThatThrownBy(() -> verifierLaListeFermee(sonde(fautif, "sonde 05")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("avant la première étape");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDeuxEtapesDefendablesApresLeTetard() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThat(sonde(fautif, "sonde 03")).hasSize(1);
        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 03")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DVP-CE1-2")
                .hasMessageContaining("deux étapes défendables");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasLEtapeVoisine() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 06")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas l'étape qui vient juste après");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurHorsDeLaListeFermee() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 07")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("hors de la liste fermée");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleDeclareeQuiNEstPasCelleDuDistracteur() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 08")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("déclare les familles");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneSuiteQuiNEstPasDansLeBonOrdre() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 09")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas dans le bon ordre");
        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 10")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("autre ordre");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLaBonneSuiteQuOnFaitTournerDansLeCycle() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        for (String sonde : List.of("sonde 15", "sonde 16", "sonde 18")) {
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, sonde)))
                    .as(sonde)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("DVP-CE1-2")
                    .hasMessageContaining("fait tourner")
                    .hasMessageNotContaining("deux étapes défendables");
        }
    }

    @Test
    void leJeuFautifCommiteLaisseLesTroisOrdresQuiNeSontPasUneRotationPasser() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThat(sonde(fautif, "sonde 17")).hasSize(1);
        verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 17"));
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneGraineQuiABesoinDeLumiere() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 11")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2");
        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 12")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2");
        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 13")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("donne la lumière");
    }

    @Test
    void leJeuFautifCommiteLaisseUneQuestionJusteSurLaGraineEtLaPlantePasser() {
        Paquet fautif = fautif("etapes-fautives.yaml");

        assertThat(sonde(fautif, "sonde 14")).hasSize(1);
        verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 14"));
    }

    @Test
    void unPaquetPrivePourUneEtapeDeLaParentheseEstRefuse() {
        List<Item> sansLaGraine = paquet().items().stream()
                .filter(item -> !etape(item.bonneReponse()).equals("graine")).toList();

        assertThat(sansLaGraine).hasSizeLessThan(paquet().items().size());
        assertThatThrownBy(() -> verifierLesSixEtapesDeLaParentheseEnBonneReponse(sansLaGraine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-1")
                .hasMessageContaining("graine");
    }

    @Test
    void unPaquetQuiNePorteQueDeuxItemsDeLaTacheOrdreEstRefuse() {
        Paquet paquet = paquet();
        List<Item> ordres = paquet.items().stream()
                .filter(item -> analyser(item).tache().equals(TACHE_ORDRE)).toList();
        List<Item> avecDeuxOrdres = new ArrayList<>(paquet.items().stream()
                .filter(item -> analyser(item).tache().equals(TACHE_VOISINE)).toList());
        avecDeuxOrdres.addAll(ordres.subList(0, 2));

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxOrdres, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("la moins servie");
    }

    @Test
    void unCommentaireQuiEcritUnMauvaisCompteDeTachesEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replace("(11 items)", "(10 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("comptes");
    }
}
