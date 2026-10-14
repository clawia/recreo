# Arbitrage prealable - s56, instance A : francais CE1 (`FRA-CE1-13`), trois reserves, `PAR-2` sur le choix des oeuvres, `A9`, `A10`

`nadia-prof`, instance A, le 2026-10-09 (21:01, UTC+02:00, lu par `date`). Arbre lu : `dfcec37c` (sommet de `round-two`, branche
`s56-nadia-a`, lignee verifiee par `bash outils/verifier-lignee.sh`, sortie 0). Story : `S56-ARB-A` (`DEC-0116` 2, 3.1 ; backlog
"Sprint 56").

**Audit sans jeu, et je le declare.** `ce1-verbe-sujet` n'est pas branche (`grep -c "paquet: ce1-verbe-sujet$"` sur `catalogue.yaml`
rend 0) : rien n'est servi, je ne peux pas jouer. J'ai lu les 24 items du paquet et je les ai comptes par un script de poste, hors du
depot (methode en 2.1). Sources : `planning.md` de s56, `DEC-0116` et son amendement, backlog "Sprint 56" et "Cloture de s55",
`proposition-s56.md` de s55 (sections 4 et 7, `Q2`), mon arbitrage A de s55 et son complement, les fiches de `-14` et `-12`,
`ARBITRAGE-PREALABLE-s48-francais-ce1.md` (sections 2 et 5.4), mon arbitrage B de s55 et les trois resumes d'audit de s55, `PAR-1` et
`PAR-2` dans leur texte de s29.

**Ce document ne prononce aucun Done, aucun verdict, aucun statut de matrice.** Il n'alloue et ne renomme aucun identifiant de condition
ou de reserve : `J1` a `J5` (conditions de `-13`), `H1`, `H2` (recommandations), `P1` a `P6` (proprietes de `CLM-CE1-R1`) et `U1` (question
a l'utilisatrice) sont des reperes de ce fichier.

Convention des blocs : espace insecable (U+00A0) devant `?` et `:` et a l'interieur des guillemets francais, comme le paquet (`TYP-1`).
Chaque bloc se colle a la place de la ligne de meme role de l'item cite.

## Resume

| Point | Reponse | Prix sur A |
|---|---|---|
| (1) `PAR-13`, lot `-13` | **confirme**, pas `-15`. Cinq conditions avant branchement, `J1` a `J5` (section 2) | lot **1,0** |
| (2) `PAR-2`, choix des oeuvres distractrices | **sur B, pas sur A** (`PAR-1` et `PAR-2`). B passe de 4,5 a **4,75**, sous le plafond dur de 5,0 ; question `U1` | **0,0** (0,25 sur B) |
| (3) Rejeu groupe `ACV-CE1-R1` + `ACV-CE1-R2` | **oui, 0,5** au lieu de 0,75, a une condition (section 4) | **0,5** |
| (4) `CLM-CE1-R1` | **`PAR-14` s'applique**, 24 items sur 24 : **1,0**. Proprietes `P1` a `P6` (section 5) | **1,0** |
| (5) `A9`, capacite de B a s55 | **3,5 vaut** (consomme). Le budget engage etait 3,75 ; mon "4,0 sur 4,0" compte deux fois 0,25 (section 6) | - |
| (6) `A10`, s54 contre les formes (a), (b), (c) | **non faite, non engagee a s56** ; due avant toute `DEC` qui propose une troisieme instance, donc avant celle de s57 si `Q3` est acceptee avec B (section 7) | 0,0 ; **0,5** quand elle est engagee |
| Cet arbitrage | 24 items lus, cinq blocs de texte d'enfant, six points de `Q2` et de la retro | **0,5** |
| Marge de rejeu, allouee a aucun lot | paie un refus au premier passage de `-13` ou de `CLM-CE1-R1` | **0,5** |
| **Total A** | `PAR-9` tenue ; deux lots sur trois (`PAR-8`) | **3,5 / 4,0** ; 0,5 non alloue |

---

## 0. Trois corrections de mes propres constats, avant de m'en servir

**0.1 `VSJ-CE1-1` a `-3` ne nomment pas la majuscule de debut de phrase.** Le paquet tient mes trois conditions telles que je les ai
ecrites a s48 (section 2.1). Mais sujet toujours en tete (`VSJ-CE1-2`) et propositions copiees de la phrase (`VSJ-CE1-1`) donnent
ensemble ceci : sur 12 items de sujet sur 15, **la bonne reponse est la seule proposition qui porte une majuscule**. C'est un trou de ma
condition de s48, comme celui que j'ai corrige a s55 (arbitrage A, 0.1). Je ne durcis pas `VSJ-CE1-1` a `-3`, que le paquet tient : j'ecris
`J1` comme une condition neuve, datee d'aujourd'hui, posee avant le premier audit de ce paquet. Son fondement est l'ancrage (etape 2 de
ma methode) : sur ces 12 items, l'enfant trouve la reponse sans chercher le verbe ni son sujet, l'item ne travaille donc pas `FRA-CE1-13`.
Le principe est celui de `NAT-1` et de `CLM-CE1-3` : aucun signe de surface ne repond a la place de l'enfant. Et `FRA-CE1-10` apprend
precisement a l'enfant de CE1 qu'une majuscule ouvre la phrase.

