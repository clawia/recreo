# DEC-0083 - Strategie de s32 a s34 : passage a trois instances de `nadia-prof`, borne a 12,0
# decotee par paire de coutures, le Plan 2 (couverture soutenable) retenu contre le Plan 1
# (couverture maximale) vu et ecarte, le palier 2 differe a l'instruction de `theo-archi`

- **Type** : arbitrage de l'utilisatrice, enregistre et non rouvert ; decisions de perimetre, de
  chiffrage et de routage de `camille-po` qui en decoulent.
- **Date** : 2026-09-08.
- **Auteur** : `camille-po`.
- **Sprints** : s32 (perimetre ferme), s33 et s34 (perimetre indicatif, gouverne par les memes
  clauses).
- **Statut** : Acceptee, amendee le 2026-09-08 (meme jour, section 13 - quatrieme arbitrage, le
  palier 2 finance a son entree minimale).
- **Lien** :
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`
  (`nadia-prof`, commit `f503b4e5`, arbitrage prealable complet - chiffres, clauses et conditions
  repris sans etre recalcules ; sections 16 et 17, meme fichier, commit `b1ee89f4`, amendement du
  2026-09-08 - la barre de longueur d'enonce au CP et le compte reel de fermeture du palier 2),
  `DEC-0077` (section 8.3, precedent de forme pour "vu et non retenu"), `DEC-0079`, `DEC-0080`,
  `DEC-0081`, `DEC-0082` (strategie et clotures de s29 a s31, `PAR-1` a `PAR-11`), `ADR-0081`
  (codes de ligne de referentiel), `ADR-0085` (`PAR-5`, registre des identifiants), `ADR-0080`
  (palier 2, statut *Propose*, amende par `theo-archi` le 2026-09-08), `DEC-0074` (referentiels EMC
  CP, sciences CM1, EPS CP et CM1), `DEC-0075` (corpus d'oeuvres, question de droits ouverte),
  `squad/sprints/s32-instruction-prealable/avis-faisabilite-theo-achevement-du-palier-2.md`
  (`theo-archi`, branche `s32-theo`, commit `96c8efbe` - **branche non fusionnee dans `round-two` a
  la date de cet amendement**. Correction du 2026-09-09, `camille-po` : cette citation portait
  `dcbcd65a`, un commit orphelin, atteignable depuis aucune branche ni aucun tag - residu d'un
  geste anterieur a une reecriture de commit, passe par erreur depuis un rapport anterieur.
  `96c8efbe` porte le meme message et le meme contenu de fichier, et il est atteignable depuis
  `round-two`), `squad/programme/MATRICE-couverture.md`, `docs/backlog/backlog.md`
  (sections "Sprint 31", "Backlog ouvert pour s32", "Backlog ouvert pour s33").

---

## 1. L'arbitrage de l'utilisatrice, rendu le 2026-09-08

Trois decisions, dans l'ordre ou elle les a rendues :

1. **Le produit passe a trois instances de `nadia-prof` en parallele**, a partir de s32.
2. **Elle retient le Plan 2** de `nadia-prof` (couverture soutenable), et non le Plan 1 (couverture
   maximale), **en connaissance du chiffre qu'il coute** : 7 lignes de plafond sur trois sprints (20
   au lieu de 27, 60,1 % au lieu de 65,2 % au plafond arithmetique), contre 9,5 de dette soldee (voir
   section 4). **Elle a vu le Plan 1, qui fermait sept lignes de plus, et elle a choisi le Plan 2
   quand meme** - c'est la meme forme d'arbitrage que celle que `DEC-0077`, section 8.3, avait deja
   tracee pour la variante B a s29, et elle se lit de la meme facon : le chiffre le plus genereux est
   vu, pese, et ecarte pour un motif ecrit, pas ignore.
3. **L'achevement du palier 2 n'est ni retenu ni ecarte.** Il part a l'instruction de `theo-archi`,
   avis de faisabilite lance en parallele de la redaction de cette decision. La decision de perimetre
   sur les dix lignes qu'il debloquerait est **differee** a la remise de cet avis. Cette decision ne
   la prend pas a sa place et ne la presente comme acquise dans aucun sens.

**Tous les chiffres cites ci-dessous sont ceux de `nadia-prof`**, repris de son arbitrage prealable
sans etre recalcules. La ou une operation arithmetique simple les combine (par exemple une somme de
postes deja chiffres), c'est signale explicitement.

## 2. La borne a trois instances : 12,0, decotee par paire de coutures

**Capacite brute** : 3 instances a 5,0 chacune = **15,0**.

**Retenue de defiance sur la couture, comptee par paire, pas par instance** : `nadia-prof` tranche
elle-meme la question qui lui etait posee sans reponse imposee - l'objet d'une couture est une
jointure entre deux travaux, pas une instance. Avec n instances, il y a n(n-1)/2 paires ; a trois
instances, il y en a **trois**. Retenue : **1,0 par paire, soit -3,0**.

> **Borne de s32, s33 et s34 a trois instances : 15,0 - 3,0 = 12,0.**

**La verification qui fait que ce n'est pas un chiffre invente** : 12,0 / 3 = **4,0 par instance**,
exactement le budget opposable par instance de `PAR-9`, tenu a l'unite a s31 (8,00 sur 8,0, instance
A 4,00/4,00, instance B 4,00/4,00). `nadia-prof` n'eleve la charge de personne : elle ajoute une
instance a la charge que les deux autres ont deja soutenue deux fois.

**Ce que la passe transverse et les dettes de portee D ne font pas a cette borne** : elles ne sont
pas retranchees en amont, elles se depensent dedans, poste par poste (motif : leur objet ne depend
pas du nombre d'instances). Aucun poste distinct n'est ajoute pour le premier sprint de la troisieme
instance : la decote par paire le couvre deja (les deux paires neuves sont son exposition).

**Contingence que `nadia-prof` ecrit elle-meme et que cette decision ne declenche pas, l'utilisatrice
tenant le passage a trois instances** : si ce passage n'etait pas tenu, sa borne resterait 8,0. Cette
decision le tient ; le point est cite pour memoire, pas pour arbitrer une hypothese ecartee.

### 2.1 Ce qui fait tomber la retenue - et c'est atteignable des la cloture de s32

> **Si s32 clot sans aucune occurrence des trois formes de defaut de couture que `nadia-prof` a
> definies (section 3 de son arbitrage), la retenue de 3,0 tombe en une seule fois, en bloc et non
> paire par paire (une condition qui ne peut pas s'attribuer a une paire precise n'est pas une
> condition), et la borne de s33 passe a 15,0.**

**Ce que cela vaut, dit pour que l'incitation reste visible** : 3,0, soit l'equivalent de deux lots
de contenu neuf sur s33 et s34, sans couter une ligne de couverture - seulement de la discipline a
la jointure. **Les compositions chiffrees de la section 6 pour s33 et s34 sont batties sur une borne
constante de 12,0** : si la retenue tombe, la capacite disponible a s33 est superieure a ce que ces
compositions depensent, et ce surplus n'est engage dans aucun poste de cette decision.

**Le critere de couture ne bouge pas**, `nadia-prof` l'a rejuge elle-meme le 2026-09-08 et le
maintient a l'identique : a trois instances, la famille de defaut qui survit (un agregat qu'aucune
branche ne voit entierement) est precisement celle que le passage a trois instances multiplie.

## 3. Clauses neuves ou amendees, opposables des s32

Reprises mot pour mot de l'arbitrage de `nadia-prof`, datees du 2026-09-08 :

- **`PAR-9`, clause de transfert.** Aucun budget ne se transfere d'une instance a une autre en cours
  de sprint sans etre ecrit au moment du transfert, dans le fichier de planning du sprint, avec
  l'instance qui cede et le poste cede. Le plafond de `PAR-8` (5,0) borne le transfert. Motif : a
  trois instances, un transfert non ecrit se noie dans le total et devient un chiffre qu'aucune
  instance ne peut verifier depuis sa moitie.
- **`PAR-11`, amendee, applicable a s32 et au-dela, non retroactive.** 0,5 par lot neuf audite, par
  instance (inchange), **plus 0,5 pour toute instance qui audite une matiere dont les conditions de
  `nadia-prof` n'ont jamais rencontre de contenu reel**. La marge du sprint est leur somme. Motif,
  mesure sur les deux seules populations disponibles : les deux taux de refus au premier passage de
  ce depot (EMC a s29, quatre paquets refuses sur cinq ; sciences a s31, deux paquets sur quatre)
  portent tous les deux sur une matiere a conditions inedites, et aucune matiere dont les conditions
  avaient deja mordu du contenu n'a produit ce taux.
- **`PAR-12`, nouvelle - generalisation de `PAR-7`.** Avec n instances, une seule porte
  l'accumulation. Elle prend les travaux de portee D (passe transverse, `CONS-*`, verification de
  `SCO-1`), les lignes `en cours` et les matieres qu'elle a deja auditees. Les autres prennent un
  terrain chacune. Le motif de `PAR-7` - on ne demande pas a une instance de rattraper une image
  qu'elle n'a pas - reste intact.
- **`PAR-13`, nouvelle.** Au plus une instance par sprint prend un terrain dont les conditions de
  `nadia-prof` n'ont jamais rencontre de contenu reel. Motif : c'est le profil exact des deux seuls
  taux de refus au premier passage mesures a ce jour, et `nadia-prof` n'a aucune mesure de deux tels
  terrains menes en parallele.
- **`PAR-2` rappelee, avec sa consequence sur le residu du groupe 2a** : une matiere et un niveau ne
  sont jamais partages entre deux instances. Un rejeu de paquet est un travail sur une matiere ; il
  va donc a l'instance qui detient cette matiere, jamais a l'instance d'accumulation. **L'instance
  d'accumulation cesse d'etre le depotoir des dettes** qu'elle pouvait etre a deux instances : elle
  ne garde que la portee D et les lignes `en cours`.

**Aucune clause anterieure n'est durcie.** `PAR-1` a `PAR-8`, `PAR-10`, `CYC-1`, `PLA-1`, `FAB-1`,
`BIN-1`, `CAT-1`, `EPS-1` a `EPS-3` restent intactes.

## 4. Deux corrections que `nadia-prof` a faites a ses propres constats de s31, tenues telles quelles

- **Le residu du groupe 2a coute 3,0, et non 1,5** comme elle l'avait ecrit le 2026-09-07 : dix
  reserves se repartissent sur huit paquets, non dix ; le groupe 1 (paye a s31) en a solde deux, pas
  trois. Ses deux chiffres precedents etaient faux et tous les deux dans le sens qui allegeait la
  dette - le meme genre d'ecart que celui que `camille-po` avait corrige a la cloture de s31
  (`DEC-0082`, section 1), en deux fois plus gros. **Le registre de dette de la section 7 reprend le
  chiffre corrige, 3,0.**
- **Le reliquat de `CONS-1` sur `histoire-geographie-cm1` a change de forme depuis le 2026-09-07** :
  la consigne servie aujourd'hui nomme quatre taches, pas deux, mais deux des dix manches jouees par
  `nadia-prof` demandent une tache qu'aucune des quatre ne nomme, et cela tombe exactement sur trois
  des six lignes `en cours` (`HG-CM1-03`, `-04`, `-08`). **Ce fait gouverne les criteres d'acceptation
  de `S32-1` (section 6) pour les deux de ces trois lignes que s32 ouvre.**

## 5. Le choix entre les deux plans, chiffre des deux cotes

**Plan 1 - couverture maximale.** Plafond arithmetique sur trois sprints : **27 lignes** (63 + 27 =
90 / 138 = **65,2 %**). Il finance le groupe 1 des dix-sept reserves de s30-31 et sature la borne
chaque sprint, mais **il laisse la dette differee (0,5 par ligne ouverte par un lot neuf, section
7.3 de l'arbitrage) s'accumuler sans la solder dans le sprint qui l'ouvre**. Solde de dette sur
trois sprints : s32 -1,0 (paye plus qu'il ne cree), s33 +2,0, s34 0,0, total **+1,0**. **Le Plan 1
solde le registre actuel de 7,5 (voir section 7) et clot s34 sur un registre de 10,0 : trois sprints
a capacite maximale laissent la dette plus grosse qu'ils ne l'ont trouvee.**

**Plan 2 - couverture soutenable, retenu.** Seule difference structurelle : **les reserves non
bloquantes qu'un lot neuf ouvre se soldent dans le sprint meme qui l'a ouvert**, a 0,5 par paquet
neuf, dans la borne. Plafond arithmetique sur trois sprints : **20 lignes** (63 + 20 = 83 / 138 =
**60,1 %**). Registre de dette a la cloture de s34 : **environ 0,5, plus `CONS-3`** - le registre est
solde.

| | Plan 1 | Plan 2 |
|---|---|---|
| Plafond de lignes sur trois sprints | 27 | **20** |
| Taux plafond a la cloture de s34 | 65,2 % | **60,1 %** |
| Registre de dette a la cloture de s34 | 10,0 + `CONS-3` | **~0,5 + `CONS-3`** |
| Lignes d'EPS fermees sur trois sprints | 6 | **4** (les deux dernieres, EPS CM1, tombent a s35) |

**Le Plan 1 achete 7 lignes de plafond contre 9,5 de dette. L'utilisatrice a vu ce chiffre et a
retenu le Plan 2 quand meme**, pour les deux motifs mesures que `nadia-prof` donne : une reserve
differee ne reste pas ou elle est (`CAT-1` a change de nature entre deux sprints, et le residu du
groupe 2a coute deux fois ce qu'il etait chiffre) ; et le Plan 1 est le pari de s29 refait a une
echelle triple, au moment ou ce pari a deja ete mesure defavorable (section 8).

## 6. Composition de s32, chiffree instance par instance

**Borne du sprint : 12,0. Budget opposable par instance : 4,0** (`PAR-9`), marge de rejeu comprise.
Plafond individuel dur : 5,0, au plus trois lots par instance (`PAR-8`, inchange).

| Instance | Postes | Total | Lignes | Paquets neufs |
|---|---|---|---|---|
| **A** | Remplissage `histoire-geographie-cm1`, tranche 1 : `HG-CM1-01`, `-02`, `-03`, `-04` (35 items) **2,5** ; remplissage `LVE-CM1-07` (5 items) **0,5** ; passe transverse (`PAR-4`, `PAR-12`) **0,5** ; marge `PAR-11` **0,5**. `HG-R8` et le reliquat de `CONS-1` voyagent gratuitement dans le meme rejeu (`PAR-10`) | **4,0** | **+5** | **0** |
| **B** | `D1` - `CAT-1` + `REG-R1` + `PER-R1` (`S32-CAT1`) **1,0** ; `D2` - `EMC-CP-07` sur `FAB-1c` seul (`S32-EMCCP07`) **0,5** ; `D5` - trois paquets d'EMC CP, dix reserves (`S32-4`) **1,5** ; marge (cinq paquets rejoues, aucun lot neuf) **0,5** ; marge non allouee, reportee **0,5** | **4,0** | **+1** (si `FAB-1c` est tenue sur `EMC-CP-07` ; cette decision ne prejuge pas du verdict) | **0** |
| **C** | Premier lot EPS CP - regles (`EPS-CP-04`, `EPS-CP-08`, `S32-5`) **1,0** ; reserves du lot solde dans le sprint (Plan 2, `PAR-10`) **1,0** ; marge `PAR-11` amendee (0,5 par lot neuf audite + 0,5 conditions inedites, `PAR-13`) **1,5** ; marge non allouee, reportee **0,5** | **4,0** | **+2** | **1** |
| | | **12,0** | **+8** | **1** |

**Ce que cette composition finance** : la fermeture des quatre lignes d'histoire-geographie de la
tranche 1 et de la ligne d'anglais deja `en cours` (aucune surface de couture, ces items entrent dans
deux paquets existants), le paiement integral du report de `CAT-1`/`REG-R1`/`PER-R1` a son quatrieme
sprint, une nouvelle tentative sur `EMC-CP-07`, le solde des dix reserves ouvertes sur trois paquets
d'EMC CP a s31, et l'ouverture de l'EPS avec ses deux lignes les moins couteuses.

**Ce que cette composition ne finance pas a s32** : la passe D4 (groupe 2b, reliquat de `CONS-1` sur
les lignes non ouvertes ce sprint, `CONS-2`, `SAI-R1`, `ERR-R1`), `D7` (verification de `SCO-1`),
aucun lot de sciences CM1 (deja `couvert-valide` pour six lignes, terrain de B pour la suite), aucun
lot d'EMC CM1, et le second lot d'EPS CP (`EPS-CP-10`, `-11`, la ligne de corps-et-effort - voir
section 8).

**Repartition des trois terrains, par objet de travail** (`nadia-prof`, section 6.2 de son
arbitrage - la correction qu'elle apporte a une lecture qui lui avait ete proposee est tenue telle
quelle) :

- **Terrain A** : histoire-geographie CM1, anglais CM1 (decouverte culturelle), et la portee D
  (passe transverse, `CONS-*`, verification de `SCO-1`) ainsi que les lignes `en cours`, par
  `PAR-12`. **A ne recoit aucune dette de matiere autre que la sienne** : la lecture qui lui aurait
  fait porter aussi les dettes est explicitement corrigee par `nadia-prof`, qui l'ecrit contre une
  proposition qui lui avait ete faite en ce sens.
- **Terrain B** : sciences et technologie CM1, qui en a l'image depuis s30 et s31 (quatre lignes
  fermees, `SCI-1` a `SCI-4` eprouvees). Aucun lot de sciences n'est budgete a s32 (voir composition
  ci-dessus) ; le terrain reste sien pour s33 et s34. `SIG-R1`, `SIG-R2` et `CHA-R1` (residu du
  groupe 2a sur sciences CM1) le suivent par `PAR-2`.
- **Terrain C** : l'education physique et sportive, aux deux niveaux. Seul terrain de cette taille
  dont aucune instance n'a l'image ; `PAR-13` s'applique (une seule instance par sprint sur un
  terrain a conditions inedites), et c'est le motif de sa marge `PAR-11` amendee.

**Deux paquets du residu du groupe 2a n'ont pas de terrain nomme parmi A, B ou C dans l'arbitrage de
`nadia-prof`** : `POS-R3` (mathematiques CM1) et `FOP-R4`/`DRO-R4` (EMC CM1). Elle les route a
« l'instance a qui les mathematiques CM1 [respectivement l'EMC] sont explicitement attribuees »,
sans nommer laquelle des trois lettres cela designe. **Cette decision ne force pas de lettre sur ce
point** : l'affectation nominative revient a `sofia-sm`, qui devra soit ouvrir un quatrieme terrain
ponctuel pour ces deux paquets, soit les rattacher a une instance existante en le motivant, avant
que `POS-R3`, `FOP-R4` ou `DRO-R4` n'entrent dans une composition chiffree (ils figurent au
perimetre indicatif de s34, section 8, sur la foi de l'arbitrage de `nadia-prof`, sans que la lettre
qui les portera soit tranchee ici).

**Plafond arithmetique de s32 : 8 lignes.** 63 + 8 = 71 / 138 = **51,4 %**.

**Attente raisonnable de `nadia-prof` pour s32 : 6 a 7 lignes**, soit 69 a 70 / 138 (50,0 % a
50,7 %). Motif chiffre : A ferme 5 sur 5 attendues (conditions eprouvees trois sprints, aucun paquet
neuf) ; B ferme 1 sur 1 attendue sur `EMC-CP-07` mais c'est un troisieme passage sur `FAB-1c`, la
propriete qu'elle ne tient que partiellement ; C ferme 0 a 1 sur 2, profil `PAR-13` (terrain a
conditions inedites, aucune mesure de calibration disponible).

## 7. Le registre de dette a l'ouverture de s32, et ce qu'il devient

**Dette ouverte a l'ouverture de s32 : 7,5, plus `CONS-3` non chiffree** (`nadia-prof`, section 7.1
de son arbitrage - `D3` corrigee a 3,0 conformement a la section 4 ci-dessus) :

| Code | Poste | Prix | Sprint qui le paie |
|---|---|---|---|
| `D1` | `CAT-1` + `REG-R1` + `PER-R1`, quatrieme sprint de report | 1,0 | s32 (`S32-CAT1`) |
| `D2` | `EMC-CP-07`, refusee sur `FAB-1c` seul | 0,5 | s32 (`S32-EMCCP07`) |
| `D3` | Residu du groupe 2a, corrige a 3,0 (section 4) | 3,0 | s33 et s34, en morceaux suivant `PAR-2` |
| `D4` | Groupe 2b : reliquat de `CONS-1` (hors lignes ouvertes a s32), `CONS-2`, `SAI-R1`, `ERR-R1` | 1,0 | s33 |
| `D5` | Reserves neuves de s31 (`EMO-R1/R2`, `POL-R1/R2`, `EXC-R1` a `R5`) | 1,5 | s32 (`S32-4`) |
| `D6` | `CONS-3` | non chiffree | ne se paiera pas dans ces trois sprints (section 9) |
| `D7` | Verification de `SCO-1` apres correction, portee depot | 0,5 | s33 |

**Projection a la cloture de s34, sous le Plan 2** : environ **0,5**, plus `CONS-3` toujours non
chiffree. Le registre actuel est solde par construction du Plan 2 (chaque lot neuf finance ses
propres reserves dans le sprint qui l'ouvre), a l'exception de `CONS-3` et du remede de `SCO-1`, qui
ne sont pas de la capacite d'audit et restent hors du ressort de `nadia-prof` (section 9).

## 8. Perimetre indicatif de s33 et de s34

**Indicatif, pas ferme** : il est gouverne par le resultat de s32 (couture ou non, verdicts) et par
l'avis de faisabilite de `theo-archi` sur le palier 2. Compositions chiffrees a une borne constante
de 12,0 (section 2.1 : si la retenue tombe, la borne de s33 monte a 15,0 et ce surplus n'est pas
engage ici).

**s33 - plafond 6, attente raisonnable 4 a 5** (`nadia-prof`, section 12 de son arbitrage) :

| Instance | Postes indicatifs |
|---|---|
| A | Remplissage `histoire-geographie-cm1`, tranche 2 (`HG-CM1-08`, `-12`, 18 items) ; `D4` (groupe 2b) ; `D7` (verification `SCO-1`) ; instruction des **onze** lignes sans annotation de jouabilite (`nadia-prof`, section 11 de son arbitrage, corrigee section 17.7 : `MAT-CP-10` en est la onzieme, la cellule du quadrillage n'en portait aucun motif) ; passe transverse |
| B | Un lot de sciences CM1 neuf, avec reserves soldees dans le sprint ; `SIG-R1`/`R2`, `CHA-R1` (residu 2a) |
| C | Second lot EPS CP - corps et effort (`EPS-CP-10`, `EPS-CP-11`), **sous condition explicite de la frontiere de la section 9** ; reserves soldees dans le sprint |

**s34 - plafond 6, attente raisonnable 4 a 5** :

| Instance | Postes indicatifs |
|---|---|
| A | Un lot d'EMC CM1 ; `FOP-R4` + `DRO-R4` (residu 2a, sous reserve de la lettre qui les porte, section 6) ; passe transverse |
| B | Un lot de sciences CM1, dont la ligne de collision corps-et-effort sous la frontiere ecrite (section 9) ; `POS-R3` (residu 2a, meme reserve de lettre) |
| C | Lot EPS CM1 (`EPS-CM1-09`, `-10`) ; reserves soldees dans le sprint |

**Total indicatif sur s33 et s34 : plafond 12 lignes, attente 8 a 10.** Sur les trois sprints :
**plafond 20, attente 14 a 17, soit 77 a 80 / 138 = 55,8 % a 58,0 %.**

**Le triplement de capacite cesse d'acheter de la couverture a partir de s34, et c'est arithmetique,
pas prudentiel.** Il ne l'achete que quand une instance travaille sur un terrain qui ne cree aucun
paquet neuf : ce terrain existe aujourd'hui, il compte exactement huit lignes (les sept lignes
`en cours` d'histoire-geographie et d'anglais, plus `EMC-CP-07`), et il est epuise a la fin de s33 -
sept de ces huit lignes sont visees par la tranche 1 et la tranche 2 de `S32-1`/son homologue de s33,
la huitieme par `S32-EMCCP07`. **A partir de s34, chaque ligne ouverte par un lot neuf porte a
nouveau ses 0,5 de dette differee, et le produit est revenu au marche de s29 - a capacite triple,
donc a dette triple.** Ce n'est pas un motif pour changer le perimetre de s34 ci-dessus, qui integre
deja ce cout ; c'est un fait a ne pas decouvrir a la cloture.

**Le fait de s29, applique sans l'adoucir** : la ligne marginale coute plus cher a trois instances
qu'a deux - 1,00 par ligne de plafond a s31 (deux instances), 1,20 sous le Plan 1 a s32 (trois
instances), **1,50 sous le Plan 2 retenu**. Le doublement de la borne a s29 n'avait achete aucune
couverture (`DEC-0077`, section 8.3) ; le triplement en achete a la condition precise du paragraphe
precedent, et cesse d'en acheter au-dela.

## 9. Routage de deux faits trouves en jouant, et d'un troisieme deja route

`nadia-prof` a trouve, en jouant l'etat servi le 2026-09-08, trois faits qu'aucune liste de dette ne
portait. Le troisieme (residu du groupe 2a a 3,0) est deja integre section 4 et 7. Les deux autres :

### 9.1 `SCO-1` - le score reste parfait par elimination sur 64 des 66 entrees du catalogue

Mesure le 2026-09-08 sur `bandit-manchot-anglais-cm1` : un enfant qui presse successivement les
distracteurs puis la bonne reponse sur un item a trois propositions voit son score progresser a
chaque appui, y compris sur les deux qui etaient faux. `DEC-0061` arbitre ce defaut le 2026-08-31 ;
le compteur de ses reports s'est arrete apres le second (pose par `sofia-sm` a s25) et **six
sprints (s26 a s31) sont passes sans qu'un troisieme report soit ecrit**, alors que la dette est
restee vivante. `nadia-prof` ne rouvre aucune des 63 lignes `couvert-valide` sur ce motif et ne
fait pas de sa correction une condition de Done ; elle pose une reserve datee du 2026-09-08 (chaque
fiche qu'elle ecrira a partir de la cloture de s32 nommera si le score du jeu reste parfait par
elimination) et chiffre sa propre part de reverification a 0,5 (`D7`, portee depot, planifiee a
s33).

**Routage** : le remede lui-meme (`SCO-1` a `SCO-7`) est une question de conception technique du
calcul de score, pas de contenu pedagogique. `sofia-sm` le route a `theo-archi` pour instruction du
correctif et de son cout ; `nadia-prof` n'a ni a le chiffrer ni a le concevoir, elle en verifiera
seulement le resultat (`D7`). `nadia-prof` note elle-meme que `SCO-1` a `SCO-7` sont entierement
automatisables une fois le remede choisi : la forme du test appartient a `theo-archi`, la propriete
reste la sienne.

### 9.2 La collision EPS / sciences CM1 / questionner le monde CP sur le corps et l'effort

Trois lignes de trois matieres differentes portent le meme objet - l'activite physique et le corps -
et seraient chacune servie par une instance differente sous la repartition de la section 6 :
`EPS-CP-10`/`EPS-CP-11`/`EPS-CM1-10` (instance C), la ligne de sciences CM1 sur alimentation,
activite physique, sommeil et hygiene (instance B), et la ligne de questionner le monde CP sur
l'hygiene de vie, aujourd'hui sans annotation de jouabilite (instance A). `ADR-0081`, clause 5,
interdit qu'un item ne compte que sur une seule ligne ; trois instances qui ecrivent sans se voir
produisent exactement le defaut de couture que `nadia-prof` definit - invisible de l'interieur de
chacune des trois moities.

**La frontiere, ecrite par `nadia-prof` et opposable des a present, reprise mot pour mot** :

> - L'EPS ne porte que l'effort lui-meme : ce que l'effort fait au corps dans l'instant
>   (essoufflement, coeur qui bat vite, chaleur, soif), ce qui l'entoure (echauffement, hydratation,
>   recuperation) et les regles de securite de la pratique. Jamais l'hygiene de vie en general.
> - Les sciences CM1 ne portent que les quatre facteurs de sante generale - alimentation, activite
>   physique, sommeil, hygiene - rapportes au fonctionnement du corps, jamais aux effets immediats
>   d'un effort.
> - Questionner le monde CP ne porte que le niveau CP de l'hygiene de vie, et `CYC-1` s'applique :
>   aucun item de cycle 3 ne contredit ce que le CP enseigne sur le meme objet.

`nadia-prof` refuse d'avance toute ligne des trois qui fermerait sans que cette frontiere ait ete
ecrite dans la specification de son lot, avant le premier item.

**Routage** : cout nul en unites d'audit ; ce que cela coute est une obligation de planning, et
`nadia-prof` le dit elle-meme, c'est `sofia-sm` qui la porte. **Cette decision route a `sofia-sm`
l'obligation d'inscrire la frontiere ci-dessus dans la specification de tout lot futur touchant
`EPS-CP-10`, `EPS-CP-11`, `EPS-CM1-10`, la ligne de sciences CM1 concernee, ou la ligne de
questionner le monde CP concernee, avant qu'un seul item n'en soit ecrit.** Aucune story de s32 ne
touche ces lignes (le lot EPS de s32 porte sur `EPS-CP-04` et `EPS-CP-08`, les regles, pas le corps) ;
la frontiere s'applique donc a partir des lots indicatifs de s33 et s34 (section 8).

### 9.3 L'annotation de `EMC-CP-04` - refus leve, non exploite a s32

`nadia-prof` leve, le 2026-09-08, son refus d'avance sur `EMC-CP-04` : l'annotation qu'elle
demandait existe desormais dans la matrice ("jouable au choix, sous `EMC-4`, `BIA-1` et `BIA-2`,
seulement sous la forme de l'interdit"). **Aucune story de s32 ne porte sur cette ligne** ; la levee
est citee ici pour memoire, a l'attention de quiconque composerait un futur lot d'EMC CP.

## 10. Ce qui ne se paiera pas dans ces trois sprints

- **`CONS-3`** (`D6`). `nadia-prof` ne la chiffre pas et ne l'estime pas ; cette decision ne
  l'estime pas non plus.
- **Les 7 lignes d'oeuvres**, bloquees par l'absence de repertoire de reproductions servies et par
  `DEC-0075`, question de droits toujours ouverte.
- **Les 10 lignes du palier 2**, bloquees par trois couches manquantes (`application`, `adapter-web`,
  `bootstrap`) et par `ADR-0080`, toujours *Propose*. Voir section 11.
- **Le remede de `SCO-1` a `SCO-7`**, hors du ressort de `nadia-prof` et non chiffre par elle ; sa
  seule part chiffree est `D7` (verification apres correction, 0,5, s33).
- **Les deux dernieres lignes d'EPS CM1** (`EPS-CM1-09`, `-10`) sous le Plan 2 : elles tombent a s35,
  hors du perimetre de cette decision.

## 11. Le palier 2 : ni retenu, ni ecarte, differe a l'instruction de `theo-archi`

`nadia-prof` nomme le palier 2 comme "le poste de plus grand rendement de tout ce document" - dix
lignes debloquees, plus que ce que l'EPS entier rapporte sur trois sprints - et refuse explicitement
de le chiffrer : ce n'est pas de la capacite d'audit, et elle ne decide pas de l'architecture. Trois
couches manquantes (`application`, `adapter-web`, `bootstrap`) et `ADR-0080` *Propose*.

**Cette decision ne tranche pas le perimetre sur ces dix lignes.** L'avis de faisabilite de
`theo-archi`, lance en parallele de sa redaction, gouvernera cet arbitrage a sa remise - a `s33` au
plus tot, sous forme d'une decision distincte si son avis ouvre un chantier.

## 12. Ce que cette decision ne fait pas

- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et n'en ferme aucune
  ligne : les statuts ne bougent qu'au verdict de `nadia-prof`.
- Elle ne prononce aucun Done pedagogique.
- Elle ne tranche pas le perimetre du palier 2 : voir section 11.
- Elle ne chiffre ni ne planifie `CONS-3`.
- Elle ne recalcule aucun chiffre de `nadia-prof` ; la ou une somme simple combine ses propres
  postes, cela est signale explicitement (sections 6 et 7).
- Elle ne fige pas les perimetres de s33 et s34 : ils sont indicatifs et gouvernes par le resultat
  de s32 et par l'avis de `theo-archi`.
- Elle ne nomme aucun agent sur aucune instance : l'affectation nominative revient a `sofia-sm`,
  y compris pour les deux paquets du residu du groupe 2a sans lettre nommee (section 6).
- Elle n'invente aucun critere chiffre la ou `nadia-prof` refuse d'en poser un (voir `S32-4`,
  section 6 du backlog : `EMO-R1`, `POL-R1`, `EXC-R4` restent sans barre imposee, malgre l'annonce
  d'une barre dans `squad/conformite/FICHE-bandit-manchot-exclusion-cp.md` que cet arbitrage
  prealable ne tient finalement pas - fait signale, non resolu a sa place).

## 13. Amendement du 2026-09-08 (meme jour) - quatrieme arbitrage : le palier 2 finance a son
## entree minimale, les deux trous de contrat payes d'abord

**L'utilisatrice a rendu un quatrieme arbitrage le 2026-09-08**, apres reception du chiffre que la
section 11 laissait ouvert : le compte reel de fermeture du palier 2 par `nadia-prof` (sections 16
et 17 de son arbitrage prealable, meme fichier que la section 1, commit `b1ee89f4`) et l'avis de
faisabilite de `theo-archi` sur son chemin de jeu (`squad/sprints/s32-instruction-prealable/
avis-faisabilite-theo-achevement-du-palier-2.md`, branche `s32-theo`, commit `96c8efbe` - **cette
branche n'est pas fusionnee dans `round-two` a la date de cet amendement**, et rien ci-dessous ne le
presuppose ; reference corrigee le 2026-09-09, voir la section "Lien" ci-dessus). La section 11
ci-dessus est tranchee par cet amendement ; elle n'est pas reecrite.

### 13.1 Ce qu'elle a vu, et ce qu'elle n'a pas retenu

Trois options lui ont ete presentees, chiffrees par `nadia-prof` et par `theo-archi` sans etre
recalculees ici :

- **Le palier 2 entier** : le chemin de jeu (cinq lots de developpement, `L-a` a `L-e` de l'avis de
  `theo-archi`, section 7) plus tout son contenu chiffrable a ce jour (trois lots de designation,
  base 2,0/2,5/2,5, soit 7,0 de la borne de `nadia-prof`). **Il ferme 3 lignes sur les onze qu'il
  touche, et 4 au plus sur les trois sprints s32-s34** en tenant la clause que `nadia-prof` s'impose
  depuis s29 - pas plus d'un lot de designation dans le sprint de premiere mise en service (section
  17.5 de son arbitrage). Le rendement mesure - **2,33 de sa borne par ligne fermee** - est **le
  plus mauvais de tous les postes que ce document chiffre** (section 17.5), et c'est `nadia-prof`
  qui le dit contre sa propre section 9, ecrite le matin meme sur la foi d'un compte de dix lignes
  "debloquees" qui n'en ferme reellement que trois.
- **Les deux trous seuls, sans financer aucun contenu** : payer le chemin de jeu (`L-a`, `L-b`,
  `L-c`, `L-d`) sans jamais commander le lot de designation qui l'exploite (`L-e`). `theo-archi`
  nomme lui-meme ce choix comme defendable, a la condition qu'il soit fait "les yeux ouverts"
  (section 9 de son avis) : la dette se reduit alors a des dessins et a un audit, et **aucune ligne
  de matrice ne ferme**.
- **L'avis de `nadia-prof` de ne rien financer du tout** : "je ne recommande pas de financer le
  palier 2. Trois lignes au prix de six a huit ailleurs" (section 17.5).

**Elle a vu les trois et a choisi une quatrieme voie**, a la forme deja tracee par `DEC-0077`,
section 8.3, pour "vu et non retenu" : **l'entree la moins chere que `nadia-prof` nomme elle-meme
comme la plus sure** (section 17.5) - **un seul lot de designation, `QLM-CP-11` + `QLM-CP-14`, base
1,0 chacune (`nadia-prof` en est la source), prix 2,0, pour deux lignes fermees**. Aucun fait a
verifier contre une source, aucun probleme de taille d'image, aucun besoin de reproduction d'oeuvre,
trois dessins (un plan de classe, deux frises de la vie de l'enfant). C'est le seul endroit du
palier 2 ou le rendement - 1,0 de sa borne par ligne - approche celui du reste du depot, et c'est le
lot ou ses huit conditions `ZON-*`, jamais encore mordues sur du contenu reel, le rencontrent au
moindre risque (`PAR-13`).

**Ce que cette entree ne finance pas, et que cette decision ne finance donc pas non plus** :
`QLM-CP-12` (necessite un fait verifie contre une source, base 2,5, mention `VUE` due), et les deux
paquets textuels qui ouvriraient sans fermer `MAT-CP-10` et `HDA-CM1-09`, ni la taille d'image plus
grande que `HG-CM1-09` demande. Ces quatre lignes restent, apres cet amendement, exactement dans
l'etat que la section 17.2 de `nadia-prof` decrit.

### 13.2 Ce que cela coute sur la borne, et ce qui en sort

**La borne de chaque sprint reste 12,0, constante (section 2.1) : cet amendement ne l'etire pas.**
s32 est deja saturee a 12,0 exactement (section 6, trois totaux de 4,0). Les compositions
indicatives de s33 et s34 (section 8) ne portent pas de chiffrage poste par poste - seul leur
plafond de lignes (6 chacune) est donne - et cette decision ne comble pas cette absence en
inventant un chiffre qui n'est ni de `nadia-prof` ni de `theo-archi`.

**Ce que je peux dire sans recalculer** : le lot de designation ne peut pas se jouer avant que
`L-c` soit livre (un developpeur, un sprint entier, `PartieController` etant indivisible - avis de
`theo-archi`, sections 4.6 et 7), donc son audit par `nadia-prof` (2,0) ne peut pas tomber avant s34
au plus tot. **A s34, ce n'est pas un poste gratuit** : au tarif de `nadia-prof` (0,5 a 0,75 pour un
lot neuf de sciences ou d'EPS, section 17.5), 2,0 vaut l'equivalent de trois lots neufs ordinaires.
Ce lot **deplace donc reellement du perimetre**, et cette decision ne le nie pas : soit un poste
indicatif de s34 (section 8) cede 2,0 de la borne de l'instance qui herite du lot de designation,
soit la retenue de couture tombe a la cloture de s32 (section 2.1) et porte la borne de s33 a 15,0 -
un surplus de 3,0 qui absorberait alors ce cout sans rien deplacer. **Cette decision ne choisit
aucune des deux voies** : lequel des postes indicatifs de s34 cede sa place, ou si le surplus de
couture l'absorbe, est arrete par `sofia-sm` au planning du sprint qui recoit reellement `L-e`, une
fois `L-c` sequence.

**Ce que cet amendement ne fait pas ici, dit explicitement** : il n'ajoute pas 2,0 en silence a une
composition de s33 ou s34 qui etait deja donnee comme pleine ; il nomme le cout, nomme la condition
qui l'absorbe sans rien couper, et route le choix a `sofia-sm`.

### 13.3 Les deux trous de contrat se paient d'abord - aucun lot de designation n'est valide avant

`theo-archi` a trouve, en instruisant le chemin de jeu, deux trous qu'`ADR-0080` n'avait pas vus
(son avis, section 4.5) :

- **Trou A - bloquant.** `DesignationVerdict` ne porte pas d'`enonce`, la ou `Verdict` et
  `SaisieVerdict` en portent un obligatoire (`ADR-0084`, `REV-3`). Sur une designation c'est pire
  qu'ailleurs : le dessin n'est plus a l'ecran a la fin de la partie, et l'enfant lirait une reponse
  sans la question et sans le terrain. `nadia-prof` ne valide aucun lot de designation avant que ce
  trou soit ferme (section 17.6 de son arbitrage).
- **Trou B - bloquant, et la reponse pedagogique existe deja.** `bonneZone` est un `Zone.id()`, et
  l'ecran de fin de partie ne peut en faire ni un identifiant lu par un enfant, ni un libelle - les
  libelles de zone etant positionnels (`ZON-10`), un libelle dirait une position qui n'enseigne
  rien. **`nadia-prof` a deja tranche** (section 17.6) : l'ecran de fin de partie porte l'enonce et
  ne montre rien a la place de la zone attendue ; la revelation reste sur l'ecran de manche, ou le
  dessin est encore la, via `DesignationSession.bonneZoneRevelee()` (`SCO-5` intacte). Ce que cette
  reponse coute est nul en unites d'audit ; `theo-archi` confirme qu'elle rend la forme la moins
  chere des deux qu'il avait identifiees suffisante (section 17.6).

**L'`ADR` qui acte ces deux changements de contrat dans `domain` est ecrit par `theo-archi`, pas par
`camille-po`.** Cette decision le route, par `sofia-sm`, comme prealable a toute story de
developpement du lot de designation : `L-b` (trou A) et la part de `L-c` qui omet le bloc de
reponse pour une designation (trou B) ne se commencent pas avant que cet `ADR` existe. Les stories
correctives correspondantes (section suivante du backlog) nomment cet `ADR` comme un prerequis a
ecrire, jamais comme un acquis.

### 13.4 Ce que cet amendement ne fait pas

- Il ne ferme aucune ligne de `squad/programme/MATRICE-couverture.md` et ne change le statut
  d'aucune : `QLM-CP-11`, `QLM-CP-12` et `QLM-CP-14` restent `non couvert`, `MAT-CP-10` aussi (sa
  cellule porte desormais un motif, pas un nouveau statut).
- Il ne prononce aucun Done pedagogique.
- Il n'ecrit pas l'`ADR` des deux trous de contrat : voir section 13.3, c'est `theo-archi`.
- Il ne fusionne pas la branche `s32-theo` : elle reste distincte de `round-two` a cette date.
- Il ne recalcule aucun chiffre de `nadia-prof` ni de `theo-archi` : le prix (2,0), le compte de
  lignes (3 fermees, 3 ouvertes, 5 non touchees sur onze) et le sequencement des lots de
  developpement (`L-a` a `L-e`) sont repris tels quels.
- Il n'affecte aucun agent ni aucune instance a ce lot : l'affectation, comme pour `POS-R3` et
  `FOP-R4`/`DRO-R4` (section 6), revient a `sofia-sm`.
- Il ne choisit pas quel poste indicatif de s33 ou s34 cede sa place au cout de 2,0 : voir section
  13.2.
