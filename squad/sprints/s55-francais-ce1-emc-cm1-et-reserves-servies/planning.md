# [Sprint s55] Planning - 2026-10-08

- **Horodatage de debut** : 2026-10-08 14:43 (UTC+02:00), lu par `date` au moment de l'ecriture.
- **Anime par** : sofia-sm, casquette Scrum Master. Aucune story de developpement portee par moi dans ce fichier : les deux stories d'outil
  qui m'iraient (A7, A8 de la retro de s54) sont proposees, et s'executeraient sous ma casquette developpeuse, dans une tache separee qui annonce la casquette.
- **Participants** : aucun agent invoque. Ce planning constate l'etat du depot, mesure, decoupe et affecte.
  **Sources lues, par auteur** : sofia-sm (`proposition-s55.md`, `review.md`, `retro.md`, `RAPPORT.md`, `planning.md` de s54) ; camille-po (`DEC-0114`
  sections 0.4, 5, 6, 7 et amendements A.18 a A.26, backlog "Sprint 54", `MATRICE-couverture.md`) ; nadia-prof (`ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`
  sections 2, 3, 9 ; `ARBITRAGE-PREALABLE-s52-instances-ce1.md` section 5 ; `PAR-8` dans l'arbitrage de s29 section 13.5, `PAR-9` dans celui de s30,
  `PAR-13` dans celui de s32, `PAR-14` dans celui de s33 ; `arbitrage-nadia-c.md`) ; theo-archi (`avis-faisabilite.md` de s54, T6 et son amendement de cloture).
- **Slug** : `s55-francais-ce1-emc-cm1-et-reserves-servies`. Le dossier n'existait pas ; il est cree par ce planning.
- **Lignee verifiee avant ecriture** : branche `s55-sofia-planning`, `git log -1 --format=%h` rend `fbe07d79` (tip de `round-two`), `bash outils/verifier-lignee.sh`
  sort 0, `git status --short` est vide. `git rev-list --count origin/round-two..HEAD` rend 0 : **s53 et s54 sont pousses**.
- **CI distante** : `gh run list --branch round-two` rend `HTTP 404` depuis ce poste (comme au planning de s54). **Aucun verdict de CI n'est lu** sur `fbe07d79` : question U3.
- **`lessons.md`** : n'existe pas dans le depot (`find . -iname lessons.md` ne rend rien). La memoire des corrections est `retro.md` et `squad/README.md`. La retro de s54
  a ete relue : ses actions A1 a A13 sont dans la section "Actions de la retro de s54" ci-dessous, avec leur proprietaire.

## Objectif du sprint

