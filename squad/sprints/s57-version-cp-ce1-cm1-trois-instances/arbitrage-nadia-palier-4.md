# s57 - arbitrage prealable du palier 4 (`nadia-prof`) : la borne, la capacite par instance, les lots

- **Auteure** : `nadia-prof`, seule autorite sur le Done pedagogique. **Date** : 2026-10-21, 18:32 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `50dbd73f`. Modifies et non commites par d'autres agents avant ecriture : `MATRICE-couverture.md`,
  `DEC-0062`, `DEC-0123`, `DEC-0124` (non lus).
- **Lu** : `preparation-palier-4-camille.md` ; `DEC-0120` A.126 a A.132 ; `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`
  4.1 a 4.4 et 11.2 a 11.4 ; `cloture-palier-3.md` (sections 1, 6, 9, 10, 13, 14) ; `duree-et-paliers.md` P5' ;
  `docs/backlog/backlog.md`, annotation *"Palier 4 de s57"* et `S57-BR-MAT-CE1-15` ; `ce1-mots-invariables.yaml` (24 enonces) ;
  en-tete et cinq items de `ce1-mots-irreguliers.yaml` ; liste des dessins `figure-*`.
- **Audit degrade, declare** : rien n'est joue, et aucun lot du palier 4 n'est encore ecrit ni branche. Ce document arbitre et
  ecrit des conditions ; il ne prononce aucun Done, ne modifie aucun paquet, ni la matrice, ni le registre. **Aucun identifiant de
  condition ou de reserve neuf.** Non commite par moi.
- **Ce qu'il vaut** : l'arbitrage prealable des trois instances A, A' et B pour le palier 4 (condition 3 de 11.3), 0,5 chacune.

## Resume

| Point | Decision |
|---|---|
| `O2` | **La borne reste 8,0 pour le palier 4.** `O2` n'est pas derivable : (i) non montree, (ii) non etablie. Sections 2.2 et 2.3 |
| Capacite par instance | **5,0 et trois lots au plus, inchanges** ; au palier 4 : A 2,0 (1 lot), B 3,0 (2), A' 3,0 (2). Section 2.4 |
| Composition recevable | **Le repli de 8,0 de `camille-po`** : `FRA-CE1-21` (A) ; `MAT-CE1-10`, `MAT-CE1-11` (B) ; `EMC-CE1-02`, `EMC-CE1-03` (A'). Section 3 |
| `FRA-CE1-21` | recevable, mais **le paquet ne tient pas ma condition 1 en saisie** : 22 enonces sur 24 admettent un autre mot. A reecrire dans le lot |
| `FRA-CE1-03` | **cousinage juge : cousine de `GRC`**, recevable ; hors du 8,0 propose, l'ordre est a `camille-po` |
| Palier 3, ma part de cloture | cinq Done ; rejeu de `-07` **0,5** sur la marge de A ; consomme 7,0 sur 8,0, plus 0,75 hors composition. Section 1 |

## 1. Cloture du palier 3, ma part (condition 3 de 11.3)

**Verdicts** : cinq Done, chacun sur sa fiche (`verdict-nadia-palier-3-ce1.md`, amendement de 18:28) : `-06`, `-11`, `EMC-CE1-01`
VALIDE SOUS RESERVE ; `MAT-CE1-01` VALIDE, plafond `en cours` ; `-07` REFUSE puis VALIDE.

**Prix du rejeu de `-07` : 0,5**, sur la marge de A. Motif : un refus, sa correction et mon rejeu tiennent dans la marge
(criteres, 4.4 point 3), et `DEC-0120` A.105 l'a compte ainsi au palier 2. Le rejeu a ete fait en deux temps (fichier, puis
ecran) ; c'est un rejeu, pas deux.

