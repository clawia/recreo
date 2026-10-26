package com.recreo.adapter.content;

import com.recreo.adapter.content.PaquetLu.Item;
import com.recreo.adapter.content.PaquetLu.Paquet;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Les conditions transverses de questionner le monde CE1, ecrites une fois pour les neuf lots
 * ({@code avis-theo-palier-5-neuf-lots-qlm-ce1.md}, 3.4) : {@code TRV-CE1-1}, {@code TRV-CE1-2},
 * {@code TRV-CE1-5}, {@code LON-CE1-1}, {@code -2}, {@code -5} et {@code QMT-CE1-1}, telles que
 * {@code ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md} (section 2) les ecrit
 * et que les sondes de {@code QC6} les mettent a l'epreuve.
 *
 * <p><b>La population est le lot de matiere, derivee des descripteurs</b> (ADR-0079) : tout fichier du
 * repertoire de contenu servi dont le descripteur declare la matiere et le niveau ci-dessous, branche
 * au catalogue ou non. Aucune liste de fichiers n'est ecrite ici : un lot de plus y entre sans
 * toucher ce fichier, et un second fichier du meme lot est juge comme le premier.
 *
 * <p><b>Ce que ce test lit, et ce qu'il ne lit pas.</b> Il lit la forme : les cles servies, le nombre
 * et la distinction des propositions, la presence d'une famille de distracteurs declaree a cote de
 * l'item, la longueur de la bonne reponse, la longueur et la distinction des indices, la longueur des
 * enonces, les quinze mots exclus. Il ne lit pas {@code TRV-CE1-5} au-dela de la forme (un indice est
 * une methode et jamais une regle fausse), {@code TRV-CE1-6}, {@code QMT-CE1-2} (la source de chaque
 * fait) ni {@code NRC-CE1-1} au-dela de sa partie textuelle : non gardes ici, lus par nadia-prof
 * ({@code QC9}). {@code NRC-CE1-1}, partie textuelle seule : un item dont l'enonce, ou l'ensemble des
 * trois propositions, est celui d'un item de CP est refuse ; la population de CP est derivee des
 * descripteurs (tout paquet dont le niveau est CP), jamais enumeree. {@code TRV-CE1-1}
 * est gardee sur ses deux champs interdits, {@code texteSupport} et {@code texteAVoixHaute} : leur
 * presence est refusee, leur contenu n'a pas a etre lu.
 *
 * <p><b>Les comptes de {@code TRV-CE1-2} et de {@code TRV-CE1-5} se font par paquet</b> : un paquet
 * dont aucun compte ne depasse la moitie fait un lot dont aucun compte ne la depasse, la lecture par
 * paquet implique donc la lecture par lot. Si nadia-prof entend ces deux comptes sur le lot seul,
 * cette garde est plus stricte que sa condition et le dit ici.
 */
class ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest {

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of("src/test/resources/contreexemple"
            + "/ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest");

    /** Le lot, tel que le descripteur l'ecrit une fois normalise (ADR-0079) : matiere puis niveau. */
    private static final String LOT = "questionner le monde / ce1";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
            | Pattern.UNICODE_CHARACTER_CLASS;

    private static final Pattern LIGNE_DE_QUESTIONNER_LE_MONDE_CE1 = Pattern.compile("QLM-CE1-[0-9]{2}");

    /** TRV-CE1-1 : les deux champs dont la presence est refusee. */
    private static final List<String> CHAMPS_INTERDITS = List.of("texteSupport", "texteAVoixHaute");

    /** TRV-CE1-2 : la declaration des familles de distracteurs, a cote de l'item, deux lettres. */
    private static final Pattern FAMILLES_DECLAREES = Pattern.compile("^TRV-CE1-2 : ([a-z]), ([a-z])$");

    /** TRV-CE1-2 : la ligne de l'en-tete qui dit ce qu'est une famille. */
    private static final Pattern FAMILLE_DE_L_ENTETE = Pattern.compile("^#\\s+famille ([a-z]) : .+$");

    /** NRC-CE1-1 : le niveau dont les items sont compares, tel que le descripteur l'ecrit une fois normalise. */
    private static final String NIVEAU_COMPARE = "cp";

    private static final Pattern TERMINAISON_DE_PHRASE = Pattern.compile("[.?!](?:[\\s\\u00A0]+|$)");

