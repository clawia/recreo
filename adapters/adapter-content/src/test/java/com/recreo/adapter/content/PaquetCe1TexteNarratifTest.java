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
 * S57-BR-FRA-CE1-04 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-texte-narratif}, ligne {@code FRA-CE1-04} - comprendre un texte narratif court
 * lu seul. Arbitrage prealable de s48 ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md},
 * section 3.4, conditions {@code RCT-CE1-1} a {@code RCT-CE1-3} et sept conditions transverses
 * {@code TRV-CE1-1} a {@code TRV-CE1-7}), arbitrage de s47 sur la longueur du texte support
 * ({@code LON-CE1-3} et {@code LON-CE1-4}) et lecture du palier 1 de s57 ({@code lecture-nadia-palier-1.md},
 * decisions {@code D1} a {@code D3}).
 *
 * <p>Le comptage de {@code LON-CE1-3} et {@code LON-CE1-4} suit la convention de s47, reprise
 * d'{@code ADR-0089} : un mot contient au moins une lettre ou un chiffre, une forme elidee compte
 * pour un mot, une fin de phrase est un {@code .}, un {@code ?} ou un {@code !} qui termine le texte
 * ou qu'une majuscule suit. Le perimetre est le champ {@code texteSupport}.
 *
 * <p>Ce que ce test ne lit pas : que le texte d'un indice fasse relire et non deduire du monde
 * (il garde la forme, {@code Relis} ou {@code Cherche}, pas ce que l'indice dit), qu'une regle
 * fausse n'y soit pas enseignee (TRV-CE1-5), l'absence de passe simple (RCT-CE1-3), que les
 * propositions raccourcies se lisent comme des actions pour un enfant de CE1, qu'aucun role ni
 * aucune activite ne soit distribue par genre (TRV-CE1-6), que la majuscule ne serve pas sur les
 * items "en dernier". Cela reste a la lecture et au jeu de nadia-prof.
 */
class PaquetCe1TexteNarratifTest {

    private static final String PAQUET = "ce1-texte-narratif";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1TexteNarratifTest";

    private static final String ID_ENTREE_CP = "bandit-manchot-texte-narratif-cp";

    /** La consigne est celle que l'enfant lit deja sur l'entree du texte narratif du CP. */
    private static final String CONSIGNE_ATTENDUE = "Lis le petit texte, puis réponds à la question.";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 20;

    /** LON-CE1-3 et LON-CE1-4 : bornes de l'arbitrage de s47, section 2. */
    private static final int LON_3_MOTS_DU_TEXTE = 30;

    private static final int LON_4_PHRASES = 4;

    private static final int LON_4_MOTS_PAR_PHRASE = 12;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    /** Fin de phrase de la convention de s47 : un signe de fin qu'une majuscule suit. */
    private static final Pattern FIN_DE_PHRASE =
            Pattern.compile("(?<=[.?!])" + ESPACES + "+(?=\\p{Lu})");

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    private static final Pattern MOT_DE_LETTRES = Pattern.compile("\\p{L}+");

    /** RCT-CE1-1 : ce qui range un enonce parmi les questions d'ordre. */
    private static final List<String> MARQUES_D_ORDRE =
            List.of(" en dernier", " en premier", " avant ", " après ", " à la fin");

    /**
     * TRV-CE1-6 : le genre de chaque prenom des textes, declaration d'auteur faute de lexique de
     * prenoms dans ce depot. Ce que la clause prouve est l'equilibre de cette table, jamais qu'elle
     * dit vrai.
     */
    private static final Map<String, String> GENRE_DES_PRENOMS = Map.ofEntries(
            Map.entry("Alice", "f"), Map.entry("Aya", "f"), Map.entry("Aïcha", "f"),
            Map.entry("Chloé", "f"), Map.entry("Inès", "f"), Map.entry("Jade", "f"),
            Map.entry("Lina", "f"), Map.entry("Lola", "f"), Map.entry("Léa", "f"),
            Map.entry("Maëlle", "f"), Map.entry("Mila", "f"), Map.entry("Nina", "f"),
            Map.entry("Nour", "f"), Map.entry("Salomé", "f"), Map.entry("Sara", "f"),
            Map.entry("Zoé", "f"), Map.entry("Élise", "f"),
            Map.entry("Adam", "m"), Map.entry("Elias", "m"), Map.entry("Hugo", "m"),
            Map.entry("Ibrahim", "m"), Map.entry("Karim", "m"), Map.entry("Louis", "m"),
            Map.entry("Léo", "m"), Map.entry("Malo", "m"), Map.entry("Noé", "m"),
            Map.entry("Paul", "m"), Map.entry("Rayan", "m"), Map.entry("Sami", "m"),
            Map.entry("Samir", "m"), Map.entry("Théo", "m"), Map.entry("Tom", "m"),
            Map.entry("Yanis", "m"), Map.entry("Émile", "m"));

    /** Mots qui ouvrent une phrase d'un texte sans etre des prenoms. */
    private static final Set<String> OUVERTURES = Set.of(
            "À", "Après", "Au", "Ce", "Elle", "En", "Enfin", "Ensuite", "Il", "Ils", "Le", "Les",
            "Pendant", "Puis", "Un", "Une");

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
        try (InputStream flux = PaquetCe1TexteNarratifTest.class.getResourceAsStream(chemin)) {
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
                .chargerPaquet("ce1-texte-narratif-fautif"));
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

    private static boolean commenceParUneMajuscule(String proposition) {
        return Character.isUpperCase(proposition.codePointAt(0));
    }

    /** RCT-CE1-1 : la question d'un item se lit a son enonce. */
    private static String categorie(Question item) {
        String enonce = item.enonce();
        if (enonce.startsWith("Où ")) {
            return "lieu";
        }
        if (enonce.startsWith("Quand ") || enonce.startsWith("Quel jour ")) {
            return "moment";
        }
        if (MARQUES_D_ORDRE.stream().anyMatch(enonce::contains)) {
            return "ordre";
        }
        if (enonce.startsWith("Qui ")) {
            return "personnage";
        }
        throw new AssertionError("RCT-CE1-1 : '" + enonce + "' n'est ni une question de personnage,"
                + " de lieu, de moment ni d'ordre");
    }

    /**
     * RCT-CE1-1 : les questions d'ordre font au moins un quart des items, celles de moment au moins
     * un cinquieme, personnages et lieu reunis au plus la moitie. Recompte sur les enonces lus par
     * le chargeur, jamais sur les commentaires de l'en-tete du fichier.
     */
    private static void verifierLesProportions(List<Question> items) {
        Map<String, Long> parCategorie = new HashMap<>();
        for (Question item : items) {
            parCategorie.merge(categorie(item), 1L, Long::sum);
        }
        int total = items.size();
        long ordre = parCategorie.getOrDefault("ordre", 0L);
        long moment = parCategorie.getOrDefault("moment", 0L);
        long personnageEtLieu = parCategorie.getOrDefault("personnage", 0L)
                + parCategorie.getOrDefault("lieu", 0L);
        assertThat(ordre).as("RCT-CE1-1 : %d items d'ordre sur %d, au moins un quart", ordre, total)
                .isGreaterThanOrEqualTo((total + 3) / 4);
        assertThat(moment).as("RCT-CE1-1 : %d items de moment sur %d, au moins un cinquième", moment,
                total).isGreaterThanOrEqualTo((total + 4) / 5);
        assertThat(personnageEtLieu)
                .as("RCT-CE1-1 : %d items de personnage et de lieu sur %d, au plus la moitié",
                        personnageEtLieu, total)
                .isLessThanOrEqualTo(total / 2);
    }

    /**
     * RCT-CE1-2 et S6 : chaque proposition est une suite de mots du texte support de son item, casse
     * ignoree, eventuellement raccourcie.
     */
    private static void verifierQueChaquePropositionEstDansLeTexte(List<Question> items) {
        for (Question item : items) {
            String support = texte(item);
            for (String proposition : propositions(item)) {
                Pattern suite = Pattern.compile("(?<![\\p{L}\\p{N}])" + Pattern.quote(proposition)
                        + "(?![\\p{L}\\p{N}])", FLAGS);
                assertThat(suite.matcher(support).find())
                        .as("RCT-CE1-2 : '%s' - la proposition '%s' n'est pas une suite de mots du texte",
                                item.enonce(), proposition)
                        .isTrue();
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
                throw new AssertionError("RCT-CE1-3 : '" + lettres + "' dans le texte de '"
                        + item.enonce() + "' est une majuscule hors des listes de prénoms et"
                        + " d'ouvertures");
            }
        }
        return prenoms;
    }

    /** RCT-CE1-3 : au plus trois personnages nommes par texte. */
    private static void verifierAuPlusTroisPersonnagesParTexte(List<Question> items) {
        for (Question item : items) {
            Set<String> prenoms = prenomsDuTexte(item);
            assertThat(prenoms)
                    .as("RCT-CE1-3 : '%s' - %d personnages nommés %s, au plus trois", item.enonce(),
                            prenoms.size(), prenoms)
                    .hasSizeLessThanOrEqualTo(3);
        }
    }

    /** RCT-CE1-3 : un texte qui sert une question d'ordre porte au moins trois evenements. */
    private static void verifierQueLesTextesDOrdrePortentTroisPhrases(List<Question> items) {
        for (Question item : items) {
            if (!"ordre".equals(categorie(item))) {
                continue;
            }
            assertThat(phrases(texte(item)))
                    .as("RCT-CE1-3 : '%s' - question d'ordre sur un texte de moins de trois phrases",
                            item.enonce())
                    .hasSizeGreaterThanOrEqualTo(3);
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
     * D1 de la lecture du palier 1 : sur aucun item la bonne reponse n'est la seule des trois
     * propositions a commencer par une majuscule, majuscule lue en Unicode.
     */
    private static void verifierQueLaMajusculeNeDesignePasLaReponse(List<Question> items) {
        for (Question item : items) {
            boolean seuleAvecMajuscule = commenceParUneMajuscule(item.bonneReponse())
                    && item.distracteurs().stream().noneMatch(
                            PaquetCe1TexteNarratifTest::commenceParUneMajuscule);
            assertThat(seuleAvecMajuscule)
                    .as("D1 : '%s' - la bonne réponse '%s' est la seule proposition à majuscule",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** D2 de la lecture du palier 1 : chaque indice renvoie au texte, il commence par Relis ou Cherche. */
    private static void verifierQueChaqueIndiceRenvoieAuTexte(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            assertThat(indice.startsWith("Relis") || indice.startsWith("Cherche"))
                    .as("D2 : '%s' - l'indice '%s' ne commence ni par Relis ni par Cherche",
                            item.enonce(), indice)
                    .isTrue();
        }
    }

    /** TRV-CE1-5 : aucun indice ne contient une proposition de son item, mot entier, casse ignoree. */
    private static void verifierQuAucunIndiceNeContientUneProposition(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
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
     * La consigne du jeu, lue au catalogue, est celle de l'entree du CP, et l'entree est unique et
     * distincte de celle du CP.
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
        assertThat(entree.get("consigne"))
                .as("la consigne est celle de l'entrée du CP, mot pour mot")
                .isEqualTo(CONSIGNE_ATTENDUE);
        assertThat(entree.get("id")).as("l'identifiant de l'entrée, distinct de celui du CP")
                .isNotEqualTo(ID_ENTREE_CP);
    }

    @Test
    void adr0081CePaquetDeclareFraCe104EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-04"));
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
    void s1LePaquetGardeSesVingtItemsChacunATroisPropositionsEtSonTexteDistinct() {
        List<Question> items = items();
        assertThat(items).as("S1 : 20 items (TRV-CE1-7 : vingt au moins)")
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
    void rct1LesProportionsDesQuatreQuestionsSontRecomptees() {
        verifierLesProportions(items());
    }

    @Test
    void rct2s6AucunePropositionNEstHorsDuTexteSupport() {
        verifierQueChaquePropositionEstDansLeTexte(items());
    }

    @Test
    void rct3AuPlusTroisPersonnagesNommesParTexte() {
        verifierAuPlusTroisPersonnagesParTexte(items());
    }

    @Test
    void rct3UneQuestionDOrdreSEcritSurUnTexteDAuMoinsTroisPhrases() {
        verifierQueLesTextesDOrdrePortentTroisPhrases(items());
    }

    @Test
    void lon3s4AucunTexteSupportNeDepasseTrenteMots() {
        verifierLon3(items());
    }

    @Test
    void lon4s4AucunTexteSupportNeDepasseQuatrePhrases() {
        verifierLon4Phrases(items());
    }

    @Test
    void lon4s4AucunePhraseDuTexteSupportNeDepasseDouzeMots() {
        verifierLon4MotsParPhrase(items());
    }

    @Test
    void d1s2LaMajusculeNeDesigneJamaisLaBonneReponseSurLesVingtItems() {
        verifierQueLaMajusculeNeDesignePasLaReponse(items());
    }

    @Test
    void d2s3ChaqueIndiceRenvoieAuTexteEtLIndiceDeSaraNEstPlusCelui() {
        List<Question> items = items();
        verifierQueChaqueIndiceRenvoieAuTexte(items);
        assertThat(items.stream().map(i -> i.indice().orElse("")).toList())
                .as("S3 : l'indice qui faisait déduire du monde n'est plus servi")
                .noneMatch(indice -> indice.contains("Pense à ce qu'on fait avant de couper un fruit"));
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniqueSurLePaquet() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots", indice)
                    .hasSizeLessThanOrEqualTo(12);
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("'%s' : l'indice ne renvoie pas à l'oreille (TRV-CE1-5)", indice)
                    .isFalse();
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
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
            item.indice().ifPresent(textes::add);
            for (String texte : textes) {
                assertThat(VERIFICATEUR_TRV_CE1_4.matcher(texte).find())
                        .as("'%s' : aucun mot du lexique exclu (TRV-CE1-4)", texte).isFalse();
            }
        }
    }

    @Test
    void lon5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
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
    void k3LEntreeDuCatalogueEstUniqueDistincteDeCelleDuCpEtSaConsigneEstCelleDuCp() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif des proportions. Un seul item de moment sur neuf, alors
     * que le fichier de contenu annonce ses categories en commentaires : le compte est celui des
     * enonces.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesProportions() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RCT-CE1-1")
                .hasMessageContaining("1 items de moment sur 9");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de RCT-CE1-2, une proposition absente du texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionHorsDuTexte() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueChaquePropositionEstDansLeTexte(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RCT-CE1-2")
                .hasMessageContaining("Léo");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de RCT-CE1-3, quatre personnages nommes. */
    @Test
    void leJeuFautifCommiteEstRefuseSurQuatrePersonnagesNommes() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierAuPlusTroisPersonnagesParTexte(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RCT-CE1-3")
                .hasMessageContaining("Qui joue au ballon")
                .hasMessageContaining("4 personnages");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de RCT-CE1-3, une question d'ordre sur deux phrases. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUneQuestionDOrdreSurDeuxPhrases() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLesTextesDOrdrePortentTroisPhrases(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("RCT-CE1-3")
                .hasMessageContaining("Que fait Hugo en dernier");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-3, un texte de trente et un mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeTrenteEtUnMots() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon3(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-3")
                .hasMessageContaining("Quand Tom mange-t-il")
                .hasMessageContaining("31 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, un texte de cinq phrases. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteDeCinqPhrases() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon4Phrases(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("Que fait Lina en dernier")
                .hasMessageContaining("5 phrases");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-4, une phrase de treize mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePhraseDeTreizeMots() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLon4MotsParPhrase(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-4")
                .hasMessageContaining("Où va Tom")
                .hasMessageContaining("13 mots");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de D1, une majuscule accentuee sur la seule bonne reponse. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaMajusculeSeule() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueLaMajusculeNeDesignePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("D1")
                .hasMessageContaining("Élise pèle une pomme");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de D2, un indice qui ne renvoie pas au texte. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiNeRenvoiePasAuTexte() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQueChaqueIndiceRenvoieAuTexte(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("D2")
                .hasMessageContaining("Pense à ce qu'on fait avec un livre");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("dans la cour");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-6, huit prenoms feminins pour cinq masculins. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("8 prénoms féminins distincts, 5 masculins");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif. Sa consigne n'est pas celle de l'entree du CP. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSaConsigne() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("consigne");
    }
}
