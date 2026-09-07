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
 * <b>S31-3, le paquet {@code cm1-sciences-regimes-alimentaires}, ligne {@code SCI-CM1-07} de
 * "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-
 * s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 4.3. Encode
 * le plancher de dix items (FER-2), les trois elements du libelle en bonne reponse (FER-1) et les
 * deux taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par l'appartenance de la bonne reponse a l'ensemble
 * ferme des trois regimes : le <b>regime</b> d'un animal decrit (herbivore, carnivore, omnivore)
 * et la <b>fonction</b> de la nourriture pour un etre vivant (une phrase d'usage).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle du regime de chaque animal et de
 * chaque fonction de la nourriture reste la garde de {@code nadia-prof}. Le libelle porte
 * "herbivore, carnivore, omnivore" - une correction de fond faite le 2026-09-05, avant qu'aucun
 * item ne soit ecrit sur cette ligne, remplace "vegetarien" par "herbivore" dans le referentiel.</p>
 */
class PaquetCm1SciencesRegimesAlimentairesTest {

    private static final String PAQUET = "cm1-sciences-regimes-alimentaires";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent les trois regimes du libelle. */
    private static final Set<String> REPONSES_DE_REGIME = Set.of("herbivore", "carnivore", "omnivore");

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

    private static List<Question> itemsDeRegime() {
        return items().stream().filter(item -> REPONSES_DE_REGIME.contains(item.bonneReponse())).toList();
    }

    private static List<Question> itemsDeFonction() {
        return items().stream()
                .filter(item -> !REPONSES_DE_REGIME.contains(item.bonneReponse())).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S31-3). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, les trois regimes : chacun est en bonne reponse au moins une fois. */
    @Test
    void fer1LesTroisRegimesSontRepresentesEnBonneReponse() {
        Set<String> reponses = itemsDeRegime().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois regimes doivent etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_REGIME);
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemAExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int regime = itemsDeRegime().size();
        int fonction = itemsDeFonction().size();

        assertThat(regime).as("la tache de regime est servie").isGreaterThan(0);
        assertThat(fonction).as("la tache de fonction est servie").isGreaterThan(0);
        assertThat(Math.min(regime, fonction))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (regime, fonction additionnes). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }

    /**
     * FAB-1a : sur la tache de regime, les sept animaux decrits sont tous distincts - aucun item
     * n'est un autre servi sous un autre nom d'animal.
     */
    @Test
    void fab1aLesSeptAnimauxDeLaTacheDeRegimeSontTousDistincts() {
        List<String> enonces = itemsDeRegime().stream().map(Question::enonce).distinct().toList();

        assertThat(enonces)
                .as("FAB-1a : chaque item de la tache de regime decrit un animal different")
                .hasSize(itemsDeRegime().size());
    }

    /**
     * FAB-1b : sur la tache de regime, le regime juste n'est jamais invariant - les trois valeurs
     * possibles sont bien reparties, aucune ne domine la totalite des sept items.
     */
    @Test
    void fab1bLeRegimeJusteNEstJamaisInvariantSurLaTacheDeRegime() {
        long herbivores = itemsDeRegime().stream().filter(item -> item.bonneReponse().equals("herbivore")).count();

        assertThat(herbivores)
                .as("FAB-1b : le regime le plus servi ne couvre pas la totalite de la tache")
                .isLessThan(itemsDeRegime().size());
    }
}
