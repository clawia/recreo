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
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S51-FRA-TEMPS ({@code docs/backlog/backlog.md}, sprint 51), le paquet {@code ce1-temps}, ligne
 * {@code FRA-CE1-20} - situer une phrase dans le temps. Arbitrage pedagogique prealable de s48
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.11, conditions
 * {@code TPS-CE1-1} a {@code TPS-CE1-3} et sept conditions transverses {@code TRV-CE1-1} a
 * {@code TRV-CE1-7}, section 2), et avis de faisabilite de theo-archi
 * ({@code squad/sprints/s51-trois-instances-francais-sciences-maths/avis-faisabilite.md}, V2).
 *
 * <p>Les trois propositions sont fixes : le temps du verbe se lit donc depuis la bonne reponse, et
 * la coherence du mot de temps ou la forme du verbe se verifie contre elle, sans lire le fichier
 * source. Le temps du verbe lui-meme est reconnu par sa forme : {@code a}/{@code ont} suivi d'un
 * participe pour le passe compose, une terminaison de futur simple pour le futur.
 */
class PaquetCe1TempsTest {

    private static final String PAQUET = "ce1-temps";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1TempsTest";

    private static final String PASSE = "passé";
    private static final String PRESENT = "présent";
    private static final String FUTUR = "futur";

    /** TPS-CE1-1 : les trois propositions, toujours les memes. */
    private static final Set<String> TROIS_TEMPS = Set.of(PASSE, PRESENT, FUTUR);

    private static final double SEUIL_TEMPS_MINIMUM = 0.25;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final double SEUIL_SANS_MOT_DE_TEMPS_MINIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    /** TPS-CE1-2 : les mots de temps admis, avec le temps que chacun impose. */
    private static final Map<String, String> TEMPS_DU_MOT = Map.of(
            "hier", PASSE, "aujourd'hui", PRESENT, "maintenant", PRESENT, "demain", FUTUR);

    private static final Pattern MOT_DE_TEMPS = Pattern.compile(
            "(?<![\\p{L}'])(hier|aujourd'hui|maintenant|demain)(?![\\p{L}'])", FLAGS);

    /** TPS-CE1-3 : passe compose avec avoir (auxiliaire puis participe). */
    private static final Pattern PASSE_COMPOSE = Pattern.compile(
            "(?<!\\p{L})(a|ont)\\s+\\p{L}+(é|i|is|it|u|ert)(?!\\p{L})", FLAGS);

    /** TPS-CE1-3 : terminaisons du futur simple. */
    private static final Pattern FUTUR_SIMPLE = Pattern.compile(
            "\\p{L}+(ra|ras|rai|rons|rez|ront)(?!\\p{L})", FLAGS);

    /** TPS-CE1-3 : le futur proche (aller conjugue puis infinitif) et l'imparfait sont exclus. */
    private static final Pattern FUTUR_PROCHE = Pattern.compile(
            "(?<!\\p{L})(vais|vas|va|allons|allez|vont)\\s+\\p{L}+(er|ir|re)(?!\\p{L})", FLAGS);

    private static final Pattern IMPARFAIT = Pattern.compile(
            "\\p{L}+(ait|aient|ais|ions|iez)(?!\\p{L})", FLAGS);

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

    /** TRV-CE1-4 : les mots de grammaire qu'un enfant de CE1 ne lit pas seul. */
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

    private static List<Question> items() {
        return tousLesItems(paquet(PAQUET));
    }

    private static Set<String> propositions(Question item) {
        Set<String> propositions = new HashSet<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    /** TPS-CE1-2 : le mot de temps d'un enonce, vide s'il n'en porte aucun ; jamais deux. */
    private static Optional<String> motDeTemps(Question item) {
        List<String> trouves = new ArrayList<>();
        Matcher recherche = MOT_DE_TEMPS.matcher(item.enonce());
        while (recherche.find()) {
            trouves.add(recherche.group(1).toLowerCase(Locale.FRENCH));
        }
        assertThat(trouves).as("'%s' : au plus un mot de temps (TPS-CE1-2)", item.enonce())
                .hasSizeLessThanOrEqualTo(1);
        return trouves.stream().findFirst();
    }

    /** TPS-CE1-2 : le mot de temps est toujours coherent avec le verbe, donc avec la bonne reponse. */
    private static void verifierCoherenceDesMotsDeTemps(List<Question> items) {
        for (Question item : items) {
            motDeTemps(item).ifPresent(mot -> assertThat(item.bonneReponse())
                    .as("'%s' : le mot de temps '%s' est incoherent avec le temps du verbe"
                            + " (TPS-CE1-2)", item.enonce(), mot)
                    .isEqualTo(TEMPS_DU_MOT.get(mot)));
        }
    }

    @Test
    void adr0081CePaquetDeclareFraCe120EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-20"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(items()).as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void tps1ChaqueItemPorteLesTroisTempsEtChacunEstBonneReponseDUnQuartDesItemsAuMoins() {
        List<Question> items = items();
        for (Question item : items) {
            assertThat(item.distracteurs()).as("'%s' : deux distracteurs", item.enonce())
                    .hasSize(2);
            assertThat(propositions(item))
                    .as("'%s' : passé, présent et futur (TPS-CE1-1)", item.enonce())
                    .isEqualTo(TROIS_TEMPS);
        }
        Map<String, Long> parTemps = new TreeMap<>();
        items.forEach(i -> parTemps.merge(i.bonneReponse(), 1L, Long::sum));
        assertThat(parTemps.keySet()).as("TPS-CE1-1 : les trois temps sont bonne reponse")
                .isEqualTo(TROIS_TEMPS);
        parTemps.forEach((temps, compte) -> assertThat((double) compte / items.size())
                .as("TPS-CE1-1 : '%s', %d items sur %d", temps, compte, items.size())
                .isGreaterThanOrEqualTo(SEUIL_TEMPS_MINIMUM));
    }

