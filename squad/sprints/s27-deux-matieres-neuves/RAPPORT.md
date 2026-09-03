# RAPPORT de cloture - s27, deux matieres neuves

## L'increment livre

**recreo ferme la derniere ligne de grapheme du francais CP et ouvre les deux premieres matieres
neuves du produit (questionner le monde, histoire-geographie), avec un correctif de rendu couleur
qui restaure le Done de la monnaie.** Six paquets joues et prononces par `nadia-prof`, aucun refus.
La couverture programme passe de 33/75 (44,0 %) a 35/75 (46,7 %), par deux mouvements distincts :
une fermeture (graphemes) et une restauration (monnaie) - les quatre lignes des deux matieres
neuves **ouvrent**, elles ne ferment rien.

## Toutes les decisions du sprint

| Reference | Titre | Type | Statut |
|---|---|---|---|
| `ADR-0077` | Un garde-fou execute la clause qu'il cite ; la population d'une clause de paquet se calcule sur le dessin | Architecture | Accepte, amende deux fois |
| `ADR-0078` | Aucun titre de jeu ne porte le niveau de son paquet ; le niveau est lu dans le paquet | Architecture | Accepte, amende |
| `ADR-0079` | Une condition de lot se compte sur le lot de matiere, derive des descripteurs | Architecture | Accepte, amende |
| `DEC-0070` | Le titre "Calcul CP" devient "Les petites additions" | Produit | Acceptee et appliquee |
| `DEC-0071` | La matrice de couverture ne porte que deux matieres sur le programme des cycles 2 et 3 | Produit | Proposee |
| `DEC-0072` | Deux arbitrages de l'utilisatrice sur les matieres absentes | Produit | Acceptee |
| `DEC-0073` | `nombreDeManches: 7` ratifie pour les trois paquets de questionner le monde | Produit | Ratifiee |

## Etat des deux Done

**Technique : prononce.** `mvn -B clean verify` vert sur le reacteur complet, `round-two` a
`b51803d`. **1128 tests, 0 echec, 0 erreur** (somme des `Tests run` par module apres `clean`, sans
`-pl`, mesure par moi sur l'arbre fusionne final). Confirme independamment par `yanis-qa` (1125 sur
`fe14ea2`, ecart de 3 tests explique par la fusion posterieure du 14e/15e defaut) et par `lea-dev`
(1128 sur `b51803d`, chiffre verifie dans son propre commit `c9b9c2b`).

**Pedagogique : prononce.** Six verdicts de `nadia-prof` (branche `matieres-absentes`, commit
`430648e`), aucun refus : cinq VALIDE, un VALIDE SOUS RESERVE (`cp-qlm-corps-sens`, reserve levee
depuis par `lea-dev`). La ligne des graphemes ferme - seule fermeture du sprint. La ligne de la
monnaie retourne a `couvert-valide` - une restauration, pas une seconde fermeture. Quatorze lignes
passent `non couvert` -> `en cours` (trois pour questionner le monde, onze pour
histoire-geographie) : **aucune ne ferme.** Ce rapport ne doit pas laisser lire que s27 a couvert
deux matieres neuves - il les a ouvertes.

**Sept conditions restent sans garde executable**, sur les six lots livres : `VOI-3`, `VOI-5`,
`VOI-8` (voix de l'adulte), `COU-2`, `COU-3` (rendu couleur de la monnaie), `HIS-2` (histoire-
geographie), et l'exactitude factuelle des deux matieres neuves. Aucun statut de matrice ne doit
suggerer que ces sept proprietes sont gardees par un test.

## Obstacles resolus

- `DEC-0072` citee mais absente de l'arbre (5e et 6e occurrence de la dette de fichier
  hors-arbre) - close par la fusion de `retours-utilisatrice`.
- `ADR-0077` annoncait un chiffre faux (`POIDS_D_UN_DESSIN_MAXIMUM` a 2685, mesure a 2775) -
  amende par `theo-archi`.
