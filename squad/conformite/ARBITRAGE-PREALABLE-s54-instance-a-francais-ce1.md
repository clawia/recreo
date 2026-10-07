# Arbitrage prealable - s54, instance A : francais CE1 (`FRA-CE1-18`, `FRA-CE1-19`, `SYC-CE1-R1` et item 16 de `FRA-CE1-25`), passe `PAR-4`

`nadia-prof`, instance A, le 2026-10-07 (21:25, UTC+02:00, lu par `date`). Arbre lu : `dc2870eb` (tip de `round-two`,
branche `s54-nadia-instance-a`).

**Audit sans jeu.** Aucun des deux paquets n'est branche au catalogue (`grep -c "paquet: ce1-futur$"` et `"paquet: ce1-passe-compose$"`
sur `catalogue.yaml` rendent 0) : je n'ai rien joue. Je decide sur la lecture des fichiers de paquet, item par item, des 24 items de
`ce1-futur.yaml`, des 24 de `ce1-passe-compose.yaml` et des items 16, 19, 23 de `ce1-synonymes-contraires.yaml`, contre mon arbitrage de
s48 (sections 2, 5.8 a 5.10, 6.3), mes fiches de s52 (`FICHE-bandit-manchot-conjugaison-present-ce1.md`,
`FICHE-bandit-manchot-synonymes-contraires-ce1.md`), le planning de s54, l'avis de `theo-archi` et `DEC-0114`.
**Ce document ne prononce aucun verdict, aucun Done, aucun statut de matrice.** **Aucun identifiant de condition neuf n'est ecrit
ici** : les codes `K1` a `K6` (conditions avant branchement), `L1` a `L3` (recommandations), `N1`, `N2` (indices) et `D1` a `D7`
(proprietes de la passe) sont des reperes de ce fichier, pas des identifiants.

Convention des blocs : ils portent l'espace insecable (U+00A0) devant `?` et `:` et a l'interieur des guillemets francais, comme les
paquets (`TYP-1`). Ils se collent a la place de la ligne de meme role de l'item cite.

## Resume

| Poste | Decision | Prix |
|---|---|---|
| `FRA-CE1-18`, `ce1-futur` | **deux conditions avant branchement** : `K1` la consigne du jeu nomme le futur ; `K2` item 8. 23 items acceptes tels quels | lot **1,0** |
| `FRA-CE1-19`, `ce1-passe-compose` | **deux conditions avant branchement** : `K3` quinze indices remplaces ; `K4` trois enonces d'*etre*. Recommandations `L2`, `L3` | lot **1,0** |
| `SYC-CE1-R1` + item 16 de `FRA-CE1-25` | texte fixe ici mot pour mot (`K5`, `K6`) ; rejeu des trois items | **0,25** |
| `PAR-4` | une passe, sept proprietes de portee **D** nommees, sur `round-two` apres la fusion des deux branchements de `marc-dev` | **0,5** |
| Cet arbitrage | lecture de 51 items, dix textes d'enfant ecrits, poses sur 23 lignes | **0,5** |
| R3 (section 11 de mon arbitrage de s53) | fait corrige ici (section 1.3) ; le fichier de s53 n'est pas edite par ce commit | **0,0** |
| Marge de rejeu, allouee a aucun lot | | **0,5** |
| **Total instance A** | `PAR-9` tenue, aucun ecart | **3,75 / 4,0** |
| `PAR-13` | **tenue** : le francais CE1 n'est pas un terrain neuf ; `FUT` et `PCO` sont des cousines de `PRS` | - |
| `PAR-14` | **sans objet pour A** ; `Q4` n'est pas a moi (`PAR-2`, instance B) | - |
| `Q5`, `Q6`, `Q7`, `Q8` | `Q5` 0,0, pas a moi ; `Q6` plafond ecrit en une ligne ; `Q7` non traite ici ; `Q8` decoupage recevable sur les frontieres, `PAR-2` non amende | - |

---

## 1. Trois corrections de mes propres constats, avant de m'en servir

### 1.1 `FUT-CE1-3` (b) : avec un mot de temps, le present reste une forme juste

J'ai admis en s48 (section 5.9) *"le present du meme verbe"* comme famille de distracteur du futur, et j'ai ecrit dans la meme
condition que l'on ne propose jamais comme fausse *"une forme juste d'un temps"*. Je n'ai pas vu que les deux se contredisent :
*"Demain, je chante une chanson."* est une phrase correcte du francais (le present dit le futur proche du locuteur). L'en-tete de
`ce1-futur.yaml` ecrit le contraire : *"Chaque phrase porte un mot de temps (demain, bientot, plus tard) : sans lui le present serait
aussi juste."* Avec lui aussi. Sur les 21 items qui proposent le present, l'enfant qui le choisit ecrit une phrase juste et lit
qu'il s'est trompe.

**Ce que je ne change pas** : `FUT-CE1-3` (b) reste admise. **Ce qui departage** : la question doit demander le futur. C'est la barre
de `TRV-CE1-2` (*"une seule acceptable"*), deja ecrite ; aucune barre ne bouge. Condition `K1`, section 5.

### 1.2 Ma levee de `TRV-CE1-5` a s52 etait ecrite plus etroite que son motif