    @Test
    void trv2PresentEstStrictementLaPlusLongueEtBonneReponseSurLaMoitieDesItemsAuPlus() {
        for (String autre : List.of(PASSE, FUTUR)) {
            assertThat(PRESENT.length()).as("'présent' strictement plus long que '%s'", autre)
                    .isGreaterThan(autre.length());
        }
        List<Question> items = items();
        long presentBonneReponse = items.stream()
                .filter(i -> PRESENT.equals(i.bonneReponse())).count();
        assertThat((double) presentBonneReponse / items.size())
                .as("TRV-CE1-2 : la bonne reponse strictement la plus longue au plus sur la moitie"
                        + " des items (présent : %d sur %d)", presentBonneReponse, items.size())
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
    }

    @Test
    void tps2AuMoinsLaMoitieDesItemsNePorteAucunMotDeTempsEtLesAutresSontCoherents() {
        List<Question> items = items();
        long sansMotDeTemps = items.stream().filter(i -> motDeTemps(i).isEmpty()).count();
        assertThat((double) sansMotDeTemps / items.size())
                .as("TPS-CE1-2 : %d items sans mot de temps sur %d", sansMotDeTemps, items.size())
                .isGreaterThanOrEqualTo(SEUIL_SANS_MOT_DE_TEMPS_MINIMUM);
        verifierCoherenceDesMotsDeTemps(items);
    }

    @Test
    void tps3LeVerbeEstAuPasseComposeAuFuturSimpleOuAuPresentSansFuturProcheNiImparfait() {
        for (Question item : items()) {
            String enonce = item.enonce();
            assertThat(FUTUR_PROCHE.matcher(enonce).find())
                    .as("'%s' : aucun futur proche (TPS-CE1-3)", enonce).isFalse();
            assertThat(IMPARFAIT.matcher(enonce).find())
                    .as("'%s' : aucun imparfait (TPS-CE1-3)", enonce).isFalse();
            boolean passeCompose = PASSE_COMPOSE.matcher(enonce).find();
            boolean futurSimple = FUTUR_SIMPLE.matcher(enonce).find();
            assertThat(passeCompose && futurSimple)
                    .as("'%s' : un seul verbe a la fois", enonce).isFalse();
            assertThat(passeCompose)
                    .as("'%s' : passé composé si et seulement si la bonne reponse est 'passé'"
                            + " (TPS-CE1-3)", enonce)
                    .isEqualTo(PASSE.equals(item.bonneReponse()));
            assertThat(futurSimple)
                    .as("'%s' : futur simple si et seulement si la bonne reponse est 'futur'"
                            + " (TPS-CE1-3)", enonce)
                    .isEqualTo(FUTUR.equals(item.bonneReponse()));
        }
    }

    @Test
    void trv4AucunMotDuLexiqueExcluNEstServiDansLEnonceOuLIndice() {
        for (Question item : items()) {
            assertThat(VERIFICATEUR_TRV_CE1_4.matcher(item.enonce()).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'enonce", item.enonce())
                    .isFalse();
            item.indice().ifPresent(indice -> assertThat(VERIFICATEUR_TRV_CE1_4.matcher(indice).find())
                    .as("'%s' : aucun mot du lexique exclu de TRV-CE1-4 dans l'indice", item.enonce())
                    .isFalse());
        }
    }

    @Test
    void trv5ChaqueIndiceCompteAuPlusDouzeMotsEtNeNommeAucunTemps() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.enonce() + "' : indice manquant (TRV-CE1-5)"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : indice d'au plus douze mots (TRV-CE1-5)", indice)
                    .hasSizeLessThanOrEqualTo(12);
            for (String temps : TROIS_TEMPS) {
                assertThat(Pattern.compile("(?<!\\p{L})" + temps + "(?!\\p{L})", FLAGS)
                        .matcher(indice).find())
                        .as("'%s' : l'indice ne nomme aucun temps '%s' (TRV-CE1-5)", indice, temps)
                        .isFalse();
            }
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMots() {
        for (Question item : items()) {
            for (String proposition : propositions(item)) {
                assertThat(mots(proposition))
                        .as("'%s' : proposition '%s' d'au plus six mots (LON-CE1-5)", item.enonce(),
                                proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son seul item porte "demain" avec un
     * verbe au passe compose et la bonne reponse "passé" (TPS-CE1-2).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonMotDeTempsIncoherent() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-temps-fautif"));

        assertThatThrownBy(() -> verifierCoherenceDesMotsDeTemps(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TPS-CE1-2");
    }
}
