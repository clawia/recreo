# Rapport de sprint s39 - couverture maximale sur six lignes candidates

- **Duree** : 2026-09-22 14:45 -> 2026-09-22 19:31 (UTC+02:00), 4 h 46 min.
- **Objectif gele** (`planning.md`, `DEC-0092`) : maximiser la couverture du programme en fermant,
  autant que possible parmi six lignes candidates identifiees, dans le respect de `DEC-0089` (CE1
  mathematiques seule, CM2 differe).

## L'increment livre, en une phrase

Quatre lignes du programme ferment a `couvert-valide` (monnaie CE1, energie CM1, outils arts
plastiques CP, solides usuels CE1), deux ouvrent partiellement a `en cours` sur leur seule
sous-partie textuelle (denombrement CP, longueurs CE1) - la sixieme, denombrement, restait bloquee
des le planning par un arbitrage pedagogique anterieur non negociable (`DEN-2`).

## Les decisions du sprint

| Decision | Titre | Statut |
|---|---|---|
| `DEC-0092` | Objectif et perimetre de s39 : maximiser la couverture en instruisant six lignes candidates verifiees `non couvert`, dans le respect de `DEC-0089` ; cite en entier l'arbitrage `DEN-2` qui plafonne `MAT-CP-01` a `en cours` | **Acceptee** |
| `ADR-0098` | L'allocation d'une sous-partie de mathematiques de CE1 (`ADR-0097`) nomme desormais sa ligne de referentiel, et un paquet dont la competence est allouee sur une ligne qu'il ne declare pas est rouge | **Accepte** |

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

**1606 tests unitaires + 6 tests d'integration = 1612, 0 echec, 0 erreur, 0 ignore**, mesures par
moi sur le journal complet du dernier lancement de `build` sur `round-two` a `7514c5ff`, methode
blocs `[INFO] Results:` par module - recoupe le compte autoritaire de `yanis-qa` (memes 1606+6,
mesure independamment sur `5e867bc5`). **ArchUnit** : `static final ArchRule` 36, `@ArchTest` 35,
inchange depuis s38 - aucune regle ArchUnit neuve, la population de ce sprint est du contenu YAML
et des descripteurs, pas du bytecode (`ADR-0098`).

**Porte technique verte dans son ensemble : les quatre jobs de `.github/workflows/ci.yml` sont
verifies, aucun n'est deduit.**

### Pedagogique - mixte, prononce par `nadia-prof`

| Ligne | Verdict | Ce qui ferme |
|---|---|---|
| Monnaie CE1 (`MAT-CE1-13`) | **VALIDE, sans reserve** | `couvert-valide` |
| Energie CM1 (`SCI-CM1-04`) | **VALIDE, sans reserve** | `couvert-valide` |
| Outils arts plastiques CP (`ART-CP-11`) | **VALIDE, sans reserve** | `couvert-valide` |
| Solides usuels CE1 (`MAT-CE1-16`) | **VALIDE, sans reserve** | `couvert-valide`, boule verifiee lisible a l'ecran sur trois tailles |
| Denombrement CP (`MAT-CP-01`) | **VALIDE limite a la part textuelle livree** | `en cours`, `DEN-2` non levee |
| Longueurs CE1 (`MAT-CE1-09`) | **VALIDE limite a la part textuelle livree** | `en cours`, mesure a la regle graduee non servie |

**Quatre validations sans reserve, deux limitees a leur sous-partie textuelle - quatre lignes
ferment, deux avancent sans fermer.** Le Done pedagogique est prononce dans six fiches de
`squad/conformite/`, citees en detail dans `review.md`, section 9.

## Couverture du programme

**82/155 (52,9 %)**, en hausse de 2,6 points sur s38 (78/155, 50,3 %). Quatre fermetures et deux
ouvertures partielles - recompte independant par `camille-po` sur les dix-neuf tableaux de la
matrice.

## Le registre de dette

