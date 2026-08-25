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
 * S20-2 (`DEC-0046`, section 2 ; `squad/conformite/FICHE-bandit-manchot-reperes-cm1.md`) : le
 * paquet "cm1-reperes". Competence detachee de "Relire et corriger son texte a l'aide de reperes
 * simples" par nadia-prof (`ARBITRAGE-PREALABLE-s19-quatre-proxys-en-attente-depuis-s13.md`,
 * section B), forme B (`REP-1` a `REP-4`) : une phrase unique par item, quatre propositions fixes
 * qui nomment ce qui manque.
 *
 * <p>Ce fichier verifie ce qui est mecaniquement derivable de la propriete sans interpretation :
 * le compte d'items, l'absence de virgule (`REP-1`), la part d'items sans erreur et son libelle
 * (`REP-2`), et que les quatre propositions servies sur chaque item sont exactement le meme
 * ensemble de quatre libelles fixes. <b>`REP-3` (un seul repere en jeu par item) et `REP-4`
 * (les quatre reperes chacun representes) ne sont pas verifies ici</b> : classer un item par le
 * repere qu'il teste demande de lire la phrase, pas seulement de compter des caracteres - "il
 * manque un point" est le meme libelle pour le point final et pour le point d'interrogation d'une
 * question, la forme retenue par nadia-prof ne portant pas de libelle distinct pour ce quatrieme
 * repere. C'est exactement la meme famille de defaut que `DEN-1` a `DEN-8` et `NAT-1` a `NAT-8` :
 * nadia-prof le dit elle-meme dans son arbitrage, aucun automate de ce depot ne les verifie. La
 * repartition obtenue (trois items par repere, items 1-3/4-6/7-9/10-12, puis quatre items sans
 * erreur) est documentee dans le commentaire du paquet servi, pour son audit.</p>
 */
class PaquetCm1ReperesTest {

    private static final String PAQUET = "cm1-reperes";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 16;

    private static final String LIBELLE_MAJUSCULE = "Il manque une majuscule.";
    private static final String LIBELLE_POINT = "Il manque un point.";
    private static final String LIBELLE_MAJUSCULE_PRENOM = "Il manque une majuscule au prénom.";
    private static final String LIBELLE_CORRECTE = "Cette phrase est correcte.";

    private static final Set<String> PROPOSITIONS_FIXES =
            Set.of(LIBELLE_MAJUSCULE, LIBELLE_POINT, LIBELLE_MAJUSCULE_PRENOM, LIBELLE_CORRECTE);

    @Test
    void lePaquetCompteExactementSeizeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void aucuneVirguleDansAucunEnonce() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.enonce())
                    .as("REP-1 : la phrase de l'item \"%s\" ne contient aucune virgule", item.enonce())
                    .doesNotContain(",");
        }
    }

    @Test
    void auMoinsUnQuartDesItemsNePorteAucuneErreur() {
        List<Question> items = tousLesItems(paquet());
        long sansErreur = items.stream()
                .filter(item -> LIBELLE_CORRECTE.equals(item.bonneReponse()))
                .count();

        assertThat(sansErreur)
                .as("REP-2 : au moins un quart des %d items du paquet est marque \"%s\"",
                        items.size(), LIBELLE_CORRECTE)
                .isGreaterThanOrEqualTo((items.size() + 3) / 4);
    }

    @Test
    void chaqueItemProposeExactementLesQuatreLibellesFixes() {
        for (Question item : tousLesItems(paquet())) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item \"%s\" : les quatre propositions sont exactement les libelles fixes"
                            + " retenus par nadia-prof (section B.3)", item.enonce())
                    .isEqualTo(PROPOSITIONS_FIXES);
            assertThat(item.distracteurs())
                    .as("item \"%s\" : trois distracteurs, un par libelle non retenu", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void lesSeizeEnoncesSontDistincts() {
        List<String> enonces = tousLesItems(paquet()).stream().map(Question::enonce).toList();
        assertThat(Set.copyOf(enonces))
                .as("seize phrases distinctes")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void leDescripteurEstFrancaisCm1OrthographeEtGrammaireReperes() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CM1");
        assertThat(descripteur.domaine()).isEqualTo("Orthographe et grammaire");
        assertThat(descripteur.competence())
                .isEqualTo("Repérer et corriger la majuscule et la ponctuation dans une phrase"
                        + " donnée (majuscule initiale, point final, majuscule au nom propre, point"
                        + " d'interrogation d'une question)");
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
