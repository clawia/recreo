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
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-QLM-CE1-03 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-qlm-besoins-de-la-plante}, ligne {@code QLM-CE1-03} - identifier ce dont une plante
 * a besoin pour vivre et grandir (eau, lumiere, chaleur). Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section 3.3 :
 * {@code BPL-CE1-1}, {@code BPL-CE1-2} ; section 2 : {@code FER-1}, {@code FER-3}).
 * Les conditions transverses ({@code TRV-CE1-1}, {@code -2}, {@code -5}, {@code LON-CE1-1}, {@code -2},
 * {@code -5}, {@code QMT-CE1-1}, {@code NRC-CE1-1} sur le texte) sont gardees une fois pour les neuf lots par
 * {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit {@code BPL-CE1-1}.</b> Deux taches, declarees dans le commentaire qui precede
 * chaque item (sujet, tache, et le besoin ou la privation). Tache {@code besoin} : la bonne reponse est
 * l'un des trois besoins que l'item declare, chaque distracteur est l'une des trois choses dont une plante
 * n'a pas besoin (famille a). Tache {@code privation} : la bonne reponse est la consequence que la table
 * {@link #CONSEQUENCES} ecrit pour le couple (sujet, besoin retire), chaque distracteur est une consequence
 * que la privation n'a jamais (famille b, table {@link #EFFETS_CONTRAIRES}). Les quatre mots jamais proposes
 * ({@code air}, {@code terre}, {@code engrais}, {@code sels mineraux}) sont refuses dans tout champ que
 * l'enfant lit : cette garde est plus stricte que la lettre de la condition, qui dit <i>proposes</i>, et
 * elle ne refuse rien d'ecrit par ce paquet.
 *
 * <p><b>Comment ce test lit {@code BPL-CE1-2}.</b> Un item qui nomme la graine ou la germination dans
 * l'enonce ou l'indice n'a jamais la lumiere pour bonne reponse ; aucune phrase d'un champ lu ne lie la
 * graine a la lumiere ; une graine n'est jamais privee de lumiere. La question <i>"De quoi la graine
 * a-t-elle besoin pour germer ?"</i> reste possible avec l'eau ou la chaleur : la garde porte sur la bonne
 * reponse, pas sur la question (nadia-prof, {@code arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md},
 * ligne {@code -03}, c3). Second point de la condition, <i>la lumiere porte sur la plante qui a deja des
 * feuilles</i>, lu ici comme : l'enonce de chaque item dont le besoin ou la privation est la lumiere nomme
 * les feuilles. C'est une lecture de la condition, a confirmer par nadia-prof.
 *
 * <p><b>Ce que ce test ne garde pas</b> : que chaque consequence de la table soit exacte
 * ({@code QMT-CE1-2}, {@code QC9}) ; que les trois choses dont une plante n'a pas besoin le soient aux yeux
 * de nadia-prof ; que le sujet d'un item ne reprenne pas celui d'un item de CP ({@code NRC-CE1-1}), que
 * {@code CYC-1} tienne, qu'un indice soit une methode. Cela reste a la lecture et au jeu de nadia-prof.
 * Les paraphrases de la graine et de la lumiere que le motif {@code graine ... lumiere} ne voit pas (une
 * phrase en deux phrases) restent a sa lecture.
 */
class PaquetCe1QlmBesoinsDeLaPlanteTest {

    private static final String PAQUET = "ce1-qlm-besoins-de-la-plante";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmBesoinsDeLaPlanteTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String BESOIN = "besoin";

    private static final String PRIVATION = "privation";

    /** BPL-CE1-1 : les trois besoins de la parenthese du libelle, ecrits sans accent. */
    private static final Set<String> TROIS_BESOINS = Set.of("eau", "lumiere", "chaleur");

    /** BPL-CE1-1 : ce dont une plante n'a pas besoin, les trois que nadia-prof nomme. */
    private static final Set<String> NON_BESOINS = Set.of("sucre", "lait", "bruit");

    /**
     * BPL-CE1-1, tache privation : la consequence d'un besoin retire, une par couple (sujet, besoin
     * retire), ecrite sans accent. Source du redacteur, a confronter a celle de nadia-prof (QMT-CE1-2).
     * Le couple (graine, lumiere) n'y est pas : BPL-CE1-2.
     */
    private static final Map<String, String> CONSEQUENCES = Map.of(
            "plante/eau", "elle se fane",
            "plante/lumiere", "elle devient pale et pousse mal",
            "plante/chaleur", "elle arrete de pousser",
            "graine/eau", "elle ne germe pas",
            "graine/chaleur", "elle reste une graine");

    /** BPL-CE1-1, tache privation : les consequences que la privation n'a jamais, ecrites sans accent. */
    private static final Set<String> EFFETS_CONTRAIRES = Set.of(
            "elle grandit plus vite", "elle devient plus verte", "elle devient plus solide",
            "elle pousse plus vite", "elle fait plus de fleurs", "elle devient une plante",
            "elle germe plus vite", "elle germe tout de suite",
            "elle reste bien droite", "elle continue de pousser");

    /** BPL-CE1-1 : les quatre mots jamais proposes, sur le texte sans accent. */
    private static final Pattern MOT_JAMAIS_PROPOSE = Pattern.compile(
            "(?<!\\p{L})(air|terres?|engrais|sels? mineraux?)(?!\\p{L})", FLAGS);

    private static final Pattern ARTICLE = Pattern.compile("^(de l'|de la |des |du |d'|de |l'|la |le |les )");

    private static final Pattern SUJET_DECLARE = Pattern.compile("^BPL-CE1-1 sujet : (plante|graine)$");

    private static final Pattern TACHE_DECLAREE = Pattern.compile("^BPL-CE1-1 tache : (besoin|privation)$");

    private static final Pattern BESOIN_DECLARE =
            Pattern.compile("^BPL-CE1-1 besoin : (eau|lumiere|chaleur)$");

    private static final Pattern PRIVE_DECLARE =
            Pattern.compile("^BPL-CE1-1 prive de : (eau|lumiere|chaleur)$");

    private static final Pattern FAMILLES_DECLAREES = Pattern.compile("^TRV-CE1-2 : ([a-z]), ([a-z])$");

    private static final Pattern TACHE_DE_L_ENTETE = Pattern.compile("^#\\s+(besoin|privation) : .*\\((\\d+) items?");

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final Pattern QUESTION_DE_PRIVATION = Pattern.compile("Que lui arrive-t-il\\u00A0\\?$", FLAGS);

    private static final Pattern SUJET_GRAINE = Pattern.compile("(?<!\\p{L})graines?(?!\\p{L})", FLAGS);

    /** BPL-CE1-2 : la graine et la germination, la lumiere, dans une meme phrase. */
    private static final Pattern GRAINE = Pattern.compile("(?<!\\p{L})(graines?|germ\\p{L}*)", FLAGS);

    private static final Pattern LUMIERE = Pattern.compile("(?<!\\p{L})(lumiere|soleil)(?!\\p{L})", FLAGS);

    private static final Pattern FEUILLES = Pattern.compile("(?<!\\p{L})feuilles?(?!\\p{L})", FLAGS);

    /** FER-3 : la tache la moins servie porte au moins ce nombre d'items. */
    private static final int ITEMS_DE_LA_TACHE_LA_MOINS_SERVIE = 3;

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

    /** Minuscule, sans accent, sans article : ce que l'item propose, comparable aux tables. */
    private static String nu(String proposition) {
        return ARTICLE.matcher(PaquetLu.sansAccent(proposition).strip()).replaceFirst("").strip();
    }

    private static String plat(String texte) {
        return PaquetLu.sansAccent(texte).strip();
    }

    private static List<String> trouvees(Item item, Pattern declaration) {
        return item.commentaires().stream().map(declaration::matcher).filter(Matcher::matches)
                .map(trouvee -> trouvee.group(1)).toList();
    }

    private static String declaration(Item item, Pattern declaration) {
        List<String> trouvees = trouvees(item, declaration);
        assertThat(trouvees)
                .as("BPL-CE1-1 : '%s' ne déclare pas exactement une fois %s", item.enonce(), declaration.pattern())
                .hasSize(1);
        return trouvees.get(0);
    }

    private static String declarationOuVide(Item item, Pattern declaration) {
        List<String> trouvees = trouvees(item, declaration);
        return trouvees.isEmpty() ? "" : trouvees.get(0);
    }

    private static List<String> declarationDesFamilles(Item item) {
        List<Matcher> trouvees = item.commentaires().stream().map(FAMILLES_DECLAREES::matcher)
                .filter(Matcher::matches).toList();
        assertThat(trouvees).as("TRV-CE1-2 : '%s' ne déclare pas exactement une paire de familles",
                item.enonce()).hasSize(1);
        return List.of(trouvees.get(0).group(1), trouvees.get(0).group(2));
    }

    // ----- les declarations

    /** BPL-CE1-1 : sujet, tache et besoin ou privation declares, et conformes a l'enonce. */
    static void verifierLesDeclarations(List<Item> items) {
        for (Item item : items) {
            String sujet = declaration(item, SUJET_DECLARE);
            assertThat(sujet)
                    .as("BPL-CE1-2 : '%s' déclare le sujet %s, son énoncé %s la graine", item.enonce(), sujet,
                            SUJET_GRAINE.matcher(item.enonce()).find() ? "nomme" : "ne nomme pas")
                    .isEqualTo(SUJET_GRAINE.matcher(item.enonce()).find() ? "graine" : "plante");
            String tache = declaration(item, TACHE_DECLAREE);
            assertThat(tache)
                    .as("BPL-CE1-1 : '%s' déclare une tâche qui n'est pas celle de son énoncé", item.enonce())
                    .isEqualTo(QUESTION_DE_PRIVATION.matcher(item.enonce()).find() ? PRIVATION : BESOIN);
            if (tache.equals(BESOIN)) {
                declaration(item, BESOIN_DECLARE);
                assertThat(trouvees(item, PRIVE_DECLARE))
                        .as("BPL-CE1-1 : '%s' est un item besoin et déclare un besoin retiré", item.enonce())
                        .isEmpty();
            } else {
                declaration(item, PRIVE_DECLARE);
                assertThat(trouvees(item, BESOIN_DECLARE))
                        .as("BPL-CE1-1 : '%s' est un item privation et déclare un besoin", item.enonce())
                        .isEmpty();
            }
        }
    }

    // ----- BPL-CE1-1 et TRV-CE1-2

    /**
     * BPL-CE1-1 : la bonne reponse est le besoin declare ou la consequence de la table, chaque distracteur
     * est dans la famille que l'item declare et aucun n'est defendable.
     */
    static void verifierLesReponsesEtLesDistracteurs(List<Item> items) {
        for (Item item : items) {
            String sujet = declaration(item, SUJET_DECLARE);
            List<String> declarees = declarationDesFamilles(item);
            List<String> calculees = new ArrayList<>();
            if (declaration(item, TACHE_DECLAREE).equals(BESOIN)) {
                String besoin = declaration(item, BESOIN_DECLARE);
                assertThat(nu(item.bonneReponse()))
                        .as("BPL-CE1-1 : la bonne réponse « %s » de '%s' n'est pas le besoin que l'item déclare (%s)",
                                item.bonneReponse(), item.enonce(), besoin)
                        .isEqualTo(besoin);
                for (String distracteur : item.distracteurs()) {
                    assertThat(NON_BESOINS)
                            .as("BPL-CE1-1 : « %s » (distracteur de '%s') n'est pas ce dont une plante n'a pas "
                                    + "besoin (%s)", distracteur, item.enonce(), NON_BESOINS)
                            .contains(nu(distracteur));
                    calculees.add("a");
                }
            } else {
                String prive = declaration(item, PRIVE_DECLARE);
                String consequence = CONSEQUENCES.get(sujet + "/" + prive);
                assertThat(consequence)
                        .as("BPL-CE1-1 : le couple (%s, privé de %s) de '%s' n'est pas dans la table des "
                                + "conséquences %s", sujet, prive, item.enonce(), CONSEQUENCES.keySet())
                        .isNotNull();
                assertThat(plat(item.bonneReponse()))
                        .as("BPL-CE1-1 : la bonne réponse « %s » de '%s' n'est pas la conséquence de la table "
                                + "pour (%s, privé de %s)", item.bonneReponse(), item.enonce(), sujet, prive)
                        .isEqualTo(consequence);
                for (String distracteur : item.distracteurs()) {
                    assertThat(EFFETS_CONTRAIRES)
                            .as("BPL-CE1-1 : « %s » (distracteur de '%s') n'est pas une conséquence que la "
                                    + "privation n'a jamais", distracteur, item.enonce())
                            .contains(plat(distracteur));
                    calculees.add("b");
                }
            }
            assertThat(declarees)
                    .as("TRV-CE1-2 : '%s' déclare les familles %s, ses distracteurs sont de familles %s",
                            item.enonce(), declarees, calculees)
                    .isEqualTo(calculees);
        }
    }

    /** BPL-CE1-1 : ni l'air, ni la terre, ni l'engrais, ni les sels mineraux, dans aucun champ lu. */
    static void verifierQueLesQuatreMotsNeSontJamaisProposes(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher mot = MOT_JAMAIS_PROPOSE.matcher(PaquetLu.sansAccent(champ));
                boolean trouve = mot.find();
                assertThat(trouve)
                        .as("BPL-CE1-1 : « %s » (champ de '%s') porte un des quatre mots jamais proposés : %s",
                                champ, item.enonce(), trouve ? mot.group(1) : "")
                        .isFalse();
            }
        }
    }

    // ----- FER-1, FER-3

    /** FER-1 : l'eau, la lumiere et la chaleur sont chacune la bonne reponse d'au moins un item besoin. */
    static void verifierLesTroisBesoinsEnBonneReponse(List<Item> items) {
        Set<String> enBonneReponse = new TreeSet<>();
        for (Item item : items) {
            if (declaration(item, TACHE_DECLAREE).equals(BESOIN)) {
                enBonneReponse.add(nu(item.bonneReponse()));
            }
        }
        for (String besoin : TROIS_BESOINS) {
            assertThat(enBonneReponse)
                    .as("FER-1 : « %s » n'est la bonne réponse d'aucun item besoin (%s)", besoin, enBonneReponse)
                    .contains(besoin);
        }
    }

    /** FER-3 : deux taches, la moins servie au moins trois items, et le commentaire dit les comptes. */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(declaration(item, TACHE_DECLAREE), 1, Integer::sum));
        assertThat(comptes.keySet()).as("FER-3 : le paquet ne sert pas deux tâches distinctes (%s)", comptes)
                .containsExactlyInAnyOrder(BESOIN, PRIVATION);
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

    /** BPL-CE1-2 : jamais la lumiere pour une graine, ni une phrase qui lie la graine a la lumiere. */
    static void verifierQueLaGraineNAPasBesoinDeLumiere(List<Item> items) {
        for (Item item : items) {
            if (GRAINE.matcher(item.enonce() + " " + item.indice()).find()) {
                assertThat(LUMIERE.matcher(PaquetLu.sansAccent(item.bonneReponse())).find())
                        .as("BPL-CE1-2 : la bonne réponse « %s » de '%s' donne la lumière à une graine",
                                item.bonneReponse(), item.enonce())
                        .isFalse();
            }
            for (String champ : item.champsLus()) {
                for (String phrase : champ.split("(?<=[.?!])[\\s\\u00A0]+")) {
                    assertThat(GRAINE.matcher(phrase).find()
                            && LUMIERE.matcher(PaquetLu.sansAccent(phrase)).find())
                            .as("BPL-CE1-2 : « %s » (champ de '%s') lie la graine à la lumière", phrase,
                                    item.enonce())
                            .isFalse();
                }
            }
            assertThat(declarationOuVide(item, SUJET_DECLARE).equals("graine")
                    && declarationOuVide(item, PRIVE_DECLARE).equals("lumiere"))
                    .as("BPL-CE1-2 : '%s' retire la lumière à une graine", item.enonce())
                    .isFalse();
        }
    }

    /** BPL-CE1-2, lecture de la condition : un item de lumiere nomme, dans son enonce, les feuilles. */
    static void verifierQueLaLumierePorteSurUnePlanteAFeuilles(List<Item> items) {
        for (Item item : items) {
            boolean lumiere = declarationOuVide(item, BESOIN_DECLARE).equals("lumiere")
                    || declarationOuVide(item, PRIVE_DECLARE).equals("lumiere");
            if (lumiere) {
                assertThat(FEUILLES.matcher(item.enonce()).find())
                        .as("BPL-CE1-2 : '%s' porte sur la lumière sans nommer les feuilles de la plante",
                                item.enonce())
                        .isTrue();
            }
        }
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe103EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-03"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-03");
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
    void bpl1ChaqueItemDeclareSonSujetSaTacheEtSonBesoinOuSaPrivation() {
        verifierLesDeclarations(paquet().items());
    }

    @Test
    void bpl1LaBonneReponseEstLeBesoinOuLaConsequenceDeLaTableEtChaqueDistracteurEstDansSaFamille() {
        verifierLesReponsesEtLesDistracteurs(paquet().items());
    }

    @Test
    void bpl1AucunChampNeProposeLairLaTerreLEngraisNiLesSelsMineraux() {
        verifierQueLesQuatreMotsNeSontJamaisProposes(paquet().items());
    }

    @Test
    void fer1LEauLaLumiereEtLaChaleurSontChacuneEnBonneReponse() {
        verifierLesTroisBesoinsEnBonneReponse(paquet().items());
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
    void bpl2LaLumierePorteSurUnePlanteQuiADejaDesFeuilles() {
        verifierQueLaLumierePorteSurUnePlanteAFeuilles(paquet().items());
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
    void leJeuFautifCommiteEstRefuseSurUneGraineDontLaBonneReponseEstLaLumiere() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 01")).hasSize(1);
        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 01")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2")
                .hasMessageContaining("donne la lumière à une graine");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiDitQueLaGraineABesoinDeLumiere() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 02")).hasSize(1);
        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 02")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2")
                .hasMessageContaining("lie la graine à la lumière");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneGraineDontLaBonneReponseEstLaLumiereSansAccent() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 23")).hasSize(1);
        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 23")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2")
                .hasMessageContaining("donne la lumière à une graine");
    }

    @Test
    void leJeuFautifCommiteLaisseLaQuestionDeLaGraineAvecLEauOuLaChaleurPasser() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        for (String sonde : List.of("sonde 03", "sonde 04")) {
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, sonde));
            verifierLesReponsesEtLesDistracteurs(sonde(fautif, sonde));
            verifierQueLaLumierePorteSurUnePlanteAFeuilles(sonde(fautif, sonde));
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneGrainePriveeDeLumiere() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 05")).hasSize(1);
        assertThatThrownBy(() -> verifierQueLaGraineNAPasBesoinDeLumiere(sonde(fautif, "sonde 05")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2")
                .hasMessageContaining("retire la lumière à une graine");
        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 05")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas dans la table des conséquences");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemDeLumiereSansLesFeuilles() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 06")).hasSize(1);
        assertThatThrownBy(() -> verifierQueLaLumierePorteSurUnePlanteAFeuilles(sonde(fautif, "sonde 06")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2")
                .hasMessageContaining("sans nommer les feuilles");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurChacunDesQuatreMotsEnDistracteurEtEnBonneReponse() {
        Paquet fautif = fautif("besoins-fautifs.yaml");
        List<String> sondes = List.of("sonde 07", "sonde 08", "sonde 09", "sonde 10", "sonde 11", "sonde 12",
                "sonde 13", "sonde 14");
        List<String> mots = List.of("air", "air", "terre", "terre", "engrais", "engrais", "sels mineraux",
                "sels mineraux");

        assertThat(sondes).as("huit sondes : quatre mots, un distracteur et une bonne réponse").hasSize(8);
        for (int rang = 0; rang < sondes.size(); rang++) {
            String sonde = sondes.get(rang);
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            assertThatThrownBy(() -> verifierQueLesQuatreMotsNeSontJamaisProposes(sonde(fautif, sonde)))
                    .as(sonde)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("BPL-CE1-1")
                    .hasMessageContaining("quatre mots jamais proposés : " + mots.get(rang));
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotJamaisProposeDansUnEnonceOuUnIndice() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        for (String sonde : List.of("sonde 19", "sonde 20")) {
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            assertThatThrownBy(() -> verifierQueLesQuatreMotsNeSontJamaisProposes(sonde(fautif, sonde)))
                    .as(sonde)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("quatre mots jamais proposés");
        }
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasLeBesoinDeclare() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 15")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas le besoin que l'item déclare");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurQuiEstUnVraiBesoin() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 16")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas ce dont une plante n'a pas besoin");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneConsequenceQuiEstCelleDUneAutrePrivation() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 17")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas une conséquence que la privation n'a jamais");
        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 22")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("n'est pas la conséquence de la table");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleDeclareeQuiNEstPasCelleDuDistracteur() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesReponsesEtLesDistracteurs(sonde(fautif, "sonde 18")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("déclare les familles");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnSujetDeclareQuiNEstPasCeluiDeLEnonce() {
        Paquet fautif = fautif("besoins-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesDeclarations(sonde(fautif, "sonde 21")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("BPL-CE1-2")
                .hasMessageContaining("déclare le sujet plante");
    }

    @Test
    void unPaquetPrivePourUnBesoinDeLaParentheseEstRefuse() {
        for (String besoin : TROIS_BESOINS) {
            List<Item> sansLeBesoin = paquet().items().stream()
                    .filter(item -> !(declaration(item, TACHE_DECLAREE).equals(BESOIN)
                            && nu(item.bonneReponse()).equals(besoin))).toList();

            assertThat(sansLeBesoin).as(besoin).hasSizeLessThan(paquet().items().size());
            assertThatThrownBy(() -> verifierLesTroisBesoinsEnBonneReponse(sansLeBesoin))
                    .as(besoin)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("FER-1")
                    .hasMessageContaining(besoin);
        }
    }

    @Test
    void unPaquetQuiNePorteQueDeuxItemsDePrivationEstRefuse() {
        Paquet paquet = paquet();
        List<Item> privations = paquet.items().stream()
                .filter(item -> declaration(item, TACHE_DECLAREE).equals(PRIVATION)).toList();
        List<Item> avecDeuxPrivations = new ArrayList<>(paquet.items().stream()
                .filter(item -> declaration(item, TACHE_DECLAREE).equals(BESOIN)).toList());
        avecDeuxPrivations.addAll(privations.subList(0, 2));

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxPrivations, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("la moins servie");
    }

    @Test
    void unCommentaireQuiEcritUnMauvaisCompteDeTachesEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replace("(5 items)", "(4 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("comptes");
    }
}
