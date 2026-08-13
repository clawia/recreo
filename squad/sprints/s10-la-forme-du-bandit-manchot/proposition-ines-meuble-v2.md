# Proposition ines-design v2 - reponse a l'arbitrage de forme F1-F12 de nadia-prof

Repond au refus mesure de `nadia-prof` (branche `s10-nadia-forme`, tete `353d53f`,
`FICHE-bandit-manchot-additions-cp.md`) sur `proposition-ines-meuble.md`. Quatre motifs, chacun
mesure : le visage ne se lit pas comme un visage, le levier doit sortir de la paroi et sa teinte est
trop importante, deux teintes de boutons sont confondues avec les couleurs du verdict, l'enseigne a
0,875 rem serait le plus petit texte de l'application. Je redessine les quatre. Je ne traite pas
l'emplacement de l'enseigne (F11, F12, la reserve H sur `ADR-0035`) : c'est bloque en attente d'une
ecoute reelle de lecteur d'ecran, et ce n'est pas mon geste a faire.

## Lignee et methode

Worktree `s10-ines-meuble`, toujours sur `round-two` a `b15bda1`, verifie par
`bash outils/verifier-lignee.sh`. Aucune ligne de `recreo.css` ni de gabarit modifiee. Mesure sur
le fragment **reellement servi** par l'application (fat jar local, JDK 21 le temps de la commande,
le `java` du `PATH` de ce poste est un JRE 8), l'item exact que `nadia-prof` cite -
*"Vous ___ du chocolat."*, propositions `mangent`/`mangeons`/`mangez`/`mange` - reassemble avec les
deux feuilles reellement servies plus ma feuille candidate, capture par un equivalent direct de
`outils/mesurer-rendu.sh` (meme gabarit de conteneur, meme invocation Chrome, ecrit en Python natif
pour eviter la conversion de chemin `file://` que `outils/mesurer-rendu.sh` ne fait que sur son
propre fichier temporaire - le meme defaut que celui deja remonte sur un chemin de PNG relatif,
touche aussi une URL fournie sous forme POSIX). Mesure pixel par pixel par balayage de couleur
exacte (Pillow), jamais a l'oeil seul - je regarde aussi l'agrandissement, comme `nadia-prof`, mais
je ne m'arrete pas la.

## 1. Le visage (F4) - redessine, contenu, mesure

### Ce qui a change

```css
.cabinet-visage {
    position: relative;
    width: 1.375rem;
    height: 1.125rem;
    flex-shrink: 0;
}

.cabinet-visage::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 0.5rem;
    height: 0.5rem;
    border-radius: 50%;
    background-color: var(--recreo-accent-texte);
    box-shadow:
        0.75rem 0 0 0 var(--recreo-accent-texte),
        0 0.5rem 0 -0.0625rem #f7a8b8,
        0.625rem 0.5rem 0 -0.0625rem #f7a8b8;
}

.cabinet-visage::after {
    content: "";
    position: absolute;
    top: 0.6875rem;
    left: 0.3125rem;
    width: 0.75rem;
    height: 0.375rem;
    border-bottom: 0.1875rem solid var(--recreo-accent-texte);
    border-radius: 0 0 50% 50%;
}
```

**Les trois defauts mesures par `nadia-prof`, corriges un par un :**

1. **"La joue fait 9 px avec son spread quand l'oeil en fait 5 - la joue est plus grosse que
   l'oeil."** Desormais l'oeil est le cercle de base, `0,5rem` (8 px), `spread: 0`. La joue est une
   copie du **meme cercle de base** avec un `spread` **negatif** de `-0,0625rem` (-1 px de chaque
   cote), donc **6 px** de diametre. L'oeil est strictement plus grand que la joue, par
   construction et non par reglage a l'oeil.
2. **"La bouche est un trait d'1,5 px."** L'epaisseur passe de `0,09375rem` a `0,1875rem` (3 px) -
   le double - et la largeur de l'arc passe de `0,5625rem` a `0,75rem` (12 px) pour rester
   proportionnee a des yeux plus grands.
