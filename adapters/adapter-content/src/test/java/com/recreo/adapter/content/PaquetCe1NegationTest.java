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
 * S57-BR-FRA-CE1-11 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-negation}, ligne {@code FRA-CE1-11} - passer d'une phrase affirmative a une
 * phrase negative ({@code ne... pas}) et inversement. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.2, conditions
 * {@code NEG-CE1-1} a {@code NEG-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage de preparation du palier 3 de s57
 * ({@code arbitrage-nadia-preparation-palier-3-ce1.md}, sections 4.1 et 4.2) et reponse de nadia-prof
 * sur l'unite de {@code TRV-CE1-2} ({@code reponses-nadia-questions-stories-palier-3.md}, section 2 :
 * les caracteres, espaces et ponctuation compris).
 *
 * <p>Les proportions de {@code NEG-CE1-1} et l'elision de {@code NEG-CE1-2} sont recomptees sur les
 * champs lus par le chargeur, jamais sur les commentaires de l'en-tete du fichier. La famille des
 * distracteurs ({@code NEG-CE1-3}) se lit dans le commentaire {@code # NEG-CE1-3: x, y} place au-dessus
 * de chaque item du fichier de contenu, comme le demande {@code TRV-CE1-2}.
 *
 * <p>Ce que ce test ne lit pas, et qui reste a nadia-prof : l'exactitude de chaque phrase, que le
 * verbe soit au present et que la negation soit bien {@code ne... pas} (le garde ne lit que les mots
 * {@code plus}, {@code jamais}, {@code rien}, {@code personne} et {@code aucun}, pas le temps du
 * verbe), que la famille declaree en commentaire soit celle du distracteur, que les trois
 * propositions reprennent les memes mots hors de la negation ({@code NEG-CE1-3}), qu'aucun role ni
 * aucune activite ne soit distribue par genre ({@code TRV-CE1-6} : non garde), qu'une regle fausse
 * ne soit pas enseignee ({@code TRV-CE1-5}). Points d'audit non gardes ici : le mot cite entre
 * chevrons dans l'enonce (la phrase citee l'est par des guillemets francais, le garde ne lit pas
 * que le contenu cite en est une) et l'indice qui dit ou regarder. Le pli a 744 x 551 n'est pas
 * mesurable par un test du serveur.
 */
class PaquetCe1NegationTest {

    private static final String PAQUET = "ce1-negation";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1NegationTest";

    /** NE1 : le plancher de vingt items de TRV-CE1-7. */
    private static final int NOMBRE_D_ITEMS_MINIMUM = 20;

    /** TRV-CE1-5 : un indice de douze mots au plus. */
    private static final int TRV_5_MOTS_DE_L_INDICE = 12;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    /** NEG-CE1-1 : les negations que la condition exclut, lues comme des mots entiers. */
    private static final Pattern NEGATIONS_EXCLUES = Pattern.compile(
            "(?<![\\p{L}\\p{N}])(plus|jamais|rien|personne|aucun)(?![\\p{L}\\p{N}])", FLAGS);

