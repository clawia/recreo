# [Sprint s17] Rapport de sprint - francais CM1 non couvert et la dette

- **Debut** : 2026-08-19 18:25 +0200 (`planning.md`)
- **Fin** : 2026-08-21 19:41 +0200 (`review.md`)

## Increment livre

**Un enfant qui navigue au clavier voit desormais quel bouton est selectionne, sur les huit familles
de pressables du produit (`S17-1`, VALIDE SOUS RESERVE) ; deux dettes de garde-fou sont fermees avec
preuve de morsure (duree du marqueur de partie, fraicheur des classes compilees) ; et trois refus ou
sorties pedagogiques successifs (comprehension CM1 sortie du perimetre avant tout code, synonymes CM1
refuses deux fois sur un rendu, homophones maintenus hors du catalogue jouable) laissent la couverture
du programme inchangee - le quatrieme sprint consecutif sans mouvement reel, ecrit comme un changement
de direction plutot que tu.** Dix-neuf fusions de lot, un sprint sans planning fige des le troisieme
jour au sens strict (deux stories refusees, une rouverte, une troisieme differee en cours de route).

## Decisions du sprint

Chaque `ADR` et chaque `DEC` cree ou modifie dans la fenetre du sprint, verifie sur
`squad/decisions/adr/ADR-index.md` et `squad/decisions/produit/DEC-index.md`, pas recopie d'une
liste anterieure.