**Inchange, 9,25** (`review.md`, section 7). Ni la couverture maximale de s39 ni sa gouvernance de
sprint (`DEC-0092`) ne sont des postes de la composition du Plan 2 (`DEC-0086`/`DEC-0087`) :
verifie par recherche des identifiants de ce sprint dans les deux textes, aucune occurrence.

## Obstacles resolus et encore ouverts

**Resolus** : l'objectif du sprint, recu hors depot, formalise en `DEC-0092` ; `DEN-2` reexaminee
et maintenue par `nadia-prof`, comme annonce des le planning ; l'allocation d'`ADR-0097` etendue a
trois lignes CE1 neuves par `ADR-0098`, trouvee par `theo-archi` avant qu'un paquet muet ne
l'exploite ; un distracteur de conversion cm/m et la forme visuelle de la sphere, corriges par un
amendement d'arbitrage le jour meme ; un conflit de fusion concurrent entre deux worktrees de dev,
resolu sans perte de travail.

**Encore ouverts** : `MAT-CE1-18` (sens de la division) toujours sans ligne dans
`squad/programme/MATRICE-couverture.md`, pour le quatrieme sprint consecutif ; un verrou `mvnd`
transitoire signale par deux devs, non reproduit et non route faute de matiere.

**Deviation de process constatee, avec consequence cette fois** : la regle du worktree dedie a
chaque premiere ecriture (`squad/README.md` section 9) a de nouveau ete contournee - cinquieme
occurrence d'une faute deja ecrite depuis trente-neuf sprints, cette fois avec un reacteur rapporte
rouge (1 test `bootstrap`) pendant le retard correspondant, corrige avant la fusion. `squad/README.md`
section 9 est amendee en consequence : Sofia ouvre desormais elle-meme le worktree de l'agent
qu'elle route, au moment du routage. Detail et posture retenue : `retro.md`, sections 2 et 3.

Tableau complet, avec la categorie et la route de chaque obstacle : `review.md`, section 6.

## Consommation de tokens

Genere par `outils/rapport-tokens.py --sprint s39`, fenetre 2026-09-22 12:45 -> 2026-09-22 17:31
UTC. Detail complet dans `RAPPORT-tokens.md` (ce dossier) et cumule dans
`squad/rapports/JOURNAL-tokens.md`.

| Agent | Modele | Tours | Input | Output | Cache cree | Cache lu |
|---|---|---:|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5 | 172 | 344 | 72 618 | 971 138 | 14 723 826 |
| `sofia-sm` | claude-sonnet-5 | 89 | 178 | 41 132 | 383 221 | 7 594 015 |
| `theo-archi` | claude-opus-5 | 344 | 688 | 136 757 | 1 247 704 | 30 442 755 |
| `marc-dev` | claude-sonnet-5 | 395 | 790 | 145 781 | 926 207 | 110 182 560 |
| `lea-dev` | claude-sonnet-5 | 469 | 938 | 191 803 | 1 232 482 | 181 544 096 |
| `yanis-qa` | claude-sonnet-5 | 125 | 250 | 27 177 | 266 259 | 10 086 117 |
| `relecteur-pedagogique` | claude-sonnet-5 | 189 | 378 | 36 081 | 452 398 | 24 699 323 |
| `nadia-prof` | claude-sonnet-5 | 250 | 500 | 103 230 | 989 602 | 26 081 216 |
| `ines-design` | claude-sonnet-5 | 68 | 136 | 21 753 | 191 678 | 4 531 060 |
| orchestrateur (fil principal) | claude-sonnet-5 | 123 | 246 | 111 033 | 141 698 | 29 729 874 |
| **Total** | | **2224** | **4 448** | **887 365** | **6 802 387** | **439 614 842** |

Les neuf agents de la squad ont tous une invocation mesuree sur cette fenetre. Les quatre
compteurs sont volontairement separes (voir `RAPPORT-tokens.md`) : les additionner donnerait un
classement faux, le cache lu depassant couramment l'output de deux ordres de grandeur.
