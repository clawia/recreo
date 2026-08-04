# ADR-0008 - Loger tout le comportement de jeu sur le serveur et brider le JavaScript par un budget

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

C'est la dette n°4, et c'est celle qui a rendu les autres invisibles. Sur la version precedente :

- les huit mecaniques Java faisaient environ 55 lignes chacune et deleguaient tout ;
- le vrai comportement de jeu vivait dans **692 lignes de JavaScript**, qu'aucun test ne couvrait ;
- `application`, `adapter-web` et `adapter-persistence` n'avaient aucun test du tout ;
- les rares tests web assertionnaient le HTML par expression reguliere, donc cassaient au moindre
  changement de balisage, ce qui a decourage d'en ecrire d'autres.

Tant que le comportement est dans le JavaScript, la CI ne peut rien juger, ArchUnit ne voit rien,
Yanis n'a rien a mesurer et Nadia ne peut pas savoir si ce qu'elle a valide en jouant est ce que le
code garantit. La consequence est structurelle : **le Done technique perd son sens.**

`CLAUDE.md` fixe deja la direction technique (htmx et Thymeleaf, rendu serveur, fragments HTML,
jamais de JSON cote vues). Reste a dire ce que le JavaScript a le droit de faire, et a le verifier.

## Decision

### Le serveur detient l'etat et le verdict

Toute partie est une suite de requetes HTTP. Chaque coup de l'enfant est une requete htmx a
laquelle le serveur repond par un fragment Thymeleaf. L'etat de session, le score, la progression,
la comparaison entre la reponse donnee et la reponse attendue, et la decision de fin de partie sont
calcules dans `com.recreo.domain.mechanics.<mecanique>`, en Java pur, donc testables sans
navigateur.

Corollaire non negociable : **la bonne reponse ne quitte jamais le serveur avant que l'enfant ait
repondu.** Le HTML envoye ne porte aucun marqueur de correction (`data-correct`, `data-bonne`,
`data-solution`, `data-reponse`). Les propositions sont envoyees sans distinction ; seul le serveur
sait laquelle est juste. C'est a la fois une propriete de conception et une propriete pedagogique :
un enfant qui inspecte la page ne doit pas pouvoir gagner.

### Ce que le JavaScript a le droit de faire

1. htmx lui-meme, servi en fichier statique vendu avec l'application (pas de CDN, pas de build
   Node : `CLAUDE.md` impose le fat jar autonome).
2. Un unique fichier `recreo.js` d'agrement visuel : basculer une classe CSS, declencher une
   animation, deplacer le focus clavier. Budget : **4 096 octets et 100 lignes**, plafond verifie
   par un test.

### Ce que le JavaScript n'a pas le droit de faire

- Emettre une requete lui-meme (`fetch`, `XMLHttpRequest`) : les echanges passent par les attributs
  htmx, donc restent lisibles dans le gabarit.
- Manipuler du JSON (`JSON.parse`, `JSON.stringify`) : les vues echangent du HTML.
- Detenir un etat de jeu : pas de `localStorage`, pas de `sessionStorage`.
- Contenir un mot du vocabulaire de jeu (`score`, `correct`, `bonneReponse`, `reponse`) : si ces
  mots apparaissent dans le JavaScript, c'est qu'une decision de jeu a migre cote client.
- `eval`, sans surprise.

### Les tests web n'assertionnent pas le HTML par expression reguliere

Les assertions passent par jsoup : selection d'un element, lecture de son texte, comptage. Un test
qui casse doit signaler un changement de comportement, pas un changement de balisage. C'est la
contre-mesure a la dette n°5.

### L'echappatoire, nommee d'avance

Une mecanique futuriste a forte animation (canvas, glisser-deposer fluide) pourra demander un
**ilot JavaScript** : plus de code client, cantonne a un fichier et a une mecanique. Cet ilot ne
sera ouvert que par un ADR nomme, qui devra maintenir la regle centrale : **le verdict reste
serveur**. On leve le budget, jamais l'autorite. Tant qu'aucune story ne le demande, cet ilot
n'existe pas.

## Regle executable ArchUnit

Trois gardes, dans trois modules, parce que la propriete n'est pas seulement une propriete de
bytecode.

### 1. Toute mecanique a un test de comportement cote serveur

Fichier : `domain/src/test/java/com/recreo/domain/archi/ToutesLesMecaniquesSontTesteesTest.java`

```java
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
```

L'assertion `isNotEmpty()` est la par application d'ADR-0003 : c'est exactement le controle qui
manquait a la version precedente.

