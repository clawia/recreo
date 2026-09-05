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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S29-1, le paquet {@code cp-qlm-cycle-de-vie}, ligne {@code QLM-CP-02} de "Questionner le
 * monde" (CP).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-
 * quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md}, section 4.1. Encode le plafond de vingt
 * items, les cinq caracteres du libelle en bonne reponse (FER-1) et les deux taches distinctes
 * (FER-3).
 *
 * <p><b>Deux taches distinctes (FER-3)</b>, distinguees par la forme de la bonne reponse : la
 * <b>reconnaissance</b> d'un caractere decrit dans une situation ("Un poussin sort de l'oeuf, que
 * fait-il ?" -&gt; "il nait") et la <b>possession</b>, qui demande si un etre donne possede ce
 * caractere ("Un caillou peut-il naitre ?" -&gt; "non").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque situation reste la garde
 * de {@code nadia-prof}, et aucune condition de biais ({@code BIA-*}) ne s'applique a cette ligne.
 * </p>
 */
class PaquetCpQlmCycleDeVieTest {

    private static final String PAQUET = "cp-qlm-cycle-de-vie";

    private static final int PLAFOND_ITEMS = 20;

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /**
     * Bonne reponse des items de la tache de reconnaissance, associee au caractere du libelle
     * qu'elle sert. Cinq caracteres, cinq reponses distinctes.
     */
    private static final Map<String, String> CARACTERE_PAR_REPONSE = Map.of(
            "il naît", "naitre",
            "il se nourrit", "se nourrir",
            "il grandit", "grandir",
            "elle a des petits", "avoir des petits",
            "elle meurt", "mourir");

    /** Bonne reponse des items de la tache de possession : un simple oui ou non. */
    private static final Set<String> REPONSES_DE_POSSESSION = Set.of("oui", "non");

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

    private static boolean estDeReconnaissance(Question item) {
        return CARACTERE_PAR_REPONSE.containsKey(item.bonneReponse());
    }

    private static List<Question> itemsDeReconnaissance() {
        return items().stream().filter(PaquetCpQlmCycleDeVieTest::estDeReconnaissance).toList();
    }

    private static List<Question> itemsDePossession() {
        return items().stream().filter(item -> !estDeReconnaissance(item)).toList();
    }

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S29-1). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les cinq caracteres du libelle de competence (il nait, se nourrit, grandit, a des
     * petits, meurt) sont tous representes en bonne reponse.
     */
    @Test
    void fer1LesCinqCaracteresSontTousRepresentesEnBonneReponse() {
        List<Question> items = itemsDeReconnaissance();
        assertThat(items).isNotEmpty();

        Set<String> caracteresCouverts = items.stream()
                .map(item -> CARACTERE_PAR_REPONSE.get(item.bonneReponse()))
                .collect(Collectors.toSet());

        assertThat(caracteresCouverts)
                .as("FER-1 : les cinq caracteres du libelle doivent tous etre representes")
                .containsExactlyInAnyOrder("naitre", "se nourrir", "grandir", "avoir des petits",
                        "mourir");
    }

    /**
     * Les items de possession ne repondent que par oui ou par non, jamais par un caractere : leur
     * bonne reponse et leur distracteur nomment une reponse binaire, jamais une des cinq phrases de
     * la tache de reconnaissance.
     */
    @Test
    void lesItemsDePossessionRepondentUniquementParOuiOuNon() {
        List<Question> items = itemsDePossession();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(REPONSES_DE_POSSESSION)
                    .as("item %s : une possession repond oui ou non", item.id().value())
                    .contains(item.bonneReponse());
            assertThat(REPONSES_DE_POSSESSION)
                    .as("item %s : le distracteur d'une possession est oui ou non",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /** Chaque item propose au moins un distracteur et au plus deux (`AuPlusTroisPropositionsParItemTest`). */
    @Test
    void chaqueItemAAuMoinsUnEtAuPlusDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs().size())
                    .as("item %s : entre un et deux distracteurs attendus", item.id().value())
                    .isBetween(1, 2);
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int reconnaissance = itemsDeReconnaissance().size();
        int possession = itemsDePossession().size();

        assertThat(reconnaissance).as("la tache de reconnaissance est servie").isGreaterThan(0);
        assertThat(possession).as("la tache de possession est servie").isGreaterThan(0);
        assertThat(Math.min(reconnaissance, possession))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
