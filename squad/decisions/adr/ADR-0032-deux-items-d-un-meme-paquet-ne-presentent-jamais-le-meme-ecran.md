# ADR-0032 - Refuser que deux items d'un meme paquet fini presentent le meme ecran, et fermer ainsi le trou de morsure d'ADR-0023

- **Statut** : Accepte
- **Date** : 2026-08-12
- **Auteur** : theo-archi
- **Sprint** : s09 (planning)

## Contexte

Le sprint 9 remet le cap sur les mathematiques CP, domaine "Nombres et calculs", en contenu pur :
des paquets YAML portes par la mecanique bandit manchot, sans mecanique neuve. C'est en preparant
l'avis de faisabilite de ces six competences que j'ai trouve, non pas un defaut livre, mais un
**trou de morsure dans une regle acceptee**.

`ADR-0023` exige qu'un jeu du catalogue offre **strictement plus d'items que sa partie n'a de
manches**, et son garde-fou `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` compte des
**`ItemId` distincts**, tires par la chaine reelle. Or `QuestionContentSourceFactory` derive
l'identifiant d'un item de sa **position** dans le YAML (`item-0`, `item-1`, ...). Deux items
rigoureusement identiques portent donc deux identifiants distincts et **comptent pour deux**.

Consequence exacte : un paquet trop court pour sa partie peut etre verdi en le remplissant de
copies. La regle passe, la propriete pour laquelle elle existe - la variete d'une partie a l'autre -
n'est pas livree. C'est la forme meme du defaut que ma propre charte nomme : une regle qui passe
sans rien prouver.

**Ce que ce trou couterait, et ce n'est pas une preference de forme.** `nadia-prof` a verifie a la
main, en jouant, qu'une partie ne repete jamais un item : `squad/conformite/FICHE-bandit-manchot-additions-cp.md`,
section "0 bis" point C - vingt-quatre parties jouees sans jamais reussir ni jamais rater, aucune
repetition, apres qu'elle a failli conclure a tort a un tirage avec remise. Cette propriete ne tient
que parce que `ListContentSource.next` ecarte les `ItemId` deja tires. Elle devient fausse, en
silence, des que deux items d'un meme paquet portent le meme ecran : le tirage reste sans remise sur
les identifiants et redevient avec remise sur ce que l'enfant lit.

**Le fait qui rend ce trou pressant maintenant, avec son calcul.** Les quatre entrees de
`catalogue.yaml` declarent toutes `nombreDeManches: 10`, donc `ADR-0023` exige au moins onze items
distincts par jeu. Les competences de ce sprint n'ont pas toutes un espace d'items confortable :

| Competence visee | Items de la forme canonique | Methode du compte |
|---|---|---|
| Complements a 10 | **11**, ou **9** sans les cas degeneres | `a + ? = 10` pour `a` de 0 a 10 ; les cas degeneres sont `a = 0` et `a = 10`, que le generateur d'additions ecarte deja en grande partie (`ACCEPTATION_OPERANDE_NUL_SUR`) |
| Soustractions dans la limite de 10 | **66**, ou **45** sans les cas degeneres | couples `(a, b)` avec `b <= a <= 10`, soit la somme de `a + 1` pour `a` de 0 a 10 ; degeneres = `b = 0` (11 cas) et `b = a` (11 cas), moins leur intersection |
| Comparer des nombres jusqu'a 100 | plusieurs milliers | couples ordonnes de deux nombres de 0 a 100 |

