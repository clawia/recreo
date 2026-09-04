# RAPPORT de cloture - s28, second passage et chantier palier 2

## L'increment livre

**recreo ferme huit lignes de programme sur un plafond de huit - deux paquets de questionner le
monde CP, deux lignes d'histoire-geographie CM1, un premier lot d'anglais CM1 et un lot de calcul
pose CM1 - et pose la mecanique du palier 2 (designation sur image) comme chantier testable, sans
paquet ni ecran servi.** Sept verdicts au premier passage, un refus corrige et reaudite dans le
sprint sur arbitrage explicite de l'utilisatrice (`DEC-0076`) : la couverture programme passe de
35/138 (25,4 %) a 43/138 (31,2 %). Une ligne s'ouvre sans fermer (`LVE-CM1-07`).

## Toutes les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0074` | Quatre arbitrages de l'utilisatrice pour s28 (perimetre, palier 2, referentiels, execution) | Produit | Acceptee, amendee le meme jour (prix reel 5,0) |
| `DEC-0075` | Corpus de reproductions d'oeuvres et droits d'usage, question ouverte | Produit | Proposee |
| `DEC-0076` | Arbitrage de cloture : corriger et reauditer le paquet refuse dans le sprint plutot que trois items faux en production | Produit | Acceptee et executee |
| `ADR-0080` | Une designation sur image est un choix parmi des zones enumerees, le client n'envoie jamais une coordonnee | Architecture | Propose, deux amendements (exemption d'`ADR-0006`, cliquet des garde-fous de designation) |
| `ADR-0081` | Une ligne de referentiel a un code alloue, un item servi ne sert qu'une ligne | Architecture | Accepte |

## Etat des deux Done

**Technique : prononce.** `mvn -B clean verify` vert sur le reacteur complet, `round-two` a
`b37405d`. **1222 tests, 0 echec, 0 erreur** (somme de l'attribut `tests="..."` de chaque rapport
XML apres `clean`, sans `-pl`, methode de l'amendement s11, mesure par moi sur l'arbre fusionne
final). Confirme independamment par `yanis-qa` (1208 sur `21f3d1d`, avant les portes) et par
`lea-dev` (1222 sur `0c383b9`, chiffre identique au mien). **Un ecart mineur et signale, non
tu** : je compte 167 rapports XML, `lea-dev` en citait 166 sur le meme perimetre de test - le
total de tests, seul chiffre que la methode engage, est identique aux deux mesures.

**Pedagogique : prononce.** Sept verdicts de `nadia-prof` au premier passage (branche `round-two`,
commit `537a0e1`), un refus (`cp-qlm-vivant-non-vivant`, trois items de matiere inerte faux),
corrige par `lea-dev` (`0c383b9`) et reaudite `VALIDE SOUS RESERVE` le meme jour (`e06de4b`). Huit
lignes ferment : `QLM-CP-01`, `QLM-CP-03`, `QLM-CP-05`, `HG-CM1-11`, `HG-CM1-13`, `LVE-CM1-04`,
`MAT-CM1-04`, `MAT-CM1-05`. Une ligne s'ouvre sans fermer (`LVE-CM1-07`). Neuf lignes
d'histoire-geographie restent `en cours`, non touchees par le second lot.

**Prix reel du sprint : 5,25 sur une borne de 5**, par l'arbitrage de cloture de l'utilisatrice
(`DEC-0076`) : elle a demande de corriger et reauditer le paquet refuse dans le sprint plutot que
de laisser trois items faux servis en production jusqu'a s29. `nadia-prof` avait decline cette
composition par ecrit avant le sprint ; elle l'a executee sans la rediscuter.

**Conditions restant sans garde executable sur les huit lignes fermees** : `HIS-2` et l'exactitude
factuelle (`HG-CM1-11`, `HG-CM1-13`), `REC-5`/`REC-6` au sens fin (`LVE-CM1-04`), `POS-3`
(`MAT-CM1-04`, `MAT-CM1-05`), et la verite d'un enonce de matiere inerte (`QLM-CP-01`). Aucun
statut de matrice ne doit suggerer que ces proprietes sont gardees par un test.

## Un piege de lecture, a desamorcer explicitement

**Le taux de couverture a baisse de 46,7 % a 25,4 % puis remonte a 31,2 % dans le meme sprint. Ce
sont deux mouvements sans rapport entre eux, et ils ne doivent jamais etre lus ensemble.**

- **46,7 % -> 25,4 %** (avant l'ouverture du sprint, commit `ef4a636`) : une **correction de
  denominateur**. Dix referentiels d'enseignements jusque-la absents de la matrice (arts
  plastiques, education musicale, EPS, enseignement moral et civique aux deux cycles, histoire des
  arts et sciences et technologie au cycle 3) y entrent pour la premiere fois, portant le
  denominateur atteignable de 75 a 138. Le numerateur (35 lignes `couvert-valide`) ne bouge pas.
  Aucun jeu ne se casse, aucune ligne verte ne retombe : le produit n'a pas regresse, la mesure
  est devenue vraie sur un perimetre plus large.
- **25,4 % -> 31,2 %** (a la cloture, commit `b37405d`) : un **gain reel de couverture**, sur un
  denominateur qui, lui, reste stable a 138. Huit lignes passent de `en cours` ou `non couvert` a
  `couvert-valide`, sur du contenu effectivement livre et audite ce sprint.

