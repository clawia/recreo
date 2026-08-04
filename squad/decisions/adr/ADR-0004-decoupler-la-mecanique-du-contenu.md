# ADR-0004 - Decoupler la mecanique du contenu par deux contrats fermes et une regle bidirectionnelle

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

C'est le principe directeur du produit : une mecanique de jeu doit servir n'importe quelle matiere
et n'importe quel niveau sans modification de code. Si ce decouplage tient, couvrir le programme
revient a ecrire du contenu ; s'il ne tient pas, on ecrit une mecanique par matiere et le produit
ne passe jamais l'echelle.

La version precedente affirmait ce decouplage et l'a mal garde. Deux regles etaient censees le
tenir :

```java
// (4) une mecanique ne depend pas d'un generateur concret
noClasses().that().resideInAnyPackage("com.recreo.domain.mechanic..", "com.recreo.domain.mechanics..")
        .should().dependOnClassesThat().resideInAPackage("com.recreo.domain.content.impl..");

// (5) les mecaniques ne dependent pas les unes des autres
slices().matching("com.recreo.domain.mechanics.(*)..").should().notDependOnEachOther();
```

Ce qu'elles laissaient passer, et qui s'est effectivement produit :

- **Le contournement par le paquet parent.** Tout le comportement de session (score, comptage,
  anti-doublon, fin de partie) vivait dans `domain.mechanic.SessionEngine`, 230 lignes, et les
  huit mecaniques etaient des coquilles de 55 lignes. La regle (5) etait vraie parce qu'il n'y
  avait rien a coupler. C'est la vacuite de type n°2 decrite en ADR-0003.
- **Le contournement par le nom de paquet.** La regle (4) vise `content.impl`. Un generateur pose
  dans `com.recreo.domain.content` ou dans `com.recreo.domain.content.generation` etait invisible
  pour elle. Une regle qui repose sur un nom de sous-paquet se contourne en renommant un
  repertoire.
- **Le sens inverse, jamais regarde.** Rien n'empechait un generateur de contenu de dependre de la
  mecanique. C'est pourtant le couplage le plus probable en pratique : le developpeur qui fabrique
  un contenu "pour le memory" lui fait produire des paires, puis "pour le bandit manchot" des
  rouleaux. A ce moment le contenu est ecrit pour une mecanique, et le principe du produit est
  mort, avec toutes les regles au vert.
- **La matiere qui remonte dans la mecanique.** Rien n'empechait une mecanique de lire
  `ContentDescriptor.matiere()` et de faire un `switch` dessus.

Question directrice : qu'est-ce qu'un developpeur presse casserait ? Il ferait, dans l'ordre :
mettre le comportement commun dans le paquet parent, faire lire la matiere par la mecanique pour
"juste adapter l'affichage", et faire produire au contenu la forme attendue par la mecanique. Les
trois doivent devenir impossibles.

## Decision

### 1. Deux paquets de contrat, fermes

`com.recreo.domain.content` et `com.recreo.domain.mechanic` sont des **paquets de contrat** : ils
ne contiennent que des interfaces, des records et des enums. Aucune classe concrete, donc aucun
comportement partage, donc aucun endroit ou hisser un `SessionEngine`.

```
com.recreo.domain.content                      contrat de contenu (ferme)
    ContentSource        interface   ContentDescriptor descriptor()
                                     Optional<Item> next(RandomSource hasard, Set<ItemId> dejaVus)
    Item                 sealed      permits Question ; expose ItemId id()
    Question             record      (ItemId id, String enonce, String bonneReponse, List<String> distracteurs)
    ItemId               record      (String value)
    ContentDescriptor    record      (String matiere, String niveau, String domaine, String competence)
    RandomSource         interface   int nextInt(int bound) ; tirage injectable, rejouable en test

com.recreo.domain.content.source               implementations de tirage, pures, sans entree/sortie
    ListContentSource    tire dans une liste finie, renvoie Optional.empty() quand le vivier est epuise

com.recreo.domain.mechanic                     contrat de mecanique (ferme)
    GameMechanic         interface   MechanicId id()
                                     GameSession start(ContentSource contenu, RandomSource hasard)
                                     GameSession apply(GameSession session, Move coup)
    GameSession          interface   SessionStatus status() + Score score()
    Move                 interface   marqueur ; chaque mecanique definit ses coups
    MechanicId           record      (String value)
    SessionStatus        enum        EN_COURS, TERMINEE
    Score                record      (int reussites, int tentatives)

com.recreo.domain.mechanics.<mecanique>        une mecanique = un paquet, tout son comportement dedans
```

