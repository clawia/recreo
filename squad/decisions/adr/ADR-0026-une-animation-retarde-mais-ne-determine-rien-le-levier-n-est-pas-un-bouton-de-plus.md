# ADR-0026 - Une animation retarde ce que l'enfant voit, elle ne determine rien ; le levier n'est pas un bouton de plus

- **Statut** : Accepte
- **Date** : 2026-08-10
- **Auteur** : theo-archi
- **Sprint** : s07 (ouverture)

## Contexte

L'utilisatrice a joue l'application et constate que la mecanique nommee "bandit manchot" ne rend pas
une machine a sous mais un questionnaire a choix multiple. **Verifie avant de me saisir, et le constat
est exact** :

- `fragments/manche.html` rend un score, une consigne, un enonce, un indice conditionnel et un
  `<form class="propositions">` de `<button type="submit">` empiles ;
- `recreo.css` empile ces boutons par `.propositions { flex-direction: column }` ;
- le depot ne contient **aucun** vocabulaire de machine a sous. Methode du chiffre : recherche
  sensible a la casse des chaines `rouleau`, `levier`, `colonne`, `machine a sous` et `bandit` dans
  tout l'arbre suivi hors `contenu-repris/` ; les seules occurrences de `bandit` sont l'identifiant
  de mecanique `bandit-manchot`, les trois entrees de catalogue qui le citent, la classe
  `BanditManchot` et sa fiche de conformite. Zero occurrence des quatre autres.

C'est la **dette n°1 de `CLAUDE.md` reproduite** : une abstraction nommee partout, absente du code.
Elle a ceci de particulier qu'elle n'est pas une abstraction manquante mais un **nom qui promet un
geste** - tirer un levier, voir tourner des rouleaux - que le code n'a jamais tenu.

**L'utilisatrice a tranche la direction** : une vraie machine a sous. L'enfant tire un levier, les
colonnes defilent puis s'arretent chacune sur une proposition, il choisit la colonne juste. Cette
direction n'est pas rediscutee ici. Ce qui m'est demande est ce qu'elle coute et ce qu'elle exige.

`camille-po` ecrit la `DEC` et les stories en parallele. Elle tranche en particulier si le levier est
un passage oblige a chaque manche ou seulement au demarrage : **cet ADR couvre les deux cas**, sans en
supposer un.

## Decision

### 1. La frontiere d'`ADR-0008` : une animation retarde, elle ne determine rien

**Une animation n'est pas un comportement de jeu tant qu'elle ne determine rien que le serveur n'ait
deja decide : quelles propositions s'affichent, dans quelle colonne, dans quel ordre, laquelle est
juste, et le fait que l'enfant puisse repondre sont tous fixes dans le HTML avant qu'il quitte le
serveur. Elle devient un comportement de jeu des l'instant ou ce que l'enfant finit par lire depend
de l'animation ayant tourne.**

Une animation a donc le droit de **retarder** l'acces a une reponse - c'est meme ce qu'une machine a
sous veut dire - et n'a jamais le droit de la **choisir**. La ligne se trace sur une propriete du
document servi, verifiable et non discutable : **le fragment servi est jouable sans CSS et sans
JavaScript.** Coupez les deux, et l'enfant voit ses trois propositions et peut en presser une. C'est
la meme posture qu'`ADR-0008` tient depuis s00, appliquee au mouvement au lieu du verdict.

Corollaire, qui repond directement a la question posee : **oui, une animation purement decorative en
CSS n'est pas un "comportement de jeu" au sens d'`ADR-0008`.** Et la reponse depend bien de ce que
l'animation determine : si elle ne determine que le moment ou l'enfant lit, elle est decorative ; si
elle determine la proposition affichee, elle est du comportement de jeu et `ADR-0008` l'interdit
au client sans exception.

### 2. L'animation est en CSS, declenchee par l'insertion du fragment. Aucun JavaScript n'entre

Une animation CSS demarre quand l'element qui la porte entre dans le document. htmx insere le
fragment de manche dans `#jeu`. Une declaration `animation` sur les colonnes suffit donc, et **aucune
ligne de JavaScript n'est necessaire** :

- `ADR-0014` point 7 est intact : le JavaScript de Bootstrap n'entre toujours pas, et rien de tiers
  n'est demande ;
- le budget d'`ADR-0008` (4 096 octets, 100 lignes) n'est pas touche : `recreo.js` reste a ses huit
  lignes de deplacement de focus ;
- l'ilot JavaScript nomme d'avance par `ADR-0008` **n'est pas ouvert**, et cet ADR ne l'ouvre pas.

Ce que ce choix coute, en toute franchise : la duree du defilement devient une valeur ecrite dans la
feuille de style, donc invisible a tout test de comportement serveur. C'est le prix, il est reel, et
la clause "non gardable" de la section 6 le nomme au lieu de le taire.

### 3. Le levier n'est pas un bouton de plus : c'est l'apparence du bouton qui ouvre deja une manche

C'est le point ou j'ai change d'avis en lisant le code plutot qu'en raisonnant.

Un levier qui declenche un echange serveur est un bouton de plus, et un bouton de plus sur un ecran
de jeu se paie a plusieurs endroits (`ADR-0017`, cible tactile, place a 360 px). Mais **le bouton qui
ouvre une manche existe deja, dans les deux cas que `camille-po` peut trancher** :

