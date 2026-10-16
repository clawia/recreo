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
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-05 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-pronoms}, ligne {@code FRA-CE1-05} - identifier a qui renvoie un pronom
 * personnel ou un substitut simple dans un texte court. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, conditions {@code PRN-CE1-1} a
 * {@code PRN-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a {@code TRV-CE1-7}),
 * arbitrage de s47 sur la longueur du texte support ({@code LON-CE1-3} et {@code LON-CE1-4}) et
 * arbitrage du palier 2 de s57 ({@code arbitrage-nadia-palier-2.md}, sections 6.1 et 6.2).
 *
 * <p>Le comptage de {@code LON-CE1-3} et {@code LON-CE1-4} suit la convention de s47, reprise
 * d'{@code ADR-0089} et de {@code PaquetCe1TexteNarratifTest} : un mot contient au moins une lettre
 * ou un chiffre, une forme elidee compte pour un mot, une fin de phrase est un {@code .}, un
 * {@code ?} ou un {@code !} qui termine le texte ou qu'une majuscule suit. Le perimetre est le
 * champ {@code texteSupport}. Les proportions de {@code PRN-CE1-1} sont recomptees sur les enonces
 * lus par le chargeur, jamais sur les commentaires de l'en-tete du fichier.
 *
 * <p>Ce que ce test ne lit pas : que le sens departage deux candidats du meme genre et du meme
 * nombre dans au moins un tiers des items ({@code PRN-CE1-2}), que le referent de chaque item soit
 * exact et unique, qu'un indice dise ou regarder sans rien faire deduire du monde, qu'aucun role ni
 * aucune activite ne soit distribue par genre ({@code TRV-CE1-6}), qu'une regle fausse ne soit pas
 * enseignee ({@code TRV-CE1-5}), que le mot "cite" d'un indice soit toujours un pronom (il garde
 * seulement qu'un pronom n'y figure jamais hors des chevrons). Cela reste a la lecture et au jeu de
 * nadia-prof.
 */
class PaquetCe1PronomsTest {

    private static final String PAQUET = "ce1-pronoms";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1PronomsTest";

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

    /** PRN-CE1-1 : la question cite le mot entre chevrons, sinon c'est une question de personnage. */
    private static final Pattern ENONCE_DU_MOT_CITE = Pattern.compile(
            "^Que désigne «" + ESPACES + "+(.+?)" + ESPACES + "+» dans le texte" + ESPACES + "?\\?$");

    private static final Set<String> PRONOMS = Set.of("il", "elle", "ils", "elles");

    private static final Set<String> SUBSTITUTS_SIMPLES = Set.of("le garçon", "l'animal", "la fille");

    /** PRN-CE1-3 : un il impersonnel ne sert jamais de cible, et n'a pas sa place dans un texte. */
    private static final Pattern IL_IMPERSONNEL = Pattern.compile(
            "(?<![\\p{L}\\p{N}])il" + ESPACES + "+(pleut|faut|y a|fait)(?![\\p{L}\\p{N}])", FLAGS);

    private static final Pattern SEGMENT_ENTRE_CHEVRONS = Pattern.compile("«[^»]*»");

    private static final Pattern PRONOM_NU = Pattern.compile(
            "(?<![\\p{L}\\p{N}])(il|elle|ils|elles)(?![\\p{L}\\p{N}])", FLAGS);

    /**
     * TRV-CE1-6 : le genre de chaque prenom des textes, declaration d'auteur faute de lexique de
     * prenoms dans ce depot. Ce que la clause prouve est l'equilibre de cette table, jamais qu'elle
     * dit vrai.
     */
    private static final Map<String, String> GENRE_DES_PRENOMS = Map.ofEntries(
            Map.entry("Anna", "f"), Map.entry("Aya", "f"), Map.entry("Eva", "f"),
            Map.entry("Inès", "f"), Map.entry("Jade", "f"), Map.entry("Jeanne", "f"),
            Map.entry("Julie", "f"), Map.entry("Kenza", "f"), Map.entry("Lina", "f"),
            Map.entry("Lola", "f"), Map.entry("Lucie", "f"), Map.entry("Manon", "f"),
            Map.entry("Maya", "f"), Map.entry("Maëlle", "f"), Map.entry("Mila", "f"),
            Map.entry("Mina", "f"), Map.entry("Nina", "f"), Map.entry("Nour", "f"),
            Map.entry("Rose", "f"), Map.entry("Sofia", "f"), Map.entry("Zoé", "f"),
            Map.entry("Adam", "m"), Map.entry("Adrien", "m"), Map.entry("Bastien", "m"),
            Map.entry("Clément", "m"), Map.entry("Elias", "m"), Map.entry("Enzo", "m"),
            Map.entry("Hamza", "m"), Map.entry("Hugo", "m"), Map.entry("Ilyes", "m"),
            Map.entry("Karim", "m"), Map.entry("Léo", "m"), Map.entry("Malo", "m"),
            Map.entry("Nael", "m"), Map.entry("Noé", "m"), Map.entry("Paul", "m"),
            Map.entry("Rayan", "m"), Map.entry("Sacha", "m"), Map.entry("Samir", "m"),
            Map.entry("Tom", "m"), Map.entry("Yanis", "m"));

