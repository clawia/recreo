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
 * <b>S40-DEVISE, le paquet {@code cp-emc-devise}, ligne {@code EMC-CP-09} de l'enseignement moral et
 * civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}. Arbitrage prealable deja rendu
 * (ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md,
 * ligne 235) : la devise est une restitution exacte, la moitie exemple est ou {@code EMC-5} mord le
 * plus fort.
 *
 * <p><b>Deux taches</b> : un item unique de restitution exacte de la devise (item 1), et dix items
 * d'exemple concret repartis sur les trois valeurs (items 2 a 11).</p>
 *
 * <p><b>ADR-0086, clause 1</b> : le paquet ne porte volontairement qu'un seul item de restitution -
 * la bonne reponse "liberté, égalité, fraternité" compte trois mots, au seuil exact de la clause, qui
 * ne s'applique qu'a partir de trois items partageant la meme bonne reponse.</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : {@code EMC-5} (aucun exemple n'admet une seconde valeur
 * defendable) reste entierement la garde de {@code nadia-prof}, item par item.</p>
 */
class PaquetCpEmcDeviseTest {

    private static final String PAQUET = "cp-emc-devise";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    private static final String DEVISE_EXACTE = "liberté, égalité, fraternité";

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

    private static boolean estDeRestitution(Question item) {
        return item.bonneReponse().equals(DEVISE_EXACTE);
    }

    private static List<Question> itemsDeRestitution() {
        return items().stream().filter(PaquetCpEmcDeviseTest::estDeRestitution).toList();
    }

    private static List<Question> itemsDExemple() {
        return items().stream().filter(item -> !estDeRestitution(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S40-DEVISE). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * Un seul item de restitution, dont la bonne reponse est la devise exacte, sans variante ni
     * synonyme, et dont les deux distracteurs different de la devise exacte.
     */
    @Test
    void unSeulItemDeRestitutionPorteLaDeviseExacte() {
        List<Question> restitution = itemsDeRestitution();

        assertThat(restitution)
                .as("ADR-0086 clause 1 : un seul item de restitution, jamais trois ou plus partageant"
                        + " la meme bonne reponse de trois mots")
                .hasSize(1);
        for (String distracteur : restitution.get(0).distracteurs()) {
            assertThat(distracteur)
                    .as("un distracteur de restitution ne doit jamais valoir la devise exacte")
                    .isNotEqualTo(DEVISE_EXACTE);
        }
    }

    /**
     * FER-1 : les trois valeurs (liberte, egalite, fraternite) sont chacune illustrees par au moins
     * un exemple concret, reconnu par la valeur nommee dans l'enonce de l'item.
     */
    @Test
    void fer1LesTroisValeursSontChacuneIllustreesParUnExemple() {
        List<Question> exemples = itemsDExemple();
        assertThat(exemples).hasSize(10);

        assertThat(exemples).as("la liberté est illustrée par au moins un exemple")
                .anyMatch(item -> item.enonce().contains("liberté"));
        assertThat(exemples).as("l'égalité est illustrée par au moins un exemple")
                .anyMatch(item -> item.enonce().contains("égalité"));
        assertThat(exemples).as("la fraternité est illustrée par au moins un exemple")
                .anyMatch(item -> item.enonce().contains("fraternité"));
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

    /** Aucune bonne reponse ne se repete d'un item a l'autre. */
    @Test
    void aucuneBonneReponseNeSeRepeteDUnItemALAutre() {
        List<Question> items = items();
        Set<String> dejaVues = new HashSet<>();
        for (Question item : items) {
            assertThat(dejaVues.add(item.bonneReponse()))
                    .as("item %s : la reponse \"%s\" est deja la bonne reponse d'un autre item de"
                            + " ce paquet", item.id().value(), item.bonneReponse())
                    .isTrue();
        }
    }
}
