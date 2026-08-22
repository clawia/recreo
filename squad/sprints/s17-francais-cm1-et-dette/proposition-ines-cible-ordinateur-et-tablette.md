# Proposition - inventaire d'appareils pour la cible ordinateur et tablette

- **Auteur** : ines-design.
- **Date** : 2026-08-22.
- **Worktree dedie** : `recreo-ines-cible-appareils`, branche `s17-ines-cible-ordinateur-tablette`,
  forkee depuis `round-two` a `db2bff6` (verifie par `git log --oneline -1` sur le worktree neuf
  au moment de sa creation).
- **Ce que ce document fait** : il propose des chiffres et une methode, il ne tranche ni la
  reference technique (`camille-po`, `DEC-0016` et sa `DEC` de remplacement en cours de redaction)
  ni le Done pedagogique (`nadia-prof`). Aucun gabarit ni feuille de style n'est modifie.
- **Ce que ce document ne fait pas** : il ne mesure aucun rendu de l'application - je n'ai acces a
  aucun chiffre de rendu, c'est `nadia-prof` qui mesurera sur la reference une fois fixee. Il ne
  leve aucun refus deja prononce sur un pli mesure a 640 px : un refus ne se leve que par une
  mesure sur la nouvelle reference, pas par ce document.

## 0. Ce que je reprends de s10, et ce qui change

`squad/sprints/s10-la-forme-du-bandit-manchot/mesure-ines-hauteur-de-pli.md` a etabli une methode
(distribution d'appareils, sourcee et datee, pire cas nomme avec ses exclusions) et une conclusion
sur 55 telephones : 640 px est le plancher d'une generation d'appareils largement sortie d'usage,
pas un pire cas contemporain. J'y avais **explicitement exclu les neuf tablettes** de la meme
source au motif que la reference etait 375 px de large et qu'"une tablette n'est pas le pire cas de
hauteur qui nous occupe" - un motif qui ne tient plus des lors que la tablette devient elle-meme un
support vise.

**Ce qui change ici, et qui n'existait pas en s10** : le telephone sort entierement du perimetre de
reference ("le telephone n'est plus la reference", arbitrage du 2026-08-22). Je ne mesure donc plus
une distribution de telephones, mais deux familles d'appareils neuves - ordinateur et tablette -
dont aucune n'avait ete chiffree jusqu'ici. Et surtout : **la source que j'avais utilisee en s10
(le fichier `Device.ts` de Puppeteer) donne une hauteur de dalle, pas une hauteur utilisable une
fois le navigateur retire** - un point que je n'avais pas eu a demeler en s10 parce qu'il n'y
changeait rien pour la conclusion demandee. Je le demele ici, parce que la question posee m'y
oblige explicitement et parce que, entre tablette et ordinateur, il ne se demele pas de la meme
facon sur les deux familles.

## 1. Methode et sources

### 1.1 Tablette

Trois sources, recuperees ce jour :

- **`packages/puppeteer-core/src/common/Device.ts`, meme depot que s10** (`puppeteer/puppeteer`,
  branche `main`) : les neuf tablettes que j'avais exclues en s10 (iPad et ses variantes, Galaxy
  Tab S4, Kindle Fire HDX, Nexus 7/10, Blackberry PlayBook). Ces valeurs sont des **dimensions de
  dalle** (voir 1.3), pas des viewports post-navigateur.
- **`ios-resolution.com`**, consulte ce jour : tableau "Logical Width" / "Logical Height" en
  points CSS pour chaque modele d'iPad depuis le premier (2010) jusqu'aux iPad Air et Pro de
  8e generation. C'est une source d'ingenierie Apple (les points logiques sont une constante
  publiee par Apple pour chaque modele, pas une mesure tierce), donc plus fiable et plus a jour
  que la liste Puppeteer pour cette famille specifique - celle-ci s'arrete a l'iPad 7e generation
  (2019) et ne connait ni l'iPad mini 6e/7e generation (2021+, le plus etroit du catalogue actuel)
  ni les iPad 10e/11e generation.
