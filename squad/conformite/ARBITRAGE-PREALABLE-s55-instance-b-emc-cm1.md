# Arbitrage prealable - s55, instance B : EMC CM1 et reserves de jeux servis

`nadia-prof`, instance B, le 2026-10-08 (15:35, UTC+02:00, lu par `date`). Arbre : branche `s55-nadia-b`, issue de `round-two`
a `0a795589`. Worktree et scratchpad propres a l'instance B.

**Audit sans jeu.** Aucun des deux paquets d'EMC CM1 n'a d'entree de catalogue ; les deux jeux servis (`exclusion-cp`,
`familles-d-oeuvres-cp`) seront rejoues apres correction, pas avant. Je decide sur la lecture des deux fichiers de paquet, de
`cp-arts-oeuvres-familles.yaml`, de `cp-emc-devise.yaml` (`CYC-1`), de mes fiches, du planning de s55, de `DEC-0115` et de mes
arbitrages de s53 et de s54 (instance B). **Ce document ne prononce aucun Done, aucun verdict, aucun statut de matrice. Il ne cree
aucun identifiant de condition ni de reserve** : je ne cite que des identifiants deja alloues.

Convention des blocs YAML, celle de mes arbitrages de s53 et s54 : espace insecable (U+00A0) devant `?` (`TYP-1`). Un bloc est un
item complet, a coller a la place de l'item de meme numero.

## Resume

| Code | Poste | Decision | Tarif reel |
|---|---|---|---|
| D1 | `S55-BR-EMC-CM1-03` (`cm1-emc-respect-differences`) | items 4, 7, 8 a corriger avant branchement, section 2 | **1,0** |
| D2 | `S55-BR-EMC-CM1-06` (`cm1-emc-principes-republique`) | items 6 et 8 a corriger avant branchement, section 3 | **1,5**, pas 1,0 |
| D3 | `S54-ART-RES-R1` a `R3` (Q5) | **instance B**, textes exacts section 4 | **0,25** mot pour mot ; 0,5 si reecrit |
| D4 | `S54-EXC-CATEGORIE` (`EXC-R15`) | **instance B** (`PAR-2`), barre inchangee | **0,25** mot pour mot |
| D5 | Cet arbitrage | - | **0,5** |
| D6 | Q6, Q7 | non adressees a B | - |

**Instance B : 3,5 alloues + 0,5 de marge = 4,0 sur 4,0** (`PAR-9`), 0,0 non alloue. Le planning ecrit 3,75 : section 5.

---

## 1. Trois constats a corriger avant de m'en servir

### 1.1 P9 de s53 n'a pas accepte `cm1-emc-respect-differences` "sans changement" - deuxieme fois

Le planning (story `S55-BR-EMC-CM1-03`) et `DEC-0115` (section 1) ecrivent *"accepte sans changement a s53 (P9)"*. Je l'ai deja
corrige a s54 pour `atteinte-autrui` (instance B, section 1.2) : P9 ne decidait que *"Je n'exige pas de ligne `DEGRADE`"*. **Je n'ai
pas audite les items de ce paquet a s53.** Il passe ici sa premiere lecture item par item, et elle trouve trois items a corriger.

### 1.2 `EMC-9` ne vaut pas sur les lignes 3 et 6

Le critere C7 du planning demande, pour `-03` et `-06`, une ligne "relu" sur `EMC-9`. Le referentiel la borne : *"Sur la ligne 2,
aucun item ne laisse croire a l'enfant qu'il vient d'alerter quelqu'un"* (`competences-cm1.md`). Aucun item de ces deux paquets ne
porte sur le fait de prevenir quelqu'un. **Je ne l'etends pas** ; la ligne C7 `EMC-9` est sans objet sur ces deux stories. Les regles
que le developpeur relit a sa place, et ecrit "relu" ou "non relu" dans `rapport-lea-dev.md` :

