# Review - s35 - fermer ce qui n'a pas recu le go pedagogique

- **Horodatage de fin** : 2026-09-11 20:32 (UTC+02:00).
- **Horodatage de debut** (`planning.md`) : 2026-09-11 14:20 (UTC+02:00).
- **Duree effective** : 6 h 12 min.
- **Casquette** : `sofia-sm`, Scrum Master.
- **Arbre revu** : `s35-integration` a `5e0f3e87` (toutes les branches `s35-*` verifiees ancetres de
  `s35-integration` par `git merge-base --is-ancestor` avant cette review - aucune fusion
  supplementaire necessaire), plus les ecritures de cette ceremonie sur `s35-cloture` (branche
  ouverte depuis `s35-integration`).

---

## 1. L'increment livre, en une phrase

Cinq refus ou reserves pedagogiques bloquantes de s34 sont fermes (`SCO-R1`, `EXC-R10`, `FRI-R1`,
`FRI-R2`, et la decision de grain de `CONS-4`), un sixieme lot (`QLM-CP-11`, le plan de la classe)
est livre pour la premiere fois et **refuse** sur un defaut de geometrie mesure a l'echelle des six
zones ; une seule ligne de la matrice de couverture bouge, `QLM-CP-14`, exactement comme l'objectif
gele du planning le prevoyait (aucune ligne neuve, uniquement de la fermeture de refus).

## 2. Le Done technique - quatre jobs, verifies un par un, sur l'arbre fusionne final

Conformement a `squad/README.md`, section 9, et a l'amendement du 2026-09-08 a `CLAUDE.md` : un
agent qui annonce la porte technique nomme les jobs qu'il a lances et ne deduit pas les autres.
Les quatre jobs de `.github/workflows/ci.yml` ont ete relances par moi, individuellement, sur le
worktree de cloture `s35-cloture` (base `s35-integration` a `5e0f3e87`, `git status` propre avant
et apres).

