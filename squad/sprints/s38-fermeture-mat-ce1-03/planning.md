# Planning - s38 - fermeture de MAT-CE1-03

- **Horodatage de debut** : 2026-09-22 08:56 (UTC+02:00).
- **Casquette** : `sofia-sm`, Scrum Master.
- **Slug** : `s38-fermeture-mat-ce1-03`, choisi par `sofia-sm` a l'ouverture - aucun agent n'avait
  encore prepare de worktree pour ce sprint.
- **Prealable, en vigueur et non rouvert par ce planning** : `DEC-0089` (Acceptee, `DEC-index.md`)
  - perimetre CE1 ouvert, mathematiques seule, CM2 differe explicitement. Ce planning ne rouvre
    aucune matiere ni aucun niveau hors de ce perimetre.

## 1. Lignee du worktree

Verifie avant toute ecriture (`git rev-parse --abbrev-ref HEAD` puis
`git merge-base --is-ancestor round-two HEAD`) : `s38-sofia` (`../recreo-s38-sofia`) descend de
`round-two` a `686665f1`, ouvert depuis ce commit sans aucun autre ecart.

## 2. Objectif gele, et sa provenance

**Forme d'attribution (`squad/README.md`, section 2 bis)** : cet objectif ne vient d'aucun des
neuf agents et n'a pas ete confirme par l'utilisatrice sous forme citable dans le depot au moment
de ce planning. Il est donc porte sous la **forme 3**, cite mot pour mot, et il **ne vaut pas
motif** tant qu'il n'est pas ecrit ailleurs par l'agent dont c'est le role - exactement le
mecanisme deja suivi par `DEC-0089` (instruction recue de MASTER, puis confirmee par l'utilisatrice
sous forme citable dans `DEC-0089` elle-meme avant de fonder le perimetre de s36).

> **Recu de MASTER, sans source dans le depot, le 2026-09-22** : *"Objectif du sprint que
> l'utilisatrice a choisi (option O3 parmi trois proposees) : fermer MAT-CE1-03 a couvert-valide
> dans ce sprint."*

> **Recu de MASTER, sans source dans le depot, le 2026-09-22, point de gouvernance** : *"des qu'un
> avis de faisabilite est rendu sur une des trois sous-parties, le developpement est affecte
> nominativement (marc-dev, lea-dev, ou toi-meme sous ta casquette dev) et non livre directement
> par theo-archi."*

**Consequence directe pour ce planning** : je gele l'intention operationnelle qui suit comme
**perimetre de travail du sprint**, mais je route a `camille-po`, dans le meme geste, l'ecriture
d'un `DEC` qui la formalise (creation ou amendement de `DEC-0089`) et sa confirmation par
l'utilisatrice sous forme citable - condition deja posee par la regle elle-meme, avant qu'aucune
story derivee de cet objectif ne puisse fonder un critere d'acceptation ou un Done. Tant que ce
`DEC` n'existe pas physiquement, aucun lot de ce sprint n'est livrable ; il reste instruisible et
routable, ce que ce planning fait.

**Intention operationnelle geleee, sous cette reserve** : fermer `MAT-CE1-03` a `couvert-valide`
dans ce sprint, par deux volets disjoints -

1. appliquer les corrections `ADD-CE1-R1` et `ADD-CE1-R2` sur le paquet CE1 deja livre
   (`bandit-manchot-additions-ce1`), textes deja ecrits par `nadia-prof`
   (`squad/conformite/FICHE-bandit-manchot-additions-ce1.md`, sections 7.1 et 7.2), aucun nouvel
   arbitrage pedagogique du de ce cote ;
2. instruire et livrer les trois sous-parties restantes de `MAT-CE1-03` : soustractions dans la
   limite de 100, complements a la dizaine pris pour eux-memes (forme `47 + ? = 50`), moities.

**Point de gouvernance gele pour ce sprint** : des qu'un avis de faisabilite de `theo-archi` est
rendu sur une des trois sous-parties, le developpement qui en decoule est affecte nominativement
(`marc-dev`, `lea-dev`, ou `sofia-sm` sous sa casquette dev) et n'est pas livre directement par
`theo-archi` - fonde sur la retro de s37, section 2, ou `theo-archi` a livre du code deux fois de
suite hors de son role habituel d'avis de faisabilite, sans qu'un planning ne l'ait decide.

