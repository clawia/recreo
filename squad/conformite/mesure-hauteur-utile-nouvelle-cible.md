# Mesure - hauteur utile d'un navigateur de bureau, et rendu des synonymes a la largeur tablette

- **Auteur** : `nadia-prof`.
- **Date** : 2026-08-22.
- **Worktree dedie** : branche `s17-nadia-hauteur-utile`, forkee de `round-two` a **`db2bff6`**
  (SHA releve par moi sur le worktree neuf, `git rev-parse round-two`, avant tout travail).
- **Motif** : `ines-design` etablit dans
  `squad/sprints/s17-francais-cm1-et-dette/proposition-ines-cible-ordinateur-et-tablette.md`
  (branche `s17-ines-cible-ordinateur-tablette`, non fusionnee) que les valeurs d'appareils de s10
  sont des dimensions de dalle et non des hauteurs utiles apres le navigateur. Elle nomme sa propre
  estimation de la chrome de bureau (110 a 160 px) comme le point le plus fragile de son document et
  demande qu'elle soit verifiee par une mesure sur navigateur reel. C'est ce que fait ce fichier.

## Ce que ce fichier est, et ce qu'il n'est pas

**Ce qu'il est** : deux faits mesures, avec leur methode et leur portee.

**Ce qu'il n'est pas**, et je le dis en tete parce que le contraire se lira vite :

- **Ce n'est pas un audit de jeu et ce n'est pas une fiche de conformite.** Il ne vit pas dans
  `FICHE-bandit-manchot-synonymes-cm1.md`, exactement pour qu'il ne puisse pas se lire comme
  voisinant mon refus du 2026-08-21, ni comme le corrigeant.
- **Aucun Done pedagogique n'est prononce ici, ni ouvert, ni prepare.**
- **Aucune de mes conditions n'est levee ni durcie.** `SYN-5` et `SYN-10` restent dans les termes ou
  je les ai ecrites, contre la reference contre laquelle elles ont ete prononcees. Mon refus du
  2026-08-21 reste en vigueur, et rien dans ce fichier ne le rejuge.
- **Je ne fixe aucune reference produit.** Fixer la hauteur et la largeur de reference est une
  decision de `camille-po`. Je lui donne un chiffre mesure a la place d'une estimation ; le choix
  reste entier.

## 1. Fait mesure - la hauteur qu'un document recoit reellement, sur ce poste

### 1.1 Methode

Navigateur reel, pas une emulation, pas un headless. Chrome et Edge lances chacun sur un **profil
isole** (sans quoi la commande s'attache a une session deja ouverte et ne mesure plus une fenetre
neuve), **sans extension**, **fenetre maximisee**, **zoom a 100 %**, page vide. Lecture par le
protocole de deverminage du navigateur : `window.outerHeight`, `window.innerHeight`,
`window.screen.height`, `window.screen.availHeight`, `window.devicePixelRatio`. Trois lectures
espacees par navigateur, identiques a chaque fois.

**Et je verifie ce que je ne pourrais pas voir depuis la page.** Un navigateur pilote peut afficher
un bandeau d'automatisation, qui coute une quarantaine de pixels et gonflerait le chiffre sans
apparaitre dans aucune de ces valeurs. J'ai donc **capture l'ecran entier du systeme** en meme temps
que la lecture, et compare : le haut de la zone de contenu tombe au pixel ou `innerHeight` le place,
le bas tombe au pixel ou la barre des taches commence. **Aucun bandeau d'automatisation n'etait
present.** C'est la meme precaution que celle qui m'a fait verifier `scrollY` a chaque lecture au
sprint 17, apres avoir attrape un artefact de defilement qui donnait 119,81 px au lieu de 2,81.

### 1.2 Chiffres