- Test de prediction d'`ADR-0077` obsolete et mecanisme de mutation jamais mordu sur son propre
  resultat - corrige par `theo-archi`, preuve par retrait (17/0).
- 14e defaut : cle de lot d'`ADR-0079` non normalisee, trouve par mutation par `yanis-qa` - corrige
  par `theo-archi` dans les deux garde-fous concernes (preuve par retrait, 9/0 et 7/0).
- Deux recits perimes dans mes propres fichiers de ceremonie, trouves par le
  `relecteur-pedagogique` - corriges par moi.
- Reserve bloquante de `nadia-prof` sur l'item 5 d'histoire-geographie - levee par `marc-dev`.
- Reserve non bloquante de `nadia-prof` sur `cp-qlm-corps-sens` - levee par `lea-dev`.
- `nombreDeManches: 7` sans decision produit ecrite - ratifie par `DEC-0073`.
- Titre "Calcul CP" bloquant sur `S1-8` - `nadia-prof` a retreci sa propre clause (fausse en
  extension sur quatre autres titres deja servis), `theo-archi` a applique.
- **Incident de coordination** : `git reset --hard` involontaire sur la branche de `theo-archi` en
  session concurrente, pendant que je diagnostiquais a tort une corruption. Aucun commit perdu ;
  `theo-archi` a du refaire des modifications non commitees. Regle de coordination posee et
  ecrite dans `squad/README.md` section 10 (voir `retro.md`).

## Obstacles encore ouverts, a porter a s28

- **Sept conditions sans garde executable** (liste ci-dessus) : matiere permanente, pas specifique
  a s27, mais concentree sur les six lots de ce sprint.
- **Une limite declaree, pas une dette** : un paquet qui ecrirait "Histoire" la ou les autres
  ecrivent "Histoire-geographie" formerait son propre lot de matiere, invisible a toute
  comparaison de chaines - vue et nommee par `nadia-prof`, pas corrigee (elle n'est pas
  enumerable par un garde-fou de chaines).
- **Reserve neuve et datee pour s28, non une dette de s27** : la consigne et l'enonce se repetent
  sur les trois paquets a voix (`bandit-manchot-lettre-du-son-cp`, `bandit-manchot-digraphe-du-
  son-cp`, `bandit-manchot-voyelle-du-son-cp`). Tracee dans `docs/backlog/backlog.md`.
- `DEC-0071` reste au statut Proposee.

## Tableau de consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s27` (`squad/sprints/s27-deux-matieres-neuves/RAPPORT-tokens.md`) :

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 283 | 566 | 105 150 | 2 620 940 | 117 256 406 |
| `sofia-sm` | claude-sonnet-5 | 476 | 952 | 143 850 | 4 169 563 | 83 463 587 |
| `theo-archi` | claude-opus-5 | 244 | 488 | 178 619 | 2 494 439 | 58 064 946 |
| `marc-dev` | claude-sonnet-5 | 212 | 424 | 111 864 | 1 130 736 | 64 071 066 |
| `lea-dev` | claude-sonnet-5 | 215 | 430 | 83 439 | 1 115 783 | 58 974 296 |
| `yanis-qa` | claude-sonnet-5 | 161 | 322 | 57 832 | 397 375 | 20 773 477 |
| `relecteur-pedagogique` | claude-sonnet-5 | 251 | 502 | 71 249 | 489 717 | 39 132 330 |
| `nadia-prof` | claude-opus-5 | 182 | 364 | 106 526 | 1 796 400 | 49 257 518 |
| `ines-design` | claude-sonnet-5 | 141 | 282 | 38 089 | 847 953 | 39 013 539 |
| orchestrateur (fil principal) | claude-opus-5 | 104 | 208 | 249 651 | 1 760 899 | 41 937 023 |
| **Total** | | **2269** | **4 538** | **1 146 269** | **16 823 805** | **571 944 188** |

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur.
