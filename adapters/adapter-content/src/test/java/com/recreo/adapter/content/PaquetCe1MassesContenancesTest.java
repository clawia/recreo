package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-MAT-CE1-10 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-masses-contenances}, ligne {@code MAT-CE1-10} - comparer et mesurer des masses
 * et des contenances, plafond {@code en cours}. Arbitrage prealable de s43
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md}, section 3.4,
 * conditions {@code MAS-CE1-1} a {@code MAS-CE1-6}) ; en mathematiques CE1 ne valent ni
 * {@code LON-CE1-3} a {@code -5} ni {@code TRV-CE1-1} a {@code -7}.
 *
 * <p>Un item est range par ce que son enonce dit : "quelle unite" est un choix d'unite,
 * "combien de" une conversion, tout le reste une comparaison de trois objets. Les valeurs, les
 * unites et les rangs sont recomptes sur les champs lus par le chargeur, jamais sur les
 * commentaires du fichier ; la bonne reponse d'une comparaison est recalculee sur les trois valeurs
 * de l'enonce.
 *
 * <p>Ce que ce test ne lit pas : que "mesurer" ne soit servi par aucun item (un test voit un champ,
 * pas un verbe de la ligne), la plausibilite d'une masse ou d'une contenance pour un objet connu
 * d'un enfant de CE1, qu'un indice n'enseigne pas, par un tour qu'un mot ne decele pas, que la
 * taille donne la masse, que les distracteurs soient plausibles, qu'une comparaison ne se resolve pas
 * par la taille connue des objets (reserve de nadia-prof du 2026-10-22). Les huit items de choix d'unite sont hors du rang de la bonne reponse, que
 * l'en-tete du paquet compte sur les treize items de comparaison et de conversion. Cela reste a la
 * lecture et au jeu de nadia-prof.
 */
class PaquetCe1MassesContenancesTest {

    private static final String PAQUET = "ce1-masses-contenances";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1MassesContenancesTest";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 21;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;

    private static final Pattern CHIFFRES = Pattern.compile("\\d+");

    /** Une valeur chiffree avec son unite, en majuscule ou minuscule : la casse est jugee a part. */
    private static final Pattern VALEUR = Pattern.compile("(\\d+) (kg|g|L|l)(?![\\p{L}])");

    /** Un nombre suivi d'un mot : ce mot est une unite, et MAS-CE1-2 en liste trois. */
    private static final Pattern NOMBRE_ET_UNITE = Pattern.compile("(\\d+) ?(\\p{L}+)");

    private static final Set<String> UNITES_ADMISES = Set.of("g", "kg", "L");

    /** MAS-CE1-2 : ni milligramme, ni tonne, ni centilitre, ni millilitre. */
    private static final Pattern UNITE_HORS_DE_LA_LIGNE = Pattern.compile(
            "milligramme|tonne|centilitre|millilitre|décilitre|hectolitre"
                    + "|(?<![\\p{L}'])(mg|cg|dg|hg|cl|dl|ml|hl)(?![\\p{L}'])", FLAGS);

    /** MAS-CE1-6 : un mot de taille dans un indice. */
    private static final Pattern MOT_DE_TAILLE = Pattern.compile("taille|gros|grand|volumineu|encombr|petit",
            FLAGS);

    private static final Set<String> NOMS_D_UNITE = Set.of("le gramme", "le kilogramme", "le litre");

    private static final Pattern PLUS_HAUT = Pattern.compile("plus lourd|contient le plus", FLAGS);

    private static final Pattern PLUS_BAS = Pattern.compile("plus léger|contient le moins", FLAGS);

    private static final Pattern DU_MILIEU = Pattern.compile("du milieu", FLAGS);

    private static final Pattern CONVERSION_CHIFFREE = Pattern.compile("^(\\d+) (kg|g), combien de (kg|g)");

    private static final String DEMI_KILOGRAMME = "Un demi-kilogramme, combien de g";

    private enum Grandeur { MASSE, CONTENANCE }

