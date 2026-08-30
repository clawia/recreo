package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S23-ETA (squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-
 * niveau-2.md, section 2, ETA-1 a ETA-10 ; section 12.1, amendement ETA-4b du 2026-08-30) : le
 * paquet "cm1-problemes-etapes", joue par "bandit-manchot-problemes-etapes-cm1".
 *
 * <p>Chaque item declare sa derivation a cote de l'enonce, dans un commentaire YAML de la forme
 * {@code # ETA-4b: op1,op2,a+b} ou {@code # ETA-4b: op1,op2,a+c} (ETA-4b : "un tiers doit pouvoir
 * recalculer les deux distracteurs a partir de l'enonce et des deux familles declarees"). Ce test
 * lit ce commentaire, extrait les trois nombres de l'enonce dans leur ordre d'apparition et
 * recalcule integralement le resultat intermediaire (famille a), la bonne reponse et le distracteur
 * de la seconde famille declaree - (b) l'inverse de la seconde etape, ou (c) l'inverse de la
 * premiere etape suivie de la seconde etape correcte - rien n'est recopie du YAML pour la
 * verifier.</p>
 *
 * <p><b>ETA-7, desormais tenue.</b> ETA-4 d'origine (familles a+b seules) rendait le rang median
 * structurellement inatteignable : la bonne reponse et le distracteur (b) encadrent toujours le
 * resultat intermediaire depuis les deux sens opposes d'une meme operation, qui reste donc toujours
 * la valeur du milieu. ETA-4b ouvre la famille (c) - l'inversion de la <b>premiere</b> etape,
 * conduite avec la seconde etape correcte - qui n'a pas cette propriete : elle rend le rang median
 * atteignable, ce que ce test verifie en recalculant les 24 items (8 petite, 8 milieu, 8 grande).</p>
 */
class PaquetCm1ProblemesEtapesTest {

    private static final String PAQUET = "cm1-problemes-etapes";
    private static final String RESSOURCE_YAML = "/contenu/cm1-problemes-etapes.yaml";
    private static final Pattern DERIVATION = Pattern.compile("#\\s*ETA-4b:\\s*(\\w+),(\\w+),(a\\+[bc])");
    private static final Pattern NOMBRE = Pattern.compile("\\d+");
    private static final Pattern ITEM_ID = Pattern.compile("item-(\\d+)");

    private record Derivation(String op1, String op2, String familles) {
    }

    /** Un (op1,op2,familles) par position de fichier (0-based), lu depuis le commentaire qui suit chaque enonce. */
    private static Map<Integer, Derivation> derivationsDeclarees() {
        String yaml;
        try (InputStream flux = PaquetCm1ProblemesEtapesTest.class.getResourceAsStream(RESSOURCE_YAML)) {
            assertThat(flux).as("%s introuvable sur le classpath", RESSOURCE_YAML).isNotNull();
            yaml = new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        Map<Integer, Derivation> parPosition = new TreeMap<>();
        int position = 0;
        for (String ligne : yaml.lines().toList()) {
            if (!ligne.contains("- enonce:")) {
                continue;
            }
            Matcher m = DERIVATION.matcher(ligne);
            assertThat(m.find()).as("'%s' : declare sa derivation ETA-4b en commentaire", ligne).isTrue();
            parPosition.put(position, new Derivation(m.group(1), m.group(2), m.group(3)));
            position++;
        }
        return parPosition;
    }

    private static int apply(String op, int a, int b) {
        return switch (op) {
            case "add" -> a + b;
            case "sub" -> a - b;
            case "mul" -> a * b;
            case "div" -> a / b;
            default -> throw new IllegalArgumentException(op);
        };
    }

    private static String inverse(String op) {
        return switch (op) {
            case "add" -> "sub";
            case "sub" -> "add";
            case "mul" -> "div";
            case "div" -> "mul";
            default -> throw new IllegalArgumentException(op);
        };
    }

    private static int[] nombresDe(String enonce) {
        Matcher m = NOMBRE.matcher(enonce);
        List<Integer> trouves = new ArrayList<>();
        while (m.find()) {
            trouves.add(Integer.parseInt(m.group()));
        }
        assertThat(trouves).as("'%s' : porte exactement trois nombres (ETA-1)", enonce).hasSize(3);
        return new int[] {trouves.get(0), trouves.get(1), trouves.get(2)};
    }

    private static int positionDe(Question item) {
        Matcher m = ITEM_ID.matcher(item.id().value());
        assertThat(m.matches()).as("identifiant '%s' de la forme 'item-N'", item.id().value()).isTrue();
        return Integer.parseInt(m.group(1));
    }

    /** Les trois valeurs (bonne reponse, famille a, second distracteur) recalculees pour un item. */
    private record ValeursRecalculees(int r2, int da, int autre) {
    }

    private static ValeursRecalculees recalculer(Question item, Derivation derivation) {
        int[] nombres = nombresDe(item.enonce());
        int a1 = nombres[0];
        int a2 = nombres[1];
        int b2 = nombres[2];

        assertThat(a1).as("'%s' : aucun operande n'est 0 ni 1 (ETA-5)", item.enonce()).isGreaterThan(1);
        assertThat(a2).as("'%s' : aucun operande n'est 0 ni 1 (ETA-5)", item.enonce()).isGreaterThan(1);
        assertThat(b2).as("'%s' : aucun operande n'est 0 ni 1 (ETA-5)", item.enonce()).isGreaterThan(1);

        int da = apply(derivation.op1(), a1, a2);
        int r2 = apply(derivation.op2(), da, b2);
        int autre;
        if (derivation.familles().equals("a+b")) {
            autre = apply(inverse(derivation.op2()), da, b2);
        } else {
            int mPrime = apply(inverse(derivation.op1()), a1, a2);
            assertThat(mPrime).as("'%s' : la famille (c) inverse la premiere etape en un entier"
                    + " positif (ETA-4b)", item.enonce()).isGreaterThan(0);
            autre = apply(derivation.op2(), mPrime, b2);
        }
        return new ValeursRecalculees(r2, da, autre);
    }

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems()).as("ETA-10 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void chaqueItemRecalculeSaBonneReponseEtSonSecondDistracteurDepuisSaDerivationDeclaree() {
        Map<Integer, Derivation> derivations = derivationsDeclarees();
        for (Question item : tousLesItems()) {
            int position = positionDe(item);
            Derivation derivation = derivations.get(position);
            assertThat(derivation).as("derivation declaree pour la position %d", position).isNotNull();

            ValeursRecalculees valeurs = recalculer(item, derivation);

            assertThat(String.valueOf(valeurs.r2())).as("'%s' : bonne reponse recalculee (ETA-4b)",
                    item.enonce()).isEqualTo(item.bonneReponse());
            assertThat(item.distracteurs())
                    .as("'%s' : les deux distracteurs sont la famille (a) et la seconde famille"
                            + " declaree (ETA-4b)", item.enonce())
                    .containsExactlyInAnyOrder(String.valueOf(valeurs.da()), String.valueOf(valeurs.autre()));

            Set<Integer> propositions = Set.of(valeurs.r2(), valeurs.da(), valeurs.autre());
            assertThat(propositions).as("'%s' : trois propositions distinctes (ETA-6)", item.enonce())
                    .hasSize(3);
            assertThat(valeurs.r2()).as("'%s' : le resultat final est un entier positif (ETA-6)",
                    item.enonce()).isPositive();
            assertThat(valeurs.da()).as("'%s' : le resultat final differe du resultat intermediaire"
                    + " (ETA-5)", item.enonce()).isNotEqualTo(valeurs.r2());
        }
    }

    @Test
    void chacuneDesQuatreOperationsEstUneDesDeuxEtapesDAuMoinsTroisItems() {
        Map<Integer, Derivation> derivations = derivationsDeclarees();
        Map<String, Long> occurrences = new HashMap<>();
        for (Derivation d : derivations.values()) {
            occurrences.merge(d.op1(), 1L, Long::sum);
            occurrences.merge(d.op2(), 1L, Long::sum);
        }
        for (String op : List.of("add", "sub", "mul", "div")) {
            assertThat(occurrences.getOrDefault(op, 0L))
                    .as("ETA-2 : '%s' est l'une des deux etapes d'au moins trois items", op)
                    .isGreaterThanOrEqualTo(3);
        }
    }

    @Test
    void laFamilleAFigureSurAuMoinsLaMoitieDesItems() {
        Map<Integer, Derivation> derivations = derivationsDeclarees();
        long total = derivations.size();
        // la famille (a) est de toute facon l'un des deux distracteurs de chaque item declare
        // ci-dessus (recalculer() la nomme "da" pour a+b comme pour a+c) : elle figure donc sur
        // 100 % des items de ce paquet, bien au-dessus du plancher de la moitie (ETA-4b).
        assertThat(2 * total).as("ETA-4b : la famille (a) figure sur au moins la moitie des items")
                .isGreaterThanOrEqualTo(total);
    }

    @Test
    void laBonneReponseEstRepartieEnTiersEgauxSurLesTroisRangsEta7() {
        Map<Integer, Derivation> derivations = derivationsDeclarees();
        long grande = 0;
        long petite = 0;
        long milieu = 0;
        for (Question item : tousLesItems()) {
            Derivation derivation = derivations.get(positionDe(item));
            ValeursRecalculees valeurs = recalculer(item, derivation);
            List<Integer> ranges = new ArrayList<>(List.of(valeurs.r2(), valeurs.da(), valeurs.autre()));
            ranges.sort(Integer::compareTo);
            if (valeurs.r2() == ranges.get(2)) {
                grande++;
            } else if (valeurs.r2() == ranges.get(0)) {
                petite++;
            } else {
                milieu++;
            }
        }
        int total = tousLesItems().size();
        int seuil = (int) Math.ceil(total / 4.0);
        assertThat(grande).as("ETA-7 : la bonne reponse est la plus grande sur au moins un quart des items")
                .isGreaterThanOrEqualTo(seuil);
        assertThat(petite).as("ETA-7 : la bonne reponse est la plus petite sur au moins un quart des items")
                .isGreaterThanOrEqualTo(seuil);
        assertThat(milieu).as("ETA-7 : la bonne reponse est celle du milieu sur au moins un quart des"
                + " items - tenue depuis ETA-4b (etait structurellement impossible sous ETA-4)")
                .isGreaterThanOrEqualTo(seuil);
    }

    /**
     * ETA-3, decompte corrige le 2026-08-30 (reserve A de nadia-prof,
     * FICHE-bandit-manchot-problemes-etapes-cm1.md) : le "?" compte comme un mot des qu'il est
     * separe du precedent par une espace, insecable ou non - c'est la methode de reference
     * (D-8, cm1-problemes-durees) et c'est elle qui a trouve l'item 17 a 31 mots, la ou une
     * version precedente de ce test, qui retirait le "?" avant de compter, ne l'aurait jamais vu.
     */
    @Test
    void chaqueEnonceTientEnAuPlusTrenteMotsEtChaqueIndiceEnAuPlusDouzeSansReveler() {
        for (Question item : tousLesItems()) {
            String enonce = item.enonce();
            String sansPonctuation = enonce.replace(".", "").replace(",", "").strip();
            int motsEnonce = sansPonctuation.isEmpty() ? 0
                    : sansPonctuation.split("[\s ]+").length;
            assertThat(motsEnonce).as("'%s' : au plus trente mots (ETA-3)", enonce).isLessThanOrEqualTo(30);


            String indice = item.indice().orElseThrow(() -> new AssertionError("indice manquant"));
            int motsIndice = indice.strip().split("\\s+").length;
            assertThat(motsIndice).as("'%s' : indice d'au plus douze mots (ETA-9)", indice)
                    .isLessThanOrEqualTo(12);
            assertThat(indice).as("'%s' : l'indice ne revele pas la bonne reponse (ETA-9)", indice)
                    .doesNotContain(item.bonneReponse());
            for (String distracteur : item.distracteurs()) {
                assertThat(indice).as("'%s' : l'indice ne revele pas un distracteur (ETA-9)", indice)
                        .doesNotContain(distracteur);
            }
        }
    }

    @Test
    void aucuneSituationNeSupposeUnPresupposeSocialInterditParEta8() {
        List<String> motsInterdits = List.of("jardin", "piscine", "chambre", "animal", "chat", "chien",
                "vacances", "argent de poche");
        for (Question item : tousLesItems()) {
            String enonce = item.enonce().toLowerCase(java.util.Locale.FRENCH);
            for (String mot : motsInterdits) {
                assertThat(enonce).as("'%s' : aucun presuppose social interdit par ETA-8 ('%s')",
                        item.enonce(), mot).doesNotContain(mot);
            }
        }
    }

    /**
     * Reserve D, correctif du 2026-08-30 (reaudit de nadia-prof,
     * FICHE-bandit-manchot-problemes-etapes-cm1.md) : le correctif de la reserve B avait change
     * les enonces des items 22 a 24 ("distribue"/"retire"/"vend" devenus "perd"), sans toucher
     * leurs indices, qui continuaient a nommer l'ancien verbe - un evenement absent de l'enonce
     * que l'enfant lit apres une erreur. **Quatrieme defaut du sprint qu'une CI entierement verte
     * n'a pas vu**, nomme par nadia-prof comme hors de tout garde-fou. Ce test ne generalise pas
     * une regle de coherence indice/enonce - une verification generique s'est averee trop fragile
     * face aux variations morphologiques du francais (participe vs conjugaison, paraphrase) pour
     * valoir mieux qu'une lecture a l'oeil - il fixe seulement la regression exacte que
     * nadia-prof a nommee, sur les trois items qu'elle a nommes.
     */
    @Test
    void lesIndicesDesItemsCorrigesParLaReserveBNommentLeVerbeDeLeurEnonce() {
        List<Question> items = tousLesItems();
        for (Question item : items) {
            int position = positionDe(item);
            if (position == 21 || position == 22 || position == 23) {
                assertThat(item.indice().orElseThrow())
                        .as("'%s' : nomme 'perdu(e)', le verbe reellement employe par l'enonce"
                                + " (reserve D)", item.enonce())
                        .containsIgnoringCase("perdu")
                        .doesNotContainIgnoringCase("distribu")
                        .doesNotContainIgnoringCase("retir")
                        .doesNotContainIgnoringCase("vend");
            }
        }
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems() {
        ContentSource contenu = new YamlContentPackLoader().chargerPaquet(PAQUET);
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }
}
