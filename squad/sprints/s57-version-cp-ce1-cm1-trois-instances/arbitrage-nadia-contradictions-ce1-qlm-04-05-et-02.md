# s57 - arbitrage des contradictions de `-04`, `-05` et de l'article de `-02`, avant les premiers jets

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-27, debut 08:03:38 (UTC+01:00), lue par `date` ; fin en section 8.
- **Arbre** : branche `s57-p5-nadia-c0405`, base `round-two` a `fb689ee2`, lue par `git rev-parse`.
- **Type** : arbitrage avant premier jet. Reponses a `Q8-1` et `Q8-7` (`docs/backlog/backlog.md`, annotation du 2026-10-26,
  huitieme), aux conflits `C1` a `C5` et aux ajouts `X1` a `X5` de `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`
  (sections 2, 3, 4 et 7). **Aucun Done, aucun verdict de lot, aucun identifiant de condition neuf, aucune condition neuve.** Je ne
  modifie ni story, ni paquet, ni test : `camille-po` reporte la section 6, `theo-archi` decide les formes de la section 7.
- **Lu** : mon `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md` en entier ; l'avis de `theo-archi` en entier ; l'annotation
  huitieme ; ma lecture du premier jet de `-02` (sections 1 a 5) et ma relecture de ses corrections (sections 1, 5) ; `ETE-CE1-1` et
  `ETE-CE1-2` dans leurs mots (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 3.5) ; les lignes
  `bonneReponse`, `distracteurs` et `source` de `ce1-qlm-ce-que-mangent-les-animaux.yaml` a `fb689ee2`.
- **Audit degrade, dit** : aucun paquet `-04` ni `-05` n'existe ; je juge des regles, pas des items. Les comptes de mots et de
  familles de ce fichier sont faits a la main, marques **[calcul a la main]**.

## Resume

| Point | Reponse | En une ligne |
|---|---|---|
| `Q8-1`, `C1` (`-05`, *rien ne change*) | **oui, en distracteur de B, a la condition que ma regle 4 posait** | ma regle 4 tient ; ce sont mes regles 2 et 8 qui sont corrigees : *rien ne change* devient une bonne reponse admise de B, dans un item de glace seule garde sous 0 degre |
| `Q8-7` (`-04`, *la pomme*) | **liste fermee ; l'exemple est retire** | *la pomme* ne porte aucun mot d'un nom de famille ; mon exemple de 1.6 etait faux, la regle 6 est juste |
| `C2` | **confirme** | cinq familles, A au moins 5 |
| `C3` | **une assertion propre** | la deduction de `theo-archi` a un trou : un menu attendu qui contient des produits sucres laisse passer un distracteur tout sucre |
| `C4` | **oui, les deux extensions** | *solide* seul et *"Comment s'appelle ..."* sont des formes du CP ou de definition |
| `C5` (`-02`) | **je garde ma liste fermee** | *de petits insectes* et *d'autres insectes* sont refuses en tache aliment, voulu |
| Compte de `-02` | **`theo-archi` a raison : 18 propositions, toutes en *des*** | j'ai ecrit *six propositions* pour *six items* |
| `X1` | **confirme** | |
| `X2` | **confirme pour `-04` seul** ; **aucune correction de `-02`** | la regle 5 de `-04` exige une source hors du depot ; `-02` et `-05` ont une autre regle de source, ecrite et tenue |
| `X3` | **confirme** | |
| `X4` | **confirme** | |
| `X5` | **confirme** | au moins un item, pas plus |

---

## 1. `-05` : *rien ne change* (`Q8-1`, `C1`)

### 1.1 Le constat

`camille-po` et `theo-archi` ont raison, et la contradiction est de moi. Ma regle 4 (5.2) n'admet *rien ne change* en distracteur
que si un item l'a pour bonne reponse ; mes regles 2 et 8 ne lui donnent aucune place en bonne reponse ; mes deux exemples de 2.2
l'emploient en distracteur. Ensemble, mes trois textes interdisent mes deux exemples.

### 1.2 Ce que je tranche

**Oui, *rien ne change* est admis en distracteur de B, et ma regle 4 reste telle qu'elle est.** C'est la barre que j'ai ecrite le
2026-10-26 ; je ne la retire pas pour lever la contradiction. Ce qui se corrige, ce sont les deux regles qui fermaient la porte :
*rien ne change* devient une bonne reponse admise de B.

