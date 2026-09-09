# DEC-0084 - Cloture de s32 : trois lignes ferment sur huit visees, sous l'attente raisonnable de
# `nadia-prof` ; le declencheur de couture de `DEC-0083` n'est pas rempli, la borne de s33 reste a
# 12,0

- **Type** : constat de cloture et decision de routage de `camille-po`, sur la foi des verdicts des
  trois instances de `nadia-prof` ; aucun arbitrage neuf de l'utilisatrice n'est demande ni rendu
  ici.
- **Date** : 2026-09-09.
- **Auteur** : `camille-po`.
- **Sprint** : s32 (cloture).
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision constate
  le resultat d'un arbitrage de perimetre deja rendu (`DEC-0083`) et route ses consequences ; les
  competences ligne a ligne sont deja nommees dans `docs/backlog/backlog.md` et dans
  `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee.
- **Lien** : `DEC-0083` (perimetre et bornes de s32 a s34), `DEC-0082` (cloture de s31),
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-ligne-marginale.md`,
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/planning.md`,
  `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/verdicts-nadia-instance-A.md`, `-B.md`,
  `-C.md`, `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/rapport-qualite.md`,
  `confirmation-archunit.md`, `relecture.md`, les dix fiches de conformite citees par les trois
  verdicts, `squad/programme/MATRICE-couverture.md` (section "Synthese apres les verdicts du
  sprint 32"), `docs/backlog/backlog.md` (sections "Sprint 32", "Backlog ouvert pour s33" et
  "Backlog ouvert pour s33 (suite)"), `squad/decisions/REGISTRE-identifiants.md`.

---

## 1. Le fait a enregistrer : le resultat contre le plan

`DEC-0083` chiffrait, pour s32, un **plafond arithmetique de 8 lignes** (71/138 = 51,4 %) et une
**attente raisonnable de `nadia-prof` de 6 a 7 lignes** (69 a 70/138, soit 50,0 % a 50,7 %).

**Le resultat reel : 3 lignes ferment.** `HG-CM1-01` (frise des grandes periodes, sur sa part
servie), `HG-CM1-03` (traces d'occupation ancienne) et `LVE-CM1-07` (decouverte culturelle CM1,
VALIDE SOUS RESERVE) passent `en cours` -> `couvert-valide`. Recompte independant sur les
dix-huit tableaux de `squad/programme/MATRICE-couverture.md`, chaque ligne de donnees reclassee
telle qu'elle est ecrite apres mise a jour, sans reprendre aucun total d'un autre document :
**66/138 = 47,8 %**, contre 63/138 = 45,7 % a l'ouverture.

**3 lignes sur 8 visees est sous les deux bornes basses des deux fourchettes annoncees d'avance.**
Ce n'est l'echec d'aucune instance prise isolement au regard de ce qu'elle avait elle-meme chiffre
: l'instance C avait annonce une attente de 0 ou 1 ligne sur les deux visees (`PAR-13`, terrain
jamais mesure) et en a apporte 0, exactement dans sa fourchette. C'est l'instance A, sur un
terrain deja mesure quatre fois consecutives (s27 a s31), qui manque son propre plafond : elle
ferme 2 lignes d'histoire-geographie sur 4 visees et 1 ligne d'anglais sur 1 visee, soit 3 sur 5,
la ou le plafond du sprint en comptait 5 pour son seul terrain. Le reste du deficit vient de
l'instance B, qui visait +1 ligne au plus (`EMC-CP-07`) et en apporte 0, et de la confirmation du
paiement refuse de `CAT-1`, qui n'ouvrait ni ne visait de ligne mais consommait une part du budget
de l'instance B.

**Quatre refus, aucun ne deplace de ligne** : `HG-CM1-02` (`HG-R11`, bloquante - un fait faux sur
le sacre de Clovis, qui n'a jamais eu lieu), `HG-CM1-04` (`HG-R12`, bloquante - "les Sarrasins" en
bonne reponse a une question qui demande un peuple, terme qu'aucun groupe n'a jamais revendique
pour lui-meme), `EMC-CP-07` (refusee au troisieme passage, sur un motif entierement neuf que
`nadia-prof` met a son propre debit : une contradiction entre deux de ses textes du meme jour),
`EPS-CP-04` et `EPS-CP-08` (refusees toutes deux au premier contact, sur des motifs anterieurs au
lot - `FAB-1c` et `EPS-1`, plus l'exactitude du contenu). **Le paiement de `CAT-1` est egalement
refuse**, sur les deux paquets ou il avait ete trouve (`EMC-CP-02`, `EMC-CP-06`), sans rouvrir ni
l'une ni l'autre.

**Aucun de ces sept resultats n'est rejuge ici.** Les trois fermetures, les quatre refus et le
paiement refuse de `CAT-1` sont ceux que `nadia-prof` prononce dans ses trois documents de verdict,
et cette decision ne fait que les enregistrer et en tirer les consequences de gouvernance.

## 2. Le cout depense par instance, contre le budget de 4,0 et contre la borne de 12,0

| Instance | Budget opposable | Depense | Ecart |
|---|---|---|---|
| A | 4,0 | **3,75** | -0,25 tiree de la marge `PAR-11` ; il en reste 0,25 non consommee |
| B | 4,0 | **3,5** | -0,5, marge `PAR-11` non prise (aucun lot neuf sur son terrain) |
| C | 4,0 | **2,5** | -1,5, marge non tiree - un refus s'arrete au premier defaut suffisant, une validation balaie toute la liste des conditions |
| **Total** | **12,0** | **9,75** | **2,25 de la borne du sprint n'ont pas ete depenses** |

**Ce sous-emploi de la borne n'est pas une economie a saluer.** Il vient, pour C, du fait qu'un
refus coute moins cher a instruire qu'une validation - `nadia-prof` le dit elle-meme - et, pour B,
de l'absence de tout lot neuf sur son terrain ce sprint. Aucune des trois instances n'a manque de
budget pour finir son travail : le deficit de lignes fermees n'est donc pas un probleme de
capacite d'audit, c'est un probleme de qualite du contenu livre au premier passage.

## 3. Le declencheur de la borne de s33 - tranche sur un fait, pas sur une impression

`DEC-0083`, section 2.1 : **la retenue de 3,0 tombe en bloc, portant la borne de s33 de 12,0 a
15,0, si et seulement si s32 clot sans aucune occurrence des trois formes de defaut de couture**
que `nadia-prof` a definies (a - une story qui modifie un paquet et une story qui modifie sa
consigne dans le meme sprint ; b - un chiffre qu'aucune des deux moities ne peut verifier et
qu'un tiers trouve faux en recomptant ; c - deux instances qui ecrivent dans une meme ressource
partagee sans le savoir). Elle tombe en bloc et non paire par paire.

**Une occurrence de la forme (c) a eu lieu.** A la fusion des trois branches `s32-nadia-a`,
`s32-nadia-b` et `s32-nadia-c`, les trois instances ont chacune alloue des identifiants neufs dans
`squad/decisions/REGISTRE-identifiants.md` en partant du meme compte de base (547/94), sans se
voir entre elles. La fusion a produit un **conflit reel** sur ce fichier, resolu par `sofia-sm`
(compte recompte sur le fichier assemble : 578 identifiants, 97 prefixes, 0 doublon -
`squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/planning.md`, section 17).

**Ce que ce fait change, et ce qu'il ne change pas.** Les trois instances avaient annonce ce risque
d'avance, chacune dans son propre document, avant meme la fusion : c'est la premiere fois que la
forme (c) - deja vue deux fois auparavant sur ce depot - est ecrite en avance plutot que decouverte
au rouge, et le conflit a ete reel et visible sur le fichier, jamais silencieux. **Cela n'empeche
pas que la condition de `DEC-0083` porte sur une occurrence, pas sur sa gestion** : le texte dit
"sans aucune occurrence des trois formes", pas "sans occurrence non geree". Une occurrence a eu
lieu.

**Le declencheur n'est donc pas rempli. La retenue de 3,0 ne tombe pas : la borne de s33 reste a
12,0, et non 15,0.** Ce n'est pas un jugement sur la qualite du geste de `sofia-sm`, qui a ete
exemplaire - annonce d'avance, conflit visible, recompte independant sur le fichier assemble - ;
c'est l'application litterale de la condition que `DEC-0083` a posee, qui ne distingue pas une
couture bien geree d'une couture qui ne l'aurait pas ete.

## 4. Le registre de dette a la cloture

Reprise du registre de `DEC-0083`, section 7, avec l'etat que s32 lui donne.

| Code | Poste | Prix | Etat a la cloture de s32 |
|---|---|---|---|
| `D1` | `CAT-1` + `REG-R1` + `PER-R1` | 1,0 | **`REG-R1` et `PER-R1` levees** (payees a s32, quatrieme sprint de report, soldees). **`CAT-1` non payee** : paiement refuse une seconde fois, reste ouverte. Story de reprise : `S33-CAT1`, regime rejeu borne, 0,5 par paquet, deux paquets (`PAR-10`) |
| `D2` | `EMC-CP-07`, `FAB-1c` seul | 0,5 | **Non paye.** Refusee au troisieme passage, sur un motif neuf. Story de reprise : `S33-EMCCP07`, prix non chiffre par `nadia-prof` pour ce quatrieme passage |
| `D3` | Residu du groupe 2a (`POS-R3`, `FOP-R4`/`DRO-R4`) | 3,0 | Inchange, sur trajectoire : lettres affectees a s32 (`POS-R3` a l'instance B, `FOP-R4`/`DRO-R4` a l'instance A), ni ouvert ni chiffre ce sprint, comme prevu |
| `D4` | Groupe 2b (reliquat `CONS-1` hors lignes ouvertes a s32, `CONS-2`, `SAI-R1`, `ERR-R1`) | 1,0 | Inchange, sur trajectoire, routee a s33 comme prevu |
| `D5` | Reserves neuves de s31 (`EMO-R1/R2`, `POL-R1/R2`, `EXC-R1` a `R5`) | 1,5 | **7 payees sur 9** (`EMO-R1`, `EMO-R2`, `POL-R1`, `POL-R2`, `EXC-R2`, `EXC-R4` entierement, `EXC-R3` sur 1 item des 3). **`EXC-R1` et `EXC-R5` non payees** : l'obstacle technique est leve pour les deux, le texte reste du. Stories de reprise : `S33-EXC-R1`, `S33-EXC-R5` |
| `D6` | `CONS-3` | non chiffree | Inchange, ne se paie pas dans ces trois sprints |
| `D7` | Verification de `SCO-1` apres correction | 0,5 | **Preuve neuve avant l'echeance** : l'instance A a rejoue `SCO-1` en jouant les paquets de son propre terrain (8 parties completes, 8 scores de 10 sur 10, pour 17 a 24 appuis), sans cout supplementaire sur son budget. Le remede technique reste du a `theo-archi`, inchange. `SCO-1` est a son neuvieme sprint sans etre payee. Story : `S33-SCO-1` (pointeur vers `S25-SCO-1`, dont les criteres ne sont pas repris ni rechiffres) |

**Dette neuve ouverte par s32, non prevue dans le registre d'ouverture** :

| Poste | Prix | Story |
|---|---|---|
| `HG-R11` (fait faux, sacre de Clovis) | 0,25, mot pour mot | `S33-HG-02` |
| `HG-R9` (indice qui designe la reponse, non bloquante) | 0,5, rejeu borne | `S33-HG-02` |
| `HG-R12` (terme "les Sarrasins", bloquante) | 0,5, rejeu borne | `S33-HG-04` |
| `HG-R10` (indice faux sur l'unification, non bloquante) | 0,25, mot pour mot | `S33-HG-04` |
| `ANG-R3` a `ANG-R6` (quatre reserves non bloquantes sur `LVE-CM1-07`) | 0,5 + 0,25 + 0,25 + 0,25 | non encore routees en story distincte - a affecter au planning de s33 avec la prochaine reprise du paquet |
| `SEC-R3` (tache B se gagne au registre du danger, motif du refus) | non chiffre | `S33-EPS-SEC` |
| `SEC-R1`, `SEC-R2` (reserves non bloquantes) | non chiffre | `S33-EPS-SEC` |
| `JEU-R1` a `JEU-R6` (quatre erreurs de fait, deux items au registre, motifs du refus) | non chiffre | `S33-EPS-JEU` |
| `JEU-R7`, `JEU-R8` (reserves non bloquantes) | non chiffre | `S33-EPS-JEU` |
| `CONS-4` (le grain du jeu empeche sa consigne d'informer l'enfant) | non chiffre, decision de grain prealable | `S33-CONS-4` |
| `EMO-R3`, `EXC-R6`, `POL-R3`, `POL-R4`, `PER-R2`, `REG-R2`, `REG-R3` (sept reserves non bloquantes neuves sur l'EMC CP) | non chiffre | pas encore de story distincte - trois d'entre elles (`EMO-R3`, `EXC-R6`, `POL-R3`) sont deja portees par la dette declaree d'`ADR-0089` (5 enonces sur 3 paquets) ; les quatre autres (`POL-R4`, `PER-R2`, `REG-R2`, `REG-R3`) restent a nommer au planning de s33 |

**`CONS-1` reste non levee sur `bandit-manchot-histoire-geographie-cm1`**, et le lot de s32
l'aggrave plutot que de la resorber : deux items nommes par la consigne, quatorze crees sans
l'etre. C'est la portee D qui le trouve, et c'est la cause, nommee par `nadia-prof`, que
`S33-CONS-4` porte.

## 5. Ce qui part a s33

Dix stories ouvertes dans `docs/backlog/backlog.md`, section "Backlog ouvert pour s33 (suite)" :
`S33-HG-02`, `S33-HG-04`, `S33-EMCCP07`, `S33-CAT1`, `S33-EPS-SEC`, `S33-EPS-JEU`, `S33-EXC-R1`,
`S33-EXC-R5`, `S33-CONS-4`, `S33-SCO-1`. Aucune n'est affectee a une instance ni chiffree contre la
borne de s33 : c'est le travail du planning de s33, par `sofia-sm`, sur une borne qui reste **12,0**
(section 3 ci-dessus).

**Une incoherence du referentiel, tracee et non corrigee ici** : `LVE-CM1-07` (langues vivantes,
CM1) ne porte pas la parenthese "(fetes, lieux, vie quotidienne)" que `LVE-CP-06` (meme competence,
CP) porte. `nadia-prof` le signale contre elle-meme - elle est la source du referentiel - et je
route la question a elle sans la trancher : la meme competence doit-elle porter la meme parenthese
aux deux niveaux ? Cette question n'est pas chiffree et n'entre dans aucune story.

**Une correction qui n'est pas la mienne et reste due** : le compte de 34 `ArchRule` (et non 35)
dans `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/rapport-qualite.md`, routee a
`yanis-qa` par le `relecteur-pedagogique`.

## 6. Ce que je decide

1. **Aucun arbitrage n'est rouvert.** `DEC-0083` reste Acceptee, telle qu'ecrite et amendee.
2. **La borne de s33 reste 12,0** : le declencheur de couture de `DEC-0083`, section 2.1, n'est pas
   rempli (section 3 ci-dessus). Le perimetre indicatif de s33 chiffre dans `DEC-0083`, section 8,
   n'est donc pas revu a la hausse par ce fait.
3. **Dix stories routees a `sofia-sm`** pour le planning de s33 (section 5), avec priorite nommee
   sur les quatre refus (`S33-HG-02`, `S33-HG-04`, `S33-EMCCP07`, les deux `S33-EPS-*`) et sur le
   paiement refuse de `CAT-1` (`S33-CAT1`), qui portent tous un motif de refus ecrit et non un
   simple report.
4. **La reference orpheline `dcbcd65a` de `DEC-0083`** (sections "Lien" et 13) est corrigee par
   `96c8efbe`, le commit atteignable depuis `round-two` qui porte le meme contenu que la citation
   visait. Corrige dans `DEC-0083` directement, dans le meme geste que cette cloture - erreur de
   `camille-po`, qui avait passe ce SHA depuis un rapport anterieur a une reecriture de commit.

## 7. Ce que cette decision ne fait pas

- Elle ne prononce aucun Done pedagogique : les trois verdicts qui ferment une ligne, les quatre
  refus et le paiement refuse de `CAT-1` sont de `nadia-prof`.
- Elle ne fixe aucun perimetre pour s33 au-dela des dix stories routees et de la borne confirmee a
  12,0.
- Elle ne rejuge aucun des sept resultats de ce sprint.
- Elle ne tranche pas la decision de grain de `S33-CONS-4` : `ADR-0081` la confie a `camille-po` et
  `nadia-prof` ensemble, et elle reste a rendre.
