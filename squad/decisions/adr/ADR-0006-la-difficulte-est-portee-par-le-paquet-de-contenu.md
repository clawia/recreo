# ADR-0006 - Supprimer la configuration de difficulte du domaine et la porter par le paquet de contenu

- **Statut** : Accepte
- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Sprint** : s00

## Contexte

La version precedente avait ce type au centre de son contrat de contenu :

```java
public record DifficultyConfig(int minOperand, int maxOperand, Set<Operation> operations, int distractorCount) { }
```

Quatre champs, dont trois purement arithmetiques, dans un produit qui vise **toutes** les matieres
du programme CP et CM1. Consequence observee : chaque contenu de francais, d'anglais, d'histoire ou
de geometrie devait remplir `minOperand`, `maxOperand` et `operations` avec des valeurs factices
pour que l'objet soit constructible. Le validateur du record exigeait meme `operations` non vide :
un paquet de vocabulaire anglais devait donc declarer qu'il pratiquait l'addition.

Le probleme de fond n'est pas le nom des champs, c'est le postulat : que la difficulte soit un
**parametre de generation** commun a toutes les matieres. Ce postulat est faux. La difficulte
d'une addition se regle par des bornes d'operandes ; celle d'une liste de mots a lire se regle par
le choix des mots ; celle d'une frise chronologique par le nombre d'evenements et leur proximite.
Il n'existe pas de parametre commun, et fabriquer un type qui pretend le contraire oblige tout le
monde a mentir.

Il faut aussi entendre l'autre risque : remplacer ce record par un sac generique
(`Map<String, String>`) rend le mensonge invisible au lieu de le supprimer.

## Decision

**Il n'y a pas de type de configuration de difficulte dans le domaine.** `DifficultyConfig` n'est
pas remplace : il est supprime.

La difficulte est portee a deux endroits, et a deux endroits seulement :

1. **Le choix du paquet de contenu.** Un paquet couvre une competence, a un niveau. `CP - additions
   jusqu'a 10` et `CM1 - tables de multiplication` sont deux paquets, pas un paquet et un reglage.
   C'est deja la realite des 61 paquets de `contenu-repris/`, tous mono-niveau. Regler la
   difficulte, c'est choisir le paquet : aucune ligne de Java n'est necessaire pour ajouter un
   niveau.
2. **La progression a l'interieur d'une partie**, si une story la demande : elle appartient a la
   mecanique et vit dans son paquet, pilotee par ce que l'enfant reussit, pas par une
   configuration externe.

Le contrat de contenu ne porte donc que du **texte** :

```java
public record ContentDescriptor(String matiere, String niveau, String domaine, String competence) { }
public record Question(ItemId id, String enonce, String bonneReponse, List<String> distracteurs) implements Item { }
```

`niveau` est une chaine, pas un enum. Un enum `Niveau { CP, CM1 }` obligerait a modifier du code
Java le jour ou le CE1 arrive ; le principe du produit est justement qu'ajouter du contenu ne
touche pas au code. Meme raisonnement pour `matiere`. La valeur admise est controlee au chargement
par le schema de paquet (ADR-0007) et par le referentiel de competences que tient Camille, pas par
le compilateur.

Cas particulier, et il est reel des le sprint 0 : le contenu **combinatoire**. La story S0-4
demande des additions qui varient d'une manche a l'autre, donc un tirage, pas une liste figee. Ses
bornes (`sommeMaximale`, `nombreDePropositions`) sont des parametres de son **unique**
implementation de `ContentSource`, `com.recreo.domain.content.source.AdditionsBorneesContentSource`,
declares dans son propre paquet YAML sous `parametres` (ADR-0007) et lus par sa seule fabrique.

Trois proprietes en decoulent, et ce sont elles qui distinguent cette solution de
`DifficultyConfig` :

- ces bornes ne figurent dans **aucun** type partage : un paquet de vocabulaire anglais ne les voit
  pas et n'a rien a en faire ;
- la mecanique ne les voit pas non plus : elle recoit des `Question` dont l'enonce et les
  propositions sont du texte (ADR-0004) ;
- ajouter "les additions jusqu'a 20" est un fichier YAML, sans une ligne de Java.

## Regle executable ArchUnit

Fichier : `bootstrap/src/test/java/com/recreo/archi/DifficulteTest.java`

