# DEC-0014 - Retrait de "Le bon verbe" du catalogue jusqu'a sa correction

- **Type** : perimetre produit, retrait temporaire d'un jeu du catalogue
- **Date** : 2026-08-10
- **Auteur** : camille-po
- **Sprint** : s06 (cloture) / s07 (ouverture)
- **Competence programme visee** : francais, CM1 (cycle 3), domaine "Orthographe et grammaire" -
  "Conjuguer les verbes frequents au present, futur et imparfait de l'indicatif"
  (`squad/programme/francais/competences-cm1.md`).
- **Statut** : Acceptee

## Contexte

`nadia-prof` a prononce **REFUSE** sur `bandit-manchot-conjugaison-present-cm1`
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, audit du 2026-08-10). Motif
unique et suffisant : cinq des douze indices du paquet enoncent une regle de conjugaison fausse
("Avec 'je', le verbe se termine par '-e' au present"), et trois d'entre elles sont dementies par
un autre item du meme paquet - `nadia-prof` a lu en une seule partie l'indice de la manche 7
("Avec 'tu', le verbe se termine par '-es'") puis recu la manche 10 ("Tu ___ ton exercice",
reponse `finis`).

Ce jeu est neanmoins dans `adapters/adapter-content/src/main/resources/catalogue.yaml` sur
`round-two` (`be60b10`) et jouable par un enfant qui lance l'application aujourd'hui, exactement
dans l'etat que `nadia-prof` a refuse.

## Decision

**"Le bon verbe" est retire du catalogue jouable jusqu'a ce qu'un increment corrige repasse
devant `nadia-prof` et obtienne un Done pedagogique.** Meme traitement que `DEC-0009` pour "Le bon
son", pour le meme motif de principe.

## Justification

Le principe du double Done (`CLAUDE.md`) dit qu'un increment techniquement irreprochable peut
etre refuse pedagogiquement, et que les deux sont independants. Un increment refuse qui reste
accessible a un enfant rend cette independance sans consequence pratique.

Le motif de refus rend ce retrait d'autant moins negociable qu'il porte sur le seul endroit du jeu
ou l'on enseigne quelque chose. `nadia-prof` l'ecrit elle-meme : l'indice n'apparait qu'apres trois
echecs consecutifs, donc il est servi a l'enfant qui vient de se tromper trois fois de suite -
celui qui est le moins en etat de reperer que l'aide est fausse. Ce que cet enfant emporte de la
partie, ce sont des formes comme *"je prende"*, *"il alle"*, *"je faie"* : la faute que le jeu lui
apprend est exactement celle qu'il existe pour prevenir. Laisser ce jeu accessible pendant sa
correction reviendrait a enseigner une regle fausse a chaque enfant qui l'ouvrirait d'ici la.

## Ce que cette decision ne fait pas

- Elle ne supprime ni le paquet de contenu
  (`adapters/adapter-content/src/main/resources/contenu/cm1-conjugaison-present.yaml`) ni la fiche
  de conformite ni l'historique du refus : ils restent la matiere de travail de la story de
  correction. `nadia-prof` ecrit elle-meme que la correction est petite et que ses trois modeles
  sont deja dans le paquet (temperer comme aux items 2 et 3, nommer le groupe comme le permettrait
  le premier groupe, nommer le verbe comme aux items 8, 10 et 11) : je n'en tire aucune conclusion
  sur le calendrier, cette question de capacite appartient a `sofia-sm`.
- Elle ne prejuge pas du verdict que `nadia-prof` rendra sur l'increment corrige.
- Elle ne tranche aucune question technique sur la maniere de retirer le jeu du catalogue
  (suppression de la ligne, drapeau de desactivation, autre mecanisme) : ce choix appartient a
  `theo-archi` et aux developpeurs, comme pour `DEC-0009`.
- Elle ne change pas le statut de la ligne de matrice associee : celle-ci reste `en cours`, comme
  elle l'etait avant cette decision et avant meme cet audit (`nadia-prof` note explicitement que la
  ligne "ne pouvait de toute facon pas atteindre `couvert-valide` sur cet increment", le paquet ne
  couvrant qu'un temps sur trois - `DEC-0013`).
- Elle ne retire aucun autre jeu ni ne touche a la mecanique bandit manchot : les autres jeux du
  catalogue continuent de fonctionner sans changement, y compris "Le bon son"
  (`bandit-manchot-le-bon-son-cp`), dont le REFUSE distinct est leve le meme jour
  (`squad/conformite/FICHE-bandit-manchot-le-bon-son-cp.md`) et qui peut rejoindre le catalogue
  independamment de cette decision.

## Impact

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : story de correction des cinq
  indices fautifs, criteres repris au mot pres du motif de refus, non affectee a un sprint - c'est
  `sofia-sm` qui juge, avec `theo-archi`, si l'effort tient dans la fenetre du sprint en cours au
  vu de sa taille annoncee comme petite par `nadia-prof`.
- `squad/programme/MATRICE-couverture.md` : note de la ligne francais CM1 (conjugaison) mise a
  jour pour refleter le refus et ce retrait.
- `squad/validation/FILE-ATTENTE.md` : `bandit-manchot-conjugaison-present-cm1` entre dans la file
  comme increment refuse en attente de reprise.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0014 ajoutee.

## Ce qui reste non testable par construction

- Que le retrait effectif du catalogue soit la bonne reponse produit plutot que, par exemple, une
  correction acceleree livree avant tout retrait, est un jugement d'opportunite de ma part : je le
  retiens parce que le motif touche directement ce que l'enfant apprend (une regle de grammaire
  fausse, au seul endroit du jeu qui enseigne), exactement comme pour `DEC-0009`, et qu'aucune
  correction n'est encore livree a la date de cette decision.
- Que l'increment corrige leve effectivement ce motif releve du seul jugement de `nadia-prof` a son
  prochain audit ; cette decision ne prejuge pas de ce verdict.