    private static final Pattern FIN_DE_PHRASE = Pattern.compile("[.?!][\\s\\u00A0]+\\p{Lu}", FLAGS);

    /**
     * QMT-CE1-1 : les quinze mots que l'enfant de CE1 ne lit pas, ecrits sans accent et au singulier.
     * Le texte lu est mis sans accent avant la recherche, et un pluriel ou un feminin est le meme mot.
     */
    private static final List<String> MOTS_EXCLUS = List.of(
            "herbivore", "carnivore", "omnivore", "reproduction", "vieillissement",
            "fusion", "solidification", "vaporisation", "evaporation", "condensation",
            "conducteur", "isolant", "larve", "plantule", "legumineuse");

    private static final Pattern MOT_EXCLU = Pattern.compile(
            "(?<!\\p{L})(" + String.join("|", MOTS_EXCLUS) + ")(?:e|s|es)?(?!\\p{L})", FLAGS);

    // ----- la population, derivee des descripteurs

    /** Les paquets d'un repertoire dont le descripteur declare le lot, lus avec leurs commentaires. */
    static List<Paquet> paquetsDuLot(Path repertoire) {
        List<Paquet> paquets = new ArrayList<>();
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            for (Path fichier : arborescence.filter(Files::isRegularFile)
                    .filter(candidat -> candidat.getFileName().toString().endsWith(".yaml")).sorted().toList()) {
                if (!(new Yaml().load(PaquetLu.lire(fichier)) instanceof Map<?, ?> corps)
                        || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                        || !(corps.get("items") instanceof List<?>)) {
                    continue;
                }
                String lot = UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest
                        .normalise(descripteur.get("matiere") + " / " + descripteur.get("niveau"));
                if (lot.equals(LOT)) {
                    paquets.add(PaquetLu.lirePaquet(fichier));
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return paquets;
    }

    private static Paquet fautif(String nom) {
        return PaquetLu.lirePaquet(CONTRE_EXEMPLE.resolve("contenu").resolve(nom));
    }

    /** Les items fautifs dont l'enonce porte ce marqueur, pour mordre un verificateur a la fois. */
    private static List<Item> sonde(Paquet paquet, String marqueur) {
        return paquet.items().stream().filter(item -> item.enonce().contains(marqueur)).toList();
    }

    // ----- les verificateurs, un par condition

    /** TRV-CE1-1 : une seule ligne de questionner le monde CE1, ni texteSupport ni champ a voix haute. */
    static void verifierLaDeclarationEtLesChamps(Paquet paquet) {
        assertThat(paquet.niveau())
                .as("TRV-CE1-1 : %s ne déclare pas niveau CE1", paquet.fichier()).isEqualTo("CE1");
        assertThat(paquet.lignes())
                .as("TRV-CE1-1 : %s ne déclare pas exactement une ligne de référentiel", paquet.fichier())
                .hasSize(1);
        assertThat(paquet.lignes().get(0))
                .as("TRV-CE1-1 : la ligne déclarée par %s n'est pas une ligne de questionner le monde CE1",
                        paquet.fichier())
                .matches(LIGNE_DE_QUESTIONNER_LE_MONDE_CE1);
        for (Item item : paquet.items()) {
            for (String interdit : CHAMPS_INTERDITS) {
                assertThat(item.cles())
                        .as("TRV-CE1-1 : '%s' porte le champ %s, refusé sur questionner le monde CE1",
                                item.enonce(), interdit)
                        .doesNotContain(interdit);
            }
        }
    }

    /**
     * TRV-CE1-2 : trois propositions distinctes, familles declarees au format exact et presentes dans
     * l'en-tete, la plus longue dans la moitie au plus.
     */
    static void verifierLesPropositions(Paquet paquet) {
        Set<String> famillesDeLEntete = new HashSet<>();
        for (String ligne : paquet.entete().split("\\R")) {
            Matcher famille = FAMILLE_DE_L_ENTETE.matcher(ligne);
            if (famille.matches()) {
                famillesDeLEntete.add(famille.group(1));
            }
        }
        for (Item item : paquet.items()) {
            assertThat(item.distracteurs())
                    .as("TRV-CE1-2 : '%s' ne porte pas trois propositions", item.enonce()).hasSize(2);
            assertThat(item.propositions().stream().distinct().count())
                    .as("TRV-CE1-2 : les propositions de '%s' ne sont pas trois propositions distinctes",
                            item.enonce())
                    .isEqualTo(3);
            List<String> declarations = item.commentaires().stream()
                    .filter(ligne -> ligne.startsWith("TRV-CE1-2")).toList();
            assertThat(declarations)
                    .as("TRV-CE1-2 : '%s' ne nomme pas les familles de ses distracteurs à côté de l'item, "
                            + "exactement une fois au format « TRV-CE1-2 : x, y »", item.enonce())
                    .hasSize(1)
                    .allMatch(ligne -> FAMILLES_DECLAREES.matcher(ligne).matches());
            Matcher familles = FAMILLES_DECLAREES.matcher(declarations.get(0));
            familles.matches();
            for (int groupe = 1; groupe <= 2; groupe++) {
                assertThat(famillesDeLEntete)
                        .as("TRV-CE1-2 : '%s' déclare la famille %s, absente de l'en-tête de %s",
                                item.enonce(), familles.group(groupe), paquet.fichier())
                        .contains(familles.group(groupe));
            }
            for (String proposition : item.propositions()) {
                assertThat(PaquetLu.mots(proposition))
                        .as("LON-CE1-5 : « %s » (proposition de '%s') compte plus de six mots", proposition,
                                item.enonce())
                        .isLessThanOrEqualTo(6);
                assertThat(FIN_DE_PHRASE.matcher(proposition).find())
                        .as("LON-CE1-5 : « %s » compte plus d'une phrase", proposition).isFalse();
            }
        }
        long plusLongue = paquet.items().stream().filter(item -> item.distracteurs().stream()
                .allMatch(distracteur -> item.bonneReponse().length() > distracteur.length())).count();
        assertThat(2 * plusLongue)
                .as("TRV-CE1-2 : %d items de %s où la bonne réponse est strictement la plus longue sur %d, "
                        + "plus de la moitié", plusLongue, paquet.fichier(), paquet.items().size())
                .isLessThanOrEqualTo(paquet.items().size());
    }

    /** LON-CE1-1 et -2 : un enonce de vingt mots et trois phrases au plus. */
    static void verifierLesEnonces(Paquet paquet) {
        for (Item item : paquet.items()) {
            assertThat(PaquetLu.mots(item.enonce()))
                    .as("LON-CE1-1 : l'énoncé « %s » compte plus de vingt mots", item.enonce())
                    .isLessThanOrEqualTo(20);
            assertThat(TERMINAISON_DE_PHRASE.matcher(item.enonce()).results().count())
                    .as("LON-CE1-2 : l'énoncé « %s » compte plus de trois phrases", item.enonce())
                    .isLessThanOrEqualTo(3);
        }
    }

    /** TRV-CE1-5 : un indice par item, douze mots au plus, aucune proposition, pas le meme sur tout le paquet. */
    static void verifierLesIndices(Paquet paquet) {
        for (Item item : paquet.items()) {
            assertThat(item.indice())
                    .as("TRV-CE1-5 : '%s' ne porte pas d'indice", item.enonce()).isNotBlank();
            assertThat(PaquetLu.mots(item.indice()))
                    .as("TRV-CE1-5 : l'indice « %s » compte plus de douze mots", item.indice())
                    .isLessThanOrEqualTo(12);
            for (String proposition : item.propositions()) {
                assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(proposition) + "(?!\\p{L})", FLAGS)
                        .matcher(item.indice()).find())
                        .as("TRV-CE1-5 : l'indice « %s » contient une proposition de son item : « %s »",
                                item.indice(), proposition)
                        .isFalse();
            }
        }
        if (paquet.items().size() > 1) {
            assertThat(paquet.items().stream().map(Item::indice).distinct().count())
                    .as("TRV-CE1-5 : tous les items de %s portent le même indice", paquet.fichier())
                    .isGreaterThan(1);
        }
    }