| Ce que `camille-po` tranche | Le bouton qui devient le levier | Route deja existante | Cout architectural |
|---|---|---|---|
| levier au demarrage seulement | `.demarrer` de `accueil.html` ("Jouer") | `POST /parties/{jeuId}` | **nul** |
| levier a chaque manche | `.encore` de `fragments/retour.html` ("Encore !") | `GET /parties/{jeuId}/manche` | **nul** |

Dans les deux cas : aucun nouveau bouton, aucun nouvel echange, aucun nouvel etat de session, aucune
nouvelle valeur de `data-role`. Le levier prend l'apparence d'un levier ; il garde
`data-role="action"`, parce que c'est deja exactement ce qu'il fait du point de vue de l'enfant -
faire avancer le jeu. **C'est la forme que je retiens.**

Les deux autres formes sont nommees et refusees, pour qu'on ne les redecouvre pas en cours de route :

- **Le levier sur l'ecran de manche lui-meme** (l'enfant arrive devant des rouleaux au repos, tire, ils
  tournent). Tenable sous `ADR-0008`, et j'ecris a quel prix : un etat de domaine de plus dans
  `BanditManchotSession` ("rouleaux au repos" / "rouleaux tires"), une route de plus, un aller-retour
  de plus par manche, et le cycle lire-calculer-ecrire d'`ADR-0019` a tenir sur cette transition
  comme sur les autres. Je le refuse **aujourd'hui** parce qu'il achete un ressenti que la forme
  retenue donne deja - l'enfant presse, l'ecran change, les rouleaux tournent - contre un etat de
  jeu supplementaire. Si `nadia-prof` constate en jouant que le geste manque de sa cause visible, ce
  refus est a reprendre, et le chemin est ecrit ici.
- **Le levier en `<label>` plus `<input type="checkbox">` plus `:has(:checked)`.** Techniquement
  possible sans JavaScript, et je le refuse pour deux raisons distinctes. La premiere est de fond :
  c'est un etat client qui commande l'affichage, donc exactement le cote de la frontiere que la
  section 1 place hors limites. La seconde est structurelle et pese autant : `ADR-0017` derive sa
  population de `button, a[href]`, et `TailleDesTextesEtBoutonsTest` de la meme selection. Un
  `<input>` et un `<label>` n'y sont ni l'un ni l'autre. Le levier le plus presse de l'application
  echapperait donc **en silence** au garde-fou de saillance et aux deux planchers de `nadia-prof`.
  C'est la forme de defaut que ce depot poursuit depuis `ADR-0013`, et je ne vais pas l'introduire
  dans l'ADR qui le cite.

### 4. Le nombre de rouleaux est celui du paquet, jamais trois en dur

Mesure, pas estimation. Methode : lecture des quatre paquets de
`adapters/adapter-content/src/main/resources/contenu/` a `28a419f`, comptage de
`1 + |distracteurs|` par item pour les paquets en liste, et lecture de `nombreDePropositions` pour le
paquet a source calculee.

| Paquet | Propositions par item | Au catalogue ? |
|---|---|---|
| `cp-additions-jusqu-a-10` | 3 (`nombreDePropositions: 3`) | oui |
| `cp-le-bon-son` | 3 sur les 15 items | oui |
| `cm1-homophones` | **2** sur les 24 items | oui |
| `cm1-conjugaison-present` | **4** sur les 12 items | non, retire par `DEC-0014` |

**"Trois colonnes" n'est donc pas une propriete du code, c'est une propriete d'un paquet**, et deux
des quatre paquets du depot ne la verifient pas. Toute mise en page qui ecrirait trois quelque part
serait fausse des la deuxieme entree du catalogue. Le nombre de rouleaux se derive du nombre de
propositions que le paquet offre.

Que la machine a sous garde son sens avec deux rouleaux ou avec quatre est une question de produit et
de pedagogie : je la route a `camille-po` et a `nadia-prof`, je ne la tranche pas.

### 5. Ce que l'implementation ne change pas, et pourquoi le dire ici epargne un sprint

Trois contraintes, chacune adossee a un garde-fou existant qui deviendrait rouge autrement.

1. **L'element cliquable reste le `<button class="btn proposition" data-role="action">`.** La colonne
   est un element d'habillage autour de lui, sans `data-role` et qui n'est pas un bouton. Si la
   colonne devenait elle-meme le bouton, `.proposition` disparaitrait et trois epinglages tomberaient
   d'un coup : le couple confronte d'`ADR-0017`, le rapport de taille entre le chiffre de reponse et
   le calcul affiche, et le plancher de cible tactile.
2. **Les colonnes portent des classes, jamais des identifiants.** `ADR-0020` interdit un identifiant
   porte deux fois dans le document assemble ; le fragment de manche est aussi insere dans
   `accueil.html` par la branche de reprise. Une classe ne pose pas cette question du tout.
3. **Toute dimension de rouleau s'exprime en `rem` ou en pourcentage, jamais en pixels.**
   `ViewportEtLargeurTelephoneTest` refuse toute largeur en pixels au-dela de 375 px et refuse
   `width` en pixels a toute valeur.