    private enum Rang { BAS, MILIEU, HAUT }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1MassesContenancesTest.class.getResourceAsStream(chemin)) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<Question> tousLesItems(ContentSource source) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = source.next(hasard, dejaVus).orElse(null); item != null;
                item = source.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static List<Question> items() {
        return tousLesItems(new YamlContentPackLoader().chargerPaquet(PAQUET));
    }

    private static List<Question> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-masses-fautif"));
    }

    /** Les items fautifs dont l'enonce commence par l'un de ces debuts, pour mordre un verificateur a la fois. */
    private static List<Question> fautif(String... debutsDEnonce) {
        return itemsFautifs().stream()
                .filter(item -> List.of(debutsDEnonce).stream().anyMatch(item.enonce()::startsWith))
                .toList();
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static List<String> champsLus(Question item) {
        List<String> champs = new ArrayList<>(propositions(item));
        champs.add(item.enonce());
        item.indice().ifPresent(champs::add);
        return champs;
    }

    private static boolean estUnChoixDUnite(Question item) {
        return item.enonce().contains("quelle unité");
    }

    private static boolean estUneConversion(Question item) {
        return item.enonce().contains("combien de");
    }

    private static boolean estUneComparaison(Question item) {
        return !estUnChoixDUnite(item) && !estUneConversion(item);
    }

    private static List<Question> comparaisons(List<Question> items) {
        return items.stream().filter(PaquetCe1MassesContenancesTest::estUneComparaison).toList();
    }

    private static List<Question> conversions(List<Question> items) {
        return items.stream().filter(PaquetCe1MassesContenancesTest::estUneConversion).toList();
    }

    /** Les valeurs chiffrees de l'enonce, dans l'ordre : {@code [nombre, unite]}. */
    private static List<String[]> valeursDe(Question item) {
        List<String[]> valeurs = new ArrayList<>();
        Matcher trouve = VALEUR.matcher(item.enonce());
        while (trouve.find()) {
            valeurs.add(new String[] {trouve.group(1), trouve.group(2)});
        }
        return valeurs;
    }

    /** Le nom d'un objet proposé, sans son article : "le chat" donne "chat", "l'ananas" donne "ananas". */
    private static String nomDe(String proposition) {
        return proposition.replaceFirst("^(le |la |les |l['’])", "");
    }

    /** La valeur que l'enonce donne a un objet, ou -1 : {@code <nom> [pese|contient] <nombre> <unite>}. */
    private static long valeurDe(Question item, String proposition) {
        Matcher trouve = Pattern.compile(
                Pattern.quote(nomDe(proposition)) + "(?: pèse| contient)? (\\d+) (?:kg|g|L|l)(?![\\p{L}])",
                FLAGS).matcher(item.enonce());
        return trouve.find() ? Long.parseLong(trouve.group(1)) : -1L;
    }

    private static Grandeur grandeurDe(Question item) {
        if (estUnChoixDUnite(item)) {
            return item.bonneReponse().equals("le litre") ? Grandeur.CONTENANCE : Grandeur.MASSE;
        }
        if (estUneConversion(item)) {
            return Grandeur.MASSE;
        }
        List<String[]> valeurs = valeursDe(item);
        return !valeurs.isEmpty() && valeurs.get(0)[1].equalsIgnoreCase("L")
                ? Grandeur.CONTENANCE : Grandeur.MASSE;
    }

    /** MAS-CE1-1 : les masses et les contenances sont chacune servies par au moins un tiers des items. */
    private static void verifierLesDeuxGrandeurs(List<Question> items) {
        for (Grandeur grandeur : Grandeur.values()) {
            long servis = items.stream().filter(item -> grandeurDe(item) == grandeur).count();
            assertThat(3 * servis)
                    .as("MAS-CE1-1 : au moins un tiers des %d items sert %s (%d servis)",
                            items.size(), grandeur, servis)
                    .isGreaterThanOrEqualTo(items.size());
        }
    }

    /** MAS-CE1-2 : seuls g, kg et L, le litre en majuscule, aucune unite voisine. */
    private static void verifierLesUnites(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                Matcher trouve = NOMBRE_ET_UNITE.matcher(champ);
                while (trouve.find()) {
                    assertThat(UNITES_ADMISES)
                            .as("MAS-CE1-2 : '%s' écrit l'unité « %s » (seuls g, kg et L, le litre en "
                                    + "majuscule)", item.enonce(), trouve.group(2))
                            .contains(trouve.group(2));
                }
                assertThat(UNITE_HORS_DE_LA_LIGNE.matcher(champ).find())
                        .as("MAS-CE1-2 : '%s' nomme une unité que la ligne ne nomme pas (« %s »)",
                                item.enonce(), champ)
                        .isFalse();
            }
        }
    }

    /** MAS-CE1-3 : chaque comparaison donne trois valeurs chiffrees, une par proposition, et la bonne l'est. */
    private static void verifierLesComparaisons(List<Question> items) {
        for (Question item : comparaisons(items)) {
            assertThat(valeursDe(item))
                    .as("MAS-CE1-3 : '%s' donne trois valeurs chiffrées avec leur unité", item.enonce())
                    .hasSize(3);
            List<Long> valeurs = new ArrayList<>();
            for (String proposition : propositions(item)) {
                long valeur = valeurDe(item, proposition);
                assertThat(valeur)
                        .as("MAS-CE1-3 : '%s' ne donne pas la valeur de « %s »", item.enonce(), proposition)
                        .isGreaterThanOrEqualTo(0L);
                valeurs.add(valeur);
            }
            assertThat(new HashSet<>(valeurs))
                    .as("MAS-CE1-3 : '%s' donne trois valeurs distinctes", item.enonce()).hasSize(3);
            long attendue = valeurAttendue(item, valeurs);
            assertThat(valeurDe(item, item.bonneReponse()))
                    .as("MAS-CE1-3 : '%s' attend la valeur %d", item.enonce(), attendue)
                    .isEqualTo(attendue);
        }
    }

    private static long valeurAttendue(Question item, List<Long> valeurs) {
        List<Long> triees = valeurs.stream().sorted().toList();
        if (PLUS_HAUT.matcher(item.enonce()).find()) {
            return triees.get(2);
        }
        if (PLUS_BAS.matcher(item.enonce()).find()) {
            return triees.get(0);
        }
        assertThat(DU_MILIEU.matcher(item.enonce()).find())
                .as("MAS-CE1-3 : '%s' ne dit ni le plus, ni le moins, ni le milieu", item.enonce())
                .isTrue();
        return triees.get(1);
    }

    /** MAS-CE1-4 : aucun nombre au-dessus de 1000, dans l'enonce comme dans les propositions. */
    private static void verifierLaBorne(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                Matcher trouve = CHIFFRES.matcher(champ);
                while (trouve.find()) {
                    long valeur = Long.parseLong(trouve.group());
                    assertThat(valeur)
                            .as("MAS-CE1-4 : '%s' porte %d, au-dessus de 1000", item.enonce(), valeur)
                            .isLessThanOrEqualTo(1000L);
                }
            }
        }
    }

    /** MAS-CE1-4 : les seules conversions sont 1 kg = 1000 g et 500 g = un demi-kilogramme. */
    private static void verifierLesConversions(List<Question> items) {
        for (Question item : conversions(items)) {
            Matcher chiffree = CONVERSION_CHIFFREE.matcher(item.enonce());
            String attendue;
            if (chiffree.find()) {
                String ecrit = chiffree.group(1) + " " + chiffree.group(2);
                attendue = switch (ecrit + " -> " + chiffree.group(3)) {
                    case "1 kg -> g" -> "1000 g";
                    case "1000 g -> kg" -> "1 kg";
                    default -> null;
                };
                assertThat(attendue)
                        .as("MAS-CE1-4 : '%s' est une conversion que la ligne n'admet pas (seules "
                                + "1 kg = 1000 g et 500 g = un demi-kilogramme)", item.enonce())
                        .isNotNull();
            } else {
                assertThat(item.enonce())
                        .as("MAS-CE1-4 : '%s' est une conversion que la ligne n'admet pas (seules "
                                + "1 kg = 1000 g et 500 g = un demi-kilogramme)", item.enonce())
                        .startsWith(DEMI_KILOGRAMME);
                attendue = "500 g";
            }
            assertThat(item.bonneReponse())
                    .as("MAS-CE1-4 : '%s' attend %s", item.enonce(), attendue)
                    .isEqualTo(attendue);
        }
    }

    /** MAS-CE1-5 : trois noms d'unite sans nombre, trois objets dans une unite, trois valeurs dans une unite. */
    private static void verifierLaMemeUniteParItem(List<Question> items) {
        for (Question item : items) {
            if (estUnChoixDUnite(item)) {
                for (String proposition : propositions(item)) {
                    assertThat(NOMS_D_UNITE)
                            .as("MAS-CE1-5 : « %s » (proposition de '%s') n'est pas un nom d'unité sans "
                                    + "nombre", proposition, item.enonce())
                            .contains(proposition);
                }
            } else if (estUneConversion(item)) {
                Set<String> unites = new HashSet<>();
                for (String proposition : propositions(item)) {
                    Matcher valeur = VALEUR.matcher(proposition);
                    assertThat(valeur.find())
                            .as("MAS-CE1-5 : « %s » (proposition de '%s') ne porte aucune valeur",
                                    proposition, item.enonce())
                            .isTrue();
                    unites.add(valeur.group(2));
                }
                assertThat(unites)
                        .as("MAS-CE1-5 : les propositions de '%s' n'ont pas la même unité", item.enonce())
                        .hasSize(1);
            } else {
                Set<String> unites = new HashSet<>();
                valeursDe(item).forEach(valeur -> unites.add(valeur[1]));
                assertThat(unites)
                        .as("MAS-CE1-5 : les trois valeurs de '%s' n'ont pas la même unité", item.enonce())
                        .hasSizeLessThanOrEqualTo(1);
                for (String proposition : propositions(item)) {
                    assertThat(CHIFFRES.matcher(proposition).find())
                            .as("MAS-CE1-5 : « %s » (proposition de '%s') porte un nombre, ou l'item "
                                    + "demande un objet", proposition, item.enonce())
                            .isFalse();
                }
            }
        }
    }

    /**
     * MAS-CE1-6 : les distracteurs d'une conversion sont une erreur de facteur 10 ou 100 ; pour le
     * demi-kilogramme, ou le double (le demi oublie, 1000 g).
     */
    private static void verifierLesErreursDeFacteur(List<Question> items) {
        for (Question item : conversions(items)) {
            boolean demi = !CONVERSION_CHIFFREE.matcher(item.enonce()).find();
            long bonne = nombreEnTete(item.bonneReponse());
            for (String distracteur : item.distracteurs()) {
                long ecrit = nombreEnTete(distracteur);
                boolean facteur = ecrit == bonne * 10 || ecrit == bonne * 100
                        || (ecrit * 10 == bonne) || (ecrit * 100 == bonne)
                        || (demi && ecrit == 2 * bonne);
                assertThat(facteur)
                        .as("MAS-CE1-6 : « %s » (distracteur de '%s') n'est pas une erreur de facteur "
                                + "10 ou 100 sur %d", distracteur, item.enonce(), bonne)
                        .isTrue();
            }
        }
    }

    private static long nombreEnTete(String proposition) {
        Matcher nombre = CHIFFRES.matcher(proposition);
        assertThat(nombre.find()).as("« %s » porte un nombre", proposition).isTrue();
        return Long.parseLong(nombre.group());
    }

    /** MAS-CE1-6 : un indice qui nomme la taille ne parle que de nombres, et ils ne sont pas tous identiques. */
    private static void verifierLesIndices(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElse("");
            if (MOT_DE_TAILLE.matcher(indice).find()) {
                assertThat(indice)
                        .as("MAS-CE1-6 : l'indice « %s » de '%s' nomme la taille sans parler de nombre",
                                indice, item.enonce())
                        .containsIgnoringCase("nombre");
            }
        }
        assertThat(items.stream().map(item -> item.indice().orElse("")).distinct().count())
                .as("MAS-CE1-6 : les indices ne sont pas identiques sur tout le paquet")
                .isGreaterThan(1L);
    }

    private static Rang rangDe(Question item) {
        List<Long> valeurs = new ArrayList<>();
        long bonne;
        if (estUneConversion(item)) {
            propositions(item).forEach(proposition -> valeurs.add(nombreEnTete(proposition)));
            bonne = nombreEnTete(item.bonneReponse());
        } else {
            propositions(item).forEach(proposition -> valeurs.add(valeurDe(item, proposition)));
            bonne = valeurDe(item, item.bonneReponse());
        }
        List<Long> triees = valeurs.stream().sorted().toList();
        int position = triees.indexOf(bonne);
        return position == 0 ? Rang.BAS : position == 2 ? Rang.HAUT : Rang.MILIEU;
    }

    /** MAS-CE1-6 : chacun des trois rangs de la bonne reponse porte de 10 % a 50 % des comparaisons et conversions. */
    private static void verifierLesTroisRangs(List<Question> items) {
        List<Question> mesures = new ArrayList<>(comparaisons(items));
        mesures.addAll(conversions(items));
        for (Rang rang : Rang.values()) {
            long portes = mesures.stream().filter(item -> rangDe(item) == rang).count();
            assertThat(10 * portes)
                    .as("MAS-CE1-6 : le rang %s porte au moins 10 %% des %d items (%d portés)",
                            rang, mesures.size(), portes)
                    .isGreaterThanOrEqualTo(mesures.size());
            assertThat(2 * portes)
                    .as("MAS-CE1-6 : le rang %s porte au plus 50 %% des %d items (%d portés)",
                            rang, mesures.size(), portes)
                    .isLessThanOrEqualTo(mesures.size());
        }
    }

    /** LON-CE1-1 et LON-CE1-2 : vingt mots au plus, trois phrases au plus, la derniere est la question. */
    private static void verifierLaLongueurDesEnonces(List<Question> items) {
        for (Question item : items) {
            long mots = List.of(item.enonce().split("[\\s\\u00A0]+")).stream()
                    .filter(mot -> mot.matches(".*[\\p{L}\\d].*")).count();
            assertThat(mots)
                    .as("LON-CE1-1 : '%s' porte %d mots, plus de vingt", item.enonce(), mots)
                    .isLessThanOrEqualTo(20L);
            long phrases = item.enonce().chars().filter(c -> c == '.' || c == '?' || c == '!').count();
            assertThat(phrases)
                    .as("LON-CE1-2 : '%s' porte %d phrases, plus de trois", item.enonce(), phrases)
                    .isLessThanOrEqualTo(3L);
            assertThat(item.enonce())
                    .as("LON-CE1-2 : la derniere phrase de '%s' est la question", item.enonce())
                    .endsWith("?");
        }
    }

    /** TYP-1 (ADR-0067) : l'espace avant ?, :, ; ou ! est insecable, dans tout ce que l'enfant lit. */
    private static void verifierLEspaceInsecable(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                assertThat(champ)
                        .as("TYP-1 : '%s' porte une espace ordinaire devant un signe double (« %s »)",
                                item.enonce(), champ)
                        .doesNotContainPattern("[^\\u00A0] [?:;!]");
            }
        }
    }

    /** Aucun texte support, aucune voix : seuls ces quatre champs sont servis. */
    private static void verifierLesChampsServis(String contenu) {
        Map<?, ?> corps = new Yaml().load(contenu);
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("NB8 : aucun texteSupport ni texteAVoixHaute, seuls enonce, bonneReponse, "
                            + "distracteurs et indice")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    /**
     * L'entree du catalogue qui sert le paquet est unique, de la mecanique bandit-manchot, a dix
     * manches (DEC-0121) ; son titre ne porte pas le niveau (ADR-0078) et l'espace avant un signe
     * double est insecable (ADR-0067).
     */
    private static void verifierLEntreeDuCatalogue(String catalogue) {
        Map<?, ?> corps = new Yaml().load(catalogue);
        List<Map<?, ?>> entrees = new ArrayList<>();
        for (Object entree : (List<?>) corps.get("jeux")) {
            Map<?, ?> jeu = (Map<?, ?>) entree;
            if (PAQUET.equals(jeu.get("paquet"))) {
                entrees.add(jeu);
            }
        }
        assertThat(entrees).as("une seule entrée du catalogue sert le paquet %s", PAQUET).hasSize(1);
        Map<?, ?> entree = entrees.get(0);
        String titre = String.valueOf(entree.get("titre"));
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(entree.get("mecanique")).as("la mécanique est bandit-manchot")
                .isEqualTo("bandit-manchot");
        assertThat(entree.get("nombreDeManches")).as("DEC-0121 : nombreDeManches vaut 10")
                .isEqualTo(10);
        assertThat(titre).as("le titre ne porte pas le niveau (ADR-0078)")
                .doesNotContainPattern("(?i)\\b(CP|CE1|CE2|CM1|CM2)\\b");
        assertThat(titre + " " + consigne)
                .as("l'espace avant ?, :, ; ou ! d'un titre ou d'une consigne est insécable (U+00A0)")
                .doesNotContainPattern("[^\\u00A0] [?:;!]");
    }

    // ----- le paquet et son entree de catalogue

    @Test
    void adr0081CePaquetDeclareMatCe110EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-10"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
        assertThat(descripteur.get("matiere")).isEqualTo("Mathématiques");
    }

    @Test
    void lIdentifiantDuPaquetEstLeNomDeSonFichier() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("id")).as("le chargeur ne compare pas le champ id au nom de fichier")
                .isEqualTo(PAQUET);
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueATitreEtConsigneSansFaute() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    @Test
    void nb1LePaquetGardeSesVingtEtUnItemsChacunATroisPropositionsEtPlusDeDixManches() {
        List<Question> items = items();
        assertThat(items).hasSize(NOMBRE_D_ITEMS_ATTENDU);
        assertThat(items.size()).as("FER-2 : strictement plus d'items que les dix manches")
                .isGreaterThan(10);
        for (Question item : items) {
            assertThat(item.distracteurs()).as("deux distracteurs pour '%s'", item.enonce()).hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("trois propositions distinctes pour '%s'", item.enonce()).hasSize(3);
        }
    }

    @Test
    void nb8AucunItemNePorteDeTexteSupportNiDeVoix() {
        verifierLesChampsServis(lire(FICHIER_CONTENU));
    }

    // ----- les conditions MAS-CE1-1 a MAS-CE1-6

    @Test
    void mas1LesMassesEtLesContenancesSontChacuneServiesParUnTiersDesItems() {
        verifierLesDeuxGrandeurs(items());
    }

    @Test
    void mas2SeulsLeGrammeLeKilogrammeEtLeLitreEnMajusculeSontEcrits() {
        verifierLesUnites(items());
    }

    @Test
    void mas3ChaqueComparaisonDonneTroisValeursChiffreesEtSaBonneReponseEstLaBonne() {
        verifierLesComparaisons(items());
    }

    @Test
    void mas4AucunNombreNeDepasseMille() {
        verifierLaBorne(items());
    }

    @Test
    void mas4LesSeulesConversionsSontLeKilogrammeEnGrammesEtLeDemiKilogramme() {
        verifierLesConversions(items());
    }

    @Test
    void mas5LesTroisPropositionsDUnItemPortentLaMemeUniteOuAucune() {
        verifierLaMemeUniteParItem(items());
    }

    @Test
    void mas6LesDistracteursDeConversionSontDesErreursDeFacteur() {
        verifierLesErreursDeFacteur(items());
    }

    @Test
    void mas6AucunIndiceNeNommeLaTailleSansParlerDeNombreEtLesIndicesNeSontPasTousIdentiques() {
        verifierLesIndices(items());
    }

    @Test
    void lon1LonEtLon2LesEnoncesTiennentVingtMotsEtTroisPhrases() {
        verifierLaLongueurDesEnonces(items());
    }

    @Test
    void typ1LEspaceAvantUnSigneDoubleEstInsecableDansTousLesChampsLus() {
        verifierLEspaceInsecable(items());
    }

    @Test
    void mas6ChacunDesTroisRangsDeLaBonneReponsePorteDixACinquantePourCent() {
        verifierLesTroisRangs(items());
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurUnLitreEcritEnMinuscule() {
        List<Question> items = fautif("Un seau contient 10 l");

        assertThatThrownBy(() -> verifierLesUnites(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-2")
                .hasMessageContaining("« l »");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneUniteQueLaLigneNeNommePas() {
        List<Question> items = fautif("Pour peser une voiture");

        assertThatThrownBy(() -> verifierLesUnites(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-2")
                .hasMessageContaining("tonne");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnNombreAuDessusDeMille() {
        List<Question> items = fautif("Un sac pèse 1500 g");

        assertThatThrownBy(() -> verifierLaBorne(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-4")
                .hasMessageContaining("1500");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneConversionQueLaLigneNAdmetPas() {
        List<Question> items = fautif("2 kg, combien");

        assertThatThrownBy(() -> verifierLesConversions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-4")
                .hasMessageContaining("n'admet pas");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneComparaisonParLaSeuleNature() {
        List<Question> items = fautif("Une plume ou une brique");

        assertThatThrownBy(() -> verifierLesComparaisons(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-3")
                .hasMessageContaining("trois valeurs chiffrées");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseQuiNEstPasLaPlusLourde() {
        List<Question> items = fautif("Une pomme pèse 150 g");

        assertThatThrownBy(() -> verifierLesComparaisons(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-3")
                .hasMessageContaining("attend la valeur 180");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionSansValeurDansLEnonce() {
        List<Question> items = fautif("Un chou pèse 2 kg");

        assertThatThrownBy(() -> verifierLesComparaisons(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-3")
                .hasMessageContaining("la courge");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesValeursDeDeuxUnitesDifferentes() {
        List<Question> items = fautif("Un chat pèse 4 kg");

        assertThatThrownBy(() -> verifierLaMemeUniteParItem(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-5")
                .hasMessageContaining("même unité");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnChoixDUniteQuiPorteUnNombre() {
        List<Question> items = fautif("Pour peser une pomme");

        assertThatThrownBy(() -> verifierLaMemeUniteParItem(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-5")
                .hasMessageContaining("10 L");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneConversionDontUnePropositionChangeDUnite() {
        List<Question> items = fautif("1 kg, combien");

        assertThatThrownBy(() -> verifierLaMemeUniteParItem(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-5")
                .hasMessageContaining("même unité");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurQuiNEstPasUneErreurDeFacteur() {
        List<Question> items = fautif("1000 g, combien");

        assertThatThrownBy(() -> verifierLesErreursDeFacteur(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-6")
                .hasMessageContaining("3 kg");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurDuDemiKilogrammeQuiNEstPasUneErreurDeFacteur() {
        List<Question> items = fautif("Un demi-kilogramme");

        assertThatThrownBy(() -> verifierLesErreursDeFacteur(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-6")
                .hasMessageContaining("100 g");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceDeTropDeMots() {
        List<Question> items = fautif("Un sac de pommes");

        assertThatThrownBy(() -> verifierLaLongueurDesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceQuiNeFinitPasParLaQuestion() {
        List<Question> items = fautif("Un panier pèse 3 kg");

        assertThatThrownBy(() -> verifierLaLongueurDesEnonces(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-2");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEspaceOrdinaireDevantLePointDInterrogation() {
        List<Question> items = fautif("Pour peser un ours");

        assertThatThrownBy(() -> verifierLEspaceInsecable(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TYP-1");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiFaitDeduireLaMasseDeLaTaille() {
        List<Question> items = fautif("Une carotte pèse 80 g");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-6")
                .hasMessageContaining("Le plus gros est le plus lourd");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesIndicesTousIdentiques() {
        List<Question> items = fautif("Un renard", "Un tigre", "Un cerf");

        assertThatThrownBy(() -> verifierLesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-6")
                .hasMessageContaining("identiques");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTroisBonnesReponsesAuMemeRang() {
        List<Question> items = fautif("Un renard", "Un tigre", "Un cerf");

        assertThatThrownBy(() -> verifierLesTroisRangs(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-6")
                .hasMessageContaining("rang BAS");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPaquetSansContenance() {
        List<Question> items = fautif("Une pomme pèse 150 g");

        assertThatThrownBy(() -> verifierLesDeuxGrandeurs(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MAS-CE1-1")
                .hasMessageContaining("CONTENANCE");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-masses-fautif.yaml");

        assertThatThrownBy(() -> verifierLesChampsServis(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NB8");
    }

    @Test
    void leCatalogueFautifDontLeTitrePorteLeNiveauEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-titre-avec-niveau.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("niveau");
    }

    @Test
    void leCatalogueFautifDontLEspaceDevantUnSigneDoubleEstOrdinaireEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-espace-ordinaire.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("insécable");
    }

    @Test
    void leCatalogueFautifQuiSertLePaquetDeuxFoisEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-deux-entrees.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une seule entrée");
    }

    @Test
    void leCatalogueFautifQuiNePorteQueCinqManchesEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-cinq-manches.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nombreDeManches");
    }
}
