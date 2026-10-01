# Rapport qualite de cloture - s45

**Auteur** : `yanis-qa`. **Date** : 2026-10-01. **Arbre mesure** : `s45-yanis` a `12fd0ae4`
(hash complet `12fd0ae45163f280a3c9a306d6057ace8e8a1dd0`, strictement egal a `round-two` au moment
de la mesure), worktree dedie (`git worktree add ../recreo-s45-yanis -b s45-yanis round-two`),
lignee verifiee (`bash outils/verifier-lignee.sh round-two`, lance depuis le worktree lui-meme ->
*"le worktree sur la branche 's45-yanis' descend de la lignee de round-two (ancetre commun trouve
dans son historique)"*).

Contenu du sprint : quatre lots de contenu statique, dispositif a deux instances paralleles de
`nadia-prof` (`DEC-0101`) - `S45-MAT-ADDITION` (`MAT-CE1-04`, `[MARC]`, `607eb567`/`fdef689b`),
`S45-MUS-INSTRUMENTS` (`MUS-CP-05`, `[SOF]`, `0d7fb883`/`b070d82b`), `S45-SCI-NUMERIQUE`
(`SCI-CM1-14`, `[SOF]`, `c5b96350`/`16b4d799`), `S45-EPS-ROLES` (`EPS-CM1-09`, `[LEA]`,
`3b61f781`/`12fd0ae4`). Un cinquieme lot, `S45-LVE-ECRITURE` (`LVE-CM1-06`), a ete retire du
perimetre avant ecriture (motif `R6`, `5b8ca0f8`/`5ef0ac7f`) et n'a jamais ete code. Un `ADR`
(`ADR-0103`, `theo-archi`, fusion `7c2b091f`, avant le developpement) restreint le perimetre du
garde-fou `ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` aux paquets qui declarent
`MAT-CE1-03`, condition de faisabilite de `S45-MAT-ADDITION`.

Trois des quatre fusions de lots portent un conflit textuel reel, resolu par les developpeurs par
concatenation en fin de liste sur les fichiers d'inventaire transverses, jamais sur leur contenu :
`fdef689b` (`catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest`),
`16b4d799` (les trois fichiers d'inventaire), `12fd0ae4` (`catalogue.yaml`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`TexteAccentueExposeParLePortEntrantTest`). Seule la fusion de `S45-MUS-INSTRUMENTS` (`b070d82b`,
premier lot fusionne) est sans conflit. Aucune marque de conflit residuelle
(`<<<<<<<`/`=======`/`>>>>>>>`) sur l'arbre final.

`bash outils/mvn-jdk21.sh -B clean verify`, lance une seule fois sur ce worktree, a rendu
`BUILD SUCCESS` du premier coup, en 4 min 57 s.

---

## 1. Compte de tests autoritaire

**1850 tests : 1844 unitaires + 6 tests d'integration**, sur `s45-yanis` a `12fd0ae4`. Methode :
somme des six blocs `[INFO] Results:` du lancement de `bash outils/mvn-jdk21.sh -B clean verify`
cite plus haut. JDK constate en interrogeant le JDK resolu par `outils/mvn-jdk21.sh` (jamais la
toolchain declaree) : **21.0.10**, coherent avec les lignes de demarrage Spring Boot du meme
lancement (`using Java 21.0.10`). `BUILD SUCCESS`, 6 modules sur 6, 0 echec, 0 erreur.

**Resultats par module** :

| Module | Tests run |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 400 |
| recreo-adapter-content | 1019 |
| recreo-bootstrap (surefire, unitaires) | 288 |
| recreo-bootstrap (failsafe, integration) | 6 |
| **Total unitaires** | **1844** |
| **Total integration** | **6** |

`domain`, `application`, `adapter-web` et `bootstrap` (unitaires et integration) sont **inchanges**
depuis la cloture de s44 (120, 17, 400, 288, 6, remesures directement a `00c9139b` pour cette
cloture, pas recopies du rapport de s44) - coherent avec les quatre lots de contenu statique, zero
ligne de Java de production (`git diff --stat 00c9139b..12fd0ae4 -- '*.java'` ne touche que des
fichiers de test). Seul `adapter-content` bouge.

