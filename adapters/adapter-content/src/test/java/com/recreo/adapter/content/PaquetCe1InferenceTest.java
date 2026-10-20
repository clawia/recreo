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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-06 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-inference}, ligne {@code FRA-CE1-06} - faire une inference simple a partir d'un
 * texte court. Arbitrage prealable de s48 ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md},
 * section 3.6, conditions {@code INF-CE1-1} a {@code INF-CE1-3} et sept conditions transverses
 * {@code TRV-CE1-1} a {@code TRV-CE1-7}), arbitrage de s47 sur la longueur du texte support
 * ({@code LON-CE1-3} et {@code LON-CE1-4}) et arbitrage de preparation du palier 3 de s57
 * ({@code arbitrage-nadia-preparation-palier-3-ce1.md}, section 4.1 et 4.2).
 *
 * <p>Le comptage de {@code LON-CE1-3} et {@code LON-CE1-4} suit la convention de s47, reprise
 * d'{@code ADR-0089} et de {@code PaquetCe1TexteNarratifTest} : un mot contient au moins une lettre
 * ou un chiffre, une forme elidee compte pour un mot, une fin de phrase est un {@code .}, un
 * {@code ?} ou un {@code !} qui termine le texte ou qu'une majuscule suit. Le perimetre est le
 * champ {@code texteSupport}. Les trois sortes d'{@code INF-CE1-1} sont recomptees sur les champs
 * {@code enonce} lus par le chargeur, jamais sur les commentaires de l'en-tete du fichier.
 *
 * <p>Ce que ce test ne lit pas : que la reponse relie au plus deux faits du texte
 * ({@code INF-CE1-1}), les familles de distracteurs et le choix des sentiments
 * ({@code INF-CE1-3}), qu'une bonne reponse faite de mots du texte non contigus ne soit pas une
 * citation ({@code INF-CE1-2} ne garde que la sous-chaine entiere), l'exactitude de chaque reponse,
 * qu'aucun role ni aucune emotion ne soit distribue par genre ({@code TRV-CE1-6} ne garde que
 * l'equilibre de la table de prenoms), qu'une regle fausse ne soit pas enseignee
 * ({@code TRV-CE1-5}). Points d'audit de nadia-prof (arbitrage de preparation, 4.1, point 3), non
 * gardes ici : l'intrus du premier mot et le fait qu'il devienne regulier sur une question, le mot
 * cite entre chevrons (aucun enonce de ce paquet ne cite un mot), l'indice qui dit ou regarder
 * (la forme {@code Relis} ou {@code Cherche} n'est pas imposee a ce paquet). Le pli a 744 x 551
 * n'est pas mesurable par un test du serveur.
 */
class PaquetCe1InferenceTest {

    private static final String PAQUET = "ce1-inference";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1InferenceTest";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** LON-CE1-3 et LON-CE1-4 : bornes de l'arbitrage de s47, section 2. */
    private static final int LON_3_MOTS_DU_TEXTE = 30;

    private static final int LON_4_PHRASES = 4;

    private static final int LON_4_MOTS_PAR_PHRASE = 12;

    /** TRV-CE1-5 : un indice de douze mots au plus. */
    private static final int TRV_5_MOTS_DE_L_INDICE = 12;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    /** Fin de phrase de la convention de s47 : un signe de fin qu'une majuscule suit. */
    private static final Pattern FIN_DE_PHRASE =
            Pattern.compile("(?<=[.?!])" + ESPACES + "+(?=\\p{Lu})");

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final Pattern MOT_DE_LETTRES = Pattern.compile("\\p{L}+");

    /**
     * TRV-CE1-6 : le genre de chaque prenom des textes, declaration d'auteur faute de lexique de
     * prenoms dans ce depot. Ce que la clause prouve est l'equilibre de cette table, jamais qu'elle
     * dit vrai.
     */
    private static final Map<String, String> GENRE_DES_PRENOMS = Map.ofEntries(
            Map.entry("Emma", "f"), Map.entry("Eva", "f"), Map.entry("Inès", "f"),
            Map.entry("Lina", "f"), Map.entry("Louna", "f"), Map.entry("Mila", "f"),
            Map.entry("Nina", "f"), Map.entry("Nour", "f"), Map.entry("Sara", "f"),
            Map.entry("Sofia", "f"), Map.entry("Zoé", "f"),
            Map.entry("Adrien", "m"), Map.entry("Hugo", "m"), Map.entry("Ilan", "m"),
            Map.entry("Karim", "m"), Map.entry("Malo", "m"), Map.entry("Nolan", "m"),
            Map.entry("Paul", "m"), Map.entry("Sami", "m"), Map.entry("Samir", "m"),
            Map.entry("Tidiane", "m"), Map.entry("Théo", "m"));

    /** Mots qui ouvrent une phrase d'un texte sans etre des prenoms ("C" et "L" sont des elisions). */
    private static final Set<String> OUVERTURES = Set.of(
            "C", "À", "Après", "Ce", "Chaque", "Des", "Elle", "Il", "Ils", "L", "La", "Le", "Les", "On",
            "Son", "Tout", "Un", "Une");

    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    private static final Pattern VERIFICATEUR_TRV_CE1_4 =
            Pattern.compile("\\b(" + String.join("|", LEXIQUE_TRV_CE1_4) + ")\\b", FLAGS);

    /** TRV-CE1-5 : un indice ne renvoie ni a l'oreille ni a la lecture a voix haute. */
    private static final Pattern RENVOI_A_L_OREILLE = Pattern.compile(
            "oreille|entends|écoute|dans ta tête|à voix haute|prononce", FLAGS);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1InferenceTest.class.getResourceAsStream(chemin)) {
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<String> mots(String texte) {
        List<String> mots = new ArrayList<>();
        for (String token : texte.strip().split(ESPACES + "+")) {
            if (LETTRE_OU_CHIFFRE.matcher(token).find()) {
                mots.add(token);
            }
        }
        return mots;
    }

    private static List<String> phrases(String texte) {
        return List.of(FIN_DE_PHRASE.split(texte.strip()));
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

    private static List<Question> itemsFautifs(String paquet) {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH).chargerPaquet(paquet));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static String texte(Question item) {
        return item.texteSupport().orElseThrow(
                () -> new AssertionError("'" + item.enonce() + "' : texteSupport manquant"));
    }

    private static String indice(Question item) {
        return item.indice().orElseThrow(
                () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
    }

    private static boolean commenceParUneMajuscule(String proposition) {
        return Character.isUpperCase(proposition.codePointAt(0));
    }

    /** INF-CE1-1 : la sorte d'inference d'un item se lit a son enonce. */
    private static String sorte(Question item) {
        String enonce = item.enonce();
        if (enonce.startsWith("Comment se sent")) {
            return "sentiment";
        }
        if (enonce.startsWith("Où ")) {
            return "lieu";
        }
        if (enonce.startsWith("Pourquoi ")) {
            return "cause";
        }
        throw new AssertionError("INF-CE1-1 : '" + enonce + "' n'est ni une question de sentiment,"
                + " de lieu ni de cause");
    }

    /**
     * INF-CE1-1 : chacune des trois sortes est dans au moins un cinquieme des items. Recompte sur
     * les enonces lus par le chargeur.
     */
    private static void verifierLesTroisSortes(List<Question> items) {
        Map<String, Long> parSorte = new HashMap<>();
        for (Question item : items) {
            parSorte.merge(sorte(item), 1L, Long::sum);
        }
        int total = items.size();
        long minimum = (total + 4) / 5;
        for (String sorte : List.of("sentiment", "lieu", "cause")) {
            long compte = parSorte.getOrDefault(sorte, 0L);
            assertThat(compte)
                    .as("INF-CE1-1 : %d items de %s sur %d, au moins un cinquième (%d)", compte, sorte,
                            total, minimum)
                    .isGreaterThanOrEqualTo(minimum);
        }
    }

    /**
     * INF-CE1-2 : la bonne reponse entiere n'est jamais une sous-chaine du texte support, casse et
     * point final ignores.
     */
    private static void verifierQueLaBonneReponseNEstJamaisDansLeTexte(List<Question> items) {
        for (Question item : items) {
            String reponse = item.bonneReponse().strip().toLowerCase(Locale.ROOT)
                    .replaceAll("[.]+$", "");
            assertThat(texte(item).toLowerCase(Locale.ROOT).contains(reponse))
                    .as("INF-CE1-2 : '%s' - la bonne réponse '%s' est écrite telle quelle dans le texte",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** LON-CE1-3, s47 : au plus trente mots par texte support. */
    private static void verifierLon3(List<Question> items) {
        for (Question item : items) {
            int nombre = mots(texte(item)).size();
            assertThat(nombre)
                    .as("LON-CE1-3 : '%s' - le texte compte %d mots, au plus %d", item.enonce(),
                            nombre, LON_3_MOTS_DU_TEXTE)
                    .isLessThanOrEqualTo(LON_3_MOTS_DU_TEXTE);
        }
    }

    /** LON-CE1-4, s47 : au plus quatre phrases par texte support. */
    private static void verifierLon4Phrases(List<Question> items) {
        for (Question item : items) {
            int nombre = phrases(texte(item)).size();
            assertThat(nombre)
                    .as("LON-CE1-4 : '%s' - le texte compte %d phrases, au plus %d", item.enonce(),
                            nombre, LON_4_PHRASES)
                    .isLessThanOrEqualTo(LON_4_PHRASES);
        }
    }

    /** LON-CE1-4, s47 : aucune phrase du texte support ne compte plus de douze mots. */
    private static void verifierLon4MotsParPhrase(List<Question> items) {
        for (Question item : items) {
            for (String phrase : phrases(texte(item))) {
                int nombre = mots(phrase).size();
                assertThat(nombre)
                        .as("LON-CE1-4 : '%s' - la phrase '%s' compte %d mots, au plus %d",
                                item.enonce(), phrase, nombre, LON_4_MOTS_PAR_PHRASE)
                        .isLessThanOrEqualTo(LON_4_MOTS_PAR_PHRASE);
            }
        }
    }

    /**
     * Point d'audit de nadia-prof gardé : sur aucun item la bonne reponse n'est la seule des trois
     * propositions a commencer par une majuscule, majuscule lue en Unicode.
     */
    private static void verifierQueLaMajusculeNeDesignePasLaReponse(List<Question> items) {
        for (Question item : items) {
            boolean seuleAvecMajuscule = commenceParUneMajuscule(item.bonneReponse())
                    && item.distracteurs().stream().noneMatch(
                            PaquetCe1InferenceTest::commenceParUneMajuscule);
            assertThat(seuleAvecMajuscule)
                    .as("IN7 : '%s' - la bonne réponse '%s' est la seule proposition à majuscule",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** TRV-CE1-5 : un indice de douze mots au plus, sans renvoi a l'oreille. */
    private static void verifierLaFormeDesIndices(List<Question> items) {
        for (Question item : items) {
            String indice = indice(item);
            assertThat(mots(indice))
                    .as("TRV-CE1-5 : '%s' - l'indice '%s' compte plus de %d mots", item.enonce(), indice,
                            TRV_5_MOTS_DE_L_INDICE)
                    .hasSizeLessThanOrEqualTo(TRV_5_MOTS_DE_L_INDICE);
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("TRV-CE1-5 : '%s' - l'indice '%s' renvoie à l'oreille", item.enonce(), indice)
                    .isFalse();
        }
    }

    /** TRV-CE1-5 : aucun indice ne contient une proposition de son item, mot entier, casse ignoree. */
    private static void verifierQuAucunIndiceNeContientUneProposition(List<Question> items) {
        for (Question item : items) {
            String indice = indice(item);
            for (String proposition : propositions(item)) {
                Pattern mot = Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(proposition)
                        + "(?![\\p{L}\\p{N}])", FLAGS);
                assertThat(mot.matcher(indice).find())
                        .as("TRV-CE1-5 : '%s' - l'indice '%s' contient une proposition de son item : '%s'",
                                item.enonce(), indice, proposition)
                        .isFalse();
            }
        }
    }

    /**
     * TRV-CE1-4 : aucun mot du lexique exclu dans ce que l'enfant lit, soit l'enonce, le texte
     * support, les trois propositions et l'indice.
     */
    private static void verifierLeLexiqueExclu(List<Question> items) {
        for (Question item : items) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            textes.add(texte(item));
            textes.add(indice(item));
            for (String texte : textes) {
                assertThat(VERIFICATEUR_TRV_CE1_4.matcher(texte).find())
                        .as("TRV-CE1-4 : '%s' - '%s' contient un mot du lexique exclu", item.enonce(),
                                texte)
                        .isFalse();
            }
        }
    }

    /**
     * TRV-CE1-2, Q-X2 : la bonne reponse est strictement la plus longue, en caracteres, dans la
     * moitie des items au plus.
     */
    private static void verifierLeBiaisDeLongueur(List<Question> items) {
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat(plusLongue * 2)
                .as("TRV-CE1-2 : %d items où la bonne réponse est strictement la plus longue, en"
                        + " caractères, sur %d", plusLongue, items.size())
                .isLessThanOrEqualTo(items.size());
    }

    private static Set<String> prenomsDuTexte(Question item) {
        Set<String> prenoms = new TreeSet<>();
        Matcher mot = MOT_DE_LETTRES.matcher(texte(item));
        while (mot.find()) {
            String lettres = mot.group();
            if (GENRE_DES_PRENOMS.containsKey(lettres)) {
                prenoms.add(lettres);
            } else if (commenceParUneMajuscule(lettres) && !OUVERTURES.contains(lettres)) {
                throw new AssertionError("TRV-CE1-6 : '" + lettres + "' dans le texte de '"
                        + item.enonce() + "' est une majuscule hors des listes de prénoms et"
                        + " d'ouvertures");
            }
        }
        return prenoms;
    }

    /** TRV-CE1-6 : les prenoms distincts des textes ne vont pas plus d'un de difference d'un genre a l'autre. */
    private static void verifierLEquilibreDesPrenoms(List<Question> items) {
        Set<String> prenoms = new TreeSet<>();
        for (Question item : items) {
            prenoms.addAll(prenomsDuTexte(item));
        }
        long feminins = prenoms.stream().filter(p -> "f".equals(GENRE_DES_PRENOMS.get(p))).count();
        long masculins = prenoms.size() - feminins;
        assertThat(Math.abs(feminins - masculins))
                .as("TRV-CE1-6 : %d prénoms féminins distincts, %d masculins, à un près", feminins,
                        masculins)
                .isLessThanOrEqualTo(1);
    }

    /**
     * L'entree du catalogue qui sert le paquet est unique, son titre ne porte pas le niveau
     * (ADR-0078), l'espace avant un signe double d'un titre ou d'une consigne est insecable et le
     * jeu se joue en dix manches (DEC-0121).
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
        assertThat(entrees).as("une seule entrée du catalogue sert le paquet %s", PAQUET)
                .hasSize(1);
        Map<?, ?> entree = entrees.get(0);
        String titre = String.valueOf(entree.get("titre"));
        String consigne = String.valueOf(entree.get("consigne"));
        assertThat(entree.get("mecanique")).as("la mécanique est bandit-manchot")
                .isEqualTo("bandit-manchot");
        assertThat(entree.get("nombreDeManches")).as("dix manches (DEC-0121)").isEqualTo(10);
        assertThat(titre).as("le titre ne porte pas le niveau (ADR-0078)")
                .doesNotContainPattern("(?i)\\b(CP|CE1|CE2|CM1|CM2)\\b");
        assertThat(titre + " " + consigne)
                .as("l'espace avant ?, :, ; ou ! d'un titre ou d'une consigne est insécable (U+00A0)")
                .doesNotContainPattern("[^\\u00A0] [?:;!]");
        List<Object> identifiants = new ArrayList<>();
        for (Object jeu : (List<?>) corps.get("jeux")) {
            identifiants.add(((Map<?, ?>) jeu).get("id"));
        }
        assertThat(identifiants).as("l'identifiant de l'entrée est unique au catalogue")
                .doesNotHaveDuplicates();
    }

    @Test
    void adr0081CePaquetDeclareFraCe106EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-06"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1ChaqueItemPorteUnTexteSupportEtAucunChampAVoixHaute() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : enonce, texteSupport, bonneReponse, distracteurs et indice")
                    .containsExactlyInAnyOrder("enonce", "texteSupport", "bonneReponse",
                            "distracteurs", "indice");
        }
    }

    @Test
    void in1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTexteDistinct() {
        List<Question> items = items();
        assertThat(items).as("IN1 : 20 items (TRV-CE1-7 : vingt au moins)")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
        Set<String> supports = new HashSet<>();
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
            supports.add(texte(item));
        }
        assertThat(supports).as("TRV-CE1-7 : deux items ne partagent jamais le même texte support")
                .hasSize(items.size());
    }

    @Test
    void in2LesTroisSortesDInferenceSontRecomptees() {
        verifierLesTroisSortes(items());
    }

    @Test
    void in3AucuneBonneReponseNEstEcriteTelleQuelleDansLeTexte() {
        verifierQueLaBonneReponseNEstJamaisDansLeTexte(items());
    }

    @Test
    void in4AucunTexteSupportNeDepasseTrenteMots() {
        verifierLon3(items());
    }

    @Test
    void in4AucunTexteSupportNeDepasseQuatrePhrases() {
        verifierLon4Phrases(items());
    }

    @Test
    void in4AucunePhraseDuTexteSupportNeDepasseDouzeMots() {
        verifierLon4MotsParPhrase(items());
    }

    @Test
    void in5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
        }
    }

    @Test
    void in5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniqueSurLePaquet() {
        List<Question> items = items();
        verifierLaFormeDesIndices(items);
        assertThat(items.stream().map(PaquetCe1InferenceTest::indice).collect(
                java.util.stream.Collectors.toSet()))
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void in6AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit() {
        verifierLeLexiqueExclu(items());
    }

    @Test
    void in7LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems() {
        verifierQueLaMajusculeNeDesignePasLaReponse(items());
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        verifierLeBiaisDeLongueur(items());
    }

    @Test
    void trv6LesPrenomsDistinctsSontEquilibresEnGenreAUnPres() {
        verifierLEquilibreDesPrenoms(items());
    }

    @Test
    void trv6ChaquePrenomDeLaTableDeGenreFigureDansUnTexte() {
        Set<String> rencontres = new TreeSet<>();
        for (Question item : items()) {
            rencontres.addAll(prenomsDuTexte(item));
        }
        assertThat(rencontres)
                .as("chaque prénom déclaré dans la table de genre figure dans au moins un texte :"
                        + " une table qui pourrit ne prouve plus rien")
                .containsExactlyInAnyOrderElementsOf(GENRE_DES_PRENOMS.keySet());
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueSonTitreNePorteNiNiveauNiEspaceOrdinaire() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    /** ADR-0013 obligation 3 : le jeu fautif de INF-CE1-1, un seul item de lieu sur huit. */
    @Test
    void leJeuFautifCommiteEstRefuseSesTroisSortes() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLesTroisSortes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("INF-CE1-1")
                .hasMessageContaining("1 items de lieu sur 8");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de INF-CE1-2, une bonne reponse ecrite dans le texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseEcriteDansLeTexte() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierQueLaBonneReponseNEstJamaisDansLeTexte(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("INF-CE1-2")
                .hasMessageContaining("Comment se sent Malo")
                .hasMessageContaining("il pleure");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-3, un texte de trente et un mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeTrenteEtUnMots() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLon3(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-3")
                .hasMessageContaining("Pourquoi Lina court-elle")
                .hasMessageContaining("31 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, un texte de cinq phrases. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeCinqPhrases() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLon4Phrases(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("Comment se sent Mila")
                .hasMessageContaining("5 phrases");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, une phrase de treize mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePhraseDeTreizeMots() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLon4MotsParPhrase(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("Comment se sent Nina")
                .hasMessageContaining("13 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de IN7, une majuscule seule sur la bonne reponse. */
    @Test
    void leJeuFautifCommiteEstRefuseSaMajusculeSeule() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierQueLaMajusculeNeDesignePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("IN7")
                .hasMessageContaining("Où est Zoé")
                .hasMessageContaining("Au zoo");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("elle a peur");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice de quinze mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeQuinzeMots() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLaFormeDesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("Relis bien tout le texte");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-4, le mot inference dans un indice. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuLexiqueExclu() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLeLexiqueExclu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-4")
                .hasMessageContaining("Fais une inférence");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-6, cinq prenoms feminins pour un masculin. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<Question> items = itemsFautifs("ce1-inference-fautif");

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("5 prénoms féminins distincts, 1 masculins");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-2, la bonne reponse la plus longue deux fois sur trois. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeBiaisDeLongueur() {
        List<Question> items = itemsFautifs("ce1-inference-fautif-longueur");

        assertThatThrownBy(() -> verifierLeBiaisDeLongueur(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("2 items où la bonne réponse est strictement la plus longue");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, un titre qui porte le niveau. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSonTitreQuiPorteLeNiveau() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-titre-avec-niveau.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("niveau");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, une espace ordinaire devant le point d'interrogation. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSonEspaceOrdinaire() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-espace-ordinaire.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("insécable");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, deux entrees pour le meme paquet. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSesDeuxEntrees() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-deux-entrees.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une seule entrée");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, un nombre de manches different de dix. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSonNombreDeManches() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-manches.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("dix manches");
    }
}
