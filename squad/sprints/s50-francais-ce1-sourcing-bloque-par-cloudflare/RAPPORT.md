# Rapport de sprint s50 - deux lignes neuves de francais CE1, trois corrections differees, blocage
puis levee de la dette technique `T6` sur l'ensemble de la porte du sprint, premier paquet d'arts
plastiques CP

- **Duree** : 2026-10-03 21:53 -> 2026-10-04 22:39:37 (UTC+02:00), 24 h 46 min.
- **Objectif gele** (`DEC-0108`, Acceptee, amendee deux fois le jour de son ouverture puis le
  2026-10-04 ; `DEC-0109`, Acceptee, amendee a quatre reprises le 2026-10-04) : instance A de
  `nadia-prof` - deux lignes neuves de francais CE1 (`FRA-CE1-02`, `FRA-CE1-16`, 1,0 chacune) et
  trois corrections differees (`GRC-CE1-R1`, `SOU-CE1-R1`, `DIV-CE1-R1`, 0,5 chacune), total
  3,5/5,0 ; instance B dediee, ouverte en cours de sprint - `T6` (`ART-CP-09`/`ART-CP-10` d'arts
  plastiques CP, `ADR-0082`), tarif 2,0. Hors chiffrage : le commit qui livre le corpus d'oeuvres
  deja verifie (accepte comme dette intentionnelle, puis engage).

## L'increment livre, en une phrase

Trois lignes du programme ferment (`FRA-CE1-02` VALIDE, `FRA-CE1-16` VALIDE SOUS RESERVE,
`ART-CP-09` VALIDE au troisieme passage), pendant qu'une dette technique deja nommee (`T6`) s'est
revelee bloquer la porte technique de l'**ensemble** du sprint - pas seulement du chantier qui la
portait - et a ete levee en cours de route par l'engagement du contenu qui manquait. La couverture
passe de **120/182 (65,9 %)** a **123/182 (67,6 %)**.

## Les decisions du sprint

| Decision | Statut | Objet |
|---|---|---|
| `DEC-0108` | Acceptee, amendee le jour de l'ouverture (sections 1, 4) et le 2026-10-04 (section 9) | Ouverture : deux lignes de francais CE1, trois corrections differees, chantier de sourcing accepte avec dette assumee. Amendement : `T6` bloque la porte technique de l'ensemble du sprint |
| `DEC-0109` | Acceptee, amendee quatre fois le 2026-10-04 (sections 9, 10, 11, 12) | Reouverture : `T6` engage en seconde instance, 2,0. O1 retenu (retrait des 17 reproductions hors vivier). Perimetre reduit a `ART-CP-09` seule. L'Arcimboldo reecrit, pas retire. Citation fautive corrigee a la cloture |

Deux arbitrages pedagogiques de `nadia-prof` (pas des `DEC`) actent des regles opposables :
`SOU-CE1-R1` (forme de distracteur `a - 10 x b`) et l'arbitrage prealable de `S50-ART`
(`ART-CP-10` refusee en texte). Detail complet : `review.md`, sections 4 et 6.

## Etat des deux Done

### Technique - rouge sur une dette assumee, puis vert, confirme a deux reprises

`yanis-qa` mesure d'abord (commit `06508d48`) trois jobs verts et `build` rouge sur exactement les
deux echecs de la dette `T6` assumee par `DEC-0108` - et constate que cette dette bloque le Done
technique de l'ensemble du sprint. Apres l'engagement de `T6` et la livraison du paquet
`cp-decrire-une-oeuvre`, `yanis-qa` mesure de nouveau (commit `bfb8e3c4`) : **les quatre jobs sont
verts, `BUILD SUCCESS`, 2004 tests, 0 echec**. `theo-archi` confirme sur le meme arbre final
(`1ca58eb1`) : 214 tests ArchUnit verts, 36/35 regles inchangees, aucune violation de Clean
Architecture. Detail : `review.md`, sections 2 et 3.

### Pedagogique - six verdicts, deux episodes de refus suivi de correction