**Delta sur `adapter-content` et sa methode, decompose en deux temps.**

Base de sprint mesuree directement, en reconstruisant un second worktree sur le dernier commit de
s44 (`00c9139b`) : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am clean test`,
**module mesure** : `adapters/adapter-content` et ses deux dependances amont, pas le depot entier :
**974**. Ce chiffre est exactement celui du rapport de s44, confirme par une mesure independante et
non par simple lecture du rapport precedent.

Base de pre-developpement s45 mesuree sur le dernier commit avant le premier lot code (`5ef0ac7f`,
fusion du retrait de `S45-LVE-ECRITURE`) : meme commande, meme module mesure : **977**, soit **+3**
sur la base de s44. Ce delta correspond exactement aux trois mises a l'epreuve neuves d'`ADR-0103`
(`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest` passe de 10 a 13 methodes `@Test`
entre `00c9139b` et `5ef0ac7f`), fusionnees avant le developpement des quatre lots et donc avant
ce sprint au sens du developpement, mais apres sa cloture precedente au sens du depot.

`adapter-content` passe ensuite de **977** (pre-developpement) a **1019** (cloture), soit **+42**
- exactement la somme des `@Test` des quatre fichiers neufs, comptee directement dans chaque
fichier : `PaquetCe1AdditionsPoseesTest` (12), `PaquetCm1EpsRolesTest` (10),
`PaquetCm1SciencesUsagesNumeriquesTest` (10), `PaquetCpMusiqueInstrumentsTest` (10). Les trois
fichiers transverses modifies par les fusions (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `TexteAccentueExposeParLePortEntrantTest`)
n'ajoutent aucun `@Test` : leurs diffs n'ajoutent que des entrees de donnees (une valeur d'enum, des
entrees de `Map`) consommees par des methodes de test deja existantes, aucune des trois classes
n'etant parametree sur ces donnees.

**Total du sprint** : 974 (s44) + 3 (`ADR-0103`) + 42 (quatre paquets) = **1019**, et le total
depot (1805 -> 1850) rend bien **+45** sur `adapter-content` seul (974 -> 1019), sans mouvement
dans aucun autre module.

**Couverture par module** (fichiers `*Test.java`/`*IT.java` presents, aucun module vide, base de
s44 remesuree directement a `00c9139b`, pas recopiee) :

| Module | Fichiers de test (s44 -> s45) |
|---|---|
| domain | 11 -> 11 |
| application | 2 -> 2 |
| adapter-web | 49 -> 49 |
| adapter-content | 131 -> 135 |
| bootstrap | 51 -> 51 |

`adapter-content` passe de 131 a 135, soit exactement **+4 fichiers**, un par paquet neuf -
`ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest`, le fichier d'`ADR-0103`, existait deja
a `00c9139b` au sens du nom de fichier (regle existante restreinte, pas un fichier neuf) et n'entre
donc pas dans ce delta. Aucun paquet neuf n'est reste sans test a son premier commit.

**ArchUnit** : **38** `static final ArchRule` et **35** `@ArchTest`, comptes par occurrences
litterales sous `src/test/java` des cinq modules, `target/` exclu. **Ecart avec le rapport de s44**
(qui citait 36/35) : remesure directement a `00c9139b` avec la meme methode, le chiffre y est deja
**38**, identique a la cloture de s45. **Aucune regle ArchUnit neuve n'est due a s45** - confirme
par `git diff 00c9139b..12fd0ae4` sur les fichiers de test, qui ne touche aucune ligne contenant
`static final ArchRule` - et le chiffre de 36 cite au rapport de s44 ne resiste pas a une remesure
sur son propre commit. Je ne corrige pas le rapport de s44 ici ; je signale l'ecart pour que la
serie ArchUnit des prochains rapports reparte du chiffre verifie (38), pas du chiffre recopie.
Aucune classe ArchUnit en echec dans le lancement complet.

## 2. Les quatre jobs de `.github/workflows/ci.yml`, nommes un par un

| Job | Commande executee | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` (equivalent du `mvn -B verify` du job, sur le JDK que la toolchain resout) | **Vert** - `BUILD SUCCESS`, 0 echec, 4 min 57 s |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` (19 cas) puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **Vert** - tous les cas passent, aucun chemin de poste ni nom de compte trouve sur l'ensemble des fichiers suivis |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` (15 cas), `bash .github/scripts/commit-atteignable-test.sh` (5 cas), puis `git log --no-merges --format=%s 00c9139b..12fd0ae4 \| bash .github/scripts/verifier-prefixes.sh` | **Vert** - tous les cas passent ; les 17 commits sans fusion du perimetre de s45 portent tous un prefixe d'agent connu (`[THEO]`, `[CAM]`, `[NAD]`, `[SOF]`, `[MARC]`, `[LEA]`) |
| `outils` | balayage des trois motifs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **Vert** - 9 juges purs executes, aucun motif vide, 0 echec |

