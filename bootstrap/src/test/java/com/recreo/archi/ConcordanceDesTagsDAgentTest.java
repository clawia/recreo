package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * s03 : la liste des tags de commit connus est recopiee a la main dans trois documents de
 * redaction (CLAUDE.md, squad/README.md, squad/EQUIPE.md), sans qu'aucun automate n'en verifie la
 * concordance avec {@code .github/scripts/verifier-prefixes.sh} - le seul endroit que la CI
 * execute reellement (ADR-0003 : {@code verifier-prefixes-test.sh} le prouve, hors de portee de
 * cette suite Maven).
 *
 * <p>Ce test derive l'ensemble canonique des tags de la variable {@code TAGS} du script, et
 * verifie que chacun des trois documents le cite integralement. Il ne verifie pas l'absence de
 * tag surnumeraire dans un document (un texte de redaction contient d'autres jetons entre
 * crochets - renvois, exemples de format - qui ne sont pas des tags) : c'est une limite assumee,
 * pas un oubli. Le sens de l'erreur retenu est celui d'ADR-0013 : une omission passe inapercue en
 * silence, une citation surnumeraire se voit a la lecture du document.
 */
class ConcordanceDesTagsDAgentTest {

    private static final Pattern LIGNE_DES_TAGS = Pattern.compile("TAGS='([A-Z|]+)'");

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Le pom agregateur, seul endroit du depot qui situe sa propre racine de facon fiable. */
    private static Path racineDuDepot() {
        Path candidat = Path.of("").toAbsolutePath();
        while (candidat != null) {
            Path pom = candidat.resolve("pom.xml");
            if (Files.isRegularFile(pom) && lire(pom).contains("<modules>")) {
                return candidat;
            }
            candidat = candidat.getParent();
        }
        throw new IllegalStateException("aucun pom.xml agregateur au-dessus du repertoire courant");
    }

    private static Set<String> tagsCanoniques(Path racine) {
        String script = lire(racine.resolve(Path.of(".github", "scripts", "verifier-prefixes.sh")));
        Matcher trouvaille = LIGNE_DES_TAGS.matcher(script);
        assertThat(trouvaille.find())
                .as("verifier-prefixes.sh doit declarer TAGS='...' : c'est la source canonique, "
                        + "celle que la CI execute reellement")
                .isTrue();
        return Set.of(trouvaille.group(1).split("\\|"));
    }

    @Test
    void chaqueTagCanoniqueEstCiteDansChaqueDocumentDeRedaction() {
        Path racine = racineDuDepot();
        Set<String> tags = tagsCanoniques(racine);
        assertThat(tags).as("au moins un tag doit etre declare dans verifier-prefixes.sh").isNotEmpty();

        for (Path document : List.of(
                racine.resolve("CLAUDE.md"),
                racine.resolve(Path.of("squad", "README.md")),
                racine.resolve(Path.of("squad", "EQUIPE.md")))) {
            String contenu = lire(document);
            for (String tag : tags) {
                assertThat(contenu)
                        .as("%s doit citer le tag [%s], declare dans verifier-prefixes.sh", document, tag)
                        .contains("[" + tag + "]");
            }
        }
    }
}
