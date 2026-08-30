package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ADR-0066. Deux sprints consecutifs ont trouve une charte d'agent qui contredisait une decision du
 * depot, et dans les deux cas la contradiction a ete trouvee parce qu'un agent a bute dessus, jamais
 * par un balayage ; le second cas vivait depuis dix-huit sprints (retro de s22, DEC-0056).
 *
 * <p><b>Ce qui est mecanisable ici, et ce qui ne l'est pas.</b> Constater qu'une charte
 * <i>contredit</i> une decision demande de lire deux textes en langue naturelle et de juger : aucun
 * automate de ce depot ne le fera. Deux proprietes voisines, elles, sont decidables, et ce sont
 * elles que ce garde-fou porte. Ce qu'il ne porte pas est ecrit dans ADR-0066 plutot que sous-entendu
 * ici.
 *
 * <p><b>Clause 1 - aucune reference de decision ne pointe vers rien.</b> C'est la famille du defaut
 * du 2026-08-17 : {@code CLAUDE.md}, le fichier que tous les agents lisent, a renvoye pendant deux
 * jours vers "ADR-0045, section Prerequis de mesure", qui n'existait pas. Mesure sur l'arbre du
 * 2026-08-30 : 121 identifiants distincts, 11 196 occurrences, 1 445 fichiers - le lien est le tissu
 * de ce depot, et rien ne le verifiait.
 *
 * <p><b>Clause 2 - une decision qui nomme une charte est citee par cette charte.</b> C'est la cause
 * exacte du cas de s22, dans les mots de DEC-0056 : <i>"un texte de decision qui n'a jamais ete
 * reporte dans les deux fiches qu'il concernait"</i>. Le lien n'a pas besoin d'etre juste pour etre
 * verifiable : il a besoin d'exister dans les deux sens.
 *
 * <p><b>Perimetre, derive et non enumere.</b> Tous les fichiers du depot sous la racine derivee du
 * pom agregateur, sauf trois familles, chacune ecartee par une propriete et non par un nom :
 * les repertoires de sortie de build ({@code target}), les arbres de contre-exemple
 * ({@code contreexemple}, dont la raison d'etre est de porter des defauts), et les fichiers binaires,
 * reconnus a l'octet nul qu'ils contiennent plutot qu'a leur extension (ADR-0036 : un garde-fou ne
 * lit pas un binaire comme du texte).
 */
class ToutesLesReferencesDeDecisionResolventTest {

    private static final Pattern REFERENCE = Pattern.compile("(ADR|DEC)-([0-9]{4})");

    private static final Pattern CHARTE_CITEE =
            Pattern.compile("[.]claude/agents/([a-z-]+)[.]md");

    private static final Path CONTRE_EXEMPLE = Path.of(
            "src/test/resources/contreexemple/ToutesLesReferencesDeDecisionResolventTest");

    private record Reference(String identifiant, Path fichier) {
    }

