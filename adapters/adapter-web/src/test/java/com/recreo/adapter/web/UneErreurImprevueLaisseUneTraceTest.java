package com.recreo.adapter.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.recreo.application.catalog.GameDefinition;
import com.recreo.application.catalog.GroupeDeJeux;
import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.content.ContentDescriptor;
import com.recreo.domain.content.source.ListContentSource;
import com.recreo.domain.mechanic.GameSession;
import com.recreo.domain.mechanic.Move;
import com.recreo.domain.mechanics.banditmanchot.BanditManchot;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * ADR-0018, la garantie qu'aucune regle ArchUnit ne peut donner : un defaut qu'aucun gestionnaire
 * ne nomme laisse une trace exploitable, et l'enfant voit quand meme son ecran en francais.
 *
 * <p>ADR-0013, obligation 3 : la preuve se fait sur un objet neuf. {@link DefautJamaisVu} est
 * declare ici, n'existe nulle part en production, et n'est nomme par aucun {@code @ExceptionHandler}
 * - c'est exactement la situation ou le depot etait aveugle avant cet ADR. Muter un type deja connu
 * n'aurait prouve que le critere.</p>
 */
@WebMvcTest(PartieController.class)
@Import(UneErreurImprevueLaisseUneTraceTest.UseCaseQuiEchoueConfiguration.class)
class UneErreurImprevueLaisseUneTraceTest {

    private static final String JEU_ID = "un-jeu";
    private static final String JEU_ABSENT_ID = "jeu-qui-nexiste-pas";

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    void unDefautQueAucunGestionnaireNeNommeEstJournaliseEnErreurAvecSaCause() throws Exception {
        var reponse = mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(new MockHttpSession()))
                .andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        assertThat(reponse.getContentType()).startsWith(MediaType.TEXT_HTML_VALUE);
        String html = reponse.getContentAsString();
        assertThat(Jsoup.parse(html).text()).contains("Oups ! Le jeu a un souci.");
        assertThat(html).doesNotContain("\"status\"", "\"timestamp\"", "Internal Server Error");

        assertThat(collecteur.list)
                .as("ADR-0018 : un defaut inattendu ne traverse pas le serveur en silence")
                .isNotEmpty();
        ILoggingEvent trace = collecteur.list.getFirst();
        assertThat(trace.getLevel())
                .as("ADR-0018 : un defaut est une ERREUR, pas une information de fonctionnement")
                .isEqualTo(Level.ERROR);
        assertThat(trace.getThrowableProxy())
                .as("ADR-0018 : la trace porte la cause, sans quoi elle ne situe rien")
                .isNotNull();
        assertThat(trace.getThrowableProxy().getClassName()).isEqualTo(DefautJamaisVu.class.getName());
        assertThat(trace.getFormattedMessage())
                .as("ADR-0018 : la trace nomme la requete, sans quoi elle n'est pas reproductible")
                .contains("POST", "/parties/" + JEU_ID);
    }

    /**
     * Le pendant du test precedent : une situation prevue ne doit pas remplir le journal d'erreurs,
     * sinon la separation ne sert a rien et l'ERREUR redevient du bruit qu'on n'ouvre plus.
     */
    @Test
    void uneSituationPrevueEstJournaliseeEnInformationEtNonEnErreur() throws Exception {
        var reponse = mockMvc.perform(post("/parties/{jeuId}", JEU_ABSENT_ID)).andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        assertThat(Jsoup.parse(reponse.getContentAsString()).text()).contains("Oups ! Le jeu a un souci.");

        assertThat(collecteur.list).hasSize(1);
        ILoggingEvent trace = collecteur.list.getFirst();
        assertThat(trace.getLevel()).isEqualTo(Level.INFO);
        assertThat(trace.getFormattedMessage()).contains(JEU_ABSENT_ID);
    }

    /** Declare ici et nulle part ailleurs : aucun gestionnaire du depot ne peut l'avoir prevu. */
    static final class DefautJamaisVu extends RuntimeException {

        DefautJamaisVu() {
            super("un defaut que personne n'a nomme");
        }
    }

    @TestConfiguration
    static class UseCaseQuiEchoueConfiguration {

        private static final ContentDescriptor DESCRIPTEUR =
                new ContentDescriptor("Test", "Test", "Test", "eprouver le chemin d'erreur");

        @Bean
        PartieUseCase partieUseCase() {
            return new PartieUseCase() {

                @Override
                public List<GameDefinition> jeuxDisponibles() {
                    return List.of(new GameDefinition(JEU_ID, "Un jeu", "Choisis la bonne reponse",
                            BanditManchot.ID, DESCRIPTEUR,
                            new ListContentSource(DESCRIPTEUR, List.of()), 4));
                }

                @Override
                public List<String> niveauxDuCatalogue() {
                    return List.of(DESCRIPTEUR.niveau());
                }

                @Override
                public List<GameDefinition> jeuxDuNiveau(String niveau) {
                    return jeuxDisponibles();
                }

                @Override
                public List<GroupeDeJeux> matieresDuNiveau(String niveau) {
                    return List.of();
                }

                @Override
                public List<GameDefinition> jeuxDeLaMatiere(String niveau, String matiere) {
                    return List.of();
                }

                @Override
                public GameSession demarrer(String jeuId) {
                    throw new DefautJamaisVu();
                }

                @Override
                public GameSession jouer(String jeuId, GameSession session, Move coup) {
                    throw new DefautJamaisVu();
                }
            };
        }
    }
}
