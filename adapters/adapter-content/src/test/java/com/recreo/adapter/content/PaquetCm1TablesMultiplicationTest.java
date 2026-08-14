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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S12-3 (docs/backlog/backlog.md, section "Sprint 12") : le paquet "cm1-tables-multiplication",
 * seul paquet neuf de cette story. Les proprietes generiques (indice non vide, aucun item ne
 * repete l'ecran d'un autre) sont deja gardees par les garde-fous derives du repertoire de
 * contenu ; ce fichier verifie les criteres d'acceptation propres a S12-3 et les conditions T-1 a
 * T-6 de l'arbitrage prealable de nadia-prof (squad/conformite/
 * ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md), qu'aucun garde-fou generique ne connait : la
 * couverture des 55 couples de facteurs, les deux ordres sur les couples sans facteur 1 ni 10, et
 * la part des couples faciles.
 */
class PaquetCm1TablesMultiplicationTest {

    private static final String PAQUET = "cm1-tables-multiplication";
    private static final Pattern ENONCE = Pattern.compile("^(\\d{1,2}) × (\\d{1,2}) = \\?$");

    @Test
    void lePaquetCompteAuMoinsCinquanteCinqItems() {
        assertThat(tousLesItems(paquet(PAQUET))).hasSizeGreaterThanOrEqualTo(55);
    }

    @Test
    void chaqueEnonceEstUneMultiplicationDeDeuxFacteursEntreUnEtDixEtLaBonneReponseEstLeProduitExact() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            int[] facteurs = facteursDe(item.enonce());
            assertThat(facteurs[0]).as("premier facteur de '%s'", item.enonce()).isBetween(1, 10);
            assertThat(facteurs[1]).as("second facteur de '%s'", item.enonce()).isBetween(1, 10);
            assertThat(Integer.parseInt(item.bonneReponse())).as("bonne reponse de '%s'", item.enonce())
                    .isEqualTo(facteurs[0] * facteurs[1]);
        }
    }

    @Test
    void chaqueItemPorteExactementTroisPropositionsToutesDistinctes() {
        for (Question item : tousLesItems(paquet(PAQUET))) {
            assertThat(item.distracteurs()).as("distracteurs de '%s'", item.enonce()).hasSize(2);
            Set<String> propositions = new HashSet<>(item.distracteurs());
            propositions.add(item.bonneReponse());
            assertThat(propositions).as("propositions de '%s' : aucune ne doit etre egale a une"
                    + " autre (T-5), sinon l'item n'a pas de reponse unique", item.enonce())
                    .hasSize(3);
        }
    }

    @Test
    void chacunDesCinquanteCinqCouplesDeFacteursEstRepresenteAuMoinsUneFois() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        Set<Set<Integer>> couplesVus = new HashSet<>();
        for (Question item : items) {
            int[] facteurs = facteursDe(item.enonce());
            couplesVus.add(facteurs[0] == facteurs[1] ? Set.of(facteurs[0]) : Set.of(facteurs[0], facteurs[1]));
        }
        List<String> manquants = new ArrayList<>();
        for (int a = 1; a <= 10; a++) {
            for (int b = a; b <= 10; b++) {
                Set<Integer> couple = a == b ? Set.of(a) : Set.of(a, b);
                if (!couplesVus.contains(couple)) {
                    manquants.add(a + "x" + b);
                }
            }
        }
        assertThat(manquants).as("T-1 : les 55 couples (a, b) avec 1 <= a <= b <= 10 doivent tous"
                + " etre representes").isEmpty();
    }

    @Test
    void chaqueCoupleSansFacteurUnNiDixEtAuxFacteursDistinctsEstPresenteDansLesDeuxOrdres() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        Set<String> enonces = new HashSet<>();
        for (Question item : items) {
            enonces.add(item.enonce());
        }
        for (int a = 2; a <= 9; a++) {
            for (int b = a + 1; b <= 9; b++) {
                assertThat(enonces).as("T-3 : couple (%d, %d) doit presenter les deux ordres", a, b)
                        .contains(a + " × " + b + " = ?", b + " × " + a + " = ?");
            }
        }
    }

    @Test
    void lesItemsAFacteurUnOuDixNeDepassentPasUneMancheSurCinq() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        long faciles = items.stream().filter(item -> {
            int[] facteurs = facteursDe(item.enonce());
            return facteurs[0] == 1 || facteurs[1] == 1 || facteurs[0] == 10 || facteurs[1] == 10;
        }).count();
        assertThat(5 * faciles).as("T-2 : un facteur 1 ou 10 ne concerne pas plus d'une manche sur"
                + " cinq, sur %d items dont %d faciles", items.size(), faciles)
                .isLessThanOrEqualTo(items.size());
    }

    @Test
    void lesIndicesNeSontPasTousIdentiquesSurLePaquet() {
        List<Question> items = tousLesItems(paquet(PAQUET));
        Set<String> indicesDistincts = new HashSet<>();
        for (Question item : items) {
            indicesDistincts.add(item.indice().orElseThrow());
        }
        assertThat(indicesDistincts).as("T-6 : l'indice ne peut pas etre le meme sur tout le"
                + " paquet, il doit porter une strategie qui varie avec l'item")
                .hasSizeGreaterThan(1);
    }

    private static int[] facteursDe(String enonce) {
        Matcher matcher = ENONCE.matcher(enonce);
        assertThat(matcher.matches()).as("forme de l'enonce '%s'", enonce).isTrue();
        return new int[] {Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
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

    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }
}
