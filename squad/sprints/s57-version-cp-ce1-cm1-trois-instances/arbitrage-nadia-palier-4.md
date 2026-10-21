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

---

## 8. Suite du 2026-10-21, 20:56 (UTC+02:00) : `QP3`, `QP5`, `QP6`, et le seuil a quatre instances

- **Ses mots**, relayes par l'agent lanceur, valant les siens par sa reponse a `E5` : *"E5 oui ça vaut pour mes mots, QP6 lea
  dev pour B et sofia pour A', QP7 après un palier 5 voir 6 je ne sais pas encore, QP5 oui, QP3 un tableau d'informations"*.
  Puis, sur le seuil : *"on monte a 20"*, et *"1 quatre instances"* a la question *"voulez-vous lever le plafond par instance a
  6,67, ou passer a quatre instances, ou fixer 15,0 ?"*.
- **Arbre** : `round-two` a `50c74519`, heure lue par `date`. **Lu** : `preparation-palier-4-camille.md` sections 3, 4 et `QP3`
  a `QP6` ; `cloture-palier-3.md` section 13 ; `avis-theo-palier-4.md` (tableau des lots, 3.5) ; `DEC-0120` A.123 ;
  `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-3.md` section 1 ; criteres de s57, 4.2 et 11.3 ; les cinq `competences-ce1.md` ;
  en-tete de `cp-mot-dicte.yaml` et statut de `FICHE-saisie-mot-dicte-cp.md`.
- **Audit degrade, declare** : rien n'est joue. Aucun Done prononce, aucun paquet, ni la matrice, ni le registre modifies.
  **Aucun identifiant de condition ou de reserve neuf.** Non commite par moi.

### 8.1 `QP3` : `MAT-CE1-14` avec un tableau d'informations

