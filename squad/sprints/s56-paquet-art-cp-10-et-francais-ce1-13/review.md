# Review - s56 - une ligne fermee (142/182), le premier paquet a reproductions refuse a l'audit, six reserves levees

- **Horodatage de debut** (`planning.md`) : 2026-10-09 20:36 (UTC+02:00), lu par `date` a l'ecriture du planning.
- **Horodatage de fin** : 2026-10-11 14:14 (UTC+02:00), lu par `date` a la fin de l'ecriture de ce fichier.
- **Duree effective** : 41 h 38 min d'horloge entre les deux heures ci-dessus. Pas de timebox. Premier commit de livraison de contenu 2026-10-09 22:27 (`550df967`), dernier commit de livraison 2026-10-11 13:53 (`56e2a6dc`).
  **Aucun commit entre 2026-10-10 11:17 (`f37e33dd`) et 2026-10-11 11:06 (`38ba3d5c`)** : 23 h 49 min sans livraison (`git log`), voir obstacle 6.
- **Casquette** : `sofia-sm`, Scrum Master. Aucune story de developpement portee dans cette tache.
- **Arbre revu** : branche `s56-sofia-cloture`, base `116354be` (`git log -1 --format=%h`), `git status --short` vide avant ecriture, `bash outils/verifier-lignee.sh` sort 0 (*"descend de la lignee de round-two"*).
- **Provenance de cette review.** Ecrite par une tache de cloture qui n'a pas assiste au sprint : chaque ligne cite l'ecrit dont elle vient. Trois enonces viennent du message de lancement de cette tache et
  d'aucun fichier du depot ; ils sont signales comme tels (sections 4 et 12, obstacles 6, 8 et 22). Je n'ai relance ni les audits de `nadia-prof`, ni les mutations de `yanis-qa`.
- **Lignee des fusions de s56, relue par moi.** 29 fusions de `dfcec37c..116354be` (`git rev-list --merges --count`), 46 commits hors fusion. Pour chaque fusion : `git merge-base` des deux parents, `git rev-list --count` de l'apport,
  `git rev-list --merges --count` de l'apport. Resultat : la base de fusion est le sommet de `round-two` ou un de ses ancetres directs dans 29 cas sur 29, l'apport compte de 1 a 6 commits, aucun apport ne contient une fusion.
  Aucune branche n'a ete provisionnee sur une autre base. Les bases de lecture des devs sont des commits de `round-two` fusionne : `f3de5e58` est ancetre de `8d40c4a5` et de `e8155d7e`, les deux bases de `lea-dev` (`git merge-base --is-ancestor`, sort 0).

---

## 1. L'increment livre contre l'objectif gele

Objectif gele (`planning.md`) : ecrire puis auditer le premier paquet a reproductions d'`ART-CP-10`, brancher `ce1-verbe-sujet` (`FRA-CE1-13`), lever six reserves de jeux servis. Au mieux 141/182 a 143/182.

