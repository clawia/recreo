package com.recreo.adapter.web;

import com.recreo.domain.mechanic.SessionStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0027 : tout ecran de jeu rendu par le serveur declare s'il a ete demande par un geste de
 * l'enfant, et seul un ecran arrive par un geste porte le marqueur qui autorise un mouvement.
 *
 * <p><b>Le defaut que ce garde-fou ferme, et il etait dans le mecanisme d'ADR-0026 lui-meme.</b>
 * ADR-0026 section 2 fait demarrer l'animation a l'insertion de l'element dans le document, en
 * comptant sur htmx pour fournir le declencheur. C'est insuffisant : un element entre aussi dans le
 * document quand le navigateur analyse une page entiere, et {@code accueil.html} rend le fragment de
 * manche en clair sur la branche de reprise (S1-4), sans echange htmx et sans aucun geste. Un enfant
 * qui recharge sa page verrait donc les colonnes tourner sans avoir rien touche, ce que
 * {@code nadia-prof} refuse nommement (arbitrage du 2026-08-10, section 6 : une machine qui bouge
 * toute seule est l'image dont cet ecran doit se separer).
 *
 * <p><b>Pourquoi le serveur, et pas le CSS.</b> Une fois le fragment insere, le document assemble est
 * exactement le meme dans les deux cas : meme arbre, memes classes, meme place dans
 * {@code <main id="jeu">}. Aucun selecteur CSS ne peut donc les distinguer, et la distinction n'a
 * d'existence possible que si le serveur la pose. Ce n'est pas un choix de conception, c'est une
 * contrainte.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : la liste des routes vient de {@code RequestMappingHandlerMapping}
 *       comme dans {@code ToutEcranEchangeEstUnDocumentJouableTest} ; une route neuve non exercee rend
 *       ce garde-fou rouge. Ce qui distingue un document complet d'un fragment est lu <b>dans la
 *       reponse elle-meme</b> - la presence d'un element {@code <html>} - jamais dans une liste de
 *       routes ecrite ici. C'est le seul critere qui ne se perime pas : un fragment est par definition
 *       ce que htmx echange, un document complet est par definition ce que le navigateur charge ;</li>
 *   <li><b>derivation totale</b> : un ecran de jeu qui ne porte aucun des deux marqueurs, ou les deux
 *       a la fois, est un echec et non un cas ignore. Le compilateur ferme l'autre moitie : le
 *       parametre {@code arriveeParUnGeste} de {@code PartieController.mancheModele} oblige chaque
 *       point d'appel a se prononcer ;</li>
 *   <li><b>mise a l'epreuve</b> : sur la sortie reelle du serveur, par mutation du seul point d'appel
 *       fautif, et par relecture adverse de la polarite du marqueur (voir ADR-0027).</li>
 * </ul>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class LEcranDeJeuDeclareCommentIlEstArriveTest {

    /** Deux items, donc deux coups suffisent a terminer la partie (PartieControllerTest). */
    private static final String JEU_ID = "un-jeu";

    /**
     * ADR-0034 : le meme item redonne indefiniment sur une partie de dix manches
     * (PartieControllerTest), seul jeu du catalogue de test sur lequel une reponse fausse laisse la
     * partie en cours.
     */
    private static final String JEU_REPETE_ID = "jeu-repete";

    /** La proposition fausse de l'item de {@code JEU_REPETE_ID} (PartieControllerTest). */
    private static final String PROPOSITION_FAUSSE = "mauvaise";

    /**
     * ADR-0034, amendement : deux manches et un contenu combinatoire (PartieControllerTest), donc une
     * partie que deux reponses fausses terminent sur un verdict faux.
     */
    private static final String JEU_INFINI_ID = "jeu-infini";

    /** Le marqueur d'ecran de jeu d'ADR-0022, qui dit ou l'enfant est. */
    private static final String ECRAN_DE_JEU = "ecran-de-jeu";

    /**
     * Le marqueur d'ADR-0027, qui dit comment l'enfant est arrive sur cet ecran. Un attribut et non
     * une classe, pour la meme raison qu'ADR-0017 a choisi data-role : ce qu'il declare n'est pas un
     * habillage, et une classe aurait exige une regle de style qui ne style rien.
     */
    private static final String ATTRIBUT_D_ARRIVEE = "data-arrivee";
    private static final String PAR_UN_GESTE = "par-un-geste";
    private static final String SANS_GESTE = "sans-geste";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestMappingHandlerMapping routes;

    /**
     * Une reponse du serveur telle qu'elle part. {@code documentComplet} est lu dans le corps, jamais
     * deduit de la route : c'est ce qui fait que ce garde-fou n'a pas de liste a tenir a jour.
     */
    private record Reponse(String nom, String route, String corps, boolean documentComplet) {
    }

    private Reponse reponse(String nom, String route, MockHttpServletRequestBuilder requete)
            throws Exception {
        String corps = mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
        boolean complet = !Jsoup.parse(corps).select("html").isEmpty()
                && corps.strip().toLowerCase(java.util.Locale.ROOT).startsWith("<!doctype");
        return new Reponse(nom, route, corps, complet);
    }

    /**
     * Le corpus, produit en jouant une vraie partie de bout en bout. Chaque route du controleur y
     * passe au moins une fois, et la branche de reprise - celle qui porte le defaut - y est exercee
     * avec une partie reellement en cours.
     */
    private List<Reponse> reponses() throws Exception {
        List<Reponse> reponses = new ArrayList<>();
        MockHttpSession partie = new MockHttpSession();

        reponses.add(reponse("accueil, aucune partie en cours", "GET /", get("/")));
        reponses.add(reponse("manche 1, demandee par un appui", "POST /parties/{jeuId}",
                post("/parties/{jeuId}", JEU_ID).session(partie)));
        reponses.add(reponse("accueil pendant une partie en cours (reprise silencieuse)", "GET /",
                get("/").session(partie)));
        reponses.add(reponse("retour juste, la partie continue", "POST /parties/{jeuId}/coups",
                post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "1")));
        reponses.add(reponse("retour faux, la partie continue", "POST /parties/{jeuId}/coups",
                coupFaux()));
        reponses.add(reponse("fin de partie sur une reponse fausse", "POST /parties/{jeuId}/coups",
                finDePartieFaussee()));
        reponses.add(reponse("manche 2, demandee par un appui sur Encore", "GET /parties/{jeuId}/manche",
                get("/parties/{jeuId}/manche", JEU_ID).session(partie)));
        reponses.add(reponse("retour de la derniere manche, fin de partie", "POST /parties/{jeuId}/coups",
                post("/parties/{jeuId}/coups", JEU_ID).session(partie)
                        .param("propositionChoisie", "2")));
        reponses.add(reponse("lecture d'une manche sur une partie terminee",
                "GET /parties/{jeuId}/manche", get("/parties/{jeuId}/manche", JEU_ID).session(partie)));
        reponses.add(reponse("retour explicite a la liste des jeux", "POST /retour",
                post("/retour").session(partie)));
        reponses.add(reponse("ecran d'erreur, partie introuvable", "POST /parties/{jeuId}/coups",
                post("/parties/{jeuId}/coups", JEU_ID).session(new MockHttpSession())
                        .param("propositionChoisie", "1")));

        return reponses;
    }

    /**
     * Une reponse fausse jouee sur une partie qui continue, sur sa propre session : la partie a deux
     * items ne peut pas porter a la fois un retour faux et un retour juste qui la laissent en cours.
     */
    private MockHttpServletRequestBuilder coupFaux() throws Exception {
        MockHttpSession partieFaussee = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_REPETE_ID).session(partieFaussee));
        return post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(partieFaussee)
                .param("propositionChoisie", PROPOSITION_FAUSSE);
    }

    /**
     * ADR-0034, amendement : la derniere manche jouee faux. Le jeu infini a deux manches la produit en
     * deux coups, la ou le jeu a item repete en demanderait dix.
     */
    private MockHttpServletRequestBuilder finDePartieFaussee() throws Exception {
        MockHttpSession partie = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_INFINI_ID).session(partie));
        mockMvc.perform(post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                .param("propositionChoisie", PROPOSITION_FAUSSE));
        return post("/parties/{jeuId}/coups", JEU_INFINI_ID).session(partie)
                .param("propositionChoisie", PROPOSITION_FAUSSE);
    }

    /**
     * ADR-0034 : ce corpus ne jouait que des bonnes reponses, et son perimetre derive par route ne
     * pouvait pas le dire - {@code POST /parties/{jeuId}/coups} y figurait deja. Les cinq clauses de
     * cette classe, dont celle qui refuse un mot leurre dans le conteneur de propositions, ne voyaient
     * donc jamais l'ecran que le serveur rend quand l'enfant se trompe.
     *
     * <p>La lecture des deux marqueurs est celle de {@code ToutEcranEchangeEstUnDocumentJouableTest},
     * appelee et non recopiee : deux lectures libres de diverger laisseraient un verdict sortir du
     * corpus d'ici et de celui de la-bas, donc controle par ni l'un ni l'autre (meme raison qu'ADR-0033
     * point 3).</p>
     */
    @Test
    void leCorpusExerceChaqueCoupleDeVerdictEtDeSuite() throws Exception {
        List<Reponse> reponses = reponses();
        String finDePartie = ToutEcranEchangeEstUnDocumentJouableTest.marqueurDeFinDePartie();

        for (String verdict : ToutEcranEchangeEstUnDocumentJouableTest.marqueursDeVerdict()) {
            for (SessionStatus statut : SessionStatus.values()) {
                boolean termineeAttendue = statut == SessionStatus.TERMINEE;
                assertThat(reponses)
                        .as("ADR-0034 : aucune reponse de ce corpus ne joint \"%s\" a un statut %s. Tout "
                                + "ce que le serveur rend dans cet etat echappe alors aux clauses de "
                                + "cette classe", verdict, statut)
                        .anyMatch(reponse -> {
                            Document rendu = Jsoup.parse(reponse.corps());
                            return !rendu.select("." + verdict).isEmpty()
                                    && !rendu.select("." + finDePartie).isEmpty() == termineeAttendue;
                        });
            }
        }
    }

    /** Les ecrans de jeu que cette reponse porte, au sens du marqueur d'ADR-0022. */
    private static List<Element> ecransDeJeu(Reponse reponse) {
        return Jsoup.parse(reponse.corps()).select("." + ECRAN_DE_JEU);
    }

    /**
     * L'ecran de jeu qui peut porter des colonnes, c'est-a-dire celui qui offre des propositions a
     * choisir. L'ecran de retour porte le meme marqueur d'ADR-0022 et n'a rien a animer ; il est donc
     * hors de la population de ce garde-fou, et ADR-0027 dit pourquoi cette exclusion est sure.
     */
    private static boolean offreDesPropositions(Element ecran) {
        return !ecran.select(".propositions").isEmpty();
    }

    private static List<Element> ecransAnimables(Reponse reponse) {
        return ecransDeJeu(reponse).stream()
                .filter(LEcranDeJeuDeclareCommentIlEstArriveTest::offreDesPropositions)
                .toList();
    }

    /** La valeur declaree du marqueur, ou une mention explicite de son absence. */
    private static String arrivee(Element ecran) {
        String valeur = ecran.attr(ATTRIBUT_D_ARRIVEE).strip();
        return valeur.isEmpty() ? "<aucune>" : valeur;
    }

    private static boolean declare(Element ecran, String valeur) {
        return valeur.equals(arrivee(ecran));
    }

    /**
     * Clause 1, celle qui ferme le defaut : un ecran de jeu qui arrive dans un document complet n'a
     * ete demande par aucun geste, donc ne porte jamais le marqueur qui autorise un mouvement.
     */
    @Test
    void aucunEcranDeJeuArrivantDansUnDocumentCompletNAutoriseUnMouvement() throws Exception {
        List<String> juges = new ArrayList<>();
        for (Reponse reponse : reponses()) {
            if (!reponse.documentComplet()) {
                continue;
            }
            for (Element ecran : ecransAnimables(reponse)) {
                juges.add(reponse.nom());
                assertThat(declare(ecran, PAR_UN_GESTE))
                        .as("ADR-0027 : sur \"%s\" (%s), le serveur rend un ecran de jeu dans un "
                                + "document complet - donc au chargement de la page, sans qu'aucun "
                                + "geste de l'enfant ne l'ait demande - et il declare %s=\"%s\". Les "
                                + "colonnes tourneraient devant un enfant qui a seulement recharge sa "
                                + "page (nadia-prof, arbitrage du 2026-08-10, section 6)",
                                reponse.nom(), reponse.route(), ATTRIBUT_D_ARRIVEE, PAR_UN_GESTE)
                        .isFalse();
                assertThat(declare(ecran, SANS_GESTE))
                        .as("ADR-0027 : sur \"%s\" (%s), cet ecran de jeu ne declare pas comment il est "
                                + "arrive. Un ecran muet est un ecran qu'aucune regle d'animation ne "
                                + "saura distinguer, et le doute joue alors du mauvais cote",
                                reponse.nom(), reponse.route())
                        .isTrue();
            }
        }
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de jeu n'est rendu dans un "
                        + "document complet. La branche de reprise de accueil.html en rend un ; si elle "
                        + "disparait, cette clause devient vide et ADR-0027 doit etre repris")
                .isNotEmpty();
    }

    /**
     * Clause 2, l'autre bout : un ecran de jeu echange par htmx a bien ete demande par un appui, et il
     * porte le marqueur. Sans cette clause, il suffirait de ne jamais poser le marqueur pour rendre la
     * clause 1 verte a vide - et plus aucun rouleau ne tournerait jamais.
     */
    @Test
    void toutEcranDeJeuEchangeParHtmxAutoriseLeMouvement() throws Exception {
        List<String> juges = new ArrayList<>();
        for (Reponse reponse : reponses()) {
            if (reponse.documentComplet()) {
                continue;
            }
            for (Element ecran : ecransAnimables(reponse)) {
                juges.add(reponse.nom());
                assertThat(declare(ecran, PAR_UN_GESTE))
                        .as("ADR-0027 : sur \"%s\" (%s), le serveur echange un ecran de jeu en reponse a "
                                + "un appui de l'enfant, et cet ecran declare %s=\"%s\" au lieu de "
                                + "\"%s\". Aucune colonne ne tournerait jamais : le garde-fou de la "
                                + "clause 1 resterait vert et le mouvement n'existerait nulle part",
                                reponse.nom(), reponse.route(), ATTRIBUT_D_ARRIVEE, arrivee(ecran),
                                PAR_UN_GESTE)
                        .isTrue();
                assertThat(arrivee(ecran))
                        .as("ADR-0027 : sur \"%s\" (%s), le marqueur d'arrivee ne prend qu'une des deux "
                                + "valeurs connues", reponse.nom(), reponse.route())
                        .isIn(PAR_UN_GESTE, SANS_GESTE);
            }
        }
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de jeu n'est echange par "
                        + "htmx")
                .isNotEmpty();
    }

    /**
     * ADR-0013 obligation 1 : le perimetre vient du controleur, pas du corpus ci-dessus. Une route
     * neuve que ce garde-fou n'exerce pas le rend rouge, au lieu de rendre un ecran de jeu sans que
     * personne n'ait verifie comment il declare son arrivee.
     */
    @Test
    void chaqueRouteDuControleurEstExerceeParCeGardeFou() throws Exception {
        Set<String> exercees = new TreeSet<>();
        reponses().forEach(reponse -> exercees.add(reponse.route()));

        assertThat(exercees)
                .as("ADR-0027 : toute route qui peut rendre un ecran de jeu entre dans ce corpus")
                .containsExactlyInAnyOrderElementsOf(routesDeclarees());
    }

    /**
     * Le corpus distingue bien deux populations. Sans cette assertion, une erreur de detection de
     * document complet - tout classe du meme cote - rendrait l'une des deux clauses vide sans le dire.
     */
    @Test
    void leCorpusPorteALaFoisDesDocumentsCompletsEtDesFragments() throws Exception {
        List<Reponse> reponses = reponses();

        assertThat(reponses).anyMatch(Reponse::documentComplet);
        assertThat(reponses).anyMatch(reponse -> !reponse.documentComplet());
        assertThat(reponses.stream().filter(Reponse::documentComplet).map(Reponse::nom).toList())
                .as("les documents complets sont ceux que le navigateur charge, et eux seuls")
                .allMatch(nom -> nom.startsWith("accueil"));
    }

    /**
     * Epinglage du chemin qui porte le defaut, sur la sortie reelle du serveur. Il nomme les deux
     * faits qui, ensemble, produisaient le mouvement non demande : l'ecran de jeu est bien rendu par
     * la reprise, et il l'est dans un document complet. Si l'un des deux cesse d'etre vrai - par
     * exemple si {@code camille-po} decide que la reprise ne rend plus la manche en clair - ce test
     * devient rouge et son message dit que la clause 1 perd son objet.
     */
    @Test
    void laRepriseSilencieuseRendBienUnEcranDeJeuDansUnDocumentComplet() throws Exception {
        MockHttpSession partie = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(partie));

        Reponse reprise = reponse("reprise", "GET /", get("/").session(partie));

        assertThat(reprise.documentComplet())
                .as("la reprise est un rendu serveur complet, pas un echange htmx")
                .isTrue();
        assertThat(ecransAnimables(reprise))
                .as("ADR-0027 : la reprise rend l'ecran de jeu en clair dans la page. Si ce n'est plus "
                        + "le cas, le defaut que ce garde-fou ferme n'existe plus et ADR-0027 doit etre "
                        + "repris plutot que laisse vert a vide")
                .hasSize(1);
        assertThat(arrivee(ecransAnimables(reprise).get(0))).isEqualTo(SANS_GESTE);
    }

    private Set<String> routesDeclarees() {
        Set<String> declarees = new TreeSet<>();
        routes.getHandlerMethods().forEach((information, methode) -> {
            if (!PartieController.class.equals(methode.getBeanType())) {
                return;
            }
            for (String verbe : verbes(information)) {
                for (String chemin : information.getPathPatternsCondition().getPatternValues()) {
                    declarees.add(verbe + " " + chemin);
                }
            }
        });
        assertThat(declarees)
                .as("le controleur doit declarer au moins une route, sans quoi ce recensement est vide")
                .isNotEmpty();
        return declarees;
    }

    private static Set<String> verbes(RequestMappingInfo information) {
        Set<String> verbes = new TreeSet<>();
        information.getMethodsCondition().getMethods().forEach(methode -> verbes.add(methode.name()));
        assertThat(verbes)
                .as("une route sans verbe declare repond a tous les verbes ; ce garde-fou ne saurait "
                        + "pas lesquels exercer")
                .isNotEmpty();
        return verbes;
    }

    /**
     * ADR-0027, propriete "jamais un autre mot" de {@code nadia-prof} (arbitrage du 2026-08-10,
     * section 2) : une colonne montre soit sa propre proposition, soit aucun texte. La forme classique
     * d'un rouleau - une bande de faces intermediaires - se voit ici, parce que ces faces seraient du
     * texte que le serveur envoie a l'interieur du conteneur de propositions.
     *
     * <p>Ce que cette clause couvre exactement, et ce qu'elle ne couvre pas, est ecrit dans ADR-0027 :
     * elle refuse un mot leurre <b>rendu par le serveur</b>. Un mot peint dans une image reste hors de
     * sa portee et reste a {@code nadia-prof}.
     */
    @Test
    void aucunTexteAutreQueLesPropositionsNEntreDansLeConteneurDePropositions() throws Exception {
        List<String> juges = new ArrayList<>();
        for (Reponse reponse : reponses()) {
            for (Element conteneur : Jsoup.parse(reponse.corps()).select(".propositions")) {
                Set<String> textesDesPropositions = new TreeSet<>();
                conteneur.select(".proposition")
                        .forEach(bouton -> textesDesPropositions.add(bouton.text().strip()));
                Set<String> textesRendus = new TreeSet<>();
                for (Element porteur : conteneur.select("*")) {
                    String propre = porteur.ownText().strip();
                    if (!propre.isEmpty()) {
                        textesRendus.add(propre);
                    }
                }
                juges.add(reponse.nom());
                assertThat(textesRendus)
                        .as("ADR-0027 : sur \"%s\" (%s), le conteneur de propositions porte du texte qui "
                                + "n'est aucune des propositions (%s). Un mot leurre lu par un enfant "
                                + "est refuse : sur un jeu d'homophones, il lui ferait voir la forme "
                                + "fausse plus souvent que la juste (nadia-prof, arbitrage du "
                                + "2026-08-10, section 2)",
                                reponse.nom(), reponse.route(), textesDesPropositions)
                        .isSubsetOf(textesDesPropositions);
            }
        }
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun conteneur de propositions n'est "
                        + "rendu")
                .isNotEmpty();
    }
}
