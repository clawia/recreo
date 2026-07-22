package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mecanique « puzzle » (7e mecanique, ilot JS, cf. ADR-0017) : demarrer une session sur le
 * jeu « Reconstitue le mot » (entryId {@code puzzle-mots-images-cp}, lu du YAML
 * content/puzzle/cp-mots-images.yml) rend le fragment {@code .puzzle} avec une reserve de
 * tuiles-lettres {@code .dnd-tuile} a glisser dans les emplacements.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PuzzleWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void startingAPuzzleRendersTilesToArrange() throws Exception {
        String html = mockMvc.perform(post("/sessions")
                        .param("catalogEntryId", "puzzle-mots-images-cp")
                        .param("from", "/matieres"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // La partie a demarre : fragment de la mecanique puzzle + tuiles a ranger.
        assertThat(html).contains("data-session-id");
        assertThat(html).contains("class=\"puzzle\"");
        assertThat(html).contains("class=\"dnd-tuile\"");
    }
}
