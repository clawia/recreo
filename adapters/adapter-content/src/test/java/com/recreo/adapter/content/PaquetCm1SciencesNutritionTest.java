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
 * <b>S40-NUTRITION, le paquet {@code cm1-sciences-nutrition}, ligne {@code SCI-CM1-09} de "Sciences
 * et technologie" (CM1).</b> Referentiel : {@code squad/programme/sciences-et-technologie/
 * competences-cm1.md}. Arbitrage prealable deja rendu (ARBITRAGE-PREALABLE-s32-trois-instances-ma-
 * borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md, section 17.2) :
 * association fonction/organe en texte, sans schema.
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de la bonne reponse : donner l'organe
 * principal d'une fonction citee (items 1 a 6, bonne reponse un nom d'organe) et donner la fonction
 * principale d'un organe cite (items 7 a 11, bonne reponse un nom de fonction).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude scientifique de chaque association
 * (digestion/estomac, respiration/poumons, circulation/coeur) reste la garde de {@code nadia-prof}.</p>
 */
class PaquetCm1SciencesNutritionTest {

    private static final String PAQUET = "cm1-sciences-nutrition";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Les trois organes de nutrition du referentiel, bonne reponse de la tache "organe". */
    private static final Set<String> ORGANES = Set.of("l'estomac", "les poumons", "le coeur");

    /** Les trois fonctions de nutrition du referentiel, bonne reponse de la tache "fonction". */
    private static final Set<String> FONCTIONS = Set.of("la digestion", "la respiration", "la circulation");

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

    private static boolean estDeTacheOrgane(Question item) {
        return ORGANES.contains(item.bonneReponse());
    }

    private static List<Question> itemsDOrgane() {
        return items().stream().filter(PaquetCm1SciencesNutritionTest::estDeTacheOrgane).toList();
    }

    private static List<Question> itemsDeFonction() {
        return items().stream().filter(item -> !estDeTacheOrgane(item)).toList();
    }

    /** Les elements de {@code ensemble} qui ne sont pas la bonne reponse de l'item. */
    private static Set<String> autresQue(Set<String> ensemble, String bonneReponse) {
        Set<String> reste = new HashSet<>(ensemble);
        reste.remove(bonneReponse);
        return reste;
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S40-NUTRITION). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les trois organes et les trois fonctions du referentiel sont chacun en bonne reponse
     * d'au moins un item.
     */
    @Test
    void fer1LesTroisOrganesEtLesTroisFonctionsSontChacunEnBonneReponse() {
        Set<String> organesServis = itemsDOrgane().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());
        Set<String> fonctionsServies = itemsDeFonction().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(organesServis)
                .as("FER-1 : les trois organes du referentiel sont chacun en bonne reponse")
                .isEqualTo(ORGANES);
        assertThat(fonctionsServies)
                .as("FER-1 : les trois fonctions du referentiel sont chacune en bonne reponse")
                .isEqualTo(FONCTIONS);
    }

    /**
     * Sur la tache "organe", les deux distracteurs sont toujours les deux autres organes de
     * nutrition - jamais un organe sans rapport, comme le cerveau ou les reins.
     */
    @Test
    void surLaTacheOrganeLesDistracteursSontLesDeuxAutresOrganesDeNutrition() {
        List<Question> items = itemsDOrgane();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            Set<String> distracteurs = Set.copyOf(item.distracteurs());
            assertThat(distracteurs)
                    .as("item %s : les distracteurs sont les deux autres organes de nutrition",
                            item.id().value())
                    .isEqualTo(autresQue(ORGANES, item.bonneReponse()));
        }
    }

    /**
     * Sur la tache "fonction", les deux distracteurs sont toujours les deux autres fonctions de
     * nutrition.
     */
    @Test
    void surLaTacheFonctionLesDistracteursSontLesDeuxAutresFonctionsDeNutrition() {
        List<Question> items = itemsDeFonction();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            Set<String> distracteurs = Set.copyOf(item.distracteurs());
            assertThat(distracteurs)
                    .as("item %s : les distracteurs sont les deux autres fonctions de nutrition",
                            item.id().value())
                    .isEqualTo(autresQue(FONCTIONS, item.bonneReponse()));
        }
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
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
        int organe = itemsDOrgane().size();
        int fonction = itemsDeFonction().size();

        assertThat(organe).as("la tache organe est servie").isGreaterThan(0);
        assertThat(fonction).as("la tache fonction est servie").isGreaterThan(0);
        assertThat(Math.min(organe, fonction))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
