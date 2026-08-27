# Correctif de la porte technique rouge apres la fusion complete de s21 - le troisieme appelant

- **Auteur** : `theo-archi`. **Date** : 2026-08-27. **Branche** : `s21-theo-2`, worktree neuf reparti
  de `round-two` a l'etat fusionne (`9ace409`).
- **La faute est la mienne.** Mon lot `S21-12` a rendu strict `valeurDeclaree`, le point de passage
  par lequel tout garde-fou de `adapter-web` lit une valeur de feuille, et **n'a converti que deux
  de ses trois appelants**. Le troisieme casse des que `.proposition` porte deux declarations de
  rembourrage - ce qu'apporte exactement le lot `S21-2`.
- **Convention de chiffres tenue dans tout le document** : chaque chiffre porte sa methode, son
  perimetre et sa date dans la meme phrase.

## 1. Le rouge, reproduit avant d'etre corrige

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` sur `9ace409`, le 2026-08-27,
worktree `s21-theo-2` : **2 echecs sur 332**, tous deux avec la **meme trace**, a un cadre pres :

```
java.lang.AssertionError: ce corps declare padding 2 fois (0.75rem 1.25rem | 0.5rem 1.25rem)
et la lecture porte sur la declaration de rang 1, que la derniere ecrase [...]
  at TailleDesTextesEtBoutonsTest.valeurDeclaree(TailleDesTextesEtBoutonsTest.java:366)
  at LeConteneurTierGardeSonRembourrageLateralTest.lateralDeclare(...java:148)
```

Un seul defaut, **une seule ligne**, deux points d'entree :
`LeConteneurTierGardeSonRembourrageLateralTest.leConteneurTierGardeSonRembourrageLateralSurLaProduction`
et `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest.unRembourrageLateralAjouteSurLaChaineFaitEchouerLeGardeFou`,
qui appelle le meme verdict.

**Le refus a fonctionne exactement comme il est ecrit** : il a refuse de rendre une valeur ecrasee
plutot que de rendre un chiffre plausible. Ce qui a manque n'est pas la garde, c'est que j'aie
converti l'appelant avant de livrer.

## 2. Lecteur ou attente ? La question posee, et la reponse avec la commande qui l'etablit

**C'est un defaut de lecteur, pas d'attente.** `lateralDeclare` lisait la **premiere** declaration
de `padding` au lieu de celle qui gagne la cascade. Sur le materiau reel, les deux declarations
portent la **meme** valeur laterale - `0.75rem 1.25rem` puis `0.5rem 1.25rem`, donc `1.25rem` de
chaque cote dans les deux cas : le chiffre que ce garde-fou compare **ne change pas**. Seule change
la declaration d'ou il le tire.

**La commande qui le prouve, plutot que ma parole** : le diff de ce lot, filtre sur les lignes
supprimees qui ne sont ni un appel au lecteur, ni un import, ni un commentaire, ne rend que le corps
des deux lecteurs locaux supprimes et l'ancien refus positionnel.

```
git diff -U0 -- adapters | grep "^-" | grep -v "^---" | grep -viE \
  "valeurDeclaree|valeurALaPosition|valeurBrute|debutDeDeclaration|declarationsDe|derniereDeclaration"
