package com.recreo.archi;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0013 : un garde-fou derive son perimetre de la source, et se prouve mordant sur un objet
 * neuf. Ce test est le recensement executable de cette exigence, et il derive lui-meme son
 * perimetre : les modules viennent du pom agregateur, les garde-fous et les regles viennent du
 * balayage des sources de test de ces modules. Aucune liste de garde-fous n'est ecrite ici, sans
 * quoi ce fichier serait le premier a violer la regle qu'il porte.
 *
 * <p>Il vit dans bootstrap parce qu'il doit voir tous les modules. Il ne peut donc pas executer un
 * garde-fou d'un autre module - le classpath de test des autres modules ne lui est pas visible -
 * et verifie la presence de la mise a l'epreuve, pas son execution ; c'est la suite du module
 * proprietaire qui l'execute. Limite nommee dans ADR-0013.
 */
class LesGardeFousSontMisALEpreuveTest {

    /** Ce qui fait d'un test un garde-fou de balayage : il lit un artefact de production. */
    private static final String LECTURE_DE_PRODUCTION = "src/main/";

    /** Ce qui fait qu'un garde-fou est mis a l'epreuve : il s'execute sur un jeu fautif. */
    private static final String MISE_A_L_EPREUVE = "assertThatThrownBy";

    private static final String DECLARATION_DE_REGLE = "ArchRule ";

    private static final Path CONTRE_EXEMPLES = Path.of("src", "test", "resources", "contreexemple");

    /** Depot fictif complet, avec son pom agregateur, sur lequel ce test se prouve mordant. */
    private static final Path DEPOT_FAUTIF =
            CONTRE_EXEMPLES.resolve("LesGardeFousSontMisALEpreuveTest");

    /**
     * Garde-fous de balayage ecrits avant ADR-0013, qui n'ont pas encore de jeu fautif. Cette liste
     * exempte, elle n'absout pas : elle ne peut pas grandir (PLAFOND_DE_DETTE), elle ne peut pas
     * pourrir (chaque nom doit designer un garde-fou qui existe), et un garde-fou neuf non eprouve
     * echoue au lieu d'y entrer. C'est une liste d'exemptions, pas un perimetre : un oubli y produit
     * un rouge, jamais un vert silencieux.
     */
    private static final Set<String> DETTE_DE_BALAYAGE = Set.of(
            "AucunVocabulaireDeMatiereTest",
            "FaviconTest",
            "FrontieresDuJavaScriptTest",
            "HabillageDesEcransTest",
            "TailleDesTextesEtBoutonsTest");

    /**
     * Etat au 2026-08-05, s03 : baisse de 6 a 5 - TexteAccentueDesGabaritsTest sort de la dette
     * (jeu fautif et mise a l'epreuve ecrits dans le meme commit que la correction du perimetre
     * omis, obstacle route par theo-archi depuis s02). Ce nombre ne remonte jamais ; le baisser
     * demande un diff visible.
     */
    private static final int PLAFOND_DE_DETTE = 5;

    private record SourceDeTest(Path module, Path fichier) {

        String nom() {
            String fichierSeul = fichier.getFileName().toString();
            return fichierSeul.substring(0, fichierSeul.length() - ".java".length());
        }
    }

