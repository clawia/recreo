# Proposition pour s55 - a `camille-po` et a l'utilisatrice

**Ce fichier n'est pas le planning de s55.** Il n'a pas d'horodatage de debut, n'affecte personne, n'ouvre aucun worktree et n'engage
aucun poste. Le planning de s55 s'ecrira apres la `DEC` d'ouverture de s55 (`DEC-0114` A.20, `squad/README.md` section 4). Ce qui suit
est une lecture du depot a la cloture de s54 et une composition possible, que `camille-po`, `nadia-prof` et l'utilisatrice jugent.

- **Auteur** : `sofia-sm`, casquette Scrum Master, 2026-10-08.
- **Ordre retenu**, relaye par `DEC-0114` A.3 (forme 3 de `DEC-0062` : l'ordre, pas la raison) : `G1`/`G2`, puis `G6`/`G5`, puis arbitrage
  de `nadia-prof` sur `G7` a `G11`, puis `G3`/`G4`. `G8` d'abord est ecartee. `DEC-0114` reste `Proposee` pour cette raison.

## 1. Ce qui reste, mesure a `HEAD`

Base : les 45 paquets de la session de `planning.md` de s54 (groupes `G1` a `G11`), moins les six branches a s54. Comptes d'items :
`planning.md`, tableau des groupes.

| Groupe | Reste | Dont | Ferme une ligne | Source |
|---|---|---|---|---|
| `G1` + `G2` francais CE1 | **13 paquets** | `G1` : `accord-groupe-nominal`, `accord-sujet-verbe`, `classes-de-mots`, `familles-de-mots`, `inference`, `negation`, `phrases-types`, `pronoms`, `texte-documentaire`, `texte-narratif`, `verbe-sujet` ; `G2` (DEGRADE) : `ordre-des-mots`, `relire-une-phrase` | oui, 13 lignes | `planning.md` s54 ; `futur` et `passe-compose` sont faits |
| `G6` sciences CM1 | **0** | `SCI-CM1-15` et `-18` fermees a s54 | - | section 2 de `review.md` |
| `G5` EMC | **3 paquets** | `cm1-emc-respect-differences` (`EMC-CM1-03`), `cm1-emc-principes-republique` (`EMC-CM1-06`, six points a jouer a l'ecran), `cp-emc-personnes-differentes` (`EMC-CP-04`, **sous refus**, `DEC-0081` section 7) | 2 oui, 1 non | `planning.md` s54 ; `atteinte-autrui` est fait |
| `G7` a `G11` | **16 paquets**, 216 items | anglais CM1 (2), histoire des arts et arts CM1 (7), histoire-geographie CM1 (2), questionner le monde CP (3), autres (2 : `cp-assemblage-syllabique`, `cm1-lire-et-restituer`) ; `cp-arts-oeuvres-familles` est branche | **aucun plafond ecrit** pour un paquet `DEGRADE` texte seul | `planning.md` s54 ; `arbitrage-nadia-c.md` section 1 (plafond d'`ART-CP-10` seul) |
| `G3` + `G4` | **7 paquets** | francais CE1 plafonne `en cours` (2), mathematiques CE1 (5) | non | `planning.md` s54 |

Total : 13 + 3 + 16 + 7 = **39**, soit 45 - 6. Deux fichiers de `contenu/` de plus (les paquets refuses a s51) restent sans entree de catalogue
(`review.md` obstacle 13).

**Le chemin critique reste le francais CE1** : 13 lots, une instance (`PAR-2`), trois lots au plus par instance (`PAR-8`) : **5 sprints a trois
lots** (s55 a s59) ou **7 a deux lots** (planning de s54, "Ce qui est reporte"). Le decoupage en deux instances (`DEC-0114` section 7.2, groupes `X` et `Y`)
ramenerait ce chiffre a 3 sprints selon `camille-po`, **mais il exige l'accord de `nadia-prof` sur `PAR-2` et l'avis de `theo-archi` sur `ADR-0065`**, et la `DEC`
dit qu'il *"ne vaut qu'a partir du sprint ou le groupe porte par la premiere instance a deja rencontre du contenu"*. Non engage ici.

## 2. Mesures de s54 qui pesent sur la composition

| Mesure | Valeur | Source |
|---|---|---|
| Consommation reelle des budgets | A **3,75**, B **3,25**, C **1,0** : somme **8,0** | `DEC-0114` A.26 |
| Part de la marge de A consommee par des complements de cloture | 0,5 sur 0,5 (rejeu `PCO-CE1-R1` 0,25, `EXC-R15` 0,25) ; *"aucun refus n'a ete paye"* | `ARBITRAGE-PREALABLE-s54-instance-a-francais-ce1.md`, complements de cloture |
| Refus au premier passage | **0 sur 6 audits** de s54 (aucun REFUSE) ; s52 : 1 sur 6 ; s51 B : 2 sur 3 | fiches de s54 ; `planning.md` s54 ("Taux de refus") |
| Prix d'un lot | 1,0 ; arbitrage prealable 0,5 ; marge de rejeu nommee 0,5 | arbitrages prealables de s54 |
| `PAR-13` pour le francais CE1 | **tenue** a s54 : `FUT` et `PCO` sont cousines de `PRS` ; **non lue pour `RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM`**, que s52 section 5 disait neuves | arbitrage prealable de A, section 3 ; `DEC-0114` section 7.2 |
| Borne de `PAR-9` | **8,0**, comptee telle qu'ecrite ; le 9,5 de s54 *"n'est pas une jurisprudence"* | `DEC-0114` A.20 |

Lecture, non decision : un refus au premier passage coute 0,5 de rejeu et la marge de A a deja ete depensee a s54 en complements. A trois lots,
A est a **4,0 sur 4,0 sans reste** (3,0 + 0,5 d'arbitrage + 0,5 de marge) : s54 l'a laisse a 3,75.

## 3. Composition possible, par ordre

Les prix sont ceux du bareme ; `nadia-prof` ecrit les siens dans son arbitrage prealable (`Q3`). **Rien n'est engage.**

### Rang 1 : `G1`/`G2`, instance A (francais CE1)

| Option | Lots | Total propose | Lots restants apres s55 | Ce que l'option coute |
|---|---|---|---|---|
| **O1** | 3 lots | 3,0 + 0,5 + 0,5 = **4,0 / 4,0**, sans reste | 10 (4 sprints a trois lots au plus : s56 a s59) | une marge qui ne paie qu'un rejeu ; `EXC-R15` (0,25) la consommerait |
| **O2** | 2 lots | 2,0 + 0,5 + 0,5 = **3,0 / 4,0**, 1,0 de reste | 11 | un lot de moins au chemin critique |

**Candidats**, selon les criteres que le planning de s54 a appliques (fermable, non `DEGRADE`, non touche par s53, terrain connu ou cousin d'un
terrain valide) : `accord-sujet-verbe`, `classes-de-mots`, `familles-de-mots`, `inference`, `negation`, `phrases-types`, `texte-narratif`,
`verbe-sujet` (8 paquets, auteurs lus par `git log --diff-filter=A` au planning de s54). **Je ne choisis pas les trois** : la lecture de `PAR-13` sur
`RCT`, `PRN`, `INF`, `DOC`, `OMP`, `RLP`, `FAM` est a `nadia-prof`, et elle commande l'ordre. **Contrainte d'ordre ecrite** : `relire-une-phrase`
(`-09`) renvoie a `accord-sujet-verbe` (`-14`) et `accord-groupe-nominal` (`-15`) (`RLP-CE1-1`, `DEC-0114` section 7.2) : ces deux-la avant elle.
`accord-groupe-nominal`, `pronoms`, `texte-documentaire` sont des paquets corriges par s53 (P1, P2, P8) : a lire a leur tour, sans ordre propose.

### Rang 2 : `G6`/`G5`, instance B (EMC CM1)

`G6` est faite. Reste `cm1-emc-respect-differences` (lot 1,0, `P9` accepte sans changement a s53) puis `cm1-emc-principes-republique` (lot 1,0, *"la ligne la
plus dangereuse de la matiere"* selon la matrice, six points que `nadia-prof` ne juge qu'en jouant). `cp-emc-personnes-differentes` reste sous refus :
**pas un lot**. Total propose B : 2,0 + 0,5 + 0,5 = **3,0 / 4,0**, 1,0 de reste. `EXC-R15` (EMC CP, meme matiere que l'EMC CM1 selon `PAR-2`
tel que le planning de s54 le lit) tient dans ce reste a 0,25.

### Rang 3 : arbitrage de `nadia-prof` sur `G7` a `G11`

Seize paquets, 216 items, **aucun plafond ecrit** ; l'arbitrage precede tout lot (`planning.md` de s54 ; `DEC-0114` A.3 point 2). Son prix est a elle : le seul
repere du depot est 0,5 pour 51 items lus (A, s54) et 0,5 pour quatre paquets lus (B, s54). **Je n'extrapole pas.** Deux places possibles, **a elle de dire** : le reste de B
(1,0), si elle le chiffre a 1,0 au plus, ou s56. Une troisieme instance demanderait son propre ecart (`DEC-0114` A.20).

### Rang 4 : `G3`/`G4`

Aucun lot a s55. `MUL-CE1-R1` se rouvre a la `DEC` du premier sprint qui porte une instance de mathematiques CE1 (`DEC-0114` section 3).

## 4. Stories ouvertes de s54, ou les mettre

Tailles et prix : `review.md` section 12. Aucune n'est engagee ; premier report de chacune : aucun.

| Story | Matiere et niveau | Instance qui la lirait (`PAR-2`) | Prix `nadia-prof` (propose) | Observation |
|---|---|---|---|---|
| `EXC-R15` | EMC CP | B | 0,25 | tient dans le reste de B |
| `S54-ART-RES-R1` a `R3` | arts plastiques CP | **aucune a s55** : l'instance C n'existait que pour s54 (`DEC-0114` A.20) | 0,5 un rejeu | **question a `nadia-prof`** : quelle instance relit une matiere qu'aucune instance de s55 ne porte, sans partager une matiere (`PAR-2`) ? |
| `S54-OBS-SCI-CONSIGNE` | sciences CM1 | B (`backlog`) | non chiffree | criteres a ecrire avant d'engager |
| `S54-PONCT-GN-INDICES` | mathematiques CM1, ponctuation | aucune (0,0) | 0,0 | `lea-dev` ; relecture de ponctuation par `relecteur-pedagogique` |
| `S54-ASCII-APOSTROPHES-TESTS` | transverse | aucune | sans objet | a router a `theo-archi` pour la forme (backlog) |

Passe `PAR-4` : ecrite a s29, s30, s35, s54 (`find squad -iname 'PASSE-TRANSVERSE*'`). **Si une passe est due a s55 est a `nadia-prof`** : pas de regle de periodicite ecrite que j'aie lue dans cette tache.

## 5. Ce que la `DEC` d'ouverture de s55 aura a trancher

| Code | Question | A qui |
|---|---|---|
| Q1 | Ordre des matieres : ses mots en forme 2, ou reprise de l'ordre sous le motif de s55 (ce qui passe `DEC-0114` a `Acceptee`, section 0.4) | utilisatrice, `camille-po` |
| Q2 | `ART-CP-10` : financer la mecanique "deux oeuvres ensemble" (`ADR-0104` `Accepte`, prix en tailles ecrit : `review.md` section 11), et a quel sprint | utilisatrice, `camille-po` |
| Q3 | O1 ou O2 pour l'instance A ; lecture de `PAR-13` sur les sept conditions neuves du francais CE1 | `camille-po`, `nadia-prof` |
| Q4 | Place et prix de l'arbitrage sur `G7` a `G11` | `nadia-prof` |
| Q5 | Instance qui relit les reserves d'`ART-CP-10` (ligne `S54-ART-RES-R1` a `R3` du tableau de la section 4) | `nadia-prof` |
| Q6 | Decoupage du francais CE1 en deux instances (`DEC-0114` section 7.2) : le demander ou non | `camille-po`, `nadia-prof`, `theo-archi` |
| Q7 | Pousser s53 et s54 avant s55, et lecture de la CI sur le sommet pousse | utilisatrice |

## 6. Ce que cette proposition ne fait pas

Elle n'ecrit aucune `DEC`, aucun `ADR`, aucun tarif, aucun plafond ; elle n'engage aucun poste, n'affecte aucun agent, n'ouvre aucun worktree et ne modifie ni le backlog, ni la matrice, ni une fiche.