```

**Aucune valeur attendue, aucun message d'assertion, aucune table epinglee d'aucun garde-fou n'a
change** : `LaLargeurDemandeeParUneRangeeEstEpingleeTest`, qui porte la table d'`ADR-0055`,
n'apparait meme pas dans le diff.

## 3. Ce que ce defaut apprend sur ma propre methode, et qui vaut au-dela de cette ligne

J'ai recense les lecteurs fautifs **par l'execution de la suite** plutot que par une liste de types,
en ecrivant qu'un perimetre enumere par types *"echoue par inclusion autant que par omission"*. Ce
recensement a rendu 6 tests sur 8 dans une classe, 7 sur 11 dans une autre, zero dans les sept
restantes. **Le troisieme appelant n'y figurait pas, et il ne pouvait pas y figurer** : il ne casse
que lorsque `.proposition` porte deux declarations de rembourrage, et ce materiau n'existait sur
**aucun** des arbres que j'ai mesures. Ni `s21-theo`, qui portait mon lecteur strict sans le
materiau ; ni `round-two` d'avant fusion, qui portait le materiau sans mon lecteur. **Il n'existait
que sur l'etat fusionne**, c'est-a-dire sur un arbre qu'aucun de nous deux ne mesurait.

**La lecon, et elle est plus generale que cette ligne** : un recensement par execution ne voit que
ce que le materiau de son arbre revele. Deux lots verts separement peuvent porter, l'un le lecteur
strict, l'autre le materiau qui le declenche, et **aucun des deux ne voit le defaut**. C'est la meme
famille que le `-pl` sans `-am` de `CLAUDE.md` - un chiffre juste, mesure sur un autre arbre que
celui qu'on croit mesurer - et je ne l'avais pas reconnue sous cette forme-ci. **Ce que j'aurais du
faire** : quand un lot durcit un lecteur **partage**, mesurer sur l'union des arbres qui vivent dans
le sprint, pas sur le mien seul. Route a `sofia-sm` pour la retro ; je ne redige pas la retro.

## 4. Le correctif : supprimer la piece qu'un appelant pouvait se tromper a calculer

Convertir le troisieme appelant aurait ferme ce rouge-ci et **laisse la classe de defaut ouverte** :
un quatrieme appelant pouvait naitre demain, vert sur sa branche et rouge a la fusion. J'ai donc
retire la piece elle-meme.

**`valeurDeclaree(corps, position, propriete)` devient `valeurDeclaree(corps, propriete)`** et
calcule la position gagnante lui-meme. La position etait une information **redondante** -
l'appelant passait deja le corps et la propriete - et elle etait la **seule** piece qu'un appelant
pouvait calculer de travers. Avec elle disparait la possibilite d'ecrire l'erreur.

**Ce que ce changement coute, et c'est son interet** : le compilateur nomme les **26 appels** a
convertir, dans **12 classes**, au lieu de laisser un recensement les chercher. Un appelant oublie
n'est plus un rouge de CI a la fusion, c'est une erreur de compilation sur la branche qui l'ecrit.

**Un lecteur explicite reste disponible pour le cas legitime** : `valeurALaPosition(corps, position,
propriete)` rend la valeur ecrite a une position precise, **y compris ecrasee**. Il sert a
inventorier ce que l'auteur d'une feuille a ecrit - une triplette de repli de prefixes doit
apparaitre en entier - jamais a comparer des valeurs appliquees. **Son nom est ce qui empeche de
l'employer par distraction.** Deux copies locales de ce lecteur, ecrites dans mon lot precedent,
sont supprimees au profit de celle-ci : deux copies d'une meme lecture sont deux endroits ou se
tromper.

**Un refus disparait, l'autre reste.** Le refus "cette position est ecrasee" n'a plus d'objet - la
position n'existe plus. Le refus **`!important`** reste : quand une propriete est declaree plusieurs
fois et que l'une porte `!important`, le rang ne decide plus de la cascade, ce lecteur ne sait pas
la resoudre, et il ne rend aucune valeur plutot qu'un chiffre sans methode.

**Un defaut voisin corrige dans le meme geste.** `declareEnImportant` demandait *"cette propriete
est-elle posee en !important"* et ne regardait que la **premiere** declaration. Or un `!important`
anterieur **bat** une declaration ordinaire posterieure : lire la seule gagnante manquerait
exactement le cas que ce controle existe pour trouver, et la feuille tierce - la seule ou il
s'applique - est precisement celle qui declare une meme propriete plusieurs fois. Il scanne
desormais **toutes** les declarations.

## 5. Y a-t-il un quatrieme appelant ? La question est fermee, et pas par une liste

**Par construction** : aucun appelant ne peut plus passer une position a `valeurDeclaree`, donc
aucun ne peut plus lire une declaration ecrasee par distraction. Les 26 appels sont convertis, et le
compilateur l'atteste - `test-compile` vert sur `-pl adapters/adapter-web -am`, le 2026-08-27.

**Par execution, sur le seul arbre ou le materiau revelateur existe** : la suite complete de
`adapter-web` sur l'etat fusionne rend **`Tests run: 332, Failures: 0`**, le 2026-08-27. Que le
materiau soit bien present est verifie plutot que suppose : lecture des blocs fusionnes de
`recreo.css` servi, `.proposition` rend deux declarations de rembourrage, `padding: 0.75rem 1.25rem`
puis `padding: 0.5rem 1.25rem`.

**Une dette nommee plutot qu'un correctif anticipe.**
`bootstrap/src/test/java/com/recreo/bootstrap/rendu/ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest`
porte son **propre** lecteur prive, independant de celui-ci, qui lit lui aussi la premiere
occurrence - et par sous-chaine, sans meme exiger un debut de declaration. Il lit le corps d'une
regle unique pour `animation-delay`, aucune regle du depot ne declare cette propriete deux fois, et
il est vert. **Je ne le corrige pas** : une abstraction partagee a travers deux modules de test se
justifie sur un usage reel constate, jamais sur un usage anticipe. **Son declencheur, ecrit pour
qu'on n'ait pas a le redecouvrir** : le jour ou une regle du depot declare deux fois une propriete
que ce garde-fou lit, ou le jour ou un troisieme module se met a lire des feuilles, ce lecteur
rejoint le point de passage commun.

## 6. Ce que la porte technique rend sur l'etat fusionne complet

```
bash outils/mvn-jdk21.sh -B clean verify
```

A la racine du reacteur (six modules, **aucun `-pl`, donc aucune question de `-am`**), le
2026-08-27, worktree `s21-theo-2` sur `round-two` fusionne plus ce correctif.
**`BUILD SUCCESS`, code de sortie 0, `Total time: 01:16 min`.** Blocs `Results:` releves **module par
module dans le journal de cette commande**, jamais deduits d'un total :

| Module | Bloc `Results:` |
|---|---|
| `recreo-domain` | `Tests run: 49, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-application` | `Tests run: 13, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-web` | `Tests run: 332, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-adapter-content` | `Tests run: 224, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (surefire) | `Tests run: 98, Failures: 0, Errors: 0, Skipped: 0` |
| `recreo-bootstrap` (failsafe) | `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` |
| **total, somme des six blocs** | **722** |

