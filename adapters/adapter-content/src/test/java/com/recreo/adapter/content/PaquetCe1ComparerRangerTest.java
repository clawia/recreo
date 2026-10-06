package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S51-MAT-COMPARER-RANGER (MAT-CE1-02, moitie textuelle) : le paquet "ce1-comparer-ranger", joue par
 * "bandit-manchot-comparer-ranger-ce1". Arbitrage prealable de nadia-prof
 * (squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md, section 3) : ORD-CE1-1 a
 * ORD-CE1-5, avec RAN-1 a RAN-8 de s23 (amendements RAN-4b et RAN-5b du 2026-08-30) pour le
 * rangement.
 *
 * <p>Trois taches, reconnues a la premiere phrase de l'enonce : ranger, comparer, encadrer. Les
 * distracteurs de rangement sont recalcules ici depuis les trois nombres, jamais recopies (meme
 * methode que PaquetCpRangerNombresTest). Ce test ne juge pas qu'un distracteur soit plausible ni
 * que le paquet travaille MAT-CE1-02 et non MAT-CP-02 : c'est la porte de nadia-prof.</p>
 */
class PaquetCe1ComparerRangerTest {

    private static final String PAQUET = "ce1-comparer-ranger";

    private static final String NBSP = " ";

    private static final Pattern NOMBRE = Pattern.compile("\\d+");

    private static final Pattern ENCADREMENT = Pattern.compile("(\\d+) et (\\d+)");

    /** ORD-CE1-2 : RAN-1 impose un seul sens, celui que l'enonce de rangement nomme. */
    private static final String DEBUT_DE_RANGEMENT = "Range du plus petit au plus grand" + NBSP + ": ";

    private static final String DEBUT_DE_COMPARAISON = "Quel signe faut-il mettre entre ";

    private static final String DEBUT_D_ENCADREMENT = "Entre quelles deux ";

    /** RAN-7 : les trois textes surs, dont la surete tient a RAN-1 et RAN-4 (arbitrage s23, 12). */
    private static final Set<String> INDICES_DE_RANGEMENT = Set.of(
            "Cherche d'abord le plus petit des trois.",
            "Repère d'abord le nombre le plus grand.",
            "Compare les nombres deux à deux avant de ranger.");

    private static final String INDICE_CENTAINES_IDENTIQUES =
            "Les centaines sont les mêmes" + NBSP + ": compare les dizaines.";

    private static final String INDICE_NOMBRE_DE_CHIFFRES = "Compte les chiffres de chaque nombre.";

    private static final String INDICE_CENTAINES_DIFFERENTES = "Compare d'abord les centaines.";

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

    private static List<Question> avecDebut(String debut) {
        return tousLesItems().stream().filter(item -> item.enonce().startsWith(debut)).toList();
    }

    private static List<Question> rangements() {
        return avecDebut(DEBUT_DE_RANGEMENT);
    }

    private static List<Question> comparaisons() {
        return avecDebut(DEBUT_DE_COMPARAISON);
    }

    private static List<Question> encadrements() {
        return avecDebut(DEBUT_D_ENCADREMENT);
    }

    private static List<Integer> nombresDe(String texte) {
        List<Integer> nombres = new ArrayList<>();
        Matcher trouve = NOMBRE.matcher(texte);
        while (trouve.find()) {
            nombres.add(Integer.parseInt(trouve.group()));
        }
        return nombres;
    }

    private static List<Integer> liste(String proposition) {
        return Arrays.stream(proposition.split(",\\s*")).map(String::trim).map(Integer::parseInt)
                .toList();
    }

    private static String texte(List<Integer> nombres) {
        return String.join(", ", nombres.stream().map(String::valueOf).toList());
    }

    private static List<Integer> croissant(List<Integer> nombres) {
        return nombres.stream().sorted().toList();
    }

    private static List<Integer> decroissant(List<Integer> nombres) {
        return nombres.stream().sorted((a, b) -> b - a).toList();
    }

