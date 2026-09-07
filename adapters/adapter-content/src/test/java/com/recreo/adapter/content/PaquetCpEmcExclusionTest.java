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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S31-2, le paquet {@code cp-emc-exclusion}, ligne {@code EMC-CP-03} de l'enseignement moral et
 * civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}. Arbitrage prealable : {@code squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-
 * ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 3.1, ligne
 * {@code EMC-CP-03} - ligne la plus delicate du sprint, ecrite en dernier dans son lot.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : reconnaitre
 * qu'une moquerie ou une exclusion repetee appelle a en parler a un adulte plutot qu'a l'ignorer ou
 * a la regler seul ("...Que doit-elle faire ?"), et identifier l'adulte a qui en parler selon le
 * lieu decrit ("À qui doit-elle en parler ?").</p>
 *
 * <p><b>Lecture retenue pour l'invariant "la bonne reponse est toujours en parler à un adulte"</b>
 * (backlog, S31-2), signalee dans le commentaire du paquet et reprise ici pour l'audit : l'invariant
 * porte sur la categorie d'action (chercher un adulte, jamais regler seul), pas sur une chaine
 * unique - la tache B nomme l'adulte precis selon le lieu. Ce test verifie donc que la tache A porte
 * l'invariant litteral, et que la tache B ne porte jamais une reponse de type "regler seul" ou
 * "ne rien dire" en position de bonne reponse.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-8}, {@code EMC-10} et
 * {@code CAT-1} demandent une lecture semantique, non gardees ici. {@code FAB-1} est verifiee depot
 * entier par {@code LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest} (ADR-0086).</p>
 */
class PaquetCpEmcExclusionTest {

    private static final String PAQUET = "cp-emc-exclusion";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** EMC-3 : la tache A repond toujours par cette meme categorie d'action. */
    private static final String REPONSE_INVARIANTE_TACHE_A = "en parler à un adulte";

    /** EMC-3, tache B : les seuls adultes valides selon le lieu decrit. */
    private static final Set<String> ADULTES_VALIDES = Set.of(
            "à un surveillant de la cour", "à sa maîtresse", "à l'adulte qui surveille la cantine");

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

    /** Tache B : un enonce qui demande a quel adulte s'adresser. */
    private static boolean estDeTacheAdulte(Question item) {
        return item.enonce().contains("À qui");
    }

    private static List<Question> itemsDeReconnaissance() {
        return items().stream().filter(item -> !estDeTacheAdulte(item)).toList();
    }

    private static List<Question> itemsDAdulte() {
        return items().stream().filter(PaquetCpEmcExclusionTest::estDeTacheAdulte).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int reconnaissance = itemsDeReconnaissance().size();
        int adulte = itemsDAdulte().size();

        assertThat(reconnaissance).as("la tache de reconnaissance est servie").isGreaterThan(0);
        assertThat(adulte).as("la tache d'identification de l'adulte est servie").isGreaterThan(0);
        assertThat(Math.min(reconnaissance, adulte))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** EMC-3 : la tache de reconnaissance repond toujours par la meme categorie d'action. */
    @Test
    void emc3LaTacheDeReconnaissanceRepondToujoursEnParlerAUnAdulte() {
        for (Question item : itemsDeReconnaissance()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse de la tache de reconnaissance est invariante",
                            item.id().value())
                    .isEqualTo(REPONSE_INVARIANTE_TACHE_A);
        }
    }

    /** EMC-3 : la tache d'identification de l'adulte ne repond jamais par une solution seule. */
    @Test
    void emc3LaTacheDAdulteNeRepondJamaisParUneSolutionSeul() {
        for (Question item : itemsDAdulte()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse nomme toujours un adulte reel selon le lieu",
                            item.id().value())
                    .isIn(ADULTES_VALIDES);
        }
    }

    /** Chaque item propose exactement deux distracteurs (une bonne reponse, trois choix au total). */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }
}
