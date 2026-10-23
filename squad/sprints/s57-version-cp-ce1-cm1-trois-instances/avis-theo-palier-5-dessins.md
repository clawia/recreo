# s57 - avis de `theo-archi` sur les dessins du palier 5 : rotation du triangle rectangle (`LP7`) et cinq lignes de mathematiques CE1 (D5)

- **Auteur** : `theo-archi`, 2026-10-23. Arbre : `round-two` a `f3b06989`, branche `s57-theo-p5-dessins`.
- **Demande** : deux reponses de l'utilisatrice du 2026-10-23, relayees par l'agent lanceur. Sur D2 (`LP7`) : *"ok pour faire une rotation"*. Sur D5 : *"option 3 et 5 lignes"*, soit rouvrir les dessins de `DEC-0123` 3 pour `MAT-CE1-01`, `-10`, `-11`, `-02` et `-09` (`proposition-camille-palier-5-ce1.md`, branche `s57-camille-p5-proposition`, `e18b13e8`, sections 6.3 et 9).
- **Convention** : `[mesure]` = lu au disque ou mesure par la commande ecrite a cote, a `f3b06989`. `[suppose]` = estimation ou lecture non verifiee. `[calcul]` = arithmetique sur des valeurs `[mesure]`.
- **Ce que ce fichier n'est pas** : ni un `ADR`, ni du code, ni une decision de produit. Aucune regle `ArchUnit` n'est ecrite, amendee ou retiree. Aucun paquet, aucune constante, aucun dessin n'est touche. Les prix sont ceux du bareme de `nadia-prof` et des registres, pas les miens.

## 0. Etat mesure des dessins

Methode : pour chaque fichier de `adapters/adapter-web/src/main/resources/static/illustrations`, `tr -d '\r' < f` puis `wc -c` (fins de ligne LF, la methode du cliquet), puis somme. Les tailles sur disque different sur ce poste (CRLF) et ne sont pas citees.

