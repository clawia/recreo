# Arbitrage prealable - s56, instance B : paquet a reproductions d'`ART-CP-10`, rejeux d'EMC (`S56-ARB-B`)

`nadia-prof`, instance B, le 2026-10-09 (20:58, UTC+02:00, lu par `date`). Branche `s56-nadia-b`, issue de `round-two` a `dfcec37c`
(planning de s56 fusionne) ; `bash outils/verifier-lignee.sh` sort 0. Worktree et scratchpad propres a l'instance B.

**Arbitrage sans jeu.** Le paquet a reproductions n'existe pas ; les deux jeux d'EMC CM1 et `exclusion-cp` seront rejoues apres
livraison, pas avant. Je decide sur la lecture de `DEC-0116` (dont A.1), du planning de s56, du backlog "Sprint 56",
de `proposition-s56.md`, de `cloture-nadia.md` (sections 4.2 et 4.4), de mes arbitrages de s55 (instance A section 3, complement section 1,
instance B), de `audit-nadia-b-emc.md`, de `rapport-lea-dev-mecanique.md` (E3, T2, T3), d'`ADR-0104`, de `CHOIX-oeuvres-candidates-T5.md`
(sections 5 et 8) et des dimensions des huit fichiers servis sous `static/oeuvres/` (en-tete `SOF`, lu par moi). **Aucun Done, aucun verdict,
aucun mouvement de matrice. Aucun identifiant neuf** : les codes `B1` a `B14` de ce document sont des reperes locaux, comme `M1` a `M6` et
`X1` a `X7` de s55.

L'arbitrage de forme (`S56-ART-CP-10-FORME`, `Q1` a `Q6`) est dans un fichier a part :
`squad/conformite/ARBITRAGE-s56-forme-art-cp-10.md`. Ce document y renvoie pour la marque de l'echec.

## Resume

| Code | Point | Decision | Prix |
|---|---|---|---|
| D1 | Paquet a reproductions (`S55-ART-CP-10-PAQUET`) | conditions `B1` a `B14`, section 1 ; au moins 4 items, au plus 6 | - (hors `PAR`) |
| D2 | Audit du paquet (`S56-AUD-ART-CP-10`) | **prix confirme** | **2,5** |
| D3 | `PAR-2` : choix des oeuvres distractrices (`S56-ART-CP-10-OEUVRES`) | **sur B**, pas sur A | 0,25, deplace de A vers B |
| D4 | Capacite de B | **4,75**, au-dela de la ventilation de `DEC-0116` A.1 (B a 4,5) : **question a l'utilisatrice**, section 2.3 | - |
| D5 | `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` | rejeux confirmes, mot pour mot | **0,25** chacun (0,5 si un autre texte) |
| D6 | A11 (en-tete de `FICHE-bandit-manchot-exclusion-cp.md`, titre du jeu) | au rejeu d'`EXC-R16`, sans prix propre ; le titre est deja juge (section 3.3) | 0,0 |
| D7 | `PAR-13` | **tenue** : la forme d'`ART-CP-10` est le seul terrain neuf de s56, sur B | - |
| D8 | A9 (capacite de B a s55) | **3,5 consommes** ; mon 4,0 comptait `EXC-R15` deux fois (section 0) | - |
| D9 | Cet arbitrage | prix confirme | **0,5** |
| D10 | Arbitrage de forme | fichier a part, prix confirme | **0,25** |

---

## 0. A9 - la capacite de B a s55 : 3,5, et la correction de mon 4,0

**Une ligne : la lecture qui vaut est 3,5 consommes par B a s55, sur une enveloppe de 3,75 (3,25 de postes et 0,5 de marge) ; il reste 0,25 de
marge non utilisee.**

