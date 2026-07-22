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
 * Écran « par jeu » ({@code GET /jeux}) FILTRABLE par {@code ?niveau=} et {@code ?matiere=}
 * (combinables, cf. IDEE-3). La liste plate affiche chaque jeu au format « Matière Niveau — Nom » ;
 * les filtres restreignent la liste et les boutons {@code filter-chip} sont toujours présents.
 *
 * <p>Note : on vérifie l'ABSENCE de cartes de jeu via le séparateur « Matière — » (ex. « Français — »),
 * qui n'apparaît QUE dans une carte (format « Matière Niveau — Nom »), jamais dans les chips de
 * filtre (qui n'affichent que le nom brut de la matière/niveau).</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ParJeuFiltersWebTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * GET /jeux avec des filtres passes en PARAMETRES (et non dans l'URL) : robuste aux
     * caracteres accentues comme « Mathématiques » (l'encodage de query est sinon fragile).
     * Les cles/valeurs sont fournies par paires ({@code "niveau", "CM1", "matiere", "..."}).
     */
    private String fetchJeux(String... params) throws Exception {
        var request = get("/jeux");
        for (int i = 0; i + 1 < params.length; i += 2) {
            request = request.param(params[i], params[i + 1]);
        }
        return mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void filterByNiveauKeepsOnlyThatLevel() throws Exception {
        String html = fetchJeux("niveau", "CM1");

        // Un jeu CM1 est bien present, au format « Matiere Niveau — Nom ».
        assertThat(html).contains("Histoire CM1 — Quiz-aventure");
        // Aucun jeu CP (ex. un jeu CP exclusif) : sa carte n'apparait pas.
        assertThat(html).doesNotContain("Mathématiques CP — Compléments à 10");
        // Boutons de filtre toujours presents.
        assertThat(html).contains("class=\"filter-chip");
    }

    @Test
    void filterByMatiereKeepsOnlyThatSubject() throws Exception {
        String html = fetchJeux("matiere", "Mathématiques");

        // Un jeu de Mathematiques est present...
        assertThat(html).contains("Mathématiques CM1 — Calcul mental");
        // ...et aucune carte d'une autre matiere (« Français — » n'apparait que dans une carte,
        // pas dans les chips de filtre).
        assertThat(html).doesNotContain("Français — ");
        assertThat(html).contains("class=\"filter-chip");
    }

    @Test
    void filtersCombineToASubset() throws Exception {
        String html = fetchJeux("niveau", "CM1", "matiere", "Mathématiques");

        // Sous-ensemble : Mathematiques ET CM1.
        assertThat(html).contains("Mathématiques CM1 — Calcul mental");
        // Autre matiere de CM1 exclue (filtre matiere) et autre niveau exclu (filtre niveau).
        assertThat(html).doesNotContain("Histoire CM1 — Quiz-aventure");
        assertThat(html).doesNotContain("Mathématiques CP — Compléments à 10");
        assertThat(html).contains("class=\"filter-chip");
    }

    @Test
    void noFilterListsEverything() throws Exception {
        String html = fetchJeux();

        // Liste complete : au moins un CP et un CM1.
        assertThat(html).contains("Mathématiques CP — Compléments à 10");
        assertThat(html).contains("Mathématiques CM1 — Calcul mental");
        assertThat(html).contains("class=\"filter-chip");
    }
}