**0.2 Ma propriete de `CLM-CE1-R1` ne donne pas de test pour le determinant.** La fiche de `-12` (section 4) donne un test pour le verbe,
le nom propre, le nom commun et l'adjectif, et aucun pour le determinant, qui est la bonne reponse de 5 items sur 24. Je le complete
en 5.1 ; la propriete ne change pas.

**0.3 Mon total de B a s55 compte deux fois 0,25.** Section 6.

---

## 1. `PAR-13` sur `-13` : confirme

Je juge `PAR-13` au niveau de la matiere (arbitrage A de s55, 1.1) : le francais CE1 n'est pas un terrain neuf, et le terrain neuf de s56
est la forme d'`ART-CP-10`, sur B. **`-13` est cousin de deux lignes servies et validees a s55** : de `-12` par la frontiere
(`VSJ-CE1-1` : *"la, on nomme la classe d'un mot designe ; ici, on trouve un mot dans la phrase"* ; les items de verbe demandent de
reconnaitre un verbe parmi des mots de la phrase) et de `-14` par le sujet place juste avant le verbe (`ACV-CE1-1`, ici `VSJ-CE1-2`).
J'avais ecrit a s55 que la frontiere `-12` / `-13` se juge sur le paquet de `-13` contre `-12` deja servi : c'est maintenant.

**Je ne le remplace pas par `-15`.** `-15` est cousin de `-14` et rien ne le rend plus pret ; `-13` est l'ordre que j'avais demande.
`-08` et `-09`, sans cousine, restent hors de ce sprint, ou une autre instance audite la forme d'`ART-CP-10`.

---

## 2. Conditions de `ce1-verbe-sujet`, `FRA-CE1-13`

### 2.1 Lecture des 24 items

**Methode** : lecture des 24 items dans le fichier ; comptes par un script de poste (scratchpad hors depot) qui lit `enonce`,
`bonneReponse`, `distracteurs` et `indice`, prend la phrase citee entre guillemets et compte : la place de la bonne reponse dans la
phrase, les propositions qui commencent par une majuscule, la plus longue proposition, les prenoms par occurrence, et si un indice
contient une proposition de son item.

| Ce qui est tenu | Compte |
|---|---|
| `VSJ-CE1-1` | 15 items de sujet (items 1, 2, 4, 5, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18, 20), 9 de verbe ; propositions copiees de la phrase sur les 24 |
| `VSJ-CE1-2` | pronom 5 (1, 2, 4, 5, 6), nom seul 5 (8, 9, 10, 12, 13, tous des prenoms), groupe nominal de trois mots au plus 5 (14, 16, 17, 18, 20) : chacun au-dessus de 24 / 5 ; sujet avant le verbe sur les 15 ; un seul verbe conjugue ; aucun sujet coordonne |
| `VSJ-CE1-3` | items de sujet : (a) le groupe apres le verbe et (b) un autre nom de la phrase, les deux apres le verbe ; items de verbe : un nom ou un adjectif de la phrase ; familles nommees a cote de chaque item |
| `TRV-CE1-1`, `-3`, `-4`, `-7` | niveau CE1, une ligne, aucun texte support, aucun champ a voix haute ; aucun mot exclu ; 24 items |
| `TRV-CE1-2` | trois propositions distinctes, une seule acceptable ; la bonne reponse est strictement la plus longue sur 6 items (15, 19, 20, 21, 23, 24), sous la moitie |
| `TRV-CE1-5` | six indices qui tournent, douze mots au plus, aucun ne contient une proposition de son item, aucun renvoi a l'ecoute |
| `LON-CE1-1`, `-2`, `-5` | enonce de 13 mots au plus a ma lecture ; propositions de trois mots au plus |
| Exactitude | **les 24 sujets et les 24 verbes sont justes** ; *porte* (15) et *souffle* (21) ont un homonyme nom, la phrase departage |

| Ce qui n'est pas tenu | Compte | Condition |
|---|---|---|
| La majuscule designe la reponse | sur **12** items de sujet sur 15, la bonne reponse est la seule proposition a majuscule (`grep -c 'bonneReponse: "[[:upper:]]'` rend 15 : les 15 sujets, aucun verbe) | `J1`, `J2` |
| `TRV-CE1-6`, prenoms | feminins **7** (Mia 3, Lea 2, Ines, Sofia), masculins **5** (Karim 2, Tom 2, Noe) : ecart de 2, la barre est *"a un pres"* ; et ranger va deux fois du cote feminin (items 5 et 7) | `J3` |
| Vocabulaire | *"du verbe cité"*, 5 indices (items 1, 5, 9, 13, 17) : au CE1, *la cité* est un lieu ; *citer* n'est pas un mot que l'enfant lit seul | `J4` |
| Consigne | l'entree n'existe pas ; chaque enonce porte sa question | `J5` |

