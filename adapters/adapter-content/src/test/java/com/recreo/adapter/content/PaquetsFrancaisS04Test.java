package com.recreo.adapter.content;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.domain.content.ContentSource;
import com.recreo.domain.content.Item;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.SessionStatus;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotMove;
import com.recreo.domain.mechanics.banditmanchot.BanditManchotSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
 *
 * <p>Defaut signale par l'utilisatrice apres livraison de S5-6 : les douze items neufs affichaient
 * la reponse en clair dans la phrase au lieu d'un trou, alors que la consigne du jeu demande de
 * completer une phrase. Corrige : chaque item neuf porte desormais le marqueur "___" a la place du
 * mot attendu (garde-fou derive dans {@link ChaquePaquetATrousPorteSonTrouSurChaqueItemTest}).</p>
 *
 * <p>S5-5 (residuel) : les vingt-deux items de "cm1-homophones" portent desormais chacun un indice,
 * fermant la dette de {@code ChaquePaquetPorteUnIndiceTest.DETTE_DE_CONTENU} pour ce paquet.</p>
 *
 * <p>S5-7 : le paquet compte davantage d'items que de manches par partie, et deux parties jouees
 * avec des graines distinctes ne presentent pas exactement les memes phrases.</p>
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

    /** S5-3, motif 2 du refus : les sons s'ecrivent avec leurs lettres, sans crochet d'adulte. */
    private static final Set<String> SONS_ATTENDUS = Set.of(
            "an", "on", "in", "ou", "oi", "ch");

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
                .contains("ch", "ou", "on", "an", "in", "oi");
    }

    /**
     * S5-3, motif 2 du refus : les crochets de notation phonetique d'adulte ("[an]") ne sont
     * enseignes nulle part au CP, et un enfant qui apprend a dechiffrer va essayer de les lire
     * comme des signes.
     */
    @Test
    void leBonSonCpNAfficheAucunCrochetDeNotationDAdulte() {
        for (Question item : tousLesItems(paquet("cp-le-bon-son"))) {
            assertThat(item.enonce())
                    .as("enonce '%s'", item.enonce())
                    .doesNotContain("[").doesNotContain("]");
            assertThat(item.bonneReponse())
                    .as("reponse du mot '%s'", item.enonce())
                    .doesNotContain("[").doesNotContain("]");
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur)
                        .as("distracteur du mot '%s'", item.enonce())
                        .doesNotContain("[").doesNotContain("]");
            }
        }
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
    void homophonesCm1NeModifiePasLeContenuDesDixItemsExistants() {
        List<Question> items = tousLesItems(jeu("bandit-manchot-homophones-cm1").contenu());

        /*
         * S5-5 (residuel) ajoute un indice a chacun de ces dix items, ce que S5-6 n'avait pas fait :
         * la liste porte donc desormais les indices, mais l'enonce, la bonne reponse et le
         * distracteur de chaque item restent ceux d'origine depuis S4.
         */
        List<Question> dixItemsExistantsDepuisS4 = List.of(
                new Question(new ItemId("item-0"), "Il ___ mangé une pomme.", "a", List.of("à"),
                        Optional.of("Remplace par 'avait' : 'Il avait mangé une pomme' fonctionne, alors c'est 'a'.")),
                new Question(new ItemId("item-1"), "Je vais ___ l'école.", "à", List.of("a"),
                        Optional.of("Remplace par 'avait' : 'Je vais avait l'école' ne fonctionne pas, alors c'est 'à'.")),
                new Question(new ItemId("item-2"), "Papa ___ maman font la cuisine.", "et", List.of("est"),
                        Optional.of("Remplace par 'et puis' : 'Papa et puis maman font la cuisine' fonctionne, alors c'est 'et'.")),
                new Question(new ItemId("item-3"), "Le chat ___ noir.", "est", List.of("et"),
                        Optional.of("Remplace par 'était' : 'Le chat était noir' fonctionne, alors c'est 'est'.")),
                new Question(new ItemId("item-4"), "Les enfants ___ partis.", "sont", List.of("son"),
                        Optional.of("Remplace par 'étaient' : 'Les enfants étaient partis' fonctionne, alors c'est 'sont'.")),
                new Question(new ItemId("item-5"), "Il prend ___ cartable.", "son", List.of("sont"),
                        Optional.of("Remplace par 'ton' : 'Il prend ton cartable' fonctionne, alors c'est 'son'.")),
                new Question(new ItemId("item-6"), "Ils ___ un chien.", "ont", List.of("on"),
                        Optional.of("Remplace par 'avaient' : 'Ils avaient un chien' fonctionne, alors c'est 'ont'.")),
                new Question(new ItemId("item-7"), "___ joue dans la cour.", "On", List.of("Ont"),
                        Optional.of("Remplace par 'il' : 'Il joue dans la cour' fonctionne, alors c'est 'on'.")),
                new Question(new ItemId("item-8"), "___ vas-tu ?", "Où", List.of("Ou"),
                        Optional.of("Remplace par 'à quel endroit' : 'À quel endroit vas-tu ?' fonctionne, alors c'est 'où'.")),
                new Question(new ItemId("item-9"), "Tu veux du thé ___ du café ?", "ou", List.of("où"),
                        Optional.of("Remplace par 'ou bien' : 'Tu veux du thé ou bien du café ?' fonctionne, alors c'est 'ou'.")));

        assertThat(items)
                .as("S5-6 puis S5-5 (residuel) : l'enonce, la bonne reponse et le distracteur des dix"
                        + " items existants ne changent pas ; seul l'indice s'y ajoute")
                .containsAll(dixItemsExistantsDepuisS4);
    }

    @Test
    void homophonesCm1VarieSurVingtManchesConsecutives() {
        assertThat(enoncesSurVingtTirages(jeu("bandit-manchot-homophones-cm1").contenu()))
                .as("variete minimale sur 20 manches")
                .hasSizeGreaterThan(1);
    }

    /**
     * S5-7, premier critere : une partie (10 manches, {@code catalogue.yaml}) ne presente jamais
     * l'integralite du paquet.
     */
    @Test
    void homophonesCm1CompteDavantageDItemsQueDeManchesParPartie() {
        GameDefinition jeu = jeu("bandit-manchot-homophones-cm1");
        List<Question> items = tousLesItems(jeu.contenu());

        assertThat(items)
                .as("S5-7 : le paquet compte davantage d'items que de manches par partie (%d), sinon"
                        + " une partie presenterait toujours l'integralite du paquet", jeu.nombreDeManches())
                .hasSizeGreaterThan(jeu.nombreDeManches());
    }

    /**
     * S5-7, second critere : deux parties completes jouees a la suite avec des graines distinctes
     * ne presentent pas exactement les memes phrases. Jouee par la mecanique reelle
     * (bandit manchot) et non par un tirage isole, pour couvrir la chaine complete traversee par un
     * enfant.
     */
    @Test
    void homophonesCm1NePresentePasLesMemesPhrasesSurDeuxPartiesAGrainesDistinctes() {
        GameDefinition jeu = jeu("bandit-manchot-homophones-cm1");

        List<String> premierePartie = phrasesDUnePartieComplete(jeu, new Random(1));
        List<String> secondePartie = phrasesDUnePartieComplete(jeu, new Random(2));

        assertThat(premierePartie)
                .as("deux parties jouees avec des graines distinctes ne presentent pas exactement"
                        + " les memes phrases dans le meme ordre")
                .isNotEqualTo(secondePartie);
    }

    /** Joue une partie complete de bandit manchot et rend les enonces presentes, dans leur ordre. */
    private static List<String> phrasesDUnePartieComplete(GameDefinition jeu, Random graine) {
        RandomSource hasard = graine::nextInt;
        BanditManchot mecanique = new BanditManchot();
        BanditManchotMove reponseToujoursFausse = new BanditManchotMove("__jamais_une_bonne_reponse__");

        GameSession session = mecanique.start(jeu.contenu(), hasard, jeu.nombreDeManches());
        List<String> phrases = new ArrayList<>();
        while (session.status() == SessionStatus.EN_COURS) {
            BanditManchotSession mancheEnCours = (BanditManchotSession) session;
            phrases.add(mancheEnCours.enonceManche().orElseThrow());
            session = mecanique.apply(session, reponseToujoursFausse);
        }
        return phrases;
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