Aucun des quatre n'est deduit des autres : chacun a ete lance separement dans le worktree, et
`git status --short` est reste vide apres chacun.

## 3. Resolution des quatre fusions successives sur les memes fichiers transverses

Risque concret, deja rencontre et resolu a s44 : une fusion a plusieurs lots sur les memes fichiers
d'inventaire peut perdre une entree, en dupliquer une, ou faire ecraser l'une par l'autre. Verifie
sur les trois fichiers transverses touches par trois des quatre fusions.

**`catalogue.yaml`** : **110 entrees** (`- id:`), contre 106 a la cloture de s44 - exactement
**+4**. **Aucun identifiant duplique** (`grep -E "^\s*- id:" ... | sort | uniq -d` rend vide),
**aucun paquet duplique** (meme controle sur `paquet:`). Les quatre entrees du sprint sont toutes
presentes, distinctes et correctement formees : `paquet: ce1-additions-posees`,
`paquet: cp-musique-instruments`, `paquet: cm1-sciences-usages-numeriques`,
`paquet: cm1-eps-roles`. Confirme independamment par les tests d'integration de chargement du
catalogue, verts dans le lancement complet.

**`TexteAccentueExposeParLePortEntrantTest.java`** : les quatre identifiants de jeu neufs
(`bandit-manchot-additions-posees-ce1`, `bandit-manchot-instruments-musicaux-cp`,
`bandit-manchot-usages-numeriques-cm1`, `bandit-manchot-roles-eps-cm1`) sont presents, chacun avec
son commentaire de tracabilite (`S45-MAT-ADDITION`, `S45-MUS-INSTRUMENTS`, `S45-SCI-NUMERIQUE`,
`S45-EPS-ROLES`), de meme pour les quatre titres, les quatre consignes et les quatre libelles de
competence neufs. Diff lu en entier (`git diff 5ef0ac7f..12fd0ae4` sur ce fichier) : chaque bloc
modifie est une insertion pure, aucune ligne preexistante n'est retiree ni remplacee.

