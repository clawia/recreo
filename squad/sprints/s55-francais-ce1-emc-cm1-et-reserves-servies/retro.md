# Retro - s55 - francais CE1, EMC CM1 et reserves servies

- **Animee par** : `sofia-sm`, casquette Scrum Master, 2026-10-08. Aucun agent invoque : cette retro lit les ecrits du sprint (`review.md`, `rapport-qualite.md`, `confirmation-archunit.md`,
  `relecture-cloture.md`, `cloture-nadia.md`, `DEC-0115`, fiches, backlog, rapports de developpeurs). Chaque constat cite sa source ; ce que je n'ai pas pu mesurer est dit.
- **Retro de s54 relue** : actions A1 a A13, etat en section 3.
- **`lessons.md`** : n'existe pas dans le depot (`find . -iname lessons.md` ne rend rien, comme au planning). La memoire des corrections est cette retro et `squad/README.md`.

## 1. Ce qui a fonctionne, mesure

| # | Constat | Mesure | Source |
|---|---|---|---|
| W1 | **La decision et les conditions ont precede chaque branchement.** `DEC-0115` (15:24), l'avis de `theo-archi` (15:48) et les deux arbitrages prealables de `nadia-prof` (15:40 et 15:50) sont anterieurs au premier commit de livraison (`f53f5c47`, 16:30) | 4 branchements sur 4 ; premier commit de livraison 16:30, `DEC-0115` 15:24 | `git log` ; `rapport-qualite.md` |
| W2 | **Aucun REFUSE.** Six audits joues, zero refus (s54 : 0 sur 6 ; s52 : 1 sur 6). Les conditions `K1` a `K5` ecrites avant branchement ont ete appliquees mot pour mot | 0 refus, 5 `VALIDE SOUS RESERVE`, 1 `VALIDE` | `cloture-nadia.md` section 1 ; `proposition-s56.md` section 2 |
| W3 | **Une lecture de testabilite avant la premiere livraison a trouve des criteres faux avant qu'un developpeur les suive.** `yanis-qa` en a releve 14 ; `camille-po` en a corrige 12, refuse 2, route 1. Deux criteres de s54 sans fichier de resultat (`TRV-CE1-5`, `EMC-9`) ont un fichier a s55 | 14 questions, 12 corriges | `lecture-testabilite.md` section 4 ; `rapport-camille-criteres.md` section 1 |
| W4 | **Le resultat "relu par le developpeur" a un fichier et une ligne par regle** (action A4 de s54). `marc-dev` : `TRV-CE1-5` rendue executable (section 3 de son rapport) ; `lea-dev` : `EMC-4`, `EMC-8`, s53 4.1, `BIA-5` lus, `P4` dit *"non garde"* | s54 : 0 sur 2 ; s55 : 2 sur 2 | `rapport-marc-dev.md` ; `rapport-lea-dev-emc.md` section 5 |
| W5 | **Une mesure locale a contredit un enonce relaye avant la premiere fusion.** *"CI verte sur fbe07d79"* : `theo-archi` mesure deux echecs sur l'arbre propre ; la ligne est corrigee a 15:53 (`8f740977`) | 1 rouge trouve, solde avant le premier commit de developpement | `avis-faisabilite.md` section 0 |
| W6 | **Les mutations ont ete rejouees par un tiers et ont mordu.** 22 mutations distinctes ; `M2`, `A8` `test_6` (trois mutants), `P3`, `L1` et quatorze mutants du garde-fou 4 rouges ; un mutant equivalent demontre comme tel | 20 rouges, 1 equivalent, 1 survivante (`Q-Y8`) | `rapport-qualite.md` section 3 |
| W7 | **Les tests sont dans le commit du code**, pour les 4 paquets et pour T2 et T3 : 91 tests de plus sur 2216 | +91 (+87 mesures par `yanis-qa`, +4 de `2224e91e`) | `review.md` section 4.5 |
| W8 | **Les agents ont nomme les jobs qu'ils ont lances** et ceux qu'ils n'ont pas lances (C6 du planning). Une exception : `rapport-lea-dev.md` (`ART-RES`) ne nomme que `build` | tous les rapports lus sauf un | rapports de `marc-dev` (cinq), `lea-dev` (trois), `yanis-qa`, `theo-archi`, relecteur, `camille-po`, `nadia-prof`, `sofia-sm` |
| W9 | **La capacite a tenu.** Deux lots par instance ; plafond dur de 5,0 non approche ; marge de A non utilisee ; aucune troisieme instance | A 3,5 hors marge, B 3,5 ou 4,0 selon la lecture | `review.md` section 10 |
| W10 | **Aucune fusion sur une mauvaise base** : 32 fusions, apport de 1 a 4 commits, base de fusion toujours le sommet de `round-two` ou un de ses ancetres directs | 32 sur 32 | `git merge-base` et `git rev-list --count`, relances a la cloture |
| W11 | **Les amendements ont corrige sans effacer** : `DEC-0115` A.1 a A.21, planning amende le 2026-10-08 a 16:20, avant le premier commit de developpement (16:30), puis a la cloture | 21 amendements, texte d'origine lisible | `DEC-0115` ; `planning.md` |
| W12 | **Deux dettes de s54 ont ete payees par ecrit** : l'isolation de l'instance C (A6 : *"faisable, et tenu"*, `rapport-qualite.md` section 5) et la verification de s52 contre les formes (a), (b), (c) (A10 : *"condition (ii) remplie pour s52"*) | 2 sur 2 | `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 2 |

## 2. Ce qui a coute, mesure

| # | Constat | Mesure | Source |
|---|---|---|---|
| C1 | **Un correctif de CI sans poste et sans cause ecrite.** `a3606b2f` (`theo-archi`) corrige un rouge de CI *"du 2026-10-08"* que ni `squad/`, ni `DEC-0115`, ni le planning ne decrivent ; il est pousse a 19:09 ; la CI sur ce commit n'a pas ete lue | 0 fichier decrit la cause | `confirmation-archunit.md` section 5 ; `review.md` section 5 |
| C2 | **Quatre postes livres sans ligne de planning** : `2b66a441`, `fa3b97c0`, `e6262ac2`, `a3606b2f`. L'action A5 de s54 (amender avant le worktree) a tenu pour les postes connus a 16:20 et pas pour ceux nes des verdicts ou d'un rouge | 4 postes, 0 amendement | `review.md` obstacle 5 |
| C3 | **Mon rapport d'A7 et A8 a rendu `round-two` rouge pendant 17 minutes.** `0e4b8ada` (19:25) porte une ligne de tableau a cinq cellules ; je n'avais pas lance `build`, au motif que le diff ne touchait aucun Java : or `com.recreo.archi` lit tout `squad/` (`ADR-0087`). `marc-dev` (corrections) et `nadia-prof` (audit d'`EXC-R15`) ont chacun lance un `verify` rouge pour cette seule ligne ; rouge de la fusion de `s55-sofia-outils` (19:32) a `02798fa7` (19:49, directement sur `round-two`) | 2 `verify` rouges chez 2 agents, 17 min sur `round-two` | `rapport-marc-dev-corrections.md` E-1 ; `audit-nadia-b-exc.md` ; `rapport-sofia-outils.md` section "Jobs" |
| C4 | **C'est la deuxieme fois en deux sprints.** La ligne `U4` de la review de s54 portait quatre cellules pour trois colonnes depuis `23b55b60` (`[CAM]`, 14:20, annotation d'une ligne de ma review) : `round-two` etait rouge en local de 14:20 a 15:53 (`8f740977`), pousse a 14:28, et personne ne le savait avant `theo-archi` | 2 occurrences sur 2 sprints | `avis-faisabilite.md` section 0 |
| C5 | **La forme 3 sans sa formule, deuxieme occurrence** (s53 `R2`, s55 `R1`) : dans `DEC-0115` (sections 0.1, 0.5, 5, A.1, A.16) et dans mon planning (lignes 27 et 395). Elle a deplace le financement de la mecanique d'une decision a une execution consignee (A.18) | 12 lignes de `DEC-0115` et 2 lignes du planning | `relecture-cloture.md` `R1` ; `DEC-0115` A.18 |
| C6 | **Quatre criteres du planning etaient insatisfiables, et ils sont de ma main.** `mise . l.cart`, `g.teau`, 42 `@Test`, cliquet de `TYP-1` "de 12 a 0" figurent dans mon amendement de 16:20 (section D) ; `yanis-qa` les a trouves rouges ou faux ; `camille-po` les a corriges au backlog ; je ne les ai alignes qu'a la cloture, cinq heures apres | 4 criteres, 6 lignes | `relecture-cloture.md` `R2` ; amendement de cloture du planning |
| C7 | **Cinq ecarts de critere restent apres la lecture de testabilite** : `Q-Y6` (23 et non 2), `Q-Y9` (`QC4` : 2 fichiers), `Q-Y10` (numeros de ligne), `C7`/`EMC-9`, et le planning (traite). `Q-Y8` etait le sixieme : un cas fabrique a ete livre apres le rapport qualite | 5 ecarts, 1 corrige a la cloture | `rapport-qualite.md` section 4 |
| C8 | **Le rapport qualite ne mesure pas le sommet.** `af8b326e` : 2303 ; sommet : 2307 ; entre les deux, 22 fichiers dont 4 classes de test | +4 tests, 22 fichiers | `review.md` section 4.3 |
| C9 | **Le sprint produit des reserves plus vite qu'il ne les paie.** Six reserves ouvertes (2,5 de rejeu), `S54-OBS-SCI-CONSIGNE` reportee, `L3` et le paquet a reproductions non engages. A s54 : 7 stories ouvertes et non engagees | 6 reserves, 2,5 ; 0 capacite a s55 | `cloture-nadia.md` section 2 ; `proposition-s56.md` section 3 |
| C10 | **Un dispositif livre et finance sans paquet, jamais joue.** T3 retouche le gabarit de tous les jeux ; sa branche image n'a jamais ete rendue a un enfant ni a l'oeil. `nadia-prof` l'a lue et l'a sondee en texte, sans Done ; `E3` n'est pas acceptable pour un paquet servi. Si les mots de l'utilisatrice divergent, le prix est celui du retrait (`avis-faisabilite.md` section 5) | 30 tests, 99 lignes de CSS, 0 ecran joue | `cloture-nadia.md` sections 3 et 4 |
| C11 | **Deux lectures du meme total de capacite** pour B (3,5 contre 4,0), non reconciliees a la cloture | 0,5 d'ecart | `review.md` section 10 |
| C12 | **Consommation de tokens.** 3706 tours et 2 107 822 d'output contre 2364 et 1 240 709 pour s54 (fenetre de s54 ouverte a sa generation : le journal la recalcule a 2473 et 1 327 873). `nadia-prof` 868 tours (23,4 %), `camille-po` 562 (15,2 %), `lea-dev` 455 (12,3 %) ; `ines-design`, absente a s54, porte 93 tours | +57 % de tours sur s54 brut, +50 % sur s54 recalcule | `RAPPORT-tokens.md` ; `JOURNAL-tokens.md` |

## 3. Retro de s54 : etat des actions A1 a A13

| # | Action de s54 | Etat a la cloture de s55 |
|---|---|---|
| A1 | `DEC` d'ouverture de s55 | **Faite** : `DEC-0115`. `Proposee`, financement de la mecanique en forme 3 (C5) |
| A2 | Pousser, lire la CI en nommant le commit | **Pousse en cinq fois pendant s55** (reflog local), **verdict non lu, aucun commit nomme** : reportee en A3 ci-dessous |
| A3 | Garde-fou de contenu sur les motifs de refus de sciences | **Regle ecrite** (`avis-faisabilite.md` T2 : un test est du quand le motif est une propriete decidable d'un item nomme) ; appliquee a `P3` (mutation rouge) ; aucun paquet de sciences touche : sans poste |
| A4 | Nommer le fichier du resultat ecrit d'un critere "relu" | **Faite et tenue** (W4) |
| A5 | Amender le planning au geste | **Tenue pour 16:20, non tenue pour quatre postes** (C2) |
| A6 | Diff de `s54-nadia-c` | **Faite** par `yanis-qa` : tenue (W12) |
| A7 | `mvn-jdk21.sh` et `JAVA_TOOL_OPTIONS` | **Livree** (`8541cffc`) ; la mutation annoncee par `yanis-qa` n'est pas faite (A9 ci-dessous) |
| A8 | `rapport-tokens.py` sans `--sprint` | **Livree** (`69d9d6a2`) ; mutations rouges chez `yanis-qa` |
| A9 | Fichier de relecture de cloture | **Livre** (`relecture-cloture.md`) |
| A10 | Verification de s52 contre les formes (a), (b), (c) | **Faite** (W12) ; celle de s54 n'est routee a personne (A11 ci-dessous) |
| A11 | Trailers | **Inchange** : `DEC-0110` 17.4 (modele execute) ; onze commits Opus et deux Sonnet dans cette tache de s55, sans reecriture ; lecture a confirmer (`review.md` F5) |
| A12 | Actions de s53 : A2, A9 (`relecture-p11.md`), A11 | A2 voir ci-dessus ; **A9 et A11 de s53 non reverifiees par moi** |
| A13 | Recompter tout chiffre de capacite repris d'un message | **Faite pour le total** (section 10 de la review, arithmetique recomptee) ; l'ecart sur B n'est pas reconcilie (C11) |

## 4. Ce qui a coince, et pourquoi (lecture de `sofia-sm`)

- **Les quatre postes nes d'un verdict ou d'un rouge n'ont pas de ligne, et je n'ai pas ete invoque entre le verdict et leur worktree.** J'ai ecrit la regle de l'amendement avant le worktree (A5) ;
  le planning confie l'ouverture des worktrees a l'agent qui me lance (section "Isolation et fusion") et **aucun commit `[SOF]` hors fusion n'existe entre 16:20 et 19:16** (`git log`). `grep -c "2b66a441\|fa3b97c0\|e6262ac2\|a3606b2f"`
  sur `planning.md` rend 0. Un amendement retrospectif serait une ceremonie fabriquee : je n'en ecris pas pour ces quatre postes. Ce que cela dit du mecanisme (rien n'oblige a m'invoquer) est une lecture, pas une mesure : la proposition est P1 de la section 6.
- **Le raisonnement "pas de Java, donc pas de build" est faux dans ce depot** (C3, C4). `com.recreo.archi` lit tous les `.md` ; un fichier de rapport est un fichier du build. J'avais ecrit moi-meme dans le rapport
  que la porte etait verte *"sur trois jobs sur quatre"* ; j'ai laisse la quatrieme etape de cote sur un argument que `CLAUDE.md` (amendement du 2026-09-08) interdit : **ne pas deduire les jobs qu'on n'a pas lances**.
  La pratique de cette cloture : A6.
- **L'amendement de planning de 16:20 portait des criteres que je n'avais pas verifies** (C6). Je les avais pris du backlog et de l'avis tels qu'ils etaient a 16:20 ; la lecture de `yanis-qa` est arrivee ensuite.
  La regle que cela nomme : un critere recopie dans le planning se recopie avec sa commande **rejouee**, ou renvoie au backlog sans la reproduire.
- **Le lancement porte un perimetre par messages d'agent** (`DEC-0115` 0.1, A.1, A.18). Trois enonces ont fonde du travail sans les mots de l'utilisatrice ; l'un d'eux (le financement) est resté a l'etat d'execution consignee.

## 5. Actions pour s56

| # | Action | Proprietaire | Artefact attendu |
|---|---|---|---|
| A1 | Ecrire la `DEC` d'ouverture de s56 : placer les six reserves (2,5, dont `CLM-CE1-R1` a 1,0 seule, `PAR-14`), l'option O1 ou O3 (`proposition-s56.md` section 4), `S54-OBS-SCI-CONSIGNE` (second report : acte ecrit qui cite le premier), `S55-ART-CP-10-PAQUET` (T4, T5) et `S55-ART-CP-10-PLAFOND` | `camille-po`, avec l'utilisatrice | `DEC` |
| A2 | **Q6 et la suite** : ses mots en forme 2 sur le financement de la mecanique d'`ART-CP-10` (`DEC-0115` passe a `Acceptee`) ; le moment du paquet a reproductions (Q-U1) ; l'ordre des matieres pour s56 ; le decoupage du francais CE1 en deux instances | utilisatrice | mots ecrits dans le depot avec leur date |
| A3 | Pousser, lire la CI **sur un commit nomme, job par job**, et dire pourquoi `gh run list` rendait `HTTP 404` depuis ce poste | utilisatrice | verdict nomme |
| A4 | Ecrire la cause du rouge de CI que `a3606b2f` corrige, avec la commande ou le message qui la reproduit | `theo-archi` | note dans son avis de faisabilite de s56 ou amendement de `confirmation-archunit.md` |
| A5 | **`S55-ART-CP-10-PLAFOND`** : planifier la dette avant le premier paquet a propositions-images. Je l'inscris au planning de s56 comme **poste prealable de tout paquet a reproductions** (story `camille-po`, prix et forme du garde-fou `theo-archi`, developpeur propose : `lea-dev`, proprietaire de la mecanique) : sans lui, le plafond de 176 px ne tient que par l'arithmetique d'une case, garde par aucun test | `sofia-sm` (planning), `camille-po` (story), `theo-archi` (prix, forme) | ligne au planning de s56, story au backlog |
| A6 | **Ma pratique corrigee.** Tout fichier que j'ecris sous `squad/` ou `docs/` passe par `bash outils/mvn-jdk21.sh -B clean verify` **avant son commit**, ou a defaut par la classe `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (`-pl bootstrap -am`, une classe, perimetre nomme) ; mes rapports nomment `build` parmi les jobs lances ou disent qu'il ne l'est pas, sans le juger inutile. Je n'ecris plus un tableau avec une barre verticale dans une cellule, dans du code en ligne compris | `sofia-sm` | ligne "jobs lances" de mes rapports |
| A7 | Reprendre les cinq ecarts de critere du rapport qualite : `Q-Y6` (23, pas 2), `Q-Y9` (`QC4` : 2 fichiers), `Q-Y10` (lignes 463 et 481 a 492), `C7`/`EMC-9` (la regle de relecture de l'EMC est celle de l'arbitrage B 1.2) ; `yanis-qa` les relit | `camille-po`, `yanis-qa` | backlog corrige |
| A8 | Rejouer la mutation de `Q-Y8` sur `2224e91e` (le critere nomme `yanis-qa` ; `marc-dev` l'a rejouee, 4 echecs) ; faire **la mutation d'A7** (retirer `grep -m1 'version "'`) et **les morsures de T2** (garde-fous 1 a 3) restees non faites | `yanis-qa` | rapport qualite de s56, ou lecture de testabilite |
| A9 | Dire laquelle des deux lectures de la capacite de B vaut (3,5 ou 4,0) ; reporter le chiffre au backlog | `nadia-prof`, `camille-po` | une ligne dans l'arbitrage prealable de s56 |
| A10 | Faire la verification de s54 (trois instances) contre les formes (a), (b), (c) : elle conditionne toute borne a trois instances et n'est routee a personne | `nadia-prof` | note dans son arbitrage de s56 |
| A11 | Completer l'en-tete de `FICHE-bandit-manchot-exclusion-cp.md` (`EXC-R15` dit ouverte) ; juger le titre du jeu (*"Une moquerie qui se repete"*) | `nadia-prof` | fiche, au rejeu d'`EXC-R16` |
| A12 | Relire la ponctuation des 12 indices de `cm1-grands-nombres` et l'ecrire (une ligne, meme sans trouvaille) | `relecteur-pedagogique` | ligne dans `relecture-cloture.md` de s56 |
| A13 | Lever les `R4`, `F2`, `F3` quand le fichier est touche : citation de 2018 (`EMC-CM1-R9`), commentaire *"oeuvre-revelee"* de `manche.html` | `lea-dev` | commits des rejeux |
| A14 | Dire si la formule de la forme 3 (`recu de MASTER, sans source dans le depot`) peut etre gardee par un test : deux occurrences sans formule en trois sprints (`R2` de s53, `R1` de s55) ; `CLAUDE.md` dit qu'une regle non executable est un souhait | `theo-archi` (avis) | avis ecrit, test ou refus motive |
| A15 | Placer la mesure de pli de `LIS-2` (`cm1-lire-et-restituer`) en tete de s56 si le lot est voulu | `ines-design`, `camille-po` | mesure ecrite |

**Faits a la cloture, sans action** : `CLAUDE.md` ligne 167 porte `python outils/rapport-tokens.py --sprint sNN` (`de1aa216`, R5) ; seule copie a mettre a jour, `squad/README.md` et `.claude/agents/sofia-sm.md` portaient deja `--sprint sNN`
(`grep -rn "rapport-tokens.py" CLAUDE.md squad/README.md .claude/agents/sofia-sm.md`) ; aucun autre fichier de `.claude/agents/` ne cite la commande. `planning.md` amende (`b20655fa`, R1 et R2).

## 6. Amendements proposes a `squad/README.md` (non ecrits par moi)

| Section | Proposition | Fait mesure | Demandee a |
|---|---|---|---|
| 4 et 9 | **Un commit de livraison porte un poste.** Tout poste ne d'un verdict, d'une reserve ou d'un rouge de CI s'ecrit dans un amendement date du planning avant l'ouverture de son worktree, et le lanceur cite la ligne du planning ou de l'amendement dans le message de lancement. Reprend la proposition de s54 (noms de worktree, de branche, de scratchpad) | s55 : 4 postes sans ligne (`2b66a441`, `fa3b97c0`, `e6262ac2`, `a3606b2f`) ; s54 : instance C et `PCO-CE1-R1` | `camille-po`, utilisatrice |
| 4 | Un critere "relu par le developpeur" nomme le fichier du resultat (proposition de s54, **appliquee et tenue a s55**, W4) | `rapport-marc-dev.md`, `rapport-lea-dev-emc.md` : 2 sur 2 | `camille-po`, `sofia-sm` |
| 9 (porte technique) | Quand un rapport qualite mesure un arbre anterieur au sommet, la review dit **les fichiers qui ont change depuis** et le total du sommet (proposition de s54, **appliquee a s55**, `review.md` 4.3) | 22 fichiers, +4 tests, 2303 contre 2307 | `yanis-qa`, `sofia-sm` |
| 9 | **Toute tache qui ecrit un `.md` sous `squad/` ou `docs/` lance le build ou, a defaut, la classe de `ADR-0087`, et le dit** : `build` est le job qui juge ces fichiers | s54 : `review.md` ligne `U4` ; s55 : `rapport-sofia-outils.md` ligne 22 ; 2 occurrences sur 2 sprints | `theo-archi`, `sofia-sm` |
| 4 | Un critere recopie dans un planning est recopie avec le resultat de sa commande rejouee, ou renvoie au backlog sans la reproduire | 4 criteres du planning de s55 insatisfiables | `camille-po`, `sofia-sm` |

## 7. Une ligne sur les ceremonies

Planning (`2439d1b3`, 2026-10-08 14:43, amende a 16:20 et a la cloture) et review (cette tache) existent ; `retro.md` et `RAPPORT.md` sont ecrits dans la meme tache. `yanis-qa`, `theo-archi`,
`relecteur-pedagogique` et `nadia-prof` ont ecrit leurs rapports de cloture ; le relecteur a un fichier (obstacle 17 de s54 solde). Le daily n'est pas journalise.

## Decisions prises

**Aucune `DEC` ni `ADR` n'est creee ou amendee par cette retro.** Les amendements de la section 6 sont demandes, pas ecrits. Le seul fichier de gouvernance modifie par cette tache est `CLAUDE.md` (une phrase, `de1aa216`, R5) et le planning de s55 (un amendement date, `b20655fa`).