**Onze items exiges pour un espace de onze, ou de neuf.** L'auteur du paquet "Complements a 10" est
donc mis, par une regle du depot, exactement sous la pression que cet `ADR` refuse : completer par
une copie plutot que par une forme d'item de plus. Aucun auteur ne l'a fait ; c'est une pression
calculee, pas un defaut constate, et c'est la meme justification qu'`ADR-0029` (une cinquieme colonne
n'avait jamais existe non plus).

**Ce qu'aucun garde-fou ne voyait.** `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` compte des
identifiants, pas des ecrans. `ChaquePaquetPorteUnIndiceTest` juge chaque item seul, jamais deux items
l'un contre l'autre. `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` confronte bien deux items,
mais sur un motif de conjugaison francaise. `PaquetsFrancaisS04Test` nomme ses paquets dans ses
titres, donc un paquet neuf en sort en silence.

## Decision

**Deux items d'un meme paquet fini ne presentent jamais le meme ecran.**

L'identite retenue est le couple **enonce + jeu de propositions**.

- **En ensemble et non en liste** : la mecanique melange l'ordre des propositions a chaque manche
  (`BanditManchotSession.melanger`), donc deux items qui ne differeraient que par l'ordre de leurs
  distracteurs sont le meme ecran pour l'enfant. C'est la copie la plus facile a ecrire, et la clause
  la refuse.
- **Le jeu de propositions entre dans l'identite, l'enonce seul ne suffit pas** : deux items de meme
  enonce et de propositions differentes sont deux ecrans differents, et cette forme est legitime -
  "Quel nombre est le plus grand ?" pose sur deux couples de nombres est precisement ce que la
  competence "Comparer et ranger des nombres jusqu'a 100" appelle. Un critere sur le seul enonce
  l'aurait refusee, et aurait fait de ce garde-fou un obstacle a du contenu que `nadia-prof` peut
  parfaitement valider.
- **L'indice n'entre pas dans l'identite** : il ne s'affiche qu'apres trois echecs consecutifs
  (`SEUIL_ECHECS_CONSECUTIFS`), il ne distingue donc pas deux manches jouees.

### Ce que cette decision ne garde pas, et le dit

**Les paquets combinatoires sont hors perimetre, et c'est mesure, pas concede.** Un generateur peut
legitimement rendre deux fois le meme couple des que son espace est presque epuise : le tirage
d'`AdditionsBorneesContentSource` retente vingt fois d'eviter un item deja vu, puis le rend quand
meme. Ce comportement est licite au-dela de la longueur d'une partie, et il est teste la ou
l'algorithme vit (`AdditionsBorneesContentSourceTest`). J'ai verifie que l'exclusion n'est pas une
commodite : en supprimant le depart entre source finie et source combinatoire, **la clause de
production devient rouge sur le paquet d'additions reellement servi** (mutation 2 ci-dessous, avec
son compte). L'exclusion est donc necessaire ; elle est declaree, et un depot qui ne servirait plus
que des paquets combinatoires ferait echouer la clause de non-vacuite au lieu d'etre vert sans rien
examiner.

**La proximite de deux items.** Deux items differents mais quasi identiques ("7 + 3" et "3 + 7", ou
"7 + ? = 10" et "10 = 7 + ?") passent cette clause. Juger qu'un paquet n'offre pas assez de variete
**pedagogique** est le travail de `nadia-prof`, et je refuse d'ecrire un automate de ressemblance :
il aurait un seuil que personne n'a mesure, et il rougirait sur du contenu qu'elle peut valider.
Cette clause refuse l'identite, pas la ressemblance, et c'est tout ce que je sais garder.

**Le nombre de manches.** La sortie propre d'un paquet trop court pour sa partie est de faire varier
l'item ou de **revoir `nombreDeManches`** dans l'entree de catalogue. C'est une decision produit
(`ADR-0012` : la longueur de partie vit dans le catalogue), routee a `camille-po` ; ce garde-fou ne
la prend pas a sa place, il refuse seulement la troisieme sortie, la copie.

## Regle executable

Ce n'est **pas** une regle ArchUnit, et le motif est le meme que pour les cinq `ADR` precedents :
ArchUnit voit des types et des dependances, pas le contenu d'un fichier YAML charge a l'execution. Le
mecanisme automatique qui garde cette decision est un test JUnit de balayage, execute par
`mvn -B verify` dans le module qui possede la chaine de chargement. Le compte de regles ArchUnit du
depot est donc **inchange**.

**Fichier** : `adapters/adapter-content/src/test/java/com/recreo/adapter/content/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest.java`

Coeur de la clause, tel qu'il est ecrit :

```java
Map<Ecran, String> premierPorteur = new HashMap<>();
for (Question item : items) {
    String precedent = premierPorteur.putIfAbsent(ecranDe(item), item.id().value());
    if (precedent != null) {
        defauts.add("paquet " + paquet + ", item " + item.id().value() + " : il presente le"
                + " meme ecran que l'item " + precedent + " ...");
    }
}
```

avec l'identite d'ecran, qui est l'endroit ou la clause se joue :

```java
private record Ecran(String enonce, Set<String> propositions) { }

private static Ecran ecranDe(Question question) {
    Set<String> propositions = new HashSet<>();
    propositions.add(question.bonneReponse());
    propositions.addAll(question.distracteurs());
    return new Ecran(question.enonce(), Set.copyOf(propositions));
}
```

