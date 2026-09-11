# Registre des reserves neuves de s35

- **Type** : document de travail de `camille-po`, pas une `DEC` - sur le patron de
  `RECALCUL-registre-de-dette-ouverture-s34.md` et de la section "Registre de dette" de
  `squad/sprints/s34-dernier-sprint-du-plan-2-palier-2-engage/RAPPORT.md`. Il enregistre les
  reserves non bloquantes ouvertes par `nadia-prof` a s35, avec le prix qu'elle leur donne
  elle-meme et le paquet qu'elles concernent ; il ne recalcule pas le total du registre de dette
  (9,5 a l'ouverture de s35, `DEC-0087`, section 3) et ne tranche aucune priorite de sprint.
- **Date** : 2026-09-11.
- **Auteur** : `camille-po`.
- **Commande** : cloture de s35 (`squad/sprints/s35-fermer-ce-qui-n-a-pas-recu-le-go-pedagogique/verdicts-nadia.md`,
  section 7 : *"Les huit reserves neuves en stories [...] a `camille-po`"*).
- **Ce que ce document ne fait pas** : il ne deplace aucun statut de
  `squad/programme/MATRICE-couverture.md` ; il ne fixe pas le registre de dette total a la cloture
  de s35 - cette mesure, comme a s34, revient a `camille-po` et `sofia-sm` ensemble au moment de la
  cloture, une fois su si `cp-emc-exclusion` et `cm1-anglais` repassent PAYE (leurs codes anciens
  etant leves sans refus ce sprint) ; il ne tranche aucune question technique ni pedagogique.

---

## 1. Les six reserves, prix donne par `nadia-prof` elle-meme, source citee

| Reserve | Paquet ou portee | Objet, en une phrase | Regime et prix | Source |
|---|---|---|---|---|
| `SCO-R4` | Transverse - ecran de fin de partie (`retour.html`, `recreo.css`) | Les deux libelles qui disent lequel des deux nombres est lequel n'ont aucune taille declaree et heritent de 1rem, sous le plancher de 1,25rem que porte tout le reste de l'ecran | mot pour mot, **0,25** | `PASSE-TRANSVERSE-s35-ecran-de-fin-de-partie.md`, section 2 |
| `EXC-R13` | `cp-emc-exclusion` (tache B) | La proposition des items 8 et 11 qui porte "un adulte sait deja" est un passif a agent, plus long que ce qu'il remplace, pour un enfant de CP | mot pour mot, **0,25** | `FICHE-bandit-manchot-exclusion-cp.md`, section correspondant a `EXC-R13` |
| `EXC-R14` | `cp-emc-exclusion` (tache B) | Les cinq indices de la tache B font 19 a 24 mots contre 10 a 12 sur la tache A - la condition de `nadia-prof` elle-meme (refuter deux conduites dans une seule phrase) a produit cette longueur | rejeu borne, **0,25** | `FICHE-bandit-manchot-exclusion-cp.md`, section 4 |
| `FRI-R6` | `designation-frise-de-la-journee` (`cp-frise-de-la-journee`) | Le texte alternatif de `frise-soir-routine` annonce "quatre moments de gauche a droite" pour un dessin de quatre moments sur trois places, deux d'entre eux empiles - l'ecart tombe exactement sur la relation *en meme temps* que testent les items 10 et 11 | mot pour mot, **0,25** | `FICHE-designation-frise-de-la-journee.md`, section 5 |
| `ANG-R8` | `cm1-anglais` (`bandit-manchot-anglais-cm1`) | Les gloses *(sous terre)* et *(une voiture)* sur l'item du transport londonien ecartent les deux distracteurs a la seule lecture, sans que l'enfant sache rien de Londres | mot pour mot, **0,25** | `FICHE-bandit-manchot-anglais-cm1.md`, section 4 |
| `CONS-5` | Les quatre paquets issus de la scission d'histoire-geographie CM1 (`nommer-en-histoire`, trois items ; `nommer-en-geographie`, un item) | Quatre items sur 110 ne font pas ce que leur consigne annonce (l'enfant compare deux espaces ou produit un evenement, il ne nomme rien) | mot pour mot, **0,25** | `FICHE-bandit-manchot-histoire-geographie-cm1.md`, section 4 |

**Total des six prix individuels, tels que `nadia-prof` les a chacun chiffres** : 6 x 0,25 = **1,5**.

