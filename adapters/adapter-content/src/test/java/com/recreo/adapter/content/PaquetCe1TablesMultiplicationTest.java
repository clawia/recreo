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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S47-MAT-TABLES ({@code docs/backlog/backlog.md}, sprint 47), le paquet
 * {@code ce1-tables-multiplication}, ligne {@code MAT-CE1-07} - tables de multiplication de 2, 5
 * et 10, puis de 3 et 4. Arbitrage pedagogique prealable de s43
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md}, section 1.3),
 * amende et corrige le 2026-10-02 par
 * {@code squad/conformite/FICHE-bandit-manchot-tables-multiplication-ce1.md} : 40 faits distincts
 * (pas 50 couples), unite de compte de {@code TAB-CE1-3} et population de {@code TAB-CE1-4}
 * precisees, et une cinquieme famille de distracteur admise sur {@code TAB-CE1-6}.
 *
 * <p>Le "facteur de table" d'un fait est le membre de {2, 3, 4, 5, 10} dont la table est exercee -
 * quand les deux facteurs du fait sont dans cet ensemble, c'est le plus petit des deux (fiche,
 * section 5). {@link #canonicaliser} le calcule depuis les deux nombres ecrits dans l'enonce,
 * quel que soit leur ordre d'ecriture.
 *
 * <p>{@code TAB-CE1-6} : chaque distracteur declare sa famille par un marqueur en commentaire sur
 * sa propre ligne {@code - enonce:} ("# TAB-CE1-6: &lt;famille du premier&gt;,&lt;famille du
 * second&gt;"), relu par {@link #marqueursParEnonce} et verifie par {@link #correspondAFamille} -
 * un distracteur dont le marqueur ne correspond a aucune lecture valide du fait (y compris la
 * seconde lecture quand les deux facteurs sont dans {2, 3, 4, 5, 10}) fait rougir ce test.
 */
class PaquetCe1TablesMultiplicationTest {

    private static final String PAQUET = "ce1-tables-multiplication";

    private static final Path FICHIER_CONTENU =
            Path.of("src/main/resources/contenu/" + PAQUET + ".yaml");

    /** ADR-0013 obligation 3 : le jeu fautif de ce garde-fou. */
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/PaquetCe1TablesMultiplicationTest");

    private static final String CONTRE_EXEMPLE_CLASSPATH =
            "/contreexemple/PaquetCe1TablesMultiplicationTest";

    private static final Set<Integer> A = Set.of(2, 3, 4, 5, 10);

    /** TAB-CE1-5 : expression numerique nue, signe "×", "?" final precede d'une espace insecable
     * (TYP-1, ADR-0067). */
    private static final Pattern ENONCE = Pattern.compile("^(\\d+) × (\\d+) =\\u00A0\\?$");

    /** Une ligne "- enonce:" du fichier source, avec son marqueur TAB-CE1-6 en commentaire. */
    private static final Pattern LIGNE_ITEM =
            Pattern.compile("- enonce: \"([^\"]*)\"\\s*#\\s*TAB-CE1-6:\\s*(\\w+),(\\w+)\\s*$");

    private static final Set<String> FAMILLES_CONNUES = Set.of("a", "b", "c", "d", "e");

    private static final double SEUIL_RANG_MIN = 0.10;

    private static final double SEUIL_RANG_MAX = 0.50;

    private record CanonicalFact(int table, int other, boolean both) {
    }

    private record ItemAnalyse(Question question, int premier, int second, CanonicalFact fait,
            String fam1, String fam2) {
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static CanonicalFact canonicaliser(int premier, int second) {
        boolean premierAlloue = A.contains(premier);
        boolean secondAlloue = A.contains(second);
        assertThat(premierAlloue || secondAlloue)
                .as("TAB-CE1-1 : ni %d ni %d n'appartient a {2, 3, 4, 5, 10}", premier, second)
                .isTrue();
        if (premierAlloue && secondAlloue) {
            return new CanonicalFact(Math.min(premier, second), Math.max(premier, second), true);
        }
        return premierAlloue
                ? new CanonicalFact(premier, second, false)
                : new CanonicalFact(second, premier, false);
    }

    /**
     * Le recensement des marqueurs TAB-CE1-6, par enonce : une lecture du fichier source distincte
     * du chargeur YAML, parce que le marqueur vit dans un commentaire qu'aucun chargeur de contenu
     * ne lit. L'association par enonce, plutot que par position dans le fichier, est deliberee : le
     * chargeur ne garantit pas de rendre les items dans l'ordre du fichier.
     */
    private static Map<String, String[]> marqueursParEnonce(Path fichier) {
        Map<String, String[]> marqueurs = new LinkedHashMap<>();
        for (String ligne : lire(fichier).split("\n", -1)) {
            Matcher matcher = LIGNE_ITEM.matcher(ligne.strip());
            if (matcher.matches()) {
                marqueurs.put(matcher.group(1), new String[]{matcher.group(2), matcher.group(3)});
            }
        }
        return marqueurs;
    }

    private static List<ItemAnalyse> itemsAnalyses() {
        return itemsAnalyses(paquet(PAQUET), FICHIER_CONTENU);
    }

    /** Parametree par la source et le fichier de marqueurs, pour etre mordue sur le jeu fautif
     * comme sur le paquet servi (ADR-0013 obligation 3). */
    private static List<ItemAnalyse> itemsAnalyses(ContentSource source, Path fichierMarqueurs) {
        Map<String, String[]> marqueurs = marqueursParEnonce(fichierMarqueurs);
        List<ItemAnalyse> analyses = new ArrayList<>();
        for (Question item : tousLesItems(source)) {
            Matcher matcher = ENONCE.matcher(item.enonce());
            assertThat(matcher.matches())
                    .as("TAB-CE1-5 : forme de l'enonce '%s'", item.enonce()).isTrue();
            int premier = Integer.parseInt(matcher.group(1));
            int second = Integer.parseInt(matcher.group(2));
            String[] familles = marqueurs.get(item.enonce());
            assertThat(familles).as("TAB-CE1-6 : aucun marqueur trouve pour '%s'", item.enonce())
                    .isNotNull();
            analyses.add(new ItemAnalyse(item, premier, second,
                    canonicaliser(premier, second), familles[0], familles[1]));
        }
        return analyses;
    }

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }

    private static List<Question> tousLesItems(ContentSource contenu) {
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

    /** TAB-CE1-6, famille (c) : la somme n'est admise que si elle reste entre la moitie et le
     * produit. */
    private static boolean familleCValide(int a, int b) {
        int somme = a + b;
        int produit = a * b;
        return somme < produit && 2 * somme > produit;
    }

    /**
     * TAB-CE1-6 : la valeur verifie-t-elle la formule de la famille nommee, lue sur le couple
     * {@code (a, b)} - {@code a} est le membre de {2, 3, 4, 5, 10} de cette lecture, {@code b}
     * l'autre facteur.
     */
    private static boolean correspondAFamille(int a, int b, int valeur, String famille) {
        return switch (famille) {
            case "a" -> b - 1 >= 1 && a * (b - 1) == valeur;
            case "b" -> a * (b + 1) == valeur;
            case "c" -> familleCValide(a, b) && a + b == valeur;
            case "d" -> (A.contains(a - 1) && (a - 1) * b == valeur)
                    || (A.contains(a + 1) && (a + 1) * b == valeur);
            case "e" -> b == 1 && a + 1 == valeur;
            default -> false;
        };
    }

    /** Les lectures valides d'un fait : une seule si un seul facteur est dans {2,3,4,5,10}, les
     * deux (dans les deux ordres) sinon (amendement du 2026-10-02 de TAB-CE1-6). */
    private static List<int[]> lecturesDe(CanonicalFact fait) {
        List<int[]> lectures = new ArrayList<>();
        lectures.add(new int[]{fait.table(), fait.other()});
        if (fait.both()) {
            lectures.add(new int[]{fait.other(), fait.table()});
        }
        return lectures;
    }

    /** ADR-0081 : ce paquet declare MAT-CE1-07, et uniquement elle. */
    @Test
    void cePaquetDeclareMatCe107EtUniquementElle() {
        Map<?, ?> corps = new Yaml().load(lire(FICHIER_CONTENU));
        assertThat(corps.get("lignesDeReferentiel")).isEqualTo(List.of("MAT-CE1-07"));
    }

    /**
     * TAB-CE1-1 : l'espace couvert (a dans {2, 3, 4, 5, 10}, b entier de 1 a 10) ne fait que 40
     * faits distincts - 3 x 4 et 4 x 3 sont le meme fait. Chacun des 40 apparait au moins une fois,
     * et ce paquet n'en sert aucun deux fois (une propriete plus forte que l'exigence, verifiee
     * parce qu'elle est vraie du paquet servi).
     */
    @Test
    void tab1LesQuaranteFaitsDistinctsSontTousServisUneSeuleFoisChacun() {
        Set<List<Integer>> attendus = new HashSet<>();
        for (int a : A) {
            for (int b = 1; b <= 10; b++) {
                CanonicalFact fait = canonicaliser(a, b);
                attendus.add(List.of(fait.table(), fait.other()));
            }
        }
        assertThat(attendus).as("TAB-CE1-1 : l'espace attendu compte 40 faits distincts")
                .hasSize(40);

        List<ItemAnalyse> analyses = itemsAnalyses();
        assertThat(analyses).as("TAB-CE1-1 : un item par fait, 40 au total").hasSize(40);

        Set<List<Integer>> servis = new HashSet<>();
        for (ItemAnalyse analyse : analyses) {
            boolean nouveau = servis.add(List.of(analyse.fait().table(), analyse.fait().other()));
            assertThat(nouveau).as("TAB-CE1-1 : le fait %s est servi plus d'une fois",
                    analyse.fait()).isTrue();
        }
        assertThat(servis).as("TAB-CE1-1 : les 40 faits attendus sont tous servis")
                .isEqualTo(attendus);
    }

    /**
     * TAB-CE1-2 : les items de facteur de table 2, 5 ou 10 font au moins la moitie du paquet, ceux
     * de facteur de table 3 au moins 15 %, ceux de facteur de table 4 au moins 15 %.
     */
    @Test
    void tab2LaRepartitionParFacteurDeTableRespecteLesTroisPlanchers() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        int total = analyses.size();
        long table2510 = analyses.stream()
                .filter(a -> Set.of(2, 5, 10).contains(a.fait().table())).count();
        long table3 = analyses.stream().filter(a -> a.fait().table() == 3).count();
        long table4 = analyses.stream().filter(a -> a.fait().table() == 4).count();

        assertThat((double) table2510 / total)
                .as("TAB-CE1-2 : facteur de table 2, 5 ou 10 au moins la moitie du paquet")
                .isGreaterThanOrEqualTo(0.5);
        assertThat((double) table3 / total)
                .as("TAB-CE1-2 : facteur de table 3 au moins 15 %%").isGreaterThanOrEqualTo(0.15);
        assertThat((double) table4 / total)
                .as("TAB-CE1-2 : facteur de table 4 au moins 15 %%").isGreaterThanOrEqualTo(0.15);
    }

    /** TAB-CE1-3 : au plus 20 % des items portent un facteur 1. */
    @Test
    void tab3AuPlusVingtPourCentDesItemsPortentUnFacteur1() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        long facteur1 = analyses.stream().filter(a -> a.fait().other() == 1).count();
        assertThat((double) facteur1 / analyses.size())
                .as("TAB-CE1-3 : %d items a facteur 1 sur %d", facteur1, analyses.size())
                .isLessThanOrEqualTo(0.20);
    }

    /**
     * TAB-CE1-4 : sur les items dont les deux facteurs different et dont aucun ne vaut 1, au moins
     * un tiers ecrivent le facteur de table en seconde position dans l'enonce.
     */
    @Test
    void tab4AuMoinsUnTiersDeLaPopulationEligibleEcritLeFacteurDeTableEnSecondePosition() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        List<ItemAnalyse> population = analyses.stream()
                .filter(a -> a.fait().other() != 1 && a.fait().table() != a.fait().other())
                .toList();
        assertThat(population).as("TAB-CE1-4 : population non vide").isNotEmpty();

        long facteurDeTableEnSecond = population.stream()
                .filter(a -> a.second() == a.fait().table()).count();

        assertThat((double) facteurDeTableEnSecond / population.size())
                .as("TAB-CE1-4 : %d items sur %d avec le facteur de table en seconde position",
                        facteurDeTableEnSecond, population.size())
                .isGreaterThanOrEqualTo(1.0 / 3);
    }

    /**
     * TAB-CE1-6 : chaque distracteur appartient a l'une des cinq familles nommees - dont la
     * cinquieme, (e) la confusion de "× 1" avec "+ 1" (valeur {@code a + 1}), qui n'est valide que
     * pour un fait dont un facteur vaut 1 - verifiee ici sur sa valeur recalculee depuis le fait,
     * sur l'une ou l'autre lecture quand les deux facteurs sont dans {2, 3, 4, 5, 10}.
     */
    @Test
    void tab6ChaqueDistracteurCorrespondASaFamilleDeclareeYComprisLaCinquieme() {
        Set<String> famillesUtilisees = verifierFamilles(itemsAnalyses());
        assertThat(famillesUtilisees)
                .as("TAB-CE1-6 : les cinq familles sont effectivement exercees par le paquet")
                .isEqualTo(FAMILLES_CONNUES);
    }

    /**
     * TAB-CE1-6, le juge : pur, il ne lit ni disque ni classpath et recoit exactement ce que
     * {@link #itemsAnalyses} produit. C'est ce qui permet de le mordre sur le jeu fautif commite
     * comme sur le paquet servi (ADR-0013 obligation 3). Rend l'ensemble des familles rencontrees.
     */
    private static Set<String> verifierFamilles(List<ItemAnalyse> analyses) {
        Set<String> famillesUtilisees = new HashSet<>();
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.question();
            assertThat(item.distracteurs()).as("deux distracteurs pour '%s'", item.enonce())
                    .hasSize(2);

            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("TAB-CE1-6 : propositions deux a deux distinctes pour '%s'",
                    item.enonce()).hasSize(3);

            List<int[]> lectures = lecturesDe(analyse.fait());
            String[] familles = {analyse.fam1(), analyse.fam2()};
            for (int i = 0; i < 2; i++) {
                String famille = familles[i];
                int valeur = Integer.parseInt(item.distracteurs().get(i));
                assertThat(FAMILLES_CONNUES).as("TAB-CE1-6 : famille inconnue '%s' sur '%s'",
                        famille, item.enonce()).contains(famille);
                assertThat(valeur).as("TAB-CE1-6 : distracteur positif sur '%s'", item.enonce())
                        .isPositive();
                boolean valide = lectures.stream()
                        .anyMatch(lecture -> correspondAFamille(lecture[0], lecture[1], valeur,
                                famille));
                assertThat(valide).as("TAB-CE1-6 : '%s', distracteur %d (%d) ne correspond a"
                        + " aucune lecture valide de la famille '%s'", item.enonce(), i, valeur,
                        famille).isTrue();
                if ("e".equals(famille)) {
                    assertThat(analyse.fait().other()).as("TAB-CE1-6 : la famille (e) n'est admise"
                            + " que pour un fait ou un facteur vaut 1, item '%s'", item.enonce())
                            .isEqualTo(1);
                }
                famillesUtilisees.add(famille);
            }
        }
        return famillesUtilisees;
    }

    /**
     * TAB-CE1-R1 (fiche, section 4 du verdict du 2026-10-02) : tout item dont le facteur de table
     * vaut 1 sert un indice sur le facteur 1 lui-meme, et non sur la table du second facteur. Forme
     * deja validee au CM1 ({@code cm1-tables-multiplication.yaml}), reprise mot pour mot.
     */
    @Test
    void tabR1ToutItemAFacteur1PorteLIndiceSurLeFacteur1() {
        String indiceAttendu = "Multiplier par 1, le nombre ne change pas.";
        List<ItemAnalyse> analyses = itemsAnalyses();
        List<ItemAnalyse> aFacteur1 = analyses.stream()
                .filter(a -> a.fait().other() == 1).toList();
        assertThat(aFacteur1).as("TAB-CE1-R1 : cinq items a facteur 1").hasSize(5);
        for (ItemAnalyse analyse : aFacteur1) {
            assertThat(analyse.question().indice())
                    .as("TAB-CE1-R1 : indice de '%s'", analyse.question().enonce())
                    .contains(indiceAttendu);
        }
    }

    /**
     * ADR-0013 obligation 3 : le jeu fautif de ce garde-fou, traverse par la lecture disque, le
     * chargeur de contenu reel et le recensement des marqueurs - pas seulement par un objet mute en
     * memoire. Son seul item declare la famille (a) pour son premier distracteur, qui ne correspond
     * a aucune lecture valide du fait (2, 3).
     */
    @Test
    void leJeuFautifCommiteEstRefuseSurSonMarqueurErrone() {
        Path fichierFautif = CONTRE_EXEMPLE.resolve(
                "contenu/ce1-tables-multiplication-fautif.yaml");
        ContentSource source = new YamlContentPackLoader(CONTRE_EXEMPLE_CLASSPATH)
                .chargerPaquet("ce1-tables-multiplication-fautif");
        List<ItemAnalyse> analyses = itemsAnalyses(source, fichierFautif);

        assertThatThrownBy(() -> verifierFamilles(analyses))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("TAB-CE1-6")
                .hasMessageContaining("2 × 3");
    }

    /**
     * TAB-CE1-6 : sur le paquet entier, chacun des trois rangs de la bonne reponse (la plus petite,
     * la mediane, la plus grande des trois propositions) porte au moins 10 % et au plus 50 % des
     * items.
     */
    @Test
    void tab6ChaqueRangDeLaBonneReponsePorteEntreDixEtCinquantePourCentDesItems() {
        List<ItemAnalyse> analyses = itemsAnalyses();
        Map<String, Integer> tally = new LinkedHashMap<>(
                Map.of("plus petite", 0, "mediane", 0, "plus grande", 0));
        for (ItemAnalyse analyse : analyses) {
            Question item = analyse.question();
            List<Integer> valeurs = new ArrayList<>();
            valeurs.add(Integer.parseInt(item.bonneReponse()));
            for (String distracteur : item.distracteurs()) {
                valeurs.add(Integer.parseInt(distracteur));
            }
            List<Integer> tries = new ArrayList<>(valeurs);
            tries.sort(Integer::compareTo);
            int rang = tries.indexOf(Integer.parseInt(item.bonneReponse()));
            String cle = switch (rang) {
                case 0 -> "plus petite";
                case 1 -> "mediane";
                default -> "plus grande";
            };
            tally.merge(cle, 1, Integer::sum);
        }
        int total = analyses.size();
        tally.forEach((rang, compte) -> assertThat((double) compte / total)
                .as("TAB-CE1-6 : rang '%s', %d items sur %d", rang, compte, total)
                .isBetween(SEUIL_RANG_MIN, SEUIL_RANG_MAX));
    }
}
