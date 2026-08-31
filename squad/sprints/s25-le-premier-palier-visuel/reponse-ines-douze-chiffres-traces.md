# Douze chiffres traces sont-ils producibles ? - reponse mesuree

- **Auteure** : `ines-design`. **Date** : 2026-08-31. **Sprint** : s25.
- **Branche** : `s25-ines`, worktree dedie. **Arbre de depart** : `round-two` a `130ba64`.
- **La question, telle que `theo-archi` la pose** (`ADR-0073`, amendement du 2026-08-31,
  section 7) : *"Que les chiffres traces soient producibles. Je l'ai demontre sur une piece, a la
  main. Un cadran de douze chiffres est un autre volume de travail, et c'est `ines-design` qui dit
  si c'est producible."*
- **Ce que ce document est** : un verdict de faisabilite avec sa methode, et **une contrainte
  chiffree** plutot qu'un avis - la forme que `nadia-prof` a exigee de ma mesure de pli (`P4`) et
  qui convient ici aussi.
- **Ce que ce document n'est pas** : le lot. Je n'ai pas produit les douze chiffres d'un paquet ;
  j'ai produit **deux demonstrations**, l'une sur un cadran complet et l'autre sur des pieces, et
  c'est sur elles que la mesure porte.
- **Ce que je n'ai pas touche** : aucun fichier de `domain/`, `application/`, `adapters/`,
  `bootstrap/`. Les deux dessins vivent dans le repertoire de sprint et **pas** sous le repertoire
  d'illustrations d'`adapter-web` : le garde-fou bidirectionnel d'`ADR-0073` refuse une illustration
  deposee sans paquet qui la declare, et **une demonstration de faisabilite n'est pas une
  illustration**.

---

## 1. La reponse en une ligne, puis ce qu'elle coute

> **Oui, douze chiffres traces sont producibles.** Ils demandent **dix glyphes, pas douze dessins**,
> aucun `<text>`, aucun `<tspan>`, aucune police. **Mais ils ne sont lisibles qu'a partir de 123 px
> de hauteur d'image, et le pli n'en laisse que 53 apres un echec.**

**C'est donc le *"oui, mais pas a cette taille"* que `sofia-sm` m'a demande de dire si c'etait le
cas, et c'est le cas.** Et la distinction qui compte pour le sprint n'est pas entre *"les chiffres
se tracent"* et *"ils ne se tracent pas"* - elle est **entre les deux paquets** :

| Ligne | Ce que le dessin doit porter | Hauteur d'image minimale mesuree | Budget du pli apres un echec | Verdict |
|---|---|---|---|---|
| **`MON`** - monnaie | **un chiffre par piece** | **43 px** | 53 px | **passe**, avec 10 px de marge |
| **`HOR`** - lire l'heure | **douze nombres sur un anneau** | **123 px** | 53 px | **ne passe pas**, il manque 70 px |

**Ce qui fait tomber `HOR` n'est pas le trace, c'est le pli.** Le trace est fait, il est commite, il
est mesure et il ne coute pas cher. C'est la hauteur qu'il lui faut pour etre vu qui ne rentre pas.

---

## 2. La methode, et son perimetre

**Deux demonstrations commitees**, dans le repertoire de ce sprint :

- `demonstration-chiffres-traces.svg` - un cadran complet, douze nombres, deux aiguilles ;
- `demonstration-pieces-tracees.svg` - trois pieces portant chacune un chiffre grave.

**Outil** : `squad/outils/ines-design/mesurer-un-chiffre-trace.py`. Il inline le dessin dans une
page qui charge **les deux feuilles reelles** de l'application dans l'ordre d'`ADR-0014`, mesure
dans un navigateur reel, et importe les juges purs de
`squad/outils/nadia-prof/balayage-largeur-rangee.py` plutot que de les recopier.

*Le dessin est inline et non charge par `<img>`, pour une raison de mesure et non de gout : un SVG
charge par `<img>` vit dans un document separe dont le script hote ne peut lire aucune boite. Ce que
le navigateur rend est identique ; ce qu'on peut en lire ne l'est pas.*

**Releve** : `squad/conformite/releves-s25/s25-ines-chiffres-traces.json`.

### 2.1 Le critere de lisibilite n'est pas mon oeil

**Il est celui que le produit s'applique deja a lui-meme**, et c'est ce qui le rend opposable :

> **Un chiffre dessine est lisible quand son encre est au moins aussi haute que celle du plus petit
> chiffre que le produit sert deja en texte.**