| Reference | Titre | Statut ce sprint |
|---|---|---|
| DEC-0038 | Trois branches locales non fusionnees (s10-camille-po, s11-ines-confirmation, s13-nadia-audit), verifiees integralement recapturees dans `round-two` sous d'autres hashes | **Creee**, Acceptee |
| DEC-0039 | Perimetre du sprint 17 : francais CM1 non couvert et la dette | **Creee**, Proposee, **amendee sept fois** : arbitrages prealables rendus (1) ; `S17-2` sort du perimetre avant tout code (2) ; `S17-C` differee, `S17-1` ferme VALIDE SOUS RESERVE (3) ; mandat cabinet/score ferme, `camille-po` proprietaire (4) ; `S17-3` REFUSE une premiere fois (5) ; item pilote tient, seize items vises (6) ; `S17-3` REFUSE une seconde fois, mandat rouvert borne a la disposition des propositions, `DEC-0037` ecrite comme changement de direction (7) |
| DEC-0024 | "Le bon petit mot" quitte le catalogue jouable | Acceptee en s10 ; **note du 2026-08-19** (chiffre du point 5 corrige a 24/24 au lieu de 22/24) ; **amendement du 2026-08-19** (l'entree de catalogue reste hors du jeu jouable tant que `BPM-1` et un Done complet manquent) |
| DEC-0037 | Perimetre du sprint 16, engagement de cloture pour s17 | Proposee en s16 ; **note du 2026-08-19** (chiffre de couverture programme corrige, 9/5/32/46 et non 23/5/46/74) ; **critere tranche le 2026-08-21** (dans `DEC-0039`, septieme amendement) : engagement non tenu en substance |
| ADR-0049 | Une matiere s'ecrit accentuee dans la donnee de catalogue | **Creee**, Accepte, sa regle ecrite dans le meme commit (`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`, 3 tests dont la morsure) ; ferme `DEC-0032` point 2 |
| ADR-0050 | Un texte-support est un champ de contenu qui rejoint le plancher et l'identite d'ecran | **Creee**, Accepte, ses deux regles ecrites dans le meme commit (`TailleDesTextesEtBoutonsTest`, `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest`, 5 methodes dont 3 morsures) |
| ADR-0051 | Un garde-fou qui n'examine aucun de ses objets le declare | **Creee**, Accepte, sa regle ecrite dans le meme commit (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, une clause, quatre morsures) |
| ADR-0052 | La part invariante d'un enonce vit dans la consigne du catalogue | **Creee**, Accepte, sa regle ecrite dans le meme commit (`ToutEcranEchangeEstUnDocumentJouableTest`, une clause, deux morsures) ; **amendee le 2026-08-20** (illustration de la marque neutralisee, `SYN-6`) ; **amendee une seconde fois le 2026-08-20** (regle etendue au materiau servi, population derivee du catalogue reel, quatre morsures) |
| ADR-0053 | Une declaration qui suit la position survit a tout etat interactif | **Creee**, Accepte, sa regle ecrite dans le meme commit (`UneCouleurDePositionSurvitATousLesEtatsTest`, une clause, deux morsures et un cas vert) ; ferme le trou maison-contre-maison laisse ouvert par l'amendement 3 d'`ADR-0046` |
| ADR-0046 | Un etat interactif tiers oblige la feuille maison a le declarer | Accepte en s15, amende en s16 ; **amende une troisieme fois le 2026-08-19 (s17)** : la derivation compare desormais une specificite reelle, pas un proxy declaratif ; deux dettes d'ancetre nommees, non fermees |

**Neuf references touchees ce sprint** (une `DEC` creee, six amendements a `DEC-0039`, deux `DEC`
anterieures amendees par note/amendement, cinq `ADR` creees et acceptees dont une amendee deux fois
de plus, un troisieme amendement a une `ADR` anterieure). Aucune section vide.

**Hors `ADR`/`DEC` mais du meme ordre de gouvernance** : la table de correspondance des jetons
`SYN-1` a `SYN-10` (`FICHE-bandit-manchot-synonymes-cm1.md`), ecrite le 2026-08-21 sur signalement du
`relecteur-pedagogique` - recidive du meme defaut trouve en s16 sur les jetons `BARRE`/`PORTE`/`S15-<n>`.
Elle ne renomme rien et ne casse donc aucune des 35 references portees par les cinq fichiers de code
qui citent ces identifiants.

## Etat des deux Done

| Done | Juge par | Etat |
|---|---|---|
| Technique | CI (`mvn -B clean verify`) | **Vert, rejoue independamment par moi sur l'arbre fusionne final, `round-two` a `a57f972`.** BUILD SUCCESS, six modules, **594 tests** (0 echec, deux methodes de comptage concordantes), **29 regles ArchUnit** (28 `@ArchTest` + 1 `ArchRule` verifiee par `.check()`), garde-fou de chemins et de prefixes en code de sortie 0. **Aucune confirmation ArchUnit dediee de `theo-archi` n'existe pour s17** : je le nomme plutot que de la fabriquer ou de recycler celle d'un sprint precedent. Le rapport qualite de `yanis-qa` existe mais **couvre une portee partielle** (mesure a mi-sprint, `150c428`, avant les trois derniers `ADR`). |
| Pedagogique | nadia-prof | **Quatre verdicts.** `S17-1` (focus clavier) : **VALIDE SOUS RESERVE** (`S16-A` FERMEE, `S17-A` FERMEE, `S17-B` non bloquante, `S17-C` differee, `S17-D` corrige). `S17-2` (comprehension) : sortie du perimetre avant tout code, aucun Done attendu. `S17-3` (synonymes) : **REFUSE**, deux fois, dernier verdict le 2026-08-21 (`SYN-5` non tenue, 7 items sur 16 sous le pli). `S17-4` (homophones) : **VALIDE SOUS RESERVE** sur `BPM-2`/`BPM-3`/`BPM-4`, reserve des indices FERMEE (0/24), entree de catalogue tenue hors du jeu jouable (`BPM-1` manquante). |

## Obstacles

**Resolus ce sprint** :

| Description | Agent route | Decision | Clos le |
|---|---|---|---|
| Conflit annonce trois fois sur `FICHE-barre-entree-niveau.md` | sofia-sm | Fusion propre a chaque occurrence reelle, correction datee deja bien formee par `nadia-prof` | 2026-08-20 |
| Quatre conflits reels sur `DEC-0039`/`DEC-index.md` (amendements successifs de `camille-po`) | sofia-sm | Concatenation dans l'ordre chronologique, jamais d'ecrasement | 2026-08-19 a 2026-08-21 |
| Garde-fou de rang vide en silence sur du texte | theo-archi | `ADR-0051` : declaration executable, quatre morsures | 2026-08-19 |
| Regle de graphie muette sur les blancs | theo-archi | Amendement d'`ADR-0049` : espace insecable comptee | 2026-08-19 |
| `ADR-0052` non exercee sur le materiau reel (jeu fictif seul teste) | theo-archi | Second amendement : population derivee du catalogue reel, quatre morsures | 2026-08-20 |
| `S17-A` (contraste de la puce active) | ines-design, marc-dev | Forme proposee, implementee, remesuree FERMEE | 2026-08-21 |
| `S15-8` defaite par le correctif de `S17-D` | marc-dev | Reparee sans reperdre `S17-D` | 2026-08-21 |
| Reserve sur les indices d'homophones (22/24 puis 24/24) | nadia-prof, lea-dev | FERMEE, 0/24 | 2026-08-21 |
| Trois occurrences de "22 sur 24" sans renvoi | nadia-prof | Cinq emplacements corriges par notes datees | 2026-08-20 |
| Tableau de bord homophones perime, table SYN absente, reconciliation `ADR-0053` | nadia-prof | Traites dans un seul lot de mise en coherence | 2026-08-21 |

**Ouverts a l'entree de s18** :

| Description | Agent route | Ce qui manque pour clore |
|---|---|---|
| Dette d'ancetre partagee par deux garde-fous, en sens opposes | theo-archi | Deux declencheurs nommes par `ADR-0053`, non traites |
| Faux rouge symetrique de la dette 8.2 | theo-archi | Conserve et documente comme non atteignable sans supprimer la compilation |
| Deux etats perimes dans `FICHE-bandit-manchot-synonymes-cm1.md` ("20 items", "Statut : en construction") | camille-po | Signales par `nadia-prof`, un des trois traite, deux non |
| `S17-C` (tabindex a la revelation) | theo-archi | Mecanisme du correctif JS et de son garde-fou, story au backlog |
| Mandat sur la disposition des propositions de l'ecran de manche partage | ines-design, theo-archi | Affectation quand les deux sont disponibles, pas ce sprint |
| Story de migration matiere (`ADR-0049`) | theo-archi | Execution non affectee a un sprint |
| `S17-B` (epaississement de bordure) | - | Non bloquante, seuil de bascule deja ecrit |
| Anneau de focus par defaut du navigateur visible sur toute manche fraiche | theo-archi | Verse au dossier `S17-C` |

## Couverture programme

**Quatrieme sprint consecutif sans mouvement reel de `squad/programme/MATRICE-couverture.md`** : 9
lignes `couvert-valide`, 6 `en cours`, 31 `non couvert`, sur 46 lignes de competence au total. Une
seule ligne de plus passe `en cours` depuis s13 (synonymes, francais CM1), sans jamais produire
d'increment jouable ce sprint. **Le critere pose a la cloture de s16 (`DEC-0037`) est tranche** :
l'entree de catalogue de `S17-3` existe, `SYN-5` est refusee - le second terme manque, l'engagement
n'est pas tenu en substance. **Ecrit comme un changement de direction**, apres les trois sprints de
stabilisation deja nommes comme tels (s14, s15, s16), plutot que reconduit sur l'espoir d'un sprint
suivant. Le mandat ouvert sur la disposition des propositions (`DEC-0039`, septieme amendement) est
ce qui pourrait, un jour, fermer `SYN-5` sur `S17-3` ; il n'est pas cense produire d'increment avant
la cloture de s18.

## Consommation de tokens

Genere par `python outils/rapport-tokens.py --sprint s17`, fenetre 2026-08-19 16:25 a 2026-08-21
17:41 UTC (18:25 a 19:41 +0200) - voir `RAPPORT-tokens.md` pour le detail et
`squad/rapports/JOURNAL-tokens.md` pour la serie complete.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 522 | 1 044 | 211 258 | 7 704 354 | 145 757 294 |
| `sofia-sm` | claude-sonnet-5 | 1 157 | 2 314 | 317 029 | 13 440 734 | 284 511 531 |
| `theo-archi` | claude-opus-5 | 519 | 1 038 | 371 389 | 7 626 922 | 183 920 152 |
| `marc-dev` | claude-sonnet-5 | 429 | 858 | 253 528 | 3 643 571 | 145 295 075 |
| `lea-dev` | claude-sonnet-5 | 526 | 1 052 | 255 340 | 5 608 696 | 199 578 598 |
| `yanis-qa` | claude-sonnet-5 | 154 | 308 | 58 778 | 364 534 | 16 459 933 |
| `relecteur-pedagogique` | claude-sonnet-5 | 341 | 682 | 117 931 | 1 487 529 | 86 783 176 |
| `nadia-prof` | claude-opus-5 | 616 | 1 232 | 424 140 | 12 754 964 | 232 362 514 |
| `ines-design` | claude-sonnet-5 | 108 | 216 | 104 793 | 1 000 442 | 8 648 534 |
| orchestrateur (fil principal) | claude-opus-5 | 282 | 564 | 691 067 | 2 712 526 | 71 444 581 |
| **Total** | | **4 654** | **9 308** | **2 805 253** | **56 344 272** | **1 374 761 388** |

Les quatre compteurs restent separes (voir `squad/README.md`, section 6) : les additionner donnerait
un classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur. Sprint le
plus consommateur du produit a ce jour, en tours comme en tokens, porte par dix-neuf fusions de lot
sur 49 h 16 min.
