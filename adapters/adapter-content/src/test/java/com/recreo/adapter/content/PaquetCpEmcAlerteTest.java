package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S47-EMC-ALERTE-CP, le paquet {@code cp-emc-alerte}, ligne {@code EMC-CP-14} de l'enseignement
 * moral et civique (CP).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cp.md}. Arbitrage prealable du 2026-10-02 sur la question Q4 de {@code theo-archi}
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s47-trois-questions-de-theo-archi.md}) : le 112
 * n'apparait nulle part dans ce paquet.
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : "service nomme" (bonne reponse un numero nu parmi
 * "15", "17", "18") ; "situation decrite" (bonne reponse un texte qui alerte un adulte).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> Que chaque situation n'admette vraiment qu'une seule reponse
 * defendable ({@code EMC-2}, {@code EMC-5}) et que l'enonce d'un item a numero nomme bien un service
 * et non une situation (regle 2 de l'arbitrage Q4) - lecture semantique reservee a {@code nadia-prof}
 * au Done pedagogique.</p>
 */
class PaquetCpEmcAlerteTest {

    private static final String PAQUET = "cp-emc-alerte";

    /** FER-2 : au moins dix items servent une ligne fermee (DEC-0064). */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Regle 1 de l'arbitrage Q4 : les trois numeros nus, et eux seuls, sur la tache a numero. */
    private static final Set<String> NUMEROS = Set.of("15", "17", "18");

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

    /** Tache "service nomme" : la bonne reponse est l'un des trois numeros nus. */
    private static boolean estDeTacheNumero(Question item) {
        return NUMEROS.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeNumero() {
        return items().stream().filter(PaquetCpEmcAlerteTest::estDeTacheNumero).toList();
    }

    private static List<Question> itemsDeSituation() {
        return items().stream().filter(item -> !estDeTacheNumero(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze). */
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
        int numero = itemsDeNumero().size();
        int situation = itemsDeSituation().size();

        assertThat(numero).as("la tache 'service nomme' est servie").isGreaterThan(0);
        assertThat(situation).as("la tache 'situation decrite' est servie").isGreaterThan(0);
        assertThat(numero + situation)
                .as("chaque item du paquet resout vers exactement une des deux taches")
                .isEqualTo(items().size());
        assertThat(Math.min(numero, situation))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** FER-1, element "numero d'urgence" : les trois numeros sont chacun en bonne reponse. */
    @Test
    void fer1LesTroisNumerosSontChacunEnBonneReponse() {
        assertThat(itemsDeNumero().stream().map(Question::bonneReponse).collect(Collectors.toSet()))
                .as("FER-1 : les trois numeros du geste d'alerte sont chacun servis en bonne reponse")
                .containsExactlyInAnyOrderElementsOf(NUMEROS);
    }

    /**
     * FER-1, element "prevenir un adulte" : au moins un item de la tache de situation porte
     * exactement la formulation du libelle en bonne reponse.
     */
    @Test
    void fer1AuMoinsUnItemDeSituationPortePrevenirUnAdulteEnBonneReponse() {
        assertThat(itemsDeSituation().stream().map(Question::bonneReponse))
                .as("FER-1 : l'element 'prevenir un adulte' est servi par au moins un item")
                .contains("prévenir un adulte");
    }

    /**
     * Regle 1 de l'arbitrage Q4 : un item a numero porte exactement les trois propositions nues
     * "15", "17", "18" - jamais une quatrieme valeur, jamais une proposition habillee ("le 18").
     */
    @Test
    void regle1ChaqueItemDeNumeroPortExactementLesTroisNumerosNus() {
        for (Question item : itemsDeNumero()) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item %s : exactement les trois numeros nus", item.id().value())
                    .isEqualTo(NUMEROS);
        }
    }

    /** Le 112 n'apparait nulle part : arbitrage Q4, decision du 2026-10-02. */
    @Test
    void le112NApparaitNullePart() {
        for (Question item : items()) {
            assertThat(item.enonce()).as("item %s : enonce sans 112", item.id().value())
                    .doesNotContain("112");
            assertThat(item.bonneReponse())
                    .as("item %s : bonne reponse sans 112", item.id().value())
                    .doesNotContain("112");
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur)
                        .as("item %s : distracteur sans 112", item.id().value())
                        .doesNotContain("112");
            }
            item.indice().ifPresent(indice -> assertThat(indice)
                    .as("item %s : indice sans 112", item.id().value())
                    .doesNotContain("112"));
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

    /**
     * EMC-7 : chaque item a numero porte un marqueur {@code # EMC-7:} citant sa source, lu dans le
     * fichier brut comme les marqueurs {@code EPS-1} ou {@code HIS-3}.
     */
    @Test
    void emc7ChaqueItemDeNumeroPorteUnMarqueurAvecSaSource() {
        List<String> lignesDItem = texteBrutDuPaquet().lines()
                .filter(ligne -> ligne.contains("- enonce:")).toList();

        assertThat(lignesDItem).as("le paquet porte bien onze lignes d'item").hasSize(11);

        long lignesMarquees = lignesDItem.stream().filter(ligne -> ligne.contains("# EMC-7:")).count();
        assertThat(lignesMarquees)
                .as("EMC-7 : les trois items a numero portent chacun un marqueur avec leur source")
                .isEqualTo(3);
    }

    /** Le texte brut du paquet, tel qu'ecrit sur le classpath - commentaires compris. */
    private static String texteBrutDuPaquet() {
        try (InputStream flux = PaquetCpEmcAlerteTest.class
                .getResourceAsStream("/contenu/" + PAQUET + ".yaml")) {
            if (flux == null) {
                throw new IllegalStateException("contenu/" + PAQUET + ".yaml introuvable sur le"
                        + " classpath");
            }
            return new String(flux.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
