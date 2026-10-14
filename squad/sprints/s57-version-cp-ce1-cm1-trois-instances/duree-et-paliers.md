# s57 - duree et paliers : mise a jour de la section 1 de preparation.md

- **Auteure** : `sofia-sm`, casquette Scrum Master, 2026-10-14 13:56 (UTC+02:00), lu par `date`. Arbre : `round-two` a `d5bd4469`, worktree dedie, lignee verifiee (`git merge-base --is-ancestor round-two HEAD`, sortie 0).
- **Ce fichier n'est pas `planning.md`.** Aucun horodatage de debut, aucun objectif gele, aucune affectation : **s57 n'est pas ouvert**. Il ne modifie aucun mot de `preparation.md` : il remplace ses propositions P1 a P4 (section 1.3) par celles de la section 3.
- **Statut** : proposition de `sofia-sm`. La duree est mon poste (`DEC-0120` A.36, A.45), mais la forme a paliers attend `Q3` de `nadia-prof` a l'utilisatrice (section 4).
- **Mots de l'utilisatrice cites** : *"oui plus que 8"* vient de `DEC-0120` A.38, forme 2, relaye par l'agent lanceur de `camille-po` ; je ne l'ai pas lu de sa main.

## 1. Ce que "palier" veut dire ici

Je retiens **le palier d'audit de `nadia-prof`** : une fenetre d'audit qui tient seule `PAR-8` et `PAR-9`, close pour toutes ses instances avant l'ouverture de la suivante. Source : `ARBITRAGE-PREALABLE-s57-criteres-de-la-version-cp-ce1-cm1.md`, section 11.3 (decision du 2026-10-13, six conditions). Ce n'est pas un palier d'une autre sorte :

- le palier visuel (`ADR-0073`, palier 1 et 2 des images ; sprint s25) est une notion d'interface, sans lien avec la duree ;
- les etapes du sprint (`squad/README.md` section 9) ne portent pas ce nom.

Motif du choix : c'est le seul sens que le depot attache a la duree de s57. La section 11.3, condition 6, dit que *"la duree, et le nombre de paliers qu'elle porte, sont a `sofia-sm`"*, et `DEC-0120` A.50 et A.51 reprennent ce terme dans ce sens.

## 2. Ce qui a change depuis la preparation du 2026-10-13 16:24

Commande : `git log --since=2026-10-13 --name-status` sur `round-two`, lue a `d5bd4469`.

| Fait | Source | Effet sur la duree |
|---|---|---|
| `Q1` de `sofia-sm` repondue : *"oui plus que 8"* | `DEC-0120` A.38 | **P2 est contredite** : l'utilisatrice veut un sprint qui porte plus de 8,0 |
| `nadia-prof` corrige sa phrase 4.4 point 2, sur laquelle P2 etait fondee : vraie pour des lots empiles dans une fenetre, pas pour des fenetres closes une a une | criteres, 11.2 | l'allongement est recevable sous forme de paliers |
| Borne : **8,0 par palier**, 5,0 et trois lots par instance et par palier ; aucune borne en heures ; aucun chiffre au-dela de 8,0 dans un palier ; O2 non derivable, (ii) non etablie | criteres, 11.3 et 11.4 | la borne du sprint est 8,0 x n |
| Condition (i) mesuree par `camille-po`, deux lectures non departagees | `DEC-0120` A.50 | aucune derivation de O2 ; `nadia-prof` tranche la lecture |
| `Q-U10` repondue et executee : maximum fige a **200** (142 / 200 = 71,00 %), plancher de travail **49,0** | `DEC-0120` A.38, A.42, A.49 | 49,0 divise par 5,0 de travail par palier a trois instances = 9,8, soit 10 paliers au plus bas, tarifs de bareme (A.31, A.42 : non recalcule par `camille-po`) |
| `Q2` repondue : `lea-dev` developpe `S54-OBS-SCI-CONSIGNE` | `DEC-0120` A.38, A.44 | aucun |
| `Q-N1` repondue par `nadia-prof` (0,75 non affectes, pas 0,25) | criteres, 10.2 | aucun |
| Amendement A2 d'`ADR-0065` ecrit (`7a34366c`) puis fusionne (`729b0d8a`, 17:42), avant tout planning | `git merge-base --is-ancestor` : sortie 0 pour `7a34366c`, `bac0a6d1` et `729b0d8a` | la sequence de `preparation.md` section 2 change, voir section 5 |
| `DEC-0120` reste `Proposee` | A.52 | elle passe a `Acceptee` quand la duree et la borne sont ecrites (A.45) |

Durees effectives, inchangees (`grep -n "Duree effective" squad/sprints/s5[2-6]-*/review.md`, relue le 2026-10-14) : s52 13 h 10 min, s53 3 h 05 min, s54 17 h 33 min, s55 7 h 38 min, s56 41 h 38 min.

## 3. Propositions (remplacent P1 a P4 de `preparation.md`)