    private static String lire(Path fichier) {
        try {
            return Files.readString(fichier);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    /** Un appel peut etre coupe en plusieurs lignes ; le nom et le .check( restent voisins. */
    private static String sansEspaces(String source) {
        StringBuilder compact = new StringBuilder(source.length());
        for (int position = 0; position < source.length(); position++) {
            char caractere = source.charAt(position);
            if (!Character.isWhitespace(caractere)) {
                compact.append(caractere);
            }
        }
        return compact.toString();
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

    private static List<String> modulesDeclares(Path racine) {
        String pom = lire(racine.resolve("pom.xml"));
        List<String> modules = new ArrayList<>();
        int position = 0;
        while ((position = pom.indexOf("<module>", position)) >= 0) {
            int fin = pom.indexOf("</module>", position);
            modules.add(pom.substring(position + "<module>".length(), fin).strip());
            position = fin;
        }
        return modules;
    }

    private static List<SourceDeTest> sourcesDeTest(Path racine) {
        List<SourceDeTest> sources = new ArrayList<>();
        for (String module : modulesDeclares(racine)) {
            Path repertoireDuModule = racine.resolve(module);
            Path tests = repertoireDuModule.resolve(Path.of("src", "test", "java"));
            if (!Files.isDirectory(tests)) {
                continue;
            }
            try (Stream<Path> arborescence = Files.walk(tests)) {
                arborescence.filter(Files::isRegularFile)
                        .filter(fichier -> fichier.getFileName().toString().endsWith(".java"))
                        .sorted()
                        .forEach(fichier -> sources.add(new SourceDeTest(repertoireDuModule, fichier)));
            } catch (IOException echec) {
                throw new UncheckedIOException(echec);
            }
        }
        return sources;
    }

    /**
     * Ce test se retire lui-meme, par son propre nom et non par une liste : il nomme la marque de
     * balayage pour la chercher chez les autres, il ne lit aucun artefact de production.
     */
    private static List<SourceDeTest> balayagesDeProduction(Path racine) {
        String soiMeme = LesGardeFousSontMisALEpreuveTest.class.getSimpleName();
        return sourcesDeTest(racine).stream()
                .filter(source -> !source.nom().equals(soiMeme))
                .filter(source -> lire(source.fichier()).contains(LECTURE_DE_PRODUCTION))
                .toList();
    }

    static List<String> balayagesSansMiseALEpreuve(Path racine, Set<String> dette) {
        List<String> manquants = new ArrayList<>();
        for (SourceDeTest balayage : balayagesDeProduction(racine)) {
            if (dette.contains(balayage.nom())) {
                continue;
            }
            Path jeuFautif = balayage.module().resolve(CONTRE_EXEMPLES).resolve(balayage.nom());
            boolean eprouve = lire(balayage.fichier()).contains(MISE_A_L_EPREUVE)
                    && Files.isDirectory(jeuFautif)
                    && !estVide(jeuFautif);
            if (!eprouve) {
                manquants.add(balayage.nom());
            }
        }
        return manquants;
    }

    private static boolean estVide(Path repertoire) {
        try (Stream<Path> contenu = Files.walk(repertoire)) {
            return contenu.noneMatch(Files::isRegularFile);
        } catch (IOException echec) {
            throw new UncheckedIOException(echec);
        }
    }

    private static List<String> reglesDeclarees(String source) {
        List<String> noms = new ArrayList<>();
        int position = 0;
        while ((position = source.indexOf(DECLARATION_DE_REGLE, position)) >= 0) {
            int debut = position + DECLARATION_DE_REGLE.length();
            int fin = debut;
            while (fin < source.length() && Character.isJavaIdentifierPart(source.charAt(fin))) {
                fin++;
            }
            int suivant = fin;
            while (suivant < source.length() && Character.isWhitespace(source.charAt(suivant))) {
                suivant++;
            }
            if (fin > debut && suivant < source.length() && source.charAt(suivant) == '=') {
                noms.add(source.substring(debut, fin));
            }
            position = debut;
        }
        return noms;
    }

    static List<String> toutesLesRegles(Path racine) {
        return sourcesDeTest(racine).stream()
                .flatMap(source -> reglesDeclarees(lire(source.fichier())).stream())
                .toList();
    }

    static List<String> reglesSansMiseALEpreuve(Path racine) {
        StringBuilder toutLeCodeDeTest = new StringBuilder();
        for (SourceDeTest source : sourcesDeTest(racine)) {
            toutLeCodeDeTest.append(sansEspaces(lire(source.fichier())));
        }
        String code = toutLeCodeDeTest.toString();
        return toutesLesRegles(racine).stream()
                .filter(regle -> !code.contains(regle + ".check("))
                .toList();
    }

    @Test
    void leDepotContientDesGardeFousEtDesRegles() {
        Path racine = racineDuDepot();

        assertThat(balayagesDeProduction(racine))
                .as("ADR-0013 : ce recensement ne prouve rien s'il ne recense aucun garde-fou")
                .isNotEmpty();
        assertThat(toutesLesRegles(racine))
                .as("ADR-0013 : ce recensement ne prouve rien s'il ne trouve aucune regle ArchUnit")
                .isNotEmpty();
    }

    @Test
    void aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve() {
        assertThat(balayagesSansMiseALEpreuve(racineDuDepot(), DETTE_DE_BALAYAGE))
                .as("ADR-0013 : un test qui balaie des artefacts de production doit posseder un jeu "
                        + "fautif sous src/test/resources/contreexemple/<NomDeLaClasse> et s'y "
                        + "executer en exigeant le rouge")
                .isEmpty();
    }

    @Test
    void aucuneRegleArchUnitNEchappeAuContreExemple() {
        assertThat(reglesSansMiseALEpreuve(racineDuDepot()))
                .as("ADR-0003 exige un contre-exemple par regle ; ADR-0013 le recense au lieu de "
                        + "l'esperer : toute ArchRule declaree doit etre passee a un .check(")
                .isEmpty();
    }

    @Test
    void laDetteNePeutNiGrandirNiPourrir() {
        assertThat(DETTE_DE_BALAYAGE)
                .as("ADR-0013 : la dette de garde-fous non eprouves ne remonte jamais")
                .hasSizeLessThanOrEqualTo(PLAFOND_DE_DETTE);

        List<String> existants = balayagesDeProduction(racineDuDepot()).stream()
                .map(SourceDeTest::nom)
                .toList();
        assertThat(existants)
                .as("ADR-0013 : une dette qui nomme un garde-fou disparu ou renomme exempte un nom "
                        + "que plus rien ne porte")
                .containsAll(DETTE_DE_BALAYAGE);
    }

    @Test
    void unGardeFouDeBalayageSansJeuFautifEstRefuse() {
        assertThat(balayagesSansMiseALEpreuve(DEPOT_FAUTIF, Set.of()))
                .containsExactly("BalayageSansEpreuveContreExemple");
    }

    @Test
    void uneRegleArchUnitJamaisPasseeAUnCheckEstRefusee() {
        assertThat(reglesSansMiseALEpreuve(DEPOT_FAUTIF))
                .containsExactly("uneRegleJamaisEprouvee");
    }

    @Test
    void laDetteExempteExactementCeQuElleNomme() {
        assertThat(balayagesSansMiseALEpreuve(DEPOT_FAUTIF, Set.of("BalayageSansEpreuveContreExemple")))
                .isEmpty();
    }
}
