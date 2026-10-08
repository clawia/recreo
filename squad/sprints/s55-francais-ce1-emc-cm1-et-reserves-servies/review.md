# Review - s55 - quatre lignes fermees (141/182), deux jeux servis corriges et rejoues, le dispositif d'`ART-CP-10` livre sans qu'un enfant le voie

- **Horodatage de debut** (`planning.md`) : 2026-10-08 14:43 (UTC+02:00), lu par `date` a l'ecriture du planning.
- **Horodatage de fin** : 2026-10-08 22:21 (UTC+02:00), lu par `date` a la fin de l'ecriture de ce fichier.
- **Duree effective** : 7 h 38 min d'horloge entre les deux heures ci-dessus. Pas de timebox : premier commit 14:49, dernier commit de livraison 21:49, aucune nuit entre les deux.
- **Casquette** : `sofia-sm`, Scrum Master. Aucune story de developpement portee dans cette tache. A7 et A8 ont ete portees sous la casquette developpeuse dans une tache separee (`8541cffc`, `69d9d6a2`, `rapport-sofia-outils.md`).
  Les deux commits d'amendement et de correction de cette tache (`de1aa216`, `b20655fa`) sont du Scrum Master : une phrase de `CLAUDE.md` et le planning, pas du code.
- **Arbre revu** : branche `s55-sofia-cloture`, base `445647a7` (`git log -1 --format=%h`), `git status --short` vide avant ecriture, `bash outils/verifier-lignee.sh` sort 0 (*"descend de la lignee de round-two"*).
  `origin/round-two` est `fa4ddc7e` (reference locale, aucun `fetch` lance) : `git rev-list --count origin/round-two..HEAD` rend 49 a `445647a7`, avant les commits de cette tache. Le reflog de cette reference
  date cinq poussees pendant le sprint (14:28, 15:55, 16:21, 18:58, 19:09) ; la derniere porte `fa4ddc7e`, qui contient `a3606b2f`.
- **Provenance de cette review.** Elle est ecrite par une tache de cloture qui n'a pas assiste au sprint. Chaque ligne cite l'ecrit dont elle vient (fiche, rapport, `DEC`, backlog).
  Ce que je n'ai pas pu citer n'est pas ecrit. Je n'ai relance ni les audits de `nadia-prof`, ni les mutations de `yanis-qa`.