### Les trois obligations d'ADR-0013

- **Perimetre derive** : les paquets viennent du balayage de `contenu/` sous les ressources de
  production du module, jamais d'une liste ecrite dans le test ; les items viennent du **chargeur
  reel** (`YamlContentPackLoader.chargerPaquet`, puis `ContentSource.next` avec le tirage deja-vu qui
  grossit), donc de la chaine que l'enfant traverse - et l'identifiant positionnel qui ouvre ce trou
  n'existe qu'a l'interieur d'elle.
- **Derivation totale** : un paquet qui ne rend aucun item est un defaut, pas une population vide
  toleree. Le depart entre source finie et source combinatoire n'est **pas lu dans une cle du YAML**
  mais constate : une source finie s'epuise et rend `Optional.empty()` avant le budget de tirages.
  Une troisieme forme de source, le jour ou elle arrivera, sera donc classee par son comportement et
  non par un nom.
- **Mise a l'epreuve** : cinq cas, dont deux qui doivent rester **verts**. Trois paquets fautifs
  neufs sous
  `adapters/adapter-content/src/test/resources/contreexemple/AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest/` :
  une copie exacte (rouge), une permutation de propositions (rouge), un perimetre sans aucune source
  finie (rouge sur la clause de non-vacuite). Puis le corrige de la copie (vert) et **le meme enonce
  avec des propositions differentes (vert)** - sans ce dernier, la clause serait une interdiction de
  reposer une question deguisee en clause de non-repetition, et aucun jeu fautif ne le dirait. C'est
  la lecon de la seconde mutation d'`ADR-0025`.

### Preuve de morsure par trois mutations reelles, chacune relancee

| Mutation | Resultat attendu | Resultat mesure |
|---|---|---|
| identite d'ecran en **liste ordonnee** au lieu d'un ensemble | la copie par permutation passe | **1 cas en echec** : `unePermutationDesPropositionsNeSuffitPasAFaireUnItemDeplusFaitEchouerLeGardeFou` |
| **suppression du depart** entre source finie et source combinatoire (tous les paquets juges) | le generateur rougit alors qu'il est licite | **2 cas en echec**, dont `aucunItemDeProductionNeRepeteLEcranDUnAutreItemDeSonPaquet` : le paquet d'additions **reellement servi** rend plusieurs fois le meme couple sur un budget de 500 tirages. C'est le chiffre qui compte le plus : il etablit que l'exclusion des sources combinatoires est necessaire, et non une commodite pour verdir |
| suppression de la clause de **non-vacuite** sur les paquets finis juges | un perimetre sans aucun paquet fini passe en silence | **1 cas en echec** : `unPerimetreSansAucuneSourceFinieFaitEchouerLaClauseDeNonVacuite` |

Mesures faites sur JDK 21.0.10 (`JAVA_HOME` sur un JDK 21 ; le `java` du `PATH` de ce poste est un
JRE 8 qui ne compile pas), commande
`mvn -B -pl adapters/adapter-content test -Dtest=AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest -Dsurefire.failIfNoSpecifiedTests=false`.
Le fichier a ete restaure a l'identique apres chaque mutation, et la suite complete relancee ensuite.

### Ce que la clause trouve aujourd'hui, avec sa methode

**Aucun defaut**, et je le mesure plus largement que la clause elle-meme pour ne pas annoncer un vert
sans perimetre. Sur les **397 items lisibles des 65 paquets** du depot - les 4 paquets servis de
`adapters/adapter-content/src/main/resources/contenu/` et les 61 paquets de reserve de
`contenu-repris/` - aucun couple enonce + jeu de propositions n'apparait deux fois dans un meme
paquet, et **meme le critere plus strict de l'enonce seul est tenu partout**. Methode : lecture ligne
a ligne des cles `enonce`/`question`, `bonneReponse`/`bonne` et `distracteurs`, le decoupage des
listes en ligne respectant les guillemets ; les **429** cles `distracteurs` du depot sont **toutes**
ecrites en ligne, donc aucune n'echappe a ce comptage. Exclusion assumee : les paquets de reserve dont
la structure n'est pas un couple question/distracteurs (memory, puzzle, associer, frise) n'ont pas
d'items de cette forme et ne sont pas comptes dans les 397.

Cette clause part donc verte sur du **materiau reel**, jamais sur une fixture ecrite de ma main -
c'est la lecon de s05 que je m'applique.

## Consequences

**Bonnes.**