**Motif pedagogique.** Savoir que la glace ne fond pas sous 0 degre, c'est relier la fonte a la temperature, exactement le verbe de
la ligne (`ETE-CE1-1`, *"le lien entre un changement d'etat et la temperature"*). Un paquet ou la glace finit toujours par fondre
enseigne le contraire. Et cela ferme le defaut que j'avais nomme en 2.2 : si *rien ne change* n'est jamais juste, l'enfant l'ecarte
sans lire.

**Ou *rien ne change* est exact, et ou il ne l'est pas.**

- **Exact** : de la glace seule, gardee sous 0 degre, et l'enonce le dit. Elle reste de la glace.
- **Faux** : de l'eau liquide laissee a l'air au-dessus de 0 degre. Elle s'evapore, meme sans bouillir : c'est le contenu meme de
  `ETE-CE1-2` (*"le linge seche sans bouillir"*). Un item qui y mettrait *rien ne change* en bonne reponse enseignerait une erreur.
- **Exclu** : de la glace et de l'eau liquide ensemble. Il n'existe pas de temperature ou rien ne change que je puisse faire lire a un
  enfant de CE1 sans parler d'equilibre a 0 degre.

**Un exemple** (ecrit sans accents ici ; dans le paquet, avec ses accents, relus par le `relecteur-pedagogique`) :

| Bonne reponse | Enonce | Bonne reponse ; distracteurs |
|---|---|---|
| rien ne change | *"Un glacon est pose dehors. Toute la journee, il fait sous 0 degre. Que se passe-t-il ?"* | *rien ne change* ; *le glacon fond*, *le glacon disparait* |

17 ou 18 mots selon que *passe-t-il* compte pour un ou deux, trois phrases, la derniere est la question (`LON-CE1-1`, `-2`) ;
propositions de 3 mots ; la bonne reponse n'est pas la plus longue **[calcul a la main, a recompter par `PaquetLu.mots`]**. *Le glacon
disparait* est la conception de la disparition, celle que `cm1-sciences-etats-matiere` porte en distracteur ; elle ne s'ecarte pas
sans savoir ce que fait le froid. Le distracteur *l'eau gele* n'y est pas : il n'y a pas d'eau liquide, et ma regle 2 refuse un
changement que l'etat decrit rend impossible.

**Pourquoi la structure tient.** Cet item et les deux exemples de 2.2 ont la meme forme de propositions : deux changements et *rien
ne change*. Dans les uns *rien ne change* est faux, dans l'autre il est juste. Aucune lecture de la forme ne donne la reponse ; seule
la temperature la donne. C'est ce que je regarderai en jouant, avec le rapport entre items ou *rien ne change* est distracteur et
items ou il est la bonne reponse. Je n'ecris pas de rapport minimal : ma regle dit *au moins un*, et je ne la durcis pas.

### 1.3 Corrections de mes propres textes

- **[corrige le 2026-10-27, `nadia-prof`]** Mon 2.2, derniere phrase, admettait *"rien ne change ou une forme equivalente (un glacon
  reste dehors toute une journee sous 0 degre)"*. **La forme equivalente est retiree** : une bonne reponse *il reste de la glace*
  ne protege pas la chaine *rien ne change*, que l'enfant apprendrait a ecarter. La bonne reponse s'ecrit *rien ne change*, a
  l'identique de la chaine proposee en distracteur. Texte d'origine dans `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, 2.2.
- **[corrige le 2026-10-27, `nadia-prof`]** Mon critere 6 precise (5.2) dit *"0 degre ne figure que dans un item de fonte ou de
  gel"*. L'item de 1.2 cite 0 degre et sa bonne reponse est *rien ne change*. Ce que je voulais dire (2.3 : *"0 degre ne sert que la
  fonte et le gel"*, contre *"a 0 degre, l'eau devient de la vapeur"*) : 0 degre est le seuil de la fonte et du gel, et ne sert que
  la tache B. La forme de `theo-archi` (`T05-3f`, *"temperature seulement"*) le lisait deja ainsi.

## 2. `-04` : *la pomme* (`Q8-7`)

**Liste fermee. L'exemple est faux, la regle est juste.** **[corrige le 2026-10-27, `nadia-prof`]** Mon 1.6 (`N04-5`) range
*la pomme face a fruits* parmi les aliments d'enonce *"qui portent un mot d'une famille"*. *Pomme* ne porte aucun mot de *fruits
et legumes* : savoir que la pomme est un fruit est un savoir sur l'aliment, pas une lecture des propositions. J'ai mis dans la meme
parenthese un mot partage (*le poisson*, *l'oeuf*) et une relation de sens ; seul le premier est le defaut a. Texte d'origine dans
`arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, 1.6.