Ecran de ce poste : dalle **1920 x 1080** en pixels CSS, `devicePixelRatio = 1` (donc mise a
l'echelle du systeme a 100 %). Windows 11. Chrome **151.0.7922.138**, Edge **151.0.4129.101**.

| Configuration | `innerHeight` | Chrome du navigateur | Barre des taches | **Total retire a la dalle** |
|---|---|---|---|---|
| Chrome, sans barre de favoris | 945 | 87 | 48 | **135 px** |
| Edge, sans barre de favoris | 948 | 84 | 48 | **132 px** |
| Chrome, **avec** barre de favoris | 911 | 121 | 48 | **169 px** |

Lecture des colonnes : chrome du navigateur = `screen.availHeight` moins `innerHeight` (la fenetre
maximisee occupe exactement `availHeight`, verifie : `outerHeight` = 1032 = `availHeight` dans les
trois cas) ; barre des taches = `screen.height` moins `screen.availHeight` ; total = `screen.height`
moins `innerHeight`.

**La hauteur reellement utile sur ce poste est donc de 945 px** (Chrome, sans barre de favoris),
et de 911 px avec une barre de favoris.

### 1.3 Sort de la fourchette d'`ines-design`

**Sa fourchette de 110 a 160 px est confirmee pour la configuration qu'elle a nommee, et debordee
par une configuration qu'elle n'avait pas comptee.**

- **Confirmee** : 135 px sur Chrome, 132 px sur Edge, sans barre de favoris, tombent bien dans
  110-160, et plutot dans la moitie basse. Son ordre de grandeur de metier etait juste.
- **Debordee** : avec la barre de favoris affichee, le total est de **169 px**, soit **9 px au-dessus
  de sa borne haute**. Elle avait explicitement pose "sans barre de favoris" comme hypothese, donc ce
  n'est pas une erreur de sa part ; c'est une hypothese qui ne tient pas sur un poste familial, ou la
  barre de favoris est frequemment affichee et ou personne ne la masque pour reviser.

**Ce que cela donne applique a son pire cas de dalle (720 px de haut)** :

| Configuration | Hauteur utile derivee sur une dalle de 720 px |
|---|---|
| Chrome, sans barre de favoris | **585 px** |
| Edge, sans barre de favoris | 588 px |
| Chrome, avec barre de favoris | **551 px** |

Sa fourchette derivee de 560 a 610 px encadre correctement les deux premieres lignes. La troisieme,
**551 px, tombe sous sa borne basse**.

### 1.4 Ce que vaut ce chiffre, et ce qu'il ne vaut pas

Je nomme la portee, parce que ce chiffre va servir a fixer une reference produit.

- **C'est un poste, pas une population d'appareils.** Une dalle, une version de Windows, deux
  navigateurs, une mise a l'echelle. La ou `ines-design` produit une distribution sourcee sur des
  centaines de milliers de vues, je produis une mesure exacte sur un exemplaire. Les deux ne se
  remplacent pas : sa methode dit ce qui est frequent, la mienne dit ce qui est vrai ici.
- **Les 585 px et les 551 px sont derives, pas mesures.** J'ai mesure la chrome sur une dalle de
  1080 px et je l'ai soustraite d'une dalle de 720 px que je n'ai pas. Je n'ai verifie sur aucune
  dalle de 720 px, ni sur aucun affichage mis a l'echelle a 125 % ou 150 %, que la chrome coute le
  meme nombre de **pixels CSS**. C'est plausible (les barres suivent la densite de l'affichage) et je
  ne l'ai pas constate. **Si quelqu'un cite 585 px, qu'il cite avec cette reserve.**
- **Je n'ai mesure ni macOS, ni Firefox, ni une barre des taches deplacee sur un cote, ni une barre
  des taches en masquage automatique** (qui rendrait 48 px et ferait de l'ordinateur un cas plus
  confortable, pas moins).

## 2. Fait mesure - ou tombent les seize items des synonymes a la nouvelle largeur

### 2.1 Methode

Application servie depuis le fat jar construit sur ce worktree a `db2bff6`. Navigateur reel pilote
par son protocole de deverminage, **viewport impose a 744 x 585**, `deviceScaleFactor` a 1, mode non
mobile. **Jeu au clavier seul** : tabulation jusqu'a l'entree de catalogue du jeu, entree pour
demarrer, puis a chaque manche tabulation jusqu'a la bonne reponse et entree, ce qui fait defiler les
dix manches d'une partie.

**Seize parties, 160 tirages.** Les seize items ont ete vus chacun **7 a 14 fois**. Le chiffre retenu
par item est **son pire tirage observe**, comme `SYN-10` l'exige.

**`scrollY` verifie avant chaque lecture : 0 lecture a `scrollY` non nul sur 160.**

**Une precaution qui a change le resultat, et que je note parce qu'elle se represente.** Mes
premieres tentatives de jeu au clavier ne deplacaient pas le focus : l'element focalise restait le
corps du document, tabulation apres tabulation. La page n'avait pas le focus du systeme, et le
clavier ne mesurait rien. Une sonde qui rend un chiffre sans jamais avoir bouge le focus n'aurait pas
echoue, elle aurait rendu un chiffre faux en silence. Corrige, puis verifie en listant l'element
focalise a chaque tabulation avant de mesurer quoi que ce soit.

