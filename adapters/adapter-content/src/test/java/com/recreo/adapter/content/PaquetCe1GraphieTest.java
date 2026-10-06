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
import java.text.Normalizer;
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
 * S51-FRA-GRAPHIE ({@code docs/backlog/backlog.md}, sprint 51), le paquet {@code ce1-graphie}, ligne
 * {@code FRA-CE1-22} - choisir la graphie d'un son selon la lettre qui suit. Arbitrage pedagogique
 * prealable de s48 ({@code squad/conformite/ARBITRAGE-PREALABLE-s48-francais-ce1.md}, section 5.13,
 * conditions {@code GRL-CE1-1} a {@code GRL-CE1-3}, et sept conditions transverses), puis conditions
 * 1 a 6 de la section 2 de
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s51-fra-ce1-22-graphie-cedille.md}.
 *
 * <p>La regle d'un item se lit de la difference entre ses deux propositions, sans lire le fichier
 * source : {@code m}/{@code n} devant {@code m}, {@code b} ou {@code p} ; {@code ç}/{@code c}
 * devant {@code a}, {@code o} ou {@code u} ; {@code gu}/{@code ge} contre {@code g}.
 */
class PaquetCe1GraphieTest {

    private static final String PAQUET = "ce1-graphie";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final String CONTRE_EXEMPLE_CLASSPATH = "/contreexemple/PaquetCe1GraphieTest";

    private static final String REGLE_M = "m";
    private static final String REGLE_C = "c/ç";
    private static final String REGLE_G = "g/ge/gu";

    private static final double SEUIL_REGLE_MINIMUM = 0.25;

    private static final double SEUIL_PLUS_LONGUE_MAXIMUM = 0.50;

    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS;

    /** GRL-CE1-3 : les exceptions a la regle du m, exclues. */
    private static final Pattern EXCEPTIONS_DU_M = Pattern.compile(
            "(?<!\\p{L})(bonbons?|néanmoins)(?!\\p{L})", FLAGS);

    private static final String ESPACES = "[\\s\\u00A0]";

    private static final Pattern LETTRE_OU_CHIFFRE = Pattern.compile("[\\p{L}\\p{N}]");

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

    private static String sansAccent(String texte) {
        return Normalizer.normalize(texte, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    /** Le mot cible : le dernier mot de la bonne reponse. */
    private static String motCible(Question item) {
        List<String> mots = mots(item.bonneReponse());
        return mots.get(mots.size() - 1);
    }

    /**
     * GRL-CE1-2 et section 2 point 1 : la regle que l'item decide, ou un echec nomme. La bonne
     * reponse porte toujours la forme marquee, jamais le distracteur.
     */
    private static String regleDe(Question item) {
        assertThat(item.distracteurs()).as("'%s' : un seul distracteur (TRV-CE1-2)",
                item.bonneReponse()).hasSize(1);
        String bonne = item.bonneReponse();
        String fautive = item.distracteurs().get(0);
        assertThat(bonne).as("deux propositions distinctes").isNotEqualTo(fautive);

        if (bonne.length() == fautive.length()) {
            List<Integer> positions = new ArrayList<>();
            for (int i = 0; i < bonne.length(); i++) {
                if (bonne.charAt(i) != fautive.charAt(i)) {
                    positions.add(i);
                }
            }
            if (positions.size() == 1) {
                int i = positions.get(0);
                char juste = bonne.charAt(i);
                char faux = fautive.charAt(i);
                char suivante = i + 1 < bonne.length() ? bonne.charAt(i + 1) : ' ';
                char precedente = i > 0 ? bonne.charAt(i - 1) : ' ';
                // Fiche de nadia-prof du 2026-10-06 : la regle du m decide un son nasal, donc m devant b
                // ou p apres une voyelle ; une consonne double (pomme, flamme) n'en porte aucun.
                if (juste == 'm' && faux == 'n' && "bp".indexOf(suivante) >= 0
                        && "aeiouyéè".indexOf(precedente) >= 0) {
                    return REGLE_M;
                }
                if (juste == 'ç' && faux == 'c' && "aou".indexOf(suivante) >= 0) {
                    return REGLE_C;
                }
            }
        } else if (bonne.length() == fautive.length() + 1) {
            for (int i = 0; i < fautive.length(); i++) {
                if (fautive.charAt(i) == 'g' && bonne.charAt(i) == 'g') {
                    String reconstituee = fautive.substring(0, i + 1) + bonne.charAt(i + 1)
                            + fautive.substring(i + 1);
                    char inseree = bonne.charAt(i + 1);
                    if (reconstituee.equals(bonne) && (inseree == 'u' || inseree == 'e')) {
                        return REGLE_G;
                    }
                }
            }
        }
        throw new AssertionError("'" + bonne + "' / '" + fautive + "' : les deux propositions ne"
                + " different pas par la graphie que la regle decide (GRL-CE1-2, section 2"
                + " point 1)");
    }

    private static List<String> reglesDe(List<Question> items) {
        return items.stream().map(PaquetCe1GraphieTest::regleDe).toList();
    }

    @Test
    void adr0081CePaquetDeclareFraCe122EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("FRA-CE1-22"));
        Map<?, ?> descripteur = (Map<?, ?>) corps.get("descripteur");
        assertThat(descripteur.get("niveau")).isEqualTo("CE1");
    }

