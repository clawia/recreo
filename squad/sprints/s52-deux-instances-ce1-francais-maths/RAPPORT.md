# Rapport de sprint s52 - cinq lignes CE1 fermees sur cinq engagees, une ligne ouverte en partie, deux corrections levees, deux paquets de sciences CM1 retires

- **Duree** : 2026-10-06 20:51 -> 2026-10-07 10:01 (UTC+02:00), 13 h 10 min. Debut : horodatage de `planning.md` ; fin :
  celle de `review.md`, lue par `date` (10:01:13).
- **Objectif gele** (`DEC-0111` section 1, Acceptee, amendee quatre fois) : deux instances de `nadia-prof`, A (francais
  CE1 : `FRA-CE1-17`, `-25`, `-26` a 1,0, plus les corrections `TPS-CE1-R1` et `ALP-CE1-R1` a 0,5, total 4,0, 4,5 / 5,0
  avec le rejeu nomme) et C (mathematiques CE1 : `MAT-CE1-17` 1,5, `MAT-CE1-06` 1,5, `MAT-CE1-02` moitie textuelle 1,0,
  total 4,0, 4,5 / 5,0) ; retrait du catalogue servi des deux paquets de sciences CM1 refuses ; jusqu'a cinq lignes a
  `couvert-valide`, 127/182 au depart, **132/182 au mieux**.

## L'increment livre, en une phrase

Cinq lignes du programme passent de `non couvert` a `couvert-valide` (`FRA-CE1-17`, `FRA-CE1-25`, `FRA-CE1-26`,
`MAT-CE1-06`, `MAT-CE1-17`), `MAT-CE1-02` passe a `en cours` sur sa moitie textuelle, les reserves `TPS-CE1-R1` et
`ALP-CE1-R1` sont levees, et les deux paquets de sciences CM1 refuses a s51 ne sont plus servis a l'enfant. La
couverture passe de **127/182 (69,8 %) a 132/182 (72,5 %)**, le meilleur cas de l'objectif.

## Les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0111` | Ouverture de s52 : deux instances de `nadia-prof` pour le CE1 (A francais, C mathematiques), retrait du catalogue des deux paquets de sciences CM1 refuses, instance C sous condition suspensive ecrite | Produit | Acceptee. Creee le 2026-10-06 (`ba00cb92`, avant le planning). Amendee le 2026-10-06 : **A.1 a A.4** (`a5579c7b` : le retrait ne touche pas `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, condition (1) de 5.2 relue contre `ADR-0080`, six fiches ouvertes) ; **A.5 a A.8** (`ee3ff6cf` : `MAT-CE1-17` re-engagee a 1,5 apres verification des trois conditions, barre de refus de zone) ; **A.9 a A.16** (`337a96b5`, `8035f4de` : etat final, budget reel 9,0 pour 8,0 engages, report de `MAT-CE1-17` clos, reserves restantes, perimetre de s53 propose et non ouvert, ecart "palier 2" traite). Amendee le 2026-10-07 (`afff61b0`) : section 11 (trois identifiants alloues en cours de sprint), forme 3, renvoi de A.5 |
| `ADR-0080` | Une designation sur image est un choix parmi des zones enumerees ; le client n'envoie jamais une coordonnee | Architecture | Accepte (depuis s35). **Amendements de s52 du 2026-10-06** : **A2** (`edf15763`, avant le planning : `ZON-13` et rang de declaration de la bonne zone deviennent des garde-fous), **A2bis** (`ceffde66` : message du cliquet de designation corrige, plancher de zone), **A2ter** (`077f2928` : barre de refus de 15 unites, 16 unites est une marge de dessin, en accord avec `DEC-0111` A.7) |

