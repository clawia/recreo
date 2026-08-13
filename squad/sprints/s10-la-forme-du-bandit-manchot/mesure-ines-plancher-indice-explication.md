# Mesure ines-design - le plancher de 1,25 rem de .indice/.explication, chiffre

Suite directe de la mesure du deplacement de l'indice (0,00 px sur les sept jeux, cabinet enterre
sous le pli sur "Le bon verbe" et "Petits problemes"). Repond a la demande de l'utilisatrice :
chiffrer ce que rendrait, par jeu et par etat, la baisse du plancher de 1,25 rem de `.indice` et
`.explication`, seul puis cumule au resserrement de marges deja propose et jamais implemente.

## Lignee et methode

Worktree `s10-ines-pli`, provisionne depuis `round-two` a `2d34a15`, arbre propre. Aucune ligne de
`recreo.css` ni de gabarit modifiee sur ce worktree.

**Jar reel, session HTTP reellement pilotee, viewport fixe par le protocole DevTools** (`Emulation.
setDeviceMetricsOverride`, 360 x 640 px exactement, jamais une taille de fenetre) - la methode
retenue au tour precedent, reconduite a l'identique. Chrome headless isole (`--user-data-dir`
dedie, `--remote-allow-origins` requis par la version presente sur ce poste, absent du script
`outils/mesurer-rendu.sh` qui date d'avant), jamais attache a une session existante. Les deux
processus (le jar, Chrome) ont ete arretes en fin de mesure par leur PID exact, jamais par nom
d'image.

**Correction de la lecon d'aout, appliquee ici et pas seulement enoncee.** L'erreur du tour d'aout
etait de mesurer un item median. Ici, pour chaque jeu et pour la sequence d'echec, l'item **le
plus charge** (`longueur(enonce) + longueur(indice)` maximale sur tout le paquet YAML servi) est
cible par tirages repetes jusqu'a l'obtenir - jamais suppose, jamais pris au hasard. Pour l'etat de
reussite, c'est l'item a l'enonce le plus long qui est cible, puisque le message de reussite
("Bravo !") ne varie jamais et que seul l'enonce au-dessus du cabinet fait varier la hauteur totale.
"Calcul CP" n'a pas de paquet YAML (generation procedurale, indice fixe "Compte sur tes doigts.") :
un seul tirage suffit, la variance y est negligeable.

**Une seule session par item cible, mesuree plusieurs fois sans jamais rejouer.** Le rendu livre
par le serveur (l'etat "baseline") est capture une fois ; les variantes de feuille de style sont
ensuite injectees en memoire (`<style>` ajoute par le navigateur, jamais un fichier du depot) et
remesurees sur le **meme DOM**, sans nouvel echange HTTP. C'est la meme discipline que la mesure du
deplacement de l'indice au tour precedent (comparer deux styles sur un seul et meme tirage, jamais
deux tirages differents).

**Pli** : 640 px de hauteur de viewport, verifie a chaque etat. "Deficit" ou "depasse de N px"
signifie `document.documentElement.scrollHeight - 640`.

**Six variantes mesurees a chaque etat** :

| Variante | Ce qu'elle change |
|---|---|
| `baseline` | production actuelle, aucune surcharge |
| `floor-1.125rem` | `.indice` et `.explication` a `1.125rem` (au lieu de `1.25rem`) |
| `floor-1rem` | `.indice` et `.explication` a `1rem` |
| `resserrement-seul` | `.consigne`/`.enonce` `margin-bottom: 0.25rem` ; `.message-jeu > .signal` `padding: 0.5rem 0.75rem` ; `.message-jeu > .indice`/`.explication` `margin: 0.25rem 0 0` et `padding: 0.5rem 0.75rem` - la proposition de resserrement de marges deja ecrite (section 3 de `mesure-ines-budget-vertical-m4.md`), adaptee aux classes reelles de la mecanique livree (`.message-jeu` et ses enfants, qui n'existaient pas quand cette proposition a ete ecrite) |
| `resserrement+floor-1.125rem` | les deux cumules |
| `resserrement+floor-1rem` | les deux cumules |

Script et donnees brutes dans le repertoire de travail de l'agent, non commites (mesure, pas
livrable de code) : `mesurer_plancher.py`, `resultats-plancher.json`, `rerun-comparer.json`
(reprise d'un tirage manque sur "Compare les nombres" au premier passage, 200 tirages au lieu de
60).

