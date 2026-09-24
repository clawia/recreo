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
 * <b>S41-EMC-ROLES, le paquet {@code cp-emc-roles-ecole-commune}, ligne {@code EMC-CP-13} de
 * l'enseignement moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-
 * et-civique/competences-cp.md}. Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md,
 * ligne 237) : connaissance verifiable, deux taches - a qui s'adresse-t-on pour telle chose ; que
 * fait telle personne ou tel service.
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : tache A, reconnaitre a qui s'adresser selon une
 * situation decrite (items 1 a 6) ; tache B, dire ce que fait un role ou un service nomme (items 7 a
 * 11).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-6} (aucun item ne suppose la commune, l'ecole ou
 * le foyer de l'enfant) demande une lecture semantique, non gardee ici - c'est la garde de
 * {@code nadia-prof}.</p>
 */
class PaquetCpEmcRolesEcoleCommuneTest {

    private static final String PAQUET = "cp-emc-roles-ecole-commune";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les quatre roles et services que la tache A doit chacun servir en bonne reponse. */
    private static final Set<String> ROLES_ET_SERVICES_DU_LIBELLE = Set.of(
            "le directeur de l'école", "la mairie", "les pompiers", "le médecin");

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

    /** Tache A : un enonce qui demande a qui s'adresser selon une situation decrite. */
    private static boolean estDeTacheAQuiSAdresser(Question item) {
        return ROLES_ET_SERVICES_DU_LIBELLE.contains(item.bonneReponse());
    }

    private static List<Question> itemsAQuiSAdresser() {
        return items().stream().filter(PaquetCpEmcRolesEcoleCommuneTest::estDeTacheAQuiSAdresser)
                .toList();
    }

    private static List<Question> itemsQueFait() {
        return items().stream().filter(item -> !estDeTacheAQuiSAdresser(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les quatre roles et services du libelle (le directeur ou la directrice de l'ecole, la
     * mairie, les pompiers, le medecin) sont chacun en bonne reponse au moins une fois.
     */
    @Test
    void fer1LesQuatreRolesEtServicesSontChacunEnBonneReponse() {
        assertThat(itemsAQuiSAdresser().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet()))
                .as("FER-1 : les quatre roles et services du libelle sont chacun servis en position"
                        + " de bonne reponse")
                .containsExactlyInAnyOrderElementsOf(ROLES_ET_SERVICES_DU_LIBELLE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int aQuiSAdresser = itemsAQuiSAdresser().size();
        int queFait = itemsQueFait().size();

        assertThat(aQuiSAdresser).as("la tache 'a qui s'adresser' est servie").isGreaterThan(0);
        assertThat(queFait).as("la tache 'que fait ce role' est servie").isGreaterThan(0);
        assertThat(Math.min(aQuiSAdresser, queFait))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * La tache "que fait ce role" ne reclasse jamais une situation en un des quatre roles ou
     * services nommes : sa bonne reponse decrit toujours une fonction, jamais un role.
     */
    @Test
    void laTacheQueFaitNeReditJamaisUnRoleEnBonneReponse() {
        for (Question item : itemsQueFait()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la tache 'que fait ce role' decrit une fonction, jamais un des"
                            + " quatre roles ou services du libelle", item.id().value())
                    .isNotIn(ROLES_ET_SERVICES_DU_LIBELLE);
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

}
