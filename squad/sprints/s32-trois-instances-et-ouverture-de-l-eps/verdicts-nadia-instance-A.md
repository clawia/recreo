# s32 - verdicts de `nadia-prof`, **instance A** : histoire-geographie CM1, anglais CM1, portee D

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-09.
- **Arbre** : worktree `s32-nadia-a`, parti de `round-two` a `fd19d18f`. Jar reconstruit depuis cet
  arbre, application lancee sur un port local.
- **Terrain** : histoire-geographie CM1 (`S32-1`), anglais CM1 (`S32-2`), portee D (`S32-3` : passe
  transverse, reliquat de `CONS-1`, verification de `SCO-1`). Plus l'affectation de `FOP-R4` et
  `DRO-R4`, section 10.
- **La conclusion a reprendre dans `review.md`** est la table de la section 13, ligne par ligne.
  **Je ne cree pas `review.md`** : c'est un fichier de ceremonie et il appartient a `sofia-sm` - trois
  instances qui ecrivent chacune un bout d'un fichier partage sans se lire est exactement la forme (c)
  de defaut de couture que j'ai decrite, et je ne vais pas la produire dans le document qui la
  denonce.
- **Ce que je n'ai pas audite, et je le dis pour que personne ne le croie couvert** : l'EMC CP
  (instance B) et l'education physique et sportive (instance C). Je n'ai lu aucune de leurs
  branches. **Deux paires d'yeux sur des lots disjoints sont deux paires d'yeux, pas un double
  controle** - c'est ma phrase, elle vaut a trois instances comme a deux.

---

## 0. Ce que j'ai joue, et ce que je n'ai pas pu voir

| Ce que j'ai joue | Combien | Ce que j'y ai verifie |
|---|---|---|
| `bandit-manchot-histoire-geographie-cm1`, **parties completes de dix manches, jusqu'a l'ecran de fin** | **5** | la consigne reecrite ; les items des quatre lignes du lot, servis a l'ecran ; l'enchainement apres une erreur, apres deux, apres trois jusqu'a la revelation ; le score |
| meme jeu, **passes ciblees** sur les items qu'une partie de dix manches sur quatre-vingt-douze items laisse le plus souvent de cote | **26 parties** | l'item de Poitiers (`les Sarrasins`), l'item des Francs et son indice, les items d'ordre de `HG-CM1-02` et leurs indices - **tous vus servis, pas seulement lus dans le fichier** |
| `bandit-manchot-anglais-cm1`, **parties completes de dix manches** | **3** | les dix items de `LVE-CM1-07`, dont les cinq neufs ; les indices apres erreur ; le score |

**Audit degrade sur un point, et je le declare, comme le 2026-09-08** : j'ai joue par requetes HTTP
reelles, donc j'ai lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les
textes, l'ordre des ecrans, l'enchainement apres une erreur et apres trois, le score et les
propositions reellement presentees. Cela **ne** couvre ni la couleur, ni le contraste, ni la taille,
ni la position a l'ecran. **Aucun verdict de ce document ne repose sur un jugement visuel.**

**Ce que je n'ai pas re-verifie et que je ne suppose pas** : la loi de tirage. Je donne des comptes
d'items, jamais une probabilite de tirage - j'ai deja enregistre un tirage comme uniforme alors
qu'il ne l'etait pas.

`yanis-qa` a constate que personne n'a de trace ecrite d'une partie jouee de bout en bout sur ce
sprint hors un lot. **Le tableau ci-dessus est cette trace pour mon terrain**, et les cinq parties
completes sont allees jusqu'a l'ecran *"C'est fini !"*.

---

## 1. Trois corrections a mes propres constats, avant de m'en servir

Je verifie ce que j'ai ecrit avant de m'appuyer dessus. Les trois vont dans des sens differents et
deux d'entre elles jouent contre moi.

### 1.1 L'enonce qui creve mon propre plafond de charge de lecture est le mien

`HG-R4`, telle que je l'ai posee, demande qu'**aucun enonce du fichier ne depasse vingt-cinq mots**.
Mesure de ce jour, mot par mot, espaces insecables compris, sur les 92 enonces du fichier :

| Perimetre | Enonces | Mediane | Maximum |
|---|---|---|---|
| Fichier entier | 92 | 16 | **30** |
| Les 35 items neufs du lot | 35 | - | **25** |
| Les 40 items de mes quatre lignes | 40 | 17 | **27** |

**Les quatre enonces qui depassent vingt-cinq mots sont tous anterieurs au lot.** Et celui de 27
mots qui tombe sur `HG-CM1-04` - *"A partir du IVe siecle, des peuples venus du nord et de l'est
s'installent dans l'Empire romain. Les Francs sont l'un d'eux. Comment appelle-t-on ces peuples ?"* -
**est mon propre texte**, la reformulation que j'ai fournie mot pour mot le 2026-09-03 pour lever ma
reserve bloquante sur l'item 5.

> **Je tranche donc la question que `marc-dev` m'a routee a s29 et que je n'ai pas tranchee depuis :
> `HG-R4` vise le lot livre, pas le fichier entier.** Sous cette lecture, `HG-R4` est **tenue** sur
> ce lot : les 35 items neufs comptent entre 8 et 25 mots, mediane du fichier inchangee a 16.
>
> Je retiens la plus indulgente des deux lectures que `marc-dev` proposait, et le motif est
> celui-la : **la lecture severe se retourne d'abord contre mon propre texte**, et une barre dont
> l'auteur s'exonere n'est pas une barre. Ce n'est pas un poteau abaisse au profit de qui que ce
> soit : c'est une ambiguite de ma redaction, ouverte depuis s29, fermee ici dans le sens qui ne
> demande a personne de reecrire ce que j'ai ecrit.

### 1.2 Le plancher de `FER-3` est **trois**, et je le confirme sur mon propre texte

Une consigne fautive a dit quatre. **Ce n'est pas ce que j'ai ecrit.** `FER-3`, mot pour mot, le
2026-09-03 : *"Au moins deux taches distinctes, la moins servie portant **au moins trois items**."*
Le depot l'a encodee sans se tromper - `MINIMUM_PAR_TACHE = 3` dans onze classes de test, dont
`PaquetCm1HistoireGeographieTest` - et ma propre matrice le confirme sur `HG-CM1-07` : *"7 contre 3,
exactement au plancher, `nadia-prof` le dit tel quel."*

> **Le plancher est trois.** `marc-dev` a construit sur le standard ecrit du depot, il a signale
> l'ecart de consigne, et rien de ce lot ne se juge sur un plancher de quatre.

**Et je precise le point que la consigne fautive brouillait, parce que les deux lignes ne sont pas
dans le meme cas** :

- **`HG-CM1-03` : le plafond a trois est structurel, et je le confirme.** Le libelle enumere trois
  peuples - les Gaulois, les Grecs, les Romains. La tache *"nommer le peuple a partir d'une trace
  decrite"* ne peut donc porter que trois items sans que deux d'entre eux partagent la meme bonne
  reponse, ce que `FAB-1a` interdit. `marc-dev` a raison, et la tache est **exactement au plancher,
  au plafond que mes deux clauses laissent, sans un item de marge**.
