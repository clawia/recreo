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
 * Nouveaux contenus : le jeu « Remets la phrase dans l'ordre » (mécanique puzzle, déclinaison
 * « mots » — {@code phrase-cp}/{@code phrase-cm1}, matière Français) et deux droites graduées
 * ({@code droite-graduee-cinquante-cp} 0→50, {@code droite-graduee-milliers-cm1} 0→1000). Vérifie
 * leur découvrabilité par l'axe niveau ({@code /niveau/{n}}) et par matière ({@code /matiere/{m}}),
 * dans le style du sibling {@code GeometrieEtMesuresNavigationWebTest}.
 *
 * <p>NB : Thymeleaf ({@code th:text}) échappe l'apostrophe des libellés en {@code &#39;} dans le
 * HTML servi — les libellés à apostrophe sont donc assertés sous leur forme ÉCHAPPÉE.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PhraseEtDroiteNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void niveauCpListsNewGames() throws Exception {
        mockMvc.perform(get("/niveau/CP"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les longueurs")))
                .andExpect(content().string(containsString("Les durées")))
                // Apostrophe échappée par Thymeleaf (th:text) -> &#39; dans le HTML servi.
                .andExpect(content().string(containsString("Remets la phrase dans l&#39;ordre")))
                .andExpect(content().string(containsString("Reconstitue l&#39;animal")))
                .andExpect(content().string(containsString("Chiffre et mot-nombre")))
                .andExpect(content().string(containsString("Memory des doubles")))
                .andExpect(content().string(containsString("Place le nombre (jusqu&#39;à 50)")));
    }

    @Test
    void niveauCm1ListsNewGames() throws Exception {
        mockMvc.perform(get("/niveau/CM1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les aires")))
                .andExpect(content().string(containsString("Les synonymes")))
                .andExpect(content().string(containsString("Memory des tables")))
                .andExpect(content().string(containsString("Mots de sciences")))
                // Apostrophe échappée par Thymeleaf (th:text) -> &#39; dans le HTML servi.
                .andExpect(content().string(containsString("Remets la phrase dans l&#39;ordre")))
                .andExpect(content().string(containsString("Place le nombre (les milliers)")));
    }

    @Test
    void matiereGeometrieListsLesAires() throws Exception {
        // /matiere/G%C3%A9om%C3%A9trie via le template d'URI (encodage géré par MockMvc).
        mockMvc.perform(get("/matiere/{m}", "Géométrie"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Les aires")));
    }
}