    private static String lireSiTexte(Path fichier) {
        byte[] octets;
        try {
            octets = Files.readAllBytes(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        for (byte octet : octets) {
            if (octet == 0) {
                return null;
            }
        }
        return new String(octets, StandardCharsets.UTF_8);
    }

    private static boolean estDansLePerimetre(Path racine, Path fichier) {
        for (Path partie : racine.relativize(fichier)) {
            String nom = partie.toString();
            if (nom.equals("target") || nom.equals("contreexemple") || nom.equals(".git")) {
                return false;
            }
        }
        return true;
    }

    private static List<Path> fichiersDuDepot(Path racine) {
        try (Stream<Path> arborescence = Files.walk(racine)) {
            return arborescence.filter(Files::isRegularFile)
                    .filter(fichier -> estDansLePerimetre(racine, fichier))
                    .sorted()
                    .toList();
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Les identifiants de decision que le depot porte reellement, lus sur les noms de fichiers. */
    static Set<String> decisionsExistantes(Path racine) {
        Set<String> existantes = new TreeSet<>();
        for (Path repertoire : List.of(racine.resolve("squad/decisions/adr"),
                racine.resolve("squad/decisions/produit"))) {
            if (!Files.isDirectory(repertoire)) {
                continue;
            }
            try (Stream<Path> fichiers = Files.list(repertoire)) {
                fichiers.map(fichier -> fichier.getFileName().toString())
                        .filter(nom -> nom.endsWith(".md"))
                        .map(REFERENCE::matcher)
                        .filter(Matcher::lookingAt)
                        .forEach(trouve -> existantes.add(trouve.group()));
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return existantes;
    }

    private static List<Reference> referencesDuDepot(Path racine) {
        List<Reference> references = new ArrayList<>();
        for (Path fichier : fichiersDuDepot(racine)) {
            String contenu = lireSiTexte(fichier);
            if (contenu == null) {
                continue;
            }
            Matcher trouve = REFERENCE.matcher(contenu);
            while (trouve.find()) {
                references.add(new Reference(trouve.group(), racine.relativize(fichier)));
            }
        }
        return references;
    }

    /**
     * Les identifiants qu'un fichier de test a le droit de nommer sans qu'ils resolvent : ceux que
     * porte <b>son propre</b> arbre de contre-exemple. Un depot fictif ne peut pas etre fautif si
     * ses identifiants doivent exister, et la source du garde-fou doit pouvoir les attendre dans son
     * assertion.
     *
     * <p>Ce trou est derive, jamais enumere : l'autorisation vaut pour le fichier
     * {@code <Nom>.java} et pour les seuls identifiants trouves sous
     * {@code contreexemple/<Nom>/}. Elle ne s'etend a aucun autre fichier, et pas non plus a un
     * identifiant que le contre-exemple ne porte pas - defaut trouve par ce garde-fou sur sa propre
     * source, a sa premiere execution.
     */
    private static java.util.Map<String, Set<String>> identifiantsDesContreExemples(Path racine) {
        java.util.Map<String, Set<String>> parTest = new java.util.HashMap<>();
        try (Stream<Path> arborescence = Files.walk(racine)) {
            List<Path> arbres = arborescence
                    .filter(Files::isDirectory)
                    .filter(chemin -> chemin.getParent() != null
                            && chemin.getParent().getFileName() != null
                            && chemin.getParent().getFileName().toString().equals("contreexemple"))
                    .toList();
            for (Path arbre : arbres) {
                Set<String> identifiants = parTest.computeIfAbsent(
                        arbre.getFileName().toString(), cle -> new TreeSet<>());
                try (Stream<Path> contenu = Files.walk(arbre)) {
                    for (Path fichier : contenu.filter(Files::isRegularFile).toList()) {
                        String texte = lireSiTexte(fichier);
                        if (texte == null) {
                            continue;
                        }
                        Matcher trouve = REFERENCE.matcher(texte);
                        while (trouve.find()) {
                            identifiants.add(trouve.group());
                        }
                        Matcher nom = REFERENCE.matcher(fichier.getFileName().toString());
                        if (nom.lookingAt()) {
                            identifiants.add(nom.group());
                        }
                    }
                }
            }
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
        return parTest;
    }

    static List<String> defautsDeResolution(Path racine) {
        Set<String> existantes = decisionsExistantes(racine);
        java.util.Map<String, Set<String>> parContreExemple = identifiantsDesContreExemples(racine);
        Set<String> defauts = new TreeSet<>();
        for (Reference reference : referencesDuDepot(racine)) {
            String nomDuFichier = reference.fichier().getFileName().toString();
            if (nomDuFichier.endsWith(".java")) {
                Set<String> permis = parContreExemple.getOrDefault(
                        nomDuFichier.substring(0, nomDuFichier.length() - ".java".length()),
                        Set.of());
                if (permis.contains(reference.identifiant())) {
                    continue;
                }
            }
            if (!existantes.contains(reference.identifiant())) {
                defauts.add(String.format(
                        "%s cite %s, qu'aucun fichier de squad/decisions/ ne porte. Une reference qui"
                                + " pointe vers rien, dans un fichier que les agents lisent, est de la"
                                + " meme famille qu'un chiffre sans methode (CLAUDE.md, note du"
                                + " 2026-08-17 ; ADR-0066, clause 1)",
                        reference.fichier(), reference.identifiant()));
            }
        }
        return List.copyOf(defauts);
    }

    /**
     * La section Impact d'une decision : la liste des fichiers qu'elle touche ou demande de toucher.
     * C'est la seule marque de ce depot qui distingue <b>statuer sur</b> une charte de la
     * <b>mentionner</b> - distinction que ce garde-fou a apprise en echouant sur ADR-0066, qui cite
     * deux chartes en rapportant le contexte de DEC-0056 sans rien decider a leur sujet. Rend
     * {@code null} quand la decision n'a pas de section Impact : la clause 2 est alors muette, et
     * elle le dit plutot que de deviner.
     */
    private static String sectionImpact(String contenu) {
        Matcher titre = Pattern.compile("(?m)^#{2,} +Impact *$").matcher(contenu);
        if (!titre.find()) {
            return null;
        }
        Matcher suivant = Pattern.compile("(?m)^#{1,2} +").matcher(contenu);
        int fin = contenu.length();
        if (suivant.find(titre.end())) {
            fin = suivant.start();
        }
        return contenu.substring(titre.end(), fin);
    }

    static List<String> defautsDeLienAvecLesChartes(Path racine) {
        List<String> defauts = new ArrayList<>();
        Path repertoireDesChartes = racine.resolve(".claude/agents");
        for (Path fichier : fichiersDuDepot(racine)) {
            Path relatif = racine.relativize(fichier);
            if (!relatif.startsWith(Path.of("squad/decisions"))) {
                continue;
            }
            String contenu = lireSiTexte(fichier);
            if (contenu == null) {
                continue;
            }
            Matcher identifiant = REFERENCE.matcher(fichier.getFileName().toString());
            if (!identifiant.lookingAt()) {
                continue;
            }
            String decision = identifiant.group();
            String impact = sectionImpact(contenu);
            if (impact == null) {
                continue;
            }
            Matcher chartes = CHARTE_CITEE.matcher(impact);
            Set<String> dejaVues = new LinkedHashSet<>();
            while (chartes.find()) {
                String charte = chartes.group(1);
                if (!dejaVues.add(charte)) {
                    continue;
                }
                Path fichierDeCharte = repertoireDesChartes.resolve(charte + ".md");
                if (!Files.isRegularFile(fichierDeCharte)) {
                    defauts.add(String.format(
                            "%s nomme la charte %s, qui n'existe pas (ADR-0066, clause 2)",
                            relatif, charte + ".md"));
                    continue;
                }
                String texteDeLaCharte = lireSiTexte(fichierDeCharte);
                if (texteDeLaCharte == null || !texteDeLaCharte.contains(decision)) {
                    defauts.add(String.format(
                            "%s nomme la charte %s et statue donc sur elle, mais cette charte ne cite"
                                    + " pas %s. C'est la cause exacte du cas de s22 : un texte de"
                                    + " decision jamais reporte dans la fiche qu'il concernait"
                                    + " (DEC-0056 ; ADR-0066, clause 2)",
                            relatif, charte + ".md", decision));
                }
            }
        }
        return defauts;
    }

    static void verifierLaCoherenceDesReferences(Path racine) {
        assertThat(decisionsExistantes(racine))
                .as("ADR-0051 : sans decision sur disque, ce garde-fou ne juge rien")
                .isNotEmpty();
        assertThat(referencesDuDepot(racine))
                .as("ADR-0051 : sans reference de decision dans l'arbre, ce garde-fou n'examine"
                        + " aucun objet")
                .isNotEmpty();

        List<String> defauts = new ArrayList<>(defautsDeResolution(racine));
        defauts.addAll(defautsDeLienAvecLesChartes(racine));
        assertThat(defauts)
                .as("ADR-0066 : toute reference de decision resout, et toute charte nommee par une"
                        + " decision cite cette decision")
                .isEmpty();
    }

    @Test
    void leDepotReelEstCoherent() {
        verifierLaCoherenceDesReferences(LesGardeFousSontMisALEpreuveTest.racineDuDepot());
    }

    /**
     * ADR-0051 : le compte d'objets reellement examines, ecrit plutot qu'espere. Il n'y a pas de
     * plafond ici - ce n'est pas un cliquet de dette, c'est la preuve qu'un vert de la clause 1 est
     * un vert sur du materiau, et non sur un balayage qui n'a rien trouve a lire.
     */
    @Test
    void leBalayageExamineLeTissuReelDuDepot() {
        Path racine = LesGardeFousSontMisALEpreuveTest.racineDuDepot();
        assertThat(referencesDuDepot(racine))
                .as("ADR-0066 : le depot porte des milliers de references de decision ; un balayage"
                        + " qui en trouverait une poignee ne lit pas ce qu'il croit lire")
                .hasSizeGreaterThan(1_000);
        assertThat(decisionsExistantes(racine))
                .as("ADR-0066 : les decisions sont lues sur les noms de fichiers de squad/decisions/")
                .hasSizeGreaterThan(50);
    }

    /**
     * ADR-0013 obligation 3. Le depot fautif porte les deux defauts a la fois : un fichier qui cite
     * une decision inexistante, et une decision qui nomme une charte qui ne la cite pas.
     */
    @Test
    void unDepotFautifFaitEchouerLesDeuxClauses() {
        assertThatThrownBy(() -> verifierLaCoherenceDesReferences(
                CONTRE_EXEMPLE.resolve("depot-fautif")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("cite ADR-9998, qu'aucun fichier de squad/decisions/ ne porte")
                .hasMessageContaining("ne cite pas DEC-9001");
    }
}
