# s57 - palier 5 : avis de theo-archi sur les ajustements `F1` a `F7` des gardes de `-04` et `-05`

- **Auteur** : `theo-archi`. Date : 2026-10-27, fin lue par `date` en section 6.
- **Arbre** : worktree `s57-p5-theo-a0405`, base `round-two` a `db9c8aaa` (`git rev-parse`). Aucun paquet `ce1-qlm-familles-d-aliments` ni `ce1-qlm-eau-trois-etats` n'existe a cette base.
- **Demande** : `arbitrage-nadia-contradictions-ce1-qlm-04-05-et-02.md`, section 7 (`F1` a `F7`), et ses textes corriges de la section 6 : `-05` critere 12 regles 2, 4, 8 et critere 6 ; `-04` critere 11 regles 3 et 6 ; `X1` a `X5` confirmes (section 5).
- **Lu** : ce fichier en entier ; mon `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md` en entier ; `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, sections 1.6 a 2.5, 5 et 6 ; `PaquetLu.sansAccent`.
- **Ce que ce fichier ne fait pas** : il ne reecrit pas mon avis du 2026-10-26, qui reste la forme de reference pour tout ce qui n'est pas nomme ici ; il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition (`F1` a `F7`, `S1` a `S3` sont des reperes) ; il ne prononce aucun Done. **Aucune regle ArchUnit** : ce sont des gardes de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite), comme a l'avis precedent ; aucune ne porte sur une dependance entre classes.

## 0. Verdict

| Code | Garde | Forme retenue | Lieu |
|---|---|---|---|
| `F1` | `T05-1` | une etape de plus : bonne reponse egale a *rien ne change* (apres `plat` et retrait d'un `.` ou `!` final) : **temperature** ; les autres etapes et le tiers ne changent pas | test du paquet de `-05` |
| `F2` | regle 4 de `-05` | methode a part, un message par paquet : refus si au moins une proposition distracteur est *rien ne change* et si aucune bonne reponse ne l'est, par la meme egalite | test du paquet de `-05` |
| `F3` | regle 2 de `-05` | pour un item a bonne reponse *rien ne change* : refus si l'enonce n'a pas `(sous\|en dessous de) (0\|zero) degre`, refus si l'enonce contient le mot `eau` ; le reste de *glace seule* est *non garde : `ETE-CE1-2`* | test du paquet de `-05` |
| `F4` | `T05-3f` | rien a changer ; avec `F1`, l'item de 1.2 est de temperature et passe (critere 6 corrige) | test du paquet de `-05` |
| `F5` | `T04-2` | assertion propre : aucun des trois menus n'a pour ensemble de familles *produits sucres* seul | test du paquet de `-04` |
| `F6` | `X2` | dans le test de `-04` seul, lu sur la source brute, `Test` sensible a la casse ; une source du depot nommee sans extension ni *nadia* est *non garde : `QMT-CE1-2`* | test du paquet de `-04` |
| `F7` | `T05-2`, `G7` | rien a changer | - |

Mesure (section 5) : sur les trois paquets de questionner le monde CE1 existants (41 items, 205 champs), **aucun motif ajuste ne mord**, `MOT_DE_FAMILLE` excepte (28 champs, le meme compte qu'au 2026-10-26, qui ne s'applique qu'a la tache classement de `-04`). Sur le corpus servi entier (3 182 items), `F1`, `F2` et `F3` ne mordent sur rien : aucune proposition n'y vaut *rien ne change*.

## 1. `-05`

### 1.1 `F1`, `T05-1` : *rien ne change* compte pour la temperature

**Egalite, notee `RNC(x)`** : `plat(x)`, un `.` ou un `!` final retire, puis `strip`, egal a `rien ne change`. Ce n'est pas une sous-chaine : *"elle resterait aussi nombreuse sans que rien ne change pour elle"* (`cm1-sciences-chaine-alimentaire`, le seul champ du corpus qui contienne la sequence) n'est pas *rien ne change*.

**Pourquoi le point final est retire.** La regle 4 se garde par la meme egalite (`F2`). Sans ce retrait, un distracteur *"Rien ne change."* n'est pas reconnu, la regle 4 ne se declenche pas, et le paquet passe : un faux vert. *Ecrit a l'identique* (regle 4 corrigee) vaut donc a la casse, aux accents, aux blancs et au point final pres ; une difference de cette sorte entre la bonne reponse et le distracteur n'est pas refusee par la forme et reste a la relecture du `relecteur-pedagogique`.

**Ordre des taches, l'etape 5 ajoutee** (les etapes 1 a 4 de mon avis precedent, 3.1, sont inchangees) :

1. a 4. inchangees ;
5. `RNC(bonneReponse)` : **temperature** ;
6. sinon **refus**.

**[corrige le 2026-10-27, `theo-archi`]** Mon avis precedent, 3.1, terminait l'ordre par *"5. sinon refus."* : un item a bonne reponse *rien ne change* y etait refuse, ce que la regle 8 remplacee admet desormais en B. Texte d'origine dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, 3.1.

**Ce qui ne change pas.** Les etapes sont disjointes : *rien ne change* ne contient ni `VAPEUR`, ni *eau liquide*, ni `FONTE`, `GEL`, `GLACE` ; aucun item deja classe ne change de tache. Le tiers de `T05-1e` (`3 * vapeur < n`) ne compte au numerateur que les bonnes reponses a `VAPEUR` : un item *rien ne change* entre dans `n` comme tout item de temperature, ce que le critere 9 dit deja. Une paraphrase (*"il ne se passe rien"*) reste refusee comme *"ni vapeur ni temperature"* : un faux rouge visible, qui se corrige en ecrivant *rien ne change*, ce que la regle 4 corrigee demande de toute facon.

| Doit passer, mesure | Tache |
|---|---|
| l'exemple de 1.2, *"Un glaçon est posé dehors. Toute la journée, il fait sous 0 degré. Que se passe-t-il ?"*, *rien ne change* | temperature |
| le meme enonce, *"Rien ne change."* | temperature |
| les deux exemples de 2.2 | temperature |
| l'item de la buee de 2.4 | vapeur |

### 1.2 `F2`, la regle 4

**Calcul**, sur la liste des items du paquet :

- `D` = items dont au moins un distracteur verifie `RNC` ;
- `B` = items dont la bonne reponse verifie `RNC` ;
- refus si `D` non vide et `B` vide. **Un message par paquet**, qui nomme les enonces de `D` : *"regle 4 : rien ne change est distracteur de %d item(s) et la bonne réponse d'aucun"*.

**Methode a part** (`verifierRienNeChange(List<Item>)`), sur le modele de `verifierLArticleDesPropositionsAliment` a `-02` : la regle porte sur le paquet, et un paquet de sonde fait de deux ou trois items leverait aussi les refus de comptes de `T05-1b`. La sonde se juge sur la sortie de cette seule methode, `hasSize(1)`.

**Sondes** (`S1` et `S2` construites dans le test ou le jeu fautif, les phrases de `nadia-prof` a l'identique, avec leurs accents) :

| Code | Liste | Attendu, mesure | Ce que la sonde prouve |
|---|---|---|---|
| `S1` | les deux exemples de 2.2 seuls | **1 message** | *rien ne change* en distracteur sans aucune bonne reponse : la contradiction que `C1` nommait est refusee |
| `S2` | les deux exemples de 2.2, plus l'item de 1.2 avec *"il reste de la glace"* en bonne reponse | **1 message** ; l'item ajoute est classe temperature par `GLACE` (etape 4) | la *forme equivalente* que `nadia-prof` retire (1.3) passe `T05-1` et ne tient pas lieu : seule la regle 4 la refuse |
| `S3` | le paquet reel prive de ses items dont la bonne reponse est *rien ne change* | 1 message **si** le paquet reel propose *rien ne change* en distracteur ; sinon 0, et la sonde ne prouve rien | materiau reel plutot que ma liste ; a poser seulement dans le premier cas |
| - | les deux exemples de 2.2 et l'item de 1.2 | **0** | doit passer |
| - | idem, la bonne reponse de 1.2 ecrite *"Rien ne change."* | **0** | doit passer (egalite de `F1`) |

**Plus strict que la lettre, dit.** La regle 4 parle des distracteurs de B ; le calcul lit les distracteurs de tout item. Un *rien ne change* propose en tache vapeur sans bonne reponse est refuse aussi. Le motif pedagogique de la regle (une proposition jamais juste s'ecarte sans lire) vaut pour les deux taches ; si `nadia-prof` veut la limiter a B, `D` se restreint aux items de temperature par `T05-1`, une ligne.

**Ce que `F2` ne garde pas** : un distracteur paraphrase de *rien ne change* (*"il ne se passe rien"*, *"le glaçon reste pareil"*), qui ne passe pas l'egalite et n'est donc pas compte dans `D` ; le rapport entre items ou *rien ne change* est distracteur et items ou il est juste (`nadia-prof`, 1.2 : *"au moins un"*, sans rapport minimal). *Non garde : `TRV-CE1-2`*, lus par `nadia-prof`.

**[corrige le 2026-10-27, `theo-archi`]** Mon avis precedent, 3.3, derniere phrase : *"La regle 4, rien ne change, n'a pas de forme ici : elle se contredit avec la regle 8 (`C1`)."* ; et section 7, `C1` : *"aucune forme pour la regle 4"*. La contradiction est levee par la regle 8 remplacee ; la forme est celle-ci. Texte d'origine dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, 3.3 et 7.

### 1.3 `F3`, la regle 2 pour *rien ne change*

**Pour chaque item ou `RNC(bonneReponse)`**, sur `plat(enonce)`, deux refus, chacun son message :

```
SOUS_ZERO = (?<!\p{L})(?:sous|en dessous de) (?:0|zero) degres?(?!\p{L})     doit etre present
EAU       = (?<!\p{L})eaux?(?!\p{L})                                          doit etre absent
```

**Ce que je tranche pour *sans eau liquide* : une garde partielle, par le mot `eau`, et le reste non garde.** *Glace seule, sans eau liquide* est une propriete de la situation, pas d'un mot : une flaque, une mare, la pluie, du linge mouille decrivent de l'eau liquide sans le mot *eau*, et une liste de ces mots ne serait jamais fermee. Mais l'erreur la plus probable n'est pas celle-la : c'est de reprendre la structure des deux exemples de 2.2 (*"Un glaçon flotte dans un verre d'eau."*) et d'y mettre *rien ne change*, ce que `nadia-prof` nomme *exclu* (glace et eau ensemble) ou *faux* (eau liquide). Ces deux enonces portent le mot `eau` ; le motif les refuse. Ce qu'il interdirait reellement : un item ou de l'eau est nommee et ou *rien ne change* est donne juste. Son echec dans l'autre sens est visible : *"une bouteille d'eau gelée"*, qui ne decrit que de la glace, est refuse a tort, et se reecrit *"un glaçon"*.

| Cas, mesure | Resultat |
|---|---|
| l'exemple de 1.2 | passe |
| *"... il fait en dessous de 0 degré ..."* ; *"... sous zéro degré ..."* | passe |
| *"... Toute la journée, il fait très froid. ..."* | refuse, `SOUS_ZERO` |
| *"... il fait au-dessus de 0 degré ..."* | refuse, `SOUS_ZERO` |
| *"Un glaçon flotte dans un verre d'eau. Toute la journée, il fait sous 0 degré. ..."* | refuse, `EAU` |
| *"Une flaque est dehors. Toute la journée, il fait sous 0 degré. ..."* | **passe** : c'est le reste non garde |
| les deux exemples de 2.2 | non lus (bonne reponse autre que *rien ne change*) |

**Formes admises fermees.** *Sous* et *en dessous de*, les deux de `nadia-prof`. *Au-dessous de 0 degre*, francais correct, est refuse : un faux rouge, qui s'ajoute par une alternative apres son accord.

**Non garde : `ETE-CE1-2`** : de l'eau liquide decrite sans le mot *eau* (flaque, mare, pluie, linge mouille) ; que le froid dure *toute la duree decrite* ; que la glace soit *seule*. Lus par `nadia-prof`.

**[corrige le 2026-10-27, `theo-archi`]** Mon avis precedent, 3.1 : *"Que l'enonce d'un item de temperature donne bien une temperature, glace et eau ensemble ou 0 degre (regle 2), n'est pas garde"* ; et section 6, derniere ligne, *"la regle 2 de `-05` (glace et eau ensemble, ou 0 degre)"*. Cela reste vrai pour les items de fonte, de gel, de glace et d'eau liquide ; pour un item a *rien ne change*, la regle 2 est desormais gardee par `F3` dans la mesure dite ci-dessus. Texte d'origine dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, 3.1 et 6.

### 1.4 `F4` et le critere 6 corrige

`T05-3f` ne change pas : *0 degre* n'est admis que dans un item de **temperature**, c'est-a-dire de la tache B, ce que le critere 6 corrige dit. Avec `F1`, l'item de 1.2 est de temperature : `T05-3f` et `T05-3e` le laissent passer, mesure.

**[corrige le 2026-10-27, `theo-archi`]** Mon avis precedent, 3.3, ligne `T05-3f`, citait le critere 6 sous sa forme d'origine, *"0 degre ne figure que dans un item de fonte ou de gel"*. La citation devient *"0 degre ne figure que dans un item de la tache B"* ; le motif et son message disent *tache B*. Ajouter a *Doit passer* : l'exemple de 1.2. Texte d'origine dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, 3.3.

| Cas, mesure | Resultat |
|---|---|
| l'exemple de 1.2 ; l'exemple de fonte de 2.2 (*au-dessus de 0 degré*) | passe |
| *"À 0 degré, que devient l'eau qui bout ?"*, *de la vapeur d'eau* | refuse |
| *"La buée se forme sous 0 degré. De quoi est faite la buée ?"*, *de l'eau liquide* (tache vapeur) | refuse |

### 1.5 `X4`, `X5`, `F7`

`X4` et `X5` confirmes par `nadia-prof` : la forme ne change pas. L'exemple de 1.2 n'a pas d'indice ; `X4` lira celui que le redacteur ecrira. `F7` : `T05-2` ne change pas, `C4` confirme.

## 2. `-04`

### 2.1 `F5`, le menu fait seulement de produits sucres

**Assertion**, dans la methode des menus, apres `T04-2g` : pour chacun des trois menus d'un item de menu (attendu compris), `familles(menu)` n'est pas l'ensemble `{produits sucres}`. Message : *"regle 3 : le menu '%s' n'est fait que de produits sucrés"*. Les familles viennent de la liste de l'en-tete, comme pour `T04-2d` a `T04-2g`.

**Pourquoi `T04-2d` a `T04-2g` le laissaient passer.** Ma deduction du 2026-10-26 supposait un menu attendu sans produits sucres. Sur le contre-exemple de `nadia-prof` (3.2), mesure : attendu *pâtes, yaourt, gâteau* {feculents, produits laitiers, produits sucres}, 3 familles ; distracteur 1 *gâteau, confiture, miel* {produits sucres}, 1 ; distracteur 2 *pain, riz, fromage* {feculents, produits laitiers}, 2. `T04-2d` : 3 > 1 et 3 > 2. `T04-2e` : trois aliments partout. `T04-2f` : *produits sucres* est porte par le distracteur 1, les deux autres familles par le distracteur 2. `T04-2g` : l'intersection des deux distracteurs est vide. Aucun refus. C'est l'attendu qui porte la famille sucree qui ouvre le trou.

**Pourquoi l'assertion neuve ne double pas les refus existants.** Mesure sur les sondes de mon avis precedent, 2.2 : les sondes de `T04-2d`, `T04-2e`, `T04-2f`, `T04-2g` levent chacune **un seul** message, le leur ; l'assertion neuve n'y mord pas, aucun de leurs menus n'etant tout sucre. Le menu de `nadia-prof` qui doit passer leve 0 message. Le contre-exemple de 3.2 leve **un seul** message, celui de `F5` : c'est la sonde jugee seule, `hasSize(1)` sur la sortie de la methode des menus pour cet item. Les aliments de la sonde (*gâteau*, *confiture*, *miel* en *produits sucres* ; *pâtes*, *pain*, *riz* en *feculents* ; *yaourt*, *fromage* en *produits laitiers*) sont declares dans la liste du jeu fautif, avec une source qui passe `X2`.

**Ce qui leve plusieurs messages, et ne se pose donc pas en sonde jugee seule.** Un menu **attendu** tout sucre (*gâteau, confiture, miel* face a *pâtes, pain, riz* et *pain, riz, pâtes*) : mesure, **4 messages** (`T04-2d`, `T04-2f`, `T04-2g`, `F5`). J'en attendais 2 : mon attente etait fausse, pas la forme ; ce cas est refuse quatre fois, et l'assertion neuve n'y ajoute qu'une redondance sans consequence. Mes deux sondes du 2026-10-26 pour ce cas, jamais ecrites dans l'avis, ne se posent pas : le contre-exemple de 3.2 les remplace.

**[corrige le 2026-10-27, `theo-archi`]** Mon avis precedent, 2.2, paragraphe *"Un menu fait seulement de produits sucres : pas d'assertion propre, parce qu'elle ne refuserait rien de plus."* jusqu'a *"aucune sonde ne pourrait prouver qu'elle mord seule."*, et section 7, `C3`. La deduction est fausse quand le menu attendu contient des produits sucres ; je l'abandonne. Texte d'origine dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, 2.2 et 7.

### 2.2 Regle 6 corrigee : *la pomme*, *la carotte*

`T04-3e` ne change pas, et ne refuse pas *la pomme* ni *la carotte*, mesure : *"Dans quelle famille range-t-on la pomme ?"*, *"... la carotte ?"*, *"... la pomme de terre ?"*, *"... le riz ?"* passent ; *le poisson*, *l'œuf*, *le lait*, *la viande* sont refuses. Le motif ne contient que des formes des mots des noms de famille, aucune relation de sens : il est deja ferme au sens de la regle 6 corrigee.

**Ce que le motif porte au-dela des sept mots de la parenthese, dit** : leurs pluriels, `laitiers?`, `laitieres?`, `sucrees?` et `feculents?`, ce dernier etant le nom d'une famille que la parenthese ne liste pas. Aucun n'est le nom d'un aliment de la ligne. Si `nadia-prof` lit *fermee* comme *les sept mots seuls*, ces formes se retirent du motif, une ligne.

### 2.3 `F6`, `X2`

**Lieu** : le test du paquet de `-04` seul. Ni le test de `-05` (regle 7 : la source est l'instruction de `nadia-prof`), ni celui de `-02` (source *lecture-nadia*, prescrite). Mesure de ce que coute l'erreur de lieu : sur le paquet reel de `-02`, `X2` mord sur **12 lignes de source sur 12** (*lecture-nadia*, seule ou suivie de *cm1-regimes* ou *cm1-chaine*). Le contre-exemple *"source : lecture-nadia"* reste dans le jeu fautif de `-04`.

**Forme, precisee** : lue sur la source **brute**, pas sur `plat`. Refus si elle contient `\.(md|java|yaml|yml)` suivi d'autre chose qu'une lettre ou un chiffre (casse ignoree), ou `Test` non suivi d'une lettre ou d'un chiffre (**casse respectee**), ou `nadia` (casse ignoree).

**[corrige le 2026-10-27, `theo-archi`]** Mon avis precedent, 2.2, ligne `X2`, ecrivait *"ni `Test`"* sans dire sur quel texte, et sa section 1 lisait tous les motifs sur `plat`, casse ignoree : ainsi lu, `test` aurait refuse *attestation* ou *contestée*. Mesure : 0 source du corpus ne contient `test` en aucune casse ; le faux rouge n'est pas observe, il est possible. Texte d'origine dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, 2.2 et 1.

| Cas, mesure | Resultat |
|---|---|
| *lecture-nadia* | refuse |
| *ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md, 3.5* | refuse |
| *PaquetCe1QlmFamillesDAlimentsTest* | refuse |
| *Programme national nutrition santé, Santé publique France* | passe |
| *Attestation de l'organisme, édition contestée* | passe |
| *l'instruction de s57* | **passe** : reste non garde |
| la seule source hors du depot ecrite dans le corpus, `cp-emc-alerte` (*page "Numeros d'urgence" de service-public.fr*) | passe |

**Non garde : `QMT-CE1-2`** : une source du depot nommee sans extension, sans suffixe `Test` et sans *nadia* (*"l'instruction de s57"*, un identifiant `DEC-` ou `ADR-` seul). Lu par `nadia-prof`.

### 2.4 `X1`, `X3`

Confirmes : la forme ne change pas.

## 3. Ce qui reste lu par `nadia-prof`

Ce qui suit s'ajoute a la section 6 de mon avis precedent, qui reste valable, corrigee par 1.3 ci-dessus pour la regle 2 de `-05` :

- *"non garde : `ETE-CE1-2`"* : pour un item a *rien ne change*, de l'eau liquide decrite sans le mot *eau*, la duree du froid, la glace *seule* (`F3`) ;
- *"non garde : `TRV-CE1-2`"* : un distracteur paraphrase de *rien ne change* ; le rapport entre items ou il est distracteur et items ou il est juste (`F2`) ;
- *"non garde : `QMT-CE1-2`"* : une source du depot nommee sans extension, sans `Test` ni *nadia* (`F6`).

## 4. Points a lire par `nadia-prof`, sans desaccord

Aucun desaccord avec ses regles corrigees. Trois points ou la forme est plus stricte ou plus large que la lettre, dits pour qu'elle les confirme ou les retire :

| Code | Point | Retrait, s'il est demande |
|---|---|---|
| `P1` | `F2` lit les distracteurs des deux taches, la regle 4 parle de B | restreindre `D` aux items de temperature |
| `P2` | `F3` refuse *au-dessous de 0 degre* et toute *bouteille d'eau gelée* | une alternative de plus a `SOUS_ZERO` ; aucune pour `EAU`, qui est la garde |
| `P3` | `T04-3e` porte `laitiers?`, `laitieres?`, `sucrees?`, `feculents?` en plus des sept mots | retirer ces formes |

## 5. Mesure

- **Methode** : programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources du JDK que designe la toolchain `jdk` 21 de `~/.m2/toolchains.xml` ; **la machine virtuelle a rapporte `java.version=21.0.10`, `java.vm.version=21.0.10+8-LTS-217`**. Classpath : SnakeYAML 2.6 du depot Maven local, la meme version qu'a l'avis precedent, non relue contre `dependency:build-classpath` ici. Lecture par `new Yaml().load`, collecte recursive de toute table portant `bonneReponse` ; `PaquetLu.sansAccent` recopie. Les gardes sont recopiees dans le programme, pas tirees des tests, qui n'existent pas : **ce n'est pas le test**, la preuve dans le depot sera l'execution des classes de test sur leurs jeux fautifs commites.
- **Perimetre du corpus** : tout `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `db9c8aaa` ; 190 fichiers, 183 avec items, **3 182 items, 15 739 champs** (enonce, indice, bonne reponse, chaque distracteur), les memes comptes qu'a `eaac43a6`. Exclus : les jeux fautifs de `src/test/resources`, les items sans `bonneReponse`. Les sources : lignes de commentaire de la forme `# ... source : ...` du meme repertoire, 14 lignes, dont 1 de prose (*"nadia-prof confronte chaque categorie..."*) que mon motif de ligne a prise pour une source ; 12 sources reelles dans `-02`, 1 dans `cp-emc-alerte`.
- **Sur les trois paquets de questionner le monde CE1** (41 items, 205 champs) : `RNC` 0 proposition, `SOUS_ZERO` 0 champ, `F3` 0 message, `F2` 0 paquet ; `MOT_DE_FAMILLE` 28 champs dont 1 enonce, inchange et sans objet hors de `-04`.
- **Sur le corpus entier** : `RNC` 0 proposition, *rien ne change* en sous-chaine 1 champ (`cm1-sciences-chaine-alimentaire`, distracteur, non egal) ; `SOUS_ZERO` 0 ; `F2` 0 paquet sur 183 ; `F3` 0 message. Ces motifs ne mordent pas sur le contenu ecrit ; ils n'ont, en contrepartie, aucun materiau reel ou mordre, et leur preuve repose sur les phrases de `nadia-prof` et sur `S3` quand le paquet de `-05` existera.
- **Les cas d'epreuve** : **46 cas, 45 conformes, 1 ecart**, l'ecart etant mon attente sur le menu attendu tout sucre (2.1), non une sonde a poser. Les phrases de 1.2 et de 2.2 de `nadia-prof`, et son contre-exemple de 3.2, sont ecrites a l'identique avec leurs accents par moi ; les autres cas (variantes de `F3`, sondes de `T04-2d` a `T04-2g`, sources de `X2`) sont de ma main et valent moins que ses phrases.

