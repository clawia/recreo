package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

/**
 * ADR-0011 : toute exception non geree qui atteint la frontiere HTTP est rendue en fragment HTML
 * francais, jamais en corps technique brut (motif de refus 3,
 * squad/conformite/FICHE-bandit-manchot-additions-cp.md).
 *
 * <p>ADR-0018 : et elle laisse une trace. Les deux regles ajoutees ici mordent toutes les deux sur
 * le code tel qu'il etait avant cet ADR - c'est la mesure qui les justifie, pas une precaution.</p>
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class GestionDesErreursTest {

    private static final String ADVICE = "org.springframework.web.bind.annotation.ControllerAdvice";
    private static final String HANDLER = "org.springframework.web.bind.annotation.ExceptionHandler";
    private static final String RESPONSE_BODY = "org.springframework.web.bind.annotation.ResponseBody";
    private static final String RESPONSE_ENTITY = "org.springframework.http.ResponseEntity";
    private static final String JOURNAL = "org.slf4j.Logger";
    private static final String CATCH_ALL = "java.lang.RuntimeException";

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

    /**
     * Le defaut mesure sur le jar de 099e750 : une manche cassee sur deux jeux, zero ligne de
     * journal. Un ecran d'erreur rendu a l'enfant est la seule trace qui existait, et l'enfant
     * n'est pas un canal de supervision.
     */
    @ArchTest
    static final ArchRule toutGestionnaireDErreurJournalise = methods()
            .that().areAnnotatedWith(HANDLER)
            .should(appelerLeJournal())
            .as("ADR-0018 : un gestionnaire d'erreur laisse une trace dans le journal");

    /**
     * La cause du deguisement. Nommer un type n'est pas nommer une situation : un type traine tout
     * son sous-arbre, et {@code IllegalStateException} est le plus peuple du JDK. Un gestionnaire
     * nomme donc un type que recreo declare - que rien d'autre ne peut lever - ou bien assume la
     * capture generale, qui ne trompe personne. L'entre-deux est ce qui a coute ce sprint.
     */
    @ArchTest
    static final ArchRule unGestionnaireNommeUnTypeDeRecreoOuLaCaptureGenerale = methods()
            .that().areAnnotatedWith(HANDLER)
            .should(neNommerQueDesTypesDeRecreoOuLaCaptureGenerale())
            .as("ADR-0018 : un gestionnaire nomme un type de recreo, ou assume la capture generale");

    private static ArchCondition<JavaMethod> appelerLeJournal() {
        return new ArchCondition<>("appeler " + JOURNAL) {

            @Override
            public void check(JavaMethod methode, ConditionEvents evenements) {
                boolean journalise = methode.getMethodCallsFromSelf().stream()
                        .anyMatch(appel -> appel.getTargetOwner().getName().equals(JOURNAL));
                if (!journalise) {
                    evenements.add(SimpleConditionEvent.violated(methode,
                            methode.getFullName() + " rend un ecran d'erreur sans appeler " + JOURNAL
                                    + " : le defaut ne laisse aucune trace exploitable"));
                }
            }
        };
    }

    private static ArchCondition<JavaMethod> neNommerQueDesTypesDeRecreoOuLaCaptureGenerale() {
        return new ArchCondition<>("ne nommer que des types de recreo, ou " + CATCH_ALL) {

            @Override
            public void check(JavaMethod methode, ConditionEvents evenements) {
                List<JavaClass> attrapes = typesAttrapes(methode);
                if (attrapes.isEmpty()) {
                    evenements.add(SimpleConditionEvent.violated(methode,
                            methode.getFullName() + " ne nomme aucun type d'exception"));
                    return;
                }
                attrapes.stream()
                        .filter(type -> !estPermis(type))
                        .forEach(type -> evenements.add(SimpleConditionEvent.violated(methode,
                                methode.getFullName() + " attrape " + type.getName()
                                        + ", un type que recreo ne declare pas et dont tout le"
                                        + " sous-arbre emprunte le canal de l'attendu")));
            }
        };
    }

    /**
     * Un {@code @ExceptionHandler} nomme ses types dans l'annotation ou, si elle est vide, par ses
     * parametres. Les deux formes sont lues, sans quoi la seconde echapperait a la regle - c'est
     * l'obligation de derivation totale d'ADR-0013.
     */
    private static List<JavaClass> typesAttrapes(JavaMethod methode) {
        JavaClass[] nommes = (JavaClass[]) methode.getAnnotationOfType(HANDLER)
                .get("value")
                .orElse(new JavaClass[0]);
        if (nommes.length > 0) {
            return List.of(nommes);
        }
        return methode.getRawParameterTypes().stream()
                .filter(type -> type.isAssignableTo(Throwable.class))
                .toList();
    }

    private static boolean estPermis(JavaClass type) {
        return type.getPackageName().startsWith("com.recreo") || type.getName().equals(CATCH_ALL);
    }
}
