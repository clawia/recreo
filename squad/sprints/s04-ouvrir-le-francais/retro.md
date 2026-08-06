# [Sprint s04] Retrospective - 2026-08-06

- **Anime par** : sofia-sm

## Ce qui a marche

- **`DEC-0008` a suffi a demarrer un increment concret sans planning formel**, parce qu'elle
  contenait ce qu'un planning aurait du contenir sur le fond : deux stories nommees, leur
  competence programme exacte, leurs criteres d'acceptation, et une clause explicite disant ce
  qu'elle ne tranchait pas. Le contenu, lui, n'a pas souffert de l'absence de ceremonie.
- **La demonstration du decouplage a ete verifiee deux fois, independamment, avant d'etre
  annoncee.** `yanis-qa` et `theo-archi` ont chacun refait le `git diff --stat` sur `domain/`
  plutot que de croire l'affirmation de `lea-dev` sur parole - exactement la posture que
  `squad/README.md` demande de ses garants du Done technique.
- **`nadia-prof` a transforme un refus en constat d'architecture actionnable plutot qu'en simple
  liste de reproches.** Son motif 5 sur "Le bon son" (aucune aide disponible) ne s'arrete pas au
  symptome : elle identifie la classe exacte (`QuestionContentSourceFactory`) et le constructeur
  qui ne porte pas de champ `indice`, et le routage vers `theo-archi` (story S5-5) est deja ecrit
  dans le backlog avant meme cette cloture.

## Ce qui a coince

### 1. Un deuxieme sprint sans planning, et cette fois un sprint entier sans dossier

`s02` n'avait pas eu de planning mais avait eu un dossier et des ceremonies de review et de retro
ecrites en temps utile. **`s04` n'a eu ni planning, ni dossier avant cette cloture : son unique
artefact ecrit pendant le sprint, `rapport-qualite.md` de `yanis-qa`, a du s'ouvrir dans le dossier
du sprint precedent, faute d'un dossier ou vivre.** `theo-archi` et le `relecteur-pedagogique` ont
fait le meme choix, chacun le disant explicitement plutot que de le cacher.

**`camille-po` signale, et je le porte sans l'attenuer, que cette absence l'a concretement gene** :
elle n'a pu relier une story ouverte (S4-1, S4-2) a l'increment reellement livre qu'a posteriori,
en reconstituant la chronologie par les commits, alors qu'un dossier de sprint existant en temps
reel aurait porte cette tracabilite au fil de l'eau. C'est la deuxieme fois que ce motif se
represente (le premier amendement de la retro de s02 proposait deja une reponse - "quand un
sprint demarre a partir d'une observation faite en jeu [...] `sofia-sm` l'ecrit explicitement dans
`review.md`" - mais rien n'imposait de creer le **dossier** avant la cloture, seulement de ne pas
fabriquer le fichier manquant).

### 2. Une decision produit a ete ecrite avant d'etre appliquee : le catalogue est reste jouable

`DEC-0009` (commit `7e739e5`, 2026-08-06 11:45:18) dit *"Le bon son" est retire du catalogue
jouable jusqu'a ce qu'un increment corrige repasse devant `nadia-prof`*. Le retrait effectif du
fichier `catalogue.yaml` n'a ete fait que par un commit distinct, neuf minutes plus tard
(`fb7f4df`, 11:54:41). **Entre les deux, un enfant qui aurait lance l'application aurait pu jouer
un contenu que l'autorite pedagogique venait de refuser, exactement dans l'etat qu'elle avait
refuse.** `nadia-prof` a bien verifie apres coup, en jouant l'application relancee sur `fb7f4df`,
que le retrait est desormais effectif (`FICHE-bandit-manchot-le-bon-son-cp.md`, point 4.1) - mais
elle a verifie une execution, pas prevenu une exposition.

**Neuf minutes n'est pas un long delai, et je ne le grossis pas** : ce n'est pas un incident qui a
concretement expose un enfant, puisqu'aucune application n'a ete servie a un utilisateur reel
entre les deux commits. Ce que je nomme est le trou de gouvernance, independant de sa duree
observee cette fois : **le principe du double Done (`CLAUDE.md`) dit qu'un refus pedagogique et un
Done technique sont independants, mais cette independance ne protege plus rien si le contenu
qu'elle bloque reste accessible pendant que sa decision d'exclusion existe deja sur le disque.**
Le meme trou, sur un dossier ou l'ecart entre la decision et son execution serait de plusieurs
heures plutot que de neuf minutes, exposerait reellement quelqu'un.

