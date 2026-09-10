package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S31-2, le paquet {@code cp-emc-regle-souhait-loi}, ligne {@code EMC-CP-07} de l'enseignement
 * moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}. Arbitrage prealable : {@code squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-
 * ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 3.1, ligne
 * {@code EMC-CP-07}.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : classer une
 * situation comme une regle qui vaut pour tous, un simple souhait, ou une loi ("Est-ce une
 * règle...?"), et reconnaitre qu'une loi citee sur un exemple concret s'impose a tous, y compris
 * aux adultes ("Qui doit respecter cette loi ?" / "Qui doit la respecter ?").</p>
 *
 * <p><b>{@code BIN-1}</b> : au plus la moitie des items <b>du paquet</b> portent deux propositions
 * plutot que trois. L'unite est le paquet, jamais une tache : l'arbitrage prealable de s31,
 * section 5, en donne le motif ("un paquet de fermeture porte onze items pour dix manches,
 * {@code DEC-0064}, donc le tirage est presque exhaustif et une propriete comptee sur le paquet
 * vaut pour la partie"). Verifie ici, alors que les autres proprietes tonales ({@code CAT-1},
 * {@code EMC-5}) restent la garde de {@code nadia-prof} seule.</p>
 *
 * <p><b>Reprise du 2026-09-10 ({@code theo-archi}, prealable de s34)</b>, sur les deux ecarts que
 * {@code nadia-prof} a nommes dans son arbitrage prealable de s34, section 5.2 : les deux tests
 * etaient plus durs que les clauses qu'ils pretendaient mesurer. {@code fer1...} exigeait les trois
 * termes du libelle en bonne reponse de la <b>seule</b> tache de classement, la ou {@code FER-1}
 * demande "au moins un item ou il est en position de bonne reponse", sans dire dans quelle tache -
 * la troisieme clause du libelle est servie par la tache de portee. {@code bin1...} divisait par le
 * nombre d'items de la tache de classement, la ou la clause divise par le nombre d'items du paquet :
 * un durcissement d'un facteur deux, jamais decide. <b>Aucune clause ne cede ici</b> ; les trois
 * methodes qui suivent sont vertes sur le contenu servi du jour comme sur celui d'arrivee.</p>
 *
 * <p><b>Reecriture de la tache de portee apres REFUS de nadia-prof le 2026-09-07</b>
 * ({@code squad/conformite/FICHE-bandit-manchot-regle-souhait-loi-cp.md}) : la premiere version
 * servait cinq fois "tout le monde, meme les adultes" (ou une variante a un mot pres), amorce
 * reconnaissable sur cinq items sur cinq ({@code FAB-1a} et {@code FAB-1c}). Elle sert desormais
 * cinq reponses entierement distinctes, dont une ou la loi ne s'applique justement pas aux enfants
 * (la vente d'alcool) - l'enfant apprend "s'impose a tous" par contraste. <b>La tache de classement
 * (items 1 a 6) est inchangee</b> : nadia-prof l'a confirmee bonne et a conserver telle quelle.</p>
 *
 * <p><b>Cinquieme passage (2026-09-10, {@code marc-dev}, {@code S34-EMCCP07}).</b> Sortie (a)
 * retenue par {@code nadia-prof} ({@code squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-
 * engage/arbitrage-emccp07-nadia.md}, sections 2 et 3) : l'item du casque a velo quitte la tache de
 * classement pour la tache de portee, et "une loi" est retiree des deux items de classement
 * restants. La tache de classement compte desormais cinq items, tous binaires ; la tache de portee
 * en compte six, tous ternaires - {@code BIN-1} reste tenue a 5 sur 11.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-8}, {@code EMC-10} et
 * {@code CAT-1} demandent une lecture semantique, non gardees ici. {@code FAB-1} est verifiee depot
 * entier par {@code LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest} (ADR-0086).</p>
 */
class PaquetCpEmcRegleSouhaitLoiTest {

    private static final String PAQUET = "cp-emc-regle-souhait-loi";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les deux categories que la premiere moitie du libelle demande de distinguer. */
    private static final Set<String> CATEGORIES_DU_CLASSEMENT = Set.of(
            "une règle qui vaut pour tous", "un simple souhait");

    private static List<Question> items() {
        ContentSource contenu = new YamlContentPackLoader("").chargerPaquet(PAQUET);
        RandomSource hasard = new Random(13)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            items.add((Question) item);
            dejaVus.add(item.id());
        }
        return items;
    }

    /** Tache de classement : un enonce qui demande de classer regle / souhait / loi. */
    private static boolean estDeTacheClassement(Question item) {
        return item.enonce().contains("Est-ce une règle");
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(PaquetCpEmcRegleSouhaitLoiTest::estDeTacheClassement)
                .toList();
    }

    private static List<Question> itemsDePortee() {
        return items().stream().filter(item -> !estDeTacheClassement(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1, premiere moitie du libelle : chacune des deux categories a distinguer est servie par
     * au moins un item ou elle est en position de bonne reponse.
     */
    @Test
    void fer1LesDeuxCategoriesDuClassementSontChacuneEnBonneReponse() {
        assertThat(itemsDeClassement().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet()))
                .as("FER-1, premiere moitie du libelle : regle et souhait sont chacun servis en"
                        + " position de bonne reponse")
                .containsAll(CATEGORIES_DU_CLASSEMENT);
    }

    /**
     * FER-1, seconde moitie du libelle ("reconnaitre qu'une loi s'impose a tous") : elle est servie
     * par une tache qui ne classe pas, en designant qui la loi oblige. Interdit que cette tache
     * disparaisse ou qu'elle se replie sur un troisieme classement.
     */
    @Test
    void fer1LaSecondeMoitieDuLibelleEstServieParUneTacheQuiNeClassePas() {
        List<Question> portee = itemsDePortee();
        assertThat(portee).as("FER-1 : la seconde moitie du libelle est servie").isNotEmpty();
        for (Question item : portee) {
            assertThat(item.bonneReponse())
                    .as("item %s : la portee de la loi se sert en designant qui elle oblige, jamais"
                            + " en reclassant la situation", item.id().value())
                    .isNotIn(CATEGORIES_DU_CLASSEMENT);
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int classement = itemsDeClassement().size();
        int portee = itemsDePortee().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(portee).as("la tache de portee de la loi est servie").isGreaterThan(0);
        assertThat(Math.min(classement, portee))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * BIN-1 : au plus la moitie des items du paquet portent deux propositions (un distracteur)
     * plutot que trois. Le denominateur est le paquet entier, tache de portee comprise.
     */
    @Test
    void bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires() {
        List<Question> items = items();
        long binaires = items.stream().filter(item -> item.distracteurs().size() == 1).count();

        assertThat(binaires)
                .as("BIN-1 : au plus la moitié des %d items du paquet portent deux propositions,"
                        + " or %d les portent", items.size(), binaires)
                .isLessThanOrEqualTo(items.size() / 2);
    }

    /** Chaque item de la tache de portee porte deux distracteurs (une bonne reponse, trois choix). */
    @Test
    void chaqueItemDeLaTacheDePorteeProposeExactementDeuxDistracteurs() {
        for (Question item : itemsDePortee()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * FAB-1a et FAB-1c, garde-fou de regression sur le motif exact du refus du 2026-09-07 : la
     * tache de portee ne sert plus une seule reponse (ou une variante a un mot pres) sur tous ses
     * items. Chaque bonne reponse de la tache est distincte des autres.
     */
    @Test
    void fab1aLaTacheDePorteeNePorteJamaisUneReponseInvariante() {
        List<Question> portee = itemsDePortee();
        Set<String> reponsesDistinctes = portee.stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponsesDistinctes)
                .as("FAB-1a/FAB-1c : les %d items de la tache de portee doivent porter des bonnes"
                        + " reponses toutes distinctes, pas une seule reponse repetee a un mot pres",
                        portee.size())
                .hasSameSizeAs(portee);
    }

    /** Chaque item de la tache de classement porte un ou deux distracteurs, jamais plus. */
    @Test
    void chaqueItemDeLaTacheDeClassementProposeUnOuDeuxDistracteurs() {
        for (Question item : itemsDeClassement()) {
            assertThat(item.distracteurs().size())
                    .as("item %s : un ou deux distracteurs attendus", item.id().value())
                    .isBetween(1, 2);
        }
    }
}
