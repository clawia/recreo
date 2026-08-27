package com.recreo.adapter.content;

import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S21-11 (`squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, section 5,
 * conditions `GRA-1` a `GRA-10`) : le paquet "cp-comparer". Sur les 10 items de
 * `contenu-repris/quiz/cp-comparer.yml`, nadia-prof en retient 6 (longueur x4, masse x2), ecarte
 * 3 items hors sujet (deux comparaisons de nombres, une comparaison de vitesse) et juge le
 * dixieme (crayon contre trousse) indecidable ; la contenance est absente du fichier repris.
 *
 * <p><b>Ce que ce fichier verifie mecaniquement, et ce qu'il ne verifie pas.</b> Les conditions
 * comptables - nombre d'items (`GRA-9`), repartition par grandeur (`GRA-2`), au moins un tiers
 * dans le sens du plus petit/leger/court (`GRA-4`), aucun triplet d'objets repete (`GRA-5`),
 * exactement trois propositions par item (`GRA-8`), aucun enonce hors des trois familles de
 * grandeur (`GRA-1`) et aucune formulation d'enonce partagee entre deux grandeurs (`GRA-6`) - se
 * derivent d'un simple comptage sur le contenu servi et sont testees ici. La decidabilite sans
 * instrument (`GRA-3`), le vocabulaire de CP pour la contenance (`GRA-7`) et la qualite de
 * l'indice (`GRA-10`) demandent de juger un objet du monde reel, pas de compter une chaine :
 * nadia-prof le dit elle-meme (section 7 de l'arbitrage) et en reste la seule garde, exactement
 * comme pour `NAT-1` sur "cp-nature-mot" et `REP-3` sur "cm1-reperes".</p>
 */
class PaquetCpComparerTest {

    private static final String PAQUET = "cp-comparer";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 18;

    /**
     * `GRA-1` et `GRA-6` : chaque enonce autorise est associe a une seule grandeur, jamais deux.
     * Un enonce absent de cette carte fait echouer {@link #aucunEnonceHorsDesTroisGrandeursAutorisees()}.
     */
    private static final Map<String, String> GRANDEUR_PAR_ENONCE = Map.ofEntries(
            Map.entry("Qu'est-ce qui est le plus long ?", "longueur"),
            Map.entry("Qu'est-ce qui est le plus court ?", "longueur"),
            Map.entry("Qu'est-ce qui est le plus grand ?", "longueur"),
            Map.entry("Qu'est-ce qui est le plus petit ?", "longueur"),
            Map.entry("Qu'est-ce qui est le plus haut ?", "longueur"),
            Map.entry("Qu'est-ce qui est le plus bas ?", "longueur"),
            Map.entry("Qu'est-ce qui est le plus lourd ?", "masse"),
            Map.entry("Qu'est-ce qui est le plus léger ?", "masse"),
            Map.entry("Où peut-on mettre le plus d'eau ?", "contenance"),
            Map.entry("Où peut-on mettre le moins d'eau ?", "contenance"));

    /** `GRA-4` : ces enonces demandent le plus petit/leger/court ou le moins. */
    private static final Set<String> ENONCES_SENS_DU_MOINS = Set.of(
            "Qu'est-ce qui est le plus court ?",
            "Qu'est-ce qui est le plus petit ?",
            "Qu'est-ce qui est le plus bas ?",
            "Qu'est-ce qui est le plus léger ?",
            "Où peut-on mettre le moins d'eau ?");

    @Test
    void lePaquetCompteExactementDixHuitItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void chaqueItemProposeExactementTroisPropositionsDistinctes() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("GRA-8 : item \"%s\" doit porter exactement deux distracteurs", item.enonce())
                    .hasSize(2);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item \"%s\" : les trois propositions doivent etre distinctes", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void aucunEnonceHorsDesTroisGrandeursAutorisees() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(GRANDEUR_PAR_ENONCE)
                    .as("GRA-1 : \"%s\" doit comparer une longueur, une masse ou une contenance,"
                            + " jamais un nombre, une vitesse, une duree, un prix ou une quantite",
                            item.enonce())
                    .containsKey(item.enonce());
        }
    }

    @Test
    void lesTroisGrandeursSontRepresenteesAuMoinsAuQuartChacune() {
        Map<String, Long> comptesParGrandeur = new HashMap<>();
        for (Question item : tousLesItems(paquet())) {
            String grandeur = GRANDEUR_PAR_ENONCE.get(item.enonce());
            comptesParGrandeur.merge(grandeur, 1L, Long::sum);
        }

        assertThat(comptesParGrandeur.keySet())
                .as("GRA-2 : longueur, masse et contenance doivent toutes etre representees")
                .containsExactlyInAnyOrder("longueur", "masse", "contenance");
        long quart = NOMBRE_D_ITEMS_ATTENDU / 4;
        comptesParGrandeur.forEach((grandeur, compte) -> assertThat(compte)
                .as("GRA-2 : \"%s\" est representee sur %d items, en dessous du quart de %d",
                        grandeur, compte, NOMBRE_D_ITEMS_ATTENDU)
                .isGreaterThanOrEqualTo(quart));
    }

    @Test
    void auMoinsUnTiersDesItemsDemandentLePlusPetitLeLegerLeCourtOuLeMoins() {
        long compte = tousLesItems(paquet()).stream()
                .filter(item -> ENONCES_SENS_DU_MOINS.contains(item.enonce()))
                .count();

        assertThat(compte * 3)
                .as("GRA-4 : au moins un tiers des %d items doivent etre dans le sens du moins,"
                        + " %d seulement le sont", NOMBRE_D_ITEMS_ATTENDU, compte)
                .isGreaterThanOrEqualTo(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void aucunTripletDObjetsNEstRepeteDUnItemALAutre() {
        List<Question> items = tousLesItems(paquet());
        Set<Set<String>> tripletsVus = new HashSet<>();
        for (Question item : items) {
            Set<String> triplet = new HashSet<>(item.distracteurs());
            triplet.add(item.bonneReponse());
            assertThat(tripletsVus)
                    .as("GRA-5 : le triplet %s (item \"%s\") est deja porte par un autre item de ce paquet",
                            triplet, item.enonce())
                    .doesNotContain(triplet);
            tripletsVus.add(triplet);
        }
    }

    @Test
    void chaqueItemPorteUnIndiceNonVide() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.indice())
                    .as("item \"%s\" doit porter un indice", item.enonce())
                    .isPresent();
            assertThat(item.indice().orElseThrow())
                    .as("item \"%s\" : l'indice ne doit pas etre vide", item.enonce())
                    .isNotBlank();
        }
    }

    @Test
    void leDescripteurEstMathematiquesCpGrandeursEtMesures() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Mathématiques");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Grandeurs et mesures");
        assertThat(descripteur.competence())
                .isEqualTo("Comparer des longueurs, des masses, des contenances (sans instrument gradué)");
    }

    /** Enumere tous les items distincts du paquet en faisant grossir le tirage deja-vu jusqu'a epuisement. */
    private static List<Question> tousLesItems(ContentSource contenu) {
        RandomSource hasard = new Random(1)::nextInt;
        Set<ItemId> dejaVus = new HashSet<>();
        List<Question> items = new ArrayList<>();
        for (Item item = contenu.next(hasard, dejaVus).orElse(null); item != null;
             item = contenu.next(hasard, dejaVus).orElse(null)) {
            Question question = (Question) item;
            items.add(question);
            dejaVus.add(question.id());
        }
        return items;
    }

    private static ContentSource paquet() {
        return new YamlContentPackLoader().chargerPaquet(PAQUET);
    }
}
