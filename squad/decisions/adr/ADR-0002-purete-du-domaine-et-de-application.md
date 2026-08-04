# ADR-0002 - Interdire toute dependance externe dans `domain` et dans `application`

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

La regle "le domaine est pur" est enoncee dans `CLAUDE.md`. Sur la version precedente, elle etait
encodee ainsi :

```java
noClasses().that().resideInAPackage("com.recreo.domain..")
        .should().dependOnClassesThat().resideInAnyPackage(
                "org.springframework..", "jakarta.persistence..");
```

C'est une **liste noire de deux entrees**. Elle laisse passer Jackson, SnakeYAML, Guava, Lombok,
SLF4J, Apache Commons, et toute bibliotheque qu'un developpeur presse ajoutera au `pom.xml` du
domaine pour gagner dix minutes. Une liste noire ne protege que contre ce qu'on a deja vu ; elle
donne l'illusion d'une garantie tout en n'en apportant qu'une fraction.

Le probleme est le meme pour `application` : la regle de la version precedente bannissait
`org.springframework..` et `jakarta..`, et rien d'autre.

## Decision

Remplacer les listes noires par des **listes blanches**, exprimees sur le bytecode :

- `com.recreo.domain..` ne depend que du JDK (`java..`) et de lui-meme.
- `com.recreo.application..` ne depend que du JDK, du domaine et d'elle-meme.

Consequences directement voulues : pas de journalisation dans le domaine ni dans l'application
(elle appartient aux adaptateurs), pas d'annotation d'aucun framework, pas d'utilitaire tiers, pas
de bibliotheque de validation. Le domaine et l'application se testent avec JUnit et AssertJ, qui
sont des dependances de portee `test` et n'entrent donc pas dans l'analyse.

Le `pom.xml` de `domain` ne declare **aucune** dependance de portee `compile`. Celui
d'`application` declare `recreo-domain` et rien d'autre.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/PureteDesCouchesTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class PureteDesCouchesTest {

    @ArchTest
    static final ArchRule leDomaineNeDependQueDuJdk = noClasses()
            .that().resideInAPackage("com.recreo.domain..")
            .should().dependOnClassesThat(
                    resideOutsideOfPackages("java..", "com.recreo.domain.."))
            .as("ADR-0002 : le domaine ne depend que du JDK et de lui-meme");

    @ArchTest
    static final ArchRule lApplicationNeDependQueDuJdkEtDuDomaine = noClasses()
            .that().resideInAPackage("com.recreo.application..")
            .should().dependOnClassesThat(
                    resideOutsideOfPackages("java..", "com.recreo.domain..", "com.recreo.application.."))
            .as("ADR-0002 : la couche application ne connait aucun framework");
}
```

Points verifies contre `archunit-1.5.0.jar` avant d'ecrire cet ADR, parce qu'une regle qui ne
compile pas ou qui echoue a tort serait pire que pas de regle :

- `JavaClass.Predicates.resideOutsideOfPackages(String...)` existe et est publique ;
- ArchUnit **exclut les types primitifs** de la construction des dependances
  (`Dependency.tryCreateDependency` filtre `targetClass.isPrimitive()`), donc un champ `int` ne
  declenche pas de faux positif ;
- un type tableau porte le paquet de son type composant (`ArrayClassDescriptor`), donc
  `String[]` est vu dans `java.lang` ;
- `..` couvre zero paquet, donc `com.recreo.domain..` selectionne aussi `com.recreo.domain`.

La non-vacuite de ces deux regles est prouvee par les contre-exemples decrits en ADR-0003.

**Amendement du 2026-08-04.** Cette derniere phrase etait fausse pour la seconde regle : seul le
domaine avait son contre-exemple (`DomainePollueContreExemple`), la purete de la couche
`application` n'avait jamais eu a echouer. Le pendant existe desormais,
`bootstrap/src/test/java/com/recreo/application/contreexemple/ApplicationPollueeContreExemple.java`
(annotee `@org.springframework.stereotype.Service`), et
`LesReglesMordentTest.lApplicationNeDoitPasDependreDUnFramework` verifie que la regle echoue en
citant la classe fautive et `org.springframework`.

A noter, parce que la nuance a coute une regle ailleurs : la remarque ci-dessus sur les primitifs
vaut pour la construction des **dependances** (`Dependency.tryCreateDependency`), pas pour la
resolution du **type brut d'un champ**, ou ArchUnit rend le primitif `int` comme une `JavaClass` de
paquet `java.lang`. C'est ce que la premiere ecriture de la regle centrale d'ADR-0006 avait suppose
a l'envers.

## Consequences

- Le domaine ne peut pas journaliser. Une mecanique qui veut "tracer" doit remonter l'information
  dans son resultat, ce qui la rend testable au lieu d'observable dans un fichier de log.
- Pas de Lombok : les records du JDK 21 couvrent le besoin de valeur immuable, et la dette n°6
  (records tres larges avec copies ecrites a la main) se traite par des types plus petits, pas par
  un generateur de `with...()`.
- La liste blanche demandera une modification d'ADR le jour ou une dependance sera reellement
  justifiee dans le domaine. C'est voulu : ce doit etre une decision, pas un ajout de ligne dans
  un `pom.xml`.
- Mauvaise consequence assumee : `java..` autorise tout le JDK, y compris `java.nio.file` et
  `java.net`. Une entree/sortie ecrite avec le JDK nu passerait cette regle. ADR-0007 la ferme
  separement en reservant l'acces au systeme de fichiers a `adapter-content`.

## Alternatives considerees

- **Garder la liste noire, en l'allongeant.** On ne peut pas enumerer ce qui n'existe pas encore.
  Ecarte.
- **`onlyDependOnClassesThat()` plutot que `noClasses().should().dependOnClassesThat()`.** Meme
  effet ici, mais la formulation negative produit un message d'erreur qui nomme la classe fautive
  et la dependance interdite, ce qui est plus utile en CI. Ecarte pour la lisibilite du rapport.
- **Maven Enforcer (`banTransitiveDependencies`).** Controle ce qui est declare, pas ce qui est
  utilise, et ne dit rien des paquets. Complementaire au mieux, pas suffisant. Ecarte pour le
  sprint 0.

## Ce qui me ferait changer d'avis

- Un besoin de precision decimale exacte ou de calendrier scolaire complexe qui justifierait une
  bibliotheque dans le domaine : je le trancherais par un ADR nomme, avec la bibliotheque ajoutee
  explicitement a la liste blanche, jamais par une exception locale.
- L'apparition d'une couche `application` qui aurait besoin d'une abstraction de transaction : ce
  serait le signe qu'un port manque, pas qu'il faut ouvrir la liste blanche.
