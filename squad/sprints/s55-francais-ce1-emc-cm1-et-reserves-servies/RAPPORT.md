# Rapport de sprint s55 - quatre paquets branches et audites, quatre lignes fermees (141/182), deux jeux servis corriges, le dispositif d'`ART-CP-10` livre sans qu'un enfant le voie

- **Duree** : 2026-10-08 14:43 -> 22:21 (UTC+02:00), 7 h 38 min d'horloge. Debut : l'heure de debut de `planning.md` ; fin : celle de `review.md`, lue par `date`.
- **Objectif gele** (`planning.md`) : brancher quatre paquets (deux de francais CE1, deux d'EMC CM1) et les presenter a `nadia-prof` ; solder les reserves non bloquantes de deux jeux deja servis
  (`EXC-R15`, `S54-ART-RES-R1` a `R3`). Complete a l'ouverture par `DEC-0115` : le dispositif d'`ART-CP-10` (T2, T3), sans paquet ni audit.

## L'increment livre, en une phrase

Quatre jeux de plus (deux de francais CE1, deux d'EMC CM1) sont servis a l'enfant avec le verdict `VALIDE SOUS RESERVE` de `nadia-prof`, ce qui porte la couverture de 137/182 (75,27 %) a **141/182 (77,47 %)** ;
deux jeux deja servis sont corriges et rejoues (un nom faux de categorie d'exclusion au CP, une consigne et un indice d'arts plastiques CP) ; le dispositif qui servira `ART-CP-10` est livre, mais aucun paquet ne l'utilise et aucun enfant ne le voit.

## Les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `DEC-0115` | Ouverture de s55 : quatre branchements, deux corrections de jeux servis, mecanique d'`ART-CP-10` financee (dispositif seul), ordre des matieres repris sous motif de forme 1 | Produit, **creee** le 2026-10-08 (`303c6a82`, `[CAM]`). **Amendements A.1 a A.21** du meme jour, dont A.18 (la formule de la forme 3) | **`Proposee`** : le financement de la mecanique n'a que l'enonce d'un agent pour source ; il passe a `Acceptee` avec les mots de l'utilisatrice en forme 2 (section 0.5, A.16, A.18) |
| `DEC-0114` | Ouverture de s54 : cinq branchements, trois postes de s53 re-engages, deux instances a 4,0, `ART-CP-10` au prix seul | Produit, **amendee** par A.27 (2026-10-08) | `Proposee` -> **`Acceptee`**, par la lecture (b) de la section 0.4 sur le motif de forme 1 de `DEC-0115` section 3 ; ne remplace pas les mots de l'utilisatrice |

**`ADR` creees ou modifiees : aucune** (`ADR-0104` reste `Accepte`, inchange ; `theo-archi` : *"Aucun"* `ADR` appele, `confirmation-archunit.md` section 6). **`DEC` creees : une (`DEC-0115`), amendee une (`DEC-0114`).**
Index tenus : `DEC-index.md` (`DEC-0115` ajoutee, ligne de `DEC-0114` mise a jour). `REGISTRE-identifiants.md` : de 924 / 158 a 930 / 158 (six identifiants neufs de `nadia-prof` : `ACV-CE1-R1`, `ACV-CE1-R2`, `CLM-CE1-R1`,
`EMC-CM1-R8`, `EMC-CM1-R9`, `EXC-R16`) ; compte lu dans l'en-tete du registre, **non recompte par moi** ; le build vert porte son garde-fou.
Hors decision, par cette cloture : `CLAUDE.md` (une phrase, `de1aa216`) et un amendement date du planning (`b20655fa`).

## Etat des deux Done

### Technique

**Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. **La CI distante (GitHub Actions) n'est lue par aucun de nous** : aucun agent n'a rendu un verdict de GitHub,
`gh run list` rendait `HTTP 404` depuis ce poste au planning. Tout ce qui suit a tourne en local, sous Windows.

| Qui | Arbre | Jobs lances, par leur nom | Resultat |
|---|---|---|---|
| `yanis-qa` | `af8b326e` | `prefixes`, `chemins`, `outils` (10 juges), `build` (`verify`, `package` et le cas du jar reproductible) | les quatre verts ; **2303 tests** |
| `theo-archi` | `af8b326e` | `build` (deux etapes plus le cas reproductible), `chemins`, `prefixes` ; `outils` non lance | verts ; 240 tests `com.recreo.archi`, 39 `ArchRule` et 36 `@ArchTest` inchanges |
| `relecteur-pedagogique` | `af8b326e` plus ses trois commits | `build` (`verify` seul), `chemins`, `prefixes`, `outils` (10 juges) | verts ; 2303 tests |
| `marc-dev` | `e661879a` plus `2224e91e` | `build` (`verify`), `chemins` ; `prefixes`, `outils` non lances | `BUILD SUCCESS` (120, 18, 407, 1423, 333 et 6) |
| `sofia-sm` (cette tache) | `445647a7` plus les fichiers de cloture | `prefixes`, `chemins`, `outils` (10 juges, avec `python`), `build` (`clean verify`, puis `package` sans `clean`) ; ni le workflow GitHub, ni le cas du jar reproductible | les quatre verts ; 2307 tests, 0 echec ; `BUILD SUCCESS` deux fois |

**Total des tests, etabli par moi sur l'arbre fusionne : 2307** (`bash outils/mvn-jdk21.sh -B clean verify` sur `445647a7`, reacteur complet : domain 120, application 18, adapter-web 407, adapter-content 1423, bootstrap 333, plus 6 d'integration ;
0 echec, 0 erreur, 0 ignore ; somme des attributs `tests` des 289 rapports XML recoupee par les lignes `Tests run:` de fin de module ; JDK 21.0.10 rapporte par la machine virtuelle des tests). **Delta : +91 sur 2216** (cloture de s54, citee) :
+87 mesures par `yanis-qa` sur `af8b326e` (2303, classe par classe dans son rapport) et +4 de `2224e91e`. Les annonces des developpeurs mesurent chacune la base de leur worktree plus leur lot ; aucune n'est reprise.

**Ce qui n'est pas verifie** : la CI distante ; la branche `BASE` du job `prefixes` ; tout ce qui tourne sous Linux ; une seule configuration de fins de ligne (`core.autocrlf=true`) ; le correctif `a3606b2f` n'a pas ete mute par `yanis-qa` ;
la mutation d'A7 et les morsures de T2 ne sont pas faites. **Le correctif de CI (`a3606b2f`)** : livre, mesure en local par `theo-archi` et `yanis-qa` (vert sans et avec `project.build.outputTimestamp`, rouge si le correctif est retire) ;
**la cause du rouge de CI qu'il corrige n'est ecrite dans aucun fichier du depot, et personne n'a observe l'execution GitHub** ; poste hors planning et hors `DEC-0115` (`review.md` section 5).
**A7** (`8541cffc`) et **A8** (`69d9d6a2`) sont livrees : `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` sort 0 (1 avant), `python outils/rapport-tokens.py` sans `--sprint` sort 2 et n'ecrit rien (6 fichiers modifies avant) ;
`yanis-qa` a rejoue trois mutants d'A8, rouges ; la mutation d'A7 n'est pas faite.

### Pedagogique - prononce par `nadia-prof` seule

| Ligne ou paquet | Verdict | Fiche |
|---|---|---|
| `FRA-CE1-14` (`ce1-accord-sujet-verbe`) | **VALIDE SOUS RESERVE** : `ACV-CE1-R1`, `ACV-CE1-R2` | `FICHE-bandit-manchot-accord-sujet-verbe-ce1.md` |
| `FRA-CE1-12` (`ce1-classes-de-mots`) | **VALIDE SOUS RESERVE** : `CLM-CE1-R1` | `FICHE-bandit-manchot-classes-de-mots-ce1.md` |
| `EMC-CM1-03` (`cm1-emc-respect-differences`) | **VALIDE SOUS RESERVE** : `EMC-CM1-R8` ; mention d'EMC CM1 exigee | `FICHE-bandit-manchot-respect-differences-cm1.md` |
| `EMC-CM1-06` (`cm1-emc-principes-republique`) | **VALIDE SOUS RESERVE** : `EMC-CM1-R9` ; mention d'EMC CM1 exigee | `FICHE-bandit-manchot-principes-republique-cm1.md` |
| `cp-arts-oeuvres-familles` (`ART-CP-10`, `S54-ART-RES-R1` a `R3`) | **VALIDE**, reserves levees ; **aucun mouvement de ligne** | `FICHE-bandit-manchot-familles-d-oeuvres-cp.md` |
| `cp-emc-exclusion` (`EMC-CP-03`, `EXC-R15`) | **VALIDE SOUS RESERVE** : `EXC-R15` levee, `EXC-R16` neuve ; ligne inchangee | `FICHE-bandit-manchot-exclusion-cp.md` |
| T2, T3 (dispositif d'`ART-CP-10`), `PONCT`, `ASCII`, A7, A8 | **aucun Done a prononcer** : l'enfant ne voit rien qui releve d'elle ; T3 se prononcera sur le premier paquet a reproductions | `cloture-nadia.md` section 3 |

Les six verdicts sont des audits joues sur un jar, rendu lu en HTML et non vu au navigateur. Aucun REFUSE. Aucune reserve bloquante. **Ce rapport ne prononce aucun Done.**

## Couverture du programme

**141/182 (77,47 %)**, contre 137/182 (75,27 %) a la base `fbe07d79`. Commande de la "Methode du compte" de `MATRICE-couverture.md`, relancee par moi sur les arbres `fbe07d79`, `af8b326e` et `HEAD` : `couvert-valide` 137, 137, **141** ;
`en cours` 4 ; `non couvert` 41, 41, **37** ; `non couvrable en l'etat` 76 ; total 258 ; denominateur 182. Chiffre identique a celui de `cloture-nadia.md`, de la matrice et de `DEC-0115` A.14.
Quatre lignes ferment : `FRA-CE1-14`, `FRA-CE1-12`, `EMC-CM1-03`, `EMC-CM1-06`. `couvert-sous-reserve` n'existe pas dans la legende de la matrice : un verdict sous reserve non bloquant ferme en `couvert-valide`, reserves nommees dans la cellule. `ART-CP-10` reste `non couvert`.

## Capacite : `PAR-8` et `PAR-9`

Unites : bareme de `nadia-prof`, pas des tokens. Recomptees par moi (arithmetique) a partir de ses tarifs, non remesurees.

| Poste | Instance A | Instance B |
|---|---|---|
| Somme des tarifs, marge exclue | **3,5** (1,0 + 1,0 + 0,5 + 0,25 + 0,25 + 0,25 + 0,25) | **3,5** (1,0 + 1,5 + 0,5 + 0,25 + 0,25), lecture de `camille-po` |
| Marge de rejeu de 0,5, allouee a aucun lot | **non utilisee** | 0,25 consommes par l'ecart de l'item 4 selon `nadia-prof` |
| Lecture de `nadia-prof` | 4,0 sur 4,0 | 4,0 sur 4,0 |

`PAR-8` (borne 8,0, plafond dur 5,0, au plus trois lots par instance) : **tenue**, deux lots par instance, plafond dur jamais approche. `PAR-9` (4,0 par instance, somme 8,0) : **tenue** sous les deux lectures. Aucun refus n'a consomme de marge.
Aucune troisieme instance. **L'ecart de lecture sur B (3,5 ou 4,0) n'est pas tranche.** **Rejeux dus a s56 : 2,5** (A 1,75, B 0,75), non finances a s55.

## Obstacles resolus et encore ouverts

Dix-neuf lignes au tableau de `review.md` section 11.

**Resolus sur fichier existant** :

| Description | Agent route | Decision ou fichier | Clos le |
|---|---|---|---|
| Perimetre sans acte d'ouverture | `camille-po` | `DEC-0115`, `DEC-0114` A.27 | 2026-10-08 |
| `round-two` rouge en local alors qu'une CI verte etait relayee (`review.md` de s54, ligne `U4`) | `sofia-sm` | `8f740977` | 2026-10-08 |
| Rouge `ADR-0087` sur `rapport-sofia-outils.md` ligne 22 | `sofia-sm` | `02798fa7` | 2026-10-08 |
| `R1` (forme 3 sans formule), `R3` (0,25 de A), `R6` (point 5 de "Decisions prises") du relecteur | `camille-po` | `DEC-0115` A.18, A.19, A.20 | 2026-10-08 |
| `R2` (quatre criteres du planning insatisfiables) et `R1` du planning | `sofia-sm` | amendement du planning, `b20655fa` | 2026-10-08 |
| `R5` (`CLAUDE.md` ligne 167) | `sofia-sm` | `de1aa216` | 2026-10-08 |
| `S54-ASCII-APOSTROPHES-TESTS`, apostrophe typographique non gardee | `marc-dev` | `2224e91e` (mutation rejouee par `marc-dev`, pas par `yanis-qa`) | 2026-10-08 |
| Isolation de l'instance C (A6 de s54) ; verification de s52 contre les formes (A10) ; fichier de relecture de cloture (A9) ; resultat ecrit de `TRV-CE1-5` et `EMC-9` (A4) | `yanis-qa`, `nadia-prof`, `relecteur-pedagogique`, `marc-dev`, `lea-dev` | `rapport-qualite.md` section 5 ; `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 2 ; `relecture-cloture.md` ; `rapport-marc-dev.md`, `rapport-lea-dev-emc.md` | 2026-10-08 |

**Encore ouverts** (numero du tableau de la review) :

| # | Description | Agent route | Ce qui manque |
|---|---|---|---|
| 2 | **Q6** : financement du dispositif d'`ART-CP-10`, forme 3 ; `DEC-0115` `Proposee` | utilisatrice | ses mots en forme 2, avec leur date |
| 4, 5 | `a3606b2f` : cause du rouge de CI non ecrite, poste hors planning ; trois autres commits de livraison sans poste (`2b66a441`, `fa3b97c0`, `e6262ac2`) | `theo-archi`, `sofia-sm`, `camille-po` | la cause ecrite ; la regle de la retro (P1) |
| 7 | Quatre ecarts de critere (`Q-Y6`, `Q-Y9`, `Q-Y10`, `C7`/`EMC-9`) | `camille-po`, `yanis-qa` | backlog corrige |
| 8, 9 | Six reserves ouvertes (2,5) ; ecart de lecture sur B | `camille-po`, `nadia-prof` | la `DEC` d'ouverture de s56 ; une ligne de `nadia-prof` |
| 10, 11 | Dispositif d'`ART-CP-10` jamais joue, `E3` non acceptable pour un paquet servi ; plafond de 176 px garde par rien (`S55-ART-CP-10-PLAFOND`) | `nadia-prof`, `ines-design`, `theo-archi`, `sofia-sm` | arbitrage de forme (0,25) ; poste prealable au planning de s56 |
| 6, 12 | Mutation de `Q-Y8` non rejouee par `yanis-qa` ; mutation d'A7 et morsures de T2 non faites | `yanis-qa` | rapport qualite de s56 |
| 13 | 49 commits non pousses a `445647a7` ; CI distante jamais lue | utilisatrice | le push, la CI lue sur un commit nomme |
| 15, 16, 17, 18, 19 | Relecture de ponctuation de `PONCT` ; en-tete et titre de la fiche d'exclusion ; verification de s54 contre les formes ; `S54-OBS-SCI-CONSIGNE` (premier report) ; toolchains du poste de `yanis-qa` | `relecteur-pedagogique`, `nadia-prof`, `camille-po`, utilisatrice | voir `retro.md` section 5 |

## Questions ouvertes de l'utilisatrice

- **Q6** : le financement du dispositif d'`ART-CP-10`. Tant que ses mots ne sont pas ecrits en forme 2, `DEC-0115` reste `Proposee` et T2 et T3 sont une execution consignee ; s'ils divergent, la section 5 est amendee
  et le sort des deux commits (`dfd2f107`, `363793e3`, 30 tests, 99 lignes de CSS) est une story routee a `sofia-sm` (`DEC-0115` A.18).
- **La suite**, `proposition-s56.md` section 7 : Q-U1 (le paquet a reproductions avant l'audit de s56 : O1 ou O3, 8,25 sur 8,0 pour O3), l'ordre des matieres pour s56, le decoupage du francais CE1 en deux instances (s58 au lieu de s60 pour les 11 lots).
- Le push et la lecture de la CI sur un commit nomme.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s55` (fichier complet : `RAPPORT-tokens.md`). Fenetre : 2026-10-08 12:43 a 20:21 UTC, **fermee par l'heure de fin de `review.md`** : elle contient la consommation de cette tache de cloture
jusqu'a cette heure, pas apres (le dernier `clean verify` et les commits lui sont posterieurs). Transcriptions presentes pour les neuf agents invoques, `ines-design` compris (une tache) ; sections de sprint dont les transcriptions manquent, conservees avec leurs chiffres ecrits : s22 a s31.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 562 | 1 124 | 322 177 | 1 985 196 | 74 964 471 |
| `sofia-sm` | claude-sonnet-5-5 | 323 | 646 | 262 369 | 1 821 573 | 64 735 757 |
| `theo-archi` | claude-opus-5-5 | 370 | 740 | 157 853 | 1 407 223 | 46 960 937 |
| `marc-dev` | claude-sonnet-5-5 | 331 | 662 | 121 403 | 1 792 484 | 22 462 525 |
| `lea-dev` | claude-sonnet-5-5 | 455 | 910 | 246 519 | 1 851 299 | 94 133 341 |
| `yanis-qa` | claude-sonnet-5-5 | 319 | 638 | 173 360 | 1 102 807 | 50 315 452 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 203 | 406 | 64 485 | 811 965 | 32 954 605 |
| `nadia-prof` | claude-opus-5-5 | 868 | 1 740 | 425 922 | 3 195 566 | 107 527 315 |
| `ines-design` | claude-sonnet-5-5 | 93 | 186 | 73 669 | 385 829 | 10 771 950 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 182 | 464 | 260 065 | 459 732 | 31 105 327 |
| **Total** | | **3706** | **7 516** | **2 107 822** | **14 813 674** | **535 931 680** |

`nadia-prof` porte 868 tours sur 3706 (23,4 %) et 425 922 tokens d'output sur 2 107 822 (20,2 %), sur deux instances (A, B) et une cloture ; `camille-po` 562 tours (15,2 %) et `lea-dev` 455 (12,3 %). `nadia-prof` et `theo-archi` sont sur `claude-opus-5-5`, les autres sur `claude-sonnet-5-5` :
c'est la source des onze trailers `Claude Opus 5.5` de `[NAD]` et `[THEO]` (`DEC-0110` 17.4). Les quatre compteurs ne s'additionnent pas (voir `RAPPORT-tokens.md`).
**Comparaison avec s54** : 3706 tours contre 2364 et 2 107 822 d'output contre 1 240 709, mais la fenetre de s54 etait ouverte a la generation de son rapport. Le journal regenere la section de s54 sur sa fenetre close (14:00) : 2473 tours, 1 327 873 d'output (`sofia-sm` 130 a 239 tours) ;
le `RAPPORT-tokens.md` de s54 n'est pas reecrit.

## Ce que ce rapport ne couvre pas

Les comptes de tokens ne disent pas ce que chaque agent a produit. Les chiffres de capacite sont ceux de `nadia-prof`, recomptes en arithmetique et non remesures. La CI distante n'a pas ete lue. Aucun Done n'est prononce ici.
