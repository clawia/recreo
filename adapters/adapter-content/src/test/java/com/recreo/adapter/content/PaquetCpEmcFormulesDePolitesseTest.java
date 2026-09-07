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
 * <b>S31-1, le paquet {@code cp-emc-formules-de-politesse}, ligne {@code EMC-CP-05} de
 * l'enseignement moral et civique (CP).</b> Referentiel : {@code squad/programme/
 * enseignement-moral-et-civique/competences-cp.md}. Arbitrage prealable : {@code squad/conformite/
 * ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md},
 * section 3.1, ligne {@code EMC-CP-05}.
 *
 * <p><b>Couverture partielle exigee d'avance (backlog, refus n°14 de nadia-prof)</b> : ce paquet
 * sert la connaissance des formules seule ; leur emploi en situation n'est pas verifie. La mention
 * doit figurer dans la matrice a la fermeture - ce test ne la porte pas, ce n'est pas un fait du
 * contenu servi.</p>
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : choisir la
 * formule qui convient a une situation decrite ("...Que doit-elle dire ?" -&gt; "bonjour"), et dire
 * quelle regle d'echange un dialogue ne respecte pas ("...Que ne respecte pas Malo ?" -&gt;
 * "écouter sans couper la parole").</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-8}, {@code EMC-10} et
 * {@code CAT-1} demandent une lecture semantique, non gardees ici. {@code FAB-1} est verifiee depot
 * entier par {@code LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest} (ADR-0086).</p>
 */
class PaquetCpEmcFormulesDePolitesseTest {

    private static final String PAQUET = "cp-emc-formules-de-politesse";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

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

    /** Tache "reconnaissance" : un enonce qui demande ce qu'un dialogue ne respecte pas. */
    private static boolean estDeTacheReconnaissance(Question item) {
        return item.enonce().contains("Que ne respecte pas");
    }

    private static List<Question> itemsDeChoix() {
        return items().stream().filter(item -> !estDeTacheReconnaissance(item)).toList();
    }

    private static List<Question> itemsDeReconnaissance() {
        return items().stream().filter(PaquetCpEmcFormulesDePolitesseTest::estDeTacheReconnaissance)
                .toList();
    }

    /** FER-1 : classement d'une bonne reponse dans l'un des quatre elements de la parenthese. */
    private static String elementDuLibelle(String bonneReponse) {
        if (bonneReponse.contains("bonjour")) {
            return "saluer";
        }
        if (bonneReponse.contains("merci")) {
            return "remercier";
        }
        if (bonneReponse.contains("couper")) {
            return "écouter sans couper";
        }
        if (bonneReponse.contains("plaît") || bonneReponse.contains("demander")) {
            return "demander";
        }
        return "inconnu";
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1 : les quatre elements de la parenthese sont chacun en position de bonne reponse. */
    @Test
    void fer1LesQuatreElementsSontEnBonneReponse() {
        Set<String> elements = items().stream()
                .map(Question::bonneReponse)
                .map(PaquetCpEmcFormulesDePolitesseTest::elementDuLibelle)
                .collect(Collectors.toSet());

        assertThat(elements)
                .as("FER-1 : les quatre elements de la parenthese (saluer, remercier, demander,"
                        + " écouter sans couper) doivent tous etre representes")
                .containsExactlyInAnyOrder("saluer", "remercier", "demander", "écouter sans couper");
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int choix = itemsDeChoix().size();
        int reconnaissance = itemsDeReconnaissance().size();

        assertThat(choix).as("la tache de choix de formule est servie").isGreaterThan(0);
        assertThat(reconnaissance).as("la tache de reconnaissance est servie").isGreaterThan(0);
        assertThat(Math.min(choix, reconnaissance))
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
}
