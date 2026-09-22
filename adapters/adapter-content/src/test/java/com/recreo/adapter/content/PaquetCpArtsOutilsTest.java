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
 * <b>S39-OUTILS, le paquet {@code cp-arts-outils}, ligne {@code ART-CP-11} de "Arts plastiques"
 * (CP), premiere ligne de cette matiere.</b> Referentiel :
 * {@code squad/programme/arts-plastiques/competences-cp.md}, ligne 11, jugement de faisabilite du
 * 2026-09-03. Douze items, repartis en trois familles fermees de quatre termes (outils, supports,
 * gestes), les quatre termes de la parenthese du referentiel (pinceau, craie, argile, esquisse)
 * chacun servis en bonne reponse.
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude et la verifiabilite de chaque terme et de
 * chaque distracteur - relecture de {@code nadia-prof} au Done pedagogique.</p>
 */
class PaquetCpArtsOutilsTest {

    private static final String PAQUET = "cp-arts-outils";

    /** ADR-0023 : le paquet offre strictement plus d'items que la partie n'a de manches (10). */
    private static final int PLANCHER_D_ITEMS = 11;

    /** Les quatre termes de la parenthese du referentiel, chacun attendu en bonne reponse. */
    private static final Set<String> TERMES_DE_LA_PARENTHESE =
            Set.of("le pinceau", "la craie", "l'argile", "l'esquisse");

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

    /** ADR-0023 : au moins onze items pour dix manches. */
    @Test
    void offrePlusDItemsQueDeManches() {
        assertThat(items().size())
                .as("ADR-0023 : strictement plus d'items que de manches")
                .isGreaterThanOrEqualTo(PLANCHER_D_ITEMS);
    }

    /** Les quatre termes explicitement cites par le referentiel sont chacun en bonne reponse. */
    @Test
    void lesQuatreTermesDeLaParentheseSontChacunEnBonneReponse() {
        Set<String> reponses = items().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses).containsAll(TERMES_DE_LA_PARENTHESE);
    }

    /** ART-1 : aucune reponse ni aucun distracteur ne nomme une couleur perçue. */
    @Test
    void art1AucuneReponseNiDistracteurNeNommeUneCouleur() {
        Set<String> couleurs = Set.of("rouge", "bleu", "bleue", "jaune", "vert", "verte", "noir",
                "noire", "blanc", "blanche", "orange", "violet", "violette", "rose", "marron",
                "gris", "grise");
        for (Question item : items()) {
            List<String> propositions = new ArrayList<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            for (String proposition : propositions) {
                for (String couleur : couleurs) {
                    assertThat(proposition.toLowerCase(java.util.Locale.FRENCH))
                            .as("item %s : ART-1, aucune proposition ne nomme une couleur",
                                    item.id().value())
                            .doesNotContain(couleur);
                }
            }
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

    /** Chaque item porte un indice. */
    @Test
    void chaqueItemPorteUnIndice() {
        for (Question item : items()) {
            assertThat(item.indice())
                    .as("item %s : indice present et non vide", item.id().value())
                    .isPresent();
            assertThat(item.indice().orElseThrow()).isNotBlank();
        }
    }
}