- **P1'. s57 est un sprint a paliers de 8,0, sans date et sans timebox.** Un seul `planning.md`. Sa duree est celle de ses paliers successifs. Il se clot quand le dernier palier ouvert est clos au sens de `nadia-prof` (chaque lot engage a un verdict ecrit ou un report ecrit, rejeu d'`ART-CP-10` compris).
- **P2'. P2 est retiree.** *"On ne l'allonge pas pour absorber plus de 8,0"* ne tient plus : la reponse de l'utilisatrice et la section 11.2 la contredisent. Ce que le sprint long achete reste celui que `nadia-prof` dit (11.3, fin) : *n* paliers valent *n* sprints en capacite d'audit, le gain est dans les ceremonies evitees.
- **P3'. Un palier s'ouvre quand le precedent est clos pour toutes ses instances** (condition 2 de `nadia-prof`). Au planning, j'ecris cette regle d'ouverture et, pour chaque palier, les sous-repertoires de scratchpad et les ports par agent, par instance et par palier (condition 4). L'arbitrage prealable de chaque instance pour un palier est ecrit par `nadia-prof` avant le premier lot de ce palier (condition 3).
- **P4'. Le nombre de paliers n'est pas fixe a l'ouverture.** `planning.md` engage le palier 1 seul, dont la composition est a `camille-po` (la repartition de `DEC-0120` A.33 se rouvre a la borne ecrite, A.39). Chaque palier suivant s'ouvre par un amendement date de `planning.md` (precedent : s56, un amendement, heure inscrite avant les worktrees concernes), qui porte sa composition (`camille-po`) et la reference de l'arbitrage de `nadia-prof`. Le sprint s'arrete quand aucun palier n'a plus de lot instruit a engager, ou quand l'utilisatrice l'arrete.
  **Ordre de grandeur, pas un engagement** : 10 paliers a la mediane de 17 h 33 min par sprint de 8,0 (s54, s55, s56) donnent environ 175 h 30 min d'horloge, quatre fois le maximum observe (41 h 38 min). La mediane repose sur trois points et sur un seul sprint a trois instances (s54).
- **P5'. Attente d'horloge, a titre d'information, par palier** : mediane 17 h 33 min, de 7 h 38 min a 41 h 38 min observees. **Seuil d'alerte : 41 h 38 min par palier.** Passe ce seuil avec un lot engage non audite, je le dis a l'utilisatrice le jour meme dans une ligne du dossier du sprint. Le seuil ne clot rien et ne limite rien.
- **P6'. La duree se reevalue a la cloture de chaque palier**, sur les durees effectives mesurees des paliers precedents.

**Ce que je ne sais pas** : aucune mesure en heures par lot ni par palier ; aucune mesure de trois instances sur du francais CE1 ; la part de la fenetre de 23 h 49 min de s56 due aux arrets (`preparation.md` 1.1).

## 4. Questions

| Code | Question | A qui |
|---|---|---|
| `Q3` | De `nadia-prof` (criteres, 11.9) : *"un sprint plus long, je l'accepte comme une suite de paliers de 8,0, chacun audite et clos avant le suivant. Est-ce ce qu'elle veut ?"*. Sans reponse dans le depot (`grep -n "Q3"` sur `DEC-0120` : seule l'occurrence de A.50 et A.51, qui la renvoient a l'utilisatrice) | utilisatrice |
| `Q-S1` | **Forme des ceremonies d'un sprint a paliers.** `nadia-prof` note que le sprint long perd la review et la retro entre deux fenetres, et que sa condition 3 ne remplace que sa part de la cloture (11.3, fin). Ma proposition : une seule `review.md` et une seule `retro.md` a la cloture du sprint, plus a chaque cloture de palier un fichier de cloture de palier de `sofia-sm` (verdicts de `nadia-prof`, CI verte avec jobs nommes sur l'arbre fusionne, consomme recompte). Ce fichier est une ceremonie neuve : il demande un amendement a `squad/README.md` section 4, que je ferais au planning. Alternative : un sprint par palier, ce qui ramene a l'organisation de s54 a s56 et ne porte aucun gain de ceremonie | utilisatrice |

## 5. Sequence d'`ADR-0065` : mise a jour de `preparation.md` section 2

Les etapes 2 et 3 sont faites avant l'ouverture : amendement et test dans `7a34366c` (16:49) et `bac0a6d1` (17:40), fusion `729b0d8a` (17:42), tous ancetres de `round-two` (sortie 0). Consequences pour `planning.md` :

- l'etape 1 (ligne 1 du planning avant tout worktree) ne s'applique plus : la story est livree avant le planning. Le planning la porte comme **livree avant l'ouverture, fusion `729b0d8a` a 17:42**, sans ligne engagee ;
- l'etape 4 (amendement date du planning) est remplacee par la citation du SHA et de l'heure dans le planning lui-meme ;
- l'etape 5 reste : le premier worktree d'un lot de A' verifie `git merge-base --is-ancestor 729b0d8a HEAD`, sortie 0, citee dans le rapport du developpeur ;
- le statut de `S57-ADR-0065-DEUX-INSTANCES` (engagee en `DEC-0120` A.2, livree avant le sprint) est a acter par un amendement de `DEC-0120`, dans le meme geste que la fusion : une story qu'un sprint absorbe sans amendement ecrit a la decision d'origine n'est constatee qu'a la cloture (cas du rhabillage du gabarit, s5 et s6). Route a `camille-po`.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee par ce fichier.** Les sections 3 et 5 sont des propositions de `sofia-sm` ; `Q3` et `Q-S1` sont ouvertes ; `DEC-0120` reste `Proposee`.
