package com.recreo.adapter.web;

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
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0104 decisions 3 et 4, cote controleur : {@code propositionsEnImage} est derive une fois, vrai si
 * et seulement si toutes les propositions de la manche portent le prefixe {@code oeuvre-} ;
 * {@code mancheRevelee} distingue une revelation d'un echec ; la reponse n'est jamais posee en texte
 * quand la bonne reponse est une reproduction, ni a la revelation ni a l'ecran de fin. Les jeux a
 * propositions en texte ne changent pas : {@code PartieControllerTest} les juge, et un cas par etat
 * est rejoue ici pour que la separation soit prouvee des deux cotes. Les identifiants de ce fichier
 * sont des chaines : aucun fichier n'est lu, c'est le rendu du controleur qui est juge.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerPropositionsEnImageTest.JeuxDeTestConfiguration.class)
class PartieControllerPropositionsEnImageTest {

    private static final String JEU_EN_IMAGE = "jeu-en-image";
    private static final String JEU_MELANGE = "jeu-melange";
    private static final String JEU_EN_TEXTE = "jeu-en-texte";

    @Autowired
    private MockMvc mockMvc;

    private Document demarrer(String jeuId, MockHttpSession session) throws Exception {
        return Jsoup.parseBodyFragment(mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session))
                .andReturn().getResponse().getContentAsString());
    }

    private Document coup(String jeuId, MockHttpSession session, String proposition) throws Exception {
        return Jsoup.parseBodyFragment(mockMvc.perform(post("/parties/{jeuId}/coups", jeuId)
                        .session(session).param("propositionChoisie", proposition))
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    void uneMancheDontToutesLesPropositionsSontDesReproductionsRendUneImageParBouton() throws Exception {
        Document manche = demarrer(JEU_EN_IMAGE, new MockHttpSession());

        assertThat(manche.select(".ecran-deux-oeuvres")).hasSize(1);
        assertThat(manche.select("form.propositions.propositions-oeuvres")).hasSize(1);
        assertThat(manche.select("button.proposition img").eachAttr("src"))
                .containsExactlyInAnyOrder("/oeuvres/oeuvre-a.jpg", "/oeuvres/oeuvre-b.jpg",
                        "/oeuvres/oeuvre-c.jpg");
        assertThat(manche.select("button.proposition").eachText()).allMatch(String::isEmpty);
        assertThat(manche.select("button.proposition").eachAttr("aria-label"))
                .hasSize(3).containsOnly("Choisir cette œuvre");
    }

    @Test
    void uneRangeeMelangeeDeTexteEtDeReproductionsResteUneRangeeDeBoutonsTexte() throws Exception {
        Document manche = demarrer(JEU_MELANGE, new MockHttpSession());

        assertThat(manche.select("button.proposition img")).isEmpty();
        assertThat(manche.select(".ecran-deux-oeuvres")).isEmpty();
        assertThat(manche.select("button.proposition").eachText())
                .containsExactlyInAnyOrder("oeuvre-a", "un texte");
    }

    @Test
    void unJeuEnTexteNePorteAucuneMarqueDuDispositif() throws Exception {
        MockHttpSession session = new MockHttpSession();
        Document manche = demarrer(JEU_EN_TEXTE, session);

        assertThat(manche.select(".ecran-deux-oeuvres, .propositions-oeuvres, [aria-label], img")).isEmpty();
        assertThat(manche.select("button.proposition").eachText())
                .containsExactlyInAnyOrder("deux", "trois");
        for (int echec = 1; echec <= 3; echec++) {
            manche = coup(JEU_EN_TEXTE, session, "trois");
        }
        assertThat(manche.select(".reponse-attendue").text()).isEqualTo("deux");
        assertThat(manche.select("[data-oeuvre-revelee]")).isEmpty();
    }

    @Test
    void laRevelationNePoseAucuneReponseEnTexteEtMarqueLaBonneOeuvreSeule() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(JEU_EN_IMAGE, session);
        Document echec = coup(JEU_EN_IMAGE, session, "oeuvre-b");
        assertThat(echec.select("[data-oeuvre-revelee=true]")).isEmpty();
        coup(JEU_EN_IMAGE, session, "oeuvre-b");
        Document revelation = coup(JEU_EN_IMAGE, session, "oeuvre-b");

        assertThat(revelation.select(".reponse-attendue")).isEmpty();
        assertThat(revelation.select(".reponse-revelee").eachText())
                .containsExactly("C'est cette œuvre qui va avec. Appuie dessus.");
        assertThat(revelation.select("[data-oeuvre-revelee=true] img").eachAttr("src"))
                .containsExactly("/oeuvres/oeuvre-a.jpg");
        assertThat(revelation.select("button.proposition").eachAttr("data-oeuvre-revelee"))
                .containsExactlyInAnyOrder("true");
    }

    @Test
    void lEcranDeFinNeRendJamaisLIdentifiantDeLaBonneReponse() throws Exception {
        MockHttpSession session = new MockHttpSession();
        demarrer(JEU_EN_IMAGE, session);
        for (int echec = 1; echec <= 3; echec++) {
            coup(JEU_EN_IMAGE, session, "oeuvre-b");
        }
        Document fin = coup(JEU_EN_IMAGE, session, "oeuvre-a");

        assertThat(fin.select(".fin-de-partie")).isNotEmpty();
        assertThat(fin.select(".signal.est-faux")).isNotEmpty();
        assertThat(fin.select(".reponse-attendue")).isEmpty();
        assertThat(fin.text()).doesNotContain("oeuvre-");
        assertThat(fin.html()).doesNotContain("oeuvre-");
    }

    @TestConfiguration
    static class JeuxDeTestConfiguration {

        @Bean
        PartieUseCase partieUseCase() {
            ContentDescriptor descripteur = new ContentDescriptor("Arts plastiques", "CP", "Preuve",
                    "preuve du controleur, jamais servie a un enfant");
            List<GameDefinition> jeux = List.of(
                    jeu(JEU_EN_IMAGE, descripteur, new Question(new ItemId("image-1"), "Enonce.",
                            "oeuvre-a", List.of("oeuvre-b", "oeuvre-c"), Optional.empty(), Optional.empty(),
                            Optional.empty(), Optional.empty(), Optional.of("oeuvre-r"),
                            Optional.of("Texte alternatif."))),
                    jeu(JEU_MELANGE, descripteur, new Question(new ItemId("melange-1"), "Enonce.",
                            "oeuvre-a", List.of("un texte"))),
                    jeu(JEU_EN_TEXTE, descripteur, new Question(new ItemId("texte-1"), "Enonce.",
                            "deux", List.of("trois"))));
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
