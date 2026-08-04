package com.recreo.domain.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0004, complement au bytecode : ArchUnit voit les types, pas les litteraux. Ce test lit les
 * sources des mecaniques et refuse le vocabulaire de matiere.
 */
class AucunVocabulaireDeMatiereTest {

    private static final Path MECANIQUES = Path.of("src/main/java/com/recreo/domain/mechanics");

    private static final List<String> VOCABULAIRE_DE_MATIERE = List.of(
            "addition", "soustraction", "multiplication", "operande", "somme",
            "calcul", "syllabe", "conjugaison", "fraction");

    @Test
    void aucuneMecaniqueNeNommeUneMatiere() throws IOException {
        List<Path> sources = sourcesDesMecaniques();

        assertThat(sources)
                .as("ADR-0004 : cette regle ne prouve rien s'il n'y a aucune mecanique a lire")
                .isNotEmpty();

        for (Path source : sources) {
            String texte = Files.readString(source).toLowerCase(Locale.ROOT);
            assertThat(VOCABULAIRE_DE_MATIERE.stream().filter(texte::contains).toList())
                    .as("ADR-0004 : %s nomme une matiere, une mecanique n'en connait aucune", source)
                    .isEmpty();
        }
    }

    private static List<Path> sourcesDesMecaniques() throws IOException {
        assertThat(MECANIQUES)
                .as("ADR-0004 : le paquet des mecaniques doit exister pour que cette regle morde")
                .isDirectory();
        try (Stream<Path> arborescence = Files.walk(MECANIQUES)) {
            return arborescence.filter(fichier -> fichier.toString().endsWith(".java")).toList();
        }
    }
}
