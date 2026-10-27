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

## Amendement du 2026-10-21 17:46 (UTC+02:00), cloture du palier 3 : ecarts 5, 6 et 9 du releve du `relecteur-pedagogique`, engagements hors tableau

Annotation datee, heure lue par `date`, casquette Scrum Master. **Rien au-dessus n'est reecrit.** Source : `releve-relecteur-cloture-palier-3.md` (`01105a16`), ecarts 5 et 6 (a `sofia-sm`), ecart 9 (a `camille-po`, qui touche trois lignes de ce fichier). Arbre : `round-two` a `d09655bf`. Ecrit en ASCII. Cet amendement ne ferme pas le palier : `cloture-palier-3.md` le fait.

### 1. Ecart 5 : la section 3 de l'amendement de 23:09 range deux citations sous une source

La section 3 de l'amendement de 23:09 dit que l'ordre est celui de la section 14 de `cloture-palier-2.md` et de `DEC-0120` A.115, et attribue les deux citations a ces deux sources ensemble. **Lire** : *"Rien n'est decide ici"* est de `sofia-sm`, `cloture-palier-2.md` section 14 (ligne 278 ; `DEC-0120` n'a pas cette phrase) ; *"sans engagement"* est de `camille-po`, titre de `DEC-0120` A.115. Meme correction dans `composition-palier-3.md`, section 13.1.

### 2. Ecart 6 : la delegation du 2026-10-19, sans ses mots

La ligne (c) de la section 1 de l'amendement de 23:09 dit la delegation de l'utilisatrice du 2026-10-19 *"relayee a `camille-po` [...] et au lanceur de cette tache"* sans citer ses mots. **Ses mots, tels que `camille-po` les ecrit** (`docs/backlog/backlog.md`, annotation "Palier 3 de s57", composition ecrite ici) : *"continue au mieux pour obtenir la meilleure couverture"*, 2026-10-19. Source en forme 1 de `DEC-0062` : `camille-po`, ce fichier du backlog. **Etat de ces mots : ils ne sont pas dans la liste que `DEC-0120` A.132 confirme** (voir 3) : ils restent relayes, et la variante (i) de la composition s'appuie dessus (`composition-palier-3.md` section 2).

### 3. Ecart 9 sur les lignes de ce fichier : les mots de `AD1`, `AD2` et `AD4`

L'amendement de 23:44 (sections 1 et 8) inscrit `AD1`, `AD2` et `AD4` *"sur la foi d'une reponse relayee"*, forme que `DEC-0062` section 3.1 n'a pas. **Etat au 2026-10-21 17:36** (`d09655bf`, `DEC-0120` A.132, `DEC-0062` amendement E) : l'utilisatrice a repondu *"confirme"* a la question qui reprend ces mots, **relayee a son tour** par l'agent lanceur a `camille-po`, qui ne l'a pas lue de sa main. `camille-po` les fait passer a la forme 2 **pour la liste seulement**. Ce que cela change dans ce fichier : le *"ok"* sur `AD1`, `AD2`, `AD4` a sa forme ; **rien d'autre n'est confirme** : ni la delegation du 2026-10-19 (point 2), ni les lectures des agents, ni le canal par lequel les mots sont arrives la premiere fois. `E5` reste ouvert pour le reste.

### 4. Engagements hors tableau du palier 3, a l'ouverture et a la cloture

**Je ne les ai pas inscrits ici au moment ou ils ont eu lieu.** Le tableau de l'amendement de 23:44 laisse `AD6` et `AD7` chez `camille-po` (*"puis `sofia-sm` (engagement)"*). Trois stories ont ete livrees sans amendement de `planning.md` qui les engage :

| Story | Livraison | Developpeur | Amendement qui l'engage |
|---|---|---|---|
| `S57-R6-COMMENTAIRES-DE-PAQUET` (`AD7`) | `e6c371ad`, fusion `91ecf180` | `sofia-sm`, casquette developpeuse | aucun |
| `S57-GARDE-PILE-DE-POLICES` (`AD6`) | `558dd887`, fusion `4650aeab` | `theo-archi` | aucun : l'engagement de `sofia-sm` n'a pas ete ecrit |
| `S57-SAISIE-CLAVIER-SANS-CORRECTION` | `5a0b55f3`, fusion `03de0f6d` | `theo-archi` | aucun : avant cet amendement, `grep -c` sur ce fichier rendait 0 |

C'est la forme de l'ecart que la lecon 6 de `retro-palier-2.md` voulait eviter (un engagement hors tableau sans ligne). Le fait est ecrit, pas rattrape : aucun amendement retrospectif ne fabrique l'engagement qui n'a pas eu lieu. Les livraisons sont dans `cloture-palier-3.md`, section 1.

### 5. Decisions prises

**Aucune `ADR` ni `DEC` creee ou modifiee par cet amendement.** Il ecrit : deux corrections de citation, les mots de la delegation du 2026-10-19 avec leur etat, l'etat des mots de `AD1`, `AD2`, `AD4` au 2026-10-21 17:36, et trois engagements livres sans amendement. Il ne modifie aucun prix, lot, instance ni critere de story.

## Amendement du 2026-10-21 22:13 (UTC+02:00), ouverture du palier 4

Annotation datee, heure lue par `date`, **inscrite avant l'ouverture de tout worktree du palier 4** (`D2` (c), precedent s56 et paliers 1 a 3). **Rien au-dessus n'est reecrit** ; cet amendement ne remplace aucune phrase. Arbre : `round-two` a `eca79363`, `bash outils/verifier-lignee.sh` sort 0, `git status --short` ne montre que `composition-palier-4.md` (non suivi, ecrit pour ce geste) avant ecriture, casquette Scrum Master. Sources, par auteur : `sofia-sm` (`cloture-palier-3.md`, `e4d54795`, sections 8, 9, 13, 14 et amendements ; `retro-palier-3.md`, `456654f8`) ; `nadia-prof` (`arbitrage-nadia-palier-4.md`, `cae06461` puis `f197dc39`, sections 1 a 8) ; `camille-po` (`preparation-palier-4-camille.md` sections 11 a 13 et `DEC-0120` A.145 a A.152, `e573613d` ; amendement de `S57-BR-FRA-CE1-21`, `eca79363`) ; `theo-archi` (`avis-theo-palier-4.md`, `fedaf20f`, sections 1 a 7). **Cet amendement ouvre le palier 4. Il n'ouvre aucun worktree** : `K1 (a)` des stories demande qu'il soit sur `round-two`, et il l'est par le commit qui le porte.

**Mots de l'utilisatrice**, forme 2 de `DEC-0062` (cites mot pour mot, 2026-10-21, `E5` ferme, `squad/README.md` section 2 bis) : *"LP9 oui on peut augmenter le poids des dessins / QP3 plafond accepté / composition de palier 4 OK / pousse après tout ça"* ; *"1 quatre instances"* ; *"QP6 lea dev pour B et sofia pour A'"* ; *"QP7 après un palier 5 voir 6 je ne sais pas encore"* ; *"QP5 oui"* ; *"E5 oui ça vaut pour mes mots"*. Le seuil de 20,0 est quatre instances de 5,0 ; `PAR-8` ne change pas. *"pousse après tout ça"* est un geste de depot, pas une decision de produit : **je ne pousse pas dans ce geste** (voir 11).

### 1. Regle d'ouverture (`D2`), condition par condition

| Condition de `D2` | Preuve lue sur l'arbre | Etat |
|---|---|---|
| (a) `cloture-palier-3.md` sur `round-two`, pour toutes les instances | `git show HEAD:<chemin>` sort 0 pour `cloture-palier-3.md` (un seul fichier pour A, A' et B) et pour `retro-palier-3.md` | tenue |
| (b) arbitrage prealable de `nadia-prof`, chaque instance | meme commande sur `arbitrage-nadia-palier-4.md` : sort 0 ; section 2.5 (capacite A, B, A'), 3 (lots), 8.4 (quatre instances, composition recevable). **Deux reserves de sa main que la composition ne leve pas** : `LP6` (*"je maintiens ma recommandation de 3.3 de ne pas le prendre"* ; la composition le prend sous sa condition) et `LP9` (*"s'il est pret avant l'ouverture"*, il ne l'est pas). Son total au-dela de 8,0 est la decision de l'utilisatrice, ecrite comme telle (8.4) | tenue |
| (c) composition de `camille-po`, avec la reference de l'arbitrage, heure avant les worktrees | `preparation-palier-4-camille.md` section 11 et `DEC-0120` A.148 : `git show HEAD:<chemin>` sort 0 ; approuvee par *"composition de palier 4 OK"*. Reference de l'arbitrage : `arbitrage-nadia-palier-4.md`. `git worktree list` rend 2 lignes (le checkout et celui de `main`) ; `git branch --list 's57-*'` rend 0 ; aucune branche de la section 6 de `composition-palier-4.md` n'existe | tenue par cet amendement (sections 2 a 4) |
| (d) table d'isolation du palier, repertoires et ports neufs | `composition-palier-4.md` section 6, commite **avant** cet amendement (premier des trois commits de ce geste). **Meme ecart a la lettre de `D2` (d) qu'au palier 3** (*"l'amendement porte la table"*) : la table est dans ce fichier-la et cet amendement la designe ; je ne la copie pas ici pour qu'elle ne puisse pas deriver en deux endroits. Ports : ceux du palier 3, aucun numero neuf ; une ligne par agent lance, `ines-design` (deux lignes), `yanis-qa`, `nadia-prof` (trois), `camille-po` et `relecteur-pedagogique` compris ; 18522 n'est donne a personne | tenue par `composition-palier-4.md`, designe ici |

Les quatre conditions sont tenues. **Ce que `D2` ne demande pas et qui n'est pas tenu** est dans `composition-palier-4.md` section 8 (OV1 a OV10), nomme pour qu'aucun ne passe pour clos. Ceux qui pesent sur l'ouverture des worktrees ou sur la lecture du palier : **les rapports de `marc-dev` et de `lea-dev` du palier 3** (OV1), le registre de prix (OV3, OV4), les conditions de `LP9` (OV5), la lecture de `nadia-prof` sur l'amendement de `S57-BR-FRA-CE1-21` (OV9).

### 2. Composition portee (`camille-po`), prix de `nadia-prof`

Reprise de `DEC-0120` A.148 et de `arbitrage-nadia-palier-4.md` **sans modifier un prix, un lot ou une instance**. Somme recomptee par `awk` sur treize lignes de prix, sortie lue ce jour : A 2,00 ; A' 4,00 ; B 4,50 ; total 10,50 ; engage 7,50 ; non engage 1,50 ; marges 1,50 ; 7 lots.

| Instance | Total | Engage a l'ouverture | Marge, allouee a aucun poste | Non engage, retenu | Lots |
|---|---|---|---|---|---|
| A, `marc-dev` | 2,0 | 1,5 (arbitrage 0,5 ; `LP1` 1,0) | 0,5 | 0 | 1 |
| B, `lea-dev` | 4,5 | 2,5 (arbitrage 0,5 ; `LP2`, `LP3` 2,0) | 0,5 | 1,5 (`LP9`) | 3 |
| A', `sofia-sm` | 4,0 | 3,5 (arbitrage 0,5 ; `LP4`, `LP5`, `LP6` 3,0) | 0,5 | 0 | 3 |
| C | 0 | n'ouvre pas : aucun lot recevable, aucun developpeur distinct, aucun arbitrage | - | - | 0 |

Bornes relues : total 10,5 sur 20,0 (decision de l'utilisatrice, *"1 quatre instances"*) ; plafond de 5,0 par instance et trois lots au plus (`PAR-8`) : B et A' sont au plafond de lots, aucune n'est au plafond de prix ; `PAR-9` a 10,5 sur 20,0 : question a `nadia-prof` (`DEC-0120` A.151 point 3). Ecart a `camille-po` : elle ecrit `engage 9.00` (`LP9` compte engageable) ; j'ecris 7,50 engage et 1,50 non engage, parce que ses conditions (`A.149`) ne sont pas ecrites.

### 3. Ordre et fusion

Ordre produit de `camille-po` (`A.148`, `A.35` : mathematiques, francais, EMC ; `LP9` en dernier dans B, `LP6` en dernier dans A') ; **engage comme ordre de planning**, pas comme decision de produit ou d'architecture. Fusion : premier pret, premier fusionne, une a la fois, **un seul `verify` complet a la fois sur le poste** (jeton), regles `RF1` a `RF13` de `composition-palier-4.md` section 5.2. **Regle des deux casquettes** (section 5.3 du meme fichier) : pendant un lot de A', personne d'autre ne fusionne ; l'agent lanceur ne lance pas un lot de A' tant qu'une branche prete attend sa fusion.

### 4. Postes du palier 4, verification poste par poste (`PAR-16`) : les stories nommees (`K1 (a)`)

Numerotation propre au palier 4. Titres lus par `grep -c "^#\{3,4\} .*<story>" docs/backlog/backlog.md` sur `eca79363`. Le detail (prix, issue de `DEC-0086` section 5, hors composition) est dans `composition-palier-4.md` section 3. **Cette annotation est la ligne de chaque story avant sa fusion** (A-7).

| # | Poste | Story, titres au backlog | Inst. | Agent nominatif | Etat |
|---|---|---|---|---|---|
| p4-1 | Arbitrage prealable et lecture complete | `S57-ARB-PALIER-4` : **0** | A, A', B | `nadia-prof` | **engage sans story**, ecart dit (a `camille-po`) ; part ecrite livree |
| p4-3 | `LP1` `ce1-mots-invariables` en saisie | `S57-BR-FRA-CE1-21` (2) | A | `marc-dev` | **engage** ; worktree sous OV1 |
| p4-4 | `LP2` `ce1-masses-contenances` | `S57-BR-MAT-CE1-10` (1) | B | `lea-dev` | **engage** ; worktree sous OV1 |
| p4-5 | `LP3` `ce1-calendrier-dates` | `S57-BR-MAT-CE1-11` (1) | B | `lea-dev` | **engage** ; worktree sous OV1 |
| p4-6 | `LP9` grille dessinee, forme (a) | `S57-BR-MAT-CE1-14-GRILLE` (6) | B | `lea-dev` | **non engage**, premier report ; engage par un amendement date quand OV5 est ecrit |
| p4-7 | `LP4` `ce1-emc-regle-sanction` | `S57-BR-EMC-CE1-02` (1) | A' | `sofia-sm`, casquette developpeuse | **engage** |
| p4-8 | `LP5` `ce1-emc-droit-devoir` | `S57-BR-EMC-CE1-03` (1) | A' | `sofia-sm`, casquette developpeuse | **engage**, apres la fusion de `LP4` |
| p4-9 | `LP6` `ce1-emc-interet-commun` | `S57-BR-EMC-CE1-04` (1) | A' | `sofia-sm`, casquette developpeuse | **engage sous condition** : verdict de `LP4` ou `LP5` sans refus |
| p4-10 | Garde de la longueur de partie, liste fermee | `S57-GARDE-NOMBRE-DE-MANCHES` (1) | hors `PAR` | `marc-dev`, apres `LP1` ; forme `theo-archi` ; morsures `yanis-qa` | **engage** |

`p4-2` (les trois marges de 0,5) n'est pas un poste de travail. **Hors composition, non engages, aucun developpeur designe** : `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` (garde `E3`), `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE`, les commentaires de `src/` (ecarts 33, 40 a 44, 37 et 38 pour la part code : aucune story), l'extension de `verifier-chemins.sh` (ecart 39, `theo-archi`), `LP7` et `LP8`.

### 5. Affectations nominatives

`marc-dev` pour `LP1` puis `S57-GARDE-NOMBRE-DE-MANCHES` (`RG1`, `RG3` ; le developpeur de la seconde est designe ici, la story le disait non designe) ; `lea-dev` pour `LP2`, `LP3`, `LP9` (*"lea dev pour B"*) ; `sofia-sm`, casquette developpeuse, pour `LP4`, `LP5`, `LP6` (*"sofia pour A'"*). `RG2` tenue, aucun ecart. **A-5 de `retro-palier-3.md`** (*"Je ne compose pas un poste d'un developpeur dont le rapport du palier precedent manque"*) : `ls squad/sprints/s57-*/rapport-*palier-3.md` ne rend que `rapport-sofia-dev-palier-3.md` et `rapport-qualite-palier-3.md`. **Lecture retenue, dite** : A-5 est une porte d'ouverture de worktree, pas d'engagement ; aucun worktree de `marc-dev` ni de `lea-dev` ne s'ouvre avant son `rapport-<developpeur>-palier-3.md` sur `round-two`. La lecture stricte coute un premier report a quatre postes ; elle est a l'utilisatrice (`composition-palier-4.md` section 9, `AP1`).

### 6. Isolation

`composition-palier-4.md` section 6, designee par la condition (d) : repertoires `s57-p4-<agent>-<instance>`, un port de jeu et un port de debogage par ligne, jeton de `verify` `s57-p4-jeton-verify`, consigne ecrite a repeter en tete du message de lancement de chaque agent, branches et worktrees nommes, aucun n'existe a l'ecriture.

**Mesure de la racine a cet amendement** (A-4), lue a 22:13 dans le scratchpad de la session, commandes sous forme portable, sorties lues :

- `find <racine du scratchpad> -maxdepth 1 -mindepth 1 -type f | wc -l` : **72**
- `find <racine du scratchpad> -maxdepth 1 -mindepth 1 -type d | wc -l` : **29**, dont `s57-p4-camille-po-t` et `s57-p4-sofia-sm-t`, les deux seuls de la table du palier 4
- `ls -A <parent des worktrees> | grep -c "^recreo-s57"` : **0**
- `git worktree list | wc -l` : **2** ; `git branch --list 's57-*' | wc -l` : **0**
- `netstat -ano | grep -cE ":(185|192)[0-9]{2} "` : **0**

Ces 72 fichiers et ces 27 repertoires hors table datent du palier 3 et d'avant ; aucun n'est attribue ici. La mesure est faite par la session qui ecrit dans ce scratchpad : **conflit d'interet dit**, `nadia-prof` lit la commande et sa sortie (`arbitrage-nadia-palier-4.md` 8.3 point 2).

### 7. Seuil d'alerte de duree (A-9)

Ouverture a **2026-10-21 22:13** ; seuil de `D4` (41 h 38 min) franchi a **2026-10-23 15:51**. Passe cette heure avec un poste engage non audite, je le dis a l'utilisatrice le jour meme dans `alerte-p4.md` d'une ligne datee. Le seuil ne clot rien et ne limite rien. Le palier 3 a dure 43 h 3 min.

### 8. Compte des reports

Regles de `DEC-0120` A.66 a A.73 : un report est un poste retenu par la composition et non engage a l'ouverture, quelle qu'en soit la cause.

| Poste | Etat a l'ouverture | Compte |
|---|---|---|
| `LP1` a `LP5` | engages | pas de report ; premier ecartement du palier 3 solde par l'engagement (`A.150`) |
| `LP6` | engage sous condition | report a la cloture si la condition ne tient pas : deuxieme ecartement |
| `LP9` | **non engage** | **premier report** ; un troisieme demande une `DEC` ; compte a confirmer par `camille-po` (`composition-palier-4.md` `AP2`) |
| p4-10 | engage | premiere inscription dans une composition |

### 9. Arrets d'agents (A-1)

**Aucun arret n'est ecrit a l'ouverture** : aucun agent du palier 4 n'est lance. Les arrets de s56 et du palier 3 dont l'utilisatrice a les faits : **faits non fournis**, je n'ecris ni heure ni motif (`DEC-0116`, `Q-U6`, close faute de source). Regle de reprise : `composition-palier-4.md` `RF9`. Chaque arret ecrit ici une ligne datee **au moment ou il survient**, avec ses trois champs : qui, quand, ce que la branche portait.

### 10. A decider

Je classe, je ne reponds a aucune. Le detail est dans `composition-palier-4.md` section 9 : `AP1` (lecture d'A-5), `AP2` (compte de `LP9`), `AP3` (`PAR-9`), `AP4` (story d'`E3`), `AP5` (`DEC` pour les deux stories a leur troisieme non-entree), `AP6` (les quatre decisions de `cloture-palier-2.md` section 13, toujours sans reponse : je n'ai modifie ni `ci.yml` ni `outils/mvn-jdk21-test.sh`).

### 11. Ce que cet amendement ne fait pas

N'ouvre aucun worktree, ne cree aucune branche, ne lance aucun agent, ne fusionne rien, **ne pousse rien** (la poussee demandee par l'utilisatrice est un geste a part, apres ces commits, avec `.env` charge). Ne modifie aucun prix, aucun lot, aucune instance, aucun critere de story. Ne prononce aucun Done, ne juge aucun contenu, ne touche pas la matrice (152 / 202, `camille-po`). Ne modifie aucun fichier signe par un autre agent : `planning.md` s'amende par cette annotation datee (`D2`).

### 12. Jobs de `ci.yml` lances pour ces commits

Lances depuis le checkout, avec `composition-palier-4.md` (non suivi) et les amendements de `planning.md` et de `cloture-palier-3.md` presents, avant tout commit. Sorties dans mon repertoire de scratchpad. Je ne deduis aucun job que je n'ai pas lance.

- **`chemins`** : `bash .github/scripts/verifier-chemins-test.sh` : *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."* ; `git ls-files` (moins le test du garde-fou) plus `composition-palier-4.md` passes a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`build`, mesure partielle** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, `ToutesLesReferencesDeDecisionResolventTest` et `LesGardeFousSontMisALEpreuveTest`, `-am` present, **perimetre ces quatre classes** : `BUILD SUCCESS`, **28 tests** (10, 5, 3, 10), 0 echec, 0 erreur, 22:18 a 22:21 (3 min 11 s). Lance parce que ces tests lisent `squad/` par `Files.walk`, fichiers non suivis compris. **Ce n'est pas le total du depot** : le `verify` complet, l'etape *"Package du fat jar"* et le job `outils` ne sont pas lances, ce changement ne touche ni code ni contenu servi ni outil. Dernier total mesure sur arbre propre : 2855 tests, mesure de `theo-archi` sur `50dbd73f` (`avis-theo-palier-4.md` 3.5), **pas la mienne**.
- **`prefixes`** : non lance ; il juge les prefixes des commits de ce geste, ecrits `[SOF]`, et ne s'applique qu'une fois les commits faits. **`outils`** : non lance.
- Lignee : `bash outils/verifier-lignee.sh` sort 0 avant l'ecriture.
- **Ecrit apres la mesure** : la presente section 12 et la phrase de la ligne `build` qui cite 22:18 ; `verifier-chemins.sh` a ete relance apres elles, sortie 0 ; les quatre classes ne l'ont pas ete.

Aucun de ces resultats n'est celui de la CI distante, que je n'ai pas lue. **Le Done technique n'est pas prononce par moi.**

### 13. Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : l'heure d'ouverture du palier 4 ; l'etat des quatre conditions de `D2` ; la composition de `camille-po` portee, sans changement de prix, de lot ni d'instance ; l'ordre engage comme ordre de planning ; les affectations, dont celle de `S57-GARDE-NOMBRE-DE-MANCHES` a `marc-dev` ; la lecture d'A-5 retenue ; la designation de la table d'isolation de `composition-palier-4.md` ; le compte des reports ; l'heure du seuil d'alerte. L'amendement de `DEC-0120` qui porte l'etat d'ouverture est a `camille-po`.

## Amendement du 2026-10-22 15:58 (UTC+02:00), palier 4 : ligne de `LP2` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-lea-mat-ce1-10` dans `round-two` (RF11 : la ligne s'ecrit avant le commit de fusion). Demande de l'utilisatrice du 2026-10-22 : *"lance la fusion des trois lots"*. Premiere des trois fusions, dans l'ordre LP2, LP4, LP1 (premier pret premier fusionne, RF3).

| Story | Competence | Instance, developpeur | Engagement | Rejeux (prix de `nadia-prof`) | Prix du lot | Sommet joue |
|---|---|---|---|---|---|---|
| `S57-BR-MAT-CE1-10`, paquet `ce1-masses-contenances` | `MAT-CE1-10` | B, `lea-dev` | engage a l'ouverture (p4-4) | un rejeu, pris sur la marge de B (0,5) ; aucun rejeu hors marge, aucun depassement | 1,0 | `c29d1d26`, VALIDE (fiche `FICHE-bandit-manchot-masses-contenances-ce1.md`, branche `s57-nadia-p4-b`) |

Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix. Le resultat du `verify` et le SHA de fusion sont dans `RAPPORT.md` et `cloture-palier-4.md`, pas ici.

## Amendement du 2026-10-22 16:14 (UTC+02:00), palier 4 : ligne de `LP4` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-sofia-emc-ce1-02` dans `round-two` (RF11). Deuxieme des trois fusions (ordre LP2, LP4, LP1). Le code et le paquet de ce lot sont de `sofia-sm` sous sa casquette developpeuse ; la lignee a ete lue par `theo-archi` (`lecture-theo-lignee-palier-4.md` section 8, exit 0), le rapport qualite est celui de `yanis-qa` seul (`rapport-qualite-palier-4-lp4-sommet-f36d83bc.md`) et le Done pedagogique est a `nadia-prof` (`composition-palier-4.md` 5.3 point 3).

| Story | Competence | Instance, developpeur | Engagement | Rejeux (prix de `nadia-prof`) | Prix du lot | Sommet joue |
|---|---|---|---|---|---|---|
| `S57-BR-EMC-CE1-02`, paquet `ce1-emc-regle-sanction` | `EMC-CE1-02` | A', `sofia-sm` (developpeuse) | engage a l'ouverture (p4-7) | rejeu 2 sur la marge de A' (0,5) ; rejeu 3 : 0,5 (depassement place par `DEC-0120` A.155 et A.160) ; rejeu 4 : 0,25 (A.160, prix de la fiche) | 1,0 | `f36d83bc`, VALIDE SOUS RESERVE (fiche `FICHE-bandit-manchot-regle-sanction-ce1.md`, branche `s57-nadia-p4-ap`) |

Total A' : 4,0 compose plus 0,5 plus 0,25 de rejeux factures, soit 4,75, sous le plafond de 5,0 de `PAR-8` (`DEC-0120` A.161). L'etat `propose` du rejeu 4 au registre de prix est a `camille-po`. Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix. Le resultat du `verify` et le SHA de fusion sont dans `RAPPORT.md` et `cloture-palier-4.md`.

## Amendement du 2026-10-22 16:26 (UTC+02:00), palier 4 : ligne de `LP1` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-marc-fra-ce1-21` dans `round-two` (RF11). Troisieme des trois fusions (ordre LP2, LP4, LP1).

| Story | Competence | Instance, developpeur | Engagement | Rejeux (prix de `nadia-prof`) | Prix du lot | Sommet joue |
|---|---|---|---|---|---|---|
| `S57-BR-FRA-CE1-21`, paquet `ce1-mots-invariables` en saisie | `FRA-CE1-21` | A, `marc-dev` | engage a l'ouverture (p4-3) | rejeu 2 sur la marge de A (0,5) ; rejeu 3 : 0,25 (depassement place par `DEC-0120` A.155 et A.160) | 1,0 | `8c76b1c7` (sommet `f8d5d875`), VALIDE SOUS RESERVE (fiche `FICHE-saisie-mots-invariables-ce1.md`, branche `s57-nadia-p4-a`) |

Total A : 2,0 compose plus 0,25 de rejeu facture, soit 2,25, sous le plafond de 5,0 de `PAR-8` (`DEC-0120` A.161). Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix. Le resultat du `verify` et le SHA de fusion sont dans `RAPPORT.md` et `cloture-palier-4.md`.

## Amendement du 2026-10-22 17:13 (UTC+02:00), palier 4 : ecrit de `theo-archi` livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de `s57-theo-p4` (`a7133e53`). Demande de l'utilisatrice du 2026-10-22 : *"commence par la branche la plus ancienne et fusionne tout"*. Ordre : `s57-theo-p4`, `s57-ines-p4`, `s57-yanis-p4`, `s57-camille-p4`, du premier commit propre le plus ancien au plus recent.

| Poste | Auteur | Contenu | Prix |
|---|---|---|---|
| lecture de lignee de LP1, LP2 et LP4, ArchUnit, conflits attendus (`composition-palier-4.md` 5.3 point 3) | `theo-archi` | `lecture-theo-lignee-palier-4.md`, 4 commits `[THEO]`, aucun fichier hors `squad/` | transverse, aucun prix alloue |

## Amendement du 2026-10-22 17:15 (UTC+02:00), palier 4 : ecrit d'`ines-design` livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de `s57-ines-p4` (`0cfa32bb`), deuxieme de la serie demandee par l'utilisatrice le 2026-10-22 (*"commence par la branche la plus ancienne et fusionne tout"*). Cette fusion est conditionnee au garde `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` vert sur l'arbre fusionne.

| Poste | Auteur | Contenu | Prix |
|---|---|---|---|
| releves d'ecran au pli 744 x 551 de LP1, LP2 et LP4 (table d'isolation, ligne `ines-design`) | `ines-design` | `mesure-ines-palier-4-pli-744x551.md`, 5 commits `[INES]`, aucun fichier hors `squad/` ; commit `2cbbc1bf` : codes `PR` renommes en *Proposition 1 a 4* | transverse, aucun prix alloue |

## Amendement du 2026-10-22 17:16 (UTC+02:00), palier 4 : ecrit de `yanis-qa` livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de `s57-yanis-p4` (`2d6a6aef`), troisieme de la serie demandee par l'utilisatrice le 2026-10-22. Les quatre commits propres de la branche ne touchent que des rapports de `squad/` ; ses autres commits sont ceux de LP4, deja fusionnes.

| Poste | Auteur | Contenu | Prix |
|---|---|---|---|
| rapport qualite de LP4 seul (`composition-palier-4.md` 5.3 point 3), a trois sommets puis a `f36d83bc` | `yanis-qa` | `rapport-qualite-palier-4-lp4.md` et trois rapports par sommet, 4 commits `[YAN]` | transverse, aucun prix alloue |

## Amendement du 2026-10-22 17:17 (UTC+02:00), palier 4 : ecrits de `camille-po` livres (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de `s57-camille-p4` (`064eba49`), derniere de la serie demandee par l'utilisatrice le 2026-10-22. La branche ne touche que `docs/backlog/backlog.md`, `DEC-0120`, `DEC-index.md`, `MATRICE-couverture.md` et `registre-prix-palier-4.txt`. Je ne modifie ni la matrice ni un prix : je fusionne ce que `camille-po` a ecrit.

| Poste | Auteur | Contenu | Prix |
|---|---|---|---|
| `S57-ARB-PALIER-4` et registre de prix du palier 4 (`OV4`), amendement de `DEC-0120` A.153 a A.163, mention de `MAT-CE1-10` et cellule de `FRA-CE1-21` en matrice | `camille-po` | 2 commits `[CAM]` | prix de l'arbitrage pris sur le 0,5 de chaque instance, aucun prix neuf |

Etat de la matrice a la fusion : les lignes `MAT-CE1-10`, `FRA-CE1-21` et `EMC-CE1-02` ne bougent pas dans ce commit. Leur passage (`en cours`, `couvert-valide`) est a `camille-po`, apres cette fusion.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ligne de `LP3` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-lea-mat-ce1-11` dans `round-two` (RF11). Premiere des cinq fusions demandees par l'utilisatrice le 2026-10-22 (ordre LP3, LP5, p4-10, fiches de LP3 et LP5, branches de documents).

| Story | Competence | Instance, developpeur | Engagement | Rejeux (prix de `nadia-prof`) | Prix du lot | Sommet joue |
|---|---|---|---|---|---|---|
| `S57-BR-MAT-CE1-11`, paquet `ce1-calendrier-dates` | `MAT-CE1-11` | B, `lea-dev` | engage a l'ouverture (p4-5) | un refus (motif M1, item 18) puis un rejeu ; prix du rejeu non inscrit a `registre-prix-palier-4.txt` a la date de cet amendement, a `camille-po` | 1,0 | `f863fa7a` (sommet `003fd4fb`), VALIDE SOUS RESERVE (fiche `FICHE-bandit-manchot-calendrier-dates-ce1.md`, branche `s57-nadia-p4-lp3`) |

RF13 : `git diff --name-only f863fa7a 003fd4fb` ne nomme que `rapport-lea-dev-palier-4-lp3.md` : ni le paquet, ni un gabarit, ni une feuille de style, ni du code sous `src/main`. Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix. Le resultat du `verify` et le SHA de fusion sont dans `RAPPORT.md` et `cloture-palier-4.md`.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ligne de `LP5` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-sofia-emc-ce1-03` dans `round-two` (RF11). Deuxieme des cinq fusions demandees par l'utilisatrice le 2026-10-22 (ordre LP3, LP5, p4-10, fiches de LP3 et LP5, branches de documents).

| Story | Competence | Instance, developpeur | Engagement | Rejeux (prix de `nadia-prof`) | Prix du lot | Sommet joue |
|---|---|---|---|---|---|---|
| `S57-BR-EMC-CE1-03`, paquet `ce1-emc-droit-devoir` | `EMC-CE1-03` | A', `sofia-sm` (developpeuse) | engage a l'ouverture (p4-8), apres la fusion de `LP4` | un refus (item 14) puis un rejeu ; prix du rejeu non inscrit a `registre-prix-palier-4.txt` a la date de cet amendement, a `camille-po` | 1,0 | `5b9bb648` (sommet `ebdfa265`), VALIDE SOUS RESERVE (fiche `FICHE-bandit-manchot-droit-devoir-ce1.md`, branche `s57-nadia-p4-lp5`) |

RF13 : `git diff --name-only 5b9bb648 fdeff890` ne nomme que `PaquetCe1EmcDroitDevoirTest.java`, une fixture de contre-exemple et `rapport-sofia-dev-palier-4-lp5.md` : ni le paquet, ni un gabarit, ni une feuille de style, ni du code sous `src/main`.

**Ecart a `composition-palier-4.md` 5.3 point 3, dit.** La lecture de lignee de `theo-archi` (`lecture-theo-lignee-palier-4-lp3-lp5.md`) et le rapport qualite de `yanis-qa` (`rapport-qualite-palier-4-lp5-lp3.md`) portent sur `db5e0e6d`, sommet de `LP5` avant son rejeu ; le sommet fusionne est `fdeff890` (quatre commits de `sofia-sm` de plus : contenu des items 14 et 24, indice, test de `D15`). Les deux ecrits sont sur leurs branches et pas encore sur `round-two` ; aucun des deux n'a relu le sommet fusionne. La fusion suit l'ordre explicite de l'utilisatrice du 2026-10-22. Le `verify` complet de l'arbre fusionne est le seul jugement technique de ce sommet ; le total de tests apres un lot de A' est a `yanis-qa`. Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix. Le resultat du `verify` et le SHA de fusion sont dans `RAPPORT.md` et `cloture-palier-4.md`.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ligne de `p4-10` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-marc-garde-nombre-de-manches` dans `round-two` (RF11). Troisieme des cinq fusions demandees par l'utilisatrice le 2026-10-22 (ordre LP3, LP5, p4-10, fiches de LP3 et LP5, branches de documents).

| Story | Instance, developpeur | Engagement | Prix | Sommet |
|---|---|---|---|---|
| `S57-GARDE-NOMBRE-DE-MANCHES`, garde `ChaqueJeuDuCatalogueDureDixManchesSaufDerogationNommeeTest` | A, `marc-dev` | engage a l'ouverture (p4-10), hors `PAR` | hors `PAR`, aucun prix | `4ef4ea82` |

Le diff de la branche ne touche que le test, cinq catalogues de contre-exemple et `rapport-marc-dev-palier-4-garde-manches.md` : aucun contenu servi a l'enfant, aucun paquet, aucun gabarit, aucun code sous `src/main`. Aucun jeu de `nadia-prof`, donc RF13 sans objet. Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix. Le resultat du `verify` et le SHA de fusion sont dans `RAPPORT.md` et `cloture-palier-4.md`.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ecrit de camille-po livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-camille-p4-b` (52817981) dans `round-two` (RF11). Premiere des quatre des cinq fusions demandees par l'utilisatrice le 2026-10-22 (branches de documents). La branche porte la matrice a 154 / 202, `DEC-0120` A.164 a A.170 (rejeux de `LP3` et `LP5`), le registre de prix et le backlog. Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que camille-po a ecrit.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ecrit de theo-archi livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-theo-p4-10` (afe31fab) dans `round-two` (RF11). Deuxieme des quatre des cinq fusions demandees par l'utilisatrice le 2026-10-22 (branches de documents). La branche porte `lecture-theo-p4-10-garde-manches.md` (forme du garde de la longueur de partie, `ADR-0013`, lecture de la branche de `marc-dev`). Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que theo-archi a ecrit.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ecrit de theo-archi livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-theo-p4-lp3-lp5` (d150618a) dans `round-two` (RF11). Troisieme des quatre des cinq fusions demandees par l'utilisatrice le 2026-10-22 (branches de documents). La branche porte `lecture-theo-lignee-palier-4-lp3-lp5.md` (lignee, ArchUnit et conflits de `LP3` et `LP5`), lue a `db5e0e6d` et `c8404173`, sommets de lots anterieurs a leur rejeu. Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que theo-archi a ecrit.

## Amendement du 2026-10-22 (UTC+02:00), palier 4 : ecrit de yanis-qa livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-yanis-p4-lp3-lp5` (fc064977) dans `round-two` (RF11). Quatrieme des quatre des cinq fusions demandees par l'utilisatrice le 2026-10-22 (branches de documents). La branche porte `rapport-qualite-palier-4-lp5-lp3.md` (rapport qualite de `LP5` a `db5e0e6d` et de `LP3` a `c8404173`, sommets de lots anterieurs a leur rejeu) ; il ne juge pas les sommets fusionnes `fdeff890` et `003fd4fb`. Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que yanis-qa a ecrit.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ligne de `LP6` livree (RF11, `composition-palier-4.md` 5.2)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-sofia-emc-ce1-04` dans `round-two` (RF11). Premiere des six fusions demandees par l'utilisatrice le 2026-10-23 (ordre : le lot, la fiche de `nadia-prof`, les branches de documents de `theo-archi`, `yanis-qa`, `ines-design`).

| Story | Competence | Instance, developpeur | Engagement | Rejeux (prix de `nadia-prof`) | Prix du lot | Sommet joue |
|---|---|---|---|---|---|---|
| `S57-BR-EMC-CE1-04`, paquet `ce1-emc-interet-commun` | `EMC-CE1-04` | A', `sofia-sm` (developpeuse) | engage sous condition a l'ouverture (p4-9) ; la condition (verdict de `LP4` ou `LP5` sans refus) est a `camille-po`, je ne la juge pas | deux refus puis un second rejeu ; prix des rejeux non inscrit a `registre-prix-palier-4.txt` a la date de cet amendement, a `camille-po` | 1,0 | `c975a7b8`, VALIDE SOUS RESERVE (fiche `FICHE-bandit-manchot-interet-commun-ce1.md`, branche `s57-nadia-p4-lp6`) |

Ecrits exiges par `composition-palier-4.md` 5.3 point 3, sur le disque, sur leurs branches, tous au sommet `c975a7b8` : lignee de `theo-archi` (branche `s57-theo-p4-lp6`, `lecture-theo-lignee-palier-4-lp6-passe-3.md`, lignee saine, aucun defaut bloquant), rapport qualite de `yanis-qa` (branche `s57-yanis-p4-lp6`, `verify` 3232 vert, survivants G5, G11, LG5, LG11 nommes), Done pedagogique de `nadia-prof` (branche `s57-nadia-p4-lp6`), releve au pli de `ines-design` (branche `s57-ines-p4-lp6`, 0 element sous le pli). **Je n'ai pas juge ma propre branche** : la lignee est lue par `theo-archi`, la qualite par `yanis-qa`, la pedagogie par `nadia-prof` ; ma part est la mecanique de fusion (RF10, RF4, RF8, RF13, RF3), dont les sorties sont dans `RAPPORT.md` et `cloture-palier-4.md`.

RF13 : `git diff --name-only c975a7b8` contre le commit de fusion ne nomme que ce fichier (`planning.md`) : ni le paquet, ni un gabarit, ni une feuille de style, ni du code sous `src/main`. `nadia-prof` a joue le jar de `c975a7b8`.

**Etat des reserves et du defaut, a l'instant de la fusion,, sans en lever aucun :**

- Reserves 3, 6 et 7 de `nadia-prof` : **ouvertes**, pour `camille-po` (stories a ecrire ; la fiche les nomme dans son verdict). Elles sont non bloquantes ; je ne les ai ni levees ni traitees. La reserve 3 (pli) est portee *"maintenue, non mesuree"* dans la fiche ; le releve de `ines-design` (0 element sous le pli) est posterieur et la fiche n'a pas ete amendee : constat, pas levee.
- Defaut `T5` de `theo-archi` (aucun test n'interdit les mots de portee *chacun*, *tous*, *toute* dans les scenes ; mutation M3 verte) : **ouvert**, non bloquant, **route a `camille-po`** pour une story a ecrire. Aucun test n'est ajoute par cette fusion.
- Survivants G5, G11, LG5, LG11 de `yanis-qa` : ouverts, nommes dans son rapport.

Cet amendement ne prononce aucun Done et ne touche ni la matrice ni un prix.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ecrit de nadia-prof livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-nadia-p4-lp6` (f5ae6d5f) dans `round-two` (RF11). Deuxieme des six fusions demandees par l'utilisatrice le 2026-10-23. La branche porte `FICHE-bandit-manchot-interet-commun-ce1.md` : Done pedagogique de `LP6` **VALIDE SOUS RESERVE** sur `c975a7b8` (le jar de ce commit joue par `nadia-prof`), reserves 3, 6 et 7 ouvertes pour `camille-po`. Aucune story n'est livree par cette branche. Je ne prononce aucun Done : je fusionne ce que `nadia-prof` a ecrit, et je ne modifie ni la matrice ni un prix. Les reserves 3, 6 et 7 et le defaut `T5` restent dans l'etat decrit par l'amendement de `LP6` ci-dessus (ouverts, a `camille-po`).

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ecrit de theo-archi livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-theo-p4-lp6` (d2a6fd72) dans `round-two` (RF11). Troisieme des six fusions demandees par l'utilisatrice le 2026-10-23. La branche porte `lecture-theo-lignee-palier-4-lp6.md`, `-reprise.md` et `-passe-3.md` (lignee, ArchUnit et defaut `T5` de `LP6`, lus jusqu'a `c975a7b8`). Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que theo-archi a ecrit. Les reserves 3, 6 et 7 de `nadia-prof` et le defaut `T5` restent ouverts, a `camille-po`, comme dans l'amendement de `LP6` ci-dessus ; cet ecrit ne leve rien.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ecrit de yanis-qa livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-yanis-p4-lp6` (2cd87ab1) dans `round-two` (RF11). Quatrieme des six fusions demandees par l'utilisatrice le 2026-10-23. La branche porte trois `rapport-qualite-palier-4-lp6-sommet-*.md` (`LP6` a `cbfb152e`, `ebbb2136` et `c975a7b8`, ce dernier `verify` 3232 vert, survivants G5, G11, LG5, LG11 nommes). Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que yanis-qa a ecrit. Les reserves 3, 6 et 7 de `nadia-prof` et le defaut `T5` restent ouverts, a `camille-po`, comme dans l'amendement de `LP6` ci-dessus ; cet ecrit ne leve rien.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ecrit de ines-design livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-ines-p4-lp6` (c4cd299a) dans `round-two` (RF11). Cinquieme des six fusions demandees par l'utilisatrice le 2026-10-23. La branche porte deux `mesure-ines-palier-4-lp6-pli-744x551*.md` (releve au pli de `LP6`, 0 element sous le pli a `c975a7b8`). Aucune story n'est livree par cette branche, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que ines-design a ecrit. Les reserves 3, 6 et 7 de `nadia-prof` et le defaut `T5` restent ouverts, a `camille-po`, comme dans l'amendement de `LP6` ci-dessus ; cet ecrit ne leve rien.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ecrit de nadia-prof livre, levee de la reserve 3 (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-nadia-p4-lp6b` (5d038278) dans `round-two` (RF11), premiere des deux fusions de cloture demandees par l'utilisatrice le 2026-10-23. Lignee verifiee dans le worktree de la branche (RF10) : `c08e49ab` est ancetre, arbre propre. La branche ne touche qu'un fichier, `squad/conformite/FICHE-bandit-manchot-interet-commun-ce1.md` : `nadia-prof` y leve la reserve 3 (pli 744x551) de `LP6` sur la mesure de `ines-design` a `c975a7b8`. Aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. Aucune story n'est livree, je ne prononce aucun Done et ne modifie ni la matrice ni un prix. Les reserves 6 et 7 et le defaut `T5` restent dans l'etat decrit par l'amendement de `LP6`, sauf ce que le tri de `camille-po` en dit.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : ecrit de camille-po livre, cloture produit (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-camille-p4-cloture` (0a51d59c) dans `round-two` (RF11), seconde des deux fusions de cloture. Lignee verifiee dans le worktree de la branche (RF10) : `c08e49ab` est ancetre, arbre propre. La branche porte `arbitrage-camille-cloture-palier-4.md`, `DEC-0120` (amendement A.171 a A.179), `DEC-0125` (nouvelle), `DEC-index.md`, `MATRICE-couverture.md` (154/202 a 156/202), `docs/backlog/backlog.md` et `registre-prix-palier-4.txt`. Aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet (`git diff --name-only`). Je ne prononce aucun Done, ne leve aucune reserve et ne fixe aucun prix : je fusionne ce que `camille-po` a ecrit. Ce fichier de planning n'est pas modifie par la branche.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : correction de la ligne 2579 de DEC-0120 livree par camille-po (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-camille-p4-cloture-b` (d8ea2a2b) dans `round-two` (RF11). Le `verify` complet du sommet `ad7f8022` etait rouge (2 echecs de `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest`, ligne 2579 de `DEC-0120`) : `camille-po` corrige cette ligne, un commit, une ligne. Lignee verifiee dans le worktree de la branche avant la fusion (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre, le sommet `85d45e87` de `round-two` est l'ancetre commun. `git diff --name-only round-two...s57-camille-p4-cloture-b` ne rend qu'un fichier, `squad/decisions/produit/DEC-0120-...md`. Aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. Je ne prononce aucun Done, ne leve aucune reserve et ne fixe aucun prix : je fusionne ce que `camille-po` a ecrit.

## Amendement du 2026-10-23 (UTC+02:00), palier 4 : confirmation ArchUnit de theo-archi livree (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-theo-p4-archunit` (798de7b3) dans `round-two` (RF11), premiere des trois fusions sans poussee demandees par l'utilisatrice le 2026-10-23. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre. `git diff --name-only round-two...s57-theo-p4-archunit` ne rend qu'un fichier, `confirmation-archunit-palier-4.md` : aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. La branche porte la confirmation ArchUnit du palier 4 que `cloture-palier-4.md` donnait pour absente. Je ne prononce aucun Done, ne leve aucune reserve et ne modifie ni la matrice ni un prix : je fusionne ce que `theo-archi` a ecrit.

## Amendement du 2026-10-23 (UTC+02:00), palier 5 : avis de theo-archi sur les dessins livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-theo-p5-dessins` (1c7eb423) dans `round-two` (RF11), deuxieme des trois fusions sans poussee demandees par l'utilisatrice le 2026-10-23. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre. `git diff --name-only round-two...s57-theo-p5-dessins` ne rend qu'un fichier, `avis-theo-palier-5-dessins.md` : aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. La branche porte l'avis de `theo-archi` sur la rotation du triangle rectangle de `LP7` et sur cinq lignes de mathematiques CE1 a dessins. Aucun palier 5 n'est compose par cette fusion, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que `theo-archi` a ecrit.

## Amendement du 2026-10-23 (UTC+02:00), palier 5 : proposition de camille-po livree (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-camille-p5-proposition` (1f76898e) dans `round-two` (RF11), troisieme et derniere des trois fusions sans poussee demandees par l'utilisatrice le 2026-10-23. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre. `git diff --name-only round-two...s57-camille-p5-proposition` ne rend que six fichiers, tous sous `squad/` ou `docs/` : `proposition-camille-palier-5-ce1.md`, `DEC-0120` (amendement A.180 a A.197), `DEC-0123`, `DEC-0125`, `DEC-index.md` et `docs/backlog/backlog.md` (annotations). Aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. Cette proposition n'est ni `composition-palier-5.md` ni l'arbitrage prealable de `nadia-prof` : aucun poste n'est engage par cette fusion, aucun Done n'est prononce, je ne fixe aucun prix et ne modifie pas la matrice. Je fusionne ce que `camille-po` a ecrit.

## Amendement du 2026-10-25 (UTC+02:00), palier 5 : arbitrage de nadia-prof sur LP7 et D5 livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-nadia-p5-a` (f78f554f) dans `round-two` (RF11), premiere des deux fusions sans poussee demandees par l'utilisatrice le 2026-10-25. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre. `git diff --name-only round-two...s57-nadia-p5-a` ne rend qu'un fichier, `arbitrage-nadia-palier-5-lp7-d5.md` : aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. La branche porte l'arbitrage de `nadia-prof` sur le second triangle rectangle de `LP7`, `PAR-13` et les cinq lignes de `D5`. Aucun palier 5 n'est compose par cette fusion, aucun Done n'est prononce, et je ne modifie ni la matrice ni un prix : je fusionne ce que `nadia-prof` a ecrit.

## Amendement du 2026-10-25 (UTC+02:00), palier 5 : prix de nadia-prof pour le rejeu de LP6 livre (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-nadia-p5-b` (70998ce6) dans `round-two` (RF11), seconde et derniere des deux fusions sans poussee demandees par l'utilisatrice le 2026-10-25. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre. `git diff --name-only round-two...s57-nadia-p5-b` ne rend qu'un fichier, `prix-nadia-rejeu-lp6.md` : aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. La branche porte le prix du rejeu 2 de `LP6` que le registre du palier 4 laissait non chiffre. Aucun palier 5 n'est compose par cette fusion, aucun Done n'est prononce, et je ne modifie ni le registre, ni la matrice, ni une `DEC` : je fusionne ce que `nadia-prof` a ecrit.

## Amendement du 2026-10-25 (UTC+02:00), palier 5 : consignation de camille-po livree (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-camille-p5-consignation` (698aef59) dans `round-two` (RF11), fusion sans poussee demandee par l'utilisatrice le 2026-10-25. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, arbre propre, le sommet `2de8032a` de `round-two` est l'ancetre commun. `git diff --name-only round-two...s57-camille-p5-consignation` ne rend que six fichiers, tous sous `squad/` ou `docs/` : `registre-prix-palier-4.txt`, `DEC-0120` (amendement A.198 a A.209), `DEC-0123` (dixieme amendement), `DEC-index.md`, `proposition-camille-palier-5-ce1.md` et `docs/backlog/backlog.md`. Aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. Aucun palier 5 n'est compose par cette fusion, aucun Done n'est prononce, je ne fixe aucun prix et ne modifie pas la matrice. Je fusionne ce que `camille-po` a ecrit.

## Amendement du 2026-10-25 (UTC+02:00), palier 5 : consignation de V3 et dix paliers de camille-po livrees (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-camille-p5-dix-paliers` (4c127aa7, parents f2bd783e puis 4c127aa7) dans `round-two` (RF11), premiere des deux fusions sans poussee demandees par l'utilisatrice le 2026-10-25. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, `c5d07a4a` est l'ancetre commun. `git diff --name-only round-two...s57-camille-p5-dix-paliers` ne rend que quatre fichiers, tous sous `squad/` ou `docs/` : `DEC-0120` (amendement A.210 a A.228), `DEC-index.md`, `proposition-camille-palier-5-ce1.md` et `docs/backlog/backlog.md`. Aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. La branche porte la consignation de V3 (le depassement ecrit de l'utilisatrice qui fait entrer questionner le monde CE1 dans s57), la trajectoire, le releve par scenario et les stories a ecrire. PO2 reste ouvert cote `camille-po` : `nadia-prof` est saisie, la reponse n'est pas dans cette branche. Aucun releve inscrit, aucune story ni paquet ecrit, aucun statut de matrice change. Aucun Done n'est prononce, je ne fixe aucun prix. Je fusionne ce que `camille-po` a ecrit.

## Amendement du 2026-10-25 (UTC+02:00), palier 5 : reponse de nadia-prof sur V3 livree, condition de consignation non remplie (RF11)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit qui precede la fusion de la branche `s57-nadia-p5-c` (3a22f063) dans `round-two` (RF11), seconde et derniere des deux fusions sans poussee demandees par l'utilisatrice le 2026-10-25. Lignee verifiee dans le worktree de la branche (RF10) : `bash outils/verifier-lignee.sh` sort 0, `c5d07a4a` est l'ancetre commun. `git diff --name-only round-two...s57-nadia-p5-c` ne rend qu'un fichier, `arbitrage-nadia-palier-5-qlm-v3.md` : aucun fichier sous adapters, domain, application, bootstrap, outils, .github, aucun paquet. La branche porte la reponse de `nadia-prof` sur V3 : depassement de sa regle admis, audit sous conditions. Sa condition : `camille-po` consigne le texte de V3 mot pour mot dans `DEC-0120` avant le premier lot. **Cette condition n'est pas remplie** : la branche precedente consigne V3, mais elle a ete ecrite avant la reponse de `nadia-prof` (PO2 ouvert), et je n'ai pas verifie qu'elle porte le texte mot pour mot. Je ne clos pas cette condition et je n'ouvre aucun lot de questionner le monde. Aucun Done n'est prononce, je ne modifie ni la matrice, ni une `DEC`, ni un prix. Je fusionne ce que `nadia-prof` a ecrit.

## Amendement du 2026-10-26 (UTC+02:00), palier des neuf lots de questionner le monde CE1 : developpeur designe, declencheur de la lecture avant `verify`, fusions serialisees

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit `s57-p5-sofia-planning`. Il execute les deux lignes que `DEC-0120` A.262 donne a `sofia-sm` (*"designer le developpeur distinct de `lea-dev` ; `planning.md` : le declencheur de `P0` et les fusions serialisees"*). **Il n'ouvre aucun palier et n'engage aucun lot** : l'ouverture reste soumise a `D2` (cloture du palier precedent, composition portee par un amendement date, table d'isolation). Les choix de l'utilisatrice du 2026-10-26 (`DEC-0120` A.261 : instance neuve, horloge `MAT-CE1-11` avec les neuf lots au palier 9, `K2` nommee, relevement de `PAR-8` a 19,20 en plafond, marge 1,5) [CORRIGE le 2026-10-26, amendement "plafond de PAR-8 a 20,20 et portee des rejeux" en fin de fichier : le plafond est 20,20, non 19,20 ; texte d'origine conserve] [corrige par A.273 : plafond de `PAR-8` du palier 9 releve a 22,20 le 2026-10-26 ; texte d'origine conserve] sont portes par `camille-po` (A.263) ; je ne les recopie pas et ne fixe aucun prix. `nadia-prof` reexamine sa condition `P3` et `theo-archi` ecrit l'exemption de garde d'`AIM-CE1-2` : je n'ai pas lu leurs fichiers en cours.

### 1. Developpeur designe : `marc-dev`

**Designation.** `marc-dev` est le developpeur des neuf lots `S57-BR-QLM-CE1` (`-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`) et, a ce titre, l'auteur de l'entree du test de rang de chaque paquet dans le commit de son premier jet (`DEC-0120` A.259, `QC10`). Un seul developpeur pour l'instance, comme `K1` de `nadia-prof` et A.260 le posent.

**Motif, chaque terme renvoie a une ligne lue.**

1. **Distinct de `lea-dev`, exigence de A.260 et de `nadia-prof`.** `lea-dev` porte B (`MAT-CE1-01`, `LP2`, `LP3`, `LP9`, lignes p4-4 a p4-6 de ce fichier), et l'horloge `MAT-CE1-11` que l'utilisatrice met avec les neuf lots au palier 9 est une ligne de B. `RG2` (`composition-palier-3.md`, ligne 95) exige des developpeurs distincts sur des instances distinctes, parce que les lots d'un palier partagent `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` : `lea-dev` sur l'horloge et sur les neuf lots reproduirait l'ecart de `AD1`, que je n'ai aucune decision pour reconduire.
2. **Charge.** Section "Charge des developpeurs" de ce fichier : `lea-dev` 529 tours a s56 contre 183 pour `marc-dev`. Je n'ai pas refait la mesure (un seul rapport de tokens par sprint, a la review).
3. **Aucune ligne engagee pour `marc-dev` apres le palier 4.** Ses lignes de palier 4, `LP1` (p4-3) et `S57-GARDE-NOMBRE-DE-MANCHES` (p4-10), sont livrees ; je n'ai trouve aucune autre ligne engagee a son nom dans ce fichier. Je n'ai pas relu les fichiers des autres agents ecrits en parallele.
4. **`RG1` (aucun lot de francais CE1 pour `lea-dev`) ne s'applique pas** a ces neuf lots, qui sont de questionner le monde : la designation ne s'appuie pas dessus, elle s'appuie sur 1 a 3.

**Raison ecrite contraire : aucune.** `squad/EQUIPE.md` (lignes 73 a 78) donne aux deux developpeurs seniors le meme role, sans specialite de matiere ; aucune ligne du depot lue ne dit que `marc-dev` est indisponible au palier 9. `RG4` (`composition-palier-3.md`, ligne 97) s'applique : un worktree vivant a la fois, il me dit quand il passe d'un lot au suivant.

**Ce que la designation ne fait pas.** Elle ne prononce aucun Done, ne choisit ni le palier ni l'instance (choix de l'utilisatrice), n'engage aucun lot, ne modifie ni la matrice ni un prix. Je reste casquette Scrum Master : je ne porte pas la casquette developpeuse sur ces lots.

### 2. Declencheur de la lecture avant `verify` (`P0`)

Definition source : `DEC-0120` A.260, ligne `P0` (*"`-01` seul : premier jet commite seul, inscrit dans `planning.md` (lot, commit, heure) avant tout `verify` complet ; `lecture-nadia-premier-jet-<paquet>.md` ; correction ; ensuite seulement les huit autres premiers jets"*), et `arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md`, point 3 du paragraphe sur l'ordre (*"chaque premier jet est inscrit dans `planning.md` (lot, commit, heure) avant tout `verify` complet du paquet (6.1 de V3, `QC7`)"*).

1. **Le declencheur est une ligne de ce fichier, pas un message.** Tant que la ligne d'un premier jet (lot, commit, heure) n'est pas ecrite dans un amendement date de `planning.md`, **aucun `verify` complet de ce paquet n'est lance**, par aucun agent.
2. **Qui l'ecrit.** `marc-dev` commite le premier jet seul et me donne le lot, le SHA du commit et l'heure ; **j'ecris la ligne**, dans un commit de `planning.md` qui precede son `verify` complet. Je ne l'ecris pas pour un commit que je n'ai pas lu sur le disque.
3. **Ce que le commit du premier jet contient.** Le paquet et, des ce commit, l'entree de rang du paquet avec ses totaux exacts dans le motif (A.259). Ce commit n'a donc pas encore passe le `verify` complet : c'est le cas que `P0` prevoit.
4. **Ordre pour `-01`.** (a) premier jet de `-01` commite seul ; (b) ligne inscrite ici ; (c) `nadia-prof` ecrit `lecture-nadia-premier-jet-<paquet>.md` ; (d) `marc-dev` corrige ; (e) **seulement ensuite** les huit autres premiers jets sont commites. Un correctif qui ajoute ou retire un item recompte le motif de l'entree de rang dans le meme commit (A.259).
5. **Les huit autres.** Meme declencheur, ligne par ligne, par paires dans l'ordre `-02`, `-03` ; `-04`, `-05` ; `-06`, `-08` ; `-10`, `-12` (`P1`). Chaque paire est inscrite avant le `verify` complet de chacun de ses paquets, et sa lecture de `nadia-prof` precede la paire suivante.
6. **Audits.** `P2` : dans le meme ordre, `-01` d'abord, chaque lot apres son `verify` ; `QC3` : aucune entree `ce1-qlm-*` au catalogue avant un Done (A.260, `P2`).
7. **Tableau a remplir, une ligne par premier jet**, ajoutee par amendement date. Il est vide a ce jour.

| Lot | Commit du premier jet | Heure d'inscription | Lecture de `nadia-prof` |
|---|---|---|---|
| (aucun premier jet commite) | - | - | - |

### 3. Fusions des neuf lots : serialisees

Source : `avis-theo-palier-5-neuf-lots-qlm-ce1.md` 3.3 et 3.4, repris par `DEC-0120` A.260 (*"serialisees, chaque branche part du sommet qui porte la fusion precedente"*).

1. **Une fusion a la fois, dans l'ordre de `P1` :** `-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`. Chaque branche de lot part du sommet de `round-two` qui porte la fusion precedente, ou s'y rebase avant de fusionner (`theo-archi` 3.3). La fusion d'un lot ne s'ecrit pas tant que le lot precedent de la liste n'est pas fusionne.
2. **Motif.** La table du rang, `catalogue.yaml` et l'inventaire du port entrant sont des ajouts en fin de liste : deux branches paralleles y entrent en conflit. Un conflit se resout en gardant les deux entrees, et la clause des comptes reverifie le resultat ; il coute un `verify` de plus, jamais une assertion affaiblie.
3. **Verification a chaque fusion, par moi.** Lignee par `bash outils/verifier-lignee.sh` dans le worktree de la branche (arbre propre) avant la fusion, `git diff --name-only` contre `round-two` pour borner le perimetre, et total de tests etabli par moi sur l'arbre fusionne, jamais relaye d'un agent. Je nomme les jobs de `ci.yml` lances avant de declarer la porte technique.
4. **Un seul test des conditions transverses**, ecrit dans `-01` ; les huit autres lots n'ont pas a toucher ce fichier (`theo-archi` 3.4). Un lot qui le modifie est signale a `theo-archi`, pas fusionne.
5. **Non tranche ici.** `QC3` (aucune entree `ce1-qlm-*` au catalogue avant un Done) et l'ajout en fin de `catalogue.yaml` que `theo-archi` decrit se lisent ensemble : ce que le catalogue contient au moment de chaque fusion est a `theo-archi` et `camille-po`, je ne le tranche pas.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : la designation de `marc-dev` (execution de `DEC-0120` A.262, ligne `sofia-sm`), le declencheur de `P0` et la serialisation des fusions. Restent ouverts, non clos par cet amendement : l'amendement A.263 de `camille-po`, la condition `P3` de `nadia-prof` et l'exemption de garde d'`AIM-CE1-2` de `theo-archi`.

## Amendement du 2026-10-26 (UTC+02:00), palier des neuf lots de questionner le monde CE1 : ordre des audits, engage de 13,50, lecture du cout reel de P0

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit `s57-p5-sofia-ordre`. Il execute la ligne que `reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md` (section 4, ligne `sofia-sm`) lui donne : *"dans `planning.md`, l'ordre de 1.3 et de 2 : neuf premiers audits, l'horloge, puis les rejeux"*. Il complete l'amendement du meme jour qui designe `marc-dev` ; il ne le modifie pas. **Il n'ouvre aucun palier et n'engage aucun lot** : l'ouverture reste soumise a `D2`. Il ne recalcule rien de ce que `camille-po` porte : `A.265` de `DEC-0120` est consigne en parallele par elle, dans ses fichiers.

### 1. Ordre des audits et des rejeux, cite de `nadia-prof`

Ordre inscrit : **neuf premiers audits (`P2`, dans l'ordre de `P1`), puis l'horloge `MAT-CE1-11`, puis les rejeux.** Source : la reponse citee, section 2 (*"l'horloge est auditee **apres les neuf premiers audits de `P2` et avant tout rejeu**"*) et section 4.

Regles de `P3` reformulee, citees sans reformulation (section 1.3, lignes 60 a 63 de la reponse) :

> `P3` - rejeux : **autant que la marge en paie** (trois a 1,5, au prix de 0,50 du rejeu, A.199), attribues aux premiers lots refuses dans l'ordre de `P2` ; **un rejeu au plus par lot dans le palier 9** ; **aucun rejeu avant le premier audit de chacun des dix lots du palier** ; tout rejeu hors marge, et tout second rejeu d'un meme lot, au palier 10 ; au palier 10, un rejeu refuse va a s58.

1. **Un rejeu au plus par lot dans le palier 9.**
2. **Aucun rejeu avant le premier audit des dix lots** (les neuf plus l'horloge). Motif de `nadia-prof` : trois rejeux places avant les derniers premiers audits pousseraient l'horloge hors du palier 9 ; *"Je ne fais pas cet echange."*
3. **Lecture des rejeux de l'horloge** : `nadia-prof` lit les trois rejeux comme ceux de l'instance neuve, l'horloge ayant sa marge dans ses 2,50 (A.215). Si l'utilisatrice veut trois rejeux pour les dix lots, `P3` s'applique pareil, l'horloge comptant comme dixieme lot dans l'ordre de `P2`. **Cette lecture est a consigner par `camille-po`** (reponse, section 4) ; elle n'est pas tranchee ici. [CORRIGE le 2026-10-26, meme amendement : l'utilisatrice a repondu, les trois rejeux valent pour les dix lots, horloge comprise ; texte d'origine conserve]

### 2. Engage du palier 9 : 13,50

L'utilisatrice a fixe le 2026-10-26 l'engage du palier 9 a **13,50** (marge de 1,5, neuf lots plus l'horloge `MAT-CE1-11`). Ce chiffre remplace, pour la lecture de ce fichier, le 12,50 de l'engage precedent (`DEC-0120` A.264). Le calcul de `nadia-prof` (reponse, 2.1 : 11,00 de l'instance neuve plus 2,50 de l'horloge) et le plafond restent a `camille-po` et a l'utilisatrice (A.244) ; **je ne les recalcule pas**. La consignation est `DEC-0120` A.265, ecrite par `camille-po`.

### 3. Cout reel de P0, a lire dans le premier jet de `-01`

Source : reponse de `nadia-prof`, section 3 (lignes 139 a 142).

1. Dans `lecture-nadia-premier-jet-<paquet>.md` de `-01`, `nadia-prof` ecrit le cout reel de `P0` (lecture et correction), au bareme et a la consommation : premiere mesure de l'instance neuve.
2. **Seuil : 1,67 pour `-01`** (bareme de 1,0 plus 0,67). S'il est depasse, elle l'ecrit **avant que `P1` commence**, pour que la projection contre le plafond soit refaite avant les huit autres lots.
3. Ce n'est pas une condition de Done : un lot plus cher n'est pas refuse pour ce motif.
4. Mon role : a la lecture de ce fichier, je verifie que le chiffre y est avant d'inscrire la premiere paire de `P1` dans `planning.md`. Si le seuil est depasse, je route le recalcul de la projection a `camille-po` sans le faire.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** `DEC-0120` A.265 est ecrite par `camille-po` dans sa branche ; elle n'est pas close par cet amendement. Restent ouverts : la lecture des rejeux de l'horloge (section 1, point 3) et [CORRIGE le 2026-10-26, meme amendement : la lecture des rejeux de l'horloge n'est plus ouverte ; texte d'origine conserve] ; reste ouverte : l'exemption de garde d'`AIM-CE1-2` de `theo-archi`.

## Amendement du 2026-10-26 (UTC+02:00), palier des neuf lots de questionner le monde CE1 : plafond de PAR-8 a 20,20 et portee des rejeux

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit `s57-p5-sofia-plafond`. Il inscrit deux reponses de l'utilisatrice du 2026-10-26 et corrige, par marqueur et sans effacer le texte d'origine, les passages de ce fichier qu'elles rendent inexacts. `camille-po` les consigne en `DEC-0120` A.266 dans ses fichiers, que cet amendement ne touche pas. **Il n'ouvre aucun palier, n'engage aucun lot, ne calcule aucun prix.**

### 1. Plafond de `PAR-8` : 20,20

L'utilisatrice a repondu que le plafond de `PAR-8` est **20,20**, au lieu de 19,20. Passage corrige : l'amendement du `s57-p5-sofia-planning` (section d'en-tete, `planning.md:2411`), qui portait *"relevement de `PAR-8` a 19,20 en plafond"*. Le `grep -n "19,20" planning.md` ne trouvait que cette ligne avant cet amendement ; elle est marquee. L'amendement `s57-p5-sofia-ordre` (`planning.md:2458` et suivantes) ne porte aucun plafond chiffre. [corrige par A.273 : plafond de `PAR-8` du palier 9 releve a 22,20 le 2026-10-26 ; texte d'origine conserve]

### 2. Portee des rejeux : les dix lots, horloge comprise

L'utilisatrice a repondu que les trois rejeux de la marge valent pour **les dix lots, horloge comprise**. C'est la seconde lecture que `nadia-prof` avait prevue (`reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md:105-108`) : `P3` reformulee s'applique pareil, l'horloge comptant comme dixieme lot dans l'ordre de `P2`. Passages corriges par marqueur : le point 3 de la section 1 de l'amendement `s57-p5-sofia-ordre` (`planning.md:2472`) et la section "Decisions prises" du meme amendement (`planning.md:2489`). Aucune regle n'est ajoutee : l'ordre (neuf premiers audits, horloge, rejeux), un rejeu au plus par lot et aucun rejeu avant le premier audit des dix lots sont inchanges.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** `DEC-0120` A.266 est ecrite par `camille-po` dans sa branche ; elle n'est pas close par cet amendement. Reste ouverte : l'exemption de garde d'`AIM-CE1-2` de `theo-archi`.

## Amendement du 2026-10-26 10:43 (UTC+01:00), palier des neuf lots de questionner le monde CE1 : declencheur de P0 pour -01, plafond de PAR-8 maintenu, reponse de nadia-prof sur le rejeu de l'horloge

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit `s57-p5-sofia-p0`. Il execute le point 2 de la section 2 de l'amendement `s57-p5-sofia-planning` (*"j'ecris la ligne, dans un commit de `planning.md` qui precede son `verify` complet"*) et remplit le tableau du point 7. **Il n'ouvre aucun palier, n'engage aucun lot, ne calcule aucun prix.** `camille-po` ecrit `DEC-0120` A.267 en parallele, dans ses fichiers : cet amendement ne touche pas `DEC-0120`.

### 1. Declencheur de `P0` pour `S57-BR-QLM-CE1-01`

Ligne lue sur le disque avant ecriture, par `git log -1 --format='%H %cI' 7c922786` :

> `7c9227869e0c403843428cfe26a166c61a7e2808 2026-10-26T10:22:24+01:00`

| Lot | Commit du premier jet | Heure du commit | Developpeur | Paquet |
|---|---|---|---|---|
| `S57-BR-QLM-CE1-01` | `7c9227869e0c403843428cfe26a166c61a7e2808` (message `[MARC] s57 palier 5: premier jet de S57-BR-QLM-CE1-01 ...`) | 2026-10-26 10:22:24 +01:00 | `marc-dev` | `ce1-qlm-etapes-de-developpement`, 15 items (message du commit) |

Le tableau du point 7 de la section 2 de l'amendement `s57-p5-sofia-planning` (`planning.md:2440` a `2442`) porte les colonnes *heure d'inscription* et *lecture de `nadia-prof`*. Je ne le reecris pas : l'heure d'inscription de cette ligne est celle de l'en-tete de cet amendement, **2026-10-26 10:43 (UTC+01:00)**. La lecture de `nadia-prof` n'existe pas encore : `lecture-nadia-premier-jet-<paquet>.md` n'est pas sur le disque a ce jour. **[corrige le 2026-10-26 13:35 (UTC+01:00), amendement "premiere paire de P1" en fin de fichier : la lecture existe, `lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md`, commit `02add592` ; texte d'origine conserve]**

A lire avec la ligne :

1. **Ce commit est deja fusionne dans `round-two`** (fusion `77fc8f8e`, `[SOF] s57 palier 5: fusion de s57-p5-marc-01`). La ligne est donc ecrite apres la fusion et avant tout `verify` complet, ce que le point 1 de la section 2 exige (*"aucun `verify` complet de ce paquet n'est lance"* tant que la ligne n'est pas ecrite).
2. **Aucun `verify` complet n'a ete lance.** Le rapport de `marc-dev`, tel qu'il m'est relaye, dit n'avoir lance que des mesures partielles avec `-am`. Je ne l'ai pas relu sur le disque (aucun `rapport-marc-dev-*` du palier 5 n'est dans le dossier du sprint) et je n'en cite aucun chiffre.
3. **Le commit contient**, d'apres son message : le paquet, l'entree de rang avec ses totaux (15 items, 45 propositions), aucune entree de catalogue (`QC3`), le test des conditions transverses, `PaquetLu` et les jeux fautifs.
4. **Suite, d'apres le point 4 de la section 2 :** (c) `nadia-prof` ecrit sa lecture, y compris le cout reel de `P0` (section 3 de l'amendement `s57-p5-sofia-ordre`, seuil 1,67) ; (d) `marc-dev` corrige ; (e) seulement ensuite les huit autres premiers jets.

### 2. Plafond de `PAR-8` maintenu a 20,20

L'utilisatrice a maintenu le plafond de `PAR-8` a **20,20** le 2026-10-26. La consignation est `DEC-0120` A.267, ecrite par `camille-po` ; elle n'est pas close par cet amendement. L'amendement `s57-p5-sofia-plafond` (20,20) est inchange. [corrige par A.273 : plafond de `PAR-8` du palier 9 releve a 22,20 le 2026-10-26 ; texte d'origine conserve]

### 3. Reponse de `nadia-prof` du 2026-10-26

Fichier : `reponse-nadia-palier-5-rejeu-horloge-et-double-compte.md`, deja dans `round-two`. Chiffres cites, non recalcules :

1. **Rejeu de l'horloge (1.2) :** il consomme d'abord la marge de 0,5 de `B`, comprise dans les 2,50 de l'horloge ; il ne prend sur la marge de 1,5 que si celle de `B` est deja consommee ; dans les deux cas il compte parmi les trois rejeux des dix lots.
2. **Surcout par lot (2.1) :** **0,42**, mesure sur `registre-prix-palier-4.txt` (2,50 au-dela du bareme sur six lots) ; les 0,67 comptent deux fois la marge consommee ; rien ne mesure une instance neuve ni une marge de 1,5 (2.3).
3. **Routage a `sofia-sm` (section 4) :** *"la ligne `B` du palier 9 porte la marge de l'horloge ; la ligne de l'instance neuve porte les trois rejeux, sauf celui de l'horloge"*. **Elle n'est pas inscrite ici** : aucun palier n'est ouvert et ce fichier ne porte aucune ligne de registre du palier 9. **[corrige le 2026-10-26 13:35 (UTC+01:00), amendement "premiere paire de P1" en fin de fichier : la ligne de registre de l'instance neuve est inscrite (section 1) ; la ligne `B` ne l'est pas ; texte d'origine conserve]**

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** `DEC-0120` A.267 est ecrite par `camille-po` dans sa branche ; elle n'est pas close ici. Restent ouverts : la lecture de `nadia-prof` sur le premier jet de `-01`, la consignation par `camille-po` de la reponse du point 3, la ligne de registre du point 3.3 et l'exemption de garde d'`AIM-CE1-2` de `theo-archi`. **[corrige le 2026-10-26 13:35 (UTC+01:00), amendement "premiere paire de P1" en fin de fichier : la lecture de `nadia-prof` sur le premier jet de `-01` est livree et `P0` est close (section 2), la ligne de registre de l'instance neuve est inscrite (section 1) ; reste ouverts : la consignation par `camille-po` de la reponse du point 3, la ligne `B`, l'exemption de garde d'`AIM-CE1-2` de `theo-archi` ; texte d'origine conserve]**

## Amendement du 2026-10-26 13:35 (UTC+01:00), palier des neuf lots de questionner le monde CE1 : ligne de registre de l'instance neuve, cloture de P0 pour -01, premiere paire de P1 (-02, -03)

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit `s57-p5-sofia-p1`. Il execute les lignes que `DEC-0120` A.270 (point 7, ligne `sofia-sm`) et `relecture-nadia-trois-indices-ce1-qlm-etapes-de-developpement.md` (section 6 et amendement A.270, tableau final) lui donnent, et ouvre la premiere paire de `P1`. Lignee verifiee avant ecriture : `bash outils/verifier-lignee.sh` sort 0 dans le worktree de la branche ; le sommet de `round-two` lu par `git rev-parse` est `8b5a719c7256b6c95b8943e92f242cb3b941479b`. **Il n'ouvre aucun palier, n'engage aucun lot de plus, ne calcule aucun prix et ne prononce aucun Done** : le Done pedagogique est a `nadia-prof` seule. Il ne modifie ni `DEC-0120`, ni le backlog, ni le code. Les chiffres ci-dessous sont ceux de `DEC-0120` A.265 a A.270 et de `nadia-prof`, cites, non recalcules.

### 1. Ligne de registre de l'instance neuve

Aucune ligne de registre du palier 9 n'existait dans ce fichier avant cet amendement (l'amendement du 2026-10-26 10:43, section 3, point 3, le disait). Elle est inscrite ici, sources : `DEC-0120` A.270 points 1, 2, 3 et 7 ; relecture de `nadia-prof`, amendement A.270, point 1 (*confirme*).

| Ligne | Imputation de la seconde lecture de `-01` | Marge consommee par cette lecture | Ligne hors marge (A.247) | Marge disponible aux rejeux |
|---|---|---|---|---|
| instance neuve (`marc-dev`, les dix lots `S57-BR-QLM-CE1`) | voie (a) d'A.270 : hors marge | **0,00** | **0,50** (seconde lecture de `-01`) [corrige le 2026-10-26 16:09, amendement "registre apres la relecture des corrections de -03" en fin de fichier : 1,00 apres A.272, soit 0,50 de `-01` plus 0,50 de `-03` ; texte d'origine conserve] | **1,5** : trois rejeux a 0,50 (A.199) |

- **Cout de `P0` lu : 1,5**, soit 1,0 (lot, lecture du premier jet et correction) plus 0,5 (seconde lecture, hors marge) plus 0,0 (arbitrage des trois indices, A.268). Seuil de 1,67 non franchi : **0,17 sous le seuil**. Lu dans `relecture-nadia-trois-indices-ce1-qlm-etapes-de-developpement.md`, section 3 (*"1,5, seuil de 1,67 non franchi"*) et amendement A.270, point 5 (*"inchanges"*). Le chiffre est ecrit avant que `P1` commence, comme l'amendement `s57-p5-sofia-ordre`, section 3, point 2, l'exige : la projection contre le plafond n'est pas a refaire.
- Engage 13,50 et plafond de `PAR-8` 20,20 inchanges (A.270 point 1). Cas haut mesure : 17,70 plus 0,50 = 18,20, soit 2,00 sous 20,20 (A.270 point 3, cite). [corrige le 2026-10-26 16:09, meme amendement : 18,70, soit 1,50 sous 20,20, apres A.272 ; texte d'origine conserve]
- **Portee** (A.270 point 4, confirmee par `nadia-prof`, point 2) : l'imputation hors marge vaut pour toute seconde lecture avant `verify` d'un lot de l'instance neuve, dix lots. Chaque seconde lecture d'un autre lot s'inscrira sur la ligne hors marge, par amendement date.
- **Ce que la ligne ne porte pas** : la ligne `B` du palier 9 (marge de l'horloge `MAT-CE1-11`, routage de `nadia-prof`, reponse `reponse-nadia-palier-5-rejeu-horloge-et-double-compte.md` section 4) n'est pas inscrite ici, personne ne me la demande a cet amendement et aucun palier n'est ouvert.

### 2. Cloture de `P0` pour `S57-BR-QLM-CE1-01`

**`P0` est close pour `S57-BR-QLM-CE1-01`, le 2026-10-26 a 13:35 (UTC+01:00).** Commit de reference : `8b5a719c7256b6c95b8943e92f242cb3b941479b` (`[SOF] s57 palier 5: fusion de s57-p5-nadia-c1c3 ...`, 2026-10-26T13:27:54+01:00, sommet de `round-two` lu sur le disque), qui porte la derniere etape.

Chaine de `P0`, chaque etape lue au disque par `git log` et `git merge-base --is-ancestor 02add592 HEAD` (sort 0) :

| Etape (amendement `s57-p5-sofia-planning`, section 2, point 4) | Commit | Heure du commit |
|---|---|---|
| (a) premier jet commite seul | `7c922786` | 2026-10-26T10:22:24+01:00 |
| (b) ligne inscrite dans ce fichier | `203668a9` (amendement de 10:43) | avant tout `verify` complet |
| (c) lecture de `nadia-prof`, `lecture-nadia-premier-jet-ce1-qlm-etapes-de-developpement.md` | `02add592` | 2026-10-26T10:57:54+01:00 |
| (d) correction de `marc-dev` | `47279e05`, puis `5e56ea7e` (trois indices) | 11:19:06 et 13:03:54 |
| seconde lecture des 15 items corriges | `408fec39` | 11:40:52 |
| relecture des trois indices, A.270 confirme | `10bc0f8e`, `9e8542e1`, `e299a288` | 13:21:01, 13:21:31, 13:24:27 |

Les conditions d'A.268 (C1, C2, C3) sont lues *vraies* par `nadia-prof` ; `QC7` est tenue pour `-01` : le commit de l'ecrit de lecture (`02add592`) est ancetre de `HEAD`, et aucun autre paquet `ce1-qlm-*` n'existe au depot (`git ls-files | grep -i "ce1-qlm"` ne trouve que le paquet, son test, son contre-exemple et les ecrits de `-01`). **Cette ligne ne prononce aucun Done de `-01`** : l'audit de `P2` n'a pas eu lieu, `QC3` est inchangee (aucune entree `ce1-qlm-*` au catalogue avant un Done). **Non verifie par moi** : qu'aucun `verify` complet de `-01` n'ait ete lance avant la ligne du 10:43 ; le rapport de `marc-dev` m'a ete relaye, pas lu (voir le point 2 de la section 1 de l'amendement du 10:43).

### 3. Premiere paire de `P1` : `S57-BR-QLM-CE1-02` et `S57-BR-QLM-CE1-03`

Source de l'ordre et de la paire : `DEC-0120` A.260 (`P1`) et l'amendement `s57-p5-sofia-planning`, section 2, point 5 (*"par paires dans l'ordre `-02`, `-03` ; `-04`, `-05` ; `-06`, `-08` ; `-10`, `-12`"*).

| Lot | Paquet propose | Ligne | Developpeur |
|---|---|---|---|
| `S57-BR-QLM-CE1-02` | `ce1-qlm-ce-que-mangent-les-animaux` | `QLM-CE1-02` | `marc-dev` |
| `S57-BR-QLM-CE1-03` | `ce1-qlm-besoins-de-la-plante` | `QLM-CE1-03` | `marc-dev` |

**Cette inscription n'est pas la ligne d'un premier jet.** Aucun commit de premier jet de `-02` ou de `-03` n'existe. Tant que `marc-dev` ne m'a pas donne, pour chacun, le lot, le SHA et l'heure, et que je n'ai pas lu le commit sur le disque et ecrit sa ligne par amendement date, **aucun `verify` complet de ce paquet n'est lance, par aucun agent**. La lecture de `nadia-prof` sur la paire precede les premiers jets de la paire suivante (`-04`, `-05`). `RG4` s'applique : un worktree vivant a la fois, `marc-dev` me dit quand il passe de `-02` a `-03`.

**Conditions qui s'appliquent, par source en vigueur** (identifiants et sources, les textes sont dans le backlog et ne sont pas recopies) :

1. **Conditions de ligne de `-02`**, story `S57-BR-QLM-CE1-02` : `RAL-CE1-1` (les trois categories en bonne reponse, source du regime de chaque animal, `FER-1`, `QMT-CE1-2`, `CYC-1`) et `RAL-CE1-2` (quatre animaux exclus, aucun aliment du commerce). Voisin relu : `cm1-sciences-regimes-alimentaires`.
2. **Conditions de ligne de `-03`**, story `S57-BR-QLM-CE1-03` : `BPL-CE1-1` (eau, lumiere, chaleur en bonne reponse, quatre mots jamais proposes, seconde tache `FER-3`) et `BPL-CE1-2` (aucun item ne dit qu'une graine a besoin de lumiere pour germer). Voisins relus : `cp-qlm-hygiene` et le paquet de `-01`.
3. **Conditions transverses des neuf stories** (annotation du 2026-10-25, cinquieme, du backlog) : `TRV-CE1-1`, `TRV-CE1-2`, `TRV-CE1-5`, `TRV-CE1-6`, `FER-1`, `FER-2`, `FER-3`, `CYC-1`, `NRC-CE1-1`, `QMT-CE1-1`, `QMT-CE1-2`, avec `LON-CE1-1`, `-2`, `-5`.
4. **Criteres communs** : `QC1` a `QC9` de l'annotation, dont `QC3` (aucune entree `ce1-qlm-*` au catalogue avant un Done), `QC4` (test du paquet), `QC6` (une sonde refusee par condition transverse negative), `QC7` (lecture avant `verify`, ordre), `QC8` (Done technique : les quatre jobs de `ci.yml`, `build`, `chemins`, `prefixes`, `outils`, nommes). **`QC10`** (annotation du 2026-10-25, compositions) et `DEC-0120` A.259 : l'entree du test de rang `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` est dans le commit du premier jet, avec les totaux exacts de son motif ; une correction qui ajoute ou retire un item recompte le motif dans le meme commit.
5. **Densite** : `DEC-0120` A.258, plancher de **11** items et plafond de **20** (`QC5` remplace). `FER-2` (*"au moins dix items"*) reste la condition de `nadia-prof`.

**Ordre de fusion serialise** (section 3 de l'amendement `s57-p5-sofia-planning`, `avis-theo-palier-5-neuf-lots-qlm-ce1.md` 3.3 et 3.4) : `-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`. `-01` est fusionne (premier jet `77fc8f8e`, corrections `648db0f9`, `1db73af8`). La branche de `-02` part du sommet de `round-two` qui porte cette fusion ; celle de `-03` part du sommet qui porte la fusion de `-02`. Je verifie la lignee dans le worktree de chaque branche avant de fusionner et j'etablis le total de tests sur l'arbre fusionne. Un seul test des conditions transverses, ecrit dans `-01` : un lot qui le modifie est signale a `theo-archi`, pas fusionne.

**Aucun rejeu avant les dix premiers audits.** `P3`, texte de `nadia-prof` cite dans l'amendement `s57-p5-sofia-ordre` : *"aucun rejeu avant le premier audit de chacun des dix lots du palier"* ; ordre neuf audits (`P2`), horloge `MAT-CE1-11`, puis rejeux ; un rejeu au plus par lot dans le palier 9. Un lot de la paire refuse n'est donc pas rejoue pendant `P1`.

### 4. Points manquants ou contradictoires, signales et non combles

1. **La liste donnee pour la paire n'est pas celle des stories.** Elle porte `DVP-CE1-1` et `DVP-CE1-2`, qui sont les conditions de ligne de `-01` (story `S57-BR-QLM-CE1-01`), et ne porte ni `RAL-CE1-1`, `RAL-CE1-2` (`-02`) ni `BPL-CE1-1`, `BPL-CE1-2` (`-03`). J'ai inscrit les conditions de la section 3 d'apres les stories, pas d'apres cette liste. `DVP-CE1-2` touche `-03` par un seul point : *"aucun item ne dit qu'une graine a besoin de lumiere pour germer"* est aussi `BPL-CE1-2`, qui est inscrite.
2. **`AIM-CE1-2` n'est attachee ni a `-02` ni a `-03`.** Condition de `-06` (aimant). `avis-theo-palier-5-exemption-aim-ce1-2.md`, section 5, place la garde dans `PaquetCe1QlmAimantTest`, dans le commit du premier jet de `-06`. Je ne l'inscris pas comme condition de la paire. Si l'intention etait de l'etendre, c'est une decision de `camille-po`, a ecrire.
3. **La garde de rotation ne s'applique a aucun item de la paire.** `avis-theo-palier-5-garde-rotation-ce1-qlm-01.md` : lieu = *le test du paquet de `-01`, pas le test transverse* (`V3`), et *"seule `-01` porte une tache ordre sur un cycle"*, les autres stories ne demandent aucun ordre. La condition *"sur tout item ordre"* est donc vide pour `-02` et `-03` aujourd'hui. Si un item *ordre* apparait dans un de ces paquets (la seconde tache de `-03` est *"ce qui arrive a une plante privee de l'un des trois"*, pas un ordre), la garde n'y existe pas et je route a `theo-archi` sans la combler.
4. **Conditions en vigueur absentes de la liste donnee** : `QC1`, `QC2`, `QC5`, `QC7`, `QC8`, `TRV-CE1-6`, `CYC-1`, `FER-2`, `LON-CE1-1/-2/-5`. Elles sont inscrites a la section 3.
5. **`-02` ne nomme pas sa seconde tache.** `FER-3` (*"au moins deux taches distinctes, la moins servie portant au moins trois items"*) est transverse, et `QC5` demande que le commentaire du paquet nomme ses taches. La story `-02` ne donne que `RAL-CE1-1` et `RAL-CE1-2` : aucune seconde tache, aucune sonde `FER-3`. La story `-03` la nomme (*privee de l'un des trois*, critere 4, au moins 3 items). **Je ne la deduis pas** : a `camille-po` (story) et a `nadia-prof` (condition), avant le premier jet de `-02`, ou a lire par `nadia-prof` a sa lecture avant `verify`.

### 5. Question laissee ouverte par `nadia-prof` (Q3 de sa relecture)

*Horloge rejouee sur la marge de `B`, puis trois lots de l'instance neuve refuses : un quatrieme rejeu sur la marge de 1,5 restee entiere, ou non.* **Ouverte, non tranchee par moi.** Elle est a `camille-po` (relecture de `nadia-prof`, amendement A.270, tableau final), puis a l'utilisatrice si `camille-po` la lui porte. Elle ne mord qu'apres les dix premiers audits et seulement si l'horloge et trois lots hors horloge sont refuses : aucun rejeu n'a lieu avant (`P3`), donc ni `P1`, ni `P2` n'en dependent. Je la remets a la fermeture des dix premiers audits, avec le verdict de l'horloge, et je route a `camille-po` a ce moment si le cas est reel. Elle n'est close que par une `DEC` presente sur le disque.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : la ligne de registre de l'instance neuve (execution d'A.270), la cloture de `P0` pour `-01`, la premiere paire de `P1` et les cinq points de la section 4. Restent ouverts : la consignation par `camille-po` de la reponse de `nadia-prof` sur le rejeu de l'horloge (amendement du 10:43, section 3), la ligne `B`, l'exemption de garde d'`AIM-CE1-2` (point 2 de la section 4 et avis de `theo-archi`), la seconde tache de `-02` (point 5 de la section 4) et la question Q3 de la section 5. Aucun Done n'est prononce.

## Amendement du 2026-10-26 16:09 (UTC+01:00), palier des neuf lots de questionner le monde CE1 : registre apres la relecture des corrections de -03 (A.272), etat des lots, projection contre le plafond, constat de l'intrus de -02

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit de la branche `s57-p5-sofia-p2` (base `round-two` a `1f6af11c`, lu par `git rev-parse`). Il execute la ligne `sofia-sm` d'A.272 (*"inscrire 0,50 sur la ligne hors marge pour `-03`"*) et route le constat de `nadia-prof` sur `-02`. **Il ne modifie ni `DEC-0120`, ni le backlog, ni le code, ne prononce aucun Done et ne tranche aucun plafond.** Les chiffres sont ceux d'A.270 a A.272, cites ; les calculs sont marques **[calcul]**. Deux lignes de l'amendement du 13:35 (section 1, ligne de registre et cas haut) portent un marqueur *[corrige ...]*, texte d'origine conserve.

### 1. Ce que le disque porte (`git log round-two`, `git merge-base --is-ancestor`)

| Lot | Etape | Commit | Fusion dans `round-two` |
|---|---|---|---|
| `-01` | seconde lecture des 15 items corriges | `408fec39` | fusionne (amendement du 13:35) |
| `-03` | premier jet de `marc-dev` | `7fe939a6` | `ccfd1a0c` |
| `-03` | lecture de `nadia-prof` | `30ba34de` | `81d203b1` |
| `-03` | corrections de `marc-dev` | `37f49795` | `b1fa3e1f` |
| `-03` | relecture des corrections par `nadia-prof` : *"les dix points tiennent, aucune ouverture"* | `dddd54b3` | `527ca288` |
| `-03` | A.272 de `camille-po` | `77f75b29` | `1f6af11c` |
| `-02` | premier jet de `marc-dev` | `ebc274b1` | `7c2b448c` |
| `-02` | lecture de `nadia-prof` | `388cb638` | `af6937a2` |
| tous | A.271 de `camille-po` | lue dans `DEC-0120` | `07524054` |

- **`-02`, corrections.** La branche `s57-p5-marc-02-corr` pointe sur `af6937a2`, le sommet de `round-two` a la fusion de la lecture : aucun commit de correction n'existe sur le disque. *"Corrections en cours"* est le constat de l'agent lanceur, non lu par moi.
- **A.273.** La branche `s57-p5-camille-273` pointe sur `1f6af11c`, sommet de `round-two`, sans commit propre, et `DEC-0120` ne contient aucun `A.273`. **A.273 n'est pas fusionnee, elle n'est pas citee ici comme fusionnee.**

### 2. Ligne de registre de l'instance neuve, mise a jour

Sources : `DEC-0120` A.270 points 1 a 4 et 7, A.272 (cout et regle) ; `relecture-nadia-corrections-ce1-qlm-besoins-de-la-plante.md`, sections 2 et 4 ; `lecture-nadia-premier-jet-ce1-qlm-besoins-de-la-plante.md`, section 7.

| Ligne | Marge consommee | Ligne hors marge (A.247) | Marge disponible aux rejeux |
|---|---|---|---|
| instance neuve (`marc-dev`, les dix lots `S57-BR-QLM-CE1`) | **0,00** | **1,00** : `-01` 0,50 (`408fec39`) plus `-03` 0,50 (`dddd54b3`, A.272) [corrige : l'amendement du 13:35 portait 0,50 ; texte d'origine conserve] | **1,5** : trois rejeux a 0,50 |

- **Engage 13,50, marge 1,5 : inchanges.** Plafond de `PAR-8` **en vigueur dans `round-two` : 20,20** (A.270, A.272). [corrige par A.273 : plafond de `PAR-8` du palier 9 releve a 22,20 le 2026-10-26 ; texte d'origine conserve]
- **Cout de `-03` : 1,5** [calcul : 1,0 plus 0,5, A.272]. `nadia-prof` ecrit (relecture, section 4) : *"Aucune"* ouverture, *"pas de deuxieme tour d'arbitrage a classer"*. Le cas a 2,0 d'A.272 (correction rejetee ou verdict rouvert) n'est pas celui qui est ecrit.
- **Cas haut selon A.272 : 18,70** [calcul : 17,70 plus 0,50 de `-01` plus 0,50 de `-03`], soit **1,50 sous 20,20** [calcul : 20,20 moins 18,70] [corrige : l'amendement du 13:35 portait 18,20 ; texte d'origine conserve]. [corrige par A.273 : plafond de `PAR-8` du palier 9 releve a 22,20 le 2026-10-26 ; texte d'origine conserve]
- **Secondes lectures encore possibles avant d'atteindre 20,20 : trois** [calcul : 1,50 divise par 0,50]. La quatrieme depasse 20,20 et va au registre (A.247), sans etre une condition de Done (A.270 point 3, dernier point ; A.272 ; A.268). [corrige par A.273 : plafond de `PAR-8` du palier 9 releve a 22,20 le 2026-10-26 ; texte d'origine conserve]
- Depassement de l'engage au registre, borne a **5,20** [calcul : 4,20 plus 0,50 plus 0,50], cite d'A.272.
- **`-02`, faits seuls.** Premier jet fusionne (`ebc274b1`), lecture faite (`388cb638`), corrections en cours (section 1). **Rien n'est impute a `-02` sur la ligne hors marge** : l'ecrit de `nadia-prof` conditionne la seconde lecture a l'application de ses corrections (*"si `marc-dev` les applique telles qu'ecrites"*, section 8), elle n'existe pas encore. Je l'inscris a la lecture du fichier de relecture des corrections, par amendement date. [corrige le 2026-10-27 08:19, amendement du 2026-10-27 08:19 en fin de fichier : la relecture des corrections de `-02` existe (`e0d2ba08`), 0,50 est impute hors marge, la ligne passe a 1,50 ; texte d'origine conserve]
- **Regle d'A.272 pour `-02` et `-04` a `-12`** : toute relecture par `nadia-prof`, apres correction, des lignes qu'elle a prescrites vaut 0,5 hors marge. Un arbitrage apres seconde lecture est classe par `camille-po` lot par lot ; une troisieme lecture coute 0,5 de plus et est reclassee par elle.
- **Q3 d'A.271** (quatrieme rejeu) : differee, a rouvrir a la fermeture des dix premiers audits, proprietaire `camille-po` via `sofia-sm`. Je la remets a ce moment.

### 3. Etat des lots

| Lot | Etat, faits lus au disque |
|---|---|
| `S57-BR-QLM-CE1-01` | premier jet, lecture, corrections, seconde lecture et relecture des trois indices faits ; `P0` close (amendement du 13:35) ; audit `P2` non fait |
| `S57-BR-QLM-CE1-03` | premier jet, lecture, corrections et relecture des corrections faits ; tout fusionne ; audit `P2` non fait |
| `S57-BR-QLM-CE1-02` | premier jet et lecture faits et fusionnes ; corrections par `marc-dev` en cours (aucun commit sur le disque) ; relecture des corrections non faite |
| `-04`, `-05`, `-06`, `-08`, `-10`, `-12` | pas commences : `git ls-files` ne trouve que trois paquets `ce1-qlm-*` (`-01`, `-02`, `-03`) |

`QC3` inchange : aucune entree `ce1-qlm-*` au catalogue avant un Done.

### 4. Ordre de fusion serialise, rappel

Source : amendement `s57-p5-sofia-planning`, section 3, repris par `DEC-0120` A.260. **Ordre : `-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`**, une fusion a la fois, chaque branche partant du sommet de `round-two` qui porte la fusion precedente. Lignee verifiee par `bash outils/verifier-lignee.sh` dans le worktree de chaque branche avant la fusion ; total de tests etabli par moi sur l'arbre fusionne ; jobs de `ci.yml` nommes avant toute declaration de porte technique.

**Ecart constate, non comble.** La fusion du premier jet de `-03` (`ccfd1a0c`, 14:30) precede celle du premier jet de `-02` (`7c2b448c`, 15:22) : l'ordre ecrit ci-dessus n'a pas ete suivi pour ces deux lots. Je ne declare pas ce qu'il aurait fallu faire ; la regle de l'ordre est de `theo-archi` (3.3 de son avis) : voir section 7, point 2.

### 5. Projection du cout total du palier, si chaque lot restant a une seconde lecture

**C'est une projection, pas une mesure.** Hypothese : chacun des sept lots restants (`-02`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`) a une seconde lecture a 0,50 hors marge (A.272, regle pour les lots suivants), et aucun n'a de troisieme lecture ni de rejeu hors marge.

| Etape | Calcul [calcul] | Resultat |
|---|---|---|
| Cas haut selon A.272 (`-01` et `-03` compris) | 17,70 plus 0,50 plus 0,50 | 18,70 |
| Sept secondes lectures restantes | 7 fois 0,50 | 3,50 |
| **Cout total projete** | 18,70 plus 3,50 | **22,20** |

| Plafond de `PAR-8` | Ecart a la projection [calcul] | Lecture |
|---|---|---|
| **20,20**, en vigueur dans `round-two` | 22,20 moins 20,20 = **2,00 au-dessus** | quatre secondes lectures sur sept depassent le plafond (2,00 divise par 0,50) ; la regle d'A.270 point 3 s'applique : *"depasse le plafond et va au registre (A.247), sans etre une condition de Done"* (A.268) |
| **22,20**, decision de l'utilisatrice du 2026-10-26 relayee par l'agent lanceur, **a inscrire par `camille-po` apres la fusion d'A.273** | 22,20 moins 22,20 = **0,00** | plafond atteint, non depasse ; reserve nulle : toute lecture supplementaire (troisieme lecture, 0,5) le depasse |

- Depassement de l'engage au registre dans cette projection : 22,20 moins 13,50 = **8,70** [calcul].
- **La projection ne tient pas compte** : d'une troisieme lecture d'un lot, d'un rejeu hors marge, du cout de la relecture redactionnelle (hors de tout calcul, A.268 point 2), ni de la consommation en tokens (non mesuree).
- **Je ne tranche pas entre les deux plafonds.** Le plafond est une decision de l'utilisatrice, inscrite par `camille-po`. Tant qu'A.273 n'est pas sur le disque, le plafond en vigueur de ce fichier est 20,20. Rien n'est change ici a l'engage, au plafond ou a la marge.

### 6. Point ouvert : dans la tache B de `-02`, la bonne reponse est l'intrus des trois propositions

Source : `lecture-nadia-premier-jet-ce1-qlm-ce-que-mangent-les-animaux.md`, section 4 (constat date du 2026-10-26) et section 10 (routage). Faits ecrits par `nadia-prof` : dans chaque item de la tache B, la bonne reponse est l'intrus des trois propositions ; sa regle produit ce resultat ; elle ne refuse aucun item et **ne durcit pas sa regle** ; elle le regardera a l'audit (`P2`). **Routage : `camille-po`**, par `sofia-sm`, pour qu'elle sache ce que l'audit regardera. Je ne propose aucune solution. **Point ouvert** : il n'est clos que par un ecrit de `camille-po` present sur le disque ; `DEC-0120` ne contient aucune occurrence de ce constat a la date de cet amendement (`grep -c` : 0).

### 7. Points manquants ou contradictoires, signales et non combles

1. **A.273 absente du disque** (section 1). Le plafond de 22,20 n'est pas inscrit ; la projection donne les deux valeurs. La regle d'autocontrole des premiers jets, demandee par l'utilisatrice, n'est pas a `sofia-sm` pour l'instant : `camille-po` en route le texte, je ne l'ecris pas.
2. **Ordre de fusion de `-02` et `-03` inverse** (section 4). A `theo-archi` : l'ordre ecrit a-t-il une consequence sur les fichiers en fin de liste (table du rang, `catalogue.yaml`) pour les fusions deja faites ? Je ne le tranche pas.
3. **A.272 a ete ecrite avant la relecture de `nadia-prof`** (elle le dit) : ses conditions C1 et C3 pour `-03` se lisent maintenant dans `relecture-nadia-corrections-ce1-qlm-besoins-de-la-plante.md`, sections 1 et 2 : lecture sur les lignes seules, `git diff --stat 7fe939a6 HEAD` a 19 insertions et 16 suppressions, toutes sur les lignes prescrites. Je le consigne ; le classement reste a `camille-po`.
4. **Le cout de `-02` n'est pas fixe** : 1,0 au bareme a l'ecriture, 1,5 si une seconde lecture a lieu (`nadia-prof`, section 8). Rien n'est inscrit tant que la relecture n'existe pas.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il ecrit : la ligne hors marge a 1,00 (execution d'A.272), l'etat des lots, le rappel de l'ordre de fusion et son ecart, la projection a 22,20 contre les deux plafonds, le constat de l'intrus de `-02` route a `camille-po`. Restent ouverts : l'inscription du plafond decide par l'utilisatrice (A.273, `camille-po`), le constat de l'intrus (`camille-po`), l'ecart d'ordre de fusion (`theo-archi`), Q3 d'A.271 (a la fermeture des dix premiers audits). Aucun Done n'est prononce.

## Amendement du 2026-10-26 16:25 (UTC+01:00), palier des neuf lots de questionner le monde CE1 : plafond de PAR-8 a 22,20 (A.273), autocontrole du premier jet, Q-273-1, etat des lots

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit de la branche `s57-p5-sofia-p3` (base `round-two` a `1706a40b`, lu par `git rev-parse`). Il execute les lignes `sofia-sm` du routage d'A.273 (`DEC-0120`, `camille-po`, fusionnee dans `1706a40b`), que je ne modifie pas. **Il n'ouvre aucun palier, n'engage aucun lot, ne calcule aucun prix, ne prononce aucun Done.** Les chiffres sont ceux d'A.273 et d'A.272, cites ; les sommes sont des calculs, marques **[calcul]**. Aucune mesure n'a ete lancee.

### 1. Plafond de PAR-8 du palier 9 : 22,20

Source : `DEC-0120` A.273, point 1 (decision de l'utilisatrice du 2026-10-26, options O2 et O3 retenues : *"on releve a 22,20 et on demande aussi que les premiers jets soient plus propres"*).

| Grandeur | Avant | Apres |
|---|---|---|
| plafond de `PAR-8` du palier 9 | 20,20 | **22,20** |
| engage du palier 9 | 13,50 | **13,50**, inchange |
| marge de l'instance neuve | 1,5 | **1,5**, inchangee |
| cas haut apres `-03` (A.272) | 18,70, soit 1,50 sous 20,20 | 18,70, soit **3,50 sous 22,20** [calcul : 22,20 moins 18,70] |
| projection, sept secondes lectures de plus (`-02`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`) | 22,20, soit 2,00 au-dessus de 20,20 | 22,20, soit **0,00 de reserve** [calcul : 18,70 plus 7 fois 0,50 = 22,20] |

- **Depassement au registre.** La regle d'A.270 point 3 et d'A.272 ne change pas de nature, elle change de seuil : un cout qui porte le total au-dela de **22,20** va au registre (A.247), sans etre une condition de Done (A.268). `nadia-prof` reste seule a prononcer le Done.
- **La projection est une projection, pas une mesure** (A.273 point 1 (a)). Elle suppose une seconde lecture a 0,50 hors marge sur chacun des sept lots et aucune troisieme lecture. Elle ne compte aucune seconde lecture a l'horloge `MAT-CE1-11` : voir la question `Q-273-1`, section 3. [corrige le 2026-10-27 08:19, amendement du 2026-10-27 08:19 en fin de fichier : les arbitrages de `nadia-prof` avant premier jet valent 0,00, non chiffre, dans le 1,0 du lot (A.276), la projection reste 22,20 ; texte d'origine conserve]
- **Passages de ce fichier marques `[corrige par A.273]`**, texte d'origine conserve, six : `planning.md:2411` (marqueur du plafond a 20,20, amendement `s57-p5-sofia-planning`), section 1 puis section 2 de l'amendement "plafond de PAR-8 a 20,20" (`planning.md:2497` et `planning.md:2532`), et, dans l'amendement du 16:09, la ligne du plafond en vigueur (`planning.md:2648`), la ligne du cas haut (`planning.md:2650`) et la ligne des secondes lectures avant d'atteindre 20,20 (`planning.md:2651`). A.273 en liste deux (le plafond, sections 1 et 2, et la ligne du cas haut du 16:09) ; j'ai cherche "20,20" dans tout le fichier et marque en plus trois passages qui le donnent comme plafond **en vigueur** (`2411`, `2648`, `2651`).
- **Passages non marques, a dessein** : les titres des amendements et de leurs sections 1 et 2 (`2491`, `2495`, `2530`), qui nomment la decision du moment ; la puce du cas haut de l'amendement A.270 (`planning.md:2559`), qui cite A.270 point 3 tel qu'il etait ecrit et porte deja son marqueur du 16:09 ; la ligne du tableau de projection "20,20, en vigueur dans round-two" et la phrase *"Tant qu'A.273 n'est pas sur le disque, le plafond en vigueur de ce fichier est 20,20"* (`planning.md:2686`, `planning.md:2691`), qui decrivent l'etat du disque a 16:09 sous condition d'A.273. Le plafond **en vigueur** est 22,20.

### 2. Autocontrole du premier jet par marc-dev : une etape, pas une porte

Source : `DEC-0120` A.273, point 2 (texte du tableau reporte tel quel). Elle vaut pour le premier jet de **`-04`, `-05`, `-06`, `-08`, `-10`, `-12`** et tout lot dont le premier jet n'est pas encore commite. `-02` est deja lu et n'est pas concerne.

**Etape ajoutee a la tache de `marc-dev`, avant le commit du premier jet.** Il rend compte dans son rapport de premier jet, en **une ligne par defaut**, de *fait* ou *non fait* et des items sur lesquels il l'a fait.

| Ligne du rapport | Ce que `marc-dev` fait | Ce qu'il ecrit |
|---|---|---|
| **a** indices | relire chaque indice du paquet et noter si son objet est la bonne reponse (periphrase) ou si son verbe la porte par son sens | fait / non fait ; les items relus ; les items dont il doute, a lire par `nadia-prof` |
| **b** distracteurs | relire chaque distracteur et noter si un enfant qui ne sait rien de la competence visee l'ecarterait par la taille, le lieu, ou parce qu'il est impossible pour le sujet | fait / non fait ; les items relus ; les items dont il doute |
| **c** sources | relire la source citee dans le commentaire du paquet et noter si elle renvoie a autre chose que le test du paquet ou qu'un tableau dont l'en-tete demande lui-meme une source | fait / non fait ; si aucune source hors du paquet et de ses tests n'existe, il l'ecrit en toutes lettres plutot que de citer une source circulaire |

**Pas une porte, aucun cout, aucun seuil, aucun chiffre promis, `nadia-prof` seule juge.** Un premier jet dont une ligne est *non fait*, ou dont `marc-dev` signale un defaut, n'est pas refuse pour ce motif. *Fait* ne vaut jamais *juste*. L'etape est comprise dans le 1,0 du lot, n'ajoute aucune ligne de registre et n'alloue aucun identifiant de condition (a, b, c sont des reperes locaux d'A.273). `nadia-prof` lit comme aujourd'hui et peut contredire une ligne du rapport par ecrit, ou ajouter ou retirer un defaut de la liste. `yanis-qa` et `theo-archi` : rien. Le rapport de premier jet de `marc-dev` est le seul endroit ou l'autocontrole s'ecrit.

### 3. Question ouverte Q-273-1, routee a nadia-prof

Source : `DEC-0120` A.273, point 1 (c). **Routee, non tranchee par moi.**

- **(i)** Un rejeu au-dela des trois de la marge de 1,5 compte-t-il contre le plafond de 22,20 du palier 9, ou contre celui d'un palier 10 ? Part chiffre du registre : `nadia-prof`. Si sa reponse est que le plafond couvre aussi le palier 10, c'est a l'utilisatrice de dire si 22,20 vaut pour les deux, par `sofia-sm`.
- **(ii)** La seconde lecture du premier jet de l'horloge `MAT-CE1-11` est-elle 0,50 hors marge, comme celle d'un lot de l'instance neuve ? **Si elle compte, la projection passe de 22,20 a 22,70, soit 0,50 au-dessus de 22,20** [calcul : 22,20 plus 0,50].
- **Rien ne la bloque avant la fermeture des dix premiers audits** (`P3`) : aucun rejeu n'est du. Elle reste ouverte tant qu'un ecrit de `nadia-prof` n'est pas sur le disque ; le fichier de reponse n'existe pas a la date de cet amendement. Q3 d'A.271 (quatrieme rejeu) reste differee a la meme echeance, proprietaire `camille-po` via `sofia-sm`.

### 4. Ecart d'ordre de fusion, connu

L'ecart deja constate dans l'amendement du 16:09 (section 4) est inscrit comme **ecart connu** : le premier jet de `-03` (`ccfd1a0c`) a ete fusionne avant celui de `-02` (`7c2b448c`), alors que l'ordre ecrit est `-01`, `-02`, `-03`. **Je ne rouvre rien.** Le point adresse a `theo-archi` (point 2 de la section 7 de l'amendement du 16:09) reste tel quel. Les fusions depuis cet amendement (`a284c398`, `cbb984e1`, `1706a40b`) sont passees sans conflit, d'apres le compte rendu de l'agent lanceur ; je ne l'ai pas rejoue.

### 5. Etat des lots, lu sur disque (git log de round-two)

| Lot | Etat |
|---|---|
| `S57-BR-QLM-CE1-01` | premier jet, lecture, corrections, seconde lecture et relecture des trois indices faits ; audit `P2` non fait |
| `S57-BR-QLM-CE1-03` | premier jet, lecture, corrections et relecture des corrections faits ; audit `P2` non fait |
| `S57-BR-QLM-CE1-02` | premier jet et lecture faits ; **corrections appliquees par `marc-dev` (`f7c6bc7d`), fusionnees (`cbb984e1`)** ; relecture des corrections par `nadia-prof` **en cours** : aucun fichier de relecture de `-02` n'existe sur le disque [corrige le 2026-10-27 08:19, amendement du 2026-10-27 08:19 en fin de fichier : relecture faite (`e0d2ba08`), fusionnee (`96b11d84`) ; texte d'origine conserve] |
| `-04`, `-05`, `-06`, `-08`, `-10`, `-12` | pas commences : `git ls-files` ne trouve que trois paquets `ce1-qlm-*` |

- **Ligne hors marge de l'instance neuve : inchangee a 1,00** (`-01` 0,50 plus `-03` 0,50). **Rien n'est impute a `-02` avant la relecture.** Si elle a lieu, A.272 la compte 0,5 hors marge, et la ligne passe a 1,50 par amendement date, a la lecture du fichier de relecture. Cout de `-02` : 1,0 au bareme, 1,5 si la relecture a lieu. [corrige le 2026-10-27 08:19, amendement du 2026-10-27 08:19 en fin de fichier : 1,50 apres la relecture des corrections de `-02` (A.272) ; texte d'origine conserve]
- `QC3` inchange : aucune entree `ce1-qlm-*` au catalogue avant un Done. **Aucune ligne de Done n'est inscrite.**

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il inscrit l'execution d'A.273 : plafond a 22,20, regle d'autocontrole, routage de `Q-273-1`, ecart d'ordre de fusion, etat des lots. Restent ouverts : `Q-273-1` (`nadia-prof`, et l'utilisatrice si le plafond couvre un palier 10), Q3 d'A.271 (fermeture des dix premiers audits), le constat de l'intrus de `-02` (`camille-po`), l'ecart d'ordre de fusion (`theo-archi`). Aucun Done n'est prononce.

## Amendement du 2026-10-27 08:19 (UTC+01:00), palier des neuf lots de questionner le monde CE1 : A.276 (arbitrages avant premier jet a 0,00, non chiffre), relecture des corrections de -02 (ligne hors marge a 1,50), remesure de fin de palier, etat des lots

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit de la branche `s57-p5-sofia-p4` (base `round-two` a `62a08896`, lu par `git rev-parse`). Il execute les lignes `sofia-sm` d'A.276 (`DEC-0120`, `camille-po`, decision de l'utilisatrice du 2026-10-27, fusionnee dans `62a08896`), que je ne modifie pas ; A.275 en est le classement de depart, que je cite sans le recopier. **Il n'ouvre aucun palier, n'engage aucun lot, ne fixe aucun prix et ne prononce aucun Done.** Il ne modifie ni `DEC-0120`, ni le backlog, ni le code. Les chiffres sont ceux d'A.270, A.272, A.273 et A.276, cites ; les sommes sont des calculs, marques **[calcul]**. Aucune mesure n'a ete lancee. Les trois branches de contradictions de `nadia-prof` (`s57-p5-nadia-c0405`, `s57-p5-nadia-c0608`, `s57-p5-nadia-c1012`) ne sont pas dans `round-two` : je ne les cite pas comme fusionnees et je n'en lis pas le contenu.

### 1. A.276 inscrit : les arbitrages de nadia-prof avant premier jet, 0,00, non chiffre

Source : `DEC-0120` A.276, points 1 et 3, et sa ligne `sofia-sm` du routage ; reponse de l'utilisatrice du 2026-10-27, citee par A.276 (*"pour Q1, on va sur 0,00 avec Projection inchangee a 22,20"*). Le classement d'A.275 (lacune) est comble par elle, pas par moi.

| Geste de `nadia-prof` | Fichier | Prix au registre | Rattachement |
|---|---|---|---|
| arbitrage avant les premiers jets de `-04` et `-05` | `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md` (`84181353`) | **0.00, non chiffre** | dans le 1,0 de chaque lot, hors ligne hors marge |
| arbitrage avant les premiers jets de `-06` et `-08` | `arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md` (`97fd6d9f`) | **0.00, non chiffre** | idem |
| arbitrage avant les premiers jets de `-10` et `-12` | `arbitrage-nadia-premiers-jets-ce1-qlm-10-12.md` (`a75dbd83`) | **0.00, non chiffre** | idem |
| avis sur la seconde tache de `-02` | `avis-nadia-seconde-tache-ce1-qlm-02.md` | **0.00, non chiffre** (compris dans la reponse, A.276 point 1) | dans le 1,0 de `-02`, hors ligne hors marge |

| Grandeur | Avant | Apres |
|---|---|---|
| projection d'A.273 | 22,20 | **22,20**, inchangee |
| plafond de `PAR-8` du palier 9 | 22,20 | **22,20**, inchange |
| engage du palier 9 | 13,50 | **13,50**, inchange |
| marge de l'instance neuve | 1,5 | **1,5**, inchangee |

- **Aucune ligne n'est ajoutee a la ligne hors marge et aucun montant n'est retire de la marge** pour ces arbitrages (A.276 point 1).
- **L'unite (le fichier ou le lot) n'a plus d'effet** : a 0,00 les quatre lignes du tableau d'A.275 point 4 donnent 22,20 (A.276 point 1). Les hypotheses 0,25 et 0,50 d'A.274 et d'A.275 ne sont pas retenues ; je ne les recopie pas.
- **Les durees sont des faits, pas un prix.** `nadia-prof` ecrit dans ses trois fichiers 25 min 12 s, 28 min 30 s et 25 min 11 s (somme 78 min 53 s selon A.275 point 4). Aucune regle ne convertit une duree en bareme (A.275, citant ses trois fichiers) : je n'en convertis aucune.
- **Passages de ce fichier marques `[corrige ...]`, texte d'origine conserve, quatre** : dans l'amendement du 16:09, la puce *"`-02`, faits seuls"* (section 2) ; dans l'amendement du 16:25, la puce *"La projection est une projection, pas une mesure"* (section 1), la ligne `S57-BR-QLM-CE1-02` du tableau de la section 5 et la puce *"Ligne hors marge de l'instance neuve : inchangee a 1,00"* (section 5). Les passages qui donnent 22,20 comme projection sans reserve ne sont pas autrement touches : la projection ne change pas.

### 2. Ligne hors marge : `-02` passe de non impute a 0,50, soit 1,50 cumule

Source : `relecture-nadia-corrections-ce1-qlm-ce-que-mangent-les-animaux.md` (lue en entier ; section 6 : *"Aucune"* ouverture, aucun item ni aucune formulation rouverts ; section 7 : duree 10 min 08 s, *"Au bareme, 0,5 hors marge"* selon `DEC-0120` A.272 et A.270 point 4) ; `DEC-0120` A.272, regle point 1 (*"La question n'a plus a etre posee par lot"*) et A.276, ligne `sofia-sm` du routage.

**Lu au disque.** `git log -1 e0d2ba08` : *"[NAD] s57 p5: relecture des corrections de ce1-qlm-ce-que-mangent-les-animaux (dix points tiennent, sonde 14 gardee, ..."*, 2026-10-26 16:29:19 (+01:00). `git merge-base --is-ancestor e0d2ba08 HEAD` : sort 0, le commit est dans `round-two` (fusion `96b11d84`). Le SHA donne par le rapport de `nadia-prof` est confirme. Les corrections de `marc-dev` : `f7c6bc7d`, fusionnees (`cbb984e1`).

| Ligne | Marge consommee | Ligne hors marge (A.247) | Marge disponible aux rejeux |
|---|---|---|---|
| instance neuve (`marc-dev`, les dix lots `S57-BR-QLM-CE1`) | **0,00** | **1,50** : `-01` 0,50 (`408fec39`) plus `-03` 0,50 (`dddd54b3`) plus `-02` 0,50 (`e0d2ba08`) [calcul : 0,50 plus 0,50 plus 0,50] | **1,5** : trois rejeux a 0,50 |

- **Cout de `-02` : 1,5** [calcul : 1,0 plus 0,5, A.272]. Le cas a 2,0 d'A.272 (correction rejetee, verdict rouvert, seconde correction) n'est pas celui que `nadia-prof` ecrit : ses dix points tiennent a l'octet, aucune ouverture.
- **Cas haut, recalcule.** Regle : A.270 point 3, reprise par A.272 (section Cout) et par A.273 point 1 (a) (*"Elle garde la methode d'A.270 point 3 : je ne retranche pas ce que chaque lot compte deja dans les 0,42"*). Chaque seconde lecture s'ajoute **en entier** au cas haut mesure de 17,70 (A.267) ; A.276 n'y ajoute aucun arbitrage (0,00). Calcul : 17,70 plus 0,50 (`-01`) plus 0,50 (`-03`) plus 0,50 (`-02`) = **19,20** [calcul], soit 18,70 plus 0,50. Ecart au plafond : 22,20 moins 19,20 = **3,00 sous 22,20** [calcul]. [corrige le 2026-10-27 15:40, amendement du 2026-10-27 15:40 en fin de fichier : les neuf secondes lectures existent et A.278 (fusionne, c93199d0) les classe : 17,70 plus 9 fois 0,50 = 22,20, sans reserve ; texte d'origine conserve]
- **Secondes lectures encore possibles : six** [calcul : 3,00 divise par 0,50], soit les six lots sans seconde lecture, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`. Verification : 19,20 plus 6 fois 0,50 = 22,20, soit la projection d'A.273 (qui comptait sept lots a partir de 18,70) : les deux chiffres sont coherents. **Six atteignent 22,20 sans le depasser ; la septieme, une troisieme lecture d'un lot (0,50, A.272 regle point 3) ou la seconde lecture de l'horloge, le depasse** et va au registre (A.247), sans etre une condition de Done (A.268, A.273 (b)). `nadia-prof` reste seule a prononcer le Done. [corrige le 2026-10-27 15:40, amendement du 2026-10-27 15:40 en fin de fichier : les six lots ont chacun une seconde lecture sur le disque, aucune n'est encore possible sans depasser 22,20 ; texte d'origine conserve]
- **`Q-273-1` (ii), chiffre inchange** : si la seconde lecture de l'horloge `MAT-CE1-11` compte 0,50 hors marge, 22,20 plus 0,50 = 22,70, soit 0,50 au-dessus de 22,20 [calcul].
- **Depassement de l'engage au registre, borne a 5,70** [calcul : 4,20 (A.267) plus 3 fois 0,50 ; verification : 19,20 moins 13,50 = 5,70]. Il etait de 5,20 apres `-03` (A.272). Le plafond n'est pas atteint par le cas haut mesure. [corrige le 2026-10-27 15:40, amendement du 2026-10-27 15:40 en fin de fichier : 8,70 apres les neuf secondes lectures (22,20 moins 13,50) ; texte d'origine conserve]

### 3. Questions : fermees, sans objet, ouvertes

Source : `DEC-0120` A.276 point 2 et point 3.

| Question | Etat | Fondement |
|---|---|---|
| `Q-274-1` (`nadia-prof`, prix et rattachement des arbitrages) | **fermee** | A.276 point 2 : `nadia-prof` n'a plus a chiffrer ses arbitrages |
| `Q-275-1` (utilisatrice, meme objet) | **fermee**, repondue le 2026-10-27 | A.276 point 2 |
| `Q-274-2` (utilisatrice, plafond, conditionnelle) | **ne se pose pas** | A.276 point 2 : sa condition (prix superieur a zero et projection recalculee au-dessus de 22,20) n'est pas remplie, le prix est 0,00 et la projection 22,20 |
| `Q-273-1` (i) et (ii) (`nadia-prof`) | **ouverte** | (i) un rejeu au-dela des trois compte-t-il contre le plafond de 22,20 ou contre un palier 10 ; (ii) la seconde lecture de l'horloge est-elle 0,50 hors marge (22,70 si oui). Aucun ecrit de `nadia-prof` sur le disque ; rien ne la bloque avant la fermeture des dix premiers audits (`P3`) |
| Q3 d'A.271 (quatrieme rejeu) | **ouverte, differee** | a rouvrir apres les dix premiers audits, proprietaire `camille-po` via `sofia-sm` |
| second membre de `Q-274-1` (iv) (le 1,00 des lots du registre du palier 4 absorbait-il des gestes de cette famille) | sans reponse, **non rouverte sauf demande** | A.276 point 3 : aucun chiffre n'en depend |

Restent en outre ouverts, inchanges : le constat de l'intrus de `-02` (`camille-po`, amendement du 16:09, section 6 ; `grep -c intrus` sur `DEC-0120` : 0 a la date de cet amendement) et l'ecart d'ordre de fusion (`theo-archi`, section 5 ci-dessous).

### 4. Remesure de fin de palier : etape inscrite

Source : `DEC-0120` A.276 point 3 et ligne `sofia-sm` du routage. **Etape, pas une porte, aucun seuil, aucun objectif chiffre** : l'utilisatrice n'en a donne aucun et A.276 n'en invente pas.

1. **`nadia-prof` donne**, en fin de palier, les durees reelles et le nombre de secondes lectures **par lot**, pour `-04`, `-05`, `-06`, `-08`, `-10`, `-12`.
2. **`sofia-sm` les inscrit au registre**, par amendement date, avec le fichier de `nadia-prof` cite. Je ne mesure aucune duree moi-meme et je n'en convertis aucune en bareme.
3. **Comparaison** : le nombre de secondes lectures des six lots arbitres avant premier jet (`-04` a `-12`) contre celui des trois premiers lots, sans arbitrage (`-01`, `-02`, `-03`, une seconde lecture chacun : trois [calcul]). Elle est inscrite telle quelle, sans seuil ; si les premiers jets ne sont pas plus propres, `nadia-prof` le dit dans ses lectures (A.273 point 2).
4. **Une seconde lecture demandee sur un lot reste a 0,50 hors marge** (A.272, A.270 point 4) : l'arbitrage a 0,00 ne l'absorbe pas et ne la modifie pas (A.276 point 3).

### 5. Etat des lots, lu sur disque (git log de round-two)

| Lot | Etat |
|---|---|
| `S57-BR-QLM-CE1-01` | contenu fait et relu (premier jet, lecture, corrections, seconde lecture, relecture des trois indices) ; audit `P2` non fait ; **aucun Done** |
| `S57-BR-QLM-CE1-02` | contenu fait et relu (premier jet `ebc274b1`, lecture `388cb638`, corrections `f7c6bc7d`, relecture des corrections `e0d2ba08`, aucune ouverture) ; demande de `nadia-prof` d'encoder `I1` (*"meme type d'article"*), forme ecrite par `theo-archi` dans `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md` (fusionnee, `fb689ee2`) ; audit `P2` non fait ; **aucun Done** |
| `S57-BR-QLM-CE1-03` | contenu fait et relu ; audit `P2` non fait ; **aucun Done** |
| `-04`, `-05`, `-06`, `-08`, `-10`, `-12` | stories dans `docs/backlog/backlog.md` (fusions `649f517b` et `e6f21737`, regles de `nadia-prof` reportees) ; arbitrages de `nadia-prof` : `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, `-06-08.md`, `-10-12.md` (fusions `c23e7aa6`, `badac22f`, `a9d0d974`) ; avis de gardes de `theo-archi` : `avis-theo-palier-5-gardes-ce1-qlm-04-05-et-article-02.md`, `avis-theo-palier-5-gardes-ce1-qlm-06-08.md`, `avis-theo-palier-5-gardes-ce1-qlm-10-12.md` (fusions `fb689ee2`, `dbc0fb3a`, `3490ea14`) ; **contradictions en cours de tranchage par `nadia-prof`**, non fusionnees ; **aucun premier jet** : `git ls-files` ne trouve que trois paquets `ce1-qlm-*` \| [corrige le 2026-10-27 15:40, amendement du 2026-10-27 15:40 en fin de fichier : les six premiers jets, lectures, corrections et relectures existent et sont fusionnes, voir la section 1 de cet amendement ; texte d'origine conserve]

- **L'autocontrole d'A.273 point 2 s'applique** au premier jet de chacun des six lots, avant son commit (section 2 de l'amendement du 16:25).
- **Ordre de fusion serialise, rappel** : `-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`, une fusion a la fois, chaque branche partant du sommet de `round-two` qui porte la fusion precedente ; lignee verifiee par `bash outils/verifier-lignee.sh` avant chaque fusion (source : amendement `s57-p5-sofia-planning`, section 3, et A.260). **Ecart connu, non rouvert** : `-03` (`ccfd1a0c`) a ete fusionne avant `-02` (`7c2b448c`) ; le point adresse a `theo-archi` (amendement du 16:09, section 7, point 2) reste tel quel.
- `QC3` inchange : aucune entree `ce1-qlm-*` au catalogue avant un Done. **Aucune ligne de Done n'est inscrite.**

### 6. Deux faits notes, non tranches

1. **Le garde-fou des identifiants echoue sans `-Dsurefire.failIfNoSpecifiedTests=false`** quand il est lance avec `-pl bootstrap -am -Dtest=...`. Fait signale par `nadia-prof` dans `arbitrage-nadia-premiers-jets-ce1-qlm-04-05.md`, section 8 : la commande telle quelle sort *"BUILD FAILURE"* sur le module `recreo-domain` (*"No tests matching pattern ... were executed"*), avec le drapeau elle sort *"Tests run: 10, Failures: 0"*, *"BUILD SUCCESS"* ; elle ajoute que ses lectures de `-02` et de `-03` rapportaient la commande telle quelle en `BUILD SUCCESS` et qu'elle ne cherche pas la cause. **Ce que j'ai trouve** (recherche de `failIfNoSpecifiedTests` par `grep`) : `CLAUDE.md`, `squad/README.md`, `outils/mvn-jdk21.sh`, `outils/mvn-jdk21-test.sh`, `.github/workflows/ci.yml` et `pom.xml` n'en parlent pas ; `.claude/agents/nadia-prof.md` (ligne 163) prescrit la commande **sans** le drapeau ; `docs/backlog/backlog.md` et `squad/conformite/ARBITRAGE-PREALABLE-s52-instances-ce1.md` la citent **avec**. Le `-am` de `CLAUDE.md` (section Prerequis de build) est la raison pour laquelle les modules amont sont construits ; rien ne dit pourquoi la commande reussissait avant. **Non tranche, non route par cet amendement** : categorie qualite et test (`yanis-qa`), avec le texte prescrit a `nadia-prof` ; je n'ai lance aucune commande Maven.
2. **Deux fichiers de `nadia-prof` citent encore 20,20 comme plafond** alors que l'utilisatrice l'a releve a 22,20 : `reponse-nadia-palier-5-rejeu-horloge-et-double-compte.md` (lignes 12, 118, 120, 124) et `relecture-nadia-trois-indices-ce1-qlm-etapes-de-developpement.md` (lignes 129 et 141, par `grep`). Ils ne sont pas touches, par decision d'A.273 (*"les fichiers de `nadia-prof` sont les siens et datent du moment ou le plafond etait 20,20"*). Le plafond en vigueur est 22,20.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il inscrit l'execution d'A.276 : arbitrages avant premier jet a 0.00, non chiffre, dans le 1,0 du lot ; ligne hors marge a 1,50 (relecture des corrections de `-02`, A.272) ; cas haut 19,20 ; remesure de fin de palier ; fermeture de `Q-274-1` et `Q-275-1`, `Q-274-2` sans objet. Restent ouverts : `Q-273-1` (`nadia-prof`, et l'utilisatrice si le plafond couvre un palier 10), Q3 d'A.271 (fermeture des dix premiers audits), le constat de l'intrus de `-02` (`camille-po`), l'ecart d'ordre de fusion (`theo-archi`), les deux faits de la section 6 (non routes). Aucun Done n'est prononce.

## Amendement du 2026-10-27 15:40 (UTC+01:00), palier des neuf lots de questionner le monde CE1 : etat des neuf lots lu sur disque, neuf secondes lectures (A.278), decision de l'utilisatrice sur la condition c d'A.277, remesure, points ouverts, etapes restantes

Ecrit par `sofia-sm`, casquette Scrum Master, dans le commit de la branche `s57-p5-sofia-p5` (base `round-two` a `f007d698`, lu par `git rev-parse` ; `round-two` porte depuis `c93199d0`, que j'ai lu par `git show round-two:`). Il execute la ligne `sofia-sm` d'A.278 (`DEC-0120`, `camille-po`, **fusionne** dans `round-two` : `c93199d0`) et d'A.277 (fusionne : `ae2c3fd3`), que je ne modifie pas. **A.279 n'est pas citee comme fusionnee** : la branche `s57-p5-camille-279` n'a pas de commit propre et `DEC-0120` de `round-two` ne contient aucun `A.279` (`grep`). Il ne modifie ni `DEC-0120`, ni le backlog, ni le code, n'ouvre aucun palier, ne fixe aucun prix et ne prononce aucun Done. Les sommes sont des calculs, marques **[calcul]**. Aucune mesure n'a ete lancee. **Ordre reel des amendements sur le disque** (`grep "^## Amendement"`) : celui du **2026-10-26 16:25** (plafond 22,20, autocontrole) precede celui du **2026-10-27 08:19**, puis celui-ci. La consigne de travail et A.278 ecrivent "2026-10-27 16:25" : la date portee par le fichier est le 26.

### 1. Etat des neuf lots, lu sur disque (`git log round-two`, `git rev-parse ^2` de chaque fusion)

**Aucun Done n'est prononce, par personne : `nadia-prof` n'a prononce aucun Done pedagogique sur aucun des neuf lots, aucun des neuf n'est au catalogue (`QC3` : aucune entree `ce1-qlm-*` a inscrire avant un Done), et aucun `mvn verify` complet n'a ete lance (fait donne par l'agent lanceur, non verifie par moi ; les relectures lues ecrivent "aucun autre job de `ci.yml` lance" et ne lancent que le test du paquet et le garde-fou des identifiants). Les neuf lots sont des contenus lus et corriges, rien de plus.**

Paquet de chaque lot, sous `adapters/adapter-content/src/main/resources/contenu/` : `ce1-qlm-etapes-de-developpement.yaml` (`-01`), `ce1-qlm-ce-que-mangent-les-animaux.yaml` (`-02`), `ce1-qlm-besoins-de-la-plante.yaml` (`-03`), `ce1-qlm-familles-d-aliments.yaml` (`-04`), `ce1-qlm-eau-trois-etats.yaml` (`-05`), `ce1-qlm-aimant.yaml` (`-06`), `ce1-qlm-dangers-electricite.yaml` (`-08`), `ce1-qlm-paysages.yaml` (`-10`), `ce1-qlm-generations.yaml` (`-12`).

**Commits, dans l'ordre** (SHA, heure ; fusion dans `round-two` entre parentheses ; `-01`, `-02`, `-03` le 2026-10-26, `-04` a `-12` le 2026-10-27). Les lignes `-01` a `-03` reprennent le registre des amendements du 13:35 et du 16:09, sans le dupliquer.

| Lot | Premier jet (`marc-dev`) | Lecture (`nadia-prof`) | Corrections (`marc-dev`) | Relecture (`nadia-prof`) |
|---|---|---|---|---|
| `-01` | `7c922786` 10:22 | `02add592` 10:57 | `47279e05` 11:19 | `408fec39` 11:40 (puis trois indices : `5e56ea7e` de `marc-dev`, `10bc0f8e`, `9e8542e1`, `e299a288` de `nadia-prof`, registre du 13:35) |
| `-02` | `ebc274b1` 14:51 (`7c2b448c`) | `388cb638` 15:52 (`af6937a2`) | `f7c6bc7d` 16:10 (`cbb984e1`) | `e0d2ba08` 16:29 (`96b11d84`) |
| `-03` | `7fe939a6` 14:13 (`ccfd1a0c`) | `30ba34de` 14:55 (`81d203b1`) | `37f49795` 15:42 (`b1fa3e1f`) | `dddd54b3` 15:58 (`527ca288`) |
| `-04` | `a9b1480a` 10:33 (`2485dc73`) | `c80ea30d` 11:10 (`d6dc2fed`) | `f273c6d9` 11:43 (`01e0b4e4`) | `5a9ded0f` 12:22 (`986b163e`) |
| `-05` | `e6694d47` 11:32 (`142c3ca7`) | `3131431f` 13:41 (`4d6a2c2c`) | `e0875968` 14:33 (`ffe7c0dd`) | `a463e55e` 14:56 (`13a50814`) |
| `-06` | `c86148d1` 11:35 (`fbe5295c`) | `d32d2777` 12:34 (`bd5472d6`) | `f3d7357c` 14:29 (`77059724`) | `330de000` 14:57 (`3e611f72`) |
| `-08` | `c8609344` 11:33 (`d37ac5f7`) | `4dfcda36` 13:43 (`a373b7d3`) | `fd24ca67` 14:41 (`c9bc965a`) | `6eef6852` 15:00 (`de8a8819`) |
| `-10` | `7cc77996` 11:38 (`3f39bb78`) | `b11e4dfa` 12:31 (`7b3326a7`) | `beb1baf5` 14:34 (`722ec0b4`) | `8271c0fb` 14:57 (`eac3ecd5`) |
| `-12` | `de814b09` 11:38 (`bdfed668`) | `c577ca31` 12:29 (`a516d40b`) | `249aaf71` 14:30 (`0286fde1`) | `de626e12` 14:59 (`f007d698`) |

Les SHA de `-04` a `-12` sont verifies : `git log` rend chaque commit sous le chemin de son paquet ou de son fichier, et `git rev-parse <fusion>^2` rend le commit cite. Les durees, verdicts et couts sont lus dans les fichiers de `nadia-prof`, `lecture-nadia-premier-jet-ce1-qlm-<jeu>.md` et `relecture-nadia-corrections-ce1-qlm-<jeu>.md`, sous le dossier du sprint.

| Lot | Items | Verdict de la lecture du premier jet | Duree de la lecture | Verdict de la relecture | Duree de la relecture | Cout au bareme (A.272) [calcul] |
|---|---|---|---|---|---|---|
| `-01` | 15 | 8 justes, 5 a corriger, 2 refuses en l'etat | non donnee en minutes (son fichier donne des tokens) | 15 items justes ; trois indices arbitres apres, 0,0 (A.268) | non donnee en minutes | 1,0 plus 0,50 hors marge = 1,5 |
| `-02` | 12 | 6 justes, 6 a corriger | 19 min 00 s | dix points tiennent, aucune ouverture | 10 min 08 s | 1,0 plus 0,50 hors marge = 1,5 |
| `-03` | 14 | 6 justes, 8 a corriger | 22 min 52 s | dix points tiennent, aucune ouverture | 7 min 10 s | 1,0 plus 0,50 hors marge = 1,5 |
| `-04` | 12 | 10 justes, 2 a corriger | 28 min 48 s | tout tient (items 8 et 11, en-tete de source, parentheses, garde `T1`), aucune ouverture | 13 min 12 s | 1,0 plus 0,50 hors marge = 1,5 |
| `-05` | 12 | 9 justes, 3 a corriger | 1 h 31 min 53 s, que son fichier dit surestime (session arretee puis relancee, arret non mesure) | items 4, 8, 12 tiennent, aucune ouverture | 6 min 47 s | 1,0 plus 0,50 hors marge = 1,5 |
| `-06` | 12 | 8 justes, 4 a corriger | 24 min 40 s | items 5, 6, 9, 12 et `g12` tiennent, aucune ouverture | 7 min 26 s | 1,0 plus 0,50 hors marge = 1,5 |
| `-08` | 12 | 5 justes, 7 a corriger | 27 min 26 s (l'arret de session de 12:36 a 13:39 n'est pas compte, ses mots) | 13 lignes conformes, gardes `T1` a `T4` tiennent, aucune ouverture | 10 min 2 s | 1,0 plus 0,50 hors marge = 1,5 |
| `-10` | 13 | 9 justes, 4 a corriger, `R10-2` et `R10-4` B rouverts | 22 min 27 s | `L1` a `L5`, `T1` a `T5` tiennent, aucune ouverture | 7 min 42 s | 1,0 plus 0,50 hors marge = 1,5 (la reouverture est dans le 1,0, A.277) |
| `-12` | 12 | 9 justes, 3 a corriger (6, 9, 12), `R12-2` B rouverte | 20 min 14 s | `K1` a `K6`, `X1` tiennent, aucune ouverture | 7 min 41 s | 1,0 plus 0,50 hors marge = 1,5 (idem, A.277) |

Le verdict "justes / a corriger" est celui de la lecture, avant correction. Les relectures de `-04` a `-12` ecrivent chacune qu'elles ne rouvrent aucun item et n'ouvrent aucun tour ; celles de `-02` et `-03` ecrivent "aucune ouverture". Ecarts que `nadia-prof` constate et garde, sans correction demandee : `-04`, la phrase d'en-tete de source de `marc-dev` retenue a la place de la sienne, qu'elle dit fausse ; `-05` et `-08`, l'espace insecable avant `?` que sa phrase demandait et que son tableau n'avait pas ; `-08`, une phrase de sa propre lecture qu'elle corrige ; `-10`, sa colonne "Avant" fausse pour la question de l'item 6.

**Ordre des fusions, faits.** (1) Ecart connu, non rouvert : `-03` (`ccfd1a0c`, 2026-10-26 14:30) avant `-02` (`7c2b448c`, 15:22), deja inscrit au 16:09. (2) Les premiers jets de `-04` a `-12` sont fusionnes dans l'ordre ecrit (10:38, puis 11:53, 11:57, 12:04, 12:06, 12:06). (3) Les fusions des lectures de `nadia-prof` ne le sont pas : `-04` (11:29), puis `-06` (13:39), `-10` et `-12` (13:40), puis `-05` (13:44) et `-08` (13:45). (4) Les premiers jets de `-05`, `-06`, `-08`, `-10`, `-12` (commits de 11:32 a 11:38) precedent la lecture de `-05` (commit de 13:41), alors que `P1` et `QC7` ecrivent que la lecture d'une paire precede les premiers jets de la paire suivante (`DEC-0120` A.260, `P1` ; amendement du 2026-10-26, section 2, point 5). Je constate, je ne tranche pas : voir P9 et C4.

**Inscription des premiers jets, dite.** L'amendement du 2026-10-26, section 2, points 1 et 2, exige une ligne (lot, commit, heure) ecrite par moi avant tout `verify` complet. Aucune ligne n'existe dans ce fichier pour `-04`, `-05`, `-06`, `-08`, `-10`, `-12` (`grep` de leurs SHA : 0). **Le tableau ci-dessus la porte, ecrite le 2026-10-27 15:40, apres les lectures de `nadia-prof` et non avant elles.** Je ne la date pas plus tot qu'elle n'est. Aucun `verify` complet ne l'a precedee.

### 2. Ligne de registre de l'instance neuve : neuf secondes lectures, hors marge

Source : `DEC-0120` A.272 (regle, point 1 : *"0,5, hors marge, pour chaque lot de l'instance neuve, que les lignes soient une ou quinze"*), A.270 points 3 et 4, A.277, **A.278 (en vigueur)** ; relectures lues en section 1.

| Ligne | Marge consommee | Ligne hors marge (A.247) | Marge disponible aux rejeux |
|---|---|---|---|
| instance neuve (`marc-dev`, les neuf lots `S57-BR-QLM-CE1`) | **0,00** | **4,50** : `-01` 0,50 (`408fec39`), `-02` 0,50 (`e0d2ba08`), `-03` 0,50 (`dddd54b3`) deja inscrites (1,50) ; plus `-04` 0,50 (`5a9ded0f`), `-05` 0,50 (`a463e55e`), `-06` 0,50 (`330de000`), `-08` 0,50 (`6eef6852`), `-10` 0,50 (`8271c0fb`), `-12` 0,50 (`de626e12`) [calcul : 9 fois 0,50] | **1,5** : trois rejeux a 0,50 |

- **Prix : 0,50 par seconde lecture, hors marge, d'apres A.272 (regle, point 1)** ; A.278 classe les six relectures comme secondes lectures. Cout de chacun des neuf lots : 1,5 [calcul : 1,0 plus 0,50].
- **Cas haut.** Regle d'A.270 point 3, reprise par A.272 (section Cout) et A.273 point 1 (a) : chaque seconde lecture s'ajoute **en entier** au cas haut mesure de 17,70 (A.267) ; A.276 n'ajoute aucun arbitrage (0,00). **17,70 plus 9 fois 0,50 = 17,70 plus 4,50 = 22,20** [calcul]. Verification : 19,20 (08:19, trois lectures) plus 6 fois 0,50 = 22,20 [calcul]. C'est une **borne haute, pas une consommation** (A.278, point 4) : on ne retranche pas ce que chaque lot compte deja dans les 0,42.
- **Ecart au plafond de 22,20 : 0,00** [calcul : 22,20 moins 22,20]. **Reserve : 0,00.** Le plafond est atteint, non depasse.
- **Depassement de l'engage au registre, borne a 8,70** [calcul : 22,20 moins 13,50 ; verification 4,20 plus 4,50]. Il etait de 5,70 au 08:19.
- **Ce que le total ne compte pas** : une troisieme lecture d'un lot (0,50, A.272 regle point 3), la seconde lecture de l'horloge `MAT-CE1-11` si elle compte hors marge (`Q-273-1` (ii) : **22,70**, soit 0,50 au-dessus [calcul]), les deux ensemble : **23,20** [calcul : 22,20 plus 1,00]. Aucune n'existe a ce jour. Un cout au-dela de 22,20 va au registre (A.247), sans etre une condition de Done (A.273 (b), A.268).
- **Reserve de projection levee.** Les amendements du 16:25 et du 08:19 ecrivaient "projection" ou un cas haut conditionnel : A.278 est fusionne, les neuf secondes lectures sont sur le disque, le chiffre de 22,20 est le cas haut des neuf secondes lectures existantes. Marqueurs *[corrige ...]* poses en section 7.

### 3. Decision de l'utilisatrice : `-04` reste une seconde lecture

Source : message de l'agent lanceur du 2026-10-27 (forme 2 de `DEC-0062` G : relaye, non lu de sa main). Mots de l'utilisatrice, cites et rien d'autre : *"O1 garder la lecture de camille-po, où -04 reste une seconde lecture à 22,20"*. Contexte : la condition c d'A.277 (*"`marc-dev` applique les corrections telles qu'ecrites"*) lue sur sa consequence et non a la lettre ; le risque ne portait que sur `-04` (A.278, point 2 : a la lettre, `-04` serait une troisieme lecture, 22,70).

- **Ce que cette decision fait au registre** : `-04` compte 0,50, seconde lecture ; total **22,20** ; aucune troisieme lecture ; la ligne hors marge de la section 2 ne change pas. Le chiffre 22,70 ne s'applique pas a `-04`.
- **A.279** de `camille-po` l'ecrit ; **elle n'est pas fusionnee**, je ne la cite pas comme en vigueur et je ne lis pas son contenu. Cette section inscrit la decision de l'utilisatrice, pas l'amendement. Je ne decide rien d'autre.

### 4. Remesure de fin de palier (A.276 point 3) : releve intermediaire, sans conclusion

Source : `DEC-0120` A.276 point 3 et sa ligne `sofia-sm` ; durees ecrites par `nadia-prof` dans ses fichiers (section 1). **Le palier n'est pas clos** (aucun audit n'a eu lieu) : ce sont les durees lues dans ses fichiers, pas un message de fin de palier ; si elle en donne d'autres a la fin, je les inscris par un autre amendement. Je n'en convertis aucune en bareme (A.275, A.276) et ne pose aucun seuil.

| Groupe | Lot | Duree de lecture du premier jet | Duree de relecture | Secondes lectures | Items a corriger au premier jet |
|---|---|---|---|---|---|
| sans arbitrage avant premier jet | `-01` | non donnee en minutes | non donnee en minutes | 1 | 5 plus 2 refuses en l'etat |
| | `-02` | 19 min 00 s | 10 min 08 s | 1 | 6 |
| | `-03` | 22 min 52 s | 7 min 10 s | 1 | 8 |
| arbitres avant premier jet (A.276) | `-04` | 28 min 48 s | 13 min 12 s | 1 | 2 |
| | `-05` | 1 h 31 min 53 s (surestime, dit par elle) | 6 min 47 s | 1 | 3 |
| | `-06` | 24 min 40 s | 7 min 26 s | 1 | 4 |
| | `-08` | 27 min 26 s | 10 min 2 s | 1 | 7 |
| | `-10` | 22 min 27 s | 7 min 42 s | 1 | 4 |
| | `-12` | 20 min 14 s | 7 min 41 s | 1 | 3 |

- **Nombre de secondes lectures** : les trois premiers lots, trois (une chacun) ; les six lots arbitres, six (une chacun). **Fait : toutes les relectures ont tenu sans correction supplementaire** ; aucune troisieme lecture n'existe. Je ne tire aucune conclusion ni aucun seuil.
- **Conditions d'A.277 lues sur `-06`, `-10`, `-12`** : (a) les phrases rouvertes de `-10` et `-12` sont ecrites dans le fichier de lecture du premier jet (A.277 le lit ; titre du commit `b11e4dfa` : *"R10-2 et R10-4 B rouverts"*), la relecture de `-06` ne rouvre rien ; (b) aucun item *juste* n'est rouvert ; (c) aucune correction n'est rejetee ni reformulee par `nadia-prof`, lecture de la condition tranchee en section 3 ; (d) `-10` : elle lit les lignes `distracteurs` des items A et les indices pour l'unicite et `F3`, et ecrit que ces lectures *"ne rejugent aucun item"* ; A.278 (point 1) lit cela comme n'etant pas une relecture du paquet. Aucune troisieme lecture n'est a router a `camille-po`.

### 5. Points ouverts et a qui, sans decision de ma part

| Ref | Sujet | A qui | Fait lu |
|---|---|---|---|
| P1 | `Q-273-1` (i) un rejeu au-dela des trois compte-t-il contre 22,20 ou contre un palier 10 ; (ii) la seconde lecture de l'horloge est-elle 0,50 hors marge | `nadia-prof` ; l'utilisatrice, par moi, si le plafond couvre un palier 10 | aucun ecrit de `nadia-prof` ne la mentionne (`grep -l "Q-273-1" squad docs` : `DEC-0120`, `DEC-index.md`, `squad/lessons.md`, `planning.md`). A.278 (point 5, ligne 1) : la reserve etant 0,00, (ii) doit etre repondue avant que la seconde lecture de l'horloge soit ecrite |
| P2 | Q3 d'A.271 : un quatrieme rejeu | `camille-po`, a la fermeture des dix premiers audits, par moi | differee, inchangee (A.271 point 4) |
| P3 | Seconde lecture de l'horloge a 22,70 | `nadia-prof` (partie de P1 (ii)) | 22,20 plus 0,50 [calcul] ; 23,20 avec une troisieme lecture |
| P4 | Constat de l'intrus : dans la tache B de `-02`, la bonne reponse est l'intrus des trois propositions | `nadia-prof` le regarde a l'audit `P2` (sa lecture de `-02`, sections 4 et 10) ; `camille-po` pour dire ce que l'audit regardera | routee au 16:09 (section 6) a `camille-po` ; `grep -c intrus` sur `DEC-0120` de `round-two` : 1, dans A.278 (tableau du point 5, ligne 4), qui l'inscrit sans ecrit de cloture |
| P5 | Accord de *du plus age au plus jeune* (items 3 et 8 de `-12`, formule de `R12-2`) | `relecteur-pedagogique` | signal de `nadia-prof` (lecture de `-12`, section 5), elle ne le corrige pas ; aucun `releve-relecteur` du palier 5 n'existe dans le dossier du sprint (seulement paliers 1 a 3) |
| P6 | La commande du garde-fou des identifiants, `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, echoue sans `-Dsurefire.failIfNoSpecifiedTests=false` | `yanis-qa` (categorie qualite et test, A.278 ligne 6), `theo-archi` si c'est une regle d'architecture ; je ne choisis pas | `grep` : le drapeau est absent de `CLAUDE.md`, `squad/README.md`, `outils/`, `.github/`, `pom.xml` et `.claude/` ; `.claude/agents/nadia-prof.md` ligne 163 prescrit la commande sans lui ; il est present dans 15 fichiers de `squad/decisions/adr/`, dans `docs/backlog/backlog.md`, dans des fiches de `squad/conformite/` et dans les relectures de `nadia-prof`. Cause non cherchee, aucune commande Maven lancee par moi |
| P7 | `.github/scripts/verifier-chemins.sh` et `verifier-prefixes.sh` lisent leur entree sur l'entree standard : lances sans redirection, ils attendent | a documenter ; aucun proprietaire designe par un texte lu (`yanis-qa` pour la CI, je propose sans trancher) | l'en-tete de `verifier-chemins.sh` dit qu'il lit une liste de chemins sur l'entree standard, celui de `verifier-prefixes.sh` des sujets de commit ; `ci.yml` les alimente par `git ls-files` et `git log --format=%s` ; `CLAUDE.md` et `squad/README.md` ne disent pas comment les lancer a la main. **Fait rejoue** : `verifier-chemins.sh < /dev/null` rend *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0, **sur une liste vide : il n'a verifie aucun fichier**. Les relectures lues de `-06`, `-08`, `-10`, `-12` citent cette sortie dans leur fichier |
| P8 | Distracteur *le mulot* de `cm1-sciences-chaine-alimentaire.yaml` (instance du CM1) | `nadia-prof` rend le verdict (A.278, ligne 7), puis `camille-po` pour une story | A.271 : *"item deja note, a traiter a part"* ; aucune occurrence de *mulot* dans `docs/backlog/backlog.md` (A.278) ; je ne modifie pas ce paquet |
| P9 | Ordre de fusion : `-03` avant `-02` (deja inscrit), et faits (3) et (4) de la section 1 | `theo-archi` (16:09, section 7, point 2) pour la regle de l'ordre ; `nadia-prof` et `camille-po` pour `P1` | aucun ecrit de `theo-archi` sur ce point lu ; je ne declare pas ce qu'il aurait fallu faire |
| P10 | Faits du rapport de `marc-dev` absents du depot : le mot que la page ecrit (`-04`) et le choix sur *celle des enfants* et *celle des arriere-grands-parents* (`-12`, `M4`, `M8`) ne sont que dans la relecture de `nadia-prof` | `marc-dev`, par moi (A.278, ligne 8) | **routee ici a `marc-dev`** : les ecrire dans un fichier du depot ; je n'en dicte pas le contenu |

### 6. Etapes restantes du palier d'apres les textes en vigueur

| Ref | Etape | Texte qui la dit | Ce que le texte ne dit pas |
|---|---|---|---|
| E1 | **Porte technique** : CI verte = tous les jobs de `.github/workflows/ci.yml`, soit quatre : `prefixes`, `chemins`, `outils`, `build` (`build` seul porte `mvn -B verify`, puis `mvn -B -pl bootstrap -am package`) | `CLAUDE.md`, "Double Done" ; `squad/README.md`, section 7 ; backlog `QC8` ; `ci.yml` (identifiants lus : lignes 9, 68, 84, 139). Un agent qui l'annonce nomme les jobs lances (`CLAUDE.md`) ; total de tests etabli par moi sur l'arbre fusionne, lignee par `outils/verifier-lignee.sh` (amendement du 2026-10-26, section 3, point 3) | qui lance le `verify` complet de chaque lot ; si le Done technique se juge par lot ou une fois a la cloture du palier (C1) |
| E2 | **Jeu a l'ecran par `nadia-prof`** (audit `P2`) : audits joues, jar servi, commit joue nomme, dans l'ordre `-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`, chaque lot apres son `verify` | `DEC-0120` A.260, `P2` ; `arbitrage-nadia-palier-5-qlm-9-lots.md`, section 5.3 ; `CLAUDE.md` ("apres avoir joue l'increment") | qui sert le jar |
| E3 | **Done pedagogique** : `nadia-prof` seule, `VALIDE`, `VALIDE SOUS RESERVE` ou `REFUSE`, motif ecrit dans `squad/conformite/FICHE-<jeu>.md` | `CLAUDE.md`, "Double Done" ; `squad/README.md`, section 7 | le nom du fichier de fiche d'un lot `ce1-qlm-*` : aucun texte lu ne le donne, et aucune fiche `ce1-qlm` n'existe a ce jour (C3) |
| E4 | **Entree au catalogue** (`QC3`) : `paquet: <P>` dans `catalogue.yaml` seulement apres un Done, ou un refus leve ; un paquet refuse n'est pas servi | backlog `QC3` ; `DEC-0120` A.260, `P2` ; `avis-theo-palier-5-neuf-lots-qlm-ce1.md` (ligne `catalogue.yaml` : *"branchement, apres le Done (`QC3`)"*) | qui ecrit l'entree et dans quel commit, avec des fusions serialisees ; amendement du 2026-10-26, section 3, point 5 : *"non tranche"*, toujours le cas (C2) |
| E5 | **Horloge `MAT-CE1-11`** (`ce1-calendrier-dates`, instance B, `lea-dev`) : auditee apres les neuf premiers audits et avant tout rejeu | `DEC-0120` A.263 (Q2, option b), A.265 (`P3` de `nadia-prof`), A.267 ; amendement du 2026-10-26 (ordre des audits) | son etat au palier 5 : je ne l'ai pas lu ; seule la ligne du palier 4 (refus puis rejeu, ce fichier) est dans ce que j'ai lu |
| E6 | **Rejeux** : trois au plus, un par lot au plus, aucun avant le premier audit des dix lots, attribues aux premiers lots refuses dans l'ordre de `P2` ; la marge de 1,5 les paie | `DEC-0120` A.265 (`P3`), A.266, A.267, A.270 | un quatrieme rejeu : Q3 d'A.271 (P2), et `Q-273-1` (i) (P1) |
| E7 | **Cloture du palier** : `cloture-palier-5.md`, ecrit une seule fois par moi ; obstacles du palier dans ce fichier, puis dans `review.md` | `squad/README.md`, paragraphe "Un sprint a paliers a une seule review et une seule retro" (amendement du 2026-10-14, `Q-S1`) | `cloture-palier-5.md` n'existe pas |

Contradictions et manques, signales et non combles :

- **C1.** `CLAUDE.md` et `squad/README.md` definissent le Done technique par la CI entiere (quatre jobs) ; `P2` et `QC7` parlent du `verify` de chaque lot, qui est le seul job `build`. Aucun texte lu ne dit comment les deux se composent lot par lot.
- **C2.** `QC3` (aucune entree au catalogue avant un Done) et les fusions serialisees d'un fichier a ajouts en fin de liste : ouverts depuis le 2026-10-26.
- **C3.** Le nom de la fiche de conformite d'un lot `ce1-qlm-*` n'est dans aucun texte lu.
- **C4.** L'ordre `P1` (lecture d'une paire avant les premiers jets de la suivante) n'a pas ete suivi (section 1, fait 4) ; aucun texte lu ne l'a leve. Ce n'est pas a moi de le declarer valide ou fautif.
- **C5.** Des relectures de `nadia-prof` citent `verifier-chemins.sh < /dev/null` comme verification : la sortie est celle d'une liste vide (P7). La forme de la CI est `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh`.

### 7. Passages des amendements precedents marques *[corrige ...]*

Texte d'origine conserve dans chacun. Quatre passages de l'amendement du 2026-10-27 08:19 (section 2, trois puces ; section 5, ligne des lots `-04` a `-12`) sont suivis d'un marqueur renvoyant a cet amendement.

### Decisions prises

**Aucune `ADR` ni aucune `DEC` n'est creee ou amendee par cet amendement.** Il inscrit : l'etat des neuf lots lu sur disque, neuf secondes lectures a 0,50 hors marge (execution d'A.272, regle, et d'A.278), la ligne hors marge a 4,50, le cas haut 22,20 sans reserve, le depassement de l'engage borne a 8,70, la decision de l'utilisatrice sur `-04` (A.279 non fusionnee), le releve de remesure, dix points ouverts routes et sept etapes restantes avec leur texte. **Aucun Done n'est prononce, aucun lot n'est au catalogue, aucun `mvn verify` complet n'a ete lance.**
