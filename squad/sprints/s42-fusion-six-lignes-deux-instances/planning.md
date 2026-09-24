# Planning - s42 - fusion de deux sprints projetes (histoire-geographie CM1, questionner le
monde CP, arts plastiques CP, EMC CM1, mathematiques CP), deux instances de `nadia-prof`

- **Horodatage de debut** : 2026-09-24 (planning anime a la suite de la fusion `517abd9d` et de
  l'avis de faisabilite `9fed12bc`).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s42-fusion-six-lignes-deux-instances`, dossier deja ouvert par `theo-archi` (son
  avis de faisabilite y est commite avant ce planning, `9fed12bc`), comme a s41. Le nom du dossier
  garde la trace de son perimetre d'origine a six lignes (`DEC-0098`, avant l'amendement du meme
  jour qui en retire une) ; il n'est pas renomme, le perimetre reel a cinq lots est ecrit ici et
  dans `docs/backlog/backlog.md`.

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`bash outils/verifier-lignee.sh`) : `s42-sofia` (`../recreo-s42-sofia`) descend de `round-two` a
`9fed12bc` (dernier commit sur `round-two` au moment de l'ouverture, avis de faisabilite de
`theo-archi`), sans aucun autre ecart. Script `outils/verifier-lignee.sh` disponible sur le
disque, utilise directement plutot que la commande recopiee en prose.

## 2. Objectif gele, et sa provenance

