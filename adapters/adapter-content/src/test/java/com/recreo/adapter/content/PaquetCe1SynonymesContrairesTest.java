package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
 * S52-FRA-SYNONYMES-CONTRAIRES ({@code docs/backlog/backlog.md}, sprint 52), le paquet
 * {@code ce1-synonymes-contraires}, ligne {@code FRA-CE1-25} - trouver un synonyme ou un contraire
 * d'un mot frequent. Arbitrage pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 6.3, conditions
 * {@code SYC-CE1-1} a {@code SYC-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), reponse de nadia-prof a Q2
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md}, section 2) et
 * avis de faisabilite de theo-archi
 * ({@code squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md}, V3).
 *
 * <p>Ce test lit la forme : la relation nommee, le mot cible present dans sa phrase, les familles
 * declarees, l'absence de prefixe et l'indice de Q2. L'explication que Q2 demandait n'est pas servie :
 * {@code ChaquePaquetExpliqueSesItemsAVariationTest} lie toute explication a la cle
 * {@code graphie} (ADR-0024), nadia-prof a retire cette exigence, et ce test garde son absence
 * ({@code trv1}). Il ne lit pas le sens : qu'un
 * distracteur soit donne comme reponse par un dictionnaire, ou qu'un mot soit frequent au CE1,
 * reste a la lecture de nadia-prof.
 */
class PaquetCe1SynonymesContrairesTest {

    private static final String PAQUET = "ce1-synonymes-contraires";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1SynonymesContrairesTest";

    private static final double SEUIL_RELATION_MINIMUM = 1.0 / 3;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    /** Q2 : la phrase entre guillemets, puis la question qui nomme la relation et cite la cible. */
    private static final Pattern ENONCE = Pattern.compile(
            "^« (.+) » Quel mot est (un synonyme|le contraire) de"
                    + " « (\\p{L}+) » \\?$", FLAGS);

    private static final Pattern DECLARATION = Pattern.compile(
            "#\\s*SYC-CE1-2:\\s*([^\\r\\n]+)\\R\\s*- enonce: \"([^\"]*)\"");

    /** Q2 : l'indice des items de synonyme, forme acceptee mot pour mot. */
    private static final String INDICE_SYNONYME = "Un synonyme veut dire presque la même chose.";

    /** SYC-CE1-3 : les prefixes dont la negation fait un contraire. */
    private static final List<String> PREFIXES = List.of(
            "in", "im", "il", "ir", "dé", "dés", "mé", "més", "mal", "anti", "non");

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
                .as("'%s' : une phrase, puis 'Quel mot est un synonyme de' ou 'Quel mot est le"
                        + " contraire de', puis le mot cible (SYC-CE1-1, Q2)", item.enonce())
                .isTrue();
        return lecture;
    }

    private static boolean estUnSynonyme(Question item) {
        return lireLEnonce(item).group(2).startsWith("un");
    }

    private static String cibleDe(Question item) {
        return lireLEnonce(item).group(3);
    }

    /** SYC-CE1-3 : aucun contraire forme par prefixe, dans un sens ou dans l'autre. */
    private static void verifierQueAucunContraireNEstFormeParPrefixe(List<Question> items) {
        for (Question item : items) {
            if (estUnSynonyme(item)) {
                continue;
            }
            String cible = cibleDe(item).toLowerCase();
            String bonne = item.bonneReponse().toLowerCase();
            for (String prefixe : PREFIXES) {
                assertThat(bonne.equals(prefixe + cible) || cible.equals(prefixe + bonne))
                        .as("'%s' : '%s' et '%s' ne different pas par le prefixe '%s' (SYC-CE1-3)",
                                item.enonce(), cible, bonne, prefixe)
                        .isFalse();
            }
        }
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

    @Test
    void adr0081CePaquetDeclareFraCe125EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-25"));
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
    void syc1ChaqueQuestionNommeSaRelationEtLesDeuxRelationsPesentUnTiersAuMoins() {
        List<Question> items = items();
        long synonymes = items.stream().filter(PaquetCe1SynonymesContrairesTest::estUnSynonyme)
                .count();
        long contraires = items.size() - synonymes;
        assertThat((double) synonymes / items.size())
                .as("SYC-CE1-1 : %d synonymes sur %d", synonymes, items.size())
                .isGreaterThanOrEqualTo(SEUIL_RELATION_MINIMUM);
        assertThat((double) contraires / items.size())
                .as("SYC-CE1-1 : %d contraires sur %d", contraires, items.size())
                .isGreaterThanOrEqualTo(SEUIL_RELATION_MINIMUM);
        for (Question item : items) {
            String phrase = lireLEnonce(item).group(1);
            assertThat(phrase.toLowerCase())
                    .as("'%s' : le mot cible est dans la phrase qui fixe son sens (SYC-CE1-1)",
                            item.enonce())
                    .contains(cibleDe(item).toLowerCase());
            assertThat(mots(item.enonce())).as("'%s' : au plus vingt mots (LON-CE1-1)",
                    item.enonce()).hasSizeLessThanOrEqualTo(20);
            assertThat(item.distracteurs()).as("'%s' : deux distracteurs", item.enonce())
                    .hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
            assertThat(propositions(item)).as("'%s' : la cible n'est pas une proposition",
                    item.enonce()).doesNotContain(cibleDe(item));
        }
    }

    @Test
    void q2LaQuestionNeDitJamaisLeSynonymeEtLIndiceSuitLaFormeAcceptee() {
        for (Question item : items()) {
            assertThat(item.enonce()).as("Q2 : 'un synonyme', jamais 'le synonyme'")
                    .doesNotContain("le synonyme");
            String indice = item.indice().orElseThrow();
            if (estUnSynonyme(item)) {
                assertThat(indice).as("'%s' : l'indice de synonyme est la forme de Q2",
                        item.enonce()).isEqualTo(INDICE_SYNONYME);
            } else {
                assertThat(indice).as("'%s' : l'indice de contraire est autre (Q2)",
                        item.enonce()).isNotEqualTo(INDICE_SYNONYME);
            }
        }
    }

    @Test
    void syc2LesFamillesDesDistracteursSontDeclareesEtUnTiersPorteLaRelationInverse() {
        List<Question> items = items();
        Map<String, List<String>> declarees = famillesDeclarees(lire(FICHIER_CONTENU));
        assertThat(declarees).as("SYC-CE1-2 : un item, une declaration").hasSize(items.size());
        long avecInverse = 0;
        for (Question item : items) {
            List<String> familles = declarees.get(item.enonce());
            assertThat(familles).as("'%s' : une famille par distracteur (SYC-CE1-2)", item.enonce())
                    .hasSize(item.distracteurs().size())
                    .allMatch(f -> Set.of("a", "b").contains(f));
            if (familles.contains("a")) {
                avecInverse++;
            }
        }
        assertThat((double) avecInverse / items.size())
                .as("SYC-CE1-2 : %d items portent la relation inverse sur %d", avecInverse,
                        items.size())
                .isGreaterThanOrEqualTo(SEUIL_RELATION_MINIMUM);
    }

    @Test
    void syc3AucunContraireNEstFormeParPrefixe() {
        verifierQueAucunContraireNEstFormeParPrefixe(items());
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
    void lon5ChaquePropositionEstUnSeulMot() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'un mot (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSize(1);
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item donne "impossible" pour
     * contraire de "possible" : un contraire forme par prefixe (SYC-CE1-3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonContraireFormeParPrefixe() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-synonymes-contraires-fautif"));

        assertThatThrownBy(() -> verifierQueAucunContraireNEstFormeParPrefixe(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("SYC-CE1-3");
    }
}
