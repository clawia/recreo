package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureEnCouchesTest {

    @ArchTest
    static final ArchRule lesDependancesVontVersLInterieur = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Domaine").definedBy("com.recreo.domain..")
            .layer("Application").definedBy("com.recreo.application..")
            .layer("Web").definedBy("com.recreo.adapter.web..")
            .layer("Contenu").definedBy("com.recreo.adapter.content..")
            .layer("Bootstrap").definedBy("com.recreo.bootstrap..")
            .whereLayer("Bootstrap").mayNotBeAccessedByAnyLayer()
            .whereLayer("Web").mayOnlyBeAccessedByLayers("Bootstrap")
            .whereLayer("Contenu").mayOnlyBeAccessedByLayers("Bootstrap")
            .whereLayer("Application").mayOnlyBeAccessedByLayers("Web", "Contenu", "Bootstrap")
            .whereLayer("Domaine").mayOnlyBeAccessedByLayers("Application", "Web", "Contenu", "Bootstrap")
            .as("ADR-0001 : les dependances vont toujours vers l'interieur");

    @ArchTest
    static final ArchRule lesAdaptateursNeSeConnaissentPas = slices()
            .matching("com.recreo.adapter.(*)..")
            .should().notDependOnEachOther()
            .as("ADR-0001 : deux adaptateurs ne se voient pas, le cablage est dans bootstrap");
}