**C'est le premier total mesure du sprint entier**, et il n'existait pas avant ce correctif : le
reacteur s'arretait sur `adapter-web`, donc les trois paquets neufs de `marc-dev`, `lea-dev` et
`sofia-sm` n'avaient jamais ete mesures ensemble. `recreo-adapter-content` passe de **205** (mon lot
sur `s21-theo`, meme commande, meme methode, le 2026-08-27) a **224**.

**Machine virtuelle des tests** : la ligne `Found matching toolchain for type jdk` du journal de
cette execution designe un **JDK 21.0.10**, et c'est cette ligne qui l'etablit - jamais la ligne de
diagnostic de l'enveloppe, dont `S21-6` a etabli qu'elle nomme un autre fichier de toolchains que
celui que Maven lit.

## 7. Ce que ce lot ne fait pas

- **Aucune valeur de feuille n'est touchee.** Le rembourrage de `.proposition` et celui de la sortie
  sont ceux que `marc-dev` et `ines-design` ont fusionnes ; je constate seulement que le garde-fou
  les lit desormais correctement. Sur l'arbre fusionne, `.proposition` rend `0.5rem 1.25rem` et
  `.lien-accueil` comme `.retour-accueil` rendent `0.5rem 1.1rem` : **l'action ne declare pas moins
  que la sortie**, `ADR-0017` tient - et pour la premiere fois le garde-fou le dit **en ayant lu la
  bonne declaration**.
- **Aucun `ADR` n'est ecrit.** Ce lot ne prend aucune decision d'architecture neuve : il retire une
  piece d'API qui permettait d'exprimer une erreur. `ADR-0017` et `ADR-0013` sont inchanges, et le
  compte de regles ArchUnit du depot ne bouge pas.
- **Aucun Done, ni technique ni pedagogique, et aucun statut de couverture.** La porte technique est
  jugee par la CI sur l'arbre que `sofia-sm` fusionne ; le Done pedagogique appartient a
  `nadia-prof` seule.
- **Je ne redige pas la retro.** La lecon de methode de la section 3 lui est routee.
