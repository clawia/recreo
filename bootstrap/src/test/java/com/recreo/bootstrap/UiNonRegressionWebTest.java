package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests de NON-REGRESSION UI (US-s07-2) au niveau markup/HTML (MockMvc). Le rendu
 * pixel/icone reste une verification manuelle en demo (pas de Playwright dans cet env,
 * cf. DEC de cadrage s07). Chaque assertion protege une regression precise constatee
 * en s06 :
 * <ul>
 *   <li>ecran de fin affiche a tort (au start / en cours de partie) ;</li>
 *   <li>icone Parametres rétrécie / remplacée par un caractere non-emoji ;</li>
 *   <li>bouton « Rejouer » agrandi (classe {@code big}) alors qu'il ne doit pas l'etre ;</li>
 *   <li>« double carte » : une carte (fond/ombre) re-dessinee autour de l'ecran de fin,
 *       qui faisait reapparaitre une barre blanche.</li>
 * </ul>
 *
 * <p>Marqueur de fin FIABLE = {@code "Rejouer"} (present UNIQUEMENT sur l'ecran de fin),
 * PAS {@code "Bravo"} qui apparait aussi dans le feedback d'une bonne reponse (« Bravo !
 * +10 points ») et donnerait de faux positifs.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UiNonRegressionWebTest {

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");

    @Autowired
    private MockMvc mockMvc;

    /**
     * Un jeu representatif de chaque mecanique, avec le marqueur de contenu SPECIFIQUE
     * a sa vue (present tant que la partie est en cours, absent sur l'ecran de fin).
     */
    private record MechanicGame(String entryId, String gameMarker) {
        @Override
        public String toString() {
            return entryId;
        }
    }

    static Stream<MechanicGame> oneGamePerMechanic() {
        return Stream.of(
                new MechanicGame("calcul-mental-cp", "class=\"machine\""),   // bandit manchot
                new MechanicGame("mot-manquant-cp", "class=\"word-btn\""),    // texte a trous
                new MechanicGame("questionner-le-monde-cp", "class=\"quiz-btn\""), // quiz-aventure
                new MechanicGame("range-nombres-cp", "class=\"dnd-slot\"")); // glisser-deposer (ilot JS)
    }

    // --- (1) Ecran de fin affiche au BON moment, pour CHAQUE mecanique ---
    // Regression s06 : l'ecran de fin s'affichait des le start (th:replace evalue avant
    // th:if). On verrouille : absent au start, absent en cours, present SEULEMENT a la fin.

    @ParameterizedTest
    @MethodSource("oneGamePerMechanic")
    void endScreenIsAbsentAtStart(MechanicGame game) throws Exception {
        String html = startSession(game.entryId(), null);

        // Contenu de jeu bien present (la partie a demarre)...
        assertThat(html).contains("data-session-id");
        // ...et AUCUN ecran de fin (regression s06 : fin affichee a tort au start).
        assertThat(html).doesNotContain("Rejouer");
        assertThat(html).doesNotContain("class=\"game-over\"");
    }

    @ParameterizedTest
    @MethodSource("oneGamePerMechanic")
    void endScreenIsAbsentWhileInProgress(MechanicGame game) throws Exception {
        // Cible par defaut (20) : apres 1 seul coup, la partie est loin de la fin.
        String start = startSession(game.entryId(), null);
        String sessionId = firstMatch(SESSION_ID, start);

        String afterOneMove = move(sessionId, "peu-importe");

        // Toujours en jeu (contenu present) et TOUJOURS pas d'ecran de fin.
        assertThat(afterOneMove).contains("data-session-id");
        assertThat(afterOneMove).doesNotContain("Rejouer");
        assertThat(afterOneMove).doesNotContain("class=\"game-over\"");
    }

    @ParameterizedTest
    @MethodSource("oneGamePerMechanic")
    void endScreenAppearsExactlyAtTheEnd(MechanicGame game) throws Exception {
        // Session courte (cible = 5) : apres 5 coups, la partie est terminee.
        String start = startSession(game.entryId(), "5");
        String sessionId = firstMatch(SESSION_ID, start);

        String lastHtml = null;
        for (int i = 0; i < 5; i++) {
            lastHtml = move(sessionId, "peu-importe");
        }

        // Ecran de fin bien present a la fin (marqueur fiable "Rejouer" + game-over)...
        assertThat(lastHtml).contains("Rejouer");
        assertThat(lastHtml).contains("class=\"game-over\"");
        // ...et le contenu de jeu SPECIFIQUE a la mecanique a disparu (plus de machine /
        // mots / boutons de quiz : on ne laisse pas les deux ecrans coexister).
        assertThat(lastHtml).doesNotContain(game.gameMarker());
    }

    // --- (2) Icone Parametres = un EMOJI ⚙️ (avec selecteur de variation) ---
    // Regression s06 : l'engrenage etait rendu comme un glyphe rétréci / non-emoji.
    // On assert la chaine EXACTE "⚙️" (U+2699 U+FE0F) dans la feuille de catalogue.

    @Test
    void settingsButtonUsesTheGearEmoji() throws Exception {
        String html = mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Les jeux a reglage affichent le bouton Parametres avec l'emoji engrenage exact.
        assertThat(html).contains("⚙️");
    }

    // --- (3) Taille du bouton "Rejouer" (proxy markup) ---
    // Regression s06 : le bouton Rejouer avait ete agrandi via la classe "play-btn big".
    // On verrouille : sur l'ecran de fin, le bouton porte "play-btn" mais JAMAIS "big".

    @Test
    void replayButtonIsNotTheBigVariant() throws Exception {
        String end = playUntilEnd("mot-manquant-cp");

        assertThat(end).contains("class=\"play-btn\""); // bouton present, taille normale
        assertThat(end).doesNotContain("play-btn big");  // pas la variante agrandie
    }

    // --- (4) "Double carte" : le CSS de .game-over ne redessine PAS de carte ---
    // Regression s06 : .game-over avait un background/box-shadow qui, imbrique dans la
    // carte #game-area, faisait reapparaitre une barre blanche a ombre sous la pop-up.
    // On assert sur le CSS SERVI que le bloc .game-over ne contient NI l'un NI l'autre.

    @Test
    void gameOverCssHasNoCardBackgroundNorShadow() throws Exception {
        String css = mockMvc.perform(get("/css/app.css"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Extrait le bloc `.game-over { ... }` (et non .game-over-title / -actions).
        Matcher block = Pattern.compile("\\.game-over\\s*\\{([^}]*)\\}").matcher(css);
        assertThat(block.find()).as("bloc .game-over introuvable dans le CSS servi").isTrue();
        String rules = block.group(1);

        assertThat(rules).doesNotContain("box-shadow");
        assertThat(rules).doesNotContain("background");
    }

    // --- Helpers ---

    private String startSession(String entryId, String questionsTarget) throws Exception {
        var request = post("/sessions").param("catalogEntryId", entryId).param("from", "/niveaux");
        if (questionsTarget != null) {
            request = request.param("questionsTarget", questionsTarget);
        }
        return mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private String move(String sessionId, String chosenAnswer) throws Exception {
        return mockMvc.perform(post("/sessions/{id}/moves", sessionId).param("chosenAnswer", chosenAnswer))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private String playUntilEnd(String entryId) throws Exception {
        String start = startSession(entryId, "5");
        String sessionId = firstMatch(SESSION_ID, start);
        String lastHtml = null;
        for (int i = 0; i < 5; i++) {
            lastHtml = move(sessionId, "peu-importe");
        }
        return lastHtml;
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
