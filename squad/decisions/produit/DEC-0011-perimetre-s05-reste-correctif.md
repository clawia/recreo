# DEC-0011 - Le sprint 5 reste entierement correctif, l'ouverture suivante est nommee au sprint 6

- **Type** : perimetre produit, priorisation de sprint
- **Date** : 2026-08-06
- **Auteur** : camille-po
- **Sprint** : s05
- **Competence programme visee** : transverse - decision de perimetre, aucune competence unique
  visee.
- **Statut** : Acceptee

## Contexte

Le sprint 5, tel qu'ecrit a sa cloture, ne cree aucun contenu de matiere ou de competence
nouvelle : il execute le retrait de "Le bon son" (`DEC-0009`), corrige son contenu sur quatre
motifs de refus (S5-2 a S5-5, dont un motif structurel route a `theo-archi`), et complete la
ligne francais CM1 vers la condition ecrite par `nadia-prof` pour `couvert-valide` (S5-6, S5-7).
`squad/programme/MATRICE-couverture.md` affiche aujourd'hui 1 ligne `couvert-valide`, 2 lignes
`en cours`, 43 lignes `non couvert`.

L'utilisatrice a deja, en s04, juge ce rythme insuffisant ("je ne comprends pas pourquoi on n'a
pas de nouveaux jeux"), ce qui a renverse `DEC-0007` et produit `DEC-0008`. Je dois trancher si
s05 doit, comme s04, ouvrir une ligne de plus en parallele des corrections, ou rester entierement
correctif - sans reproduire le raisonnement de `DEC-0007`, qui a produit trois sprints sans
matiere nouvelle et que l'utilisatrice a du renverser elle-meme.

## Ce qui distingue cette decision de DEC-0007

`DEC-0007` posait une regle generale et fermee : aucune matiere nouvelle avant que la ligne
engagee ne soit `couvert-valide`, quel que soit l'etat reel de la reserve de contenu disponible.
Cette regle a produit trois sprints de silence sur la couverture sans date de sortie ecrite, ce
qui a fini par etre percu comme une politique plutot que comme une decision motivee par les faits
d'un sprint donne.

Je ne pose pas ici la meme regle. Le motif de rester correctif ce sprint tient a l'etat reel de la
reserve de contenu, pas a un principe general :

1. **Aucun candidat de `contenu-repris/` n'ouvre une ligne sans reproduire un defaut deja
   diagnostique.** L'inventaire complet mene pour `DEC-0008` a identifie exactement deux fichiers
   en correspondance integrale avec une ligne de referentiel (`cp-le-bon-son.yml`,
   `cm1-homophones.yml`) - les deux deja ouverts. Les deux candidats suivants les plus proches
   (`textes-trous/cm1-accords.yml`, `textes-trous/cm1-conjugaison.yml`) ne couvrent chacun qu'
   **une partie** de leur competence de referentiel (l'accord de l'adjectif seul, le present de
   l'indicatif seul). C'est exactement le defaut que `nadia-prof` vient de sanctionner sur
   `bandit-manchot-homophones-cm1` : un contenu qui ne couvre qu'une partie des "principaux"
   homophones reste `en cours` sans pouvoir passer `couvert-valide`. Ouvrir l'un de ces deux
   candidats maintenant reproduirait, en le sachant a l'avance, le meme trou plutot que d'apporter
   une information nouvelle.
2. **Le sprint porte deja une charge corrective non negociable.** Un jeu reste `REFUSE` et retire
   du catalogue jusqu'a correction (`DEC-0009`) ; un jeu est `VALIDE SOUS RESERVE` avec une
   condition ecrite non encore levee ; un constat structurel bloque l'aide en jeu dans deux des
   trois jeux du catalogue. Ce n'est pas une dette choisie par politique, comme celle de
   `DEC-0007` : ce sont trois obstacles ecrits par `nadia-prof` elle-meme sur les increments de
   s04, independants de toute decision de ma part.
3. **Le report est borne et nomme, pas indefini.** Une fois S5-6 et S5-7 audites, les deux
   candidats compatibles (`cm1-accords`, `cm1-conjugaison`) sont deja identifies par `DEC-0008`
   comme ne demandant aucun nouvel arbitrage de matiere : ce n'est pas un "plus tard" sans date,
   c'est le sprint suivant, sur un contenu deja choisi.

## Decision

Le sprint 5 reste entierement correctif : aucune ligne de plus n'entre `en cours` ce sprint. Le
sprint 6 ouvre, en parallele de toute correction restante, au moins une des deux lignes candidates
de `textes-trous/` deja nommees dans `docs/backlog/backlog.md` ("Backlog - sprints suivants"), en
assumant explicitement cette fois qu'elle ne couvrira qu'une partie de sa competence - a la
difference de s04, ou ce fait n'etait pas encore visible avant l'audit des homophones.

## Ce que cette decision ne fait pas

- Elle ne pose aucune regle generale interdisant d'ouvrir une matiere avant qu'une autre ne soit
  `couvert-valide` : c'est une lecture de l'etat reel de la reserve de contenu pour ce sprint
  precis, pas une politique reconduite par defaut.
- Elle ne prejuge pas des verdicts de `nadia-prof` sur les increments corriges de s05.
- Elle ne tranche aucune question technique.

## Impact

- `docs/backlog/backlog.md` : note ajoutee en tete de la section Sprint 5, et dans la section
  "Backlog - sprints suivants" sur le candidat de sprint 6.
- `squad/programme/MATRICE-couverture.md` : note ajoutee sous la synthese de couverture, sans
  changement de statut de ligne - aucune ligne n'est ouverte par cette decision.

## Ce qui reste non testable par construction

- Que le sprint 6 ouvre effectivement une des deux lignes nommees reste un engagement de
  planification, pas une garantie : si l'audit de S5-6/S5-7 ou un autre constat en decide
  autrement, je le documenterai a ce moment plutot que d'affirmer ici une date certaine.
