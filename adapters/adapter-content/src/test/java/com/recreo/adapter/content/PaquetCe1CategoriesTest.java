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
 * S52-FRA-CATEGORIES ({@code docs/backlog/backlog.md}, sprint 52), le paquet {@code ce1-categories},
 * ligne {@code FRA-CE1-26} - classer des mots par categorie et trouver le mot qui les designe tous.
 * Arbitrage pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 6.4, conditions
 * {@code CTG-CE1-1} a {@code CTG-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), et avis de faisabilite de theo-archi
 * ({@code squad/sprints/s52-deux-instances-ce1-francais-maths/avis-faisabilite.md}, V4).
 *
 * <p>La liste fermee des categories est declaree dans l'en-tete du fichier, la categorie et la
 * famille de chaque distracteur dans deux commentaires juste au-dessus de l'item ({@code CTG-CE1-2},
 * {@code CTG-CE1-3}) ; ce test les relit contre l'item. Ce qu'il ne lit pas : qu'un membre soit
 * discute, qu'une categorie soit juste, qu'un distracteur de famille "a" d'un item "Lequel est un"
 * soit bien un membre d'une categorie soeur. Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1CategoriesTest {

    private static final String PAQUET = "ce1-categories";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1CategoriesTest";

    private static final String QUESTION_DESIGNE = "Quel mot désigne";

    private static final String QUESTION_CLASSER = "Lequel est un ";

    private static final double SEUIL_DESIGNE_MINIMUM = 0.50;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final Pattern LISTE_FERMEE = Pattern.compile(
            "^# CTG-CE1-2 liste fermee : ([^\\r\\n]+)$", Pattern.MULTILINE);

    /** Les deux commentaires d'un item, puis l'item lu par son enonce et sa bonne reponse. */
    private static final Pattern DECLARATION = Pattern.compile(
            "#\\s*CTG-CE1-2:\\s*([^\\r\\n]+)\\R\\s*#\\s*CTG-CE1-3:\\s*([^\\r\\n]+)\\R"
                    + "\\s*- enonce: \"([^\"]*)\"\\R\\s*bonneReponse: \"([^\"]*)\"");

    /** CTG-CE1-2 : aucune classification scientifique, aucun membre discute. */
    private static final Pattern SCIENTIFIQUE_OU_DISCUTE = Pattern.compile(
            "(?<!\\p{L})(vivants?|non vivants?|tomates?|champignons?|baleines?|mammifères?"
                    + "|insectes?|végétaux|végétal|espèces?)(?!\\p{L})", FLAGS);

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
        try (InputStream flux = PaquetCe1CategoriesTest.class.getResourceAsStream(chemin)) {
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

    private static boolean designe(Question item) {
        return item.enonce().startsWith(QUESTION_DESIGNE);
    }

    private static List<String> categoriesDeclarees(String source) {
        Matcher liste = LISTE_FERMEE.matcher(source);
        assertThat(liste.find()).as("CTG-CE1-2 : l'en-tete declare la liste fermee").isTrue();
        return Arrays.stream(liste.group(1).split(",")).map(String::strip).toList();
    }

    private record Declaration(String categorie, List<String> familles) {
    }

    private static Map<String, Declaration> declarations(String source) {
        Map<String, Declaration> declarations = new HashMap<>();
        Matcher declaration = DECLARATION.matcher(source);
        while (declaration.find()) {
            declarations.put(declaration.group(3) + "|" + declaration.group(4), new Declaration(
                    declaration.group(1).strip(),
                    Arrays.stream(declaration.group(2).split(",")).map(String::strip).toList()));
        }
        return declarations;
    }

    /** Les mots donnes d'un item "Quel mot designe : a, b, c ?". */
    private static List<String> motsDonnes(Question item) {
        String enonce = item.enonce().substring(item.enonce().indexOf(':') + 1);
        return Arrays.stream(enonce.replace("?", "").replace(" ", "").split(",")).map(String::strip).toList();
    }

    /** CTG-CE1-2 et CTG-CE1-3 : categorie et familles declarees, relues contre l'item. */
    private static void verifierLesDeclarations(String source, List<Question> items) {
        List<String> categories = categoriesDeclarees(source);
        Map<String, Declaration> declarations = declarations(source);
        assertThat(declarations).as("CTG-CE1-2 et CTG-CE1-3 : un item, une declaration")
                .hasSize(items.size());
        for (Question item : items) {
            Declaration declaration = declarations.get(item.enonce() + "|" + item.bonneReponse());
            assertThat(declaration).as("'%s' : declaration absente", item.enonce()).isNotNull();
            assertThat(categories).as("'%s' : catégorie de la liste fermée (CTG-CE1-2)",
                    item.enonce()).contains(declaration.categorie());
            assertThat(declaration.familles())
                    .as("'%s' : une famille par distracteur (CTG-CE1-3)", item.enonce())
                    .hasSize(item.distracteurs().size())
                    .allMatch(f -> Set.of("a", "b", "c").contains(f));
            if (designe(item)) {
                assertThat(item.bonneReponse())
                        .as("'%s' : la bonne réponse est la catégorie déclarée (CTG-CE1-2)",
                                item.enonce())
                        .isEqualTo(declaration.categorie());
                verifierLesFamillesDUnItemDesigne(item, declaration, categories);
            } else {
                assertThat(item.enonce())
                        .as("'%s' : la question nomme la catégorie déclarée (CTG-CE1-2)",
                                item.enonce())
                        .isEqualTo(QUESTION_CLASSER + declaration.categorie() + " ?");
                assertThat(declaration.familles())
                        .as("'%s' : un item 'Lequel est un' ne porte que les familles a et c"
                                + " (CTG-CE1-3)", item.enonce())
                        .doesNotContain("b");
            }
        }
    }

    private static void verifierLesFamillesDUnItemDesigne(
            Question item, Declaration declaration, List<String> categories) {
        List<String> donnes = motsDonnes(item);
        for (int i = 0; i < item.distracteurs().size(); i++) {
            String distracteur = item.distracteurs().get(i);
            switch (declaration.familles().get(i)) {
                case "a" -> assertThat(categories)
                        .as("'%s' : '%s' (a) est une autre catégorie de la liste (CTG-CE1-3)",
                                item.enonce(), distracteur)
                        .contains(distracteur);
                case "b" -> assertThat(donnes)
                        .as("'%s' : '%s' (b) est l'un des mots donnés (CTG-CE1-3)",
                                item.enonce(), distracteur)
                        .contains(distracteur);
                default -> {
                    assertThat(categories)
                            .as("'%s' : '%s' (c) n'est pas une catégorie (CTG-CE1-3)",
                                    item.enonce(), distracteur)
                            .doesNotContain(distracteur);
                    assertThat(donnes)
                            .as("'%s' : '%s' (c) n'est pas l'un des mots donnés (CTG-CE1-3)",
                                    item.enonce(), distracteur)
                            .doesNotContain(distracteur);
                }
            }
            assertThat(distracteur).as("'%s' : un distracteur n'est pas la bonne réponse",
                    item.enonce()).isNotEqualTo(declaration.categorie());
        }
    }

    @Test
    void adr0081CePaquetDeclareFraCe126EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-26"));
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
    void ctg1AuMoinsLaMoitieDesItemsDemandentLeMotQuiDesigneTroisMotsDonnes() {
        List<Question> items = items();
        long designe = items.stream().filter(PaquetCe1CategoriesTest::designe).count();
        assertThat((double) designe / items.size())
                .as("CTG-CE1-1 : %d items 'Quel mot désigne' sur %d", designe, items.size())
                .isGreaterThanOrEqualTo(SEUIL_DESIGNE_MINIMUM);
        for (Question item : items) {
            if (designe(item)) {
                assertThat(motsDonnes(item)).as("'%s' : trois mots donnés (CTG-CE1-1)",
                        item.enonce()).hasSize(3);
            } else {
                assertThat(item.enonce()).as("l'autre forme classe (CTG-CE1-1)")
                        .startsWith(QUESTION_CLASSER);
            }
            assertThat(item.distracteurs()).as("'%s' : deux distracteurs (TRV-CE1-2)",
                    item.enonce()).hasSize(2);
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void ctg2ctg3LaListeFermeeLesCategoriesEtLesFamillesSontDeclareesEtRelues() {
        verifierLesDeclarations(lire(FICHIER_CONTENU), items());
    }

    @Test
    void ctg2AucuneClassificationScientifiqueNiMembreDiscuteDansLePaquet() {
        String source = lire(FICHIER_CONTENU);
        assertThat(categoriesDeclarees(source)).as("CTG-CE1-2 : aucune catégorie scientifique")
                .noneMatch(c -> SCIENTIFIQUE_OU_DISCUTE.matcher(c).find());
        for (Question item : items()) {
            String texte = item.enonce() + " " + String.join(" ", propositions(item));
            assertThat(SCIENTIFIQUE_OU_DISCUTE.matcher(texte).find())
                    .as("'%s' : ni classification scientifique ni membre discuté (CTG-CE1-2)", texte)
                    .isFalse();
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
            for (String mot : mots(item.bonneReponse())) {
                if (Set.of("un", "une", "de").contains(mot.toLowerCase(Locale.FRENCH))) {
                    continue;
                }
                assertThat(Pattern.compile("(?<!\\p{L})" + Pattern.quote(mot) + "(?!\\p{L})", FLAGS)
                        .matcher(indice).find())
                        .as("'%s' : l'indice ne cite pas '%s' (TRV-CE1-5)", indice, mot).isFalse();
            }
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMotsEtUneSeulePhrase() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
                assertThat(proposition).as("une seule phrase").doesNotContain(".", "?", "!");
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item declare "arbre"
     * distracteur de famille "a" (categorie soeur) alors que "arbre" n'est pas une categorie de la
     * liste fermee (CTG-CE1-3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaFamilleDeclareeFausse() {
        String chemin = CONTRE_EXEMPLE_CLASSPATH + "/contenu/ce1-categories-fautif.yaml";
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-categories-fautif"));

        assertThatThrownBy(() -> verifierLesDeclarations(lireRessource(chemin), items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("CTG-CE1-3");
    }
}
