# Fiche de conformite - bandit-manchot-droits-enfant-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-emc-droits-enfant.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-droits-enfant-cp`, titre servi *"Les droits de
  l'enfant"*, consigne servie *"Lis bien, puis dis quel droit c'est, ou ce qu'il garantit."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-10`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du bloc
  de construction du sprint s41 (paquet et catalogue livres et fusionnes dans `round-two`, commit
  `b5b07966`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof` le
  remplisse.
- **Story** : `S41-EMC-DROITS` - Identifier les principaux droits de l'enfant
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CP (cycle 2), domaine "Acquerir
  et partager les valeurs de la Republique" -
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`, `EMC-CP-10`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, ligne 236) : *"oui, et c'est le terrain le plus sur des cinq a 1,5. `FER-1` : etre
  protege, etre soigne, aller a l'ecole, jouer. Deux taches : reconnaitre le droit dont parle une
  situation ; dire ce que le droit garantit. `BIA-4` et `EMC-1` : aucun item n'interroge l'enfant
  sur sa propre situation. 'As-tu le droit d'aller a l'ecole ?' est refuse d'avance."* Ligne
  evaluee par `nadia-prof` elle-meme comme la moins risquee des cinq lignes d'EMC CP a ce tarif.
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-EMC-DROITS`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les quatre droits du
  libelle (etre protege, etre soigne, aller a l'ecole, jouer) sont chacun en bonne reponse au
  moins une fois (`FER-1`).
- Etant donne un item, quand il s'affiche, alors il porte l'une des deux taches distinctes
  (reconnaitre le droit dont parle une situation decrite ; dire ce que le droit garantit), jamais
  uniquement la premiere sur tout le paquet (`FER-3`).
- Etant donne un item, quand la situation ou la question s'affiche, alors elle ne s'adresse jamais
  directement a l'enfant sur sa propre situation (par exemple "as-tu le droit de...") mais decrit
  un personnage ou une situation generale (`BIA-4`, `EMC-1`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres droits ou notions reels, jamais des
  elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CP-10` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme que les quatre droits sont chacun servis et qu'aucun item n'interroge l'enfant sur
  sa propre situation.

## Ce que cette story ne fait pas

Elle n'interroge jamais l'enfant sur sa propre situation (`BIA-4`, `EMC-1`) ; elle ne fixe aucun
contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