- **56 fichiers, 69 203 octets** `[mesure]`. Egal a `POIDS_TOTAL_MAXIMUM` (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java:355`). Plus gros dessin : `frise-journee-ecole.svg`, **4 633 octets**, egal a `POIDS_D_UN_DESSIN_MAXIMUM` (`:279`). Les deux sont en egalite, pas en plafond (`leCliquetVautExactementCeQueCeLotInstalle`, `:826`).
- **Populations en egalite** dans `LIllustrationDeContenuResoutEtAucuneNeDortTest.java` : `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION = 87` (`:193`), `FICHIERS_D_ILLUSTRATION = 56` (`:211`). Alphabet d'un identifiant d'illustration : `[a-z0-9-]+` (`:149`).
- **Poids par famille** `[mesure]`, meme methode : figure plane d'une forme, 321 a 706 octets (`figure-triangle-pose` 1 082 et `figure-carre-droit` 1 430, par leur seul commentaire d'en-tete) ; solides, 363 a 796 ; reseaux de points, 934 a 1 745 ; figures de symetrie, 740 a 751 ; quadrillages de symetrie, 1 442 a 2 215 ; scenes de monnaie (chiffres traces), 1 475 a 2 775 ; frises et plan, 3 098 a 4 633.
- **Composants** `[mesure]` : une definition de glyphe pese 42 a 68 octets (longueur des lignes `<path id=`) ; une instanciation `<use` pese 86 octets en moyenne (51 lignes) ; le commentaire d'en-tete median pese 241 octets (minimum 46, maximum 2 667, sur les 56 fichiers).
- **Glyphes existants** `[mesure]` : `grep -ho '<path id="[^"]*"'` sur le repertoire rend `g0` (4 fichiers), `g1` (10), `g2` (10), `g5` (7). **Aucun autre chiffre, aucune lettre.** Chaque fichier recopie ses glyphes : un dessin charge par `<img>` est un document isole (message du garde, `:613`), il ne peut rien partager avec un autre fichier.
- **Nombre de dessins par paquet illustre** `[mesure]`, `ls` groupe par prefixe : reseaux de points 3, symetrie 11, figures planes 12, monnaie 12, solides 15.
- **Les six paquets concernes** `[mesure]`, `grep -c` sur `adapters/adapter-content/src/main/resources/contenu/` : tous `type: question`, aucun ne declare d'illustration.

| Paquet | Ligne | Items | `illustration:` | En-tete `DEGRADE` |
|---|---|---|---|---|
| `ce1-figures-planes` | `MAT-CE1-15` | 20 | 0 | 1 |
| `ce1-nombres-jusqu-a-1000` | `MAT-CE1-01` | 22 | 0 | 1 |
| `ce1-masses-contenances` | `MAT-CE1-10` | 21 | 0 | 1 |
| `ce1-calendrier-dates` | `MAT-CE1-11` | 25 | 0 | 1 |
| `ce1-comparer-ranger` | `MAT-CE1-02` | 34 | 0 | 0 |
| `ce1-longueurs` | `MAT-CE1-09` | 14 | 0 | 0 |

## 1. Q1 - `LP7` : la rotation du triangle rectangle

### 1.1 Ce que `nadia-prof` exige

`arbitrage-nadia-palier-4.md`, ligne 162, condition datee du 2026-10-21 : *"il n'existe qu'un dessin de triangle rectangle (`figure-triangle-rectangle.svg`) ; un enfant qui revoit toujours la meme image reconnait l'image, pas la propriete. Le triangle rectangle est montre dans au moins deux dessins qui different par l'orientation ou les proportions."* Ligne 221, elle me route *"un second dessin de triangle rectangle, ou la rotation de l'existant"*. Ligne 212, elle reste seule garde : *"aucun test ne compare deux dessins"*.

- **Deux images distinctes** a l'ecran de l'enfant : oui, c'est son motif.
- **Orientation** : suffit a la lettre (*"ou les proportions"*). Elle ne fixe aucun angle.
- **Ce qu'elle n'a pas dit** : si une copie tournee, aux memes proportions, sert son motif. C'est a elle (1.4).

`[mesure]` : `ls static/illustrations | grep -c figure-triangle-rectangle` rend **1**. Le fichier pese **513 octets** LF. Son triangle a pour sommets (16,86), (16,20) et (84,86) : angle droit en bas a gauche, cotes de 66 et 68 unites, presque isocele `[calcul]`.

### 1.2 Verdict : aucune rotation sans octet nouveau ne se fait sans code

Quatre manieres de tourner, une seule sans code :

| Forme | Ce qu'elle exige | Verdict |
|---|---|---|
| (a) `transform` CSS sur le `<img>` | L'item ne declare que `illustration` et `illustrationTexteAlternatif` (`Question.java:69-70`, `QuestionContentSourceFactory.java:113-124`). Il faut une cle d'item neuve, lue par la fabrique (sinon `ChaqueCleFacultativeDItemEstLueParLaFabriqueDeSonTypeTest` la refuse), un champ neuf dans le record `Question` (`domain`), un attribut de modele (`PartieController.java:545-547`), une classe dans `manche.html:120-123` et une regle de `recreo.css`. Cette feuille ecrit : *"La feuille ne touche pas au dessin, elle le dimensionne"* (`recreo.css:446`). Il faut aussi amender `ADR-0073`, et l'identite d'ecran d'`ADR-0032` doit inclure l'orientation. Les gardes de geometrie, de poids et de `COU-2` jugent des fichiers : aucun ne verrait l'image tournee | **Code sous `src/main` dans trois modules, plus un amendement d'`ADR`. Taille M a L.** Zero octet de dessin |
| (b) deuxieme item du YAML qui pointe le meme fichier avec une orientation | La meme cle que (a). Sans elle, deux items qui montrent le meme fichier montrent la meme image, ce que 3.2 refuse | Revient a (a) |
| (c) fragment SVG dans l'identifiant | L'alphabet `[a-z0-9-]+` refuse `#`, `(` et `,` (`LIllustrationDeContenuResoutEtAucuneNeDortTest.java:149`). Le gabarit ajoute `.svg` apres l'identifiant (`manche.html:122`). Support par les navigateurs : non verifie | Ecarte |
| (d) **un second fichier dont la rotation est dans le dessin** | Precedent servi : `figure-carre-incline.svg` (475 octets) porte `transform="rotate(20 50 50)"` sur son `rect`. Un fichier sous `static/illustrations`, aucune classe, aucun gabarit, aucune feuille | **Faisable sans code.** Ajoute environ 500 octets |

**Recommandation : (d).** Elle a le moins de pieces mobiles, et la rotation se trouve dans le fichier, la ou tous les gardes existants la voient. (a) paierait du code et un `ADR` pour economiser 500 octets sur une seule ligne. Ce qui me ferait changer d'avis : plusieurs lignes qui demandent la meme image sous plusieurs orientations, au point que le poids devienne la contrainte.

**Consequence a dire a l'utilisatrice** : sa reponse a choisi l'option (c) de D2, *"rotation sans octet"*. Cette rotation n'existe pas sans code. La rotation faisable, (d), ajoute un fichier et des octets. Or `DEC-0120` A.146 ecrit que le *oui* sur le poids ne couvre pas `MAT-CE1-15`.

### 1.3 Ce que (d) touche, et ce qu'elle ne touche pas

- **Octets** `[suppose]` : la copie du fichier existant, avec un attribut `transform` (environ 28 octets) et son propre commentaire, pese **480 a 620 octets**. `POIDS_TOTAL_MAXIMUM` passerait de 69 203 a environ **69 700 a 69 830**, soit +0,7 a +0,9 %. `POIDS_D_UN_DESSIN_MAXIMUM` ne bouge pas. La valeur commitee est celle que le commit mesure, jamais cette estimation (`ADR-0077`, decision 6).
- **Angle** `[calcul]` : une rotation de 90, 180 ou 270 degres autour de (50,50) garde les sommets dans le cadre. A 90 degres, ils deviennent (14,16), (80,16) et (14,84). **Tout autre angle est a verifier a la main** : a 45 degres, le sommet (16,86) passe a x = 0,5, et le trait de 6 unites sort du cadre de vue. **Aucun garde ne verifie qu'une forme reste dans son cadre** : le garde de geometrie juge le `viewBox`, la taille, le rapport et le trait (`:516-558`), pas le debordement.
- **Proportions au lieu de l'orientation** : un `polygon` aux autres sommets coute le meme ordre d'octets. Une fois le fichier cree, choisir entre rotation et proportions ne coute rien de plus techniquement. Ce choix revient a `nadia-prof`.
- **Nom** : libre dans l'alphabet `[a-z0-9-]+`. Le choix revient a `lea-dev`, sur le patron des noms servis, qui decrivent l'orientation (`pose`, `renverse`, `incline`).
- **Constantes qui bougent dans le commit de `LP7`** : `POIDS_TOTAL_MAXIMUM` ; `FICHIERS_D_ILLUSTRATION` de 56 a 57 ; `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` de 87 a 87 plus le nombre d'items illustres du paquet, au moins 20 d'apres la story. Cette derniere bougerait de toute facon avec `LP7`, sans fichier neuf.
- **Gardes qui jugent le fichier neuf sans rien ecrire** : geometrie et plafond de 176 px (`:541`), plancher de trait (`:554`), `FIG-4` (une seule forme, pas de remplissage), `COU-2`. Le garde de `COU-2` (`:580`) **refuserait une copie qui ne differe que par son commentaire** ; l'attribut `transform` la rend distincte. Il y a aussi l'absence de `<text` (`LIllustrationDeContenuResoutEtAucuneNeDortTest.java:611`) et *"aucune ne dort"* : le fichier doit etre declare par au moins un item d'un paquet de `contenu/`.
- **Texte alternatif** : `CON-5`, le texte alternatif ne porte jamais la reponse. Il ne peut donc pas dire *"triangle rectangle"*. `CON-5` n'est execute que par un test de paquet (precedent `PaquetCpFiguresPlanesTest.java:229-246`) : le test de paquet de `LP7` le porte, a `lea-dev`.
- **Gabarit, feuille de style, `ArchUnit`, `ADR`** : **rien**. `ADR-0036` clause 5 et `ADR-0077` decision 6 gouvernent deja le deplacement du cliquet.
- **Ordre** : `DEC-0120` A.149, regle d'ordre. Si `LP9` installe des dessins dans le meme palier, `LP9` fusionne d'abord. `LP7` fusionne ensuite le sommet de `round-two` et recalcule la constante sur l'arbre fusionne.
- **Test facultatif, que je n'impose pas.** Il comparerait les deux dessins, la ou `nadia-prof` dit qu'aucun test ne le fait. Il serait derive du materiau : les dessins d'une seule forme qui sont un triangle a angle droit, calcule par produit scalaire nul apres application du `transform`. Il exigerait au moins deux tels dessins, non superposables par translation. Il refuserait reellement une rotation de 0 ou de 360 degres, ou un second triangle dont l'angle n'est pas droit. Taille M, petite. C'est a `nadia-prof` de dire si elle le veut.

### 1.4 Ce qu'on demande, et a qui

| Code | Question | A qui |
|---|---|---|
| `Q1` | *"ok pour faire une rotation"* couvre-t-il la forme (d), un fichier d'environ 500 octets ? Ou veut-elle la forme (a), sans octet de dessin, avec du code et un `ADR` ? Je recommande (d) | l'utilisatrice, par `camille-po` (A.146 : la question du poids se repose pour `LP7`) |
| `Q2` | Une copie de l'existant tournee de 90, 180 ou 270 degres, aux memes proportions, tient-elle votre condition de 3.2 ? Sinon, des proportions autres, au meme cout ? Et quel angle ? | `nadia-prof` |
| `Q3` | Voulez-vous le test facultatif de 1.3, ou restez-vous la seule garde de la ligne 212 ? | `nadia-prof` |

Le lot reste ce que dit la story : *"si un dessin neuf devenait necessaire, le lot s'arrete"* jusqu'a la reponse a `Q1`.

## 2. Q2 - cinq lignes de mathematiques CE1 avec images

### 2.1 Ce qui vaut pour les cinq

**Forme sans mecanisme neuf** : des fichiers SVG statiques sous `static/illustrations`. Chaque item declare `illustration` et `illustrationTexteAlternatif` (`ADR-0073`). Le rendu est soit `manche.html:120-123` (`bandit-manchot`, `.image-item`, 11rem soit 176 px), soit `designation.html:42-44` (`.image-designation`, 22rem soit 352 px). **Ni gabarit, ni feuille, ni domaine, ni regle `ArchUnit` ne changent.** Le schema d'item avec image existe deja, et 87 items s'en servent `[mesure]`. **Aucun schema neuf, aucun `ADR` neuf.** Precedents sans `ADR` : les solides, les reseaux de points et la symetrie.

**Gardes qui jugent sans rien ecrire** : geometrie (`viewBox`, plafond, rapport, trait), `FIG-4`, `COU-2`, absence de `<text`, resolution, *"aucune ne dort"*, cliquets. Pour la designation (`MAT-CE1-02`) s'ajoutent les gardes d'`ADR-0080`, d'`ADR-0091` et d'`ADR-0094`.

**Constantes deplacees par chaque lot, taille S** : `POIDS_TOTAL_MAXIMUM`, `FICHIERS_D_ILLUSTRATION` et `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION`. S'y ajoutent `DESSINS_DE_DESIGNATION` (`:205`) pour `MAT-CE1-02`, et `POIDS_D_UN_DESSIN_MAXIMUM` si un fichier depasse 4 633.

**Garde neuve par famille, taille M** : un test de lot dans `bootstrap` qui croise chaque dessin avec la `bonneReponse` de son item. Precedents : `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` (336 lignes), `LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` (668 lignes). Le declencheur est une condition de `nadia-prof` qui affirme ce que le dessin montre (`avis-theo-preparation-palier-3-ce1.md` 3.3). C'est un test de paquet : aucune regle `ArchUnit`, aucun `ADR`.

**Ce qu'aucun garde ne voit, et qui reste a `ines-design` et `nadia-prof`** :
- le plancher de 14 px pour un chiffre trace (`:233-238`) ;
- le debordement hors du cadre (1.3) ;
- la lisibilite ;
- `CON-5`, executee seulement par un test de paquet. Pour une horloge, une regle ou une collection, le texte alternatif qui decrit l'image donne la reponse : c'est le piege commun aux cinq lignes.

**Glyphes** : il n'existe que 0, 1, 2 et 5. `LP9` doit apporter A a F, 3, 4 et 6 (`DEC-0120` A.149, a `ines-design`). L'horloge et la regle demandent aussi 7, 8 et 9. Aucune lettre d'unite (g, kg, L, cm) n'existe. Ce travail de dessin **n'a aucun prix ecrit**.

**Production** : par script hors depot, non commite, comme pour les quadrillages (`avis-theo-palier-4.md` 7.3). Ce sont les fichiers qui sont juges. Ce qui me ferait changer d'avis : une seconde regeneration d'une meme famille. Elle justifierait un generateur commite sous `outils/`, avec juge pur et test (`ADR-0009`).

**Ce qui change l'architecture : un dessin genere par le serveur.** C'est le cas d'une horloge a aiguilles calculee depuis l'heure de l'item, ou d'une regle ou d'une droite tracee depuis une valeur. **Ce n'est plus un fichier.** Il faudrait :
- des cles d'item neuves et du code de rendu, une route ou du SVG en ligne dans le gabarit ;
- un `ADR` qui remplace la regle d'`ADR-0073` (l'item declare un fichier par son identifiant) ;
- une regle `ArchUnit` sur l'endroit ou vit la geometrie ;
- **une contrepartie de chaque garde de fichier** (poids, geometrie, `COU-2`, `<text`, *"aucune ne dort"*), qui cesserait de voir ces dessins.