### 2. Budget et interdits du JavaScript, absence de marqueur de correction

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/FrontieresDuJavaScriptTest.java`

```java
package com.recreo.adapter.web;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrontieresDuJavaScriptTest {

    private static final Path JS = Path.of("src/main/resources/static/js");
    private static final Path GABARITS = Path.of("src/main/resources/templates");
    private static final long PLAFOND_OCTETS = 4096;
    private static final int PLAFOND_LIGNES = 100;

    private static final List<String> INTERDITS_JS = List.of(
            "fetch(", "XMLHttpRequest", "JSON.parse", "JSON.stringify",
            "localStorage", "sessionStorage", "eval(",
            "score", "correct", "bonneReponse", "reponse");

    private static final List<String> MARQUEURS_DE_CORRECTION = List.of(
            "data-correct", "data-bonne", "data-solution", "data-reponse");

    private static Stream<Path> fichiers(Path racine, String suffixe) throws IOException {
        return Files.walk(racine).filter(Files::isRegularFile)
                .filter(f -> f.getFileName().toString().endsWith(suffixe));
    }

    private static List<Path> javaScriptMaison() throws IOException {
        return fichiers(JS, ".js")
                .filter(f -> !f.getFileName().toString().startsWith("htmx"))
                .toList();
    }

    @Test
    void leJavaScriptDAgrementTientDansSonBudget() throws IOException {
        for (Path fichier : javaScriptMaison()) {
            assertThat(Files.size(fichier))
                    .as("ADR-0008 : %s depasse le budget de %d octets", fichier, PLAFOND_OCTETS)
                    .isLessThanOrEqualTo(PLAFOND_OCTETS);
            assertThat(Files.readAllLines(fichier))
                    .as("ADR-0008 : %s depasse %d lignes", fichier, PLAFOND_LIGNES)
                    .hasSizeLessThanOrEqualTo(PLAFOND_LIGNES);
        }
    }

    @Test
    void leJavaScriptNePrendAucuneDecisionDeJeu() throws IOException {
        for (Path fichier : javaScriptMaison()) {
            String source = Files.readString(fichier);
            assertThat(INTERDITS_JS.stream().filter(source::contains).toList())
                    .as("ADR-0008 : %s contient du vocabulaire de decision de jeu", fichier)
                    .isEmpty();
        }
    }

    @Test
    void aucunGabaritNeMarqueLaBonneReponse() throws IOException {
        for (Path gabarit : fichiers(GABARITS, ".html").toList()) {
            String source = Files.readString(gabarit);
            assertThat(MARQUEURS_DE_CORRECTION.stream().filter(source::contains).toList())
                    .as("ADR-0008 : %s livre la bonne reponse au client", gabarit)
                    .isEmpty();
        }
    }
}
```

### 3. Les tests web n'assertionnent pas par expression reguliere

Fichier : `adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/AssertionsWebTest.java`

```java
package com.recreo.adapter.web.archi;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo.adapter.web")
class AssertionsWebTest {

    @ArchTest
    static final ArchRule aucuneAssertionParExpressionReguliere = noClasses()
            .that().resideInAPackage("com.recreo.adapter.web..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("java.util.regex..")
            .as("ADR-0008 : le HTML s'asserte avec jsoup, pas par expression reguliere");
}
```

Cette classe-ci **inclut** les tests dans l'analyse (pas de `DoNotIncludeTests`), puisque c'est
justement le code de test qu'elle regarde ; elle vit dans `adapter-web` parce que les tests de ce
module ne sont pas sur le classpath de `bootstrap`.

**Ce qui n'est garde par aucun automate** : qu'un test web verifie un comportement plutot qu'un
detail de balisage. Interdire `java.util.regex` et fournir jsoup rend la bonne pratique la plus
facile, mais la pertinence de l'assertion reste du ressort de Yanis en revue.

## Consequences

- Chaque interaction coute un aller-retour HTTP. Sur un jeu de revision au tour par tour, c'est
  imperceptible en local et acceptable en reseau. Ce serait redhibitoire pour une mecanique
  temps reel : d'ou l'ilot nomme plus haut, et l'ADR qu'il exigera.
- Le fragment Thymeleaf de chaque mecanique devient la seule vue possible de son etat. C'est plus
  de HTML serveur et beaucoup moins de JavaScript.
- Les mots interdits dans le JavaScript sont recherches en texte brut : un commentaire contenant
  "score" fera echouer le test. C'est brutal et volontaire ; le message d'erreur nomme le fichier
  et le mot, la correction prend dix secondes.
- Le budget de 4 096 octets est arbitraire. Il est calibre pour laisser passer un agrement visuel
  et refuser une machine a etats. S'il devait etre releve deux fois de suite sans ADR, c'est que la
  regle serait devenue decorative.
- Mauvaise consequence assumee : la regle n°1 impose un test par mecanique, sans rien dire de sa
  qualite. Une classe de test vide la satisferait. La qualite reste le travail de Yanis ; la regle
  ne garantit que l'existence, ce qui est deja infiniment mieux que 692 lignes non couvertes.

## Alternatives considerees

- **Mesurer la couverture du JavaScript** plutot que le brider. Il faudrait une chaine de test
  front (Node, Jest, navigateur sans tete), donc un build Node, que `CLAUDE.md` exclut, et le
  comportement resterait hors du domaine. Ecarte.
- **Autoriser le JavaScript a calculer le verdict et le faire confirmer par le serveur.** Deux
  implementations de la meme regle de jeu, dont une non testee : c'est la dette n°4 avec une
  ceinture. Ecarte.
- **Interdire tout JavaScript hors htmx.** Tentant, mais un retour visuel immediat (une case qui
  clignote) compte pedagogiquement pour un enfant de CP, et l'obtenir sans une ligne de JavaScript
  contorsionne le HTML. Ecarte au profit d'un budget explicite.
- **Compter les lignes de JavaScript en revue de code.** C'est ce qui n'a pas ete fait pendant 25
  sprints. Ecarte.

## Ce qui me ferait changer d'avis

- Une mecanique validee pedagogiquement qui exigerait du canvas ou du glisser-deposer fluide :
  j'ouvre l'ilot par ADR, avec un budget propre a cette mecanique, et le verdict reste serveur.
- Une latence percue genante sur un vrai reseau : la reponse serait de reduire le nombre
  d'allers-retours (repondre plusieurs items par fragment), pas de deplacer la decision.
- Si la liste de mots interdits produisait plus de faux positifs que de captures reelles, je la
  reduirais aux quatre premiers (`fetch`, `XMLHttpRequest`, `JSON`, `localStorage`), qui sont
  structurels et non lexicaux.