## 1. Quels blocs le plancher gouverne aujourd'hui - fait de code, pas d'intention

Ce n'est pas une simple convention CSS partagee entre deux classes. `TailleDesTextesEtBoutonsTest`
encode une seule et meme constante, `PLANCHER_DE_LISIBILITE_REM = 1.25`, sur **deux perimetres
distincts** :

1. **`.consigne` y est epingle a l'egalite stricte** (`aucunTexteNePasseSousLaTailleDeLaConsigne`,
   ligne 531 : `assertThat(consigne).isEqualTo(PLANCHER_DE_LISIBILITE_REM)`), et sept autres
   selecteurs doivent rester **au moins aussi grands que `.consigne`** dans le meme test : `.score`,
   `.indice`, `.fin-de-partie`, `.erreur`, `.pas-de-jeu`, `.message-reprise`, `.explication`. Un
   second test, `lIndiceEstAuMoinsAussiGrandQueLaConsigne`, refait la meme verification pour
   `.indice` seul, a l'egalite ou a l'ecart selon la lecture.
2. **Tout bouton reellement rendu dans l'application**, sur n'importe quel ecran, doit atteindre la
   meme valeur (`verifierPlancherDeLisibilite`, S2-1) - `.proposition`, `.sortie-pleine`, tous les
   boutons de tous les etats confondus.

**Consequence directe pour ce qui est demande.** Baisser `.indice`/`.explication` seuls, sans
toucher `.consigne`, est **aujourd'hui bloque par le test**, pas par une simple habitude d'ecriture
: tant que `.consigne` reste a `1,25rem`, l'assertion `>= consigne` interdit a `.indice` de
descendre en dessous. Le geste qui rendrait le levier possible n'est donc pas de baisser
`PLANCHER_DE_LISIBILITE_REM` (ce qui entrainerait tous les boutons de l'application avec lui - un
tout autre chantier, hors de portee de cette mesure) mais de **sortir `.indice` et `.explication` de
la liste couverte par ce test** et de leur donner une regle dediee, plus basse, assumee comme telle
- deux methodes de test a reecrire dans le meme commit que la feuille, nommement, pas un
defaisement du plancher des boutons.

**`.message-jeu` lui-meme ne porte aucune regle** (classe vide, commentaire du lot mecanique :
"sa forme revient au lot qui porte le meuble"). Ce que "et par extension du bloc de message" veut
dire concretement : la hauteur du bloc sous le meuble est la somme de `.signal` (jamais touche par
ce levier), `.indice` et `.explication` (les deux boites touchees) - il n'y a pas de neuvieme
selecteur "message" a editer, seulement l'effet mecanique sur le total.

**`.explication` ne s'affiche jamais sur les deux jeux qui posent probleme.** Aucun item de
`cm1-conjugaison-present.yaml` ni de `cp-problemes-additifs.yaml` ne porte de champ `explication` -
seul `cp-le-bon-son.yaml` en porte (5 items sur 15, les exceptions graphie/son). **Baisser
`.explication` est donc un geste a cout nul et a effet nul sur "Le bon verbe" et "Petits
problemes"** : seule la baisse de `.indice` y produit un gain, mesure ci-dessous. Sur "Le bon son",
les deux jouent ensemble sur l'etat de revelation des items d'exception.

## 2. Ce que chaque variante rend, par jeu et par etat

Deficit = px au-dela du pli (640 px). "TIENT" = 0 ou negatif. Item cible entre parentheses sous
chaque jeu.

### Calcul CP (`8 + 8`, indice fixe)

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1 et 2 | 21 | 18 | 15 | **TIENT** | TIENT | TIENT |
| echec 3 (revelation) | TIENT | TIENT | TIENT | TIENT | TIENT | TIENT |
| reussite | TIENT | TIENT | TIENT | TIENT | TIENT | TIENT |

Le resserrement seul suffit deja. Le plancher n'est pas necessaire sur ce jeu.