Taille **L**. Ce qu'on gagne : des octets. Je ne le recommande pour aucune des cinq lignes. Ce qui me ferait changer d'avis : un poids total que l'utilisatrice refuse alors que les lignes restent voulues.

**Ce que le poids total mesure** : les octets du jar, pas ce que l'enfant charge. Un ecran de manche montre un seul dessin (`manche.html:120-126`), plafonne par `POIDS_D_UN_DESSIN_MAXIMUM`.

### 2.2 Ligne par ligne

Octets par dessin : `[suppose]`, construits sur les composants de la section 0 : racine et prologue environ 300, en-tete 241, glyphe 55, instanciation 86. **N = 10 dessins par ligne** `[suppose]`, dans la fourchette des precedents (3 a 15). Le nombre d'items illustres revient a `nadia-prof` et `camille-po`.

| Ligne | Dessin | Mecanique et rendu | Octets par dessin | Pour N = 10 | Garde neuve | Mecanisme neuf |
|---|---|---|---|---|---|---|
| `MAT-CE1-01` | collection de plaques, barres et cubes, trois motifs en `<defs>` instancies par `<use>` ; aucun glyphe | `bandit-manchot`, `manche.html`, 176 px | 1 500 a 2 200 (motifs environ 450, plus 12 a 27 instanciations de 45) | 15 000 a 22 000 | M : nombre d'instances par motif, valeur egale a `bonneReponse` (patron des reseaux de points) | aucun |
| `MAT-CE1-10` | recipient gradue avec niveau, ou balance avec masses marquees ; le choix revient a `nadia-prof` | `bandit-manchot`, 176 px | 1 600 a 3 000 | 16 000 a 30 000 | M : niveau ou somme des masses egal a `bonneReponse` | aucun ; des glyphes d'unite si l'unite est sur le dessin |
| `MAT-CE1-11` | cadran, graduations en un seul chemin, nombres traces, deux aiguilles ; un fichier par heure montree | `bandit-manchot`, 176 px ; plancher de lisibilite d'un cadran a douze nombres, 123 px, mesure d'`ines-design` citee a `:164-165` | 2 600 a 3 300 avec 12 nombres ; environ 1 600 avec 12, 3, 6 et 9 seuls | 16 000 a 33 000 | M : angle des aiguilles egal a l'heure de `bonneReponse` | **aucun en fichiers ; L si l'horloge est generee par le serveur** |
| `MAT-CE1-02` | droite graduee ; les positions candidates sont des zones, marquees d'une lettre tracee | **`designation`** (`ADR-0080`), `designation.html`, 352 px, chaque fichier dans `DESSINS_DE_DESIGNATION` ; un **paquet neuf de type `designation`** et une entree de catalogue, car `ce1-comparer-ranger` est de type `question` | 2 000 a 3 500 | 20 000 a 35 000 | M : la bonne zone tombe sur la graduation de la valeur (patron de la symetrie), plus les gardes de designation existants ; trois a six zones (`avis-theo-preparation-palier-3-ce1.md` ligne 40, arithmetique non mesuree) | aucun ; la mecanique existe. `ORD-CE1-6` nomme *"placer"* : une forme `bandit-manchot` qui fait *lire* une position est a juger par `nadia-prof` |
| `MAT-CE1-09` | regle de 0 a 10 cm sous un segment | `bandit-manchot`, 176 px ; environ 16 px par centimetre `[calcul]`, donc les millimetres ne se lisent pas : centimetres seuls | 2 000 a 3 000 | 20 000 a 30 000 | M : longueur du segment, en graduations, egale a `bonneReponse` | aucun |

