# s57 - arbitrage de `nadia-prof` pour le palier 5 : second triangle rectangle de `LP7`, `PAR-13` et les cinq lignes de `D5`, portee de la lecture avant `verify`

- **Auteure** : `nadia-prof`, seule autorite sur le Done pedagogique. Instance A de deux instances lancees en parallele ; l'autre
  chiffre le rejeu 2 de `LP6`, que ce fichier ne touche pas. **Date** : 2026-10-24, 10:07 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `4ff3bd7e`, branche `s57-nadia-p5-a`.
- **Lu** : `proposition-camille-palier-5-ce1.md` (sections 1, 4.3, 5.1, 6, 7, 9) ; `avis-theo-palier-5-dessins.md` ; mon
  `arbitrage-nadia-palier-4.md` (3.2 ligne 162, 6 ligne 212, 8.4) ; `DEC-0120` A.180 a A.197 ; `DEC-0123` 3 et amendements
  huit et neuf ; `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md` 4.2 et 11.3 ;
  `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` 5 ; `arbitrage-nadia-preparation-palier-3-ce1.md` 1.3 ;
  `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` 1.1 ; `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md` (`ORD-CE1-6`, 7) ;
  `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md` 4.1 ; `ARBITRAGE-PREALABLE-s25-palier-1-visuel-...` (`HOR-1` a `HOR-8`,
  `CON-5`) ; cellules de matrice de `MAT-CE1-01`, `-02`, `-09`, `-10`, `-11`, `-13`, `-06`, `-17`, `QLM-CP-14` ; les quatre dessins
  de triangle et `figure-carre-incline.svg` ; `decision-theo-garde-ce1-e3.md` 3 et 5 ; story `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS`.
- **Audit degrade, declare** : rien n'est joue, aucun lot de ce fichier n'est ecrit. Ce document arbitre et ecrit des conditions ;
  il ne prononce aucun Done et ne modifie ni paquet, ni matrice, ni registre, ni `DEC`. **Aucun identifiant de condition ou de
  reserve neuf** : les conditions ci-dessous sont numerotees dans leur section, sans prefixe ; `camille-po` les reprend dans les
  stories.
- **Ce qu'il n'est pas** : l'arbitrage prealable du palier 5 (condition 3 de 11.3, 0,5 par instance). Celui-ci porte la cloture du
  palier 4, donc le prix du rejeu 2 de `LP6`, qui est a l'autre instance.

## Resume

| Point | Decision |
|---|---|
| (1) Second triangle de `LP7` | Une copie tournee de 90, 180 ou 270 degres **tient ma condition**, a la lettre. **Mon choix au meme cout** : un triangle d'autres proportions et sans cote horizontal ni vertical, sommets (20,57), (80,12), (44,89). Test facultatif : **oui**, ancre sur les items. Section 1 |
| (2) `PAR-13` | **Texte inchange.** *"Une seule ligne a dessin par sprint"* n'est pas son texte ; le *"un seul"* de 8.4 n'etait pas une regle. Jugees par cousinage : quatre lignes ont une cousine, **`MAT-CE1-11` (horloge) n'en a pas : pas dans s57**. Clause *"pas tant qu'A' existe"* : inchangee, sans objet pour les quatre lignes. Section 2 |
| (3) Par ligne | Palier 6 : `MAT-CE1-01` et `-02` ; palier 7 : `-09` et `-10`, **apres un Done de `-02`**. `-10` : balance **et** recipient. `-02` : *"placer"* par designation, oui ; par lecture d'une position, non. Section 3 |
| (4) Bareme | **1,5 par lot confirme**, lecture avant `verify` comprise. Le surcout de 0,375 n'est pas un prix. s57 : 8,0 au bareme pour quatre lignes ; `-11` : 2,5 a s58. Section 4 |
| (5) Lecture avant `verify` | `LP8` hors portee **tant que le lot ne change aucun item**. Les trois stories de reserve d'A' **dans la portee**, sur les items touches. Section 5 |
| (6) Questions de `camille-po` | Stories d'A' : pas des lots au sens des trois lots de `PAR-8`, leur prix compte dans 5,0. `Q-X1` **vaut** `CA4`. s58 : **une seule famille**, et `MAT-CE1-11` s'ajoute aux candidates. Section 6 |

