package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrontieresDuJavaScriptTest {

    private static final Path JS = Path.of("src/main/resources/static/js");
    private static final Path GABARITS = Path.of("src/main/resources/templates");
    private static final long PLAFOND_OCTETS = 4096;
    private static final int PLAFOND_LIGNES = 100;

    private static final List<String> INTERDITS_JS = List.of(
            "fetch(", "XMLHttpRequest", "JSON.parse", "JSON.stringify",
            "localStorage", "sessionStorage", "eval(",
            "score", "correct", "bonneReponse", "reponse");

    private static final List<String> MARQUEURS_DE_CORRECTION = List.of(
            "data-correct", "data-bonne", "data-solution", "data-reponse");

    private static Stream<Path> fichiers(Path racine, String suffixe) throws IOException {
        return Files.walk(racine).filter(Files::isRegularFile)
                .filter(fichier -> fichier.getFileName().toString().endsWith(suffixe));
    }

    private static List<Path> javaScriptMaison() throws IOException {
        return fichiers(JS, ".js")
                .filter(fichier -> !fichier.getFileName().toString().startsWith("htmx"))
                .toList();
    }

    @Test
    void leJavaScriptDAgrementTientDansSonBudget() throws IOException {
        List<Path> fichiers = javaScriptMaison();
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            assertThat(Files.size(fichier))
                    .as("ADR-0008 : %s depasse le budget de %d octets", fichier, PLAFOND_OCTETS)
                    .isLessThanOrEqualTo(PLAFOND_OCTETS);
            assertThat(Files.readAllLines(fichier))
                    .as("ADR-0008 : %s depasse %d lignes", fichier, PLAFOND_LIGNES)
                    .hasSizeLessThanOrEqualTo(PLAFOND_LIGNES);
        }
    }

    @Test
    void leJavaScriptNePrendAucuneDecisionDeJeu() throws IOException {
        List<Path> fichiers = javaScriptMaison();
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            String source = Files.readString(fichier);
            assertThat(INTERDITS_JS.stream().filter(source::contains).toList())
                    .as("ADR-0008 : %s contient du vocabulaire de decision de jeu", fichier)
                    .isEmpty();
        }
    }

    @Test
    void aucunGabaritNeMarqueLaBonneReponse() throws IOException {
        List<Path> gabarits = fichiers(GABARITS, ".html").toList();
        assertThat(gabarits)
                .as("ADR-0003 : cette regle ne prouve rien si aucun gabarit n'est passe au crible")
                .isNotEmpty();

        for (Path gabarit : gabarits) {
            String source = Files.readString(gabarit);
            assertThat(MARQUEURS_DE_CORRECTION.stream().filter(source::contains).toList())
                    .as("ADR-0008 : %s livre la bonne reponse au client", gabarit)
                    .isEmpty();
        }
    }
}
