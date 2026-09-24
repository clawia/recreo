# Fiche de conformite - bandit-manchot-classer-etres-vivants-cm1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cm1-sciences-classer-etres-vivants.yaml`
  (11 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-classer-etres-vivants-cm1`, titre servi *"Classer les
  etres vivants"*, consigne servie *"Lis bien, puis dis lequel des trois ne partage pas le meme
  caractere."*, `nombreDeManches: 10`.
- **Ligne declaree** : `SCI-CM1-06`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du bloc
  de construction du sprint s41 (paquet et catalogue livres et fusionnes dans `round-two`, commit
  `b5b07966`). Le bloc de validation pedagogique reste vide jusqu'a ce que `nadia-prof` le
  remplisse.
- **Story** : `S41-SCI-CLASSER` - Classer des etres vivants d'apres un caractere commun observable
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : sciences et technologie, CM1 (cycle 3), domaine "Le vivant, sa
  diversite et les fonctions qui le caracterisent" -
  `squad/programme/sciences-et-technologie/competences-cm1.md`, `SCI-CM1-06`. Ligne `non couvert`
  dans `squad/programme/MATRICE-couverture.md` au moment d'ouverture de cette fiche, deja notee
  "jouable au choix textuel, item par item. Mention exigee d'avance : 'raisonnement sur un
  caractere, item par item ; le tri d'une collection entiere reste non couvert' (refuse au palier
  1 par `V3`)."
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage neuf
  a router** (`ARBITRAGE-PREALABLE-s31-ce-qui-ferme-une-ligne-d-emc-cp-le-catechisme-civique-et-ma-
  borne.md`, section 4.3) : classee dans le rang "les suivantes", deuxieme rang de surete sur
  quatre parmi les lignes de sciences CM1 alors restantes - *"6 (classement, mention partielle
  due)"*. Le referentiel source precise, cite mot pour mot : *"'Lequel des trois n'a pas de
  plumes ?' travaille bien le caractere."*
- **`CYC-1` s'applique et se satisfait par construction** (`DEC-0095`, section 9.3 et 9.4) : cette
  ligne de cycle 3 porte sur le meme objet que `QLM-CP-01` (distinguer les etres vivants) et
  `QLM-CP-02` (caracteriser un etre vivant), deja `couvert-valide` et audites par la meme instance
  de `nadia-prof` (instance B). Aucun item de ce paquet ne doit contredire ce que ces deux paquets
  enseignent deja sur le meme objet.
- **Lien decision** : `DEC-0095`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S41-SCI-CLASSER`)

- Etant donne un item, quand il s'affiche, alors il demande de reconnaitre lequel de trois etres
  vivants decrits ne partage pas un caractere observable commun aux deux autres (par exemple
  presence de plumes, nombre de pattes, milieu de vie), jamais de trier ou de deplacer une
  collection entiere (`V3`).
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs caracteres
  observables distincts sont mobilises d'un item a l'autre, pas un seul caractere repete.
- Etant donne un item, quand il porte sur la diversite des especes d'un milieu, alors il decrit ce
  milieu et les especes qui l'occupent sans jamais contredire ce que `QLM-CP-01` et `QLM-CP-02`
  enseignent deja sur la distinction du vivant (`CYC-1`).
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres etres vivants reels partageant un
  caractere different, jamais des elements sans rapport avec le vivant.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `SCI-CM1-06` et uniquement elle, avec la mention de couverture partielle deja fixee
  ("raisonnement sur un caractere, item par item ; le tri d'une collection entiere reste non
  couvert") portee par la matrice au Done pedagogique.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucun item ne demande de trier une collection et qu'aucun item ne contredit
  `QLM-CP-01` ou `QLM-CP-02`.

## Ce que cette story ne fait pas

Elle ne construit aucune interaction de tri ou de deplacement d'une collection - hors de portee du
palier 1 (`V3`) ; elle ne fixe aucun contenu factuel precis (quels etres vivants, quels caracteres
retenir) - reste a l'auteur du paquet puis a l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`. La mention de couverture partielle deja fixee par la matrice
> ("raisonnement sur un caractere, item par item ; le tri d'une collection entiere reste non
> couvert") doit etre reprise mot pour mot dans le verdict si `nadia-prof` prononce un Done
> pedagogique.