- les deux paquets : `EMC-4` (aucune proposition, distracteurs compris, n'attribue un trait ou une capacite a une categorie de
  personnes), `EMC-8` (aucun item ne demande ce que l'enfant fait ou ferait), et la regle de s53 section 4.1 (un indice ne redit
  pas le contenu de la bonne reponse de son item) ;
- `-06` en plus : `BIA-5` (aucun distracteur n'enonce un interdit de croire ni ne juge une conviction).

Ce que le developpeur relit ne me remplace pas : je juge ces quatre regles en jouant.

### 1.3 Je corrige mon acceptation de l'indice de l'item 6 de `principes` (s53, P4)

A s53, j'ai retenu tel quel le texte de `marc-dev` pour l'indice de l'item 6 (*"Teste chaque phrase avec un exemple pris dans la
classe."*) **sans le lire contre ses distracteurs**. Lu contre *"jusqu'à ce qu'un adulte la refuse"*, il pousse vers ce distracteur :
en classe, l'enseignant refuse des choses tous les jours. Le defaut est dans le distracteur, pas dans l'indice que j'ai accepte :
je corrige le distracteur (section 3.1) et je ne touche pas l'indice. L'equipe a tenu la barre de s53 ; je ne la deplace pas.

---

## 2. D1 - `cm1-emc-respect-differences` (`EMC-CM1-03`) : trois items a corriger avant branchement

Lu item par item contre `EMC-1`, `EMC-4`, `EMC-5`, `EMC-6`, `EMC-8`, `BIA-1`, `BIA-2`, `FER-1` a `FER-3`. Tenu a la lecture : les quatre
termes de la parenthese sont en bonne reponse (items 1 a 5) ; la difference est toujours le motif d'un traitement, jamais la
description d'un groupe ; aucune religion, aucun pays, aucun handicap particulier nomme ; aucune adresse a l'enfant. Seul fait
institutionnel : l'item 8 (*"chaque personne a la même valeur et les mêmes droits"*), fait de manuel de cycle 3, famille (a) de
`HIS-3` : je le signe, aucune source a exiger a l'audit.

Trois defauts, aucun n'est un motif de refus a lui seul ; corriges avant le branchement, ils ne deviendront pas des reserves. Un
autre texte qui atteint la meme barre est accepte ; je le juge contre la barre, pas contre mon texte.

### 2.1 Item 4 : une capacite attribuee a une categorie (`EMC-4`)

*"Aya, parce qu'elle utilise un fauteuil roulant et que les autres refusent de l'attendre"*, dans un jeu de relais. **La proposition
presuppose qu'une enfant en fauteuil est plus lente** : c'est une capacite attribuee a une categorie de personnes, ce qu'`EMC-4`
refuse meme en bonne reponse. Le relais (un jeu de vitesse) ouvre en plus la lecture "raison du jeu" que l'en-tete craignait
(`EMC-5`). **Barre** : le motif est le handicap seul, dans une activite ou il n'a aucune incidence, et les trois propositions ont la
meme longueur (`CAT-1`).

```yaml
  - enonce: "Trois élèves ne sont pas pris pour un jeu de cartes. Dans quel cas le respect d'autrui n'est-il pas respecté ?"
    bonneReponse: "Aya, parce qu'elle utilise un fauteuil roulant"
    distracteurs: ["Tom, parce que la partie a déjà quatre joueurs", "Léa, parce que la récréation est terminée"]
    indice: "Une raison juste parle de la partie, pas de la personne."
```

Le paragraphe `EMC-5` de l'en-tete, qui decrit l'ancien item 4, est mis a jour dans le meme commit (texte libre, non lu par l'enfant).

### 2.2 Item 7 : l'indice redit la bonne reponse

*"On peut penser autrement sans manquer de respect."* reprend *"on peut ne pas être d'accord ... sans lui manquer de respect"*. Le
second essai devient une recopie (regle de s53 section 4.1). **Barre** : un exemple que l'enfant relie lui-meme a la reponse.

```yaml
  - enonce: "Quelle phrase sur le respect d'autrui est juste ?"
    bonneReponse: "on peut ne pas être d'accord avec un camarade sans lui manquer de respect"
    distracteurs: ["respecter quelqu'un, c'est toujours être d'accord avec lui sur tout", "pour être respecté, il faut cacher ce qui nous rend différent des autres"]
    indice: "Pense à deux amis qui n'aiment pas les mêmes livres."
```

### 2.3 Item 8 : un distracteur que personne ne croit, un indice qui redit la reponse

*"parce que les différences disparaissent avec le temps"* : aucun enfant de neuf ans ne le pense, l'item devient un choix a deux
(l'en-tete le signalait, `PLA-1`). Le remplacant porte la raison qu'un enfant de cet age donne reellement - c'est interdit parce
qu'on est puni - et *"si un adulte le voit"* le rend faux sans discussion (`EMC-5`). L'indice *"Cela concerne la valeur et les droits
de chaque personne."* redit la reponse.

```yaml
  - enonce: "Pourquoi le respect d'autrui interdit-il de traiter quelqu'un moins bien à cause d'une différence ?"
    bonneReponse: "parce que chaque personne a la même valeur et les mêmes droits"
    distracteurs: ["parce que tous les élèves sont exactement pareils", "parce qu'on risque d'être puni si un adulte le voit"]
    indice: "Pense à deux élèves très différents que la loi protège de la même façon."
```

Source a ajouter a l'en-tete (`HIS-3`), a cote de l'article 1er de 1789 qui ne dit que *"égaux en droits"* : la *"même valeur"* est
l'egale dignite de la Declaration universelle des droits de l'homme de 1948, article 1er.

Onze items, deux distracteurs chacun : le motif de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (11 items, 33
propositions) ne change pas.

### 2.4 Ce que je regarderai en jouant, sans en faire une condition

- *"le respect d'autrui n'est-il pas respecté"* (items 1 a 5) : la repetition se lit lourdement, cinq fois. Je juge a l'ecran si un
  enfant de CM1 bute ; si oui, c'est une reserve nouvelle et datee, pas un refus.
- La frontiere avec `-06` : l'item 6 (*"ne pas traiter quelqu'un moins bien parce qu'il est différent"*) et l'item 10 de `principes`
  ont presque la meme reponse et un distracteur identique (*"éviter de parler aux personnes différentes de soi"*). Coherent, les deux
  notions sont liees ; je verifie en jouant les deux paquets que l'enfant ne les lit pas comme un doublon.
- `CYC-1` contre `cp-emc-regle-souhait-loi` (servi) ; `cp-emc-personnes-differentes` est sous refus et n'est pas servi.

---

## 3. D2 - `cm1-emc-principes-republique` (`EMC-CM1-06`) : deux items a corriger avant branchement

### 3.0 "La ligne la plus dangereuse de la matiere" : ce que cela change

C'est mon jugement de faisabilite (referentiel, ligne 6 ; matrice) : `EMC-4`, `EMC-5` et `BIA-5` mordent toutes les trois, *"un item
faux sur cette ligne fait plus de degats qu'une absence"*. Concretement, pour cet audit :

1. **Le prix est celui de l'EMC CM1, 1,5, et non 1,0.** Mon tarif de l'EMC CM1 est 1,5 (`DEC-0077` section 8.1, applique de s41 a
   s47). A s54, j'ai facture `atteinte-autrui` 1,0 pour un motif ecrit : *"aucun fait institutionnel n'est affirme"*. Ce motif ne
   tient pas ici : les onze items reformulent la Declaration de 1789, la Constitution, la loi de 1905 ou le code penal, et c'est la
   **formulation** qui porte le risque (les deux defauts ci-dessous en sont).
2. **Un distracteur vrai dans une lecture que l'enfant fait reellement est un motif de refus**, au meme titre qu'une erreur de fait
   (`EMC-5`, `HIS-3`). Je joue chaque item avec la lecture d'un enfant de neuf ans, pas avec celle d'un juriste.
3. **L'ordre** : `-03` d'abord, `-06` ensuite, comme le planning. La marge de 0,5 finance un rejeu ; un second refus dans B est un
   lot non ferme a s55, dit comme tel a la review.
4. **Les six indices de P4** (s53) : je les juge contre mon texte de s53, applique mot pour mot. Je ne releve pas cette barre.

Tenu a la lecture : P3 applique (*"la démocratie"*, item 4) ; aucun distracteur n'enonce un interdit de croire ou de pratiquer
(`BIA-5`) ; la laicite est toujours ce que l'ecole et la Republique garantissent ; le principe voisin n'est jamais en distracteur
(items 1 a 5) ; aucune adresse a l'enfant (`EMC-8`) ; aucun lieu ni famille presuppose (`EMC-6`).

### 3.1 Item 6 : un distracteur vrai pour un enfant (`EMC-5`, `EMC-1`)

*"jusqu'à ce qu'un adulte la refuse"*. Pour un enfant de neuf ans, c'est vrai : ses parents et son enseignant limitent sa liberte, et
c'est legitime. Marque faux, l'item lui dit qu'un adulte qui refuse ne borne pas sa liberte, et l'indice (*"un exemple pris dans la
classe"*) le pousse vers ce distracteur (section 1.3). **Barre** : un distracteur faux pour toute lecture, qui porte une conception
reelle. Le plus repandu : la liberte sans limite, que l'article 4 de 1789 contredit.

```yaml
  - enonce: "Jusqu'où va la liberté de chacun dans la République ?"
    bonneReponse: "jusqu'à ce qu'elle nuise à autrui"
    distracteurs: ["jusqu'à pouvoir faire tout ce que l'on veut", "jusqu'à ce que la majorité ne soit pas d'accord"]
    indice: "Teste chaque phrase avec un exemple pris dans la classe."
```

### 3.2 Item 8 : la fraternite n'est pas reservee aux citoyens (`HIS-3`, `CYC-1`)

*"se soutenir et s'entraider entre citoyens"*. Le Conseil constitutionnel a juge en 2018 (decision no 2018-717/718 QPC du 6 juillet
2018) que du principe de fraternite decoule la liberte d'aider autrui *"sans considération de la régularité de son séjour"* : la
fraternite ne s'arrete pas aux citoyens. Un enfant de CM1 n'est pas citoyen au sens civique, et beaucoup n'ont pas la nationalite
francaise. Au CP, le produit enseigne deja la fraternite comme l'aide a un camarade (`cp-emc-devise.yaml`, quatre items) : *"entre
citoyens"* retrecit ce que le CP a appris. **Barre** : la bonne reponse ne borne la fraternite a aucun groupe.

```yaml
  - enonce: "Que veut dire la fraternité dans la devise de la République ?"
    bonneReponse: "se soutenir et s'entraider les uns les autres"
    distracteurs: ["avoir les mêmes idées que les autres", "faire partie de la même famille"]
    indice: "Pense à des personnes qui apportent leur aide après une tempête."
```

La decision de 2018 est ajoutee a la liste `HIS-3` de l'en-tete. Onze items, 33 propositions : le motif de `LeRang...` ne change pas.

### 3.3 Ce que je ne peux juger qu'en jouant

Je reste la seule garde de chacun de ces points : aucun garde-fou ne les mesure.

1. `EMC-5` sur les items 1 a 5 : qu'aucun enfant ne puisse defendre le principe en distracteur (item 1 : la laicite contre la liberte
   d'opinion ; item 2 : la liberte contre l'egalite d'acces).
2. Les trois items de laicite (4, 9, 11) cote a cote : qu'ils ne se lisent pas comme un interdit, distracteurs compris (`BIA-5`).
3. L'exactitude des cinq familles de sources de l'en-tete, contre leur texte (`HIS-3`) ; je n'en suis pas la source.
4. Le vocabulaire a l'ecran : *"employeur"*, *"discriminations"*, *"autrui"*, *"laïque"* et les guillemets de l'item 11.
5. Fuite d'un indice vers la reponse d'un autre item, selon l'ordre de tirage (items 7 et 2, items 9 et 4).

### 3.4 Conditions d'entree de catalogue, pour les deux paquets

Titre sans niveau (`AucunTitreDeJeuNePorteSonNiveauTest`), qui ne nomme ni un groupe de personnes ni une religion (`EMC-4`, `BIA-5`).
*"Lis bien, puis choisis la bonne réponse."* (consigne existante) convient. **Mention exigee d'avance**, si l'une des deux lignes
ferme, reproduite mot pour mot par `camille-po` dans la matrice : *"connaissances civiques et reconnaissance de situations seules ;
aucun comportement, aucun engagement et aucune participation à une décision collective ne sont servis par ce produit."* Les deux
en-tetes la portent deja ; je la juge au verdict.

---

## 4. D3 - `S54-ART-RES-R1` a `R3` (Q5) : instance B, textes exacts

### 4.1 Quelle instance, et ce qu'en dit `PAR-2`

**B relit les trois reserves.** Arts plastiques CP n'est porte par aucune instance a s55, donc `PAR-2` permet A ou B ; je prends B
parce que c'est un jeu de CP servi avec un texte faux, rejoue dans la meme session que `EXC-R15`. Les reserves sont celles de ma
fiche (`FICHE-bandit-manchot-familles-d-oeuvres-cp.md`, section 5, instance C a s54) ; la barre est celle de la fiche, mot pour mot.

**Consequence de `PAR-2` que `DEC-0115` 5.1 ne voit pas** : une matiere et un niveau ne sont jamais partages dans un sprint. Si B
porte `ART-RES`, **l'arbitrage de forme d'`ADR-0104` (points 2 a 5, arts plastiques CP) ne peut pas aller a A a s55**, et B, a 4,0
sur 4,0, ne peut pas le porter. Deux issues, que je ne tranche pas parce que la reserve de A n'est pas a B :

| Issue | B | A | Ce que cela coute |
|---|---|---|---|
| (a) `ART-RES` sur B, arbitrage de forme a s56 avec le paquet a reproductions | 4,0 | inchange | T3 de la mecanique attend mes mots (`DEC-0115` 5.4, point 5) ; l'enfant ne voit rien de plus a s55 de toute facon (aucun paquet, 5.3) |
| (b) arts plastiques CP entierement sur A : `ART-RES` (0,25) et la forme | 3,75 | 3,25 + prix de la forme, sur sa reserve | amendement de `DEC-0115` par `camille-po`, accepte par l'instance A dans son propre arbitrage |

Sans amendement ecrit avant l'ouverture du worktree de `lea-dev`, c'est (a). **Dans les deux cas, `ART-RES` se fait a s55 et fusionne
en tete** : aucun report.

### 4.2 Les trois textes

Contraintes verifiees a la lecture contre les criteres du planning : aucun `reconna` ni `rapproch` ; l'indice de l'item 11 contient
`peinture` sans `peintre` ni `pinceau` ; un metier au feminin, la meme forme partout. Aucun test du paquet ni l'inventaire de chaines
ne porte *"photographe"* (`grep` sur `PaquetCpArtsOeuvresFamillesTest` et `TexteAccentueExposeParLePortEntrantTest`) ; l'inventaire
porte la consigne actuelle et change avec elle.

**R1, consigne** (`catalogue.yaml`, entree `bandit-manchot-familles-d-oeuvres-cp`). Chaque enonce porte sa question ; la consigne
redevient generique, comme ma fiche le permet.

```yaml
    consigne: "Lis bien, puis choisis la bonne réponse."
```

**R2, indice de l'item 11.** Dit ce qui distingue un tableau d'une photographie.

```yaml
  - enonce: "Quelle œuvre peinte accroche-t-on au mur ?"
    bonneReponse: "un tableau"
    distracteurs: ["une statue", "une photographie"]
    indice: "Cette œuvre est faite avec de la peinture."
```

**R3, *"une photographe"*, aux items 2, 3 et 4.** Je choisis ce metier parce qu'au feminin seul l'article change, et parce que l'item
3, ou il est la bonne reponse, se trouve deja par la famille de mots (*photographie*, *photographe*), que j'ai acceptee a s54 :
l'article n'y ajoute aucun indice.

```yaml
  - enonce: "Qui fait des tableaux ?"
    bonneReponse: "un peintre"
    distracteurs: ["un sculpteur", "une photographe"]
    indice: "Cette personne travaille avec de la peinture."
  - enonce: "Qui fait des photographies ?"
    bonneReponse: "une photographe"
    distracteurs: ["un peintre", "un sculpteur"]
    indice: "Cette personne fait des images qu'on peut imprimer."
  - enonce: "Qui fait des statues ?"
    bonneReponse: "un sculpteur"
    distracteurs: ["un peintre", "une photographe"]
    indice: "Cette personne travaille avec ses mains, debout devant un gros bloc."
```

Apres correction : `grep -c "un photographe"` sur le paquet rend 0, `grep -c "une photographe"` rend 3. Rien d'autre ne change. Un
seul commit (critere du planning). **Regime** : mot pour mot, 0,25 (`PAR-10`) ; un autre texte qui atteint la barre de la fiche est
leve aussi, a 0,5. `PAR-14` ne s'applique pas : 4 items sur 12 et la consigne.

---

## 5. D4 - `EXC-R15` : instance B, barre inchangee

**B la porte, et ce n'est pas un choix.** `PAR-2` : l'EMC CP et l'EMC CM1 comptent pour une seule matiere ; B porte l'EMC CM1, donc
l'EMC CP ne peut pas aller a A. Que l'instance A de s54 ait ecrit la reserve ne change rien : une barre ecrite est dans la fiche, pas
dans l'instance.

La barre est celle de `FICHE-bandit-manchot-exclusion-cp.md`, bloc du 2026-10-08, section 4, mot pour mot : quatre lignes, rien
d'autre. **Prix : 0,25.** Le rejeu verifie le diff, puis rejoue les items 1, 3, 4 et la consigne a l'ecran. Rappel du symptome deja
ecrit pour `marc-dev` : `PaquetCpEmcExclusionTest` identifie la tache A par ses bonnes reponses (ligne 82).

**Ce que je regarderai au rejeu, sans en faire une condition** : le titre servi est *"Une moquerie qui se répète"*. Ma barre de s54
dit que le titre ne change pas, et je ne la releve pas. Si, a l'ecran, ce titre contredit la mise a l'ecart que le jeu nomme
desormais, ce sera une reserve nouvelle et datee, allouee au registre dans le meme commit, pas un durcissement d'`EXC-R15`.

---

## 6. Tarifs reels de l'instance B

| Poste | Regle | Prix |
|---|---|---|
| Cet arbitrage | precedent de s54 B : deux paquets lus item par item, huit blocs de texte | **0,5** |
| `EXC-R15` | `PAR-10`, mot pour mot | **0,25** (0,5 si reecrit) |
| `S54-ART-RES-R1` a `R3` | `PAR-10`, mot pour mot, textes section 4.2 | **0,25** (0,5 si reecrit) |
| `EMC-CM1-03` | premier passage, EMC CM1, un seul fait de manuel signe ici (motif de s54) | **1,0** |
| `EMC-CM1-06` | premier passage, EMC CM1, faits institutionnels sur onze items (`DEC-0077` 8.1) | **1,5** |
| **Alloue** | | **3,5** |
| Marge de rejeu nommee, allouee a aucun lot | `PAR-8`, `PAR-9` | **0,5** |
| **Total / 4,0** | `PAR-9` | **4,0** ; 0,0 non alloue |
| Lots / 3 | `PAR-8` | **2** |

**Le planning et `DEC-0115` ecrivent 3,75 : je corrige a 4,0.** Ecart : `EMC-CM1-06` +0,5 (section 3.0), `ART-RES` -0,25 (textes
fournis). Somme du sprint avec A inchangee : 7,0 sur 8,0.

**Si l'une des deux corrections est reecrite au lieu d'etre appliquee**, B passerait a 4,25 : je ne demande pas d'ecart. Le surcout
de 0,25 se paie sur la marge, qui ne finance plus aucun rejeu de lot ; un refus de `-03` ou de `-06` serait alors un lot non ferme
a s55. Avec les deux reecrites, la marge est consommee en entier.

**`PAR-11`** (0,5 de marge par lot neuf) demanderait 1,0 a B, soit 4,5. Elle reste non tranchee (`ARBITRAGE-PREALABLE-s52-instances-ce1.md`
section 1.1) ; je compte comme a s54, une marge de 0,5 par instance, et j'ecris le risque : B ne peut rejouer qu'un lot.

**`PAR-13`** : B ne porte aucun terrain neuf. EMC CM1 : neuf lignes `couvert-valide`, `EMC-4`, `EMC-5`, `BIA-5` ont mordu du contenu
reel. Arts plastiques CP : `ART-1`, `ART-2`, `CON-1` aussi.

**Prix de l'arbitrage de forme d'`ADR-0104`** : je ne le fixe pas ici. Il n'est pas dans le perimetre de B (section 4.1) et je n'ai
pas lu les points 2 a 5 pour ce document.

## 7. D6 - Q6 et Q7

**Ni l'une ni l'autre n'est adressee a B.** Le planning (affectations nominatives) route Q3, Q4 et Q7 (passe `PAR-4`, verification
A10) a l'instance A ; Q6 porte sur le francais CE1, que `PAR-2` met entierement chez A. Je n'y reponds pas : deux instances qui
ecrivent la meme decision sans le savoir, c'est la forme (c).

## 8. Ordre de jeu

`EXC-R15` puis `ART-RES` (jeux servis, premiers fusionnes) : les items corriges d'abord, avec une erreur sur chacun. Puis `-03`,
items 4, 7, 8 en premier, puis les onze. Puis `-06`, items 6 et 8 en premier, puis 4, 9, 11, puis les onze. Un port de serveur et
un sous-repertoire de scratchpad propres a l'instance B, choisis au lancement.

## 9. Routage

| A qui | Quoi |
|---|---|
| `lea-dev` | Section 4.2 : les trois textes, un seul commit, en tete. Sections 2 et 3 : les cinq blocs et les deux ajouts d'en-tete, dans le commit de branchement de chaque paquet (C2 : correction decidee par `nadia-prof`). Section 1.2 pour `rapport-lea-dev.md` |
| `marc-dev` | `EXC-R15` : la barre de la fiche, mot pour mot |
| `camille-po` | Section 1.1 (P9, deuxieme fois) ; section 4.1 : issue (a) ou (b), et `DEC-0115` 5.1 a corriger si (b) ; tarifs de la section 6 au backlog |
| `sofia-sm` | Section 6 (B a 4,0, pas 3,75) ; section 1.2 (C7 : `EMC-9` sans objet sur `-03`, `-06`). Le worktree de B s'appelle `../recreo-s55-nadia-b`, branche `s55-nadia-b`, et non `s55-nadia-instance-b` comme au planning : amendement A5 a ecrire |

## 10. Ce que ce document ne fait pas

- Il ne prononce aucun Done, ne touche aucune fiche, ni la matrice, ni le backlog, ni un YAML, ni `catalogue.yaml`, ni le registre
  d'identifiants.
- Il ne cree aucun identifiant de condition ou de reserve.
- Il ne releve aucune barre ecrite a s53 ou a s54 : il les applique aux textes neufs.
- Il ne repond a aucune question routee a l'instance A et ne fixe aucun prix sur la reserve de A.
