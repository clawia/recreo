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
## Porte pedagogique - reponse de principe du 2026-08-13 : le lieu de l'indice

> Cette section est la reponse a une question de principe posee apres mon refus. **Elle ne contient
> aucun Done pedagogique et elle ne leve rien** : mon refus du 2026-08-13 sur M4 et sur M13 clause 1
> tient entier. La reponse complete est dans
> `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Reponse de principe - ce que le
> lieu 'sous le meuble' portait pour l'indice (M1), du 2026-08-13".

### La question et la reponse

**Question** : ma condition M1 place l'indice sous le meuble, des le premier echec, sur la question
qui bloque. Le lieu portait-il quelque chose, ou seul le rattachement comptait ?

**Reponse** : **le lieu portait quatre choses, et aucune des quatre n'est ce que M1 protege.**
L'indice peut donc remonter au-dessus du meuble sans qu'aucune de mes exigences pedagogiques ne cede.

| Ce que le lieu portait | A qui cela appartient |
|---|---|
| rien ne bouge au-dessus du point ou l'enfant appuie (65 a 155 px de deplacement des colonnes dans l'autre position) | **M8** |
| un seul endroit ou le jeu parle | **M6 clause 1** |
| l'ordre vertical : le verdict au-dessus de l'aide, donc le premier a echapper au pli | **M4** |
| des pixels | **personne - le lieu n'en porte aucun** |
| le rattachement a la question qui bloque, et le premier echec comme moment | **M1, et c'est toute sa substance** |

**Le fait qui tranche** : avant ce sprint l'indice etait deja au-dessus du meuble. Ce que j'ai refuse
le 2026-08-12 n'etait pas sa position, c'etait qu'il n'atteignait pas l'enfant bloque - *"un enfant
qui rate neuf manches sur dix recoit deux aides, et les deux portent sur le seul item qu'il avait
reussi."* **C'est le rattachement qui faisait le travail, et il ne se paie pas en geometrie.** Le
retour au lieu d'avant ce sprint n'est pas un retour a l'etat d'avant ce sprint.

### M6 s'y oppose, et c'est M6 qui cede

Oui, elle s'y oppose : j'avais enumere l'indice moi-meme dans sa clause 1. **Elle cede, et pas parce
que la geometrie l'exige - parce que mon enumeration etait fausse.** M6 parle de ce que le jeu dit de
la **reponse de l'enfant** ; l'indice parle de la **question**. Verifie ce jour sur les **146 indices
des huit paquets servis, un par un : aucun** ne mentionne ce que l'enfant a presse. J'avais range
l'indice avec le verdict parce qu'il arrive au meme moment, et arriver au meme moment n'est pas parler
de la meme chose.

**La clause 2 n'est pas touchee** - le meuble rigoureusement identique dans les quatre etats, que
j'ai appelee le point de garde le plus important de ce chantier, et qui est tenue au pixel dans
l'increment livre.

### M4 ne retrecit pas a deux elements : elle se scinde

Sa phrase contient une **barre** (trois elements au-dessus du pli) et un **lieu** (sous le meuble).
**Le lieu tombe, la barre reste** : les trois elements sont mesures chacun la ou il vit desormais.

**Motif, et il n'est pas formel** : une M4 qui ne compterait plus l'indice deviendrait verte sur
plusieurs jeux **le jour ou l'indice sort du bloc mesure, c'est-a-dire sans qu'un seul pixel ait bouge
pour l'enfant.** J'ai refuse cette forme de vert deux fois ce sprint - sur M10, en exigeant un temoin
dans la meme execution, et sur M13 clause 3, comptee non tenue parce qu'elle n'etait tenue que par la
panne qui cassait ses voisines. Je ne l'accepte pas davantage contre ma propre condition.

Aucun des trois elements ne peut ceder, parce qu'aucun n'est couvert ailleurs : la revelation est le
seul endroit qui dit sur quoi appuyer et son absence est un cul-de-sac ; le message est le seul qui
dit "essaie encore" ; l'indice est la seule chose qui apprend quelque chose au moment ou l'enfant est
bloque.

### Ce que je dois dire avant que l'equipe construise - une derivation, pas une mesure

**Remonter l'indice rend 0 px au message.** Le gabarit servi rend un seul flux vertical de blocs et
`.message-jeu` ne porte aucune propriete dans `recreo.css` : `.indice` a sa propre boite au-dessus
comme en dessous. Deplacer un bloc dans un flux vertical ne change pas la hauteur du flux. Sur "Le
bon verbe", avec le releve d'`ines-design` et le mien : le bas de tout ce que le jeu dit reste a
**891 px** dans les deux positions, et la ligne de verdict passe de 736 a **891 px**. Les 65 a 155 px
de sa mesure sont reels, mais ils sont **gagnes par le cabinet et repris au bloc du dessous dans le
meme mouvement**.

**Ce que la direction choisie change n'est pas une quantite de pixels, c'est lequel des deux textes
est sacrifie.** Je l'annonce comme une derivation - **je n'ai pas joue l'indice remonte, il n'existe
nulle part** - et si la mesure d'`ines-design` sur le rendu reel la contredit, c'est sa mesure qui
vaut et je le dirai comme une correction de mon propre constat.

### Ce que je produis pour `camille-po`

- **Une condition neuve, M15** : l'indice se lit comme une partie de la question et non comme un
  second verdict (aucun signe visuel partage avec le bloc du verdict), et un seul indice a la fois,
  celui de la question courante. C'est le prix de la cession de M6 clause 1, pas un durcissement.
- **Une reserve neuve, Q**, a la suite de mes reserves L a P du meme jour : **la direction choisie
  deplace un defaut d'une condition bloquante (M4) vers une condition qui ne l'est pas (M8)**, puisque
  l'apparition d'un indice au-dessus du meuble deplace les colonnes de 65 a 155 px sous le doigt de
  l'enfant. **Je ne rends pas M8 bloquante apres coup** ; je la signale datee.
- **Trois clauses de lieu retirees, aucune barre baissee** : M1, M4 et M8 perdent chacune les mots
  qui fixaient une position dans la page. C'est un allegement de mes conditions.
- **Une correction de mon propre ecrit** : ma parenthese de M1 ne donnait qu'un des quatre motifs du
  lieu. Un motif ecrit ne fait pas l'inventaire.

### Ce que cette reponse ne tranche pas

Ce qui doit payer si remonter l'indice ne suffit pas - ce sera une autre question, avec les chiffres
d'`ines-design`. La repartition du budget vertical d'un ecran a 360 x 640 : **cette reponse l'appelle**,
puisqu'un flux a somme nulle ne se pilote pas texte par texte, mais `ines-design` la proposera avec
ses mesures et je l'arbitrerai. Aucune route technique. **Et aucun verdict** : mon refus tient, aucun
Done pedagogique n'est prononce, aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.
## Porte pedagogique - arbitrage du 2026-08-13 : l'indice appele par l'enfant (une ampoule)

> Cette section est ma reponse a une proposition qui touche ma condition M1, adressee apres mon
> refus. **Elle ne contient aucun Done pedagogique et elle ne leve rien** : mon refus du 2026-08-13
> sur M4 et sur M13 clause 1 tient entier. L'arbitrage complet est dans
> `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Arbitrage prealable au
> developpement - l'indice appele par l'enfant (une ampoule), du 2026-08-13".