Le detail, parce que l'ecart vient de moi. Mon arbitrage de s55 (instance B, section 6) allouait 3,5 dont `ART-RES` 0,25. L'option (b)
(`DEC-0115` A.1) a mis `ART-RES` sur A : postes de B 0,5 + 1,0 + 1,5 + 0,25 (`EXC-R15`) = 3,25, plus 0,5 de marge = 3,75, le chiffre de
`DEC-0115` A.19. Consomme : 3,25 + 0,25 de l'ecart de l'item 4 de `-03` (`P1` d'`audit-nadia-b-emc.md`, tire sur la marge) = **3,5**.
`audit-nadia-b-exc.md` ecrit *"B passe de 3,75 a 4,0"* en ajoutant `EXC-R15` : ce poste etait deja dans les 3,75. **J'ai compte
`EXC-R15` deux fois.** Le 4,0 de `review.md` de s55 (section 10, "Lecture de `nadia-prof`") est ce double compte ; la lecture de
`camille-po` (3,5) est juste. Rien de s56 n'en depend.

---

## 1. Le paquet a reproductions : ce qu'il doit contenir (`S55-ART-CP-10-PAQUET`)

Mes regles 1 a 6 (arbitrage A de s55, section 3.4), `M1` a `M6` et `X1` a `X7` (section 3.2 et 3.3) restent ce qu'elles sont. Ce qui suit les
applique a un paquet reel ; **aucune barre n'est relevee**. Deux precisions de mes propres conditions sont datees ici et dites comme
telles (`B5`, et `M1` dans le fichier de forme, `Q2`).

### 1.1 Forme du fichier

- **`B1`** - Un fichier, `type: question`, `lignesDeReferentiel: ["ART-CP-10"]` et rien d'autre. Chaque item : `illustration: oeuvre-...`
  (la reference), `bonneReponse: "oeuvre-..."` et deux `distracteurs: ["oeuvre-...", "oeuvre-..."]` : trois propositions, une juste
  (regle 4). Les trois invariants d'`ADR-0104` (decision 5) tiennent ; le garde-fou 2 les juge.
- **`B2`** - `illustrationTexteAlternatif` de la reference : le texte deja servi par `cp-decrire-une-oeuvre`, a la lettre, le meme sur tous
  les items. Il ne nomme ni sujet, ni titre, ni artiste (`X1`, `CON-5`) :

```text
Une reproduction d'œuvre à regarder attentivement avant de répondre.
```

### 1.2 Oeuvres, paires, distracteurs

- **`B3` - Paires** : seulement les trois de `CHOIX-oeuvres-candidates-T5.md` (section 8.4) : `oeuvre-chambre` et `oeuvre-champ-de-ble`
  (meme touche) ; `oeuvre-grande-vague` et `oeuvre-averse-sur-le-pont` (meme maniere de dessiner l'eau et la pluie) ; `oeuvre-femme-au-luth`
  et `oeuvre-jeune-femme-aiguiere` (meme lumiere de fenetre). Chaque oeuvre d'une paire est reference **au plus une fois** ; un couple
  (reference, bonne oeuvre) n'apparait jamais deux fois, meme avec d'autres distracteurs : la seconde fois, l'enfant retrouverait la paire de
  memoire au lieu de regarder.
- **`B4` - Le vivier des distracteurs** : les huit reproductions servies et les trois a restaurer, rien d'autre a s56. Une oeuvre neuve
  demande une source et une preuve (`ADR-0082`), c'est-a-dire du travail de T4 que personne n'a chiffre. **Si un item ne peut pas tenir la
  regle 2 avec ces onze oeuvres, il sort du paquet ; la regle ne baisse pas.** Que `theo-archi` nomme dans son avis (`AV2`) une reproduction
  de provenance prouvee hors des onze change ce point ; sans cela, il tient.
- **Regle 2 appliquee, et ce que j'en lis aujourd'hui** (a verifier sur les images dans `S56-ART-CP-10-OEUVRES`) :
  - **Van Gogh** : la bonne oeuvre ne partage pas le sujet de la reference (une chambre, un champ). La seconde clause de la regle 2 ne
    s'applique pas. Quand la reference est le champ de ble, `oeuvre-moissonneurs` (un champ de ble, sans la touche) est un distracteur qui
    partage le sujet de la reference : il est bienvenu, c'est exactement le piege que la competence doit dejouer.
  - **Hokusai et Hiroshige** : de l'eau des deux cotes. `oeuvre-rue-sous-la-pluie` (de la pluie, sans la maniere de l'estampe) tient la
    seconde clause, dans les deux sens.
  - **Vermeer** : une femme pres d'une fenetre des deux cotes. **Parmi les onze, je ne vois aucune autre femme dans un interieur** : la rue
    sous la pluie est dehors, les quatre saisons sont un visage fait de bois. Si la lecture des images le confirme et que `AV2` ne nomme rien,
    **les deux items de Vermeer sortent**, et `oeuvre-jeune-femme-aiguiere` n'est pas restauree (garde-fou 1, *"aucune ne dort"*).
- **`B5` - Le format ne designe pas la bonne oeuvre, ni par la forme ni par la taille.** Regle 3 telle qu'ecrite : aucun item ou la bonne
  oeuvre est la seule a partager le format de la reference. **Et, dans une case carree egale** (`Q2` du fichier de forme), une image en
  hauteur se rend plus grande qu'une image en largeur : **aucun item ou la bonne oeuvre est la seule des trois propositions de son format**.
  C'est le motif de `M1` (*"une image plus grande serait un indice"*) applique a la case egale, pas une barre neuve. Formats lus : en largeur,
  `chambre` (352 x 275), `grande-vague`, `marche-aux-chevaux`, `moissonneurs`, `rue-sous-la-pluie`, `champ-de-ble` (352 x 280) ; en hauteur,
  `femme-au-luth` (307 x 352), `hippopotame`, `quatre-saisons`, `averse-sur-le-pont` (242 x 352), `jeune-femme-aiguiere` (313 x 352).
  Exemple : reference `grande-vague`, bonne `averse-sur-le-pont` (en hauteur) : un des deux distracteurs est en hauteur aussi.
- **`B6` - Distracteurs plausibles** : chaque proposition est une image plate, peinture ou estampe. **`oeuvre-hippopotame` (une statuette de
  faience) n'est jamais proposee** : l'enfant l'ecarterait parce que c'est un objet, sans regarder la propriete. Un distracteur qu'on
  elimine sans regarder fait d'un choix a trois un choix a deux.
- **`B7` - Nombre d'items** : 6 au plus (trois paires, deux sens), **4 au moins** (deux paires, deux sens). `nombreDeManches` strictement
  inferieur au nombre d'items (`ADR-0023`) : 5 pour 6 items, 3 pour 4. **Moins de 4 items, c'est une seule paire : ce n'est pas un jeu de
  rapprochement, et je le refuserais sur ce motif.**

### 1.3 Mots lus par l'enfant

Mes mots (`CON-5`). Un autre texte qui tient la barre est accepte ; je le juge contre la barre, pas contre mon texte.

- **`B8` - Enonce, le meme pour tous les items.** Barre : il demande **la facon dont l'oeuvre est faite**, jamais seulement *"ce qui va
  avec"* ; dix mots au plus ; aucun sujet, titre ni artiste. Motif : *"Quelle oeuvre va avec ?"* rend defendable le rapprochement par le sujet
  (deux images de pluie *vont ensemble* pour un enfant de six ans), que la regle 2 compte faux. Un enfant ne doit pas etre compte faux pour
  avoir repondu a la question posee (`EMC-5`, meme motif). Texte, espace insecable devant le point d'interrogation (`TYP-1`) :

```text
Quelle œuvre est faite de la même façon que celle-ci ?
```

- **`B9` - Consigne et titre** (`catalogue.yaml`). Titre sans niveau (`AucunTitreDeJeuNePorteSonNiveauTest`), sans sujet ni artiste,
  distinct de *"Décrire une œuvre"* a l'ecran. Textes :

```text
titre: Des œuvres faites de la même façon
consigne: Regarde l'œuvre, puis trouve celle qui est faite de la même façon.
```

- **`B10` - Indice et explication de chaque item** : regle 5 (l'indice dit ou regarder, jamais le sujet de la bonne oeuvre ; l'explication
  nomme ce que les deux oeuvres partagent, en mots de CP, sans titre ni artiste). **Les textes sont ecrits dans le document de
  `S56-ART-CP-10-OEUVRES`**, avec les paires : ils dependent des distracteurs. `lea-dev` les recopie, elle ne les redige pas.
- Phrases du gabarit : `M4` et `M5`, inchangees.

### 1.4 T4 et T5

- **`B11` - T4** : `lea-dev` restaure depuis `1db6348f^`, a l'octet, **exactement** les reproductions que le document des oeuvres retient,
  avec leurs entrees de registre. Les trois constantes montent ensemble, sur ce qui est installe (`ADR-0104` section 8) : si les trois
  oeuvres restent, `REPRODUCTIONS_SERVIES` 8 -> 11 et `POIDS_TOTAL_MAXIMUM` 176 474 -> 250 915 ; si le Vermeer sort (`B4`), 8 -> 10 et
  176 474 -> 229 579 (176 474 + 31 398 + 21 707, octets lus dans le tableau de la section 8 d'`ADR-0104`). Ces sommes sont mon arithmetique :
  le cliquet exact juge, pas ce paragraphe.
- **`B12` - T5** : l'entree de catalogue apres le garde-fou du plafond et la marque de l'echec (ancetres, `git merge-base --is-ancestor`),
  et apres l'arbitrage de forme. La fiche de conformite est ouverte par `camille-po` apres ce commit (`DEC-0115` A.11).

### 1.5 Plafond de 176 px et marque de l'echec

- **`B13` - Plafond** : `S55-ART-CP-10-PLAFOND` fusionne avant l'entree de catalogue. Sa forme est a `theo-archi`. Ce que j'exige : qu'une
  image de proposition ne puisse pas monter au-dessus de 176 px CSS sans qu'un test rougisse. **La visibilite de chaque propriete a 176, 132-137
  et 84 px, je ne la juge qu'a l'ecran, a l'audit** (`Q8`, regle 6). Chambre et champ de ble sortent si la touche ne s'y voit pas (regle 1).
- **`B14` - Marque de l'echec (`E3`)** : condition de branchement, sans changement depuis le 2026-10-08 (`cloture-nadia.md` 4.2) : *"branche
  sans elle, il serait refuse sur ce motif"*. **Le critere a ecrire pour `S56-ART-CP-10-ECHEC` est dans le fichier de forme, `Q1`, conditions
  `N1` a `N6`** ; `camille-po` l'ajoute au backlog par amendement, avec mes mots, avant le worktree de `lea-dev`.

### 1.6 Prix de l'audit : 2,5, confirme

Lot d'arts plastiques CP dont je ne suis pas la source (1,5), illustre (+0,5, `REC-1`), forme dont les conditions n'ont jamais rencontre de
contenu (+0,5). Un paquet de 4 items au lieu de 6 ne le baisse pas : le prix tient a la forme jouee pour la premiere fois, pas au nombre
d'items. Il comprend le jeu de `M1` a `M6`, `X1` a `X7`, `E2` a l'ecran, `Q8`, `B3` a `B10` sur les paires reelles, et la mention de s50.

---

## 2. `PAR-2` : le choix des oeuvres distractrices va sur B

### 2.1 Lecture

**Le choix des oeuvres va sur B.** `PAR-2` : une matiere et un niveau ne sont jamais partages dans un sprint. Choisir les distracteurs d'un
paquet d'arts plastiques CP, c'est travailler les arts plastiques CP ; l'audit de ce meme paquet est sur B. Mettre le choix sur A, c'est
partager la matiere entre deux instances. A s55, les deux instances l'ont lu ainsi, pour une frontiere moins etroite que celle-ci : *"tenue par
deux instances, elle est tenue par moitie"* (arbitrage A de s55, 2.3, d'accord avec l'arbitrage B, 4.1). Ici, ce n'est pas une frontiere
entre deux paquets, c'est le meme paquet : celle qui choisit les distracteurs est celle qui doit repondre de la regle 2 en jouant.

### 2.2 Consequence sur la capacite

| | A | B | Somme |
|---|---|---|---|
| `DEC-0116` A.1 (choix des oeuvres sur A) | 4,0 | 4,5 | 8,5 |
| Cette lecture (choix des oeuvres sur B) | **3,75** | **4,75** | **8,5** |

La somme ne change pas : l'ecart total reste 0,5. **B est a 4,75, au-dessus de la ventilation que `DEC-0116` A.1 ecrit (B a 4,5) et de
`PAR-9` de 0,75.** Sous le plafond dur de 5,0 ; lots de B : un (l'audit). Les 0,25 liberes sur A ne sont alloues a rien sans amendement.

### 2.3 Ce qui exige l'utilisatrice

**B a 4,75 est une decision de l'utilisatrice, pas la mienne.** Ses mots, *"Ecart de 0,5"*, ne citent pas de ventilation (`camille-po` le dit
en A.1 : *"Le rattachement est le mien"*). La question, en une phrase : **votre ecart de 0,5 vaut-il quelle que soit sa repartition entre les
deux instances, B montant a 4,75 et A descendant a 3,75 ?**

| Issue | B | Cout |
|---|---|---|
| (a) oui | 4,75 | aucun poste ne bouge |
| (b) non : `EMC-CM1-R8` passe a s57 | 4,5 | premier report compte de `EMC-CM1-R8`. **Si une reserve part, c'est `R8`, pas `R9`** : `R9` corrige un indice inexact lu par l'enfant, `R8` deux indices qui designent la reponse par un mot ; l'inexact passe avant |
| (c) non : `PAR-2` suspendue pour s56, choix sur A | 4,5 | **je ne la recommande pas** : la regle 2 serait appliquee par une instance et jugee par une autre |

Ma lecture, qu'elle peut defaire : (a). **Je n'ecris pas le document des oeuvres avant sa reponse ecrite** (forme 2) ou l'amendement de
`sofia-sm` qui la porte : c'est un poste que sa reponse finance ou non. L'echeance du planning pour ce document est le 2026-10-10 a 08:00.

### 2.4 Pour l'instance A, qui ecrit en parallele

`S56-ARB-A` repond aussi a `PAR-2` (critere du backlog). **Deux instances qui ecrivent la meme decision sans le savoir, c'est la forme (c).**
Si `S56-ARB-A` place le choix sur A, les deux lectures se contredisent : l'agent qui nous lance le porte a `sofia-sm` avant tout worktree du
paquet, et l'instance A n'ecrit pas le document des oeuvres tant que ce n'est pas tranche.

---

## 3. Les rejeux de B

### 3.1 `EMC-CM1-R8` et `EMC-CM1-R9` : 0,25 chacun

- Barre : les textes de la section 5 de chaque fiche (`FICHE-bandit-manchot-respect-differences-cm1.md`,
  `FICHE-bandit-manchot-principes-republique-cm1.md`), mot pour mot. Rien d'autre ne change dans les paquets. **0,25 chacun** (`PAR-10`) ; 0,5
  pour un autre texte, que je juge contre la barre de la fiche.
- `R9` porte aussi la citation de 2018 de l'en-tete (A13, point (b) de la fiche) : texte non lu par l'enfant, verifie au `diff` contre mon
  texte, compris dans les 0,25.
- Le rejeu : le `diff` d'abord, puis le jeu servi, l'item corrige avec une erreur sur chaque distracteur, l'indice lu a l'ecran, puis une
  partie complete. Apres le commit de `lea-dev` (ou de `marc-dev` si le planning les lui passe).

### 3.2 `EXC-R16` : 0,25

Texte deja applique par `e6262ac2`. Le rejeu verifie le `diff` (une ligne, item 4), puis l'indice de l'item 4 **a l'ecran, apres une erreur
sur chacun des deux distracteurs**, et une partie complete. **0,25** (`PAR-10`). Aucun developpement.

### 3.3 A11 : en-tete de la fiche et titre du jeu

- **En-tete** : au rejeu d'`EXC-R16`, dans le meme commit que son verdict, je complete *"Statut global"* et *"Derniere mise a jour"* de
  `FICHE-bandit-manchot-exclusion-cp.md`, qui s'arretent a s54 et disent `EXC-R15` ouverte. Le bloc de s55 la dit levee et fait foi.
  J'ajoute une ligne datee ; je n'efface pas les lignes de s54.
- **Titre** (*"Une moquerie qui se répète"*) : **deja juge**, le 2026-10-08, dans le bloc de s55 de la fiche (section 4) : affiche sur la
  liste des jeux seulement, il nomme la moitie de la competence sans donner un faux nom au moment ou l'enfant repond : *"Pas de reserve."*
  La retro de s55 ne l'a pas vu. Je le reporte dans l'en-tete au rejeu ; je le regarde a nouveau a l'ecran, sans relever la barre : un
  defaut neuf serait une reserve neuve et datee.
- Sans prix propre (`DEC-0116` 6).

---

## 4. `PAR-13`

**Tenue.** La forme d'`ART-CP-10` n'a aucune cousine : c'est **le** terrain neuf de s56, et il est sur B (arbitrage A de s55, 3.5). Le
choix des oeuvres en fait partie. Les rejeux d'EMC ne sont pas un terrain neuf (conditions deja mordues par du contenu). **Ce que cela
impose a A, que je ne tranche pas** : son lot doit etre cousin d'une condition servie (`-13` ou `-15`, lecture de l'instance A), et ni `-08` ni
`-09` dans ce sprint (arbitrage A de s55, 1.1).

---

## 5. Capacite de l'instance B

| Poste | Regle | Prix |
|---|---|---|
| Cet arbitrage | `S56-ARB-B` | **0,5** |
| `EMC-CM1-R8` | `PAR-10`, mot pour mot | **0,25** |
| `EMC-CM1-R9` | `PAR-10`, mot pour mot | **0,25** |
| `EXC-R16` (et A11) | `PAR-10`, mot pour mot | **0,25** |
| Arbitrage de forme | `S56-ART-CP-10-FORME` | **0,25** |
| Choix des oeuvres distractrices | `PAR-2`, section 2 | **0,25** |
| Audit du paquet | arbitrage A de s55, 3.5 | **2,5** |
| Marge de rejeu, allouee a aucun lot | `PAR-8`, `PAR-9` | **0,5** |
| **Total** | | **4,75** (4,5 dans l'issue (b) ou (c) de 2.3) |

**Le risque, ecrit** : la marge paie **un** rejeu du paquet (0,5, `PAR-10`). Si le paquet est refuse et rejoue, il ne reste rien pour un
rejeu d'EMC ecrit autrement que mot pour mot (0,5 au lieu de 0,25) : ce surcout serait un ecart a dire, pas un poste a absorber. `PAR-11`
n'est pas appliquee a part : l'audit porte deja son +0,5.

---

## 6. Ce dont je reste la seule garde (C8)

**Aucun automate ne juge ces proprietes ; je les tiens en jouant.** L'equipe ne doit pas les croire couvertes par la CI.

- **Paquet d'`ART-CP-10`** : la regle 2 (le sujet ne suffit jamais) et le sujet de chaque oeuvre, qui ne se lit que sur l'image ; la
  plausibilite d'un distracteur au-dela de `B6` ; la regle 5 (indice et explication) ; la visibilite de chaque propriete a 176, 132-137 et
  84 px ; que l'enonce `B8` se lise comme une question sur la facon de faire ; `M1` a l'ecran (meme ecran, sans defilement), `M2`, `M3` et
  la marque de l'echec vue sur une reproduction pale et une sombre, `X2`, `X3`, le second cas de `X5`, `X6`, `E2` a l'ecran, les mots de
  `M4` et `M5`, la disposition "cote" (`Q8`), le rapprochement retrouve de memoire d'une manche a l'autre (`B3`).
- **EMC CM1** (inchange depuis s55) : `EMC-5`, `BIA-5` sur le sens d'un distracteur, la repartition des prenoms, l'exactitude des sources
  `HIS-3`, l'exactitude d'un indice contre son enonce, un indice qui contient un mot propre a la bonne reponse.
- **Exclusion CP** : qu'une bonne reponse de classement nomme ce que la situation est ; qu'un indice ecarte un choix qui est a l'ecran.

**Ce qu'un test peut mordre, si `theo-archi` le juge du** (C8 : un test, ou une ligne "non garde" dans le rapport du dev) : `B1` (garde-fou 2,
deja ecrit) ; `B2` (le texte alternatif identique sur tous les items) ; `B3` (un couple reference - bonne oeuvre unique, et une reference
unique) ; `B5` (le format se lit dans l'en-tete du fichier : aucune bonne oeuvre seule de son format parmi les trois propositions, ni seule a
partager le format de la reference) ; `B6` (`oeuvre-hippopotame` absente des propositions) ; `B7` (bornes 4 et 6) ; `B11` (cliquets exacts) ;
`B13` (plafond). Je ne choisis pas la forme du test.

---

## 7. Routage

| A qui | Quoi |
|---|---|
| Utilisatrice (via `sofia-sm`) | Section 2.3 : B a 4,75, issue (a), (b) ou (c), avant le document des oeuvres et avant le premier worktree du paquet |
| `sofia-sm` | Section 2 (le choix des oeuvres passe de A a B, A 3,75, B 4,75) ; amendement du planning ; section 2.4 si `S56-ARB-A` lit `PAR-2` autrement |
| `camille-po` | Section 0 (A9 : 3,5) ; `B1` a `B14` comme criteres de `S55-ART-CP-10-PAQUET` ; `N1` a `N6` du fichier de forme comme critere de `S56-ART-CP-10-ECHEC` ; story `S56-ART-CP-10-OEUVRES` passee sur B |
| `theo-archi` | `B4` (`AV2` decide si le Vermeer reste) ; section 6 (ce qu'un test peut mordre) |
| `lea-dev` | `B1` a `B13` ; elle recopie les textes, elle n'en redige aucun ; ligne "relu" ou "non relu" pour chacun dans `rapport-lea-dev.md` (C7) |
| Instance A | Section 2.4 ; section 4 (`PAR-13` pour son lot) |

## 8. Ce que ce document ne fait pas

- Il ne prononce aucun Done, ne touche ni fiche, ni matrice, ni backlog, ni YAML, ni `catalogue.yaml`, ni le registre d'identifiants.
- Il n'ecrit pas le document des oeuvres (`S56-ART-CP-10-OEUVRES`), qui attend la reponse de la section 2.3.
- Il ne releve aucune barre ecrite a s55 : `B5` applique le motif de `M1`, `B6` et `B8` sont des conditions neuves, datees d'aujourd'hui, posees
  avant que le paquet existe.
- Il ne cree aucun identifiant de condition ni de reserve.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.

---

## Bloc du 2026-10-09, 21:37 - reponse de l'utilisatrice, avis de `theo-archi`, capacite reconciliee avec l'instance A

Annotation datee ; rien au-dessus n'est efface. Arbre : `round-two` a `a772c822`, fusionne dans `s56-nadia-b`.

### R.1 La reponse de l'utilisatrice

*"Oui, a 4,75"* (2026-10-09, transmise par l'agent qui me lance, qui l'atteste ; je ne l'ai pas lue de sa main) : issue (a) de la section
2.3. Son ecart de 0,5 vaut quelle que soit la repartition ; `PAR-2` tenue, B a 4,75. Aucune reserve d'EMC ne part a s57. Le document des
oeuvres est ecrit : `squad/conformite/CHOIX-OEUVRES-s56-art-cp-10.md`.

### R.2 `B4` et `B7` : l'avis de `theo-archi` change ce que j'ai ecrit, comme je l'avais prevu

`B4` disait : les onze, sauf si `AV2` nomme une reproduction de provenance prouvee. `AV2` en nomme **28** (`1db6348f^`, restaurables a
l'octet). Le vivier des distracteurs est donc ces 28 ; la regle 2 ne bouge pas. Consequence : **le Vermeer reste**, avec
`oeuvre-autoportrait-leyster` en distracteur (une femme dans une piece, sans la lumiere d'une fenetre). Le paquet a **six items**, `B7` est
tenue par son haut. La phrase de la section 1.2 sur les deux items de Vermeer qui *"sortent"* est caduque.

### R.3 La capacite, reconciliee

| Poste | A | B |
|---|---|---|
| Arbitrage prealable | 0,5 | 0,5 |
| Lot `-13` | 1,0 | - |
| `ACV-CE1-R1` et `ACV-CE1-R2` | **0,5**, rejeu groupe (arbitrage de A, section 4) | - |
| `CLM-CE1-R1` (`PAR-14`) | 1,0 | - |
| `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` | - | 0,75 |
| Arbitrage de forme | - | 0,25 |
| Choix des oeuvres | 0,0 | 0,25 |
| Audit du paquet | - | 2,5 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| **Total** | **3,5** | **4,75** |

**Somme : 3,5 + 4,75 = 8,25.** C'est le chiffre juste. Mon 3,75 pour A (section 2.2) prenait les deux rejeux d'`ACV-CE1` au prix separe de
`DEC-0116` 2.1 (0,25 + 0,5 = 0,75) ; l'instance A les a tarifes ensemble a 0,5, et le prix de ses postes est le sien. Mon 3,75 et la somme de
8,5 qui en decoule sont donc faux d'aujourd'hui ; la section 2.2 se lit avec cette ligne. 8,5 reste le plafond que l'ecart accorde permet,
pas une allocation : les 0,25 entre 8,25 et 8,5 ne sont alloues a rien sans amendement (`DEC-0116` 2.4). Si `S55-ACV-CE1-R2` n'est pas
livree, l'instance A rejoue `ACV-CE1-R1` seule a 0,25 (sa section 4) : sa consommation descend, son engagement reste 3,5.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.