**Ce que je sais en le tranchant** : les items dont la bonne reponse est *fruits et legumes* seront plus faciles que les autres,
parce que le nom de la famille est le mot de tous les jours. C'est le libelle qui le veut, et `FER-1` demande cette famille en bonne
reponse. Le piege de la ligne sur cette famille est la pomme de terre (critere 2), pas la pomme. Je le regarderai en jouant.

## 3. Conflits `C2` a `C5`

### 3.1 `C2` : confirme

Cinq familles, A au moins 5, comme mon 1.5. `camille-po` l'a reporte (annotation huitieme, *"Ce que j'abandonne"*).

### 3.2 `C3` : une assertion propre, parce que la deduction a un trou

`theo-archi` ecrit qu'un distracteur fait seulement de produits sucres est toujours refuse par `T04-2d` ou `T04-2f`. **C'est vrai si
le menu attendu n'a pas de produits sucres, faux s'il en a.** `T04-2f` demande que chaque famille de l'attendu soit dans au moins un
des deux autres : la famille *produits sucres* de l'attendu est alors portee par le distracteur tout sucre, et l'autre distracteur
n'a besoin que des familles restantes, une de moins que l'attendu. `T04-2d` passe.

Contre-exemple **[calcul a la main, familles selon la classification usuelle, a verifier contre la source du paquet]** :

| Menu | Aliments | Familles | Nombre |
|---|---|---|---|
| attendu | *pates, yaourt, gateau* | feculents, produits laitiers, produits sucres | 3 |
| distracteur 1 | *gateau, confiture, miel* | produits sucres | 1 |
| distracteur 2 | *pain, riz, fromage* | feculents, produits laitiers | 2 |

Trois aliments par menu (`T04-2e` passe) ; 3 contre 1 et 2 (`T04-2d` passe) ; chaque famille de l'attendu est dans un distracteur
(`T04-2f` passe) ; l'intersection des deux distracteurs est vide (`T04-2g` passe). Seul mon refus *"aucun n'est fait seulement de
produits sucres"* le refuse. Les deux sondes de `theo-archi` avaient un attendu sans produits sucres. **Je veux une assertion
propre**, et ce contre-exemple ou un equivalent en sonde jugee seule.

### 3.3 `C4` : oui, les deux extensions

- **Une seule proposition *solide* ou *liquide***. Aucune bonne reponse de A ni de B n'est un adjectif d'etat (5.2, regles 1 et 2) ;
  en distracteur, *solide* a cote de *l'eau gele* et *le glacon fond* s'ecarte par la forme. C'est l'etat nomme a la maniere du CP
  (`ETE-CE1-1`, *"solide ou liquide ?"*). Refuse.
- ***"Comment s'appelle l'eau qu'on ne voit pas ?"***. C'est une question de definition, pas une situation : la tache A demande de
  reconnaitre un etat dans une situation (2.1). La definition dans l'enonce donne la reponse comme l'indice *"ne se voit pas"* que ma
  regle 5 interdit. Refuse, comme toute question *"Comment s'appelle ..."*.

### 3.4 `C5` : la liste fermee de `-02` reste fermee

**Je garde *du*, *de la*, *de l'*, *des*, et rien d'autre.** *De petits insectes* et *d'autres insectes* sont du francais correct, et
ils sont refuses en tache aliment, voulu. Trois motifs :

1. ***D'autres* est la tete de la categorie *d'autres animaux*** de la tache A. Une proposition de B qui commence ainsi ressemble a une
   categorie : c'est la faute `FER-3` que la sonde 01 du jeu fautif porte deja (*d'autres animaux* en tache aliment).
2. ***De petits animaux* est le mot de la source** (`cm1-sciences-regimes-alimentaires`, le sanglier) pour une famille d'aliments, pas
   pour un aliment : *de petits ...* glisse vers la categorie.
3. **Un adjectif devant le nom allonge une seule proposition** et la designe par sa forme (`TRV-CE1-2`). Un redacteur qui veut
   *de petits insectes* ecrit *des insectes*.

Ce que je veux pour la tache aliment de `-02` : exactement la regle de `I1` (ma lecture, section 3), et la garde `G7` telle que
`theo-archi` l'a ecrite, *de petits insectes* et *d'autres insectes* compris dans ses refus. Le paquet reel la tient (section 4).

## 4. Le compte de `-02`

