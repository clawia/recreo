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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S29-5, le paquet {@code cm1-emc-droit-devoir}, ligne {@code EMC-CM1-07} de l'enseignement moral
 * et civique (CM1).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cm1.md}. Arbitrage prealable : {@code squad/conformite/ARBITRAGE-PREALABLE-s29-le-
 * prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md}, sections 4.4 et 5.
 *
 * <p><b>S29 (yanis-qa, {@code squad/sprints/s29-parallelisation-et-dettes-payees/rapport-
 * qualite.md}, section 3.3)</b> : ce paquet n'avait aucun fichier de test de matiere, ni a sa
 * livraison ni a sa reprise apres refus. Ce fichier comble ce vide pour ce qui est reellement
 * mordable, et encode en particulier deux des trois defauts corriges lors de la reprise
 * ({@code squad/conformite/FICHE-bandit-manchot-droit-devoir-cm1.md}) : le retrait de la troisieme
 * proposition "une interdiction", jamais bonne reponse, sur les items de classement (defaut 2), et la
 * presence d'au moins un devoir sur la seconde tache qui ne portait auparavant que des droits
 * (defaut 3).</p>
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : classer une situation comme un droit ou un devoir (bonne
 * reponse "un droit" ou "un devoir"), et dire a quoi sert un droit ou un devoir donne (bonne reponse
 * une phrase de finalite, l'enonce nommant explicitement "le droit" ou "le devoir" concerne).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1} et {@code EMC-8} demandent une lecture
 * semantique de chaque situation : non gardees ici. {@code EMC-5} (chaque distracteur nettement hors
 * sujet, aucune reponse doublement defendable) reste la garde de {@code nadia-prof} seule.
 * {@code EMC-10} (aucune personne reelle nommee) et {@code CYC-1} (continuite avec le lot de CP livre
 * le meme jour, qui demande une comparaison semantique entre deux referentiels) ne sont pas
 * automatisables ici et restent non gardees. {@code HIS-3} (chaque fait institutionnel porte sa
 * source) est une obligation de commentaire verifiee par {@code nadia-prof} a la lecture, pas une
 * propriete des items eux-memes : non gardee par ce fichier.</p>
 *
 * <p><b>Correction du 2026-09-06 (s30, reprise bloc F, S30-8, squad/conformite/FICHE-bandit-
 * manchot-droit-devoir-cm1.md, second audit s29, sections 4 a 6)</b> : trois reserves neuves,
 * {@code DRO-R1} a {@code DRO-R3}. Cette reprise change la forme du defaut 3 corrige plus haut - la
 * seconde tache continue de porter un devoir (item 4), mais son enonce ne le nomme plus, ce qui a
 * rendu obsolete {@code laSecondeTacheNommeAuMoinsUnDevoirEtLeResteDesDroits} : elle est remplacee
 * par {@code laSecondeTachePorteToujoursAuMoinsUnDevoir}, qui verifie la meme propriete via
 * l'indice plutot que l'enonce, laisse inchange par {@code DRO-R1}.</p>
 */
class PaquetCm1EmcDroitDevoirTest {

    private static final String PAQUET = "cm1-emc-droit-devoir";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les deux notions du libelle, bonne reponse des items de classement. */
    private static final Set<String> REPONSES_DE_CLASSEMENT = Set.of("un droit", "un devoir");

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

    private static boolean estDeClassement(Question item) {
        return REPONSES_DE_CLASSEMENT.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(PaquetCm1EmcDroitDevoirTest::estDeClassement).toList();
    }

    private static List<Question> itemsDUtilite() {
        return items().stream().filter(item -> !estDeClassement(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S29-5). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1 : les deux notions du libelle (un droit, un devoir) sont toutes deux en bonne reponse. */
    @Test
    void fer1LesDeuxNotionsSontToutesDeuxRepresenteesEnBonneReponse() {
        Set<String> reponses = itemsDeClassement().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : \"un droit\" et \"un devoir\" doivent tous deux etre representes")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_CLASSEMENT);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int classement = itemsDeClassement().size();
        int utilite = itemsDUtilite().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(utilite).as("la tache d'utilite est servie").isGreaterThan(0);
        assertThat(Math.min(classement, utilite))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Correction du 2026-09-06, defaut 2 : chaque item de classement reste binaire (un droit contre
     * un devoir), sans troisieme proposition "une interdiction" qui n'etait jamais la bonne reponse.
     * Un seul distracteur attendu.
     */
    @Test
    void chaqueItemDeClassementEstBinaireAvecUnSeulDistracteur() {
        for (Question item : itemsDeClassement()) {
            assertThat(item.distracteurs())
                    .as("item %s : un seul distracteur attendu, le classement reste binaire",
                            item.id().value())
                    .hasSize(1);
        }
    }

    /** Les items d'utilite proposent trois choix (une bonne reponse, deux distracteurs). */
    @Test
    void chaqueItemDUtiliteAExactementDeuxDistracteurs() {
        List<Question> items = itemsDUtilite();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Correction du 2026-09-06 (s29), defaut 3, forme mise a jour le 2026-09-06 (s30, {@code
     * DRO-R1}) : la seconde tache porte toujours au moins un devoir (item 4), mais son enonce ne le
     * nomme plus depuis {@code DRO-R1} - il nommait la categorie d'une situation qu'un item de
     * classement traite par ailleurs (item 3). La marque de la propriete vit desormais dans
     * l'indice de l'item, laisse inchange par cette correction.
     */
    @Test
    void laSecondeTachePorteToujoursAuMoinsUnDevoir() {
        List<Question> utilite = itemsDUtilite();
        assertThat(utilite).isNotEmpty();

        long portantUnDevoir = utilite.stream()
                .filter(item -> item.indice().orElse("").contains("devoir"))
                .count();

        assertThat(portantUnDevoir)
                .as("FER-1/FER-3 : au moins un item d'utilite reste associe a un devoir")
                .isGreaterThanOrEqualTo(1);
    }

    /** Les trois bonnes reponses d'utilite dont la situation est aussi traitee par un item de classement. */
    private static final Set<String> BONNES_REPONSES_UTILITE_PARTAGEANT_UNE_SITUATION = Set.of(
            "à ce que chacun puisse apprendre sans être dérangé",
            "à permettre à chaque enfant d'être soigné, quelle que soit sa famille",
            "à lui laisser du temps pour grandir en dehors du travail scolaire");

    /**
     * {@code DRO-R1} (nadia-prof, 2026-09-06, S30-8) : les trois items d'utilite dont la situation
     * est aussi traitee par un item de classement (ecouter le maitre et respecter les regles, etre
     * soigne, jouer et se reposer) ne nomment plus la categorie a trouver dans leur enonce - sans
     * quoi un tirage qui sert les deux items du couple resout le second sans distinction des qu'il a
     * deja servi le premier.
     */
    @Test
    void droR1LesItemsDUtiliteQuiPartagentLeurSituationAvecUnItemDeClassementNeNommentPlusLaCategorie() {
        List<Question> items = itemsDUtilite().stream()
                .filter(item -> BONNES_REPONSES_UTILITE_PARTAGEANT_UNE_SITUATION
                        .contains(item.bonneReponse()))
                .toList();

        assertThat(items).as("les trois items d'utilite concernes par DRO-R1").hasSize(3);
        for (Question item : items) {
            assertThat(item.enonce())
                    .as("item '%s' : DRO-R1, l'enonce ne nomme plus la categorie d'une situation"
                            + " deja traitee par un item de classement", item.enonce())
                    .doesNotContain("droit")
                    .doesNotContain("devoir");
        }
    }

    private static Question itemDontLEnonceContient(List<Question> items, String fragment) {
        return items.stream().filter(item -> item.enonce().contains(fragment)).findFirst()
                .orElseThrow(() -> new AssertionError("aucun item ne contient : " + fragment));
    }

    /**
     * {@code DRO-R2} (nadia-prof, 2026-09-06, S30-8, regle etendue a tout item binaire du depot) :
     * sur un item a deux propositions, l'indice porte le critere et jamais la categorie. L'indice
     * de l'item 1 nommait la categorie ("Ce droit protege...") ; celui de l'item 5 employait le mot
     * "droit" dans un autre sens (la permission), signal ambigu. Les quatre autres items binaires
     * (3, 7, 9, 11) portaient deja le critere sans nommer la categorie et ne sont pas concernes.
     */
    @Test
    void droR2LIndiceDesItemsUnEtCinqPortePlusLeCritereQueLaCategorie() {
        List<Question> items = items();
        Question item1 = itemDontLEnonceContient(items, "Jouer et se reposer en dehors du travail scolaire");
        Question item5 = itemDontLEnonceContient(items, "protégé contre la violence");

        assertThat(item1.indice().orElseThrow())
                .as("DRO-R2, item 1 : l'indice ne nomme plus la categorie")
                .doesNotContain("Ce droit")
                .doesNotContain("Ce devoir");
        assertThat(item5.indice().orElseThrow())
                .as("DRO-R2, item 5 : le mot 'droit' n'est plus employe, meme dans un autre sens")
                .doesNotContain("droit");
    }

    /**
     * {@code DRO-R3} (constat de serie, cout nul, S30-8) : l'adulte de classe nomme par ce paquet
     * ("la maitresse") devient masculin ("le maitre"), une occurrence suffisant a equilibrer la
     * serie de quatre paquets d'EMC ou l'adulte nomme etait une femme dans les quatre occurrences.
     */
    @Test
    void droR3LAdulteDeClasseDevientMasculinDansCePaquet() {
        List<Question> items = items();
        for (Question item : items) {
            assertThat(item.enonce())
                    .as("DRO-R3 : l'ancienne figure feminine unique ne doit plus apparaitre")
                    .doesNotContain("la maîtresse");
        }
        assertThat(items.stream().anyMatch(item -> item.enonce().contains("le maître")))
                .as("DRO-R3 : une occurrence masculine ('le maître') est desormais presente")
                .isTrue();
    }
}
