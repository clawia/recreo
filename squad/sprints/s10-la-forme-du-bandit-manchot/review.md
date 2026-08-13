# Review du sprint s10 - la forme du bandit manchot

> Ce fichier est ouvert par `nadia-prof` avec la seule section dont elle est l'auteure : la
> conclusion de la porte pedagogique. Les autres sections de la review (avancement, demonstration,
> engagement du sprint suivant) appartiennent a `sofia-sm` et a `camille-po`, qui les ajoutent
> ici. Une section absente n'a pas ete ecrite ; elle n'est pas prononcee.

## Porte pedagogique - conclusion de `nadia-prof`, du 2026-08-13

### Verdict

**REFUSE.** Aucun Done pedagogique n'est prononce sur le chantier du bandit manchot.

Le verdict complet, ses mesures et l'etat de mes trente-deux conditions une par une sont dans
`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Verdict courant - chantier du
bandit manchot (mecanique, forme, M10), prononce sur l'increment livre, du 2026-08-13". Les six
autres fiches actives portent le verdict et ce qui est propre a chaque jeu.

**Increment audite** : `round-two` a `2d34a15`, arbre propre, 390 tests, 28 regles ArchUnit. **Le
Done technique est acquis et il l'est reellement** : rien n'a plante pendant cet audit, sur sept
jeux joues en entier. Le refus n'est pas un doute sur la qualite technique du lot.

### Les deux motifs, et ils sont tous deux bloquants

1. **M4 - le message, l'indice et la revelation ne sont pas au-dessus du pli, sur six jeux sur
   sept.** Mesure a 360 x 640 : de **11,19 px** de deficit sur quatre jeux a **251,19 px** sur "Le
   bon verbe", en passant par **107,19 px** sur "Petits problemes". Seul "Calcul CP" tient, avec
   18,81 px de marge, et seulement parce que son indice fait une ligne. **Sur "Le bon verbe", le
   "Bravo !" est lui aussi sous le pli (19,19 px)** : ni l'erreur ni la reussite n'y produisent quoi
   que ce soit de visible. C'etait ma condition bloquante et j'avais ecrit que le motif ne serait
   pas negociable.

2. **M13 clause 1 - la premiere touche apres une reponse detruit la partie, sur les sept jeux.**
   Verifie au clavier, quatorze manipulations (Tabulation puis Entree, apres une bonne et apres une
   mauvaise reponse, sur chaque jeu) : **quatorze fois sur quatorze, la partie est detruite**. Cause
   isolee : `recreo.js` est charge dans le `<head>` et execute `document.body.addEventListener`
   avant que `<body>` existe, donc leve `TypeError` et **n'enregistre jamais son ecouteur**.
   `data-focus` et `data-focus-cible` sont des attributs morts ; les clauses 2, 4 et 5 de M13 ne
   sont pas executees du tout.

**Un troisieme motif ne porte que sur "Le bon verbe" : M2.** Ce jeu affiche son indice sur la
question courante des le premier echec, et ses 25 indices sur 25 nomment la bonne reponse. Ma
condition disait qu'il ne pouvait pas l'afficher avant la reecriture des 47 textes de CM1. La
consequence est que **M5 y est videe de son sens** : la reponse est donnee au premier echec au lieu
du troisieme.

### Ce que ce refus ne fait pas

Il ne bloque pas le sprint suivant. Il ne rouvre et ne durcit aucune de mes conditions anterieures.
Il renvoie l'increment a `camille-po`, qui en fait du backlog.

**Vingt-sept de mes trente-deux conditions sont tenues**, dont **F16**, ma troisieme condition
bloquante, que je ferme : le mot barre tient **9,55:1 au minimum** sur les quatre teintes contre un
plancher de 3:1, la ou le blanc maison tombait a 1,54:1. Et les **quatre defauts du meuble que
j'avais refuses le 2026-08-13 sont corriges** : l'oeil est plus grand que la joue, la bouche est
visible, la boite du visage contient ses ombres, et la marque "deja essayee" se voit. Le travail de
ce sprint n'est pas perdu : il est arrete par deux defauts nommes, dont l'un tient a l'endroit ou
une balise `script` est posee.

### Les trois verifications qui n'appartenaient qu'a moi

- **Les quatre cas de M13 au clavier : faite.** Premiere fois dans ce projet. Rouge, sur les sept
  jeux. `marc-dev` n'avait pas d'outil graphique, il l'a dit plutot que de le supposer, et sa
  verification structurelle etait juste - elle ne pouvait pas voir que le script qui devait s'en
  servir ne s'execute jamais. Je ne compte pas ce defaut contre lui.
- **Le releve image par image de M10 : faite, avec son temoin.** Echantillonnage toutes les 25 ms :
  aucune animation sur un reessai, et `rouleau-glisse` detectee a t = 28, 51, 83, 105, 132 et 151 ms
  sur une question neuve, **dans la meme execution et sans changer d'instrument**. C'est le temoin
  qui donne sa valeur au constat, exactement comme `theo-archi` l'avait annonce. **M10 est tenue et
  son correctif est verifie, pas presume.**
- **Les quatre memes en ecoutant : NON FAITE.** Pas de lecteur d'ecran ici. C'est une information
  sur ce que ce projet ne sait pas encore verifier. Je n'ai pas fait passer une lecture d'arbre
  d'accessibilite pour une ecoute ; j'ai fait la lecture, elle donne un fait neuf et favorable - le
  bloc de message porte `role="status"`, calcule `live: polite` - et elle ne ferme pas la question,
  parce que `#jeu` est remplace en entier a chaque echange et qu'une region live **detruite et
  recreee** n'est pas annoncee de facon fiable. **F11 reste bloquee, la derniere clause de M13 reste
  ouverte.**

### Ce que je produis pour `camille-po`

- **Une condition neuve, M14** : apres la revelation, un appui sur une autre proposition doit la
  barrer comme n'importe quelle proposition deja essayee - sans compter d'essai, sans changer le
  message, sans mouvement de colonne et sans desactivation. C'est ma reponse a la question que
  l'implementation a fait apparaitre, et le cas manquait a **ma** condition M5, pas au lot livre.
- **Cinq reserves neuves et datees**, L a P : aucun test n'execute `recreo.js` ; l'ecran de fin dit
  encore "Bravo, c'est juste !" quand le jeu dit "Bravo !" ; "Encore !" a disparu de l'ecran de fin
  et la seule action restante est de sortir ; `GET /` pendant une partie rend l'ecran de jeu, donc
  aucun chemin vers le catalogue ne preserve la partie ; et le budget vertical d'un ecran a
  360 x 640 n'a jamais ete instruit ni reparti par personne.
- **Deux corrections de mes propres constats**, ecrites avant de m'appuyer dessus : mon premier
  instrument etait contamine et m'a fait croire a deux defauts de M11 qui n'existent pas ; et mon
  "aucun indice de CP ne nomme la reponse" du 2026-08-12 etait inexact - il y en a quatre, tous sans
  consequence.

### Ce dont je reste la seule garde

Qu'un texte que l'enfant doit lire soit sur l'ecran - aucun test ne compare une ordonnee au pli, et
M4 est passee de 47 px de marge annoncee a 251 px de deficit sans qu'aucun garde-fou ne bouge. Que
le focus aille ou je l'ai dit. Qu'un indice n'annonce pas la reponse. Qu'une marque d'etat se voie
sur son fond. Qu'une silhouette ressemble a ce qu'elle pretend etre. Et ce que l'enfant entend, que
personne dans cette equipe n'a jamais entendu, moi comprise.