- **Lignee des fusions de s55.** Relue par moi a la cloture, sur les 32 fusions de `fbe07d79..445647a7` (`git merge-base` de chaque couple de parents, `git rev-list --count` de l'apport) :
  chaque branche fusionnee a pour base de fusion le sommet de `round-two` ou un de ses ancetres directs, apporte 1 a 4 commits, et aucun apport ne contient une fusion. Aucune branche n'a ete provisionnee
  sur une autre base. Je n'ai pas fusionne : cette verification est faite apres coup, elle ne remplace pas celle qui precede chaque fusion.

---

## 1. L'increment livre contre l'objectif gele

Objectif gele (`planning.md`) : brancher quatre paquets (deux de francais CE1, deux d'EMC CM1) et les presenter a `nadia-prof`, solder les reserves non bloquantes de deux jeux deja servis
(`EXC-R15`, `S54-ART-RES-R1` a `R3`). Au mieux 137/182 a 141/182. Complete a l'ouverture (amendement de 16:20, `DEC-0115` section 5) : le dispositif d'`ART-CP-10` (T2, T3), sans paquet.

| Objectif | Etat a la cloture | Source |
|---|---|---|
| Quatre paquets branches et presentes a `nadia-prof` | **Tenu** : `grep -c "paquet: <nom>$" catalogue.yaml` rend 1 pour `ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `cm1-emc-respect-differences`, `cm1-emc-principes-republique` (0 a l'ouverture) ; quatre verdicts ecrits | backlog "Cloture de s55" ; `rapport-qualite.md` section 4 ; fiches |
| Quatre lignes fermees, 141/182 | **Tenu**, sur quatre verdicts `VALIDE SOUS RESERVE` : `FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06` | section 9 ; matrice |
| Solder `EXC-R15` et `S54-ART-RES-R1` a `R3` | **Tenu** : deux rejeux ecrits. `ART-RES` `VALIDE` ; `EXC-R15` levee, `EXC-R16` neuve | `cloture-nadia.md` section 1 |
| Dispositif d'`ART-CP-10` (T2, T3), sans paquet | **Livre, non joue** : `dfd2f107` (T2), `363793e3` (T3) ; aucun paquet a reproductions n'est au catalogue, **l'enfant ne voit rien** ; `ART-CP-10` reste `non couvert` | `rapport-lea-dev-mecanique.md` ; `cloture-nadia.md` section 3 |

---

## 2. Les stories, une par une

Colonnes : **Livre** = commit qui livre ; **Verdict** = prononce par `nadia-prof` dans la fiche ou le bilan cites (je ne prononce aucun Done) ; **Reserve** = ce qui reste ouvert apres le verdict ;
**Report** = nombre de reports avant s55 selon `DEC-0115` section 4 et A.15.

| Story | Livre | Verdict de `nadia-prof` (source) | Reserve | Report |
|---|---|---|---|---|
| `S54-ART-RES-R1` a `R3` (`ART-CP-10`) | `f53f5c47` (`lea-dev`), 3 fichiers, 6 lignes | **VALIDE**, rejoue (30 parties), trois reserves levees (`FICHE-bandit-manchot-familles-d-oeuvres-cp.md`) ; **aucun mouvement de ligne** | aucune | aucun (ouverte a s54) |
| `S54-EXC-CATEGORIE` (`EXC-R15`, `EMC-CP-03`) | `d2b317a8` (`marc-dev`), 4 fichiers | **VALIDE SOUS RESERVE**, rejoue (30 parties) : `EXC-R15` levee, **`EXC-R16` neuve** (`FICHE-bandit-manchot-exclusion-cp.md`) ; `EMC-CP-03` reste `couvert-valide` | `EXC-R16` : texte servi par `e6262ac2`, **non rejouee**, 0,25 | aucun |
| `S55-BR-FRA-CE1-L1` (`ce1-accord-sujet-verbe`, `FRA-CE1-14`) | `c9c80937` (`marc-dev`), 6 fichiers ; `2b66a441` pour `ACV-CE1-R1` | **VALIDE SOUS RESERVE**, joue (40 parties) (`FICHE-bandit-manchot-accord-sujet-verbe-ce1.md`) | `ACV-CE1-R1` (servie, non rejouee, 0,25) ; `ACV-CE1-R2` (non servie, 0,5) | aucun (neuf) |
| `S55-BR-FRA-CE1-L2` (`ce1-classes-de-mots`, `FRA-CE1-12`) | `2aa1e03a` (`marc-dev`), 6 fichiers ; `fa3b97c0` pour `E3` | **VALIDE SOUS RESERVE**, joue (40 parties) (`FICHE-bandit-manchot-classes-de-mots-ce1.md`) | `CLM-CE1-R1` : les 24 indices changent, **1,0** (`PAR-14`) | aucun (neuf) |
| `S55-BR-EMC-CM1-03` (`cm1-emc-respect-differences`, `EMC-CM1-03`) | `0839f9dc` (`lea-dev`), 5 fichiers | **VALIDE SOUS RESERVE**, joue (9 parties), mention d'EMC CM1 exigee (`FICHE-bandit-manchot-respect-differences-cm1.md`) ; l'ecart de l'item 4 (*"cette partie"*) est accepte (`audit-nadia-b-emc.md` P1) | `EMC-CM1-R8` : indices des items 10 et 11, 0,25 | aucun (neuf) |
| `S55-BR-EMC-CM1-06` (`cm1-emc-principes-republique`, `EMC-CM1-06`) | `4636aa28` (`lea-dev`), 5 fichiers | **VALIDE SOUS RESERVE**, joue (9 parties), mention d'EMC CM1 exigee (`FICHE-bandit-manchot-principes-republique-cm1.md`) | `EMC-CM1-R9` : indice de l'item 3, citation de 2018 incomplete dans l'en-tete, 0,25 | aucun (neuf) |
| `S55-ART-CP-10-MECANIQUE`, T2 (garde-fous 1 a 3) | `dfd2f107` (`lea-dev`), +15 tests | **aucun a prononcer** : aucune ligne de production, aucun ecran (`cloture-nadia.md` section 3, T1) | aucune | financee a s55, pas un report (A.15) |
| `S55-ART-CP-10-MECANIQUE`, T3 (rendu, garde-fou 4) | `363793e3` (`lea-dev`), +15 tests, 99 lignes de CSS | **aucun a prononcer** : la branche image ne s'ouvre que si toutes les propositions portent `oeuvre-`, et aucun paquet servi n'en porte (section 3, T2). Lecture du gabarit par `nadia-prof` : `M4`, `M5`, `M6`, `X1`, `X2`, `X7` **tenus** ; `E2` acceptable, **`E3` pas acceptable pour un paquet servi** (section 4) | huit questions Q1 a Q8 a trancher **avant le premier paquet a reproductions** ; **branche image jamais jouee** | pas un report : T3 est livree |
| `S54-PONCT-GN-INDICES` | `08cb05fd` (`lea-dev`), 2 fichiers | **aucun** : la ponctuation du texte lu par l'enfant est au `relecteur-pedagogique` (`cloture-nadia.md` section 3, T3) | la relecture de ponctuation de ces 12 indices n'est nommee par aucun ecrit (obstacle 15) | aucun |
| `S54-ASCII-APOSTROPHES-TESTS` | `51c81763` (`marc-dev`) ; cas fabrique `2224e91e` apres le rapport qualite | sans objet | mutation `Q-Y8` rejouee par `marc-dev` (4 echecs), pas par `yanis-qa` | aucun |
| A7 (`S55-OUTIL-JDK-JAVA-TOOL-OPTIONS`) | `8541cffc` (`sofia-sm`, casquette dev) | sans objet | la mutation que `yanis-qa` annoncait **n'est pas faite** (obstacle 12) | aucun |
| A8 (`S55-OUTIL-TOKENS-SANS-SPRINT`) | `69d9d6a2` (`sofia-sm`, casquette dev) | sans objet | aucune : mutations rejouees et rouges par `yanis-qa` (`rapport-qualite.md` section 3) | aucun |
| A6 (diff de `s54-nadia-c`) | `rapport-qualite.md` section 5 | sans objet | non verifie : les noms de worktree et de scratchpad de l'instance C | aucun |
| A9 (`relecture-cloture.md`) | `56803a36` et trois commits `[REL]` | sans objet | `R4` : citation de 2018 non appliquee, deja un rejeu (`EMC-CM1-R9`) | action de s54, livree |
| Mise en page d'`ART-CP-10` (`ines-design`) | `4893771b` : proposition, prototype, releve a dix largeurs | sans objet | porte `G-INES` satisfaite : `4893771b` est ancetre de `363793e3` (`git merge-base --is-ancestor`, relance par moi) ; les ecarts `E1` a `E7` de `lea-dev` sont juges ligne T3 | aucun |
| `S54-OBS-SCI-CONSIGNE` | non engagee | sans objet | criteres non ecrits | **premier report (s55)**, `DEC-0115` A.10 |
| Correctif du garde-fou de provenance | `a3606b2f` (`theo-archi`) | sans objet | **poste absent du planning et de `DEC-0115`** (obstacle 4) | sans objet |
| `S55-BR-FRA-CE1-L3`, `S55-ART-CP-10-PAQUET` (T4, T5) | non engages | sans objet | option ouverte, pas un report (`DEC-0115` A.15) | aucun |

Engagements : chaque story du tableau "Ce qui est retenu" du planning et chaque story engagee par un amendement de `DEC-0115` est livree dans le tableau ci-dessus, **sauf `S54-OBS-SCI-CONSIGNE`**
(premier report, motif de `DEC-0115` A.10). Aucun REFUSE n'est prononce dans s55. **Premier report de `S54-OBS-SCI-CONSIGNE` : un second report exige un acte ecrit dans la `DEC` d'ouverture de s56
qui cite ce premier report ; un troisieme est interdit** (`DEC-0112` section 6, `proposition-s56.md` section 5).

**Ce qui n'est pas livre ou pas joue, dit une fois.**

- **T3 : jamais jouee sur la branche image.** Aucun paquet a propositions-images n'est au catalogue. Le HTML a ete lu par jsoup, jamais a l'oeil (`rapport-lea-dev-mecanique.md` "Non verifie" ; `rapport-qualite.md` section 4).
  `nadia-prof` a rejoue en sonde, sur le jar de `af8b326e`, la **branche texte** que T3 retouche : sept jeux, 67 manches, 45 revelations, aucune regression vue, rendu lu en HTML (`cloture-nadia.md` section 3.2). Ce n'est ni un audit ni un Done.
- **T4 (trois reproductions) et le paquet a reproductions (T5)** : non engages. T4 voyage avec le fichier du paquet (`avis-faisabilite.md` section 4).
- **Six reserves ouvertes sur des lignes deja fermees**, 2,5 de rejeu (section 10) : aucune capacite n'existe a s55 pour les rejouer.

---

## 3. Arbitrages de l'utilisatrice, tels que relayes

Aucun n'a ses mots dans le depot. Ils sont consignes dans `DEC-0115` sections 0.1 et A.1 sous la formule `recu de MASTER, sans source dans le depot` (`DEC-0115` A.18, depuis le 2026-10-08) : ils fondent que le travail se fait,
**jamais pourquoi**.

| Enonce relaye | Lieu | Effet ecrit |
|---|---|---|
| CI verte sur `fbe07d79` | `DEC-0115` 0.1 point 1 | **contredit par la mesure locale de `theo-archi`** (`avis-faisabilite.md` section 0 : deux echecs sur `review.md` de s54, ligne `U4`) ; solde par `8f740977` ; la CI distante n'a jamais ete lue (obstacle 3) |
| `ok` (ordre des matieres) | `DEC-0115` 0.1 point 2 | l'ordre est repris sous un motif de forme 1 par rang (`DEC-0115` section 3) ; **`DEC-0114` passe a `Acceptee`** (A.27) |
| La mecanique d'`ART-CP-10` est financee a s55 | `DEC-0115` 0.1 point 3, section 5 | **execution consignee, pas decision fondee** (A.18) ; `DEC-0115` reste `Proposee` ; **question Q6 ouverte** |
| Toutes les arts plastiques CP a l'instance A (option (b)) | `DEC-0115` A.1 | fonde le choix de (b), pas sa raison ; la raison est de forme 1 (A.1) |

---

## 4. Done technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. **La CI distante n'a rendu aucun verdict que nous ayons lu** : `gh run list` rendait `HTTP 404` depuis ce poste
au planning, aucun agent n'a lu GitHub Actions, et cette tache ne l'a pas tente. Tout ce qui suit a tourne en local, sous Windows.

### 4.1 Ce que `yanis-qa` a mesure, et sur quel arbre

`rapport-qualite.md`, arbre **`af8b326e`** (fusion de `s55-lea-mecanique`), les quatre jobs lances :

| Job | Resultat |
|---|---|
| `prefixes` | vert (22 sujets hors fusions ; `origin/round-two` ancienne : la branche `BASE` du workflow n'est pas exercee) |
| `chemins` | vert |
| `outils` | vert, 10 juges, 10 codes 0 |
| `build` | vert, deux etapes : `bash outils/mvn-jdk21.sh -B clean verify` (3 min 28 s, `BUILD SUCCESS`) et `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` (`BUILD SUCCESS`) ; plus le cas du jar reproductible (`-Dproject.build.outputTimestamp=1980-01-01T00:00:02Z`, `BUILD SUCCESS`) |

**2303 tests**, 0 echec, 0 erreur, 0 ignore. Reserves ecrites par lui : CI distante non lue, fins de ligne (`autocrlf=true`, une seule configuration), tout ce qui tourne sous Linux, branche `BASE` de `prefixes`.
Mutations : `M2` d'`EXC-R15`, `A8` `test_6` (trois mutants), `P3`, `L1` et quatorze mutants du garde-fou 4 rougissent comme annonce ; **`Q-Y8` reste verte** (obstacle 6). Non rejouees : les morsures de T2,
le correctif `a3606b2f`, **la mutation d'A7**, les cliquets de `PONCT`.

### 4.2 Ce que `theo-archi` a confirme

`confirmation-archunit.md`, arbre `af8b326e` : 240 tests de `com.recreo.archi` verts ; **39 `static final ArchRule` et 36 `@ArchTest`, inchanges aux trois bases** (`fbe07d79`, `fa4ddc7e`, `af8b326e`) ; aucune regle affaiblie ;
aucun `ADR` appele. Jobs lances : `build` (deux etapes plus le cas reproductible), `chemins`, `prefixes` ; `outils` non lance.

### 4.3 Ce qui a change depuis `af8b326e`

`git diff --name-only af8b326e 445647a7` : 22 fichiers. **Quatre sont des classes de test modifiees par `2224e91e`** (`PaquetCm1EmcAtteinteAutruiTest`, `PaquetCm1EmcPrincipesRepubliqueTest`, `PaquetCm1EmcRespectDifferencesTest`,
`PaquetCm1SciencesActiviteHumaineTest`, +4 tests) ; **cinq sont des commentaires ou un texte d'aide** (`cm1-emc-respect-differences.yaml`, `cp-emc-exclusion.yaml`, `TexteAccentueExposeParLePortEntrantTest`, `outils/mvn-jdk21.sh`,
`outils/rapport-tokens.py`, commits `[REL]` `eb523da8` et `c5bd97cf`) ; les treize autres sont sous `docs/` ou `squad/`. Le rapport qualite ne couvre donc pas la fin du sprint, et **ce n'est plus seulement de la documentation**
(retro de s54, C3). Les quatre tests de `2224e91e` sont les seuls a changer un total. Les commentaires ont ete relus par le `relecteur-pedagogique`, qui a lance `bash outils/mvn-jdk21.sh -B clean verify` apres ses trois commits (2303 tests) ; `marc-dev` l'a lance apres `2224e91e`
(`rapport-marc-dev-qy8.md`).

### 4.4 Ce que j'ai lance moi-meme

Tous les chiffres de cette section sont de moi, sur l'arbre `445647a7` plus les fichiers de cloture, pas repris d'un rapport.

| Job | Commande | Resultat |
|---|---|---|
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` ; `bash .github/scripts/commit-atteignable.sh origin/round-two` ; les sujets de `git log --no-merges --format=%s origin/round-two..HEAD` en entree de `bash .github/scripts/verifier-prefixes.sh` | **vert**, codes 0, 34 sujets hors fusions (17 fusions). Ce n'est pas la plage `github.event.before..GITHUB_SHA` d'un push reel : la branche `BASE` du workflow n'est pas exercee. Le sujet du dernier commit de cloture est verifie apres son commit (compte rendu de la tache) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` ; `git ls-files -co --exclude-standard` hors `verifier-chemins-test.sh` en entree de `bash .github/scripts/verifier-chemins.sh` | **vert**, codes 0, fichiers de cloture compris (suivis et non suivis) |
| `outils` | boucle du workflow, trois motifs : `outils/*-test.sh` (5), `squad/outils/*/*-test.sh` (2), `outils/*-test.py` (3), avec `python` : le `python3` de ce poste est le faux binaire du Store, et le job prend `python3` s'il existe, sinon `python` | **vert, 10 juges, 10 codes 0**, aucun motif vide |
| `build`, etape `verify` | `bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, sans `-pl`, sur l'arbre qui porte `review.md`, `retro.md` et `RAPPORT.md` (hors l'heure de fin et le tableau de tokens, poses ensuite) | **`BUILD SUCCESS`**, 2 min 41 s, code 0, memes comptes qu'en 4.5 (120, 18, 407, 1423, 333 et 6) : `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` lit mes trois fichiers et passe |
| `build`, etape `package` | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, sans `clean`, apres le `verify`, comme le workflow | **`BUILD SUCCESS`**, 2 min 1 s, code 0, journal `Replacing main artifact ... with repackaged archive` |
| `build`, etape de detection du `pom.xml` | **non lancee** : sans objet en local, le `pom.xml` est present | - |

Ce que je n'ai **pas** lance : le workflow sous GitHub Actions ; la branche `BASE` du job `prefixes` ; le cas du jar reproductible (`-Dproject.build.outputTimestamp`), lance par `yanis-qa` et `theo-archi` sur `af8b326e`, **non relance par moi** : les fichiers modifies depuis ne sont ni de la production ni un POM (section 4.3).
Mes verifications de tableaux : un script de lecture seule, de meme regle que le garde-fou (une cellule en trop, une barre verticale dans un bloc de code compte), rend 0 ligne en surplus sur `planning.md`, `review.md`, `retro.md` et `RAPPORT.md` ;
le garde-fou lui-meme est vert dans le `verify` ci-dessus.
Le `clean verify` est **relance a l'identique apres la derniere ecriture** (l'heure de fin et le tableau de tokens) et avant le dernier commit ; son resultat est rendu dans le compte rendu de la tache, pas dans ce fichier.

### 4.5 Total des tests, etabli sur l'arbre fusionne : 2307

`bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, sans `-pl`, sur `445647a7` avant mes fichiers de cloture : `BUILD SUCCESS`, 3 min 16 s, JDK de la toolchain 21.0.10 ; la machine virtuelle des tests rapporte
`java.version` 21.0.10 dans les 289 rapports XML (une seule valeur).

| Module | Surefire | Failsafe | Echecs, erreurs, ignores |
|---|---|---|---|
| `recreo-domain` | 120 | - | 0 |
| `recreo-application` | 18 | - | 0 |
| `recreo-adapter-web` | 407 | - | 0 |
| `recreo-adapter-content` | 1423 | - | 0 |
| `recreo-bootstrap` | 333 | 6 | 0 |
| **Reacteur** | **2301** | **6** | **0** |

**Total : 2307.** Methode : lignes `Tests run:` de fin de module de la sortie Maven, recoupees par la somme des attributs `tests`, `failures`, `errors`, `skipped` des `TEST-*.xml` de `surefire-reports` et `failsafe-reports`
(2301 + 6, les deux concordent).

**Delta : +91 sur la base 2216** (cloture de s54, citee, non remesuree) : adapter-content +63 (1360 a 1423), bootstrap +21 (312 a 333), adapter-web +6 (401 a 407), application +1 (17 a 18), domain 0, Failsafe 0.
Origine : **+87 mesures par `yanis-qa` sur `af8b326e`** (2303, classe par classe dans `rapport-qualite.md` section 2 : 17 + 13 + 12 + 12 + 15 + 15 + 3), plus **+4** de `2224e91e` (`marc-dev`, un `@Test` par motif de quatre paquets ;
adapter-content 1419 a 1423, constate par moi). Les annonces des developpeurs ne s'additionnent pas a ce total : chacune mesure la base de son worktree plus son propre lot (`rapport-marc-dev.md` : +15 et +13, puis +2 en corrections ;
`rapport-lea-dev-emc.md` : +24 ; `rapport-lea-dev-mecanique.md` : +30 ; `rapport-marc-dev-qy8.md` : +4) ; le correctif de provenance ajoute trois tests, un par copie (`rapport-qualite.md` section 2). Aucune annonce n'a ete reprise sans etre recoupee.

---

## 5. Le correctif CI : `a3606b2f` (garde-fou de provenance)

**Ce qui est ecrit.** Le message du commit dit que le garde-fou comparait l'entree d'un jar fixee vers 1980 (build reproductible, `project.build.outputTimestamp` ou `SOURCE_DATE_EPOCH`) au `.java` source
et *"rougissait la CI sur un jar frais"* ; il compare desormais l'horodatage du fichier jar quand toutes les entrees portent le meme. Trois copies identiques (`application`, `adapter-web`, `adapter-content`), jeu fautif a trois cas dans chacune.

**Ce qui est mesure.** `theo-archi` : vert sans et avec la propriete ; la retraite du correctif rougit les trois copies avec le message attendu ; le repli `SOURCE_DATE_EPOCH` nomme par la Javadoc est reel (`confirmation-archunit.md` section 5).
`yanis-qa` : vert sur le cas reproductible (`1980-01-01T00:00:02Z`), +3 tests ; **il n'a pas mute le correctif** (`rapport-qualite.md` section 1).

**Ce qui n'est pas prouve.** *"La cause du rouge de CI du 2026-10-08 n'est ecrite dans aucun fichier de `squad/`, et je n'ai pas observe d'execution GitHub sur ce commit"* (`confirmation-archunit.md` section 5). Je ne l'ai pas observee non plus.
Aucun POM ne fixe `project.build.outputTimestamp` (`rapport-qualite.md` section 1) : seul un lancement qui passe la propriete exerce la branche corrigee, ce que `yanis-qa` et `theo-archi` ont fait chacun.
Je ne relie pas ce correctif a un job ou a un message de CI precis : aucun ecrit du depot ne le fait. `a3606b2f` est ancetre de `fa4ddc7e`, pousse le 2026-10-08 a 19:09 selon le reflog local : le verdict de la CI sur ce commit existe a GitHub et n'a pas ete lu.

**Statut** : correctif livre et mesure en local ; cause non prouvee ; poste sans ligne au planning ni a `DEC-0115` (obstacle 4). Route : `theo-archi` (ecrire la cause), utilisatrice (lire la CI sur `fa4ddc7e`).

---

## 6. A7 et A8 : livrees

Rapport : `rapport-sofia-outils.md` (casquette developpeuse). Elles ne sont pas relues par moi sous la casquette Scrum Master, seulement recoupees.

| Poste | Livre | Mesure ecrite | Recoupement |
|---|---|---|---|
| A7 : `JAVA_TOOL_OPTIONS` rendait la version `inconnue` | `8541cffc` : `head -n1` devient `grep -m1 'version "'` ; un cas neuf dans `outils/mvn-jdk21-test.sh` | `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` : code 1 avant, code 0 apres ; correction retiree, le cas neuf rend 2 echecs | `theo-archi` a reproduit le defaut (`avis-faisabilite.md` T7) ; le job `outils` est vert dans les rapports de `yanis-qa` et du relecteur ; **la mutation annoncee par `yanis-qa` n'est pas faite** |
| A8 : `outils/rapport-tokens.py` sans `--sprint` ecrivait six fichiers suivis | `69d9d6a2` : refus, code 2, avant toute lecture ; `test_6` reecrit | sans `--sprint` : 6 fichiers modifies avant, `git status --short` vide apres ; `test_6` rouge sans la correction | `yanis-qa` : trois mutants rouges, code 2 dans trois cas (dossier vide, absent, reel), arbre propre |

Cette tache a lance `python outils/rapport-tokens.py --sprint s55` avec `--sprint` explicite, ce que l'outil exige desormais. La consigne *"`--sprint` explicite tant qu'A8 n'est pas fait"* du planning est devenue une contrainte de l'outil.

---

## 7. Confirmation ArchUnit - `theo-archi`

Voir section 4.2. Deux mesures hors compte : **F3**, un cadre en tirets pose sur l'image d'une proposition deja essayee laisse tout le reacteur vert (0 echec sur 2 303 tests) : `ADR-0046` n'impose donc pas l'abandon de `E3` ;
**F4**, le plafond de 176 px d'`ADR-0104` decision 4.3 tient a la mesure et **n'est garde par aucun test** : dette ouverte **`S55-ART-CP-10-PLAFOND`**, due *"au plus tard avec le premier paquet a propositions-images"*, **a planifier par `sofia-sm`**
(`confirmation-archunit.md` section 4). Elle est inscrite en retro (action A5) et dans les questions du planning de s56.

## 8. Relecture - `relecteur-pedagogique`

`relecture-cloture.md` existe (A9 livree ; il n'existait pas a s54). Six corrections de texte appliquees (`C1` a `C6`, trois commits `[REL]`), six points routes (`R1` a `R6`). Traitement :

| Point | Traite en |
|---|---|
| `R1` forme 3 sans formule | `DEC-0115` A.18 (`camille-po`) ; `planning.md` lignes 27 et 395 par l'amendement de cette cloture (`b20655fa`) |
| `R2` quatre criteres du planning insatisfiables | amendement du planning de cette cloture (`b20655fa`) |
| `R3` 0,25 de A non alloues | `DEC-0115` A.19 (`camille-po`) |
| `R4` citation de 2018 non appliquee dans l'en-tete de `cm1-emc-principes-republique` | **ouvert** : c'est une levee de reserve (`EMC-CM1-R9`), rejeu a 0,25 |
| `R5` `CLAUDE.md` ligne 167 | corrige par `de1aa216` : `python outils/rapport-tokens.py --sprint sNN`. `grep -rn "rapport-tokens.py" CLAUDE.md squad/README.md .claude/agents/sofia-sm.md` : les deux autres fichiers portaient deja `--sprint sNN` ou ne citent que le nom du fichier. **Aucune copie sous `.claude/agents/` a mettre a jour.** |
| `R6` point 5 de "Decisions prises" de `DEC-0115` | `DEC-0115` A.20 (`camille-po`) |

Ce que le relecteur ne dit pas : la relecture de ponctuation des 12 indices de `cm1-grands-nombres` est une des choses que `nadia-prof` lui route ; son fichier mesure 0 espace ordinaire et 12 U+00A0 (section 4) sans nommer cette relecture (obstacle 15).

---

## 9. Couverture du programme : 141/182 (77,47 %)

Commande de la "Methode du compte" de `MATRICE-couverture.md` (l'`awk` de la section de s51), **relancee par moi sur les trois arbres, sans reprendre un chiffre** :

| Arbre | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` | Lignes | Denominateur | Taux |
|---|---|---|---|---|---|---|---|
| `fbe07d79` (base) | 137 | 4 | 41 | 76 | 258 | 182 | 75,27 % |
| `af8b326e` | 137 | 4 | 41 | 76 | 258 | 182 | 75,27 % |
| `HEAD` (`445647a7`) | **141** | 4 | **37** | 76 | 258 | 182 | **77,47 %** |

Perimetre : les 258 lignes de donnees, aucune mesure partielle. Meme chiffre que `cloture-nadia.md` section 6 (qui l'avait calcule avant le geste de matrice), que la note de s55 de la matrice et que `DEC-0115` A.14.
Les quatre lignes passees a `couvert-valide` sont celles de la section 2 ; `couvert-sous-reserve` n'existe pas dans la legende de la matrice, un `VALIDE SOUS RESERVE` non bloquant ferme en `couvert-valide`, reserves nommees dans la cellule
(`cloture-nadia.md` section 5). `ART-CP-10` reste `non couvert`, `EMC-CP-03` reste `couvert-valide`.

---

## 10. Capacite : `PAR-8` et `PAR-9`

Unites : bareme de `nadia-prof`, pas des tokens. **Aucun de ces chiffres n'est un tarif de ma main** ; je les recompte (arithmetique) et indique leur origine.

| Poste | Instance A | Instance B |
|---|---|---|
| Deux lots | 2,0 (`-14` 1,0 ; `-12` 1,0) | 2,5 (`EMC-CM1-03` 1,0 ; `-06` 1,5) |
| Arbitrage prealable | 0,5 | 0,5 |
| Plafonds de `G7` a `G11` | 0,25 | - |
| Forme d'`ART-CP-10` | 0,25 | - |
| `ART-RES` ; `EXC-R15` | 0,25 ; - | - ; 0,25 |
| `A10` (verification de s52) | 0,25 | - |
| Ecart de l'item 4 de `-03` (`P1`) | - | 0,25 (`nadia-prof` : *"sur la marge"* ; `camille-po` le compte dans sa somme) |
| **Somme des tarifs, marge exclue** | **3,5** | **3,5** (lecture de `camille-po`) |
| Marge de rejeu, allouee a aucun lot | 0,5, **non utilisee** | 0,5 (0,25 consommes par `P1` selon `audit-nadia-b-emc.md`) |
| **Lecture de `nadia-prof`** | **4,0 sur 4,0** | **4,0 sur 4,0** |

Recompte : A 1,0 + 1,0 + 0,5 + 0,25 + 0,25 + 0,25 + 0,25 = 3,5 ; B 1,0 + 1,5 + 0,5 + 0,25 + 0,25 = 3,5. **`PAR-8`** : borne 8,0, plafond dur 5,0 (jamais approche), deux lots par instance (au plus trois) : tenue.
**`PAR-9`** : 4,0 par instance, somme 8,0 : tenue sous la lecture de `nadia-prof` (4,0 + 4,0) comme sous celle de `camille-po` (`DEC-0115` A.19 : A 4,0 et B 3,75, somme 7,75, B inchangee depuis l'ouverture ; backlog : somme des tarifs de B 3,5).
**L'ecart de lecture sur B (3,5 ou 4,0) n'est pas tranche** : `camille-po` ecrit que la marge non allouee de B n'entre pas dans son addition et que l'ecart est a `nadia-prof` (backlog "Cloture de s55") ; elle ne l'a pas ecrit. Aucun refus n'a consomme de marge.
**Aucune troisieme instance n'a ete ouverte.** **Rejeux dus a s56 : 2,5** (A 1,75, B 0,75), non finances a s55 ; `PAR-11` reste non tranchee (`proposition-s56.md` section 6).

---

## 11. Tableau des obstacles du sprint

Daily non journalise. **Cette review est ecrite apres coup par une tache de cloture** : les obstacles ci-dessous sont ceux que les ecrits du sprint nomment, classes et routes selon la table de ma casquette.
Aucun n'est une observation personnelle sauf les 4, 5 et 14, que j'ai mesures dans cette tache. Un obstacle n'est declare **Resolu** que si le fichier qui le tranche existe sur le disque (verifie par moi dans cette tache).

| # | Obstacle | Categorie | Route a | Decision liee | Statut a la cloture |
|---|---|---|---|---|---|
| 1 | **Perimetre sans acte d'ouverture** au planning ; ordre relaye en forme 3 | Produit | `camille-po`, utilisatrice | `DEC-0115` (`303c6a82`), `DEC-0114` A.27 | **Resolu sur fichier** pour l'ouverture ; `DEC-0115` reste `Proposee` (obstacle 2) |
| 2 | **Financement de la mecanique d'`ART-CP-10` sans les mots de l'utilisatrice** : forme 3 sans formule (`R1`), execution consignee et non decision (`DEC-0115` A.18) ; question `Q6` ouverte | Produit | utilisatrice | `DEC-0115` 0.5, A.16, A.18 | **Ouvert.** Ce qui le clot : ses mots ecrits en forme 2 avec leur date |
| 3 | **`round-two` rouge en local a l'ouverture** alors qu'une CI verte etait relayee : `AucuneLigneDeTableau...EnTeteTest` rouge sur `review.md` de s54, ligne `U4` a quatre cellules (`avis-faisabilite.md` section 0) | Qualite | `sofia-sm` | `8f740977` | **Resolu sur fichier** : ligne a trois cellules, classe verte (`lecture-testabilite.md` section 1) ; la CI distante reste non lue |
| 4 | **`a3606b2f` : correctif de CI hors planning, hors `DEC-0115`, cause non ecrite** (section 5) | Technique / process | `theo-archi`, `sofia-sm`, `camille-po` | aucune | **Ouvert, constate.** Je n'ecris pas d'amendement retrospectif pour le poste : voir retro. La cause : `theo-archi` |
| 5 | **Quatre commits de livraison sans poste au planning ni a `DEC-0115`** : `2b66a441` (`ACV-CE1-R1`), `fa3b97c0` (`E3`, un commentaire et un en-tete), `e6262ac2` (`EXC-R16`), `a3606b2f`. Les deux reserves et `E3` sont nees des verdicts ; `DEC-0115` A.14 constate leurs textes servis, aucun amendement ne les engage | Process | `sofia-sm`, `camille-po` | aucune | **Constate.** Meme famille que `PCO-CE1-R1` a s54 (obstacle 12) : l'action A5 de s54 (amender avant le worktree) n'a pas ete suivie pour ces quatre postes. Pas d'amendement retrospectif |
| 6 | **`Q-Y8` : l'apostrophe typographique n'etait gardee par rien** ; `S54-ASCII-APOSTROPHES-TESTS` livree (`51c81763`) sans cas fabrique ; mutation verte (46 tests, 0 echec) | Qualite / test | `sofia-sm` (route par `yanis-qa`), `theo-archi` (forme), `marc-dev` | `2224e91e` | **Resolu pour le code** : 4 tests, mutation rouge chez `marc-dev` (4 echecs). **Non rejouee par `yanis-qa`**, que le critere nomme |
| 7 | **Critere non satisfaisable ou non tenu : cinq ecarts de `rapport-qualite.md` section 4** : `Q-Y6` (le rapport de `camille-po` dit 2, la commande non bornee rend 23), `Q-Y9` (`QC4` : 2 fichiers, le backlog annonce 1), `Q-Y10` (numeros de ligne 483 et 495 d'avant A8, lignes 463 et 481 a 492 sur `HEAD`), `C7` et `EMC-9` (le critere demande `EMC-9`, `lea-dev` lit `EMC-4`, `EMC-8`, s53 4.1, `BIA-5`), le planning (traite : amendement de cette cloture) | Qualite / redaction | `camille-po` (backlog), `yanis-qa` | `rapport-camille-criteres.md` ; amendement du planning | **Partiellement resolu** : le planning est amende ; les quatre autres sont dans le backlog de `camille-po`, **ouverts**. `Q-Y8` est le sixieme defaut, voir obstacle 6 |
| 8 | **Six reserves ouvertes sur lignes fermees** (`ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`), 2,5 de rejeu ; `ACV-CE1-R1` et `EXC-R16` ont leur texte servi mais ne sont pas levees | Capacite | `camille-po` | backlog "Cloture de s55", `proposition-s56.md` | **Ouvert**, a placer a la `DEC` d'ouverture de s56 |
| 9 | **Ecart de lecture sur B (3,5 ou 4,0)** (section 10) | Capacite | `nadia-prof` | aucune | **Ouvert** |
| 10 | **Dispositif d'`ART-CP-10` livre, jamais joue** ; `E3` pas acceptable pour un paquet servi ; questions Q1 a Q8 de `nadia-prof` avant le premier paquet ; **le jugement de la forme n'a pas eu lieu** | Pedagogie / produit | `nadia-prof`, `ines-design`, `theo-archi` | `ADR-0104` | **Ouvert.** Condition posee par `nadia-prof` avant le branchement du premier paquet a reproductions |
| 11 | **Plafond de 176 px garde par rien** (`S55-ART-CP-10-PLAFOND`) | Technique | `sofia-sm` (planifier), `theo-archi` (garde-fou) | aucune | **Ouvert, non planifie** : action A5 de la retro |
| 12 | **Mutation d'A7 et morsures de T2 non faites** (`rapport-qualite.md` section 6) | Qualite | `yanis-qa` | aucune | **Ouvert** |
| 13 | **Tronc distant** : 49 commits non pousses a `445647a7`, plus ceux de cette tache (reference locale `fa4ddc7e`) ; la CI distante n'a jamais ete lue pendant s55 ; `gh run list` rendait `HTTP 404` au planning | Qualite / etat du tronc | utilisatrice | aucune | **Ouvert.** Je ne pousse pas (consigne de la tache) |
| 14 | **Rouge `ADR-0087` sur `rapport-sofia-outils.md` ligne 22** : mon rapport d'A7 et A8 (`0e4b8ada`) portait une ligne de tableau a cinq cellules ; je ne l'avais pas lance en build ; `round-two` rouge de 19:32 (fusion) a 19:49 (`02798fa7`) ; `marc-dev` (corrections) et `nadia-prof` (audit d'`EXC-R15`) ont chacun lance un `verify` rouge pour cette seule raison (`rapport-marc-dev-corrections.md` E-1, `audit-nadia-b-exc.md`) | Qualite / process | `sofia-sm` | `02798fa7` | **Resolu sur fichier** : ligne a trois cellules, `python` de controle sur ce fichier : 0 ligne en surplus. La pratique est corrigee en retro (action A6) |
| 15 | **Relecture de ponctuation de `S54-PONCT-GN-INDICES` non nommee** : `nadia-prof` la route au relecteur, `lea-dev` dit qu'elle n'est pas faite, `relecture-cloture.md` mesure l'espace mais ne la nomme pas | Redaction | `relecteur-pedagogique` | aucune | **Ouvert** |
| 16 | **Fiche `exclusion-cp` : en-tete de s54** (*"Statut global"*, *"Derniere mise a jour"*) dit `EXC-R15` ouverte ; le titre du jeu (*"Une moquerie qui se repete"*) ne nomme plus que l'une des deux categories | Pedagogie / redaction | `nadia-prof` | aucune | **Ouvert** : *"a completer au prochain passage"* (`cloture-nadia.md` section 1) ; le titre est note par le relecteur (section 5) |
| 17 | **Verification `A10` de s54 (trois instances)** : `nadia-prof` ferme la condition (ii) pour s52 et dit que la derivation suivante lit s54, *"routee a personne"* | Pedagogie / capacite | `nadia-prof` | `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 2.5 | **Ouvert.** Condition a lever avant toute borne a trois instances |
| 18 | **`S54-OBS-SCI-CONSIGNE`** : premier report, criteres non ecrits | Produit | `camille-po` | `DEC-0115` A.10 | **Ouvert.** Un second report exige un acte dans la `DEC` de s56 |
| 19 | **Deux toolchains `~/.m2` du poste de `yanis-qa`** (s54) | Technique / poste | utilisatrice, `theo-archi` | `ADR-0071` | **Non reverifie** : l'enveloppe a rapporte 21.0.10 et la machine virtuelle des tests 21.0.10 chez tous les agents de s55 qui l'ont lu |

Rappel : un obstacle n'est clos que si le fichier de decision existe physiquement. Resolus sur fichier existant : 1 (pour l'ouverture), 3, 14 ; 6 (code) et 7 (planning) en partie. Constates ou ouverts : 2, 4, 5, 7 (reste), 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19.

---

## 12. Done pedagogique - `nadia-prof`

Prononce par elle seule, fiche par fiche (section 2). **Quatre `VALIDE SOUS RESERVE`** : `FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06`, reserves non bloquantes (aucun REFUSE, aucune reserve bloquante, `cloture-nadia.md` section 1).
**Un `VALIDE`** : `S54-ART-RES-R1` a `R3` (`cp-arts-oeuvres-familles`, aucun mouvement de ligne). **Un `VALIDE SOUS RESERVE`** : `S54-EXC-CATEGORIE` (`EXC-R15` levee, `EXC-R16` neuve, `EMC-CP-03` inchangee).
**Aucun Done a prononcer pour T2, T3, `PONCT`, `ASCII`, A7, A8** : aucun enfant ne voit de changement qui releve d'elle (`cloture-nadia.md` section 3) ; T3 se prononcera sur le premier paquet a reproductions, en jouant.
Chaque verdict est un audit joue sur un jar dont les ressources ont ete comparees a l'arbre ; rendu lu en HTML, **non vu au navigateur**. Cette review ne prononce aucun Done.

---

## 13. Points signales, sans correction de ma part

| Code | Point | Source | Qui le lit |
|---|---|---|---|
| F1 | `rapport-qualite.md` mesure `af8b326e` ; son *"2303"* n'est pas le total du sommet (2307) : l'ecart est `2224e91e` | section 4.5 | `yanis-qa` |
| F2 | `cm1-emc-principes-republique.yaml` ligne 50 porte encore la citation de 2018 incomplete (`R4`) | `relecture-cloture.md` | `nadia-prof`, `lea-dev` |
| F3 | Le commentaire de `manche.html` sur la revelation dit *"oeuvre-revelee"*, classe qui n'existe pas | `confirmation-archunit.md` F1 | `lea-dev` |
| F4 | Correction de deux faits de la review de s52 par `nadia-prof` : l'auteur du fichier `c3` est `lea-dev`, et `nadia-prof` C l'avait declare dans son message de retour. Je n'edite pas les fichiers de s52 | `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 2.4 | - |
| F5 | **Trailers.** Onze commits de s55 portent `Claude Opus 5.5` : huit `[NAD]` et trois `[THEO]` (`git log --format` des trailers, `fbe07d79..445647a7`). La regle de `DEC-0110` 17.4 (modele reellement execute) est celle qui s'applique ; `RAPPORT-tokens.md` donne le modele par agent. Mes deux commits portent `Sonnet 5.5` | `RAPPORT-tokens.md` | - |
| F6 | **Mesure de la fenetre de tokens** : la fenetre est fermee par l'heure de fin de cette review ; les lancements de verification posterieurs ne sont pas dans la fenetre. Le journal regenere la section de s54 sur sa fenetre close (2364 a 2473 tours, `sofia-sm` 130 a 239) ; le `RAPPORT-tokens.md` de s54 n'est pas reecrit | `RAPPORT-tokens.md`, `JOURNAL-tokens.md` | - |

---

## 14. Questions a porter a l'utilisatrice

| Code | Question | Source |
|---|---|---|
| U1 | **Q6** : financer le dispositif d'`ART-CP-10` (T2, T3), **ses mots ecrits en forme 2 avec leur date** passent `DEC-0115` a `Acceptee` ; en attendant, le financement est une execution consignee | obstacle 2 ; `DEC-0115` A.16, A.18 |
| U2 | **Q-U1** : le paquet a reproductions s'ecrit-il avant l'audit de s56 (O1 : reserves et un lot de francais, sans l'audit ; O3 : audit sur l'instance B, 8,25 sur 8,0, ecart de 0,25) | `proposition-s56.md` sections 4 et 7 |
| U3 | L'ordre des matieres de `DEC-0115` section 3 vaut-il pour s56 : son rang 2 (EMC CM1) est epuise, le rang 3 achete au plus une ligne | `proposition-s56.md` Q4 |
| U4 | Decoupage du francais CE1 en deux instances a partir de s57 (s58 au lieu de s60 pour les 11 lots) : exige l'accord de `nadia-prof` sur `PAR-2` et l'avis de `theo-archi` sur `ADR-0065` | `proposition-s56.md` Q3 |
| U5 | Pousser les commits de s55 et lire la CI **en nommant le commit et les jobs** ; dire pourquoi `gh run list` rend `HTTP 404` depuis ce poste | obstacle 13 |

---

## 15. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique et ne change aucun statut de matrice, de fiche ou de decision.
- Elle n'annonce pas la CI distante verte : les jobs de la section 4.4 ont tourne en local.
- Elle n'edite ni `DEC-0115`, ni la matrice, ni une fiche, ni le backlog, ni `catalogue.yaml`, ni un paquet.
- Les seuls fichiers de gouvernance qu'elle modifie sont `CLAUDE.md` (une phrase, `de1aa216`) et `planning.md` (un amendement date, `b20655fa`), tous deux traces en section 8.
- Elle ne decide aucun des points qu'elle route et n'engage rien pour s56 : la proposition est dans `proposition-s56.md`, qui n'est pas un planning.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cette review.** Decisions du sprint, rapportees et non prises ici (liste dans `RAPPORT.md`) : `DEC-0115` (creee, amendements A.1 a A.21, statut `Proposee`),
`DEC-0114` (amendement A.27, `Proposee` a `Acceptee`). Aucune `ADR` creee ni modifiee.
