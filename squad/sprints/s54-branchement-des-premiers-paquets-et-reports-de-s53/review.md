# Review - s54 - six paquets branches et audites, cinq lignes fermees (137/182), une ligne branchee sans fermer, quatre reports de s53 livres

- **Horodatage de debut** (`planning.md`) : 2026-10-07 20:27 (UTC+02:00), lu par `date` a l'ecriture du planning.
- **Horodatage de fin** : 2026-10-08 14:00 (UTC+02:00), lu par `date` a la fin de l'ecriture de ce fichier.
- **Duree effective** : 17 h 33 min d'horloge entre les deux horodatages. Pas de timebox : le sprint n'a pas ete continu (une nuit
  separe les derniers commits du 2026-10-07, 21:59, des premiers du 2026-10-08, 11:41).
- **Casquette** : `sofia-sm`, Scrum Master. Aucune story de developpement portee dans cette tache ; la story du journal de tokens
  a ete portee sous la casquette developpeuse dans une tache separee (`410aea2c`).
- **Arbre revu** : branche `s54-sofia-cloture`, issue de `round-two` a `731aac5a` (`git log -1 --format=%h`), `git status --short`
  vide avant ecriture, `bash outils/verifier-lignee.sh` sort 0, `git merge-base --is-ancestor round-two HEAD` reussit.
  `origin/round-two` est `52e2b0cd` : **60 commits (35 hors fusions, 25 fusions) ne sont pas pousses** (`git rev-list --count
  origin/round-two..HEAD`).
- **Provenance de cette review.** Elle est ecrite par une tache de cloture qui n'a pas assiste au sprint. Chaque ligne cite l'ecrit
  dont elle vient (fiche, backlog, `DEC`, rapport). Ce que je ne peux pas citer n'est pas ecrit. Je n'ai pas relance les audits de
  `nadia-prof` ni rejoue les mutations de `yanis-qa`.
- **Lignee des branches de s54.** Aucune fusion dans cette tache, donc aucune lignee de branche d'agent re-verifiee par moi ici.
  Les fusions de `marc-dev` (`4786248d`) et de `lea-dev` (`194e018c`) sont dans l'historique de `HEAD` ; `yanis-qa` a verifie que
  `5b759d1d` (`DEC-0114`) est ancetre des six commits de branchement (`rapport-qualite.md` section 3, C1).

---

## 1. L'increment livre contre l'objectif gele

Objectif gele (`planning.md`) : brancher cinq paquets de la session du 2026-10-07 et les presenter a `nadia-prof`, solder quatre
reports de s53, traiter les cinq corrections de redaction de s53, ecrire pour `ART-CP-10` le prix et le plafond. Mieux possible :
132/182 a 137/182. Ajouts decides en cours de sprint par `DEC-0114` (amendements A.1 a A.22) : le branchement d'un sixieme paquet
(`cp-arts-oeuvres-familles`, `ART-CP-10`, sans fermer la ligne) et une troisieme instance de `nadia-prof` pour l'auditer.

| Objectif | Etat a la cloture | Source |
|---|---|---|
| Cinq paquets branches et presentes a `nadia-prof` | **Tenu** : 5 entrees de `catalogue.yaml` (`grep -c "paquet: <nom>$"` rend 1 pour chacun, 0 a la base), 5 verdicts ecrits | `rapport-qualite.md` section 3 (C3), fiches section 2 ci-dessous |
| Cinq lignes fermees, 137/182 | **Tenu** : `FRA-CE1-18`, `FRA-CE1-19`, `SCI-CM1-15`, `SCI-CM1-18`, `EMC-CM1-02` `couvert-valide` | commande de la matrice, section 7 |
| Quatre reports de s53 (`CRP-CM1-R1` a `R3`, `S53-PASSE-TRANSVERSE`, `S51-OUTIL-JOURNAL-TOKENS`, `S53-REPORT-GRANDS-NOMBRES`) | **Livres** : `9618b974`, fichier `PASSE-TRANSVERSE-s54-*.md`, `410aea2c`, `8b15e3ba` | `rapport-qualite.md` section 4 |
| Cinq corrections de redaction de s53 | R1, R2, R5, `S53-REL-10` par amendement de `DEC-0112` ; R3 corrige par `nadia-prof` (`78da0199`) | section 4 ci-dessous, obstacle 10 |
| `ART-CP-10` : prix et plafond | **Tenu** : prix en tailles par `theo-archi`, ligne de plafond `Q6` par `nadia-prof` (`a3819bcd`) ; **plus** paquet branche et audite, ligne inchangee | `avis-faisabilite.md` T6, `arbitrage-nadia-c.md` section 1 |

---

## 2. Les stories, une par une

Colonnes : **Livre** = commit qui livre ; **Verdict** = prononce par `nadia-prof` dans la fiche citee (je ne prononce aucun Done) ;
**Reserve** = ce qui reste ouvert apres le verdict ; **Report** = nombre de sprints de report avant s54 selon `DEC-0114`/backlog.