| Instance | Compose | Consomme | Detail | Marge non entamee |
|---|---|---|---|---|
| A | 4,0 | **4,0** | arbitrage 0,5 ; trois lots 3,0 ; rejeu de `-07` 0,5 | 0 |
| A' | 2,0 | **1,5** | arbitrage 0,5 ; `EMC-CE1-01` 1,0 | 0,5 |
| B | 2,0 | **1,5** | arbitrage 0,5 ; `MAT-CE1-01` 1,0 | 0,5 |
| **Total** | **8,0** | **7,0** | | **1,0** |

**Registre** : la ligne `A|marge de rejeu|0.50|marge` de `registre-prix-palier-3.txt` devient une ligne de rejeu de `-07` a 0,50,
etat `facture`. Fichier de `camille-po`, commite : je ne le modifie pas, je lui donne la valeur.

**Hors composition, dans la meme fenetre, sans prix jusqu'ici** : le veto du gabarit du pli (deux passages, aucun item joue),
**0,25** ; `arbitrage-nadia-q05-q10.md` (cinq reponses, conditions de six lignes, deux suites sur `R6`), **0,5**. Ce n'etait dans
aucun budget et je ne corrige pas le registre avec : je le compte pour mesurer la fenetre. **La fenetre du palier 3 a porte 7,75
sur 8,0.**

**Ce qui reste ouvert, a moi** : la lecture des transcriptions sur l'isolation des paliers 2 et 3 (`cloture-palier-3.md`,
section 10) ; l'annotation de `TRV-CE1-1` pour `-27` et `-29` (`arbitrage-nadia-q05-q10.md`, section 3).

## 2. La borne et la capacite par instance

### 2.1 La consigne, et le mot *"seuil"*

Mots de l'utilisatrice, forme 2 de `DEC-0062` depuis `DEC-0120` A.132 : *"pour le palier 4 augmente le seuil avec des instances en
// jusqu'a 3 si besoin"*. La lecture n'est pas confirmee. Deux lectures, et je reponds aux deux :

1. **Dans le depot, *"seuil"* nomme le seuil d'alerte de 41 h 38 min par palier** (`duree-et-paliers.md` P5', `planning.md` D4),
   franchi de 1 h 25 min au palier 3. Ma limite d'audit s'appelle *"borne"* dans tous mes fichiers. Si c'est ce seuil-la, il est
   a `sofia-sm`, *"ne clot rien et ne limite rien"*, et je n'ai aucune objection a ce qu'il monte.
2. **Si c'est ma borne de 8,0** (lecture de `camille-po`) : sections 2.2 a 2.5.

*"jusqu'a 3"* : A, A' et B sont ouvertes depuis le palier 1. Aucune quatrieme instance n'est demandee, et je n'en arbitre pas.

### 2.2 `O2` : la borne reste 8,0

Ma regle (criteres, 4.3 et 11.4) : une borne au-dela de 8,0 ne se derive que si trois conditions cumulatives tiennent.

| Condition | Etat le 2026-10-21, 18:32 |
|---|---|
| (i) deux instances remplissent 8,0 et laissent dehors au moins 4,0 de lots instruits, prenables par la troisieme | **non montree.** Mesure de `camille-po` (A.127), refaite par moi : B et A' avec les lots prets font 7,0 (2,0 fixes, cinq lots) et ne remplissent pas 8,0 ; avec `MAT-CE1-15` illustre, 8,5, et dehors il reste `FRA-CE1-21` et `FRA-CE1-03`, 2,0. Il faut 4,0 |
| (ii) le dernier sprint parallele clos est sans occurrence des formes (a), (b), (c), verifie par moi transcriptions comprises | **non etablie.** s56 n'est pas verifie par moi. s57 n'est pas clos, et *"un palier clos ne compte pas pour (ii)"* (11.4). De plus, `cloture-palier-3.md` section 10 porte des faits que je devrais qualifier contre la forme (c) avant que (ii) tienne pour s57 : 69 fichiers non attribues a la racine du scratchpad, un checkout ecrit par plusieurs agents |
| (iii) `PAR-13` | **tenue** par la composition de 8,0 (section 3) |

