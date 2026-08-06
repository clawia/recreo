package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
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
 * S4-1, S4-2 (DEC-0008) : les deux paquets reels de francais, sans qu'aucune ligne de
 * domain/mechanic ou domain/mechanics n'ait ete touchee - la meme mecanique bandit manchot que
 * cp-additions-jusqu-a-10 joue ce contenu.
 *
 * <p>DEC-0009 : "cp-le-bon-son" n'a plus d'entree dans le catalogue de production (le jeu qui le
 * jouait, bandit-manchot-le-bon-son-cp, est REFUSE et retire jusqu'a correction), mais le paquet
 * lui-meme reste la matiere de travail des stories de correction et doit continuer a etre
 * couvert : il est charge directement par {@link YamlContentPackLoader#chargerPaquet}, sans
 * passer par le catalogue.</p>
 */
class PaquetsFrancaisS04Test {

    private static final Set<String> COUPLES_HOMOPHONES_AUTORISES = Set.of(
            "a", "à", "et", "est", "on", "ont", "son", "sont", "ou", "où");

    private static final Map<String, String> PAIRE_DE = Map.ofEntries(
            Map.entry("a", "à"), Map.entry("à", "a"),
            Map.entry("et", "est"), Map.entry("est", "et"),
            Map.entry("on", "ont"), Map.entry("ont", "on"),
            Map.entry("son", "sont"), Map.entry("sont", "son"),
            Map.entry("ou", "où"), Map.entry("où", "ou"));

    private static final Set<String> SONS_ATTENDUS = Set.of(
            "[an]", "[on]", "[in]", "[ou]", "[oi]", "[ch]");

    @Test
    void leBonSonCpNePresenteQueDesSonsConnusEtCouvreLesTroisDigraphesCitesParLeReferentiel() {
        List<Question> items = tousLesItems(paquet("cp-le-bon-son"));

        Set<String> sonsCouverts = new HashSet<>();
        for (Question item : items) {
            sonsCouverts.add(item.bonneReponse());

            assertThat(SONS_ATTENDUS)
                    .as("le mot '%s' n'annonce qu'un son de la liste connue", item.enonce())
                    .contains(item.bonneReponse());
            assertThat(item.distracteurs())
                    .as("le mot '%s' : les distracteurs sont des sons absents du mot", item.enonce())
                    .doesNotContain(item.bonneReponse())
                    .doesNotHaveDuplicates()
                    .allSatisfy(distracteur -> assertThat(SONS_ATTENDUS)
                            .as("distracteur '%s' du mot '%s'", distracteur, item.enonce())
                            .contains(distracteur));
        }

        assertThat(sonsCouverts)
                .as("ADR-0007, S4-2 : les trois digraphes cites en exemple par le referentiel,"
                        + " en plus des sons deja presents dans la matiere premiere")
                .contains("[ch]", "[ou]", "[on]", "[an]", "[in]", "[oi]");
    }

    @Test
    void leBonSonCpVarieSurVingtManchesConsecutives() {
        assertThat(enoncesSurVingtTirages(paquet("cp-le-bon-son")))
                .as("variete minimale sur 20 manches")
                .hasSizeGreaterThan(1);
    }

    @Test
    void homophonesCm1NePresenteQueLesCinqCouplesAttendusAvecUnUniqueDistracteurApparie() {
        List<Question> items = tousLesItems(jeu("bandit-manchot-homophones-cm1").contenu());

        Set<String> couplesCouverts = new HashSet<>();
        for (Question item : items) {
            String bonneReponse = item.bonneReponse().toLowerCase();
            assertThat(COUPLES_HOMOPHONES_AUTORISES)
                    .as("la phrase '%s' ne porte que sur un homophone grammatical connu", item.enonce())
                    .contains(bonneReponse);
            couplesCouverts.add(coupleNormalise(bonneReponse));

            assertThat(item.distracteurs())
                    .as("la phrase '%s' n'a qu'un unique distracteur, l'autre mot du couple", item.enonce())
                    .hasSize(1);
            String distracteur = item.distracteurs().get(0).toLowerCase();
            assertThat(distracteur)
                    .as("le distracteur de '%s' est l'autre mot du meme couple, jamais un mot etranger",
                            item.enonce())
                    .isEqualTo(PAIRE_DE.get(bonneReponse));
        }

        assertThat(couplesCouverts)
                .as("S4-1 : les cinq couples d'homophones cites par la competence sont tous representes")
                .containsExactlyInAnyOrder("a-à", "est-et", "on-ont", "son-sont", "ou-où");
    }

    @Test
    void homophonesCm1VarieSurVingtManchesConsecutives() {
        assertThat(enoncesSurVingtTirages(jeu("bandit-manchot-homophones-cm1").contenu()))
                .as("variete minimale sur 20 manches")
                .hasSizeGreaterThan(1);
    }

    /** Normalise un couple en la paire triee de ses deux formes, pour compter chaque couple une fois. */
    private static String coupleNormalise(String motConnu) {
        String autre = PAIRE_DE.get(motConnu);
        return motConnu.compareTo(autre) <= 0 ? motConnu + "-" + autre : autre + "-" + motConnu;
    }

    private static Set<String> enoncesSurVingtTirages(ContentSource contenu) {
        RandomSource hasard = new Random(42)::nextInt;
        Set<String> enonces = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            Question question = (Question) contenu.next(hasard, Set.<ItemId>of()).orElseThrow();
            enonces.add(question.enonce());
        }
        return enonces;
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

    private static GameDefinition jeu(String id) {
        return new YamlContentPackLoader().games().stream()
                .filter(candidat -> candidat.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    /** DEC-0009 : charge un paquet retire du catalogue directement, sans passer par une entree de jeu. */
    private static ContentSource paquet(String paquetId) {
        return new YamlContentPackLoader().chargerPaquet(paquetId);
    }
}