Le motif du refus de `FRA-CE1-17` (fiche, section 4) : l'indice *"lui donne une methode qui confirme l'erreur que l'item est fait pour
corriger"*. Ce qui le levait, ecrit par moi : *"l'indice ne renvoie pas a l'ecoute de la phrase"*. L'oreille etait le cas de ce
paquet, pas la regle. Le planning de s54 (critere de `S54-BR-FRA-CE1-18`, `-19` : *"aucun indice ne renvoie a l'oreille"*) et l'en-tete
de `ce1-passe-compose.yaml` ont repris ma formule etroite, et le paquet la tient tout en servant un indice qui confirme l'erreur
centrale de la ligne (section 6).

**La barre, telle que mon motif de s52 l'a toujours dite, sans rien y ajouter** : un indice qui donne un **critere** (*"a deux
mots"*, *"parle de ce qui s'est passe"*) ne doit pas etre satisfait par un distracteur de son item ; l'enfant qui a choisi ce
distracteur et applique fidelement l'indice s'y trouve confirme. Un indice qui dit **ou regarder** sans departager les deux
propositions restantes n'est pas un motif de refus : c'est l'etage insuffisant, que j'ai traite en reserve (`ALP-CE1-R1`) et accepte
sur `FRA-CE1-17` (les indices du sujet ne departagent pas l'infinitif).

### 1.3 R3 : "six fichiers" a la section 11 de mon arbitrage de s53 est faux, un seul fichier

Recompte sur `dc2870eb` : `grep -cE '^ *- enonce: "[^"]* :'` rend 14 sur `cm1-grands-nombres.yaml` et 0 sur `cm1-anglais`,
`cm1-comprehension`, `cm1-fractions`, `cm1-histoire-geographie-nommer-en-geographie`, `cm1-histoire-geographie-nommer-en-histoire` ;
`grep -lE` du meme motif sur tout `contenu/` ne rend que `cm1-grands-nombres.yaml`. **Cause, retrouvee** : mon motif de s53 (`".* :"`)
debordait de la chaine et comptait le commentaire de fin de ligne (`# COMP-3 : ...`). Le `relecteur-pedagogique` avait raison. La
phrase de s53 se corrige dans son fichier, par moi, dans un commit distinct (une phrase, **0,0**) ; ce commit-ci n'ecrit qu'un fichier.

---

## 2. `Q3` - les tarifs reels de l'instance A

| Poste | Prix | Motif |
|---|---|---|
| `FRA-CE1-18` | **1,0** | premier passage complet d'un paquet jamais audite, tarif de lot (s28, section 4.3). Les conditions de ce document le rendent plus court, pas moins cher (s53, section 14) |
| `FRA-CE1-19` | **1,0** | idem |
| `SYC-CE1-R1` + item 16 | **0,25**, et non 0,5 | les trois textes sont fixes ici mot pour mot (`K5`, `K6`) : bareme de s29 section 8.1, reconduit par `PAR-10` (*"correction reprise mot pour mot d'un texte que j'ai fourni, puis reverification = 0,25"*). Un seul paquet, un seul passage |
| `PAR-4` | **0,5** | ma clause, inchangee. **Une passe, pas deux** : la passe de s53 n'a pas eu lieu, et une passe porte sur le depot servi a un sha ; celle de s54 lit le depot entier, donc aussi ce que s53 y a mis. Il n'y a rien a rejouer pour s53 a part |
| Cet arbitrage | **0,5** | meme volume que celui de s53 (0,5) : 51 items lus, dix textes d'enfant ecrits pour 23 lignes, que je rejugerai a l'audit |
| Correction R3 | **0,0** | une phrase de mon propre fichier, cause etablie (1.3) |
| `Q5`, `Q6` | **0,0** | section 9 |
| Marge de rejeu | **0,5** | nommee, allouee a aucun lot. Elle paie **un** refus au premier passage d'un des deux paquets |
| **Total** | **3,75 / 4,0** | `PAR-9` : 4,0 par instance, marge comprise. **Reste non alloue : 0,25** |

Lots : deux sur trois (`PAR-8`). Le reste de 0,25 ne paie aucun lot ni un second rejeu (0,5) ; il ne paie pas non plus `Q7` (section 9).

## 3. `PAR-13` - le francais CE1 n'est pas un terrain neuf, et `FUT`, `PCO` sont des cousines de `PRS`

Je juge `PAR-13` au niveau du terrain, comme a s51 et s52 (`ARBITRAGE-PREALABLE-s52-instances-ce1.md`, section 3 : *"A, francais CE1 :
non"*). Depuis, le francais CE1 a ferme `FRA-CE1-17`, `-25`, `-26` a s52 ; `TRV-CE1-1` a `-7` et `LON-CE1-1`, `-2`, `-5` ont mordu neuf
paquets. `FUT-CE1-*` et `PCO-CE1-*` n'ont jamais rencontre de contenu, mais leur dispositif est celui de `PRS-CE1-*` : meme forme
d'enonce (*"« verbe » : phrase a trou"*), sujet pronom (`FUT-CE1-2` et `PCO-CE1-2` renvoient a `PRS-CE1-2`), familles de meme nature,
meme mecanique. Ce dispositif a ete refuse une fois puis valide a s52 ; la defaillance alors trouvee est nommee (1.2).

**Lecture : cousines, pas terrain neuf.** Et `PAR-13` tient de toute facon a s54 : B porte sciences et EMC CM1, terrains a lignes
`couvert-valide`. **Ce que la lecture ne dit pas** : les deux defauts de la section 1 sont du type qu'un terrain neuf produit ; ils
sont trouves a la lecture, avant branchement, et c'est le role de ce document.

## 4. `PAR-14` - sans objet pour l'instance A ; `Q4` n'est pas a moi

`PAR-14` chiffre une **reprise apres un audit**. `ce1-futur` et `ce1-passe-compose` n'ont jamais ete audites : leur prix est deja
celui d'un lot (1,0, je suis la source des motifs). `K3` et `K4` touchent 17 items sur 24 du passe compose, plus de la moitie, sans
changer ce prix : le premier passage est complet de toute facon. `SYC-CE1-R1` touche 3 items sur 24 : regime du mot pour mot.

**`Q4`** (les deux reprises de sciences CM1, leur tarif sous `PAR-14`, la barre de B) porte sur le lot de sciences CM1, qui est a
l'instance B (`PAR-2` : une matiere n'est jamais partagee). **Je n'y reponds pas** ; l'instance B le fait sur son lot.

---

## 5. `FRA-CE1-18`, `ce1-futur` - lecture des 24 items

**Indices du paquet** (ils tournent dans l'ordre du fichier) : **A** *"Le verbe change selon le moment : regarde le debut de la
phrase."* (12 mots) ; **B** *"Qui fait l'action ? Regarde le petit mot devant le trou."* (10) ; **C** *"Lis le mot de temps, puis le
petit mot devant le trou."* (12). Aucun ne renvoie a l'ecoute ni ne donne un critere qu'un distracteur satisfait (1.2) ; aucun ne
donne la reponse. Familles : (a) futur a une autre personne, (b) present, (c) radical regularise.

| n | Phrase | Bonne reponse | Distracteurs (famille) | Indice | Lecture |
|---|---|---|---|---|---|
| 1 | *chanter* : demain, je ___ une chanson | chanterai | chante (b), chanteras (a) | A | juste ; (b) juste aussi sans `K1` |
| 2 | *jouer* : demain, je ___ au ballon | jouerai | joue (b), jouerons (a) | B | juste |
| 3 | *dessiner* : bientot, je ___ un arbre | dessinerai | dessine (b), dessinerons (a) | C | juste |
| 4 | *etre* : demain, je ___ en classe | serai | suis (b), *etrai* (c) | A | juste ; (c) plausible, radical tire de l'infinitif |
| 5 | *regarder* : demain, tu ___ un film | regarderas | regardes (b), regarderez (a) | B | juste |
| 6 | *parler* : plus tard, tu ___ a la maitresse | parleras | parles (b), parlerons (a) | C | juste |
| 7 | *laver* : demain, tu ___ les verres | laveras | laves (b), laverez (a) | A | juste |
| 8 | *avoir* : demain, tu ___ un nouveau jeu | auras | aura (a, homophone), *avras* (c) | B | **`K2`** : *"tu auras un nouveau jeu"* suppose un objet possede, `TRV-CE1-6`. Indice B : renvoie au sujet, qui departage l'homophone |
| 9 | *ecouter* : demain, il ___ la radio | ecoutera | ecoute (b), ecouteras (a, homophone) | C | juste ; C renvoie au sujet |
| 10 | *travailler* : bientot, elle ___ dans le jardin | travaillera | travaille (b), travaillerons (a) | A | juste ; *elle*, pas *tu* : aucun jardin suppose chez l'enfant |
| 11 | *chanter* : demain, nous ___ en classe | chanterons | chantons (b), chanteront (a) | B | juste |
| 12 | *etre* : demain, elle ___ a l'ecole | sera | seras (a, homophone), *etra* (c) | C | juste |
| 13 | *jouer* : plus tard, nous ___ dehors | jouerons | jouons (b), jouerez (a) | A | juste |
| 14 | *regarder* : bientot, nous ___ les etoiles | regarderons | regardons (b), regarderai (a) | B | juste |
| 15 | *dessiner* : demain, vous ___ une maison | dessinerez | dessinez (b), dessineront (a) | C | juste |
| 16 | *avoir* : plus tard, il ___ faim | aura | auras (a, homophone), a (b) | A | juste ; `L1` |
| 17 | *parler* : plus tard, vous ___ doucement | parlerez | parlez (b), parlerons (a) | B | juste |
| 18 | *laver* : demain, vous ___ la voiture | laverez | lavez (b), laveront (a) | C | juste |
| 19 | *chanter* : bientot, ils ___ en choeur | chanteront | chantent (b), chanterez (a) | A | juste |
| 20 | *etre* : bientot, nous ___ dans la cour | serons | serez (a), sommes (b) | B | juste |
| 21 | *danser* : demain, elles ___ sur la scene | danseront | dansent (b), dansera (a) | C | juste ; voir "Personnages" |
| 22 | *avoir* : bientot, vous ___ un cadeau | aurez | aurons (a), avez (b) | A | juste ; *vous* n'est pas l'enfant seul (`TRV-CE1-6` vise *tu*) : accepte |
| 23 | *etre* : plus tard, ils ___ au parc | seront | sont (b), *etront* (c) | B | juste |
| 24 | *avoir* : demain, elles ___ de la chance | auront | aura (a), *avront* (c) | C | juste |

**Conditions tenues, comptees** : `FUT-CE1-1` (*etre* et *avoir* sur 8 items sur 24 ; neuf verbes du premier groupe, aucun en
`-ger`, `-cer`, `-eler`, `-eter`, `-yer`) ; `FUT-CE1-2` (pronom toujours ; chaque personne sujet de 4 items) ; `FUT-CE1-3` (familles
declarees et justes sur les 24 ; ni conditionnel, ni futur proche, ni imparfait) ; `TRV-CE1-1` ; `TRV-CE1-2` (trois propositions
distinctes ; bonne reponse strictement la plus longue sur 4 items : 13, 14, 19, 21) ; `TRV-CE1-3` sans objet ; `TRV-CE1-4` ;
`TRV-CE1-5` ; `TRV-CE1-7` (24) ; `LON-CE1-1`, `-2`, `-5`. **Les 24 formes sont justes**, accents relus (*ecoutera*, *etoiles*,
*maitresse*, *choeur*, *scene* portent le leur dans le fichier).

**Personnages** (`TRV-CE1-6`) : troisieme personne, deux *il*, deux *elle*, deux *ils*, deux *elles*. *"elles danseront sur la
scene"* est la seule activite du cote du stereotype, a cote de *"elle travaillera dans le jardin"* et *"il ecoutera la radio"* : ce
n'est pas un partage (barre du refus de `FRA-CE1-17`, section 5 : *"il va tout entier du meme cote"*). Pas de condition.

### `K1` - la consigne du jeu nomme le futur (bloquante, `TRV-CE1-2`, section 1.1)

L'entree de catalogue porte, mot pour mot :

```yaml
    consigne: "Lis bien, puis choisis le verbe au futur."
```

*Futur* est admis (`TRV-CE1-4`). La consigne est rendue sur chaque manche (`fragments/manche.html`, element `.consigne`) : c'est le
seul endroit qui la fait lire a chaque item sans toucher les 24 enonces. **Dans le meme commit**, la ligne d'en-tete fausse de
`ce1-futur.yaml` devient :

```yaml
# Chaque phrase porte un mot de temps (demain, bientot, plus tard), et la consigne du jeu demande le futur : avec un mot
# de temps, le present reste une phrase correcte ("demain, je chante"), c'est la consigne qui l'ecarte.
```

Le titre est libre ; je propose *"Les verbes au futur"*, en regard de *"Les verbes au présent"*, sans en faire une condition.

### `K2` - item 8 (bloquante, `TRV-CE1-6`, meme motif que s53 section 12.4, *"ta boite"*)

Seule la ligne `enonce` change ; bonne reponse, distracteurs, familles et indice inchanges :

```yaml
  - enonce: "« avoir » : demain, tu ___ une dictée."
```

### `L1` - recommandation, non bloquante

Item 16 (*aura* contre l'homophone *auras*) : l'indice A dit le moment, pas la personne. L'indice B (*"Qui fait l'action ? Regarde
le petit mot devant le trou."*) departage. Laisse tel quel, ce n'est pas un motif (1.2, etage insuffisant).

---

## 6. `FRA-CE1-19`, `ce1-passe-compose` - lecture des 24 items

**Indices du paquet**, dans l'ordre du fichier : **I1** *"Un verbe au passe compose a deux mots : cherche-les."* ; **I2** *"Le temps
du verbe se lit au debut de la phrase."* ; **I3** *"Cherche la forme qui parle de ce qui s'est passe."* Familles : (a) infinitif pour
participe, (b) auxiliaire a une autre personne, (c) present.

**Le defaut, sous la barre de 1.2.** L'erreur centrale de la ligne est (a), *"c'est l'erreur du CE1"* (`PCO-CE1-3`), et sur les
verbes du premier groupe elle ne s'entend pas (*chante* / *chanter*). **I1 donne un critere que (a) et (b) satisfont** : *"j'ai
chanter"*, *"nous avez joue"* ont deux mots ; l'enfant qui s'est trompe compte, et se trouve confirme. **I3 donne un critere que (a)
sur un verbe du premier groupe et (b) satisfont** : *"il a laver"* se lit comme un passe, *"elles a lave"* en est un. I2 dit ou
regarder (le mot de temps) : il ecarte (c), il ne confirme rien ; il est accepte.

| n | Phrase | Bonne reponse | Distracteurs (famille) | Indice | Lecture |
|---|---|---|---|---|---|
| 1 | *chanter* : hier, ___ une chanson | j'ai chante | j'ai chanter (a, homophone), je chante (c) | I1 | juste ; **`K3`** -> `N1` |
| 2 | *regarder* : hier soir, ___ un film | tu as regarde | tu as regarder (a, hom.), tu a regarde (b, hom.) | I2 | juste ; `L2` -> `N1` |
| 3 | *laver* : hier, ___ les verres | il a lave | il a laver (a, hom.), il lave (c) | I3 | juste ; **`K3`** -> `N1` |
| 4 | *chanter* : hier soir, ___ ensemble | nous avons chante | nous avons chanter (a, hom.), nous chantons (c) | I1 | juste ; **`K3`** -> `N1` |
| 5 | *regarder* : hier, ___ les etoiles | vous avez regarde | vous avez regarder (a, hom.), vous regardez (c) | I2 | juste ; `L2` -> `N1` |
| 6 | *danser* : hier soir, ___ sur la scene | ils ont danse | ils ont danser (a, hom.), ils dansent (c) | I3 | juste ; **`K3`** -> `N1` |
| 7 | *jouer* : hier, ___ au ballon | j'ai joue | j'ai jouer (a, hom.), j'as joue (b) | I1 | juste ; **`K3`** -> `N1` |
| 8 | *dessiner* : ce matin, ___ une maison | tu as dessine | tu as dessiner (a, hom.), tu dessines (c) | I2 | juste ; `L2` -> `N1` |
| 9 | *avoir* : hier, ___ de la chance | il a eu | il a avoir (a), il as eu (b, hom.) | I3 | juste ; **`K3`** -> `N2` |
| 10 | *jouer* : hier, ___ dehors | nous avons joue | nous avez joue (b), nous jouons (c) | I1 | juste ; **`K3`** -> `N2` |
| 11 | *dessiner* : ce matin, ___ un arbre | vous avez dessine | vous avez dessiner (a, hom.), vous avons dessine (b) | I2 | juste ; `L2` -> `N1` |
| 12 | *avoir* : hier, ___ un cadeau | ils ont eu | ils ont avoir (a), ils a eu (b) | I3 | juste ; **`K3`** -> `N2` |
| 13 | *etre* : hier, ___ en classe | j'ai ete | j'ai etre (a), je suis (c) | I1 | **`K4`** ; **`K3`** -> `N1` |
| 14 | *etre* : hier, ___ a l'heure | tu as ete | tu a ete (b, hom.), tu es (c) | I2 | juste (*etre a l'heure* n'est pas un lieu) ; `L2` -> `N2` |
| 15 | *ecouter* : ce matin, ___ la radio | elle a ecoute | elle a ecouter (a, hom.), elle ecoute (c) | I3 | juste ; **`K3`** -> `N1` |
| 16 | *travailler* : la semaine derniere, ___ beaucoup | nous avons travaille | nous avons travailler (a, hom.), nous avez travaille (b) | I1 | juste ; **`K3`** -> `N1` |
| 17 | *etre* : hier, ___ au parc | vous avez ete | vous avons ete (b), vous etes (c) | I2 | **`K4`** ; I2 garde |
| 18 | *aider* : hier, ___ en classe | elles ont aide | elles ont aider (a, hom.), elles a aide (b) | I3 | juste ; **`K3`** -> `N1` |
| 19 | *avoir* : hier, ___ tres faim | j'ai eu | j'ai avoir (a), j'as eu (b) | I1 | juste ; **`K3`** -> `N1` |
| 20 | *parler* : hier, ___ a la maitresse | tu as parle | tu as parler (a, hom.), tu parles (c) | I2 | juste ; `L2` -> `N1` |
| 21 | *etre* : hier, ___ dans la cour | elle a ete | elle a etre (a), elle est (c) | I3 | **`K4`** ; I3 garde : *"elle a etre"* n'est pas un passe, *"elle est"* non plus |
| 22 | *avoir* : hier, ___ une bonne idee | nous avons eu | nous avons avoir (a), nous avez eu (b) | I1 | juste ; **`K3`** -> `N1` |
| 23 | *parler* : hier, ___ trop fort | vous avez parle | vous avez parler (a, hom.), vous parlez (c) | I2 | juste ; `L2` -> `N1` |
| 24 | *laver* : ce matin, ___ la voiture | elles ont lave | elles a lave (b), elles lavent (c) | I3 | juste ; **`K3`** -> `N2` |

**Conditions tenues, comptees** : `PCO-CE1-1` (auxiliaire *avoir* seul ; *etre*, *avoir*, dix verbes du premier groupe ; aucun verbe
a *etre*, aucun pronominal, aucun complement avant le verbe) ; `PCO-CE1-2` (chaque personne sujet de 4 items) ; `PCO-CE1-3` ((a) sur
20 items sur 24, au-dessus du tiers ; familles declarees justes sur les 24 ; jamais l'imparfait) ; `TRV-CE1-1`, `-2` (bonne reponse
strictement la plus longue sur 3 items : 10, 14, 24), `-4` (*passe compose* admis), `-6`, `-7` ; `LON-CE1-1`, `-2`, `-5` (trois mots au
plus par proposition). **(a) sur *etre* et *avoir*** (*"j'ai etre"*, *"il a avoir"*) : ce n'est pas l'homophone du premier groupe,
c'est la recopie du verbe ecrit en tete de l'enonce, une erreur reelle d'enfant dans cette forme d'item. Accepte.

**Personnages** : *ils* dansent, *il* lave les verres, *elles* lavent la voiture, *elles* aident en classe : rien du cote du
stereotype. Aucun *tu* qui suppose un foyer ou un objet.

### `K3` - quinze indices remplaces (bloquante, `TRV-CE1-5`, barre de 1.2)

Deux indices neufs. Chacun est vrai pour tous les items ou il est pose, donne une methode et ne donne pas la reponse ; *verbe*,
*sujet*, *present* sont admis (`TRV-CE1-4`).

```yaml
    indice: "Ne recopie pas le verbe écrit au début : il change."
```

`N1`, 10 mots : vise (a). Vrai sur tous les items : *chanter* devient *chante*, *etre* *ete*, *avoir* *eu*. Un enfant qui a recopie
le verbe se voit dire ou il s'est trompe ; il reste a choisir entre les deux autres.

```yaml
    indice: "Avec ce sujet, comment s'écrit « avoir » au présent ?"
```

`N2`, 8 mots : vise (b), avec ce que `FRA-CE1-17` vient d'apprendre a l'enfant (*avoir* au present).

**Items et indice a poser, rien d'autre ne change dans ces items** (sauf l'item 13, `K4`) :

- `N1` : items **1, 3, 4, 6, 7, 13, 15, 16, 18, 19, 22** ;
- `N2` : items **9, 10, 12, 24**.

Restent : I2 sur les items 2, 5, 8, 11, 14, 17, 20, 23 ; I3 sur l'item 21. **I1 disparait du paquet.** `TRV-CE1-5` (*"pas identique sur
tout le paquet"*) reste tenue. **Dans le meme commit**, la ligne d'en-tete *"Aucun indice ne renvoie a l'ecoute : participe et
infinitif en -e/-er se prononcent de meme."* devient :

```yaml
# Aucun indice ne donne un critere qu'un distracteur de son item satisfait (ni "deux mots", ni "parle du passe") :
# participe et infinitif en -e/-er se prononcent de meme, et "j'ai chanter" a deux mots.
```

### `K4` - trois enonces d'*etre* (bloquante, exactitude)

*"Hier, j'ai ete en classe"*, *"vous avez ete au parc"*, *"elle a ete dans la cour"* : *avoir ete* suivi d'un lieu est l'emploi
familier pour *aller* (*"j'ai ete au parc"* pour *"je suis alle au parc"*), que la classe corrige. Meme barre que mon P1 de s53 :
un item ne propose pas en bonne reponse une forme qu'une grammaire scolaire corrige. Remplacements, complements invariables en genre
(le *je* d'un enfant peut etre une fille) ; seule la ligne `enonce` change, bonne reponse et distracteurs inchanges :

```yaml
  - enonce: "« être » : hier, ___ en retard."
```

(item 13)

```yaml
  - enonce: "« être » : hier, ___ en avance."
```

(item 17)

```yaml
  - enonce: "« être » : hier, ___ la première."
```

(item 21 ; *la premiere* s'accorde avec *elle*). Le present (c) reste faux avec *hier* dans les trois.

### `L2`, `L3` - recommandations, non bloquantes

- **`L2`** : sur les items 2, 5, 8, 11, 20, 23 (homophone (a)), I2 ne departage pas ; `N1` le ferait. Sur l'item 14 (homophone (b)
  *tu a*), `N2`. Ce n'est pas un motif de refus (etage insuffisant, 1.2) ; c'est la meme correction pour un cout nul si elle part
  dans le meme commit. Appliquee, le paquet porte `N1` sur 17 items, `N2` sur 5, I2 sur l'item 17, I3 sur l'item 21.
- **`L3`** : une consigne qui nomme aussi le temps (*"Lis bien, puis choisis le passé composé."*), pour qu'un enfant qui enchaine
  les deux jeux lise la meme forme de question. Rien n'y oblige : avec *hier*, le present n'est pas une phrase correcte.

---

## 7. `SYC-CE1-R1` et item 16 de `FRA-CE1-25` - le texte, mot pour mot

Barre : celle de ma fiche, section 5, *"Les reponses ne changent pas, sauf l'accord. Rien d'autre ne change."* Je choisis l'une des deux
formes que j'y avais ecrites pour l'item 23.

- **`K5`, item 19** : dans l'enonce, le mot *Il* devient *Elle*. Rien d'autre. Ligne resultante :
  ```yaml
  - enonce: "« Elle lance le ballon. » Quel mot est un synonyme de « lance » ?"
  ```
- **`K5`, item 23** : dans l'enonce, *Cet homme* devient *Ce cheval*. Bonne reponse *costaud*, distracteurs *faible* et *vieux*,
  inchanges : aucun accord ne bouge, et *costaude*, rare a l'ecrit au CE1, n'entre pas. Ligne resultante :
  ```yaml
  - enonce: "« Ce cheval est fort. » Quel mot est un synonyme de « fort » ?"
  ```
- **`K6`, item 16** : la phrase *"Il arrive a l'ecole."* devient *"Le train arrive."* ; la question, la bonne reponse *part*, les
  distracteurs *vient* (a) et *marche* (b) et l'indice ne changent pas. Remise dans la phrase, *part* donne *"Le train part."* : le
  doute de mon observation 1 (*"Il part a l'ecole"*, qui veut dire qu'il y va) disparait. Ligne resultante :
  ```yaml
  - enonce: "« Le train arrive. » Quel mot est le contraire de « arrive » ?"
  ```

Le developpeur reproduit l'elision *de « arrive »* telle qu'elle est : elle est au `relecteur-pedagogique` (fiche, observation 2), je
ne la tranche pas. Au rejeu, je verifie que le diff ne touche que ces trois lignes, puis je joue les trois items. Si la reserve tombe,
je l'ecris levee et datee dans la fiche, sans effacer la reserve.

---

## 8. `PAR-4` - la conduite de la passe

**Objet** : le depot servi entier, portee **D** (`ARBITRAGE-PREALABLE-s29-...` section 13.2, completee par la passe de s30 section 3.4
et par `PAR-12`). **Arbre** : `round-two` apres la fusion des branchements de `marc-dev` (`ce1-futur`, `ce1-passe-compose`), pour que la
passe voie le depot que l'enfant aura ; si ces fusions manquent a la date de la passe, le tip de `round-two` du jour, sha ecrit.
**Forme** : un fichier `squad/conformite/PASSE-TRANSVERSE-s54-<objet>.md`, une ligne par propriete, sha de l'arbre, prix reel. Jar
construit au sha, copie hors du worktree, lance sur un port propre a l'instance A.

| Repere | Propriete (portee **D**) | Ce que je fais | Pourquoi maintenant |
|---|---|---|---|
| `D1` | la dette des paquets a voix | liste des paquets a champ de voix ajoutes ou modifies depuis la passe de s35 (`git log --since=2026-09-11` sur `contenu/`) ; s'il y en a, lecture de chacun | non lue depuis s30 |
| `D2` | ce qu'un indice ou une consigne d'adulte demande, contre `VOI-3`, `VOI-5`, `VOI-8` | meme population que `D1` | idem |
| `D3` | l'unicite de sens d'un identifiant de condition | le garde-fou `PAR-5` lance sur l'arbre ; lecture a la main des formes qu'il ne lit pas (`ARBITRAGE-PREALABLE-s52-instances-ce1.md`, section 9) | trois incidents de ma main a s40 et s42 |
| `D4` | **la coherence de mes conditions entre elles** | **objet principal** : `LON-CE1-1` a `-5`, `TRV-CE1-1` a `-7`, les conditions de ligne de s48 et celles de mathematiques CE1 de s43, lues deux a deux sur les lignes qui partagent une population | jamais rejouee depuis s29 ; 1.1 en est une occurrence (`FUT-CE1-3` (b) contre `TRV-CE1-2`) |
| `D5` | l'homogeneite de registre entre jeux qu'un meme enfant enchaine | titres et consignes des entrees CE1 du catalogue, francais et mathematiques ; en particulier : la consigne pose-t-elle la question quand l'enonce ne la pose pas (famille de `K1`) | une trentaine d'entrees CE1 neuves depuis s30 |
| `D6` | un gabarit qui compose une phrase autour d'un champ de contenu (famille de `REV-1`) | relecture des gabarits modifies depuis s35 : 3 commits (`git log --since=2026-09-11 --format=%h -- adapters/adapter-web/src/main/resources/templates`) ; jeu des ecrans touches | gabarits changes |
| `D7` | la contrefacon par tirage (un item resolu par un autre item du meme paquet) | **non couverte par cette passe** : population du depot entier, hors de 0,5 ; ecrite comme non cherchee | la plus productive de s30, jamais mesuree ailleurs |

**Prix : 0,5.** Si `D4` trouve une contradiction qui demande d'amender une condition, l'amendement est un document a part, date, qui
n'entre pas dans ce prix.

---

## 9. `Q5` a `Q8`

- **`Q5`** (`S53-REPORT-GRANDS-NOMBRES`) : **0,0, pas par moi.** La ponctuation du texte d'enfant est au `relecteur-pedagogique`
  (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`) et au cliquet de `TYP-1`. Condition : le commit ne change que l'espace devant
  `:` dans les 14 enonces. Si un mot change, il passe devant moi.
- **`Q6`, plafond d'`ART-CP-10` en une ligne** : *`cp-arts-oeuvres-familles`, branche tel quel, laisse `ART-CP-10` a `non couvert` :
  il ne sert ni la reconnaissance ni le rapprochement d'oeuvres (son en-tete le dit), il sert le vocabulaire des familles, qui n'est
  pas la ligne, et aucune oeuvre ne peut y etre designee en texte sans contrefacon (`CON-1`).* Plafond lu sur l'en-tete et la forme,
  sans lecture des 12 items ; un plafond est une borne haute, la qualite des items n'y change rien.
- **`Q7`** (plafond des 17 paquets de G7 a G11) : **non traite ici.** Cinq matieres hors du francais CE1, et un chiffre que je ne
  donne pas sans avoir lu les 17 en-tetes et les cellules de matrice. Il ne tient pas dans le reste de 0,25 de A sauf si cette lecture
  suffit. A engager par `sofia-sm` et `camille-po` comme poste distinct, a s54 par amendement ou a s55.
- **`Q8`** (decoupage du francais CE1 en X et Y, `DEC-0114` section 7.2) : **recevable sur le critere des frontieres, `PAR-2` non
  amende aujourd'hui.** J'ai relu la table de s48 section 7 ligne par ligne : `-04`/`-06` et `-05`/`-04` sont dans X ; `-12`/`-13`
  dans X ; `-08`/`-09`/`-10` dans Y ; la dependance de `RLP-CE1-1` a `-14` et `-15` dans Y ; `-24`/`-25` et `-14`/`-17` touchent une
  ligne deja `couvert-valide`. **Aucune frontiere n'est coupee entre X et Y.** Restent deux conditions de mon verdict de s52 :
  l'avis de `theo-archi` sur `ADR-0065` (absent de son avis de s54) et un sprint ou aucune autre instance ne porte de terrain neuf ;
  la sequence de `camille-po` (X seul, puis X et Y) respecte la seconde. **Non places** : `FRA-CE1-03` et `-21` (G3, plafonnes `en
  cours`) ; `camille-po` dit dans quel groupe ils vont avant que je juge le decoupage pour un sprint.

---

## 10. Ce que je ne peux pas decider sans le jeu a l'ecran

1. **Que la consigne de `K1` soit lue a chaque item**, au-dessus de la manche et sur l'ecran qui suit une erreur. Les gabarits disent
   qu'elle y est ; je le verifie en jouant.
2. **Le moment ou l'indice s'affiche** (apres la premiere erreur, s53 section 13, point 6). `K3` suppose que l'enfant lit l'indice apres
   avoir choisi le distracteur ; si ce n'est pas le cas, je relis `K3` avant de juger.
3. **Ce que *"le verbe ecrit au debut"* (`N1`) designe a l'ecran** : l'etiquette *« chanter »* est-elle bien ce que l'enfant voit en
   premier, avant le mot de temps ?
4. **La lisibilite de *chante* contre *chanter*** sur des boutons de trois mots (*nous avons travailler*) : la difference tient dans
   une ou deux lettres en fin de bouton.
5. **Les tirages** : dix manches sur 24 items, sans ordre de progression (s48, section 9, point 10). Une partie peut ne tirer que des
   items (a, c) ; je le constate en jouant, je n'en fais pas une barre.
6. **Item 16 des synonymes** : *"Le train part."* sur l'ecran de revelation.

## 11. Ce dont je reste la seule garde

Aucun garde-fou ne lit : qu'une consigne nomme le temps demande (`K1`) ; qu'un indice ne donne pas un critere satisfait par un
distracteur de son item (`K3`, 1.2) ; qu'une bonne reponse ne soit pas une forme que la classe corrige (`K4`) ; un objet possede
suppose chez l'enfant (`K2`) ; la repartition des activites par genre (`K5`) ; l'accent des participes et des homographes
(*ete*, *ecoute*, *dictee*). Les comptes de `FUT-CE1-1` et `PCO-CE1-3` (a), eux, peuvent etre tenus par les tests de paquet que
`theo-archi` dit dus (avis de s54, T1). **Je tiens les six premiers en lisant et en jouant ; l'equipe ne doit pas les croire
couverts par la CI.**

## 12. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | `K1`, `K2` dans le commit de `S54-BR-FRA-CE1-18` ; `K3`, `K4` (et `L2`, `L3` s'il les prend) dans celui de `S54-BR-FRA-CE1-19` ; `K5`, `K6` dans `S52-SYC-CE1-R1`, trois lignes et rien d'autre. Le critere du planning *"aucun indice ne renvoie a l'oreille"* se lit desormais avec 1.2 |
| `sofia-sm` | la barre de 1.2 a la place de la formule *"aucun indice ne renvoie a l'oreille"* dans les criteres de `-18`, `-19` ; `Q7` comme poste distinct ; la passe `PAR-4` apres la fusion des branchements de `marc-dev` |
| `camille-po` | `Q8` : placer `FRA-CE1-03` et `-21` ; la fiche de `FRA-CE1-18` et `-19` porte `K1` a `K4` comme criteres ; `Q6` : la ligne de la section 9, pour la decision de s55 (`DEC-0114` section 6) |
| `theo-archi` | `Q8` : l'avis sur `ADR-0065` que mon verdict de s52 exige avant tout decoupage ; T1 : `K1` est mordable (la consigne de l'entree contient *futur*) si le test du paquet lit le catalogue |
| instance B | `Q4` |

## 13. Ce que ce document ne fait pas

- Il ne prononce aucun Done, aucun verdict, et ne touche ni une fiche, ni la matrice, ni le registre d'identifiants, ni un YAML, ni le
  catalogue. Les textes des sections 5 a 7 sont a appliquer par le developpeur, puis rejuges par moi en jouant.
- Il ne rouvre ni `FUT-CE1-1` a `-3`, ni `PCO-CE1-1` a `-3`, ni `TRV-CE1-1` a `-7` : `K1` a `K6` appliquent des barres deja ecrites
  (`TRV-CE1-2`, `-5`, `-6`, le motif de s52, ma reserve `SYC-CE1-R1`, mon P1 de s53). `L1` a `L3` ne seront pas des motifs de refus.
- Il ne traite ni `Q4` (instance B), ni `Q7` (section 9), et n'edite pas la section 11 de mon arbitrage de s53 (1.3).
- Il ne cree aucun identifiant de condition ou de reserve.

**Signe** : `nadia-prof`, s54, instance A, 2026-10-07.
