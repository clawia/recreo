# DEC-0038 - Trois branches locales non fusionnees, verifiees integralement recapturees dans round-two : abandon des trois

- **Type** : gouvernance - sort de branches non fusionnees
- **Date** : 2026-08-19
- **Auteur** : camille-po
- **Sprint** : s17
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision statue
  sur le sort de trois branches Git, pas sur une competence du programme. Les competences que le
  contenu audite dans ces branches concerne (grands nombres et tables de multiplication CM1) sont
  deja suivies par leurs propres lignes de `squad/programme/MATRICE-couverture.md`, inchangees par
  cette decision.
- **Statut** : Acceptee

## Contexte

`sofia-sm` a nettoye 45 worktrees et 132 branches fusionnees a la cloture du sprint 16. Trois
branches locales restaient, aucune ne survivant a `origin` (qui ne porte que `main` et
`round-two`) : `s10-camille-po` (tete `0c03f2c`), `s11-ines-confirmation` (tete `ccac47f`) et
`s13-nadia-audit` (tetes `653b915` puis `cfb17dc`). L'utilisatrice demande de statuer : soit le
travail entre dans `round-two`, soit il est abandonne explicitement. Le principe de gouvernance du
depot (`CLAUDE.md`) interdit de le laisser dormir une septieme journee sans motif ecrit.

`sofia-sm` a etabli en s13 (`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/review.md`,
ligne 412 et suivantes) qu'une branche non fusionnee ne veut pas dire un travail perdu : un commit
peut avoir ete recapture ailleurs, sous un autre hash et un autre message, tout en portant le meme
contenu. J'ai verifie chacune des trois branches sur ce principe, commit par commit, avant de
statuer - je ne suppose rien.

## Verification effectuee, branche par branche

### 1. `s10-camille-po` (`0c03f2c`)

Un seul commit : `DEC-0022` (perimetre du sprint 10, trois demandes de l'utilisatrice ecartees) et
les quatre stories `S10-1` a `S10-4` qui l'accompagnaient dans `docs/backlog/backlog.md` (340
lignes).