```java
package com.recreo.archi;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.recreo", importOptions = ImportOption.DoNotIncludeTests.class)
class DifficulteTest {

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
     * Le contrat de contenu ne porte que du texte : un champ numerique y serait forcement
     * le reglage d'une matiere, que les autres matieres devraient remplir de valeurs factices.
     */
    @ArchTest
    static final ArchRule leContratDeContenuNePorteQueDuTexte = fields()
            .that().areDeclaredInClassesThat().resideInAPackage("com.recreo.domain.content")
            .and().doNotHaveModifier(JavaModifier.STATIC)
            .should().haveRawType(resideInAnyPackage(
                    "java.lang", "java.util", "com.recreo.domain.content"))
            .as("ADR-0006 : le contrat de contenu porte du texte, jamais des bornes de matiere");
}
```

La troisieme regle est celle qui mord vraiment. ArchUnit exclut les primitifs de la construction
des **dependances** mais pas de la **resolution du type brut d'un champ** : le type brut de
`int minOperand` est le `JavaClass` du primitif `int`, dont le nom de paquet est vide, donc hors de
la liste blanche. Rajouter `int minOperand`, `Set<Operation> operations` (l'enum `Operation`
residerait hors du contrat) ou n'importe quel reglage arithmetique dans `Question` ou dans
`ContentDescriptor` fait echouer le build. Les champs statiques sont exclus du controle pour ne pas
achopper sur les champs synthetiques `$VALUES` d'un eventuel enum.

**Amendement du 2026-08-04 : les deux phrases precedentes etaient fausses, et la regle ne mordait
pas.** C'est le contre-exemple exige par ADR-0003 qui l'a montre, a sa premiere execution, sur la
regle que cet ADR presentait comme "celle qui mord vraiment".

1. Le nom de paquet du primitif `int`, tel qu'ArchUnit 1.5 le rend
   (`JavaField.getRawType().getPackageName()`), n'est pas vide : c'est **`java.lang`**, mesure par
   sonde et non deduit. Comme `java.lang` figurait dans la liste blanche, **tous** les primitifs
   etaient admis. `DifficultyConfig(int minOperand, int maxOperand, ...)` serait passe sans un mot.
   Accessoirement, `java.lang` admettait aussi `Integer`, `Double` et `Boolean`.
2. Le type brut de `Set<Operation> operations` est `java.util.Set`, qui etait dans la liste blanche.
   L'argument generique n'est pas regarde par `haveRawType`. La seconde forme de la dette passait
   donc aussi.

Autrement dit, cette regle interdisait un champ `java.time.LocalDate` et laissait passer les deux
seules formes que la dette n°2 a reellement prises. La regle est reecrite ; la propriete annoncee
est inchangee, c'est son encodage qui etait faux.

```java
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
                    .forEach(type -> evenements.add(SimpleConditionEvent.violated(champ,
                            champ.getFullName() + " cite le type " + type.getName() + ", ...")));
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

private static boolean estPermis(JavaClass type) {
    return type.getName().equals(String.class.getName())
            || type.getPackageName().equals("java.util")
            || type.getPackageName().equals(CONTRAT_CONTENU);
}
```

Trois differences, et une seule idee : ne plus juger le type brut mais **tous les types que la
declaration cite**, arguments generiques compris ; n'admettre de `java.lang` que `String` et non le
paquet entier ; garder `java.util` pour les collections et le paquet du contrat pour `ItemId`. Le
prix est une `ArchCondition` ecrite a la main, une vingtaine de lignes la ou il y en avait cinq.
Je l'assume : c'est le seul encodage qui ferme les deux portes, et une regle de cinq lignes qui ne
mord pas coute plus cher que vingt lignes qui mordent. Ce qui me ferait revenir a la forme courte :
un `haveRawType` capable de recevoir le type declare et non l'erasure, s'il apparait dans une
version ulterieure d'ArchUnit.

Contre-exemples, tous deux dans `bootstrap/src/test/java/com/recreo/domain/content/` :

| Fichier | Ce qu'il ramene | Test de non-vacuite |
|---|---|---|
| `ReglageArithmetiqueContreExemple` (record, `int sommeMaximale`, `int nombreDePropositions`) | la borne primitive | `LesReglesMordentTest.uneBorneArithmetiqueDansLeContratDeContenuEstRefusee` |
| `ReglageEnCollectionContreExemple` (record, `Set<OperationContreExemple>`, `List<Integer>`) | la borne cachee dans un argument generique | `LesReglesMordentTest.uneBorneArithmetiqueCacheeDansUneCollectionEstRefusee` |

L'enum `OperationContreExemple` vit dans `domain.content.contreexemple` et non dans le contrat :
place dans le contrat, il serait permis, et le contre-exemple ne prouverait rien.

Les deux regles de nommage ont elles aussi recu leur contre-exemple, une par orthographe
(`DifficultyConfigContreExemple`, `DifficulteConfigContreExemple`) : un seul nom ne peut pas
contenir les deux sous-chaines brulees. Test :
`LesReglesMordentTest.unTypeDeDifficulteDansLeDomaineEstRefuse`.

