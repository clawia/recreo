package com.recreo.archi;

import com.recreo.application.catalog.OrdreDesNiveaux;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ADR-0095 : l'ordre des niveaux est une cle de tri portee par une sequence unique, et le niveau
 * lui-meme reste la chaine que le paquet declare.
 *
 * <p><b>Le trou que la premiere regle ferme, et il est mesure, pas suppose.</b> ADR-0006 dit deja
 * que le niveau n'est pas un enum, et {@code DifficulteTest.leContratDeContenuNePorteQueDuTexte}
 * est cense le tenir. Son {@code estPermis} admet tout type residant dans
 * {@code com.recreo.domain.content} : un {@code enum Niveau} pose dans ce paquet, puis cite par
 * {@code ContentDescriptor}, passe cette regle sans un mot. C'est exactement la forme que le
 * chemin le moins cher prendrait pour obtenir un ordre - {@code Comparator.comparing(Niveau::
 * valueOf)} est le tri en une ligne. Les deux contre-exemples de {@code com.recreo.domain.content}
 * le montrent : {@code LesReglesMordentTest.unNiveauEnumereDansLeContratEstRefuse} verifie que
 * cette regle-ci rougit et que celle d'ADR-0006 reste verte sur le meme materiau.
 *
 * <p><b>Le perimetre des noms interdits est derive, jamais ecrit ici.</b> Il est lu dans
 * {@link OrdreDesNiveaux#SEQUENCE}. Un niveau ajoute a la sequence est garde le jour ou il y entre,
 * sans qu'une ligne de ce fichier bouge.
 *
 * <p><b>Ce que cette regle ne voit pas, ecrit plutot que laisse decouvrir.</b> Elle compare des
 * noms de constantes aux chaines de la sequence, a la casse pres. Un {@code enum Niveau {
 * COURS_PREPARATOIRE, CE_UN }} passerait. C'est la forme sous laquelle la regression se produirait
 * reellement qui est gardee - on ecrit {@code CP}, pas {@code COURS_PREPARATOIRE} - et l'autre
 * demanderait un vocabulaire ecrit a la main, donc une liste qui pourrit.
 */
@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class LOrdreDesNiveauxResteUneDonneeTest {

    private static final String SEQUENCE = "com.recreo.application.catalog.OrdreDesNiveaux";

    @ArchTest
    static final ArchRule aucunNiveauNEstUneConstanteDEnumeration = classes()
            .that().resideInAnyPackage("com.recreo.domain..", "com.recreo.application..")
            .should(neJamaisEnumererUnNiveauDeLaSequence())
            .as("ADR-0095 : le niveau reste une donnee, jamais une constante d'enumeration");

    /**
     * La dette 1 de CLAUDE.md, encodee : une abstraction citee partout et absente du code.
     * ADR-0095 nomme cette sequence dans sa decision ; elle existe, ou le build le dit.
     */
    @ArchTest
    static final ArchRule laSequenceDesNiveauxExiste = classes()
            .that().haveFullyQualifiedName(SEQUENCE)
            .should().bePublic()
            .allowEmptyShould(false)
            .as("ADR-0095 : la sequence des niveaux existe dans le code, pas seulement dans la decision");

    /**
     * La sequence est une decision produit (DEC-0090), pas un detail d'implementation : la changer
     * doit couter un diff visible ici et une decision, jamais un tri reordonne en passant.
     */
    @Test
    void laSequenceEstCelleQueDec0090Fixe() {
        assertThat(OrdreDesNiveaux.SEQUENCE)
                .as("DEC-0090 : CP, CE1, CM1, CM2")
                .containsExactly("CP", "CE1", "CM1", "CM2");
    }

    /**
     * Le rang de repli d'ADR-0095 : un niveau inconnu se range apres tous les niveaux connus,
     * plutot que d'arreter l'accueil. C'est la moitie du mecanisme que le tri ne montre pas.
     */
    @Test
    void unNiveauAbsentDeLaSequenceRecoitLeRangLePlusGrand() {
        assertThat(OrdreDesNiveaux.rang("6e")).isGreaterThan(OrdreDesNiveaux.rang("CM2"));
        assertThat(OrdreDesNiveaux.rang(null)).isGreaterThan(OrdreDesNiveaux.rang("CM2"));
    }

    /** ADR-0079 : un descripteur ecrit a la main a deja porte un blanc final invisible. */
    @Test
    void unBlancAutourDuNiveauNeLeFaitPasSortirDeLaSequence() {
        assertThat(OrdreDesNiveaux.rang("CM1 ")).isEqualTo(OrdreDesNiveaux.rang("CM1"));
    }

    private static ArchCondition<JavaClass> neJamaisEnumererUnNiveauDeLaSequence() {
        return new ArchCondition<>(
                "ne jamais enumerer un niveau de " + OrdreDesNiveaux.SEQUENCE) {

            @Override
            public void check(JavaClass type, ConditionEvents evenements) {
                if (!type.isEnum()) {
                    return;
                }
                for (String constante : constantes(type)) {
                    if (estUnNiveauDeLaSequence(constante)) {
                        evenements.add(SimpleConditionEvent.violated(type, type.getName()
                                + " declare la constante " + constante + ", qui est un niveau de la"
                                + " sequence " + OrdreDesNiveaux.SEQUENCE + ". Le niveau reste la"
                                + " chaine que le paquet declare (ADR-0006, DEC-0090) ; l'ordre se"
                                + " porte par la cle de tri d'ADR-0095, pas par un type enumere"));
                    }
                }
            }
        };
    }

    private static List<String> constantes(JavaClass type) {
        return type.getFields().stream()
                .filter(champ -> champ.getModifiers().contains(JavaModifier.STATIC))
                .filter(champ -> champ.getRawType().getName().equals(type.getName()))
                .map(JavaField::getName)
                .toList();
    }

    private static boolean estUnNiveauDeLaSequence(String constante) {
        return OrdreDesNiveaux.SEQUENCE.stream()
                .anyMatch(niveau -> niveau.toLowerCase(Locale.ROOT)
                        .equals(constante.toLowerCase(Locale.ROOT)));
    }
}
