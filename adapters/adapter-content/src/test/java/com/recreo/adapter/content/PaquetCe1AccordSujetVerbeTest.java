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
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S55-BR-FRA-CE1-L1 ({@code squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/planning.md}),
 * le paquet {@code ce1-accord-sujet-verbe}, ligne {@code FRA-CE1-14} - accorder le verbe avec son
 * sujet au present. Arbitrage prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.5, conditions
 * {@code ACV-CE1-1} a {@code ACV-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}), arbitrage prealable de s55, instance A
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md}, section 4,
 * conditions {@code K1} a {@code K3}) et avis de faisabilite de theo-archi (T1).
 *
 * <p>Un item est une phrase a trou precedee du verbe a l'infinitif entre guillemets francais ; le
 * sujet est lu dans l'enonce, entre les deux-points et le trou. Ce que ce test ne lit pas : la
 * justesse de chaque forme conjuguee, la vraisemblance d'un distracteur comme erreur d'un enfant
 * et ce qu'un indice laisse a l'enfant qui a choisi un distracteur au-dela du nombre
 * ({@code TRV-CE1-5}). Cela reste a la lecture de nadia-prof.
 */
class PaquetCe1AccordSujetVerbeTest {

    private static final String PAQUET = "ce1-accord-sujet-verbe";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    private static final Path FICHIER_CATALOGUE = Path.of("src/main/resources/catalogue.yaml");

    /** ADR-0013 obligation 3 : les jeux fautifs de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1AccordSujetVerbeTest";

    /** K3 : la question du present CE1, mot pour mot, que l'enfant lit deja sur FRA-CE1-17. */
    private static final String CONSIGNE_ATTENDUE = "Lis bien, puis choisis la bonne forme du verbe.";

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    private static final String ESPACES = "[\\s\\u00A0]";

    /** « verbe » : sujet ___ suite. */
    private static final Pattern ENONCE = Pattern.compile(
            "^«" + ESPACES + "(\\p{L}+)" + ESPACES + "»" + ESPACES + ": (.+?) ___ .+$", FLAGS);

    private static final Set<String> PRONOMS_PLURIELS = Set.of("ils", "elles");

    private static final Set<String> PRONOMS = Set.of("je", "tu", "il", "elle", "nous", "vous",
            "ils", "elles");

    private static final Set<String> PLURIELS_IRREGULIERS =
            Set.of("sont", "ont", "vont", "font", "disent");

    /** PRS-CE1-1 : les six verbes nommes ; tout autre verbe est du premier groupe. */
    private static final Set<String> VERBES_NOMMES_DE_PRS_CE1_1 =
            Set.of("être", "avoir", "aller", "faire", "dire", "venir");

    /** PRS-CE1-1 : exclus, les verbes du premier groupe a modification orthographique. */
    private static final Pattern MODIFICATION_ORTHOGRAPHIQUE = Pattern.compile("(ger|cer|eler|eter|yer)$");

    private static final Set<String> EXCLUS_NOMMES_DE_PRS_CE1_1 = Set.of("acheter", "lever");

    /** ACV-CE1-R2 : au moins huit des douze items a sujet pluriel portent la marque muette. */
    private static final int PLURIELS_MUETS_AU_MOINS = 8;

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