Ce que la nouvelle regle ne garde toujours pas : un champ `List<String>` dont les chaines seraient
en fait des nombres ecrits en texte. Aucune regle de bytecode ne verra jamais cela ; c'est le
schema de paquet (ADR-0007) et la relecture de contenu qui le voient.

Les deux premieres regles sont des interdictions de nommage, donc contournables par un
developpeur determine qui appellerait son type `Reglage`. Elles ne sont pas la pour arreter la
mauvaise foi mais pour arreter la reintroduction distraite, qui est le mode de defaillance reel.
C'est la troisieme regle qui tient la propriete de fond.

`fields().that().areDeclaredInClassesThat()`, `MembersThat.doNotHaveModifier(JavaModifier)` et
`FieldsShould.haveRawType(DescribedPredicate)` sont verifies presents dans `archunit-1.5.0`.

## Amendement du 2026-09-04 (s28, `theo-archi`) - une exemption nommee, datee et plafonnee pour le cadre en pourcentage

**Ce qui a joue.** `ADR-0080`, decision 2, ecrit le 2026-09-03 par le meme auteur, impose verbatim
`CadreDeZone(int x, int y, int largeur, int hauteur)` dans `com.recreo.domain.content`, et **ne cite
pas cet `ADR` parmi ceux qu'il prolonge**. Le conflit a ete decouvert par `sofia-sm` en implementant
`S28-5`, par le reacteur et non par une relecture : `bash outils/mvn-jdk21.sh -B clean verify`,
**BUILD FAILURE, 1 echec sur 1166 tests**, quatre violations nommant `CadreDeZone.x`,
`CadreDeZone.y`, `CadreDeZone.largeur` et `CadreDeZone.hauteur`. Mesure refaite ici avant de decider,
sur le meme arbre : `bash outils/mvn-jdk21.sh -B -o -pl bootstrap -am test -Dtest=DifficulteTest`,
**3 tests, 1 echec**, les memes quatre violations. Perimetre de cette mesure-la : le module
`bootstrap` et ses amonts, `-am` porte, pas le depot.

### Le critere applique avant de percer la regle

Cet `ADR` existe contre un mecanisme precis, ecrit dans son contexte : **une composante qu'un paquet
de contenu doit remplir sans qu'elle le concerne**, donc de valeurs factices - *"un paquet de
vocabulaire anglais devait declarer qu'il pratiquait l'addition"*. `ADR-0069` a etendu la propriete
au non chiffre avec le meme test : *"une composante qu'aucune autre forme d'item ne remplit autrement
que par une valeur par defaut"*.

Applique aux quatre entiers de `CadreDeZone`, ce test ne trouve rien a refuser. Aucun paquet ne les
remplit de valeurs factices : un paquet qui n'a pas de zones ne construit ni `Zone`, ni
`CadreDeZone`. Et ces entiers ne sont pas un **reglage** pose au-dessus du contenu, ils **sont** du
contenu - la position d'une etiquette sur un dessin, au meme titre que l'enonce est du texte. Un
pourcentage de la boite d'une image veut dire la meme chose en mathematiques CP et en
histoire-geographie CM1, la ou `minOperand` ne veut rien dire hors de l'arithmetique.

### Ce qui a ete essaye avant, et mesure

**1. La transposition d'`ADR-0069` - un type dedie qui porte la grandeur - est impossible, et ce
n'est pas une opinion.** Sonde du 2026-09-04, `record PourcentageSonde(int valeur)` pose dans
`com.recreo.domain.content`, meme commande que ci-dessus : **rouge**, message
`com.recreo.domain.content.PourcentageSonde.valeur cite le type int`. Le motif est structurel :
l'enveloppe redeclare le `int` un cran plus bas, dans le paquet exact que cette regle garde. Sortir
l'enveloppe du paquet ne sauve rien : `Zone.cadre` citerait alors un type hors liste blanche, et
elargir la liste a un sous-paquet serait une exemption plus large que celle qu'on cherche a eviter.
**`ADR-0069` n'a fonctionne que parce qu'un domaine de valeurs fini s'enumere et qu'un enum ne porte
aucun champ d'instance.** Une grandeur ne s'enumere pas. L'affirmation de `sofia-sm` sur ce point est
verifiee, pas reprise.