| Story | Livre | Verdict de `nadia-prof` (source) | Reserve | Report |
|---|---|---|---|---|
| `S51-SCI-CORPS-CM1-RESERVES` (`CRP-CM1-R1` a `R3`, `SCI-CM1-10`) | `9618b974` (`marc-dev`), `grep -c "ne fait rien d'autre"` 1 a 0, 12 items avant et apres (`rapport-qualite.md` s.4) | **VALIDE, reserves levees** ; `SCI-CM1-10` reste `couvert-valide` (`FICHE-bandit-manchot-corps-cm1.md`, "Verdict du 2026-10-08") | aucune | s51, s52, s53 (`DEC-0112` s.5), livre a s54 |
| `S54-BR-FRA-CE1-18` (`ce1-futur`) | `95aa8f39` (`marc-dev`), conditions `K1`, `K2` appliquees (3/2 lignes du paquet) | **VALIDE** sans reserve (`FICHE-bandit-manchot-futur-ce1.md`, "Statut global") ; `FRA-CE1-18` `couvert-valide`, sans mention | aucune | aucun (neuf) |
| `S54-BR-FRA-CE1-19` (`ce1-passe-compose`) | `557ebd8a` (`marc-dev`), `K3`, `K4` (20/19 lignes) | **VALIDE SOUS RESERVE** le 2026-10-08, puis **`PCO-CE1-R1` LEVEE** au rejeu sur `7d32c1e3`, fiche **VALIDE** (`FICHE-bandit-manchot-passe-compose-ce1.md`, "Statut global" et "Bloc de rejeu") ; `FRA-CE1-19` `couvert-valide` | aucune ouverte ; `L3` (consigne qui nomme le passe compose) reste une recommandation sans condition (backlog `PCO-CE1-R1`) | aucun (neuf) |
| `S54-BR-SCI-CM1-15` (`cm1-sciences-terre-jour-nuit-saisons`) | `269ce5ce` (`lea-dev`), item 4 (7/5 lignes) | **VALIDE** sans reserve (`FICHE-bandit-manchot-jour-nuit-saisons-cm1.md`, "Verdict du 2026-10-08") ; les deux motifs du refus de s51 sont leves a sa barre ; `SCI-CM1-15` `couvert-valide` | aucune | s51 a s53 (backlog, "Trois sprints de report (s51 a s53) clos") |
| `S54-BR-SCI-CM1-18` (`cm1-sciences-environnement-activite-humaine`) | `fb42d5f1` (`lea-dev`), aucun changement du YAML | **VALIDE** sans reserve (`FICHE-bandit-manchot-environnement-cm1.md`, "Verdict du 2026-10-08") ; motif de l'item 11 leve ; `SCI-CM1-18` `couvert-valide` | aucune | s51 a s53 |
| `S54-BR-EMC-CM1-02` (`cm1-emc-atteinte-autrui`) | `ecf34b10` (`lea-dev`), items 2, 3, 9, 10 (4/4 lignes) | **VALIDE** sans reserve, premier passage (`FICHE-bandit-manchot-atteinte-autrui-cm1.md`, "Verdict du 2026-10-08") ; `EMC-CM1-02` `couvert-valide` **avec la mention de couverture exigee d'avance**, reproduite dans la ligne et dans `competences-cm1.md` (`rapport-qualite.md` s.3, C7) | aucune ; **`S54-EXC-CATEGORIE`** nee de cette lecture (voir ligne `EXC-R15`) | aucun |
| `S54-BR-ART-CP-10` (`cp-arts-oeuvres-familles`, `DEGRADE`) | `920e19e2` (`lea-dev`, 12:09), puis corrections decidees par l'instance C `9a0011b1` (12:20, 21/17 lignes du paquet), toutes deux avant la fiche `d6660a70` (12:42) | **VALIDE SOUS RESERVE** (`FICHE-bandit-manchot-familles-d-oeuvres-cp.md`, "Statut global", commit `d6660a70`) ; **aucun mouvement de ligne : `ART-CP-10` reste `non couvert`**, le verdict "ne vaut pour aucune ligne de la matrice" (fiche, section 3) | trois reserves non bloquantes, backlog `S54-ART-RES-R1` a `R3`, non engagees | `ART-CP-10` : quatre sprints hors perimetre (s50 a s53) ; **s54 n'est pas un cinquieme report** (`DEC-0114` A.1) |
| `S54-ART-CP-10-PRIX` | prix : `avis-faisabilite.md` T6, amendement du 2026-10-08 (`3d75a284` le confirme) ; plafond : ligne `Q6` de `arbitrage-nadia-c.md` section 1, confirmee par C, ecrite par A (`a3819bcd`) | pas un verdict : *"Rien sur `ART-CP-10`, ni `couvert-valide` ni `en cours`"* (`arbitrage-nadia-c.md`, Resume) | **la decision de financer la mecanique n'est pas prise** (`DEC-0114` s.6 point 4) | hors perimetre depuis s50 |
| `S53-REPORT-GRANDS-NOMBRES` | `8b15e3ba` (`lea-dev`) : 14 `enonce`, 2 constantes, `26 -> 12` et `516 -> 502` en un commit (`rapport-qualite.md` s.4) | hors porte de `nadia-prof` (`Q5`, `DEC-0114` A.23 point 4) ; relecture de ponctuation par `relecteur-pedagogique` : *tenue*, 14 sur 14 (`FICHE-bandit-manchot-grands-nombres-cm1.md`, "Passage du 2026-10-08") | 12 espaces ordinaires restent dans des `indice` : story `S54-PONCT-GN-INDICES` | s53, livre a s54 |
| `S51-OUTIL-JOURNAL-TOKENS` | `410aea2c` (`sofia-sm`, casquette developpeuse) ; `outils/rapport-tokens-journal-test.py`, 7 tests (`rapport-qualite.md` s.4) | sans objet | le defaut de `main` sans `--sprint` modifie encore des fichiers suivis (obstacle 16) | s51, s53 (`DEC-0112` s.5), livre a s54 |
| `S53-PASSE-TRANSVERSE` (`PAR-4`) | `squad/conformite/PASSE-TRANSVERSE-s54-portee-depot-apres-les-branchements-de-francais-ce1.md`, 7 lignes `D1` a `D7`, prix 0,5 (`rapport-qualite.md` s.4) | sans objet (jugement de `nadia-prof`) | `S54-OBS-SCI-CONSIGNE` ouverte ; `S54-OBS-ART-ALT` **retiree** par `nadia-prof` (le texte est celui que `CON-5` demande) | s53, livre a s54 |
| `S52-SYC-CE1-R1` + item 16 de `FRA-CE1-25` | `915de585` (`marc-dev`) | **`SYC-CE1-R1` levee**, fiche **VALIDE** ; `FRA-CE1-25` reste `couvert-valide` (`FICHE-bandit-manchot-synonymes-contraires-ce1.md`, "Rejeu du 2026-10-08") | aucune | s52, s53 ; troisieme report interdit, livre |
| `MUL-CE1-R1` | aucun (non engagee) | sans objet | la reserve reste ecrite dans `FICHE-bandit-manchot-sens-multiplication-ce1.md` s.6 | **retiree a s54** (premier report s52, second s53, retrait s54, `DEC-0114` s.3) |
| `S53-REPORT-REDACTION` | R1, R2, `S53-REL-10` : amendements de `DEC-0112` ; R5 : erratum au backlog (`rapport-qualite.md` s.4) ; **R3 : `nadia-prof`, `78da0199`** | sans objet | aucune : `grep -c "six fichiers"` rend 0 sur l'arbitrage de s53 a `HEAD` (relance par moi) | s53, livre a s54 |
| `PCO-CE1-R1` (ouverte et levee le 2026-10-08) | `7d32c1e3` (`marc-dev`), 8 lignes d'indice ; comptes de `grep` : 19 et 2 (relances par moi sur `HEAD`) | **LEVEE** au rejeu (fiche passe-compose, "Bloc de rejeu"), prix reel 0,25 | aucune | aucun |
| `S54-EXC-CATEGORIE` / **`EXC-R15`** | non engagee | reserve neuve non bloquante `EXC-R15` sur `cp-emc-exclusion` ; **la ligne `EMC-CP-03` n'est pas rouverte** (`FICHE-bandit-manchot-exclusion-cp.md`, bloc du 2026-10-08) ; `EXC-R15` portee dans la cellule de la ligne de matrice (`a46220ab`) | ouverte, criteres ecrits mot pour mot par `nadia-prof` (backlog), 0,25 de rejeu | premier (aucun) |
| `S54-OBS-SCI-CONSIGNE` | non engagee | observation de la passe `PAR-4`, corrigee par `nadia-prof` (12 enonces) | ouverte, criteres non ecrits | aucun |
| `S54-ART-RES-R1`, `-R2`, `-R3` | non engagees | reserves de l'instance C sur `cp-arts-oeuvres-familles` | ouvertes, criteres ecrits (backlog) | aucun (ouvertes le jour du verdict) |
| `S54-PONCT-GN-INDICES`, `S54-ASCII-APOSTROPHES-TESTS` | non engagees | sans objet (transverses, signalees par `relecteur-pedagogique`) | ouvertes ; la seconde est a router a `theo-archi` | aucun |