### 2.2 `J1` - la majuscule ne designe jamais la reponse (bloquante)

**Regle** : une proposition copiee du debut de la phrase perd la majuscule que lui donnait cette place ; un prenom garde la sienne.
C'est ainsi qu'on cite un groupe hors de sa phrase ; la phrase de l'enonce, elle, garde sa majuscule. Dix lignes `bonneReponse`
changent, rien d'autre dans ces items :

| Item | Avant | Apres |
|---|---|---|
| 1 | `Elle` | `elle` |
| 2 | `Ils` | `ils` |
| 4 | `Il` | `il` |
| 5 | `Elles` | `elles` |
| 6 | `Il` | `il` |
| 14 | `Le chat` | `le chat` |
| 16 | `Les oiseaux` | `les oiseaux` |
| 17 | `Le facteur` | `le facteur` |
| 18 | `La maîtresse` | `la maîtresse` |
| 20 | `Un grand bus` | `un grand bus` |

Reste le prenom sujet : sur les items 9, 10, 12 et 13, il serait encore la seule proposition a majuscule. `J2` donne a chacun un prenom
parmi les distracteurs. L'item 8 en a deja un (*Karim*).

**Dans le meme commit**, une ligne d'en-tete, a la suite de celle de `VSJ-CE1-3` :

```yaml
# Majuscule : une proposition copiee du debut de la phrase perd sa majuscule, un prenom garde la sienne ; sur aucun item
# la bonne reponse n'est la seule proposition a majuscule.
```

### 2.3 `J2` - items 9, 10, 12, 13 (bloquante, partie de `J1`)

Le distracteur (b) devient un prenom, a qui l'on donne ou montre quelque chose : il ne fait pas l'action, aucun des indices de ces
items (*"qui fait l'action"*, *"Qui est-ce qui"*, *"les mots devant"*) n'est donc satisfait par lui. Un prenom apres *avec* aurait ete
un co-agent : je l'ai ecarte pour cette raison. Chaque bloc remplace les lignes `enonce` et `distracteurs` de l'item ; `bonneReponse`
et `indice` (apres `J4`) ne changent pas.

```yaml
  - enonce: "« Tom prête son livre à Lina. » Quel est le sujet du verbe « prête » ?"
    distracteurs: ["son livre", "Lina"]
```

(item 9)

```yaml
  - enonce: "« Inès montre un dessin à Sami. » Quel est le sujet du verbe « montre » ?"
    distracteurs: ["un dessin", "Sami"]
```

(item 10)

```yaml
  - enonce: "« Noé écrit une carte à Awa. » Quel est le sujet du verbe « écrit » ?"
    distracteurs: ["une carte", "Awa"]
```

(item 12)

```yaml
  - enonce: "« Sofia ouvre la porte à Hugo. » Quel est le sujet du verbe « ouvre » ?"
    distracteurs: ["la porte", "Hugo"]
```

(item 13). Treize mots chacun (`LON-CE1-1`). Qui donne a qui : un garcon a une fille, une fille a un garcon, deux fois chacun.

### 2.4 `J3` - item 7 (bloquante, `TRV-CE1-6`)

```yaml
  - enonce: "« Noé range ses crayons. » Quel est le verbe de la phrase ?"
    distracteurs: ["crayons", "Noé"]
```

Apres `J2` et `J3`, prenoms en occurrences dans les phrases citees : feminins **8** (Mia 3, Lea, Ines, Sofia, Lina, Awa), masculins **8**
(Karim 2, Tom 2, Noe 2, Sami, Hugo) ; onze prenoms distincts, d'origines variees. Ranger va une fois de chaque cote (*elles rangent*,
*Noe range*).

### 2.5 `J4` - *cité* (bloquante, vocabulaire)

Sur les items 1, 5, 9, 13 et 17, la ligne `indice` devient :

```yaml
    indice: "Demande-toi : qui fait l'action de ce verbe ?"
```

Huit mots. Il dit ou regarder ; aucun distracteur de ces cinq items ne fait l'action.

### 2.6 `J5` - la consigne du jeu

Chaque enonce porte sa question ; la consigne est donc generique. Celle que l'enfant lit deja sur cinq entrees du catalogue, mot pour mot :

```yaml
    consigne: "Lis bien, puis choisis la bonne réponse."
```

Le titre est libre ; je propose *"Le verbe et son sujet"*, sans en faire une condition.

### 2.7 `H1`, `H2` - non bloquantes

- **`H1`** : item 3, *"Le petit chat boit du lait."*, indice *"Cherche le mot qui dit ce que fait quelqu'un."* : un chat n'est pas
  *quelqu'un*. Recommande : l'indice de l'item 21, *"Cherche le mot qui dit ce qui se passe."*. Pas une reserve si non pris.
