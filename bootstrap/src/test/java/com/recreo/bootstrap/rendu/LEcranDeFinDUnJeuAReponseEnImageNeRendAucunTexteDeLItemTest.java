package com.recreo.bootstrap.rendu;

import com.recreo.adapter.web.PartieController;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.application.port.out.ContentCatalogPort;
import com.recreo.application.port.out.MechanicRegistryPort;
import com.recreo.application.usecase.PartieService;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.ItemId;
import com.recreo.domain.content.Question;
import com.recreo.domain.content.RandomSource;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.Score;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * <b>ADR-0104, amendement du 2026-10-14 (S56-RDO-CP-R2, criteres {@code D6} et {@code D7} de
 * nadia-prof) : l'ecran de fin d'un jeu dont la bonne reponse est une reproduction ne rend aucun
 * texte de l'item.</b> Ni l'enonce, ni l'indice, ni l'explication : ils designent des oeuvres que cet
 * ecran ne montre pas ({@code celle-ci}, {@code ces deux oeuvres}). Le critere est une liste fermee
 * des textes permis, jamais une liste de mots interdits.
 *
 * <p><b>Pourquoi ce test vit ici et pas dans adapter-web.</b> Il rend le fragment {@code retour} par le
 * controleur et le gabarit reels sur les donnees reelles de {@code cp-rapprocher-deux-oeuvres.yaml}
 * (ADR-0013, amendement du 2026-08-06 : les donnees d'entree d'un test font partie de son perimetre).
 * {@code adapter-web} ne voit pas {@code adapter-content} ; seul {@code bootstrap} voit les deux.
 * Precedent : {@code LExplicationDUnItemReelAtteintLEcranDeRetourTest}. Le paquet est lu par
 * {@link Yaml} : il n'est pas au catalogue servi tant que le poste 9 ne l'y remet pas, et un test qui
 * deriverait du catalogue verrait zero jeu.</p>
 *
 * <p><b>Perimetre derive.</b> Les items du paquet lu dont la bonne reponse porte le prefixe
 * {@code oeuvre-} (le predicat de {@code PartieController.retourModele}), joues chacun dans un jeu
 * d'une manche, sur les deux issues : derniere manche revelee, derniere manche juste. La population
 * n'est pas nommee, et elle ne doit pas etre vide.</p>
 *
 * <p><b>La liste fermee, sans exemption.</b> Sur le fragment, sous {@code aria-hidden} ou non
 * (nadia-prof, 4.1 de {@code reponse-nadia-forme2-palier-1.md}) : tout noeud de texte non blanc, et
 * toute valeur d'{@code alt}, {@code title}, {@code aria-label}, {@code aria-description},
 * {@code aria-roledescription}, {@code placeholder} ou {@code value} (4.2), est dans la liste ou
 * vide ; la cible d'{@code aria-labelledby} et d'{@code aria-describedby} est dans le fragment et son
 * texte est soumis a la meme liste ; toute {@code <img>} porte un {@code alt}. Les textes sont lus par
 * {@code wholeText()}, jamais par {@code text()} qui normalise U+00A0.</p>
 *
 * <p><b>Ce que ce test ne voit pas, nomme</b> : un texte pose par la feuille ({@code content} d'un
 * pseudo-element) ou par le JavaScript apres le rendu. nadia-prof en reste la garde. Il ne juge pas
 * non plus ce que les textes de la liste disent a un enfant de CP.</p>
 *
 * <p><b>Non-regression.</b> Un jeu dont la bonne reponse est un texte garde son ecran de fin : la
 * question rappelee et la reponse attendue apres une revelation, l'explication apres une reussite.
 * Il est fait d'un item reel du paquet dont seules les trois propositions sont remplacees par du
 * texte.</p>
 */
@WebMvcTest(PartieController.class)
@Import(LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest.JeuxDeTestConfiguration.class)
class LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest {

    private static final String PAQUET_REEL = "/contenu/cp-rapprocher-deux-oeuvres.yaml";
    private static final String PREFIXE_DE_REPRODUCTION = "oeuvre-";
    private static final String JEU_EN_IMAGE = "fin-en-image-";
    private static final String JEU_EN_TEXTE = "fin-en-texte";