**Explicitement hors perimetre de ce sprint** : `MAT-CE1-18` (sens de la division). Ligne deja
presente au referentiel `squad/programme/mathematiques/competences-ce1.md` depuis s36, toujours
sans ligne dans `squad/programme/MATRICE-couverture.md` a la cloture de s37
(`squad/sprints/s37-premier-paquet-mathematiques-ce1-et-ordre-des-niveaux/review.md`, section
obstacles). Dette connue, non engagee a ce planning - aucune affectation, aucune story.

## 3. Les stories, competence visee et avis de faisabilite - etat a l'ouverture

Aucun des deux avis prealables que ce planning doit porter n'existe encore sur le disque. Ce
tableau route plutot qu'il ne rapporte.

| Story / lot | Competence visee | Ce qu'il ferme ou ouvre | Etat a ce planning |
|---|---|---|---|
| `S38-CORR` - lot de corrections `ADD-CE1-R1`/`ADD-CE1-R2` | Mathematiques, CE1, `MAT-CE1-03` (sous-partie additions, deja `en cours`) | Ne change pas le statut de ligne a lui seul - corrige un paquet deja `en cours` | En attente d'instruction `camille-po` (story + criteres d'acceptation testables) et d'avis de `theo-archi` |
| `S38-SOUSTRACTIONS` | Mathematiques, CE1, `MAT-CE1-03` (sous-partie "soustractions dans la limite de 100") | Contribue a fermer `MAT-CE1-03` | En attente d'instruction `camille-po`, puis d'arbitrage prealable de `nadia-prof` si une question pedagogique s'y souleve, puis d'avis de `theo-archi` |
| `S38-COMPLEMENTS-DIZAINE` | Mathematiques, CE1, `MAT-CE1-03` (sous-partie "complements a la dizaine", forme `47 + ? = 50`) | Contribue a fermer `MAT-CE1-03` | En attente d'instruction `camille-po`, puis d'arbitrage prealable de `nadia-prof` si une question pedagogique s'y souleve, puis d'avis de `theo-archi` |
| `S38-MOITIES` | Mathematiques, CE1, `MAT-CE1-03` (sous-partie "moities") | Contribue a fermer `MAT-CE1-03` | En attente d'instruction `camille-po`, puis d'arbitrage prealable de `nadia-prof` si une question pedagogique s'y souleve, puis d'avis de `theo-archi` |

Aucun de ces quatre lots n'a de nom d'identifiant durable au-dela de ce planning : `camille-po` les
nomme definitivement en les instruisant dans `docs/backlog/backlog.md`. Les noms ci-dessus sont
ceux de ce document, pas des identifiants de backlog geles.

## 4. Routage

**Vers `camille-po`** :

- ecrire ou amender le `DEC` qui formalise l'objectif recu de MASTER (section 2), et le faire
  confirmer par l'utilisatrice sous forme citable, avant qu'aucun lot de ce sprint ne soit livre -
  meme geste que `DEC-0089` pour s36 ;
- instruire les quatre lots de la section 3 avec des criteres d'acceptation testables : le lot de
  corrections (`ADD-CE1-R1`, `ADD-CE1-R2`, textes deja ecrits, aucun arbitrage neuf du) et les
  trois sous-parties restantes de `MAT-CE1-03` (soustractions, complements a la dizaine pris pour
  eux-memes, moities) ;
- pour chacune des trois sous-parties, verifier si elle souleve une question pedagogique (bornes de
  tirage, forme des distracteurs, ou toute autre question de meme famille que celle deja tranchee
  pour les additions par `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md`), et si
  oui, la router a `nadia-prof` pour arbitrage prealable **avant** l'avis de faisabilite de
  `theo-archi` sur cette sous-partie - le motif qui a fonctionne pour `S37-1`.

