# Retro - s54 - branchement des premiers paquets et reports de s53

- **Animee par** : `sofia-sm`, casquette Scrum Master, 2026-10-08. Aucun agent invoque : cette retro lit les ecrits du sprint
  (`review.md`, `rapport-qualite.md`, `confirmation-archunit.md`, `DEC-0114`, fiches, backlog). Chaque constat cite sa source ; ce que
  je n'ai pas pu mesurer est dit.
- **Retro de s53 relue** : actions A1 a A11 ; etat dans `review.md` obstacle 20.
- **`lessons.md`** : n'existe pas dans le depot (`find . -iname lessons.md` ne rend rien, comme au planning). La memoire des corrections est cette retro et `squad/README.md`.

## 1. Ce qui a fonctionne, mesure

| # | Constat | Mesure | Source |
|---|---|---|---|
| W1 | **La decision a precede chaque branchement.** `5b759d1d` (`DEC-0114`) est ancetre des six commits de branchement | 6 sur 6 | `rapport-qualite.md` s.3, C1 |
| W2 | **Les conditions de `nadia-prof` ecrites avant branchement ont change le taux de refus.** Aucun REFUSE a s54 sur six audits premier passage ou reprise ; s52 : 1 sur 6, s51 B : 2 sur 3 | 0 refus, 2 verdicts sous reserve, l'une levee le jour meme | fiches de s54 ; `planning.md` ("Taux de refus") |
| W3 | **Plafond ecrit avant audit, branchement avant audit** pour `ART-CP-10` : ordre lisible dans l'historique | `a3819bcd` (21:31) avant `c5c2d50f` (2026-10-08 12:14) ; `920e19e2` ancetre de `c5c2d50f` | `rapport-qualite.md` s.4, criteres 6 et 8 |
| W4 | **La capacite a tenu sous trois instances.** Consommation reelle 8,0 pour une borne de 9,5 ; l'ecart n'a pas ete consomme ; aucune instance n'a franchi 4,0 | A 3,75, B 3,25, C 1,0 | `DEC-0114` A.26 |
| W5 | **Un garde-fou s'est resserre.** Cliquet de `TYP-1` pour `cm1-grands-nombres` : 26 a 12 ; total 516 a 502 ; aucune assertion affaiblie | `rapport-qualite.md` s.3, C5 | `8b15e3ba` |
| W6 | **Un chiffre faux d'une retro precedente a ete refute par un temoin positif.** Le "0 sur 182" de s53 devient 161 (meme motif, temoin : les 4 fichiers de `competence`) | `lecture-testabilite.md` section 4 | `31458c0f` |
| W7 | **Le test du paquet a ete ecrit avec le branchement** pour quatre des six, ce qui est la regle du depot. `theo-archi` l'a tenu pour *du* (T1) parce que les conditions comptables ne vivent que dans ces tests | +42 tests, 4 classes | `avis-faisabilite.md` T1 ; `rapport-qualite.md` s.2 |
| W8 | **Les annotations ont corrige sans effacer** : `b5779885` marque `Accepte` aux endroits qui disaient `Propose`, le texte d'origine reste | `git show b5779885` | `review.md` section 6 |
| W9 | **Chaque agent a nomme les jobs qu'il a lances** (`prefixes`, `chemins`, `outils`, `build`), conformement a l'amendement du 2026-09-08 de `CLAUDE.md` | `rapport-qualite.md` s.1, `confirmation-archunit.md` s.6, `avis-faisabilite.md` s.7 | - |

## 2. Ce qui a coute, mesure