    /** K1 : les mots par lesquels un indice donne le nombre comme critere. */
    private static final Pattern CRITERE_DU_NOMBRE = Pattern.compile(
            "plusieurs|combien|compte", FLAGS);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static String lireRessource(String chemin) {
        try (InputStream flux = PaquetCe1AccordSujetVerbeTest.class.getResourceAsStream(chemin)) {
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

    private static String sujetDe(Question item) {
        Matcher lecture = ENONCE.matcher(item.enonce());
        assertThat(lecture.matches())
                .as("'%s' : un verbe entre guillemets, le sujet, puis le trou", item.enonce())
                .isTrue();
        return lecture.group(2).toLowerCase(Locale.FRENCH);
    }

    private static boolean sujetPluriel(String sujet) {
        return sujet.startsWith("les ") || PRONOMS_PLURIELS.contains(sujet);
    }

    private static boolean formePlurielle(String forme) {
        return PLURIELS_IRREGULIERS.contains(forme) || forme.endsWith("ons")
                || (forme.endsWith("ent") && !forme.equals("vient"));
    }

    private static String verbeDe(Question item) {
        Matcher lecture = ENONCE.matcher(item.enonce());
        assertThat(lecture.matches())
                .as("'%s' : un verbe entre guillemets, le sujet, puis le trou", item.enonce())
                .isTrue();
        return lecture.group(1).toLowerCase(Locale.FRENCH);
    }

    /** Premier groupe de PRS-CE1-1 : en -er, ni aller, ni a modification orthographique. */
    private static boolean premierGroupeSansModification(String infinitif) {
        return infinitif.endsWith("er") && !infinitif.equals("aller")
                && !MODIFICATION_ORTHOGRAPHIQUE.matcher(infinitif).find()
                && !EXCLUS_NOMMES_DE_PRS_CE1_1.contains(infinitif);
    }

    private static boolean estUnPlurielMuet(Question item) {
        return sujetPluriel(sujetDe(item)) && premierGroupeSansModification(verbeDe(item))
                && item.bonneReponse().endsWith("ent");
    }

    /**
     * ACV-CE1-R2, arbitrage prealable de s56 instance A et fiche de s55 : sur les items a sujet
     * pluriel, au moins huit ont pour bonne reponse une forme du premier groupe en -ent. Le verbe est
     * lu dans l'enonce, jamais deduit du suffixe de la reponse : viennent et disent finissent par
     * -ent sans etre du premier groupe.
     */
    private static void verifierLaPartDesPlurielsMuets(List<Question> items) {
        long pluriels = items.stream().filter(i -> sujetPluriel(sujetDe(i))).count();
        long muets = items.stream().filter(PaquetCe1AccordSujetVerbeTest::estUnPlurielMuet).count();
        assertThat(muets)
                .as("ACV-CE1-R2 : %d items à sujet pluriel sur %d ont pour bonne réponse une forme du"
                        + " premier groupe en -ent", muets, pluriels)
                .isGreaterThanOrEqualTo(PLURIELS_MUETS_AU_MOINS);
    }

    /** ACV-CE1-R2 : les verbes sont ceux de PRS-CE1-1 et aucun autre. */
    private static void verifierQueLesVerbesSontCeuxDePrsCe11(List<Question> items) {
        for (Question item : items) {
            String verbe = verbeDe(item);
            assertThat(VERBES_NOMMES_DE_PRS_CE1_1.contains(verbe) || premierGroupeSansModification(verbe))
                    .as("PRS-CE1-1 : '%s' - le verbe '%s' n'est ni l'un des six verbes nommés ni du"
                            + " premier groupe sans modification orthographique", item.enonce(), verbe)
                    .isTrue();
        }
    }

    /**
     * ACV-CE1-R2 : sur un item a pluriel muet, les distracteurs sont de la meme famille que la
     * bonne reponse (autre nombre, autre personne), c'est-a-dire qu'ils en portent le radical.
     */
    private static void verifierQueLesDistracteursDesPlurielsMuetsSontDeLaMemeFamille(List<Question> items) {
        for (Question item : items) {
            if (!estUnPlurielMuet(item)) {
                continue;
            }
            String radical = item.bonneReponse().substring(0, item.bonneReponse().length() - 3);
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.startsWith(radical) && !distracteur.equals(item.bonneReponse()))
                        .as("ACV-CE1-R2 : '%s' - le distracteur '%s' n'est pas une autre forme du verbe '%s'",
                                item.enonce(), distracteur, verbeDe(item))
                        .isTrue();
            }
        }
    }