3. **"La copie d'ombre droite sort de 7 px hors de la boite declaree."** La boite passe de
   `0,9375rem` a `1,375rem` de large (22 px) et de rien-de-declare a `1,125rem` de haut (18 px).
   J'ai recalcule chaque decalage pour que **la copie la plus a droite (le second oeil, decalage
   `0,75rem` + largeur `0,5rem`) s'arrete a 20 px, dans une boite de 22 px** - 2 px de marge, jamais
   un debordement.

### Preuve de contenance, mesuree et non relue sur la regle

Test isole (yeux et bouche colores, boite cerclee d'un contour jaune de reperage, jamais commite),
capture Chrome reelle, balayage de couleur exacte :

| | Mesure |
|---|---|
| Boite declaree (contour) | `x = 11..34`, `y = 6..25` |
| Yeux + bouche (blanc) | `x = 13..30`, `y = 8..25` |
| Joues (rose) | `x = 14..27`, `y = 17..20` |

**Toutes les copies restent strictement a l'interieur de la boite declaree**, sur les quatre bords.
Le point le plus proche d'un bord est le bas de la bouche, qui **touche** `y = 25` sans le
depasser - marge nulle mais jamais negative, verifiee au pixel.

**Confirme sur le rendu reel de l'application**, fragment servi de "Le bon verbe" reassemble avec
l'enseigne "Le Copain Malin" a cote : le visage se lit comme deux yeux, deux joues roses plus
petites, un sourire net, sans agrandissement necessaire. Le `gap` de `0,5rem` vers l'enseigne reste
entier - aucune copie ne mord dessus, `nadia-prof` peut le rejuger sur la meme capture.

**Aucun marqueur de genre** : ni cils, ni noeud, ni cheveu, ni sourcil, ni bouche coloree - je
reconduis cette regle de la version refusee, elle n'a pas ete critiquee et je ne la retouche pas.

## 2. Le levier (F1, F2, F3) - suspendu hors de la paroi, teinte acier

### Ce qui change de fond en comble

La bordure du `.cabinet` **redevient uniforme a 0,5rem sur ses quatre cotes** - j'abandonne
entierement l'elargissement symetrique de ma proposition precedente, qui est la cause du defaut
mesure sur `mangeons` (F5). Le levier n'habite plus une paroi elargie : il est **suspendu dans le
rembourrage du `.container`**, exactement la route que `nadia-prof` a mesuree elle-meme comme
gratuite.

```css
.cabinet {
    position: relative;
    /* bordure : aucune regle neuve ici, elle reste 0,5rem, heritee du raccourci "border" existant */
}

.cabinet-levier {
    position: absolute;
    top: 50%;
    right: -1.125rem;
    width: 1.125rem;
    height: 5rem;
    transform: translateY(-50%);
}

.cabinet-levier::before {
    content: "";
    position: absolute;
    left: 50%;
    top: 0.75rem;
    bottom: 0.5rem;
    width: 0.5rem;
    transform: translateX(-50%);
    background-color: var(--recreo-levier-teinte);
    border-radius: 999px;
}

.cabinet-levier::after {
    content: "";
    position: absolute;
    left: 50%;
    top: 0;
    width: 1.125rem;
    height: 1.125rem;
    transform: translateX(-50%);
    background-color: var(--recreo-levier-teinte);
    border-radius: 50%;
    box-shadow: inset 0 -0.1875rem 0 rgba(0, 0, 0, 0.18);
}
```

`:root { --recreo-levier-teinte: #8e8e8e; }` - la teinte "acier" que `nadia-prof` nomme, choisie et
non devinee (section 2.c).

### F1 - la silhouette : fut vertical, boule au sommet, embase au flanc

Meme geometrie que la piste que `nadia-prof` lit comme "poignee" dans son propre rendu (boule
18 px sur fut 8 px, hors de la paroi) : fut vertical (`0,5rem`, 8 px), boule au sommet (`1,125rem`,
18 px), toujours en position de repos - aucune classe, aucune transition, jamais de mi-course.
`.cabinet-levier` est un frere de `.propositions`, jamais son ancetre.

**Mesure sur le rendu reel** ("Le bon verbe", item `mangeons`), balayage de la teinte exacte :

| | Mesure |
|---|---|
| Bordure du cabinet (couleur exacte) | `x = 12..347`, inchangee sur les quatre cotes |
| Colonne (`mangeons`) | boite mesuree a **152 px**, inchangee |
| Boule du levier | `x = 341..357` a mi-hauteur, **17 px** mesures pour 18 px declares (antialiasing du bord, meme ecart que celui deja constate par `ines-design` sur le manche de fronton) |
| Recouvrement avec une proposition | **0 px** - le pixel le plus a droite d'un bouton (`x = 339`) et le pixel le plus a gauche du levier (`x = 341`) sont separes par la bordure du cabinet |

**Agrandi x6** (capture jointe a la mesure) : la boule se voit nettement suspendue au-dela du mur,
sur le fond clair de la page, le fut descendant le long de la bordure - je lis un objet accroche a
l'exterieur du meuble, pas une forme plantee dans son epaisseur.

### F2 - la teinte : plus discrete qu'un bouton de reponse, et je dis contre quelles surfaces

**Ce que le levier touche reellement, mesure et non suppose.** Sur le rendu, la teinte du levier
n'apparait jamais sur la couleur du corps du cabinet (`#081c2c`) : sa geometrie (embase dans
l'epaisseur de la bordure, boule au-dela) le fait toujours peindre soit sur la **bordure**
(`--recreo-entete-fond`, `#1d4b6b`), soit sur le **fond de page** (`--recreo-fond`, `#f4f7fb`),
jamais sur le remplissage sombre de l'interieur. Je le donne comme fait mesure sur mon propre rendu,
pas comme argument de principe.

| Surface | Contraste de `#8e8e8e` |
|---|---|
| Bordure du cabinet `#1d4b6b` | **2,82:1** |
| Fond de page `#f4f7fb` | **3,05:1** |
| Corps du cabinet `#081c2c` (jamais touche par cette geometrie, donne pour verification) | 5,29:1 |

Les deux surfaces reellement croisees restent **sous 3,20:1**, la reference que `nadia-prof` pose
(contraste d'un bouton de reponse contre le corps du cabinet). Le levier reste donc moins voyant
que n'importe quel bouton de reponse, sur les deux surfaces ou il se trouve reellement.

**Ce que je nomme en retour, honnetement** : si une implementation future positionnait le levier
autrement, au point de le faire peindre sur le remplissage sombre de l'interieur, cette teinte-la
(`5,29:1`) ne tiendrait plus la reference de `nadia-prof`. La contrainte de forme (l'embase ne mord
jamais sur le remplissage, seulement sur la bordure et au-dela) est donc aussi dure que la
contrainte de couleur - je le dis pour qu'un futur retouchage ne deplace pas l'un sans l'autre.

### F3 - aucune faute fabriquee

`.cabinet-levier` est un `<div aria-hidden="true">` simple : jamais un `button` ni un `a`, aucun
`data-role`, hors du parcours clavier par construction (aucun `tabindex`), aucune regle `:hover`,
`:active` ni `:focus` declaree, aucun texte de l'ecran n'y renvoie. Zero pixel de recouvrement avec
une proposition, mesure ci-dessus.

## 3. L'enseigne (F7, F8) - agrandie, jamais tronquee ; l'emplacement reste hors de mon geste

```css
.cabinet-enseigne {
    font-size: 1.25rem;
    font-weight: bold;
    color: var(--recreo-accent-texte);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
```

**F7** : le texte est **"Le Copain Malin"**, mot pour mot, verifie sur mon fragment de mesure.

**F8** : la taille passe de `0,875rem` a `1,25rem` - le plancher que `nadia-prof` mesure comme celui
de toute l'application, jamais en dessous. Verifie sur le rendu reel, a cote du visage v2 : le nom
tient entierement sur une seule ligne, sans ellipse engagee, avec une marge visible avant le bord du
fronton. Je garde `overflow: hidden` et `text-overflow: ellipsis` comme filet - la propriete exigee
est que l'ellipse **ne se declenche jamais**, pas que la regle soit absente, exactement comme
`nadia-prof` l'ecrit ("je n'exige pas que la declaration change, j'exige la propriete, mesuree").

**Ce que je ne fais pas ce tour-ci, et je le dis pour ne pas laisser croire le contraire.**
L'emplacement du nom - dans quel sous-arbre, masque ou non, annonce ou non a un lecteur d'ecran -
reste bloque par F11, F12 et la reserve H sur `ADR-0035`. Je dessine la bande et son texte parce que
la forme visuelle est mon domaine ; je ne fais pas parler l'arbre d'accessibilite a sa place, parce
que ce n'est pas mesurable de mon poste et que `nadia-prof` a ecrit qu'aucun Done ne sera prononce
sur ce point avant une ecoute reelle. Si l'ecoute conclut que la structure doit changer, la classe et
la taille ci-dessus restent valables quel que soit le sous-arbre qui les porte.

## 4. Les boutons colores (F9, F10) - repris entierement, texte sombre plutot que blanc

### Pourquoi je ne peux pas garder le texte blanc, et je le prouve plutot que de l'affirmer

`nadia-prof` mesure ses planchers contre un texte blanc (F10, "contre le blanc de son texte"). J'ai
cherche une teinte qui tienne **a la fois** F9 (>= 3:1 avec `#2e7d32` et `#c62828`), le >= 3:1 contre
le corps `#081c2c`, et le >= 4,5:1 contre un texte blanc. **Il n'en existe aucune, et voici la
preuve, en luminances relatives (methode WCAG deja partagee) :**

- Texte blanc a 4,5:1 impose une teinte de bouton assez sombre : `L <= 0,1833`.
- Distinguer `#2e7d32` (`L = 0,1548`) et `#c62828` (`L = 0,1368`) a 3:1, avec une teinte **plus
  sombre** qu'eux (la seule case compatible avec `L <= 0,1833`, l'autre case exigeant `L >= 0,56`)
  impose `L <= 0,0123` - un bouton pratiquement noir.
- Mais `#081c2c` (`L = 0,0106`) est **encore plus sombre**. Tenir 3:1 contre lui avec une teinte
  plus claire que lui exige `L >= 0,1318` ; avec une teinte plus sombre, `L` devrait etre negative,
  ce qui n'existe pas.

**`0,0123` et `0,1318` ne se recouvrent jamais : aucune teinte reelle ne peut satisfaire F9, F10 et
un texte blanc en meme temps.** Ce n'est pas une teinte que j'ai mal choisie, c'est le corps en deux
tons - deja accepte, et la meilleure part de cette proposition selon `nadia-prof` elle-meme - qui
ferme cette porte en assombrissant le fond derriere les boutons.

**Ma reponse : des teintes claires, avec un texte sombre plutot que blanc.** Un bouton clair peut
tenir 3:1 contre les deux couleurs de verdict (`L >= 0,5644`) tout en restant tres au-dessus de 3:1
contre le corps sombre, et un texte a `--recreo-texte` (`#1c2733`) y reste largement lisible.

```css
.propositions .proposition:nth-child(4n+1) { background-color: #bdd2ef; color: var(--recreo-texte); }
.propositions .proposition:nth-child(4n+2) { background-color: #e7cb7f; color: var(--recreo-texte); }
.propositions .proposition:nth-child(4n+3) { background-color: #dcc8ed; color: var(--recreo-texte); }
.propositions .proposition:nth-child(4n+4) { background-color: #eec1d8; color: var(--recreo-texte); }
```

| Teinte | vs `#2e7d32` | vs `#c62828` | vs corps `#081c2c` | vs texte sombre `#1c2733` |
|---|---|---|---|---|
| bleu `#bdd2ef` | 3,33:1 | 3,65:1 | 11,24:1 | 9,82:1 |
| jaune `#e7cb7f` | 3,23:1 | 3,55:1 | 10,92:1 | 9,55:1 |
| lavande `#dcc8ed` | 3,30:1 | 3,62:1 | 11,15:1 | 9,75:1 |
| rose `#eec1d8` | 3,24:1 | 3,55:1 | 10,93:1 | 9,55:1 |

**Les quatre passent F9 (>= 3:1 des deux couleurs de verdict) et F10 (>= 3:1 contre le corps, ici
>= 3:1 contre un texte qui n'est plus blanc mais qui reste lisible a plus de 9,5:1)**, avec une
marge d'au moins 0,23 sur le plancher le plus juste. J'ai volontairement evite toute teinte proche
d'un rouge ou d'un vert - meme clairs - pour ne pas rouvrir la question sur une nuance voisine.

**Ce que je nomme comme decision qui n'est pas neutre, et que `nadia-prof` doit juger comme un
contenu de forme a part entiere** : le texte des boutons de reponse cesse d'etre blanc uniformement
dans toute l'application. C'est un changement plus visible que le choix des quatre teintes
elles-memes, et je ne le glisse pas sous silence.

**Reserve G, rappelee et non levee** : la marque "deja essayee" de M9 doit rester lisible sur ces
quatre fonds clairs - un exercice different de celui pose sur l'ancienne teinte unique et sombre.
Je ne dessine pas cette marque ici ; je confirme seulement qu'elle devra etre mesuree sur les quatre
teintes ci-dessus, pas sur une cinquieme proposee entre-temps.

## 5. F5 et F6 - satisfaites par construction, pas par ajustement

**F5** : je ne touche plus aucune largeur de bordure, de colonne ni de rembourrage de bouton. La
bordure du cabinet redevient l'unique regle de production (`0,5rem` sur ses quatre cotes), donc
chaque colonne de chaque jeu retrouve exactement sa largeur d'avant ce chantier - `152,00 px` sur
"Le bon verbe", inchangee, verifiee ci-dessus sur le rendu reel avec `mangeons`. Le plancher de
`150,27 px` est tenu avec la meme marge qu'avant que je ne m'en approche.

**F6** : aucun item ni distracteur n'est touche dans aucun paquet. Je n'ai pas eu besoin d'y
toucher : la route qui evite d'elargir la bordure rend la question sans objet, exactement comme
`nadia-prof` l'ecrit en section 2.c de son arbitrage.

## 6. Corps en deux tons et etoiles du socle - inchanges, acceptes

Je ne retouche ni l'un ni l'autre : `nadia-prof` les accepte tels quels. Reverifies sur le rendu de
cette version (memes regles, mêmes valeurs) pour m'assurer qu'aucun changement voisin ne les a
degrades par accident - confirme, aucune difference.

## Ce que je remonte

- **A `nadia-prof`** : le jugement sur les quatre points redessines (F1, F2, F4, F9/F10), la
  question du texte sombre sur les boutons (section 4, un changement que je nomme explicitement
  plutot que de le glisser dans une liste de teintes), et F8/F11/F12 sur l'enseigne (je ne fais que
  la partie forme).
- **A `theo-archi`** : confirmation que `.cabinet-levier` (nouveau frere de `.propositions`, aucun
  `clip-path` ni `overflow` sur un ancetre de pressable) passe `LHabillageNeRogneJamaisUnElementPressableTest`
  et `LHabillageResteImmobileTest` sans amendement ; entree de `SELECTEURS_DU_CABINET` pour
  `.cabinet-levier`, `.cabinet-levier::before` et `.cabinet-levier::after`, chacun en `rem`
  uniquement, verifie sur mon propre candidat (aucune sous-chaine `px`).
- **A `camille-po` / `sofia-sm`** : le changement de couleur de texte des boutons de reponse (blanc
  vers `--recreo-texte`) est un choix visible pour l'utilisatrice, pas seulement un detail
  technique ; je le signale pour qu'il soit su avant d'etre livre.
- **Reserve G** : rappelee, non levee - la marque de M9 doit etre mesuree sur les quatre teintes
  claires ci-dessus.

Je ne fusionne pas, je ne pousse pas.