Le plancher typographique de `recreo` est **1,25 rem**, la taille de `.consigne`, de `.indice` et du
verdict, tenue par `TailleDesTextesEtBoutonsTest`. **Mesure, dans la pile de polices reellement
servie (`system-ui`, graisse 400)** : a 1,25 rem, l'encre d'un chiffre mesure **14,00 px** de haut
et 10,78 px de large ; a 2 rem, la taille de `.enonce`, elle mesure **23,00 px**.

Un chiffre dessine n'a alors pas a etre juge *"assez gros"* par qui que ce soit : **il est aussi
gros que le plus petit chiffre que l'enfant lit deja sur cet ecran**, ou il ne l'est pas.

### 2.2 Le second plancher, celui du trait

Un chiffre trace n'a pas que de la hauteur, il a une epaisseur, et une epaisseur qui tombe sous le
pixel disparait. **Meme methode, meme source** :

> **Le trait d'un chiffre dessine rend au moins 2 px**, la plus fine ligne que `recreo` dessine deja
> en lui faisant porter un sens - la bordure de **0,125 rem** de `.champ-reponse` et des deux
> boutons de sortie.

L'epaisseur est **mesuree sur le rendu**, pas deduite de la declaration : elle est lue par la
matrice de transformation de l'instance, ce qui la rend juste meme si une echelle non uniforme ou un
`vector-effect` etait introduit un jour.

### 2.3 Ce que cette mesure ne couvre pas - `P5` s'applique a elle aussi

- **Elle ne dit rien de la forme d'un chiffre scolaire.** Le 1 a-t-il une base, le 7 une barre, le 4
  est-il ferme, un CP reconnait-il ces traces-la comme les chiffres qu'on lui apprend a ecrire :
  **c'est un jugement pedagogique et il est a `nadia-prof`**, entier. Je trace ; elle juge.
- **Elle porte sur deux dessins, pas sur un paquet.** Les valeurs des pieces, le nombre de pieces
  par item, les heures servies sont des questions de contenu.
- **Elle porte sur la hauteur d'encre et l'epaisseur de trait**, pas sur le contraste - celui-ci est
  mesure ailleurs (14,09:1 pour `--recreo-texte` sur `--recreo-fond`).
- **Elle suppose la disposition de mes deux dessins.** Une autre mise en page du cadran donnerait un
  autre rapport ; la section 4 dit ce que j'ai mesure a ce sujet, et jusqu'ou cela va.

---

## 3. Ce que les deux demonstrations etablissent

