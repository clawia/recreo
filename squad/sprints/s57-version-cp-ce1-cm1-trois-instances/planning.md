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
