# Les trois dessins du premier lot de designation (`S33-PAL2-1`) - proposition, pas un Done

- **Auteure** : `ines-design`. **Date** : 2026-09-10. **Sprint** : s34.
- **Destinataires** : `lea-dev` (encodage du contenu, `L-e`, et le chemin de jeu qui pose les
  boutons de zone par-dessus ces dessins) ; `nadia-prof` (audit pedagogique, instance B, `ZON-*`
  et `FER-1` a `FER-3`) ; `theo-archi` (le point route en section 5) ; `sofia-sm` (integration).
- **Ce que ce document est, et ce qu'il n'est pas** : une proposition de forme. Je ne tranche ni
  le Done pedagogique, ni l'exactitude d'un fait, ni la formulation finale d'un enonce ou d'un
  texte alternatif - ce sont `nadia-prof` et `lea-dev`. Rien ici n'est acte tant que le depot ne
  porte pas le commit qui l'integre.

---

## 1. Ce qui a gouverne les trois dessins, avant leur description

`ADR-0080` : une designation est un choix parmi des **zones enumerees et denombrables**, jamais
une coordonnee continue. Les trois dessins ci-dessous ne portent donc aucune zone "au hasard" :
chacune est un rectangle nomme (`CadreDeZone`, quatre entiers en pourcentage de la boite de
l'image), place a un endroit du dessin qui existe reellement en tant que meuble, evenement ou
moment, et non une decoupe arbitraire de la surface.

Trois fichiers, dans `adapters/adapter-web/src/main/resources/static/illustrations/` une fois
integres (ils sont ecrits ici, dans ce repertoire de sprint, pour que `sofia-sm` les deplace au
commit qui les integre - je ne commite ni ne pousse) :

| Fichier | Ligne servie | Ce qu'il montre |
|---|---|---|
| `plan-classe.svg` | `QLM-CP-11` | Un plan de classe vu de dessus, six zones |
| `frise-journee-ecole.svg` | `QLM-CP-14` (dessin A) | La journee d'ecole, six evenements sur un axe |
| `frise-soir-routine.svg` | `QLM-CP-14` (dessin B) | La routine du soir, quatre evenements dont deux "en meme temps" |

## 2. Format technique - DES-1 a DES-7, et leur lecture pour le palier 2

`contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md` (s25) a ecrit `DES-1` a `DES-7` pour le
palier 1 (`.image-item`, plafond 176 px). Le palier 2 a sa propre classe (`.image-designation`,
`recreo.css`, deja arbitree par moi et acceptee dans la feuille de style : plafond 22 rem =
352 px). Je restate ici la lecture de `DES-3` pour cette classe, parce qu'aucun document ne le
faisait encore et que les trois fichiers en dependent :

- **`DES-1`, `DES-2`** tenues a l'identique : `viewBox` et `width`/`height` en unites
  utilisateur, jamais de pourcentage, au meme rapport (trivial ici : mes trois fichiers declarent
  un `viewBox` dont les nombres sont litteralement ceux de `width`/`height`).
- **`DES-3`, lu pour `.image-designation`** : plafond **352 px** (pas 176). `plan-classe.svg` le
  declare exactement (352 x 352, carre). Les deux frises declarent **320 px** de hauteur, **sous**
  le plafond avec une marge deliberee de 32 px : leur rapport 2:1 est celui que la feuille de
  style a deja mesure comme "le plus large de la famille" (`recreo.css`, commentaire de
  `.image-designation`) ; rester legerement en-dessous du plafond a ce rapport laisse une marge de
  securite sur les largeurs de conteneur les plus etroites (720 px) sans rien couter en
  lisibilite - un calcul au plafond exact (469 px de large a 352 px de haut) s'en approcherait
  sans marge. Aucun chiffre n'a de sens ici pour "l'encre d'un chiffre" (14 px) : aucun de mes
  trois dessins ne trace de chiffre.
  **Plancher de trait tenu, calcule et non suppose** : `plan-classe.svg` rend 1 unite de `viewBox`
  a 3,52 px (352 / 100) - son trait le plus fin (0,8 unite) rend donc 2,82 px, au-dessus du
  plancher de 2 px. Les deux frises declarent leur `viewBox` en unites egales aux pixels (640 =
  640, 320 = 320) - leur trait unique (3 unites) rend donc 3 px, au-dessus du plancher.
- **`DES-4`** tenue : aucun `vector-effect`, trait en unites utilisateur.
- **`DES-5`** tenue : `stroke="#1c2733"` ecrit en clair sur chaque `<svg>`, jamais `currentColor`.
- **`DES-6`** tenue : l'icone "ecole" (livre) est definie une fois dans `<defs>` et posee deux
  fois par `<use>`, plutot que redessinee.
- **`DES-7`** tenue : aucun element `<text>`, aucune lettre. Chaque evenement de frise est un
  pictogramme trace, jamais un mot.
- **Seul ecart au regime "contour pur" du palier 1** : les trois points du biscuit de `gouter`
  sont remplis (`fill="#1c2733"`) plutot que traces en contour. Ce n'est pas une couleur - c'est
  la meme encre, pleine plutot qu'en trait - et je le nomme explicitement parce que c'est un choix,
  pas un oubli de `fill="none"`.

## 3. Ce que j'ai mesure au rendu, pas seulement au code

Rendu reel (Chrome headless, capture directe des trois fichiers) avant de figer ce document.
**Quatre defauts trouves et corriges par cette mesure, aucun des quatre visible en lisant le SVG :**

1. **L'icone "ecole" v1** (deux courbes de Bezier partant des cotes vers un sommet central)
   rendait un "m" en double bosse, pas un livre. Remplacee par un rectangle traverse d'une ligne
   centrale (couverture + ligne de reliure) : sans ambiguite au rendu.
2. **Le biscuit de `gouter` v1** (six petits traits radiaux) rendait une roue dentee, pas un
   biscuit. Remplace par un cercle a trois points pleins (pepites) : lisible au rendu.
3. **Le croissant de lune v1** (deux arcs de rayons 38 et 30 entre les memes deux points) rendait
   un arc ouvert, pas un croissant ferme - la corde entre les deux points vaut 70 unites, donc
   aucun arc de rayon inferieur a 35 ne peut relier ces deux points sans que le moteur de rendu ne
   corrige silencieusement le rayon, ce qui degenere la forme. Corrige a 50 et 36 (tous deux
   superieurs a 35) : un croissant ferme et net au rendu, verifie sur cinq variantes avant de
   choisir celle-ci.
4. **Le biscuit de `gouter`, une seconde fois** : son rayon d'origine (18, centre a x=45 local)
   depassait le bord droit du `viewBox` (640) une fois translate a x=592 - le motif exact que
   `ZON-13` interdit ("aucune zone ne deborde du dessin"), ici sur le dessin lui-meme et pas
   seulement sur une zone. Rapproche et retreci (centre a x=28, rayon 15) : entierement dans le
   cadre au rendu.

Aucun de ces quatre defauts n'aurait ete visible a la seule lecture du marquage SVG. C'est
exactement le motif que mon mandat me rappelle : *"un CSS qui compile n'est pas un CSS qui se
voit."* Les fichiers livres ici sont ceux d'apres correction, verifies au rendu une derniere fois.

## 4. Les six zones de `plan-classe.svg` (`QLM-CP-11`)

**Un seul dessin sert la ligne** (`FER-1`, enumeration disjonctive, un plan de classe suffit).
Vue de dessus, aucune perspective (`ZON-12`) : chaque meuble est trace dans sa silhouette au sol
- le tableau est un rectangle mural, le bureau un rectangle avec sa chaise, l'armoire un caisson a
deux vantaux, la porte son arc de debattement (symbole de plan standard), la fenetre une rupture
du trait de mur, le coin lecture un tapis avec sa pile de livres.

| id | libelle (positionnel) | x | y | largeur | hauteur |
|---|---|---|---|---|---|
| `tableau` | "en haut, au centre" | 28 | 6 | 34 | 15 |
| `bureau` | "en haut, a gauche" | 15 | 26 | 28 | 20 |
| `fenetre` | "a droite, sur le mur" | 68 | 26 | 18 | 40 |
| `armoire` | "a gauche, contre le mur" | 6 | 55 | 16 | 22 |
| `coin-lecture` | "en bas, a droite" | 58 | 76 | 34 | 20 |
| `porte` | "en bas, a gauche" | 8 | 80 | 18 | 16 |

**Pourquoi le libelle est positionnel et non nommant (`ZON-10`).** `FER-3` demande au moins deux
des trois taches (`T-A`, `T-B`, `T-C`), et l'exemple donne par `nadia-prof` pour `T-A` sur cette
ligne est *"Montre le tableau"* - un enonce qui nomme sa cible. Des qu'un seul enonce du paquet
fait cela, `ZON-10` interdit un libelle nommant sur **tout le paquet** : mes six libelles decrivent
donc un emplacement dans l'image, jamais l'objet qu'ils portent. Verification que cela tient
`ZON-1` : en effacant l'image, entendre les six libelles ne dit a personne lequel est "le tableau"
- seule l'image porte cette information.

**Ordre de declaration (`ZON-11`, haut en bas puis gauche a droite, sur le bord superieur de
chaque cadre)** : `tableau` (y=6), `bureau` (y=26, x=15), `fenetre` (y=26, x=68), `armoire`
(y=55), `coin-lecture` (y=76), `porte` (y=80). C'est un balayage de la piece coherent (mur du
fond, cote gauche, cote droit, puis fond de la piece), pas seulement un artefact du calcul.

