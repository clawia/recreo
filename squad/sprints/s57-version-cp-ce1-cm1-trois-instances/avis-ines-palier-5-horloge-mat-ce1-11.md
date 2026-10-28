# s57, palier 5 : avis d'`ines-design` sur l'horloge `MAT-CE1-11` (`ce1-lire-l-heure`), exigence 2 de `nadia-prof`

- **Auteure** : `ines-design`. Je propose, je ne tranche pas : aucun Done, aucune réserve levée ou maintenue. Le veto pédagogique est à `nadia-prof`.
- **Date** : 2026-10-28, 13:29 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-ines-horloge`, à `b37d476a`. Ce fichier est le seul que j'écris. Ni le paquet, ni les 12 dessins, ni un test, ni une feuille de style ne sont touchés. Le jar a été construit avec une entrée de catalogue locale (`bandit-manchot-lire-l-heure-ce1`, titre et consigne de ma main), non commitée et restituée par `git restore` avant ce commit : `git status -s` est vide hors de ce fichier.
- **Source** : `lecture-nadia-premier-jet-horloge-mat-ce1-11.md` (exigence 2, réserve b), `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` (Q4, Q5, Q12).

## Résumé

| Code | Constat | Section |
|---|---|---|
| F1 | Glyphes 3, 4, 6, 7, 8, 9 : **aucun remplacement proposé**. Ils se lisent à 176 px dans mon rendu, ink de 10,8 à 12,6 px de large et de 17,9 à 19,8 px de haut, plancher de 14 px tenu | 1 |
| F2 | Petite aiguille à 7,5 degrés : le déport du bout est de **3,65 px** pour une épaisseur de 7. Le calcul de `nadia-prof` est exact. Il vaut pour 6 dessins sur 12 (`horloge-g` à `-l`) | 2.2 |
| F3 | Deux dessins où les aiguilles se touchent près du moyeu : `horloge-h` (jusqu'à 9 unités du centre) et `horloge-k` (jusqu'à 14,4). Elles se séparent avant le bout de la petite aiguille | 2.3 |
| F4 | Pli à 744 x 551, après une erreur : bas de `.indice` **530** (21 px de marge), 0 item sur 12 sous 551. Bas du signal (verdict et indice) **556**, soit 5 px sous 551 pour les 70 relevés | 3.2 |
| F5 | Pli à 360 de large (hauteur 551) : relevé, par la méthode du dépôt. Bas de `.indice` **872 à 902**, 70 relevés sur 70 sous 551. Avant toute erreur, les propositions finissent à **702** | 3.3 |
| F6 | Non mesuré : écran d'erreur de l'application, autres largeurs, six ordres permutés, appareil réel | 4 |

## 1. Relecture des glyphes 3, 4, 6, 7, 8 et 9

**Méthode.** Les dix `<path id="g0">` à `g9` lus dans `horloge-d.svg` (identiques dans les douze fichiers, d'après `nadia-prof` 1.1). Rendu Chrome 156 sans fenêtre : les douze cadrans à 176 x 176 (facteur 1), quatre cadrans à facteur 2, et les dix glyphes seuls, agrandis. Boîtes d'encre par `getBBox()` (géométrie du tracé) plus le trait de 8, mises à l'échelle de 0,3 du `<g transform>`. Une police, un navigateur, un poste. **C'est ma lecture, pas celle d'un enfant de CE1.**

| Glyphe | Ink large x haut (px, à 176) | Lecture |
|---|---|---|
| 1 | 6,0 x 19,2 | reste, déjà relu par `nadia-prof` |
| 2 | 12,0 x 19,2 | reste |
| 3 | 12,5 x 19,8 | se lit comme un 3 : deux arcs, le bas ouvert à gauche, comme à l'écriture |
| 4 | 12,6 x 19,2 | se lit comme un 4 fermé : diagonale, barre, hampe |
| 5 | 11,1 x 17,9 | reste |
| 6 | 10,8 x 18,3 | se lit comme un 6 : crochet haut, boucle basse fermée |
| 7 | 11,4 x 18,6 | se lit comme un 7 : barre haute, oblique, sans barre médiane |
| 8 | 11,4 x 18,6 | se lit comme un 8 : deux boucles fermées, la haute plus étroite |
| 9 | 10,8 x 18,3 | se lit comme un 9 : boucle haute, queue ouverte en bas à gauche |

- **Ressemblance** : à 1x, 6, 8, 9 et 0 se distinguent par leurs boucles (une, deux, une en haut, une pleine). 3 et 5 se distinguent par le côté de l'ouverture. Je n'ai relevé aucune paire confondable.
- **Graisse** : trait de 8, rendu 2,4 px, le même que `g0`, `g1`, `g2`, `g5` ; les chiffres ne paraissent ni plus gras ni plus maigres que 10, 11, 12.
- **Corps** : hauteur d'encre de 17,9 à 19,8 px. Le plus petit écart est entre `g5` (17,9) et `g3` (19,8), soit 1,9 px, d'après les boîtes. Plancher d'encre de 14 px (`glyphes-7-8-9-ines.md`) : tenu par les neuf. `g0` n'a pas renvoyé de boîte dans mon script et n'est pas relu ici.
- **Écart de ligne de base** : `g3` va de y 4 à y 62, les autres s'arrêtent à 58. Le 3 descend d'environ 1,2 px sous la ligne des voisins à la même ordonnée. Je ne le vois pas sur les cadrans rendus à 1x. Je le note, je ne le propose pas en correction.

**Conclusion F1 : aucun glyphe ne tombe, donc aucun SVG de remplacement dans ce fichier.** Ce que je n'ai pas fait : une lecture à l'écran par un enfant, et un rendu sur une autre police de rendu SVG que celle de Chrome.

## 2. Contraste et lisibilité des aiguilles

### 2.1 Heures contre minutes

| | Petite aiguille (heures) | Grande aiguille (minutes) |
|---|---|---|
| Longueur | 28 unités | 42 unités, rapport 0,67 |
| Épaisseur | 7 | 4 |
| Couleur | `#1c2733` | `#1c2733` |

