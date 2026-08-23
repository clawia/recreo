package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrontieresDuJavaScriptTest {

    private static final Path STATIQUE = Path.of("src/main/resources/static");
    private static final Path TIERS = STATIQUE.resolve("vendor");
    private static final Path GABARITS = Path.of("src/main/resources/templates");
    private static final long PLAFOND_OCTETS = 4096;
    private static final int PLAFOND_LIGNES = 100;

    /**
     * Amendement ADR-0026 : {@code proposition} et {@code colonne} rejoignent la liste. ADR-0008
     * interdisait le vocabulaire du <b>verdict</b> ; il ne disait rien du vocabulaire du
     * <b>tirage</b>, et un rouleau ecrit en JavaScript n'a besoin d'aucun des onze mots precedents -
     * il lui suffit de relire {@code .propositions} et de decider laquelle arrive dans quelle
     * colonne. Ces deux mots-la nomment exactement ce que le serveur doit avoir decide avant que le
     * fragment ne le quitte, et ils sont absents de {@code recreo.js} au jour de cet amendement.
     */
    private static final List<String> INTERDITS_JS = List.of(
            "fetch(", "XMLHttpRequest", "JSON.parse", "JSON.stringify",
            "localStorage", "sessionStorage", "eval(",
            "score", "correct", "bonneReponse", "reponse",
            "proposition", "colonne");

    private static final List<String> MARQUEURS_DE_CORRECTION = List.of(
            "data-correct", "data-bonne", "data-solution", "data-reponse");

    private static Stream<Path> fichiers(Path racine, String suffixe) throws IOException {
        return Files.walk(racine).filter(Files::isRegularFile)
                .filter(fichier -> fichier.getFileName().toString().endsWith(suffixe));
    }

    /**
     * ADR-0014 : ce qui distingue le JavaScript maison du JavaScript tiers n'est plus un prefixe de
     * nom de fichier ecrit ici (htmx), c'est l'emplacement - static/vendor/ est le seul endroit ou
     * une copie tierce a le droit de vivre, et provenance.txt dit ce qu'elle est. Le budget d'ADR-0008
     * n'a jamais eu de sens pour une bibliotheque tierce ; il en a pour tout le reste, y compris pour
     * un fichier maison depose ailleurs que sous js/.
     */
    private static List<Path> javaScriptMaison() throws IOException {
        return fichiers(STATIQUE, ".js")
                .filter(fichier -> !fichier.startsWith(TIERS))
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

    /**
     * S18, S17-C : troisieme critere d'acceptation de la story (backlog), reformule par
     * theo-archi comme une propriete textuelle plutot que comportementale (avis-faisabilite.md,
     * section 4.3, motif 4) - le mecanisme retenu deplace la declaration de {@code tabindex} vers
     * les gabarits, et {@code recreo.js} ne doit plus jamais la poser lui-meme, sur aucune cible,
     * qu'elle soit ou non nativement focalisable.
     */
    @Test
    void leScriptDeFocusNeMuteJamaisTabindex() throws IOException {
        List<Path> fichiers = javaScriptMaison();
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            String source = Files.readString(fichier);
            assertThat(source)
                    .as("S17-C : %s ne doit plus poser tabindex, desormais declare par les gabarits",
                            fichier)
                    .doesNotContain("tabindex");
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