**Objectif** (`DEC-0098`, Acceptee, amendee le 2026-09-24 section 9) : fermer, autant que
possible, les cinq lots restants du perimetre fusionne de s42/s43 sur deux terrains non
chevauchants, avec **deux instances paralleles de `nadia-prof`** et une borne d'audit de 8,0
(recalculee a s29, reconfirmee a s32, tenue a l'unite a s41), consommee a **7,0 / 8,0** apres le
retrait sans remplacement de `S42-FRA-LECTURE` (amendement du 2026-09-24, verdict negatif de la
mesure de pli d'`ines-design`).

**Ce que ce planning ne rouvre pas** : `DEC-0089` (CE1 mathematiques seule, CM2 differe) ;
`DEC-0075` (neuf lignes bloquees) ; les tarifs et exclusions de `DEC-0097` ; le retrait de
`S42-FRA-LECTURE` deja tranche et applique par `DEC-0098` section 9 et par
`docs/backlog/backlog.md`. Les cinq lots retenus sont deja verifies `non couvert` dans
`MATRICE-couverture.md` (`DEC-0098`, section 5, non rouverte par l'amendement) et resolus ligne
par ligne, citation comprise, par l'avis de faisabilite de `theo-archi` (section 2).

## 3. Stories deja instruites, avis de faisabilite deja rendu

Les cinq stories sont deja ecrites avec criteres d'acceptation testables dans
`docs/backlog/backlog.md`, section "Sprint 42" (`camille-po`, commits `982c4a83` et `517abd9d`).
L'avis de faisabilite de `theo-archi`
(`squad/sprints/s42-fusion-six-lignes-deux-instances/avis-faisabilite.md`, `9fed12bc`) porte sur
les cinq, sans qu'aucun `ADR` ni aucune regle ArchUnit neuve ne soit due :

| Lot | Ligne | Competence programme visee | Tarif | Verdict `theo-archi` |
|---|---|---|---|---|
| `S42-HG-ROIS` | `HG-CM1-05` | Histoire-geographie, CM1 - nommer et situer dans le temps quelques rois qui ont marque la construction du royaume | 1,5 | **Faisable sous condition** : le paquet nomme par la story (`cm1-histoire-geographie.yaml`) n'existe plus, scinde en quatre a s35 - condition levee section 5 ci-dessous |
| `S42-QLM-MATIERE` | `QLM-CP-06` | Questionner le monde, CP - identifier de quelle matiere un objet est fait, trier des materiaux selon une propriete simple | 1,5 | Faisable en l'etat ; adjacence de contenu avec `cm1-sciences-familles-materiaux` (deja servi), partiellement discriminee par `LON-1`/`LON-2` |
| `S42-ART-COULEURS` | `ART-CP-05` | Arts plastiques, CP - reconnaitre et nommer les couleurs, primaires et melanges | 1,5 | Faisable en l'etat, sous `ART-1` (deja rendue, non proposee par `theo-archi`) |
| `S42-EMC-SYMBOLES-CM1` | `EMC-CM1-05` | EMC, CM1 - reconnaitre les symboles de la Republique et dire ce qu'ils representent | 1,5 | **Faisable sous condition** : les cinq symboles du libelle sont deja servis au CP (`cp-emc-symboles` s41, `cp-emc-devise` s40), aucun garde-fou ne compare deux paquets - condition levee section 6 ci-dessous |
| `S42-MAT-TEXTUEL` | `MAT-CP-10` (moitie textuelle) | Mathematiques, CP - decrire un deplacement sur un quadrillage, en choix textuel | 1,0 | Faisable en l'etat sur le mecanisme ; contrainte reelle du lot : `LON-1`/`LON-2` (15 mots, 2 phrases), scene a poser en mots sans image |

Porte technique mesuree par `theo-archi` sur l'arbre de `517abd9d` :
`bash outils/mvn-jdk21.sh -B clean verify` vert, cinq modules et le reacteur complet - ce vert ne
vaut que pour le job `build` de la CI, les trois autres jobs (`prefixes`, `chemins`, `outils`) ne
sont pas mesures par cette commande.

## 4. Deux terrains, deux instances

| Terrain | Instance | Lots | Cout |
|---|---|---|---|
| 1 - histoire-geographie CM1, questionner le monde CP | A | `S42-HG-ROIS`, `S42-QLM-MATIERE` | 3,0 / 5,0 |
| 2 - arts plastiques CP, EMC CM1, mathematiques CP | B | `S42-ART-COULEURS`, `S42-EMC-SYMBOLES-CM1`, `S42-MAT-TEXTUEL` | 4,0 / 5,0 |

`PAR-2` ne contraint aucune composition possible : les cinq lignes portent chacune une matiere et
un niveau distincts des autres (`DEC-0098`, section 4.1). `CYC-1` ne trouve aucune occurrence a
instruire dans ce lot (section 4.2). Aucune clause de frontiere n'est due entre les deux terrains,
verifie contre le seul critere que `nadia-prof` a ecrit pour en produire une, applique aux dix
paires possibles des cinq lignes (section 4.3). Marge individuelle : 2,0 sur le terrain 1, 1,0 sur
le terrain 2 ; marge de 1,0 non allouee au niveau du sprint (7,0 / 8,0).

## 5. Condition levee sur `S42-HG-ROIS` - paquets reels a etendre

**Le fichier que la story nomme (`cm1-histoire-geographie.yaml`) n'existe plus** : il a ete scinde
en quatre a s35 (`S35-CONS-4`, decision de grain `ADR-0081`). `HG-CM1-05` est conjonctive -
*"nommer et situer dans le temps"* - et ses deux verbes tombent dans deux des quatre paquets
actuels :

- `cm1-histoire-geographie-nommer-en-histoire` (verbe *nommer*) ;
- `cm1-histoire-geographie-situer-dans-le-temps` (verbe *situer dans le temps*).

Cette configuration (une ligne servie par deux entrees de catalogue) existe deja et est verte sur
ce meme lot de matiere : `HG-CM1-07` est declaree par ces deux memes paquets (avis de
faisabilite, section 5, point 1). Aucun `ADR` d'allocation de sous-partie n'est du : le mecanisme
de `ADR-0098` (`SousPartieDeMathematiquesCe1`) est borne aux mathematiques de CE1 et ne se leve
pas ici.

**Condition transmise au developpeur affecte, a tenir avant d'ecrire** : etendre les deux paquets
cites ci-dessus, chacun sur son verbe, plutot que le fichier disparu que la story designe. Chaque
item neuf porte le marqueur `# HIS-1: <type>` (`ADR-0079`), le lot histoire-geographie CM1 etant
deja soumis a cette condition (110 marqueurs sur 110 items mesures par `theo-archi`). Laquelle des
deux voies est retenue en detail (etendre les deux paquets pour couvrir integralement le libelle,
ou un seul si `camille-po`/`nadia-prof` limitent la cible) reste une decision de grain de contenu,
non tranchee par ce planning - le developpeur la souleve a `camille-po` avant d'ecrire si le
critere d'acceptation de la story laisse un doute.

## 6. Condition levee sur `S42-EMC-SYMBOLES-CM1` - alerte transmise a l'instance B de `nadia-prof`

**Fait transmis explicitement, non tranche ici** (avis de faisabilite, section 8.1) : les cinq
symboles du libelle CM1 (drapeau, devise, Marianne, hymne, fete nationale) sont deja tous servis
au CP, par deux paquets livres aux deux sprints precedents - `cp-emc-symboles` (`EMC-CP-08`,
s41, quatre symboles) et `cp-emc-devise` (`EMC-CP-09`, s40, le cinquieme). Les deux verbes de la
ligne CM1 (*reconnaitre*, *dire ce qu'ils representent*) sont eux aussi deja servis au CP, a la
formulation pres. **Aucun garde-fou du depot ne compare deux paquets differents** : `NIVEAU_BORNE`
ne borne que le CP, rien ne force le paquet CM1 a depasser en exigence son equivalent CP deja
livre.

**Une circonstance aggravante nommee par `theo-archi`** : l'instance qui auditera le paquet CM1
(instance B) n'est pas celle qui a audite le paquet CP le sprint precedent (`S41-EMC-SYMBOLES`
avait ete audite par l'instance A de s41), et aucune des deux instances n'a de moyen automatique
de voir le contenu de l'autre.

**Condition de verdict, transmise telle quelle a `nadia-prof`, instance B, avant l'ecriture du
paquet** : lire `cp-emc-symboles` et `cp-emc-devise` avant que `S42-EMC-SYMBOLES-CM1` ne soit
ecrit, et poser elle-meme le discriminant qui fait que le paquet CM1 depasse le simple doublon du
CP. Ce planning ne dit pas ce que ce discriminant doit etre - c'est une question pedagogique, elle
appartient a `nadia-prof` seule.

## 7. Precision transmise a `camille-po` sur `S42-MAT-TEXTUEL` - non une action de ce planning

**Fait a router, non a agir ici** (avis de faisabilite, section 4) : contrairement a ce que la
story affirme, il n'existe **aucune divergence** entre `MATRICE-couverture.md` (ligne 227) et
`ARBITRAGE-PREALABLE-s32` (section 17.7) sur la question de fond - les deux sources disent
identiquement que la moitie *decrire un deplacement* se sert en choix textuel et que la moitie
*se reperer et se deplacer* est une designation. La direction que la story retient est correcte.

**Ce qui est reellement en jeu** : la mention de couverture partielle pre-ecrite dans la cellule
227 suppose un ordre de livraison (designation d'abord, texte ensuite, `ARBITRAGE-PREALABLE-s32`
section 17.2) que s42 inverse (texte livre en premier). La mention due au Done pedagogique de
`S42-MAT-TEXTUEL` n'est donc pas celle que la story annonce comme une correction d'inversion : le
critere d'acceptation de la story porte deja le bon libelle miroir (*"moitie 'decrire un
deplacement' seule [...] la moitie 'se reperer et se deplacer' [...] reste non couverte"*), et
c'est ce libelle-la qui doit remplacer la mention actuelle de la cellule 227, au moment du Done -
pas avant. **`camille-po` en tient compte lors de la mise a jour de `MATRICE-couverture.md` en fin
de sprint** ; aucune action n'est due de ce planning.

## 8. Affectations nominatives finales

**Proposition de `camille-po`, verifiee et confirmee sans ajustement** (`docs/backlog/backlog.md`,
section "Sprint 42", introduction et "Agent pressenti" de chaque story) : le terrain 1 ne porte
plus que deux lots depuis le retrait de `S42-FRA-LECTURE`, ce qui libere un developpeur de la
necessite de franchir les deux terrains.

| Lot | Terrain / instance | Developpeur | Condition |
|---|---|---|---|
| `S42-HG-ROIS` (`HG-CM1-05`) | 1 / A | `marc-dev` | Paquets reels a etendre, section 5 |
| `S42-QLM-MATIERE` (`QLM-CP-06`) | 1 / A | `marc-dev` | - |
| `S42-ART-COULEURS` (`ART-CP-05`) | 2 / B | `sofia-sm` (casquette dev) | - |
| `S42-EMC-SYMBOLES-CM1` (`EMC-CM1-05`) | 2 / B | `sofia-sm` (casquette dev) | Alerte instance B, section 6 |
| `S42-MAT-TEXTUEL` (`MAT-CP-10`) | 2 / B | `lea-dev` | - |

`marc-dev` prend les deux lots du terrain 1 seul, `sofia-sm` (casquette dev) les deux lots les
moins chers du terrain 2, `lea-dev` le lot restant du terrain 2. Aucun developpeur ne franchit les
deux terrains, a la difference de `lea-dev` a s41.

**Point de vigilance retenu pour le sprint, non bloquant** (avis de faisabilite, section 9.1) :
quatre des cinq lots (tous sauf `S42-HG-ROIS`) ecrivent en fin de fichier dans les trois memes
fichiers partages -
`adapters/adapter-content/src/main/resources/catalogue.yaml`,
`.../LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`,
`bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java`
- des conflits de fusion certains, jamais silencieux, les garde-fous mordant dans les deux sens.
`S42-HG-ROIS` s'en distingue : il **modifie** une entree existante de
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` plutot que d'en ajouter une, seul point du sprint ou deux lots
ecrivent dans le meme fichier a des endroits de nature differente. Chaque developpeur fusionne son
lot des qu'il est vert plutot que d'accumuler les cinq en parallele sur une meme branche.

## 9. Deux instances paralleles de `nadia-prof` - Done pedagogique en fin de sprint

- **Instance A**, terrain 1 (histoire-geographie CM1, questionner le monde CP) :
  `S42-HG-ROIS`, `S42-QLM-MATIERE`. Budget opposable 5,0, cout retenu 3,0 - marge de 2,0.
- **Instance B**, terrain 2 (arts plastiques CP, EMC CM1, mathematiques CP) :
  `S42-ART-COULEURS`, `S42-EMC-SYMBOLES-CM1`, `S42-MAT-TEXTUEL`. Budget opposable 5,0, cout
  retenu 4,0 - marge de 1,0. **Instance B porte l'alerte de la section 6** avant d'auditer
  `S42-EMC-SYMBOLES-CM1`.

## 10. Verification `PAR-16` - point de controle poste/story

| Element | Affectation nominative | Issue |
|---|---|---|
| `S42-HG-ROIS` (`HG-CM1-05`) | `marc-dev`, puis `nadia-prof` instance A | Engagee sous condition (section 5) |
| `S42-QLM-MATIERE` (`QLM-CP-06`) | `marc-dev`, puis `nadia-prof` instance A | Engagee |
| `S42-ART-COULEURS` (`ART-CP-05`) | `sofia-sm` (casquette dev), puis `nadia-prof` instance B | Engagee |
| `S42-EMC-SYMBOLES-CM1` (`EMC-CM1-05`) | `sofia-sm` (casquette dev), puis `nadia-prof` instance B | Engagee sous condition (section 6) |
| `S42-MAT-TEXTUEL` (`MAT-CP-10`) | `lea-dev`, puis `nadia-prof` instance B | Engagee |

Aucun element n'est "non engage, budget libere" : les cinq lots du perimetre amende de `DEC-0098`
sont tous affectes.

## 11. Ce que ce planning ne fait pas

- Il n'engage aucun `ADR` ni aucune regle ArchUnit neuve : aucune des deux n'est due sur les cinq
  lots (avis de `theo-archi`, section 1).
- Il ne tranche pas laquelle des deux voies d'extension de `S42-HG-ROIS` est retenue en detail
  (section 5) : decision de grain de contenu, a `camille-po` et `nadia-prof`.
- Il ne pose aucun discriminant pour `S42-EMC-SYMBOLES-CM1` (section 6) : question pedagogique, a
  `nadia-prof` seule, instance B.
- Il ne corrige pas la mention de couverture partielle de la cellule 227 de `MATRICE-couverture.md`
  (section 7) : tenue de matrice, a `camille-po`, en fin de sprint.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.
- Il ne rouvre ni `DEC-0089`, ni `DEC-0075`, ni les tarifs et exclusions de `DEC-0097`, ni le
  retrait de `S42-FRA-LECTURE`.

## Decisions prises

Aucune decision de sprint n'est prise dans ce planning. `DEC-0098` (objectif et perimetre,
amendee) et l'avis de faisabilite de `theo-archi` existent deja avant ce planning ; ce document
confirme l'affectation nominative proposee par `camille-po` sans l'amender, transmet
explicitement les deux conditions et l'alerte pedagogique aux destinataires nommes, et ouvre le
sprint au developpement.

## Amendement du 2026-09-24 (`sofia-sm`, cloture) - horodatage de debut precise

La section d'en-tete ecrit *"Horodatage de debut : 2026-09-24"*, sans heure : suffisant pour lire
ce planning, insuffisant pour `outils/rapport-tokens.py`, qui exige une heure et des minutes sur
la meme ligne que le mot *"horodatage"* et n'ouvre aucune fenetre de sprint sans elle - constate a
la cloture, le script a ignore ce dossier avec le motif *"pas d'horodatage de debut dans
planning.md"*. Je ne remplace pas la ligne d'origine, je precise le seul fait qui lui manquait,
avec sa source verifiable : **Horodatage de debut : 2026-09-24 17:26:51 (UTC+02:00), horodatage du
commit `edbe8582`** (ce planning lui-meme), premier commit de ce dossier de sprint portant un tag
d'agent de developpement.
