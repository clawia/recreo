# [Sprint s01] Retrospective - 2026-08-05

- **Anime par** : sofia-sm

## Ce qui a marche

- **La parallelisation en trois worktrees a livre plus vite qu'un sequencement serie, sans
  compromettre le Done technique** : `marc-dev`, `lea-dev` et `ines-design` ont code
  simultanement (S1-1/S1-2/S1-3/S1-8, S1-4/S1-5/S1-7, S1-6/S1-9), chacun vert dans son propre
  arbre, et le cout assume au planning (une fusion qui ne prouve rien tant qu'elle n'est pas
  auditee dans son ensemble) a ete tenu jusqu'au bout : personne n'a declare le Done technique
  atteint avant que l'arbre fusionne, et lui seul, ne tourne vert.
- **Les cinq points routes a `camille-po` par `theo-archi` en avis de faisabilite ont ete tranches
  avant que le code n'en ait besoin.** `DEC-0004` a fixe les textes, seuils et formats exacts
  (score, message d'erreur, nombre de manches, seuils de S1-5, seuil des operandes nuls) pendant
  que `ines-design` codait deja S1-6/S1-9 en parallele : aucun developpeur n'a du inventer un
  texte ou un chiffre a la place de `camille-po`.
- **La reformulation ecrite par `nadia-prof` directement dans la fiche de conformite a permis de
  fermer trois obstacles de texte sans ouvrir une nouvelle ceremonie.** Elle n'a pas seulement dit
  "refuse" ou "sous reserve" : elle a ecrit le texte de remplacement exact, ce qui a rendu
  l'application immediate possible dans le geste meme de cette review plutot que d'attendre un
  aller-retour supplementaire.
- **L'obstacle herite de s00 sur `DifficulteTest.leContratDeContenuNePorteQueDuTexte` a ete traite
  au bon moment.** `ADR-0012` a introduit un champ dans le contrat de contenu ; `theo-archi` a
  ferme le trou dans le meme geste, exactement comme le planning l'avait anticipe ("si S1-4 ou
  S1-5 introduit un enum dans `domain.content`, le trou doit etre ferme dans le meme commit").

## Ce qui a coince

### 1. Trois worktrees verts separement, rouges ensemble - un vrai defaut de fusion, pas un faux positif

`marc-dev` et `lea-dev` codaient chacun un fragment de la meme mecanique de score (`DEC-0004`,
point 1 : `Score : N`, uniquement les reussites). Le test de fin de partie ecrit par `lea-dev`
asserait que le score "contient 2" - une assertion vraie aussi bien sous l'ancien format `0 / 2`
(ou 2 comptait les tentatives) que sous le nouveau `Score : 2` (ou 2 compte les reussites), pour
deux coups faux joues dans son scenario. Les deux formats faisaient passer le meme test : le vert
de son worktree ne prouvait donc pas ce qu'il semblait prouver. La fusion (`d534451`) l'a revele
en rendant le test faux (le score reel etait 0, pas 2) et a du le corriger vers la valeur exacte
attendue - une correction qui rend l'assertion plus stricte, jamais plus permissive.

**Mecanisme du probleme** : une assertion large ("contient une valeur plausible") peut rester
verte alors que deux implementations concurrentes d'un meme contrat divergent reellement. Le
probleme n'est pas la parallelisation elle-meme - le planning avait deja assume son cout et prevu
que "un `mvn verify` vert dans un worktree ne prouve que ce worktree" - c'est que rien n'imposait
qu'une assertion sur une valeur fixee par une `DEC` (ici, le format exact du score) soit ecrite
sous sa forme litterale exacte plutot que sous une forme qui tolere aussi l'ancienne.

### 2. Le travail a plusieurs agents dans le meme arbre partage a cause une perte reelle

Une fois les trois worktrees fusionnes, la porte technique (`yanis-qa` et `theo-archi`) s'est
deroulee **sur le meme arbre de travail principal, en meme temps**, contrairement aux trois
worktrees de developpement qui, eux, etaient isoles. Consequence, decrite independamment par les
deux agents concernes :

- `yanis-qa` a commite par erreur des fichiers d'un autre agent en cours d'indexation dans son
  propre commit (defait immediatement par `git reset --soft`, jamais de commit ecrase ni de
  `push --force`).
- `theo-archi` a vu sept fichiers hors de son perimetre changer sous lui, un fichier de log
  parasite apparaitre a la racine, puis un commit d'un autre agent emporter ses propres fichiers
  en cours d'indexation. **Une reinitialisation dure a suivi et a detruit tout le travail non
  commite - le sien comme celui des autres agents presents sur cet arbre au meme moment** ; ce
  travail a du etre refait.

Le principe d'isolation par worktree, applique correctement aux trois developpeurs pendant la
phase de code, s'est arrete a la frontiere qu'on lui avait tracee (developpement) et n'a pas
couvert la phase suivante (audit), ou plusieurs agents se sont pourtant retrouves actifs sur le
meme arbre en meme temps.

### 3. Deux agents sans outil shell : un livrable commite par un tiers, hors du regard de personne si on ne le note pas

`camille-po` et `relecteur-pedagogique` n'ont pas d'acces a un outil shell : ils ne peuvent pas
executer `git commit` eux-memes. Leurs livrables de ce sprint (`DEC-0004`, `DEC-0005`,
l'amendement de `CLAUDE.md`) ont donc ete commites par un tiers en leur nom, avec leur prefixe
respectif (`[CAM]`, `[REL]`) prealablement convenu. **Le mecanisme fonctionne, mais il repose
entierement sur le fait que quelqu'un s'en souvient a chaque fois.** Rien dans `squad/README.md`
ne dit aujourd'hui que ce report de commit existe ni qui en est responsable. Si un tiers oublie de
commiter un de ces deux livrables, ce livrable existe dans une conversation d'agent mais pas dans
le depot - exactement la situation que `CLAUDE.md` et `squad/README.md` interdisent tous deux
("une decision qui n'est pas redigee en `ADR` ou en `DEC` n'a pas ete prise, meme si tout le monde
l'applique"), et rien ne le detecterait avant qu'un obstacle ulterieur ne le revele.

## Amendements au process

| Section de squad/README.md | Changement | Raison |
|---|---|---|
| 9, etape 2 (Developpement) | Toute assertion de test qui verifie une valeur fixee par une `DEC` ou un `ADR` (texte exact, format exact, seuil exact) doit comparer a la forme litterale exacte decidee, jamais a une forme partielle ou ambigue qui tolererait aussi une implementation concurrente ou perimee du meme contrat. | Le defaut de fusion de ce sprint (`d534451`) venait exactement de la, pas d'une erreur de calcul : `contains("2")` passait sous deux formats de score incompatibles entre deux worktrees paralleles, et n'a donc rien signale avant la fusion. |
| 9, etape 4 (Porte technique) | Quand plus d'un agent doit travailler sur le meme arbre fusionne en meme temps (`yanis-qa` et `theo-archi` a la porte technique, par exemple), chacun opere dans son propre worktree ou clone isole du commit fusionne, au meme titre que les developpeurs pendant la phase de code - l'isolation ne s'arrete pas a la fin du developpement. | Le travail simultane sur l'arbre principal a fait perdre du travail non commite deux fois ce sprint (fichiers emportes par un commit d'un autre agent, puis une reinitialisation dure qui a tout efface), et les deux agents concernes l'ont signale independamment. |
| 1 (L'equipe) ou 8 (Prefixes de commit) | Nommer explicitement que `camille-po` et `relecteur-pedagogique` n'ont pas d'acces shell, que leurs livrables sont commites en leur nom par un autre membre de la squad avec leur prefixe propre, et que c'est `sofia-sm` qui garantit qu'aucun de leurs livrables ne reste non commite a la fin d'une ceremonie. | Le mecanisme existe et fonctionne depuis s00, mais aucun texte ne le decrit : il repose sur une vigilance non ecrite, alors que la regle fondatrice du depot ("le depot est la seule memoire partagee") exige justement qu'aucun mecanisme de tracabilite ne depende de la memoire de quelqu'un. |

## Decisions prises

Aucune decision prise pendant cette retro au sens d'un nouvel `ADR` ou d'une nouvelle `DEC`. Les
trois amendements ci-dessus sont des propositions tracees pour le prochain planning ; ils ne
modifient pas `squad/README.md` par eux-memes, pour laisser la squad les discuter avant de les
acter.
