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
 * <b>S47-EMC-VOTE-CM1, le paquet {@code cm1-emc-vote-election}, ligne {@code EMC-CM1-10}</b> de
 * l'enseignement moral et civique (CM1), domaine "Construire une culture civique". Referentiel :
 * {@code squad/programme/enseignement-moral-et-civique/competences-cm1.md}. Arbitrage prealable
 * cite par la story et precise par {@code nadia-prof} sur la question Q6 de {@code theo-archi}
 * ({@code squad/conformite/ARBITRAGE-PREALABLE-s47-trois-questions-de-theo-archi.md}) : la
 * frontiere avec {@code EMC-CM1-09} (fermee a s46, {@code cm1-emc-institutions.yaml}) passe par
 * l'objet de la question - le vote <b>du</b> conseil municipal y est ferme, le vote <b>pour</b> le
 * conseil est ici.
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : tache A, reconnaitre quel terme du libelle (le
 * delegue de classe, une election, le suffrage universel) correspond a une situation decrite -
 * bonne reponse strictement l'un des trois termes (items 1 a 6) ; tache B, dire qui elit, qui est
 * elu, ou ce que fait un delegue - bonne reponse ne nomme jamais un des trois termes (items 7 a
 * 12).</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code EMC-1}, {@code EMC-2}, {@code EMC-5} et
 * {@code EMC-8} (aucun item n'interroge l'enfant sur sa propre vie ni ne lui demande de declarer
 * un comportement ou une intention de vote, une seule reponse est defendable) demandent une
 * lecture semantique de chaque situation, non gardee ici - c'est la garde de {@code nadia-prof}.
 * L'exactitude des faits institutionnels retenus (en particulier le piege du maire, non elu par
 * les habitants) reste egalement sa garde.</p>
 */
class PaquetCm1EmcVoteElectionTest {

    private static final String PAQUET = "cm1-emc-vote-election";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** FER-1 : les trois termes de la parenthese du libelle que la tache A doit chacun servir. */
    private static final Set<String> TERMES_DU_LIBELLE =
            Set.of("le délégué de classe", "une élection", "le suffrage universel");

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

    /** Tache A : la bonne reponse est strictement l'un des trois termes du libelle. */
    private static boolean estDeTacheQuelEstCeTerme(Question item) {
        return TERMES_DU_LIBELLE.contains(item.bonneReponse());
    }

    private static List<Question> itemsQuelEstCeTerme() {
        return items().stream().filter(PaquetCm1EmcVoteElectionTest::estDeTacheQuelEstCeTerme).toList();
    }

    private static List<Question> itemsQuiElitOuQueFait() {
        return items().stream().filter(item -> !estDeTacheQuelEstCeTerme(item)).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (douze a la livraison). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les trois termes du libelle (le delegue de classe, une election, le suffrage
     * universel) sont chacun en bonne reponse au moins une fois, sur la tache A.
     */
    @Test
    void fer1LesTroisTermesSontChacunEnBonneReponse() {
        assertThat(itemsQuelEstCeTerme().stream().map(Question::bonneReponse)
                .collect(Collectors.toSet()))
                .as("FER-1 : les trois termes du libelle sont chacun servis en position de bonne"
                        + " reponse")
                .containsExactlyInAnyOrderElementsOf(TERMES_DU_LIBELLE);
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int quelEstCeTerme = itemsQuelEstCeTerme().size();
        int quiElitOuQueFait = itemsQuiElitOuQueFait().size();

        assertThat(quelEstCeTerme).as("la tache 'quel est ce terme' est servie").isGreaterThan(0);
        assertThat(quiElitOuQueFait).as("la tache 'qui elit, ou que fait' est servie")
                .isGreaterThan(0);
        assertThat(Math.min(quelEstCeTerme, quiElitOuQueFait))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }

    /**
     * La tache "qui elit, ou que fait" ne reclasse jamais une situation en l'un des trois termes
     * du libelle : sa bonne reponse decrit toujours un fait, jamais un terme.
     */
    @Test
    void laTacheQuiElitOuQueFaitNeRediJamaisUnTermeEnBonneReponse() {
        for (Question item : itemsQuiElitOuQueFait()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la tache 'qui elit, ou que fait' decrit un fait, jamais l'un"
                            + " des trois termes du libelle", item.id().value())
                    .isNotIn(TERMES_DU_LIBELLE);
        }
    }

    /**
     * Q6, piege d'exactitude : le maire n'est pas elu par les habitants. Garde de forme, sur le
     * texte servi : aucun item n'ecrit "les habitants" comme corps electoral (seul "les électeurs
     * de la commune" est admis).
     */
    @Test
    void aucunItemNEcritLesHabitantsCommeCorpsElectoral() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s : 'les habitants' n'est jamais le corps electoral",
                            item.id().value())
                    .doesNotContain("les habitants");
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse ne designe jamais 'les habitants' comme corps"
                            + " electoral", item.id().value())
                    .doesNotContain("les habitants");
        }
    }

    /**
     * Q6, regle 1 : aucune bonne reponse ne decrit ce que decide le conseil municipal (budget,
     * projets, nom des rues) - garde de forme, sur les mots memes que cm1-emc-institutions.yaml
     * sert en bonne reponse pour ce role.
     */
    @Test
    void aucuneBonneReponseNeDecritCeQueDecideLeConseilMunicipal() {
        for (Question item : items()) {
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse ne decrit pas une decision du conseil"
                            + " municipal", item.id().value())
                    .doesNotContain("budget")
                    .doesNotContain("projet")
                    .doesNotContain("nom de la rue")
                    .doesNotContain("terrain de sport")
                    .doesNotContain("lampadaire");
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

    /** Ligne de referentiel : EMC-CM1-10 et elle seule, deja tenu par ADR-0081, non repete ici. */
    @Test
    void chaqueItemDeclareUniquementEmcCm110() {
        assertThat(new YamlContentPackLoader("").chargerPaquet(PAQUET).descriptor().competence())
                .contains("vote");
    }
}
