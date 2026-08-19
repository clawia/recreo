# DEC-0036 - Le message de partie interrompue est borne a une interruption plausible, jamais a un cookie qui a survecu a l'expiration de sa session

- **Type** : comportement transverse, precision du declencheur d'un texte deja valide
- **Date** : 2026-08-19
- **Auteur** : camille-po
- **Sprint** : s16
- **Competence programme visee** : transverse - pas de contenu pedagogique. Ce comportement
  concerne l'accueil generique de `recreo`, pas une ligne de
  `squad/programme/MATRICE-couverture.md`.
- **Statut** : Acceptee

## Contexte

L'utilisatrice constate, en rejouant l'application livree, que le message "Ta partie s'est
arretee. Choisis un nouveau jeu !" s'affiche des l'ouverture de `/`, sans qu'aucune partie n'ait
ete jouee dans les minutes qui precedent. Reproduit avec un seul cookie
`recreo-partie-en-cours` present et aucune session : `PartieController.accueil` affiche le
message et efface le cookie des que cette combinaison existe, quelle que soit l'anciennete du
cookie.

**Le mecanisme, tel qu'il existe aujourd'hui** : le cookie marqueur ne porte aucune duree de vie
(`marquerPartieEnCours`, `PartieController.java`) - il survit tant que le navigateur reste ouvert,
independamment de toute action de l'enfant. La session HTTP, elle, expire par la seule inactivite
au bout d'une duree bien plus courte, et disparait integralement a chaque redemarrage du serveur.
Le cookie peut donc survivre, sans limite connue, a une session qui, elle, a deja disparu depuis
longtemps par le cours normal des choses.

**Ce que la lettre de S1-4 dit, et ce qu'elle ne dit pas.** Son dernier critere
(`docs/backlog/backlog.md`, sprint 1) prevoit le message pour le cas ou "la session n'est plus
retrouvable (session perdue ou serveur redemarre)", sans distinguer une interruption survenue il y
a quelques secondes d'un cookie vieux de plusieurs jours. `DEC-0004`, point 3, fixe le texte exact
mais ne fixe aucune duree : ce point n'a jamais ete instruit, ni par la story ni par la decision.

**Ce que le texte lui-meme promet.** `nadia-prof` a valide ce texte precisement parce qu'il nomme
"le meme mot pour le meme evenement" a chaque fois qu'il apparait ailleurs dans l'application
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, motif du 2026-08-18 : *"Quand une partie
est interrompue, recreo affiche aujourd'hui 'Ta partie s'est arretee. Choisis un nouveau jeu !' -
texte que j'ai valide [...] pour cet evenement exact."*). Un cookie qui a survecu plusieurs jours a
la disparition de sa session ne decrit plus "cet evenement exact" : il decrit une trace perimee,
et le texte, lu par un enfant, lui fait croire a une interruption qui n'a pas eu lieu de son point
de vue.

## Decision

**Le marqueur de partie en cours ne doit jamais rester valide au-dela du moment ou la session HTTP
qu'il signale aurait, de toute facon, deja expire par la seule inactivite.** Concretement :

- si la session disparait par la seule inactivite ordinaire (aucun redemarrage, aucun plantage),
  le marqueur doit avoir cesse d'exister au plus tard en meme temps qu'elle, de sorte que l'enfant
  qui revient dans ce cas retrouve l'accueil comme s'il n'avait jamais commence de partie, sans le
  message ;
- si la session disparait par un evenement autre que la seule inactivite (redemarrage, plantage)
  survenu pendant que le marqueur etait encore valide au sens du point precedent, le message
  continue de s'afficher exactement comme aujourd'hui : ce cas reste celui que `nadia-prof` a
  valide, et cette decision ne le retire pas.

**Je ne fixe aucune duree chiffree ici** : lier la duree du marqueur a celle, deja configuree ou a
configurer, de l'inactivite maximale de la session est une question d'implementation, pas un choix
pedagogique ni un choix de contenu. Le choisir comme une lecture de la duree de session plutot que
comme une valeur dupliquee separement est **le moyen technique**, qui appartient a `theo-archi` -
je fixe l'invariant que ce moyen doit tenir, pas sa forme.

**Le texte du message n'est pas rouvert.** Aucune des deux phrases citees plus haut ne change : ce
que cette decision corrige est le moment ou le texte s'affiche, jamais son contenu, deja valide.

## Ce que je route

- **`theo-archi`** : le mecanisme qui lie la duree de vie du marqueur `recreo-partie-en-cours` a la
  duree d'inactivite maximale de la session HTTP, de sorte que l'invariant ci-dessus soit vrai par
  construction et non par coincidence de deux valeurs maintenues separement.
- **`nadia-prof`** : rejouer les deux scenarios nommes ci-dessus (retour apres une simple
  inactivite prolongee ; retour immediatement apres un redemarrage survenu pendant une partie
  active) sur l'increment livre, et consigner son constat dans
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md` - elle seule peut confirmer que le
  message ne se declenche plus que pour l'evenement qu'elle a deja valide.

## Impact

- `docs/backlog/backlog.md`, sprint 16 : story `S16-1` ajoutee, avec un lien vers cette decision.
- `squad/decisions/produit/DEC-0004-textes-score-et-seuils-lever-refus-s01.md` : note ajoutee sous
  le point 3, renvoyant vers cette decision.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0036` ajoutee.
- `squad/programme/MATRICE-couverture.md` : inchangee, aucune ligne de programme concernee.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : **inchangee par cette decision**, le
  bloc de validation reste la main de `nadia-prof` seule, qui le completera apres avoir rejoue
  l'increment corrige.