| Job | Commande exacte | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` | **BUILD SUCCESS**, 6 modules sur 6 (`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`, `recreo-bootstrap`) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert** : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | balayage `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, un par un | **vert**, **9 juges purs executes**, 0 echec |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` et `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s -1 \| bash .github/scripts/verifier-prefixes.sh` | **vert** : *"Tous les commits portent un prefixe d'agent connu."* |

**Compte de tests, mesure par moi, deux methodes independantes sur le meme lancement de `build`** :

| Module | Tests run |
|---|---|
| recreo-domain | 111 |
| recreo-application | 13 |
| recreo-adapter-web | 397 |
| recreo-adapter-content | 707 |
| recreo-bootstrap (surefire) | 247 |
| recreo-bootstrap (failsafe) | 6 |
| **Total (methode 1, blocs `[INFO] Results:` du journal)** | **1481** |
| **Total (methode 2, somme de `tests="..."` des 201 rapports XML Surefire/Failsafe)** | **1481** |

**Les deux methodes convergent : 1481 tests, 0 echec, 0 erreur, 0 ignore.** Ce chiffre est
**superieur de 2** aux 1479 annonces independamment par `theo-archi` (`confirmation-archunit.md`)
et par `yanis-qa` (`rapport-qualite.md`), tous deux sur `recreo-adapter-content` (705 chez eux,
707 ici). L'ecart n'est pas recopie sans methode : leurs deux mesures precedent le dernier commit
de `s35-integration` (`5e0f3e87`, `[CAM] s35: mouvement de matrice QLM-CP-14, colonne Jeu(x)
HG-CM1 apres scission, registre de dette et backlog`), sur lequel ni l'un ni l'autre ne s'etait
explicitement positionne dans son en-tete ; ce commit est un commit de documentation
(`squad/programme/MATRICE-couverture.md`, `docs/backlog/backlog.md`, registre de dette), aucun
fichier de test n'y figure au diff. **L'ecart de 2 tests n'a donc pas sa cause dans ce dernier
commit** ; il tient a un etat different d'`adapter-content` entre leurs deux mesures et la mienne
sur des commits anterieurs de la meme branche, non recompte ici faute d'enjeu : le chiffre qui
fait foi pour cette cloture est celui mesure sur l'arbre exact fusionne, **1481**, confirme par
deux methodes independantes sur le meme lancement. Aucune regression, aucun echec, dans les deux
cas.

**ArchUnit** : 33 `ArchRule` (compte de `theo-archi`, inchange par ce sprint - aucun garde-fou neuf
de s35 n'est exprimable en `ArchRule`, les quatre etant des chaines de contenu, un document HTML
servi et un compte de fichiers). Non remesure independamment par moi : repris de
`confirmation-archunit.md`, section 1, avec sa methode citee.

## 3. La confirmation ArchUnit et les decisions d'architecture

`theo-archi` (`avis-faisabilite-theo.md` au planning, `confirmation-archunit.md` a la cloture) :

- `ADR-0091` (le texte alternatif d'un item de designation ne nomme aucune de ses zones, l'indice
  ne porte aucun jeton distinctif du libelle) : `Propose` -> **`Accepte`**, morsure refaite sur le
  materiau reel (40 defauts clause 1, 1 defaut clause 2 sur le contenu fautif restaure, 7/7 vert
  sur le contenu corrige).
- `ADR-0092` (le libelle d'une zone n'est pas gardable par une arithmetique, la police servie est
  celle de l'appareil) : `Propose` -> **`Accepte`**, deux corrections de ses propres phrases faites
  par `theo-archi` lui-meme avant l'acceptation (le levier de pave n'etait pas gratuit, 4,8 px et
  non 40 ; le nom de la constante du cliquet affirmait un audit non fait).
- `ADR-0080` (designation sur image) : `Propose` -> **`Accepte`**, son quatrieme et dernier critere
  rempli par la morsure des garde-fous 1 et 3 sur l'ecran de manche reellement servi (un seul test
  sur 1473 rougit sur un defaut de geometrie introduit dans un paquet de production).

Verifie sur le disque : les trois entrees de `squad/decisions/adr/ADR-index.md` portent bien le
statut `Accepte` avec leur methode citee dans la meme ligne.

## 4. Le rapport qualite de `yanis-qa`

`rapport-qualite.md` : Done technique confirme sur `build` ; compte de tests independant (1479 sur
son arbre, methode double convergente) ; trois garde-fous recents mordus sur materiau de
production reel (`ADR-0091` clauses 1 et 2, garde-fou de `S35-SCO1-ECRAN-FIN`) ; un **faux vert
demontre** sur `PaquetCpEmcExclusionTest` (un nouveau "bouton au rabais" absent de la liste
litterale que la clause verifie traverse le garde-fou sans le faire rougir) ; un **defaut de
tracabilite** trouve sur `S35-EXC-INDICES` (aucun fichier Java ne cite `S35-EXC-INDICES`,
`EXC-R10`, `EXC-R11` ou `EXC-R12` ; deux des quatre criteres d'acceptation de la story sont
structurellement testables et ne le sont pas) ; verification independante du compte de catalogue
(70 -> 73 sur le commit de `marc-dev`, 74 au total du sprint avec l'ajout du lot de `lea-dev`, deux
chiffres distincts nommes comme tels).

## 5. Les corrections du `relecteur-pedagogique`

`relecture.md` : un defaut CI reel trouve et corrige (trois chemins de poste de travail dans
`planning.md`, section 1, reverifie vert par `verifier-chemins.sh`) ; quatre corrections de
redaction (une faute d'orthographe et une espace manquante dans `DEC-0088`, un caractere accentue
isole dans `ADR-0091`, un extrait de regle CSS inexact dans l'avis de faisabilite) ; une correction
de langue sur un texte candidat a l'enfant (`cp-emc-exclusion.yaml`, trois indices de la tache B
sans complement d'objet apres "a deja vu", corriges par l'ajout de "cela", reverifie vert sur
`PaquetCpEmcExclusionTest` et le garde-fou de rang de bonne reponse) ; aucun point laisse a
trancher.

## 6. Tableau des obstacles du sprint

| Obstacle | Categorie | Route a | Statut a la cloture |
|---|---|---|---|
| Blocage arithmetique de `S35-CONS-4` (`DEC-0064` contre `ADR-0023`, entree "Lire un document") | Produit | `camille-po` | **Resolu** - `DEC-0088` (derogation `nombreDeManches: 7`), Acceptee |
| Faisabilite des six stories, forme de deux `ADR` a ouvrir | Technique | `theo-archi` | **Resolu** - avis rendu au planning, `ADR-0091` et `ADR-0092` ouverts en `Propose`, confirmes `Accepte` a la cloture |
| Trois chemins de poste de travail dans `planning.md` | Redaction / CI reelle | `relecteur-pedagogique` | **Resolu** - corriges, `verifier-chemins.sh` revert vert |
| Complement d'objet manquant sur trois indices de `cp-emc-exclusion.yaml` (tache B) | Redaction / pedagogie | `relecteur-pedagogique` | **Resolu** - "cela" ajoute, tests revert verts |
| Refus de `S35-QLM11-PLANCLASSE` (`ZON-15` rompue sur six zones sur six, 17 a 95 px de debordement) | Pedagogie | `nadia-prof` | **Non bloquant pour la cloture, renvoye en backlog** - `S36-QLM11-PLANCLASSE` cree par `camille-po` dans `docs/backlog/backlog.md` |
| Garde-fou de taille de police a liste de selecteurs figee, laisse passer `SCO-R4` (les deux libelles neufs de l'ecran de fin ne declarent aucune taille) | Technique | `theo-archi` | **Ouvert**, route par `nadia-prof` (`verdicts-nadia.md`, section 6) a la cloture de ce sprint, non traite dans s35 |
| Faux vert sur `PaquetCpEmcExclusionTest` (`"a personne"` couvre un incident, pas une famille) | Qualite | `theo-archi` (via `yanis-qa`) | **Ouvert**, symptome remonte, pas de reformulation de la clause dans ce sprint |
| Defaut de tracabilite sur `S35-EXC-INDICES` (`EXC-R11`, `EXC-R12` sans garde-fou de regression) | Qualite | `theo-archi` | **Ouvert**, symptome remonte par `yanis-qa` |
| Six reserves neuves : `SCO-R4`, `EXC-R13`, `EXC-R14`, `FRI-R6`, `ANG-R8`, `CONS-5` (plus `FRI-R7`, `PCL-R1`, `PCL-R2` non chiffrees dans le registre numerique) | Pedagogie | `camille-po` | **Ouverts**, en stories a composer ; `FRI-R6` designee **prioritaire** par `camille-po`, reprenant le mot de `nadia-prof` (*"a payer avant que la formule ne soit recopiee sur un paquet de designation de plus"*) |
| Reconciliation du registre de dette a la cloture (`cp-emc-exclusion` et `cm1-anglais` repassent-ils PAYE ?) | Capacite / gouvernance | `sofia-sm` (moi-meme, explicitement laissee par `camille-po`) | **Resolu** dans cette review, section 7 |
| Ecart de test entre les mesures de `theo-archi`/`yanis-qa` (1479) et la mesure de cloture (1481) | Qualite / mesure | `sofia-sm` (moi-meme) | **Constate et nomme** (section 2), sans enjeu de regression - 0 echec dans les deux cas |

## 7. Le registre de dette a la cloture - reconciliation poste par poste, pas mecanique

`camille-po` a explicitement laisse ce recalcul a la cloture
(`REGISTRE-reserves-neuves-s35.md`, en-tete et section 1) : la methode de `DEC-0086`/`DEC-0087`
facture **par paquet** (0,5 le plus souvent, ici 1,0 pour `cp-emc-exclusion` sous tarif de lot
`PAR-15`), pendant que `nadia-prof` chiffre chaque reserve neuve **isolement** ; les deux echelles
ne s'additionnent pas terme a terme. Methode reprise a l'identique de `DEC-0087`, section 3 : un
paquet est **PAYE** quand la **totalite** des codes que la table lui attribue a l'ouverture sont
**leves**, et que l'increment qui les leve **n'est pas refuse**.

**Je ne recopie pas le calcul mecanique "9,5 + 6 x 0,25 = 11,0"** : il additionnerait des prix
d'echelles differentes sans verifier, poste par poste, ce que chaque reserve fait reellement au
registre - exactement l'erreur que `DEC-0086` (section 4) et `DEC-0087` (section 3) ont chacune
nommee et corrigee au sprint precedent.

### 7.1 `cp-emc-exclusion` (1,0 a l'ouverture, increment refuse a s34) - **reste NON PAYE, 1,0 inchange**

Les codes attribues a l'ouverture de s35 sont `EXC-R10` (bloquante), `EXC-R11`, `EXC-R12`
(`FICHE-bandit-manchot-exclusion-cp.md`, bloc du 2026-09-11 s34). Le verdict de `S35-EXC-INDICES`
(`verdicts-nadia.md`, section 1) : `EXC-R10` **LEVEE**, `EXC-R11` **SOLDEE**, mais **`EXC-R12` :
"sa lettre est payee, son objet l'est a moitie"** - le subjonctif passe et le pronom elide sont
retires, la phrase servie n'est pas plus simple pour autant. **`EXC-R12` n'est pas levee en
entier** : la totalite des codes attribues n'est donc pas soldee, et le paquet reste **NON PAYE**
sous la methode binaire de `DEC-0086`/`DEC-0087`, au meme prix qu'a l'ouverture, **1,0**.

Les deux reserves neuves qu'il ouvre (`EXC-R13`, `EXC-R14`) sont **marginales, 0,0** : le paquet
est deja compte non paye pour son propre code restant (`EXC-R12`), sur le meme patron que
`REG-R4`/`REG-R5` et `PER-R3` a l'ouverture de s34 (`RECALCUL-registre-de-dette-ouverture-s34.md`,
section 4) - ces deux codes voyagent avec la meme reprise a venir, sans facturation separee.

**Je ne tranche pas la question que `REGISTRE-reserves-neuves-s35.md` posait sur les codes plus
anciens `EXC-R1`, `EXC-R5`, `EXC-R6`** : elle est sans objet ici, `EXC-R12` suffit a elle seule a
maintenir le paquet NON PAYE quelle que soit la reponse sur ces trois codes-la.

### 7.2 `cm1-anglais` (0,5 a l'ouverture) - **PAYE, 0,5 retire**

Les codes attribues a l'ouverture de s35 sont `ANG-R3` (reserve restante de `S34-ANG-CORR`, sur
ses deux clauses) et `ANG-R7` (`planning.md`, section 3 ; `FICHE-bandit-manchot-anglais-cm1.md`,
etat au 2026-09-11). Le verdict de `S35-ANG-R3` (`verdicts-nadia.md`, section 1 ; fiche, etat au
2026-09-11 s35) : **l'increment est VALIDE** (non refuse), **`ANG-R3` est LEVEE sur ses deux
clauses**, **`ANG-R7` est LEVEE**. **La totalite des codes attribues est levee et l'increment
n'est pas refuse : le paquet est PAYE.** Le registre perd **0,5**.

La reserve neuve qu'il ouvre, `ANG-R8` (les gloses *(sous terre)* et *(une voiture)* ecartent les
deux distracteurs a la lecture), nait sur un paquet dont la dette ancienne vient d'etre remise a
zero dans le meme geste - le mecanisme que `DEC-0086` (section 4) nomme comme la premiere raison
pour laquelle le registre ne descend jamais autant que prevu, deja observe sur trois paquets a
s34 (`cm1-histoire-geographie`, `cp-eps-regles-de-securite`, `cp-eps-regles-jeu-collectif`) et sur
un a s33 (`cm1-histoire-geographie` egalement). **Le prix retenu est celui que `nadia-prof` chiffre
elle-meme pour cette reserve precise** (`REGISTRE-reserves-neuves-s35.md`, section 1 : "mot pour
mot, **0,25**"), et non le tarif generique de reprise complete de paquet (0,5, `PAR-10` standard) :
`ANG-R8` porte deja son texte de correction fourni par `nadia-prof` elle-meme, ce qui est
exactement le regime "mot pour mot" au tarif reduit, sur le meme patron que les corrections
verbatim tarifees a s29 sur ce paquet. Le registre **gagne 0,25**.

### 7.3 Les trois reserves neuves hors de l'echelle numerique - nommees, non chiffrees dans le 9,5

- **`SCO-R4`** (les deux libelles neufs de l'ecran de fin n'ont aucune taille declaree) : portee
  transverse D, deja tarifee a **0,0** par precedent
  (`RECALCUL-registre-de-dette-ouverture-s34.md`, section 2 : *"portee D, SCO-1, verification D7 |
  0,0, payee gratuitement"*). `SCO-R4` nait du meme perimetre - constat de
  `REGISTRE-reserves-neuves-s35.md`, confirme ici.
- **`FRI-R6`** (le texte alternatif de `frise-soir-routine` decrit une succession la ou l'exercice
  porte sur la simultaneite) : `designation-frise-de-la-journee` est un paquet du palier 2, qui ne
  fait partie d'aucun des seize postes de `DEC-0086`/`DEC-0087` - son suivi vit dans
  `squad/programme/MATRICE-couverture.md` (deja porte a la ligne `QLM-CP-14`), pas dans le
  registre numerique. **Elle n'ajoute rien au 9,5**, mais elle est la **plus prioritaire des six**
  au dire de `nadia-prof` elle-meme et de `camille-po` : a traiter avant qu'un second paquet de
  designation ne recopie la meme formule de texte alternatif.
- **`CONS-5`** (quatre items sur cent-dix ne font pas ce que leur consigne annonce, sur deux des
  quatre paquets issus de la scission d'histoire-geographie CM1) : les quatre paquets neufs
  n'existaient pas dans le registre des seize postes sous ce decoupage ; `cm1-histoire-geographie`
  y portait sa propre dette, separee (0,5, inchangee - non touchee par la scission technique de
  `S35-CONS-4`, qui ne visait aucun code de reserve pedagogique). `CONS-5` rejoint la famille
  `CONS-1`/`CONS-3`/`CONS-4`, deja "non chiffree" depuis plusieurs sprints : **elle n'ajoute rien
  au 9,5** non plus.

### 7.4 Le chiffre resultant

| | Montant |
|---|---|
| Registre a l'ouverture de s35 (`DEC-0087`, section 3) | 9,5 |
| `cm1-anglais` PAYE - dette ancienne retiree | -0,5 |
| `ANG-R8` - dette neuve sur paquet remis a zero, prix propre de `nadia-prof` | +0,25 |
| `cp-emc-exclusion` - reste NON PAYE, inchange | 0,0 |
| `EXC-R13`, `EXC-R14` - marginales sur paquet deja non paye | 0,0 |
| `SCO-R4` - portee D, precedent a 0,0 | 0,0 |
| `FRI-R6` - hors echelle numerique (palier 2), suivie dans la matrice | 0,0 |
| `CONS-5` - hors echelle numerique (famille `CONS`, non chiffree) | 0,0 |
| Les dix autres postes de `DEC-0087` (`cm1-histoire-geographie`, `cp-eps-regles-jeu-collectif`, `cp-emc-regle-souhait-loi`, `cp-eps-regles-de-securite`, les neuf postes a 0,5, le groupe 2b, `cp-eps-corps-effort`) | inchanges, non touches par s35 |
| **Registre de dette a l'ouverture de s36** | **9,25** |

**Ce chiffre descend pour la premiere fois depuis l'ouverture de cette strategie** (9,0 -> 9,5 a
s34), et il descend de **0,25**, pas de 0,5 net : la reouverture immediate de `cm1-anglais` par
`ANG-R8` sur le paquet meme qui vient d'etre solde est le mecanisme deja nomme par `DEC-0086`,
observe une nouvelle fois. `FRI-R6`, la reserve la plus lourde du sprint au jugement de
`nadia-prof`, ne pese rien dans ce chiffre parce qu'elle porte sur un paquet hors de l'echelle des
seize postes - ce qui ne diminue en rien sa priorite, fixee par `camille-po` en dehors du registre
numerique.

## 8. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique : les six verdicts (cinq `VALIDE`/`VALIDE SOUS RESERVE`,
  un `REFUSE`) sont de `nadia-prof`, dans `verdicts-nadia.md` et les fiches de conformite citees.
- Elle ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` au-dela du mouvement deja
  execute par `camille-po` (`QLM-CP-14`).
