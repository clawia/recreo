package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.domain.JavaParameterizedType;
import com.tngtech.archunit.core.domain.JavaType;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.List;
import java.util.stream.Stream;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DifficulteTest {

    private static final String CONTRAT_CONTENU = "com.recreo.domain.content";

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
     * Le contrat de contenu ne porte que du texte : un champ numerique y serait forcement le reglage
     * d'une matiere, que les autres matieres devraient remplir de valeurs factices.
     *
     * <p>Amendement du 2026-08-04, decouvert en ecrivant son contre-exemple : la version precedente
     * de cette regle etait un {@code haveRawType(resideInAnyPackage("java.lang", "java.util",
     * CONTRAT_CONTENU))}, et elle ne mordait sur aucune des deux formes reelles de la dette qu'elle
     * garde. ArchUnit place les primitifs dans le paquet {@code java.lang}, donc {@code int
     * minOperand} passait ; et le type brut de {@code Set<Operation> operations} est
     * {@code java.util.Set}, donc il passait aussi. La condition regarde desormais tous les types
     * cites par la declaration du champ, arguments generiques compris, et n'admet que
     * {@code java.lang.String}, les collections de {@code java.util} et les types du contrat.</p>
     */
    @ArchTest
    static final ArchRule leContratDeContenuNePorteQueDuTexte = fields()
            .that().areDeclaredInClassesThat().resideInAPackage(CONTRAT_CONTENU)
            .and().doNotHaveModifier(JavaModifier.STATIC)
            .should(neCiterQueDuTexteDesCollectionsOuDuContenu())
            .as("ADR-0006 : le contrat de contenu porte du texte, jamais des bornes de matiere");

    private static ArchCondition<JavaField> neCiterQueDuTexteDesCollectionsOuDuContenu() {
        return new ArchCondition<>(
                "ne citer que du texte, des collections du JDK ou des types du contrat de contenu") {

            @Override
            public void check(JavaField champ, ConditionEvents evenements) {
                typesCites(champ.getType()).stream()
                        .filter(type -> !estPermis(type))
                        .forEach(type -> evenements.add(SimpleConditionEvent.violated(champ,
                                champ.getFullName() + " cite le type " + type.getName()
                                        + ", qui n'est ni du texte, ni une collection du JDK, ni un"
                                        + " type du contrat de contenu")));
            }
        };
    }

    private static List<JavaClass> typesCites(JavaType type) {
        if (type instanceof JavaParameterizedType parametre) {
            return Stream.concat(
                            Stream.of(type.toErasure()),
                            parametre.getActualTypeArguments().stream()
                                    .flatMap(argument -> typesCites(argument).stream()))
                    .toList();
        }
        return List.of(type.toErasure());
    }

    private static boolean estPermis(JavaClass type) {
        return type.getName().equals(String.class.getName())
                || type.getPackageName().equals("java.util")
                || type.getPackageName().equals(CONTRAT_CONTENU);
    }
}
