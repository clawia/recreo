# Complement a l'arbitrage prealable de s55, instance A : section 3 sous l'issue (b), verification `A10` de s52, plafond de `G3`

`nadia-prof`, instance A, le 2026-10-08 (16:41, UTC+02:00, lu par `date`). Arbre lu : `2d0d10e7` (sommet de `round-two`, branche
`s55-nadia-a`). Complete `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md` (cite "arbitrage A" ci-dessous), qui disait en section 10
ne traiter ni `A10` ni le plafond de `G3`. Routage : `DEC-0115` amendement A.8, planning de s55 section D (ligne "section 3 vaut sous
(b), A10, plafond de `G3`", porte `G-NADIA-A`).

**Ce document ne prononce aucun Done, aucun verdict, aucun statut de matrice. Il ne cree aucun identifiant de condition ou de reserve.**
Il ne touche ni catalogue, ni paquet, ni code, ni matrice.

## Resume

| Ligne | Reponse | Prix |
|---|---|---|
| 1. Section 3 de l'arbitrage A sous (b) | **Vaut telle quelle**, prononcee a la date de ce document. Rien ne change dans `M1` a `M6`, `X1` a `X7`, 3.4 | 0,0 (dans les 0,25 de la forme) |
| 2. `A10` : s52 contre les formes (a), (b), (c) | **Condition (ii) remplie pour s52** : aucune occurrence. Le dernier sprint parallele clos est desormais s54, pas s52 | **0,25** |
| 3. Plafond de `G3` | **`en cours` pour les deux lignes**, ecrit a s48 et inchange. Le rang 4 de `DEC-0115` section 3 tient sans amendement | 0,0 |

---

## 1. La section 3 vaut sous l'option (b)

**Confirmation, en une ligne : sous l'amendement A.1 de `DEC-0115` (toutes les arts plastiques CP a l'instance A), la section 3 de
l'arbitrage A vaut telle qu'elle est ecrite, et elle est prononcee a partir de ce document.**

**Ce que (b) change : la condition de prononce seulement.** La section 3 etait "prononcee dans l'issue (b) seulement" ; (b) est
retenue, elle l'est. Aucun mot lu par l'enfant (`M4`, `M5`), aucune exigence (`M1` a `M6`), aucun interdit (`X1` a `X7`), aucune regle de
paires (3.4) ne bouge. Le motif de (b) etait la frontiere entre `cp-arts-oeuvres-familles` et `ART-CP-10` ; tenue par une seule instance,
elle ne demande rien de plus a la section 3. Les points 2 et 5 d'`ADR-0104` restent ce que j'ai ecrit : a juger sur un paquet, en jouant
(`DEC-0115` A.5, point 1).

### 1.1 Ce que je juge a s55 sur le dispositif, sans paquet

Sans entree de catalogue, aucun ecran n'est servi a un enfant : **je ne peux pas jouer**. Ce que je ferai a s55 est une **lecture**,
donc un audit degrade par nature, et elle ne produit ni verdict ni Done :

