package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S1-1 : filet de regression recommande par theo-archi (avis de faisabilite du sprint 1) sur les
 * trois fautes precises nommees par nadia-prof (FICHE-bandit-manchot-additions-cp.md, motif 1).
 * Comparaison de sous-chaine litterale, jamais une expression reguliere (ADR-0008,
 * AssertionsWebTest). Ne remplace pas la relecture de fond du relecteur-pedagogique.
 */
class TexteAccentueDesGabaritsTest {

    private static final Path GABARITS = Path.of("src/main/resources/templates");

    @Test
    void lesTroisFautesRepereesParNadiaNeReviennentPas() throws IOException {
        for (Path gabarit : List.of(
                GABARITS.resolve("accueil.html"),
                GABARITS.resolve("fragments/manche.html"),
                GABARITS.resolve("fragments/retour.html"))) {
            String source = Files.readString(gabarit);
            assertThat(source)
                    .as("%s ne doit pas reintroduire une faute deja corrigee", gabarit)
                    .doesNotContain("ca.", "reponse etait", "la partie est terminee", "d'avoir joue");
        }
    }

    /**
     * S02, reformulation de nadia-prof (FICHE-bandit-manchot-additions-cp.md, verdict REFUSE) :
     * le message inconditionnel de fin de partie ne doit pas revenir, quel que soit le score.
     */
    @Test
    void leMessageDeFinDePartieRefuseParNadiaNeRevientPas() throws IOException {
        String retour = Files.readString(GABARITS.resolve("fragments/retour.html"));

        assertThat(retour)
                .contains("C'est fini !")
                .doesNotContain("Bravo, tu as bien joué");
    }
}
