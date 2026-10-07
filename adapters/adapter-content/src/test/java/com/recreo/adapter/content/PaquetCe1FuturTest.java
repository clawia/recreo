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
 * S54-BR-FRA-CE1-18 ({@code squad/sprints/s54-branchement-des-premiers-paquets-et-reports-de-s53/planning.md}),
 * le paquet {@code ce1-futur}, ligne {@code FRA-CE1-18} - conjuguer au futur. Arbitrage prealable de
 * s48 ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.9, conditions
 * {@code FUT-CE1-1} a {@code FUT-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage prealable de s54, instance A
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md}, section 5) et avis de
 * faisabilite de theo-archi (T1).
 *
 * <p>Un item est une phrase a trou precedee du verbe a l'infinitif entre guillemets francais. La
 * bonne reponse et les familles de distracteurs ({@code FUT-CE1-3}, declarees dans un commentaire
 * YAML au-dessus de l'item) se relisent contre les tables du futur et du present ci-dessous, ecrites
 * dans ce test : une forme fausse y rougit. Ce que ce test ne lit pas : la vraisemblance d'un
 * distracteur comme erreur d'un enfant, un objet suppose chez l'enfant ({@code TRV-CE1-6}), l'accent
 * des homographes et ce qu'un indice laisse a l'enfant qui a choisi un distracteur ({@code TRV-CE1-5}).
 * Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1FuturTest {

    private static final String PAQUET = "ce1-futur";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1FuturTest";

    private static final double SEUIL_ETRE_AVOIR_MINIMUM = 0.25;

    private static final double SEUIL_PERSONNE_MINIMUM = 0.10;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    /** « verbe » : mot de temps, sujet ___ suite. Le sujet est le mot qui precede le trou. */
    private static final Pattern ENONCE = Pattern.compile(
            "^« (\\p{L}+) » : [^_]*?(\\p{L}+) ___ .+$", FLAGS);

    private static final Pattern DECLARATION = Pattern.compile(
            "#\\s*FUT-CE1-3:\\s*([^\\r\\n]+)\\R\\s*- enonce: \"([^\"]*)\"");

    /** Les six personnes, dans l'ordre des tables de formes. */
    private static final Map<String, Integer> PERSONNE_DU_PRONOM = Map.of(
            "je", 0, "tu", 1, "il", 2, "elle", 2, "nous", 3, "vous", 4, "ils", 5, "elles", 5);

    private static final Set<String> ETRE_ET_AVOIR = Set.of("être", "avoir");

    /** FUT-CE1-1 : les verbes exclus (modification orthographique du radical). */
    private static final Pattern VERBE_EXCLU = Pattern.compile(
            "(ger|cer|eler|eter|yer)$|^(acheter|lever)$", FLAGS);

    private static final List<String> PREMIER_GROUPE = List.of(
            "chanter", "jouer", "dessiner", "regarder", "parler", "laver", "écouter", "travailler",
            "danser");

    private static final Map<String, List<String>> FUTUR = new HashMap<>();

    private static final Map<String, List<String>> PRESENT = new HashMap<>();

    /** FUT-CE1-3 (c) : le radical regularise, tire de l'infinitif, que l'enfant prete a etre et avoir. */
    private static final Map<String, String> RADICAL_REGULARISE = Map.of("être", "êtr", "avoir", "avr");

    static {
        FUTUR.put("être", List.of("serai", "seras", "sera", "serons", "serez", "seront"));
        FUTUR.put("avoir", List.of("aurai", "auras", "aura", "aurons", "aurez", "auront"));
        PRESENT.put("être", List.of("suis", "es", "est", "sommes", "êtes", "sont"));
        PRESENT.put("avoir", List.of("ai", "as", "a", "avons", "avez", "ont"));
        for (String verbe : PREMIER_GROUPE) {
            List<String> terminaisonsDuFutur = List.of("ai", "as", "a", "ons", "ez", "ont");
            List<String> futur = new ArrayList<>();
            for (String terminaison : terminaisonsDuFutur) {
                futur.add(verbe + terminaison);
            }
            FUTUR.put(verbe, futur);
            String radical = verbe.substring(0, verbe.length() - 2);
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
        try (InputStream flux = PaquetCe1FuturTest.class.getResourceAsStream(chemin)) {
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

    private static Matcher lireLEnonce(Question item) {
        Matcher lecture = ENONCE.matcher(item.enonce());
        assertThat(lecture.matches())
                .as("'%s' : un verbe entre guillemets, un mot de temps, un sujet, puis le trou",
                        item.enonce())
                .isTrue();
        return lecture;
    }

    private static String verbeDe(Question item) {
        return lireLEnonce(item).group(1).toLowerCase(Locale.FRENCH);
    }

    private static String sujetDe(Question item) {
        return lireLEnonce(item).group(2).toLowerCase(Locale.FRENCH);
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

    /** FUT-CE1-3 : la bonne forme et la famille de chaque distracteur, relues contre les tables. */
    private static void verifierLesFormesEtLesFamilles(String source, List<Question> items) {
        Map<String, List<String>> declarees = famillesDeclarees(source);
        assertThat(declarees).as("FUT-CE1-3 : un item, une declaration").hasSize(items.size());
        for (Question item : items) {
            String verbe = verbeDe(item);
            assertThat(FUTUR).as("'%s' : verbe connu de la table", item.enonce()).containsKey(verbe);
            List<String> formes = FUTUR.get(verbe);
            int personne = PERSONNE_DU_PRONOM.get(sujetDe(item));
            assertThat(item.bonneReponse())
                    .as("'%s' : la forme du futur de '%s' à cette personne", item.enonce(), verbe)
                    .isEqualTo(formes.get(personne));
            List<String> familles = declarees.get(item.enonce());
            assertThat(familles).as("'%s' : une famille par distracteur (FUT-CE1-3)", item.enonce())
                    .hasSize(item.distracteurs().size());
            for (int i = 0; i < item.distracteurs().size(); i++) {
                String distracteur = item.distracteurs().get(i);
                switch (familles.get(i)) {
                    case "a" -> assertThat(formes)
                            .as("'%s' : '%s' (a) est le futur du même verbe à une autre personne"
                                    + " (FUT-CE1-3)", item.enonce(), distracteur)
                            .contains(distracteur);
                    case "b" -> assertThat(PRESENT.get(verbe))
                            .as("'%s' : '%s' (b) est le présent du même verbe (FUT-CE1-3)",
                                    item.enonce(), distracteur)
                            .contains(distracteur);
                    case "c" -> {
                        assertThat(ETRE_ET_AVOIR)
                                .as("'%s' : (c) ne vaut que pour être et avoir (FUT-CE1-3)",
                                        item.enonce())
                                .contains(verbe);
                        assertThat(distracteur)
                                .as("'%s' : '%s' (c) est un radical régularisé, tiré de l'infinitif"
                                        + " (FUT-CE1-3)", item.enonce(), distracteur)
                                .startsWith(RADICAL_REGULARISE.get(verbe));
                        assertThat(distracteur)
                                .as("'%s' : '%s' (c) n'est ni un futur ni un présent juste"
                                        + " (FUT-CE1-3)", item.enonce(), distracteur)
                                .isNotIn(formes)
                                .isNotIn(PRESENT.get(verbe));
                    }
                    default -> throw new AssertionError(
                            "'" + item.enonce() + "' : famille inconnue (FUT-CE1-3)");
                }
                assertThat(distracteur)
                        .as("'%s' : un seul acceptable, '%s' n'est pas la forme de ce sujet"
                                + " (FUT-CE1-3)", item.enonce(), distracteur)
                        .isNotEqualTo(formes.get(personne));
            }
        }
    }

    /**
     * TRV-CE1-2, arbitrage prealable de s54 instance A, section 1.1 : avec un mot de temps le present
     * reste une phrase correcte, c'est la consigne du jeu qui demande le futur. Le catalogue est lu
     * pour que la consigne ne puisse pas perdre ce mot sans que ce test rougisse.
     */
    private static void verifierQueLaConsigneNommeLeFutur(String catalogue) {
        Map<?, ?> corps = new Yaml().load(catalogue);
        List<String> consignes = new ArrayList<>();
        for (Object entree : (List<?>) corps.get("jeux")) {
            Map<?, ?> jeu = (Map<?, ?>) entree;
            if (PAQUET.equals(jeu.get("paquet"))) {
                consignes.add(String.valueOf(jeu.get("consigne")));
            }
        }
        assertThat(consignes).as("une seule entrée du catalogue sert le paquet %s", PAQUET)
                .hasSize(1);
        assertThat(consignes.get(0))
                .as("TRV-CE1-2 : la consigne du jeu demande le futur, le présent reste juste avec"
                        + " un mot de temps")
                .containsIgnoringCase("futur");
    }

    @Test
    void adr0081CePaquetDeclareFraCe118EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-18"));
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
    void fut1EtreEtAvoirSontLaCibleDUnQuartDesItemsAuMoinsEtAucunVerbeExcluNEstServi() {
        List<Question> items = items();
        long etreEtAvoir = items.stream().filter(i -> ETRE_ET_AVOIR.contains(verbeDe(i))).count();
        assertThat((double) etreEtAvoir / items.size())
                .as("FUT-CE1-1 : %d items sur être et avoir, sur %d", etreEtAvoir, items.size())
                .isGreaterThanOrEqualTo(SEUIL_ETRE_AVOIR_MINIMUM);
        for (Question item : items) {
            assertThat(VERBE_EXCLU.matcher(verbeDe(item)).find())
                    .as("'%s' : ni -ger, -cer, -eler, -eter, -yer, ni acheter, ni lever (FUT-CE1-1)",
                            item.enonce())
                    .isFalse();
        }
    }

    @Test
    void fut2LeSujetEstToujoursUnPronomPersonnelEtChaquePersonneEnPorteUnDixieme() {
        List<Question> items = items();
        Map<Integer, Long> parPersonne = new HashMap<>();
        for (Question item : items) {
            assertThat(PERSONNE_DU_PRONOM).as("'%s' : le sujet est un pronom personnel (FUT-CE1-2)",
                    item.enonce()).containsKey(sujetDe(item));
            parPersonne.merge(PERSONNE_DU_PRONOM.get(sujetDe(item)), 1L, Long::sum);
        }
        for (int personne = 0; personne < 6; personne++) {
            long compte = parPersonne.getOrDefault(personne, 0L);
            assertThat((double) compte / items.size())
                    .as("FUT-CE1-2 : personne %d sujet de %d items sur %d", personne + 1, compte,
                            items.size())
                    .isGreaterThanOrEqualTo(SEUIL_PERSONNE_MINIMUM);
        }
    }

    @Test
    void fut3LaBonneFormeEtLesFamillesDeclareesSontRelues() {
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
    void trv2LaConsigneDuJeuNommeLeFutur() {
        verifierQueLaConsigneNommeLeFutur(lire(FICHIER_CATALOGUE));
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
    void lon5ChaquePropositionEstUnSeulMotEtLEnonceTientEnUnePhrase() {
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
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item declare "chante" de
     * famille "a" (le futur a une autre personne) alors que "chante" est le present (FUT-CE1-3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaFamilleDeclareeFausse() {
        String chemin = CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-futur-fautif.yaml";
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-futur-fautif"));

        assertThatThrownBy(() -> verifierLesFormesEtLesFamilles(lireRessource(chemin), items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("FUT-CE1-3");
    }

    /**
     * ADR-0013 obligation 3 : le catalogue fautif de ce garde-fou. Sa consigne ne nomme pas le
     * futur (TRV-CE1-2).
     */
    @Test
    void leCatalogueFautifCommiteEstRefuseSurSaConsigneQuiNeNommePasLeFutur() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierQueLaConsigneNommeLeFutur(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-2");
    }
}
