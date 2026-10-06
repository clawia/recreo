package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

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

/**
 * S51-MAT-MULTIPLICATION (MAT-CE1-06) : le paquet "ce1-sens-multiplication", joue par
 * "bandit-manchot-sens-multiplication-ce1". Arbitrage prealable de nadia-prof
 * (squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md, section 6) : MUL-CE1-1 a
 * MUL-CE1-7. Ce test porte tout ce qui se decide sur le texte de l'item ; ce que le dessin montre est
 * juge par {@code LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest} (module bootstrap, seul
 * module qui voie a la fois les paquets et les fichiers de dessin).
 *
 * <p>Chaque item est rattache a son couple de facteurs (a, b) par sa seule bonne reponse et son enonce,
 * jamais par une table ecrite ici : a est le terme repete ou le nombre de colonnes, b le nombre de termes
 * ou de lignes, comme dans MUL-CE1-6. Les distracteurs sont recalcules depuis (a, b). Ce test ne juge
 * pas qu'un distracteur soit plausible, ni que l'ecriture "n x m = n fois m" soit celle que la classe
 * attend : c'est la porte de nadia-prof.</p>
 */
class PaquetCe1SensMultiplicationTest {

    private static final String PAQUET = "ce1-sens-multiplication";

    private static final String NBSP = " ";

    private static final String FOIS = "×";

    private static final Pattern SOMME_VERS_PRODUIT = Pattern.compile(
            "^(\\d+(?: \\+ \\d+)+), c'est quelle multiplication" + NBSP + "\\?$");

    private static final Pattern PRODUIT_VERS_SOMME = Pattern.compile(
            "^(\\d+) " + FOIS + " (\\d+), c'est quelle addition" + NBSP + "\\?$");

    /** MUL-CE1-4 : l'enonce dit que les deux comptes donnent le meme total, sans le moindre nombre. */
    private static final String TOTAL_IDENTIQUE = "Les deux façons de compter donnent le même total.";

    private static final String ENONCE_PAR_LIGNES = TOTAL_IDENTIQUE
            + " Compte les points ligne par ligne. Quelle multiplication" + NBSP + "?";

    private static final String ENONCE_PAR_COLONNES = TOTAL_IDENTIQUE
            + " Compte les points colonne par colonne. Quelle multiplication" + NBSP + "?";

    private static final Pattern PRODUIT = Pattern.compile("^(\\d+) " + FOIS + " (\\d+)$");

    private enum Forme {
        SOMME_VERS_PRODUIT, PRODUIT_VERS_SOMME, RESEAU_PAR_LIGNES, RESEAU_PAR_COLONNES
    }

    /** Un item rattache a son couple (a, b) : a = terme ou colonnes, b = nombre de termes ou lignes. */
    private record Lu(Question item, Forme forme, int a, int b) {
    }

    private enum Rang {
        PLUS_PETITE, INTERMEDIAIRE, PLUS_GRANDE
    }

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

    /** Le couple (a, b) d'un item, lu dans son enonce ou, pour un reseau, dans sa bonne reponse. */
    private static Lu lire(Question item) {
        String enonce = item.enonce();
        Matcher somme = SOMME_VERS_PRODUIT.matcher(enonce);
        if (somme.matches()) {
            String[] termes = somme.group(1).split(" \\+ ");
            int a = Integer.parseInt(termes[0]);
            assertThat(termes).as("'%s' : le meme terme repete", enonce).allMatch(t -> Integer.parseInt(t) == a);
            return new Lu(item, Forme.SOMME_VERS_PRODUIT, a, termes.length);
        }
        Matcher produit = PRODUIT_VERS_SOMME.matcher(enonce);
        if (produit.matches()) {
            return new Lu(item, Forme.PRODUIT_VERS_SOMME, Integer.parseInt(produit.group(2)),
                    Integer.parseInt(produit.group(1)));
        }
        Matcher bonne = PRODUIT.matcher(item.bonneReponse());
        if (ENONCE_PAR_LIGNES.equals(enonce) && bonne.matches()) {
            return new Lu(item, Forme.RESEAU_PAR_LIGNES, Integer.parseInt(bonne.group(2)),
                    Integer.parseInt(bonne.group(1)));
        }
        if (ENONCE_PAR_COLONNES.equals(enonce) && bonne.matches()) {
            return new Lu(item, Forme.RESEAU_PAR_COLONNES, Integer.parseInt(bonne.group(1)),
                    Integer.parseInt(bonne.group(2)));
        }
        throw new AssertionError("'" + enonce + "' n'est d'aucune des quatre formes du paquet");
    }

    private static List<Lu> lus() {
        return tousLesItems().stream().map(PaquetCe1SensMultiplicationTest::lire).toList();
    }

    private static List<Lu> deForme(Forme... formes) {
        List<Forme> voulues = List.of(formes);
        return lus().stream().filter(lu -> voulues.contains(lu.forme())).toList();
    }

    private static String produit(int n, int m) {
        return n + " " + FOIS + " " + m;
    }

    private static String somme(int terme, int nombre) {
        return String.join(" + ", java.util.Collections.nCopies(nombre, String.valueOf(terme)));
    }

