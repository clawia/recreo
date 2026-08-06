# [Sprint s03] Retrospective - 2026-08-06

- **Anime par** : sofia-sm

## Ce qui a marche

- **La parallelisation en trois worktrees disjoints, decidee au planning sur la base du defaut
  reel de s01, a tenu sa promesse.** `lea-dev` (domaine/mecanique), `ines-design` (CSS/gabarits) et
  `marc-dev` (viewport) ont code sans toucher au meme contrat en meme temps, et l'audit qui a
  suivi la fusion s'est fait lui aussi en arbres isoles, pour la premiere fois reellement applique
  et non plus seulement propose (retro de s01, section 9, etape 4).
- **Un desaccord de comptage entre deux agents (`22` contre `21` sur `LesReglesMordentTest`) a ete
  reconcilie par la mesure, pas par l'autorite.** `yanis-qa` et `theo-archi` ont chacun explique le
  chiffre de l'autre plutot que de defendre le sien : deux moteurs JUnit distincts sur la meme
  classe, deux mesures legitimes du meme fichier. Le desaccord ouvert depuis s02 est clos avec le
  meme protocole des deux cotes.
- **`theo-archi` a trouve et corrige un defaut dans son propre code, dans le meme cycle, sans
  attendre qu'on le lui reproche deux fois.** `ADR-0015` ferme deux dettes qu'il avait lui-meme
  nommees sans les fermer dans `ADR-0014`, et corrige un angle mort de detection (`!important`)
  trouve par `yanis-qa` dans une regle qu'il avait ecrite deux jours plus tot.

## Ce qui a coince

### 1. Trois worktrees ont ete provisionnes sur `main`, l'ancienne lignee, au lieu de `round-two`

**Chaque agent (`lea-dev`, `ines-design`, `marc-dev`) a du reinitialiser sa branche avant de
commencer a coder**, parce que le worktree de depart pointait par defaut sur `main` - la branche
consultable mais non references de la reconstruction, celle que `CLAUDE.md` nomme explicitement
"pas une reference a suivre". Ce n'est pas une erreur de jugement d'un agent isole : le meme
defaut s'est produit trois fois de suite, ce qui indique une cause de configuration plutot qu'une
inattention repetee. **J'ai verifie a la fusion que le lignage de chacun des trois lots etait
sain** (chaque commit fusionne descend bien de `round-two` et non de `main`), mais je l'ai verifie
apres coup, pas empeche avant.

### 2. Trois agents ont valide sur un JDK 25 en croyant qu'aucun JDK 21 n'existait sur le poste

`yanis-qa`, dans la premiere moitie de son rapport (redigee avant la fusion), affirme avoir compile
"via un JDK 25 local (aucun JDK 21 n'etait installe sur ce poste)". Deux autres agents ont fait le
meme constat pendant s03, selon ce que `theo-archi` rapporte a sa propre cloture
(`confirmation-archunit.md`, section 1). **`lea-dev` a trouve la cause reelle, et elle est
precise** : le fichier de toolchains de l'utilisateur porte un nom fautif -
`toochains.xml` au lieu de `toolchains.xml` -, que Maven ne lit donc jamais ; le fichier declare
pourtant bien un JDK 21 (`21.0.10`) et un JDK 25 tous les deux presents sur le poste. Le `java` par
defaut du `PATH`, lui, resout un JRE 8 qui ne compile pas, ce qui pousse chaque agent a chercher
un JDK utilisable et a tomber sur le 25 installe a cote.

**Ce n'est pas un fichier du depot** : je ne le corrige pas, et aucun `ADR` ne peut le faire. Mais
`theo-archi` a raison de nommer ce qui, lui, releve du depot : rien dans `CLAUDE.md` ni dans
`squad/README.md` n'oblige aujourd'hui un agent a verifier localement qu'il mesure sur le meme JDK
que la CI (`actions/setup-java`, `java-version: "21"`). Verifie a cette cloture : le fichier existe
toujours sous ce nom fautif au moment ou j'ecris, et `JAVA_HOME` positionne explicitement sur le
JDK 21 declare par ce fichier de toolchains (chemin de poste, jamais ecrit dans un fichier commite)
donne bien `java version "21.0.10"`.

### 3. Une fixture ecrite par l'auteur d'une regle ressemble a ce que l'auteur imagine, pas a ce que le monde ecrit