### Le bon son (`MANTEAU`, indice "On entend aussi ce son dans le mot maman.")

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1 et 2 | 51 | 45 | 39 | **TIENT** | TIENT | TIENT |
| echec 3 (revelation) | 23 | 17 | 11 | **TIENT** | TIENT | TIENT |
| reussite | TIENT | TIENT | TIENT | TIENT | TIENT | TIENT |

Meme constat : le resserrement seul suffit. Le plancher n'est pas necessaire sur ce jeu non plus.

### Compare les nombres (`45 ___ 40`, indice "Les deux ont 4 dizaines : regarde les unites, 5 unites, c'est plus que 0 unite.")

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1 et 2 | 81 | 72 | 39 | 29 | 20 | **TIENT** |
| echec 3 (revelation) | 53 | 44 | 11 | 1 | **TIENT** | TIENT |
| reussite | TIENT | TIENT | TIENT | TIENT | TIENT | TIENT |

**Le resserrement seul ne suffit pas ici** (reste 29 px sur l'echec, 1 px sur la revelation) : il
faut le cumul avec le plancher a `1rem` pour fermer l'echec, `1,125rem` suffit pour la revelation
seule.

### Les complements a 10 (`5 + ? = 10`, indice "Leve 5 doigts d'une main, puis compte ceux de l'autre main : il en reste 5 pour arriver a 10.")

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1 et 2 | 111 | 102 | 93 | 59 | 50 | **41** |
| echec 3 (revelation) | 83 | 74 | 65 | 31 | 22 | **13** |
| reussite | TIENT | TIENT | TIENT | TIENT | TIENT | TIENT |

**Ne tient dans aucune combinaison mesuree.** Meme le cumul maximal (resserrement + plancher a
`1rem`) laisse 41 px sur l'echec et 13 px sur la revelation. Ce jeu n'etait pas signale par la
mesure precedente (l'ancien rapport `mesure-ines-budget-vertical-m4.md`, ecrit avant que la
mecanique existe, le disait "TIENT" avec 238 px de marge) - sur l'item reellement le plus charge du
paquet reel, il ne tient pas. C'est une information neuve, pas une confirmation.

### Petits problemes ("Le jardinier a 6 fleurs et en plante 2 de plus. Combien en a-t-il ?", indice "Il avait des fleurs et il en plante d'autres : additionne 6 et 2.")

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1 et 2 | 195 | 189 | 183 | 143 | 137 | **131** |
| echec 3 (revelation) | 167 | 161 | 155 | 115 | 109 | **103** |
| reussite | 63 | 63 | 63 | 31 | 31 | **31** |

**Ne tient dans aucune combinaison mesuree**, sur aucun des quatre etats. Le cumul maximal laisse
131 px sur l'echec, 103 px sur la revelation, 31 px meme sur la reussite (ou le plancher, lui, ne
change rigoureusement rien : 63 -> 63 avant resserrement, 31 -> 31 apres - voir section 3).

### Le bon verbe ("Autrefois, ils ___ ce dessin anime chaque mercredi.", indice "Le mot 'autrefois' annonce l'imparfait, jamais le futur : avec 'ils' ou 'elles', le verbe 'aimer' devient 'aimaient', pas 'aimeront'.")

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1, 2 et 3 (revelation) | 321 | 279 | 267 | 269 | 227 | **215** |
| reussite | 51 | 51 | 51 | 19 | 19 | **19** |

**Ne tient dans aucune combinaison mesuree.** Le cumul maximal laisse 215 px sur les trois etats
d'echec (le message ne change pas de forme entre l'echec et la revelation sur cet item : le verbe
"aimaient"/"aimeront" garde le texte de revelation sur deux lignes, meme hauteur que le message
d'echec) et 19 px meme sur la reussite.

**Le baseline de 321 px est plus severe que celui deja connu (-281 px).** Sur l'item veritablement
le plus charge du paquet (recherche systematique, pas un tirage constate), le deficit est plus
grand que celui rapporte precedemment, pas plus petit : la aussi, chercher l'extreme aggrave le
constat plutot que de le confirmer a l'identique. Les deux mesures pointent la meme direction :
aucune n'autorise a conclure que ce jeu tient.