    /** Mots qui ouvrent une phrase d'un texte sans etre des prenoms ("L" est l'elision de "L'animal"). */
    private static final Set<String> OUVERTURES = Set.of(
            "Elle", "Elles", "Il", "Ils", "L", "La", "Le", "Les", "Un", "Une");

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
        try (InputStream flux = PaquetCe1PronomsTest.class.getResourceAsStream(chemin)) {
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

    private static List<Question> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-pronoms-fautif"));
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

    private static Pattern motEntier(String mot) {
        return Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(mot) + "(?![\\p{L}\\p{N}])",
                FLAGS);
    }

    /** PRN-CE1-1 : le mot que l'enonce cite entre chevrons, en minuscules. */
    private static String motCite(Question item) {
        Matcher enonce = ENONCE_DU_MOT_CITE.matcher(item.enonce());
        if (!enonce.matches()) {
            throw new AssertionError("PRN-CE1-1 : '" + item.enonce() + "' ne cite pas son mot entre"
                    + " chevrons (« mot » dans le texte), c'est une question de personnage");
        }
        return enonce.group(1).toLowerCase(Locale.ROOT);
    }

    /** PRN-CE1-1 : chaque enonce cite un pronom personnel ou un substitut simple. */
    private static void verifierQueChaqueEnonceCiteUnPronomOuUnSubstitut(List<Question> items) {
        for (Question item : items) {
            String mot = motCite(item);
            assertThat(PRONOMS.contains(mot) || SUBSTITUTS_SIMPLES.contains(mot))
                    .as("PRN-CE1-1 : '%s' - « %s » n'est ni il, elle, ils, elles ni un substitut simple",
                            item.enonce(), mot)
                    .isTrue();
        }
    }

    /** PRN-CE1-1 : ils et elles reunis font au moins un cinquieme des items. */
    private static void verifierLesItemsIlsEtElles(List<Question> items) {
        long ilsEtElles = items.stream()
                .map(PaquetCe1PronomsTest::motCite)
                .filter(mot -> mot.equals("ils") || mot.equals("elles"))
                .count();
        assertThat(ilsEtElles)
                .as("PRN-CE1-1 : %d items ils ou elles sur %d, au moins un cinquième", ilsEtElles,
                        items.size())
                .isGreaterThanOrEqualTo((items.size() + 4) / 5);
    }

    /** PRN-CE1-1 : les substituts simples font au plus un tiers des items. */
    private static void verifierLesItemsDeSubstitut(List<Question> items) {
        long substituts = items.stream()
                .map(PaquetCe1PronomsTest::motCite)
                .filter(SUBSTITUTS_SIMPLES::contains)
                .count();
        assertThat(substituts)
                .as("PRN-CE1-1 : %d substituts simples sur %d, au plus un tiers", substituts,
                        items.size())
                .isLessThanOrEqualTo(items.size() / 3);
    }

    /** PRN-CE1-3 : le mot cite n'apparait qu'une fois dans le texte support, mot entier, casse ignoree. */
    private static void verifierQueLeMotCiteNApparaitQuUneFois(List<Question> items) {
        for (Question item : items) {
            String mot = motCite(item);
            Matcher occurrences = motEntier(mot).matcher(texte(item));
            int nombre = 0;
            while (occurrences.find()) {
                nombre++;
            }
            assertThat(nombre)
                    .as("PRN-CE1-3 : '%s' - « %s » apparaît %d fois dans le texte, une seule attendue",
                            item.enonce(), mot, nombre)
                    .isEqualTo(1);
        }
    }

