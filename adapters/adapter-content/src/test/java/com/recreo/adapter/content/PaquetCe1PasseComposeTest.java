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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S54-BR-FRA-CE1-19 ({@code squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/planning.md}),
 * le paquet {@code ce1-passe-compose}, ligne {@code FRA-CE1-19} - conjuguer au passe compose avec
 * {@code avoir}. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.10, conditions
 * {@code PCO-CE1-1} a {@code PCO-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage prealable de s54, instance A
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md}, section 6) et avis de
 * faisabilite de theo-archi (T1).
 *
 * <p>Un item est une phrase a trou precedee du verbe a l'infinitif entre guillemets francais ; le
 * sujet est lu dans la bonne reponse, qui porte le pronom, l'auxiliaire et le participe. La bonne
 * reponse et les familles de distracteurs ({@code PCO-CE1-3}, declarees dans un commentaire YAML
 * au-dessus de l'item) se relisent contre les tables ci-dessous, ecrites dans ce test : une forme
 * fausse y rougit. Ce que ce test ne lit pas : la vraisemblance d'un distracteur comme erreur d'un
 * enfant, une bonne reponse que la classe corrige ({@code etre} suivi d'un lieu), l'accent des
 * participes et ce qu'un indice laisse a l'enfant qui a choisi un distracteur ({@code TRV-CE1-5},
 * hors du critere "deux mots"). Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1PasseComposeTest {

    private static final String PAQUET = "ce1-passe-compose";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1PasseComposeTest";

    private static final double SEUIL_INFINITIF_MINIMUM = 1.0 / 3.0;

    private static final double SEUIL_PERSONNE_MINIMUM = 0.10;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    /** « verbe » : mot de temps, ___ suite. Le sujet est dans la bonne reponse, pas dans l'enonce. */
    private static final Pattern ENONCE = Pattern.compile(
            "^« (\\p{L}+) » : [^_]*___ .+$", FLAGS);

    private static final Pattern DECLARATION = Pattern.compile(
            "#\\s*PCO-CE1-3:\\s*([^\\r\\n]+)\\R\\s*- enonce: \"([^\"]*)\"");

    /** Les six personnes, dans l'ordre des tables de formes. */
    private static final Map<String, Integer> PERSONNE_DU_PRONOM = Map.of(
            "je", 0, "tu", 1, "il", 2, "elle", 2, "nous", 3, "vous", 4, "ils", 5, "elles", 5);

    private static final List<String> AVOIR_AU_PRESENT =
            List.of("ai", "as", "a", "avons", "avez", "ont");

    /** PCO-CE1-1 : etre, avoir et dix verbes du premier groupe qui se conjuguent avec avoir. */
    private static final List<String> PREMIER_GROUPE = List.of(
            "chanter", "jouer", "regarder", "dessiner", "parler", "laver", "écouter", "travailler",
            "danser", "aider");

    private static final Map<String, String> PARTICIPE = new HashMap<>();

    private static final Map<String, List<String>> PRESENT = new HashMap<>();

    static {
        PARTICIPE.put("être", "été");
        PARTICIPE.put("avoir", "eu");
        PRESENT.put("être", List.of("suis", "es", "est", "sommes", "êtes", "sont"));
        PRESENT.put("avoir", List.of("ai", "as", "a", "avons", "avez", "ont"));
        for (String verbe : PREMIER_GROUPE) {
            String radical = verbe.substring(0, verbe.length() - 2);
            PARTICIPE.put(verbe, radical + "é");
            PRESENT.put(verbe, List.of(radical + "e", radical + "es", radical + "e",
                    radical + "ons", radical + "ez", radical + "ent"));
        }
    }

    private static final String ESPACES = "[\\s\\u00A0]";

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
        try (InputStream flux = PaquetCe1PasseComposeTest.class.getResourceAsStream(chemin)) {
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

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static String verbeDe(Question item) {
        Matcher lecture = ENONCE.matcher(item.enonce());
        assertThat(lecture.matches())
                .as("'%s' : un verbe entre guillemets, un mot de temps, puis le trou", item.enonce())
                .isTrue();
        return lecture.group(1).toLowerCase(Locale.FRENCH);
    }

    /** La bonne reponse est "pronom auxiliaire participe", avec l'elision de "j'". */
    private static String[] lireLaBonneReponse(Question item) {
        String reponse = item.bonneReponse();
        String pronom;
        String reste;
        if (reponse.startsWith("j'")) {
            pronom = "je";
            reste = reponse.substring(2);
        } else {
            int espace = reponse.indexOf(' ');
            assertThat(espace).as("'%s' : un pronom puis l'auxiliaire et le participe", reponse)
                    .isPositive();
            pronom = reponse.substring(0, espace);
            reste = reponse.substring(espace + 1);
        }
        String[] auxiliaireEtParticipe = reste.split(" ");
        assertThat(auxiliaireEtParticipe)
                .as("'%s' : pronom, auxiliaire, participe et rien d'autre (PCO-CE1-1)", reponse)
                .hasSize(2);
        return new String[] {pronom, auxiliaireEtParticipe[0], auxiliaireEtParticipe[1]};
    }

    private static String pronomDe(Question item) {
        return lireLaBonneReponse(item)[0].toLowerCase(Locale.FRENCH);
    }

    private static String avecElision(String pronom, String forme) {
        boolean voyelle = "aeiouyhéèêâîô".indexOf(Character.toLowerCase(forme.charAt(0))) >= 0;
        return pronom.equals("je") && voyelle ? "j'" + forme : pronom + " " + forme;
    }

    private static Map<String, List<String>> famillesDeclarees(String source) {
        Map<String, List<String>> declarees = new HashMap<>();
        Matcher declaration = DECLARATION.matcher(source);
        while (declaration.find()) {
            declarees.put(declaration.group(2),
                    Arrays.stream(declaration.group(1).split(",")).map(String::strip).toList());
        }
        return declarees;
    }

    /** PCO-CE1-3 : la bonne forme et la famille de chaque distracteur, relues contre les tables. */
    private static void verifierLesFormesEtLesFamilles(String source, List<Question> items) {
        Map<String, List<String>> declarees = famillesDeclarees(source);
        assertThat(declarees).as("PCO-CE1-3 : un item, une declaration").hasSize(items.size());
        for (Question item : items) {
            String verbe = verbeDe(item);
            assertThat(PARTICIPE).as("'%s' : verbe connu de la table, auxiliaire avoir (PCO-CE1-1)",
                    item.enonce()).containsKey(verbe);
            String pronom = pronomDe(item);
            assertThat(PERSONNE_DU_PRONOM).as("'%s' : le sujet est un pronom personnel (PCO-CE1-2)",
                    item.bonneReponse()).containsKey(pronom);
            int personne = PERSONNE_DU_PRONOM.get(pronom);
            String participe = PARTICIPE.get(verbe);
            String attendue = avecElision(pronom, AVOIR_AU_PRESENT.get(personne)) + " " + participe;
            assertThat(item.bonneReponse())
                    .as("'%s' : le passé composé de '%s' à cette personne", item.enonce(), verbe)
                    .isEqualTo(attendue);
            List<String> familles = declarees.get(item.enonce());
            assertThat(familles).as("'%s' : une famille par distracteur (PCO-CE1-3)", item.enonce())
                    .hasSize(item.distracteurs().size());
            for (int i = 0; i < item.distracteurs().size(); i++) {
                String distracteur = item.distracteurs().get(i);
                switch (familles.get(i)) {
                    case "a" -> assertThat(distracteur)
                            .as("'%s' : '%s' (a) est l'infinitif à la place du participe"
                                    + " (PCO-CE1-3)", item.enonce(), distracteur)
                            .isEqualTo(avecElision(pronom, AVOIR_AU_PRESENT.get(personne)) + " " + verbe);
                    case "b" -> assertThat(autresAuxiliaires(pronom, personne, participe))
                            .as("'%s' : '%s' (b) est l'auxiliaire à une autre personne, avec le"
                                    + " même pronom (PCO-CE1-3)", item.enonce(), distracteur)
                            .contains(distracteur);
                    case "c" -> assertThat(distracteur)
                            .as("'%s' : '%s' (c) est le présent du verbe (PCO-CE1-3)",
                                    item.enonce(), distracteur)
                            .isEqualTo(avecElision(pronom, PRESENT.get(verbe).get(personne)));
                    default -> throw new AssertionError(
                            "'" + item.enonce() + "' : famille inconnue (PCO-CE1-3)");
                }
                assertThat(distracteur)
                        .as("'%s' : un seul acceptable, '%s' n'est pas la bonne forme (PCO-CE1-3)",
                                item.enonce(), distracteur)
                        .isNotEqualTo(item.bonneReponse());
            }
        }
    }

    private static List<String> autresAuxiliaires(String pronom, int personne, String participe) {
        List<String> formes = new ArrayList<>();
        for (int autre = 0; autre < AVOIR_AU_PRESENT.size(); autre++) {
            if (autre != personne) {
                formes.add(avecElision(pronom, AVOIR_AU_PRESENT.get(autre)) + " " + participe);
            }
        }
        return formes;
    }

    /**
     * TRV-CE1-5, arbitrage prealable de s54 instance A, section 1.2 : "deux mots" est un critere que
     * l'infinitif et l'auxiliaire a une autre personne satisfont ("j'ai chanter"), l'enfant qui
     * s'est trompe compte et se trouve confirme. Aucun indice ne le donne.
     */
    private static void verifierQueAucunIndiceNeDonneLeCritereDeuxMots(List<Question> items) {
        for (Question item : items) {
            assertThat(item.indice().orElseThrow())
                    .as("'%s' : l'indice ne donne pas le critère « deux mots », qu'un distracteur"
                            + " satisfait (TRV-CE1-5)", item.enonce())
                    .doesNotContainIgnoringCase("deux mots");
        }
    }

    @Test
    void adr0081CePaquetDeclareFraCe119EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-19"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1AucunItemNePorteDeTexteSupportDIllustrationNiDeVoixHaute() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        for (Object item : (List<?>) corps.get("items")) {
            assertThat(((Map<?, ?>) item).keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : enonce, bonneReponse, distracteurs et indice seulement")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(items()).as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void pco2LeSujetEstToujoursUnPronomPersonnelEtChaquePersonneEnPorteUnDixieme() {
        List<Question> items = items();
        Map<Integer, Long> parPersonne = new HashMap<>();
        for (Question item : items) {
            assertThat(PERSONNE_DU_PRONOM).as("'%s' : le sujet est un pronom personnel (PCO-CE1-2)",
                    item.bonneReponse()).containsKey(pronomDe(item));
            parPersonne.merge(PERSONNE_DU_PRONOM.get(pronomDe(item)), 1L, Long::sum);
        }
        for (int personne = 0; personne < 6; personne++) {
            long compte = parPersonne.getOrDefault(personne, 0L);
            assertThat((double) compte / items.size())
                    .as("PCO-CE1-2 : personne %d sujet de %d items sur %d", personne + 1, compte,
                            items.size())
                    .isGreaterThanOrEqualTo(SEUIL_PERSONNE_MINIMUM);
        }
    }

    @Test
    void pco3LaBonneFormeEtLesFamillesDeclareesSontRelues() {
        verifierLesFormesEtLesFamilles(lire(FICHIER_CONTENU), items());
        for (Question item : items()) {
            assertThat(item.distracteurs()).as("'%s' : deux distracteurs", item.enonce())
                    .hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
            assertThat(item.enonce().split("___", -1)).as("'%s' : un seul trou", item.enonce())
                    .hasSize(2);
        }
    }

    @Test
    void pco3LInfinitifALaPlaceDuParticipeEstUnDistracteurDAuMoinsUnTiersDesItems() {
        String source = lire(FICHIER_CONTENU);
        List<Question> items = items();
        Map<String, List<String>> declarees = famillesDeclarees(source);
        long avecInfinitif = items.stream()
                .filter(i -> declarees.get(i.enonce()).contains("a")).count();
        assertThat((double) avecInfinitif / items.size())
                .as("PCO-CE1-3 (a) : %d items sur %d portent l'infinitif à la place du participe",
                        avecInfinitif, items.size())
                .isGreaterThanOrEqualTo(SEUIL_INFINITIF_MINIMUM);
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat((double) plusLongue / items.size())
                .as("TRV-CE1-2 : %d items ou la bonne reponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
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
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsNeDonnePasLaReponseNiNeRenvoieALOreilleEtNEstPasUniforme() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots", indice)
                    .hasSizeLessThanOrEqualTo(12);
            assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(item.bonneReponse())
                    + "(?!\\p{L})", FLAGS).matcher(indice).find())
                    .as("'%s' : l'indice ne cite pas '%s' (TRV-CE1-5)", indice,
                            item.bonneReponse())
                    .isFalse();
            assertThat(RENVOI_A_L_OREILLE.matcher(indice).find())
                    .as("'%s' : l'indice ne renvoie pas à l'oreille (TRV-CE1-5)", indice)
                    .isFalse();
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void trv5AucunIndiceNeDonneLeCritereDeuxMotsQueSatisfontLesDistracteurs() {
        verifierQueAucunIndiceNeDonneLeCritereDeuxMots(items());
    }

    @Test
    void lon5ChaquePropositionTientEnSixMotsEtLEnonceEnVingtMots() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
                assertThat(proposition).as("une seule phrase").doesNotContain(".", "?", "!");
            }
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item declare "je chante" de
     * famille "a" (l'infinitif a la place du participe) alors que "je chante" est le present
     * (PCO-CE1-3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaFamilleDeclareeFausse() {
        String chemin = CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-passe-compose-fautif.yaml";
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-passe-compose-fautif"));

        assertThatThrownBy(() -> verifierLesFormesEtLesFamilles(lireRessource(chemin), items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PCO-CE1-3");
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif du critere "deux mots". Son seul item, juste sur ses
     * familles, porte l'indice retire du paquet par l'arbitrage de s54 (TRV-CE1-5).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonIndiceQuiDonneLeCritereDeuxMots() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-passe-compose-fautif-indice"));

        assertThatThrownBy(() -> verifierQueAucunIndiceNeDonneLeCritereDeuxMots(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5");
    }
}
