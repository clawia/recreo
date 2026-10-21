# [Sprint s57] Planning - 2026-10-14

- **Horodatage de debut** : 2026-10-14 15:45 (UTC+02:00), lu par `date` au moment de l'ecriture.
- **Anime par** : sofia-sm, casquette Scrum Master. Aucune story de developpement portee par moi dans ce fichier.
- **Participants** : aucun agent invoque. Ce planning constate l'etat du depot, mesure, decoupe et affecte. **Sources lues, par auteur** : theo-archi (`avis-faisabilite.md`, `ea6cd1fd`) ; nadia-prof (`arbitrage-nadia-palier-1.md`, `8396696e`, fusionne `7180f666`) ; camille-po (`composition-palier-1.md`, `0aec34f3`, `DEC-0120` A.53 a A.62, stories `S57-BR-*` et annotations du backlog) ; yanis-qa (`lecture-testabilite.md`, `da1991b7`) ; sofia-sm (`duree-et-paliers.md`, `13e2507b`, `preparation.md`, `ef34b8bf`).
- **Slug** : `s57-version-cp-ce1-cm1-trois-instances`. Le dossier existait avant ce planning (cree par `preparation.md`) ; ce planning n'en cree pas.
- **Lignee verifiee avant ecriture** : branche `s57-sofia-planning`, `git log -1 --format=%h` rend `771b55de` (tip de `round-two`), `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide.

## Sur quoi repose l'ouverture

- **La confirmation de l'utilisatrice, 2026-10-14, forme 2** : mots *"Option 1"*, relayes par l'agent principal, **que je n'ai pas lus de sa main**. Lecture de l'agent principal : le planning peut s'ecrire sur l'etat actuel et s57 s'ouvre ; `Q3` (de `nadia-prof`) = oui ; `Q-S1` (de `sofia-sm`) = ok, soit une seule review et une seule retro a la fin, plus un fichier de cloture de palier par `sofia-sm`.
- **`DEC-0120` n'est pas `Acceptee`.** `grep -n "^- \*\*Statut" <DEC-0120>` rend, ligne 13, **`Proposee`**. `camille-po` n'a pas acte son acceptation (`DEC-0120` A.55 et A.61 : elle la passe par un amendement date quand la ligne de duree de `sofia-sm` est dans le depot). **S57 ne s'ouvre donc pas sur un passage de `DEC-0120` a `Acceptee`, il s'ouvre sur la confirmation de l'utilisatrice ci-dessus, et sur elle seule.** Ce planning ne passe pas la decision a `Acceptee`. Il ecrit la duree comme decidee (section "Duree"), ce que A.55 nomme comme condition ; le passage reste a `camille-po`.
- **Ce qui rendrait cette base fausse** : si la confirmation relayee n'etait pas celle de l'utilisatrice, l'ouverture n'aurait aucun fondement ecrit. Je le dirais le jour meme, avant tout worktree de lot.
- **`K1 (a)` du backlog** (aucune edition de lot avant que `planning.md` existe et nomme la story) : remplie quand ce fichier est fusionne sur `round-two`. **Aucun worktree de poste engage ne s'ouvre avant cette fusion** ; cette tache n'en ouvre aucun et ne fusionne rien.

## Objectif du sprint

Gele : **brancher et faire auditer, au palier 1, les deux lots de l'instance A' (`FRA-CE1-10`, `ce1-phrases-types`, et `FRA-CE1-15`, `ce1-accord-groupe-nominal`), lever la reserve `VSJ-CE1-R1` sur `FRA-CE1-13` (A), et livrer puis rejouer `S54-OBS-SCI-CONSIGNE` (B).**

**Reserves du palier, hors de l'objectif gele** : le lot `FRA-CE1-04` (A) et la reprise d'`ART-CP-10` (B). Une question bloquante est ouverte pour chacun (section "Questions") ; ils entrent dans l'objectif par un amendement date de ce fichier, a la reponse, jamais avant.

Aucune ligne de matrice ne bouge avant le verdict ecrit de `nadia-prof`. Matrice a l'ouverture : 142 / 200 (`DEC-0120` A.57), **non remesuree ici**. Lignes susceptibles de fermer : `FRA-CE1-10`, `FRA-CE1-15`, et sous reserve `FRA-CE1-04` et `ART-CP-10`. `FRA-CE1-13` et `SCI-CM1-20` sont `couvert-valide` et ne sont pas rouvertes. Ce n'est pas une promesse : un Done pedagogique refuse reste un refus.

## Avis de faisabilite de theo-archi

`avis-faisabilite.md` (`ea6cd1fd`, base `544159d9`). **Non faisable : rien.** Verdicts, section de l'avis entre parentheses :

| Poste | Verdict | Condition qui entre dans ce planning |
|---|---|---|
| Correction d'`ART-CP-10` (2) | faisable sous condition | `C1` a `C4` : regle sans condition d'etat, pas de `@media` sans extension du garde-fou, jamais `.cabinet` masque, cotes jugees au jar au navigateur. `RDO-CP-R2` : trois voies, choix a `nadia-prof` |
| Rejeu d'un paquet sans entree (3) | faisable en l'etat | deux branches B1 et B2, conditions `R1` a `R3` (section "Branche de rejeu B2") |
| Lots de francais CE1 (4) | faisable en l'etat | un risque de fusion nomme sur `catalogue.yaml` et l'inventaire ; comptage a chaque fusion (section "Ordre de fusion") |
| Isolation (5) | faisable sous condition | `I1` a `I3` (section "Isolation") |
| `S54-OBS-SCI-CONSIGNE` (6) | faisable en l'etat | simulation : 0 echec sur 2498 tests |
| `VSJ-CE1-R1` (7) | faisable sous condition | l'amendement de la propriete 8 par `nadia-prof` precede le commit |
| `S57-ADR-0065-DEUX-INSTANCES` (8) | livree | `729b0d8a` |
| `HS-ACCUEIL-APM-R2` (9) | faisable moyennant un amendement d'`ADR-0106` | de la main de `theo-archi` |

Aucun `ADR` ouvert, aucune regle ArchUnit neuve (avis, 10). Les amendements d'`ADR-0104` (voies b et c) et d'`ADR-0106` sont dus seulement si la voie ou le poste est retenu.

## Duree

Reprise de `P1'` a `P6'` de `duree-et-paliers.md` **sans modification de fond**, a la lumiere de `Q3` et `Q-S1` repondues en forme 2. Ce paragraphe est la ligne datee de duree que `DEC-0120` A.55 demande. `P2'` etait deja la retraite de `P2`.

- **D1 (`P1'`).** S57 est un sprint a paliers de 8,0, sans date et sans timebox, sous ce seul `planning.md`. Sa duree est celle de ses paliers successifs. Un palier est la fenetre d'audit de `nadia-prof` (`PAR-8`, `PAR-9`), close pour toutes ses instances avant l'ouverture de la suivante. Le sprint se clot quand le dernier palier ouvert est clos au sens de `nadia-prof` (chaque poste engage a un verdict ecrit ou un report ecrit, rejeu d'`ART-CP-10` compris), ou quand l'utilisatrice l'arrete.
- **D2 (`P3'`, regle d'ouverture d'un palier).** Le palier N+1 s'ouvre quand : (a) `cloture-palier-N.md` existe sur `round-two` (`ls`), pour toutes les instances ; (b) `nadia-prof` a ecrit l'arbitrage prealable de chaque instance pour ce palier (condition 3 de 11.3) ; (c) `camille-po` a ecrit la composition, portee par un amendement date de ce fichier avec la reference de l'arbitrage, **heure inscrite avant l'ouverture des worktrees concernes** (precedent : s56) ; (d) l'amendement porte la table d'isolation du palier, repertoires et ports neufs.
- **D3 (`P4'`).** Le palier 1 est le seul engage a l'ouverture. Le nombre de paliers n'est pas fixe. Le sprint s'arrete quand aucun palier n'a de lot instruit a engager, ou quand l'utilisatrice l'arrete. Ordre de grandeur de `duree-et-paliers.md` section 3, **pas un engagement**.
- **D4 (`P5'`).** Attente d'horloge, a titre d'information, par palier : mediane 17 h 33 min, de 7 h 38 min a 41 h 38 min observees (s54, s55, s56 ; un seul sprint a trois instances, s54). **Seuil d'alerte : 41 h 38 min par palier**, compte depuis l'heure d'ouverture du palier (pour le palier 1, l'horodatage de ce fichier). Passe ce seuil avec un poste engage non audite, je le dis a l'utilisatrice le jour meme, dans `alerte-p<N>.md` d'une ligne datee si le palier n'est pas clos, dans `cloture-palier-N.md` sinon. Le seuil ne clot rien et ne limite rien.
- **D5 (`P6'`).** La duree se reevalue a la cloture de chaque palier, sur les durees effectives des paliers precedents. Un changement s'ecrit par amendement date de ce fichier.

**Ce que je ne sais pas** : aucune mesure en heures par poste ni par palier ; aucune mesure de trois instances sur du francais CE1 ; la part de la fenetre de 23 h 49 min de s56 due aux arrets.

## Capacite du palier 1

Prix : ceux de `nadia-prof` (arbitrage, section 4), confirmes pour toutes les affectations. Somme recomptee par `awk` sur les prix de la table suivante, sortie lue le 2026-10-14 : A 2,5 ; A' 3,0 ; B 2,5 ; engage 5,75 ; non engage 1,5 ; non affecte 0,75 ; total 8,0.

| Instance | Engage a l'ouverture | Non engage, reserve du palier | Non affecte | Total | Lots |
|---|---|---|---|---|---|
| A | 1,5 (arbitrage 0,5, marge 0,5, `VSJ-CE1-R1` 0,5) | 1,0 (`FRA-CE1-04`) | - | 2,5 | 1 en reserve |
| A' | 3,0 (arbitrage 0,5, marge 0,5, deux lots a 1,0) | - | - | 3,0 | 2 |
| B | 1,25 (arbitrage 0,5, marge 0,5, `S54-OBS-SCI-CONSIGNE` 0,25) | 0,5 (rejeu d'`ART-CP-10`) | 0,75 | 2,5 | 0 |

Bornes, relues dans leur texte : somme des budgets 8,0 (`PAR-9`) ; plafond dur de 5,0 par instance, aucune ne s'en approche (`PAR-8`) ; trois lots au plus par instance, A en porte un en reserve, A' deux ; aucun chiffre au-dela de 8,0 (11.4).

**Marges de rejeu, nommees a l'ouverture.** A 0,5, A' 0,5, **B 0,5**, chacune allouee a aucun poste. La marge de B couvre un second rejeu d'`ART-CP-10` (0,5) ou de `S54-OBS-SCI-CONSIGNE` (0,25) (`Q-P1`, decision D1 de `nadia-prof`). Les 0,75 non affectes de B restent non affectes, avec une seule exception de `nadia-prof` : si les deux rejeux de B sont refuses dans ce palier, le second rejeu de `S54-OBS-SCI-CONSIGNE` (0,25) se prend sur eux. Aucun lot n'y entre.

## Postes du palier 1, verification poste par poste (`PAR-16`)

Source des postes : `composition-palier-1.md` et son arbitrage (`nadia-prof`, section 4). Verification des titres de story : `grep -c "^#\{3,4\} .*<story>" docs/backlog/backlog.md`, lance sur `771b55de`. `S54-OBS-SCI-CONSIGNE` rend 6 : un titre par annotation, la derniere (*"developpeur `lea-dev`"*) est celle qui complete le placement.

| # | Poste | Story, titres au backlog | Inst. | Agent nominatif | Competence visee | Prix | Etat |
|---|---|---|---|---|---|---|---|
| 1 | Arbitrage prealable de A, A', B | aucune (`S57-ARB` : 0) | A, A', B | `nadia-prof` (A), (A'), (B) | - | 0,5 chacune | **engage, part ecrite livree** : `arbitrage-nadia-palier-1.md` (`8396696e`, fusionne `7180f666`). Reste : la lecture complete des items de chaque lot avant son premier lot, sur le meme 0,5 (arbitrage, section 4) |
| 2 | Marges de rejeu de A, A', B | aucune | A, A', B | aucun | - | 0,5 chacune | nommees, allouees a aucun poste |
| 3 | `VSJ-CE1-R1` | `S56-VSJ-CE1-R1` (1) | A | `marc-dev` ; rejeu `nadia-prof` (A) | `FRA-CE1-13` | 0,5 | **engage** |
| 4 | Lot `ce1-phrases-types` | `S57-BR-FRA-CE1-10` (1) | A' | `marc-dev` ; audit `nadia-prof` (A') | `FRA-CE1-10` | 1,0 | **engage** |
| 5 | Lot `ce1-accord-groupe-nominal` | `S57-BR-FRA-CE1-15` (1) | A' | `sofia-sm`, casquette developpeuse ; audit `nadia-prof` (A') | `FRA-CE1-15` | 1,0 | **engage** |
| 6 | `S54-OBS-SCI-CONSIGNE` | `S54-OBS-SCI-CONSIGNE` (6) | B | `lea-dev` ; rejeu `nadia-prof` (B) | `SCI-CM1-20` | 0,25 | **engage** |
| 7 | Lot `ce1-texte-narratif` | `S57-BR-FRA-CE1-04` (1) | A | `marc-dev`, apres ses postes 3 et 4 ; audit `nadia-prof` (A) | `FRA-CE1-04` | 1,0 | **non engage** : `Q-B1`, `Q-Y6` |
| 8 | Correction d'`ART-CP-10` et rejeu | `S56-ART-CP-10-REPRISE` (1), `S56-RDO-CP-R1` (1), `S56-RDO-CP-R2` (1) | B | `lea-dev` (developpeur, `DEC-0120` A.44) ; forme `theo-archi`, `ines-design` ; rejeu `nadia-prof` (B) | `ART-CP-10` | 0,5 (rejeu) | **non engage** : `Q1`, `Q2` de `theo-archi`, `Q-Y5` |
| 9 | Branche d'audit B2 (entree de catalogue neuve) | `S56-RETRAIT-RAPPROCHER-OEUVRES` (1), dernier critere | B | `lea-dev` | `ART-CP-10` | 0,0, hors `PAR` | **non engage** : depend du poste 8 et de `Q3` de `theo-archi` |
| 10 | `S57-ADR-0065-DEUX-INSTANCES` | (2) | hors `PAR` | `theo-archi` | transverse | 0,0 | **livree avant l'ouverture**, `729b0d8a` (2026-10-13 17:42), `git merge-base --is-ancestor 729b0d8a HEAD` sort 0. Aucune ligne engagee |

**Deux faits sur cette table.**

1. **Postes 7, 8, 9 : ni une troisieme issue silencieuse, ni un budget libere au sens strict.** `PAR-16` ecrit deux issues : une story ecrite et affectee, ou *"non engage, budget libere"* avec le montant et sa destination. Ces trois postes ont leur story et leur affectation ; ce qui leur manque est une reponse a une question bloquante. Je les ecris **non engages a l'ouverture** et je dis ou va le montant : **1,0 (poste 7) reste dans A et 0,5 (poste 8) reste dans B, alloues a aucun autre poste, aucun lot n'y entre sans amendement de `camille-po` et arbitrage de `nadia-prof`.** Chacun redevient engage par un amendement date de ce fichier, a la reponse. Si `camille-po` lit cette destination comme un etat que `PAR-16` n'a pas prevu, elle le dit : `Q-S2`.
2. **Poste 1 : aucune story `S57-ARB`.** Son livrable est un fichier de `nadia-prof` deja sur le disque ; la part restante est sa lecture des items avant son premier lot. C'est un ecart a la lettre de `PAR-16`, ecrit ici, pas silencieux. Je ne le tranche pas : `Q-S3`.

**Hors `PAR`, hors sprint, lignes ecrites parce que `DEC-0120` 4.5 dit que le planning les trouvera au backlog.**

| Poste | Story | Agent | Etat |
|---|---|---|---|
| `RP-ACCUEIL-147` | `RP-ACCUEIL-147` (1) | `nadia-prof` | **repris ici, comme l'utilisatrice l'a demande le 2026-10-12** (`DEC-0120` 4.5). Engage hors `PAR`, aucun prix ecrit, ne consomme aucun des 8,0. L'instance et le moment sont a `nadia-prof` : `Q-S3` |
| `HS-ACCUEIL-MESURE-DEFAUT` | `HS-ACCUEIL-MESURE-DEFAUT` (1) | `yanis-qa` | engage hors `PAR`, action sans budget ; sa ligne vit dans `rapport-qualite.md` |
| `HS-ACCUEIL-APM-R2` | `HS-ACCUEIL-APM-R2` (1) | developpeur non designe | **non engage** : le moyen est ecrit (`theo-archi`, avis, 9) mais l'audit de `nadia-prof` avant fusion (`DEC-0117` section 11) n'a pas de prix, et je ne designe pas un developpeur pour un poste dont le cout en audit n'est pas ecrit : `Q-S3` |
| `S57-FRA-FAMILLES-LIVRE` | `S57-FRA-FAMILLES-LIVRE` (1) | a designer | **non engage**, palier 2, aucun prix ; `Q-B2` |

**Actions des autres agents, sans budget** (`DEC-0116` section 9) : `yanis-qa`, `rapport-qualite.md` sur l'arbre fusionne a la cloture du sprint ; `theo-archi`, confirmation ArchUnit a la review ; `relecteur-pedagogique`, `relecture-cloture.md` a la review ; `camille-po`, fiche `FICHE-bandit-manchot-<id>.md` apres chaque commit de branchement (K9), matrice au verdict seulement.

**Verdict `PAR-16`** : les postes 3 a 6 portent une story et une affectation nominative et sont engages ; les postes 7 a 9 aussi et sont non engages par ecrit avec leur montant ; le poste 2 (marges) n'est pas un poste de travail ; le poste 1 est le seul sans story, dit.

## Questions ouvertes : ce qui bloque, a qui c'est route, ce que le planning engage

Regle appliquee : **un poste dont une question bloquante est ouverte n'est pas engage.** Une question est bloquante pour un poste quand sa reponse change un critere de ce poste ou la forme de ce qu'il livre. Je classe, je ne reponds a aucune.

| Question | Objet | Bloque | Route a | Ce que ce planning engage ou n'engage pas |
|---|---|---|---|---|
| `Q1` (theo-archi) | la correction d'`ART-CP-10` leve-t-elle `RDO-CP-R1` dans le meme rejeu : cible 125 px et non 105 a 744 | **poste 8** | `nadia-prof` (cible), `ines-design` (forme) | poste 8 non engage ; aucun worktree de correction ne s'ouvre |
| `Q2` (theo-archi) | `RDO-CP-R2` : voie (a) textes, (b) controleur, (c) oeuvre a l'ecran de fin | **poste 8** | `nadia-prof`, `ines-design` ; si (b) ou (c), amendement d'`ADR-0104` par `theo-archi` dans le commit du code | idem |
| `Q3` (theo-archi) | l'entree neuve de B2 reprend-elle l'identifiant `bandit-manchot-rapprocher-deux-oeuvres-cp` ou en prend-elle un neuf | **poste 9** seul | `camille-po` | poste 9 non engage ; B2 n'est pas ecrite avant la reponse |
| `Q4` (theo-archi) | crochet du harnais refusant une ecriture a la racine du scratchpad | rien : `I1` a `I3` suffisent (avis, 5.3) | utilisatrice, routee par `sofia-sm` | aucun poste ne l'attend ; la garde reste celle de `nadia-prof` seule (arbitrage, 7) |
| `Q5` (theo-archi) | la ligne de rejeu par B2 et la table d'isolation | rien | `sofia-sm` | **repondue par ce planning** (sections "Branche de rejeu B2" et "Isolation") |
| `Q-Y1` (yanis-qa) | `S57-ADR-0065-DEUX-INSTANCES`, critere 2 : un seul commit, alors que le test est inchange et que la dette est payee en deux commits | rien : la story est livree | `camille-po` | ecart cite, pas corrige ; aucune ligne engagee |
| `Q-Y2` (yanis-qa) | `VSJ-CE1-R1` : l'exemple de `nadia-prof` rend rouges `J1`, la liste d'ouvertures de `TRV-CE1-6` et le plancher de formes de sujet | **pas l'engagement du poste 3** : le critere ecrit demande `J1` tenue et dit *"par exemple"* ; `marc-dev` l'applique tel quel et, s'il doit amender un test, **il s'arrete et route** | `camille-po`, `nadia-prof` | poste 3 engage. Porte du commit, section "Portes" |
| `Q-Y3` (yanis-qa) | `S54-OBS-SCI-CONSIGNE` : le critere 2 laisse passer une question reecrite sur sa ligne, le critere 1 n'est garde par aucun test une fois livre | **pas l'engagement du poste 6** : les criteres ecrits sont testables et tenables (12 puis 0, diff sur 12 lignes). Ils laissent un trou, ils ne sont pas faux | `camille-po` | poste 6 engage sur les criteres du backlog. **Un amendement date de `camille-po` avant le premier commit de `lea-dev` prevaut** ; son rapport nomme les tests lances |
| `Q-Y4` (yanis-qa) | rejeu d'un paquet sans entree : comment, quel commit etend l'inventaire ; un juge pur sur le JSON de `pilote-q7.mjs` ? | **poste 8** pour la seconde moitie | `sofia-sm` et `theo-archi` | **premiere moitie repondue** par `theo-archi` (avis, 3) et par ce planning (B2). Seconde moitie (juge pur) : `theo-archi`, `yanis-qa` ; l'avis dit que la propriete se juge par le releve au jar puis par le rejeu (`C4`), pas par un test du reacteur |
| `Q-Y5` (yanis-qa) | `RDO-CP-R2` : quelle liste ferme la propriete *"aucun texte ne designe une oeuvre absente"* | **poste 8** | `nadia-prof` | idem poste 8 |
| `Q-Y6` (yanis-qa) | trois lots de francais CE1 : regle de comptage de `LON-CE1-3` et `-4` (22, 4 et 10 pour `-04` par la regle de `yanis-qa`, avec 4 phrases sur une borne de 4), propriete de la majuscule | **poste 7** ; **pas les postes 4 et 5** : leurs stories portent leurs commandes de recompte et ne comptent ni mots ni phrases de texte | `camille-po`, `nadia-prof` | poste 7 non engage (avec `Q-B1`). Postes 4 et 5 engages |
| `Q-Y7` (yanis-qa) | fichiers partages entre A et A' : ordre de fusion, auteur de la resolution, verification | rien | `sofia-sm`, `theo-archi` | **repondue par ce planning** (section "Ordre de fusion") ; la verification est celle de `theo-archi` (avis, 4) |
| `Q-B1` (camille-po) | `ce1-texte-narratif`, points 6.1 (1) et (2) : correction avant le branchement (cible 0) ou valeur consignee et jugee au jeu | **poste 7** : sans la reponse, `S2` et `S3` n'ont pas de cible et `K2` ne sait pas si le YAML est touche | `nadia-prof`, routee par `sofia-sm` | poste 7 non engage ; amendement de `S57-BR-FRA-CE1-04` avant l'ouverture de son worktree (`S5`) |
| `Q-B2` (camille-po) | l'indice cite en 6.3 est-il celui de l'item *livre* | `S57-FRA-FAMILLES-LIVRE` (palier 2) ; **aucun poste du palier 1** | `nadia-prof` | rien d'engage |
| `Q-S2` (sofia-sm) | les postes 7 et 8 sont non engages a l'ouverture pour question ouverte, et peuvent glisser au palier 2 : sont-ils des reports au sens de `DEC-0112` section 6 et de `DEC-0120` 4.2 ? | rien | `camille-po` | **ce planning n'en compte aucun** (section "Compte des reports") |
| `Q-S3` (sofia-sm) | poste 1 sans story `S57-ARB` ; prix de l'audit d'`APM-R2` ; instance et moment de `RP-ACCUEIL-147` | rien d'engage dans le palier | `camille-po` (story), `nadia-prof` (prix, instance) | lignes ecrites comme dans la section precedente |

**Ce que le planning n'engage pas en attendant** : aucune correction d'`ART-CP-10`, aucune branche B1 ni B2, aucun lot `FRA-CE1-04`, aucun poste hors `PAR` non nomme ci-dessus. **Ce qu'il engage** : les postes 1, 3, 4, 5 et 6, les marges nommees, `RP-ACCUEIL-147` et l'action de `yanis-qa`.

## Charge des developpeurs

| Developpeur | Postes engages | Postes en reserve | Tours de s56 (`RAPPORT-tokens.md` de s56, colonne *Tours*, relue ce jour) |
|---|---|---|---|
| `marc-dev` | 3 (`VSJ-CE1-R1`) et 4 (`ce1-phrases-types`) | 7 (`ce1-texte-narratif`) | 183 |
| `sofia-sm` (casquette developpeuse) | 5 (`ce1-accord-groupe-nominal`) | - | 192 |
| `lea-dev` | 6 (`S54-OBS-SCI-CONSIGNE`) | 8 (correction d'`ART-CP-10`), 9 (B2) | 529 |

**Lecture : `lea-dev` ne devient pas un goulot, a ces conditions.**

- Elle a **2,9 fois les tours de `marc-dev`** a s56 (529 contre 183). Le nombre de tours n'est pas un temps passe, mais c'est la seule mesure de charge que le depot porte : **je ne lui affecte aucun lot de francais CE1.** Les trois lots vont a `marc-dev` et a moi.
- Sa chaine est `S54-OBS-SCI-CONSIGNE`, puis la correction (poste 8), puis B2 (poste 9) apres le verdict. Aujourd'hui elle ne porte que le premier, un fichier de contenu au perimetre disjoint des lots (avis, 6). **Ordre : le poste 6 d'abord.** Si le poste 8 s'engage avant que le poste 6 soit fusionne, c'est la correction qui devient le chemin critique, pas les lots : je reaffecte alors le poste 8 ou le poste 9 a `marc-dev` ou a moi par amendement date de ce fichier (capacite, donc a moi). **Le poste 6 ne se reaffecte pas** : `lea-dev` est le choix de l'utilisatrice (`Q2` de `sofia-sm`, `DEC-0120` A.38, forme 2).
- `marc-dev` : deux postes engages et un en reserve. Le poste 3 touche un fichier de contenu et son test, distincts des autres ; les postes 4 et 7 touchent `catalogue.yaml`. Il commence par le poste 4 : le poste 3 attend l'amendement de la propriete 8 de `nadia-prof` (section "Portes").
- **Ma propre charge** : un lot, plus les fusions, la cloture de palier et la review. Choix de ma main, de capacite, que l'utilisatrice peut inverser. **Le geste de casquette** : je declare "developpeuse" au debut de la tache du poste 5, je n'y arbitre pas mon propre obstacle (je le note, je change de casquette, je le route), mon commit est `[SOF]`. **Ma fusion de ma propre branche** : lignee par `outils/verifier-lignee.sh`, liste de fichiers contre `K2`, relue par `yanis-qa` dans `rapport-qualite.md` ; je n'ai pas d'autre juge pour la fusion du poste 5, et je le dis ici.

## Portes

Chaque porte depend d'un artefact que l'etape qu'elle precede ne produit pas (`squad/lessons.md`, s56).

| Porte | Condition | Artefact | Qui le produit |
|---|---|---|---|
| P-1, tout poste engage | ce planning est sur `round-two` (`K1 (a)`) | fusion de ce fichier | `sofia-sm` |
| P-2, postes 4 et 5 | `git merge-base --is-ancestor 729b0d8a HEAD` dans le worktree du lot sort 0 ; commande et sortie citees dans le rapport du developpeur (`K1 (c)`, `ADR-0065` A2). Verifie ici : sort 0 sur `771b55de` | amendement fusionne le 2026-10-13 17:42 | `theo-archi` (fait) |
| P-3, poste 3, **commit** | l'amendement date de la propriete 8 par `nadia-prof` existe sur le disque ; le test qui porte la propriete change dans le meme commit que les textes. Le worktree peut s'ouvrir avant, le commit des textes non | amendement de `nadia-prof` | `nadia-prof` (A) |
| P-4, poste 6, **premier commit** | amendement de `Q-Y3` s'il existe | amendement du backlog | `camille-po` |
| P-5, poste 7 | `Q-B1` et `Q-Y6` repondues ; amendement de `S57-BR-FRA-CE1-04` avant le worktree | amendement du backlog | `nadia-prof`, `camille-po` |
| P-6, poste 8 | `Q1`, `Q2`, `Q-Y5` repondues ; conditions `C1` a `C4` de `theo-archi` dans le rapport du developpeur ; forme proposee par `ines-design`, jugee par `nadia-prof` | amendements, proposition de forme | `nadia-prof`, `ines-design`, `theo-archi` |
| P-7, poste 9 | poste 8 fusionne (B1) ; `Q3` repondue | fusion de B1, reponse | `sofia-sm`, `camille-po` |

## Isolation : repertoire, port et worktree, par agent, instance et palier

Conditions de `theo-archi` (avis, 5.3), appliquees. **`I1`** : un repertoire de scratchpad par agent, par instance et par palier, a un seul niveau sous la racine, nomme `s57-p<N>-<agent>-<instance>`, jamais reutilise d'un palier a l'autre. **`I2`** : un port par agent, par instance et par palier, distinct de tout autre de la table. **`I3`** : a chaque lancement de jar, avant, le port est libre (`netstat -ano` filtre sur `":<port> "` ne rend rien) ; apres, l'agent lit dans **son** journal de demarrage la ligne *"Tomcat started on port <port>"* et le PID, avant toute mesure ; il arrete ce PID et lui seul, **jamais par nom d'image**. Chaque instance construit son jar dans son propre worktree. **Rien de cela n'est encodable dans le depot** (avis, 5.2) : la garde est celle de `nadia-prof` seule, qui lit les transcriptions a la cloture.

Le repertoire est choisi et cree par l'agent qui ouvre, la racine du scratchpad n'est ecrite nulle part. Les ports 18511 a 18543 ne sont ecrits dans aucun fichier suivi : `git grep -nE "185[0-9]{2}"` rend une seule ligne, un identifiant de session sans rapport avec un port, sur `771b55de`. Palier 1 :

| Agent | Instance | Postes | Repertoire de scratchpad | Port de jeu |
|---|---|---|---|---|
| `nadia-prof` | A | audits et rejeux de A | `s57-p1-nadia-prof-a` | 18511 |
| `nadia-prof` | A' | audits de A' | `s57-p1-nadia-prof-ap` | 18512 |
| `nadia-prof` | B | rejeu de `S54-OBS-SCI-CONSIGNE`, rejeu d'`ART-CP-10` | `s57-p1-nadia-prof-b` | 18513 |
| `marc-dev` | A | postes 3 et 7 | `s57-p1-marc-dev-a` | 18521 |
| `marc-dev` | A' | poste 4 | `s57-p1-marc-dev-ap` | 18522 |
| `sofia-sm` (developpeuse) | A' | poste 5 | `s57-p1-sofia-dev-ap` | 18523 |
| `lea-dev` | B | postes 6, 8, 9 | `s57-p1-lea-dev-b` | 18531 |
| `yanis-qa` | transverse | rapport qualite, mesures | `s57-p1-yanis-qa-t` | 18541 |
| `theo-archi` | transverse | mesures d'avis, confirmation | `s57-p1-theo-archi-t` | 18542 |
| `ines-design` | transverse | forme d'`ART-CP-10` | `s57-p1-ines-design-t` | 18543 |
| `camille-po`, `relecteur-pedagogique`, `sofia-sm` (Scrum Master) | transverse | aucun jar | `s57-p1-camille-po-t`, `s57-p1-relecteur-t`, `s57-p1-sofia-sm-t` | aucun |

**Un agent transverse n'a qu'un lancement vivant a la fois.** Un second lancement concurrent du meme agent recoit un repertoire `...-t2` et un port de la table avant son lancement, par ligne ajoutee a ce fichier.

**Un port que la table ne controle pas.** `pilote-q7.mjs` fixe le port de debogage de Chrome a **19222** (`squad/conformite/releves-s56/pilote-q7.mjs`, ligne 6, relu ce jour). Deux agents qui le lancent en meme temps se heurtent : **un seul agent a la fois le lance**, et sa ligne s'ecrit dans ce fichier avant l'usage.

**Branches, worktrees.** Une branche et un worktree dedies par poste, voisins du checkout, nommes `recreo-<branche>` ; les agents ne rebasent, ne poussent et ne fusionnent pas. Une branche neuve part du sommet de `round-two` fusionne ; chaque agent note `git log -1 --format=%h` dans son premier message ; **je verifie la lignee avant chaque fusion** (`bash outils/verifier-lignee.sh`), meme quand l'agent dit l'avoir faite.

| Poste | Branche | Worktree |
|---|---|---|
| 3 | `s57-marc-vsj-ce1-r1` | `../recreo-s57-marc-vsj-ce1-r1` |
| 4 | `s57-marc-fra-ce1-10` | `../recreo-s57-marc-fra-ce1-10` |
| 5 | `s57-sofia-fra-ce1-15` | `../recreo-s57-sofia-fra-ce1-15` |
| 6 | `s57-lea-sci-consigne` | `../recreo-s57-lea-sci-consigne` |
| 7 (si engage) | `s57-marc-fra-ce1-04` | `../recreo-s57-marc-fra-ce1-04` |
| 8 (si engage) | `s57-lea-art-cp-10-b1` | `../recreo-s57-lea-art-cp-10-b1` |
| 9 (si engage) | `s57-lea-art-cp-10-b2` | `../recreo-s57-lea-art-cp-10-b2` |
| audits de `nadia-prof` | `s57-nadia-a`, `s57-nadia-ap`, `s57-nadia-b` | `../recreo-s57-nadia-a`, `-ap`, `-b` |
| cloture de palier | `s57-sofia-cloture-p1` | `../recreo-s57-sofia-cloture-p1` |

## Branche de rejeu B2 d'`ART-CP-10` (`DEC-0120` 4.2, avis de `theo-archi`, section 3)

**Etat : non engagee.** Elle suit le poste 8 et `Q3`. Le moyen, tel que l'avis l'ecrit et que je l'inscris :

| Branche | Contenu | Fusion |
|---|---|---|
| B1, correction | les regles de la forme retenue sous `C1` a `C4` ; les fichiers de la voie de `RDO-CP-R2` ; leurs tests | sur `round-two` apres le job `build` vert, **avant** le rejeu. Sans entree, seul le jeu de preuve du garde-fou 4 l'exerce |
| B2, audit | issue de `round-two` apres B1 ; **un seul commit** : l'entree de `catalogue.yaml`, les quatre chaines de l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, `isEmpty()` remis en `containsExactly(<identifiant>)` dans le garde-fou 4 | **apres le verdict de `nadia-prof` seulement**. Non `REFUSE` : `sofia-sm` fusionne. `REFUSE` : B2 n'est pas fusionnee, rien n'est servi, une story corrective repart de B1 |

`nadia-prof`, instance B, ouvre son worktree sur la tete de B2, lance `bash outils/mvn-jdk21.sh -B clean verify` et joue le jar produit. Le jeu retire n'est pas servi par `round-two` : c'est ce que fait la decision de l'utilisatrice du 2026-10-11 (`DEC-0116` A.26), que je ne rouvre pas.

**A la fusion de B2, executees et citees par moi** : **`R1`** `git diff <commit joue> <resultat de la fusion> -- adapters/adapter-web/src/main/resources adapters/adapter-content/src/main/resources/contenu/cp-rapprocher-deux-oeuvres.yaml` est vide, ou ce qu'il montre est nomme a `nadia-prof` avant la fusion, et les six lignes de l'entree du paquet dans `catalogue.yaml` sont identiques des deux cotes (`catalogue.yaml` n'est pas compare en entier, les lots des autres postes y ajoutent leurs entrees) ; **`R2`** la fiche cite le commit joue et `git merge-base --is-ancestor <commit joue> <commit de fusion de B2>` sort 0 ; **`R3`** l'identifiant est celui de `camille-po` (`Q3`). Une consigne de B2 qui finit par `?` porte U+00A0 avant le signe (`ADR-0067`).

Cette ligne est neuve pour un sprint (l'audit avant fusion existait pour le chantier hors sprint, `squad/README.md` section 6, amendement du 2026-10-12) : un seul poste, cette ligne.

## Ordre de fusion

**Reacteur complet a chaque fusion** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`. **Tout compte de tests est celui du reacteur sur l'arbre fusionne**, que j'etablis moi-meme : un chiffre annonce par un agent mesure sa base plus son lot. Dernier total connu : 2498 sur `544159d9` (`yanis-qa` et `theo-archi`, deux mesures, `clean verify` complet), **non remesure sur `771b55de`**.

1. Ce planning ; l'amendement de `nadia-prof` de la propriete 8 (porte P-3) ; le cas echeant les amendements de `Q-Y3`.
2. Postes engages, **le premier pret est fusionne le premier**, un a la fois : 4, 5 et 6, puis 3. Les postes 7, 8, 9 s'inserent a leur engagement, dans l'ordre de leur livraison. B1 avant le rejeu, B2 apres le verdict.
3. Audits et rejeux de `nadia-prof`, au fil des livraisons. Cloture du palier.

**Fichiers que plusieurs postes touchent, et resolution (`Q-Y7`).** `catalogue.yaml` : une entree en fin de fichier par lot (postes 4, 5, 7) ; conflit mesure par `yanis-qa` (`git merge-tree --write-tree`, code 1) ; l'inventaire de `TexteAccentueExposeParLePortEntrantTest` ; `REGISTRE-identifiants.md`, ecrit par `nadia-prof` seule depuis le worktree de son instance.

- **Qui resout** : `sofia-sm`, a la fusion, dans son worktree de fusion. Les developpeurs ne fusionnent pas et ne rebasent pas. Le second poste pret est fusionne **apres** le premier, puis `clean verify` repasse en entier.
- **Comment** : on garde les ajouts des deux cotes, **jamais une assertion affaiblie**. L'inventaire est juge dans les deux sens (`touteChaineExposeeParLeCatalogueReelEstInventoriee`, `chaqueChaineDeLInventaireEstEncoreExposeeParLeCatalogueReel`) : une resolution qui perd une entree ou ses chaines rougit. Si deux tests se contredisent apres fusion, l'un des deux dit quelque chose de faux, et je trouve lequel.
- **Le cas non garde, nomme par `theo-archi`** (avis, 4) : une resolution qui perd a la fois une entree et ses chaines d'inventaire passe, car un paquet sans entree est un etat permis (`ADR-0061`). **Verification a chaque fusion, de ma main** : `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` apres la fusion vaut le compte d'avant plus le nombre d'entrees fusionnees (146 sur `771b55de`, mesure ce jour), et `grep -c "paquet: <nom>$"` rend 1 pour chaque paquet fusionne. **Une ligne par fusion dans `cloture-palier-N.md`** : SHA, compte d'avant, entrees fusionnees, compte d'apres. Un ecart arrete la fusion et va a `theo-archi`. Pas de garde-fou neuf (avis, 4 : aucune occurrence constatee, une exemption a tenir a jour a chaque lot).
- **Registre** : le compte est verifie par sa table (`ADR-0085` clause 5, `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, dans le job `build`). Si deux instances y ajoutent, les deux ajouts sont gardes.
- **Garde-fou de l'accent** : aucune instance ne le modifie (`K10`, `ADR-0065` A2). Un besoin se route a `theo-archi` ; le lot attend, celui de l'autre instance non.

## Compte des reports

| Ligne | Etat mesure | Position de ce planning |
|---|---|---|
| `S54-OBS-SCI-CONSIGNE` | **deux reports comptes** (s55, s56) ; un troisieme est interdit (`DEC-0112` section 6) ; placement complet (`DEC-0120` A.34, A.44) | un placement n'est pas un report. **Mon avis sur le glissement du palier 1 au palier 2** (`DEC-0120` A.59 : *"sauf avis contraire de `sofia-sm` au planning"*) : **je ne contredis pas la lecture de `camille-po` et de `nadia-prof`.** Un glissement entre deux paliers de s57 est un glissement de palier (11.3 condition 2), pas un report de sprint ; **le compte reste a deux** ; l'acte ecrit d'abandon se prend a la cloture de s57 si la story n'est ni livree ni rejouee. **Deux precisions de ma main** : (1) un glissement s'ecrit dans `cloture-palier-N.md` avec sa date et sa cause, et je le dis a l'utilisatrice le jour meme, sinon cette lecture ferait disparaitre le compte ; (2) la cloture de s57 n'a pas de date (D3), donc l'interdiction ne mord qu'a une cloture lointaine. **Je ne pose aucune limite au nombre de paliers de glissement : c'est un compte de report, donc a `camille-po`** (`Q-S2`). C'est pourquoi le poste est engage au palier 1 et premier poste de `lea-dev` |
| `ART-CP-10` | quatre reports (s50 a s53), trois engagements sans fermeture (s54, s55, s56) ; **la reprise elle-meme n'a jamais ete engagee : aucun report** (`DEC-0120` 4.2) | le poste 8 n'est pas engage a l'ouverture (`Q1`, `Q2`, `Q-Y5`). **Je ne compte pas de report** : `DEC-0120` 4.2 le definit par le correctif non fusionne *quand le rejeu devrait commencer*, ce qui n'est pas arrive. Un glissement a la cloture du palier 1 est ecrit comme tel. Si `camille-po` lit autrement : `Q-S2` |
| Lots de francais CE1 du palier 1 | le premier report de `DEC-0120` A.56 (lots sans story) est **solde** le jour meme (A.59) | le poste 7 non engage pour `Q-B1` n'est pas un lot engage puis non livre : **je ne compte pas de report**, `Q-S2` |

## Depassements de garde-fous consignes (`DEC-0116` A.36, repris de `preparation.md` 5.2)

Regle de l'utilisatrice : *"pas de bornes superieur c'est au besoin mais on la consigne"* (2026-10-13, `DEC-0116` A.32, forme 2). Elle ne porte ni sur `PAR-8` ni sur `PAR-9` (A.33). Une ligne par depassement, quatre champs. **Aucun depassement neuf de s57 a l'ouverture.**

| Date | Garde-fou | Estimation | Accepte par |
|---|---|---|---|
| 2026-10-11, `22b5f888` (11:56), fusionne dans `80c06e64` (12:17) | `B2` : texte alternatif des references | `theo-archi`, avis de s56, amendement du 2026-10-11 : *"0,25 j pour les quatre premiers"* (`B2`, `B3`, `B7`, `B8`) ; methode non ecrite, non ventilee | l'utilisatrice, *"Lance Q2 aussi meme si on depasse la borne"* (2026-10-11, `review.md` de s56, section 4) ; *"pas de bornes superieur c'est au besoin mais on la consigne"* (2026-10-13) |
| 2026-10-11, `5021ddf0` (11:58) | `B3` : references et paires | idem, dans le meme 0,25 j | idem |
| 2026-10-11, `2eeabb58` (12:04) | `B5` : format d'une oeuvre | `theo-archi` : *"0,25 j pour `B5`"* ; methode non ecrite | idem |
| 2026-10-11, `ca6c5e8a` (11:59) | `B7` : quatre a six items | dans le 0,25 j de `B2`, `B3`, `B7`, `B8` | idem |
| 2026-10-11, `fe150188` (12:01) | `B8` : enonce unique de dix mots | idem | idem |

Total : environ 0,5 j, estimation de `theo-archi`, **pas un tarif**. Reserves : les mots d'acceptation du 2026-10-11 sont relayes ; l'attribution aux cinq garde-fous est celle de l'agent lanceur ; le temps reellement passe n'est pas mesure.

## Cloture du palier 1

Le palier 1 est clos quand chaque poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit (condition 2 de 11.3 : un poste non audite glisse au palier suivant, ecrit comme tel). `sofia-sm` ecrit alors `cloture-palier-1.md` (amendement de `squad/README.md` section 4, fait dans le meme geste que ce planning) : horodatage de fin, verdict ou report de chaque poste, CI verte avec les jobs nommes sur l'arbre fusionne, total de tests recompte sur cet arbre, **une ligne par fusion avec le comptage du catalogue**, glissements, obstacles. Il n'est ni la review, ni la retro, ni `RAPPORT.md`.

## Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, avec ce fichier et l'amendement de `squad/README.md`, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 (le dernier : 21 cas) ; le sujet prevu du commit passe dans `verifier-prefixes.sh`, sortie 0. L'etape *"Verifier le proprietaire du garde-fou de l'accent"* sur la plage reelle apres commit : voir le compte rendu de la tache.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; la liste de `git ls-files` (moins le test du garde-fou) plus les fichiers non suivis, ce fichier compris, passe a `verifier-chemins.sh` : sort 0.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests (120, 31, 486, 1484, 371 et 6 d'integration), 0 echec, 0 erreur, 0 ignore ; JDK **21.0.10** (propriete `java.version` d'un rapport Surefire de `bootstrap`, 59 rapports). Le total est celui de ce reacteur sur ce worktree, qui ne change ni code ni contenu : il egale celui de `544159d9` par construction. L'etape *"Package du fat jar"* (`mvn -B -pl bootstrap -am package`) n'est pas lancee. Le build a lu ce fichier avant l'ecriture de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance ; aucun outil n'est modifie par ce commit.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier.** `DEC-0120` reste `Proposee` (`Q-S2`, `Q-S3`, et son passage a `Acceptee` sont a `camille-po`). Ce planning ecrit : la duree comme decidee (D1 a D5), les affectations nominatives du palier 1 et les developpeurs des postes 3 a 9, la marge de B nommee, la regle d'ouverture d'un palier, la table d'isolation `I1` a `I3`, la ligne de B2, l'ordre de fusion et le comptage du catalogue, la position sur le glissement de `S54-OBS-SCI-CONSIGNE`. Il ouvre s57 **sur la confirmation relayee de l'utilisatrice et non sur l'acceptation de `DEC-0120`**. Le seul autre fichier modifie dans ce commit est `squad/README.md`, section 4 (`Q-S1`). Les questions ouvertes ci-dessus sont les obstacles de `review.md` a l'ouverture, avec leur route.

---

## Amendement du 2026-10-14 18:20 (UTC+02:00), apres les decisions fusionnees depuis ce planning

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. Arbre : `round-two` a `6ca84317`, branche `s57-sofia-amend`, `bash outils/verifier-lignee.sh` sort 0. Sources : `DEC-0120` A.63 a A.78 (`camille-po`), `lecture-nadia-palier-1.md` (`nadia-prof`, `7161ab69`), annotations du backlog. **Cet amendement n'engage ni le poste 7 ni le poste 8** : `camille-po` (amendement des stories) et `ines-design` (forme des 125 px) sont en cours, et leurs portes P-5 et P-6 ne sont pas remplies.

### Statut de `DEC-0120`

**Remplace** *"`DEC-0120` n'est pas `Acceptee`"* (section "Sur quoi repose l'ouverture") : `grep -n "^- \*\*Statut"` sur `DEC-0120` rend, ligne 13, **`Acceptee le 2026-10-14 (amendement A.77)`**. Le passage est de `camille-po`, motif A.55 : la ligne de duree de ce planning (D1 a D5) est dans le depot. **Ce qui ne change pas** : l'ouverture a eu lieu sur la confirmation relayee de l'utilisatrice, avant le passage ; A.77 dit elle-meme qu'elle repose sur les memes mots en forme 2, non lus de sa main, et pas sur A.63 a A.76.

### Compte des reports : remplace la section du meme nom

`Q-S2` est repondue en premiere partie par `DEC-0120` A.66 et A.67. **Je confirme A.72 comme regle de ce planning** : elle est ecrite a ma place (*"`sofia-sm`, `planning.md`"*), elle porte sur les paliers et la duree, qui sont mon poste ; elle remplace ma phrase *"je ne pose aucune limite au nombre de paliers de glissement : c'est un compte de report, donc a `camille-po`"*. Deux regles, appliquees ici :

- **Un report** est un poste retenu par la composition d'un palier et non engage a l'ouverture de ce palier, quelle qu'en soit la cause (A.66). Il est compte a l'ouverture ; un engagement tardif ne l'efface pas, il evite le suivant.
- **Un glissement** est un poste engage au palier N et non audite a sa cloture (D2, condition 2 de 11.3). **Un poste glisse au plus une fois.** Le second glissement est un report compte, ecrit par moi le jour de la cloture ; **le troisieme report est interdit** (A.67, A.72). Aucune limite n'est posee au nombre de paliers de s57.

| Poste | Compte a l'ouverture du palier 1, 2026-10-14 | Ce qui debloque |
|---|---|---|
| 7, `ce1-texte-narratif` (`FRA-CE1-04`) | **premier report de ce poste** (A.67). Remplace *"je ne compte pas de report"* | `Q-B1` est repondue (`lecture-nadia-palier-1.md`, D1 et D2 : correction avant le branchement, cible 0) ; reste l'amendement de `S57-BR-FRA-CE1-04` par `camille-po` |
| 8, correction d'`ART-CP-10` et rejeu | **premier report de la reprise elle-meme** (A.67). Remplace *"je ne compte pas de report"* | `Q1`, `Q2`, `Q-Y5` sont repondues (D5 a D7, voie (b), 125 px a 744) ; reste la forme d'`ines-design` et l'amendement de `S56-RDO-CP-R1` et `-R2` par `camille-po` |
| 9, branche B2 | **pas un report** : elle suit le poste 8 et le verdict, jamais retenue seule (A.67). `Q3` est repondue (A.65) : l'entree reprend l'identifiant `bandit-manchot-rapprocher-deux-oeuvres-cp` | le poste 8 |

**Compte de la ligne `ART-CP-10`** : quatre reports (s50 a s53), trois engagements sans fermeture (s54, s55, s56), plus ce report : **huit evenements, aucune fermeture** (A.67). A lire par l'utilisatrice, comme A.75 le dit.

**`S54-OBS-SCI-CONSIGNE`** : deux reports comptes (s55, s56), le placement n'en est pas un, le poste est engage et livre sur branche. **Remplace** *"l'acte ecrit d'abandon se prend a la cloture de s57"* (section "Compte des reports", premiere ligne) par A.73 : un glissement du palier 1 au palier 2 est permis une fois ; **a la cloture du palier 2, si la story n'est pas livree et rejouee par `nadia-prof`, c'est le troisieme report, interdit, et l'acte d'abandon s'ecrit ce jour-la**. Si s57 se cloture avant qu'un palier 2 existe, l'acte se prend a cette cloture.

**Lots de francais CE1** : le premier report de `DEC-0120` A.56 reste solde (A.59). Le poste 7 est un report neuf, ligne ci-dessus. **Au palier 2, le poste 7 s'il n'est toujours pas engage a l'ouverture est le second report, puis le troisieme est interdit** : je l'ecrirai ce jour-la.

### Engagements, etat au 2026-10-14 18:20

Les faits sont lus sur les branches (`git log`, `git diff --stat round-two...<branche>`), pas sur les rapports des agents. **Livre veut dire commit sur une branche non fusionnee** : aucun Done, `nadia-prof` joue les branches.

| # | Poste | Etat | Faits |
|---|---|---|---|
| 1 | Arbitrage prealable | **engage, story `S57-ARB-PALIER-1`** | titre au backlog : `grep -c "^#\{3,4\} .*S57-ARB-PALIER-1" docs/backlog/backlog.md` rend 1 (A.64). Part ecrite livree. **Criteres 2 et 3 non constates verts** : aucune ligne datee *"lecture complete"* avec le nom du paquet et le nombre d'items lus n'est dans `lecture-nadia-palier-1.md` (`grep -rnE "lecture complete"` sur ce fichier et sur `squad/conformite` : une phrase de l'en-tete qui cite l'arbitrage, aucune ligne de paquet). La forme de la ligne est a `nadia-prof`. Ordre du critere 3 verifie par moi a la cloture, une ligne par paquet |
| 3 | `VSJ-CE1-R1` | **engage ; `marc-dev` l'a repris** | worktree et branche `s57-marc-vsj-ce1-r1` sur `6ca84317`, **aucun commit** (`git log round-two..s57-marc-vsj-ce1-r1` vide). Porte P-3 **remplie** : l'amendement date de la propriete 8 est sur `round-two` (`grep -c "Amendement du 2026-10-14"` sur `ARBITRAGE-PREALABLE-s56-instance-a-francais-ce1.md` rend 1). A.69 : `J1` et les deux tests nommes restent verts sans modification ; sinon `marc-dev` s'arrete et route |
| 4 | `ce1-phrases-types` (A') | **livre**, branche `s57-fra-ce1-10`, commit `1f6642ab` (16:39:44), non fusionne | 5 fichiers, 624 insertions : `catalogue.yaml`, `PaquetCe1PhrasesTypesTest`, son dossier de fixtures, `TexteAccentueExposeParLePortEntrantTest`. Le YAML du paquet n'est pas touche. Porte P-2 : `git merge-base --is-ancestor 729b0d8a 1f6642ab` sort 0. Base `3a3ea97e` |
| 5 | `ce1-accord-groupe-nominal` (A') | **engage**, ma tache de developpeuse, separee de celle-ci | non constate ici |
| 6 | `S54-OBS-SCI-CONSIGNE` (B) | **livre**, branche `s57-sci-consigne`, commit `c3875936` (16:29:52), non fusionne | 2 fichiers, 31 insertions, 12 suppressions : le paquet et `PaquetCm1SciencesExtraireInformationTest`. Base `3a3ea97e`. **Porte P-4 non remplie par la lignee** : les amendements de `Q-Y3` (A.70) sont dans `829db5eb` (16:28:55), fusionnes dans `72719521` a 16:32:10, apres ce commit ; `git merge-base --is-ancestor 829db5eb s57-sci-consigne` sort 1. Le message du commit cite le test du critere 1 et le critere 3, pas la commande de paires du critere 2 amende. **Je ne juge pas le contenu** : `lea-dev` cite la commande de paires de A.70 et sa sortie avant la fusion, et `yanis-qa` la relit |
| 7 | `ce1-texte-narratif` | **non engage**, report compte | tableau ci-dessus |
| 8 | Correction d'`ART-CP-10` et rejeu | **non engage**, report compte | tableau ci-dessus |
| 9 | Branche B2 | **non engage** | tableau ci-dessus |

**Lignee des deux branches livrees.** Elles partent de `3a3ea97e`, avant les fusions de `camille-po` et de `nadia-prof` : `git merge-base --is-ancestor round-two <branche>` sort 1 pour les deux, ce qui est attendu quand `round-two` a avance, et `bash outils/verifier-lignee.sh` reste le juge. **Je le relance avant chaque fusion, branche par branche** ; la fusion de `round-two` dans une branche d'agent n'est pas demandee.

**Hors capacite.** `RP-ACCUEIL-147` : echeance fixee par A.74, **faite avant la cloture du palier 1, sinon un glissement ecrit dans `cloture-palier-1.md`**. `HS-ACCUEIL-APM-R2` : non engagee, son audit ne se prend ni sur les marges ni sur les 0,75 non affectes de B (A.74) ; le prix est `Q-N2` de `nadia-prof`.

### Noms de branche et de worktree : alignes sur les branches reelles

**Remplace** les lignes 4 et 6 du tableau "Branches, worktrees" : poste 4 `s57-fra-ce1-10`, worktree `../recreo-s57-fra-ce1-10` ; poste 6 `s57-sci-consigne`, worktree `../recreo-s57-sci-consigne`. Je n'ai pas renomme ces branches : elles portent des commits d'autres agents et un renommage est une mutation git sans effet sur le contenu. Les autres lignes (3, 5, 7, 8, 9) gardent le nom du planning ; le poste 3 est deja `s57-marc-vsj-ce1-r1`. Les repertoires de scratchpad et les ports du tableau d'isolation ne changent pas.

### Ce que cet amendement ne fait pas

N'engage ni le poste 7 ni le poste 8, ne passe aucun poste a `Done`, ne juge aucun contenu, ne fusionne rien, ne repond pas a `Q-N2`. **`cloture-palier-1.md` est ecrit en squelette** (voir ce fichier), sans horodatage de fin ni verdict.

### Jobs de `ci.yml` lances pour ce commit

- **`prefixes`** : trois tests (`verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh`, 21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh`, sortie 0.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; fichiers suivis et non suivis passes a `verifier-chemins.sh`, sortie 0.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2498 tests (120, 31, 486, 1484, 371 et 6 d'integration), 0 echec ; ce commit ne change ni code ni contenu. Le build a lu ce fichier avant l'ecriture de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance, aucun outil modifie. La CI distante n'est pas lue.

### Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.** Cet amendement applique `DEC-0120` A.66 a A.73, confirme A.72 comme regle de ce planning, aligne deux noms de branche, constate l'etat des postes 1 a 9 et la porte P-4 non remplie par la lignee de `s57-sci-consigne`.

---

## Amendement du 2026-10-14 21:48 (UTC+02:00), engagement du poste 7 et etat apres les fusions

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. Arbre : `round-two` a `f95736b5` (avance rapide sur `s57-sofia-fusion`), branche `s57-sofia-engage7`, `bash outils/verifier-lignee.sh` sort 0. Sources : `DEC-0120` A.79 a A.86, annotation du backlog *"poste 7 : amendement qui precede le worktree"*, `lecture-nadia-palier-1.md`, `rejeu-nadia-palier-1.md`, fiches de `nadia-prof`. **Le poste 8 n'est pas touche** : il attend `theo-archi` (`C1` a `C4`) et la seconde moitie de `Q-Y4` ; il reste non engage, report compte, et le poste 9 avec lui.

### Etat des postes 3 a 6 : remplace les lignes 3 a 6 du tableau "Engagements, etat au 2026-10-14 18:20"

Les faits sont lus dans le depot, pas dans les rapports des agents. Verdicts de `nadia-prof`, qui seule les prononce.

| # | Poste | Fusion sur `round-two` | Verdict de `nadia-prof` | Source |
|---|---|---|---|---|
| 3 | `VSJ-CE1-R1` | `65d00d8d` (branche `7dc40fb0`) | **`VSJ-CE1-R1` levee** ; `FRA-CE1-13` **VALIDE SOUS RESERVE**, reserve neuve non bloquante `VSJ-CE1-R2` | `rejeu-nadia-palier-1.md`, resume ; `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| 4 | `ce1-phrases-types` | `c935d764` (branche `1f6642ab`) | **VALIDE SOUS RESERVE**, reserve non bloquante `TPH-CE1-R1`. `Q-N4` = non (`7d162b3c`) : le pli ne devient pas bloquant | `FICHE-bandit-manchot-phrases-types-ce1.md` |
| 5 | `ce1-accord-groupe-nominal` | `d67204de` (branche `3459ddb8`, jar joue sur `72931c94`) | **VALIDE SOUS RESERVE**, reserve non bloquante `GNA-CE1-R1` (`0688b581`) | `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` |
| 6 | `S54-OBS-SCI-CONSIGNE` | `14d6cf47` (branche `ac32f976`, jouee a ce commit) | **VALIDE**, sans reserve ; `SCI-CM1-20` non rouverte | `rejeu-nadia-palier-1.md`, resume |

- **Porte P-4, close** : `git merge-base --is-ancestor 829db5eb ac32f976` sort 0 ; `lea-dev` a fusionne `round-two` dans sa branche avant le verdict, ce qui ferme l'ecart que l'amendement du 18:20 avait note.
- **Les verdicts du tableau sont ceux de `nadia-prof`, seule autorite sur le Done pedagogique** ; ce planning n'en prononce aucun. **Les statuts de matrice sont a `camille-po`** : ce planning n'en ecrit aucun.
- Comptage du catalogue et des tests de chaque fusion : `cloture-palier-1.md`, section 4.

### Poste 7 engage : `S57-BR-FRA-CE1-04`, `ce1-texte-narratif`, `FRA-CE1-04`

**Porte `P-5`, remplie**, chaque element par sa commande, lue sur `round-two` a `f95736b5` :

| Element de `P-5` | Commande | Sortie |
|---|---|---|
| `Q-B1` et `Q-Y6` repondues | `lecture-nadia-palier-1.md` (`7161ab69`) : `git merge-base --is-ancestor 7161ab69 HEAD` | 0 ; `D1` et `D2` (correction avant le branchement, cible 0), `D3` (regle de s47) |
| amendement de `S57-BR-FRA-CE1-04` par `camille-po` | `git log -S"poste 7 : amendement qui precede le worktree" -- docs/backlog/backlog.md` rend `6bbef9dd` ; `git merge-base --is-ancestor 6bbef9dd HEAD` | 0 ; `grep -c "poste 7 : amendement qui precede le worktree" docs/backlog/backlog.md` rend 1 |
| story au backlog (`PAR-16`) | `grep -c "^#\{3,4\} .*S57-BR-FRA-CE1-04" docs/backlog/backlog.md` | 3 titres : la story et deux annotations (precisions de `Q-Y6`, amendement de `P-5`) |
| ce planning est sur `round-two` (`K1 (a)`) | `git merge-base --is-ancestor 3a3ea97e HEAD` (fusion de `b7a64ab4`) | 0 |

**Ligne engagee** (verification poste par poste, `PAR-16`) :

| Champ | Valeur |
|---|---|
| Poste | 7, lot `ce1-texte-narratif` |
| Story, titre au backlog | `S57-BR-FRA-CE1-04`, criteres `K1` a `K10` et `S1` a `S6` tels que l'amendement les remplace |
| Competence visee | `FRA-CE1-04`, *"Comprendre un texte narratif court lu seul : personnages, lieu, moment, ordre chronologique des evenements."* |
| Instance | A (groupe X), audit par `nadia-prof` (A) |
| Developpeur | **`marc-dev`**, nominatif |
| Prix | **1,0**, dans A |
| Branche, worktree | `s57-marc-fra-ce1-04`, `../recreo-s57-marc-fra-ce1-04` : nom du planning, inchange, ouvert par `marc-dev` apres la fusion de cet amendement sur `round-two` |
| Scratchpad, port (`I1` a `I3`) | `marc-dev` : `s57-p1-marc-dev-a`, port 18521 ; `nadia-prof` (A) : `s57-p1-nadia-prof-a`, port 18511. Le poste 3 a utilise ce repertoire et ce port, sa branche est fusionnee et aucun jar n'est suppose vivant : `I3` s'applique a chaque lancement (port libre avant, ligne *"Tomcat started on port"* et PID lus dans son propre journal, arret par PID seul) |
| Etat | **engage** |

- **Ce que le developpeur applique** : la correction avant le branchement (`D1`, `D2`) : le YAML du paquet entre dans la liste de `K2`, **les mots que l'indice neuf dit sont a lui, `nadia-prof` les juge en jouant** ; la regle de comptage de `LON-CE1-3` et `-4` est celle de s47 ; son premier message note `git log -1 --format=%h` ; je verifie la lignee avant la fusion.
- **Marge de A** : 0,5, nommee, allouee a aucun poste, **intacte** (`rejeu-nadia-palier-1.md`, section 11 : marges de A, A' et B intactes).
- **Capacite recomptee** par `awk` sur les prix, sortie lue ce jour : A 2,5 (arbitrage 0,5, marge 0,5, `VSJ-CE1-R1` 0,5, lot 1,0), A' 3,0, B 2,5 ; **engage 6,75**, non engage 0,5 (poste 8), non affecte 0,75, total 8,0. Plafond de 5,0 non approche ; A porte un lot, A' deux.
- **Report** : le premier report du poste 7 (`DEC-0120` A.67) **reste ecrit** : A.83, *"l'engagement tardif n'efface pas le report, il evite le suivant"*. Le poste, une fois engage et non audite a la cloture du palier 1, glisse au plus une fois (A.72).
- **Ce qui reste a nommer** : rien. Le Done pedagogique de `FRA-CE1-04` est a `nadia-prof`, apres jeu.

### Charge des developpeurs : remplace le tableau de la section du meme nom

| Developpeur | Postes livres et fusionnes | Postes engages en cours | Reserve |
|---|---|---|---|
| `marc-dev` | 3 (`65d00d8d`), 4 (`c935d764`) | 7 | - |
| `sofia-sm` (casquette developpeuse) | 5 (`d67204de`) | - | - |
| `lea-dev` | 6 (`14d6cf47`) | - | 8, 9 |

**Lecture.** Le seul travail de developpement engage et ouvert est le poste 7, a `marc-dev`, qui l'a devant lui avec ses deux autres postes livres : un worktree a la fois, pas de chevauchement. `lea-dev` n'a plus de poste engage ; le poste 8 s'engagera sur une reponse de `theo-archi`, pas sur sa capacite, et le poste 9 apres le verdict du poste 8. **Aucun goulot a l'ouverture de ce poste.** La charge en tours n'est pas remesuree ici (celle de s56, 183 pour `marc-dev`, 529 pour `lea-dev`, est un repere de ce planning, pas de l'etat d'aujourd'hui). **Si le poste 7 est le chemin critique a la cloture, je le dis a l'utilisatrice, je ne le reaffecte pas** : `marc-dev` est celui que `DEC-0120` A.80 et le backlog nomment.

### Ce qui reste ouvert, poste 8 compris, a cet instant

Postes 8 et 9 non engages (A.84 : `C1` a `C4`, seconde moitie de `Q-Y4`, amendement de `P-6`). `S57-ARB-PALIER-1`, `RP-ACCUEIL-147` (non fait : `grep -n "147" squad/conformite/ARBITRAGE-PREALABLE-accueil-par-matiere.md` rend encore les lignes 41 et 236) et la cloture : `cloture-palier-1.md`.

### Jobs de `ci.yml` lances pour ce commit

- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; le sujet du commit passe dans `verifier-prefixes.sh`, sortie 0.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 2548 tests (120, 31, 486, 1534, 371 et 6 d'integration), 0 echec ; le commit ne change ni code ni contenu, ce total egale celui de la derniere fusion. Le build a lu ces fichiers avant l'ecriture de cette section, qui ne contient aucun tableau.
- **`outils`** : non lance, aucun outil modifie. La CI distante n'est pas lue.

### Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.** Cet amendement engage le poste 7 sous la porte `P-5`, nomme `marc-dev`, constate les fusions et verdicts des postes 3 a 6 et la fermeture de la porte `P-4`. Le poste 8 n'est ni engage ni modifie.

---

## Amendement du 2026-10-15 20:56 (UTC+02:00), ouverture du palier 2

Annotation datee, heure lue par `date`, **inscrite avant l'ouverture de tout worktree du palier 2** (`D2` (c), precedent s56). **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace est nomme. Arbre : `round-two` a `0cdccb62`, branche `s57-sofia-planning2`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture. Sources, par auteur : `sofia-sm` (`cloture-palier-1.md`, `a8ff6fc6`, section 9) ; `nadia-prof` (`arbitrage-nadia-palier-2.md`, `b4fe7eb4`) ; `camille-po` (`composition-palier-2.md`, `8de576d0`, annotation du backlog du 2026-10-15 20:40, `DEC-0120` A.66 a A.73 et A.91 a A.93) ; `duree-et-paliers.md`. **Cet amendement ouvre le palier 2. Il n'ouvre aucun worktree** : `K1 (a)` des stories de branchement demande qu'il soit fusionne sur `round-two`, et je ne le fusionne pas.

### 1. Regle d'ouverture (`D2`), condition par condition

| Condition de `D2` | Preuve lue sur l'arbre | Etat |
|---|---|---|
| (a) `cloture-palier-1.md` sur `round-two` | `git show HEAD:squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cloture-palier-1.md` sort 0 | tenue |
| (b) arbitrage prealable de `nadia-prof`, trois instances | meme commande sur `arbitrage-nadia-palier-2.md` : sort 0 ; titre *"instances A, A' et B"* ; il porte la lecture de l'isolation (section 3) et le critere 3 de `ce1-texte-narratif` (section 4), que ma cloture lui demandait | tenue |
| (c) composition de `camille-po`, avec la reference de l'arbitrage, heure avant les worktrees | `composition-palier-2.md` : sort 0 ; reference `b4fe7eb4` ; heure de ce titre | tenue par cet amendement (sections 2 a 4) |
| (d) table d'isolation du palier, repertoires et ports neufs | section 6 | tenue par cet amendement |

Sur le meme arbre : `grep -n "^- \*\*Statut"` sur `DEC-0120` rend, ligne 13, *"Acceptee le 2026-10-14 (amendement A.77)"* ; `git merge-base --is-ancestor 729b0d8a HEAD` sort 0 (`K1 (c)` de `-24`, instance A').

**Ce que `D2` ne demande pas et qui reste ouvert, nomme pour qu'aucun ne passe pour clos** :

- **`E3`** (lecture du diff de B1 par `theo-archi`) : aucune trace ecrite : `grep -rln "lecture du diff"` sur le dossier du sprint rend quatre fichiers (`avis-faisabilite.md`, `avis-theo-poste-8-art-cp-10.md`, `cloture-palier-1.md`, `engagement-poste-8.md`) qui la posent ou la constatent absente, et `git log --since=2026-10-15T17:30` sur `squad` et `docs` ne rend aucun commit de `theo-archi`. Ma cloture, section 7, l'ecrit comme condition de l'ouverture ; `D2` (a) a (d) ne la contient pas et la section 9 ne la liste pas. **Je ne l'applique pas comme porte du palier** : `-05`, `-24` et `S57-OBS-SCI-N1-N2` ne touchent aucun fichier de B1. **Je l'applique comme porte des deux postes qui reprennent le code de B1** : la forme commune (feuille et gabarit de B1) et `RDO-CP-R3` (ligne 215 de `manche.html`, garde-fou de `ADR-0067` D.4). Leur engagement attend la trace de `theo-archi`. Que la porte couvre tout le palier est a l'utilisatrice : `Q-S7`.
- **`E1`** : aucun rapport de `lea-dev` ni de `marc-dev` pour le palier 1. Reprise par `K7` des stories de branchement (nom `rapport-<developpeur>-palier-2.md`) ; chaque developpeur du palier 2 ecrit le sien.
- **Mots de l'utilisatrice** (`Q-N4`, `S2`, et provenance `S1` a `S4`, `P5` de la cloture) : toujours absents du depot. Je n'inscris aucune correction de provenance dans cet amendement, faute de mots a inscrire.

### 2. Composition portee (`camille-po`), prix de `nadia-prof`

Reprise de `composition-palier-2.md` **sans modifier un prix ni un lot**. Chaque prix est le maximum du registre de `nadia-prof` ; l'ecart a 8,0 reste non affecte. Somme recomptee par `awk` sur les prix de la section 3, sortie lue ce jour : engage 3,75 ; marges 1,50 ; non engage 2,50 ; non affecte 0,25 ; total 8,00.

| Instance | Total | Engage a l'ouverture | Marge, allouee a aucun poste | Non engage, retenu par la composition | Lots |
|---|---|---|---|---|---|
| A (groupe X) | 3,0 | 1,5 (arbitrage 0,5, lot `-05` 1,0) | 0,5 | 1,0 (`RCT-CE1-R1` 0,5, `VSJ-CE1-R2` 0,5) | 1 |
| A' (groupe Y) | 3,0 | 1,5 (arbitrage 0,5, lot `-24` 1,0) | 0,5 | 1,0 (`TPH-CE1-R1` 0,5, `GNA-CE1-R1` 0,5) | 1 |
| B | 1,75 | 0,75 (arbitrage 0,5, `S57-OBS-SCI-N1-N2` 0,25) | 0,5 | 0,5 (`RDO-CP-R3`) | 0 |
| hors instance | 0,25 | - | - | non affecte, aucun poste | - |

Bornes relues : somme 8,0 (`PAR-9`) ; plafond de 5,0 par instance, aucune ne s'en approche (`PAR-8`) ; trois lots au plus par instance, A et A' en portent un ; aucun chiffre au-dela de 8,0 ; aucune marge du palier 1 entamee.

### 3. Postes du palier 2, verification poste par poste (`PAR-16`)

Numerotation propre au palier 2, sans lien avec celle du palier 1. Titres lus par `grep -c "^#\{3,4\} .*<story>" docs/backlog/backlog.md` sur `0cdccb62` ; le compte compte aussi les annotations qui portent le meme titre.

| # | Poste | Story, titres au backlog | Inst. | Agent nominatif | Competence visee | Prix | Etat |
|---|---|---|---|---|---|---|---|
| p2-1 | Arbitrage et lecture complete | `S57-ARB-PALIER-2` (1) | A, A', B | `nadia-prof` | transverse | 0,5 par instance | **engage** : part ecrite livree (`b4fe7eb4`). Reste la ligne datee *"lecture complete"* de `ce1-pronoms` (20 items) et de `ce1-familles-de-mots` (24 items), avant le premier verdict de chacun ; ordre verifie par moi a la cloture |
| p2-2 | Marges de rejeu | aucune | A, A', B | aucun | - | 0,5 par instance | nommees, allouees a aucun poste |
| p2-3 | Lot `ce1-pronoms` | `S57-BR-FRA-CE1-05` (2) | A | `marc-dev` ; audit `nadia-prof` (A) | `FRA-CE1-05` | 1,0 | **engage** |
| p2-4 | Lot `ce1-familles-de-mots`, correction de l'item *livre* comprise | `S57-BR-FRA-CE1-24` (2), `S57-FRA-FAMILLES-LIVRE` (2) | A' | `sofia-sm`, casquette developpeuse ; audit `nadia-prof` (A') | `FRA-CE1-24` | 1,0 | **engage** |
| p2-5 | Recommandations `N1` et `N2` | `S57-OBS-SCI-N1-N2` (1) | B | `marc-dev` ; rejeu `nadia-prof` (B) | `SCI-CM1-20` | 0,25 | **engage** : l'acte de `sofia-sm` que la story demande est cet amendement |
| p2-6 | Script des 43 enonces d'`additions-cp` (`PA1`) | aucun titre (`CR14` du backlog est une ligne de routage) | transverse | `marc-dev` ; mesure `ines-design` | transverse | 0, hors `PAR` | **engage**, sans story : `Q-S5` |
| p2-7 | Forme commune des propositions longues | `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` (1) | A' (jar rejoue par `nadia-prof` A') | `lea-dev` | transverse | 0, hors `PAR` | **non engage**, report compte (section 8) : `PA3`, `PA4`, `PA5`, `E3` |
| p2-8 | `TPH-CE1-R1` | `S57-TPH-CE1-R1` (2) | A' | developpeur de p2-7 ; rejeu `nadia-prof` (A') | `FRA-CE1-10` | 0,5 | **non engage**, report compte |
| p2-9 | `GNA-CE1-R1` | `S57-GNA-CE1-R1` (1) | A' | developpeur de p2-7 ; rejeu `nadia-prof` (A') | `FRA-CE1-15` | 0,5 | **non engage**, report compte ; jamais avant p2-8 |
| p2-10 | `RCT-CE1-R1` | `S57-RCT-CE1-R1` (2) | A | `marc-dev` si des textes changent, sinon developpeur de p2-7 ; rejeu `nadia-prof` (A) | `FRA-CE1-04` | 0,5 | **non engage**, report compte : `PA7` |
| p2-11 | `VSJ-CE1-R2` | `S57-VSJ-CE1-R2` (1) | A | `marc-dev` si les textes, aucun si la forme ; rejeu `nadia-prof` (A) | `FRA-CE1-13` | 0,5 | **non engage**, report compte : voie non ecrite, `Q-S9` |
| p2-12 | `RDO-CP-R3` | `S57-RDO-CP-R3` (1) | B | `lea-dev`, sous la regle de la section 5 ; rejeu `nadia-prof` (B) | `ART-CP-10` | 0,5 | **non engage**, report compte : `PA8`, `E3` |

**Hors `PAR`, non engages, aucun prix, aucun report compte** (la composition ne les retient pas, section 9 de `composition-palier-2.md`) : `S57-MESSAGE-PARTIE-PERDUE` (1), `S57-TYP1-TEXTES-DE-GABARIT` (2), `S57-GARDE-NOMBRE-DE-MANCHES` (1), `D5`. **Je ne designe aucun developpeur pour eux a l'ouverture** : aucun ne part avant son prealable (mesure des lignes a 744 et 1200, `Q-T12` de `theo-archi`, liste de depart de `DEC-0121`), et en nommer un maintenant chargerait une des deux chaines de la section 5. Je le fais a leur engagement. **`D5` ne s'engage pas avant la fusion de la forme commune** : il modifie l'en-tete de paquets que la forme mesure (`ce1-phrases-types`, `ce1-accord-groupe-nominal`, `ce1-verbe-sujet`) et celui de `ce1-familles-de-mots` que `-24` modifie, et le critere 4 de la forme exige que `git diff --stat <base> <commit livre>` ne nomme aucun paquet.

**Actions des autres agents, sans budget** (`DEC-0116` section 9) : `yanis-qa`, `rapport-qualite.md` sur l'arbre fusionne a la cloture du sprint ; `theo-archi`, confirmation ArchUnit a la review ; `relecteur-pedagogique`, `relecture-cloture.md` a la review, et l'orthotypographie des chevrons de `S57-OBS-SCI-N1-N2` avant son rejeu ; `camille-po`, fiche `FICHE-bandit-manchot-<id>.md` apres chaque commit de branchement (`K9`), matrice au verdict seulement.

### 4. Ce qui peut commencer, ce qui est bloque

**Peut commencer des que cet amendement est fusionne, sans prealable** (la porte est `K1 (a)`) :

- p2-1 : `nadia-prof` lit les items ; ce poste n'attend pas la fusion.
- p2-6 : l'ecriture du script par `marc-dev`. Il ne depend ni de la base ni du jar : il recoit son port en argument, comme `pilote-r3.mjs`.
- p2-5 : `S57-OBS-SCI-N1-N2` (`marc-dev`). Les textes sont ceux de `nadia-prof`, copies sans reformulation ; aucun prealable hors `K1`.
- p2-3 et p2-4 : le worktree s'ouvre, les fixtures, le test du paquet et l'entree de catalogue s'ecrivent (`K3`, `K4`). **Ce qui attend, et c'est la porte du commit, pas du worktree (precedent `P-3` du palier 1)** : pour `-05`, la reponse de `nadia-prof` a `Q-P6` (l'item 15, bonne reponse *"Elias"*, seule proposition a majuscule : `PN4` est rouge sur l'existant, un test vert est impossible avant sa decision) ; pour `-24`, la forme de la correction de l'item *livre*, qui est a `nadia-prof` et au developpeur (`S57-FRA-FAMILLES-LIVRE` : `grep -c 'bonneReponse: "librairie"'` doit rendre 0) : `Q-S8`. **Une lecture de la lettre de `PN8` et `FM5`** (*"avant l'ouverture du worktree"*) demanderait la lecture complete de `nadia-prof` avant le worktree ; je ne la tranche pas, c'est une story de `camille-po` : `Q-S4`. En attendant, **aucun developpeur ne touche le YAML d'un paquet** (`K2`), ce qui garde les deux lectures ouvertes.
- Les marges : rien a faire, aucun poste.
- Les reponses attendues, qui n'attendent pas la fusion : `PA3`, `PA4`, `PA5`, `PA7`, `PA8`, `Q-P4`, `Q-P6`, `Q-S8`, `Q-S9`, `E3` (section 7).

**Bloque, avec ce qui le debloque** :

- p2-7, la forme : `PA3`, `PA4` (`nadia-prof`), `PA5` (`theo-archi`), `E3` (`theo-archi`). Engagee par un amendement date, avec la base de la cote (section 9).
- p2-8 et p2-9 : la forme fusionnee, `PA1` et `PA2` ecrits en entier. Aucun rejeu de la forme avant (arbitrage, section 8, point 4).
- p2-10 : `PA7` et la forme.
- p2-11 : la voie (`Q-S9`) ; par la forme, comme p2-8.
- p2-12 : `PA8` (`Q-R7`), `E3`, et la regle de la section 5.
- Les quatre stories a prix 0 et `D5` : section 3.

### 5. Affectations nominatives des developpeurs

Proposition de `camille-po` (`composition-palier-2.md` sections 3 et 4), confirmee ou ajustee. **Ce choix est de capacite, donc a moi ; l'utilisatrice peut l'inverser.**

| Developpeur | Postes engages, dans cet ordre | Nommes, non engages | Tours de s56 (repris de la section *Charge des developpeurs* ci-dessus, colonne *Tours* de `RAPPORT-tokens.md` de s56) |
|---|---|---|---|
| `marc-dev` | p2-6 (script), p2-5 (`N1`, `N2`), p2-3 (`-05`) | p2-10 et p2-11 si la voie est celle des textes | 183 |
| `sofia-sm` (casquette developpeuse) | p2-4 (`-24`) | - | 192 |
| `lea-dev` | aucun a l'ouverture | p2-7 (forme), puis p2-12 (`RDO-CP-R3`) | 529 |

- **Confirme : `marc-dev` pour `-05`** (la forme de `PaquetCe1TexteNarratifTest` et la regle de s47 de `LON-CE1-3` et `-4`, que `-05` reprend) **et `sofia-sm` pour `-24`** (deux developpeurs distincts sur deux instances et deux jeux de fichiers partages, `catalogue.yaml` et l'inventaire ; mon geste de casquette, mes limites et la relecture de `yanis-qa` pour la fusion de ma propre branche sont ceux du palier 1, section "Charge des developpeurs").
- **Ajuste : `marc-dev` porte aussi le script de `PA1` et `S57-OBS-SCI-N1-N2`**, que la composition laissait au developpeur "a designer". Ordre : le script d'abord, parce que c'est le seul element de la chaine de la forme que le developpement porte avant la reponse de `nadia-prof` ; `-05` attend de toute facon `Q-P6` pour son YAML. Le palier 1 lui a donne trois postes et `F1` ; ici trois postes, dont deux petits.
- **Confirme : `lea-dev` pour la forme commune.** Elle a fait la chaine B1 de la feuille de style et de la forme C (`25e4a823`, `472dce51`, `6a80fe66`, `7ab7bfd7`) sur laquelle la forme commune s'ajoute ; un autre developpeur paierait ce contexte. **Ajuste : `RDO-CP-R3` n'est nominee a `lea-dev` que sous condition.** Elle a 529 tours en s56 (2,9 fois `marc-dev`), et la chaine forme puis `RDO-CP-R3` sur un seul developpeur etait le chemin critique du palier 1. **Regle ecrite : a l'amendement qui engage p2-12, si la forme commune n'est pas fusionnee, je nomme `marc-dev` (s'il a fusionne `-05`) ou j'ecris a l'utilisatrice que `lea-dev` est le chemin critique ; je ne reaffecte pas la forme.** `RDO-CP-R3` et la forme touchent la meme feuille de style : deux developpeurs en meme temps imposent un ordre de fusion et une resolution de conflit que j'ecris alors.
- **La charge en tours n'est pas remesuree** : le rapport de tokens est un seul pour le sprint, a la review (`squad/README.md`, section 6). Les nombres de s56 sont un repere, pas l'etat d'aujourd'hui.
- **Si `marc-dev` a trois postes ouverts et que l'un d'eux bloque**, il ne les entrelace pas : un worktree a la fois, et il me le dit.

### 6. Isolation : repertoire, port et worktree, par agent, instance et palier

`I1` a `I3` du palier 1 s'appliquent **sans changement** : un repertoire par agent, par instance et par palier, a un seul niveau sous la racine, nomme `s57-p2-<agent>-<instance>`, jamais reutilise ; un port de jeu par agent, par instance et par palier ; avant chaque lancement le port est libre (`netstat -ano` filtre sur `":<port> "`), apres, la ligne *"Tomcat started on port <port>"* et le PID sont lus dans **son** journal, arret par PID, jamais par nom d'image. **Remplace** la table du palier 1 pour le palier 2. Le port de debogage de Chrome est dans la table : **port de jeu plus 700**, la regle que `lea-dev` (19231) et `ines-design` (19243) ont suivie au palier 1, reprise ici pour tous. Etat de l'ecoute a l'ouverture : `netstat -ano` filtre sur les ports 185xx et 192xx rend 0 ligne.

| Agent | Instance | Postes | Repertoire de scratchpad | Port de jeu | Port de debogage |
|---|---|---|---|---|---|
| `nadia-prof` | A | audits et rejeux de A | `s57-p2-nadia-prof-a` | 18511 | 19211 |
| `nadia-prof` | A' | audits et rejeux de A' | `s57-p2-nadia-prof-ap` | 18512 | 19212 |
| `nadia-prof` | B | rejeux de B | `s57-p2-nadia-prof-b` | 18513 | 19213 |
| `marc-dev` | A | p2-3, puis p2-10 et p2-11 si les textes | `s57-p2-marc-dev-a` | 18521 | 19221 |
| `sofia-sm` (developpeuse) | A' | p2-4 | `s57-p2-sofia-dev-ap` | 18523 | 19223 |
| `marc-dev` | B | p2-5 | `s57-p2-marc-dev-b` | 18524 | 19224 |
| `marc-dev` | transverse | p2-6, script de `PA1` | `s57-p2-marc-dev-t` | 18525 | 19225 |
| `lea-dev` | B | p2-12 | `s57-p2-lea-dev-b` | 18531 | 19231 |
| `lea-dev` | A' | p2-7 | `s57-p2-lea-dev-ap` | 18532 | 19232 |
| `yanis-qa` | transverse | rapport qualite, mesures | `s57-p2-yanis-qa-t` | 18541 | 19241 |
| `theo-archi` | transverse | avis, confirmation | `s57-p2-theo-archi-t` | 18542 | 19242 |
| `ines-design` | transverse | cote de reference, mesures de la forme, jar de base | `s57-p2-ines-design-t` | 18543 | 19243 |
| `ines-design` | transverse, second jar | jar de la branche de la forme, mesure appariee | `s57-p2-ines-design-t2` | 18544 | 19244 |
| `camille-po`, `relecteur-pedagogique`, `sofia-sm` (Scrum Master) | transverse | aucun jar | `s57-p2-camille-po-t`, `s57-p2-relecteur-t`, `s57-p2-sofia-sm-t` | aucun | aucun |

**Collisions evitees, et d'ou elles viennent** (mesurees par `git grep` sur `0cdccb62` et par l'arbitrage, section 3) :

- **`C1`, 19222.** `pilote-q7.mjs` fixe ce port de debogage en dur (ligne 6). Avec la regle *"jeu plus 700"*, le port 18522 du palier 1 (`marc-dev`, instance A') donne 19222 : **18522 n'est donne a personne au palier 2**, et `marc-dev` n'a plus d'instance A'. `pilote-q7.mjs` reste a un seul agent a la fois, sa ligne s'ecrit dans ce fichier avant l'usage (consigne du palier 1, reprise).
- **`C2`, 19333 et 19334.** `pilote-accueil.mjs` et `capture-colle.mjs` (`squad/conformite/releves-accueil/`) fixent ces deux ports en dur. Aucun n'est donne ; a un seul agent a la fois.
- **`C3`, `outils/mesurer-accueil.py`** ouvre Chrome avec `--remote-debugging-port=0` (port aleatoire) : aucun port de la table ne le contient. Rien a allouer, a lancer seul.
- **`C4`, ports hors table du palier 1.** `ines-design` a servi sur **18457** (2026-10-14) et **18571** (jar de base de `rapport-ines-r8-forme-commune.md`), au lieu de 18543 ; `lea-dev` a utilise 19231 (debogage) sans qu'il soit dans la table. Aucune collision n'en est resultee. Les trois sont maintenant dans la table, et `ines-design` a une seconde ligne (18544, 19244) pour le jar de base et le jar de la branche qu'elle compare : un second lancement concurrent n'a plus a attendre une ligne ajoutee.
- **`C5`, un meme repertoire et un meme port pour deux instances.** `marc-dev` a servi les postes 3 (A) et 7 (A) sur `s57-p1-marc-dev-a` et 18521, `m1.log` du poste 4 (A') est tombe a la racine. Ici `marc-dev` a trois lignes (A, B, transverse), chacune avec son port.
- **`C6`, noms de repertoire hors format.** Au palier 1, `camille`, `camille-po-s57-p1`, `theo-s57-avis`, `ines-s57` et `s57-p1-nadia-prof-a-poste7` ont remplace les noms de la table, et `s57-p1-sofia-dev-ap` n'a jamais ete cree. **Le nom se copie de la table, lettre pour lettre, et le repertoire se cree avant la premiere commande.**
- **`C7`, un agent qui prend les fichiers d'un autre.** `yanis-qa` a deplace sept fichiers de `camille-po` et du `relecteur-pedagogique` pendant 19 secondes (arbitrage, 3.3). La consigne ci-dessous interdit de deplacer, renommer ou supprimer un fichier qu'on n'a pas cree.

**Consigne ecrite, a repeter en tete du message de lancement de chaque agent du palier 2** (constat de `nadia-prof`, arbitrage 3.2 : la regle *"aucune ecriture a la racine du scratchpad"* n'est pas tenue par huit agents sur neuf ; le crochet du harnais ne bloque que l'outil d'ecriture de fichier et ne voit pas les redirections du shell). **Ce n'est pas un moyen de garde et ce n'est pas le crochet** : le crochet est `Q4` de `theo-archi`, routee a l'utilisatrice, sans reponse dans le depot ; je n'en decide pas.

> 1. Ton repertoire de scratchpad est celui de la table du palier 2 de `planning.md`, copie lettre pour lettre. Ta premiere commande le cree (`mkdir -p`). Tu n'en utilises aucun autre.
> 2. **Tout ce que tu ecris, tu l'ecris dans ce repertoire, quel que soit l'outil** : l'outil d'ecriture, mais aussi chaque redirection du shell (`>`, `>>`, `2>`, `&>`, `tee`), chaque journal de build ou de jar, chaque profil de Chrome, chaque capture, chaque fichier de PID, chaque sortie d'un script Python ou Node.
> 3. **Chemin absolu, toujours, construit a partir de ton repertoire.** Le repertoire courant est reinitialise entre deux commandes : un chemin relatif n'est pas sur.
> 4. Trois endroits sont interdits pour tes fichiers : la racine du scratchpad, le repertoire parent des worktrees, le repertoire temporaire du systeme (`$TEMP`, `/tmp`).
> 5. Tu ne deplaces, ne renommes et ne supprimes aucun fichier que tu n'as pas cree, meme s'il te gene ou si tu crois qu'il est a toi.
> 6. Avant de rendre la main : tu listes la racine du scratchpad et le repertoire parent des worktrees ; tout fichier que tu y as ecrit est nomme dans ton rapport, puis deplace dans ton repertoire (ou supprime, s'il est a toi). Ton rapport porte une ligne *"racine : propre"* ou la liste des noms. Tu ne declares jamais propre une racine que tu n'as pas listee.

**Ce que je mesure, et ce que je ne mesure pas** : a la cloture du palier 2, je liste (`ls`) les racines que ma session peut lire et je compte les fichiers qui n'y ont rien a faire ; c'est une mesure, pas une garde. Les racines des autres sessions, redirections comprises, restent lues par `nadia-prof` dans les transcriptions (arbitrage, 3.4, point 3, et section 10) : je ne m'y substitue pas.

**Branches et worktrees**, une branche et un worktree dedies par poste, voisins du checkout (`recreo-<branche>`), sans rebase, sans push, sans fusion par les agents ; chaque agent note `git log -1 --format=%h` dans son premier message ; **je verifie la lignee avant chaque fusion** (`bash outils/verifier-lignee.sh`), meme quand l'agent dit l'avoir faite. Aucune de ces branches n'existe (`git branch --list`, rien) et aucun de ces worktrees non plus.

| Poste | Branche | Worktree |
|---|---|---|
| p2-3 | `s57-marc-fra-ce1-05` | `../recreo-s57-marc-fra-ce1-05` |
| p2-4 | `s57-sofia-fra-ce1-24` | `../recreo-s57-sofia-fra-ce1-24` |
| p2-5 | `s57-marc-sci-n1-n2` | `../recreo-s57-marc-sci-n1-n2` |
| p2-6 | `s57-marc-script-additions-cp` | `../recreo-s57-marc-script-additions-cp` |
| p2-7 (si engage) | `s57-lea-forme-commune` | `../recreo-s57-lea-forme-commune` |
| p2-12 (si engage) | `s57-lea-rdo-cp-r3` | `../recreo-s57-lea-rdo-cp-r3` |
| audits de `nadia-prof` | `s57-nadia-p2-a`, `s57-nadia-p2-ap`, `s57-nadia-p2-b` | `../recreo-s57-nadia-p2-a`, `-ap`, `-b` |
| cloture de palier | `s57-sofia-cloture-p2` | `../recreo-s57-sofia-cloture-p2` |

### 7. Prealables, avec titulaire et ordre

Codes `PA1` a `PA9` : reperes de `composition-palier-2.md` section 6, pas des identifiants. **Ordre 1** : la question est posee ou le travail part des l'ouverture, en parallele. Les ordres suivants disent ce qui precede quoi.

| Code | Quoi | Titulaire | Ordre | Bloque | Etat au 2026-10-15 20:56 |
|---|---|---|---|---|---|
| `PA1` | Script des 43 enonces restants d'`additions-cp` (22 mesures sur 65 aujourd'hui) | **ecriture : `marc-dev`** (mon affectation, `CR14`) ; **mesure : `ines-design`**, sur le jar de la base | ecriture 1 ; mesure apres la base (ordre 4) | tout rejeu de la forme | script non ecrit ; aucune story (`Q-S5`) |
| `PA2` | Cote de reference de `Q-R4` ecrite en entier, fichier date | `ines-design` (mesure et fichier) ; `nadia-prof` dit si une base de code identique suffit (`Q-P4`) | 4, apres `PA3` et `PA5` et la base de la section 9 | tout rejeu de la forme | elements 1, 2 et 4 mesures, element 3 incomplet (depend de `PA1`) |
| `PA3` | Accord de `nadia-prof` sur `R3-1` | `nadia-prof` (`Q-P3`) | 1 | l'ouverture du worktree de la forme | non repondue |
| `PA4` | Lecture de l'ascenseur a la revelation | `nadia-prof` (`Q-P3`) | 1 | idem | non repondue |
| `PA5` | Lecture de `C1'`, `C2` et `C3` par `theo-archi`, amendement d'`ADR` eventuel | `theo-archi` (`Q-P7`) | 1 ; porte aussi la trace de `E3` | l'ouverture du worktree de la forme | non ecrit |
| `PA6` | Story de mise en oeuvre de la forme | `camille-po` | - | - | **faite** : `grep -c` rend 1 pour `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` |
| `PA7` | Voie de `RCT-CE1-R1` pour les 3 items a 552 | `nadia-prof` (regle du bandeau), developpeur et `theo-archi` (propositions plus courtes) : `Q-P5` | question 1 ; engagement apres la forme fusionnee | p2-10 et son prix (0,25 ou 0,5) | non repondue |
| `PA8` | Voie de `RDO-CP-R3` (`Q-R7`) | `ines-design`, `theo-archi` | 1 ; engagement apres `E3` et la regle de la section 5 | p2-12 | non repondue |
| `Q-P6` | Item 15 de `ce1-pronoms` | `nadia-prof` | 1 | le commit de p2-3 | non repondue |
| `Q-S8` | Forme de la correction de l'item *livre* | `nadia-prof`, avec `sofia-sm` developpeuse | 1 | le commit de p2-4 | non repondue ; `Q-B2` (l'indice cite) reste aussi ouverte, sans effet sur le motif de fond |

**Chaine de la forme, dans l'ordre** : (1) `PA3`, `PA4`, `PA5` et la trace de `E3` ; (2) un amendement date de ce fichier engage p2-7, nomme la base (section 9) ; (3) `lea-dev` coupe la branche sur cette base, `ines-design` mesure `PA2` sur le meme commit et `marc-dev` a deja livre le script de `PA1` ; (4) `lea-dev` developpe ; (5) fusion apres `build` vert ; (6) rejeu de `TPH-CE1-R1` avec `GNA-CE1-R1` sur le meme jar, puis `VSJ-CE1-R2` et `RCT-CE1-R1` selon leurs voies. Un raccourci n'existe pas : le rejeu avant la cote ecrite est un interdit de `nadia-prof` (arbitrage, section 8, point 4).

### 8. Compte des reports

Regles de `DEC-0120` A.66 a A.73, confirmees par moi au palier 1 (amendement du 2026-10-14 18:20) et appliquees a l'identique. **Un report est un poste retenu par la composition et non engage a l'ouverture du palier, quelle qu'en soit la cause.**

| Poste | Compte a l'ouverture du palier 2, 2026-10-15 | Ce qui debloque |
|---|---|---|
| p2-7 forme commune (hors `PAR`) | **premier report** | `PA3`, `PA4`, `PA5`, `E3` |
| p2-8 `TPH-CE1-R1` | **premier report** | la forme, `PA1`, `PA2` |
| p2-9 `GNA-CE1-R1` | **premier report** | idem, apres p2-8 |
| p2-10 `RCT-CE1-R1` | **premier report** | `PA7`, la forme |
| p2-11 `VSJ-CE1-R2` | **premier report** | `Q-S9` |
| p2-12 `RDO-CP-R3` | **premier report** | `PA8`, `E3` |

- **Ecart avec `composition-palier-2.md`, section 9** : elle ecrit *"premiere inscription ; un glissement du palier 2 est le premier report"* pour ces postes. A.66 compte le report **a l'ouverture** (precedent : postes 7 et 8 du palier 1, comptes le jour de l'ouverture, `DEC-0120` A.67). **J'applique A.66 telle qu'ecrite et je le dis** ; si `camille-po` lit autrement, elle l'ecrit par amendement a `DEC-0120` : `Q-S6`. L'enjeu est reel : un second report au palier 3, puis le troisieme interdit.
- **Un engagement tardif n'efface pas le report, il evite le suivant** (A.83). **Un poste glisse au plus une fois** : engage au palier 2 et non audite a sa cloture, il glisse au palier 3 ; le second glissement est un report compte, ecrit par moi le jour de la cloture. Le troisieme report est interdit (A.67, A.72).
- **`-06`, `-07`, `-11` (groupe X)** : deuxieme ecartement, compte de `camille-po` (`composition-palier-2.md`, section 9), que je ne recalcule pas. *"Si `-06` n'entre pas au palier 3, c'est le troisieme : une `DEC` le dit, avec le motif."*
- **`S54-OBS-SCI-CONSIGNE`** : VALIDE au palier 1, rien a compter.

### 9. Ordre de fusion

**Reacteur complet a chaque fusion** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`. **Tout compte de tests est celui du reacteur sur l'arbre fusionne, que j'etablis moi-meme.** Dernier total mesure : 2597 sur `e7497db7` (`cloture-palier-1.md`, section 3) ; le total de l'ouverture est dans la section 12 (jobs).

- **Catalogue** : 150 entrees sur cet arbre (`grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml`), dont 145 `bandit-manchot` (`grep -c "mecanique: bandit-manchot"`). `grep -c "paquet: ce1-pronoms$"` et `grep -c "paquet: ce1-familles-de-mots$"` rendent 0. Apres `-05` et `-24` : 152 et 147, chacun des deux `grep` rendant 1. **Une ligne par fusion dans `cloture-palier-2.md`** : SHA, compte d'avant, entrees fusionnees, compte d'apres ; un ecart arrete la fusion et va a `theo-archi`. Resolution des fichiers partages (`catalogue.yaml`, inventaire de `TexteAccentueExposeParLePortEntrantTest`, `REGISTRE-identifiants.md`) : celle du palier 1, section "Ordre de fusion" : **je resous, j'ai les ajouts des deux cotes, jamais une assertion affaiblie.**
- **Ordre** : p2-5, p2-3 et p2-4 dans l'ordre ou ils sont prets, un a la fois, `clean verify` entier apres chacun. Je ne retiens aucune fusion de lot pour attendre la forme : la preference de `nadia-prof` (forme fusionnee avant l'audit de `-05`) est la sienne, elle audite quand elle le decide, et sans la forme un lot audite porte une reserve de la famille de `RCT-CE1-R1` (arbitrage, 6.1, point 4, et section 7).
- **Base de la cote de reference** (`Q-R5`) : c'est **le sommet de `round-two` au moment ou `lea-dev` coupe la branche de la forme**. Je note son SHA dans l'amendement qui engage p2-7 ; `PA2` nomme ce commit ; **toute fusion sur `round-two` entre la mesure et la coupe force la remesure**. Les lots fusionnes avant la coupe sont dans la base (le compte de jeux monte de 1 par entree `bandit-manchot`, `composition-palier-2.md` `CT2`) ; ceux fusionnes apres ne sont pas dans le jar rejoue.
- **Forme, apres sa fusion** : `git diff <jar rejoue> <resultat de la fusion> -- adapters domain application bootstrap` vide, ou ce qu'il montre nomme a `nadia-prof` avant (`R1` de s57, section "Branche de rejeu B2", transpose).
- **Garde-fou de l'accent** : aucune instance ne le modifie (`K10`, `ADR-0065` A2).

### 10. Questions et routes

Je classe, je ne reponds a aucune. Un obstacle n'est clos que quand le fichier qui le tranche existe.

| Question | Objet | Route | Ce que le planning engage en attendant |
|---|---|---|---|
| `Q-S4` | La lettre de `PN8` et `FM5` (*"avant l'ouverture du worktree"*) interdit-elle d'ouvrir le worktree de `-05` et `-24` avant la lecture complete de `nadia-prof` ? | `camille-po` | worktrees ouverts, aucun YAML de paquet touche (section 4) |
| `Q-S5` | p2-6 (script de `PA1`) n'a pas de story ; `PAR-16` en demande une pour tout poste engage ; hors `PAR` et sans prix, `CR14` n'est qu'une ligne de routage | `camille-po` | p2-6 engage sans story, ecart dit ici ; `PAR-16` n'est pas assoupli |
| `Q-S6` | Compte des reports a l'ouverture : A.66 ou section 9 de la composition (section 8 de cet amendement) | `camille-po` | A.66 appliquee |
| `Q-S7` | `E3` est-elle une porte de tout le palier, comme ma cloture l'avait ecrit, ou des deux seuls postes qui reprennent le code de B1 ? | utilisatrice, routee par `sofia-sm` | porte des postes p2-7 et p2-12 seulement |
| `Q-S8` | Forme de la correction de l'item *livre* | `nadia-prof` | commit de p2-4 en attente |
| `Q-S9` | Voie de `VSJ-CE1-R2` : la forme commune (aucun developpeur) ou les textes (`marc-dev`) | `nadia-prof` | p2-11 non engage |
| `Q-P3`, `Q-P4`, `Q-P5`, `Q-P6`, `Q-P7`, `Q-R7`, `Q-B2` | ouvertes dans `composition-palier-2.md` section 7 et le backlog | voir section 7 | voir section 7 |
| `Q4` de `theo-archi` | crochet du harnais sur les redirections du shell | utilisatrice, `theo-archi` | la consigne de la section 6, sans decision de crochet |
| mots de `Q-N4` et `S2` | absents du depot (`P5` de la cloture) | agent lanceur | rien n'en depend a l'ouverture, sauf le regime du pli (arbitrage, section 9) |

### 11. Ce que cet amendement ne fait pas

N'ouvre aucun worktree, ne lance aucun agent, ne fusionne rien. N'engage ni p2-7 a p2-12 ni les quatre stories a prix 0. Ne modifie aucun prix, aucun lot, aucun critere de story. Ne prononce aucun Done, ne juge aucun contenu, ne touche pas la matrice (146 / 200, `DEC-0120` A.91). Ne decide aucun crochet. Ne tranche ni `Q-S4`, ni `Q-S6`, ni `Q-S7`. Ne modifie aucun fichier signe : `planning.md` s'amende par cette annotation datee, comme sa section "Duree" l'a prevu (`D2`).

### 12. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, avec ce fichier modifie, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`prefixes`** : `verifier-prefixes-test.sh` sort 0 (*"Tous les cas passent : le garde-fou de prefixes mord, et sur le bon motif."*), `commit-atteignable-test.sh` sort 0, `verifier-garde-fou-accent-test.sh` sort 0 (21 cas) ; le sujet prevu du commit et les sujets de `0cdccb62~3..HEAD` passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0. L'etape *"Verifier le proprietaire du garde-fou de l'accent"* n'est pas lancee : aucun de ses chemins n'est touche.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce fichier modifie compris, passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 5 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), 12 juges lances avec `python`, 12 sortis 0, aucun balayage vide. Lance bien qu'aucun outil ne soit modifie, parce que la porte technique nomme ses jobs.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0, 3 min 46 s. **2597 tests, 0 echec, 0 erreur, 0 ignore** : domain 120, application 31, adapter-web 492, adapter-content 1567, bootstrap 381, integration 6, somme recomptee par `awk`. JDK 21.0.10 (`java.version` de 60 rapports Surefire de `bootstrap`, une seule valeur). Le total egale celui de `e7497db7` : ce commit ne change ni code ni contenu. Des tests de `bootstrap` lisent `squad/` et `docs/` : le build a lu ce fichier avant l'ajout de la presente section, qui ne contient aucun tableau.
- **Etape "Package du fat jar"** : non lancee.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

### 13. Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : l'heure d'ouverture du palier 2 ; la composition de `camille-po` portee, sans changement de prix ; les affectations nominatives de p2-3, p2-4, p2-5, p2-6 et les developpeurs nommes de p2-7 a p2-12 ; la regle de reaffectation de `RDO-CP-R3` ; la table d'isolation du palier 2 et la consigne ecrite sur la racine du scratchpad ; l'ordre de la chaine de la forme et la base de la cote ; le compte des reports a l'ouverture. **L'amendement de `DEC-0120` qui porte la composition** (precedent : A.53 a A.62) est a `camille-po` : les proprietaires qu'elle attendait sont nommes ici (`composition-palier-2.md`, section 9, derniere phrase).

---

## Amendement du 2026-10-16 09:03 (UTC+02:00), palier 2 : fusions des arbitrages et des reponses, engagement de p2-7

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou corrige est nomme. Arbre : `round-two` a `babd70e7` (apres trois fusions de ma main), branche `s57-sofia-planning3`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` vide avant ecriture. Sources, par auteur : `camille-po` (`DEC-0120` A.94 a A.99, backlog, `8ea8f4f2`) ; `nadia-prof` (`reponse-nadia-q-palier-2.md`, `5d2f34fb`) ; `theo-archi` (`avis-theo-palier-2.md`, `d5d974b5` et `c85d54e6`). **Cet amendement engage p2-7. Il n'ouvre aucun autre worktree.**

### 1. Fusions de ce jour, lignee verifiee avant chacune

Les trois branches partent de `f6134910` (`git merge-base round-two <branche>` rend ce SHA pour chacune) ; `bash outils/verifier-lignee.sh` lance dans chaque worktree sort 0 ; aucune des trois ne touche un fichier touche par une autre (`git diff --name-only round-two...<branche>`). Reacteur complet apres chaque fusion : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`.

| Ordre | Branche | Sommet livre | Fusion | Compte de tests mesure par moi |
|---|---|---|---|---|
| 1 | `s57-camille-p2-arbitrages` | `8ea8f4f2` | `bf471fed` | 2597, `BUILD SUCCESS` |
| 2 | `s57-nadia-p2-a` | `5d2f34fb` | `f3b5e4cb` | 2597, `BUILD SUCCESS` |
| 3 | `s57-theo-p2-avis` | `c85d54e6` | `babd70e7` | 2597, `BUILD SUCCESS` |

**Compte** : 120 + 31 + 492 + 1567 + 381 + 6 d'integration, lignes `Tests run` du reacteur, identique a chaque fusion et a celui de l'ouverture (section 12 de l'amendement precedent) : les trois branches ne changent ni code ni contenu servi. Le total de `avis-theo-palier-2.md` (2597) egale le mien ; il a ete mesure sur sa branche, qui part de la meme base. Les jobs de `ci.yml` lances pour ce commit sont en section 12.

### 2. Decisions inscrites, avec le fichier qui les porte

| Question | Reponse | Fichier, sur `round-two` | Etat |
|---|---|---|---|
| `Q-S4` | oui : le worktree de `-05` et `-24` ne s'ouvre qu'apres la ligne *"lecture complete"* de son paquet | `DEC-0120` A.96 | tranchee ; **sa commande ne passe pas sur les lignes ecrites, section 4** |
| `Q-S5` | story `S57-SCRIPT-ADDITIONS-CP`, prix 0, hors `PAR` | `DEC-0120` A.97, backlog (annotation du 2026-10-16) | tranchee |
| `Q-S6` | compte des reports a l'ouverture : A.66 a la lettre, la phrase de la composition est remplacee | `DEC-0120` A.95 | tranchee ; l'ecart etait celui de la composition |
| `Q-S7` | `E3` couvre tout le palier 2, pas seulement p2-7 et p2-12 | `DEC-0120` A.94 (a) | tranchee par l'utilisatrice, forme 2 de `DEC-0062` : mots non cites, relayes par l'agent lanceur, que je n'ai pas lus de sa main |
| `E3` | **levee** : verdict *conforme* sur les quatre commits de B1 | `avis-theo-palier-2.md` section 1 | lue apres la fusion de B1 : elle constate la garde a posteriori, elle ne la remplace pas |
| `PA5` (`Q-P7`) | `C1'`, `C2`, `C3` tenues sur la forme ; **aucun amendement d'ADR** ; compte ArchUnit inchange selon `theo-archi` (41 et 38), non recompte par moi | `avis-theo-palier-2.md` section 2, `TP1` a `TP3` | tranche |
| `PA3` (`Q-P3`) | accord sur `R3-1`, condition regardee au rejeu de `TPH-CE1-R1` | `reponse-nadia-q-palier-2.md` 3.1 | tranchee |
| `PA4` (`Q-P3`) | l'ascenseur a la revelation est une observation sans reserve, condition regardee au rejeu | idem 3.2 | tranchee |
| `Q-P4` | oui, **si** `git diff --stat 6dbb855d <base> -- pom.xml domain application adapters bootstrap` ne rend rien ; sinon la cote se remesure | idem 3.3 | tranchee ; **condition non tenue, section 5** |
| `PA7` (`Q-P5`) | regle du bandeau **non accordee** ; propositions plus courtes, textes donnes, sous une mesure qui n'existe pas | idem 3.4 | voie (a) ouverte, mesure a faire |
| `Q-P6` | item 15 de `ce1-pronoms` corrige avant le branchement, texte donne | idem 3.5 | tranchee ; correction a recopier par `marc-dev` |
| `Q-S8` | item *livre* remplace par *chat* et *chaton*, texte donne | idem 3.6 | tranchee ; correction a recopier par la developpeuse |
| `Q-S9` | voie de `VSJ-CE1-R2` : la forme commune, prix 0,25 | idem 3.8 | tranchee |
| `Q-B2` | deja repondue le 2026-10-14 (`lecture-nadia-palier-1.md` 1.7) ; relecture des cinq items faite | idem 3.7 | close ; recommandation non bloquante laissee a `camille-po` |
| `Q-N4` | mots *"le pli n'est pas bloquant"*, annotes dans `rejeu-nadia-palier-1.md` et `FICHE-bandit-manchot-phrases-types-ce1.md` ; ils ne changent pas le regime du pli | idem section 2 | mots relayes (forme 2), date de l'echange d'origine non fournie |

**Ce que ces fichiers ne disent pas.** Les deux lignes *"lecture complete"* de `nadia-prof` (`ce1-pronoms`, 20 items ; `ce1-familles-de-mots`, 24 items) sont ecrites (`reponse-nadia-q-palier-2.md`, section 1). **`S2` (mots de l'utilisatrice) reste absent du depot** et je n'en ecris aucun ; je ne touche pas non plus aux autres points de provenance de la cloture du palier 1.

**Obstacles clos par ce geste** : `Q-S4` (decision), `Q-S5`, `Q-S6`, `Q-S7`, `Q-S8`, `Q-S9`, `Q-P3`, `Q-P6`, `Q-P7`, `E3` : le fichier qui les tranche existe sur le disque (`git grep -n "^### A.9[4-9]"` sur `DEC-0120` rend six lignes ; le fichier de `nadia-prof` et celui de `theo-archi` sont dans le dossier du sprint). **Restent ouverts** : la commande de `Q-S4` (section 4), `Q-P4` (condition non tenue, section 5), `Q-P5` (mesure), `Q-R7`, `Q4` (section 10), `Q-T14` (section 8). `Q-T15` est repondue en section 8.

### 3. Corrections de l'amendement du 2026-10-15 20:56

- **Section 4 (*"Peut commencer ... sans prealable"*).** Elle declarait p2-3 a p2-6 partables sans prealable hors `K1`. **Cela ne tient plus** : `E3` couvre le palier (`Q-S7`) et sa trace est sur `round-two` depuis `babd70e7` (`avis-theo-palier-2.md`, section 1, verdict *conforme*). Ce qui reste a chaque poste est en section 4 du present amendement.
- **Titre de p2-6 (section 3).** *"aucun titre"* est remplace par **`S57-SCRIPT-ADDITIONS-CP`**, au backlog (`git grep -c "S57-SCRIPT-ADDITIONS-CP" -- docs/backlog/backlog.md` rend 2). Prix 0, hors `PAR`, competence transverse, ecriture `marc-dev`, mesure `ines-design`. `PAR-16` n'est pas assoupli : elle ne demandait pas de story pour un poste hors `PAR` (A.97), et il en a une. **La question `Q-S5` du planning (*"p2-6 engage sans story"*) est close.** Critere 8 de la story (*"aucune edition avant l'amendement du planning qui nomme la story, ni avant la trace de `E3`"*) : cet amendement nomme la story, la trace de `E3` est tenue. **`marc-dev` ouvre son worktree apres la fusion de cet amendement, pas avant.**
- **Ligne `D5` (sections 3 et 4).** *"`D5` ne s'engage pas avant la fusion de la forme commune"*, la liste *"Hors `PAR`, non engages [...] `D5`"* et *"les quatre stories a prix 0 et `D5`"* sont **obsoletes**. `D5` est fait : `29d02bc6` est ancetre de `round-two` (`git merge-base --is-ancestor 29d02bc6 round-two` sort 0), fusionne par `dbd4a612` le 2026-10-15 18:10, **26 minutes apres la fusion de la cloture du palier 1 (`6dbb855d`, 17:44) et avant l'ouverture du palier 2 (20:56)** ; `grep -rl "brouillon soumis"` sur `adapters/adapter-content/src/main/resources/contenu` rend 0 fichier. Il reste **trois** stories hors `PAR` non engagees : `S57-MESSAGE-PARTIE-PERDUE`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-GARDE-NOMBRE-DE-MANCHES`. **Le meme ecart est dans `composition-palier-2.md` (section 4, ligne `D5`, et son resume) : le fichier est a `camille-po`, je ne le corrige pas.**
- **Section 10, `Q-S7`.** La colonne *"Ce que le planning engage en attendant"* (*"porte des postes p2-7 et p2-12 seulement"*) est **remplacee** : `E3` couvre tout le palier (`DEC-0120` A.94 (a)).
- **Section 8, ecart avec `composition-palier-2.md`.** Tranche par `DEC-0120` A.95 en faveur de la lecture que j'avais appliquee ; la section 8 reste exacte.

### 4. Etat de chaque poste ouvrable, et ce qui reste avant son worktree

| Poste | Developpeur | Ce qui reste avant le worktree | Ce qui reste avant le commit ou la fusion |
|---|---|---|---|
| p2-6, script | `marc-dev`, premier poste | la fusion de cet amendement | tous les criteres de la story ; mesure de `ines-design` apres |
| p2-5, `N1` et `N2` | `marc-dev`, deuxieme poste | la fin du script (un worktree a la fois, section 5 de l'amendement precedent) | `K1` ; orthotypographie des chevrons par le `relecteur-pedagogique` avant le rejeu |
| p2-3, `-05` | `marc-dev`, troisieme poste | la ligne *"lecture complete"* de `ce1-pronoms` au sens de `DEC-0120` A.96 : **la commande ne passe pas, voir ci-dessous** | recopier sans reformulation les indices des items 10 et 13 et l'item 15 (`reponse-nadia-q-palier-2.md` 1.1 et 3.5) ; `PN8` |
| p2-4, `-24` | `sofia-sm`, casquette developpeuse | idem pour `ce1-familles-de-mots` | recopier l'item *chat* et *chaton* a la place de *livre* ; `grep -c 'bonneReponse: "librairie"'` rend 0 ; `FM5` |

**Ecart que je route sans le trancher : la commande de `DEC-0120` A.96 rend 0 sur l'arbre qui porte les lignes de `nadia-prof`.** Commande de la decision, lancee par moi sur `babd70e7` : `git grep -l -E 'lecture complete : .?ce1-pronoms.?, 20 items lus' -- squad docs | wc -l` rend **0**, et de meme pour `ce1-familles-de-mots`, 24. Les lignes existent : `reponse-nadia-q-palier-2.md`, lignes 43 et 44, ecrites `- **lecture complete** : ...`. **La mise en gras place `**` entre *"complete"* et *" : "*, que le motif n'accepte pas** ; avec `lecture complete.{0,6} : ` a la place, le meme fichier est trouve pour chacune des deux. Je ne choisis pas : c'est la commande de `camille-po` (A.96 et critere 2 de `S57-ARB-PALIER-2`) ou la forme de la ligne de `nadia-prof`. **Tant qu'aucune des deux n'a ecrit, je n'ouvre pas le worktree de `-24` (le mien).** `-05` n'est pas touche : c'est le troisieme poste de `marc-dev`.

### 5. Q-P4 : la base de code n'est pas identique a celle de la cote

`git diff --stat 6dbb855d babd70e7 -- pom.xml domain application adapters bootstrap` rend **10 fichiers, 10 lignes ajoutees et 10 supprimees**, tous sous `adapters/adapter-content/src/main/resources/contenu/` : l'en-tete de dix paquets `ce1-*` que `D5` a modifie (`29d02bc6`, commentaires seuls). `6dbb855d` est le jar de base de `rapport-ines-r8-forme-commune.md` (section 1) et des releves `r3-*`. **Selon `nadia-prof` (3.3), la sortie n'est pas vide, donc la cote se remesure sur la base reelle, sans exception de contenu.** Je ne juge pas qu'un commentaire d'en-tete ne change pas l'ecran. **Effet : aucun delai de plus**, parce que la chaine de la forme (section 7 de l'amendement precedent, etape 3) fait deja mesurer `PA2` par `ines-design` sur le commit de la coupe. Ce que la sortie non vide retire : la possibilite de reutiliser comme cote de reference les elements de `PA2` mesures sur `6dbb855d`. **A `ines-design`** : remesurer sur la base de la section 6, commande et sortie dans le fichier date de la cote.

### 6. Engagement de p2-7 (forme commune des propositions longues), 2026-10-16 09:03

**p2-7 est engage.** `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES`, prix 0, hors `PAR`, agent `lea-dev`, branche `s57-lea-forme-commune`, worktree voisin `recreo-s57-lea-forme-commune`, repertoire de scratchpad `s57-p2-lea-dev-ap`, ports 18532 et 19232 (section 6 de l'amendement precedent, sans changement).

Les quatre conditions que la section 4 de l'amendement precedent posait, lues sur l'arbre :

| Condition | Fichier et lieu | Etat |
|---|---|---|
| `PA3` | `reponse-nadia-q-palier-2.md` 3.1 | levee, accord |
| `PA4` | idem 3.2 | levee, observation sans reserve |
| `PA5` | `avis-theo-palier-2.md` section 2, `TP1` a `TP3` | levee, aucun amendement d'ADR |
| `E3` | `avis-theo-palier-2.md` section 1 | levee, *conforme* |

**Ce qui reste, pour lui et pour moi** :

1. **Conditions regardees au rejeu, pas a l'engagement** : le score se lit comme une ligne du jeu avec `R3-1` ; aucun libelle ne change de nombre de lignes a la revelation a 720 (`nadia-prof` 3.1 et 3.2). Une condition non tenue est une reserve neuve, datee, non bloquante.
2. **`scrollbar-gutter` n'entre pas dans la forme** (`TP5`). S'il est demande plus tard, le meme commit amende `ADR-0054` et sa regle.
3. **La fusion attend la trace de lecture de `theo-archi`** (`TP6`, section 8).
4. **`C1'` n'a aucune garde executable** sur l'ecran de manche (`TP4`) : la forme ne cree pas la dette ; son traitement est en section 8.
5. **Le script de `PA1` n'est pas une condition de l'engagement.** La section 7 de l'amendement precedent le placait avant la coupe (etape 3, *"marc-dev a deja livre"*) ; **je corrige ce point** : la coupe ne depend que de la base, le script conditionne la mesure de `PA2` et tout rejeu (ordre 4), pas la coupe ni le developpement.

**Base de la cote (`Q-R5`)** : le sommet de `round-two` au moment ou `lea-dev` coupe, note par lui par `git log -1 --format=%h` dans son premier message. **Au 2026-10-16 09:03, apres mes trois fusions, ce sommet est `babd70e7`.** Cet amendement, une fois fusionne, ajoute des commits qui ne touchent que `squad/` ; le SHA du sommet apres cette fusion est dans mon rapport au lanceur, et `lea-dev` coupe de ce sommet. **Tout lot fusionne avant la coupe est dans la base** (`-05` et `-24` ne sont pas fusionnes, et aucun ne l'est avant la coupe si `lea-dev` coupe a l'ouverture) ; **tout lot fusionne apres n'est pas dans le jar rejoue** et force la remesure de la cote (section 9 de l'amendement precedent, inchangee). Verification a la coupe, par `lea-dev` et par moi : `git diff --stat babd70e7 <sommet> -- pom.xml domain application adapters bootstrap` ne rend rien.

### 7. Affectations confirmees

| Developpeur | Postes engages, dans cet ordre | Nommes, non engages |
|---|---|---|
| `marc-dev` | script de `PA1` (`S57-SCRIPT-ADDITIONS-CP`), puis `S57-OBS-SCI-N1-N2`, puis `-05` | p2-10 si la voie est celle des textes |
| `sofia-sm`, casquette developpeuse | `-24`, dans un geste de casquette distinct du Scrum Master, relu par `yanis-qa` avant fusion | - |
| `lea-dev` | forme commune (p2-7) | `RDO-CP-R3` (p2-12), sous la condition de la section 5 de l'amendement precedent, inchangee |

Repertoires, ports et consigne de racine : table de la section 6 de l'amendement precedent, sans changement.

### 8. Questions de `theo-archi` et voies de PA7 et PA8

- **`Q-T14` : engager l'ecriture par `theo-archi` de la garde de `C1'` (amendement d'`ADR-0031`, regle d'`adapter-web`, morsure) avant la fusion de p2-7 ?** **Je ne l'engage pas aujourd'hui et je ne la decide pas.** Capacite : aucun developpeur n'est charge par elle (`theo-archi` l'ecrit lui-meme, ses actions n'ont pas de prix) et elle n'attend personne. Ce qui manque est d'un autre ordre : **il n'existe ni story ni critere d'acceptation** pour cette garde, et un poste engage a un observable (`DEC-0120` A.97, motif 1) ; une story qui n'est pas au backlog n'existe pas. **Route a `camille-po`** : une story, son prix (0 hors `PAR`, ou autre), et si la garde est une condition de la fusion de p2-7. **Route a `theo-archi`** : la forme de la garde, que son avis se reserve. Tant qu'aucun fichier ne la pose en condition, **elle n'en est pas une** et la fusion de p2-7 ne l'attend pas ; elle n'est pas non plus un motif pour retarder l'ouverture du worktree de p2-7, que l'avis ne bloque pas.
- **`Q-T15` : verifier l'existence de sa trace de lecture avant la fusion de p2-7 et de p2-12 ?** **Oui, comme condition de fusion.** C'est ma garde (la lignee et le build sont les miens), pas une decision de produit ni d'architecture. Ma verification : un fichier de `theo-archi` dans ce dossier, qui nomme le commit lu et les points lus (points 1 a 3 de `C1'`, `C2`, `C3`, `C1'` sur le diff livre) ; `git diff --stat <commit lu> <sommet de la branche> -- adapters domain application bootstrap` vide, ou le delta relu et dit. Sans ce fichier je ne fusionne pas, et je ne l'ecris pas a sa place. **`E3` en est la raison** : la lecture du diff de B1 avait manque a la fusion et n'a ete faite qu'apres.
- **`PA8` (`Q-R7`)** : l'avis de `theo-archi` (3.1) classe les trois voies de `RDO-CP-R3` sans choisir ; **la mesure de la voie (i) par `ines-design` n'existe pas** et ouvre ou ferme la voie (ii). p2-12 reste non engage.
- **`PA7`** : le bandeau est refuse, les textes restent ouverts et sans mesure ; **p2-10 reste non engage** : son engagement attend la forme fusionnee (inchange) et la mesure de `ines-design` sur les textes de `nadia-prof`.

### 9. Compte des reports et prix, recomptes sans modifier un lot

**Reports.** Inchange : six premiers reports, comptes a l'ouverture du 2026-10-15 20:56 (`DEC-0120` A.95) : p2-7, p2-8, p2-9, p2-10, p2-11, p2-12. **p2-7, engage ce jour, garde son premier report** (A.83) : non audite a la cloture du palier 2, il glisse (A.72) ; non audite a la cloture du palier 3, c'est un report compte, le second. **Effet de l'engagement** : la clause d'A.95 (*"si `p2-7` n'est pas engagee a l'ouverture du palier 3, ces cinq postes prennent leur second report le meme jour"*) ne joue plus par ce motif. Restent a p2-8, p2-9, p2-10 et p2-11 leurs conditions propres (la forme fusionnee, `PA1`, `PA2`, la voie). **p2-8 a p2-12 restent non engages : cinq premiers reports, et un second report au palier 3 pour chacun qui n'est pas engage a cette ouverture-la.**

**Prix.** Un seul prix change, sur la parole de `nadia-prof` (3.8) : **`VSJ-CE1-R2` de 0,5 a 0,25** (paquet inchange, voie de la forme). Aucun lot n'est modifie. Somme recomptee par `awk` sur les prix de la section 3, sortie lue ce jour : engage 3,75 ; marges 1,50 ; non engage 2,25 ; non affecte 0,50 ; **total 8,00**. Par instance : A 3,0 (engage 1,5 ; marge 0,5 ; non engage 0,75, soit `RCT-CE1-R1` 0,5 et `VSJ-CE1-R2` 0,25 ; **non affecte 0,25**) ; A' 3,0 inchange ; B 1,75 inchange ; hors instance 0,25 inchange. Le 0,25 libere reste non affecte, comme le dit `nadia-prof` ; l'affecter est a `camille-po`. **L'engagement de p2-7 ne change aucune somme** (prix 0, hors `PAR`). Bornes relues : somme 8,0 ; aucun chiffre au-dela de 8,0 ; plafond de 5,0 par instance et trois lots au plus par instance tenus.

### 10. Question a l'utilisatrice : `Q4`, crochet du harnais sur les redirections du shell

**Je ne la decide pas ; elle est a l'utilisatrice.** La consigne de racine du scratchpad (section 6 de l'amendement precedent) n'est pas un moyen de garde. **Avis de `theo-archi`** (`avis-theo-palier-2.md`, section 4), sans decision de sa part :

1. **Un crochet qui lit le texte de la commande** (`>`, `>>`, `2>`, `tee`) enumere des formes d'ecriture et echoue par omission : un script qui ecrit lui-meme, une variable qui porte le chemin, `cd` puis un chemin relatif, un journal de Maven, un profil de Chrome. Il ne le recommande pas comme garde.
2. **Un controle de l'effet** (liste de la racine du scratchpad et du repertoire parent des worktrees avant et apres chaque commande, ecart signale) voit toute ecriture, quel que soit l'outil. Limite : des agents en parallele ecrivent dans la meme fenetre, l'attribution a une commande n'est pas sure, la detection l'est.
3. **Sans crochet**, ma mesure de cloture (section 6 de l'amendement precedent) est le meme controle de l'effet, une fois par palier : elle detecte, elle n'empeche pas, elle n'attribue pas.

**Question** : crochet de lecture de commande (1), controle de l'effet par commande (2), ou rien de plus que ma mesure de cloture (3) ? En attendant, rien ne change. **`S2` reste absent.**

### 11. Ce que cet amendement ne fait pas, decisions

N'ouvre aucun worktree ; ne prononce aucun Done ; ne juge aucun contenu ; ne touche ni la matrice (146 / 200), ni un paquet, ni un catalogue, ni du code ; ne modifie aucun fichier signe d'un autre agent ; ne decide ni `Q4`, ni `Q-T14`, ni la commande de `DEC-0120` A.96.

**Decisions prises** : aucune `ADR` ni `DEC` creee ou amendee par cet amendement. Decisions fusionnees ce jour, par leurs auteurs : `DEC-0120`, amendement du 2026-10-16 (A.94 a A.99), statut `Acceptee` inchange ; `DEC-index.md`, une ligne ajoutee ; aucune `ADR` (`TP3`, `PA5`).

### 12. Jobs de `ci.yml` lances pour ce commit

Lances sur ce worktree, ce fichier indexe, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, ce fichier modifie : `BUILD SUCCESS`, exit 0, **2597 tests, 0 echec, 0 erreur, 0 ignore** (120, 31, 492, 1567, 381, 6 d'integration). Des tests de `bootstrap` lisent `squad/` : le build a lu le fichier avant l'ajout de la presente section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce fichier indexe, passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; le sujet prevu du commit et les sujets de `f6134910..HEAD` (hors fusions) passent dans `verifier-prefixes.sh` : sort 0. L'etape *"Verifier le proprietaire du garde-fou de l'accent"* n'est pas lancee : aucun de ses chemins n'est touche.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 5 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), 12 juges lances avec `python`, 12 sortis 0, aucun balayage vide.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

---

## Amendement du 2026-10-16 11:05 (UTC+02:00), palier 2 : trois fusions, engagement de la garde de `C1'`, etat des postes

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `4004fab7` (`origin/round-two` egal). Aucune ceremonie n'est animee par ce texte : c'est un amendement de planning, comme les deux precedents.

### 1. Fusions de ce jour, lignee verifiee avant chacune

Les trois branches partent de `aa9d703c` (`git merge-base round-two <branche>` rend ce SHA pour chacune, 1 commit propre chacune). La lignee est jugee par `juger_lignee` de `outils/verifier-lignee.sh`, chargee depuis le script et appelee avec le nom de la branche et le code de `git merge-base round-two <branche>` : l'enveloppe du script juge le worktree courant, et la branche a fusionner n'a pas de worktree sur `round-two`. Les trois verdicts : *"descend de la lignee de round-two"*, sortie 0. Reacteur complet apres chaque fusion : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`.

| Ordre | Branche | Sommet livre | Fusion | Tests mesures par moi |
|---|---|---|---|---|
| 1 | `s57-camille-p2-garde` | `79130ca9` | `ea4a9f37` | 2597, `BUILD SUCCESS` |
| 2 | `s57-marc-sci-n1-n2` | `69dd7631` | `9cbf3571` | 2597, `BUILD SUCCESS` |
| 3 | `s57-marc-script-additions-cp` | `baefae1d` | `97a205e8` | 2597, `BUILD SUCCESS` |

**Compte** : 120 + 31 + 492 + 1567 + 381 + 6 d'integration, lignes `Tests run` du reacteur, identique aux trois fusions et au dernier total du palier (2597). Ecart avec les chiffres annonces : aucun, parce qu'aucun des trois lots n'ajoute de test Maven (`marc-dev` annonce 2597 pour p2-6 et p2-5, et 2634 pour p2-3, qui n'est pas fusionne : ses 37 tests s'ajouteront a sa fusion, et le total attendu apres elle est 2634 si rien d'autre n'entre avant). Catalogue : 150 entrees avant et apres (`grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml`), `N1` et `N2` n'y touchent pas.

**Ce que la fusion 2 change dans la base de la cote (`Q-R5`).** La branche de la forme (`s57-lea-forme-commune`) est coupee de `aa9d703c`, comme l'amendement du 09:03 le demandait. `N1` et `N2` modifient quatre lignes de `cm1-sciences-extraire-information.yaml`, sous `adapters/` : elles ne sont pas dans le jar de la forme. A la fusion de p2-7, la commande de la section 9 (`git diff <jar rejoue> <resultat de la fusion> -- adapters domain application bootstrap`) montrera donc ces quatre lignes, plus celles de `-05` et `-24` si elles sont fusionnees avant. **Je les nommerai a `nadia-prof` avant son rejeu de la forme**, comme la section 9 le prescrit ; aucun de ces ecarts ne touche un ecran de CE1 ou de CP.

### 2. Reponses aux questions de `marc-dev` (`rapport-marc-dev-palier-2.md`, sur la branche `s57-marc-fra-ce1-05`)

- **`Q1` (rapport sur une seule branche) : oui, c'est mon processus et je le tranche ainsi.** Trois exemplaires du meme fichier se heurteraient a la fusion (ajout des deux cotes), et un SHA ne peut pas figurer dans le commit qu'il nomme. **Le prix, dit** : le rapport n'est pas sur `round-two` tant que `s57-marc-fra-ce1-05` n'est pas fusionnee. Le critere 1 de `S57-SCRIPT-ADDITIONS-CP` (*"le rapport nomme le script"*) n'est donc pas verifiable aujourd'hui sur `round-two` : **je ne declare pas la story close** avant la fusion de ce rapport. Le script et son juge, eux, sont fusionnes (`git ls-files -- squad/conformite/releves-s57/mesurer-additions-cp.mjs` rend 1).
- **`Q3` (un test qui epingle les quatre textes de `N1` et `N2` est-il voulu ?)** : non, et ce n'est pas a moi de l'amender. Le critere 4 de `S57-OBS-SCI-N1-N2` borne le diff au seul `cm1-sciences-extraire-information.yaml` (*"`git diff --stat <base> HEAD` ne nomme que `cm1-sciences-extraire-information.yaml`, 4 lignes ajoutees et 4 retirees"*), et le critere 5 laisse le test du paquet inchange (*"`PaquetCm1SciencesExtraireInformationTest`, sans assertion retiree (`git diff <base> HEAD -- <son fichier>` vide)"* et *"Aucun test Java n'epingle ces quatre textes"*). Un test qui epingle ces textes violerait l'un des deux. Aucun amendement n'est ecrit ni demande.
- **`Q2`** (orthotypographie des chevrons, U+00A0) : au `relecteur-pedagogique`, avant le rejeu, comme `S57-OBS-SCI-N1-N2` le dit. **`Q4`, `Q5`** : a `camille-po`. **`Q6`** : a `nadia-prof`. Aucune n'est a moi.
- **Dette de duplication nommee** (`mots`, `phrases`, regle de fin de phrase, table d'ouvertures recopies dans `PaquetCe1TexteNarratifTest` et `PaquetCe1PronomsTest`) : je la route a `theo-archi` (structure de test), elle n'est pas payee ici et je n'en fais pas une story.

### 3. Engagement de `S57-GARDE-C1-SUJET-HORS-MESSAGE` (`CR15`, `DEC-0120` A.100 et A.104)

**Engagee ce jour.** Agent `theo-archi`, qui l'ecrit lui-meme ; test des criteres par `yanis-qa` (`CR18`) ; prix 0, hors `PAR` ; aucun developpeur n'est charge ; **aucune condition sur la fusion de p2-7** (A.100), dont la seule condition reste la trace de lecture de `theo-archi` (`Q-T15`, `TP6`). La story est sur `round-two` depuis `ea4a9f37` (`git grep -c "S57-GARDE-C1-SUJET-HORS-MESSAGE" -- docs/backlog/backlog.md` : rend 2) et `DEC-0120` A.100 et A.101 aussi : l'obstacle `Q-T14` est clos par un fichier qui existe, pas par une intention.

- **Verification demandee par A.104** : l'amendement du 09:03, section 8, `Q-T14`, ne nomme pas la story (il ecrit *"il n'existe ni story ni critere"* et route a `camille-po`). Il renvoie bien a elle par sa route, et la story porte son nom. **Ce paragraphe fait le lien** ; la section 8 du 09:03 n'est pas reecrite.
- **Ordre de fusion avec p2-7** : non decide, et non necessaire (A.100, dernier paragraphe). Si la garde est fusionnee la premiere, la fusion de p2-7 la subit et mon `clean verify` la rejoue sur la feuille de la forme (critere 4 de la story) ; sinon la garde s'ecrit contre une feuille qui porte la forme. **Je fusionne dans l'ordre ou les branches arrivent, une a la fois.**
- **Horizon** (A.101) : si la story n'est pas fusionnee a la cloture du palier 2, elle est inscrite en tete du palier 3, avant tout poste qui modifie `recreo.css`. **Report : aucun**, la story n'est retenue par aucune composition. Capacite : aucune charge de developpeur ; si `theo-archi` chiffre une lecture ou un rejeu a `nadia-prof`, c'est a elle de le dire avant le commit.
- **`CR16`** (FM6 dans le commit de `-24`) : a moi, casquette developpeuse. La branche `s57-sofia-fra-ce1-24` porte deja 14 occurrences de l'indice de FM6 (`git grep -c "Le bon mot garde un morceau"` sur la branche : 14). Sa fusion attend `K10` (section 4).

### 4. Etat des postes au 2026-10-16 11:05

Un poste *livre* a un commit sur sa branche. *Fusionne* : sur `round-two`. Aucun Done n'est prononce par ce tableau.

| Poste | Branche et sommet | Etat | Ce qui reste |
|---|---|---|---|
| p2-6, `S57-SCRIPT-ADDITIONS-CP` | `s57-marc-script-additions-cp`, `baefae1d` | **fusionne** (`97a205e8`) | mesure de `PA2` par `ines-design` sur le jar de la base ; critere 1 de la story, qui attend la fusion du rapport (`Q1`) |
| p2-5, `S57-OBS-SCI-N1-N2` | `s57-marc-sci-n1-n2`, `69dd7631` | **fusionne** (`9cbf3571`) | orthotypographie des chevrons par le `relecteur-pedagogique` (`Q2`) ; rejeu 0,25 par `nadia-prof` |
| p2-3, `S57-BR-FRA-CE1-05` | `s57-marc-fra-ce1-05`, `cb50740a` (lot `b9f1c62f` plus le rapport) | **livre, non fusionne** | l'amendement de `PN8` par `camille-po` ; `Q4`, `Q5` ; audit de `nadia-prof` |
| p2-4, `S57-BR-FRA-CE1-24` | `s57-sofia-fra-ce1-24`, `e918f43e` | **livre, non fusionne** | `K10` de `theo-archi` ; relecture de `yanis-qa` avant fusion (section 7 du 09:03) |
| p2-7, forme commune | `s57-lea-forme-commune`, `78fac33d` | **livre, non fusionne** | la trace de lecture de `theo-archi` (`TP6`, `Q-T15`) |
| garde de `C1'` | aucune branche | **engagee** (section 3) | ecriture par `theo-archi` ; test par `yanis-qa` |
| p2-8, p2-9, p2-10, p2-11, p2-12 | - | inchange | la forme fusionnee, `PA2`, `PA7`, `PA8` (amendement du 09:03) |

**Constat sur ce que je n'ai pas fusionne.** `git branch` montre des branches de `theo-archi` (`s57-theo-p2-trace`, `s57-theo-k10-accent`) et de `camille-po` (`s57-camille-p2-pn8`) qui portent un commit chacune. **Je ne les ai pas fusionnees et n'en ai lu que le sujet de commit** : l'ordre de fusion de cette session m'a ete donne par le lanceur, et un fichier sur une branche n'est pas un fichier sur `round-two`. **Je ne clos donc aucun obstacle sur leur existence** : `Q-T15`, `K10` et `PN8` restent ouverts dans cet amendement jusqu'a leur fusion et a ma lecture.

### 5. Poste de mesure et job `outils`

Le juge `outils/mesurer-additions-cp-test.sh` exige `node` et sort en erreur s'il est absent (*"ECHEC node absent : ce juge ne peut pas tourner"*). **Sur ce poste** : `node --version` rend v24.12.0 ; le juge est lance par mon balayage, sortie 0. **Dans la CI** : `.github/workflows/ci.yml` n'installe pas `node` (`grep -n "node\|setup-node" .github/workflows/ci.yml` ne rend aucune ligne d'installation) ; le job `outils` repose donc sur ce que l'image `ubuntu-latest` du runner porte, **et je ne l'ai pas verifie** : la CI distante n'est pas lue. L'absence ferait rougir le job, pas le faire passer a tort (le juge refuse). **Je ne modifie pas `ci.yml`.** **Question routee a `theo-archi` et `yanis-qa`** : declarer `node` dans le job (`actions/setup-node`) ou accepter l'image ? `marc-dev` signale que le mode mesure du script demande le `WebSocket` global (Node 22 ou plus) et que son juge n'utilise que le mode ensemble et les refus ; la version minimale du job est donc une decision de `theo-archi`, pas la mienne.

### 6. Ce que cet amendement ne fait pas

N'ouvre aucun worktree de dev. Ne fusionne ni `s57-marc-fra-ce1-05`, ni `s57-lea-forme-commune`, ni `s57-sofia-fra-ce1-24`. Ne prononce aucun Done, ne juge aucun contenu, ne touche ni la matrice (146 / 200), ni un paquet, ni un catalogue, ni du code. N'amende ni un critere de story ni un prix. Ne modifie pas `ci.yml`. Ne decide ni `Q4`, ni `Q5`, ni `Q-T15`, ni la forme de la garde.

### 7. Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cet amendement.** Decisions fusionnees ce jour, par leur auteur : `DEC-0120`, amendement du 2026-10-16 de `camille-po` (A.100 a A.104), statut `Acceptee` inchange ; `DEC-index.md`, une ligne d'amendement ajoutee. Aucune `ADR`.

### 8. Jobs de `ci.yml` lances pour ce commit

Lances sur l'arbre de `round-two` a `97a205e8` avec ce fichier modifie (sections 1 a 7), avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0, **2597 tests, 0 echec** (120, 31, 492, 1567, 381, 6 d'integration). Le build a lu ce fichier avant l'ajout de la presente section, qui ne contient aucun tableau. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0 (relance apres l'ajout de cette section).
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; les sujets de `4004fab7..HEAD` (hors fusions) et le sujet prevu du commit passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "4004fab7..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances avec `python`, 13 sortis 0, aucun balayage vide ; le treizieme est `outils/mesurer-additions-cp-test.sh`.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-16 12:01 (UTC+02:00), palier 2 : six fusions, branche de `-24` mise a jour, etat des postes

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `fbcf94e9` (`origin/round-two` egal). Aucune ceremonie n'est animee par ce texte : c'est un amendement de planning, comme les precedents. Aucun Done n'est prononce.

### 1. Fusions de ce jour, lignee verifiee avant chacune

Lignee jugee par `juger_lignee` de `outils/verifier-lignee.sh`, chargee depuis le script et appelee avec le nom de la branche et le code de `git merge-base round-two <branche>` (meme raison que l'amendement du 11:05). Les six verdicts : *"descend de la lignee de round-two"*, sortie 0. Reacteur complet : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, `BUILD SUCCESS` a chaque fois qu'il a ete lance.

| Ordre | Branche | Sommet livre | Fusion | Reacteur lance | Tests mesures par moi |
|---|---|---|---|---|---|
| 1 | `s57-theo-k10-accent` | `76642a48` | `1ac68087` | oui | 2599 (`adapter-content` 1569, 1567 avant : +2) |
| 2 | `s57-theo-p2-trace` | `2277f2e3` | `4406f04e` | avec les fusions 3 et 4 | 2599 |
| 3 | `s57-camille-p2-pn8` | `36766dae` | `2764310d` | avec les fusions 2 et 4 | 2599 |
| 4 | `s57-relecteur-p2` | `e7a075ef` | `0e7af3ce` | une seule fois sur l'arbre des fusions 2 a 4 (documents seuls) | 2599 |
| 5 | `s57-lea-forme-commune` | `78fac33d` (code `dc6f423d`) | `aca0d90f` | oui | 2603 (`adapter-web` 496, 492 avant : +4) |
| 6 | `s57-marc-fra-ce1-05` | `cb50740a` (lot `b9f1c62f`) | `edcd3de9` | oui | 2640 (`adapter-content` 1606 : +37) |

**Compte, d'ou vient l'ecart.** `marc-dev` annonce 2634 pour p2-3 : 2597 plus ses 37 tests. Mon total est 2640 : sa base n'etait pas celle de `round-two` apres les fusions 1 et 5, qui ajoutent 2 (garde de l'accent, `K10`) et 4 (forme commune) tests. 2597 + 2 + 4 + 37 = 2640. Repartition finale : 120, 31, 496, 1606, 381, 6 d'integration.

**Fusion 3, conflit.** `docs/backlog/backlog.md` : double ajout en fin de fichier, l'annotation de `s57-camille-p2-garde` (deja fusionnee, `CR15` a `CR18`) et celle de `s57-camille-p2-pn8` (`CR19` a `CR22`). Les deux sont gardees, dans cet ordre, une ligne vide entre elles, aucun marqueur restant (`grep -c` des trois marqueurs : 0), aucune ligne retiree (`git diff HEAD^1 HEAD --stat` de la fusion : 92 insertions, 0 suppression ; la branche en ajoutait 93, une ligne vide n'est pas dupliquee). Aucune assertion n'est touchee : le fichier est un backlog. `CR22` de `camille-po` avait prevu ce conflit et sa resolution.

**Fusion 5, condition de `Q-T15`.** `git diff --stat dc6f423d 78fac33d -- adapters domain application bootstrap pom.xml outils` rend 0 ligne : le code lu par `theo-archi` est le code fusionne (`lecture-theo-p2-7-forme-commune.md`, section 6, sur `round-two` depuis `4406f04e`). **Ecarts `TL9` et `TL10` de `theo-archi`, non corriges ici (cela changerait la tete) et inscrits comme dette :**

- **`TL9`** : la liste des declarations n'est pas fermee par selecteur dans `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` ; une declaration ajoutee dans une regle mesuree passe. **Rattachee a `S57-GARDE-C1-SUJET-HORS-MESSAGE`**, engagee, comme le lanceur de la session l'a demande. **Ecart a dire** : `theo-archi` (section 6 de sa trace) route `TL9` a `lea-dev`, *"dans le prochain commit qui touche la forme, au plus tard celui de p2-10"*. Le rattachement ne change ni les criteres de la story de garde (je ne les amende pas) ni le prix, et ne decide pas qui ecrit : si `theo-archi` garde sa route, `TL9` reste a `lea-dev` et la story de garde la cite seulement. **Je ne clos pas `TL9`.**
- **`TL10`** : la Javadoc de ce test annonce deux feuilles fautives, le code en porte trois (`regle-hors-seuil`, `valeur-changee`, `regle-ajoutee`). Redactionnel, meme route que `TL9` (prochain commit touchant ce test).

**Diff de code contre la base de la cote (`Q-P4`, `aa9d703c`).** Mesure : `git diff --stat aa9d703c <ref> -- adapters domain application bootstrap pom.xml outils`.

- **Apres la fusion 5 (`aca0d90f`)** : 8 fichiers, 406 insertions, 15 suppressions. Hors tests : `recreo.css` (+34, la forme), `cm1-sciences-extraire-information.yaml` (+4, -4 : `N1` et `N2`). Tests : `AucunMotServiNeSEcarteDeSonAccentTest` (+74, `K10`), `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` (+159) et ses trois feuilles fautives, `outils/mesurer-additions-cp-test.sh` (+136).
- **A `edcd3de9`, apres la fusion 6** : 16 fichiers, 1346 insertions, 20 suppressions. S'y ajoutent `catalogue.yaml` (+6), `ce1-pronoms.yaml` (+5, -5), `PaquetCe1PronomsTest` et ses fixtures, la ligne d'inventaire de `TexteAccentueExposeParLePortEntrantTest`. Ces fichiers sont dans `adapters/` ou `bootstrap/` et ne sont pas dans le jar de la forme : je les nommerai a `nadia-prof` avant son rejeu de la forme, comme la section 9 du 20:56 le prescrit. Aucun ne touche un ecran de CE1 ou de CP.

**Fusion 6, `PN8`.** Condition 1 : l'amendement de `PN8` est sur `round-two` (fusion 3, `2764310d`, avant celle-ci). Condition 2 : les six commandes de l'amendement, lancees sur l'arbre fusionne (`<ref>` = `HEAD` a `edcd3de9`), rendent :

| Code | Attendu | Obtenu |
|---|---|---|
| `PN8-1` | 2 | 2 |
| `PN8-2` | `10 13 ` | `10 13 ` |
| `PN8-3` | 0 | 0 |
| `PN8-4` | 5 | 5 |
| `PN8-5` | 0 | 0 |
| `PN8-6` | 5 | 5 (`git diff -U0 edcd3de9^1 edcd3de9 -- ce1-pronoms.yaml`, comptage des `@@`) |

**Ecart de forme sur `PN8-6`** : la commande ecrite dans l'amendement calcule sa base par `git merge-base round-two <ref>`. Une fois la branche fusionnee, cette base est la branche elle-meme et la commande rend 0 (mesure : 0 avec `<ref>` = `cb50740a`). Elle ne vaut qu'avant la fusion ; le 5 ci-dessus est obtenu avec le premier parent de la fusion comme base. Je ne reecris pas la commande de `camille-po` ; je dis seulement qu'elle est a lancer avant la fusion.

**Catalogue** (`grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml`) : `edcd3de9` / 150 avant / entree `ce1-pronoms` (+1) / 151 apres. Avec la fusion de `round-two` dans la branche de `-24` (section 2) : 152.

### 2. Branche `s57-sofia-fra-ce1-24` : `round-two` fusionnee DANS la branche, non fusionnee dans `round-two`

`ADR-0065` A2 point 4. Casquette Scrum Master pour le geste de fusion ; le code de la branche reste celui de la developpeuse, non retouche. Lignee de la branche avant le geste : sortie 0. Fusion de `round-two` (`edcd3de9`) dans la branche : **`6b70d7e5`**, aucun conflit (`catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` ajoutaient chacun a des endroits distincts ; le diff du test entre `edcd3de9` et la branche montre trois ajouts, un par liste, ceux de `-24`, et les trois de `-05` sont deja dans `edcd3de9`). Reacteur complet sur la branche : `BUILD SUCCESS`, **2664 tests** (120, 31, 496, 1630, 381, 6) : 2640 + 24 de `PaquetCe1FamillesDeMotsTest`. Catalogue : 152 entrees. Le rouge de la base (`chaqueMotServiEnProductionPorteSonAccent`, rapporte par la developpeuse) a disparu avec `K10`. **Non fusionnee dans `round-two`** : `yanis-qa` relit la branche d'abord (section 7 du 09:03, precedent du palier 1).

### 3. Obstacles : ce que les fichiers presents sur le disque permettent de clore

- **`Q-T15` / `TP6`** : clos. `lecture-theo-p2-7-forme-commune.md` existe sur `round-two`, verdict *"fusionnable sur `dc6f423d`, tete `78fac33d`"*, delta de code vide verifie par moi.
- **`K10`** : clos. `ADR-0065` amendement A3 existe sur `round-two` ; la branche de `-24`, rouge avant, est verte apres fusion.
- **`PN8`** : clos pour l'amendement (fichier sur `round-two`) et pour la verification des commandes. `Q5` de `marc-dev` trouve sa reponse dans cet amendement ; `Q4` reste a `camille-po`, comme l'amendement du 11:05 le dit.
- **`Q2` (orthotypographie des chevrons)** : le `relecteur-pedagogique` rend *"correct"* sur `N1` et `N2`, sur les items 10, 13 et 15 de `ce1-pronoms`, sur l'item *chat*/*chaton* et les 14 indices de `ce1-familles-de-mots` (`releve-relecteur-palier-2.md`, section 1), aucune correction a appliquer.
- **Ouvert, route** : la section 5 du releve du relecteur (espace avant `?` non uniforme : 2377 lignes a espace insecable, 224 a espace ordinaire, `ADR-0060` consequence assumee 2) est une decision de typographie pour un enfant, **a `theo-archi` pour l'ADR et a `camille-po` pour le produit**. Je ne la tranche pas.
- **Ouvert** : `TL9` et `TL10` (section 1) ; job `outils` et `node` (amendement du 11:05, section 5, route a `theo-archi` et `yanis-qa`, sans reponse).

### 4. Poste de mesure : le juge `mvn-jdk21-test.sh` et `TMPDIR`

`theo-archi` a constate 10 cas en echec quand `TMPDIR` est sous le scratchpad. Mecanisme, mesure : `outils/mvn-jdk21-test.sh` cree tout son banc par `BAC="$(mktemp -d)"` (ligne 23), qui honore `TMPDIR` ; il y place un `mvn` temoin, des JDK jetables et des `HOME` jetables, puis lance l'enveloppe avec `PATH="${BAC}/bin:${PATH}"` (fonction `jouer`). **Quand `TMPDIR` commence par une lettre de lecteur suivie de deux-points et d'une barre oblique (forme mixte de Git Bash), `mktemp -d` rend ce chemin tel quel ; `PATH`, separe par des deux-points, coupe l'entree apres la lettre de lecteur, le `java` et le `mvn` temoins ne sont plus trouves, et les cas qui en dependent echouent.** Mesure : forme mixte, 10 cas en echec et 4 ok, sortie 1 ; forme POSIX (`/c/...`), 14 ok, sortie 0 ; forme avec barres inverses, 14 ok, sortie 0 (je n'ai pas explique pourquoi cette derniere passe). Avec le `TMPDIR` par defaut de la session (`mktemp -d` rend un chemin POSIX sous le repertoire temporaire de l'utilisateur), le juge passe : 14 ok, sortie 0. Les fichiers temporaires sont sous ce repertoire, dans un dossier `tmp.XXXXXXXXXX` retire par le `trap` de la ligne 24. **Je n'ai pas modifie le script.** Question a `yanis-qa` et `theo-archi` : le juge doit-il refuser, ou normaliser, un `TMPDIR` qui contient un deux-points ? Le fichier `ci.yml` tourne sur Linux, ou le cas ne se presente pas.

### 5. Etat des postes au 2026-10-16 12:01

Un poste *livre* a un commit sur sa branche. *Fusionne* : sur `round-two`. Aucun Done n'est prononce par ce tableau.

| Poste | Branche et sommet | Etat | Ce qui reste |
|---|---|---|---|
| p2-6, `S57-SCRIPT-ADDITIONS-CP` | `s57-marc-script-additions-cp` | fusionne (`97a205e8`) ; son rapport est fusionne avec p2-3 (`edcd3de9`) | mesure de `PA2` par `ines-design` ; je verifie le critere 1 de la story sur `round-two` (rapport present) avant de la declarer close |
| p2-5, `S57-OBS-SCI-N1-N2` | `s57-marc-sci-n1-n2` | fusionne (`9cbf3571`) | rejeu 0,25 par `nadia-prof` ; orthotypographie : correct (relecteur) |
| p2-3, `S57-BR-FRA-CE1-05` | `s57-marc-fra-ce1-05` | **fusionne (`edcd3de9`)** | audit de `nadia-prof` ; `Q4` a `camille-po` |
| p2-4, `S57-BR-FRA-CE1-24` | `s57-sofia-fra-ce1-24`, `6b70d7e5` | **livre, `round-two` fusionnee dedans, non fusionne** | relecture de `yanis-qa` avant fusion ; audit de `nadia-prof` |
| p2-7, forme commune | `s57-lea-forme-commune` | **fusionne (`aca0d90f`)** | rejeu de la forme par `nadia-prof` (ecarts de la section 1 a lui nommer) ; mesure de `ines-design` ; `TL9`, `TL10` |
| garde de `C1'` | aucune branche | engagee (11:05) | ecriture par `theo-archi` ; test par `yanis-qa` ; `TL9` rattachee (section 1) |
| `K10`, `ADR-0065` A3 | `s57-theo-k10-accent` | fusionne (`1ac68087`) | aucun |
| p2-8, p2-9, p2-10, p2-11, p2-12 | - | inchange | la forme est fusionnee ; ces postes dependent aussi de `PA2`, `PA7`, `PA8` (amendement du 09:03) |

### 6. Ce que cet amendement ne fait pas

Ne fusionne pas `s57-sofia-fra-ce1-24` dans `round-two`. Ne prononce aucun Done, ne juge aucun contenu, ne touche ni la matrice (146 / 200), ni un paquet, ni un catalogue, ni du code, ni `ci.yml`, ni `mvn-jdk21-test.sh`. N'amende ni un critere de story ni un prix. Ne decide ni `TL9` (qui l'ecrit), ni la typographie de l'espace avant `?`, ni la normalisation de `TMPDIR`.

### 7. Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cet amendement.** Decisions fusionnees ce jour, par leur auteur : `ADR-0065`, amendement A3 de `theo-archi` (statut `Accepte` inchange), `ADR-index.md` modifie en une ligne ; aucune `DEC` (l'annotation de `camille-po` fusionnee au point 3 est une annotation de `docs/backlog/backlog.md`, qui amende `PN8` et `FM5` et ne cree aucun `DEC`).

### 8. Jobs de `ci.yml` lances pour ce commit

Lances sur l'arbre de `round-two` a `edcd3de9` avec ce fichier modifie (sections 1 a 7), avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur l'arbre de `edcd3de9` : `BUILD SUCCESS`, exit 0, **2640 tests, 0 echec** (120, 31, 496, 1606, 381, 6 d'integration). Le build a lu ce fichier avant l'ajout du present amendement. Etape *"Package du fat jar"* non lancee. Meme commande sur la branche de `-24` a `6b70d7e5` : `BUILD SUCCESS`, 2664 tests.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0 (relance apres l'ajout de ce texte).
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh fbcf94e9` sort 0 ; les sujets de `fbcf94e9..HEAD` (hors fusions) et le sujet prevu du commit passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "fbcf94e9..HEAD"` : *"1 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0 (c'est `76642a48`, `K10`).
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide. `outils/mvn-jdk21-test.sh` y est lance avec le `TMPDIR` par defaut de la session (section 4).

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-16 12:46 (UTC+02:00), palier 2 : fusion du rapport qualite de `-24` et du lot `-24`, etat des postes

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `9c271e14` (`origin/round-two` egal). Aucune ceremonie n'est animee par ce texte. Aucun Done n'est prononce.

### 1. Fusions de ce jour

Lignee verifiee avant chaque fusion : `bash outils/verifier-lignee.sh` depuis la racine sort 0 ; `git merge-base round-two 54a606d8` rend `9c271e14` (un seul commit, rapport seul, pose sur la tete de `round-two`) ; `git merge-base --is-ancestor edcd3de9 s57-sofia-fra-ce1-24` sort 0 (la branche de `-24` contient la tete de `-05`). Je n'ai pas lance `verifier-lignee.sh` depuis un worktree de chacune de ces deux branches : je me suis appuyee sur les commandes `git` ci-dessus, qui sont ce que le script appelle.

| Ordre | Branche | Sommet livre | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-yanis-p2-24` | `54a606d8` | `349ec934` | un fichier, `rapport-qualite-lot-24.md`, aucun code |
| 2 | `s57-sofia-fra-ce1-24` | `a742ec8e` (lot `1febd011`, tete relue par `yanis-qa` : `6b70d7e5`) | `9f110983` | le code et le contenu de `6b70d7e5` ; `a742ec8e` ne change que `rapport-sofia-dev-palier-2.md` |

Aucun conflit. `yanis-qa` rend *"fusionnable"* sur `6b70d7e5` ; la fusion 2 ajoute seulement la correction datee de ma casquette de developpeuse.

**`E1` a `E4` du rapport qualite (section 6), sort de chacun.** `E1`, `E2`, `E3` : corriges dans `rapport-sofia-dev-palier-2.md` par une ligne datee (commit `a742ec8e`, casquette developpeuse, sans changement de code). Pour `E3`, j'ai choisi de **dire** *"non garde en Java : `FM5-2` et `FM5-3` le gardent"* et non d'ajouter un test : le perimetre de `FM5` (annotation de `camille-po` du 2026-10-16 10:45) ne demande que des commandes. Un test d'egalite sur l'item 12 serait une decision de `camille-po` ou de `theo-archi`, pas un oubli. **`E4`, route et non ecrit** : l'amendement de `FM3` (le libelle `descripteur.competence` n'est pas servi par les gabarits, mais est inventorie comme chaine exposee ; `fam3` ne lit que les quatre champs de l'enfant) est **attendu de `camille-po` et de `theo-archi`**. Il n'existe pas sur le disque : l'obstacle reste ouvert.

### 2. Mesures sur l'arbre fusionne `9f110983`

- **Reacteur complet** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, `BUILD SUCCESS`, exit 0, 6 modules `SUCCESS`, JDK 21.0.10 (ligne d'enveloppe). **2664 tests, 0 echec, 0 erreur, 0 ignore** : 120, 31, 496, 1630, 381 et 6 d'integration. Recompte a part par l'attribut `tests` des `TEST-*.xml` de chaque module : 2664.
- **D'ou vient l'ecart.** 2664 = 2640 (reacteur a `edcd3de9`, mesure par moi) + 24 (`PaquetCe1FamillesDeMotsTest`, seul fichier de test neuf). Mon rapport de developpeuse annoncait 2621 : 2597 + 24, base d'avant les fusions de `K10` (+2), de la forme commune (+4) et de `-05` (+37). `yanis-qa` mesure 2664 sur `6b70d7e5` ; les deux mesures concordent.
- **Catalogue** (`grep -c "^  - id: "` sur `catalogue.yaml`) : `9f110983` / 151 avant (a `349ec934`) / entree `bandit-manchot-familles-de-mots-ce1` (+1) / **152** apres. `mecanique: bandit-manchot` : 146 avant, 147 apres (le 147 de la consigne du lot est un compte de mecanique, pas d'entrees).
- **Contenu** : `grep -c "paquet: ce1-familles-de-mots$"` sur `catalogue.yaml` : **1** ; `grep -c 'bonneReponse: "librairie"'` sur `ce1-familles-de-mots.yaml` : **0** ; `grep -c 'bonneReponse:'` : 24.
- **`FM6` et `79130ca9`** : `git merge-base --is-ancestor 79130ca9 HEAD` sort 0, la story est sur `round-two`. Le point ouvert 2 de mon rapport de developpeuse est clos.

### 3. Diff de code contre la base de la cote (`aa9d703c`), pour `nadia-prof` avant son rejeu de la forme

Mesure : `git diff --stat aa9d703c 9f110983 -- adapters domain application bootstrap pom.xml outils` : **21 fichiers, 1950 insertions, 37 suppressions**. Nomme, par nature :

- **Forme (ce que regarde le rejeu de `nadia-prof`)** : `adapter-web/.../static/css/recreo.css` (+34). Il n'a pas bouge depuis `aca0d90f` (fusion 5 du 12:01).
- **Contenu servi** : `catalogue.yaml` (+12 : les entrees `ce1-pronoms` et `ce1-familles-de-mots`), `ce1-familles-de-mots.yaml` (item 12 et 14 indices), `ce1-pronoms.yaml` (+5 -5), `cm1-sciences-extraire-information.yaml` (+4 -4). Ces fichiers sont dans le jar ; ils ne portent pas la forme.
- **Tests et jeux fautifs** : `AucunMotServiNeSEcarteDeSonAccentTest` (+74), `PaquetCe1FamillesDeMotsTest` (+493) et ses 3 fichiers fautifs, `PaquetCe1PronomsTest` (+789) et ses 4 fichiers fautifs, `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` (+159) et ses 3 feuilles fautives, `TexteAccentueExposeParLePortEntrantTest` (+18), `outils/mesurer-additions-cp-test.sh` (+136).

Depuis `edcd3de9`, le delta de code est de **7 fichiers, 604 insertions, 17 suppressions** (catalogue, YAML de `-24`, test du paquet et ses 3 fichiers fautifs, inventaire de `bootstrap`).

### 4. Point de depart pour la cote de reference

`ines-design` mesure la cote de pli (744 x 551) **apres la fusion de la garde de `theo-archi`** (`s57-theo-garde-c1`, en cours chez `theo-archi`, **non fusionnee ici**), sur le sommet de `round-two` a ce moment. Je n'ecris pas de SHA : il n'existe pas encore.

### 5. Deux decisions pour la cloture, non prises ici

`yanis-qa` recommande (rapport qualite, sections 4.1 et 4.2) :

1. **Declarer `node` et Python dans le job `outils` de `ci.yml`**, par `actions/setup-node` avec une majeure fixee (et la meme decision pour Python). Aujourd'hui l'absence de `node` rend le juge rouge et bruyant (`ECHEC node absent`), pas silencieux.
2. **Normaliser `TMPDIR` dans `outils/mvn-jdk21-test.sh`** : `BAC="$(cd "$(mktemp -d)" && pwd)"` a la place de `BAC="$(mktemp -d)"`, plutot que de refuser un `TMPDIR` de forme `C:/`. La preuve sera manuelle et datee (aucun cas ne peut rejouer la forme mixte sur Linux).

Ce sont deux decisions, a `theo-archi` (`ADR`), **a prendre a la cloture du palier**. Je ne modifie ni `ci.yml` ni `mvn-jdk21-test.sh`. Pour ce commit, le job `outils` a ete lance avec un `TMPDIR` sous le scratchpad de la session, en forme `/c/...`.

### 6. Etat des postes au 2026-10-16 12:46

| Poste | Branche | Etat | Ce qui reste |
|---|---|---|---|
| p2-4, `S57-BR-FRA-CE1-24` | `s57-sofia-fra-ce1-24` | **fusionne (`9f110983`)** | audit de `nadia-prof` (titre, consigne et place de l'entree sont de ma main, a juger par elle) ; `E4` a `camille-po` et `theo-archi` |
| p2-3, `S57-BR-FRA-CE1-05` | `s57-marc-fra-ce1-05` | fusionne (`edcd3de9`) | audit de `nadia-prof` ; `Q4` a `camille-po` |
| p2-7, forme commune | `s57-lea-forme-commune` | fusionne (`aca0d90f`) | rejeu de la forme par `nadia-prof` (section 3) ; mesure de `ines-design` (section 4) ; `TL9`, `TL10` |
| garde de `C1'` | `s57-theo-garde-c1` | **en cours chez `theo-archi`, non fusionnee** | fusion apres lecture ; test par `yanis-qa` |
| p2-5, p2-6 | - | fusionnes (inchange, amendement du 12:01) | rejeu 0,25 par `nadia-prof` ; mesure de `PA2` par `ines-design` |
| p2-8 a p2-12 | - | inchange | dependent de `PA2`, `PA7`, `PA8` (amendement du 09:03) |

### 7. Ce que cet amendement ne fait pas

Ne fusionne pas `s57-theo-garde-c1`. Ne prononce aucun Done, technique ou pedagogique. Ne juge ni le titre, ni la consigne, ni l'item *chat*/*chaton*. Ne tranche ni `E4`, ni la declaration de `node` et de Python, ni la normalisation de `TMPDIR`. Ne touche ni la matrice, ni `ci.yml`, ni un script.

### 8. Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par cet amendement ni par les deux fusions.** Les deux decisions de la section 5 et l'amendement de `FM3` (`E4`) n'existent pas sur le disque : ils restent des obstacles ouverts.

### 9. Jobs de `ci.yml` lances pour ce commit

Lances sur l'arbre de `round-two` a `9f110983`, avant l'ajout du present amendement (le build ne l'a donc pas lu ; `chemins` est relance apres). Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl` : `BUILD SUCCESS`, exit 0, **2664 tests, 0 echec**. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh` sur `origin/round-two` (`9c271e14`) sort 0 ; les sujets de `9c271e14..HEAD` (hors fusions) passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "9c271e14..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances avec `python`, 13 sortis 0, aucun balayage vide.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-16 13:53 (UTC+02:00), palier 2 : fusion de la garde de `C1'` point 1 et de son rapport qualite, etat de la story

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `9b668a78` (`origin/round-two` egal). Casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. Aucun Done n'est prononce.

### 1. Fusions de ce jour, lignee verifiee avant chacune

`git merge-base round-two s57-theo-garde-c1` rend `9c271e14`, ancetre de `round-two` ; `git log 9c271e14..d9c6cc69` rend deux commits (`b0c47c97`, `d9c6cc69`), tous deux [THEO]. `git merge-base --is-ancestor 9f110983 s57-yanis-p2-garde` sort 0 (la branche de `yanis-qa` est posee sur la tete de `-24`, un commit, rapport seul). Je n'ai pas lance `verifier-lignee.sh` depuis un worktree de chaque branche : ce sont les commandes `git` qu'il appelle.

| Ordre | Branche | Sommet livre | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-theo-garde-c1` | `d9c6cc69` (base `9c271e14`) | `e138ec97` | garde et amendement d'`ADR-0031` (`b0c47c97`), `TL9` et `TL10` (`d9c6cc69`) : tests, un fichier fautif, un `ADR`, une ligne d'index, aucun code de production |
| 2 | `s57-yanis-p2-garde` | `543e7bd0` | `0c0d2142` | un fichier, `rapport-qualite-garde-c1.md`, aucun code |

Aucun conflit. **Condition posee par le lanceur, lue contre `Q-T15`.** `Q-T15` (`avis-theo-palier-2.md`, ligne 131 : *"Avant la fusion de p2-7 et de p2-12"*) vise p2-7 et p2-12, pas la garde : aucune trace de lecture de `theo-archi` n'est exigee pour la fusionner, et il n'en existe pas sur le disque (le seul fichier de lecture du dossier est `lecture-theo-p2-7-forme-commune.md`). Le releve de `yanis-qa` est, lui, exige par la story (`CR18`) : il est fusionne en deuxieme. `Q-T15` reste a lire pour p2-12.

### 2. Code de production : delta vide, et diff de `adapters` contre `aa9d703c`

- **`d9c6cc69` ne touche aucun code de production.** `git diff 9c271e14 d9c6cc69 -- adapters/adapter-web/src/main adapters/adapter-content domain application bootstrap` rend **0 ligne**. Hors `squad/`, le delta est trois fichiers de test d'`adapter-web` (la garde, l'epinglage de la forme et une quatrieme feuille fautive) : 338 insertions, 7 suppressions.
- **Apres fusion**, `git diff 9b668a78 0c0d2142 -- adapters/adapter-web/src/main adapters/adapter-content/src/main domain application bootstrap` rend **0 ligne** : le jar servi est celui de `9b668a78`.
- **Pour `nadia-prof`** : `git diff --stat aa9d703c 0c0d2142 -- adapters domain application bootstrap pom.xml outils` : **23 fichiers, 2284 insertions, 40 suppressions**. Hors tests : `recreo.css` (+34, la forme), `catalogue.yaml` (+12), `ce1-familles-de-mots.yaml`, `ce1-pronoms.yaml`, `cm1-sciences-extraire-information.yaml`. Le reste est des tests et leurs jeux fautifs. **`recreo.css` est identique a celui de `aca0d90f`** : `git diff --quiet aca0d90f 0c0d2142 -- adapters/adapter-web/src/main/resources/static/css/recreo.css` sort 0. Depuis `aca0d90f`, les seuls fichiers de `src/main` qui bougent sont `catalogue.yaml`, `ce1-familles-de-mots.yaml` et `ce1-pronoms.yaml` (34 insertions, 22 suppressions) ; aucune feuille de style ni aucun gabarit n'y change.

### 3. Compte de tests, d'ou vient l'ecart

Reacteur complet sur l'arbre de la fusion 1 (`e138ec97`) : **2669 tests**, 0 echec, 0 erreur, 0 ignore (120, 31, 501, 1630, 381, 6 d'integration ; recompte a part par l'attribut `tests` des `TEST-*.xml`, 2669). `theo-archi` et `yanis-qa` annoncent 2645 sur `d9c6cc69` : c'est 2640 (`9c271e14`) plus 5 (les 4 tests de la garde, 1 de `TL9`). `round-two` porte **24 de plus**, pas 19 : `PaquetCe1FamillesDeMotsTest`, seul fichier de test neuf de `-24`. Les 37 tests de `-05` sont deja dans les 2640. 2640 + 24 + 5 = 2669. `adapter-web` : 496 + 5 = 501, ce que mesure `yanis-qa`.

### 4. Etat de `S57-GARDE-C1-SUJET-HORS-MESSAGE`

**Fusionnee** (`e138ec97`, rapport en `0c0d2142`). Verdicts de `yanis-qa` (`rapport-qualite-garde-c1.md`, section 4), que je relaie sans les juger :

| Critere | Verdict de `yanis-qa` |
|---|---|
| 1, regle de `TP4` | tenu pour la garde seule ; **non discriminant au module sur ce socle** (la meme regle est deja rouge sur `9c271e14` par l'epinglage de la forme) |
| 2, les 4 noms | tenu |
| 3, le residu | **non garde, dit** : `data-oeuvre-revelee` passe la garde ; `grep -c "data-oeuvre-revelee"` sur `ADR-0031` rend 5 (verifie par moi sur l'arbre fusionne) ; la Javadoc le nomme |
| 4, ce qui ne doit pas rougir | tenu |
| 5, perimetre derive | tenu |

Criteres 6 a 8 : non juges ici. Le diff de production est vide (section 2) ; l'`ADR-0031` et le test sont nommes par `git show --stat b0c47c97` ; le compte ArchUnit (41 declarations, 38 `@ArchTest`) est celui que l'`ADR` annonce inchange, je ne l'ai pas recompte. **La story n'est pas close par ce texte** : critere 3 non garde (dit, c'etait l'une des deux issues admises), trous `Z1` a `Z4` ci-dessous ouverts, commande du critere 1 a amender.

**`TL9` et `TL10`.** Livres par `theo-archi` (`d9c6cc69`) : une declaration ajoutee a une regle de la forme rougit (quatrieme feuille fautive), la Javadoc dit quatre feuilles. `yanis-qa` n'a pas mute les tests de cette classe (rapport, section 6). **Je ne les clos pas** : la livraison est sur le disque, la mesure de leur morsure n'y est pas.

### 5. Trous trouves par `yanis-qa`, non corriges ici, routes a `theo-archi` pour le palier 3

Aucun ne bloque la fusion : la garde tient ses criteres, ces cas sont hors de ce qu'elle promettait. **Aucun `ADR` ne les tranche : les quatre restent ouverts**, la route n'en clot aucun.

| Code | Trou | Mesure de `yanis-qa` | Route |
|---|---|---|---|
| `Z1` | une virgule dans `:has(...)` ou `:is(...)` : `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` passe | 501 tests, 0 echec. Cause lue dans le code : `LAnimationRetardeMaisNeDetermineRienTest.java` ligne 217, `entete.split(",")` coupe aussi dans les parentheses ; le decoupage est partage avec cinq autres tests du module et anterieur a la garde | `theo-archi`, palier 3 |
| `Z2` | CSS imbrique : `.ecran-de-jeu { &:has(.est-faux) .propositions { ... } }` passe, un navigateur actuel applique la regle | 501 tests, 0 echec | `theo-archi`, palier 3 |
| `Z3` | sujet dans `retour.html` (`.signal` pose hors region d'annonce) | 0 echec ; limite 4.a de l'`ADR`, dite | `theo-archi`, palier 3 |
| `Z4` | retrait de `role="status"` d'un seul gabarit (`manche.html`) : la garde sort ce gabarit du controle | 0 echec avec une regle sur `.est-faux` (`Z4b`) ; dit par l'`ADR` | `theo-archi`, palier 3 |

Je ne propose aucune correction et ne touche ni le decoupeur ni la garde.

### 6. Commande du critere 1 : routee a `camille-po`

`yanis-qa` propose (rapport, section 2.1) d'ajouter au critere 1 la forme **sans** `:not(...)` ni `@media` : 0 echec sur `9c271e14`, 1 echec avec la garde, pour les quatre noms. C'est un critere de story, donc a `camille-po` : **elle l'ecrit, d'apres le lanceur de la tache** ; je n'ai pas lu cet amendement et je n'amende ni critere ni prix. L'obstacle reste ouvert tant que l'annotation de backlog ou le `DEC` n'existe pas sur le disque.

### 7. Ecarts de methode de `yanis-qa`, relayes

- **Prefixes, rejeu partiel** (rapport, section 5) : `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` et `verifier-garde-fou-accent.sh` non lances ; `yanis-qa` ne declare pas le job vert. Je lance les trois ci-dessous.
- **Fichier temporaire** : le lanceur de la tache signale que `yanis-qa` a cree puis supprime un fichier temporaire hors du depot. Le rapport ne le mentionne pas et je ne l'ai pas verifie ; je le consigne comme dit, sans le qualifier.
- **Critere 1, mesure au module** : le rapport le dit lui-meme (section 2.1). La mesure `BUILD SUCCESS` de la story date de `aa9d703c`, avant l'epinglage de la forme.

### 8. Pour `ines-design`

La cote de pli (744 x 551) se mesure sur le sommet fusionne **`0c0d2142`**. Son code servi est celui de `9b668a78` (section 2, delta de `src/main` vide) et son `recreo.css` celui de `aca0d90f`. Le commit de cet amendement vient apres et ne change que ce fichier ; un fichier ne peut pas porter le SHA de son propre commit, le SHA de la tete poussee est dans le rapport de la session.

### 9. Ce que cet amendement ne fait pas

Ne prononce aucun Done, ne juge aucun contenu, ne corrige ni `Z1` a `Z4`, ni le decoupeur, ni la garde. N'amende ni critere ni prix. Ne touche ni la matrice (146 / 200), ni un paquet, ni un catalogue, ni du code, ni `ci.yml`.

### 10. Decisions prises

**Aucune `ADR` ni `DEC` n'est creee par cet amendement.** Fusionne ce jour, par son auteur : `ADR-0031`, amendement du 2026-10-16 de `theo-archi` (statut `Accepte` inchange, ligne de `ADR-index.md` mise a jour et une ligne d'amendement ajoutee). Aucune `DEC`.

### 11. Jobs de `ci.yml` lances pour ce commit

Lances sur l'arbre de `round-two` a `0c0d2142` avec ce fichier modifie (sections 1 a 10), avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `e138ec97` (section 3), puis sur l'arbre de `0c0d2142` avec ce fichier present. Les deux : `BUILD SUCCESS`, exit 0, **2669 tests, 0 echec** (120, 31, 501, 1630, 381, 6 d'integration), JDK 21.0.10 (ligne d'enveloppe). Le second build a lu ce fichier avant l'ajout de la presente section. Etape *"Package du fat jar"* non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh`, sortie 0, relance apres l'ajout de cette section.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh 9b668a78` sort 0 ; les sujets de `9b668a78..HEAD` (hors fusions : `b0c47c97`, `d9c6cc69`, `543e7bd0`) et le sujet prevu du commit passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "9b668a78..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0. Ces trois etapes sont celles que `yanis-qa` n'avait pas lancees.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide. Ecart a dire : le premier passage a laisse `TMPDIR` a la valeur de l'environnement du shell (`/tmp`, hors de mon espace de travail, ce que la consigne interdisait) ; `outils/mvn-jdk21-test.sh` a ete relance seul avec `TMPDIR` dans mon espace de travail, forme POSIX : sortie 0, repertoire de travail vide ensuite. Les repertoires `tmp.*` qui subsistent dans `/tmp` ne sont pas a moi, je n'y ai pas touche.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

---

## Amendement du 2026-10-16 15:27 (UTC+02:00), palier 2 : cote de reference ecrite, PA2 levee, engagement de p2-8, p2-9 et p2-11

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `2043b3f3` (`origin/round-two` egal), casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. Aucun Done n'est prononce, technique ou pedagogique.

### 1. Fusions de ce jour, lignee verifiee avant chacune

Lignee : `git merge-base round-two s57-camille-p2-garde2` rend `9b668a78`, `git merge-base round-two s57-ines-p2-cote` rend `0c0d2142`, tous deux ancetres de `round-two` (`git merge-base --is-ancestor` sort 0 pour les deux). `bash outils/verifier-lignee.sh` lance depuis le worktree de fusion (branche `s57-sofia-p2-fusion2`, issue de `round-two`) sort 0 ; je ne l'ai pas lance depuis un worktree de chaque branche livree, j'ai lu les deux commandes `git` qu'il appelle. Rapports lus par `git show` avant la fusion.

| Ordre | Branche | Sommet livre | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-camille-p2-garde2` | `d92c839d` | `254221ad` | un fichier, `docs/backlog/backlog.md` (+105), aucun code |
| 2 | `s57-ines-p2-cote` | `96f5e101` | `056f8c88` | 20 fichiers sous `squad/` (+14174) : le fichier de cote, les outils de mesure et les releves `p2-*` de `squad/conformite/releves-s57/` ; rien hors `squad/` |

Aucun conflit. **Chemins et noms de compte** : le balayage des lignes ajoutees par les deux branches (lettres de lecteur, chemins de repertoire personnel, nom de compte) ne rend rien, et le job `chemins` sort 0 apres fusion (section 10). Mon premier balayage avait ramene des faux positifs : le nom de compte, cherche sans bornes, est contenu dans *declarative* et *exclamative*, que les releves citent par milliers ; je l'ai borne par des caracteres non alphabetiques avant de conclure.

**Ce que dit `camille-po`** (annotation du backlog, `d92c839d`), relaye sans le juger : commande du critere 1 de `S57-GARDE-C1-SUJET-HORS-MESSAGE` amendee (regle sans `@media` ni `:not(...)`, 496 tests et 0 echec sur `round-two` a `9b668a78`, 501 tests et 1 echec avec la garde) ; `PN8-6` amendee, base ecrite en SHA (`aa9d703c`), 5 hunks sur les cinq references qu'elle liste ; story `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (`Z1` et `Z2`), prix 0 hors `PAR`, palier 3 ; `Z3` et `Z4` laissees comme limites a declencheur. **Elle propose, et l'engagement est a moi** : la story `Z1`/`Z2` en tete du palier 3, avant tout poste qui modifie `recreo.css`. Je ne l'engage pas ici : le palier 3 n'est pas ouvert, et je note la proposition pour l'amendement qui l'ouvrira.

### 2. Claims de `ines-design` reverifies, sans refaire sa mesure

| Claim du fichier de cote | Ma verification | Sortie lue |
|---|---|---|
| Base `aa9d703c` = parent du premier commit de la forme | `git rev-parse dc6f423d^` et `git rev-parse aa9d703c` | les deux rendent `aa9d703ce1af7cd0c0964ae12e85c6864db0481c` ; `dc6f423d` est le premier commit de la branche, pose sur cette base |
| SHA-256 du jar de base | `sha256sum` du jar laisse dans le worktree de la base | `f4eb0d40...de89`, egal a la section 1 de la cote |
| SHA-256 du jar du sommet | idem, worktree de la cote | `61874630...5ff4`, egal |
| Le jar de base porte la feuille de `aa9d703c` | feuille extraite du module `adapter-web` du jar, retours chariot retires, comparee au SHA-256 de la feuille lue par `git show aa9d703c:<feuille>` | `496b2368...40e6`, egal des deux cotes |
| Le jar du sommet porte la feuille de `0c0d2142` | meme methode | `c312cd1b...3ef6`, egal des deux cotes |
| Le code servi du sommet est celui de `0c0d2142` | `git diff --quiet 0c0d2142 HEAD -- adapters domain application bootstrap pom.xml outils` sur l'arbre fusionne | sort 0 : l'arbre actuel sert le meme code que le jar mesure |

**Limites de ma verification.** Je n'ai ni reconstruit ni rejoue un jar : je confirme que les fichiers identifies existent, que leur empreinte egale celle que la cote declare, et que la feuille qu'ils portent est celle du commit nomme. Je n'ai pas recompte un seul pixel. Le jar de base seul n'est mesure que sur six jeux (cinq de la cote et `additions-cp`) : le reste de l'echantillon compare la feuille de base posee sur le DOM du sommet, ce que le fichier dit (sa section 9).

### 3. Ce que la cote ecrite dit, et ce qu'elle ne mesure pas

**`Q-P4` : non tenue, base fixee.** `git diff --stat 6dbb855d aa9d703c -- pom.xml domain application adapters bootstrap` rend 10 fichiers (en-tetes de dix paquets `ce1-*`) ; sur le sommet, 31. La condition de `nadia-prof` (`reponse-nadia-q-palier-2.md` 3.3) n'est tenue sur aucune des deux bases : la cote de `6dbb855d` n'est pas reprise, **tout est remesure**. Base de la cote : **`aa9d703c`**, parent de `dc6f423d` (section 2). Sommet mesure : **`0c0d2142`**. Le fichier est celui que `Q-R5` et le critere 4 de `S57-FORME-COMMUNE-PROPOSITIONS-LONGUES` demandent, date du 2026-10-16.

**Critere 4, deuxieme commande** (`git diff --stat <base> <commit livre>` ne nomme aucun paquet) : tenu pour `dc6f423d` (5 fichiers), non tenu pour le sommet (22 fichiers du perimetre du jar, dont `catalogue.yaml` et 3 paquets, mais aucun des six paquets de la cote). **La remesure est celle que ma section 9 du 2026-10-15 forcait** (toute fusion apres la coupe). Sur quel jar elle rejoue est a `nadia-prof`.

**Les quatre elements de `Q-R4`**, tels que `ines-design` les ecrit (744 x 551, bas de `.indice` apres une erreur, au plus 551) : element 1, 22 items de `ce1-verbe-sujet` de 534 a 418 - 448 et le groupe *les gateaux* de 486 a 400 ; element 2, 11 items de `ce1-texte-narratif` de 550 a 434 ; element 3, **65 enonces sur 65** d'`additions-cp`, de 486 a 370 ; element 4, 20 items de `comprehension-cm1`, aucun ne perd le pli. Trois jeux de la levee : base 23 sur 72, sommet 72 sur 72, maximum 548. Echantillon apparie : 40 jeux, pli perdu 0 et debordement neuf a droite 0 aux cinq largeurs, 2248 etats par largeur.

**Ce que la cote ne leve pas, dit par elle et relaye ici** :

- `ce1-texte-narratif`, items 13, 17 et 18 : 552 au pire ordre, la forme ne les leve pas d'un pixel. `S57-RCT-CE1-R1` reste ouverte (section 5).
- `comprehension-cm1` : 11 items sur 20 gardent leur indice sous le pli en ordre servi. Ce n'est pas une regression : aucun item qui etait au-dessus ne passe dessous.

**Valeur transitoire non expliquee.** Item 5 de `ce1-accord-groupe-nominal` (*les cheveux longs*), a 744, sur le seul bras du sommet : bas d'indice **355,8** dans les six ordres. Trois reprises donnent 474 a 744 et a 720. **Cause non trouvee**, valeur gardee dans le releve, au-dessus du pli, aucun compte ne change. `nadia-prof` la verra sur son jeu `GNA` : je ne la tiens pas pour reglee.

**Non mesure par `ines-design`** (section 9 de son fichier) : la revelation (mesuree par le pilote, non agregee, hors levee `Q-R2`) ; les largeurs sous 720 px (la forme ne s'y applique pas, rien mesure) ; les polices autres que celle du poste ; les appareils reels, la tablette de 744 x 551, le lecteur d'ecran ; un seul navigateur ; `additions-cp` a une seule largeur et dans l'ordre servi ; les jeux `bandit-manchot` hors des 42 de l'echantillon et des cinq de la cote ; le jar de base joue seul sur l'echantillon. **Un releve ecarte par la faute de l'auteure** (deux pilotes sur un meme port de debogage), non verse, hors de tout chiffre : dit dans son fichier, section 7. Constats hors critere qu'elle ne juge pas : la barre de defilement reste presente a la revelation dans la plupart des etats (matiere de `PA4`) ; l'indice, coupe par le pli a la revelation de `phrases-types-ce1` item 17 ; `Score : 0` sous la bande de l'en-tete (`R3-1`).

**Outils de mesure sans juge.** Les huit scripts `.mjs` et le script `.py` ajoutes sous `squad/conformite/releves-s57/` ne tombent sous aucun des trois balayages du job `outils` (aucun juge, dit par `ines-design`, section 13 de son fichier). Le job `outils` compte donc toujours 13 juges. Je ne l'elargis pas : c'est une question de qualite, routee a `yanis-qa`.

### 4. `PA2` levee ; la regle de non-rejeu avant la cote est satisfaite

**`PA2`** : le fichier date de la cote existe sur le disque (`056f8c88`, `squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cote-de-reference-forme-commune-2026-10-16.md`), avec le commit de la base, la largeur, la hauteur, la methode, les commandes de `Q-R5` et les quatre elements. Je la leve comme prealable de ce planning. **Elle ne dit pas que la forme est bonne** : elle compte des pixels.

**Regle de `nadia-prof`** (`arbitrage-nadia-palier-2.md`, section 8, point 4) : pas de rejeu de la forme commune avant la mesure d'`ines-design` et avant que la cote de `Q-R4` soit ecrite dans un fichier date. Les deux sont sur le disque. **`nadia-prof` peut rejouer `p2-8`, `p2-9`, `p2-10`, `p2-11` et `p2-12` selon leurs engagements** (section 5) ; la regle ne leve aucune des conditions propres de `p2-10` et `p2-12`, qui sont les leurs.

**Jar a rejouer.** Le code servi par la tete de `round-two` apres cette fusion est celui de `0c0d2142` (`git diff --quiet` sur `adapters domain application bootstrap pom.xml outils` sort 0) ; seuls `squad/` et `docs/backlog/` different. Diff de code contre la base de la cote, repris du 13:53 : `git diff --stat aa9d703c 0c0d2142 -- adapters domain application bootstrap pom.xml outils` : 23 fichiers, 2284 insertions, 40 suppressions ; hors tests, `recreo.css` (+34, la forme), `catalogue.yaml` (+12), `ce1-familles-de-mots.yaml`, `ce1-pronoms.yaml`, `cm1-sciences-extraire-information.yaml`. Si `nadia-prof` prefere `dc6f423d`, la seconde commande de `Q-R5` y est tenue (section 3) ; **le choix est le sien**, je ne le fais pas.

### 5. Engagements, capacite uniquement

Je tranche en capacite. Je ne lis pas `PA7` et `PA8` a la place de `nadia-prof`, `theo-archi` et `ines-design` : je lis leurs fichiers, et ce qu'ils ne disent pas reste ouvert.

| Poste | Decision | Developpeur | Rejeu | Prix | Ce que j'ai lu |
|---|---|---|---|---|---|
| p2-8, `TPH-CE1-R1` | **engage** | aucun (la forme est livree, `lea-dev` n'a plus de developpement a porter) | `nadia-prof`, instance A' (`s57-p2-nadia-prof-ap`, 18512, 19212) | 0,5 | forme fusionnee, `PA1` livre, `PA2` levee, `PA3`, `PA4`, `PA5` levees (amendement du 09:03) |
| p2-9, `GNA-CE1-R1` | **engage, apres p2-8**, meme jar | aucun | `nadia-prof`, A' | 0,5 | idem ; jamais avant p2-8 |
| p2-11, `VSJ-CE1-R2` | **engage, voie de la forme** | aucun (paquet inchange) | `nadia-prof`, instance A (`s57-p2-nadia-prof-a`, 18511, 19211) | 0,25 | `Q-S9` tranchee par `nadia-prof` (`reponse-nadia-q-palier-2.md` 3.8) ; les 24 items de `ce1-verbe-sujet` a 418 - 496 sur la cote |
| p2-10, `RCT-CE1-R1` | **non engage** | `marc-dev` si des textes changent (libre : `-05`, `N1`, `N2` et le script sont fusionnes), sinon `lea-dev` | `nadia-prof`, A | 0,5 retenu | `PA7`, ci-dessous |
| p2-12, `RDO-CP-R3` | **non engage** | `lea-dev`, ci-dessous | `nadia-prof`, instance B | 0,5 | `PA8`, ci-dessous |

**p2-9 et le prix.** Le bareme de `nadia-prof` ecrit 0,25 pour `GNA-CE1-R1` quand la forme du paquet ne differe pas de celle de `TPH-CE1-R1`, et 0,5 sinon (`arbitrage-nadia-palier-2.md`, section 5). La composition retient 0,5, le maximum. **Je ne le baisse pas** : un prix est a `nadia-prof` et a `camille-po`. Si elle confirme 0,25, un 0,25 s'ajoute a l'ecart non affecte.

**p2-10 reste non engage, et ce n'est pas la capacite.** `marc-dev` est libre. Ce qui manque, lu dans les fichiers : (1) `nadia-prof` n'accorde pas la regle du bandeau et donne les textes de la voie des propositions plus courtes, **sans choisir la voie** (`reponse-nadia-q-palier-2.md` 3.4 : *"Le choix entre eux reste au developpeur et a `theo-archi`"*) ; (2) `theo-archi` classe les deux voies et laisse le choix a `nadia-prof` pour le bandeau et a elle et au developpeur pour les textes (`avis-theo-palier-2.md` 3.2) ; **aucun fichier ne designe la voie** ; (3) la condition que j'ai ecrite le 2026-10-16 09:03, *"la mesure de `ines-design` sur les textes de `nadia-prof`"*, **n'existe pas** : le fichier de cote constate les trois items a 552 et ne mesure aucun des textes de `nadia-prof` (recherche de la premiere proposition courte, *saute dans une flaque*, dans le fichier de cote : aucune occurrence ; dans les releves `p2-*` elle n'apparait que dans le contenu actuel de l'item 13, *elle saute dans une flaque*). **Pour l'engager** : la voie ecrite par `nadia-prof` et le developpeur, et la mesure de `Q-P5` par `ines-design`. La voie decide aussi du prix (0,25 paquet inchange, 0,5 si des textes changent).

**p2-12 reste non engage.** (1) `PA8` / `Q-R7` : `theo-archi` classe les trois voies de `RDO-CP-R3` et ecrit *"Le choix entre (i), (ii) et (iii) n'est pas le mien"* ; la mesure de la voie (i) qu'il demande a `ines-design` **n'existe pas** (son fichier de cote ne traite pas `RDO-CP-R3`) ; `nadia-prof` n'a pas choisi. (2) `Q-T15` : la trace de lecture de `theo-archi` exigee avant la fusion de p2-12 n'existe pas (le seul fichier de lecture du dossier est `lecture-theo-p2-7-forme-commune.md`) ; elle ne retient pas l'engagement, elle retient la fusion. **Regle ecrite de la section 5 du 2026-10-15** : *"si la forme commune n'est pas fusionnee, je nomme `marc-dev` ... ; je ne reaffecte pas la forme"*. La forme est fusionnee : la condition ne joue plus, **`lea-dev` reste le developpeur nomme de p2-12**, sa chaine critique pour la forme est soldee. Branche `s57-lea-rdo-cp-r3`, repertoire `s57-p2-lea-dev-b`, ports 18531 et 19231, inchanges. Aucun worktree n'est ouvert.

### 6. Ce que `nadia-prof` rejoue, dans cet ordre

1. **p2-8, `TPH-CE1-R1`** (24 items de `ce1-phrases-types`), jar de la tete de `round-two` ou de `dc6f423d` selon son choix. Clause `Q-R4` : la cote ecrite. Conditions qu'elle a posees et regarde au rejeu : le score se lit comme une ligne du jeu avec `R3-1` (`reponse-nadia-q-palier-2.md` 3.1) ; a 720, aucun libelle ne change de nombre de lignes a la revelation (3.2).
2. **p2-9, `GNA-CE1-R1`** (24 items de `ce1-accord-groupe-nominal`), sur le meme jar, apres p2-8. A regarder : l'item 5, valeur transitoire de 355,8 non expliquee (section 3).
3. **p2-11, `VSJ-CE1-R2`** (24 items de `ce1-verbe-sujet`), voie de la forme, sur le meme jar. Elle se rejoue sur le jar ou `TPH-CE1-R1` est leve ou non (`reponse-nadia-q-palier-2.md` 3.8).

**Pas encore** : p2-10 (`PA7`) et p2-12 (`PA8`), section 5. Les non-mesures d'`ines-design` (section 3) sont les siennes a juger au jeu, et les trois items de `ce1-texte-narratif` a 552 ne sont pas leves par la forme.

### 7. Compte des reports (`DEC-0120` A.66, A.72, A.83) et prix

Regles appliquees a la lettre : un poste retenu par la composition et non engage a l'ouverture du palier est un report compte a l'ouverture ; **un engagement tardif n'efface pas le report, il evite le suivant** ; un poste engage et non audite a la cloture du palier glisse (A.72).

| Poste | Compte a l'ouverture du palier 2 | Apres cet amendement |
|---|---|---|
| p2-8 `TPH-CE1-R1` | premier report | engage : le premier report reste ecrit ; non audite a la cloture du palier 2, il glisse ; second report compte seulement a la cloture du palier 3 |
| p2-9 `GNA-CE1-R1` | premier report | idem |
| p2-11 `VSJ-CE1-R2` | premier report | idem |
| p2-10 `RCT-CE1-R1` | premier report | non engage : premier report ; **second report compte a l'ouverture du palier 3 s'il n'est pas engage a ce moment** |
| p2-12 `RDO-CP-R3` | premier report | idem |
| p2-7 forme commune | premier report | fusionne (`aca0d90f`), jugee au rejeu de `TPH-CE1-R1` ; inchange |

**Prix, recomptes sans modifier un lot ni un prix.** Somme par `awk` sur les prix de la section 3 de l'amendement du 2026-10-15 20:56, corriges au 09:03 (p2-11 a 0,25), sortie lue ce jour : engage **5,00** ; marges **1,50** ; non engage **1,00** ; non affecte **0,50** ; **total 8,00**. Par instance : A 3,0 (engage 1,75 ; marge 0,5 ; non engage 0,5, soit `RCT-CE1-R1` ; non affecte 0,25) ; A' 3,0 (engage 2,5 ; marge 0,5 ; non engage 0) ; B 1,75 (engage 0,75 ; marge 0,5 ; non engage 0,5, soit `RDO-CP-R3`) ; hors instance 0,25. Bornes relues : somme 8,0 ; aucun chiffre au-dela ; plafond de 5,0 par instance et trois lots au plus par instance tenus. Le seul mouvement est 1,25 qui passe de non engage a engage (p2-8 0,5, p2-9 0,5, p2-11 0,25).

### 8. Obstacles : ce que les fichiers presents sur le disque permettent de clore

| Obstacle | Fichier qui le tranche | Etat |
|---|---|---|
| `PA2`, cote de reference | `cote-de-reference-forme-commune-2026-10-16.md`, fusionne (`056f8c88`) | **clos** (section 4) |
| `Q-P4` | `reponse-nadia-q-palier-2.md` 3.3 et sections 2 et 3 de la cote | **clos** : condition non tenue, remesure faite |
| Commande du critere 1 de la garde de `C1'` (`CR23`) | annotation du backlog, fusionnee (`254221ad`) | **clos** pour l'ecriture ; la commande reste a rejouer par mutation (`yanis-qa`, `theo-archi`) |
| `PN8-6`, base en SHA (`CR24`, premiere moitie) | meme annotation | **clos** ; `FM5-6` porte la meme expression et reste a amender par `camille-po` |
| Story `Z1` et `Z2` | meme annotation | ecrite ; engagement au palier 3 (section 1) |
| `PA7` (`Q-P5`) | aucun fichier ne designe la voie ni ne mesure les textes | **ouvert** : `nadia-prof`, developpeur et `theo-archi` pour la voie, `ines-design` pour la mesure |
| `PA8` (`Q-R7`) | aucun | **ouvert** : `ines-design` (mesure de la voie (i)), `nadia-prof` et `theo-archi` |
| `Q-T15` pour p2-12 | aucun | ouvert, conditionne la fusion |
| `CR26`, `CR27` | aucun | ouverts, `theo-archi` |
| Outils de mesure sans juge | aucun | **ouvert**, `yanis-qa` |
| `E4` (`FM3`), declaration de `node` et de Python, normalisation de `TMPDIR`, `Q4` | aucun | inchanges, ouverts |

### 9. Ce que cet amendement ne fait pas, decisions

Ne prononce aucun Done, ne juge aucun contenu, ne leve aucune reserve, ne repond ni a `PA7` ni a `PA8` ni a `Q-P4` a la place de leurs titulaires. Ne modifie ni critere ni prix ni lot. N'engage ni p2-10, ni p2-12, ni la story `Z1`/`Z2`. Ne touche ni la matrice (146 / 200), ni un paquet, ni un catalogue, ni du code, ni `ci.yml`. N'ouvre aucun worktree de developpement.

**Decisions prises** : aucune `ADR` ni `DEC` n'est creee ou amendee par cet amendement ni par les deux fusions. L'annotation du backlog de `camille-po` l'ecrit pour elle-meme (*"aucune decision nouvelle n'est inscrite par cette annotation"*) ; le fichier de cote d'`ines-design` n'en ecrit aucune.

### 10. Jobs de `ci.yml` lances pour ce commit

Lances sur le worktree de fusion, tete `056f8c88` plus ce fichier modifie, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `056f8c88` (apres les deux fusions), puis avec ce fichier present. Les deux : `BUILD SUCCESS`, exit 0, **2669 tests, 0 echec, 0 erreur, 0 ignore** (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, recompte a part par l'attribut `tests` des `TEST-*.xml`). JDK 21.0.10 (ligne d'enveloppe). **2669 est attendu** : 2669 sur `0c0d2142`, et les deux fusions ne changent ni code ni test (`git diff --quiet 0c0d2142 HEAD` sur `adapters domain application bootstrap pom.xml outils` sort 0). Le second build a lu ce fichier avant l'ajout de la presente section, qui ne contient aucun tableau. Etape "Package du fat jar" non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, avec les releves d'`ines-design` et ce fichier.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh 2043b3f3` sort 0 ; les sujets de `2043b3f3..HEAD` (hors fusions) et le sujet prevu du commit passent dans `verifier-prefixes.sh`, sortie 0 ; `verifier-garde-fou-accent.sh "2043b3f3..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, avec `TMPDIR` dans mon espace de travail en forme POSIX. Le nombre n'a pas bouge : `ines-design` n'ajoute aucun juge (section 3).

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

---

## Amendement du 2026-10-16 16:59 (UTC+02:00), palier 2 : six fusions, amendement PN8 avant le worktree de marc-dev, engagement tardif de p2-10 et de p2-12, rejeu de `-05`

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `e8d14ddb`, casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. **Aucun Done n'est prononce, technique ou pedagogique** : les verdicts ci-dessous sont ceux de `nadia-prof`, relayes sans les juger.

### 1. Fusions de ce jour, lignee verifiee avant chacune

`git merge-base round-two <branche>` rend `e8d14ddb` pour les six branches, ancetre de `round-two` (`git merge-base --is-ancestor` sort 0). J'ai appele `juger_lignee` de `outils/verifier-lignee.sh` avec le nom de chaque branche et le code de sortie de `git merge-base`, six sorties 0 ; **je ne l'ai pas lance depuis un worktree de chaque branche**, je n'ai pas appele son enveloppe. Rapports lus par `git show` avant chaque fusion.

| Ordre | Branche | Sommet livre | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-nadia-p2-b` | `1ce56426` | `9aa51463` | 2 fichiers : la fiche des sciences CM1 (+65) et `rejeu-nadia-palier-2-b.md` |
| 2 | `s57-nadia-p2-ap` | `8145da31` (contient `610bcba1`, `4097ac13` et `297851f6`) | `9c42046b` | 7 fichiers : quatre fiches de conformite (dont celles des familles de mots et des pronoms, de `camille-po`) et trois rapports de `nadia-prof`, dont `reponse-nadia-pa7-pa8.md` |
| 3 | `s57-nadia-p2-a2` | `e49ca262` (contient `c7a69e60`) | `b2f6da08` | 4 fichiers : la fiche des pronoms (+144), celle du verbe-sujet, deux rapports |
| 4 | `s57-ines-p2-pa78` | `e7c37933` | `e2972e3b` | 31 fichiers sous `squad/` : mesures de `PA7` et `PA8`, releves, outils de mesure ; rien hors `squad/` |
| 5 | `s57-theo-p2-pa78` | `d43e8e96` | `fe3091ce` | un fichier, `avis-theo-pa7-pa8.md` |
| 6 | `s57-camille-p2-pn8b` | `341129f8` | `5a6e94b5` | un fichier, `docs/backlog/backlog.md` (+116) |

**Aucun conflit, aux six fusions.** Le conflit attendu sur la fiche des pronoms (meme ancetre `610bcba1` dans les fusions 2 et 3) n'a pas eu lieu : la fiche fusionnee fait 307 lignes, aucun marqueur de conflit (`grep -c '^<<<<<<<\|^>>>>>>>'` rend 0), la section *Corrections de `nadia-prof`* et le bloc de validation y figurent une fois chacun. Les codes `CR28` a `CR31` de la fusion 6 ne collisionnent avec aucune branche `s57*` (`git show <branche>:docs/backlog/backlog.md` : seule `s57-camille-p2-pn8b` les porte) ni avec `round-two` avant fusion.

**Code** : `git diff --quiet e8d14ddb HEAD -- adapters domain application bootstrap pom.xml outils` sort 0. Les six fusions n'ajoutent aucun code, aucun test, aucun paquet. Le jar servi est celui de `0c0d2142`.

### 2. Ce que disent les verdicts et les avis, relayes

| Poste | Source | Ce que dit l'auteur |
|---|---|---|
| p2-3, lot `-05` | `rejeu-nadia-palier-2-a-lot05.md`, fiche des pronoms | **REFUSE** par `nadia-prof`. Motif unique, `TRV-CE1-6` (roles distribues par genre). `FRA-CE1-05` reste `non couvert`. Cinq items reecrits (3, 9, 13, 18 pour les roles ; 16, *Hamza* devient *Omar*), textes exacts en section 5 de la fiche. Rejeu **0,5**. Item 19 *Nael* : fermee par elle sans correction, *Naël* reste une recommandation non bloquante. Le reste est tenu (pli 20 sur 20, 240 couples sur 240) |
| p2-4, lot `-24` | `rejeu-nadia-palier-2-ap-lot24.md` | **VALIDE**, `FRA-CE1-24`, sans reserve ; 24 items sur 24 au-dessus du pli, bas le plus haut 518. Prix **1,0** |
| p2-5, `N1` et `N2` | `rejeu-nadia-palier-2-b.md` | **prises en compte** ; `SCI-CM1-20` reste `couvert-valide`, aucune ligne ne bouge. Observation sans reserve : a 744 apres une erreur, l'indice finit sous le pli sur les items 9 et 12 ; elle le mesure identique avec les textes d'avant `69dd7631` (`N1` et `N2` ne sont pas la cause). Rejeu **0,25** |
| p2-8, `TPH-CE1-R1` | `rejeu-nadia-palier-2-ap.md` | **levee** ; `FRA-CE1-10` **VALIDE**. Conditions `R3-1` et `PA4` tenues, clause `Q-R4` tenue sur la cote. Prix **0,5** |
| p2-9, `GNA-CE1-R1` | idem | **levee** ; `FRA-CE1-15` **VALIDE**. Prix **0,25** : la forme de `ce1-accord-groupe-nominal` ne differe pas de celle de `ce1-phrases-types` (le bareme de `arbitrage-nadia-palier-2.md` ecrit 0,25, et 0,5 si la forme du paquet differe). Item 5, valeur transitoire de 355,8 : elle mesure 474 dans les six ordres, ne reproduit pas 355,8, cause toujours non trouvee |
| p2-11, `VSJ-CE1-R2` | `rejeu-nadia-palier-2-a.md` | **levee** ; `FRA-CE1-13` **VALIDE**, aucune reserve ouverte ; 24 sur 24, 288 couples sur 288. Prix **0,25**. Elle dit que son verdict vaut pour le jar joue : une modification de `recreo.css` ou du gabarit peut le defaire sans qu'aucun test rougisse |
| `PA7`, `RCT-CE1-R1` | `reponse-nadia-pa7-pa8.md` (`NP1` a `NP3`) | voie des **textes**, mot pour mot ; bandeau **non accorde** ; police qui fait foi : celle que la feuille sert, rendue sur le poste de mesure. Prix **0,5** |
| `PA8`, `RDO-CP-R3` | idem (`NP4` a `NP6`) | voie **(iii)**, phrase plus courte : *"Voici la bonne œuvre. Appuie dessus."* ; (i) et (ii) ecartees ; marge de 0,3 px non acceptable. Prix **0,5** |
| `PA7` et `PA8`, mesures | `mesure-ines-pa7-...` et `mesure-ines-pa8-...` | textes de `nadia-prof` : les trois items a 478, marge de 73 px, 120 couples sur 120 ; bandeau : 548, marge de 3 px. `PA8` : colonne necessaire 262,2 px ; deux combinaisons de (i) tiennent a 720 avec barre. **`ines-design` ne mesure pas la phrase de la voie (iii)** : `nadia-prof` la mesure de sa main (191 px pour une colonne de 229 ou 244, 38 px de marge au pire) |
| `PA7` et `PA8`, avis | `avis-theo-pa7-pa8.md` | `T5` : la voie des textes preserve le mieux l'architecture. `T4` : (iii) a le moins de pieces mobiles, aucun `ADR`, aucune feuille. `T1` : plancher du signal 219,1 px et non 207. `T6` : la police du poste n'est le perimetre d'aucune garantie ecrite, a fermer par une `DEC` de `camille-po` |

**Verifie par moi, pas relaye** : `git grep -n "ecran-deux-oeuvres"` sur les gabarits d'`adapter-web` rend une ligne (`fragments/manche.html:34`) et 28 occurrences dans `recreo.css` : `theo-archi` le dit, le disque le confirme. Les fichiers de test qui citent la phrase actuelle, que `lea-dev` aura a lire (`git grep -l "cette œuvre qui va avec"`) : `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest`, `PartieControllerPropositionsEnImageTest`, `TexteAccentueDesGabaritsTest` (`adapter-web`), `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (`bootstrap`). Le garde-fou de `D.4` est dans le premier.

**Matrice** : je ne la touche pas (146 / 200 au dernier compte de `camille-po`). `FRA-CE1-10`, `-13`, `-15` et `-24` ont un verdict VALIDE de `nadia-prof` ; les passer a `couvert-valide` est le geste de `camille-po`, au verdict. Le statut de tete de la fiche des pronoms (encore *"en attente d'audit"*) lui revient aussi, `nadia-prof` le lui route.

### 3. L'amendement `PN8` precede l'ouverture du worktree de `marc-dev`

| Fait | Valeur lue |
|---|---|
| Commit de l'amendement | `341129f8`, `[CAM]`, `git log -1 --format=%ci` : **2026-10-16 16:41:27 +0200** |
| Fusion sur `round-two` | `5a6e94b5`, **2026-10-16 16:54:18 +0200** |
| Branche ou worktree de la correction de `-05` | **aucun** a cette date (`git branch --list 's57-marc-*'` ne rend aucune branche de correction ; `s57-marc-fra-ce1-05` est la branche livree du palier, fusionnee, **non reutilisee**) |

La clause de `PN8` (*"avant l'ouverture du worktree"*) est tenue pour la correction : le worktree de `marc-dev` s'ouvre apres les deux horodatages. Verification de `camille-po`, a refaire sur le rapport de `marc-dev` : `git reflog show --date=iso <sa branche> | tail -1` porte une heure posterieure a 16:41:27. **Item 19 (`CR29`)** : `camille-po` ne l'a pas repris, il est decline sans amendement ; le texte fusionne l'ecrit (*"Sans amendement, elle est declinee"*, motif : item tenu et ferme par `nadia-prof`). L'annotation fixe `PN8-13a` a 8, `PN8-13b` a 18 et `PN8-6` a 13 ; aucun amendement posterieur ne les change sur l'arbre fusionne. `CR28`, `CR30`, `CR31` restent a leurs titulaires (`yanis-qa`, `relecteur-pedagogique`, `marc-dev`).

### 4. Engagements, capacite uniquement

Je tranche en capacite. Je ne choisis aucune voie et ne fixe aucun prix : je lis ceux de `nadia-prof`, de `theo-archi` et d'`ines-design`.

| Poste | Decision | Developpeur | Rejeu | Prix | Branche, worktree, repertoire, ports |
|---|---|---|---|---|---|
| p2-3, rejeu de `-05` | **engage**, apres la correction | `marc-dev` (la correction : paquet, table de genre, jeu fautif de `TRV-CE1-6`, `PN8-7` a `PN8-14`) | `nadia-prof`, instance A | 0,5 | `s57-marc-fra-ce1-05-corr`, `../recreo-s57-marc-fra-ce1-05-corr` ; `s57-p2-marc-dev-a`, 18521 et 19221 |
| p2-10, `RCT-CE1-R1` | **engage, tardif**, voie des textes | `marc-dev`, apres la correction de `-05` | `nadia-prof`, instance A | 0,5 | `s57-marc-rct-ce1-r1`, `../recreo-s57-marc-rct-ce1-r1` ; `s57-p2-marc-dev-a`, 18521 et 19221 |
| p2-12, `RDO-CP-R3` | **engage, tardif**, voie (iii) | `lea-dev` | `nadia-prof`, instance B | 0,5 | `s57-lea-rdo-cp-r3`, `../recreo-s57-lea-rdo-cp-r3` ; `s57-p2-lea-dev-b`, 18531 et 19231 |

**Ordre de `marc-dev`** : correction de `-05` d'abord, puis `RCT-CE1-R1`. **Un worktree a la fois**, il ne les entrelace pas ; le second s'ouvre quand le premier a livre son commit et son rapport. Pour le rejeu : `nadia-prof` instance A (18511 et 19211) rejoue `-05` puis `RCT-CE1-R1` ; instance B (18513 et 19213) pour `RDO-CP-R3`. Les noms de branche de `-05` et de `RCT` sont **neufs** (aucun n'existe) et s'ajoutent a la table du 2026-10-15 20:56 ; les repertoires et les ports sont ceux de cette table, **inchanges**. Aucun lancement n'a lieu avant le worktree : chaque agent verifie son port libre (`netstat -ano`) avant de lancer.

**Regle de la section 5 du 2026-10-15, appliquee a p2-12** : *"si la forme commune n'est pas fusionnee, je nomme `marc-dev` ... ; je ne reaffecte pas la forme"*. La forme est fusionnee (`aca0d90f`) : la condition ne joue pas, **`lea-dev` reste le developpeur de p2-12**. `lea-dev` et `marc-dev` travaillent en parallele sur des fichiers distincts (gabarit, tests de gabarit et garde-fou de `D.4` d'un cote ; `ce1-pronoms`, son test et `ce1-texte-narratif` de l'autre) : aucun ordre de fusion n'est impose entre eux.

**Ce que chaque developpeur ecrit**, sans que je le modifie :

- `marc-dev`, `-05` : les neuf textes de la section 5 de la fiche, recopies par `camille-po` dans l'amendement fusionne (`PN8-7` a `PN8-14`), commandes lancees sur son commit, jobs de `ci.yml` nommes. `PaquetCe1PronomsTest` et le jeu fautif nomment *Hamza* : trois fichiers changent ensemble.
- `marc-dev`, `RCT-CE1-R1` : les trois items de `ce1-texte-narratif.yaml` de `reponse-nadia-pa7-pa8.md` section 1.4, mot pour mot ; `PaquetCe1TexteNarratifTest` vert dans le meme commit, **sans assertion retiree** (`theo-archi`, `T5`) ; le commentaire de tete, s'il compte quelque chose que ces textes changent.
- `lea-dev`, `RDO-CP-R3` : la phrase *"Voici la bonne œuvre. Appuie dessus."* a la place de la premiere phrase dans `fragments/manche.html` ; l'inventaire de `TexteAccentueDesGabaritsTest` ; **dans le garde-fou de `D.4`, les constantes `PHRASE_DE_M4`, `PREMIERE_PHRASE`, `SECONDE_PHRASE` et les deux comptes litteraux de U+00A0 que `theo-archi` nomme** (`avis-theo-pa7-pa8.md` 2.4). **Sans amendement d'`ADR`** : `theo-archi` ecrit que `D.4` dit la forme de la phrase, pas ses mots. Je n'ai pas ouvert ce garde-fou : les valeurs a ajuster sont celles que `lea-dev` y lit. Compte ArchUnit inchange : 41 declarations, 38 `@ArchTest` (compte de `theo-archi` sur `e8d14ddb`, que je ne recompte pas).

**Ce qui reste a ecrire par d'autres, avant le commit du developpeur** (je n'en fais pas une porte, sauf ou la story l'ecrit) :

- `camille-po` : copier le texte de la voie (iii) dans la story `S57-RDO-CP-R3`, **critere 4 : *"copie mot pour mot ici avant le commit"*** (porte du commit de `lea-dev`, pas de son worktree) ; amender de meme les criteres de `S57-RCT-CE1-R1` avec les textes (demande de `nadia-prof`, `reponse-nadia-pa7-pa8.md` section 4).
- `relecteur-pedagogique` : l'orthotypographie des textes des sections 1.4 et 2.4 de `nadia-prof` et des cinq textes de `-05` avant les rejeux (`CR30`).
- `theo-archi` : `Q-T15` pour la fusion de p2-12, section 7.

### 5. Prix recomptes par instance, sans modifier un lot

Somme par `awk` sur un registre de dix-huit lignes (instance, ligne, prix, etat), sortie lue ce jour. **Le prix de chaque poste est celui de `nadia-prof`** : `-05` 1,0 et rejeu 0,5, `-24` 1,0, `TPH` 0,5, `GNA` 0,25, `VSJ` 0,25, `N1` et `N2` 0,25, `RCT` 0,5, `RDO` 0,5.

| Instance | Total | Consomme | Engage | Marge | Non affecte | Composition du 2026-10-15 |
|---|---|---|---|---|---|---|
| A (groupe X) | 3,25 | 1,25 | 1,50 | 0,50 | 0,00 | 3,0 |
| A' (groupe Y) | 2,75 | 1,75 | 0,50 | 0,50 | 0,00 | 3,0 |
| B | 1,75 | 0,25 | 1,00 | 0,50 | 0,00 | 1,75 |
| hors instance | 0,25 | 0,00 | 0,00 | 0,00 | 0,25 | 0,25 |
| **total** | **8,00** | **3,25** | **3,00** | **1,50** | **0,25** | 8,0 |

- **A** : arbitrage 0,5 ; `-05` 1,0 et rejeu de `-05` 0,5 ; `VSJ-CE1-R2` 0,25 ; `RCT-CE1-R1` 0,5 ; marge 0,5. Consomme : `-05` et `VSJ`. Engage : arbitrage, rejeu, `RCT`.
- **A'** : arbitrage 0,5 ; `-24` 1,0 ; `TPH` 0,5 ; `GNA` 0,25 ; marge 0,5. Consomme : `-24`, `TPH`, `GNA`.
- **B** : arbitrage 0,5 ; `N1` et `N2` 0,25 ; `RDO-CP-R3` 0,5 ; marge 0,5. Consomme : `N1` et `N2`.

**Bornes relues** : somme **8,0** (`PAR-9`) ; plafond de **5,0** par instance (`PAR-8`) : la plus chargee est A a 3,25 ; **trois lots au plus** par instance : A porte un lot (le rejeu n'est pas un lot), A' un lot, B aucun ; marge de 0,5 par instance qui porte un lot ou un rejeu : **intacte aux trois instances**. L'engagement de `RCT-CE1-R1` et de `RDO-CP-R3` n'ajoute rien : leurs 0,5 etaient deja comptes comme non engages (section 7 du 15:27). **Le seul prix neuf est le rejeu de `-05` : 0,5**, et c'est lui qui force la lecture suivante.

**Ce que je retiens, et ce que je ne decide pas.**

1. **`GNA-CE1-R1` a 0,25** : le registre de `nadia-prof` le dit (`arbitrage-nadia-palier-2.md`, tarifs) et son rejeu constate la condition. Je l'ai ecrit a 15:27 : *"Si elle confirme 0,25, un 0,25 s'ajoute a l'ecart non affecte"*. **Je retiens 0,25** pour le compte de capacite. La composition de `camille-po` porte 0,5 (le maximum) ; je ne la reecris pas.
2. **Le rejeu de `-05` (0,5) n'a pas de ligne dans la composition.** Il est pris sur le non affecte : les 0,25 de A (non affecte du 15:27) et les 0,25 rendus par `GNA`. Resultat : A passe de 3,0 a 3,25 et A' de 3,0 a 2,75, **8,0 au total, aucune marge entamee**, non affecte restant 0,25 (hors instance).
3. **Si la lecture 1 n'est pas retenue** (`GNA` reste a 0,5), la somme est **8,25 : `PAR-9` est depassee de 0,25.** **Si la lecture 2 n'est pas retenue**, le rejeu ne peut se prendre que sur la marge de A (0,5) : **elle serait entamee**. Je l'ecris sans trancher.
4. **Ecart de repartition** : la composition fixait A a 3,0 et A' a 3,0. Deplacer 0,25 de A' vers A change le total d'une instance, pas un plafond ; l'amendement de `DEC-0120` qui porte la composition (precedent A.53 a A.62) est a `camille-po`. **Je lui route : confirmer `GNA` a 0,25 et le financement du rejeu de `-05` par le non affecte, ou dire le contraire.** Tant qu'elle ne l'a pas ecrit, l'engagement du rejeu est de capacite seule.
5. **Plus de marge de manoeuvre hors marges** : le non affecte vaut 0,25. Un rejeu qui echoue (celui de `-05` ou celui de `RCT`) ne se paie que sur la marge de son instance, c'est son usage ; je le dis maintenant pour qu'un second refus ne passe pas pour une surprise.

### 6. Compte des reports (`DEC-0120` A.66, A.72, A.83)

| Poste | Compte au 15:27 | Apres cet amendement |
|---|---|---|
| p2-8, p2-9, p2-11 | premier report, engages | audites et leves au palier 2 : le premier report reste ecrit, **aucun glissement, aucun second report** |
| p2-4, p2-5 | engages a l'ouverture, aucun report | audites et valides : rien a compter |
| p2-3, lot `-05` | engage a l'ouverture, aucun report | audite et **refuse** ; le rejeu est engage au palier 2. Non rejoue a la cloture du palier 2, il glisse (A.72) ; je l'ecris ce jour-la |
| p2-10 `RCT-CE1-R1` | premier report, non engage | **engage tardif** : le premier report reste ecrit (A.83) ; non audite a la cloture du palier 2, il glisse ; le second report se compte a la cloture du palier 3 et non a l'ouverture du palier 3 |
| p2-12 `RDO-CP-R3` | premier report, non engage | idem |

### 7. Obstacles : ce que les fichiers presents sur le disque permettent de clore

| Obstacle | Fichier qui le tranche | Etat |
|---|---|---|
| `PA7` (`Q-P5`) | `reponse-nadia-pa7-pa8.md` (voie), `mesure-ines-pa7-...` (mesure), `avis-theo-pa7-pa8.md` (`T5`), tous fusionnes | **clos** pour la voie et la mesure ; criteres de la story a amender (`camille-po`) |
| `PA8` (`Q-R7`) | `reponse-nadia-pa7-pa8.md` (voie (iii)), `mesure-ines-pa8-...`, `avis-theo-pa7-pa8.md` (`T4`) | **clos** pour la voie ; la phrase neuve n'est mesuree que par `nadia-prof`, la levee se juge au rejeu |
| Refus de `-05` sur `TRV-CE1-6` | fiche des pronoms (verdict) et amendement `PN8` fusionne (`5a6e94b5`) | **ecrit** ; l'obstacle reste ouvert tant que le commit corrige et le rejeu n'existent pas |
| `CR29`, item 19 | amendement fusionne, texte *"declinee"* | **clos** |
| `Q-T15` pour p2-12 | aucun : le seul fichier de lecture du dossier est `lecture-theo-p2-7-forme-commune.md` | **ouvert**, conditionne la fusion de p2-12, pas son engagement |
| Dette `T6`, polices imposees hors du perimetre de garantie | aucun ; `theo-archi` propose la formulation : *"La cote d'une forme vaut pour la pile servie rendue sur le poste de mesure ; les polices imposees sont une sensibilite rapportee, pas un critere de levee."* | **ouvert**, route : `DEC` de `camille-po`, la barre reste a `nadia-prof`. Je ne l'ecris pas |
| Constantes de `D.4` pour la phrase neuve | `avis-theo-pa7-pa8.md` 2.4 : aucun amendement d'`ADR` | a `lea-dev`, dans son commit |
| Trous `Z1` a `Z4`, `CR26`, `CR27`, outils de mesure sans juge, `Q4`, `E4` | aucun | inchanges, ouverts |
| Statut de tete de la fiche des pronoms (*"en attente d'audit"*) | aucun | **ouvert**, `camille-po` |

### 8. Etat des postes au 2026-10-16 16:59

| Poste | Etat |
|---|---|
| p2-3, `-05` | audite, **refuse** ; correction engagee (`marc-dev`), rejeu engage (0,5) |
| p2-4, `-24` | audite, **valide** (verdict de `nadia-prof`) |
| p2-5, `N1` et `N2` | rejoue, prises en compte |
| p2-8, `TPH-CE1-R1` et p2-9, `GNA-CE1-R1` | rejoues, levees (verdict de `nadia-prof`) |
| p2-10, `RCT-CE1-R1` | engage tardif, `marc-dev`, apres `-05` |
| p2-11, `VSJ-CE1-R2` | rejoue, levee |
| p2-12, `RDO-CP-R3` | engage tardif, `lea-dev` |
| p2-1, p2-2, p2-6, p2-7 | inchanges par cet amendement |

### 9. Ce que cet amendement ne fait pas, decisions

Ne prononce aucun Done, ne juge aucun contenu, ne choisit aucune voie, ne fixe aucun prix, ne leve aucune reserve. Ne touche ni la matrice, ni un paquet, ni un catalogue, ni du code, ni `ci.yml`, ni un critere de story. N'ouvre aucun worktree. Ne fusionne rien d'autre que les six branches de la section 1.

**Decisions prises** : aucune `ADR` ni `DEC` n'est creee ou amendee par cet amendement ni par les six fusions. `camille-po` ecrit pour elle-meme que son annotation de `PN8` n'amende que la story et la liste des fichiers. Deux `DEC` sont a ecrire par `camille-po` et n'existent pas : la dette `T6` (polices) et l'amendement de composition de la section 5 (point 4).

### 10. Jobs de `ci.yml` lances pour ce commit

Lances sur `round-two` a `5a6e94b5` (apres les six fusions) avec ce fichier modifie, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `fe3091ce` (apres les cinq premieres fusions, `BUILD SUCCESS`, 2669 tests, 6 min 26 s), puis sur `5a6e94b5` avec les sections 1 a 9 de cet amendement presentes (`BUILD SUCCESS`, exit 0, 4 min 38 s). **2669 tests, 0 echec, 0 erreur, 0 ignore** (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, somme par `awk`, recomptee a part par l'attribut `tests` des `TEST-*.xml` : 2669). JDK 21.0.10 (`java.version` de 60 rapports Surefire de `bootstrap`, une seule valeur). **2669 est attendu** : 2669 sur `056f8c88`, et les six fusions ne changent ni code ni test (`git diff --quiet e8d14ddb HEAD` sur `adapters domain application bootstrap pom.xml outils` sort 0). Le second build a lu ce fichier avant l'ajout de la presente section, qui ne contient aucun tableau. Etape "Package du fat jar" non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, avec les mesures et releves d'`ines-design` et ce fichier.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh e8d14ddb` sort 0 ; les sujets de `e8d14ddb..HEAD` hors fusions (dix commits d'agents) et le sujet prevu du commit passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `verifier-garde-fou-accent.sh "e8d14ddb..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, avec `TMPDIR` dans mon espace de travail en forme POSIX. Le nombre n'a pas bouge : `ines-design` ajoute des outils de mesure sans juge (cote du 15:27), ils ne tombent sous aucun balayage.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-16 18:14 (UTC+02:00), palier 2 : fusion de la cloture des amendements de `camille-po`, financement du rejeu de `-05` (lecture de `camille-po` retenue), engagements apres les criteres amendes, `Q-U11` et `Q-N5`

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `78270b4c`, casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. **Aucun Done n'est prononce, technique ou pedagogique.**

### 1. Fusion de ce jour, lignee verifiee avant

| Branche | Sommet livre | Fusion | Delta |
|---|---|---|---|
| `s57-camille-p2-cloture` | `e83fe3f1` (`[CAM]`, 2026-10-16 18:01:35) | `b1b71f80` (2026-10-16 18:07:52) | 6 fichiers, 399 insertions, 8 suppressions : `docs/backlog/backlog.md` (+213), `DEC-0120` (+109), `DEC-0122` (neuf, +59), `DEC-index.md` (+3), `MATRICE-couverture.md` (14 lignes), `FICHE-bandit-manchot-pronoms-ce1.md` (9 lignes) |

- **Lignee.** `git merge-base round-two s57-camille-p2-cloture` rend `78270b4c`, qui est le sommet de `round-two` (`git merge-base --is-ancestor` sort 0). J'ai appele `juger_lignee` de `outils/verifier-lignee.sh` avec le nom de la branche et le code de sortie de `git merge-base` : sortie 0. **Je ne l'ai pas lancee depuis le worktree de la branche**, je n'ai pas appele son enveloppe.
- **Lecture du rapport.** `git show` du commit : message, liste des fichiers, ajouts de `DEC-0120` en entier (A.105 a A.110). L'annotation du backlog est lue en partie (la preuve de `S57-RDO-CP-R3`, le debut de `S57-RCT-CE1-R1`, la story `S57-RETRAIT-PRONOMS-CE1` et le debut de `S57-GARDE-PILE-DE-POLICES`), pas ligne a ligne.
- **Aucun conflit.** La fusion s'est faite sans intervention. `grep -c '^<<<<<<<\|^>>>>>>>'` rend 0 sur le backlog, la matrice et la fiche des pronoms. Les codes `CR32` a `CR38` ne sont portes que par cette branche (`git show <branche>:docs/backlog/backlog.md` sur les branches `s57*`). `DEC-0122` ne collisionne pas : `round-two` portait `DEC-0121` comme dernier numero de la serie.
- **Code.** `git diff --quiet 78270b4c HEAD -- adapters domain application bootstrap pom.xml outils .github` sort 0. La fusion n'ajoute aucun code, aucun test, aucun paquet, aucun catalogue.
- **Chiffre verifie, pas relaye.** La matrice fusionnee, par la commande de la "Methode du compte" (`awk`, lignes de donnees, aucune mesure partielle) : `couvert-valide` 147, `en cours` 4, `non couvert` 49, `non couvrable en l'etat` 77, total 277, soit **147 / 200**. C'est le compte de `camille-po` (A.107). Ma ligne du 16:59, section 2 (*"146 / 200 au dernier compte"*), est depassee.
- **Statuts lus sur le disque.** `DEC-0122` : `Acceptee` (ligne 8). `DEC-0120` : `Acceptee` (ligne 13, amendement A.77), inchange par A.105 a A.110 d'apres le texte de l'amendement et celui de `DEC-index.md`.

**Ecart de mon texte du 16:59, section 2, reconnu.** J'y ecrivais que passer `FRA-CE1-10`, `-13`, `-15` et `-24` a `couvert-valide` etait le geste de `camille-po`. `camille-po` ecrit (A.107) que c'est vrai d'une ligne sur quatre : `-10`, `-13` et `-15` l'etaient deja (au titre d'un `VALIDE SOUS RESERVE`), seule `-24` change de statut. Le compte ci-dessus est coherent avec elle (146 a 147, une ligne).

### 2. Financement du rejeu de `-05` : je retiens la lecture de `camille-po`, qui remplace celle du 16:59

Mon texte du 16:59, section 5, points 2 a 4, prelevait 0,25 sur A' (les 0,25 rendus par `GNA`) et portait A' a 2,75. `camille-po` lit autrement (`DEC-0120` A.105) : `GNA-CE1-R1` vaut 0,25 (prix de `nadia-prof`, constate par son rejeu) ; le rejeu de `-05` (0,5, instance A) est finance par deux quarts qui n'appartiennent a aucun poste : 0,25 de `VSJ-CE1-R2` (inscrit 0,5, facture 0,25, reste dans A) et 0,25 hors instance (l'ecart de 7,75 a 8,0 de sa composition), attribue a A. **A passe a 3,25 ; A' garde 3,0** (2,75 de postes et 0,25 non affecte) ; B ne bouge pas ; hors instance 0,00.

**Je retiens la lecture de `camille-po`.** Le produit et la composition sont a elle : je ne tranche pas entre deux lectures de sa propre composition, et la mienne etait une lecture de capacite ecrite faute de la sienne (16:59, point 4 : *"Je lui route"*). **Reserve** : A.105 laisse a `nadia-prof` la repartition entre instances (`DEC-0120` A.4) et lui demande de dire si l'attribution du quart hors instance a A lui convient. **Sa reponse n'est pas au depot** (`git grep -l "attribution du quart"` ne rend que `DEC-0120`). Sauf objection ecrite de `nadia-prof`, la lecture de `camille-po` s'applique ; si elle objecte, `camille-po` ecrit que la mienne s'applique, et la somme reste 8,0 tant que `GNA` vaut 0,25.

**Recompte par `awk`**, sur un registre de dix-sept lignes (instance, ligne, prix, etat) que j'ai ecrit moi-meme a partir des prix de `nadia-prof`, sans recopier la sortie de `camille-po` ; `wc -l` rend 17. Commande : `awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}' registre.txt | sort`. Sortie lue ce jour : `A 3.25`, `Ap 3.00`, `B 1.75`, `H 0.00`, **`total 8.00`**. Par etat (meme registre, quatrieme champ) : `consomme 3.25`, `engage 3.00`, `marge 1.50`, `non affecte 0.25`. Elle est identique a celle de `camille-po`.

| Instance | Total | Consomme | Engage | Marge | Non affecte | Remplace la ligne du 16:59, section 5 |
|---|---|---|---|---|---|---|
| A (groupe X) | 3,25 | 1,25 | 1,50 | 0,50 | 0,00 | inchangee (3,25) |
| A' (groupe Y) | 3,00 | 1,75 | 0,50 | 0,50 | 0,25 | 2,75, consomme 1,75, engage 0,50, marge 0,50, non affecte 0,00 |
| B | 1,75 | 0,25 | 1,00 | 0,50 | 0,00 | inchangee |
| hors instance | 0,00 | 0,00 | 0,00 | 0,00 | 0,00 | 0,25, non affecte 0,25 |
| **total** | **8,00** | **3,25** | **3,00** | **1,50** | **0,25** | 8,00 |

Bornes : somme **8,0** par palier (`PAR-9`) ; plafond de **5,0** par instance (`PAR-8`), la plus chargee est A a 3,25 ; **trois lots au plus** par instance : A en porte un (`-05`, le rejeu n'est pas un lot), A' un, B aucun ; trois marges de 0,5, **intactes**. **Second refus** : le rejeu de `-05` et celui de `RCT-CE1-R1` sont tous deux dans A, dont la marge est de 0,5 ; **un seul des deux peut echouer sans acte ecrit** (A.105). Le non affecte restant (0,25, dans A') est un reste, pas un moyen de payer un rejeu. Ce que je disais au 16:59, point 5, sur le non affecte vaut donc pour A' et non pour l'ensemble hors instance.

### 3. Engagements : les criteres amendes precedent maintenant les worktrees de `marc-dev` et de `lea-dev`

Capacite uniquement. Je ne change aucune affectation, aucune forme, aucun prix : celles du 16:59, section 4, tiennent.

| Poste | Developpeur | Branche, worktree | Ce que le developpeur lit avant son commit |
|---|---|---|---|
| p2-3, correction de `-05` | `marc-dev`, en premier | `s57-marc-fra-ce1-05-corr`, `../recreo-s57-marc-fra-ce1-05-corr` | `PN8-7` a `PN8-14` (amendement fusionne le 16:54), `CR31`, `FM5-6` reecrite (base ecrite en SHA, `DEC-0120` A.109) |
| p2-10, `RCT-CE1-R1` | `marc-dev`, apres la correction, un worktree a la fois | `s57-marc-rct-ce1-r1`, `../recreo-s57-marc-rct-ce1-r1` | critere 3 remplace (les trois jeux de propositions de `nadia-prof`), mesures `RC1-1` a `RC1-7` |
| p2-12, `RDO-CP-R3` | `lea-dev` | `s57-lea-rdo-cp-r3`, `../recreo-s57-lea-rdo-cp-r3` | critere 4 remplace (la phrase de la voie (iii)), critere 6 precise, critere 7 neuf, mesures `RD3-1` a `RD3-13` |

**Date de l'amendement des stories : le commit `e83fe3f1` porte 2026-10-16 18:01:35 (`git log -1 --format=%cI e83fe3f1`) ; sa fusion sur `round-two`, `b1b71f80`, porte 18:07:52.** Les criteres de `RDO-CP-R3` (critere 4) et de `RCT-CE1-R1` (critere 3) sont donc ecrits avant tout worktree. A ce jour, aucun des trois worktrees n'existe : `git branch --list 's57-marc-fra-ce1-05-corr' 's57-marc-rct-ce1-r1' 's57-lea-rdo-cp-r3'` ne rend rien. **La regle de `DEC-0120` A.108, que je reprends :** le worktree de chaque developpeur s'ouvre apres cet amendement, et `git reflog show --date=iso <branche> | tail -1` porte une heure posterieure a 18:01:35. Une branche qui ne descend pas du sommet de `round-two` poussee ce jour (`git merge-base --is-ancestor <sommet> HEAD`, `<sommet>` = celui donne dans le rapport de ce commit) est provisionnee sur la mauvaise lignee : chaque agent le verifie avec `outils/verifier-lignee.sh` avant d'editer et le dit dans son rapport. Un ecart de calendrier se dit dans le rapport avec l'heure, il ne se tait pas.

**Ce qui reste a ecrire par d'autres avant le commit du developpeur, mis a jour** : `relecteur-pedagogique`, orthotypographie de la phrase de la voie (iii) et des trois jeux de propositions (`CR38`) ; `theo-archi`, `Q-T15` pour la fusion de p2-12. Le critere 4 de `S57-RDO-CP-R3` et le critere 3 de `S57-RCT-CE1-R1`, que le 16:59 listait comme dus par `camille-po`, **sont ecrits** (`e83fe3f1`).

### 4. Questions

| Code | Etat | Detail |
|---|---|---|
| `Q-U11` | **ouverte, reponse pas encore au depot** | Retrait ou fenetre bornee pour `bandit-manchot-pronoms-ce1`, paquet refuse le 2026-10-16 (16:18) et servi depuis (`DEC-0120` A.106). **L'utilisatrice est consultee par l'agent principal ; je ne la pose pas moi-meme.** `git grep -n "Q-U11"` ne rend que la question (backlog, `DEC-0120`, `DEC-index.md`, matrice) : aucune reponse ecrite. Regime par defaut en attendant : le retrait (`DEC-0045` section 3) ; la story `S57-RETRAIT-PRONOMS-CE1` n'attend pas la reponse (A.106). Si l'utilisatrice repond par une fenetre, c'est un arbitrage ecrit **avant** que j'engage la story, avec son echeance (`DEC-0055` 2 et 5) |
| `Q-N5` | **ouverte** | A `nadia-prof` : l'indice des dix items d'affixe de `-24`, ecrire le texte ou retirer la recommandation (backlog, annotation du 17:40 ; `rejeu-nadia-palier-2-ap-lot24.md` 2.3 et section 6). Aucune reponse au depot |
| Repartition entre instances | **ouverte pour `nadia-prof`** | Le quart hors instance attribue a A (section 2). Reponse absente du depot |

### 5. Obstacles : ce que les fichiers presents sur le disque permettent de clore

Mise a jour du tableau du 16:59, section 7.

| Obstacle | Fichier qui le tranche | Etat |
|---|---|---|
| Dette `T6`, polices imposees | `DEC-0122` (`Acceptee`), present sur le disque apres cette fusion | **decision close** ; reste le code : story `S57-GARDE-PILE-DE-POLICES` (hors `PAR`, prix 0, palier 3 au plus tot et avant tout poste qui modifie `recreo.css`). **Je ne l'affecte pas ici** : la forme du garde-fou est a `theo-archi` (`DEC-0120` A.110), son avis n'est pas au depot. L'affectation s'ecrit a l'ouverture du palier 3 |
| Statut de tete de la fiche des pronoms | fiche fusionnee (statut de tete mis a jour par `camille-po`, A.107) | **clos** |
| Amendement de composition (financement du rejeu de `-05`) | `DEC-0120` A.105 | **clos** pour `camille-po` ; reserve de `nadia-prof` sur la repartition, section 2 |
| Paquet refuse de `-05` encore servi | `DEC-0120` A.106 (regime par defaut : retrait) | **decision ecrite** ; execution ouverte : `S57-RETRAIT-PRONOMS-CE1` demande ma mesure de capacite, la mesure de cout de `theo-archi` (absente du depot) et la forme de la branche de rejeu de `-05`, que je n'ecris pas avant son avis ; `Q-U11` ouverte. **Risque nomme par `camille-po`** : un rejeu non fait a la cloture du palier 2 glisse (A.72), et ce retrait augmente ce risque |
| Refus de `-05` sur `TRV-CE1-6` | fiche des pronoms, `PN8` fusionne | ecrit ; **reste ouvert** tant que le commit corrige et le rejeu n'existent pas |
| `Q-T15`, `Z1` a `Z4`, `CR26`, `CR27`, outils de mesure sans juge, `Q4` | aucun | inchanges, ouverts |
| `E4` | `DEC-0120` A.109 (`FM3b` ecrite par `camille-po`) | clos par elle ; `theo-archi` peut l'amender |

### 6. Ce que cet amendement ne fait pas, decisions

Ne prononce aucun Done, ne juge aucun contenu, ne choisit aucune voie, ne fixe aucun prix. N'ouvre aucun worktree. N'affecte ni `S57-RETRAIT-PRONOMS-CE1` ni `S57-GARDE-PILE-DE-POLICES` (section 5). Ne pose pas `Q-U11` a l'utilisatrice. Ne touche ni la matrice, ni un paquet, ni un catalogue, ni du code, ni `ci.yml`, ni un critere de story.

**Decisions** de la fusion : `DEC-0120`, amendee une dix-septieme fois (A.105 a A.110), statut `Acceptee` inchange ; `DEC-0122`, **creee**, `Acceptee` (perimetre de garantie d'une cote : la pile de polices servie, rendue sur le poste de mesure) ; `DEC-index.md`, mis a jour. **Aucune `ADR` n'est creee ou amendee.** Cet amendement-ci ne cree et n'amende aucune decision : les deux `DEC` que le 16:59 (section 9) disait a ecrire par `camille-po`, la dette `T6` et l'amendement de composition, **existent maintenant sur le disque**.

### 7. Jobs de `ci.yml` lances pour ce commit

Lances sur `round-two` a `b1b71f80` (apres la fusion) avec ce fichier modifie, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `b1b71f80` seul (`BUILD SUCCESS`, exit 0, 3 min 42 s), puis avec les sections 1 a 6 de cet amendement presentes (`BUILD SUCCESS`, exit 0, 3 min 43 s). **2669 tests, 0 echec, 0 erreur, 0 ignore** aux deux runs (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, somme par `awk`, recomptee a part par l'attribut `tests` des `TEST-*.xml` : 2669). JDK 21.0.10 (`java.version` de 117 rapports Surefire de `adapter-web` et `bootstrap`, une seule valeur). **2669 etait attendu et c'est le compte du 16:59** : la fusion ne change ni code ni test (`git diff --quiet 78270b4c HEAD -- adapters domain application bootstrap pom.xml outils .github` sort 0), donc aucun ecart a expliquer. Le second run a lu ce fichier avant l'ajout de la presente section, qui ne contient aucun tableau. Etape "Package du fat jar" non lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, avec ce fichier.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh e8d14ddb` sort 0 ; les sujets de `e8d14ddb..HEAD` hors fusions (12 commits d'agents), le sujet de la fusion et le sujet prevu du commit passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `verifier-garde-fou-accent.sh "e8d14ddb..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, avec `TMPDIR` dans mon espace de travail en forme POSIX. Le nombre n'a pas bouge depuis le 16:59.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue.

## Amendement du 2026-10-16 19:20 (UTC+02:00), palier 2 : fusion des corrections de `marc-dev` (`-05` et `RCT-CE1-R1`) et du releve du `relecteur-pedagogique`, fenetre bornee de `Q-U11`, questions de `marc-dev`

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `0ea24709`, casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. **Aucun Done n'est prononce, technique ou pedagogique.**

### 1. Fusions de ce jour, lignee verifiee avant

| Ordre | Branche | Sommet livre | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-marc-fra-ce1-05-corr` | `42071c23` (`[MARC]`) | `02b4a34e` | 3 fichiers, 14 insertions, 14 suppressions : `ce1-pronoms.yaml`, `PaquetCe1PronomsTest.java`, `ce1-pronoms-fautif.yaml` |
| 2 | `s57-marc-rct-ce1-r1` | `ec5b146f` (`c77560f2` pour le contenu, `ec5b146f` pour le rapport) | `4e5c9314` | 2 fichiers, 97 insertions, 5 suppressions : `ce1-texte-narratif.yaml` (5 lignes `-`, 5 `+`), `rapport-marc-dev-palier-2-corrections.md` (neuf, 92 lignes) |
| 3 | `s57-relecteur-p2b` | `14ca643f` (`[REL]`) | `7c9ac220` | 1 fichier neuf, 170 lignes : `releve-relecteur-palier-2-corrections.md` |

- **Lignee.** `git merge-base round-two <branche>` rend `0ea24709` pour les trois (code de sortie 0 ; `git rev-list --count round-two..<branche>` : 1, 2, 1). J'ai appele `juger_lignee` de `outils/verifier-lignee.sh` avec le nom de chaque branche et ce code : trois sorties 0, *"descend de la lignee de round-two"*. **Je ne l'ai pas lancee depuis un worktree de chaque branche**, je n'ai pas appele son enveloppe.
- **Lecture des rapports avant fusion.** `git show` du rapport de `marc-dev` (`ec5b146f`) et du releve du `relecteur-pedagogique` (`14ca643f`), en entier, puis du message et de la liste de fichiers de `42071c23`. Le diff de `c77560f2` n'est pas relu ligne a ligne : ses cinq lignes sont celles que `RC1-4` et `RC1-5` comptent (section 3).
- **Ordre des worktrees de `marc-dev`, verifie.** `git reflog show --date=iso` : `s57-marc-fra-ce1-05-corr` cree a 18:26:28, `s57-marc-rct-ce1-r1` a 18:44:11, tous deux depuis `0ea24709`, apres l'amendement de `PN8` (fusionne a 16:54) et apres le commit des criteres de `RCT-CE1-R1` (`e83fe3f1`, 18:01:35).
- **Aucun conflit.** Les trois fusions se font sans intervention. `git grep -c '^<<<<<<<\|^>>>>>>>' HEAD -- docs squad adapters` ne rend rien.
- **Fichiers non touches.** Ni `catalogue.yaml`, ni la matrice, ni un `pom.xml`.

### 2. Sorties de `PN8-1` a `PN8-14` sur l'arbre fusionne

Bases ecrites en SHA. `<ref>` = `7c9ac220` (sommet apres les trois fusions). Aucune fusion posterieure a la premiere ne touche `ce1-pronoms.yaml` : `git log --oneline e8d14ddb997965536d06689a812a998431ca3938..7c9ac220 -- <YAML>` rend 1 ligne. Commandes de `backlog.md` telles quelles, lancees par un script que j'ai ecrit dans mon espace de travail et que je ne commite pas. **Les sorties sont identiques sur `02b4a34e` (fusion 1 seule) et sur `7c9ac220`.**

| Code | Attendu | Sortie sur `7c9ac220` |
|---|---|---|
| `PN8-1` | 2 | 2 |
| `PN8-2` | `10 13 ` | `10 13 ` |
| `PN8-3` | 0 | 0 |
| `PN8-4` | 5 | 5 |
| `PN8-5` (`PN4`) | 0 | 0 |
| `PN8-6`, base `aa9d703c` | 13 | 13 |
| `PN8-7` (item 3) | 5 | 5 |
| `PN8-8` (item 9) | 5 | 5 |
| `PN8-9` (item 13) | 5 | 5 |
| `PN8-10` (item 16) | 5 | 5 |
| `PN8-11` (item 18) | 5 | 5 |
| `PN8-12` (*Hamza*) | 0 | 0 |
| precondition de `PN8-13` (`git log --oneline e8d14ddb...`) | 1 | 1 |
| `PN8-13a`, base `e8d14ddb997965536d06689a812a998431ca3938` | 8 | 8 |
| `PN8-13b`, meme base | 18 | 18 |
| precondition de la forme a parents (`git rev-list --parents -n1 02b4a34e \| wc -w`) | 3 | 3 |
| `PN8-13a`, forme `02b4a34e^1 02b4a34e` | 8 | 8 |
| `PN8-13b`, forme `02b4a34e^1 02b4a34e` | 18 | 18 |
| `PN8-14` (`bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1PronomsTest -Dsurefire.failIfNoSpecifiedTests=false`, perimetre `adapter-content` et ses amonts, `-am` present, mesure partielle) | `Tests run: 37, Failures: 0, Errors: 0` | `Tests run: 37, Failures: 0, Errors: 0, Skipped: 0`, `BUILD SUCCESS` |

`PN8-6` : la commande a base `aa9d703c` rend 13, valeur amendee par `camille-po`. L'item 19 n'est pas touche (`PN8-13a` rend 8 et non 9).

### 3. Sorties de `RC1-1` a `RC1-7`

`<ref>` = `7c9ac220` pour `RC1-1` a `RC1-6`, `<C>` = `c77560f2` pour `RC1-7` (le commit livre). Base `78270b4c` pour `RC1-4` a `RC1-6`, comme ecrit.

| Code | Attendu | Sortie |
|---|---|---|
| `RC1-1` (Ines) | 3 | 3 |
| `RC1-2` (Ibrahim) | 3 | 3 |
| `RC1-3` (Chloe) | 3 | 3 |
| precondition (`git log --oneline 78270b4c..7c9ac220 -- <YAML> \| wc -l`) | 1 | 1 |
| `RC1-4` | 3 | 3 |
| `RC1-5` | 10 | 10 |
| `RC1-6` | 0 | 0 |
| `RC1-7` | 0 | 0 |
| forme a parents, fusion `4e5c9314` (`rev-list --parents` : 3 mots) : hunks, lignes, `RC1-6` | 3, 10, 0 | 3, 10, 0 |

### 4. Comptes, mesures, jobs de `ci.yml`

Voir section 7, ecrite apres les runs.

### 5. Fenetre bornee de `Q-U11`, questions de `marc-dev`, points du releve

**`Q-U11`, reponse de l'utilisatrice relayee.** L'utilisatrice a repondu le 2026-10-16 a l'agent principal, qui me la transmet : **fenetre bornee**. Le paquet `bandit-manchot-pronoms-ce1` reste au catalogue le temps de la correction et du rejeu dans ce palier ; **pas de retrait** ; `S57-RETRAIT-PRONOMS-CE1` **n'est pas engagee**. **Je n'ai pas lu ces mots de sa main** : c'est le relais de l'agent principal. Cette reponse remplace le regime par defaut que j'ecrivais a 18:14 (retrait, `DEC-0045` section 3). **La correction est fusionnee ce jour (`02b4a34e`) ; le rejeu de `-05` par `nadia-prof` n'a pas eu lieu.** S'il n'a pas lieu avant la cloture du palier 2, **la question du retrait revient au palier 3** : je le dis, je ne la decide pas, et la fenetre ne se prolonge pas d'elle-meme. **Etat de `Q-U11` : reponse consignee ici, pas close.** L'acte qui la tranche est a `camille-po` (amendement de `DEC-0120` ou `DEC`) ; ce fichier n'est pas une decision. `git grep -n "Q-U11" HEAD -- squad/decisions` ne rend aujourd'hui que la question.

**`Q2` de `marc-dev`, routee a `camille-po`, non decidee.** Le jeu fautif de `TRV-CE1-6` (`ce1-pronoms-fautif.yaml`) garde les anciens textes des items 3, 9, 13 et 18 ; seul *Hamza* y change, comme la demande le disait (`git show 42071c23 -- <fautif>` : 2 lignes `-`, 2 `+`, l'item 16). Il atteste un desequilibre de prenoms, pas ces textes. Que la copie suive le paquet est une question de perimetre produit : a `camille-po`. Aucune story n'est creee ici.

**`Q1` de `marc-dev`, close.** U+00A0 dans les chevrons et devant le deux-points de l'indice de l'item 9 : confirme par le `relecteur-pedagogique` (releve, section 4 : `ADR-0060` clause 2, amendement du 2026-10-14 d'`ADR-0067`, critere `PN8-8`). Le releve existe sur le disque apres la fusion 3.

**Item 19 (`CR29`), decline.** Aucun amendement de `camille-po` ne l'a repris avant l'ouverture du worktree : `git grep -n "CR29" HEAD -- docs` ne rend que l'annotation initiale, sans amendement posterieur. Par le texte de cette annotation, *"sans amendement, elle est declinee"*. `PN8-13a` rend 8. C'est un constat de mesure, pas une decision de ma part.

**Point de fusion du releve (section 6), sans objet.** Le `relecteur-pedagogique` lisait la section 5 de la fiche des pronoms sur `s57-nadia-p2-a2` (`e49ca262`). `git merge-base --is-ancestor e49ca262 0ea24709` sort 0 : le commit est deja sur `round-two`.

**Point ouvert du releve, section 5, inchange.** 2377 occurrences de `U+00A0` avant `?` contre 224 a espace ordinaire dans les contenus : ni le fait de ces deux commits, ni de moi. A `theo-archi` (`ADR`) et `camille-po` (produit), deja ouvert.

**Branche de `lea-dev`, non fusionnee.** `s57-lea-rdo-cp-r3` attend la trace de lecture de `theo-archi` (`Q-T15`, en cours). Son sommet est `5a1d76fa` (rapport de livraison, 19:07:33), au-dessus de `23222465` (18:41:11), que le releve a lu. **Le releve ne couvre donc pas `5a1d76fa`**, un rapport et non un texte de gabarit ; je ne l'ai pas lu.

### 6. Etat des postes de rejeu et sommet pour `nadia-prof`

| Poste | Etat | Preuve |
|---|---|---|
| p2-3, correction de `-05` | **fusionne** | `02b4a34e` ; `PN8-1` a `PN8-13` ci-dessus |
| p2-10, `RCT-CE1-R1` | **fusionne** | `4e5c9314` ; `RC1-1` a `RC1-7` ci-dessus |
| p2-3, rejeu de `-05` (0,5, A) | **a faire**, `nadia-prof` | aucun fichier de rejeu apres la correction |
| p2-10, rejeu de `RCT-CE1-R1` (0,5, A) | **a faire**, `nadia-prof` | idem |
| p2-12, `RDO-CP-R3` | **non fusionne** | `Q-T15` |

**Sommet de rejeu.** Le code et le contenu servis de `round-two` sont ceux de `7c9ac220` : le commit de cet amendement ne change ni `adapters`, ni `domain`, ni `application`, ni `bootstrap`, ni `pom.xml` (`git diff --quiet 7c9ac220 HEAD -- adapters domain application bootstrap pom.xml outils .github`, section 7). Un jar construit sur le sommet de `round-two` apres ce commit rejoue donc la correction de `-05` et `RCT-CE1-R1`. Le SHA du sommet est dans le rapport de l'agent : un fichier ne peut pas contenir le SHA de son propre commit.

**Decisions de cette serie.** **Aucune `ADR` ni aucune `DEC` n'est creee ou amendee** par ces trois fusions ni par cet amendement. `DEC-0120` (dix-sept amendements, A.105 a A.110 les derniers) et `DEC-0122` sont inchanges.

**Ce que cet amendement ne fait pas.** Ne prononce aucun Done, ne juge aucun contenu ni la typographie, ne decide ni `Q-U11` ni `Q2`, n'engage pas `S57-RETRAIT-PRONOMS-CE1`, ne fusionne pas `s57-lea-rdo-cp-r3`, ne touche ni la matrice (147 / 200, non remesuree), ni un catalogue, ni un critere.

### 7. Total recompte, jobs de `ci.yml` lances pour ce commit

Lances sur `round-two` a `7c9ac220` (apres les trois fusions), puis avec les sections 1 a 6 de cet amendement presentes, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `7c9ac220` seul (`BUILD SUCCESS`, exit 0, 4 min 50 s), puis avec les sections 1 a 6 presentes (`BUILD SUCCESS`, exit 0, 4 min 48 s). **2669 tests, 0 echec, 0 erreur, 0 ignore** aux deux runs (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, somme faite par moi, recomptee a part par l'attribut `tests` des 313 fichiers `TEST-*.xml` : 2669). JDK 21.0.10 (`java.version` lu dans les 60 rapports Surefire de `bootstrap`, une seule valeur). `PaquetCe1PronomsTest` : 37 ; `PaquetCe1TexteNarratifTest` : 32. L'etape *"Package du fat jar"* n'est pas lancee.
- **Ecart de total.** Les deux branches de `marc-dev` annoncent chacune 2669 (base plus leur lot, delta nul : *"37 avant, 37 apres"* et *"32 avant, 32 apres"*). L'arbre fusionne donne 2669, soit le total de `round-two` a `b1b71f80` (2669, amendement de 18:14) : aucun test n'est ajoute ni retire par ces trois fusions, et aucun ecart n'est a expliquer.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, avec les sections 1 a 6 de ce fichier.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh 0ea24709` sort 0 ; les sujets de `0ea24709..HEAD` hors fusions (quatre commits d'agents) et le sujet prevu du commit passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `verifier-garde-fou-accent.sh "0ea24709..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, `TMPDIR` dans mon espace de travail en forme POSIX.
- **Code inchange par ce commit.** `git diff --quiet 7c9ac220 -- adapters domain application bootstrap pom.xml outils .github` sort 0.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. **Le Done technique n'est pas prononce par moi** : il est juge par la CI sur le commit pousse.

## Amendement du 2026-10-16 19:40 (UTC+02:00), palier 2 : fusion de la trace de lecture de `theo-archi` (`Q-T15`) et de `RDO-CP-R3`, dette de commentaires `RL5` a `RL7`

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement remplace ou precise est nomme. Arbre de depart : `round-two` a `1cf4e3a9`, casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. **Aucun Done n'est prononce, technique ou pedagogique.**

### 1. Fusions, lignee verifiee avant

| Ordre | Branche | Sommet | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-theo-p2-12` | `c703c8c6` (`[THEO]`) | `5d07c1bd` | 1 fichier neuf, 125 lignes : `lecture-theo-p2-12-rdo-cp-r3.md` |
| 2 | `s57-lea-rdo-cp-r3` | `5a1d76fa` (code de `23222465`, rapport de `lea-dev` au-dessus) | `19c8a7b2` | 6 fichiers, 93 insertions, 17 suppressions : `manche.html`, quatre tests, `rapport-lea-dev-palier-2-rdo-cp-r3.md` (neuf, 76 lignes) |

- **Lignee.** `git merge-base round-two <branche>` rend `0ea24709` pour les deux ; `git rev-list --count round-two..<branche>` : 1 et 2. `juger_lignee` de `outils/verifier-lignee.sh`, appelee avec le nom de la branche et le code de `git merge-base` : deux sorties 0. **Je ne l'ai pas lancee depuis un worktree de chaque branche**, je n'ai pas appele son enveloppe.
- **Lecture avant fusion.** `git show` de la trace de `theo-archi` et du rapport de `lea-dev`, en entier. Je n'ai pas relu le diff de `23222465` ligne a ligne : j'ai lu son resume (`git diff --stat`) et rejoue les commandes `RD3`.
- **Condition de `Q-T15`.** La trace nomme le commit lu, `23222465`, et rend le verdict *fusionnable*. Elle donne un diff de code vide entre `23222465` et `5a1d76fa`. **Je l'ai refait** : `git diff --stat 23222465 5a1d76fa -- adapters domain application bootstrap pom.xml outils .github` ne rend aucune ligne, et le meme `git diff --stat` sans filtre rend 1 fichier, 76 insertions (le rapport).
- **Aucun conflit** : `git grep -c '^<<<<<<<\|^>>>>>>>' HEAD -- docs squad adapters` ne rend rien.
- **Ecart de nommage entre la trace et la story.** `Q-T15` demandait une trace *"avec un diff de code vide entre ce commit et la tete de branche"* ; la tete `5a1d76fa` est un rapport seul, comme la trace le dit. Aucun ecart.

### 2. Sorties de `RD3-1` a `RD3-13` sur l'arbre fusionne

`<ref>` = `19c8a7b2` (sommet apres les deux fusions), `<C>` = `23222465`, `<F>` = `19c8a7b2`. `G` et `T1` comme ecrits par la story. Commandes de `backlog.md` telles quelles, par un script de mon espace de travail que je ne commite pas.

| Code | Attendu | Sortie |
|---|---|---|
| `RD3-1` | 1 | 1 |
| `RD3-2` | 0 | 0 |
| `RD3-3` | 0 | 0 |
| `RD3-4` | 0 | 0 |
| `RD3-5` | 3 | 3 |
| `RD3-6` sur `<C>` | 5 | 5 |
| `RD3-7` sur `<C>` | 0 | 0 |
| `RD3-8` sur `<C>` | 0 | 0 |
| precondition de `RD3-9`/`RD3-10` (`git log --oneline 78270b4c..<ref> -- G \| wc -l`) | 1 | 1 |
| `RD3-9`, base `78270b4c` | 2 | 2 |
| `RD3-10`, base `78270b4c` | 0 | 0 |
| precondition de la forme a parents (`git rev-list --parents -n1 <F> \| wc -w`) | 3 | 3 |
| `RD3-9`, forme `<F>^1 <F>` | 2 | 2 |
| `RD3-10`, forme `<F>^1 <F>` | 0 | 0 |
| `RD3-7`, forme fusion (`static/css/`) ; fichiers hors `squad/` | 0 ; 5 | 0 ; 5 |
| `RD3-11` | 9 | 9 |
| `RD3-12` | 6 | 6 |
| `RD3-13` | 2 | 2 |

**Ce que ces chiffres ne disent pas** : `RD3-1` a `RD3-5` et `RD3-11` a `RD3-13` valent pour n'importe quel commit ; `RD3-9` et `RD3-10` ne valent sur `round-two` que tant qu'aucun autre commit ne touche `G` (precondition a 1). **Apres le commit de commentaires de la section 4, ils se lisent par la forme a parents sur `19c8a7b2`**, pas sur `round-two`.

### 3. Decisions de cette serie

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee** par ces deux fusions ni par cet amendement. `DEC-0120` et `DEC-0122` sont inchanges. `Q-T15` est close par un fichier present sur le disque : `lecture-theo-p2-12-rdo-cp-r3.md`.

### 4. Dette `RL5`, `RL6`, `RL7` : trois commentaires devenus faux, routee, engagee

Ecrite par `theo-archi` (trace, section 8) ; `lea-dev` n'en nommait que deux. **Je les ai relus sur l'arbre fusionne**, ils y sont tels que la trace les dit :

| Code | Fichier, lignes | Ce qui est faux |
|---|---|---|
| `RL5` | `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`, 207 a 213 | *"cette phrase, de nadia-prof (M4)"* : la source est `reponse-nadia-pa7-pa8.md` 2.4 |
| `RL6` | `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TexteAccentueDesGabaritsTest.java`, 158 a 160 | cite `ARBITRAGE-PREALABLE-s55` section 3.2, *"copie tel quel"* : cet arbitrage porte l'ancienne phrase |
| `RL7` | `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java`, 107 (le commentaire ; la constante est ligne 108) | *"M4 du meme arbitrage, mot pour mot"* |

**Pas corrigees avant la fusion**, motifs de `theo-archi` repris : `RD3-9` casserait, et un commit de plus sur la branche rouvrirait `Q-T15`. Elles sont **ecrites ici comme dette routee**, a `theo-archi` pour la forme (sa proposition : un seul commit, sur ces trois fichiers, qui ne touche que ces trois commentaires, cite `reponse-nadia-pa7-pa8.md` 2.4, apres la fusion et avant la cloture du palier 2) et a moi pour la capacite.

**Capacite : j'engage ce commit, casquette developpeuse, dans une tache distincte de celle-ci.** Motifs : (1) il n'y a aucun choix de produit a faire, seulement l'exactitude d'une citation ; (2) `lea-dev` est l'autrice du gabarit, mais son poste p2-12 est fusionne, comme les deux postes de `marc-dev` ; je n'ai aucune mesure de charge plus fine qui designe l'un des trois, et je ne prends pas ce commit pour dire que l'un d'eux est plus charge ; (3) la trace de `theo-archi` est le diagnostic, il n'y a rien a decouvrir. Ce que j'ecris ici est l'engagement, **le commit n'existe pas**. **Je ne le fais pas sous cette casquette** : l'agent principal doit me le confier sous la casquette developpeuse. **Je ne fixe aucun prix** : le prix d'un poste est a `camille-po` (`PAR`), et ce commit n'entre dans aucun lot ; si elle veut le chiffrer, elle l'ecrit.

**Conditions que le developpeur lira avant son commit** (aucune ne decide quoi que ce soit) : (a) trois fichiers, trois commentaires, aucune autre ligne ; (b) `RD3-1` a `RD3-5` et `RD3-11` a `RD3-13` inchanges ; `RD3-9` et `RD3-10` lus par la forme a parents sur `19c8a7b2` ; (c) **a mesurer avant d'ecrire** : le commentaire de `manche.html` est un commentaire HTML ordinaire (`<!-- ... -->`). Je n'ai pas verifie s'il est servi dans le HTML rendu. **S'il l'est, un commit de commentaires change l'octet servi, et un jar construit apres lui n'est plus celui que `nadia-prof` rejoue** : le developpeur le mesure et le dit, et c'est `theo-archi` qui juge si la forme de commentaire change.

### 5. Total recompte, sommet de rejeu, jobs de `ci.yml`

Lances sur `round-two` a `19c8a7b2` (apres les deux fusions), puis avec les sections 1 a 4 de cet amendement presentes, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, sur `19c8a7b2` seul (`BUILD SUCCESS`, exit 0, 3 min 41 s). **2669 tests, 0 echec, 0 erreur, 0 ignore** (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, somme faite par moi, recomptee a part par l'attribut `tests` de 313 fichiers `TEST-*.xml` : 2669). JDK 21.0.10 (`java.version` lu dans les 60 rapports Surefire de `bootstrap`, une seule valeur). **Ecart de total : aucun.** `lea-dev` annonce 2669 (base plus son lot, delta `+0` : 2 `@Test` avant et apres) ; l'arbre fusionne donne 2669, comme `round-two` avant la fusion. Les quatre classes touchees : 2, 5, 9 et 14 tests, 0 echec. Etape *"Package du fat jar"* non lancee.
- Les lignes `chemins`, `prefixes`, `outils` et le second `build` (sections de cet amendement presentes) sont a la suite, en section 6.

**Sommet de rejeu.** Le code et le contenu servis de `round-two` sont ceux de `19c8a7b2` ; le commit de cet amendement ne touche que `planning.md`. Un jar construit sur le sommet de `round-two` apres ce commit rejoue `-05` et `RCT-CE1-R1` (instance A, code de `7c9ac220`) et `RDO-CP-R3` (instance B, code de `23222465`). Le SHA du sommet est dans le rapport de l'agent : un fichier ne peut pas contenir le SHA de son propre commit. **Si le commit de commentaires de la section 4 est livre avant le rejeu**, le sommet change et ce paragraphe est a relire.

**Ce que cet amendement ne fait pas.** Ne prononce aucun Done, ne juge ni la cote de la phrase a l'ecran (`ines-design`, `RD3` critere 1) ni ce que l'enfant lit (`nadia-prof`, critere 3), ne fixe aucun prix, n'ecrit pas le commit de commentaires, ne touche ni la matrice (147 / 200, non remesuree), ni un catalogue, ni un critere.

### 6. Suite des jobs de `ci.yml`, avec les sections 1 a 5 presentes

- **`build`, second run** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, avec les sections 1 a 5 presentes : `BUILD SUCCESS`, exit 0, 3 min 57 s, **2669 tests, 0 echec, 0 erreur, 0 ignore** (memes six lignes `Tests run`). Cette section 6 n'etait pas dans le fichier lors de ce run.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, avec les sections 1 a 5.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh 1cf4e3a9` sort 0 ; les sujets de `1cf4e3a9..HEAD` hors fusions (trois commits d'agents) et le sujet prevu du commit passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `verifier-garde-fou-accent.sh "1cf4e3a9..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : trois balayages (6, 2 et 5 fichiers), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, `TMPDIR` dans mon espace de travail en forme POSIX.
- **Code inchange par ce commit** : `git diff --quiet 19c8a7b2 -- adapters domain application bootstrap pom.xml outils .github` sort 0.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. **Le Done technique n'est pas prononce par moi** : il est juge par la CI sur le commit pousse.

## Amendement du 2026-10-16 20:15 (UTC+02:00), palier 2 : correctif sur le sommet de rejeu, fusion des trois verdicts de `nadia-prof`, etat final des postes, consommation par instance, reports

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; ce que cet amendement corrige ou remplace est nomme. Arbre de depart : `round-two` a `3eef868d`, casquette Scrum Master. Aucune ceremonie n'est animee par ce texte. **Aucun Done n'est prononce par moi, technique ou pedagogique.**

### 1. Correctif : le code du sommet de rejeu n'etait pas celui de `7c9ac220`

**Ce qui etait ecrit, et qui est faux pour le sommet que j'ai donne.** L'amendement de 19:20 (section 6, *"Sommet de rejeu"*) ecrit que *"le code et le contenu servis de `round-two` sont ceux de `7c9ac220`"*. C'etait vrai a `1cf4e3a9`, et c'est devenu faux a la fusion de `s57-lea-rdo-cp-r3` (19:40), **sans que j'annote la phrase**. L'amendement de 19:40 (section 5) corrige le sommet en `19c8a7b2` mais garde la mention *"`-05` et `RCT-CE1-R1` (instance A, code de `7c9ac220`)"*, qui fait lire le contraire. Le sommet `3eef868d` que j'ai donne a l'agent principal pour le rejeu de l'instance A a donc ete presente avec un code qui n'est pas celui de `7c9ac220`. `nadia-prof` a recu l'annonce *"code identique a `7c9ac220`"* et l'a verifiee : elle ecrit, dans son rejeu de `-05` et dans la fiche des pronoms, que **l'annonce est inexacte a la lettre** ; son rejeu de `RCT-CE1-R1` et la fiche du texte narratif donnent la meme sortie (`git diff --quiet` sort 1).

**Le fait, verifie par moi.** `git diff --name-only 7c9ac220 3eef868d -- adapters domain application bootstrap pom.xml` rend **cinq fichiers** et `git diff --quiet` sort 1 :

- `adapters/adapter-web/src/main/resources/templates/fragments/manche.html` (la phrase de revelation de `RDO-CP-R3`) ;
- quatre tests : `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest`, `PartieControllerPropositionsEnImageTest`, `TexteAccentueDesGabaritsTest`, `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest`.

Le gabarit change donc **le code servi**, pas seulement des tests. **Ce que je ne sais pas par moi-meme** : si cette ligne s'affiche sur les deux jeux rejoues. `nadia-prof` dit, dans les deux rejeux et dans la fiche des pronoms, qu'elle n'est rendue que sur un ecran a propositions en image (la ligne porte `th:if="${propositionsEnImage == true and mancheRevelee == true}"`, ce que j'ai lu dans le gabarit) et qu'elle ne s'affiche ni sur `ce1-pronoms` ni sur `ce1-texte-narratif`. **C'est sa constatation, au jar ; je ne l'ai pas rejouee.** Son verdict porte sur le jar qu'elle a joue, `3eef868d`, et ses SHA-256 de paquet sont egaux a ceux de `7c9ac220`.

**Ce que je corrige, pour que la regle tienne.** (a) Le sommet de rejeu de l'instance A etait `3eef868d` et son code differe de `7c9ac220` par `RDO-CP-R3`. (b) Une phrase de sommet ecrite dans ce fichier se relit a chaque fusion de code posterieure, pas seulement quand elle me semble concernee : je n'ai pas relu celle de 19:20 apres la fusion de 19:40. (c) La lecon est a porter dans `retro.md` a la cloture ; ce fichier n'existe pas encore et je ne l'ecris pas ici.

### 2. Fusions de ce jour, lignee verifiee avant

| Ordre | Branche | Sommet | Fusion | Delta |
|---|---|---|---|---|
| 1 | `s57-nadia-p2-a3` | `bf07d89d` (`[NAD]`, 20:07:31) | `ae116186` | 4 fichiers, 258 insertions, 2 suppressions : deux rejeux neufs (50 et 64 lignes), `FICHE-bandit-manchot-pronoms-ce1.md` (+67), `FICHE-bandit-manchot-texte-narratif-ce1.md` |
| 2 | `s57-nadia-p2-b3` | `bc4009ed` (`[NAD]`, 20:00:10) | `a5a486ad` | 2 fichiers, 162 insertions, 2 suppressions : `rejeu-nadia-palier-2-b-rdo-cp-r3.md` (neuf, 55 lignes), `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` |

- **Lignee.** `git merge-base round-two <branche>` rend `3eef868d` pour les deux (code 0 ; un commit propre chacune). `juger_lignee` de `outils/verifier-lignee.sh`, appelee avec le nom de la branche et ce code : deux sorties 0. **Je ne l'ai pas lancee depuis un worktree de chaque branche.**
- **Lecture avant fusion.** Les trois rejeux en entier, le diff complet des trois fiches (lignes `+` et `-`). Les corps entiers des fiches (verdicts anterieurs) ne sont pas relus.
- **Aucun conflit** (`git grep -c '^<<<<<<<\|^>>>>>>>' HEAD -- docs squad` ne rend rien). **Docs seules** : `git diff --stat 3eef868d a5a486ad -- adapters domain application bootstrap pom.xml outils .github` ne rend aucune ligne.

### 3. Verdicts, tels que `nadia-prof` les a ecrits

| Poste | Fichier | Verdict | Jar joue |
|---|---|---|---|
| p2-3, rejeu de `-05` (`FRA-CE1-05`) | `rejeu-nadia-palier-2-a-lot05-rejeu.md`, fiche des pronoms | **VALIDE**, sans reserve ; refus `TRV-CE1-6` **leve** (quatre items dans un sens, trois dans l'autre, un mixte). 20 items sur 20, 240 couples sur 240 au pli | `3eef868d`, port 18511 |
| p2-10, `RCT-CE1-R1` (`FRA-CE1-04`) | `rejeu-nadia-palier-2-a-rct.md`, fiche du texte narratif | **levee**, `FRA-CE1-04` **VALIDE**, aucune reserve ouverte. Les trois items a 478, 20 items sur 20 et 240 couples sur 240 aux trois largeurs | `3eef868d`, port 18511 |
| p2-12, `RDO-CP-R3` (`ART-CP-10`) | `rejeu-nadia-palier-2-b-rdo-cp-r3.md`, fiche du jeu | **levee**, jeu **VALIDE**, aucune reserve ouverte. 36 revelations sur 36, marge minimale 38 px | `3eef868d`, port 18513 (code de `19c8a7b2`, `git diff --quiet` sort 0 : **exact pour ce poste**) |

Ce que je relaye sans le juger : ces verdicts sont a elle. Observations qu'elle classe hors reserve : intrus du premier mot (5 items au lieu de 6), indice sous le pli sur 9 items a la revelation, phrase de revelation qui ne se lit pas comme la suite du verdict (aucune cote ne le garde).

### 4. Etat final des postes du palier 2

| Poste | Etat | Preuve sur disque |
|---|---|---|
| p2-1, arbitrage | livre | `arbitrage-nadia-palier-2.md` |
| p2-2, marges de rejeu | **non entamees** (section 5) | aucun poste ne les a prises |
| p2-3, lot `-05` | **audite, VALIDE** (lot `-05` refuse le 2026-10-16 16:18, corrige `42071c23`, fusionne `02b4a34e`, rejoue) | `rejeu-nadia-palier-2-a-lot05.md`, `...-rejeu.md` |
| p2-4, lot `-24` | **audite, VALIDE** | `rejeu-nadia-palier-2-ap-lot24.md` |
| p2-5, `N1` et `N2` | **audite**, prises en compte | `rejeu-nadia-palier-2-b.md` |
| p2-6, script d'`additions-cp` | fusionne (`97a205e8`), hors `PAR` | amendement du 2026-10-16 |
| p2-7, forme commune | fusionne (`aca0d90f`), hors `PAR` ; **juge au rejeu de p2-8** (pas de rejeu propre) | `rejeu-nadia-palier-2-ap.md` 2.4 (`PA4` tenue) |
| p2-8, `TPH-CE1-R1` | **audite, levee** | `rejeu-nadia-palier-2-ap.md` |
| p2-9, `GNA-CE1-R1` | **audite, levee** | idem |
| p2-10, `RCT-CE1-R1` | **audite, levee** | `rejeu-nadia-palier-2-a-rct.md` |
| p2-11, `VSJ-CE1-R2` | **audite, levee** | `rejeu-nadia-palier-2-a.md` |
| p2-12, `RDO-CP-R3` | **audite, levee** | `rejeu-nadia-palier-2-b-rdo-cp-r3.md` |

**Tous les postes sont audites.** Ce n'est pas une clôture : le Done pedagogique reste prononce par `nadia-prof` fiche par fiche, la cloture du sprint vient apres, et la matrice (147 / 200, non remesuree) est a `camille-po` (`FRA-CE1-05` a `couvert-valide`, statut de tete de la fiche des pronoms, ligne `ART-CP-10`, fermeture de `S57-RCT-CE1-R1` et de `S57-RDO-CP-R3`).

### 5. Consommation par instance

Chiffres de `nadia-prof`, **relus dans ses fichiers** : lot `-05` 1,0 (`rejeu-nadia-palier-2-a-lot05.md`, routage) ; rejeu de `-05` 0,5 et `RCT-CE1-R1` 0,5 (`Tarif consomme`, ses deux rejeux de ce jour) ; `VSJ-CE1-R2` 0,25 (`rejeu-nadia-palier-2-a.md`, routage) ; lot `-24` 1,0 (`rejeu-nadia-palier-2-ap-lot24.md`) ; `TPH` 0,5 et `GNA` 0,25 (`rejeu-nadia-palier-2-ap.md`, *"0,75 sur A'"*) ; `N1` et `N2` 0,25 (`rejeu-nadia-palier-2-b.md`) ; `RDO-CP-R3` 0,5 (`rejeu-nadia-palier-2-b-rdo-cp-r3.md`) ; arbitrage 0,5 par instance (`arbitrage-nadia-palier-2.md`, *"pris sur les 0,5 d'arbitrage de chacune des trois instances"*). **Les chiffres de l'agent principal sont les memes.** Les marges (0,5 par instance) et le non affecte de A' (0,25) sont ceux de l'amendement de 18:14.

**Recompte par `awk`**, sur un registre de seize lignes (instance, ligne, prix, etat) que j'ai ecrit moi-meme, `wc -l` rend 16 : `awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}'`. Sortie lue ce jour : `A 3.25`, `Ap 3.00`, `B 1.75`, **`total 8.00`**. Meme commande restreinte aux lignes facturees : `A 2.75`, `Ap 2.25`, `B 1.25`, **`facture 6.25`** ; par etat : `facture 6.25`, `marge 1.50`, `nonaffecte 0.25`.

| Instance | Composition | Facture | Detail | Marge | Non affecte |
|---|---|---|---|---|---|
| A (groupe X) | 3,25 | **2,75** | arbitrage 0,5 ; `-05` 1,0 ; rejeu de `-05` 0,5 ; `VSJ` 0,25 ; `RCT` 0,5 | 0,5 | 0 |
| A' (groupe Y) | 3,00 | **2,25** | arbitrage 0,5 ; `-24` 1,0 ; `TPH` 0,5 ; `GNA` 0,25 | 0,5 | 0,25 |
| B | 1,75 | **1,25** | arbitrage 0,5 ; `N1` et `N2` 0,25 ; `RDO-CP-R3` 0,5 | 0,5 | 0 |
| **total** | **8,00** | **6,25** | | **1,5** | **0,25** |

**Bornes** : somme **8,0** (`PAR-9`) ; plafond de 5,0 par instance (`PAR-8`), la plus chargee est A a 3,25 en composition et 2,75 facturee ; **trois lots au plus** par instance : A en porte un, A' un, B aucun. **Marges : intactes aux trois instances.** Le second refus que A.105 empechait (le rejeu de `-05` et celui de `RCT-CE1-R1` etant tous deux dans A, dont la marge est de 0,5) n'a pas eu lieu : **les deux rejeux ont leve du premier coup, aucun rejeu supplementaire n'a ete ouvert**. Le non affecte de A' (0,25) reste un reste, non attribue ; **je ne l'attribue pas**, c'est a `camille-po`.

**Ce que le recompte ne prouve pas** : que les prix de `nadia-prof` sont les bons (le bareme est a elle), ni que le registre ecrit par moi contient toutes les lignes facturables : il reprend les postes de la composition et le rejeu de `-05`.

### 6. Reports

Six premiers reports, comptes a l'ouverture du palier 2 (2026-10-15 20:56, `DEC-0120` A.66 et A.95) : **p2-7, p2-8, p2-9, p2-10, p2-11, p2-12**. Les cinq premiers etaient non engages a l'ouverture ; p2-7 etait engage le 2026-10-16.

**Ce que cela compte (A.83).** Un engagement tardif n'efface pas le report : **les six premiers reports restent ecrits**. Il evite le suivant. **Tous les six sont audites au palier 2** (section 4) : **aucun ne glisse au palier 3**, aucun second report n'est compte, et le troisieme report interdit (A.67, A.72) ne se pose pour aucun d'eux. Les postes engages des l'ouverture (p2-1 a p2-6) n'ont aucun report. Ce que A.72 aurait fait de p2-7 s'il n'avait pas ete juge (glissement) ne s'applique pas : son audit est celui du rejeu de p2-8, que je cite en section 4 ; **si `camille-po` ou `nadia-prof` lit autrement le jugement d'un poste hors `PAR` sans rejeu propre, c'est a eux de l'ecrire**. `-06`, `-07`, `-11` (groupe X) : deuxieme ecartement de `camille-po`, que je ne recalcule pas.

### 7. `Q-U11`, `Q2` de `marc-dev`

**`Q-U11`.** La reponse relayee le 2026-10-16 (fenetre bornee, 19:20, section 5) disait que, si le rejeu de `-05` n'avait pas lieu avant la cloture, la question du retrait revenait au palier 3. **Le rejeu a eu lieu (VALIDE), la condition ne joue pas.** La fenetre se referme sans retrait, par le fait. **`Q-U11` n'est pas close pour autant** : aucun `DEC` ne la tranche (`git grep -n "Q-U11" HEAD -- squad/decisions` : la question seule). Fermer la question est a `camille-po`, par un amendement ; **je ne la ferme pas**.

**`Q2` de `marc-dev`** (jeu fautif de `TRV-CE1-6` aux anciens textes) : toujours ouverte, a `camille-po`.

### 8. Decisions

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee** par ces deux fusions ni par cet amendement. `DEC-0120` et `DEC-0122` sont inchanges.

### 9. Total recompte et jobs de `ci.yml`

La section 10 donne le total et les jobs, ecrits apres les runs.

**Ce que cet amendement ne fait pas.** Ne prononce aucun Done, ne juge aucun contenu, ne ferme aucune question de `camille-po`, ne touche ni la matrice, ni un catalogue, ni un critere, ni du code.

### 10. Total recompte et jobs de `ci.yml` lances pour ce commit

Lances sur `round-two` a `a5a486ad` (apres les deux fusions), puis avec les sections 1 a 9 de cet amendement presentes, avant commit. Je ne deduis aucun job que je n'ai pas lance.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, sans `-pl`, deux fois : sur `a5a486ad` seul (`BUILD SUCCESS`, exit 0, 3 min 48 s), puis avec les sections 1 a 9 presentes (`BUILD SUCCESS`, exit 0, 4 min 02 s). **2669 tests, 0 echec, 0 erreur, 0 ignore** aux deux runs (120, 31, 501, 1630, 381, 6 d'integration, lignes `Tests run` du reacteur, somme faite par moi, recomptee a part par l'attribut `tests` de 313 fichiers `TEST-*.xml` : 2669). JDK 21.0.10 (`java.version` lu dans les 60 rapports Surefire de `bootstrap`, une seule valeur). **Delta nul**, comme attendu : les deux fusions ne portent que des fichiers de `squad/`. L'etape *"Package du fat jar"* n'est pas lancee.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, avec les sections 1 a 9.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; `commit-atteignable.sh 3eef868d` sort 0 ; les sujets de `3eef868d..HEAD` hors fusions (deux commits d'agents, `[NAD]`) et le sujet prevu du commit passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `verifier-garde-fou-accent.sh "3eef868d..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 6 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **13 juges** lances (`bash` pour les `.sh`, `python` pour les `.py`), 13 sortis 0, aucun balayage vide, `TMPDIR` dans mon espace de travail en forme POSIX. Ils ont tourne pendant le second `build`, qui est sorti 0.
- **Code inchange** : `git diff --quiet 3eef868d -- adapters domain application bootstrap pom.xml outils .github` sort 0.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. **Le Done technique n'est pas prononce par moi** : il est juge par la CI sur le commit pousse.

## Amendement du 2026-10-16 21:46 (UTC+02:00), palier 2 : annotations de cloture (ecarts C1, C2, C3, R1, R3, R5, R9, N1 et N2 du releve du `relecteur-pedagogique`, mots de `Q-U11`, registre des prix)

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** : chaque phrase corrigee est designee par son numero de ligne dans `planning.md` a `bb322938` (1801 lignes), et reste en place. Arbre de depart : `round-two` a `efb8d013` (cinq fusions de cloture), casquette Scrum Master. Source : `relecture-cloture.md` (`72c2b198`, fusionne `376694c9`), sections 2, 4, 5 et 6. **Aucun Done n'est prononce par ce texte, technique ou pedagogique.** Les jobs de `ci.yml` lances pour son commit sont nommes dans `cloture-palier-2.md`, section 12.

### 1. `C1` : la condition du retrait est de `sofia-sm`, pas de l'utilisatrice

La ligne 1777 (amendement de 20:15, section 7) ecrit que *"la reponse relayee le 2026-10-16 (fenetre bornee, 19:20, section 5) disait que"* le retrait revenait au palier 3 si le rejeu n'avait pas lieu avant la cloture. **Cette condition est ma phrase de la ligne 1567** (amendement de 19:20, section 5 : *"je le dis, je ne la decide pas"*). La reponse relayee de l'utilisatrice est le libelle de la section 2 ci-dessous, et rien de plus. `camille-po` l'a ecrit avant moi : `DEC-0120` A.111 dit que la lecture de 19:20 *"ne joue pas : sa condition est fausse"* et que *"ce n'est pas un mot de l'utilisatrice"*. **Correction** : la condition est de `sofia-sm`. Le rejeu a eu lieu avant la cloture (VALIDE), elle ne joue pas.

### 2. Mots de `Q-U11` (ecart `P2`) : le libelle, entre guillemets, avec sa provenance

Les lignes 1567 et 1777 ecrivent *fenetre bornee* en gras, sans guillemets ni date. **Provenance, forme 2 de `DEC-0062` 3.1, telle que `camille-po` l'inscrit a `DEC-0120` A.111** : l'utilisatrice a repondu le 2026-10-16 a l'agent principal, par le formulaire de celui-ci, en choisissant entre les deux options que `Q-U11` lui proposait (retrait, regime par defaut de `DEC-0045` section 3 ; fenetre bornee, `DEC-0055`). Son libelle, cite entre guillemets : *"fenetre bornee"*. L'heure de la reponse n'est pas fournie ; aucun motif ni aucune echeance n'a ete transmis. Je n'ai pas lu ces mots de sa main.

**Ligne 1777, *"aucun `DEC` ne la tranche"*** : vrai a 20:15, faux depuis `DEC-0120` A.111 (fusion `286e47c0`) : `Q-U11` est close, `S57-RETRAIT-PRONOMS-CE1` est non engagee et sans objet, et la decision de `A.106` (retrait) est remplacee par A.111. Le renvoi a cette inscription dans `rapport-marc-dev-palier-2-corrections.md` (ligne 84) est a `marc-dev` : je ne modifie pas son rapport.

### 3. `C2` : *"Tous les postes sont audites"* (ligne 1750)

Le tableau de la ligne 1738 dit `p2-2` *"non entamees"*, et `p2-1` (arbitrage, livre) et `p2-6` (script, fusionne hors `PAR`) n'ont pas d'audit. **Correction** : huit postes ont leur audit ou leur rejeu de `nadia-prof` (`p2-3`, `p2-4`, `p2-5`, `p2-8`, `p2-9`, `p2-10`, `p2-11`, `p2-12`) ; `p2-7` est juge au rejeu de `p2-8` (pas d'audit propre) ; `p2-1` est livre, `p2-6` est fusionne, `p2-2` n'est pas entame. Douze postes, 8 + 1 + 3.

### 4. `C3` : l'etat de `p2-7` a l'ouverture (ligne 1771)

La phrase *"Les cinq premiers etaient non engages a l'ouverture ; p2-7 etait engage le 2026-10-16"* laisse lire que `p2-7` etait engage a l'ouverture. **Les six etaient non engages a l'ouverture** (2026-10-15 20:56, ligne 458 et section 8 de cet amendement-la). Engagements : `p2-7` le 2026-10-16 09:03 ; `p2-8`, `p2-9` et `p2-11` le 2026-10-16 15:27 ; `p2-10` et `p2-12` le 2026-10-16 16:59 (titres des amendements du meme nom).

### 5. `R1` : noms tronques par `...`, noms complets

Ligne 1286 et ligne 1369 : `mesure-ines-pa7-rct-ce1-r1-2026-10-16.md`. Ligne 1286 et ligne 1370 : `mesure-ines-pa8-rdo-cp-r3-2026-10-16.md`. Ligne 1739 : `rejeu-nadia-palier-2-a-lot05-rejeu.md`. (`ls` du dossier du sprint : les trois existent.) La quatrieme occurrence de l'ecart, `ADR-0031-un-ecran-ne-gouverne-jamais-l-apparence-d-un-autre.md` dans `rapport-qualite-garde-c1.md` ligne 100, est a `yanis-qa`.

### 6. `R3` : le champ *"Tarif consomme"* (ligne 1754)

La ligne 1754 cite *"Tarif consomme"* pour les rejeux de `-05` et de `RCT-CE1-R1`, *"ses deux rejeux de ce jour"*. Les deux fichiers de rejeu ne portent pas ce champ : leur chiffre est dans la ligne de routage (*"0,5 consomme en A"*, `rejeu-nadia-palier-2-a-lot05-rejeu.md` ligne 46 et `rejeu-nadia-palier-2-a-rct.md` ligne 47). Le champ existe dans les **fiches** : `FICHE-bandit-manchot-pronoms-ce1.md` ligne 377 (*"rejeu 0,5 (instance A)"*) et `FICHE-bandit-manchot-texte-narratif-ce1.md` ligne 233 (*"rejeu 0,5 (p2-10, instance A)"*), en plus de `rejeu-nadia-palier-2-ap.md` ligne 185. **Correction** : le renvoi est aux deux fiches pour le champ, aux deux lignes de routage pour le chiffre. Le chiffre, 0,5, etait juste. L'ecart est donc plus etroit que le releve ne le dit (il ne cherchait le champ que dans les rejeux).

### 7. `R9` : `Q-P8`, refermee par son code

`composition-palier-2.md` ligne 148 : *"Qui ecrit le script des 43 enonces, qui mesure ; developpeurs des deux lots et de la forme ; ordre de fusion"*, a `sofia-sm`. Chaque terme est tranche par un fichier present : script a `marc-dev` (amendement du 2026-10-15 20:56, section 5 ; fusion `97a205e8`), mesure a `ines-design` (`PA1`, meme amendement, section 7), developpeurs `marc-dev` pour `-05`, `sofia-sm` pour `-24`, `lea-dev` pour la forme (meme section 5), ordre de fusion (meme amendement, section 9). **`Q-P8` est refermee**, sans que je l'aie nommee par son code avant ce jour.

### 8. `N1` : 496 et 501 sont des comptes d'`adapter-web`, pas du reacteur

Lignes 1078, 1079 et 1135 : *"501 tests, 0 echec"* et *"496 tests"* sont des comptes du seul module `adapter-web`, mesures par `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` (`backlog.md` ligne 22564 ; mesure partielle, `-am` present). Lecture corrigee : *"501 tests d'adapter-web (`-pl adapters/adapter-web -am`)"* et *"496 tests d'adapter-web"*. Le reacteur complet sur l'arbre qui porte la garde (`e138ec97`) est **2669** (lignes 1054 et 1111 de ce fichier). La ligne `DEC-index.md:128` (meme ecart) est a `camille-po`.

### 9. `R5` : `RL5` a `RL7`, le commit n'existe pas, reporte au palier 3 avec son etat et sa condition

**Etat mesure a `efb8d013`** : `git log --oneline 19c8a7b2..HEAD -- adapters domain application bootstrap` rend 0 ligne. Les trois commentaires sont tels que l'amendement de 19:40 (section 4) les decrit : `manche.html` lignes 207 a 213 (*"cette phrase, de nadia-prof (M4)"*), `TexteAccentueDesGabaritsTest.java` lignes 158 a 160 (*"M4 de ARBITRAGE-PREALABLE-s55... copie tel quel"*), `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java` ligne 107 (*"M4 du meme arbitrage, mot pour mot"*). La phrase servie est celle de `reponse-nadia-pa7-pa8.md` 2.4.

**Ce qui est faux dans mon propre texte.** L'amendement de 19:40 (section 4) ecrit *"j'engage ce commit"*, avec la condition que l'agent principal me le confie sous la casquette developpeuse : **il ne l'a pas ete dans le palier 2**. Je ne l'ai pas rappele dans l'amendement de 20:15 (`git grep -n "RL5\|RL6\|RL7"` : lignes 1610 et 1658 a 1666 seulement) : le releve (`R5`) le constate a raison.

**Fait mesure par `theo-archi`** (`confirmation-archunit-palier-2.md` 5.5, jar de `bb322938`, une manche de `bandit-manchot-pronoms-ce1`) : le commentaire de `manche.html` est servi, dans le bloc `th:if="${message != null}"`, sur **toute** manche de `bandit-manchot` apres un coup (11 commentaires HTML dans la reponse, dont celui de `RL5`). Le corriger change **l'octet servi**, pas ce qui s'affiche. `RL6` et `RL7` sont dans `src/test` : absents du jar. Sa forme : le commentaire reste un commentaire HTML, un seul commit pour les trois (jugement de `theo-archi`).

**Report, ecrit** :

| Quoi | Etat | Titulaire | Condition |
|---|---|---|---|
| Commit unique de `RL5`, `RL6`, `RL7` (trois fichiers, trois commentaires, aucune autre ligne ; citer `reponse-nadia-pa7-pa8.md` 2.4) | **non livre**, engage le 2026-10-16 19:40, **reporte au palier 3** (premier glissement d'un engagement hors tableau, ecrit aussi par `camille-po`, `DEC-0120` A.115 rang 2) | un developpeur que j'engage a la composition du palier 3, casquette developpeuse, tache distincte ; hors `PAR`, aucun prix | **avant le premier jar rejoue du palier 3** (`theo-archi`), et avant tout autre commit de code (A.115). Tout sommet de rejeu ecrit apres lui dit que le code differe du precedent par ce commentaire. `RD3-9` et `RD3-10` se lisent par la forme a parents sur `19c8a7b2` (section 4 de l'amendement de 19:40) |

A `theo-archi`, avant le commit : dire si `PHRASE_DE_M4` et les trois messages de `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest` (`yanis-qa`, `rapport-qualite.md` section 5) sont un quatrieme commentaire de la meme famille.

### 10. `N2` : le registre des prix, commite

Les recomptes `awk` des lignes 1331 (dix-huit lignes), 1434 (dix-sept) et 1756 (seize) portent sur des registres que je n'avais pas commites : la commande ne se rejouait pas. Le registre de dix-sept lignes est celui de `DEC-0120` A.105 (en entier, `camille-po`, `e83fe3f1`). **Celui de la ligne 1756 est ce registre sans la ligne `H|non affecte|0.00`, avec les etats `consomme` et `engage` regroupes en `facture`** : il est commite sous `registre-prix-palier-2.txt` (seize lignes, `wc -l`).

**Ce que ce fichier n'est pas** : le fichier ecrit a 20:15. Je ne l'ai pas (il vivait dans le repertoire de travail d'une autre instance de `sofia-sm`, que je n'ai pas ouvert) ; ce fichier est **reconstruit ce jour a partir d'A.105**, et verifie contre les sorties que la ligne 1756 annonce. Le registre de dix-huit lignes de la ligne 1331 a ete remplace par celui de la section 2 de l'amendement de 18:14 : je ne le reproduis pas, et aucun chiffre de ce fichier n'en depend.

```
awk -F'|' '{p[$1]+=$3; t+=$3} END{for(k in p) printf "%s %.2f\n",k,p[k]; printf "total %.2f\n",t}' registre-prix-palier-2.txt | sort
```

Sortie lue ce jour : `A 3.25`, `Ap 3.00`, `B 1.75`, `total 8.00`. Par etat (`awk -F'|' '{e[$4]+=$3} END{for(k in e) printf "%s %.2f\n",k,e[k]}'`) : `facture 6.25`, `marge 1.50`, `nonaffecte 0.25`. Lignes facturees par instance : `A 2.75`, `Ap 2.25`, `B 1.25`, `facture 6.25`. Ce sont les chiffres de la ligne 1756.

### 11. Ce qui reste du releve, et qui n'est pas a moi

Nomme avec son destinataire dans `cloture-palier-2.md`, section 9.

### 12. Decisions

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee** par cet amendement. `DEC-0120` (dix-huit amendements, A.111 a A.116 les derniers) et `DEC-0122` sont inchanges par lui.

### 13. Ce que cet amendement ne fait pas

Ne prononce aucun Done, ne juge aucun contenu, ne touche ni la matrice (148 / 200, `camille-po`), ni un catalogue, ni un critere, ni du code. N'ecrit pas le commit de `RL5` a `RL7`. Ne modifie aucun fichier signe par un autre agent.

---

## Amendement du 2026-10-19 23:09 (UTC+02:00), ouverture du palier 3

Annotation datee, heure lue par `date`, **inscrite avant l'ouverture de tout worktree du palier 3** (`D2` (c), precedent s56 et paliers 1 et 2). **Rien au-dessus n'est reecrit** ; cet amendement ne remplace aucune phrase. Arbre : `round-two` a `ee9ecd44`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` ne montre que `.claude/settings.local.json` (non suivi) avant ecriture, casquette Scrum Master. Sources, par auteur : `sofia-sm` (`cloture-palier-2.md`, `ad65827e`, sections 8, 9 et 14) ; `nadia-prof` (`arbitrage-nadia-preparation-palier-3-ce1.md`, `b8fa7f9c` ; `reponses-nadia-questions-stories-palier-3.md`, `c3d1ebcf`, section 7) ; `camille-po` (`preparation-palier-3-ce1-camille.md`, `a5748847`, section 8 ; annotation "Palier 3 de s57" du backlog et son amendement, `ee9ecd44` ; `DEC-0120` A.66 et A.115) ; `theo-archi` (`reponses-theo-questions-stories-palier-3.md`, `34f6734a` ; `avis-theo-preparation-palier-3-ce1.md` et `decision-theo-garde-ce1-e3.md`, `08d0dc0e`). **Cet amendement ouvre le palier 3 sous les reserves de la section 7. Il n'ouvre aucun worktree** : `K1 (a)` des stories demande qu'il soit fusionne sur `round-two`, et je ne le fusionne pas. Il n'est pas commite par moi.

### 1. Regle d'ouverture (`D2`), condition par condition

| Condition de `D2` | Preuve lue sur l'arbre | Etat |
|---|---|---|
| (a) `cloture-palier-2.md` sur `round-two` | `git show HEAD:squad/sprints/s57-version-cp-ce1-cm1-trois-instances/cloture-palier-2.md` sort 0 | tenue |
| (b) arbitrage prealable de `nadia-prof`, trois instances | meme commande sur `reponses-nadia-questions-stories-palier-3.md` : sort 0 ; sa section 7 est *"Arbitrage prealable du palier 3, instances A, A' et B"* ; elle porte la cloture du palier 2 (7.1), la capacite et l'affectation (7.2), les conditions de Done (7.3). *"Il n'y a pas de second fichier"* (section 3 du meme fichier) | tenue |
| (c) composition de `camille-po`, avec la reference de l'arbitrage, heure avant les worktrees | section 8 de `preparation-palier-3-ce1-camille.md` : sort 0 ; variante (i), confirmee par `nadia-prof` (7.2, `CR41`) ; stories `S57-BR-*` et `S57-ARB-PALIER-3` en fin de `backlog.md`. `git worktree list` rend 2 lignes (le checkout et celui de `main`) ; aucune des branches de la section 6 de `composition-palier-3.md` n'existe (`git branch --list`, 0 par nom). Choix de (i) : delegation de l'utilisatrice du 2026-10-19 au mieux de la couverture, relayee a `camille-po` (backlog, annotation "Palier 3 de s57") et au lanceur de cette tache ; je n'ai pas lu ses mots de sa main | tenue par cet amendement (sections 2 a 4) |
| (d) table d'isolation du palier, repertoires et ports neufs | `composition-palier-3.md`, section 6. **Ecart a la lettre de `D2` (d)** (*"l'amendement porte la table"*) : la table est dans ce fichier, a la demande du lanceur, et cet amendement la designe ; je ne la copie pas ici pour qu'elle ne puisse pas deriver en deux endroits. Ports : ceux du palier 2, aucun numero neuf ; 18522 n'est donne a personne (19222 est fixe en dur par `pilote-q7.mjs`) ; `netstat -ano` sur les ports 185xx et 192xx rend 0 ligne | tenue par `composition-palier-3.md`, designe ici |

Sur le meme arbre : `git merge-base --is-ancestor 729b0d8a HEAD` sort 0 (`K1 (c)` de `S57-BR-EMC-CE1-01`, instance A'). `reponses-theo-questions-stories-palier-3.md` (`X5c`) dit qu'`ADR-0065` A2 ne demande ni amendement ni garde-fou pour qu'A' porte l'EMC.

**Ce que `D2` ne demande pas et qui reste ouvert, nomme pour qu'aucun ne passe pour clos** :

- **La lecture de l'isolation du palier 2 par `nadia-prof`** : aucun fichier du depot (`cloture-palier-2.md` section 10 ; `reponses-nadia-questions-stories-palier-3.md` 7.1 : *"Reste ouvert, a moi"*). Elle n'est pas une condition de `D2`.
- **`Q4` de `theo-archi`** (crochet du harnais sur les redirections du shell) : decision de l'utilisatrice, non prise. La consigne ecrite de `composition-palier-3.md` section 6 n'est pas un moyen de garde.
- **`K7`** : chaque developpeur du palier ecrit `rapport-<developpeur>-palier-3.md`, avec les jobs de `ci.yml` qu'il a lances.

### 2. Composition portee (`camille-po`), prix de `nadia-prof`

Reprise de `preparation-palier-3-ce1-camille.md` 8.2 et de `reponses-nadia-questions-stories-palier-3.md` 7.2 **sans modifier un prix, un lot ni une instance**. Somme recomptee par `awk` sur onze lignes de prix, sortie lue ce jour : A 4,00 ; A' 2,00 ; B 2,00 ; total 8,00 ; engage 4,50 ; marges 1,50 ; non engage 2,00 ; non affecte 0.

| Instance | Total | Engage a l'ouverture | Marge, allouee a aucun poste | Non engage, retenu par la composition | Lots |
|---|---|---|---|---|---|
| A (groupe X) | 4,0 | 3,5 (arbitrage 0,5 ; `-06`, `-07`, `-11` 3,0) | 0,5 | 0 | 3 |
| A' | 2,0 | 0,5 (arbitrage) | 0,5 | 1,0 (`EMC-CE1-01`, `AD1`) | 1 |
| B | 2,0 | 0,5 (arbitrage) | 0,5 | 1,0 (`MAT-CE1-01`, `AD1`) | 1 |
| hors instance | 0 | - | - | - | - |

Bornes relues : somme 8,0 (`PAR-9`) ; plafond de 5,0 par instance, A est a 4,0 (`PAR-8`) ; trois lots au plus par instance, A en porte trois (borne atteinte), A' et B un ; aucun chiffre au-dela de 8,0 ; les marges et les non affectes du palier 2 sont tombes (`DEC-0120` A.114) et rien n'en est repris. Un lot a 1,0 pour `EMC-CE1-01` : prix de l'audit, l'ecriture du paquet n'est pas un poste du bareme (`nadia-prof`, 7.2).

### 3. Ordre : `Z1`/`Z2`, `RL5` a `RL7`, lots. **Propose, confirme dans sa lecture, non decide**

L'ordre est celui de la section 14 de `cloture-palier-2.md` et de `DEC-0120` A.115 : **proposition de `camille-po`, ecrite *"Rien n'est decide ici"* et *"sans engagement"***. Sa lecture architecturale est confirmee par `theo-archi` (`reponses-theo-questions-stories-palier-3.md`, ligne `ORD`) : `RL5` a `RL7` precede chaque lot, `Z1`/`Z2` ne lie les lots que par le rang. **Aucune `ADR` ni aucune `DEC` ne le decide.** L'engagement est a moi (A.115 : *"l'engagement est a `sofia-sm` au planning du palier 3"*) : **je l'engage comme ordre de planning, pas comme decision de produit ou d'architecture.**

| Rang | Quoi | Porte |
|---|---|---|
| 1 | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (`Z1`, `Z2`) | porte de tout poste qui modifie `recreo.css` ; aucun des cinq lots n'y touche. Pas une porte des lots, sauf si l'un d'eux demande une regle de feuille |
| 2 | commit de `RL5`, `RL6`, `RL7` | **porte des lots** : avant le premier jar rejoue du palier 3 et avant tout commit de lot ; les branches des cinq lots sont coupees sur le sommet de `round-two` apres sa fusion |
| 3 | `-06`, `-07`, `-11` (A) ; `MAT-CE1-01` (B) ; `EMC-CE1-01` (A') | `-06`, `-07`, `-11` dans l'ordre d'audit de `nadia-prof` ; la lecture complete precede chaque verdict |

Le rang est l'ordre de demarrage. **Ordre de fusion** : le premier pret est fusionne le premier, un a la fois, `bash outils/mvn-jdk21.sh -B clean verify` entier a la racine apres chacun, regle du palier 1 reprise. **Tout compte de tests est celui du reacteur sur l'arbre fusionne, que j'etablis moi-meme.** Dernier total mesure : 2669 sur `efb8d013` (`cloture-palier-2.md` section 12). Resolution des fichiers partages (`catalogue.yaml`, 152 entrees avant, 157 apres les cinq lots ; inventaire de `TexteAccentueExposeParLePortEntrantTest` ; `REGISTRE-identifiants.md`) : celle des paliers 1 et 2, jamais une assertion affaiblie. **Une phrase d'`A.115` dit autrement** (rang 2 : *"avant tout autre commit de code"*, le commit de `Z1`/`Z2` etant au rang 1) : `AD5`, a `camille-po`.

### 4. Postes du palier 3, verification poste par poste (`PAR-16`) : les stories nommees (`K1 (a)`)

Numerotation propre au palier 3. Titres lus par `grep -c "^#\{3,4\} .*<story>" docs/backlog/backlog.md` sur `ee9ecd44`. Le detail (prix, etat, issue de `DEC-0086` section 5) est dans `composition-palier-3.md`, section 3.

| # | Poste | Story, titres au backlog | Inst. | Agent nominatif | Etat |
|---|---|---|---|---|---|
| p3-1 | Arbitrage prealable et lecture complete | `S57-ARB-PALIER-3` (2) | A, A', B | `nadia-prof` | **engage** : part ecrite livree, reste la lecture |
| p3-3 | Garde de `C1'`, virgule et CSS imbrique | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (1) | hors `PAR` | `theo-archi` ; mesure `yanis-qa` | **engage** |
| p3-4 | `RL5`, `RL6`, `RL7` | aucune (0) | hors `PAR` | `sofia-sm`, casquette developpeuse, sous `AD2` | **engage sans story**, ecart dit (`AD3`) |
| p3-5 | `ce1-inference` | `S57-BR-FRA-CE1-06` (1) | A | `marc-dev` | **engage** |
| p3-6 | `ce1-texte-documentaire` | `S57-BR-FRA-CE1-07` (1) | A | `marc-dev` | **engage** |
| p3-7 | `ce1-negation` | `S57-BR-FRA-CE1-11` (1) | A | `marc-dev` | **engage** |
| p3-8 | `ce1-nombres-jusqu-a-1000` | `S57-BR-MAT-CE1-01` (1) | B | **a decider** (`AD1`) | **non engage** |
| p3-9 | `ce1-emc-biens-communs`, paquet a ecrire | `S57-BR-EMC-CE1-01` (1) | A' | **a decider** (`AD1`) | **non engage** |

`p3-2` (les trois marges de 0,5) n'est pas un poste de travail. **Hors composition, non engages, aucun developpeur designe** : `S57-GARDE-PILE-DE-POLICES` (`AD6`), `S57-GARDE-NOMBRE-DE-MANCHES`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE`, le garde generique de `LON-CE1-3` a `-5` et `TRV-CE1-1` (forme `F1` de `theo-archi`, story non ecrite : `camille-po`), les trois commentaires d'en-tete de paquet de `R6` (`AD7`).

### 5. Affectations nominatives des developpeurs

**Deduit des regles ecrites** (`composition-palier-3.md`, section 4, `RG1` a `RG6`) : `theo-archi` pour `Z1`/`Z2` (la story le nomme, precedent du palier 2) ; `sofia-sm`, casquette developpeuse, pour `RL5` a `RL7` (mon texte du 2026-10-16 21:46, section 9, *"casquette developpeuse"*), **sous `AD2`** ; `marc-dev` pour `-06`, `-07`, `-11`, dans l'ordre d'audit, un worktree a la fois (`lea-dev` exclu des lots de francais CE1 par la regle de ce fichier, section "Charge des developpeurs").

**Pas deduit** : le developpeur de `MAT-CE1-01` (B) et celui d'`EMC-CE1-01` (A') : `lea-dev` sur l'un, `sofia-sm` sur l'autre, **le sens est a l'utilisatrice (`AD1`)**. Les stories de `-06` et `-07` proposent `lea-dev` ; la regle l'exclut ; la derogation, si elle est voulue, est a l'utilisatrice (`AD4`).

### 6. Isolation

`composition-palier-3.md`, section 6, designee par la condition (d) ci-dessus : repertoires `s57-p3-<agent>-<instance>`, un port de jeu et un port de debogage par ligne, consigne ecrite a repeter en tete du message de lancement de chaque agent, branches et worktrees nommes, aucun n'existe a l'ecriture.

### 7. Compte des reports et reserves d'ouverture

Regles de `DEC-0120` A.66 a A.73, appliquees comme aux paliers 1 et 2 : un report est un poste retenu par la composition et non engage a l'ouverture, quelle qu'en soit la cause.

| Poste | Etat a l'ouverture | Compte |
|---|---|---|
| `-06`, `-07`, `-11` | engages | pas de report ; deuxieme ecartement solde ; pas de troisieme |
| p3-8 `MAT-CE1-01`, p3-9 `EMC-CE1-01` | **non engages tant que `AD1` n'est pas repondue** | **premier report chacun si la reponse arrive apres la fusion de cet amendement** ; si elle arrive avant, l'amendement se corrige et ils sont engages a l'ouverture |
| `RL5` a `RL7` | engages sous `AD2` | premier glissement deja ecrit (21:46, section 9) ; non livres a la cloture du palier 3 : second glissement, report compte, ecrit ce jour-la |

### 8. A decider

Je classe, je ne reponds a aucune. Le detail est dans `composition-palier-3.md`, section 8.

| Code | Objet | Qui decide |
|---|---|---|
| `AD1` | developpeurs de `MAT-CE1-01` et d'`EMC-CE1-01` : `lea-dev` ou `sofia-sm` | utilisatrice |
| `AD2` | `RL5` a `RL7` confiee a `sofia-sm` sous la casquette developpeuse | utilisatrice, par le lanceur |
| `AD3` | story pour `RL5` a `RL7` | `camille-po` |
| `AD4` | derogation a la regle *"aucun lot de francais CE1 pour `lea-dev`"* | utilisatrice |
| `AD5` | *"avant tout autre commit de code"* (`A.115` rang 2) et le commit de `Z1`/`Z2` | `camille-po`, avis de `theo-archi` |
| `AD6` | `S57-GARDE-PILE-DE-POLICES` au palier 3 | `camille-po` (rang), puis `sofia-sm` (engagement) |
| `AD7` | les trois commentaires d'en-tete de `R6` | `camille-po` (moment), puis `sofia-sm` (developpeur) |

### 9. Ce que cet amendement ne fait pas

N'ouvre aucun worktree, ne cree aucune branche, ne lance aucun agent, ne fusionne ni ne commite rien. Ne modifie aucun prix, aucun lot, aucune instance, aucun critere de story. Ne prononce aucun Done, ne juge aucun contenu, ne touche pas la matrice (148 / 200, `camille-po`). Ne decide ni l'ordre (section 3), ni `AD1` a `AD7`. N'ecrit pas le commit de `RL5` a `RL7`. Ne modifie aucun fichier signe par un autre agent : `planning.md` s'amende par cette annotation datee, comme sa section "Duree" l'a prevu (`D2`).

### 10. Jobs de `ci.yml` lances pour ce commit

Lances depuis le checkout, avec `composition-palier-3.md` (non suivi) et cette annotation presentes, avant tout commit (il n'y en a pas). Sorties des journaux ecrits dans mon repertoire de scratchpad. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 (*"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*) ; `git ls-files` (moins le test du garde-fou) et `composition-palier-3.md` passent a `verifier-chemins.sh` : sortie 0 (*"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*).
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 15 tests (5 et 10), 0 echec, premiere execution. Lance parce que ces deux tests lisent `squad/` par `Files.walk`, fichiers non suivis compris ; ils ont lu `composition-palier-3.md` et cette annotation avant l'ajout de la presente section, qui ne contient aucun tableau. Le reste du `build` (reacteur complet) et l'etape *"Package du fat jar"* ne sont pas lances : ce changement ne touche ni code ni contenu servi.
- **`prefixes`** et **`outils`** : non lances. Aucun commit n'est fait ; aucun outil n'est modifie.
- Lignee : `bash outils/verifier-lignee.sh` sort 0 avant et apres l'ecriture.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. Le Done technique n'est pas prononce par moi.

### 11. Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : l'heure d'ouverture du palier 3 ; la composition de `camille-po` portee, sans changement de prix, de lot ni d'instance ; l'ordre `Z1`/`Z2`, `RL5` a `RL7`, lots, engage comme ordre de planning et non decide ; les affectations deduites des regles et les sept points a decider ; la designation de la table d'isolation de `composition-palier-3.md` ; le compte des reports a l'ouverture. **L'amendement de `DEC-0120` qui porte la composition** (precedent : A.53 a A.62 et A.91 a A.93 ; derniers amendements en vigueur, A.115 et A.116) est a `camille-po` : les proprietaires qu'elle attendait sont nommes ici, sauf les deux de `AD1`.

## Amendement du 2026-10-19 23:44 (UTC+02:00), palier 3 : AD1, AD2 et AD4 inscrites comme decidees, lots de B et d'A' engages

Annotation datee, heure lue par `date`. **Rien au-dessus n'est reecrit** ; cet amendement ne remplace aucune phrase de l'amendement de 23:09, il en corrige l'etat par les lignes ci-dessous. Arbre : `round-two` a `6b6d271a`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` ne montre que `.claude/settings.local.json` (non suivi) avant ecriture, casquette Scrum Master. `camille-po` traite `AD3`, `AD5`, `AD6` et `AD7` en parallele dans `docs/backlog/backlog.md` et `preparation-palier-3-ce1-camille.md` : je ne touche aucun de ces deux fichiers et je laisse ces quatre points *"a decider"*, chez leur proprietaire.

### 1. Ce qui est decide, et sur quoi je l'inscris

L'utilisatrice a repondu *"ok"* le 2026-10-19 a ma recommandation sur `AD1`, `AD2` et `AD4`. **La reponse m'est relayee par l'agent qui m'a lance : je ne l'ai pas lue de sa main**, comme la reponse sur `DEC-0120`. Je n'ecris donc que ce que ma recommandation contenait, et je ne lui prete aucune autre parole. Cette reponse a une recommandation n'est ni une `ADR` ni une `DEC` : c'est un choix de capacite, que l'amendement de 23:09 classait a l'utilisatrice, et qu'elle peut inverser.

| Code | Etait | Est inscrit comme decide |
|---|---|---|
| `AD1` | developpeurs de `MAT-CE1-01` (B) et d'`EMC-CE1-01` (A') : `lea-dev` ou `sofia-sm` | `lea-dev` prend `MAT-CE1-01` (B) **et** `EMC-CE1-01` (A') |
| `AD2` | `RL5` a `RL7` confiee a `sofia-sm`, casquette developpeuse | oui : `sofia-sm`, casquette developpeuse, tache distincte |
| `AD4` | derogation a la regle *"aucun lot de francais CE1 pour `lea-dev`"* | **pas de derogation** : `marc-dev` garde `-06`, `-07`, `-11` ; `lea-dev` n'a aucun lot de francais CE1 |

### 2. Etat des postes : remplace les lignes `p3-4`, `p3-8` et `p3-9` de la section 4 de l'amendement de 23:09

| # | Poste | Agent nominatif | Etat |
|---|---|---|---|
| p3-4 | `RL5`, `RL6`, `RL7` | `sofia-sm`, casquette developpeuse (`AD2`) | **engage sans story** ; l'ecart reste dit, `AD3` est a `camille-po` |
| p3-5, p3-6, p3-7 | `-06`, `-07`, `-11` | `marc-dev` (`AD4`) | **engages**, inchanges ; la contradiction entre les stories de `-06` et `-07` (qui proposent `lea-dev`) et la regle est tranchee par `AD4`. Je ne modifie pas ces stories, qui sont de `camille-po` |
| p3-8 | `ce1-nombres-jusqu-a-1000`, `S57-BR-MAT-CE1-01` (B) | `lea-dev` (`AD1`) ; audit `nadia-prof` (B) | **engage** par cet amendement |
| p3-9 | `ce1-emc-biens-communs`, `S57-BR-EMC-CE1-01` (A') | `lea-dev` (`AD1`) ; audit `nadia-prof` (A') | **engage** par cet amendement |

Arithmetique, lue le 2026-10-19 (`awk -F'|'` sur onze lignes `instance|poste|prix|etat`) : A 4,00 ; Ap 2,00 ; B 2,00 ; total 8,00 ; par etat, `engage` 6,50 ; `marge` 1,50 ; `a-decider` 0. Aucun prix, aucun lot, aucune instance ne change.

### 3. Ce que la decision de `AD1` coute, dit et non arbitre

- **Un seul developpeur sur deux instances.** La regle de `composition-palier-3.md` (RG2 : des developpeurs distincts sur des instances distinctes, parce que les lots d'un palier partagent `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest`) n'est pas tenue pour B et A' : `AD1` met les deux lots chez `lea-dev`. J'applique la decision et je dis l'ecart. Ce que je fais de mon role : `lea-dev` n'a **qu'un worktree vivant a la fois** (RG4) ; il conduit `p3-8` puis `p3-9`, l'ordre de la composition, et me dit quand il passe de l'un a l'autre ; la resolution des fichiers partages est la mienne, comme aux paliers 1 et 2, jamais une assertion affaiblie.
- **Effet sur les instances** : le lot d'A' ne demarre qu'apres la livraison de celui de B. Je n'ai aucune mesure de charge pour dire lequel est le plus long, et je n'en invente pas.
- **`sofia-sm` n'a plus aucun lot** : sa seule ligne de developpement est `p3-4`. Les deux lignes d'isolation de `sofia-sm` envisagees par la section 6 de `composition-palier-3.md` tombent.
- **Reports.** L'amendement de 23:09 (section 7) ecrivait : *"premier report chacun si la reponse arrive apres la fusion de cet amendement"*. Cet amendement est fusionne sur `round-two` (`6b6d271a`) et la reponse est posterieure : **`p3-8` et `p3-9` sont chacun a leur premier report** (`DEC-0120` A.66), engages tardivement, ce qui evite un second report et ne l'efface pas. Compte a l'ouverture : 2 premiers reports. L'amendement de `DEC-0120` qui porte la composition est a `camille-po`.

### 4. Isolation : remplace deux lignes de la table de `composition-palier-3.md` section 6

La table reste dans `composition-palier-3.md` (une seule copie) ; son amendement du meme jour porte les deux lignes remplacees. Aucun numero neuf : `lea-dev` prend 18531 (B) et 19231, puis 18532 (A') et 19232, repertoires `s57-p3-lea-dev-b` et `s57-p3-lea-dev-ap`. **`lea-dev` a deux lignes et ne les entrelace pas.** Branches et worktrees : `s57-lea-mat-ce1-01` et `s57-lea-emc-ce1-01`, worktrees voisins du checkout, ouverts apres la fusion de `p3-4`.

### 5. Ce qui reste a decider, chez son proprietaire

| Code | Objet | Qui decide |
|---|---|---|
| `AD3` | story pour `RL5` a `RL7` | `camille-po` |
| `AD5` | *"avant tout autre commit de code"* (`A.115` rang 2) et le commit de `Z1`/`Z2` | `camille-po`, avis de `theo-archi` |
| `AD6` | `S57-GARDE-PILE-DE-POLICES` au palier 3 | `camille-po` (rang), puis `sofia-sm` (engagement) |
| `AD7` | les trois commentaires d'en-tete de `R6` | `camille-po` (moment), puis `sofia-sm` (developpeur) |

### 6. Ce que cet amendement ne fait pas

N'ouvre aucun worktree de lot, ne cree aucune branche de lot, ne lance aucun agent. N'engage aucun lot de paquet avant la fusion de `p3-4` : les lots s'ouvrent apres elle (ordre de l'amendement de 23:09, section 3). Ne modifie aucun prix, aucun lot, aucune instance, aucun critere de story, ni `backlog.md`, ni `preparation-palier-3-ce1-camille.md`, ni la matrice. Ne prononce aucun Done. Ne decide ni `AD3`, ni `AD5`, ni `AD6`, ni `AD7`.

### 7. Jobs de `ci.yml` lances pour ce commit

Lances depuis le checkout, avec les deux amendements presents, avant le commit. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `verifier-chemins-test.sh` rend *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."* ; `git ls-files` (moins le test du garde-fou) passe a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` et `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, 15 tests (5 et 10), 0 echec. Le reacteur complet est lance pour la tache suivante (`RL5` a `RL7`), pas pour celle-ci.
- **`prefixes`** : lance apres le commit, resultat dans le message de retour de la tache. **`outils`** : non lance par ce commit, aucun outil modifie.

### 8. Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : `AD1`, `AD2` et `AD4` comme decidees sur la foi d'une reponse relayee ; l'engagement de `p3-8` et `p3-9` ; le compte des reports a l'ouverture (2 premiers reports) ; l'ecart a RG2, dit. L'amendement de `DEC-0120` qui porte la composition est a `camille-po`.

## Annotation de `camille-po` du 2026-10-21 17:13 (UTC+02:00), palier 3 : prix, reports, points `AD3`, `AD5`, `AD6`, `AD7`, consigne sur la borne

Annotation datee, heure lue par `date`, **limitee a ce qui est de ma competence** (prix recomptes, compte des reports, points rendus chez moi) ; aucune ligne de `sofia-sm` n'est modifiee. Arbre : `round-two` a `b52c4ac5`. Detail : `DEC-0120` A.117 a A.131 ; `preparation-palier-4-camille.md`.

1. **Registre des prix** : `registre-prix-palier-3.txt`, onze lignes ; `awk` : `A 4.00`, `Ap 2.00`, `B 2.00`, `total 8.00` ; `facture 6.50`, `marge 1.50`. Le *"engage 6,50 ; marge 1,50"* de `composition-palier-3.md` 12.2 est juste. `nadia-prof` n'a ecrit aucun prix consomme ; le rejeu de `-07` n'a pas de prix (A.119).
2. **Reports** : `MAT-CE1-01` et `EMC-CE1-01`, premier report ecrit a 23:44, soldes par un verdict dans le palier ; `RL5` a `RL7` livres, pas de second glissement ; `-06`, `-07`, `-11` soldes ; trois stories hors `PAR` a leur deuxieme ecartement (A.118).
3. **Points rendus chez moi** : `AD3` (story `S57-RL5-RL7-SOURCES-DES-COMMENTAIRES`), `AD5` (*"avant tout autre commit de code"* se lit *"avant tout commit de lot"*, A.117), `AD6` (`S57-GARDE-PILE-DE-POLICES` dans le palier, livree `4650aeab`), `AD7` (`S57-R6-COMMENTAIRES-DE-PAQUET`, livree `91ecf180`).
4. **Ecart a signaler** : `rapport-sofia-dev-palier-3.md` section 6 dit les branches de lots coupees *"avant `RL5` a `RL7`"* ; `git merge-base --is-ancestor 4bf3c359 <tete>` sort 0 pour les cinq tetes (A.117).
5. **Consigne de l'utilisatrice sur la borne du palier 4** (relayee, non lue de sa main) : la borne de 8,0 est a `nadia-prof` (`PAR-9`) ; je ne l'ai pas relevee ; la capacite par instance est a son arbitrage prealable du palier 4 (A.126 a A.129). Les regles de charge et de parallele sont a `sofia-sm` (A.130).

Ne modifie aucun prix, lot ou instance, n'engage aucun poste, ne touche ni la matrice ni `composition-palier-3.md`.
