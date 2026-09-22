# ADR-0095 - Porter l'ordre des niveaux par une cle de tri declaree une seule fois, et refuser tout niveau servi que cette sequence ne nomme pas

- **Statut** : Accepte
- **Date** : 2026-09-21
- **Auteur** : theo-archi
- **Sprint** : s37

## Contexte

`DEC-0090` decide que l'ordre des niveaux affiches dans l'en-tete est **CP, CE1, CM1, CM2**, et
laisse explicitement le mecanisme a l'architecte, avec une seule contrainte de fond : le niveau
reste une donnee, `ADR-0006` n'est pas rouverte. `S36-ORDRE-NIVEAUX` est engagee a s37.

L'etat de depart, verifie et non suppose : `PartieService.niveauxDuCatalogue()` rend les valeurs
distinctes **dans l'ordre d'apparition au catalogue**, et c'est la specification ecrite par
`ADR-0044`. Avec deux niveaux (`CP`, `CM1`), cet ordre coincide avec la progression scolaire par
l'ordre des entrees de `catalogue.yaml`, un fichier edite pour des motifs qui n'ont rien a voir
avec l'affichage.

Le probleme n'est donc pas un defaut visible : c'est qu'une propriete que le produit veut n'est
ecrite nulle part et que rien ne la tient. Elle sera mise a l'epreuve des qu'un troisieme niveau
entrera - ce que `S37-1` fait au meme sprint.

**Le vrai risque n'est pas le tri, c'est ce qui le rend decoratif.** Une sequence ecrite a la main
est la seule piece du mecanisme qui puisse pourrir : le jour ou un paquet declare `CE2`, ou ecrit
`ce1` en minuscules, le tri ne rougit pas - il range ce niveau en dernier, silencieusement. Un
mecanisme d'ordre sans garde-fou de completude est un mecanisme qui garantit l'ordre des niveaux
qu'il connait deja, c'est-a-dire rien.

## Decision

**1. L'ordre est une cle de tri, portee par une sequence unique dans `application`.**
`com.recreo.application.catalog.OrdreDesNiveaux` declare `SEQUENCE = List.of("CP", "CE1", "CM1",
"CM2")` et rend le rang d'une chaine. `PartieService.niveauxDuCatalogue()` trie les valeurs
distinctes par ce rang. Le niveau reste la `String` que le paquet declare : aucun type neuf ne le
represente.

**2. Un niveau absent de la sequence recoit le rang le plus grand.** Il se range apres tous les
niveaux connus, et le tri de `List` etant stable, plusieurs inconnus conservent entre eux l'ordre
du catalogue. Ce n'est pas une tolerance : c'est un comportement deterministe pour un cas que la
decision 3 rend impossible sur le catalogue servi. Lever une exception a la place transformerait
une faute de frappe dans un YAML en page d'accueil en erreur, ce qu'`ADR-0011` et `ADR-0018`
traitent comme un defaut, pas comme une severite.

**3. Tout niveau servi par le catalogue est nomme dans la sequence, ou le build echoue.** C'est la
moitie de cette decision qui interdit quelque chose. Sans elle, la sequence est une liste ecrite a
la main dont le plafond depasse indefiniment la dette.

**4. Le blanc autour du niveau est rogne avant comparaison, la casse ne l'est pas.** Le blanc
final dans un descripteur ecrit a la main s'est deja produit dans ce depot et a casse une cle de
regroupement sans rien afficher (`ADR-0079`, trouve par `yanis-qa` le 2026-09-03) ; il est
invisible a la relecture. La casse, elle, se voit dans le YAML : un `"cp"` doit etre corrige dans
le paquet, pas absorbe ici, et la decision 3 le rend rouge.

**Ce que cette decision ne fait pas** : elle ne change pas l'ordre affiche aujourd'hui (`CP` puis
`CM1` dans les deux regimes), elle ne cree aucune puce pour un niveau sans contenu, elle ne
touche ni `ADR-0006` ni le contrat de contenu, et elle ne dit rien de l'apparence de l'en-tete.

## Regles executables

### 1. La regle ArchUnit qui ferme le chemin le moins cher, et le trou qu'`ADR-0006` laisse

Fichier :
`bootstrap/src/test/java/com/recreo/archi/LOrdreDesNiveauxResteUneDonneeTest.java`