## 1. `LP7` : le second triangle rectangle

### 1.1 Une copie tournee tient-elle ma condition ? Oui

Ma condition du 2026-10-21 (`arbitrage-nadia-palier-4.md` 3.2) : *"Le triangle rectangle est montre dans au moins deux dessins
qui different par l'orientation ou les proportions."* Une copie de `figure-triangle-rectangle.svg` tournee de 90, 180 ou 270 degres
differe par l'orientation. **Elle tient la condition, et je ne releve pas la barre apres coup** : si `lea-dev` livre une telle
copie, je ne la refuse pas pour ce motif.

### 1.2 Ce que je choisis, au meme cout

On me demande l'angle, ou d'autres proportions au meme cout (`avis-theo-palier-5-dessins.md` 1.3 : *"un `polygon` aux autres sommets
coute le meme ordre d'octets"*). **Je ne choisis aucune des trois rotations.**

**Motif.** Le dessin servi a son angle droit en bas a gauche, ses deux cotes de l'angle droit l'un horizontal, l'autre vertical,
et il est presque isocele (66 et 68 unites `[calcul]`). Tourne de 90, 180 ou 270 degres, il garde ces deux traits : l'angle droit
change de coin, mais il reste fait d'un cote horizontal et d'un cote vertical. Or c'est exactement la position prototypique que
l'enfant de CE1 sait deja reconnaitre ; celle qu'il ne reconnait pas, c'est l'angle droit dont aucun cote n'est parallele au bord.
Pour le carre, le paquet de CP sert deja la meme idee avec `figure-carre-incline.svg` (*"ni l'orientation ni la taille des deux
autres"*, `FIG-2`). Le triangle rectangle merite le meme traitement.

**Le dessin que je demande** (un seul `polygon`, `viewBox` 0 0 100 100, trait de 6, sans remplissage, comme les quatre autres) :

- sommets **(20,57)**, **(80,12)**, **(44,89)** ; angle droit en (20,57) ;
- cotes de l'angle droit (60,-45) et (24,32) : produit scalaire 60 x 24 - 45 x 32 = 0 `[calcul]`, l'angle est **exactement** droit,
  sans `transform` ;
- longueurs 75, 40 et 85 `[calcul]` : rapport des cotes de l'angle droit 1,9 contre 1,0 pour le dessin servi, aucun cote
  horizontal ni vertical ; il differe donc par les proportions **et** par l'orientation ;
- cadre : x de 20 a 80, y de 12 a 89 ; avec un demi-trait de 3, rien ne sort du cadre `[calcul]`. Ce point n'est garde par aucun
  test (avis 1.3) : je le verifie a l'ecran.

**Les deux autres triangles du paquet n'ont aucun angle voisin de 90 degres** `[calcul]` sur leurs sommets declares :
`figure-triangle-pose` 62, 62 et 56 degres ; `figure-triangle-renverse` 61, 61 et 58,5 degres. Un angle droit incline reste donc
distinct d'eux a l'oeil : l'exercice n'est pas une devinette.

**Ce que ce choix est, et ce qu'il n'est pas.** C'est ma reponse a la question de l'angle, avant que le fichier soit ecrit. Ce n'est
pas une condition de Done neuve : la condition reste celle de 1.1. Nom du fichier : a `lea-dev`, sur le patron des noms servis.

**Texte alternatif (`CON-5`)** : il ne dit ni *"triangle rectangle"*, ni *"angle droit"*, ni *"incline"*. Le patron des figures de CP
convient (*"Une figure geometrique a reconnaitre."*), identique sur tous les items.

### 1.3 Le test facultatif : oui, ancre sur les items

Je le veux, sous cette forme : **parmi les items de `ce1-figures-planes` dont la bonne reponse est le triangle rectangle, au moins
deux dessins distincts sont references ; chacun est un triangle dont un angle est droit (produit scalaire nul, apres `transform`
s'il y en a un) ; et ils ne sont pas superposables par translation ni par agrandissement.** La derniere clause est celle de ma
condition : une simple difference de taille ne compte pas.

**Motif.** Un test qui ne regarderait que le repertoire des dessins resterait vert si un item cessait de montrer le second
triangle. La propriete que je garde seule depuis le 2026-10-21 (ligne 212) porte sur ce que l'enfant voit, donc sur les items.
Forme, taille et place du test : `theo-archi` et `lea-dev`. **Ce qui me reste a moi seule**, meme avec ce test : que l'angle droit
incline se voie a 176 px, et qu'aucun triangle propose comme distracteur n'ait un angle assez proche de 90 degres pour tromper
l'oeil.

## 2. `PAR-13`, les deux lots illustres par palier et la clause sur A'

### 2.1 D'abord, deux corrections de lectures de mes propres textes

1. **`PAR-13` n'a jamais dit *"une seule ligne a dessin par sprint"*.** Son texte : *"Au plus une instance par sprint prend un terrain
   dont mes conditions n'ont jamais rencontre de contenu reel"*, juge par cousinage : une condition neuve n'est pas neuve si une
   condition cousine a deja mordu un paquet servi (`ARBITRAGE-PREALABLE-s57-instruction-...` 5). *"Une ligne a dessin au plus par
   sprint"* est la consequence que j'en ai tiree en 5.2 **pour les trois lignes dessinees de questionner le monde CE1**, parce que
   chacune etait un terrain neuf. `DEC-0123` 3.2 point 1 l'a etendue a *"chacun des dessins"* ; je n'avais jamais juge les lignes de
   mathematiques. Je le fais en 2.2.
2. **Le *"un seul"* de 8.4 n'est pas une limite de lots illustres par palier.** Dans le tableau (3) de 8.4, B portait deja deux lots
   textuels (`LP2`, `LP3`) ; *"un seul de `LP7` ou `LP9`"* etait le troisieme lot que `PAR-8` laissait. La phrase suivante dit :
   *"B a 5,0 (`LP7`, `LP9` et un textuel) tient `PAR-8` : je le recois"*. Il n'y a donc rien a lever : **deux lots illustres dans un
   palier, en B, sont recevables** sous `PAR-8` (1,0 + 2 x 1,5 = 4,0). Cela vaut pour `LP7` et `LP9` au palier 5.

### 2.2 Les cinq lignes, jugees par cousinage

| Ligne | Ce que l'enfant fait sur le dessin | Cousine servie | Terrain neuf ? |
|---|---|---|---|
| `MAT-CE1-01` | compter une collection groupee en centaines, dizaines, unites | reseaux de points de `ce1-sens-multiplication` (`MAT-CE1-06`, `couvert-valide`) : compter ce qu'un dessin montre, `MUL-CE1-5` ; `NUM-CE1-1` a `-7` | **non** |
| `MAT-CE1-02` | designer l'endroit d'un nombre sur une ligne graduee | designation sur un axe gradue (`designation-frise-de-la-journee`, `QLM-CP-14`, `couvert-valide`) et designation de CE1 (`designation-symetrie-ce1`, `MAT-CE1-17`, `couvert-valide`) ; `ORD-CE1-1` a `-5` | **non** |
| `MAT-CE1-10`, balance | additionner les masses marquees d'une balance a l'equilibre | scenes de monnaie de `cp-monnaie` (lire des valeurs tracees et les additionner, `couvert-valide`) ; `MAS-CE1-1` a `-6` | **non** |
| `MAT-CE1-10`, recipient | lire un niveau sur une graduation chiffree | **aucune tant qu'une ligne graduee chiffree n'est pas servie** ; ce sera `MAT-CE1-02` | non **apres** un Done de `-02`, oui avant |
| `MAT-CE1-09` | lire une longueur sur une regle graduee | meme lecture : `MES-CE1-1` a `-3` ont mordu les longueurs, mais pas une graduation chiffree | non **apres** un Done de `-02`, oui avant |
| `MAT-CE1-11` | lire deux aiguilles sur un cadran | **aucune** : `HOR-1` a `HOR-8` (s25) n'ont jamais rencontre de contenu, aucun cadran n'a ete servi ; la frise est lineaire, pas un cadran | **oui** |

Ce tableau est mon jugement de cousinage, pas une mesure. Il impose un ordre : **`MAT-CE1-02` avant `-09` et avant `-10`**, et un
Done de `-02` (VALIDE ou VALIDE SOUS RESERVE) ecrit sur sa fiche dans un palier clos avant l'ouverture du palier qui porte `-09` ou
`-10` (11.3, condition 2).

### 2.3 `MAT-CE1-11` n'entre pas dans s57

s57 a deja eu son terrain sans cousine : le rejeu d'`ART-CP-10`, audite par B au palier 1. Le 2026-10-19 j'ai refuse de lire cette
regle par palier pour questionner le monde CE1 (`arbitrage-nadia-preparation-palier-3-ce1.md` 1.3 : *"Je ne deplace pas ce
poteau"*), pour le meme motif et avec la meme portee que la condition 4 sur `FRA-CE1-08` et `-09` (*"quelle que soit sa duree"*). Je
ne le deplace pas davantage pour l'horloge : **`MAT-CE1-11` est au plus tot a s58**, sous la contrainte de 6.3.

Si l'utilisatrice veut l'horloge dans s57, c'est sa decision de produit : elle l'ecrit dans ses mots (forme 2 de `DEC-0062`), et
j'ecris l'entree comme un depassement de `PAR-13` qui est le sien, pas comme ma lecture, comme pour la borne (2.3 et 8.4 de mon
arbitrage du palier 4). J'audite alors le lot, et mes conditions de 3.5 ne changent pas.

### 2.4 Ce que je reponds aux trois points

1. **Texte de `PAR-13` pour B a deux lots illustres par palier** : **aucun texte neuf.** `PAR-13` ne porte ni sur les dessins ni sur
   le nombre de lots illustres ; elle porte sur les terrains neufs. Ce qui gouverne B a deux lots illustres, c'est `PAR-8` (5,0 et
   trois lots) et `PAR-2` (mathematiques CE1 a B seule). Deux lots illustres par palier : **recevable**, sous l'ordre de 2.2.
2. **Clause *"pas tant qu'A' existe"*** (11.3, condition 5, qui renvoie a ma condition de 4.2 : aucune autre instance sur un terrain
   neuf) : **inchangee.** Elle ne mord que les terrains neufs. Avec l'ordre de 2.2, aucune des quatre lignes de s57 n'en est un ; la
   seule qui l'est, `-11`, sort de s57 par `PAR-13` elle-meme. Je n'ai donc pas a juger son sort aux paliers 6 a 8, et je ne la
   reecris pas sans objet.
3. **Le *"un seul"* lot illustre de 8.4** : il n'etait pas une regle (2.1, point 2). Rien ne tombe, rien n'est leve.

**Cadence qui en resulte** : palier 6, `MAT-CE1-01` et `-02` ; palier 7, `-09` et `-10`. s57 porte quatre lignes de `D5` en deux
paliers, pas cinq en trois. **Dependance** : `-02` demande les chiffres 3, 4, 6 a 9 et les lettres de zone, dont 7, 8 et 9 restent a
dessiner (`ines-design`, avis 2.1). S'ils manquent a l'ouverture du palier 6, le palier 6 porte `-01` seul, et `-09` et `-10`
attendent un Done de `-02`.

## 3. Par ligne de `D5`

Communes aux cinq : ma section 4.1 de `arbitrage-nadia-preparation-palier-3-ce1.md`, inchangee (lecture complete datee avant le
premier verdict, exactitude relue a l'ecran, audit sur le commit joue) ; releve d'ecran a 744 x 551 par `ines-design`, avec la
lisibilite du dessin a sa taille rendue ; `LON-CE1-1` et `-2` ; `CON-5` verifie par moi, item par item. Un texte alternatif peut
etre le meme pour tous les items d'une famille (precedent : les douze figures de CP).

Les conditions *"ce que le dessin montre"* ci-dessous sont celles qui declenchent la garde M de `theo-archi` (avis 2.1). Elles sont
exactes ou fausses sur chaque dessin, donc testables.

### 3.1 `MAT-CE1-01`, denombrer (palier 6)

- **Items illustres** : **au moins 10**, chacun une collection differente ; total au plus 999.
- **Ce que le dessin montre** : 1. plaques, barres et cubes se distinguent par la forme et la taille, jamais par la seule couleur ;
  2. une barre montre ses dix cubes, une plaque ses dix barres (traits interieurs) : la valeur se compte sur le dessin ; 3. le nombre
  de plaques x 100, plus les barres x 10, plus les cubes, egale la bonne reponse.
- **Repartition** : au moins 3 items ou une sorte manque (le zero) ; au moins 3 ou les sortes ne sont pas rangees des centaines aux
  unites ; au moins 2 ou plus de neuf cubes ou de neuf barres obligent a regrouper (au plus 19 de chaque, pour la lisibilite).
  **Motif** : sans ces trois cas, l'enfant lit trois chiffres de gauche a droite, il ne denombre pas.
- **Distracteurs** : l'inversion d'ordre, l'oubli du zero, le regroupement oublie, quand ils restent sous 1000.
- **`CON-5`** : le texte alternatif ne donne aucun nombre, ni de plaques, ni de barres, ni de cubes.
- **Plafond** : le plafond `en cours` de cette ligne a pour motif la mention de `NUM-CE1-2` : *"le verbe 'denombrer' [...] n'est
  travaille par aucun jeu"*. Ma phrase *"quel que soit le Done"* visait le lot textuel. **Un Done sur ce lot, ces conditions tenues,
  rend `couvert-valide` atteignable et fait tomber la mention.** Ce n'est pas un abaissement : c'est la barre que la mention ecrivait.

### 3.2 `MAT-CE1-02`, placer sur une ligne graduee (palier 6)

- **Forme** : **designation**, paquet neuf (`ADR-0080`). *"Placer"* par designation : **oui**, c'est la forme que j'ai ecrite a s43
  (lot 1 : *"`couvert-valide` atteignable si une ligne graduee reellement dessinee est servie en designation"*). Une forme
  `bandit-manchot` qui fait lire le nombre d'une position marquee sert le geste inverse, *lire*, pas *placer* : **elle ne leve pas le
  plafond.**
- **Items** : **au moins 10** ; un dessin sert au plus deux items, qui demandent deux nombres differents ; donc au moins 6 dessins.
- **Ce que le dessin montre** : `ORD-CE1-6` en entier, plus : 1. pas constant sur l'image, et au moins deux pas differents sur le
  paquet (parmi 1, 10, 100) ; 2. au moins deux nombres de reference ecrits a leur graduation ; 3. le nombre demande tombe sur une
  graduation, jamais entre deux ; 4. la bonne zone est centree sur la graduation dont la valeur egale le nombre demande ; 5. les
  zones distractrices sont a une graduation de la bonne, ou a la place que donnerait un pas mal lu.
- **`CON-5`** : ni le texte alternatif ni le libelle accessible d'une zone ne disent la valeur d'une graduation ni son rang depuis
  une reference (*"la troisieme apres 300"*) ; des lettres seulement (`ZON-10`).
- **Plafond** : **`couvert-valide` atteignable** ; la mention de `ORD-CE1-5` tombe avec un Done de ce lot.

### 3.3 `MAT-CE1-09`, mesurer avec une regle (palier 7, apres un Done de `-02`)

- **Items illustres** : **au moins 10**, segments differents ; longueurs entieres de 1 a 10 cm ; centimetres seuls (16 px par
  centimetre environ a 176 px, avis 2.2 : les millimetres ne se lisent pas, et la ligne n'en demande pas).
- **Ce que le dessin montre** : 1. une regle de 0 a 10, une graduation par centimetre, regulierement espacees, nombres 0 a 10
  ecrits ; 2. les deux extremites du segment tombent exactement sur une graduation ; 3. graduation de fin moins graduation de debut
  egale la bonne reponse ; 4. **au moins 4 items ou le segment ne commence pas a 0.**
- **Distracteurs** : le nombre lu sous l'extremite quand le debut n'est pas 0 ; le nombre de traits couverts (longueur plus un).
- **`CON-5`** : ni la longueur, ni les nombres sous les extremites, ni le fait que le segment commence ou non a 0.
- **Plafond** : **`couvert-valide` atteignable**, sans mention, si le point 4 tient. **Motif** : sur l'ecran, l'enfant ne pose pas
  la regle ; mais le savoir que ce geste porte (le zero a une extremite, ou bien la difference) est exactement ce que travaillent
  les items decales. Le produit de *mesurer* est un nombre, et il est servi ; c'est la difference avec *tracer* (`MAT-CE1-15`), dont
  le produit est un dessin. Sans les items decales, la ligne reste `en cours`.

### 3.4 `MAT-CE1-10`, mesurer des masses et des contenances (palier 7, apres un Done de `-02`)

- **Forme** : **les deux**, balance et recipient gradue, dans un seul lot. **Motif** : la ligne nomme les masses et les contenances,
  et la mention du Done dit *"aucune balance ni aucun recipient gradue"*. Une seule des deux ne leve pas le plafond.
- **Items illustres** : **au moins 10**, dont au moins 4 de chaque.
- **Balance, ce que le dessin montre** : 1. une balance a deux plateaux, **a l'equilibre** (fleau horizontal) ; 2. un objet sur un
  plateau, des masses marquees sur l'autre, chacune portant son nombre ; 3. la somme des masses egale la bonne reponse, au plus 1000.
  **L'unite peut etre dite par l'enonce** plutot que tracee (*"Les masses sont en grammes."*) : `MAS-CE1-2` et `MAS-CE1-5` restent
  tenues, et aucune lettre n'est a dessiner. Distracteurs : le nombre de masses, la somme oubliant une masse.
- **Recipient, ce que le dessin montre** : 1. une graduation par litre, reguliere, nombres ecrits ; 2. le niveau est un trait
  horizontal net, lisible en niveaux de gris (jamais par la seule couleur) ; 3. il tombe exactement sur la graduation qui egale la
  bonne reponse. Distracteurs : la graduation voisine, la graduation du haut (la contenance du recipient plein).
- **`CON-5`** : ni la valeur des masses, ni leur somme, ni le niveau. *"Une balance a deux plateaux."* et *"Un recipient gradue."*
  conviennent.
- **Plafond** : **`couvert-valide` atteignable si les deux sont servies.** Une seule : `en cours`, et la mention nomme la moitie
  manquante.

### 3.5 `MAT-CE1-11`, lire l'heure (s58 au plus tot, 2.3)

Ecrit maintenant pour que la question ne revienne pas sous une autre forme.

- **Forme** : un fichier SVG par cadran, produit hors depot (avis 2.1). **`HOR-1` est tenue par la garde M**, qui verifie l'angle des
  aiguilles contre la bonne reponse (grande aiguille 6 x minutes, petite 30 x heure plus 0,5 x minutes, en degres) : je ne demande
  pas d'horloge generee par le serveur, la taille L n'est pas due.
- **Items illustres** : **au moins 12**, deux items ne montrent jamais le meme cadran : au moins 3 heures pleines, 3 et demie, 3 et
  quart, 3 moins le quart ; aucune heure plus de deux fois (`HOR-6` transposee).
- **Ce que le dessin montre** : `HOR-1` a `HOR-3` telles qu'ecrites (douze nombres ecrits, pas seulement 12, 3, 6 et 9 ; aiguilles
  distinctes par la longueur) ; la grande aiguille sur 12, 3, 6 ou 9 seulement ; **la petite aiguille a sa place exacte entre deux
  nombres** a la demie et aux quarts : c'est le point de la ligne.
- **Distracteurs** (`HOR-5` transposee) : les aiguilles inversees ; l'heure suivante, lue sur le nombre que la petite aiguille
  approche. Les trois propositions ne contiennent jamais deux lectures de la meme position (*3 h* et *15 h*).
- **`CON-5`** : ni l'heure, ni la place d'une aiguille (*"la petite aiguille est entre 3 et 4"*).
- **Plafond** : **`couvert-valide` atteignable** ; la mention *"l'ordre des mois est servi par `QLM-CP-13`"* reste.

## 4. Bareme des lots de `D5`

- **1,5 par lot, confirme**, pour les cinq, `-02` compris (paquet neuf de designation avec ses dessins, comme `LP9`). **Motif** : ce
  que je paie, c'est la verification de chaque dessin contre son item, une par une, comme les douze figures de CP ; les items
  textuels deja valides ne sont pas reaudites, seules les conditions de repartition sur le paquet entier sont recomptees.
- **La lecture avant `verify` et la correction qui la suit sont comprises dans ce 1,5** (section 5).
- **Fixe** : 1,0 par instance et par palier (arbitrage prealable 0,5, marge 0,5), inchange.
- **Le surcout de 0,375 par lot** (avis 2.3) est une mesure de `theo-archi` sur B, **pas un prix** : un refus et son rejeu tiennent
  dans la marge de 0,5 ; au-dela, une ligne de rejeu, comme avant.
- **Totaux au bareme** : palier 6, 1,0 + 1,5 + 1,5 = **4,0** ; palier 7, **4,0** ; s57, **8,0** pour quatre lignes. `MAT-CE1-11` a
  s58 : **1,5**, plus 1,0 de fixe si B n'y porte pas d'autre lot. Les cinq lignes coutent toujours 10,5 ; ce qui change, c'est le
  sprint de la cinquieme.
- **Hors de ces chiffres** : le dessin des glyphes et des familles (`ines-design`), les gardes M (developpeur). Aucun n'est un poste
  de mon bareme.

## 5. Portee de la lecture avant `verify`

**Regle de l'utilisatrice** (`DEC-0120` A.184) : tout paquet ecrit ou reecrit dans un lot. Ma portee fine :

| Poste | Dans la portee ? | Motif |
|---|---|---|
| `LP7`, `LP9` | **oui** | paquet reecrit, paquet neuf ; le premier jet est le paquet **avec** ses dessins sur la branche |
| Lots de `D5` | **oui** | des items illustres s'ajoutent (`-01`, `-09`, `-10`, `-11`) ou un paquet s'ecrit (`-02`) ; premier jet avec ses dessins |
| `LP8` | **non, tant que le lot ne change aucun item** | le paquet a ete ecrit hors sprint ; le lot le branche. **Si le lot modifie un item** (enonce, proposition, bonne reponse), le paquet est reecrit dans le lot et la regle s'applique **au paquet entier** : je n'en ai lu que l'en-tete et cinq items |
| `S57-EMC-CE1-04-R1`, `-02-R1`, `-03-R1` | **oui, sur les items touches** | une story de reserve reecrit des items d'un paquet : ce sont les mots de l'utilisatrice. Et la regle est nee des trois refus de `LP6`, sur le paquet meme que `-04-R1` corrige. Je lis les items touches et le compte de la regle des mots, avant le `verify`, comme je les lisais apres (A.173) |

**Ce que je produis** : un ecrit date, `lecture-nadia-premier-jet-<paquet>.md` dans le dossier du sprint : les items que je
refuserais et leur motif, **sans verdict**, aucune fiche modifiee, aucun identifiant neuf. **Je n'ecris aucun contenu** : un item
nomme est corrige par le developpeur, jamais par moi (je ne touche aucun fichier sous `adapters/`).

**Le prix** : compris dans le prix du lot (1,0 textuel, 1,5 illustre) ou dans celui de la story, **une lecture par lot avant le
`verify`**. Une seconde lecture avant `verify` (un premier jet corrige que l'on me redonne) n'est pas dans le lot : elle va sur la
marge. Un refus apres le `verify` se facture comme avant.

## 6. Les questions de `camille-po` qui me regardent

### 6.1 Les trois stories de reserve d'A' sont-elles des lots ? (proposition 4.3)

**Non, pas au sens des trois lots de `PAR-8`** : un lot est une ligne de matrice auditee jusqu'a un Done ; une story de reserve
corrige des items d'une ligne deja auditee. **Leur prix compte dans les 5,0 d'A'.** Il se fixe dans l'arbitrage prealable d'A' du
palier 5 : je n'ai pas lu les trois stories ici et je ne le fixe pas de memoire.

### 6.2 `Q-X1` vaut-elle `CA4` ? (proposition 8)

**Oui.** `reponses-nadia-questions-stories-palier-3.md` section 1 (`Q-X1`) est l'avis que `CA4` demande sur la section 3 de
`decision-theo-garde-ce1-e3.md`. Par regle : mathematiques CE1 **exclues** de `LON-CE1-3` a `-5` et de `TRV-CE1-1` (supports et voix),
motif s48 1.3 ; EMC et questionner le monde CE1 **soumises** a `LON-CE1-5` et a `TRV-CE1-1` (supports et voix) ; `LON-CE1-3` et `-4`
nomment leurs lignes de francais. La faute de `LON-CE1-5` mesuree en mathematiques par `theo-archi` est donc hors population. Les
arts plastiques CE1 n'ont aucun paquet : un premier paquet rougira le garde, comme sa section 3 le veut, et je le classerai a son
instruction.

**Dette a moi, dite ici** : le critere 1 de la story attend mon annotation de `TRV-CE1-1` pour `FRA-CE1-27` et `-29`, promise le
2026-10-21 pour *"mon prochain commit `[NAD]`"* (`arbitrage-nadia-palier-4.md` 8.2). `grep -c "2026-10-2"` sur
`ARBITRAGE-PREALABLE-s48-francais-ce1.md` rend **0** : elle n'est pas ecrite. Ce commit ne la porte pas non plus, faute d'etre dans
la demande ; c'est un report, compte comme tel.

### 6.3 s58 : une famille, ou plusieurs ? (proposition, F3)

**Une seule, et la lecture de `camille-po` est juste.** A s58 : au plus un lot sans cousine ou un terrain neuf, toutes instances
confondues (`PAR-13`, ma regle de s55), et un premier lot de questionner le monde CE1 ne prend pas le sprint ou une autre instance
audite un paquet sans cousine (1.3 de la preparation du palier 3). Les candidates sans cousine sont `FRA-CE1-09` (puis `-08` a un
sprint suivant), `ART-CE1-01` et `-02`, `FRA-CE1-27`, **et desormais `MAT-CE1-11`**. s58 porte donc **soit** les premiers lots de
questionner le monde CE1, **soit** un seul de ces lots, pas les deux. `FRA-CE1-29` n'est pas sans cousine (`cp-mot-dicte`) et
n'entre pas dans ce compte. Le choix entre les familles est a `camille-po`.

## 7. Ce que je suppose

1. Le rejeu d'`ART-CP-10` au palier 1 est le terrain sans cousine de s57 : je l'ai ecrit moi-meme (1.3), non remesure ici.
2. Le cousinage de 2.2 est mon jugement ; aucun outil ne le mesure.
3. Les octets par dessin et par ligne sont ceux de `theo-archi`, supposes par lui.
4. La lisibilite a 176 px des traits interieurs d'une plaque, des nombres d'une regle, des graduations d'un recipient et du triangle
   incline n'est pas mesuree : elle est a `ines-design`, puis a moi a l'ecran.
5. Les angles des triangles servis sont calcules sur les sommets declares des fichiers, pas mesures a l'ecran.
6. Je n'ai lu ni les trois stories de reserve d'A', ni le paquet de `LP8` en entier.

## 8. Ce dont je reste la seule garde, ajoute

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'un texte alternatif ou un libelle de zone ne livre pas la reponse, sur les lignes de `D5` et `LP7` (`CON-5`) | aucun test ne lit le sens d'un texte alternatif |
| Que l'angle droit incline de `LP7` se voie a 176 px, et qu'aucun triangle distracteur n'ait un angle trompeur | le test de 1.3 calcule, il ne regarde pas |
| Que la petite aiguille a la demie et aux quarts se lise entre deux nombres, a 176 px (`MAT-CE1-11`) | la garde M verifie l'angle, pas la lecture |
| Qu'une collection de `MAT-CE1-01` se compte sans se confondre | la garde M compte des instances, pas ce que l'oeil distingue |

## 9. Routage

| A qui | Quoi |
|---|---|
| `lea-dev` | `LP7` : le triangle de 1.2 (ou une copie tournee, qui tient la condition) ; le test de 1.3 ; la ligne de premier jet de 5 |
| `theo-archi` | la forme du test de 1.3 ; les gardes M des lignes de `D5`, declenchees par les conditions *"ce que le dessin montre"* de 3 |
| `ines-design` | chiffres 7, 8, 9 et lettres de zone avant le palier 6 ; mesure a 176 px d'une plaque, d'une regle, d'un recipient, d'une balance, du triangle incline |
| `camille-po` | stories des lignes de `D5` avec les conditions de 3 ; l'ordre de 2.2 (`-02` avant `-09` et `-10`) ; `MAT-CE1-11` hors s57 dans la `DEC` ; la portee de 5 ; le choix de famille de s58 (6.3) |
| `sofia-sm` | cadence de `planning.md` : paliers 6 et 7 pour `D5`, palier 8 sans objet pour `D5` |
| l'utilisatrice, par `camille-po` | seulement si elle veut `MAT-CE1-11` dans s57 : ses mots, forme 2 (2.3) |

**Signe** : `nadia-prof`, s57, palier 5, instance A, 2026-10-24, 10:07.
