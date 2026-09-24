# Fiche de conformite - bandit-manchot-roles-ecole-commune-cp

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cp-emc-roles-ecole-commune.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-roles-ecole-commune-cp`, titre servi *"Les roles de
  l'ecole et de la commune"*, consigne servie *"Lis bien, puis dis a qui s'adresser, ou ce que
  fait cette personne."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CP-13`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du bloc
  de construction du sprint s41 (paquet et catalogue livres et fusionnes dans `round-two`, commit
  `b5b07966`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof` le
  remplisse.
- **Story** : `S41-EMC-ROLES` - Identifier quelques roles et services de l'ecole et de la commune
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CP (cycle 2), domaine
  "Construire une culture civique" -
  `squad/programme/enseignement-moral-et-civique/competences-cp.md`, `EMC-CP-13`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, ligne 237) : *"oui (lot a 1,5) [...] Connaissance verifiable. Deux taches : a qui
  s'adresse-t-on pour telle chose ; que fait telle personne ou tel service. `EMC-6` : aucun item ne
  suppose la commune, l'ecole ou le foyer de l'enfant. Le produit ne les connait pas et ne peut
  pas les connaitre (`DEC-0028`)."*
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-EMC-ROLES`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les roles et services du
  libelle (directeur, mairie, pompiers, medecin) sont chacun en bonne reponse au moins une fois
  (`FER-1`).
- Etant donne un item, quand il s'affiche, alors il porte l'une des deux taches distinctes (a qui
  s'adresse-t-on pour telle situation ; que fait telle personne ou tel service), jamais uniquement
  la premiere sur tout le paquet (`FER-3`).
- Etant donne un item, quand la situation s'affiche, alors elle ne suppose jamais une commune, une
  ecole ou une configuration familiale particuliere de l'enfant (`EMC-6`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres roles ou services reels, jamais des
  elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CP-13` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme que les roles et services du libelle sont chacun servis et qu'aucun item ne
  suppose la commune ou l'ecole de l'enfant.

## Ce que cette story ne fait pas

Elle ne suppose jamais la commune, l'ecole ou le foyer de l'enfant (`EMC-6`) ; elle ne fixe aucun
contenu factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
