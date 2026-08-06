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
 *
 * <p>S5-6 : condition de nadia-prof pour que la ligne francais CM1 de
 * {@code squad/programme/MATRICE-couverture.md} passe a couvert-valide. Cinq familles ajoutees a
 * "cm1-homophones" (ce/se, ces/ses, la/la/l'a, mais/mes, peu/peut) ; la famille la/la/l'a pivote
 * sur deux paires distinctes plutot qu'un triplet, sur son arbitrage du 2026-08-06
 * ({@code squad/conformite/FICHE-bandit-manchot-homophones-cm1.md}).</p>
 */
class PaquetsFrancaisS04Test {

    private static final Set<String> COUPLES_HOMOPHONES_AUTORISES = Set.of(
            "a", "à", "et", "est", "on", "ont", "son", "sont", "ou", "où",
            "ce", "se", "ces", "ses", "la", "là", "l'a", "mais", "mes", "peu", "peut");

    /**
     * S5-6, arbitrage de nadia-prof du 2026-08-06 : "la" pivote deux paires distinctes (la/là et
     * la/l'a), donc un mot peut avoir plusieurs partenaires possibles selon l'item - une simple
     * correspondance mot-a-mot ne suffit plus.
     */
    private static final Map<String, Set<String>> PARTENAIRES_DE = Map.ofEntries(
            Map.entry("a", Set.of("à")), Map.entry("à", Set.of("a")),
            Map.entry("et", Set.of("est")), Map.entry("est", Set.of("et")),
            Map.entry("on", Set.of("ont")), Map.entry("ont", Set.of("on")),
            Map.entry("son", Set.of("sont")), Map.entry("sont", Set.of("son")),
            Map.entry("ou", Set.of("où")), Map.entry("où", Set.of("ou")),
            Map.entry("ce", Set.of("se")), Map.entry("se", Set.of("ce")),
            Map.entry("ces", Set.of("ses")), Map.entry("ses", Set.of("ces")),
            Map.entry("la", Set.of("là", "l'a")), Map.entry("là", Set.of("la")), Map.entry("l'a", Set.of("la")),
            Map.entry("mais", Set.of("mes")), Map.entry("mes", Set.of("mais")),
            Map.entry("peu", Set.of("peut")), Map.entry("peut", Set.of("peu")));

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
    void homophonesCm1NePresenteQueLesCouplesAttendusAvecUnUniqueDistracteurApparie() {
        List<Question> items = tousLesItems(jeu("bandit-manchot-homophones-cm1").contenu());

        Set<String> couplesCouverts = new HashSet<>();
        for (Question item : items) {
            String bonneReponse = item.bonneReponse().toLowerCase();
            assertThat(COUPLES_HOMOPHONES_AUTORISES)
                    .as("la phrase '%s' ne porte que sur un homophone grammatical connu", item.enonce())
                    .contains(bonneReponse);

            assertThat(item.distracteurs())
                    .as("la phrase '%s' n'a qu'un unique distracteur, l'autre mot du couple", item.enonce())
                    .hasSize(1);
            String distracteur = item.distracteurs().get(0).toLowerCase();
            assertThat(PARTENAIRES_DE.get(bonneReponse))
                    .as("le distracteur de '%s' est l'autre mot du meme couple, jamais un mot etranger",
                            item.enonce())
                    .contains(distracteur);
            couplesCouverts.add(coupleNormalise(bonneReponse, distracteur));
        }

        assertThat(couplesCouverts)
                .as("S5-6, arbitrage de nadia-prof du 2026-08-06 : dix familles d'homophones grammaticaux,"
                        + " la/là/l'a pivotant sur deux paires distinctes plutot qu'un triplet, soit onze"
                        + " couples au total")
                .containsExactlyInAnyOrder("a-à", "est-et", "on-ont", "son-sont", "ou-où",
                        "ce-se", "ces-ses", "la-là", "l'a-la", "mais-mes", "peu-peut");
    }

    @Test
    void homophonesCm1NeModifiePasLesDixItemsExistants() {
        List<Question> items = tousLesItems(jeu("bandit-manchot-homophones-cm1").contenu());

        List<Question> dixItemsExistantsDepuisS4 = List.of(
                new Question(new ItemId("item-0"), "Il ___ mangé une pomme.", "a", List.of("à")),
                new Question(new ItemId("item-1"), "Je vais ___ l'école.", "à", List.of("a")),
                new Question(new ItemId("item-2"), "Papa ___ maman font la cuisine.", "et", List.of("est")),
                new Question(new ItemId("item-3"), "Le chat ___ noir.", "est", List.of("et")),
                new Question(new ItemId("item-4"), "Les enfants ___ partis.", "sont", List.of("son")),
                new Question(new ItemId("item-5"), "Il prend ___ cartable.", "son", List.of("sont")),
                new Question(new ItemId("item-6"), "Ils ___ un chien.", "ont", List.of("on")),
                new Question(new ItemId("item-7"), "___ joue dans la cour.", "On", List.of("Ont")),
                new Question(new ItemId("item-8"), "___ vas-tu ?", "Où", List.of("Ou")),
                new Question(new ItemId("item-9"), "Tu veux du thé ___ du café ?", "ou", List.of("où")));

        assertThat(items)
                .as("S5-6 : cette story ajoute des items, elle ne corrige pas les dix items existants")
                .containsAll(dixItemsExistantsDepuisS4);
    }

    @Test
    void homophonesCm1VarieSurVingtManchesConsecutives() {
        assertThat(enoncesSurVingtTirages(jeu("bandit-manchot-homophones-cm1").contenu()))
                .as("variete minimale sur 20 manches")
                .hasSizeGreaterThan(1);
    }

    /** Normalise un couple observe (bonne reponse, distracteur) en la paire triee, pour le compter une fois. */
    private static String coupleNormalise(String bonneReponse, String distracteur) {
        return bonneReponse.compareTo(distracteur) <= 0
                ? bonneReponse + "-" + distracteur
                : distracteur + "-" + bonneReponse;
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