```java
@ArchTest
static final ArchRule aucunNiveauNEstUneConstanteDEnumeration = classes()
        .that().resideInAnyPackage("com.recreo.domain..", "com.recreo.application..")
        .should(neJamaisEnumererUnNiveauDeLaSequence())
        .as("ADR-0095 : le niveau reste une donnee, jamais une constante d'enumeration");
```

La condition lit les noms de constantes de tout enum du perimetre et les compare, a la casse pres,
a `OrdreDesNiveaux.SEQUENCE`.

**Ce qu'elle interdit reellement, et pourquoi elle n'est pas un doublon d'`ADR-0006`.** Le chemin
le moins cher vers un ordre est `enum Niveau { CP, CE1, CM1, CM2 }` puis
`Comparator.comparing(Niveau::valueOf)` - c'est le tri en une ligne, et c'est exactement la
regression qu'`ADR-0006` interdit. On pourrait croire que
`DifficulteTest.leContratDeContenuNePorteQueDuTexte` la tient deja. **Elle ne la tient pas** : son
`estPermis` admet **tout type residant dans `com.recreo.domain.content`**, donc un enum pose dans
ce paquet et cite par le descripteur passe sans un mot. Ce n'est pas deduit de la lecture : les
deux contre-exemples `NiveauEnumereContreExemple` et `ContratAuNiveauEnumereContreExemple`, poses
dans `com.recreo.domain.content` parce que c'est la que le chemin le moins cher les poserait, sont
passes aux deux regles dans la meme assertion -
`LesReglesMordentTest.unNiveauEnumereDansLeContratEstRefuseEtLaRegleDAdr0006LeLaissePasser` :
la regle neuve rougit en nommant `NiveauEnumereContreExemple` et `CE1`, **celle d'`ADR-0006` reste
verte**. L'import est restreint a ces deux classes : sur le paquet entier, la regle d'`ADR-0006`
rougirait de toute facon sur les quatre autres contre-exemples et la mesure ne dirait rien.

**Son perimetre est derive, jamais ecrit dans le fichier** : la liste des noms interdits est lue
dans `OrdreDesNiveaux.SEQUENCE`. Un niveau ajoute a la sequence est garde le jour ou il y entre.

**Ce qu'elle ne voit pas, ecrit plutot que laisse decouvrir** : elle compare des noms de
constantes a des chaines. Un `enum Niveau { COURS_PREPARATOIRE, CE_UN }` passerait. C'est la forme
sous laquelle la regression se produirait reellement qui est gardee ; l'autre demanderait un
vocabulaire ecrit a la main, donc la liste qui pourrit que cet `ADR` existe pour eviter.

### 2. La regle ArchUnit d'existence - la dette 1 de `CLAUDE.md`

```java
@ArchTest
static final ArchRule laSequenceDesNiveauxExiste = classes()
        .that().haveFullyQualifiedName("com.recreo.application.catalog.OrdreDesNiveaux")
        .should().bePublic()
        .allowEmptyShould(false)
        .as("ADR-0095 : la sequence des niveaux existe dans le code, pas seulement dans la decision");
```

Une regle d'existence n'a pas de classe fautive : sa faute est une absence, et ce qui mord est
`allowEmptyShould(false)`. Morsure :
`LesReglesMordentTest.uneSequenceDesNiveauxQuiDisparaitEstRefusee`.

### 3. Le versant que le bytecode ne voit pas - la completude de la sequence

Fichier :
`adapters/adapter-content/src/test/java/com/recreo/adapter/content/ChaqueNiveauServiEstNommeDansLaSequenceTest.java`

**Nomme avant d'etre decouvert** : la decision 3 porte sur du YAML, pas sur des classes. ArchUnit
ne peut pas la tenir, et ce n'est pas un pis-aller - c'est la regle qui empeche les deux
precedentes d'etre decoratives.

La population est **derivee du catalogue servi** par le recensement existant de
`AucunTitreDeJeuNePorteSonNiveauTest.entreesServies`, reutilise plutot que recopie : deux
recensements du meme materiau divergent, et celui-ci a deja ete corrige une fois (rognage du blanc,
`ADR-0079`). Le juge est pur - il ne lit ni disque ni classpath - ce qui permet de le mordre sur
du materiau reel mute en memoire.

