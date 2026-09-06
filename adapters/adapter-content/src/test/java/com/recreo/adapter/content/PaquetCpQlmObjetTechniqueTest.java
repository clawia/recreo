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
 * <b>S29-1, le paquet {@code cp-qlm-objet-technique}, ligne {@code QLM-CP-07} de "Questionner le
 * monde" (CP).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-
 * quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md}, section 4.1. Encode le plafond de vingt
 * items, les deux moities du libelle en bonne reponse (FER-1) et les deux taches distinctes par
 * construction (FER-3).
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de la bonne reponse : nommer
 * la <b>fonction</b> d'un objet decrit ("A quoi sert un parapluie ?" -&gt; "a se proteger de la
 * pluie") et nommer une de ses <b>parties</b> ("Quelle partie du velo sert a s'asseoir ?" -&gt;
 * "la selle").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque fonction et de chaque
 * partie reste la garde de {@code nadia-prof}, et aucune condition de biais ({@code BIA-*}) ne
 * s'applique a cette ligne.</p>
 */
class PaquetCpQlmObjetTechniqueTest {

    private static final String PAQUET = "cp-qlm-objet-technique";

    private static final int PLAFOND_ITEMS = 20;

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent la moitie "fonction" du libelle de competence. */
    private static final Set<String> REPONSES_DE_FONCTION = Set.of(
            "à se protéger de la pluie", "à couper", "à écrire", "à balayer",
            "à se laver les dents");

    /** Bonne reponse des items qui servent la moitie "parties" du libelle de competence. */
    private static final Set<String> REPONSES_DE_PARTIE = Set.of(
            "la selle", "le guidon", "la pédale", "le manche", "la toile", "la lame");

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

    private static boolean estDeFonction(Question item) {
        return REPONSES_DE_FONCTION.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeFonction() {
        return items().stream().filter(PaquetCpQlmObjetTechniqueTest::estDeFonction).toList();
    }

    private static List<Question> itemsDeParties() {
        return items().stream().filter(item -> !estDeFonction(item)).toList();
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S29-1). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, moitie "fonction" : les cinq fonctions du paquet sont toutes en bonne reponse. */
    @Test
    void fer1LesCinqFonctionsSontToutesRepresenteesEnBonneReponse() {
        Set<String> reponses = itemsDeFonction().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : la moitie \"fonction\" du libelle doit etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_FONCTION);
    }

    /** FER-1, moitie "parties" : les six parties du paquet sont toutes en bonne reponse. */
    @Test
    void fer1LesSixPartiesSontToutesRepresenteesEnBonneReponse() {
        Set<String> reponses = itemsDeParties().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : la moitie \"parties\" du libelle doit etre en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_PARTIE);
    }

    /**
     * Un item de fonction ne repond jamais par une partie, et reciproquement : les deux taches
     * n'empruntent jamais l'espace de reponse l'une de l'autre.
     */
    @Test
    void unItemDeFonctionNeReponditJamaisParUnePartieEtReciproquement() {
        for (Question item : itemsDeFonction()) {
            assertThat(REPONSES_DE_PARTIE)
                    .as("item %s : un item de fonction ne repond pas par une partie",
                            item.id().value())
                    .doesNotContain(item.bonneReponse());
        }
        for (Question item : itemsDeParties()) {
            assertThat(REPONSES_DE_FONCTION)
                    .as("item %s : un item de partie ne repond pas par une fonction",
                            item.id().value())
                    .doesNotContain(item.bonneReponse());
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
        int fonction = itemsDeFonction().size();
        int parties = itemsDeParties().size();

        assertThat(fonction).as("la tache de fonction est servie").isGreaterThan(0);
        assertThat(parties).as("la tache de parties est servie").isGreaterThan(0);
        assertThat(Math.min(fonction, parties))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * S30-4, bloc B (`OBJ-R2`) : au moins deux des cinq items de fonction portent un distracteur
     * qui n'est la fonction d'aucun autre objet du paquet - donc plausible pour l'objet nomme et non
     * un decor eliminable sans le lire. Propriete structurelle et proxy de celle que nadia-prof
     * rejoue a l'ecran : un distracteur invente pour l'objet ne peut, par construction, etre la
     * fonction reelle d'un autre objet du meme paquet.
     */
    @Test
    void auMoinsDeuxItemsDeFonctionPortentUnDistracteurEtrangerAuxAutresObjetsDuPaquet() {
        Set<String> reponsesConnues = new HashSet<>(REPONSES_DE_FONCTION);
        reponsesConnues.addAll(REPONSES_DE_PARTIE);

        long comptes = itemsDeFonction().stream()
                .filter(item -> item.distracteurs().stream().anyMatch(d -> !reponsesConnues.contains(d)))
                .count();

        assertThat(comptes)
                .as("OBJ-R2 : au moins deux items de fonction portent un distracteur specifique a"
                        + " l'objet nomme, absent de toute autre bonne reponse du paquet")
                .isGreaterThanOrEqualTo(2);
    }
}
