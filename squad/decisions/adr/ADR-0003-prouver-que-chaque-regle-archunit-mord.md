# ADR-0003 - Prouver que chaque regle ArchUnit mord, par un contre-exemple qui doit la faire echouer

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

C'est l'ADR le plus important du socle, parce qu'il conditionne la valeur de tous les autres.

La version precedente avait cinq regles ArchUnit vertes pendant 25 sprints. L'une d'elles etait :

```java
slices().matching("com.recreo.domain.mechanics.(*)..").should().notDependOnEachOther();
```

Elle etait verte, et elle ne prouvait rien. Les huit mecaniques etaient des coquilles d'environ
55 lignes qui deleguaient tout a un `SessionEngine` de 230 lignes place dans le paquet parent
`domain.mechanic`. Deux coquilles qui n'ont aucun comportement ne peuvent pas se coupler : la
regle etait **vraie par vacuite**. Le vrai comportement, lui, etait dans 692 lignes de JavaScript
qu'aucune regle ne regardait.

Une regle verte peut donc mentir de trois facons :

1. **Ensemble source vide** : `that()` ne selectionne aucune classe. ArchUnit fait echouer ce cas
   par defaut depuis la version 1.0 (`archRule.failOnEmptyShould`, valeur par defaut `true`),
   encore faut-il ne pas le desactiver.
2. **Ensemble source vide de sens** : les classes selectionnees existent mais sont vides de
   comportement. C'est le cas des mecaniques de la version precedente. Aucun reglage ne l'attrape.
3. **Regle qui ne peut pas echouer** : condition mal ecrite, paquet mal orthographie,
   `dependOnClassesThat` sur un paquet qui n'existe pas. Le build reste vert quoi qu'on ecrive.

Aucun de ces trois cas ne se voit en lisant le rapport de CI. Tous se voient si l'on demande a la
regle de faire ce qu'on attend d'elle : **echouer** face a du code qui la viole.

## Decision

**Toute regle ArchUnit qui garde une decision d'ADR est accompagnee d'un contre-exemple, et un
test verifie que la regle echoue sur ce contre-exemple.** Une regle sans contre-exemple n'est pas
consideree comme encodee : elle a le meme statut qu'une phrase dans un document.

Modalites :

1. Les contre-exemples vivent **uniquement** dans `bootstrap/src/test/java`, dans les paquets de
   production qu'ils doivent violer (une regle qui vise `com.recreo.domain.mechanic` ne peut etre
   violee que par une classe de ce paquet). Ils sont exclus des evaluations reelles par
   `ImportOption.DoNotIncludeTests`, qui filtre par emplacement de compilation.
2. Leur nom se termine par `ContreExemple`. Une regle interdit ce suffixe dans le code de
   production : un contre-exemple qui migre vers `src/main` casse le build.
3. `bootstrap/src/test/resources/archunit.properties` contient
   `archRule.failOnEmptyShould=true`. C'est la valeur par defaut d'ArchUnit ; on l'ecrit pour
   qu'une desactivation soit un diff visible et non un silence.
4. Chaque regle porte un `.as("ADR-XXXX : ...")`. Le rapport de CI nomme donc l'ADR a lire, pas
   seulement le paquet fautif.
5. La contre-mesure au cas n°2 (classes vides de sens) n'est pas un reglage mais une decision de
   conception : ADR-0004 interdit qu'un comportement de mecanique vive ailleurs que dans le paquet
   de la mecanique, et ADR-0008 exige que chaque mecanique ait un test de comportement cote
   serveur. Les contre-exemples verifient que ces deux interdictions mordent.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/LesReglesMordentTest.java`

```java
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

    @Test
    void laPureteDuDomaineEchoueSurUnDomaineQuiUtiliseSpring() {
        assertThatThrownBy(() -> PureteDesCouchesTest.leDomaineNeDependQueDuJdk
                .check(contreExemples("com.recreo.domain.contreexemple")))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void leContratDeMecaniqueRefuseUnMoteurPartage() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme
                .check(contreExemples("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void uneMecaniqueQuiLitLaMatiereEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaMatiere
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void unContenuQuiConnaitSaMecaniqueEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class);
    }
}
```

Fichiers de contre-exemple correspondants, tous dans `bootstrap/src/test/java` :

| Fichier | Ce qu'il viole |
|---|---|
| `com/recreo/domain/contreexemple/DomainePollueContreExemple.java` | ADR-0002 : classe du domaine annotee `@org.springframework.stereotype.Component` |
| `com/recreo/domain/mechanic/MoteurPartageContreExemple.java` | ADR-0004 : classe concrete avec comportement dans le paquet de contrat |
| `com/recreo/domain/mechanics/contreexemple/MecaniqueCoupleeContreExemple.java` | ADR-0004 : depend de `domain.content.source.ListContentSource` et lit `ContentDescriptor` |
| `com/recreo/domain/content/contreexemple/ContenuQuiSaitJouerContreExemple.java` | ADR-0004 : depend de `domain.mechanic.GameMechanic` |

Ces classes ne sont jamais instanciees. Elles existent pour etre refusees.

Nota : `ImportOption.OnlyIncludeTests` est le symetrique exact de `DoNotIncludeTests` ; les deux
existent dans `archunit-1.5.0`. Les contre-exemples sont donc importes par les tests de
non-vacuite, et seulement par eux.

## Consequences

- Le cout est reel : une regle coute desormais deux fichiers au lieu d'un. Il est tres inferieur
  au cout constate d'une regle qui ment pendant 25 sprints.
- Un developpeur qui durcit une regle doit verifier que le contre-exemple echoue toujours pour la
  bonne raison. `assertThatThrownBy(...).isInstanceOf(AssertionError.class)` ne verifie pas le
  motif : une regle cassee pourrait echouer pour une autre cause. Faiblesse assumee au sprint 0 ;
  si elle se manifeste, on ajoutera `.hasMessageContaining(...)` sur le nom de la classe fautive.
- Les paquets de production apparaissent en double dans l'arborescence de test de `bootstrap`.
  C'est deroutant a la premiere lecture, et c'est le seul moyen de violer une regle de paquet.
  D'ou le suffixe `ContreExemple`, obligatoire et verifie.
- Cette regle s'applique aux regles de garde (celles qui interdisent). Les regles purement
  descriptives, s'il en apparait, n'ont pas de contre-exemple ; il n'y en a aucune aujourd'hui.

## Alternatives considerees

- **Faire confiance a `failOnEmptyShould`.** Il ne traite que le cas n°1 sur trois, et c'est le
  cas le moins dangereux. La vacuite de la version precedente etait de type n°2. Ecarte comme
  suffisant, retenu comme complement.
- **Tests de mutation (PIT) sur les regles.** PIT mute du code de production, pas des regles
  d'architecture ; il ne repond pas a la question. Ecarte.
- **Revue humaine des regles a chaque sprint.** C'est exactement ce qui n'a pas fonctionne :
  personne n'a remarque en 25 sprints qu'une regle etait vide de sens. Ecarte.
- **Un depot d'exemples separe, compile a part.** Plus propre visuellement, mais il faut un module
  Maven de plus dont le seul role est de ne pas compiler dans le reacteur principal. Trop de
  machinerie pour quatre classes. Ecarte.

## Ce qui me ferait changer d'avis

- Si un contre-exemple devenait plus complexe que la regle qu'il garde, c'est que la regle est mal
  ecrite : je reecrirais la regle, pas le contre-exemple.
- Si ArchUnit proposait un jour un mecanisme natif de test de regle sur un ensemble synthetique de
  classes, je basculerais dessus et supprimerais les fichiers de contre-exemple.
