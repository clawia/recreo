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
     * Le contre-exemple d'une regle d'existence n'est pas une classe fautive mais un monde ou le
     * type annonce n'est pas : c'est allowEmptyShould(false) qui doit alors mordre.
     */
    private static JavaClasses productionSansLeContrat(String paquet) {
        return new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
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

    /** ADR-0002 : la purete de l'application, symetrique de celle du domaine, jamais eprouvee. */
    @Test
    void lApplicationNeDoitPasDependreDUnFramework() {
        assertThatThrownBy(() -> PureteDesCouchesTest.lApplicationNeDependQueDuJdkEtDuDomaine
                .check(contreExemples("com.recreo.application.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.application.contreexemple.ApplicationPollueeContreExemple")
                .hasMessageContaining("org.springframework");
    }

    /** ADR-0003 : la regle qui garde les contre-exemples eux-memes doit mordre aussi. */
    @Test
    void unContreExempleEnProductionEstRefuse() {
        assertThatThrownBy(() -> aucunContreExempleEnProduction
                .check(contreExemples("com.recreo.domain.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.contreexemple.DomainePollueContreExemple");
    }

    /** ADR-0004 (1) : la fermeture du contrat de contenu, pendant de celle du contrat de mecanique. */
    @Test
    void unMoteurDeContenuPartageEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.MoteurDeContenuPartageContreExemple");
    }

    /** ADR-0004 (6) : sans elle, l'isolation entre mecaniques ne decoupe plus rien. */
    @Test
    void uneMecaniqueHorsDeSonPaquetEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueVitDansSonProprePaquet
                .check(contreExemples("com.recreo.domain.mechanics")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.mechanics.MecaniqueHorsPaquetContreExemple");
    }

    /**
     * ADR-0004 (7) : la regle exacte qui est restee verte 25 sprints sur la version precedente sans
     * rien interdire. Elle n'avait, ici non plus, jamais eu a echouer.
     */
    @Test
    void deuxMecaniquesNeDoiventPasSeConnaitre() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.lesMecaniquesNeSeConnaissentPas
                .check(contreExemples("com.recreo.domain.mechanics")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.mechanics.seconde.MecaniqueSecondeContreExemple")
                .hasMessageContaining("com.recreo.domain.mechanics.premiere.MecaniquePremiereContreExemple");
    }

    /** ADR-0005 : le nom brule. Le message doit citer le type fautif, pas seulement le nom brule. */
    @Test
    void unTypeNommeRuleSetEstRefuse() {
        assertThatThrownBy(() -> AbstractionsCiteesTest.aucunTypeNommeRuleSet
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.contreexemple.RuleSet");
    }

    /**
     * ADR-0005 : une regle d'existence ne peut pas avoir de classe fautive - la faute serait une
     * absence. Ce qui doit mordre est allowEmptyShould(false) ; le desactiver rend ce test rouge.
     */
    @Test
    void uneAbstractionCiteeQuiDisparaitEstRefusee() {
        assertThatThrownBy(() -> AbstractionsCiteesTest.contentSourceExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0005 : ContentSource existe et est une interface")
                .hasMessageContaining("failed to check any classes");

        assertThatThrownBy(() -> AbstractionsCiteesTest.itemExiste
                .check(productionSansLeContrat("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0005 : Item existe et est une interface")
                .hasMessageContaining("failed to check any classes");

        assertThatThrownBy(() -> AbstractionsCiteesTest.gameMechanicExiste
                .check(productionSansLeContrat("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ADR-0005 : GameMechanic existe et est une interface")
                .hasMessageContaining("failed to check any classes");
    }

    /** ADR-0006 : les deux orthographes du nom brule, un contre-exemple chacune. */
    @Test
    void unTypeDeDifficulteDansLeDomaineEstRefuse() {
        assertThatThrownBy(() -> DifficulteTest.aucunTypeDifficultyDansLeDomaine
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.contreexemple.DifficultyConfigContreExemple");

        assertThatThrownBy(() -> DifficulteTest.aucunTypeDifficulteDansLeDomaine
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.contreexemple.DifficulteConfigContreExemple");
    }

    /**
     * ADR-0006 : la regle qui tient la propriete de fond, et non seulement un nom. Ces deux
     * contre-exemples ont montre qu'elle ne mordait sur aucune des deux formes reelles de la dette
     * (primitif, argument generique) ; voir l'amendement du 2026-08-04 dans DifficulteTest.
     */
    @Test
    void uneBorneArithmetiqueDansLeContratDeContenuEstRefusee() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageArithmetiqueContreExemple.sommeMaximale")
                .hasMessageContaining("int");
    }

    /** ADR-0006 : la meme regle, sur la borne cachee dans un argument generique. */
    @Test
    void uneBorneArithmetiqueCacheeDansUneCollectionEstRefusee() {
        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageEnCollectionContreExemple.operations")
                .hasMessageContaining("com.recreo.domain.content.contreexemple.OperationContreExemple");

        assertThatThrownBy(() -> DifficulteTest.leContratDeContenuNePorteQueDuTexte
                .check(contreExemples("com.recreo.domain.content")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.domain.content.ReglageEnCollectionContreExemple.bornes")
                .hasMessageContaining("java.lang.Integer");
    }

    /** ADR-0007 : le dixieme chargeur, hors de l'adaptateur de contenu. */
    @Test
    void unChargeurHorsDeLAdaptateurDeContenuEstRefuse() {
        assertThatThrownBy(() -> ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers
                .check(contreExemples("com.recreo.bootstrap.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("com.recreo.bootstrap.contreexemple.ChargeurSauvageContreExemple")
                .hasMessageContaining("java.nio.file");
    }
}
