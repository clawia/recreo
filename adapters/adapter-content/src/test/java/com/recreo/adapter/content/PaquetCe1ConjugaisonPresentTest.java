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
 * S52-FRA-PRESENT ({@code docs/backlog/backlog.md}, sprint 52), le paquet
 * {@code ce1-conjugaison-present}, ligne {@code FRA-CE1-17} - conjuguer au present. Arbitrage
 * pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.8, conditions
 * {@code PRS-CE1-1} a {@code PRS-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), reponse de nadia-prof a Q1
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md}, section 1) et
 * avis de faisabilite de theo-archi
 * ({@code squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md}, V2).
 *
 * <p>Un item est une phrase a trou precedee du verbe a l'infinitif entre guillemets francais. La
 * bonne reponse et les familles de distracteurs ({@code PRS-CE1-3}, declarees dans un commentaire
 * YAML au-dessus de l'item) se relisent contre la table des formes du present ci-dessous, ecrite
 * dans ce test : une forme fausse y rougit. Ce que ce test ne lit pas : la vraisemblance d'un
 * distracteur comme erreur d'un enfant, hors des cinq formes regularisees admises d'avance par
 * nadia-prof (Q1). Cela reste a sa lecture.
 */
class PaquetCe1ConjugaisonPresentTest {

    private static final String PAQUET = "ce1-conjugaison-present";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1ConjugaisonPresentTest";

    private static final double SEUIL_SIX_VERBES_MINIMUM = 0.50;

    private static final double SEUIL_PERSONNE_MINIMUM = 0.10;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    /** « verbe » : sujet ___ suite. Le sujet est lu, jamais deduit. */
    private static final Pattern ENONCE = Pattern.compile(
            "^« (\\p{L}+) » : (\\p{L}+) ___ .+$", FLAGS);

    private static final Pattern DECLARATION = Pattern.compile(
            "#\\s*PRS-CE1-3:\\s*([^\\r\\n]+)\\R\\s*- enonce: \"([^\"]*)\"");

    /** Les six personnes, dans l'ordre de la table des formes. */
    private static final Map<String, Integer> PERSONNE_DU_PRONOM = Map.of(
            "je", 0, "tu", 1, "il", 2, "elle", 2, "nous", 3, "vous", 4, "ils", 5, "elles", 5);

    private static final Set<String> SIX_VERBES = Set.of(
            "être", "avoir", "aller", "faire", "dire", "venir");

    /** PRS-CE1-1 : les verbes exclus (modification orthographique du radical). */
    private static final Pattern VERBE_EXCLU = Pattern.compile(
            "(ger|cer|eler|eter|yer)$|^(acheter|lever)$", FLAGS);

    /** Q1 : les cinq formes regularisees admises d'avance par nadia-prof. */
    private static final Set<String> FORMES_B_ADMISES = Set.of(
            "disez", "faisez", "faisent", "allent", "venent");

    private static final Map<String, List<String>> PRESENT = new HashMap<>();

    static {
        PRESENT.put("être", List.of("suis", "es", "est", "sommes", "êtes", "sont"));
        PRESENT.put("avoir", List.of("ai", "as", "a", "avons", "avez", "ont"));
        PRESENT.put("aller", List.of("vais", "vas", "va", "allons", "allez", "vont"));
        PRESENT.put("faire", List.of("fais", "fais", "fait", "faisons", "faites", "font"));
        PRESENT.put("dire", List.of("dis", "dis", "dit", "disons", "dites", "disent"));
        PRESENT.put("venir", List.of("viens", "viens", "vient", "venons", "venez", "viennent"));
        for (String verbe : List.of("écouter", "regarder", "parler", "dessiner", "travailler", "laver")) {
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

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1ConjugaisonPresentTest.class.getResourceAsStream(chemin)) {
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
                .as("'%s' : un verbe entre guillemets, puis un sujet, puis le trou", item.enonce())
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

    /** PRS-CE1-3 : la bonne forme et la famille de chaque distracteur, relues contre la table. */
    private static void verifierLesFormesEtLesFamilles(String source, List<Question> items) {
        Map<String, List<String>> declarees = famillesDeclarees(source);
        assertThat(declarees).as("PRS-CE1-3 : un item, une declaration").hasSize(items.size());
        for (Question item : items) {
            String verbe = verbeDe(item);
            assertThat(PRESENT).as("'%s' : verbe connu de la table", item.enonce())
                    .containsKey(verbe);
            List<String> formes = PRESENT.get(verbe);
            int personne = PERSONNE_DU_PRONOM.get(sujetDe(item));
            assertThat(item.bonneReponse())
                    .as("'%s' : la forme du présent de '%s' à cette personne", item.enonce(), verbe)
                    .isEqualTo(formes.get(personne));
            List<String> familles = declarees.get(item.enonce());
            assertThat(familles).as("'%s' : une famille par distracteur (PRS-CE1-3)", item.enonce())
                    .hasSize(item.distracteurs().size());
            for (int i = 0; i < item.distracteurs().size(); i++) {
                String distracteur = item.distracteurs().get(i);
                switch (familles.get(i)) {
                    case "a" -> assertThat(formes)
                            .as("'%s' : '%s' (a) est le même verbe à une autre personne (PRS-CE1-3)",
                                    item.enonce(), distracteur)
                            .contains(distracteur);
                    case "b" -> assertThat(FORMES_B_ADMISES)
                            .as("'%s' : '%s' (b) est l'une des cinq formes admises d'avance"
                                    + " (PRS-CE1-3, Q1)", item.enonce(), distracteur)
                            .contains(distracteur);
                    case "c" -> assertThat(distracteur)
                            .as("'%s' : '%s' (c) est l'infinitif (PRS-CE1-3)", item.enonce(),
                                    distracteur)
                            .isEqualTo(verbe);
                    default -> throw new AssertionError(
                            "'" + item.enonce() + "' : famille inconnue (PRS-CE1-3)");
                }
                assertThat(distracteur)
                        .as("'%s' : un seul acceptable, '%s' n'est pas la forme de ce sujet"
                                + " (PRS-CE1-3)", item.enonce(), distracteur)
                        .isNotEqualTo(formes.get(personne));
            }
        }
    }

    @Test
    void adr0081CePaquetDeclareFraCe117EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-17"));
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
    void prs1LesSixVerbesSontLaCibleDeLaMoitieDesItemsEtAucunVerbeExcluNEstServi() {
        List<Question> items = items();
        long six = items.stream().filter(i -> SIX_VERBES.contains(verbeDe(i))).count();
        assertThat((double) six / items.size())
                .as("PRS-CE1-1 : %d items sur les six verbes, sur %d", six, items.size())
                .isGreaterThanOrEqualTo(SEUIL_SIX_VERBES_MINIMUM);
        for (Question item : items) {
            assertThat(VERBE_EXCLU.matcher(verbeDe(item)).find())
                    .as("'%s' : ni -ger, -cer, -eler, -eter, -yer, ni acheter, ni lever (PRS-CE1-1)",
                            item.enonce())
                    .isFalse();
        }
    }

    @Test
    void prs2LeSujetEstToujoursUnPronomPersonnelEtChaquePersonneEnPorteUnDixieme() {
        List<Question> items = items();
        Map<Integer, Long> parPersonne = new HashMap<>();
        for (Question item : items) {
            assertThat(PERSONNE_DU_PRONOM).as("'%s' : le sujet est un pronom personnel (PRS-CE1-2)",
                    item.enonce()).containsKey(sujetDe(item));
            parPersonne.merge(PERSONNE_DU_PRONOM.get(sujetDe(item)), 1L, Long::sum);
        }
        for (int personne = 0; personne < 6; personne++) {
            long compte = parPersonne.getOrDefault(personne, 0L);
            assertThat((double) compte / items.size())
                    .as("PRS-CE1-2 : personne %d sujet de %d items sur %d", personne + 1, compte,
                            items.size())
                    .isGreaterThanOrEqualTo(SEUIL_PERSONNE_MINIMUM);
        }
    }

    @Test
    void prs3LaBonneFormeEtLesFamillesDeclareesSontRelues() {
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

    /**
     * TRV-CE1-5, refus de nadia-prof du 2026-10-06 : la bonne reponse et un distracteur qui se
     * prononcent de meme ne se departagent pas a l'oreille. La table est celle des paires du paquet ;
     * une paire neuve s'y ajoute.
     */
    private static final List<Set<String>> HOMOPHONES = List.of(
            Set.of("parlez", "parler", "parle", "parlent"), Set.of("dis", "dit"),
            Set.of("es", "est"), Set.of("a", "as"), Set.of("va", "vas"),
            Set.of("fais", "fait"), Set.of("viens", "vient"), Set.of("allez", "aller"),
            Set.of("regardez", "regarder"), Set.of("dessine", "dessines", "dessinent"),
            Set.of("écoute", "écoutes", "écoutent"), Set.of("lave", "laves", "lavent"),
            Set.of("travaille", "travailles", "travaillent"));

    private static final String INDICE_A_L_OREILLE =
            "Lis la phrase dans ta tête avec chaque proposition.";

    @Test
    void trv5AucunIndiceNeRenvoieALEcouteSurUnItemQuiADesDistracteursHomophones() {
        for (Question item : items()) {
            boolean homophone = HOMOPHONES.stream().anyMatch(paire -> paire.contains(item.bonneReponse())
                    && item.distracteurs().stream().anyMatch(paire::contains));
            if (homophone) {
                assertThat(item.indice().orElseThrow())
                        .as("'%s' : la bonne réponse '%s' a un distracteur qui se prononce de même,"
                                + " l'indice ne renvoie pas à l'écoute (TRV-CE1-5)", item.enonce(),
                                item.bonneReponse())
                        .isNotEqualTo(INDICE_A_L_OREILLE);
            }
        }
    }

    /** TRV-CE1-6 : une activite du stereotype au moins change de cote, la table dit de quel cote. */
    private static final Map<String, String> COTE_DU_STEREOTYPE = Map.of(
            "l'histoire", "F", "bibliothèque", "F", "bruit", "M");

    @Test
    void trv6UneActiviteAuMoinsDesTroisDuStereotypeChangeDeCoteDeGenre() {
        boolean uneChange = false;
        for (Question item : items()) {
            for (Map.Entry<String, String> activite : COTE_DU_STEREOTYPE.entrySet()) {
                if (item.enonce().contains(activite.getKey())) {
                    String sujet = sujetDe(item);
                    String genre = sujet.equals("elle") || sujet.equals("elles") ? "F" : "M";
                    uneChange |= !genre.equals(activite.getValue());
                }
            }
        }
        assertThat(uneChange)
                .as("TRV-CE1-6 : écouter l'histoire, aller à la bibliothèque et faire du bruit sont"
                        + " servis chacun du côté du stéréotype ; l'une change de côté")
                .isTrue();
    }

    @Test
    void q1AucuneFormeAlleNiAllesEtAucunParticipePasseDansLesPropositions() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(proposition).as("'%s' : ni 'alle' ni 'alles' (Q1)", item.enonce())
                        .isNotIn("alle", "alles");
            }
            assertThat(item.distracteurs())
                    .as("'%s' : aucun participe passé en distracteur (Q1, section 1)", item.enonce())
                    .noneMatch(d -> d.endsWith("é") && !PRESENT.get(verbeDe(item)).contains(d));
        }
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
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsNeDonnePasLaReponseEtNEstPasUniforme() {
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
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item declare "est" de famille
     * "c" (l'infinitif) alors que "est" n'est pas l'infinitif de "etre" (PRS-CE1-3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaFamilleDeclareeFausse() {
        String chemin = CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-conjugaison-present-fautif.yaml";
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-conjugaison-present-fautif"));

        assertThatThrownBy(() -> verifierLesFormesEtLesFamilles(lireRessource(chemin), items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRS-CE1-3");
    }
}
