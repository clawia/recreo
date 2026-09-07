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
 * <b>S30-2, le paquet {@code cm1-sciences-objet-technique}, ligne {@code SCI-CM1-12} de "Sciences
 * et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-s30-ce-qui-
 * ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md}, section 2.8. Encode le plancher
 * de dix items (FER-2), les trois elements du libelle en bonne reponse (FER-1) et les deux taches
 * distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de la bonne reponse : la
 * <b>fonction</b> ("À quoi sert... ?" -&gt; une phrase d'usage) et le <b>composant</b> ("Quelle
 * pièce... ?" -&gt; le nom d'une pièce). Un onzième item, dédié au fonctionnement d'ensemble, sert
 * le troisième élément du libelle sans former une troisième tâche (deux suffisent, chacune
 * au-dessus du plancher de trois items).</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque fonction, composant ou
 * mécanisme reste la garde de {@code nadia-prof}.</p>
 */
class PaquetCm1SciencesObjetTechniqueTest {

    private static final String PAQUET = "cm1-sciences-objet-technique";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Bonne reponse des items qui servent l'element "la fonction" du libelle. */
    private static final Set<String> REPONSES_DE_FONCTION = Set.of(
            "à se déplacer plus vite et avec moins d'effort", "à éclairer dans le noir",
            "à tailler la pointe d'un crayon", "à brasser l'air d'une salle, sans jamais le refroidir");

    /** Bonne reponse des items qui servent l'element "un composant" du libelle. */
    private static final Set<String> REPONSES_DE_COMPOSANT = Set.of(
            "la chaîne", "le guidon", "la pile", "l'interrupteur", "la lame", "les pales");

    /** Bonne reponse de l'unique item qui sert l'element "le fonctionnement" du libelle. */
    private static final String REPONSE_DE_FONCTIONNEMENT =
            "la pile envoie du courant à l'ampoule, qui s'allume";

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

    private static List<Question> itemsDeFonction() {
        return items().stream()
                .filter(item -> REPONSES_DE_FONCTION.contains(item.bonneReponse())).toList();
    }

    private static List<Question> itemsDeComposant() {
        return items().stream()
                .filter(item -> REPONSES_DE_COMPOSANT.contains(item.bonneReponse())).toList();
    }

    private static Question itemDontLaBonneReponseEst(String bonneReponse) {
        return items().stream().filter(item -> item.bonneReponse().equals(bonneReponse)).findFirst()
                .orElseThrow(() -> new AssertionError("aucun item n'a pour bonne reponse : " + bonneReponse));
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S30-2). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1, element "la fonction" : les quatre fonctions du paquet sont en bonne reponse. */
    @Test
    void fer1LElementFonctionEstRepresenteParSesQuatreValeurs() {
        Set<String> reponses = itemsDeFonction().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les quatre fonctions doivent être en bonne réponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_FONCTION);
    }

    /** FER-1, element "un composant" : les six composants du paquet sont en bonne reponse. */
    @Test
    void fer1LElementComposantEstRepresenteParSesSixValeurs() {
        Set<String> reponses = itemsDeComposant().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les six composants doivent être en bonne réponse")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_COMPOSANT);
    }

    /** FER-1, element "le fonctionnement" : un item decrit le mecanisme entier. */
    @Test
    void fer1LElementFonctionnementEstServiParUnItemDedie() {
        List<Question> items = items().stream()
                .filter(item -> item.bonneReponse().equals(REPONSE_DE_FONCTIONNEMENT)).toList();

        assertThat(items)
                .as("un item au moins decrit le fonctionnement d'ensemble d'un objet")
                .hasSize(1);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int fonction = itemsDeFonction().size();
        int composant = itemsDeComposant().size();

        assertThat(fonction).as("la tache de fonction est servie").isGreaterThan(0);
        assertThat(composant).as("la tache de composant est servie").isGreaterThan(0);
        assertThat(Math.min(fonction, composant))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (fonction, composant, fonctionnement additionnes). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }

    /**
     * Reprise S31-6 ({@code OBT-R1}, nadia-prof, 2026-09-06,
     * {@code squad/conformite/FICHE-bandit-manchot-objet-technique-cm1.md}) : l'indice de l'item
     * du vélo affirmait "pour une fatigue proche", ce qui dement sa propre bonne reponse ("avec
     * moins d'effort") en comparant a effort egal la ou l'enonce et la bonne reponse comparent a
     * distance egale.
     */
    @Test
    void obtR1LIndiceDeLItemDuVeloNeDementPlusSaBonneReponse() {
        Question item = itemDontLaBonneReponseEst("à se déplacer plus vite et avec moins d'effort");
        assertThat(item.indice().orElseThrow())
                .as("OBT-R1 : l'indice ne compare plus a effort egal, ce qui dementait la bonne reponse")
                .doesNotContainIgnoringCase("fatigue proche");
    }

    /**
     * Reprise S31-6 ({@code OBT-R2}) : les indices de la lampe de poche et du taille-crayon
     * nommaient les deux distracteurs de leur item pour les ecarter, revelant la bonne reponse des
     * la premiere erreur.
     */
    @Test
    void obtR2LesIndicesDeLaLampeEtDuTailleCrayonNEcartentPlusLesDeuxDistracteursALaFois() {
        Question itemLampe = itemDontLaBonneReponseEst("à éclairer dans le noir");
        assertThat(itemLampe.indice().orElseThrow())
                .as("OBT-R2 : l'indice de la lampe ne nomme plus le distracteur 'recharger'")
                .doesNotContainIgnoringCase("recharger");

        Question itemTailleCrayon = itemDontLaBonneReponseEst("à tailler la pointe d'un crayon");
        assertThat(itemTailleCrayon.indice().orElseThrow())
                .as("OBT-R2 : l'indice du taille-crayon ne nomme plus le distracteur 'ranger'")
                .doesNotContainIgnoringCase("ranger");
    }
}
