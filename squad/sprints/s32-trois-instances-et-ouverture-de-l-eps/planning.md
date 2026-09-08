# [Sprint 32] Planning - 2026-09-08

- **Horodatage de debut** : 2026-09-08 20:10
- **Anime par** : sofia-sm, casquette Scrum Master
- **Participants** : sofia-sm (SM, et casquette dev sur `S32-5`), marc-dev, lea-dev, theo-archi
  (branche `s32-theo` fusionnee ce planning, `ADR-0088`), nadia-prof (arbitrage prealable a trois
  instances deja rendu, `f503b4e5` et `b1ee89f4`), camille-po (`DEC-0083`, amendee, `60bebbce` et
  `ad5f307c`)

La strategie de s32 a s34 est arbitree par l'utilisatrice (`DEC-0083`) et n'est pas rouverte ici :
trois instances de `nadia-prof` en parallele, borne 12,0, Plan 2 (couverture soutenable) retenu.
Ce planning execute cette strategie pour s32, affecte nominativement les trois instances et les
trois developpeurs, et traite les trois obstacles routes nommement a `sofia-sm`.

## 0. Ce qui est deja entre, avant l'ouverture - fusion de `s32-theo`, quatre jobs mesures verts

**Casquette dev, geste distinct de la ceremonie de planning.** La branche `s32-theo` (tete
`043b8503`) n'etait pas fusionnee dans `round-two` : elle porte l'avis de faisabilite du palier 2,
`ADR-0080` amende et `ADR-0088` (Accepte, quatre clauses dont une `ArchRule`, `DecouplageMecaniqueContenuTest`
clause 12).

**Verification de lignee avant fusion** : `git merge-base round-two s32-theo` rend `b1ee89f4`, et
`git diff b1ee89f4..s32-theo` ne touche que des fichiers disjoints de ce que `round-two` a avance
depuis ce point (`docs/backlog/backlog.md`, le fichier de `DEC-0083`, `DEC-index.md` et
`MATRICE-couverture.md`, tous du seul cote de `round-two` ; tests, fichiers de `domain.mechanics.designation`,
`ADR-index.md` et les deux `ADR` neufs, tous du seul cote de `s32-theo`). **Fusion sans conflit**
(`git merge s32-theo`, commit `cb0b191e` apres reformulation du message de fusion au prefixe
`[SOF]`, la convention du depot pour un message de fusion generique).

**Quatre jobs de la CI lances par moi sur l'arbre fusionne, chacun nomme, aucun deduit** :