**`theo-archi` a raison, et le constat ne change pas.** J'ai ecrit *"les six propositions de B commencent toutes par des"*
(relecture, section 5). Je comptais les six items de la tache B et j'ai ecrit *propositions*. Relu a `fb689ee2` : six items de B,
trois propositions chacun, **18 propositions, toutes en *des*** (bonnes reponses *des feuilles de salade*, *des herbes*, *des
mouches*, *des feuilles*, *des carottes*, *des insectes* ; distracteurs *des mouches*, *des vers de terre*, *des souris*, *des
graines*, *des feuilles*). **[corrige le 2026-10-27, `nadia-prof`]** : lire *"les 18 propositions des six items de B"*. Texte
d'origine dans `relecture-nadia-corrections-ce1-qlm-ce-que-mangent-les-animaux.md`, section 5.

## 5. Les ajouts `X1` a `X5`

| Code | Reponse | Motif |
|---|---|---|
| `X1` | **confirme** | Regle 2 de `-04` : l'enonce demande *le plus varie*, l'indice donne la methode. *Famille* dans l'enonce donne le critere (1.1). Le cas d'une *famille* humaine (*le menu de la famille*) est refuse par `TRV-CE1-6` de toute facon |
| `X2` | **confirme pour `-04` seul** | Regle 5 de `-04` : *"un document nomme hors du depot"*. Tout fichier du depot est refuse, le mien compris. Voir ci-dessous |
| `X3` | **confirme** | Regle 7 : *"d'autres aliments de la liste"*. L'aliment de l'enonce est deja sous les yeux de l'enfant ; le renommer dans l'indice ouvre la periphrase (*"ce avec quoi on fait ..."*). Une comparaison le designe par un pronom (*"Compare-la avec les pates."*) |
| `X4` | **confirme** | Regle 5 de `-05` : en B, un indice qui dit que le froid fait geler ou que la chaleur fait fondre est la definition de la reponse, l'indice de `cp-qlm-etats-eau`. Qu'il ne vise que la tache B est juste |
| `X5` | **confirme** | Regle 6 de `-05`. *Pas tous* veut dire au moins un item hors ebullition. Je n'en demande pas plus ; la repartition se lit au jeu |

**`X2` et la source de `-02`.** La regle 5 de `-04` ne s'applique pas a `-02`, et `-02` n'a rien a corriger.

- **`-04`** : une famille d'aliments est une classification publiee, avec un auteur et un organisme. Mes treize exclusions viennent de
  ce que les sources se contredisent (`FAL-CE1-1`). Ma lecture ne peut pas etre la source d'une classification nutritionnelle : la
  source est hors du depot, et `X2` la garde.
- **`-02`** : la categorie *vegetaux*, *autres animaux*, *les deux* d'un animal familier est un fait elementaire, que je tiens pour
  exact par ma lecture et que je confronte au CM1 (`CYC-1`). La source *lecture-nadia* est celle que j'ai prescrite, sur le precedent
  de `-01` et de `-03` (lecture de `-02`, section 1, *"Comme a -01 et a -03, la source devient ma lecture"*), et ma relecture l'a
  trouvee a l'identique. Ce que ma regle de `-04` refuse aussi, *"un fichier de `nadia-prof` qui demande une source"*, n'est pas ce que
  cite `-02` : ma lecture tient chaque categorie pour exacte, elle ne demande rien.
- **`-05`** : ma regle 7 nomme ma propre instruction, 3.5, comme source. `X2` ne doit pas etre ecrit dans le test de `-05`.

**Le contre-exemple de `theo-archi`** (*"source : lecture-nadia, la forme exacte que porte le paquet reel de `-02`"*) est une bonne
sonde pour le jeu fautif de `-04`, a condition qu'elle reste dans ce jeu fautif. **Aucune correction du paquet de `-02` n'est
necessaire.**

## 6. Textes a reporter dans les stories

Texte exact, que `camille-po` reporte, avec le marqueur *[arbitre par `nadia-prof` le 2026-10-27,
`arbitrage-nadia-contradictions-ce1-qlm-04-05-et-02.md`]*. Il remplace la regle ou la phrase nommee du critere 12 de `-05` et du
critere 11 de `-04` ; le reste de ces criteres ne change pas. Les exemples sont ecrits sans accents ; dans le paquet et dans les
tests, ils s'ecrivent avec leurs accents.

### 6.1 `S57-BR-QLM-CE1-05`, critere 12

