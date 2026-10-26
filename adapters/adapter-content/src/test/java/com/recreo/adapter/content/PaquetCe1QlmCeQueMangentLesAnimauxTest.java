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
 * S57-BR-QLM-CE1-02 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-qlm-ce-que-mangent-les-animaux}, ligne {@code QLM-CE1-02} - relier quelques animaux
 * familiers a ce qu'ils mangent. Conditions de nadia-prof
 * ({@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md}, section 3.2 :
 * {@code RAL-CE1-1}, {@code RAL-CE1-2} ; section 2 : {@code FER-1}, {@code FER-3}) et critere 6 de la
 * story ({@code avis-nadia-seconde-tache-ce1-qlm-02.md}). Les conditions transverses ({@code TRV-CE1-1},
 * {@code -2}, {@code -5}, {@code LON-CE1-1}, {@code -2}, {@code -5}, {@code QMT-CE1-1}, {@code NRC-CE1-1}
 * sur le texte) sont gardees une fois pour les neuf lots par
 * {@code ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest}.
 *
 * <p><b>Comment ce test lit {@code FER-3}</b> ({@code avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md}).
 * La tache d'un item est une fonction de sa seule bonne reponse : une categorie ({@link #categorie}) pose
 * la tache {@code categorie}, toute autre bonne reponse la tache {@code aliment}. Aucune declaration de
 * tache par item. La reconnaissance d'une categorie est une egalite apres normalisation de forme, jamais
 * une recherche de sous-chaine. Les deux lignes de compte de l'en-tete du paquet sont relues contre les
 * comptes calcules sur les items.
 *
 * <p><b>Comment ce test lit {@code RAL-CE1-1} et {@code RAL-CE1-2}.</b> Chaque item declare son animal
 * en commentaire ; l'en-tete du paquet porte la liste fermee des animaux, une ligne par animal avec sa
 * categorie et sa source ({@code QMT-CE1-2}). L'animal declare figure dans la liste, l'enonce le nomme,
 * et la bonne reponse d'un item de categorie est celle que la liste donne a l'animal. Les quatre animaux
 * exclus et les aliments du commerce sont refuses dans tout champ que l'enfant lit.
 *
 * <p><b>Ce que ce test ne garde pas</b> : les paraphrases d'une categorie en proposition d'un item
 * aliment ({@code FER-3}) ; que seuls des animaux a une seule categorie entrent en tache aliment, que les
 * deux distracteurs d'un item aliment soient de l'autre categorie et qu'aucun ne soit mange par
 * l'animal ({@code TRV-CE1-2}) ; que {@code meme type d'article} soit lu au-dela de ce qu'un item ecrit ;
 * que chaque categorie et chaque aliment soit exact contre sa source et contre
 * {@code cm1-sciences-regimes-alimentaires} ({@code QMT-CE1-2}, {@code CYC-1}, {@code QC9}) ; que
 * l'enonce dise que l'animal mange dans la nature ou a la ferme ({@code RAL-CE1-2}) ; que la tache
 * aliment soit une operation mentale distincte de la tache categorie. Cela reste a la lecture et au jeu
 * de nadia-prof.
 */
class PaquetCe1QlmCeQueMangentLesAnimauxTest {

    private static final String PAQUET = "ce1-qlm-ce-que-mangent-les-animaux";

    private static final Path FICHIER_CONTENU = Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1QlmCeQueMangentLesAnimauxTest/contenu");

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String CATEGORIE = "categorie";

    private static final String ALIMENT = "aliment";

    /** FER-3 : la reconnaissance d'une categorie, cle normalisee vers categorie ecrite. */
    private static final Map<String, String> CATEGORIES = Map.of(
            "vegetaux", "des végétaux",
            "autres animaux", "d'autres animaux",
            "deux", "les deux");

    /** FER-3 : un seul article de tete retire, sur une liste fermee, du plus long au plus court. */
    private static final Pattern ARTICLE_DE_TETE = Pattern.compile("^(de la |de l'|des |les |du |de |d'|l'|le |la )");

    private static final Pattern BLANCS = Pattern.compile("[\\s\\u00A0\\u202F]+");

    private static final Pattern TACHE_DE_L_ENTETE =
            Pattern.compile("^#\\s+(categorie|aliment) : .*\\((\\d+) items?");

