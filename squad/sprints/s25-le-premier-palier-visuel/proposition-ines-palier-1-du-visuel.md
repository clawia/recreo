# Palier 1 du visuel - la mesure du pli avec une image, la forme de l'ecran, et le texte de remplacement

- **Auteure** : `ines-design`. **Date** : 2026-08-31. **Sprint** : s25 (ouverture du visuel).
- **Branche** : `s25-ines`, worktree dedie. **Arbre de depart** : `round-two` a `130ba64`.
- **Repertoire de sprint** : `squad/sprints/s25-le-premier-palier-visuel/`, **le slug de
  `theo-archi`**. J'avais pris un slug provisoire de mon cote - aucun n'existait dans le depot quand
  j'ai commence - et **je me suis alignee sur le sien des qu'il a ete pousse**, plutot que de laisser
  deux repertoires de sprint coexister. `sofia-sm` renomme au planning si elle en decide un autre ;
  ce fichier, le prototype, les quatre dessins de mesure et les quatre outils suivront, comme la
  dependance `D7` de s24 l'a deja fait une fois.
- **Ce que ce document est** : (1) les deux mesures de pli que `nadia-prof` a routees vers moi dans
  son arbitrage prealable de s25, (2) le releve aux dix largeurs qu'elle exige avant de prononcer
  sur l'ecran de saisie, (3) la proposition de rendu du palier 1 - image fixe, propositions en
  texte.
- **Ce que ce document n'est pas** : ni un Done, ni une decision pedagogique, ni une decision
  d'architecture. **`nadia-prof` garde seule le veto**, y compris sur ce que je juge correct. Toute
  phrase adressee a l'enfant ecrite ici, tout dessin, tout texte de remplacement est un
  **candidat**, jamais un acquis.
- **Ce que je n'ai pas touche** : aucun fichier de `domain/`, `application/`, `bootstrap/`, ni
  aucun controleur, ni `adapters/adapter-web/src/main/resources/`. **Ce commit n'ajoute pas une
  ligne a `recreo.css` ni un noeud a un gabarit servi** : il ajoute des vehicules de mesure, trois
  outils, trois releves et ce document.

---

## 0. Le verdict, en premier - parce que c'est une information de planning

**Trois verdicts, dans l'ordre ou ils changent le sprint.**

| # | Question | Reponse mesuree |
|---|---|---|
| **1** | Le pli tient-il avec une image, tel que le palier 1 est concu ? | **NON.** A la largeur de reference, une fois l'enfant en echec - l'etat sur lequel ce depot mesure le pli - il reste **53 px** de hauteur pour l'image. Sous 720 px de large, il n'en reste **aucun** : l'ecran passe deja sous le pli avec une image de hauteur nulle |
| **2** | Un texteSupport de 45 a 70 mots, sa question et trois propositions tiennent-ils au-dessus du pli ? | **NON**, et **pas pour la raison qu'on attendait**. A la largeur de reference, apres un echec, la reponse est non **meme avec un texte de zero mot**. Ce ne sont pas les mots qui debordent, ce sont les **propositions** |
| **3** | La chaine barree est-elle visible en meme temps que le champ, sans defilement ? | **OUI, aux dix largeurs, sur 220 lectures sur 220, defilement nul partout.** La condition de `nadia-prof` pour `D5` est servie par la disposition que `marc-dev` a implementee |

**Ce que ces trois verdicts ne disent pas, et je l'ecris avant qu'on me le demande** : aucun d'eux
ne prononce quoi que ce soit. Le premier ne dit pas que le visuel est impossible - il dit ce que
l'image coute et il chiffre huit leviers qui la rendraient possible, dont sept touchent des blocs
que `nadia-prof` a deja arbitres. Le deuxieme ne ferme pas `LIS` - il deplace la contrainte du
texte vers les propositions, ou personne ne l'avait cherchee. Le troisieme ne prononce aucun Done.

**Une quatrieme question m'est arrivee pendant cette mesure - douze chiffres traces sont-ils
producibles ? - et elle a son propre document** :
`reponse-ines-douze-chiffres-traces.md`, a cote de celui-ci. **Sa reponse depend du chiffre de ce
document-ci** et il faut la lire avec : le trace est producible, une piece portant un chiffre demande
**43 px** de hauteur d'image et **passe** les 53 px ci-dessus, un cadran a douze nombres en demande
**123 px** et **ne les passe pas**.

**Et deux faits sont sortis de ces mesures sans que personne les ait demandes.** Je ne les juge pas ;
je les route.

- **Section 4.4** : `bandit-manchot-comprehension-cm1`, ligne verte, a **onze de ses treize items
  vus sous le pli** a la largeur de reference, apres un echec - le critere meme de `DEC-0051`. Ce
  jeu est ne a **s22**, apres la mesure de catalogue de `DEC-0051`, qui ne pouvait donc pas le
  contenir.
- **Section 5.4** : sur l'ecran de saisie servi, **la revelation passe sous le pli a sept des dix
  largeurs**, dont la largeur de reference sur deux items. Les deux etats de refus, eux, sont propres
  partout des 576 px - **sur le critere du depot, cet ecran est propre**, et ce que ce chiffre revele
  est peut-etre un trou du critere plutot qu'un defaut du lot.

---

## 1. Les exigences de `nadia-prof` que ces mesures devaient satisfaire - et ce que j'ai trouve a leur place

`sofia-sm` me demande de ne pas commencer avant de connaitre ses exigences, parce qu'a s24 elle en
avait pose quatre, declarees refusables. **J'ai lu son arbitrage prealable de s25 en entier avant de
mesurer quoi que ce soit. Il n'y en a pas quatre cette fois, et l'ecart est de sa main, pas d'un
oubli.**

Ce que son document adresse a `ines-design`, litteralement (section 10, table de routage) :

| Ce qu'elle route | Sa formule exacte |
|---|---|
| Le pli avec une image | *"Une question, pas une demande. Ma porte refusera un jeu dont l'image tombe sous le pli"* |
| `LIS`, sous une mesure de pli | *"Un texte de 45 a 70 mots, sa question et trois propositions tiennent-ils au-dessus du pli ? **Sans cette mesure, je ne fais pas ouvrir cette ligne**"* |
| `D5`, releve aux dix largeurs (`SUITES-s24`, section 6.3) | *"que la chaine barree soit visible **en meme temps que le champ, sans defilement**, sur les largeurs que le produit vise"* |

**Une seule des trois est une condition refusable, et c'est la troisieme** : elle enonce un
resultat, elle est verifiable, et une mesure peut ne pas la satisfaire. Les deux autres sont des
questions ouvertes - elle ne dit pas quel chiffre serait acceptable, et **elle a raison de ne pas le
dire**, parce qu'elle ecrit dans le meme document : *"je ne sais pas ce que coute une image dans ce
produit, et je n'ai aucun avis sur la forme qu'elle prendrait"*.

**Ce que cela change a ma methode, et pourquoi je le nomme** : quand une exigence est refusable, je
peux rendre une mesure et dire si elle passe. Quand c'est une question, **le risque est que je
choisisse moi-meme le seuil et que je le presente comme un resultat**. Je ne le fais pas : les
tableaux ci-dessous rendent des **plafonds**, en pixels et en caracteres, et le jugement de savoir
si 53 px sont une image ou une vignette lui appartient. Je donne mon avis de metier a la section
3.4, separement et nomme comme tel.

**Le poteau qu'elle plante et que ma mesure ne peut pas deplacer** : *"ma porte refusera un jeu dont
l'image tombe sous le pli"*. Ce n'est pas negociable par un chiffre.

### 1.1 Addendum - `P1` a `P5`, arrivees pendant la mesure, et ce que ma mesure en fait

`nadia-prof` a pousse dans la journee un second arbitrage,
`ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md`, qui **pose
cette fois cinq exigences declarees refusables** (sa section 4.2). Elles sont arrivees apres le
debut de ma mesure ; **je les confronte donc a ce que j'ai mesure, sans rien reecrire pour les
satisfaire apres coup.**

| Son exigence | Ce que ma mesure en fait |
|---|---|
| **`P1`** - un ecran reellement rendu, avec une image reellement dessinee, ni maquette ni calcul theorique | **Tenue.** Navigateur reel, feuilles reelles, dessins `.svg` reels charges par `<img>`. Le plafond est trouve **par dichotomie sur le rendu**, jamais par une soustraction - c'est ecrit en 2.2 et c'etait deja mon protocole |
| **`P2`** - le perimetre nomme ses largeurs et 744 x 551 en fait partie | **Tenue.** Dix largeurs nommees, 744 x 551 au centre du tableau, viewport **lu** et non suppose a chaque lecture |
| **`P3`** - l'ecran mesure est celui d'apres un echec, indice compris. *Celle a laquelle elle tient le plus* | **Tenue, et c'est ma colonne principale.** Le 53 px est cet etat-la. Je publie l'etat frais et l'etat de revelation **a cote**, jamais a la place |
| **`P4`** - le resultat est une hauteur d'image maximale, pas un verdict | **Tenue.** 53 px apres un echec des 720 px, 89 px en manche fraiche a 576 px, 171 px en manche fraiche des 720 px, **aucune** sous 576 px. Le mot *"non"* de ma section 0 est une lecture de ces nombres, pas leur substitut |
| **`P5`** - la mesure dit ce qu'elle ne couvre pas | **Tenue, et je l'etends** : voir 3.1 pour le perimetre du tableau et 3.5 pour ce que je n'ai pas mesure |

