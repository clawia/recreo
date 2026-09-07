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
 * <p><b>{@code BIN-1}</b> : sur la tache de classement, au plus la moitie des items portent deux
 * propositions plutot que trois. Verifie ici, alors que les autres proprietes tonales
 * ({@code CAT-1}, {@code EMC-5}) restent la garde de {@code nadia-prof} seule.</p>
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

    /** FER-1 : les trois termes du libelle. */
    private static final Set<String> TROIS_TERMES = Set.of(
            "une règle qui vaut pour tous", "un simple souhait", "une loi");

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

    /** FER-1 : les trois termes du libelle sont chacun en position de bonne reponse. */
    @Test
    void fer1LesTroisTermesSontEnBonneReponseDeLaTacheDeClassement() {
        Set<String> reponses = itemsDeClassement().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : regle, souhait et loi doivent tous etre representes en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(TROIS_TERMES);
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
     * BIN-1 : sur la tache de classement, au plus la moitie des items portent deux propositions
     * (un distracteur) plutot que trois.
     */
    @Test
    void bin1AuPlusLaMoitieDesItemsDeClassementSontBinaires() {
        List<Question> classement = itemsDeClassement();
        long binaires = classement.stream().filter(item -> item.distracteurs().size() == 1).count();

        assertThat(binaires)
                .as("BIN-1 : au plus la moitié des %d items de classement sont binaires (2"
                        + " propositions), or %d le sont", classement.size(), binaires)
                .isLessThanOrEqualTo(classement.size() / 2);
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