### La proposition et ma reponse

**Proposition** : l'indice ne s'affiche plus de lui-meme ; l'enfant l'appelle, par une boite qu'on
ouvre ou une ampoule.

**Reponse** : **ACCEPTE dans son principe, sous six conditions M16 a M21, dont M17 bloquante.**
**Je cede l'affichage automatique sur les essais 1 et 2, et je garde deux morceaux de M1** :

| Ce que M1 disait | Ce qu'il en reste |
|---|---|
| l'indice est **rattache a la question qui bloque** | **intact** |
| **au premier echec**, quelque chose vient a l'enfant | **intact, sous une autre forme** : l'ampoule se marque au premier echec (M16 clause 2), et l'enfant ouvre d'un appui |
| l'indice **s'affiche** de lui-meme | **cede sur les essais 1 et 2** ; garde sur l'ecran de revelation (M17 clause 2), ou il n'y a plus rien a divulguer |

**L'affaiblissement, dit comme tel : un appui.** Un enfant de six ans en difficulte n'appelle pas
une aide - c'est mon jugement de metier, il n'est pas mesure, et c'est la seule chose de cette
reponse que je ne peux pas etayer par un chiffre (**reserve R**). C'est pour cela que je refuse une
aide seulement *disponible* et que j'exige qu'elle s'annonce.

### Les trois faits mesures qui me font accepter

1. **Sur "Le bon verbe", l'aide automatique n'est pas une aide, c'est la reponse.** L'utilisatrice
   l'a constate en jouant, je l'ai reproduit : **un seul appui faux**, et l'indice dit *"il devient
   'va'"*. **25 indices sur 25** de ce paquet nomment la bonne reponse.
2. **L'aide automatique coute a l'enfant le texte qui lui dit qu'il s'est trompe.** La boite
   d'indice pese **58 a 148 px** ; c'est elle qui pousse "Ce n'est pas ça. Essaie encore !" sous le
   pli sur quatre jeux qui l'auraient garde. Entre les deux, celui qui doit rester est celui qui dit
   ou l'enfant en est.