> **Decision : la borne du palier 4 est 8,0, pour trois instances. Je ne pose aucun chiffre au-dela, ni 9,0, ni 11,5.**

### 2.3 Pourquoi je ne la releve pas sur la consigne seule

Ma borne mesure ce que je tiens ensemble dans une fenetre ouverte (`PAR-0`, 11.2), pas des heures. Le palier 3 dit ou elle en est :

1. **La fenetre a porte 7,75 sur 8,0** (section 1), un refus et son rejeu compris : la marge de A est consommee en entier.
2. **J'ai ecrit, a cette charge, neuf ecarts que le releve de cloture a trouves dans mes fichiers** (numeros 17 a 25) : une phrase
   attribuee a moitie a tort a l'utilisatrice, un SHA de fusion pour un SHA de correction, une reserve en deux formulations, des
   numeros de ligne faux, des citations sans accents. Ce sont des defauts d'attention, au niveau de 8,0.
3. **Aucun de mes audits n'a ete joue par moi** : tous l'ont ete par le pilote d'`ines-design`. Un lot de plus, c'est aussi un
   releve d'ecran de plus pour elle.

8,0 est donc ma capacite mesuree, pas une marge de prudence au-dessous d'elle. **Un sprint plus long n'augmente pas la borne**
(4.4 point 2) ; des paliers successifs, si : un palier 5 porte 8,0 de plus (11.3).

