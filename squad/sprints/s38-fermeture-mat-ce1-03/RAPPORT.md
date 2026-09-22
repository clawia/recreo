# Rapport de sprint s38 - fermeture de MAT-CE1-03

- **Duree** : 2026-09-22 08:56 -> 2026-09-22 14:15 (UTC+02:00), 5 h 19 min.
- **Objectif gele** (`planning.md`) : fermer `MAT-CE1-03` a `couvert-valide`, par deux volets -
  corriger les deux reserves du paquet d'additions livre a s37, puis instruire et livrer les trois
  sous-parties restantes (soustractions, complements a la dizaine, moities).

## L'increment livre, en une phrase

`MAT-CE1-03` (calcul mental CE1) ferme sur ses quatre pieces - additions, soustractions,
complements a la dizaine, moities -, chacune auditee par `nadia-prof` apres avoir joue le jar
servi : trois Done pedagogiques **VALIDE**, un **VALIDE SOUS RESERVE** non bloquante.

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0091` | Objectif et regle de gouvernance de s38 : fermer `MAT-CE1-03` par deux volets ; affectation nominative du developpement par `sofia-sm`, jamais livree directement par `theo-archi` | **Acceptee** |
| `ADR-0097` | La sous-partie de `MAT-CE1-03` se declare au descripteur du paquet (cle `competence`) et s'alloue en un seul endroit (`SousPartieDeMathematiquesCe1`) ; les soustractions et les moities de CE1 sont des paquets statiques, comme les additions de s37 | **Accepte** |

Les deux entrees sont verifiees presentes sur le disque, avec leur statut a jour, dans
`squad/decisions/produit/DEC-index.md` et `squad/decisions/adr/ADR-index.md`. Aucune ceremonie de
ce sprint (planning, review, retro) n'a elle-meme cree de `DEC` ni d'`ADR` : les deux ci-dessus ont
ete redigees en cours de sprint, sur des obstacles rencontres, puis enregistrees ici.

## Etat des deux Done

### Technique - verifie par `sofia-sm` sur l'arbre fusionne final, quatre jobs nommes un par un

| Job | Resultat |
|---|---|
| `build` (`bash outils/mvn-jdk21.sh -B clean verify`) | **BUILD SUCCESS**, 6 modules sur 6 |
| `chemins` | **vert** - aucun chemin de poste de travail ni nom de compte |
| `outils` | **vert** - 9 juges purs executes, 0 echec |
| `prefixes` | **vert** - tous les commits portent un prefixe d'agent connu |

**1555 tests unitaires + 6 tests d'integration = 1561, 0 echec, 0 erreur, 0 ignore**, mesures par
moi sur le journal complet du dernier lancement de `build` sur `round-two` a `847a68a9`, methode
blocs `[INFO] Results:` par module - recoupe le compte autoritaire de `yanis-qa` (memes 1555+6,
mesure independamment sur `65f03285`). **ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35,
inchange depuis s37 - aucune regle ArchUnit neuve, la population de ce sprint est du contenu, pas
du bytecode (`ADR-0097`).

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par `nadia-prof`

| Piece de `MAT-CE1-03` | Verdict | Ce qui ferme |
|---|---|---|
| Additions | **VALIDE, sans reserve** | `ADD-CE1-R1`/`ADD-CE1-R2` (reserves de s37) fermees |
| Soustractions | **VALIDE SOUS RESERVE** | Reserve non bloquante `SOU-CE1-R1`, correctif deja donne |
| Complements a la dizaine | **VALIDE, sans reserve** | Piece neuve |
| Moities | **VALIDE, sans reserve** | Piece neuve |

**Trois validations sans reserve, une sous reserve non bloquante - la ligne ferme sur ses quatre
pieces.** Le Done pedagogique est prononce dans les quatre fiches de `squad/conformite/`, citees en
detail dans `review.md`, section 9.

## Couverture du programme

**78/155 (50,3 %)**, en hausse d'un point de pourcentage sur s37 (77/155, 49,7 %). Un seul
mouvement de statut : `MAT-CE1-03` passe de `en cours` a `couvert-valide` - recompte independant
par `camille-po` sur les dix-neuf tableaux de la matrice.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 7). Ni la fermeture de `MAT-CE1-03` ni sa gouvernance de
sprint (`DEC-0091`) ne sont des postes de la composition du Plan 2 (`DEC-0086`/`DEC-0087`) :
verifie par recherche des identifiants de ce sprint dans les deux textes, aucune occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : l'objectif du sprint, recu hors depot, formalise en `DEC-0091` ; l'infaisabilite de
`S38-COMPLEMENTS-DIZAINE`, levee par `nadia-prof` en amendant son propre arbitrage le jour meme ;
l'absence d'`ADR` de mecanisme pour `S38-SOUSTRACTIONS` et `S38-MOITIES`, tranchee par
`ADR-0097`.

**Encore ouverts** : `SOU-CE1-R1` (un distracteur non plausible sur quatre items de soustraction,
correctif deja ecrit, routee en story de correction differee) ; `MAT-CE1-18` (sens de la division)
toujours sans ligne dans `squad/programme/MATRICE-couverture.md`, pour le troisieme sprint
consecutif ; la conclusion de la relecture pedagogique de ce sprint - une invocation est mesuree
(72 tours), mais aucun fichier n'en garde le perimetre ni le resultat. Amendement ecrit dans
`squad/README.md` pour que ce cas soit trace a l'avenir (`retro.md`, section 3).

**Deviation de process constatee, sans consequence de contenu** : la regle du worktree dedie a
chaque premiere ecriture (`squad/README.md` section 9) a de nouveau ete contournee, cette fois par
deux agents a la fois (`camille-po`, `theo-archi`) ecrivant leurs premiers commits directement sur
`round-two`. Rien n'a ete perdu ; c'est la quatrieme occurrence d'une faute deja ecrite depuis
trente-deux sprints. Detail et posture retenue : `retro.md`, sections 2 et 3.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 6.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s38`, fenetre 2026-09-22 06:56 -> 2026-09-22 12:15
UTC. Detail complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 146 | 292 | 74 654 | 1 011 866 | 14 176 722 |
| `sofia-sm` | claude-sonnet-5 | 156 | 312 | 69 328 | 867 301 | 13 790 968 |
| `theo-archi` | claude-opus-5 | 180 | 360 | 91 822 | 1 014 645 | 18 354 193 |
| `marc-dev` | claude-sonnet-5 | 278 | 556 | 91 542 | 1 107 155 | 50 998 967 |
| `lea-dev` | claude-sonnet-5 | 296 | 592 | 116 721 | 2 811 353 | 71 291 016 |
| `yanis-qa` | claude-sonnet-5 | 98 | 196 | 25 972 | 234 395 | 7 323 489 |
| `relecteur-pedagogique` | claude-sonnet-5 | 72 | 144 | 20 586 | 316 131 | 6 316 430 |
| `nadia-prof` | claude-opus-5 / claude-sonnet-5 | 283 | 566 | 173 366 | 1 267 458 | 29 830 626 |
| orchestrateur (fil principal) | claude-sonnet-5 | 89 | 178 | 115 472 | 135 542 | 12 264 484 |
| **Total** | | **1598** | **3 196** | **779 463** | **8 765 846** | **224 346 895** |

Aucune invocation mesuree sur cette fenetre pour : `ines-design`.

Les quatre compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner
donnerait un classement faux, le cache lu depassant couramment l'output de deux ordres de
grandeur.