Cinq morsures : un niveau servi absent de la sequence (`CE2`), le meme dans une autre casse
(`ce1`), un paquet sans niveau, une population vide, et le **jeu fautif commite** sous
`src/test/resources/contreexemple/ChaqueNiveauServiEstNommeDansLaSequenceTest` - deux entrees, une
seule fautive, pour prouver que la regle discrimine et non qu'elle refuse tout. Cette derniere est
la seule qui traverse la lecture disque et la jointure catalogue/paquet ; les quatre autres mutent
la sortie du recensement.

### 4. Le comportement, teste par les criteres de la story

Fichier : `application/src/test/java/com/recreo/application/usecase/PartieServiceTest.java` -
catalogue declare a l'envers (`CM2, CM1, CE1, CP`) rendu dans l'ordre de la progression, ordre
inchange sur le catalogue d'aujourd'hui, niveau absent du catalogue absent de la liste, et le rang
de repli teste plutot que suppose. `EnTeteEtMenuTest` liait deja l'ordre rendu par l'en-tete a
celui que `niveauxDuCatalogue()` rend : un second tri ecrit dans l'adaptateur serait rouge sans
qu'une regle neuve soit necessaire.

### 5. Ce qui n'est garde par rien, et pourquoi

Que `CP, CE1, CM1, CM2` soit **le bon** ordre n'est pas gardable : c'est `DEC-0090`. Il est
epingle par un test (`laSequenceEstCelleQueDec0090Fixe`) pour qu'un reordonnancement coute un diff
visible et une decision produit, jamais un tri modifie en passant.

## Consequences

- Ajouter un niveau reste une donnee cote contenu, mais **cesse d'etre gratuit cote code** : un
  paquet d'un niveau neuf rend le build rouge tant que la sequence ne le nomme pas. C'est
  intentionnel - la place d'un niveau dans la progression est une decision, pas un effet de bord -
  et c'est un cout reel, paye des `S37-1` : le premier paquet CE1 n'a rien a payer (`CE1` est deja
  dans la sequence), un futur `CE2` ou `6e` le paiera.
- La sequence est une liste ecrite a la main. C'est la piece que je prefere ne pas avoir, et je
  n'ai pas trouve de source a en deriver : `squad/programme/` ne porte pas d'ordre de niveaux
  exploitable par le code, et le catalogue est precisement ce dont on ne veut pas dependre. **Ce
  qui me ferait changer d'avis** : le jour ou un artefact du depot porte la progression scolaire
  sous une forme lisible par un test, cette sequence en derive et la decision 3 disparait avec.
- `ADR-0044` n'est pas contredite mais sa clause *"dans l'ordre du catalogue"* cesse d'etre vraie.
  Elle recoit une note datee plutot qu'un amendement : la propriete qu'elle decide - le choix vit
  derriere le port entrant - est inchangee, c'est sa description d'un detail de rendu qui est
  perimee.
- Un troisieme test de recensement s'appuie desormais sur `entreesServies`. Si ce recensement
  change de forme, deux garde-fous cassent ensemble. C'est le prix assume de ne pas le recopier.

## Alternatives considerees

**1. Une cle de tri declaree dans le descripteur de chaque paquet (`ordre: 20`).** Ecartee. Elle
remet le meme ordinal dans plus de quarante fichiers editables independamment, donc elle peut
deriver par paquet ; et elle oblige chaque paquet a remplir un champ qui ne parle pas de son
contenu - la dette 2 de `CLAUDE.md`, sous un autre nom.

**2. Un enum `Niveau` dans le domaine, avec son ordre naturel.** Ecartee par `ADR-0006` et par
`DEC-0090` qui la nomme comme sa seule contrainte de fond. C'est aussi le contre-exemple de la
regle 1 : la solution la plus courte a ecrire est celle que le build refuse.

**3. Un comparateur dans `adapter-content` ou dans `adapter-web`.** Ecartee. `ADR-0044` place le
choix de ce que l'en-tete montre derriere le port entrant ; l'ordre de ce choix est de la meme
nature. Dans `adapter-web`, il serait en plus invisible au test qui compare le rendu a ce que le
port rend.

**4. Trier sans garde-fou de completude.** Ecartee, et c'est l'alternative qui compte : elle
produit exactement le tri demande, passe tous les criteres de la story, et ne garantit l'ordre que
des niveaux deja connus. C'est un garde-fou dont le plafond depasse la dette.
