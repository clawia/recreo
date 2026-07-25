package com.recreo.bootstrap;

import com.recreo.bootstrap.config.PhraseContentPack;
import com.recreo.bootstrap.config.PhrasePackLoader;
import com.recreo.domain.content.impl.PhrasePuzzleGenerator.Phrase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mécanique « puzzle » RÉUTILISÉE par le jeu « Remets la phrase dans l'ordre » (déclinaison
 * « mots » de la mécanique puzzle, cf. ADR-0017 ; coup {@code SubmitArrangement}, vue {@code puzzle}).
 * Démarrer une session sur {@code phrase-cp} rend le plateau d'arrangement (tuiles-MOTS + emplacements) ;
 * soumettre les mots DANS LE BON ORDRE score « Bravo ! +10 » ; un arrangement faux ne fait pas
 * avancer et affiche le feedback « Presque » (les cœurs/jokers gèrent la fin de partie).
 *
 * <p>Même mécanisme d'arrangement que {@code GlisserDeposerWebTest} : les tuiles portent leur valeur
 * dans {@code data-value} et l'ordre soumis part dans le champ {@code arrangement} RÉPÉTÉ. La réponse
 * n'étant jamais rendue (on n'affiche pas {@code game.options}), l'ordre attendu se déduit — comme le
 * glisser-déposer déduit le sien de la consigne — en identifiant la phrase tirée : les tuiles affichent
 * les mots TRIÉS, ce multiset désigne une unique phrase du contenu livré (lu via {@link PhrasePackLoader}).</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PhrasePuzzleWebTest {

    private static final String ENTRY_ID = "phrase-cp";

    private static final Pattern SESSION_ID = Pattern.compile("data-session-id=\"([^\"]+)\"");
    /** Une tuile-mot : sa valeur est portée par data-value (dans un ordre ou l'autre des attributs). */
    private static final Pattern TILE = Pattern.compile(
            "class=\"dnd-tuile\"[^>]*data-value=\"([^\"]*)\"|data-value=\"([^\"]*)\"[^>]*class=\"dnd-tuile\"");

    @Autowired
    private MockMvc mockMvc;

    /** Phrases livrées du jeu phrase-cp (source de vérité pour l'ordre attendu). */
    private final List<Phrase> phrasesCp = new PhrasePackLoader().loadAll().stream()
            .filter(p -> ENTRY_ID.equals(p.entryId()))
            .findFirst().map(PhraseContentPack::phrases).orElseThrow();

    @Test
    void startingAPhrasePuzzleRendersWordTilesToArrange() throws Exception {
        String html = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", ENTRY_ID)
                        .param("from", "/niveau/CP"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // La partie a démarré : vue puzzle réutilisée + tuiles à ranger dans des emplacements.
        assertThat(html).contains("data-session-id");
        assertThat(html).contains("class=\"puzzle\"");
        assertThat(html).contains("class=\"dnd-tuile\"");
        assertThat(html).contains("class=\"dnd-slot\"");
        assertThat(html).contains("Remets les mots dans l&#39;ordre"); // consigne (apostrophe échappée par Thymeleaf)
    }

    @Test
    void correctlyOrderedWordsScore() throws Exception {
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", ENTRY_ID))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);
        List<String> correct = correctOrder(html); // mots DANS L'ORDRE de la phrase tirée

        mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("arrangement", correct.toArray(new String[0])))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bravo")))
                .andExpect(content().string(containsString("+10")));
    }

    @Test
    void wrongOrderDoesNotAdvanceAndShowsTheJokerFeedback() throws Exception {
        // UX « ranger » (DEC-0008) : un ordre faux ne fait PAS avancer -> même puzzle, feedback
        // « Presque », pas d'écran de fin. La réponse n'est JAMAIS révélée.
        MvcResult start = mockMvc.perform(post("/sessions").param("catalogEntryId", ENTRY_ID))
                .andExpect(status().isOk()).andReturn();
        String html = start.getResponse().getContentAsString();
        String sessionId = firstMatch(SESSION_ID, html);

        // On part de l'ordre correct puis on échange le premier et le dernier mot (distincts dans
        // toutes les phrases livrées) -> ordre nécessairement faux.
        List<String> correct = correctOrder(html);
        List<String> attempt = new ArrayList<>(correct);
        assertThat(attempt.get(0)).isNotEqualTo(attempt.get(attempt.size() - 1));
        Collections.swap(attempt, 0, attempt.size() - 1);

        String after = mockMvc.perform(post("/sessions/{id}/moves", sessionId)
                        .param("arrangement", attempt.toArray(new String[0])))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Presque")))
                .andExpect(content().string(not(containsString("Rejouer")))) // pas d'écran de fin
                .andReturn().getResponse().getContentAsString();

        // Même puzzle re-proposé (emplacements toujours présents), réponse non révélée.
        assertThat(after).contains("class=\"dnd-slot\"");
    }

    /**
     * L'ordre ATTENDU pour ce puzzle : les tuiles affichent les mots TRIÉS, ce multiset désigne
     * une unique phrase du contenu livré ; on renvoie SES mots dans l'ordre de la phrase.
     */
    private List<String> correctOrder(String html) {
        List<String> key = new ArrayList<>(tileValues(html));
        Collections.sort(key);
        for (Phrase p : phrasesCp) {
            List<String> words = new ArrayList<>(List.of(p.phrase().trim().split("\\s+")));
            List<String> sorted = new ArrayList<>(words);
            Collections.sort(sorted);
            if (sorted.equals(key)) {
                return words;
            }
        }
        throw new AssertionError("Aucune phrase de " + ENTRY_ID + " ne correspond aux tuiles : " + key);
    }

    /** Valeurs (mots) des tuiles, HTML-déséchappées (Thymeleaf échappe l'apostrophe en &#39;). */
    private List<String> tileValues(String html) {
        Matcher m = TILE.matcher(html);
        List<String> values = new ArrayList<>();
        while (m.find()) {
            String v = m.group(1) != null ? m.group(1) : m.group(2);
            values.add(unescape(v));
        }
        assertThat(values).as("tuiles data-value trouvées").isNotEmpty();
        return values;
    }

    /** Déséchappe les entités HTML markup-significatives émises par Thymeleaf (apostrophe, &, etc.). */
    private static String unescape(String s) {
        return s.replace("&#39;", "'").replace("&amp;", "&")
                .replace("&quot;", "\"").replace("&lt;", "<").replace("&gt;", ">");
    }

    private String firstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        assertThat(matcher.find()).as("motif introuvable : " + pattern).isTrue();
        return matcher.group(1);
    }
}
