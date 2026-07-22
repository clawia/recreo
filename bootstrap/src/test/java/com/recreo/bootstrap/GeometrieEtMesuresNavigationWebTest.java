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
}