**Non-chevauchement (`ZON-13`)** : verifie paire par paire sur les six rectangles - aucune
intersection, chacun reste dans `[0,100]` sur les deux axes (le plus proche du bord est `fenetre`,
qui touche le mur a x=86, et `coin-lecture`, qui va jusqu'a x=92).

**`ZON-8` (nombre de zones)** : six zones, heterogenes (chaque meuble est different) - au plafond
CP exact, pas au-dela.

**`FER-3` sur ce dessin, exemples illustratifs et non un texte fige** (le libelle final reste a
`lea-dev` et `nadia-prof`) :
- `T-A` : *"Montre ce qui est en haut, au centre."* -> `tableau`.
- `T-B` : *"Montre ce qui est juste au-dessus de la porte."* -> `armoire` (les deux zones sont
  spatialement adjacentes sur le dessin, avec un intervalle de 3 %).

## 5. Les zones des deux frises de `QLM-CP-14`

**Deux dessins, comme l'exige la ligne.** Aucune date ni aucun mot ecrit dans le dessin (`DES-7`) :
chaque evenement est vecu par l'enfant et trace, jamais nomme en toutes lettres.

### 5.1 `frise-journee-ecole.svg` - six evenements, un seul axe

| id | libelle (positionnel) | x | y | largeur | hauteur |
|---|---|---|---|---|---|
| `lever` | "le premier" | 0 | 10 | 15 | 75 |
| `petit-dejeuner` | "le deuxieme" | 17 | 10 | 15 | 75 |
| `ecole-matin` | "le troisieme" | 34 | 10 | 15 | 75 |
| `dejeuner` | "le quatrieme" | 51 | 10 | 15 | 75 |
| `ecole-apres-midi` | "le cinquieme" | 68 | 10 | 15 | 75 |
| `gouter` | "le sixieme" | 85 | 10 | 15 | 75 |

Meme motif de libelle positionnel qu'en section 4 : l'exemple de `T-A` donne par `nadia-prof` pour
cette ligne est *"Montre le gouter"*, donc `ZON-10` interdit un libelle nommant sur tout le
paquet - un ordinal ("le sixieme") est une position sur l'axe, pas un nom.

**Ordre de declaration** : les six zones partagent le meme bord superieur (y=10) ; l'ordre se
reduit donc a un tri par x, qui est exactement l'ordre chronologique gauche-a-droite. Aucune
ambiguite.

**`FER-3`, exemples** : `T-A` *"Montre le lever."* ; `T-B` *"Montre ce qui vient juste avant
l'ecole du matin."* -> `petit-dejeuner` ; `T-C` (si retenu) *"Montre ce qui vient deux moments
apres le reveil."* -> `ecole-matin`.

### 5.2 `frise-soir-routine.svg` - quatre evenements, la relation "en meme temps"

| id | libelle (positionnel) | x | y | largeur | hauteur |
|---|---|---|---|---|---|
| `diner` | "le premier" | 5 | 10 | 26 | 75 |
| `brosse-dents` | "le deuxieme, en haut" | 38 | 10 | 26 | 40 |
| `pyjama` | "le deuxieme, en bas" | 38 | 52 | 26 | 33 |
| `coucher` | "le troisieme" | 71 | 10 | 26 | 75 |

**Pourquoi une colonne partagee.** `FER-1` exige que la relation "en meme temps" soit servie par
au moins un item, et `4.4` de l'arbitrage prealable de s29 le dit explicitement : *"elle suppose
deux evenements a la meme place sur la frise, l'un au-dessus de l'autre."* `brosse-dents` et
`pyjama` partagent donc la meme colonne de temps (x=38 a 64), l'un dans la moitie haute, l'autre
dans la moitie basse, avec un intervalle de 2 % entre les deux (52-50=2, aucun chevauchement,
`ZON-13`).

**Point d'attention sur l'ordre de declaration, nomme et non tranche par moi.** `brosse-dents`
partage le bord superieur (y=10) de `diner` et `coucher` ; `ZON-11` ("haut en bas, puis gauche a
droite") les groupe donc d'abord tous les trois, tries par x, avant `pyjama` (seul a y=52) : l'ordre
qui en resulte est `diner`, `brosse-dents`, `coucher`, `pyjama` - et non `diner`, `brosse-dents`,
`pyjama`, `coucher`, qui suivrait la seule lecture chronologique de gauche a droite. Je n'ai pas
trouve de disposition qui satisfasse a la fois `ZON-11` a la lettre et un ordre purement
chronologique sans reintroduire un chevauchement ou casser l'egalite des bords superieurs entre
zones d'une meme "rangee" ; ce n'est pas un defaut de mes zones mais une consequence du croisement
entre un axe horizontal et une regle de lecture pensee pour un plan a deux dimensions
(planisphere). Je ne tranche pas si c'est acceptable : je le nomme pour que `theo-archi` (qui
derive l'algorithme d'ordre, route par `nadia-prof` sur `ZON-11`,
`ARBITRAGE-PREALABLE-s29-le-palier-2-ce-qu-un-paquet-de-designation-doit-apporter.md`) et `nadia-prof` (qui verifie `ZON-11` au
rejeu) le voient avant l'audit. A noter en attenuation : les libelles eux-memes ("le deuxieme, en
haut" / "le deuxieme, en bas") restent explicites sur la relation temporelle quel que soit l'ordre
de tabulation.

**`FER-3`, exemples** : `T-B` (coeur de la ligne) *"Montre ce qui se passe en meme temps que se
brosser les dents."* -> `pyjama` ; `T-A` *"Montre le moment du coucher."* -> `coucher`.

## 6. Les huit conditions `ZON-*`, une par une, sur ce lot

| Condition | Comment ce lot la rencontre |
|---|---|
| `ZON-7` (recevabilite) | Ne me revient pas : c'est `lea-dev` qui sert l'ecran de manche (`L-c`, `L-e`). Mes dessins ne rendent pas ce lot recevable a eux seuls. |
| `ZON-8` (3 a 6 zones, CP) | `plan-classe.svg` : 6. `frise-journee-ecole.svg` : 6. `frise-soir-routine.svg` : 4. Toutes dans la fourchette. |
| `ZON-9` (zones masquees, la cible se trouve sur le dessin seul) | Verifiable seulement par `nadia-prof` en jouant (elle le dit elle-meme). Ce que j'ai fait de mon cote : verifier, en effacant mentalement chaque libelle, que chaque pictogramme reste identifiable par sa forme seule (section 3, les quatre corrections). Je ne peux pas prejuger de son verdict. |
| `ZON-10` (une seule forme de libelle par paquet) | Positionnelle sur les trois dessins, avec le motif ecrit en sections 4 et 5.1 : des qu'un `T-A` nomme sa cible, le nommant est ferme pour tout le paquet. |
| `ZON-11` (ordre de lecture, haut-bas puis gauche-droite) | Tenue par construction sur `plan-classe.svg` et sur `frise-journee-ecole.svg`. Point d'attention nomme en section 5.2 sur `frise-soir-routine.svg`, route a `theo-archi` et `nadia-prof`, pas resolu unilateralement. |
| `ZON-12` (le dessin est un schema, pas une perspective) | `plan-classe.svg` : vue de dessus stricte, aucun objet en trois quarts. Les deux frises : axe oriente (fleche), evenements en pictogrammes, aucune date chiffree. Aucun des trois dessins ne represente un lieu ou un evenement reel qui se verifierait contre une source (classe et journee fictives, generiques) - c'est precisement le motif pour lequel ce lot a ete choisi (`DEC-0083`, section 13) et je ne l'ai pas reintroduit. |
| `ZON-13` (non-chevauchement, aucun debordement) | Verifie par le calcul en sections 4 et 5 pour les zones ; verifie par le rendu pour les dessins eux-memes (section 3, quatrieme correction : un dessin peut deborder de son propre cadre sans qu'aucune zone ne soit en cause, je l'ai trouve et corrige). |
| `ZON-14` (un item, un geste) | Ne depend pas de mes dessins : c'est la mecanique et le contenu qui font qu'une manche ne demande qu'une seule designation. Rien dans mes trois fichiers ne suggere ou ne facilite une double designation. |

## 7. Ce que je ne decide pas, et a qui je le route

- **Le texte de chaque enonce et de chaque texte alternatif** : `lea-dev` les ecrit, `nadia-prof`
  les audite contre `ZON-1` a `ZON-3`. Mes exemples de `T-A`/`T-B`/`T-C` (sections 4 et 5) sont
  illustratifs, pour montrer que la geometrie les rend possibles - ce ne sont pas des enonces a
  recopier.
- **L'algorithme exact qui derive l'ordre de declaration depuis les cadres** : route par
  `nadia-prof` a `theo-archi` (`ZON-11`, arbitrage prealable de s29) ; je fournis les cadres, pas
  la fonction qui les trie.
- **Le point d'attention de la section 5.2** (l'ordre "haut-bas puis gauche-droite" separe les
  deux zones de la relation "en meme temps" par une troisieme zone) : nomme, pas tranche. Si
  `theo-archi` ou `nadia-prof` juge que cela contredit l'esprit de `ZON-11` sur un axe horizontal,
  je peux redessiner la colonne partagee autrement (par exemple en donnant aux deux zones
  empilees le meme bord superieur que leurs voisines et en les distinguant seulement par une
  hauteur differente) - mais je ne le fais pas d'avance, faute d'un verdict sur la question.
- **Le Done pedagogique des deux lignes** : `nadia-prof` seule, en jouant l'ecran une fois qu'il
  existe (`ZON-7`).
- **La forme technique du champ ajoute au contenu, l'implementation de `DesignationSession`, le
  chargeur du type `designation`** : `theo-archi` et `lea-dev`, hors de mon perimetre.

## 8. Fichiers livres

- `plan-classe.svg`
- `frise-journee-ecole.svg`
- `frise-soir-routine.svg`
- ce document

Tous dans ce repertoire de sprint. Je ne commite pas, je ne pousse pas : `sofia-sm` integre, et
c'est elle qui deplacera les trois `.svg` vers
`adapters/adapter-web/src/main/resources/static/illustrations/` au moment ou `lea-dev` cable le
paquet qui les reference.