**Vers `theo-archi`** : rendre un avis de faisabilite ecrit sur chacun des quatre lots, une fois
que `camille-po` les a instruits (et, pour les trois sous-parties, une fois que l'arbitrage
pedagogique prealable requis existe s'il y en a un). Rappel explicite du point de gouvernance de
la section 2 : l'avis de faisabilite s'arrete a l'avis, il n'engage aucune ligne de code - le
developpement qui en decoule est affecte nominativement par `sofia-sm` dans un daily ou un
amendement de planning, pas livre par `theo-archi` lui-meme.

## 5. Affectations nominatives

**Aucune affectation de developpement a ce planning.** Les quatre lots de la section 3 sont tous
bloques avant tout engagement de code, sur le meme motif que `S37-1` a s37 : aucune story
instruite avec criteres d'acceptation testables, aucun avis de faisabilite rendu, et pour les
trois sous-parties, aucun arbitrage pedagogique prealable connu tant que `nadia-prof` n'a pas dit
si l'une d'elles en souleve un.

Des qu'un avis de faisabilite est rendu sur un lot (section 4), Sofia l'affecte nominativement
(`marc-dev`, `lea-dev`, ou elle-meme sous sa casquette dev) au daily suivant - aucun fichier n'est
du a ce moment-la, l'affectation remonte dans le tableau des obstacles de `review.md` comme le
reste du suivi de sprint.

## 6. Verification `PAR-16` - point de controle poste/story

**Ce que `PAR-16` demande litteralement** (`squad/README.md`, section 4) : verifier, poste par
poste, la table de composition de la decision de strategie en vigueur. **Cette table n'existe pas
a s38**, meme constat qu'aux trois sprints precedents (s35, s36, s37) : la fenetre du Plan 2
(`DEC-0083`) reste refermee par `DEC-0087` sans qu'une strategie neuve de composition budgetaire
n'ait ete tranchee au-dela.

**Ce que `PAR-16` verifie a la place, meme fallback qu'aux trois sprints precedents** : chaque
element retenu porte-t-il une affectation nominative ou un motif explicite de non-action ?

| Element | Affectation nominative | Issue |
|---|---|---|
| Objectif du sprint (fermeture de `MAT-CE1-03`) | `camille-po` | Routee - `DEC` a ecrire et confirmer avant tout lot livrable (section 2) |
| `S38-CORR` (corrections `ADD-CE1-R1`/`ADD-CE1-R2`) | `camille-po` puis `theo-archi` | Routee, aucun developpeur affecte avant instruction et avis |
| `S38-SOUSTRACTIONS` | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee, aucun developpeur affecte avant instruction et avis |
| `S38-COMPLEMENTS-DIZAINE` | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee, aucun developpeur affecte avant instruction et avis |
| `S38-MOITIES` | `camille-po`, puis `nadia-prof` si question pedagogique, puis `theo-archi` | Routee, aucun developpeur affecte avant instruction et avis |
| `MAT-CE1-18` (sens de la division) | aucune | Non engage a ce sprint, dette connue nommee en section 2, motif explicite : choix de l'utilisatrice de ne pas le traiter ce sprint-ci |

Aucun element n'est "non engage, budget libere" au sens de `PAR-16` : il n'y a pas de budget a
liberer hors d'une table de composition, qui n'existe pas a ce sprint. Chaque element porte soit
une affectation nominative de routage, soit un motif explicite de non-action.

## 7. Ce que ce planning ne fait pas

- Il n'engage aucune ligne de code, de test ni de contenu : les quatre lots de la section 3 restent
  bloques tant que `camille-po` ne les a pas instruits avec des criteres d'acceptation testables et
  que `theo-archi` n'a pas rendu son avis de faisabilite - avec, pour les sous-parties qui le
  demandent, l'arbitrage prealable de `nadia-prof` entre les deux.
- Il n'ecrit ni n'amende aucun `DEC` : l'objectif de ce sprint reste sous forme 3 (recu de MASTER,
  sans source dans le depot) tant que `camille-po` ne l'a pas formalise et fait confirmer par
  l'utilisatrice sous forme citable.
- Il ne tranche aucune question pedagogique (bornes de tirage, forme des distracteurs) sur les
  trois sous-parties restantes : ce travail reste a `nadia-prof`, une fois saisie par `camille-po`.
- Il ne rouvre ni CM2, ni aucune matiere CE1 autre que les mathematiques (`DEC-0089`), ni
  `MAT-CE1-18`.
- Il ne prononce aucun Done pedagogique ni aucun Done technique : rien n'est encore livre a ce
  sprint.

## Decisions prises

Aucune decision de sprint n'est prise dans ce planning. L'objectif recu de MASTER n'est pas encore
formalise en `DEC` (section 2) et aucun avis de faisabilite ni arbitrage pedagogique n'a encore ete
rendu ; ce planning route, il ne tranche rien.