    /** La valeur d'une proposition : un produit "n x m", ou une somme de termes. */
    static int valeur(String proposition) {
        Matcher produit = PRODUIT.matcher(proposition);
        if (produit.matches()) {
            return Integer.parseInt(produit.group(1)) * Integer.parseInt(produit.group(2));
        }
        int total = 0;
        for (String terme : proposition.split(" \\+ ")) {
            total += Integer.parseInt(terme);
        }
        return total;
    }

    /**
     * Les ecritures des formes de MUL-CE1-6 pour un item, par numero de forme : 1 = a + b ; 2 = a x
     * (b - 1) ; 3 = a x (b + 1) ; 4 = (a + 1) x b ; 5 = a x (b + 2). Une forme n'existe que dans
     * l'ecriture de l'item : sur une multiplication, la forme 1 se lirait au signe et n'est pas admise.
     */
    private static Map<Integer, String> formesAdmises(Lu lu) {
        int a = lu.a();
        int b = lu.b();
        Map<Integer, String> formes = new LinkedHashMap<>();
        switch (lu.forme()) {
            case PRODUIT_VERS_SOMME -> {
                formes.put(1, a + " + " + b);
                formes.put(2, somme(a, b - 1));
                formes.put(3, somme(a, b + 1));
                formes.put(4, somme(a + 1, b));
                formes.put(5, somme(a, b + 2));
            }
            case RESEAU_PAR_COLONNES -> {
                formes.put(2, produit(a, b - 1));
                formes.put(3, produit(a, b + 1));
                formes.put(4, produit(a + 1, b));
                formes.put(5, produit(a, b + 2));
            }
            default -> {
                formes.put(2, produit(b - 1, a));
                formes.put(3, produit(b + 1, a));
                formes.put(4, produit(b, a + 1));
                formes.put(5, produit(b + 2, a));
            }
        }
        return formes;
    }

    private static Rang rang(Question item) {
        int bonne = valeur(item.bonneReponse());
        long plusPetites = item.distracteurs().stream().filter(d -> valeur(d) < bonne).count();
        return plusPetites == 0 ? Rang.PLUS_PETITE
                : plusPetites == item.distracteurs().size() ? Rang.PLUS_GRANDE : Rang.INTERMEDIAIRE;
    }

    @Test
    void lePaquetCompteAuMoinsDixItemsEtPlusQueDeManches() {
        assertThat(tousLesItems()).as("FER-3 : dix items au moins, FER-2 : plus que les dix manches")
                .hasSizeGreaterThan(10);
    }

    @Test
    void lesDeuxFacteursSontDesEntiersDeDeuxADixDeProduitAuPlusCent() {
        for (Lu lu : lus()) {
            assertThat(lu.a()).as("'%s' : facteur de 2 a 10 (MUL-CE1-1)", lu.item().enonce())
                    .isBetween(2, 10);
            assertThat(lu.b()).as("'%s' : facteur de 2 a 10 (MUL-CE1-1)", lu.item().enonce())
                    .isBetween(2, 10);
            assertThat(lu.a() * lu.b()).as("'%s' : produit d'au plus 100 (MUL-CE1-1)",
                    lu.item().enonce()).isLessThanOrEqualTo(100);
        }
    }

    @Test
    void laBonneReponseEcritLeMemeProduitQueLEnonceOuLeReseau() {
        for (Lu lu : lus()) {
            String attendu = switch (lu.forme()) {
                case SOMME_VERS_PRODUIT, RESEAU_PAR_LIGNES -> produit(lu.b(), lu.a());
                case PRODUIT_VERS_SOMME -> somme(lu.a(), lu.b());
                case RESEAU_PAR_COLONNES -> produit(lu.a(), lu.b());
            };
            assertThat(lu.item().bonneReponse()).as("'%s'", lu.item().enonce()).isEqualTo(attendu);
        }
    }

    @Test
    void aucunEnonceNEstUnProduitNuNiUnePhraseDePartageOuDeGroupement() {
        List<String> interdits = List.of("combien de fois", "partage", "groupe", "paquet", "chacun");
        for (Question item : tousLesItems()) {
            String enonce = item.enonce();
            assertThat(enonce).as("MUL-CE1-2 : produit nu a x b = ?").doesNotContain("=");
            assertThat(enonce).as("MUL-CE1-2 : trou dans un produit").doesNotContain("?" + NBSP + "=")
                    .doesNotContain(FOIS + " ?");
            for (String interdit : interdits) {
                assertThat(enonce.toLowerCase()).as("MUL-CE1-2 : '%s' (%s)", enonce, interdit)
                        .doesNotContain(interdit);
            }
        }
    }

