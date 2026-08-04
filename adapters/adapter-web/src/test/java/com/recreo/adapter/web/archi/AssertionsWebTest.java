package com.recreo.adapter.web.archi;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Analyse qui inclut volontairement les tests : c'est le code de test que cette regle regarde.
 */
@AnalyzeClasses(packages = "com.recreo.adapter.web",
        importOptions = AssertionsWebTest.SansLesContreExemples.class)
class AssertionsWebTest {

    /**
     * Cette regle est la seule du depot qui juge du code de test. Son contre-exemple ne peut donc
     * pas vivre dans bootstrap, qui ne voit pas les tests de ce module, et doit etre soustrait a
     * l'evaluation reelle, sans quoi il la ferait echouer en permanence. ADR-0003 nomme cette
     * exception et le trou qu'elle ouvre.
     */
    public static final class SansLesContreExemples implements ImportOption {

        @Override
        public boolean includes(Location location) {
            return !location.contains("/contreexemple/");
        }
    }

    @ArchTest
    static final ArchRule aucuneAssertionParExpressionReguliere = noClasses()
            .that().resideInAPackage("com.recreo.adapter.web..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("java.util.regex..")
            .as("ADR-0008 : le HTML s'asserte avec jsoup, pas par expression reguliere");

    @Test
    void uneAssertionParExpressionReguliereEstRefusee() {
        assertThatThrownBy(() -> aucuneAssertionParExpressionReguliere.check(
                new ClassFileImporter().importPackages("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.adapter.web.contreexemple.AssertionRegexContreExemple")
                .hasMessageContaining("java.util.regex");
    }
}