3. **Deux blocs de texte a la fois, pour un lecteur de CP, c'est un de trop.**

### Ce que l'ampoule rend, et ce qu'elle ne rend pas - 49 etats joues a 360 x 640

| | Etats d'essai | Reste a payer |
|---|---|---|
| Cinq jeux de CP legers | de **+11,19 / +41,19** a **-84,81 px** : **tenus** | rien dans ces etats |
| Petits problemes | de +107,19 a **+11,19 px** | **+11,19 px** |
| Le bon verbe | de +251,19 a **+95,19 px** (enonce long) | **+95,19 px**, et **-0,81 px** seulement sur les items a enonce court |
| Le bon verbe, **"Bravo !"** | **+67,19 px sur les items a enonce long, inchange** | **je ne credite rien a M11 de l'espace libere** |
| Ecrans de revelation | inchanges, l'indice y reste (M17 clause 2) | +13,19 a +251,19 px |

**Donc : allegement reel, remede a M4 non.** Mon refus n'est pas leve d'un pixel, et le vrai sujet
reste le budget vertical d'un ecran a 360 x 640 - ma **reserve P**.

### Mes six conditions, en une ligne chacune

| # | Ce qu'elle exige |
|---|---|
| **M16** | l'ampoule est la des le premier rendu, et elle **se marque** des le premier echec, sans qu'un pixel de la page bouge |
| **M17** | **bloquante.** L'indice ouvert est **entierement au-dessus du pli** ; ni sous le pli, ni en recouvrant les propositions. Et il **s'affiche seul sur l'ecran de revelation** |
| **M18** | le bas du cabinet **ne descend sur aucun jeu** : 455,19 / 551,19 / 539,19 et 635,19 px, mesures ce jour |
| **M19** | elle porte le mot **"Aide"**, texte au moins 20 px, cible au moins 44 x 44 px, pictogramme en plus du mot et jamais a sa place |
| **M20** | hors du contour du cabinet (C2) ; l'ouvrir n'est pas un essai, ne deplace aucune proposition, et Tabulation puis Entree ne detruit jamais la partie |
| **M21** | **pas d'ampoule sur un paquet dont les indices nomment la reponse** - "Le bon verbe" n'en portera pas. C'est M2 transposee, pas une condition neuve |

### Trois de mes propres constats que je corrige

1. **Mon 251,19 px etait le pire cas, pas l'etat du jeu** : sur "Le bon verbe" le deficit depend de
   l'item tire, **125,19 a 251,19 px**.
2. **Un seul paquet sur huit porte des explications** (`cp-le-bon-son`, 6 items sur 15). Ma ligne M7
   decrivait la revelation comme portant une explication : c'etait vrai d'un jeu, pas des sept. **Six
   jeux sur sept revelent la reponse sans rien expliquer**, et c'est le motif de M17 clause 2.
3. **Un garde-fou litteral "l'indice contient la bonne reponse" est faux** : il disqualifierait deux
   indices de CP parfaitement sains (`9 + ? = 10`, `10 + ? = 10`). Ma correction du matin - quatre
   indices de CP, tous sans consequence - tient.

### Ce que je laisse aux autres

- **La forme de l'ampoule** : `ines-design` propose, j'arbitre. Mes contraintes sont M17 a M20 et
  elles sont chiffrees.
- **La route technique** : `theo-archi`.
- **Le backlog** : `camille-po`. Six conditions, deux reserves neuves (**R**, **S**), et une priorite
  que je nomme - **reserve A**, reecrire les 25 indices de `cm1-conjugaison-present`, cesse d'etre une
  amelioration et devient ce qui rend "Le bon verbe" enseignable.

### Ce dont je reste la seule garde

Mes six points du verdict du matin, plus deux que cette reponse ajoute : **qu'une aide appelee
montre quelque chose a l'enfant qui l'appelle**, et **qu'une ampoule se marque quand l'enfant est
bloque**. Aucun test ne compare une ordonnee au pli, aucun ne compare deux rendus successifs de la
meme question. Tant que ces garde-fous n'existent pas, c'est moi le garde-fou, et je le suis en
jouant.

### Ce que cet arbitrage est degrade sur - dit avant mes conclusions

Trois points nommes : les appuis sont **programmes et non faits au doigt** ; **je n'ai pas joue
l'ampoule**, elle n'existe nulle part, et mes chiffres sur ce qu'elle rendrait sont une soustraction
sur un rendu reel, pas la mesure d'un ecran ; **je n'ai pas observe d'enfant**, et c'est la question
qui decide (reserve R).
