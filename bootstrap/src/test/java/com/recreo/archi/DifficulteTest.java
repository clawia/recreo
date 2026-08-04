package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DifficulteTest {

    /** Le nom est brule, dans les deux orthographes. */
    @ArchTest
    static final ArchRule aucunTypeDifficultyDansLeDomaine = noClasses()
            .that().resideInAPackage("com.recreo.domain..")
            .should().haveSimpleNameContaining("Difficulty")
            .as("ADR-0006 : la difficulte n'est pas un type du domaine");

    @ArchTest
    static final ArchRule aucunTypeDifficulteDansLeDomaine = noClasses()
            .that().resideInAPackage("com.recreo.domain..")
            .should().haveSimpleNameContaining("Difficulte")
            .as("ADR-0006 : la difficulte n'est pas un type du domaine");

    /**
     * Le contrat de contenu ne porte que du texte : un champ numerique y serait forcement
     * le reglage d'une matiere, que les autres matieres devraient remplir de valeurs factices.
     */
    @ArchTest
    static final ArchRule leContratDeContenuNePorteQueDuTexte = fields()
            .that().areDeclaredInClassesThat().resideInAPackage("com.recreo.domain.content")
            .and().doNotHaveModifier(JavaModifier.STATIC)
            .should().haveRawType(resideInAnyPackage(
                    "java.lang", "java.util", "com.recreo.domain.content"))
            .as("ADR-0006 : le contrat de contenu porte du texte, jamais des bornes de matiere");
}