    /** PRN-CE1-3 : aucun il impersonnel dans un texte support, donc aucun ne peut servir de cible. */
    private static void verifierQuAucunIlImpersonnelNEstServi(List<Question> items) {
        for (Question item : items) {
            assertThat(IL_IMPERSONNEL.matcher(texte(item)).find())
                    .as("PRN-CE1-3 : '%s' - le texte '%s' porte un il impersonnel", item.enonce(),
                            texte(item))
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

    /** LON-CE1-5 : au plus six mots par proposition. */
    private static void verifierLon5(List<Question> items) {
        for (Question item : items) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("LON-CE1-5 : '%s' - proposition '%s' de plus de six mots", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
        }
    }

    /**
     * Point d'audit 6.1 (3) de l'arbitrage du palier 2, Q-P6 : sur aucun item la bonne reponse n'est
     * la seule des trois propositions a commencer par une majuscule, majuscule lue en Unicode.
     */
    private static void verifierQueLaMajusculeNeDesignePasLaReponse(List<Question> items) {
        for (Question item : items) {
            boolean seuleAvecMajuscule = commenceParUneMajuscule(item.bonneReponse())
                    && item.distracteurs().stream().noneMatch(
                            PaquetCe1PronomsTest::commenceParUneMajuscule);
            assertThat(seuleAvecMajuscule)
                    .as("PN4 : '%s' - la bonne réponse '%s' est la seule proposition à majuscule",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** TRV-CE1-5 : aucun indice ne contient une proposition de son item, mot entier, casse ignoree. */
    private static void verifierQuAucunIndiceNeContientUneProposition(List<Question> items) {
        for (Question item : items) {
            String indice = indice(item);
            for (String proposition : propositions(item)) {
                assertThat(motEntier(proposition).matcher(indice).find())
                        .as("TRV-CE1-5 : '%s' - l'indice '%s' contient une proposition de son item : '%s'",
                                item.enonce(), indice, proposition)
                        .isFalse();
            }
        }
    }

    /**
     * Point d'audit de nadia-prof (reponse-nadia-q-palier-1.md 4.2), repris par le palier 2 : un mot
     * cite dans un indice ou un enonce se marque entre chevrons. Ce que le test lit : hors des
     * chevrons, ni un indice ni un enonce ne porte il, elle, ils ou elles.
     */
    private static void verifierQueLePronomCiteSeMarqueEntreChevrons(List<Question> items) {
        for (Question item : items) {
            for (String texte : List.of(item.enonce(), indice(item))) {
                String horsChevrons = SEGMENT_ENTRE_CHEVRONS.matcher(texte).replaceAll(" ");
                Matcher nu = PRONOM_NU.matcher(horsChevrons);
                boolean trouve = nu.find();
                assertThat(trouve)
                        .as("PN7 : '%s' - le pronom cité '%s' n'est pas entre chevrons dans '%s'",
                                item.enonce(), trouve ? nu.group() : "", texte)
                        .isFalse();
            }
        }
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
     * (ADR-0078) et l'espace avant un signe double d'un titre ou d'une consigne est insecable.
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
        assertThat(titre).as("le titre ne porte pas le niveau (ADR-0078)")
                .doesNotContainPattern("(?i)\\b(CP|CE1|CE2|CM1|CM2)\\b");
        assertThat(titre + " " + consigne)
                .as("l'espace avant ?, :, ; ou ! d'un titre ou d'une consigne est insécable (U+00A0)")
                .doesNotContainPattern("[^\\u00A0] [?:;!]");
    }

    @Test
    void adr0081CePaquetDeclareFraCe105EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-05"));
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
    void pn1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTexteDistinct() {
        List<Question> items = items();
        assertThat(items).as("PN1 : 20 items (TRV-CE1-7 : vingt au moins)")
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
    void pn2ChaqueEnonceCiteUnPronomOuUnSubstitutSimpleEntreChevrons() {
        verifierQueChaqueEnonceCiteUnPronomOuUnSubstitut(items());
    }

    @Test
    void pn2IlsEtEllesFontAuMoinsUnCinquiemeDesItems() {
        verifierLesItemsIlsEtElles(items());
    }

    @Test
    void pn2LesSubstitutsSimplesFontAuPlusUnTiersDesItems() {
        verifierLesItemsDeSubstitut(items());
    }

    @Test
    void pn3LeMotCiteNApparaitQuUneFoisDansLeTexteSupport() {
        verifierQueLeMotCiteNApparaitQuUneFois(items());
    }

    @Test
    void pn3AucunIlImpersonnelNEstServi() {
        verifierQuAucunIlImpersonnelNEstServi(items());
    }

    @Test
    void pn4LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems() {
        verifierQueLaMajusculeNeDesignePasLaReponse(items());
    }

    @Test
    void pn5Lon3AucunTexteSupportNeDepasseTrenteMots() {
        verifierLon3(items());
    }

    @Test
    void pn5Lon4AucunTexteSupportNeDepasseQuatrePhrases() {
        verifierLon4Phrases(items());
    }

    @Test
    void pn5Lon4AucunePhraseDuTexteSupportNeDepasseDouzeMots() {
        verifierLon4MotsParPhrase(items());
    }

    @Test
    void pn6Lon5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
        List<Question> items = items();
        verifierLon5(items);
        for (Question item : items) {
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
        }
    }

    @Test
    void pn6Trv5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniqueSurLePaquet() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = indice(item);
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots", indice)
                    .hasSizeLessThanOrEqualTo(TRV_5_MOTS_DE_L_INDICE);
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("'%s' : l'indice ne renvoie pas à l'oreille (TRV-CE1-5)", indice)
                    .isFalse();
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void pn6Trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void pn7AucunPronomCiteDansUnIndiceOuUnEnonceNEstHorsDesChevrons() {
        verifierQueLePronomCiteSeMarqueEntreChevrons(items());
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
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat(plusLongue * 2)
                .as("TRV-CE1-2 : %d items où la bonne réponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(items.size());
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceLesPropositionsOuLIndice() {
        for (Question item : items()) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            textes.add(indice(item));
            for (String texte : textes) {
                assertThat(VERIFICATEUR_TRV_CE1_4.matcher(texte).find())
                        .as("'%s' : aucun mot du lexique exclu (TRV-CE1-4)", texte).isFalse();
            }
        }
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueSonTitreSansNiveauEtSesEspacesInsecables() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    /** ADR-0013 obligation 3 : le jeu fautif de PRN-CE1-1, un enonce qui ne cite pas son mot. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnEnonceQuiNeCitePasSonMot() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueChaqueEnonceCiteUnPronomOuUnSubstitut(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRN-CE1-1")
                .hasMessageContaining("Qui donne un biscuit");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de PRN-CE1-1, aucun item ils ni elles sur onze. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesProportionsDIlsEtDElles() {
        List<Question> items = itemsFautifsQuiCitentLeurMot();

        assertThatThrownBy(() -> verifierLesItemsIlsEtElles(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRN-CE1-1")
                .hasMessageContaining("0 items ils ou elles sur 14");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de PRN-CE1-1, cinq substituts simples sur quatorze. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesSubstitutsSimples() {
        List<Question> items = itemsFautifsQuiCitentLeurMot();

        assertThatThrownBy(() -> verifierLesItemsDeSubstitut(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRN-CE1-1")
                .hasMessageContaining("5 substituts simples sur 14");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de PRN-CE1-3, un pronom cite deux fois dans le texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnPronomCiteDeuxFois() {
        List<Question> items = itemsFautifsQuiCitentLeurMot();

        assertThatThrownBy(() -> verifierQueLeMotCiteNApparaitQuUneFois(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRN-CE1-3")
                .hasMessageContaining("apparaît 2 fois");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de PRN-CE1-3, un il impersonnel dans un texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIlImpersonnel() {
        List<Question> items = itemsFautifsQuiCitentLeurMot();

        assertThatThrownBy(() -> verifierQuAucunIlImpersonnelNEstServi(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRN-CE1-3")
                .hasMessageContaining("il impersonnel");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de PN4, une majuscule sur la seule bonne reponse. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaMajusculeSeule() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLaMajusculeNeDesignePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PN4")
                .hasMessageContaining("Elias");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-3, un texte de trente et un mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeTrenteEtUnMots() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon3(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-3")
                .hasMessageContaining("31 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, un texte de cinq phrases. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeCinqPhrases() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon4Phrases(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("5 phrases");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, une phrase de treize mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePhraseDeTreizeMots() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon4MotsParPhrase(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("13 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-5, une proposition de sept mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionDeSeptMots() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon5(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5")
                .hasMessageContaining("de plus de six mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("Tom");
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif du point d'audit sur les chevrons, l'indice qui servait
     * les items 10 et 13 avant la correction de nadia-prof (reponse-nadia-q-palier-2.md 1.1).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnPronomCiteHorsDesChevrons() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLePronomCiteSeMarqueEntreChevrons(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PN7")
                .hasMessageContaining("Elle désigne un mot féminin");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-6, neuf prenoms feminins pour cinq masculins. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("féminins distincts");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, un titre qui porte le niveau. */
    @Test
    void leCatalogueFautifDontLeTitrePorteLeNiveauEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-titre-avec-niveau.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("niveau");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, une espace ordinaire devant le point d'interrogation. */
    @Test
    void leCatalogueFautifDontLEspaceDevantUnSigneDoubleEstOrdinaireEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-espace-ordinaire.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("insécable");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif, deux entrees pour le meme paquet. */
    @Test
    void leCatalogueFautifQuiSertLePaquetDeuxFoisEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-deux-entrees.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("une seule entrée");
    }

    /** Les items fautifs dont l'enonce cite son mot : tous sauf l'item de personnage du jeu fautif. */
    private static List<Question> itemsFautifsQuiCitentLeurMot() {
        return itemsFautifs().stream()
                .filter(item -> ENONCE_DU_MOT_CITE.matcher(item.enonce()).matches())
                .toList();
    }
}
