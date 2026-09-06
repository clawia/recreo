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
 * <b>S29-5, le paquet {@code cm1-emc-fait-opinion}, ligne {@code EMC-CM1-11} de l'enseignement
 * moral et civique (CM1).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cm1.md}. Arbitrage prealable : {@code squad/conformite/ARBITRAGE-PREALABLE-s29-le-
 * prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md}, sections 4.4 et 5.
 *
 * <p><b>S29 (yanis-qa, {@code squad/sprints/s29-parallelisation-et-dettes-payees/rapport-
 * qualite.md}, section 3.3)</b> : ce paquet n'avait aucun fichier de test de matiere, ni a sa
 * livraison ni a sa reprise apres refus. Ce fichier comble ce vide pour ce qui est reellement
 * mordable, et encode en particulier deux des trois defauts corriges lors de la reprise
 * ({@code squad/conformite/FICHE-bandit-manchot-fait-opinion-cm1.md}) : le retrait de la troisieme
 * categorie "un mensonge" sur les items de classement (defaut 2), et la consigne desormais explicite
 * sur chacun d'eux (defaut 1).</p>
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : classer un enonce cite comme un fait ou une opinion
 * (bonne reponse "un fait" ou "une opinion"), et dire comment verifier l'origine d'une information
 * avant de la partager (bonne reponse une phrase de methode).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1} et {@code EMC-8} demandent une lecture
 * semantique de chaque situation : non gardees ici. {@code EMC-5} (chaque distracteur nettement hors
 * sujet, aucune reponse doublement defendable) reste la garde de {@code nadia-prof} seule - en
 * particulier, ce test ne juge pas si une methode de verification decrite est elle-meme plausible ou
 * insuffisante, seulement qu'elle existe et que le classement reste binaire. {@code EMC-10} (aucune
 * personne reelle nommee) et {@code CYC-1} (continuite avec le lot de CP livre le meme jour, qui
 * demande une comparaison semantique entre deux referentiels) ne sont pas automatisables ici et
 * restent non gardees. {@code HIS-3} est sans objet sur ce paquet : les scenarios sont fictifs, le
 * commentaire du paquet le dit deja.</p>
 *
 * <p><b>Correction du 2026-09-06 (s30, reprise bloc E, S30-7, squad/conformite/FICHE-bandit-
 * manchot-fait-opinion-cm1.md, second audit s29, sections 4 a 6)</b> : deux reserves neuves,
 * {@code FOP-R2} et {@code FOP-R3}. {@code FOP-R1} (la consigne de catalogue decrit une tache que
 * les items de verification ne demandent pas) n'est pas traitee ici : sa correction porte sur
 * {@code catalogue.yaml}, dont {@code lea-dev} est la seule ecrivaine de s30 (planning s30, section
 * 5.1) - routee a {@code sofia-sm}, pas tranchee par ce commit.</p>
 */
class PaquetCm1EmcFaitOpinionTest {