Le premier mouvement est un effet de mesure ; le second est un effet de production. Additionner ou
comparer leurs pourcentages (par exemple lire "s28 a perdu 15 points puis n'en a regagne que 6")
produirait une lecture fausse du sprint.

## Obstacles resolus

- `HIS-4` (forme du marqueur de ligne de referentiel) tranchee par `theo-archi` avant `S28-2`
  (`ADR-0081`), puis reprise sur les 42 paquets deja servis par `marc-dev` (`1619d06`, tache non
  planifiee arbitree en cours de sprint) - cliquet de dette descendu de 42 a 0 dans le meme diff.
- Conflit entre `ADR-0080` et `ADR-0006` (contrat de contenu integralement textuel) - trouve et
  repare par `theo-archi` lui-meme dans le commit qui l'implementait (`13963eb`), exemption
  nommee, datee et plafonnee.
- Quatre trouvailles de `yanis-qa`, trouvees apres coup sur une CI verte : `PaquetCpQlmCorpsSensTest`
  non etendu sur un paquet passe de 8 a 14 items, `ANG-5` affirmee sans etre gardee ni disclosed,
  un test nomme pour `BIA-2` qui gardait une autre propriete, et la dette des garde-fous de
  designation sans cliquet executable. Les trois premieres corrigees par `lea-dev` (`ce3059b`),
  la quatrieme payee immediatement par `sofia-sm` (`aac5d88`).
- Prescription de `nadia-prof` sur le calcul pose, incompatible avec un garde-fou existant du
  depot - trouvee par `marc-dev` en executant `S28-4`, l'exigence retiree par `nadia-prof`
  elle-meme dans son verdict (`537a0e1`).
- Refus `cp-qlm-vivant-non-vivant` (trois items de matiere inerte factuellement faux) - corrige
  mot pour mot par `lea-dev` (`0c383b9`), reaudite `VALIDE SOUS RESERVE` par `nadia-prof`
  (`e06de4b`), `QLM-CP-01` ferme.
- Depassement de la borne d'audit (5,25 sur 5,0) - arbitre par l'utilisatrice, acte en `DEC-0076`,
  execute sans etre rediscute.
- Classement de la ligne des echelles derriere le palier 2 - `nadia-prof` a corrige son propre
  classement (assimilation, pas examen) et sorti la ligne du palier 2, tracee pour s29.

## Obstacles encore ouverts, a porter a s29

- **La question de fond sur la ligne des echelles** : le produit ne connait pas le lieu de vie de
  l'enfant (`DEC-0028`, aucune persistance) - `nadia-prof` a pose la question a l'utilisatrice via
  `camille-po`, non tranchee dans s28.
- **`DEC-0075`** (corpus de reproductions d'oeuvres, droits d'usage non instruits) reste `Proposee`,
  neuf lignes atteignables bloquees.
- **La dette des paquets a voix** (six jeux, cinq indices) reste due depuis s26 : sa correction
  coute une capacite d'audit de `nadia-prof` (0,25) qu'aucun sprint depuis n'a pu lui allouer sans
  depasser sa borne.
- **Sept reserves non bloquantes** issues des verdicts de s28 : `HG-R4`, `HG-R5`, `ANG-R1`,
  `POS-R1` (priorite signalee : neuf items sur onze du paquet de division portent un distracteur
  au meme quotient que la bonne reponse), `COR-R2`, `EAU-R1`, `VIV-R2`.
- **Le cout d'un refus en fin de sprint sans marge de rejeu** : la question de la reponse par
  defaut (report au sprint suivant, avec un item faux laisse en ligne le temps du report) n'a
  jamais ete posee comme un choix assume avant que ce sprint ne la rencontre en vrai - a poser a
  `camille-po` et `nadia-prof` avant s29 (voir `retro.md`).

## Tableau de consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s28`
(`squad/sprints/s28-second-passage-et-chantier-palier-2/RAPPORT-tokens.md`) :

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 157 | 314 | 66 377 | 517 757 | 26 119 784 |
| `sofia-sm` | claude-sonnet-5 | 500 | 1 000 | 280 939 | 1 825 449 | 104 400 083 |
| `theo-archi` | claude-opus-5 | 207 | 414 | 137 754 | 739 246 | 24 104 662 |
| `marc-dev` | claude-sonnet-5 | 497 | 994 | 297 543 | 1 728 917 | 101 571 657 |
| `lea-dev` | claude-sonnet-5 | 592 | 1 184 | 245 020 | 2 364 201 | 91 585 314 |
| `yanis-qa` | claude-sonnet-5 | 121 | 242 | 38 044 | 386 810 | 13 391 841 |
| `relecteur-pedagogique` | claude-sonnet-5 | 156 | 312 | 47 533 | 734 689 | 27 498 840 |
| `nadia-prof` | claude-opus-5 | 233 | 466 | 141 135 | 637 602 | 26 134 011 |
| orchestrateur (fil principal) | claude-opus-5 | 158 | 316 | 217 346 | 2 284 345 | 40 939 763 |
| **Total** | | **2621** | **5 242** | **1 471 691** | **11 219 016** | **455 745 955** |

**Aucune invocation mesuree sur cette fenetre pour `ines-design`** : elle n'a pas ete sollicitee ce
sprint, `T4` du palier 2 (route web, gabarit) etant suspendu a un arbitrage qui n'existe pas
encore.

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur.
