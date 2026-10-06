# S52 - contraintes de dessin de l'instance C (mathematiques CE1) - proposition, pas un Done

- **Auteure** : `ines-design`. **Date** : 2026-10-06. **Sprint** : s52.
- **Arbre** : `round-two`, HEAD `4aad468b`, arbre de travail propre a l'ouverture.
- **Ce qui m'est pose** : `sofia-sm`, question Q3 de l'avis de faisabilite de s51 (section 7) et
  ligne `ines-design` de l'arbitrage de `nadia-prof` du 2026-10-06 (section 8) - *"Contraintes de
  dessin des figures a lignes candidates, des quadrillages 6 x 6 et des reseaux de points, avant
  tout dessin de C"*. Destinataire : le developpeur de dessin que `sofia-sm` affecte (Q5), pour
  `MAT-CE1-17` (`SYM-CE1-1` a `-3`, `ZON-13`) et `MAT-CE1-06` (`MUL-CE1-5`).
- **Ce que je ne dessine pas** : aucun SVG livre au paquet. Les trois exemples ci-dessous sont des
  **modeles de contrainte**, jetables, ecrits dans ce document et nulle part sous `adapters/`.
- **Ce que je ne tranche pas** : la porte pedagogique est a `nadia-prof` seule. Tout ce qui suit est
  une proposition de forme ; les points qui touchent a ce qu'un dessin *montre* sont listes en
  section 5, en questions.

---

## 1. Methode et mesures communes

### 1.1 Methode

Rien ci-dessous n'est lu dans un fichier seul. Trois mesures sur rendu reel :

