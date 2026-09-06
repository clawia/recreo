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
 * <b>S29-4, le paquet {@code cp-emc-permis-interdit}, ligne {@code EMC-CP-02} de l'enseignement
 * moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}. Arbitrage prealable : {@code squad/conformite/ARBITRAGE-PREALABLE-s29-le-
 * prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md}, sections 4.3 et 5.
 *
 * <p><b>S29 (yanis-qa, {@code squad/sprints/s29-parallelisation-et-dettes-payees/rapport-
 * qualite.md}, section 3.3)</b> : ce paquet n'avait aucun fichier de test de matiere, ni a sa
 * livraison ni a sa reprise apres refus. Ce fichier comble ce vide pour ce qui est reellement
 * mordable.
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de l'enonce : reconnaitre
 * l'interdit ou le permis a partir d'une situation decrite ("Malo dit des mots méchants à Hugo.
 * Qu'est-ce qu'il lui fait ?" -&gt; "une insulte"), et dire ce que la regle protege, sur un enonce
 * qui commence toujours par "Pourquoi" ("Pourquoi est-il interdit d'insulter un camarade ?" -&gt;
 * "pour ne pas le faire souffrir").</p>
 *
 * <p><b>FER-1</b> : les trois qualifications de l'interdit du libelle ({@code une moquerie},
 * {@code une insulte}, {@code de la violence}) sont chacune en position de bonne reponse de la tache
 * de reconnaissance, aux cotes des deux items de contraste qui montrent un comportement permis
 * ({@code un service}, {@code une discussion}).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1} et {@code EMC-8} demandent une lecture
 * semantique de chaque situation, pas une forme reconnaissable dans le texte : non gardees ici.
 * {@code EMC-5} (chaque distracteur nettement hors sujet, aucune reponse doublement defendable) et
 * {@code EMC-10} (aucune personne reelle, aucun parti, aucun syndicat nomme) restent la garde de
 * {@code nadia-prof} seule. Ce paquet ne sert pas la ligne 3 du referentiel (situation d'exclusion) :
 * aucune condition sur ce point n'est encodee, conformement au commentaire du paquet.</p>
 */
class PaquetCpEmcPermisInterditTest {

    private static final String PAQUET = "cp-emc-permis-interdit";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /**
     * FER-1 : bonne reponse des items de la tache de reconnaissance - les trois qualifications de
     * l'interdit et les deux items de contraste qui montrent un comportement permis.
     */
    private static final Set<String> REPONSES_DE_QUALIFICATION = Set.of(
            "une moquerie", "une insulte", "de la violence", "un service", "une discussion");

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
    private static boolean estDeTacheProtege(Question item) {
        return item.enonce().startsWith("Pourquoi");
    }

    private static List<Question> itemsDeQualification() {
        return items().stream().filter(item -> !estDeTacheProtege(item)).toList();
    }

    private static List<Question> itemsDeProtege() {
        return items().stream().filter(PaquetCpEmcPermisInterditTest::estDeTacheProtege).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis la reprise du 2026-09-06). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les trois qualifications de l'interdit et les deux items de contraste sont tous
     * representes en bonne reponse de la tache de reconnaissance, et rien d'autre.
     */
    @Test
    void fer1LesTroisQualificationsEtLesDeuxContrastesSontEnBonneReponse() {
        Set<String> reponses = itemsDeQualification().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois qualifications de l'interdit et les deux contrastes de"
                        + " permis doivent tous etre representes")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_QUALIFICATION);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int qualification = itemsDeQualification().size();
        int protege = itemsDeProtege().size();

        assertThat(qualification).as("la tache de qualification est servie").isGreaterThan(0);
        assertThat(protege).as("la tache de protection est servie").isGreaterThan(0);
        assertThat(Math.min(qualification, protege))
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
