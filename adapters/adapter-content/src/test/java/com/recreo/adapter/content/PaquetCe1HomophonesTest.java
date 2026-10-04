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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S50-FRA-HOMOPHONES ({@code docs/backlog/backlog.md}, sprint 50), le paquet
 * {@code ce1-homophones}, ligne {@code FRA-CE1-16} - distinguer les homophones grammaticaux
 * {@code a}/{@code à} et {@code et}/{@code est}. Arbitrage pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.7, conditions
 * {@code HOM-CE1-1} a {@code HOM-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), et avis de faisabilite de theo-archi
 * ({@code squad/rapports/avis-faisabilite-s50-cinq-stories.md}, section 4, V2).
 *
 * <p><b>A la difference de {@code PaquetCe1ValeurLettreTest}, aucun marqueur de commentaire YAML
 * n'est necessaire.</b> Les deux paires admises, {@code {a, à}} et {@code {et, est}}, sont un
 * ensemble ferme : la paire d'un item se lit directement depuis sa bonne reponse et son
 * distracteur, tels que charges par {@link YamlContentPackLoader}, sans relire le fichier source.
 *
 * <p><b>Le compte de mots de l'indice (TRV-CE1-5) suit la convention de nadia-prof</b>
 * ({@code AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots}, non visible depuis ce module) :
 * un token compte pour un mot des lors qu'il porte une lettre ou un chiffre - {@code split("\\s+")}
 * compterait a tort un {@code ":"} isole comme un mot.
 */
class PaquetCe1HomophonesTest {

    private static final String PAQUET = "ce1-homophones";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1HomophonesTest";

    private static final String MARQUEUR_DE_TROU = "___";

    /** HOM-CE1-1 : les deux seules paires admises. */
    private static final Set<String> PAIRE_A_A = Set.of("a", "à");
    private static final Set<String> PAIRE_ET_EST = Set.of("et", "est");
    private static final Set<Set<String>> PAIRES_VALIDES = Set.of(PAIRE_A_A, PAIRE_ET_EST);

    private static final double SEUIL_PAIRE_MINIMUM = 1.0 / 3;

    private static final double SEUIL_FORME_MINIMUM = 1.0 / 3;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    /**
     * Les espaces que le contenu servi emploie reellement : l'espace ordinaire et l'espace
     * insecable. Convention de comptage de nadia-prof, reprise sans un mot de changement
     * (voir la Javadoc de la classe).
     */
    private static final String ESPACES = "[\\s\\u00A0]";

    /** Un token est un mot des lors qu'il porte une lettre ou un chiffre. */
    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    /**
     * TRV-CE1-4 : les mots de grammaire qu'un enfant de CE1 ne lit pas seul, exclus de l'enonce et
     * de l'indice.
     */
    private static final List<String> LEXIQUE_TRV_CE1_4 = List.of(
            "graphème", "graphèmes", "phonème", "phonèmes", "préfixe", "préfixes", "suffixe",
            "suffixes", "radical", "radicaux", "participe", "participes", "auxiliaire",
            "auxiliaires", "complément", "compléments", "antonyme", "antonymes", "homophone",
            "homophones", "inférence", "inférences");

    private static Pattern verificateurLexique(List<String> mots) {
        return Pattern.compile("\\b(" + String.join("|", mots) + ")\\b",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
    }

    private static final Pattern VERIFICATEUR_TRV_CE1_4 = verificateurLexique(LEXIQUE_TRV_CE1_4);

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les mots d'un texte, sous la convention de comptage de nadia-prof. */
    private static List<String> mots(String texte) {
        List<String> mots = new ArrayList<>();
        for (String token : texte.strip().split(ESPACES + "+")) {
            if (LETTRE_OU_CHIFFRE.matcher(token).find()) {
                mots.add(token);
            }
        }
        return mots;
    }

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
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

    private record ItemAnalyse(Question item, Set<String> paire, String motDeSubstitution) {
    }

    private static int comptageDeTrous(String enonce) {
        int compte = 0;
        int index = enonce.indexOf(MARQUEUR_DE_TROU);
        while (index >= 0) {
            compte++;
            index = enonce.indexOf(MARQUEUR_DE_TROU, index + MARQUEUR_DE_TROU.length());
        }
        return compte;
    }

    /** HOM-CE1-1 : le mot que la methode de substitution attend pour la paire de l'item. */
    private static String motDeSubstitution(Set<String> paire) {
        if (PAIRE_A_A.equals(paire)) {
            return "avait";
        }
        if (PAIRE_ET_EST.equals(paire)) {
            return "était";
        }
        throw new IllegalArgumentException("paire inconnue : " + paire);
    }

    /**
     * HOM-CE1-2, HOM-CE1-1 (perimetre) et TRV-CE1-2 : la morsure item par item, sans lecture du
     * fichier source.
     */
    private static ItemAnalyse analyser(Question item) {
        assertThat(comptageDeTrous(item.enonce()))
                .as("'%s' : exactement un trou '___' par enonce (HOM-CE1-2)", item.enonce())
                .isEqualTo(1);

        assertThat(item.distracteurs())
                .as("'%s' : un seul distracteur (TRV-CE1-2)", item.enonce()).hasSize(1);
        String bonne = item.bonneReponse().toLowerCase(Locale.FRENCH);
        String distracteur = item.distracteurs().get(0).toLowerCase(Locale.FRENCH);
        assertThat(bonne)
                .as("'%s' : deux propositions distinctes (TRV-CE1-2)", item.enonce())
                .isNotEqualTo(distracteur);

        Set<String> paire = Set.of(bonne, distracteur);
        assertThat(PAIRES_VALIDES)
                .as("'%s' : la paire %s n'est ni {a,à} ni {et,est} (HOM-CE1-1)", item.enonce(), paire)
                .contains(paire);

        return new ItemAnalyse(item, paire, motDeSubstitution(paire));
    }

    private static List<ItemAnalyse> itemsAnalyses(ContentSource source) {
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(source)) {
            analyses.add(analyser(item));
        }
        return analyses;
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        return itemsAnalyses(paquet(PAQUET));
    }

