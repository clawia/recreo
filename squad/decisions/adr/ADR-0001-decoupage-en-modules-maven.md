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

Deux precisions de mise en oeuvre, notees a la livraison du squelette :

- coordonnees du parent, `com.recreo:recreo:0.1.0-SNAPSHOT`, packaging `pom`, cinq `<module>` dont
  deux avec un chemin a deux segments (`adapters/adapter-web`, `adapters/adapter-content`).
  `adapters/` n'a **pas** de POM d'agregation : cinq modules signifie cinq `pom.xml` de module, plus
  le parent, pas six ;
- `bootstrap` fixe `<finalName>recreo</finalName>` et declare `spring-boot-maven-plugin`, ce qui
  produit `bootstrap/target/recreo.jar`. Consequence a connaitre : `repackage` echoue tant que
  `bootstrap` n'a pas sa classe `@SpringBootApplication`, avec le message `Unable to find main
  class`. `mvn verify` n'est donc vert qu'a partir de l'etape de cablage.

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

**Amendement du 2026-08-04.** Ces deux regles n'avaient aucun contre-exemple, contrairement a ce
qu'ADR-0003 exige ; c'est `yanis-qa` qui l'a trouve et comble, avec
`DomaineDependantDeLApplicationContreExemple`, `AdaptateurWebDependantDuContenuContreExemple` et
les deux tests correspondants de `LesReglesMordentTest`. Un piege d'ecriture est a retenir, il vaut
pour toute regle de couches : `layeredArchitecture()` echoue par defaut sur une **couche declaree
vide**, avant meme d'evaluer la dependance visee. Un test de non-vacuite qui n'importerait que le
paquet du contre-exemple echouerait donc pour le mauvais motif, et resterait vert quand la regle
cesserait de mordre. Il faut importer aussi les classes de production des cinq couches - ce que
fait `productionEtContreExemples()`.

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

- ~~Une story qui demande de conserver la progression d'un enfant entre deux sessions : je cree
  `adapter-persistence` et j'ajoute sa couche a la regle dans le meme commit.~~ **Declencheur ferme
  le 2026-08-14 (s12) par `DEC-0028`**, sur decision de l'utilisatrice : *"je ne souhaite pas
  persister une partie, ce n'est pas le but de recreo."* Aucune story de ce genre ne sera plus
  ecrite ; ce declencheur-ci ne peut plus se produire. Detail de l'amendement ci-dessous.
- Un second adaptateur entrant (API pour un autre client) : je verifierais que `adapter-web` n'est
  pas devenu un fourre-tout avant d'en ajouter un troisieme.

## Amendement du 2026-08-14 (s12) - le declencheur de `adapter-persistence` est ferme, et une phrase de cet ADR ne l'est pas

`DEC-0028` acte l'abandon de la persistance sur decision de l'utilisatrice. Ce que j'amende ici est le
**premier point de "Ce qui me ferait changer d'avis"** ci-dessus, et rien d'autre : la decision du
sprint 0 - `adapter-persistence` n'est pas cree - est inchangee, et elle avait raison pour un motif
qui n'a pas bouge (un module sans usage est un module sans test).

**J'ai relu les trois textes avant d'ecrire, parce qu'un abandon entier et un abandon partiel ne
s'ecrivent pas pareil.** La lecture de `camille-po` est exacte sur les motifs : ni cet ADR, ni
`DEC-0003`, ni `CLAUDE.md` ne motivent la persistance par autre chose que conserver la progression
d'un enfant entre deux sessions - reprendre une partie interrompue, un historique de score. Aucun ne
nomme de compte adulte, de contenu edite hors du code, de journal durable.

**Une phrase de cet ADR est cependant plus large que ce motif, et elle n'est pas couverte par la
decision de l'utilisatrice.** C'est le point 2 de la decision : *"Le module sera cree, avec sa couche
dans la regle ArchUnit, dans le meme commit que la premiere story qui a besoin **d'ecrire quelque
part**."* "Ecrire quelque part" est plus vaste que "conserver une partie". L'utilisatrice a ferme le
besoin de conserver une partie ; elle n'a pas dit que rien ne serait jamais ecrit sur un disque. **Je
laisse donc cette phrase intacte et vivante**, exactement comme `DEC-0028` le prevoit elle-meme au
point 2 de sa decision ("ce sera un motif distinct, a justifier de nouveau le moment venu"). Etendre
la parole de l'utilisatrice a tout ce qui s'ecrit serait la lui faire dire.

Ce que cet amendement change, en une ligne : **le declencheur nomme n'est plus une eventualite normale
du backlog, il est ferme** ; tout autre besoin d'ecriture durable rouvrira la question par ses propres
motifs, et la couche `Persistance` devra alors etre ajoutee a la regle ArchUnit dans le meme commit
que le module, comme la section executable ci-dessus l'exige depuis le sprint 0.

**Ce que cet amendement n'est pas.** Il n'y a rien a encoder ici, et je le dis plutot que de le
laisser croire : `layeredArchitecture` ignore silencieusement les paquets qu'aucune couche ne couvre
(deja nomme dans la section executable), donc aucun test ne peut interdire la creation d'un module -
il peut seulement refuser une dependance qui traverse les couches declarees. Ce qui garde cet abandon
est **la revue et `DEC-0028`**, pas un automate. C'est le seul garde-fou honnete pour une decision de
perimetre produit, et c'est le motif pour lequel elle est ecrite dans le depot plutot qu'appliquee de
memoire : la dette n°1 de `main` etait une abstraction documentee partout et jamais implementee, et la
regle de `CLAUDE.md` est que soit une abstraction existe dans le code, soit un `ADR` acte son abandon.
Ce paragraphe est cet acte.
