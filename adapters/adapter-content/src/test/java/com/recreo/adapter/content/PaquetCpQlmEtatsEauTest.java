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
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>S27-2, le paquet {@code cp-qlm-etats-eau}, troisieme paquet de "Questionner le monde" (CP),
 * matiere neuve du produit.</b> Referentiel : ligne 5 de
 * {@code squad/conformite/ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md}, section
 * 4. Encode le plafond de vingt items, l'espace de reponse ferme aux deux etats que le referentiel
 * nomme ("solide", "liquide" - jamais "gazeux", hors de la parenthese de la competence), et la
 * condition de distribution ecrite dans le commentaire du paquet.
 */
class PaquetCpQlmEtatsEauTest {

    private static final String PAQUET = "cp-qlm-etats-eau";

    private static final int PLAFOND_ITEMS = 20;

    private static final Set<String> ETATS = Set.of("solide", "liquide");

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

    @Test
    void lePaquetNeDepassePasLePlafondDeVingtItems() {
        assertThat(items().size())
                .as("addendum de nadia-prof, section 12.2 : plafond de 20 items par paquet")
                .isLessThanOrEqualTo(PLAFOND_ITEMS);
    }

    /**
     * La competence du referentiel ne nomme que deux etats ("solide", "liquide") : aucun item ni
     * aucun distracteur ne doit introduire un troisieme etat (ex. "gazeux"), hors de la parenthese
     * de la ligne.
     */
    @Test
    void lEspaceDeReponseEstExactementLesDeuxEtatsDuReferentiel() {
        for (Question item : items()) {
            assertThat(ETATS)
                    .as("item %s : la bonne reponse doit etre l'un des deux etats du referentiel",
                            item.id().value())
                    .contains(item.bonneReponse());
            assertThat(ETATS)
                    .as("item %s : chaque distracteur doit etre l'un des deux etats du referentiel",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Condition de distribution ecrite dans le commentaire du paquet : chaque etat est reponse sur
     * la moitie des items. Compte reel, jamais affirme.
     */
    @Test
    void chacunDesDeuxEtatsEstReponseSurLaMoitieDesItems() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("les deux etats doivent toutes etre representes comme reponse")
                .containsExactlyInAnyOrderElementsOf(ETATS);

        int total = items.size();
        assertThat(total % comptes.size())
                .as("une moitie exacte suppose un total pair")
                .isZero();
        int moitie = total / comptes.size();
        for (Map.Entry<String, Integer> entree : comptes.entrySet()) {
            assertThat(entree.getValue())
                    .as("l'etat \"%s\" doit etre reponse sur exactement la moitie des %d items,"
                            + " soit %d", entree.getKey(), total, moitie)
                    .isEqualTo(moitie);
        }
    }
}