    /**
     * K1, arbitrage prealable de s55 instance A, section 4 : un indice qui donne le nombre comme
     * critere (un seul, plusieurs, combien, compter) est satisfait par le distracteur d'une autre
     * personne qui a le meme nombre que la bonne reponse ("joues", "ecoutons"). Il n'est admis que
     * sur un item dont les deux distracteurs ont l'autre nombre.
     */
    private static void verifierQueLeNombreNEstUnCritereQueOuIlDepartage(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow();
            if (!CRITERE_DU_NOMBRE.matcher(indice).find()) {
                continue;
            }
            for (String distracteur : item.distracteurs()) {
                assertThat(formePlurielle(distracteur))
                        .as("'%s' : l'indice '%s' donne le nombre, que '%s' satisfait avec la bonne"
                                + " réponse '%s' (TRV-CE1-5)", item.enonce(), indice, distracteur,
                                item.bonneReponse())
                        .isNotEqualTo(formePlurielle(item.bonneReponse()));
            }
        }
    }

    /**
     * ACV-CE1-R1, TRV-CE1-5 ("jamais la reponse") : un indice qui contient, en mot entier, une des
     * propositions de son item laisse l'enfant repondre en retrouvant le mot sans lire le sujet.
     */
    private static void verifierQueAucunIndiceNeContientUneDeSesPropositions(List<Question> items) {
        for (Question item : items) {
            String indice = item.indice().orElseThrow();
            for (String proposition : propositions(item)) {
                Pattern motEntier = Pattern.compile(
                        "(?<![\\p{L}\\p{N}])" + Pattern.quote(proposition) + "(?![\\p{L}\\p{N}])", FLAGS);
                assertThat(motEntier.matcher(indice).find())
                        .as("'%s' : l'indice '%s' contient la proposition '%s' (ACV-CE1-R1, TRV-CE1-5)",
                                item.enonce(), indice, proposition)
                        .isFalse();
            }
        }
    }

    /**
     * K2, arbitrage prealable de s55 instance A, section 4 : les activites de courses et de cuisine
     * ne vont pas toutes du cote feminin (TRV-CE1-6). Les items du marche et du gateau portent
     * "il". Aucun item ne porte "tu" (TRV-CE1-6).
     */
    private static void verifierLesSujetsDeCoursesEtLAbsenceDeTu(List<Question> items) {
        for (Question item : items) {
            String sujet = sujetDe(item);
            assertThat(sujet).as("'%s' : aucun sujet 'tu' (TRV-CE1-6)", item.enonce())
                    .isNotEqualTo("tu");
            if (item.enonce().contains("marché") || item.enonce().contains("gâteau")) {
                assertThat(sujet)
                        .as("'%s' : les courses et la cuisine ne vont pas du côté féminin"
                                + " (TRV-CE1-6)", item.enonce())
                        .isEqualTo("il");
            }
        }
    }

    /** K3, arbitrage prealable de s55 instance A, section 4 : la consigne du jeu, lue au catalogue. */
    private static void verifierQueLaConsigneEstCelleDuPresentCe1(String catalogue) {
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
                .as("K3 : la consigne est celle du présent CE1 (FRA-CE1-17)")
                .isEqualTo(CONSIGNE_ATTENDUE);
    }

    @Test
    void adr0081CePaquetDeclareFraCe114EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-14"));
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
    void trv7LePaquetCompteAuMoinsVingtItemsEtChacunATroisPropositionsDistinctes() {
        List<Question> items = items();
        assertThat(items).as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
        for (Question item : items) {
            assertThat(new HashSet<>(propositions(item)))
                    .as("'%s' : trois propositions distinctes (TRV-CE1-2)", item.enonce())
                    .hasSize(3);
            assertThat(item.enonce().split("___", -1)).as("'%s' : un seul trou", item.enonce())
                    .hasSize(2);
        }
    }

    @Test
    void acv1DixSeptSujetsNominauxDouzePlurielsDouzeSinguliersEtAucunSujetCoordonne() {
        List<Question> items = items();
        long nominaux = 0;
        long pluriels = 0;
        for (Question item : items) {
            String sujet = sujetDe(item);
            assertThat(sujet).as("'%s' : aucun sujet coordonné (ACV-CE1-1)", item.enonce())
                    .doesNotContain(" et ");
            if (!PRONOMS.contains(sujet)) {
                nominaux++;
            }
            if (sujetPluriel(sujet)) {
                pluriels++;
            }
        }
        assertThat(items).hasSize(24);
        assertThat(nominaux).as("ACV-CE1-1 : sujets nominaux sur 24").isEqualTo(17);
        assertThat(pluriels).as("ACV-CE1-1 : sujets pluriels sur 24").isEqualTo(12);
        assertThat(items.size() - pluriels).as("ACV-CE1-1 : sujets singuliers sur 24").isEqualTo(12);
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
    void k1LeNombreSeulNEstUnCritereQueSurLesItemsOuIlDepartage() {
        verifierQueLeNombreNEstUnCritereQueOuIlDepartage(items());
    }

    @Test
    void trv5AucunIndiceNeContientUnePropositionDeSonItem() {
        verifierQueAucunIndiceNeContientUneDeSesPropositions(items());
    }

    @Test
    void k1DouzeItemsPortentLIndiceDeSubstitutionEtSeptCeluiDeRelecture() {
        List<Question> items = items();
        long substitution = items.stream()
                .filter(i -> i.indice().orElseThrow().startsWith("Remplace le sujet par")).count();
        long relecture = items.stream()
                .filter(i -> i.indice().orElseThrow().startsWith("Lis le sujet avec chaque verbe"))
                .count();
        assertThat(substitution).as("K1 : indice de substitution").isEqualTo(12);
        assertThat(relecture).as("K1 : indice de relecture, dont les items 3 et 15 (ACV-CE1-R2)")
                .isEqualTo(7);
    }

    @Test
    void acvR2HuitItemsAuMoinsASujetPluriel() {
        verifierLaPartDesPlurielsMuets(items());
    }

    @Test
    void acvR2LesVerbesSontCeuxDePrsCe11() {
        verifierQueLesVerbesSontCeuxDePrsCe11(items());
    }

    @Test
    void acvR2LesDistracteursDesPlurielsMuetsSontDeLaMemeFamille() {
        verifierQueLesDistracteursDesPlurielsMuetsSontDeLaMemeFamille(items());
    }

    @Test
    void k2LesCoursesEtLaCuisinePortentIlEtAucunItemNePorteTu() {
        verifierLesSujetsDeCoursesEtLAbsenceDeTu(items());
    }

    @Test
    void k3LaConsigneDuJeuEstCelleDuPresentCe1() {
        verifierQueLaConsigneEstCelleDuPresentCe1(lire(FICHIER_CATALOGUE));
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

    /**
     * ADR-0013 obligation 3 : le jeu fautif de K1. Son premier item porte l'indice du nombre alors
     * que "joues" a le meme nombre que "joue" (TRV-CE1-5).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonIndiceQuiDonneLeNombreSeul() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-accord-sujet-verbe-fautif"));

        assertThatThrownBy(() -> verifierQueLeNombreNEstUnCritereQueOuIlDepartage(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-5");
    }

    /**
     * ADR-0013 obligation 3 : le meme jeu fautif, sur ACV-CE1-R1. Son troisieme item porte la bonne
     * reponse "va" dans son indice.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonIndiceQuiContientUneProposition() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-accord-sujet-verbe-fautif"));

        assertThatThrownBy(() -> verifierQueAucunIndiceNeContientUneDeSesPropositions(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ACV-CE1-R1");
    }

    /** ADR-0013 obligation 3 : le meme jeu fautif, sur K2. Son second item : "elle ___ au marche". */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonSujetFemininAuMarche() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-accord-sujet-verbe-fautif"));

        assertThatThrownBy(() -> verifierLesSujetsDeCoursesEtLAbsenceDeTu(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TRV-CE1-6");
    }

    private static List<Question> itemsFautifsDesPlurielsMuets() {
        return tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-accord-sujet-verbe-pluriels-fautif"));
    }

    /** ADR-0013 obligation 3 : le jeu fautif d'ACV-CE1-R2. Un seul pluriel muet du premier groupe. */
    @Test
    void leJeuFautifCommiteEstRefuseSurSaPartDePlurielsMuets() {
        List<Question> items = itemsFautifsDesPlurielsMuets();

        assertThatThrownBy(() -> verifierLaPartDesPlurielsMuets(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ACV-CE1-R2")
                .hasMessageContaining("1 items à sujet pluriel sur 3");
    }

    /** ADR-0013 obligation 3 : le meme jeu fautif. Son premier item porte "manger", en -ger. */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnVerbeHorsDePrsCe11() {
        List<Question> items = itemsFautifsDesPlurielsMuets();

        assertThatThrownBy(() -> verifierQueLesVerbesSontCeuxDePrsCe11(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("PRS-CE1-1")
                .hasMessageContaining("manger");
    }

    /** ADR-0013 obligation 3 : le meme jeu fautif. Son deuxieme item propose "viens" pour "jouent". */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurDUneAutreFamille() {
        List<Question> items = itemsFautifsDesPlurielsMuets();

        assertThatThrownBy(() -> verifierQueLesDistracteursDesPlurielsMuetsSontDeLaMemeFamille(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ACV-CE1-R2")
                .hasMessageContaining("viens");
    }

    /** ADR-0013 obligation 3 : le catalogue fautif de K3. Sa consigne n'est pas celle du present. */
    @Test
    void leCatalogueFautifCommiteEstRefuseSurSaConsigne() {
        String catalogue = lireRessource(CONTRE_EXEMPLE_CLASSPATH + "/catalogue.yaml");

        assertThatThrownBy(() -> verifierQueLaConsigneEstCelleDuPresentCe1(catalogue))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("K3");
    }
}
