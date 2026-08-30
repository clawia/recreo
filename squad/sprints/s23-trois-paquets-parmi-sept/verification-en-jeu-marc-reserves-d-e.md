# Verification en jouant - reserves D et E d'ETA, garde-fou RAN-7, marc-dev, 2026-09-01

Base : `round-two` a `96083ba` (801 tests), worktree dedie `s23-marc-3`, branche `s23-marc-3`.
Methode identique aux verifications precedentes du sprint : fat jar construit par
`bash outils/mvn-jdk21.sh -B clean verify` (reacteur complet, BUILD SUCCESS), lance en local sur
le JDK 21 de la toolchain declaree, joue par requetes HTTP directes (`curl`, cookie de session)
sur les routes reelles de `PartieController` - pas de navigateur graphique disponible dans cet
environnement.

**`cp-ranger-nombres` n'a pas ete rejoue** : le paquet est `VALIDE` sans reserve depuis le reaudit
de nadia-prof, et la seule modification de ce lot porte sur le test qui garde `RAN-7`
(`PaquetCpRangerNombresTest`), pas sur le contenu servi a l'enfant. Rejouer le paquet n'aurait
rien montre de neuf a l'ecran.

## `cm1-problemes-etapes` - reserve D (indices alignes sur "perd")

Verifie a l'ecran, sur des parties jouees en pressant une proposition fausse pour lire l'indice
servi :

- item `La reserve a 40 cahiers, en perd 15...` : indice rendu *"Soustrais d'abord les cahiers
  perdus en debut d'annee."* (avant : *"...distribues..."*).
- item `L'ecole a 50 chaises, en perd 22...` : indice rendu *"Soustrais d'abord les chaises
  perdues pour l'autre activite."* (avant : *"...retirees..."*).
- item `La classe a 35 crayons en reserve, en perd 13...` : indice rendu *"Soustrais d'abord les
  crayons perdus le premier jour."* (avant : *"...vendus..."*).

Les trois indices nomment desormais exactement le verbe de leur enonce.

## `cm1-problemes-etapes` - reserve E (les six items d'ambiguite "N groupes")

Verifie a l'ecran, les six items rencontres :

- item `La classe fabrique 34 bracelets...` : *"3 classes, celle-ci comprise, fabriquent..."*
- item `L'ecole imprime 12 affiches...` : *"4 ecoles, celle-ci comprise, impriment..."*
- item `Le club prepare 18 medailles...` : *"2 clubs, celui-ci compris, preparent..."* (la
  precedente mention "lors du tournoi" a ete retiree pour tenir `ETA-3` apres l'ajout)
- item `L'atelier de poterie fabrique 75 bols...` (deja corrige au tour precedent) : *"3 ateliers,
  celui-ci compris, fabriquent..."* - inchange
- item `L'atelier de couture prepare 50 coussins...` : *"4 ateliers, celui-ci compris,
  preparent..."* - le groupe est desormais nomme du meme mot que le sujet ("atelier"), le pronom
  "celui-ci" a donc un antecedent
- item `L'atelier de peinture prepare 90 affiches...` : *"2 ateliers, celui-ci compris,
  preparent..."* - meme correction

Les six items ne se lisent plus que d'une seule facon.

## Ce que cette verification ne re-couvre pas

Elle ne rejoue pas les items non touches par ce lot (deja verifies aux tours precedents du
sprint). Elle ne mesure pas la hauteur utile ni l'orthographe (`MNC-1`, `relecteur-pedagogique`) :
hors du mandat de ce lot, nommes par nadia-prof comme non instruits.