    /** TRV-CE1-2 : la famille des distracteurs est nommee a cote de chaque item. */
    private static final Pattern FAMILLE_DES_DISTRACTEURS =
            Pattern.compile("^  # NEG-CE1-3: [a-d], [a-d]$");

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
        try (InputStream flux = PaquetCe1NegationTest.class.getResourceAsStream(chemin)) {
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

    private static String indice(Question item) {
        return item.indice().orElseThrow(
                () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
    }

    private static boolean commenceParUneMajuscule(String proposition) {
        return Character.isUpperCase(proposition.codePointAt(0));
    }

    private static boolean versLaNegative(Question item) {
        return item.enonce().contains("phrase négative");
    }

    private static boolean versLAffirmative(Question item) {
        return item.enonce().contains("phrase affirmative");
    }

    /**
     * NEG-CE1-1 : au moins la moitie des items vont vers la negative, au moins un quart vers
     * l'affirmative, et chaque enonce demande l'un ou l'autre. Recompte sur les enonces.
     */
    private static void verifierLesProportions(List<Question> items) {
        for (Question item : items) {
            assertThat(versLaNegative(item) || versLAffirmative(item))
                    .as("NEG-CE1-1 : '%s' ne demande ni la phrase négative ni la phrase affirmative",
                            item.enonce())
                    .isTrue();
        }
        int total = items.size();
        long negatives = items.stream().filter(PaquetCe1NegationTest::versLaNegative).count();
        long affirmatives = items.stream().filter(PaquetCe1NegationTest::versLAffirmative).count();
        assertThat(negatives)
                .as("NEG-CE1-1 : %d items vers la négative sur %d, au moins la moitié", negatives,
                        total)
                .isGreaterThanOrEqualTo((total + 1) / 2);
        assertThat(affirmatives)
                .as("NEG-CE1-1 : %d items vers l'affirmative sur %d, au moins un quart", affirmatives,
                        total)
                .isGreaterThanOrEqualTo((total + 3) / 4);
    }

    /**
     * NEG-CE1-1, part mesurable de son refus d'avance : aucun des mots plus, jamais, rien, personne
     * ou aucun dans l'enonce, la bonne reponse ou un distracteur.
     */
    private static void verifierQueLaNegationEstNePasSeule(List<Question> items) {
        for (Question item : items) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            for (String texte : textes) {
                Matcher mot = NEGATIONS_EXCLUES.matcher(texte);
                boolean presente = mot.find();
                assertThat(presente)
                        .as("NEG-CE1-1 : '%s' - '%s' contient une négation hors ne... pas (%s)",
                                item.enonce(), texte, presente ? mot.group() : "")
                        .isFalse();
            }
        }
    }

    /**
     * NEG-CE1-2 : l'elision (n') figure dans au moins un cinquieme des items, dans l'enonce ou la
     * bonne reponse.
     */
    private static void verifierLElision(List<Question> items) {
        long avecElision = items.stream()
                .filter(i -> i.enonce().contains("n'") || i.bonneReponse().contains("n'")).count();
        long minimum = (items.size() + 4) / 5;
        assertThat(avecElision)
                .as("NEG-CE1-2 : %d items avec élision sur %d, au moins un cinquième (%d)", avecElision,
                        items.size(), minimum)
                .isGreaterThanOrEqualTo(minimum);
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
     * TRV-CE1-2 : le fichier nomme la famille des distracteurs au-dessus de chaque item, par un
     * commentaire {@code # NEG-CE1-3: x, y} de deux lettres de a a d.
     */
    private static void verifierLesFamillesNommees(String contenu) {
        List<?> items = (List<?>) ((Map<?, ?>) new Yaml().load(contenu)).get("items");
        List<String> lignes = contenu.lines().toList();
        int compte = 0;
        for (int i = 0; i < lignes.size(); i++) {
            if (!lignes.get(i).startsWith("  - enonce:")) {
                continue;
            }
            int familles = 0;
            for (int j = i - 1; j >= 0 && lignes.get(j).startsWith("  #"); j--) {
                if (FAMILLE_DES_DISTRACTEURS.matcher(lignes.get(j)).matches()) {
                    familles++;
                }
            }
            String enonce = String.valueOf(((Map<?, ?>) items.get(compte)).get("enonce"));
            assertThat(familles)
                    .as("TRV-CE1-2 : l'item '%s' porte %d commentaires de famille NEG-CE1-3, un seul"
                            + " attendu", enonce, familles)
                    .isEqualTo(1);
            compte++;
        }
        assertThat(compte).as("TRV-CE1-2 : un commentaire de famille par item")
                .isEqualTo(items.size());
    }

    /**
     * Point d'audit de nadia-prof garde : sur aucun item la bonne reponse n'est la seule des trois
     * propositions a commencer par une majuscule, majuscule lue en Unicode.
     */
    private static void verifierQueLaMajusculeNeDesignePasLaReponse(List<Question> items) {
        for (Question item : items) {
            boolean seuleAvecMajuscule = commenceParUneMajuscule(item.bonneReponse())
                    && item.distracteurs().stream().noneMatch(
                            PaquetCe1NegationTest::commenceParUneMajuscule);
            assertThat(seuleAvecMajuscule)
                    .as("NE7 : '%s' - la bonne réponse '%s' est la seule proposition à majuscule",
                            item.enonce(), item.bonneReponse())
                    .isFalse();
        }
    }

    /** LON-CE1-5 : chaque proposition compte six mots au plus. */
    private static void verifierLesPropositionsCourtes(List<Question> items) {
        for (Question item : items) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("LON-CE1-5 : '%s' - la proposition '%s' compte plus de six mots",
                                item.enonce(), proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
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
     * TRV-CE1-4 : aucun mot du lexique exclu dans ce que l'enfant lit, soit l'enonce, les trois
     * propositions et l'indice.
     */
    private static void verifierLeLexiqueExclu(List<Question> items) {
        for (Question item : items) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
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
     * TRV-CE1-1 : aucun item ne porte de texte support, de champ a voix haute ni d'illustration ;
     * chaque item porte enonce, bonne reponse, distracteurs et indice.
     */
    private static void verifierLesChampsDeChaqueItem(String contenu) {
        Map<?, ?> corps = new Yaml().load(contenu);
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : '%s' - enonce, bonneReponse, distracteurs et indice seuls, ni texte"
                            + " support, ni champ à voix haute, ni illustration", champs.get("enonce"))
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
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
    void adr0081CePaquetDeclareFraCe111EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-11"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void ne6AucunItemNePorteDeTexteSupportDeVoixHauteNiDIllustration() {
        verifierLesChampsDeChaqueItem(lire(FICHIER_CONTENU));
    }

    @Test
    void ne1LePaquetGardeAuMoinsVingtItemsChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("NE1 : 20 items au moins (TRV-CE1-7)")
                .hasSizeGreaterThanOrEqualTo(NOMBRE_D_ITEMS_MINIMUM);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void ne2LesProportionsVersLaNegativeEtVersLAffirmativeSontRecomptees() {
        verifierLesProportions(items());
    }

    @Test
    void ne3AucuneNegationHorsNePasNEstServie() {
        verifierQueLaNegationEstNePasSeule(items());
    }

    @Test
    void ne4LElisionFigureDansAuMoinsUnCinquiemeDesItems() {
        verifierLElision(items());
    }

    @Test
    void ne5LaBonneReponseNEstStrictementLaPlusLongueQueDansLaMoitieDesItemsAuPlus() {
        verifierLeBiaisDeLongueur(items());
    }

    @Test
    void ne5ChaqueItemNommeLaFamilleDeSesDistracteursDansLeFichier() {
        verifierLesFamillesNommees(lire(FICHIER_CONTENU));
    }

    @Test
    void ne6ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
        List<Question> items = items();
        verifierLesPropositionsCourtes(items);
        for (Question item : items) {
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
        }
    }

    @Test
    void ne6ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniqueSurLePaquet() {
        List<Question> items = items();
        verifierLaFormeDesIndices(items);
        assertThat(items.stream().map(PaquetCe1NegationTest::indice).distinct().count())
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .isGreaterThan(1);
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansCeQueLEnfantLit() {
        verifierLeLexiqueExclu(items());
    }

    @Test
    void ne7LaMajusculeNeDesigneJamaisLaBonneReponse() {
        verifierQueLaMajusculeNeDesignePasLaReponse(items());
    }

    @Test
    void k3LEntreeDuCatalogueEstUniqueSonTitreNePorteNiNiveauNiEspaceOrdinaire() {
        verifierLEntreeDuCatalogue(lire(FICHIER_CATALOGUE));
    }

    /** ADR-0013 obligation 3 : le jeu fautif de NEG-CE1-1, quatre items vers la negative et aucun vers l'affirmative. */
    @Test
    void leJeuFautifCommiteEstRefuseSansItemVersLAffirmative() {
        List<Question> items = itemsFautifs("ce1-negation-fautif-negatifs");

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NEG-CE1-1")
                .hasMessageContaining("0 items vers l'affirmative sur 4");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de NEG-CE1-1, quatre items vers l'affirmative et aucun vers la negative. */
    @Test
    void leJeuFautifCommiteEstRefuseSansItemVersLaNegative() {
        List<Question> items = itemsFautifs("ce1-negation-fautif-affirmatifs");

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NEG-CE1-1")
                .hasMessageContaining("0 items vers la négative sur 4");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de NEG-CE1-1, un enonce qui ne demande ni l'une ni l'autre. */
    @Test
    void leJeuFautifCommiteEstRefuseUnEnonceQuiNeDemandeNiLUneNiLAutre() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NEG-CE1-1")
                .hasMessageContaining("Quelle est la phrase correcte")
                .hasMessageContaining("ne demande ni la phrase négative");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de NEG-CE1-1, le mot jamais. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeMotJamais() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierQueLaNegationEstNePasSeule(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NEG-CE1-1")
                .hasMessageContaining("Zoé aime la soupe")
                .hasMessageContaining("jamais");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de NEG-CE1-2, aucune elision sur cinq items. */
    @Test
    void leJeuFautifCommiteEstRefuseSansElision() {
        List<Question> items = itemsFautifs("ce1-negation-fautif-elision");

        assertThatThrownBy(() -> verifierLElision(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NEG-CE1-2")
                .hasMessageContaining("0 items avec élision sur 5");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-2, la bonne reponse la plus longue deux fois sur trois. */
    @Test
    void leJeuFautifCommiteEstRefuseSurLeBiaisDeLongueur() {
        List<Question> items = itemsFautifs("ce1-negation-fautif-longueur");

        assertThatThrownBy(() -> verifierLeBiaisDeLongueur(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("2 items où la bonne réponse est strictement la plus longue");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-2, un item sans commentaire de famille. */
    @Test
    void leJeuFautifCommiteEstRefuseUnItemSansFamilleNommee() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH
                + "/contenu/ce1-negation-fautif-sans-famille.yaml");

        assertThatThrownBy(() -> verifierLesFamillesNommees(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2")
                .hasMessageContaining("Léo aime les épinards")
                .hasMessageContaining("0 commentaires de famille");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de NE7, une majuscule seule sur la bonne reponse. */
    @Test
    void leJeuFautifCommiteEstRefuseSaMajusculeSeule() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierQueLaMajusculeNeDesignePasLaReponse(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NE7")
                .hasMessageContaining("Léo aime le chocolat")
                .hasMessageContaining("Léo n'aime pas le chocolat.");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de LON-CE1-5, une proposition de huit mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnePropositionDeHuitMots() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierLesPropositionsCourtes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("LON-CE1-5")
                .hasMessageContaining("Mia aime la peinture");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice de quinze mots. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceDeQuinzeMots() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierLaFormeDesIndices(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5")
                .hasMessageContaining("Relis bien toutes les phrases");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("Sami ne lit pas le livre.");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-4, le mot complement dans l'indice. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnMotDuLexiqueExclu() {
        List<Question> items = itemsFautifs("ce1-negation-fautif");

        assertThatThrownBy(() -> verifierLeLexiqueExclu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-4")
                .hasMessageContaining("Cherche le complément");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-1, un texte support. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH
                + "/contenu/ce1-negation-fautif-texte-support.yaml");

        assertThatThrownBy(() -> verifierLesChampsDeChaqueItem(contenu))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-1")
                .hasMessageContaining("Rex garde la maison");
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
