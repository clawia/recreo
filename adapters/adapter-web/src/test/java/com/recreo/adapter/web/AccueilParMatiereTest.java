package com.recreo.adapter.web;

import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.Matiere;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>Hors sprint accueil par matiere (DEC-0117, ADR-0105, ADR-0106, conditions APM-1 a APM-11 de nadia-prof) :
 * les routes et les gabarits de l'accueil, jouees par le vrai controleur.</b> Le catalogue de ce fichier est
 * fait pour que chaque propriete ait de quoi mordre : deux matieres dont l'ordre de la donnee differe de l'ordre
 * du catalogue (l'ordre est celui des lignes, APM-4), une matiere de trois jeux et une d'un seul (APM-10), et un
 * second niveau dont les jeux ne doivent paraitre nulle part sous le premier.
 *
 * <p>Ce que ce fichier ne juge pas : l'aspect (feuille de style, taille des cibles : {@code
 * TailleDesTextesEtBoutonsTest} et les garde-fous d'habillage), la mesure de defilement et l'audit des mots et
 * des images, qui sont a {@code yanis-qa} et a {@code nadia-prof}. Les corpus de routes sont ceux d'ADR-0038
 * ({@code ToutEcranEchangeEstUnDocumentJouableTest} et ses deux homologues), qui exercent chaque route avec et
 * sans partie.</p>
 */
@WebMvcTest(PartieController.class)
@Import(AccueilParMatiereTest.JeuxDeTestConfiguration.class)
class AccueilParMatiereTest {

    private static final String NIVEAU = "CP";
    private static final String AUTRE_NIVEAU = "CE1";

    private static final String MATIERE_A_UN_SEUL_JEU = "Francais";
    private static final String MATIERE_DE_TROIS_JEUX = "Mathematiques";

    /** Les lignes de donnee de ce catalogue de test, dans l'ordre d'affichage : Francais avant Mathematiques. */
    private static final String SEGMENT_A_UN_SEUL_JEU = "francais";
    private static final String SEGMENT_DE_TROIS_JEUX = "maths";

