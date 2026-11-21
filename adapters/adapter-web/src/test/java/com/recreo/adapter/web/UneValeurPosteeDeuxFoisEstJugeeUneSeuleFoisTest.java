package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Defaut releve sur un iPad sous iOS 15 (Safari 15.6) : le journal du serveur montrait, pour un
 * appui sur la bonne reponse "5", la valeur recue {@code brut=[5,5]} - le parametre
 * {@code propositionChoisie} arrivait deux fois, et Spring joint deux valeurs d'un meme parametre par
 * une virgule. "5,5" n'est pas la bonne reponse : la manche etait jugee fausse, l'indice s'affichait
 * et, au troisieme appui, la reponse etait revelee.
 *
 * <p>Ce test envoie le meme parametre deux fois, comme ce navigateur, et exige le verdict de la
 * valeur envoyee une seule fois.</p>
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class UneValeurPosteeDeuxFoisEstJugeeUneSeuleFoisTest {

    private static final String JEU_ID = "jeu-repete";
    private static final String BONNE_REPONSE = "bonne";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void laBonneReponsePosteeDeuxFoisEstJugeeJuste() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", JEU_ID).session(session));

        String ecran = mockMvc.perform(post("/parties/{jeuId}/coups", JEU_ID)
                        .session(session).param("propositionChoisie", BONNE_REPONSE, BONNE_REPONSE))
                .andReturn().getResponse().getContentAsString();

        assertThat(ecran)
                .as("la bonne reponse envoyee deux fois doit etre jugee comme envoyee une fois")
                .contains("Bravo")
                .doesNotContain("class=\"indice\"");
    }

    /**
     * Meme defaut sur la route de designation ({@code zoneDesignee}). Le jeu de test a deux items dont
     * la bonne zone differe ("tableau" ou "fenetre") et l'ordre du tirage n'est pas fixe : les deux
     * zones sont donc postees, chacune deux fois, et l'une des deux reponses doit etre jugee juste.
     */
    @Test
    void uneZonePosteeDeuxFoisEstJugeeUneSeuleFois() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(post("/parties/{jeuId}", PartieControllerTest.JEU_DESIGNATION_ID).session(session));

        String premiere = mockMvc.perform(post("/parties/{jeuId}/designations",
                                PartieControllerTest.JEU_DESIGNATION_ID)
                        .session(session).param("zoneDesignee", "tableau", "tableau"))
                .andReturn().getResponse().getContentAsString();
        String seconde = mockMvc.perform(post("/parties/{jeuId}/designations",
                                PartieControllerTest.JEU_DESIGNATION_ID)
                        .session(session).param("zoneDesignee", "fenetre", "fenetre"))
                .andReturn().getResponse().getContentAsString();

        assertThat(premiere + seconde)
                .as("une des deux zones, envoyee deux fois, est la bonne et doit etre jugee juste")
                .contains("Bravo");
    }
}