    private static final String BRAVO = "Bravo, c'est juste !";
    private static final String PAS_TROUVE = "Tu n'as pas trouvé.";

    /** La liste fermee de D7, mot pour mot, hors les deux nombres du score (voir {@link #defautsDe}). */
    private static final Set<String> LISTE_FERMEE = Set.of("Les jeux", "Tu as trouvé, tout seul :",
            "Tu as joué toutes les questions :", BRAVO, PAS_TROUVE, "C'est fini !");

    /** Les attributs de nadia-prof (reponse-nadia-forme2-palier-1.md, 4.2, point 1). */
    private static final List<String> ATTRIBUTS_PORTEURS_DE_TEXTE = List.of("alt", "title", "aria-label",
            "aria-description", "aria-roledescription", "placeholder", "value");

    private static final List<String> ATTRIBUTS_QUI_RENVOIENT = List.of("aria-labelledby", "aria-describedby");

    private enum Issue { REVELEE, JUSTE }

    /** Un ecran de fin rendu, et le score que le controleur a pose au modele pour le rendre. */
    private record Fin(Document fragment, Score score) {
    }

    @Autowired
    private MockMvc mockMvc;

    // ------------------------------------------------------------------------------------------
    // Le materiau : le paquet reel
    // ------------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> itemsReels() {
        try (InputStream flux = LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest.class
                .getResourceAsStream(PAQUET_REEL)) {
            assertThat(flux).as("%s introuvable sur le classpath", PAQUET_REEL).isNotNull();
            Map<String, Object> corps = (Map<String, Object>) new Yaml().load(flux);
            return (List<Map<String, Object>>) corps.get("items");
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static boolean reponseEnImage(Map<String, Object> item) {
        return String.valueOf(item.get("bonneReponse")).startsWith(PREFIXE_DE_REPRODUCTION);
    }

    private static List<Map<String, Object>> population() {
        return itemsReels().stream()
                .filter(LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest::reponseEnImage)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private static Question versQuestion(int rang, Map<String, Object> item, boolean enTexte) {
        List<String> distracteurs = ((List<Object>) item.get("distracteurs")).stream()
                .map(String::valueOf).toList();
        String bonneReponse = String.valueOf(item.get("bonneReponse"));
        if (enTexte) {
            bonneReponse = "reponse ecrite";
            distracteurs = List.of("autre reponse", "encore une autre");
        }
        return new Question(new ItemId("item-reel-" + rang), String.valueOf(item.get("enonce")),
                bonneReponse, distracteurs, Optional.of(String.valueOf(item.get("indice"))),
                Optional.of(String.valueOf(item.get("explication"))), Optional.empty(), Optional.empty(),
                Optional.of(String.valueOf(item.get("illustration"))),
                Optional.of(String.valueOf(item.get("illustrationTexteAlternatif"))));
    }

    // ------------------------------------------------------------------------------------------
    // Le juge : une fonction pure sur le fragment rendu
    // ------------------------------------------------------------------------------------------

    private static boolean permis(String texte) {
        return texte.isBlank() || LISTE_FERMEE.contains(texte);
    }

    /**
     * Les defauts d'un ecran de fin, un message par defaut. Aucune exemption sous {@code aria-hidden}.
     * Les deux nombres du score sont compares a ceux du modele, pas a un motif de chiffres.
     */
    static List<String> defautsDe(Document fragment, Score score) {
        List<String> defauts = new ArrayList<>();
        for (Element element : fragment.getAllElements()) {
            for (TextNode noeud : element.textNodes()) {
                String texte = noeud.getWholeText().strip();
                if (element.hasClass("score-trouve-nombre")) {
                    if (!texte.equals(String.valueOf(score.reussites()))) {
                        defauts.add("le nombre de .score-trouve-nombre vaut \"" + texte + "\", le modele "
                                + score.reussites());
                    }
                } else if (element.hasClass("score-joue-nombre")) {
                    if (!texte.equals(String.valueOf(score.tentatives()))) {
                        defauts.add("le nombre de .score-joue-nombre vaut \"" + texte + "\", le modele "
                                + score.tentatives());
                    }
                } else if (!permis(texte)) {
                    defauts.add("noeud de texte hors de la liste fermee dans <" + element.tagName() + "> : \""
                            + texte + "\"");
                }
            }
            for (Attribute attribut : element.attributes()) {
                String valeur = attribut.getValue().strip();
                if (ATTRIBUTS_PORTEURS_DE_TEXTE.contains(attribut.getKey()) && !permis(valeur)) {
                    defauts.add("attribut " + attribut.getKey() + " de <" + element.tagName()
                            + "> hors de la liste fermee : \"" + valeur + "\"");
                }
                if (ATTRIBUTS_QUI_RENVOIENT.contains(attribut.getKey())) {
                    for (String identifiant : valeur.split("\\s+")) {
                        Element cible = identifiant.isEmpty() ? null : fragment.getElementById(identifiant);
                        if (cible == null) {
                            defauts.add(attribut.getKey() + " de <" + element.tagName() + "> renvoie a \""
                                    + identifiant + "\", absent du fragment");
                        } else if (!permis(cible.wholeText().strip())) {
                            defauts.add(attribut.getKey() + " de <" + element.tagName() + "> renvoie a un"
                                    + " element dont le texte est hors de la liste fermee : \""
                                    + cible.wholeText().strip() + "\"");
                        }
                    }
                }
            }
            if (element.tagName().equals("img") && !element.hasAttr("alt")) {
                defauts.add("une <img> sans attribut alt : src=\"" + element.attr("src") + "\"");
            }
        }
        if (!fragment.select(".question-rappelee").isEmpty()) {
            defauts.add("un element .question-rappelee est rendu");
        }
        if (!fragment.select(".explication").isEmpty()) {
            defauts.add("un element .explication est rendu");
        }
        return defauts;
    }

    // ------------------------------------------------------------------------------------------
    // Le parcours : le vrai controleur, le vrai gabarit, la vraie traversee HTTP
    // ------------------------------------------------------------------------------------------

    private Fin jouerJusquALaFin(String jeuId, Question item, Issue issue) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session));
        if (issue == Issue.REVELEE) {
            for (int echec = 1; echec <= 3; echec++) {
                coup(jeuId, session, item.distracteurs().get(0));
            }
        }
        MvcResult fin = coup(jeuId, session, item.bonneReponse());
        return new Fin(Jsoup.parseBodyFragment(fin.getResponse().getContentAsString()),
                (Score) fin.getModelAndView().getModel().get("score"));
    }

    private MvcResult coup(String jeuId, MockHttpSession session, String proposition) throws Exception {
        return mockMvc.perform(post("/parties/{jeuId}/coups", jeuId).session(session)
                .param("propositionChoisie", proposition)).andReturn();
    }

    // ------------------------------------------------------------------------------------------
    // Les tests
    // ------------------------------------------------------------------------------------------

    @Test
    void laPopulationDuPaquetReelEstNonVideEtChaqueItemPorteSesTextes() {
        List<Map<String, Object>> population = population();

        assertThat(population)
                .as("ADR-0051 : sans item a reponse en image, ce test ne juge rien")
                .isNotEmpty();
        for (Map<String, Object> item : population) {
            for (String cle : List.of("enonce", "indice", "explication")) {
                assertThat(String.valueOf(item.get(cle)))
                        .as("%s de l'item de bonne reponse %s : l'ecran de fin ne peut etre juge sur un"
                                + " item qui n'a rien a y faire voir", cle, item.get("bonneReponse"))
                        .isNotBlank().isNotEqualTo("null");
            }
        }
    }

    @Test
    void aucunTexteDeLItemNAtteintLEcranDeFinReveleNiJuste() throws Exception {
        List<Map<String, Object>> population = population();
        List<String> defauts = new ArrayList<>();
        int ecransLus = 0;
        for (int rang = 0; rang < population.size(); rang++) {
            Question item = versQuestion(rang, population.get(rang), false);
            for (Issue issue : Issue.values()) {
                Fin fin = jouerJusquALaFin(JEU_EN_IMAGE + rang, item, issue);
                ecransLus++;
                String ou = "item " + rang + ", fin " + issue;

                assertThat(fin.fragment().select(".fin-de-partie")).as(ou + " : l'ecran de fin").isNotEmpty();
                assertThat(fin.fragment().select(issue == Issue.JUSTE ? ".signal.est-juste" : ".signal.est-faux"))
                        .as(ou + " : l'issue jouee est celle de l'ecran").isNotEmpty();
                String lu = fin.fragment().body().wholeText();
                assertThat(lu).as(ou + " : les textes de la liste y sont, ce test lit un ecran")
                        .contains("Les jeux", "C'est fini !")
                        .contains(issue == Issue.JUSTE ? BRAVO : PAS_TROUVE);
                assertThat(lu).as(ou)
                        .doesNotContain(item.enonce())
                        .doesNotContain(item.explication().orElseThrow())
                        .doesNotContain(item.indice().orElseThrow());
                defauts.addAll(defautsDe(fin.fragment(), fin.score()).stream()
                        .map(defaut -> ou + " : " + defaut).toList());
            }
        }

        assertThat(ecransLus).as("deux issues par item de la population").isEqualTo(2 * population.size());
        assertThat(defauts).isEmpty();
    }

    @Test
    void unJeuDontLaBonneReponseEstUnTexteGardeSonEcranDeFin() throws Exception {
        Question item = versQuestion(0, population().get(0), true);

        Fin revelee = jouerJusquALaFin(JEU_EN_TEXTE, item, Issue.REVELEE);
        // wholeText : l'enonce du paquet porte U+00A0 devant le point d'interrogation, que text() normalise.
        assertThat(revelee.fragment().select(".question-rappelee").stream().map(Element::wholeText).toList())
                .as("question rappelee apres une fin revelee").containsExactly(item.enonce());
        assertThat(revelee.fragment().select(".reponse-revelee")).as("bloc de reponse").hasSize(1);
        assertThat(revelee.fragment().select(".reponse-attendue").eachText())
                .as("reponse attendue apres une fin revelee").containsExactly("reponse ecrite");

        Fin juste = jouerJusquALaFin(JEU_EN_TEXTE, item, Issue.JUSTE);
        assertThat(juste.fragment().select(".explication").stream().map(Element::wholeText).toList())
                .as("explication apres une fin juste").containsExactly(item.explication().orElseThrow());
    }

    // ------------------------------------------------------------------------------------------
    // Le juge mord, sur des fragments fabriques
    // ------------------------------------------------------------------------------------------

    private static final Score SCORE = new Score(1, 4);

    private static final String ECRAN_CONFORME = """
            <div class="ecran-de-jeu">
              <button class="btn sortie-pleine" data-role="sortie">Les jeux</button>
              <p class="score-trouve-libelle">Tu as trouvé, tout seul :</p>
              <p class="score-trouve-nombre">1</p>
              <p class="score-joue-libelle">Tu as joué toutes les questions :</p>
              <p class="score-joue-nombre">4</p>
              <div class="signal" tabindex="-1"><span class="icone-signal" aria-hidden="true">%s</span>
                <p class="verdict">Tu n'as pas trouvé.</p></div>
              %s
              <p class="fin-de-partie">C'est fini !</p>
            </div>
            """;

    private static List<String> defautsDuFragment(String dansLIcone, String ailleurs) {
        return defautsDe(Jsoup.parseBodyFragment(ECRAN_CONFORME.formatted(dansLIcone, ailleurs)), SCORE);
    }

    @Test
    void leJugeLaisseUnEcranConformePasser() {
        assertThat(defautsDuFragment("", "")).isEmpty();
        assertThat(defautsDuFragment("", "<img src=\"/x.jpg\" alt=\"\">")).isEmpty();
    }

    @Test
    void leJugeRefuseUnTexteDeLItemMemeSousAriaHidden() {
        assertThat(defautsDuFragment("Quelle œuvre est faite de la même façon que celle-ci ?", ""))
                .singleElement().asString().contains("noeud de texte hors de la liste fermee");
        assertThat(defautsDuFragment("", "<p>Dans ces deux œuvres, on voit le trait.</p>")).hasSize(1);
    }

    @Test
    void leJugeRefuseChaqueAttributQuiPorteUnTexteDeLItem() {
        for (String attribut : ATTRIBUTS_PORTEURS_DE_TEXTE) {
            assertThat(defautsDuFragment("", "<span " + attribut + "=\"celle-ci\"></span>"))
                    .as("attribut %s", attribut).singleElement().asString().contains(attribut);
            assertThat(defautsDuFragment("", "<span aria-hidden=\"true\" " + attribut + "=\"celle-ci\"></span>"))
                    .as("attribut %s sous aria-hidden", attribut).hasSize(1);
            assertThat(defautsDuFragment("", "<span " + attribut + "=\"\"></span>"))
                    .as("attribut %s vide", attribut).isEmpty();
            assertThat(defautsDuFragment("", "<span " + attribut + "=\"Les jeux\"></span>"))
                    .as("attribut %s dans la liste", attribut).isEmpty();
        }
    }

    @Test
    void leJugeRefuseUneImgSansAlt() {
        assertThat(defautsDuFragment("", "<img src=\"/oeuvres/x.jpg\">"))
                .singleElement().asString().contains("sans attribut alt");
    }

    @Test
    void leJugeSuitLesRenvoisDAriaLabelledbyEtDAriaDescribedby() {
        for (String attribut : ATTRIBUTS_QUI_RENVOIENT) {
            assertThat(defautsDuFragment("", "<span " + attribut + "=\"absent\"></span>"))
                    .as("%s vers un identifiant hors du fragment", attribut)
                    .singleElement().asString().contains("absent du fragment");
            assertThat(defautsDuFragment("", "<span " + attribut + "=\"cible\"></span>"
                            + "<span id=\"cible\">Dans ces deux œuvres</span>"))
                    .as("%s vers un texte de l'item", attribut)
                    .hasSize(2);
            assertThat(defautsDuFragment("", "<span " + attribut + "=\"cible\"></span>"
                            + "<span id=\"cible\">C'est fini !</span>"))
                    .as("%s vers un texte de la liste", attribut).isEmpty();
        }
    }

    @Test
    void leJugeRefuseLesElementsDeLaVoieA() {
        assertThat(defautsDuFragment("", "<p class=\"question-rappelee\"></p>"))
                .singleElement().asString().contains(".question-rappelee");
        assertThat(defautsDuFragment("", "<p class=\"explication\"></p>"))
                .singleElement().asString().contains(".explication");
    }

    @Test
    void leJugeComparLesNombresAuScoreDuModele() {
        Document fragment = Jsoup.parseBodyFragment(ECRAN_CONFORME.formatted("", ""));

        assertThat(defautsDe(fragment, new Score(2, 4))).singleElement().asString()
                .contains("score-trouve-nombre");
        assertThat(defautsDe(fragment, new Score(1, 5))).singleElement().asString()
                .contains("score-joue-nombre");
    }

    // ------------------------------------------------------------------------------------------
    // La configuration
    // ------------------------------------------------------------------------------------------

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            ContentDescriptor descripteur = new ContentDescriptor("Arts plastiques", "CP",
                    "Preuve d'ADR-0104", "test de rendu, jamais servi a un enfant");
            List<GameDefinition> jeux = new ArrayList<>();
            List<Map<String, Object>> population = population();
            for (int rang = 0; rang < population.size(); rang++) {
                jeux.add(jeu(JEU_EN_IMAGE + rang, descripteur, versQuestion(rang, population.get(rang), false)));
            }
            jeux.add(jeu(JEU_EN_TEXTE, descripteur, versQuestion(0, population.get(0), true)));
            ContentCatalogPort catalogue = () -> jeux;
            MechanicRegistryPort mecaniques = id -> new BanditManchot();
            RandomSource hasard = borne -> 0;
            return new PartieService(catalogue, mecaniques, hasard);
        }

        private static GameDefinition jeu(String id, ContentDescriptor descripteur, Question item) {
            return new GameDefinition(id, id, "Consigne.", BanditManchot.ID, descripteur,
                    new ListContentSource(descripteur, List.of(item)), 1);
        }
    }
}