### 6. Ce qui reste non gardable, avec son destinataire nomme

**Qu'une proposition tienne dans sa colonne** est une propriete du rendu, pas de la declaration : elle
depend de la police installee, des glyphes exacts du mot et de l'algorithme de mise en page.
`ADR-0025` a deja declare cette famille non gardable par un automate de ce depot et l'a confiee a
`nadia-prof` ; rien de ce chantier ne change ce constat, et la mesure de la section suivante en donne
la demonstration chiffree.

**J'ai refuse d'ecrire le garde-fou tentant**, et voici la mesure qui motive le refus. Une regle de
contenu "aucune proposition d'un paquet ne depasse N caracteres" serait facile a ecrire et fausse :
mesure au navigateur a 360 px sur trois colonnes, `oreille` (7 caracteres) tient dans sa colonne et
`mangez` (6 caracteres) n'y tient pas. Le budget n'est pas un nombre de caracteres, c'est une largeur
d'avance de glyphes. Une regle en caracteres aurait donc **surpromis** - le defaut exact
qu'`ADR-0015` a deja du corriger une fois dans ce depot, et je ne vais pas le refaire dans l'ADR qui
le cite.

Ce qui reste donc a `nadia-prof`, et qui n'a aucun automate : la duree du defilement, le confort de
lecture d'une proposition dans une colonne etroite, et le fait qu'un enfant de CP comprenne le geste
du levier. Ce qui reste a `ines-design` : la forme visuelle du rouleau et du levier, dans la latitude
que la section 9 delimite.

## La mesure a 360 px : trois colonnes tiennent, et aucun plancher ne bouge

**Une correction de fait, d'abord, parce qu'un chiffre porte sa methode.** La largeur cible actee par
`DEC-0007` point 5 est **375 px**, pas 360 px. 360 px est la largeur a laquelle `nadia-prof` audite
l'application dans `squad/conformite/FICHE-bandit-manchot-additions-cp.md`, et
`ViewportEtLargeurTelephoneTest` code 375 px comme cible. Je mesure quand meme a **360 px**, parce
que c'est la contrainte la plus serree des deux et que la tenir tient l'autre.