### 3. Un referentiel declarait couverte une competence par un jeu qui n'existe pas

`nadia-prof`, en auditant "Le bon son", a trouve que
`squad/programme/francais/competences-cp.md` affirmait la competence "Denombrer les syllabes
orales d'un mot" couverte par un jeu de bandit manchot consacre aux syllabes, avec un renvoi a une
fiche `FICHE-francais-cp-syllabes.md` et a un sprint `s02` - **aucun des trois n'existe sur
`round-two`**, ni le jeu, ni la fiche, ni ce sprint. C'est une annotation heritee de `main`, jamais
purgee lors de la reconstruction depuis zero (`DEC-0001`). `squad/programme/MATRICE-couverture.md`
etait honnete (elle classait deja cette ligne `non couvert`, comme toutes les autres au sprint 0) ;
seul le referentiel mentait.

`nadia-prof` l'ecrit sans mesure, et je reprends sa phrase parce qu'elle nomme exactement la
gravite : *"un referentiel qui declare couverte une competence qui ne l'est pas est le pire defaut
qu'un referentiel puisse avoir : il fait disparaitre un trou."* La matrice de couverture existe
precisement pour rendre visibles les trous du programme ; un referentiel qui en ment un ferme
retire cette visibilite a la source, avant meme que quiconque relise la matrice. Corrige a cette
date (`MATRICE-couverture.md`, section "Etat de reconstruction") ; `camille-po` a relu les trois
autres referentiels sans trouver d'autre occurrence, ce qui est la bonne facon de traiter ce genre
de signalement.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 1 (Planning) | Des le premier commit qui porte un slug de sprint (ou, a defaut de slug encore choisi, des le premier commit qui execute une `DEC` d'ouverture de sprint comme `DEC-0008`), `sofia-sm` cree immediatement `squad/sprints/sNN-<slug>/`, meme vide de tout fichier de ceremonie. Les artefacts produits pendant le sprint (rapport qualite, confirmation ArchUnit, relecture) s'y ouvrent directement, au lieu de choisir un dossier de repli. | Deuxieme sprint sans planning ni dossier ; son seul artefact ecrit pendant le sprint a du s'ouvrir dans le dossier du sprint precedent, et `camille-po` signale que cela l'a empechee de relier une story ouverte a l'increment livre autrement qu'a posteriori. L'amendement de la retro de s02 traitait l'absence de planning ; il ne traitait pas l'absence de dossier. |
| 7 (Le double Done) | Une `DEC` qui change un comportement observable de l'application (retrait ou activation d'un contenu, d'un jeu) est executee dans le meme commit que sa redaction chaque fois que c'est possible techniquement ; si ce n'est pas possible, `sofia-sm` ou `camille-po` verifie et note explicitement, avant de considerer l'obstacle clos, que l'execution a bien suivi et sous quel delai. | `DEC-0009` a ete redigee neuf minutes avant l'edition effective de `catalogue.yaml` qui la met en oeuvre. Le delai etait court et rien n'a ete reellement expose cette fois, mais le principe du double Done cesse de proteger quiconque si une decision d'exclusion peut exister sur le disque avant que le comportement de l'application change. |
| 1 (L'equipe) et section sur `camille-po` | A chaque revue d'un referentiel sous `squad/programme/`, verifier que toute mention d'un jeu, d'une fiche ou d'un sprint cite existe reellement sur `round-two`, pas seulement que la ligne de `MATRICE-couverture.md` correspondante est juste. | Un referentiel declarait une competence couverte par un jeu, une fiche et un sprint qui n'existent pas sur `round-two` (annotation heritee de `main`, jamais purgee). La matrice etait honnete ; c'est le referentiel source qui mentait, et rien ne le verifiait avant que `nadia-prof` le trouve en auditant un contenu different. |

## Decisions prises

Aucune decision prise pendant cette retro au sens d'un nouvel `ADR` ou d'une nouvelle `DEC`. Les
trois amendements ci-dessus sont des propositions tracees pour le prochain planning ; ils ne
modifient pas `squad/README.md` par eux-memes, pour laisser la squad les discuter avant de les
acter.