## 6. Actions, cout, duree, verifications

| Code | Quoi | Qui |
|---|---|---|
| `A1` | `F1` a `F3` dans le test du paquet de `-05`, `F5` et `F6` dans celui de `-04`, avec chaque paquet, dans le commit du premier jet ; sondes `S1`, `S2`, `S3` si elle prouve, le contre-exemple de 3.2, chacune jugee seule sur la sortie de sa methode | `marc-dev` |
| `A2` | Confirmer ou retirer `P1` a `P3` | `nadia-prof`, par `sofia-sm` |

**Cout** : 0,00, par la decision de l'utilisatrice du 2026-10-27 (arbitrages avant premier jet non chiffres, A.276), sous laquelle ce geste de forme tombe ; le classer est a `camille-po`.

**Duree** : debut non lu par `date`, je ne l'ecris pas ; fin **09:02:36**, lue par `date` apres la derniere verification, avant le commit.

## 7. Verifications avant commit

Ce fichier present dans l'arbre, base `db9c8aaa`. Il ne cree ni ne renomme aucun identifiant de condition.

- `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest -Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*, *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique. Le drapeau pour la meme raison que `nadia-prof` le dit (son arbitrage, section 8) ; je n'ai pas relance la commande sans lui.
- `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

**Signe** : `theo-archi`, s57, palier 5, 2026-10-27.