- Elle ne tranche aucune priorite de sprint futur : les six reserves neuves et `S36-QLM11-PLANCLASSE`
  restent des elements de backlog, a composer par `camille-po`.
- Elle ne recalcule pas les 1479 tests annonces par `theo-archi` et `yanis-qa` sur leurs propres
  arbres : elle nomme l'ecart avec le chiffre mesure sur l'arbre fusionne final (1481) sans lui
  chercher de cause au-dela de ce que le diff du dernier commit permet d'exclure.

## Decisions prises

**Aucune decision de sprint neuve n'est prise dans cette review.** Les decisions du sprint ont ete
prises ailleurs et sont enregistrees ici pour memoire, chacune verifiee physiquement presente sur
le disque avant d'etre citee :

- `DEC-0088` - derogation `nombreDeManches: 7` pour l'entree "Lire un document"
  (`camille-po`, 2026-09-11) - **Acceptee**. Verifie dans `squad/decisions/produit/DEC-index.md`.
- `ADR-0091` - le texte alternatif d'un item de designation ne nomme aucune de ses zones, l'indice
  ne porte aucun jeton distinctif de son libelle (`theo-archi`, 2026-09-11) - **`Propose` ->
  `Accepte`** dans ce meme sprint. Verifie dans `squad/decisions/adr/ADR-index.md`.
- `ADR-0092` - le libelle d'une zone n'est pas gardable par une arithmetique, la police servie est
  celle de l'appareil (`theo-archi`, 2026-09-11) - **`Propose` -> `Accepte`** dans ce meme sprint,
  avec deux amendements faits par son propre auteur avant l'acceptation. Verifie dans
  `squad/decisions/adr/ADR-index.md`.
- `ADR-0080` - une designation sur image est un choix parmi des zones enumerees
  (`theo-archi`, s28, deja `Propose`) - **`Propose` -> `Accepte`** a la confirmation de s35, son
  quatrieme et dernier critere etant rempli. Verifie dans `squad/decisions/adr/ADR-index.md`.

**Aucun `DEC` ni `ADR` n'est cree par cette ceremonie de review elle-meme.** Le recalcul du
registre de dette (section 7) est une mesure de gouvernance faite par `sofia-sm`, sur mandat
explicite de `camille-po`, exactement comme `DEC-0087` l'avait fait pour la cloture de s34 - ce
n'est ni un `DEC` ni un `ADR`, et je ne lui en donne pas la forme.