### Soustractions CP (`7 - 7 = ?`, indice "Leve 7 doigts, puis baisses-en 7 un par un : il n'en reste plus aucun.")

| Etat | baseline | floor 1,125 | floor 1 | resserrement seul | +floor 1,125 | +floor 1rem |
|---|---|---|---|---|---|---|
| echec 1 et 2 | 81 | 45 | 39 | 29 | **TIENT** | TIENT |
| echec 3 (revelation) | 53 | 17 | 11 | 1 | **TIENT** | TIENT |
| reussite | TIENT | TIENT | TIENT | TIENT | TIENT | TIENT |

**Le resserrement seul ne suffit pas** (reste 29 px sur l'echec, 1 px sur la revelation, comme
"Compare les nombres") : il faut le cumul avec le plancher, et `1,125rem` suffit deja ici (pas
besoin de descendre a `1rem`).

## 3. Le plancher touche-t-il l'etat de reussite ? Non, jamais, nulle part

Sur les sept jeux, **le plancher seul (`floor-1.125rem` ou `floor-1rem`, sans resserrement) ne
change pas un seul pixel de l'etat "reussite (Bravo)"** - le gain mesure est 0 partout, y compris
sur "Le bon verbe" (51 -> 51) et "Petits problemes" (63 -> 63), les deux seuls jeux ou cet etat
depasse le pli. C'est mecanique et non une coincidence de mesure : le controleur ne pose
`explication` que dans la branche `revelee()`, jamais dans la branche `signalJuste` (M11 clause 3),
et `indice()` retourne vide tant que `essaisSurLaManche == 0`, ce qui est toujours le cas sur une
reussite au premier coup. Le seul enfant du `.message-jeu` sur cet etat est `.signal`, jamais
touche par ce levier. **Le raisonnement du point 1 de la demande est confirme : ce levier ne peut
rien, par construction, contre le deficit de l'etat de reussite.**

Ce que le resserrement seul y fait, lui : -32 px sur "Le bon verbe" (691 -> 659, encore 19 px de
trop) et -32 px sur "Petits problemes" (703 -> 671, encore 31 px de trop) - via le rembourrage de
`.consigne`/`.enonce` et celui de `.signal`, les trois seuls elements presents sur cet etat.

## 4. Bilan

| Jeu | Sans rien toucher | Resserrement seul | Resserrement + plancher maximal (1rem) |
|---|---|---|---|
| Calcul CP | ne tient pas (21 px) | **TIENT** | TIENT |
| Le bon son | ne tient pas (51 px) | **TIENT** | TIENT |
| Compare les nombres | ne tient pas (81 px) | ne tient pas (29 px) | **TIENT** (1rem necessaire) |
| Soustractions CP | ne tient pas (81 px) | ne tient pas (29 px) | **TIENT** (1,125rem suffit) |
| Les complements a 10 | ne tient pas (111 px) | ne tient pas (59 px) | **ne tient pas (41 px)** |
| Petits problemes | ne tient pas (195 px) | ne tient pas (143 px) | **ne tient pas (131 px)** |
| Le bon verbe | ne tient pas (321 px) | ne tient pas (269 px) | **ne tient pas (215 px)** |

**Quatre jeux sur sept finissent par tenir**, mais **deux d'entre eux (Compare les nombres,
Soustractions CP) ont besoin du plancher en plus du resserrement** - le resserrement seul ne les
ferme pas (29 px de reste sur les deux). Ce n'est pas la repartition "quatre jeux a -11 px deja
regles par le resserrement seul, plancher inutile pour eux" nommee en debut de tour : sur l'item
reellement le plus charge de chacun, seuls **Calcul CP et Le bon son** tiennent au resserrement
seul ; les deux autres ont besoin du plancher, et meme alors, seul `1rem` (pas `1,125rem`) ferme
"Compare les nombres" sur son etat d'echec. Le plancher **n'est donc pas gratuit ni inutile** pour
deux jeux sur sept, contrairement a l'hypothese a verifier : il est necessaire, meme si ce n'est
pas sur les deux jeux qui inquietaient.