    /** RAL-CE1-1 : une ligne de la liste fermee des animaux, dans l'en-tete du paquet. */
    private static final Pattern ANIMAL_DE_L_ENTETE =
            Pattern.compile("^#\\s+animal ([a-z]+) : (vegetaux|autres animaux|les deux) ; source : (\\S.*)$");

    private static final Pattern ANIMAL_DECLARE = Pattern.compile("^RAL-CE1-1 animal : ([a-z]+)$");

    /** RAL-CE1-2 : les quatre animaux exclus, sur le texte sans accent. */
    private static final Pattern ANIMAL_EXCLU = Pattern.compile(
            "(?<!\\p{L})(chat|chatte|chien|chienne|renard|renarde|herisson)s?(?!\\p{L})", FLAGS);

    /** RAL-CE1-2 : les aliments du commerce pour animaux, sur le texte sans accent. */
    private static final Pattern ALIMENT_DU_COMMERCE = Pattern.compile(
            "(?<!\\p{L})(croquettes?|patee|granules?)(?!\\p{L})", FLAGS);

    /** TYP-1 : l'espace avant le point d'interrogation est insecable. */
    private static final String ESPACE_ORDINAIRE_AVANT_PONCTUATION = "[^\\u00A0] [?:;!]";

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

    // ----- FER-3 : la tache est une fonction de la bonne reponse

    /** Minuscule, sans accent, apostrophe droite, blancs ramenes a une espace, sans ponctuation finale ni article. */
    static String normalise(String proposition) {
        String texte = PaquetLu.sansAccent(proposition).replace('\u2019', '\'');
        texte = BLANCS.matcher(texte).replaceAll(" ").strip();
        if (texte.endsWith(".") || texte.endsWith("!")) {
            texte = texte.substring(0, texte.length() - 1).strip();
        }
        return ARTICLE_DE_TETE.matcher(texte).replaceFirst("").strip();
    }

    /** La categorie que cette proposition ecrit, ou rien : une egalite avec l'ensemble ferme, jamais une sous-chaine. */
    static Optional<String> categorie(String proposition) {
        return Optional.ofNullable(CATEGORIES.get(normalise(proposition)));
    }

    static String tache(Item item) {
        return categorie(item.bonneReponse()).isPresent() ? CATEGORIE : ALIMENT;
    }

    private static Map<String, Integer> comptesDesTaches(List<Item> items) {
        Map<String, Integer> comptes = new TreeMap<>();
        items.forEach(item -> comptes.merge(tache(item), 1, Integer::sum));
        return comptes;
    }

