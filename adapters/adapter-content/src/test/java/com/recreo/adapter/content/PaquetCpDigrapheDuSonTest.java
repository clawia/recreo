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
 * <b>S26-L4, le paquet {@code cp-digraphe-du-son}, clause 2 de la ligne "associer les graphemes aux
 * phonemes" (digraphes {@code ou}, {@code oi}, {@code ch}).</b> Encode ce qui est calculable sur la
 * fiche de conformite (`squad/conformite/FICHE-bandit-manchot-digraphe-du-son-cp.md`) et l'addendum
 * de nadia-prof, section 11.4 : le plafond de vingt items, l'espace de reponse ferme a trois
 * digraphes, et la condition de distribution - <i>"chacun des trois est reponse sur un tiers des
 * items"</i>.
 *
 * <p><b>Ce que cette classe ne juge pas, et pourquoi</b> : meme motif que
 * {@link PaquetCpLettreDuSonTest} - le digraphe propose est mecaniquement une sous-chaine du mot
 * que l'adulte doit prononcer en entier (VOI-7). <b>Amendement du 2026-09-01</b> : ce n'est plus
 * une raison de ne pas juger. {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest} compare desormais par
 * <b>enonciation</b> - une suite contigue de jetons - et non par sous-chaine (VOI-14 point 1,
 * ADR-0076) ; il est branche sur le contenu de production et <b>ce paquet y passe</b>.</p>
 */
class PaquetCpDigrapheDuSonTest {

    private static final String PAQUET = "cp-digraphe-du-son";

    private static final int PLAFOND_ITEMS = 20;

    /** ADR-0064 : aucune borne ecrite a la main - le tirage s'arrete de lui-meme sur un paquet enumere. */
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

    /** Espace de reponse : exactement trois digraphes stables, jamais un autre. */
    @Test
    void lEspaceDeReponseEstExactementLesTroisDigraphesStables() {
        Set<String> attendus = Set.of("ou", "oi", "ch");
        for (Question item : items()) {
            assertThat(attendus)
                    .as("item %s : la bonne reponse doit etre l'un des trois digraphes stables",
                            item.id().value())
                    .contains(item.bonneReponse());
            assertThat(attendus)
                    .as("item %s : chaque distracteur doit etre l'un des trois digraphes stables",
                            item.id().value())
                    .containsAll(item.distracteurs());
        }
    }

    /**
     * Condition de distribution (addendum, section 11.4, citee mot pour mot) : chacun des trois
     * digraphes est reponse sur un tiers des items. Compte reel, jamais affirme.
     */
    @Test
    void chacunDesTroisDigraphesEstReponseSurUnTiersDesItems() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> comptes = new TreeMap<>();
        for (Question item : items) {
            comptes.merge(item.bonneReponse(), 1, Integer::sum);
        }

        assertThat(comptes.keySet())
                .as("les trois digraphes doivent tous etre representes comme reponse")
                .containsExactlyInAnyOrder("ou", "oi", "ch");

        int total = items.size();
        assertThat(total % comptes.size())
                .as("un tiers exact suppose un total multiple de trois")
                .isZero();
        int tiers = total / comptes.size();
        for (Map.Entry<String, Integer> entree : comptes.entrySet()) {
            assertThat(entree.getValue())
                    .as("addendum 11.4 : le digraphe \"%s\" doit etre reponse sur exactement un tiers"
                            + " des %d items, soit %d", entree.getKey(), total, tiers)
                    .isEqualTo(tiers);
        }
    }
}
