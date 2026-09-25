# Fiche de conformite - bandit-manchot-problemes-durees-ce1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-problemes-durees.yaml`
  (24 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-problemes-durees-ce1`, titre servi *"Le temps qui
  passe"*, consigne servie *"Trouve combien de temps s'est ecoule."*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-12`.
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s43 (paquet, catalogue et test livres et fusionnes dans
  `round-two`, commit `e980f31a`). Le bloc de validation pedagogique reste vide jusqu'a ce que
  `nadia-prof` le remplisse.
- **Story** : `S43-MAT-DUREE` - Calculer une duree simple en heures entieres ou en demi-heures
  (`docs/backlog/backlog.md`).
- **Competence programme visee** : mathematiques, CE1 (cycle 2), domaine "Grandeurs et mesures" -
  `squad/programme/mathematiques/competences-ce1.md`, `MAT-CE1-12`. Ligne `non couvert` dans
  `squad/programme/MATRICE-couverture.md` (ligne 678) au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router pour les conditions generales de la ligne**
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 5) : *"Verdict :
  ACCEPTE SOUS CONDITIONS `DUR-CE1-1` a `DUR-CE1-6`. Servable par du texte seul - c'est de
  l'arithmetique sur des instants, aucun cadran n'est requis - et la ligne peut atteindre
  `couvert-valide` sur un paquet unique."* Une seule forme est admise (section 5.1) : deux
  instants donnes, la duree a trouver - jamais l'horaire d'arrivee. Les six conditions,
  `DUR-CE1-1` a `DUR-CE1-6`, citees mot pour mot dans la story, portent sur la forme unique, les
  bornes des instants et des durees, l'ecriture distincte instant/duree, le franchissement de la
  demi-heure, les trois familles de distracteurs et les trois rangs de la bonne reponse, et
  l'enonce, les situations, l'indice et le volume du paquet.
- **Arbitrage prealable neuf, ajoute le 2026-09-25 par `nadia-prof` avant l'ecriture du paquet,
  discriminant avec `cm1-problemes-durees` deja servi** : `DUR-CE1-7`, cinq clauses (l'indice
  nomme le pas de comptage du CE1 et ne reprend aucun des quatre indices deja servis par le
  paquet de CM1 ; au moins un tiers des items porte une duree de deux heures ou plus ; aucun
  enonce ne reprend le couple prenom-situation d'un enonce du CM1 ; l'ecriture choisie laisse
  l'erreur de base 100 ecrivable ; au moins deux items portent un distracteur superieur a 59
  minutes), cite en entier dans la story. Ce bloc pose une barre avant le premier item et ne
  prononce aucun Done pedagogique.
- **Lien decision** : `DEC-0099` (section 9.5).
- **Derniere mise a jour** : 2026-09-25 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S43-MAT-DUREE`)

- Etant donne un item, quand il s'affiche, alors il donne deux instants et demande la duree
  ecoulee entre eux, jamais un horaire d'arrivee ou de depart a calculer, jamais une lecture
  d'heure seule, jamais une conversion d'unite de temps isolee, jamais une comparaison de durees
  (`DUR-CE1-1`).
- Etant donne les deux instants d'un item, quand on les relit, alors leurs minutes valent `00` ou
  `30` uniquement, ils appartiennent a la meme journee sans franchir minuit, aucune seconde
  n'apparait, l'ecriture est sur 24 heures (jamais "de l'apres-midi"), et la duree a trouver vaut
  au moins 30 minutes et au plus 4 heures (`DUR-CE1-2`).
- Etant donne le paquet livre, quand on compare l'ecriture des instants et celle des durees,
  alors les deux formes sont visiblement differentes et constantes dans tout le paquet (par
  exemple `14 h 30` pour un instant contre "2 heures et demie" pour une duree), et dans chaque
  item les trois propositions partagent la meme ecriture (`DUR-CE1-3`).
- Etant donne le paquet livre, quand on compte les items dont les deux instants ont des minutes
  differentes (`00` et `30`, franchissement d'une demi-heure), alors ils representent entre un
  tiers et deux tiers du paquet (`DUR-CE1-4`).
- Etant donne les distracteurs d'un item, quand on les verifie, alors chacun correspond a une
  erreur nommee dans le fichier a cote de l'item - au minimum parmi la demi-heure oubliee, le
  comptage en base 100 ou l'heure decalee d'un rang a minutes justes - et aucune proposition
  n'egale une autre ; sur le paquet entier, chacun des trois rangs de la bonne reponse porte au
  moins 10 % et au plus 50 % des items (`DUR-CE1-5`).
- Etant donne l'enonce d'un item, quand on le relit, alors il tient dans vingt mots et trois
  phrases au plus, la derniere etant la question (`LON-CE1-1`, `LON-CE1-2`, reprises par
  `DUR-CE1-6`), toutes les donnees necessaires y figurent, la situation ne suppose ni avion, ni
  depart en vacances, ni adulte qui conduit l'enfant quelque part, et aucun retard n'y est
  dramatise.
- Etant donne l'indice d'un item, quand il s'affiche, alors il donne une methode et jamais le
  resultat, et il n'est pas identique sur tout le paquet (`DUR-CE1-6`).
- Etant donne le paquet livre, quand on compte ses items, alors il en porte au moins vingt
  (`DUR-CE1-6`).
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `MAT-CE1-12` et uniquement elle.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` le joue, alors elle confirme
  que les six conditions `DUR-CE1-1` a `DUR-CE1-6` sont tenues et que la ligne passe a
  `couvert-valide`.
- **`DUR-CE1-7` (ajout du 2026-09-25), opposable au meme titre que les six conditions
  ci-dessus** : chaque indice nomme le pas de comptage du CE1 sans reprendre l'un des quatre
  indices deja servis par `cm1-problemes-durees` (clause 1) ; au moins un tiers des items porte
  une duree de deux heures ou plus (clause 2) ; aucun enonce ne reprend le couple prenom-situation
  d'un enonce du CM1 (clause 3) ; l'ecriture choisie laisse l'erreur de base 100 ecrivable
  (clause 4) ; si un distracteur superieur a 59 minutes est servi, au moins deux items en portent
  un (clause 5).

## Ce que cette story ne fait pas

Elle ne construit aucun rendu de cadran ni aucune lecture d'heure sur une horloge - c'est
`MAT-CE1-11`, hors de son lot et hors de cette story ; elle ne sert jamais la forme "instant de
depart + duree -> horaire d'arrivee", explicitement exclue par `nadia-prof` elle-meme et non
opposable ulterieurement comme un trou de couverture ; elle ne demande jamais une conversion
d'unite de temps isolee ni une comparaison de durees sans calcul ; elle ne fixe aucun contenu
factuel precis (quelles situations, quels instants retenus) - reste a l'auteur du paquet puis a
l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`.
