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
 * <b>S26-L3, le paquet {@code cp-lettre-du-son}, clause 1 de la ligne "associer les graphemes aux
 * phonemes" (voyelles et consonnes simples).</b> Encode ce qui est calculable sur la fiche de
 * conformite (`squad/conformite/FICHE-bandit-manchot-lettre-du-son-cp.md`) et l'addendum de
 * nadia-prof, section 11.4 : le plafond de vingt items et la condition de distribution -
 * <i>"chaque lettre servie est distracteur au moins aussi souvent qu'elle est reponse"</i>.
 *
 * <p><b>Ce que cette classe ne juge pas, et pourquoi c'est ecrit ici plutot que suppose</b> :
 * {@code VOI-10} comparait {@code texteAVoixHaute} aux propositions par sous-chaine repliee.
 * <b>Amendement du 2026-09-01</b> : cet operateur est remplace par l'enonciation - une suite
 * contigue de jetons - en {@code VOI-14} point 1, encodee par
 * {@link AucunTexteAVoixHauteNEnonceUnePropositionDeSonItemTest} et actee par ADR-0076. Sur ce paquet, la proposition
 * est une <b>lettre unique</b> (l'initiale du mot que l'adulte doit prononcer en entier, VOI-7) :
 * elle est donc mecaniquement une sous-chaine du mot lui-meme, par construction et non par defaut
 * d'ecriture - "papa" contient "p" quel que soit le mot choisi pour cette lettre. Le crochet
 * partage est branche sur le contenu de production et ce paquet y passe.</p>
 */
class PaquetCpLettreDuSonTest {

    private static final String PAQUET = "cp-lettre-du-son";

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

    /**
     * Espace de reponse : une lettre simple, jamais un digraphe (S26-L3, critere d'acceptation 1).
     */
    @Test
    void chaquePropositionEstUneLettreSimple() {
        for (Question item : items()) {
            assertThat(item.bonneReponse().length())
                    .as("item %s : la bonne reponse doit etre une lettre simple", item.id().value())
                    .isEqualTo(1);
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.length())
                        .as("item %s : chaque distracteur doit etre une lettre simple", item.id().value())
                        .isEqualTo(1);
            }
        }
    }

    /**
     * Condition de distribution (addendum, section 11.4, citee mot pour mot) : chaque lettre
     * servie est distracteur au moins aussi souvent qu'elle est reponse. Compte reel, jamais
     * affirme.
     */
    @Test
    void chaqueLettreServieEstDistracteurAuMoinsAussiSouventQuElleEstReponse() {
        List<Question> items = items();
        assertThat(items).isNotEmpty();

        Map<String, Integer> commeReponse = new TreeMap<>();
        Map<String, Integer> commeDistracteur = new TreeMap<>();
        for (Question item : items) {
            commeReponse.merge(item.bonneReponse(), 1, Integer::sum);
            for (String distracteur : item.distracteurs()) {
                commeDistracteur.merge(distracteur, 1, Integer::sum);
            }
        }

        for (Map.Entry<String, Integer> entree : commeReponse.entrySet()) {
            String lettre = entree.getKey();
            int commeReponseCompte = entree.getValue();
            int commeDistracteurCompte = commeDistracteur.getOrDefault(lettre, 0);
            assertThat(commeDistracteurCompte)
                    .as("addendum 11.4 : la lettre \"%s\" est reponse %d fois et distracteur %d fois"
                                    + " - elle doit etre distracteur au moins aussi souvent",
                            lettre, commeReponseCompte, commeDistracteurCompte)
                    .isGreaterThanOrEqualTo(commeReponseCompte);
        }
    }
}
