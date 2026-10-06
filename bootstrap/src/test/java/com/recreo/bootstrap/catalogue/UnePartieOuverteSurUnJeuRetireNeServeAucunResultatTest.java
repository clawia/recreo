package com.recreo.bootstrap.catalogue;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.recreo.adapter.web.GestionnaireDesErreurs;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.port.in.PartieUseCase;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * S52-RETRAIT-SCIENCES-CM1, critere "sans redemarrage" (question Q6 de l'avis de faisabilite,
 * section 3.3) : une partie ouverte sur un jeu, puis ce jeu absent du catalogue sans que la
 * session HTTP soit perdue, ne sert aucun resultat juste ou faux.
 *
 * <p>Ce cas n'a pas d'occurrence en production (le catalogue est lu une fois au demarrage, et le
 * redemarrage efface la session). Il est fabrique : la session HTTP est amorcee par une vraie
 * partie du vrai catalogue, puis son identifiant de jeu est remplace par un identifiant que le
 * catalogue ne porte pas. Les noms d'attributs de session sont ceux de
 * {@code EtatDePartieEnSession} (package-private) ; le temoin positif ci-dessous echoue si ces
 * noms derivent, au lieu de laisser le test vert sur une session vide.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
class UnePartieOuverteSurUnJeuRetireNeServeAucunResultatTest {

    private static final String ATTRIBUT_JEU_ID = "recreo.jeuId";
    private static final String JEU_ABSENT = "jeu-fabrique-absent-du-catalogue";
    private static final String ECRAN_D_ERREUR = "Oups ! Le jeu a un souci.";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartieUseCase partie;

    private ch.qos.logback.classic.Logger journal;
    private ListAppender<ILoggingEvent> collecteur;

    @BeforeEach
    void brancherLeCollecteur() {
        journal = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(GestionnaireDesErreurs.class);
        collecteur = new ListAppender<>();
        collecteur.start();
        journal.addAppender(collecteur);
    }

    @AfterEach
    void debrancherLeCollecteur() {
        journal.detachAppender(collecteur);
    }

    private String unJeuReelDeBanditManchot() {
        return partie.jeuxDisponibles().stream()
                .map(GameDefinition::id)
                .filter(id -> id.startsWith("bandit-manchot-"))
                .findFirst().orElseThrow();
    }

    private MockHttpSession sessionAvecUnePartieOuverteSur(String jeuId) throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", jeuId).session(session)).andReturn();
        assertThat(session.getAttribute(ATTRIBUT_JEU_ID))
                .as("amorce : la partie reelle est bien en session sous l'attribut que ce test nomme")
                .isEqualTo(jeuId);
        return session;
    }

    @Test
    void temoinPositifUnCoupSurUnJeuDuCatalogueNeDonnePasL_EcranD_Erreur() throws Exception {
        String jeuId = unJeuReelDeBanditManchot();
        MockHttpSession session = sessionAvecUnePartieOuverteSur(jeuId);

        String html = mockMvc.perform(post("/parties/{jeuId}/coups", jeuId).session(session)
                        .param("propositionChoisie", "0"))
                .andReturn().getResponse().getContentAsString();

        assertThat(Jsoup.parse(html).text()).doesNotContain(ECRAN_D_ERREUR);
        assertThat(collecteur.list).isEmpty();
    }

    @Test
    void unCoupSurUnJeuAbsentDuCatalogueAvecUnePartieEnSessionDonneL_EcranD_ErreurEtAucunResultat()
            throws Exception {
        MockHttpSession session = sessionAvecUnePartieOuverteSur(unJeuReelDeBanditManchot());
        session.setAttribute(ATTRIBUT_JEU_ID, JEU_ABSENT);
        assertThat(partie.jeuxDisponibles()).extracting(GameDefinition::id).doesNotContain(JEU_ABSENT);

        var reponse = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ABSENT).session(session)
                        .param("propositionChoisie", "0"))
                .andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        String html = reponse.getContentAsString();
        assertThat(Jsoup.parse(html).text()).contains(ECRAN_D_ERREUR);
        assertThat(Jsoup.parse(html).select("a[href='/']")).isNotEmpty();
        assertThat(html).doesNotContain("\"status\"", "\"timestamp\"", "Internal Server Error");

        assertThat(collecteur.list)
                .as("la cause est l'absence au catalogue, pas une session perdue : l'amorce a porte")
                .anySatisfy(trace -> assertThat(trace.getThrowableProxy().getMessage())
                        .contains("jeu inconnu : " + JEU_ABSENT));
    }
}