**`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java`** (`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`,
une liste d'exemption, pas un inventaire complet) : `cp-musique-instruments`,
`cm1-sciences-usages-numeriques` et `cm1-eps-roles` y figurent, chacun avec son motif chiffre et sa
date de mesure (2026-10-01) - les trois paquets textuels, comme prevu par le planning (section 5,
"pieges communs"). `ce1-additions-posees` en est **absent, et c'est correct** : c'est le seul des
quatre paquets dont les propositions sont numeriques, il reste donc soumis au garde-fou de rang
pour de vrai - verifie par le vert du lancement complet sur ce garde-fou, pas seulement par
l'absence attendue dans la liste.

Aucune entree perdue, aucun doublon, aucune entree ecrasee sur les trois fusions conflictuelles.

## 4. `ADR-0103` mis a l'epreuve sur le reacteur final, dans les deux sens

Le texte de l'`ADR` documente sa propre mise a l'epreuve par mutation, mais sur son arbre a lui
(`c6dcba81`), avant que le paquet reel de `MAT-CE1-04` existe. Je la rejoue ici sur le materiau
servi aujourd'hui, pas seulement sur la mesure de `theo-archi`.

**Sens 1 (paquet qui declare `MAT-CE1-04` reste hors de la population)** : deja couvert sur
materiau reel par `lePaquetServiRedeclareMatCe104SortDeLaPopulation`, qui mute
`ce1-additions-jusqu-a-100` (`MAT-CE1-03`) vers `MAT-CE1-04` et verifie que la population devient
vide. Cette assertion est verte dans le lancement complet cite en section 2.

**Sens 2 (un paquet qui declarerait `MAT-CE1-03` avec les memes items y resterait et y
echouerait)** : non couvert par un test ecrit sur le paquet reel - `ADR-0103` ne le prouve que sur
une fixture. Je l'ai mis a l'epreuve directement : `lignesDeReferentiel` du paquet servi
`ce1-additions-posees.yaml` mute de `["MAT-CE1-04"]` vers `["MAT-CE1-03"]`, puis
`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=ChaquePaquetDAdditionsDeCe1TientSaRepartitionParFormeTest -Dsurefire.failIfNoSpecifiedTests=false`.
**Rouge attendu, rouge obtenu** : `chaquePaquetDAdditionsDeCe1ServiTientLesHuitBandes` echoue, le
paquet mute entre dans la population et rate sept des huit bandes (`avec retenue` a 93,33 % contre
une bande 10-20 %, entre autres). Mutation annulee, meme classe relancee : **13 verts sur 13**,
retour confirme a l'etat du depot (`git status --short` vide apres restauration). `ADR-0103` tient
dans les deux sens sur le reacteur final, pas seulement a la date ou il a ete ecrit.

## 5. Tracabilite - criteres d'acceptation encodes, lot par lot

Chaque story de `docs/backlog/backlog.md`, section "Sprint 45", est verifiee methode par methode :
le fichier de test du paquet porte-t-il une methode nommee sur le critere, et l'assertionne-t-elle
reellement.

- **`S45-MAT-ADDITION`** (`PaquetCe1AdditionsPoseesTest`, 12 methodes) : `ADP-CE1-1` a `ADP-CE1-6`
  toutes citees en nom de methode ou en commentaire d'assertion (`laConsigneDeCatalogueDemandeDePoser`
  pour `ADP-CE1-1`, `lesTaillesEtLesRetenuesTiennentLesSeuilsDAdpCe12` pour `ADP-CE1-2`,
  `auMoinsLesDeuxTiersDesItemsOntUnOperandeDeTroisChiffresOuUneSommeSuperieureA100` pour
  `ADP-CE1-3`, `lesTroisPropositionsOntLeMemeNombreDeChiffresEtUnDistracteurPartageLeChiffreDesUnites`
  pour `ADP-CE1-4`, `chaqueDistracteurEstUneErreurNommeeDeclareeEtCalculeeDepuisLesOperandes` pour
  `ADP-CE1-5`, et le decompte des nombres nus sans habillage pour `ADP-CE1-6`). La ligne de
  referentiel exclusive (`cePaquetDeclareMatCe104EtUniquementElle`) et le jeu fautif commite
  (`leJeuFautifCommiteEstRefuseSurSonMarqueurErrone`) sont egalement testes.
- **`S45-MUS-INSTRUMENTS`** (`PaquetCpMusiqueInstrumentsTest`, 10 methodes) : `MUS-1`, `MUS-2`,
  `FER-1`, `FER-3` toutes citees ; `musCpR1AucunEnonceNeContientLeTexteDUneDeSesPropresPropositions`
  reprend nommement la reserve `MUS-CP-R1` ouverte a s44 sur le paquet precedent, verifiee ici sur
  le paquet neuf avant meme un audit.
