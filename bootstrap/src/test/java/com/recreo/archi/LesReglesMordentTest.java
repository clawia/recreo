package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class LesReglesMordentTest {

    /** Un contre-exemple qui atterrit dans le code de production casse le build. */
    @ArchTest
    static final ArchRule aucunContreExempleEnProduction = noClasses()
            .that().resideInAPackage("com.recreo..")
            .should().haveSimpleNameEndingWith("ContreExemple")
            .as("ADR-0003 : les contre-exemples restent dans bootstrap/src/test");

    private static JavaClasses contreExemples(String paquet) {
        return new ClassFileImporter()
                .withImportOption(new ImportOption.OnlyIncludeTests())
                .importPackages(paquet);
    }

    /**
     * layeredArchitecture() echoue par defaut si une couche declaree est vide, avant meme de
     * regarder la dependance visee : un import restreint au seul contre-exemple laisserait donc
     * "Application", "Web", "Contenu" et "Bootstrap" vides et masquerait la vraie violation
     * derriere une autre. Il faut donc les classes de production de chaque couche, en plus du
     * contre-exemple.
     */
    private static JavaClasses productionEtContreExemples() {
        return new ClassFileImporter().importPackages("com.recreo");
    }

    @Test
    void laPureteDuDomaineEchoueSurUnDomaineQuiUtiliseSpring() {
        assertThatThrownBy(() -> PureteDesCouchesTest.leDomaineNeDependQueDuJdk
                .check(contreExemples("com.recreo.domain.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DomainePollueContreExemple")
                .hasMessageContaining("org.springframework");
    }

    @Test
    void leContratDeMecaniqueRefuseUnMoteurPartage() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme
                .check(contreExemples("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MoteurPartageContreExemple");
    }

    @Test
    void uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueCoupleeContreExemple")
                .hasMessageContaining("ListContentSource");
    }

    @Test
    void uneMecaniqueQuiLitLaMatiereEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaMatiere
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueCoupleeContreExemple")
                .hasMessageContaining("ContentDescriptor");
    }

    @Test
    void unContenuQuiConnaitSaMecaniqueEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ContenuQuiSaitJouerContreExemple")
                .hasMessageContaining("GameMechanic");
    }

    /**
     * ADR-0001, critere 4 de S0-1 : avant ce test, aucun contre-exemple ne prouvait que la regle
     * de couches echoue reellement sur une dependance vers l'exterieur.
     */
    @Test
    void leDomaineNeDoitPasDependreDeLApplication() {
        assertThatThrownBy(() -> ArchitectureEnCouchesTest.lesDependancesVontVersLInterieur
                .check(productionEtContreExemples()))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DomaineDependantDeLApplicationContreExemple");
    }

    /** ADR-0001 : idem pour l'isolation entre adaptateurs, jamais mise en echec jusqu'ici. */
    @Test
    void deuxAdaptateursNeDoiventPasSeConnaitre() {
        assertThatThrownBy(() -> ArchitectureEnCouchesTest.lesAdaptateursNeSeConnaissentPas
                .check(contreExemples("com.recreo.adapter.web.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("AdaptateurWebDependantDuContenuContreExemple");
    }
}
