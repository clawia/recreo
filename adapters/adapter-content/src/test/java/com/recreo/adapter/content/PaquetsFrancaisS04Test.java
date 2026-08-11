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
 * <p>S5-7 : deux parties jouees avec des graines distinctes ne presentent pas exactement les memes
 * phrases. Le premier critere de S5-7 (davantage d'items que de manches par partie) vivait aussi ici,
 * dans une methode qui nommait son paquet ; il est desormais tenu pour <b>toutes</b> les entrees du
 * catalogue par {@link ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest} (ADR-0023), avec le
 * quatrieme critere de S6-3 qui etait la meme propriete ecrite une seconde fois. Les deux methodes
 * enumerees sont supprimees plutot que laissees en double : un perimetre enumere qui coexiste avec un
 * perimetre derive n'ajoute rien et laisse croire que la propriete se verifie paquet par paquet.</p>
 *
 * <p>S6-3 (DEC-0013) : ouverture d'un troisieme paquet, "cm1-conjugaison-present" (present de
 * l'indicatif uniquement), sans qu'aucune ligne de domain/mechanic ni domain/mechanics n'ait ete
 * touchee. Douze items, deux par personne grammaticale, chaque distracteur etant une autre forme
 * conjuguee au present du meme verbe que la bonne reponse.</p>
 *
 * <p>S6-4 : deux items neufs ajoutes a "cm1-homophones", dans les familles la/l'a et peu/peut, dont
 * le mot decisif n'est pas voisin du trou (condition chiffree par nadia-prof le 2026-08-07, section
 * 3, point 1). S6-5 : l'indice de l'item dont la bonne reponse est "mes" est corrige pour montrer
 * une phrase de substitution qui est elle-meme du francais correct (meme verdict, section 3, point
 * 2).</p>
 *
 * <p>S8-4 (DEC-0017, chantier 4) : "cm1-conjugaison-present" s'etend au futur et a l'imparfait de
 * l'indicatif, treize items ajoutes en fin de paquet, sous les six points de la barre fermee ecrite
 * par nadia-prof (squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md, refus du
 * 2026-08-10, section 5). Les douze items de present restent inchanges. Six personnes pour le
 * futur, six pour l'imparfait ; un item par temps (futur "auront", imparfait "aimaient", et un
 * treizieme item de present "aide") oppose sa bonne reponse a un distracteur d'un autre temps du
 * meme verbe, portant une marque temporelle reperable dans la phrase ("demain", "autrefois",
 * "maintenant") plutot que devinable.</p>
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

    /**
     * S5-3, motif 2 du refus : les sons s'ecrivent avec leurs lettres, sans crochet d'adulte.
     * S6-6/S6-7 : resserre a trois voyelles nasales, seule composition tranchee par nadia-prof
     * (avis de conception du 2026-08-06, point 3) - "ou", "oi" et "ch" sortent du paquet, faute de
     * graphie alternative courante au niveau CP qui les rendrait travaillables sans phoneme audio.
     * La liste reste ecrite plutot que supprimee : elle continue de refuser un quatrieme son.
     */
    private static final Set<String> SONS_ATTENDUS = Set.of("an", "on", "in");

    /**
     * S6-3 : chaque forme conjuguee du paquet "cm1-conjugaison-present" pointe vers le verbe
     * qu'elle conjugue au present. Un distracteur doit pointer vers le meme verbe que la bonne
     * reponse de son item - jamais un verbe different, jamais une forme d'un autre temps.
     */
    private static final Map<String, String> VERBE_DE_LA_FORME = Map.ofEntries(
            Map.entry("vais", "aller"), Map.entry("vas", "aller"), Map.entry("va", "aller"),
            Map.entry("allons", "aller"), Map.entry("allez", "aller"), Map.entry("vont", "aller"),
            Map.entry("lis", "lire"), Map.entry("lit", "lire"), Map.entry("lisons", "lire"),
            Map.entry("lisez", "lire"), Map.entry("lisent", "lire"),
            Map.entry("mange", "manger"), Map.entry("manges", "manger"), Map.entry("mangeons", "manger"),
            Map.entry("mangez", "manger"), Map.entry("mangent", "manger"),
            Map.entry("fais", "faire"), Map.entry("fait", "faire"), Map.entry("faisons", "faire"),
            Map.entry("faites", "faire"), Map.entry("font", "faire"),
            Map.entry("joue", "jouer"), Map.entry("joues", "jouer"), Map.entry("jouons", "jouer"),
            Map.entry("jouez", "jouer"), Map.entry("jouent", "jouer"),
            Map.entry("dessine", "dessiner"), Map.entry("dessines", "dessiner"),
            Map.entry("dessinons", "dessiner"), Map.entry("dessinez", "dessiner"),
            Map.entry("dessinent", "dessiner"),
            Map.entry("cherche", "chercher"), Map.entry("cherches", "chercher"),
            Map.entry("cherchons", "chercher"), Map.entry("cherchez", "chercher"),
            Map.entry("cherchent", "chercher"),
            Map.entry("finis", "finir"), Map.entry("finit", "finir"), Map.entry("finissons", "finir"),
            Map.entry("finissez", "finir"), Map.entry("finissent", "finir"),
            Map.entry("range", "ranger"), Map.entry("ranges", "ranger"), Map.entry("rangeons", "ranger"),
            Map.entry("rangez", "ranger"), Map.entry("rangent", "ranger"),
            Map.entry("cours", "courir"), Map.entry("court", "courir"), Map.entry("courons", "courir"),
            Map.entry("courez", "courir"), Map.entry("courent", "courir"),
            Map.entry("prends", "prendre"), Map.entry("prend", "prendre"), Map.entry("prenons", "prendre"),
            Map.entry("prenez", "prendre"), Map.entry("prennent", "prendre"),
            Map.entry("chante", "chanter"), Map.entry("chantes", "chanter"), Map.entry("chantons", "chanter"),
            Map.entry("chantez", "chanter"), Map.entry("chantent", "chanter"),
            /*
             * S8-4 : le futur et l'imparfait ajoutent des formes des memes verbes ("jouer", "faire",
             * "aller", "finir", "chanter" restent du paquet de present) et de trois verbes neufs pour
             * ce paquet ("aider", "etre", "aimer"). La propriete verifiee par ce garde-fou ne regarde
             * pas le temps d'une forme, seulement son verbe : un distracteur au futur ou a l'imparfait
             * reste une forme du meme verbe que la bonne reponse, jamais un verbe different.
             */
            Map.entry("jouerai", "jouer"), Map.entry("joueras", "jouer"), Map.entry("jouera", "jouer"),
            Map.entry("jouerons", "jouer"), Map.entry("jouerez", "jouer"), Map.entry("joueront", "jouer"),
            Map.entry("jouais", "jouer"), Map.entry("jouait", "jouer"), Map.entry("jouions", "jouer"),
            Map.entry("jouiez", "jouer"), Map.entry("jouaient", "jouer"),
            Map.entry("aide", "aider"), Map.entry("aides", "aider"), Map.entry("aidons", "aider"),
            Map.entry("aidez", "aider"), Map.entry("aident", "aider"),
            Map.entry("aiderai", "aider"), Map.entry("aideras", "aider"), Map.entry("aidera", "aider"),
            Map.entry("aiderons", "aider"), Map.entry("aiderez", "aider"), Map.entry("aideront", "aider"),
            Map.entry("aidais", "aider"), Map.entry("aidait", "aider"), Map.entry("aidions", "aider"),
            Map.entry("aidiez", "aider"), Map.entry("aidaient", "aider"),
            Map.entry("finirai", "finir"), Map.entry("finiras", "finir"), Map.entry("finira", "finir"),
            Map.entry("finirons", "finir"), Map.entry("finirez", "finir"), Map.entry("finiront", "finir"),
            Map.entry("ferai", "faire"), Map.entry("feras", "faire"), Map.entry("fera", "faire"),
            Map.entry("ferons", "faire"), Map.entry("ferez", "faire"), Map.entry("feront", "faire"),
            Map.entry("faisais", "faire"), Map.entry("faisait", "faire"), Map.entry("faisions", "faire"),
            Map.entry("faisiez", "faire"), Map.entry("faisaient", "faire"),
            Map.entry("irai", "aller"), Map.entry("iras", "aller"), Map.entry("ira", "aller"),
            Map.entry("irons", "aller"), Map.entry("irez", "aller"), Map.entry("iront", "aller"),
            Map.entry("aurai", "avoir"), Map.entry("auras", "avoir"), Map.entry("aura", "avoir"),
            Map.entry("aurons", "avoir"), Map.entry("aurez", "avoir"), Map.entry("auront", "avoir"),
            Map.entry("avais", "avoir"), Map.entry("avait", "avoir"), Map.entry("avions", "avoir"),
            Map.entry("aviez", "avoir"), Map.entry("avaient", "avoir"),
            Map.entry("étais", "être"), Map.entry("était", "être"), Map.entry("étions", "être"),
            Map.entry("étiez", "être"), Map.entry("étaient", "être"),
            Map.entry("aimerai", "aimer"), Map.entry("aimeras", "aimer"), Map.entry("aimera", "aimer"),
            Map.entry("aimerons", "aimer"), Map.entry("aimerez", "aimer"), Map.entry("aimeront", "aimer"),
            Map.entry("aimais", "aimer"), Map.entry("aimait", "aimer"), Map.entry("aimions", "aimer"),
            Map.entry("aimiez", "aimer"), Map.entry("aimaient", "aimer"));

    /**
     * S6-6/S6-7 : recomposition a trois voyelles nasales (avis de conception du 2026-08-06, point
     * 3). Remplace l'ancienne couverture a six sons ("ch", "ou", "on", "an", "in", "oi") : ces
     * trois-la sortent du paquet, la propriete qui reste est qu'aucun quatrieme son n'entre.
     */
    @Test
    void leBonSonCpNePresenteQueLesTroisNasalesAttendues() {
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
                .as("S6-6 : les trois voyelles nasales tranchees par nadia-prof, ni plus ni moins")
                .containsExactlyInAnyOrder("an", "on", "in");
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

    /**
     * S6-4 : deux items neufs dont le mot decisif n'est pas voisin du trou, dans les familles
     * la/l'a et peu/peut (condition chiffree par nadia-prof le 2026-08-07, section 3, point 1).
     * S6-5 : l'indice de l'item dont la bonne reponse est "mes" est corrige pour montrer une phrase
     * de substitution qui est elle-meme du francais correct, sur le modele de l'indice de l'item
     * "son" (meme verdict, section 3, point 2). Les treize autres items de ce lot (herites de S5-6)
     * restent inchanges : ce test les reprend tous pour le prouver, pas seulement celui qui bouge.
     */
    @Test
    void homophonesCm1PorteLesQuatorzeDerniersItemsAvecLIndiceDeMesCorrige() {
        List<Question> items = tousLesItems(jeu("bandit-manchot-homophones-cm1").contenu());

        List<Question> quatorzeDerniersItemsAttendus = List.of(
                new Question(new ItemId("item-10"), "Pose ton cartable ___, à côté du mien.", "là", List.of("la"),
                        Optional.of("Remplace par 'à cet endroit' : 'Pose ton cartable à cet endroit' fonctionne, alors c'est 'là'.")),
                new Question(new ItemId("item-11"), "Léa cherche ___ règle dans sa trousse.", "la", List.of("là"),
                        Optional.of("Le mot qui suit le trou est un nom ('règle') : il faut un déterminant, 'la'.")),
                new Question(new ItemId("item-12"), "Léa a perdu son livre depuis lundi, mais elle ___ enfin retrouvé ce matin.", "l'a", List.of("la"),
                        Optional.of("Remplace par 'l'avait' : 'elle l'avait enfin retrouvé' fonctionne, alors c'est 'l'a'.")),
                new Question(new ItemId("item-13"), "Il ___ range soigneusement dans son cartable.", "la", List.of("l'a"),
                        Optional.of("Le mot qui suit le trou est un verbe au présent ('range'), pas un participe : c'est 'la'.")),
                new Question(new ItemId("item-14"), "___ cahier appartient à mon petit frère.", "ce", List.of("se"),
                        Optional.of("Le mot qui suit le trou est un nom ('cahier') : il faut un déterminant, 'ce'.")),
                new Question(new ItemId("item-15"), "Après la récréation, tous les élèves ___ rangent devant la porte de la classe.", "se", List.of("ce"),
                        Optional.of("Le mot qui suit le trou est un verbe conjugué ('rangent') : il faut un pronom, 'se'.")),
                new Question(new ItemId("item-16"), "Regarde ___ oiseaux là-bas, sur la branche !", "ces", List.of("ses"),
                        Optional.of("'là-bas' montre ce qu'on regarde : c'est 'ces', comme quand on désigne quelque chose.")),
                new Question(new ItemId("item-17"), "Léa range son cahier et ___ crayons avant de partir.", "ses", List.of("ces"),
                        Optional.of("Léa range son cahier à elle, et donc ses crayons à elle aussi : c'est 'ses'.")),
                new Question(new ItemId("item-18"), "Il fait très froid ce matin, ___ le soleil brille quand même.", "mais", List.of("mes"),
                        Optional.of("Remplace par 'pourtant' : 'il fait froid, pourtant le soleil brille' fonctionne, alors c'est 'mais'.")),
                new Question(new ItemId("item-19"), "J'ai rangé ___ affaires avant de partir à l'école.", "mes", List.of("mais"),
                        Optional.of("Remplace par 'tes' : 'J'ai rangé tes affaires' fonctionne, alors c'est 'mes'.")),
                new Question(new ItemId("item-20"), "Il reste ___ de pain dans le placard de la cuisine.", "peu", List.of("peut"),
                        Optional.of("Remplace par 'un peu' : 'il reste un peu de pain' fonctionne, alors c'est 'peu'.")),
                new Question(new ItemId("item-21"), "Même si le ciel est bien dégagé ce matin, il ___ encore pleuvoir avant le soir.", "peut", List.of("peu"),
                        Optional.of("Remplace par 'pouvait' : 'il pouvait encore pleuvoir' fonctionne, alors c'est 'peut'.")),
                new Question(new ItemId("item-22"), "Tom cherchait sa gomme depuis le début du cours, et son voisin ___ enfin repérée sous la table.", "l'a", List.of("la"),
                        Optional.of("Remplace par 'l'avait' : 'son voisin l'avait enfin repérée sous la table' fonctionne, alors c'est 'l'a'.")),
                new Question(new ItemId("item-23"), "Après cette longue randonnée en montagne, le vieux chien ne ___ plus vraiment courir comme avant.", "peut", List.of("peu"),
                        Optional.of("Remplace par 'pouvait' : 'le vieux chien ne pouvait plus vraiment courir comme avant' fonctionne, alors c'est 'peut'.")));

        assertThat(items)
                .as("S6-4 : deux items neufs ajoutes en fin de paquet ; S6-5 : seul l'indice de 'mes'"
                        + " change, les treize autres items de ce lot restent inchanges")
                .containsAll(quatorzeDerniersItemsAttendus);
    }

    /**
     * S6-3, deuxieme critere, etendu par S8-4 : chaque distracteur est une autre forme conjuguee du
     * meme verbe que la bonne reponse - jamais un verbe different. S6-3 excluait aussi "une forme
     * d'un autre temps" ; S8-4 leve cette seconde moitie a dessein (barre de nadia-prof, point 2) :
     * trois items opposent desormais leur bonne reponse a un distracteur du meme verbe pris a un
     * autre temps, verifie item par item dans
     * {@link #conjugaisonCm1OpposeUnItemParTempsAUneAutreFormeDuMemeVerbe()}. Ce que ce garde-fou
     * continue de verifier, sur les vingt-cinq items : jamais un verbe different.
     */
    @Test
    void conjugaisonCm1NePresenteQueDesFormesDuMemeVerbe() {
        // DEC-0017 restitue "Le bon verbe" au catalogue (chantier 2) ; ce test continue de charger
        // le paquet directement, comme les autres verifications de contenu de ce fichier.
        List<Question> items = tousLesItems(paquet("cm1-conjugaison-present"));

        for (Question item : items) {
            String bonneReponse = item.bonneReponse().toLowerCase();
            String verbe = VERBE_DE_LA_FORME.get(bonneReponse);
            assertThat(verbe)
                    .as("la phrase '%s' porte une bonne reponse dont le verbe est connu", item.enonce())
                    .isNotNull();

            assertThat(item.distracteurs())
                    .as("la phrase '%s' porte au moins un distracteur", item.enonce())
                    .isNotEmpty();
            for (String distracteur : item.distracteurs()) {
                assertThat(distracteur.equalsIgnoreCase(bonneReponse))
                        .as("le distracteur '%s' de '%s' n'est pas la bonne reponse elle-meme",
                                distracteur, item.enonce())
                        .isFalse();
                assertThat(VERBE_DE_LA_FORME.get(distracteur.toLowerCase()))
                        .as("le distracteur '%s' de '%s' est une autre forme du meme verbe '%s',"
                                        + " jamais un verbe different",
                                distracteur, item.enonce(), verbe)
                        .isEqualTo(verbe);
            }
        }
    }

    /**
     * S6-3, troisieme critere : chacune des six personnes grammaticales est representee par au
     * moins un item du paquet.
     */
    @Test
    void conjugaisonPresentCm1RepresenteChacuneDesSixPersonnesGrammaticales() {
        // DEC-0017 restitue "Le bon verbe" au catalogue (chantier 2) ; ce test continue de charger
        // le paquet directement, comme les autres verifications de contenu de ce fichier.
        List<Question> items = tousLesItems(paquet("cm1-conjugaison-present"));

        Set<String> personnesRepresentees = new HashSet<>();
        for (Question item : items) {
            personnesRepresentees.add(personneGrammaticale(item.enonce()));
        }

        assertThat(personnesRepresentees)
                .as("S6-3 : chacune des six personnes grammaticales est representee par au moins"
                        + " un item")
                .containsExactlyInAnyOrder("je", "tu", "il/elle", "nous", "vous", "ils/elles");
    }

    /** S6-3, sixieme critere : 20 manches jouees a la suite ne presentent pas toutes la meme phrase. */
    @Test
    void conjugaisonPresentCm1VarieSurVingtManchesConsecutives() {
        // DEC-0017 restitue "Le bon verbe" au catalogue (chantier 2) ; ce test continue de charger
        // le paquet directement, comme les autres verifications de contenu de ce fichier.
        assertThat(enoncesSurVingtTirages(paquet("cm1-conjugaison-present")))
                .as("variete minimale sur 20 manches")
                .hasSizeGreaterThan(1);
    }

    /**
     * S8-4, point 1 de la barre de nadia-prof : le futur et l'imparfait sont ajoutes avec les six
     * personnes chacun. Identifie les items ajoutes par leur bonne reponse (les douze items de
     * present, inchanges, ne portent aucune de ces formes) et verifie que les six personnes
     * grammaticales sont chacune representee dans le lot du futur, puis dans celui de l'imparfait.
     */
    @Test
    void conjugaisonCm1AjouteLeFuturEtLImparfaitAvecLesSixPersonnesChacun() {
        List<Question> items = tousLesItems(paquet("cm1-conjugaison-present"));

        Set<String> bonnesReponsesFutur = Set.of("jouerai", "aideras", "finira", "ferons", "irez", "auront");
        Set<String> bonnesReponsesImparfait =
                Set.of("jouais", "aidais", "faisait", "étions", "aviez", "aimaient");

        Set<String> personnesAuFutur = new HashSet<>();
        Set<String> personnesALImparfait = new HashSet<>();
        for (Question item : items) {
            if (bonnesReponsesFutur.contains(item.bonneReponse())) {
                personnesAuFutur.add(personneGrammaticale(item.enonce()));
            }
            if (bonnesReponsesImparfait.contains(item.bonneReponse())) {
                personnesALImparfait.add(personneGrammaticale(item.enonce()));
            }
        }

        assertThat(personnesAuFutur)
                .as("S8-4, point 1 : les six personnes grammaticales sont representees au futur")
                .containsExactlyInAnyOrder("je", "tu", "il/elle", "nous", "vous", "ils/elles");
        assertThat(personnesALImparfait)
                .as("S8-4, point 1 : les six personnes grammaticales sont representees a l'imparfait")
                .containsExactlyInAnyOrder("je", "tu", "il/elle", "nous", "vous", "ils/elles");
    }

    /**
     * S8-4, point 2 de la barre de nadia-prof : au moins un item par temps oppose sa bonne reponse a
     * un distracteur du meme verbe pris a un autre temps, pas seulement a une autre personne du meme
     * temps - faute de quoi le temps demande resterait toujours donne par les propositions plutot que
     * choisi par l'enfant. Trois items designes nommement, un par temps : "auront" (futur, oppose a
     * "avaient", imparfait), "aimaient" (imparfait, oppose a "aimeront", futur) et "aide" (present,
     * oppose a "aidait" et "aidera", imparfait et futur).
     */
    @Test
    void conjugaisonCm1OpposeUnItemParTempsAUneAutreFormeDuMemeVerbe() {
        List<Question> items = tousLesItems(paquet("cm1-conjugaison-present"));

        Question itemFutur = itemALaBonneReponse(items, "auront");
        Question itemImparfait = itemALaBonneReponse(items, "aimaient");
        Question itemPresent = itemALaBonneReponse(items, "aide");

        assertThat(itemFutur.distracteurs())
                .as("S8-4, point 2 : l'item futur 'auront' oppose une forme de l'imparfait du meme verbe")
                .contains("avaient");
        assertThat(itemImparfait.distracteurs())
                .as("S8-4, point 2 : l'item imparfait 'aimaient' oppose une forme du futur du meme verbe")
                .contains("aimeront");
        assertThat(itemPresent.distracteurs())
                .as("S8-4, point 2 : l'item present 'aide' oppose une forme de l'imparfait et une du"
                        + " futur du meme verbe")
                .contains("aidait", "aidera");
    }

    /**
     * S8-4, point 3 de la barre de nadia-prof : chacun des trois items qui opposent deux temps porte
     * une marque temporelle reperable dans sa phrase plutot que devinable.
     */
    @Test
    void conjugaisonCm1LesItemsQuiOpposentDeuxTempsPortentUneMarqueTemporelleDansLEnonce() {
        List<Question> items = tousLesItems(paquet("cm1-conjugaison-present"));

        assertThat(itemALaBonneReponse(items, "auront").enonce())
                .as("S8-4, point 3 : marque temporelle du futur")
                .contains("Demain");
        assertThat(itemALaBonneReponse(items, "aimaient").enonce())
                .as("S8-4, point 3 : marque temporelle de l'imparfait")
                .contains("Autrefois");
        assertThat(itemALaBonneReponse(items, "aide").enonce())
                .as("S8-4, point 3 : marque temporelle du present")
                .contains("Maintenant");
    }

    /** Le seul item du paquet dont la bonne reponse est celle demandee, ou echoue si aucun ne l'est. */
    private static Question itemALaBonneReponse(List<Question> items, String bonneReponse) {
        return items.stream()
                .filter(item -> item.bonneReponse().equals(bonneReponse))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "aucun item du paquet n'a pour bonne reponse '" + bonneReponse + "'"));
    }

    /**
     * Determine la personne grammaticale d'un item a partir du pronom sujet ecrit dans sa phrase.
     * Leve une exception si aucun des six pronoms attendus n'est trouve, plutot que de rendre une
     * valeur qui masquerait une phrase mal formee.
     */
    private static String personneGrammaticale(String enonce) {
        for (String mot : enonce.toLowerCase().split("[^a-zàâäéèêëïîôöùûüçœ]+")) {
            switch (mot) {
                case "je" -> {
                    return "je";
                }
                case "tu" -> {
                    return "tu";
                }
                case "il", "elle" -> {
                    return "il/elle";
                }
                case "nous" -> {
                    return "nous";
                }
                case "vous" -> {
                    return "vous";
                }
                case "ils", "elles" -> {
                    return "ils/elles";
                }
                default -> {
                }
            }
        }
        throw new IllegalStateException("aucun sujet reconnu dans : " + enonce);
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
