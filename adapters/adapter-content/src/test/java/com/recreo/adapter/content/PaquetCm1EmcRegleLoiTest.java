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
 * <b>S40-LOI, le paquet {@code cm1-emc-regle-loi}, ligne {@code EMC-CM1-08} de l'enseignement moral
 * et civique (CM1).</b> Referentiel : {@code squad/programme/enseignement-moral-et-civique/
 * competences-cm1.md}. Arbitrage prealable deja rendu (ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-
 * matieres-neuves-et-le-plafond-a-huit-lignes.md, ligne 425) : "les trois verbes de la parenthese.
 * Jamais de definition juridique dans l'enonce."
 *
 * <p><b>Deux taches distinctes (FER-3)</b> : distinguer une regle d'une loi sur un exemple concret
 * (items 1 a 6, bonne reponse "une regle" ou "une loi") et dire ce que fait une loi citee (items 7 a
 * 11, bonne reponse "elle autorise", "elle interdit" ou "elle protege").</p>
 *
 * <p><b>Ce que ce test ne juge pas</b> : {@code EMC-5} (aucun item n'a deux reponses defendables) est
 * le risque signale par l'avis de faisabilite de {@code theo-archi} - aucune regle executable ne peut
 * le mordre, il reste entierement la garde de {@code nadia-prof}. De meme pour l'exactitude des faits
 * de droit cites et pour l'absence de toute definition juridique dans l'enonce.</p>
 */
class PaquetCm1EmcRegleLoiTest {

    private static final String PAQUET = "cm1-emc-regle-loi";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

    /** FER-3 : au moins deux taches, la moins servie portant au moins trois items. */
    private static final int MINIMUM_PAR_TACHE = 3;

    /** Les trois verbes de la parenthese du libelle, bonne reponse de la tache "portee de la loi". */
    private static final Set<String> VERBES = Set.of("elle autorise", "elle interdit", "elle protège");

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

    private static boolean estDeTachePortee(Question item) {
        return VERBES.contains(item.bonneReponse());
    }

    private static List<Question> itemsDeClassement() {
        return items().stream().filter(item -> !estDeTachePortee(item)).toList();
    }

    private static List<Question> itemsDePortee() {
        return items().stream().filter(PaquetCm1EmcRegleLoiTest::estDeTachePortee).toList();
    }

    /** FER-2 : au moins dix items servent cette ligne (onze depuis S40-LOI). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * FER-1 : les trois verbes de la parenthese (autorise, interdit, protege) sont chacun en bonne
     * reponse sur des items distincts de la tache de portee.
     */
    @Test
    void fer1LesTroisVerbesSontEnBonneReponseDeLaTacheDePortee() {
        Set<String> reponses = itemsDePortee().stream().map(Question::bonneReponse)
                .collect(java.util.stream.Collectors.toSet());

        assertThat(reponses)
                .as("FER-1 : les trois verbes de la parenthese sont chacun en bonne reponse")
                .isEqualTo(VERBES);
    }

    /** Sur la tache de classement, la bonne reponse est toujours "une regle" ou "une loi". */
    @Test
    void laTacheDeClassementNeSertQueDeuxCategories() {
        List<Question> items = itemsDeClassement();
        assertThat(items).isNotEmpty();

        for (Question item : items) {
            assertThat(item.bonneReponse())
                    .as("item %s : la bonne reponse de classement est \"une règle\" ou \"une loi\"",
                            item.id().value())
                    .isIn("une règle", "une loi");
        }
    }

    /** Le mot "loi" n'est jamais accompagne d'une definition juridique dans l'enonce. */
    @Test
    void leMotLoiNEstJamaisAccompagneDUneDefinitionJuridique() {
        for (Question item : items()) {
            assertThat(item.enonce())
                    .as("item %s : l'enonce ne definit pas juridiquement le mot \"loi\"",
                            item.id().value())
                    .doesNotContain("la loi est", "une loi est", "la loi se definit");
        }
    }

    /**
     * FER-3 : au moins deux taches distinctes, la moins servie portant au moins trois items.
     * Encode la condition de fermeture, pas la fermeture elle-meme.
     */
    @Test
    void fer3AuMoinsDeuxTachesDistinctesEtLaMoinsServieAuMoinsTroisItems() {
        int classement = itemsDeClassement().size();
        int portee = itemsDePortee().size();

        assertThat(classement).as("la tache de classement est servie").isGreaterThan(0);
        assertThat(portee).as("la tache de portee est servie").isGreaterThan(0);
        assertThat(Math.min(classement, portee))
                .as("FER-3 : la tache la moins servie porte au moins %d items", MINIMUM_PAR_TACHE)
                .isGreaterThanOrEqualTo(MINIMUM_PAR_TACHE);
    }
}