- **`HG-CM1-04` : le plafond a trois n'est pas structurel, et c'est moi qui le dis contre la note du
  paquet.** Le libelle - *"les grands mouvements et deplacements de populations du IVe au Xe
  siecle"* - **n'enumere rien et ne ferme aucune liste**. Un quatrieme mouvement du meme intervalle
  existe et s'enseigne : l'installation des Bretons venus de Grande-Bretagne en Armorique. Cette
  distinction n'est pas academique : elle decide si le refus que je prononce en section 5 a une
  sortie ou pas. **Il en a une.**

### 1.3 Ma propre methode de comptage a derape trois fois le 2026-09-08, et je ne m'en sers pas

Section 16.5 de mon arbitrage prealable : `EMO-R1` nomme deux enonces trop longs quand il y en a
trois, `EXC-R4` de meme, et les cinq longueurs de `POL-R1` ne sont pas recomptees sous ma propre
convention. Les trois ecarts vont dans le meme sens. **C'est le terrain de l'instance B et je ne
l'audite pas** ; je le nomme parce que c'est ma methode qui a derape, et parce que **tous les
chiffres de ce document sont recomptes a la machine sur l'arbre que je juge**, jamais repris d'un
compte anterieur. `LON-1` et `LON-2` sont desormais executees par `ADR-0089` ; elles ne portent que
sur le CP et ne s'appliquent a aucune ligne de mon terrain.

---

## 2. Ce que j'ai verifie, condition par condition, avant de conclure

Comptes refaits par extraction sur le fichier servi, pas repris de la note du paquet.

| Condition | Ce que j'ai mesure | Verdict |
|---|---|---|
| `FER-2` (dix items par ligne) | `HG-CM1-01` 10, `-02` 10, `-03` 10, `-04` 10, `LVE-CM1-07` 10 | **tenue sur les cinq lignes** |
| `HIS-1` (au plus un tiers de restitution) | 17 restitution sur 92 items du fichier, **18,5 %**, plafond entier(92/3) = 30 | **tenue.** Le compte de la note du paquet est juste |
| `HIS-2` (aucun item a deux reponses defendables) | les 40 items de mes quatre lignes, lus un par un | **tenue** |
| `HIS-3` (chaque fait porte sa source) | les 35 items neufs, marqueur par marqueur | **non tenue sur deux items**, sections 4 et 5 |
| `HG-R4` (charge de lecture) | tableau de la section 1.1 | **tenue sur le lot** |
| `FAB-1a` (pas de bonne reponse repetee dans une tache) | les cinq lignes, tache par tache | **tenue partout** |
| `FAB-1b` (distracteurs hors du vivier des bonnes reponses) | `HG-CM1-01`, `-03`, `-04` | **tenue, et je dis pourquoi** : les cinq periodes et les trois peuples sont des **categories fermees**, cas que `FAB-1` exclut explicitement de sa portee - *"c'est licite des lors que la bonne reponse varie"*. Une lecture naive de `FAB-1b` rendrait ces lignes rouges ; ce n'est pas ce que ma clause dit |
| `FAB-1c` (bonne reponse reconnaissable a sa forme) | les cinq lignes | **tenue** |
| `PLA-1` (chaque proposition est un candidat) | les 40 items neufs, ecrits apres le 2026-09-07 donc dans sa portee | **tenue en histoire-geographie ; non tenue sur trois des cinq items neufs d'anglais**, section 6 |
| `BIA-5` (aucun jugement de valeur sur un personnage) | les items des lignes 1 a 4 | **tenue** |
| `BIA-1`, `BIA-2` (anglais) | 10 items de `LVE-CM1-07` : Royaume-Uni 4 traits, Etats-Unis 6 | **tenues** |
| `CONS-1` (la consigne nomme la tache de son jeu) | les cinq clauses de la consigne servie, confrontees aux 92 items | **non tenue**, section 8 |
| Exactitude | chacun des 40 items d'histoire-geographie et chacun des 10 items d'anglais : enonce, bonne reponse, distracteurs, indice, texte-support | **deux defauts bloquants, quatre reserves**, sections 4, 5, 6 |

---

## 3. `HG-CM1-01` - **VALIDE**, la ligne ferme, avec une mention de couverture partielle

**Verdict : `couvert-valide`.**

- **`FER-1`** : les cinq periodes de la parenthese sont chacune servies **en bonne reponse** - la
  Prehistoire (items 58, 61), l'Antiquite (1, 62), le Moyen Age (59, 63), les Temps modernes (2,
  64), l'epoque contemporaine (60, 65). Avant ce lot, deux l'etaient.
