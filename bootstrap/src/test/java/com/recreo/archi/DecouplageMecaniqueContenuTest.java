package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DecouplageMecaniqueContenuTest {

    private static final String CONTRAT_CONTENU = "com.recreo.domain.content";
    private static final String CONTRAT_MECANIQUE = "com.recreo.domain.mechanic";
    private static final String MECANIQUES = "com.recreo.domain.mechanics..";

    /** (1) Aucun comportement partage possible dans le contrat de contenu. */
    @ArchTest
    static final ArchRule leContratDeContenuEstFerme = classes()
            .that().resideInAPackage(CONTRAT_CONTENU)
            .should().beInterfaces()
            .orShould().beRecords()
            .orShould().beEnums()
            .as("ADR-0004 : le contrat de contenu ne contient que des interfaces, records et enums");

    /** (2) Le contournement du SessionEngine : impossible de hisser un moteur dans le contrat. */
    @ArchTest
    static final ArchRule leContratDeMecaniqueEstFerme = classes()
            .that().resideInAPackage(CONTRAT_MECANIQUE)
            .should().beInterfaces()
            .orShould().beRecords()
            .orShould().beEnums()
            .as("ADR-0004 : aucun moteur de session partage dans le contrat de mecanique");

    /** (3) Liste blanche : un generateur concret est hors de portee, ou qu'il soit range. */
    @ArchTest
    static final ArchRule uneMecaniqueNeVoitQueLesContrats = noClasses()
            .that().resideInAPackage(MECANIQUES)
            .should().dependOnClassesThat(resideOutsideOfPackages(
                    "java..", CONTRAT_CONTENU, CONTRAT_MECANIQUE, MECANIQUES))
            .as("ADR-0004 : une mecanique ne depend que du JDK et des deux contrats");

    /** (4) Interdit le switch sur la matiere ou le niveau. */
    @ArchTest
    static final ArchRule uneMecaniqueIgnoreLaMatiere = noClasses()
            .that().resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .should().dependOnClassesThat()
            .haveFullyQualifiedName(CONTRAT_CONTENU + ".ContentDescriptor")
            .as("ADR-0004 : une mecanique ne lit ni la matiere, ni le niveau, ni la competence");

    /** (5) Le sens que la version precedente ne regardait pas. */
    @ArchTest
    static final ArchRule leContenuIgnoreLesMecaniques = noClasses()
            .that().resideInAPackage(CONTRAT_CONTENU + "..")
            .should().dependOnClassesThat()
            .resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .as("ADR-0004 : un contenu ne connait pas la mecanique qui le jouera");

    /** (6) Une mecanique = un paquet, sinon l'isolation ne compare rien. */
    @ArchTest
    static final ArchRule uneMecaniqueVitDansSonProprePaquet = classes()
            .that().implement(CONTRAT_MECANIQUE + ".GameMechanic")
            .should().resideInAPackage("com.recreo.domain.mechanics.*")
            .allowEmptyShould(false)
            .as("ADR-0004 : une implementation de GameMechanic vit dans com.recreo.domain.mechanics.<mecanique>");

    /** (7) Isolation, desormais adossee a (1), (2) et (6). */
    @ArchTest
    static final ArchRule lesMecaniquesNeSeConnaissentPas = slices()
            .matching("com.recreo.domain.mechanics.(*)..")
            .should().notDependOnEachOther()
            .as("ADR-0004 : deux mecaniques ne dependent pas l'une de l'autre");
}