    /** HOM-CE1-3, TRV-CE1-5 : la methode de substitution est donnee, la bonne reponse ne l'est pas. */
    private static void verifierIndices(List<ItemAnalyse> analyses) {
        Set<String> indicesDistincts = new HashSet<>();
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.item();
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);

            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots (TRV-CE1-5)", indice)
                    .hasSizeLessThanOrEqualTo(12);

            Pattern motDeSubstitution = Pattern.compile(
                    "\\b" + Pattern.quote(analyse.motDeSubstitution()) + "\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
            assertThat(motDeSubstitution.matcher(indice).find())
                    .as("'%s' : l'indice doit donner la methode de substitution '%s' (HOM-CE1-3)",
                            indice, analyse.motDeSubstitution())
                    .isTrue();

            Pattern motReponse = Pattern.compile(
                    "\\b" + Pattern.quote(item.bonneReponse()) + "\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);
            assertThat(motReponse.matcher(indice).find())
                    .as("'%s' : l'indice revele la bonne reponse '%s' (HOM-CE1-3)", indice,
                            item.bonneReponse())
                    .isFalse();
        }
        assertThat(indicesDistincts)
                .as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void adr0081CePaquetDeclareFraCe116EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-16"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems(paquet(PAQUET)))
                .as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void hom2Hom1TrvLePerimetreEtLaFormeSontTenusSurChaqueItem() {
        // La morsure est dans analyser() : un seul distracteur, deux propositions distinctes, un
        // seul trou, une paire fermee a {a,à} ou {et,est}.
        assertThat(itemsAnalyses()).isNotEmpty();
    }

    @Test
    void hom1ChaquePaireEtChaqueFormeAttaintSonSeuil() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        Map<String, List<ItemAnalyse>> parPaire = new TreeMap<>();
        for (ItemAnalyse analyse : analyses) {
            String cle = analyse.paire().equals(PAIRE_A_A) ? "a/à" : "et/est";
            parPaire.computeIfAbsent(cle, c -> new ArrayList<>()).add(analyse);
        }
        assertThat(parPaire.keySet())
                .as("HOM-CE1-1 : les deux paires doivent toutes etre servies")
                .containsExactlyInAnyOrder("a/à", "et/est");
        parPaire.forEach((paire, itemsDeLaPaire) -> {
            assertThat((double) itemsDeLaPaire.size() / total)
                    .as("HOM-CE1-1 : paire '%s', %d items sur %d", paire, itemsDeLaPaire.size(), total)
                    .isGreaterThanOrEqualTo(SEUIL_PAIRE_MINIMUM);
            Map<String, Long> parForme = new TreeMap<>();
            for (ItemAnalyse analyse : itemsDeLaPaire) {
                parForme.merge(analyse.item().bonneReponse().toLowerCase(Locale.FRENCH), 1L,
                        Long::sum);
            }
            assertThat(parForme.keySet())
                    .as("HOM-CE1-1 : paire '%s' doit servir ses deux formes comme bonne reponse",
                            paire)
                    .hasSize(2);
            parForme.forEach((forme, compte) -> assertThat((double) compte / itemsDeLaPaire.size())
                    .as("HOM-CE1-1 : paire '%s', forme '%s', %d items sur %d", paire, forme, compte,
                            itemsDeLaPaire.size())
                    .isGreaterThanOrEqualTo(SEUIL_FORME_MINIMUM));
        });
    }

    @Test
    void trv2LaBonneReponseNestStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long strictementLaPlusLongue = analyses.stream()
                .filter(a -> a.item().bonneReponse().length()
                        > a.item().distracteurs().get(0).length())
                .count();
        assertThat((double) strictementLaPlusLongue / total)
                .as("TRV-CE1-2 : la bonne reponse n'est strictement la plus longue que sur la"
                        + " moitie des items au plus")
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceOuLIndice() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(VERIFICATEUR_TRV_CE1_4.matcher(item.enonce()).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'enonce", item.enonce())
                    .isFalse();
            item.indice().ifPresent(indice -> assertThat(VERIFICATEUR_TRV_CE1_4.matcher(indice).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'indice", item.enonce())
                    .isFalse());
        }
    }

    @Test
    void hom3ChaqueIndiceDonneLaMethodeDeSubstitutionSansRevelerLaBonneReponse() {
        verifierIndices(itemsAnalyses());
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMotsEtUneSeulePhrase() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item declare une paire valide
     * ({@code et}/{@code est}) mais un indice generique qui ne donne jamais la methode de
     * substitution par "etait" (HOM-CE1-3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonIndiceSansMethodeDeSubstitution() {
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-homophones-fautif");

        assertThatThrownBy(() -> verifierIndices(itemsAnalyses(source)))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("HOM-CE1-3");
    }
}
