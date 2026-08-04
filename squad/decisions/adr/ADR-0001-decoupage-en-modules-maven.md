# ADR-0001 - Decouper le build en cinq modules Maven et orienter les dependances vers l'interieur

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

`round-two` demarre sans une ligne de Java. Le decoupage en modules est la premiere decision
structurante : il conditionne ce qu'un developpeur peut ecrire sans meme s'en rendre compte,
parce que Maven refuse de compiler une dependance qui n'est pas declaree.

La version precedente avait cinq modules (`domain`, `application`, `adapters/adapter-web`,
`adapters/adapter-persistence`, `bootstrap`). Deux constats la concernant :

- le decoupage lui-meme n'etait pas en cause : la regle de dependance etait respectee ;
- en revanche `bootstrap` avait absorbe 2 774 lignes, dont environ 1 200 de chargement de contenu
  YAML. Le chargement de contenu est une entree/sortie : c'est un adaptateur, pas du cablage. Il
  a atterri dans `bootstrap` parce qu'aucun module ne lui etait ouvert, et parce que `bootstrap`
  est le seul module ou tout est permis.

Par ailleurs `adapter-persistence` existait des le debut alors qu'aucun besoin de durabilite
n'etait etabli. Un module vide ou faiblement utilise est un module sans test.

`CLAUDE.md` cite un decoupage indicatif et renvoie explicitement la decision a cet ADR.

## Decision

Cinq modules Maven, un POM parent `recreo` de packaging `pom` :

| Module | Artefact | Paquet racine | Depend de |
|---|---|---|---|
| `domain` | `recreo-domain` | `com.recreo.domain` | rien (JDK seul) |
| `application` | `recreo-application` | `com.recreo.application` | `domain` |
| `adapters/adapter-web` | `recreo-adapter-web` | `com.recreo.adapter.web` | `application`, `domain`, Spring Web, Thymeleaf |
| `adapters/adapter-content` | `recreo-adapter-content` | `com.recreo.adapter.content` | `application`, `domain`, SnakeYAML |
| `bootstrap` | `recreo-bootstrap` | `com.recreo.bootstrap` | tous les precedents, Spring Boot |

Versions figees au POM parent, verifiees sur Maven Central le 2026-08-04 :
`spring-boot-starter-parent` 4.1.0 (dont le socle Java est 17, on cible 21), `java.version` 21,
`archunit-junit5` 1.5.0, `jsoup` 1.23.1. Les versions gerees par le BOM Spring Boot (SnakeYAML,
JUnit 5, AssertJ) ne sont pas redeclarees.

Trois consequences directes :

1. **`adapter-content` existe des le sprint 0.** Le chargement du contenu pedagogique est un
   adaptateur sortant a part entiere, avec son propre module, ses propres tests, et l'interdiction
   de voir le web. C'est la contre-mesure structurelle a la dette n°3 : le code de chargement n'a
   plus le droit de vivre dans `bootstrap`.
2. **`adapter-persistence` n'est pas cree.** Aucune story du socle ne demande de durabilite : la
   session de jeu vit le temps d'une partie. Le module sera cree, avec sa couche dans la regle
   ArchUnit, dans le meme commit que la premiere story qui a besoin d'ecrire quelque part.
3. **`bootstrap` ne contient que du cablage** : la classe `@SpringBootApplication`, les `@Bean`,
   la configuration, et l'integralite des tests d'architecture.

Les tests d'architecture transverses vivent dans `bootstrap/src/test/java/com/recreo/archi/`,
seul endroit du reacteur ou toutes les classes de tous les modules sont sur le meme classpath.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/ArchitectureEnCouchesTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureEnCouchesTest {

    @ArchTest
    static final ArchRule lesDependancesVontVersLInterieur = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Domaine").definedBy("com.recreo.domain..")
            .layer("Application").definedBy("com.recreo.application..")
            .layer("Web").definedBy("com.recreo.adapter.web..")
            .layer("Contenu").definedBy("com.recreo.adapter.content..")
            .layer("Bootstrap").definedBy("com.recreo.bootstrap..")
            .whereLayer("Bootstrap").mayNotBeAccessedByAnyLayer()
            .whereLayer("Web").mayOnlyBeAccessedByLayers("Bootstrap")
            .whereLayer("Contenu").mayOnlyBeAccessedByLayers("Bootstrap")
            .whereLayer("Application").mayOnlyBeAccessedByLayers("Web", "Contenu", "Bootstrap")
            .whereLayer("Domaine").mayOnlyBeAccessedByLayers("Application", "Web", "Contenu", "Bootstrap")
            .as("ADR-0001 : les dependances vont toujours vers l'interieur");

    @ArchTest
    static final ArchRule lesAdaptateursNeSeConnaissentPas = slices()
            .matching("com.recreo.adapter.(*)..")
            .should().notDependOnEachOther()
            .as("ADR-0001 : deux adaptateurs ne se voient pas, le cablage est dans bootstrap");
}
```

Le jour ou `adapter-persistence` est cree, la couche `Persistance` est ajoutee a cette regle
**dans le meme commit** que le module. Un module dont la couche n'est pas declaree ici n'est pas
gouverne : `layeredArchitecture` ignore silencieusement les paquets qu'aucune couche ne couvre.
C'est le seul point ou cette regle peut mentir, il est nomme ici pour qu'on le surveille.

La deuxieme regle n'est pas decorative : `adapter-web` et `adapter-content` contiennent tous les
deux du code reel des le sprint 0. Un developpeur qui appelle le chargeur YAML depuis un
controleur, pour eviter de passer par un port, casse le build.

## Consequences

- Un module de plus que la version precedente sur le chargement, un de moins sur la persistence.
  Le compte est stable, la repartition est meilleure.
- `mvn verify` construit cinq modules : le temps de build augmente un peu par rapport a un module
  unique. C'est le prix de la contrainte de compilation, et il est faible a cette taille.
- Ajouter une dependance interdite devient un acte visible : il faut editer un `pom.xml`. La revue
  de code voit le diff, et la CI le refuse.
- Mauvaise consequence assumee : creer `adapter-persistence` plus tard demandera de deplacer du
  code (probablement une implementation en memoire d'un port). Ce deplacement est mecanique et il
  se fera avec le port deja ecrit, donc sans redecoupage.

## Alternatives considerees

- **Module unique avec des paquets.** Plus simple a construire, mais la seule barriere devient
  ArchUnit. Or ArchUnit s'execute apres la compilation : le developpeur ecrit d'abord le code
  interdit, puis apprend qu'il est interdit. Maven, lui, refuse en amont. Ecarte.
- **Creer `adapter-persistence` tout de suite** pour "ne pas y revenir". C'est exactement
  l'anticipation que je m'interdis : un module sans usage est un module sans test, et la version
  precedente en a fourni la demonstration. Ecarte.
- **Charger le contenu depuis `bootstrap`, comme avant.** C'est la dette n°3. Ecarte.
- **BOM Spring Boot importe au lieu du parent `spring-boot-starter-parent`.** Plus pur (le parent
  n'apporte alors aucun plugin), mais il faut recabler a la main le plugin de repackaging et la
  gestion des ressources. La purete est deja garantie par ADR-0002 au niveau du bytecode. Ecarte
  au nom de la simplicite.

## Ce qui me ferait changer d'avis

- Une story qui demande de conserver la progression d'un enfant entre deux sessions : je cree
  `adapter-persistence` et j'ajoute sa couche a la regle dans le meme commit.
- Un second adaptateur entrant (API pour un autre client) : je verifierais que `adapter-web` n'est
  pas devenu un fourre-tout avant d'en ajouter un troisieme.