- **`S45-EPS-ROLES`** (`PaquetCm1EpsRolesTest`, 10 methodes) : les quatre clauses du discriminant
  `EPS-10` toutes citees (`eps10Clause1...`, `cyc1...` dans les deux sens, `eps10Clause2...`),
  `EPS-1`, `EPS-3`, et la declaration exclusive de `EPS-CM1-09`.
- **`S45-SCI-NUMERIQUE`** (`PaquetCm1SciencesUsagesNumeriquesTest`, 10 methodes) : `SCI-1` est bien
  teste sur l'**indice** (`sci1ChaqueIndiceCompteUnePhraseEtNeNommeNiLaBonneReponseNiTaReponse`) et
  non sur l'explication - la correction de `camille-po` de la story (Q7) est bien celle qui est
  testee, pas l'erreur de s44. `SCI-2` a `SCI-4`, `FER-1` a `FER-3`, et `HIS-3` cite nommement
  **et** verifie l'absence du marqueur `HIS-1` dans la meme methode
  (`his3ChaqueLigneDItemPorteUnMarqueurHis3NonVideEtJamaisHis1`) - le piege nomme par le planning
  (section 5) est donc couvert par construction, pas par omission.

Aucun critere d'acceptation des quatre stories n'est reste sans test.

## 6. Matrice de couverture - etat constate, non modifie

Les quatre lignes du perimetre de `DEC-0101` restent `non couvert`, comme attendu avant le Done
pedagogique des deux instances de `nadia-prof` :

| Ligne | Fichier, position | Etat constate |
|---|---|---|
| `MAT-CE1-04` | `squad/programme/MATRICE-couverture.md:701` | `non couvert` |
| `MUS-CP-05` | `squad/programme/MATRICE-couverture.md:1863` | `non couvert` |
| `EPS-CM1-09` | `squad/programme/MATRICE-couverture.md:1972` | `non couvert` |
| `SCI-CM1-14` | `squad/programme/MATRICE-couverture.md:2154` | `non couvert` |

Aucune des quatre lignes n'a ete modifiee par ce rapport.

## 7. `S45-LVE-ECRITURE` - verification negative

`docs/backlog/backlog.md` marque la story **"RETIRE le 2026-10-01"** en tete de section (motif
`R6`), texte conserve pour reemploi futur. Verification independante du texte : aucune trace de
`LVE-CM1-06` ni de `lve-ecriture` dans le code Java ou les ressources YAML de `domain`,
`application`, `adapters`, `bootstrap` ; aucun fichier de contenu nomme sur ce motif ; aucune entree
`lve`/`langues-vivantes` dans `catalogue.yaml`. Le retrait est entier, rien n'a ete code.

## 8. Tests instables

Aucun test instable observe. Le lancement complet cite dans ce rapport n'a ete execute qu'une fois ;
un test lance une seule fois n'est pas qualifie de stable pour autant, et ce rapport ne l'affirme
pas.

## 9. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique - les deux instances de `nadia-prof`, seules, le font, sur
les quatre lots. Il ne juge le niveau scolaire d'aucun des quatre lots. Il n'etablit aucun total
sur l'arbre fusionne `round-two` au-dela de ce qui est deja fusionne dans `12fd0ae4`. Il ne modifie
aucun contenu ni aucun code de production : la mutation de la section 4 a ete ecrite, mesuree puis
integralement annulee avant la cloture (`git status --short` vide).

---

**Conclusion** : les quatre jobs de la CI sont verts, mesures individuellement sur ce worktree. Les
trois fusions conflictuelles (`fdef689b`, `16b4d799`, `12fd0ae4`) sont resolues sans perte, doublon
ni ecrasement sur les fichiers transverses qu'elles touchaient. `ADR-0103` tient dans les deux sens,
verifie par mutation sur le paquet servi, pas seulement cite. Le compte de tests (1850, +45 depuis
s44) est explique test par test, decompose entre `ADR-0103` (+3) et les quatre paquets neufs (+42).
**La porte technique de s45 est verte.**