- **`H2`, limite que je nomme, pas une barre** : sur les 15 items de sujet, le sujet est le premier groupe de la phrase, parce que
  `VSJ-CE1-2` le met toujours avant le verbe et qu'aucun complement n'ouvre la phrase. Apres `J1`, l'enfant doit encore lire la phrase ;
  mais il peut repondre "le premier groupe" sans trouver le verbe. **Je ne deplace pas ma barre** : `VSJ-CE1-2` n'exige aucun complement
  en tete, et je l'ai refuse a s48 pour la longueur (section 1.1). Si le jeu montre que la position seule suffit, j'ouvrirai une reserve
  neuve et datee.

### 2.8 Les criteres du lot, en proprietes decidables

Pour l'amendement de `S56-BR-FRA-CE1-13` par `camille-po`. Fichier : `adapters/adapter-content/src/main/resources/contenu/ce1-verbe-sujet.yaml`
(note `Y`). Valeurs du 2026-10-09 sur `dfcec37c`. Les proprietes 2 a 4, 8 et 9 se portent dans `PaquetCe1VerbeSujetTest`.

| # | Propriete | Commande ou porteur | Aujourd'hui | Cible |
|---|---|---|---|---|
| 1 | Bonnes reponses a majuscule : les prenoms sujets seulement | `grep -c 'bonneReponse: "[[:upper:]]' Y` | 15 | 5 (items 8, 9, 10, 12, 13) |
| 2 | Sur aucun item la bonne reponse n'est la seule des trois propositions a commencer par une majuscule | test du paquet | 12 | 0 |
| 3 | Chaque proposition, casse de la premiere lettre ignoree, est une suite de mots de la phrase citee | test du paquet | 24 | 24 |
| 4 | Prenoms des phrases citees, par occurrence : ecart feminins / masculins au plus 1 ; le test porte la liste des prenoms et leur genre | test du paquet | 7 / 5 | 8 / 8 avec les textes de `J2`, `J3` |
| 5 | Plus de *cité* dans un indice | `grep -c 'verbe cité' Y` ; `grep -c "qui fait l'action de ce verbe" Y` | 5 ; 0 | 0 ; 5 |
| 6 | Les cinq enonces de `J2` et `J3` sont dans le paquet mot pour mot, espaces insecables comprises | `grep -cF` de chaque ligne `enonce` des blocs | 0 chacune | 1 chacune |
| 7 | Consigne de l'entree | `grep -c 'consigne: "Lis bien, puis choisis la bonne réponse."' catalogue.yaml` | 5 | 6 |
| 8 | `VSJ-CE1-1`, `-2` : au moins 12 items de sujet ; pronom, nom seul, groupe nominal chacun dans au moins 5 items ; sur chaque item de sujet, la bonne reponse est avant le verbe cite et les deux distracteurs apres | test du paquet | 15 ; 5, 5, 5 ; 15 sur 15 | inchange |
| 9 | Aucun indice ne contient une proposition de son item (mot entier, casse ignoree) : lecon d'`ACV-CE1-R1` | test du paquet | 0 | 0 |
| 10 | 24 items | `grep -c '^ *- enonce:' Y` | 24 | 24 |
| 11 | `TRV-CE1-2` : bonne reponse strictement la plus longue sur 12 items au plus | test du paquet | 6 | au plus 12 |

`LON-CE1-1`, `-2`, `-5` sont deja tenues par les garde-fous de longueur. Le perimetre C2 du branchement compte `ce1-verbe-sujet.yaml` :
la correction est decidee ici.

#### Amendement du 2026-10-14 (`nadia-prof`, s57, palier 1) : propriete 8, pour lever `VSJ-CE1-R1`

Annonce dans la fiche (`FICHE-bandit-manchot-verbe-sujet-ce1.md`, section 5) : *"ma propriete 8 [...] est amendee par moi sur ces
items quand la story s'ecrit"*. La ligne 8 du tableau ci-dessus reste ecrite ; elle se lit desormais ainsi :

| # | Propriete, amendee | Porteur | Aujourd'hui (`3a3ea97e`) | Cible |
|---|---|---|---|---|
| 8 | `VSJ-CE1-1`, `-2` : au moins 12 items de sujet ; pronom, nom seul, groupe nominal chacun dans au moins 5 items ; sur chaque item de sujet, la bonne reponse est avant le verbe cite, **au moins un distracteur est apres le verbe** (famille (a) de `VSJ-CE1-3`) et **au plus un est avant** ; **sur au moins 3 items de sujet, un distracteur est avant le verbe cite** | test du paquet | 15 ; 5, 5, 5 ; 0 item avec un distracteur avant | 3 items au moins |

**Ce qui va avec, et qui n'est pas une barre neuve** (chaque point applique une condition deja ecrite) :

1. **Le distracteur place avant le verbe est un complement en tete de phrase, de temps ou de lieu, suivi d'une virgule** (*"ce
   matin"*, *"dans la cour"*). Jamais une personne ni un animal : il ferait mentir l'indice *"qui fait l'action"* (`J4`, 2.5) ou
   ferait un sujet coordonne (`VSJ-CE1-2`). La virgule est decidable ; *"de temps ou de lieu"* est a moi, au rejeu.