**Une exigence que je ne peux pas satisfaire et que je nomme plutot que de la maquiller** : `P1`
demande *"un ecran reellement rendu"*, et le mien est un **prototype** qui charge les feuilles
reelles, pas l'application servie. **Aucun item visuel n'est servi par aucune route** : il n'existe
pas d'ecran de production a mesurer. `P1` est donc tenue au sens ou rien n'est simule - ni la
feuille, ni l'image, ni le calcul de hauteur - mais **elle n'est pas tenue au sens ou l'ecran serait
celui du jar**. Le releve refait sur le jar reste du le jour ou un lot livre un item visuel, et il
lui restera a elle de juger ce que l'enfant voit.

---

## 2. Le pli, la methode, et ce que mes chiffres prouvent

### 2.1 Ce que "le pli" designe ici, et sur quel etat il se mesure

**Le pli est 551 px**, la hauteur de reference du produit (`DEC-0040`, second amendement du
2026-08-22), a la largeur de reference **744 px**. `DEC-0051` le ferme sur tout le catalogue.

**L'etat sur lequel ce depot a toujours mesure le pli est l'etat APRES UN ECHEC** - verdict et
indice affiches. C'est ce que `squad/outils/nadia-prof/auditer-paquet.py` rend
(`basDuVerdict`, `basDeLIndice`), c'est ce que `REP-8` et `DEC-0051` comptent, et c'est ce que
`compter-le-pli-du-catalogue-entier.py` a compte sur 92 puis 428 items. **Je garde cet etat comme
reference et je ne le change pas** : changer l'etat de mesure en meme temps qu'on ajoute une image
rendrait les deux chiffres incomparables.

Je publie a cote **l'etat frais** et **l'etat de revelation**, parce qu'ils encadrent : le premier
est le plus genereux, le second le plus severe, et le lecteur voit ou est la marge.

### 2.2 Vehicule, outil, perimetre

**Vehicule** : `prototype-ines-palier-1-image-fixe.html`, commite a cote de ce fichier. Il charge
**les deux feuilles reelles** de l'application par chemin relatif, dans l'ordre d'`ADR-0014` (tierce
puis maison), recopie la coquille de `templates/accueil.html` (en-tete, `#jeu.container.zone-jeu`)
et **le bloc `.cabinet` / `.propositions` / `.message-jeu` de `fragments/manche.html` sans changer
une seule de leurs regles**. Les regles candidates sont dans un `<style>` place apres la feuille
maison, donc soumises aux memes calculs de specificite et d'ordre qu'une fois versees. Meme
protocole qu'a s24, pour que les deux releves se comparent.

**Outil** : `squad/outils/ines-design/mesurer-l-illustration.py`, navigateur reel pilote par CDP,
qui importe les juges purs de `squad/outils/nadia-prof/balayage-largeur-rangee.py` (port, viewport
**lu** et non suppose, un seul etat visible a la fois) plutot que de les recopier. Le plafond est
trouve **par dichotomie au pixel**, jamais par une soustraction : la hauteur d'une image n'est pas
seule a entrer dans le bas de l'ecran - marges qui fusionnent, meuble qui se replie -, et une
soustraction aurait l'air juste sans l'etre.

**Dix largeurs** : 360, 390, 576, 640, 720, 744, 768, 992, 1200, 1440. Elles encadrent les paliers
de la feuille tierce et la bande maison de 720-767,98 px. Mesurer a une seule largeur est exactement
ce qui a laisse passer le defaut de rembourrage lateral de s05.

**Releve complet** :
`squad/conformite/releves-s25/s25-ines-palier-1-image-et-lis-dix-largeurs.json`, 120 mesures.

**Ce que ces chiffres ne prouvent pas, ecrit avant qu'on me le reproche** : que l'application servie
rendra ces boites. Ils prouvent que **ces regles, sur ces feuilles, donnent ces pixels**. Aucun item
visuel n'est servi par aucune route ; le releve refait sur le jar reste du le jour ou un lot en
livre un, et il restera a `nadia-prof` de juger ce que l'enfant voit.

### 2.3 Ce que le dessin de mesure est, et ce qu'il n'est pas

Deux fichiers, `prototype-figure-carree.svg` (rapport 1:1) et `prototype-rangee-large.svg`
(rapport 3:1). **Ce ne sont pas des items.** Ils ne sont declares par aucun paquet, servis par aucun
controleur, et ils ne representent rien : un carre nu et trois disques nus, sans remplissage ni
teinte. Ils portent **un rapport de geometrie et rien d'autre**.

Les deux rapports ne sont pas une coquetterie : le palier 1 ouvre des lignes dont les dessins n'ont
pas la meme forme - une figure plane et un cadran sont proches de 1:1, une collection de pieces
posee en rangee est large. **Le 1:1 est le pire cas vertical** ; le 3:1 est celui ou, sur un ecran
etroit, c'est la **largeur** disponible qui plafonne la hauteur avant que le pli ne le fasse.

**L'exactitude de ce qui serait reellement dessine - proportions, graduations, cases carrees - est
la seule main de `nadia-prof`**, elle l'a ecrit le 2026-08-31 et je ne m'en approche pas.

---

## 3. Mesure 1 - le pli avec une image

### 3.1 Les chiffres

**Perimetre : prototype ci-dessus, dessin 1:1, trois propositions courtes tenant sur une seule
rangee, indice de 14 mots. "Nu" = le meme ecran avec une image de hauteur nulle. "Plafond" = la plus
grande hauteur d'image, en pixels, qui garde le bas de l'ecran entier au-dessus de 551 px.**

| Largeur | Manche fraiche : nu / plafond | **Apres un echec : nu / plafond** | Revelation avec explication : nu / plafond | Revelation sans explication : nu / plafond |
|---|---|---|---|---|
| 360 | 650 / **aucun** | 828 / **aucun** | 972 / **aucun** | 828 / **aucun** |
| 390 | 602 / **aucun** | 780 / **aucun** | 894 / **aucun** | 780 / **aucun** |
| 576 | 462 / 89 px | 610 / **aucun** | 724 / **aucun** | 610 / **aucun** |
| 640 | 462 / 89 px | 610 / **aucun** | 724 / **aucun** | 610 / **aucun** |
| 720 | 380 / 171 px | **498 / 53 px** | 582 / **aucun** | 498 / 53 px |
| **744 (reference)** | 380 / 171 px | **498 / 53 px** | 582 / **aucun** | 498 / 53 px |
| 768 | 380 / 171 px | **498 / 53 px** | 582 / **aucun** | 498 / 53 px |
| 992 | 380 / 171 px | **498 / 53 px** | 582 / **aucun** | 498 / 53 px |
| 1200 | 380 / 171 px | **498 / 53 px** | 552 / **aucun** | 498 / 53 px |
| 1440 | 380 / 171 px | **498 / 53 px** | 552 / **aucun** | 498 / 53 px |

*"aucun" signifie que l'ecran passe deja sous le pli avec une image de hauteur nulle : il n'existe
aucune hauteur d'image qui le sauve, la borne n'existe pas. Ce n'est pas "0 px", c'est "negatif".*

**Le meme tableau avec un dessin de rapport 3:1** donne les memes plafonds a toutes les largeurs -
53 px apres un echec des 720 px - avec une seule difference, qui est celle qu'on cherchait : a 576
et 640 px, le dessin est plafonne par la **largeur** (516 px de large, donc 172 px de haut) avant de
l'etre par la regle de hauteur. Un rapport large ne coute pas moins cher au pli ; il coute la meme
chose, et sur un ecran etroit il est simplement plus petit.

### 3.2 Le rapport de geometrie n'est jamais deforme - et c'est mesure, pas affirme

`FIG-1` demande qu'un rectangle reste un rectangle a toute taille d'ecran, **verifiable autrement
que par l'oeil de `nadia-prof`**. Le releve porte, a chaque lecture, le rapport declare par le
fichier et le rapport de la boite rendue :

| Fichier | Rapport declare | Rapport rendu, aux dix largeurs |
|---|---|---|
| `prototype-figure-carree.svg` | 1,0 | **1,0 partout** |
| `prototype-rangee-large.svg` | 3,0 | **3,0 partout**, y compris a 576 et 640 px ou `max-width` mord |

**C'est la regle de feuille qui l'obtient, et il y faut quatre declarations** : `max-height`
plafonne la seule dimension que le pli compte, `max-width: 100%` empeche le dessin de sortir de la
zone, `height: auto` et `width: auto` laissent **le rapport du fichier gouverner, jamais la
feuille**, et `object-fit: contain` est la ceinture.

**La variante fautive est mesuree, pas supposee, et elle est dans le releve commite** (cle
`varianteFautiveHeightFixe`, etats `large-*`) : l'outil rejoue le meme etat avec `height: 176px`
fixe a la place de `max-height`, sur le dessin de rapport 3:1.

