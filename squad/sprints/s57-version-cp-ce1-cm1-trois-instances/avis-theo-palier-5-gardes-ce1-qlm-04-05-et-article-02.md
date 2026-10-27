# s57 - palier 5 : avis de theo-archi sur les gardes de `-04`, `-05` (`T04-1` a `T05-3`) et sur la garde de l'article de `-02` (`I1`)

- **Auteur** : `theo-archi`. Date : 2026-10-26 17:35 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-g0405`, base `round-two` a `eaac43a6` (`git rev-parse`). Aucun paquet `ce1-qlm-familles-d-aliments` ni `ce1-qlm-eau-trois-etats` n'existe a cette base.
- **Demande** : `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, sections 5 et 6 (`T04-1` a `T04-3`, `T05-1` a `T05-3`) ; `relecture-nadia-corrections-ce1-qlm-ce-que-mangent-les-animaux.md`, section 5 (`I1`).
- **Lu** : ces deux fichiers en entier ; les stories `S57-BR-QLM-CE1-04` et `-05` avec l'annotation septieme (`docs/backlog/backlog.md:24150-24185`) ; `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java`, `PaquetLu.java`, `PaquetCe1QlmCeQueMangentLesAnimauxTest.java`, `PaquetCe1QlmBesoinsDeLaPlanteTest.java` en entier ; mes avis `avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md` et `avis-theo-palier-5-garde-rotation-ce1-qlm-01.md`.
- **Regles qui font foi** : celles de `nadia-prof` (section 5 de son arbitrage), pas le texte de la story quand les deux different (`C2`).
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition ; il ne prononce aucun Done. Aucune regle ArchUnit, aucun `ADR` : ce sont des gardes de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite) et des precedents `-01`, `-02`, `-03`.

## 0. Verdict

| Code | Garde | Forme | Lieu |
|---|---|---|---|
| `G1` | `T04-1` recompte | **executable**. Tache calculee sur les propositions (trois familles = classement, trois menus = menu, sinon refus), jamais declaree ; deux lignes de compte relues | test du paquet de `-04` |
| `G2` | `T04-2` aliments et menus | **executable**. Liste fermee des aliments dans l'en-tete, aliment de chaque item de A declare ; familles de chaque menu calculees par la liste | test du paquet de `-04` |
| `G3` | `T04-3` exclus et formes | **executable** : les treize exclus et cinq formes ; les paraphrases restent a `nadia-prof` | test du paquet de `-04` |
| `G4` | `T05-1` recompte et tiers | **executable**. Tache calculee sur la bonne reponse (et sur l'enonce pour *de l'eau liquide*), jamais declaree | test du paquet de `-05` |
| `G5` | `T05-2` formes du CP | **executable**, trois motifs | test du paquet de `-05` |
| `G6` | `T05-3` buee, bouillir, nombre, intrus, indice | **executable** : huit refus ; la negation est exemptee, pas la restriction | test du paquet de `-05` |
| `G7` | `I1` article de `-02` | **executable**, debut de chaine sur quatre articles, tache aliment seule, methode a part | `PaquetCe1QlmCeQueMangentLesAnimauxTest` |

Mesure (section 5) : sur les trois paquets de questionner le monde CE1 existants, **aucun motif de `-05` ni de `-04` ne mord, sauf `MOT_DE_FAMILLE` (28 champs)**, qui ne s'applique qu'aux enonces de la tache classement de `-04` ; sur le paquet reel de `-02`, la garde de l'article refuse **0 proposition sur 18**. Les deux nouveaux lots n'existent pas : la morsure sur eux sera celle du jeu fautif.

## 1. Ce qui est commun aux sept gardes

**Le test du paquet, pas le test transverse.** Chaque garde definit des taches, des listes et des mots propres a une ligne. Dans le test transverse, elles mordraient par inclusion sur les autres lots du meme lot de matiere : `MOT_DE_FAMILLE` trouve 28 champs dans les paquets existants de questionner le monde CE1 (*oeuf*, *poisson* de `-01` et `-02`), qui y sont justes. Chaque test ne lit que son `FICHIER_CONTENU` et son jeu fautif. Ce qui me ferait changer d'avis : une deuxieme ligne qui a besoin du meme motif ; il se recopie alors, et l'extraction se discute a ce deuxieme usage.

