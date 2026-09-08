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
 * <b>S31-4, le paquet {@code cm1-sciences-familles-materiaux}, ligne {@code SCI-CM1-11} de
 * "Sciences et technologie" (CM1).</b> Referentiel : {@code squad/conformite/ARBITRAGE-PREALABLE-
 * s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md}, section 4.3. Encode
 * le plancher de dix items (FER-2) et les deux taches distinctes (FER-3).
 *
 * <p><b>Deux taches distinctes</b>, distinguees par la forme de l'enonce : la tache A
 * (<b>famille</b> d'un objet decrit, "de quelle famille de materiau...") et la tache B (<b>choix</b>
 * d'un materiau d'apres une propriete nommee, "Lequel choisir ?").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : l'exactitude factuelle de chaque famille et de chaque
 * propriete reste la garde de {@code nadia-prof}.</p>
 */
class PaquetCm1SciencesFamillesMateriauxTest {

    private static final String PAQUET = "cm1-sciences-familles-materiaux";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /**
     * FAB-1b : le nombre d'items de la tache de choix qu'un meme materiau peut porter. Deux, par
     * decision d'architecture prise sur la mesure du paquet servi (ADR-0086, section 7). Ce n'est pas
     * la barre de {@code nadia-prof}, qui ne chiffre rien : sa clause dit que la repetition est licite
     * <i>"des lors que la bonne reponse varie"</i>.
     */
    private static final int DOMINATION_MAXIMALE = 2;

    /** Fragment qui distingue la tache B (choix) de la tache A (famille) dans l'enonce. */
    private static final String FRAGMENT_TACHE_DE_CHOIX = "Lequel choisir";

    /** Les quatre proprietes du libelle, nommees comme criteres dans l'enonce de la tache B. */
    private static final List<String> ADJECTIFS_DE_PROPRIETE =
            List.of("résistant", "isolant", "souple", "imperméable");

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

    private static boolean estDeLaTacheDeChoix(Question item) {
        return item.enonce().contains(FRAGMENT_TACHE_DE_CHOIX);
    }

    private static List<Question> itemsDeFamille() {
        return items().stream().filter(item -> !estDeLaTacheDeChoix(item)).toList();
    }

