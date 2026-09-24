# Fiche de conformite - bandit-manchot-symboles-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-symboles.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-symboles-cp`, titre servi *"Les symboles de la
  Republique"*, consigne servie *"Lis bien, puis dis le nom du symbole, ou ce qu'il represente."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-08`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du bloc
  de construction du sprint s41 (paquet et catalogue livres et fusionnes dans `round-two`, commit
  `b5b07966`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof` le
  remplisse.
- **Story** : `S41-EMC-SYMBOLES` - Reconnaitre et nommer les symboles de la Republique francaise
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CP (cycle 2), domaine "Acquerir
  et partager les valeurs de la Republique" -
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`, `EMC-CP-08`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, ligne 234) : *"oui (lot a 1,5) [...] Connaissance exacte. `FER-1` : drapeau, Marianne,
  La Marseillaise, fete nationale, chacun en bonne reponse. Deux taches : nommer le symbole
  decrit ; dire ce qu'il represente. `ART-1` : aucune reponse portee par une couleur percue."*
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-EMC-SYMBOLES`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les quatre symboles du
  libelle (drapeau, Marianne, La Marseillaise, fete nationale) sont chacun en bonne reponse au
  moins une fois (`FER-1`).
- Etant donne un item, quand il s'affiche, alors il porte l'une des deux taches distinctes (nommer
  le symbole decrit ; dire ce qu'il represente), jamais uniquement la premiere sur tout le paquet
  (`FER-3`).
- Etant donne un item portant sur le drapeau, quand la question s'affiche, alors elle ne demande
  jamais la couleur d'un element (par exemple "de quelle couleur est le drapeau ?"), mais son nom,
  son hymne, sa devise ou sa date (`ART-1`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres symboles ou elements reels du
  registre republicain, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CP-08` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme que les quatre symboles sont chacun servis et qu'aucune reponse ne se porte par
  une couleur percue.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant une couleur percue en bonne reponse (`ART-1`) ; elle ne fixe
aucun contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