**Methode.** Maquette jetable, hors du depot, non commitee : les deux feuilles reellement servies
(`vendor/bootstrap.min.css` puis `css/recreo.css`, dans cet ordre, comme `RessourcesTiercesTest`
l'exige), la structure reelle `.container.zone-jeu > .ecran-de-jeu > form.propositions >
button.btn.proposition`, et **un seul delta** : `.propositions { flex-direction: row }` avec
`.proposition { flex: 1 1 0 }`. Aucun plancher touche - `gap` reste a 1rem, `.proposition` garde
`font-size: 2rem`, `min-height: 2.75rem`, `min-width: 2.75rem`, `padding: 0.75rem 1.25rem`. Les
libelles sont les propositions reelles des paquets du depot. Capture par la commande versionnee
d'`ADR-0025`, `outils/mesurer-rendu.sh 360 <url> <png>`, sur un Chrome 151 designe par la variable
`CHROME` ; mesure des largeurs par balayage des aplats de `--recreo-accent` (`#2e6f9e`) sur l'image,
ligne prise a 6 px sous le bord haut du conteneur pour ne pas couper de glyphe. La meme maquette a
ete capturee a **375 px**, la cible de `DEC-0007` : les colonnes y passent a 104 px et le cas a quatre
colonnes deborde de la meme facon. 360 px est bien la contrainte serree des deux, et c'est celle que
les chiffres ci-dessous rapportent.

**Resultat.** Zone de contenu mesuree : `x = 12` a `x = 347`, soit **336 px** - les 360 px moins les
2 x 12 px de rembourrage lateral de `.container` (Bootstrap, `ADR-0014` point 5).

| Cas | Colonnes | Largeur par colonne | Ecart mesure | Hauteur | Verdict |
|---|---|---|---|---|---|
| `cp-additions-jusqu-a-10` (`10`, `7`, `9`) | 3 | 99, 100, 99 px | 18 px | 74 px | tient |
| `cp-le-bon-son` (`an`, `on`, `in`) | 3 | 99, 100, 99 px | 18 px | 74 px | tient |
| `cm1-homophones` (`sont`, `son`) | 2 | 158, 158 px | 18 px | 74 px | tient |
| `cm1-conjugaison-present` (`mangez`... ) | 4 | 70 px | 18 px | 74 px | **texte hors de la boite** |

Les 18 px mesures sont les 16 px de `gap: 1rem` plus un pixel d'anticrenelage de part et d'autre de
chaque angle arrondi ; le rembourrage de `.container` et l'ecart declare sont donc rendus tels quels.

**Confrontation aux planchers, aucun n'est abaisse ni approche par le bas** :

| Plancher | Valeur exigee | Valeur mesuree a 3 colonnes | Marge |
|---|---|---|---|
| cible tactile (largeur) | 2,75rem = 44 px | 99 px | x 2,25 |
| cible tactile (hauteur) | 2,75rem = 44 px | 74 px | x 1,68 |
| lisibilite du bouton | 1,25rem = 20 px | 2rem = 32 px inchange | x 1,6 |
| ecart entre deux reponses | 1rem = 16 px | 16 px declares, 18 px rendus | au plancher exactement |

**Trois colonnes a 360 px tiennent donc sans abaisser un seul plancher.** Le plancher d'ecart est
tenu a l'egalite exacte, ce qui est son etat depuis s03 et l'etat que `nadia-prof` a accepte ; l'ADR
ne le deplace pas et le garde-fou amende epingle desormais ce bord (section "Regles executables",
regle 3).

**Le cas a quatre colonnes, lui, ne tient pas** : chaque colonne tombe a 70 px, la cible tactile est
encore respectee mais le mot deborde de son aplat et les glyphes de deux colonnes voisines se
touchent. Le budget reel est le contenu de la colonne, soit `99 - 2 x 20 = 59 px` a trois colonnes.
Mesure de la borne, sur la meme commande : `1`, `10`, `100`, `sont`, `ouil`, `ouill`, `oreill`,
`oreille`, `lisons`, `lisent` et `allez` tiennent ; `mmmmmm`, `mangez`, `mangeons` et `dessinent` ne
tiennent pas. C'est bien une largeur de glyphes et non un nombre de caracteres.

**Consequence a remonter, pas a decouvrir plus tard.** `DEC-0014` retire "Le bon verbe"
(`cm1-conjugaison-present`) du catalogue jusqu'a sa correction, et ouvre s07 sur son retour. Ce
paquet est **le seul du depot** qui ne rentre pas en rouleaux a 360 px : quatre propositions,
jusqu'a neuf caracteres. Le jour ou il revient au catalogue, la mise en page en rouleaux et ce paquet
se contredisent. Ce n'est ni une decision d'architecture ni un defaut technique : c'est un arbitrage
de perimetre entre deux stories du meme sprint, et je le route a `camille-po` et a `nadia-prof` avec
la mesure ci-dessus comme piece.

## Regles executables

### Regle 1 - `LAnimationRetardeMaisNeDetermineRienTest`, nouveau garde-fou

Fichier :
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/LAnimationRetardeMaisNeDetermineRienTest.java`

Quatre clauses, chacune la contrepartie executable d'une phrase de la section 1. Perimetre derive
(`ADR-0013` obligation 1) : les feuilles du balayage de `static/` hors `static/vendor/`, les gabarits
du balayage de `templates/`, les classes d'action des attributs des elements `[data-role=action]`
eux-memes. Lecture caractere par caractere avec une pile de regles-`@`, jamais par expression
reguliere (`AssertionsWebTest`, voir plus bas).

1. **Toute animation declaree par une feuille maison se termine.** Une animation `infinite` est
   refusee : un rouleau qui ne s'arrete jamais cesse de retarder et se met a determiner - l'enfant
   n'atteint jamais l'etat que le serveur a decide.
2. **Toute animation a un repli de mouvement.** La forme est contractuelle, comme le masquage
   d'`ADR-0022` :
   `@media (prefers-reduced-motion: reduce) { <selecteur> { animation: none } }`, meme selecteur.
   Une forme approchante n'est pas lue de travers, elle n'est pas lue du tout, et le garde-fou echoue
   faute de repli.
3. **Aucune regle maison permanente ne rend une action inactionnable.** `display: none`,
   `visibility: hidden`, `pointer-events: none` et `opacity: 0` sont refuses sur toute regle qui
   nomme une classe portee par un `[data-role=action]`, **hors** `@keyframes`. Une animation a le
   droit de traverser ces etats ; une regle qui n'est pas dans des images cles ne les traverse pas,
   elle y reste, et la proposition serait hors d'atteinte quelle que soit l'animation.
4. **Aucun gabarit ne desactive un bouton porteur de `data-role`.** Ni `disabled`, ni
   `aria-disabled`, ni leur forme Thymeleaf. Un ecran qui n'attend plus de reponse ne rend pas un
   bouton desactive : il ne rend pas ce bouton du tout, ce qui est deja la propriete d'`ADR-0020`
   verifiee sur l'ecran de fin de partie.

**Le point delicat, et je l'ecris plutot que de le laisser trouver.** Les clauses 1 et 2 ont une
population de production **vide** aujourd'hui : aucune feuille maison n'anime encore quoi que ce
soit. Elles sont donc vertes a vide, ce qu'`ADR-0003` interdit de laisser passer en silence. La
sortie retenue est un **epinglage de l'etat reel** : `aucuneAnimationNEstEncoreDeclareeEnProduction`
affirme que le compte d'animations de production vaut exactement zero, et son message dit quoi faire
le jour ou ce n'est plus vrai - supprimer l'epinglage, les deux clauses portant desormais sur un
objet reel. Le plafond de cet epinglage vaut exactement la taille de la dette, zero et pas
"quelques-unes" ; c'est la lecon du plafond d'`ADR-0016` appliquee a moi-meme. Les clauses 3 et 4,
elles, ont une population de production non vide des aujourd'hui et sont portantes immediatement.

**Preuves de morsure.** Cinq jeux fautifs neufs sous
`adapters/adapter-web/src/test/resources/contreexemple/LAnimationRetardeMaisNeDetermineRienTest/`,
plus une mutation reelle de la production :

| Objet mis a l'epreuve | Attendu | Mesure |
|---|---|---|
| `animation-sans-fin` : rouleau `infinite`, repli present | clause 1 rouge, sur l'infinite et rien d'autre | rouge, "cette animation est declaree infinie" |
| `animation-sans-repli` : animation finie, aucun repli | clause 2 rouge | rouge, "n'a aucun repli de mouvement" |
| `animation-avec-repli` : le meme jeu, repli ajoute | vert | vert. Sans ce cas, une clause impossible a satisfaire passerait pour une clause exigeante |
| `proposition-cachee-en-permanence` : `.proposition { opacity: 0 }` | clause 3 rouge | rouge, "c'est un etat permanent" |
| `proposition-cachee-dans-des-images-cles` : le meme `opacity: 0` dans `@keyframes` | vert | vert. Ce cas empeche la clause 3 d'etre plus large que la decision |
| `action-desactivee` : `disabled` sur une proposition | clause 4 rouge | rouge, "porte disabled" |
| **Mutation reelle** : ajout de `.proposition { animation: arrivee 0.6s linear 1 }` a `recreo.css` | l'epinglage et la clause 2 rougissent ensemble | **2 echecs sur 11 cas** : l'epinglage ("supprime cet epinglage") et la clause 2, qui nomme le selecteur et la forme du repli a ecrire. Production restauree |

### Regle 2 - amendement de `FrontieresDuJavaScriptTest` : le vocabulaire du tirage

`INTERDITS_JS` gagne `proposition` et `colonne`.

Motif, et il n'est pas cosmetique : `ADR-0008` interdisait le vocabulaire du **verdict** (`score`,
`correct`, `bonneReponse`, `reponse`) et ne disait rien du vocabulaire du **tirage**. Un rouleau ecrit
en JavaScript n'a besoin d'aucun des onze mots precedents ; il lui suffit de relire `.propositions` et
de decider laquelle arrive dans quelle colonne. **Ces deux mots-la nomment exactement ce que le
serveur doit avoir decide avant que le fragment ne le quitte**, et c'est le trou que ce chantier
ouvrirait sans cet amendement.

Ils sont absents de `recreo.js` au jour de l'amendement, dont les huit lignes ne parlent que de
`htmx:afterSwap`, de `.signal` et de focus.

**Preuve de morsure, par mutation reelle** : ajout a `recreo.js` de
`var rouleaux = document.querySelectorAll('.propositions .btn');`, c'est-a-dire la premiere ligne
qu'ecrirait un rouleau client. Resultat mesure : **1 echec sur 3 cas**,
`leJavaScriptNePrendAucuneDecisionDeJeu`, message nommant le fichier. Sans l'amendement, cette ligne
passait vert. Production restauree.

### Regle 3 - amendement de `TailleDesTextesEtBoutonsTest` : l'ecart tient sur les deux axes

`lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRem` devient
`lEcartEntreDeuxBoutonsDeReponseEstAuMoinsUnRemSurLesDeuxAxes`, et lit desormais les deux axes de
`gap` : raccourci a une ou deux valeurs, puis surcharges par `row-gap` et `column-gap`, avec le meme
plancher de 1rem sur chacun et un message qui nomme l'axe fautif.

Motif : le commentaire de `recreo.css` disait la limite a voix haute - "proxy valide tant que les
boutons restent empiles dans ce conteneur flex en colonne". Le jour ou les propositions deviennent des
colonnes cote a cote, l'ecart que le doigt de l'enfant franchit change d'axe, et **deux formes le
defaisaient sans faire rougir quoi que ce soit**.

**Preuve de morsure, par relecture adverse et par rejeu de l'ancienne regle sur les jeux fautifs** -
mesure et non racontee, l'ancienne lecture ayant ete rejouee telle quelle dans un test jetable :

| Jeu fautif | Ce que faisait l'ancienne regle | Ce que fait la regle amendee |
|---|---|---|
| `ecart-lateral-defait` : `gap: 1rem; column-gap: 0.5rem` | **lisait 1.0rem et passait.** Vert silencieux confirme par execution | rouge, "l'ecart lateral entre deux boutons de reponse" |
| `raccourci-a-deux-valeurs` : `gap: 1rem 0.5rem` | levait `NumberFormatException: For input string: "1rem 0.5"` - rouge, mais par un defaut de format et sans motif lisible | rouge, avec l'axe et le plancher nommes |
| `un-poil-sous-le-plancher` : `gap: 0.99rem` | refuse | refuse |
| production : `gap: 1rem`, le bord exact | accepte | accepte, et le bord est desormais **epingle** : un cas affirme que la production vaut exactement le plancher, l'autre qu'un poil en dessous est refuse |

Ce dernier couple est la lecon de la seconde mutation d'`ADR-0025` appliquee ici : une inegalite
stricte rejetterait l'etat que `nadia-prof` a accepte, et aucun cas nominal ne le dirait.

### Regle 4 - ce que les garde-fous existants font sans etre touches

`ADR-0013` exige de dire ce qu'un garde-fou ne couvre pas. Voici le sort de chacun de ceux que ce
chantier fait bouger, et cinq d'entre eux ne bougent pas du tout.

| Garde-fou | Sort | Pourquoi |
|---|---|---|
| `ADR-0017` / `LActionNeDeclarePasMoinsQueLaSortieTest` | **inchange** | le levier est `.demarrer` ou `.encore`, deja `data-role="action"` ; la proposition reste `.btn.proposition`, deja `data-role="action"`. L'ensemble ferme `{action, sortie}` n'a pas a s'ouvrir, et les trois epinglages de couples restent verts sans qu'une ligne change. Le role du levier est **`action`** : il fait avancer le jeu |
| `TailleDesTextesEtBoutonsTest`, planchers | **inchanges, mesures tenus** | 99 px x 74 px par colonne a 360 px, contre 44 px exiges ; `font-size: 2rem` inchange. **Aucun seuil ne s'abaisse pour faire tenir une mise en page** |
| `TailleDesTextesEtBoutonsTest`, ecart `.propositions` | **amende**, regle 3 | l'axe lateral cesse d'etre un angle mort |
| `ADR-0020` / `ToutEcranEchangeEstUnDocumentJouableTest` | **inchange**, contrainte enoncee | les colonnes portent des classes, jamais d'identifiants ; et une colonne n'est pas un `<p>`, donc la clause du paragraphe vide reste sans objet |
| `ADR-0022` / `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` | **inchange** | le fragment de manche garde `class="ecran-de-jeu"` sur sa racine. Si le chantier ajoutait un fragment - il n'en a pas besoin - le garde-fou l'exigerait de lui-meme, son perimetre etant derive du repertoire |
| `ViewportEtLargeurTelephoneTest` | **inchange**, contrainte enoncee | toute dimension de rouleau en `rem` ou en pourcentage. Et son angle mort reste celui qu'`ADR-0025` a nomme : il ne voit pas un debordement, ce qui est precisement pourquoi la section "mesure" ci-dessus est une mesure et non une deduction |
| `RessourcesTiercesTest` | **inchange** | aucune ressource tierce demandee. Un decor de rouleau se dessine en CSS, comme la coche et la croix du signal juste/faux le sont deja par `clip-path` ; s'il fallait un fichier, il passerait par `static/vendor/` et sa ligne de provenance, et `url()` ne peut de toute facon viser que du local |
| `AssertionsWebTest.aucuneAssertionParExpressionReguliere` | **inchange, et respecte** | il m'avait mordu en s06. Le garde-fou de la regle 1 lit le CSS caractere par caractere avec un compteur d'accolades et ne touche pas a `java.util.regex` - une regle-@ imbriquee n'est de toute facon pas une chose qu'une expression reguliere lit correctement |
| `FrontieresDuJavaScriptTest` | **amende**, regle 2 | le vocabulaire du tirage rejoint celui du verdict |

### Pourquoi rien de tout cela n'est une `ArchRule`

ArchUnit lit du bytecode. Ni une declaration CSS, ni un attribut de gabarit, ni une largeur rendue n'y
sont visibles. **Le compte de regles ArchUnit du depot est inchange a 28**, et cet ADR n'en revendique
aucune.

Methode de ce chiffre, remesuree ici et non reprise de s06. Perimetre : les fichiers `.java` **suivis
par Git** (`git ls-files '*.java'`), quel que soit le module. Ce perimetre-la et pas un autre, parce
qu'un balayage du disque apres `mvn verify` rend **32** et non 30 : les repertoires `target/` recoivent
une copie des sources de test-ressources, dont le faux module ci-dessous. C'est exactement la famille
de divergence qui m'avait fait annoncer 29 regles pour 25 reelles. Critere : une declaration
`static final ArchRule`. Le comptage brut rend **30**, dans douze fichiers. Exclusion, une seule et
elle est nommee : les **2** declarations de
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/src/test/java/com/recreo/faux/RegleContreExemple.java`,
qui est une **ressource de test** - un faux module que `LesGardeFousSontMisALEpreuveTest` balaie pour
se mettre lui-meme a l'epreuve, jamais compile ni execute comme regle du depot. Reste **28**, dans
onze classes reelles. Aucune n'est ajoutee ni retiree par ce commit ; ce chiffre est donc a la fois
l'etat avant et l'etat apres.

## Ce que je laisse comme latitude

**A `ines-design`**, qui propose sans trancher : tout ce qui est visuel. La forme du rouleau (fenetre
a `overflow: hidden` et bande qui glisse, ou pseudo-element decoratif qui passe devant un bouton
immobile), le dessin du levier, la duree et la courbe du defilement, le decalage entre colonnes, la
couleur et la texture des faces. La seule contrainte de forme que la regle 1 lui impose est
d'ecrire son repli de mouvement sous une forme litterale precise, qui est ecrite dans le message
d'erreur du garde-fou. Elle garde aussi la latitude d'un rouleau qui **apparait** en fondu, a
condition que l'etat cache vive dans les `@keyframes` et pas dans une regle permanente - le jeu
fautif `proposition-cachee-dans-des-images-cles` existe precisement pour prouver que cette latitude
lui reste.

**A `nadia-prof`**, seule autorite sur le Done pedagogique : la duree acceptable d'un defilement
devant un enfant de CP ; le fait qu'un enfant qui a besoin de temps ne soit pas presse par un
mouvement ; le fait qu'une proposition reste lisible dans une colonne de 99 px ; le fait que le geste
du levier se comprenne sans explication ; et le fait qu'une machine a sous a deux ou a quatre
rouleaux garde son sens. La technique lui doit deux choses et les lui livre : un ecran qui reste
jouable immobile pour l'enfant qui en a besoin (`prefers-reduced-motion`, garde par la clause 2), et
une mesure rejouable de tout ce qu'elle voudra constater a une largeur donnee (`ADR-0025`,
`outils/mesurer-rendu.sh`).

