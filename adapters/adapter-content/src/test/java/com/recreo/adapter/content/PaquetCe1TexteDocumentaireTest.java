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
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-07 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-texte-documentaire}, ligne {@code FRA-CE1-07} - comprendre un texte
 * documentaire court ou une consigne ecrite, et y prelever une information. Arbitrage prealable de
 * s48 ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 3.7, conditions
 * {@code DOC-CE1-1} a {@code DOC-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage de s47 sur la longueur du texte support ({@code LON-CE1-3} et
 * {@code LON-CE1-4}), arbitrage de preparation du palier 3 de s57
 * ({@code arbitrage-nadia-preparation-palier-3-ce1.md}, sections 4.1 et 4.2) et reponse de nadia-prof
 * sur la mesure de {@code DOC-CE1-1} ({@code reponses-nadia-questions-stories-palier-3.md}, section 5).
 *
 * <p>Le comptage de {@code LON-CE1-3} et {@code LON-CE1-4} suit la convention de s47, reprise
 * d'{@code ADR-0089} et de {@code PaquetCe1TexteNarratifTest} : un mot contient au moins une lettre
 * ou un chiffre, une forme elidee compte pour un mot, une fin de phrase est un {@code .}, un
 * {@code ?} ou un {@code !} qui termine le texte ou qu'une majuscule suit. Le perimetre est le
 * champ {@code texteSupport}. Le genre de {@code DOC-CE1-1} est lu sur le marqueur
 * {@code # consigne} ou {@code # documentaire} que le fichier de contenu porte au-dessus de chaque
 * item, jamais sur l'enonce.
 *
 * <p>Ce que ce test ne lit pas, et qui reste a nadia-prof : qu'une phrase d'un texte marque
 * {@code consigne} soit une instruction et qu'un texte marque {@code documentaire} n'en soit pas
 * une (un marqueur est une declaration de l'auteur), que la question d'une consigne porte sur
 * l'ordre ou le contenu d'une instruction, l'exactitude de chaque fait ({@code DOC-CE1-3}), qu'un
 * enfant ne reponde pas sans lire (chaque item est joue texte masque), qu'aucune situation ne
 * suppose un logement, un animal possede ou des vacances ({@code TRV-CE1-6} : ce paquet ne nomme
 * aucun personnage, la condition n'est pas gardee), qu'une regle fausse ne soit pas enseignee
 * ({@code TRV-CE1-5}). Points d'audit non gardes ici : l'intrus du premier mot et le fait qu'il
 * devienne regulier sur une question, le mot cite entre chevrons, l'indice qui dit ou regarder.
 * Le pli a 744 x 551 n'est pas mesurable par un test du serveur.
 */
class PaquetCe1TexteDocumentaireTest {

    private static final String PAQUET = "ce1-texte-documentaire";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1TexteDocumentaireTest";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** LON-CE1-3 et LON-CE1-4 : bornes de l'arbitrage de s47, section 2. */
    private static final int LON_3_MOTS_DU_TEXTE = 30;

    private static final int LON_4_PHRASES = 4;

    private static final int LON_4_MOTS_PAR_PHRASE = 12;

    /** TRV-CE1-5 : un indice de douze mots au plus. */
    private static final int TRV_5_MOTS_DE_L_INDICE = 12;

    /** DOC-CE1-1 : une consigne ecrite compte de deux a quatre instructions. */
    private static final int CONSIGNE_PHRASES_MINIMUM = 2;

    private static final int CONSIGNE_PHRASES_MAXIMUM = 4;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    /** Fin de phrase de la convention de s47 : un signe de fin qu'une majuscule suit. */
    private static final Pattern FIN_DE_PHRASE =
            Pattern.compile("(?<=[.?!])" + ESPACES + "+(?=\\p{Lu})");

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

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
        try (InputStream flux = PaquetCe1TexteDocumentaireTest.class.getResourceAsStream(chemin)) {
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

    private static boolean figureDans(String texte, String proposition) {
        return texte.toLowerCase(Locale.ROOT).contains(proposition.strip().toLowerCase(Locale.ROOT)
                .replaceAll("[.]+$", ""));
    }

    /**
     * DOC-CE1-1, reponse de nadia-prof (section 5) : le genre se lit sur le marqueur place au-dessus
     * de chaque item du fichier, pas sur l'enonce. Chaque item porte exactement un marqueur ; au
     * moins la moitie des items sont documentaires, au moins un quart sont des consignes ; le texte
     * d'une consigne compte de deux a quatre phrases.
     */
    private static void verifierLesGenres(String contenu) {
        List<?> items = (List<?>) ((Map<?, ?>) new Yaml().load(contenu)).get("items");
        List<String> lignes = contenu.lines().toList();
        List<String> genres = new ArrayList<>();
        for (int i = 0; i < lignes.size(); i++) {
            if (!lignes.get(i).startsWith("  - enonce:")) {
                continue;
            }
            String enonce = String.valueOf(((Map<?, ?>) items.get(genres.size())).get("enonce"));
            String precedente = i == 0 ? "" : lignes.get(i - 1);
            assertThat(precedente.equals("  # consigne") || precedente.equals("  # documentaire"))
                    .as("DOC-CE1-1 : l'item '%s' ne porte aucun marqueur de genre au-dessus de lui",
                            enonce)
                    .isTrue();
            genres.add(precedente.substring("  # ".length()));
        }
        int total = items.size();
        assertThat(genres).as("DOC-CE1-1 : un marqueur par item").hasSize(total);
        long documentaires = genres.stream().filter("documentaire"::equals).count();
        long consignes = genres.stream().filter("consigne"::equals).count();
        assertThat(documentaires)
                .as("DOC-CE1-1 : %d textes documentaires sur %d, au moins la moitié", documentaires,
                        total)
                .isGreaterThanOrEqualTo((total + 1) / 2);
        assertThat(consignes)
                .as("DOC-CE1-1 : %d consignes sur %d, au moins un quart", consignes, total)
                .isGreaterThanOrEqualTo((total + 3) / 4);
        for (int i = 0; i < total; i++) {
            if (!"consigne".equals(genres.get(i))) {
                continue;
            }
            Map<?, ?> item = (Map<?, ?>) items.get(i);
            int nombre = phrases(String.valueOf(item.get("texteSupport"))).size();
            assertThat(nombre)
                    .as("DOC-CE1-1 : le texte consigne de '%s' compte %d phrases, de %d à %d",
                            item.get("enonce"), nombre, CONSIGNE_PHRASES_MINIMUM,
                            CONSIGNE_PHRASES_MAXIMUM)
                    .isBetween(CONSIGNE_PHRASES_MINIMUM, CONSIGNE_PHRASES_MAXIMUM);
        }
    }

    /** DOC-CE1-2 : la bonne reponse figure dans le texte, sous-chaine sans casse. */
    private static void verifierQueLaBonneReponseFigureDansLeTexte(List<Question> items) {
        for (Question item : items) {
            assertThat(figureDans(texte(item), item.bonneReponse()))
                    .as("DOC-CE1-2 : '%s' - la bonne réponse '%s' ne figure pas dans le texte",
                            item.enonce(), item.bonneReponse())
                    .isTrue();
        }
    }

    /** DOC-CE1-2 : au moins un distracteur figure aussi dans le texte, le prelevement doit choisir. */
    private static void verifierQuUnDistracteurFigureDansLeTexte(List<Question> items) {
        for (Question item : items) {
            assertThat(item.distracteurs().stream().anyMatch(d -> figureDans(texte(item), d)))
                    .as("DOC-CE1-2 : '%s' - aucun distracteur ne figure dans le texte (%s)",
                            item.enonce(), item.distracteurs())
                    .isTrue();
        }
    }

    /** DOC-CE1-3, part mesurable : aucun chiffre dans ce que l'enfant lit, donc aucun nombre au-dela de 1000. */
    private static void verifierQuAucunChiffreNEstServi(List<Question> items) {
        for (Question item : items) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            textes.add(texte(item));
            textes.add(indice(item));
            for (String texte : textes) {
                assertThat(texte).as("DOC-CE1-3 : '%s' - '%s' contient un chiffre", item.enonce(), texte)
                        .doesNotContainPattern("[0-9]");
            }
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
     * Point d'audit de nadia-prof garde : sur aucun item la bonne reponse n'est la seule des trois
     * propositions a commencer par une majuscule, majuscule lue en Unicode.
     */
    private static void verifierQueLaMajusculeNeDesignePasLaReponse(List<Question> items) {
        for (Question item : items) {
            boolean seuleAvecMajuscule = commenceParUneMajuscule(item.bonneReponse())
                    && item.distracteurs().stream().noneMatch(
                            PaquetCe1TexteDocumentaireTest::commenceParUneMajuscule);
            assertThat(seuleAvecMajuscule)
                    .as("DO7 : '%s' - la bonne réponse '%s' est la seule proposition à majuscule",
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

    /** TRV-CE1-1 : aucun item ne porte un champ a voix haute, et chaque item porte les cinq champs attendus. */
    private static void verifierLesChampsDeChaqueItem(String contenu) {
        Map<?, ?> corps = new Yaml().load(contenu);
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : '%s' - enonce, texteSupport, bonneReponse, distracteurs et indice"
                            + " seuls, aucun champ à voix haute", champs.get("enonce"))
                    .containsExactlyInAnyOrder("enonce", "texteSupport", "bonneReponse",
                            "distracteurs", "indice");
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
    void adr0081CePaquetDeclareFraCe107EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-07"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1ChaqueItemPorteUnTexteSupportEtAucunChampAVoixHaute() {
        verifierLesChampsDeChaqueItem(lire(FICHIER_CONTENU));
    }

    @Test
    void do1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTexteDistinct() {
        List<Question> items = items();
        assertThat(items).as("DO1 : 20 items (TRV-CE1-7 : vingt au moins)")
                .hasSizeGreaterThanOrEqualTo(NOMBRE_D_ITEMS_ATTENDU);
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
    void do2LesDeuxGenresSontMarquesEtRecomptesSurLeFichier() {
        verifierLesGenres(lire(FICHIER_CONTENU));
    }

    @Test
    void do3LaBonneReponseFigureDansLeTexte() {
        verifierQueLaBonneReponseFigureDansLeTexte(items());
    }

    @Test
    void do3AuMoinsUnDistracteurFigureDansLeTexte() {
        verifierQuUnDistracteurFigureDansLeTexte(items());
    }

    @Test
    void do4AucunChiffreNEstServiDoncAucunNombreAuDelaDeMille() {
        verifierQuAucunChiffreNEstServi(items());
    }

    @Test
    void do5AucunTexteSupportNeDepasseTrenteMots() {
        verifierLon3(items());
    }

    @Test
    void do5AucunTexteSupportNeDepasseQuatrePhrases() {
        verifierLon4Phrases(items());
    }

    @Test
    void do5AucunePhraseDuTexteSupportNeDepasseDouzeMots() {
        verifierLon4MotsParPhrase(items());
    }

    @Test
    void do6ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
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
    void do6ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniqueSurLePaquet() {
        List<Question> items = items();
        verifierLaFormeDesIndices(items);
        assertThat(items.stream().map(PaquetCe1TexteDocumentaireTest::indice).distinct().count())
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .isGreaterThan(1);
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void do6AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit() {
        verifierLeLexiqueExclu(items());
    }

    @Test
    void do7LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems() {
        verifierQueLaMajusculeNeDesignePasLaReponse(items());
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        verifierLeBiaisDeLongueur(items());
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueSonTitreNePorteNiNiveauNiEspaceOrdinaire() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DOC-CE1-1, un item sans marqueur de genre. */
    @Test
    void leJeuFautifCommiteEstRefuseUnItemSansMarqueurDeGenre() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH
                + "/contenu/ce1-texte-documentaire-fautif-sans-marqueur.yaml");

        assertThatThrownBy(() -> verifierLesGenres(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DOC-CE1-1")
                .hasMessageContaining("Où vit le poisson")
                .hasMessageContaining("ne porte aucun marqueur");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DOC-CE1-1, aucune consigne sur quatre items. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesProportionsDeGenres() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH
                + "/contenu/ce1-texte-documentaire-fautif-proportions.yaml");

        assertThatThrownBy(() -> verifierLesGenres(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DOC-CE1-1")
                .hasMessageContaining("0 consignes sur 4");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DOC-CE1-1, un texte de consigne d'une seule phrase. */
    @Test
    void leJeuFautifCommiteEstRefuseUneConsigneDUneSeulePhrase() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH
                + "/contenu/ce1-texte-documentaire-fautif-consigne.yaml");

        assertThatThrownBy(() -> verifierLesGenres(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DOC-CE1-1")
                .hasMessageContaining("Que faut-il prendre")
                .hasMessageContaining("1 phrases");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DOC-CE1-2, aucun distracteur dans le texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansDistracteurDansLeTexte() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierQuUnDistracteurFigureDansLeTexte(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DOC-CE1-2")
                .hasMessageContaining("Où vit le poisson")
                .hasMessageContaining("aucun distracteur");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DOC-CE1-2, une bonne reponse absente du texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseAbsenteDuTexte() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierQueLaBonneReponseFigureDansLeTexte(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DOC-CE1-2")
                .hasMessageContaining("Que mange le hibou")
                .hasMessageContaining("des mulots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DOC-CE1-3, un chiffre dans le texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnChiffre() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierQuAucunChiffreNEstServi(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DOC-CE1-3")
                .hasMessageContaining("Combien de pattes a le chien");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-3, un texte de trente et un mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeTrenteEtUnMots() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierLon3(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-3")
                .hasMessageContaining("Que fait la fourmi")
                .hasMessageContaining("31 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, un texte de cinq phrases. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeCinqPhrases() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierLon4Phrases(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("Que mange la vache")
                .hasMessageContaining("5 phrases");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, une phrase de treize mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePhraseDeTreizeMots() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierLon4MotsParPhrase(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("Où vit l'ours")
                .hasMessageContaining("13 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de DO7, une majuscule seule sur la bonne reponse. */
    @Test
    void leJeuFautifCommiteEstRefuseSaMajusculeSeule() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierQueLaMajusculeNeDesignePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DO7")
                .hasMessageContaining("Que mange l'âne")
                .hasMessageContaining("Du foin");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("dans la mer");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice de quinze mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeQuinzeMots() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierLaFormeDesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("Relis bien tout le texte");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-4, le mot radical dans l'indice. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuLexiqueExclu() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif");

        assertThatThrownBy(() -> verifierLeLexiqueExclu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-4")
                .hasMessageContaining("Cherche le radical");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-1, un champ a voix haute. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnChampAVoixHaute() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH
                + "/contenu/ce1-texte-documentaire-fautif-voix.yaml");

        assertThatThrownBy(() -> verifierLesChampsDeChaqueItem(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1")
                .hasMessageContaining("Que mange le lapin");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-2, la bonne reponse la plus longue deux fois sur trois. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeBiaisDeLongueur() {
        List<Question> items = itemsFautifs("ce1-texte-documentaire-fautif-longueur");

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
