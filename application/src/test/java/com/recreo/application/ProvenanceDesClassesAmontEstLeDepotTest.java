package com.recreo.application;

import com.recreo.domain.mechanic.GameMechanic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.security.CodeSource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * S16-7 : les classes d'un module amont doivent etre chargees depuis l'arbre du depot - le
 * {@code target/classes} que le reacteur vient de compiler - jamais depuis le depot local Maven,
 * ou dort l'archive d'un {@code mvn install} anterieur, potentiellement plus ancienne que le
 * worktree qu'on croit mesurer. Mecanisme enonce au mot dans
 * {@code squad/sprints/s14-points-d-entree-par-niveau/avis-faisabilite.md}, section 12.
 *
 * <p><b>Ce qu'il ferme.</b> Une mesure partielle ({@code mvn -pl application test}, sans
 * {@code -am}) resout {@code recreo-domain} depuis le depot local au lieu de le construire : la
 * commande reussit, le chiffre s'affiche, et il decrit un autre arbre que celui du worktree. Ce
 * garde-fou mord dans les deux sens, faux rouge comme faux vert, parce qu'il ne juge pas un
 * verdict mais la provenance de ce qui a ete mesure - {@code ADR-0045}, section "Prerequis de
 * mesure", et {@code CLAUDE.md}, section "Prerequis de build", qui nomme ce mode de defaillance
 * sans le fermer.
 *
 * <p><b>Derivation (ADR-0013).</b> La racine du depot se derive en remontant jusqu'au pom
 * agregateur, comme {@code LesGardeFousSontMisALEpreuveTest} le fait deja (bootstrap) : aucun
 * chemin de poste, ni ici ni dans un message d'echec. La provenance se lit sur l'URL que rend le
 * chargeur de classes pour une classe amont reellement utilisee par ce module, et il suffit
 * qu'elle vive sous le {@code target/} du module amont dans l'arbre du depot - {@code classes/}
 * pendant une execution de tests, l'archive packagee une fois {@code package} atteint : les deux
 * sont mesures a l'identique, la seule provenance rejetee est le depot local Maven.
 *
 * <p><b>Preuve de morsure : la commande elle-meme, rien a inventer comme fixture.</b> Vert sur
 * {@code bash outils/mvn-jdk21.sh -B clean verify} (reacteur complet) ; rouge sur une invocation
 * partielle du meme module sans {@code -am}, des qu'une version de {@code recreo-domain} differente
 * du worktree est installee dans le depot local.
 *
 * <p><b>Dette 8.2 (s17) : l'emplacement ne suffit pas, la fraicheur est verifiee en plus.</b> Un
 * {@code target/classes} ou un jar present au bon emplacement peut dater d'une compilation
 * anterieure a la derniere modification du {@code .java} - un {@code -pl} sans {@code -am} lance
 * apres une modification de source non recompilee produit exactement ce cas, et l'emplacement seul
 * ne le voit pas. La comparaison ajoutee lit l'horodatage du {@code .class} compile (dans le
 * repertoire ou dans le jar, selon la phase atteinte) et celui du {@code .java} source, et exige
 * que le premier ne soit jamais anterieur au second.
 *
 * <p><b>Ce que cette comparaison ne prouve pas, dans les deux sens</b> (le second nomme le
 * 2026-08-19 par {@code yanis-qa}, rapport qualite de s17, point 3 : il l'a mesure, le premier
 * seul etait ecrit ici). Elle ne compare que deux horodatages, jamais un contenu ni une empreinte,
 * donc elle est aveugle symetriquement :
 * <ul>
 *   <li><b>faux vert</b> : un {@code touch} sur le {@code .class} la trompe sans recompiler quoi que
 *       ce soit ;</li>
 *   <li><b>faux rouge</b> : un {@code .java} dont l'horodatage avance sans que son contenu change -
 *       un {@code touch}, un enregistrement sans modification, un {@code git checkout} qui reecrit le
 *       fichier - la fait rougir sur un {@code .class} parfaitement a jour.</li>
 * </ul>
 *
 * <p><b>Le faux rouge est conserve tel quel, et voici la mesure qui le justifie</b> (theo-archi,
 * 2026-08-19, JDK 21.0.10, sur l'arbre de {@code s17-theo-gardefous}). Il n'est <b>pas atteignable
 * depuis une commande qui compile</b> : apres {@code touch} sur {@code GameMechanic.java},
 * {@code mvn -pl application -am test} recompile le fichier touche - le {@code .class} passe a un
 * horodatage posterieur - et les deux clauses sont vertes. Le rouge n'apparait qu'en <b>supprimant
 * la compilation</b> ({@code -Dmaven.main.skip=true}), et il apparait alors sur un
 * {@code target/classes} dont plus rien ne garantit qu'il correspond aux sources. Autrement dit, le
 * seul geste qui produit ce faux rouge est celui qui rend la mesure non verifiable : le rouge est
 * exact sur le fond, meme quand sa cause immediate n'est pas une modification de source. Le corriger
 * demanderait de comparer un contenu a un binaire compile, donc de recompiler pour juger - un cout
 * hors de proportion avec un rouge qui ne peut pas apparaitre dans {@code mvn -B clean verify}, la
 * commande de la porte technique. Ce qui est corrige, c'est le <b>message</b> : il nomme desormais
 * les deux causes possibles, pour qu'un lecteur qui le rencontre ne cherche pas une modification de
 * source qui n'existe pas.
 *
 * <p><b>Jar d'un build reproductible (s55, CI rouge du 2026-10-08).</b> L'entree d'un jar ne porte
 * pas toujours l'horodatage de compilation : quand {@code project.build.outputTimestamp} est fixe,
 * ou, a defaut, quand la variable d'environnement {@code SOURCE_DATE_EPOCH} l'est (repli lu dans
 * {@code maven-archiver} 3.6.5, que tire {@code maven-jar-plugin} 3.5.0 de
 * {@code spring-boot-dependencies} 4.1.0), toutes les entrees recoivent ce meme horodatage fixe,
 * vers 1980. Comparee au {@code .java} du checkout, une telle entree rougit sur un jar frais. Ce cas
 * se reconnait a sa propriete definitoire - toutes les entrees du jar portent le meme horodatage -
 * et non a une valeur : la date lue depend de la valeur fixee et du fuseau de la machine virtuelle.
 * La comparaison se fait alors sur l'horodatage du fichier jar, que l'archiveur ne fixe pas. Elle
 * reste une comparaison classe contre source, rouge sur un jar plus ancien que son source. Ce
 * qu'elle perd : un {@code target/classes} perime reempaquete apres la modification du source
 * passerait, puisque l'horodatage du jar dit quand il a ete empaquete, pas quand ses classes ont
 * ete compilees - un jar reproductible n'expose plus cette information.
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
     * atteinte). Dans un jar dont toutes les entrees portent le meme horodatage - build
     * reproductible, voir la Javadoc de la classe - c'est l'horodatage du fichier jar qui est rendu.
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
            if (jar.stream().map(JarEntry::getLastModifiedTime).distinct().count() == 1) {
                return Files.getLastModifiedTime(provenance);
            }
            return entree.getLastModifiedTime();
        }
    }

    /**
     * Dette 8.2 (s17) : la comparaison elle-meme, extraite pour pouvoir se prouver mordante sur un
     * jeu fautif (ADR-0013) independamment de tout {@code CodeSource} reel. Un {@code .class}
     * strictement plus ancien que son {@code .java} signale un target non recompile apres une
     * modification du source.
     *
     * <p><b>Ce que cette comparaison ne prouve pas, dans les deux sens</b> - voir la Javadoc de la
     * classe, qui porte la mesure du 2026-08-19 : le faux rouge (horodatage du {@code .java} avance
     * sans modification de contenu) n'est atteignable qu'en supprimant la compilation, et le faux vert
     * reste entier - un {@code touch} sur le {@code .class}
     * suffirait a la tromper sans recompiler quoi que ce soit, et elle ne remplace donc pas un
     * {@code clean} en amont d'une mesure, seule garantie reelle de fraicheur.
     */
    private static void verifierFraicheur(String nomClasse, String moduleAmont, Path provenance,
            Path sourceJava, FileTime horodatageClasse, FileTime horodatageSource) {
        assertThat(horodatageClasse.toInstant().isBefore(horodatageSource.toInstant()))
                .as("%s (module %s) : le .class compile (%s, horodatage %s) est plus ancien que son "
                        + ".java source (%s, horodatage %s). Deux causes possibles, et cette "
                        + "comparaison ne les distingue pas - elle ne lit que des horodatages, jamais "
                        + "un contenu : soit le target est perime, jamais recompile depuis une "
                        + "modification du source, soit l'horodatage du .java a avance sans que son "
                        + "contenu change (touch, enregistrement a vide, git checkout) alors que la "
                        + "compilation a ete supprimee - un mvn -B clean verify, qui compile, ne peut "
                        + "pas produire ce second cas. Dans les deux cas la mesure en cours ne "
                        + "prouve rien : un clean en amont reste la seule garantie de fraicheur. "
                        + "Pour un jar dont toutes les entrees portent le meme horodatage (build "
                        + "reproductible), l'horodatage compare est celui du fichier jar",
                        nomClasse, moduleAmont, provenance, horodatageClasse, sourceJava,
                        horodatageSource)
                .isFalse();
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
                .hasMessageContaining("plus ancien")
                // s17, point 3 de yanis-qa : le message nomme les deux causes possibles, et cette
                // clause-ci est ce qui empeche la seconde de disparaitre d'une prochaine reecriture.
                // Un rouge dont la cause peut etre un horodatage avance sans modification de contenu
                // doit le dire la ou le lecteur le rencontre, pas seulement dans une Javadoc.
                .hasMessageContaining("Deux causes possibles");
    }

    /**
     * s55, ADR-0013 : le jeu fautif du cas "jar d'un build reproductible", trois jars construits ici
     * parce que git ne conserve aucun horodatage. Chacun ferme une regression distincte : le
     * premier, une exemption du jar reproductible ; le deuxieme, la lecture de l'entree fixe qui a
     * rougi la CI ; le troisieme, un repli sur le fichier jar etendu a un jar ordinaire, ou il
     * masquerait une classe perimee.
     */
    @Test
    void unJarReproductibleEstJugeSurSonFichierEtUnJarOrdinaireSurSonEntree(@TempDir Path dossier)
            throws IOException {
        Path sourceJava = FIXTURE_FRAICHEUR.resolve("Perimee.java");
        byte[] classe = Files.readAllBytes(FIXTURE_FRAICHEUR.resolve("Perimee.class"));
        Instant maintenant = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        FileTime fixe = FileTime.from(Instant.parse("1980-02-01T00:00:00Z"));
        FileTime avant = FileTime.from(maintenant.minusSeconds(120));
        FileTime apres = FileTime.from(maintenant.plusSeconds(120));
        Files.setLastModifiedTime(sourceJava, FileTime.from(maintenant));

        Path reproAncien = jar(dossier.resolve("repro-ancien.jar"), classe, fixe, fixe, avant);
        assertThatThrownBy(() -> juger(reproAncien, sourceJava))
                .as("un jar reproductible plus ancien que son .java source doit faire rougir le "
                        + "garde-fou")
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("plus ancien");

        Path reproFrais = jar(dossier.resolve("repro-frais.jar"), classe, fixe, fixe, apres);
        assertThatCode(() -> juger(reproFrais, sourceJava))
                .as("un jar reproductible frais ne doit pas rougir sur l'horodatage fixe de ses "
                        + "entrees")
                .doesNotThrowAnyException();

        Path ordinairePerime = jar(dossier.resolve("ordinaire.jar"), classe, avant, apres, apres);
        assertThatThrownBy(() -> juger(ordinairePerime, sourceJava))
                .as("un jar ordinaire frais dont l'entree de classe est plus ancienne que son .java "
                        + "doit faire rougir le garde-fou : le repli sur le fichier ne vaut que pour "
                        + "un jar reproductible")
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("plus ancien");
    }

    private static void juger(Path jar, Path sourceJava) throws IOException {
        verifierFraicheur("Perimee", "fixture", jar, sourceJava,
                horodatageDeLaClasseCompilee(jar, "Perimee.class"),
                Files.getLastModifiedTime(sourceJava));
    }

    private static Path jar(Path chemin, byte[] classe, FileTime entreeClasse,
            FileTime entreeManifeste, FileTime fichier) throws IOException {
        try (JarOutputStream sortie = new JarOutputStream(Files.newOutputStream(chemin))) {
            JarEntry manifeste = new JarEntry("META-INF/MANIFEST.MF");
            manifeste.setLastModifiedTime(entreeManifeste);
            sortie.putNextEntry(manifeste);
            sortie.write("Manifest-Version: 1.0\r\n".getBytes(StandardCharsets.US_ASCII));
            sortie.closeEntry();
            JarEntry entree = new JarEntry("Perimee.class");
            entree.setLastModifiedTime(entreeClasse);
            sortie.putNextEntry(entree);
            sortie.write(classe);
            sortie.closeEntry();
        }
        Files.setLastModifiedTime(chemin, fichier);
        return chemin;
    }
}