- **Couleur** : les deux aiguilles ont la même, sur le fond de page `#f4f7fb` : contraste **14,09** calculé (`[calcul]`, WCAG), le même que l'énoncé. La différence est portée par la longueur et par l'épaisseur, jamais par la couleur : le cadran se lit en niveaux de gris (`FIG-3`, `QUA-5`).
- **Longueur** : la grande aiguille s'arrête à 42 du centre, les nombres sont centrés à environ 59 : un espace d'environ 8 unités subsiste entre son bout et l'encre des nombres. Elle montre la direction du nombre sans le toucher.
- **Je ne propose aucune modification de géométrie ni de couleur.** Un changement de longueur ou d'épaisseur déplace la hiérarchie entre les deux aiguilles, et la garde M lit des `<line>` à coordonnées entières.

### 2.2 La petite aiguille à 7,5 degrés

Calcul sur les 12 fichiers, à partir des `rotate` des `<line>` (longueur 28, épaisseur 7) :

| Écart de la petite aiguille au nombre le plus proche | Dessins | Déport latéral du bout (28 x sin) | Déport de son prolongement à r = 59 |
|---|---|---|---|
| 0 degré | `a`, `b`, `c` | 0 | 0 |
| 15 degrés | `d`, `e`, `f` | 7,25 | 15,3 |
| **7,5 degrés** | `g`, `h`, `i`, `j`, `k`, `l` | **3,65** | **7,7** |

- **Le calcul de `nadia-prof` est confirmé** : 3,65 px pour un bout, soit à peu près la moitié de l'épaisseur (7) ; le chiffre 4 qu'elle donne est l'arrondi.
- **Le prolongement de l'axe** passe à 7,7 px du centre du nombre, soit environ 1,5 px en dehors de l'encre du 3 (demi-largeur de 6,25 px). Lu comme une ligne, l'aiguille longe le nombre sans le croiser.
- **À l'écran** (cadrans à 1x et à 2x) : sur `horloge-g` et `horloge-j`, l'inclinaison de la petite aiguille se voit. Elle se voit moins que sur `horloge-d` (15 degrés, déport double). **C'est une lecture de designer sur un rendu, pas la lecture d'un enfant : la garde de cette propriété est à `nadia-prof`** (`lecture-nadia-premier-jet-horloge-mat-ce1-11.md`, section 6).
- **Options, sans en recommander une contre l'avis de `nadia-prof`** :
  - **O1** : garder la géométrie. Le plafond est géométrique : 7,5 degrés valent 3,65 px à 28 unités, quelle que soit la couleur.
  - **O2** : allonger la petite aiguille de 28 à 34. Déport 4,4 px (+21 %), mais le rapport des longueurs passe de 0,67 à 0,81 : `HOR-3` (aiguilles distinctes par la longueur) en est affaiblie.
  - **O3** : amincir la petite aiguille de 7 à 6. Déport inchangé, rapport déport sur épaisseur de 0,52 à 0,61 ; l'écart d'épaisseur avec la grande aiguille (4) tombe de 3 à 2.
  - O2 et O3 se résolvent en changeant un entier dans une `<line>` : la garde M reste applicable. Je penche pour O1 tant qu'un enfant n'a pas échoué sur un quart : O2 et O3 achètent moins de lisibilité qu'ils n'en retirent à la distinction heure et minute.

