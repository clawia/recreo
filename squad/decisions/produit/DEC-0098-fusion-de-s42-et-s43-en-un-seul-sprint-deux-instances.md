# DEC-0098 - Fusion de s42 et s43 en un seul sprint, deux instances paralleles de `nadia-prof`

- **Statut** : Acceptee.
- **Date** : 2026-09-24
- **Auteur** : `camille-po`
- **Sprint** : s42 (fusionne ; s43 disparait, absorbe dans s42)
- **Prealable, en vigueur et non rouvert par ce document** : `DEC-0097` (perimetre chiffre propose
  pour s42 et s43, six lignes, `Proposee` a ce jour) - ce document ne rouvre aucune des exclusions
  ni des tarifs de `DEC-0097`, il en reprend le perimetre chiffre tel quel. `DEC-0095` (fusion de
  s41 et s42 projete en un seul sprint a deux instances, modele repris ici). `DEC-0089` (perimetre
  CE1 ouvert, mathematiques seule, CM2 differe) - aucune des six lignes n'est CE1.
- **Lien** : `squad/decisions/produit/DEC-0097-proposition-de-perimetre-pour-s42-et-s43.md`
  (sections 5 et 6, les six lignes et leurs tarifs estimes) ;
  `squad/decisions/produit/DEC-0095-objectif-et-perimetre-de-s41-fusion-de-deux-sprints-projetes-et-
  borne-d-audit-verifiee.md`, section 9 (methode de fusion a deux instances, reprise ici) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, section 13.5 (`PAR-8`, derivation de la borne a deux instances, 8,0) ;
  `squad/conformite/ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-
  le-prix-d-une-ligne-marginale.md`, section 4.2 (reconfirmation de 8,0 pour deux instances) et
  section 6.4 (critere de `nadia-prof` pour ecrire une clause de frontiere - un objet du monde reel
  partage entre des lignes de matieres differentes affectees a des instances differentes) ;
  `squad/decisions/adr/ADR-0081-une-ligne-de-referentiel-a-un-code-alloue-et-un-item-servi-ne-sert-qu-
  une-ligne.md`, clause 5 ; `squad/sprints/s41-fusion-emc-sciences-qlm/retro.md`, section 2 (le cout
  reel du risque de marge nulle a s41) ; `squad/README.md`, section 2 bis (les trois formes
  d'attribution).
- **Competence programme visee** : transverse - ce document porte sur l'objectif, le perimetre et la
  borne d'un sprint, pas sur le contenu d'une ligne.

---

## 1. Ce que ce document fait

1. Formalise en `DEC` l'instruction recue : fusionner s42 et s43 (`DEC-0097`) en un seul sprint,
   avec deux instances paralleles de `nadia-prof`, sur le modele deja utilise pour s41 (`DEC-0095`).
2. Nomme les limites de sourcage de cette instruction, et ce qu'elle valide reellement - section 2.
3. Verifie, sans la reprendre de confiance, que la borne a deux instances est toujours 8,0 et n'a pas
   ete revisee depuis s41 - section 3.
4. Verifie ligne par ligne les clauses de non-collision (`PAR-2`, `CYC-1`, critere de frontiere de
   `nadia-prof`) avant de proposer une repartition en deux terrains - section 4.
5. Retient une repartition en deux terrains de trois lignes chacun - section 5.
6. Signale explicitement le risque de marge de rejeu nulle au niveau du sprint, sans le cacher -
   section 6.
7. Route l'ouverture effective du sprint a `sofia-sm` - section 7.

## 2. L'instruction recue, et ce qu'elle valide

**Recue directement comme instruction de tache, sans fichier `planning.md` et sans relais par
`sofia-sm`**, citee mot pour mot le 2026-09-24 : *"oui fais tu dans un seul sprint avec 2 instances
de nadia et fais ajouter MAT-CE1-18 a MATRICE-couverture.md"*. C'est la **forme 3** de
`squad/README.md`, section 2 bis (*"recu de MASTER, sans source dans le depot"*) - je le nomme comme
tel plutot que de fabriquer un relais qui n'existe pas, et je ne la fais pas dire plus qu'elle ne
dit (borne 1 de la forme 3).

**Ce que le "oui" valide** : le perimetre chiffre de `DEC-0097` (les six lignes des sections 5 et 6,
tarif estime total 8,0) - c'est l'objet explicite auquel "oui" repond, `DEC-0097` etant le seul
document en attente de validation au moment ou l'instruction est recue.

**Ce que le reste de l'instruction demande, et qui n'etait pas deja tranche par `DEC-0097`** :
fusionner les deux sprints proposes (s42, s43) en un seul, sur un dispositif a deux instances
paralleles de `nadia-prof` - le modele deja utilise pour s41 (`DEC-0095`). `DEC-0097`, section 1,
raisonnait explicitement a instance unique, borne 5,0 par sprint ; ce document remplace cette
hypothese par celle a deux instances, sans rouvrir le chiffrage des six lignes elles-memes.

**Ce que je ne fais pas dire a cette instruction, comme `DEC-0095` ne l'a pas fait pour la sienne** :
le fait que la borne a deux instances absorbe les huit unites de tarif sans y regarder - c'est
l'objet de la section 3, qui se verifie contre le depot, pas contre l'instruction.

**La seconde partie de l'instruction** - ajouter `MAT-CE1-18` a `MATRICE-couverture.md` - est traitee
independamment de ce document, par une correction de tenue de la matrice deja commitee ce jour
(`c684f86a`), suivant le motif deja pose par `DEC-0097`, section 8 : ce n'est pas une ligne
candidate de sprint, c'est un ecart de tenue corrige hors de tout perimetre de sprint.

## 3. La borne a deux instances, verifiee et non reprise de seconde main

**Ce que je ne fais pas** : reprendre "8,0" cite par `DEC-0095` comme une preuve suffisante. Je
verifie que rien ne l'a revisee depuis s41.

**La chaine, deja etablie par `DEC-0095` section 9.2 et non rouverte ici** :

1. **Origine, s29** (`ARBITRAGE-PREALABLE-s29`, section 13.5) : `nadia-prof` derive 8,0 depuis
   2 x 5,0 = 10,0 brut, moins 0,5 (passe transverse payee), moins 0,5 (dettes de portee D
   payables), moins 1,0 (retenue de defiance sur un premier essai de parallelisation). `PAR-8` :
   *"la borne passe de 5,0 a 8,0 pour s29. Le plafond individuel reste 5,0 par instance, et au plus
   trois lots par instance."*
2. **Reconfirmation explicite au passage a trois instances, s32** (`ARBITRAGE-PREALABLE-s32`,
   section 4.2) : *"Si l'utilisatrice reste a deux instances, ma borne est 8,0, comme je l'ai ecrit
   ce matin meme a la cloture de s31, et rien dans ce document ne la change. 12,0 n'est pas une
   revision a la hausse de 8,0 : c'est la borne d'un autre dispositif."*
3. **Tenue a l'unite a s41** (`DEC-0095`, section 9.5 ; confirme par `squad/sprints/s41-fusion-emc-
   sciences-qlm/RAPPORT.md`) : sprint clos a 8,0/8,0.

**Verification que rien ne l'a revisee depuis** : aucun commit sur les deux fichiers d'arbitrage
cites ci-dessus depuis le 2026-09-08 (`git log` sur les deux chemins, dernier commit `b1ee89f4`,
s32). Aucune `DEC` entre `DEC-0095` et ce document ne mentionne d'instance ni de borne : `DEC-0096`
(retrait d'items de `cp-semaine`) et `DEC-0097` (perimetre s42/s43, section 1 : *"aucune reconduction
[du dispositif a deux instances] n'est ecrite pour s42 ni pour s43. La regle par defaut est donc
l'instance unique"*) confirment toutes deux, chacune a sa maniere, qu'aucun arbitrage nouveau n'a
touche au chiffre. La retro de s41 (`squad/sprints/s41-fusion-emc-sciences-qlm/retro.md`, section 3)
ne propose non plus aucun amendement sur ce point.

> **Borne retenue pour la fusion de s42/s43 : 8,0.** Inchangee depuis le 2026-09-08, jamais revisee
> a la hausse ni a la baisse pour le dispositif a deux instances. Meme deux clauses reconduites sans
> changement : plafond individuel dur **5,0 par instance**, et **au plus trois lots par instance**
> (`PAR-8`).

**Le perimetre chiffre de `DEC-0097` (huit unites de tarif, six lignes) tient exactement dans cette
borne, a l'unite pres : 8,0 / 8,0.**

## 4. Verification des clauses de non-collision, ligne par ligne

**Contrairement a s41** (EMC d'un cote, sciences CM1 et questionner le monde CP de l'autre - deux
groupes nettement separes par matiere), **les six lignes de `DEC-0097` touchent six matieres
differentes, chacune une seule fois** : aucun regroupement naturel par matiere n'existe. La
repartition en deux terrains ne peut donc pas s'appuyer sur `PAR-2` pour se dicter elle-meme (voir
ci-dessous) ; elle se vérifie ligne par ligne.

| Ligne | Matiere / niveau | Objet du monde reel porte |
|---|---|---|
| Lire silencieusement | francais CM1 | competence transversale de lecture, aucun objet de contenu propre (le texte support n'est pas encore ecrit) |
| `HG-CM1-05` | histoire-geographie CM1 | rois ayant marque la construction du royaume - periode monarchique |
| `QLM-CP-06` | questionner le monde CP | matiere d'un objet (bois, metal, plastique, verre...), proprietes physiques |
| `ART-CP-05` | arts plastiques CP | couleurs primaires et melanges |
| `EMC-CM1-05` | enseignement moral et civique CM1 | symboles de la Republique (drapeau, devise, Marianne, hymne) |
| `MAT-CP-10` (moitie textuelle) | mathematiques CP | deplacements decrits sur un quadrillage - geometrie spatiale |

### 4.1 `PAR-2` - une matiere et un niveau ne sont jamais partages entre deux instances

Chacune des six lignes porte une matiere et un niveau **distincts des cinq autres** : aucune paire
de lignes ne partage la meme matiere (contrairement a s41, ou EMC CP et EMC CM1 comptaient pour une
seule matiere et forcaient les trois lignes d'EMC dans un seul terrain). `PAR-2` ne contraint donc
**aucune** composition possible des deux terrains ici : elle est satisfaite quelle que soit la
repartition retenue, par construction, faute d'une matiere qui apparaitrait deux fois.

### 4.2 `CYC-1` - un item de cycle 3 ne contredit aucun item de cycle 2 deja servi sur le meme objet

`CYC-1` s'applique quand une ligne de cycle 3 (sciences et technologie CM1, histoire-geographie
CM1) partage un objet avec une ligne de cycle 2 de *questionner le monde* CP. La seule paire
cycle 2 / cycle 3 de ce lot est `QLM-CP-06` (matiere d'un objet) / `HG-CM1-05` (rois du royaume) :
**aucun objet du monde reel commun** - l'une porte les proprietes physiques d'un materiau, l'autre
une chronologie de personnages historiques. `CYC-1` ne trouve donc aucune occurrence a instruire
dans ce lot ; elle reste opposable telle qu'ecrite si une ligne de sciences et technologie CM1
entrait dans un lot futur sur le meme objet que `QLM-CP-06` (composition de la matiere), ce qui
n'est pas le cas ici.

### 4.3 Le critere de `nadia-prof` pour ecrire une clause de frontiere - verifie, pas suppose

**Le critere qu'elle applique elle-meme** (`ARBITRAGE-PREALABLE-s32`, section 6.4) n'est pas "deux
terrains distincts", c'est **un objet du monde reel partage entre des lignes de matieres
differentes affectees a des instances differentes**. Verification exhaustive des quinze paires
possibles parmi les six lignes : aucune ne porte le meme objet qu'une autre. La paire la plus
proche thematiquement - `HG-CM1-05` (rois du royaume) et `EMC-CM1-05` (symboles de la Republique) -
porte deux objets distincts et non chevauchants : une chronologie de personnages d'un regime
politique aboli, et des symboles institutionnels du regime actuel. Ce ne sont pas le meme objet au
sens ou *"l'activite physique et le corps"* l'etait, litteralement, dans les trois referentiels
d'EPS, de sciences CM1 et de questionner le monde CP (`ARBITRAGE-PREALABLE-s32`, section 6.4) : il
n'existe ici aucune paire de libelles qui designe le meme fait du monde reel.

**Conclusion : aucune clause de frontiere n'est due entre les deux terrains proposes ci-dessous, ni
entre aucune autre composition possible de ces six lignes.** L'absence n'est pas supposee, elle est
verifiee contre le seul critere que `nadia-prof` a ecrit pour en produire une, applique aux quinze
paires du lot. Rien n'est donc route a `nadia-prof` sur ce point : a la difference de s41, ou une
clause de frontiere explicite avait ete ecrite et restait a verifier a l'usage, ici aucune n'est
requise en amont.

## 5. La repartition en deux terrains

**Methode** : `PAR-2` ne dictant aucun regroupement (section 4.1) et aucune clause de frontiere
n'etant due (section 4.3), la repartition retenue est celle qui equilibre le budget entre les deux
instances sous `PAR-8` (plafond 5,0, trois lots au plus par instance), plutot que d'en laisser une
consommer 4,5 pendant que l'autre n'en consomme que 3,5 - la composition de terrain la moins
equilibree qu'ait connue s41 (terrain EMC, 4,5/5,0, marge individuelle de seulement 0,5, cause
directe du risque nomme en section 6). Elle reprend, matiere pour matiere, le regroupement deja
instruit et sourci par `DEC-0097` sections 5 et 6, ce qui evite en outre de redistribuer des lignes
deja documentees ensemble sans motif nouveau.

| Terrain | Instance | Lot | Ligne | Tarif |
|---|---|---|---|---|
| 1 - francais CM1, histoire-geographie CM1, questionner le monde CP | A | `S42-FRA-LECTURE` | Lire silencieusement (francais CM1) | 1,0 |
| 1 | A | `S42-HG-ROIS` | `HG-CM1-05` (les rois) | 1,5 |
| 1 | A | `S42-QLM-MATIERE` | `QLM-CP-06` (matiere d'un objet) | 1,5 |
| **Sous-total terrain 1** | | | | **4,0 / 5,0** |
| 2 - arts plastiques CP, EMC CM1, mathematiques CP | B | `S42-ART-COULEURS` | `ART-CP-05` (couleurs) | 1,5 |
| 2 | B | `S42-EMC-SYMBOLES` | `EMC-CM1-05` (symboles de la Republique) | 1,5 |
| 2 | B | `S42-MAT-QUADRILLAGE` | `MAT-CP-10`, moitie textuelle (deplacements sur quadrillage) | 1,0 |
| **Sous-total terrain 2** | | | | **4,0 / 5,0** |
| **Total du sprint** | | | | **8,0 / 8,0** |

Chaque terrain porte exactement trois lots (plafond de `PAR-8` atteint en nombre de lots, pas en
budget) et reste sous le plafond individuel de 5,0, avec une marge individuelle de 1,0 chacun -
**une marge individuelle strictement meilleure que celle du terrain EMC a s41 (0,5)**, malgre un
total de sprint identique a la borne.

**Precision reprise de `DEC-0097`, non rouverte ici** : `MAT-CP-10` ne ferme que la moitie *decrire
un deplacement*, en choix textuel ; la moitie *se reperer et se deplacer* (designation sur
quadrillage, palier 2) reste `non couvert`. Le lot `S42-MAT-QUADRILLAGE` fait donc passer la ligne
de `non couvert` a `en cours`, pas a `couvert-valide`, avec la mention de couverture partielle deja
posee par `nadia-prof` (`DEC-0097`, section 6).

## 6. Le risque de marge de rejeu nulle - signale, pas cache

**Au niveau du sprint, ce perimetre consomme la totalite de la borne : 8,0 sur 8,0, exactement
comme s41.** Aucune unite de tarif n'est laissee sans lot pour absorber un refus qui demanderait un
second audit au-dela de ce que le tarif du lot couvre deja.

**Ce que ce risque a concretement coute a s41, pour ne pas le laisser abstrait** : le terrain EMC de
s41, compose a 4,5/5,0 sans marge de rejeu individuelle, a subi deux refus sur le meme lot
(`EMC-CP-08`, symboles de la Republique CP) le meme jour, plus un refus sur un second lot
(`EMC-CP-13`). Le risque avait ete nomme deux fois avant l'ouverture du sprint (`DEC-0095`, section
9.7 ; `planning.md` de s41, section 6) comme *"non bloquant mais a peser"*, et n'a jamais ete pese
par personne avant qu'il ne se produise (`squad/sprints/s41-fusion-emc-sciences-qlm/retro.md`,
section 2).

**Une difference reelle avec s41, qui attenue le risque sans l'annuler** : le terrain EMC de s41
n'avait aucune marge individuelle possible (4,5/5,0 etait deja la composition la moins chere
atteignable, tarif uniforme a 1,5 sur les trois lots). Ici, **chaque terrain conserve une marge
individuelle de 1,0 sur son propre plafond de 5,0** (section 5) : un refus isole sur un seul lot,
demandant une correction et un second audit dans le meme terrain, reste absorbable sans depasser le
plafond de cette instance. **Ce qui reste vrai malgre cette difference** : au niveau du sprint dans
son ensemble, aucune unite de tarif n'est disponible pour un lot supplementaire ou pour un refus qui
toucherait les deux terrains a la fois - la borne totale de 8,0 est integralement allouee, sans
reserve.

**Je ne tranche pas si ce risque est acceptable** : c'est un fait chiffre, route a `sofia-sm` et a
l'utilisatrice en section 7, exactement comme `DEC-0095` l'avait fait pour s41 - sans qu'il soit pese
davantage cette fois-ci.

## 7. Ce que ce document route

- **Ouverture du sprint fusionne** : a `sofia-sm`, avec le slug propose `s42-fusion-six-lignes-deux-
  instances` (`s43` disparait, absorbe dans `s42` ; `sofia-sm` reste libre d'ajuster le nom du
  dossier). Instruction du backlog et ecriture des stories restent entierement a elle : ce document
  ne cree ni story ni dossier de sprint.
- **Avis de faisabilite de `theo-archi`**, sur les six lots, directement : aucun arbitrage
  pedagogique prealable neuf n'est du, les six lignes etant deja instruites par `DEC-0097`.
- **A `sofia-sm` et a l'utilisatrice, non bloquant mais a peser explicitement cette fois avant
  l'ouverture du planning** : le risque de marge de rejeu nulle au niveau du sprint (section 6),
  deja nomme deux fois sans etre pese a s41 et materialise par deux refus sur le meme lot. Peser ce
  risque avant l'ouverture, pas seulement le nommer une troisieme fois, est le geste que la retro de
  s41 pointe comme n'ayant jamais eu lieu.
- **Rien n'est route a `nadia-prof` sur une clause de frontiere** : verifiee absente (section 4.3),
  a la difference de s41 qui en portait une a surveiller.
- **Confirmation de l'utilisatrice deja recue pour la fusion et les deux instances** (section 2,
  forme 3) : ce document est `Acceptee` sur cette base, pas `Proposee`. S'il est infirme ou ajuste,
  il est amende ou remplace, jamais silencieusement contourne.

## 8. Ce que ce document ne fait pas

- Il ne rouvre aucun des tarifs ni aucune des exclusions de `DEC-0097` : le perimetre chiffre des six
  lignes est repris tel quel.
- Il ne fixe aucune borne de tirage, aucune forme de distracteur, ni aucun contenu pedagogique pour
  aucune des six lignes : ce travail reste entierement a `nadia-prof`.
- Il ne choisit aucun mecanisme technique : ce travail reste a `theo-archi`.
- Il n'ouvre aucun dossier de sprint et n'ecrit aucune story dans `docs/backlog/backlog.md` : ces
  deux gestes reviennent a `sofia-sm`.
- Il ne modifie pas `squad/programme/MATRICE-couverture.md` : la correction de `MAT-CE1-18`,
  demandee dans la meme instruction, est traitee independamment et deja commitee (`c684f86a`).
- Il ne garantit la fermeture d'aucune des six lignes retenues : l'avis de faisabilite de
  `theo-archi` et le Done pedagogique final de chaque instance de `nadia-prof` restent a rendre.
- Il ne tranche pas si le risque de marge nulle au niveau du sprint est acceptable : c'est nomme,
  chiffre et route, pas resolu par moi.
