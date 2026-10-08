# Arbitrage prealable - s55, instance A : francais CE1 (`FRA-CE1-14`, `FRA-CE1-12`), forme d'`ART-CP-10`, plafonds de `G7` a `G11`

`nadia-prof`, instance A, le 2026-10-08 (15:44, UTC+02:00, lu par `date`). Arbre lu : `0a795589` (tip de `round-two`,
branche `s55-nadia-a`).

**Audit sans jeu.** Aucun des deux paquets n'est branche (`grep -c "paquet: ce1-accord-sujet-verbe$"` et
`"paquet: ce1-classes-de-mots$"` sur `catalogue.yaml` rendent 0). Je decide sur la lecture des 24 items de
`ce1-accord-sujet-verbe.yaml`, des 24 de `ce1-classes-de-mots.yaml`, des en-tetes des 16 paquets de `G7` a `G11` et de leurs
cellules de matrice, contre mon arbitrage de s48 (sections 2, 5.3, 5.5, 5.8), celui de s54 (instance A, section 1.2), `PAR-2`,
`PAR-8`, `PAR-9`, `PAR-13`, `PAR-14`, `DEC-0115` et `ADR-0104`. **Ce document ne prononce aucun verdict, aucun Done, aucun
statut de matrice.** **Aucun identifiant de condition neuf n'est ecrit ici** : `K1` a `K5` (conditions avant branchement),
`W1` a `W4` (recommandations), `M1` a `M6` et `X1` a `X7` (ce que le dispositif montre et ne montre pas) sont des reperes de ce
fichier. Les lots sont nommes par leur ligne (`-14`, `-12`) pour ne pas confondre avec les stories `S55-BR-FRA-CE1-L1`, `-L2`.

Convention des blocs : ils portent l'espace insecable (U+00A0) devant `?` et `:` et a l'interieur des guillemets francais, comme les
paquets (`TYP-1`). Ils se collent a la place de la ligne de meme role de l'item cite.

## Resume

