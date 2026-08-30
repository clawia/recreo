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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S23-RAN (squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-
 * niveau-2.md, section 1, RAN-1 a RAN-8 ; section 12.2, amendements RAN-4b et RAN-5b du
 * 2026-08-30) : le paquet "cp-ranger-nombres", joue par "bandit-manchot-ranger-nombres-cp". Ferme
 * la moitie "ranger" de la ligne mathematiques | CP | Nombres et calculs | Comparer et ranger des
 * nombres jusqu'a 100 (moins que, plus que, egal), la moitie "comparer" restant servie par
 * cp-comparer-nombres.
 *
 * <p>Chaque proposition s'ecrit "a, b, c" ; la bonne reponse est toujours l'ordre croissant des
 * trois nombres de l'item (RAN-1, la consigne du catalogue fixe ce sens pour tout le paquet). Les
 * deux distracteurs sont recalcules ici depuis les trois nombres, jamais recopies : l'ordre
 * decroissant (famille a) et l'ordre obtenu en ne comparant que le chiffre des unites (famille b,
 * RAN-4b : n'est definie que si les trois chiffres des unites sont deux a deux distincts, ce que ce
 * test verifie item par item).</p>
 *
 * <p>ADR-0068 : ce paquet est declare hors du domaine numerique des garde-fous generiques de rang
 * (propositions = listes ordonnees, pas des valeurs scalaires), voir
 * LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest#PAQUETS_HORS_DU_DOMAINE_NUMERIQUE.
 * RAN-5b (l'anti-raccourci) est donc gardee ici, dans ce test propre au paquet, et nulle part
 * ailleurs.</p>
 */
class PaquetCpRangerNombresTest {

    private static final String PAQUET = "cp-ranger-nombres";

    private static List<Integer> nombresDe(String proposition) {
        return Arrays.stream(proposition.split(",\\s*")).map(String::trim).map(Integer::parseInt).toList();
    }

    private static List<Integer> ordreCroissant(List<Integer> nombres) {
        List<Integer> tri = new ArrayList<>(nombres);
        tri.sort(Integer::compareTo);
        return tri;
    }

    private static List<Integer> ordreDecroissant(List<Integer> nombres) {
        List<Integer> tri = new ArrayList<>(nombres);
        tri.sort((a, b) -> b - a);
        return tri;
    }

    /** RAN-4b famille (b) : trie par chiffre des unites, defini uniquement si tous distincts. */
    private static List<Integer> ordreParUnite(List<Integer> nombres) {
        List<Integer> tri = new ArrayList<>(nombres);
        tri.sort((a, b) -> (a % 10) - (b % 10));
        return tri;
    }

    private static String fmt(List<Integer> nombres) {
        return String.join(", ", nombres.stream().map(String::valueOf).toList());
    }

    @Test
    void lePaquetCompteAuMoinsVingtItems() {
        assertThat(tousLesItems()).as("RAN-8 : vingt items au moins").hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    void aucunItemNePartageSonTripletAvecUnAutre() {
        List<Question> items = tousLesItems();
        Set<Set<Integer>> triplets = new HashSet<>();
        for (Question item : items) {
            Set<Integer> triplet = new HashSet<>(nombresDe(item.bonneReponse()));
            assertThat(triplets).as("'%s' : aucun autre item ne porte ce meme triplet (RAN-8)",
                    item.bonneReponse()).doesNotContain(triplet);
            triplets.add(triplet);
        }
    }

    @Test
    void chaqueItemOpposeTroisEntiersDistinctsDeZeroACent() {
        for (Question item : tousLesItems()) {
            List<Integer> nombres = nombresDe(item.bonneReponse());
            assertThat(nombres).as("'%s' : exactement trois nombres (RAN-2)", item.bonneReponse())
                    .hasSize(3);
            assertThat(new HashSet<>(nombres)).as("'%s' : deux a deux distincts (RAN-2)",
                    item.bonneReponse()).hasSize(3);
            for (int n : nombres) {
                assertThat(n).as("'%s' : entier de 0 a 100 (RAN-2)", item.bonneReponse())
                        .isBetween(0, 100);
            }
        }
    }

    @Test
    void chaqueItemALesTroisChiffresDesUnitesDeuxADeuxDistinctsCarLaFamilleBLExige() {
        for (Question item : tousLesItems()) {
            List<Integer> nombres = nombresDe(item.bonneReponse());
            List<Integer> unites = nombres.stream().map(n -> n % 10).toList();
            assertThat(new HashSet<>(unites))
                    .as("'%s' : les trois chiffres des unites sont deux a deux distincts, sinon la"
                            + " famille (b) n'est pas definie (RAN-4b)", item.bonneReponse())
                    .hasSize(3);
        }
    }

    @Test
    void chaqueGraphiePropositionTientSurDouzeCaracteresAuPlus() {
        for (Question item : tousLesItems()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                assertThat(proposition.length())
                        .as("'%s' : douze caracteres au plus, espaces comprises (RAN-3)", proposition)
                        .isLessThanOrEqualTo(12);
            }
        }
    }

    @Test
    void laBonneReponseEstToujoursLOrdreCroissantEtLesDeuxDistracteursSontLesDeuxFamillesDeclarees() {
        for (Question item : tousLesItems()) {
            List<Integer> nombres = nombresDe(item.bonneReponse());
            List<Integer> croissant = ordreCroissant(nombres);
            List<Integer> decroissant = ordreDecroissant(nombres);
            List<Integer> parUnite = ordreParUnite(nombres);

            assertThat(item.bonneReponse()).as("'%s' : la bonne reponse est l'ordre croissant (RAN-1)",
                    item.bonneReponse()).isEqualTo(fmt(croissant));
            assertThat(item.distracteurs())
                    .as("'%s' : les deux distracteurs sont l'ordre decroissant et l'ordre par unite"
                            + " (RAN-4/RAN-4b)", item.bonneReponse())
                    .containsExactlyInAnyOrder(fmt(decroissant), fmt(parUnite));

            Set<String> propositions = Set.of(fmt(croissant), fmt(decroissant), fmt(parUnite));
            assertThat(propositions).as("'%s' : les trois propositions restent distinctes (RAN-4)",
                    item.bonneReponse()).hasSize(3);
        }
    }

    /**
     * RAN-5b (remplace RAN-5, amendement du 2026-08-30) : sur au moins la moitie des items, au
     * moins un distracteur commence par le meme nombre que la bonne reponse. RAN-5 d'origine se
     * contentait d'un partage entre les deux distracteurs, ce qui laissait le raccourci "premier
     * nombre le plus petit" entierement gagnant - c'est exactement ce que ce test verifie ne plus
     * se produire : voir {@link #contreExempleDuRaccourciSurRan5DOrigine}.
     */
    @Test
    void auMoinsLaMoitieDesItemsPartagentLePremierNombreEntreLaBonneReponseEtUnDistracteur() {
        List<Question> items = tousLesItems();
        long tenus = 0;
        for (Question item : items) {
            List<Integer> nombres = nombresDe(item.bonneReponse());
            int premierBonneReponse = ordreCroissant(nombres).get(0);
            boolean unDistracteurPartage = item.distracteurs().stream()
                    .anyMatch(d -> nombresDe(d).get(0) == premierBonneReponse);
            if (unDistracteurPartage) {
                tenus++;
            }
        }
        assertThat(2 * tenus).as("RAN-5b : au moins la moitie des %d items (%d tenus)", items.size(),
                tenus).isGreaterThanOrEqualTo(items.size());
    }

    /**
     * Demontre le defaut que RAN-5b corrige (section 12.2.2 de l'arbitrage) : un item ou seuls les
     * deux distracteurs partagent leur premier nombre tenait RAN-5 d'origine, alors que le
     * raccourci "presse la liste dont le premier nombre est le plus petit" y gagne a coup sur.
     * Aucun item de ce paquet n'est dans ce cas (RAN-5b l'exclut par construction) : ce test
     * documente pourquoi la condition a change, il ne porte pas sur le contenu servi.
     */
    @Test
    void contreExempleDuRaccourciSurRan5DOrigine() {
        List<Integer> triplet = List.of(12, 25, 40);
        List<Integer> croissant = ordreCroissant(triplet);
        List<Integer> decroissant = ordreDecroissant(triplet);
        List<Integer> parUnite = ordreParUnite(triplet);
        assertThat(decroissant.get(0)).as("famille (a) commence par 40").isEqualTo(40);
        assertThat(parUnite.get(0)).as("famille (b) commence aussi par 40 sur ce triplet, unites 0/2/5")
                .isEqualTo(40);
        assertThat(croissant.get(0)).as("la bonne reponse commence par 12, jamais 40")
                .isNotEqualTo(decroissant.get(0));
        // RAN-5 d'origine ("au moins deux propositions commencent par le meme nombre") est tenue
        // ici (40 partage par les deux distracteurs) ; RAN-5b ne l'est pas (aucun distracteur ne
        // commence par 12) - c'est exactement le cas que RAN-5b elimine.
    }

    @Test
    void auMoinsUnTiersDesItemsOpposentDeuxNombresDeLaMemeDizaine() {
        List<Question> items = tousLesItems();
        long tenus = 0;
        for (Question item : items) {
            List<Integer> nombres = nombresDe(item.bonneReponse());
            Set<Integer> dizaines = nombres.stream().map(n -> n / 10).collect(java.util.stream.Collectors.toSet());
            if (dizaines.size() < nombres.size()) {
                tenus++;
            }
        }
        assertThat(3 * tenus).as("RAN-6 : au moins un tiers des %d items (%d tenus)", items.size(), tenus)
                .isGreaterThanOrEqualTo(items.size());
    }

    @Test
    void chaqueIndiceDonneUneMethodeEnAuPlusDixMotsSansJamaisEcrireLeTriplet() {
        for (Question item : tousLesItems()) {
            String indice = item.indice().orElseThrow(() -> new AssertionError("indice manquant"));
            int mots = indice.strip().split("\\s+").length;
            assertThat(mots).as("'%s' : dix mots au plus (RAN-7)", indice).isLessThanOrEqualTo(10);
            assertThat(indice).as("'%s' : ne recopie pas la liste rangee (RAN-7)", indice)
                    .doesNotContain(item.bonneReponse());
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
