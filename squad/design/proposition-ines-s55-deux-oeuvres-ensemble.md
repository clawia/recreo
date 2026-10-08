# Proposition de mise en page - le dispositif "deux oeuvres ensemble" d'ART-CP-10 (s55, avant T3)

- Auteure : ines-design
- Date : 2026-10-08
- Branche : `s55-ines-page`, issue de `round-two` a `2d0d10e7`
- Demande : `planning.md` de s55 (poste "Mise en page d'`ART-CP-10`", porte `G-INES`), `DEC-0115` 5.4 point 2
  et amendement A.4 ; `ADR-0104` decisions 4.3 et 4.4 ; `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`,
  section 3 (`M1` a `M6`, `X1` a `X7`, regle 4 de 3.4)
- Fichiers de cette proposition : ce document, `prototype-ines-s55-deux-oeuvres-ensemble.html`,
  `mesure-ines-s55-deux-oeuvres-ensemble.html`, `releves-ines-s55-deux-oeuvres-dix-largeurs.json`

## 0. Ce que ce document est, et ce qu'il n'est pas

Une proposition de forme. Aucun gabarit, aucune feuille de style, aucun controleur n'est modifie par ce
commit. Les regles candidates vivent dans le `<style>` du prototype, apres les deux feuilles reelles
(ordre d'`ADR-0014`), comme elles s'appliqueraient si elles etaient versees. Elle ne prononce aucun Done
pedagogique et n'ecrit aucun mot lu par l'enfant : les deux phrases que le prototype affiche (`M4`, `M5`)
sont celles de `nadia-prof`, recopiees. Les phrases entre crochets du prototype sont des marqueurs de
longueur. Le dev de T3 applique, il ne dessine pas.

## 1. Reponses

| Question du planning | Reponse |
|---|---|
| La reference et trois propositions tiennent-elles sans defilement ? | **Pas a 551 px de haut en disposition empilee, a aucune des dix largeurs** (606 px au mieux, manche fraiche). **Oui en disposition "cote" des 720 px pour la manche fraiche** (411 a 514 px selon le meuble et la largeur), et pour la bonne reponse sauf avec meuble a 992 px et plus (578). **Non sous 720 px** (614 a 710 px a 360 et 390). A 1024 px de haut, tout tient. Tableaux en section 3 |
| Forme de la reference hors du choix (`M2`) | Image nue, **sans cadre, hors du meuble et hors de la rangee**, non focalisable : celle que `.illustration` rend deja, inchangee. Ce qui se presse a un cadre plein de 2 px ; ce qui ne se presse pas n'en a aucun. Section 2.2 |
| Marque de la bonne oeuvre a la revelation (`M4`) | **Double cadre** hors de la case : un anneau plein de 4 px, separe de la case par un jour de 4 px. Blanc dans le meuble, encre sans meuble. Le sens est porte par la forme, pas par la couleur. Section 2.4 |
| Faut-il passer a deux propositions si trois ne tiennent pas (regle 4 de 3.4) ? | **Non, ca ne rend pas de hauteur** : a deux, les cases grandissent et la manche est plus haute (706 px contre 652 a 360 px, sans meuble). A 744 px et plus, les deux chiffres sont egaux. Le repli de la regle 4 ne sert pas le pli |

Ce qui ne tient pas, dit sans detour : a **360 px** aucune disposition essayee ne tient a 551 ni a 640 px
(`DEC-0040` a sorti le telephone du perimetre ; le chiffre est la pour qu'il soit sur la table). Et
a **551 px de haut**, l'etat d'echec et la revelation depassent en disposition "cote" de 12 a 115 px selon
le cas (section 3.3).

## 2. La mise en page proposee

### 2.1 Deux dispositions, une seule structure de gabarit

La structure du gabarit ne change pas d'une disposition a l'autre : `.illustration` (la reference), puis
`.enonce`, puis la rangee de trois boutons-images dans `form.propositions`. Seule une classe sur
`.ecran-de-jeu` (posee par le gabarit sous la condition `propositionsEnImage` d'`ADR-0104` 3) bascule
d'une disposition a l'autre. L'ordre du document est celui d'un lecteur d'ecran : reference, enonce,
propositions.

- **Empilee (sous 720 px)** : reference centree au-dessus, enonce, trois cases en une rangee.
- **"Cote" (720 px et plus)** : la reference occupe une colonne de 11 rem a gauche, sur la hauteur de
  l'enonce et du meuble, qui sont dans la colonne de droite. Une grille CSS sur `.ecran-de-jeu` suffit
  (aucun element de gabarit en plus). L'enfant voit l'oeuvre de reference **a cote** de celles qu'il
  compare, sans que l'oeil monte et descende (`M4` : "la reference toujours visible a cote").

### 2.2 La reference (`M2`) et les cases (`M1`, `X6`)

- **Reference** : `.illustration` et `.image-item` inchanges (plafond 11 rem, soit 176 px, aucune
  bordure, aucune couleur, `ADR-0073`). Elle est la plus grande image de l'ecran a toute largeur (176 px
  de haut contre 132 a 176 px pour une proposition) : ce que l'enfant doit reconnaitre en premier n'est
  jamais plus petit que ce qu'il presse.
- **Cases** : trois cases **carrees, egales**, une reproduction par case, centree, entiere
  (`object-fit: contain`, jamais rognee). Cadre plein de 2 px en `--recreo-accent`, fond
  `--recreo-fond` identique pour les trois, rembourrage de 4 px, jour de 12 px entre cases. Les quatre
  teintes cycliques des boutons-texte sont neutralisees : une teinte qui change de position en position
  est une difference de presentation avant le geste (`X6`) et un aplat derriere une reproduction (`X3`).
  Le voile glissant de l'arrivee (`::after`) est coupe : il assombrirait la reproduction.
- **Ce qu'une case egale coute** : une reproduction large dans une case carree se rend plus petite qu'une
  reproduction haute. A 744 px en disposition "cote", les trois cases font 144 x 144 px (avec meuble) ou 149 x 149 (sans) et les
  images rendues 99 x 132 (portrait 264 x 352), mais **132 x 89** (paysage 352 x 237) et **132 x 67**
  (352 x 180).
  `M1` dit "a la meme taille rendue" : la case est egale, l'image ne l'est pas. Question 1 a
  `nadia-prof`.
- **Contrastes** (luminance relative WCAG, calcul reproductible) : cadre `--recreo-accent` sur fond de
  page 5,03:1, sur le meuble 3,20:1 (plancher de 3:1 d'un composant non textuel, tenu de peu) ; case sur
  meuble 16,11:1 ; anneau blanc sur meuble 17,32:1 ; anneau encre sur fond de page 14,09:1.
- **Etat presse** : fond `--recreo-accent` sous le doigt, comme les boutons-texte. Le survol et le focus
  clavier gardent l'anneau de focus de la feuille tierce.

### 2.3 L'etat d'echec (`M3`)

Un mot barre n'existe pas sur une image. Proposition : sur `.deja-essayee`, **cadre en tirets** (meme
epaisseur, rien ne bouge) et reproduction attenuee a 50 %. Le canal est la forme du cadre, pas la
couleur. Aucun pictogramme, aucun texte dans la case (`X3`). La reference ne change pas.
Question 2 a `nadia-prof` : une reproduction attenuee se voit-elle encore assez ?

### 2.4 La revelation (`M4`, `M6`)

- La bonne oeuvre porte `outline: 4px solid` avec `outline-offset: 4px` : un double cadre **hors de la
  case**, pas dedans (`X3`). Mesure : 8 px au dela du bord de la case, 12 px de jour vers la case
  voisine, donc 4 px de jour restant, a toutes les largeurs et dans les deux variantes. L'anneau est
  blanc dans le meuble, `--recreo-texte` sans meuble (un blanc sur fond de page ne se verrait pas).
- Le focus clavier arrive deja sur cette case (`data-focus-cible`, `M13`), ce qui designe aussi la case
  a un lecteur d'ecran sans attribut de plus.
- La phrase de `M4` reprend `.reponse-revelee` (1,25 rem, aucune taille neuve), sans `.reponse-attendue`.
  Variante "grande" mesuree (2 rem gras, la taille de l'ancienne reponse en texte) : **+18 px a 744 px,
  +84 px a 360 px**. Je ne la propose pas : ce que l'enfant doit lire en premier ici est l'anneau.
- `M6` (explication) garde sa place et sa forme.

### 2.5 Le meuble (cabinet) : deux variantes mesurees, je n'en tranche aucune

Le gabarit entoure aujourd'hui les propositions du meuble du bandit manchot (`ADR-0030`, bandeau,
levier, socle). Ce meuble coute **64 px** de haut a toute largeur (bandeau 32, socle 16, cadre 16) et
fait le fond sombre sur lequel les cases se detachent. Sans meuble, les cases sont sur le fond de page.
Les deux sont mesurees partout. Retirer le meuble pour ce type d'item est un choix qui touche
`ADR-0030` et ses conditions `C1` a `C6` : a `theo-archi` et a `nadia-prof`, pas a moi.
Sur le seul critere du pli, **sans meuble** est la seule variante qui tient la manche fraiche, la bonne
reponse et l'echec a 744 x 551.

## 3. La mesure de pli

### 3.1 Methode

`mesure-ines-s55-deux-oeuvres-ensemble.html` ouvre `prototype-ines-s55-deux-oeuvres-ensemble.html` dans
une iframe de **largeur exacte** par cas (les dix largeurs de
`releves-ines-s26-ecran-voix-dix-largeurs.json` : 360, 390, 576, 640, 720, 744, 768, 992, 1200, 1440), dans
Chrome 154.0.8037.98 sur profil isole jetable, `--headless=new`. Une iframe contourne le plancher de
fenetre de Chrome headless (`ADR-0025`) ; une capture `--window-size=360,...` ne rend pas du 360 px.
L'iframe fait 1600 px de haut pour qu'aucune barre de defilement ne retrecisse la largeur. Pour chaque
cas, `getBoundingClientRect()` donne le **bas du dernier element visible** de l'etat (hors levier du
meuble, qui deborde sur le flanc) : `basContenu`. Le pli se lit en le comparant a la hauteur utile :
**551 px** (`DEC-0040`, second amendement : 744 x 551, le pire cas de l'ordinateur), **640 px**
(l'ancienne reference de telephone) et **1024 px** (la tablette la plus basse du catalogue).
Les 240 cas (4 etats, 2 meubles, 2 dispositions, 3 ou 2 propositions, 2 tailles de phrase) sont dans le
releve. Les marqueurs de longueur donnent deux lignes a 360 px a l'enonce (96 px), a l'indice et a
l'explication : chaque ligne de plus coute 30 px.

Controles faits sur le releve : aucun debordement lateral, aucun defilement horizontal, ecart lateral
zone de jeu / en-tete nul a toute largeur et dans tous les etats (le defaut de s05 ne se reproduit pas).

### 3.2 Bas du contenu, en px, trois propositions (fraiche / juste / echec / revelation)

Empilee :

| Largeur | Avec meuble | Sans meuble |
|---|---|---|
| 360 | 711 / 775 / 859 / 923 | 652 / 716 / 800 / 864 |
| 390 | 673 / 737 / 821 / 885 | 614 / 678 / 762 / 826 |
| 576 et 640 | 665 / 729 / 783 / 817 | 606 / 670 / 724 / 758 |
| 720 a 1440 | 702 / 766 / 820 / 854 | 638 / 702 / 756 / 790 |

"Cote" (720 px et plus ; sous 720 px la disposition reste empilee) :

| Largeur | Avec meuble | Sans meuble |
|---|---|---|
| 720, 744, 768 | 470 / 534 / 588 / 622 | 411 / 475 / 529 / 563 |
| 992, 1200, 1440 | 514 / 578 / 632 / 666 | 450 / 514 / 568 / 602 |

### 3.3 Lecture contre les trois hauteurs

| Hauteur utile | Ce qui tient |
|---|---|
| 1024 (tablette) | Tout, aux dix largeurs, dans les deux dispositions et les deux meubles (maximum 923) |
| 640 | Empilee : rien a 360 et 390 ; a 576 et plus, la manche fraiche seule, sans meuble (606 a 638). "Cote" : tout tient a 744 (622 au plus) ; a 992 et plus, tout tient sans meuble (602), et avec meuble sauf la revelation (666) |
| 551 | Empilee : rien, a aucune largeur. "Cote" sans meuble a 744 : fraiche (411), juste (475), echec (529) tiennent, la revelation depasse de **12 px** (563). "Cote" sans meuble a 992 et plus : fraiche et juste tiennent, l'echec depasse de 17 px, la revelation de 51. "Cote" avec meuble : la fraiche tient ; la bonne reponse tient a 744 (534) et depasse de 27 px a 992 (578) ; l'echec depasse de 37 px (744) ou 81 (992), la revelation de 71 ou 115 |

Deux faits utiles pour juger l'exces. D'abord, ce qui depasse en disposition "cote" est la queue du bloc
de message (derniere ligne de l'explication ou de l'indice) : l'anneau, les trois cases et la reference
sont tous entre 186 et 411 px, donc visibles meme si l'ecran defile de 70 px. Ensuite, a 992 px et plus,
les cases montent a 188 px (le plafond de 176 px de l'image, plus son cadre) et la rangee est environ 40 px
plus haute qu'a 744 : plafonner la case vers 149 px ramenerait la hauteur vers celle de 744, a quelques
pixels pres (non mesure). C'est un plafond d'image a 132 px que `nadia-prof` doit accepter ou refuser
(question 3).

### 3.4 Ce que les images rendues donnent

| Largeur / disposition | Plus grand cote d'une proposition | Reproduction la plus large (352 x 180) |
|---|---|---|
| 360, empilee, avec meuble | 79 px | 40 px de haut |
| 360, empilee, sans meuble | 84 px | 43 px |
| 576 a 640, empilee | 139 a 144 px | 71 a 74 px |
| 720 a 768, "cote" | 132 px (avec meuble), 137 px (sans) | 67 a 70 px |
| 992 et plus | 176 px (plafond d'`ADR-0104` 4.3) | 90 px |

Le plafond d'`ADR-0104` 4.3 (176 px) n'est atteint qu'a 992 px et plus, ou en empile des 720 px. Que la
touche, l'eau ou la lumiere se voient a 132 px, et a 84 px a 360, je ne le juge pas : c'est la regle 6
de 3.4, a l'ecran, a l'audit.

## 4. Les regles candidates, resumees

Posees par le gabarit quand `propositionsEnImage` est vrai ; ecrites en entier dans le prototype.

| Classe | Porte par | Role |
|---|---|---|
| `propositions-oeuvres` | `form.propositions` | rangee sans retour a la ligne, jour de 12 px |
| `proposition-oeuvre` | chaque `button.proposition` | case carree egale, cadre 2 px, fond unique, teintes cycliques et voile coupes |
| `oeuvre-revelee` | la case de la bonne oeuvre, **a la revelation seulement** | double cadre (section 2.4) |
| `deja-essayee` (existante) | les cases pressees | cadre en tirets, image a 50 % |
| `ecran-deux-oeuvres` (nom propose) | `.ecran-de-jeu` | disposition "cote" des 720 px |

## 5. Texte accessible

- **Nom de chaque bouton** (`M5`, `ADR-0104` 4.4 clause 2) : `aria-label` portant le texte de `nadia-prof`
  ("Choisir cette œuvre"), **identique sur les trois boutons et sur tous les items**, jamais une donnee de
  contenu. L'`<img>` du bouton porte `alt=""` : son nom est celui du bouton, et un `alt` serait un texte de
  contenu (`X1`). Aucun `title`. Le prototype le fait ainsi.
- **Trois noms identiques** : un lecteur d'ecran annonce trois fois la meme chose. La facon de les
  distinguer **sans ecrire un mot de plus** est de mettre les trois boutons dans une liste (`ul` / `li`),
  dont la structure annonce "element 1 sur 3". Cela change la structure du formulaire (les boutons ne
  sont plus enfants directs de `.propositions`) et les selecteurs de la feuille : a `theo-archi`, avec
  son prix. Le rang dans le nom accessible, que `nadia-prof` autorise, ecrirait des mots : a elle.
- **Reference** : `<img class="image-item">` avec `alt` pris du modele (`illustrationTexteAlternatif`,
  `ADR-0073` clause 5), mots de `nadia-prof` (`CON-5` : un texte qui ne nomme rien).
- **Nom du groupe des trois boutons** : aucun aujourd'hui sur `form.propositions`. S'il en faut un, c'est
  une phrase de plus : a `nadia-prof`.
- **Revelation** : la phrase de `M4` est dans `.message-jeu role="status"`, donc annoncee ; le focus arrive
  ensuite sur la bonne case.
- **Ecran de fin** (`X7`) : aucun element neuf, `reponseAttendueVisible` reste faux (`ADR-0104` 4.5).
  Il n'y a rien a mesurer.

## 6. Ce que le rendu exige du controleur, et que je ne fais pas

Remonte a `sofia-sm` (je ne touche ni `PartieController` ni son contrat) :

1. **Un drapeau de revelation distinct.** `ADR-0104` 4.4 dit que `reponseRevelee` n'est pas pose en texte
   quand `propositionsEnImage` est vrai. Or `focusCibles` vaut la derniere proposition pressee sur un echec
   et la bonne sur une revelation (`PartieController`, lignes 463 a 473) : sans drapeau, le gabarit ne sait
   pas s'il doit poser `oeuvre-revelee` ou non. Il lui faut un booleen de manche revelee (la saisie en
   porte deja un, `mancheResolue`, ligne 532, la manche non).
2. `propositionsEnImage`, deja decide par `ADR-0104` 3, sert aussi a poser `ecran-deux-oeuvres`.

## 7. Garde-fous Java que cette proposition touche, non lances

Je n'ai lance ni `mvn` ni aucun garde-fou : aucun fichier de production n'est modifie. Lecture seule, a
verifier par T3 : `HabillageDesEcransTest` rougit sur une classe qu'aucune feuille ne nomme (quatre
classes neuves ici, `ADR-0104` decision 7 ligne 7) ; la derivation d'`ADR-0046` demande ses propres
etats a un sujet compose (`.proposition.proposition-oeuvre` ne declare que `:active` dans le prototype) ;
`TailleDesTextesEtBoutonsTest` lit la cible tactile et la taille de police de `.proposition` (la case
fait 90 px au plus bas, aucune taille de police n'est declaree ici).

## 8. Ce qui reste a juger par nadia-prof

1. **Case egale ou image egale (`M1`).** Une reproduction large rend plus petite qu'une reproduction
   haute dans une case carree (section 2.2). Est-ce "la meme taille rendue" ? Sinon, le choix est entre
   des cases de meme hauteur et de largeur variable, ou un recadrage (interdit par `X5` pour la
   reference, a dire pour les propositions).
2. **L'attenuation de l'echec** (section 2.3) : le tiret et les 50 % laissent-ils la propriete visible ?
3. **132 a 137 px de plus grand cote a 744 px** (144 a 149 px de case), 84 px a 360 px : la propriete se voit-elle ?
   Sinon, c'est elle qui rejuge la hauteur (regle 4 de 3.4), et la section 3 donne le prix.
4. **Le pli de l'echec et de la revelation a 551 px** (section 3.3) : `M1` ne parle que de l'ecran avant
   tout geste. Le depassement de l'echec et de la revelation est-il recevable ?
5. **Le meuble** (section 2.5) : conserve ou retire pour ce type d'item. Avec `theo-archi`.
6. **Un fond clair identique derriere chaque reproduction** (`C5` clause 1, lecture du 2026-10-06) :
   c'est un passe-partout, pas un decor, mais c'est un aplat derriere une image.
7. **La disposition "cote"** : l'enfant lit la reference a gauche et choisit a droite. Le rangement
   change-t-il la facon de rapprocher ?
8. **Le rang annonce par une liste** (section 5), les mots d'un nom de groupe, et la taille de la phrase
   de revelation.

## 9. Ce qui n'est pas mesure

- **Le jar.** Aucun paquet a reproductions n'est au catalogue (`T5` n'existe pas), donc aucun etat de ce
  dispositif ne peut etre rendu par l'application. Le releve est celui d'un prototype qui charge les
  deux feuilles reelles et reprend la structure de `fragments/manche.html` ; il est a refaire sur le
  jar quand T3 et T5 existent.
- Aucun appareil reel, aucun doigt : la cible tactile (90 a 188 px) est calculee, pas essayee.
- Les couleurs ont ete lues par le calcul de contraste, pas par un simulateur de daltonisme : le sens
  d'aucune marque ne repose sur la teinte (cadre plein ou en tirets, double cadre).
- Une paire d'oeuvres reelle : le prototype prend trois reproductions deja servies pour leur rapport
  largeur / hauteur, sans proposer de paire.