| Objectif | Etat a la cloture | Source |
|---|---|---|
| Paquet a reproductions ecrit, branche, audite | **Ecrit, branche, audite : REFUSE.** `ART-CP-10` reste `non couvert`. Le paquet est toujours au catalogue (`grep -c 'id: bandit-manchot-rapprocher-deux-oeuvres-cp$'` sur `catalogue.yaml` rend 1, relance par moi) | `cloture-nadia.md` section 1 ; fiche du jeu |
| Brancher `ce1-verbe-sujet` | **Tenu** : `grep -c "paquet: ce1-verbe-sujet$"` rend 1 (0 a l'ouverture) ; `VALIDE SOUS RESERVE`, `FRA-CE1-13` ferme | `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| Six reserves levees | **Tenu** : `ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` levees, une fiche chacune | `cloture-nadia.md` section 1 |
| 141/182 a 143/182 | **142/182 (78,02 %)** : une fermeture sur deux possibles | section 10 |

---

## 2. Les stories, une par une

Colonnes : **Livre** = commit qui livre ; **Verdict** = prononce par `nadia-prof` (je n'en prononce aucun) ; **Reserve** = ce qui reste ouvert.

| Story | Livre | Verdict de `nadia-prof` | Reserve ou suite |
|---|---|---|---|
| `S56-ARB-A`, `S56-ARB-B`, `S56-ART-CP-10-FORME`, `S56-ART-CP-10-OEUVRES` | `c92ad8f7`, `1ae74a43`, `6788abc2` (`nadia-prof`) | sans objet | closes (backlog "Cloture de s56") |
| `S56-ADR-0024`, `S56-ADR-0026` | `6f8895a4`, `1de72f0f` (`theo-archi`) | sans objet | closes ; fusionnees avant le premier commit du paquet (22:34 et 22:21 contre 23:34) |
| `S55-ART-CP-10-PAQUET` | `25f0c1ee` (`lea-dev`) | refuse avec l'audit | six items, quatre reproductions restaurees, quatre cliquets 0 a 6, 81 a 87, 8 a 12, 176 474 a 275 163 |
| `S55-ART-CP-10-PLAFOND` | `1f0fe8e4` (`lea-dev`) | sans objet | mordu : 3 echecs sous mutation (`confirmation-archunit.md` section 4) |
| `S56-ART-CP-10-ECHEC` | `696f4f20`, `8db641f5` (`lea-dev`) | croix de 40 px tenue | mordu : 2 echecs sous mutation ; cinq proprietes non gardees (`F3`) |
| Entree de catalogue du paquet | `0076cbff`, `f37e33dd` (`lea-dev`) | **REFUSE** | 105 px manquent ; `RDO-CP-R1`, `RDO-CP-R2` neuves |
| `S56-AUD-ART-CP-10` | `12c1d6db` (`nadia-prof`) | **REFUSE** | un refus est un poste livre, pas un report (`cloture-nadia.md` section 2) |
| `S56-BR-FRA-CE1-13` | `550df967` (`marc-dev`) | **VALIDE SOUS RESERVE** | `VSJ-CE1-R1` neuve, 0,5 |
| `S55-ACV-CE1-R1`, `S55-ACV-CE1-R2` | texte `2b66a441` (s55) ; `955951f2` (`marc-dev`) | **LEVEES** | 8 pluriels muets sur 12 |
| `S55-CLM-CE1-R1` | `afc39a7c` (`marc-dev`) | **LEVEE** | 24 indices |
| `S55-EMC-CM1-R8`, `S55-EMC-CM1-R9` | `1d1d3ae6`, `02b938bd` (`lea-dev`) | **LEVEES** | citation de 2018 appliquee |
| `S55-EXC-R16`, A11 | texte `e6262ac2` (s55) | **LEVEE** ; en-tete de fiche complete | aucune |
| Garde-fous `B2`, `B3`, `B5`, `B7`, `B8` | `22b5f888`, `5021ddf0`, `ca6c5e8a`, `fe150188`, `2eeabb58` (`lea-dev`) ; `7b315f36` (borne de `B8`) | sans objet ; `nadia-prof` ne les a ni lus ni mutes | sans ligne au planning, obstacle 8 ; `B6` refuse par `theo-archi`, tenu par `nadia-prof` seule |
| `Q7` (`ines-design`) | `b2136295` | sans objet | memes cotes que `nadia-prof` a 744 x 551 |
| `S54-OBS-SCI-CONSIGNE` | non engagee | sans objet | **second report** ; un troisieme est interdit (`DEC-0112` section 6) |
| A4, A14 (`theo-archi`) | `avis-faisabilite.md` sections 7 et 8 | sans objet | A4 : mecanisme reproduit, declencheur non etabli ; A14 : refus motive |
| A8 (`yanis-qa`), A12 (`relecteur-pedagogique`) | `56e2a6dc` ; `3ca6a20b` | sans objet | A8 livree ; A12 : rien a corriger, ligne ecrite |

Engagements : chaque story du planning et de ses amendements est livree ci-dessus, **sauf `S54-OBS-SCI-CONSIGNE`** (second report, acte dans `DEC-0116` section 5 et A.2). Un REFUSE est prononce, sur un poste engage et livre.

---

## 3. Le planning a-t-il ete rattrape par les decisions et les avis qui devaient le suivre ? (amendement de s48)

Oui, par un amendement date, et le planning n'est pas reecrit ici. `planning.md` (2026-10-09 20:36, `dfcec37c`) affecte sans avis ni arbitrage ; l'avis de `theo-archi` (`026b2e17`, 21:18), `S56-ARB-A` (`c92ad8f7`, 21:11), `S56-ARB-B` et la forme
(`1ae74a43`, 21:08) puis le choix des oeuvres (`6788abc2`, 21:44) arrivent apres lui et avant tout code. L'amendement du planning (heure inscrite 22:17, commit `b906364e` a 22:36, fusionne `8d40c4a5` a 22:37) porte
B a 4,75, capacite 8,25, `S56-ART-CP-10-OEUVRES` sur l'instance B, les deux postes d'`ADR`. **Le premier commit de developpement (`550df967`, `marc-dev`, 22:27) est anterieur au commit de l'amendement (22:36)** ; son worktree y est constate (section E de l'amendement).

Les trois echeances de mon planning, contre les commits :

| Echeance du planning | Heure prevue | Ce qui s'est passe | Tenue ? |
|---|---|---|---|
| Prealables : `S56-ARB-A`, `S56-ARB-B`, avis, `S56-ART-CP-10-OEUVRES` | 2026-10-10 08:00 | tous fusionnes le 2026-10-09 avant 21:45 (`a5976e3b`, `840cf04f`, `796badf2`, `bb4041c8`) | oui |
| `S55-ART-CP-10-PAQUET` fusionne | 2026-10-10 12:00 | `e8155d7e`, 2026-10-09 23:44 | oui |
| Entree de catalogue fusionnee | 2026-10-10 20:00 | commit `f37e33dd` 2026-10-10 11:17 ; **fusion `bdd6dbe4` le 2026-10-11 11:07**, soit 15 h 07 min apres l'echeance | **non** |

Ce que le planning disait en cas de glissement : le dire a l'utilisatrice le jour meme. **Je ne trouve dans le depot aucune trace que cela ait ete dit le 2026-10-10** ; je n'ai pas assiste a ce jour. L'audit n'a pas glisse hors du sprint : il est livre le 2026-10-11 12:41, donc ce n'est pas un report.
**Porte du planning insatisfiable telle qu'ecrite (de ma main).** Le planning (tableau des postes sans prix, ligne `Q1`, `Q7`) et le backlog placent `Q7` (releve a dix largeurs sur le gabarit livre) **avant** l'entree de catalogue.
`Q7` se mesure sur un jar qui sert l'entree : `Q7-releve-gabarit-livre.md` sections 0 et 1 (*"l'application est servie par le jar, le paquet est au catalogue"*). `lea-dev` l'a constate (`rapport-lea-dev.md` 11.3) et a commite l'entree sans `Q7`. Le releve est arrive le 2026-10-11 a 12:26 (`b2136295`).

---

## 4. Les mots de l'utilisatrice, tels que relayes

Aucun n'a ses mots de sa main dans le depot ; `DEC-0116` 0.1, A.1, A.4 les cite en forme 2 de `DEC-0062` (mots entre guillemets, date), recus par l'agent lanceur qui les atteste.

| Mots | Date | Lieu | Effet ecrit |
|---|---|---|---|
| "O3 avec ecart 0,25" ; "lea-dev" (proprietaire du paquet) | 2026-10-09 | `DEC-0116` 0.1 | composition O3 ; `lea-dev` ecrit le paquet |
| "Ecart de 0,5" | 2026-10-09 | `DEC-0116` A.1 | ecart de 0,5 sur la borne de 8,0, pour s56 seulement |
| "Oui, a 4,75" | 2026-10-09 | `DEC-0116` A.4 | B a 4,75, choix des oeuvres sur B |
| "Reporter a s57, abandon ou placement ecrit alors" | 2026-10-09 | `DEC-0116` A.2 | second report de `S54-OBS-SCI-CONSIGNE`, troisieme interdit |
| "Lance Q2 aussi meme si on depasse la borne" | 2026-10-11 | message de lancement de cette tache ; **cite ici, aucun fichier du depot ne le porte** | accepte le depassement des garde-fous `B2`, `B3`, `B5`, `B7`, `B8` (obstacle 8) |

Le dernier enonce est en forme 2 : de l'utilisatrice, ses mots cites, la date. **Meme reserve que `DEC-0116` 0.1 : je ne l'ai pas lu de sa main, il me vient de l'agent lanceur.** Deux faits que je ne peux pas etablir : `Q2` ne designe pas ces garde-fous dans le depot
(`Q2` de `proposition-s56.md` section 7 est la question de l'instance qui audite l'arts plastiques CP, a laquelle l'utilisatrice a repondu "ok", section 9 D4) ; l'attribution aux cinq garde-fous est celle de l'agent lanceur. **Il fonde que le travail s'est fait, jamais de quelle borne ni de combien** : `DEC-0116` A.18 point 5 ecrit ne pas savoir (section 11).

---

## 5. Done technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `build`, `prefixes`, `chemins`, `outils`. **La CI distante (GitHub Actions) n'a ete lue par personne, cette tache comprise** : `gh run list` rendait `HTTP 404` depuis ce poste
(`avis-faisabilite.md` section 7). **Rien n'est pousse.** Tout ce qui suit a tourne en local, sous Windows. **Je ne declare pas le Done technique** : ce que j'ai, ce sont des jobs lances en local et un rouge de CI dont la cause n'est pas etablie.

### 5.1 Ce que les agents ont lance, par job

| Agent | Arbre | `build` | `prefixes` | `chemins` | `outils` |
|---|---|---|---|---|---|
| `yanis-qa` | `80c06e64` | lance (`clean verify`, 2403 tests, vert) ; etape "Package du fat jar" **non lancee telle quelle** | lance (39 sujets) | lance | lance (10 juges) |
| `theo-archi` | `80c06e64` | lance, deux etapes, vert deux fois | lance | lance | **non lance** |
| `relecteur-pedagogique` | `7130dec6` plus ses commits | lance (`clean verify`, 2404 tests, vert) ; etapes "presence du build" et "package" non lancees | lance | lance | **non lance** |
| `nadia-prof` | `a6ca1751` | lance (`clean verify`, 2386 tests, vert) | sur son commit | sur son commit | **non lance** |
| `marc-dev`, `lea-dev` | arbre de chaque commit | `clean verify` avant chaque commit, `BUILD SUCCESS` ; **`lea-dev` : non mesure commit par commit pour les garde-fous**, seul le dernier (`2eeabb58`) est un `verify` complet (`rapport-lea-dev.md` 13.3) | lance | lance | lance (10 juges) |

### 5.2 Un rouge intermediaire a signaler

`yanis-qa` : son propre rapport, premiere version, a rendu `BUILD FAILURE` (1 echec sur 2397 tests de `bootstrap`, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent`) : ses codes de mutation `XX-NN` etaient lus comme des identifiants de condition non alloues
(`ADR-0085`). Corrige avant son commit en renommant les codes sans tiret (`rapport-qualite.md` section 1, ligne `build`). **Rouge que le garde-fou a vu avant le depot, pas apres** : la pratique d'A6 de s55 (lancer `build` avant de commiter un `.md`) a tenu.
`lea-dev` : une erreur `JarRecreoIT` (*"Web server failed to start. Port 51729 was already in use"*) sur un premier `verify`, vert a la reexecution ; `yanis-qa` ne l'a pas reproduite (onze lancements temoins verts). **Non expliquee** (obstacle 20).

### 5.3 Total des tests, etabli par moi sur l'arbre fusionne : 2404

`bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, sans `-pl`, sur `116354be` : `BUILD SUCCESS`, 3 min 11 s, JDK de la toolchain 21.0.10 ; la machine virtuelle des tests rapporte `java.version` 21.0.10 dans les 295 rapports XML (`grep -rhoE` du nom de propriete, une seule valeur).

| Module | Tests | Echecs, erreurs, ignores |
|---|---|---|
| `recreo-domain` | 120 | 0 |
| `recreo-application` | 18 | 0 |
| `recreo-adapter-web` | 442 | 0 |
| `recreo-adapter-content` | 1470 | 0 |
| `recreo-bootstrap` | 348 plus 6 d'integration (Failsafe) | 0 |
| **Reacteur** | **2404** | **0** |

**Total : 2404.** Methode : lignes `Tests run:` de fin de module de la sortie Maven, recoupees par la somme des attributs `tests`, `failures`, `errors`, `skipped` des `TEST-*.xml` de `surefire-reports` et `failsafe-reports` des cinq modules (script de poste, 2404). Perimetre : les 295 rapports, aucune exclusion.
**Delta : +97 sur 2307** (base `4cd44098`, mesure du planning, relancee par `yanis-qa` classe par classe : 120, 18, 407, 1423, 333 plus 6). Origine : **+96 mesures par `yanis-qa` sur `80c06e64`** (2403 ; adapter-content +47, adapter-web +35, bootstrap +14) plus **+1** de `7b315f36`
(le test de la borne de `B8`, `bootstrap` 347 puis 348). **Les annonces des developpeurs ne s'additionnent pas a ce total** : chacune mesure la base de son worktree plus son lot. Elles se recoupent pourtant : `lea-dev` 60 (0, 11, 27, 2, 3, 17), `marc-dev` 33 (22, 6, 5), `theo-archi` 3 = 96 ;
`rapport-qualite.md` cite la meme reconciliation. Aucune annonce n'a ete reprise sans mesure.

**Ce qui a change depuis l'arbre du rapport qualite (`80c06e64`)** : `git diff --name-only 80c06e64 HEAD` : 24 fichiers avant mes fichiers (23 sous `squad/` ou `docs/`, un test). Un seul fichier executable : `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest.java` (+13, -2, le test de `7b315f36`). Les autres sont des `.md`, des fiches, la matrice, `REGISTRE-identifiants.md`,
`Q7-releve-gabarit-livre.md`, `pilote-q7.mjs` (un script de mesure, hors `src/`) et la modification d'`ADR-0026` par le relecteur (`C3`). Le rapport qualite ne couvre donc ni ce test ni ces fichiers ; le `verify` ci-dessus les couvre.

### 5.4 Ce que j'ai lance moi-meme

Chiffres de moi, sur `116354be` plus mes fichiers de cloture, pas repris d'un rapport. Le `clean verify` de 5.3 a ete lance sur `116354be` **avant** l'ecriture de ces fichiers ; il est relance ci-dessous sur l'arbre qui les porte, **puis une derniere fois apres le remplissage de cette section** (heure de fin, tableaux de jobs et de tokens), dont le resultat est rendu dans le compte rendu de la tache, pas dans ce fichier.

| Job | Commande | Resultat |
|---|---|---|
| `build`, etape `verify` | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` | `BUILD SUCCESS` sur `116354be` (5.3) ; sur l'arbre avec mes fichiers de cloture : **`BUILD SUCCESS`**, 2 min 51 s, 2404 tests (120, 18, 442, 1470, 348 et 6), 0 echec, 0 erreur, 0 ignore ; `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` lit mes trois fichiers et passe |
| `build`, etape `package` | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, sans `clean`, apres le `verify` | **`BUILD SUCCESS`**, 2 min 01 s, code 0, journal `Replacing main artifact ... with repackaged archive`, sur l'arbre avec mes fichiers de cloture (hors les remplissages de cette section) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` ; `git ls-files` plus mes fichiers, hors le test du garde-fou, en entree de `bash .github/scripts/verifier-chemins.sh` | **vert**, codes 0 : le test du garde-fou et `verifier-chemins.sh` sur `git ls-files -co --exclude-standard` (suivis et non suivis, mes fichiers compris) : *"Aucun chemin de poste de travail ni nom de compte"* |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` ; les sujets de `git log --no-merges --format=%s dfcec37c..HEAD` en entree de `bash .github/scripts/verifier-prefixes.sh` | **vert**, codes 0 : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, et les 46 sujets hors fusion de `dfcec37c..HEAD` ; le sujet de mon commit (`[SOF] s56: ...`) passe dans `verifier-prefixes.sh` avant sa creation. Ce n'est pas la plage `github.event.before..GITHUB_SHA` d'un push reel : la branche `BASE` du workflow n'est pas exercee |
| `outils` | les trois motifs de la CI (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), avec `python` | **vert, 10 juges, 10 codes 0**, aucun motif vide (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) |

Ce que je n'ai **pas** lance : le workflow sous GitHub Actions ; la branche `BASE` du job `prefixes` ; la version de Maven du runner ; tout ce qui tourne sous Linux ; une seule configuration de fins de ligne (`core.autocrlf=true`).

### 5.5 Le rouge de CI que `a3606b2f` corrige : cause non etablie (A4)

`theo-archi` (`avis-faisabilite.md` section 7) **reproduit le mecanisme** : sur `c315c024` (`a3606b2f^`), `SOURCE_DATE_EPOCH=318211200 bash outils/mvn-jdk21.sh -B clean verify -pl application -am` rend 1 echec sur 17 (`laClasseDeDomainVientDuTargetDuDepotEtNonDuDepotLocal`, jar a l'horodatage 1980) ;
meme commande sans la variable : 0 echec. **Declencheur sur GitHub : non etabli.** Aucun POM ne fixe `project.build.outputTimestamp`, `ci.yml` ne pose pas `SOURCE_DATE_EPOCH`, et le journal du job rouge n'est cite par aucun fichier ; `gh run list` rend `HTTP 404`.
Il ecrit lui-meme : *"Je ne l'ecris pas comme cause tant que ce journal n'est pas lu."* **Je ne l'ecris pas non plus.** Ce qui trancherait : le journal du job `build` rouge sur le commit pousse avant `a3606b2f`, lu par l'utilisatrice (A3).
Ce que le depot montre : le mecanisme est reel et corrige ; que ce soit **ce** rouge-la n'est pas prouve. A3 est reporte une seconde fois (`DEC-0116` section 6 : `A2` de s54, `A3` de s55, s56).

---

## 6. Portes de sortie nommees au planning, verifiees physiquement (README section 9, etape 7)

| Porte | Fichier ou commit | Etat a la date de cette review |
|---|---|---|
| C7 : `rapport-marc-dev.md` et `rapport-lea-dev.md` existent | les deux existent ; chacun porte "relu" ou "non relu" par regle (`rapport-marc-dev.md` section 4 ; `rapport-lea-dev.md` section 5) | tenue |
| Lecture de testabilite avant la premiere livraison de code | `ls squad/sprints/s56-*/lecture-testabilite.md` : **absent** ; la premiere livraison (`550df967`) est du 2026-10-09 22:27 | **non tenue** (obstacle 16) |
| Liste des verbes de `PRS-CE1-1` ecrite avec `marc-dev` | absente ; remplacee par une regle dans le test (`rapport-marc-dev.md` E7), mordue par la mutation M3 de son lot | **non tenue telle qu'ecrite** |
| `Q1` avant `S56-ART-CP-10-ECHEC` ; `Q7` avant l'entree de catalogue | `Q1` : `c4a79010` (21:11) ; `Q7` : `b2136295` (2026-10-11 12:26), apres `f37e33dd` | `Q1` tenue ; `Q7` **non tenue**, porte insatisfiable (section 3) |
| Ancetres de l'entree de catalogue : plafond et echec | `1f0fe8e4` et `696f4f20` sont ancetres de `19d9312f`, base de `lea-dev` ; fusionnes avant `f37e33dd` | tenue |
| Deux amendements d'`ADR` avant les worktrees du paquet et d'`ECHEC` | `7c087903` (22:21) et `11a576b5` (22:34) contre 23:34 | tenue |
| Fiches de `-13` et du paquet, apres l'entree de catalogue | `FICHE-bandit-manchot-verbe-sujet-ce1.md`, `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` : existent, blocs de verdict du 2026-10-11 | tenue |
| A12, ligne dans `relecture-cloture.md`, meme sans trouvaille | section 1 du fichier : *"Rien a corriger"*, quatre mesures, contre-verification par un script | tenue |
| A8 dans `rapport-qualite.md` | section 3, trois sous-postes | tenue |
| A14 : test ou refus motive | `avis-faisabilite.md` section 8 : refus motive (47 fichiers sur 65 rouges) | tenue |
| A4 : cause et commande ou message qui la reproduit | section 7 : mecanisme reproduit, declencheur non etabli | **partielle** (5.5) |

---

## 7. Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md`, arbre `80c06e64` : 248 tests de `com.recreo.archi` verts (240 a la cloture de s55) ; **39 `static final ArchRule` et 36 `@ArchTest`, inchanges** de `dfcec37c` a `80c06e64` ; aucune regle affaiblie ;
trois cliquets montent de la taille exacte de ce qui est servi (0 a 6, 81 a 87, 8 a 12 reproductions, 176 474 a 275 163 octets). Aucun `ADR` neuf, trois amendes (`ADR-0026`, `ADR-0024`, `ADR-0104`), chacun avec sa ligne d'index et sa regle executable.
**`F1` de ce fichier (la borne de `B8` non epinglee a son bord)** : corrige par `lea-dev` (`7b315f36`, mutation 10 a 12 : 2 echecs sur 4). `B8` dans `bootstrap/archi` : confirme, pas de copie du comptage de mots (`ADR-0101` decision 2).
Jobs lances : `build` (deux etapes), `chemins`, `prefixes` ; `outils` non lance.

---

## 8. Rapport qualite - `yanis-qa`

`rapport-qualite.md`, arbre `80c06e64`. **90 mutations distinctes, 75 rouges (dont 2 temoins positifs), 15 vertes** (MT04, MT05, MT22, MT23, MT24, MT27 a MT31, MT40, MT41, MT51, MT64, MT65). Neuf constats, routes par lui, que je ne tranche pas :

| Code | Ce qui ne mord pas | Route |
|---|---|---|
| F1 | la case des propositions perd `aspect-ratio: 1 / 1` sans qu'un test rougisse (cases de carrees a 1 : 1,4, +64 et +76 px de rangee a 744 et 992) | `theo-archi`, `ines-design` |
| F2 | une regle sur l'image sous `@media` echappe au garde-fou du plafond | `theo-archi` |
| F3 | cinq proprietes de la marque et de la revelation non gardees (taille, centrage, place reservee, attenuation, anneau de revelation) | `nadia-prof`, `ines-design`, `theo-archi` |
| F4 | le choix de la derniere manche par REV-3 n'est pas mordu, ni l'oracle qui le juge | `theo-archi` |
| F5 | la borne basse de `B7` n'est epinglee par rien (`ITEMS_AU_MOINS = 3` reste vert) | `lea-dev` |
| F6 | la clause 2 de `B5` ne change jamais le verdict ; seule l'assertion sur le texte du grief la tue | `theo-archi` |
| F7 | `œuvres` ecrit `oeuvres` passe toute la suite ; `oeuvrse` passe le garde-fou d'accent | `relecteur-pedagogique`, `theo-archi` |
| F8 | la mutation d'A7 annoncee en s55 est un mutant equivalent ; la regression reelle est `head -n1`, mordue (MT06) | `sofia-sm` : retro, section 3 |
| F9 | le critere d'origine du plafond (ligne 1780) reste lisible dans le corps de la story, insatisfiable | `camille-po` |

**Non produit** : `lecture-testabilite.md` (obstacle 16). **A8** : `Q-Y8` rejouee et rouge (MT01 a MT03) ; mutation d'A7 faite, mutant equivalent demontre ; morsures de T2 : toutes mordent (MT07 a MT18). `yanis-qa` ne mesure que ce qu'il a lance : la CI distante, les fins de ligne dans l'autre sens, ce qui se juge en jouant.

---

## 9. Relecture - `relecteur-pedagogique`

`relecture-cloture.md` existe. A12 : rien a corriger, quatre mesures et une contre-verification (28 indices, 0 espace ordinaire, 12 espaces insecables, 0 signe colle). **Quatre corrections de texte** (`C1` a `C4`, un commit `[REL]`) : `C1` est un defaut de fond (le tableau des reproductions restaurables de l'avis listait 19 lignes pour 20, il manquait `oeuvre-vitrail`, somme de colonne 485 661 contre 516 335 ecrit) ;
`C2` et `C4` des tournures, `C3` un mot d'`ADR-0026`. **Cinq points routes sans reecriture** :

| Point | Route | Traite en |
|---|---|---|
| P1 : `DEC-0114` ligne 31, attribution de forme 3 au pluriel (*"recus de MASTER..."*) | `sofia-sm`, `camille-po` | retro section 4 (mon constat) ; la lecture de `DEC-0062` est a `camille-po` : **ouvert** |
| P2 : `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` section 11 renvoie a une section 1.2 qui n'existe pas | `nadia-prof` | **ouvert** |
| P3 : le total 2307 cite sous deux perimetres dits differents puis identiques | `sofia-sm` | retro section 4 : la demonstration est ecrite ; **clos par ce fichier** |
| P4 : `marque-echec.svg`, 295 octets d'un cote, 296 de l'autre | `ines-design`, `lea-dev` | **ouvert** (`git cat-file -s` rend 295, `wc -c` du poste 296 selon le releve, fins de ligne non etablies) |
| P5 : `CHOIX-OEUVRES-s56-art-cp-10.md` applique "1 a 5" des regles, le planning (C7) en cite six | `nadia-prof` | **ouvert** |

Jobs lances par lui : `build` (2404 tests, vert), `chemins`, `prefixes` ; `outils` non lance.

---

## 10. Couverture du programme : 142/182 (78,02 %)

Commande de la "Methode du compte" de `MATRICE-couverture.md` (l'`awk` de la section de s51), **relancee par moi sans reprendre un chiffre** :

| Arbre | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` | Lignes | Denominateur | Taux |
|---|---|---|---|---|---|---|---|
| `dfcec37c` (debut) | 141 | 4 | 37 | 76 | 258 | 182 | 77,47 % |
| `7130dec6` (avant le geste de `camille-po`) | 141 | 4 | 37 | 76 | 258 | 182 | 77,47 % |
| `HEAD` (`116354be`) | **142** | 4 | **36** | 76 | 258 | 182 | **78,02 %** |

Perimetre : les 258 lignes de donnees, aucune mesure partielle. Meme chiffre que `DEC-0116` A.15 et la note de la matrice. **Une ligne ferme : `FRA-CE1-13`** (`VALIDE SOUS RESERVE`, `VSJ-CE1-R1` non bloquante, sans mention de couverture partielle). **`ART-CP-10` reste `non couvert`**, colonne des jeux vide, aucune mention de s50.
Fichiers de contenu sans entree de catalogue : **36** sur 183 (`comm -23` des fichiers de `contenu/` et des `paquet:` du catalogue, relance par moi), contre 37 sur 182 a l'ouverture : le paquet a reproductions ajoute un fichier et son entree, `ce1-verbe-sujet` gagne la sienne.

---

## 11. Capacite : `PAR-8` et `PAR-9`

Unites : bareme de `nadia-prof`, pas des tokens. **Aucun chiffre n'est un tarif de ma main** ; je recompte l'arithmetique et dis d'ou vient chaque nombre.

| Poste | Instance A | Instance B |
|---|---|---|
| Arbitrage prealable | 0,5 | 0,5 |
| Lot `ce1-verbe-sujet` ; `CLM-CE1-R1` (`PAR-14`) | 1,0 ; 1,0 | - |
| Rejeu groupe `ACV-CE1-R1` et `ACV-CE1-R2` | 0,5 | - |
| `EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16` | - | 0,75 |
| Arbitrage de forme ; choix des oeuvres | - | 0,25 ; 0,25 |
| Audit du paquet | - | 2,5 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| **Engage** (planning, amendement : 8,25 sur 8,0 ; `DEC-0116` A.13) | **3,5** | **4,75** |

**Engage : 3,5 + 4,75 = 8,25**, sous les 8,5 que l'ecart de 0,5 permet (`DEC-0116` A.1) : **0,25 n'est alloue a aucun poste** et ne l'a pas ete. Plafond dur de 5,0 : B a 4,75, non atteint. Deux lots pour A, un pour B : sous le maximum de trois.
**Consomme, selon `cloture-nadia.md` section 6 : 5,75** (audit 2,5 ; `-13` 1,0 ; `CLM-CE1-R1` 1,0 ; `ACV` 0,5 ; `EMC-CM1-R8`, `-R9`, `EXC-R16` 0,75 ; A11 0), reparti A 2,5, B 3,25. **Mon recompte differe** : ce total ne contient pas `S56-ARB-A` (0,5), `S56-ARB-B` (0,5),
`S56-ART-CP-10-FORME` (0,25) ni `S56-ART-CP-10-OEUVRES` (0,25), quatre postes livres (backlog "Cloture de s56") qui ont un prix a leur ligne. **Avec eux : 7,25**, soit A 3,0 et B 4,25 ; les deux marges de 0,5 ne sont pas consommees
(*"aucun rejeu du paquet a s56"*). Les deux lectures sont des sommes de prix de `nadia-prof` ; la seconde compte tout ce qui a ete livre. **Aucune des deux lectures du consomme ne depasse 8,0** ; c'est l'engage (8,25) qui depasse 8,0 de 0,25, sous l'ecart accorde de 0,5.
**Garde-fous `B2`, `B3`, `B5`, `B7`, `B8`** : hors `PAR`, 0,0 pour `nadia-prof`, environ 0,5 jour de developpement (estimation de `theo-archi`, `avis-faisabilite.md` amendement du 2026-10-11), **sans ligne au planning** (son amendement le dit). **Depassement de la borne : accepte par l'utilisatrice**, mots cites en section 4 (2026-10-11), relayes par l'agent lanceur.
`camille-po` ecrit n'en trouver aucun fichier dans le depot (`DEC-0116` A.18 point 5) et ne sait pas de quelle borne ni de combien : **je ne le sais pas non plus.** Sur le bareme de `nadia-prof`, ces garde-fous coutent 0,0 : ils ne changent ni l'engage (8,25) ni le consomme (7,25). La borne que l'utilisatrice visait n'est donc pas
identifiee par les fichiers que j'ai lus. Ce depassement n'est **pas** une jurisprudence : l'ecart de s56 reste un ecart accorde pour s56 seulement (`DEC-0114` A.20), `DEC-0116` ne sert pas de precedent a s57.
**Cycle cause par la specification prealable ou par le contenu livre** (README section 4, s21) : le refus a pour cause racine une barre ecrite avant l'entree de catalogue par `nadia-prof` et dite tenue a tort (*"Mesure : tenu avec O1 et le meuble"*, fiche, motif du refus), donc une **specification prealable fausse**, pas un contenu faux
(six explications, `B6` et la regle 2 tenus). Le rejeu coute 0,5 ; il est a la `DEC` d'ouverture de s57.

---

## 12. Tableau des obstacles du sprint

Daily non journalise. Cette review est ecrite apres coup : les obstacles sont ceux que les ecrits du sprint nomment, classes et routes selon la table de ma casquette. Un obstacle n'est declare **Resolu** que si le fichier qui le tranche existe sur le disque (verifie par moi dans cette tache).

| # | Obstacle | Categorie | Route a | Decision liee | Statut a la cloture |
|---|---|---|---|---|---|
| 1 | Planning sans avis de faisabilite ni arbitrage prealable | Technique, pedagogie | `theo-archi`, `nadia-prof` | `avis-faisabilite.md`, `ARBITRAGE-PREALABLE-s56-*`, `026b2e17`, `c92ad8f7`, `1ae74a43` | **Resolu sur fichier** (section 3) |
| 2 | Capacite : B a 4,75, `PAR-2` sur le choix des oeuvres, 0,25 sans poste | Produit, capacite | `camille-po`, utilisatrice | `DEC-0116` A.4, A.5, A.13 | **Resolu sur fichier** ; les mots sont relayes |
| 3 | Deux postes d'`ADR` nes de l'avis, sans story | Technique | `camille-po`, `theo-archi` | `DEC-0116` A.10 ; backlog ; `6f8895a4`, `1de72f0f` | **Resolu sur fichier** |
| 4 | Critere de la marque et du plafond non ecrits a l'ouverture ; critere d'origine du plafond insatisfiable | Produit | `camille-po`, `nadia-prof` | `DEC-0116` A.9, A.11 ; backlog | **Resolu sur fichier** ; `F9` reste (obstacle 15) |
| 5 | `lea-dev` ecrit deux fois que le critere de la marque n'est pas ecrit alors qu'il est dans sa base (`rapport-lea-dev.md` sections 8 et 9.6) | Process | `sofia-sm` | `lessons.md` | **Resolu** : constat et regle ecrits ; aucun cout mesure (la marque est livree a 09:44, 22 min apres) |
| 6 | **Entree de catalogue 15 h 07 apres son echeance** ; 23 h 49 sans commit ; deux agents arretes par l'utilisatrice et remplaces, un watchdog sur `lea-dev` : **enonces du message de lancement, aucun fichier du depot ne les porte** hors `cloture-nadia.md` (*"l'instance B arretee"*) | Capacite | `sofia-sm` | reaffectation ecrite : `cloture-nadia.md` (reprend les postes de B et ceux de A) | **Constate.** Reaffectation resolue sur fichier ; **les deux agents arretes, le motif et la duree du watchdog ne sont ecrits nulle part** (`lessons.md`) |
| 7 | **Porte `Q7` avant l'entree de catalogue : insatisfiable telle qu'ecrite** (section 3) | Process | `sofia-sm` | aucune | **Constate**, de ma main ; pas d'amendement retrospectif du planning |
| 8 | Garde-fous `B2`, `B3`, `B5`, `B7`, `B8` sans ligne au planning, depassement de borne accepte par l'utilisatrice ; ecrit nulle part avant cette review | Capacite | `sofia-sm`, `camille-po` | aucune `DEC` ; citation datee en section 4 | **Constate et ecrit ici.** `DEC-0116` n'est pas amendee ; si l'utilisatrice veut un amendement, il est a `camille-po` |
| 9 | **`ART-CP-10` REFUSE** : phrase de `M4` sous le pli a 744 x 551 (626 a 656, pli a 551, 105 px), `RDO-CP-R1`, `RDO-CP-R2` | Pedagogie, produit | `nadia-prof`, `camille-po`, `theo-archi`, `ines-design`, `lea-dev` | fiche ; backlog `S56-ART-CP-10-REPRISE`, `S56-RDO-CP-R1`, `S56-RDO-CP-R2` | **Ouvert.** Priorite proposee par `camille-po` (premier poste d'`ART-CP-10` a s57) ; la `DEC` d'ouverture de s57 decide |
| 10 | **`Q-U3` : le paquet refuse est jouable au catalogue** ; retrait (D1) ou maintien (D2) | Produit | utilisatrice | `DEC-0116` A.17 ; story `S56-RETRAIT-RAPPROCHER-OEUVRES`, conditionnelle | **Ouvert, sans reponse.** Tant qu'elle n'a pas repondu, l'entree reste (`grep -c` rend 1) |
| 11 | `DEC-0116` `Proposee` : `Q4` (ordre des matieres) sans reponse a la cloture. **Corrige le 2026-10-12 : repondue le 2026-10-11, apres la cloture** | Produit | utilisatrice | `DEC-0116` 0.5, A.16 ; reponse : `DEC-0116` A.21 et A.22 | **Ouvert** : mots relayes (*"D2 ordre ok"*), non lus de sa main ; `camille-po` ne passe pas le statut (`DEC-0114` A.27 et `DEC-0115` A.22 lisent la meme situation de deux facons) ; l'ordre de `DEC-0115` section 3 est suivi pour s57 |
| 12 | `Q3` (deux instances de francais CE1 a partir de s57) et `Q6` (pli de `LIS-2`) sans reponse a la cloture. **Corrige le 2026-10-12 : repondues le 2026-10-11, apres la cloture** | Produit | utilisatrice | `DEC-0116` A.18 points 1 et 2 ; reponses : `DEC-0116` A.21, A.23, A.24 | **`Q6` : reponse ecrite**, `FRA-CM1-01` n'entre pas en s57, `A15` ni report ni dette. **`Q3` : reponse ecrite (*"D3 deux instances de nadia"*), ses prerequis restent ouverts** : accord de `nadia-prof` sur `PAR-2`, amendement d'`ADR-0065` (`S57-ADR-0065-DEUX-INSTANCES`, non engagee), budget `PAR-9`, compte des instances de s57 non tranche (A, A' et B font trois, `A10` serait due). Rattachement des mots aux questions : celui de l'agent lanceur |
| 13 | **Rouge de CI que `a3606b2f` corrige : cause non etablie** (A4) | Technique | `theo-archi`, utilisatrice | `avis-faisabilite.md` section 7 | **Ouvert** : mecanisme reproduit, declencheur non etabli (5.5) |
| 14 | **CI distante non lue** (A3), second report ; rien n'est pousse | Qualite | utilisatrice | aucune | **Ouvert.** Un troisieme report serait a acter par ecrit (`DEC-0116` A.18 point 7) |
| 15 | **Neuf constats de `rapport-qualite.md` (F1 a F9)** | Qualite | voir section 8 | aucune | **Ouverts**, routes ; aucun correctif ecrit |
| 16 | **`lecture-testabilite.md` absente** ; liste des verbes de `PRS-CE1-1` remplacee par une regle | Qualite | `yanis-qa` | aucune | **Ouvert** : la porte du planning n'a pas ete franchie |
| 17 | **Points P1 a P5 de `relecture-cloture.md`** | Redaction | `sofia-sm`, `camille-po`, `nadia-prof`, `ines-design`, `lea-dev` | voir section 9 | P3 **resolu** (retro) ; P1, P2, P4, P5 **ouverts** |
| 18 | **A10** (verification de s54, trois instances) non engagee | Pedagogie | `nadia-prof` | `DEC-0116` section 6 | **Ouvert, pas un report** : due avant toute `DEC` qui propose une troisieme instance |
| 19 | **`S54-OBS-SCI-CONSIGNE` : second report** ; criteres non ecrits | Produit | `camille-po`, `nadia-prof` | `DEC-0116` section 5, A.2, A.19 | **Ouvert** : a s57, placement ou abandon par un acte ecrit |
| 20 | Collision de port dans `JarRecreoIT` (*"Port 51729 was already in use"*), un premier `verify` de `lea-dev` | Qualite | `yanis-qa` | aucune | **Ouvert, non explique** : non reproduit en onze lancements, a six builds en parallele |
| 21 | `B6` (pas d'objet en volume en proposition) : aucune donnee ne le porte, refus de test motive par `theo-archi` | Pedagogie, technique | `nadia-prof` | `avis-faisabilite.md` amendement du 2026-10-11 | **Tenu par `nadia-prof` seule**, a chaque rejeu ; ce qui la ferait changer : un second objet en volume restaure |
| 22 | Formule de la forme 3 : `DEC-0114` ligne 31 au pluriel ; `DEC-0114` lignes 13, 52, 79 sont des mentions, pas des attributions (relecteur) ; refus de test de `theo-archi` (47 fichiers sur 65) | Redaction, produit | `camille-po`, utilisatrice | `DEC-0062` | **Ouvert.** Mon constat est dans la retro, section 4 |
| 23 | Rapport qualite sur un arbre anterieur au sommet (`80c06e64`) ; cote de la phrase de `M4` mesuree a l'oeil par `lea-dev` (indice y = 740 environ) et contredite par `getBoundingClientRect()` (626 a 676) | Qualite | `lea-dev` | `lessons.md` | **Resolu** : le delta est dit (5.3), la regle est ecrite ; la cote reelle est celle du releve `Q7` |

Rappel : un obstacle n'est clos que si le fichier de decision existe physiquement. **Resolus sur fichier** : 1, 2, 3, 4, 5, 23 ; 17 en partie (P3). **Constates** : 6, 7, 8. **Ouverts** : 9, 10, 11, 12, 13, 14, 15, 16, 17 (P1, P2, P4, P5), 18, 19, 20, 21, 22.

---

## 13. Done pedagogique - `nadia-prof`

Prononce par elle seule, fiche par fiche, sur un jar, Chrome sans tete pilote par son port de deverminage (`cloture-nadia.md` sections 1 et 4). **Je ne prononce aucun Done.**

| Ligne ou paquet | Verdict | Fiche |
|---|---|---|
| `ART-CP-10` (`cp-rapprocher-deux-oeuvres`) | **REFUSE** : phrase de `M4` entierement sous le pli a 744 x 551, 105 px manquent ; reserves `RDO-CP-R1`, `RDO-CP-R2` | `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` |
| `FRA-CE1-13` (`ce1-verbe-sujet`) | **VALIDE SOUS RESERVE** : `J1` a `J5` tenues, 24 items justes ; `VSJ-CE1-R1` (la place seule donne le sujet) | `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| `FRA-CE1-14` (`ACV-CE1-R1`, `-R2`) | **LEVEES** | `FICHE-bandit-manchot-accord-sujet-verbe-ce1.md` |
| `FRA-CE1-12` (`CLM-CE1-R1`) | **LEVEE** | `FICHE-bandit-manchot-classes-de-mots-ce1.md` |
| `EMC-CM1-03` (`EMC-CM1-R8`), `EMC-CM1-06` (`EMC-CM1-R9`) | **LEVEES** | fiches `respect-differences-cm1`, `principes-republique-cm1` |
| `EMC-CP-03` (`EXC-R16`, A11) | **LEVEE** ; en-tete de fiche complete | `FICHE-bandit-manchot-exclusion-cp.md` |

Une correction de son propre constat, ecrite par elle : *"Mesure : tenu avec O1 et le meuble"* etait faux (lecture du bas du contenu d'un vehicule statique, 622 puis 662, comme s'il disait ou commence la phrase ; sur le jar le document descend a 796).
Limites qu'elle declare : aucun enfant, aucun ecran de tablette, captures a densite 1 ; la barre de defilement peinte non essayee par elle ; les garde-fous `B2` a `B8` ni lus en detail ni mutes ; sur le paquet d'oeuvres, sept largeurs que `Q7` n'a pas mesurees et toute hauteur autre que 551 et 768.
**Ce dont elle reste la seule garde** (`cloture-nadia.md` section 5, fiche section 6) : la phrase de `M4`, l'indice et la croix au-dessus du pli, `RDO-CP-R2`, la regle 2, la visibilite des proprietes a 93-176 px, `B6`, la croix contre les anneaux, l'exactitude des explications, `VSJ-CE1-R1`, la verite des tests de classe de `-12`, qu'un indice d'exclusion ecarte un choix a l'ecran.
**Une CI verte ne couvre aucune de ces proprietes.**

---

## 14. Points signales, sans correction de ma part

| Code | Point | Source |
|---|---|---|
| S1 | **Trailers.** Onze commits hors fusion de `dfcec37c..116354be` portent `Claude Opus 5.5` (six `[THEO]`, cinq `[NAD]`), trente-cinq `Claude Sonnet 5.5` (`git show -s --format=%B`, boucle sur `git rev-list --no-merges`). La regle de `DEC-0110` 17.4 (modele reellement execute) s'applique ; `RAPPORT-tokens.md` donne le modele par agent. Aucun commit n'est reecrit | `squad/README.md` section 8 |
| S2 | `ARBITRAGE-s56-forme-art-cp-10.md` porte un bloc date qui corrige *"Mesure : tenu avec O1 et le meuble"* ; je ne reproduis pas la correction : elle est a `nadia-prof` | `cloture-nadia.md` section 1 |
| S3 | `lea-dev` ecrit (`rapport-lea-dev.md` 11.3) que la phrase de `M4` est *"91 px sous le pli"* a y = 642 ; la boite va de 626 a 656, il manque 105 px : deux grandeurs (milieu de ligne, bas de boite). Le verdict de refus cite 105 | fiche, motif ; `Q7-releve-gabarit-livre.md` |
| S4 | **Fenetre de tokens** fermee par l'heure de fin de cette review : les lancements de verification posterieurs ne sont pas dans la fenetre | `RAPPORT-tokens.md` |
| S5 | `rapport-qualite.md` section 6 : `S55-ART-CP-10-PLAFOND` apparait dans 0 fichier de test (la classe ne cite ni la story ni la decision) ; le critere se retrouve par le nom de la classe | `rapport-qualite.md` section 6 |

---

## 15. Questions a porter a l'utilisatrice

| Code | Question | Source |
|---|---|---|
| U1 | **`Q-U3` de s56** : retirer l'entree du paquet refuse (D1, recommandation de `camille-po`) ou la maintenir (D2) ? D2 demande un arbitrage ecrit, un amendement de `DEC-0055` (fenetre bornee a la cloture du sprint ou 48 heures) et un proprietaire de la correction nomme avant s57 | obstacle 10 ; `DEC-0116` A.17 |
| U2 | `Q4` : l'ordre des matieres. **Repondue le 2026-10-11, apres la cloture** (*"D2 ordre ok"*, relaye, `DEC-0116` A.21). `DEC-0116` reste `Proposee` (A.22) : question residuelle, ses mots lus de sa main, ou la `DEC` d'ouverture de s57 reprend l'ordre en forme 1 par rang | obstacle 11 |
| U3 | `Q3` (deux instances de francais CE1) et `Q6` (mesure de pli de `LIS-2`). **Repondues le 2026-10-11, apres la cloture** (*"D3 deux instances de nadia"*, *"D4 non"*, relayes, `DEC-0116` A.21, A.23, A.24). `Q6` close pour s57. Question residuelle de `Q3` : combien d'instances compte s57, avec la reprise d'`ART-CP-10` et `S54-OBS-SCI-CONSIGNE` ? | obstacle 12 |
| U4 | Pousser et lire la CI **sur un commit nomme, job par job** ; lire le journal du job `build` rouge d'avant `a3606b2f` pour trancher A4 ; si A3 n'est pas faite a s57, le troisieme report est a acter par ecrit | obstacles 13 et 14 |
| U5 | Le depassement des garde-fous : quelle borne, de combien ? `DEC-0116` ne le porte pas ; voulez-vous que `camille-po` l'amende ? | obstacle 8 |
| U6 | Les deux agents arretes, le motif et la duree du watchdog sur `lea-dev` : a ecrire, sinon rien dans le depot ne les dit | obstacle 6 |
| U7 | `DEC-0114` ligne 31 : *"recus de MASTER, sans source dans le depot"* vaut-il la formule fixe de `DEC-0062` ? Et la convention structurelle que `theo-archi` nomme comme condition de revision de son refus de test (bloc de citation dont la premiere ligne est la formule) | obstacle 22 |

---

## 16. Ce que cette review ne fait pas

- Elle ne prononce aucun Done, ne change aucun statut de matrice, de fiche ou de decision, ne touche ni catalogue, ni paquet, ni backlog, ni `DEC-0116`.
- Elle n'annonce pas la CI distante verte : elle ne l'a pas lue.
- Elle n'engage rien pour s57 et ne decide aucun des points qu'elle route.
- Elle ne retire pas l'entree de catalogue du paquet refuse : c'est `Q-U3`.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cette review.** Decisions du sprint, rapportees et non prises ici (liste dans `RAPPORT.md`) : `DEC-0116` (amendee cinq fois pendant le sprint, `A.1` a `A.20`, statut `Proposee` tenu ; un sixieme amendement apres la cloture, `A.21` a `A.25`, ajoute le 2026-10-12 : reponses sur `Q4`, `Q3`, `Q6`, statut inchange), `ADR-0024`, `ADR-0026`, `ADR-0104` (amendes, `Accepte`).
Un fichier de gouvernance est cree par cette cloture : `lessons.md` (racine), qui n'existait pas.
