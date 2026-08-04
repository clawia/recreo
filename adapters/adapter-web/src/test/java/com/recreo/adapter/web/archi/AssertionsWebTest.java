package com.recreo.adapter.web.archi;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Analyse qui inclut volontairement les tests : c'est le code de test que cette regle regarde.
 */
@AnalyzeClasses(packages = "com.recreo.adapter.web")
class AssertionsWebTest {

    @ArchTest
    static final ArchRule aucuneAssertionParExpressionReguliere = noClasses()
            .that().resideInAPackage("com.recreo.adapter.web..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("java.util.regex..")
            .as("ADR-0008 : le HTML s'asserte avec jsoup, pas par expression reguliere");
}
