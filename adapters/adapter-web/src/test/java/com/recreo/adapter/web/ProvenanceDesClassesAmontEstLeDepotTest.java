package com.recreo.adapter.web;

import com.recreo.application.port.in.PartieUseCase;
import com.recreo.domain.mechanic.GameMechanic;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.security.CodeSource;
import java.time.Instant;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
 *
 * <p><b>Dette 8.2 (s17) : l'emplacement ne suffit pas, la fraicheur est verifiee en plus.</b> Un
 * {@code target/classes} ou un jar present au bon emplacement peut dater d'une compilation
 * anterieure a la derniere modification du {@code .java} - un {@code -pl} sans {@code -am} lance
 * apres une modification de source non recompilee produit exactement ce cas, et l'emplacement seul
 * ne le voit pas. La comparaison ajoutee lit l'horodatage du {@code .class} compile (dans le
 * repertoire ou dans le jar, selon la phase atteinte) et celui du {@code .java} source, et exige
 * que le premier ne soit jamais anterieur au second. <b>Ce que cette comparaison ne prouve pas</b> :
 * un {@code touch} sur le {@code .class} suffirait a la tromper sans recompiler quoi que ce soit, et
 * elle ne remplace donc pas un {@code clean} en amont d'une mesure, seule garantie reelle de
 * fraicheur.
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
            throws URISyntaxException, IOException {
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

        String cheminBinaire = classeAmont.getName().replace('.', '/') + ".class";
        Path sourceJava = racine.resolve(moduleAmont).resolve("src/main/java")
                .resolve(classeAmont.getName().replace('.', '/') + ".java");
        FileTime horodatageClasse = horodatageDeLaClasseCompilee(provenance, cheminBinaire);
        FileTime horodatageSource = Files.getLastModifiedTime(sourceJava);
        verifierFraicheur(classeAmont.getSimpleName(), moduleAmont, provenance, sourceJava,
                horodatageClasse, horodatageSource);
    }

    /**
     * Lit l'horodatage du {@code .class} compile, que la provenance soit un repertoire
     * {@code target/classes} (execution de tests) ou un jar packaged (phase {@code package}
     * atteinte) : les deux portent l'horodatage de compilation de l'entree elle-meme.
     */
    private static FileTime horodatageDeLaClasseCompilee(Path provenance, String cheminBinaire)
            throws IOException {
        if (Files.isDirectory(provenance)) {
            return Files.getLastModifiedTime(provenance.resolve(cheminBinaire));
        }
        try (JarFile jar = new JarFile(provenance.toFile())) {
            JarEntry entree = jar.getJarEntry(cheminBinaire);
            assertThat(entree)
                    .as("%s absente de %s", cheminBinaire, provenance)
                    .isNotNull();
            return entree.getLastModifiedTime();
        }
    }

    /**
     * Dette 8.2 (s17) : la comparaison elle-meme, extraite pour pouvoir se prouver mordante sur un
     * jeu fautif (ADR-0013) independamment de tout {@code CodeSource} reel. Un {@code .class}
     * strictement plus ancien que son {@code .java} signale un target non recompile apres une
     * modification du source.
     *
     * <p><b>Ce que cette comparaison ne prouve pas</b> : un {@code touch} sur le {@code .class}
     * suffirait a la tromper sans recompiler quoi que ce soit, et elle ne remplace donc pas un
     * {@code clean} en amont d'une mesure, seule garantie reelle de fraicheur.
     */
    private static void verifierFraicheur(String nomClasse, String moduleAmont, Path provenance,
            Path sourceJava, FileTime horodatageClasse, FileTime horodatageSource) {
        assertThat(horodatageClasse.toInstant().isBefore(horodatageSource.toInstant()))
                .as("%s (module %s) : le .class compile (%s, horodatage %s) est plus ancien que son "
                        + ".java source (%s, horodatage %s) - signe d'un target perime, jamais "
                        + "recompile depuis une modification du source. Cette comparaison ne prouve "
                        + "ni qu'un touch n'a pas rajeuni le .class sans le recompiler, ni qu'aucun "
                        + "mvn n'a ete saute entre-temps : un clean en amont d'une mesure reste la "
                        + "seule garantie",
                        nomClasse, moduleAmont, provenance, horodatageClasse, sourceJava,
                        horodatageSource)
                .isFalse();
    }

    @Test
    void laClasseDApplicationVientDuTargetDuDepotEtNonDuDepotLocal()
            throws URISyntaxException, IOException {
        verifierProvenance(PartieUseCase.class, "application");
    }

    @Test
    void laClasseDeDomainVientDuTargetDuDepotEtNonDuDepotLocal() throws URISyntaxException, IOException {
        verifierProvenance(GameMechanic.class, "domain");
    }

    /** ADR-0013 : le jeu fautif de ce garde-fou, un .java et un .class dont l'ordre est impose ici. */
    private static final Path FIXTURE_FRAICHEUR =
            Path.of("src/test/resources/contreexemple/ProvenanceDesClassesAmontEstLeDepotTest");

    /**
     * Dette 8.2 (s17), ADR-0013 : preuve de morsure sur un jeu fautif reel. Git ne conserve aucun
     * horodatage de fichier au clonage - un .class committe "perime" ne le resterait pas apres un
     * checkout - donc l'ordre des horodatages est impose ici, au moment de l'epreuve, sur les deux
     * fichiers du jeu fautif.
     */
    @Test
    void unClassPlusAncienQueSonJavaSourceFaitRougirLeGardeFou() throws IOException {
        Path classeCompilee = FIXTURE_FRAICHEUR.resolve("Perimee.class");
        Path sourceJava = FIXTURE_FRAICHEUR.resolve("Perimee.java");
        assertThat(classeCompilee).isRegularFile();
        assertThat(sourceJava).isRegularFile();

        Instant maintenant = Instant.now();
        Files.setLastModifiedTime(sourceJava, FileTime.from(maintenant));
        Files.setLastModifiedTime(classeCompilee, FileTime.from(maintenant.minusSeconds(60)));

        assertThatThrownBy(() -> verifierFraicheur("Perimee", "fixture", classeCompilee, sourceJava,
                Files.getLastModifiedTime(classeCompilee), Files.getLastModifiedTime(sourceJava)))
                .as("un .class plus ancien que son .java source doit faire rougir le garde-fou")
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("plus ancien");
    }
}
