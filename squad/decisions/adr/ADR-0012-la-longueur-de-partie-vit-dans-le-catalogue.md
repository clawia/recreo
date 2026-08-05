# ADR-0012 - Porter la longueur d'une partie par l'entree de catalogue, jamais par le contenu

- **Statut** : Accepte
- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Sprint** : s01

## Contexte

Story S1-4 : une partie doit se terminer automatiquement apres un nombre fixe et annonce de
manches. Reserve 1 de `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : `nadia-prof` a
enchaine 41 manches sans que rien ne s'arrete, et la Javadoc d'`AdditionsBorneesContentSource.next`
le confirme - ce tirage combinatoire ne renvoie jamais `Optional.empty()`, donc
`BanditManchotSession` ne passe jamais en `SessionStatus.TERMINEE` par epuisement du contenu.

ADR-0006 a deja pose la question et l'a laissee ouverte deliberement, en nommant le cas exact :

> la regle "que du texte" interdit aussi un champ legitime comme `int nombreDeQuestions`. C'est
> voulu : ce nombre est un parametre de partie, il appartient a l'entree de catalogue ou a la
> mecanique, pas au contenu. Si cette contrainte devient penible sur un cas reel et non
> arithmetique, elle sera revue par un ADR.

C'est ce cas reel. Il ne remet en cause aucune regle existante : il choisit entre les deux
emplacements qu'ADR-0006 avait deja identifies.

## Decision

**Le nombre de manches d'une partie est une donnee de l'entree de catalogue, transmise a la
mecanique au demarrage. Il ne figure ni dans `ContentDescriptor`, ni dans `Question`, ni dans
aucun type de `com.recreo.domain.content`.**

Raisons de choisir le catalogue plutot qu'une constante fixee dans la mecanique :

- une longueur de partie est un attribut de **ce jeu-la** (cette association mecanique + paquet),
  pas de la mecanique en general. Deux entrees de catalogue qui rejouent `bandit-manchot` avec des
  paquets differents pourront demain avoir des longueurs differentes sans toucher a une ligne de
  Java, exactement la promesse d'ADR-0007 ("ajouter quelque chose = un fichier YAML") ;
- ce n'est toujours pas un reglage de matiere : n'importe quelle mecanique, sur n'importe quel
  contenu, peut avoir une longueur de partie. Le placer dans le catalogue ne cree donc pas
  d'exception a ADR-0004.

Trois changements, dans le meme commit :

1. **`GameDefinition` (`com.recreo.application.catalog`) gagne un champ `int nombreDeManches`.**
   L'application reste pure JDK + domaine (ADR-0002) : un `int` ne l'enfreint pas.
2. **Le schema de catalogue (ADR-0007) gagne un champ obligatoire `nombreDeManches` par entree de
   `jeux`.** Comme tout champ du schema, son absence fait echouer le demarrage avec un message
   nommant le fichier et le champ (ADR-0007, "echec au demarrage, jamais de valeur par defaut
   silencieuse") - pas de longueur par defaut choisie en silence.
3. **`GameMechanic.start` gagne un parametre : `GameSession start(ContentSource contenu,
   RandomSource hasard, int nombreDeManches)`.** Le contrat de mecanique reste ferme (interfaces,
   records, enums uniquement, ADR-0004) : c'est une interface qui gagne un parametre de methode,
   pas une classe concrete. `BanditManchotSession` compte les manches jouees (independamment de ce
   que rend `ContentSource`) et passe en `TERMINEE` des que `nombreDeManches` est atteint, que le
   contenu soit epuise ou non. Le "nombre annonce" du critere d'acceptation est un affichage tire de
   cette meme donnee, porte par la vue (`adapter-web`), jamais par la mecanique ou le contenu.

## Regle executable ArchUnit

**Le versant "interdiction" est deja garde, sans modification.** `DifficulteTest.leContratDeContenuNePorteQueDuTexte`
(ADR-0006) refuse deja tout champ non textuel dans `com.recreo.domain.content` : un `int
nombreDeManches` ajoute par erreur a `Question` ou a `ContentDescriptor` y echouerait exactement
comme `ReglageArithmetiqueContreExemple` aujourd'hui, pour le meme motif (le type brut du champ
n'est ni `String`, ni `java.util..`, ni le paquet du contrat). Je n'ecris pas de nouvelle regle
pour cette moitie de la decision : elle existerait deja et mordrait deja.

Ce que je recommande d'ajouter au tableau de contre-exemples d'ADR-0006, parce que c'est desormais
un cas reel et non seulement synthetique : un contre-exemple
`ReglageDeLongueurDePartieContreExemple` (record, `int nombreDeManches`), avec son test de
non-vacuite dans `LesReglesMordentTest`. Ce n'est pas une nouvelle regle, c'est une preuve
supplementaire que la regle existante mord sur le cas precis que cette story a fait surgir.

> **Amendement du 2026-08-05 (cloture de s01).** Deux corrections a ce paragraphe, faites en
> l'appliquant.
>
> 1. **Erreur de paquet, corrigee.** La premiere redaction rangeait ce contre-exemple dans
>    `domain.content.contreexemple`. C'est faux : `leContratDeContenuNePorteQueDuTexte` porte sur
>    `resideInAPackage("com.recreo.domain.content")`, le paquet exact du contrat et non ses
>    sous-paquets. Un contre-exemple range dans le sous-paquet aurait ete invisible pour la regle, et
>    son test de non-vacuite aurait echoue sur `failed to check any classes` au lieu de la violation
>    attendue - c'est-a-dire qu'il aurait ete rouge pour le mauvais motif. Il vit donc dans
>    `bootstrap/src/test/java/com/recreo/domain/content/`, comme
>    `ReglageArithmetiqueContreExemple` et `ReglageEnCollectionContreExemple`.
> 2. **La recommandation est appliquee, plus recommandee.** Fichier
>    `bootstrap/src/test/java/com/recreo/domain/content/ReglageDeLongueurDePartieContreExemple.java`,
>    test de non-vacuite `LesReglesMordentTest.uneLongueurDePartieDansLeContratDeContenuEstRefusee`.
>    Sa valeur exacte, sans l'exagerer : il n'ajoute aucun pouvoir de refus a la regle, qui mordait
>    deja sur un `int` via `ReglageArithmetiqueContreExemple`. Ce qu'il ajoute est un point
>    d'accrochage nomme : si quelqu'un assouplit un jour cette regle au motif qu'un "parametre de
>    partie" merite un `int` dans le contrat de contenu, le test rouge citera ADR-0012 et pas
>    seulement la borne arithmetique d'ADR-0006. Laisser une recommandation ecrite et non appliquee
>    aurait ete, en miniature, la dette n°1 de `main`.

**Le versant "ou est-ce que ca vit et est-ce vraiment applique" n'est pas encodable en ArchUnit** :
ArchUnit voit des types et des dependances, pas qu'un compteur atteint une valeur au bon moment. Il
est garde par des tests de comportement, chacun deja rendu obligatoire par un ADR anterieur - je
n'invente donc aucun mecanisme nouveau, je nomme les tests que les ADR existants exigent deja :

| Ce qui doit etre vrai | Test qui le garde | Deja exige par |
|---|---|---|
| Apres `nombreDeManches` coups, la session passe en `TERMINEE`, meme si le contenu est combinatoire et infini | `BanditManchotSessionTest` (nouveau cas, dans la classe de test que la mecanique doit deja avoir) | ADR-0008, regle 1 (`ToutesLesMecaniquesSontTesteesTest`) |
| `PartieService.demarrer` transmet bien `GameDefinition.nombreDeManches()` a `GameMechanic.start` | `PartieServiceTest` (nouveau cas) | pratique etablie du module `application`, aucun test n'existait avant ce sprint - a creer dans le meme commit, pas une dispense |
| Une entree de catalogue sans `nombreDeManches` echoue au chargement avec un message nommant le champ | extension du test de schema d'`adapter-content` | ADR-0007, "echec au demarrage" |
| Le catalogue charge produit bien la valeur ecrite en YAML | extension de `CatalogueChargeableIT` | ADR-0007 |

> **Amendement du 2026-08-05 (cloture de s01), sur la derniere ligne du tableau.** Elle etait la
> seule des quatre a n'avoir pas ete honoree : `CatalogueChargeableIT` demarrait bien chaque jeu du
> vrai catalogue mais n'avait aucune assertion sur `nombreDeManches`. Le trou etait plus etroit qu'il
> n'y parait - un champ absent du vrai `catalogue.yaml` faisait deja echouer ce test, puisque le
> chargeur leve avant de rendre le moindre `GameDefinition` - mais une valeur de zero, elle, serait
> passee sans un mot et aurait produit une partie qui se termine au premier coup. L'assertion ajoutee
> est `isPositive()`, pas une egalite : **combien** de manches est un choix pedagogique qui
> n'appartient pas a l'architecte, alors qu'**au moins une** est une propriete de forme.

## Consequences

- `GameMechanic.start` change de signature : c'est un cout reel puisqu'il n'y a qu'une seule
  implementation aujourd'hui (`BanditManchot`), donc un seul appelant a corriger
  (`PartieService.demarrer`). Une deuxieme mecanique qui n'aurait besoin d'aucune limite de manches
  devra tout de meme accepter le parametre et pourra l'ignorer ou l'utiliser comme elle l'entend
  (par exemple en le laissant sans effet si son propre contenu est deja fini).
- Le catalogue devient legerement plus verbeux (un champ de plus par entree). C'est du contenu, pas
  du code, et c'est deja la nature du fichier depuis ADR-0007.
- Mauvaise consequence assumee : rien n'empeche aujourd'hui une mecanique d'ignorer completement le
  parametre `nombreDeManches` qu'on lui donne. Ce n'est pas garde par une regle - une mecanique qui
  boucle indefiniment malgre un `nombreDeManches` fourni ne casse aucun test d'architecture, elle
  casse le test de comportement nomme ci-dessus, qui reste sous la responsabilite de qui l'ecrit.

## Alternatives considerees

- **Fixer le nombre de manches en dur dans `BanditManchot` (une constante Java).** Rejete : cela
  reintroduit une modification de code pour un changement qui doit rester un fichier YAML
  (principe directeur du produit, ADR-0007), et cela empeche deux entrees de catalogue utilisant la
  meme mecanique d'avoir des longueurs differentes sans les distinguer par sous-classe.
- **Porter la limite dans `ContentSource` (par exemple un compteur interne qui s'epuise apres N
  tirages).** Rejete explicitement par ADR-0006, qui nomme `int nombreDeQuestions` comme
  l'exemple meme d'un champ a refuser dans le contrat de contenu : ce nombre n'est pas une propriete
  du contenu, deux jeux pourraient vouloir jouer le meme paquet avec des longueurs differentes.
- **Une propriete de session que l'application force de l'exterieur, sans toucher a `GameMechanic`
  (l'application observerait `Score.tentatives()` et deciderait seule de la fin).** Demanderait un
  type enveloppant `GameSession` pour lui faire mentir sur son propre `status()`, ce qui deplace
  "l'etat de session appartient a la mecanique" (ADR-0004) vers l'application sans le dire. Ecarte :
  plus de pieces mobiles pour le meme resultat.
- **Un `Map<String, Object> parametres` generique passe a `start`.** Deja ecarte en ADR-0006 pour le
  contenu, pour le meme motif ici : ca deplace le mensonge de typage au lieu de le supprimer.

## Ce qui me ferait changer d'avis

- Une deuxieme mecanique dont le seul parametre de partie pertinent ne serait pas un compte de
  manches (un budget de temps, par exemple) : je ne generaliserais pas `nombreDeManches` en un type
  plus large par anticipation ; j'ajouterais le parametre dont cette mecanique a reellement besoin,
  quitte a ce que `GameMechanic.start` porte plusieurs entiers nommes plutot qu'un seul objet
  fourre-tout.
- Si le nombre de parametres de partie portes par `start` depassait deux ou trois, ce serait le
  signe qu'il faut les regrouper dans un petit type dedie du contrat de mecanique (toujours un
  record, jamais une classe concrete) plutot que d'allonger indefiniment la signature.