Index tenus a jour : `DEC-index.md` (ligne de `DEC-0111`), `ADR-index.md` (ligne d'`ADR-0080`).

Trois identifiants alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, chacun dans le meme diff que
la fiche qui le definit (ce ne sont pas des decisions) : `SYC-CE1-R1`, `MUL-CE1-R1`, `SYM-CE1-R1`. Compte du registre :
919 au planning, **922** a la cloture, 158 prefixes (obstacle 3 de `review.md`).

Trois arbitrages de `nadia-prof` (pas des `DEC`) actent des regles opposables :
`ARBITRAGE-PREALABLE-s52-instances-ce1.md` (deux instances, `PAR-2` non amende, borne a trois refusee sur la condition
(i)), `ARBITRAGE-PREALABLE-s52-fra-ce1-questions-de-l-avis.md` (`Q1` a `Q3`, `C5` clause 1, `PAR-9` maintenue pour s53)
et `ARBITRAGE-PREALABLE-s52-instance-c-reponses.md` (densite du reseau jusqu'a 10 x 10, esquisse du quadrillage,
barre de 44 px a 296 px). Une contrainte de dessin d'`ines-design` (`squad/design/`) et une transcription de la regle
du trailer dans `squad/README.md` section 8 (`DEC-0110` 17.4, planning) ont aussi ete ecrites ; ce ne sont pas des
decisions.

Non modifiees par s52 : `DEC-0110`, `ADR-0104` (statut `Propose` sur le disque, `C5` clause 1 repondue, statut a
`theo-archi`).

## Etat des deux Done

### Technique - quatre jobs verts en local, juge CI non rendu

Source : `rapport-qualite.md` de `yanis-qa`, arbre `40daa419`, **les quatre jobs de `.github/workflows/ci.yml`
lances** : `prefixes` vert, `chemins` vert, `outils` vert (9 juges), `build` vert (`BUILD SUCCESS`, reacteur complet,
**2172 tests**, **2174** avec le test de la question Q6, 0 echec, 0 erreur). Reserve : le pas `package` du job `build`
n'est pas rejoue.

**Ce que ce rapport ne couvre pas** : huit commits posterieurs (`40daa419..509552f0`) : documents (`DEC-0111`, matrice,
backlog, `ADR-0080` A2ter, avis, confirmation ArchUnit, contrainte d'`ines-design`), le test Q6 (compte dans les 2174),
et des commentaires de trois YAML et de trois tests (`d8ace7c1`, aucune ligne non commentaire dans les YAML). **J'ai
relance les quatre jobs sur `509552f0`** avant d'ecrire les fichiers de cloture (`review.md` section 2.2) : `build`
**2174 tests**, 0 echec, `BUILD SUCCESS` (domain 120, application 17, adapter-web 401, adapter-content 1318, bootstrap
312 + 6 d'integration) ; `prefixes`, `chemins`, `outils` (9 juges) verts. Rien n'est pousse : **la CI n'a rendu aucun
verdict sur ces commits.**

`theo-archi` confirme (`confirmation-archunit.md`, `40daa419`) : **229 tests ArchUnit verts** (32 classes, 229 retrouves
par moi sur `509552f0`), **39** `static final ArchRule` et **36** `@ArchTest` (methode d'`ADR-0104`, contre-exemples
compris, 37 / 36 exclus), aucune regle ajoutee, aucun `src/main/java` touche.

### Pedagogique - prononce par `nadia-prof` seule

| Ligne | Verdict final | Fiche |
|---|---|---|
| `FRA-CE1-17` | VALIDE (REFUSE au premier passage, VALIDE au second apres rejeu) | `FICHE-bandit-manchot-conjugaison-present-ce1.md` |
| `FRA-CE1-25` | VALIDE SOUS RESERVE (`SYC-CE1-R1`) | `FICHE-bandit-manchot-synonymes-contraires-ce1.md` |
| `FRA-CE1-26` | VALIDE | `FICHE-bandit-manchot-categories-ce1.md` |
| `MAT-CE1-06` | VALIDE SOUS RESERVE (`MUL-CE1-R1`), aucune mention due | `FICHE-bandit-manchot-sens-multiplication-ce1.md` |
| `MAT-CE1-17` | VALIDE au second passage, `SYM-CE1-R1` levee, mention de couverture de l'arbitrage de s43 | `FICHE-designation-symetrie-ce1.md` |
| `MAT-CE1-02` | VALIDE sur la moitie textuelle, la ligne passe a `en cours`, mention d'`ORD-CE1-5` | `FICHE-bandit-manchot-comparer-ranger-ce1.md` |
| `FRA-CE1-20` | `TPS-CE1-R1` levee, ligne non rouverte | `FICHE-bandit-manchot-temps-ce1.md` |
| `FRA-CE1-23` | `ALP-CE1-R1` levee, ligne non rouverte | `FICHE-bandit-manchot-ordre-alphabetique-ce1.md` |

**Aucun refus final.** Un refus au premier passage (`FRA-CE1-17`, un motif), rejoue et leve dans le sprint. Detail :
`review.md` section 6.

## Couverture du programme

**127/182 (69,8 %) -> 132/182 (72,5 %)**, mesuree par la commande ecrite dans `squad/programme/MATRICE-couverture.md`
(section "Methode du compte", `awk` sur la septieme cellule), que j'ai relancee : sur la matrice a `b4ce7d93`,
`couvert-valide` 127, `en cours` 3, `non couvert` 52 ; sur la matrice a `509552f0`, `couvert-valide` 132, `en cours` 4,
`non couvert` 46 ; `non couvrable en l'etat` 76 inchange, total 258, denominateur 182.

Budget : A 4,5 (4,0 + rejeu de `FRA-CE1-17` 0,5), C 4,5 (4,0 + second passage de `MAT-CE1-17` 0,5, lecture de
`camille-po` a confirmer par `nadia-prof`) ; engage 8,0, consomme 9,0, aucun plafond de 5,0 depasse. Si `nadia-prof`
lit `PAR-14` a la lettre, C est a 5,5 (`DEC-0111` A.11, obstacle 9 de `review.md`).

## Obstacles resolus et encore ouverts

Seize lignes au tableau de `review.md` section 5 (la ligne 14 regroupe les dix-sept points routes du planning).

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Plancher de zone 13 contre 16 unites, puis barre de refus 15 | `theo-archi`, `camille-po` | `ADR-0080` A2bis et A2ter ; `DEC-0111` A.7 | 2026-10-06 (fichiers existants) |
| Condition (1) de `MAT-CE1-17` : message du cliquet, lecture de `camille-po` | `theo-archi`, `camille-po` | `ADR-0080` A2bis ; `DEC-0111` A.2 et A.5 | 2026-10-06 |
| Fiches de conformite des six jeux | `camille-po` | `DEC-0111` A.3 | 2026-10-06 (six fiches sur le disque) |
| Retrait et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (ecart F2) | `camille-po` | `DEC-0111` A.1 | 2026-10-06 |
| A1 commite deux fois a s51 : evite a s52 (ordre d'ouverture) | `theo-archi` | `ADR-0080` | 2026-10-06 |
| Test de la symetrie fragile aux fins de ligne CRLF, trouve a la fusion de C | `lea-dev` | `173b48f7` ; 2166 tests et 1 echec, puis 2172 verts | 2026-10-06 |
| Conflits de fusion de C (registre, test de rang) | `sofia-sm` | `ADR-0085` ; 919 + 1 + 2 = 922, 158 prefixes | 2026-10-06 |
| Compte du lexique de l'avis (266 contre 273 entrees) | `theo-archi` | avis, ligne corrigee (`7e965956`) | 2026-10-07 |
| F1 de `yanis-qa` (niveau de journal d'un jeu retire) : pas un defaut | `theo-archi` | `ADR-0018` point 2 ; avis section 3.6 | 2026-10-07 |
| Ecart de toolchain du poste : pas un defaut de script | `theo-archi` | `confirmation-archunit.md` section 6 | 2026-10-06 (le fichier du poste reste a corriger, ouvert ci-dessous) |
| Trailers "Opus 5.5" : ventilation du 568 du planning, regle appliquee | `sofia-sm` | `DEC-0110` 17.4 ; `squad/README.md` section 8 | 2026-10-07 |
| Worktree du retrait impossible sous le nom du planning (260 caracteres) | `sofia-sm` | - | contourne : `../recreo-s52-retrait` |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| Fichier `c3` (cookies) a la racine du worktree de C, non suivi | `lea-dev`, `nadia-prof` | s52 | auteur et suppression |
| Lecture du rejeu de `MAT-CE1-17` : 0,5 par `PAR-10` ou 5,5 par `PAR-14` | `nadia-prof` | s52 | prix ecrit, amendement de `DEC-0111` si elle lit `PAR-14` a la lettre |
| Item 16 de `FRA-CE1-25` a remplacer (*"Le train arrive."*), re-audit avec `SYC-CE1-R1` | `camille-po`, `nadia-prof` | s52 | engagement a s53, rejeu |
| `SYC-CE1-R1` et `MUL-CE1-R1`, 0,5 chacune | `camille-po` | s52 | `DEC` de s53 (premier report) |
| Verification de la condition (ii) de la borne a trois instances pour s52, dernier sprint parallele | `nadia-prof` | s52 | fichier de verification des formes (a), (b), (c) |
| Niveau `ERROR` d'un coup sur un jeu retire non epingle dans le test de `yanis-qa` | `yanis-qa` | s52 | un test qui asserte le niveau, ou un motif ecrit |
| Fichier de toolchains du shell perime (hors depot) | utilisatrice | s52 | correction ou suppression sur le poste |
| Mesure de fusion de cloture interrompue apres 8 h 46 min (fork termine, code 143) | `yanis-qa` | s52 | cause |
| Entete `Statut global` des six fiches de s52 encore a *"en construction"* | `camille-po`, `nadia-prof` | s52 | mise a jour de l'entete |
| Reprises de sciences CM1, `CRP-CM1-R1` a `R3`, `MAT-CE1-02` moitie designation, `PAR-4`, `S51-OUTIL-JOURNAL-TOKENS` : troisieme report | `camille-po`, `nadia-prof`, `theo-archi` | s51 | engager ou abandonner, avec motif, a l'ouverture de s53 (`DEC-0111` A.14) |
| `ADR-0104` reste `Propose` ; `ART-CP-10` hors perimetre, troisieme sprint | `theo-archi`, `nadia-prof` | s50 | statut de l'`ADR` apres la reponse a `C5` ; issue de `ART-CP-10` |
| Journal de tokens : la regeneration degrade les sections s22 a s27 | `theo-archi`, utilisatrice | s51 | decision, story `S51-OUTIL-JOURNAL-TOKENS` |

## Prochain perimetre

**Non propose par cette cloture** : role de `camille-po`. Son perimetre propose, ses reports et la regle `PAR-9` pour
s53 sont ecrits dans `DEC-0111` amendement A.14 ; l'ouverture de s53 est une decision de l'utilisatrice, qui n'a pas
eu lieu.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s52`, fenetre 2026-10-06 18:51 -> 2026-10-07 08:01 UTC (debut du
planning, fin de `review.md`). Detail dans `RAPPORT-tokens.md` (ce dossier). Le script couvre les dix agents, dont
`ines-design` et les sous-agents lances dans des worktrees. **Cumul dans `squad/rapports/JOURNAL-tokens.md` : non
mis a jour.** La regeneration du journal remplace par *"Aucune consommation mesuree"* des sections de s22 a s27
(decision de l'utilisatrice du 2026-10-06 : ne pas commiter le journal regenere) ; je l'ai restaure par
`git checkout -- squad/rapports/JOURNAL-tokens.md`. **La section de s52 est donc absente du journal commite**, comme
celle de s51 (le journal commite s'arrete a s50). Le tableau ci-dessous et `RAPPORT-tokens.md` sont les seules traces
commitees de s52 ; la regeneration complete est conservee hors depot.

Le travail d'ouverture de s52 (`DEC-0111`, arbitrage prealable, avis de faisabilite, contraintes d'`ines-design`) a
commence avant le planning (d'apres les horodatages de fichiers du scratchpad, des 18:47 heure locale) : il tombe dans
`hors sprint` du journal et n'est pas dans ce tableau. Les tokens que j'ai consommes apres 10:01 pour finir ces
fichiers sont hors de la fenetre.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 324 | 648 | 148 583 | 1 012 267 | 36 634 283 |
| `sofia-sm` | claude-sonnet-5-5 | 178 | 356 | 96 676 | 883 570 | 34 790 969 |
| `theo-archi` | claude-opus-5-5 | 231 | 462 | 74 603 | 1 155 933 | 18 827 400 |
| `marc-dev` | claude-sonnet-5-5 | 297 | 598 | 167 722 | 1 335 955 | 45 995 030 |
| `lea-dev` | claude-sonnet-5-5 | 394 | 798 | 248 003 | 2 584 996 | 161 128 314 |
| `yanis-qa` | claude-sonnet-5-5 | 117 | 234 | 43 524 | 372 991 | 9 450 789 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 170 | 340 | 58 685 | 627 094 | 33 711 039 |
| `nadia-prof` | claude-opus-5-5 | 484 | 982 | 238 890 | 1 983 402 | 80 112 166 |
| `ines-design` | claude-sonnet-5-5 | 145 | 290 | 90 252 | 431 786 | 15 987 132 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 189 | 512 | 234 792 | 1 908 555 | 78 294 201 |
| **Total** | | **2529** | **5 220** | **1 401 730** | **12 296 549** | **514 931 323** |

Les quatre compteurs sont separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un classement faux.