| Largeur | Regle proposee (`max-height`) | Regle fautive (`height` fixe) |
|---|---|---|
| 744 | boite 528 x 176, **rapport rendu 3,000** | boite 528 x 176, rapport rendu 3,000 |
| **576** | boite 516 x 172, **rapport rendu 3,000** | boite 516 x 176, **rapport rendu 2,932** |

**A 576 px, la ou `max-width` mord, la regle fautive rend une boite qui ne porte plus le rapport
declare par le fichier.** `object-fit: contain` empeche le dessin lui-meme d'etre etire - il est
alors centre dans une boite trop haute de 4 px - mais **la propriete que `FIG-1` demande de pouvoir
verifier autrement que par l'oeil n'est plus verifiable sur la boite rendue**. C'est la faute que
ces quatre declarations ecartent, et c'est pourquoi elles sont quatre et non deux.

**Consequence a router, section 11, `DV-1`** : ces trois declarations ne font jamais **grandir** un
dessin au-dela de sa taille naturelle. La taille intrinseque doit donc etre declaree par le fichier
servi, donc par le contenu. C'est exactement ce que `FIG-1` demande - *"ses proportions sont
declarees par le contenu, pas par une police"* - et c'est une cle de contrat que `theo-archi`
n'a pas encore rendue.

### 3.3 Ou passent les 498 px, et huit leviers avec leur prix

Le decompte a 744 px, apres un echec, image nulle - lu sur le releve, pas reconstitue :

| Bloc | Cout vertical |
|---|---|
| Rembourrage haut de `.zone-jeu` | 32 px |
| `.score` | 38 px |
| `.consigne` | 30 px |
| Marges de `.illustration` (haut et bas) | 16 px |
| `.enonce` | 48 px + 4 px de marge |
| `.cabinet` (dont **66 px de propositions** et **64 px de meuble**) | 130 px |
| `.message-jeu` (signal 64 px, indice de 14 mots sur 2 lignes 50 px) | 118 px |
| En-tete permanent et haut de page | 82 px |

**Ce chiffre converge avec celui de `nadia-prof`, et la difference s'explique au pixel.** Elle donne
dans son arbitrage, pour qu'on ne le cherche pas : *"il reste 65 px entre le bas de l'indice et le
pli, dans le cas le plus favorable du catalogue"*, soit un `basIndice` de **486 px** a 744 x 551,
mesure sur ses releves de s21. **Mon ecran nu, apres un echec, finit a 498 px** - et le levier
"marge de l'illustration a zero" ci-dessous vaut exactement **12 px**, ce qui ramene mon ecran a
**486 px**. **Les deux mesures, prises sur deux arbres et par deux instruments, donnent le meme
nombre.** L'ecart de 12 px entre son budget de 65 px et le mien de 53 px n'est pas un desaccord :
**c'est le cout propre du bloc d'illustration**, que son chiffre ne pouvait pas contenir puisque le
bloc n'existait pas.

**Le 53 px depend de la longueur de l'indice, donc du contenu, et il faut le lire avec ce
chiffre-la.** L'indice du prototype fait 14 mots et se replie sur **deux lignes rendues**, soit
50 px : **une ligne d'indice coute 25 px**. Les bornes deja arbitrees sont plus basses - `SEM-8` et
`NAR-9` posent huit mots, `LIS-9` dix - mais **l'indice le plus long des 31 paquets servis compte 27
mots** (`cm1-accord-sujet-verbe`, compte mecanique sur les fichiers de contenu de cet arbre).

**Ce qui suit est une deduction et non une mesure, et je le dis parce que la difference compte
ici** : les 25 px par ligne sont mesures, la longueur de 27 mots est comptee, mais **je n'ai pas
mesure d'ecran portant cet indice-la avec un dessin** - aucun item visuel n'existe. Sur ces deux
chiffres mesures, deux lignes d'indice de plus consommeraient les 50 px des 53 disponibles. **Le
budget d'image d'un item visuel est gouverne par la longueur de l'indice de cet item**, et cette
longueur est une regle d'ecriture de contenu, pas une regle de forme : elle appartient a
`nadia-prof`. Le chiffre exact, lui, se mesurera sur le premier paquet reel.

**Les huit leviers, mesures un par un, chacun seul, a chacune des dix largeurs.** Le gain est
identique partout sauf le dernier :

