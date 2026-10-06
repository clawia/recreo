# S52 - contraintes de dessin de l'instance C (mathematiques CE1) - proposition, pas un Done

- **Auteure** : `ines-design`. **Date** : 2026-10-06. **Sprint** : s52.
- **Arbre** : `round-two`, HEAD `4aad468b`, arbre de travail propre a l'ouverture.
- **Amendement du 2026-10-06 (s52, U2)** : la section 4 est reecrite en entier (elle n'etait qu'une
  esquisse) et deux erreurs de ce document sont corrigees plus bas, dites la ou elles etaient :
  la table de la section 1.2 (largeur servie, deux conditions de defilement melangees) et les
  cadres a decimale des sections 2.4 a 2.6 (`CadreDeZone` est un record d'entiers).
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

| Largeur de fenetre | Image servie, defilement superposable (telephone, tablette) | Image servie, barre de defilement classique de 15 px |
|---|---|---|
| 320 px | **296 x 296** | 281 x 281 |
| 360 px | 336 x 336 | 321 x 321 |
| 390 px | 352 x 352 | 351 x 351 |
| 412 px et plus | 352 x 352 | 352 x 352 |

Commande de la mesure (s52, U2) : jar de `bootstrap/target` construit depuis ce worktree, fragment
reel de `designation` (`POST /parties/designation-plan-de-la-classe`) rendu dans l'accueil reel,
puis iframes `srcdoc` de 320 a 992 px, `getBoundingClientRect()` ; la colonne de gauche est une
iframe plus haute que la page, celle de droite une iframe plus courte, qui fait apparaitre la barre.

**Correction.** La table de la premiere version melangeait les deux colonnes : la ligne 320 etait
lue sans barre de defilement, les lignes 360 et 390 avec (321 et 351). Elle ne changeait pas le
resultat qui compte, mais elle n'etait pas une mesure unique.

**L, la largeur servie la plus etroite, vaut 296 px** sur un appareil a defilement superposable, et
**281 px** si une barre classique est affichee a 320 px (cas que seule une fenetre de bureau etroite
produirait). Le garde-fou 3 d'`ADR-0080` calcule a 352 (`avis de faisabilite s51`, section 5.4) :
un cadre de 14 % y passe (49,3 px) et tombe a 41,4 px a 296. Tout ce qui suit est derive de 296 et
reverifie a 281.

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
> unite du dessin, convention de l'avis s51 section 5.2). 16 unites rendent 56,3 px a 352 px,
> **47,4 px a 296 px** et 45,0 px a 281 px (mesure 1.2, colonne de droite). 15 unites rendraient
> 44,4 px a 296 et 42,2 px a 281 : sous le plancher a 281, au ras a 296 ; j'ecarte 15.
> **Un cadre est un entier** : `CadreDeZone` est un record de quatre `int`
> (`domain/content/CadreDeZone.java`, ADR-0080 decision 2). Toute valeur a decimale de ce
> document, y compris dans sa premiere version, se lit comme un entier.

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
   moins **5 unites** (17,6 px a 352, 14,8 px a 296). Mesure sur le modele : 8,0 unites (recalculee avec les cadres entiers de 2.6 ; 7,8 avec le cadre
   a decimale de la premiere version). Seuil
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
5. **Cadre en pourcentage = unites, en entiers** (`x`, `y`, `largeur`, `hauteur` du `CadreDeZone`).
   La premiere version disait "a la decimale pres" : c'etait faux, le record ne porte que des
   `int`. Un cadre a decimale ne se construit pas.

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
  <!-- ligne 2 (la diagonale, prolongee) : s'arrete au bord du cadre 84,66,16,16 -->
  <line x1="8" y1="26.2" x2="84" y2="69.27" stroke-width="1.4" stroke-dasharray="5 3.5"/>
  <!-- ligne 3 (decalee de 8 unites de la mediane horizontale) : cadre 0,50,16,16 -->
  <line x1="93" y1="58" x2="16" y2="58" stroke-width="1.4" stroke-dasharray="5 3.5"/>
</svg>
```

Cadres de zone correspondants (`x`, `y`, `largeur`, `hauteur`, en %, entiers) : `42, 0, 16, 16` ;
`84, 66, 16, 16` ; `0, 50, 16, 16`. La diagonale entre dans le cadre 2 a y = 69,27, dans l'intervalle
66 a 82 du cadre. Numeros 1, 2, 3 dans l'onglet, libelle complet dans la legende
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

## 4. Quadrillage - `SYM-CE1-3` (completer par symetrie) et `DEP-CE1-2` (se reperer, se deplacer)

Cette section remplace l'esquisse de la premiere version. Elle couvre les deux usages d'une grille
dessinee sous une designation : `SYM-CE1-3` (figure a completer, une case manque) et `DEP-CE1-2`
(grille de 4 x 4 a 6 x 6, "toutes de meme taille et toutes designables"). Les bornes de grille de
`SYM-CE1-3` sont celles de `DEP-CE1-2`, que `nadia-prof` ne recopie pas.

**Les reponses de `nadia-prof` (U3) n'existaient pas a la date de ce commit** (aucune branche
`s52-nadia-instance-c` dans `git branch -a`). Tout ce qui touche a ce qu'un dessin *montre* est donc
range en section 5, "Pour `nadia-prof`" : ici, seule la forme est proposee.

### 4.1 Mesures faites, commandes

Toutes depuis le worktree `s52-ines-contraintes`, jar `bootstrap/target/recreo.jar` construit par
`bash outils/mvn-jdk21.sh -B clean verify` (build vert, aucun fichier de production touche), lance par
`java -jar` sur un port libre. Les modeles de grille sont des SVG jetables ecrits hors du depot ; ils
ne sont jamais sous `adapters/`.

| # | Mesure | Commande et perimetre |
|---|---|---|
| M1 | Taille rendue de chaque zone, chevauchement, debordement, texte hors de la boite | fragment reel de `designation` (`POST /parties/designation-plan-de-la-classe`) pris comme gabarit, dessin et zones remplaces par ceux du modele, rendu dans l'accueil reel avec `recreo.css` et la feuille tierce servies par le jar ; iframes `srcdoc` de 320, 360, 390, 412, 744 et 992 px (la fenetre headless ne descend pas sous 516 px, `outils/mesurer-rendu.sh` ; l'iframe contourne le plancher) ; `getBoundingClientRect()` sur l'image, chaque zone et le texte de chaque zone ; Chrome `--headless=new --dump-dom`. Six pages : 3 zones, 36 zones hors de la boite, 36 zones a libelle en boite, 36 zones de deplacement hors de la boite, 4 x 4 et 5 x 5 en boite |
| M2 | Axe, grille et cases pleines en pixels | quatre variantes du dessin rendues a 296 px de large, `--screenshot`, lecture des pixels d'une ligne a travers l'axe (seuil sombre : moyenne des canaux sous 128) |
| M3 | Axe sous une zone, et sous 36 zones | page a un seul bloc (`.illustration-designation` de la feuille reelle, image de 296 puis 352 px de large), capture, lecture de pixels sur la ligne de la zone |
| M4 | Contraste d'une encre sous une zone | pixel lu dans la capture (`#989da3` sous le voile, `#fafbfd` pour une case vide), puis calcul WCAG 2.x comme en 1.1 |
| M5 | Poids | taille des fichiers generes, sans en-tete |
| M6 | Garde-fou de poids et de geometrie | deux dessins du modele copies sous `static/illustrations/`, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest -Dsurefire.failIfNoSpecifiedTests=false`, puis fichiers retires (`git status` propre) |

### 4.2 La grille, commune aux deux usages

- `viewBox="0 0 100 100"`, `width="352" height="352"`. Une grille de `n x n` cases, **cote de case et
  marge entiers** (un `CadreDeZone` est un record d'entiers, 1.3) :

| n | cote de case (unites) | marge (unites) | case rendue, image de 296 px | de 336 px | de 352 px |
|---|---|---|---|---|---|
| 4 | 24 | 2 | 71,0 px | 80,6 px | 84,5 px |
| 5 | 18 | 5 | 53,3 px | 60,5 px | 63,4 px |
| 6 | 16 | 2 | **47,4 px** | 53,8 px | 56,3 px |

  Mesure M1 sur les six largeurs (320 a 992 px), grilles completes a zones de la taille d'une case :
  les valeurs du tableau sont les minimales relevees, **aucune zone ne sort de l'image, aucune paire
  de zones ne se recouvre sur plus de 0,5 px dans les deux sens**, aucun debordement horizontal de la
  page. A 281 px d'image (barre classique, 1.2) la case de 16 unites rend 45,0 px (page a 3 zones,
  iframe courte).
- **Le 5 x 5 est ecrit a 18 unites et non a 19,2** : un cadre decimal ne se construit pas ; 18 garde
  des marges egales (5 de chaque cote) et une case de 53,3 px.
- Une case = une zone du meme cote, **cadre egal a la case**, deux cases voisines bord a bord. Le
  critere de `DeuxZonesDUnItemNeSeChevauchentJamaisTest` est strict (les interieurs se coupent, cases
  bord a bord admises) : ce rangement passe.
- **Trait de grille : 0,7 unite**, plein, `stroke="#1c2733"`. 2,07 px a 296, 2,46 px a 352 (le
  garde-fou lit la hauteur declaree) : au-dessus du plancher de 2 px. 0,6 donnerait 1,8 px a 296.
- Rien d'ecrit dans le dessin : ni lettre, ni chiffre (`DES-7`). Un repere de case est un libelle de
  zone, servi par le gabarit.

### 4.3 `SYM-CE1-3` - figure a completer

**Cases pleines** : carres de `#1c2733`, `stroke="none"`, **retrecis de 2 unites de chaque cote**
(12 unites sur une case de 16, 20 sur une case de 24), centres dans la case.

**Axe** : un trait plein de **2,4 unites** sur la ligne de grille centrale, un seul trait d'un bord a
l'autre de la grille. Il suppose un `n` pair : un axe sur une grille de 5 coupe des cases par le
milieu, **le 5 x 5 est donc exclu pour `SYM-CE1-3`** (il reste permis pour `DEP-CE1-2`). Un axe
horizontal est la meme regle par transposition ; lequel est utilise est un choix de contenu.

Mesure M2, ligne a travers la rangee 2 (cases pleines de part et d'autre de l'axe), image de 296 px :

| Variante | Pixels, de gauche a droite, entre les deux colonnes centrales (case / lacune / axe / lacune / case) | Verdict |
|---|---|---|
| a. cases de 12, axe plein de 2,4 (retenue) | 35 / 3 / **6** / 3 / 35 | l'axe se voit, separe des cases |
| b. cases de 16 jointives | **94 px sombres d'un seul tenant** | l'axe n'existe plus (le defaut que la premiere version avait trouve) |
| c. axe de 0,7 unite | 35 / 5 / 2 / 5 / 35 | l'axe a l'epaisseur d'un trait de grille (2 px) : rien ne le designe |
| d. axe de 2,4 en tirete `5 3.5` | meme rangee : axe 2 px (la lacune du tirete y tombe) ; rangee vide : 6 px | l'axe change d'epaisseur le long de son trace, se lit comme un trait irregulier |

Dans la variante retenue, l'axe rend 6 px au seuil de lecture (7,1 px theoriques) contre 2 px pour un
trait de grille : rapport 3 ou plus. L'ecart entre une case et l'axe est de 0,8 unite (2,4 px, mesure
3 px).

**Zones : les seules cases vides candidates** (`ZON-8` : trois au moins). Une zone est une case,
cadre egal a la case. Une case pleine n'est jamais une zone. Justification mesuree (M3 et M4) :

1. **Sous un voile de zone, une case pleine perd son contraste.** Le fond d'une zone est du blanc a
   55 % (`.zone`, `recreo.css`) : l'encre `#1c2733` y devient `#989da3` (lu dans la capture) contre
   `#fafbfd` pour une case vide, soit **2,62:1**, sous le seuil de 3:1 pris en 1.4. Les cases pleines
   d'une figure a 36 zones se lisent comme des cases grisees.
2. **Sous 36 zones, l'axe disparait.** Sur les deux rangees lues (2 et 6), entre les deux colonnes
   centrales, a 296 px : **0 px d'encre d'axe**, remplace par la jonction de deux bordures de zone
   (4 px bleus).
3. **Une zone contre l'axe en recouvre la moitie.** A 296 px : 3 px d'encre restent a cote de 2 px de
   bordure bleue (4 px a 352 px). C'est lisible, et c'est pourquoi l'axe ne descend pas sous 2,4
   unites : a 0,7 unite il ne resterait qu'un pixel.
4. **36 zones, c'est 36 arrets de tabulation** (M1 : 36 boutons) et, hors de la boite, une legende de
   **1 220 px** de haut (36 libelles, identique aux six largeurs), contre 98 px pour trois libelles.

**Legende** : celle de 36 libelles n'est pas necessaire quand seules les cases candidates sont des
zones. Le gabarit existant suffit : `hors-de-la-boite` (`ADR-0094`), un numero de repere dans la case
(1, 2, 3...) et une legende de trois a cinq lignes sous le dessin. Le texte des libelles est du
contenu (section 5).

**Exemple annote conforme (modele de contrainte, `viewBox` 100, figure de neuf cases, la case
attendue est en colonne 4, rangee 3)** :

```xml
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100" width="352" height="352" role="img"
     fill="none" stroke="#1c2733">
  <!-- grille : 7 horizontales et 7 verticales de 0,7, de 2 a 98, pas de 16 -->
  <g stroke-width="0.7">
    <line x1="2" y1="2" x2="98" y2="2"/> <!-- ... y = 18, 34, 50, 66, 82, 98 -->
    <line x1="2" y1="2" x2="2" y2="98"/> <!-- ... x = 18, 34, 50, 66, 82, 98 -->
  </g>
  <!-- cases pleines : 12 x 12, retrecies de 2 dans leur case -->
  <rect x="20" y="20" width="12" height="12" fill="#1c2733" stroke="none"/> <!-- colonne 2, rangee 2 -->
  <!-- ... huit autres cases -->
  <!-- axe : plein, 2,4, sur la ligne x = 50 -->
  <line x1="50" y1="2" x2="50" y2="98" stroke-width="2.4"/>
</svg>
```

Fichier complet mesure : **1 401 octets**, sans en-tete. Cadres de zone correspondants (`x`, `y`,
`largeur`, `hauteur`, en %) : `50, 34, 16, 16` (la case attendue, contre l'axe) ; `66, 34, 16, 16` ;
`82, 18, 16, 16`. Le choix des cases candidates est pedagogique, pas le mien. Ce modele repond a un
point de `SYM-CE1-3` que je lis en geometrie : la case attendue n'est pas la seule case vide qui
touche la figure, la case (colonne 5, rangee 3) la touche aussi.

**Refuse, `SYM-CE1-3`** :

1. Des zones sur les 36 cases, ou sur une case pleine (points 1 et 2 ci-dessus).
2. Des cases pleines de la taille de la case, jointives (variante b).
3. Un axe tirete, ou de moins de 2,4 unites, ou qui ne suit pas une ligne de grille (variantes c, d).
4. Un `n` impair, ou un axe qui coupe une case.
5. Une couleur ou un niveau de gris different pour les cases pleines, l'axe ou la grille : un seul
   niveau, `#1c2733`.
6. Une legende de plus de cinq libelles : la legende repond a des zones, pas a des cases. Seuil
   propose, non mesure comme limite (98 px pour trois, 1 220 px pour 36).
7. Un trait de grille de moins de 0,7 unite.

### 4.4 `DEP-CE1-2` - se reperer et se deplacer

La clause demande des cases "toutes designables" : **la grille porte donc `n x n` zones**, ce que la
mesure ci-dessus rend ici admissible (le dessin n'a pas de cases pleines a proteger). Ce que la mesure
a trouve de specifique :

1. **Le dessin de la grille est presque entierement couvert.** Avec 36 zones, chaque case est une
   boite blanche a 55 % bordee de bleu ; du trait de grille il ne reste qu'une jonction bleue de 4 px.
   Le quadrillage "montre" (`DEP-CE1-2`) est alors porte par les zones, et le dessin porte **la case
   de depart seule**. La grille reste dessinee dans le fichier (0,7 unite, 4.2) : elle est ce que le
   fichier montre si une case n'est pas une zone.
2. **Marque de depart : un disque plein de 10 unites de diametre** (rayon 5), `#1c2733`,
   `stroke="none"`, centre dans sa case, soit 29,6 px a 296. Sous le voile d'une zone il devient
   `#999ea3`, **2,61:1 contre le fond de zone** (calcul ; 2,62:1 au pixel) : sous 3:1, et aucune encre
   `#1c2733` voilee n'atteint 3:1 (le noir pur y fait 3,25:1, hors palette). Il se reconnait donc par
   **sa forme et sa taille**, pas par son contraste. Regle : pas de marque sous 10 unites de diametre,
   une seule marque par item, une marque qui est un disque plein.
3. **Libelle de case : dans la boite, deux caracteres au plus.** Mesure M1 : le plus large des
   libelles essayes (A1 a F6) fait **26,3 px** de large et 27 px de haut dans une case de 47,4 px, a
   20 px de police (le plancher de `.zone`) ; aucun libelle ne depasse sa boite, aux six largeurs, pour
   les grilles de 4, 5 et 6. Trois caracteres ne sont pas mesures. Le mode `hors-de-la-boite` a 36
   cases est **refuse** : numeros de 1 a 36 dans les cases et legende de 1 220 px a faire lire a un
   lecteur debutant. Que le libelle soit une coordonnee, un numero ou autre chose est a `nadia-prof`
   (`DEP-CE1-3`, section 5).
4. **Libelle sur la case de depart** : le libelle (`#1c2733`) sur le disque voile (`#999ea3`) fait
   **5,60:1** (calcul 1.4) ; a la capture, le libelle se lit sur le disque.
5. Poids du modele de 6 x 6 avec sa marque : **747 octets**, sans en-tete.

**Refuse, `DEP-CE1-2`** :

1. Un `n` hors de 4 a 6, des cases de tailles differentes, une case du quadrillage qui n'est pas une
   zone (sans que `nadia-prof` l'ait dit, section 5).
2. Un libelle de plus de deux caracteres dans la boite ; le mode `hors-de-la-boite` au-dela de cinq
   zones (seuil propose, 4.3).
3. Une marque qui n'est pas un disque plein de 10 unites au moins ; deux marques ; une marque
   distinguee par sa teinte.
4. Un chemin, une fleche, un trait de deplacement dans le dessin (`DEP-CE1-3` : l'enonce ne contient
   pas sa reponse, le dessin non plus).
5. Un chiffre ou une lettre dans le dessin (`DES-7`).

### 4.5 Poids et garde-fou (mesures M5 et M6)

| Dessin | Octets, sans en-tete |
|---|---|
| grille 6 x 6 seule (enveloppe comprise) | 687 |
| grille 4 x 4 seule (enveloppe comprise) | 535 |
| modele `SYM-CE1-3` de 6 x 6, neuf cases pleines | **1 401** |
| 6 x 6, dix-huit cases pleines (une moitie entiere) et axe, cas extreme | 2 049 |
| modele `DEP-CE1-2` de 6 x 6 | **747** |

Tous sous le cliquet de 4 633 octets par dessin, avec plus de 2 500 octets pour l'en-tete le plus
long. **Garde-fou, essai M6** : les deux modeles (1 401 + 747 = 2 148 octets) copies sous
`static/illustrations/`, sous des noms qu'aucune liste ne connait, font echouer trois tests pour deux
motifs seulement : la hauteur declaree de 352 px au-dessus du plafond de 176 px (`DES-3` : le nom
n'est pas dans `DESSINS_DE_DESIGNATION`, ce que l'inscription du dessin regle) et le cliquet total de
poids (51 307 attendus, 53 455 mesures : exactement 2 148 de plus). **Aucune autre faute** : ni sur le
trait de grille de 0,7 unite, ni sur les remplissages `#1c2733` a `stroke="none"` (la forme que
`frise-journee-ecole.svg` emploie deja), ni sur une seconde couleur de trait, ni sur l'unicite d'un
dessin (`COU-2`). Les fichiers d'essai sont retires, aucun n'est livre.

## 5. Points ouverts

### Pour `nadia-prof`

1. **Onglet au bout de la ligne ou zone sur la ligne** (2.4). Le premier garde le numero hors du
   trait, mais l'enfant qui touche la ligne elle-meme ne declenche rien : la cible est l'onglet,
   pas le tirete. Elle a ecrit qu'une zone sur la partie exterieure d'une ligne est recevable
   (6.3 point 3) ; je ne la contredis pas, je propose une forme ou le numero ne couvre aucun trait.
2. **Texte alternatif** (`th:alt`) : il ne doit ni nommer l'axe, ni donner le nombre de rangees et
   de colonnes. Contenu, donc a elle.
3. **Leurre loyal** (2.5 point 4) : un ecart de 8 unites a la mediane est une borne de lisibilite,
   pas un jugement sur la difficulte.
4. **Densite du reseau** (3.2) : diametre 10 px, ecart 6 px, rendu 1:1. Qu'un enfant de CE1 compte
   a l'oeil un 10 x 10 a ce pas est un jugement qu'elle seule peut faire en jouant.
5. **Quadrillage de `SYM-CE1-3` : zones sur toutes les cases ou sur les cases candidates seules** (4.3).
   Je propose les candidates seules : sur 36 zones, une case pleine tombe a 2,62:1 sous le voile et
   l'axe disparait. Le nombre de candidates (trois au moins, `ZON-8`) et leur choix sont a elle.
   La meme question se pose pour la lettre de `DEP-CE1-2` ("toutes designables") : je la lis comme
   `n x n` zones sur une grille de deplacement et je ne la generalise pas a la symetrie.
6. **Case de depart d'une grille de deplacement : zone ou non** (4.4). Zone, elle est voilee (2,61:1,
   reconnue par sa forme). Non-zone, elle garde ses 14,09:1 mais la grille compte `n x n - 1` zones.
7. **Libelle visible des cases d'une grille de deplacement** (4.4, `DEP-CE1-3`) : coordonnee ("B3"),
   numero, ou autre. Deux caracteres au plus mesures. C'est elle qui juge si un libelle sur la case
   fait de la tache une lecture de libelle.
8. **Libelles de la legende de `SYM-CE1-3`** (trois a cinq lignes) : leur texte, et s'ils sont
   positionnels ("colonne 4, ligne 3"). Contenu, donc a elle.
9. **Axe vertical, horizontal ou les deux, et `n`** (4.3) : 4 x 4 et 6 x 6 pour la symetrie (un axe
   central suppose `n` pair), 4 a 6 pour le deplacement. Quelle taille sert quel item est a elle.

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

5. **Un voile de zone propre aux grilles** (4.3, 4.4) : toutes les zones recoivent le meme fond
   blanc a 55 % (`.zone`). Si `nadia-prof` veut des cases pleines ou une marque sans voile sur 36
   zones, il faut un indicateur de vue du genre de `libelleDeZoneHorsDeLaBoite` : c'est une donnee que
   le controleur ne fournit pas, je ne la cree pas, et je ne touche pas `.zone` pour tous les paquets
   de designation.
6. **Cadre entier** (1.3, 4.2) : le 5 x 5 s'ecrit a 18 unites (marge 5), pas 19,2.

### Pour `sofia-sm`

1. Q5 : le dessinateur de ces dessins reste a affecter ; ce document est ce qu'il doit lire.
2. Aucun fichier de production, aucun test n'est modifie par ce commit.
3. U2 est ecrit : la section 4 est complete (mesures, cases candidates, legende ou son remplacement).
   Je ne declare pas la condition (2) de `DEC-0111` remplie, c'est a `camille-po` (U4) et a
   `nadia-prof` (U3, section 5, points 5 a 9).

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
- Les mesures de la section 4 sont celles d'un Chrome headless sur des modeles jetables dans le
  gabarit reel : pas d'enfant, pas d'appareil tactile, pas de dessin rendu pour les grilles de 4 x 4
  et de 5 x 5 (leurs zones sont mesurees, leur dessin est calcule). L'axe d'une grille de 4 x 4
  (2,4 unites sur une case de 24) est calcule, non mesure.
