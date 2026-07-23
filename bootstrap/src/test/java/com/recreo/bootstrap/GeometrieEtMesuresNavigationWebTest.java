package com.recreo.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Nouveaux contenus s23 : la matière « Géométrie » (quiz {@code cp-geometrie}/{@code cm1-geometrie},
 * associer {@code cp-formes}) et les jeux Grandeurs et mesures (quiz {@code cm1-longueurs}/
 * {@code cm1-durees}, associer {@code cm1-mesures}, matière Mathématiques). Vérifie leur
 * découvrabilité par les axes de navigation : par matière ({@code /matieres}, {@code /matiere/{m}})
 * et par niveau ({@code /niveau/{n}}).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GeometrieEtMesuresNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void matieresPageListsGeometrie() throws Exception {
        mockMvc.perform(get("/matieres"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Géométrie")));
    }

    @Test
    void matiereGeometrieListsGeometryGames() throws Exception {
        // /matiere/G%C3%A9om%C3%A9trie via le template d'URI (encodage géré par MockMvc).
        mockMvc.perform(get("/matiere/{m}", "Géométrie"))
                .andExpect(status().isOk())
                // Au moins un jeu de géométrie (le quiz CP « Les formes », matière Géométrie).
                .andExpect(content().string(containsString("Les formes")));
    }

    @Test
    void niveauCm1ListsLongueursAndDurees() throws Exception {
        mockMvc.perform(get("/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les longueurs")))
                .andExpect(content().string(containsString("Les durées")));
    }

    @Test
    void niveauCpListsLesFormes() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les formes")));
    }

    // Nouveaux contenus : jeux de géométrie ajoutés (symétrie, solides, périmètre, quadrilatères).
    @Test
    void matiereGeometrieListsNewGeometryGames() throws Exception {
        // /matiere/G%C3%A9om%C3%A9trie via le template d'URI (encodage géré par MockMvc).
        mockMvc.perform(get("/matiere/{m}", "Géométrie"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("La symétrie")))
                .andExpect(content().string(containsString("Les solides")))
                .andExpect(content().string(containsString("Le périmètre")))
                .andExpect(content().string(containsString("Les quadrilatères")));
    }

    // Nouveaux contenus : jeux Grandeurs et mesures ajoutés (matière Mathématiques).
    @Test
    void matiereMathematiquesListsNewGrandeursGames() throws Exception {
        // /matiere/Math%C3%A9matiques via le template d'URI (encodage géré par MockMvc).
        mockMvc.perform(get("/matiere/{m}", "Mathématiques"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les masses")))
                .andExpect(content().string(containsString("Les contenances")))
                // L'apostrophe du libellé est échappée par Thymeleaf (th:text) -> &#39; dans le HTML servi.
                .andExpect(content().string(containsString("Lire l&#39;heure")))
                .andExpect(content().string(containsString("Compare les grandeurs")));
    }

    // Nouveaux contenus CM1 découvrables par l'axe niveau (solides, masses, contenances, heure).
    @Test
    void niveauCm1ListsNewGames() throws Exception {
        mockMvc.perform(get("/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les solides")))
                .andExpect(content().string(containsString("Les masses")))
                .andExpect(content().string(containsString("Les contenances")))
                // L'apostrophe du libellé est échappée par Thymeleaf (th:text) -> &#39; dans le HTML servi.
                .andExpect(content().string(containsString("Lire l&#39;heure")));
    }

    // Nouveaux contenus CP découvrables par l'axe niveau (symétrie, comparaison de grandeurs).
    @Test
    void niveauCpListsNewGames() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("La symétrie")))
                .andExpect(content().string(containsString("Compare les grandeurs")));
    }
}
