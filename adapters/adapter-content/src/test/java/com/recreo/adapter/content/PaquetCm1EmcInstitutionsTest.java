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
 * <b>S46-EMC-INSTITUTIONS-CM1, le paquet {@code cm1-emc-institutions}, ligne {@code EMC-CM1-09} de
 * l'enseignement moral et civique (CM1).</b> Referentiel : {@code squad/programme/enseignement-
 * moral-et-civique/competences-cm1.md}. Arbitrage prealable cite par la story (nadia-prof,
 * 2026-09-03, precise le 2026-10-01 en reponse a la question Q2 de theo-archi,
 * {@code squad/rapports/avis-faisabilite-s46-cinq-lots.md}, section 5).
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : tache A, reconnaitre a qui s'adresser selon une
 * situation decrite - bonne reponse strictement l'un des trois noms d'institution du libelle
 * (items 1 a 6) ; tache B, dire ce que fait une institution nommee - bonne reponse une fonction,
 * jamais un des trois noms d'institution (items 7 a 12).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-6} (aucun item ne suppose la commune, l'ecole ou
 * le foyer de l'enfant) et {@code EMC-5} (aucun item n'a deux reponses defendables) demandent une
 * lecture semantique de chaque situation, non gardee ici - c'est la garde de {@code nadia-prof}.
 * {@code EMC-7} ne mord sur aucun item : ce paquet n'ecrit aucun numero d'urgence, verifie ici par
 * construction plutot que suppose.</p>
 */
class PaquetCm1EmcInstitutionsTest {

    private static final String PAQUET = "cm1-emc-institutions";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les trois institutions du libelle que la tache A doit chacune servir en bonne reponse. */
    private static final Set<String> INSTITUTIONS_DU_LIBELLE =
            Set.of("la mairie", "le conseil municipal", "l'école");

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

    /** Tache A : la bonne reponse est strictement l'un des trois noms d'institution du libelle. */
    private static boolean estDeTacheAQuiSAdresser(Question item) {
        return INSTITUTIONS_DU_LIBELLE.contains(item.bonneReponse());
    }

    private static List<Question> itemsAQuiSAdresser() {
        return items().stream().filter(PaquetCm1EmcInstitutionsTest::estDeTacheAQuiSAdresser).toList();
    }

    private static List<Question> itemsQueFait() {
        return items().stream().filter(item -> !estDeTacheAQuiSAdresser(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (douze a la livraison). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les trois institutions du libelle (mairie, conseil municipal, ecole) sont chacune en
     * bonne reponse au moins une fois, sur la tache A.
     */
    @Test
    void fer1LesTroisInstitutionsSontChacuneEnBonneReponse() {
        assertThat(itemsAQuiSAdresser().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet()))
                .as("FER-1 : les trois institutions du libelle sont chacune servies en position de"
                        + " bonne reponse")
                .containsExactlyInAnyOrderElementsOf(INSTITUTIONS_DU_LIBELLE);
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
        assertThat(queFait).as("la tache 'que fait cette institution' est servie").isGreaterThan(0);
        assertThat(Math.min(aQuiSAdresser, queFait))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * La tache "que fait cette institution" ne reclasse jamais une situation en l'un des trois noms
     * d'institution du libelle : sa bonne reponse decrit toujours une fonction, jamais un nom.
     */
    @Test
    void laTacheQueFaitNeReditJamaisUneInstitutionEnBonneReponse() {
        for (Question item : itemsQueFait()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la tache 'que fait cette institution' decrit une fonction, jamais"
                            + " l'un des trois noms d'institution du libelle", item.id().value())
                    .isNotIn(INSTITUTIONS_DU_LIBELLE);
        }
    }

    /**
     * EMC-7, verifie par construction : aucun numero d'urgence n'apparait dans l'enonce, la bonne
     * reponse, un distracteur ni l'indice d'aucun item.
     */
    @Test
    void emc7AucunNumeroDUrgenceNApparaitNullePart() {
        for (Question item : items()) {
            assertThat(item.enonce()).as("item %s : enonce sans numero d'urgence", item.id().value())
                    .doesNotContainPattern("\\d");
            assertThat(item.bonneReponse())
                    .as("item %s : bonne reponse sans numero d'urgence", item.id().value())
                    .doesNotContainPattern("\\d");
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur)
                        .as("item %s : distracteur sans numero d'urgence", item.id().value())
                        .doesNotContainPattern("\\d");
            }
            item.indice().ifPresent(indice -> assertThat(indice)
                    .as("item %s : indice sans numero d'urgence", item.id().value())
                    .doesNotContainPattern("\\d"));
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

    /** Ligne de referentiel : EMC-CM1-09 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementEmcCm109() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("institutions");
    }
}