**Ce que je ne leur laisse pas** : abaisser un plancher pour faire tenir une mise en page. La mesure
montre que ce n'est pas necessaire a trois colonnes ; a quatre, la reponse n'est pas un plancher plus
bas, c'est une decision de produit.

## La reponse au clavier

Elle existe aujourd'hui parce que chaque proposition est un `<button type="submit">` dans un `<form>`,
donc atteignable par `Tab` et actionnable par `Entree` ou `Espace`, dans l'ordre du document, sans une
ligne de code ecrite pour ca.

**La nouvelle forme ne lui fait rien, et c'est un resultat des trois decisions ci-dessus plutot qu'une
chance.** Le bouton reste un `<button type="submit">` dans le meme `<form>` (section 5, contrainte 1) ;
la colonne est un habillage autour de lui et non un remplacement ; la clause 4 interdit `disabled` et
la clause 3 interdit `pointer-events: none` et `display: none` permanents, qui sont les trois seules
facons courantes de retirer un bouton du parcours clavier. `flex-direction: row` ne touche pas l'ordre
du document : `Tab` parcourt les colonnes de gauche a droite, dans l'ordre ou le serveur les a rendues.

Deux points a nommer franchement plutot que de laisser croire a une couverture complete :

- pendant le defilement, si `ines-design` retient une fenetre a `overflow: hidden`, le bouton est
  present et focusable mais peut etre hors de la partie visible de sa colonne. Le navigateur fait
  defiler pour amener le focus a l'ecran, ce qui peut se voir. C'est un effet de rendu, donc du
  ressort de `nadia-prof` et d'`ines-design` ; la latitude du pseudo-element decoratif devant un
  bouton immobile evite entierement la question, et c'est un argument reel en sa faveur ;