    @Test
    void trv7LePaquetCompteAuMoinsVingtItems() {
        assertThat(items()).as("TRV-CE1-7 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void grl2ChaqueItemDecideUneRegleParLaSeuleGraphieQuiLaFait() {
        assertThat(reglesDe(items())).isNotEmpty();
    }

    @Test
    void grl1ChacuneDesTroisReglesEstDecideeParUnQuartDesItemsAuMoins() {
        List<String> regles = reglesDe(items());
        Map<String, Long> parRegle = new TreeMap<>();
        regles.forEach(r -> parRegle.merge(r, 1L, Long::sum));
        assertThat(parRegle.keySet()).as("GRL-CE1-1 : les trois regles sont servies")
                .containsExactlyInAnyOrder(REGLE_M, REGLE_C, REGLE_G);
        parRegle.forEach((regle, compte) -> assertThat((double) compte / regles.size())
                .as("GRL-CE1-1 : regle '%s', %d items sur %d", regle, compte, regles.size())
                .isGreaterThanOrEqualTo(SEUIL_REGLE_MINIMUM));
    }

    @Test
    void grl3AucuneExceptionALaRegleDuMNEstServie() {
        for (Question item : items()) {
            for (String proposition : List.of(item.bonneReponse(), item.distracteurs().get(0))) {
                assertThat(EXCEPTIONS_DU_M.matcher(proposition).find())
                        .as("'%s' : aucune exception a la regle du m (GRL-CE1-3)", proposition)
                        .isFalse();
            }
        }
    }

    @Test
    void trv2LaBonneReponseNEstStrictementLaPlusLongueQueSurLaMoitieDesItemsAuPlus() {
        List<Question> items = items();
        long plusLongue = items.stream()
                .filter(i -> i.bonneReponse().length() > i.distracteurs().get(0).length()).count();
        assertThat((double) plusLongue / items.size())
                .as("TRV-CE1-2 : %d items ou la bonne reponse est strictement la plus longue sur %d",
                        plusLongue, items.size())
                .isLessThanOrEqualTo(SEUIL_PLUS_LONGUE_MAXIMUM);
    }

    @Test
    void section2Point4LeMotCibleNEstEcritNiDansLEnonceNiDansLIndice() {
        for (Question item : items()) {
            String cible = sansAccent(motCible(item)).toLowerCase(Locale.FRENCH);
            for (String champ : List.of(item.enonce(), item.indice().orElse(""))) {
                assertThat(sansAccent(champ).toLowerCase(Locale.FRENCH))
                        .as("'%s' : le mot cible '%s' n'est ni dans l'enonce ni dans l'indice"
                                + " (section 2, point 4)", item.bonneReponse(), cible)
                        .doesNotContain(cible);
            }
        }
    }

    @Test
    void section2Point5ChaqueIndiceEstUneMethodeDeDouzeMotsAuPlusQuiNeNommePasLaCedille() {
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items()) {
            String indice = item.indice().orElseThrow(
                    () -> new AssertionError("'" + item.bonneReponse() + "' : indice manquant"));
            indicesDistincts.add(indice);
            assertThat(mots(indice)).as("'%s' : douze mots au plus (TRV-CE1-5)", indice)
                    .hasSizeLessThanOrEqualTo(12);
            assertThat(sansAccent(indice).toLowerCase(Locale.FRENCH))
                    .as("'%s' : l'indice ne nomme pas la cedille (section 2, point 5)", indice)
                    .doesNotContain("cedille");
        }
        assertThat(indicesDistincts).as("TRV-CE1-5 : l'indice n'est pas identique sur tout le paquet")
                .hasSizeGreaterThan(1);
    }

    /**
     * Section 2, point 6 : l'enfant ne quitte pas la manche avec la forme fautive sous les yeux. Le
     * paquet ne porte aucune explication (ChaquePaquetExpliqueSesItemsAVariationTest l'imposerait
     * avec un enonce qui nomme le mot, ce que le point 4 interdit) : la forme fautive ne figure donc
     * que dans le distracteur.
     */
    @Test
    void section2Point6LaFormeFautiveNeFigureQueDansLeDistracteur() {
        for (Question item : items()) {
            assertThat(item.explication()).as("'%s' : aucune explication", item.bonneReponse())
                    .isEmpty();
            String fautive = item.distracteurs().get(0);
            assertThat(item.enonce() + " " + item.indice().orElse(""))
                    .as("la forme fautive '%s' n'est ni dans l'enonce ni dans l'indice", fautive)
                    .doesNotContain(fautive);
        }
    }

    @Test
    void lon5ChaquePropositionCompteAuPlusSixMotsEtUneSeulePhrase() {
        for (Question item : items()) {
            for (String proposition : List.of(item.bonneReponse(), item.distracteurs().get(0))) {
                assertThat(mots(proposition))
                        .as("'%s' : au plus six mots (LON-CE1-5)", proposition)
                        .hasSizeLessThanOrEqualTo(6);
            }
        }
    }

    /**
     * Rejeu de s51 (fiche de nadia-prof) : "pomme" / "ponme" oppose deux formes sans son nasal, que
     * l'enfant departage en lisant. La regle du m n'est decidee que devant b ou p, apres une voyelle.
     */
    @Test
    void leJeuFautifDeLaConsonneDoubleEstRefuse() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-graphie-fautif-consonne-double"));

        assertThatThrownBy(() -> reglesDe(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GRL-CE1-2");
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. Son distracteur perd le m et le b, la
     * ou la regle du m ne decide qu'un m devenu n (GRL-CE1-2).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurUnDistracteurQuiNeDifferePasParLaGraphieDeLaRegle() {
        List<Question> items = tousLesItems(new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-graphie-fautif"));

        assertThatThrownBy(() -> reglesDe(items))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("GRL-CE1-2");
    }
}
