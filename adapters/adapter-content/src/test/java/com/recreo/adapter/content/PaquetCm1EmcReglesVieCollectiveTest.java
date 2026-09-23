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
 * <b>S40-REGLES, le paquet {@code cm1-emc-regles-vie-collective}, ligne {@code EMC-CM1-04} de
 * l'enseignement moral et civique (CM1).</b> Referentiel : {@code squad/programme/enseignement-
 * moral-et-civique/competences-cm1.md}. Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md, ligne
 * 427) : meme patron que {@code EMC-CP-06} (cp-emc-regles-de-vie.yaml).
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce et de la bonne
 * reponse : nommer la regle a partir d'une situation decrite (items 1 a 6) et dire pourquoi cette
 * regle existe, sur un enonce qui commence par "Pourquoi" et dont la bonne reponse commence par
 * "pour " (items 7 a 11).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-6} et {@code EMC-8} demandent une
 * lecture semantique de chaque situation, pas une forme reconnaissable dans le texte : non gardees
 * ici. {@code EMC-5} (chaque distracteur est nettement hors sujet, aucun item n'a deux reponses
 * defendables) et {@code EMC-10} restent la garde de {@code nadia-prof} seule.</p>
 */
class PaquetCm1EmcReglesVieCollectiveTest {

    private static final String PAQUET = "cm1-emc-regles-vie-collective";

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

    /** Tache "pourquoi" : un enonce qui commence par "Pourquoi" et dit ce que la regle protege. */
    private static boolean estDeTacheJustification(Question item) {
        return item.enonce().startsWith("Pourquoi");
    }

    private static List<Question> itemsDeRegle() {
        return items().stream().filter(item -> !estDeTacheJustification(item)).toList();
    }

    private static List<Question> itemsDeJustification() {
        return items().stream().filter(PaquetCm1EmcReglesVieCollectiveTest::estDeTacheJustification).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S40-REGLES). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les deux moities du libelle sont chacune en position de bonne reponse - la regle
     * elle-meme (une reponse qui n'est jamais une justification) et ce a quoi elle sert (une reponse
     * qui commence toujours par "pour ").
     */
    @Test
    void fer1LesDeuxMoitiesDuLibelleSontEnBonneReponse() {
        List<Question> regle = itemsDeRegle();
        List<Question> justification = itemsDeJustification();

        assertThat(regle).as("la moitie \"regle\" du libelle est servie").isNotEmpty();
        assertThat(justification).as("la moitie \"a quoi elle sert\" du libelle est servie").isNotEmpty();

        for (Question item : regle) {
            assertThat(item.bonneReponse())
                    .as("item %s : une reponse de regle ne commence pas par une justification",
                            item.id().value())
                    .doesNotStartWith("pour ");
        }
        for (Question item : justification) {
            assertThat(item.bonneReponse())
                    .as("item %s : une reponse de justification commence par \"pour \"",
                            item.id().value())
                    .startsWith("pour ");
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int regle = itemsDeRegle().size();
        int justification = itemsDeJustification().size();

        assertThat(regle).as("la tache de la regle est servie").isGreaterThan(0);
        assertThat(justification).as("la tache de justification est servie").isGreaterThan(0);
        assertThat(Math.min(regle, justification))
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

    /** Aucune bonne reponse ne se repete d'un item a l'autre. */
    @Test
    void aucuneBonneReponseNeSeRepeteDUnItemALAutre() {
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
}