    private static final String PAQUET = "cm1-emc-fait-opinion";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les deux notions du libelle, bonne reponse des items de classement. */
    private static final Set<String> REPONSES_DE_CLASSEMENT = Set.of("un fait", "une opinion");

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
        return items().stream().filter(PaquetCm1EmcFaitOpinionTest::estDeClassement).toList();
    }

    private static List<Question> itemsDeVerification() {
        return items().stream().filter(item -> !estDeClassement(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S29-5). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /** FER-1 : les deux notions du libelle (un fait, une opinion) sont toutes deux en bonne reponse. */
    @Test
    void fer1LesDeuxNotionsSontToutesDeuxRepresenteesEnBonneReponse() {
        Set<String> reponses = itemsDeClassement().stream()
                .map(Question::bonneReponse)
                .collect(Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : \"un fait\" et \"une opinion\" doivent tous deux etre representes")
                .containsExactlyInAnyOrderElementsOf(REPONSES_DE_CLASSEMENT);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int classement = itemsDeClassement().size();
        int verification = itemsDeVerification().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(verification).as("la tache de verification est servie").isGreaterThan(0);
        assertThat(Math.min(classement, verification))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * Correction du 2026-09-06, defaut 2 : chaque item de classement reste binaire (un fait contre
     * une opinion), sans troisieme bouton "un mensonge" qui melangerait l'axe vrai/faux avec l'axe
     * fait/opinion. Un seul distracteur attendu.
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

    /**
     * Correction du 2026-09-06, defaut 1 : chaque item de classement pose desormais explicitement
     * la question "Fait ou opinion ?" a la suite de la phrase citee.
     */
    @Test
    void chaqueItemDeClassementPoseExplicitementFaitOuOpinion() {
        List<Question> items = itemsDeClassement();
        assertThat(items).isNotEmpty();

        // TYP-1 (ADR-0067) : le paquet pose l'espace insecable devant le "?" final, y compris ici.
        String consigne = "Fait ou opinion" + ' ' + "?";
        for (Question item : items) {
            assertThat(item.enonce())
                    .as("item %s : la consigne \"Fait ou opinion ?\" doit etre explicite",
                            item.id().value())
                    .contains(consigne);
        }
    }

    /** Les items de verification proposent trois choix (une bonne reponse, deux distracteurs). */
    @Test
    void chaqueItemDeVerificationAExactementDeuxDistracteurs() {
        List<Question> items = itemsDeVerification();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus", item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Les cinq expressions d'absence de verification que {@code FOP-R2} retire. Un distracteur qui
     * declare litteralement qu'on ne verifie rien se repere sans comprendre la competence.
     */
    private static final List<String> EXPRESSIONS_D_ABSENCE_DE_VERIFICATION = List.of(
            "à toute la classe parce que ça semble amusant à raconter",
            "parce que le score est impressionnant",
            "immédiatement à tout le monde",
            "parce que ça lui semble inquiétant",
            "en imaginant une explication supplémentaire");

    /**
     * {@code FOP-R2} (nadia-prof, 2026-09-06, S30-7) : les cinq distracteurs qui disaient encore une
     * absence de verification en toutes lettres ont disparu ; les dix distracteurs de verification
     * disent chacun une methode reellement tentee mais insuffisante.
     */
    @Test
    void fopR2AucunDistracteurDeVerificationNeDeclarePlusUneAbsenceDeVerification() {
        List<String> distracteurs = itemsDeVerification().stream()
                .flatMap(item -> item.distracteurs().stream())
                .toList();

        assertThat(distracteurs).as("dix distracteurs de verification").hasSize(10);

        for (String expression : EXPRESSIONS_D_ABSENCE_DE_VERIFICATION) {
            assertThat(distracteurs)
                    .as("FOP-R2 : aucun distracteur ne doit plus contenir '%s'", expression)
                    .noneMatch(distracteur -> distracteur.contains(expression));
        }
    }

    /**
     * {@code FOP-R3} (nadia-prof, 2026-09-06, S30-7) : sur l'item dont la bonne reponse est "en
     * demandant directement à un adulte de l'école", la question porte desormais sur la verite de
     * l'information, pas sur son origine - l'adulte de l'ecole n'etant pas la source du camarade qui
     * a parle.
     */
    @Test
    void fopR3LItemSurLAdulteDeLEcoleInterrogeLaVeriteEtNonLOrigine() {
        Question item = itemsDeVerification().stream()
                .filter(candidat -> "en demandant directement à un adulte de l'école"
                        .equals(candidat.bonneReponse()))
                .findFirst()
                .orElseThrow();

        assertThat(item.enonce())
                .as("FOP-R3 : la question porte sur la verite, pas sur l'origine")
                .contains("Comment peut-elle savoir si c'est vrai")
                .doesNotContain("d'où vient cette information");
    }
}
