# [Sprint s14] Rapport de sprint - une barre d'entree par niveau dans l'en-tete, deux ecrans exclus par choix pedagogique

- **Debut** : 2026-08-17 13:24 (`planning.md`)
- **Fin** : 2026-08-17 19:34 (`review.md`)

## Increment livre

Deux boutons d'entree par niveau (CP, CM1) vivent desormais dans l'en-tete de recreo et filtrent
la liste des jeux d'un appui ; ils sont visibles en permanence sur l'accueil et sur les trois
etats de manche, mais **absents**, par refus pedagogique motive, de l'ecran de confirmation
d'abandon et de l'ecran d'erreur - deux ecrans ou ajouter deux puces creerait plus de reponses que
la question n'en attend. Un appui pendant une partie en cours passe par l'ecran de confirmation
existant plutot que de detruire la partie ; a l'accueil sans partie en cours, l'appui filtre
immediatement. Dans le meme sprint, le lien "Les jeux" de l'en-tete de l'accueil, qui renvoyait
vers l'ecran qui l'affichait deja, est retire (`DEC-0033`). Deux dettes techniques sont fermees en
parallele : le retrait d'un jeu du catalogue retire desormais sa methode de test dans le meme
geste, et le quatrieme axe de rang (position a l'ecran) est mesure par un test de distribution.

Le Done pedagogique a ete **refuse puis valide sous reserve dans la meme journee** : une premiere
version laissait quatre boites pressables sur l'ecran de confirmation au lieu de deux (et trois au
lieu d'une sur l'ecran d'erreur), parce que l'en-tete permanent survivait a ces deux ecrans en
dehors de ce que le fragment seul montrait. Corrigee, remesuree, neuf conditions sur neuf tenues.

## Decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| DEC-0032 | Perimetre du sprint 14 : barre d'entree par niveau, arbitrage prealable, matiere differee | Produit | Acceptee sur le perimetre |
| DEC-0033 | BARRE-7 : le lien "Les jeux" de l'en-tete disparait de l'accueil | Produit | Acceptee |
| DEC-0027 | (amendement du 2026-08-17) - PORTE-1 fermee par `DEC-0033` | Produit | Amendee ce sprint |
| ADR-0043 | Un point d'entree permanent porte un troisieme role, le mot unique reste aux sorties | Architecture | Accepte (neuf) |
| ADR-0044 | Le choix des jeux montres vit derriere le port, le point d'entree est une route | Architecture | Accepte (neuf) |
| ADR-0045 | Un critere derive de l'artefact juge n'en juge que la coherence interne | Architecture | Accepte (neuf) |
| ADR-0022 | Un habillage permanent declare son masquage, l'ecran son marqueur | Architecture | Accepte a l'origine (s06) ; **trois amendements ce sprint** - visibilite permanente de S14-1, second amendement de constat apres S14-2, troisieme amendement qui reforme la clause 2 (assertion mal placee identifiee comme cause de toute la cascade) |
| ADR-0039 | Un critere se derive des que la promesse est universelle | Architecture | Accepte a l'origine (s12) ; **amende ce sprint** (`ADR-0045`) - precise de quelle source le calcul de coherence part |
| ADR-0042 | Un arret remplacant est un second predicat orthogonal du destructeur | Architecture | Accepte a l'origine (s13) ; **amende ce sprint** - le predicat n'existe dans aucun fichier Java, capacite non affectee ce sprint (decision de `sofia-sm`) |

**Neuf decisions creees ou modifiees ce sprint** (deux `DEC` neuves, un amendement de `DEC`, trois
`ADR` neufs, trois amendements sur deux `ADR` anterieurs). Aucune section vide.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert, rejoue independamment par moi sur l'arbre fusionne final, `round-two` a `a7c7401`.** BUILD SUCCESS, six modules, **510 tests** (0 echec), **29 regles ArchUnit** (deux methodes concordantes), garde-fou de chemins en code de sortie 0 (CI et auto-test). `rapport-qualite.md` (`yanis-qa`) et `confirmation-archunit.md` (`theo-archi`) s'arretent tous deux avant le correctif de masquage et le verdict final : c'est ma propre reverification sur la tete reellement fusionnee qui atteste ce Done, voir `review.md`. |
| Pedagogique | nadia-prof | **VALIDE SOUS RESERVE sur S14-1 et S14-2**, second verdict du 2026-08-17 apres un premier refus le meme jour. Neuf conditions sur neuf tenues (BARRE-1 a BARRE-9), remesurees une par une sur l'increment corrige. Deux reserves neuves non bloquantes (S14-A, S14-B). R-BARRE-3 maintenue non bloquante, avec une condition de bascule datee au sprint qui ouvrira le filtre par matiere. |

## Obstacles

**Resolus** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| BARRE-1/BARRE-2 declarees "tenues par construction" sur le fragment, fausses sur le document assemble - quatre boites pressables sur la confirmation au lieu de deux, trois sur l'erreur au lieu d'une | theo-archi (arbitrage de forme), marc-dev (correctif) | marqueur `ecran-exclusif` neuf, trois clauses A/B/C dans `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` | 2026-08-17 |
| Une assertion du garde-fou d'`ADR-0022` (`masquages(...).isNotEmpty()`) exigeait son propre mecanisme et a produit toute la cascade du sprint | theo-archi | troisieme amendement d'`ADR-0022`, clause 2 reformee plutot qu'amendee une troisieme fois par constat | 2026-08-17 |
| Sort de BARRE-7 (le lien "Les jeux" de l'accueil renvoie vers l'ecran qui l'affiche deja) | camille-po | `DEC-0033` - le lien est retire plutot que remplace | 2026-08-17 |
| Le compte de tests autoritaire (506 vs 507) sur `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` | theo-archi | corrige contre son propre chiffre - `ADR-0013` obligation 3 exige que le jeu fautif soit execute, pas seulement present | 2026-08-17 |
| Deux dettes techniques routees par `DEC-0032` (methodes de test fragiles sur cinq paquets, quatrieme axe de rang non mesure) | theo-archi (avis de faisabilite), lea-dev et sofia-sm (implementation) | clause unique derivee du catalogue ; test de distribution sur 30 000 tirages a graine declaree | 2026-08-17 |

**Encore ouverts** :

| Description | Agent route | Depuis | Ce qui manque |
|---|---|---|---|
| `rapport-qualite.md` et `confirmation-archunit.md` s'arretent avant le correctif de masquage et le verdict final de `nadia-prof` | yanis-qa, theo-archi | 2026-08-17 (constate a cette review) | une republication sur l'arbre final, ou une note explicite sur la methode retenue a la place |
| `relecture.md` s'arrete avant le correctif de masquage et le verdict final (243 lignes neuves sur la fiche de conformite) | relecteur-pedagogique | 2026-08-17 (constate a cette review) | une relecture formelle du delta - mon propre filet de securite mecanique ne couvre que les motifs de forme, pas la coherence terminologique |
| S14-A : bande sombre vide de 16 px sur les deux ecrans exclusifs | camille-po | 2026-08-17 | story candidate, non bloquante |
| S14-B : aucune puce ne se distingue jamais, aucun filtre nomme a l'ecran | camille-po | 2026-08-17 | story candidate, non bloquante ; devient bloquante au sprint du filtre par matiere |
| R-BARRE-1 (reserve J, grands nombres CM1) : +13 px mesures | camille-po | 2026-08-17 | story candidate sur la fiche des grands nombres |
| R-BARRE-4 : F11 s'enrichit de deux puces sur deux ecrans sur cinq | aucun agent - hors de la portee de la squad | 2026-08-17 | ecoute reelle au lecteur d'ecran, hors squad |
| Nomenclature de la matiere accentuee, avis consultatif de `theo-archi` non tranche | camille-po (choix), nadia-prof (graphie) | 2026-08-17 | decision avant que s15 n'instruise le filtre par matiere - `nadia-prof` demande sa fermeture avant s15 |
| Residu de 11 mutations sur 121 non couvertes par un automate (`ADR-0045`) | nadia-prof | 2026-08-17 | rien - pris explicitement sous deux clauses verifiables N-1 et N-2, ecrites dans la fiche |
| Garde-fou de provenance des classes amont (`-am`) | theo-archi | 2026-08-17 | mecanisme, non affecte |
| Migration du marqueur `ecran-de-jeu` vers un attribut | theo-archi (mecanisme), sofia-sm (capacite) | s06, renomme ce sprint | capacite non affectee |
| Resolution de navigateur d'`outils/mesurer-rendu.sh` (facteur de bus sur la mesure de rendu) | sofia-sm (capacite) | 2026-08-17 | capacite non affectee |
| Nom du marqueur `ecran-exclusif` a soumettre a `ines-design` | ines-design | 2026-08-17 | avis de design, non affecte |
| Predicat `estRemplacant` d'`ADR-0042` toujours sans objet en code | theo-archi (dette actee) | s13 | capacite, declenchee par le premier lot qui rend un arret remplacant sur un ecran a partie en cours |
| Reserve F11 (ecoute reelle au lecteur d'ecran) | aucun agent - hors de la portee de la squad | s13 | ecoute reelle, hors squad |

## Couverture programme

**Aucune ligne de `squad/programme/MATRICE-couverture.md` n'est ouverte ni fermee par ce sprint.**
C'est un choix acte au planning : S14-1 et S14-2 sont une story de navigation transverse, sans
ligne de programme, et `camille-po` n'a affecte aucune ouverture de contenu ce sprint. Ce n'est pas
une omission - le plafond de trois ouvertures par sprint (`DEC-0030`) est un maximum, pas un
minimum.

## Consommation de tokens

```
python outils/rapport-tokens.py --sprint s14
```

produit `squad/sprints/s14-points-d-entree-par-niveau/RAPPORT-tokens.md` et met a jour
`squad/rapports/JOURNAL-tokens.md`. Fenetre mesuree : 2026-08-17 11:24 a 2026-08-17 17:34 UTC.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 55 | 20 402 | 33 982 | 287 416 | 4 326 148 |
| `sofia-sm` | claude-sonnet-5 | 469 | 184 186 | 172 852 | 1 826 675 | 34 720 920 |
| `theo-archi` | claude-opus-5 | 213 | 56 976 | 178 196 | 4 034 175 | 50 286 479 |
| `marc-dev` | claude-sonnet-5 | 473 | 55 522 | 269 332 | 6 441 060 | 217 080 896 |
| `lea-dev` | claude-sonnet-5 | 197 | 20 686 | 62 383 | 397 541 | 25 178 534 |
| `yanis-qa` | claude-sonnet-5 | 204 | 408 | 45 069 | 428 385 | 25 670 873 |
| `relecteur-pedagogique` | claude-sonnet-5 | 171 | 20 634 | 44 864 | 579 059 | 29 588 674 |
| `nadia-prof` | claude-opus-5 | 106 | 212 | 65 002 | 1 433 401 | 33 334 063 |
| orchestrateur (fil principal) | claude-opus-5 | 61 | 114 | 199 551 | 298 828 | 11 894 426 |
| **Total** | | **1949** | **359 140** | **1 071 231** | **15 726 540** | **432 081 013** |

**Aucune invocation mesuree sur cette fenetre pour `ines-design`** - elle n'a pas ete sollicitee
ce sprint apres sa proposition initiale (arbitree en amont du planning).

Detail complet, y compris la fenetre "hors sprint", dans `RAPPORT-tokens.md` et
`squad/rapports/JOURNAL-tokens.md`.
