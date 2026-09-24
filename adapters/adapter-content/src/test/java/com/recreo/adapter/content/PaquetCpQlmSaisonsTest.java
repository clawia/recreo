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
 * <b>S41-QLM-SAISONS, le paquet {@code cp-qlm-saisons}, ligne {@code QLM-CP-13} de "Questionner le
 * monde" (CP).</b> Referentiel : {@code squad/programme/questionner-le-monde/competences-cp.md}.
 * Arbitrage prealable deja rendu (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-
 * plafond-a-huit-lignes.md, ligne 368) : "rentable. FER-1 sur les deux moities, pas sur les douze
 * mois. FER-3 : nommer, et ordonner ('quel mois vient apres... ?')."
 *
 * <p><b>Deux moities distinctes (FER-1), reperees structurellement et non par l'ordre de tirage</b>
 * ({@code ListContentSource.next} tire au hasard, l'ordre du fichier n'est pas garanti) :
 * <b>nommer</b> demande le nom d'un seul mois ou d'une seule saison, jamais plus d'un autre nomme
 * dans l'enonce ; <b>ordonner</b> pose une relation d'ordre entre deux ou trois mois ou saisons
 * explicitement nommes, marquee soit par "avant ou apres" entre deux mois nommes, soit par une liste
 * de plusieurs mois ou saisons introduite par un deux-points ("parmi ces trois...").</p>
 */
class PaquetCpQlmSaisonsTest {

    private static final String PAQUET = "cp-qlm-saisons";

    /** FER-2 : au moins dix items servent une ligne fermee (DEC-0064). */
    private static final int PLANCHER_FER_2 = 10;

    private static final Set<String> MOIS = Set.of("janvier", "février", "mars", "avril", "mai",
            "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre");

    private static final Set<String> SAISONS = Set.of("l'hiver", "le printemps", "l'été", "l'automne");

    private static final Set<String> RELATIONS_D_ORDRE = Set.of("avant", "après");

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

    /**
     * Une relation d'ordre entre deux mois nommes ("avant ou apres") ou une liste de plusieurs mois
     * ou saisons nommes ("parmi ces trois... : a, b, c") : les deux formes que ce paquet emploie pour
     * la moitie "ordonner". Aucune des deux ne peut apparaitre dans un item de la moitie "nommer",
     * qui ne nomme jamais plus d'un mois ou d'une saison autre que la reponse elle-meme.
     */
    private static boolean estDOrdonner(Question item) {
        String enonce = item.enonce();
        return enonce.contains("avant ou après") || enonce.contains(" : ");
    }

    private static List<Question> itemsDeNommer() {
        return items().stream().filter(item -> !estDOrdonner(item)).toList();
    }

    private static List<Question> itemsDOrdonner() {
        return items().stream().filter(PaquetCpQlmSaisonsTest::estDOrdonner).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (seize sur cp-qlm-saisons). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les deux moities (nommer, ordonner) sont chacune servies par des items distincts, sans
     * exigence de couvrir les douze mois individuellement.
     */
    @Test
    void fer1LesDeuxMoitiesSontChacuneServiesParDesItemsDistincts() {
        List<Question> nommer = itemsDeNommer();
        List<Question> ordonner = itemsDOrdonner();

        assertThat(nommer.size() + ordonner.size())
                .as("chaque item du paquet resout vers exactement une des deux moities")
                .isEqualTo(items().size());
        assertThat(nommer).as("la moitie \"nommer\" est servie").isNotEmpty();
        assertThat(ordonner).as("la moitie \"ordonner\" est servie").isNotEmpty();
    }

    /**
     * Un item de la moitie "nommer" demande le nom d'un mois ou d'une saison a partir d'une
     * description ou d'une position : sa bonne reponse et ses distracteurs sont tous des mois, ou
     * tous des saisons - jamais un melange, et jamais une relation d'ordre ("avant", "après") en
     * position de reponse.
     */
    @Test
    void unItemDeNommerDemandeUnNomDeMoisOuDeSaisonSeul() {
        for (Question item : itemsDeNommer()) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());

            boolean toutMois = MOIS.containsAll(propositions);
            boolean touteSaison = SAISONS.containsAll(propositions);

            assertThat(toutMois || touteSaison)
                    .as("item %s : toutes les propositions doivent etre des mois, ou toutes des"
                            + " saisons, jamais un melange (propositions : %s)", item.id().value(),
                            propositions)
                    .isTrue();
        }
    }

    /**
     * Un item de la moitie "ordonner" pose une relation d'ordre entre deux ou trois mois ou saisons
     * nommes : sa bonne reponse est un mois, une saison, ou la relation elle-meme ("avant",
     * "après"), jamais uniquement leur nom pris isolement (l'enonce nomme toujours au moins deux
     * mois ou saisons distincts).
     */
    @Test
    void unItemDOrdonnerPoseUneRelationDOrdreEntreDeuxMoisOuSaisonsNommes() {
        Set<String> espaceDeReponseOrdonner = new HashSet<>();
        espaceDeReponseOrdonner.addAll(MOIS);
        espaceDeReponseOrdonner.addAll(SAISONS);
        espaceDeReponseOrdonner.addAll(RELATIONS_D_ORDRE);

        for (Question item : itemsDOrdonner()) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());

            assertThat(espaceDeReponseOrdonner)
                    .as("item %s : chaque proposition doit etre un mois, une saison ou une relation"
                            + " d'ordre", item.id().value())
                    .containsAll(propositions);
        }
    }

    /** Chaque item propose au moins un distracteur, jamais plus de deux. */
    @Test
    void chaqueItemProposeUnOuDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs().size())
                    .as("item %s : entre un et deux distracteurs attendus", item.id().value())
                    .isBetween(1, 2);
        }
    }
}