### 2.3 Aiguilles qui se touchent

Écart angulaire entre les deux aiguilles, et rayon jusqu'où leurs traits se recouvrent (demi-épaisseurs 3,5 et 2) :

| Dessin | Écart | Recouvrement jusqu'à |
|---|---|---|
| `horloge-k` (9 h 45) | 22,5 degrés | 14,4 unités du centre |
| `horloge-h` (4 h 15) | 37,5 degrés | 9,0 unités du centre |
| les 10 autres | 75 degrés ou plus | aucun |

Dans les deux cas, les aiguilles se séparent avant la moitié de la petite. Sur `horloge-k`, le bout de la petite aiguille est à 10,7 px de l'axe de la grande, soit 5,2 px de vide entre les deux traits. Rien à corriger de mon côté.

## 3. Relevé du pli

### 3.1 Méthode

Celle de `mesure-ines-palier-4-lp6-pli-744x551.md` et de `mesure-ines-palier-4-pli-744x551.md` (story `S57-PLI-LP3-LP5-MESURE`, critères 1 et 2) :

- Un jar construit par `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` dans ce worktree (JDK 21, `-am` porté, mesure partielle : pas de `verify`, aucune porte technique annoncée). SHA-256 du jar : `e65738a7bf01847f483472ba818f91ddc083512db430df4a694c4024ebf67052`.
- Le jar sur le port 18101, arrêté par son PID (`taskkill /PID`). Aucun arrêt par nom d'image. Aucun écouteur ne reste sur 18101.
- Un Chrome sans fenêtre piloté par le protocole devtools (Node), profil jetable, arrêté par le PID de son processus. Largeur forcée par `Emulation.setDeviceMetricsOverride` (`mobile: false`, facteur 1, hauteur 551). C'est la méthode que le rapport de `LP6` emploie à 360, et elle contourne le plancher de `--window-size` (`outils/mesurer-rendu.sh`, `ADR-0025`) : **`lea-dev` avait raison que la fenêtre sans interface refuse moins de 500 px, mais le dépôt a une méthode qui n'a pas cette limite**. Contrôle : `document.documentElement.clientWidth` relevé à 744 et à 360 sur les écrans mesurés, et `scrollWidth` égal à `clientWidth` à chaque relevé.
- Cote = `getBoundingClientRect().bottom + scrollY`. Critère : bas de `.indice` au plus à 551.
- Parcours : 10 parties de 10 manches, deux sur trois par une mauvaise réponse (dont une sur trois avec une seconde erreur), une sur trois par la bonne. À chaque état, les deux largeurs sont relevées **sur le même DOM**. 100 arrivées de manche, 70 premières erreurs, 30 secondes erreurs, 100 retours après une bonne réponse, 10 fins de partie. **12 dessins sur 12** couverts à la première erreur (de 3 à 9 relevés par dessin). 44 ordres de propositions distincts vus à la première erreur.
- Le pilote est un script hors dépôt, dérivé de `squad/conformite/releves-s57/mesurer-additions-cp.mjs` pour le protocole et le parcours. Il n'est pas commité ; la méthode ci-dessus suffit à le refaire.

### 3.2 À 744 x 551

| État | Relevés | Élément | Bas (px) | Sous 551 |
|---|---|---|---|---|
| Arrivée de manche | 100 | image (176 x 176) | 306 | 0 |
| | | énoncé | 358 | 0 |
| | | propositions (une rangée, 58 px de haut) | 456 | 0 |
| Première erreur | 70 (12 dessins) | `.indice` | **530** | **0** |
| | | signal (verdict et indice) | **556** | **70 sur 70, de 5 px** |
| Seconde erreur | 30 | `.indice` / signal | 530 / 556 | 0 / 30 sur 30 |
| Manche après une bonne réponse | 90 | signal | 544 | 0 |
| Fin de partie | 10 | bouton unique (`Les jeux`) | 154 | 0 |

- Les cotes sont identiques d'un dessin à l'autre : le dessin ne change aucune boîte, seul l'ordre des propositions change et la rangée reste unique (456 partout).
- **Le critère des fiches (bas de `.indice` au plus à 551) est tenu : 530, marge de 21 px.** Le signal de la première erreur finit à 556, soit 5 px sous le pli ; le critère ne le couvre pas, et je ne dis pas si c'est recevable. Je n'ai pas relevé ce que contiennent les 5 derniers px.
- Chemin de sortie : bas à 58. Aucun débordement horizontal.
- Référence, `LP6` à 744 x 551 : `.indice` 510 au plus, signal 536 au plus. L'horloge finit environ 20 px plus bas, ce qui correspond à l'image de 176 px ajoutée au-dessus de l'énoncé. Pour la forme seulement : je ne rapproche pas les deux contenus.