Ce constat appartient au chantier Bootstrap, hors sprint (voir `review.md`), mais la lecon de
methode qu'il porte concerne toute regle de garde-fou ecrite par cette squad, pas seulement celle
qui l'a produite - je la range donc ici plutot que de la laisser dormir dans un ADR que personne ne
relit en retro.

`theo-archi` avait ecrit, dans `ADR-0014`, une preuve de morsure sur `!important` fondee sur un
jeu fautif qui posait `font-size:1rem!important` en clair. `yanis-qa` a montre que cette forme
n'est jamais celle que Bootstrap 5 ecrit reellement : la bibliotheque nomme ses variables
`--bs-<composant>-<propriete>`, et `--bs-btn-font-size:1rem` contient la sous-chaine `font-size:`
que le garde-fou cherchait, trouvee **avant** la vraie declaration du meme bloc. Le garde-fou
passait sa propre epreuve et echouait sur la vraie bibliotheque. `theo-archi` l'ecrit lui-meme,
sans l'attenuer (`rapport-qualite.md`, section 8, cite dans `confirmation-archunit.md`) :
*"`ADR-0013` exige un objet neuf pour prouver la morsure. Ce n'est pas assez. Une fixture ecrite
par l'auteur de la regle ressemble a ce que l'auteur imagine, pas a ce que la bibliotheque
ecrit."*

`ADR-0013` (obligation 3) exige deja qu'un objet neuf, absent de la production, prouve la morsure
d'un garde-fou de balayage. Ce que ce cycle montre en plus : un objet neuf **invente par l'auteur
de la regle** peut rester une fiction confortable si la regle porte sur une matiere externe (ici,
une bibliotheque tierce dont on ne controle pas les conventions). La preuve de morsure la plus
forte est celle qui confronte la regle a un artefact **reellement issu de la source qu'elle
juge** - ce que `yanis-qa` a fait en mutant `bootstrap.min.css` lui-meme plutot que la fixture de
`theo-archi`.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 2 (Developpement) | Avant le premier commit dans un worktree neuf, l'agent qui l'ouvre verifie explicitement (`git log -1`, ou `git merge-base --is-ancestor round-two HEAD`) que la branche de depart descend de `round-two`, et le note dans son premier commit si une reinitialisation a ete necessaire. `sofia-sm` continue de verifier le lignage a la fusion, mais en aval seulement ; ce controle doit exister en amont. | Trois worktrees sur trois ont ete provisionnes sur `main` en s03. Un incident isole se corrige ; trois occurrences consecutives indiquent une cause de configuration du poste ou de la commande d'ouverture de worktree, pas trois inattentions independantes. |
| 9, etape 4 (Porte technique) | Avant de rapporter un resultat de `mvn verify`, chaque agent execute `java -version` sous le `JAVA_HOME` qu'il utilise et cite la version majeure obtenue dans son rapport, plutot que de rapporter seulement "vert" ou "un JDK 21 n'existait pas". | Trois agents ont conclu a tort qu'aucun JDK 21 n'existait sur ce poste, alors qu'il y etait, mal reference par un fichier de toolchains au nom fautif (`toochains.xml`). Le defaut n'est pas dans le depot et ne peut pas y etre corrige ; la seule protection possible est que chaque agent verifie et cite explicitement ce qu'il mesure, plutot que de deduire l'absence d'un JDK de l'echec d'une seule commande par defaut. |
| 4 (Les ceremonies) et ADR-0013 | Quand une preuve de morsure de garde-fou porte sur une matiere externe au depot (bibliotheque tierce, format impose par un tiers), l'objet neuf exige par `ADR-0013` obligation 3 doit inclure au moins une mutation faite directement sur un artefact reel de cette matiere (le fichier tiers lui-meme), pas seulement sur une fixture ecrite par l'auteur de la regle. | La preuve de morsure de `theo-archi` sur `!important` (`ADR-0014`) reposait uniquement sur une fixture qui n'ecrivait jamais la forme que Bootstrap 5 ecrit reellement (`--bs-<composant>-<propriete>`) ; elle est passee sa propre epreuve tout en laissant un angle mort reel, trouve seulement quand `yanis-qa` a mute le fichier tiers lui-meme. |

## Decisions prises

Aucune decision prise pendant cette retro au sens d'un nouvel `ADR` ou d'une nouvelle `DEC`. Les
trois amendements ci-dessus sont des propositions tracees pour le prochain planning ; ils ne
modifient pas `squad/README.md` par eux-memes, pour laisser la squad les discuter avant de les
acter.