`git diff 0c03f2c 72537e2` (commit deja present dans l'historique de `round-two`, meme message,
meme date d'auteur) ne rend aucune difference : c'est le meme patch, capture sous un hash different.
`DEC-0022` existe donc deja, mot pour mot, dans `round-two`. `DEC-index.md` porte deja la suite de
son histoire : elle a ete partiellement remplacee le meme jour par `DEC-0023`, apres que
l'utilisatrice a vu la silhouette rendue et fourni des references neuves - fait deja trace, non
reecrit ici.

**Issue : abandon.** Rien a recuperer, la branche ne porte rien que `round-two` n'ait deja et sous
une forme plus a jour (la suite de l'histoire, jusqu'a `DEC-0023` puis les sprints suivants, est
deja ecrite).

### 2. `s11-ines-confirmation` (`ccac47f`)

Un seul commit : la forme de l'ecran de confirmation d'abandon proposee par `ines-design`
(conteneur `.confirmation-actions`, bouton "Continuer la partie" en bandeau pleine largeur, "Les
jeux" ecarte de 2 rem).

`git diff ccac47f 6d6a6dc` (commit deja present dans l'historique de `round-two`, meme message,
meme date d'auteur) ne rend aucune difference : meme patch, capture sous un hash different. Le
fichier actuel, `adapters/adapter-web/src/main/resources/templates/fragments/confirmation.html`,
porte encore aujourd'hui le commentaire exact de cette proposition s11, et la regle
`.confirmation-actions` existe dans `recreo.css` (ligne 967). La proposition n'a pas seulement ete
recapturee : elle a ensuite continue a vivre et a ete amendee par la suite reelle de l'historique
(`4eaddf0` s11 - ecart et texte arbitres ; `4ba7298` s12 ; `c39098d`/`10909e8` s14 - barre d'entree
par niveau ; `b7eda0b`/`7173a03` s15 - troisieme arbitrage sur la sortie d'en-tete). L'ecran a
change depuis, mais par-dessus cette proposition, pas a sa place.

**Issue : abandon.** La proposition d'`ines-design` a un objet qui a deja ete traite : elle est
livree, en production dans `round-two`, et surclassee par les amendements des sprints 11, 12, 14
et 15. Rien a router vers `ines-design` : il n'y a pas de proposition en attente, seulement une
branche qui a cesse d'etre le dernier mot avant meme d'etre fusionnee.

### 3. `s13-nadia-audit` (`653b915`, `cfb17dc`)

Deux commits de `nadia-prof` : `653b915` (reserve B des tables de multiplication - 0 sur 1x1 est
un distracteur legitime) et `cfb17dc` (Done pedagogique REFUSE sur les grands nombres CM1 - le
verbe "decomposer" n'est pas travaille, deux indices sont faux - avec son motif detaille, deux
captures d'ecran et un releve de pli en JSON).

C'est le cas nomme par l'utilisatrice comme le plus sensible, puisqu'un refus pedagogique non
trace serait une perte que seule `nadia-prof` pourrait arbitrer. Verification faite sans supposer :

- `git diff 653b915 cc68304` : aucune difference de contenu. `cc68304` est deja dans l'historique
  de `round-two`, date du 2026-08-17, soit **apres** la review de s13
  (`squad/sprints/s13-grands-nombres-et-chemin-de-catalogue/review.md`, ligne 412) qui avait
  constate ce commit absent de `round-two` a ce moment-la. Le trou signale par la review a donc
  ete referme entre s13 et s14 : `868ef52` ("[NAD] s14 : Done pedagogique VALIDE - reserves A et B
  des tables") suit immediatement `cc68304` dans l'historique.
- `git diff cfb17dc a7ebb29` : aucune difference de contenu (memes 287 lignes de motif dans
  `FICHE-bandit-manchot-grands-nombres-cm1.md`, memes quatre fichiers de releve : deux captures
  PNG du geste "decomposer", une capture PNG "lire", un releve de pli en JSON). `a7ebb29` est deja
  dans l'historique de `round-two`, a la meme date d'auteur que `cfb17dc`. La suite de l'histoire
  pedagogique de ce paquet est elle aussi presente et complete : `e67d287` (second Done refuse),
  `c2cb6ee` (addendum), `3996b31` (arbitrage voie de rendu), `2bfcb55` (Done pedagogique VALIDE
  SOUS RESERVE).

Le motif detaille du refus et les quatre fichiers de releve existent donc integralement dans
`round-two`, sous les hashes `cc68304` et `a7ebb29`. Aucune trace pedagogique n'est perdue : rien
a arbitrer par `nadia-prof` sur ce point precis.

**Issue : abandon.** Les deux commits sont integralement et identiquement recaptures.

## Decision

Les trois branches sont **abandonnees**, sur le seul motif verifie ci-dessus : leur contenu existe
deja, integralement et a l'identique, dans l'historique de `round-two`. Aucune n'est recuperee,
puisqu'il n'y a rien a recuperer que `round-two` ne porte deja - recopier ce contenu serait une
duplication, pas une recuperation.

## Ce que je tranche seule

Le sort des trois branches (abandon, motive commit par commit ci-dessus). Je n'ai retire ni
prononce aucun Done pedagogique : je constate que celui deja rendu par `nadia-prof` sur les grands
nombres et sur les tables de multiplication CM1 est deja lisible au meme endroit qu'avant, sous des
hashes differents mais un contenu identique.

## Ce que je route

- **`sofia-sm`** : suppression des trois branches locales (`s10-camille-po`, `s11-ines-confirmation`,
  `s13-nadia-audit`) et de leurs worktrees s'ils existent encore. C'est une operation Git de
  housekeeping, pas une decision produit ; je ne l'execute pas moi-meme et je ne fusionne ni ne
  pousse rien depuis ce worktree dedie.
- **Aucun autre routage.** Rien vers `ines-design` (la proposition a un objet deja traite, voir
  point 2). Rien vers `nadia-prof` (aucune trace pedagogique manquante, voir point 3). Rien vers
  `theo-archi` (aucune question technique ou d'architecture ouverte par cette verification).

## Impact

- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0038` ajoutee.
- `docs/backlog/backlog.md` : inchange, aucune story neuve n'en decoule.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- Aucun fichier de code, aucune fiche de conformite n'est modifie par cette decision : elle
  constate un etat deja present dans `round-two`, elle n'en cree pas.
