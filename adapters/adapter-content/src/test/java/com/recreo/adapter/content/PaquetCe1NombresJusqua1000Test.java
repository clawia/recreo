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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-MAT-CE1-01 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-nombres-jusqu-a-1000}, ligne {@code MAT-CE1-01} - lire, ecrire et decomposer
 * les nombres jusqu'a 1000, plafond {@code en cours}. Arbitrage prealable de s43
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md}, section 2,
 * conditions {@code NUM-CE1-1} a {@code NUM-CE1-7}) et reponses de nadia-prof du palier 3
 * ({@code reponses-nadia-questions-stories-palier-3.md}, section 1 : en mathematiques CE1 ne valent
 * ni {@code LON-CE1-3} a {@code -5} ni {@code TRV-CE1-1} a {@code -7}).
 *
 * <p>Les designations en lettres sont recalculees ici par une convention classique constante
 * (traits d'union sous cent seulement, "et" sur 21, 31, 41, 51, 61 et 71, "cents" au pluriel
 * seulement quand il termine le nombre, "quatre-vingts" seulement en fin de nombre), jamais
 * recopiees du paquet. Les comptes de {@code NUM-CE1-1} et {@code NUM-CE1-4} a {@code NUM-CE1-7}
 * sont refaits sur les champs lus par le chargeur, jamais sur les commentaires du fichier.
 *
 * <p>Ce que ce test ne lit pas : que le verbe "denombrer" ne soit servi par aucun item (un test
 * voit un champ, pas un verbe de la ligne), l'exactitude des bonnes reponses de decomposition,
 * qu'un indice dise ou regarder sans donner la reponse, que les distracteurs soient plausibles.
 * Cela reste a la lecture et au jeu de nadia-prof.
 */
class PaquetCe1NombresJusqua1000Test {

    private static final String PAQUET = "ce1-nombres-jusqu-a-1000";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1NombresJusqua1000Test";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 22;

    private static final String NBSP = " ";

    private static final String DEBUT_DE_LECTURE = "Lis ce nombre" + NBSP + ": ";

    private static final String DEBUT_D_ECRITURE = "Écris en chiffres" + NBSP + ": ";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;

    private static final Pattern CHIFFRES = Pattern.compile("\\d+");

    /** NUM-CE1-5 : le mot decomposer, sous toutes ses formes, n'est jamais lu par l'enfant. */
    private static final Pattern DECOMPOSER = Pattern.compile("d[ée]compos", FLAGS);

    /** NUM-CE1-4 : le vocabulaire centaines, dizaines, unites. */
    private static final Pattern VOCABULAIRE_DE_POSITION = Pattern.compile("centaine|dizaine|unité", FLAGS);

    private static final String[] UNITES = {"zéro", "un", "deux", "trois", "quatre", "cinq", "six",
        "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize"};

    private static final String[] DIZAINES = {"", "", "vingt", "trente", "quarante", "cinquante",
        "soixante"};

    /** Designation en lettres vers valeur, de 0 a 1000 : une ecriture hors de cette table est fautive. */
    private static final Map<String, Integer> VALEURS_EN_LETTRES = valeursEnLettres();

    private static Map<String, Integer> valeursEnLettres() {
        Map<String, Integer> valeurs = new HashMap<>();
        for (int n = 0; n <= 1000; n++) {
            valeurs.put(lettres(n), n);
        }
        return valeurs;
    }

    private static String lettres(int n) {
        if (n == 1000) {
            return "mille";
        }
        if (n >= 100) {
            int centaines = n / 100;
            int reste = n % 100;
            String tete = centaines == 1 ? "cent" : UNITES[centaines] + " cent" + (reste == 0 ? "s" : "");
            return reste == 0 ? tete : tete + " " + sousCent(reste);
        }
        return sousCent(n);
    }

