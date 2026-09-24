# Fiche de conformite - bandit-manchot-symboles-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-emc-symboles.yaml`
  (13 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-symboles-cm1`, titre servi *"L'histoire des symboles
  de la Republique"*, consigne servie *"Lis bien, puis dis d'ou vient ce symbole, ou on le voit,
  ou ce que c'est."*, `nombreDeManches: 10`.
- **Ligne declaree** : `EMC-CM1-05`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `9a2aae8a`, contenu au commit `00837889`). Le bloc de validation pedagogique reste vide
  jusqu'a ce que `nadia-prof` le remplisse.
- **Story** : `S42-EMC-SYMBOLES-CM1` - Reconnaitre les symboles de la Republique francaise et dire
  ce qu'ils representent (`docs/backlog/backlog.md`).
- **Competence programme visee** : enseignement moral et civique, CM1 (cycle 3), domaine
  "Acquerir et partager les valeurs de la Republique" -
  `squad/programme/enseignement-moral-et-civique/competences-cm1.md`, `EMC-CM1-05`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 2002) au moment d'ouverture de
  cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`squad/programme/enseignement-moral-et-civique/competences-cm1.md`, Jugement de
  faisabilite, `nadia-prof`, 2026-09-03, ligne 5) : *"Symboles de la Republique | non couvert -
  jouable au choix textuel, sous `ART-1` | Connaissance exacte et verifiable, terrain sur. La
  reponse ne se porte jamais par une couleur percue : on demande le nom, la devise, la date, la
  figure - jamais 'de quelle couleur est... ?', qui exclut un enfant daltonien et ne teste pas le
  symbole."* S'ajoutent les conditions valables sur toute la matiere, citees mot pour mot
  (introduction du meme document) : *"`ART-1` (aucune reponse portee par une couleur percue)
  s'applique a la ligne des symboles. `HIS-2` et `HIS-3` s'appliquent : cette matiere porte des
  faits institutionnels dont je ne suis pas la source, et une erreur y est un motif de refus a
  elle seule."*
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-EMC-SYMBOLES-CM1`)

- Etant donne un item, quand il s'affiche, alors la reponse se porte par le nom, la devise, la
  date ou la figure du symbole, jamais par une couleur percue (jamais "de quelle couleur est le
  drapeau ?") (`ART-1`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors les cinq symboles du
  libelle (drapeau, devise, Marianne, hymne, fete nationale) sont chacun en bonne reponse au
  moins une fois.
- Etant donne chaque fait institutionnel du paquet, quand il est livre, alors sa source figure
  dans le commentaire du paquet, et aucun fait n'est errone (`HIS-3`).
- Etant donne chaque item, quand on verifie sa reponse, alors une seule des trois propositions
  est defendable (`HIS-2`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres symboles ou elements reels du
  registre republicain, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `EMC-CM1-05` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme que les cinq symboles sont chacun servis, qu'aucune reponse ne se porte par une
  couleur percue, et que la matrice porte la mention *"connaissances civiques et reconnaissance
  de situations seules ; aucun comportement, aucun engagement et aucune participation a une
  decision collective ne sont servis par ce produit"*.

## Ce que cette story ne fait pas

Elle ne demande jamais une couleur percue en bonne reponse (`ART-1`) ; elle ne fixe aucun contenu
factuel precis - reste a l'auteur du paquet puis a l'audit de `nadia-prof`, qui verifie
l'exactitude de chaque fait institutionnel cite (`HIS-3`).

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
