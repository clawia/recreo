package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Filtres symétriques aux « Par jeu » (DEC-0019), ajoutés sur les axes « par niveau » et
 * « par matière » : {@code GET /niveau/{niveau}?matiere=} restreint aux jeux d'une matière ;
 * {@code GET /matiere/{matiere}?niveau=} restreint à un niveau. Chips {@code filter-chip}
 * toujours présents (quand il y a au moins 2 options).
 *
 * <p>Note : sur l'axe « par niveau » la carte affiche « Matière — Nom » ; sur « par matière »
 * elle affiche « Niveau — Nom ». Le séparateur « — » n'apparaît que dans une carte, jamais
 * dans une chip de filtre (qui n'affiche que le nom brut) — d'où les assertions d'ABSENCE.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ParNiveauEtMatiereFiltersWebTest {

    @Autowired
    private MockMvc mockMvc;

    // ---- Axe « par niveau » filtré par matière ----

    @Test
    void niveauPageFilteredByMatiereKeepsOnlyThatSubject() throws Exception {
        String html = mockMvc.perform(get("/niveau/{n}", "CP").param("matiere", "Géométrie"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Une carte de Géométrie CP est présente (format « Matière — Nom »)...
        assertThat(html).contains("Géométrie — ");
        // ...et aucune carte d'une autre matière (« Français — » n'apparaît que dans une carte).
        assertThat(html).doesNotContain("Français — ");
        assertThat(html).contains("class=\"filter-chip");
    }

    @Test
    void niveauPageWithoutFilterListsSeveralSubjects() throws Exception {
        String html = mockMvc.perform(get("/niveau/{n}", "CP"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Sans filtre, plusieurs matières coexistent au niveau CP.
        assertThat(html).contains("Géométrie — ");
        assertThat(html).contains("Français — ");
        assertThat(html).contains("class=\"filter-chip");
    }

    // ---- Axe « par matière » filtré par niveau ----

    @Test
    void matierePageFilteredByNiveauKeepsOnlyThatLevel() throws Exception {
        String html = mockMvc.perform(get("/matiere/{m}", "Mathématiques").param("niveau", "CP"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Une carte CP est présente (format « Niveau — Nom »)...
        assertThat(html).contains("CP — ");
        // ...et aucune carte CM1.
        assertThat(html).doesNotContain("CM1 — ");
        assertThat(html).contains("class=\"filter-chip");
    }

    @Test
    void matierePageWithoutFilterListsBothLevels() throws Exception {
        String html = mockMvc.perform(get("/matiere/{m}", "Mathématiques"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Sans filtre, Mathématiques couvre CP et CM1.
        assertThat(html).contains("CP — ");
        assertThat(html).contains("CM1 — ");
        assertThat(html).contains("class=\"filter-chip");
    }
}
