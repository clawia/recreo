package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @TempDir
    private Path tempo;

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
        return javaScriptMaison(STATIQUE);
    }

    /**
     * Le meme perimetre, sur une racine statique quelconque : c'est ce qui permet a une morsure de
     * juger une copie mutee du materiau reel au lieu d'une fixture. Le repertoire tiers est derive de
     * la racine passee, jamais de la constante de production.
     */
    private static List<Path> javaScriptMaison(Path racineStatique) throws IOException {
        Path tiers = racineStatique.resolve(TIERS.getFileName().toString());
        return fichiers(racineStatique, ".js")
                .filter(fichier -> !fichier.startsWith(tiers))
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
    /**
     * ADR-0057, clause 1 : le script d'agrement ne rend jamais un element focalisable. Le mecanisme
     * de S17-C deplace la declaration de {@code tabindex} vers les gabarits ; ce que cette clause
     * garde est la moitie negative de la propriete - le script ne la reprend pas.
     *
     * <p><b>Pourquoi la comparaison ignore la casse, et ce qu'il a fallu pour le voir.</b> Cette
     * clause est arrivee dans le lot de {@code marc-dev} sous la forme
     * {@code doesNotContain("tabindex")}, sensible a la casse. Mesure du 2026-08-23 sur l'arbre
     * fusionne : {@code pointDeFocus.tabIndex = -1;} - la propriete DOM, qui est la facon la
     * <b>plus</b> idiomatique d'ecrire ce geste - restaure integralement le defaut que
     * {@code nadia-prof} avait mesure (le bouton nomme a la revelation sort de l'ordre de
     * tabulation) et laisse les 618 tests du reacteur verts. Un garde-fou que l'orthographe usuelle
     * de la faute contourne ne garde rien. La comparaison couvre desormais l'attribut
     * ({@code setAttribute}, {@code removeAttribute}) et la propriete ({@code tabIndex}) d'un seul
     * predicat, parce que les deux s'ecrivent avec les memes lettres.
     */
    @Test
    void leScriptDeFocusNeRendJamaisUnElementFocalisable() throws IOException {
        verifierQueLeScriptNeMuteAucuneFocalisabilite(STATIQUE);
    }

    /**
     * ADR-0013 obligation 3 pour cette clause, sur le materiau reel plutot que sur une fixture :
     * {@code recreo.js} tel qu'il est servi, mute du geste exact que la mesure a trouve invisible.
     * Une fixture ecrite de ma main aurait porte l'orthographe que j'imagine - c'est precisement
     * l'orthographe imaginee qui a laisse passer la version precedente de cette clause.
     *
     * <p>Note de gouvernance : {@code FrontieresDuJavaScriptTest} figure dans
     * {@code DETTE_DE_BALAYAGE} de {@code LesGardeFousSontMisALEpreuveTest}, donc l'obligation de jeu
     * fautif ne lui est pas opposee. <b>Le plafond de la dette n'est pas baisse pour autant</b> : ses
     * trois autres clauses de balayage restent sans morsure, et sortir le fichier de la dette parce
     * qu'un repertoire existe reviendrait a satisfaire le proxy que ce depot a deja critique
     * (ADR-0054, section sur le jeu fautif commite). Cette morsure-ci mute donc une copie.
     */
    @Test
    void uneFocalisabiliteRendueParLeScriptFaitEchouerLeGardeFou() throws IOException {
        Path racine = copieDuStatique(tempo.resolve("focalisabilite-rendue-par-le-script"));
        LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.muterUneSeuleFois(
                racine.resolve("js/recreo.js"),
                "        pointDeFocus.focus();",
                "        pointDeFocus.tabIndex = -1;\n        pointDeFocus.focus();");

        assertThatThrownBy(() -> verifierQueLeScriptNeMuteAucuneFocalisabilite(racine))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("tabIndex");
    }

    static void verifierQueLeScriptNeMuteAucuneFocalisabilite(Path racineStatique)
            throws IOException {
        List<Path> fichiers = javaScriptMaison(racineStatique);
        assertThat(fichiers)
                .as("ADR-0003 : cette regle ne prouve rien si aucun fichier n'est passe au crible")
                .isNotEmpty();

        for (Path fichier : fichiers) {
            String source = Files.readString(fichier);
            assertThat(source)
                    .as("ADR-0057 : %s ne doit rendre aucun element focalisable. Depuis S17-C, "
                            + "tabindex est declare par le gabarit qui rend la cible ; un script qui "
                            + "le pose, le retire, ou ecrit la propriete tabIndex reprend au serveur "
                            + "une decision que la CI ne peut plus verifier sur le document servi",
                            fichier)
                    .doesNotContainIgnoringCase("tabindex");
        }
    }

    private static Path copieDuStatique(Path destination) {
        try {
            try (Stream<Path> arborescence = Files.walk(STATIQUE)) {
                for (Path fichier : arborescence.toList()) {
                    Path cible = destination.resolve(STATIQUE.relativize(fichier).toString());
                    if (Files.isDirectory(fichier)) {
                        Files.createDirectories(cible);
                    } else {
                        Files.createDirectories(cible.getParent());
                        Files.copy(fichier, cible, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            return destination;
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
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
