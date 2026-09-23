# Rapport de sprint s40 - couverture maximale sur six lignes candidates (EMC, histoire des arts, sciences)

- **Duree** : 2026-09-23 09:05 -> 2026-09-23 14:23:30 (UTC+02:00), 5 h 18 min.
- **Objectif gele** (`planning.md`, `DEC-0093`) : maximiser la couverture du programme en
  instruisant six lignes candidates verifiees `non couvert` (EMC, histoire des arts, sciences),
  dans le respect de `DEC-0089` (CE1 mathematiques seule, CM2 differe).

## L'increment livre, en une phrase

Six lignes du programme ferment a `couvert-valide` : nutrition CM1, regles de la vie collective
CM1, relier une oeuvre a son contexte CM1 (premiere ligne d'histoire des arts du produit), extraire
une information d'un document CM1 (couverture partielle texte et tableau, premier support tableau
du produit), la devise CP (sous reserve), distinguer une regle d'une loi CM1 (apres un refus
pedagogique corrige dans le sprint).

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0093` | Objectif et perimetre de s40 : maximiser la couverture en instruisant six lignes candidates verifiees `non couvert`, dans le respect de `DEC-0089` | **Acceptee** |
| `DEC-0094` | `SCI-CM1-20` exige texte et tableau ensemble pour fermer avec la mention deja arbitree par `nadia-prof` ; le texte seul ne suffit pas, l'`ADR` de support tableau est route a `theo-archi` | **Acceptee** |
| `ADR-0099` | Un document tableau est une composante structuree d'item (`TableauSupport`), rendue en `<table>` a intitules `<th scope="col">`, dont une ligne porte exactement les cellules de son en-tete ; `ADR-0072` etendu au fragment "rangee de tableau". Amende le meme jour : deux garde-fous corriges sur le premier paquet reel a grille | **Accepte** |

Les trois entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/produit/DEC-index.md` et `squad/decisions/adr/ADR-index.md`. Aucune ceremonie de
ce sprint (planning, review, retro) n'a elle-meme cree de `DEC` ni d'`ADR` : les trois ci-dessus
ont ete redigees en cours de sprint, sur des obstacles rencontres, puis enregistrees ici.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juges purs executes, 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1670 tests (1664 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur
le journal complet du dernier lancement de `build` sur `round-two` a `3aad142a`, methode blocs
`[INFO] Results:` par module - recoupe le compte autoritaire de `yanis-qa` sur `652cafc8`.
**ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35, inchange depuis s39, recompte
independamment par moi (occurrences litterales, methode `ADR-0095`/`ADR-0098`/`ADR-0099`) - aucune
regle ArchUnit neuve, les quatre garde-fous d'`ADR-0099` jugent un YAML, un record et un document
HTML servi, qu'ArchUnit ne modelise pas.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par `nadia-prof`

| Ligne | Verdict | Ce qui ferme |
|---|---|---|
| Nutrition CM1 (`SCI-CM1-09`) | **VALIDE, sans reserve** | `couvert-valide` |
| Regles de la vie collective CM1 (`EMC-CM1-04`) | **VALIDE, sans reserve** | `couvert-valide` |
| Relier une oeuvre a son contexte CM1 (`HDA-CM1-05`) | **VALIDE, sans reserve** | `couvert-valide`, premiere ligne d'histoire des arts du produit |
| Extraire une information CM1 (`SCI-CM1-20`) | **VALIDE, sans reserve**, couverture partielle | `couvert-valide`, premier support tableau du produit |
| Devise CP (`EMC-CP-09`) | **VALIDE SOUS RESERVE** (`DVS-CP-R1`) | `couvert-valide` |
| Regle ou loi CM1 (`EMC-CM1-08`) | **REFUSE au premier audit**, **VALIDE au second** apres correction de `marc-dev` | `couvert-valide` |

**Quatre validations sans reserve, une sous reserve non bloquante, une validee apres un cycle
complet de refus et de correction dans le meme sprint - les six lignes ferment.** Le Done
pedagogique est prononce dans six fiches de `squad/conformite/`, citees en detail dans `review.md`,
section 9.

## Couverture du programme

**88/155 (56,8 %)**, en hausse de 3,9 points sur s39 (82/155, 52,9 %). Six fermetures, aucune
ouverture partielle - recompte independant par `camille-po` sur les dix-neuf tableaux de la
matrice, revenifie par moi.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 7). Ni la couverture maximale de s40 ni sa gouvernance de
sprint (`DEC-0093`, `DEC-0094`) ne sont des postes de la composition du Plan 2 (`DEC-0086`/
`DEC-0087`) : verifie par recherche des identifiants de ce sprint dans les deux textes, aucune
occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : l'objectif du sprint, recu hors depot, formalise en `DEC-0093` ; la question
pedagogique de `S40-HDA` (`HIS-2`, lectures multiples d'un contexte d'oeuvre), tranchee par
l'arbitrage prealable de `nadia-prof` (`HDA-1` a `HDA-4`) ; la question de perimetre de
`S40-EXTRACTION` (texte seul ou les deux formes), tranchee par `DEC-0094`, et l'`ADR` de support
tableau qui en decoule (`ADR-0099`) ; un identifiant ecrit sans allocation dans
`REGISTRE-identifiants.md` (`PAR-5`), corrige par `nadia-prof` ; deux garde-fous faux rouges
d'`ADR-0099`, trouves par `lea-dev` sur le premier paquet reel a grille et corriges par
`theo-archi` sans toucher le contenu qui les avait reveles ; un refus pedagogique sur `S40-LOI`,
corrige par `marc-dev` et revalide par `nadia-prof` dans le meme sprint ; une deviation de mon
propre process de routage (worktree ouvert avant que ma propre ceremonie ne soit fusionnee),
constatee par moi-meme et corrigee sans aucun cout avant tout travail en aval.

