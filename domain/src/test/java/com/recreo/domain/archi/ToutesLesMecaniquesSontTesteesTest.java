package com.recreo.domain.archi;

import com.recreo.domain.mechanic.GameMechanic;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class ToutesLesMecaniquesSontTesteesTest {

    @Test
    void chaqueMecaniqueAUnTestDeComportement() {
        JavaClasses production = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("com.recreo.domain.mechanics");
        JavaClasses tests = new ClassFileImporter()
                .withImportOption(new ImportOption.OnlyIncludeTests())
                .importPackages("com.recreo.domain.mechanics");

        List<String> mecaniques = production.stream()
                .filter(classe -> classe.isAssignableTo(GameMechanic.class))
                .map(JavaClass::getName)
                .sorted()
                .toList();

        assertThat(mecaniques)
                .as("ADR-0008 : cette regle ne prouve rien s'il n'y a aucune mecanique")
                .isNotEmpty();

        Set<String> classesDeTest = tests.stream().map(JavaClass::getName).collect(toSet());

        assertThat(mecaniques.stream().filter(nom -> !classesDeTest.contains(nom + "Test")).toList())
                .as("ADR-0008 : toute mecanique a un test de comportement cote serveur")
                .isEmpty();
    }
}
