package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0038 : sur tout ecran assemble ou une partie est en cours, ni le premier arret de tabulation
 * ni l'arret qui suit un point de focus que le serveur declare ne detruit cette partie. C'est
 * l'encodage demande par ce mecanisme pour la story 2 de {@code DEC-0026} : la confirmation avant
 * abandon (voir {@code PartieController.retour()}, {@code fragments/confirmation.html}).
 *
 * <p><b>ADR-0040, point 5, ajoute ici une quatrieme clause</b> : tout ecran a partie en cours
 * declare un point de focus. Elle ne juge pas ou va ce point de focus - les clauses ci-dessus s'en
 * chargent deja - elle juge seulement qu'il en existe un. Elle ferme la reserve N de
 * {@code nadia-prof} : sur {@code fragments/confirmation.html}, la premiere Tabulation quittait le
 * document 57 fois sur 69 arrivees, faute de tout point de focus declare.
 *
 * <p><b>"Destructeur" est une mesure, jamais une lecture de {@code data-role}</b> (ADR-0038, point
 * 3) : chaque arret candidat est rejoue - meme requete HTTP, sur une session fraiche reproduisant
 * l'etat de depart de l'ecran juge - et le verdict vient de l'etat de session apres coup
 * ({@code EtatDePartieEnSession}), jamais de l'attribut du bouton. C'est ce qui rend ce garde-fou
 * exact quel que soit le libelle ou la classe portee : renommer un bouton ne le desamorce pas.
 *
 * <p>ADR-0013, les trois obligations :
 * <ul>
 *   <li><b>perimetre derive</b> : le corpus d'ecrans vient de vraies requetes sur de vraies
 *       sessions (comme {@link ToutEcranEchangeEstUnDocumentJouableTest}) ; les routes jugees
 *       viennent de {@link RequestMappingHandlerMapping} ; les arrets de tabulation viennent du
 *       document assemble et du masquage declare par la feuille maison
 *       ({@link LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest#masquages}) ; le point de focus declare
 *       vient des marqueurs poses par le gabarit ({@code data-focus-cible}, {@code .enonce}) ;
 *       le caractere destructeur vient de la requete rejouee, jamais d'une liste ecrite ici ;</li>
 *   <li><b>derivation totale</b> : zero arret de tabulation sur un ecran a partie en cours, un
 *       {@code tabindex} illisible, une requete declaree introuvable sur un arret sont des
 *       <b>echecs</b>, jamais des cas ignores ;</li>
 *   <li><b>mise a l'epreuve</b> : sur la forme exacte que la production rendait avant ce lot
 *       ({@code manche-revelation-avant-correctif.html}), mesuree et datee dans ADR-0038 - pas une
 *       fixture imaginee.</li>
 * </ul>
 *
 * <p><b>Le vert facile qu'ADR-0038 nomme lui-meme, et pourquoi il ne s'applique pas ici.</b> Cette
 * regle passerait verte a vide si le contenu cessait d'offrir plus d'une proposition, ou si aucun
 * ecran du corpus ne portait de partie en cours : {@link #ecransAJugerNeContientPasQueDesEcransSansPartieEnCours}
 * et les assertions de non-vacuite de chaque clause existent pour fermer cette porte.
 *
 * <p><b>Ce que ce garde-fou ne prouve pas</b> : les huit reserves d'ADR-0038 (focus reel dans un
 * navigateur, rebouclage effectif, masquage au sens visuel, exhaustivite de "destructeur", jugement
 * pedagogique...) restent enumerees la-bas ; elles ne sont pas reprises ici, sur instruction
 * explicite de l'ADR de ne pas les paraphraser dans un commentaire de code.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class AucunArretDeTabulationNEstDestructeurTest {

    private static final String JEU_ID = "un-jeu";
    private static final String JEU_REPETE_ID = "jeu-repete";
    private static final String PROPOSITION_FAUSSE = "mauvaise";

    private static final Path RACINE_PRODUCTION = Path.of("src/main/resources");
    private static final Path CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/AucunArretDeTabulationNEstDestructeurTest");

    private static final Set<String> BALISES_NATURELLEMENT_FOCALISABLES =
            Set.of("a", "button", "input", "select", "textarea");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestMappingHandlerMapping routes;

    /** Rejoue integralement le scenario qui a produit un ecran, sur une session qu'on lui fournit. */
    @FunctionalInterface
    private interface Scenario {
        String rejouer(MockHttpSession session) throws Exception;
    }

    /** Un ecran assemble et le scenario qui l'a produit - necessaire pour rejouer un de ses arrets. */
    private record Ecran(String nom, String route, Scenario scenario, Document document) {
    }

    /** La requete qu'un arret declare : ce qu'on rejoue pour mesurer s'il detruit la partie. */
    private record RequeteDeclaree(String verbe, String url, Map<String, String> parametres) {
    }

    private String corps(MockHttpServletRequestBuilder requete) throws Exception {
        return mockMvc.perform(requete).andReturn().getResponse().getContentAsString();
    }

    /**
     * Le meme assemblage que {@link ToutEcranEchangeEstUnDocumentJouableTest#assembler} : l'innerHTML
     * que htmx pose dans la zone echangee, lu dans la coquille reelle. Duplique a dessein - trois
     * lignes semblables plutot qu'une abstraction prematuree pour deux classes (meme raisonnement
     * qu'ADR-0034 point 3 sur {@code LEcranDeJeuDeclareCommentIlEstArriveTest}).
     */
    private Document assembler(String fragment) throws Exception {
        Document coquille = Jsoup.parse(corps(get("/")));
        Set<String> cibles = new LinkedHashSet<>();
        for (Element porteur : coquille.select("[hx-target]")) {
            cibles.add(porteur.attr("hx-target").strip());
        }
        assertThat(cibles).as("la coquille doit declarer une seule zone hx-target").hasSize(1);
        coquille.select(cibles.iterator().next()).html(fragment);
        return coquille;
    }

    private Ecran ecranFragment(String nom, String route, Scenario scenario) throws Exception {
        MockHttpSession session = new MockHttpSession();
        String corps = scenario.rejouer(session);
        return new Ecran(nom, route, scenario, assembler(corps));
    }

    private Ecran ecranDocument(String nom, String route, Scenario scenario) throws Exception {
        MockHttpSession session = new MockHttpSession();
        String corps = scenario.rejouer(session);
        return new Ecran(nom, route, scenario, Jsoup.parse(corps));
    }

    /**
     * Le corpus, produit en jouant de vraies parties. Chaque route du controleur y figure au moins
     * une fois (obligation 1) ; les ecrans a partie en cours y voisinent des ecrans qui n'en portent
     * pas, pour que le filtre de la section suivante ait quelque chose a exclure et pas seulement a
     * inclure.
     */
    private List<Ecran> ecrans() throws Exception {
        List<Ecran> ecrans = new ArrayList<>();

        ecrans.add(ecranDocument("accueil, aucune partie en cours", "GET /",
                session -> corps(get("/").session(session))));

        ecrans.add(ecranFragment("manche 1, demandee par un appui", "POST /parties/{jeuId}",
                session -> corps(post("/parties/{jeuId}", JEU_ID).session(session))));

        ecrans.add(ecranDocument("reprise, une partie en cours", "GET /", session -> {
            corps(post("/parties/{jeuId}", JEU_ID).session(session));
            return corps(get("/").session(session));
        }));

        ecrans.add(ecranFragment("manche apres une reponse juste, la partie continue",
                "POST /parties/{jeuId}/coups", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    return corps(post("/parties/{jeuId}/coups", JEU_ID).session(session)
                            .param("propositionChoisie", "1"));
                }));

        ecrans.add(ecranFragment("manche apres une reponse fausse, la partie continue",
                "POST /parties/{jeuId}/coups", session -> {
                    corps(post("/parties/{jeuId}", JEU_REPETE_ID).session(session));
                    return corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                            .param("propositionChoisie", PROPOSITION_FAUSSE));
                }));

        ecrans.add(ecranFragment("manche en revelation, la partie continue",
                "POST /parties/{jeuId}/coups", session -> {
                    corps(post("/parties/{jeuId}", JEU_REPETE_ID).session(session));
                    corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                            .param("propositionChoisie", PROPOSITION_FAUSSE));
                    corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                            .param("propositionChoisie", PROPOSITION_FAUSSE));
                    return corps(post("/parties/{jeuId}/coups", JEU_REPETE_ID).session(session)
                            .param("propositionChoisie", PROPOSITION_FAUSSE));
                }));

        ecrans.add(ecranFragment("demande de confirmation avant d'abandonner, la partie continue",
                "POST /retour", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    return corps(post("/retour").session(session));
                }));

        ecrans.add(ecranFragment("retour explicite, sans partie en cours", "POST /retour", session -> {
            corps(post("/parties/{jeuId}", JEU_ID).session(session));
            corps(post("/parties/{jeuId}/coups", JEU_ID).session(session)
                    .param("propositionChoisie", "1"));
            corps(post("/parties/{jeuId}/coups", JEU_ID).session(session)
                    .param("propositionChoisie", "2"));
            return corps(post("/retour").session(session));
        }));

        ecrans.add(ecranFragment("annulation de l'abandon, retour a la manche, la partie continue",
                "POST /retour/annuler", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    corps(post("/retour").session(session));
                    return corps(post("/retour/annuler").session(session));
                }));

        ecrans.add(ecranFragment("confirmation de l'abandon, retour a la liste des jeux",
                "POST /retour/confirmer", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    corps(post("/retour").session(session));
                    return corps(post("/retour/confirmer").session(session));
                }));

        ecrans.add(ecranFragment("ecran d'erreur, partie introuvable", "POST /parties/{jeuId}/coups",
                session -> corps(post("/parties/{jeuId}/coups", JEU_ID).session(session)
                        .param("propositionChoisie", "1"))));

        return ecrans;
    }

    /**
     * ADR-0038, clause 4 : "une partie est en cours" est un fait de session mesure, jamais une
     * supposition tiree du balisage. Rejoue le scenario sur une session neuve et interroge
     * {@link EtatDePartieEnSession}, le seul type du depot qui lit cet etat (ADR-0019).
     */
    private boolean partieEnCoursSur(Ecran ecran) throws Exception {
        MockHttpSession session = new MockHttpSession();
        ecran.scenario().rejouer(session);
        return partieEnCours(session);
    }

    private static boolean partieEnCours(MockHttpSession session) {
        MockHttpServletRequest requete = new MockHttpServletRequest();
        requete.setSession(session);
        return new EtatDePartieEnSession(requete).lire().portePartieEnCours();
    }

    /**
     * Un arret est destructeur si la requete qu'il declare, rejouee sur une session fraiche qui
     * reproduit l'etat de depart de cet ecran, laisse la session sans partie en cours (ADR-0038,
     * point 3 : un effet mesure, jamais un attribut lu).
     */
    private boolean estDestructeur(Element arret, Scenario scenario) throws Exception {
        RequeteDeclaree requete = requeteDeclaree(arret).orElseThrow(() -> new AssertionError(
                "ADR-0038 obligation 2 : aucune requete declaree lisible sur " + etiquette(arret)));
        MockHttpSession fraiche = new MockHttpSession();
        scenario.rejouer(fraiche);

        MockHttpServletRequestBuilder rejeu = ("GET".equals(requete.verbe())
                ? get(requete.url())
                : post(requete.url())).session(fraiche);
        for (Map.Entry<String, String> parametre : requete.parametres().entrySet()) {
            rejeu = rejeu.param(parametre.getKey(), parametre.getValue());
        }
        mockMvc.perform(rejeu);
        return !partieEnCours(fraiche);
    }

    /**
     * La requete qu'un arret declare : {@code hx-post}/{@code hx-get} en priorite (c'est ce que htmx
     * intercepte, y compris sur une activation clavier) ; a defaut, un bouton de soumission dans un
     * formulaire declare la requete du formulaire, avec son propre couple nom/valeur ; a defaut, un
     * lien nu declare son {@code href}. Aucune des trois lisible est un echec (obligation 2), jamais
     * un arret ignore.
     */
    private static Optional<RequeteDeclaree> requeteDeclaree(Element arret) {
        if (arret.hasAttr("hx-post")) {
            return Optional.of(new RequeteDeclaree("POST", arret.attr("hx-post"), Map.of()));
        }
        if (arret.hasAttr("hx-get")) {
            return Optional.of(new RequeteDeclaree("GET", arret.attr("hx-get"), Map.of()));
        }
        if ("submit".equals(arret.attr("type"))) {
            Element formulaire = arret.closest("form");
            if (formulaire != null && formulaire.hasAttr("hx-post")) {
                Map<String, String> parametres = new LinkedHashMap<>();
                String nom = arret.attr("name");
                if (!nom.isBlank()) {
                    parametres.put(nom, arret.attr("value"));
                }
                return Optional.of(new RequeteDeclaree("POST", formulaire.attr("hx-post"), parametres));
            }
        }
        if (arret.hasAttr("href")) {
            return Optional.of(new RequeteDeclaree("GET", arret.attr("href"), Map.of()));
        }
        return Optional.empty();
    }

    private static Integer tabindex(Element element) {
        if (!element.hasAttr("tabindex")) {
            return null;
        }
        String valeur = element.attr("tabindex").strip();
        try {
            return Integer.parseInt(valeur);
        } catch (NumberFormatException echec) {
            throw new AssertionError("ADR-0038 obligation 2 : tabindex=\"" + valeur + "\" sur "
                    + etiquette(element) + " n'est pas une valeur que cette regle sait ordonner");
        }
    }

    private static boolean masque(Element candidat, Document document,
            List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages) {
        for (LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage masquage : masquages) {
            if (candidat.hasClass(masquage.habillage())
                    && LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masqueSur(document, masquage)) {
                return true;
            }
        }
        return false;
    }

    /**
     * L'ordre de tabulation d'ADR-0038, point 1 : {@code tabindex} positifs par valeur croissante
     * d'abord, puis le reste dans l'ordre du document ; un {@code tabindex} negatif exclut ; un
     * element masque par la feuille maison est retire (ADR-0022, meme derivation que
     * {@link LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest}).
     */
    private static List<Element> arretsDeTabulation(Document document,
            List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages) {
        List<Element> positifs = new ArrayList<>();
        List<Element> autres = new ArrayList<>();
        for (Element candidat : document.select("a[href], button, input, select, textarea, [tabindex]")) {
            if (masque(candidat, document, masquages) || candidat.hasAttr("disabled")) {
                continue;
            }
            Integer valeur = tabindex(candidat);
            if (valeur != null && valeur < 0) {
                continue;
            }
            boolean naturel = BALISES_NATURELLEMENT_FOCALISABLES.contains(candidat.tagName());
            if (!naturel && valeur == null) {
                continue;
            }
            (valeur != null && valeur > 0 ? positifs : autres).add(candidat);
        }
        positifs.sort(Comparator.comparingInt(AucunArretDeTabulationNEstDestructeurTest::tabindex));
        List<Element> resultat = new ArrayList<>(positifs);
        resultat.addAll(autres);
        return resultat;
    }

    /**
     * Le point de focus que le serveur peut declarer sur cet ecran (ADR-0038 clause 2), lu dans les
     * marqueurs du gabarit et non recopie ici (ADR-0015) : un {@code data-focus-cible="true"} designe
     * directement l'element cible, qu'il soit ou non un bouton (ADR-0040 : la phrase arbitree de
     * {@code fragments/confirmation.html} le porte, sans etre un arret de tabulation) ; a defaut, un
     * {@code data-focus} sans cible designe l'{@code .enonce} du meme ecran (M13 clause 4). Aucun des
     * deux sur un ecran : aucun point de focus n'y est declare, et la clause 2 n'a rien a y examiner.
     */
    private static Optional<Element> pointDeFocusDeclare(Document document) {
        Elements cibles = document.select("[data-focus-cible=\"true\"]");
        if (!cibles.isEmpty()) {
            assertThat(cibles).as("un seul bouton doit se declarer cible de focus").hasSize(1);
            return Optional.of(cibles.first());
        }
        Elements porteurs = document.select("[data-focus]");
        if (porteurs.isEmpty()) {
            return Optional.empty();
        }
        assertThat(porteurs).as("un seul element doit declarer data-focus par document assemble")
                .hasSize(1);
        Elements enonce = porteurs.first().select(".enonce");
        assertThat(enonce).as("un data-focus sans cible doit designer un .enonce present")
                .isNotEmpty();
        return Optional.of(enonce.first());
    }

    /** L'arret de {@code arrets} qui suit {@code pointDeFocus} dans l'ordre du document ; rebouclage
     * sur le premier arret si {@code pointDeFocus} est apres le dernier (ADR-0038, definition de
     * {@code A[0]} : "c'est aussi celui vers lequel la tabulation revient depuis le dernier arret"). */
    private static Element arretApres(Element pointDeFocus, Document document, List<Element> arrets) {
        IdentityHashMap<Element, Integer> ordre = new IdentityHashMap<>();
        int position = 0;
        for (Element element : document.getAllElements()) {
            ordre.put(element, position++);
        }
        int positionDuFocus = ordre.get(pointDeFocus);
        for (Element arret : arrets) {
            Integer positionArret = ordre.get(arret);
            if (positionArret != null && positionArret > positionDuFocus) {
                return arret;
            }
        }
        return arrets.get(0);
    }

    private static String etiquette(Element element) {
        return "<" + element.tagName() + " class=\"" + element.className() + "\"> \""
                + element.text() + "\"";
    }

    /**
     * Clause 1 : sur tout ecran assemble ou une partie est en cours, le premier arret de tabulation
     * n'est pas destructeur. C'est cette clause qui ferme le defaut mesure par nadia-prof (M13
     * clause 1) : la sortie n'est plus, seule, le premier arret d'un ecran qui detruit d'un geste.
     */
    @Test
    void aucunPremierArretDeTabulationNeDetruitUnePartieEnCours() throws Exception {
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(RACINE_PRODUCTION);
        List<String> juges = new ArrayList<>();
        List<String> ecarts = new ArrayList<>();

        for (Ecran ecran : ecrans()) {
            if (!partieEnCoursSur(ecran)) {
                continue;
            }
            juges.add(ecran.nom());
            List<Element> arrets = arretsDeTabulation(ecran.document(), masquages);
            assertThat(arrets)
                    .as("ADR-0038 obligation 2 : zero arret de tabulation sur \"%s\" (%s)",
                            ecran.nom(), ecran.route())
                    .isNotEmpty();
            if (estDestructeur(arrets.get(0), ecran.scenario())) {
                ecarts.add("clause 1 : sur \"" + ecran.nom() + "\" (" + ecran.route()
                        + "), le premier arret de tabulation " + etiquette(arrets.get(0))
                        + " detruit la partie en cours");
            }
        }

        assertThat(ecarts).as("ADR-0038 clause 1").isEmpty();
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de ce corpus ne porte une "
                        + "partie en cours")
                .isNotEmpty();
    }

    /**
     * Clause 2 : pour tout point de focus que le serveur peut declarer sur un ecran a partie en
     * cours, l'arret qui le suit n'est pas destructeur - y compris par rebouclage depuis le dernier
     * arret du document, exactement le chemin par lequel la revelation en derniere position
     * detruisait la partie avant ce lot.
     */
    @Test
    void aucunArretQuiSuitUnPointDeFocusDeclareNeDetruitUnePartieEnCours() throws Exception {
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(RACINE_PRODUCTION);
        List<String> juges = new ArrayList<>();
        List<String> ecarts = new ArrayList<>();

        for (Ecran ecran : ecrans()) {
            if (!partieEnCoursSur(ecran)) {
                continue;
            }
            Optional<Element> pointDeFocus = pointDeFocusDeclare(ecran.document());
            if (pointDeFocus.isEmpty()) {
                continue;
            }
            juges.add(ecran.nom());
            List<Element> arrets = arretsDeTabulation(ecran.document(), masquages);
            assertThat(arrets)
                    .as("ADR-0038 obligation 2 : zero arret de tabulation sur \"%s\" (%s)",
                            ecran.nom(), ecran.route())
                    .isNotEmpty();
            Element apres = arretApres(pointDeFocus.get(), ecran.document(), arrets);
            if (estDestructeur(apres, ecran.scenario())) {
                ecarts.add("clause 2 : sur \"" + ecran.nom() + "\" (" + ecran.route()
                        + "), l'arret qui suit le point de focus declare " + etiquette(apres)
                        + " detruit la partie en cours");
            }
        }

        assertThat(ecarts).as("ADR-0038 clause 2").isEmpty();
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de ce corpus ne declare de "
                        + "point de focus pendant qu'une partie est en cours")
                .isNotEmpty();
    }

    /**
     * Clause 4 (ADR-0040, point 5) : tout ecran a partie en cours declare un point de focus. Elle ne
     * juge pas ou va ce point de focus - les clauses 1 a 3 s'en chargent deja - elle juge seulement
     * qu'il en existe un. Avant ce lot, elle aurait ete rouge des sa premiere execution, sur le seul
     * ecran de confirmation : c'est la mesure de {@code nadia-prof} qui l'a fait naitre (57 arrivees
     * sur 69 ou la premiere Tabulation quittait le document).
     *
     * <p>Perimetre et critere derives, comme les trois clauses precedentes : le corpus vient de
     * vraies requetes sur de vraies sessions, "partie en cours" vient de
     * {@link EtatDePartieEnSession}, et le point de focus vient des marqueurs poses par le gabarit
     * ({@link #pointDeFocusDeclare}) - jamais d'une liste d'ecrans a verifier ecrite ici (ADR-0039,
     * ADR-0013).
     */
    @Test
    void toutEcranAPartieEnCoursDeclareUnPointDeFocus() throws Exception {
        List<String> juges = new ArrayList<>();
        List<String> ecarts = new ArrayList<>();

        for (Ecran ecran : ecrans()) {
            if (!partieEnCoursSur(ecran)) {
                continue;
            }
            juges.add(ecran.nom());
            if (pointDeFocusDeclare(ecran.document()).isEmpty()) {
                ecarts.add("clause 4 : sur \"" + ecran.nom() + "\" (" + ecran.route()
                        + "), aucun point de focus n'est declare");
            }
        }

        assertThat(ecarts).as("ADR-0040 clause 4").isEmpty();
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de ce corpus ne porte une "
                        + "partie en cours")
                .isNotEmpty();
    }

    /**
     * ADR-0013 obligation 3 pour la clause 4, "muter un objet existant prouve que le critere mord" :
     * le point de focus reellement declare par un ecran a partie en cours, retire du document que le
     * serveur a vraiment rendu, doit faire echouer la clause 4. Sur materiau reel et non sur une
     * fixture ecrite a la main (lecon de s05).
     */
    @Test
    void unEcranDontLePointDeFocusEstRetireFaitEchouerLaClause4() throws Exception {
        List<String> mutes = new ArrayList<>();

        for (Ecran ecran : ecrans()) {
            if (!partieEnCoursSur(ecran)) {
                continue;
            }
            Optional<Element> pointDeFocus = pointDeFocusDeclare(ecran.document());
            if (pointDeFocus.isEmpty()) {
                continue;
            }
            mutes.add(ecran.nom());
            pointDeFocus.get().removeAttr("data-focus-cible");
            ecran.document().select("[data-focus]").removeAttr("data-focus");

            assertThatThrownBy(() -> assertThat(pointDeFocusDeclare(ecran.document()))
                    .as("clause 4 : sur \"" + ecran.nom() + "\" (" + ecran.route()
                            + "), un point de focus doit etre declare")
                    .isPresent())
                    .isInstanceOf(AssertionError.class)
                    .hasMessageContaining("clause 4");
            break;
        }

        assertThat(mutes)
                .as("ADR-0003 : aucun ecran a partie en cours de ce corpus ne declare de point de "
                        + "focus - la clause 4 ne garderait plus aucun objet")
                .isNotEmpty();
    }

    /**
     * ADR-0003 : les deux clauses ci-dessus ne jugent que les ecrans a partie en cours - encore
     * faut-il que le corpus en porte au moins un, et au moins un qui n'en porte pas (sinon le filtre
     * ne filtre rien).
     */
    @Test
    void ecransAJugerNeContientPasQueDesEcransSansPartieEnCours() throws Exception {
        List<Ecran> ecrans = ecrans();
        List<Boolean> statuts = new ArrayList<>();
        for (Ecran ecran : ecrans) {
            statuts.add(partieEnCoursSur(ecran));
        }

        assertThat(statuts).contains(true);
        assertThat(statuts).contains(false);
    }

    /**
     * ADR-0013 obligation 1 : le perimetre des routes vient du controleur, jamais de la liste
     * ci-dessus. Une route neuve que ce garde-fou n'exerce pas le rend rouge.
     */
    @Test
    void chaqueRouteDuControleurEstExerceeParCeGardeFou() throws Exception {
        Set<String> exercees = new TreeSet<>();
        ecrans().forEach(ecran -> exercees.add(ecran.route()));

        assertThat(exercees)
                .as("ADR-0038 : toute route qui peut rendre un ecran entre dans ce corpus")
                .containsExactlyInAnyOrderElementsOf(routesDeclarees());
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
        information.getMethodsCondition().getMethods()
                .forEach(methode -> verbes.add(methode.name()));
        assertThat(verbes)
                .as("une route sans verbe declare repond a tous les verbes ; ce garde-fou ne saurait "
                        + "pas lesquels exercer")
                .isNotEmpty();
        return verbes;
    }

    /**
     * ADR-0013 obligation 3 : preuve de morsure sur un objet neuf, absent de la production - la
     * forme exacte que le serveur rendait avant ce lot sur l'ecran de manche en revelation quand la
     * reponse revelee occupe la derniere position de tabulation (table mesuree par theo-archi,
     * ADR-0038, section "Ce que la production rendrait aujourd'hui"). Ce document n'est plus
     * produit par le serveur depuis ce lot.
     *
     * <p>Aucun serveur n'est reconstruit pour mesurer "destructeur" sur ce jeu fautif : la mesure
     * est celle, documentee et datee, que ce lot a changee ({@code POST /retour} detruisait alors
     * la partie inconditionnellement). Reconstruire l'ancien controleur pour cette seule preuve
     * d'algorithme couterait plus que l'algorithme qu'elle eprouve.</p>
     */
    @Test
    void laFormeDeLaProductionAvantCeLotFaitEchouerLaClause1() throws Exception {
        Document fautif = Jsoup.parse(
                CONTRE_EXEMPLE.resolve("manche-revelation-avant-correctif.html").toFile(), "UTF-8");
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(RACINE_PRODUCTION);
        List<Element> arrets = arretsDeTabulation(fautif, masquages);

        assertThat(arrets).as("le jeu fautif doit fournir au moins un arret").isNotEmpty();
        assertThat(arrets.get(0).hasClass("retour-accueil"))
                .as("le premier arret doit etre la sortie, comme la production le rendait avant ce lot")
                .isTrue();

        assertThatThrownBy(() -> assertThat(destructeurAvantCorrectif(arrets.get(0)))
                .as("clause 1 : le premier arret ne doit pas detruire la partie en cours")
                .isFalse())
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("clause 1");
    }

    private static boolean destructeurAvantCorrectif(Element arret) {
        RequeteDeclaree requete = requeteDeclaree(arret).orElseThrow();
        return "POST".equals(requete.verbe()) && "/retour".equals(requete.url());
    }

    /** Le corpus doit vraiment contenir des ecrans, sinon les deux clauses ne prouveraient rien. */
    @Test
    void leCorpusNEstPasVide() throws Exception {
        assertThat(ecrans()).isNotEmpty();
    }
}