**Total pour les cinq lignes, N = 10** `[suppose]` : **87 000 a 150 000 octets**, soit +126 % a +217 % sur 69 203. Avec `LP9` (45 540 a 61 200, prototype de `avis-theo-palier-4.md` 7.4) et `LP7` (environ 500), le repertoire atteindrait **environ 202 000 a 281 000 octets** `[calcul]`. Le *oui* de l'utilisatrice est borne a 161 863 octets et ne vaut que pour `LP9` (`DEC-0120` A.146). **La question du poids se repose pour chacune des cinq lignes** (A.146 *"n'ouvre pas"*, A.149 point 3). *"Option 3 et 5 lignes"* rouvre le perimetre. Je ne lis pas que cette reponse donne un chiffre de poids : c'est a `camille-po` de le lui demander, avec ces fourchettes.

**Plafonds de matrice** : lever `en cours` releve de `nadia-prof` seule, et une image ne suffit pas. Exemple : la cellule de `MAT-CE1-01` porte *"la ligne ne depassera pas `en cours`, quel que soit le Done"*. Elle porte sur la forme textuelle, mais c'est a elle de dire si un dessin la leve.

### 2.3 Cout en lots

**Bareme** (`arbitrage-nadia-preparation-palier-3-ce1.md` 2) : lot illustre 1,5 ; arbitrage 0,5 et marge 0,5 par instance et par palier. **Surcout mesure en B** : `grep "^B|"` sur les registres. Au palier 3 : 1 lot, marge non consommee, 0. Au palier 4 : 2 lots, marge consommee 0,50 plus rejeu 0,25, soit **0,375 par lot**. Le prix d'un lot qui illustre un paquet deja servi peut differer de 1,5 : c'est a `nadia-prof`.

