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
 * <b>S39-ENERGIE, le paquet {@code cm1-sciences-energie}, ligne {@code SCI-CM1-04} de "Sciences et
 * technologie" (CM1).</b> Referentiel : arbitrage prealable de {@code nadia-prof} du s30
 * ({@code ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-
 * reserves.md}, ligne 4 du tableau section 2.2), route sur ce lot par l'avis de faisabilite de s39
 * de {@code theo-archi} (section 5, "conditions pedagogiques opposables"). Encode le plancher de
 * douze items (FER-2), les trois elements du libelle en bonne reponse (FER-1 : source, forme,
 * conversion) et les deux taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de l'enonce : <b>source ou forme</b>
 * ("Quelle est la source d'énergie... ?" ou "Quelle forme d'énergie... ?" - six items, sert les
 * elements "source" et "forme" du libelle) et <b>transformation</b> ("Quelle transformation
 * d'énergie... réalise-t-il ?" - six items, sert l'element "conversion").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude scientifique de chaque conversion (SCI-3), la
 * plausibilite de chaque distracteur, et le refus 3 de nadia-prof (aucun item sur "l'électricité
 * est-elle une source ou une forme ?") - relecture de {@code nadia-prof} au Done pedagogique.</p>
 */
class PaquetCm1SciencesEnergieTest {

    private static final String PAQUET = "cm1-sciences-energie";

    /** FER-2 : au moins dix items servent une ligne fermee ; ce paquet en porte douze. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1, element "source" : les quatre sources reelles servies en bonne reponse. */
    private static final Set<String> SOURCES = Set.of("le vent", "le soleil", "l'eau", "le charbon");

    /** FER-1, element "forme" : les deux formes reelles servies en bonne reponse (hors conversion). */
    private static final Set<String> FORMES = Set.of("de la chaleur", "de la lumière");

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

    private static boolean estUneTransformation(Question item) {
        return item.bonneReponse().contains(" en énergie ");
    }

    private static List<Question> itemsDeSourceOuForme() {
        return items().stream().filter(item -> !estUneTransformation(item)).toList();
    }

    private static List<Question> itemsDeTransformation() {
        return items().stream().filter(PaquetCm1SciencesEnergieTest::estUneTransformation).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (douze depuis S39-ENERGIE). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, element "source" : les quatre sources sont chacune bonne reponse d'au moins un item. */
    @Test
    void fer1LElementSourceEstServiParChaqueSourceReelle() {
        Set<String> reponses = itemsDeSourceOuForme().stream().map(Question::bonneReponse)
                .filter(SOURCES::contains)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1, element source : chacune des quatre sources doit être en bonne réponse")
                .isEqualTo(SOURCES);
    }

    /** FER-1, element "forme" : les deux formes sont chacune bonne reponse d'au moins un item. */
    @Test
    void fer1LElementFormeEstServiParChaqueFormeReelle() {
        Set<String> reponses = itemsDeSourceOuForme().stream().map(Question::bonneReponse)
                .filter(FORMES::contains)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1, element forme : chacune des deux formes doit être en bonne réponse")
                .isEqualTo(FORMES);
    }

    /**
     * FER-1, element "conversion" : au moins trois transformations distinctes servent cet element,
     * chacune nommant a la fois ce qui est transforme et la forme resultante.
     */
    @Test
    void fer1LElementConversionEstServiParPlusieursTransformationsDistinctes() {
        Set<String> reponses = itemsDeTransformation().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1, element conversion : plusieurs transformations distinctes en bonne réponse")
                .hasSizeGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Refus 3 de nadia-prof (arbitrage de s30, section 6) : aucun item ne demande si l'électricité
     * est une source ou une forme d'énergie - la distinction n'est pas tranchable au cycle 3.
     * L'électricité n'apparaît que comme résultat ou origine d'une transformation.
     */
    @Test
    void aucunItemNeDemandeSiLElectriciteEstUneSourceOuUneForme() {
        for (Question item : itemsDeSourceOuForme()) {
            assertThat(item.bonneReponse())
                    .as("item %s : le refus 3 de nadia-prof interdit toute bonne réponse"
                            + " \"électricité\" a une question source-ou-forme", item.id().value())
                    .doesNotContain("électri");
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
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int sourceOuForme = itemsDeSourceOuForme().size();
        int transformation = itemsDeTransformation().size();

        assertThat(sourceOuForme).as("la tache source-ou-forme est servie").isGreaterThan(0);
        assertThat(transformation).as("la tache transformation est servie").isGreaterThan(0);
        assertThat(Math.min(sourceOuForme, transformation))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
