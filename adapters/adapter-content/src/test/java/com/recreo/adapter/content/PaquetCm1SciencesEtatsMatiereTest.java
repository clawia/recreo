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
 * <b>S31-3, le paquet {@code cm1-sciences-etats-matiere}, ligne {@code SCI-CM1-01} de "Sciences et
 * technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-
 * ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 4.3. Encode le plancher
 * de dix items (FER-2), les sept elements du libelle en bonne reponse (FER-1) et les deux taches
 * distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par l'appartenance de la bonne reponse a l'un des
 * deux ensembles fermes : l'<b>etat</b> (solide, liquide, gaz) et le <b>changement d'etat</b> (la
 * fusion, la solidification, la vaporisation, la condensation).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque etat et de chaque
 * changement reste la garde de {@code nadia-prof}, de meme que {@code SCI-1} (l'indice de l'item
 * de la vaporisation corrige la conception fausse sans nommer la reponse) et la couture avec
 * {@code cm1-sciences-melanges} (aucun item n'ecrit que l'eau disparait).</p>
 */
class PaquetCm1SciencesEtatsMatiereTest {

    private static final String PAQUET = "cm1-sciences-etats-matiere";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent les trois etats du libelle. */
    private static final Set<String> REPONSES_D_ETAT = Set.of("solide", "liquide", "gaz");

    /** Bonne reponse des items qui servent les quatre changements d'etat du libelle. */
    private static final Set<String> REPONSES_DE_CHANGEMENT = Set.of(
            "la fusion", "la solidification", "la vaporisation", "la condensation");

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

    private static List<Question> itemsDEtat() {
        return items().stream().filter(item -> REPONSES_D_ETAT.contains(item.bonneReponse())).toList();
    }

    private static List<Question> itemsDeChangement() {
        return items().stream()
                .filter(item -> REPONSES_DE_CHANGEMENT.contains(item.bonneReponse())).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S31-3). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, les trois etats : chacun est en bonne reponse au moins une fois. */
    @Test
    void fer1LesTroisEtatsSontRepresentesEnBonneReponse() {
        Set<String> reponses = itemsDEtat().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois etats doivent etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_D_ETAT);
    }

    /** FER-1, les quatre changements d'etat : chacun est en bonne reponse au moins une fois. */
    @Test
    void fer1LesQuatreChangementsSontRepresentesEnBonneReponse() {
        Set<String> reponses = itemsDeChangement().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les quatre changements d'etat doivent etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_CHANGEMENT);
    }

    /** Chaque item propose exactement deux distracteurs (trois propositions, SCI-4). */
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
        int etat = itemsDEtat().size();
        int changement = itemsDeChangement().size();

        assertThat(etat).as("la tache de l'etat est servie").isGreaterThan(0);
        assertThat(changement).as("la tache du changement est servie").isGreaterThan(0);
        assertThat(Math.min(etat, changement))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (etat, changement additionnes). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }

    /**
     * Couture avec {@code cm1-sciences-melanges} (MEL-R1, S31-5) : aucun enonce ni aucune bonne
     * reponse de cette ligne n'ecrit que l'eau (ou toute matiere) disparait purement et
     * simplement - ce que la ligne 1 doit precisement defaire. Seul l'indice de l'item qui porte
     * la conception fausse en distracteur ("la disparition") y fait exception, et seulement pour
     * la corriger par une negation.
     */
    @Test
    void aucunEnonceEtAucuneBonneReponseNAffirmeQuUneMatiereDisparait() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s : l'enonce n'affirme pas qu'une matiere disparait", item.id().value())
                    .doesNotContainIgnoringCase("dispara");
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse n'affirme pas qu'une matiere disparait",
                            item.id().value())
                    .doesNotContainIgnoringCase("dispara");
        }
    }

    /**
     * SCI-1 : l'item qui porte la conception fausse "la disparition" en distracteur la corrige,
     * par une negation, sans jamais nommer la bonne reponse ("la vaporisation") en toutes lettres.
     */
    @Test
    void sci1LIndiceDeLItemDeLaDisparitionCorrigeLaConceptionFausseSansNommerLaReponse() {
        Question item = items().stream()
                .filter(candidat -> candidat.distracteurs().contains("la disparition")).findFirst()
                .orElseThrow(() -> new AssertionError("aucun item ne porte 'la disparition' en distracteur"));

        assertThat(item.indice().orElseThrow())
                .as("SCI-1 : l'indice corrige la conception fausse par une negation")
                .containsIgnoringCase("n'a pas disparu")
                .doesNotContain(item.bonneReponse());
    }
}