    @Test
    void lesDeuxSensDeLAdditionReiteereSontServisAuMoinsTroisFoisChacun() {
        assertThat(deForme(Forme.SOMME_VERS_PRODUIT)).as("MUL-CE1-3 : de la somme vers le produit")
                .hasSizeGreaterThanOrEqualTo(3);
        assertThat(deForme(Forme.PRODUIT_VERS_SOMME)).as("MUL-CE1-3 : du produit vers la somme")
                .hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    void auMoinsUnReseauEstLuDansLesDeuxSensEtLesDeuxItemsDisentQueLeTotalEstLeMeme() {
        Map<String, List<Lu>> parDessin = new LinkedHashMap<>();
        for (Lu lu : deForme(Forme.RESEAU_PAR_LIGNES, Forme.RESEAU_PAR_COLONNES)) {
            parDessin.computeIfAbsent(lu.item().illustration().orElseThrow(), cle -> new ArrayList<>())
                    .add(lu);
        }
        long lusDansLesDeuxSens = parDessin.values().stream()
                .filter(lus -> lus.stream().anyMatch(lu -> lu.forme() == Forme.RESEAU_PAR_LIGNES)
                        && lus.stream().anyMatch(lu -> lu.forme() == Forme.RESEAU_PAR_COLONNES))
                .count();
        assertThat(lusDansLesDeuxSens).as("MUL-CE1-4 : au moins une disposition lue dans les deux sens")
                .isGreaterThanOrEqualTo(1);
        parDessin.forEach((dessin, lus) -> {
            assertThat(lus.stream().map(lu -> lu.a() * lu.b()).distinct().count())
                    .as("MUL-CE1-4 : le meme total sur les items de %s", dessin).isEqualTo(1);
            for (Lu lu : lus) {
                assertThat(lu.item().enonce())
                        .as("MUL-CE1-4 : l'enonce de '%s' dit que les deux comptes donnent le meme"
                                + " total", dessin)
                        .startsWith(TOTAL_IDENTIQUE);
            }
        });
    }

    @Test
    void seulsLesItemsDeReseauMontrentUnDessinSansDireLeNombreDeLignesNiDeColonnes() {
        Set<String> textesAlternatifs = new HashSet<>();
        for (Lu lu : lus()) {
            boolean reseau = lu.forme() == Forme.RESEAU_PAR_LIGNES
                    || lu.forme() == Forme.RESEAU_PAR_COLONNES;
            assertThat(lu.item().illustration().isPresent()).as("'%s'", lu.item().enonce())
                    .isEqualTo(reseau);
            if (reseau) {
                assertThat(lu.item().enonce()).as("MUL-CE1-5 : l'enonce ne donne aucun nombre")
                        .doesNotContainPattern("\\d");
                String alternatif = lu.item().illustrationTexteAlternatif().orElseThrow();
                assertThat(alternatif).as("MUL-CE1-5 : le texte alternatif ne donne aucun nombre")
                        .doesNotContainPattern("\\d");
                textesAlternatifs.add(alternatif);
            }
        }
        assertThat(textesAlternatifs).as("un seul texte alternatif pour ce type de dessin").hasSize(1);
    }

    @Test
    void chaqueDistracteurEstUneFormeAdmiseDistinctePositiveEtLaFormeUnNEstQueSurLesAdditions() {
        for (Lu lu : lus()) {
            Question item = lu.item();
            Map<Integer, String> admises = formesAdmises(lu);
            assertThat(item.distracteurs()).as("'%s' : deux distracteurs (MUL-CE1-6)", item.enonce())
                    .hasSize(2);
            Set<Integer> valeurs = new HashSet<>();
            valeurs.add(valeur(item.bonneReponse()));
            for (String distracteur : item.distracteurs()) {
                assertThat(admises.values()).as("'%s' : '%s' est d'une forme admise (MUL-CE1-6)",
                        item.enonce(), distracteur).contains(distracteur);
                assertThat(valeur(distracteur)).as("'%s' : entier positif", distracteur).isPositive();
                assertThat(valeurs.add(valeur(distracteur)))
                        .as("'%s' : '%s' est distinct de la bonne reponse et de l'autre distracteur",
                                item.enonce(), distracteur).isTrue();
            }
        }
    }

    @Test
    void laBonneReponseOccupeChacunDesTroisRangsSansEtreMajoritaire() {
        List<Lu> lus = lus();
        Map<Rang, Long> rangs = new LinkedHashMap<>();
        for (Rang rang : Rang.values()) {
            rangs.put(rang, lus.stream().filter(lu -> rang(lu.item()) == rang).count());
        }
        for (Map.Entry<Rang, Long> rang : rangs.entrySet()) {
            assertThat(10 * rang.getValue()).as("MUL-CE1-6 : rang %s au moins 10 %% (%s)",
                    rang.getKey(), rangs).isGreaterThanOrEqualTo(lus.size());
            assertThat(2 * rang.getValue()).as("MUL-CE1-6 : rang %s non majoritaire (%s)",
                    rang.getKey(), rangs).isLessThanOrEqualTo(lus.size());
        }
    }

    @Test
    void chaqueItemPorteUnIndiceQuiNeDonneAucunProduit() {
        for (Lu lu : lus()) {
            String indice = lu.item().indice().orElseThrow();
            assertThat(indice).as("'%s'", lu.item().enonce()).doesNotContain(lu.item().bonneReponse())
                    .doesNotContain(String.valueOf(lu.a() * lu.b()));
        }
    }
}
