# s57 - arbitrage avant les premiers jets de `-04` (`ce1-qlm-familles-d-aliments`) et `-05` (`ce1-qlm-eau-trois-etats`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26, debut 16:28:17 (UTC+01:00), lue par `date` ; fin en section 7.
- **Arbre** : branche `s57-p5-nadia-arb0405`, base `round-two` a `649f517b`, lue par `git rev-parse`.
- **Type** : arbitrage avant premier jet, sur le modele de `avis-nadia-seconde-tache-ce1-qlm-02.md`. Reponses a `N04-1` a `N04-4`
  et `N05-1` a `N05-4` (`docs/backlog/backlog.md`, annotation du 2026-10-26, septieme, sections 3, 4 et 9). **Aucun Done, aucun
  verdict de lot, aucun identifiant de condition neuf, aucune condition neuve.** Les reperes `N04-5` a `N04-8` et `N05-5` a `N05-8`
  sont des questions que j'ajoute, numerotees a la suite de celles de `camille-po`. Je ne modifie ni les stories, ni un paquet, ni
  un test : `camille-po` reporte les textes de la section 5, `theo-archi` decide les formes de la section 6.
- **Audit degrade, dit** : rien a jouer, aucun paquet `ce1-qlm-familles-d-aliments` ni `ce1-qlm-eau-trois-etats` n'existe. Je juge
  des formes de taches et des regles de contenu, pas des items.
