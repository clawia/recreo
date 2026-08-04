package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class ChargementDuContenuTest {

    private static final String ADAPTATEUR_CONTENU = "com.recreo.adapter.content..";

    /** Une seule porte d'entree vers les fichiers et vers YAML. */
    @ArchTest
    static final ArchRule seulLAdaptateurDeContenuLitDesFichiers = noClasses()
            .that().resideInAPackage("com.recreo..")
            .and().resideOutsideOfPackage(ADAPTATEUR_CONTENU)
            .should().dependOnClassesThat()
            .resideInAnyPackage("org.yaml.snakeyaml..", "java.nio.file..")
            .as("ADR-0007 : un seul adaptateur lit le contenu sur disque");

    /**
     * La dette n'etait pas "un chargeur mal ecrit", c'etait "neuf chargeurs".
     * Le dixieme, comme le deuxieme, casse le build.
     */
    @Test
    void ilNYAQuUnSeulChargeurDeContenu() {
        JavaClasses production = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.recreo");

        List<String> chargeurs = production.stream()
                .map(JavaClass::getName)
                .filter(nom -> nom.endsWith("Loader") || nom.endsWith("ContentPack"))
                .sorted()
                .toList();

        assertThat(chargeurs)
                .as("ADR-0007 : un seul chargeur, pilote par le schema de paquet")
                .containsExactly("com.recreo.adapter.content.YamlContentPackLoader");
    }
}