| Objet | Ce que je lis | Contre |
|---|---|---|
| T2 (garde-fous 1 a 3 d'`ADR-0104` decision 7) | les messages d'echec et la population de chaque garde-fou | quelle propriete de ma section 3 chacun tient : invariant 1 tient `X4`, invariant 3 tient le premier cas de `X5` (meme identifiant), invariant 2 la reference existe. Je ne juge pas le code |
| T3 (rendu, revelation, ecran de fin, garde-fou 4) | le gabarit livre par `lea-dev` et le rendu de ses tests | `M4` et `M5` mot pour mot et a leur place (`M4` remplace *"Appuie sur la bonne réponse :"* et la reponse en texte) ; `M6` a la revelation seulement ; `X2` aucun numero, lettre ni legende dans la branche bouton-image ; `X7` `reponseAttendueVisible` a `false` ; `X1` couvert par le garde-fou 4 sur noeud de texte, `alt`, `aria-label`, `title` |

Un ecart trouve a cette lecture se renvoie a `lea-dev` avec le texte de la section 3 ; ce n'est pas un refus, il n'y a rien a refuser.

### 1.2 Ce que je laisse a l'audit du paquet illustre (s56 au plus tot, 2,5)

Tout ce qui ne se voit qu'a l'ecran : `M1` (meme ecran sans defilement, meme taille rendue), `M2` (reference hors du choix, forme
d'`ines-design`), `M3` (apres une erreur), la marque de revelation de `M4` a l'ecran, `X3`, le second cas de `X5` (recadrage, detail ou
miroir de la reference), `X6`, les regles 1 a 6 de 3.4 sur des paires reelles, la visibilite de chaque propriete a 176 px CSS, et ce que
la forme vaut pour un enfant de CP.

**Ce dont je reste la seule garde apres T2 et T3** : `M1`, `M2`, `M3`, `X2`, `X3`, le second cas de `X5`, `X6`, et les mots de `M4` et
`M5` (le garde-fou 4 exige un nom accessible non vide, pas ces mots). L'equipe ne doit pas les croire couverts par la CI.

---

## 2. `A10` - s52 contre les formes (a), (b), (c)

Routee depuis la cloture de s52 (`retro.md` de s52, A1 ; `review.md` de s52, obstacle 16 ; `review.md` de s53, obstacle 15 ; `retro.md`
de s54, A10). Formes inchangees (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-borne.md`) ;
methode et lecture de la forme (c) celles de `ARBITRAGE-PREALABLE-s52-instances-ce1.md` section 2 (ecriture concurrente d'une meme
ressource physique par deux instances).

**s52 : deux couloirs paralleles**, A (francais CE1) et C (mathematiques CE1). Fenetres lues dans les transcriptions (UTC+02:00, premier et
dernier horodatage) :

| Couloir | Agent | Fenetre | Worktree | Ports servis |
|---|---|---|---|---|
| A | `marc-dev` | 21:11:48 - 22:29:10 | `recreo-s52-nadia-instance-a` | 18811 a 18813 |
| A | `nadia-prof` A | 21:11:57 - 22:39:49 | `recreo-s52-nadia-instance-a` | 18830, 18831 |
| C | `lea-dev` | 21:16:40 - 23:09:43 | `recreo-s52-nadia-instance-c` | 18850 a 18852 |
| C | `nadia-prof` C | 21:16:47 - 23:19:20 | `recreo-s52-nadia-instance-c` | 18870, 18872 |

**Transcriptions lues**, hors du depot, sous `$HOME/.claude/projects/<projet>/1dc17d75-1f5a-4b9f-af1c-ba3edf258c62/subagents/` :
`agent-ae815be57de627b51` (`marc-dev`), `agent-a68aa70640c10ff64` (`nadia-prof` A), `agent-a74dfb72b1aee7e43` (`lea-dev`),
`agent-a1d78738f6d57fe7f` (`nadia-prof` C) : 642 appels d'outil. **Non lues** : la session principale, les agents hors couloir.
**Methode** : un script de poste dans mon sous-repertoire de scratchpad liste chaque appel avec son horodatage ; chaque `Write`, `Edit`,
chemin de scratchpad, port et commande de construction est attribue a son agent. Fusions et commits lus par `git`.

### 2.1 Forme (a) - aucune occurrence

| Constat | Methode |
|---|---|
| Les six consignes de s52 sont ecrites une fois, dans le commit qui cree l'entree, jamais modifiees ensuite | `git show --numstat` de `catalogue.yaml` sur `git rev-list --no-merges b4ce7d93..9d44d510` : six commits `6 0` (`4fb9afc4`, `bd56d39b`, `befc4128`, `92344c23`, `46e69b13`, `fb1c7ad3`), un retrait `0 12` (`2278bdfb`) |
| Les paquets modifies apres creation l'ont ete sous leur consigne, et les seconds passages ont joue le paquet final | `40989ba9` (present, et un commentaire de `ce1-categories`), second passage VALIDE sur ce sha ; `bc7cf6a0` (zones de la symetrie), second passage VALIDE sur ce sha ; `e1fcec51`, `3bc2e501` : prenoms et indices de paquets de s51, consigne non touchee ; `d8ace7c1` (relecteur) : commentaires YAML seuls |

### 2.2 Forme (b) - aucune occurrence

| Chiffre | Lecture |
|---|---|
| Registre : 920 (A), 921 (C), **922** a la fusion `40daa419`, 158 prefixes | verifie par le garde-fou `PAR-5` a la fusion (`review.md` de s52, obstacle 3) ; chaque instance a ecrit sur sa copie que la fusion reverifiait (note de A dans son registre) |
| 2172 tests, 0 echec apres `173b48f7` | mesure par deux tiers sur deux arbres ; non recompte par moi |
| 8,0 engages, 9,0 consommes (A 4,5, C 4,5) | coherent avec les deux rejeux (`FRA-CE1-17`, `MAT-CE1-17`) |
| 568 trailers "Opus 5.5" au planning, faux (somme de quatre libelles) | **pas une forme (b)** : chiffre de depot ecrit par `sofia-sm` avant l'ouverture des couloirs, faux par un prefixe de `grep`, trouve par son autrice. Rien ne le produit a la jointure de deux instances |

### 2.3 Forme (c) - aucune occurrence

| Ressource | Ce que les transcriptions montrent | Qualification |
|---|---|---|
| Scratchpad | Un sous-repertoire par agent, comme le planning de s52 l'ecrivait : 41 references a `instance-a/marc-dev`, 29 a `instance-a/nadia`, 91 a `instance-c/lea-dev`, 63 a `instance-c/nadia` ; **aucune** vers l'autre couloir. Une lecture : `nadia-prof` A lit `instance-a/jouer2.py` (fichier de s51) a 22:00:51 et en ecrit une copie dans son propre sous-repertoire | pas de forme (c) |
| Worktrees | `Write` et `Edit` : `marc-dev` et `nadia-prof` A dans le seul worktree A (7 et 7) ; `lea-dev` et `nadia-prof` C dans le seul worktree C (8 et 7) ; aucun dans le checkout principal | pas de forme (c) |
| Ports | tous dans la plage de leur agent (planning de s52, "Ports") | pas de forme (c) |
| `REGISTRE-identifiants.md` | deux copies, deux worktrees ; identifiants distincts (`SYC-CE1-R1` ; `MUL-CE1-R1`, `SYM-CE1-R1`) ; A savait l'autre (*"l'instance C ecrit sur la sienne, et la fusion le reverifie"*) | pas de forme (c) |
| Fichiers touches par les deux couloirs | `catalogue.yaml`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest` : chaque ligne ajoutee par A (contre `e5ec4456`) et par C (contre `5b199825`) est presente dans `40daa419`, zero manquante. Registre : seules manquent les lignes de compte 920 et 921 et la note de A sur 920, remplacees par 922 | pas de forme (c) |

**A l'interieur du couloir C, la regle 1 du planning de s52 a tenu** : les `clean verify` de `lea-dev` dans le worktree C (21:22,
21:36, 22:00, 22:57) tombent hors des serveurs de `nadia-prof` C (arretes a 22:28:50 et 22:49:22) ; celui de 22:23, pendant un serveur
de `nadia-prof` C, est lance dans une copie de son scratchpad. Son serveur de 23:05:50 est arrete dans la meme commande, avant le
`package` de `nadia-prof` C a 23:10:57, qui copie le jar dans son sous-repertoire avant de le servir.

### 2.4 Correction de deux faits de la review de s52 (obstacle 5, fichier `c3`)

La review ecrit *"Auteur non etabli"* et *"Ni lea-dev ni nadia-prof ne l'a declare"*. Les transcriptions disent autre chose :

1. **Auteur : `lea-dev`**, a 23:05:50, par `curl -c c3` lance depuis la racine du worktree C. Un seul ecrivain, dans son propre
   couloir : un fichier ecrit hors du scratchpad (`squad/README.md` section 9), **pas une forme (c)**.
2. **`nadia-prof` C l'a declare** dans son message de retour (23:19), sous un titre "Collision" : *"ce n'est pas le mien [...] il reste a
   `sofia-sm` de l'attribuer"*. La regle 3 du planning de s52 admettait le message de retour ; le depot n'en avait pas la trace.

Je n'edite pas les fichiers de s52 ; la correction est ici.

### 2.5 Verdict

> **Condition (ii) remplie pour s52** : s52 se clot sans occurrence des formes (a), (b) ou (c), verifie le 2026-10-08 contre les trois
> formes, transcriptions des deux couloirs comprises.

**Ce que ce verdict n'ouvre pas.** La condition (ii) se lit sur le **dernier** sprint parallele clos, qui est s54 (trois instances), pas
s52. Ce document ferme une dette ; il ne derive aucune borne a trois instances. La prochaine derivation lit s54, avec la condition 2 de
`arbitrage-nadia-c.md` de s54 (section 3.4) ; cette verification-la n'est routee a personne aujourd'hui, et elle n'est pas faite.

---

## 3. Plafond de `G3`

`G3` : `ce1-mots-irreguliers` (`FRA-CE1-03`, 20 items) et `ce1-mots-invariables` (`FRA-CE1-21`, 24 items), aucune entree de catalogue.

**Il ne me manque rien : le plafond est ecrit depuis s48, et il tient.** Methode de la section 6 de l'arbitrage A : en-tete, forme,
cellule de matrice, conditions ecrites.

| Ligne | Plafond | Source | Ce qui le confirme a `2d0d10e7` | Ce qui le releverait |
|---|---|---|---|---|
| `FRA-CE1-03` | **`en cours`**, mention *"lecture exacte des mots irreguliers servie ; reconnaissance immediate des mots frequents non servie"* | `ARBITRAGE-PREALABLE-s48-francais-ce1.md`, 3.3 et 12 | en-tete `# DEGRADE` qui reprend ce motif ; forme "quel mot rime avec" ; cellule `non couvert`, rien d'autre | **rien que je connaisse** : reconnaitre, c'est lire sans dechiffrer, une question de vitesse, et je ne demande pas de chronometre |
| `FRA-CE1-21` | **`en cours`**, mention *"reconnaitre la graphie juste d'un mot invariable ou frequent servi ; l'ecrire sans modele non servi"* | idem, 5.12 et 12 | en-tete `# DEGRADE` et ligne *"Plafond 'en cours'"* ; choix parmi trois graphies ; cellule `non couvert` | l'extension de la premisse de l'adulte au CE1 (dictee, `FRA-CE1-29`), **decision de l'utilisatrice** ; ou mon arbitrage de la saisie alphabetique au CE1, **document que je n'ai pas ecrit**, demande par `camille-po` via `sofia-sm` : je ne peux pas en fixer le prix aujourd'hui. Aucune `DEC` posterieure a s48 ne fait l'un ou l'autre |

**Consequences.** Le motif du rang 4 de `DEC-0115` section 3 (*"ne ferment aucune ligne"*) tient pour `G3` : **pas d'amendement du
rang**. Un lot de `G3` ferait passer une ligne de `non couvert` a `en cours`, jamais a `couvert-valide` ; son audit serait au tarif d'un
premier passage dont je suis la source des motifs, **1,0 par lot**. Sous `PAR-2`, il va a l'instance qui porte le francais CE1 dans le
sprint qui le prend.

---

## 4. Prix et capacite de A

| Poste | Prix |
|---|---|
| Ligne 1 | 0,0 : prononcer la section 3 etait compris dans ses 0,25 (`DEC-0115` A.2) ; la lecture de 1.1 aussi |
| `A10` | **0,25** : quatre transcriptions, deux fusions, douze commits ; le volume de mes plafonds de `G7` a `G11` |
| Plafond de `G3` | 0,0 : deux en-tetes, deux cellules, un plafond deja ecrit (le prix de mon plafond d'`ART-CP-10` a s54) |

**A passe de 3,75 a 4,0 : `PAR-9` tenue, reserve 0.** Ce poste consomme les 0,25 dont l'emploi est la question `Q-U4` a l'utilisatrice
(`DEC-0115` A.3). Je ne tranche pas `Q-U4` ; je dis le fait : le travail est fait, et tout autre emploi de ces 0,25 porterait A a 4,25,
au-dessus de `PAR-9`, sans ecart ecrit.

## 5. Routage

| A qui | Quoi |
|---|---|
| `sofia-sm` | porte `G-NADIA-A` levee par la section 1 ; capacite de A a 4,0, reserve 0 (section 4) ; `A10` fermee, avec A1 de la retro de s52 et l'obstacle 15 de s53 ; correction de l'obstacle 5 de s52 (2.4) a reprendre dans la review de s55 si elle le juge utile |
| `camille-po` | `Q-U4` (section 4) ; rang 4 inchange (section 3) ; la derivation d'une borne a trois instances lit s54, non verifiee (2.5) |
| `lea-dev` | section 1.1 : ce que je lirai sur T3 ; les mots de `M4` et `M5` sont dans l'arbitrage A, 3.2, et ne sont pas recopies ici |

**Signe** : `nadia-prof`, s55, instance A, 2026-10-08.