**Ce que *"si besoin"* acheterait.** A 9,0, le seul lot ajoute est `EMC-CE1-04` (une ligne). 11,5 repose sur deux lots qui ne
sont pas prets (`FRA-CE1-03` sans story, `MAT-CE1-15` a reecrire). **Ce qui limite le palier 4, c'est le stock de lots prets, pas
la borne.** `EMC-CE1-04` et ce qui sera pret ensuite tiennent dans un palier 5 (`QP7`, a l'utilisatrice).

**Si l'utilisatrice, apres cette lecture, veut un palier 4 au-dessus de 8,0**, c'est sa decision de produit : elle l'ecrit dans
ses mots, forme 2. J'audite les lots engages, mes conditions de Done ne changent pas, et j'ecris le depassement comme le sien. Je
n'en derive pas `O2`, et le plafond par instance (5,0, trois lots) tient dans tous les cas.

### 2.4 Ce que la condition 4 de `FRA-CE1-08` et `-09` change

**Rien a la borne, tout au stock de A.** La condition 4 tient pour tout s57 (`arbitrage-nadia-q05-q10.md`, section 2), quel que
soit le seuil : relever la borne ne libere ni `-08` ni `-09`. Elle explique pourquoi A n'a qu'un lot pret (`-21`), deux avec
`-03`, et pourquoi (i) ne peut pas atteindre 4,0 dans s57 : avec `PAR-13` (`-27`, arts plastiques, questionner le monde tant
qu'A' existe) et `Q-06` (dessins), elle retire de s57 tous les lots qui auraient rempli une troisieme instance. Elle s'eteint a
s58 : `-09` d'abord, un des deux par sprint.

### 2.5 Capacite par instance

| Instance | Plafond (`PAR-8`) | Au palier 4, a 8,0 | Recul du palier 3 |
|---|---|---|---|
| A, francais CE1 | 5,0 et trois lots | **2,0** : arbitrage, marge, `FRA-CE1-21` | trois lots, un refus, la marge consommee : le plafond de trois lots a ete atteint une fois, et il a coute toute la marge |
| B, mathematiques CE1 | 5,0 et trois lots | **3,0** : arbitrage, marge, `MAT-CE1-10`, `MAT-CE1-11` | un lot, aucun rejeu |
| A', EMC | 5,0 et trois lots | **3,0** : arbitrage, marge, `EMC-CE1-02`, `EMC-CE1-03` | un lot, aucun rejeu |

**En parallele dans une fenetre : cinq unites de lot de 1,0**, une fois payes les 3,0 fixes des trois instances. Je lis un
paquet a la fois ; le parallele est entre instances, et il ne change rien a ce que je tiens ensemble.

## 3. Les lots, un par un

Prix : mon bareme (lot textuel 1,0, illustre 1,5). L'ecriture d'un paquet n'est pas un poste de mon bareme : le prix est celui de
l'audit, comme pour `EMC-CE1-01`. Communes a tous les lots : 4.1 de `arbitrage-nadia-preparation-palier-3-ce1.md`, inchangee
(lecture complete datee avant le premier verdict, exactitude relue a l'ecran, pli en reserve non bloquante, lot sans story non
arbitre, audit sur le commit joue).

### 3.1 Instance A

**`FRA-CE1-21`, `ce1-mots-invariables`, en saisie : recevable au palier 4.** 1,0, plafond `couvert-valide` avec la mention
*"mots sans accent seuls tant que l'accent est tolere"* et la reserve de clavier du 2026-10-21 dans la cellule.

- **`PAR-13` tient** : je juge `PAR-13` par matiere (s55, 1.1) et le francais CE1 n'est pas neuf ; la saisie a rencontre mes
  conditions sur `FRA-CP-09` et `MAT-CP-05`. Ce n'est pas un lot sans cousine.
- **Conditions de Done** : 4.3 de `arbitrage-nadia-q05-q10.md`, points 1 a 5, inchanges, plus la reserve de 8.2 dans la cellule.
- **Le paquet ne tient pas le point 1 en saisie, et ce n'est pas une barre neuve.** Le point 1 dit : *"une phrase a trou dont le
  sens n'admet qu'un mot invariable"*. Les enonces ont ete ecrits pour un choix entre trois graphies du meme mot, ou le sens n'avait
  pas a fermer la phrase. En saisie, il le doit : un enfant qui ecrit un autre mot juste serait corrige a tort. A ma premiere
  lecture (pas la lecture complete), **22 enonces sur 24 admettent un autre mot**, par exemple *"Il pleut ___ en novembre."*
  (*souvent*, *toujours*, *parfois*), *"Ce gâteau est ___ délicieux."* (*vraiment*, *très*, *trop*), *"On se lave les mains, ___
  on mange."* (*ensuite*, *puis*, *après*). Seuls les items 2 (*toujours*) et 9 (*Pourquoi*) me semblent fermes. **Les 24 enonces
  sont a reecrire dans le lot** ; la story dit *"Le paquet passe de type question a type saisie"*, ce qui sous-estime le travail.
  A amender par `camille-po` avant l'engagement. Refus ecrit d'avance : un item ou un autre mot, bien ecrit, complete la phrase.
- **`QP1`, *"Aujourd'hui"*, ma reponse.** Un item dont la bonne reponse, tapee juste sur la tablette, peut etre jugee fausse est
  refuse. Deux remedes me conviennent, le choix est au developpeur et a `theo-archi` : les deux apostrophes (U+0027 et U+2019)
  dans les formes acceptees, ce qui n'est pas une faute d'orthographe du mot (point 3 tenu) ; ou un autre mot invariable, sans
  apostrophe ni accent. Je ne demande aucun changement de mecanique.

**`FRA-CE1-03`, `ce1-mots-irreguliers` : cousinage juge, recevable.** Demande de `camille-po` (LP8). Sa cousine est `GRC`
(`ce1-graphemes-complexes`, `FRA-CE1-01`, **VALIDE** le 2026-10-04, `couvert-valide`) : meme forme, un mot cite entre chevrons et
trois mots, un jugement de son fait sans voix (*"Quel mot contient le même son que « main » ?"* contre *"Quel mot rime avec
« femme » ?"*). Ce n'est donc pas un lot sans cousine, et la condition 4 ne le touche pas. 1,0, plafond `en cours`, mention du
complement de s55 (section 3) ; conditions `MFR-CE1-1` a `-3` (s48, 3.3) et les communes. **Pas dans la composition de 8,0
proposee** : il n'a pas de story, et `marc-dev` le prendrait apres `-21` (`RG4`). L'ordre de `DEC-0120` A.35 place le francais
(4b) avant l'EMC : s'il faut le faire entrer a la place d'`EMC-CE1-03`, c'est a `camille-po` de le dire, avec une story.

### 3.2 Instance B

| Lot | Recevable | Prix, plafond | Conditions de Done |
|---|---|---|---|
| `MAT-CE1-10`, `ce1-masses-contenances` | **oui** | 1,0, `en cours` | `MAS-CE1-1` a `-6` (s43 lot 2, 3.4) ; mention et refus d'avance de 4.3 de mon arbitrage de preparation du palier 3, inchanges |
| `MAT-CE1-11`, `ce1-calendrier-dates` | **oui** | 1,0, `en cours` | `CAL-CE1-1` a `-5` (s43 lot 2, 4.3) ; mention et refus d'avance de 4.3, inchanges |
| `MAT-CE1-15`, `ce1-figures-planes` illustre | forme recevable ; **pas au palier 4 a 8,0** (B a 4,5, palier a 9,5) | 1,5, `en cours` | `FIG-CE1-1` a `-3`, mention et refus de 4.3. **Condition neuve, datee du 2026-10-21, sur une ligne sans Done** : il n'existe qu'un dessin de triangle rectangle (`figure-triangle-rectangle.svg`) ; un enfant qui revoit toujours la meme image reconnait l'image, pas la propriete. Le triangle rectangle est montre dans au moins deux dessins qui different par l'orientation ou les proportions. C'est un besoin de dessin, donc routable a `theo-archi` avant l'ouverture du lot (la story dit deja que le lot s'arrete si un dessin neuf est necessaire) |
| `MAT-CE1-14`, grille | **non pret** | (a) 1,5 `en cours` ; (b) 3,0, ou 1,5 en un paquet, `couvert-valide` | `arbitrage-nadia-q05-q10.md` 1.3, inchangees ; attend `theo-archi` (`A3`, `DA5`) et `QP3` |

### 3.3 Instance A'

| Lot | Recevable | Prix, plafond | Conditions de Done |
|---|---|---|---|
| `EMC-CE1-02`, regle et sanction | **oui** | 1,0, `couvert-valide` | `RSA-CE1-1`, `-2` et refus d'avance de 4.4 (preparation du palier 3) ; communes d'EMC de 4.4, dont `NRC-CE1-1` lu contre les trois paquets de CP et les trois de CM1 nommes ; points d'audit de ma lecture du palier 3, section 5 |
| `EMC-CE1-03`, droits et devoirs | **oui** | 1,0, `couvert-valide` | `DDE-CE1-1`, `-2` et refus d'avance de 4.4 ; memes communes |
| `EMC-CE1-04`, interet d'un seul et de tous | conditions ecrites ; **pas au palier 4 a 8,0** | 1,0, `couvert-valide` | `INT-CE1-1`, `-2` et refus d'avance de 4.4 ; dernier des quatre, la condition la plus difficile. Palier 5 |

A' a trois lots EMC, c'est la charge de A au palier 3 : je ne la recommande pas tant que A' n'a pas montre un lot ecrit dans le
palier sans refus. A deux lots, la marge de 0,5 couvre un rejeu.

### 3.4 Hors du palier 4, inchange

`FRA-CE1-08`, `-09` : s58, condition 4, `-09` d'abord. `FRA-CE1-29` : au plus tot au sprint qui suit la levee de `R6`, soit s58.
`FRA-CE1-27`, `ART-CE1-01`, `-02`, questionner le monde CE1 : `PAR-13`, pas tant qu'A' existe. `LVE-CM1-06` : CM1, hors du
perimetre, aucune story.

## 4. Ce qui exige un jeu reel ou un appareil, et qui le fait

| Quoi | Lots | Qui | Condition de Done ? |
|---|---|---|---|
| Lecture complete de chaque paquet, ligne datee, avant le premier verdict | tous | moi | oui |
| Releve d'ecran a 744 x 551 : arrivee, deux erreurs, revelation, bonne reponse, texte DOM | tous | `ines-design`, par son pilote, comme au palier 3 | oui (4.1 point 5) |
| **La saisie jouee** : le mot attendu, une faute, une validation a champ vide, un autre mot juste sur les items que je signale, *"aujourd'hui"* avec U+2019 si le mot reste | `FRA-CE1-21` | **moi**, sur le jar du commit du lot, sur un port et un sous-repertoire de la table d'isolation ; captures par `ines-design` | oui |
| Ce que le clavier de la tablette propose ou produit (mots au-dessus des touches, apostrophe) | `FRA-CE1-21` | l'utilisatrice seule : personne dans la squad n'a d'appareil | **non** : la reserve est ecrite |
| Police et appareil reels (pli, coupures) | tous | l'utilisatrice | non |

## 5. Ce que j'exige avant l'ouverture

1. **Ce fichier sur `round-two`**, avant le premier worktree d'un lot (condition 3 de 11.3 ; K1 (b) des stories).
2. **`S57-BR-FRA-CE1-21` amendee par `camille-po`** : les 24 enonces reecrits pour que le sens n'admette qu'un mot, le YAML livrable
   du lot, et le remede de `QP1` choisi. Sans cet amendement, je n'arbitre pas le lot de A.
3. **Une composition qui tient 8,0** et nomme une marge de 0,5 par instance, allouee a aucun lot.
4. **La table d'isolation, une ligne par agent lance avant son lancement**, mes trois instances et `ines-design` compris, un
   sous-repertoire par agent, par instance et par palier, aucune ecriture a la racine (condition 5 de 4.2, par palier en 11.3).
   Elle n'a pas tenu au palier 3 (`cloture-palier-3.md`, section 10). `sofia-sm` mesure la racine a la cloture.
5. **La ligne du rejeu de `-07` dans le registre du palier 3** (section 1), par `camille-po`.

**Ce que je n'exige pas** : la confirmation de la lecture de la consigne, si la composition est de 8,0 ; les ecarts 33 et 40 a 44
(commentaires de `src/main`, que l'enfant ne lit pas) ; la correction des reserves du pli du palier 3. `QP6` (les developpeurs de B
et d'A') est a l'utilisatrice et coute des reports, pas une condition de ma part.

## 6. Ce dont je reste la seule garde, ajoute pour le palier 4

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'aucun enonce de `FRA-CE1-21` n'admette un autre mot juste | aucun test ne lit le sens d'une phrase |
| Que le triangle rectangle ne se reconnaisse pas a une seule image (`MAT-CE1-15`, quand il entre) | aucun test ne compare deux dessins |
| Ce que fait le clavier de l'enfant | le produit ne le voit pas |

## 7. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | la composition de 8,0 (section 3) ; l'amendement de `S57-BR-FRA-CE1-21` (5.2) ; une story pour `FRA-CE1-03` si elle le veut, et son rang ; la ligne de rejeu du registre du palier 3 ; la condition neuve de `MAT-CE1-15` dans sa story |
| `sofia-sm` | la composition et l'amendement de `planning.md` ; la table d'isolation (5.4) ; le seuil d'alerte, si c'est lui que la consigne vise (2.1) |
| `theo-archi` | `QP1` avec le developpeur (3.1) ; un second dessin de triangle rectangle, ou la rotation de l'existant, pour `MAT-CE1-15` |
| `ines-design` | les releves d'ecran des lots, avec la saisie de `FRA-CE1-21` |
| l'utilisatrice | la lecture de *"seuil"* (2.1) ; si elle veut plus de 8,0 malgre 2.3, le dire dans ses mots |
| `relecteur-pedagogique` | l'espace simple avant les deux-points de *"Appuie sur la bonne réponse :"* (`veto-nadia-gabarit-pli.md`, amendement du 2026-10-21) |

**Signe** : `nadia-prof`, s57, palier 4, 2026-10-21, 18:32.
