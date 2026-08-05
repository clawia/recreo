package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

/**
 * ADR-0011 : toute exception non geree qui atteint la frontiere HTTP est rendue en fragment HTML
 * francais, jamais en corps technique brut (motif de refus 3,
 * squad/conformite/FICHE-bandit-manchot-additions-cp.md).
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class GestionDesErreursTest {

    private static final String ADVICE = "org.springframework.web.bind.annotation.ControllerAdvice";
    private static final String HANDLER = "org.springframework.web.bind.annotation.ExceptionHandler";
    private static final String RESPONSE_BODY = "org.springframework.web.bind.annotation.ResponseBody";
    private static final String RESPONSE_ENTITY = "org.springframework.http.ResponseEntity";

    /** Une porte de rendu d'erreur existe. Sans elle, ce test echoue par vacuite (ADR-0003, dispense 1). */
    @ArchTest
    static final ArchRule uneAdviceDErreurExiste = classes()
            .that().areAnnotatedWith(ADVICE)
            .should().resideInAPackage("com.recreo.adapter.web..")
            .allowEmptyShould(false)
            .as("ADR-0011 : une porte de rendu d'erreur existe dans adapter-web");

    /** Le contournement le plus probable : repondre par du JSON plutot que par une vue. */
    @ArchTest
    static final ArchRule uneErreurNeRendPasUnCorpsBrut = methods()
            .that().areAnnotatedWith(HANDLER)
            .should().notBeAnnotatedWith(RESPONSE_BODY)
            .andShould().notHaveRawReturnType(RESPONSE_ENTITY)
            .as("ADR-0011 : un gestionnaire d'erreur rend une vue HTML, jamais un corps technique");
}