### 2.2 Le fait qui commande tout le reste - **la largeur ne devient pas 744 px**

**Mesure, et c'est le chiffre le plus important de ce fichier** : a un viewport de 744 px, la zone de
contenu `#jeu` fait **540 px** et le conteneur des propositions **500 px**. Pas 744. La `container`
de Bootstrap plafonne a 540 px sur toute la bande de viewport **576 a 767 px**.

Consequences, toutes mesurees :

- La largeur de rangee passe de **320 px** (a 360 px de viewport, ou j'ai mesure le 2026-08-21) a
  **500 px**. C'est un gain reel de 180 px, mais **ce n'est pas le gain de 384 px** qu'une lecture
  directe de "744 px de large" laisserait attendre.
- **Ma mesure a 744 px vaut pour toute la bande 576-767 px.** Elle couvre donc aussi bien l'iPad mini
  a 744 px que les tablettes Android a 600 px qu'`ines-design` a identifiees puis ecartees pour cause
  d'anciennete : elles rendent la meme largeur de rangee. C'est une portee plus large que ce que
  j'attendais, et elle est favorable a la mesure.
- **La mise en page est un escalier, pas une pente.** A **768 px** de viewport, 24 px de plus, le
  seuil `md` de Bootstrap fait passer la zone de contenu a **720 px** et la rangee a **680 px**.
  **Une reference fixee a 744 px place donc le produit 24 px sous une marche qui change tout.**
  Mesure a 768 px, meme methode, 4 parties : **les seize items passent sur une rangee**, la consigne
  tombe de 2 lignes a 1, et le bas de la derniere proposition passe de 531,19 a **411,19** ou
  **363,19** selon la longueur de la phrase (`releves-mesure-hauteur-utile/768x585-triste-une-rangee.png`).

### 2.3 Les seize items a 744 px de large, au pire tirage

Une rangee coute 90 px, le chiffre que j'avais etabli le 2026-08-20 et qui ne bouge pas.
`besoin` = somme des largeurs intrinseques des trois etiquettes plus les deux ecarts de 16 px ;
`dispo` = 500 px.

| Rangees | Pire bas | Tirages | Besoin de largeur | Ecart a 500 px | Mot cible |
|---|---|---|---|---|---|
| 2 | 531,19 | 9 | 538,15 | **-38,15** | facile |
| 2 | 531,19 | 8 | 537,64 | **-37,64** | contente |
| 2 | 531,19 | 7 | 526,14 | **-26,14** | difficile |
| 2 | 531,19 | 12 | 516,80 | **-16,80** | triste (l'item pilote) |
| 2 | 531,19 | 7 | 514,11 | **-14,11** | calme |
| 1 | 441,19 | 13 | 498,49 | **+1,51** | gentil |
| 1 | 441,19 | 8 | 489,24 | +10,76 | courageux |
| 1 | 393,19 | 12 | 484,29 | +15,71 | petite |
| 1 | 441,19 | 7 | 478,03 | +21,97 | fort |
| 1 | 441,19 | 11 | 477,21 | +22,79 | fatigue |
| 1 | 393,19 | 7 | 470,52 | +29,48 | propre |
| 1 | 441,19 | 9 | 448,36 | +51,64 | grand |
| 1 | 441,19 | 12 | 432,18 | +67,82 | malin |
| 1 | 441,19 | 14 | 431,15 | +68,85 | gaie |
| 1 | 441,19 | 11 | 415,97 | +84,03 | douce |
| 1 | 441,19 | 13 | 358,94 | +141,06 | beau |

**Reponse a la premiere question posee : non, les trois etiquettes ne tiennent pas toutes sur une
rangee a cette largeur. Onze items sur seize tiennent sur une rangee, cinq restent a deux rangees**,
en manquant de 14 a 38 px. **Et un des onze tient a 1,51 px** (le mot cible "gentil").

**Reponse a la seconde question : le pire bas de tous les items est 531,19 px.** Voici ou il tombe,
selon la hauteur retenue :

| Pli | Origine du pli | Marge du pire item | Items sous le pli |
|---|---|---|---|
| 945 px | hauteur utile mesuree sur ce poste (dalle 1080, Chrome, sans favoris) | +413,81 px | **0 sur 16** |
| 585 px | derive : dalle 720 moins 135 px mesures (Chrome, sans favoris) | +53,81 px | **0 sur 16** |
| 551 px | derive : dalle 720 moins 169 px mesures (Chrome, **avec** favoris) | +19,81 px | **0 sur 16** |

**Verifie et non suppose : la position de la derniere proposition ne depend pas de la hauteur du
viewport.** Mesure aux trois hauteurs 945, 585 et 551 px, trois parties chacune, le jeu de valeurs
observees est identique au centieme (393,19 / 441,19 / 531,19). La feuille de style ne contient
aucune unite relative a la hauteur du viewport. C'est ce qui autorise a appliquer les trois plis aux
memes chiffres, plutot qu'a extrapoler.

**Ce que l'enfant voit, au pli le plus severe que j'ai mesure**
(`releves-mesure-hauteur-utile/744x551-triste-deux-rangees.png`, item pilote sur le mot "triste",
celui qui etait 87 px sous le pli le 2026-08-21) : score, consigne sur 2 lignes, phrase sur 2 lignes,
et **les trois propositions entieres**, la troisieme comprise, sur la seconde rangee. Le bas du cadre
du cabinet est effleure : le socle se termine 3,81 px au-dessus du pli, le bord bas du cadre le
depasse de 4,19 px.

**Un fait annexe que je note sans en tirer de verdict** : sur les cinq items a deux rangees, la page
depasse le pli meme sans message de retour (hauteur de document 587 px pour un pli a 585) et une
barre de defilement apparait. Ce qui depasse n'est pas une proposition, c'est le bas du meuble puis
le bloc de retour juste/faux. **Un enfant qui repond faux sur un de ces cinq items ne verra donc pas
le message qui lui dit pourquoi sans defiler.** Ce n'est pas l'objet de `SYN-5`, qui porte sur les
propositions ; je le nomme parce que je l'ai vu en jouant et que personne d'autre ne le verra.

### 2.4 Ce que je corrige de mon propre constat

**Le 2026-08-21, j'ai etabli que "le comportement d'un item face au pli est une propriete du couple
(item, tirage)" et j'en ai fait `SYN-10`.** A 360 px de large, cinq items sur seize montraient deux
nombres de rangees selon le tirage, et c'est ce qui avait rendu ma mesure du 2026-08-20 fausse.

**A 744 px de large, sur 160 tirages, aucun des seize items ne varie.** Chaque item rend le meme
nombre de rangees a chaque tirage, avec 7 a 14 tirages par item.

Je dis precisement ce que cela veut dire et ce que cela ne veut pas dire. **Cela ne rend pas `SYN-10`
inutile ni faux** : la variabilite etait bien reelle a 320 px de rangee, et sa cause est comprise -
quand la somme des etiquettes est tout juste au-dela de la rangee, l'ordre du tirage decide laquelle
bascule. A 500 px, les cinq items qui debordent debordent de 14 a 38 px, ce qui est trop pour
qu'aucun ordre ne les rattrape, et les onze qui tiennent tiennent de 1,51 a 141 px. **La variabilite
n'a pas disparu, elle a ete quittee** - et l'item a +1,51 px dit exactement a quelle distance on est
d'y retomber. `SYN-10` reste ce qu'elle est : une regle de methode, qui vaut quelle que soit la
largeur, et qui est ce qui m'a fait faire 160 tirages plutot qu'un.

## 3. Ce que je refuse de conclure

1. **Je ne conclus pas que `SYN-5` est tenue, et ce fichier n'est pas une levee de `SYN-5`.** Il dit
   ou tombent seize items sur un ecran, ce qui est une donnee pour une decision, pas un verdict.
   `SYN-5` a ete prononcee contre la reference de `DEC-0016` et se mesure contre la reference que
   `camille-po` fixera. Tant qu'aucune reference n'est fixee, il n'y a rien contre quoi la mesurer,
   donc rien a lever. **Un refus ne se leve pas par une redefinition de la reference qui l'a
   produit**, et il ne se leve pas davantage par une mesure faite avant que la reference existe.
2. **Je ne conclus pas que 744 px est une largeur sure.** J'ai mesure un **viewport** de 744 px dans
   Chrome sur Windows. Je n'ai pas mesure un iPad mini, et je n'en ai pas. La pile de polices du
   produit commence par `system-ui, -apple-system, "Segoe UI"` : elle resout vers **Segoe UI** ici et
   vers **San Francisco** sur iOS. Les largeurs intrinseques du tableau 2.3 sont des largeurs Segoe
   UI. **L'item a +1,51 px de marge peut basculer d'une police a l'autre**, et je ne peux pas dire de
   quel cote. Tout ce tableau est a refaire sur un iPad reel avant qu'on s'appuie sur ses marges
   fines.
3. **Je ne conclus pas que l'ordinateur est le pire cas de hauteur de l'ensemble**, ni le contraire.
   Ma mesure va dans le sens de la conclusion d'`ines-design` (585 ou 551 px derives, contre 744 px
   au minimum pour une tablette couchee), mais elle repose sur une dalle de 720 px que je n'ai pas
   mesuree. Le classement des deux familles reste le sien, avec la reserve qu'elle a nommee.
4. **Je ne conclus rien sur les autres jeux du catalogue.** J'ai mesure un paquet, `cm1-synonymes`,
   parce que c'est celui qu'on m'a demande. `BPM-1`, `COMP-4` et la famille M4 n'ont pas ete mesures
   a cette largeur et rien ici ne les concerne.
5. **Je ne conclus pas que la marge de 19,81 px au pli de 551 px est confortable.** Elle est
   positive, et c'est tout ce que je peux en dire. Une marge de moins de 20 px sur le pire item, avec
   un item a 1,51 px de basculer de rangee et une police non verifiee sur l'appareil cible, est un
   resultat favorable et fragile a la fois.

## 4. Ce dont je reste la seule garde, et il y a un trou de plus qu'hier

`SYN-5` et `SYN-10` n'ont toujours **aucun garde-fou executable**, et j'ai trouve aujourd'hui une
propriete de plus dans le meme cas, plus dangereuse que les deux precedentes :

**La largeur du conteneur des propositions est une fonction en escalier du viewport, decidee par un
seuil de la feuille de style d'un tiers, et aucun test ne l'assertionne.** Passer de 744 a 768 px de
viewport fait passer la rangee de 500 a 680 px et les seize items de "cinq a deux rangees" a "tous
sur une rangee". Symetriquement, un changement de version de Bootstrap, un retrait de la classe
`container` sur `#jeu`, ou une regle de feuille de style ajoutee ailleurs peut ramener la rangee vers
320 px **sans qu'aucun test ne rougisse** - et le defaut se verra seulement en jouant, comme les sept
items du 2026-08-21.

Je nomme les trois proprietes dont je reste seule garde, pour que personne ne croie couvert ce qui ne
l'est pas :

1. **La hauteur rendue de la derniere proposition** (`SYN-5`) : mesurable seulement au rendu.
2. **L'invariance au tirage** (`SYN-10`) : la propriete n'est pas dans le fichier de contenu.
3. **La largeur effective de la rangee de propositions** (neuf, 2026-08-22) : elle ne se lit ni dans
   le gabarit ni dans `recreo.css`, elle est le produit d'un seuil de Bootstrap et de la largeur du
   viewport.

## Ce que je remonte

- **A `camille-po`** : la chrome de bureau est de **135 px mesures** (Chrome, sans favoris) et de
  **169 px mesures** avec une barre de favoris, sur un poste, avec les reserves de 1.4. La fourchette
  d'`ines-design` tient pour l'hypothese qu'elle a nommee et est debordee de 9 px par la barre de
  favoris. Sur une dalle de 720 px cela derive **585 px** et **551 px** de hauteur utile - dans les
  deux cas **plus contraint que les 640 px de `DEC-0016`**. Et la largeur : fixer 744 px place le
  produit **24 px sous le seuil Bootstrap `md`**, alors que 768 px suffisent a mettre les seize items
  sur une rangee. Le choix de reference lui appartient entierement ; je signale seulement que la
  difference entre 744 et 768 n'est pas une nuance de 24 px, c'est un changement de mise en page.
- **A `ines-design`** : sa fourchette est confirmee pour sa configuration et debordee par la barre de
  favoris, qu'elle avait explicitement exclue. La question du survol qu'elle pose en section 5 de son
  document est bien pour moi et je ne la traite pas ici : elle sera traitee comme une question
  pedagogique datee, pas au detour d'une mesure de pixels.
- **A `theo-archi`** : la propriete 3 de la section 4 est un trou de garde-fou, et sa cause est
  technique. Je decris le symptome, je ne propose pas de mecanisme.

## Captures citees

- `releves-mesure-hauteur-utile/744x551-triste-deux-rangees.png` - pli le plus severe mesure, item
  pilote a deux rangees, les trois propositions entieres.
- `releves-mesure-hauteur-utile/744x585-triste-deux-rangees.png` - meme item au pli derive de la
  configuration sans barre de favoris.
- `releves-mesure-hauteur-utile/768x585-triste-une-rangee.png` - meme item 24 px plus large, apres la
  marche du seuil `md` : une seule rangee, consigne sur une ligne.