- **Lu** : stories `S57-BR-QLM-CE1-04` et `-05` (criteres 1 a 10 et 1 a 11) et l'annotation septieme ;
  `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` (2, 3.4, 3.5) ; `FER-1` a `FER-3` dans leurs mots
  (`ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, section 2) ; `LON-CE1-5`
  (`ARBITRAGE-PREALABLE-s48-francais-ce1.md`) ; mon `arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md` (sections 1, 2) ;
  mon avis sur `-02` ; mes lectures de premier jet de `-01`, `-02`, `-03` ; `DEC-0120` A.273, point 2 ; le referentiel
  `squad/programme/questionner-le-monde/competences-ce1.md` (lignes 30, 31, 55, 56) ; les items des voisins `cp-qlm-hygiene`,
  `cp-qlm-matiere`, `cp-qlm-etats-eau`, `cm1-sciences-corps`, `cm1-sciences-nutrition`, `cm1-sciences-etats-matiere`.

## Resume

| Q | Reponse | En une ligne |
|---|---|---|
| `N04-1` | **oui, precise** | classement et menu ; recompte sur les items ; un item qui n'est ni l'un ni l'autre est refuse |
| `N04-2` | **reformulee** | l'enonce dit a qui sont les menus, les propositions portent leur contenu ; ni menu dans l'enonce, ni `texteSupport` |
| `N04-3` | **oui** | les treize exclus nulle part, menus et indices compris ; liste fermee des aliments admis, chacun avec sa famille et sa source |
| `N04-4` | **oui pour `cm1-sciences-corps`, non pour `cm1-sciences-nutrition`** | le premier porte les aliments varies et le calcium des produits laitiers ; le second ne parle que d'organes |
| `N04-8` | **correction de mon propre ecrit** | la ligne a **cinq** familles, pas six : *viande, poisson et oeufs* en est une seule (section 1.5) |
| `N05-1` | **deux taches** | *vapeur d'eau* (reconnaitre) et *temperature* (relier la fonte et le gel a la temperature) |
| `N05-2` | **oui, deux exemples** | un verre ou flottent un glacon et de l'eau : seule la temperature decide ce qui change |
| `N05-3` | **oui pour toute temperature, non pour un nombre qui n'en est pas une** | toute temperature chiffree autre que 0 degre est refusee ; *deux glacons* n'est pas vise |
| `N05-4` | **oui, les deux passent** | sauf si la buee est presentee comme vapeur, ou si la situation de l'enonce la produit |
| Intrus (`-02`, section 4) | **contredit en partie** | `-04` : non en classement, **possible** en menu ; `-05` : **possible** en *vapeur d'eau* si les deux distracteurs sont de la famille de la buee. Les regles de la section 5 le ferment |
| Cout | **aucun prix fixe par moi** | aucun texte ne nomme ce geste (section 7) |

---

## 1. Lot `-04` (`QLM-CE1-04`)

### 1.1 `N04-1` : les deux taches, le recompte

**Oui, ce sont les deux taches**, et ce sont les deux verbes du libelle, comme `camille-po` l'ecrit.

- **A, classement** : l'enonce nomme un aliment ; les trois propositions sont trois familles ; la bonne reponse est la famille de
  l'aliment.
- **B, menu** : l'enonce montre trois menus ; la bonne reponse est celui qui a strictement le plus de familles.

**Deux operations distinctes (`FER-3`, *"l'operation mentale demandee"*)** : A range un aliment ; B range plusieurs aliments, compte
les familles de chaque menu et compare trois comptes. A ne demande jamais de comparer. **A une condition** : l'enonce de B demande
le menu *le plus varie* et c'est l'indice qui donne la methode (*compter les familles*, `TRV-CE1-5`). Un enonce qui dit *"le plus de
familles"* donne le critere a la place de l'enfant : *reconnaitre un repas varie*, c'est savoir que *varie* veut dire *plusieurs
familles*, et c'est le pas que `cp-qlm-hygiene` (*"manger des aliments varies"*) ne fait pas.

**Le recompte sur les items : oui**, meme motif que `-02` (*"un commentaire qui annonce 4 items de B au-dessus de 2 items de B
passe"*). Une precision de plus que `camille-po` : *"bonne reponse dans les familles = classement, sinon menu"* laisse un trou.
Un item *"Lequel de ces aliments est un feculent ?"* a pour bonne reponse un aliment : il compterait pour B sans etre un menu. La
regle se lit donc dans les deux sens : un item dont la bonne reponse est une famille et dont les trois propositions sont trois
familles compte pour A ; un item dont les trois propositions sont trois menus compte pour B ; **tout autre item est refuse**. Je ne
garde pas la forme *"lequel de ces aliments"* : avec deux distracteurs de la meme famille, elle produit la structure de l'intrus
(section 4).

**Comptes** : A porte au moins **5** items (`FER-1`, une famille par item au moins, section 1.5), B au moins 3.

### 1.2 `N04-2` : ou les menus sont montres

**Reformulee.** Les propositions portent le contenu des menus ; l'enonce dit a qui ils sont. Trois menus de trois aliments dans
l'enonce depassent `LON-CE1-1` des que la question s'y ajoute, et `texteSupport` est interdit (`TRV-CE1-1`).

**Correction de mon propre ecrit.** J'ai ecrit *"trois menus nommes, d'un personnage ou de la cantine"*. *Nommes* ne voulait pas
dire qu'une proposition porte un nom : `LON-CE1-5` (six mots) ne le permettrait pas a cote de quatre aliments. Il voulait dire que
**l'item dit a qui sont les menus**, la cantine ou un personnage, et jamais l'enfant (`BIA-4`). Le texte de la regle est en 5.1.

### 1.3 `N04-3` : les exclus dans un menu et en distracteur

**Oui, partout** : enonce, contenu d'un menu, indice. Motif : dans un menu, un aliment a deux familles defendables rend le compte
du menu defendable de deux facons, donc deux menus peuvent etre a egalite ou s'inverser, et l'item a deux reponses (`TRV-CE1-2`,
*"une seule acceptable"*). Dans un indice, il donne un exemple faux. En distracteur de A, les propositions sont des familles :
la question ne s'y pose pas.

**Ce que la liste des treize ne fait pas** : elle nomme ce qui est exclu, pas ce qui est admis. `QMT-CE1-2` demande la source de
*"la famille d'un aliment"* : la consequence est une **liste fermee des aliments admis**, dans le commentaire, chacun avec sa
famille et sa source, et tout aliment d'un item y figure. C'est la liste des animaux de `-02`, pour la meme raison. Un aliment
dont la source ne tranche pas n'y entre pas : c'est le motif de `FAL-CE1-1` (*"deux familles sont defendables selon la source"*),
pas une exclusion neuve.

### 1.4 `N04-4` : les voisins du CM1

- **`cm1-sciences-corps` : oui**, je le lis a l'audit (`CYC-1`). Il porte *"des aliments varies"* (bonne reponse, ligne 67), le
  calcium *"contenu dans le lait et dans le fromage"* (ligne 98), les fibres *"des fruits, des legumes et des cereales
  completes"* (ligne 106) et les boissons sucrees. Un item de CE1 qui rangerait le fromage hors des produits laitiers le
  contredirait.
- **`cm1-sciences-nutrition` : non.** Ses items portent sur l'estomac, les poumons et le coeur et sur leurs fonctions ; aucun ne
  nomme une famille ni un repas. Il n'y a rien a contredire.

La story ajoute `cm1-sciences-corps` a ses voisins ; `cp-qlm-hygiene` et `cp-qlm-matiere` y restent (ma table de V3 donnait
`cp-qlm-matiere` pour la forme *classer*).

### 1.5 `N04-8`, que j'ajoute : cinq familles, pas six

**Correction de mon propre ecrit, datee du 2026-10-26, avant tout premier jet.** `FAL-CE1-1` dit *"Les six familles de la
ligne"*, et la story le reprend (criteres 1 et 9 : *viande* et *poisson et oeufs* separees). Le libelle dit *"(fruits et legumes,
feculents, produits laitiers, viande, poisson et oeufs, produits sucres)"*. J'ai compte les virgules. La classification enseignee
a l'ecole regroupe **viande, poisson et oeufs en une seule famille** : ce sont des aliments qui apportent la meme chose, et aucune
classification que je connaisse ne met la viande d'un cote et le poisson avec les oeufs de l'autre. Les deux familles que la
classification usuelle ajoute et que la parenthese n'a pas, les matieres grasses et les boissons, sont celles d'ou viennent mes
exclusions du beurre, de la creme et du jus de fruits : la parenthese se lit avec cinq familles.

Ce que six familles produiraient : un item *"Dans quelle famille range-t-on l'oeuf ?"* avec *viande* en distracteur aurait une
seconde reponse defendable selon toute source qui suit la classification usuelle. C'est une erreur de fait, et j'en suis l'auteure.

**Ce qui change** : cinq familles en bonne reponse (`FER-1`), *viande, poisson et oeufs* etant l'une d'elles ; A porte au moins 5
items. **Ce qui ne change pas** : le texte de 3.4 n'est pas reecrit ; cette correction le precise, et la source nommee dans le
commentaire (section 5.1, regle 5) doit porter les cinq familles. Le libelle du referentiel n'est pas faux, il est ambigu : je le
signale a `camille-po` par `sofia-sm`, sans demander de le changer (*"un mot change, la ligne n'est plus la mienne"*).

### 1.6 `N04-5` a `N04-7`, que j'ajoute (A.273, defauts a, b, c)

- **`N04-5`, defaut a (indice qui donne la reponse)**. Le risque est propre a cette ligne : les noms des familles contiennent les
  mots qui les designent (*lait*, *sucre*, *fruit*, *legume*, *viande*, *poisson*, *oeuf*). Un indice *"Pense a ce avec quoi on
  fabrique le yaourt"* designe le lait, qui est dans *produits laitiers* : c'est la periphrase de mes lectures de `-02` et de
  `-03`. De meme, un aliment d'enonce qui porte un mot d'une famille (*le poisson*, *l'oeuf*, *la pomme* face a *fruits*) donne la
  reponse par les propositions. `QMT-CE1-1` demande deja qu'un mot difficile (*feculents*) soit explique par des exemples : ces
  exemples sont d'autres aliments, jamais l'aliment de l'enonce. Regles 6 et 7 en 5.1.
- **`N04-6`, defaut b (distracteur ecarte sans la competence)**. En A, ecarter *produits laitiers* pour la pomme de terre, c'est
  appliquer le critere de la famille : c'est la competence, pas un defaut. En B, le defaut existe : un menu de deux aliments face a
  deux menus de quatre s'ecarte par sa longueur ; un menu fait seulement de produits sucres s'ecarte parce qu'il *"n'est pas un
  vrai repas"*, ce qui est le jugement que `FAL-CE1-2` interdit (*"aucun menu n'est qualifie de mauvais"*). Regle 3 en 5.1.
- **`N04-7`, defaut c (source circulaire)**. La story dit que le paquet *"declare la famille de chaque aliment avec sa source, que
  le test relit"*. Si la source est la table du test, ou un fichier de moi qui n'a pas encore ete ecrit, c'est le defaut de `-02` et
  de `-03`. La source est un document hors du depot, nomme (titre et organisme), qui porte les cinq familles. Je n'en cite aucune
  ici : je n'en ai relu aucune pour ce lot, et je ne cite pas comme source ce que je n'ai pas relu. Regle 5 en 5.1.

---

## 2. Lot `-05` (`QLM-CE1-05`)

### 2.1 `N05-1` : les deux taches

**Deux taches, et ce sont les deux verbes du libelle** (*reconnaitre l'eau sous ses trois etats*, *relier la fonte et le gel a la
temperature*). `FER-3` dans ses mots : *"Reconnaitre et transformer sont deux taches."*

- **A, vapeur d'eau** : l'item porte sur l'eau qu'on ne voit pas. La bonne reponse est *la vapeur d'eau* ; ou, si l'enonce nomme
  la buee, le nuage, le brouillard ou la fumee blanche d'une casserole, *de l'eau liquide* (ce que l'on voit, ce sont des gouttes,
  `ETE-CE1-2`). L'enfant reconnait un etat dans une situation.
- **B, temperature** : l'enonce donne une temperature ou un changement de temperature (on chauffe, on met au congelateur, au
  soleil, sous ou au-dessus de 0 degre) ; la bonne reponse est le changement (*la fonte*, *le gel*, en nom ou en verbe) ou l'etat
  qu'il produit (*la glace*, *l'eau liquide*). L'enfant prevoit ce que la temperature fait a l'eau.

**Pourquoi la glace et l'eau liquide sont en B** : ma section 2 de l'arbitrage avant ouverture les admet en bonne reponse *"dans
un item ou un changement de temperature produit cet etat, jamais solide ou liquide ? seul"*. Les reconnaitre sans temperature,
c'est l'item de `cp-qlm-etats-eau`.

**Recompte sur les items**, comme `-02` : bonne reponse *la vapeur d'eau*, A ; bonne reponse *de l'eau liquide* et enonce qui
nomme l'un des quatre, A ; bonne reponse parmi les formes de la fonte, du gel, de la glace, de l'eau liquide, B ; **tout autre
item est refuse**. La moins servie porte au moins 3 items. Le tiers de `ETE-CE1-1` (critere 9) se compte sur la seule bonne
reponse *la vapeur d'eau*, pas sur A.

### 2.2 `N05-2` : un item admissible dont la bonne reponse est la fonte ou le gel

**Ce que `cp-qlm-etats-eau` a deja appris.** Ses deux indices de nommage disent : *"C'est ce qui arrive a un glacon laisse dehors
quand il fait chaud"* (fondre) et *"C'est ce qui arrive a l'eau laissee dehors quand il fait tres froid"* (geler). Un item de CE1
qui pose un seul etat et *"il fait chaud"* ou *"il fait tres froid"* se resout avec ces indices, sans rien de plus : il sert la
ligne de CP et ne compte pas (`NRC-CE1-1`, sur le sujet ; c'est ce que ma section 2 renvoyait *"a ma lecture, item par item"*).

**Ce que le CE1 ajoute** : la temperature decide, et l'etat ne la remplace pas. Quand l'enonce ne pose qu'un etat, un distracteur
*elle fond* pour de l'eau liquide s'ecarte par l'etat, ce que le CP a appris, et l'item devient un choix entre deux (defaut b). La
forme qui tient les deux : **la glace et l'eau liquide ensemble dans ce que l'enonce decrit**, et seule la temperature dit laquelle
change.

Deux exemples (ecrits sans accents ici ; dans le paquet, avec leurs accents, relus par le `relecteur-pedagogique`) :

| Bonne reponse | Enonce | Bonne reponse ; distracteurs |
|---|---|---|
| le gel | *"Un glacon flotte dans un verre d'eau. On met le verre au congelateur. Que se passe-t-il ?"* | *l'eau gele* ; *le glacon fond*, *rien ne change* |
| la fonte | *"La mare est gelee en surface. Le soleil la rechauffe au-dessus de 0 degre. Que se passe-t-il ?"* | *la glace fond* ; *l'eau gele*, *rien ne change* |

Comptes a la main, a recompter par `PaquetLu.mots` : 16 et 17 mots, trois phrases, la derniere est la question (`LON-CE1-1`,
`-2`) ; propositions de 3 mots au plus (`LON-CE1-5`). La bonne reponse n'est pas la plus longue des trois dans aucun des deux
[calcul a la main]. La meme forme sert la glace et l'eau liquide en B : *"Le lendemain, que trouve-t-on dans le verre ?"*,
*seulement de la glace* ; *seulement de l'eau liquide*, *de la glace et de l'eau* (six mots).

**Une limite, que je nomme pour qu'on ne la decouvre pas au jeu** : *rien ne change* qui n'est jamais la bonne reponse devient,
apres quelques manches, un distracteur que l'enfant ecarte sans lire (defaut b). Il n'est propose que si un item du paquet a
pour bonne reponse *rien ne change* ou une forme equivalente (un glacon reste dehors toute une journee sous 0 degre). Regle en 5.2.

### 2.3 `N05-3` : le seul nombre admis

**Oui pour toute temperature.** Ma phrase *"Si un nombre est cite, c'est 0 degre pour la fonte et le gel, et aucun autre"* porte
sur les temperatures. Toute temperature chiffree autre que 0 degre est refusee, en chiffres ou en lettres, positive ou negative
(*100 degres*, *cent degres*, *10 degres*, *moins 5 degres*, *37 degres*). *Sous 0 degre* et *au-dessus de 0 degre* passent : le
seul nombre est 0. **0 degre ne sert que la fonte et le gel** : *"A 0 degre, l'eau devient de la vapeur d'eau"* est faux.

**Non pour un nombre qui n'est pas une temperature** : *deux glacons*, *trois verres* ne sont pas vises par `ETE-CE1-2`. Une
exception, qui n'est pas une regle neuve : une duree chiffree d'un changement (*"la glace fond en dix minutes"*) est un fait qui se
discute et que rien ne source ; `QMT-CE1-2` dit qu'*"un fait sans source n'entre pas"*.

### 2.4 `N05-4` : la buee nommee pour dire qu'elle n'est pas de la vapeur

**Oui, les deux passent.** Ma condition interdit de *presenter* la buee, le nuage, le brouillard et la fumee blanche comme de la
vapeur d'eau, *"ni dans une bonne reponse ni dans un indice"*. Elle ne les interdit pas de l'item. *La buee* en distracteur, a cote
de la bonne reponse *la vapeur d'eau*, est meme le meilleur piege de la ligne : c'est la conception qu'elle doit deplacer. Et
l'item qui demande ce qu'est la buee (*"Apres la douche, le miroir se couvre de buee. De quoi est faite la buee ?"*, *de l'eau
liquide* ; *de la vapeur d'eau*, *de la glace*) enseigne exactement ce que `ETE-CE1-2` protege.

**Deux limites.**

- **La situation de l'enonce ne produit pas le distracteur.** *"L'eau bout dans la casserole. Que devient l'eau qui s'en va ?"*,
  avec *de la fumee blanche* en distracteur : au-dessus de la casserole, une partie de l'eau devient bien cette fumee, et l'item a
  deux reponses (`TRV-CE1-2`). La buee ne va en distracteur que dans une situation qui n'en fait pas.
- **Une phrase de negation dans un indice** (*"La buee n'est pas de la vapeur d'eau"*) est exacte, mais dans un item ou la buee est
  une proposition, elle ecarte le meilleur distracteur et donne la reponse (defaut a). Je la lis ; elle ne doit pas etre refusee
  par la garde de `ETE-CE1-2`, qui ne porte que sur l'exactitude.

### 2.5 `N05-5` a `N05-8`, que j'ajoute

- **`N05-5`, defaut a.** En A, un indice qui dit *"ne se voit pas"* ou *"invisible"* donne la vapeur d'eau par sa definition. En
  B, un indice qui enonce le sens de la temperature (*"le froid fait geler l'eau"*, *"la chaleur fait fondre la glace"*) est la
  definition de la reponse, et c'est l'indice de `cp-qlm-etats-eau`. Une methode compare deux situations (*"Compare le
  congelateur et une journee d'ete."*), comme mes remplacements de `-03`. Regle 5 en 5.2.
- **`N05-6`, defaut b.** En B : section 2.2. En A : un distracteur que la temperature de la situation rend impossible (*de la glace*
  pour le linge qui seche au soleil) s'ecarte sans rien savoir de la vapeur. Le distracteur plausible est la conception a deplacer :
  *l'eau a disparu* (`cm1-sciences-etats-matiere` porte deja *la disparition* en distracteur), ou un element de la famille de la
  buee, un seul par item (section 4). Regles 3 et 4 en 5.2.
- **`N05-7`, defaut c.** Les faits de cette ligne qui se discutent (la buee est de l'eau liquide, la vapeur ne se voit pas, l'eau
  devient vapeur sans bouillir, 0 degre) ont pour source mon instruction, 3.5, hors du paquet et de ses tests : c'est le precedent
  de `-01`, que j'ai accepte en disant que l'exactitude reposait sur ma relecture. `cm1-sciences-etats-matiere` dit la meme chose
  (indices des lignes 88 et 96) et se cite pour `CYC-1`. Jamais le test du paquet comme source.
- **`N05-8`, `ETE-CE1-2`, bouillir.** *"Aucun item ne dit que l'eau ne devient vapeur qu'en bouillant (le linge seche sans
  bouillir)."* Un paquet dont toute vapeur d'eau sort d'une casserole qui bout ne le dit dans aucun item, mais l'enseigne par sa
  repetition. Les items dont la bonne reponse est la vapeur d'eau ne sont pas tous des situations d'ebullition. C'est ma lecture de
  ma condition et de sa parenthese, pas une barre neuve. Regle 6 en 5.2.

---

## 3. Ce que la porte technique ne verra pas, et que je garde seule sur ces deux lots

`QC9`, sans test : l'exactitude de chaque famille contre sa source et de chaque fait de `-05` ; `CYC-1` contre
`cm1-sciences-corps` et `cm1-sciences-etats-matiere` ; `NRC-CE1-1` sur le sujet (le CP resout-il l'item) ; `BIA-4` dans ses
paraphrases ; l'operation mentale de `FER-3` sur chaque item ; la plausibilite de chaque distracteur ; qu'un indice soit une
methode ; les paraphrases de *mauvais* (*le moins bon*, *a eviter*). **Aucune n'est couverte par un test.**

## 4. La structure *bonne reponse = intrus* (mon constat du 2026-10-26, lecture de `-02`, section 4)

`camille-po` dit *non* pour `-04` et `-05`. **Je contredis en partie.**

- **`-04`, A : non**, tant que les trois propositions sont trois familles : trois familles differentes ne forment aucune paire qu'un
  enfant reconnaitrait sans la competence. La forme *"lequel de ces aliments est un feculent ?"* avec deux distracteurs de la meme
  famille la produirait : elle est exclue (1.1).
- **`-04`, B : possible, pas forcee.** Si une famille n'est que dans le menu attendu (seul menu avec un legume), l'enfant choisit
  *"celui qui a des legumes"*, la regle de `cp-qlm-hygiene`, sans compter. Si une famille est dans les deux autres menus et pas
  dans l'attendu (les deux ont un dessert sucre), il choisit *"celui sans sucreries"*, un jugement que `FAL-CE1-2` interdit. Regle
  3 en 5.1.
- **`-05`, A : possible.** Avec deux distracteurs de la famille de la buee (*la buee*, *le brouillard*), les deux se ressemblent
  (blancs, visibles) et *la vapeur d'eau* est l'intrus par la forme. Avec un seul, non. Le cas que `camille-po` n'a pas compte
  (*la vapeur d'eau* parmi *la glace* et *l'eau liquide*) n'est pas la structure de `-02` : ce qui reunit la glace et l'eau
  liquide, *on les voit*, est le contenu meme de la ligne. Je le regarderai en jouant.
- **`-05`, B : non** avec les propositions de 2.2 (un changement, le changement contraire, rien ne change).

---

## 5. Textes a reporter dans les stories

Texte exact, que `camille-po` reporte, avec le marqueur *[arbitre par `nadia-prof` le 2026-10-26,
`arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`]*. Les exemples sont ecrits sans accents ; dans le paquet et dans les tests, ils
s'ecrivent avec leurs accents.

### 5.1 `S57-BR-QLM-CE1-04`

> **Critere 1, remplace.** `FER-1`, les cinq familles : *fruits et legumes*, *feculents*, *produits laitiers*, *viande, poisson et
> oeufs*, *produits sucres*, chacune la bonne reponse d'au moins un item. *Viande, poisson et oeufs* est une seule famille
> (`arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, 1.5). Aujourd'hui : **0 sur 5**.
>
> **Critere 9, `FER-3`, deux taches, confirme et precise.**
> 1. **Tache A, classement** : l'enonce nomme un aliment de la liste ; les trois propositions sont trois familles, ecrites sans
>    article ; la bonne reponse est la famille de l'aliment selon la liste. Aucune proposition de A n'est un aliment.
> 2. **Tache B, menu** : l'enonce dit a qui sont les trois menus, la cantine ou un personnage, et demande lequel est **le plus
>    varie** ; les trois propositions sont les trois menus, chacun une liste d'aliments de la liste separes par des virgules, au
>    plus six mots (`LON-CE1-5`) ; la bonne reponse est le menu qui a strictement le plus de familles. L'indice donne la methode
>    (compter les familles de chaque menu), l'enonce ne la donne pas. Aucun menu n'est dans l'enonce, aucun `texteSupport`.
> 3. **Les trois menus d'un item de B** ont le meme nombre d'aliments ; aucun n'est fait seulement de produits sucres ; chaque
>    famille du menu attendu est aussi dans au moins un des deux autres ; aucune famille n'est dans les deux autres et absente du
>    menu attendu.
> 4. **Le menu d'un personnage** ne suppose ni foyer ni logement (`TRV-CE1-6`) : jamais *"a la maison"*, *"chez lui"*.
> 5. **Liste des aliments** : fermee, dans le commentaire du paquet, chaque aliment avec sa famille et sa source (`QMT-CE1-2`) ;
>    tout aliment d'un enonce, d'un menu ou d'un indice y figure ; aucun des treize exclus de `FAL-CE1-1` n'y figure ni n'apparait
>    dans un champ de l'item. La source est un document nomme hors du depot, qui porte les cinq familles ; jamais le test du
>    paquet, jamais un fichier de `nadia-prof` qui demande une source. Si le developpeur n'en trouve pas, il l'ecrit en toutes
>    lettres.
> 6. **L'aliment de l'enonce de A** ne porte aucun mot d'un nom de famille (*poisson*, *oeuf*, *viande*, *lait*, *fruit*,
>    *legume*, *sucre*).
> 7. **L'indice de A** ne contient ni le nom de la famille attendue, ni un mot de la meme famille de mots (*lait* pour *produits
>    laitiers*, *sucre* pour *produits sucres*), ni une definition de la famille ; il donne d'autres aliments de la liste en
>    exemple, ou une comparaison (`QMT-CE1-1`, `TRV-CE1-5`).
> 8. **Recompte sur les items** : un item dont la bonne reponse est une famille et dont les trois propositions sont trois familles
>    compte pour A ; un item dont les trois propositions sont trois menus compte pour B ; tout autre item est refuse. A porte au
>    moins 5 items, B au moins 3. Le commentaire dit les comptes, le test les relit. Aujourd'hui 0 et 0, **rouge**.
>
> **Voisins, ajoute** : `cm1-sciences-corps` (`CYC-1`).