    /** RAN-4b, famille (b) : trie sur le seul chiffre des unites. */
    private static List<Integer> parUnite(List<Integer> nombres) {
        return nombres.stream().sorted((a, b) -> (a % 10) - (b % 10)).toList();
    }

    private static List<String> propositions(Question item) {
        List<String> propositions = new ArrayList<>(item.distracteurs());
        propositions.add(item.bonneReponse());
        return propositions;
    }

    private static long compte(List<Question> items, Predicate<Question> critere) {
        return items.stream().filter(critere).count();
    }

    @Test
    void chaqueItemEstUnRangementUneComparaisonOuUnEncadrementEtLeSeul() {
        List<Question> items = tousLesItems();
        assertThat(rangements().size() + comparaisons().size() + encadrements().size())
                .as("ORD-CE1-3 et ORD-CE1-4 : aucun item hors des trois taches, aucun dans deux")
                .isEqualTo(items.size());
    }

    @Test
    void chaqueTacheCompteSesItemsEtLePaquetEnPorteAssezPourDixManches() {
        assertThat(rangements()).as("RAN-8 : vingt items de rangement au moins")
                .hasSizeGreaterThanOrEqualTo(20);
        assertThat(comparaisons()).as("ORD-CE1-3 : trois comparaisons au moins")
                .hasSizeGreaterThanOrEqualTo(3);
        assertThat(encadrements()).as("ORD-CE1-4 : quatre encadrements au moins")
                .hasSizeGreaterThanOrEqualTo(4);
        assertThat(tousLesItems()).as("FER-2 : strictement plus d'items que les dix manches")
                .hasSizeGreaterThan(10);
    }

    @Test
    void aucunItemNeSertLaLigneGraduee() {
        for (Question item : tousLesItems()) {
            String lu = (item.enonce() + " " + String.join(" ", propositions(item))
                    + " " + item.indice().orElse("")).toLowerCase();
            for (String interdit : List.of("graduation", "graduée", "droite", "demi-droite",
                    "place ", "placer", "position")) {
                assertThat(lu).as("ORD-CE1-1 : '%s' parle de la ligne graduee (%s)", item.enonce(),
                        interdit).doesNotContain(interdit);
            }
            assertThat(item.illustration()).as("pas d'illustration : '%s'", item.enonce()).isEmpty();
        }
    }

    @Test
    void lesNombresSontDesEntiersDeZeroAMilleDistinctsDansUnItem() {
        for (Question item : tousLesItems()) {
            List<Integer> nombres = nombresDe(item.enonce());
            assertThat(new HashSet<>(nombres)).as("'%s' : nombres deux a deux distincts (ORD-CE1-1)",
                    item.enonce()).hasSameSizeAs(nombres);
            assertThat(nombres).as("'%s' : entiers de 0 a 1000 (ORD-CE1-1)", item.enonce())
                    .allMatch(n -> n >= 0 && n <= 1000);
        }
    }

    @Test
    void laMoitieAuMoinsDesItemsPorteUnNombreDeTroisChiffres() {
        List<Question> items = tousLesItems();
        long avecTroisChiffres = compte(items,
                item -> nombresDe(item.enonce()).stream().anyMatch(n -> n >= 100));
        assertThat(2 * avecTroisChiffres)
                .as("ORD-CE1-1 : au moins la moitie des %d items (%d tenus)", items.size(),
                        avecTroisChiffres)
                .isGreaterThanOrEqualTo(items.size());
    }

    // ----- ranger : RAN-1 a RAN-8, avec RAN-2 a 1000 et RAN-3 a 16 caracteres (ORD-CE1-2)

    @Test
    void aucunTripletDeRangementNeSeRepete() {
        Set<Set<Integer>> triplets = new HashSet<>();
        for (Question item : rangements()) {
            assertThat(triplets.add(new HashSet<>(nombresDe(item.enonce()))))
                    .as("RAN-8 : le triplet de '%s' est deja pris", item.enonce()).isTrue();
        }
    }

