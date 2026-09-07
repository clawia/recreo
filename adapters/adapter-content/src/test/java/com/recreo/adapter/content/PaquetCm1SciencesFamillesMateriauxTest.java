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
 * <b>S31-4, le paquet {@code cm1-sciences-familles-materiaux}, ligne {@code SCI-CM1-11} de
 * "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-
 * s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 4.3. Encode
 * le plancher de dix items (FER-2) et les deux taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de l'enonce : la tache A
 * (<b>famille</b> d'un objet decrit, "de quelle famille de materiau...") et la tache B (<b>choix</b>
 * d'un materiau d'apres une propriete nommee, "Lequel choisir ?").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque famille et de chaque
 * propriete reste la garde de {@code nadia-prof}.</p>
 */
class PaquetCm1SciencesFamillesMateriauxTest {

    private static final String PAQUET = "cm1-sciences-familles-materiaux";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Fragment qui distingue la tache B (choix) de la tache A (famille) dans l'enonce. */
    private static final String FRAGMENT_TACHE_DE_CHOIX = "Lequel choisir";

    /** Les quatre proprietes du libelle, nommees comme criteres dans l'enonce de la tache B. */
    private static final List<String> ADJECTIFS_DE_PROPRIETE =
            List.of("résistant", "isolant", "souple", "imperméable");

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

    private static boolean estDeLaTacheDeChoix(Question item) {
        return item.enonce().contains(FRAGMENT_TACHE_DE_CHOIX);
    }

    private static List<Question> itemsDeFamille() {
        return items().stream().filter(item -> !estDeLaTacheDeChoix(item)).toList();
    }

    private static List<Question> itemsDeChoix() {
        return items().stream().filter(PaquetCm1SciencesFamillesMateriauxTest::estDeLaTacheDeChoix).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S31-4). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
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
        int famille = itemsDeFamille().size();
        int choix = itemsDeChoix().size();

        assertThat(famille).as("la tache de famille est servie").isGreaterThan(0);
        assertThat(choix).as("la tache de choix est servie").isGreaterThan(0);
        assertThat(Math.min(famille, choix))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (famille, choix additionnes). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }

    /**
     * FER-1 : sur la tache de choix, les quatre proprietes du libelle (resistance, isolation,
     * souplesse, impermeabilite) sont chacune le critere nomme d'au moins un item - la position de
     * bonne reponse est celle de la propriete testee (un materiau), pas une reprise litterale du
     * mot dans le texte de la reponse (meme lecture que "un moyen de separation" sur
     * cm1-sciences-melanges).
     */
    @Test
    void fer1LesQuatrePropertiesSontChacuneLeCritereDUnItemDeLaTacheDeChoix() {
        List<Question> choix = itemsDeChoix();
        assertThat(choix).isNotEmpty();

        for (String adjectif : ADJECTIFS_DE_PROPRIETE) {
            boolean present = choix.stream().anyMatch(item -> item.enonce().contains(adjectif));
            assertThat(present)
                    .as("FER-1 : la propriete portee par '%s' est le critere d'au moins un item", adjectif)
                    .isTrue();
        }
    }

    /**
     * FAB-1b : sur la tache de choix, le materiau juste n'est jamais le meme d'un item a l'autre -
     * les six bonnes reponses sont toutes distinctes.
     */
    @Test
    void fab1bLeMateriauJusteNEstJamaisLeMemeSurLaTacheDeChoix() {
        List<String> reponses = itemsDeChoix().stream().map(Question::bonneReponse).distinct().toList();

        assertThat(reponses)
                .as("FAB-1b : chaque item de la tache de choix a un materiau juste different")
                .hasSize(itemsDeChoix().size());
    }

    /** Sur la tache de famille, les cinq objets decrits relevent chacun d'une famille differente. */
    @Test
    void lesCinqFamillesDeLaTacheDeFamilleSontToutesDistinctes() {
        List<String> reponses = itemsDeFamille().stream().map(Question::bonneReponse).distinct().toList();

        assertThat(reponses)
                .as("chaque item de la tache de famille a une bonne reponse differente")
                .hasSize(itemsDeFamille().size());
    }
}