**Normalisation, une seule forme, nommee `plat`** (elle existe deja sous le nom `normalise` dans le test de `-02`, sans le retrait d'article) :

1. minuscule et sans accent : `PaquetLu.sansAccent` (qui fait aussi `oe` de `œ`) ;
2. apostrophe typographique `U+2019` remplacee par l'apostrophe droite ; traits d'union `U+2010` et `U+2011` remplaces par `-` (pour *appelle-t-on*, *as-tu*) ;
3. toute suite de blancs, `U+00A0` et `U+202F` compris, ramenee a une espace, puis `strip`.

Tous les motifs se cherchent sur `plat`, bornes par `(?<!\p{L})` et `(?!\p{L})`, avec `CASE_INSENSITIVE | UNICODE_CASE | UNICODE_CHARACTER_CLASS` (les `FLAGS` des tests existants). **Une seule exception, *maïs*** (section 2.3). Le retrait d'un article de tete, quand il est dit, utilise la liste fermee du test de `-02` (`ARTICLE_DE_TETE`), plus `un ` et `une `.

**Le paquet ne declare pas ses taches item par item** : elles se calculent, comme a `-02`. L'en-tete porte deux lignes de compte, relues contre le calcul (`S5` de `-02`) : `#   classement : ... (N items)` et `#   menu : ... (N items)` pour `-04` ; `#   vapeur : ... (N items)` et `#   temperature : ... (N items)` pour `-05`. L'ordre des assertions de `-02` vaut : comptes calcules d'abord, comparaison a l'en-tete ensuite.

**Ajouts de ma main.** Cinq refus sont tires d'une regle de la section 5 de `nadia-prof` sans figurer dans son tableau de la section 6. Ils sont marques `X1` a `X5` ; elle les confirme ou les retire (`A4`), comme `S4` a `-02` et `R4` a `-01`.

## 2. `-04`, `ce1-qlm-familles-d-aliments`

### 2.1 `T04-1`, le recompte

**Ce que le paquet porte.**

- En-tete, la liste fermee (regle 5) : une ligne par aliment, `#   aliment <nom> : <famille> ; source : <source>`, lue par `^#\s+aliment (.+?) : (fruits et legumes|feculents|produits laitiers|viande, poisson et oeufs|produits sucres) ; source : (\S.*)$`. Le nom est compare apres `plat` et retrait d'article ; il est ecrit sous la forme ou les items l'emploient (*carottes* et *carotte* sont deux lignes si les deux servent).
- Chaque item de classement : un commentaire `FAL-CE1-1 aliment : <nom>`, sur le modele de `RAL-CE1-1 animal` a `-02`.

**Reconnaissance d'une proposition.**

- **famille** : `plat` puis retrait d'article, **egalite** avec l'une des cinq formes ci-dessus. Jamais une sous-chaine : *"des fruits"* n'est pas *fruits et legumes*.
- **menu** : `plat`, point final retire, decoupe sur `,`, retrait d'article par element ; c'est un menu si **au moins deux elements** et si chacun est un nom de la liste. Une famille se reconnait avant un menu : *viande, poisson et oeufs* contient une virgule et n'est jamais lu comme un menu.

**Tache d'un item** : la bonne reponse et les trois propositions sont des familles, **classement** ; les trois propositions sont des menus, **menu** ; sinon **refus** (`FER-3`, *"ni classement ni menu"*).

**Refus, chacun avec son contre-exemple de jeu fautif** :

| Code | Refus | Contre-exemple | Doit passer |
|---|---|---|---|
| `T04-1a` | tous les items sont de classement | le paquet reel restreint a ses items de classement, en-tete inchange | - |
| `T04-1b` | menu a 2 items | le paquet reel avec ses deux premiers items de menu seulement | - |
| `T04-1c` | *viande, poisson et oeufs* n'est la bonne reponse d'aucun item de classement (`FER-1`, cinq familles) | le paquet reel prive de ces items, une boucle sur les cinq familles comme `unPaquetPriveDUneCategorieEnBonneReponseEstRefuse` | - |
| `T04-1d` | *"Lequel de ces aliments est un féculent ?"*, bonne *"les pâtes"* | jeu fautif, distracteurs *"le yaourt"*, *"la pomme"* | - |
| `T04-1e` | un item de classement dont une proposition est un aliment | *"Dans quelle famille range-t-on le yaourt ?"*, *"produits laitiers"* ; *"féculents"*, *"le fromage"* | - |
| `T04-1f` | classement sous 5 items, menu sous 3 | comptes du paquet reel mutes | le paquet reel, cinq familles en classement et trois menus |
| `T04-1g` | l'en-tete ecrit un autre compte que le calcul | en-tete reel, un compte change | - |

**Pourquoi au moins deux elements par menu.** Sans ce seuil, *"les pâtes"* est un menu d'un aliment, et `T04-1d` compterait pour la tache menu : c'est le trou que `nadia-prof` nomme en 1.1. Mesure : avec le seuil, `T04-1d` est refuse comme *"ni classement ni menu"*. Sans lui, par lecture et non par mesure, il ne serait refuse que plus loin par `T04-2` (aucun menu strictement le plus varie), avec un message qui ne dit pas la faute.

**Ce que `T04-1` ne garde pas** : que la tache menu soit une operation distincte item par item (`FER-3`, `QC9`), lu par `nadia-prof`.

### 2.2 `T04-2`, declaration des aliments et des menus

| Code | Refus | Forme | Contre-exemple | Doit passer |
|---|---|---|---|---|
| `T04-2a` | la pomme de terre declaree hors des feculents | si la liste porte *pomme de terre*, sa famille est *feculents*, ecrite dans le test (la seule famille que `FAL-CE1-1` fixe) ; et un item de classement au moins declare *pomme de terre* avec la bonne reponse *feculents* (critere 2) | en-tete reel, ligne *pomme de terre* changee en *fruits et legumes* | *"Dans quelle famille range-t-on la pomme de terre ?"*, *"féculents"* |
| `T04-2b` | un aliment d'un item absent de la liste | le nom declare d'un item de classement, et chaque element d'un menu, est une cle de la liste ; l'enonce nomme l'aliment declare (motif borne sur `plat`) | jeu fautif, *"Dans quelle famille range-t-on la courgette ?"*, *courgette* absente de la liste | - |
| `T04-2c` | une bonne reponse de classement qui n'est pas la famille declaree | egalite entre la famille de la bonne reponse et celle que la liste donne a l'aliment declare | jeu fautif, *yaourt* declare, bonne *"féculents"* | - |
| `T04-2d` | le menu attendu n'a pas strictement plus de familles que chacun des deux autres | familles d'un menu = ensemble des familles de ses elements par la liste ; `>` strict contre les deux | *"riz, poisson, carottes"* (3) face a *"pâtes, pain, poulet"* (2) et *"pâtes, haricots verts, fromage"* (3) | l'exemple de `nadia-prof` : *"riz, poisson, carottes, yaourt"* (4) face a *"pâtes, pain, poulet, pomme"* et *"pâtes, pain, haricots verts, fromage"* (3 chacun) |
| `T04-2e` | trois menus de tailles differentes | nombre d'elements egal sur les trois | un distracteur de six aliments | idem |
| `T04-2f` | une famille presente dans le seul menu attendu | chaque famille de l'attendu est dans au moins un des deux autres | distracteurs *"pâtes, pain, riz, pomme"*, *"pâtes, pain, haricots verts, pomme"* (le poisson et le yaourt ne sont que dans l'attendu) | idem |
| `T04-2g` | une famille dans les deux autres et absente de l'attendu | intersection des deux autres incluse dans l'attendu | distracteurs *"pâtes, poulet, gâteau, pain"*, *"pomme, fromage, confiture, carottes"* (sucre dans les deux, pas dans l'attendu) : **seul refus leve** | idem |
| `X1` | l'enonce de menu ne demande pas *le plus varie*, ou donne la methode | l'enonce contient `plus varie` et ne contient pas `famille` (regle 2) | *"Lequel a le plus de familles ?"* | *"La cantine propose trois menus. Lequel est le plus varié ?"* |
| `X2` | une source qui est un fichier du depot | la source d'une ligne de la liste ne contient ni `.md`, ni `.java`, ni `.yaml`, ni `Test`, ni `nadia` (regle 5) | `source : lecture-nadia`, la forme exacte que porte le paquet reel de `-02` (`:47`) | une source nommee par un titre et un organisme |

**Un menu fait seulement de produits sucres : pas d'assertion propre, parce qu'elle ne refuserait rien de plus.** Si un distracteur n'a que *produits sucres*, `T04-2f` oblige l'autre distracteur a porter toutes les familles de l'attendu, donc au moins autant de familles que lui, et `T04-2d` refuse. Si c'est l'attendu, il a une famille, et aucun distracteur ne peut en avoir moins. Mesure : les deux sondes que j'ai construites pour ce cas sont chacune refusees aussi par `T04-2d` ou `T04-2f` ; aucune ne peut l'etre par ce seul motif. Le refus de `nadia-prof` est donc garde, par `T04-2d` et `T04-2f`. Une assertion de plus serait decorative : aucune sonde ne pourrait prouver qu'elle mord seule.

**Ce que `T04-2` ne garde pas** : l'exactitude de chaque famille contre sa source, hors la pomme de terre ; que la source nommee existe et porte les cinq familles (`QMT-CE1-2`, `QC9`) ; un aliment nomme dans un enonce ou un indice sans etre declare ; le menu *"d'un personnage"* qui suppose un foyer (regle 4, `TRV-CE1-6`).

### 2.3 `T04-3`, exclus et formes

**Les treize exclus** (critere 3), dans tout champ lu et dans chaque nom de la liste :

```
(?<!\p{L})(lentilles?|pois chiches?|petits? pois|avocats?|olives?|fruits? a coque|beurres?|cremes?
         |jus de fruits?|pizzas?|sandwich(?:e?s)?|gratins?)(?!\p{L})            sur plat(champ)
(?<!\p{L})maïs(?!\p{L})                                                     sur nfc(champ)
```

`nfc` = minuscule, `Normalizer.Form.NFC`, etapes 2 et 3 de `plat`, **sans retrait d'accent**.

- **Pourquoi *maïs* a part.** `sansAccent` fait de *maïs* le mot *mais*. Mesure : sur le corpus servi, **29 champs portent `mais` apres `sansAccent` ; 1 seul porte `maïs`** (`cm1-histoire-geographie-nommer-en-histoire`, *"le maïs"*). Un motif sur `plat` aurait refuse 28 conjonctions. Sur `nfc`, la conjonction n'est jamais lue.
- **Ce que la forme ne voit pas** : *mais* ecrit sans trema pour la cereale. C'est une faute d'orthographe, de la garde du `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04), pas de ce test.
- **Doit passer, mesure** : *fruits et legumes*, *des fruits*, *des pois*, *"C'est bon, mais c'est sucré."*. *Fruits a coque* et *jus de fruits* sont deux sequences entieres : `fruits` seul ne mord jamais.
- **Plus strict que la lettre, dit** : *creme* refuse aussi *crème fraîche* et *crème dessert*, et *beurre* refuse *beurres* ; *tartine beurrée* (`beurree`) n'est pas lu.

**Les formes** :

| Code | Refus | Motif | Champ | Doit passer |
|---|---|---|---|---|
| `T04-3a` | *"Pourquoi manger varié ?"* | `pourquoi` **et** `(vari\p{L}*\|mang\p{L}*)` | enonce | - |
| `T04-3b` | l'item de `cp-qlm-hygiene` | deja refuse par `NRC-CE1-1` (test transverse, egalite d'enonce) ; rien a ajouter | - | - |
| `T04-3c` | *"Quel menu est mauvais pour la santé ?"* | `mauvai(s\|se\|ses)` | tout champ lu | - |
| `T04-3d` | *"Qu'as-tu mangé ce midi ?"* ; l'indice *"Pense à ce que tu manges à midi."* | `tu (manges?\|as mange\|aimes?\|preferes?\|bois\|choisis)`, `(manges\|as\|aimes\|preferes\|bois\|choisis\|choisirais\|prends)-tu`, `(ton\|ta\|tes) (repas\|assiettes?\|menus?\|dejeuners?\|diners?\|gouters?\|petits?-dejeuners?\|cantine\|maison\|famille\|parents)` | tout champ lu | *"Compte les familles de chaque menu."* ; *"Compte dans ta tête les familles de chaque menu."* |
| `T04-3e` | un enonce de classement dont l'aliment porte un mot d'un nom de famille (*le poisson*) | `(poissons?\|oeufs?\|viandes?\|laits?\|laitiers?\|laitieres?\|fruits?\|legumes?\|sucres?\|sucrees?\|feculents?)` | enonce, tache classement | *"Dans quelle famille range-t-on la pomme de terre ?"* |
| `T04-3f` | un indice de classement qui contient le nom ou un mot de la famille attendue (*"Pense au lait."* pour le yaourt) | un motif par famille : fruits et legumes `fruits?\|fruitiers?\|fruitees?\|legumes?` ; feculents `feculents?` ; produits laitiers `laits?\|laitiers?\|laitieres?` ; viande, poisson et oeufs `viandes?\|poissons?\|oeufs?` ; produits sucres `sucres?\|sucrees?\|sucrer\|sucreries?` | indice, tache classement | *"Pense aux pâtes et au riz."* pour la pomme de terre |
| `X3` | un indice de classement qui nomme l'aliment de son enonce | le nom declare, borne, absent de l'indice (regle 7, *"d'autres aliments"*) | indice, tache classement | idem |

**Pourquoi `T04-3d` n'est pas "toute deuxieme personne".** Mesure du premier motif que j'ai ecrit, `(tu|ton|ta|tes)` : **203 champs** du corpus servi, dont *"Dis le jour dans ta tête"*, *"Compte tes pas"*, *"Reconnais-tu un mot connu"* : des methodes justes, la forme meme d'un indice de CE1. Il aurait refuse *"Compte dans ta tête les familles de chaque menu."*. Le motif retenu lie la deuxieme personne a manger, aimer, choisir ou au repas : **9 champs** du corpus (8 *"quelle unité choisis-tu"* de `ce1-masses-contenances`, 1 *"Où as-tu rangé tes affaires"*), tous de vraies questions a l'enfant. `BIA-4` dans ses paraphrases (*"Quel menu te plaît ?"*) reste a `nadia-prof`.

**Pourquoi `T04-3f` enumere des formes, pas des racines.** Une racine `lait\p{L}*` refuserait *laitue*, un fruit et legume juste en exemple.

**Ce que `T04-3` ne garde pas** : les paraphrases de *mauvais* (*le moins bon*, *a eviter*) ; *"aucun aliment de defendu"* ; un plat compose autre que les trois nommes ; la definition d'une famille dans un indice ; *"a la maison"*, *"chez lui"* (`TRV-CE1-6`). Lus par `nadia-prof`.

## 3. `-05`, `ce1-qlm-eau-trois-etats`

### 3.1 `T05-1`, recompte et tiers

**Motifs** (sur `plat`) : `VAPEUR` = `vapeurs?` ; `BUEE` = `buees?|nuages?|brouillards?|fumees?` ; `FONTE` = `fond|fondent|fondre|fondu|fondue|fondus|fondues|fonte|fondra|fondait` ; `GEL` = `gel|gele|gelent|geler|gelee|gelees|geles|gelera|gelait` ; `GLACE` = `glaces?|glacons?` ; `TEMPERATURE` = `chaud\p{L}*|chauff\p{L}*|rechauff\p{L}*|chaleur|froid\p{L}*|refroidi\p{L}*|congelateurs?|soleil|degres?`.

**Tache d'un item, dans cet ordre** :

1. la bonne reponse contient `VAPEUR` : **vapeur** ;
2. la bonne reponse est, a l'article pres, *eau liquide*, et l'enonce contient `BUEE` : **vapeur** ;
3. la bonne reponse contient *eau liquide* : **temperature** si l'enonce contient `TEMPERATURE`, sinon **refus** ;
4. la bonne reponse contient `FONTE`, `GEL` ou `GLACE` : **temperature** ;
5. sinon **refus**.

| Code | Refus | Contre-exemple | Doit passer |
|---|---|---|---|
| `T05-1a` | tous les items sont de vapeur | le paquet reel restreint a ses items de vapeur, en-tete inchange | - |
| `T05-1b` | temperature a 2 items | le paquet reel avec deux items de temperature | - |
| `T05-1c` | un item de temperature dont la bonne reponse est la vapeur d'eau | **impossible par construction** : la regle 1 classe vapeur tout item dont la bonne reponse est la vapeur. La sonde *"On chauffe l'eau de la casserole. Que devient-elle ?"*, *"de la vapeur d'eau"*, verifie que l'item compte pour vapeur et pas pour temperature | - |
| `T05-1d` | *de l'eau liquide* sans element de la buee et sans temperature | *"Que trouve-t-on dans le verre ?"*, *"de l'eau liquide"* | - |
| `T05-1e` | moins d'items a la vapeur d'eau que le tiers (critere 9) | `3 * vapeur < n`, ou `vapeur` compte les seuls items dont la bonne reponse contient `VAPEUR`, pas la tache vapeur entiere (*de l'eau liquide* de la buee n'y entre pas) | les deux exemples de 2.2, comptes temperature ; l'item de la buee de 2.4, compte vapeur |
| `T05-1f` | l'en-tete ecrit un autre compte que le calcul | en-tete reel, un compte change | - |

**`TEMPERATURE` est une liste fermee, et son echec est visible.** Un mot de temperature qu'elle n'a pas (*le four*, *l'hiver*) refuse a tort un item de *eau liquide* : un faux rouge, qui se corrige par une ligne, apres accord de `nadia-prof`. Elle n'est lue que sur la regle 3 : pour la fonte, le gel et la glace, la tache se decide sur la bonne reponse seule, comme sa regle 8 le dit. **Que l'enonce d'un item de temperature donne bien une temperature, glace et eau ensemble ou 0 degre (regle 2), n'est pas garde** : lu par `nadia-prof`.

### 3.2 `T05-2`, ce que le CP a servi

| Code | Refus | Motif | Doit passer |
|---|---|---|---|
| `T05-2a` | une proposition *solide* ou *liquide* | egalite, apres `plat` et retrait d'article, avec `solide` ou `liquide` ; *eau liquide* n'est pas `liquide` | *"de l'eau liquide"* |
| `T05-2b` | *"... solide ou liquide ?"* | `solide ou liquide\|liquide ou solide` dans l'enonce | - |
| `T05-2c` | *"Comment appelle-t-on le passage ..."*, *"Comment s'appelle ..."* | `comment (s'appelle\|appelle-t-on\|on appelle\|nomme-t-on\|on nomme)` dans l'enonce | *"Un glaçon flotte dans un verre d'eau. On met le verre au congélateur. Que se passe-t-il ?"* |

**Mesure sur materiau reel** : les trois motifs refusent **les 14 items de `cp-qlm-etats-eau`**, et aucun d'eux n'est refuse pour une autre raison : 12 par `T05-2a` et `T05-2b`, 2 par `T05-2c` (*"Comment appelle-t-on le passage de la glace à l'eau liquide ?"*). C'est la population que `NRC-CE1-1` vise, ecrite par d'autres que moi.

**Plus strict que la lettre, dit (`C4`)** : `T05-2a` refuse une seule proposition *solide*, pas seulement le couple. `T05-2c` refuse aussi *"Comment s'appelle l'eau qu'on ne voit pas ?"*, une question de nommage de la vapeur.

### 3.3 `T05-3`, buee, bouillir, nombre, intrus, indice

Chaque champ lu (enonce, indice, bonne reponse) est decoupe en phrases sur `(?<=[.?!])\s+`. `NEGATION` = `(ne |n')` suivi, dans la meme phrase, de `pas`. `RESTRICTION` = `(ne |n')` suivi de `que|qu'`, ou `seulement|uniquement|il faut|doit|doivent`.

| Code | Refus | Forme | Doit passer |
|---|---|---|---|
| `T05-3a` | *"La buée sur la vitre, c'est de la vapeur d'eau."* ; l'indice *"Le nuage, c'est de la vapeur d'eau."* | une phrase avec `BUEE` et `VAPEUR`, **sans** `NEGATION` | *"La buée n'est pas de la vapeur d'eau."* (2.4, *"elle ne doit pas etre refusee"*) ; *"La buée, ce sont de toutes petites gouttes d'eau."* ; *la buée* en distracteur a cote de *la vapeur d'eau* (les distracteurs ne sont pas lus par `T05-3a`) |
| `T05-3b` | la bonne reponse *de la vapeur d'eau* a *"De quoi est faite la buée ?"* | enonce avec `BUEE` et bonne reponse avec `VAPEUR` | l'item de 2.4, bonne *"de l'eau liquide"* |
| `T05-3c` | *"On voit la vapeur d'eau sortir de la casserole."* | une phrase avec `VAPEUR` et `voi[st]\|voient\|voyons\|voyez\|voir\|vu\|vue\|visibles?\|apercoi\p{L}*`, sans `NEGATION` | *"On ne voit plus la flaque."* (pas de vapeur dans la phrase) |
| `T05-3d` | *"L'eau ne devient de la vapeur que quand elle bout."* | une phrase avec `VAPEUR`, `BOUILLIR` et `RESTRICTION`. `BOUILLIR` = `bouill\p{L}*\|ebullition\|bouent`, ou `bout` non precede de `au\|le\|du\|un\|ce\|son\|a` **en mot entier** | *"L'eau qui bout dans la casserole devient de la vapeur d'eau."* ; *"au bout de quelques heures"* |
| `T05-3e` | *100 degres*, *cent degres*, *10 degres*, *moins 5 degres* | chaque `(moins \|- ?\|U+2212 ?)?(<nombre>) ?(degres?\|°)`, ou `<nombre>` = chiffres avec decimale eventuelle, ou un nombre en lettres (`zero`, `un` a `seize`, `vingt` a `soixante`, `cent`, `mille`) ; refuse sauf `0` ou `zero` sans signe | *0 degre*, *zéro degré*, *sous 0 degre*, *au-dessus de 0 degre* ; *deux glaçons* (pas de `degre` apres) |
| `T05-3f` | *0 degre* dans un item dont la bonne reponse est la vapeur d'eau | `0` ou `zero` degre admis dans un item de **temperature** seulement (critere 6, *"0 degre ne figure que dans un item de fonte ou de gel"*) | les deux exemples de 2.2 |
| `T05-3g` | un item de vapeur dont les deux distracteurs sont de la famille de la buee | au plus un distracteur avec `BUEE` (regle 3, *"un seul par item"*) | un seul (*la buée*, *l'eau a disparu*) |
| `T05-3h` | en vapeur, un indice qui contient *invisible* ou *ne se voit pas* | sur l'indice : `invisibles?`, `(ne \|n')(se \|la \|le \|les \|l')?voi\p{L}* pas`, `pas visibles?`, `ne peu\p{L}* pas (la \|le \|l')?voir` | *"On ne voit plus la flaque."* dans un **enonce** |
| `X4` | en temperature, un indice qui enonce le sens de la temperature (*"Le froid fait geler l'eau."*) | une phrase de l'indice avec `froid\p{L}*\|chaleur\|chaud\p{L}*\|chauff\p{L}*` et `GEL` ou `FONTE` (regle 5) | *"Compare le congélateur et une journée d'été."* ; la meme phrase dans un item de **vapeur** (la regle 5 ne vise que la temperature) |
| `X5` | tous les items a la vapeur d'eau sont des situations ou l'eau bout | parmi les items dont la bonne reponse contient `VAPEUR`, au moins un dont l'enonce n'a pas `BOUILLIR` (regle 6) | un item du linge qui seche |

**Le defaut que la mesure a trouve dans mon propre motif.** Ma premiere forme de `BOUILLIR` excluait `bout` apres `le ` sans borne de mot : elle lisait *elle bout* comme *le bout*, et `T05-3d` laissait passer la phrase que `nadia-prof` refuse. Le corpus ne l'a pas vu (aucun *elle bout* dans le contenu servi) ; sa sonde, si. La forme ci-dessus porte la borne, et c'est la raison pour laquelle le jeu fautif reprend sa phrase **a l'identique**.

**`T05-3b` refuse aussi *"D'où vient la buée ?"*, bonne *"de la vapeur d'eau"*.** C'est conforme a sa regle 1 (si l'enonce nomme la buee, la bonne reponse est *de l'eau liquide*), et c'est la condensation, un mot que `QMT-CE1-1` exclut. Je le dis pour qu'il ne soit pas decouvert au premier jet.

**Ce que `T05-3` ne garde pas** : une buee presentee comme vapeur en deux phrases, ou par une negation double ; *"Que vois-tu au-dessus de la casserole ?"*, bonne *"la vapeur d'eau"* (l'enonce et la bonne reponse sont deux champs : les lire ensemble refuserait *"On voit le linge sécher au soleil. Que devient son eau ?"*, qui est juste) ; un distracteur que la situation produit (la fumee blanche pour une casserole qui bout) ; un etat que la temperature rend impossible (la glace au soleil) ; une temperature sans le mot *degre* ; une duree chiffree (*"en dix minutes"*, `QMT-CE1-2`) ; qu'un item de temperature ne se resolve pas avec les indices de `cp-qlm-etats-eau` (2.2). Lus par `nadia-prof`.

**La regle 4, *rien ne change***, n'a pas de forme ici : elle se contredit avec la regle 8 (`C1`).

## 4. `-02`, la garde de l'article (`I1`)

**Forme.** Une methode a part, `verifierLArticleDesPropositionsAliment(List<Item>)`, dans `PaquetCe1QlmCeQueMangentLesAnimauxTest`. Pour chaque item dont `tache(item)` vaut `aliment`, chaque proposition, mise en minuscule (`Locale.FRENCH`), `U+2019` remplace par l'apostrophe droite, blancs ramenes a une espace (`U+00A0`, `U+202F` compris) et `strip`, doit verifier :

```java
private static final Pattern ARTICLE_ADMIS = Pattern.compile("^(du |de la |de l'|des )\\p{L}");
```

Message : *"I1 : %s (proposition de '%s', tâche aliment) ne commence ni par du, de la, de l' ni par des"*. `I1` est le repere de `nadia-prof` dans sa lecture, pas un identifiant neuf.

**Pourquoi une methode a part et pas une ligne de `verifierLesPropositionsDeChaqueTache`.** La sonde 01 du jeu fautif porte *"d'autres animaux"*, ecrit avec `U+2019`, en tache aliment : c'est sa faute `FER-3`, et le test `s3` attend ce message. Dans la meme methode, l'ordre des assertions deciderait lequel des deux messages sort. Mesure : sur `animaux-fautifs.yaml`, la garde refuse **1 proposition sur 18** (6 items de tache aliment), et c'est celle-la.

**Ce que la garde laisse passer, mesure** : *de l'herbe* avec l'apostrophe droite et avec `U+2019` ; *du foin*, *de la viande*, *des souris*, *Des souris* ; *de la* suivi de `U+00A0` ; les quatre articles meles sur un meme item (aucune contrainte entre propositions) ; toute proposition d'un item de categorie, *les deux* compris (la methode ne lit pas cette tache).

**Ce qu'elle refuse, mesure** : *le foin*, *la salade*, *les souris*, *un ver de terre*, *une souris*, *3 souris*, *deux souris*, *souris* ; et aussi *de petits insectes*, *d'autres insectes* et *de l' herbe* (`C5`).

**Contre-exemples a poser dans le jeu fautif** : une sonde par article refuse de la liste de `nadia-prof` (*le*, *la*, *les*, *un*, *une*, un nombre, sans article) ; une sonde qui passe et mele *du*, *de la* et *de l'* ecrit avec `U+2019` ; une sonde de categorie dont une proposition est *les deux*, qui passe.

**Sur le paquet reel** : 6 items de tache aliment, **18 propositions, 0 refusee**. `nadia-prof` ecrit *"les six propositions de B commencent toutes par des"* ; par ma methode (tache calculee par `categorie`, bonne reponse et deux distracteurs de chaque item), il y en a 18, toutes en *des*. Le constat est le meme, seul le compte differe.

**Ce qu'elle ne garde pas** : que l'article convienne au nom (*du souris*), l'elision (*de la herbe* passe), *"meme type d'article"* au-dela de la liste fermee. Lus par `nadia-prof` et le `relecteur-pedagogique`.

## 5. Mesure

- **Methode** : programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources du JDK 21.0.10 (propriete `java.version` imprimee par le programme), classpath SnakeYAML 2.6 du depot Maven local, version que `dependency:build-classpath` resolvait pour `adapters/adapter-content` a l'avis de `-01`, non relue ici. Il lit chaque fichier par `new Yaml().load`, comme `PaquetLu`, et recopie `PaquetLu.sansAccent`. **Ce n'est pas le test** : la preuve dans le depot sera l'execution des classes de test sur leurs jeux fautifs commites.
- **Perimetre du corpus** : tout `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `eaac43a6` ; 190 fichiers, 183 avec des items a `bonneReponse`, **3 182 items, 15 739 champs** (enonce, indice, bonne reponse, chaque distracteur). Exclus : les jeux fautifs de `src/test/resources`, sauf `animaux-fautifs.yaml` pour la section 4, et les items sans `bonneReponse`.
- **Sur les trois paquets de questionner le monde CE1** (41 items) : 0 champ pour chaque motif de `-05` et de `-04`, sauf `MOT_DE_FAMILLE`, 28 champs (section 1).
- **Sur le corpus entier**, ou chaque motif mord : `EXCLUS` 9 champs (*beurre* 2, *creme* 4, *jus de fruit(s)* 2, *lentilles* 1) et `maïs` 1, tous des aliments reels ; `MAUVAIS` 2 ; `T04-3d` 9 (section 2.3) ; `BOUILLIR` 1 sur 9 occurrences de `bout`, la seule qui soit le verbe (*"L'eau qui bout longtemps"*, `cm1-sciences-etats-matiere`), les 8 autres etant *au bout de*, *jusqu'au bout*, *au bout du* ; `DEGRE` 4 (*"à cent degrés"*, *"à dix degrés"*, *"à zéro degré"* de `ce1-texte-documentaire`, *"trois degrés"* de `cm1-emc-fait-opinion`), ce qui prouve sur du contenu ecrit qu'un nombre en lettres arrive ; `INVISIBLE` 8, dont les deux indices de `cm1-sciences-etats-matiere` (*"La vapeur d'eau, invisible dans l'air chaud"*, *"un gaz invisible"*) que `T05-3h` refuserait en tache vapeur, c'est-a-dire l'indice que `nadia-prof` interdit, ecrit par un autre redacteur ; `NOMMAGE` 88, toutes de vraies questions de nommage d'autres matieres.
- **Les cas d'epreuve** : `-05`, **30 cas, 30 conformes** ; `-04`, **22 cas, 22 conformes**, plus 19 cas d'exclus (les treize, *MAÏS* et *crème fraîche*, quatre formes qui doivent passer), 19 conformes ; `-02`, **20 cas, 20 conformes**. Chaque phrase du tableau de la section 6 de `nadia-prof` et de sa section 5 de `-02` y figure, ecrite avec ses accents par moi. Les menus des refus de `T04-2`, et les cas des refus `X` et des points `C`, sont de ma main : ils valent moins que ses phrases et que le corpus, et je ne les compte pas comme preuve sur du materiau reel.

## 6. Ce qui reste lu par `nadia-prof`

Dans le rapport de qualite, chaque garde de la section 0 s'ecrit **garde** pour ce qu'elle refuse. Le reste s'ecrit :

- *"non garde : `FER-3`"* : que les deux taches soient deux operations distinctes item par item, sur `-04` et `-05` ;
- *"non garde : `QMT-CE1-2`"* : l'exactitude de chaque famille hors la pomme de terre, l'existence et le contenu de la source nommee ; chaque fait de `-05` contre son instruction 3.5 ; une duree chiffree ;
- *"non garde : `CYC-1`"* : `cm1-sciences-corps`, `cm1-sciences-etats-matiere` ;
- *"non garde : `NRC-CE1-1`"* au-dela du texte : qu'un item de temperature ne se resolve pas avec les indices de `cp-qlm-etats-eau` ;
- *"non garde : `BIA-4`"* et *"non garde : `FAL-CE1-2`"* dans leurs paraphrases ; *"non garde : `TRV-CE1-6`"* ;
- *"non garde : `TRV-CE1-5`"* : qu'un indice soit une methode, la definition d'une famille ou de la vapeur dans un indice ;
- *"non garde : `TRV-CE1-2`"* : la plausibilite de chaque distracteur, le distracteur que la situation produit, l'etat que la temperature rend impossible ;
- *"non garde : `ETE-CE1-2`"* : la buee presentee comme vapeur hors d'une phrase, une temperature sans *degre* ; la regle 2 de `-05` (glace et eau ensemble, ou 0 degre).

## 7. Conflits et points a trancher

| Code | Point | Ce que je fais en attendant |
|---|---|---|
| `C1` | **Regle 4 contre regle 8 de `-05`.** La regle 8 refuse tout item dont la bonne reponse n'est ni la vapeur, ni l'eau liquide, ni la fonte, ni le gel, ni la glace : *"rien ne change"* en bonne reponse est refuse. La regle 4 n'admet *rien ne change* en distracteur que si un item l'a pour bonne reponse. Ensemble, *rien ne change* ne peut jamais etre propose, alors que ses deux exemples de 2.2 le proposent. Sa *"forme equivalente"* (*"il reste de la glace"*) passe deja par `GLACE` | aucune forme pour la regle 4. Si `nadia-prof` admet *rien ne change* en bonne reponse de temperature, la tache le reconnait par egalite apres `plat` et la regle 4 se garde par la meme egalite |
| `C2` | La story de `-04` dit encore six familles et *"A au moins 6"* (criteres 1 et 9) ; `nadia-prof` dit cinq et 5 (1.5) | la forme suit `nadia-prof` ; `camille-po` reporte |
| `C3` | *"un menu fait seulement de produits sucres"* : refus garde par `T04-2d` et `T04-2f`, sans assertion propre (2.2) | a confirmer par `nadia-prof` |
| `C4` | `T05-2a` refuse une seule proposition *solide* ou *liquide*, pas seulement le couple ; `T05-2c` refuse toute question *"Comment s'appelle ..."*, y compris sur la vapeur | a confirmer ou a restreindre au couple |
| `C5` | `I1` refuse *de petits insectes* et *d'autres insectes*, du francais correct que sa liste fermee n'admet pas | a confirmer ; si elle les admet, `de ` et `d'` entrent dans la liste fermee |

## 8. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A1` | `G1` a `G3` dans le test du paquet de `-04`, `G4` a `G6` dans celui de `-05`, avec chaque paquet, dans le commit du premier jet ; deux lignes de compte par en-tete ; liste des aliments et `FAL-CE1-1 aliment :` pour `-04` | `marc-dev` |
| `A2` | Jeux fautifs : chaque contre-exemple des sections 2 et 3, les phrases de `nadia-prof` a l'identique ; les refus de comptes construits sur le paquet reel ; chaque sonde jugee seule (`hasSize(1)`) et avec son message propre | `marc-dev` |
| `A3` | `G7` : la methode a part, ses sondes, dans `PaquetCe1QlmCeQueMangentLesAnimauxTest` ; le classement (ce lot ou un suivant) est a `camille-po` | `marc-dev`, `camille-po` |
| `A4` | Confirmer ou retirer `X1` a `X5` ; trancher `C1`, `C3`, `C4`, `C5` | `nadia-prof`, par `sofia-sm` |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
