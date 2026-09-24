# Fiche de conformite - bandit-manchot-couleurs-arts-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-arts-couleurs.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-couleurs-arts-cp`, titre servi *"Les couleurs et les
  melanges"*, consigne servie *"Dis le nom de la couleur, ou ce qu'on obtient en melangeant deux
  couleurs."*, `nombreDeManches: 10`.
- **Ligne declaree** : `ART-CP-05`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `9a2aae8a`, contenu au commit `00837889`). Le bloc de validation pedagogique reste vide
  jusqu'a ce que `nadia-prof` le remplisse.
- **Story** : `S42-ART-COULEURS` - Reconnaitre et nommer les couleurs, distinguer les couleurs
  primaires de celles obtenues par melange (`docs/backlog/backlog.md`).
- **Competence programme visee** : arts plastiques, CP (cycle 2), domaine "L'expression des
  emotions" - `squad/programme/arts-plastiques/competences-cp.md`, `ART-CP-05`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1741) au moment d'ouverture de
  cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`squad/programme/arts-plastiques/competences-cp.md`, Jugement de faisabilite,
  `nadia-prof`, 2026-09-03, ligne 5) : *"Reconnaitre et nommer les couleurs, primaires et melanges
  | jouable au choix textuel, sous `ART-1` | Le melange des couleurs est une connaissance :
  'Quelle couleur obtient-on en melangeant le bleu et le jaune ?' se pose en trois propositions
  ecrites, se verifie, et ne demande a l'enfant de percevoir aucune couleur."* Condition
  opposable associee, citee mot pour mot : `ART-1` *"Aucun item d'arts plastiques ne fait porter
  la reponse par une couleur percue. Pas de pastille coloree, pas de 'de quelle couleur
  est... ?' sur une image. La ligne 5 se sert par le nom ecrit des couleurs et par la regle de
  melange."* Mention exigee d'avance si la ligne bouge : *"vocabulaire et connaissances de la
  pratique plastique seuls ; aucune production plastique n'est servie par ce produit"*.
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-ART-COULEURS`)

- Etant donne un item, quand il s'affiche, alors la reponse se porte par le nom ecrit d'une
  couleur ou par une regle de melange (par exemple "quelle couleur obtient-on en melangeant le
  bleu et le jaune ?"), jamais par une pastille coloree ni par une question du type "de quelle
  couleur est... ?" sur une image (`ART-1`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors les trois couleurs
  primaires (bleu, jaune, rouge) et au moins trois melanges reels (par exemple bleu + jaune =
  vert) sont chacun en bonne reponse au moins une fois.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres couleurs reelles, jamais des
  elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `ART-CP-05` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucune reponse ne se porte par une couleur percue et que la matrice porte la
  mention *"vocabulaire et connaissances de la pratique plastique seuls ; aucune production
  plastique n'est servie par ce produit"*.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant de percevoir une couleur affichee pour repondre (`ART-1`) ; elle
ne fixe aucun contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