| Job | Commande | Verdict |
|---|---|---|
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s origin/round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | Vert |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | Vert |
| `outils` | les neuf juges purs de `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | Vert, 9 juges executes |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, racine) | **BUILD SUCCESS** |

**Mesure independante du total de tests, sur l'arbre fusionne, pas relayee** : comptage des
fichiers `TEST-*.xml` de `surefire-reports` et `failsafe-reports` apres le `clean verify`
ci-dessus - **192 fichiers, 1421 tests, 0 echec, 0 erreur**. Confirme exactement le chiffre
qu'`ADR-0088` annonce (1402 + 6 + 13), mesure de nouveau par moi plutot que recopie.

**`docs/backlog/backlog.md` corrige dans le meme geste** : la note de la section "Backlog ouvert
pour s33" qui disait la branche `s32-theo` non fusionnee est mise a jour pour porter la fusion et
son commit, plutot que de laisser une affirmation fausse dans un fichier que toute la squad lit.

## 1. Objectif du sprint

**Trois instances de `nadia-prof` en parallele pour la premiere fois. Fermer la tranche 1
d'histoire-geographie CM1 et la ligne d'anglais CM1 deja `en cours` (terrain A), payer la dette
d'EMC CP ouverte depuis s31 - `CAT-1`, `EMC-CP-07`, dix reserves (terrain B) -, et ouvrir
l'education physique et sportive CP par ses deux lignes de regles (terrain C), sans financer aucun
lot de sciences CM1 ni la ligne de corps-et-effort de l'EPS.**

**Gele pour la duree du sprint.** Composition retenue par l'utilisatrice (`DEC-0083`, section 6),
reprise sans recalcul.

## 2. Taux d'entree du sprint

**63 / 138 = 45,7 %.** Etat de `squad/programme/MATRICE-couverture.md` a la cloture de s31, repris
tel que `nadia-prof` l'a elle-meme repris sans le recalculer (arbitrage prealable de s32,
section 15).

## 3. La borne, la composition retenue, et le chiffre honnete

**Borne du sprint : 12,0**, decotee de 3,0 sur une capacite brute de 15,0 (1,0 par paire de
coutures, trois paires - `DEC-0083`, section 2). **Budget opposable par instance : 4,0** (`PAR-9`,
marge de rejeu comprise). Plafond individuel dur 5,0, au plus trois lots par instance (`PAR-8`,
inchange).

| Instance | Terrain | Postes | Total | Lignes visees |
|---|---|---|---|---|
| **A** | histoire-geographie CM1, anglais CM1, portee D | `S32-1` (2,5) + `S32-2` (0,5) + `S32-3` (0,5) + marge `PAR-11` (0,5) | 4,0 | +5 |
| **B** | dette d'EMC CP | `S32-CAT1` (1,0) + `S32-EMCCP07` (0,5) + `S32-4` (1,5) + marge (0,5) + marge non allouee (0,5) | 4,0 | +1 au plus |
| **C** | education physique et sportive, conditions inedites (`PAR-13`) | `S32-5` (1,0) + reserves soldees dans le sprint (1,0) + marge `PAR-11` amendee (1,5) + marge non allouee (0,5) | 4,0 | +2 |

**Le chiffre a garder present tout le sprint** : **plafond arithmetique 8 lignes, 63 + 8 = 71/138 =
51,4 %. Attente raisonnable de `nadia-prof` : 6 a 7 lignes, soit 69 a 70/138 (50,0 % a 50,7 %).**
L'EPS est le seul terrain sans aucune mesure de calibration (`PAR-13`) : son attente descend a 0 ou
1 ligne sur les deux visees.

**Deux clauses opposables des ce sprint** (`DEC-0083`, section 3) : `PAR-9` clause de transfert
(tout transfert de budget entre instances en cours de sprint est ecrit au moment du transfert,
dans ce fichier, avec l'instance qui cede et le poste cede - **aucun transfert n'a ete ecrit a
l'ouverture** ; s'il en survient un en cours de sprint, il est ajoute ici avant la review, jamais
decouvert a la cloture) ; `PAR-11` amendee (0,5 par lot neuf audite, plus 0,5 pour l'instance dont
le terrain n'a jamais rencontre de contenu reel - seule l'instance C en beneficie ce sprint).

**Ce que ce sprint ne finance pas** : aucun lot de sciences CM1 (terrain de B, reporte a s33-s34) ;
le second lot d'EPS CP (`EPS-CP-10`, `EPS-CP-11`, la ligne de corps-et-effort, sous condition de la
frontiere - section 8 ci-dessous) ; `D4` (groupe 2b) et `D7` (verification de `SCO-1`), tous deux
routes a s33 ; les trois stories du palier 2 (`S33-PAL2-TROU-A`, `S33-PAL2-TROU-B`, `S33-PAL2-1`),
qui restent au backlog de s33 et ne sont pas tirees dans s32 - leur seul prealable technique
(`ADR-0088`) vient d'entrer dans `round-two`, mais leur sequencement (`L-a` a `L-e`, avis de
`theo-archi`, section 7) et leur affectation restent a trancher au planning de s33.

## 4. Stories retenues

| Story | Titre | Competence programme visee | Decision liee | Affectee a |
|---|---|---|---|---|
| `S32-1` | Remplissage d'histoire-geographie CM1, tranche 1 | Histoire-geographie CM1 (`HG-CM1-01` a `-04`) | `DEC-0083` section 6 | `marc-dev` (contenu), instance A |
| `S32-2` | Remplissage de `LVE-CM1-07` | Langues vivantes CM1 (decouverte culturelle) | `DEC-0083` section 6 | `marc-dev` (contenu), instance A |
| `S32-3` | Passe transverse de l'instance d'accumulation | Transverse | `DEC-0083` sections 2, 3, 6 | nadia-prof, instance A |
| `S32-CAT1` | Le catechisme civique sur `EMC-CP-02`/`EMC-CP-06` | EMC CP, correction non bloquante | `DEC-0081` section 4 ; `DEC-0083` section 6 | `lea-dev` (contenu), instance B |
| `S32-EMCCP07` | Le marqueur restrictif de `EMC-CP-07` | EMC CP (`EMC-CP-07`) | `DEC-0081` ; `DEC-0083` section 6 | `lea-dev` (contenu), instance B |
| `S32-4` | Reprise de dix reserves d'EMC CP | EMC CP, correction non bloquante | `DEC-0083` sections 6, 7 | `lea-dev` (contenu), instance B |
| `S32-5` | Premier lot d'EPS CP - regles de securite, regles de jeu collectif | EPS CP (`EPS-CP-04`, `EPS-CP-08`) | `DEC-0074` ; `DEC-0083` sections 6, 9.2 | `sofia-sm`, casquette dev (contenu), instance C |

**Motif de l'affectation nominative entre les trois developpeurs.** Terrain A (`S32-1` + `S32-2`)
et terrain B (`S32-CAT1` + `S32-EMCCP07` + `S32-4`) touchent chacun un ensemble de paquets deja
existants sur `catalogue.yaml` : je les confie chacun a un seul developpeur pour eviter la
collision d'ecriture deja rencontree a s30 et s31 sur ce meme fichier (meme motif que le
sequencement impose a `marc-dev` a s31). Terrain C (`S32-5`) ouvre des paquets neufs, sans
recouvrement de fichier avec les deux autres terrains : je le prends moi-meme, casquette dev, pour
equilibrer la charge entre les trois developpeurs plutot que de la concentrer sur Lea (terrain B
porte deja trois stories). Les trois worktrees restent independants et je fusionnerai les trois
branches moi-meme, casquette dev, dans l'ordre ou elles arrivent - `catalogue.yaml` ne recoit que
des ajouts de blocs disjoints, jamais deux ecritures sur la meme ligne.

## 5. Avis de faisabilite - theo-archi

| Story | Avis | ADR requis |
|---|---|---|
| `S32-1`, `S32-2` | Faisable en l'etat - remplissage de paquets existants (`bandit-manchot-histoire-geographie-cm1`, `bandit-manchot-anglais-cm1`), aucune ligne de code hors contenu | Non |
| `S32-3` | Faisable en l'etat - travail d'audit, aucune ligne de code | Non |
| `S32-CAT1`, `S32-EMCCP07`, `S32-4` | Faisable en l'etat - reecriture de propositions et d'enonces sur des paquets existants, aucune ligne de code hors contenu | Non |
| `S32-5` | Faisable en l'etat - deux paquets neufs sur la mecanique `bandit-manchot` deja cablee, meme patron que tout paquet neuf depuis s09 | Non |
| Stories du palier 2 (`S33-PAL2-*`) | Non retenues a s32 (section 3) - avis complet dans `squad/sprints/s32-instruction-prealable/avis-faisabilite-theo-achevement-du-palier-2.md` | `ADR-0080` amende, `ADR-0088` (Accepte), deja fusionnes (section 0) |

**Ce que cette ligne dit, et ce qu'elle ne dit pas.** Aucune des sept stories de contenu de s32 ne
touche `domain`, `application`, `adapters` ou `bootstrap` : elles ajoutent ou reecrivent des
paquets YAML sous un schema deja decouple de la mecanique (`ADR-0081`, `DecouplageMecaniqueContenuTest`),
exactement le patron suivi sans ADR distinct depuis l'ouverture du francais a s04. **Je n'ai donc
pas sollicite un avis ecrit de `theo-archi` par story** : le seul avis de `theo-archi` que ce
sprint engage est celui, deja rendu et deja fusionne, sur le palier 2 (section 0). Si un
developpeur rencontre en ecrivant un obstacle que ce tableau n'a pas vu, il le route a `theo-archi`
avant de le contourner - ce tableau n'est pas une garantie a sa place.

## 6. Affectation des trois instances de `nadia-prof`, par terrain

Reprise de l'arbitrage de `nadia-prof` (section 6.2 de son arbitrage prealable) et de `DEC-0083`
section 6, sans recalcul :

- **Instance A** : histoire-geographie CM1, anglais CM1 (decouverte culturelle), et la portee D
  (passe transverse, `CONS-*`, verification de `SCO-1`, lignes `en cours`). A ne recoit aucune
  dette d'une autre matiere.
- **Instance B** : **son terrain permanent reste sciences et technologie CM1**, qu'elle detient
  depuis s30-s31 (quatre lignes fermees, `SCI-1` a `SCI-4` eprouvees) - aucun lot de sciences n'est
  budgete a s32, le terrain reste sien pour s33 et s34 (`DEC-0083`, section 6). **Ce sprint, elle
  recoit en plus, a titre ponctuel, la dette d'EMC CP** (`CAT-1`, `EMC-CP-07`, dix reserves) : ce
  sont des corrections sur des lignes deja `couvert-valide` ou deja refusees, pas l'ouverture d'un
  lot neuf, donc `PAR-2` (une matiere et un niveau ne sont jamais partages) n'est pas engagee - EMC
  CP ne devient pas le terrain de B, et rien n'empeche qu'un lot neuf d'EMC CP soit ouvert par une
  autre instance a un sprint futur.
- **Instance C** : l'education physique et sportive, aux deux niveaux (`PAR-2` : EPS CP et EPS CM1
  comptent pour une seule matiere, comme EMC CP et EMC CM1). Premier terrain sans aucune image
  d'aucune instance (`PAR-13`).

## 7. Affectation des deux paquets residuels sans lettre - `POS-R3` et `FOP-R4`/`DRO-R4`

`camille-po` a refuse de les affecter (`DEC-0083`, section 6) parce que `PAR-2` ne les tranche pas
mecaniquement : aucune des trois instances de s32 ne detient aujourd'hui la matiere qu'ils portent
(mathematiques CM1 pour `POS-R3` ; EMC CM1 pour `FOP-R4` et `DRO-R4`). C'est une question de
capacite et de charge entre instances, donc la mienne, et je la tranche ici plutot que de la
laisser sans lettre jusqu'a s34.

**Je n'invente pas cette repartition : je reprends celle que `nadia-prof` a deja chiffree elle-meme
dans son Plan 2 (arbitrage prealable, section 8.2, ligne "s34"), le seul plan retenu, plutot que la
repartition de son Plan 1 ecarte (section 8.1) qui les groupait differemment.**

- **`FOP-R4` et `DRO-R4`** (residu sur `fait-opinion-cm1` et `droit-devoir-cm1`, EMC CM1) sont
  affectes a **l'instance A**. Motif : le Plan 2 de `nadia-prof` les associe a un lot d'EMC CM1
  indicatif de s34 dans la meme ligne qu'elle chiffre pour A (section 8.2, colonne A du sprint
  s34) ; A porte deja la portee D et n'a aucune autre matiere en collision avec l'EMC CM1.
- **`POS-R3`** (residu sur `division-posee-cm1`, mathematiques CM1) est affecte a **l'instance B**.
  Motif : meme source - le Plan 2 de `nadia-prof` le chiffre dans la colonne B du sprint s34
  (section 8.2), aux cotes d'un lot de sciences CM1. Ce n'est pas un partage de la matiere
  mathematiques CM1 avec une autre instance (`PAR-2` n'est pas viole) : aucune autre instance ne
  touche cette matiere en s33 ni s34 dans le perimetre indicatif actuel, et un seul paquet residuel
  isole n'ouvre pas un terrain.

**Ce que cette affectation ne fait pas** : elle n'ouvre ni ne chiffre `POS-R3`, `FOP-R4` ni
`DRO-R4` dans la composition de s32 - ils restent au perimetre indicatif de s34 (`DEC-0083`,
section 8) et cette affectation ne fait qu'y attacher une lettre, prealable explicitement demande
avant qu'ils n'entrent dans une composition chiffree. Elle ne deplace aucun statut de la matrice de
couverture.

## 8. La frontiere corps-et-effort (EPS / sciences CM1 / questionner le monde CP) - obligation
## permanente, non declenchee ce sprint

`nadia-prof` a ecrit une frontiere opposable entre trois lignes de trois matieres qui portent le
meme objet - l'activite physique et le corps (arbitrage prealable, section 6.4 ; `DEC-0083`,
section 9.2) :

> - L'EPS ne porte que l'effort lui-meme : ce que l'effort fait au corps dans l'instant, ce qui
>   l'entoure (echauffement, hydratation, recuperation) et les regles de securite de la pratique.
>   Jamais l'hygiene de vie en general.
> - Les sciences CM1 ne portent que les quatre facteurs de sante generale - alimentation, activite
>   physique, sommeil, hygiene - rapportes au fonctionnement du corps, jamais aux effets immediats
>   d'un effort.
> - Questionner le monde CP ne porte que le niveau CP de l'hygiene de vie, et `CYC-1` s'applique :
>   aucun item de cycle 3 ne contredit ce que le CP enseigne sur le meme objet.

`nadia-prof` refuse d'avance toute fermeture des trois lignes concernees (`EPS-CP-10`, `EPS-CP-11`,
`EPS-CM1-10`, la ligne de sciences CM1 sur les quatre facteurs, la ligne de questionner le monde CP
sur l'hygiene de vie) sans que cette frontiere soit ecrite dans la specification de son lot, avant
le premier item.

**Aucune story de s32 ne touche ces lignes** : `S32-5` porte sur les regles (`EPS-CP-04`,
`EPS-CP-08`), pas sur le corps. **J'inscris ici l'obligation permanente qu'elle porte, pour qu'elle
ne se perde pas entre deux sprints** : toute story future qui specifie un lot touchant l'une de ces
cinq lignes reprend la frontiere ci-dessus mot pour mot dans ses criteres d'acceptation, avant que
`camille-po` ne l'ouvre. Elle s'applique donc a partir du planning de s33, sur les lots indicatifs
qui portent ces lignes (`DEC-0083`, section 8).

## 9. La barre `LON-1` / `LON-2`, reprise dans les criteres des deux stories de CP de ce sprint

`nadia-prof` a ecrit, le 2026-09-08, une barre chiffree pour tout enonce de CP (arbitrage
prealable, section 16.4) :

> **`LON-1`** : au CP, un enonce compte au plus 15 mots. **`LON-2`** : au CP, un enonce compte au
> plus deux phrases, et quand il en compte deux, la seconde est la question. Convention de
> comptage (section 16.1) : une forme elidee (`qu'il`, `l'ami`) compte pour un mot.

Cette barre est datee du jour meme ou `S32-4` et `S32-5` etaient deja au backlog sous une
formulation anterieure ("sans barre chiffree imposee" pour trois des reserves de `S32-4`). **Je
l'ai reprise dans `docs/backlog/backlog.md`, dans le meme geste que ce planning, plutot que de la
laisser vivre seulement dans ce fichier** :

- `S32-4` : les items 4 et 5 d'`EMO-R1` (mesures a 17 et 18 mots), les cinq enonces de la tache B
  de `POL-R1` (mesures a 20, 22, 23, 24, 25 mots, dont un en trois phrases) et les deux enonces les
  plus longs d'`EXC-R4` (mesures a 16 et 18 mots) doivent desormais tenir `LON-1` et `LON-2`,
  chiffres corriges repris de l'arbitrage prealable, section 16.2 et 16.5.
- `S32-5` : chaque enonce neuf des deux paquets d'EPS CP tient `LON-1` et `LON-2` des sa premiere
  ecriture - premiers items de CP ecrits sous cette barre.

`S32-1` et `S32-2` ne sont pas concernees : `LON-1`/`LON-2` ne valent que pour le CP (arbitrage
prealable, section 16.6).

## 10. Le declencheur de la borne de s33 - a mesurer a la cloture, pas a esperer

**Si s32 clot sans aucune occurrence des trois formes de defaut de couture que `nadia-prof` a
definies** (a - une story qui modifie un paquet et une story qui modifie sa consigne dans le meme
sprint ; b - un chiffre qu'aucune des deux moities ne peut verifier et qu'un tiers trouve faux en
recomptant ; c - deux instances qui ecrivent dans une meme ressource partagee sans le savoir),
**la retenue de 3,0 tombe en bloc, et la borne de s33 passe de 12,0 a 15,0** (`DEC-0083`,
section 2.1 ; arbitrage prealable, section 4.3). Elle tombe en bloc et non paire par paire : une
occurrence qu'aucune moitie ne peut attribuer a une paire precise n'est pas une condition
attribuable.

**Ce que je surveille specifiquement ce sprint, pour eviter chacune des trois formes** : (a) aucune
story de s32 ne modifie a la fois un paquet et sa consigne dans un sprint different de celui qui
sert le paquet - point deja verifie, `S32-1` porte les deux dans la meme story ; (b) toute mesure
citee dans `review.md` sera etablie par moi sur l'arbre fusionne, jamais relayee d'une instance ;
(c) les trois developpeurs travaillent dans des worktrees dedies sur des paquets disjoints
(section 4), et `catalogue.yaml` ne recoit que des ajouts de blocs, jamais une reecriture
partagee de la meme ligne.

## 11. Ce que je route, pas ce que je tranche

- **Un obstacle technique deja route et deja ferme avant ce planning** : les deux trous de contrat
  du palier 2, tranches par `ADR-0088` (section 0). Rien a router de plus a ce jour.
- **`SCO-1` a `SCO-7`** (le score reste parfait par elimination sur 64 des 66 entrees du
  catalogue) : route a `theo-archi` par `nadia-prof` elle-meme (arbitrage prealable, section 2 ;
  `DEC-0083`, section 9.1), planifie a s33 (`D7`). Rien a instruire ce sprint.
- **Le sequencement des cinq lots de developpement du palier 2** (`L-a` a `L-e`) et l'affectation
  du lot de designation `QLM-CP-11`+`QLM-CP-14` : restent au planning de s33, une fois `L-c`
  sequence (`DEC-0083`, section 13.2). Je ne les tranche pas ici.
- **Tout transfert de budget entre instances en cours de sprint** (`PAR-9`) : aucun a ce jour. Si
  l'un survient, il est ecrit dans une prochaine version de ce document avant la review, jamais
  decouvert a la cloture - `review.md` n'est pas le bon endroit pour l'introduire silencieusement.

## 12. Decisions prises

Aucune decision produit, pedagogique ou d'architecture n'est prise par moi dans ce planning : je
reprends du travail deja tranche par `nadia-prof`, `theo-archi` et `camille-po`. Ce que ce document
tranche, qui releve de mon role :

1. **Fusion de `s32-theo` dans `round-two`** et mesure independante des quatre jobs de la CI sur
   l'arbre fusionne (section 0).
2. **Affectation nominative des sept stories** entre `marc-dev`, `lea-dev` et moi-meme, casquette
   dev (section 4).
3. **Affectation de `POS-R3` (instance B) et de `FOP-R4`/`DRO-R4` (instance A)**, reprise du
   chiffrage deja fait par `nadia-prof` dans son Plan 2 (section 7).
4. **Reprise de la barre `LON-1`/`LON-2` dans les criteres d'acceptation de `S32-4` et `S32-5`**,
   avec correction des chiffres de longueur deja cites dans `docs/backlog/backlog.md` (section 9).
5. **Inscription de l'obligation permanente de la frontiere corps-et-effort** pour toute
   specification future touchant les cinq lignes concernees (section 8).
6. **Correction d'une note perimee de `docs/backlog/backlog.md`** annoncant `s32-theo` non
   fusionnee (section 0).

## 13. Prochaines etapes

- `marc-dev` : `S32-1` puis `S32-2`, en serie sur `catalogue.yaml` et les paquets de terrain A.
  Ouvre un worktree dedie (`git worktree add ../recreo-s32-marc -b s32-marc round-two`), verifie
  par `outils/verifier-lignee.sh` avant le premier commit.
- `lea-dev` : `S32-CAT1`, `S32-EMCCP07`, `S32-4`, en serie sur les paquets d'EMC CP de terrain B.
  Meme protocole de worktree dedie (`../recreo-s32-lea`).
- `sofia-sm`, casquette dev : `S32-5`, terrain C, paquets neufs sans recouvrement de fichier avec
  les deux autres. Worktree dedie (`../recreo-s32-sofia`).
- `nadia-prof` : instance A audite `S32-1`, `S32-2` et conduit `S32-3` (passe transverse) ; instance
  B audite `S32-CAT1`, `S32-EMCCP07`, `S32-4` ; instance C audite `S32-5`, premier terrain sans
  image.
- `sofia-sm`, casquette SM : fusionne les trois branches au fil de leur arrivee, verifie la lignee
  de chacune avant sa premiere ecriture, mesure les quatre jobs de la CI sur l'arbre assemble avant
  d'ouvrir la review.

## 14. Fusion des trois branches de developpement, jobs mesures, rouge herite et obstacles routes

**Casquette dev, geste distinct de la ceremonie de planning**, ecrit avant la review comme
`PAR-9` et la section 10 l'exigent pour tout transfert ou obstacle survenu en cours de sprint.

**Verification de lignee avant chaque fusion.** `git merge-base round-two s32-marc`,
`s32-lea` et `s32-sofia` rendent tous les trois `a613c38d`, la tete de `round-two` au moment ou
les trois worktrees ont ete provisionnes : les trois branches en descendent directement, aucune
n'est fourchee d'une tete perimee.

**Verification du point de couture avant de le supposer.** Deux branches sur trois touchent
`catalogue.yaml` et `TexteAccentueExposeParLePortEntrantTest` : diff verifie ligne a ligne avant
fusion plutot que suppose. `s32-marc` reecrit une ligne de consigne existante (bloc
histoire-geographie CM1) ; `s32-sofia` ajoute deux blocs neufs en fin de fichier (EPS CP). Meme
verification sur l'inventaire de `TexteAccentueExposeParLePortEntrantTest` : `s32-marc` modifie la
consigne de HG-CM1 (ligne ~450), `s32-sofia` ajoute des entrees dans des sections disjointes
(mecanique, titres, consignes, matiere, domaines, competences). Aucun chevauchement de ligne.

**Fusion sans conflit dans l'ordre marc, lea, sofia** : `git merge s32-marc` puis `git merge
s32-lea` puis `git merge s32-sofia`, toutes trois en fast merge automatique, aucun marqueur de
conflit residuel (`grep -rn "^<<<<<<<"` sur l'arbre, aucune occurrence).

**Quatre jobs de la CI lances par moi sur l'arbre fusionne, chacun nomme, aucun deduit** :

| Job | Commande | Verdict |
|---|---|---|
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s a613c38d..HEAD \| bash .github/scripts/verifier-prefixes.sh` | Vert |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | Vert |
| `outils` | les neuf juges purs de `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | Vert, 9 juges executes |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, racine) | **ROUGE, herite et attendu** |

**Aucune porte technique n'est annoncee.** Le rouge du job `build` est connu depuis avant la
fusion (`lea-dev`, commit `167a4421`, l'a paye sciemment et route) : `FAB-1c` est corrige sur
`cp-emc-regle-souhait-loi.yaml`, et l'exemption `DETTE_DE_MARQUEUR` de
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (bootstrap), qui prouvait ce defaut tant
qu'il existait, ne l'a pas ete - sa consigne a `lea-dev` interdisait de toucher du Java. Deux
echecs exacts, confirmes sur l'arbre fusionne (module `recreo-bootstrap`, 231 tests, 2 echecs, 0
erreur) :

- `laDetteNePeutNiGrandirNiPourrir` - le paquet exempte n'est plus fautif, l'exemption devrait
  etre vide et ne l'est pas.
- `leContenuQuiPorteLeRefusMaintenuFaitRougirLaClauseTroisSansSonExemption` - le test d'obligation
  attendait un rejet que la clause 3 ne prononce plus, l'exemption masquant desormais un cas
  conforme.

**Routee a `theo-archi`, non reparee par moi** : c'est un garde-fou, donc son terrain. Le
commentaire du test lui-meme programmait ce geste : *"le jour ou la story de s32 reecrit sa tache
B, cette exemption doit disparaitre dans le meme diff."*

**Compte de tests etabli par moi sur l'arbre fusionne, pas additionne depuis les trois rapports.**
Mesure directe (`mvn -B clean verify`) : `recreo-domain` 105, `recreo-application` 13,
`recreo-adapter-web` 388, `recreo-adapter-content` 688, `recreo-bootstrap` 231 (dont les 2 echecs
ci-dessus). Le job `build` s'arrete a l'echec de `surefire` avant la phase `failsafe`, donc les
tests d'integration de `recreo-bootstrap` n'y tournent pas ; remesures separement
(`-Dmaven.test.failure.ignore=true`, dans le seul but de compter, sans changer le verdict rouge
ci-dessus) : 6 tests d'integration, 0 echec, inchange depuis la base. **Total sur l'arbre fusionne :
1431 tests, 2 echecs, 0 erreur.**

Base rejouee independamment dans un worktree isole sur `a613c38d` (avant les trois fusions) pour
ne pas relayer le chiffre du planning sans le revérifier : 105 + 13 + 388 + 678 + 231 + 6 = 1421,
identique a ce que la section 0 annoncait. **Delta net +10, entierement dans
`recreo-adapter-content`** (678 -> 688) : il correspond exactement aux dix methodes `@Test`
ajoutees par `marc-dev` dans `PaquetCm1AnglaisTest` (+2) et `PaquetCm1HistoireGeographieTest`
(+8). `lea-dev` n'a touche aucun fichier Java, delta nul confirme. Sur mon propre lot
(`s32-sofia`), les deux paquets d'EPS neufs sont couverts par des tests deja generiques
(`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest`)
qui ajoutent des entrees a des structures de donnees lues par une seule methode `@Test` existante :
mon lot n'ajoute donc aucune execution de test nouvelle, malgre le contenu neuf - l'annonce
"+2 ou plus" faite avant mesure ne se confirme pas telle quelle, et c'est le chiffre mesure ici qui
fait foi.

**Obstacles routes, non tranches ici :**

| Obstacle | Categorie | Route a | Motif |
|---|---|---|---|
| Exemption `DETTE_DE_MARQUEUR` et son test d'obligation, `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` | Garde-fou, technique | `theo-archi` | Le defaut qu'elle exemptait est paye ; l'exemption doit disparaitre dans le meme diff, ce que la consigne "contenu pur" de `lea-dev` ne permettait pas |
| `EXC-R1` (consigne du catalogue) et `EXC-R5` (critere de l'adulte plutot que le lieu), `cp-emc-exclusion.yaml` | Technique, hors perimetre contenu | Retour a la dette | `EXC-R1` casserait quatre tests de l'inventaire de `TexteAccentueExposeParLePortEntrantTest` ; `EXC-R5` romprait le classement de tache de `PaquetCpEmcExclusionTest`. Aucune des deux ne rouvre une ligne de la matrice - reserves non bloquantes, ecrites plutot que closes |
| `LON-1` et `LON-2` sans regle executable (`ADR-0086`, section 16.6) | Technique, garde-fou absent | `theo-archi` | Une regle non executable est un souhait, pas une regle (`CLAUDE.md`) ; le respect de la barre a ete verifie a la main sur mon propre lot (22 enonces comptes par script), pas par un test qui mord |
| Plancher de `FER-3` : ma consigne a `marc-dev` disait "au moins 4 items", le depot ecrit `MINIMUM_PAR_TACHE = 3` dans chaque test de paquet et `MATRICE-couverture.md` confirme 3 sur `HG-CM1-07` ("7 contre 3, exactement au plancher") | Pedagogie, source de la regle | `nadia-prof` | L'erreur est la mienne, pas celle de `marc-dev` : il a construit sur 3 et l'a signale plutot que de le contourner. `nadia-prof` est la source de `FER-3` et tranche laquelle des deux valeurs est la bonne |
| Terme "les Sarrasins", `HG-CM1-04` (`cm1-histoire-geographie.yaml`, commentaire de `marc-dev`) | Pedagogie, formulation d'un contenu | `nadia-prof` | Terme traditionnellement enseigne dans le programme francais pour cet evenement, signale par `marc-dev` au moment de l'ecrire plutot que tranche par lui |

Aucun de ces six obstacles n'est clos par cette entree : elle les route, elle ne les tranche pas.
Le sprint n'a pas de porte technique verte tant que le job `build` est rouge, et cette entree
n'affirme aucun Done, ni technique ni pedagogique.

## 15. Fusion de `s32-theo2` - le rouge herite est paye, trois routages evoluent

**Casquette dev, geste distinct de la ceremonie de planning.**

**Verification de lignee avant fusion** : `git merge-base round-two s32-theo2` rend `7ed471a7`,
exactement la tete de `round-two` au moment ou `s32-theo2` a ete forkee (section 14). La branche en
descend directement, aucune tete perimee. **Fusion en fast-forward** (`git merge --ff-only
s32-theo2`), sans commit de fusion, sans conflit : le nouveau `HEAD` de `round-two` est `63590b33`.

**Point de couture verifie, pas suppose** : `squad/decisions/adr/ADR-index.md` porte l'entree
`ADR-0089`, et `squad/sprints/s32-trois-instances-et-ouverture-de-l-eps/confirmation-archunit.md`
existe dans mon propre repertoire de sprint.

**Quatre jobs de la CI lances par moi sur l'arbre fusionne, chacun nomme, aucun deduit** :

| Job | Commande | Verdict |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, racine) | **BUILD SUCCESS**, six modules `SUCCESS`, 02:06 min |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | Vert. *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | les neuf juges purs de `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py` | Vert, 9 juges executes |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 7ed471a7..HEAD \| bash .github/scripts/verifier-prefixes.sh` (plage reelle du push, le seul commit fusionne) | Vert. *"Tous les commits portent un prefixe d'agent connu."* |

