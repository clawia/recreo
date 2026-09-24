# Planning - s41 - fusion de deux sprints projetes (enseignement moral et civique, sciences et technologie CM1, questionner le monde CP)

- **Horodatage de debut** : 2026-09-24 09:15 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s41-fusion-emc-sciences-qlm`, dossier deja ouvert par `theo-archi` (son avis de
  faisabilite y est commite avant ce planning, `eec116b1`). Ordre inhabituel note pour memoire,
  sans consequence : les six lots etaient deja geles par `DEC-0095` (forme 3, amendee section 9)
  avant que le planning ne soit anime, `theo-archi` a donc pu rendre son avis directement sur les
  six lignes citees par la `DEC`, comme son propre document le precise en en-tete.

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`bash outils/verifier-lignee.sh`) : `s41-sofia` (`../recreo-s41-sofia`) descend de `round-two` a
`784421ed` (dernier commit sur `round-two` au moment de l'ouverture, six stories de
`camille-po`), sans aucun autre ecart. Script `outils/verifier-lignee.sh` disponible sur le disque,
utilise directement plutot que la commande recopiee en prose.

## 2. Objectif gele, et sa provenance

**Objectif** (`squad/decisions/produit/DEC-0095-...md`, Acceptee, amendee le 2026-09-23 section 9) :
fermer, autant que possible, six lignes du referentiel sur deux terrains non chevauchants, avec
passage a **deux instances paralleles de `nadia-prof`** et une borne d'audit verifiee et recalculee
a **8,0** (contre 5,0 a instance unique) :

- **terrain 1, enseignement moral et civique (CP + CM1)** : `S41-EMC-SYMBOLES` (`EMC-CP-08`),
  `S41-EMC-DROITS` (`EMC-CP-10`), `S41-EMC-ROLES` (`EMC-CP-13`) - 4,5 / 5,0, audite par
  l'**instance A** ;
- **terrain 2, sciences et technologie CM1 + questionner le monde CP** : `S41-QLM-HYGIENE`
  (`QLM-CP-04`), `S41-QLM-SAISONS` (`QLM-CP-13`), `S41-SCI-CLASSER` (`SCI-CM1-06`) - 3,5 / 5,0,
  audite par l'**instance B**.

`PAR-2` force le terrain EMC (CP et CM1 comptent pour une seule matiere) sur une seule instance ;
`CYC-1` est satisfaite par construction sur le terrain sciences/questionner le monde CP, les deux
matieres y partageant la meme instance. `DEC-0095`, section 9.3, verifie qu'aucune clause de
frontiere n'est due **entre** les deux terrains - aucune paire de lignes candidates, une dans
chaque terrain, ne porte le meme objet du monde reel.

**Ce que ce planning ne rouvre pas** : `DEC-0089` (CE1 mathematiques seule, CM2 differe) ;
`DEC-0075` (neuf lignes bloquees, droits d'usage de reproductions d'oeuvres non tranches) ;
`EMC-CM1-06` et `SCI-CM1-18`, explicitement ecartees par `DEC-0093` section 6 et non reprises par
`DEC-0095`. Les six lignes retenues sont verifiees `non couvert` dans `MATRICE-couverture.md` et
verifiees ligne par ligne contre ces perimetres par `DEC-0095` section 5 - je ne recommence pas
cette verification, je la cite.

## 3. Stories deja instruites, avis de faisabilite deja rendu

Les six stories sont deja ecrites avec criteres d'acceptation testables dans
`docs/backlog/backlog.md`, section "Sprint 41" (`camille-po`, `784421ed`). L'avis de faisabilite de
`theo-archi` (`squad/sprints/s41-fusion-emc-sciences-qlm/avis-faisabilite.md`, `eec116b1`) porte sur
les six, sans qu'aucun `ADR` ni aucune regle ArchUnit neuve ne soit due :

| Lot | Ligne | Competence programme visee | Tarif | Verdict `theo-archi` |
|---|---|---|---|---|
| `S41-EMC-SYMBOLES` | `EMC-CP-08` | EMC, CP - reconnaitre et nommer les symboles de la Republique francaise | 1,5 | Faisable en l'etat, a condition de rester en texte seul (`ART-1`, image ecartee d'avance) |
| `S41-EMC-DROITS` | `EMC-CP-10` | EMC, CP - identifier les principaux droits de l'enfant | 1,5 | Faisable en l'etat |
| `S41-EMC-ROLES` | `EMC-CP-13` | EMC, CP - identifier quelques roles et services de l'ecole et de la commune | 1,5 | Faisable en l'etat |
| `S41-QLM-HYGIENE` | `QLM-CP-04` | Questionner le monde, CP - connaitre quelques regles d'hygiene de vie et leur effet sur la sante | 1,0 | Faisable en l'etat ; adjacence de contenu avec `cp-eps-corps-effort` (deja servi) nommee, non gardee par aucun automate |
| `S41-QLM-SAISONS` | `QLM-CP-13` | Questionner le monde, CP - nommer et ordonner les mois de l'annee et les saisons | 1,0 | Faisable en l'etat |
| `S41-SCI-CLASSER` | `SCI-CM1-06` | Sciences et technologie, CM1 - classer des etres vivants d'apres un caractere commun observable | 1,5 | **Faisable sous condition** : encoder `BIN-1` dans le test du paquet neuf (section 6.6 de l'avis) |

Porte technique mesuree par `theo-archi` sur l'arbre de `998b9992` (avant les six lots) :
`bash outils/mvn-jdk21.sh -B clean verify` vert, quatre modules et le reacteur complet - ce vert ne
vaut que pour le job `build` de la CI, les trois autres jobs (`prefixes`, `chemins`, `outils`) ne
sont pas mesures par cette commande.

## 4. Affectations nominatives finales

**Proposition de `camille-po`, verifiee et confirmee sans ajustement** (`docs/backlog/backlog.md`,
section "Sprint 41", "Agent pressenti" de chaque story) : la charge est equilibree a deux lots par
developpeur, chaque terrain reste jouable en parallele par ses deux devs principaux, et la seule
condition technique du sprint (`BIN-1`) tombe sur la developpeuse deja affectee au lot concerne.

| Lot | Terrain / instance | Developpeur | Condition |
|---|---|---|---|
| `S41-EMC-SYMBOLES` (`EMC-CP-08`) | 1 / A | `marc-dev` | - |
| `S41-EMC-DROITS` (`EMC-CP-10`) | 1 / A | `marc-dev` | - |
| `S41-EMC-ROLES` (`EMC-CP-13`) | 1 / A | `lea-dev` | - |
| `S41-QLM-HYGIENE` (`QLM-CP-04`) | 2 / B | `sofia-sm` (casquette dev) | - |
| `S41-QLM-SAISONS` (`QLM-CP-13`) | 2 / B | `sofia-sm` (casquette dev) | - |
| `S41-SCI-CLASSER` (`SCI-CM1-06`) | 2 / B | `lea-dev` | **`BIN-1`, section 5 ci-dessous** |

`lea-dev` est la seule des trois developpeurs a porter un lot sur chacun des deux terrains. Ce
n'est pas un obstacle a la parallelisation des deux terrains eux-memes - `docs/backlog/backlog.md`
le note deja, et l'avis de `theo-archi` (section 7) confirme que le risque technique du parallelisme
ne tient pas au decoupage par terrain mais a trois fichiers partages par les six lots, quel que soit
le terrain. Point de vigilance retenu pour le sprint, non bloquant : les six lots ecrivent tous dans
`adapters/adapter-content/src/main/resources/catalogue.yaml`,
`adapters/adapter-content/src/test/java/.../LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`
et `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`,
tous les trois en fin de fichier - des conflits de fusion certains, jamais silencieux (les deux
garde-fous mordent dans les deux sens). Chaque developpeur fusionne son lot des qu'il est vert
plutot que d'accumuler les six en parallele sur une meme branche, pour garder ces conflits petits et
visibles un par un.

## 5. Condition `BIN-1` pour `lea-dev` sur `S41-SCI-CLASSER`

`SCI-CM1-06` est une ligne de classement a deux categories. `ARBITRAGE-PREALABLE-s31`, section 5,
pose `BIN-1` : **au plus la moitie des items d'un paquet portent deux propositions** - refus
d'avance sur tout paquet de classement binaire majoritaire. Aucun garde-fou transverse ne l'encode
aujourd'hui (`theo-archi`, avis section 6.6) : elle vit dans un seul test de paquet,
`PaquetCpEmcRegleSouhaitLoiTest`, et le depot porte deja l'exemple d'un paquet livre a s40
(`cm1-emc-regle-loi`, 6 binaires sur 11 items) dont le test ne porte aucune assertion `BIN-1`.

**Condition du verdict de `theo-archi`, a tenir dans le meme commit que le paquet** : le test de
paquet de `S41-SCI-CLASSER` encode `BIN-1` sur le patron exact de `PaquetCpEmcRegleSouhaitLoiTest` -
denominateur le paquet entier, jamais une seule tache. Sans cette assertion dans
`Paquet<X>ClasserTest` (ou nom equivalent), le lot n'est pas faisable au sens de l'avis rendu.

## 6. Deux instances paralleles de `nadia-prof` - Done pedagogique en fin de sprint

- **Instance A**, terrain 1 (enseignement moral et civique) : `S41-EMC-SYMBOLES`,
  `S41-EMC-DROITS`, `S41-EMC-ROLES`. Budget opposable 5,0, cout retenu 4,5 - **aucune marge de
  rejeu individuelle** (`DEC-0095`, section 9.5) : un refus sur l'un des trois lots ferait depasser
  la borne de l'instance sans reallocation ecrite. Point signale a l'ouverture du sprint, non
  tranche ici.
- **Instance B**, terrain 2 (sciences et technologie CM1 + questionner le monde CP) :
  `S41-QLM-HYGIENE`, `S41-QLM-SAISONS`, `S41-SCI-CLASSER`. Budget opposable 5,0, cout retenu 3,5 -
  marge de 1,5.

**Fait a lire par l'instance B avant d'auditer `S41-QLM-HYGIENE`, route par `theo-archi` (avis,
section 6.4), non tranche par ce planning** : `cp-eps-corps-effort`, deja servi et valide, couvre
deja "boire de l'eau autour d'un effort" et "le repos apres un effort physique intense", et
`QLM-CP-04` porte sommeil, alimentation et hygiene du corps. Les deux lignes se touchent sur l'eau
et sur le repos ; aucun garde-fou du depot ne compare le contenu de deux paquets differents. C'est
un fait a lire avant d'ecrire le lot, pas un verdict - le discriminant que l'arbitrage prealable
donne deja est que `cp-eps-corps-effort` borne tout "autour de l'effort physique", ce que
`S41-QLM-HYGIENE` ne doit pas repeter.

## 7. Verification `PAR-16` - point de controle poste/story

Meme fallback qu'aux cinq sprints precedents (s35-s40) : aucune table de composition budgetaire de
strategie n'est en vigueur (`DEC-0083` refermee par `DEC-0087`). `PAR-16` verifie donc que chaque
element retenu porte une affectation nominative ou un motif explicite de non-action.

| Element | Affectation nominative | Issue |
|---|---|---|
| `S41-EMC-SYMBOLES` (`EMC-CP-08`) | `marc-dev`, puis `nadia-prof` instance A | Engagee |
| `S41-EMC-DROITS` (`EMC-CP-10`) | `marc-dev`, puis `nadia-prof` instance A | Engagee |
| `S41-EMC-ROLES` (`EMC-CP-13`) | `lea-dev`, puis `nadia-prof` instance A | Engagee |
| `S41-QLM-HYGIENE` (`QLM-CP-04`) | `sofia-sm` (casquette dev), puis `nadia-prof` instance B | Engagee |
| `S41-QLM-SAISONS` (`QLM-CP-13`) | `sofia-sm` (casquette dev), puis `nadia-prof` instance B | Engagee |
| `S41-SCI-CLASSER` (`SCI-CM1-06`) | `lea-dev` sous condition `BIN-1`, puis `nadia-prof` instance B | Engagee sous condition |

Aucun element n'est "non engage, budget libere" : les six lots du perimetre de `DEC-0095` sont tous
affectes.

## 8. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : aucune des deux n'est due sur les six
  lots (avis de `theo-archi`, section 1).
- Il ne tranche pas la marge de rejeu nulle du terrain 1, ni le `+1,0` que `nadia-prof` a refuse de
  s'attribuer elle-meme (`DEC-0095`, section 9.7) : ces deux points restent routes, non bloquants.
- Il ne tranche aucune question pedagogique : l'adjacence `QLM-CP-04` / `cp-eps-corps-effort`
  (section 6 ci-dessus) reste a lire par l'instance B, pas a decider par ce planning.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.
- Il ne rouvre ni `DEC-0089`, ni `DEC-0075`, ni `EMC-CM1-06`, ni `SCI-CM1-18`.

## Decisions prises

Aucune decision de sprint n'est prise dans ce planning. `DEC-0095` (objectif et perimetre) et
l'avis de faisabilite de `theo-archi` existent deja avant ce planning ; ce document confirme
l'affectation nominative proposee par `camille-po` sans l'amender et ouvre le sprint au
developpement.