    private static final String JEU_EN_COURS = "cp-maths-1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private Document page(String url, Object... variables) throws Exception {
        return Jsoup.parse(mockMvc.perform(get(url, variables)).andReturn().getResponse().getContentAsString());
    }

    private Document page(MockHttpSession session, String url, Object... variables) throws Exception {
        return Jsoup.parse(mockMvc.perform(get(url, variables).session(session))
                .andReturn().getResponse().getContentAsString());
    }

    private MockHttpServletResponse basculer(MockHttpSession session, String niveau, String vue) throws Exception {
        return mockMvc.perform(post("/niveaux/{niveau}/vue/{vue}", niveau, vue).session(session))
                .andReturn().getResponse();
    }

    private static Document fragment(MockHttpServletResponse reponse) throws Exception {
        return Jsoup.parse(reponse.getContentAsString());
    }

    private void demarrerUnePartie(MockHttpSession session) throws Exception {
        mockMvc.perform(post("/parties/{jeuId}", JEU_EN_COURS).session(session));
    }

    private static EtatDePartie etat(MockHttpSession session) {
        org.springframework.mock.web.MockHttpServletRequest requete =
                new org.springframework.mock.web.MockHttpServletRequest();
        requete.setSession(session);
        return new EtatDePartieEnSession(requete).lire();
    }

    private static VueDeLAccueil vue(MockHttpSession session) {
        org.springframework.mock.web.MockHttpServletRequest requete =
                new org.springframework.mock.web.MockHttpServletRequest();
        requete.setSession(session);
        return new EtatDePartieEnSession(requete).vue();
    }

    private static List<String> titresDesCartes(Document document) {
        return document.select("button.demarrer").eachText();
    }

    // ------------------------------------------------------------------------------------------
    // Q-U1 : "/" demande le niveau d'abord
    // ------------------------------------------------------------------------------------------

    @Test
    void laRacineDemandeLeNiveauEtNeRendAucuneListeDeJeux() throws Exception {
        Document racine = page("/");

        assertThat(racine.select(".choix-niveau")).hasSize(1);
        assertThat(racine.select("button.demarrer, .tuile, .entete-matiere, .bascule"))
                .as("Q-U1 : aucune liste de jeux, aucune matiere, aucun bouton de vue sans niveau").isEmpty();
        assertThat(racine.select("a.chip-niveau").eachText())
                .as("les puces de l'en-tete sont le choix du niveau").containsExactly(NIVEAU, AUTRE_NIVEAU);
    }

    // ------------------------------------------------------------------------------------------
    // V2, la vue par defaut : un bandeau par matiere, ses jeux dessous
    // ------------------------------------------------------------------------------------------

    @Test
    void sansChoixLaVueEstV2UnBandeauParMatiereDansL_ordreDesLignesDeDonnee() throws Exception {
        Document niveau = page("/niveaux/{niveau}", NIVEAU);

        List<String> libelles = niveau.select(".entete-matiere span").eachText();
        assertThat(libelles).as("APM-4 : l'ordre des lignes de donnee, pas celui du catalogue")
                .containsExactly("Français", "Maths");
        assertThat(niveau.select(".tuile")).as("pas d'etage 1 : la vue par defaut est V2").isEmpty();
    }

    @Test
    void chaqueJeuDuNiveauParaitUneFoisDansLeGroupeDeSaMatiereEtAucunJeuD_unAutreNiveau() throws Exception {
        Document niveau = page("/niveaux/{niveau}", NIVEAU);

        List<String> attendus = partie.jeuxDuNiveau(NIVEAU).stream().map(GameDefinition::titre).toList();
        assertThat(titresDesCartes(niveau)).containsExactlyInAnyOrderElementsOf(attendus);
        assertThat(new HashSet<>(niveau.select("button.demarrer").eachAttr("hx-post")))
                .as("aucun identifiant deux fois").hasSize(attendus.size());
        assertThat(titresDesCartes(niveau)).doesNotContain("Jeu CE1");

        List<Element> blocs = niveau.select(".bloc-matiere");
        assertThat(blocs).hasSize(2);
        assertThat(blocs).as("pas de groupe vide (DEC-0032 section 3)")
                .allSatisfy(bloc -> assertThat(bloc.select("button.demarrer")).isNotEmpty());
        assertThat(blocs.get(0).select("button.demarrer").eachText()).containsExactly("Jeu Francais");
        assertThat(blocs.get(1).select("button.demarrer").eachText())
                .containsExactly("Jeu Maths 1", "Jeu Maths 2", "Jeu Maths 3");
    }

    @Test
    void lOrdreDesGroupesEstStableD_unChargementAL_autre() throws Exception {
        assertThat(page("/niveaux/{niveau}", NIVEAU).select(".entete-matiere span").eachText())
                .isEqualTo(page("/niveaux/{niveau}", NIVEAU).select(".entete-matiere span").eachText());
    }

    /** APM-3 : le pictogramme n'est jamais seul et ne parle pas ; le mot est un noeud de texte du bandeau. */
    @Test
    void chaqueBandeauPorteSonMotEnTexteEtSonPictogrammeSansTexteAlternatif() throws Exception {
        Document niveau = page("/niveaux/{niveau}", NIVEAU);

        for (Element bandeau : niveau.select(".entete-matiere")) {
            assertThat(bandeau.select("span").text()).isNotBlank();
            Element image = bandeau.selectFirst("img");
            assertThat(image).isNotNull();
            assertThat(image.attr("alt")).isEmpty();
            assertThat(image.attr("src")).startsWith("/images/matieres/").endsWith(".svg");
            assertThat(bandeau.attr("style")).contains("--teinte-fond").contains("--teinte-contour");
        }
    }

    // ------------------------------------------------------------------------------------------
    // Le bouton de vue : APM-7
    // ------------------------------------------------------------------------------------------

    @Test
    void unSeulBoutonDeVueDansLaZoneDeLAccueilJamaisDansL_enTeteEtSonLibelleDitOuIlMene() throws Exception {
        Document v2 = page("/niveaux/{niveau}", NIVEAU);
        assertThat(v2.select("#jeu .bascule")).hasSize(1);
        assertThat(v2.select("header .bascule")).as("APM-7 : jamais dans l'en-tete").isEmpty();
        assertThat(v2.selectFirst(".bascule span").text()).isEqualTo("Par matière");
        assertThat(v2.selectFirst(".bascule").attr("data-role")).isEqualTo("entree");
        assertThat(v2.selectFirst(".bascule").attr("hx-post")).isEqualTo("/niveaux/CP/vue/V1");
        assertThat(v2.selectFirst(".bascule").attr("hx-target")).isEqualTo("#jeu");

        MockHttpSession session = new MockHttpSession();
        Document etage1 = fragment(basculer(session, NIVEAU, "V1"));
        assertThat(etage1.select(".bascule")).hasSize(1);
        assertThat(etage1.selectFirst(".bascule span").text()).isEqualTo("Tout voir");
        assertThat(etage1.selectFirst(".bascule").attr("hx-post")).isEqualTo("/niveaux/CP/vue/V2");

        Document etage2 = page("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_DE_TROIS_JEUX);
        assertThat(etage2.select("#jeu .bascule")).hasSize(1);
        assertThat(etage2.selectFirst(".bascule span").text()).isEqualTo("Par matière");
        assertThat(etage2.selectFirst(".bascule").attr("hx-post")).isEqualTo("/niveaux/CP/vue/V1");
    }

    @Test
    void leBoutonDeVueNePorteAucuneMarqueDEtatNiLeMotLesJeux() throws Exception {
        for (Document document : List.of(page("/niveaux/{niveau}", NIVEAU),
                fragment(basculer(new MockHttpSession(), NIVEAU, "V1")),
                page("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_A_UN_SEUL_JEU))) {
            Element bouton = document.selectFirst(".bascule");
            assertThat(bouton.text()).doesNotContain("Les jeux").doesNotContain("✓").doesNotContain("✔");
            assertThat(bouton.hasAttr("aria-pressed")).as("APM-7 : aucune marque d'etat").isFalse();
            assertThat(bouton.className()).doesNotContain("est-actif");
        }
    }

    @Test
    void auPlusUnLesJeuxVisibleSurChaqueEcranNeufDeLAccueil() throws Exception {
        for (Document document : List.of(page("/niveaux/{niveau}", NIVEAU),
                fragment(basculer(new MockHttpSession(), NIVEAU, "V1")),
                page("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_DE_TROIS_JEUX))) {
            long occurrences = document.getAllElements().stream()
                    .filter(element -> element.ownText().strip().equals("Les jeux")).count();
            assertThat(occurrences).as("BARRE-5 : un seul mot de sortie").isLessThanOrEqualTo(1);
        }
    }

    @Test
    void leBoutonDeVueEstAbsentDeTousLesEcransDeJeu() throws Exception {
        MockHttpSession session = new MockHttpSession();
        Document manche = Jsoup.parse(mockMvc.perform(post("/parties/{jeuId}", JEU_EN_COURS).session(session))
                .andReturn().getResponse().getContentAsString());
        assertThat(manche.select(".bascule, .tuile, .entete-matiere")).isEmpty();

        Document reprise = page(session, "/");
        assertThat(reprise.select("#jeu .bascule, #jeu .tuile, #jeu .entete-matiere")).isEmpty();

        Document confirmation = Jsoup.parse(mockMvc.perform(post("/retour").session(session))
                .andReturn().getResponse().getContentAsString());
        assertThat(confirmation.select(".question-confirmation")).isNotEmpty();
        assertThat(confirmation.select(".bascule, .tuile, .entete-matiere")).isEmpty();
    }

    // ------------------------------------------------------------------------------------------
    // La route de vue : ecrit la vue portee, n'abandonne jamais une partie, 404 sur une vue inconnue
    // ------------------------------------------------------------------------------------------

    @Test
    void basculerVersV1RendLEtageUnEtPousseLAdresseDuNiveau() throws Exception {
        MockHttpSession session = new MockHttpSession();

        MockHttpServletResponse reponse = basculer(session, NIVEAU, "V1");
        Document etage1 = fragment(reponse);

        assertThat(reponse.getStatus()).isEqualTo(200);
        assertThat(reponse.getHeader("HX-Push-Url")).isEqualTo("/niveaux/CP");
        assertThat(etage1.select(".tuile")).hasSize(2);
        assertThat(etage1.select(".filtre-niveau").text()).isEqualTo("Pour le CP");
        assertThat(vue(session)).isEqualTo(VueDeLAccueil.V1);
        assertThat(page(session, "/niveaux/{niveau}", NIVEAU).select(".tuile"))
                .as("la vue vient de la session : un nouveau chargement reste en V1").hasSize(2);
    }

    @Test
    void unDoubleAppuiRendDeuxFoisLeMemeEcran() throws Exception {
        MockHttpSession session = new MockHttpSession();

        String premier = basculer(session, NIVEAU, "V1").getContentAsString();
        String second = basculer(session, NIVEAU, "V1").getContentAsString();

        assertThat(second).isEqualTo(premier);
        assertThat(vue(session)).isEqualTo(VueDeLAccueil.V1);
        assertThat(fragment(basculer(session, NIVEAU, "V2")).select(".entete-matiere")).hasSize(2);
        assertThat(vue(session)).isEqualTo(VueDeLAccueil.V2);
    }

    @Test
    void uneVueInconnueRend404EtN_ecritRienDansLaSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        basculer(session, NIVEAU, "V1");

        for (String inconnue : List.of("V3", "v0", "toutes", "1")) {
            MockHttpServletResponse reponse = basculer(session, NIVEAU, inconnue);
            assertThat(reponse.getStatus()).as("vue %s", inconnue).isEqualTo(404);
            assertThat(reponse.getHeader("HX-Push-Url")).isNull();
        }
        assertThat(vue(session)).as("la session garde la vue qu'elle avait").isEqualTo(VueDeLAccueil.V1);
    }

    /** APM-11 : la route de vue n'abandonne jamais une partie ; elle rend la confirmation de la puce de niveau. */
    @Test
    void basculerPendantUnePartieRendLaConfirmationDeLaPuceEtNAbandonneRien() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrerUnePartie(session);
        EtatDePartie avant = etat(session);

        MockHttpServletResponse reponse = basculer(session, NIVEAU, "V1");
        Document confirmation = fragment(reponse);

        assertThat(confirmation.select(".question-confirmation")).hasSize(1);
        assertThat(confirmation.select(".retour-accueil").attr("hx-post")).isEqualTo("/niveaux/CP/confirmer");
        assertThat(reponse.getHeader("HX-Push-Url")).as("ADR-0048 : l'adresse ne ment pas").isNull();
        EtatDePartie apres = etat(session);
        assertThat(apres.portePartieEnCours()).isTrue();
        assertThat(apres.jeuId()).isEqualTo(avant.jeuId());
        assertThat(apres.session()).isSameAs(avant.session());
        assertThat(vue(session)).as("la vue est ecrite, la partie intacte").isEqualTo(VueDeLAccueil.V1);
    }

    @Test
    void confirmerLeChangementDeNiveauRendLaVueRetenueDeLaSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrerUnePartie(session);
        basculer(session, NIVEAU, "V1");

        Document apres = Jsoup.parse(mockMvc.perform(post("/niveaux/{niveau}/confirmer", NIVEAU).session(session))
                .andReturn().getResponse().getContentAsString());

        assertThat(etat(session).portePartieEnCours()).isFalse();
        assertThat(apres.select(".tuile")).hasSize(2);
        assertThat(apres.select(".filtre-niveau").text()).isEqualTo("Pour le CP");
    }

    // ------------------------------------------------------------------------------------------
    // V1 : etage 1 (tuiles) et etage 2 (cartes d'une matiere)
    // ------------------------------------------------------------------------------------------

    /** APM-10 : une tuile ouvre toujours l'etage 2, meme d'un seul jeu ; elle ne lance jamais un jeu. */
    @Test
    void chaqueTuilePointeVersLEtageDeuxDeSaMatiereEtNeLanceJamaisUnJeu() throws Exception {
        Document etage1 = fragment(basculer(new MockHttpSession(), NIVEAU, "V1"));

        List<Element> tuiles = etage1.select(".tuile");
        assertThat(tuiles).hasSize(2);
        assertThat(tuiles.stream().map(tuile -> tuile.attr("href")).toList())
                .containsExactly("/niveaux/CP/matieres/francais", "/niveaux/CP/matieres/maths");
        for (Element tuile : tuiles) {
            assertThat(tuile.nodeName()).isEqualTo("a");
            assertThat(tuile.attr("data-role")).isEqualTo("action");
            assertThat(tuile.hasAttr("hx-post")).as("une tuile est un lien, pas un lancement").isFalse();
            assertThat(tuile.select("span").text()).isNotBlank();
            assertThat(tuile.selectFirst("img").attr("alt")).isEmpty();
            assertThat(tuile.attr("style")).contains("--teinte-fond");
        }
        assertThat(etage1.select("button.demarrer")).as("l'etage 1 ne porte aucun jeu").isEmpty();
        assertThat(etage1.select(".tuile").eachText()).containsExactly("Français", "Maths");
    }

    @Test
    void lEtageDeuxRendTousLesJeuxDeLaMatiereEtRienQueSesJeuxQuelleQueSoitLaVueDeLaSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        assertThat(vue(session)).isEqualTo(VueDeLAccueil.V2);

        Document etage2 = page(session, "/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_DE_TROIS_JEUX);

        assertThat(etage2.select(".entete-matiere span").eachText()).containsExactly("Maths");
        assertThat(etage2.select("button.demarrer").eachText())
                .containsExactly("Jeu Maths 1", "Jeu Maths 2", "Jeu Maths 3");
        assertThat(etage2.select(".filtre-niveau").text()).isEqualTo("Pour le CP");
        assertThat(vue(session)).as("APM-8 : l'adresse gouverne, rien n'est ecrit en session")
                .isEqualTo(VueDeLAccueil.V2);
        assertThat(etat(session).portePartieEnCours()).isFalse();
    }

    @Test
    void unEtageDeuxD_unSeulJeuNeParaitPasCasse() throws Exception {
        Document etage2 = page("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_A_UN_SEUL_JEU);

        assertThat(etage2.select(".entete-matiere span").eachText()).containsExactly("Français");
        assertThat(etage2.select("button.demarrer").eachText()).containsExactly("Jeu Francais");
        assertThat(etage2.select(".pas-de-jeu")).isEmpty();
    }

    @Test
    void sommeDesEtagesDeuxEgaleLesJeuxDuNiveauEtAucuneTuileSansJeu() throws Exception {
        List<String> tousLesJeux = new ArrayList<>();
        for (Element tuile : fragment(basculer(new MockHttpSession(), NIVEAU, "V1")).select(".tuile")) {
            List<String> titres = page(tuile.attr("href")).select("button.demarrer").eachText();
            assertThat(titres).as("DEC-0032 section 3 : aucune tuile sans carte").isNotEmpty();
            tousLesJeux.addAll(titres);
        }

        assertThat(tousLesJeux).containsExactlyInAnyOrderElementsOf(
                partie.jeuxDuNiveau(NIVEAU).stream().map(GameDefinition::titre).toList());
    }

    @Test
    void unSegmentInconnuOuUneMatiereD_unAutreNiveauRendLeNiveauSansJeu() throws Exception {
        for (Document document : List.of(
                page("/niveaux/{niveau}/matieres/{segment}", NIVEAU, "philosophie"),
                page("/niveaux/{niveau}/matieres/{segment}", AUTRE_NIVEAU, SEGMENT_A_UN_SEUL_JEU))) {
            assertThat(document.select(".pas-de-jeu").text()).isEqualTo("Il n'y a pas de jeu pour le moment.");
            assertThat(document.select("button.demarrer, .bascule")).isEmpty();
        }
    }

    // ------------------------------------------------------------------------------------------
    // L'etage 2 pendant une partie : la confirmation de la puce, puis l'etage demande (APM-11)
    // ------------------------------------------------------------------------------------------

    @Test
    void lEtageDeuxPendantUnePartieRendLaConfirmationDirecteSansToucherLaSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrerUnePartie(session);
        EtatDePartie avant = etat(session);

        Document confirmation = page(session, "/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_DE_TROIS_JEUX);

        assertThat(confirmation.select(".question-confirmation").text()).isEqualTo("Si tu pars, ta partie s'arrête.");
        assertThat(confirmation.select(".retour-accueil").attr("hx-post"))
                .isEqualTo("/niveaux/CP/matieres/maths/confirmer");
        assertThat(confirmation.select(".tuile, .entete-matiere, button.demarrer")).isEmpty();
        assertThat(etat(session).session()).isSameAs(avant.session());
    }

    @Test
    void confirmerUnEtageDeuxAbandonneLaPartiePuisRendCetEtageDeux() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrerUnePartie(session);

        Document etage2 = Jsoup.parse(mockMvc.perform(post("/niveaux/{niveau}/matieres/{segment}/confirmer",
                        NIVEAU, SEGMENT_DE_TROIS_JEUX).session(session))
                .andReturn().getResponse().getContentAsString());

        assertThat(etat(session).portePartieEnCours()).isFalse();
        assertThat(etage2.select(".entete-matiere span").eachText()).containsExactly("Maths");
        assertThat(etage2.select("button.demarrer")).hasSize(3);
    }

    // ------------------------------------------------------------------------------------------
    // "Les jeux" ramene au premier ecran de la vue retenue, jamais a un etage 2 (APM-9)
    // ------------------------------------------------------------------------------------------

    @Test
    void lesJeuxNeRendJamaisUnEtageDeuxQuelleQueSoitLaVueEtLaMatiereJouee() throws Exception {
        MockHttpSession session = new MockHttpSession();
        basculer(session, NIVEAU, "V1");
        page(session, "/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_DE_TROIS_JEUX);
        demarrerUnePartie(session);
        mockMvc.perform(post("/retour").session(session));

        Document apres = Jsoup.parse(mockMvc.perform(post("/retour/confirmer").session(session))
                .andReturn().getResponse().getContentAsString());

        assertThat(apres.select(".entete-matiere, .tuile, button.demarrer, .bascule"))
                .as("APM-9 : ni etage 2, ni jeux de la matiere jouee ; le premier ecran est le choix du niveau")
                .isEmpty();
        assertThat(apres.select(".choix-niveau")).hasSize(1);
        assertThat(vue(session)).as("la vue survit a la partie").isEqualTo(VueDeLAccueil.V1);
    }

    @Test
    void apresUnePartieLeNiveauChoisiSOuvreDansLaVueRetenue() throws Exception {
        MockHttpSession session = new MockHttpSession();
        basculer(session, NIVEAU, "V1");
        demarrerUnePartie(session);
        mockMvc.perform(post("/retour").session(session));
        mockMvc.perform(post("/retour/confirmer").session(session));

        assertThat(page(session, "/niveaux/{niveau}", AUTRE_NIVEAU).select(".tuile"))
                .as("la vue retenue survit a la partie et au retour").isNotEmpty();
    }

    // ------------------------------------------------------------------------------------------
    // APM-8 : la session ne retient que la vue
    // ------------------------------------------------------------------------------------------

    @Test
    void laSessionNeRetientQueLaVueSurToutesLesRoutesDeLAccueil() throws Exception {
        MockHttpSession session = new MockHttpSession();
        page(session, "/");
        page(session, "/niveaux/{niveau}", NIVEAU);
        basculer(session, NIVEAU, "V1");
        page(session, "/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_DE_TROIS_JEUX);
        mockMvc.perform(post("/retour").session(session));

        Set<String> attributs = new HashSet<>(Collections.list(session.getAttributeNames()));
        assertThat(attributs).as("APM-8 : ni la matiere, ni le niveau, ni le segment en session")
                .containsExactly("recreo.vue");
    }

    @Test
    void aucuneRouteDeLAccueilNePoseDeCookie() throws Exception {
        MockHttpSession session = new MockHttpSession();
        List<MockHttpServletResponse> reponses = new ArrayList<>();
        reponses.add(mockMvc.perform(get("/").session(session)).andReturn().getResponse());
        reponses.add(mockMvc.perform(get("/niveaux/{niveau}", NIVEAU).session(session)).andReturn().getResponse());
        reponses.add(basculer(session, NIVEAU, "V1"));
        reponses.add(mockMvc.perform(get("/niveaux/{niveau}/matieres/{segment}", NIVEAU, SEGMENT_A_UN_SEUL_JEU)
                .session(session)).andReturn().getResponse());

        for (MockHttpServletResponse reponse : reponses) {
            assertThat(reponse.getCookies()).as("DEC-0028 : aucun cookie de preference").isEmpty();
        }
    }

    // ------------------------------------------------------------------------------------------
    // Le catalogue de test
    // ------------------------------------------------------------------------------------------

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        private static GameDefinition jeu(String id, String titre, String matiere, String niveau) {
            ContentDescriptor descripteur = new ContentDescriptor(matiere, niveau, "Preuve", "preuve du controleur");
            return new GameDefinition(id, titre, "Consigne.", BanditManchot.ID, descripteur,
                    new ListContentSource(descripteur, List.of(
                            new Question(new ItemId(id + "-q1"), "un", "1", List.of("2")),
                            new Question(new ItemId(id + "-q2"), "deux", "2", List.of("3")))),
                    1);
        }

        @Bean
        PartieUseCase partieUseCase() {
            // L'ordre du catalogue (maths d'abord) differe de l'ordre des lignes de donnee (Francais d'abord).
            List<GameDefinition> jeux = List.of(
                    jeu("cp-maths-1", "Jeu Maths 1", MATIERE_DE_TROIS_JEUX, NIVEAU),
                    jeu("cp-maths-2", "Jeu Maths 2", MATIERE_DE_TROIS_JEUX, NIVEAU),
                    jeu("cp-francais-1", "Jeu Francais", MATIERE_A_UN_SEUL_JEU, NIVEAU),
                    jeu("cp-maths-3", "Jeu Maths 3", MATIERE_DE_TROIS_JEUX, NIVEAU),
                    jeu("ce1-maths-1", "Jeu CE1", MATIERE_DE_TROIS_JEUX, AUTRE_NIVEAU));
            List<Matiere> lignes = List.of(
                    new Matiere(MATIERE_A_UN_SEUL_JEU, "Français", "francais.svg", "#aabbcc", "#112233",
                            SEGMENT_A_UN_SEUL_JEU),
                    new Matiere(MATIERE_DE_TROIS_JEUX, "Maths", "mathematiques.svg", "#ccbbaa", "#332211",
                            SEGMENT_DE_TROIS_JEUX));
            ContentCatalogPort catalogue = new ContentCatalogPort() {
                @Override
                public List<GameDefinition> games() {
                    return jeux;
                }

                @Override
                public List<Matiere> matieres() {
                    return lignes;
                }
            };
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }
    }
}