**Contraintes de composition, lues et non tranchees par moi** :
- `PAR-2` : toutes les mathematiques CE1 dans une instance, B ;
- `PAR-8` : 5,0 et trois lots par instance, donc **deux lots illustres au plus par palier en B**. 1,0 + 2 x 1,5 + 2 x 0,375 = 4,75 ; un troisieme ferait 6,625 ;
- au palier 5, B est deja pleine en lots illustres avec `LP7` et `LP9` (4,0 au bareme). Les cinq lignes commencent **au plus tot apres le palier 5** ;
- `nadia-prof` 8.4 : *"un seul"* lot illustre par palier, ecrit pour le palier 4, validite au-dela non connue ;
- `DEC-0123` 3.2 point 1 : *"une seule ligne a dessin par sprint"* (`PAR-13`), soit cinq sprints a la lettre. C'est a `camille-po` et `nadia-prof` de dire si *"option 3 et 5 lignes"* deplace cette regle.

| Cadence | Paliers | Lots | Fixe | Bareme | Avec surcout de B |
|---|---|---|---|---|---|
| deux lots par palier (`PAR-8` seul) | 3 | 7,5 | 3,0 | **10,5** | 12,4 |
| un lot par palier (8.4 reconduit) | 5 | 7,5 | 5,0 | **12,5** | 14,4 |
| une ligne par sprint (`PAR-13` a la lettre) | 5 sprints | 7,5 | 5,0 | 12,5 | 14,4 |