- **le focus visible sur une colonne n'est garde par aucun automate de ce depot.** Il ne l'etait pas
  davantage avant ce chantier, et je ne l'ajoute pas ici : ce serait une regle ecrite contre un
  defaut que le depot n'a pas etabli. Destinataire nomme : `ines-design` le propose, `nadia-prof` le
  constate.

## Consequences

- La mecanique tient enfin la promesse de son nom, et le chemin le moins couteux se trouve etre aussi
  le plus sur : zero nouvelle route, zero nouvel etat de session, zero ligne de JavaScript, zero
  ressource tierce, un seul delta structurel dans la feuille de style et un habillage de colonne dans
  le gabarit.
- Le depot gagne quatre clauses executables sur l'animation, dont deux ne portent encore sur rien et
  le disent par un epinglage plutot que par un commentaire.
- **Mauvaise consequence assumee, la premiere** : la duree du defilement vit dans une feuille de
  style et aucun test ne la juge. Un defilement de huit secondes passerait la CI verte. C'est le prix
  du choix "aucun JavaScript", il appartient a `nadia-prof` en jouant, et l'alternative - un compte a
  rebours serveur - couterait un aller-retour par manche et deplacerait un ressenti visuel dans le
  domaine.
- **Mauvaise consequence assumee, la deuxieme** : la clause 2 impose une forme litterale de repli. Un
  auteur qui ecrit `animation-duration: 0s` au lieu d'`animation: none`, ce qui est correct en CSS,
  sera refuse. Le message d'erreur donne la forme exacte a ecrire, et le cout d'un formalisme est
  prefere ici a celui d'un analyseur qui lirait charitablement des formes approchantes - c'est la
  meme arbitrage qu'`ADR-0022` et pour la meme raison.
