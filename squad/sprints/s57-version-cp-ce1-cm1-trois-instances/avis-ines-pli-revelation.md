# [Sprint s57] Avis d'`ines-design` : le retour faux et la révélation sous le pli, la coupure de "grands-parents"

Réponse à la décision de l'utilisatrice (Q1, option 2) de traiter, avant la clôture, le constat 1 de
`nadia-prof` (`avis-nadia-cloture-s57.md`, section 4.3) et les constats 2, 4 et 5 qui s'y rattachent.
**Ce document propose, il ne modifie aucun gabarit ni aucune feuille.** `nadia-prof` garde le veto,
la décision revient à `camille-po` et à l'utilisatrice. Il ne prononce aucun Done.

## 0. Ce que j'ai lu, ce que je n'ai pas mesuré

- Lu : `fragments/manche.html`, `static/css/recreo.css`, `vendor/bootstrap.min.css` (5.3.8),
  `ce1-qlm-generations.yaml`, `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`, ADR-0017, le
  commentaire de ADR-0030/M1.
- **Aucune mesure à l'écran faite ici.** Les cotes de `nadia-prof` (744 x 551, 360) sont reprises
  telles qu'elle les écrit. Les hauteurs en pixels ci-dessous sont **lues dans les valeurs de la
  feuille** (interligne, rembourrage, hauteur déclarée), pas relevées : elles donnent un ordre de
  grandeur et un sens, pas une cote. Toute option retenue demande une mesure par navigateur avant
  d'être dite tenue.

## 1. Causes

### C1 - Pourquoi le retour faux et l'indice passent sous le pli quand une proposition tient sur deux lignes

Le retour ne dépend de rien d'autre que de la hauteur de ce qui le précède. Trois faits de structure :

1. **Le message est le dernier bloc de la pile, sans position propre.** `manche.html:192`
   (`.message-jeu`) vient après `.cabinet` (`manche.html:151-182`), lui-même après `.enonce`
   (`:136`). `.message-jeu` ne porte aucune propriété de position (`recreo.css:1606`, règle vide) :
   sa cote haute est exactement le bas du meuble. Tout pixel ajouté plus haut descend le signal, la
   réponse nommée et l'indice d'autant.
2. **Une ligne de proposition de plus coûte une ligne entière, une rangée de plus coûte une
   rangée entière.** `.propositions` est un flex qui se replie (`recreo.css:609-616`,
   `flex-wrap: wrap`) et `.proposition` a `flex: 1 1 auto` (`:618-620`) : un bouton dont le texte est
   long réclame sa largeur de contenu, les trois ne tiennent plus côte à côte et passent chacun sur
   leur rangée. Les propositions sont à 2 rem (`recreo.css:1090-1092`), rembourrage vertical
   0,5 rem (`:1016-1018`), interligne 1,25 à partir de 720 px (`:1950`). Lecture : un bouton d'une
   ligne vaut environ 56 px, de deux lignes environ 96 px, plus 8 px entre rangées (`:101`). Trois
   rangées de deux lignes valent donc environ 300 px contre environ 56 px pour une rangée de trois
   mots courts. C'est la même cause pour toutes les propositions longues, pas pour `-12` seul.
3. **Le signal est le plus haut des blocs du message après une erreur.** À partir de 720 px la
   forme commune pose le signal à gauche et le texte à droite (`recreo.css:1906-1930`,
   `grid-template-columns: 15rem minmax(0, 1fr)`). Le signal fait `.icone-signal` 3 rem
   (`recreo.css:1552-1558`) plus 2 x 0,5 rem de rembourrage (`:1539-1544`), environ 64 px ; l'indice
   seul sur une ligne est plus bas. D'où, chez `nadia-prof`, un bas de l'indice à 630 et un bas du
   signal à 648 : c'est le signal qui descend le plus, pas l'indice.

À 551 px de haut, nadia a besoin de remonter d'environ 97 px (648 - 551) sur `-12` item 8 après une
erreur. Les autres états en demandent plus : voir C3.

### C2 - Pourquoi "grands-parents" est coupé

Le navigateur a le droit de couper après un trait d'union ordinaire (U+002D) : c'est une
opportunité de saut de ligne que rien ne retire ici.

