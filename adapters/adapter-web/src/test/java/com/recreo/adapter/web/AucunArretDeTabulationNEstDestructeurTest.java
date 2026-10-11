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
 * <p><b>ADR-0040 y ajoute deux clauses de plus</b> : la troisieme dit que le point de focus
 * declare ne detruit pas lui-meme la partie - Entree seule, sans aucune Tabulation, depuis le
 * point de depart que le serveur choisit ; la quatrieme dit que ce point de depart existe toujours,
 * sur tout ecran a partie en cours. Les quatre ensemble disent une seule chose : depuis le point de
 * depart que le serveur declare - et il en declare toujours un (clause 4) - ni Entree seule
 * (clause 3) ni une Tabulation puis Entree (clause 2) ne detruit une partie en cours, quel que soit
 * le premier arret de tabulation (clause 1).
 *
 * <p>La clause 4 ferme la reserve N de {@code nadia-prof} : sur
 * {@code fragments/confirmation.html}, la premiere Tabulation quittait le document 57 fois sur 69
 * arrivees, faute de tout point de focus declare.
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
    /** S24 : le jeu de la seconde mecanique du catalogue de test (PartieControllerTest). */
    private static final String JEU_SAISIE_ID = PartieControllerTest.JEU_SAISIE_ID;

    /** ADR-0080 : le jeu de la troisieme mecanique du catalogue de test (PartieControllerTest). */
    private static final String JEU_DESIGNATION_ID = PartieControllerTest.JEU_DESIGNATION_ID;

    private static final String JEU_REPETE_ID = "jeu-repete";
    private static final String PROPOSITION_FAUSSE = "mauvaise";

    /** Le seul niveau du catalogue de test (PartieControllerTest.JeuDeTestConfiguration). */
    private static final String NIVEAU = "CP";

    /** Le segment de la seule matiere du catalogue de test (PartieControllerTest.JeuDeTestConfiguration). */
    private static final String SEGMENT = "maths";

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

        // S24, ADR-0058 : les deux routes de la seconde mecanique. SEUIL-4 est la condition de
        // nadia-prof qui mord ici - "si le champ de saisie n'est pas atteignable et validable au
        // clavier seul, la saisie ne doit pas exister" - et le clavier est le seul geste depuis
        // l'arbitrage du 2026-08-25. Le champ est un arret de tabulation de plus, et il entre dans
        // ce corpus par le meme selecteur que les autres.
        ecrans.add(ecranFragment("manche de saisie apres une reponse fausse, la partie continue",
                "POST /parties/{jeuId}/reponses", session -> {
                    corps(post("/parties/{jeuId}", JEU_SAISIE_ID).session(session));
                    return corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "3"));
                }));

        ecrans.add(ecranFragment("manche de saisie en revelation, le champ a disparu",
                "POST /parties/{jeuId}/reponses", session -> {
                    corps(post("/parties/{jeuId}", JEU_SAISIE_ID).session(session));
                    corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "1"));
                    corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "2"));
                    return corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "3"));
                }));

        ecrans.add(ecranFragment("manche de saisie suivante, apres le geste de suite",
                "POST /parties/{jeuId}/manche", session -> {
                    corps(post("/parties/{jeuId}", JEU_SAISIE_ID).session(session));
                    corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "1"));
                    corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "2"));
                    corps(post("/parties/{jeuId}/reponses", JEU_SAISIE_ID).session(session)
                            .param("reponseEcrite", "3"));
                    return corps(post("/parties/{jeuId}/manche", JEU_SAISIE_ID).session(session));
                }));

        // ADR-0080 : la route de la troisieme mecanique. Meme motif que la saisie ci-dessus - une
        // zone designable est un arret de tabulation de plus (garde-fou 1, ADR-0080).
        ecrans.add(ecranFragment("manche de designation apres une designation fausse, la partie continue",
                "POST /parties/{jeuId}/designations", session -> {
                    corps(post("/parties/{jeuId}", JEU_DESIGNATION_ID).session(session));
                    return corps(post("/parties/{jeuId}/designations", JEU_DESIGNATION_ID)
                            .session(session).param("zoneDesignee", "porte"));
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

        // S14-1 (ADR-0043, ADR-0044) : deux routes neuves, le point d'entree par niveau.
        ecrans.add(ecranFragment("liste filtree par niveau, aucune partie en cours",
                "GET /niveaux/{niveau}",
                session -> corps(get("/niveaux/{niveau}", NIVEAU).session(session))));

        ecrans.add(ecranFragment("demande de confirmation avant de changer de niveau, la partie continue",
                "GET /niveaux/{niveau}", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    return corps(get("/niveaux/{niveau}", NIVEAU).session(session));
                }));

        ecrans.add(ecranFragment("confirmation du changement de niveau, retour a la liste filtree",
                "POST /niveaux/{niveau}/confirmer", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    corps(get("/niveaux/{niveau}", NIVEAU).session(session));
                    return corps(post("/niveaux/{niveau}/confirmer", NIVEAU).session(session));
                }));

        // Hors sprint accueil par matiere (ADR-0105, ADR-0106 decision 8, F10) : les routes neuves, avec et sans
        // partie en cours.
        ecrans.add(ecranFragment("bascule vers V1, aucune partie en cours", "POST /niveaux/{niveau}/vue/{vue}",
                session -> corps(post("/niveaux/{niveau}/vue/{vue}", NIVEAU, "V1").session(session))));

        ecrans.add(ecranFragment("bascule pendant une partie : la confirmation de la puce de niveau",
                "POST /niveaux/{niveau}/vue/{vue}", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    return corps(post("/niveaux/{niveau}/vue/{vue}", NIVEAU, "V1").session(session));
                }));

        ecrans.add(ecranFragment("etage 2 d'une matiere, aucune partie en cours",
                "GET /niveaux/{niveau}/matieres/{segment}",
                session -> corps(get("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT).session(session))));

        ecrans.add(ecranFragment("etage 2 pendant une partie : la confirmation directe",
                "GET /niveaux/{niveau}/matieres/{segment}", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    return corps(get("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT).session(session));
                }));

        ecrans.add(ecranFragment("confirmation de l'etage 2, retour a l'etage 2 demande",
                "POST /niveaux/{niveau}/matieres/{segment}/confirmer", session -> {
                    corps(post("/parties/{jeuId}", JEU_ID).session(session));
                    corps(get("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT).session(session));
                    return corps(post("/niveaux/{niveau}/matieres/{segment}/confirmer", NIVEAU, SEGMENT)
                            .session(session));
                }));

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
        // S24, ADR-0058 : un arret de tabulation peut desormais etre un CHAMP et non un declencheur.
        // Un <input> n'envoie rien de lui-meme ; ce qu'il peut faire partir, c'est la requete de son
        // formulaire, avec sa propre valeur dedans. Cette branche ne teste donc plus type="submit" :
        // elle juge tout arret vivant dans un formulaire qui declare une requete SUR CETTE REQUETE,
        // qu'il la declenche ou qu'il la nourrisse. Le bouton de soumission reste le cas particulier
        // ou nom et valeur designent le choix presse ; le champ est le cas general.
        //
        // Ce que cela ne relache pas : un arret qui ne declare rien ET ne vit dans aucun formulaire
        // qui declare quelque chose fait toujours echouer l'obligation 2 ci-dessous. Le silence d'un
        // bouton dont le hx-post aurait disparu reste rouge - ce que ce garde-fou existe pour
        // attraper. Ce que la branche ajoute est un cas ou l'arret a bien une requete lisible, mais
        // portee par son formulaire plutot que par lui-meme.
        Element formulaire = arret.closest("form");
        if (formulaire != null && formulaire.hasAttr("hx-post")) {
            Map<String, String> parametres = new LinkedHashMap<>();
            String nom = arret.attr("name");
            if (!nom.isBlank()) {
                parametres.put(nom, arret.attr("value"));
            }
            return Optional.of(new RequeteDeclaree("POST", formulaire.attr("hx-post"), parametres));
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

    /**
     * Le point de focus declare <b>quand il declare lui-meme une requete</b> : c'est le seul cas ou
     * Entree, pressee sans aucune Tabulation, peut agir depuis le point de depart que le serveur
     * choisit (ADR-0040, clause 3). Un point de focus qui ne declare aucune requete - l'{@code
     * .enonce} d'une manche neuve, un paragraphe - n'est pas juge : rien a activer.
     *
     * <p>Ce critere est volontairement plus large que l'activation reelle d'un navigateur : un
     * element qui declare une requete sans etre activable au clavier serait juge ici alors qu'Entree
     * n'y declencherait rien. Plus strict que la realite, jamais plus lache (ADR-0040, section "Ce
     * que cette clause ne prouve pas").
     */
    private static Optional<Element> pointDeFocusActivable(Document document) {
        return pointDeFocusDeclare(document).filter(point -> requeteDeclaree(point).isPresent());
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
     * Clause 3 (ADR-0040) : sur tout ecran a partie en cours, le point de focus que le serveur
     * declare ne detruit pas lui-meme cette partie. La clause 2 juge l'arret d'apres - Tabulation
     * puis Entree ; celle-ci juge le point de depart - <b>Entree seule, sans aucune Tabulation</b>.
     *
     * <p>Elle n'existait pas avant s12 parce qu'aucun point de focus ne pouvait etre destructeur :
     * jusqu'a l'ecran de confirmation, la seule requete destructrice de l'application etait celle
     * d'une sortie, qu'aucun gabarit ne designe comme point de focus. Mesure du 2026-08-14, sur le
     * depot et non en raisonnement : {@code data-focus-cible="true"} pose sur le bouton
     * destructeur de {@code fragments/confirmation.html} laissait les six tests d'alors, et les 222
     * du module, entierement verts.
     */
    @Test
    void aucunPointDeFocusDeclareNeDetruitLuiMemeUnePartieEnCours() throws Exception {
        List<String> juges = new ArrayList<>();
        List<String> ecarts = new ArrayList<>();

        for (Ecran ecran : ecrans()) {
            if (!partieEnCoursSur(ecran)) {
                continue;
            }
            Optional<Element> pointDeFocus = pointDeFocusActivable(ecran.document());
            if (pointDeFocus.isEmpty()) {
                continue;
            }
            juges.add(ecran.nom());
            if (estDestructeur(pointDeFocus.get(), ecran.scenario())) {
                ecarts.add("clause 3 : sur \"" + ecran.nom() + "\" (" + ecran.route()
                        + "), le point de focus declare " + etiquette(pointDeFocus.get())
                        + " detruit lui-meme la partie en cours - Entree suffit, sans Tabulation");
            }
        }

        assertThat(ecarts).as("ADR-0040 clause 3").isEmpty();
        assertThat(juges)
                .as("ADR-0003 : cette clause ne prouve rien si aucun ecran de ce corpus ne declare un "
                        + "point de focus qui porte une requete pendant qu'une partie est en cours")
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
     * S18, S17-C : ce que le mecanisme retenu par theo-archi rend derivable (avis-faisabilite.md,
     * section 4.3, motif 3). Avant ce lot, {@link #arretsDeTabulation} lisait un document assemble
     * qui ne portait jamais l'attribut que {@code recreo.js} n'ajoutait qu'au moment du focus reel :
     * le calcul etait juste mais aveugle au defaut mesure par {@code nadia-prof} (le bouton nomme
     * par le message de revelation sortait de l'ordre de tabulation apres le focus, dans un DOM que
     * ce garde-fou ne voit jamais). Depuis ce lot, {@code tabindex="-1"} n'est plus mute par le
     * script : le document que le serveur rend <b>est</b> celui que le navigateur affichera, donc la
     * cause declarative de la clause 1 de M13 (le bouton reste un arret de tabulation) est desormais
     * verifiable ici, sur le meme materiau que les quatre clauses d'ADR-0038/ADR-0040.
     *
     * <p><b>Ce que ce test ne pretend pas prouver</b> : que Tabulation, au clavier, dans un vrai
     * navigateur, atteint reellement ce bouton (lecture de pile d'accessibilite, ordre effectif) -
     * cela reste la seule verification de {@code nadia-prof} (avis-faisabilite.md, section 4.4).
     * Ce test prouve seulement que la cause qui faisait echouer cette mesure a disparu du materiau
     * que le serveur sert.
     */
    @Test
    void surLaMancheEnRevelationLeBoutonNommeParLeMessageResteUnArretDeTabulation() throws Exception {
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(RACINE_PRODUCTION);

        Ecran revelation = ecrans().stream()
                .filter(ecran -> ecran.nom().equals("manche en revelation, la partie continue"))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "le corpus doit porter l'ecran de revelation pour que ce test juge quelque chose"));

        Element boutonRevele = pointDeFocusDeclare(revelation.document()).orElseThrow(() ->
                new AssertionError("l'ecran de revelation doit declarer un point de focus"));
        assertThat(boutonRevele.tagName())
                .as("le point de focus de la revelation doit etre le bouton nomme, pas l'enonce")
                .isEqualTo("button");

        List<Element> arrets = arretsDeTabulation(revelation.document(), masquages);
        assertThat(arrets)
                .as("S17-C : le bouton nomme par le message de revelation doit rester un arret de "
                        + "tabulation du document servi")
                .contains(boutonRevele);
    }

    /**
     * ADR-0013 obligation 3 : preuve de morsure du test precedent, sur le meme document reel - jamais
     * une fixture ecrite a la main. Reproduit exactement le defaut d'avant ce lot ({@code recreo.js}
     * posait {@code tabindex="-1"} meme sur une cible deja focalisable) en mutant le seul attribut que
     * le script mutait, sur l'objet que le serveur a vraiment rendu.
     */
    @Test
    void unTabindexNegatifSurLeBoutonReveleFaitEchouerCeteEpreuve() throws Exception {
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(RACINE_PRODUCTION);

        Ecran revelation = ecrans().stream()
                .filter(ecran -> ecran.nom().equals("manche en revelation, la partie continue"))
                .findFirst()
                .orElseThrow();
        Element boutonRevele = pointDeFocusDeclare(revelation.document()).orElseThrow();
        boutonRevele.attr("tabindex", "-1");

        List<Element> arrets = arretsDeTabulation(revelation.document(), masquages);

        assertThatThrownBy(() -> assertThat(arrets)
                .as("S17-C : le bouton nomme par le message de revelation doit rester un arret de "
                        + "tabulation du document servi")
                .contains(boutonRevele))
                .isInstanceOf(AssertionError.class);
    }

    /**
     * ADR-0013 obligation 3 pour la clause 3, sur du materiau reel plutot que sur une fixture de ma
     * main (lecon de s05) : le document que le serveur rend vraiment, avec son point de focus
     * <b>deplace</b> sur un arret destructeur du meme ecran. Deplace et non ajoute, pour que cette
     * epreuve reste valable le jour ou l'ecran de confirmation declarera enfin un point de focus.
     *
     * <p>La population n'est pas nommee ici : elle est derivee - tout ecran a partie en cours qui
     * offre au moins un arret destructeur. Qu'elle soit non vide est la mesure d'anti-vacuite de
     * toute la famille ADR-0038 : le jour ou aucun ecran a partie en cours n'offrirait plus de
     * requete destructrice, les trois clauses ne garderaient plus aucun objet, et ce test le dirait.
     */
    @Test
    void unPointDeFocusDeplaceSurUnArretDestructeurFaitEchouerLaClause3() throws Exception {
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(RACINE_PRODUCTION);
        List<String> mutes = new ArrayList<>();

        for (Ecran ecran : ecrans()) {
            if (!partieEnCoursSur(ecran)) {
                continue;
            }
            for (Element arret : arretsDeTabulation(ecran.document(), masquages)) {
                if (!estDestructeur(arret, ecran.scenario())) {
                    continue;
                }
                ecran.document().select("[data-focus-cible]").removeAttr("data-focus-cible");
                arret.attr("data-focus-cible", "true");
                Element mute = pointDeFocusActivable(ecran.document()).orElseThrow(() ->
                        new AssertionError("le point de focus deplace doit etre relu sur \""
                                + ecran.nom() + "\", sans quoi cette epreuve ne prouve rien"));
                mutes.add(ecran.nom());

                assertThatThrownBy(() -> assertThat(estDestructeur(mute, ecran.scenario()))
                        .as("clause 3 : le point de focus declare ne detruit pas lui-meme la partie")
                        .isFalse())
                        .isInstanceOf(AssertionError.class)
                        .hasMessageContaining("clause 3");
                break;
            }
        }

        assertThat(mutes)
                .as("ADR-0003 : aucun ecran a partie en cours n'offre d'arret destructeur - les trois "
                        + "clauses d'ADR-0038 et d'ADR-0040 ne gardent alors plus aucun objet")
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
     * S14-2, correction du Done pedagogique refuse par nadia-prof (BARRE-1, BARRE-2) : retiree
     * avant d'avoir ete commitee. <b>Motif ecrit</b>, exige par la relecture de theo-archi
     * (ADR-0022, troisieme amendement, section 7.2) : "deux boites" et "une boite" ne sont pas des
     * proprietes, ce sont les contenus du jour de deux ecrans - les ecrire dans une assertion
     * referait a chaque arbitrage futur de nadia-prof ce que l'oscillation de la clause 2
     * d'ADR-0022 vient de couter au sprint. La propriete que ces deux chiffres visaient - aucun
     * element actionnable permanent ne survit sur un ecran exclusif - est portee, sans aucun
     * nombre, par
     * {@code LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.verifierQuAucunActionnablePermanentNeSurvitSurUnEcranExclusif}
     * (clause B).
     */

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
     *
     * <p>S14-2 (BARRE-7, DEC-0033), amendement : le masquage d'ADR-0022 se lisait jusqu'ici dans
     * la feuille de <b>production</b> ({@code RACINE_PRODUCTION}), qui ne le porte plus depuis que
     * S14-2 retire le lien "Les jeux" de l'en-tete (et sa regle de masquage avec lui). Cette
     * epreuve rejoue une regression figee dans le temps, independante de ce que la production
     * porte aujourd'hui ; la recoupler a une feuille qui evolue pour d'autres motifs que le sien
     * l'aurait fait rougir pour un defaut qui n'est pas celui qu'elle nomme. Le masquage se lit
     * donc desormais dans un fixture fige a cote du gabarit fautif
     * ({@code masquage-avant-correctif/static/css/recreo.css}), forme exacte de la regle
     * qu'ADR-0022 declarait a la date de cette regression - <b>propose pour confirmation ecrite de
     * theo-archi (via sofia-sm)</b>, au meme titre que les trois autres garde-fous que ce lot
     * touche.</p>
     */
    @Test
    void laFormeDeLaProductionAvantCeLotFaitEchouerLaClause1() throws Exception {
        Document fautif = Jsoup.parse(
                CONTRE_EXEMPLE.resolve("manche-revelation-avant-correctif.html").toFile(), "UTF-8");
        List<LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.Masquage> masquages =
                LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest.masquages(
                        CONTRE_EXEMPLE.resolve("masquage-avant-correctif"));
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
