# Fiche de conformite - bandit-manchot-objets-numeriques-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-objets-numeriques.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-objets-numeriques-cp`, titre servi *"Les objets
  numeriques du quotidien"*, consigne servie *"Lis bien, puis dis quel objet est numerique, ou
  quelle regle simple d'usage s'applique."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-08`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s43 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `f1dac400`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof`
  le remplisse.
- **Story** : `S43-QLM-NUMERIQUE` - Reconnaitre les objets numeriques du quotidien et quelques
  regles simples de leur usage (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Le vivant, la
  matiere et les objets" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-08`.
  Ligne `non couvert` dans `squad/programme/MATRICE-couverture.md` (ligne 1586) au moment
  d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-lignes.md`,
  section 4.1) : *"**`QLM-CP-08`** - *objets numeriques et regles d'usage* | moyenne | Les regles
  d'usage au CP sont peu nombreuses ; risque de moraliser plutot que d'enseigner"*. Repris et
  precise par `DEC-0099` (section 5) : *"les regles d'usage au CP sont peu nombreuses ; risque de
  moraliser plutot que d'enseigner"* - a l'auteur du paquet de rester sur des faits verifiables
  (quel objet est numerique, quelle regle simple s'applique), jamais sur une injonction.
- **Lien decision** : `DEC-0099` (section 9.5).
- **Derniere mise a jour** : 2026-09-25 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S43-QLM-NUMERIQUE`)

- Etant donne un item, quand il s'affiche, alors il porte soit sur la reconnaissance d'un objet
  numerique du quotidien (par exemple une tablette, un ordinateur, un telephone, une console,
  face a un objet non numerique), soit sur une regle simple d'usage, sur le patron du libelle.
- Etant donne un item de regle d'usage, quand on relit son enonce et sa bonne reponse, alors ils
  enoncent un fait verifiable (quelle regle simple s'applique dans telle situation), jamais une
  injonction ni un jugement moral porte sur l'enfant (vigilance posee d'avance par `nadia-prof` :
  risque de moraliser plutot que d'enseigner).
- Etant donne le paquet livre, quand on le relit dans son entier, alors les deux moities du
  libelle (objets numeriques du quotidien, regles simples de leur usage) sont chacune servies par
  plusieurs items distincts.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres objets ou d'autres regles
  plausibles, jamais des elements sans rapport ni une formulation moralisatrice.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-08` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` le joue, alors elle confirme
  qu'aucun item ne moralise et que chaque regle d'usage se lit comme un fait verifiable.

## Ce que cette story ne fait pas

Elle ne demande jamais a l'enfant de manipuler un objet numerique reel - hors de portee du
palier 1 ; elle ne construit jamais une consigne de securite formulee comme un ordre adresse a
l'enfant (vigilance de `nadia-prof` contre la moralisation) ; elle ne fixe aucun contenu factuel
precis (quels objets, quelles regles retenues) - reste a l'auteur du paquet puis a l'audit de
`nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