Deux precisions sur ce bloc, ajoutees a la livraison des contrats :

- `Item` declare `ItemId id()`. Sans cet accesseur, `ListContentSource` devrait filtrer les items
  deja vus en discriminant sur la forme concrete (`instanceof Question`), c'est-a-dire connaitre
  les formes qu'elle transporte. L'identite est la seule chose que le contrat a besoin de savoir de
  tout item.
- `RandomSource.nextInt(int bound)` est volontairement la signature de `java.util.Random.nextInt` :
  un test se donne un tirage rejouable par `new Random(42)::nextInt`, sans qu'aucune
  implementation concrete n'ait a vivre dans le domaine.

`GameSession` est une **interface**, pas un record : l'etat de session appartient a la mecanique,
qui en definit la forme dans son propre paquet. C'est ce qui evite a la fois le moteur partage et
le record fourre-tout de la dette n°6.

### 2. Une mecanique ne voit que les deux contrats

Liste blanche, comme en ADR-0002 : une classe de `com.recreo.domain.mechanics..` ne peut dependre
que du JDK, du contrat de contenu, du contrat de mecanique, et de son propre paquet. Un generateur
concret, ou qu'il soit range, est hors de la liste blanche. Renommer un repertoire ne change rien.

### 3. Une mecanique ignore la matiere et le niveau

Aucune classe de `domain.mechanic..` ni de `domain.mechanics..` ne peut dependre de
`ContentDescriptor`. Le titre, la matiere et le niveau sont affiches par la couche web a partir de
l'entree de catalogue ; la mecanique, elle, ne peut pas s'y brancher, meme "juste pour
l'affichage". C'est la regle qui interdit le `switch (matiere)`.

### 4. Le contenu ignore l'existence des mecaniques

Aucune classe de `com.recreo.domain.content..` ne peut dependre de `domain.mechanic..` ni de
`domain.mechanics..`. Un contenu ne produit pas des rouleaux ni des paires : il produit des
`Item`. C'est le sens que la version precedente ne regardait pas.

### 5. Une implementation de `GameMechanic` vit dans son propre sous-paquet

`com.recreo.domain.mechanics.<mecanique>`, exactement un niveau. Interdit d'implementer
`GameMechanic` directement dans `domain.mechanics`, dans un sous-sous-paquet, ou dans un
adaptateur.

### 6. Les mecaniques restent isolees entre elles

`slices().notDependOnEachOther()` est conservee. Elle n'est plus decorative parce que les points 1
et 2 garantissent que le comportement est bien **dans** les paquets qu'elle compare.

### Ce que la liaison mecanique/contenu devient

Un jeu n'est pas une classe : c'est une **entree de catalogue** qui associe un `MechanicId` et un
paquet de contenu (ADR-0007). La meme `ContentSource` peut etre jouee par deux mecaniques, et la
meme mecanique peut jouer n'importe quel paquet dont la forme d'item lui convient. Cette
compatibilite de forme est verifiee au demarrage par un test d'integration, pas par ArchUnit :
voir la section suivante.