**Compte de tests mesure par moi sur l'arbre fusionne, pas relaye de la confirmation de
`theo-archi`** : somme de l'attribut `tests` des fichiers `TEST-*.xml` de `surefire-reports` et
`failsafe-reports` apres le `clean verify` ci-dessus - `recreo-domain` 105, `recreo-application` 13,
`recreo-adapter-web` 388, `recreo-adapter-content` 689, `recreo-bootstrap` surefire 238, `recreo-bootstrap`
failsafe 6. **193 fichiers, 1 439 tests, 0 echec, 0 erreur.** Identique, chiffre pour chiffre, a la
mesure de `theo-archi` sur son propre worktree (`confirmation-archunit.md`) - deux mesures
independantes, pas une recopiee dans l'autre.

**Le rouge herite (section 14) est paye par `63590b33`, pas contourne** : l'exemption
`DETTE_DE_MARQUEUR` est retiree de `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (9
tests, 0 echec, la clause 3 garde une population de 1 111 items a deux distracteurs sous cliquet a
plafond zero), et `LON-1`/`LON-2` sont desormais executees par `ADR-0089`
(`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, 7 tests dont 3 morsures sur materiau reel).
**Je ne prononce aucun Done** : les quatre jobs sont verts sur l'arbre fusionne, mais le Done
technique appartient a `yanis-qa` et `theo-archi`, et le rapport qualite de `yanis-qa` n'est pas
encore produit ce sprint.

