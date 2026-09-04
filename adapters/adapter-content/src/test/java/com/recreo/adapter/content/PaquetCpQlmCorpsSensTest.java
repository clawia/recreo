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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S27-2 puis S28-1, le paquet {@code cp-qlm-corps-sens}, ligne 3 de "Questionner le monde"
 * (CP).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-
 * et-le-prix-des-quatre-lots.md}, section 3.1. Encode le plafond de vingt items, l'absence de
 * reponse repetee, et - depuis S28-1 - les deux moities du libelle de competence ("les cinq sens"
 * et "les principales parties du corps humain") et les deux taches que le paquet porte.
 *
 * <p>Les deux moities se lisent sur la bonne reponse : celles de la moitie "sens" nomment l'organe
 * qui permet un sens (l'ouie, la vue, l'odorat, le gout, le toucher), tout le reste nomme une
 * partie du corps. Le toucher est servi par deux reponses distinctes depuis S28-1 - "les mains"
 * (S27-2) et "la peau" (S28-1, demande nommement par nadia-prof le 2026-09-03, pour qu'un enfant
 * ne ressorte pas en croyant qu'on ne touche qu'avec les mains) - sans que cela ne compte pour un
 * sixieme sens.</p>
 */
class PaquetCpQlmCorpsSensTest {

    private static final String PAQUET = "cp-qlm-corps-sens";

    private static final int PLAFOND_ITEMS = 20;

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent la moitie "cinq sens" du libelle de competence. */
    private static final Set<String> REPONSES_DE_SENS = Set.of(
            "les oreilles", "les yeux", "le nez", "la bouche", "les mains", "la peau");

    /**
     * Sens servi par chaque reponse de {@link #REPONSES_DE_SENS} : le toucher est servi par deux
     * reponses distinctes ("les mains" et "la peau"), sans que cela ne compte pour un sixieme sens.
     */
    private static final Map<String, String> SENS_PAR_REPONSE = Map.of(
            "les oreilles", "ouie",
            "les yeux", "vue",
            "le nez", "odorat",
            "la bouche", "gout",
            "les mains", "toucher",
            "la peau", "toucher");

    /** FER-1 : les cinq parties du corps ajoutees a S28-1, en plus des trois d'origine. */
    private static final Set<String> PARTIES_DU_CORPS_NEUVES_A_S28_1 = Set.of(
            "les bras", "les pieds", "le dos", "le ventre", "le genou");

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

    private static boolean estDeSens(Question item) {
        return REPONSES_DE_SENS.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeSens() {
        return items().stream().filter(PaquetCpQlmCorpsSensTest::estDeSens).toList();
    }

    private static List<Question> itemsDePartieDuCorps() {
        return items().stream().filter(item -> !estDeSens(item)).toList();
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * Chaque organe n'est bonne reponse que d'un seul item : aucune question ne se repete sous une
     * autre formulation.
     */
    @Test
    void aucunOrganeNEstBonneReponseDePlusDUnItem() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Set<String> dejaVues = new HashSet<>();
        for (Question item : items) {
            assertThat(dejaVues.add(item.bonneReponse()))
                    .as("item %s : la reponse \"%s\" est deja la bonne reponse d'un autre item de"
                            + " ce paquet", item.id().value(), item.bonneReponse())
                    .isTrue();
        }
    }

    /** Chaque item propose exactement trois choix (une bonne reponse, deux distracteurs). */
    @Test
    void chaqueItemProposeExactementTroisChoix() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /** FER-2 : au moins dix items servent cette ligne (14 depuis S28-1). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1, moitie "cinq sens" du libelle : les cinq sens sont tous representes en bonne reponse.
     * Le toucher est servi par deux items ("les mains", "la peau") mais compte pour un seul sens.
     */
    @Test
    void fer1LesCinqSensSontTousRepresentesEnBonneReponse() {
        List<Question> items = itemsDeSens();
        assertThat(items).isNotEmpty();

        Set<String> sensCouverts = items.stream()
                .map(item -> SENS_PAR_REPONSE.get(item.bonneReponse()))
                .collect(Collectors.toSet());

        assertThat(sensCouverts)
                .as("FER-1 : les cinq sens doivent tous etre representes en bonne reponse")
                .containsExactlyInAnyOrder("ouie", "vue", "odorat", "gout", "toucher");
    }

    /**
     * FER-1, moitie "principales parties du corps" du libelle : les cinq parties ajoutees a S28-1
     * (bras, pieds, dos, ventre, genou) sont bien en bonne reponse, en plus des trois d'origine.
     */
    @Test
    void fer1LesCinqPartiesDuCorpsNeuvesSontRepresenteesEnBonneReponse() {
        Set<String> reponses = itemsDePartieDuCorps().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les cinq parties du corps ajoutees a S28-1 sont en bonne reponse")
                .containsAll(PARTIES_DU_CORPS_NEUVES_A_S28_1);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int sens = itemsDeSens().size();
        int partieDuCorps = itemsDePartieDuCorps().size();

        assertThat(sens).as("la tache des cinq sens est servie").isGreaterThan(0);
        assertThat(partieDuCorps).as("la tache des parties du corps est servie").isGreaterThan(0);
        assertThat(Math.min(sens, partieDuCorps))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Observation datee du 2026-09-03 (nadia-prof) : le toucher n'etait servi que par "les mains".
     * Un item distinct existe desormais, dont la bonne reponse est "la peau", pour qu'un enfant ne
     * ressorte pas en croyant qu'on ne touche qu'avec les mains.
     */
    @Test
    void laPeauEstBonneReponseDUnItemDistinctDesMainsPourLeToucher() {
        List<Question> items = items();

        assertThat(items)
                .as("nadia-prof, 2026-09-03 : un item dedie a \"la peau\" existe pour le toucher")
                .anyMatch(item -> item.bonneReponse().equals("la peau"));

        long itemsDeToucher = items.stream()
                .filter(item -> item.bonneReponse().equals("les mains")
                        || item.bonneReponse().equals("la peau"))
                .count();
        assertThat(itemsDeToucher)
                .as("le toucher est servi par deux items distincts : les mains et la peau")
                .isEqualTo(2);
    }
}