| Ligne / objet | Verdict final | Episode notable |
|---|---|---|
| `FRA-CE1-02` | VALIDE, sans reserve | REFUSE au premier passage (`ç` absent, indices de `s` faux), leve au second passage le meme jour |
| `FRA-CE1-16` | VALIDE SOUS RESERVE (`HOM-CE1-R1`) | prenoms d'origines non variees, non bloquant |
| `GRC-CE1-R1` (reserve, `FRA-CE1-01`) | Levee | aucun statut de ligne ne bouge |
| `DIV-CE1-R1` (reserve, `MAT-CE1-03`) | Levee | aucun statut de ligne ne bouge |
| `SOU-CE1-R1` (reserve, `MAT-CE1-03`) | Levee | correctif initial infaisable techniquement, nouvelle forme admise par arbitrage |
| `ART-CP-09` | VALIDE, sans reserve, **troisieme passage** | REFUSE deux fois ; le second refus portait sur une **erreur de lecture de `nadia-prof` elle-meme** (le visage d'une oeuvre, pretendu fait de fruits, est en realite un tronc d'arbre), qu'elle trouve et corrige elle-meme |

**Zero refus final.** Detail complet, y compris les motifs et leur levee mot pour mot :
`review.md`, section 5.

## Couverture du programme

**120/182 (65,9 %)** a l'ouverture -> **123/182 (67,6 %)** a la cloture, verifiee sur
`squad/programme/MATRICE-couverture.md` (commit `01957d9a`). `ART-CP-10` reste `non couvert` :
elle exige une mecanique de vue simultanee, hors perimetre de `T6`.

## Obstacles resolus et encore ouverts

**Treize obstacles constates, tableau complet dans `review.md`, section 6.** Les plus notables :

- **La dette technique `T6`, deja nommee, decouverte comme bloquant la porte du sprint entier** -
  resolue non par un scoping de la dette en CI (question routee, jamais tranchee en ce sens), mais
  par l'engagement effectif de `T6` et la livraison du contenu qui manquait.
- **Un defaut de poids trouve par `theo-archi` sur son propre commit**, avant qu'il ne coute plus
  cher - corrige par lui-meme dans le meme mouvement.
- **Une lecture erronee d'un garde-fou** ("au moins un identifiant" au lieu de "chaque
  reproduction") - trouvee par `theo-archi`, corrigee par `camille-po` (retrait de 17 reproductions
  hors vivier, restaurables a l'octet depuis l'historique Git).
- **Une erreur de lecture de `nadia-prof` elle-meme**, trouvee et corrigee par elle-meme, puis
  signalee deux fois pour que la `DEC` qui la citait soit corrigee a son tour - faite a la
  cloture.
- **Un commit (`56b6b77c`) porte le prefixe `[THEO]` a tort** : execute par la session principale
  avec l'utilisatrice, pas par l'agent `theo-archi`. Ouvert, constate, non corrige par une
  reecriture d'historique.

**Encore ouvert** : `ART-CP-10` reste `non couvert`, hors perimetre chiffre de s50 (necessite une
mecanique neuve et un `ADR` prealable).

## Prochain perimetre

**Non propose par cette cloture** : role de `camille-po`. Candidates disponibles, non engagees :
les 22 autres lignes du vivier francais CE1, `ART-CP-10` (si une mecanique de vue simultanee est
instruite), la story de `HOM-CE1-R1`.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s50`, fenetre 2026-10-03 19:53 -> 2026-10-04 20:39
UTC (horodatage de debut du planning, complete par amendement de cloture, jusqu'au dernier commit
du sprint). Detail complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 481 | 962 | 222 638 | 2 409 589 | 51 356 251 |
| `sofia-sm` | claude-sonnet-5 | 3 | 6 | 1 463 | 1 442 | 369 203 |
| `theo-archi` | claude-opus-5-5 | 389 | 784 | 150 297 | 2 306 170 | 48 686 313 |
| `marc-dev` | claude-sonnet-5 | 886 | 1 772 | 396 243 | 5 680 625 | 177 024 548 |
| `lea-dev` | claude-sonnet-5 | 334 | 668 | 107 096 | 1 792 608 | 27 305 940 |
| `yanis-qa` | claude-sonnet-5 | 165 | 330 | 52 500 | 754 103 | 10 508 269 |
| `relecteur-pedagogique` | claude-sonnet-5 | 120 | 240 | 28 811 | 411 627 | 13 287 898 |
| `nadia-prof` | claude-opus-5-5 | 598 | 1 196 | 252 372 | 1 773 488 | 52 876 391 |
| orchestrateur (fil principal) | claude-sonnet-5 | 276 | 552 | 377 953 | 2 393 506 | 79 202 550 |
| **Total** | | **3252** | **6 510** | **1 589 373** | **17 523 158** | **460 617 363** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait
du sur ce sprint (perimetre entierement textuel et de contenu illustre existant). Les quatre
compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un
classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur.
