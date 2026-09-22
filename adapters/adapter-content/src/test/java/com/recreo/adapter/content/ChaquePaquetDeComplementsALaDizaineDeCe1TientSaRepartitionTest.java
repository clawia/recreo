package com.recreo.adapter.content;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * CDZ-CE1-1 et CDZ-CE1-2, arbitrage de nadia-prof du 2026-09-22
 * ({@code squad/conformite/ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md},
 * section 2) : <b>dans un paquet de complements a la dizaine de CE1, chaque item est de la forme
 * {@code a + ? = d} avec {@code a} entre 1 et 99, non multiple de dix, et {@code d} le plus petit
 * multiple de dix strictement superieur a {@code a} ; sur l'ensemble du paquet, les neuf valeurs de
 * complement (1 a 9) apparaissent chacune au moins une fois, et les valeurs de {@code a} couvrent au
 * moins cinq des neuf dizaines possibles.</b>
 *
 * <p><b>Le perimetre est derive de la competence declaree, pas de la forme de l'enonce</b>
 * (ADR-0097, decision 2). Contrairement a {@link ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest},
 * qui reconnait ses additions par un motif sur l'enonce, ce garde-fou selectionne d'abord les paquets
 * dont le descripteur declare le niveau {@code CE1}, la matiere <i>Mathematiques</i> et la competence
 * {@link ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.SousPartieDeMathematiquesCe1#COMPLEMENTS_A_LA_DIZAINE},
 * puis lit l'expression arithmetique <b>dans les items</b> du paquet ainsi selectionne. C'est la
 * raison d'etre de la decision 2 de cet ADR : un enonce en francais libre qui servirait la meme
 * competence sortirait d'un perimetre derive de l'enonce sans qu'aucune ligne ne rougisse.
 *
 * <p><b>Derivation totale</b> (ADR-0013, obligation 2) : un paquet qui declare la competence gardee
 * mais dont un item ne s'ecrit pas {@code a + ? = d} est un <b>echec</b> de ce garde-fou, jamais un
 * paquet silencieusement retire de sa population - c'est exactement le mode de defaillance muet
 * qu'ADR-0097 existe pour fermer.
 *
 * <p><b>Le second distracteur exige l'amendement du 2026-09-22 (section 2.3) pour exister sans faire
 * rougir {@code LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest}</b> : la clause d'origine
 * (barree dans l'arbitrage) rendait {@code d} systematiquement la plus grande des trois propositions.
 * Ce garde-fou ne verifie pas les distracteurs eux-memes - c'est le role du rang de la bonne reponse,
 * deja garde ailleurs - il verifie les deux clauses chiffrees sur {@code a} et {@code d}.
 */
class ChaquePaquetDeComplementsALaDizaineDeCe1TientSaRepartitionTest {

    private static final Path CATALOGUE_SERVI = Path.of("src/main/resources/catalogue.yaml");

    private static final Path CONTENU_SERVI = Path.of("src/main/resources/contenu");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/ChaquePaquetDeComplementsALaDizaineDeCe1TientSaRepartitionTest");

    private static final String NIVEAU_GARDE = "CE1";

    /** Forme repliee de la matiere gardee, meme motif qu'ADR-0049 (voir la classe soeur). */
    private static final String MATIERE_GARDEE = "mathematiques";

    private static final String COMPETENCE_GARDEE = ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest
            .SousPartieDeMathematiquesCe1.COMPLEMENTS_A_LA_DIZAINE.competence();

    /**
     * Meme classe de blancs que {@link ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest} :
     * l'espace insecable (ADR-0067, TYP-1) que tout enonce conforme du depot porte devant son point
     * d'interrogation n'est pas couverte par {@code \s}.
     */
    private static final String BLANC = "[\\s\\u00A0\\u202F]";

    private static final Pattern COMPLEMENT = Pattern.compile(
            "^" + BLANC + "*(\\d+)" + BLANC + "*\\+" + BLANC + "*\\?" + BLANC + "*="
                    + BLANC + "*(\\d+)" + BLANC + "*$");

    /** Un paquet reduit a ce que cette regle juge : son identifiant et ses couples (a, d). */
    record PaquetDeComplements(String id, List<int[]> couples) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * Le recensement : tout paquet servi dont le descripteur declare le niveau, la matiere et la
     * competence gardes. Un item qui ne s'ecrit pas {@code a + ? = d} entre dans le paquet avec la
     * paire {@code null} plutot que d'en sortir en silence : c'est {@link #verifier} qui le refuse.
     */
    static List<PaquetDeComplements> paquetsDeComplementsDuNiveau(Path catalogue,
            Path repertoireDeContenu) {
        if (AucunTitreDeJeuNePorteSonNiveauTest.entreesServies(catalogue, repertoireDeContenu)
                .isEmpty()) {
            throw new AssertionError("ADR-0051 : aucune entree de catalogue, le recensement de ce"
                    + " garde-fou n'a alors rien lu");
        }
        List<PaquetDeComplements> paquets = new ArrayList<>();
        try (Stream<Path> arborescence = Files.walk(repertoireDeContenu)) {
            arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".yaml"))
                    .sorted()
                    .forEach(fichier -> retenir(fichier, paquets));
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return paquets;
    }

    private static void retenir(Path fichier, List<PaquetDeComplements> paquets) {
        if (!(new Yaml().load(lire(fichier)) instanceof Map<?, ?> corps)
                || !(corps.get("descripteur") instanceof Map<?, ?> descripteur)
                || !NIVEAU_GARDE.equals(String.valueOf(descripteur.get("niveau")).strip())
                || !MATIERE_GARDEE.equals(ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest
                        .replie(String.valueOf(descripteur.get("matiere"))))
                || !COMPETENCE_GARDEE.equals(String.valueOf(descripteur.get("competence")).strip())
                || !(corps.get("items") instanceof List<?> items)) {
            return;
        }
        List<int[]> couples = new ArrayList<>();
        for (Object item : items) {
            if (!(item instanceof Map<?, ?> champs)) {
                couples.add(null);
                continue;
            }
            Matcher complement = COMPLEMENT.matcher(String.valueOf(champs.get("enonce")));
            couples.add(complement.matches()
                    ? new int[] {Integer.parseInt(complement.group(1)),
                            Integer.parseInt(complement.group(2))}
                    : null);
        }
        paquets.add(new PaquetDeComplements(String.valueOf(corps.get("id")), couples));
    }

    /**
     * Le juge, pur : il ne lit ni disque ni classpath et recoit exactement ce que le recensement
     * produit.
     */
    static void verifier(List<PaquetDeComplements> paquets) {
        if (paquets.isEmpty()) {
            throw new AssertionError("ADR-0051 : aucun paquet de complements a la dizaine de "
                    + NIVEAU_GARDE + " dans le contenu servi. CDZ-CE1-1 et CDZ-CE1-2 ne portent"
                    + " alors sur rien, et un garde-fou qui ne trouve plus sa population le dit au"
                    + " lieu de passer");
        }
        List<String> fautes = new ArrayList<>();
        for (PaquetDeComplements paquet : paquets) {
            TreeSet<Integer> complementsVus = new TreeSet<>();
            TreeSet<Integer> dizainesVues = new TreeSet<>();
            for (int rang = 0; rang < paquet.couples().size(); rang++) {
                int[] couple = paquet.couples().get(rang);
                if (couple == null) {
                    fautes.add(paquet.id() + " (item " + rang + ") : cet item ne s'ecrit pas"
                            + " \"a + ? = d\", et il appartient a un paquet qui declare la competence"
                            + " \"" + COMPETENCE_GARDEE + "\" (ADR-0097) - un membre de la population"
                            + " qui ne porte pas ce dont la derivation a besoin est un echec, jamais"
                            + " un item ignore (ADR-0013)");
                    continue;
                }
                int a = couple[0];
                int d = couple[1];
                if (a < 1 || a > 99 || a % 10 == 0) {
                    fautes.add(paquet.id() + " (item " + rang + ") : a = " + a + " hors des bornes"
                            + " de CDZ-CE1-1 (1 a 99, jamais multiple de dix)");
                    continue;
                }
                int dizaineSuivante = a - (a % 10) + 10;
                if (d != dizaineSuivante) {
                    fautes.add(paquet.id() + " (item " + rang + ") : d = " + d + " n'est pas le"
                            + " plus petit multiple de dix strictement superieur a a = " + a
                            + " (attendu " + dizaineSuivante + "), CDZ-CE1-1");
                    continue;
                }
                complementsVus.add(d - a);
                dizainesVues.add(a / 10);
            }
            for (int complement = 1; complement <= 9; complement++) {
                if (!complementsVus.contains(complement)) {
                    fautes.add(paquet.id() + " : le complement " + complement + " n'apparait dans"
                            + " aucun item, CDZ-CE1-2 exige que les neuf valeurs (1 a 9) apparaissent"
                            + " chacune au moins une fois");
                }
            }
            if (dizainesVues.size() < 5) {
                fautes.add(paquet.id() + " : les valeurs de a ne couvrent que " + dizainesVues.size()
                        + " dizaine(s) sur les neuf possibles, CDZ-CE1-2 en exige au moins cinq");
            }
        }
        if (!fautes.isEmpty()) {
            throw new AssertionError("repartition des complements a la dizaine (CDZ-CE1-1,"
                    + " CDZ-CE1-2) : " + fautes);
        }
    }

    @Test
    void chaquePaquetDeComplementsALaDizaineDeCe1ServiTientSaRepartition() {
        verifier(paquetsDeComplementsDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI));
    }

    /**
     * Le recensement atteint bien le materiau reel. Sans cette assertion, les morsures ci-dessous
     * pourraient toutes passer sur une population que le recensement n'a jamais trouvee.
     */
    @Test
    void leRecensementTrouveLePaquetDeComplementsALaDizaineServi() {
        assertThat(paquetsDeComplementsDuNiveau(CATALOGUE_SERVI, CONTENU_SERVI))
                .extracting(PaquetDeComplements::id)
                .contains("ce1-complements-dizaine");
    }

    /**
     * Morsure 1 : un item hors bornes de CDZ-CE1-1 (a multiple de dix, cas exclu explicitement par
     * l'arbitrage).
     */
    @Test
    void unItemDontAEstMultipleDeDixEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        for (int complement = 1; complement <= 9; complement++) {
            couples.add(new int[] {10 + complement, 20});
        }
        couples.add(new int[] {40, 50});
        List<PaquetDeComplements> paquets = List.of(new PaquetDeComplements("a-multiple-de-dix",
                couples));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("a = 40")
                .hasMessageContaining("CDZ-CE1-1");
    }

    /**
     * Morsure 2 : un item dont d n'est pas la dizaine immediatement superieure (la strategie que
     * l'arbitrage interdit explicitement, section 2.1 - "Autoriser 47 + ? = 80 deplacerait la tache
     * vers une soustraction generale").
     */
    @Test
    void unItemDontDNEstPasLaDizaineImmediatementSuperieureEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        for (int complement = 1; complement <= 9; complement++) {
            couples.add(new int[] {10 + complement, 20});
        }
        couples.set(0, new int[] {47, 80});
        List<PaquetDeComplements> paquets = List.of(new PaquetDeComplements("dizaine-lointaine",
                couples));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("d = 80")
                .hasMessageContaining("CDZ-CE1-1");
    }

    /** Morsure 3 : un complement absent du paquet (CDZ-CE1-2). */
    @Test
    void unComplementAbsentDuPaquetEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        for (int complement = 1; complement <= 8; complement++) {
            couples.add(new int[] {20 - complement, 20});
        }
        List<PaquetDeComplements> paquets = List.of(new PaquetDeComplements("complement-9-absent",
                couples));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("le complement 9 n'apparait dans aucun item");
    }

    /** Morsure 4 : moins de cinq dizaines distinctes parmi les valeurs de a (CDZ-CE1-2). */
    @Test
    void moinsDeCinqDizainesDistinctesEstRefuse() {
        List<int[]> couples = new ArrayList<>();
        for (int complement = 1; complement <= 9; complement++) {
            couples.add(new int[] {10 + complement, 20});
        }
        List<PaquetDeComplements> paquets = List.of(new PaquetDeComplements("une-seule-dizaine",
                couples));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne couvrent que 1 dizaine(s)");
    }

    /** Morsure 5 : le membre de la population qui ne porte pas ce dont la derivation a besoin. */
    @Test
    void unItemQuiNeSEcritPasCommeUnComplementFaitEchouerLeGardeFou() {
        List<int[]> couples = new ArrayList<>();
        for (int complement = 1; complement <= 9; complement++) {
            couples.add(new int[] {10 + complement, 20});
        }
        couples.set(0, null);
        List<PaquetDeComplements> paquets = List.of(new PaquetDeComplements("item-non-conforme",
                couples));

        assertThatThrownBy(() -> verifier(paquets))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ne s'ecrit pas \"a + ? = d\"");
    }

    /** Morsure 6 : une population vide ne rend pas ce garde-fou vert (ADR-0051). */
    @Test
    void unePopulationVideFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(List.of()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0051");
    }

    /**
     * ADR-0013 obligation 3, morsure sur le <b>jeu fautif commite</b> : un objet absent de la
     * production, distinct des mutations en memoire ci-dessus.
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonSeulPaquetFautif() {
        List<PaquetDeComplements> fautifs = paquetsDeComplementsDuNiveau(
                CONTRE_EXEMPLE.resolve("catalogue.yaml"), CONTRE_EXEMPLE.resolve("contenu"));

        assertThat(fautifs)
                .as("ce jeu fautif ne prouve rien si le recensement n'y lit pas les deux paquets du"
                        + " perimetre, et seulement eux")
                .extracting(PaquetDeComplements::id)
                .containsExactly("paquet-complements-conforme", "paquet-complements-fautif");

        assertThatThrownBy(() -> verifier(fautifs))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("paquet-complements-fautif")
                .hasMessageNotContaining("paquet-complements-conforme");
    }

    /**
     * Le perimetre derive de la competence, prouve dans les deux sens : un paquet de CE1 qui declare
     * une autre competence de mathematiques (ici les additions) sort de la population, sans qu'aucun
     * motif sur son enonce ne soit en cause.
     */
    @Test
    void unPaquetDeCe1QuiDeclareUneAutreCompetenceSortDuPerimetre() {
        assertThat(paquetsDeComplementsDuNiveau(CONTRE_EXEMPLE.resolve("catalogue.yaml"),
                CONTRE_EXEMPLE.resolve("contenu")))
                .extracting(PaquetDeComplements::id)
                .doesNotContain("paquet-autre-competence");
    }
}
