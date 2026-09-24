# Rapport de sprint s41 - fusion de deux sprints projetes (enseignement moral et civique, sciences et technologie CM1, questionner le monde CP)

- **Duree** : 2026-09-24 09:15 -> 2026-09-24 12:45:30 (UTC+02:00), 3 h 30 min.
- **Objectif gele** (`DEC-0095`, amendee le 2026-09-23) : fermer, autant que possible, six lignes du
  referentiel sur deux terrains non chevauchants - enseignement moral et civique (CP + CM1) d'une
  part, sciences et technologie CM1 et questionner le monde CP d'autre part - avec deux instances
  paralleles de `nadia-prof`, borne d'audit verifiee et recalculee a 8,0.

## L'increment livre, en une phrase

Six lignes du programme ferment a `couvert-valide` : symboles de la Republique CP, droits de
l'enfant CP (sous reserve), roles de l'ecole et de la commune CP, regles d'hygiene de vie CP, mois
et saisons CP, classement des etres vivants CM1 (couverture partielle) - la couverture du programme
passe de 88/155 (56,8 %) a **94/155 (60,6 %)**.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0095` | Fusion en un seul sprint de ce qui etait propose comme deux (EMC d'une part, sciences CM1 et questionner le monde CP d'autre part) ; borne d'audit de `nadia-prof` verifiee avant tout engagement plutot que supposee ; amendee le meme jour pour passer a deux instances paralleles, borne recalculee a 8,0, perimetre porte a six lignes sur deux terrains non chevauchants | **Acceptee, amendee le 2026-09-23** |

L'entree est verifiee presente sur le disque, avec son statut a jour, dans
`squad/decisions/produit/DEC-index.md`. **Aucun `ADR` n'est cree ni amende ce sprint** - verifie par
diff independant (`git diff --stat` sur `squad/decisions/adr/` entre l'avis de faisabilite et
l'arbre final : vide). Aucune ceremonie de ce sprint (planning, review, retro) n'a elle-meme cree de
`DEC` ni d'`ADR` : `DEC-0095` a ete redigee par `camille-po` avant l'ouverture du planning, sur un
obstacle de gouvernance recu directement comme instruction de tache (forme 3), et est enregistree
ici pour memoire.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juge(s) pur(s) execute(s), 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1701 tests (1695 unitaires + 6 integration), 0 echec, 0 erreur, 0 ignore**, mesures par moi sur le
journal complet du lancement de `build` sur `round-two` a `6417c67f`, methode blocs `[INFO]
Results:` par module - chiffre identique, module par module, a celui de `yanis-qa` et a celui de
`theo-archi` apres sa propre correction d'une erreur de methode qu'il a trouvee et payee lui-meme
(1692 -> 1701, `confirmation-archunit.md`, section 1.1). **ArchUnit** : `static final ArchRule` 36,
`@ArchTest` 35, inchange depuis s40, recompte independamment par moi - **zero ligne de Java de
production sur les six lots**, uniquement du contenu YAML et des tests de paquet.

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par deux instances paralleles de `nadia-prof`

| Ligne | Verdict | Ce qui ferme |
|---|---|---|
| Symboles de la Republique (`EMC-CP-08`) | **REFUSE deux fois** (reponse exacte sanctionnee, puis defaut neuf introduit par un premier correctif insuffisant), **VALIDE** au troisieme audit | `couvert-valide` |
| Droits de l'enfant (`EMC-CP-10`) | **VALIDE SOUS RESERVE** (deux reserves non bloquantes, registre de vocabulaire) | `couvert-valide` |
| Roles de l'ecole et de la commune (`EMC-CP-13`) | **REFUSE** (fait inexact sur le role du directeur), **VALIDE** au second audit | `couvert-valide` |
| Regles d'hygiene de vie (`QLM-CP-04`) | **VALIDE**, sans reserve bloquante | `couvert-valide` |
| Mois et saisons (`QLM-CP-13`) | **VALIDE**, sans reserve bloquante | `couvert-valide` |
| Classer les etres vivants (`SCI-CM1-06`) | **REFUSE** (enonces auto-contradictoires, confusion tetard/grenouille), **VALIDE** au second passage | `couvert-valide`, couverture partielle |

**Trois lots sur six refuses au moins une fois, tous les six finalement valides.** Aucun des trois
refus n'a ete vu par la CI : ce sont des jugements semantiques (une reponse exacte sanctionnee, un
fait inexact, un enonce qui se contredit lui-meme) qu'aucun garde-fou de ce depot ne rend. Le Done
pedagogique est prononce dans six fiches de `squad/conformite/`, citees en detail dans `review.md`,
section 6.

## Couverture du programme

**94/155 (60,6 %)**, en hausse de 3,8 points sur s40 (88/155, 56,8 %). Six fermetures, aucune
ouverture partielle nouvelle au-dela de la mention de couverture partielle deja exigee d'avance sur
`SCI-CM1-06` - verifie par moi sur `squad/programme/MATRICE-couverture.md`, commit `6417c67f`.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 8). Les six lignes de s41 ne sont pas des postes de la
composition du Plan 2 (`DEC-0086`/`DEC-0087`) : verifie par recherche des identifiants de ce sprint
dans les deux textes, aucune occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : l'objectif du sprint, recu hors depot, formalise et amende en `DEC-0095` le jour meme ;
les deux refus sur `EMC-CP-08`, le second nommant sa propre cause (une prescription de `nadia-prof`
ambigue), tous deux corriges par `marc-dev` et revalides dans le meme sprint ; le refus sur
`EMC-CP-13` (fait inexact), corrige par `lea-dev` et revalide ; le refus sur `SCI-CM1-06` (enonces
auto-contradictoires, confusion tetard/grenouille), corrige par `lea-dev` sur le contenu et par
`camille-po` sur le critere d'acceptation lui-meme, revalide ; une erreur de methode de comptage de
tests trouvee et corrigee par `theo-archi` lui-meme (1692 -> 1701).

**Encore ouverts** : un ecart entre `SEM-1` (referentiel de `cp-semaine`, mathematiques CP) et
`QLM-CP-13` sur le double-compte des mois et des saisons entre deux paquets de matieres differentes,
signale par `nadia-prof` et consigne par `camille-po` dans la matrice, non tranche ; l'absence de
marge de rejeu sur le terrain EMC, nommee d'avance par `DEC-0095` comme un risque non bloquant a
peser et jamais reellement pesee avant de se materialiser par le double refus d'`EMC-CP-08` ;
`MAT-CE1-18` (sens de la division), toujours sans ligne dans `MATRICE-couverture.md`, non signale ce
sprint - rupture d'une sequence de cinq sprints consecutifs (s36 a s40), imputable a un effet de
circuit : `DEC-0095` est arrivee hors du planning habituel qui portait ce signalement les fois
precedentes.

**Un point signale hors depot** (forme 3 de `squad/README.md`, section 2 bis), sur des collisions
`mvnd` transitoires qu'un agent aurait rencontrees pendant le developpement : verifie par moi contre
le depot et non corrobore - ni par les commits de l'agent cite, ni par le rapport qualite de
`yanis-qa`, qui mesure explicitement le contraire. Nomme comme point de vigilance de process, pas
comme fait etabli. Detail : `retro.md`, section 2.

**Aucun amendement de process fait ce sprint** : les deux points qui auraient pu en motiver un sont
deja couverts par une regle existante (forme 3) ou appartiennent a un autre role (arbitrage de tarif
de `nadia-prof` et de `camille-po`). Detail et motif : `retro.md`, section 3.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 7.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s41`, fenetre 2026-09-24 07:15 -> 2026-09-24 10:45
UTC (fenetre ouverte au moment de l'execution du script, avant les commits de cloture - review,
retro, ce rapport). Detail complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 151 | 302 | 52 667 | 836 500 | 9 791 408 |
| `sofia-sm` | claude-sonnet-5 | 99 | 198 | 25 642 | 1 354 493 | 28 072 193 |
| `theo-archi` | claude-opus-5 | 171 | 342 | 64 814 | 934 010 | 17 432 167 |
| `marc-dev` | claude-sonnet-5 | 365 | 730 | 93 285 | 1 373 115 | 69 838 783 |
| `lea-dev` | claude-sonnet-5 | 322 | 644 | 63 643 | 1 779 821 | 50 935 610 |
| `yanis-qa` | claude-sonnet-5 | 183 | 366 | 38 579 | 331 488 | 20 880 643 |
| `nadia-prof` | claude-opus-5 | 335 | 670 | 145 636 | 1 635 347 | 34 641 523 |
| orchestrateur (fil principal) | claude-sonnet-5 | 58 | 116 | 88 721 | 239 363 | 8 500 841 |
| **Total** | | **1684** | **3 368** | **572 987** | **8 484 137** | **240 093 168** |

Aucune invocation mesuree sur cette fenetre pour `relecteur-pedagogique` et `ines-design` : aucun
travail de relecture ou de design n'etait du sur ce sprint (section 5 de `review.md`). Les quatre
compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un
classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur.