- **Mauvaise consequence assumee, la troisieme** : `proposition` et `colonne` sont cherches en texte
  brut dans le JavaScript maison. Un commentaire de `recreo.js` qui contiendrait le mot
  "proposition" fera echouer la suite. `ADR-0008` a deja acte cette brutalite et son motif tient :
  le message nomme le fichier et le mot, la correction prend dix secondes.
- Le nombre de propositions par paquet devient une contrainte visible de mise en page alors qu'il
  n'etait qu'une donnee de contenu. C'est un couplage nouveau entre le catalogue et l'ecran, il est
  reel, et il est la vraie raison pour laquelle la section 4 refuse d'ecrire trois quelque part.
- **Une story de s07 et `DEC-0014` se contredisent** sur "Le bon verbe". C'est remonte, pas resolu
  ici.

## Alternatives considerees

- **Ouvrir l'ilot JavaScript nomme par `ADR-0008`.** C'etait la sortie attendue, et elle est inutile :
  une animation CSS demarre a l'insertion de l'element, htmx insere le fragment, donc le declencheur
  existe deja sans une ligne de script. Ecartee, et c'est le point ou j'ai le plus change d'avis en
  lisant le code plutot qu'en raisonnant sur les principes.
- **Faire du levier un nouveau bouton avec sa propre route.** Ecartee, section 3 : le bouton qui ouvre
  une manche existe deja dans les deux branches que `camille-po` peut trancher, et un bouton de plus
  se paie sur `ADR-0017`, sur la cible tactile et sur les 336 px disponibles.
