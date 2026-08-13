package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * S10 : {@code recreo.js} appelle {@code document.body.addEventListener(...)} des sa lecture, sans
 * attendre {@code DOMContentLoaded}. Charge nu dans le {@code <head>} d'{@code accueil.html},
 * {@code <body>} n'existe pas encore au moment ou le script s'execute : l'appel leve une
 * {@code TypeError}, l'ecouteur n'est jamais enregistre, et le focus que M13 exige ne bouge jamais -
 * y compris avant ce sprint, puisque ce script est dans l'en-tete depuis qu'il existe.
 * Consequence mesuree par nadia-prof : {@code POST /retour} appelant {@code abandonner()}, Tabulation
 * puis Entree apres n'importe quelle reponse detruit la partie sans confirmation (clause 1 de M13,
 * bloquante).
 *
 * <p>Ce test garde la position du script dans le gabarit rendu : {@code defer} reporte son execution
 * a la fin de l'analyse du document, quand {@code <body>} existe deja, sans le sortir du
 * {@code <head>}. Il ne garde pas le comportement de {@code recreo.js} lui-meme, ce que
 * {@link RecreoJsDeplaceLeFocusTest} verifie en executant reellement le fichier.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class LeScriptDAgrementSExecuteApresLeCorpsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void leScriptRecreoJsEstDiffereJusquACeQueLeCorpsExiste() throws Exception {
        String html = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        Document page = Jsoup.parse(html);

        Elements script = page.select("script[src=\"/js/recreo.js\"]");
        assertThat(script)
                .as("recreo.js doit rester servi depuis les ressources statiques")
                .hasSize(1);
        assertThat(script.first().hasAttr("defer"))
                .as("sans defer, ce script s'execute pendant l'analyse du <head> : document.body y "
                        + "vaut encore null, document.body.addEventListener leve une TypeError et "
                        + "l'ecouteur de focus (M13) n'est jamais enregistre")
                .isTrue();
    }
}