**2. Aucun predicat sur le bytecode ne distingue la geometrie de la dette.**
`CadreDeZone(int x, int y, int largeur, int hauteur)` et
`ReglageArithmetiqueContreExemple(int sommeMaximale, int nombreDePropositions)` sont deux records du
meme paquet dont toutes les composantes sont des `int` : **la seule chose qui les separe est le nom
de leurs composantes et ce qu'un humain sait qu'elles veulent dire.** Toute condition structurelle
qui admettrait le premier admettrait le second, et ferait passer au vert la morsure qui tient cet
`ADR` depuis s00. C'est le test auquel toutes les formulations elegantes ont echoue - y compris la
plus tentante, *"un type dont aucune mecanique ne depend"*, qui aurait admis `DifficultyConfig` le
jour ou personne ne le lit encore.

**3. Revenir sur la decision 2 d'`ADR-0080` est strictement pire, sur ses trois branches.** Porter
les quatre pourcentages en `String` supprime les invariants du constructeur compact, deplace une
analyse de texte dans le gabarit et **rend inecrivable la clause 10 d'`ADR-0080`**, qui est fondee
sur l'existence du type ; c'est aussi l'alternative 3 d'`ADR-0069`, deja ecartee. Faire vivre la
geometrie hors du domaine, dans un second chemin de contenu vers l'adaptateur web, cree une seconde
source de verite pour un meme paquet, donc un ecart muet le jour ou les deux divergent - la famille
de defaut que ce depot sanctionne. Un sous-paquet `content.geometrie` oblige a elargir la liste
blanche a un paquet entier, un trou plus grand que celui qu'on ouvre ici.

### Decision

**La regle `leContratDeContenuNePorteQueDuTexte` admet le type `int`, et lui seul, quand il est porte
par un type du contrat de contenu dont les champs d'instance sont exactement `x`, `y`, `largeur` et
`hauteur`.** Rien d'autre n'est admis, sous aucune autre forme.

- **Le plafond vaut exactement la taille de la dette** : quatre composantes, ces quatre noms-la. Une
  cinquieme composante, une de moins ou un autre nom, et le type entier retombe sous la regle.
- **Seul `int` est exempte.** `boolean`, `long`, `double`, `Integer`, `java.time.LocalDate` et tout
  argument generique restent refuses, sur le cadre comme ailleurs.
- **L'exemption ne s'applique jamais a une forme d'item ni au descripteur** : ajouter
  `int x, int y, int largeur, int hauteur` a `Question` ne place pas `Question` sous l'exemption, ses
  champs d'instance n'etant pas exactement ces quatre-la.
- Cette exemption est **datee** et sa reouverture est un `ADR`, pas une ligne de code.

### Ce qui l'encode, et la preuve qu'elle est plafonnee

**Fichier** : `bootstrap/src/test/java/com/recreo/archi/DifficulteTest.java`,
`estUneComposanteDeCadreEnPourcentage(JavaField, JavaClass)` et la constante
`COMPOSANTES_DU_CADRE_EN_POURCENTAGE`. **Aucune `ArchRule` n'est ajoutee** : le compte de regles du
depot est inchange.

```java
private static final Set<String> COMPOSANTES_DU_CADRE_EN_POURCENTAGE =
        Set.of("x", "y", "largeur", "hauteur");

private static boolean estUneComposanteDeCadreEnPourcentage(JavaField champ, JavaClass type) {
    return type.getName().equals(int.class.getName())
            && champ.getOwner().getFields().stream()
                    .filter(autre -> !autre.getModifiers().contains(JavaModifier.STATIC))
                    .map(JavaField::getName)
                    .collect(Collectors.toSet())
                    .equals(COMPOSANTES_DU_CADRE_EN_POURCENTAGE);
}
```

**Jeu fautif du plafond** :
`bootstrap/src/test/java/com/recreo/domain/content/CadreEtenduDUneBorneContreExemple.java`, un record
`(int x, int y, int largeur, int hauteur, int sommeMaximale)` - la seule route par laquelle la dette
n. 2 pourrait rentrer par cette exemption, en gardant les quatre composantes du rectangle pour se
ranger sous elle. **Morsure** :
`LesReglesMordentTest.unCadreEtenduDUneBorneArithmetiqueEstRefuse`.

**Ce que cette morsure ne prouve pas**, ecrit plutot que laisse a decouvrir : ce jeu fautif aurait
aussi ete refuse par la regle d'avant l'amendement. **Ce qu'elle prouve est le plafond, et c'est
mesure par mutation** : `equals` remplace par `containsAll` dans la condition ci-dessus - c'est-a-dire
l'exemption elargie a *"les quatre composantes, plus ce qu'on veut"* - rend `LesReglesMordentTest` a
**34 tests, 1 echec, et cet echec est exactement
`unCadreEtenduDUneBorneArithmetiqueEstRefuse`**. La mutation a ete annulee ; c'est la forme `equals`
qui est commitee.