- **Le levier sans JavaScript par `<label>` plus `<input type="checkbox">` plus `:has(:checked)`.**
  Ecartee pour deux raisons independantes, dont la seconde est la plus grave : le controle le plus
  presse de l'application echapperait en silence a la derivation `button, a[href]` de deux garde-fous.
- **Ecrire une regle "aucune proposition ne depasse N caracteres".** Ecartee, avec la mesure qui la
  refute : `oreille` (7 caracteres) tient, `mangez` (6) ne tient pas. Le budget est une largeur de
  glyphes, et une regle en caracteres aurait surpromis.
- **Elargir `ViewportEtLargeurTelephoneTest` au debordement des colonnes.** Impossible, et
  `ADR-0025` a deja etabli pourquoi et l'a ecrit : sa population est faite de declarations, la
  propriete est faite de pixels rendus. Ecartee sans nouvelle deliberation.
- **Un compte a rebours serveur pendant le defilement** (le serveur refuse un coup avant la fin de
  l'animation). Ecartee : c'est deplacer une decoration dans le domaine, ajouter un aller-retour par
  manche et introduire un etat qui peut se desynchroniser d'une animation qu'il ne voit pas.
- **Ne rien garder et se contenter d'ecrire la frontiere en prose.** Ecartee, c'est l'axiome de ce
  role : une regle qui n'est pas encodee est un souhait. La frontiere de la section 1 aurait tenu le
  temps du sprint et se serait dissoute au suivant, quand quelqu'un aurait ajoute un fondu depuis
  `opacity: 0` en regle permanente.

## Ce qui me ferait changer d'avis

- **`nadia-prof` constate en jouant que le geste du levier manque de sa cause visible**, parce que
  l'ecran change au moment ou l'enfant presse. La reponse serait le levier sur l'ecran de manche, avec
  son etat de domaine, sa route et son cout, tels que la section 3 les chiffre. Le chemin est ecrit
  pour ne pas etre reinvente.
- **Une animation dont la duree devient un enjeu pedagogique mesure** plutot qu'un choix visuel : le
  compte a rebours serveur redeviendrait defendable, mais il faudrait alors que le refus de
  `nadia-prof` porte sur la duree elle-meme, pas sur une impression.
- **Un catalogue ou plus de la moitie des paquets offrent quatre propositions ou des mots longs** : la
  mise en page en colonnes cesserait d'etre la bonne reponse pour l'ensemble du catalogue, et la
  question redeviendrait produit - une machine a sous pour les paquets courts et un autre habillage
  pour les autres, ce qui suppose que la mecanique porte cette variation, donc un ADR.
- **Un besoin reel de piloter une animation par le JavaScript** (une sequence que le CSS ne sait pas
  enchainer). L'ilot d'`ADR-0008` s'ouvrirait alors par un ADR nomme, et la regle 1 devrait etre
  transposee au script, ce qu'elle ne sait pas faire aujourd'hui - elle lit du CSS.

## Verification

`mvn -B clean verify` sur un JDK 21.0.10 designe par `JAVA_HOME` le temps de la commande, dans un
worktree dedie descendant de `round-two` (`outils/verifier-lignee.sh` vert avant toute ecriture).
Constat de JDK rendu par `outils/verifier-jdk.sh` : `java` du `PATH` 1.8.0_202, JDK de compilation
21.0.10, `JAVA_HOME` vide hors de la commande, toolchains non lu par Maven.

**Resultat : BUILD SUCCESS**, les six modules du pom agregateur en SUCCESS.

**Compte de cas de test et sa methode.** Perimetre : les 53 rapports XML de
`*/target/surefire-reports/` et `*/target/failsafe-reports/`, apres un `clean`, somme de l'attribut
`tests` de chaque `<testsuite>`. Les rapports XML et non les rapports texte, parce que deux moteurs
JUnit tournent dans ce depot et que le rapport texte n'en garde qu'un - c'est la troisieme des trois
causes qui m'avaient fait annoncer un compte faux. Resultat : **290 cas surefire plus 3 cas failsafe,
soit 293, zero ignore**. L'etat de depart, `round-two` a `28a419f`, en comptait **279** ; le delta de
**+14** se decompose en 11 cas de `LAnimationRetardeMaisNeDetermineRienTest` et 3 cas ajoutes a
`TailleDesTextesEtBoutonsTest`, la methode d'ecart amendee restant un seul cas sous son nouveau nom.

**Constat de rendu.** Les mesures de cet ADR sont toutes issues de `outils/mesurer-rendu.sh` a 360 px
et a 375 px, sur une maquette jetable **non commitee** dont le contenu est decrit dans la section
"mesure" et dont le seul delta avec la production est `flex-direction: row` et `flex: 1 1 0`.
