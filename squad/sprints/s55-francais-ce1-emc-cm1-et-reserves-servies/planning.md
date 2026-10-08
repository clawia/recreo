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


---

## Amendement du 2026-10-08 (`sofia-sm`, ouverture de s55) : aucun poste sans ligne

Le texte ci-dessus est inchange : il dit ce que le planning savait a 14:43. Cet amendement est ecrit avant l'ouverture du premier worktree de developpement (action A5). Il ne decide ni produit, ni architecture, ni pedagogie,
ne prononce aucun Done, et ne touche ni le backlog, ni la matrice, ni une fiche, ni `catalogue.yaml`. Il n'ajoute aucune ligne d'heure de debut ou de fin : celles du debut de ce fichier et de `review.md` restent les seules que `outils/rapport-tokens.py` lit.

- **Heure de l'amendement** : 2026-10-08 16:20 (UTC+02:00), lue par `date`.
- **Sources lues, par auteur** : camille-po (`DEC-0115` sections 0 a 8, amendement A.1 a A.8, backlog "Sprint 55") ; nadia-prof (`ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, 15:44 ; `ARBITRAGE-PREALABLE-s55-instance-b-emc-cm1.md`, 15:35) ;
  theo-archi (`avis-faisabilite.md`, 15:42) ; sofia-sm (ce planning, retro de s54, amendements de s51 et s52).
- **Lignee** : branche `s55-sofia-amendement`, `git log -1 --format=%h` rend `ba47b716` (tip de `round-two`), `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture.
- **Reponses de l'utilisatrice (messages relayes, forme 3, non citees comme ses mots)** : CI verte ; toutes les arts plastiques CP vont a l'instance A ; le sprint demarre. Je n'ai pas lu la CI distante. Mesure locale : la ligne `U4` de `review.md` de s54 a 3 cellules
  (`awk -F'|'`), et `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest -Dsurefire.failIfNoSpecifiedTests=false` (perimetre : cette classe, `-am`) rend 5 tests, 0 echec.
  **A-1 de `theo-archi` est paye par `8f740977`** ; je ne l'ouvre pas comme poste. Cette mesure ne dit rien des autres jobs de la CI.
- **Chemins des commandes ci-dessous** : `catalogue.yaml` et `contenu/<paquet>.yaml` sont sous `adapters/adapter-content/src/main/resources/`. Tous les comptes "rouge" ont ete relances par moi sur `ba47b716`.

### A. Ce qui a change depuis le planning

| Ligne du planning | A l'ouverture | Source |
|---|---|---|
| Objectif gele | Non reecrit, complete : le dispositif d'`ART-CP-10` (T2, T3) est finance ; **l'enfant ne voit rien tant qu'aucun paquet a reproductions n'est au catalogue** ; `ART-CP-10` reste `non couvert`. Les fermetures possibles restent 137/182 a 141/182 (quatre branchements) | `DEC-0115` 5.3 et 5.4, point 7 |
| "Tarifs proposes" 3,0 / 3,75 ; "Engage 6,75 sur 8,0" | **3,75 / 3,75 ; 7,5 sur 8,0** (section B) | `DEC-0115` A.2 |
| B, "Corrections et reserves" : `EXC-R15` 0,25 + `ART-RES` 0,5 | B : `EXC-R15` 0,25 ; **`ART-RES` passe a A, 0,25** | A.2 |
| `S55-BR-EMC-CM1-06`, "B, 1,0" | **1,5** | arbitrage B, section 6 |
| `S55-BR-EMC-CM1-03`, "accepte sans changement a s53 (P9)" | **Faux** : P9 ne decidait que l'absence de ligne `DEGRADE`. Trois items a corriger avant branchement | arbitrage B, sections 1.1 et 2 |
| `S55-BR-FRA-CE1-L1`, `-L2` | Deux conditions avant branchement chacun (`K1` a `K3` ; `K4`, `K5`) | arbitrage A, sections 4 et 5 |
| `S54-ART-RES-R1` a `R3` : "B si Q5, 0,5" | **A, 0,25 mot pour mot** (0,5 si reecrit) ; dev inchange : lea-dev | A.2 |
| Q3, Q4, Q5, Q7 | Q3 : `PAR-13` lue (le francais CE1 n'est pas un terrain neuf), `-14` et `-12` acceptes, paire `-12`/`-13` separable. Q4 : fait, 0,25, 15 paquets sur 16 plafonnes `non couvert`. Q5 : instance A. Q7 : passe `PAR-4` non due (0,0). **Restent ouverts : A10 et le plafond de `G3`** (l'instance A dit ne pas les traiter) | arbitrage A, sections 1, 2.1, 6, 10 |
| O1, O2, O3 | O1 ne tient pas (4,75 sur 4,0) ; O3 fait (0,25) ; O2 : emploi des 0,25 restants, question Q-U4 (section H) | A.3 |
| "il tient sous 8,0 uniquement si O1 et O3 ne sont pas pris" | **Fausse**, remplacee en section F | `DEC-0115` 5.2 |
| C7, ligne `EMC-9` pour `-03` et `-06` | `EMC-9` ne vaut pas sur ces lignes : sans objet. Le developpeur relit `EMC-4`, `EMC-8`, la regle de s53 section 4.1 (un indice ne redit pas la bonne reponse) et, pour `-06`, `BIA-5` | arbitrage B, section 1.2 |
| C2, "aucun YAML de paquet" | Les corrections de nadia-prof sont l'exception que C2 prevoit : le YAML du paquet change, dans le commit de branchement | arbitrages A 9, B 9 |
| "ines-design : aucune" | Une tache (section D) | `DEC-0115` 5.4 |
| "Aucun avis ecrit" de theo-archi | Ecrit. T1 oui ; T2 un test sur P3 dans le poste `-06`, P4 non garde ; T3 une seule liste, l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (`LeRang...` declare deja les quatre paquets) ; T4 echappement, marc-dev ; T5 sans objet ; T6 prix reconfirme, garde-fou 2 en population exacte 0 ; T7 A7 une ligne, A8 refus | avis, sections 2 et 3 |
| Noms de branche et de worktree | Section E, noms effectifs | - |

### B. Capacite (A.2 de `DEC-0115`, tarifs de nadia-prof, recomptes par moi)

| Poste | Instance A | Instance B |
|---|---|---|
| Deux lots | 2,0 (`-14` 1,0 ; `-12` 1,0) | 2,5 (`EMC-CM1-03` 1,0 ; `-06` 1,5) |
| Arbitrage prealable | 0,5 | 0,5 |
| Plafonds de `G7` a `G11` | 0,25 | - |
| Forme d'`ART-CP-10` | 0,25 | - |
| `S54-ART-RES-R1` a `R3` | 0,25 | - |
| `S54-EXC-CATEGORIE` | - | 0,25 |
| Marge de rejeu, allouee a aucun lot | 0,5 | 0,5 |
| **Total / 4,0** | **3,75** | **3,75** |
| Reserve non allouee | 0,25 | 0,25 |

**Somme 7,5 sur 8,0.** A : 2,0 + 0,5 + 0,25 + 0,25 + 0,25 + 0,5 = 3,75. B : 2,5 + 0,5 + 0,25 + 0,5 = 3,75. `PAR-9` tenue, deux lots par instance (`PAR-8`), plafond dur de 5,0 non approche, aucune troisieme instance.
`PAR-11`, non tranchee : en lecture amendee (0,5 de marge par lot neuf), 4,25 chacune, 8,5 en somme, au-dessus de la borne. Risque : une instance ne rejoue qu'un lot.
`ART-RES` ou `EXC-R15` reecrits au lieu d'etre appliques mot pour mot : 0,5 au lieu de 0,25 (`PAR-10`) ; A 4,0 ou B 4,0, reserve 0.
**Hors `PAR`, non comptes** : developpement de T2 et T3, tache d'ines-design, `PONCT`, `ASCII`, A7, A8, A9, lecture de yanis-qa, relecture.

### C. Les corrections de nadia-prof "avant branchement"

1. **Qui** : le developpeur du branchement, **dans le meme commit que le paquet** : le commit de `L1` porte `K1`, `K2`, `K3` et la ligne d'en-tete ; celui de `L2` porte `K4`, `K5` ; celui de `-03` les items 4, 7, 8, le paragraphe `EMC-5` de l'en-tete et la source `HIS-3` de 1948 ;
   celui de `-06` les items 6 et 8 et la source `HIS-3` de 2018. `ART-RES` et `EXC-R15` sont des commits seuls.
2. **Quoi** : les textes sont ceux de nadia-prof, **mot pour mot, et ne sont pas recopies ici** (une copie se perime) : arbitrage A sections 4 (`K1`, `K2`, `K3`) et 5 (`K4`, `K5`) ; arbitrage B sections 2.1 a 2.3, 3.1, 3.2 et 4.2 ; fiche d'`EXC-R15` (bloc du 2026-10-08, section 4).
   Les blocs portent l'espace insecable (U+00A0) devant `?` et `:` et a l'interieur des guillemets francais (`TYP-1`).
3. **Un autre texte** qui atteint la meme barre est accepte et juge contre la barre (arbitrage B, 2). Le developpeur l'ecrit dans son rapport avec le motif. Pour `ART-RES` et `EXC-R15`, le tarif passe a 0,5 (section B).
4. **Recommandations non bloquantes** : `W1` (item 12 de `-14`), `W3` (indices de `-12`), `W4` (item 6 de `-12`) : le rapport dit "pris" ou "non pris". **`W2` ne se fait pas** : ecrire `ecoutes` pour `ecoutons` rendrait la bonne reponse la plus longue sur 11 items.
5. **Le developpeur ne remplace pas nadia-prof** : il relit les regles qu'elle nomme (`TRV-CE1-5` avec la barre de s54 section 1.2 pour le francais ; `EMC-4`, `EMC-8`, section 4.1 de s53, `BIA-5` pour `-06`) et ecrit "relu" ou "non relu" avec le resultat (C7). Elle juge en jouant.

### D. Les postes : proprietaire, porte, rang de fusion, critere mesurable

| Poste | Proprietaire | Instance, tarif | Porte avant ouverture | Rang |
|---|---|---|---|---|
| `S54-ART-RES-R1` a `R3` | lea-dev | A, 0,25 | aucune | 1 |
| `S54-EXC-CATEGORIE` (`EXC-R15`) | marc-dev | B, 0,25 | **QC4** (nadia-prof, instance B) | 2 |
| `S55-BR-FRA-CE1-L1` | marc-dev | A, 1,0 | aucune | 3 |
| `S55-BR-FRA-CE1-L2` | marc-dev | A, 1,0 | aucune | 3, apres `L1` |
| `S55-BR-EMC-CM1-03` | lea-dev | B, 1,0 | aucune | 3 |
| `S55-BR-EMC-CM1-06` | lea-dev | B, 1,5 | aucune | 3, apres `-03` |
| `S55-ART-CP-10-MECANIQUE`, T2 | lea-dev | hors `PAR` | aucune | 4 |
| `S55-ART-CP-10-MECANIQUE`, T3 | lea-dev | forme : A, 0,25 (ecrite) | **G-INES**, **G-NADIA-A** | 4 |
| `S54-PONCT-GN-INDICES` | lea-dev | 0,0 | aucune | 5 |
| `S54-ASCII-APOSTROPHES-TESTS` | marc-dev | 0,0 | engagement de camille-po | 5 |
| `S55-OUTIL-JDK-JAVA-TOOL-OPTIONS` (A7), `S55-OUTIL-TOKENS-SANS-SPRINT` (A8) | sofia-sm, casquette developpeuse | 0,0 | engagement de camille-po | 6 |
| Mise en page d'`ART-CP-10` | ines-design | hors `PAR` | aucune | avant T3 |
| Ligne QC4 | nadia-prof, instance B | sans prix publie | aucune | avant `EXC-R15` |
| Ligne "section 3 vaut sous (b)", A10, plafond de `G3` | nadia-prof, instance A | sans prix publie | aucune | avant T3 |
| Lecture de testabilite, A6 | yanis-qa | hors `PAR` | aucune | avant la premiere livraison |
| `relecture-cloture.md` (A9) | relecteur-pedagogique | hors `PAR` | aucune | cloture |

**Portes** : **QC4** = une ligne ecrite par nadia-prof (instance B) disant si le seuil de `FAB-1a` (minimum 3 par categorie) se lit sur deux categories ou sur trois : `git grep -c QC4 -- squad/conformite` rend 0 fichier aujourd'hui, il doit en rendre au moins 1.
**G-INES** = la proposition de mise en page existe : `ls squad/design | grep -c s55` rend 0, doit rendre au moins 1, et `git log --format=%ad --follow` de ce fichier precede le premier commit de rendu (critere 6, corrige par `DEC-0115` A.5).
**G-NADIA-A** = une ligne de nadia-prof (instance A) confirmant que sa section 3 vaut sous l'issue (b), retenue par `DEC-0115` A.1 : `git grep -c "amendement A.1" -- squad/conformite` rend 0 fichier, doit en rendre au moins 1. Elle n'a pas ete demandee a l'ecrit dans son arbitrage ; `DEC-0115` A.8 la route.

**Postes de dev, criteres mesures le 2026-10-08 sur `ba47b716`.** Aucun n'est vert sur ce qu'il corrige. C1, C3 a C9 du planning valent pour chacun ; ci-dessous, ce qui s'y ajoute.

- **`S54-ART-RES-R1` a `R3`** (arbitrage B 4.2, rejoue par l'instance A). Un seul commit. `grep -c 'un photographe'` sur `cp-arts-oeuvres-familles.yaml` rend 3, doit rendre 0 ; `grep -c 'une photographe'` rend 0, doit rendre 3 ;
  `grep -c 'indice: "Cette .uvre est faite avec de la peinture."'` rend 0, doit rendre 1 ; `grep -c 'va avec la statue, le tableau ou la photographie'` sur `catalogue.yaml` rend 1, doit rendre 0.
  Les criteres du planning (tableau "Specifiques") restent. `PaquetCpArtsOeuvresFamillesTest` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` verts (la consigne change).
- **`S54-EXC-CATEGORIE`** (fiche, bloc du 2026-10-08, section 4). Les trois comptes du planning restent (3, 0, 1 doivent devenir 1, 3, 0). **La clause "`PaquetCpEmcExclusionTest` reste vert" ne tient pas avec les quatre lignes seules** (avis 7.1, simulation C ; `DEC-0115` A.6) :
  la categorie "moquerie" tombe a 1 bonne reponse sous le minimum de 3, `CLASSEMENTS_VALIDES` ne connait pas la categorie neuve (les items 1 et 3 basculent sans erreur dans la tache B : un faux vert), l'inventaire porte l'ancienne consigne.
  Mesure a ajouter : `grep -c "mise . l.cart" PaquetCpEmcExclusionTest.java` rend 0, doit rendre au moins 1. Le diff reel est d'au moins 6 lignes en 4 fichiers. **Le worktree ne s'ouvre pas avant QC4** ; la reecriture du critere est a camille-po (backlog : "non tenable tel qu'ecrit").
- **`S55-BR-FRA-CE1-L1`** (`K1` a `K3`). `grep -c 'indice: "Remplace le sujet par'` sur `ce1-accord-sujet-verbe.yaml` : 0 vers 12 ; `grep -c 'indice: "Lis le sujet avec chaque verbe'` : 0 vers 5 ; `grep -c 'compte-les'` : 6 vers 1 ;
  `grep -c '^ *indice:'` : 24 vers 24 ; `grep -c 'il ___ au march'` : 0 vers 1 (item 6) ; `grep -c 'il ___ un g.teau'` : 0 vers 1 (item 18) ; `grep -c "satisfait par la forme d.une autre personne"` : 0 vers 1 (en-tete) ;
  `grep -c 'Lis bien, puis choisis la bonne forme du verbe'` sur `catalogue.yaml` : **2 vers 3** (la consigne est deja servie par une autre entree ; le titre est libre). Le test neuf du paquet (`PaquetCe1<Nom>Test`) ajoute au moins un test et garde `ACV-CE1-1` (sujet nominal 17 sur 24 ; 12 singuliers, 12 pluriels).
- **`S55-BR-FRA-CE1-L2`** (`K4`, `K5`). Sur `ce1-classes-de-mots.yaml` : `grep -c 'Fatou'` 0 vers 1 ; `grep -c 'Lina'` 0 vers 1 ; `grep -c 'Tom.*range'` 1 vers 0 ; `grep -c 'Karim aime'` 1 vers 0 ;
  `grep -c 'dis quelle sorte de mot est entre guillemets'` sur `catalogue.yaml` : 0 vers 1. Le cliquet `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` reste vert **sans toucher sa table** (la consigne de `K5` ne porte aucun signe double). Le test neuf du paquet ajoute au moins un test et garde `CLM-CE1-1` (5, 4, 5, 5, 5 par classe).
- **`S55-BR-EMC-CM1-03`** (arbitrage B 2.1 a 2.3). Sur `cm1-emc-respect-differences.yaml` : `grep -c 'bonneReponse: "Aya, parce qu.elle utilise un fauteuil roulant et que'` 1 vers 0 ; `grep -c 'bonneReponse: "Aya, parce qu.elle utilise un fauteuil roulant"'` 0 vers 1 ;
  `grep -c 'jeu de cartes'` 0 vers 1 ; `grep -c 'Pense . deux amis qui n.aiment pas les m.mes livres'` 0 vers 1 ; `grep -c 'disparaissent avec le temps'` 1 vers 0 ; `grep -c "puni si un adulte le voit"` 0 vers 1 ; `grep -c 1948` 0 vers au moins 1 ;
  `grep -c '^ *- enonce:'` 11 vers 11 (le motif de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` ne change pas). Le paragraphe `EMC-5` de l'en-tete decrit le nouvel item 4 : relu par le relecteur.
- **`S55-BR-EMC-CM1-06`** (arbitrage B 3.1, 3.2). Sur `cm1-emc-principes-republique.yaml` : `grep -c "qu.un adulte la refuse"` 1 vers 0 ; `grep -c "pouvoir faire tout ce que l.on veut"` 0 vers 1 ; l'indice de l'item 6 **ne change pas** (`grep -c 'indice: "Teste chaque phrase avec un exemple pris dans la classe."'` reste 1, arbitrage B 1.3) ;
  `grep -c 'bonneReponse: "se soutenir et s.entraider entre citoyens"'` 1 vers 0 ; `grep -c "s.entraider les uns les autres"` 0 vers 1 ; `grep -c 2018-717` 0 vers au moins 1.
  **P3** : le test neuf du paquet (`PaquetCm1Emc<Nom>Test`) porte un test qui interdit le retour de "la liberte" dans les distracteurs de l'item 4 (avis 2, point 1) ; yanis-qa rejoue la mutation. **P4** : "non garde : P4" dans `rapport-lea-dev.md`, avec les six indices lus et lesquels (C8).
- **`S55-ART-CP-10-MECANIQUE`**, criteres 1 a 9 du backlog tels que corriges par `DEC-0115` A.5. **T2** : criteres 4 (garde-fous 1 a 3, population reelle de reproductions **exactement 0**, morsures sur un item reel de `cp-decrire-une-oeuvre` mute en memoire), 7, 8 (chemin `adapters/adapter-content/src/main/resources/contenu/`), 9 ; aucun mot ne lui est necessaire.
  **T3** : criteres 1 (`grep -c "oeuvres/' + \${proposition}" manche.html` 0 vers au moins 1), 2, 3 (42 `@Test` de `PartieControllerTest` au minimum, aucune assertion affaiblie), 4 (garde-fou 4), 5 (la methode `troisReferencesDeLaProductionSontDesIllustrationsOuDesReproductionsDeContenu` passe de `hasSize(3)` a `hasSize(4)`, `sed -n '709,730p'`), 6 pour les points 3 et 4 seuls, 7 (`ArchRule` 39 et `@ArchTest` 36 inchanges), 8, 9.
  **T4 sort de la story** et voyage avec le fichier du paquet (avis 4). Sans paquet, l'enfant ne voit rien. **T3 non livree a la cloture : premier report, compte** (`DEC-0115` A.5, point 1). Le developpeur n'ecrit aucun mot lu par l'enfant (`CON-5`).
- **`S54-PONCT-GN-INDICES`** : criteres du planning (12 vers 0 ; la meme avec U+00A0, 0 vers 12 ; cliquet `TYP-1` de 12 a 0 ; un commit). **`S54-ASCII-APOSTROPHES-TESTS`** : echappement `\\u2019` (avis, T4), `grep -cP '[\x{2018}\x{2019}]'` rend 1 sur chacun des deux fichiers, doit rendre 0, les deux tests restent verts.
- **A7** : `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` sort 1 (reproduit par theo-archi), doit sortir 0 ; un cas neuf dans `outils/mvn-jdk21-test.sh` ; `bash outils/mvn-jdk21-test.sh` sort 0. **A8** : `python outils/rapport-tokens.py` sans `--sprint` sort 2 et `git status --short` reste vide ; `test_6` de `outils/rapport-tokens-journal-test.py` reecrit en "refuse et n'ecrit rien".
- **Ines-design** : un fichier de `squad/design/` nomme pour s55 (proposition, pas de gabarit) qui dit : la reference et trois propositions tiennent-elles sans defilement aux dix largeurs deja relevees (`releves-ines-s26-ecran-voix-dix-largeurs.json`) ; la forme de la reference hors du choix (`M2`) ; la marque de la bonne oeuvre a la revelation (`M4`) ;
  si trois images ne tiennent pas, elle le dit (nadia-prof rejuge alors, arbitrage A 3.4 regle 4). Elle ne tranche pas et n'ecrit aucun mot lu par l'enfant. Le dev de T3 applique, il ne dessine pas.
- **Yanis-qa** : `lecture-testabilite.md` dans le dossier de sprint avant la premiere livraison ; dit lesquels des criteres ci-dessus un automate ne juge pas (`K1` a `K5`, l'indice de P4, la clause de `EXC-R15`, `X5`), et ce qu'il faut pour `EXC-R15` en l'etat. A6 (diff de `s54-nadia-c`) y figure.
- **Relecteur-pedagogique** : `relecture-cloture.md` existe a la cloture (`ls` rend 1), avec au moins une ligne meme sans trouvaille.

### E. Voies : un worktree par agent, noms effectifs, ordre de fusion

**Noms effectifs (action A5).** Le planning nommait `s55-nadia-instance-a`, `s55-nadia-instance-b`, `s55-theo-avis`, `s55-camille-dec` seul. Les branches reellement ouvertes sont `s55-nadia-a`, `s55-nadia-b`, `s55-theo-faisabilite`, `s55-camille-dec`, `s55-camille-amendement`, `s55-sofia-planning`, `s55-sofia-amendement` ;
les worktrees portent le meme nom precede de `recreo-`. Scratchpad : un par agent, dedie par instance de nadia-prof (A, B) ; choisi par l'agent qui ouvre, aucun chemin ecrit ici. Un port de jeu par instance si nadia-prof en lance un.

**Regles** : un dev tient **un seul worktree a la fois**, ses postes se font l'un apres l'autre ; **une branche porte un commit par poste et se fusionne par commit**, dans l'ordre de la table ; une branche neuve est ouverte pour tout poste qui n'est pas dans la meme file.
`R0` = sommet de `round-two` apres la fusion de `s55-sofia-amendement` ; `R+x` = `round-two` apres la fusion de `x`. **Chaque agent note `git log -1 --format=%h` dans son premier message** ; je verifie la lignee avant chaque fusion (`bash outils/verifier-lignee.sh`), meme si l'agent dit l'avoir faite.
Les agents ne rebasent, ne poussent et ne fusionnent pas.

**Table des worktrees a ouvrir** (longueur du plus long nom : 27 caracteres, soit 256 avec la formule de la section "Isolation et fusion" : sous 260).

| Agent | Branche | Dossier (voisin du checkout) | Base | Taches | Ouverture |
|---|---|---|---|---|---|
| lea-dev | `s55-lea-art-res` | `recreo-s55-lea-art-res` | `R0` | `S54-ART-RES-R1` a `R3` ; `rapport-lea-dev.md` ouvert | des `R0` |
| marc-dev | `s55-marc-branchement` | `recreo-s55-marc-branchement` | `R0` | `L1`, puis `L2`, puis `ASCII` si engagee (un commit chacun) ; `rapport-marc-dev.md` | des `R0` |
| ines-design | `s55-ines-page` | `recreo-s55-ines-page` | `R0` | proposition de mise en page (G-INES) | des `R0` |
| yanis-qa | `s55-yanis-lecture` | `recreo-s55-yanis-lecture` | `R0` | lecture de testabilite, A6 | des `R0` |
| camille-po | `s55-camille-fiches` | `recreo-s55-camille-fiches` | `R0` | amendement d'engagement (`ASCII`, A7, A8) ; reecriture du critere d'`EXC-R15` apres QC4 ; fiches des quatre jeux a l'ouverture de leur branchement (C9) | des `R0` |
| nadia-prof B | `s55-nadia-b` | `recreo-s55-nadia-b` (existant) | son sommet actuel, ancetre de `round-two` | ligne QC4 seule : elle amende son propre arbitrage, sans lire de code | des `R0` |
| nadia-prof A | `s55-nadia-a` | `recreo-s55-nadia-a` (existant) | son sommet actuel, ancetre de `round-two` | ligne G-NADIA-A, A10, plafond de `G3` | des `R0` |
| marc-dev | `s55-marc-exc` | `recreo-s55-marc-exc` | `R+` fusion de la ligne QC4 | `S54-EXC-CATEGORIE` | apres QC4 **et** apres la livraison de `L1` ; avant `L2` |
| lea-dev | `s55-lea-branchement` | `recreo-s55-lea-branchement` | `R+s55-lea-art-res` | `EMC-CM1-03`, puis `-06`, puis `PONCT` (un commit chacun) | apres la fusion d'`ART-RES` |
| lea-dev | `s55-lea-mecanique` | `recreo-s55-lea-mecanique` | `R+` livraison de `-06` | T2, puis T3 (un commit chacun) | T2 apres la livraison de `-06` ; T3 apres G-INES et G-NADIA-A |
| nadia-prof A | `s55-nadia-a-art-res` | `recreo-s55-nadia-a-art-res` | `R+s55-lea-art-res` | rejeu d'`ART-RES` | apres sa fusion |
| nadia-prof A | `s55-nadia-a-l1` | `recreo-s55-nadia-a-l1` | `R+` commit `L1` | audit de `ce1-accord-sujet-verbe` | apres sa fusion |
| nadia-prof A | `s55-nadia-a-l2` | `recreo-s55-nadia-a-l2` | `R+` commit `L2` | audit de `ce1-classes-de-mots` | apres sa fusion |
| nadia-prof B | `s55-nadia-b-exc` | `recreo-s55-nadia-b-exc` | `R+s55-marc-exc` | rejeu d'`EXC-R15` | apres sa fusion |
| nadia-prof B | `s55-nadia-b-emc03` | `recreo-s55-nadia-b-emc03` | `R+` commit `-03` | audit de `cm1-emc-respect-differences` | apres sa fusion |
| nadia-prof B | `s55-nadia-b-emc06` | `recreo-s55-nadia-b-emc06` | `R+` commit `-06` | audit de `cm1-emc-principes-republique` | apres sa fusion |
| sofia-sm (dev) | `s55-sofia-outils` | `recreo-s55-sofia-outils` | `R+` dernier branchement | A7, A8 (deux commits) ; **tache separee, casquette annoncee** | apres les branchements, si engages |
| yanis-qa | `s55-yanis-cloture` | `recreo-s55-yanis-cloture` | sommet de cloture | rapport qualite, quatre jobs nommes | cloture |
| theo-archi | `s55-theo-cloture` | `recreo-s55-theo-cloture` | sommet de cloture | confirmation ArchUnit | cloture |
| relecteur-pedagogique | `s55-rel-cloture` | `recreo-s55-rel-cloture` | sommet de cloture | relecture de fin, A9 | cloture |
| camille-po | `s55-camille-cloture` | `recreo-s55-camille-cloture` | sommet de cloture | fiches, matrice au verdict seulement | cloture |
| sofia-sm | `s55-sofia-cloture` | `recreo-s55-sofia-cloture` | sommet de cloture | `review.md`, `RAPPORT.md`, `RAPPORT-tokens.md`, `retro.md` | cloture |

**Proprietaire de la mecanique : lea-dev.** Raison tiree de la charge, en lignes mesurees a s54 et dans l'avis : marc-dev porte environ 1 030 lignes (`L1` 493 et `L2` 523, mesures sur deux branchements de s54 ; `EXC-R15` au moins 6 ; `ASCII` 2) ;
lea-dev environ 450 (`ART-RES` 6 a 9 ; deux EMC de l'ordre de 200 chacun, un seul mesure a s54 ; test de P3 10 a 20 ; `PONCT` 12). La mecanique ajoute 20 a 38 lignes de production et 680 a 1 400 de test : lea-dev finit a 1 150 a 1 890, marc-dev a 1 730 a 2 470 s'il la prenait. Ce sont des ordres de grandeur, pas des tarifs.
Les fichiers : la mecanique ne partage **ni le catalogue, ni le registre, ni l'inventaire** avec aucun autre poste (avis 8) ; son ordre de fusion est libre. lea-dev est l'auteure du paquet d'`ART-RES` et du gabarit `manche.html` du bloc-adulte (`a4b22724`).
**Cout nomme** : cinq postes sequentiels pour lea-dev. Si elle devient le chemin critique, je deplace `PONCT` vers marc-dev (qui n'a pas commence, entree au tableau d'obstacles le jour meme).
Dans l'ordre de lea-dev, T2 passe apres les deux EMC : ce sont eux qui font attendre nadia-prof (instance B), et T2 se fusionne librement.

**Ordre de fusion** (reacteur complet, `bash outils/mvn-jdk21.sh -B clean verify`, a chaque commit de fusion ; tout compte de tests est celui du reacteur sur l'arbre fusionne, jamais la somme de deux branches ; dernier total connu 2216 en `verify`, 2210 en phase `test`, a remesurer) :

1. Decisions et portes : cet amendement, puis la ligne QC4, la ligne G-NADIA-A, le fichier d'ines-design, l'amendement d'engagement de camille-po.
2. **Corrections de jeux servis, avant tout branchement** : `ART-RES` (A), puis `EXC-R15` (B). **Exception ecrite** : si QC4 n'est pas ecrite quand `L1` ou `-03` est livre, l'attente est une entree au tableau d'obstacles (route : nadia-prof B) et le branchement ne l'attend pas ; la correction attend la reponse, pas l'inverse.
3. Branchements, dans l'ordre de livraison : `L1`, `L2` (marc-dev), `-03` puis `-06` (lea-dev).
4. Mecanique : T2, puis T3. Libre.
5. `PONCT`, `ASCII`.
6. A7, A8.
7. Cloture.

**Fichiers communs et ancres** (lignes a `0a795589`, avis section 8). `catalogue.yaml` : marc-dev ajoute apres l'entree `paquet: ce1-passe-compose` (793-794) et corrige la ligne 395 (`EXC-R15`) ; lea-dev ajoute apres `paquet: cm1-emc-atteinte-autrui` (847-848) et corrige la ligne 852 (`ART-RES`).
Inventaire de `TexteAccentueExposeParLePortEntrantTest` : marc-dev apres 423, 708, 1628 et pres de 870 ; lea-dev apres 360, 665, 1540 et pres de 896. **Zone de conflit nommee** : l'inventaire pres de la ligne 870, ou `EXC-R15` corrige une consigne et ou `L2` ajoute la sienne ; au conflit on garde les ajouts des deux cotes, jamais une assertion affaiblie.
`REGISTRE-identifiants.md` : ecrit par nadia-prof seule, depuis le worktree de son instance (R8) ; les deux instances ajoutent, l'une apres l'autre. `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` : **ne change pas** pour les quatre paquets (avis T1 : deja declares). Les lignes du planning qui le disent modifie par les deux couloirs sont perimees.

### F. La phrase fausse du planning, corrigee

Le planning ecrivait qu'un budget d'audit pour la mecanique *"tient sous 8,0 uniquement si O1 et O3 ne sont pas pris"*. **C'est faux, y compris au moment ou je l'ai ecrit** : avec le chiffre de theo-archi (2,0), 6,75 + 2,0 = 8,75 sur 8,0 sans O1 ni O3.
Le tarif est celui de nadia-prof : **audit du paquet illustre 2,5** (lot d'arts plastiques CP 1,5 ; illustre 0,5 ; forme sans cousine 0,5). A s55, aucune instance ne le porte : A 3,75 + 2,5 = 6,25 et B 3,75 + 2,5 = 6,25, tous deux au-dessus du plafond dur de 5,0 ; somme 7,5 + 2,5 = 10,0 sur 8,0.
**Il va a s56 au plus tot.** Charge connue de s56, que la `DEC` d'ouverture de s56 comptera : l'instance qui l'audite garde 4,0 - 0,5 - 0,5 - 2,5 = 0,5, donc aucun autre lot ; la forme n'a aucune cousine, c'est le terrain neuf de `PAR-13` ;
un lot sans cousine (`-08`, `-09`) n'est jamais dans ce sprint. **Consequence de produit, nommee et non tranchee** : `PAR-2` met le francais CE1 chez A ; si A audite le paquet a s56, le francais CE1 n'avance pas a s56.

### G. A9, A7, A8, `ASCII` et les stories de la retro de s54 : ligne ou report

| Poste | Decision | Motif |
|---|---|---|
| A9, `relecture-cloture.md` | **Ligne** (relecteur-pedagogique) | aucun prix, une ligne meme sans trouvaille ; section D |
| `S54-ASCII-APOSTROPHES-TESTS` | **Ligne, marc-dev, derniere de sa file.** Engagement a ecrire par camille-po (`DEC-0115` section 4 : "engageable par amendement") ; sans cet amendement a la fusion du dernier branchement de marc-dev, **report numero 1, compte** | forme et agent sont ecrits (avis T4) ; 2 fichiers, 1 ligne chacun ; le motif du non-engagement ("aucun proprietaire") est leve |
| A7, `S55-OUTIL-JDK-JAVA-TOOL-OPTIONS` | **Ligne, sofia-sm (casquette developpeuse), dernier tour.** Meme regle d'engagement | prix et forme de theo-archi ecrits (1 ligne de production, 15 a 25 de test) ; defaut reproduit ; le motif du non-engagement ("prix manque") est leve |
| A8, `S55-OUTIL-TOKENS-SANS-SPRINT` | **Ligne, sofia-sm (casquette developpeuse), dernier tour.** Meme regle | forme : refus sans `--sprint` (5 a 8 lignes plus un test de 15 a 30) ; `--sprint` explicite reste ma regle tant qu'A8 n'est pas fait |
| `S54-OBS-SCI-CONSIGNE` | **Report numero 1**, propriete du report : camille-po | criteres non ecrits ("a ecrire quand une instance l'engage") ; sciences CM1 est a l'instance B, qui n'a aucun paquet de sciences a s55 |
| A3 (motifs de refus de sciences) | **Pas de poste** | aucun paquet de sciences touche a s55 (avis 2, point 4) ; T2 s'applique au prochain poste qui les touche ; le motif P3 de `-06` est garde dans son poste |
| `S54-OBS-ART-ALT`, `PCO-CE1-R1` | sans objet | retiree sans suite ; close |
| A10, plafond de `G3` | **Ligne** (nadia-prof, instance A), sans prix | non traites par son arbitrage ; sans reponse a la cloture : report numero 1 |
| Passe `PAR-4` | non due | arbitrage A 2.1 |
| Amendements de README proposes par la retro de s54 | pas dans ce sprint | ils se decident a la retro de s55 ; la regle de l'action A5 (noms de worktree, branche, scratchpad) est appliquee dans cette section E |

Reports de postes non engages dans `DEC-0115` section 4 : `ASCII`, A7, A8 et `OBS-SCI-CONSIGNE` sont des premiers reports **comptes** ; la ligne ci-dessus les place dans un couloir sans les engager.

**Postes des sources hors table D** : `S55-BR-FRA-CE1-L3` non engagee (O1 ne tient pas, 4,75 sur 4,0) ; 11 paquets de `G1`/`G2` restants reportes (`PAR-2`, `PAR-8` : un lot de plus par instance sature A) ;
`G7` a `G11` : plafonds ecrits, **seul `cm1-lire-et-restituer` (`FRA-CM1-01`) peut fermer**, apres une mesure de pli d'ines-design non faite puis un lot a 1,0 : aucun poste a s55, a placer par camille-po a la `DEC` d'ouverture de s56 ; `G3`, `G4` non engages ;
`cp-emc-personnes-differentes` sous refus ; **paquet a reproductions (T5), T4 du corpus et choix des oeuvres distractrices : non engages, option ouverte** (Q-U1, Q-U4), ce n'est pas un report ; Q6 non engagee.

### H. Questions restees a l'utilisatrice

| Code | Question | Defaut propose, qui n'engage rien |
|---|---|---|
| Q-U1 | Le **paquet a reproductions** d'`ART-CP-10` s'ecrit-il a s55 ou a s56 ? Son audit coute 2,5 et va a s56 au plus tot dans les deux cas ; ecrit a s55, il serait pret pour cet audit ; il n'a aujourd'hui ni proprietaire, ni T4, ni choix des oeuvres distractrices | **s56** : rien n'est ecrit a s55, la `DEC` d'ouverture de s56 le prend. Si ta reponse est s55, il faut un proprietaire (lea-dev apres T3, ou sofia-sm) et l'usage 1 de Q-U4 |
| Q-U3 | Confirmes-tu par tes mots, avec leur date, que tu finances le **dispositif seul** (T2, T3), **sans paquet ni audit**, a s55 ? La formule de `DEC-0115` est celle de l'agent qui relaie | **Pas de reponse** : `DEC-0115` reste `Proposee` (section 0.5) ; rien n'est bloque, la section 5 s'applique deja |
| Q-U4 | Les **0,25 restants de A** : (1) choisir les oeuvres distractrices du paquet illustre (utile seulement si Q-U1 dit s55) ; (2) payer un second rejeu mot pour mot apres un refus ; (3) les laisser non alloues. (1) et (2) ne tiennent pas ensemble (0,5 sur 0,25) | **(3)** : non alloues. Un second refus a s55 est ecrit au tableau des obstacles avec son poste, et camille-po decide |

U1 (ordre des matieres) et U2 (financer la mecanique) du planning sont reprises par `DEC-0115` ; U3 (CI) : la reponse relayee est "verte", non lue par moi (voir plus haut) ; U4 (trailers) : aucune reponse relayee, la regle de `DEC-0110` 17.4 (modele execute) continue, mon trailer porte `Sonnet 5.5`.

### I. Risques nouveaux

| Code | Risque | Ce qui le contient |
|---|---|---|
| R11 | `EXC-R15` attend QC4 : un texte faux reste servi tant qu'elle n'est pas ecrite | demande routee des `R0` ; exception d'ordre ecrite en section E ; entree au tableau d'obstacles |
| R12 | lea-dev porte cinq postes sequentiels et la mecanique | `PONCT` deplacable vers marc-dev ; T2 apres les deux EMC ; T3 n'est pas sur le chemin des audits |
| R13 | T3 attend deux portes (ines-design, nadia-prof A) ; non livree : premier report | portes demandees des `R0` ; T3 est le dernier poste de lea-dev avant `PONCT` |
| R14 | Un second refus a s55 n'est finance par aucune marge | Q-U4 ; entree au tableau d'obstacles, decision de camille-po |
| R15 | Le conflit de l'inventaire pres de la ligne 870 (`EXC-R15`, `L2`) est deduit, non rejoue | zone nommee en section E ; reacteur complet a chaque fusion |

### J. Jobs de `ci.yml` lances pour cet amendement

Voir le message de livraison de ce commit : `chemins` et `prefixes` sont lances avec une entree explicite ; `build` n'est lance que sur la classe nommee ci-dessus et sur le paquetage `com.recreo.archi` ; `outils` n'est pas lance (aucun outil modifie).

### K. Decisions de planning prises par cet amendement (mon role)

1. Proprietaire de la mecanique : lea-dev ; branche `s55-lea-mecanique` ; tache d'ines-design avant T3.
2. Les corrections de nadia-prof "avant branchement" sont dans le commit du paquet, mot pour mot, avec leurs criteres mesures (sections C, D).
3. Capacite 3,75 / 3,75, somme 7,5 ; phrase fausse corrigee (F).
4. Une branche par file de poste, fusion par commit, ordre de fusion et zone de conflit ecrits (E).
5. `ASCII`, A7, A8 : lignes dans un couloir, engagement a camille-po ; A9 : ligne.
6. Aucune `ADR`, aucune `DEC` creee ou amendee. Aucun worktree ouvert, rien fusionne, rien pousse.

---

## Amendement du 2026-10-08 (`sofia-sm`, cloture de s55) : criteres qui ne valent plus, formule de la forme 3

Le texte ci-dessus, amendement de 16:20 compris, est inchange. Cet amendement est ecrit **a la cloture, apres la livraison** : il ne date aucun poste d'avant, il dit
quels textes de ce planning ne valent plus et ce qui vaut a leur place. Il n'ouvre aucun poste, ne decide ni produit, ni architecture, ni pedagogie, ne prononce aucun Done
et ne touche ni le backlog, ni la matrice, ni une fiche, ni `catalogue.yaml`. Il n'ajoute aucune ligne d'heure de debut ou de fin : celles du debut de ce fichier et de `review.md`
restent les seules que `outils/rapport-tokens.py` lit.

- **Heure de l'amendement** : 2026-10-08 21:56 (UTC+02:00), lue par `date`.
- **Sources** : `relecture-cloture.md` (`relecteur-pedagogique`, points `R1` et `R2`), `rapport-camille-criteres.md` et son bilan Q-Y1 a Q-Y14, `rapport-camille-relecture.md`,
  `rapport-qualite.md` section 4 (`yanis-qa`), `DEC-0115` amendements A.13 et A.18, backlog ("Sprint 55" et "Cloture de s55").
- **Mesure** : `grep -n` des quatre motifs ci-dessous sur ce fichier, avant cet amendement, rend les lignes 154, 267, 481, 483, 494 et 496.

### R2 du relecteur : quatre criteres que le backlog declare insatisfiables ou remplaces

Le backlog dit que ses lignes l'emportent pour le produit (`rapport-camille-criteres.md`, section 3). Ce planning ne le disait pas ; il le dit ici.

| Ce que le planning ecrit (lignes) | Pourquoi il ne vaut plus | Ce qui vaut |
|---|---|---|
| `grep -c "mise . l.cart"` sur `PaquetCpEmcExclusionTest.java`, au moins 1 (ligne 481) | Deux caracteres separent `l` et `cart` : la commande rend 0 meme sur le texte correct (`rapport-marc-dev-exc.md` E1 ; Q-Y1) | `grep -c "mise à l'écart"` rend au moins 1 ; `yanis-qa` mesure 3 sur `af8b326e` |
| `il ___ un g.teau`, `Pense . deux amis`, `Cette .uvre` (ligne 483, et les chaines equivalentes du poste `-03` et d'`ART-RES`) | Un `.` pour une lettre accentuee ne matche rien en locale `C` (Q-Y7) | La lettre litterale : 1 en `LC_ALL=C` et en `LC_ALL=C.UTF-8` (mesure de `yanis-qa`, Q-Y7) |
| 42 `@Test` de `PartieControllerTest` (ligne 494) | Une ligne est `@TestConfiguration` : 41 tests (Q-Y4) | `grep -cE '^\s*@Test\s*$'` rend 41 |
| cliquet de `TYP-1` "de 12 a 0" (lignes 154, 267, 496) | La forme verte n'est pas une entree a 0 : la table et le total sont redescendus ensemble (Q-Y3, rejoue par `camille-po`) | L'entree `cm1-grands-nombres` est retiree de la table, `DETTE_TOTALE` passe de 502 a 490 ; livre par `08cb05fd` |

### R1 du relecteur : la forme 3 sans sa formule, lignes 27 et 395

Les lignes 27 (l'ordre des matieres) et 395 (les reponses relayees : CI verte, arts plastiques CP a l'instance A, le sprint demarre) disent "forme 3" sans la formule que `DEC-0062`
(amendement A.2) et `squad/README.md` section 2 bis exigent. Elles resument aussi des enonces que la regle veut cites mot pour mot. Cette tache de cloture n'a pas assiste au planning
et **ne reconstitue pas de mot pour mot** : le seul texte fige dans le depot est celui de `DEC-0115` sections 0.1 et A.1, que `DEC-0115` A.18 range sous la formule.

**Formule appliquee a ces deux lignes : `recu de MASTER, sans source dans le depot`**, date de reception 2026-10-08. Elle ne fait de ces enonces ni des mots de l'utilisatrice, ni un motif.
Rien de ce planning ne repose sur eux : l'ordre est repris par `DEC-0115` section 3 sous un motif de forme 1, et la CI relayee "verte" est contredite par la mesure locale de `theo-archi`
(`avis-faisabilite.md` section 0) puis soldee par `8f740977`.

### Ce que cet amendement ne fait pas

Il ne reecrit aucune ligne ci-dessus, ne decide pas quelle regle de relecture vaut pour l'EMC (le critere C7 et `EMC-9` : arbitrage B section 1.2, deja porte par l'amendement de 16:20, et laisse au backlog par
`camille-po`) et ne cree ni `ADR`, ni `DEC`.