- Aucune règle de `recreo.css` ne traite la coupure : pas de `hyphens`, pas de `overflow-wrap`, pas
  de `word-break`, pas de `white-space` sur `.proposition` (seuls usages de `nowrap` : `:772`, une
  enseigne, et `:1767`, un `flex-wrap`).
- `.btn` de Bootstrap 5.3.8 ne pose pas non plus de `white-space` : le texte passe à la ligne.
- Le gabarit rend la proposition brute (`manche.html:176`, `th:text="${proposition}"`) et le contenu
  l'écrit avec un trait d'union ordinaire (`ce1-qlm-generations.yaml:83, 97, 105, 132`). Le seul
  endroit où une espace insécable est posée est la phrase de révélation d'une manche en image
  (`manche.html:214`, ADR-0067), jamais une proposition.
- Les trois suites de l'item 8 (`arrière-grands-parents, grands-parents, parents` et ses deux
  distracteurs, `yaml:132-133`) font 47 à 50 caractères. À 2 rem gras, c'est plus large que la rangée
  à 744 px (environ 680 px de rangée moins 40 px de rembourrage, d'après ADR-0054) : le texte doit
  passer à la ligne, et il le fait au premier saut permis, qui est le trait d'union.

**Ce qu'aucune règle de feuille ne peut faire.** Aucune propriété CSS n'ôte le saut permis après un
U+002D. `word-break: keep-all` ne vaut que pour le chinois, le japonais et le coréen ; `hyphens:
manual` est déjà le comportement par défaut. Retirer ce saut passe par le caractère (U+2011) ou par
un balisage par mot, donc par le contenu ou par le gabarit, pas par la feuille.

**360 px.** Un mot insécable de "arrière-grands-parents," fait, d'après les mêmes valeurs, de l'ordre
de 370 à 400 px à 2 rem gras, pour environ 280 px de texte disponible dans un bouton à 360 de large
(rangée ~320 moins rembourrage latéral 40). **Estimation non mesurée.** Si elle se confirme, aucune
mise en page ne tient `V1` de `-12` à 360 pour ce mot : il faudrait baisser la police (plancher de
`TailleDesTextesEtBoutonsTest`, une seule exception accordée, `recreo.css:618-637`) ou le rembourrage
latéral (« épuisé à 360 px », `recreo.css:1010`), ou laisser le mot déborder ou se couper en lettres.
C'est une décision pour `nadia-prof`, pas un réglage. Rappel : 360 n'est plus une référence de pli
depuis `DEC-0040`, mais `V1` de la fiche de `-12` l'exige toujours par écrit.

### C3 - Pourquoi la réponse nommée passe sous le pli à la révélation (constats 4 et 5)

À la révélation le bloc de droite empile, dans l'ordre du document (`manche.html:215-218`) : la
phrase "Appuie sur la bonne réponse :", la réponse à 2 rem gras, l'explication éventuelle, l'indice.
La réponse nommée est le **deuxième** bloc, collé au bas du meuble : sa cote ne dépend que de la
hauteur de tout ce qui est au-dessus, comme en C1. Elle passe sous le pli quand le haut de page plus
le meuble dépasse environ 500 px. Rien dans le message lui-même ne peut la remonter : c'est la
pile au-dessus qui est trop haute.

- `-03`, `-05`, `-08`, `-12` : meuble haut (propositions sur deux lignes ou trois rangées), C1.
- **Horloge** : `.illustration` plafonne à 11 rem, soit 176 px (`recreo.css:421-428, 447-452`), plus
  ses marges, au-dessus de l'énoncé, `manche.html:120-127`. Le cadran n'est pas une proposition : le
  meuble est bas, c'est l'illustration qui mange le budget, sur les douze items (constat 4).
- Le plafond de 11 rem est écrit comme la valeur du « confort de lecture » mesurée par `ines-design`
  (`recreo.css:431-440`), et ce même commentaire dit que le pli est hors de la porte pédagogique
  (`DEC-0051`, `DEC-0053`). Je ne le rouvre pas sans que `nadia-prof` le demande.

## 2. Options

Légende des effets : **résout** (la cote attendue passe sous 551 sur les états de `nadia-prof`, à
confirmer par mesure), **aide** (gain réel mais insuffisant seul), **sans effet**. Les gains en pixels
sont lus dans la feuille, non mesurés. Les constats sont ceux de `avis-nadia-cloture-s57.md`,
section 4.3 : 1 (`-12` item 8, rien à l'écran après erreur), 2 (coupure de "grands-parents"),
4 (horloge à la révélation), 5 (cinq jeux à la révélation).

**Périmètre commun à toute option qui touche `.cabinet`, `.score`, `.message-jeu` ou `.proposition`** :
`fragments/manche.html` est le seul gabarit de manche à propositions écrites, donc **tous les jeux
bandit-manchot servis par ce fragment**, pas seulement `-12`. La forme commune existante exclut
`.ecran-deux-oeuvres` et vaut à partir de 720 px (`recreo.css:1906-1930`) ; sous 720 px rien ne
change aujourd'hui, et rien ne changerait sans le dire.

**Garde commune** : `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` épingle les dix-sept
déclarations de la forme commune et **échoue sur toute règle nouvelle dont le sélecteur contient
`:not(.ecran-deux-oeuvres)`** (« une regle de la population qui n'est pas attendue »). Toute option
en feuille qui étend la forme commune exige donc de mettre ce test à jour **dans le même commit**.

### O0 - Ne rien changer

Aucun effet sur 1, 2, 4, 5. Argument pour : le pli est hors de la porte pédagogique (`DEC-0051`,
`DEC-0053`), l'enfant bloqué peut toujours avancer (nadia, section 4.2) ; la fiche de `-12` prévoit
elle-même qu'une coupure devienne une story. Argument contre : `V1` de `-12` reste non tenue.

### O1 - Trait d'union insécable dans les propositions (constat 2)

Remplacer U+002D par U+2011 dans le texte affiché des propositions. Deux emplacements possibles :

- **O1a - dans le contenu** : écrire la séquence d'échappement de U+2011 dans le YAML des propositions concernées. Précédent :
  l'espace insécable est déjà posée ainsi, en séquence d'échappement, jamais en caractère
  (`LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest`). **Contenu : à `camille-po`,
  `marc-dev`/`lea-dev`, avec relecture de `nadia-prof`**, pas à moi. Périmètre : les paquets qui
  portent un mot composé avec trait d'union dans une proposition (liste à dériver par recherche, non
  faite ici).
- **O1b - dans le gabarit** : `manche.html:176` affiche `#strings.replace(proposition, '-', U+2011)`,
  la valeur du bouton (`th:value`) restant l'originale. Périmètre : **toutes les propositions de
  tous les jeux**, y compris `"3-4"`, une date, une plage, une soustraction écrite avec un trait
  d'union. Je n'ai pas vérifié ce que cela change pour ces paquets : c'est le défaut de la forme
  générique. Je ne le recommande pas sans que `sofia-sm` dise si le gabarit a le droit de
  transformer un texte de contenu (la frontière contrôleur/gabarit n'est pas la mienne).

Effet : **résout 2 à 744** si la ligne coupe alors aux virgules et aux espaces (les saut restent
permis). **Sans effet sur 1, 4, 5** : le bouton fait toujours autant de lignes. **Risque** : (R-a)
couverture du glyphe U+2011 par la pile de polices système (`LaPileDePolicesServieNeChangePasEnSilenceTest`
garde la pile, pas la couverture) : un repli de police change l'apparence du trait, **à regarder par
nadia**, pas déduit ; (R-b) lecture d'écran : « trait d'union insécable » peut être lu ou tu selon le
lecteur, et aucune écoute réelle n'a eu lieu (c'est la limite déjà nommée par ADR-0035/F11) ;
(R-c) à 360 px, voir C2 : sans saut permis, le mot de 22 caractères peut déborder au lieu de se couper
(constat à mesurer avant tout choix).

### O2 - Remonter le score à droite de la consigne (constats 1, 4, 5, gain faible)

Reprendre pour l'écran de manche à propositions écrites la disposition L2 déjà mesurée sur l'écran à
deux œuvres (`recreo.css:1863-1876`, `.score` en `grid-area` à droite de `.consigne`), à partir de
720 px. Gain lu : une ligne de score, environ 30 px.

Effet : **aide** sur 1, 4, 5 (sur `-12` item 8 il manque environ 97 px, sur `-12` jusqu'à 710 chez
nadia pour une réponse nommée, d'où « insuffisant seul »). Sans effet sur 2. Périmètre : tous les
écrans de manche à propositions écrites, à partir de 720 px. **Risque** : un sélecteur nouveau avec
`:not(.ecran-deux-oeuvres)` met rouge le test d'épinglage (à mettre à jour dans le même commit) ;
le score change de place pour l'enfant, **à regarder par nadia** ; la consigne tient sur une ligne
sur tous les jeux ? (non vérifié : une consigne sur deux lignes à côté du score gagne moins).

### O3 - Ôter le bandeau et le socle du meuble sur les écrans de manche à propositions écrites (constats 1, 4, 5)

Étendre à ces écrans la règle L1 déjà posée pour l'écran à deux œuvres
(`recreo.css:1852-1855`, `.cabinet-bandeau, .cabinet-socle { display: none }`). Gain lu : 2 rem +
1 rem, environ 48 px.

Effet : **aide** sur 1, 4, 5, pas assez seul pour `-12` à la révélation. Sans effet sur 2.
Périmètre : tous les jeux à propositions écrites. **Risque élevé** : (R-d) `ADR-0030` fait du
cabinet un objet fermé sur quatre côtés, qui entoure les colonnes, avec bandeau et socle ; la
condition « le visage, les étoiles, le bandeau et le socle gardent leur hauteur » est écrite dans la
forme commune elle-même (`recreo.css:1904`), acceptée par `nadia-prof` ; (R-e) `CabinetDeLaMachineTest`
la garde. **C'est un arbitrage de `nadia-prof` et un amendement d'`ADR-0030` par `theo-archi`**,
pas une retouche de feuille. Le précédent L1 existe seulement pour l'écran à deux œuvres, où elle
a été arbitrée.

### O4 - Disposition « côté » de l'illustration pour les écrans qui en portent une (constat 4)

À partir de 720 px, sur un écran de manche dont `.illustration` est présente, placer l'illustration
dans une colonne à gauche, face à l'énoncé et au meuble, comme la grille déjà écrite pour
`.ecran-deux-oeuvres` (`recreo.css:1863-1900`, colonne de 11 rem). L'ordre du document ne change pas.
Gain lu pour l'horloge : la hauteur de l'illustration, environ 176 px plus ses marges.

Effet : **résout probablement 4** (la réponse nommée remonte d'environ 190 px sur les douze items,
à mesurer). Sans effet sur 1, 2 ; **aide** sur 5 pour l'horloge seule. Périmètre : **tout jeu qui
déclare une illustration** (ADR-0073), pas l'horloge seule - je ne sais pas combien, la liste est à
dériver. **Risque** : la colonne retire environ 200 px de largeur au meuble (11 rem + 1,5 rem) et
peut faire passer des propositions sur plus de lignes ou de rangées sur les jeux à illustration :
(R-f) `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest` et `LaLargeurDemandeeParUneRangeeEstEpingleeTest`
(ADR-0054, ADR-0056) épinglent la largeur de rangée offerte, elle changerait ; (R-g)
`ChaqueIllustrationDeContenuDeclareSaGeometrie...` et les 30 couples mesurés d'ADR-0073 (`manche.html:93-100`)
valaient pour le placement entre consigne et énoncé. À mesurer sur chaque jeu à illustration avant
toute décision, aux dix largeurs et aux trois états.

### O5 - Rembourrage vertical des propositions de 0,5 à 0,25 rem (constats 1, 5, gain faible)

Gain lu : 8 px par rangée de propositions, soit 16 à 24 px. La cible tactile reste au-dessus de
44 px (une ligne à 2 rem x 1,25 fait 40 px, plus 2 x 0,25 rem = 48 px).

Effet : **aide** sur 1 et 5, sans effet sur 2 et 4. Périmètre : tous les jeux, toutes largeurs sauf
si posé sous `@media`. **Risque le plus lourd des cinq** : (R-h) ADR-0017 compare à la composante près
(`font-size`, `min-height`, `padding`) le bouton qui fait avancer et le bouton qui sort
(`LActionNeDeclarePasMoinsQueLaSortieTest`) ; (R-i) `recreo.css:1010-1015` dit que 0,5 rem est un
plancher « pas une pente, toute valeur en dessous revient devant elle » ; (R-j) le rapport de
saillance action/sortie est déjà le motif de deux refus de `nadia-prof`, et ADR-0017 ne garde ni la
couleur, ni la position, ni la largeur des libellés. **Je ne propose pas d'aller dans cette
direction avant que `nadia-prof` dise si elle rouvre le plancher.**

### O6 - Raccourcir la suite de l'item 8 (constats 1 et 2 de `-12` seulement)

Ce n'est pas de la mise en page : les trois suites de l'item 8 sont les plus longues du paquet
(`yaml:131-133`) ; une formulation plus courte retire la deuxième ligne à 744. **Contenu : à
`camille-po`, `marc-dev`, `lea-dev`, avec le jugement de `nadia-prof`** sur ce que la suite doit
dire. Je n'en tranche ni la forme ni le niveau. Effet : constats 1 et 2 sur `-12` seulement ;
aucun sur 4 et 5. Aucun risque de garde de gabarit ; relecture par le `relecteur-pedagogique`.

### O7 - Message au-dessus du meuble (refusée par construction)

Placer `.message-jeu` entre l'énoncé et le meuble le rendrait indépendant de la hauteur du meuble :
il résoudrait en apparence 1, 4 et 5. Écartée : `manche.html:183-186` (« jamais au-dessus (M1, M4) »,
arbitrage de `nadia-prof`) l'interdit ; et la bonne proposition, qui est ce que l'enfant doit presser
à la révélation, passerait sous le pli à la place de la réponse nommée. À ne rouvrir qu'à la demande
de `nadia-prof`.

## 3. Effet par constat, en une table

| Option | 1 (`-12` it. 8, erreur) | 2 (coupure) | 4 (horloge, révélation) | 5 (cinq jeux, révélation) | Qui décide |
|---|---|---|---|---|---|
| O0 rien | non | non | non | non | `nadia-prof`, `camille-po` |
| O1a / O1b trait insécable | non | oui à 744, **360 non établi** | non | non | O1a contenu, O1b `sofia-sm` |
| O2 score à côté | aide (~30 px) | non | aide | aide | `nadia-prof` |
| O3 sans bandeau ni socle | aide (~48 px) | non | aide | aide | `nadia-prof` + `theo-archi` (ADR-0030) |
| O4 illustration à côté | non | non | oui probable | aide (horloge) | `nadia-prof` + `theo-archi` |
| O5 rembourrage 0,25 rem | aide (16-24 px) | non | non | aide | `nadia-prof` (refus passés) |
| O6 suite plus courte | oui (`-12`) | oui (`-12`) | non | non | `camille-po`, `nadia-prof` |

**Aucune option seule ne règle les constats 1 et 5 à la révélation sur `-12`** : il manque encore
plus de 100 px avec O2 + O3 (environ 78 px lus) sur la cote la plus basse de `nadia-prof` (710). Les
combinaisons qui ont une chance sont O1 + O2 + O3, ou O6 pour `-12` et O4 pour l'horloge, à mesurer.

## 4. Ce que je propose de décider, dans l'ordre

1. `nadia-prof` dit si `V1` de `-12` vaut toujours à 360 px (C2) : sans cela, O1 ne peut pas être
   jugé complet.
2. `camille-po` choisit entre O1a (contenu) et O1b (gabarit, à passer à `sofia-sm`) pour le constat 2.
3. `nadia-prof` dit lesquels de O2, O3, O4 elle accepte de voir mesurés ; je ne les mesure qu'après
   ce choix, pas avant, comme le veut le principe « arbitrage préalable » de ce dépôt.
4. `theo-archi` : amendement d'`ADR-0030` si O3 est retenue ; mise à jour de
   `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` dans le même commit que toute règle de
   feuille qui s'y ajoute.

## 5. Ce que ce document ne fait pas

- Aucune mesure à l'écran, aucune capture, aucun navigateur lancé.
- Aucune modification de gabarit, de feuille ni de test.
- Aucun jugement sur le contenu de l'item 8 (O6 est un chemin, pas une recommandation).
- Aucun Done pédagogique : `nadia-prof` seule.
