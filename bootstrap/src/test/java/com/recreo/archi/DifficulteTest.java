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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DifficulteTest {

    private static final String CONTRAT_CONTENU = "com.recreo.domain.content";

    /**
     * ADR-0006, amendement du 2026-09-04 : les quatre composantes du seul cadre en pourcentage que le
     * contrat de contenu admette. Ce jeu de noms <b>est</b> le plafond de l'exemption geometrique :
     * il vaut exactement la taille de la dette, un rectangle et rien de plus.
     */
    private static final Set<String> COMPOSANTES_DU_CADRE_EN_POURCENTAGE =
            Set.of("x", "y", "largeur", "hauteur");

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
     *
     * <p>Amendement du 2026-09-04 (s28) : une exemption nommee, datee et plafonnee pour le cadre en
     * pourcentage d'ADR-0080. Voir {@link #estUneComposanteDeCadreEnPourcentage(JavaField,
     * JavaClass)} pour ce qu'elle admet, ce qu'elle continue de refuser, et pourquoi elle ne pouvait
     * pas prendre la forme d'ADR-0069.</p>
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
                        .filter(type -> !estUneComposanteDeCadreEnPourcentage(champ, type))
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

    /**
     * ADR-0006, amendement du 2026-09-04 (theo-archi, s28) : l'exemption geometrique.
     *
     * <p><b>Ce qu'elle admet, et rien d'autre</b> : un champ de type {@code int} declare par un type
     * du contrat de contenu dont les champs d'instance sont <b>exactement</b> {@code x}, {@code y},
     * {@code largeur} et {@code hauteur} - le rectangle en pourcentage de la boite d'une image
     * (ADR-0080, decision 2). Une cinquieme composante, une composante de moins ou un autre nom, et
     * le type entier retombe sous la regle : le plafond vaut exactement la taille de la dette.
     * Morsure : {@code LesReglesMordentTest.unCadreEtenduDUneBorneArithmetiqueEstRefuse}.</p>
     *
     * <p><b>Ce qu'elle ne touche pas</b> : les trois morsures historiques de cette regle restent
     * rouges - {@code ReglageArithmetiqueContreExemple}, {@code ReglageEnCollectionContreExemple},
     * {@code ReglageDeLongueurDePartieContreExemple} - ainsi que celle d'ADR-0069,
     * {@code ModeDeSaisieEnBooleenContreExemple}. Seul le type {@code int} est exempte, et seulement
     * porte par la forme ci-dessus.</p>
     *
     * <p><b>Pourquoi une exemption plutot que la transposition d'ADR-0069</b>, mesure et non
     * deduite : un type dedie qui envelopperait la grandeur ({@code record Pourcentage(int valeur)})
     * redeclare le {@code int} un cran plus bas, dans le meme paquet, donc dans le perimetre de cette
     * regle. Sonde du 2026-09-04 sur l'arbre de travail : la regle rougit sur
     * {@code PourcentageSonde.valeur}. ADR-0069 n'a pu remplacer un booleen par un type que parce
     * qu'un domaine de valeurs fini s'enumere, et qu'un enum ne porte aucun champ d'instance ; une
     * grandeur ne s'enumere pas.</p>
     */
    private static boolean estUneComposanteDeCadreEnPourcentage(JavaField champ, JavaClass type) {
        return type.getName().equals(int.class.getName())
                && champ.getOwner().getFields().stream()
                        .filter(autre -> !autre.getModifiers().contains(JavaModifier.STATIC))
                        .map(JavaField::getName)
                        .collect(Collectors.toSet())
                        .equals(COMPOSANTES_DU_CADRE_EN_POURCENTAGE);
    }

    private static boolean estPermis(JavaClass type) {
        return type.getName().equals(String.class.getName())
                || type.getPackageName().equals("java.util")
                || type.getPackageName().equals(CONTRAT_CONTENU);
    }
}
