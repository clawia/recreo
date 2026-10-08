# Rapport de sprint s54 - six paquets branches et audites, cinq lignes fermees (137/182), `ART-CP-10` branchee sans fermer, quatre reports de s53 livres

- **Duree** : 2026-10-07 20:27 -> 2026-10-08 14:00 (UTC+02:00), 17 h 33 min d'horloge, nuit comprise. Debut : horodatage de `planning.md` ; fin : celui de `review.md`, lu par `date`.
- **Objectif gele** (`planning.md`) : brancher cinq paquets de la session du 2026-10-07 et les presenter a `nadia-prof`, solder quatre reports
  de s53, traiter les cinq corrections de redaction de s53, ecrire pour `ART-CP-10` le prix et le plafond. Amende en cours de sprint par `DEC-0114`
  (A.1 a A.22) : un sixieme paquet (`cp-arts-oeuvres-familles`) branche sans fermer `ART-CP-10`, audite par une troisieme instance de `nadia-prof`.

## L'increment livre, en une phrase

Cinq jeux de plus (deux de francais CE1, deux de sciences CM1, un d'EMC CM1) sont servis a l'enfant avec le verdict `VALIDE` de `nadia-prof`,
ce qui porte la couverture de 132/182 (72,53 %) a **137/182 (75,27 %)** ; un sixieme jeu, de vocabulaire d'arts plastiques CP, est servi et
`VALIDE SOUS RESERVE` sans que la ligne `ART-CP-10` bouge.

## Les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0114` | Ouverture de s54 : cinq branchements au catalogue, trois postes de s53 re-engages, seconds reports tranches, deux instances de `nadia-prof` a 4,0 par instance, `ART-CP-10` au prix seul | Produit, **creee** le 2026-10-07 (`5b759d1d`, `[CAM]`) | **`Proposee`** (section 0.4 : l'ordre des matieres au-dela de s54 n'a que la forme 3 pour source). **Amendements A.1 a A.26** du 2026-10-08, dont trois par arbitrage relaye (`Q6`, `Q11`, `Q12`) et une annotation (A.26 : tarif reel de A 3,75, somme 8,0) |
| `DEC-0112` | Ouverture de s53 : onze points de relecture des paquets de la session de contenu... | Produit, **amendee** le 2026-10-07 (`5b759d1d`) : A.1 (R1, mesure "0 fichier"), A.2 (R2, provenance), A.3 (`S53-REL-10` absorbee) | `Acceptee`, inchange |
| `ADR-0104` | Rapprocher deux oeuvres se sert par des propositions qui sont des reproductions, sans mecanique ni forme d'item neuve, et la mecanique ne lit jamais une proposition | Architecture, **statut modifie** le 2026-10-07 (`4e8ae7b4`, `[THEO]`) | `Propose` -> **`Accepte`** ; l'acceptation n'engage ni la construction ni `ART-CP-10`. `ADR-index.md` mis a jour |

**`ADR` creees : aucune. `DEC` creees : une (`DEC-0114`).** Index tenus : `DEC-index.md`, `ADR-index.md`. `REGISTRE-identifiants.md` est modifie
(16 lignes de diff, `git diff --stat db69bbc6 HEAD`) : le message de `78da0199` dit *"EXC-R15 alloue, compte 924 / 158 lu sur le garde-fou PAR-5"* ; je n'ai pas relu le diff.
`squad/README.md` section 9 amende par `theo-archi` (`4e8ae7b4`, +12 lignes : regle du push, **non executable**, `avis-faisabilite.md` T7).
Pas une decision : arbitrages prealables de `nadia-prof` (instances A, B, C : `ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, `-instance-b-sciences-emc-cm1.md`, `arbitrage-nadia-c.md`), et correction de la section 11 de l'arbitrage de s53 (R3).

## Etat des deux Done

### Technique - `yanis-qa` sur `db69bbc6` ; ce que j'ai lance sur le sommet

Ce que `yanis-qa` a mesure (`rapport-qualite.md`, arbre `db69bbc6`) : `prefixes` vert, `chemins` vert, `outils` vert (10 juges), `build` vert
(`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, `BUILD SUCCESS`, **2216 tests**, 0 echec, 0 erreur, 0 ignore ; `package` aussi vert). Delta +42 sur 2174,
quatre classes de test de paquet. Il ne lit pas la CI distante.

**Ce qui a change depuis `db69bbc6`** : 16 fichiers, tous sous `docs/` ou `squad/` (backlog, fiches, arbitrages, matrice, `DEC-0114`, passe transverse,
rapports de cloture) ; **aucun fichier de contenu, de catalogue, de Java ou d'outil**. La correction de 8 lignes de contenu (`7d32c1e3`, `PCO-CE1-R1`) est **anterieure** a
`db69bbc6`.

**Ce que j'ai lance moi-meme sur le sommet** (`731aac5a` plus les fichiers de cloture ; commandes et detail : `review.md` section 4.3) :

| Job | Resultat |
|---|---|
| `prefixes` | vert, codes 0, 35 sujets hors fusions ; branche `BASE` du workflow non exercee |
| `chemins` | vert, codes 0, fichiers de cloture compris |
| `outils` | vert, 10 juges, 10 codes 0 |
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, racine : `BUILD SUCCESS`, 4 min 53 s, **2216 tests** (domain 120, application 17, adapter-web 401, adapter-content 1360, bootstrap 312 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK 21.0.10 rapporte par la machine virtuelle des tests |

Le pas `package` du job `build` n'est **pas relance par moi** : `yanis-qa` l'a lance sur `db69bbc6` (vert) et aucun code ne differe depuis. Total identique a celui de `yanis-qa` et de `theo-archi` : l'ecart avec les 2174 de s53 est de +42, quatre classes de test de paquet.

**La CI distante n'a rien rendu sur ces commits** : `origin/round-two` est `52e2b0cd`, 60 commits en arriere. Le verdict *"la CI est verte sur GitHub pour round-two"*
(`Q9`, relaye, `DEC-0114` A.2) ne nomme aucun commit ni aucun job.

### Pedagogique - prononce par `nadia-prof` seule

| Ligne ou paquet | Verdict | Fiche |
|---|---|---|
| `FRA-CE1-18` (`ce1-futur`) | VALIDE | `FICHE-bandit-manchot-futur-ce1.md` |
| `FRA-CE1-19` (`ce1-passe-compose`) | VALIDE SOUS RESERVE, puis `PCO-CE1-R1` levee, fiche VALIDE | `FICHE-bandit-manchot-passe-compose-ce1.md` |
| `SCI-CM1-15` | VALIDE | `FICHE-bandit-manchot-jour-nuit-saisons-cm1.md` |
| `SCI-CM1-18` | VALIDE | `FICHE-bandit-manchot-environnement-cm1.md` |
| `EMC-CM1-02` | VALIDE, avec la mention de couverture exigee d'avance | `FICHE-bandit-manchot-atteinte-autrui-cm1.md` |
| `cp-arts-oeuvres-familles` (`ART-CP-10`) | **VALIDE SOUS RESERVE, aucun mouvement de ligne** ; trois reserves `S54-ART-RES-R1` a `R3` | `FICHE-bandit-manchot-familles-d-oeuvres-cp.md` |
| `CRP-CM1-R1` a `R3` (`SCI-CM1-10`) | VALIDE, reserves levees | `FICHE-bandit-manchot-corps-cm1.md` |
| `SYC-CE1-R1` (`FRA-CE1-25`) | levee, VALIDE | `FICHE-bandit-manchot-synonymes-contraires-ce1.md` |
| `EMC-CP-03` (`EXC-R15`) | reserve neuve non bloquante, **ligne non rouverte** | `FICHE-bandit-manchot-exclusion-cp.md` |

Ce rapport ne prononce aucun Done.

## Couverture du programme

**137/182 (75,27 %)**, contre 132/182 (72,53 %) a la base `52e2b0cd`. Commande de la "Methode du compte" de `MATRICE-couverture.md`, relancee par moi sur
les deux arbres : `couvert-valide` 132 -> **137**, `en cours` 4, `non couvert` 46 -> **41**, `non couvrable en l'etat` 76, total 258, denominateur 182.
Chiffre identique a celui de `yanis-qa` et de `DEC-0114` A.23.

## Capacite : consommation reelle contre la borne

Borne ecrite de `PAR-9` pour s54 : 9,5 (`DEC-0114` A.18, `Q12` relaye). **Consommation reelle : A 3,75, B 3,25, C 1,0, somme 8,0** (`DEC-0114` A.26,
arbitrages prealables). **L'ecart de +1,5 accorde n'a pas ete consomme** (0,0 sur 1,5). Unites : bareme de `nadia-prof`, pas des tokens.

## Obstacles resolus et encore ouverts

Vingt-deux lignes au tableau de `review.md` section 8.

**Resolus sur fichier existant** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Perimetre sans acte d'ouverture | `camille-po` | `DEC-0114` (statut `Proposee`, voir ci-dessus) | 2026-10-07 |
| Trois engagements ajoutes en cours de sprint, capacite | `camille-po`, utilisatrice | `DEC-0114` A.1 a A.22 | 2026-10-08 |
| Statut d'`ADR-0104` contradictoire entre fichiers | `theo-archi`, `relecteur-pedagogique` | `ADR-0104` (`Accepte`), annotations `b5779885` | 2026-10-08 |
| Critere `TRV-CE1-5` mal cite | `nadia-prof` | arbitrage prealable de A section 12 ; fiche passe-compose section 4 | 2026-10-08 |
| R3 : "six fichiers" de l'arbitrage de s53 | `nadia-prof` | arbitrage de s53 corrige (`78da0199`) ; `grep -c` rend 0 | 2026-10-08 |
| Textes qui contredisaient les fiches (matrice, backlog) | `camille-po`, `relecteur-pedagogique` | `c1763f41`, `b5779885`, `a46220ab` | 2026-10-08 |
| `ART-CP-10` : branchement et audit | `camille-po`, `nadia-prof` | `DEC-0114` A.1, A.25 ; fiche | 2026-10-08 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `DEC-0114` `Proposee` : ordre des matieres en forme 3 | utilisatrice, `camille-po` | s54 | ses mots en forme 2, ou la `DEC` d'ouverture de s55 |
| `ART-CP-10` : financer la mecanique ; `ADR-0104` `Accepte` | utilisatrice, `camille-po` | s50 | decision a la `DEC` d'ouverture de s55 |
| Tronc distant : 60 commits non pousses ; `Q9` sans commit nomme | utilisatrice | s53 | le push, la CI lue sur le sommet |
| `TRV-CE1-5`, `EMC-9` : aucun resultat ecrit des developpeurs | `sofia-sm`, `marc-dev`, `lea-dev` | s54 | fichier nomme au planning, resultat ecrit |
| Motifs de refus de sciences sans test (mutation "le printemps" verte) | `theo-archi`, `yanis-qa` | s54 | avis sur un garde-fou, mutation rejouee |
| Isolation de l'instance C non verifiee ; planning non amende pour C et `S54-BR-ART-CP-10` | `yanis-qa`, `sofia-sm` | s54 | verification du diff de `s54-nadia-c` |
| Toolchain `~/.m2` double ; ligne `JAVA_TOOL_OPTIONS` rendant `inconnue` | utilisatrice, `theo-archi` | s52 | poste ; enveloppe |
| Defaut de `rapport-tokens.py` sans `--sprint` (cinq fichiers suivis modifies) | `theo-archi`, `sofia-sm` | s51 | correction ou `--sprint` toujours explicite |
| Pas de fichier de relecture de cloture | `relecteur-pedagogique` | s54 | fichier ou liste de corrections |
| Condition (ii) de `nadia-prof` (verification de s52) | `nadia-prof` | s52 | sa verification |
| Trailers `Opus 5.5` sur 7 commits `[NAD]` et `[THEO]` | utilisatrice | s54 | choix de la regle de `DEC-0110` 17.4 |
| Actions de la retro de s53 non traitees : A2 (push), A9 (`relecture-p11.md`), A11 | leurs proprietaires | s53 | voir `retro.md` |
| Stories non engagees : `EXC-R15`, `S54-ART-RES-R1` a `R3`, `S54-OBS-SCI-CONSIGNE`, `S54-PONCT-GN-INDICES`, `S54-ASCII-APOSTROPHES-TESTS` | `camille-po` | s54 | `DEC` d'ouverture de s55 (prix : `review.md` section 12) |

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s54` (fichier complet : `RAPPORT-tokens.md`). Fenetre : 2026-10-07 18:27 a 2026-10-08 11:36 UTC, **fenetre ouverte
jusqu'a l'execution de l'outil : elle contient une partie de la consommation de cette tache de cloture**. Transcriptions presentes pour les neuf agents invoques ; `ines-design` : aucune invocation mesuree.
Sections de sprint dont les transcriptions manquent, conservees avec leurs chiffres ecrits : s22 a s31.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 411 | 822 | 237 547 | 1 648 872 | 41 212 303 |
| `sofia-sm` | claude-sonnet-5-5 | 130 | 260 | 71 909 | 652 909 | 13 458 069 |
| `theo-archi` | claude-opus-5-5 | 253 | 506 | 87 664 | 619 870 | 33 323 849 |
| `marc-dev` | claude-sonnet-5-5 | 152 | 304 | 74 267 | 530 848 | 11 407 751 |
| `lea-dev` | claude-sonnet-5-5 | 143 | 286 | 61 193 | 573 988 | 9 720 416 |
| `yanis-qa` | claude-sonnet-5-5 | 292 | 584 | 119 275 | 753 700 | 32 630 742 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 82 | 164 | 18 510 | 177 473 | 4 635 193 |
| `nadia-prof` | claude-opus-5-5 | 752 | 1 504 | 406 973 | 2 652 114 | 103 970 934 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 149 | 386 | 163 371 | 453 834 | 28 766 337 |
| **Total** | | **2364** | **4 816** | **1 240 709** | **8 063 608** | **279 125 594** |

`nadia-prof` porte 752 tours sur 2364 (31,8 %) et 406 973 tokens d'output sur 1 240 709 (32,8 %), sur trois instances (A, B, C) ; `camille-po` 411 tours (17,4 %).
Les quatre compteurs ne s'additionnent pas (voir `RAPPORT-tokens.md`).

## Ce que ce rapport ne couvre pas

Les comptes de tokens ne disent pas ce que chaque agent a produit. Les chiffres de B (3,25) sont ceux de l'arbitrage prealable de `nadia-prof`, non recomptes. La CI distante n'a pas ete lue.