**Trois routages du tableau de la section 14 evoluent** :

| Obstacle | Ancien routage | Nouveau statut | Motif |
|---|---|---|---|
| `EXC-R1` (consigne du catalogue) | Retour a la dette, technique | Route a `camille-po` | `theo-archi` mesure que la mise a jour de l'inventaire est mecanique - le geste que `marc-dev` et moi avons chacun fait ce sprint. Ce qui manque n'est plus technique : c'est le texte de la consigne, donc du texte lu par l'enfant |
| `EXC-R5` (critere de l'adulte plutot que le lieu) | Retour a la dette, technique | Retiree de la dette, corrigee | Ce n'etait pas un probleme de tache mais un test (`PaquetCpEmcExclusionTest`) qui lisait l'identite d'une tache dans la sous-chaine `"A qui"` de l'enonce, la phrase exacte qu'`EXC-R5` demande de reecrire. La tache se lit desormais dans la bonne reponse, corrige dans `63590b33` |
| Trois comptes de `nadia-prof` (section 16.5 de son arbitrage) : `EMO-R1`, `EXC-R4`, `POL-R1` | Non ouvert a la section 14 | Route a `nadia-prof` | La mesure automatisee d'`ADR-0089` dement ses trois comptes, tous dans le meme sens : `EMO-R1` nomme deux enonces trop longs quand il y en a trois, `EXC-R4` de meme, et les cinq longueurs de `POL-R1` ne sont pas recomptees sous sa propre convention de comptage. Aucune reserve n'est modifiee par ce constat, aucun verdict ne change : le constat lui est rendu, elle reaudite |

**Une dette generale nouvelle, ouverte par `theo-archi` avec son propre chiffre, tracee pour s33** :
25 fichiers de test de paquet sur 95 lisent l'enonce par une operation de chaine plutot que par un
champ de tache dedie au schema d'item ; la regle generale n'est pas prise dans ce commit,
declencheur ecrit dans `ADR-0089`.

**Les deux obstacles restants du tableau de la section 14 sont inchanges**, tous deux toujours
routes a `nadia-prof` : le plancher de `FER-3` et le terme "les Sarrasins" (`HG-CM1-04`).

**Un obstacle distinct, hors du tableau de la section 14, note par `theo-archi` dans
`confirmation-archunit.md`** : la clause 2 de `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`
mord encore sur du contenu vivant prive de son exemption, `FOP-R4` etant ouverte et non payee ; le
geste attendu dans le meme diff que son paiement est ecrit dans la Javadoc du test concerne. Non
tranche ici, laisse au backlog ou il vit deja.
