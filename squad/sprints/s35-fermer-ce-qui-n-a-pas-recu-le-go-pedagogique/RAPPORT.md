# Rapport de sprint s35 - fermer ce qui n'a pas recu le go pedagogique

- **Duree** : 2026-09-11 14:20 -> 2026-09-11 20:32 (UTC+02:00), 6 h 12 min.
- **Objectif gele** (`planning.md`) : aucune ligne neuve de `squad/programme/MATRICE-couverture.md`
  - les six stories retenues ne font que fermer des refus pedagogiques bloquants ou des reserves
  et routages non soldes a la cloture de s34.

## L'increment livre, en une phrase

Cinq refus ou reserves bloquantes de s34 sont fermes (`SCO-R1`, `EXC-R10`, `FRI-R1`, `FRI-R2`, et
le blocage arithmetique de `CONS-4`) et le premier lot livre sur `QLM-CP-11` (le plan de la classe)
est refuse sur un defaut de geometrie chiffre a six zones sur six ; une seule ligne de la matrice
bouge (`QLM-CP-14`), exactement comme l'objectif gele du planning le prevoyait.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0088` | Derogation `nombreDeManches: 7` pour l'entree "Lire un document" (scission de `bandit-manchot-histoire-geographie-cm1`, `S35-CONS-4`) | **Acceptee** |
| `ADR-0091` | Le texte alternatif d'un item de designation ne nomme aucune de ses zones, et son indice ne porte aucun jeton distinctif du libelle de sa bonne zone | `Propose` -> **Accepte** (dans ce meme sprint) |
| `ADR-0092` | Le libelle d'une zone n'est pas gardable par une arithmetique, la police servie etant celle de l'appareil | `Propose` -> **Accepte** (dans ce meme sprint, avec deux amendements de son propre auteur avant l'acceptation) |
| `ADR-0080` | Une designation sur image est un choix parmi des zones enumerees, le client n'envoie jamais une coordonnee | `Propose` (depuis s28) -> **Accepte** (son quatrieme et dernier critere rempli a s35) |