| # | Constat | Mesure | Source |
|---|---|---|---|
| C1 | **`DEC-0114` a double en un jour.** 369 lignes a la creation (2026-10-07 21:02), 734 a la cloture, 26 sections d'amendement `A.1` a `A.26` ; **trois arbitrages relayes par message d'agent** (`Q6`, `Q11`, `Q12`) ont chacun appele un amendement, plus deux annotations de correction (A.25 point 5, A.26) | +365 lignes | `wc -l` ; `git show 5b759d1d:<DEC-0114>` |
| C2 | **Les cinq divergences de texte que le sprint a produites et que `yanis-qa` ou le relecteur ont trouvees apres coup** : statut d'`ADR-0104` (`Propose` partout, `Accepte` dans le fichier) ; cellule de matrice d'`ART-CP-10` (*"aucun verdict n'est prononce"* une minute apres le verdict) ; `PCO-CE1-R1` (`OUVERTE` au backlog alors que `7d32c1e3` l'avait livree) ; A.25 (3,25 pour A, reel 3,75) ; section 11 de l'arbitrage de s53 (R3) | 5 | `rapport-qualite.md` s.7 ; `confirmation-archunit.md` s.4 ; `DEC-0114` A.26 ; `review.md` obstacles 4, 10, 11 |
| C3 | **Le rapport qualite porte sur un arbre en retard sur le depot.** `yanis-qa` a mesure `db69bbc6` ; 16 fichiers ont change ensuite (tous `docs/` ou `squad/`), dont la correction de R3, qui rend perimee sa ligne *"R3 non tenu"* | 16 fichiers, 0 de code | `review.md` section 4.2 |
| C4 | **La charge de `nadia-prof`.** 752 tours sur 2364 (31,8 %), 32,8 % de l'output, sur trois instances ; `camille-po` 17,4 % des tours. Comparaison : s53, 1167 tours en tout ; s52, 2529 | 2364 tours | `RAPPORT-tokens.md` ; `JOURNAL-tokens.md`. La fenetre de s54 est ouverte jusqu'a l'outil : elle contient cette cloture |
| C5 | **Le sprint a produit 9 entrees de backlog apres les verdicts, dont 7 ouvertes et non engagees** (`PCO-CE1-R1` close, `EXC-R15`, `S54-ART-RES-R1` a `R3`, `S54-OBS-SCI-CONSIGNE`, `S54-PONCT-GN-INDICES`, `S54-ASCII-APOSTROPHES-TESTS`, `S54-OBS-ART-ALT` retiree) : le travail de lecture de `nadia-prof` et du relecteur alimente la file plus vite que le sprint ne la vide | 7 ouvertes, 1 retiree (`S54-OBS-ART-ALT`), 1 close (`PCO-CE1-R1`) | backlog "Sprint 54" |
| C6 | **Un critere non automatisable, sans rapport ecrit.** `TRV-CE1-5` et `EMC-9` demandaient un resultat *"dans son rapport"* ; aucun fichier ne le porte | 2 criteres sur 2 | `rapport-qualite.md` s.4 |
| C7 | **Un motif de refus non garde.** La mutation "le printemps" laisse verts le test du paquet (10 tests) et la suite complete (1360 + 312) | 1 mutation, 0 rouge | `rapport-qualite.md` s.5 |
| C8 | **Les chiffres de capacite ont circule sans etre recomptes** : A.25 a repris des chiffres du message de lancement (*"je n'ai pas relu leurs rapports pour les recompter"*) ; l'un etait faux | 1 sur 2 | `DEC-0114` A.25, A.26 |

## 3. Ce qui a coince, et pourquoi (lecture de `sofia-sm`)

- **Le planning n'a pas ete amende pour le sixieme paquet ni l'instance C** (`review.md` obstacle 22). La lecon de la retro de s53 (*"un engagement ajoute par
  une `DEC` s'ecrit dans un amendement du planning, avec affectation nominative, avant l'ouverture du premier worktree"*) etait ecrite dans le planning lui-meme ;
  elle n'a pas ete appliquee. La consequence est mesuree : `yanis-qa` ne peut pas verifier l'isolation de C (*"je n'ai pas reconstitue [...] les noms de worktree et de scratchpad"*).
  **Je n'ecris pas cet amendement apres coup** : il serait date de la cloture et ferait croire qu'il a precede les worktrees.
- **`PCO-CE1-R1` a ete livree le jour de son ouverture, sans engagement** (`review.md` obstacle 12). Ce n'est pas un defaut de contenu : le geste a ete juste, la reserve est levee. C'est la meme
  famille que `S53-REL-10` : un travail livre avant son acte.
- **Une consigne de planning sans fichier**. J'ai ecrit *"resultat ecrit dans son rapport"* pour `TRV-CE1-5` et `EMC-9` sans nommer le fichier ; les developpeurs n'ont donc pas de rapport a ecrire, et `yanis-qa` n'a rien a lire. L'ecart est dans ma redaction du critere, pas dans leur travail.
- **Le lancement porte un perimetre par messages d'agent.** Cinq arbitrages arrivent sans les mots de l'utilisatrice (`DEC-0114` A.0, A.9, A.17) ; `DEC-0114` reste `Proposee` a cause d'un seul d'entre eux (l'ordre). C'est le systeme qui fonctionne comme prevu, mais il laisse une `DEC` non acceptee a la fin du sprint.

## 4. Actions

| # | Action | Proprietaire | Artefact attendu |
|---|---|---|---|
| A1 | Ecrire la `DEC` d'ouverture de s55 en tranchant les sept questions de `proposition-s55.md` section 5 (ordre en forme 2 ou repris sous motif, financement de la mecanique d'`ART-CP-10`, option O1/O2, lecture de `PAR-13` sur les sept conditions neuves, place de l'arbitrage sur `G7` a `G11`, instance qui relit `S54-ART-RES-R1` a `R3`, decoupage du francais CE1) | `camille-po`, avec l'utilisatrice | `DEC` |
| A2 | Pousser s53 et s54, lire la CI sur le sommet pousse **en nommant le commit** (`Q9` n'en nommait aucun) | utilisatrice | push, verdict nomme |
| A3 | Dire si un **garde-fou de contenu** est du pour les motifs de refus de sciences (items 4 et 11 de `cm1-sciences-terre-jour-nuit-saisons`, item 11 de `cm1-sciences-environnement-activite-humaine`) : la mutation "le printemps" est restee verte. Le `theo-archi` de s54 a ecarte une regle "un test par paquet" comme *decorative* (T1) : l'avis dit si un test **de contenu** sur un motif precis ne l'est pas. `nadia-prof` dit si elle le veut (le backlog dit que le motif *"tient a `nadia-prof`"*) | `theo-archi` (avis), `nadia-prof` (besoin), `yanis-qa` (rejouer la mutation) | avis ecrit ; test ou refus motive |
| A4 | **Nommer le fichier du resultat ecrit** de tout critere "relu par le developpeur" des le planning (`rapport-<dev>.md` du dossier de sprint, ou section de `propositions-<dev>.md`) ; `marc-dev` et `lea-dev` ecrivent le resultat de `TRV-CE1-5` et d'`EMC-9` de s54 s'ils l'ont, sinon disent qu'ils ne l'ont pas | `sofia-sm` (planning de s55), `marc-dev`, `lea-dev` | critere nomme ; resultat ecrit ou absence dite |
| A5 | **Amender `planning.md` au geste**, pas apres : tout poste ajoute par une `DEC` apres le planning s'ecrit dans un amendement date, nominatif, avant le worktree, **et** les noms de worktree, branche et scratchpad d'une instance neuve y figurent. Je m'astreins a la regle ; la proposition de README est en section 5 | `sofia-sm` | amendement de s55 en cas d'occurrence |
| A6 | Verifier le diff de `s54-nadia-c` (un fichier par commit, aucun fichier de A ou de B) ou dire que ce n'est pas faisable apres fusion | `yanis-qa` | note |
| A7 | Corriger `outils/mvn-jdk21.sh` pour qu'il ne rende pas `inconnue` quand `JAVA_TOOL_OPTIONS` ajoute une ligne a `java -version` (le refus tient, le chiffre est faux) | `theo-archi` (forme), `sofia-sm` (dev si assignee) | correctif et cas de test dans `outils/mvn-jdk21-test.sh` |
| A8 | **Corriger le defaut de `rapport-tokens.py` sans `--sprint`** : un lancement sans argument modifie encore cinq fichiers suivis ; soit refuser de s'executer sans `--sprint`, soit ne plus recalculer les sprints anciens. Tant que non fait : `--sprint` explicite | `theo-archi` (prix), `sofia-sm` (dev) | correctif et cas de test dans `outils/rapport-tokens-journal-test.py` |
| A9 | Ecrire la liste de corrections de cloture du relecteur dans un fichier du dossier de sprint, comme s53 | `relecteur-pedagogique` | `relecture-cloture.md` en cas de besoin au prochain sprint |
| A10 | Faire la verification de s52 contre les formes (a), (b), (c) due depuis s52 (condition (ii)) | `nadia-prof` | note dans son arbitrage de s55 |
| A11 | Decider des trailers `Opus 5.5` : garder la regle de `DEC-0110` 17.4 (modele execute) ou fixer le trailer ; les 7 commits ne sont pas reecrits dans les deux cas | utilisatrice | une phrase |
| A12 | Traiter les actions de la retro de s53 restees ouvertes : A2 (voir A2 ci-dessus), A9 (provenance de `relecture-p11.md` lignes 7 a 9), A11 | leurs proprietaires | voir la retro de s53 |
| A13 | **Recompter tout chiffre de capacite repris d'un message** avant de l'ecrire dans une `DEC` : A.25 portait 3,25 pour A, reel 3,75 | `camille-po`, `sofia-sm` (je donne les chiffres, j'indique leur origine) | chiffre source a l'arbitrage de l'instance |

## 5. Amendements proposes a `squad/README.md` (non ecrits par moi)

| Section | Proposition | Fait mesure | Demandee a |
|---|---|---|---|
| 4 (`PAR-16`) et 9 | Ajouter a la regle de l'amendement de planning : *"y figurent les noms de worktree, de branche et de scratchpad de toute instance ouverte apres le planning"* | s54 : instance C sans nom ecrit au planning, isolation non verifiable (obstacle 22) | `camille-po`, `sofia-sm` |
| 4 | Un critere "relu par le developpeur" nomme le fichier du resultat | `TRV-CE1-5`, `EMC-9` : aucun fichier (C6) | `camille-po`, `sofia-sm` |
| 9 (porte technique) | Quand un rapport qualite mesure un arbre anterieur au sommet de la cloture, la review dit **les fichiers qui ont change depuis** et refait la commande qui touche ce qui a change | 16 fichiers depuis `db69bbc6`, dont R3 | `yanis-qa`, `sofia-sm` |

## 6. Une ligne sur les ceremonies

Planning (`ccd47543`, 2026-10-07 20:32) et review (cette tache) existent ; **aucun fichier de planning n'existe pour l'instance C ni pour les amendements** (obstacle 22). Le daily n'est pas journalise.
`yanis-qa`, `theo-archi` et `relecteur-pedagogique` ont ecrit leurs rapports de cloture ; le relecteur n'a pas de fichier (obstacle 17).

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette retro.** Les amendements de la section 5 sont demandes, pas ecrits.