    /**
     * FER-3 : deux taches, la moins servie au moins trois items. Les comptes sont calcules d'abord sur les
     * items, puis seulement compares a ceux que l'en-tete ecrit : un en-tete ne rattrape jamais un paquet.
     */
    static void verifierLesDeuxTachesEtLeursComptes(List<Item> items, String entete) {
        Map<String, Integer> comptes = comptesDesTaches(items);
        assertThat(comptes.keySet()).as("FER-3 : le paquet ne sert pas deux tâches distinctes (%s)", comptes)
                .containsExactlyInAnyOrder(CATEGORIE, ALIMENT);
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

    /**
     * FER-3 : un item aliment ne propose aucune categorie, un item categorie propose les trois. Sans le
     * premier, un item aliment redevient un item categorie avec un mot change ; sans le second, un item
     * categorie gonfle le compte de sa tache sans la poser.
     */
    static void verifierLesPropositionsDeChaqueTache(List<Item> items) {
        for (Item item : items) {
            if (tache(item).equals(ALIMENT)) {
                for (String proposition : item.propositions()) {
                    assertThat(categorie(proposition))
                            .as("FER-3 : « %s » (proposition de '%s', tâche aliment) est une catégorie",
                                    proposition, item.enonce())
                            .isEmpty();
                }
            } else {
                Set<String> offertes = new TreeSet<>();
                item.propositions().forEach(proposition -> categorie(proposition).ifPresent(offertes::add));
                assertThat(offertes)
                        .as("FER-3 : '%s' (tâche catégorie) n'offre pas les trois catégories en propositions",
                                item.enonce())
                        .hasSize(3);
            }
        }
    }

    /** FER-1 : des vegetaux, d'autres animaux et les deux sont chacune la bonne reponse d'au moins un item. */
    static void verifierLesTroisCategoriesEnBonneReponse(List<Item> items) {
        Set<String> enBonneReponse = new TreeSet<>();
        items.forEach(item -> categorie(item.bonneReponse()).ifPresent(enBonneReponse::add));
        CATEGORIES.values().forEach(ecrite -> assertThat(enBonneReponse)
                .as("FER-1 : « %s » n'est la bonne réponse d'aucun item (%s)", ecrite, enBonneReponse)
                .contains(ecrite));
    }

    // ----- RAL-CE1-1 : la liste fermee des animaux, sa categorie et sa source

    /** La liste fermee que l'en-tete ecrit : animal vers categorie normalisee. */
    static Map<String, String> listeDesAnimaux(String entete) {
        Map<String, String> liste = new LinkedHashMap<>();
        for (String ligne : entete.split("\\R")) {
            Matcher animal = ANIMAL_DE_L_ENTETE.matcher(ligne);
            if (animal.matches()) {
                liste.put(animal.group(1), animal.group(2).equals("les deux") ? "deux" : animal.group(2));
            }
        }
        return liste;
    }

    private static String animalDeclare(Item item) {
        List<String> declares = item.commentaires().stream().map(ANIMAL_DECLARE::matcher)
                .filter(Matcher::matches).map(trouve -> trouve.group(1)).toList();
        assertThat(declares).as("RAL-CE1-1 : '%s' ne déclare pas exactement un animal", item.enonce()).hasSize(1);
        return declares.get(0);
    }

    /**
     * RAL-CE1-1 et QMT-CE1-2 : aucun animal exclu dans la liste, l'animal de chaque item y figure avec sa
     * source, l'enonce le nomme, et la bonne reponse d'un item categorie est celle de la liste.
     */
    static void verifierLaListeDesAnimaux(List<Item> items, String entete) {
        Map<String, String> liste = listeDesAnimaux(entete);
        assertThat(liste).as("RAL-CE1-1 : l'en-tête du paquet n'écrit aucune liste des animaux").isNotEmpty();
        liste.keySet().forEach(animal -> assertThat(ANIMAL_EXCLU.matcher(animal).matches())
                .as("RAL-CE1-2 : la liste des animaux porte un des quatre animaux exclus : %s", animal)
                .isFalse());
        for (Item item : items) {
            String animal = animalDeclare(item);
            assertThat(liste)
                    .as("QMT-CE1-2 : l'animal « %s » de '%s' n'est pas dans la liste fermée des animaux, avec sa "
                            + "catégorie et sa source", animal, item.enonce())
                    .containsKey(animal);
            assertThat(Pattern.compile("(?<!\\p{L})" + animal + "(?!\\p{L})", FLAGS)
                    .matcher(PaquetLu.sansAccent(item.enonce())).find())
                    .as("RAL-CE1-1 : l'énoncé de '%s' ne nomme pas l'animal qu'il déclare (%s)", item.enonce(),
                            animal)
                    .isTrue();
            if (tache(item).equals(CATEGORIE)) {
                assertThat(categorie(item.bonneReponse()).orElseThrow())
                        .as("RAL-CE1-1 : la bonne réponse « %s » de '%s' n'est pas le régime que la liste donne à "
                                + "l'animal %s (%s)", item.bonneReponse(), item.enonce(), animal,
                                CATEGORIES.get(liste.get(animal)))
                        .isEqualTo(CATEGORIES.get(liste.get(animal)));
            }
        }
    }

    // ----- RAL-CE1-2

    /** RAL-CE1-2 : ni chat, ni chien, ni renard, ni herisson, ni aliment du commerce, dans aucun champ lu. */
    static void verifierQueLesQuatreAnimauxEtLeCommerceSontAbsents(List<Item> items) {
        for (Item item : items) {
            for (String champ : item.champsLus()) {
                Matcher animal = ANIMAL_EXCLU.matcher(PaquetLu.sansAccent(champ));
                boolean exclu = animal.find();
                assertThat(exclu)
                        .as("RAL-CE1-2 : « %s » (champ de '%s') porte un des quatre animaux exclus : %s", champ,
                                item.enonce(), exclu ? animal.group(1) : "")
                        .isFalse();
                Matcher commerce = ALIMENT_DU_COMMERCE.matcher(PaquetLu.sansAccent(champ));
                boolean achete = commerce.find();
                assertThat(achete)
                        .as("RAL-CE1-2 : « %s » (champ de '%s') porte un aliment du commerce pour animaux : %s",
                                champ, item.enonce(), achete ? commerce.group(1) : "")
                        .isFalse();
            }
        }
    }

    // ----- le paquet

    @Test
    void adr0081CePaquetDeclareQlmCe102EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(PaquetLu.lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("QLM-CE1-02"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Questionner le monde");
        Set<String> citees = new TreeSet<>();
        Matcher ligne = Pattern.compile("QLM-CE1-[0-9]+").matcher(PaquetLu.lire(FICHIER_CONTENU));
        while (ligne.find()) {
            citees.add(ligne.group());
        }
        assertThat(citees).as("QC2 : le fichier ne cite aucune autre ligne que la sienne")
                .containsExactly("QLM-CE1-02");
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
    void fer3DeuxTachesLaMoinsServiePorteTroisItemsEtLEnTeteDitLesComptes() {
        Paquet paquet = paquet();
        verifierLesDeuxTachesEtLeursComptes(paquet.items(), paquet.entete());
    }

    @Test
    void fer3UnItemAlimentNeProposeAucuneCategorieEtUnItemCategorieLesProposeToutes() {
        verifierLesPropositionsDeChaqueTache(paquet().items());
    }

    @Test
    void fer1DesVegetauxDAutresAnimauxEtLesDeuxSontChacuneEnBonneReponse() {
        verifierLesTroisCategoriesEnBonneReponse(paquet().items());
    }

    @Test
    void ral1LAnimalDeChaqueItemEstDansLaListeFermeeAvecSaCategorieEtSaSource() {
        Paquet paquet = paquet();
        verifierLaListeDesAnimaux(paquet.items(), paquet.entete());
    }

    @Test
    void ral2AucunDesQuatreAnimauxExclusNiAucunAlimentDuCommerceDansLePaquet() {
        verifierQueLesQuatreAnimauxEtLeCommerceSontAbsents(paquet().items());
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
    void s1UnPaquetQuiNePoseQueLaTacheCategorieEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> categories = paquet.items().stream().filter(item -> tache(item).equals(CATEGORIE)).toList();

        assertThat(categories).hasSizeLessThan(paquet.items().size());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(categories, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("deux tâches");
    }

    @Test
    void s1UnPaquetSansItemEstRefuse() {
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(List.of(), paquet().entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("deux tâches");
    }

    @Test
    void s2UnPaquetDontLaTacheAlimentNePorteQueDeuxItemsEstRefuseMemeSiSonEnTeteDitLeContraire() {
        Paquet paquet = paquet();
        List<Item> avecDeuxAliments = new ArrayList<>(paquet.items().stream()
                .filter(item -> tache(item).equals(CATEGORIE)).toList());
        avecDeuxAliments.addAll(paquet.items().stream().filter(item -> tache(item).equals(ALIMENT)).limit(2)
                .toList());

        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(avecDeuxAliments, paquet.entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("la moins servie");
    }

    @Test
    void s3UnItemAlimentQuiProposeDAutresAnimauxEcritAvecUneApostropheTypographiqueEstRefuse() {
        Paquet fautif = fautif("animaux-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 01")).hasSize(1);
        assertThatThrownBy(() -> verifierLesPropositionsDeChaqueTache(sonde(fautif, "sonde 01")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("d\u2019autres animaux");
    }

    @Test
    void s4UnItemCategorieSansLesTroisCategoriesEnPropositionsEstRefuse() {
        Paquet fautif = fautif("animaux-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 02")).hasSize(1);
        assertThatThrownBy(() -> verifierLesPropositionsDeChaqueTache(sonde(fautif, "sonde 02")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("trois catégories");
    }

    @Test
    void p1UnItemAlimentATroisAlimentsEtUnItemCategorieJustePassent() {
        Paquet fautif = fautif("animaux-fautifs.yaml");

        for (String sonde : List.of("sonde 03", "sonde 04")) {
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            verifierLesPropositionsDeChaqueTache(sonde(fautif, sonde));
            verifierLaListeDesAnimaux(sonde(fautif, sonde), paquet().entete());
            verifierQueLesQuatreAnimauxEtLeCommerceSontAbsents(sonde(fautif, sonde));
        }
        assertThat(tache(sonde(fautif, "sonde 03").get(0))).isEqualTo(ALIMENT);
        assertThat(tache(sonde(fautif, "sonde 04").get(0))).isEqualTo(CATEGORIE);
    }

    @Test
    void s5UnEnTeteQuiEcritUnMauvaisCompteDeTachesEstRefuse() {
        Paquet paquet = paquet();
        String faux = paquet.entete().replaceFirst("\\(6 items\\)", "(5 items)");

        assertThat(faux).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLesDeuxTachesEtLeursComptes(paquet.items(), faux))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FER-3")
                .hasMessageContaining("comptes");
    }

    @Test
    void unPaquetPriveDUneCategorieEnBonneReponseEstRefuse() {
        for (Map.Entry<String, String> categorie : CATEGORIES.entrySet()) {
            List<Item> sansLaCategorie = paquet().items().stream()
                    .filter(item -> !categorie(item.bonneReponse()).equals(Optional.of(categorie.getValue())))
                    .toList();

            assertThat(sansLaCategorie).as(categorie.getValue()).hasSizeLessThan(paquet().items().size());
            assertThatThrownBy(() -> verifierLesTroisCategoriesEnBonneReponse(sansLaCategorie))
                    .as(categorie.getValue())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("FER-1")
                    .hasMessageContaining(categorie.getValue());
        }
    }

    @Test
    void ral2UnItemSurChacunDesQuatreAnimauxExclusEstRefuse() {
        Paquet fautif = fautif("animaux-fautifs.yaml");
        List<String> sondes = List.of("sonde 05", "sonde 06", "sonde 07", "sonde 08");
        List<String> animaux = List.of("chat", "chien", "renard", "herisson");

        for (int rang = 0; rang < sondes.size(); rang++) {
            String sonde = sondes.get(rang);
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            assertThatThrownBy(() -> verifierQueLesQuatreAnimauxEtLeCommerceSontAbsents(sonde(fautif, sonde)))
                    .as(sonde)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("RAL-CE1-2")
                    .hasMessageContaining("quatre animaux exclus : " + animaux.get(rang));
        }
    }

    @Test
    void ral2UnItemQuiProposeDesCroquettesOuDeLaPateeEstRefuse() {
        Paquet fautif = fautif("animaux-fautifs.yaml");
        List<String> sondes = List.of("sonde 09", "sonde 10");
        List<String> aliments = List.of("croquettes", "patee");

        for (int rang = 0; rang < sondes.size(); rang++) {
            String sonde = sondes.get(rang);
            assertThat(sonde(fautif, sonde)).as(sonde).hasSize(1);
            assertThatThrownBy(() -> verifierQueLesQuatreAnimauxEtLeCommerceSontAbsents(sonde(fautif, sonde)))
                    .as(sonde)
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("RAL-CE1-2")
                    .hasMessageContaining("aliment du commerce pour animaux : " + aliments.get(rang));
        }
    }

    @Test
    void ral2UneListeDesAnimauxQuiPorteUnAnimalExcluEstRefusee() {
        Paquet paquet = paquet();
        String entete = paquet.entete() + "\n#   animal chat : vegetaux ; source : avis-nadia\n";

        assertThatThrownBy(() -> verifierLaListeDesAnimaux(paquet.items(), entete))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RAL-CE1-2")
                .hasMessageContaining("chat");
    }

    @Test
    void ral1UnAnimalQuiNEstPasDansLaListeFermeeEstRefuse() {
        Paquet fautif = fautif("animaux-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 11")).hasSize(1);
        assertThatThrownBy(() -> verifierLaListeDesAnimaux(sonde(fautif, "sonde 11"), paquet().entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("QMT-CE1-2")
                .hasMessageContaining("n'est pas dans la liste fermée");
    }

    @Test
    void ral1UneBonneReponseQuiNEstPasLaCategorieDeLaListeEstRefusee() {
        Paquet fautif = fautif("animaux-fautifs.yaml");

        assertThat(sonde(fautif, "sonde 12")).hasSize(1);
        assertThatThrownBy(() -> verifierLaListeDesAnimaux(sonde(fautif, "sonde 12"), paquet().entete()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RAL-CE1-1")
                .hasMessageContaining("n'est pas le régime que la liste donne");
    }

    @Test
    void unEnTeteSansListeDesAnimauxEstRefuse() {
        Paquet paquet = paquet();
        String sansListe = paquet.entete().replaceAll("(?m)^#\\s+animal .*$", "#");

        assertThat(sansListe).isNotEqualTo(paquet.entete());
        assertThatThrownBy(() -> verifierLaListeDesAnimaux(paquet.items(), sansListe))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RAL-CE1-1")
                .hasMessageContaining("aucune liste des animaux");
    }
}
