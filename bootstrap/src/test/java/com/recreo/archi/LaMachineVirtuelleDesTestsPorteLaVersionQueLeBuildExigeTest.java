package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0071 : la version du JDK sur laquelle une mesure a ete faite est ecrite dans des dizaines de
 * fichiers de ce depot, et rien ne la verifiait. Elle etait lue sur ce que
 * {@code maven-toolchains-plugin} <b>declare</b> - le journal du build nomme la racine de JDK
 * appariee - alors que le plugin apparie sur la <b>version declaree</b> par le fichier de
 * toolchains, jamais sur celle du binaire qui s'y trouve. Une toolchain qui declare 21 et designe un
 * JDK 25 est donc appariee sans un mot, et le build reussit en compilant et en testant sur 25.
 *
 * <p><b>Ce que ce test juge, et c'est le seul point qui compte</b> : la version que la machine
 * virtuelle <b>executant ce test</b> rapporte d'elle-meme. C'est la machine virtuelle que surefire
 * a forkee depuis la toolchain resolue par Maven - donc celle qui a execute tous les autres tests du
 * meme build. Il ne lit aucun journal, aucun chemin, aucun fichier de poste : il demande a
 * l'execution ce qu'elle est.
 *
 * <p><b>Le fichier de toolchains n'est pas dans le depot, et ce test n'en a pas besoin.</b> C'est
 * un fichier de poste, non commite, hors de portee de tout garde-fou. Ce qui est dans le depot est
 * <b>l'exigence</b> - le pom agregateur declare la version de toolchain que le build reclame - et
 * <b>l'affirmation</b> que les documents portent. Ce test raccorde les deux sans jamais lire le
 * fichier de poste.
 *
 * <p><b>Egalite, jamais plancher</b>, et c'est la moitie de comparaison qu'un test ecrit vite
 * oublierait. Un {@code >=} laisserait passer une execution sur 25 en silence, c'est-a-dire le
 * defaut meme que ce test ferme ; un {@code <=} laisserait passer une execution sur 17. Les deux
 * moities sont mordues separement.
 *
 * <p><b>Ce que ce test ne prouve pas.</b> Il juge la machine virtuelle de surefire, pas celle de
 * failsafe ni celle de Maven lui-meme. Celle de Maven est gouvernee par une regle distincte et
 * volontairement plus large ({@code maven-enforcer-plugin}, 17 ou plus recent : une toolchain ne
 * gouverne pas la machine virtuelle de Maven, et rien n'exige qu'elle soit la meme). Celle de
 * failsafe est forkee depuis la meme toolchain que surefire, par le meme plugin de toolchains ; le
 * jour ou l'une des deux cesserait de l'etre, ce test ne le verrait pas.
 */
class LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest {

    /**
     * L'exigence vient du pom agregateur, jamais d'une constante ecrite ici : c'est la meme
     * declaration que {@code maven-toolchains-plugin} lit pour apparier. Deux notions de "quelle
     * version ce build exige" divergeraient un jour, et c'est exactement la divergence que ce test
     * existe pour attraper.
     */
    static int versionDeToolchainExigee(Path racine) {
        String pom = lire(racine.resolve("pom.xml"));
        int blocToolchains = pom.indexOf("<toolchains>");
        if (blocToolchains < 0) {
            throw new AssertionError("ADR-0071 : le pom agregateur ne declare aucun bloc <toolchains> -"
                    + " l'exigence de version de ce build n'est plus lisible, et ce test ne juge plus rien");
        }
        int blocJdk = pom.indexOf("<jdk>", blocToolchains);
        int debut = pom.indexOf("<version>", blocJdk);
        int fin = pom.indexOf("</version>", debut);
        if (blocJdk < 0 || debut < 0 || fin < 0) {
            throw new AssertionError("ADR-0071 : le bloc <toolchains> du pom agregateur ne declare"
                    + " aucune version de jdk");
        }
        return Integer.parseInt(pom.substring(debut + "<version>".length(), fin).strip());
    }

    /**
     * Le juge, pur et donc mordable par entrees fabriquees. Le message nomme les deux valeurs et le
     * prerequis a corriger, jamais son emplacement sur une machine (ADR-0010).
     */
    static void verifier(int exigee, int executee) {
        if (exigee != executee) {
            throw new AssertionError("ADR-0071 : le pom agregateur exige une toolchain jdk de version "
                    + exigee + ", et la machine virtuelle qui execute les tests rapporte " + executee
                    + ". maven-toolchains-plugin apparie sur la version DECLAREE dans le fichier de"
                    + " toolchains de l'utilisateur, jamais sur celle du binaire designe : une entree"
                    + " qui declare " + exigee + " en pointant un JDK " + executee + " est appariee"
                    + " sans un mot. Corriger cette entree dans le fichier de toolchains du profil"
                    + " utilisateur de la machine virtuelle de Maven, ou toute mesure de ce depot"
                    + " decrira une autre chaine que celle qu'elle annonce");
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    @Test
    void laMachineVirtuelleQuiExecuteCeTestPorteLaVersionExigeeParLePomAgregateur() {
        int exigee = versionDeToolchainExigee(LesGardeFousSontMisALEpreuveTest.racineDuDepot());

        assertThat(exigee)
                .as("ADR-0071 : ce test ne juge rien si l'exigence lue dans le pom n'est pas une"
                        + " version plausible")
                .isGreaterThanOrEqualTo(17);

        verifier(exigee, Runtime.version().feature());
    }

    /** ADR-0013 obligation 3 : la moitie haute - une toolchain qui declare 21 et designe un JDK 25. */
    @Test
    void uneVersionExecuteePlusRecenteQueLExigenceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(21, 25))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("exige une toolchain jdk de version 21")
                .hasMessageContaining("rapporte 25");
    }

    /**
     * ADR-0013 obligation 3 : la moitie basse. Sans elle, un plancher aurait suffi, et un plancher
     * ne garde qu'une moitie de comparaison - la lecon d'ADR-0055.
     */
    @Test
    void uneVersionExecuteePlusAncienneQueLExigenceFaitEchouerLeGardeFou() {
        assertThatThrownBy(() -> verifier(21, 17))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("rapporte 17");
    }

    /** Le cas vert du juge, sans lequel les deux morsures ne prouvent que la moitie du critere. */
    @Test
    void deuxVersionsEgalesPassent() {
        verifier(21, 21);
    }
}