**Les quatre morsures historiques de la regle restent rouges**, verifie sur le meme arbre :
`ReglageArithmetiqueContreExemple`, `ReglageEnCollectionContreExemple`,
`ReglageDeLongueurDePartieContreExemple` (`ADR-0012`) et `ModeDeSaisieEnBooleenContreExemple`
(`ADR-0069`). C'est la preuve qui compte le plus, et elle porte sur du materiau qui n'a pas ete ecrit
pour cet amendement : la dette de s00 est toujours refusee mot pour mot.

### Consequences, y compris les mauvaises

- **Le contrat de contenu porte desormais des entiers, et la phrase "que du texte" n'est plus vraie
  a la lettre.** Le titre de la regle est conserve parce qu'il dit sa raison d'etre ; ce paragraphe
  est ce qui empeche de le lire comme une garantie totale.
- **Le plafond est un jeu de noms de composantes.** C'est une convention, et une convention se
  contourne par un developpeur determine qui appellerait ses bornes d'arithmetique `x` et `largeur`.
  C'est exactement le statut que cet `ADR` accorde deja a ses deux regles de nommage : elles
  n'arretent pas la mauvaise foi, elles arretent la reintroduction distraite, qui est le mode de
  defaillance reel.
- **Ce qui me ferait revenir dessus** : une deuxieme demande d'exemption. Une seule exemption nommee
  reste lisible ; deux signifient que le perimetre de la regle est le mauvais, et il faudra alors
  reecrire la regle sur les types que tout paquet construit, pas sur un paquet Java entier.

## Consequences

- Un paquet de contenu = un niveau = une competence. Il y aura plus de paquets qu'avant, chacun
  plus petit. C'est du contenu, pas du code : le cout est du cote de Camille et Nadia, et il est
  reversible.
- Impossible de proposer, dans le meme jeu, une montee CP vers CM1 par simple reglage. Si une story
  le demande, la reponse sera une sequence de paquets decrite dans le catalogue, pas un curseur
  numerique dans le domaine.
- Le contenu de francais n'a plus rien de factice a remplir. C'est la mesure directe du succes de
  cet ADR : un paquet de vocabulaire anglais ne declare plus pratiquer l'addition.
- Mauvaise consequence assumee : la regle "que du texte" interdit aussi un champ legitime comme
  `int nombreDeQuestions`. C'est voulu : ce nombre est un parametre de partie, il appartient a
  l'entree de catalogue ou a la mecanique, pas au contenu. Si cette contrainte devient penible sur
  un cas reel et non arithmetique, elle sera revue par un ADR.

## Alternatives considerees

- **Generaliser en `Map<String, String> parametres` dans le contrat.** Zero typage, zero test
  possible, et le contenu de francais continue de remplir des cles qui ne le concernent pas, sans
  meme que le compilateur le montre. Ecarte : ca deplace le mensonge, ca ne le supprime pas.
- **Typer par matiere : `sealed interface Difficulte permits DifficulteArithmetique,
  DifficulteLexicale, ...`.** Le typage est honnete, mais ajouter une matiere devient une
  modification de code Java, ce qui contredit frontalement le principe directeur du produit
  ("couvrir toutes les matieres sans coder par matiere"). Ecarte, et c'est l'alternative la plus
  serieuse : elle redeviendrait pertinente si l'on constatait que plusieurs mecaniques ont besoin
  de reglages numeriques riches.
- **Garder `DifficultyConfig` avec des valeurs par defaut pour les matieres non arithmetiques.**
  C'est la version precedente avec du sucre. Ecarte.
- **Un enum `Niveau { CP, CM1 }` pour la securite de typage.** Le gain est faible (le niveau n'est
  jamais utilise pour brancher du code, ADR-0004 l'interdit meme aux mecaniques) et le cout est
  une modification de code par niveau ajoute. Ecarte.

## Ce qui me ferait changer d'avis

- Deux mecaniques ou plus qui auraient reellement besoin de parametres numeriques de partie : je
  les porterais dans l'entree de catalogue (donnee), et je verrais alors s'il faut les typer.
- Une demande pedagogique de progression continue a l'interieur d'un meme jeu : la reponse serait
  une progression pilotee par les reussites, dans la mecanique, jamais un curseur dans le contrat
  de contenu.
- Un troisieme niveau (CE1, CM2) qui multiplierait les paquets au point de rendre le catalogue
  illisible : ce serait un probleme d'organisation du contenu, traite dans ADR-0007, pas un retour
  du type de difficulte.
