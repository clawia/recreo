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
 * 3e axe de navigation (par matiere, cf. CatalogueQuery#matieres/#byMatiere) : la page
 * {@code /matieres} liste les matieres distinctes du catalogue, et {@code /matiere/{m}}
 * liste les jeux de cette matiere.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MatiereNavigationWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void matieresPageListsTheDistinctSubjects() throws Exception {
        mockMvc.perform(get("/matieres"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Mathématiques")))
                .andExpect(content().string(containsString("Français")));
    }

    @Test
    void mathematiquesPageListsMathGames() throws Exception {
        mockMvc.perform(get("/matiere/{m}", "Mathématiques"))
                .andExpect(status().isOk())
                // Au moins un jeu de maths y figure (ex. le memory des quantites, matiere « Mathématiques »).
                .andExpect(content().string(containsString("Memory des quantités")));
    }
}
