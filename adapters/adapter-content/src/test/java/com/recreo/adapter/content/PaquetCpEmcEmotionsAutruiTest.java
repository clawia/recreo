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
 * <b>S31-1, le paquet {@code cp-emc-emotions-autrui}, ligne {@code EMC-CP-01} de l'enseignement
 * moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}. Arbitrage prealable : {@code squad/conformite/ARBITRAGE-PREALABLE-s31-ce-qui-
 * ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 3.1, ligne
 * {@code EMC-CP-01}.
 *
 * <p><b>Deux operations mentales distinctes (FER-3)</b>, distinguees par la forme de l'enonce :
 * inferer l'emotion d'un evenement decrit a un personnage nomme ("Léa a perdu son doudou préféré...
 * Que ressent-elle ?" -&gt; "de la tristesse"), et lire un signe corporel decrit ("Tom serre les
 * poings et crie très fort. Qu'est-ce que cela montre ?" -&gt; "de la colère").</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-2}, {@code EMC-8}, {@code EMC-10},
 * {@code CAT-1} et {@code PLA-1} demandent une lecture semantique de chaque situation, pas une forme
 * reconnaissable dans le texte : non gardees ici, restent la garde de {@code nadia-prof} seule.
 * {@code FAB-1} (aucun sous-ensemble de trois items ou plus ne partage a la fois le meme trio de
 * propositions et la meme bonne reponse) est verifiee depot entier par
 * {@code LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest} (ADR-0086), pas dupliquee ici.</p>
 */
class PaquetCpEmcEmotionsAutruiTest {

    private static final String PAQUET = "cp-emc-emotions-autrui";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les quatre emotions de la parenthese du libelle. */
    private static final Set<String> QUATRE_EMOTIONS = Set.of(
            "de la joie", "de la tristesse", "de la colère", "de la peur");

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

    /** Tache "signe corporel" : un enonce qui demande ce qu'un comportement montre. */
    private static boolean estDeTacheSigneCorporel(Question item) {
        return item.enonce().contains("Qu'est-ce que cela montre");
    }

    private static List<Question> itemsDEvenement() {
        return items().stream().filter(item -> !estDeTacheSigneCorporel(item)).toList();
    }

    private static List<Question> itemsDeSigne() {
        return items().stream().filter(PaquetCpEmcEmotionsAutruiTest::estDeTacheSigneCorporel)
                .toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1 : les quatre emotions de la parenthese sont chacune en position de bonne reponse. */
    @Test
    void fer1LesQuatreEmotionsSontEnBonneReponse() {
        Set<String> reponses = items().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les quatre emotions de la parenthese doivent toutes etre representees")
                .containsExactlyInAnyOrderElementsOf(QUATRE_EMOTIONS);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int evenement = itemsDEvenement().size();
        int signe = itemsDeSigne().size();

        assertThat(evenement).as("la tache d'evenement decrit est servie").isGreaterThan(0);
        assertThat(signe).as("la tache de signe corporel est servie").isGreaterThan(0);
        assertThat(Math.min(evenement, signe))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
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

    /** Chaque proposition (bonne reponse et distracteurs) est l'une des quatre emotions connues. */
    @Test
    void chaqueGroupeDePropositionsNeContientQueDesEmotionsConnues() {
        for (Question item : items()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item %s : chaque proposition est une des quatre emotions du referentiel",
                            item.id().value())
                    .allMatch(QUATRE_EMOTIONS::contains);
        }
    }
}
