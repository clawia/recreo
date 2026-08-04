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
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("DomainePollueContreExemple")
                .hasMessageContaining("org.springframework");
    }

    @Test
    void leContratDeMecaniqueRefuseUnMoteurPartage() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme
                .check(contreExemples("com.recreo.domain.mechanic")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MoteurPartageContreExemple");
    }

    @Test
    void uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueCoupleeContreExemple")
                .hasMessageContaining("ListContentSource");
    }

    @Test
    void uneMecaniqueQuiLitLaMatiereEstRefusee() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaMatiere
                .check(contreExemples("com.recreo.domain.mechanics.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("MecaniqueCoupleeContreExemple")
                .hasMessageContaining("ContentDescriptor");
    }

    @Test
    void unContenuQuiConnaitSaMecaniqueEstRefuse() {
        assertThatThrownBy(() -> DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques
                .check(contreExemples("com.recreo.domain.content.contreexemple")))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("ContenuQuiSaitJouerContreExemple")
                .hasMessageContaining("GameMechanic");
    }
}
```

**Amendement du 2026-08-04, a la premiere execution de cette suite.** Les cinq tests etaient
initialement ecrits avec le seul `isInstanceOf(AssertionError.class)`, et la section Consequences
qualifiait de "faiblesse assumee" le fait de ne pas verifier le motif de l'echec. Le premier build
a montre que ce n'est pas une faiblesse theorique : quand une regle ne selectionne aucune classe,
ArchUnit leve exactement le meme `AssertionError` ("failed to check any classes"). Six des regles
du socle echouent d'ailleurs ainsi a cette etape du sprint, en attendant la mecanique et le
contenu. Un contre-exemple deplace, un paquet mal orthographie ou un `ImportOption` inverse
auraient donc laisse ces cinq tests **verts sans que rien ne soit prouve** - la vacuite de type
n°3, dans l'outil meme qui est cense la detecter.

Verification faite, et refaisable : en pointant `contreExemples` sur
`com.recreo.domain.contreexemple.absent`, le test echoue desormais avec le message
`Expecting throwable message ... to contain "DomainePollueContreExemple"`. Avec la version
initiale, il passait.

`hasMessageContaining` porte donc sur le nom de la classe fautive, et sur la dependance interdite
quand deux regles differentes visent le meme contre-exemple (regles 3 et 4 d'ADR-0004 visent
toutes deux `MecaniqueCoupleeContreExemple` : c'est `ListContentSource` d'un cote et
`ContentDescriptor` de l'autre qui distinguent le motif). Aucun de ces messages ne peut etre
produit par un ensemble source vide.

Fichiers de contre-exemple correspondants, tous dans `bootstrap/src/test/java` :

| Fichier | Ce qu'il viole |
|---|---|
| `com/recreo/domain/contreexemple/DomainePollueContreExemple.java` | ADR-0002 : classe du domaine annotee `@org.springframework.stereotype.Component` |
| `com/recreo/domain/mechanic/MoteurPartageContreExemple.java` | ADR-0004 : classe concrete avec comportement dans le paquet de contrat |
| `com/recreo/domain/mechanics/contreexemple/MecaniqueCoupleeContreExemple.java` | ADR-0004 : depend de `domain.content.source.ListContentSource` et expose une methode qui **retourne** `ContentDescriptor` |
| `com/recreo/domain/content/contreexemple/ContenuQuiSaitJouerContreExemple.java` | ADR-0004 : depend de `domain.mechanic.GameMechanic` |

Ces classes ne sont jamais instanciees. Elles existent pour etre refusees.

**Amendement du 2026-08-04, apres le rapport qualite de `yanis-qa`.** Ce texte exigeait un
contre-exemple pour "toute regle ArchUnit qui garde une decision d'ADR", et le depot ne le faisait
pas. Le compte exact, etabli en inventoriant les `ArchRule` du depot une par une : **21 regles de
garde, 5 contre-exemplifiees** au premier commit du sprint. `yanis-qa` en a comble 2 (ADR-0001) et
signale 5 autres ; l'inventaire en a montre **9 de plus** que son rapport ne cite pas, dont la
regle d'isolation entre mecaniques - celle-la meme dont cet ADR raconte qu'elle a menti 25 sprints
sur `main`. Un ADR qui exige a la lettre ce que le depot ne fait pas est la dette n°7 de `main`,
dans le fichier meme qui pretend l'interdire. Les 21 regles sont desormais mises a l'epreuve.

Trois choses sont donc tranchees ici : le perimetre exact de l'exigence, les deux seules dispenses
admises, et deux exceptions d'ecriture.

### Ce qui est une "regle de garde", et ce qui n'en est pas

L'exigence porte sur les `ArchRule` : un objet dont on peut appeler `check(JavaClasses)` sur un jeu
de classes de notre choix, donc dont on peut prouver l'echec. Elle ne porte pas sur les tests JUnit
ordinaires qui assertent directement une propriete (`itemEstScelle`,
`ilNYAQuUnSeulChargeurDeContenu`, `chaqueMecaniqueAUnTestDeComportement`,
`aucuneMecaniqueNeNommeUneMatiere`, les trois tests de `FrontieresDuJavaScriptTest`) : une
assertion directe est son propre temoin, elle devient rouge quand la propriete est fausse. Leur
seul mode de vacuite est l'ensemble vide, et il est deja traite par un `isNotEmpty()` ou par un
`containsExactly(...)` explicite - c'est exactement ce que `yanis-qa` a corrige sur
`FrontieresDuJavaScriptTest`. Ecrire un "contre-exemple" pour ces tests demanderait de dupliquer
leur code dans un test qui l'inverse : cout reel, information nulle.

### Les deux seules dispenses, et leur contrepartie

1. **Une regle d'existence** (`classes().that().haveFullyQualifiedName(X).should().beInterfaces()
   .allowEmptyShould(false)`, ADR-0005) n'a pas de classe fautive possible : la faute qu'elle garde
   est une **absence**. Sa mise a l'epreuve n'est donc pas une classe mais un jeu de classes ou `X`
   n'est pas, et ce qui doit mordre est `allowEmptyShould(false)`. C'est ce que fait
   `uneAbstractionCiteeQuiDisparaitEstRefusee` : les trois regles d'existence sont evaluees sur un
   paquet qui ne contient pas le type annonce, et l'echec attendu est nommement "failed to check any
   classes". Passer une seule d'elles a `allowEmptyShould(true)` rend ce test rouge - verifie.
2. **Une regle qui juge du code de test** (`AssertionsWebTest`, ADR-0008) ne peut pas etre
   contre-exemplifiee depuis `bootstrap`, qui ne voit pas les classes de test des autres modules.
   Son contre-exemple vit donc dans les tests de son propre module, et il doit etre soustrait a
   l'evaluation reelle sans quoi il la ferait echouer en permanence. C'est la seule regle du depot
   dont le contre-exemple ne vit pas dans `bootstrap/src/test`, et cette dispense ouvre un trou que
   je nomme plutot que de le taire : une assertion par expression reguliere ecrite dans le paquet
   `com.recreo.adapter.web.contreexemple` echapperait a la regle. Je l'accepte parce que le mode de
   defaillance reel est la regression distraite, pas la dissimulation deliberee dans un paquet
   nomme `contreexemple`. Le texte de la regle, lui, n'est pas affaibli : c'est le perimetre
   d'import qui exclut le contre-exemple, par un `ImportOption` de trois lignes visible dans le
   fichier.

Aucune autre dispense n'est admise. En particulier, "cette regle me parait sans risque a la
lecture" n'en est pas une : c'est le raisonnement qui a laisse une regle mentir 25 sprints.

### Deux exceptions d'ecriture

1. **Le contre-exemple d'une regle qui brule un nom porte ce nom exact**, et donc pas le suffixe
   `ContreExemple` : `RuleSet` (ADR-0005) ne peut pas s'appeler `RuleSetContreExemple`, sinon la
   regle ne le voit pas. C'est le paquet `..contreexemple` qui dit son role a sa place, et sa
   migration accidentelle vers `src/main` est deja rouge - par la regle qu'il viole, pas par la
   regle de suffixe. Les noms brules par sous-chaine (`Difficulty`, `Difficulte`, ADR-0006), eux,
   gardent le suffixe : `DifficultyConfigContreExemple` contient bien la sous-chaine interdite.
   Deux orthographes brulees demandent deux classes : un seul nom ne peut pas contenir les deux.
2. **Un contre-exemple ne doit violer qu'une regle a la fois**, sans quoi le motif de l'echec
   devient ambigu. C'est pour cela que `ReglageArithmetiqueContreExemple` est un record (une classe
   ferait aussi echouer ADR-0004 (1)) et que `MoteurDeContenuPartageContreExemple` n'a aucun champ
   (un champ numerique ferait aussi echouer ADR-0006). Quand deux regles visent volontairement la
   meme classe, c'est le second `hasMessageContaining` qui les distingue.

### Etat de l'application, regle par regle

L'inventaire complet, avec le contre-exemple de chaque regle et le test qui prouve son echec, est
tenu a chaque fin de sprint dans `squad/sprints/<sprint>/confirmation-archunit.md`. Un tableau dans
un ADR se perime ; un tableau produit avant chaque review ne se perime pas. Ce que cet ADR fixe est
la regle, pas l'inventaire.

Ce que ce travail a revele, et qui justifie a lui seul l'exigence : **la regle centrale d'ADR-0006
ne mordait pas.** Elle est corrigee, et l'ADR est amende. Aucune relecture n'avait vu le defaut ;
le premier contre-exemple l'a montre en une execution.

### Les garde-fous qui ne sont pas en Java

Le meme raisonnement s'applique au garde-fou de prefixes de commit, qui est en shell et non en
ArchUnit. Il est traite par **ADR-0009**, qui l'extrait du workflow et lui donne ses
contre-exemples.

Point d'ecriture a respecter, constate a la redaction de ces quatre classes : **un contre-exemple
doit porter la dependance interdite dans une signature**, pas dans un corps de methode. Une
variable locale de type `ContentDescriptor` ne produit aucune dependance dans le bytecode ; un
type de retour, un type de parametre ou un type de champ en produisent une. Un contre-exemple
ecrit avec une variable locale ne violerait rien et rendrait le test de non-vacuite trompeusement
vert.

Nota : `ImportOption.OnlyIncludeTests` est le symetrique exact de `DoNotIncludeTests` ; les deux
existent dans `archunit-1.5.0`. Les contre-exemples sont donc importes par les tests de
non-vacuite, et seulement par eux.

## Consequences

- Le cout est reel : une regle coute desormais deux fichiers au lieu d'un. Il est tres inferieur
  au cout constate d'une regle qui ment pendant 25 sprints.
- Un developpeur qui durcit une regle doit verifier que le contre-exemple echoue toujours pour la
  bonne raison. C'est ce que garantit `hasMessageContaining` sur le nom de la classe fautive (voir
  l'amendement ci-dessus) : le motif de l'echec est verifie, pas seulement le fait qu'il y en ait
  un. Ce qui reste non garanti est plus etroit : deux violations distinctes de la meme regle par la
  meme classe ne sont pas distinguees.
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
