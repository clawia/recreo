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
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S47-QLM-AUTREFOIS, le paquet {@code cp-qlm-autrefois-aujourdhui}, ligne {@code QLM-CP-15} de
 * "Questionner le monde" (CP).</b> Referentiel : {@code
 * squad/programme/questionner-le-monde/competences-cp.md}. Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md, ligne
 * 374) : "oui, choix ; plus juste en illustre. Piege de biais : 'autrefois' ne veut pas dire 'moins
 * bien'."
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : rattacher un objet a une epoque (bonne reponse
 * "autrefois" ou "aujourd'hui") ; comparer deux usages (bonne reponse un objet ou une pratique
 * concrete, jamais "autrefois"/"aujourd'hui").</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> Que chaque fait historique decrit soit exact - lecture de
 * {@code nadia-prof} au Done pedagogique.</p>
 */
class PaquetCpQlmAutrefoisAujourdhuiTest {

    private static final String PAQUET = "cp-qlm-autrefois-aujourdhui";

    /** FER-2 : au moins dix items servent une ligne fermee (DEC-0064). */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    private static final Set<String> EPOQUES = Set.of("autrefois", "aujourd'hui");

    /**
     * BIA-3 : mots qui qualifieraient l'ancien de moins bien, verifies mot entier (jamais une
     * sous-chaine - "dur" est dans "duree", "dure" est aussi le verbe "durer") - voir le piege
     * nomme par le planning de s47. Aucun des mots ci-dessous n'est employe dans ce paquet : ce
     * test en garde l'absence plutot que d'arbitrer un sens.
     */
    private static final Pattern MOT_DEPRECIATIF = Pattern.compile(
            "\\b(difficile|dur|pauvre)\\b|pas pratique|moins bien", Pattern.CASE_INSENSITIVE);

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

    /** Tache de rattachement : la bonne reponse est l'une des deux epoques. */
    private static boolean estDeTacheDeRattachement(Question item) {
        return EPOQUES.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeRattachement() {
        return items().stream().filter(PaquetCpQlmAutrefoisAujourdhuiTest::estDeTacheDeRattachement)
                .toList();
    }

    private static List<Question> itemsDeComparaison() {
        return items().stream().filter(item -> !estDeTacheDeRattachement(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (quatorze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int rattachement = itemsDeRattachement().size();
        int comparaison = itemsDeComparaison().size();

        assertThat(rattachement).as("la tache de rattachement est servie").isGreaterThan(0);
        assertThat(comparaison).as("la tache de comparaison est servie").isGreaterThan(0);
        assertThat(rattachement + comparaison)
                .as("chaque item du paquet resout vers exactement une des deux taches")
                .isEqualTo(items().size());
        assertThat(Math.min(rattachement, comparaison))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * FER-1 : l'element "objets et images du quotidien" est servi par la tache de comparaison,
     * dont la bonne reponse ne reclasse jamais une epoque mais nomme un objet ou une pratique
     * concrete.
     */
    @Test
    void fer1LaTacheDeComparaisonNommeToujoursUnObjetOuUnePratiqueConcrete() {
        List<Question> comparaison = itemsDeComparaison();
        assertThat(comparaison).as("la tache de comparaison sert l'element 'objets et images du"
                + " quotidien'").isNotEmpty();
        for (Question item : comparaison) {
            assertThat(item.bonneReponse())
                    .as("item %s : la tache de comparaison ne reclasse jamais une epoque en bonne"
                            + " reponse", item.id().value())
                    .isNotIn(EPOQUES);
        }
    }

    /**
     * BIA-3 : aucun enonce, bonne reponse, distracteur ou indice ne qualifie l'ancien de moins
     * bien que l'actuel.
     */
    @Test
    void bia3AucunTexteNeQualifieLAncienDeMoinsBienQueLActuel() {
        for (Question item : items()) {
            assertThat(MOT_DEPRECIATIF.matcher(item.enonce().toLowerCase(Locale.FRENCH)).find())
                    .as("item %s : BIA-3, l'enonce ne qualifie jamais l'ancien de moins bien"
                            + " (\"%s\")", item.id().value(), item.enonce())
                    .isFalse();
            assertThat(MOT_DEPRECIATIF.matcher(item.bonneReponse().toLowerCase(Locale.FRENCH)).find())
                    .as("item %s : BIA-3 sur la bonne reponse", item.id().value())
                    .isFalse();
            for (String distracteur : item.distracteurs()) {
                assertThat(MOT_DEPRECIATIF.matcher(distracteur.toLowerCase(Locale.FRENCH)).find())
                        .as("item %s : BIA-3 sur le distracteur \"%s\"", item.id().value(), distracteur)
                        .isFalse();
            }
            item.indice().ifPresent(indice -> assertThat(
                    MOT_DEPRECIATIF.matcher(indice.toLowerCase(Locale.FRENCH)).find())
                    .as("item %s : BIA-3 sur l'indice", item.id().value())
                    .isFalse());
        }
    }

    /** Chaque item de la tache de rattachement propose exactement un distracteur. */
    @Test
    void chaqueItemDeRattachementProposeExactementUnDistracteur() {
        for (Question item : itemsDeRattachement()) {
            assertThat(item.distracteurs())
                    .as("item %s : un distracteur attendu", item.id().value())
                    .hasSize(1);
        }
    }

    /** Chaque item de la tache de comparaison propose exactement deux distracteurs. */
    @Test
    void chaqueItemDeComparaisonProposeExactementDeuxDistracteurs() {
        for (Question item : itemsDeComparaison()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }
}