**Hors de ces chiffres** : le dessin des glyphes et des familles (`ines-design`, sans prix), et les cinq gardes M dans les lots. `MAT-CE1-02` porte aussi un paquet neuf de designation.

### 2.4 Ordre technique

L'ordre de produit revient a `camille-po` et `nadia-prof`, l'ordre de fusion a `sofia-sm`. Celui-ci ne suit que les dependances.

1. **`LP9`** (palier 5) : il apporte les glyphes A a F, 3, 4 et 6, et il fusionne avant tout autre lot qui installe des octets (A.149).
2. **`LP7`** (palier 5) : un fichier, aucun glyphe. Il recalcule le cliquet apres `LP9`.
3. **`MAT-CE1-01`** : aucun glyphe, le garde le plus simple (un compte d'instances).
4. **`MAT-CE1-11`**, puis **`MAT-CE1-09`** : meme jeu de chiffres 0 a 9, dont 7, 8 et 9 restent a dessiner.
5. **`MAT-CE1-10`** : la forme (balance ou recipient) et les unites sur le dessin sont a trancher d'abord par `nadia-prof`.
6. **`MAT-CE1-02`** en dernier : paquet neuf de designation, le plus de gardes, et des lettres de zone qui viennent de `LP9`.

### 2.5 Ce qu'on demande, et a qui

| Code | Question | A qui |
|---|---|---|
| `Q4` | Le poids : environ 87 000 a 150 000 octets pour les cinq lignes (N = 10), soit un repertoire de 202 000 a 281 000 octets avec `LP9` et `LP7`. Les accepte-t-elle, ligne par ligne ou ensemble ? | l'utilisatrice, par `camille-po` |
| `Q5` | La cadence : `PAR-13` (une ligne a dessin par sprint) et *"un seul"* lot illustre par palier valent-ils encore pour ces cinq lignes ? | `nadia-prof`, puis `camille-po` pour la `DEC` |
| `Q6` | Par ligne : le nombre d'items illustres, la forme de `MAT-CE1-10`, l'acceptation pour `MAT-CE1-02` de *"placer"* par designation, et la condition qui affirme ce que chaque dessin montre (declencheur du garde M) | `nadia-prof` |
| `Q7` | Les glyphes 7, 8 et 9, la marque de zone et une mesure d'un cadran et d'une regle a 176 px | `ines-design` |

## 3. Mesure et supposition, separees

- **Mesure** a `f3b06989` : nombre et poids des 56 dessins, egalite des deux cliquets et des deux populations, poids du triangle (513), glyphes existants, composants d'un fichier, items et illustrations des six paquets, alphabet d'identifiant, lignes de code citees. Le JDK n'a servi qu'aux verifications de la section 4.
- **Suppose** : tous les octets par dessin et par ligne, N = 10, le comportement d'un navigateur sur un fragment `svgView`, la lisibilite des dessins, et la lecture de ce que *"ok pour faire une rotation"* et *"option 3 et 5 lignes"* couvrent.
- **Calcul** : les sommets tournes, les totaux, les prix.

## 4. Verifications lancees avant le commit

- **Classes de `bootstrap` qui jugent `squad/` et `docs/backlog`.** Liste derivee par `grep -rl 'squad\|docs/backlog' bootstrap/src/test`, soit 11 classes. Lancees par `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=<les 11> -Dsurefire.failIfNoSpecifiedTests=false`, fichier present sur le disque, non commite. Ces gardes parcourent le disque, pas l'index. Resultat : **79 tests, 0 echec, 0 erreur, `BUILD SUCCESS`**. Machine virtuelle des tests : Java 21.0.10, version rapportee par `ArchUnit` dans le journal.
- **Scanner de cellules naif.** Chaque ligne de tableau compte ses `|` sans egard aux accents graves, et le compte est compare a celui de l'en-tete. **0 ecart.** Caracteres hors ASCII : 0.
- **Non lances** : le `build` complet, les jobs `prefixes` et `chemins` de `ci.yml`. **Je ne declare pas la porte technique.**