### 5.2 `S57-BR-QLM-CE1-05`

> **Critere 10, `FER-3`, deux taches, ecrit.**
> 1. **Tache A, vapeur d'eau** : la bonne reponse est *la vapeur d'eau* ; ou l'enonce nomme la buee, le nuage, le brouillard ou la
>    fumee blanche d'une casserole et la bonne reponse est *de l'eau liquide*.
> 2. **Tache B, temperature** : l'enonce donne une temperature ou un changement de temperature ; la bonne reponse est la fonte ou
>    le gel (nom ou verbe : *la glace fond*, *l'eau gele*), ou l'etat que la temperature produit (*la glace*, *l'eau liquide*).
>    Ce que l'enonce decrit contient de la glace et de l'eau liquide ensemble (un glacon dans un verre d'eau, une mare gelee en
>    surface), ou cite 0 degre. Aucune proposition n'est un changement que l'etat decrit rend impossible.
> 3. **Distracteurs de A** : la conception a deplacer (*l'eau a disparu*) ou un element de la famille de la buee, **un seul par
>    item** ; jamais un element que la situation de l'enonce produit (la fumee blanche pour une casserole qui bout, la buee pour un
>    miroir apres la douche) ; jamais un etat que la temperature de la situation rend impossible (la glace au soleil).
> 4. **Rien ne change** n'est propose en B que si au moins un item du paquet l'a pour bonne reponse.
> 5. **Indices** : en A, aucun indice ne dit que la vapeur d'eau ne se voit pas ou est invisible ; en B, aucun indice n'enonce le
>    sens de la temperature (*le froid fait geler*, *la chaleur fait fondre*). Un indice compare deux situations.
> 6. **Les items dont la bonne reponse est la vapeur d'eau** ne sont pas tous des situations ou l'eau bout (`ETE-CE1-2`).
> 7. **Source** (`QMT-CE1-2`) : l'instruction de `nadia-prof`, 3.5 (`ETE-CE1-2`), et `cm1-sciences-etats-matiere` pour `CYC-1`.
> 8. **Recompte sur les items** : bonne reponse *la vapeur d'eau*, A ; bonne reponse *de l'eau liquide* avec un des quatre elements
>    de la buee nomme dans l'enonce, A ; bonne reponse de la fonte, du gel, de la glace ou de l'eau liquide, B ; tout autre item
>    est refuse. La moins servie porte au moins 3 items. Aujourd'hui 0 et 0, **rouge**.
>
> **Critere 6, precise** : toute temperature chiffree autre que 0 degre est refusee, en chiffres ou en lettres ; 0 degre ne
> figure que dans un item de fonte ou de gel ; un nombre qui n'est pas une temperature n'est pas vise.
>
> **Critere 4, clause ajoutee** : *la buee* en distracteur a cote de la bonne reponse *la vapeur d'eau*, et l'item qui demande de
> quoi est faite la buee (bonne reponse *de l'eau liquide*), **passent**.

---

## 6. Ce que je demande que chaque garde refuse et laisse passer (forme a `theo-archi`)

La forme de chaque garde, et le choix d'en faire un test ou une lecture, sont a `theo-archi`. Une garde qu'il ne juge pas possible
s'ecrit *"non garde : <identifiant>"*, et je lis.

| Garde | Doit refuser | Doit passer |
|---|---|---|
| `T04-1`, recompte | un paquet dont tous les items sont A ; B a 2 items ; A sans *viande, poisson et oeufs* en bonne reponse ; un item *"Lequel de ces aliments est un feculent ?"*, bonne reponse *les pates* ; un item de A dont une proposition est un aliment | un paquet dont A porte les cinq familles et B trois menus |
| `T04-2`, declaration des aliments et des menus | la pomme de terre declaree *fruits et legumes* ; un aliment d'un item absent de la liste ; une bonne reponse de A qui n'est pas la famille declaree de son aliment ; un menu attendu qui n'a pas strictement plus de familles que chacun des deux autres (egalite comprise) ; trois menus de tailles differentes ; une famille presente dans le seul menu attendu ; une famille presente dans les deux autres et absente de l'attendu ; un menu fait seulement de produits sucres | le menu attendu *riz, poisson, carottes, yaourt* (4 familles) face a *pates, pain, poulet, pomme* et *pates, pain, haricots verts, fromage* (3 chacun) |
| `T04-3`, exclus et formes | chacun des treize exclus dans un enonce, un menu ou un indice ; *Pourquoi manger varie ?* ; l'item de `cp-qlm-hygiene` ; *Quel menu est mauvais pour la sante ?* ; *Qu'as-tu mange ce midi ?* ; l'indice *Pense a ce que tu manges a midi.* ; un enonce de A dont l'aliment porte un mot d'un nom de famille (*le poisson*) ; un indice de A qui contient le nom ou un mot de la famille attendue (*Pense au lait.* pour le yaourt) | *fruits et legumes* (la garde de *fruits a coque* et de *jus de fruits* ne le touche pas) ; la conjonction *mais* (si la forme distingue *maïs*) ; l'indice *Compte les familles de chaque menu.* (un imperatif n'est pas une question sur l'enfant) ; l'indice *Pense aux pates et au riz.* pour la pomme de terre |
| `T05-1`, recompte et tiers | un paquet dont tous les items sont A ; B a 2 items ; un item de B dont la bonne reponse est la vapeur d'eau ; un item dont la bonne reponse est *de l'eau liquide* sans aucun element de la buee dans l'enonce et sans temperature ; moins d'items a la vapeur d'eau que le tiers du critere 9 | les deux exemples de 2.2 comptes pour B ; l'item de la buee de 2.4 compte pour A |
| `T05-2`, ce que le CP a servi | un item dont les propositions sont *solide* et *liquide* ; un enonce *"... solide ou liquide ?"* ; un enonce *"Comment appelle-t-on le passage ..."* ou *"Comment s'appelle ..."* | *"Un glacon flotte dans un verre d'eau. On met le verre au congelateur. Que se passe-t-il ?"* |
| `T05-3`, buee, bouillir, nombre, intrus | *La buee sur la vitre, c'est de la vapeur d'eau.* ; la bonne reponse *de la vapeur d'eau* a *De quoi est faite la buee ?* ; un indice *Le nuage, c'est de la vapeur d'eau.* ; *On voit la vapeur d'eau sortir de la casserole.* ; *L'eau ne devient de la vapeur que quand elle bout.* ; *100 degres*, *cent degres*, *10 degres*, *moins 5 degres* ; *0 degre* dans un item dont la bonne reponse est la vapeur d'eau ; un item de A dont les deux distracteurs sont de la famille de la buee ; en A, un indice qui contient *invisible* ou *ne se voit pas* | *la buee* en distracteur a cote de *la vapeur d'eau* ; *La buee, ce sont de toutes petites gouttes d'eau.* ; *L'eau qui bout dans la casserole devient de la vapeur d'eau.* ; *au bout de quelques heures* (le mot *bout* n'est pas *bouillir*) ; *0 degre*, *zero degre*, *sous 0 degre* ; *deux glacons* ; un enonce *On ne voit plus la flaque.* (une situation, pas un indice) |

**Ce qui reste a ma lecture quelle que soit la forme** : section 3 ; et, en B de `-05`, qu'un item ne se resolve pas avec les indices
de `cp-qlm-etats-eau` (2.2).

---

## 7. Cout et duree

**Au bareme.** Aucun texte ne nomme un arbitrage avant premier jet : le bareme compte 1,0 par lot, *"lecture avant `verify` et
correction qui la suit comprises"*, et un fixe de 1,0 par instance et par palier (arbitrage 0,5, marge 0,5) ; mon avis sur `-02`,
le meme geste, ne portait aucun prix. **Je ne fixe aucun prix a ce document et je n'en deduis aucun seuil.** Ce n'est pas une
seconde lecture (0,5 hors marge, `DEC-0120` A.270, point 4) : aucun premier jet n'est lu. Le classer est a `camille-po`.

**Ce qu'il vise, sans promesse** : moins de secondes lectures sur `-04` et `-05` (A.273, point 2). Je n'ecris aucun nombre de
corrections evitees.

**Duree**, lue par `date` : debut **16:28:17**, fin **16:53:29**, lue au commit `84181353`, soit **25 min 12 s** [corrige le 2026-10-26 : la premiere version portait 16:55:10, heure ecrite avant d'etre lue ; texte d'origine dans `84181353`], dont trois executions du
garde-fou des identifiants (section 8). Aucun seuil de duree ne s'applique a ce geste.

## 8. Verifications avant commit

Ce fichier present dans l'arbre, sur la base `649f517b`. Il n'alloue aucun identifiant de condition : `N04-5` a `N04-8` et `N05-5`
a `N05-8` sont des reperes de question, de la meme forme que ceux de `camille-po`.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, **telle
  quelle** : *"BUILD FAILURE"*, sur le module `recreo-domain` : *"No tests matching pattern
  "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were executed! (Set -Dsurefire.failIfNoSpecifiedTests=false to ignore
  this error.)"*. Le garde-fou n'a pas ete lance : `-am` construit d'abord des modules qui n'ont pas ce test. Ce n'est pas un
  verdict sur ce fichier.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*
- **Signale a `sofia-sm`, symptome** : mes lectures de `-02` et de `-03` rapportent la commande telle quelle en *"BUILD SUCCESS"* ;
  aujourd'hui, sur cette base, elle echoue sans le drapeau. Je ne cherche pas la cause.

## 9. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | section 5 (textes des criteres 1, 9 de `-04` ; 4, 6, 10 de `-05` ; voisin de `-04`) ; 1.5 (cinq familles, ambiguite du libelle, par `sofia-sm`) ; section 7 (classer le cout) |
| `theo-archi` | section 6 (`T04-1` a `T04-3`, `T05-1` a `T05-3`) |
| `marc-dev`, par `sofia-sm` | rien avant que `camille-po` ait reporte la section 5 ; son autocontrole A.273 lit ensuite ces regles |

## 10. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant de condition, ne
modifie ni story, ni paquet, ni test, ni catalogue, ni `DEC`, ni matrice, ni registre, ni l'instruction de s57. La correction de
1.5 ne reecrit pas 3.4 de l'instruction : elle la precise ici, datee.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