1. **Largeur servie de l'image de designation.** Le jar de `bootstrap/target` (construit le
   2026-10-06), lance sur un port libre ; fragment reel obtenu par `POST /parties/designation-plan-de-la-classe`
   (meme gabarit, meme feuille, meme conteneur que le jeu) ; page de l'accueil du niveau CP rendue
   avec ce fragment dans `#jeu` ; huit iframes `srcdoc` de 320 a 1280 px de large (la fenetre
   headless ne descend pas sous 516 px, `outils/mesurer-rendu.sh` ; l'iframe contourne le plancher)
   ; `getBoundingClientRect()` sur `.image-designation` et sur chaque `.zone`.
2. **Rendu des exemples** : Chrome headless, feuille `recreo.css` reelle, `<img class="image-item
   image-designation">` et boutons `.zone` positionnes en pourcentage comme
   `fragments/designation.html`, a 352 px et a 296 px.
3. **Contraste** : luminance relative WCAG 2.x, `L = 0,2126 R + 0,7152 G + 0,0722 B` apres
   linearisation sRGB, rapport `(Lclair + 0,05) / (Lfonce + 0,05)`. Un fond translucide est melange
   avant calcul (`alpha x avant-plan + (1 - alpha) x fond`).

### 1.2 Largeur servie : la mesure qui change les cadres

| Largeur de fenetre | Image de designation (declaree 352 x 352) |
|---|---|
| 320 px | **296 x 296** |
| 360 px | 321 x 321 |
| 390 px | 351 x 351 |
| 412 px et plus | 352 x 352 (aucun debordement horizontal mesure de 320 a 1280) |

**L, la largeur servie la plus etroite mesuree, vaut donc 296 px**, et non 352. Le garde-fou 3
d'`ADR-0080` calcule a 352 (`avis de faisabilite s51`, section 5.4) : un cadre de 14 % y passe
(49,3 px) et tombe a 41,4 px a 320 px de fenetre. Tout ce qui suit est derive de 296.

### 1.3 Tailles minimales de cible tactile

**Regle citee, non recalculee** : `nadia-prof`, arbitrage de s51 section 6.4 et avis de faisabilite
de s51 section 5.4 - une zone fait **au moins 44 px** de cote rendu, deux zones voisines ne se
chevauchent pas (`ZON-13`), le plafond de zones d'une rangee est la partie entiere de **L / 44**.
Le plancher de 44 px est celui de `ADR-0080` (garde-fou 3) et de `TailleDesTextesEtBoutonsTest`.
Je ne calcule aucun plafond de zones : les deux familles de designation ci-dessous n'en ont pas
besoin (zones posees sur le pourtour, ou une case = une zone). J'applique seulement la valeur de
L mesuree en 1.2.

> **Consequence, et c'est ma seule arithmetique** : le cadre d'une zone vaut **au moins 16 unites**
> sur un cadre de vue de 100 (352 x 352 declare, un pourcentage de `CadreDeZone` est alors une
> unite du dessin, convention de l'avis s51 section 5.2). 16 unites rendent 56,3 px a 352 px et
> **47,4 px a 296 px**. 15 unites rendraient 44,4 px a 296 : au-dessus du plancher de 0,4 px, donc
> sans marge ; j'ecarte 15.

### 1.4 Palette et contrastes

Le garde-fou impose une seule couleur de trait, `#1c2733` (`DES-5`), sans remplissage de teinte
differente (`COU-2`, `FIG-3`). **Aucune information ne passe donc par la couleur** : le canal
non colore est la forme, l'epaisseur et le motif de trait, ce qui tient pour un enfant daltonien
sans rien ajouter.

| Paire | Valeurs | Rapport |
|---|---|---|
| trait contre fond de page | `#1c2733` / `#f4f7fb` | **14,09:1** (identique a `DES-5`) |
| trait contre blanc | `#1c2733` / `#ffffff` | 15,14:1 |
| bordure de zone contre fond de page | `#2e6f9e` / `#f4f7fb` | 5,03:1 |
| numero de zone contre fond de zone (blanc a 55 %) | `#1c2733` / `#fafbfd` | 14,62:1 |
| numero de zone contre fond de zone **posee sur un trait** | `#1c2733` / `#999ea3` | 5,60:1 |
| bordure de zone **posee sur un trait** | `#2e6f9e` / `#999ea3` | **2,00:1, sous 3:1** |

La derniere ligne motive le refus de poser une zone sur un trait (sections 2.4 et 2.6) : le chiffre
reste lisible, la bordure qui dit "ceci est un bouton" ne l'est plus. Seuil de 3:1 : graphique non
textuel, comme `DES-5`.

### 1.5 Ce qui est reconduit tel quel

`DES-1` a `DES-7` (`squad/sprints/s25-le-premier-palier-visuel/contrainte-ines-ce-qu-un-dessin-servi-doit-declarer.md`) :
`viewBox` et `width`/`height` en unites utilisateur au meme rapport, jamais de `vector-effect`,
teinte en clair `#1c2733`, aucun element de texte, trait rendu d'au moins 2 px **a la largeur servie
la plus etroite** (296 px, pas a 352). Plafond de hauteur : 352 px en designation, 176 px en item
illustre. Cliquet de poids : 4 633 octets par dessin, `POIDS_TOTAL_MAXIMUM` deplace par le commit
qui installe les dessins (`ADR-0036` clause 5, avis s51 section 5.2).

---

## 2. Type A - figure a axe de symetrie, lignes candidates qui se croisent (`MAT-CE1-17`)

### 2.1 Dimensions

- `viewBox="0 0 100 100"`, `width="352" height="352"`. Carre. C'est ce qui rend les cadres exacts
  et ce que le garde-fou 3 sait juger.
- Une figure **par dessin**, un dessin **par item** (avis s51 section 5.1) : au moins dix.
- Le dessin est inscrit dans `DESSINS_DE_DESIGNATION` par le developpeur, a la main
  (`adapter-web` ne voit pas le contenu).
- Poids : un dessin de trois lignes pese environ 490 octets sans en-tete ; marge confortable sous
  4 633 meme avec un en-tete commente de 1 Ko.

### 2.2 Traits (trois niveaux, un seul canal de couleur)

| Element | Epaisseur (unites) | Rendu a 352 / 296 px | Motif |
|---|---|---|---|
| contour de la figure | 2,2 | 7,7 / 6,5 px | plein, `stroke-linejoin="round"` |
| ligne candidate | 1,4 | 4,9 / 4,1 px | **tiretee `5 3.5`**, bouts plats (jamais `round` : le bout rond mange l'espace) |

- **Le contour porte "ceci est la figure", le tirete "ceci est une ligne qu'on essaie".** Deux
  canaux non colores (epaisseur et motif) : le rapport d'epaisseur est de 1,57, le motif suffit
  seul a les separer.
- **Toutes les lignes candidates d'un item ont le meme trait, la meme epaisseur, le meme motif.**
  Un tirete different pour l'axe le designerait (`ZON-9`).
- **Seuil de tirete** : l'essai de s39 (`contrainte-ines-solides-ce1.md`, 1.2) a mesure qu'un segment
  de 6 unites sur 100 a 176 px (10,56 px) et un intervalle de 5 unites (8,8 px) se lisent comme un
  tirete. Ici : segment `5` = 17,6 px a 352 et 14,8 px a 296 ; intervalle `3.5` = 12,3 px et
  10,4 px. Les deux restent au-dessus de ces seuils a la largeur servie la plus etroite. **Ne pas
  descendre sous segment 10,56 px et intervalle 8,8 px a 296 px.**

### 2.3 Geometrie de la figure

- La figure tient dans le tiers central : **entre 20 et 80 sur l'axe long** et ne touche pas le
  bord du cadre, pour laisser le pourtour aux zones et au prolongement des lignes.
- Contour fermant, sans remplissage (`fill="none"`).
- La diagonale d'un rectangle non carre (`SYM-CE1-2`) passe par deux coins : elle se dessine de
  coin a coin **puis se prolonge** de part et d'autre (recevable, arbitrage s51 6.3, point 3).
  Elle croise toujours la mediane au centre : c'est inevitable, pas un defaut de dessin.

### 2.4 Lignes candidates et zones - la forme que je propose

Le probleme : une zone est un **bouton translucide** (fond blanc a 55 % d'opacite, bordure `#2e6f9e`)
pose par-dessus l'image, et son numero est centre. Pose *sur* sa ligne, il masque le trait et sa
bordure tombe a 2,00:1 (section 1.4). `ZON-15` (b) interdit qu'un libelle couvre un trait de la
ligne qu'il designe.

**Forme proposee : la ligne s'arrete au bord de sa zone.** Chaque ligne candidate va de son
extremite libre jusqu'au **bord du cadre de sa zone** (point ou la ligne entre dans le cadre) ; la
zone est un **onglet au bout de la ligne**, a l'exterieur de la figure, sur le pourtour. Le numero
est centre dans l'onglet et ne recouvre aucun trait.

- Le point de croisement de deux lignes n'appartient a aucune zone (arbitrage s51 6.3, point 2).
- Une zone ne contient **aucun** segment d'une autre ligne ni le contour de la figure.
- Toutes les zones d'un item ont **la meme taille : 16 x 16 unites** (`LaBonneZoneNEstPasPredictibleSurUnPaquetTest`,
  avis s51 5.3).
- Le cadre d'une zone est **entierement dans le cadre de vue** (centre a au moins 8 unites du
  bord).
- Une seule ligne par item est un axe (arbitrage s51 6.3, point 4) ; au moins trois lignes
  candidates par item (`ZON-8`, plancher cite en 6.4).

### 2.5 Controles geometriques, a faire par qui dessine (avant de commiter)

Calcul sur les coordonnees du fichier, rejouable avec un script de poste de quelques lignes :

1. **Zone / zone** : aucune paire de cadres ne se chevauche, et l'ecart est d'au moins 5 unites.
   Mesure sur le modele : 34 unites.
2. **Zone / ligne etrangere** : la distance de chaque ligne a la zone d'une autre ligne est d'au
   moins **5 unites** (17,6 px a 352, 14,8 px a 296). Mesure sur le modele : 7,8 unites. Seuil
   propose par moi, a mesurer sur enfant par `nadia-prof` si elle le juge utile : il est la
   moitie d'une zone moins la marge d'un doigt, pas une valeur etablie.
3. **Points singuliers** (croisement de deux lignes, ligne qui coupe le contour, coin de la
   figure) : **deux points ne sont jamais a moins de 7 unites** l'un de l'autre, sauf le coin
   que la diagonale traverse. Une premiere maquette a quatre lignes avait un triple point (axe,
   oblique et contour a 0,1 unite l'un de l'autre) qui rendait la lecture impossible. Mesure sur
   le modele : 8,0 unites.
4. **Les lignes qui ne sont pas des axes ne sont pas proches d'en etre un sans l'etre** : une
   horizontale decalee de la mediane de moins de 6 unites (21 px) se lit comme l'axe. Mesure sur
   le modele : 8 unites. **C'est un jugement pedagogique** (quel leurre est loyal) : le seuil
   n'est qu'une borne de lisibilite, `nadia-prof` garde la question.
5. **Cadre en pourcentage = unites**, a la decimale pres (`x`, `y`, `largeur`, `hauteur` du
   `CadreDeZone`).

Je n'ai pas verifie de **quatrieme ligne** : a tester avec les controles 1 a 3 avant d'en mettre
une. Un item de trois lignes tient.

### 2.6 Exemple annote conforme (modele de contrainte)

Rectangle 60 x 34, axe = mediane verticale, une diagonale de coin a coin, une horizontale decalee.

```xml
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100" width="352" height="352" role="img"
     fill="none" stroke="#1c2733">
  <!-- contour : plein, 2,2 -->
  <rect x="20" y="33" width="60" height="34" stroke-width="2.2" stroke-linejoin="round"/>
  <!-- ligne 1 (l'axe) : s'arrete au bord du cadre 42,0,16,16 -->
  <line x1="50" y1="92" x2="50" y2="16" stroke-width="1.4" stroke-dasharray="5 3.5"/>
  <!-- ligne 2 (la diagonale, prolongee) : s'arrete au bord du cadre 84,65.8,16,16 -->
  <line x1="8" y1="26.2" x2="84" y2="69.27" stroke-width="1.4" stroke-dasharray="5 3.5"/>
  <!-- ligne 3 (decalee de 8 unites de la mediane horizontale) : cadre 0,50,16,16 -->
  <line x1="93" y1="58" x2="16" y2="58" stroke-width="1.4" stroke-dasharray="5 3.5"/>
</svg>
```

Cadres de zone correspondants (`x`, `y`, `largeur`, `hauteur`, en %) : `42, 0, 16, 16` ; `84, 65.8,
16, 16` ; `0, 50, 16, 16`. Numeros 1, 2, 3 dans l'onglet, libelle complet dans la legende
(`hors-de-la-boite`, `ADR-0094`).

**Mesures sur ce modele, rendu a 352 et a 296 px** : trois lignes lisibles, aucun numero ne couvre
un trait, onglets de 56,3 et 47,4 px, tirets separes a l'oeil, aucun triple point. Les premiers
essais (zones de 14 puis 15 unites, quatre lignes) ont montre le triple point de 2.5 point 3 et une zone
qui couvrait un coin de la figure : les deux sont corriges ici.

### 2.7 Ce qui est refuse

1. Une couleur, un tirete ou une epaisseur propre a l'axe.
2. Une zone posee sur sa ligne : numero centre sur le trait et bordure a 2,00:1.
3. Une zone qui contient un segment d'une autre ligne, un coin de la figure ou son contour.
4. Des zones de tailles differentes dans un item.
5. Un cadre de zone sous 16 unites (sous 44 px a 296 px de large) ou depassant le cadre de vue.
6. Un remplissage de la figure, un degrade, une ombre : un seul niveau de gris, trait seul.
7. Un segment de tirete sous 10,56 px ou un intervalle sous 8,8 px a 296 px de large ; des bouts
   `round` sur un tirete.
8. Un triple point, ou deux points singuliers a moins de 7 unites.
9. Un element de texte, une lettre sur une ligne (`DES-7`) : les reperes sont les numeros de zone,
   servis par le gabarit.
10. Un `<pattern>` ou un `<use>` d'un meme tirete pour economiser des octets : `FIG-4` lit la
    population du fichier.
11. Deux axes traces parmi les candidates (le carre en a quatre, le rectangle non carre deux).

---

## 3. Type B - reseau de points pour la multiplication (`MAT-CE1-06`, `MUL-CE1-5`)

C'est un **item illustre** (`question` a `illustration`, la forme de `ce1-solides`) : plafond
**176 px**, aucune zone, aucune cible tactile. Les reponses sont les boutons de l'ecran, pas le
dessin. **La regle des 44 px de la section 1.3 ne s'y applique pas** ; ce qui borne un point est
la lisibilite, mesuree ci-dessous.

### 3.1 Dimensions

- **Pas fixe de 16 unites, et 1 unite = 1 px au rendu** : `viewBox="0 0 W H"` avec `W = colonnes x
  16`, `H = rangees x 16`, `width="W" height="H"`. Un 10 x 10 mesure donc 160 x 160, sous le
  plafond de 176 ; une fenetre de 320 px offre 296 px, aucun reseau ne retrecit.
- Le centre du point `(j, i)` est en `(16 j + 8, 16 i + 8)` : la marge exterieure est de 3 unites,
  la meme que l'ecart entre voisins.
- **Un reseau est un rectangle de points a pas egal en largeur et en hauteur.** Un pas different
  ferait lire les rangees comme plus proches ou plus espacees que les colonnes.
- **Taille de point et pas identiques d'un dessin a l'autre du paquet** : un point plus gros sur
  un reseau plus grand ferait lire l'aire.
- 2 a 10 rangees, 2 a 10 colonnes (`MUL-CE1-1`, `MUL-CE1-5`).

### 3.2 Point et ecart

- **Disque plein de 10 unites de diametre** (rayon 5), `fill="#1c2733"`, aucun contour. Ecart bord
  a bord : **6 unites (6 px)**, soit 0,6 diametre.
- Mesure : rendu 1:1 de 3 x 5, 4 x 6 et 10 x 10 a l'ecran. Chaque point est separe de son voisin
  sans zoom ; le 10 x 10 se lit encore comme une trame de points distincts. **Je n'ai pas cherche
  la limite basse** (diametre, ecart) : le seuil que je propose est celui de mon essai, pas un
  seuil mesure. Au-dessous, **a verifier au rendu avant de commiter**.
- Le point est un disque : c'est un **point**, pas un cercle, et pas une des formes nommees par le
  programme (`MUL-CE1-5`, exclusion des carres, rectangles, triangles, cercles, solides).

### 3.3 Palette et contrastes

Un seul niveau : `#1c2733`, 14,09:1 contre le fond de page (section 1.4). Pas de couleur, donc
rien a distinguer pour un enfant daltonien. **Aucune rangee ni colonne n'est mise en evidence**
(ni couleur, ni epaisseur, ni cadre) : un reseau mis en evidence dit la reponse.

### 3.4 Poids

Mesure sur fichiers generes (en-tete non commente) :

| Reseau | Un `<circle>` par point | Un seul `<path>` de points |
|---|---|---|
| 3 x 5 | 603 o | 297 o |
| 4 x 6 | 889 o | 367 o |
| 5 x 7 | 1 246 o | 460 o |
| 10 x 10 | **3 395 o** | 1 049 o |

- Jusqu'a **5 x 7** (1 246 octets) : un `<circle>` par point, lisible.
- **10 x 10 avec un `<circle>` par point** : 3 395 octets de trace, plus l'en-tete commente. Il
  reste donc **1 238 octets** de commentaire sous le plafond de 4 633 : serre, et un en-tete du
  type de ceux de s39 (environ 600 octets) y tient. Verifier au poids reel.
- **Variante compacte si un en-tete plus long est voulu** : un seul `<path>` de sous-chemins
  `M x y h0`, `fill="none"`, `stroke-linecap="round"`, `stroke-width="10"`. Chaque point est un
  trace d'un segment de longueur nulle a bout rond : un disque de 10 unites. 1 049 octets pour 100
  points. Rendu compare au 10 x 10 en `<circle>` : identique a l'oeil. **Je ne l'ai pas passe dans le garde-fou** : `FIG-4` lit la population d'un fichier ;
  un `<path>` unique est une forme rendue, sans remplissage, et je ne sais pas ce que le calcul de
  population en dit. Question a `theo-archi`, section 5.
- Jamais un `<pattern>` (`FIG-4`, avis s51 5.2) ni un `<use>` d'un point (pas de gain).

### 3.5 Exemple annote conforme (3 x 5, `viewBox` 80 x 48)

```xml
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 80 48" width="80" height="48" role="img"
     fill="#1c2733" stroke="none">
  <!-- rangee 1 : cy = 8 ; colonnes : cx = 8, 24, 40, 56, 72 ; r = 5 -->
  <circle cx="8" cy="8" r="5"/> <circle cx="24" cy="8" r="5"/> <circle cx="40" cy="8" r="5"/>
  <circle cx="56" cy="8" r="5"/> <circle cx="72" cy="8" r="5"/>
  <!-- rangee 2 : cy = 24 -->
  <circle cx="8" cy="24" r="5"/> <!-- ... meme cx, 5 colonnes -->
  <!-- rangee 3 : cy = 40 -->
  <circle cx="8" cy="40" r="5"/> <!-- ... meme cx, 5 colonnes -->
</svg>
```

(Les elisions `...` sont des commentaires du modele ; le fichier livre ecrit les quinze points.)

### 3.6 Ce qui est refuse

1. Des points de tailles ou de formes differentes dans un meme reseau (`MUL-CE1-5` : elements
   identiques).
2. Un carre, un rectangle, un cercle (contour), un solide en place d'un point (`MUL-CE1-5`).
3. Un cadre, un contour, une ligne de grille autour ou entre les points : un rectangle trace est
   une figure plane nommee, et un trait entre les points est un quadrillage (`MAT-CE1-14`).
4. Une rangee, une colonne ou un groupe de points mis en evidence (couleur, epaisseur, ecart
   different).
5. Un pas different entre rangees et entre colonnes ; un reseau en quinconce ou incline.
6. Un numero de rangee, de colonne, un total, une lettre (`DES-7`, et un reperage en coordonnees
   sert `MAT-CE1-14`, pas cette ligne).
7. Plus de 10 rangees ou 10 colonnes.
8. Un `<pattern>` rempli ; un `<text>` ; un `vector-effect`.
9. Un pas ou un diametre qui varie d'un dessin a l'autre du meme paquet.
10. Un `viewBox` sans `width`/`height`, ou une taille declaree au-dela de 176 (`DES-1` a `DES-3`).

---

## 4. Ce que ce document ne couvre qu'en esquisse - le quadrillage 6 x 6 (`SYM-CE1-3`)

La consigne de cette tache nomme deux types, mais l'avis s51 et la ligne `ines-design` de
l'arbitrage de s52 rangent le quadrillage parmi les trois familles. Une maquette a trouve **un defaut que la
description n'aurait pas montre** :

- **Axe trace sur une case pleine : il disparait.** Une figure de cases pleines `#1c2733`
  contre l'axe `#1c2733` : la ou deux cases pleines se touchent de part et d'autre de l'axe, le
  trait n'existe plus.
- **Forme qui tient (rendu a 352 et 296 px)** : cases de 16 unites en partant de 2 (`2 + 16 k`),
  trait de grille de **0,7 unite** (2,07 px a 296, au-dessus du plancher de 2 px ; 0,6 donnerait
  1,8 px), cases pleines **retrecies a 12 unites** et centrees (marge de 2 unites : la grille et
  l'axe restent visibles entre elles), axe **plein de 2,4 unites** sur la ligne de grille centrale
  (rapport de 3,4 contre la grille). Un axe tirete sur une ligne de grille se lit comme un trait
  plein irregulier : refuse.
- **Zone = une case de 16 unites** (47,4 px a 296), qui touche sa voisine sans la recouvrir. Seules
  les cases vides candidates sont des zones (3 au moins) : pas trente-six arrets de tabulation.
- Mesure non faite : la **legende de 36 libelles** que l'avis demandait. Elle n'est pas
  necessaire si seules les cases candidates sont des zones. A trancher avec `nadia-prof` (5.3).

---

## 5. Points ouverts

### Pour `nadia-prof`

1. **Onglet au bout de la ligne ou zone sur la ligne** (2.4). Le premier garde le numero hors du
   trait, mais l'enfant qui touche la ligne elle-meme ne declenche rien : la cible est l'onglet,
   pas le tirete. Elle a ecrit qu'une zone sur la partie exterieure d'une ligne est recevable
   (6.3 point 3) ; je ne la contredis pas, je propose une forme ou le numero ne couvre aucun trait.
2. **Texte alternatif** (`th:alt`) : il ne doit ni nommer l'axe, ni donner le nombre de rangees et
   de colonnes. Contenu, donc a elle.
3. **Leurre loyal** (2.5 point 4) : un ecart de 8 unites a la mediane est une borne de lisibilite,
   pas un jugement sur la difficulte. **Cases candidates du quadrillage** : toutes les cases vides
   ou un sous-ensemble ? Je propose un sous-ensemble.
4. **Densite du reseau** (3.2) : diametre 10 px, ecart 6 px, rendu 1:1. Qu'un enfant de CE1 compte
   a l'oeil un 10 x 10 a ce pas est un jugement qu'elle seule peut faire en jouant.

### Pour `theo-archi`

1. **Garde-fou 3 d'`ADR-0080` calcule a 352 px** : a 320 px de fenetre l'image fait 296 px (1.2) ; un
   cadre de 14 % passe le garde-fou et rend 41,4 px. Mon plancher de 16 unites est sur. La
   derivation de la taille rendue a partir de la largeur servie est la meme question que A3.
2. **Variante `<path>` en disques a bout rond** (3.4) : que dit le calcul de population de
   `FIG-4` d'un fichier a une seule forme sans remplissage ?
3. **10 x 10 et poids** (3.4) : `<circle>` serre (1 238 octets de commentaire), `<path>` large.
   Le choix entre les deux, ou un 10 x 10 a ecarter, est a l'architecture et a `nadia-prof` ;
   le contenu n'a pas besoin de 100 points pour fermer `MAT-CE1-06`.
4. **Controles geometriques 2.5** : seul le point 1 (non-chevauchement des cadres) est dans A2.
   Les points 2 et 3 (ligne etrangere dans une zone, croisement) sont a `nadia-prof` en jouant
   (`ZON-9`) ; si `theo-archi` veut en encoder un, le script de poste existe, a lui de decider.

### Pour `sofia-sm`

1. Q5 : le dessinateur de ces dessins reste a affecter ; ce document est ce qu'il doit lire.
2. Aucun fichier de production, aucun test n'est modifie par ce commit.

---

## 6. Ce que ce document ne fait pas

- Il ne dessine aucun SVG livre ; les trois modeles sont jetables.
- Il ne prononce aucun Done, pedagogique ou technique, et ne ferme aucune ligne de la matrice.
- Il ne cree aucun identifiant de condition : les mentions `DES-*`, `ZON-*`, `SYM-CE1-*`,
  `MUL-CE1-*`, `FIG-4`, `COU-2` sont des identifiants existants, cites.
- Il ne modifie ni CSS, ni gabarit, ni controleur, ni test.
- Il ne mesure pas les ecrans de manche, de reponse, de fin de partie ni d'erreur : aucun paquet
  de C n'existe, il n'y a encore rien a mesurer. A la livraison des dessins, ces ecrans
  se mesurent a quatre largeurs au moins (320, 360, 744, 992), comme en 1.2.