    @Test
    void chaqueRangementOpposeTroisNombresEtSesPropositionsTiennentSeizeCaracteres() {
        for (Question item : rangements()) {
            assertThat(nombresDe(item.enonce())).as("'%s' : trois nombres (RAN-1)", item.enonce())
                    .hasSize(3);
            for (String proposition : propositions(item)) {
                assertThat(proposition.length())
                        .as("'%s' : seize caracteres au plus, espaces comprises (ORD-CE1-2, RAN-3)",
                                proposition)
                        .isLessThanOrEqualTo(16);
            }
        }
    }

    @Test
    void laBonneReponseDUnRangementEstLOrdreCroissantEtLesDeuxDistracteursLesFamillesDeclarees() {
        for (Question item : rangements()) {
            List<Integer> nombres = nombresDe(item.enonce());
            assertThat(item.bonneReponse()).as("RAN-1 : '%s'", item.enonce())
                    .isEqualTo(texte(croissant(nombres)));
            assertThat(item.distracteurs()).as("RAN-4 : '%s'", item.enonce())
                    .containsExactlyInAnyOrder(texte(decroissant(nombres)), texte(parUnite(nombres)));
            assertThat(new HashSet<>(propositions(item)))
                    .as("RAN-4 : trois propositions distinctes pour '%s'", item.enonce()).hasSize(3);
            assertThat(nombres.stream().map(n -> n % 10).distinct().count())
                    .as("RAN-4b : trois chiffres des unites distincts pour '%s'", item.enonce())
                    .isEqualTo(3);
        }
    }

    @Test
    void auMoinsLaMoitieDesRangementsPartagentLePremierNombreEntreLaBonneReponseEtUnDistracteur() {
        List<Question> items = rangements();
        long tenus = compte(items, item -> {
            int premier = liste(item.bonneReponse()).get(0);
            return item.distracteurs().stream().anyMatch(d -> liste(d).get(0) == premier);
        });
        assertThat(2 * tenus).as("RAN-5b : au moins la moitie des %d items (%d tenus)", items.size(),
                tenus).isGreaterThanOrEqualTo(items.size());
    }

    @Test
    void auMoinsUnTiersDesRangementsOpposentDeuxNombresDeLaMemeDizaine() {
        List<Question> items = rangements();
        long tenus = compte(items, item -> nombresDe(item.enonce()).stream().map(n -> n / 10)
                .distinct().count() < 3);
        assertThat(3 * tenus).as("RAN-6 : au moins un tiers des %d items (%d tenus)", items.size(),
                tenus).isGreaterThanOrEqualTo(items.size());
    }

    @Test
    void chaqueIndiceDeRangementEstUnDesTroisTextesSursEnDixMotsAuPlus() {
        for (Question item : rangements()) {
            String indice = item.indice().orElseThrow();
            assertThat(INDICES_DE_RANGEMENT).as("RAN-7 : '%s' n'est pas un texte sur", indice)
                    .contains(indice);
            assertThat(indice.strip().split("\\s+").length).as("RAN-7 : dix mots au plus")
                    .isLessThanOrEqualTo(10);
            assertThat(indice).as("RAN-7 : ne recopie pas la liste rangee")
                    .doesNotContain(item.bonneReponse());
        }
    }

    // ----- comparer : ORD-CE1-3

    @Test
    void chaqueComparaisonProposeLesTroisSignesEtLaBonneReponseDit() {
        for (Question item : comparaisons()) {
            List<Integer> nombres = nombresDe(item.enonce());
            assertThat(nombres).as("deux nombres (ORD-CE1-3) : '%s'", item.enonce()).hasSize(2);
            String attendu = nombres.get(0) < nombres.get(1) ? "<" : ">";
            assertThat(item.bonneReponse()).as("'%s'", item.enonce()).isEqualTo(attendu);
            assertThat(propositions(item)).as("'%s'", item.enonce())
                    .containsExactlyInAnyOrder("<", ">", "=");
        }
    }