| Poste | Decision | Prix |
|---|---|---|
| Choix `-14`, `-12` (Q3) | **accepte**. `PAR-13` tenue : les deux portent des conditions cousines de conditions deja servies | - |
| `FRA-CE1-14`, `ce1-accord-sujet-verbe` | **deux conditions avant branchement** : `K1` dix-sept indices ; `K2` items 6 et 18. Consigne fixee (`K3`) | lot **1,0** |
| `FRA-CE1-12`, `ce1-classes-de-mots` | **deux conditions avant branchement** : `K4` prenoms des items 17 et 20 ; `K5` consigne. Une reserve annoncee (`W3`) | lot **1,0** |
| Paire `-12` / `-13` | separable ; `-12` d'abord est le bon ordre | - |
| Forme d'`ART-CP-10` (`ADR-0104`, points 2 a 5) | ecrite en section 3, **prononcee seulement si l'arts plastiques CP va entier a A** (`PAR-2`) | **0,25** |
| Audit du paquet illustre | **s56 au plus tot** | **2,5** |
| Plafonds de `G7` a `G11` (Q4) | **15 paquets sur 16 plafonnes `non couvert`** ; `cm1-lire-et-restituer` seul peut fermer, apres une mesure qui manque | **0,25** |
| **Total instance A** | `PAR-9` tenue | **3,5 / 4,0** (3,75 si l'issue (b) de 2.3) |

---

## 0. Deux corrections de mes propres constats, avant de m'en servir

### 0.1 `TRV-CE1-2` demande des familles que `ACV-CE1-2` ne nomme pas

`TRV-CE1-2` exige que *"chaque distracteur appartient a une famille nommee par la condition de sa ligne, et le fichier nomme la
famille a cote de l'item"*. `ACV-CE1-2` ne nomme aucune famille : il dit *"trois formes du present du meme verbe"*. Le paquet ne
nomme donc pas de familles, et ce n'est pas sa faute : c'est un trou de ma condition de s48. **Je n'en fais pas un motif.** Pour la
lecture ci-dessous, je nomme moi-meme les deux familles que le paquet sert : **(n)** le meme verbe a l'autre nombre de la troisieme
personne, **(p)** le meme verbe a une autre personne (*joues*, *ecoutons*).

### 0.2 L'en-tete de `ce1-accord-sujet-verbe` reprend ma formule etroite de s52

Ligne 10 : *"Aucun indice ne renvoie a l'ecoute : les formes en -e, -es, -ent se prononcent de meme."* C'est la formule que j'ai
corrigee a s54 (instance A, section 1.2). La barre est celle de 1.2 : **un indice qui donne un critere ne doit pas etre satisfait par
un distracteur de son item.** Le paquet tient la formule etroite et ne tient pas la barre (`K1`).

---

## 1. Q3 - `PAR-13` sur les sept conditions neuves, et le choix des deux paquets

### 1.1 Lecture

Je juge `PAR-13` au niveau de la matiere, comme a s51, s52 et s54 : **le francais CE1 n'est pas un terrain neuf**, quel que soit le
paquet que A prend. Et `PAR-13` tient a s55 de toute facon : B porte l'EMC CM1 (neuf lignes `couvert-valide`) et, au plus, une
correction d'arts plastiques CP, terrains dont les conditions ont rencontre du contenu.

**Ce qui reste a lire est le cousinage**, le critere qui m'a fait dire a s54 que `FUT` et `PCO` ne sont pas neuves. Les sept
conditions n'ont jamais rencontre de contenu ; elles ne sont pas toutes sans cousine :

| Conditions | Cousine servie | Ce qui est neuf | Lecture |
|---|---|---|---|
| `RCT`, `INF`, `DOC`, `PRN` (`-04`, `-06`, `-07`, `-05`) | `NAR` (`cp-texte-narratif`), `COMP` (`cm1-comprehension`) : texte support, question, trois propositions | `LON-CE1-3`, `-4` (aucun texte support n'est servi au CE1 : `grep -c texteSupport` ne compte sur les paquets CE1 branches que des lignes d'en-tete) ; la question de referent pour `PRN` | cousines |
| `FAM` (`-24`) | `SYC` (`ce1-synonymes-contraires`, valide a s52) : choisir un mot pour un mot | `FAM-CE1-3`, le sens d'un affixe | cousine |
| `OMP`, `RLP` (`-08`, `-09`) | **aucune que je sache nommer sans lire les paquets**, tous deux `DEGRADE` | propositions en phrases ordonnees (`OMP`) ; phrase fautive montree, explication qui redonne la forme juste (`TRV-CE1-5`, seconde clause) | **sans cousine** jusqu'a lecture |

**Ce que j'en tire pour la composition, sans amender `PAR-13`** : au plus un lot sans cousine (`-08` ou `-09`) par sprint sur A, et
jamais dans le sprint ou une autre instance audite le paquet illustre d'`ART-CP-10` (section 3.5), dont la forme n'a aucune cousine.

### 1.2 Verdict sur le choix

**Accepte : `-14` et `-12` sont les deux lots de A a s55.** Aucun ne porte l'une des sept conditions. `ACV-CE1-*` est cousine de
`PRS-CE1-*` (memes verbes, `ACV-CE1-2` renvoie a `PRS-CE1-1` ; meme enonce *"« verbe » : phrase a trou"*). `CLM-CE1-*` est cousine
de `NAT-*` (`cp-nature-mot`, valide le 2026-08-25 : mot cible entre guillemets, `partDesignee`, `ADR-0062`). `-14` precede `-09`, qui
renvoie a lui (`RLP-CE1-1`).

**Paire `-12` / `-13`.** Separable. La frontiere de `VSJ-CE1-1` (*"la, on nomme la classe d'un mot designe ; ici, on trouve un mot
dans la phrase"*) se juge sur le paquet de `-13`, contre `-12` deja servi : `-12` d'abord est le bon ordre. Les auditer ensemble
(O1 avec `-13`) donnerait une seule lecture de la frontiere ; section 2.2 dit pourquoi O1 ne tient pas a s55.

---

## 2. Q3 - les tarifs reels de l'instance A

### 2.1 Postes

| Poste | Prix | Motif |
|---|---|---|
| `FRA-CE1-14` | **1,0** | premier passage complet d'un paquet jamais audite, tarif de lot (s28, section 4.3) ; je suis la source des motifs. Les conditions de la section 4 le rendent plus court, pas moins cher |
| `FRA-CE1-12` | **1,0** | idem |
| Cet arbitrage, partie francais CE1 | **0,5** | 48 items lus, huit textes d'enfant ecrits, poses sur 23 lignes ; meme volume que s54 (51 items, 0,5) |
| Plafonds de `G7` a `G11` (section 6) | **0,25** | 16 en-tetes et 16 cellules lus, aucun item ; plus qu'une ligne de plafond (0,0 a s54, `Q6`), moins qu'un lot |
| Forme d'`ART-CP-10` (section 3) | **0,25** | quatre points routes par `ADR-0104`, deux textes d'enfant ecrits, regles de paires ; aucun paquet a lire |
| `S54-EXC-CATEGORIE` (`EXC-R15`) | **0,0 sur A** | `PAR-2` : l'EMC CP et l'EMC CM1 sont une matiere, B porte l'EMC CM1. Le texte est le mien, ecrit a s54 par A ; le rejeu est a B, **0,25** (`PAR-10`, mot pour mot) |
| `S54-ART-RES-R1` a `R3` | **0,0 sur A**, ou **0,25** dans l'issue (b) de 2.3 | `PAR-2`, voir 2.3 |
| Passe `PAR-4` | **0,0** : non due a s55 | celle de s54 a lu le depot servi apres les branchements de s54 ; s55 n'ajoute que quatre paquets. `D7` reste non cherchee |
| Marge de rejeu, allouee a aucun lot | **0,5** | paie **un** refus au premier passage de `-14` ou de `-12` |
| **Total** | **3,5 / 4,0** | `PAR-9` tenue. **Reste non alloue : 0,5** |

Lots : deux sur trois (`PAR-8`). `PAR-14` : sans objet, ni `-14` ni `-12` n'a ete audite ; `K1` touche 17 items sur 24 sans
changer le prix, le premier passage est complet de toute facon.

### 2.2 La reserve de A : ma lecture et mon prix, pas mon choix

Le planning comptait 1,0 de reserve sur un arbitrage a 0,5. Avec mes prix, elle est de **0,5** (0,25 dans l'issue (b)).

| Emploi | Ce que mes prix en disent |
|---|---|
| **O1** : un troisieme lot (`-13`, 1,0) | **ne tient pas sous `PAR-9`** : 3,5 + 1,0 = 4,5 sur 4,0. Meme sans la section 3 : 4,25. O1 a s55 demande un ecart ecrit a l'utilisatrice ; je ne le demande pas |
| **O3** : l'arbitrage sur `G7` a `G11` | **fait par ce document, 0,25** (section 6), et non 1,0. Ce qu'il apprend : le rang 3 de l'ordre achete au plus une ligne |
| **O2** : rien | 0,5 garde : un rejeu mot pour mot (0,25) en plus de la marge, ou le choix des oeuvres du paquet illustre (0,25, section 3.5) |

### 2.3 `PAR-2` et l'arts plastiques CP : deux issues, que je ne tranche pas

`PAR-2` : une matiere et un niveau ne sont jamais partages dans un sprint. L'instance B, dans son arbitrage en cours d'ecriture
(`ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md`, section 4.1, lu dans son worktree, non fusionne), prend `S54-ART-RES-R1` a `R3` et
ecrit que la forme d'`ART-CP-10` ne peut alors pas aller a A. **Je suis d'accord avec sa lecture** : le critere de `ART-RES` (ni
titre ni consigne du jeu des familles ne contiennent `reconna` ou `rapproch`) est exactement la frontiere entre ce paquet et
`ART-CP-10` ; tenue par deux instances, elle est tenue par moitie.

| Issue | A | B | Effet |
|---|---|---|---|
| (a) `ART-RES` sur B, forme a s56 | 3,5 (la section 3 est ecrite, non prononcee) | 4,0 | T3 de la mecanique attend mes mots : il ne se termine pas a s55. L'enfant ne voit rien de plus a s55 dans les deux cas (aucun paquet) |
| (b) arts plastiques CP entier sur A : `ART-RES` (0,25, textes de la section 4.2 de B, que je rejoue mot pour mot) et la forme | **3,75** | 3,75 | T3 peut se terminer a s55. Demande un amendement de `DEC-0115` par `camille-po` avant l'ouverture du worktree de `lea-dev` |

Sans amendement, c'est (a), et la section 3 est relue a s56 par l'instance qui porte l'arts plastiques CP, sans prix neuf si elle ne
change pas.

---

## 3. Forme d'`ART-CP-10` (`S55-ART-CP-10-MECANIQUE`, `ADR-0104` points 2 a 5)

Prononcee dans l'issue (b) de 2.3 seulement. Point 1 (`C5` clause 1) : repondu le 2026-10-06
(`ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md`, section 4.2), inchange.

**Ce que la premisse de l'adulte change ici : rien.** La voix n'est pas la competence de cette ligne ; l'arbitrage du 2026-09-01
borne la premisse aux lignes ou elle l'est. Aucun texte n'est adresse a l'adulte, et la consigne reste lisible par un enfant de CP
seul.

### 3.1 Point 2 - la forme "propositions en image" sert la ligne

**Oui, pour la moitie "rapprocher deux oeuvres montrees ensemble".** Le geste de reponse est le rapprochement : l'enfant designe une
oeuvre, pas un mot. La mention de s50 (Q5) reste due si la ligne ferme : *"moitie 'rapprocher deux oeuvres montrees ensemble' seule ;
la moitie 'reconnaitre des oeuvres deja rencontrees' reste non couverte"*. Ce que la forme vaut sur des paires reelles se juge au
paquet, en jouant.

### 3.2 Ce que le dispositif doit montrer

| | Exigence |
|---|---|
| `M1` | La reference et toutes les propositions **sur le meme ecran, sans defilement, avant tout geste**, chaque proposition **a la meme taille rendue** que les autres (une image plus grande serait un indice) |
| `M2` | La reference **visiblement hors du choix** : pas un bouton, separee des propositions. La forme est a `ines-design` |
| `M3` | Apres une erreur : l'etat d'echec existant sur l'oeuvre choisie, et l'indice du contenu. La reference reste visible |
| `M4` | A la revelation : la bonne oeuvre designee par la marque d'`ines-design`, **la reference toujours visible a cote**, et ce texte du gabarit, a la place de *"Appuie sur la bonne réponse :"* et de la reponse en texte |
| `M5` | Le nom accessible de chaque bouton-image, texte du gabarit, identique sur tous les items |
| `M6` | L'`explication` de l'item, quand le contenu en porte une, a la revelation seulement (comportement actuel du gabarit) |

Texte de `M4`, 8 mots, deux phrases :

```text
C'est cette œuvre qui va avec. Appuie dessus.
```

Texte de `M5` :

```text
Choisir cette œuvre
```

`ines-design` peut ajouter un rang dans le nom accessible, jamais a l'ecran ni dans un attribut `title` (une bulle est un texte lu).

### 3.3 Ce que le dispositif ne doit pas montrer

| | Interdit |
|---|---|
| `X1` | Un identifiant `oeuvre-`, un titre, un artiste, une date ou un nom de sujet, dans un noeud de texte, un `alt`, un nom accessible ou un `title`, sur aucun ecran (manche, echec, revelation, fin) - `ADR-0104` 4.1, `CON-5` |
| `X2` | Un numero, une lettre ou une legende sur, sous ou a cote d'une image proposee. Un enfant qui presse "2" lit une etiquette (objection faite au quadrillage) |
| `X3` | Un texte, un pictogramme ou un aplat ajoute dans une boite d'image (`C5` clause 1, lecture du 2026-10-06) |
| `X4` | Une rangee melangee texte et image (`ADR-0104`, invariant 1) |
| `X5` | La reference parmi les propositions, **ni un recadrage, un detail ou un miroir de la reference** : la machine voit l'identite d'identifiant (invariant 3), pas l'identite d'image. **Je suis la seule garde du second cas** |
| `X6` | Une difference de presentation entre propositions avant le geste (cadre, ombre, survol different) |
| `X7` | **Point 4 - l'ecran de fin : pas d'oeuvre.** `reponseAttendueVisible` reste `false` (`ADR-0104` 4.5). Une oeuvre seule, sans sa reference, n'apprend rien d'un rapprochement ; le moment ou l'enfant apprend est `M4`, ou les deux sont visibles |

### 3.4 Point 5 - paires, distracteurs, nombre de propositions, taille

Regles pour le paquet (T5), qui se jugent sur lui :

1. **Paires** : les trois de `CHOIX-oeuvres-candidates-T5.md` (section 5). Chambre et champ de ble sortent si la touche ne se voit
   pas a la taille rendue (s50, Q1).
2. **Le sujet ne suffit jamais.** Aucun distracteur ne partage la propriete rapprochee. Si la bonne oeuvre partage le sujet de la
   reference (vague et averse : de l'eau ; luth et aiguiere : une femme pres d'une fenetre), **au moins un distracteur partage ce
   sujet sans la propriete**. Sans cela, l'enfant rapproche par le sujet et refait `ART-CP-09` (s50, Q1, motif 2). **Consequence de
   corpus** : ces distracteurs doivent exister en reproductions de provenance prouvee ; c'est une question a `theo-archi` (T4), pas
   un fait que je connais.
3. **Le format ne designe pas la bonne oeuvre** : aucun item ou la bonne oeuvre est la seule a partager le format (portrait ou
   paysage) de la reference.
4. **Trois propositions**, une juste. A deux, l'enfant trouve une fois sur deux sans regarder. Deux seulement si `ines-design` mesure
   que trois images et la reference ne tiennent pas au-dessus du pli a une taille ou la propriete se voit ; je rejuge alors.
5. **Indice** : il dit ou regarder (la touche, l'eau, la lumiere), jamais le sujet de la bonne oeuvre. **Explication** : elle nomme ce
   que les deux oeuvres partagent, en mots de CP, sans titre ni artiste.
6. **Taille** : visibilite de chaque propriete a 176 px CSS (`ADR-0104` 4.3) : **je ne la juge qu'a l'ecran, a l'audit.**

### 3.5 Prix et place

| Poste | Prix | Place, ma lecture |
|---|---|---|
| Cet arbitrage de forme | **0,25** | s55, issue (b) ; sinon relu a s56 |
| Choix des oeuvres distractrices sur la liste du corpus (regles 2 et 3) | **0,25** | avant l'ecriture du paquet : s55 sur le reste de A dans l'issue (b), ou s56 |
| **Audit du paquet illustre** | **2,5** : lot d'arts plastiques CP dont je ne suis pas la source (1,5), illustre (+0,5, `REC-1`), forme dont les conditions n'ont jamais rencontre de contenu (+0,5, `PAR-11`) | **s56 au plus tot.** A s55 : le paquet n'existe pas et n'est pas finance (`DEC-0115` 5.3), et 2,5 ne tient sur aucune instance (A : 6,0, au-dessus du plafond dur de 5,0 ; B : 6,5) |

**Ce que l'audit de s56 impose a la composition de s56** : l'instance qui le porte garde 4,0 - 0,5 (arbitrage) - 0,5 (marge) - 2,5 =
**0,5**, donc aucun autre lot ; et la forme n'ayant aucune cousine, c'est le terrain neuf de s56 au sens de `PAR-13` (section 1.1).

---

## 4. `FRA-CE1-14`, `ce1-accord-sujet-verbe` - lecture des 24 items

**Indices du paquet**, qui tournent : **A** *"Regarde le sujet : un seul ou plusieurs ?"* ; **B** *"Demande-toi qui agit, un seul ou
plusieurs."* ; **C** *"Le sujet se trouve juste avant le trou : combien en voit-on ?"* ; **D** *"Cherche qui agit, puis
compte-les."* Familles (0.1) : (n) autre nombre, (p) autre personne.

| n | Phrase | Bonne reponse | Distracteurs | Indice | Lecture |
|---|---|---|---|---|---|
| 1 | *jouer* : le chat ___ dans le jardin | joue | jouent (n), joues (p) | A | juste ; **`K1`** |
| 2 | *ecouter* : les eleves ___ la maitresse | ecoutent | ecoute (n), ecoutons (p) | B | juste ; **`K1`** |
| 3 | *venir* : ils ___ de l'ecole | viennent | vient (n), viens (p) | C | juste |
| 4 | *etre* : la maitresse ___ dans la classe | est | sont (n), es (p) | D | juste ; **`K1`** (et *"compte-les"* pour un seul) |
| 5 | *etre* : les chats ___ sur le toit | sont | est (n), es (p) | A | juste |
| 6 | *aller* : elle ___ au marche | va | vont (n), vas (p) | B | juste ; **`K1`**, **`K2`** |
| 7 | *avoir* : Leo ___ un ballon rouge | a | ont (n), as (p) | C | juste ; **`K1`** |
| 8 | *avoir* : les ecoliers ___ un nouveau jeu | ont | a (n), as (p) | D | juste |
| 9 | *parler* : elles ___ doucement | parlent | parle (n), parlons (p) | A | juste ; **`K1`** |
| 10 | *aller* : Mia ___ a la piscine | va | vont (n), vas (p) | B | juste ; **`K1`** |
| 11 | *aller* : les oiseaux ___ vers le sud | vont | va (n), vas (p) | C | juste |
| 12 | *etre* : il ___ dans la cour | est | sont (n), es (p) | D | juste ; **`K1`** ; `W1` |
| 13 | *faire* : la boulangere ___ du pain | fait | font (n), fais (p) | A | juste ; **`K1`** |
| 14 | *faire* : les ouvriers ___ du bruit | font | fait (n), fais (p) | B | juste |
| 15 | *avoir* : elles ___ une bonne idee | ont | a (n), as (p) | C | juste |
| 16 | *dessiner* : Ines ___ un arbre | dessine | dessinent (n), dessines (p) | D | juste ; **`K1`** |
| 17 | *dire* : les voisins ___ bonjour | disent | dit (n), dis (p) | A | juste |
| 18 | *faire* : elle ___ un gateau | fait | font (n), fais (p) | B | juste ; **`K1`**, **`K2`** |
| 19 | *venir* : le facteur ___ a pied | vient | viennent (n), viens (p) | C | juste ; **`K1`** |
| 20 | *travailler* : les medecins ___ a l'hopital | travaillent | travaille (n), travaillons (p) | D | juste ; **`K1`** |
| 21 | *dessiner* : ils ___ des etoiles | dessinent | dessine (n), dessinons (p) | A | juste ; **`K1`** |
| 22 | *parler* : la directrice ___ aux eleves | parle | parlent (n), parles (p) | B | juste ; **`K1`** |
| 23 | *laver* : les enfants ___ les verres | lavent | lave (n), lavons (p) | C | juste ; **`K1`** |
| 24 | *regarder* : Karim ___ la lune | regarde | regardent (n), regardes (p) | D | juste ; **`K1`** |

**Conditions tenues, comptees** : `ACV-CE1-1` (sujet immediatement avant le verbe sur les 24 ; nominal 17 sur 24, au-dessus des deux
tiers ; singulier 12, pluriel 12 ; aucun sujet coordonne) ; `ACV-CE1-2` (trois formes du present du meme verbe ; treize verbes, tous
de `PRS-CE1-1`, aucun en `-ger`, `-cer`, `-eler`, `-eter`, `-yer`) ; `ACV-CE1-3` (sujet surtout un nom) ; `TRV-CE1-1` ; `TRV-CE1-2`
(trois propositions distinctes, une seule acceptable ; bonne reponse strictement la plus longue sur 6 items : 3, 5, 8, 11, 15, 17) ;
`TRV-CE1-3`, `-4` ; `TRV-CE1-6` (aucun *tu* ; prenoms Leo, Mia, Ines, Karim, deux et deux) ; `TRV-CE1-7` (24) ; `LON-CE1-1`, `-2`, `-5`.
**Les 24 formes sont justes**, accents relus dans le fichier.

### `K1` - dix-sept indices remplaces (bloquante, `TRV-CE1-5`, barre de s54 section 1.2)

**Le defaut.** Les quatre indices donnent le meme critere : le nombre (*un seul ou plusieurs*, *combien*, *compte-les*). Chaque item
a un distracteur de la famille (p), qui a **le meme nombre** que la bonne reponse sur 17 items : *joues*, *es*, *vas*, *as*, *fais*,
*viens*, *dessines*, *parles*, *regardes* sont au singulier, comme le sujet singulier ; *ecoutons*, *parlons*, *travaillons*,
*dessinons*, *lavons* sont au pluriel, comme le sujet pluriel. **L'enfant qui a ecrit "le chat joues" lit "un seul ou plusieurs ?",
repond "un seul", et se trouve confirme.** Sur les 7 items pluriels de verbes irreguliers (3, 5, 8, 11, 14, 15, 17), les deux
distracteurs sont au singulier : le critere departage, l'indice reste. Et *"compte-les"* dit a l'enfant qu'ils sont plusieurs sur
les items 4, 12, 16, 24, ou le sujet est seul : c'est faux sur l'item.

Deux indices neufs. Chacun donne une methode, n'est satisfait par aucune proposition et ne donne pas la reponse ; *sujet* et *verbe*
sont admis (`TRV-CE1-4`).

```yaml
    indice: "Remplace le sujet par « il », « elle », « ils » ou « elles », puis relis."
```

Indice de substitution, 11 mots, pour un sujet nominal. Il ramene l'item a ce que `FRA-CE1-17` vient d'apprendre (*il joue*,
*ils ecoutent*) : *"il joues"* et *"ils ecoutons"* ne se disent pas comme des formes de *il* et *ils*.

```yaml
    indice: "Lis le sujet avec chaque verbe proposé : lequel va avec lui ?"
```

Indice de relecture, 11 mots, pour un sujet pronom. Il dit ou regarder, sans critere.

**Items et indice a poser, rien d'autre ne change dans ces items** (sauf 6 et 18, `K2`) :

- substitution : items **1, 2, 4, 7, 10, 13, 16, 19, 20, 22, 23, 24** ;
- relecture : items **6, 9, 12, 18, 21**.

Restent tels quels : items 3, 5, 8, 11, 14, 15, 17. `TRV-CE1-5` (*"pas identique sur tout le paquet"*) reste tenue. **Dans le meme
commit**, la ligne 10 de l'en-tete devient :

```yaml
# Aucun indice ne donne un critere qu'un distracteur de son item satisfait : le nombre seul (un ou plusieurs) est
# satisfait par la forme d'une autre personne (joues, ecoutons). Les formes en -e, -es, -ent se prononcent de meme.
```

### `K2` - items 6 et 18 (bloquante, `TRV-CE1-6`)

Les trois activites de nourriture et de courses du paquet vont toutes du cote feminin : *elle va au marche* (6), *la boulangere fait
du pain* (13), *elle fait un gateau* (18) ; *les ouvriers font du bruit* et *le facteur* sont du cote masculin. C'est la barre du
refus de `FRA-CE1-17` : *"il va tout entier du meme cote"*. Seule la ligne `enonce` change ; bonne reponse, distracteurs inchanges
(meme personne, meme nombre) :

```yaml
  - enonce: "« aller » : il ___ au marché."
```

(item 6)

```yaml
  - enonce: "« faire » : il ___ un gâteau."
```

(item 18)

### `K3` - la consigne du jeu

Le paquet porte la question par la consigne (en-tete, ligne 14). Celle du present CE1, mot pour mot, pour qu'un enfant qui enchaine
`-17` et `-14` lise la meme question :

```yaml
    consigne: "Lis bien, puis choisis la bonne forme du verbe."
```

Le titre est libre ; je propose *"Un ou plusieurs ?"*, sans en faire une condition.

### `W1`, `W2` - recommandations, non bloquantes

- **`W1`** : item 12, *il* devient *elle* (`"« être » : elle ___ dans la cour."`). Sans lui, apres `K2`, les trois sujets
  pronoms singuliers sont *il*. Aucune barre ne l'exige.
- **`W2`, a ne pas faire** : remplacer *ecoutons* par *ecoutes* sur les items 2, 9, 20, 21, 23 servirait mieux l'erreur reelle
  (*"les enfants joues"*), mais la bonne reponse deviendrait strictement la plus longue sur 11 items sur 24, tous pluriels : la
  longueur designerait la reponse (`TRV-CE1-2`). Le choix de `marc-dev` se defend ; je le dis pour qu'on ne le "corrige" pas.

**A verifier en jouant, pas une barre** : 15 items sur 24 portent un verbe irregulier dont le pluriel s'entend (*va* / *vont*) ; sur
les 7 items pluriels de ces verbes, l'enfant peut repondre a l'oreille sans lire le sujet. `ACV-CE1-2` ne fixe aucune proportion de
verbes du premier groupe : **je ne deplace pas ma barre**. Si le jeu montre que la marque muette *-ent* est trop rarement tiree
(environ 4 manches sur 10), j'ouvrirai une reserve neuve et datee.

---

## 5. `FRA-CE1-12`, `ce1-classes-de-mots` - lecture des 24 items

**Indices**, qui tournent : *"Cherche a quoi sert ce mot dans la phrase."*, *"Relis la phrase en pensant au mot entre guillemets."*,
*"Compare ce mot aux autres mots de la phrase."*, *"Demande-toi ce que ce mot fait dans la phrase."* Aucun ne donne un critere ;
aucun ne departage (`W3`).

| n | Phrase (mot cible) | Bonne reponse | Distracteurs | Lecture |
|---|---|---|---|---|
| 1 | Le petit (chat) dort sur le tapis | nom commun | verbe, adjectif | juste |
| 2 | Le chat (dort) sur le lit | verbe | nom commun, adjectif | juste |
| 3 | Mia a un (joli) chapeau | adjectif | nom commun, determinant | juste |
| 4 | Le chien de (Lea) court vite | nom propre | nom commun, adjectif | juste |
| 5 | Tom mange (un) gateau | determinant | nom commun, verbe | juste |
| 6 | Ines porte un joli (sac) | nom commun | nom propre, adjectif | juste ; `W4` |
| 7 | Les enfants (jouent) dans la cour | verbe | nom commun, determinant | juste |
| 8 | Le (grand) arbre perd ses feuilles | adjectif | nom commun, verbe | juste |
| 9 | Ce matin, (Mia) joue au ballon | nom propre | verbe, nom commun | juste |
| 10 | Mia ouvre (la) porte | determinant | nom propre, adjectif | juste |
| 11 | Tom mange une grosse (pomme) | nom commun | verbe, determinant | juste |
| 12 | Elle (mange) une pomme rouge | verbe | adjectif, nom propre | juste |
| 13 | Le ballon (rond) roule vite | adjectif | verbe, nom commun | juste ; *un rond* existe, le contexte departage |
| 14 | Le facteur salue (Noe) devant l'ecole | nom propre | determinant, nom commun | juste |
| 15 | Les eleves lisent (des) livres | determinant | nom commun, verbe | juste |
| 16 | Le (soleil) brille dans le ciel | nom commun | adjectif, nom propre | juste |
| 17 | Tom (range) ses crayons | verbe | nom commun, determinant | juste ; **`K4`** |
| 18 | L'eau est (froide) | adjectif | nom commun, verbe | juste ; attribut, pas *"apres le nom"* comme le dit le commentaire |
| 19 | Le bus de (Karim) arrive | nom propre | nom commun, verbe | juste |
| 20 | Karim aime (cette) histoire | determinant | adjectif, nom commun | juste ; **`K4`** |
| 21 | La maitresse ouvre la (fenetre) | nom commun | nom propre, verbe | juste |
| 22 | Le vent (souffle) tres fort | verbe | nom commun, adjectif | juste ; *le souffle* existe, le contexte departage |
| 23 | Karim a un chat (noir) | adjectif | nom propre, nom commun | juste |
| 24 | Noe range (ses) crayons | determinant | nom propre, verbe | juste |

**Conditions tenues, comptees** : `CLM-CE1-1` (mot cible designe, `partDesignee` sur les 24 ; nom commun 5, nom propre 4, verbe 5,
determinant 5, adjectif 5 ; nom commun et verbe 10, sous la moitie) ; `CLM-CE1-2` (aucun mot cible en tete ; aucun pronom, adverbe,
preposition ni conjonction designe) ; `CLM-CE1-3` (trois noms communs sur cinq apres un adjectif ; adjectif avant le nom 2 fois sur 5,
apres le nom 2 fois sur 5 sans compter l'item 18, au-dessus du tiers dans les deux cas) ; `TRV-CE1-1` a `-4` (bonne reponse
strictement la plus longue sur 6 items : 1, 5, 10, 15, 20, 24) ; `TRV-CE1-7` ; `LON-CE1-1`, `-2`, `-5`. **Les 24 classes sont justes.**
Le commentaire de l'item 18 est faux et sans effet sur le compte : il est au developpeur, pas une condition.

### `K4` - items 17 et 20 (bloquante, `TRV-CE1-6`)

Prenoms, comptes en occurrences comme sur les paquets de CE1 servis (`FICHE-bandit-manchot-homophones-ce1.md`, 7 et 6) : feminins
**5** (Mia 3, Lea, Ines), masculins **8** (Tom 3, Karim 3, Noe 2). L'ecart est de 3, la barre est *"a un pres"*. Deux prenoms
masculins deviennent feminins, sur des items ou le prenom n'est pas le mot cible ; seule la ligne `enonce` change :

```yaml
  - enonce: "Fatou « range » ses crayons."
```

(item 17)

```yaml
  - enonce: "Lina aime « cette » histoire."
```

(item 20). Resultat : feminins 7, masculins 6 ; cinq prenoms feminins et trois masculins distincts, d'origines variees.

### `K5` - la consigne du jeu

Le paquet porte la question par la consigne (en-tete, ligne 11) ; l'entree n'existe pas encore. Texte a poser, 11 mots, dans le
registre des jeux de CE1 (*"Lis bien, puis..."*) et avec la formule *"le mot entre guillemets"* que l'enfant a lue au CP :

```yaml
    consigne: "Lis bien, puis dis quelle sorte de mot est entre guillemets."
```

*Sorte* et non *classe* : au CE1, *la classe* est d'abord la salle. Le titre est libre ; je propose *"Quelle sorte de mot ?"*.

### `W3`, `W4` - recommandation et reserve annoncee, non bloquantes

- **`W3`, reserve que j'ecrirai a l'audit si rien ne change** : les quatre indices disent ou regarder sans rien departager, sur les
  24 items. Ce n'est pas un motif de refus (barre de s54, 1.2 : l'etage insuffisant), c'est la reserve de `ALP-CE1-R1`. **Propriete
  attendue, si le developpeur veut la lever dans le meme commit** : un indice par item qui donne un test (*"hier"* pour le verbe, la
  majuscule pour le nom propre, *un* / *une* / *des* a sa place pour le determinant), **qu'aucun distracteur de son item ne passe**.
  Je rejuge alors chaque indice contre ses distracteurs ; je ne fournis pas les 24 textes ici.
- **`W4`** : item 6, *joli* devient *gros* (`"Inès porte un gros « sac »."`). *Joli* et un accessoire vont aux deux
  filles des items 3 et 6. L'apparence n'est pas nommee par `TRV-CE1-6` (role, activite, emotion) : je ne l'exige pas.

---

## 6. Q4 - plafonds de `G7` a `G11`, sans jouer

**Methode** : celle de mon plafond d'`ART-CP-10` a s54 (`Q6`) - en-tete du paquet, forme, cellule de matrice, conditions deja
ecrites ; **aucun item lu**. Un plafond est une borne haute : la qualite des items n'y change rien, et un plafond `non couvert` ne
dit rien d'un futur paquet d'une autre forme. Comptes relances sur `0a795589` : 16 paquets, 216 items, aucune entree de catalogue.

| Paquet | Ligne | Plafond | Motif | Ce qui manque pour plus |
|---|---|---|---|---|
| `cm1-anglais-lire-texte` | `LVE-CM1-05` | `non couvert` | ma condition du 2026-09-04 : un second paquet lexical avant que la ligne s'ouvre ; l'en-tete dit qu'il ne l'est pas | un second paquet lexical valide |
| `cm1-anglais-ecrire-mots` | `LVE-CM1-06` | `non couvert` | au choix, l'enfant reconnait le mot, il ne l'ecrit pas : c'est `LVE-CM1-04` (s45, 5.1) | une saisie, refusee (motif `R6` de s45) |
| `cm1-arts-decrire-une-oeuvre-lexique` | `ART-CM1-08` | `non couvert` | sans image, le sens des mots du lexique, pas la description d'une oeuvre vue (`DEC-0075`) | une reproduction par item |
| `cm1-hda-decrire-lexique` | `HDA-CM1-01` | `non couvert` | idem ; un item qui decrirait l'oeuvre est une contrefacon (`ART-4`, `CON-1`) | idem |
| `cm1-hda-caracteristiques-techniques` | `HDA-CM1-02` | `non couvert` | la ligne dit *"d'une oeuvre observee"* ; le paquet definit des techniques | idem |
| `cm1-hda-materiaux-mise-en-forme` | `HDA-CM1-03` | `non couvert` | materiau dans l'abstrait, pas celui d'une oeuvre regardee | idem |
| `cm1-hda-epoque-civilisation` | `HDA-CM1-04` | `non couvert` | classification par des mots, pas observation (en-tete) | idem |
| `cm1-hda-reperes-patrimoine` | `HDA-CM1-06` | `non couvert` | identification par une phrase ; image requise sur la moitie identification | idem, et la frise (palier 2) pour l'autre moitie |
| `cm1-hda-musee-reperes-regles` | `HDA-CM1-09` | `non couvert` | se reperer suppose un plan (palier 2) ; le quiz de comportement est refuse d'avance (matrice) | un plan de musee en designation |
| `cm1-histoire-geographie-carte-et-planisphere` | `HG-CM1-09` | `non couvert` | un quiz de noms ne sert pas la competence ; le palier 2 existe, le materiau non | un dessin de carte de France et un planisphere |
| `cm1-histoire-geographie-echelles-du-lieu-de-vie` | `HG-CM1-10` | `non couvert` | meme materiau manquant (matrice) | idem |
| `cp-qlm-situer-la-france` | `QLM-CP-12` | `non couvert` | ligne de designation ; aucune carte ni globe dessines | un dessin de carte ou de globe |
| `cp-qlm-vocabulaire-de-position` | `QLM-CP-10` | `non couvert` | *"gauche / droite sans image est la difficulte classique du CP"* (s29) ; la ligne exige le palier 1 illustre | un paquet illustre (palier 1, `ADR-0073`), lot a 2,0 |
| `cp-qlm-modes-de-vie` | `QLM-CP-16` | `non couvert` | *comparer* n'est servi que par l'item 12 sur 12 (en-tete) ; les onze autres expliquent, c'est la competence voisine | un paquet ou comparer est la tache de la plupart des items, sous `BIA-1`, `BIA-2` |
| `cp-assemblage-syllabique` | `FRA-CP-05` | `non couvert` | **lecture de la forme** : la bonne proposition reproduit les lettres des syllabes dans le meme ordre, les distracteurs en different par l'ordre ou une lettre ; l'enfant compare des lettres, il ne dechiffre pas. Ce n'est pas la forme que je demandais a s26 (4.4) | une forme ou la proposition ne reproduit pas les lettres du stimulus ; je n'en connais pas sans image ni son |
| `cm1-lire-et-restituer` | `FRA-CM1-01` | **`couvert-valide`**, avec la mention *"restituer l'essentiel seul ; la longueur croissante n'est pas servie, le tirage d'une manche etant sans ordre"* | `LIS-1` a `LIS-10` (s25, 9.1) ; forme acceptee | **la mesure de pli d'`ines-design`**, prealable de `LIS-2`, non faite (en-tete) ; puis un lot a 1,0 |

**Ce que cela dit au rang 3 de l'ordre** : il achete **au plus une ligne** (`FRA-CM1-01`), apres une mesure qui n'est pas la mienne.
Brancher l'un des quinze autres a un plafond `non couvert` est la situation de `DEC-0009` : je ne recommande aucun lot sur eux.

---

## 7. Ce que je ne peux pas decider sans le jeu a l'ecran

1. Que l'indice de `K1` s'affiche bien apres la premiere erreur, sous l'enonce, et que l'enfant lise *"il"* dans *« il »*.
2. Le tirage de `-14` : combien de manches sur dix portent la marque muette *-ent* (section 4, a verifier en jouant).
3. Le rendu du mot cible de `-12` : guillemets et renfort de `partDesignee` ensemble, sur les mots courts (*un*, *la*, *des*).
4. Pour `ART-CP-10` : tout ce que la section 3.4 renvoie au paquet, et la taille de 176 px.

## 8. Ce dont je reste la seule garde

Aucun garde-fou ne lit : qu'un indice donne un critere satisfait par un distracteur (`K1`) ; qu'il dise une chose fausse sur l'item
(*"compte-les"*) ; la repartition des activites par genre (`K2`) ; l'equilibre des prenoms en occurrences (`K4`) ; qu'une consigne
pose la question que l'enonce ne pose pas (`K3`, `K5`) ; un recadrage de la reference parmi les propositions (`X5`) ; qu'un distracteur
partage le sujet sans la propriete (3.4, regle 2). **Je les tiens en lisant et en jouant ; l'equipe ne doit pas les croire couverts
par la CI.** Les comptes de `ACV-CE1-1` et `CLM-CE1-1` peuvent etre tenus par les tests de paquet.

## 9. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | `K1`, `K2`, `K3` (et `W1` s'il le prend) dans le commit de `S55-BR-FRA-CE1-L1` ; `K4`, `K5` (et `W3`, `W4` s'il les prend) dans celui de `S55-BR-FRA-CE1-L2`. Rapport (`C7`) : `TRV-CE1-5` se lit avec la barre de s54, 1.2 |
| `camille-po` | 2.2 et 2.3 : emploi du reste de A et issue (a) ou (b), par amendement de `DEC-0115` ; section 6 pour le rang 3 de l'ordre ; les fiches de `-14` et `-12` portent `K1` a `K5` comme criteres |
| `sofia-sm` | capacite de A a 3,5 (3,75 en (b)) au planning ; la composition de s56 de 3.5 (instance de l'audit d'`ART-CP-10` sans autre lot ; aucun lot sans cousine ailleurs) |
| `theo-archi` | 3.4 regle 2 : existe-t-il au corpus, ou en restauration (T4), des reproductions qui partagent un sujet sans la propriete ? ; `X5` : une identite d'image est-elle mordable |
| `ines-design` | `M1`, `M2`, `M4` (marque de revelation), 3.4 regle 4 (trois images et la reference sous le pli) ; mesure de pli de `LIS-2` pour `FRA-CM1-01` |
| instance B | 2.3 : je lis comme elle ; ses textes de `ART-RES` (section 4.2) sont ceux que je rejouerais en (b) |

## 10. Ce que ce document ne fait pas

- Il ne prononce aucun Done, aucun verdict, et ne touche ni une fiche, ni la matrice, ni le registre d'identifiants, ni un YAML, ni le
  catalogue. Les textes des sections 3 a 5 sont a appliquer par le developpeur, puis rejuges par moi en jouant.
- Il ne rouvre ni `ACV-CE1-1` a `-3`, ni `CLM-CE1-1` a `-3`, ni `TRV-CE1-1` a `-7` : `K1` a `K5` appliquent des barres deja ecrites.
  `W1` a `W4` ne seront pas des motifs de refus.
- Il ne tranche ni O1, O2, O3, ni l'issue (a) ou (b), ni le financement du paquet illustre. Il ne traite ni `Q5`, ni `Q6`, ni la
  verification `A10`, ni le plafond de `G3`.
- Il ne cree aucun identifiant de condition ou de reserve.

**Signe** : `nadia-prof`, s55, instance A, 2026-10-08.