**D'abord, une correction de mon propre texte.** La mention de la forme (a) que j'ai fixee (`arbitrage-nadia-q05-q10.md` 1.3,
point 7 : celle de s43 *"suivie de"* un complement) se contredit : celle de s43 commence par *"se reperer et se deplacer servis
par designation sur quadrillage"*, et le complement dit *"servis sans designation"*. s43 demande seulement que la mention de la
forme sans designation *"porte en plus"* ce qui manque. **Mention qui gouverne pour (a), a la place** : *"se reperer et se
deplacer servis sur un quadrillage montre, reponse au choix sans designation ; 'coder un deplacement' servi du cote reception
(choix d'un code parmi trois), la production d'un code reste non couverte ; la ligne reste en cours"*. La ligne n'a pas de Done :
aucune barre ne bouge.

**La ligne**, mot pour mot : *"Se repérer, se déplacer et coder un déplacement sur un quadrillage."* Deux lectures de *"un
tableau d'informations"*, je tranche les deux.

| Lecture | Verdict | Pourquoi |
|---|---|---|
| (1) Le quadrillage dessine, axes etiquetes, montre sur chaque item, **sans case a toucher** : l'option que la question de `camille-po` opposait a la designation | **recevable pour `MAT-CE1-14` : c'est la forme (a)**, plafond `en cours` | le quadrillage est montre, la designation manque (q05-q10, 1.2) |
| (2) Un tableau de donnees : des lignes et des colonnes remplies d'informations, ou l'enfant lit une case a leur croisement | **non recevable pour `MAT-CE1-14`**, et **pour aucune autre ligne** | on ne se deplace pas dans un tableau de donnees et on n'y code aucun deplacement : il ne reste que la moitie *se reperer*, hors d'un quadrillage. Aucune ligne des cinq referentiels CE1 ne porte la lecture d'un tableau (recherche de *tableau*, *repérer*, *quadrillage*, *données*) ; `QLM-CE1-09` est un plan avec legende, et il est sorti du perimetre avec les dessins (`Q-06`) |

**Signalement sur le referentiel, a `camille-po` via `sofia-sm`** : le referentiel de mathematiques CE1 ne porte aucune ligne
sur l'usage de donnees en tableau. Je crois que le programme du cycle 2 en porte une ; je ne l'ai pas relu ici et je ne l'ecris
pas de memoire. A verifier contre le texte officiel ; une ligne neuve, je l'ecris moi-meme.

**Ce que la forme (a) permet de demander a l'enfant, et rien d'autre** (grille montree sur chaque item, depart marque) :

1. **se reperer** : un objet marque sur la grille, *"sur quelle case est-il ?"*, trois reponses formees d'une lettre de colonne
   et d'un chiffre de ligne, lues sur les axes ;
2. **se deplacer** : depart marque, un deplacement dit en pas, *"ou arrive-t-il ?"*, trois cases ; au moins un tiers des chemins
   change d'axe (`DEP-CE1-1`) ;
3. **coder** : depart et arrivee marques, trois codes de meme longueur (`DEP-CE1-4`).

Ce qui n'est pas demandable : montrer une case, et produire un code.

- **Conditions de Done** : `arbitrage-nadia-q05-q10.md` 1.3, points 1, 2, 4, 5, 6 et 8, inchanges ; le point 3 est sans objet ;
  le point 7 est remplace par la mention ci-dessus. `DEP-CE1-2` est tenu pour *"montre, de 4 x 4 a 6 x 6, cases de meme taille"*,
  pas pour *"toutes designables"* : c'est ce qui plafonne la ligne.
- **Prix 1,5** (lot illustre), instance B. Prealables : l'amendement de `S57-BR-MAT-CE1-14-GRILLE` par `camille-po`, de (b) a
  (a) ; la mesure d'ecran d'`avis-theo-palier-4.md` 2.3.

**Ce que je demande a l'utilisatrice**, par `camille-po`, puisque son choix ne leve pas `DEP-CE1-2` : *"Avec un tableau
d'informations ou l'enfant ne touche pas la case, la ligne 'Se repérer, se déplacer et coder un déplacement sur un quadrillage'
reste 'en cours' : elle ne passe pas a 'couverte' tant que cette forme seule est servie. L'acceptez-vous ? Et par 'tableau
d'informations', parlez-vous du quadrillage dessine avec ses lettres et ses chiffres, ou d'un tableau rempli d'informations ?"*
Un oui sur le quadrillage : forme (a). Un tableau rempli : non recevable, la ligne reste `non couvert`.

### 8.2 `QP5` : `FRA-CE1-27` et `FRA-CE1-29`, maximum de version a 202

**Ce qui les rend auditables**, dans l'ordre :

1. son *"oui"* : `camille-po` peut les passer a `non couvert`, une a une (`DEC-0120` A.123). Le compte est a elle ;
2. **le declencheur qu'A.123 a pose est a moi et n'est pas fait** : l'annotation de `TRV-CE1-1` dans
   `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (`grep -c "2026-10-21"` y rend 0 a 20:56). Son contenu est decide
   (`arbitrage-nadia-q05-q10.md` section 3 : le champ `texteAVoixHaute` admis sur ces deux lignes seules). Je l'ecris dans mon
   prochain commit `[NAD]` ; cette tache n'en porte pas ;
3. un paquet, une story, une instance. Auditable n'est pas ouvrable dans s57 :

| Ligne | Conditions de Done | Plafond, prix | Ce qui reste bloquant |
|---|---|---|---|
| `FRA-CE1-27`, *"Écouter et comprendre un texte lu par l'adulte, et s'exprimer à l'oral."* | q05-q10 3.1, points 1 a 5, inchanges ; *s'exprimer a l'oral* reste non couvert | `en cours`, **1,0** | **terrain neuf** (`PAR-13`) : *"pas tant qu'A' existe"* (criteres, 11.3, condition 5), y compris aux paliers 5 ou 6 (`QP7`) ; aucun paquet, aucune story |
| `FRA-CE1-29`, *"Écrire sous la dictée des mots et des phrases courtes."* | q05-q10 3.2, inchanges : mots seuls, conditions `VOI-9` de `cp-mot-dicte` transposees, aucun mot dont l'orthographe tient a un accent tant que `SAI-1` le tolere, `NRC-CE1-1` contre `cp-mot-dicte` ; plus la reserve de q05-q10 8.2 dans la cellule, dans la formulation de `FICHE-saisie-mot-dicte-cp.md` | `en cours`, **1,0** | **ma regle de 3.2** : *"au plus tot au sprint qui suit la levee de `R6`"* ; `R6` est leve dans s57, donc s58, meme si s57 porte un palier 5 ou 6. Je ne la deplace pas. Aucun paquet, aucune story |

**Cousinage de `-29`, juge ici** : sa cousine est `cp-mot-dicte` (`FRA-CP-09`, **VALIDE** le 2026-09-01) : l'adulte dit le mot
sous `VOI-1`, l'enfant le tape. Ce n'est pas un terrain neuf.

**La reserve des mots proposes reste non bloquante**, comme je l'ai ecrite a 16:12. Je note sans en faire une condition : sur une
dictee, le mot propose au-dessus du clavier a toutes les chances d'etre le mot dicte lui-meme, plus que sur `-21`. A l'audit, je
joue un item en touchant une proposition et j'ecris dans la fiche ce que le jeu en fait.

### 8.3 `QP6` : `lea-dev` sur B, `sofia-sm` sur A'

`RG2` est tenue. **Rien a ajouter a la barre de 5.4.** Deux precisions :

1. **Deux lignes, jamais une ligne commune** : chaque developpeur a son worktree, son port, son sous-repertoire de palier, ecrits
   avant son lancement.
2. **`sofia-sm` ecrit desormais dans le scratchpad dont elle mesure la racine a la cloture.** Je ne demande pas un autre
   mesureur. Je demande que la commande et sa sortie soient collees dans la cloture : je les lis moi-meme, avec les transcriptions
   que je dois encore lire (section 1). `RG5` (elle fusionne sa propre branche) n'est pas a moi.

### 8.4 Le seuil : quatre instances

**Correction d'un fait de la consigne qui me relaie la question.** Le palier 3 n'a eu **aucune instance pleine** : A a consomme
4,0 sur 4,0 composes, A' 1,5 sur 2,0, B 1,5 sur 2,0 (section 1), pour un plafond de 5,0 chacune. Ce qui s'est rempli, c'est
ma fenetre : 7,75 sur 8,0.

**Statut.** C'est ce que j'ai prevu en 2.3 : sa decision de produit, ecrite dans ses mots. **J'audite, et j'ecris le depassement
de 8,0 comme le sien, pas comme `O2`.** `PAR-8` par instance tient : 5,0 et trois lots au plus.

**(1) Les conditions de 4.3, a 20:56**

| Condition | Etat | Pourquoi |
|---|---|---|
| (i) deux instances remplissent 8,0 et laissent dehors au moins 4,0 de lots instruits | **non montree** | lots prets ce soir : `LP1` a `LP6`, 6,0. B et A' avec eux font 7,0, pas 8,0. Rien ne reste dehors pour une troisieme instance, encore moins pour une quatrieme |
| (ii) le dernier sprint parallele clos est sans occurrence (a), (b), (c), transcriptions comprises | **non etablie** | inchangee depuis 18:32 (2.2) : s57 n'est pas clos, et je n'ai pas lu les transcriptions du palier 3 |
| (iii) `PAR-13` | **tenue** par la composition ci-dessous ; **rompue par tout lot de C** | chaque candidat de C est un terrain neuf ou un lot sans cousine (`-27`, arts plastiques CE1, questionner le monde CE1) |

**(2) Ce que je lis en parallele, pour de bon.** Ma seule mesure : le palier 3, **43 h 3 min**, cinq lots audites, un refus et
son rejeu, cinq releves d'ecran par `ines-design`, un veto et un arbitrage hors composition, 7,75 ; et neuf ecarts trouves dans
mes fichiers a cette charge (2.3).

- **Lots** : je lis un paquet a la fois. Les instances mettent le developpement en parallele, pas ma lecture. A 9,0, c'est six
  lots ; a 11,5, huit.
- **Rejeux** : un par instance qui porte un lot, dans sa marge de 0,5. Trois au plus, puisque C n'ouvre pas (point 3).
- **Releves d'ecran** : un par lot, tous par le pilote d'`ines-design`, l'un apres l'autre. Plus la saisie de `FRA-CE1-21`,
  jouee par moi.
- **Duree** : je n'ai aucune mesure au-dessus de 7,75, et je n'ecris pas d'heures (criteres, 4.4 point 4). Si le rythme du palier
  3 se repete, ma part depasse 43 h 3 min.
- **Ce qui ne change pas** : aucun verdict sans lecture complete datee (4.1). Un lot non audite a la cloture glisse au palier
  suivant (11.3, point 2) : il n'est pas audite a la hate.

**(3) Composition recevable a quatre instances, parmi `LP1` a `LP9`**

| Instance, developpeur | Lots recevables | Prix | Total |
|---|---|---|---|
| A, `marc-dev` | `LP1` `FRA-CE1-21` (sous 5.2) ; `LP8` `FRA-CE1-03` si sa story est ecrite avant l'ouverture (cousinage juge en 3.1) | 1,0 + 1,0 | 2,0 ou 3,0 |
| B, `lea-dev` | `LP2` `MAT-CE1-10`, `LP3` `MAT-CE1-11` ; plus **un seul** de `LP7` `MAT-CE1-15` illustre (condition du second triangle rectangle, 3.2) ou `LP9` `MAT-CE1-14` forme (a) (8.1), s'il est pret avant l'ouverture | 1,0 + 1,0 + 1,5 | 3,0 ou 4,5 |
| A', `sofia-sm` | `LP4` `EMC-CE1-02`, `LP5` `EMC-CE1-03` ; `LP6` `EMC-CE1-04` recevable, dernier des trois, et je maintiens ma recommandation de 3.3 de ne pas le prendre | 1,0 x 2 ou 3 | 3,0 ou 4,0 |
| C, aucun | **aucun lot recevable** : `-27` (`PAR-13`), `-29` (s58), `ART-CE1-01` et `-02` (terrains neufs, corpus absent), questionner le monde CE1 (aucun audit a s57), `LVE-CM1-06` (CM1, hors perimetre, sans story). Les mathematiques CE1 ne se partagent pas entre B et C (`PAR-2`) ; mon accord sur le francais CE1 ne vaut que pour le decoupage X / Y, et `-03` comme `-21` sont hors de X et de Y | - | **C n'ouvre pas** |

**Ce soir, 9,0** (A 2,0, B 3,0, A' 4,0), ou 8,0 sans `LP6`. **Au plus 11,5**, si la story de `LP8` et un des deux lots illustres
sont prets avant l'ouverture. B a 5,0 (`LP7`, `LP9` et un textuel) tient `PAR-8` : je le recois, je ne le recommande pas, aucun des
deux lots illustres n'est pret. **20,0 n'est pas atteignable** : ce qui limite le palier, c'est le stock de lots, pas la borne
(2.3). Le neuvieme lot n'existe pas, et je n'en arbitre aucun pour remplir C.

**(4) Ce que j'exige avant l'ouverture, en plus de la section 5**

1. **La composition nomme, par instance ouverte**, ses lots, son developpeur et sa marge de 0,5, allouee a aucun lot. Le total
   est la somme de ces lignes, et c'est le sien, ecrit comme tel.
2. **La table d'isolation, une ligne par agent lance, avant son lancement** : `marc-dev`, `lea-dev`, `sofia-sm`, mes trois
   instances de lecture, `ines-design`. Chacun a son port et son sous-repertoire de palier, sans aucune ecriture a la racine.
3. **C n'ouvre qu'avec trois choses** : un lot recevable ; un developpeur distinct des trois autres (`RG2`) ; mon arbitrage
   prealable pour C, ecrit avant son premier lot (0,5). Aucune des trois n'existe ce soir.
4. **La file de fusion** (un seul `verify` complet a la fois, 11 min 39 s, `avis-theo-palier-4.md` 3.5) est a `sofia-sm` et
   `theo-archi`. Ce qui me concerne : je joue le jar du commit fusionne sur `round-two`, et je le nomme dans mon verdict. Si la file
   rebase un lot apres mon jeu, mon jeu ne vaut pas pour le nouveau commit : je rejoue, sur la marge.

### 8.5 Ce dont je reste la seule garde, ajoute

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| Qu'un item de `MAT-CE1-14` en forme (a) ne devienne pas une simple lecture d'etiquette (`DEP-CE1-3`) | aucun test ne distingue un reperage d'une lecture |
| Qu'un mot propose par le clavier sur `FRA-CE1-29` soit ecrit par l'enfant | ni le produit ni les tests ne voient le clavier |

### 8.6 Routage

| A qui | Quoi |
|---|---|
| l'utilisatrice, par `camille-po` | la question de 8.1 |
| `camille-po` | l'amendement de `S57-BR-MAT-CE1-14-GRILLE` vers (a), avec la mention de 8.1 ; le reclassement de `-27` et `-29`, apres mon annotation ; une story pour `LP8`, si elle veut 11,5 ; le signalement du referentiel (8.1) |
| `sofia-sm` | la composition (8.4 point 3) ; la table d'isolation (8.3, 8.4 point 4) ; la sortie de la mesure de la racine, collee dans la cloture |
| moi | l'annotation de `TRV-CE1-1` (8.2), dans mon prochain commit `[NAD]` |

**Signe** : `nadia-prof`, s57, palier 4, 2026-10-21, 20:56.