Engagements : chaque story du tableau "Stories retenues" du planning et chaque story ajoutee par `DEC-0114` (`S54-BR-ART-CP-10`, instance C)
est livree dans le tableau ci-dessus, **sauf `MUL-CE1-R1`, retiree par `DEC-0114` section 3**. Aucune story engagee n'est reportee.
Aucun REFUSE n'est prononce dans s54 (verdicts ci-dessus).

---

## 3. Arbitrages de l'utilisatrice, tels que relayes

**Aucun de ces arbitrages n'a ses mots dans le depot** : chacun est cite par `DEC-0114` comme message d'un agent, forme 3 de `DEC-0062`
(*fonde l'ordre dans lequel le travail se fait, jamais sa raison*). Je les relaie dans la meme forme.

| Question | Arbitrage relaye (formule du message d'agent, `DEC-0114`) | Source | Effet ecrit |
|---|---|---|---|
| `Q6` | *"on BRANCHE LE PAQUET DEGRADE sans fermer la ligne"* | `DEC-0114` A.0 point 1 | A.1 ; `S54-BR-ART-CP-10` ; `ART-CP-10` reste `non couvert` |
| `Q9` | *"la CI est verte sur GitHub pour round-two"* | `DEC-0114` A.0 point 2, A.2 | **non lu sur la CI, aucun commit nomme** ; `origin/round-two` est `52e2b0cd`, soit le sommet d'avant s54 : le verdict ne dit rien de ce qui est dans cette review |
| `Q10` | *"ordre propose par sofia-sm retenu : G1/G2, puis G6/G5, puis arbitrage de nadia-prof sur G7 a G11, puis G3/G4"*, `G8` d'abord ecartee | `DEC-0114` A.0 point 3, A.3 | section 7.1 amendee ; `DEC-0114` reste `Proposee` pour cette seule raison (section 0.4) |
| `Q11` | une troisieme instance (C) pour auditer `cp-arts-oeuvres-familles` | `DEC-0114` A.9, A.10 | instance C ouverte, un paquet |
| `Q12` | elle *"accepte l'ecart"* et demande d'ecrire la borne de `PAR-9` a 9,5 | `DEC-0114` A.17, A.18 | borne de s54 : 9,5 (A 4,0, B 4,0, C 1,5), pour s54 seulement (A.20) |

**Ecart de borne de `PAR-9` non consomme.** Consommation reelle (A.25 corrige par A.26, tarifs de `nadia-prof`) : A **3,75**, B **3,25**,
C **1,0** (audit 1,0, rejeu 0,5 non consomme), **somme 8,0**, borne 8,0 de `PAR-8`. L'ecart de +1,5 accorde par A.18 n'a pas ete consomme
(0,0 sur 1,5). Sources : `ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md` (tableau "Consommation reelle de l'instance A"),
`ARBITRAGE-PREALABLE-s54-instance-b-sciences-emc-cm1.md` (section 6 : 3,25 alloues), `arbitrage-nadia-c.md` section 2, `DEC-0114` A.26.
Les unites sont celles du bareme de `nadia-prof`, pas des tokens. **A.25 portait 3,25 pour A (chiffre d'avant les complements de
cloture) : corrige par A.26, texte de A.25 non reecrit.** Le chiffre de B (3,25) est un total alloue ; l'arbitrage de B n'ecrit pas de
tableau de consommation de cloture ; sa marge de 0,5 nommee n'est pas dans les 3,25, et aucun REFUSE n'est prononce dans B.

---

## 4. Done technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. **Rien n'est pousse** : la CI
n'a rendu aucun verdict sur les commits de s54, et le seul verdict relaye (`Q9`) ne nomme ni job ni commit.

### 4.1 Ce que `yanis-qa` a mesure, et sur quel arbre

`rapport-qualite.md`, arbre **`db69bbc6`**, les quatre jobs lances (voir ce rapport, section 1) : `prefixes` vert, `chemins` vert,
`outils` vert (10 juges, 10 codes 0), `build` vert (`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, `BUILD SUCCESS`,
**2216 tests** : domain 120, application 17, adapter-web 401, adapter-content 1360, bootstrap 312 et 6 d'integration ; 0 echec,
0 erreur, 0 ignore ; pas `package` lance aussi, `BUILD SUCCESS`, `recreo.jar` present). Delta **+42 sur 2174** (base de s53), tout dans `adapter-content`,
quatre classes de test de paquet (13 + 13 + 10 + 6). **Reserves ecrites par lui** : CI distante non lue, fins de ligne (`autocrlf=true`)
une seule configuration, branche `BASE` de `prefixes` non exercee, isolation de C non reconstituee, criteres non automatisables
(`TRV-CE1-5`, `EMC-9`, rejeu `SYC-CE1-R1`, passe `PAR-4`, motifs de refus de sciences, titre de l'entree d'arts).

### 4.2 Ce qui a change depuis `db69bbc6` (`git diff --name-only db69bbc6 HEAD`)

16 fichiers, **tous sous `docs/` ou `squad/`** : `docs/backlog/backlog.md`, l'arbitrage de s53 (correction de R3), l'arbitrage prealable de
l'instance A, quatre fiches de conformite, la passe transverse, `REGISTRE-identifiants.md`, `DEC-0114`, `DEC-index.md`,
`MATRICE-couverture.md`, `avis-faisabilite.md`, `confirmation-archunit.md`, `planning.md`, `rapport-qualite.md`.
**Aucun fichier de `contenu/`, de `catalogue.yaml`, de Java ou d'`outils/` n'a change** (`git diff --name-only db69bbc6 HEAD | grep -vE
'^(squad|docs)/'` rend vide). La correction de 8 lignes de contenu (`7d32c1e3`, `PCO-CE1-R1`) est **anterieure** a `db69bbc6`
(`git merge-base --is-ancestor 7d32c1e3 db69bbc6` reussit) : le rapport de `yanis-qa` la couvre. Elle est confirmee par `grep` sur
`HEAD` (19 et 2). Une partie de `squad/` est lue par les tests ArchUnit de `com.recreo.archi`, d'ou la relance ci-dessous.

### 4.3 Ce que j'ai lance sur le sommet de cette review

Tous les chiffres de cette section sont de moi, sur l'arbre `731aac5a` plus les fichiers de cloture de s54, pas repris d'un rapport.

| Job | Commande | Resultat |
|---|---|---|
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh` ; `bash .github/scripts/commit-atteignable-test.sh` ; `bash .github/scripts/commit-atteignable.sh origin/round-two` ; `git log --no-merges --format=%s origin/round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | **vert**, codes 0, 35 sujets hors fusions (`Tous les commits portent un prefixe d'agent connu.`). Ce n'est pas la plage `github.event.before..GITHUB_SHA` d'un push reel : la branche `BASE` du workflow n'est pas exercee. Le sujet du commit de cloture est verifie apres son commit (compte rendu de la tache) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh` ; `git ls-files -co --exclude-standard -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | **vert**, codes 0, fichiers de cloture compris (avant les dernieres retouches de ce fichier ; relance apres, dans le compte rendu de la tache) |
| `outils` | boucle du workflow (`outils/*-test.sh` 5, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 3), `python3` repond (3.14.0) | **vert, 10 juges, 10 codes 0**, aucun motif vide |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, sans `-pl` | **`BUILD SUCCESS`**, 4 min 53 s, code 0, six modules `SUCCESS` ; l'enveloppe rend `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10` et la machine virtuelle des tests rapporte `using Java 21.0.10` (20 occurrences, une seule valeur ; propriete `java.version` des rapports XML : une seule valeur, `21.0.10`) |
| `build` (pas de package) | **non relance par moi** | `yanis-qa` l'a lance sur `db69bbc6` (`BUILD SUCCESS`, `recreo.jar` present) ; aucun fichier de code, de contenu ni de POM ne differe depuis (section 4.2) |

Arbre de ces mesures : `731aac5a` plus `review.md`, `RAPPORT.md`, `retro.md`, `proposition-s55.md`, `RAPPORT-tokens.md` et `squad/rapports/JOURNAL-tokens.md` modifie. Les tests `com.recreo.archi` lisent `squad/` : le `clean verify` ci-dessus a tourne avec ces fichiers dans leur etat
d'avant les dernieres retouches de texte ; **les tests `com.recreo.archi` sont relances apres la derniere ecriture** et leur resultat est rendu dans le compte rendu de la tache, pas dans ce fichier.

**Total des tests, etabli sur l'arbre fusionne : 2216** (domain 120, application 17, adapter-web 401, adapter-content 1360, bootstrap 312 Surefire et 6 Failsafe ; 0 echec, 0 erreur, 0 ignore ; somme des attributs `tests` des rapports XML recoupee par les lignes `Tests run` de fin de module).
Origine de l'ecart avec les annonces : `theo-archi` annoncait *"le total ne bouge pas"* pour cinq entrees de catalogue seules (`avis-faisabilite.md`
section 1, passage 2 : 2174) ; le total bouge de +42 parce que les developpeurs ont ecrit un test de paquet pour quatre des six
branchements (`rapport-qualite.md` section 2). Le meme total (2216) est mesure par `yanis-qa` et `theo-archi` sur `db69bbc6` et par moi sur le sommet.

---

## 5. Confirmation ArchUnit - `theo-archi`

`confirmation-archunit.md`, arbre `db69bbc6` : 229 tests `com.recreo.archi` verts, **37 `static final ArchRule` et 36 `@ArchTest`** hors
contre-exemples (39 et 36 avec), **identique a s53**, diff du sprint sans ligne de `src/main/java`, sans POM, sans workflow : **aucune
frontiere touchee**. `ADR-0104` est `Accepte` depuis le 2026-10-07 (`4e8ae7b4`), regle (13) verte. `T6` (prix) et `T7` (regle du push) n'appellent
aucune regle ArchUnit. La regle du push est ecrite dans `squad/README.md` section 9 (+12 lignes, `4e8ae7b4`) et **n'est pas executable** :
aucun crochet de pre-push (`avis-faisabilite.md` T7).

## 6. Relecture - `relecteur-pedagogique`

**Aucun fichier de relecture de cloture n'existe dans le dossier de sprint** (s53 en avait un, `relecture-cloture.md`). Les corrections du
relecteur sont dans son seul commit `b5779885` : (1) annotation `Accepte` d'`ADR-0104` aux endroits de `DEC-0114`, du backlog, de
`MATRICE-couverture.md` et de `planning.md` qui disaient `Propose` ; (2) annotation de `PCO-CE1-R1` au backlog (livree par `7d32c1e3`) ;
(3) passage de ponctuation de `S53-REPORT-GRANDS-NOMBRES`, trace dans la fiche : *tenu*, 14 sur 14, aucune faute d'orthographe ni d'accent
sur les 14 enonces ; (4) deux stories signalees (`S54-PONCT-GN-INDICES`, `S54-ASCII-APOSTROPHES-TESTS`, creees au backlog par `camille-po`
dans `a46220ab`). Le relecteur a modifie `planning.md` (trois annotations, 6 lignes) : le texte d'origine du planning reste lisible a cote.
**Une liste de corrections au format du process n'est pas ecrite** : obstacle 17.

---

## 7. Couverture du programme : 137/182 (75,27 %)

Commande de la "Methode du compte" de `MATRICE-couverture.md` (l'`awk` de la section de s51), **relancee par moi sur `git show <commit>:squad/programme/MATRICE-couverture.md`** :

| Arbre | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'etat` | Lignes | Denominateur | Taux |
|---|---|---|---|---|---|---|---|
| `52e2b0cd` (base) | 132 | 4 | 46 | 76 | 258 | 182 | 72,53 % |
| `HEAD` (`731aac5a`) | **137** | 4 | **41** | 76 | 258 | 182 | **75,27 %** |

Perimetre : les 258 lignes de donnees, aucune mesure partielle. Meme chiffre que `yanis-qa` (`rapport-qualite.md` s.3, C7, arbre `db69bbc6`),
que `DEC-0114` A.23 et que la matrice. Les cinq lignes passees a `couvert-valide` sont celles de la section 2. `ART-CP-10` reste `non couvert`.

---

## 8. Tableau des obstacles du sprint

Daily non journalise. **Cette review est ecrite apres coup par une tache de cloture** : les obstacles ci-dessous sont ceux que les
ecrits du sprint nomment, classes et routes selon la table de ma casquette. Aucun n'est une observation personnelle. Un obstacle n'est
declare **Resolu** que si le fichier qui le tranche existe sur le disque (verifie par moi dans cette tache).

| # | Obstacle | Categorie | Route a | Decision liee | Statut a la cloture |
|---|---|---|---|---|---|
| 1 | **Perimetre sans acte d'ouverture** : le planning reposait sur un message d'agent ; aucune `DEC` d'ouverture n'existait (`planning.md`, "Provenance", R1, Q1) | Produit / process | `camille-po` | `DEC-0114` (`5b759d1d`, 2026-10-07 21:02) | **Resolu sur fichier.** Statut de `DEC-0114` : **`Proposee`** (section 0.4) tant que l'ordre des matieres n'a pour source que la forme 3 : voir obstacle 14 |
| 2 | **Trois engagements ajoutes en cours de sprint** par l'utilisatrice (`Q6` branchement d'`ART-CP-10`, `Q11` instance C, `Q12` borne 9,5), plus un depassement de capacite : A.5 chiffrait 4,5 sur A ou B (`DEC-0114` A.5) | Produit / capacite | `camille-po`, utilisatrice | `DEC-0114` A.1 a A.22 | **Resolu sur fichier** (amendements ecrits, un par arbitrage) ; consommation reelle 8,0 sur 9,5, ecart non consomme (section 3) |
| 3 | **`ART-CP-10`** : cinquieme sprint de report si non engagee ; paquet `DEGRADE` qui ne ferme pas la ligne ; un paquet au catalogue sans verdict est la situation de `DEC-0009` (`DEC-0114` A.5) | Produit / pedagogie | utilisatrice, `nadia-prof`, `camille-po` | `DEC-0114` A.1, A.10, A.25 ; `FICHE-bandit-manchot-familles-d-oeuvres-cp.md` | **Resolu pour le branchement et l'audit** (VALIDE SOUS RESERVE, ligne inchangee). **Ouvert** : trois reserves non engagees (`S54-ART-RES-R1` a `R3`) et la decision de financer la mecanique, a la `DEC` d'ouverture de s55 |
| 4 | **Statut d'`ADR-0104` contradictoire** : lancement, planning et `DEC-0114` disaient `Propose` ; le fichier de l'`ADR` dit `Accepte le 2026-10-07` (`4e8ae7b4`) (`confirmation-archunit.md` s.4) | Technique / redaction | `theo-archi`, `relecteur-pedagogique` | `ADR-0104` (statut), `ADR-index.md` | **Resolu sur fichier** : `ADR-0104` existe avec le statut ; annotations de `b5779885`. **Question residuelle : section 11** |
| 5 | **Critere `TRV-CE1-5` mal cite** : le planning lui prete *"aucun indice ne renvoie a l'oreille"*, le texte de s48 ne le dit pas (`lecture-testabilite.md` s.5 point 3) ; la barre appliquee a `FRA-CE1-19` a ete relue par `nadia-prof` elle-meme (indice `N1`) | Pedagogie / redaction | `nadia-prof`, `camille-po` | arbitrage prealable de A, section 12 ; backlog, "Correction du critere" | **Resolu sur fichier** (fiche passe-compose section 4, backlog) |
| 6 | **`TRV-CE1-5` et `EMC-9` : aucun resultat ecrit des developpeurs.** Le critere demandait un resultat *"dans son rapport"* ; il n'y a pas de rapport de `marc-dev` ni de `lea-dev` dans le dossier ; `git log --grep="TRV-CE1-5"` ne rend aucun des deux commits ; le message de `ecf34b10` dit *"EMC-9 sur la forme seulement"* (`rapport-qualite.md` s.4) | Qualite / process | `sofia-sm` (capacite des devs), `yanis-qa` | aucune | **Ouvert, constate.** `nadia-prof` a applique les deux regles dans ses fiches ; je ne conclus pas que la relecture n'a pas eu lieu, aucun fichier ne la porte |
| 7 | **Motifs de refus de sciences sans test** : le distracteur "le printemps" remis sur l'item 11 de Terre laisse vertes `PaquetCm1SciencesTerreTest` (10 tests) et la suite `adapter-content,bootstrap` (1360 + 312). Je n'ai mute ni l'item 4 ni l'item 11 de l'environnement (`rapport-qualite.md` s.4, s.5) | Qualite / test | `yanis-qa`, `theo-archi` | aucune | **Ouvert, non engage.** Le motif tient a `nadia-prof` seule (backlog le dit) : action A5 de la retro |
| 8 | **Isolation de l'instance C non verifiee** : `c5c2d50f` et `d6660a70` ne touchent chacun qu'un fichier ; la branche `s54-nadia-c` est fusionnee, son diff complet et les noms de worktree et de scratchpad ne sont pas reconstitues (`rapport-qualite.md` s.4, critere 7) | Qualite | `yanis-qa` | `DEC-0114` A.14 | **Ouvert.** Aucun conflit d'ecriture observe dans les fichiers cites |
| 9 | **Deux fichiers `~/.m2/toolchains.xml` sur le poste de `yanis-qa`** : celui du `HOME` du shell designe un JDK 25 sous l'entree `21`, celui que Maven lit designe 21.0.10. **Le refus de `outils/mvn-jdk21.sh` est verifie** : code 1, Maven non lance, message *"refus de lancer Maven"* (`rapport-qualite.md` s.6, `ADR-0071`). Observation non corrigee : avec `JAVA_TOOL_OPTIONS`, la version imprimee devient `inconnue` au lieu de `25.0.2`, le refus tient | Technique / poste | `theo-archi` (enveloppe), utilisatrice (poste) | `ADR-0071` | **Ouvert, hors depot** pour le poste ; **ouvert** pour la ligne `Picked up JAVA_TOOL_OPTIONS` (action A6) |
| 10 | **R3 de la relecture de s53** : *"six fichiers"* dans l'arbitrage de s53, section 11 ; `yanis-qa` le trouve **non tenu** a `db69bbc6` (ligne 315) (`rapport-qualite.md` s.4) | Redaction / chiffre | `nadia-prof` | arbitrage de s53 section 11 ; `78da0199` | **Resolu sur fichier** : correction par `nadia-prof` apres le rapport ; `grep -c "six fichiers"` rend 0 sur `HEAD` (relance par moi). `rapport-qualite.md` n'est pas reecrit |
| 11 | **Textes qui disaient le contraire des fiches** : la cellule de matrice d'`ART-CP-10` (*"aucun verdict n'est prononce"*), le backlog (fiche inexistante ; `PCO-CE1-R1` `OUVERTE, non engagee`) (`rapport-qualite.md` s.7) | Redaction | `camille-po`, `relecteur-pedagogique` | `c1763f41`, `b5779885`, `a46220ab` | **Resolu sur fichier** : la cellule d'`ART-CP-10` ne contient plus la phrase (`grep` relance), le backlog porte la levee de `PCO-CE1-R1` |
| 12 | **`PCO-CE1-R1` livree par `7d32c1e3` le jour de son ouverture, sans engagement ni `DEC`** (`backlog.md`, annotation du relecteur). Le texte de `DEC-0114` ne cite pas `7d32c1e3` | Process | `camille-po`, `sofia-sm` | `DEC-0114` A.23 point 5 (ouverte, non engagee) ; fiche (levee) | **Constate.** La reserve est close par la fiche et le backlog (`a46220ab`). La regle de la retro de s53 (un engagement ajoute apres planning s'acte par un amendement avant le worktree) n'a pas ete suivie pour ce geste : voir retro |
| 13 | **Ecart de compte "45 puis 40" du planning contre "47 puis 41"** de `yanis-qa` (`rapport-qualite.md` s.3, C3), *"que je n'explique pas"* | Qualite / chiffre | `sofia-sm` | aucune | **Explique par moi, a verifier par `yanis-qa`.** Les 47 fichiers sans entree a la base sont les 45 de la session plus les deux paquets refuses a s51 (`cm1-sciences-terre.yaml`, `cm1-sciences-activite-humaine.yaml`), qui restent dans `contenu/` sans entree (`avis-faisabilite.md` s.2 ; `ls` et `grep -c` relances : fichiers presents, 0 entree). Apres six branchements : 45 - 6 + 2 = 41. Le planning disait 40 parce qu'il comptait cinq branchements et non six |
| 14 | **`DEC-0114` `Proposee`** : l'ordre des matieres au-dela de s54 (`Q10`) n'a que la forme 3 comme source, `Q12` arrive par le meme canal (`DEC-0114` A.21) | Produit | utilisatrice, via `sofia-sm` | `DEC-0114` s.0.4, A.22 | **Ouvert.** Ce qui la fait passer a `Acceptee` : ses mots ecrits en forme 2 avec leur date, ou la `DEC` d'ouverture de s55 qui reprend l'ordre sous son motif |
| 15 | **Trailer `Co-Authored-By` des commits `[NAD]` et `[THEO]` : "Opus 5.5" au lieu de "Sonnet 5.5"** | Process / redaction | utilisatrice | `DEC-0110` 17.4 | **Ouvert, a trancher par l'utilisatrice.** Faits en section 10, F6 |
| 16 | **Defaut de `outils/rapport-tokens.py` sans `--sprint`** : `yanis-qa` rejoue l'outil sans `--sprint` : il ne modifie plus aucun `RAPPORT-tokens.md` de s22 a s27 (critere tenu), mais **modifie cinq autres fichiers suivis** (`JOURNAL-tokens.md`, rapports de s32, s37, s38, s40) et cree un rapport de s54 (`rapport-qualite.md` s.4). Les bornes "s22 a s27" du critere etaient fausses (23 a 28, `lecture-testabilite.md` s.5 point 1) | Technique / outil | `theo-archi`, `sofia-sm` (dev) | `DEC-0112` 5.5 | **Ouvert.** J'ai lance `--sprint s54` explicite, comme le planning le demande ; le journal est regenere avec les sections s51 a s54 (section 13) |
| 17 | **Aucun fichier de relecture de cloture dans le dossier de sprint** : les corrections du relecteur ne sont lisibles que dans `b5779885` (section 6) | Redaction / process | `relecteur-pedagogique` | aucune | **Ouvert.** Je n'ecris pas le fichier a sa place |
| 18 | **Condition (ii) de `nadia-prof` sur la borne a trois instances** : sa verification de s52 contre les formes (a), (b), (c) lui est routee depuis s52 (`review.md` de s52, obstacle 16 ; de s53, obstacle 15) et n'est pas faite (`arbitrage-nadia-c.md` s.3.1 point 2, s.3.4 point 3) | Pedagogie / capacite | `nadia-prof` | arbitrage de s46 section 5 | **Ouvert depuis s52.** Troisieme sprint |
| 19 | **Tronc distant** : 60 commits non pousses ; `Q9` relaye (CI verte) sans commit nomme | Qualite / etat du tronc | utilisatrice (le push) | `DEC-0114` A.2 | **Ouvert.** Je ne pousse pas (consigne de la tache) |
| 20 | **Actions de la retro de s53** : A1 (`DEC-0114`), A3, A4, A5, A6, A7, A8, A10 traitees ; **A2 (pousser s53) non traitee** ; **A9 non traitee** : `relecture-p11.md` n'a pas de commit apres `863cad06` (s53, 2026-10-07 13:55), et `DEC-0114` section 8 dit qu'il *"reste a son auteur"* ; A11 non reverifiee par moi | Divers | leurs proprietaires | retro de s53 section 4 | **Traite sauf A2, A9 et A11.** Detail dans `retro.md` |
| 21 | **Capacite des developpeurs** : les commits de livraison portent les auteurs du planning (`[MARC]` : `9618b974`, `95aa8f39`, `557ebd8a`, `915de585`, `7d32c1e3` ; `[LEA]` : `fb42d5f1`, `269ce5ce`, `ecf34b10`, `8b15e3ba`, `920e19e2`, `9a0011b1`) ; aucune reaffectation n'est ecrite | Capacite | `sofia-sm` | - | **Aucun obstacle de capacite ecrit.** Je ne dis rien des worktrees ouverts en parallele : aucun ecrit ne le dit |
| 22 | **`planning.md` n'a jamais ete amende pour `S54-BR-ART-CP-10` ni pour l'instance C** (`grep -c "instance C\|S54-BR-ART-CP-10" planning.md` rend 0). L'affectation (`lea-dev`, A.15 : *"proposition, affectation a `sofia-sm`"*) et l'isolation de C (A.14 : *"leurs noms et leur mecanique sont a `sofia-sm`"*) ne sont ecrites nulle part comme affectation nominative du planning ; les noms de branche (`s54-nadia-c`) ne se lisent que dans l'arbitrage de C et la fiche. C'est ce qui rend l'isolation non verifiable par `yanis-qa` (obstacle 8). La retro de s53 demandait cet amendement *avant l'ouverture du premier worktree concerne* | Process / capacite | `sofia-sm` | `planning.md` s54 ("Lecon de la retro de s53") ; `DEC-0114` A.14, A.15 | **Ouvert, constate.** Je n'ecris pas d'amendement retrospectif : un amendement date apres coup serait une ceremonie fabriquee. Dit dans `retro.md` |

Rappel : un obstacle n'est clos que si le fichier de decision existe physiquement. Resolus sur fichier existant : 1 (partiel), 2, 4,
5, 10, 11 et la partie branchement/audit de 3. Constates ou ouverts : 6, 7, 8, 9, 12, 14, 15, 16, 17, 18, 19, 20 (A2, A9, A11), 22.

---

## 9. Done pedagogique - `nadia-prof`

Prononce par elle seule, fiche par fiche (section 2) : **`FRA-CE1-18` VALIDE, `FRA-CE1-19` VALIDE SOUS RESERVE puis `PCO-CE1-R1` levee
(fiche VALIDE), `SCI-CM1-15` VALIDE, `SCI-CM1-18` VALIDE, `EMC-CM1-02` VALIDE avec la mention de couverture, `cp-arts-oeuvres-familles`
VALIDE SOUS RESERVE sans mouvement de ligne (`ART-CP-10` reste `non couvert`), `CRP-CM1-R1` a `R3` et
`SYC-CE1-R1` levees.** `SYC-CE1-R1` (rejeu) et la passe `PAR-4` sont dans les fiches et le fichier de passe. Aucun Done n'est prononce
par cette review.

---

## 10. Points signales, sans correction de ma part

| Code | Point | Source | Qui le lit |
|---|---|---|---|
| F1 | **R3** : `rapport-qualite.md` dit non tenu (ligne 315) ; `nadia-prof` l'a corrige apres, `grep` rend 0 sur `HEAD`. Le rapport n'est pas reecrit | `rapport-qualite.md` s.4 ; `78da0199` | `yanis-qa` si une mesure doit etre refaite |
| F2 | **Criteres `TRV-CE1-5` et `EMC-9`** : aucun resultat ecrit des developpeurs | `rapport-qualite.md` s.4, s.8 ("Non evidencie") | `marc-dev`, `lea-dev` |
| F3 | **Motifs de refus de sciences non gardes par un test**, mutation "le printemps" restee verte | `rapport-qualite.md` s.4, s.5 | `yanis-qa`, `theo-archi` |
| F4 | **Isolation de l'instance C** : non verifiee | `rapport-qualite.md` s.4, critere 7 | `yanis-qa` |
| F5 | **Toolchain `~/.m2` double** sur le poste de `yanis-qa` ; refus de `mvn-jdk21.sh` verifie (code 1, Maven non lance) | `rapport-qualite.md` s.6 | utilisatrice, `theo-archi` |
| F6 | **Trailers.** 7 commits de s54 portent `Claude Opus 5.5` : `[NAD]` `31f5c1ec`, `a3819bcd`, `c5c2d50f`, `0e271cfd`, `df9386ee` ; `[THEO]` `4e8ae7b4`, `3d75a284` (`git log 52e2b0cd..HEAD`, lecture des trailers). Les autres commits d'agent portent `Sonnet 5.5`. **Le rapport de tokens de s54 place `nadia-prof` et `theo-archi` sur `claude-opus-5-5` et tous les autres sur `claude-sonnet-5-5`** (`RAPPORT-tokens.md`). La regle ecrite, `DEC-0110` 17.4, dit que la valeur du trailer est *"le modele reellement execute"*, sourcee par ce rapport, et qu'aucun commit existant n'est reecrit. Les 7 trailers sont donc **conformes a la regle ecrite** ; la question de savoir si la regle est celle voulue est a l'utilisatrice | `DEC-0110` s.17.4 ; `squad/README.md` s.8 | utilisatrice |
| F7 | **Ecart de comptage 45/40 contre 47/41** : explique, section 8 obstacle 13 | `rapport-qualite.md` s.3 | `yanis-qa` |
| F8 | **Mesure de la fenetre de tokens** : la fenetre de s54 est ouverte jusqu'a maintenant (18:27 a 11:36 UTC) ; elle contient donc une partie de la consommation de cette tache de cloture | `RAPPORT-tokens.md` | - |

---

## 11. Question residuelle : `ADR-0104` est deja `Accepte`

**Faits.** `ADR-0104` : *"Accepte le 2026-10-07 (s54)"* (fichier de l'`ADR` et `ADR-index.md`), par `theo-archi` dans `4e8ae7b4`, sur la seule
condition ecrite (reponse de `nadia-prof` sur `C5` clause 1, 2026-10-06). Le lancement, le planning, le backlog et `DEC-0114` (section 6,
A.1) le disaient `Propose` ; `b5779885` a annote ces endroits sans effacer le texte d'origine. Le fichier de l'`ADR` dit lui-meme :
*"Accepter cet `ADR` n'engage ni la construction ni `ART-CP-10`"*. Le prix de la mecanique est ecrit en tailles
(`avis-faisabilite.md`, amendement de T6) : production 2 fichiers d'`adapter-web` et 20 a 38 lignes, plus 3 images et 30 lignes de registre ;
tests 3 fichiers neufs, 5 modifies, 15 a 18 tests, 680 a 1 400 lignes ; zero ligne dans `domain`, `application`, le chargeur, les POM
et la CI ; zero regle ArchUnit neuve. Le paquet qu'elle servirait n'existe pas (`avis-faisabilite.md` T6, poste T5 : `cp-arts-oeuvres-familles`
ne le remplace pas, 0 `illustration`).

**Question, a `camille-po` et a l'utilisatrice.** L'`ADR` est `Accepte` ; **la mecanique est-elle financee, et a quel sprint**, sachant que
`DEC-0114` section 6 point 4 renvoie la decision a la `DEC` d'ouverture de s55 *"apres lecture des deux prix"* et qu'elle ne peut plus
se reporter sans compte (`ART-CP-10` : s50 a s53, puis s54 par le branchement) ? Ce n'est pas une question d'architecture : rien dans
l'`ADR` ne la tranche.

---

## 12. Prix des stories ouvertes et non engagees

**Le depot n'a pas de tarif de developpement** (`DEC-0112` section 7 ; `avis-faisabilite.md` T6). Je donne des **tailles mesurees sur `HEAD`**
(fichiers et lignes que le critere de la story touche, comptees par `grep`), pas des heures. Les prix de `nadia-prof` sont ceux du bareme
ecrit (`PAR-10`, arbitrage de s30) ; ils restent **proposes** tant qu'elle ne les a pas ecrits pour la story. Aucune de ces stories n'est engagee.

| Story | Taille de developpement (mesure sur `HEAD`) | `nadia-prof` | Source du chiffre |
|---|---|---|---|
| `S54-ART-RES-R1` (consigne du jeu des familles d'oeuvres) | 2 fichiers, 2 lignes : `catalogue.yaml` ligne 851 (1 occurrence de *"va avec la statue, le tableau ou la photographie"*) et sa ligne d'inventaire, `TexteAccentueExposeParLePortEntrantTest` ligne 896 | **un seul rejeu pour R1 a R3** (`PAR-10` : plusieurs reserves sur un meme paquet forment un rejeu au prix du regime le plus cher), **0,5** car le texte est a `lea-dev`, pas fourni mot pour mot ; 0,25 si elle ecrit les trois textes | backlog `S54-ART-RES-R1` ; `PAR-10` |
| `S54-ART-RES-R2` (indice de l'item 11) | 1 fichier, 1 ligne : `cp-arts-oeuvres-familles.yaml` ligne 72 ; aucun test ne cite l'indice (`grep -rln "cadre autour"` ne rend que le YAML) | compris dans le rejeu ci-dessus | backlog `S54-ART-RES-R2` |
| `S54-ART-RES-R3` (metiers au feminin) | 1 fichier, **3 lignes** pour un seul metier (le photographe : lignes 35, 38, 43), **6** pour trois ; aucun test ne cite un metier (`grep` sur les `*.java` : rien) | compris dans le rejeu ci-dessus | backlog `S54-ART-RES-R3` ; `cp-arts-oeuvres-familles.yaml` lignes 34 a 43 |
| **R1 a R3 ensemble** | 3 fichiers, **6 a 9 lignes**, un seul commit | **0,5** (propose) | somme des trois lignes ci-dessus |
| `S54-PONCT-GN-INDICES` | 2 fichiers : `cm1-grands-nombres.yaml`, **12 lignes** `indice` (`grep` relance : 12 avec espace ordinaire, 0 insecable) ; `AucunSigneDoubleNEstDetachableDeSonMotTest` : l'entree `"cm1-grands-nombres", 12` (ligne 82) et `DETTE_TOTALE` 502 (ligne 99), soit 502 a 490 d'apres le critere du backlog (de 12 a 0). Un seul commit, comme `8b15e3ba` | **0,0** (note de `nadia-prof` du 2026-10-08 : la ponctuation est relue par `relecteur-pedagogique`) | backlog ; fiche grands-nombres, "Passage du 2026-10-08" |
| `S54-ASCII-APOSTROPHES-TESTS` | 2 fichiers, 1 ligne chacun : `PaquetCm1EmcAtteinteAutruiTest` ligne 57, `PaquetCm1SciencesActiviteHumaineTest` ligne 65 (U+2019 litteral, relance par script : exactement ces deux lignes) ; **la forme est a `theo-archi`** (backlog) | sans objet (aucun contenu) | backlog |
| `EXC-R15` (`S54-EXC-CATEGORIE`) | contenu : **4 lignes** (3 de `cp-emc-exclusion.yaml`, 1 de `catalogue.yaml`, criteres mot pour mot de `nadia-prof`) ; tests : `PaquetCpEmcExclusionTest` (7 lignes citent *"moquerie"* : liste des deux bonnes reponses de la tache A ligne 82, filtre ligne 170, message ligne 175), `TexteAccentueExposeParLePortEntrantTest` ligne 870 (consigne inventoriee), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ligne 877 (prose d'un motif). **4 fichiers.** Le nombre de lignes de test a changer n'est pas mesure : seules les occurrences le sont | **0,25** (rejeu mot pour mot, `PAR-10`), ecrit par elle dans le backlog | backlog `S54-EXC-CATEGORIE` ; fiche exclusion-cp, bloc du 2026-10-08 |
| `S54-OBS-SCI-CONSIGNE` | **non chiffree** : criteres non ecrits (*"a ecrire quand une instance l'engage"*, backlog) | non chiffree | backlog |

Le total des stories chiffrables (R1 a R3 ensemble, `S54-PONCT-GN-INDICES`, `EXC-R15`) est **0,5 + 0,0 + 0,25 = 0,75** pour `nadia-prof` (propose), contre une marge de rejeu nommee de 0,5 par instance
dans `PAR-9`. La capacite de s55 est a `camille-po` et a elle.

---

## 13. Rapport de tokens

`python outils/rapport-tokens.py --sprint s54` (avec `--sprint` explicite, tant que le defaut de l'outil n'est pas corrige) a ecrit
`RAPPORT-tokens.md` dans le dossier de sprint et regenere `squad/rapports/JOURNAL-tokens.md` (79 lignes ajoutees, 12 retirees : sections
s51 a s54 ajoutees, section "hors sprint" recalculee). **Des transcriptions existent pour les neuf agents invoques ; `ines-design` : aucune invocation mesuree.**
Sections conservees (chiffres ecrits, transcriptions absentes ou partielles) : s22 a s31. Rien n'est comble a la main. Le tableau est repris dans `RAPPORT.md`.

---

## 14. Questions a porter a l'utilisatrice

| Code | Question | Source |
|---|---|---|
| U1 | `ART-CP-10` : financer la mecanique "deux oeuvres ensemble" (`ADR-0104` `Accepte`, prix en tailles ecrit), ou non, et a quel sprint | section 11 |
| U2 | Redire ses mots sur l'ordre des matieres et sur `Q12` (forme 2 de `DEC-0062`) : c'est ce qui passe `DEC-0114` a `Acceptee` | obstacle 14, `DEC-0114` A.22 |
| U3 | Pousser s53 et s54 (60 commits) ; relire la CI sur le sommet pousse (`Q9` n'a nomme aucun commit) | obstacle 19 |
| U4 | Trailers `Opus 5.5` sur `[NAD]` et `[THEO]` : la regle de `DEC-0110` 17.4 (modele execute) est-elle celle voulue | F6 |
| U5 | Poste avec deux `toolchains.xml` : lequel est le bon pour la squad | F5 |

---

## 15. Ce que cette review ne fait pas

- Elle ne prononce aucun Done pedagogique et ne change aucun statut de matrice, de fiche ou de decision.
- Elle n'annonce pas la CI distante verte : les jobs de la section 4.3 ont tourne en local.
- Elle n'edite ni `DEC-0114`, ni la matrice, ni une fiche, ni le backlog, ni `catalogue.yaml`, ni un paquet.
- Elle ne decide aucun des points qu'elle route et n'engage rien pour s55 : la proposition est dans `proposition-s55.md`, qui n'est pas un planning.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cette review.** Decisions du sprint, rapportees et non prises ici (liste dans `RAPPORT.md`) :
`DEC-0114` (creee, amendements A.1 a A.26, statut `Proposee`), `DEC-0112` (amendements A.1 a A.3, 2026-10-07), `ADR-0104` (`Propose` a `Accepte`).