| Ce qui est mesure | Cadran, douze nombres | Trois pieces, un chiffre chacune |
|---|---|---|
| Glyphes definis | **10** | **10** (les memes, recopies) |
| Instances de glyphe | **15** | 3 |
| `<text>` ou `<tspan>` dans le rendu | **aucun** | **aucun** |
| Boites de glyphe qui se recouvrent | **0** | **0** |
| Encre d'un chiffre, en part de la hauteur d'image | **11,4 %** | **52,1 %** |
| Hauteur d'image pour 14 px d'encre (plancher du produit) | **122,8 px** | 26,9 px |
| Hauteur d'image pour 23 px d'encre (taille de l'enonce) | 201,8 px | 44,2 px |
| Hauteur d'image pour un trait de 2 px | 111,1 px | **43,0 px** |
| **Minimum qui gouverne** | **123 px** (c'est la hauteur d'encre qui mord) | **43 px** (c'est le trait qui mord) |

**Le fait de production qui repond a la question de `theo-archi`, et qui est le vrai resultat de ce
document** : *"douze chiffres"* n'est pas douze dessins. **Ce sont dix glyphes definis une fois, et
quinze instances** - les nombres 1 a 9 en portent une chacun, 10, 11 et 12 en portent deux.
**Le douzieme nombre coute un `<use>`, pas un glyphe.** Et les dix glyphes du cadran sont **les
memes** que ceux des pieces : le jeu s'ecrit une fois pour la famille visuelle entiere, pas une
fois par paquet.

**Pourquoi le trace est facile plutot que penible** : chaque chiffre est un **squelette** epaissi
par `stroke-width` - la ligne que la main de l'enfant suit quand il l'ecrit -, pas un contour rempli
extrait d'une police. Un contour extrait n'aurait de toute facon pas ete une option : **le depot ne
vendorise aucune police**, comme `theo-archi` le releve, donc un glyphe copie d'une police locale ne
serait reproductible sur aucun autre appareil.

---

## 4. Le cadran ne se sauve pas en grossissant ses chiffres - mesure

La reponse evidente a *"123 px, c'est trop"* est *"faites les chiffres plus gros"*. **Je l'ai
essayee et mesuree plutot que de l'ecarter.**

**Methode** : le meme cadran, le meme anneau de rayon 124, les nombres regeneres a six echelles, en
comptant a chaque echelle **les boites de glyphe qui se recouvrent** - deux chiffres colles ne se
lisent pas, quelle que soit la hauteur de l'image.

| Echelle des nombres | Boites qui se recouvrent | Encre / hauteur d'image | Image minimale pour 14 px d'encre |
|---|---|---|---|
| **0,80** (celle du dessin commite) | **0** | 11,4 % | **123 px** |
| 0,90 | 1 | 12,8 % | 109 px |
| 1,00 | 1 | 14,3 % | 98 px |
| 1,10 | 1 | 15,7 % | 89 px |
| 1,20 | 6 | 17,1 % | 82 px |
| 1,30 | 8 | 18,5 % | 76 px |

**Deux lectures, et la seconde est celle qui compte** :

1. **La seule echelle sans aucun recouvrement est celle du dessin commite**, et elle donne 123 px.
   Une mise en page plus fine - anneau plus large, reperes horaires raccourcis, ecart de paire
   retouche - descendrait probablement en dessous ; **je ne l'ai pas produite, donc je ne la chiffre
   pas**.
2. **Meme en poussant jusqu'a l'echelle que je n'accepterais jamais** - 1,30, huit paires de
   chiffres qui se touchent - **le cadran demande encore 76 px, contre 53 px que le pli laisse**.
   **La conclusion ne depend donc d'aucun choix de mise en page** : il n'existe pas de cadran a douze
   nombres lisibles dans 53 px de haut.

---

## 5. Ce que j'en conclus, et ce que je laisse a d'autres

### 5.1 Ce que je tranche, parce que c'est de mon metier

- **Le trace est producible**, pour douze nombres comme pour un chiffre grave, sans `<text>` et sans
  police. La clause 4 d'`ADR-0073` n'est pas un obstacle a `HOR` ni a `MON`.
- **La contrainte est une hauteur d'image minimale, chiffree** : **43 px** pour une piece, **123 px**
  pour un cadran a douze nombres. Ce sont des budgets, pas des verdicts : ils se respectent a
  l'ecriture d'un dessin.
- **`MON` passe le pli, `HOR` ne le passe pas.** C'est la seule frontiere que mes chiffres tracent,
  et elle ne passe pas la ou la question la placait.

### 5.2 Ce que je ne tranche pas

- **Si un CP reconnait ces formes.** `nadia-prof`, entiere. Si elle refuse la forme de mon 1, de mon
  7 ou de mon 4, le trace se refait ; le rapport d'encre, lui, ne bougera pas de facon sensible, et
  les 123 px non plus.
- **L'exactitude de ce qui serait dessine** - graduations, position des aiguilles, valeurs des
  pieces. Elle en est seule garde et elle l'a ecrit.
- **La composition du lot.** `+4` lignes ou `+3` est a `camille-po` et `sofia-sm`. Mon chiffre dit
  seulement que la separation ne passe pas entre *"les chiffres se tracent"* et *"ils ne se tracent
  pas"* : **elle passe entre `MON` et `HOR`, et le juge est le pli**.
- **Le pli lui-meme.** Il est ferme par `DEC-0051`, arbitrage de l'utilisatrice, sorti de la porte
  pedagogique par `DEC-0053`. **Ni `nadia-prof` ni moi ne pouvons le deplacer.** C'est la dependance
  `DV-7` de ma proposition, et `HOR` est le premier cas concret qui s'y heurte.

### 5.3 Ce que ma reponse change au tableau de `nadia-prof`

Elle chiffre le sprint a **31/41 (75,6 %)** si les chiffres peuvent etre dessines, **30/41 (73,2 %)**
sinon. **Ma reponse n'est aucune des deux, et je ne la fais pas passer pour l'une d'elles** :

- les chiffres **peuvent** etre dessines - donc ce n'est pas la seconde ligne ;
- mais `HOR` ne tient pas au-dessus du pli - donc ce n'est pas la premiere non plus, **a moins que
  le pli ou l'etat sur lequel il se mesure ne bouge**.

**Le choix reste entier et il n'est pas de moi** : ou bien `HOR` sort du lot et le sprint prend
`MON` sans elle, ou bien l'arbitrage du pli remonte a l'utilisatrice avec ce chiffre-ci en main.
**`sofia-sm` a ecrit qu'un sprint a trois lignes est un bon sprint quand meme ; je n'ai pas a en
juger, mais je peux dire que rien dans ma mesure ne l'oblige.**

---

## 6. La condition `CON-5` et mon texte de remplacement

`nadia-prof` pose `CON-5` le meme jour : *"le texte alternatif decrit ce qu'il y a a regarder,
jamais la reponse ni ce qui permet de la deduire"*, avec le motif que **c'est le seul endroit du
produit ou une reponse peut fuir sans que personne s'en apercoive**, et qu'un enfant qui utilise un
lecteur d'ecran ne ferait alors pas le meme exercice qu'un autre.

**Sa condition tranche la question que je posais ouverte** dans `proposition-ines-palier-1-du-visuel.md`,
section 9, et **je m'y range** : c'est l'option (d) de mon tableau - un texte ecrit item par item par
le contenu, sous son audit - avec la borne de l'option (c) : il ne porte aucune information qui
permette de repondre.

**Ce que j'ajoute a sa condition, et qui est de mon perimetre, parce que cela la renforce** :
`alt` **n'est pas un canal reserve au lecteur d'ecran**. C'est aussi ce que le navigateur **affiche
en clair a l'ecran** quand l'image ne charge pas. Un texte alternatif qui livrerait la reponse ne la
livrerait donc pas seulement a l'enfant qui ecoute - **il l'afficherait a l'enfant qui voit, des que
le reseau flanche**. Sa condition est donc encore plus necessaire qu'elle ne l'ecrit, et le defaut
d'equite qu'elle nomme a un second visage.

**Ce que je ne peux pas verifier, et que je dis plutot que de le taire** : ce depot n'a **jamais**
fait ecouter un lecteur d'ecran - `F11` et `ADR-0035` bloquent depuis s10 l'emplacement du nom
accessible du cabinet pour ce motif exact. **Je ne peux donc pas mesurer ce que ces textes
produisent a l'oreille.** Ma proposition maintient le seul point qui soit de moi : le gabarit ecrit
`th:alt="${...}"` et **ne fige aucun litteral**, pour qu'aucun lot ne rende a la place de
`nadia-prof` un arbitrage qui est le sien.

---

## 7. Impact

- `squad/sprints/s25-le-premier-palier-visuel/reponse-ines-douze-chiffres-traces.md` (ce fichier) :
  cree.
- `squad/sprints/s25-le-premier-palier-visuel/demonstration-chiffres-traces.svg` : cree - cadran de
  douze nombres, demonstration de faisabilite, jamais du contenu servi.
- `squad/sprints/s25-le-premier-palier-visuel/demonstration-pieces-tracees.svg` : cree - trois
  pieces, meme statut.
- `squad/outils/ines-design/mesurer-un-chiffre-trace.py` : cree.
- `squad/conformite/releves-s25/s25-ines-chiffres-traces.json` : cree.
- **Aucun fichier de `domain/`, `application/`, `adapters/`, `bootstrap/` n'a change.** Aucune
  illustration n'est deposee sous le repertoire que le garde-fou d'`ADR-0073` surveille.

---

## 8. Porte technique

`bash outils/mvn-jdk21.sh -B clean verify` a la racine du reacteur, sur l'arbre de ce worktree :
**BUILD SUCCESS, 937 tests, 0 echec**. Perimetre : le reacteur complet, aucun `-pl`, donc aucun
`-am` a citer. **La machine virtuelle qui a execute ces tests rapporte la version majeure 21** -
verifie a cette execution par `LaMachineVirtuelleDesTestsPorteLaVersionQueLeBuildExigeTest`
(`ADR-0071`), jamais lu dans une declaration de toolchain.

Le detail de cette porte, et le motif pour lequel cet arbre porte une fusion de `s25-theo`, sont
dans `proposition-ines-palier-1-du-visuel.md`, section "Porte technique" : ma premiere execution a
echoue parce que ce document-ci cite `ADR-0073`, que `round-two` ne porte pas encore.
**`ToutesLesReferencesDeDecisionResolventTest` a mordu exactement comme il devait.**

**Les deux dessins de demonstration ne sont vus par aucun garde-fou de contenu, et c'est voulu** :
`LIllustrationDeContenuResoutEtAucuneNeDortTest` derive sa population du repertoire d'illustrations
d'`adapter-web`, ou ces deux fichiers ne sont pas. Une illustration deposee la sans paquet qui la
declare rougirait - c'est la seconde direction du garde-fou de `theo-archi`, et une demonstration de
faisabilite n'a rien a y faire.