2. **Sur ces items, l'indice n'est pas *"Le sujet est avant le verbe : regarde les mots devant."*** Deux propositions sont alors
   devant le verbe : l'indice serait satisfait par un distracteur, ce que `J4` exclut (2.5 ; section 10, point 1). *"Demande-toi : qui
   fait l'action de ce verbe ?"* et *"Pose la question « Qui est-ce qui ? » avant le verbe."* conviennent. Decidable : test du paquet.
3. **`J1` reste tenue** (propriete 2) : le complement copie du debut de la phrase perd sa majuscule (*"ce matin"*), comme le dit deja
   l'en-tete du paquet. Un prenom sujet seul a majuscule la rendrait rouge : le sujet de ces items est un pronom ou un groupe nominal,
   ou bien l'autre distracteur est un prenom.
4. **Le plancher de 5 par forme de sujet et la propriete 4 restent tenus.** Le mot a majuscule qui ouvre le complement (*"Ce"*) n'est
   pas un prenom : la liste des ouvertures du test le recoit, **lui seul**, et la regle des prenoms ne change pas.
5. **Le jeu fautif de la propriete 8** (`ADR-0013`, obligation 3) doit rester refuse par la propriete amendee : un seul distracteur
   avant le verbe y devient permis. Sa forme est au developpeur.

**Correction de mon propre exemple, avant que quiconque s'en serve.** Mon critere de levee cite *"« Ce matin, Léa lit un livre. »,
propositions Léa, ce matin, un livre"*. **Il contredit ma propre `J1`** : *Léa* y est la seule proposition a majuscule. Il retire en
plus un pronom sujet (plancher) et fait lire *"Ce"* hors des listes du test (`lecture-testabilite.md` de s57, 2.1, `Q-Y2` : 5 echecs
sur 23). **C'etait une illustration, pas un texte a servir** ; elle est retiree comme modele et reste ecrite dans la fiche, pour que
l'erreur se lise. Exemple qui tient, applique a l'item 1 dans un worktree jetable sur `3a3ea97e` :

```yaml
  - enonce: "« Ce matin, elle lit un livre. » Quel est le sujet du verbe « lit » ?"
    bonneReponse: "elle"
    distracteurs: ["ce matin", "un livre"]
```

`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1VerbeSujetTest` : 22 tests, **2 echecs, les deux
attendus** - l'ancienne propriete 8 (*"le distracteur 'ce matin' est après le verbe"*) et la propriete 4 (*"'Ce' [...] est une majuscule
hors des listes"*, point 4). `J1`, le plancher, les proprietes 3, 9, 11 et `LON-CE1-1` restent vertes. JDK 21.0.10 (`java.version` du
rapport). Les textes restent au developpeur ; je les juge en rejouant (0,5, `PAR-10`).

**Ce que cet amendement ne fait pas** : il ne touche ni `VSJ-CE1-1` a `-3`, ni `J1` a `J5`, ni les 12 items de sujet non concernes,
dont l'indice *"regarde les mots devant"* reste permis. Le test qui porte la propriete 8 change dans le meme commit que les textes
(backlog, `S56-VSJ-CE1-R1`). Aucun identifiant neuf.

### 2.9 Prix

**1,0** : premier passage complet d'un paquet jamais audite, tarif de lot (s28, 4.3). `J1` a `J5` le rendent plus court, pas moins cher.

---

## 3. `PAR-2` et le choix des oeuvres distractrices : sur B

### 3.1 Lecture

`PAR-2`, dans son texte de s29 : *"Une matiere et un niveau ne sont jamais partages, ni dans un sprint ni entre deux lots du meme
sprint."* `PAR-1` : *"Un lot n'est jamais partage entre deux instances. [...] chaque moitie tenant la condition que le tout ne tient
pas."* B porte la forme (0,25) et l'audit du paquet (2,5) : les arts plastiques CP sont a B a s56. Le choix des oeuvres applique mes regles
2 et 3 (arbitrage A de s55, 3.4 : *"le sujet ne suffit jamais"*, *"le format ne designe pas la bonne oeuvre"*), et l'audit juge ces memes
regles sur le paquet. Choix sur A et audit sur B, c'est une regle appliquee par une instance et jugee par l'autre : la frontiere tenue
par moitie que j'ai refusee a s55 pour `ART-RES` (arbitrage A, 2.3). **Le choix des oeuvres va a B.** C'est aussi ma lecture de s55 : le
choix etait *"sur le reste de A dans l'issue (b)"*, c'est-a-dire chez l'instance qui portait alors toutes les arts plastiques CP.

**Ce qui ne change pas** : le prix (0,25), la story (`S56-ART-CP-10-OEUVRES`), les regles (3.4 de mon arbitrage A de s55, inchangees), l'ordre
(avant la premiere ligne du paquet). Le prix de l'audit reste 2,5 : son 1,5 *"dont je ne suis pas la source"* vise les items ecrits par
`lea-dev`, et le choix etait deja de moi a s55, quelle que soit l'instance.

### 3.2 Consequences sur le plafond de B

