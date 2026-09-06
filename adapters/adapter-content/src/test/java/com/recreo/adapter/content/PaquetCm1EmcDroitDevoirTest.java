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
     * Correction du 2026-09-06, defaut 3 : la seconde tache (dire a quoi sert un droit ou un devoir)
     * porte desormais au moins un devoir, pas seulement des droits comme avant la correction. Chaque
     * item d'utilite nomme explicitement "le droit" ou "le devoir" dont il parle, ce qui rend la
     * verification directe sur l'enonce.
     */
    @Test
    void laSecondeTacheNommeAuMoinsUnDevoirEtLeResteDesDroits() {
        List<Question> utilite = itemsDUtilite();
        assertThat(utilite).isNotEmpty();

        long nommantUnDevoir = utilite.stream()
                .filter(item -> item.enonce().contains("devoir"))
                .count();
        long nommantUnDroit = utilite.stream()
                .filter(item -> item.enonce().contains("droit"))
                .count();

        assertThat(nommantUnDevoir)
                .as("FER-1/FER-3 : au moins un item d'utilite porte sur un devoir")
                .isGreaterThanOrEqualTo(1);
        assertThat(nommantUnDevoir + nommantUnDroit)
                .as("chaque item d'utilite nomme explicitement le droit ou le devoir concerne")
                .isEqualTo(utilite.size());
    }
}