    @Test
    void chaqueIndiceDeComparaisonEstChoisiPourSonItem() {
        for (Question item : comparaisons()) {
            List<Integer> nombres = nombresDe(item.enonce());
            int a = nombres.get(0);
            int b = nombres.get(1);
            String indice = item.indice().orElseThrow();
            boolean memeNombreDeChiffres = String.valueOf(a).length() == String.valueOf(b).length();
            switch (indice) {
                case INDICE_CENTAINES_IDENTIQUES -> assertThat(memeNombreDeChiffres
                        && a / 100 == b / 100 && (a / 10) % 10 != (b / 10) % 10)
                        .as("'%s' : memes centaines, dizaines differentes", item.enonce()).isTrue();
                case INDICE_NOMBRE_DE_CHIFFRES -> assertThat(memeNombreDeChiffres)
                        .as("'%s' : les nombres n'ont pas le meme nombre de chiffres", item.enonce())
                        .isFalse();
                case INDICE_CENTAINES_DIFFERENTES -> assertThat(memeNombreDeChiffres
                        && a / 100 != b / 100)
                        .as("'%s' : centaines differentes", item.enonce()).isTrue();
                default -> throw new AssertionError("indice de comparaison inconnu de ce test : "
                        + indice);
            }
        }
    }

    // ----- encadrer : ORD-CE1-4

    private static int echelleNommeeDans(Question item) {
        boolean dizaines = item.enonce().startsWith(DEBUT_D_ENCADREMENT + "dizaines ");
        boolean centaines = item.enonce().startsWith(DEBUT_D_ENCADREMENT + "centaines ");
        assertThat(dizaines ^ centaines).as("ORD-CE1-4 : '%s' nomme une echelle et une seule",
                item.enonce()).isTrue();
        return dizaines ? 10 : 100;
    }

    @Test
    void lesDeuxEchellesSontServiesEtChaqueEnonceNommeLaSienne() {
        List<Question> items = encadrements();
        assertThat(compte(items, item -> echelleNommeeDans(item) == 10))
                .as("ORD-CE1-4 : la dizaine est servie").isGreaterThanOrEqualTo(1);
        assertThat(compte(items, item -> echelleNommeeDans(item) == 100))
                .as("ORD-CE1-4 : la centaine est servie").isGreaterThanOrEqualTo(1);
    }

    @Test
    void laBonneReponseEstUnEncadrementJamaisUnComplementEtLesDistracteursSontDAutresFenetres() {
        for (Question item : encadrements()) {
            int echelle = echelleNommeeDans(item);
            int nombre = nombresDe(item.enonce()).get(0);
            assertThat(nombre % echelle).as("'%s' : pas sur une graduation de l'echelle",
                    item.enonce()).isNotZero();
            Set<String> fenetres = new HashSet<>();
            for (String proposition : propositions(item)) {
                Matcher fenetre = ENCADREMENT.matcher(proposition);
                assertThat(fenetre.matches()).as("'%s' : un encadrement 'a et b'", proposition)
                        .isTrue();
                int bas = Integer.parseInt(fenetre.group(1));
                int haut = Integer.parseInt(fenetre.group(2));
                assertThat(haut - bas).as("'%s' : fenetre de largeur %d", proposition, echelle)
                        .isEqualTo(echelle);
                assertThat(bas % echelle).as("'%s' : borne multiple de %d", proposition, echelle)
                        .isZero();
                assertThat(bas).as("'%s' : borne dans 0 a 1000", proposition).isBetween(0, 1000 - echelle);
                assertThat(fenetres.add(proposition)).as("'%s' : fenetres distinctes", item.enonce())
                        .isTrue();
                boolean contient = bas < nombre && nombre < haut;
                assertThat(contient).as("'%s' pour %d : seule la bonne reponse encadre", proposition,
                        nombre).isEqualTo(proposition.equals(item.bonneReponse()));
            }
        }
    }

    @Test
    void chaqueIndiceDEncadrementNommeLaMemeEchelleQueSonEnonceSansDonnerLaReponse() {
        for (Question item : encadrements()) {
            String attendu = echelleNommeeDans(item) == 10
                    ? "Cherche la dizaine juste avant, puis la dizaine juste après."
                    : "Cherche la centaine juste avant, puis la centaine juste après.";
            assertThat(item.indice().orElseThrow()).as("'%s'", item.enonce()).isEqualTo(attendu);
        }
    }
}
