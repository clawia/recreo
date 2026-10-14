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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S57-BR-FRA-CE1-10 ({@code squad/sprints/s57-version-cp-ce1-cm1-trois-instances/planning.md}), le
 * paquet {@code ce1-phrases-types}, ligne {@code FRA-CE1-10} - reconnaitre une phrase et
 * identifier son type par sa ponctuation. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.1, conditions
 * {@code TPH-CE1-1} a {@code TPH-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}) et arbitrage du palier 1 de s57 (point d'audit 6.2).
 *
 * <p>Les proportions de {@code TPH-CE1-1} sont recomptees ici sur les champs {@code enonce} et
 * {@code bonneReponse} lus par le chargeur, jamais sur les commentaires de l'en-tete du fichier.
 * Ce que ce test ne lit pas : qu'un groupe de mots ait un sens (TPH-CE1-2), qu'aucune phrase
 * citee ne soit imperative (TPH-CE1-3 : "Viens ici !" serait classee exclamative par son signe),
 * qu'un indice n'enseigne pas une regle fausse (TRV-CE1-5), l'equilibre des roles et des
 * situations par genre et l'absence de configuration familiale supposee (TRV-CE1-6), la
 * lisibilite pour un enfant de CE1. Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1PhrasesTypesTest {

    private static final String PAQUET = "ce1-phrases-types";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1PhrasesTypesTest";

    /** La question est portee par chaque enonce : la consigne est celle que l'enfant lit deja. */
    private static final String CONSIGNE_ATTENDUE = "Lis bien, puis choisis la bonne réponse.";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern PHRASE_CITEE =
            Pattern.compile("^«" + ESPACES + "(.+?)" + ESPACES + "»");

    private static final String QUESTION_RECONNAITRE = "Quel groupe de mots est une phrase";

    private static final List<String> NOMS_DE_TYPE =
            List.of("déclarative", "interrogative", "exclamative");

    /** TPH-CE1-3 : le type se lit au signe final seul. */
    private static final Map<Character, String> TYPE_DU_SIGNE =
            Map.of('.', "déclarative", '?', "interrogative", '!', "exclamative");

    /** TPH-CE1-3 : jamais deux signes combines (?!, ...). */
    private static final Pattern SIGNES_COMBINES =
            Pattern.compile("[.?!…]" + ESPACES + "*[.?!…]");

    /** TRV-CE1-6 : le genre de chaque prenom des phrases, porte ici et non deduit. */
    private static final Set<String> PRENOMS_FEMININS =
            Set.of("Mia", "Léa", "Inès", "Sofia", "Lina", "Awa");

    private static final Set<String> PRENOMS_MASCULINS =
            Set.of("Karim", "Tom", "Noé", "Sami", "Hugo");

    /** Mots qui ouvrent une phrase sans etre des prenoms. */
    private static final Set<String> OUVERTURES = Set.of(
            "Le", "Les", "La", "Un", "Une", "Tu", "Où", "Qui", "Combien", "Comme", "Quel", "Quelle");

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
        try (InputStream flux = PaquetCe1PhrasesTypesTest.class.getResourceAsStream(chemin)) {
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

    private static List<Question> itemsFautifs() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-phrases-types-fautif"));
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    /** TPH-CE1-1 : un item "reconnaitre une phrase" se lit a son enonce. */
    private static boolean estUnItemDeReconnaissance(Question item) {
        return item.enonce().startsWith(QUESTION_RECONNAITRE);
    }

    /** TPH-CE1-1 : un item "type" se lit a sa bonne reponse, un des trois noms de type. */
    private static boolean estUnItemDeType(Question item) {
        return NOMS_DE_TYPE.contains(item.bonneReponse());
    }

    private static String sansMajusculeInitiale(String texte) {
        return texte.substring(0, 1).toLowerCase(Locale.ROOT) + texte.substring(1);
    }

    private static String phraseCitee(Question item) {
        Matcher phrase = PHRASE_CITEE.matcher(item.enonce());
        assertThat(phrase.find()).as("'%s' : une phrase citée entre guillemets", item.enonce()).isTrue();
        return phrase.group(1);
    }

    private static char dernierSigne(String phrase) {
        return phrase.stripTrailing().charAt(phrase.stripTrailing().length() - 1);
    }

    /**
     * TPH-CE1-1, point d'audit 6.2 : les proportions sont recomptees sur les champs. Au moins neuf
     * items de reconnaissance, quinze items de type, cinq par type. Un item qui n'est ni l'un ni
     * l'autre fait echouer le comptage en se nommant.
     */
    private static void verifierLesProportions(List<Question> items) {
        long reconnaissance = 0;
        long type = 0;
        Map<String, Long> parType = new HashMap<>();
        for (Question item : items) {
            if (estUnItemDeReconnaissance(item)) {
                reconnaissance++;
            } else if (estUnItemDeType(item)) {
                type++;
                parType.merge(item.bonneReponse(), 1L, Long::sum);
            } else {
                throw new AssertionError("TPH-CE1-1 : '" + item.enonce()
                        + "' n'est ni un item de reconnaissance (énoncé) ni un item de type"
                        + " (bonne réponse : un des trois noms)");
            }
        }
        assertThat(reconnaissance)
                .as("TPH-CE1-1 : %d items « reconnaître une phrase », au moins 9", reconnaissance)
                .isGreaterThanOrEqualTo(9);
        assertThat(type).as("TPH-CE1-1 : %d items « type », au moins 15", type)
                .isGreaterThanOrEqualTo(15);
        for (String nom : NOMS_DE_TYPE) {
            long compte = parType.getOrDefault(nom, 0L);
            assertThat(compte).as("TPH-CE1-1 : %d items dont la bonne réponse est « %s », au moins 5",
                    compte, nom).isGreaterThanOrEqualTo(5);
        }
    }

    /**
     * TPH-CE1-2 : sur un item de reconnaissance, la bonne reponse a la majuscule initiale et le
     * point final ; les deux distracteurs sont la meme phrase (a) sans majuscule et (b) sans point.
     */
    private static void verifierLesTroisGroupesDeMots(List<Question> items) {
        for (Question item : items) {
            if (!estUnItemDeReconnaissance(item)) {
                continue;
            }
            String phrase = item.bonneReponse();
            assertThat(Character.isUpperCase(phrase.codePointAt(0)) && phrase.endsWith("."))
                    .as("TPH-CE1-2 : '%s' - la bonne réponse '%s' a la majuscule initiale et le point final",
                            item.enonce(), phrase)
                    .isTrue();
            assertThat(item.distracteurs())
                    .as("TPH-CE1-2 : '%s' - les distracteurs de '%s' : sans majuscule, sans point",
                            item.enonce(), phrase)
                    .containsExactlyInAnyOrder(sansMajusculeInitiale(phrase),
                            phrase.substring(0, phrase.length() - 1));
        }
    }

    /**
     * TPH-CE1-3 : sur un item de type, les propositions sont les trois noms de type et le nom de la
     * bonne reponse est celui que designe le signe final de la phrase citee.
     */
    private static void verifierLeTypeParLeSigneFinal(List<Question> items) {
        for (Question item : items) {
            if (!estUnItemDeType(item)) {
                continue;
            }
            assertThat(propositions(item))
                    .as("TPH-CE1-3 : '%s' - les propositions sont les trois noms de type", item.enonce())
                    .containsExactlyInAnyOrderElementsOf(NOMS_DE_TYPE);
            String phrase = phraseCitee(item);
            assertThat(TYPE_DU_SIGNE.get(dernierSigne(phrase)))
                    .as("TPH-CE1-3 : '%s' - le signe final de la phrase citée désigne le type", item.enonce())
                    .isEqualTo(item.bonneReponse());
        }
    }

    /** TPH-CE1-3 : jamais deux signes combines, ni dans un enonce ni dans une proposition. */
    private static void verifierQuAucunSigneNEstCombine(List<Question> items) {
        for (Question item : items) {
            List<String> textes = new ArrayList<>(propositions(item));
            textes.add(item.enonce());
            for (String texte : textes) {
                assertThat(SIGNES_COMBINES.matcher(texte).find())
                        .as("TPH-CE1-3 : '%s' - signes combinés dans '%s'", item.enonce(), texte)
                        .isFalse();
            }
        }
    }

    /**
     * TRV-CE1-6 : les prenoms des phrases, comptes en occurrences (la phrase citee d'un item de
     * type, la bonne reponse d'un item de reconnaissance), ne vont pas plus d'un de difference d'un
     * genre a l'autre. Une majuscule hors des listes, hors de l'ouverture d'une phrase, fait
     * echouer le garde-fou en se nommant.
     */
    private static void verifierLEquilibreDesPrenoms(List<Question> items) {
        long feminins = 0;
        long masculins = 0;
        for (Question item : items) {
            String phrase = estUnItemDeReconnaissance(item) ? item.bonneReponse() : phraseCitee(item);
            List<String> tokens = mots(phrase).stream()
                    .map(mot -> mot.replaceAll("^[^\\p{L}]+|[^\\p{L}]+$", "")).toList();
            for (int rang = 0; rang < tokens.size(); rang++) {
                String token = tokens.get(rang);
                if (PRENOMS_FEMININS.contains(token)) {
                    feminins++;
                } else if (PRENOMS_MASCULINS.contains(token)) {
                    masculins++;
                } else if (Character.isUpperCase(token.codePointAt(0))
                        && !(rang == 0 && OUVERTURES.contains(token))) {
                    throw new AssertionError("TRV-CE1-6 : '" + token + "' dans '" + phrase
                            + "' est une majuscule hors des listes de prénoms et d'ouvertures");
                }
            }
        }
        assertThat(Math.abs(feminins - masculins))
                .as("TRV-CE1-6 : %d occurrences de prénoms féminins, %d de masculins, à un près",
                        feminins, masculins)
                .isLessThanOrEqualTo(1);
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

    /**
     * La consigne du jeu, lue au catalogue, est celle que l'enfant lit deja sur d'autres entrees :
     * la question est portee par chaque enonce.
     */
    private static void verifierLaConsigneDuJeu(String catalogue) {
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
                .as("la consigne est celle des entrées existantes, mot pour mot")
                .isEqualTo(CONSIGNE_ATTENDUE);
    }

    @Test
    void adr0081CePaquetDeclareFraCe110EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-10"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1AucunItemNePorteDeTexteSupportDIllustrationNiDeVoixHaute() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : enonce, bonneReponse, distracteurs et indice")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice");
        }
    }

    @Test
    void trv7LePaquetGardeSesVingtQuatreItemsEtChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("P1 : 24 items (TRV-CE1-7 : vingt au moins)").hasSize(24);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void tph1LesProportionsSontRecomptees() {
        verifierLesProportions(items());
    }

    @Test
    void tph1ChaqueItemEstUnItemDeReconnaissanceOuUnItemDeTypeEtPasLesDeux() {
        for (Question item : items()) {
            assertThat(estUnItemDeReconnaissance(item) ^ estUnItemDeType(item))
                    .as("TPH-CE1-1 : '%s' est de reconnaissance ou de type, pas des deux", item.enonce())
                    .isTrue();
        }
    }

    @Test
    void tph2LesItemsDeReconnaissanceOffrentLaPhraseSansMajusculeEtSansPoint() {
        verifierLesTroisGroupesDeMots(items());
    }

    @Test
    void tph3LesItemsDeTypeOffrentLesTroisNomsEtLeSigneFinalDesigneLeType() {
        verifierLeTypeParLeSigneFinal(items());
    }

    @Test
    void tph3AucunSigneNEstCombine() {
        verifierQuAucunSigneNEstCombine(items());
    }

    @Test
    void trv6LesPrenomsFemininsEtMasculinsSontALUnPres() {
        verifierLEquilibreDesPrenoms(items());
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQuAucunIndiceNeContientUneProposition(items());
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueSurDouzeItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream().filter(i -> i.distracteurs().stream()
                .allMatch(d -> i.bonneReponse().length() > d.length())).count();
        assertThat(plusLongue)
                .as("TRV-CE1-2 : %d items où la bonne réponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(12);
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
    void laConsigneDuJeuEstCelleDesEntreesExistantes() {
        verifierLaConsigneDuJeu(lire(FICHIER_CATALOGUE));
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif des proportions. Il compte un seul item de
     * reconnaissance, alors que des commentaires du fichier en annoncent davantage : le compte est
     * celui des champs.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesProportionsRecomptees() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesProportions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TPH-CE1-1")
                .hasMessageContaining("1 items « reconnaître une phrase »");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TPH-CE1-2, un distracteur sans point manquant. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurSansPointManquant() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLesTroisGroupesDeMots(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TPH-CE1-2")
                .hasMessageContaining("Le chat boit du lait.");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TPH-CE1-3, un type que le signe final ne designe pas. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnTypeQueLeSigneFinalNeDesignePas() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLeTypeParLeSigneFinal(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TPH-CE1-3")
                .hasMessageContaining("Où est le crayon");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TPH-CE1-3, deux signes combines. */
    @Test
    void leJeuFautifCommiteEstRefuseSurDeuxSignesCombines() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunSigneNEstCombine(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("signes combinés")
                .hasMessageContaining("Quelle belle journée");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-6, trois prenoms masculins pour aucun feminin. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsDesequilibres() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6")
                .hasMessageContaining("occurrences de prénoms");
    }

    /** ADR-0013 obligation 3 : le jeu fautif de TRV-CE1-5, un indice qui contient une proposition. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnIndiceQuiContientUneProposition() {
        List<Question> items = itemsFautifs();

        assertThatThrownBy(() -> verifierQuAucunIndiceNeContientUneProposition(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("contient une proposition")
                .hasMessageContaining("déclarative");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif. Sa consigne n'est pas celle des entrees existantes. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSaConsigne() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierLaConsigneDuJeu(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("consigne");
    }
}