Gele : **brancher quatre paquets au catalogue et les presenter a `nadia-prof` (deux de francais CE1 sur l'instance A, deux d'EMC CM1 sur l'instance B), et solder les
reserves non bloquantes de deux jeux deja servis (`EXC-R15`, `S54-ART-RES-R1` a `R3`) dont un texte servi aujourd'hui est faux.** Un troisieme lot de francais CE1 est propose, pas
retenu (O1, Q3). Au mieux quatre lignes de matrice ferment : **137/182 (75,27 %) a 141/182 (77,47 %)**, ou 142/182 (78,02 %) avec le troisieme lot. Ce n'est pas une promesse : un
Done pedagogique refuse reste un refus. **Rien n'est engage tant que la `DEC` d'ouverture de s55 n'existe pas** (critere C1).

## Provenance, et ce qui manque a la cloture de ce planning

- **L'ordre de planifier est un message relaye par l'agent qui m'a lance**, qui le donne comme arbitrage de l'utilisatrice a s54 : `G1`/`G2` (francais CE1), puis `G6`/`G5` (sciences et EMC CM1),
  puis arbitrage de `nadia-prof` sur `G7` a `G11`, puis `G3`/`G4`. C'est la forme 3 de `squad/README.md` section 2 bis : **elle fonde l'ordre dans lequel le travail se fait, jamais sa raison**.
  Je n'ai pas les mots de l'utilisatrice. **`DEC-0114` reste `Proposee`** (section 0.4) tant que l'ordre au-dela de s54 n'est pas ecrit par elle en forme 2, avec sa date,
  ou repris sous son motif par la `DEC` d'ouverture de s55.
- **`DEC-0114` A.20** : la borne de 9,5 de s54 *"ne fait aucune jurisprudence"*. **Ce planning compte selon `PAR-9` telle qu'ecrite : somme des budgets par instance egale a la borne de 8,0
  (`PAR-8`), deux instances, 4,0 chacune au plus.** Aucune troisieme instance n'est ouverte ; une troisieme demanderait son propre ecart, son propre motif et sa propre autorisation.
- **Aucune `DEC` d'ouverture de s55 n'existe** (`ls squad/decisions/produit` s'arrete a `DEC-0114`). **Aucun avis de faisabilite de `theo-archi`** et **aucun arbitrage prealable de `nadia-prof`**
  n'existent pour s55 (`ls squad/conformite | grep -c s55` rend 0). Je planifie ce que l'ordre relaye appelle et je n'ecris aucune `DEC`. Conformement a l'amendement de s48, le planning affecte
  sans attendre l'avis ; `review.md` dira comment l'avis l'a rattrape.
- **Lecon de s54, appliquee ici** : le planning de s54 n'a jamais ete amende pour l'instance C (retro de s54, section 3 ; obstacle 22). **A s55, aucun poste n'existe sans ligne dans ce planning ou dans un
  amendement date de lui, nominatif, avant l'ouverture du worktree concerne**, avec les noms de branche, de worktree et de scratchpad de toute instance ouverte apres coup (action A5).

## Le cout reel d'un branchement, mesure sur les six de s54

Mesure : `git show --stat` des six commits de livraison de s54 (`git log 52e2b0cd..HEAD -- adapters/adapter-content/src/main/resources/catalogue.yaml`).

| Branchement s54 | Commit | Fichiers | Lignes (+/-) | Dont test du paquet |
|---|---|---|---|---|
| `ce1-futur` | `95aa8f39` | 6 | 493 / 2 | 445 + 26 de fixtures fautives |
| `ce1-passe-compose` | `557ebd8a` | 6 | 523 / 19 | 453 + 34 de fixtures |
| `cm1-emc-atteinte-autrui` | `ecf34b10` | 4 | 204 / 4 | 184 |
| `cp-arts-oeuvres-familles` | `920e19e2` | 3 | 156 / 0 | 140 |
| `cm1-sciences-terre-jour-nuit-saisons` (reprise) | `269ce5ce` | 4 | 28 / 8 | modifie : 6 |
| `cm1-sciences-environnement-activite-humaine` (reprise) | `fb42d5f1` | 3 | 21 / 3 | modifie : 6 |

**Pour s55** : un branchement de francais CE1 coute environ **500 lignes en six fichiers** (deux commits mesures : 493 et 523), un branchement d'EMC CM1 environ **200 lignes en quatre fichiers**
(un commit mesure). Chaque branchement ajoute au moins : 6 lignes a `catalogue.yaml`, un `Paquet<Nom>Test`, 10 a 13 lignes a `TexteAccentueExposeParLePortEntrantTest`. Le test du paquet a ete tenu pour du
par `theo-archi` a s54 (T1) : *les conditions comptables ne vivent que dans ces tests*. **La cadence n'est pas le plafond** : le plafond d'un sprint est la regle de capacite de `nadia-prof`
(planning de s54, "Cadence mesuree"). Total de tests a la cloture de s54 : **2216** (reacteur complet, `RAPPORT.md` de s54) ; **a remesurer**, un compte annonce par un agent ne se relaie pas.

**Taux de refus au premier passage, tels que le depot les porte** : s54, 0 sur 6 audits (aucun REFUSE, deux verdicts sous reserve dont une levee le jour meme) ; s52, 1 sur 6 ; s51 B, 2 sur 3.
Un refus coute 0,5 de rejeu. **Mesure de s54 : la marge de A (0,5) a ete consommee en entier par des complements de cloture** (rejeu `PCO-CE1-R1` 0,25, `EXC-R15` 0,25), *"aucun refus n'a ete paye"*
(arbitrage de A, section 2). Ce planning separe donc la marge de rejeu d'une reserve non allouee : voir "Capacite".

## Les paquets restants, par groupe (comptes relances sur `fbe07d79`)

Source : `grep -m1 '^lignesDeReferentiel'`, `grep -c '^ *- enonce:'`, `grep -c '^# DEGRADE'` sur chaque fichier de `contenu/` ; `grep -c "paquet: <nom>$" catalogue.yaml` ; auteur par
`git log --diff-filter=A --format=%s`. **Entrees de catalogue : 0 sur les 16 paquets ci-dessous** (C3 rouge sur l'existant). Les comptes de `proposition-s55.md` (13, 3, 16, 7 = 39) sont repris ; les 16 paquets
de `G7` a `G11` et les 7 de `G3`/`G4` ne sont pas relus ici.

| Groupe | Paquet (ligne) | Items | DEGRADE | Auteur | Touche par s53 |
|---|---|---|---|---|---|
| `G1` | `ce1-accord-sujet-verbe` (`-14`) | 24 | non | `[MARC]` | non |
| `G1` | `ce1-classes-de-mots` (`-12`) | 24 | non | `[MARC]` | non |
| `G1` | `ce1-verbe-sujet` (`-13`) | 24 | non | `[MARC]` | non |
| `G1` | `ce1-negation` (`-11`) | 24 | non | `[MARC]` | non |
| `G1` | `ce1-phrases-types` (`-10`) | 24 | non | `[MARC]` | non |
| `G1` | `ce1-familles-de-mots` (`-24`) | 24 | non | `[MARC]` | non |
| `G1` | `ce1-accord-groupe-nominal` (`-15`) | 24 | non | `[MARC]` | **oui (P1)** |
| `G1` | `ce1-texte-narratif` (`-04`), `ce1-inference` (`-06`) | 20 + 20 | non | `[LEA]` | non |
| `G1` | `ce1-pronoms` (`-05`), `ce1-texte-documentaire` (`-07`) | 20 + 20 | non | `[LEA]` | **oui (P2, P8)** |
| `G2` | `ce1-ordre-des-mots` (`-08`), `ce1-relire-une-phrase` (`-09`) | 20 + 20 | **oui** | `[LEA]` | non |
| `G5` | `cm1-emc-respect-differences` (`EMC-CM1-03`) | 11 | non | `[MARC]` | non (P9, accepte sans changement) |
| `G5` | `cm1-emc-principes-republique` (`EMC-CM1-06`) | 11 | non | `[MARC]` | **oui (P3, P4 : six indices)** |
| `G5` | `cp-emc-personnes-differentes` (`EMC-CP-04`) | 11 | non | `[MARC]` | oui ; **sous refus** (`DEC-0081` section 7) : pas un lot |

`G6` est fait (`SCI-CM1-15`, `-18` fermees a s54). Restent 13 paquets de `G1`/`G2` (11 + 2), 3 de `G5`.

**Le francais CE1 reste le chemin critique.** 13 lots, une instance (`PAR-2`), trois lots au plus par instance (`PAR-8`). Compte, sous la condition qu'aucun refus ne depasse la marge et que chaque sprint
suivant prenne trois lots : **O1 (3 lots a s55)** laisse 10 lots, soit 3, 3, 3, 1 (s56 a s59) ; **O2 (2 lots a s55)** laisse 11 lots, soit 3, 3, 3, 2 (s56 a s59). **La fin est la meme, s59** ; l'ecart est la charge de s59
(1 lot ou 2) et la reserve de s55 (0,0 ou 1,0). Le decoupage en deux instances (`DEC-0114` section 7.2) ramenerait le chiffre a 3 sprints selon `camille-po` ; il exige l'accord de `nadia-prof` sur `PAR-2`
(*"non amende aujourd'hui"*, arbitrage de A, `Q8`), l'avis de `theo-archi` sur `ADR-0065`, et `G3` (`FRA-CE1-03`, `-21`, plafonnes `en cours`) n'est place dans aucun groupe. **Non engage.**

## Capacite : ce que les plafonds de `nadia-prof` permettent

Regles lues dans leur texte, non amendees par ce planning :

| Regle | Ce qu'elle dit | Effet ici |
|---|---|---|
| `PAR-1`, `PAR-2` | un lot n'est jamais partage ; une matiere et un niveau ne sont jamais partages (EMC CP et EMC CM1 comptent pour une matiere) | francais CE1 sur A seule ; EMC CP, EMC CM1, et toute matiere que B porte, sur B seule |
| `PAR-8` | borne 8,0, plafond dur 5,0 par instance, **au plus trois lots par instance**, marge de rejeu 0,5 nommee et allouee a aucun lot | au plus six lots par sprint a deux instances |
| `PAR-9` | **somme des budgets par instance egale a la borne ; 4,0 par instance, marge comprise** | 8,0 au plus ; `DEC-0114` A.20 : pas de 9,5 |
| `PAR-10` | rejeu 0,5 par paquet ; correction mot pour mot 0,25 ; une correction n'est pas un lot | `EXC-R15` 0,25 ; `S54-ART-RES-R1` a `R3` 0,5 (propose, voir plus bas) |
| `PAR-13` | au plus une instance par sprint prend un terrain dont les conditions n'ont jamais rencontre de contenu | **lecture par `nadia-prof`**, Q3. A s54, elle a lu le francais CE1 comme *"pas un terrain neuf"* et `FUT`, `PCO` comme cousines de `PRS` ; elle ne s'est pas prononcee sur `RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM` |
| `PAR-14` | une reprise dont les corrections touchent plus de la moitie des items se paie au tarif d'un lot | `S54-ART-RES-R1` a `R3` touchent les items 2, 3, 4, 11 de 12 et la consigne : **sous la moitie, lecture a elle** |
| `PAR-7`, `PAR-4` | une instance porte l'accumulation et la passe transverse | passes ecrites a s29, s30, s35, s54 (`find squad -iname 'PASSE-TRANSVERSE*'`) ; **si une passe est due a s55 est a elle** (Q7) |

**Tarifs proposes** (bareme du depot, a confirmer par `nadia-prof` dans son arbitrage prealable de s55 ; je n'en ecris aucun comme le sien) :

| | **Instance A** (francais CE1) | **Instance B** (EMC CM1, EMC CP, et arts plastiques CP si Q5 le dit) |
|---|---|---|
| Lots | `L1`, `L2` : 1,0 + 1,0 | `EMC-CM1-03`, `EMC-CM1-06` : 1,0 + 1,0 |
| Arbitrage prealable de l'instance | 0,5 (s54 A : 51 items lus, 0,5) | 0,5 (s54 B : quatre paquets lus, 0,5) |
| Corrections et reserves | aucune | `EXC-R15` 0,25 ; `S54-ART-RES-R1` a `R3` 0,5 |
| Marge de rejeu nommee, allouee a aucun lot | 0,5 | 0,5 |
| **Total / 4,0** | **3,0** | **3,75** |
| Reserve non allouee, hors marge | **1,0** | **0,25** |
| Lots / 3 | 2 | 2 |

**Engage si la `DEC` le retient : 6,75 sur 8,0.** Les deux instances ne partagent aucune matiere (`PAR-2`) et aucun lot n'est partage (`PAR-1`). **Les 1,0 de reserve de A ne sont pas dans la marge** : ils sont la
place de la **decision O1 / arbitrage**, a `camille-po` et `nadia-prof` (Q3, Q4) :

| Emploi de la reserve de A (1,0) | A | Somme | Ce qu'il achete | Ce qu'il coute |
|---|---|---|---|---|
| **O1** : un troisieme lot de francais CE1 (`L3`) | 4,0 / 4,0, **sans reserve** | 7,75 / 8,0 | une fermeture de plus (142/182) ; 10 lots restants | un complement de cloture ne se paie plus que sur la marge (un seul rejeu) ; `PAR-13` si `nadia-prof` lit `L3` comme terrain neuf |
| **O3** : l'arbitrage sur `G7` a `G11` (16 paquets, 216 items), s'il est chiffre a 1,0 au plus | 4,0 / 4,0 | 7,75 / 8,0 | 16 paquets sans plafond ecrit deviennent jugeables ; le rang 3 de l'ordre relaye | un lot de francais CE1 de moins : 11 restants |
| **O2** : rien, reserve gardee | 3,0 / 4,0 | 6,75 / 8,0 | deux rejeus de plus sans depasser la borne | aucune fermeture de plus |

**Prix de l'arbitrage sur `G7` a `G11`** : le seul repere du depot est 0,5 pour 51 items lus (A, s54) et 0,5 pour quatre paquets lus (B, s54). **Je n'extrapole pas a 216 items** ; le prix et la place sont a elle (Q4). **Sa place dans l'ordre
relaye est le rang 3, derriere `G1`/`G2` et `G6`/`G5` : l'emploi O1 precede donc O3 dans cet ordre** ; je le dis sans le trancher, parce que l'ordre est de forme 3.

## Ce qui est retenu (sous C1) et ce qui est propose

**Retenu** = story du planning, avec affectation nominative, dont aucun edit ne commence avant la `DEC` d'ouverture. **Propose** = hors du compte de 6,75, a decider avant qu'un worktree s'ouvre.

### Stories retenues

Tarifs de `nadia-prof` : **proposes**, voir ci-dessus. Aucun n'est ecrit par moi.

| Story | Contenu | Competence visee | Dev (affecte a) | Instance, tarif propose |
|---|---|---|---|---|
| `S54-EXC-CATEGORIE` (`EXC-R15`) | **Premiere a fusionner, avec `S54-ART-RES-R1` a `R3`** : le nom donne a l'exclusion au CP est *faux* (*"une moquerie qui se repete"*), jeu servi. Quatre lignes, mot pour mot de `nadia-prof` : 3 de `cp-emc-exclusion.yaml`, 1 de `catalogue.yaml` | EMC / CP / `EMC-CP-03`, `couvert-valide`, non rouverte | marc-dev (auteur, s31) | B, 0,25 |
| `S54-ART-RES-R1` a `R3` | Un seul commit : consigne du jeu des familles d'oeuvres (fausse pour les items 7 et 10), indice de l'item 11, au moins un metier au feminin. 3 fichiers, 6 a 9 lignes (`review.md` de s54 section 12) | arts plastiques / CP / `ART-CP-10`, **reste `non couvert`** | lea-dev (auteure) | **B si Q5, 0,5 propose** (un rejeu pour R1 a R3, `PAR-10`) ; 0,25 si elle ecrit les trois textes |
| `S55-BR-FRA-CE1-L1` | Brancher `ce1-accord-sujet-verbe` (24 items). **Proposition d'ordre, a elle** : il precede `relire-une-phrase` (`RLP-CE1-1` renvoie a `-14` et `-15`) ; non touche par s53 ; la frontiere `-14`/`-17` touche une ligne deja `couvert-valide` ; sa condition n'est pas dans les sept que `PAR-13` nomme comme neuves | francais / CE1 / `FRA-CE1-14` | marc-dev (auteur) | A, 1,0 |
| `S55-BR-FRA-CE1-L2` | Brancher `ce1-classes-de-mots` (24 items), `-12`. **Proposition** : meme critere que `L1` ; sa frontiere avec `-13` (`VSJ-CE1-1`) se juge avec `ce1-verbe-sujet` | francais / CE1 / `FRA-CE1-12` | marc-dev (auteur) | A, 1,0 |
| `S55-BR-EMC-CM1-03` | Brancher `cm1-emc-respect-differences` (11 items). Accepte sans changement a s53 (P9) : le moins touche, donc le premier | EMC / CM1 / `EMC-CM1-03` | lea-dev (non auteure : voir justification 2) | B, 1,0 |
| `S55-BR-EMC-CM1-06` | Brancher `cm1-emc-principes-republique` (11 items). *"La ligne la plus dangereuse de la matiere"* (matrice) ; six indices corriges a s53 (P3, P4), six points que `nadia-prof` ne juge qu'en jouant | EMC / CM1 / `EMC-CM1-06` | lea-dev (non auteure) | B, 1,0 |

**Les deux paquets de A (`L1`, `L2`) sont une proposition** : le choix des trois premiers paquets de francais CE1 depend de la lecture de `PAR-13` par `nadia-prof` sur `RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM`. Les
six paquets qui ne portent aucune de ces sept conditions (`-10` a `-15`) sont les premiers candidats ; `-15` est touche par s53. **Elle peut remplacer ces deux paquets : la story change de nom, l'affectation et le tarif restent.**
La paire `-12`/`-13` pose une question : auditer `classes-de-mots` seul a s55 et `verbe-sujet` a s56, ou les deux ensemble (Q3).

### Stories proposees, non retenues

| Story | Contenu | Pourquoi proposee, pas retenue | Prix propose |
|---|---|---|---|
| `S55-BR-FRA-CE1-L3` (O1) | Troisieme lot de francais CE1 ; candidat : `ce1-verbe-sujet` (`-13`, 24 items, `[MARC]`) | emploi de la reserve de A : Q3, Q4 | A, 1,0 ; dev : marc-dev, **apres** `L1`, `L2` et `EXC-R15` (un seul worktree a la fois) |
| `S54-PONCT-GN-INDICES` | 12 lignes `indice` de `cm1-grands-nombres.yaml` (espace ordinaire avant `: ; ! ?`), cliquet de `TYP-1` de 12 a 0, un commit. Mesure a `fbe07d79` : `grep -cP '^ *indice: ".* [:;!?]'` rend 12 | 0,0 pour `nadia-prof` (note du 2026-10-08) ; relecture de ponctuation par `relecteur-pedagogique`. Je la propose a lea-dev (auteure de la correction des enonces, `8b15e3ba`) | 0,0 ; taille : 2 fichiers, 12 lignes plus `DETTE_TOTALE` |
| `S54-ASCII-APOSTROPHES-TESTS` | `PaquetCm1EmcAtteinteAutruiTest` et `PaquetCm1SciencesActiviteHumaineTest` portent U+2019 litteral (`grep -cP '[\x{2018}\x{2019}]'` rend 1 sur chacun) | **la forme est a `theo-archi`** (backlog) ; designe l'agent | sans objet ; taille : 2 fichiers, 1 ligne chacun |
| `S54-OBS-SCI-CONSIGNE` | consigne du jeu `sciences-extraire-information-cm1` dite deux fois | **criteres non ecrits** (*"a ecrire quand une instance l'engage"*) ; sciences CM1 est a B | non chiffree |
| `S55-OUTIL-TOKENS-SANS-SPRINT` (A8) | `rapport-tokens.py` sans `--sprint` modifie cinq fichiers suivis ; refuser ou ne plus recalculer les anciens | prix de `theo-archi` d'abord ; dev : sofia-sm (casquette developpeuse), tache separee | aucun tarif de `nadia-prof` |
| `S55-OUTIL-JDK-JAVA-TOOL-OPTIONS` (A7) | `outils/mvn-jdk21.sh` rend `inconnue` quand `JAVA_TOOL_OPTIONS` ajoute une ligne a `java -version` ; cas de test dans `outils/mvn-jdk21-test.sh` | forme a `theo-archi` ; dev : sofia-sm (casquette developpeuse) | aucun |
| **Mecanique d'`ART-CP-10`** ("deux oeuvres ensemble") | **Question U2 : je ne la tranche pas.** Prix en tailles (`avis-faisabilite.md` de s54, T6, amendement de cloture) : production **2 fichiers d'`adapter-web`, 20 a 38 lignes**, plus 3 images (74 441 octets) et 30 lignes de registre ; tests **3 fichiers neufs, 5 modifies, 15 a 18 tests, 680 a 1 400 lignes** ; zero ligne dans `domain`, `application`, le chargeur, les POM et la CI ; zero regle ArchUnit neuve. **Hors prix : le paquet** (`cp-arts-oeuvres-familles` ne la sert pas, 0 `illustration`) **et l'audit** | `ADR-0104` est `Accepte` et n'engage ni la construction ni la ligne ; `DEC-0114` section 6 point 4 renvoie le financement a la `DEC` d'ouverture de s55 ; `ART-CP-10` : quatre sprints hors perimetre (s50 a s53), engagee a s54 au branchement du paquet ; un report sans compte n'est pas permis | audit : non chiffre ; dev : non affecte, **a affecter par amendement si l'utilisatrice la finance** |

**Ce que la mecanique achete et ne finance pas** : elle ne ferme pas la ligne seule. Il faut aussi un paquet d'oeuvres illustre (contenu, a ecrire) et un audit d'un paquet neuf par une instance de `nadia-prof`
dont aucune n'est prevue pour arts plastiques CP hors de B (Q5). Un budget d'audit s'ajouterait aux 6,75 ; il tient sous 8,0 uniquement si O1 et O3 ne sont pas pris.

## Ce qui est reporte, et pourquoi

| Poste | Reporte parce que | Ce qui le debloque |
|---|---|---|
| 11 paquets de `G1`/`G2` (O2) ou 10 (O1) | `PAR-2`, `PAR-8` : une instance, trois lots | s56 a s59 ; ou le decoupage en deux instances (Q6) |
| `cp-emc-personnes-differentes` (`EMC-CP-04`) | **sous refus** de s31 (`DEC-0081` section 7) ; pas un lot | decision de `nadia-prof` |
| `G7` a `G11` (16 paquets, 216 items) | aucun plafond ecrit pour un paquet `DEGRADE` texte seul | arbitrage de `nadia-prof` (O3 ou s56) |
| `G3` (2), `G4` (5) | ne ferment aucune ligne ; `G3` sans groupe attribue | rang 4 de l'ordre ; `MUL-CE1-R1` se rouvre a la `DEC` du premier sprint qui porte mathematiques CE1 (`DEC-0114` section 3) |
| Deux fichiers `contenu/` de s51 sans entree de catalogue | refuses a s51, retires par `DEC-0111` section 3 | sans objet a s55 |
| Reserves de `yanis-qa` et `theo-archi` non levees a s53 | lues au branchement du paquet concerne | le sprint qui branche chacun |

**Premier report de chacune des stories ouvertes de s54 : aucun** (`review.md` section 12). Un second report d'une story de s55 n'est permis qu'avec un acte ecrit (`DEC-0112` section 6).

## Actions de la retro de s54 : ou elles tombent

| # | Action | Proprietaire | Dans ce planning |
|---|---|---|---|
| A1 | `DEC` d'ouverture de s55 (sept questions de `proposition-s55.md` section 5) | `camille-po`, utilisatrice | Questions Q1 a Q7 ; **C1** |
| A2 | Pousser s53 et s54, lire la CI **en nommant le commit** | utilisatrice | pousse (origine = `fbe07d79`) ; verdict non lu : U3 |
| A3 | Garde-fou de contenu sur les motifs de refus de sciences (mutation "le printemps" verte) | `theo-archi` (avis), `nadia-prof` (besoin), `yanis-qa` (mutation) | **C8** et T2 ; aucun paquet de sciences n'est branche a s55, la regle vaut pour les motifs des quatre paquets de s55 |
| A4 | Nommer le fichier du resultat ecrit de tout critere "relu par le developpeur" | `sofia-sm`, `marc-dev`, `lea-dev` | **C7** : `rapport-marc-dev.md`, `rapport-lea-dev.md`, dossier de sprint. **Resultat de `TRV-CE1-5` (s54) : les devs l'ecrivent s'ils l'ont, sinon disent qu'ils ne l'ont pas** : ligne de chaque rapport |
| A5 | Amender le planning au geste | `sofia-sm` | section "Isolation" ; regle suivie |
| A6 | Diff de `s54-nadia-c` (un fichier par commit, aucun fichier de A ou B) ou dire que ce n'est pas faisable | `yanis-qa` | tache de lecture, hors lot |
| A7, A8 | `mvn-jdk21.sh` ; `rapport-tokens.py` sans `--sprint` | `theo-archi` (forme, prix), `sofia-sm` (dev) | stories proposees ci-dessus ; **`--sprint` explicite tant que A8 n'est pas fait** |
| A9 | Fichier de relecture de cloture | `relecteur-pedagogique` | `relecture-cloture.md` du dossier, une ligne meme s'il ne trouve rien |
| A10 | Verification de s52 contre les formes (a), (b), (c) | `nadia-prof` | note dans son arbitrage de s55 (Q7) |
| A11 | Trailers | utilisatrice | **tranchee** a s54 ; portee future : le point 1 de `DEC-0110` 17.4 (modele execute) reste la regle ; ma session execute `Sonnet 5.5`, trailer en consequence ; lecture a confirmer (U4) |
| A12 | Actions de s53 restees ouvertes : A2, A9 (`relecture-p11.md` lignes 7 a 9), A11 | leurs proprietaires | A2 ci-dessus ; A9 : `relecteur-pedagogique` |
| A13 | Recompter tout chiffre de capacite repris d'un message | `camille-po`, `sofia-sm` | **fait ici** : A 3,75 + B 3,25 + C 1,0 = 8,0 repris de `DEC-0114` A.26 et des arbitrages (non recomptes par moi : les tarifs sont les siens) ; **137/182 recompte par la commande de `MATRICE-couverture.md`** (section "Methode du compte") : `couvert-valide` 137, `en cours` 4, `non couvert` 41, `non couvrable en l'etat` 76, total 258, denominateur 182 |

## Affectations nominatives (`PAR-16`)

| Agent | Taches | Couloir |
|---|---|---|
| **nadia-prof, instance A** | arbitrage prealable de s55 (Q3, Q4, Q7), audits de `L1`, `L2` (et `L3` si O1) | A, scratchpad dedie |
| **nadia-prof, instance B** | rejeu `EXC-R15`, rejeu `S54-ART-RES-R1` a `R3` (si Q5), audits d'`EMC-CM1-03`, `-06` | B, scratchpad dedie |
| **camille-po** | `DEC` d'ouverture de s55 (Q1 a Q7) ; stories au backlog (`S55-BR-*`) ; fiches et matrice au fil des verdicts ; place de `FRA-CE1-03`, `-21` | decision |
| **theo-archi** | avis de faisabilite (section ci-dessous) ; forme de `S54-ASCII-APOSTROPHES-TESTS` ; prix de A7, A8 | decision |
| **marc-dev** | dans cet ordre, un seul worktree a la fois : `S54-EXC-CATEGORIE`, `S55-BR-FRA-CE1-L1`, `S55-BR-FRA-CE1-L2`, (`L3` si O1) | un worktree |
| **lea-dev** | dans cet ordre : `S54-ART-RES-R1` a `R3` (si Q5), `S55-BR-EMC-CM1-03`, `S55-BR-EMC-CM1-06`, (`S54-PONCT-GN-INDICES` si retenue) | un worktree |
| **sofia-sm (dev)** | A7, A8 si assignes, apres l'avis de `theo-archi`, **tache separee qui annonce la casquette** ; je ne les ouvre pas dans ce planning | un worktree |
| **yanis-qa** | lecture de testabilite des criteres, avant la premiere livraison ; A6 ; rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes | cloture |
| **relecteur-pedagogique** | relecture de fin de sprint ; A9 | cloture |
| **ines-design** | aucune (aucun des quatre paquets n'est illustre) | - |

Justification :

1. **Un dev tient un seul worktree a la fois.** marc-dev porte trois postes (quatre avec `L3`), lea-dev trois (quatre avec `PONCT`). Aucun n'est sur deux worktrees en parallele.
2. **Les deux EMC vont a lea-dev, qui n'en est pas l'auteure** (les trois paquets de `G5` sont de `[MARC]`). Raison : marc-dev est l'auteur des cinq paquets que s55 branche (trois de francais CE1, deux d'EMC) et de `cp-emc-exclusion` ;
   lui donner les sept postes laisserait lea-dev avec un seul. Cout nomme : elle lit le paquet et son test avant d'editer, comme pour `cm1-emc-atteinte-autrui` a s54. Si elle devient le chemin critique, je peux deplacer
   `S55-BR-EMC-CM1-03` vers marc-dev sous les conditions de s52 : elle n'a pas commence, marc-dev n'a plus de worktree ouvert, entree au tableau d'obstacles le jour meme.
3. **`S54-EXC-CATEGORIE` et `S54-ART-RES-R1` a `R3` sont les premiers a fusionner** : ce sont les seuls postes qui touchent un jeu servi a l'enfant avec un texte faux (meme raison que `CRP-CM1-R1` a s54). Ils ne dependent d'aucune decision hors C1.

## Isolation et fusion

Une branche, un worktree, un scratchpad par agent ; un scratchpad dedie par instance de `nadia-prof`. Aucun serveur de jeu n'est lance par les developpeurs ; si `nadia-prof` en lance un, un port par instance.
**Aucune instance C a s55.** Si une instance neuve s'ouvre, l'amendement du planning donne ses noms de branche, de worktree et de scratchpad avant son premier edit (action A5).

| Agent | Branche | Worktree (dossier voisin du checkout) |
|---|---|---|
| marc-dev | `s55-marc-branchement` | `../recreo-s55-marc-branchement` |
| lea-dev | `s55-lea-branchement` | `../recreo-s55-lea-branchement` |
| nadia-prof A | `s55-nadia-instance-a` | `../recreo-s55-nadia-instance-a` |
| nadia-prof B | `s55-nadia-instance-b` | `../recreo-s55-nadia-instance-b` |
| camille-po | `s55-camille-dec` | `../recreo-s55-camille-dec` |
| theo-archi | `s55-theo-avis` | `../recreo-s55-theo-avis` |
| relecteur-pedagogique | `s55-rel-redaction` | `../recreo-s55-rel-redaction` |
| yanis-qa | `s55-yanis-qualite` | `../recreo-s55-yanis-qualite` |
| sofia-sm (dev, si A7 ou A8) | `s55-sofia-outils` | `../recreo-s55-sofia-outils` |

**Ouverture des worktrees** : **par l'agent qui m'a lance, une fois ce planning fusionne sur `round-two`** (consigne de cette tache). **Cette tache n'en ouvre aucun, ne fusionne rien et ne pousse rien.**
**Longueur des chemins** : le chemin suivi le plus long fait 219 caracteres a `fbe07d79` (`git ls-files | awk '{ print length($0) }' | sort -n | tail -1`) ; les noms de dossier ci-dessus ont 20 a 27 caracteres,
soit 249 a 256 avec la formule du planning de s54 (nom + 10 de prefixe de poste) : marge de 4 a 11 caracteres sous 260, la plus courte pour `recreo-s55-marc-branchement` et `recreo-s55-nadia-instance-a` et `-b` (27). Non verifie sur un autre poste.

**Ordre de fusion** : decisions d'abord (`DEC` d'ouverture, arbitrage prealable, avis), puis `S54-EXC-CATEGORIE` et `S54-ART-RES-R1` a `R3`, puis les branchements de marc-dev, puis ceux de lea-dev. `catalogue.yaml`,
`TexteAccentueExposeParLePortEntrantTest` et `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sont modifies par les deux couloirs : on garde les ajouts des deux cotes, jamais une assertion affaiblie.
**`bash outils/mvn-jdk21.sh -B clean verify` sur chaque commit de fusion. Tout compte de tests est celui du reacteur complet sur l'arbre fusionne, jamais la somme de deux branches.** Dernier total connu : **2216** (s54), a remesurer.
**Lignee verifiee avant chaque fusion** (`bash outils/verifier-lignee.sh`), par moi, meme quand l'agent dit l'avoir fait.

## Criteres d'acceptation

**Communs** (chacun est une commande ou une lecture) :

- **C1 - decision d'abord.** Aucun branchement ni correction n'est edite avant que la `DEC` d'ouverture de s55 existe sur `round-two` (`git show round-two:<fichier>`) et nomme son poste.
- **C2 - un branchement est un diff borne.** `git diff round-two --stat` d'une story de branchement ne montre que : l'entree de `catalogue.yaml`, le test du paquet, ses fixtures fautives, l'inventaire de chaines, les declarations des garde-fous ;
  aucun YAML de paquet sauf correction decidee par `nadia-prof`. Reference mesuree : 3 a 6 fichiers, 21 a 523 lignes (section "Le cout reel").
- **C3 - `grep -c "paquet: <nom>$" adapters/adapter-content/src/main/resources/catalogue.yaml` rend 1** apres livraison, **0 aujourd'hui** pour les quatre paquets retenus (mesure a `fbe07d79`). Les autres paquets : toujours 0 a la cloture.
- **C4 - suite verte, perimetre nomme.** Mesure partielle : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` (`-am` obligatoire). Porte : `mvn -B clean verify` de la racine.
- **C5 - aucun garde-fou assoupli.** Un cliquet ne monte jamais.
- **C6 - jobs nommes** : chaque livraison nomme les jobs de `.github/workflows/ci.yml` qu'elle a lances (`prefixes`, `chemins`, `outils`, `build`) et ne deduit pas les autres.
- **C7 - un resultat "relu par le developpeur" a un fichier.** `squad/sprints/s55-francais-ce1-emc-cm1-et-reserves-servies/rapport-marc-dev.md` et `rapport-lea-dev.md` existent a la cloture (`ls` rend les deux ; **rouge aujourd'hui**).
  Chaque rapport porte, par paquet branche, une ligne **"relu" ou "non relu"** pour les regles que `nadia-prof` nomme dans son arbitrage prealable (`TRV-CE1-5` pour les paquets de francais CE1, `EMC-9` pour l'EMC), avec le resultat. Un dev qui ne l'a pas fait l'ecrit.
- **C8 - chaque motif de refus ecrit mot pour mot par `nadia-prof` a un test qui le mord, ou une ligne "non garde : <motif>" dans le rapport du dev.** `theo-archi` dit si la regle est du (A3, T2). Tant qu'il ne l'a pas dit, la ligne "non garde" suffit.
- **C9 - un branchement s'arrete au verdict.** Aucune ligne de matrice ne change de statut avant le verdict ecrit de `nadia-prof` dans la fiche ; la mention `MNC-1` est reproduite mot pour mot quand elle est due. **Le planning ne prononce aucun Done.**

**Specifiques** :

| Story | Critere | Rouge sur l'existant ? |
|---|---|---|
| `S54-EXC-CATEGORIE` | sur `cp-emc-exclusion.yaml` : `grep -c 'bonneReponse: "une moquerie qui se répète"'` rend 3 aujourd'hui et doit rendre **1** ; `grep -c "une mise à l'écart qui se répète"` rend 0 et doit rendre **3** ; sur `catalogue.yaml`, `grep -c "dis si c'est une moquerie qui se répète"` rend 1 et doit rendre **0** ; `git diff` : trois lignes du paquet, une du catalogue ; `PaquetCpEmcExclusionTest` reste vert | oui (mesure a `fbe07d79` : 3, 0, 1) |
| `S54-ART-RES-R1` a `R3` | `grep -c 'va avec la statue, le tableau ou la photographie'` sur `catalogue.yaml` rend 1 et doit rendre **0** ; la ligne `indice` de l'item 11 contient `peinture` ou `peint` (0 aujourd'hui, doit etre 1) sans `peintre` ni `pinceau` ; `grep -cE "une peintre\|une photographe\|une sculptrice"` sur le paquet rend 0 et doit rendre au moins **1**, et la forme masculine de ce metier rend 0 ; l'entree garde `paquet: cp-arts-oeuvres-familles` et ni titre ni consigne ne contiennent `reconna` ou `rapproch` ; **un seul commit** | oui (1, 0, 0) |
| `S55-BR-FRA-CE1-L1`, `-L2` | C3 ; C7 pour `TRV-CE1-5` ; `PaquetCe1<Nom>Test` ajoute au moins un test ; `git diff round-two --stat` borne par C2 | oui |
| `S55-BR-EMC-CM1-03`, `-06` | C3 ; C7 pour `EMC-9` (aucun item ne laisse croire a l'enfant qu'il vient d'alerter quelqu'un) ; pour `-06`, les six indices de P3, P4 sont lus et le rapport dit lesquels | oui |
| `S54-PONCT-GN-INDICES` (si retenue) | `grep -cP '^ *indice: ".* [:;!?]'` sur `cm1-grands-nombres.yaml` rend 12 et doit rendre **0** ; la meme avec `\x{00A0}` rend 0 et doit rendre 12 ; seule l'espace change ; cliquet de `TYP-1` de 12 a 0 ; un seul commit | oui (12, 0) |
| `S54-ASCII-APOSTROPHES-TESTS` (si retenue) | `grep -cP '[\x{2018}\x{2019}]'` rend 1 sur chacun des deux fichiers et doit rendre 0 ; les deux tests restent verts et reconnaissent les deux apostrophes | oui |

**Ce que `yanis-qa` doit dire**, avant la premiere livraison : lesquels de ces criteres un automate ne juge pas (mes lectures : `TRV-CE1-5`, `EMC-9`, les six points de `principes`, les rejeus de `EXC-R15` et d'`ART-RES`, le jugement de C8).

## Avis de faisabilite - theo-archi

**Aucun avis ecrit a la cloture de ce planning.** Je ne l'ai pas invoque et je n'ecris pas son avis pour lui. Le tableau dit ce qu'il doit rendre.

| Question | Objet | Ma lecture (non un avis) |
|---|---|---|
| T1 | Le test du paquet reste-t-il du pour les quatre branchements ? | tenu a s54 (T1 de s54) ; a reconfirmer, chaque paquet porte ses conditions comptables |
| T2 | A3 : un test **de contenu** sur un motif precis de refus est-il du (C8) ? Il a ecarte *"un test par paquet"* comme decoratif ; l'avis dit si un test sur un motif precis ne l'est pas | la mutation "le printemps" est restee verte a s54 |
| T3 | Les listes de garde-fous que modifient encore quatre branchements (inventaire de chaines, rang, `TYP-1`) | mesure a s54 : 10 a 13 lignes d'inventaire par branchement |
| T4 | Forme de `S54-ASCII-APOSTROPHES-TESTS`, et agent | backlog : *"la forme est a `theo-archi`"* |
| T5 | Si Q6 (deux instances de francais CE1) : comment deux instances n'amendent pas `ADR-0065` en meme temps | condition de `nadia-prof` (arbitrage de s52, section 5) |
| T6 | Mecanique d'`ART-CP-10` : le prix en tailles est ecrit ; confirmer qu'il vaut pour l'arbre de s55 si l'utilisatrice la finance | `avis-faisabilite.md` de s54, amendement de cloture |
| T7 | A7, A8 : forme et prix | - |

## Verification poste/story (`PAR-16`, `squad/README.md` section 4)

Faite a la cloture de ce planning. **Aucune `DEC` de composition n'existe pour s55** : la table est la liste de l'ordre relaye, point par point.

| Poste de l'ordre relaye ou des sources | Story | Affectation nominative | Issue |
|---|---|---|---|
| `G1`/`G2` francais CE1 : 13 paquets | `S55-BR-FRA-CE1-L1`, `-L2` ; `L3` proposee | marc-dev ; instance A | **2 retenus** ; 1 propose (O1) ; **11 ou 10 reportes avec motif** |
| `G6` sciences CM1 | aucune | - | **fait a s54** (aucun paquet restant) |
| `G5` EMC : 3 paquets | `S55-BR-EMC-CM1-03`, `-06` | lea-dev ; instance B | 2 retenus ; `EMC-CP-04` **sous refus, pas un lot** |
| Arbitrage de `nadia-prof` sur `G7` a `G11` | aucune story de dev | instance A (reserve) ou B | **propose (O3)**, prix et place a elle (Q4) |
| `G3`/`G4` | aucune | - | **non engage** : ne ferme rien |
| `EXC-R15` | `S54-EXC-CATEGORIE` | marc-dev ; instance B | **retenu** |
| `S54-ART-RES-R1` a `R3` | idem | lea-dev ; instance B si Q5 | **retenu sous Q5** ; sans instance, report numero 1 |
| `S54-PONCT-GN-INDICES` | idem | lea-dev (propose) | propose |
| `S54-ASCII-APOSTROPHES-TESTS` | idem | `theo-archi` designe | propose |
| `S54-OBS-SCI-CONSIGNE` | idem | aucune | **non engage** : criteres non ecrits |
| Mecanique d'`ART-CP-10` | aucune | aucune | **question U2**, non engage |
| Passe `PAR-4` | aucune | instance A | **a elle** (Q7) |

**Aucun poste retenu sans story, aucun sans affectation nominative.** Ecart signale : les stories `S55-BR-*` n'existent pas au backlog (qui est a `camille-po`) ; ce planning les nomme et les affecte, il ne les ecrit pas a sa place.

## Risques

| Code | Risque | Ce qui le contient |
|---|---|---|
| R1 | Le perimetre repose sur un message d'agent et aucune `DEC` d'ouverture ; `DEC-0114` `Proposee` (comme s54, R1) | C1 ; Q1 ; amendement de ce fichier avant tout worktree concerne |
| R2 | **Tarifs inconnus** : les prix de s55 sont ceux du bareme et de s54, pas ceux de `nadia-prof` pour ces paquets | Q3, Q4 ; le plan tient a 1,0 par lot ; il se resserre sinon |
| R3 | **`PAR-13`** : `nadia-prof` n'a pas lu sept conditions de francais CE1 ; trois lots (O1) en terrain neuf sur A | Q3 ; la liste des candidats `-10` a `-15` evite ces sept conditions |
| R4 | **La marge se consomme par complements, pas par refus** (s54, A : 0,5 sur 0,5) ; sans reserve (O1), un complement mange le rejeu | reserve separee de la marge dans le tableau de capacite ; un complement de cloture s'ecrit au tableau avec son poste |
| R5 | `principes` : six points que `nadia-prof` ne juge qu'en jouant ; un refus consomme la marge de B, qui porte deja 0,75 de reserves | `-03` d'abord ; un refus de plus est dit non ferme a la review |
| R6 | B porte trois matieres (EMC CM1, EMC CP, arts plastiques CP) si Q5 va a B ; la charge de `nadia-prof` est deja 31,8 % des tours a s54 | Q5 ; si elle refuse, `ART-RES` est un report numero 1 |
| R7 | Un branchement entre dans des garde-fous qui jugent tout `contenu/` | C4 : reacteur complet a chaque fusion ; `-am` obligatoire |
| R8 | Les deux instances de `nadia-prof` ecrivent la meme ressource sans le savoir (forme (c), s50) | scratchpad et worktree dedies ; `REGISTRE-identifiants.md` ecrit depuis le worktree de l'instance |
| R9 | Un critere non automatisable reste sans rapport ecrit (s54 : `TRV-CE1-5`, `EMC-9`, 2 sur 2) | C7 : le fichier est nomme ici |
| R10 | Le francais CE1 est le chemin critique et ne se partage pas : s59 au plus tot | Q6 ; O1 ne change pas la fin, seulement la charge de s59 |

## Questions ouvertes

Ecrites comme ouvertes ; aucune n'est tranchee par ce planning.

| Code | Question | Route a | Artefact attendu |
|---|---|---|---|
| Q1 | **`DEC` d'ouverture de s55** : retient-elle les stories ci-dessus ; reprend-elle l'ordre sous son motif (ce qui passe `DEC-0114` a `Acceptee`, section 0.4) ou attend-elle les mots de l'utilisatrice ; reconnait-elle le message relaye comme source de l'ordre ? | `camille-po`, utilisatrice | `DEC` |
| Q2 | **Mecanique d'`ART-CP-10`** : financer (voir U2), et a quel sprint | utilisatrice, `camille-po` | `DEC` |
| Q3 | O1, O2 ou O3 pour la reserve de A ; lecture de `PAR-13` sur `RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM` ; choix des trois premiers paquets ; la paire `-12`/`-13` ensemble ou separee | `camille-po`, `nadia-prof` | `DEC`, arbitrage |
| Q4 | Prix et place de l'arbitrage sur `G7` a `G11` (A en O3, B, ou s56) | `nadia-prof` | arbitrage |
| Q5 | **Quelle instance relit `S54-ART-RES-R1` a `R3`**, sans partager une matiere (`PAR-2`) : B, qui ne porte pas arts plastiques CP a s55 ; ou aucune | `nadia-prof` | une ligne |
| Q6 | Decoupage du francais CE1 en deux instances (`DEC-0114` section 7.2) : le demander ou non ; placer `FRA-CE1-03`, `-21` | `camille-po`, `nadia-prof`, `theo-archi` | proposition ecrite |
| Q7 | Passe `PAR-4` due a s55 ? Verification A10 (formes (a), (b), (c)) | `nadia-prof` | une ligne ; note |

**Questions a l'utilisatrice** (celles que personne d'autre ne peut trancher) :

| Code | Question |
|---|---|
| U1 | **Ordre des matieres** : ses mots en forme 2, avec leur date, pour passer `DEC-0114` a `Acceptee`, ou reprise de l'ordre relaye sous le motif de s55 |
| U2 | **Financer la mecanique d'`ART-CP-10`** ("deux oeuvres ensemble") ? Prix en tailles ci-dessus (production 2 fichiers, 20 a 38 lignes ; tests 680 a 1 400 lignes ; zero ligne dans `domain`/`application`). Hors prix : un paquet illustre a ecrire et un audit non chiffre. A quel sprint ? |
| U3 | **CI** : `origin/round-two` est `fbe07d79`, mais `gh run list` rend `HTTP 404` depuis ce poste. Quel est le verdict de la CI sur ce commit, job par job ? |
| U4 | Trailers : confirmer que la regle de `DEC-0110` 17.4 (modele execute) est la lecture voulue pour s55 |

## Porte technique

- **Juge : la CI, tous les jobs de `.github/workflows/ci.yml`** : `prefixes`, `chemins`, `outils`, `build`. `mvn verify` n'est que le contenu du job `build`. Chaque agent nomme les jobs qu'il a lances.
- **`yanis-qa`** : rapport qualite de cloture sur l'arbre fusionne, quatre jobs nommes, commande exacte, total mesure, origine de l'ecart avec la base (2216). **Je n'annonce aucun total.**
- **`theo-archi`** : confirmation ArchUnit a la cloture ; une regle ArchUnit dans le meme geste si T2 ou T6 en appelle une.
- **Porte pedagogique** : **prononcee par `nadia-prof` seule**, apres avoir joue chaque paquet, avec un motif ecrit dans `squad/conformite/FICHE-<jeu>.md`. **Ce planning ne prononce aucun Done**, ni technique ni pedagogique.
- **Jobs lances pour ce planning** : `chemins` (`git ls-files` hors `verifier-chemins-test.sh` vers `verifier-chemins.sh`, code 0) et `prefixes` (`verifier-prefixes.sh` sur le sujet du commit, code 0).
  **`outils` et `build` ne sont pas lances** : ce planning ne modifie ni code, ni contenu, ni outil, et je ne deduis pas leur resultat.

## Ce que ce planning ne fait pas

- Il ne decide ni les tarifs, ni les plafonds, ni la lecture de `PAR-13`, ni l'ordre des matieres, ni le sort de la mecanique d'`ART-CP-10`, ni le contenu de la `DEC` d'ouverture : il les propose ou les route.
- Il n'ecrit aucune `DEC`, aucun `ADR`, aucun avis a la place de `theo-archi`, aucun arbitrage a la place de `nadia-prof`, et n'amende ni `PAR-2`, ni `PAR-8`, ni `PAR-9`, ni `PAR-13`, ni `PAR-14`.
- Il n'ouvre aucun worktree d'agent, ne fusionne rien et ne pousse rien.
- **Il ne modifie ni le backlog, ni la matrice, ni une fiche, ni le registre, ni `catalogue.yaml`.** La matrice reste a 137/182 ; aucune ligne ne change de statut.

## Decisions prises

Aucune `ADR` ni `DEC` n'est creee ou amendee par ce planning. Cinq decisions de planning, qui relevent de mon role :

1. **Decoupage en stories** : une par paquet branche, une par report ; deux lots par instance retenus, un troisieme et sept stories annexes proposes.
2. **Reserve separee de la marge** : la marge de rejeu de 0,5 n'est pas dans les emplois de la reserve (R4, mesure de s54).
3. **Affectations nominatives** : marc-dev (`EXC-R15`, trois paquets de francais CE1), lea-dev (`ART-RES`, deux paquets d'EMC) ; les deux EMC a lea-dev, qui n'en est pas l'auteure (justification 2).
4. **Fichiers de rapport nommes des le planning** (C7), en reponse a la retro de s54.
5. **Isolation** : un worktree, une branche et un scratchpad par agent, dedies par instance de `nadia-prof` ; aucune instance C.

## Prochaines etapes

Ce planning est fusionne sur `round-two` par l'agent qui m'a lance avant tout worktree. Ensuite, par agent :

- `camille-po` : `DEC` d'ouverture (Q1 a Q7), stories `S55-BR-*` au backlog, place de `FRA-CE1-03`, `-21`.
- `nadia-prof` : arbitrage prealable (Q3, Q4, Q5, Q7) ; audits au fil des livraisons.
- `theo-archi` : avis de faisabilite (T1 a T7).
- `marc-dev`, `lea-dev` : edits sur C1, dans l'ordre ci-dessus ; rapports nommes en C7.
- `yanis-qa` : lecture de testabilite des criteres ; `relecteur-pedagogique` : relecture de cloture.
- `sofia-sm` : verification de lignee a chaque fusion, `review.md` avec le tableau des obstacles, `RAPPORT.md`, `python outils/rapport-tokens.py --sprint s55` (**avec `--sprint` explicite** tant que A8 n'est pas fait), `retro.md`. **Le daily n'est pas journalise.**
- **Utilisatrice** : U1 a U4.
