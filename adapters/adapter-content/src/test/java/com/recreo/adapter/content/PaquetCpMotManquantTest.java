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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S21-10 (`DEC-0051`) : le paquet "cp-mot-manquant". Arbitrage prealable de `nadia-prof`
 * (`squad/conformite/ARBITRAGE-PREALABLE-s21-trois-paquets-de-volume-cp.md`, partie 4, conditions
 * `PRE-1` a `PRE-8`) : le fichier repris (`contenu-repris/textes-trous/cp-mot-manquant.yml`) ne
 * travaillait pas la competence visee - 0 distracteur sur 15 obligeait a choisir un mot precis. Ce
 * paquet est une ecriture neuve ; seule la forme est reprise.
 *
 * <p>Ce que ce test verifie, et ce qu'il ne verifie pas. `PRE-4` (les trois familles - objets,
 * actions, sensations - representees, aucune sous un quart) et `PRE-5`/`PRE-6` (au moins seize
 * items, trois propositions chacun) sont des comptages, mecaniquement verifiables. `PRE-1`
 * (admissibilite grammaticale de chaque proposition dans le trou), `PRE-2` (deux propositions au
 * moins dans le meme champ que la bonne reponse) et `PRE-3` (aucune reponse alternative
 * defendable) demandent de lire chaque phrase : l'arbitrage prealable le dit lui-meme (section 7),
 * aucun de ces trois-la ne devient un test - un classifieur qui jugerait la plausibilite d'un
 * distracteur se tromperait, et une regle qui passe pour une mauvaise raison est pire qu'une regle
 * absente.
 */
class PaquetCpMotManquantTest {

    private static final String PAQUET = "cp-mot-manquant";

    private static final int NOMBRE_D_ITEMS_ATTENDU = 16;

    private static final int PROPOSITIONS_PAR_ITEM = 3;

    /**
     * PRE-4 : la famille de chaque item, derivee de sa position dans le YAML (le chargeur derive
     * {@code ItemId} de cette meme position). Le fichier commente ce meme decoupage en trois blocs
     * ("Objets", "Actions", "Sensations"), lu ici par position plutot que par {@code bonneReponse}
     * parce que "piquant" y designe deux items differents (hérisson, poivre).
     */
    private static final Map<Integer, String> FAMILLE_PAR_POSITION = Map.ofEntries(
            Map.entry(0, "objets"), Map.entry(1, "objets"), Map.entry(2, "objets"),
            Map.entry(3, "objets"), Map.entry(4, "objets"), Map.entry(5, "objets"),
            Map.entry(6, "actions"), Map.entry(7, "actions"), Map.entry(8, "actions"),
            Map.entry(9, "actions"), Map.entry(10, "actions"),
            Map.entry(11, "sensations"), Map.entry(12, "sensations"), Map.entry(13, "sensations"),
            Map.entry(14, "sensations"), Map.entry(15, "sensations"));

    @Test
    void lePaquetCompteExactementSeizeItems() {
        assertThat(tousLesItems(paquet())).hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void lesSeizeEnoncesSontDistincts() {
        List<String> enonces = tousLesItems(paquet()).stream().map(Question::enonce).toList();
        assertThat(Set.copyOf(enonces))
                .as("seize phrases distinctes")
                .hasSize(NOMBRE_D_ITEMS_ATTENDU);
    }

    @Test
    void chaqueItemProposeExactementTroisPropositions() {
        for (Question item : tousLesItems(paquet())) {
            assertThat(item.distracteurs())
                    .as("PRE-6 : item \"%s\" - deux distracteurs, soit trois propositions au total",
                            item.enonce())
                    .hasSize(2);

            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item \"%s\" : trois propositions distinctes", item.enonce())
                    .hasSize(PROPOSITIONS_PAR_ITEM);
        }
    }

    @Test
    void lesTroisFamillesSontChacuneRepresenteesSansDescendreSousUnQuart() {
        List<Question> items = tousLesItems(paquet());
        Map<String, Long> parFamille = Map.of(
                "objets", compterFamille(items, "objets"),
                "actions", compterFamille(items, "actions"),
                "sensations", compterFamille(items, "sensations"));

        long total = parFamille.values().stream().mapToLong(Long::longValue).sum();
        assertThat(total)
                .as("PRE-4 : chaque item est classe dans exactement une des trois familles")
                .isEqualTo(items.size());

        long quart = (items.size() + 3) / 4;
        parFamille.forEach((famille, compte) -> assertThat(compte)
                .as("PRE-4 : la famille \"%s\" represente au moins un quart des %d items", famille,
                        items.size())
                .isGreaterThanOrEqualTo(quart));
    }

    private static long compterFamille(List<Question> items, String famille) {
        long compte = 0;
        for (Question item : items) {
            int position = Integer.parseInt(item.id().value().substring("item-".length()));
            if (famille.equals(FAMILLE_PAR_POSITION.get(position))) {
                compte++;
            }
        }
        return compte;
    }

    @Test
    void leDescripteurEstFrancaisCpVocabulaireMotsPrecis() {
        var descripteur = paquet().descriptor();

        assertThat(descripteur.matiere()).isEqualTo("Français");
        assertThat(descripteur.niveau()).isEqualTo("CP");
        assertThat(descripteur.domaine()).isEqualTo("Vocabulaire");
        assertThat(descripteur.competence())
                .isEqualTo("Utiliser des mots précis pour désigner des objets, actions, sensations"
                        + " du quotidien");
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
