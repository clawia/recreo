package com.recreo.adapter.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S1-1 : filet de regression recommande par theo-archi (avis de faisabilite du sprint 1) sur les
 * trois fautes precises nommees par nadia-prof (FICHE-bandit-manchot-additions-cp.md, motif 1).
 * Comparaison de sous-chaine litterale, jamais une expression reguliere (ADR-0008,
 * AssertionsWebTest). Ne remplace pas la relecture de fond du relecteur-pedagogique.
 *
 * <p>ADR-0013 : le perimetre balaye (quels gabarits sont controles) derive du repertoire de
 * gabarits, jamais d'une liste ecrite a la main - c'est cette derivation qui manquait a
 * {@code fragments/erreur.html} avant ce commit. Le critere (les fautes reprochees) reste une
 * liste, ce qui est permis : c'est un motif refuse, pas un perimetre.
 */
class TexteAccentueDesGabaritsTest {

    private static final Path GABARITS_DE_PRODUCTION = Path.of("src/main/resources/templates");

    /** ADR-0013 : le jeu fautif de ce garde-fou, un gabarit absent de la production. */
    private static final Path GABARITS_DU_CONTRE_EXEMPLE =
            Path.of("src/test/resources/contreexemple/TexteAccentueDesGabaritsTest");

    /** ADR-0013 obligation 1 : le perimetre balaye vient du repertoire, jamais d'une liste. */
    private static List<Path> tousLesGabarits(Path racine) {
        try (Stream<Path> arborescence = Files.walk(racine)) {
            return arborescence
                    .filter(Files::isRegularFile)
                    .filter(fichier -> fichier.getFileName().toString().endsWith(".html"))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /**
     * ADR-0013 obligation 2 : chaque gabarit derive produit une assertion, sans exception - la
     * lecture du fichier ne depend d'aucune propriete facultative qui pourrait en exempter un.
     */
    private static void aucunGabaritNeReintroduitUneFauteConnue(Path racineDesGabarits) {
        List<Path> gabarits = tousLesGabarits(racineDesGabarits);
        assertThat(gabarits)
                .as("au moins un gabarit doit exister sous %s", racineDesGabarits)
                .isNotEmpty();
        for (Path gabarit : gabarits) {
            String source;
            try {
                source = Files.readString(gabarit);
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
            assertThat(source)
                    .as("%s ne doit pas reintroduire une faute deja corrigee", gabarit)
                    .doesNotContain("ca.", "reponse etait", "la partie est terminee", "d'avoir joue",
                            "Récréo");
        }
    }

    @Test
    void aucunGabaritDeProductionNeReintroduitUneFauteDejaCorrigee() {
        aucunGabaritNeReintroduitUneFauteConnue(GABARITS_DE_PRODUCTION);
    }

    /**
     * ADR-0013 obligation 3 : la preuve de morsure porte sur un objet neuf, absent de la
     * production - pas sur la mutation d'un gabarit existant. gabarit-neuf-fautif.html n'a jamais
     * ete livre : seul un perimetre derive du repertoire peut le voir.
     */
    @Test
    void leGardeFouMordSurUnGabaritAbsentDeLaProduction() {
        assertThatThrownBy(() -> aucunGabaritNeReintroduitUneFauteConnue(GABARITS_DU_CONTRE_EXEMPLE))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("gabarit-neuf-fautif.html");
    }

    /**
     * S02, reformulation de nadia-prof (FICHE-bandit-manchot-additions-cp.md, verdict REFUSE) :
     * le message inconditionnel de fin de partie ne doit pas revenir, quel que soit le score. Vise
     * un gabarit nomme pour une propriete qui lui est propre, ce n'est pas un balayage (ADR-0013).
     */
    @Test
    void leMessageDeFinDePartieRefuseParNadiaNeRevientPas() throws IOException {
        String retour = Files.readString(GABARITS_DE_PRODUCTION.resolve("fragments/retour.html"));

        assertThat(retour)
                .contains("C'est fini !")
                .doesNotContain("Bravo, tu as bien joué");
    }

    /**
     * S11, arbitrage prealable de nadia-prof (FICHE-bandit-manchot-additions-cp.md, 2026-08-14,
     * CONF-3) : le texte de l'ecran de confirmation avant abandon est integralement accentue et ne
     * pose plus de question, les deux boutons etant le choix. Vise un gabarit nomme pour une
     * propriete qui lui est propre, ce n'est pas un balayage (ADR-0013) - aucune faute connue de
     * ce texte-la ne pouvait figurer dans la liste de la section precedente puisqu'il est neuf.
     */
    @Test
    void leTexteDeConfirmationEstCeluiArbitreParNadiaProf() throws IOException {
        String confirmation =
                Files.readString(GABARITS_DE_PRODUCTION.resolve("fragments/confirmation.html"));
        Document fragment = Jsoup.parse(confirmation);

        assertThat(fragment.select(".question-confirmation").text())
                .isEqualTo("Si tu pars, ta partie s'arrête.");
    }

    /**
     * S5-8 (DEC-0010) : l'utilisatrice corrige le nom du produit, "Recreo" sans accent, et
     * remplace ainsi le point 7 de DEC-0007 execute par S3-9. Un nom propre de produit n'est pas
     * un mot de vocabulaire que l'equipe redige pour apprendre a l'enfant a lire ou a
     * orthographier ; l'exigence d'accentuation de DEC-0002 ne le concerne pas. Vise un gabarit
     * nomme pour une propriete qui lui est propre, ce n'est pas un balayage (ADR-0013).
     */
    @Test
    void leTitreDuProduitSecritRecreoSansAccent() throws IOException {
        String accueil = Files.readString(GABARITS_DE_PRODUCTION.resolve("accueil.html"));
        Document page = Jsoup.parse(accueil);

        assertThat(page.title()).isEqualTo("Recreo");
        assertThat(page.select("h1").text()).isEqualTo("Recreo");
    }
}