**Trois jeux ne tiennent dans aucune combinaison mesuree** : "Le bon verbe" (-215 px au mieux),
"Petits problemes" (-131 px au mieux) et **"Les complements a 10" (-41 px au mieux), non signale
jusqu'ici**. Descendre le plancher plus bas que `1rem` ne fermerait toujours pas ces trois cas : le
gain observe entre `1,25rem` et `1rem` est de 6 a 54 px selon le jeu et l'etat (jamais plus, parce
qu'un `.indice` ou une `.explication` a `1rem` reste un bloc de texte qui occupe presque autant de
lignes qu'a `1,25rem` - le gain vient surtout de la hauteur de ligne, pas du nombre de lignes, sauf
quand le passage sous une largeur de mot fait tomber une ligne entiere, ce qui n'arrive que sur
"Soustractions CP" et "Compare les nombres" a `1rem`). Aucun des deficits de 41, 131 et 215 px ne
peut se resorber par une baisse de police supplementaire sans passer sous un seuil illisible bien
avant d'avoir comble l'ecart.

## 5. Ce qui fermerait vraiment "Le bon verbe" et "Petits problemes"

Ni le plancher ni le resserrement, seuls ou cumules, ne les ferment - -215 px et -131 px au mieux.
Trois leviers restent hors du ressort d'`ines-design`, deja nommes au tour precedent et confirmes
par cette mesure plus severe :

1. **La longueur des enonces et des indices de CM1**, `nadia-prof`/`camille-po` : sur l'item le
   plus charge, l'indice a lui seul (`"Le mot 'autrefois' annonce l'imparfait, jamais le futur :
   avec 'ils' ou 'elles', le verbe 'aimer' devient 'aimaient', pas 'aimeront'."`, 135 caracteres)
   occupe une part significative des 321 px de deficit. Reformuler les indices les plus longs de
   `cm1-conjugaison-present.yaml` (contenu, pas forme) romprait le seuil qu'aucune reduction de
   police ne peut atteindre sans devenir illisible.
2. **Le repli a deux rangees de "Le bon verbe" a quatre propositions**, deja nomme par
   `theo-archi`/`ADR-0033` (84 px par repli) : une disposition a une seule rangee sur ce jeu, si
   elle existe, romprait un cout que ce levier ne peut pas toucher (c'est une saisine
   `camille-po`/`theo-archi`, pas un choix de forme).
3. **Sur "Petits problemes", la longueur des enonces de probleme** ("Le jardinier a 6 fleurs et en
   plante 2 de plus. Combien en a-t-il ?", 66 caracteres) pese directement sur la hauteur au-dessus
   du cabinet, hors de portee d'un rembourrage ou d'une police plus petite du bloc de message.

## Ce que je remonte

- **A l'utilisatrice** : le plancher chiffre exactement ce qu'elle demandait, y compris sur l'etat
  de reussite ou il ne peut rien (confirme, section 3). Il n'est ni inutile (il est necessaire pour
  fermer deux jeux sur sept) ni suffisant pour les deux jeux qui inquietaient, et un troisieme jeu
  ("Les complements a 10") ne ferme pas non plus une fois cherche a l'extreme, alors qu'il semblait
  clos avant que la mecanique existe. Le decoupage "quatre jeux regles par le resserrement seul,
  plancher inutile pour eux" ne correspond pas a cette mesure : je le dis pour ce que c'est, pas
  pour confirmer une hypothese qui ne tient pas.
- **A `nadia-prof`** : entailler `.indice` en dessous de `.consigne` demande de sortir ces deux
  selecteurs de `aucunTexteNePasseSousLaTailleDeLaConsigne` et de reecrire
  `lIndiceEstAuMoinsAussiGrandQueLaConsigne` - un choix qui lui revient, pas un simple ajustement
  de valeur. Et `.explication` ne sert a rien sur "Le bon verbe"/"Petits problemes" (jamais rendu) :
  si elle veut prevenir un cout sur "Le bon son" (le seul jeu ou `.explication` s'affiche), c'est
  la seule ou son accord compte.
- **A `sofia-sm`** : rien de neuf sur le fond technique, cette mesure ne fait que chiffrer une
  feuille de style hypothetique, jamais commitee.

Je ne modifie ni `recreo.css` ni les gabarits sur disque. Je ne fusionne pas, je ne pousse pas.
