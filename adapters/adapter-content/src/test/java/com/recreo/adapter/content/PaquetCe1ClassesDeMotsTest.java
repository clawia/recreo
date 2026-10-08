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
import java.util.TreeMap;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S55-BR-FRA-CE1-L2 ({@code squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/planning.md}),
 * le paquet {@code ce1-classes-de-mots}, ligne {@code FRA-CE1-12} - identifier la classe d'un mot
 * dans une phrase. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.3, conditions
 * {@code CLM-CE1-1} a {@code CLM-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage prealable de s55, instance A
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md}, section 5,
 * conditions {@code K4} et {@code K5}) et avis de faisabilite de theo-archi (T1, T3).
 *
 * <p>Un item est une phrase dont un mot est entre guillemets francais ; la classe de ce mot est la
 * bonne reponse. Ce que ce test ne lit pas : la justesse de la classe de chacun des 24 mots,
 * l'ordre adjectif et nom de {@code CLM-CE1-3}, l'ambiguite d'un mot de deux classes
 * ({@code rond}, {@code souffle}) et ce que les indices laissent a l'enfant. Cela reste a la
 * lecture de nadia-prof.
 */
class PaquetCe1ClassesDeMotsTest {

    private static final String PAQUET = "ce1-classes-de-mots";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1ClassesDeMotsTest";

    /** K5 : "sorte" et non "classe", le mot que l'enfant lit au CP etant "le mot entre guillemets". */
    private static final String CONSIGNE_ATTENDUE =
            "Lis bien, puis dis quelle sorte de mot est entre guillemets.";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Map<String, Integer> REPARTITION_ATTENDUE = new TreeMap<>(Map.of(
            "nom commun", 5, "nom propre", 4, "verbe", 5, "déterminant", 5, "adjectif", 5));

    private static final Set<String> PRENOMS_FEMININS = Set.of("Mia", "Léa", "Inès", "Fatou", "Lina");

    private static final Set<String> PRENOMS_MASCULINS = Set.of("Tom", "Karim", "Noé");

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
        try (InputStream flux = PaquetCe1ClassesDeMotsTest.class.getResourceAsStream(chemin)) {
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

    /**
     * K4, arbitrage prealable de s55 instance A, section 5 : les prenoms, comptes en occurrences dans
     * les enonces, ne vont pas tous du meme cote (TRV-CE1-6, barre "a un pres").
     */
    private static void verifierLEquilibreDesPrenoms(List<Question> items) {
        long feminins = 0;
        long masculins = 0;
        for (Question item : items) {
            for (String token : item.enonce().split("[^\\p{L}]+")) {
                if (PRENOMS_FEMININS.contains(token)) {
                    feminins++;
                } else if (PRENOMS_MASCULINS.contains(token)) {
                    masculins++;
                }
            }
        }
        assertThat(Math.abs(feminins - masculins))
                .as("TRV-CE1-6 : %d occurrences de prénoms féminins, %d de masculins, à un près",
                        feminins, masculins)
                .isLessThanOrEqualTo(1);
    }

    /**
     * K5, arbitrage prealable de s55 instance A, section 5 : la consigne du jeu, lue au catalogue,
     * est le texte de nadia-prof et ne dit pas "classe". La question est portee par la consigne,
     * l'enonce n'etant que la phrase.
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
                .as("K5 : la consigne dit « sorte » et non « classe », la classe étant d'abord la salle")
                .doesNotContainIgnoringCase("classe")
                .isEqualTo(CONSIGNE_ATTENDUE);
    }

    @Test
    void adr0081CePaquetDeclareFraCe112EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-12"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv1AucunItemNePorteDeTexteSupportDIllustrationNiDeVoixHauteEtTousDesignentUnMot() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        for (Object item : (List<?>) corps.get("items")) {
            Map<?, ?> champs = (Map<?, ?>) item;
            assertThat(champs.keySet().stream().map(Object::toString).toList())
                    .as("TRV-CE1-1 : enonce, bonneReponse, distracteurs, indice et partDesignee")
                    .containsExactlyInAnyOrder("enonce", "bonneReponse", "distracteurs", "indice",
                            "partDesignee");
        }
    }

    @Test
    void clm1LeMotCibleEstDesigneEtLEnonceNeCommencePasParLui() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("'%s' : un mot entre guillemets, jamais en tête de phrase (CLM-CE1-1, -2)",
                            item.enonce())
                    .matches("^[^«]+«" + ESPACES + "\\p{L}+" + ESPACES + "».*$");
        }
    }

    @Test
    void clm1CinqClassesRepartiesEnCinqQuatreCinqCinqCinqEtNomCommunPlusVerbeSousLaMoitie() {
        Map<String, Integer> repartition = new TreeMap<>();
        for (Question item : items()) {
            repartition.merge(item.bonneReponse(), 1, Integer::sum);
            for (String proposition : propositions(item)) {
                assertThat(REPARTITION_ATTENDUE).as("'%s' : une des cinq classes (CLM-CE1-2)",
                        proposition).containsKey(proposition);
            }
        }
        assertThat(repartition).as("CLM-CE1-1 : nom commun 5, nom propre 4, verbe 5, déterminant 5,"
                + " adjectif 5").isEqualTo(REPARTITION_ATTENDUE);
        assertThat(repartition.get("nom commun") + repartition.get("verbe"))
                .as("CLM-CE1-1 : nom commun et verbe réunis sous la moitié").isLessThan(12);
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItemsEtChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
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
                .isLessThanOrEqualTo(0.50);
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
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsNeRenvoiePasALOreilleEtNEstPasUniforme() {
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
    void k4LesPrenomsFemininsEtMasculinsSontALUnPres() {
        verifierLEquilibreDesPrenoms(items());
    }

    @Test
    void k5LaConsigneDuJeuDitSorteEtNonClasse() {
        verifierLaConsigneDuJeu(lire(FICHIER_CATALOGUE));
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

    /** ADR-0013 obligation 3 : le jeu fautif de K4. Trois prenoms masculins, aucun feminin. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSesPrenomsTousMasculins() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-classes-de-mots-fautif"));

        assertThatThrownBy(() -> verifierLEquilibreDesPrenoms(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif de K5. Sa consigne dit "classe". */
    @Test
    void leCatalogueFautifCommiteEstRefuseSurSaConsigne() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierLaConsigneDuJeu(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("K5");
    }
}