Les quatre entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/produit/DEC-index.md` et `squad/decisions/adr/ADR-index.md`. Aucune ceremonie de
ce sprint (planning, review, retro) n'a elle-meme cree de `DEC` ni d'`ADR` : les quatre ci-dessus
ont ete rediges en cours de sprint, sur des obstacles rencontres, puis enregistrees ici.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre fusionne final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juges purs executes, 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1481 tests, 0 echec, 0 erreur, 0 ignore**, mesure independamment par deux methodes convergentes
(somme des blocs `Results:` du journal, somme des attributs `tests="..."` des 201 rapports XML).
Ce chiffre est celui de l'arbre exact fusionne a la cloture ; il differe de 2 des 1479 annonces en
cours de sprint par `theo-archi` et `yanis-qa`, chacun sur son propre arbre anterieur au dernier
commit du sprint - l'ecart est nomme et sans consequence (0 echec dans les deux mesures), voir
`review.md`, section 2.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, ligne par ligne, prononce par `nadia-prof`

| Story | Verdict | Ce qui ferme |
|---|---|---|
| `S35-SCO1-ECRAN-FIN` | **VALIDE SOUS RESERVE** | `SCO-R1` levee |
| `S35-EXC-INDICES` | **VALIDE SOUS RESERVE** | `EXC-R10` levee, `EXC-R11` soldee |
| `S35-QLM14-FRISE` | **VALIDE SOUS RESERVE** | `FRI-R1`, `FRI-R2` levees ; `QLM-CP-14` ferme |
| `S35-QLM11-PLANCLASSE` | **REFUSE** | rien ; `ZON-15` rompue sur six zones sur six |
| `S35-ANG-R3` | **VALIDE** | `ANG-R3` (deux clauses), `ANG-R7` levees |
| `S35-CONS-4` | **VALIDE** | `CONS-4` levee (scission en quatre paquets) |

**Cinq `VALIDE` ou `VALIDE SOUS RESERVE`, un `REFUSE`.** Le Done pedagogique est prononce dans
`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/verdicts-nadia.md` et les fiches de
conformite qu'il cite.

## Couverture du programme

**75/138 (54,3 %) a l'ouverture -> 76/138 (55,1 %) a la cloture**, verifie contre
`squad/programme/MATRICE-couverture.md` : un seul mouvement, `QLM-CP-14` (`non couvert` ->
`couvert-valide`, mention de couverture partielle levee).

## Le registre de dette - reconciliation de cloture

**Ouverture de s35 : 9,5** (`DEC-0087`, section 3). Reconciliation poste par poste (detail complet
dans `review.md`, section 7) :

- `cm1-anglais` **repasse PAYE** : les deux codes attribues (`ANG-R3`, `ANG-R7`) sont leves,
  l'increment n'est pas refuse -> **-0,5**.
- Sa reserve neuve `ANG-R8` cree une dette neuve sur un paquet remis a zero, au prix que
  `nadia-prof` lui donne elle-meme -> **+0,25**.
- `cp-emc-exclusion` **reste NON PAYE** : `EXC-R12` n'est levee qu'a moitie ("sa lettre est payee,
  son objet l'est a moitie") -> **inchange, 1,0**. Ses deux reserves neuves (`EXC-R13`, `EXC-R14`)
  sont marginales sur un paquet deja compte non paye -> **0,0**.
- `SCO-R4`, `FRI-R6`, `CONS-5` : trois reserves neuves qui n'ajoutent rien au chiffre numerique -
  la premiere par precedent (portee D deja a 0,0), les deux autres parce qu'elles portent sur des
  paquets hors des seize postes de `DEC-0086`/`DEC-0087` (palier 2, famille `CONS` non chiffree).

**Registre de dette a l'ouverture de s36 : 9,25.** Premiere descente depuis l'ouverture de cette
strategie de registre (9,0 -> 9,5 a s34), et elle est faible (-0,25) pour le motif deja identifie
par `DEC-0086` : un paquet solde rouvre presque toujours une dette neuve dans le meme geste.

## Obstacles resolus et encore ouverts

**Resolus** : le blocage arithmetique de `S35-CONS-4` (`DEC-0088`) ; la faisabilite des six
stories et l'ouverture de deux `ADR` (`theo-archi`) ; trois chemins de poste de travail dans
`planning.md` et un complement d'objet manquant sur trois indices d'EMC (`relecteur-pedagogique`).

**Encore ouverts** : le refus de `S35-QLM11-PLANCLASSE`, repris en backlog
(`S36-QLM11-PLANCLASSE`, cree par `camille-po`) ; le garde-fou de taille de police a liste de
selecteurs figee, qui a laisse passer `SCO-R4` (route a `theo-archi`) ; le faux vert de
`PaquetCpEmcExclusionTest` sur un "bouton au rabais" reformule, et le defaut de tracabilite de
`S35-EXC-INDICES` (les deux route a `theo-archi`, remontes par `yanis-qa`) ; les six reserves
neuves du sprint (`SCO-R4`, `EXC-R13`, `EXC-R14`, `FRI-R6`, `ANG-R8`, `CONS-5`), a composer en
stories par `camille-po` - **`FRI-R6` designee prioritaire**, avant qu'un second paquet de
designation ne recopie la meme formule de texte alternatif fautive.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 6.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s35`, fenetre 2026-09-11 12:20 -> 18:32 UTC. Detail
complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans `squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 148 | 296 | 66 248 | 613 049 | 21 880 423 |
| `sofia-sm` | claude-sonnet-5 | 656 | 1 312 | 256 237 | 2 063 280 | 170 995 074 |
| `theo-archi` | claude-opus-5 | 164 | 328 | 63 994 | 388 635 | 18 286 465 |
| `marc-dev` | claude-sonnet-5 | 226 | 452 | 99 994 | 688 316 | 50 445 353 |
| `lea-dev` | claude-sonnet-5 | 378 | 756 | 125 742 | 1 011 333 | 108 806 046 |
| `yanis-qa` | claude-sonnet-5 | 114 | 228 | 30 341 | 281 924 | 10 645 477 |
| `relecteur-pedagogique` | claude-sonnet-5 | 158 | 316 | 45 871 | 709 202 | 17 269 467 |
| `nadia-prof` | claude-opus-5 | 284 | 568 | 159 848 | 676 848 | 58 134 583 |
| `ines-design` | claude-sonnet-5 | 149 | 298 | 79 650 | 1 170 704 | 25 116 533 |
| orchestrateur (fil principal) | claude-sonnet-5 | 57 | 114 | 148 149 | 1 088 405 | 9 407 059 |
| **Total** | | **2334** | **4 668** | **1 076 074** | **8 691 696** | **490 986 480** |

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur.