- **StatCounter, "Tablet Screen Resolution Stats Worldwide - July 2026"**, consulte ce jour : les
  six resolutions les plus frequentees, toutes plateformes confondues (pas seulement Apple). Utile
  pour verifier qu'une dimension identifiee par ailleurs (iPad classique 768x1024, par exemple)
  reste effectivement presente dans le trafic reel, et pas seulement dans un catalogue de modeles
  theorique.

### 1.2 Ordinateur

Une seule source disponible pour la dalle, **aucune source equivalente pour le navigateur** :

- **StatCounter, "Desktop Screen Resolution Stats Worldwide - July 2026"**, consulte ce jour. Le
  libelle "Desktop" de StatCounter n'exclut pas les ordinateurs portables : ses quatre categories
  d'appareils (Desktop, Mobile, Tablet, Console) regroupent les ordinateurs fixes et portables sous
  la meme etiquette, faute d'un moyen fiable de les distinguer par en-tete HTTP. C'est exactement
  la famille visee ici ("ordinateur").
- Il n'existe pas d'equivalent au fichier `Device.ts` de Puppeteer pour un navigateur de bureau :
  Puppeteer n'a pas besoin d'emuler un ordinateur, il tourne dessus nativement. Aucune source que
  j'ai trouvee ne publie, avec la meme autorite que les points logiques d'Apple, une hauteur de
  navigateur "de reference" pour Windows ou macOS - **et pour une raison structurelle, pas
  seulement documentaire** (voir 1.3).

### 1.3 La distinction que la question pose, et pourquoi elle se traite differemment sur les deux familles

Les valeurs Puppeteer de s10 et d'ici (téléphone comme tablette) sont des **dimensions de dalle**
- j'en ai la preuve directe : elles concordent au pixel pres avec les points logiques publies par
Apple sur `ios-resolution.com` pour chaque modele (iPad 6e generation : 768x1024 dans les deux
sources ; iPhone 6/7/8 : 375x667 dans les deux, chiffre deja cite en s10). Ce ne sont donc pas des
hauteurs de `window.innerHeight` mesurees barre d'adresse deployee, ce sont des tailles d'ecran.

**Sur telephone et tablette, ce n'est pas un probleme aussi grave qu'il y parait**, pour une raison
que tout developpeur web connait : Safari et Chrome mobiles **retractent leur barre d'adresse au
defilement**, convergeant vers une hauteur utilisable proche de la dalle entiere. Ce n'est pas
l'etat au chargement de la page (barre deployee, quelques dizaines de pixels de moins), mais c'est
l'etat qu'un enfant qui a deja commence a interagir avec l'ecran atteint rapidement - et c'est,
implicitement, la convention que ce depot applique deja depuis `DEC-0016` sans jamais l'avoir
nommee : 640 px n'a jamais ete une hauteur de `window.innerHeight` mesuree barre deployee, c'est la
hauteur de dalle du Galaxy S III et consorts. Je reconduis cette meme convention pour la tablette,
par coherence avec le reste du produit, plutot que d'inventer une regle neuve non appliquee
ailleurs.

**Sur ordinateur, cette convention ne tient plus, et je le dis sans l'habiller** : un navigateur de
bureau (Chrome, Edge, Firefox, Safari) **ne retracte rien**. La bande d'onglets, la barre
d'adresse/d'outils et, sous Windows, la barre des taches par defaut restent visibles en permanence,
quel que soit le defilement - il n'existe pas d'etat "convergé" equivalent a celui du mobile. Une
hauteur de dalle d'ordinateur **surestime donc la hauteur utilisable d'un montant qui n'a pas
d'equivalent sur les deux autres familles**, et cet ecart ne se resorbe jamais avec l'usage.

## 2. Inventaire

### 2.1 Tablette (portrait)

