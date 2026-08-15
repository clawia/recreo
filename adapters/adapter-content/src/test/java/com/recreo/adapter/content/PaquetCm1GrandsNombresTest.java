package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S13-1 (docs/backlog/backlog.md, section "Sprint 13") : le paquet "cm1-grands-nombres", seul
 * paquet neuf de cette story, joue par "bandit-manchot-grands-nombres-cm1". Proxy de transcription
 * chiffres/lettres accepte par nadia-prof sous GN-1 et GN-2
 * (squad/conformite/ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md, section 1).
 *
 * <p>Les proprietes generiques (indice non vide, aucun item ne repete l'ecran d'un autre du meme
 * paquet, aucune proposition egale a une autre dans un item, le rang de la bonne reponse) sont
 * deja derivees du repertoire de contenu par les garde-fous existants et ne sont pas repetees ici.
 * Ce fichier verifie ce qu'aucun garde-fou generique ne connait : la repartition par verbe, la
 * justesse arithmetique des items "decomposer" et "comparer", et GN-1 - qu'un distracteur "lire" ou
 * "ecrire" differe de la bonne reponse par la valeur qu'il designe, jamais par la seule orthographe
 * des mots-nombres.</p>
 *
 * <p><b>Correction du 2026-08-15, apres le refus pedagogique de nadia-prof</b>
 * ({@code squad/conformite/FICHE-bandit-manchot-grands-nombres-cm1.md}) : les sept items
 * "decomposer" ne faisaient varier que leur premier terme, et trois d'entre eux opposaient a la
 * bonne reponse deux substitutions de chiffre plutot que deux erreurs de decomposition
 * plausibles. Chaque distracteur "decomposer" garde desormais le premier terme de la bonne
 * reponse et deplace une erreur reelle (rang confondu, zero avale) sur un terme suivant - ce que
 * {@link #chaqueItemDecomposeEstUneSommeDeValeursDePositionQuiVautLeNombreDeLEnonce()} continue de
 * prouver par le calcul de la somme, pas par une lecture du premier terme. Les deux indices faux
 * sur "600 040" (verbes "ecrire" et "decomposer") sont corriges dans le paquet. Le separateur de
 * milliers passe de l'espace simple a l'espace fine insecable (U+202F, voir
 * {@link #sansSeparateur(String)}) pour que la decomposition ne soit plus coupee en plein milieu
 * d'un nombre a l'ecran.</p>
 */
class PaquetCm1GrandsNombresTest {

    private static final String PAQUET = "cm1-grands-nombres";

    private static final String PREFIXE_ECRIRE = "Écris en chiffres : ";
    private static final String PREFIXE_LIRE = "Lis ce nombre : ";
    private static final String PREFIXE_DECOMPOSE = "Décompose ";
    private static final String PREFIXE_COMPARER = "Compare ";
    private static final String MARQUEUR_COMPARER = " et ";

    /**
     * GN-1 : la valeur designee par chaque texte litteral ou chiffre utilise dans ce paquet. Cette
     * table n'est pas un analyseur general de mots-nombres francais - elle donne seulement la
     * valeur de chaque texte reellement ecrit dans cm1-grands-nombres.yaml, pour prouver que les
     * distracteurs "lire" et "ecrire" different par la valeur et non par la seule orthographe.
     */
    private static final Map<String, Integer> VALEUR_LITTERALE = Map.ofEntries(
            Map.entry("quarante mille sept cent cinq", 40_705),
            Map.entry("trois cent cinq mille quatre cent vingt-et-un", 305_421),
            Map.entry("cent vingt-huit mille", 128_000),
            Map.entry("six cent mille quarante", 600_040),
            Map.entry("quatre-vingt-dix mille quatre-vingt-dix", 90_090),
            Map.entry("quatre cent cinquante-deux mille", 452_000),
            Map.entry("un million", 1_000_000),
            Map.entry("soixante-dix mille dix-huit", 70_018),
            Map.entry("sept mille dix-huit", 7_018),
            Map.entry("soixante-dix-huit mille", 78_000),
            Map.entry("huit cent seize mille deux cent trois", 816_203),
            Map.entry("huit cent seize mille vingt-trois", 816_023),
            Map.entry("quatre-vingt-seize mille deux cent trois", 96_203),
            Map.entry("quarante-sept mille trois cents", 47_300),
            Map.entry("quatre mille sept cent trente", 4_730),
            Map.entry("quarante-sept mille trente", 47_030),
            Map.entry("cinq cent vingt-huit mille six", 528_006),
            Map.entry("cinquante-deux mille huit cent six", 52_806),
            Map.entry("cinq cent vingt-huit mille soixante", 528_060),
            Map.entry("deux cent treize mille cinq cents", 213_500),
            Map.entry("deux cent treize mille cinquante", 213_050),
            Map.entry("deux cent treize mille cinq", 213_005),
            Map.entry("trente-deux mille dix", 32_010),
            Map.entry("trois mille deux cent dix", 3_210),
            Map.entry("trente-deux mille cent", 32_100),
            Map.entry("cent mille", 100_000),
            Map.entry("dix mille", 10_000));

    @Test
    void lePaquetCompteAuMoinsVingtQuatreItems() {
        assertThat(tousLesItems(paquet())).hasSizeGreaterThanOrEqualTo(24);
    }

    @Test
    void chacunDesQuatreVerbesEstRepresenteParAuMoinsUnQuartDuPaquet() {
        List<Question> items = tousLesItems(paquet());
        int total = items.size();
        Map<String, Long> parVerbe = Map.of(
                "ecrire", items.stream().filter(PaquetCm1GrandsNombresTest::estEcrire).count(),
                "lire", items.stream().filter(PaquetCm1GrandsNombresTest::estLire).count(),
                "decomposer", items.stream().filter(PaquetCm1GrandsNombresTest::estDecompose).count(),
                "comparer", items.stream().filter(PaquetCm1GrandsNombresTest::estComparer).count());

        long somme = parVerbe.values().stream().mapToLong(Long::longValue).sum();
        assertThat(somme).as("chaque item est classe dans exactement un des quatre verbes").isEqualTo(total);

        parVerbe.forEach((verbe, compte) -> assertThat(compte)
                .as("verbe '%s' : au moins un quart des %d items", verbe, total)
                .isGreaterThanOrEqualTo(6)
                .isGreaterThanOrEqualTo((total + 3) / 4));
    }

    @Test
    void chaqueItemEcrireDonneLaBonneEcritureChiffreeEtSesDistracteursDesignentUneAutreValeur() {
        for (Question item : itemsDuVerbe(PaquetCm1GrandsNombresTest::estEcrire)) {
            String litteral = item.enonce().substring(PREFIXE_ECRIRE.length(), item.enonce().length() - 1);
            Integer valeurAttendue = VALEUR_LITTERALE.get(litteral);
            assertThat(valeurAttendue).as("'%s' : la designation litterale est connue", litteral).isNotNull();

            assertThat(sansSeparateur(item.bonneReponse())).as("'%s'", item.enonce())
                    .isEqualTo(valeurAttendue);
            for (String distracteur : item.distracteurs()) {
                assertThat(sansSeparateur(distracteur)).as("'%s', distracteur '%s' (GN-1)",
                        item.enonce(), distracteur).isNotEqualTo(valeurAttendue);
            }
        }
    }

    @Test
    void chaqueItemLireDonneLaBonneDesignationLitteraleEtSesDistracteursDesignentUneAutreValeur() {
        for (Question item : itemsDuVerbe(PaquetCm1GrandsNombresTest::estLire)) {
            String chiffres = item.enonce().substring(PREFIXE_LIRE.length(), item.enonce().length() - 1);
            int valeurEnonce = sansSeparateur(chiffres);

            Integer valeurBonneReponse = VALEUR_LITTERALE.get(item.bonneReponse());
            assertThat(valeurBonneReponse).as("'%s' : la bonne reponse est connue", item.enonce()).isNotNull();
            assertThat(valeurBonneReponse).as("'%s' : la bonne reponse designe le nombre de l'enonce",
                    item.enonce()).isEqualTo(valeurEnonce);

            for (String distracteur : item.distracteurs()) {
                Integer valeurDistracteur = VALEUR_LITTERALE.get(distracteur);
                assertThat(valeurDistracteur).as("'%s' : le distracteur '%s' est connu", item.enonce(),
                        distracteur).isNotNull();
                assertThat(valeurDistracteur).as("'%s', distracteur '%s' (GN-1)", item.enonce(), distracteur)
                        .isNotEqualTo(valeurEnonce);
            }
        }
    }

    @Test
    void chaqueItemDecomposeEstUneSommeDeValeursDePositionQuiVautLeNombreDeLEnonce() {
        for (Question item : itemsDuVerbe(PaquetCm1GrandsNombresTest::estDecompose)) {
            int valeurEnonce = sansSeparateur(
                    item.enonce().substring(PREFIXE_DECOMPOSE.length(), item.enonce().indexOf(" en valeurs")));

            assertThat(sommeDeLaDecomposition(item.bonneReponse())).as("'%s'", item.enonce())
                    .isEqualTo(valeurEnonce);
            for (String distracteur : item.distracteurs()) {
                assertThat(sommeDeLaDecomposition(distracteur)).as("'%s', distracteur '%s'",
                        item.enonce(), distracteur).isNotEqualTo(valeurEnonce);
            }
        }
    }

    @Test
    void chaqueItemComparerOpposeDeuxNombresDistinctsEtLeSigneEstExact() {
        for (Question item : itemsDuVerbe(PaquetCm1GrandsNombresTest::estComparer)) {
            String corps = item.enonce().substring(PREFIXE_COMPARER.length(), item.enonce().length() - 1);
            String[] parts = corps.split(MARQUEUR_COMPARER);
            assertThat(parts).as("forme de l'enonce '%s'", item.enonce()).hasSize(2);

            int gauche = sansSeparateur(parts[0]);
            int droite = sansSeparateur(parts[1]);
            assertThat(gauche).as("'%s' : les deux nombres sont distincts", item.enonce()).isNotEqualTo(droite);

            String attendu = gauche < droite ? "<" : ">";
            assertThat(item.bonneReponse()).as("signe attendu pour '%s'", item.enonce()).isEqualTo(attendu);

            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s'", item.enonce())
                    .containsExactlyInAnyOrder("<", ">", "=");
        }
    }

    @Test
    void leJeuDuCatalogueChargeBienLePaquetCm1GrandsNombres() {
        GameDefinition jeu = jeu("bandit-manchot-grands-nombres-cm1");

        assertThat(tousLesItems(jeu.contenu()))
                .as("l'entree de catalogue charge bien le paquet '%s'", PAQUET)
                .isEqualTo(tousLesItems(paquet()));
    }

    private static boolean estEcrire(Question item) {
        return item.enonce().startsWith(PREFIXE_ECRIRE);
    }

    private static boolean estLire(Question item) {
        return item.enonce().startsWith(PREFIXE_LIRE);
    }

    private static boolean estDecompose(Question item) {
        return item.enonce().startsWith(PREFIXE_DECOMPOSE);
    }

    private static boolean estComparer(Question item) {
        return item.enonce().startsWith(PREFIXE_COMPARER);
    }

    private static List<Question> itemsDuVerbe(java.util.function.Predicate<Question> verbe) {
        return tousLesItems(paquet()).stream().filter(verbe).toList();
    }

    /**
     * MD-4 (arbitrage prealable s13) exige une espace entre les milliers. Depuis la correction du
     * motif 1 de nadia-prof (FICHE-bandit-manchot-grands-nombres-cm1.md, section 5) ce paquet
     * l'ecrit en espace fine insecable (U+202F) plutot qu'en espace simple, pour que le separateur
     * ne casse plus la ligne a l'ecran - les deux ecritures restent lues ici, comme dans le
     * garde-fou de rang (LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.NOMBRE).
     */
    private static int sansSeparateur(String nombre) {
        return Integer.parseInt(nombre.strip().replaceAll("[\\u00A0\\u202F ]", ""));
    }

    private static int sommeDeLaDecomposition(String decomposition) {
        int somme = 0;
        for (String terme : decomposition.split("\\+")) {
            somme += sansSeparateur(terme);
        }
        return somme;
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
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

    private static GameDefinition jeu(String id) {
        return new YamlContentPackLoader().games().stream()
                .filter(candidat -> candidat.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