    /** QMT-CE1-1 : aucun des quinze mots exclus dans l'enonce, l'indice ou une proposition. */
    static void verifierLesMotsExclus(Paquet paquet) {
        for (Item item : paquet.items()) {
            for (String champ : item.champsLus()) {
                Matcher mot = MOT_EXCLU.matcher(PaquetLu.sansAccent(champ));
                boolean trouve = mot.find();
                assertThat(trouve)
                        .as("QMT-CE1-1 : « %s » (champ de '%s') porte un mot que l'enfant de CE1 ne lit pas : %s",
                                champ, item.enonce(), trouve ? mot.group(1) : "")
                        .isFalse();
            }
        }
    }

    /** Un item d'un paquet de CP, tel que NRC-CE1-1 le compare : enonce et ensemble de propositions normalises. */
    record ItemDeCp(String fichier, String enonce, Set<String> propositions) {
    }

    /** Les items de tout paquet du repertoire dont le descripteur declare le niveau CP, quelle que soit la matiere. */
    static List<ItemDeCp> itemsDeCp(Path repertoire) {
        List<ItemDeCp> items = new ArrayList<>();
        try (Stream<Path> arborescence = Files.walk(repertoire)) {
            for (Path fichier : arborescence.filter(Files::isRegularFile)
                    .filter(candidat -> candidat.getFileName().toString().endsWith(".yaml")).sorted().toList()) {
                if (!(new Yaml().load(PaquetLu.lire(fichier)) instanceof Map<?, ?> corps)
                        || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                        || !(corps.get("items") instanceof List<?> entrees)
                        || !UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest
                                .normalise(String.valueOf(descripteur.get("niveau"))).equals(NIVEAU_COMPARE)) {
                    continue;
                }
                for (Object entree : entrees) {
                    if (!(entree instanceof Map<?, ?> item) || item.get("enonce") == null) {
                        continue;
                    }
                    Set<String> propositions = new HashSet<>();
                    if (item.get("bonneReponse") != null && item.get("distracteurs") instanceof List<?> distracteurs) {
                        propositions.add(normaliseLeTexte(String.valueOf(item.get("bonneReponse"))));
                        distracteurs.forEach(distracteur ->
                                propositions.add(normaliseLeTexte(String.valueOf(distracteur))));
                    }
                    items.add(new ItemDeCp(fichier.getFileName().toString(),
                            normaliseLeTexte(String.valueOf(item.get("enonce"))), propositions));
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return items;
    }

    private static String normaliseLeTexte(String texte) {
        return texte.replaceAll("[\\s\\u00A0]+", " ").strip().toLowerCase(Locale.FRENCH);
    }

    /**
     * NRC-CE1-1, partie textuelle : l'enonce normalise d'un item, ou l'ensemble normalise de ses trois
     * propositions, n'est pas celui d'un item de CP. Une seule proposition en commun est permise : une
     * etape de la plante n'est pas un item repris.
     */
    static void verifierQueRienNEstReprisDUnItemDeCp(Paquet paquet, List<ItemDeCp> itemsDeCp) {
        assertThat(itemsDeCp).as("NRC-CE1-1 : aucun item de CP n'est lu, la comparaison ne juge rien")
                .isNotEmpty();
        for (Item item : paquet.items()) {
            String enonce = normaliseLeTexte(item.enonce());
            Set<String> propositions = new HashSet<>();
            item.propositions().forEach(proposition -> propositions.add(normaliseLeTexte(proposition)));
            for (ItemDeCp cp : itemsDeCp) {
                assertThat(enonce)
                        .as("NRC-CE1-1 : l'énoncé de '%s' est celui d'un item de CP (%s)", item.enonce(),
                                cp.fichier())
                        .isNotEqualTo(cp.enonce());
                assertThat(propositions)
                        .as("NRC-CE1-1 : les trois propositions de '%s' sont celles d'un item de CP (%s)",
                                item.enonce(), cp.fichier())
                        .isNotEqualTo(cp.propositions());
            }
        }
    }

    private static void verifierTout(Paquet paquet) {
        verifierLaDeclarationEtLesChamps(paquet);
        verifierLesPropositions(paquet);
        verifierLesEnonces(paquet);
        verifierLesIndices(paquet);
        verifierLesMotsExclus(paquet);
    }

    // ----- la clause sur les paquets servis

    @Test
    void laPopulationEstLeLotDeMatiereDeriveDesDescripteursEtElleNEstPasVide() {
        assertThat(paquetsDuLot(CONTENU_SERVI))
                .as("ADR-0051 : ce garde-fou ne juge rien s'il ne trouve aucun paquet de questionner le monde CE1")
                .isNotEmpty();
    }

    @Test
    void chaquePaquetDuLotDeclareUneLigneEtAucunChampInterdit() {
        paquetsDuLot(CONTENU_SERVI).forEach(ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest
                ::verifierLaDeclarationEtLesChamps);
    }

    @Test
    void chaquePaquetDuLotPorteTroisPropositionsDistinctesEtLaBonneReponseNEstPasToujoursLaPlusLongue() {
        paquetsDuLot(CONTENU_SERVI).forEach(ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest
                ::verifierLesPropositions);
    }

    @Test
    void chaquePaquetDuLotTientSesEnoncesEnVingtMotsEtTroisPhrases() {
        paquetsDuLot(CONTENU_SERVI).forEach(ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest
                ::verifierLesEnonces);
    }

    @Test
    void chaquePaquetDuLotPorteUnIndiceCourtQuiNeContientAucuneProposition() {
        paquetsDuLot(CONTENU_SERVI).forEach(ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest
                ::verifierLesIndices);
    }

    @Test
    void aucunPaquetDuLotNEcritUnDesQuinzeMotsExclus() {
        paquetsDuLot(CONTENU_SERVI).forEach(ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest
                ::verifierLesMotsExclus);
    }

    @Test
    void aucunItemDuLotNEstCeluiDUnItemDeCpParSonEnonceOuSesTroisPropositions() {
        List<ItemDeCp> itemsDeCp = itemsDeCp(CONTENU_SERVI);

        assertThat(itemsDeCp).as("ADR-0051 : la population de CP, derivee des descripteurs, n'est pas vide")
                .isNotEmpty();
        paquetsDuLot(CONTENU_SERVI).forEach(paquet -> verifierQueRienNEstReprisDUnItemDeCp(paquet, itemsDeCp));
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceOuTroisPropositionsRepris() {
        Paquet paquet = fautif("reprises-de-cp.yaml");
        List<ItemDeCp> itemsDeCp = itemsDeCp(CONTRE_EXEMPLE.resolve("cp"));

        assertThat(itemsDeCp).extracting(ItemDeCp::fichier).containsOnly("cp-source-nommee-nulle-part.yaml");
        assertThat(sonde(paquet, "(sonde 01)")).hasSize(1);
        assertThat(sonde(paquet, "(sonde 02)")).hasSize(1);
        assertThat(sonde(paquet, "(sonde 03)")).hasSize(1);
        assertThatThrownBy(() -> verifierQueRienNEstReprisDUnItemDeCp(
                avecItems(paquet, sonde(paquet, "(sonde 01)")), itemsDeCp))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NRC-CE1-1")
                .hasMessageContaining("énoncé");
        assertThatThrownBy(() -> verifierQueRienNEstReprisDUnItemDeCp(
                avecItems(paquet, sonde(paquet, "(sonde 02)")), itemsDeCp))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NRC-CE1-1")
                .hasMessageContaining("trois propositions");
        verifierQueRienNEstReprisDUnItemDeCp(avecItems(paquet, sonde(paquet, "(sonde 03)")), itemsDeCp);
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneFamilleDeclareeSansLeFormatOuAbsenteDeLEnTete() {
        Paquet paquet = fautif("propositions-fautives.yaml");

        assertThatThrownBy(() -> verifierLesPropositions(avecItems(paquet, sonde(paquet, "(sonde 05)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("au format");
        assertThatThrownBy(() -> verifierLesPropositions(avecItems(paquet, sonde(paquet, "(sonde 06)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("absente de l'en-tête");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupportEtSurUnTexteAVoixHaute() {
        Paquet paquet = fautif("champs-interdits.yaml");

        assertThat(sonde(paquet, "(sonde 01)")).hasSize(1);
        assertThat(sonde(paquet, "(sonde 02)")).hasSize(1);
        assertThatThrownBy(() -> verifierLaDeclarationEtLesChamps(avecItems(paquet, sonde(paquet, "(sonde 01)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1")
                .hasMessageContaining("texteSupport");
        assertThatThrownBy(() -> verifierLaDeclarationEtLesChamps(avecItems(paquet, sonde(paquet, "(sonde 02)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1")
                .hasMessageContaining("texteAVoixHaute");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPaquetQuiDeclareDeuxLignes() {
        assertThatThrownBy(() -> verifierLaDeclarationEtLesChamps(fautif("deux-lignes.yaml")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1")
                .hasMessageContaining("exactement une ligne");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPaquetOuLaBonneReponseEstPresqueToujoursLaPlusLongue() {
        assertThatThrownBy(() -> verifierLesPropositions(fautif("bonne-reponse-la-plus-longue.yaml")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("plus de la moitié");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesPropositionsQuiNeSontPasTroisEtDistinctes() {
        Paquet paquet = fautif("propositions-fautives.yaml");

        assertThatThrownBy(() -> verifierLesPropositions(avecItems(paquet, sonde(paquet, "(sonde 01)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("pas trois propositions distinctes");
        assertThatThrownBy(() -> verifierLesPropositions(avecItems(paquet, sonde(paquet, "(sonde 02)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne nomme pas les familles");
        assertThatThrownBy(() -> verifierLesPropositions(avecItems(paquet, sonde(paquet, "(sonde 03)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne porte pas trois propositions");
        assertThatThrownBy(() -> verifierLesPropositions(avecItems(paquet, sonde(paquet, "(sonde 04)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeVingtEtUnMotsEtSurUnEnonceDeQuatrePhrases() {
        Paquet paquet = fautif("enonces-trop-longs.yaml");

        assertThatThrownBy(() -> verifierLesEnonces(avecItems(paquet, sonde(paquet, "(sonde 01)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1");
        assertThatThrownBy(() -> verifierLesEnonces(avecItems(paquet, sonde(paquet, "(sonde 02)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-2");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeTreizeMotsLaisseDouzeMotsPasserEtRefuseUnIndiceRepeteSurTout() {
        Paquet paquet = fautif("indices-fautifs.yaml");

        assertThatThrownBy(() -> verifierLesIndices(avecItems(paquet, sonde(paquet, "(sonde 01)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("plus de douze mots");
        assertThatThrownBy(() -> verifierLesIndices(avecItems(paquet, sonde(paquet, "(sonde 02)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("même indice");
        assertThatThrownBy(() -> verifierLesIndices(avecItems(paquet, sonde(paquet, "(sonde 03)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition");
        verifierLesIndices(avecItems(paquet, sonde(paquet, "(sonde 04)")));
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurChacunDesQuinzeMotsExclusDansUnChampDifferent() {
        Paquet paquet = fautif("mots-exclus.yaml");

        assertThat(paquet.items()).as("un item fautif par mot exclu").hasSameSizeAs(MOTS_EXCLUS);
        for (int rang = 0; rang < paquet.items().size(); rang++) {
            Item item = paquet.items().get(rang);
            assertThatThrownBy(() -> verifierLesMotsExclus(avecItems(paquet, List.of(item))))
                    .as("le mot exclu de l'item %d (%s) doit être refusé", rang + 1, item.enonce())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("QMT-CE1-1");
        }
    }

    @Test
    void leJeuFautifCommiteLaisseUnMotAdmisPasserEtMordSurLeSeulMotExclu() {
        Paquet paquet = fautif("mot-admis.yaml");

        verifierLesMotsExclus(avecItems(paquet, sonde(paquet, "(sonde 01)")));
        assertThatThrownBy(() -> verifierLesMotsExclus(avecItems(paquet, sonde(paquet, "(sonde 02)"))))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("evaporation");
    }

    @Test
    void unSecondFichierDuLotEstJugeCommeLePremierEtUnAutreLotNEstPasJuge() {
        List<Paquet> lot = paquetsDuLot(CONTRE_EXEMPLE.resolve("depot"));

        assertThat(lot).extracting(Paquet::fichier)
                .containsExactly("lot-conforme.yaml", "lot-fautif.yaml");
        verifierTout(lot.get(0));
        assertThatThrownBy(() -> verifierTout(lot.get(1)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1");
    }

    /** Le meme paquet, restreint a ces items : une sonde est jugee seule, sans les autres items du jeu fautif. */
    private static Paquet avecItems(Paquet paquet, List<Item> items) {
        return new Paquet(paquet.fichier(), paquet.matiere(), paquet.niveau(), paquet.lignes(),
                paquet.entete(), items);
    }
}