**Encore ouverts** : `MAT-CE1-18` (sens de la division) toujours sans ligne dans
`squad/programme/MATRICE-couverture.md`, pour le cinquieme sprint consecutif.

**Amendement de process fait** : `squad/README.md` section 9 precise desormais que Sofia verifie
que sa propre ceremonie routant un travail est deja fusionnee sur `round-two` avant d'ouvrir, depuis
son tip, le worktree de l'agent qu'elle route - complement a l'amendement de s39, qui couvrait le
sens inverse de la meme dependance. Detail et motif : `retro.md`, sections 2 et 3.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 6.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s40`, fenetre 2026-09-23 07:05 -> 2026-09-23 12:36
UTC (fenetre ouverte au moment de l'execution du script, avant les derniers commits de cloture).
Detail complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 267 | 534 | 108 493 | 1 134 530 | 28 773 234 |
| `sofia-sm` | claude-sonnet-5 | 97 | 194 | 32 199 | 600 285 | 9 645 599 |
| `theo-archi` | claude-opus-5 | 310 | 620 | 150 591 | 1 439 564 | 48 228 764 |
| `marc-dev` | claude-sonnet-5 | 401 | 802 | 157 199 | 2 618 594 | 115 070 792 |
| `lea-dev` | claude-sonnet-5 | 439 | 878 | 171 505 | 1 869 729 | 129 037 200 |
| `yanis-qa` | claude-sonnet-5 | 110 | 220 | 23 345 | 378 452 | 11 059 510 |
| `relecteur-pedagogique` | claude-sonnet-5 | 119 | 238 | 25 662 | 441 170 | 14 751 409 |
| `nadia-prof` | claude-sonnet-5 | 305 | 610 | 126 155 | 1 229 156 | 29 659 231 |
| orchestrateur (fil principal) | claude-sonnet-5 | 145 | 290 | 98 106 | 143 799 | 48 253 825 |
| **Total** | | **2193** | **4 386** | **893 255** | **9 855 279** | **434 479 564** |

Aucune invocation mesuree sur cette fenetre pour `ines-design` : aucun travail de design n'etait
du sur ce sprint. Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les
additionner donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres
de grandeur.
