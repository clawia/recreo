package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class PureteDesCouchesTest {

    @ArchTest
    static final ArchRule leDomaineNeDependQueDuJdk = noClasses()
            .that().resideInAPackage("com.recreo.domain..")
            .should().dependOnClassesThat(
                    resideOutsideOfPackages("java..", "com.recreo.domain.."))
            .as("ADR-0002 : le domaine ne depend que du JDK et de lui-meme");

    @ArchTest
    static final ArchRule lApplicationNeDependQueDuJdkEtDuDomaine = noClasses()
            .that().resideInAPackage("com.recreo.application..")
            .should().dependOnClassesThat(
                    resideOutsideOfPackages("java..", "com.recreo.domain..", "com.recreo.application.."))
            .as("ADR-0002 : la couche application ne connait aucun framework");
}
