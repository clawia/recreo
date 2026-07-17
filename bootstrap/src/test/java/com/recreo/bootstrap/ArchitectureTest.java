package com.recreo.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

/**
 * Les 5 regles d'architecture qui font echouer le build si l'on casse la Clean
 * Architecture ou le decouplage mecanique/contenu.
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    /** (1) Architecture en couches : la regle de dependance du reactor Maven, verifiee au bytecode. */
    @ArchTest
    static final ArchRule layered = layeredArchitecture().consideringAllDependencies()
            .layer("Domain").definedBy("com.recreo.domain..")
            .layer("Application").definedBy("com.recreo.application..")
            .layer("Web").definedBy("com.recreo.adapter.web..")
            .layer("Persistence").definedBy("com.recreo.adapter.persistence..")
            .layer("Bootstrap").definedBy("com.recreo.bootstrap..")
            .whereLayer("Bootstrap").mayNotBeAccessedByAnyLayer()
            .whereLayer("Web").mayOnlyBeAccessedByLayers("Bootstrap")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Bootstrap")
            .whereLayer("Application").mayOnlyBeAccessedByLayers("Web", "Persistence", "Bootstrap")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Web", "Persistence", "Bootstrap");

    /** (2) Domaine pur : aucune dependance a Spring ni a JPA. */
    @ArchTest
    static final ArchRule domainIsPure = noClasses()
            .that().resideInAPackage("com.recreo.domain..")
            .should().dependOnClassesThat().resideInAnyPackage(
                    "org.springframework..", "jakarta.persistence..")
            .as("le domaine ne doit dependre d'aucun framework (Spring / JPA)");

    /** (3) Application sans framework. */
    @ArchTest
    static final ArchRule applicationIsFrameworkFree = noClasses()
            .that().resideInAPackage("com.recreo.application..")
            .should().dependOnClassesThat().resideInAnyPackage(
                    "org.springframework..", "jakarta..")
            .as("la couche application ne doit dependre d'aucun framework");

    /** (4) Decouplage mecanique/contenu : les mecaniques ignorent les generateurs concrets. */
    @ArchTest
    static final ArchRule mechanicsDoNotDependOnContentImpl = noClasses()
            .that().resideInAnyPackage("com.recreo.domain.mechanic..", "com.recreo.domain.mechanics..")
            .should().dependOnClassesThat().resideInAPackage("com.recreo.domain.content.impl..")
            .as("une mecanique ne doit pas dependre d'un generateur de contenu concret");

    /** (5) Mecaniques isolees entre elles. */
    @ArchTest
    static final ArchRule mechanicsAreIsolated = slices()
            .matching("com.recreo.domain.mechanics.(*)..")
            .should().notDependOnEachOther()
            .as("les mecaniques ne doivent pas dependre les unes des autres");
}