| | A | B | Somme |
|---|---|---|---|
| `DEC-0116` A.1 (choix sur A, rejeu separe) | 4,0 | 4,5 | 8,5 |
| Choix sur B, rejeu separe | 3,75 | **4,75** | 8,5 |
| Choix sur B, rejeu groupe (section 4) | **3,5** | **4,75** | **8,25** |

- **Plafond dur de 5,0** (`PAR-8`) : B a 4,75, tenu. Lots : un sur B (l'audit), sous trois.
- **`PAR-9`** : B depasse 4,0 de 0,75 ; la somme reste sous les 8,5 que l'ecart accorde permet. `DEC-0116` A.1 ventile l'ecart en "B a 4,5"
  et dit que B a 4,75 demande une question de plus : c'est **`U1`**, section 9.
- **La marge de B ne paie pas un refus de l'audit** : elle vaut 0,5, l'audit 2,5. C'etait deja vrai a 4,5.
- **Si l'utilisatrice dit non**, deux issues que je n'ecris pas : B absorbe 0,25 dans le prix de son arbitrage prealable (son chiffre,
  pas le mien) ; ou une reserve d'EMC de B (0,25) va a s57, premier report compte. **Ramener le choix sur A n'en est pas une** : c'est
  `PAR-1` et `PAR-2`.

### 3.3 `PAR-2` et `FRA-CM1-01` (point de `Q2`)

`PAR-2` compte une matiere **et** un niveau : le francais CM1 n'est pas le francais CE1. `FRA-CM1-01` peut aller a A ou a une autre
instance ; `PAR-2` exige seulement que le francais CM1 ne soit pas partage dans un sprint. Sans objet a s56 : aucun lot de
`cm1-lire-et-restituer` n'est engage, et la mesure de pli de `LIS-2` n'est pas faite.

---

## 4. Rejeu groupe de `ACV-CE1-R1` et `ACV-CE1-R2` : 0,5

**Oui, 0,5 au lieu de 0,75.** Les deux reserves sont sur le meme paquet. Le rejeu de `ACV-CE1-R2` rejoue le paquet entier (au moins trois
items changes, a juger contre `ACV-CE1-1`, `TRV-CE1-2` et la barre de s54 sur les indices des items changes). Le texte de `ACV-CE1-R1`
est deja applique (`2b66a441`) : verifier l'item 6 dans ce meme rejeu ne coute rien de plus. Les items 3 et 15 (*"combien en voit-on ?"*),
a reprendre au meme passage, n'ont pas de prix propre.

**Condition** : les deux se rejouent sur le meme arbre, qui contient `2b66a441` et le commit de `S55-ACV-CE1-R2`. **Si `S55-ACV-CE1-R2`
n'est pas livre** a la cloture, je rejoue `ACV-CE1-R1` seule, a 0,25, avant la cloture. La difference de 0,25 n'est allouee ailleurs
que par amendement (`DEC-0116` 2.4).

---

## 5. `CLM-CE1-R1` : `PAR-14`, 1,0

**`PAR-14` s'applique** : les 24 indices changent, 24 items sur 24. Tarif d'un lot, **1,0**. Je rejuge chaque indice contre ses deux
distracteurs, en jouant.

### 5.1 Le test du determinant (correction 0.2)

Propriete de la fiche, inchangee : un indice par item qui donne un test de classe, qu'aucun distracteur de son item ne passe, douze mots au
plus, jamais la classe nommee. Le test qui manquait, pour les 5 items de determinant (5, 10, 15, 20, 24) : **remplacer le mot par un
autre petit mot (*un*, *une*, *le*, *la*, *des*) garde une phrase correcte**. Un adjectif ou un nom ne passe pas ce test. Les 24 textes
restent au developpeur.

### 5.2 Proprietes decidables

Fichier : `adapters/adapter-content/src/main/resources/contenu/ce1-classes-de-mots.yaml` (note `Y`). Valeurs du 2026-10-09 sur `dfcec37c`.

| # | Propriete | Commande ou porteur | Aujourd'hui | Cible |
|---|---|---|---|---|
| `P1` | Les quatre anciens textes ont disparu | commande du backlog (`S55-CLM-CE1-R1`) | 24 | 0 |
| `P2` | Un indice par item | `grep -c '^ *indice:' Y` | 24 | 24 |
| `P3` | Douze mots au plus par indice (convention d'`ADR-0089`) | test du paquet | 24 sur 24 | 24 sur 24 |
| `P4` | Aucun indice ne nomme la classe de sa bonne reponse ; pour *nom commun* et *nom propre*, aucun ne contient le mot *nom* | test du paquet | 0 en faute | 0 |
| `P5` | Aucun mot exclu par `TRV-CE1-4` dans un indice | commande `P5` ci-dessous | 0 | 0 |
| `P6` | Le diff du paquet ne touche que des lignes `indice:` et des commentaires (dont les deux corrections d'`E3` de la fiche, section 3) | commande `P6` ci-dessous | sans objet | ne rend rien |

Commandes, ecrites hors du tableau parce qu'elles portent des barres verticales :

```text
P5 : grep -ciE '^ *indice:.*(graphème|phonème|préfixe|suffixe|radical|participe|auxiliaire|complément|antonyme|homophone|inférence)' Y
P6 : git diff round-two -- Y | grep -E '^[-+] ' | grep -vE '^[-+] *(indice:|#)'
```

**Non decidable, a moi seule** : que le test donne soit vrai pour la bonne reponse, qu'aucun des deux distracteurs ne le passe, et qu'un
enfant de CE1 le lise seul.

---

## 6. `A9` : la capacite de B a s55, 3,5 ou 4,0

**3,5 vaut, comme consomme. Le budget engage etait 3,75. Le "4,0 sur 4,0" est le mien, instance B de s55, et il est faux.**

| Poste de B a s55 | Source | Prix |
|---|---|---|
| Arbitrage prealable | arbitrage B de s55, section 6 | 0,5 |
| `EXC-R15` | idem | 0,25 |
| `EMC-CM1-03` | idem | 1,0 |
| `EMC-CM1-06` | idem | 1,5 |
| `ART-RES` | passe a A par l'issue (b), retenue (`DEC-0115` A.1 ; complement de A, section 1) ; consomme par A | 0,0 sur B |
| **Alloue** | | **3,25** |
| Marge, allouee a aucun lot | | 0,5 |
| **Budget engage** | | **3,75** |
| Consomme sur la marge : ecart de l'item 4 de `-03` | `audit-nadia-b-emc.md`, *"0,25, sur la marge"* | 0,25 |
| **Consomme** | 3,25 + 0,25 | **3,5** |

**Ou est l'erreur.** `audit-nadia-b-emc.md` compte l'ecart de l'item 4 comme un poste (0,25) **et** compte la marge entiere (0,5), alors que
cet ecart etait paye *sur* la marge ; `audit-nadia-b-exc.md` reprend ce total (*"B passe de 3,75 a 4,0"*). 0,25 y est compte deux fois.
L'addition de `camille-po` (backlog, "Cloture de s55") est juste comme consomme. La marge de B a servi a 0,25 sur 0,5.

`A9` est portee par la ligne de `S56-ARB-B` (`DEC-0116` 6). Je l'ecris ici parce que l'agent qui m'a lance me la pose. Les lignes du
tableau sont lisibles par l'instance B ; **si `S56-ARB-B` ecrit un autre chiffre, les deux calculs sont a comparer avant que `sofia-sm`
en porte un a la review**.

---

## 7. `A10` : s54 contre les formes (a), (b), (c)

**Non faite.** `DEC-0116` 6 : non engagee a s56, *"due avant toute `DEC` qui propose une troisieme instance"*, jamais engagee, donc pas un
report. Je ne la fais pas ici : sans story ni budget, elle ne serait pas allouee (`PAR-16`).

**Ce que s56 change a sa date.** `Q3` propose deux instances de francais CE1 a partir de s57. **Avec B, cela fait trois instances.** Si
l'utilisatrice accepte `Q3` sans retirer B, `A10` est due avant la `DEC` d'ouverture de s57. Ma condition (ii) se lit sur le dernier sprint
parallele clos, qui est s54 (complement de A de s55, 2.5).

**Prix : 0,5.** s52 avait deux couloirs, quatre transcriptions, une couture a lire : 0,25. s54 en a trois, donc trois coutures. A s56, A a 0,5
non alloue (section 8) : `A10` y tiendrait sous `PAR-9` (3,5 + 0,5 = 4,0), sans ecart, **par un amendement de `DEC-0116`** (2.4). C'est a
`camille-po`. **Ce qui plaide pour s56** : les transcriptions de s54 vivent hors du depot, et le depot ne les garde pas.

---

## 8. Tarifs de l'instance A

| Poste | Story | Prix |
|---|---|---|
| Cet arbitrage | `S56-ARB-A` | **0,5** |
| Lot `-13` | `S56-BR-FRA-CE1-13` | **1,0** |
| `ACV-CE1-R1` et `ACV-CE1-R2`, rejeu groupe | `S55-ACV-CE1-R1`, `S55-ACV-CE1-R2` | **0,5** (`ACV-CE1-R1` seule 0,25 si `S55-ACV-CE1-R2` n'est pas livree) |
| `CLM-CE1-R1`, tarif de lot | `S55-CLM-CE1-R1` | **1,0** |
| Choix des oeuvres distractrices | `S56-ART-CP-10-OEUVRES` | **0,0 sur A** (0,25 sur B, section 3) |
| Marge de rejeu, allouee a aucun lot | aucune | **0,5** |
| **Total** | | **3,5 / 4,0** ; 0,5 non alloue, que rien n'emploie sans amendement |

Lots : deux sur trois (`-13`, `CLM-CE1-R1` au tarif de lot). `PAR-11` : non tranchee, non appliquee. La marge paie **un** refus au premier
passage de `-13` ou de `CLM-CE1-R1`, pas deux.

---

## 9. Ce qui demande une decision de l'utilisatrice

- **`U1`** : le choix des oeuvres passe sur B (`PAR-1`, `PAR-2`). B est a **4,75**, A a **3,5**, somme **8,25** sur 8,0. Son "Ecart de 0,5"
  couvre-t-il B a 4,75, la somme restant sous 8,5 ? A poser **avant l'ouverture du premier worktree du paquet** (`DEC-0116` A.1). Sans
  reponse : issues de 3.2, a B et a `camille-po`.
- **`Q3`** (deja ouverte, pas neuve) : si elle est acceptee avec B, elle fait trois instances a s57, et `A10` devient due avant la `DEC`
  de s57 (section 7). Mon accord sur `PAR-2` pour deux instances de francais CE1 n'est pas donne ici.

Le reste (`J1` a `J5`, `P1` a `P6`, le rejeu groupe, `A9`, l'emploi des 0,5 non alloues de A) ne lui est pas pose.

---

## 10. Ce dont je reste la seule garde (`C8`)

Aucun garde-fou ne lit, et l'equipe ne doit pas les croire couverts par la CI :

1. **`-13`** : qu'un indice donne un critere satisfait par un distracteur (un prenom co-agent ferait mentir *"qui fait l'action"*) ; la
   justesse grammaticale de chaque sujet et de chaque verbe ; la plausibilite des distracteurs ; l'accessibilite d'un mot au CE1
   (`J4` est un cas trouve en lisant) ; les roles et activites par genre (le test de 2.8 compte les prenoms, pas ce qu'ils font) ;
   la position seule comme raccourci (`H2`) ; le rendu des propositions en minuscule a l'ecran.
2. **`CLM-CE1-R1`** : la verite du test de classe pour la bonne reponse, et qu'aucun distracteur ne le passe.
3. **`ACV-CE1-R2`** : qu'une forme soit du premier groupe, tant que le test que `yanis-qa` ecrit avec `marc-dev` n'existe pas ; et la barre
   de s54 sur les indices des items changes.
4. **`ART-CP-10`, choix des oeuvres** : *"le sujet ne suffit jamais"* et *"le format ne designe pas la bonne oeuvre"*, a l'oeil, sur B.

Ce que les tests de paquet peuvent tenir : les proprietes 1 a 11 de 2.8 et `P1` a `P6` de 5.2.

---

## 11. Regles que les rapports des developpeurs relisent (`C7`)

`rapport-marc-dev.md` porte, par poste, "relu" ou "non relu" pour :

| Poste | Regles |
|---|---|
| `S56-BR-FRA-CE1-13` | `J1` a `J5` ; `TRV-CE1-5` avec la barre de s54 (1.2) ; `TRV-CE1-6` ; `VSJ-CE1-1` a `-3` |
| `S55-ACV-CE1-R2` | le critere de la fiche, mot pour mot ; `TRV-CE1-5` avec la barre de s54 sur chaque item change ; `ACV-CE1-1`, `TRV-CE1-2` |
| `S55-CLM-CE1-R1` | la propriete de la fiche (section 4) et 5.1 ; `TRV-CE1-4`, `TRV-CE1-5` |

---

## 12. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Amendement de `S56-BR-FRA-CE1-13` : `J1` a `J5` et le tableau de 2.8, **avant le worktree de `marc-dev`** ; `S56-ART-CP-10-OEUVRES` passe sur B (story inchangee) ; `P1` a `P6` au critere de `S55-CLM-CE1-R1` ; l'emploi des 0,5 non alloues de A, dont `A10` (section 7) |
| `sofia-sm` | A a 3,5 ; B a 4,75 si le choix des oeuvres y passe ; `U1` a l'utilisatrice avant le premier worktree du paquet ; `A9` a comparer avec `S56-ARB-B` |
| Instance B | Le choix des oeuvres est a elle (section 3) ; ses regles sont la section 3.4 de mon arbitrage A de s55 ; echeance du planning : avant la premiere ligne du paquet |
| `marc-dev` | `J1` a `J5` dans le commit de branchement de `-13` ; `H1` s'il le prend ; rapport selon la section 11 |
| `yanis-qa` | Proprietes 2, 3, 4, 8, 9, 11 de 2.8 et `P3`, `P4` de 5.2 : lesquelles un test de paquet peut porter |

## 13. Ce que ce document ne fait pas

- Il ne prononce aucun Done, ne touche ni une fiche, ni la matrice, ni le registre d'identifiants, ni un YAML, ni le catalogue. Les textes de
  la section 2 sont a appliquer par `marc-dev`, puis rejuges par moi en jouant.
- Il ne rouvre ni `VSJ-CE1-1` a `-3`, ni `TRV-CE1-1` a `-7` : `J1` est une condition neuve et datee (0.1), `J3` applique `TRV-CE1-6`.
- Il ne choisit aucune oeuvre et n'ecrit pas `S56-ART-CP-10-OEUVRES` : c'est a B.
- Il ne fait pas `A10`, ne tranche pas `Q3` ni `Q4`, et ne donne pas mon accord sur `PAR-2` pour deux instances de francais CE1.
- Il n'alloue ni ne renomme aucun identifiant de condition ou de reserve.

**Signe** : `nadia-prof`, s56, instance A, 2026-10-09.
