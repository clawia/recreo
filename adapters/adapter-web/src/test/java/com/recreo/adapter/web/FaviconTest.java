package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * S2-3 : l'application a une icone reconnaissable dans l'onglet du navigateur, servie hors ligne
 * comme une ressource statique, et referencee explicitement par accueil.html plutot que de
 * dependre du seul comportement implicite /favicon.ico.
 */
@WebMvcTest(PartieController.class)
@Import(PartieControllerTest.JeuDeTestConfiguration.class)
class FaviconTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void laRequeteFaviconRepond200AvecUnTypeDImage() throws Exception {
        var reponse = mockMvc.perform(get("/favicon.ico")).andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(200);
        assertThat(reponse.getContentType()).as("le type doit annoncer une image, jamais une page HTML")
                .isNotNull()
                .startsWith("image/");
    }

    @Test
    void lIconeExisteCommeRessourceStatiqueDAdapterWeb() {
        assertThat(Path.of("src/main/resources/static/favicon.ico")).isRegularFile();
    }

    @Test
    void accueilReferenceExplicitementLIcone() throws IOException {
        String html = Files.readString(Path.of("src/main/resources/templates/accueil.html"));
        Document page = Jsoup.parse(html);

        assertThat(page.select("link[rel=icon]")).isNotEmpty();
    }
}