- **`FER-2`** : 10 items.
- **`FER-3`** : deux taches a cinq items chacune, et **elles different par l'operation, pas par la
  phrase** - c'est le critere que j'ai ecrit. *Ordonner* (*"Quelle grande periode vient juste
  apres... ?"*) demande de parcourir une suite ; *identifier a partir d'un fait decrit* (*"Les
  chevaliers vivaient dans des chateaux forts entoures de douves. A quelle periode cela
  correspond-il ?"*) demande de rattacher un indice concret a une categorie. Minorite a **5**,
  au-dessus du plancher de trois.
- **Exactitude** : les dix items verifies. L'ordre des cinq periodes est juste sur les cinq items
  d'ordre ; les cinq faits d'ancrage - art parietal, amphitheatres et gladiateurs, chateaux forts et
  douves, Versailles sous Louis XIV, ordinateur et telephone - sont chacun dans la bonne periode.
- **`PLA-1`** : tenue, et le motif merite d'etre ecrit parce qu'il n'est pas evident. Un distracteur
  comme *"le Moyen Age"* face a *"ordinateur ou telephone"* n'est pas serieux pour un enfant qui
  sait quelque chose - mais il **appartient a la categorie fermee que l'item enseigne**, et `PLA-1`
  vise la proposition *"qui n'appartient a rien"*. Elle n'est pas violee ici.

### La mention de couverture partielle - due, et j'en suis la source

Le libelle dit *"Situer et ordonner les grandes periodes historiques **sur une frise**"*. **Le
produit ne sert aucune frise et n'en servira pas au palier 1.** L'enfant ordonne les cinq periodes
par la lecture ; il ne les place jamais.

C'est exactement le regime de `FER-1` - *"la ligne ne ferme alors que sur sa part servie, avec la
mention de couverture partielle ecrite dans la matrice"* - et exactement ce que j'ai deja fait sur
`QLM-CP-14`, dont le libelle porte la meme frise, et sur `HG-CM1-14`, fermee *"sur sa part servie"*
avec sa mention. **Je m'applique mon propre precedent ; je n'invente pas une exigence pour cette
ligne-ci.**

**Aucune mention n'etait pre-ecrite pour cette ligne** - celle que `FER-1` annonce *"exigee d'avance
sur la frise"* est celle de `QLM-CP-14`, ecrite dans la matrice depuis le 2026-09-02. **Je l'ecris
donc ici, aujourd'hui, et j'en suis la source. Elle est a reprendre mot pour mot par `camille-po`,
sans reformulation** :

> *"ordre et identification des cinq grandes periodes par la lecture seule ; le placement sur une
> frise reste non couvert."*

---

## 4. `HG-CM1-02` - **REFUSE**. La ligne ne ferme pas et reste `en cours`

**Verdict : la ligne ne passe pas `couvert-valide`.** `FER-1` (siecle ou millenaire, et ordonner),
`FER-2` (10 items) et `FER-3` (5 contre 5) sont tenues - je le dis d'abord, parce que le refus ne
porte pas sur elles. Motif unique, bloquant, et actionnable sans me redemander ce que je voulais
dire.

### `HG-R11` - **reserve bloquante, datee du 2026-09-09** : *"le sacre de Clovis"* n'a jamais eu lieu

**Texte servi, vu a l'ecran le 2026-09-09, deuxieme partie, manche 2 :**

| Ou | Texte |
|---|---|
| Item 70, **bonne reponse** | *"le sacre de Clovis, roi des Francs, en 481"* |
| Item 71, **distracteur** | *"le sacre de Clovis, roi des Francs, en 481"* |

**Le fait est faux, et il l'est deux fois.** En 481, Clovis **succede** a son pere Childeric et
devient roi des Francs saliens de Tournai : il n'y a pas de ceremonie, et il n'y en aura jamais pour
lui. **Le sacre d'un roi franc commence avec Pepin le Bref, en 751**, et c'est precisement ce que le
programme de cycle 3 enseigne - ce qu'on enseigne de Clovis, c'est son **bapteme**, vers 496, que ce
paquet ne mentionne nulle part.

**Le meme fichier dit la chose juste plus haut** : l'item 66 porte *"Clovis devient roi des Francs en
481"*, et je l'ai valide. **Le paquet se contredit lui-meme sur son propre fait.**

**Et le marqueur `HIS-3` ne source pas ce que l'item affirme** : il porte *"dates 481, 800 et 1789,
consensuelles, famille (a)"*. La **date** est sourcee et elle est juste ; l'**evenement** que l'item
nomme ne l'est pas. Un marqueur qui source autre chose que ce que l'item dit ne tient pas `HIS-3`.

**Pourquoi c'est un refus et non une reserve, et ce n'est pas un durcissement.** Ma doctrine est
ecrite depuis le 2026-09-06, dans le bloc `ANG-R2` de `FICHE-bandit-manchot-anglais-cm1.md`, mot
pour mot : *"Pourquoi ce n'est pas un refus, alors qu'une erreur de fait est chez moi un motif de
refus a elle seule. **Aucune reponse attendue ne depend de cette phrase** : elle vit dans une
consigne, pas dans une question."* C'est le meme motif qui a fait de `HG-R7` - le 14 juillet fete
nationale des 1790 - une reserve non bloquante sur un texte-support, ligne fermee.

> **Ici, la reponse attendue *est* la phrase fausse.** L'enfant qui joue juste est recompense pour
> avoir choisi un evenement qui n'existe pas, et l'enfant qui joue faux se le voit presenter comme
> ecarte pour une raison de date - ce qui le confirme comme evenement. **La condition de ma propre
> doctrine est remplie dans le sens du refus.** Je n'invente pas une barre : je l'applique la ou je
> l'avais posee.

**Ce qui la leve** : reprendre, aux items 70 et 71, la formulation que l'item 66 du meme fichier
porte deja - *"Clovis devient roi des Francs, en 481"*. **Le texte existe dans le depot** ; ni la
date, ni la tache, ni les autres propositions n'ont a changer. **Regime : mot pour mot, 0,25.**

### `HG-R9` - reserve nouvelle, datee du 2026-09-09, **non bloquante**

**Les cinq items de la tache *ordonner* donnent la reponse dans leur indice.** Vu a l'ecran sur
trois des cinq :

| Item | Indice servi apres une erreur | Ce qu'il fait |
|---|---|---|
| 70 | *"Repense a la date la plus ancienne des trois : 481."* | **une seule proposition porte 481** |
| 71 | *"Repense a la date la plus recente des trois : 1789."* | idem |
| 72, 73, 74 | meme forme, avec 800, 1804, 1598 | idem |

**C'est `M21` et c'est la famille exacte de `HG-R8`** : un indice qui nomme la reponse est une
revelation deguisee. Ici il ne la nomme pas, il la **designe sans ambiguite**, ce qui revient au meme
puisque chaque proposition affiche sa date.

**Pourquoi ce n'est pas bloquant** : les cinq bonnes reponses sont justes, l'indice n'est lu qu'apres
une erreur, et je me tiens a `HG-R8` - *"un item trop facile n'est pas un item faux"*. **Mais je
nomme ce que cela coute, parce que c'est plus large que `HG-R8` ne l'etait** : `HG-R8` portait sur
deux items d'une ligne de dix ; celui-ci porte sur **la totalite de la seconde tache** de la ligne,
c'est-a-dire sur la moitie que `FER-3` compte.

> **`HG-R9`. Propriete a tenir** : sur les items d'ordre de `HG-CM1-02`, l'indice porte **la
> methode** - ce qu'il faut comparer, comment on situe une date par rapport a une autre - et
> **jamais la date de la bonne reponse** quand chaque proposition affiche la sienne. **Je ne
> fournis pas les textes** : ecrire un indice de methode est le jugement qu'il faut faire ici.
> **Regime : rejeu borne, 0,5.**

### Un point que je verifie et qui **n'est pas** un defaut, dit pour qu'un tiers ne le compte pas comme tel

Item 67 : *"Charlemagne est couronne empereur en 800. En quel siecle cela se passe-t-il ?"* -> *"le
VIIIe siecle"*. **C'est juste** : 800 est la derniere annee du VIIIe siecle (701-800), la meme
convention qui met 1789 au XVIIIe (1701-1800), et l'indice l'ecrit. C'est le **seul item du paquet
qui tombe sur une annee en 00**, c'est-a-dire sur le cas ou la regle scolaire usuelle - *"les deux
premiers chiffres plus un"* - donne le distracteur. L'indice explique l'intervalle, donc l'erreur
enseigne quelque chose. **Je ne l'ouvre pas en reserve** ; je l'ecris parce que sans cela il se
relira comme une erreur de fait.

---

## 5. `HG-CM1-04` - **REFUSE**. La ligne ne ferme pas et reste `en cours`

**Verdict : la ligne ne passe pas `couvert-valide`.** `FER-1`, `FER-2` et `FER-3` sont tenues - je le
dis d'abord, parce que le refus ne porte pas sur elles.

- **`FER-1`** : le libelle enumere par *"et"* plutot que par une parenthese, et `FER-1` dit *"et
  **notamment** par sa parenthese"* - donc elle s'applique. Ses deux elements sont servis : **les
  mouvements** (peuples germaniques, Vikings, l'armee de 732) et **leurs effets sur le territoire**
  (sept items). La note du paquet ecrit *"pas de `FER-1` numerote sur cette ligne"* ; **c'est
  inexact, `FER-1` s'y applique - et elle est tenue.** Je corrige la lecture dans le sens qui ne
  coute rien a personne.
- **`FER-2`** : 10 items. **`FER-3`** : 3 contre 7, minorite exactement au plancher.

### `HG-R12` - **reserve bloquante, datee du 2026-09-09** : *"les Sarrasins"* en bonne reponse a la question *"Quel peuple ?"*

**Texte servi, vu a l'ecran le 2026-09-09 :**

> *"En 732, une armee venue du sud est arretee pres de Poitiers par Charles Martel. **Quel peuple**
> menait cette armee ?"* -> **`les Sarrasins`**, contre *"les Vikings"* et *"les Romains"*.

`marc-dev` a signale le terme dans le commentaire du paquet et l'a laisse a mon arbitrage. **Je
l'arbitre, et je ne le signe pas.** Deux motifs, et le premier suffit.

**1. `HIS-3`, famille (b) - ma propre precision du 2026-09-03.** J'y ai distingue deux familles de
faits : *"(a) fait de manuel sans variante - elle en est une source recevable et signe ; (b) fait
date, dispute, ou dont la formulation engage une couverture - elle ne signe pas, il faut une source
ou une reformulation qui retire le besoin de source."* **Une designation que le developpeur lui-meme
route a mon arbitrage est, par construction, de la famille (b).** Je n'ai pas d'outil de recherche en
ligne cette session, pas plus que lui, et `HIS-3` est **bloquante** sur cette matiere par declaration
du referentiel et de la matrice. Je ne signe pas un terme que je ne peux pas sourcer et que je sais
dispute.

**2. Le fond, et il ne depend d'aucune source.** *Sarrasins* n'est pas le nom d'un peuple. C'est une
designation medievale chretienne, appliquee globalement aux musulmans, qu'aucun groupe n'a jamais
revendiquee pour lui-meme ; l'armee arretee en 732 est celle du gouverneur omeyyade d'al-Andalus, et
ses troupes sont arabes et berberes. **La question demande un *peuple* et la reponse n'en nomme
pas.** Ce n'est donc pas un debat sur un mot traditionnel : c'est un desaccord de nature entre la
question et la reponse qu'elle recompense.

Et le contexte rend le point plus net, pas moins : **les huit autres items de ces deux lignes nomment
des peuples reels** - Romains, Gaulois, Grecs, Francs, Vikings, peuples germaniques. Le seul qui n'en
nomme pas est celui qui designe des musulmans. **Un enfant de neuf ans ressort de cet item en croyant
que *les Sarrasins* etaient un peuple, et il ne pourra jamais reemployer ce mot.** `BIA-5` ne mord pas
ici - elle interdit le jugement de valeur sur un **personnage** - et je ne l'invoque pas.

> **`HG-R12`. Propriete a tenir, et je ne fournis pas le texte** - ecrire cet item est le jugement
> qu'il faut faire. Sur `HG-CM1-04`, aucune bonne reponse ne designe un groupe humain par un exonyme
> qu'aucun groupe ne revendique. Deux directions me conviennent, et il n'y a pas a m'en redemander
> une troisieme : **soit l'item nomme l'armee par son origine** plutot que par un nom de peuple,
> **soit l'item cesse de porter sur l'identite de l'armee** et porte sur le mouvement lui-meme, comme
> le font deja les items 90 et 92 de la meme ligne.
>
> **Et la sortie existe, contrairement a ce que la note du paquet laisse croire** (section 1.2) : la
> tache de nomination est a trois items, mais son plafond **n'est pas structurel** sur cette ligne -
> le libelle ne ferme aucune liste. Un quatrieme mouvement du IVe-Xe siecle s'enseigne :
> l'installation des Bretons venus de Grande-Bretagne en Armorique. **Le refus ne ferme donc pas la
> ligne, il demande un item.** **Regime : rejeu borne, 0,5.**

### `HG-R10` - reserve nouvelle, datee du 2026-09-09, **non bloquante**

**Indice servi, vu a l'ecran apres deux erreurs sur l'item 87** (*"Les Francs s'installent en Gaule a
la fin du Ve siecle et fondent un royaume qui deviendra un grand pays d'Europe. Lequel ?"* -> *"la
France"*) :

> *"Leur roi Clovis unifie ce royaume en 481."*

**Faux.** 481 est l'annee ou Clovis devient roi d'un petit royaume franc autour de Tournai ;
l'unification vient de Soissons (486) et de Vouille (507). **C'est le troisieme endroit de ce fichier
ou l'annee 481 se voit attribuer un evenement qu'elle ne porte pas** - avec les items 70 et 71 de la
section 4.

**Non bloquante, et je m'applique mon precedent plutot que de durcir parce que c'est le meme
sprint** : l'indice n'est pas une reponse attendue, et `HG-R7` comme `ANG-R2` ont ete traitees en
reserve pour ce motif exact. **Elle doit neanmoins etre corrigee avant que la ligne ferme, puisque la
ligne est refusee par ailleurs.** **Regime : mot pour mot, 0,25** - la formulation juste est celle de
l'item 66 du meme fichier.

### Deux points verifies qui ne fondent aucune demande

- L'indice de l'item 85 emploie *"la peninsule Iberique"*, terme au-dessus du CM1 et **plus difficile
  que la question qu'il aide**. Il disparaitra avec la reecriture que `HG-R12` demande ; je ne lui
  alloue pas de code pour ne pas facturer deux fois le meme geste.
- L'item 90 - *"Que devient son expansion en Europe ?"* -> *"elle s'arrete"* - est une simplification
  scolaire (les incursions continuent apres 732). **Elle est celle du programme**, et je ne la
  reprends pas.

---

## 6. `LVE-CM1-07` - **VALIDE SOUS RESERVE**. La ligne ferme, avec sa mention et quatre reserves

**Verdict : `couvert-valide`.**

- **`FER-2`** : 10 items, cinq de s28 et cinq neufs. Plancher atteint.
- **`FER-1`** : voir ci-dessous - la ligne n'enumere rien et la condition est donc infalsifiable en
  l'etat ; je l'accepte sur la declaration du lot.
- **`FER-3`** : **le lot ne la revendique pas et aucun test ne la mord.** Je l'ai donc etablie
  moi-meme, et je donne le partage pour qu'il soit contestable : *restituer un fait que la question
  designe par son pays* (items 16, 18, 21, 24 - **4**) contre *reconnaitre un objet ou une fete a
  partir des traits qu'on en decrit* (17, 19, 20, 22, 23, 25 - **6**). Minorite a 4, au-dessus du
  plancher. **Tenue sur mon partage, et personne d'autre que moi ne peut le refaire** : sans
  declaration de tache par item, `FER-3` n'est calculable sur cette ligne par aucun automate. C'est
  le meme manque qu'`ADR-0081` a comble pour les lignes de referentiel.
- **`BIA-1`** : tenue. Aucun item ne compare un ailleurs a un ici en position de norme.
- **`BIA-2`** : tenue, comptee - Royaume-Uni 4 traits, Etats-Unis 6, aucun pays reduit a un seul.
- **`FAB-1a`** : tenue, dix bonnes reponses distinctes.
- **Exactitude** : les dix items verifies un par un. Uniforme scolaire au Royaume-Uni, bus a
  imperiale de Londres, Halloween le 31 octobre, Thanksgiving fin novembre, statue de la Liberte a
  l'entree du port de New York, drapeau a cinquante etoiles, Londres et Washington capitales : tous
  justes. Une seule inexactitude, dans un indice : `ANG-R5`.

### La mention exigee d'avance, **reprise mot pour mot depuis ma source, non reformulee**

> *"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"*

### La parenthese que le lot invoque n'est pas dans la ligne qu'il ferme - et le defaut est le mien

Le commentaire du paquet et le Javadoc du test disent *"les trois elements de la parenthese du
referentiel (fetes, lieux, vie quotidienne)"*. **`LVE-CM1-07` ne porte aucune parenthese** :
*"Decouvrir quelques aspects culturels des pays de la langue etudiee."*, point. La parenthese est sur
**`LVE-CP-06`**, la meme competence au CP.

**C'est une incoherence de mon propre referentiel** : j'ai ecrit la meme competence a deux niveaux et
je n'ai mis la parenthese qu'au CP. Consequence directe : **`FER-1` est infalsifiable sur cette
ligne**, exactement le trou que `ANG-4` a comble pour `LVE-CM1-04`.

Ce que j'en fais, et ce que je n'en fais pas :

- **Je ne corrige pas le referentiel pendant que j'audite le lot qui s'y mesure.** Ajouter la
  parenthese aujourd'hui rendrait `FER-1` plus mordante sur le lot que je juge : ce serait un poteau
  deplace, et je n'en plante pas un sur mon propre oubli.
- **J'accepte la declaration du lot comme tenant le role de `FER-1`** : trois aspects declares -
  fetes, lieux, vie quotidienne - chacun servi en bonne reponse, *vie quotidienne* n'etant servie par
  aucun des cinq items de s28. C'est le regime d'`ANG-4`, et le lot l'a tenu.
- **Je le route a `camille-po` via `sofia-sm`** : la meme competence doit-elle porter la meme
  parenthese aux deux niveaux ? La ligne de referentiel est a corriger hors audit, et je ne la
  tranche pas dans le document qui juge le lot.

### `ANG-R3` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : `PLA-1` sur trois des cinq items neufs

`PLA-1`, posee le 2026-09-07 et valant *"pour les paquets ecrits a partir d'aujourd'hui"*, s'applique
aux cinq items neufs et a eux seuls. **Elle n'est pas tenue sur trois d'entre eux.**

| Item | Ce que l'enonce donne deja | Les propositions a ecarter |
|---|---|---|
| 22 - *"Quel vehicule rouge a deux etages circule dans les rues de Londres ?"* | rouge, deux etages, **dans les rues** | *"le metro souterrain"* n'est ni rouge, ni a etages, ni dans les rues - l'enonce l'exclut trois fois ; *"le tramway"* n'a pas deux etages |
| 23 - *"Quelle fete, celebree le 31 octobre aux Etats-Unis, ou les enfants se deguisent et recoltent des bonbons, est tres populaire ?"* | la date, les deguisements, les bonbons | *"Noel"* et *"le Nouvel An"* tombent sur la seule date |
| 25 - *"Quel monument, une grande statue tenant une torche, se trouve sur une ile a l'entree du port de New York ?"* | une statue, une torche, une ile, New York | *"la tour Eiffel"* et *"le Colisee"* ne sont ni des statues ni a New York |

**Ces trois items se repondent sans rien savoir du Royaume-Uni ni des Etats-Unis** : ils se repondent
en lisant. C'est le motif meme de `PLA-1` - *"un item dont l'une des propositions n'appartient a rien
redevient binaire de fait"* - sauf qu'ici il redevient **unaire**.

**Et un second defaut se cache derriere le premier** : la bonne reponse de l'item 22 est *"le bus a
imperiale"*, ou **le mot *imperiale* est au-dessus du CM1 et n'est explique nulle part**. Combine a
la faille ci-dessus, l'enfant gagne l'item **sans avoir appris le mot** : il l'a designe par
elimination.

**Pourquoi ce n'est pas un refus.** `PLA-1` a produit `SIG-R2` et `MEL-R3`, deux reserves non
bloquantes sur des lignes que j'ai fermees, et je m'y tiens. Aucune bonne reponse n'est fausse.
**Mais je dis ce que cela coute, une fois** : `FER-2` compte dix items sur cette ligne, et trois des
cinq que ce lot a ajoutes ne sont pas des exercices. **C'est ce que ma propre clause voulait dire en
ecrivant que `FER-2` est *"necessaire et non suffisante"*.**

> **`ANG-R3`. Propriete a tenir** : sur les items neufs de `LVE-CM1-07`, aucune proposition ne
> s'ecarte par la seule lecture de l'enonce. Soit l'enonce cesse de decrire l'objet qu'il fait
> nommer, soit les propositions deviennent des candidats reels du meme genre - un autre monument de
> New York, une autre fete americaine d'automne, un autre transport londonien. **Regime : rejeu
> borne, 0,5.**

### `ANG-R4` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : l'enonce de l'item 23

*"Quelle fete, celebree le 31 octobre aux Etats-Unis, ou les enfants se deguisent et recoltent des
bonbons, est tres populaire ?"* - **21 mots, dont 14 inseres entre le sujet et le verbe** de la
question principale (*"Quelle fete ... est tres populaire ?"*). C'est l'enonce le plus lourd de la
ligne, a egalite avec l'item 25, et le seul dont la question principale soit coupee en deux.

> **`ANG-R4`. Propriete a tenir** : sur `LVE-CM1-07`, la question principale d'un enonce n'est pas
> coupee par une incise. La forme de l'item 20 du meme paquet - *"Quelle fete traditionnelle,
> celebree fin novembre aux Etats-Unis, reunit la famille autour d'un grand repas ?"* - la tient
> deja et sert de patron. **Regime : mot pour mot, 0,25.**
>
> *Le rattachement du relatif* ***ou*** *- qui se lit comme portant sur* ***Etats-Unis*** *et non sur*
> ***fete*** *- est de la grammaire : il appartient au `relecteur-pedagogique` (`DEC-0002`,
> amendement du 2026-08-04 ; `DEC-0056`) et je le lui route sans le compter dans ma reserve.*

### `ANG-R5` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : l'indice de l'item 16

*"Quelle est la capitale du Royaume-Uni ?"* -> *"Londres"*, indice *"C'est aussi le nom d'une grande
roue celebre au bord de la Tamise."*

**La grande roue s'appelle le *London Eye*, pas *Londres*.** L'indice affirme que la reponse *"est
aussi le nom"* de la roue ; elle ne l'est pas.

**Deux choses a dire honnetement.** L'item date de s28 et le lot ne l'a pas touche - **mais il est
l'un des dix que je compte pour fermer la ligne aujourd'hui, donc il est dans mon perimetre**. Et le
paquet ecrit que *"`nadia-prof` les a confirmes bons au reaudit du 2026-09-08"* : **c'est plus fort
que ce que j'ai ecrit.** J'avais verifie *"les cinq items de `LVE-CM1-07` servis, quatre d'entre eux
dans une seule partie ; la consigne a deux taches, exacte sur les dix manches"* - servis et exacts
sur les manches jouees, pas un audit signe des cinq. **Je corrige la citation avant qu'elle serve de
caution.**

> **`ANG-R5`. Propriete a tenir** : l'indice n'attribue pas a la bonne reponse un nom qui n'est pas le
> sien. **Regime : mot pour mot, 0,25.**

### `ANG-R6` - reserve nouvelle, datee du 2026-09-09, **non bloquante** : l'item 24 et sa majorite

*"Aux Etats-Unis, dans de nombreuses regions, comment la plupart des eleves se rendent-ils a
l'ecole ?"* -> *"en bus scolaire jaune"*, contre *"a pied"* et *"en metro"*.

**Je ne refuse pas, et je dis pourquoi plutot que de le laisser deviner.** L'item affirme une
majorite - *"la plupart des eleves"* - que je ne peux pas sourcer cette session. La double reserve
*"dans de nombreuses regions"* la rend defendable : dans les districts ruraux et periurbains, le
ramassage scolaire est bien le mode dominant. **Une affirmation couverte par sa propre restriction
n'est pas une erreur de fait**, et je ne la traite pas comme telle.

**Ce qui reste, et qui est reel** : le mode qu'un enfant francais nommerait d'abord - *"en voiture,
avec un parent"* - **est absent des propositions**, alors qu'il est, dans une grande partie des
Etats-Unis, celui qui dispute la majorite au bus. L'item ecarte donc son seul candidat serieux et
retient les deux qui ne le sont pas.

> **`ANG-R6`. Propriete a tenir** : quand un item affirme une pratique majoritaire d'un pays, l'une de
> ses propositions est la pratique concurrente reelle, et non deux pratiques ecartables. **Regime :
> mot pour mot, 0,25.**

---

## 7. `EXA-1` - je codifie une doctrine deja ecrite, je n'en ajoute pas une

Trois fois en trois sprints, la meme question s'est posee et j'y ai repondu de la meme facon sans que
la reponse porte un nom : `HG-R7` (2026-09-06, texte-support faux, reserve, ligne fermee), `ANG-R2`
(2026-09-06, consigne fausse, reserve, aucune ligne touchee), `HG-R11` (aujourd'hui, bonne reponse
fausse, **refus**). Le critere que j'ai applique les trois fois est ecrit dans le bloc `ANG-R2` ; il
n'est simplement pas citable.

> **`EXA-1` - ou une erreur de fait bloque, et ou elle ne bloque pas.** Une inexactitude dans un texte
> servi a l'enfant **bloque la fermeture de la ligne qu'elle sert** lorsqu'une **reponse attendue en
> depend** - c'est-a-dire lorsqu'elle vit dans un enonce, dans une bonne reponse ou dans un
> distracteur. Elle est une **reserve non bloquante** lorsqu'elle vit dans une consigne, dans un
> texte-support ou dans un indice : la ligne peut fermer, la correction est due.

**Ce que `EXA-1` ne fait pas, et c'est l'essentiel.** Elle **ne rouvre aucune ligne fermee** :
`HG-CM1-14`, fermee le 2026-09-06 en portant `HG-R7`, reste fermee, et son verdict ne se reecrit pas.
Elle **ne durcit rien** : elle nomme le critere que j'ai deja applique trois fois, dans les deux sens,
y compris contre moi. Un developpeur peut desormais se l'opposer avant d'ecrire, ce qui est tout ce
qu'elle apporte. Datee du **2026-09-09**.

**Et je dis franchement la limite** : je n'ai aucun outil de recherche en ligne cette session, pas
plus que les developpeurs. `EXA-1` ne rend donc pas l'exactitude mordable ; elle dit seulement ce
qu'on fait d'une inexactitude quand quelqu'un la trouve. **L'exactitude factuelle reste sans garde
executable, et je reste seule dessus.**

---

## 8. Portee D, premier volet - le reliquat de `CONS-1` n'est **pas** paye sur cette entree

**Consigne servie, relevee a l'ecran a chacune de mes 31 parties :**

> *"Lis bien, puis situe dans le temps, nomme un peuple ou un mouvement de population, associe un roi
> a son fait, decris un espace ou une ressource, ou lis un document."*

**La clause neuve fait ce qu'elle annonce, et je le porte au credit du lot** : les items que j'avais
cites le 2026-09-08 comme portant une tache que la consigne ne nommait pas - *"Quel peuple les a
construits ?"* et *"Comment appelle-t-on ces peuples ?"*, sur `HG-CM1-03` et `HG-CM1-04` - sont
desormais nommes.

**Et le lot a cree, dans le meme geste, quatorze items dont la tache n'est nommee par aucune des cinq
clauses.** Ce sont les taches majoritaires des deux lignes qu'il voulait fermer :

| Ligne | Items | La tache reelle | La clause qui la nommerait |
|---|---|---|---|
| `HG-CM1-03` | 77 a 83 (**7**) | nommer **ce qu'un peuple a laisse** : des arenes, un aqueduc, un reseau de routes, une colonie, la vigne, le fer, des mots de vocabulaire | aucune. *"nomme un peuple ou un mouvement de population"* ne couvre pas la trace ; *"decris un espace ou une ressource"* ne couvre pas un aqueduc |
| `HG-CM1-04` | 86 a 92 (**7**) | dire **un effet du mouvement sur le territoire** : disparition de l'Empire d'Occident, naissance de la France, naissance de la Normandie, siege de Paris, arret de l'expansion, division en royaumes, dynastie carolingienne | aucune |

**Et les trois taches non nommees que je citais deja le 2026-09-08 le sont toujours** : nommer un
texte (item 49, `HG-CM1-07` - vu a l'ecran, deuxieme partie, manche 4), nommer une installation (item
35, `HG-CM1-13`), nommer un regime politique (item 13, `HG-CM1-08`).

> **`CONS-1` reste non levee sur `bandit-manchot-histoire-geographie-cm1`.** Et le compte d'items dont
> la tache n'est pas nommee est **plus grand apres le lot qu'avant** : deux ont ete nommes, quatorze
> ont ete crees. **Ce n'est pas un reproche au lot** - il n'avait pas pour objet de refondre la
> consigne - **c'est le constat que la methode ne converge pas.** Je ne donne pas de pourcentage :
> l'attribution item par tache est la mienne, faite a la main, et un chiffre que personne ne peut
> refaire ne vaut pas mieux qu'aucun chiffre. Les quatorze items ci-dessus sont nommes un par un, ce
> qui suffit a agir.

### `CONS-4` - pourquoi la methode ne converge pas, et c'est une trouvaille de portee D

**Aucun audit de lot ne peut voir ce fait**, ce qui est la definition meme de la portee D.

| Arbre | La consigne de cette entree | Mots | Taches nommees |
|---|---|---|---|
| s27-s30 | *"Lis bien, puis reponds a la question d'histoire ou de geographie."* | 12 | **0** |
| s31 | *"Lis bien, puis situe dans le temps, associe un roi a son fait, decris un espace ou une ressource, ou lis un document."* | 22 | 4 |
| s32 | la consigne servie ci-dessus | **31** | 5 |

**La consigne grossit d'une clause par sprint, et il reste des lignes a servir dans ce paquet.** Ce
n'est pas un defaut d'ecriture : c'est la consequence arithmetique de payer `CONS-1` sur un jeu unique
qui sert onze lignes de referentiel.

**Et l'objet de `CONS-1` cesse d'etre atteint en chemin.** `CONS-1` existe parce que *"la consigne
generique dit a l'enfant de lire et de choisir, ce que la forme de l'ecran lui dit deja"*. Une
enumeration de cinq taches alternatives en 31 mots **ne dit toujours pas a l'enfant ce qu'il doit
faire** : la tache est decidee par l'item qu'il a devant lui, pas choisie par lui dans une liste qu'il
a lue avant de commencer. **L'enfant lit 31 mots pour n'en retenir aucun.**

> **`CONS-4`. Le fait, et ce que je route.** Sur `bandit-manchot-histoire-geographie-cm1`, la consigne
> de catalogue croit d'une clause par sprint et n'informe plus l'enfant de sa tache. **La cause n'est
> pas la consigne, c'est le grain du jeu** : une entree de catalogue resout vers un fichier, et onze
> lignes de referentiel dans un jeu unique n'ont pas de consigne commune qui soit une consigne.
> `marc-dev` a route ce decoupage a s28 en disant que le nombre et le grain des jeux proposes a
> l'enfant sont une decision de contenu qu'`ADR-0081` reserve a `camille-po` et a moi. **Il a raison
> et la question m'est revenue par un autre chemin.**
>
> **Ce que je dis, et ce que je ne dis pas.** Je dis la propriete a tenir : *la consigne d'un jeu
> nomme une tache que l'enfant reconnaitra dans la manche qu'il a devant lui.* **Je ne tranche pas le
> decoupage du jeu dans ce document** - ce serait fixer un perimetre, et je n'en fixe pas ; et je ne
> le tranche pas seule, `ADR-0081` le confie a `camille-po` et a moi ensemble. **Route a `camille-po`
> via `sofia-sm`**, avec les deux issues que je vois : ou la consigne cesse d'enumerer, ou le jeu se
> scinde. **Regime : je ne le chiffre pas, le geste n'est pas de mon ressort.**

**`CONS-1` sur `bandit-manchot-anglais-cm1`** : levee depuis le 2026-09-06 et **elle le reste** - la
consigne servie n'a pas change et le lot ne l'a pas touchee. Je ne rouvre rien.

---

## 9. Portee D, second volet - `SCO-1` est **toujours rouge**, et je viens de la rejouer

`DEC-0083` route la verification de `SCO-1` a s33. **Je la fais aujourd'hui parce que je jouais de
toute facon**, et le resultat vaut d'etre ecrit maintenant plutot que dans un sprint.

**Mesure du 2026-09-09, sur l'arbre `fd19d18f`, score lu dans la reponse de chaque appui.** Cinq
parties completes d'histoire-geographie et trois d'anglais, en pressant les propositions dans l'ordre
servi jusqu'a trouver la bonne :

| Jeu | Parties | Score final | Appuis depenses |
|---|---|---|---|
| `bandit-manchot-histoire-geographie-cm1` | 5 | **10 / 10** a chaque partie | 17, 17, 17, 21, 24 |
| `bandit-manchot-anglais-cm1` | 3 | **10 / 10** a chaque partie | 19, 20, 21 |

**Huit parties, huit scores parfaits, et jamais moins de sept appuis de trop pour dix manches.**
Exemple releve tel quel, premiere partie d'histoire-geographie, manche 1 : deux propositions fausses
pressees, puis la bonne, **score 0 -> 1**. Manches 6, 7, 8, 9 de la meme partie : idem.

> **`SCO-1` est rouge sur cet arbre, verifie en jouant et non deduit.** *"`resoudre(true)` fait
> `reussites + 1` quel que soit le nombre d'essais deja depenses"* : c'est exactement ce que j'ai
> mesure. **Un enfant qui ne sait rien rapporte 10 sur 10 de ces deux jeux.**

**Ce que j'en fais, sans bouger d'un mot depuis le 2026-09-08 :**

- **Je n'en fais pas une condition du Done.** Aucune de mes conditions de fermeture ne porte sur le
  score, et 63 lignes ont ete validees sans lui. **Un verdict prononce ne se reecrit pas.**
- **J'applique la reserve que j'ai posee le 2026-09-08 et qui vaut a partir de la cloture de s32** :
  les deux fiches que j'ecris ce sprint nomment, dans leur bloc de validation, que **le score de ces
  deux jeux reste parfait par elimination**. C'est fait dans les deux.
- Le remede appartient a `theo-archi`, son cout n'est pas de mon ressort, et `SCO-1` a `SCO-7` sont
  entierement automatisables.

**Et je note le fait de calendrier, sans le commenter** : `SCO-1` etait a son huitieme sprint sans
report compte le 2026-09-08. Elle en est a son neuvieme.

---

## 10. `FOP-R4` et `DRO-R4` - recues, non auditees, non financees

`sofia-sm` me les affecte au planning (section 7) en reprenant mon Plan 2, et **la meme section ecrit
que cette affectation *"n'ouvre ni ne chiffre"* ces residus dans la composition de s32** : ils restent
au perimetre indicatif de s34.

> **Je les prends en charge et je ne les audite pas ce sprint.** Elles ne sont pas dans mon budget de
> 4,0 - le planning le detaille poste par poste et elles n'y figurent pas - et **je ne facture rien
> pour elles**. Je le nomme ici pour qu'a la cloture personne ne les lise comme traitees, ni comme
> oubliees. `FOP-R4` porte sur `fait-opinion-cm1`, `DRO-R4` sur `droit-devoir-cm1`, EMC CM1, matiere
> qu'aucune autre instance ne touche a s33 ni a s34.

---

## 11. Ce dont je reste la seule garde sur mon terrain

Je le dis explicitement, sinon l'equipe croira couvert ce qui ne l'est pas.

| Propriete | Mordable par un automate ? | Qui la garde aujourd'hui |
|---|---|---|
| **L'exactitude factuelle** - chaque enonce, chaque bonne reponse, chaque distracteur, chaque indice, chaque texte-support | **non**, et `EXA-1` ne la rend pas mordable : elle dit ce qu'on fait d'une inexactitude, pas comment on la trouve | **moi seule.** Les deux refus de ce document en sont la preuve : les tests neufs du lot sont verts sur `HG-CM1-02` et `HG-CM1-04` |
| **`HIS-2`** - aucun item a deux reponses defendables | non | **moi seule** |
| **`HIS-3` famille (b)** - une formulation disputee | non | **moi seule.** C'est ce qui refuse `HG-CM1-04` |
| **`PLA-1`** - une proposition est un candidat | non. La plausibilite pour un enfant de neuf ans ne se calcule pas | **moi seule** |
| **`FER-3` sur `LVE-CM1-07`** | **non, et rien ne la mord** : le lot ne l'a pas revendiquee, aucun test ne l'ecrit, et sans declaration de tache par item elle n'est calculable par personne d'autre que moi | **moi seule** |
| **`FER-3` sur `HG-CM1-01` et `HG-CM1-02`** | **oui, mais par un proxy plus etroit que ma clause.** Les tests neufs comptent les items dont l'enonce **commence par** *"Quelle grande periode vient juste"* ou *"Parmi ces trois"*. Ma clause porte sur **l'operation mentale**, pas sur l'amorce de la phrase | **partage, et le proxy derivera** le jour ou un item d'une autre operation commencera par ces mots. Je le nomme plutot que de laisser croire que la clause est encodee |
| **`CONS-4`** - la consigne informe l'enfant de sa tache | non | **moi seule**, et c'est une propriete de portee D qu'aucun audit de lot ne voit |
| **`SCO-1`** - le score distingue celui qui savait | **oui, entierement**, le backlog le dit lui-meme, et rien n'est ecrit | **personne**, et c'est le probleme |
| La couleur, le contraste, la taille, la position a l'ecran | hors de ma portee cette session (audit degrade, section 0) | **personne sur mon terrain ce sprint** |

---

## 12. Ce que j'ai depense, contre mon budget de 4,0

Budget opposable au planning : **4,0**, decompose en `S32-1` 2,5 + `S32-2` 0,5 + `S32-3` 0,5 + marge
`PAR-11` 0,5. Ma borne de sprint est 12,0 sur trois instances (`DEC-0083`).

| Poste | Budgete | Depense | Ecart et motif |
|---|---|---|---|
| `S32-1` - audit d'histoire-geographie, quatre lignes | 2,5 | **2,50** | conforme. Les deux refus coutent a ecrire ce que les deux mentions non dues font economiser |
| `S32-2` - audit d'anglais, une ligne | 0,5 | **0,75** | **+0,25.** Deux gestes n'etaient pas dans le prix : etablir moi-meme le partage de `FER-3`, que le lot ne revendique pas et qu'aucun test n'ecrit ; et instruire l'incoherence de parenthese de mon propre referentiel |
| `S32-3` - passe transverse, `CONS-1`, `SCO-1` | 0,5 | **0,50** | conforme. `SCO-1` s'est verifiee dans les parties que je jouais de toute facon, ce qui a paye la confrontation des 92 items aux cinq clauses de la consigne |
| `FOP-R4`, `DRO-R4` | 0 | **0** | affectees, non ouvertes (section 10) |
| **Total** | **4,0** | **3,75** | **j'ai tire 0,25 de ma marge `PAR-11`. Il en reste 0,25.** |

**Et je dis ce que ce chiffre est** : une estimation dans mon unite, par moi, et l'unite est un
jugement et non une mesure. Elle ne devient comparable que parce que je l'ecris de la meme facon a
chaque sprint. **Ce qui est mesure, dans ce document, c'est ce qui porte un compte et une methode** -
les 92 enonces, les 17 items de restitution, les 8 parties completes, les 31 mots de la consigne.

---

## 13. Recapitulatif pour `camille-po` - ligne par ligne

**Je ne mets pas la matrice a jour ; c'est `camille-po`, sur ces verdicts.**

| Ligne | Verdict | Statut de matrice | Mention de couverture partielle |
|---|---|---|---|
| `HG-CM1-01` | **VALIDE** | `en cours` -> **`couvert-valide`** | **due**, et a reprendre mot pour mot : *"ordre et identification des cinq grandes periodes par la lecture seule ; le placement sur une frise reste non couvert."* |
| `HG-CM1-02` | **REFUSE** (`HG-R11`, bloquante) | **reste `en cours`** | sans objet |
| `HG-CM1-03` | **VALIDE** | `en cours` -> **`couvert-valide`** | **aucune n'est due** |
| `HG-CM1-04` | **REFUSE** (`HG-R12`, bloquante) | **reste `en cours`** | sans objet |
| `LVE-CM1-07` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** | **due**, et a reprendre mot pour mot depuis ma source : *"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"* |

**Bilan de l'histoire-geographie CM1 apres mes verdicts** : 7 `couvert-valide`, 4 `en cours`
(`HG-CM1-02`, `-04`, `-08`, `-12`), 3 `non couvert`, 14 au total. **Bilan de l'anglais CM1** : 7
lignes, 2 `couvert-valide`, 2 `non couvert`, 3 `non couvrable en l'etat`, plus aucune `en cours`.

**Reserves ouvertes ce jour, avec leur regime, pour que `camille-po` en fasse du backlog** :

| Code | Jeu | Bloquante ? | Regime |
|---|---|---|---|
| `HG-R11` | histoire-geographie CM1 | **oui** | mot pour mot, 0,25 |
| `HG-R12` | histoire-geographie CM1 | **oui** | rejeu borne, 0,5 |
| `HG-R9` | histoire-geographie CM1 | non | rejeu borne, 0,5 |
| `HG-R10` | histoire-geographie CM1 | non | mot pour mot, 0,25 |
| `ANG-R3` | anglais CM1 | non | rejeu borne, 0,5 |
| `ANG-R4` | anglais CM1 | non | mot pour mot, 0,25 |
| `ANG-R5` | anglais CM1 | non | mot pour mot, 0,25 |
| `ANG-R6` | anglais CM1 | non | mot pour mot, 0,25 |
| `CONS-1` | histoire-geographie CM1 | non | non levee, reliquat (section 8) |
| `CONS-4` | histoire-geographie CM1 | non | non chiffre, decision de grain de jeu |

---

## 14. La porte technique sur mon propre diff - les quatre jobs, nommes un par un

`CLAUDE.md`, amendement du 2026-09-08 : *"Un agent qui annonce la porte technique nomme les jobs
qu'il a lances, et ne deduit pas les autres."* Le job `chemins` est reste rouge un sprint entier
parce que quatre agents et le Scrum Master avaient annonce la porte verte sur la foi de `mvn verify`
seul. **Je lance les quatre sur mon diff avant de commiter**, et je les nomme.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| **`prefixes`** - prefixes de commit par agent | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s fd19d18f..HEAD` dans `verifier-prefixes.sh` | **vert** - *"Tous les commits portent un prefixe d'agent connu."* |
| **`chemins`** - chemins de poste de travail | `verifier-chemins-test.sh`, puis le balayage `git ls-files` dans `verifier-chemins.sh` | **vert** - *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| **`outils`** - juges purs des outils de poste | les trois motifs du job, **9 juges trouves et executes**, aucun motif vide | **vert** |
| **`build`** - build et tests (Java 21) | `mvn -B clean verify` sur la racine, reacteur complet, JDK 21.0.10 rapporte par la machine virtuelle | **vert - `BUILD SUCCESS`, 1 439 tests, 0 echec** (105 + 13 + 388 + 689 + 238 + 6, les six modules) |

**Mon diff ne porte aucun test neuf, et c'est correct** : il ne contient que quatre documents - ce
relevé, les deux fiches, le registre. **Aucun octet de contenu, de `domain`, d'`application`,
d'`adapters` ni de `bootstrap` n'est touche.** Le total de 1 439 est celui de la base `fd19d18f`,
inchange, et il confirme que mes ecrits n'ont rien casse.

**Deux resultats de ce build valent d'etre nommes parce qu'ils mordent sur ce que je viens
d'ecrire :**

- `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : **7 tests verts**. Mes dix allocations
  resolvent, aucune n'est en double, et le compte courant `557 / 95` correspond a la table.
  **`PAR-5` est tenue sur ce worktree** - avec la reserve d'assemblage que j'ai ecrite dans le
  registre lui-meme.
- `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` : **5 tests verts**, donc les tableaux
  de ce document et des deux fiches sont bien formes.

**Et le fait qui compte pour la lecture des deux refus** : `PaquetCm1HistoireGeographieTest` passe
ses **24 tests**, dont ceux que le lot a ajoutes pour `FER-1`, `FER-2`, `FER-3` et `CONS-1` sur les
quatre lignes. **La porte technique est verte sur `HG-CM1-02` et sur `HG-CM1-04`, et je les
refuse.** C'est la demonstration, sur ce sprint et sur mon terrain, de ce que le double Done veut
dire : **une CI verte ne dit rien de l'exactitude de ce qu'un enfant lit.**

**Un incident de mesure, que je nomme plutot que de le taire.** Mon premier `mvn -B clean verify` a echoue - non pas
sur un test, mais sur `maven-clean-plugin`, qui ne pouvait pas supprimer `recreo.jar` parce que
**l'application que j'avais lancee pour jouer le tenait ouvert**. J'ai arrete le processus **par son
identifiant** et relance : `BUILD SUCCESS`. Aucun test n'a echoue a aucun moment, et je le note pour
qu'un rouge de ce genre ne soit pas relu comme un rouge de contenu.

---

**Ce que ce document ne fait pas** : il n'ecrit pas la matrice, n'ecrit aucune story, ne repriorise
rien, ne fixe aucun perimetre de sprint, ne tranche aucun choix technique ni aucun decoupage de jeu,
et ne modifie aucun octet de contenu. **Un refus renvoie l'increment a `camille-po`, qui en fait du
backlog ; il ne bloque pas le sprint suivant.**
