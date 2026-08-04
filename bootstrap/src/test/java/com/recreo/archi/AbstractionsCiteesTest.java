package com.recreo.archi;

import com.recreo.domain.content.Item;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class AbstractionsCiteesTest {

    /** L'abandon, encode : le nom est brule. */
    @ArchTest
    static final ArchRule aucunTypeNommeRuleSet = noClasses()
            .that().resideInAPackage("com.recreo..")
            .should().haveSimpleName("RuleSet")
            .as("ADR-0005 : RuleSet est abandonne, aucun type ne porte ce nom");

    /** Les trois abstractions citees dans la documentation existent et ont la forme annoncee. */
    @ArchTest
    static final ArchRule contentSourceExiste = classes()
            .that().haveFullyQualifiedName("com.recreo.domain.content.ContentSource")
            .should().beInterfaces()
            .allowEmptyShould(false)
            .as("ADR-0005 : ContentSource existe et est une interface");

    @ArchTest
    static final ArchRule gameMechanicExiste = classes()
            .that().haveFullyQualifiedName("com.recreo.domain.mechanic.GameMechanic")
            .should().beInterfaces()
            .allowEmptyShould(false)
            .as("ADR-0005 : GameMechanic existe et est une interface");

    @ArchTest
    static final ArchRule itemExiste = classes()
            .that().haveFullyQualifiedName("com.recreo.domain.content.Item")
            .should().beInterfaces()
            .allowEmptyShould(false)
            .as("ADR-0005 : Item existe et est une interface");

    /**
     * ArchUnit 1.5 sait dire "interface", pas "scellee" : la couture d'extension d'ADR-0004 se
     * verifie donc par reflexion. Le nombre de formes permises n'est pas fige, seule la cloture
     * l'est.
     */
    @Test
    void itemEstScelle() {
        assertThat(Item.class.isSealed())
                .as("ADR-0004 : Item est la couture d'extension des formes d'item, elle reste scellee")
                .isTrue();
    }
}