> **Regle 2, phrase ajoutee a la fin.** La bonne reponse de B peut aussi etre *rien ne change*, ecrit a l'identique, dans un item
> dont l'enonce decrit de la glace seule, sans eau liquide, et dit qu'il fait sous 0 degre pendant toute la duree decrite (*"Un
> glacon est pose dehors. Toute la journee, il fait sous 0 degre. Que se passe-t-il ?"* ; *rien ne change* ; *le glacon fond*, *le
> glacon disparait*). Aucun item n'a *rien ne change* pour bonne reponse si l'enonce decrit de l'eau liquide : laissee a l'air, elle
> s'evapore (`ETE-CE1-2`).
>
> **Regle 4, inchangee, precisee.** *Rien ne change* n'est propose en distracteur de B que si au moins un item du paquet a *rien ne
> change* pour bonne reponse, ecrit a l'identique ; aucune autre formulation (*il reste de la glace*) n'en tient lieu.
>
> **Regle 8, remplacee.** Bonne reponse *la vapeur d'eau*, A ; bonne reponse *de l'eau liquide* avec un des quatre elements de la
> buee nomme dans l'enonce, A ; bonne reponse de la fonte, du gel, de la glace, de l'eau liquide, ou *rien ne change*, B ; tout autre
> item est refuse. La moins servie porte au moins 3 items.
>
> **Critere 6, phrase remplacee.** *"0 degre ne figure que dans un item de fonte ou de gel"* devient : 0 degre ne figure que dans un
> item de la tache B.

### 6.2 `S57-BR-QLM-CE1-04`, critere 11

> **Regle 6, phrase ajoutee.** La liste des mots est fermee. Un aliment dont le nom ne porte aucun de ces mots (*la pomme*, *la
> carotte*) est admis en enonce de A, meme s'il est un fruit ou un legume.
>
> **Regle 3, inchangee** : le refus *"aucun n'est fait seulement de produits sucres"* vaut aussi quand le menu attendu contient des
> produits sucres.

### 6.3 `S57-BR-QLM-CE1-02`

Rien a reporter : la regle `I1` est ecrite, `G7` la garde, le paquet la tient.

## 7. Ce que je demande a `theo-archi` (forme)

| Code | Garde | Ajustement |
|---|---|---|
| `F1` | `T05-1` | Ajouter a l'ordre des taches : une bonne reponse egale a *rien ne change* apres `plat` compte pour **temperature**. Doit passer : l'exemple de 1.2 |
| `F2` | regle 4 de `-05` (`C1`) | Donner une forme a la regle 4 : un distracteur egal a *rien ne change* apres `plat` exige au moins un item du paquet dont la bonne reponse l'est. Doit refuser : un paquet dont les seuls items a *rien ne change* sont les deux exemples de 2.2 ; un paquet dont l'item de 1.2 porte *il reste de la glace* en bonne reponse |
| `F3` | regle 2 de `-05` | Doit refuser : un item dont la bonne reponse est *rien ne change* et dont l'enonce ne cite pas *sous 0 degre* (ou *en dessous de 0 degre*). Que l'enonce ne decrive pas d'eau liquide, si la forme ne le permet pas, s'ecrit *non garde : `ETE-CE1-2`* et je le lis |
| `F4` | `T05-3f` | Rien a changer : *temperature seulement* est la lecture juste (1.3) |
| `F5` | `T04-2` (`C3`) | Une assertion propre pour le menu fait seulement de produits sucres, avec le contre-exemple de 3.2 jugee seule (`hasSize(1)`) |
| `F6` | `X2` | Dans le test de `-04` seulement ; ni dans celui de `-05`, ni dans celui de `-02`. Une source du depot nommee sans extension ni *nadia* (*"l'instruction de s57"*) passe la forme : *non garde : `QMT-CE1-2`* pour ce reste, je le lis |
| `F7` | `T05-2`, `G7` | Rien a changer (`C4`, `C5`) |

## 8. Cout, duree, verifications

**Cout : 0,00**, par la decision de l'utilisatrice du 2026-10-27 (arbitrages avant premier jet non chiffres). Je ne chiffre rien
d'autre.

**Eventuelle correction d'un lot deja lu** : aucune. Le paquet de `-02` n'a rien a corriger (section 5). Mes deux corrections de
texte sur `-02` (section 4) et sur `-04`, `-05` (sections 1.3, 2) portent sur mes fichiers, pas sur un paquet. Le classement est a
`camille-po`.

**Duree**, lue par `date` : debut **08:03:38**, fin **08:14:31**, avant le commit, soit **10 min 53 s**, dont deux executions du
garde-fou des identifiants. Aucun seuil de duree ne s'applique a ce geste.

**Verifications avant commit**, ce fichier present dans l'arbre, base `fb689ee2`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `F1` a `F7` sont des reperes de demande, comme `C1` et `X1` chez `theo-archi`.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que le 2026-10-26 (mon arbitrage `-04`, `-05`, section 8).
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
