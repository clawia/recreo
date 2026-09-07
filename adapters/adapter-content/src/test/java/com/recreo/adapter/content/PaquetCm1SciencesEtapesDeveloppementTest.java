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
 * <b>S31-4, le paquet {@code cm1-sciences-etapes-developpement}, ligne {@code SCI-CM1-08} de
 * "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-
 * s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 4.3. Encode
 * le plancher de dix items (FER-2), les cinq elements du libelle en bonne reponse (FER-1) et les
 * deux taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par l'appartenance de la bonne reponse a l'ensemble
 * ferme des cinq etapes : la tache A (quelle <b>etape suit</b> une etape decrite, bonne reponse un
 * nom d'etape) et la tache B (ce qui <b>caracterise</b> une etape nommee, bonne reponse une
 * description).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque etape et de son ordre
 * reste la garde de {@code nadia-prof}. {@code V3} (aucune reconstitution de la sequence entiere en
 * un seul geste) n'est pas mordable par un automate : chaque item de la tache A compare deux
 * etapes, jamais plus, et c'est une propriete de redaction verifiee par lecture.</p>
 */
class PaquetCm1SciencesEtapesDeveloppementTest {

    private static final String PAQUET = "cm1-sciences-etapes-developpement";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items de la tache A, un nom d'etape parmi les cinq du libelle. */
    private static final Set<String> REPONSES_D_ETAPE = Set.of(
            "la naissance", "la croissance", "la reproduction", "le vieillissement", "la mort");

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

    private static List<Question> itemsDeTransition() {
        return items().stream().filter(item -> REPONSES_D_ETAPE.contains(item.bonneReponse())).toList();
    }

    private static List<Question> itemsDeCaracteristique() {
        return items().stream()
                .filter(item -> !REPONSES_D_ETAPE.contains(item.bonneReponse())).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S31-4). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, les cinq etapes : chacune est en bonne reponse au moins une fois, des la tache A. */
    @Test
    void fer1LesCinqEtapesSontRepresenteesEnBonneReponseSurLaTacheDeTransition() {
        Set<String> reponses = itemsDeTransition().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les cinq etapes doivent etre en bonne reponse sur la tache de transition")
                .containsExactlyInAnyOrderElementsOf(REPONSES_D_ETAPE);
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
        int transition = itemsDeTransition().size();
        int caracteristique = itemsDeCaracteristique().size();

        assertThat(transition).as("la tache de transition est servie").isGreaterThan(0);
        assertThat(caracteristique).as("la tache de caracteristique est servie").isGreaterThan(0);
        assertThat(Math.min(transition, caracteristique))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (transition, caracteristique additionnes). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }

    /**
     * V3 : sur la tache de transition, chaque distracteur est lui-meme un nom d'etape - jamais une
     * sequence de plusieurs etapes a la fois. La comparaison porte toujours sur deux etapes, jamais
     * sur une suite a reconstituer.
     */
    @Test
    void v3ChaqueDistracteurDeLaTacheDeTransitionEstUneSeuleEtape() {
        for (Question item : itemsDeTransition()) {
            for (String distracteur : item.distracteurs()) {
                assertThat(REPONSES_D_ETAPE)
                        .as("item %s : chaque distracteur de la tache de transition est une seule etape",
                                item.id().value())
                        .contains(distracteur);
            }
        }
    }

    /**
     * Reprise S31 (REFUSE, {@code squad/conformite/FICHE-bandit-manchot-etapes-developpement-
     * cm1.md}) : un arbre ne finit jamais de grandir, contrairement a ce que l'item de l'arbre
     * affirmait, et cette premisse fausse dementait la bonne reponse d'un autre item du meme
     * paquet (celui de la reproduction, qui range "il arrete de grandir definitivement" en reponse
     * fausse).
     */
    @Test
    void lItemDeLArbreNAffirmePlusQuUnArbreAFiniDeGrandir() {
        Question item = itemDontLEnonceContient("Un arbre produit");

        assertThat(item.enonce())
                .as("un arbre continue de grandir toute sa vie, meme apres sa premiere fructification")
                .doesNotContain("a fini de grandir");
    }

    /**
     * Reprise S31 (REFUSE, meme famille de defaut que le refus de {@code SCI-CM1-17} a s30) :
     * aucune paire d'items de la tache de transition ne partage a la fois sa bonne reponse et ses
     * deux distracteurs - ce serait le meme item servi deux fois sous deux noms d'animal. Sous les
     * deux seuils de la clause 1 d'{@code ADR-0086} (trois items pour une repetition, trois mots
     * pour une bonne reponse), donc invisible de ce garde-fou transverse ; verifie ici, au niveau
     * du paquet.
     */
    @Test
    void aucuneDeuxItemsDeLaTacheDeTransitionNePartagentLaMemeBonneReponseEtLesMemesDistracteurs() {
        List<Question> transition = itemsDeTransition();
        for (int i = 0; i < transition.size(); i++) {
            for (int j = i + 1; j < transition.size(); j++) {
                Question a = transition.get(i);
                Question b = transition.get(j);
                boolean memeBonneReponse = a.bonneReponse().equals(b.bonneReponse());
                boolean memesDistracteurs =
                        new HashSet<>(a.distracteurs()).equals(new HashSet<>(b.distracteurs()));

                assertThat(memeBonneReponse && memesDistracteurs)
                        .as("items %s et %s : meme bonne reponse et memes distracteurs, un seul item"
                                + " sous deux noms d'animal", a.id().value(), b.id().value())
                        .isFalse();
            }
        }
    }

    private static Question itemDontLEnonceContient(String fragment) {
        return items().stream().filter(item -> item.enonce().contains(fragment)).findFirst()
                .orElseThrow(() -> new AssertionError("aucun item ne contient : " + fragment));
    }
}