| Levier | Gain |
|---|---|
| Retirer entierement l'habillage du cabinet (bordure, bandeau, socle, levier) | **+64 px** |
| Masquer le bandeau du cabinet seul | +32 px |
| Retirer la bordure du cabinet seule | +16 px |
| Masquer le socle du cabinet seul | +16 px |
| Masquer `.score` | +38 px |
| Rembourrage haut de `.zone-jeu` a 1 rem | +16 px |
| Marge de l'illustration a zero | +12 px |
| `.enonce` a 1,5 rem | +12 px (et **+60 px a 360 px**, ou l'enonce se replie) |

**Tous ensemble : +178 px**, ce qui porterait le plafond apres un echec de 53 px a **231 px**.

**Je ne propose aucun de ces leviers et je ne les additionne pas comme un plan.** **Sept des huit
touchent des blocs que `nadia-prof` a deja arbitres nommement** - les quatre premiers sont le
cabinet, qu'elle a arbitre en s10 sous les conditions `C1` a `C6` (*"un seul objet ferme sur ses
quatre cotes"*) ; le score et le rembourrage de zone sont les deux leviers de `REP-8` qu'elle a
juges a s21 ; le dernier touche le rapport entre l'enonce et une proposition, que son *acquis 3*
fixe. **Seul le huitieme, la marge de l'illustration, est propre a ce que je propose.**

**Et aucun d'eux n'est propre au visuel** : ils s'appliqueraient aux 31 jeux du catalogue d'un coup.

**Un point que je verifie plutot que de le supposer, sur le dernier levier** : abaisser `.enonce` a
1,5 rem **ne ferait rougir aucun test**. `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` compare
`.proposition` a `.enonce` et reste vert quand l'enonce **retrecit** ; le plancher de lisibilite est
a 1,25 rem. **Le garde-fou ne verrait donc pas passer ce levier, et c'est precisement pourquoi il
est a elle et pas a la CI.**

Ce sont des chiffres poses sur la table avant la discussion, pas une recommandation. C'est le meme
geste qu'a s21 pour `REP-8`, et il avait servi.

### 3.4 Mon avis de metier, nomme comme tel et separe des chiffres

**53 px n'est pas une image, c'est une vignette**, et je le dis en tant que designer, pas en tant
que mesureuse. A cette hauteur, un carre de 53 px de cote pose entre une consigne a 20 px et un
enonce a 32 px n'est pas *"ce que l'enfant doit regarder en premier"* : il est **plus petit que le
signal juste/faux (48 px) et a peine plus grand que la hauteur d'une ligne d'enonce**. Cela contredit
frontalement la regle que je tiens depuis s01 - ce que l'enfant doit lire ou reconnaitre en premier
est au moins aussi grand que les elements secondaires.

**Et il y a une raison propre au contenu, qui n'est pas mon jugement mais qui decoule de sa
condition** : `FIG-2` exige *"au moins trois exemples differents par forme, variant l'orientation,
la taille et les proportions"*. Distinguer un carre pose sur la pointe d'un losange demande de voir
les angles ; a 53 px, un trait de 3 px de large occupe 6 % de la figure. **Je ne prononce pas que
c'est illisible pour un CP - ce jugement est a `nadia-prof`, en jouant** - mais c'est le fait de
forme qu'il lui faut pour trancher.

**Ce que je propose comme suite, sans la decider** : que la question posee ne soit pas *"fait-on le
visuel"* mais *"le pli se rediscute-t-il pour un item qui porte un dessin, et si oui sur quel
etat"*. Le pli est un arbitrage de l'utilisatrice (`DEC-0051`), sorti de la porte pedagogique par
`DEC-0053`. **Ce n'est ni ma decision ni celle de `nadia-prof`.**

### 3.5 Ce que cette mesure ne couvre pas - `P5`, ecrit ici et pas ailleurs

Une mesure dont le domaine de validite n'est pas ecrit sera citee hors de son domaine dans deux
sprints. Voici le mien, sans complaisance.

- **Elle suppose un enonce d'une seule ligne rendue** et une consigne d'une seule ligne. Un enonce
  sur deux lignes retire **52 px** au plafond, ce qui l'annule a toutes les largeurs.
- **Elle suppose trois propositions courtes tenant sur une seule rangee.** Une seconde rangee coute
  **82 px** (66 px de bouton et 16 px d'ecart) et annule egalement le plafond.
- **Elle suppose un indice d'une ligne rendue** - celui de mon prototype en fait 14 mots et tient sur
  une ligne a 744 px. Chaque ligne de plus coute **30 px** (section 3.3).
- **Elle porte sur deux rapports de geometrie**, 1:1 et 3:1. Un rapport intermediaire n'est pas
  mesure ; le 1:1 est le pire cas vertical et borne les autres.
- **Elle ne porte sur aucun paquet.** Ni le contenu des dessins, ni le nombre d'items, ni leurs
  indices reels.
- **Elle porte sur un prototype, pas sur le jar** (voir 1.1). Ce qu'elle prouve est que **ces regles,
  sur ces feuilles, donnent ces pixels**.
- **Elle ne dit rien de la revelation avec explication a partir de 1200 px** autrement que par le
  chiffre du tableau : l'ecran nu y finit a 552 px, soit **1 px** sous le pli. Un pixel n'est pas une
  marge, et je ne le presente pas comme un cas limite favorable.

---

## 4. Mesure 2 - `LIS`, le texte de 45 a 70 mots

### 4.1 Ce que le prototype modelise

Le meme vehicule, trois etats de plus, **sans aucune image** : `.texte-support` (la classe reelle,
1,25 rem, `ADR-0050`), un `.enonce` de 6 mots (`LIS-3` en autorise 12), **trois propositions qui
sont des phrases de synthese** de 33, 37 et 62 caracteres, et un indice de 8 mots (`LIS-9` en
autorise 10). Le texte de mesure compte exactement 70 mots - le plafond que `LIS-2` declare
provisoire et remet a cette mesure - et l'outil en retire des mots par la fin pour redescendre.

**Ce texte est un gabarit de longueur, jamais un candidat d'item.** Les propositions non plus : leur
longueur est ma seule hypothese, et c'est precisement celle qui decide de tout, section 4.3.

### 4.2 Les chiffres

**Bas de l'ecran, apres un echec, dessin absent :**

| Largeur | 0 mot de texteSupport | 70 mots | Verdict a 45 mots |
|---|---|---|---|
| 360 | 1074 | 1408 | sous le pli |
| 390 | 1026 | 1330 | sous le pli |
| 576 / 640 | 842 | 1056 | sous le pli |
| 720 / 744 / 768 | **698** | **852** | **sous le pli** |
| 992 | 698 | 822 | sous le pli |
| 1200 | 650 | 744 | sous le pli |
| 1440 | 568 | 662 | sous le pli |

**Le plafond de caracteres par proposition** - la variable que j'ai isolee parce que c'est elle qui
mord - **avec un texteSupport de 45 mots** :

| Largeur | Manche fraiche | **Apres un echec** | Revelation |
|---|---|---|---|
| 360 / 390 | aucun | **aucun** | aucun |
| 576 / 640 | 8 caracteres | **aucun** | aucun |
| 720 / 744 / 768 | 11 caracteres | **aucun** | aucun |
| 992 | 27 caracteres | **aucun** | aucun |
| 1200 | 34 caracteres | **21 caracteres** | aucun |
| 1440 | plus de 120 | **24 caracteres** | aucun |

*"aucun" veut dire : meme avec des propositions vides, l'ecran passe sous le pli.*

**Reponse a sa question, sans detour** : **non.** A la largeur de reference, apres un echec, un
texteSupport de 45 mots avec sa question et trois propositions **ne tient pas au-dessus du pli, quel
que soit le contenu des propositions**. Il n'y tient a aucune des dix largeurs sauf 1200 et 1440,
et seulement si chaque proposition tient en 21 ou 24 caracteres.

### 4.3 La cause n'est pas le texte, et c'est le point utile

`LIS-2` borne le texteSupport a 70 mots et declare ce plafond provisoire. **Ce n'est pas la variable
qui mord.** A 744 px, apres un echec :

- le texteSupport de 70 mots occupe **150 px** ; a 45 mots, environ **96 px** ;
- les **trois propositions occupent 278 px sur trois rangees**, parce qu'une phrase de synthese de
  62 caracteres rendue a **2 rem** ne tient pas sur une rangee et en prend deux a elle seule ;
- l'ecran finit deja a **698 px avec zero mot de texte**.

**Retirer tout le texte ne suffit pas. Retirer toutes les propositions ne suffit pas non plus.**
Les deux ensemble ramenent l'ecran a 534 px - et il ne reste alors plus de place pour les 96 px du
texte de 45 mots que `LIS-2` exige au minimum.

**Ce que je route plutot que de le trancher** (`DV-4`, section 11) : `LIS-1` a `LIS-10` bornent la
longueur du texte, celle de l'enonce et celle de l'indice. **Aucune condition ne borne la longueur
d'une proposition**, et c'est elle qui decide du nombre de rangees, donc du budget vertical. C'est
la meme famille que `D4` a s24 - la longueur maximale d'une reponse a ecrire, que personne n'avait
vue - et c'est une regle d'ecriture de contenu, donc a `nadia-prof`, pas a moi.

*Precision qui limite ma propre mesure : les 21 a 24 caracteres du tableau ne sont pas une
proposition de condition. Ils sont ce que la geometrie laisse, sur mon hypothese de longueur, aux
deux seules largeurs ou quelque chose passe. Une condition ecrite sur ce chiffre serait une
condition ecrite par un pixel.*

### 4.4 L'ancrage sur le jeu vert - ou en est deja le produit servi

Un prototype dit ce que des regles donnent en pixels, jamais ou en est le produit. J'ai donc mesure
le **seul jeu servi qui porte deja un texteSupport**.

**Perimetre, ecrit a cote du chiffre : `bandit-manchot-comprehension-cm1` sur l'application servie
depuis le jar de cet arbre, largeur 744 px seulement (la reference de `DEC-0040`), hauteur 551 px,
etat apres un echec, 13 items sur 20 - ceux que vingt parties ont tires. Outil :
`squad/outils/ines-design/mesurer-l-ancrage-du-texte-support.py`. Releve :
`squad/conformite/releves-s25/s25-ines-ancrage-comprehension-cm1-744.json`. Ce releve ne balaie pas
les dix largeurs et ne pretend pas le faire.**

| Ce qui est mesure | Valeur |
|---|---|
| Mots de texteSupport | de 20 a 34 (coherent avec les 20 a 38 mesures par `nadia-prof`) |
| Hauteur du texteSupport | 60 ou 90 px |
| Rangees de propositions | 1, 2 ou 3 selon l'item |
| Bas de l'ecran apres un echec | de **598** a **744 px** |
| **Items dont le verdict passe sous le pli** | **11 sur 13** |
| **Items dont l'indice passe sous le pli** | **11 sur 11** (les deux autres items n'ont pas d'indice) |

**Deux outils independants donnent le meme chiffre sur le meme item** : `auditer-paquet.py`, de
`nadia-prof`, rend *"verdict 583, indice 662"* sur *"Que fait Monsieur Albert de ses journees ?"* ;
mon outil rend 583 et 662. Je le dis parce qu'un chiffre de cette portee ne doit pas reposer sur un
instrument neuf, seul.

**Ce que cela change a la lecture du verdict `LIS`** : la ligne verte d'a cote, avec des textes deux
fois plus courts que le plancher de `LIS-2`, est **deja** sous le pli sur presque toute sa
population. `LIS` n'ajouterait pas un defaut de pli a un catalogue propre ; il aggraverait un defaut
qui existe et que personne n'avait mesure.

**Pourquoi personne ne l'avait vu, et ce n'est pas une negligence** : `cm1-comprehension` est ne a
**s22** (`7e259a4`), et la mesure de catalogue de `DEC-0051` date de la cloture de **s21**. Ce jeu
n'a jamais ete dans ce comptage. **Les quatre jeux CM1 nommes par `DEC-0051` restent exacts ; il y
en a un cinquieme, plus recent, que ce comptage ne pouvait pas contenir.**

**Je ne juge pas ce fait et je n'en tire aucun statut de couverture.** Il va a `nadia-prof`, dont
c'est la porte, et a `camille-po`, dont c'est la matrice.

---

## 5. Mesure 3 - l'ecran de saisie servi, le releve que `nadia-prof` exige avant de prononcer

### 5.1 Pourquoi ce releve est neuf et ne recopie pas celui de s24

Le releve de s24 portait sur un prototype statique, parce qu'aucune route ne servait la saisie.
Elle est servie depuis le lot 4, et `marc-dev` a place la liste des tentatives **au-dessus du
champ** et non sous le verdict comme ma proposition la placait. **Il ecrit lui-meme que ce
deplacement est de lui et qu'il n'est pas mesure.** Mon releve de s24 decrit donc une disposition
que l'application ne rend pas ; celui-ci decrit celle qu'elle rend.

**Perimetre** : application servie depuis le jar de cet arbre, jeu `saisie-complements-10-cp`,
**les 11 items du paquet**, quatre etats par item (manche fraiche, premier refus, deuxieme refus,
revelation), **aux dix largeurs**, hauteur 551 px. **440 lectures.** Trois chaines refusees de
longueurs croissantes (`99`, `888`, `7777`), pour que la liste mesuree soit le pire cas de hauteur
et non un cas moyen. Outil :
`squad/outils/ines-design/mesurer-l-ecran-de-saisie-servi.py`. Releve :
`squad/conformite/releves-s25/s25-ines-ecran-de-saisie-servi-dix-largeurs.json`.

*Le tirage d'une manche est aleatoire et l'indice n'a pas la meme hauteur d'un item a l'autre :
mesurer une seule manche par largeur aurait fait dependre le chiffre du tirage, et deux largeurs se
seraient comparees sur deux items differents. Le releve joue donc des parties jusqu'a avoir vu les
onze enonces, et refuse de rendre un chiffre s'il n'y arrive pas.*

### 5.2 Le verdict sur sa condition

**Sa condition, litteralement : *"que la chaine barree soit visible en meme temps que le champ, sans
defilement, sur les largeurs que le produit vise"*.**

| Ce qui est juge | Resultat |
|---|---|
| Lectures ou la liste et le champ coexistent (premier et deuxieme refus) | 220 |
| Lectures ou **les deux sont entierement au-dessus du pli** | **220 sur 220** |
| Largeurs qui ne servent pas la condition | **aucune**, y compris 360 et 390 px |
| Lectures a defilement non nul | **0 sur 440** |

**La condition est servie par la disposition implementee.** A 744 px, la liste finit a 268 px et le
champ a 348 px : les deux sont dans le meme regard, tres au-dessus du pli, et la liste est
au-dessus du champ - ce que `marc-dev` visait en citant sa condition.

**Je ne prononce rien de plus, et surtout pas que la place est la bonne.** Elle ecrit qu'elle ne
tranche pas la place et que ce n'est pas sa porte ; ce n'est pas davantage la mienne de la trancher
a sa place par un chiffre.

**Ce que ce releve mesure, et ce qu'il ne mesure pas** : il porte sur **la seule disposition que
l'application rend**, celle du lot. **Je n'ai pas mesure la mienne sur l'application servie**, et je
ne peux donc pas ecrire qu'elle servirait aussi bien - ce serait comparer un rendu a un prototype.

**Je retire ma preference de s24**, pour un motif qui n'est pas un chiffre : elle reposait sur un
critere de forme de moi - *"aucune chaine n'apparait aux deux endroits a la fois"* - que
`nadia-prof` a explicitement ecarte le 2026-08-31 en validant la combinaison (*"l'une dit refusee,
l'autre dit modifiable"*). Sa lecture prime sur mon critere, la disposition servie satisfait sa
condition aux dix largeurs, et **la mesure ne donne aucun motif de rouvrir**. Rouvrir couterait
d'ailleurs un aller-retour a `marc-dev` pour un gain nul.

*Note pour que personne ne me le repropose : la forme (b) - une marque de refus sur le champ qui
s'efface a la premiere frappe - est retiree par `nadia-prof` elle-meme, sur le fait technique etabli
par `marc-dev` (elle exigerait du JavaScript maison decidant de ce que l'ecran montre, dette 4 de
`CLAUDE.md`, gardee par `FrontieresDuJavaScriptTest`). Je ne la reproposerai pas et je n'en cherche
pas de variante.*

### 5.3 Le trait, verifie sur le rendu servi

`marc-dev` ecrit avoir repris a l'identique les valeurs de ma section 5.1 de s24. Le releve le
confirme sur le rendu, a chacune des 220 lectures ou la liste existe : `line-through`, epaisseur
**3 px** (0,1875 rem), couleur `rgb(28, 39, 51)` (`--recreo-texte`), police 20 px. **C'est le signe
de `M9`, deja arbitre par `nadia-prof` sous la condition `F16` pour dire *"tu as deja essaye ca"*.
Meme sens, meme signe, seconde mecanique.**

### 5.4 Ce que le meme releve fait apparaitre, et qu'on ne m'a pas demande

**A la revelation, le bas de l'ecran passe sous le pli a sept des dix largeurs, dont la largeur de
reference.** Les deux etats de refus, eux, sont propres partout des 576 px.

| Largeur | Manche fraiche | Refus 1 et 2 | **Revelation** |
|---|---|---|---|
| 360 | 474 - aucun sous le pli | 696 a 756 - **11 sur 11** | 650 a 710 - **11 sur 11** |
| 390 | 402 - aucun | 596 a 626 - **11 sur 11** | 650 a 680 - **11 sur 11** |
| 576 / 640 | 314 - aucun | 508 - aucun | **562 - 11 sur 11 sous le pli** |
| 720 | 314 - aucun | 478 a 508 - aucun | 532 a 562 - **2 sur 11** |
| **744 (reference)** | 314 - aucun | 478 a 508 - aucun | 532 a 562 - **2 sur 11 sous le pli** |
| 768 | 314 - aucun | 478 a 508 - aucun | 532 a 562 - **2 sur 11** |
| 992 / 1200 / 1440 | 314 - aucun | 478 - aucun | 532 - aucun |

Les deux items concernes a 744 px sont `0 + ? = 10` et `10 + ? = 10`, **les deux dont l'indice est
le plus long** : il se replie sur deux lignes et pousse le bas de l'ecran a 562 px, soit **11 px
sous le pli**.

**Trois choses avec ce chiffre, et je tiens aux trois** :

1. **Sur le critere du depot, cet ecran est propre.** Le pli se mesure apres un echec ; a cet etat,
   les 11 items sont au-dessus du pli a chacune des huit largeurs a partir de 576 px, sur les 220
   lectures correspondantes. Le lot 4 n'a pas livre un defaut de pli au sens ou ce depot compte les
   defauts de pli.
2. **La revelation n'a jamais ete dans ce critere**, sur aucun des 31 jeux. Ce que ce chiffre
   revele n'est peut-etre pas un defaut du lot 4 mais **un trou du critere**, qui vaudrait alors
   pour tout le catalogue.
3. **Savoir lequel des deux n'est pas a moi.** C'est `nadia-prof` pour la porte, et l'utilisatrice
   pour le pli lui-meme, que `DEC-0053` a sorti de la porte pedagogique. **Je pose le chiffre et je
   m'arrete.**

---

## 6. La forme de l'ecran du palier 1

Ce qui suit est la proposition de rendu. **Elle est ecrite en connaissance du verdict de la
section 3** : si le pli reste ou il est, cette forme n'est pas servable en l'etat, et je l'ecris
quand meme, parce que la forme et le budget sont deux questions distinctes et qu'un arbitrage sur
le pli aura besoin de savoir a quoi ressemble ce qu'il autorise ou refuse.

### 6.1 Etat par etat

**Manche fraiche**

```
Score : 2
Regarde bien la figure.          <- la consigne, ecrite par le paquet
        [ le dessin ]            <- centre, plafonne en hauteur, rapport intact
Quelle est cette figure ?        <- l'enonce
+-----------------------------+
|  un carré | un rectangle | un triangle  |   <- le cabinet, inchange
+-----------------------------+
```

Rien d'autre. **Le jeu ne parle pas tant que l'enfant n'a rien envoye** : pas de message, pas de
consigne d'usage, pas de texte d'aide. Meme regle qu'aujourd'hui.

**Apres un echec** : le dessin **reste a l'ecran**, a la meme place et a la meme taille ; la
proposition pressee porte la marque de `M9` ; le bloc de retour apparait sous le meuble.

**A la revelation** : le dessin **reste a l'ecran**. C'est le seul point de cette section que je
defends comme une regle et non comme un gout : **un enfant qui lit *"c'etait un carre"* sans voir la
figure qu'on lui a montree n'apprend rien.** C'est aussi ce qui rend l'etat de revelation si cher au
pli, section 3.1, et les deux faits vont ensemble.

### 6.2 Ou se place le dessin - et la mesure dit que ce n'est pas une question de pixels

J'ai mesure les deux placements, **le dessin entre la consigne et l'enonce** et **le dessin entre
l'enonce et le meuble**, aux dix largeurs et aux trois etats. **Les trente couples de chiffres sont
identiques au pixel.** Deplacer un bloc dans une colonne verticale ne change pas la hauteur de la
colonne, et le mesurer etait la seule facon de le dire plutot que de le supposer.

**Le choix est donc entierement semantique, et je propose le premier - le dessin dans
l'emplacement du texte-support** :

- `.texte-support` est deja, depuis `ADR-0050`, le bloc *"la matiere sur laquelle porte la
  question"*, rendu entre la consigne et l'enonce. **Une image fixe joue exactement ce role.** La
  proposer ailleurs inventerait un second emplacement pour la meme fonction ;
- l'ordre de lecture devient : *je regarde, puis je lis la question, puis je choisis*. L'ordre
  inverse fait lire la question avant d'avoir vu ce sur quoi elle porte ;
- c'est une regle de **gabarit generique**, pas une regle pour les figures planes. Elle vaudra
  identiquement pour un cadran ou une collection.

**Ce que je ne propose pas** : mettre le dessin **dans** le cabinet. `ADR-0030`, point 1.a a 1.d,
fait du cabinet le meuble qui **entoure les colonnes de reponses et rien d'autre** ; `nadia-prof`
l'a arbitre en s10 sous les conditions `C1` a `C6`, dont `C2` (*"ne contient que `.propositions` et
rien d'autre qui se presse"*) et `C5` (*"dont l'unique image est fixe"*). Un dessin d'item a
l'interieur du meuble ferait croire qu'il se presse, et casserait la seule image fixe qu'`ADR-0030`
autorise.

### 6.3 Le retour juste/faux - ce qui ne change pas, et pourquoi c'est la bonne reponse

**Rien.** `.message-jeu`, `.signal`, `.icone-signal`, `.verdict`, `.explication`, `.indice` sont
repris a l'identique, sans changer une seule de leurs regles.

**Ce n'est pas de la paresse, c'est la regle de mon mandat.** Le signal juste/faux appartient a **la
mecanique**, pas au contenu : la coche et la croix disent *"juste"* et *"faux"* de la meme facon
qu'un enfant ecrive un mot, presse un bouton ou reconnaisse une figure. Dessiner un signal *"pour
les items visuels"* serait exactement la faute que mon mandat nomme - une regle de forme collee a un
contenu. **Le signal du palier 1 est celui du bandit-manchot, sans une ligne de plus.**

Il tient deja les deux exigences qui comptent ici, et je les reverifie plutot que de les citer :
- **il ne repose jamais sur la couleur seule** (`M11` clause 1) : la coche et la croix sont des
  formes, connues d'un cahier d'ecole, et le texte du verdict porte le meme sens ;
- **il n'a que deux etats**, et une manche resolue par revelation n'affiche jamais le signal de
  reussite (`M11` clause 3, `M7`).

**Un seul point neuf, et c'est une question, pas une proposition** (`DV-5`, section 11) : sur un
item visuel, l'explication de la revelation devrait-elle pouvoir **designer une partie du dessin**
(*"regarde ce cote-la"*) ? Aujourd'hui elle ne peut pas : `.explication` est une chaine de texte, et
`.mot-designe` (`ADR-0062`) designe un segment d'enonce, pas une region d'image. Je **ne propose
rien** ici : ce serait une capacite de contenu, donc `theo-archi` pour le contrat et `nadia-prof`
pour l'opportunite. Je le nomme pour qu'il ne soit pas decouvert au troisieme lot.

---

## 7. Le balisage propose

```html
<!-- Palier 1 du visuel : un item qui porte un dessin. Le bloc occupe l'emplacement du
     texte-support (ADR-0050) - "la matiere sur laquelle porte la question" -, entre la consigne et
     l'enonce, et il n'invente aucun emplacement neuf.
     th:if, jamais un bloc vide (ADR-0020) : un jeu qui ne declare aucun dessin ne rend pas ce
     noeud, plutot que de le nourrir d'une valeur factice.
     Le cabinet n'entoure PAS ce bloc : ADR-0030 point 1.a a 1.d fait du meuble ce qui entoure les
     colonnes de reponses et rien d'autre, et C5 n'y autorise qu'une image fixe, celle du bandeau.
     Le texte de remplacement n'est PAS ecrit ici : voir la section 9 de
     proposition-ines-palier-1-du-visuel.md. Ce gabarit ne doit pas figer une reponse que
     nadia-prof n'a pas rendue. -->
<div class="illustration" th:if="${dessin != null}">
    <img class="image-item" th:src="@{${dessin.source()}}" th:alt="${dessin.remplacement()}">
</div>
```

**Trois points de ce balisage ne sont pas de la mise en page** :

1. **`<img>` et pas un SVG inline.** Un SVG recopie dans le document serait du balisage de contenu
   melange au balisage de gabarit, et il ouvrirait la porte a un contenu qui pose ses propres
   regles CSS dans la page. Un `<img>` referme le dessin sur lui-meme.
2. **`th:src="@{...}"` et jamais une route ecrite en dur** : `ADR-0006`. Le chemin vient du
   contenu, l'URL est calculee.
3. **`alt` est declare, toujours, et sa valeur vient du modele.** Un `<img>` sans `alt` est un
   defaut, dans tous les cas de figure. Ce que cette valeur doit contenir est la section 9, et **ce
   n'est pas moi qui le decide**.

**Le nom des deux attributs de modele (`dessin.source()`, `dessin.remplacement()`) est une
illustration, pas une proposition de contrat.** L'architecture d'un item visuel est instruite en
parallele par `theo-archi` ; voir `DV-2`, section 11.

---

## 8. Les regles de feuille proposees

```css
/* Le bloc qui porte le dessin. Il centre, il espace, et rien d'autre : ni fond, ni bordure, ni
   rembourrage. Ce n'est pas une economie de style, c'est une mesure. Un aplat sous le dessin lui
   donnerait une surface declaree, donc un contraste calculable ; il n'apporte rien et il coute.
   Contraste de --recreo-texte : 14,09:1 contre --recreo-fond, 15,14:1 contre du blanc, quand le
   seuil d'un graphique non textuel est 3:1. L'aplat achete 1,05 point au-dessus d'un seuil deja
   franchi cinq fois, et il coute 16 px du budget vertical que la section 3 compte au pixel.
   Aucune bordure non plus : la bordure appartient au cabinet, qui dit "ici on presse", et
   l'illustration ne se presse pas. */
.illustration {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0.25rem 0 0.75rem;
}

/* Le dessin. Chacune de ces declarations sert FIG-1, et aucune n'est decorative :
   - max-height plafonne la seule dimension que le pli compte ;
   - max-width empeche le dessin de sortir de la zone sur un ecran etroit ;
   - height:auto et width:auto laissent le RAPPORT du fichier gouverner, jamais la feuille ;
   - object-fit: contain est la ceinture, si une regle future posait les deux dimensions.
   Mesure : rapport declare 1,0 rendu 1,0, rapport declare 3,0 rendu 3,0, aux dix largeurs, y
   compris la ou max-width mord. Une declaration "height" fixe casserait FIG-1 des la premiere
   largeur ou max-width mord.
   La valeur de max-height est un EMPLACEMENT, pas une proposition : elle depend de l'arbitrage du
   pli (section 3) et je ne la choisis pas a la place de nadia-prof. Les deux valeurs mesurees sont
   11rem (176px, le confort de lecture) et 3,3125rem (53px, ce que le pli laisse aujourd'hui apres
   un echec). */
.image-item {
    display: block;
    max-height: 11rem;
    max-width: 100%;
    height: auto;
    width: auto;
    object-fit: contain;
}
```

**Ce que ces deux regles ne declarent pas, deliberement** : aucune couleur, aucun filtre, aucune
ombre. `FIG-3` et `QUA-5` exigent qu'une figure lue en niveaux de gris donne la meme reponse ;
**une feuille qui recolorerait un dessin d'item pourrait en changer le sens sans que le contenu le
sache**. La feuille ne touche pas au dessin, elle le dimensionne.

---

## 9. Le texte de remplacement de l'image - une tension, posee et non resolue

> **Addendum du 2026-08-31, apres `CON-5`.** `nadia-prof` a pose depuis, le meme jour, une condition
> qui tranche cette section : *"le texte alternatif decrit ce qu'il y a a regarder, jamais la reponse
> ni ce qui permet de la deduire"*, **non automatisable, verifiee par elle item par item**. **C'est
> l'option (d) du tableau 9.2, bornee par l'option (c), et je m'y range sans reserve.** Le tableau
> ci-dessous reste lisible a sa place parce qu'il porte les couts de chaque option, dont l'un est de
> mon perimetre et renforce sa condition (le point 2 de la section 9.1 : `alt` s'affiche a l'ecran
> quand l'image ne charge pas). Ce que la section 9.3 laissait ouvert est clos par elle ; ce que
> j'ecris en 9.3 sur **une ligne injouable sans la vue** ne l'est pas et reste route.
> Voir `reponse-ines-douze-chiffres-traces.md`, section 6.

**C'est le point ou je m'arrete le plus tot, et c'est volontaire.** `nadia-prof` a nomme *"la
contrefacon par description"* comme la condition la plus dangereuse du visuel et **la garde dans sa
seule main**. Ce que j'apporte ici est ce qui est de mon metier - ce que `alt` fait reellement, et
ce que chaque option coute - et **rien de ce qui est du sien**.

### 9.1 Trois faits de mon perimetre, avant toute option

1. **Un `<img>` sans `alt` est un defaut**, dans tous les cas. La question n'est jamais *"faut-il un
   `alt`"* mais *"que contient-il"*.
2. **`alt` n'est pas un canal reserve aux lecteurs d'ecran.** C'est aussi ce que le navigateur
   **affiche a l'ecran, en clair**, quand l'image ne charge pas. **Un texte de remplacement qui
   decrit le dessin est donc lu par un enfant qui voit, des que le reseau flanche.** C'est un fait
   de rendu, pas un avis, et il a une consequence : l'option (b) ci-dessous ne se contente pas de
   contrevenir a la regle de `nadia-prof`, **elle affiche la reponse sur l'ecran d'un enfant
   voyant**. Elle tomberait meme si elle changeait d'avis sur la contrefacon.
3. **Ce depot n'a jamais fait ecouter un lecteur d'ecran.** `F11` et `ADR-0035` bloquent depuis s10
   l'emplacement du nom accessible du cabinet pour ce motif exact. **Je ne peux donc verifier
   aucune de ces options a l'oreille**, et je ne pretends pas le contraire.

### 9.2 Les quatre options, avec ce que chacune coute

| Option | Ce qu'elle donne | Ce qu'elle coute |
|---|---|---|
| **(a)** `alt=""` - dessin declare decoratif | **Aucune fuite possible, jamais.** C'est la seule option qui le garantit par construction | Un lecteur d'ecran **passe l'image en silence**. L'item devient sans reponse pour un enfant qui ne voit pas : il entend une question qui porte sur un objet dont rien ne dit qu'il existe |
| **(b)** `alt` decrivant le dessin | Un enfant qui n'y voit pas peut repondre | **C'est la contrefacon que `nadia-prof` a nommee**, et - point 2 ci-dessus - **c'est la reponse affichee en clair a tout enfant des que l'image ne charge pas** |
| **(c)** `alt` nommant le **role** du dessin, identique pour tous les items d'un paquet, ecrit par le gabarit et jamais par le contenu (par exemple *"le dessin de la question"*) | **Aucune fuite par construction** : la chaine ne porte aucune information d'item, donc elle ne peut pas en trahir une. Un lecteur d'ecran annonce qu'une image porte la question, au lieu de ne rien annoncer | **Ne rend pas l'item repondable** pour un enfant qui ne voit pas. Ce n'est pas une solution d'accessibilite, c'est une **frontiere nommee a haute voix** plutot que laissee muette |
| **(d)** `alt` ecrit item par item par le contenu, sous condition de `nadia-prof` | La seule option qui pourrait servir l'accessibilite sans contrefacon, **si une telle formulation existe** | Une **cle de contrat de contenu** (donc `theo-archi`), et **un audit item par item par `nadia-prof`** - la propriete *"cette phrase ne fait pas le geste que la ligne nomme"* n'est vue par aucun automate. C'est de la meme famille que `LIS-6` et `SUB-6`, c'est-a-dire `COMP-2` |

### 9.3 Ce que j'apporte et ou je m'arrete

**Ce que j'etablis** : (b) est disqualifiee par un fait de rendu de mon perimetre, independamment de
la regle pedagogique. (c) est la seule option qui soit **a la fois non muette et incapable de
fuir**, parce qu'elle ne transporte aucune information d'item.

**Ce que je ne tranche pas, et qui est la vraie question** : (a) et (c) reviennent au meme du point
de vue de l'enfant qui ne voit pas - **l'item n'a pas de reponse**. Autrement dit, **ouvrir le
palier 1 rend une ligne entiere injouable sans la vue**, et aucun texte de remplacement ne repare
cela : seul un autre canal le pourrait, et ce produit n'en a aucun. **C'est un fait de produit, pas
un choix de forme, et il ne m'appartient pas.** Il va a `nadia-prof` pour la porte, a `camille-po`
pour le perimetre, et il vaut d'etre pose a l'utilisatrice avant un lot plutot qu'apres.

**Ce que je demande, au minimum, quelle que soit l'option retenue** : que le gabarit **ne fige
aucune de ces quatre options** tant que le choix n'est pas rendu. C'est pourquoi le balisage de la
section 7 ecrit `th:alt="${...}"` et ne met aucun litteral : un `alt=""` ecrit aujourd'hui dans un
gabarit serait un arbitrage rendu par un lot, ce que ce depot sanctionne.

---

## 10. Accessibilite visuelle

### 10.1 Contraste

Toutes les valeurs sont calculees sur les jetons declares dans `:root`, pas estimees a l'oeil.

| Paire | Rapport | Seuil applicable | Etat |
|---|---|---|---|
| `--recreo-texte` sur `--recreo-fond` (le dessin sur la page) | **14,09:1** | 3:1 (graphique non textuel) | tres large |
| Coche verte sur l'aplat de reussite | 4,56:1 | 3:1 | tenu |
| Croix rouge sur l'aplat d'echec | 4,92:1 | 3:1 | tenu |
| `--recreo-texte` sur l'aplat de reussite | 13,46:1 | 4,5:1 (texte) | tres large |
| `--recreo-texte` sur l'aplat d'echec | 13,24:1 | 4,5:1 (texte) | tres large |

**La regle que je propose pour un dessin d'item, et c'est une regle de gabarit generique, pas une
regle pour les figures planes** : le trait d'un dessin sert la meme fonction qu'un signal - il doit
etre reconnu, pas seulement vu -, donc il tient **3:1 au moins contre `--recreo-fond`**. La feuille
garantit la surface ; le trait est declare par le fichier servi, donc par le contenu. **`DV-3`**,
section 11.

**Et la regle qui compte davantage, celle que `FIG-3` et `QUA-5` posent deja** : la couleur ne
distingue jamais deux formes, et une figure lue en niveaux de gris donne la meme reponse. **Mes deux
regles de feuille ne recolorent rien** : c'est ce qui permet a cette condition de rester verifiable
sur le fichier servi.

*Le materiau de reprise en donne le contre-exemple exact, et c'est `nadia-prof` qui l'a trouve :
`contenu-repris/associer/cp-formes.yml` associe le glyphe carre orange a la cible "rectangle" et le
glyphe carre bleu a la cible "carre". La meme forme, deux reponses, departagees par la couleur. Je
le cite comme la demonstration qu'une regle de couleur non tenue casse la competence elle-meme, pas
seulement l'accessibilite.*

### 10.2 Tailles

| Element | Taille | Ce qui la tient |
|---|---|---|
| `.enonce` | 2 rem (32 px) | aucun plancher propre ; c'est **`.proposition` qui lui est comparee**, jamais l'inverse (`leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`) |
| `.proposition` | 2 rem (32 px) | plancher litteral de 2 rem, **plus l'unique exception a 1,875 rem** sous le repli a quatre colonnes (arbitrage de `nadia-prof` du 2026-08-11, condition 7) |
| `.consigne`, `.texte-support`, `.indice`, `.verdict` | 1,25 rem (20 px) | plancher general de lisibilite de `TailleDesTextesEtBoutonsTest` |
| `.icone-signal` | 3 rem de boite, **48 px rendus** | |
| **Le dessin** | **plafonne, valeur a arbitrer** | 176 px au confort de lecture, **53 px** a ce que le pli laisse aujourd'hui apres un echec |

**Le fait qui doit etre lu avec ce tableau, et c'est la regle de mon mandat prise a rebours** : ce
que l'enfant doit reconnaitre en premier sur un item visuel **est le dessin**. A 53 px, **il est
plus petit que le signal juste/faux (48 px de cote) d'a peine 5 px, et plus petit qu'une ligne
d'enonce et ses marges (52 px)**. Ce n'est pas une hierarchie, c'est son ecrasement. Section 3.4.

### 10.3 Cible pressable

**Aucune cible pressable n'est ajoutee, deplacee ni redimensionnee par cette proposition.** Le
dessin **ne se presse pas** : il ne porte ni `data-role`, ni `href`, ni `type="submit"`, et il n'est
pas dans le cabinet.

**Consequence sur `ADR-0017`, verifiee et non supposee** : la population de pressables du garde-fou
derive de `button, a[href]` lus dans les gabarits. **Un `<img>` n'y entre pas, et le rapport de
saillance declare entre une action et la sortie est donc inchange au caractere pres** - une
proposition reste a 2 rem et `0.5rem 1.25rem` de rembourrage, les deux sorties a `0.5rem 1.1rem`
depuis `SOR-1` a `SOR-4`. **Aucune de ces valeurs n'est touchee par cette proposition** ; je les
cite pour dire ce qui ne bouge pas, pas pour les rouvrir.

**Et ce que le garde-fou ne voit pas, que je nomme parce que c'est justement la ou j'ai deja ete
refusee deux fois** : `ADR-0017` ne compare ni la couleur, ni la position, ni la largeur des
libelles. **L'arrivee d'un dessin change la saillance de l'ecran sans changer une seule declaration
de bouton** - c'est desormais l'objet le plus grand de l'ecran, avant le meuble. **Je juge que c'est
correct**, parce que sur un item visuel le dessin *est* ce que l'enfant doit regarder en premier -
mais **c'est un jugement, il tombe sous les points 2, 3 et 7 de la section "Ce que cette regle ne
couvre pas" d'`ADR-0017`, et `nadia-prof` en reste la seule garde, en jouant.**

---

## 11. Dependances ouvertes

Ni une decision ni une demande : ce que ma proposition suppose et que je n'ecris pas moi-meme.

| # | Ce qui manque | A qui |
|---|---|---|
| **DV-1** | **La taille intrinseque du dessin servi.** Mes trois declarations ne font jamais grandir un dessin au-dela de sa taille naturelle : un fichier declare a 100x100 restera a 100 px meme sous un plafond de 176. Le fichier servi doit donc declarer ses dimensions **au moins egales au plafond retenu**. C'est ce que `FIG-1` exige deja - *"ses proportions sont declarees par le contenu"* - et c'est une propriete du fichier, pas de ma feuille | `theo-archi`, `nadia-prof` |
| **DV-2** | **Comment un item porte une donnee de dessin.** `camille-po` ecrit qu'aucun `ADR` ne le dit. Mon balisage nomme `dessin.source()` et `dessin.remplacement()` **a titre d'illustration** : je ne propose aucun contrat et je ne presume pas la reponse. Si le contrat rendu differe, seuls les deux `th:` du gabarit changent ; **aucune regle de feuille de la section 8 n'en depend** | `theo-archi` |
| **DV-3** | **Le contraste du trait du dessin servi.** Ma feuille garantit la surface (`--recreo-fond`, 14,09:1 avec `--recreo-texte`) ; elle ne peut pas garantir la teinte d'un trait qu'un fichier de contenu apporte. Il faut soit une condition d'ecriture (famille `FIG-3`), soit un garde-fou qui lise les `.svg` servis | `nadia-prof`, `theo-archi`, `yanis-qa` |
| **DV-4** | **La longueur maximale d'une proposition, sur `LIS` et au-dela.** Section 4.3 : c'est elle, et non le texteSupport, qui decide du budget vertical, et aucune condition ne la borne. Meme famille que `D4` a s24 | `nadia-prof`, `camille-po` |
| **DV-5** | **Une explication de revelation peut-elle designer une partie du dessin ?** Aujourd'hui non : `.explication` est une chaine, `.mot-designe` designe un segment d'enonce. Je ne propose rien ; je nomme la limite avant qu'un lot la decouvre | `theo-archi`, `nadia-prof` |
| **DV-6** | **Le texte de remplacement** : **le choix entre les quatre options est rendu par `CON-5` le 2026-08-31** et cette moitie-la est close. **Ce qui reste ouvert** est le fait de produit que `CON-5` ne repare pas : ouvrir le palier 1 rend une ligne injouable sans la vue, et aucun texte alternatif conforme a `CON-5` ne peut y changer quoi que ce soit | `camille-po`, et l'utilisatrice pour le fait de produit |
| **DV-7** | **Le pli lui-meme, pour un item qui porte un dessin.** 53 px apres un echec a la reference, aucun sous 720 px. C'est un arbitrage de l'utilisatrice (`DEC-0051`), sorti de la porte pedagogique par `DEC-0053` : ni `nadia-prof` ni moi ne pouvons le deplacer | `sofia-sm`, pour remontee a l'utilisatrice |
| **DV-8** | **Les onze items de `bandit-manchot-comprehension-cm1` sous le pli** a la largeur de reference, sur les treize vus (section 4.4). Jeu ne a s22, hors du comptage de `DEC-0051` | `nadia-prof`, `camille-po` |
| **DV-9** | **La revelation de l'ecran de saisie**, 11 px sous le pli a 744 px sur deux items, et sur les onze items entre 576 et 720 px (section 5.4) - defaut, ou trou du critere qui vaudrait pour les 31 jeux | `nadia-prof`, `sofia-sm` |
| **DV-10** | **Le slug du repertoire de sprint** : je me suis alignee sur `s25-le-premier-palier-visuel`, celui de `theo-archi`. S'il change au planning, ce fichier, le prototype, les quatre dessins et les quatre releves suivent | `sofia-sm` |

---

## 12. Ce que je ne prononce pas

- **Le Done pedagogique.** Cette proposition ne se valide pas contre l'avis de `nadia-prof`, y
  compris sur les points ou elle corrige exactement ce qu'un de ses arbitrages a demande.
- **Qu'une ligne s'ouvre ou ne s'ouvre pas.** Mes chiffres disent ce que le pli laisse ; ce qu'on en
  fait est a `camille-po` pour le perimetre et a `nadia-prof` pour la porte.
- **Qu'un jeu deja vert cesse de l'etre.** La section 4.4 pose un fait mesure sur
  `bandit-manchot-comprehension-cm1` et rien d'autre : je ne retire aucun Done, je n'en anticipe
  aucun, et je ne touche pas a la matrice.
- **La place de la liste des tentatives** (section 5.2), que `nadia-prof` a explicitement refuse de
  trancher et que je ne tranche pas davantage par un chiffre.
- **Le contenu d'un texte de remplacement** (section 9), ni celui d'un dessin, ni son exactitude.
- **Aucune decision d'architecture** : le contrat d'un item visuel est a `theo-archi`.

---

## Porte technique

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur, sur l'arbre de ce worktree :
**BUILD SUCCESS, 937 tests, 0 echec** (931 surefire - 78 `domain`, 13 `application`, 349
`adapter-web`, 355 `adapter-content`, 136 `bootstrap` - et 6 failsafe). Perimetre : le reacteur
complet, aucun `-pl`, donc aucun `-am` a citer.

**Cet arbre porte une fusion de `s25-theo`, et le motif est une morsure de garde-fou.** Ma premiere
execution a echoue, et sur le bon motif : `ToutesLesReferencesDeDecisionResolventTest` (`ADR-0066`,
clause 1) refuse une reference de decision qui ne resout pas, et ma reponse sur les chiffres traces
cite `ADR-0073` quatre fois - un `ADR` qui vit sur `s25-theo` et pas encore sur `round-two`. **Je
n'ai ni contourne le garde-fou ni retire la citation** : un document sur la clause 4 qui ne la
nommerait pas serait illisible. J'ai fusionne la branche qui la porte, pour que ma porte technique
mesure un arbre ou la reference existe reellement. **Cette fusion ne valide pas le lot de
`theo-archi` et n'en juge aucun fichier** ; `sofia-sm` reste seule a ordonner les fusions.

**Les 937 tests sont donc ceux de mon arbre apres cette fusion**, dont les 11 de
`LIllustrationDeContenuResoutEtAucuneNeDortTest` qui viennent de lui. **Mon lot n'ajoute et ne
retire aucun test.**

**Ce commit n'ajoute et ne retire aucun test, et c'est attendu** : il ne touche aucun module. Il
n'ajoute aucune classe CSS ni aucun jeton de gabarit servi, donc **aucun test structurel
d'`adapter-web` n'a de population a re-deriver** - `TailleDesTextesEtBoutonsTest`,
`LActionNeDeclarePasMoinsQueLaSortieTest`, `HabillageDesEcransTest` et `EcranDeSaisieTest` lisent
des gabarits et une feuille que ce commit laisse intacts. La regle *"les tests avec le code, jamais apres"* ne mord pas
ici parce qu'il n'y a pas de code.

**La version de JDK est celle que la machine virtuelle des tests rapporte d'elle-meme**, et non
celle qu'une toolchain declare : c'est ce qu'`ADR-0071` decide et ce que son garde-fou verifie a
chaque build, en comparant la version exigee par le pom agregateur a celle de l'execution.

**Ce que je peux affirmer, et rien de plus** : **la machine virtuelle qui a execute ces tests
rapporte la version majeure 21**. Ce n'est pas une lecture de ma part - c'est ce que
`LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest` a verifie a cette execution, en
comparant `Runtime.version().feature()` a la version de toolchain que le pom agregateur reclame,
par une egalite et non par un plancher. **Je ne cite aucun numero de correctif** : le garde-fou ne
lit que la version majeure, donc je n'ai lu que celle-la. C'est exactement la discipline que ma
formule de s24 n'avait pas.

*Un fait de poste que je verse parce qu'il a l'air d'une anomalie et n'en est pas* : l'enveloppe
`outils/mvn-jdk21.sh` a signale que **le JDK sur lequel Maven lui-meme tourne rapporte 25**.
`ADR-0071`, section 4, le declare explicitement conforme - une toolchain ne gouverne pas la machine
virtuelle de Maven, et rien n'exige que ce soit la meme. **Ce qui compte est celle des tests, et le
garde-fou l'a mordue.**

*Ce que cette porte ne couvre pas, et qui est la raison d'etre de ce document : `mvn verify` ne voit
aucun des chiffres ci-dessus. Un CSS qui compile n'est pas un CSS qui se voit, et les trois releves
de ce commit sont hors CI par construction - ils ont besoin d'un navigateur et, pour deux d'entre
eux, d'une application servie.*

---

## Impact

- `squad/sprints/s25-le-premier-palier-visuel/proposition-ines-palier-1-du-visuel.md`
  (ce fichier) : cree.
- `squad/sprints/s25-le-premier-palier-visuel/prototype-ines-palier-1-image-fixe.html` : cree -
  vehicule de mesure, jamais servi.
- `squad/sprints/s25-le-premier-palier-visuel/prototype-figure-carree.svg` et
  `prototype-rangee-large.svg` : crees - deux rapports de geometrie, jamais du contenu.
- `squad/outils/ines-design/mesurer-l-illustration.py` : cree.
- `squad/outils/ines-design/mesurer-l-ecran-de-saisie-servi.py` : cree.
- `squad/outils/ines-design/mesurer-l-ancrage-du-texte-support.py` : cree.
- `squad/conformite/releves-s25/s25-ines-palier-1-image-et-lis-dix-largeurs.json` : cree,
  120 mesures.
- `squad/conformite/releves-s25/s25-ines-ecran-de-saisie-servi-dix-largeurs.json` : cree,
  440 lectures.
- `squad/conformite/releves-s25/s25-ines-ancrage-comprehension-cm1-744.json` : cree, 13 items a une
  seule largeur, perimetre ecrit dans le fichier.
- `squad/sprints/s24-trois-paquets-et-ouverture-de-la-saisie/proposition-ines-ecran-de-saisie.md` :
  **correction datee ajoutee en fin de fichier**, rien reecrit au-dessus - la formule sur la version
  de JDK qu'`ADR-0071` disqualifie, routee a moi par `relecteur-pedagogique`.
- `squad/sprints/s25-le-premier-palier-visuel/reponse-ines-douze-chiffres-traces.md`, ses deux
  dessins de demonstration, `squad/outils/ines-design/mesurer-un-chiffre-trace.py` et
  `squad/conformite/releves-s25/s25-ines-chiffres-traces.json` : crees par la seconde question de ce
  sprint, listes dans leur propre section Impact.
- **Aucun fichier de `domain/`, `application/`, `adapters/`, `bootstrap/` n'a change.** Aucun
  controleur, aucun contrat, aucun gabarit servi, aucune ligne de `recreo.css`, aucun test.
