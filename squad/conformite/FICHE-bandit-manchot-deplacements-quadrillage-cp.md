# Fiche de conformite - bandit-manchot-deplacements-quadrillage-cp

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/cp-deplacements-quadrillage.yaml`
  (12 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-deplacements-quadrillage-cp`, titre servi *"Decrire un
  deplacement"*, consigne servie *"Lis bien, puis dis quel geste est decrit."*,
  `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CP-10`, **couverture partielle des l'origine** (voir ci-dessous).
- **Statut global** : **EN ATTENTE D'AUDIT.** Fiche ouverte par `camille-po` a l'ouverture du
  bloc de construction du sprint s42 (paquet et catalogue livres et fusionnes dans `round-two`,
  commit `00952c9a`, contenu au commit `051ba2bb`). Le bloc de validation pedagogique reste vide
  jusqu'a ce que `nadia-prof` le remplisse.
- **Story** : `S42-MAT-TEXTUEL` - Se reperer et decrire des deplacements sur un quadrillage
  simple, moitie textuelle seulement (`docs/backlog/backlog.md`).
- **Competence programme visee** : mathematiques, CP (cycle 2), domaine "Espace et geometrie" -
  `squad/programme/mathematiques/competences-cp.md`, `MAT-CP-10`. Ligne `non couvert` dans
  `squad/programme/MATRICE-couverture.md` (ligne 227) au moment d'ouverture de cette fiche.
- **Arbitrage pedagogique prealable deja rendu, cite en entier dans la story, aucun arbitrage
  neuf a router**
  (`ARBITRAGE-PREALABLE-s32-trois-instances-ma-borne-a-douze-le-terrain-sans-image-et-le-prix-d-une-
  ligne-marginale.md`, section 17.7) : *"Oui, `MAT-CP-10` demande le palier 2, et pour une moitie
  seulement. Se reperer et se deplacer sur un quadrillage est une designation sur un dessin et
  rien d'autre ne la sert ; decrire un deplacement n'est pas une designation et se sert en choix
  textuel, dans un second paquet. La ligne ne ferme donc pas par le palier 2 seul, et la mention
  [...] est due."* Repris sans reouverture par `DEC-0097` (section 6) et `DEC-0098` (section 5) :
  cette story ne couvre que la moitie *decrire un deplacement*, en choix textuel ; la moitie *se
  reperer et se deplacer* (designation sur quadrillage, palier 2) reste `non couvert`.
- **Ecart de tenue signale par la story, non corrige par cette fiche** : `MATRICE-couverture.md`,
  ligne 227, porte a l'ouverture de cette fiche une mention de couverture partielle dont la
  formulation inverse les deux moities par rapport a la source citee ci-dessus (elle attribue la
  couverture a *"se reperer et se deplacer"* et laisse *"decrire un deplacement"* non couverte,
  alors que `ARBITRAGE-PREALABLE-s32`, section 17.7, dit l'inverse). La correction de cette
  cellule est hors du perimetre de cette fiche.
- **Lien decision** : `DEC-0098`.
- **Derniere mise a jour** : 2026-09-24 (ouverture, `camille-po`).

---

## Criteres d'acceptation de la story (`docs/backlog/backlog.md`, `S42-MAT-TEXTUEL`)

- Etant donne un item, quand il s'affiche, alors il demande de decrire en mots un deplacement sur
  un quadrillage (par exemple avancer, reculer, tourner a gauche, tourner a droite), en choix
  textuel, jamais une designation d'une case ou d'un point sur une image (palier 2, hors de portee
  ici).
- Etant donne le paquet livre, quand on le relit dans son entier, alors plusieurs formes de
  deplacement distinctes (avancer, reculer, tourner a gauche, tourner a droite) sont chacune en
  bonne reponse au moins une fois.
- Etant donne cette manche, quand les propositions s'affichent, alors la bonne reponse y figure
  exactement une fois, avec des distracteurs qui sont d'autres deplacements plausibles sur un
  quadrillage, jamais des elements sans rapport.
- Etant donne chaque item du paquet, quand il declare sa ligne de referentiel, alors il porte
  `MAT-CP-10` et uniquement elle, avec la mention de couverture partielle exacte portee au Done
  pedagogique : *"moitie 'decrire un deplacement' seule, en choix textuel ; la moitie 'se reperer
  et se deplacer' (designation sur quadrillage, palier 2) reste non couverte"* - direction
  verifiee contre `ARBITRAGE-PREALABLE-s32`, section 17.7.
- Etant donne le Done pedagogique de ce paquet, quand `nadia-prof` (instance B) le joue, alors
  elle confirme qu'aucun item ne demande une designation sur l'image et que la ligne passe de
  `non couvert` a `en cours`, pas a `couvert-valide`.

## Ce que cette story ne fait pas

Elle ne construit aucune interaction de designation sur un quadrillage (palier 2, hors de portee
au palier 1) ; elle ne fait pas passer `MAT-CP-10` a `couvert-valide` - seule la moitie textuelle
est servie ; elle ne corrige pas la mention actuellement ecrite dans `MATRICE-couverture.md`,
ligne 227, dont la formulation est inversee par rapport a la source citee - signale ci-dessus,
pas corrige ici ; elle ne fixe aucun contenu factuel precis - reste a l'auteur du paquet puis a
l'audit de `nadia-prof`.

---

## Bloc de validation pedagogique

> Non rempli. A completer par `nadia-prof` seule, apres avoir joue l'increment (voir
> `squad/README.md`, section 7 - Double Done). Trois verdicts possibles : `VALIDE`,
> `VALIDE SOUS RESERVE`, `REFUSE`. **Rappel de la story** : meme si le verdict est `VALIDE`, la
> ligne `MAT-CP-10` ne passe qu'a `en cours` - jamais a `couvert-valide` sur cette seule moitie.