### 3.3 À 360 de large (hauteur 551, même méthode)

| État | Élément | Bas (px) | Sous 551 |
|---|---|---|---|
| Arrivée de manche | image (176 x 176, bas) | 450 | 0 |
| | énoncé | 510 | 0 |
| | propositions (3 boutons de 66 px de haut) | 702 | **100 sur 100** |
| Première erreur | `.indice` | **872** (`horloge-a`, `-b`, `-c`) ou **902** (les 9 autres) | **70 sur 70** |
| | signal | 818 | 70 sur 70 |
| Manche après une bonne réponse | signal | 790 | 90 sur 90 |
| Fin de partie | bouton unique | 228 | 0 |

- **À 360 de large, avec une hauteur de 551, l'enfant ne voit pas les propositions sans faire défiler**, avant toute erreur. C'est le cas de l'horloge parce que l'image de 176 px s'ajoute. Pour mémoire, le rapport de `LP6` a relevé les gabarits de référence eux-mêmes sous le pli à 360 (958 à 1102 pour `.indice`) : ce n'est pas une largeur du périmètre de mesure de ces fiches, donnée pour la limite basse.
- Hauteur 551 est la convention du dépôt, pas la hauteur d'un téléphone : un appareil de 640 px ou plus en voit davantage. Je n'ai relevé aucune autre hauteur.
- Aucun débordement horizontal : `scrollWidth` égal à `clientWidth` (360) à chaque relevé. L'image garde 176 x 176 et reste centrée (gauche 84,5).
- **Réserve b de `nadia-prof` (le pli à 360 n'est relevé par personne sur ce paquet)** : relevé, chiffres ci-dessus. Le critère 1 de `S57-PLI-LP3-LP5-MESURE` vise `ce1-calendrier-dates` et `ce1-emc-droit-devoir` : ce paquet n'y est pas couvert, et je ne modifie pas la story.

## 4. Ce que je n'ai pas pu mesurer ou n'ai pas fait

- **Écran d'erreur de l'application** : une requête sur un chemin inexistant renvoie la page d'erreur par défaut du serveur (`Whitelabel Error Page`), pas l'écran du jeu. Je n'ai pas de moyen, sans modifier un contrôleur ou un contenu, de provoquer l'écran d'erreur de l'application. Non relevé.
- **Les six ordres permutés sur un même DOM** : non faits. L'ordre est tiré par le serveur (44 ordres distincts vus). La rangée unique et la cote 456 constantes sur les 100 arrivées rendent l'écart peu probable, sans le prouver.
- **Autres largeurs** (600, 720, 768, 992, 1200), mode sans barre de défilement, polices imposées, zoom, toucher, appareil réel : non relevés. Seuls 744 et 360 l'ont été, comme demandé.
- **Contenu de la zone sous le bas de `.indice`** à 744 (les 5 px du signal).
- **Lecture par un enfant** : aucune, ni des glyphes ni des aiguilles.
- **`g0`** : pas de boîte d'encre dans mon script.

## 5. Routage

| À qui | Quoi |
|---|---|
| `nadia-prof` | F1 : lire les glyphes à l'écran et dire s'ils tiennent ; F2 et O1 à O3 : juger la petite aiguille à 7,5 degrés à l'écran ; F4 et F5 : dire si le signal à 556 (744) et le pli à 360 sont recevables, et si la réserve b se lève ou se maintient |
| `sofia-sm` | rien d'une autre nature : aucun rendu exige une donnée que le contrôleur ne fournit pas ; aucun désaccord à remonter |
| `lea-dev` | seulement si `nadia-prof` retient O2 ou O3 : un entier à changer dans les `<line>`, garde M à relancer sur les douze dessins |
| `camille-po` | la mention de la réserve b : ce paquet n'est dans aucune story de pli aujourd'hui |

## 6. Limites

Un navigateur (Chrome 156 sans fenêtre), une police (celle du poste), pas d'appareil réel, pas de clavier virtuel. Pas de mesure d'un autre jar que celui nommé ci-dessus. Le calcul de la petite aiguille est un calcul ; l'écran n'en est pas une preuve. Aucune porte technique annoncée : `mvn -B verify` et les jobs de la CI ne sont pas lancés.