    private static List<Question> itemsDeChoix() {
        return items().stream().filter(PaquetCm1SciencesFamillesMateriauxTest::estDeLaTacheDeChoix).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S31-4). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** Chaque item propose exactement deux distracteurs. */
    @Test
    void chaqueItemAExactementDeuxDistracteurs() {
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
        int famille = itemsDeFamille().size();
        int choix = itemsDeChoix().size();

        assertThat(famille).as("la tache de famille est servie").isGreaterThan(0);
        assertThat(choix).as("la tache de choix est servie").isGreaterThan(0);
        assertThat(Math.min(famille, choix))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /** Le paquet porte exactement onze items (famille, choix additionnes). */
    @Test
    void lePaquetPorteExactementOnzeItems() {
        assertThat(items()).hasSize(11);
    }

    /**
     * FER-1 : sur la tache de choix, les quatre proprietes du libelle (resistance, isolation,
     * souplesse, impermeabilite) sont chacune le critere nomme d'au moins un item - la position de
     * bonne reponse est celle de la propriete testee (un materiau), pas une reprise litterale du
     * mot dans le texte de la reponse (meme lecture que "un moyen de separation" sur
     * cm1-sciences-melanges).
     */
    @Test
    void fer1LesQuatrePropertiesSontChacuneLeCritereDUnItemDeLaTacheDeChoix() {
        List<Question> choix = itemsDeChoix();
        assertThat(choix).isNotEmpty();

        for (String adjectif : ADJECTIFS_DE_PROPRIETE) {
            boolean present = choix.stream().anyMatch(item -> item.enonce().contains(adjectif));
            assertThat(present)
                    .as("FER-1 : la propriete portee par '%s' est le critere d'au moins un item", adjectif)
                    .isTrue();
        }
    }

    /**
     * FAB-1b : la bonne reponse de la tache de choix ne se repete pas au-dela des proprietes qu'un
     * materiau porte reellement. <b>Un materiau repond a au plus {@link #DOMINATION_MAXIMALE} items
     * de la tache.</b>
     *
     * <p><b>Pourquoi la borne est ecrite et non deduite de la taille de la tache.</b> Cette
     * assertion comparait la frequence maximale a {@code choix.size()}, soit six : elle acceptait
     * <b>cinq bonnes reponses identiques sur six</b>. Fait mesure par {@code nadia-prof} au reaudit
     * du 2026-09-08 - <i>"varier n'est pas ne pas etre constant"</i> - et exact : le seuil avait
     * baisse de <i>six reponses distinctes</i> a <i>pas d'invariance totale</i>, un plafond
     * <b>trois crans au-dessus</b> de la dette qu'il gardait, ce qu'ADR-0013 refuse d'appeler un
     * cliquet.
     *
     * <p><b>La borne vaut la dette mesuree, et ce chiffre est une decision d'architecture</b> - pas
     * la lecture de {@code nadia-prof}, qui ne fixe aucun plafond (correction d'attribution du
     * 2026-09-08, sur son signalement ; ADR-0086, section 7). Un materiau peut porter <b>deux</b>
     * proprietes differentes - le plastique, sur la souplesse (item 8) et l'impermeabilite (item 9)
     * -, jamais trois. Mesure du 2026-09-08 sur le paquet servi : six items de choix, cinq
     * materiaux, frequence maximale <b>2</b>.
     *
     * <p><b>La borne est donc atteinte sans marge, {@code nadia-prof} le constate et je le
     * confirme</b> : un troisieme item sur un meme materiau rend ce test rouge, et c'est voulu -
     * c'est la moitie de la tache derriere un seul bouton, et un plafond qui depasse la dette qu'il
     * garde ne garde rien (ADR-0013). <b>Ce que ce rouge demande</b> n'est pas de relever la borne
     * ici : c'est un amendement ecrit d'ADR-0086 disant quel materiau porte trois proprietes reelles,
     * et cette question-la est de contenu, donc de {@code nadia-prof}.
     */
    @Test
    void fab1bAucunMateriauNeRepondAPlusDeDeuxItemsDeLaTacheDeChoix() {
        assertThat(dominationMaximaleDes(itemsDeChoix()))
                .as("FAB-1b : un materiau repond a au plus %d items de la tache de choix",
                        DOMINATION_MAXIMALE)
                .isLessThanOrEqualTo(DOMINATION_MAXIMALE);
    }

    /**
     * ADR-0013 obligation 3, sur une borne arithmetique. La morsure ne se prouve pas sur du contenu
     * - le contenu servi est conforme - mais sur ce que la borne <b>refuserait</b> : la distribution
     * que l'ancienne ecriture laissait passer, cinq reponses identiques sur six, et celle qui la
     * depasse d'un cran.
     */
    @Test
    void laBorneRefuseLaDistributionQueLAncienneEcritureAcceptait() {
        assertThat(dominationMaximale(List.of("le plastique", "le bois", "le metal", "le verre",
                "le tissu", "le plastique")))
                .as("la distribution reelle du paquet servi passe la borne")
                .isLessThanOrEqualTo(DOMINATION_MAXIMALE);
        assertThat(dominationMaximale(List.of("le plastique", "le plastique", "le plastique",
                "le plastique", "le plastique", "le bois")))
                .as("cinq reponses identiques sur six : l'ancienne ecriture les acceptait")
                .isGreaterThan(DOMINATION_MAXIMALE);
        assertThat(dominationMaximale(List.of("le plastique", "le plastique", "le plastique",
                "le bois", "le metal", "le verre")))
                .as("trois items sur le meme materiau : la moitie de la tache derriere un bouton")
                .isGreaterThan(DOMINATION_MAXIMALE);
    }

    private static long dominationMaximaleDes(List<Question> items) {
        return dominationMaximale(items.stream().map(Question::bonneReponse).toList());
    }

    /** Le nombre d'items que porte la bonne reponse la plus repetee. */
    private static long dominationMaximale(List<String> reponses) {
        return reponses.stream()
                .collect(java.util.stream.Collectors.groupingBy(reponse -> reponse,
                        java.util.stream.Collectors.counting()))
                .values().stream().mapToLong(Long::longValue).max().orElse(0);
    }

    /** Sur la tache de famille, les cinq objets decrits relevent chacun d'une famille differente. */
    @Test
    void lesCinqFamillesDeLaTacheDeFamilleSontToutesDistinctes() {
        List<String> reponses = itemsDeFamille().stream().map(Question::bonneReponse).distinct().toList();

        assertThat(reponses)
                .as("chaque item de la tache de famille a une bonne reponse differente")
                .hasSize(itemsDeFamille().size());
    }

    /**
     * Reprise S31 (REFUSE, {@code squad/conformite/FICHE-bandit-manchot-familles-materiaux-cm1.md}) :
     * toute bonne reponse de la tache de choix appartient a une famille deja enseignee par la tache
     * de famille - le caoutchouc, servi en bonne reponse sans avoir jamais ete enseigne, est ce qui
     * a fait partie du refus.
     */
    @Test
    void touteBonneReponseDeLaTacheDeChoixAppartientAUneFamilleEnseigneeParLaTacheDeFamille() {
        Set<String> famillesEnseignees = itemsDeFamille().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        for (Question item : itemsDeChoix()) {
            assertThat(famillesEnseignees)
                    .as("item %s : la bonne reponse '%s' doit etre une famille deja enseignee par la"
                            + " tache de famille", item.id().value(), item.bonneReponse())
                    .contains(item.bonneReponse());
        }
    }

    /**
     * Reprise S31 (REFUSE, PLA-1) : sur les items 6 (pont), 10 (bouteille) et 11 (vetement), au
     * moins un distracteur est desormais un materiau qu'un enfant peut serieusement envisager,
     * la ou les deux distracteurs precedents n'appartenaient a rien.
     */
    @Test
    void pla1LesTroisItemsCorrigesPortentDesormaisUnDistracteurPlausible() {
        Question itemPont = itemDontLEnonceContient("construire un pont");
        assertThat(itemPont.distracteurs())
                .as("PLA-1 : le pont propose desormais le bois comme candidat plausible")
                .contains("le bois");

        Question itemBouteille = itemDontLEnonceContient("bouteille réutilisable");
        assertThat(itemBouteille.distracteurs())
                .as("PLA-1 : la bouteille propose desormais le plastique comme candidat plausible")
                .contains("le plastique");

        Question itemVetement = itemDontLEnonceContient("fabriquer un vêtement");
        assertThat(itemVetement.distracteurs())
                .as("PLA-1 : le vetement propose desormais le cuir comme candidat plausible")
                .contains("le cuir");
    }

    /**
     * Reprise S31 (REFUSE, SCI-1) : l'indice de l'item de la bouteille ne dit plus "même liquide",
     * qui ne voulait rien dire puisque l'eau est deja liquide.
     */
    @Test
    void sci1LIndiceDeLItemDeLaBouteilleDitDesormaisQuelqueChoseDeVrai() {
        Question itemBouteille = itemDontLEnonceContient("bouteille réutilisable");
        assertThat(itemBouteille.indice().orElseThrow())
                .as("SCI-1 : l'indice ne repete plus 'meme liquide'")
                .doesNotContain("même liquide");
    }

    private static Question itemDontLEnonceContient(String fragment) {
        return items().stream().filter(item -> item.enonce().contains(fragment)).findFirst()
                .orElseThrow(() -> new AssertionError("aucun item ne contient : " + fragment));
    }
}
