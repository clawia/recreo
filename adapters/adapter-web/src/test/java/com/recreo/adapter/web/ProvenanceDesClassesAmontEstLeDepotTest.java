package com.recreo.adapter.web;

import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.mechanic.GameMechanic;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * S16-7 : les classes des modules amont doivent etre chargees depuis l'arbre du depot - le
 * {@code target/classes} que le reacteur vient de compiler - jamais depuis le depot local Maven,
 * ou dort l'archive d'un {@code mvn install} anterieur, potentiellement plus ancienne que le
 * worktree qu'on croit mesurer. Mecanisme enonce au mot dans
 * {@code squad/sprints/s14-points-d-entree-par-niveau/avis-faisabilite.md}, section 12.
 *
 * <p><b>Ce qu'il ferme.</b> Une mesure partielle ({@code mvn -pl adapters/adapter-web test}, sans
 * {@code -am}) resout {@code recreo-domain} et {@code recreo-application} depuis le depot local au
 * lieu de les construire : la commande reussit, le chiffre s'affiche, et il decrit un autre arbre
 * que celui du worktree. Ce garde-fou mord dans les deux sens, faux rouge comme faux vert, parce
 * qu'il ne juge pas un verdict mais la provenance de ce qui a ete mesure - {@code ADR-0045},
 * section "Prerequis de mesure", et {@code CLAUDE.md}, section "Prerequis de build", qui nomme ce
 * mode de defaillance sans le fermer.
 *
 * <p><b>Derivation (ADR-0013).</b> La racine du depot se derive en remontant jusqu'au pom
 * agregateur, comme {@code LesGardeFousSontMisALEpreuveTest} le fait deja (bootstrap) : aucun
 * chemin de poste, ni ici ni dans un message d'echec. La provenance se lit sur l'URL que rend le
 * chargeur de classes pour une classe amont reellement utilisee par ce module - un port entrant
 * pour {@code application}, une mecanique pour {@code domain}. Il suffit qu'elle vive sous le
 * {@code target/} du module amont dans l'arbre du depot - {@code classes/} pendant une execution
 * de tests, l'archive packagee une fois {@code package} atteint : les deux sont mesures a
 * l'identique, la seule provenance rejetee est le depot local Maven.
 *
 * <p><b>Preuve de morsure : la commande elle-meme, rien a inventer comme fixture.</b> Vert sur
 * {@code bash outils/mvn-jdk21.sh -B clean verify} (reacteur complet) ; rouge sur une invocation
 * partielle du meme module sans {@code -am}, des qu'une version d'un module amont differente du
 * worktree est installee dans le depot local.
 */
class ProvenanceDesClassesAmontEstLeDepotTest {

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Le pom agregateur, seul endroit du depot qui dise quels modules existent vraiment. */
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

    private static void verifierProvenance(Class<?> classeAmont, String moduleAmont)
            throws URISyntaxException {
        Path racine = racineDuDepot();
        Path racineDuModule = racine.resolve(moduleAmont).resolve("target");

        CodeSource source = classeAmont.getProtectionDomain().getCodeSource();
        assertThat(source)
                .as("%s doit exposer un CodeSource : impossible de juger sa provenance sans lui",
                        classeAmont.getName())
                .isNotNull();
        Path provenance = Path.of(source.getLocation().toURI());

        assertThat(provenance)
                .as("%s (module %s) doit etre charge depuis %s - le target/ que le reacteur vient "
                        + "de construire pour ce module (classes ou jar, selon la phase), jamais "
                        + "depuis le depot local Maven : provenance mesuree %s. Invocation "
                        + "partielle sans -am, ou reacteur incomplet",
                        classeAmont.getSimpleName(), moduleAmont, racineDuModule, provenance)
                .startsWith(racineDuModule);
    }

    @Test
    void laClasseDApplicationVientDuTargetDuDepotEtNonDuDepotLocal() throws URISyntaxException {
        verifierProvenance(PartieUseCase.class, "application");
    }

    @Test
    void laClasseDeDomainVientDuTargetDuDepotEtNonDuDepotLocal() throws URISyntaxException {
        verifierProvenance(GameMechanic.class, "domain");
    }
}