Le sprint 0 livre **une seule** mecanique, celle que Camille retient. `Item` n'a donc qu'une seule
forme permise, `Question`. La deuxieme forme sera ajoutee a l'interface scellee par la deuxieme
mecanique qui en aura besoin, pas avant.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/DecouplageMecaniqueContenuTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DecouplageMecaniqueContenuTest {

    private static final String CONTRAT_CONTENU = "com.recreo.domain.content";
    private static final String CONTRAT_MECANIQUE = "com.recreo.domain.mechanic";
    private static final String MECANIQUES = "com.recreo.domain.mechanics..";

    /** (1) Aucun comportement partage possible dans le contrat de contenu. */
    @ArchTest
    static final ArchRule leContratDeContenuEstFerme = classes()
            .that().resideInAPackage(CONTRAT_CONTENU)
            .should().beInterfaces()
            .orShould().beRecords()
            .orShould().beEnums()
            .as("ADR-0004 : le contrat de contenu ne contient que des interfaces, records et enums");

    /** (2) Le contournement du SessionEngine : impossible de hisser un moteur dans le contrat. */
    @ArchTest
    static final ArchRule leContratDeMecaniqueEstFerme = classes()
            .that().resideInAPackage(CONTRAT_MECANIQUE)
            .should().beInterfaces()
            .orShould().beRecords()
            .orShould().beEnums()
            .as("ADR-0004 : aucun moteur de session partage dans le contrat de mecanique");

    /** (3) Liste blanche : un generateur concret est hors de portee, ou qu'il soit range. */
    @ArchTest
    static final ArchRule uneMecaniqueNeVoitQueLesContrats = noClasses()
            .that().resideInAPackage(MECANIQUES)
            .should().dependOnClassesThat(resideOutsideOfPackages(
                    "java..", CONTRAT_CONTENU, CONTRAT_MECANIQUE, MECANIQUES))
            .as("ADR-0004 : une mecanique ne depend que du JDK et des deux contrats");

    /** (4) Interdit le switch sur la matiere ou le niveau. */
    @ArchTest
    static final ArchRule uneMecaniqueIgnoreLaMatiere = noClasses()
            .that().resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .should().dependOnClassesThat()
            .haveFullyQualifiedName(CONTRAT_CONTENU + ".ContentDescriptor")
            .as("ADR-0004 : une mecanique ne lit ni la matiere, ni le niveau, ni la competence");

    /** (5) Le sens que la version precedente ne regardait pas. */
    @ArchTest
    static final ArchRule leContenuIgnoreLesMecaniques = noClasses()
            .that().resideInAPackage(CONTRAT_CONTENU + "..")
            .should().dependOnClassesThat()
            .resideInAnyPackage(CONTRAT_MECANIQUE + "..", MECANIQUES)
            .as("ADR-0004 : un contenu ne connait pas la mecanique qui le jouera");

    /** (6) Une mecanique = un paquet, sinon l'isolation ne compare rien. */
    @ArchTest
    static final ArchRule uneMecaniqueVitDansSonProprePaquet = classes()
            .that().implement(CONTRAT_MECANIQUE + ".GameMechanic")
            .should().resideInAPackage("com.recreo.domain.mechanics.*")
            .allowEmptyShould(false)
            .as("ADR-0004 : une implementation de GameMechanic vit dans com.recreo.domain.mechanics.<mecanique>");

    /** (7) Isolation, desormais adossee a (1), (2) et (6). */
    @ArchTest
    static final ArchRule lesMecaniquesNeSeConnaissentPas = slices()
            .matching("com.recreo.domain.mechanics.(*)..")
            .should().notDependOnEachOther()
            .as("ADR-0004 : deux mecaniques ne dependent pas l'une de l'autre");
}
```

Details verifies contre `archunit-1.5.0.jar` : `ClassesShould.beRecords()`, `beEnums()`,
`beInterfaces()`, `ClassesThat.haveFullyQualifiedName(String)`, `ClassesThat.implement(String)`,
`ArchRule.allowEmptyShould(boolean)` existent tous. `com.recreo.domain.mechanic..` ne selectionne
pas `com.recreo.domain.mechanics` : le motif de paquet travaille par segment et exige un point
apres `mechanic`.

**Pourquoi cette regle n'est pas decorative, contrairement a celle de `main`.** Trois raisons, dans
l'ordre d'importance :

1. La regle (3) est une **liste blanche**, la version precedente avait une liste noire sur un nom
   de sous-paquet. Deplacer un generateur ne le rend plus invisible : tout ce qui n'est pas le
   contrat est refuse par construction.
2. Les regles (1), (2) et (6) suppriment la cachette. La regle d'isolation de la version
   precedente etait vraie parce que le comportement etait ailleurs ; ici il n'y a plus d'ailleurs
   ou le mettre. Un `SessionEngine` dans `domain.mechanic` casse (2) ; dans `domain.mechanics` sans
   sous-paquet il casse (6) des qu'il implemente le contrat, et il casse (7) des que deux
   mecaniques l'appellent.
3. La regle (5) ferme le sens inverse, qui n'existait pas, et la regle (4) ferme la fuite par les
   metadonnees, qui n'existait pas non plus.

Et surtout, conformement a ADR-0003, chacune de ces regles est adossee a un contre-exemple qui
**doit** la faire echouer. Une regle dont je ne peux pas montrer l'echec n'est pas encodee.

**Amendement du 2026-08-04.** La phrase precedente etait fausse a la livraison : sur les sept
regles, seules (2), (3), (4) et (5) avaient un contre-exemple. Les trois manquantes etaient
precisement celles sur lesquelles repose l'argument "pourquoi cette regle n'est pas decorative"
ci-dessus - (1) la fermeture du contrat de contenu, (6) une mecanique dans son propre paquet, et
(7) l'isolation entre mecaniques, c'est-a-dire la regle exacte qui est restee verte 25 sprints sur
`main` sans rien interdire. Elles sont desormais mises a l'epreuve :

| Regle | Contre-exemple | Test de non-vacuite |
|---|---|---|
| (1) `leContratDeContenuEstFerme` | `domain/content/MoteurDeContenuPartageContreExemple` : une classe porteuse de comportement dans le contrat de contenu, pendant exact de `MoteurPartageContreExemple` | `LesReglesMordentTest.unMoteurDeContenuPartageEstRefuse` |
| (6) `uneMecaniqueVitDansSonProprePaquet` | `domain/mechanics/MecaniqueHorsPaquetContreExemple` : implemente `GameMechanic` mais reside directement dans `domain.mechanics`, donc dans aucun sous-paquet | `LesReglesMordentTest.uneMecaniqueHorsDeSonPaquetEstRefusee` |
| (7) `lesMecaniquesNeSeConnaissentPas` | `domain/mechanics/premiere/MecaniquePremiereContreExemple` et `domain/mechanics/seconde/MecaniqueSecondeContreExemple`, la seconde reutilisant le comportement de la premiere | `LesReglesMordentTest.deuxMecaniquesNeDoiventPasSeConnaitre` |

Une precision sur ce que le contre-exemple de (7) prouve, et sur ce qu'il ne prouve pas : il prouve
que la regle **peut** echouer, donc qu'elle n'est pas vide de selection ni mal ecrite (vacuite de
types 1 et 3). Il ne prouve pas que les mecaniques ont du comportement a coupler (vacuite de type
2) : cela reste tenu par (1), (2) et (6), qui suppriment l'ailleurs ou le mettre, et par ADR-0008
qui exige un test de comportement par mecanique. Les deux moities du contre-exemple portent
volontairement du comportement reel plutot que d'etre des coquilles vides, pour que le couplage
qu'elles montrent soit celui qui a vraiment fait defaut sur `main`.

**Ce qu'ArchUnit ne peut pas garder**, et par quoi c'est garde a la place :

1. *La compatibilite de forme entre un paquet de contenu et la mecanique qui le joue* est une
   propriete des donnees, pas du bytecode. Elle est gardee par un test d'integration dans
   `bootstrap` (ADR-0007) : chaque entree de catalogue est demarree, et une forme d'item
   incompatible fait echouer le demarrage, donc le build.
2. *Le vocabulaire de matiere en dur dans une mecanique* - la story S0-3 demande explicitement
   qu'une regle echoue si la mecanique reference "la borne 10" ou "le mot addition". ArchUnit
   travaille sur le bytecode : il voit les types, pas les litteraux. Le complement est un test de
   lecture de sources, meme technique que le budget JavaScript d'ADR-0008 :

   Fichier : `domain/src/test/java/com/recreo/domain/archi/AucunVocabulaireDeMatiereTest.java`

   ```java
   private static final List<String> VOCABULAIRE_DE_MATIERE = List.of(
           "addition", "soustraction", "multiplication", "operande", "somme",
           "calcul", "syllabe", "conjugaison", "fraction");

   @Test
   void aucuneMecaniqueNeNommeUneMatiere() throws IOException {
       List<Path> sources = sourcesDesMecaniques();

       assertThat(sources)
               .as("ADR-0004 : cette regle ne prouve rien s'il n'y a aucune mecanique a lire")
               .isNotEmpty();

       for (Path source : sources) {
           String texte = Files.readString(source).toLowerCase(Locale.ROOT);
           assertThat(VOCABULAIRE_DE_MATIERE.stream().filter(texte::contains).toList())
                   .as("ADR-0004 : %s nomme une matiere, une mecanique n'en connait aucune", source)
                   .isEmpty();
       }
   }
   ```

   L'assertion `isNotEmpty()` et le controle d'existence du repertoire sont la par application
   d'ADR-0003 : une boucle sur zero fichier passe sans rien lire. Un paquet `mechanics` renomme ou
   deplace doit faire echouer ce test, pas le rendre muet.

   Quant a la borne numerique, elle est deja impossible autrement : une mecanique ne manipule que
   du texte (`Question` ne porte que des `String`, ADR-0006). Une constante `10` dans une mecanique
   n'aurait rien a comparer.

## Consequences

- Une mecanique ne peut pas afficher "Calcul mental CP" elle-meme. C'est la couche web qui le
  fait, a partir de l'entree de catalogue. Un developpeur trouvera cela penible la premiere fois ;
  c'est le prix exact du decouplage annonce.
- Le comportement commun eventuel entre deux mecaniques n'a plus d'endroit ou vivre. C'est
  volontaire : avec une seule mecanique au sprint 0, il n'y a rien a factoriser. Quand la deuxieme
  arrivera et qu'une duplication reelle sera constatee, elle sera traitee par un ADR qui dira ou
  la factorisation atterrit, et qui devra ne pas rouvrir le contrat aux classes concretes.
  Dupliquer trente lignes entre deux mecaniques est un cout acceptable ; recreer un `SessionEngine`
  ne l'est pas.
- L'interface scellee `Item` avec une seule forme permise sera jugee inutile par certains. Elle
  coute une ligne et nomme la couture d'extension. Si la deuxieme mecanique se contente de
  `Question`, on la supprimera.
- Mauvaise consequence assumee : la liste blanche (3) interdit aussi a une mecanique d'utiliser une
  classe utilitaire du domaine hors des deux contrats. Il n'y en a aucune aujourd'hui ; le jour ou
  il en faudra une, ce sera un ADR, pas un ajout discret.

## Alternatives considerees

- **Garder les deux regles de la version precedente en les completant.** Insuffisant : la faille
  principale n'est pas dans les regles mais dans la conception qui permettait de les contourner
  (un paquet parent ouvert aux classes concretes). Ecarte.
- **Un `Item` unique, large, couvrant toutes les formes de jeu.** C'est la dette n°6 (records de
  16 et 27 champs) reproduite dans le contrat central. Ecarte.
- **`Map<String, Object>` comme forme d'item universelle.** Aucun typage, aucun test possible sur
  la forme, et le contenu redevient implicitement ecrit pour une mecanique. Ecarte.
- **Definir des le sprint 0 les quatre formes d'item pressenties** (question, paires, sequence,
  association) parce que 61 paquets existent dans `contenu-repris/`. C'est de l'anticipation :
  trois de ces formes n'auraient aucun consommateur et donc aucun test. Ecarte, et c'est le point
  ou je me surveille le plus.
- **La mecanique recoit le `ContentDescriptor` en lecture seule.** Ca parait inoffensif, et c'est
  exactement par la que le `switch (matiere)` entre. Ecarte.

## Ce qui me ferait changer d'avis

- L'arrivee de la deuxieme mecanique : si elle a besoin d'une forme d'item differente, j'ajoute une
  permission a l'interface scellee. Si elle duplique un comportement reel, je tranche par un ADR ou
  la factorisation atterrit.
- Une mecanique qui aurait besoin d'un vrai service transverse (chronometre, tirage pondere) :
  j'ouvrirais un troisieme paquet de contrat plutot que d'assouplir la liste blanche.
- Si la regle (4) empechait un besoin d'accessibilite legitime (adapter la vitesse a un niveau,
  par exemple), la reponse serait un parametre porte par l'entree de catalogue, pas la lecture de
  la matiere par la mecanique.
