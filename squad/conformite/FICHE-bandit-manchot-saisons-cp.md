# Fiche de conformite - bandit-manchot-saisons-cp

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cp-qlm-saisons.yaml`
  (16 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-saisons-cp`, titre servi *"Les mois et les saisons"*,
  consigne servie *"Lis bien, puis nomme le mois ou la saison, ou dis lequel vient avant ou
  apres."*, `nombreDeManches: 10`.
- **Ligne declaree** : `QLM-CP-13`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du bloc
  de construction du sprint s41 (paquet et catalogue livres et fusionnes dans `round-two`, commit
  `b5b07966`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof` le
  remplisse.
- **Story** : `S41-QLM-SAISONS` - Nommer et ordonner les mois de l'annee et les saisons
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : questionner le monde, CP (cycle 2), domaine "Se situer dans le
  temps" - `squad/programme/questionner-le-monde/competences-cp.md`, `QLM-CP-13`. Ligne
  `non couvert` dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s29-le-prix-des-quatre-matieres-neuves-et-le-plafond-a-huit-
  lignes.md`, ligne 368) : *"rentable [...] `FER-1` sur les deux moities, pas sur les douze mois.
  `FER-3` : nommer, et ordonner ('quel mois vient apres... ?')."* Precise par
  `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md`, ligne 377 : *"Terrain deja
  eprouve par `cp-semaine`."*
- **Recouvrement verifie, aucun double comptage** : cette ligne ne recouvre ni les jours de la
  semaine (`cp-semaine`, mathematiques CP, `MAT-CP-09`), ni la journee - aucun item du paquet ne
  demande l'ordre des jours de la semaine ni de la journee (`squad/programme/questionner-le-
  monde/competences-cp.md`, section "Recouvrements a ne pas double-compter").
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-QLM-SAISONS`)

- Etant donne le paquet livre, quand on le relit dans son entier, alors les deux moities du
  libelle (nommer un mois ou une saison, ordonner deux mois ou deux saisons l'un par rapport a
  l'autre) sont chacune en bonne reponse sur des items distincts (`FER-1`). Aucune exigence de
  couvrir les douze mois individuellement par un item distinct.
- Etant donne un item de la moitie "nommer", quand il s'affiche, alors il demande le nom d'un mois
  ou d'une saison a partir d'une description ou d'une position (par exemple "quel mois vient apres
  juin ?").
- Etant donne un item de la moitie "ordonner", quand il s'affiche, alors il demande une relation
  d'ordre entre deux mois ou deux saisons (avant, apres), jamais uniquement leur nom.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres mois ou saisons reels et proches
  dans l'ordre du calendrier, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `QLM-CP-13` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme que les deux moities du libelle (nommer, ordonner) sont chacune tenues.

## Ce que cette story ne fait pas

Elle ne demande jamais l'ordre des jours de la semaine ni de la journee (deja porte par
`cp-semaine` et par mathematiques CP) ; elle ne fixe aucun contenu factuel precis - reste a
l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