**Pourquoi ce total n'est pas ajoute mecaniquement aux 9,5 de l'ouverture de s35** : le registre de
dette que `DEC-0086`/`DEC-0087` tiennent facture **par paquet** (0,5 la reprise d'un paquet entier,
quel que soit le nombre de codes qu'il porte, sauf lot sous `PAR-14`/`PAR-15`), alors que le prix
que `nadia-prof` ecrit dans chaque fiche est **son estimation du cout de cette reserve prise
isolement** - les deux echelles ne s'additionnent pas terme a terme sans reconciliation. Cette
reconciliation demande de savoir, paquet par paquet, si la dette ancienne est soldee :

- **`cp-emc-exclusion`** portait 1,0 non paye a l'ouverture de s35 (`DEC-0087`, section 3,
  increment refuse a s34). Ce sprint leve `EXC-R10` et solde `EXC-R11` sans refus
  (`S35-EXC-INDICES`, VALIDE SOUS RESERVE) : le paquet redevient candidat a PAYE, sous reserve de
  verifier que `EXC-R1`, `EXC-R5`, `EXC-R6` (dette plus ancienne) sont eux aussi leves - verification
  non refaite dans ce document.
- **`cm1-anglais`** portait 0,5 non paye a l'ouverture de s35 (`DEC-0087`, section 3, jamais
  engage a s34). Ce sprint leve `ANG-R3` sur ses deux clauses et `ANG-R7`, sans refus (`S35-ANG-R3`,
  VALIDE) : le paquet redevient candidat a PAYE, sous reserve de la meme verification sur `ANG-R4` a
  `ANG-R6` (deja levees a s34 selon la matrice, ligne `LVE-CM1-07`).
- **Les quatre paquets issus de la scission d'histoire-geographie** n'existaient pas dans le
  registre des seize postes de `DEC-0086`/`DEC-0087` sous ce decoupage ; `cm1-histoire-geographie`
  y portait sa propre dette (`CONS-1`, reliquat non chiffre separement). `CONS-5` est une reserve
  neuve sur deux des quatre paquets neufs, pas sur l'ancien.
- **`designation-frise-de-la-journee`** et **`designation-plan-de-la-classe`** (paquets du palier 2)
  ne font partie d'aucun des seize postes de `DEC-0086`/`DEC-0087` : leur suivi vit dans
  `squad/programme/MATRICE-couverture.md`, pas dans le registre numerique. `FRI-R6` s'y ajoute donc
  hors de cette echelle.
- **La portee transverse de l'ecran de fin** est deja tarifee a 0,0 par precedent
  (`RECALCUL-registre-de-dette-ouverture-s34.md`, section 2 : *"portee D | SCO-1, verification D7 |
  0,0 (payee gratuitement)"*). `SCO-R4` nait du meme perimetre.

**Ce que j'en conclus, sans trancher a la place de la cloture** : au moins deux paquets
(`cp-emc-exclusion`, `cm1-anglais`) sont en position de solder une partie de la dette ancienne de
9,5 pendant que ce sprint leur ouvre une reserve neuve - exactement le mecanisme que `DEC-0086`
nommait pour expliquer pourquoi le registre ne descend jamais autant que prevu. Le chiffre final
de la cloture de s35 reste a etablir par `camille-po` et `sofia-sm` ensemble, poste par poste,
comme cela a ete fait pour s34 (`DEC-0087`, section 3).

---

## 2. Priorite entre les six reserves

**`FRI-R6` est la plus prioritaire des six**, et `nadia-prof` le dit elle-meme en ces termes
(`FICHE-designation-frise-de-la-journee.md`, section 5) : *"c'est la plus lourde des reserves de ce
sprint, et elle est a payer avant que la formule ne soit recopiee sur un paquet de designation de
plus."* Le motif tient a la nature du defaut, pas a son prix (identique aux cinq autres, 0,25) :
c'est une **erreur factuelle** dans un contenu scolaire (une disposition de dessin mal decrite), sur
la seule mecanique du produit ou un second paquet - `designation-plan-de-la-classe` - est deja en
cours de reprise et pourrait recopier la meme formule de texte alternatif si elle n'est pas
corrigee d'abord. Elle est donc portee comme priorite haute dans toute composition de sprint qui
engagerait un second paquet de designation, avant les cinq autres reserves de ce registre.

Les cinq autres (`SCO-R4`, `EXC-R13`, `EXC-R14`, `ANG-R8`, `CONS-5`) sont non bloquantes, sans
ordre de priorite impose entre elles par `nadia-prof` - chacune reste ouverte jusqu'a ce qu'un
sprint l'engage.

---

## 3. Ce que ce document ne fait pas

- Il ne fixe pas de strategie pour un sprint a venir : c'est l'objet d'une composition de sprint
  puis d'une `DEC`, comme `DEC-0086`/`DEC-0087` l'ont fait pour s34.
- Il ne calcule pas le registre de dette total a la cloture de s35 : cette mesure demande de savoir
  si `cp-emc-exclusion` et `cm1-anglais` sont PAYES au sens de `DEC-0086`, ce que ce document ne
  tranche pas.
- Il ne deplace aucun statut de `squad/programme/MATRICE-couverture.md` et ne remplace aucune
  story du backlog.