    private static String sousCent(int n) {
        if (n <= 16) {
            return UNITES[n];
        }
        if (n < 20) {
            return "dix-" + UNITES[n - 10];
        }
        if (n >= 70 && n < 80) {
            return n == 71 ? "soixante et onze" : "soixante-" + sousCent(n - 60);
        }
        if (n == 80) {
            return "quatre-vingts";
        }
        if (n > 80 && n < 90) {
            return "quatre-vingt-" + UNITES[n - 80];
        }
        if (n >= 90) {
            return "quatre-vingt-" + sousCent(n - 80);
        }
        int dizaines = n / 10;
        int unites = n % 10;
        if (unites == 0) {
            return DIZAINES[dizaines];
        }
        return unites == 1 ? DIZAINES[dizaines] + " et un" : DIZAINES[dizaines] + "-" + UNITES[unites];
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1NombresJusqua1000Test.class.getResourceAsStream(chemin)) {
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
                .chargerPaquet("ce1-nombres-fautif"));
    }

    /** L'item fautif dont l'enonce commence par ce debut, seul, pour mordre un verificateur a la fois. */
    private static List<Question> fautif(String debutDEnonce) {
        return itemsFautifs().stream().filter(item -> item.enonce().startsWith(debutDEnonce)).toList();
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

    private static boolean estUneLecture(Question item) {
        return item.enonce().startsWith(DEBUT_DE_LECTURE);
    }

    private static boolean estUneEcriture(Question item) {
        return item.enonce().startsWith(DEBUT_D_ECRITURE);
    }

    private static List<Question> decompositions(List<Question> items) {
        return items.stream().filter(item -> !estUneLecture(item) && !estUneEcriture(item)).toList();
    }

    /** Le nombre que porte un item : le plus grand entier de son enonce et de sa bonne reponse. */
    private static long nombreDe(Question item) {
        long plusGrand = 0;
        Matcher trouve = CHIFFRES.matcher(item.enonce() + " " + item.bonneReponse());
        while (trouve.find()) {
            plusGrand = Math.max(plusGrand, Long.parseLong(trouve.group()));
        }
        return plusGrand;
    }

    /** NUM-CE1-1 : aucun nombre au-dessus de 1000 dans ce que l'enfant lit. */
    private static void verifierLaBorne(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                Matcher trouve = CHIFFRES.matcher(champ);
                while (trouve.find()) {
                    long valeur = Long.parseLong(trouve.group());
                    assertThat(valeur)
                            .as("NUM-CE1-1 : '%s' porte %d, au-dessus de 1000", item.enonce(), valeur)
                            .isLessThanOrEqualTo(1000L);
                }
            }
        }
    }

    /** NUM-CE1-1 : au moins la moitie des items portent un nombre de trois chiffres. */
    private static void verifierLaMoitieATroisChiffres(List<Question> items) {
        long tenus = items.stream().filter(item -> nombreDe(item) >= 100).count();
        assertThat(2 * tenus)
                .as("NUM-CE1-1 : au moins la moitié des %d items porte un nombre de trois chiffres "
                        + "(%d tenus)", items.size(), tenus)
                .isGreaterThanOrEqualTo(items.size());
    }

    /** NUM-CE1-3 : lire et ecrire sont servis dans le meme paquet. */
    private static void verifierLesDeuxVerbes(List<Question> items) {
        assertThat(items.stream().filter(PaquetCe1NombresJusqua1000Test::estUneLecture).count())
                .as("NUM-CE1-3 : au moins un item « lire » (chiffres vers lettres)")
                .isGreaterThanOrEqualTo(1);
        assertThat(items.stream().filter(PaquetCe1NombresJusqua1000Test::estUneEcriture).count())
                .as("NUM-CE1-3 : au moins un item « écrire » (lettres vers chiffres)")
                .isGreaterThanOrEqualTo(1);
    }

    /**
     * NUM-CE1-3 : la designation en lettres est ecrite sans faute et constante, et les distracteurs
     * d'un item « lire » sont des designations sans faute d'une autre valeur.
     */
    private static void verifierLesDesignations(List<Question> items) {
        for (Question item : items) {
            if (estUneLecture(item)) {
                int nombre = Integer.parseInt(
                        item.enonce().substring(DEBUT_DE_LECTURE.length()).replace(".", ""));
                assertThat(item.bonneReponse())
                        .as("NUM-CE1-3 : '%s' attend « %s »", item.enonce(), lettres(nombre))
                        .isEqualTo(lettres(nombre));
                for (String distracteur : item.distracteurs()) {
                    assertThat(VALEURS_EN_LETTRES)
                            .as("NUM-CE1-3 : « %s » (distracteur de '%s') n'est pas une écriture en "
                                    + "lettres sans faute", distracteur, item.enonce())
                            .containsKey(distracteur);
                }
            } else if (estUneEcriture(item)) {
                String litteral = item.enonce().substring(DEBUT_D_ECRITURE.length()).replaceAll("\\.$", "");
                assertThat(VALEURS_EN_LETTRES)
                        .as("NUM-CE1-3 : « %s » (enoncé '%s') n'est pas une écriture en lettres sans "
                                + "faute", litteral, item.enonce())
                        .containsKey(litteral);
                assertThat(item.bonneReponse())
                        .as("NUM-CE1-3 : '%s' attend %d", item.enonce(), VALEURS_EN_LETTRES.get(litteral))
                        .isEqualTo(String.valueOf(VALEURS_EN_LETTRES.get(litteral)));
            }
        }
    }

    /** NUM-CE1-4 : au moins quatre decompositions, dont la moitie au vocabulaire et a trois chiffres. */
    private static void verifierLesDecompositions(List<Question> items) {
        List<Question> decompositions = decompositions(items);
        assertThat(decompositions.size())
                .as("NUM-CE1-4 : au moins quatre items de décomposition")
                .isGreaterThanOrEqualTo(4);
        long vocabulaire = decompositions.stream()
                .filter(item -> VOCABULAIRE_DE_POSITION.matcher(item.enonce()).find()).count();
        assertThat(2 * vocabulaire)
                .as("NUM-CE1-4 : au moins la moitié des %d décompositions emploie centaines, dizaines "
                        + "ou unités (%d tenues)", decompositions.size(), vocabulaire)
                .isGreaterThanOrEqualTo(decompositions.size());
        long troisChiffres = decompositions.stream().filter(item -> nombreDe(item) >= 100).count();
        assertThat(2 * troisChiffres)
                .as("NUM-CE1-4 : au moins la moitié des %d décompositions porte un nombre de trois "
                        + "chiffres (%d tenues)", decompositions.size(), troisChiffres)
                .isGreaterThanOrEqualTo(decompositions.size());
    }

    /** NUM-CE1-5 : le mot decomposer n'apparait dans aucun champ que l'enfant lit. */
    private static void verifierQueDecomposerNEstJamaisLu(List<Question> items) {
        for (Question item : items) {
            for (String champ : champsLus(item)) {
                assertThat(DECOMPOSER.matcher(champ).find())
                        .as("NUM-CE1-5 : '%s' fait lire le mot décomposer (« %s »)", item.enonce(), champ)
                        .isFalse();
            }
        }
    }

    /** NUM-CE1-6 : au moins deux nombres a zero intercalaire ou final, au moins un de la tranche 60-99. */
    private static void verifierLesZerosEtLaTranche(List<Question> items) {
        long zeros = items.stream().map(PaquetCe1NombresJusqua1000Test::nombreDe)
                .filter(n -> n >= 100 && (n % 10 == 0 || (n / 10) % 10 == 0)).count();
        assertThat(zeros)
                .as("NUM-CE1-6 : au moins deux items dont le chiffre des dizaines ou des unités est zéro")
                .isGreaterThanOrEqualTo(2);
        long tranche = items.stream().map(PaquetCe1NombresJusqua1000Test::nombreDe)
                .filter(n -> n >= 100 && n % 100 >= 60).count();
        assertThat(tranche)
                .as("NUM-CE1-6 : au moins un item dont les deux derniers chiffres sont entre 60 et 99")
                .isGreaterThanOrEqualTo(1);
    }

    /**
     * NUM-CE1-7 : trois propositions distinctes ; meme nombre de chiffres, ou une ecriture en lettres
     * dont la longueur ne differe que d'un mot.
     */
    private static void verifierLesPropositions(List<Question> items) {
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("NUM-CE1-7 : '%s' a trois propositions distinctes", item.enonce()).hasSize(3);
            if (estUneLecture(item)) {
                List<Integer> mots = propositions(item).stream()
                        .map(proposition -> proposition.strip().split("\\s+").length).toList();
                int ecart = mots.stream().max(Integer::compare).orElseThrow()
                        - mots.stream().min(Integer::compare).orElseThrow();
                assertThat(ecart)
                        .as("NUM-CE1-7 : les écritures en lettres de '%s' ont %s mots, plus d'un mot "
                                + "d'écart", item.enonce(), mots)
                        .isLessThanOrEqualTo(1);
            } else {
                Set<Integer> longueurs = new HashSet<>();
                for (String proposition : propositions(item)) {
                    Matcher nombre = CHIFFRES.matcher(proposition);
                    assertThat(nombre.find())
                            .as("NUM-CE1-7 : « %s » (proposition de '%s') ne porte aucun nombre",
                                    proposition, item.enonce())
                            .isTrue();
                    longueurs.add(nombre.group().length());
                }
                assertThat(longueurs)
                        .as("NUM-CE1-7 : les propositions de '%s' n'ont pas le même nombre de chiffres",
                                item.enonce())
                        .hasSize(1);
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
     * manches (DEC-0121) ; son titre ne porte pas le niveau (ADR-0078), l'espace avant un signe
     * double est insecable (ADR-0067) et ni le titre ni la consigne ne font lire le mot decomposer
     * (NUM-CE1-5).
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
        assertThat(DECOMPOSER.matcher(titre + " " + consigne).find())
                .as("NUM-CE1-5 : le titre et la consigne ne font pas lire le mot décomposer")
                .isFalse();
    }

    // ----- le paquet et son entree de catalogue

    @Test
    void adr0081CePaquetDeclareMatCe101EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-01"));
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
    void nb1LePaquetGardeSesVingtDeuxItemsChacunATroisPropositionsEtPlusDeDixManches() {
        List<Question> items = items();
        assertThat(items).hasSize(NOMBRE_D_ITEMS_ATTENDU);
        assertThat(items.size()).as("FER-2 : strictement plus d'items que les dix manches")
                .isGreaterThan(10);
        for (Question item : items) {
            assertThat(item.distracteurs()).as("deux distracteurs pour '%s'", item.enonce()).hasSize(2);
        }
    }

    @Test
    void nb8AucunItemNePorteDeTexteSupportNiDeVoix() {
        verifierLesChampsServis(lire(FICHIER_CONTENU));
    }

    // ----- les conditions NUM-CE1-1 a NUM-CE1-7

    @Test
    void num1AucunNombreNeDepasseMille() {
        verifierLaBorne(items());
    }

    @Test
    void num1LaMoitieAuMoinsDesItemsPorteUnNombreDeTroisChiffres() {
        verifierLaMoitieATroisChiffres(items());
    }

    @Test
    void num3LireEtEcrireSontServisDansLeMemePaquet() {
        verifierLesDeuxVerbes(items());
    }

    @Test
    void num3ChaqueDesignationEnLettresEstSansFauteEtLesDistracteursDeLectureAussi() {
        verifierLesDesignations(items());
    }

    @Test
    void num4LesDecompositionsSontAssezNombreusesVocabulaireEtTroisChiffres() {
        verifierLesDecompositions(items());
    }

    @Test
    void num5LeMotDecomposerNApparaitDansAucunChampLu() {
        verifierQueDecomposerNEstJamaisLu(items());
    }

    @Test
    void num6LesZerosEtLaTrancheSoixanteQuatreVingtDixNeufSontServis() {
        verifierLesZerosEtLaTranche(items());
    }

    @Test
    void num7LesTroisPropositionsDUnItemOntLaMemeLongueur() {
        verifierLesPropositions(items());
    }

    // ----- ADR-0013 obligation 3 : un jeu fautif commite mord chaque verificateur

    @Test
    void leJeuFautifCommiteEstRefuseSurUnNombreAuDessusDeMille() {
        List<Question> items = fautif(DEBUT_DE_LECTURE + "1200");

        assertThatThrownBy(() -> verifierLaBorne(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-1")
                .hasMessageContaining("1200");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnItemSansTroisChiffres() {
        List<Question> items = fautif(DEBUT_DE_LECTURE + "45");

        assertThatThrownBy(() -> verifierLaMoitieATroisChiffres(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-1")
                .hasMessageContaining("trois chiffres");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPaquetQuiNeServeQueLEcriture() {
        List<Question> items = fautif(DEBUT_D_ECRITURE + "sept cent trois");

        assertThatThrownBy(() -> verifierLesDeuxVerbes(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-3")
                .hasMessageContaining("lire");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneBonneReponseMalEcriteEnLettres() {
        List<Question> items = fautif(DEBUT_DE_LECTURE + "305");

        assertThatThrownBy(() -> verifierLesDesignations(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-3")
                .hasMessageContaining("trois cent cinq");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurMalEcritEnLettres() {
        List<Question> items = fautif(DEBUT_DE_LECTURE + "620");

        assertThatThrownBy(() -> verifierLesDesignations(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-3")
                .hasMessageContaining("six cents vingt");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUneEcritureEnChiffresQuiNEstPasLaValeurDesLettres() {
        List<Question> items = fautif(DEBUT_D_ECRITURE + "deux cent quarante-sept");

        assertThatThrownBy(() -> verifierLesDesignations(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-3")
                .hasMessageContaining("attend 247");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurTropPeuDeDecompositions() {
        List<Question> items = fautif("D");

        assertThatThrownBy(() -> verifierLesDecompositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-4")
                .hasMessageContaining("quatre items");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurLeMotDecomposerLuParLEnfant() {
        List<Question> items = fautif("Décompose");

        assertThatThrownBy(() -> verifierQueDecomposerNEstJamaisLu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-5")
                .hasMessageContaining("décomposer");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnPaquetSansZeroNiTranche() {
        List<Question> items = fautif(DEBUT_DE_LECTURE + "45");

        assertThatThrownBy(() -> verifierLesZerosEtLaTranche(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-6");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesEcrituresEnLettresDeDeuxMotsDEcart() {
        List<Question> items = fautif(DEBUT_DE_LECTURE + "407");

        assertThatThrownBy(() -> verifierLesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-7")
                .hasMessageContaining("mots");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurDesPropositionsDeNombresDeChiffresDifferents() {
        List<Question> items = fautif(DEBUT_D_ECRITURE + "sept cent trois");

        assertThatThrownBy(() -> verifierLesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-7")
                .hasMessageContaining("nombre de chiffres");
    }

    @Test
    void leJeuFautifCommiteEstRefuseSurUnTexteSupport() {
        String contenu = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-nombres-fautif.yaml");

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
    void leCatalogueFautifDontLaConsigneFaitLireDecomposerEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-consigne-decomposer.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("NUM-CE1-5");
    }

    @Test
    void leCatalogueFautifQuiNePorteQueCinqManchesEstRefuse() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue-cinq-manches.yaml");

        assertThatThrownBy(() -> verifierLEntreeDuCatalogue(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("nombreDeManches");
    }
}