| Largeur (pt CSS) | Hauteur (pt CSS) | Appareil | Source |
|---|---|---|---|
| 600 | 1024 | Blackberry PlayBook | Puppeteer |
| 744 | 1133 | iPad mini (6e/7e generation, 2021+, le boitier plein ecran actuel) | ios-resolution.com |
| 600 | 960 | Nexus 7 | Puppeteer |
| 768 | 1024 | iPad (1re a 6e generation, Air, Air 2, mini 2 a 5, Pro 1re gen 9,7") | Puppeteer + ios-resolution.com, concordants |
| 712 | 1138 | Galaxy Tab S4 | Puppeteer |
| 601 | 1007 | (non identifie - voir reserve ci-dessous) | StatCounter, 4,27 % |
| 810 | 1080 | iPad 7e a 9e generation (10,2") | Puppeteer (7e gen) + ios-resolution.com (7e a 9e gen), concordants |
| 820 | 1180 | iPad Air 4e/5e/7e generation, iPad 10e/11e generation (10,9") | ios-resolution.com |
| 834 | 1194 / 1210 | iPad Pro 11" (3e a 8e generation) | ios-resolution.com |
| 800 | 1280 | Kindle Fire HDX, Nexus 10 | Puppeteer |
| 1024 | 1366 / 1376 | iPad Air 13", iPad Pro 12,9"/13" | ios-resolution.com |

**Reserve que je nomme sans la resoudre.** La ligne StatCounter 601x1007 (4,27 % du trafic tablette
mondial, juillet 2026) ne correspond a aucun modele que j'ai pu identifier dans les deux autres
sources - ni un iPad, ni un des appareils Android de la liste Puppeteer. Sa largeur, a mi-chemin
entre un telephone et une tablette, me fait soupconner soit un phablette ou un pliable classe
"tablette" par une regle de categorisation qui m'echappe, soit un artefact de mesure. **Je ne
l'ecarte pas du tableau, comme le JioPhone 2 ne l'avait pas ete en s10, mais je n'appuie aucune
conclusion sur cette seule ligne.**

**Pire cas de largeur, tablette, appareils identifies** : **744 px** (iPad mini 6e/7e generation).
C'est le plus etroit du catalogue Apple actuel - tout autre iPad, quelle que soit sa generation,
est a 768 px ou plus. Sur Android, le plus etroit identifie est 600 px (Nexus 7, PlayBook), mais ce
sont des appareils du debut des annees 2010, du meme ordre d'anciennete que les telephones que
j'avais deja juges hors d'usage courant en s10 - je ne les retiens pas comme pire cas contemporain
pour le meme motif.

**Pire cas de hauteur, tablette, appareils identifies** : **1024 px** (iPad classique, 768x1024).
Cette dimension reste tres presente dans le trafic reel (9,05 % des vues tablette selon StatCounter
juillet 2026, la ligne la plus frequentee du tableau), ce qui exclut l'hypothese qu'elle serait
elle aussi une antiquite sans usage courant, contrairement aux telephones a 640 px de s10 : la
duree de vie d'un iPad est plus longue que celle d'un telephone, et le format 768x1024 couvre des
modeles vendus de 2010 a 2019.

### 2.2 Ordinateur (dalle, avant deduction du navigateur)

| Largeur (px CSS) | Hauteur (px CSS) | Part de marche (juillet 2026, StatCounter) | Lecture |
|---|---|---|---|
| 1920 | 1080 | 22,41 % | le plus frequent, largement |
| 1536 | 864 | 7,3 % | panneau 1920x1080 physique rapporte a 864 px CSS sous une mise a l'echelle Windows a 125 % - tres frequent sur portable recent a forte densite |
| 1366 | 768 | 5,45 % | portable d'entree de gamme, format historiquement dominant |
| 1280 | 720 | 5,36 % | portable ou moniteur d'entree de gamme, plus ancien ou plus modeste |
| 1280 | 1200 | 3,71 % | moniteur professionnel haut-format, ne contraint rien ici |
| 384 | 832 | 4,33 % | **ecarte** - un ratio de telephone classe sous "Desktop", vraisemblablement une erreur de categorisation d'appareil (meme famille de doute que la ligne tablette 601x1007) |

**Pire cas de dalle identifie : 1280x720.** Ce n'est pas un cas isole ou un accident statistique :
le meme resultat en pixels CSS peut aussi bien provenir d'un panneau natif 1280x720 (materiel
modeste, budget ou ancien - le type meme d'"appareil disponible a la maison" nomme par `DEC-0007`)
que d'un panneau 1920x1080 mis a l'echelle a 150 % (frequent sur portable 13-14" a forte densite
de pixels, Windows recommandant souvent une echelle superieure a 100 % par defaut sur ce type
d'ecran). **Deux chemins materiels distincts convergent vers la meme hauteur de dalle** : ce n'est
donc pas une curiosite de longue traine, c'est une hauteur qui se reproduit structurellement.

### 2.3 Ordinateur, ce qui reste au document une fois le navigateur retire - la partie que je ne peux pas sourcer avec la meme rigueur

Je n'ai trouve aucune source de la meme autorite que les points logiques Apple ou que la
distribution StatCounter pour chiffrer la hauteur reellement occupee par la chrome du navigateur et
de l'OS. Je le nomme composante par composante, avec un ordre de grandeur assume comme **une
estimation non verifiee**, pas une mesure :

- **Barre des taches Windows**, visible par defaut et non masquee automatiquement : de l'ordre de
  40 a 50 px selon la version et l'echelle d'affichage. Je n'ai pas pu confirmer un chiffre precis
  aupres d'une source Microsoft de premiere main dans cette session (page bloquee par un controle
  anti-robot au moment de la consultation) - je le signale plutot que d'ecrire un chiffre que je
  n'ai pas verifie comme s'il l'etait.
- **Bande d'onglets + barre d'adresse/outils d'un navigateur Chromium**, sans barre de favoris,
  zoom a 100 %, fenetre maximisee (pas de bordure de fenetre supplementaire) : je n'ai pas de
  chiffre source du tout ici, seulement un ordre de grandeur de connaissance generale du metier
  (couramment de 70 a 110 px), que je ne fais pas passer pour verifie.
- **macOS** : barre de menu fixe a 24-25 px en haut ; le Dock, souvent configure en masquage
  automatique, n'est pas compte par defaut dans cette estimation.

**Somme estimee, non verifiee : de l'ordre de 110 a 160 px sur Windows avec Chrome ou Edge,
fenetre maximisee, sans barre de favoris.** Applique au pire cas de dalle (720 px), cela donne une
**hauteur utilisable estimee entre 560 et 610 px** - un ordre de grandeur que je nomme comme une
fourchette a verifier par une mesure reelle (navigateur reel, pas une emulation), pas comme un
chiffre que je fixe.

**Ce que cette fourchette, meme incertaine, met en evidence et que je ne peux pas taire** : elle se
situe **sous ou tout au bord de la reference actuelle de 640 px**, deja jugee fragile sur plusieurs
jeux en s10. Un ordinateur n'est donc pas automatiquement le cas le plus confortable en hauteur
sous pretexte qu'il a le plus grand ecran : sur l'axe hauteur precisement, il pourrait etre **le
pire cas de l'ensemble ordinateur + tablette**, alors qu'il est de tres loin le plus confortable sur
l'axe largeur (2.4). C'est l'inverse de l'intuition "un plus grand ecran donne plus de place", et
c'est une raison de plus pour que ce chiffre soit verifie par une mesure reelle avant d'etre fige
dans une decision, plutot que suppose a partir de la seule taille de l'ecran.

## 3. Pire cas de hauteur et de largeur, sur l'ensemble ordinateur + tablette

- **Pire cas de largeur : 744 px** (iPad mini 6e/7e generation, portrait, section 2.1). Toute
  configuration d'ordinateur du tableau 2.2 est plus large (1280 px au plus etroit) : l'axe
  largeur est donc entierement porte par la tablette, l'ordinateur n'y contraint jamais rien.
  L'orientation paysage d'une tablette ne change pas cette conclusion : elle ne fait qu'elargir
  l'appareil, jamais le retrecir.
- **Pire cas de hauteur : porte par l'ordinateur, avec une reserve d'incertitude que je nomme deux
  fois plutot qu'une.** La dalle la plus basse identifiee (720 px) mene, une fois le navigateur
  retire, a une fourchette estimee de 560 a 610 px (section 2.3) - **une estimation, pas une
  mesure**. La tablette la plus basse identifiee reste a 1024 px sans deduction supplementaire
  (l'auto-masquage de la barre d'adresse mobile rend la dalle elle-meme un proxy raisonnable de
  l'utilisable, comme deja pratique implicitement sur telephone). Meme en orientation paysage, la
  plus petite hauteur de tablette que le tableau 2.1 laisse deduire (iPad mini couche : 744 px) reste
  au-dessus de la fourchette ordinateur. **Le pire cas de hauteur n'est donc, sur les donnees dont
  je dispose, jamais porte par une tablette : il est porte par l'ordinateur, et c'est le point le
  moins solidement chiffre de tout ce document.**

## 4. Reference unique ou pire cas sur un ensemble - ce que chacune coute

La question n'est pas neutre cette fois, pour une raison qui n'existait pas quand la reference
etait un telephone : **le pire cas de largeur et le pire cas de hauteur viennent desormais de deux
familles d'appareils differentes** (tablette pour l'un, ordinateur pour l'autre), alors qu'en s10
les deux venaient de la meme famille de telephones. Cela change ce que chaque option coute.

**Reference unique (une largeur, une hauteur).**
- *Cout* : nul cote outillage - c'est exactement la mesure que `nadia-prof` sait deja produire, un
  seul rendu par etat de jeu, comme aujourd'hui.
- *Ce qu'elle force a accepter* : un point combinant le pire cas de largeur (744 px) et le pire cas
  de hauteur (560-610 px estimes) **ne correspond a aucun appareil reel** - aucune tablette de
  744 px de large n'a une hauteur utilisable aussi basse, aucun ordinateur a cette hauteur n'est
  aussi etroit. Un tel point est **plus contraignant que n'importe quel appareil reellement tenu
  par un enfant**, ce qui est une marge de securite, mais une marge qui se paie en espace perdu
  partout ou aucun appareil reel n'atteint ce double pire cas a la fois. Ce depot a deja fait ce
  choix une fois sans le nommer : le couple 360x640 de `DEC-0016` n'est pas non plus un appareil
  unique (aucun telephone de la distribution de s10 n'a exactement ces deux dimensions ensemble,
  seul le Galaxy S8 s'en approche a 740 px pres) - le precedent existe, je le signale pour que la
  decision soit prise en connaissance de cause, pas en le presentant comme neuf.

**Pire cas sur un ensemble d'appareils (chaque appareil mesure sur sa propre paire largeur/hauteur).**
- *Cout* : multiplie le nombre de rendus a produire par `nadia-prof`, potentiellement par autant
  d'appareils retenus. Le cout n'est pas theorique : elle a deja consacre 8 parties et 80 tirages
  au clavier pour mesurer un seul format sur un seul paquet (`SYN-5`, s17). Ajouter des formats
  multiplie ce chiffre, pas seulement le temps de rendu.
- *Ce qu'elle donne en echange* : une mesure fidele a chaque appareil reel, qui peut reveler qu'un
  gabarit passe sur ordinateur et echoue sur tablette (ou l'inverse) - une information qu'une
  reference unique fictive masque completement, puisqu'elle ne represente jamais un appareil que
  l'enfant tient vraiment.

Je ne tranche pas entre les deux : je nomme que le cout de la premiere option est un espace perdu
sur tout appareil reel, et le cout de la seconde est un nombre de mesures qui grandit avec le
nombre d'appareils retenus - un cout que `nadia-prof` a deja mesure a ses depens ce sprint.

## 5. Ce que le clavier et le survol changent

**Le focus clavier devient plus frequemment exerce, et ma proposition de s17 n'a pas besoin d'etre
refaite pour ca.** `proposition-ines-focus-clavier.md` (S17-1, implementee - anneau interieur,
`currentColor`, uniquement a `:focus-visible`, `squad/sprints/s17-francais-cm1-et-dette/review.md`,
"neuf familles sur dix percoivent l'anneau interieur, mesure au clavier reel") a ete concue pour un
etat que le tactile ne produit jamais spontanement (`:focus-visible` naissant d'une navigation au
Tab) et que la souris ne declenche generalement pas non plus (heuristique navigateur deja citee
dans la proposition). Sur ordinateur, ou le clavier redevient un chemin de navigation courant sans
necessiter d'accessoire externe (contrairement a une tablette, ou il suppose un clavier physique
branche), cet etat sera simplement **sollicite plus souvent qu'il ne l'etait sur telephone** - la
proposition tient sur le fond, elle devient seulement plus utile, pas differente. Le calcul de
contraste (section 3 de cette proposition, seuil WCAG 1.4.11 a 3:1) ne depend d'aucune propriete
d'appareil : il reste valide a l'identique.

**Le survol reel souleve une question que je n'avais pas a poser sur telephone, et que je pose
sans la trancher.** La premisse acceptee par `nadia-prof` le 2026-08-18 ("survoler ou presser une
puce ne change jamais rien a son apparence, nulle part dans cette application") a ete etablie dans
un contexte ou le survol par souris etait un evenement rare et non soutenu (touche a peine
quelques appareils a la marge, jamais un chemin principal). Devenu le chemin d'interaction
principal sur ordinateur, un survol soutenu et volontaire qui ne produit **visuellement rien du
tout** est une experience differente de celle d'un survol accidentel et bref sur telephone : un
enfant qui deplace sa souris sur un bouton et n'y voit aucune reaction pourrait raisonnablement se
demander si le bouton est actif. Je ne rouvre pas cette premisse moi-meme - ce n'est pas mon
jugement a faire, et le gel de design pose au sprint 9 (reouvert au sprint 10) n'est pas quelque
chose que je decide seule de reconduire ou de lever ici. Je nomme la question pour `nadia-prof`
et `camille-po` : la premisse du 2026-08-18 doit-elle etre reexaminee maintenant que le survol
qu'elle couvre cesse d'etre marginal ?

## 6. Ce que je n'ai pas tranche, nomme franchement

1. **La hauteur utilisable d'un ordinateur (section 2.3) repose sur une estimation, pas sur une
   mesure sourcee au meme niveau que le reste de ce document.** C'est le chiffre le plus fragile de
   toute cette proposition, et c'est justement celui qui porte le pire cas de hauteur (section 3).
   Je recommande qu'il soit verifie par une mesure reelle (navigateur reel, pas une emulation)
   avant d'etre fige dans une decision.
2. **L'orientation d'une tablette (portrait ou paysage) n'est pas un choix que je fais.** Elle ne
   change pas mes deux conclusions de pire cas (section 3), mais elle change la forme de gabarit
   qui aurait du sens sur un grand ecran tenu a l'horizontale - une question de mise en page, pas
   de mesure, qui reste ouverte pour `theo-archi` et pour moi le moment venu.
3. **La ligne StatCounter tablette a 601x1007 et la ligne StatCounter ordinateur a 384x832**
   restent non identifiees ou probablement mal categorisees (section 2.1, 2.2) - je les signale, je
   ne les elimine pas silencieusement, et je n'appuie aucune conclusion dessus.
4. **La question posee en section 4 (reference unique ou ensemble) n'est pas tranchee** - j'ai
   nomme ce que chaque option coute, pas laquelle choisir.
5. **La premisse du 2026-08-18 sur le survol neutre (section 5)** - je pose la question, je ne la
   rouvre pas moi-meme.

## Ce que je remonte

- **A `camille-po`** : la `DEC` en cours de redaction devra choisir entre une reference unique et
  un pire cas sur un ensemble (section 4), et fixer si l'orientation paysage d'une tablette est un
  usage vise (point 2 ci-dessus). Aucun des deux choix n'est fait ici.
- **A `nadia-prof`** : le pire cas de largeur (744 px, tablette) est presque le double de la
  reference actuelle (375 px) - un budget horizontal tres different de celui sur lequel toutes les
  mesures precedentes ont ete faites. Le pire cas de hauteur, lui, reste incertain et pourrait etre
  plus severe que la reference actuelle de 640 px (section 2.3, 3) - je recommande qu'une mesure
  reelle sur navigateur de bureau soit produite avant qu'un chiffre soit fige.
- **A `theo-archi`** : rien de nouveau sur l'architecture - ce document ne mesure que des
  dimensions d'appareils, aucune ligne de domaine ni d'application n'est concernee. La question
  d'orientation tablette (point 2) le concernera si elle devient une story.

Je ne fusionne pas, je ne pousse pas.