- `ADR-0023` retrouve la morsure que son intitule promet : un paquet trop court ne peut plus etre
  verdi par des copies.
- La propriete que `nadia-prof` a verifiee a la main - une partie ne repete jamais un item - cesse de
  dependre d'une coincidence entre identifiants positionnels et textes distincts. Elle est desormais
  gardee par `mvn verify`, ce qui la rend rejouable sans elle.
- Le message d'echec nomme les deux items, l'enonce en cause, et les deux sorties licites. Un auteur
  de contenu qui le lit sait quoi faire sans lire cet `ADR`.

**Mauvaises, et je les assume.**

- **Un garde-fou de plus a lire pour un auteur de contenu.** Le module en compte desormais six qui
  jugent les paquets servis. Le cout est reel et je ne le minimise pas ; il est paye une fois, a
  l'ecriture du paquet.
- **Le budget de 500 tirages par paquet est un cout de suite fixe**, paye sur chaque paquet
  combinatoire pour rien puisqu'il est ensuite exclu. Mesure : le fichier de test complet s'execute
  en 0,7 s environ. Si un jour ce cout devenait sensible, la sortie serait de plafonner le budget par
  la longueur de partie declaree au catalogue - je ne le fais pas aujourd'hui, l'abstraction n'aurait
  pas d'usage constate.
- **La clause ne dit rien de la ressemblance**, donc un paquet peut rester pauvre sans rougir. C'est
  volontaire, et c'est `nadia-prof` qui juge la variete pedagogique.

**Ce qui me ferait revenir sur cette decision.** Le jour ou `QuestionContentSourceFactory` cesserait
de deriver l'identifiant d'un item de sa position - par exemple en le faisant declarer dans le YAML,
ou en le derivant du texte de l'item - le trou de morsure d'`ADR-0023` se fermerait a la source, et
cette clause deviendrait une seconde barriere sur la meme propriete. Elle resterait juste, mais elle
ne serait plus necessaire, et il faudrait alors se demander si elle vaut son cout.

## Alternatives considerees

**1. Deriver l'`ItemId` du contenu de l'item plutot que de sa position.** Ecartee, et c'est
l'alternative la plus tentante parce qu'elle ferme le trou a la source : deux items identiques
porteraient alors le meme identifiant, et `ADR-0023` les compterait pour un. Trois raisons de ne pas
la retenir ce sprint. Elle touche du **code de production** dans un sprint que l'utilisatrice a
cadre en contenu pur. Elle change le comportement de la mecanique sans que personne l'ait demande :
deux items de meme identifiant se masqueraient l'un l'autre dans `ListContentSource` (le second ne
serait jamais tire), donc un paquet perdrait un item **en silence**, ce qui remplace un defaut visible
par un defaut muet. Et elle ne dirait rien de la permutation de propositions, qui produirait deux
identifiants distincts. Je la note comme la piste a instruire si la duplication devenait un defaut
recurrent, et non comme un renoncement definitif.

**2. Interdire a un paquet d'etre plus court que N items.** Ecartee : un seuil que personne n'a
mesure, et qui rate l'objet - un paquet de vingt items dont deux sont des copies resterait vert.

**3. Etendre `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` a compter des ecrans plutot que
des identifiants.** Ecartee, apres l'avoir sincerement envisagee. Elle economise un fichier de test,
mais elle melange deux proprietes dans une seule clause : la longueur d'un paquet face a sa partie
(qui n'a de sens que pour une entree de **catalogue**, d'ou vient `nombreDeManches`) et l'unicite des
ecrans (qui a du sens des que le paquet existe, catalogue ou pas). Les deux perimetres sont
differents et l'`ADR-0023` explique deja pourquoi le sien s'arrete au catalogue. Fondre les deux
aurait fait sortir du controle les paquets hors catalogue, qui y reviennent par deux lignes de YAML
(`DEC-0009`, `DEC-0014`, `DEC-0015`, `DEC-0018`).

**4. Un critere sur le seul enonce.** Ecartee par le contenu que ce sprint prepare : "Quel nombre est
le plus grand ?" pose sur onze couples de nombres est une forme legitime de la competence "Comparer et
ranger des nombres jusqu'a 100", et ce critere l'aurait refusee. Verifie plutot que suppose : sur les
397 items reels du depot, le critere de l'enonce seul serait vert aujourd'hui - il n'aurait donc rien
attrape de plus, et aurait seulement interdit une forme a venir.
