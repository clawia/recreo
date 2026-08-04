# ADR-0005 - Abandonner `RuleSet` et interdire les abstractions fantomes

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

Sur la version precedente, `RuleSet` etait cite dans `CLAUDE.md` et dans quatre prompts d'agents,
au meme rang que `DifficultyConfig`, comme l'un des deux piliers du contenu pilote par des regles.
Il n'existait dans aucun fichier Java. Pendant 25 sprints, huit agents ont raisonne sur une
abstraction qui n'existait pas, et l'ont citee dans leurs decisions.

Le degat n'est pas le type manquant, c'est le raisonnement fausse : quand un agent lit "le contenu
est pilote par un `RuleSet`", il suppose resolu un probleme qui ne l'est pas, et il ne pose pas la
question qu'il aurait dû poser, c'est-a-dire "qui decide quel item est tire, et selon quoi ?".

`CLAUDE.md` de `round-two` ne cite plus `RuleSet` que dans sa liste de dettes, et impose de
trancher : soit une abstraction existe dans le code, soit un ADR acte explicitement son abandon.

## Decision

**`RuleSet` est abandonne. Aucun type de `recreo` ne portera ce nom.**

Ce qui le remplace, et qui existe dans le code des le sprint 0 :

| Role que `RuleSet` etait cense tenir | Ce qui le tient reellement | Ou |
|---|---|---|
| Decider quel item est presente a l'enfant | `ContentSource`, interface a une methode `next(RandomSource, Set<ItemId> dejaVus)` | `com.recreo.domain.content` |
| Porter les regles de tirage d'un paquet fini | `ListContentSource`, implementation pure | `com.recreo.domain.content.source` |
| Porter le niveau de difficulte | rien : c'est le choix du paquet de contenu (ADR-0006) | - |
| Dire quelle mecanique joue quel contenu | l'entree de catalogue (ADR-0007) | `com.recreo.application.catalog` |

Autrement dit, le "pilotage par des regles" tient dans **une interface a une methode** et dans le
fait qu'un paquet de contenu est une donnee, pas du code. Il n'y a pas de moteur de regles, et il
n'y en aura pas tant qu'aucun besoin constate n'en demandera un.

Et une regle de gouvernance, qui est la vraie lecon : **toute abstraction citee dans la
documentation d'architecture porte une assertion d'existence executable.** Si le type disparait ou
n'a jamais existe, le build est rouge. La documentation ne peut plus decrire un code qui n'existe
pas.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/AbstractionsCiteesTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

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
```

**Correction apportee a la livraison.** La regle `itemExiste` etait annoncee ici comme prouvant que
"Item existe et est une interface scellee". Elle ne prouve que la moitie de cette phrase :
`beInterfaces()` ignore la cloture, et `archunit-1.5.0` n'expose aucun predicat de scellement (le
scellement n'est pas un drapeau d'acces du fichier de classe mais l'attribut `PermittedSubclasses`).
Une regle qui affirme plus que ce qu'elle verifie est exactement le mode de defaillance que ce
socle combat. Deux issues possibles : affaiblir la phrase, ou completer la verification. La seconde
a ete retenue, pour trois lignes, parce que le scellement d'`Item` est la couture d'extension
qu'ADR-0004 designe : le perdre par distraction doit etre rouge. Le test n'asserte volontairement
pas la liste des formes permises, qui doit pouvoir s'allonger sans toucher a ce fichier.

Le mecanisme central ici est `allowEmptyShould(false)` : sans lui, une regle dont le `that()` ne
selectionne aucune classe est consideree comme satisfaite. C'est precisement le comportement qui
aurait laisse `RuleSet` fantome. La valeur par defaut d'ArchUnit 1.5 est deja `false` via
`archRule.failOnEmptyShould=true` ; on l'ecrit sur chaque regle d'existence pour que l'intention
soit lisible sans connaitre la configuration.

Symetriquement, la regle `aucunTypeNommeRuleSet` selectionne tout `com.recreo..`, donc son `that()`
n'est jamais vide : elle ne peut pas etre neutralisee par le meme mecanisme.

Ce que cette regle **ne** garde **pas** : qu'un document cite une abstraction inexistante. Le
`relecteur-pedagogique` verifie l'exactitude des livrables non executables contre le code (voir
`squad/EQUIPE.md`), et toute abstraction qu'un ADR introduit doit arriver avec sa ligne dans ce
fichier de test. Si un troisieme nom fantome apparait malgre tout, la reponse sera d'ajouter son
assertion d'existence, pas de faire confiance.

## Consequences

- Il n'y a plus de "moteur de regles" dans le vocabulaire du projet. Les agents qui parlaient de
  `RuleSet` parleront de `ContentSource`, qui a une definition verifiable en trois lignes.
- Ajouter une abstraction au vocabulaire d'architecture coute une ligne de test. C'est un frein
  volontaire : une abstraction nommee dans la documentation est un engagement.
- Mauvaise consequence assumee : les assertions d'existence figent des noms pleinement qualifies.
  Renommer `ContentSource` demandera d'editer ce fichier. C'est le but ; un renommage
  d'abstraction centrale doit passer par un ADR.
- Trois assertions aujourd'hui. Si la liste devait depasser une dizaine d'entrees, ce serait le
  signe que le vocabulaire d'architecture est trop large, pas que la regle est trop stricte.

## Alternatives considerees

- **Implementer `RuleSet` pour de vrai.** Il aurait fallu lui trouver un role. Les trois roles
  candidats sont deja tenus : le tirage par `ContentSource`, le niveau par le choix du paquet, la
  liaison par le catalogue. Un `RuleSet` n'aurait ete qu'un intermediaire supplementaire.
  Ecarte : c'est l'abstraction speculative type.
- **Le garder comme nom pour `ContentSource`.** Le nom porte l'histoire d'une abstraction qui n'a
  jamais eu de contenu ; le reutiliser entretiendrait la confusion pour tout lecteur de `main`.
  Ecarte.
- **Se contenter de supprimer les mentions dans la documentation.** L'ancienne documentation etait
  deja "coherente" avec elle-meme. Sans assertion executable, rien n'empeche le fantome suivant.
  Ecarte.

## Ce qui me ferait changer d'avis

- Un besoin constate de composer plusieurs criteres de tirage sur un meme paquet (par exemple :
  reviser en priorite ce que l'enfant a rate, en s'interdisant les items vus le jour meme). A ce
  moment il faudra un type qui compose des criteres. Il ne s'appellera pas `RuleSet`, il naitra
  d'un besoin ecrit, et il arrivera avec son test.
- Si `ContentSource` devait porter plus de deux methodes, ce serait le signe qu'un role manque et
  qu'il faut le nommer, avec un ADR et une assertion d'existence.
