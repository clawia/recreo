# Fiche de conformite - bandit-manchot-matiere-objets-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-matiere.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-matiere-objets-cp`, titre servi *"La matiere des
  objets"*, consigne servie *"Lis bien, puis dis en quelle matiere est fait l'objet, ou trie les
  matieres selon leurs proprietes."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-06`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `cdf0229f`, contenu au commit `cbe35d0b`). Le bloc de validation pedagogique reste vide
  jusqu'a ce que `nadia-prof` le remplisse.
- **Story** : `S42-QLM-MATIERE` - Identifier de quelle matiere un objet est fait et trier des
  materiaux selon une propriete simple (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Le vivant, la
  matiere et les objets" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-06`.
  Ligne `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1564) au moment
  d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, section 7.1,
  ligne 6) : *"6 matiere d'un objet | oui, choix ; plus juste en illustre | Le nom de l'objet dans
  l'enonce donne souvent la matiere ('une cuillere en bois'). Distracteurs a surveiller."*
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-QLM-MATIERE`)

- Etant donne un item, quand il s'affiche, alors il demande d'identifier la matiere d'un objet
  (bois, metal, plastique, verre, tissu...) ou de trier des materiaux selon une propriete simple
  (dur/mou, qui flotte/qui coule...), sur le patron du libelle.
- Etant donne l'enonce d'un item, quand on le relit, alors le nom de l'objet ne revele pas la
  matiere attendue avant reponse (vigilance posee d'avance par `nadia-prof` : le nom de l'objet
  donne souvent la matiere, par exemple "une cuillere en bois").
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres materiaux reellement plausibles
  pour l'objet decrit, jamais des elements sans rapport.
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs materiaux
  distincts (au moins bois, metal, plastique, verre) sont chacun en bonne reponse au moins une
  fois.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-06` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance A) le joue, alors
  elle confirme qu'aucun enonce ne revele la matiere par le nom de l'objet et que les distracteurs
  sont des materiaux plausibles.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant de manipuler ou de toucher un materiau reel - hors de portee du
palier 1 ; elle ne fixe aucun contenu factuel precis (quels objets, quels materiaux retenus) -
reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
