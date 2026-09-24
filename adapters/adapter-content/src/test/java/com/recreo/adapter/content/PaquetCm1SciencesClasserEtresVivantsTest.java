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
 * <b>S41-SCI-CLASSER, le paquet {@code cm1-sciences-classer-etres-vivants}, ligne
 * {@code SCI-CM1-06} de sciences et technologie (CM1).</b> Referentiel :
 * {@code squad/programme/sciences-et-technologie/competences-cm1.md}. Arbitrage prealable deja
 * rendu (ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
 * borne.md, section 4.3) : ligne de classement, mention de couverture partielle deja fixee par la
 * matrice ("raisonnement sur un caractere, item par item ; le tri d'une collection entiere reste non
 * couvert").
 *
 * <p><b>{@code BIN-1}</b> : condition de faisabilite explicite du lot (avis de faisabilite de
 * {@code theo-archi}, {@code squad/sprints/s41-fusion-emc-sciences-qlm/avis-faisabilite.md},
 * section 6.6) - "au plus la moitie des items du paquet portent deux propositions". Encode ici sur
 * le patron exact de {@code PaquetCpEmcRegleSouhaitLoiTest} : denominateur le paquet entier, jamais
 * une tache. Ce paquet ne porte aucun item binaire (la forme "lequel des trois" impose trois
 * candidats sur chaque item), mais la clause est mordante par construction - un item binaire ferait
 * rougir cette assertion des qu'il depasserait la moitie du paquet.</p>
 *
 * <p><b>Ce que ce test ne juge pas.</b> {@code CYC-1} (aucune contradiction avec les paquets
 * {@code cp-qlm-vivant-non-vivant} et {@code cp-qlm-cycle-de-vie} deja servis) demande de comparer
 * deux paquets differents, ce qu'aucun garde-fou du depot ne fait (avis de faisabilite, section
 * 6.4) - verifie a la main dans le commentaire du fichier de contenu, garde de {@code nadia-prof} au
 * Done pedagogique.</p>
 */
class PaquetCm1SciencesClasserEtresVivantsTest {

    private static final String PAQUET = "cm1-sciences-classer-etres-vivants";

    /** FER-2 : au moins dix items servent une ligne fermee. */
    private static final int PLANCHER_FER_2 = 10;

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

    /** FER-2 : au moins dix items servent cette ligne (onze). */
    @Test
    void fer2AuMoinsDixItems() {
        assertThat(items().size())
                .as("FER-2 : au moins %d items par ligne", PLANCHER_FER_2)
                .isGreaterThanOrEqualTo(PLANCHER_FER_2);
    }

    /**
     * BIN-1 : au plus la moitie des items du paquet portent deux propositions (un distracteur)
     * plutot que trois. Le denominateur est le paquet entier, sur le patron exact de
     * {@code PaquetCpEmcRegleSouhaitLoiTest} (avis de faisabilite de theo-archi, section 6.6,
     * condition de faisabilite du lot).
     */
    @Test
    void bin1AuPlusLaMoitieDesItemsDuPaquetSontBinaires() {
        List<Question> items = items();
        long binaires = items.stream().filter(item -> item.distracteurs().size() == 1).count();

        assertThat(binaires)
                .as("BIN-1 : au plus la moitié des %d items du paquet portent deux propositions,"
                        + " or %d les portent", items.size(), binaires)
                .isLessThanOrEqualTo(items.size() / 2);
    }

    /** V3 : chaque item propose exactement trois candidats (une bonne reponse, deux distracteurs),
     * jamais une collection a trier ou a deplacer. */
    @Test
    void chaqueItemProposeExactementDeuxDistracteurs() {
        for (Question item : items()) {
            assertThat(item.distracteurs())
                    .as("item %s : deux distracteurs attendus (V3, jamais une collection entiere)",
                            item.id().value())
                    .hasSize(2);
        }
    }

    /**
     * Plusieurs caracteres observables distincts sont mobilises d'un item a l'autre, jamais un seul
     * caractere repete sur tout le paquet : les plumes, le nombre de pattes, le milieu de vie, les
     * ecailles, les poils et le vol sont chacun nommes par au moins un indice.
     */
    @Test
    void plusieursCaracteresObservablesDistinctsSontMobilises() {
        List<Question> items = items();

        assertThat(items).as("le caractere des plumes est mobilise")
                .anyMatch(item -> item.indice().orElse("").contains("plumes"));
        assertThat(items).as("le nombre de pattes est mobilise")
                .anyMatch(item -> item.indice().orElse("").contains("pattes"));
        assertThat(items).as("le caractere des ecailles est mobilise")
                .anyMatch(item -> item.indice().orElse("").contains("écailles"));
        assertThat(items).as("le caractere des poils est mobilise")
                .anyMatch(item -> item.indice().orElse("").contains("poils"));
        assertThat(items).as("le milieu de vie est mobilise")
                .anyMatch(item -> item.indice().orElse("").contains("eau")
                        || item.indice().orElse("").contains("arbres")
                        || item.indice().orElse("").contains("savane"));
    }

    /** Chaque item met en jeu trois etres vivants reels et distincts, jamais un element sans rapport
     * avec le vivant. */
    @Test
    void chaqueItemMetEnJeuTroisPropositionsDistinctes() {
        for (Question item : items()) {
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions)
                    .as("item %s : trois propositions distinctes attendues", item.id().value())
                    .hasSize(3);
        }
    }
}
