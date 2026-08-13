# Proposition ines-design - le meuble d'arcade redessine en CSS pur

Sprint 10, chantier repris sur les references de l'utilisatrice (`DEC-0023`). Je propose, je ne
tranche pas : `nadia-prof` rend un arbitrage prealable sur ce document avant qu'une ligne de
production soit ecrite (`squad/README.md`, section 10). Ce document remplace ma proposition
precedente (`proposition-ines-manche-et-relief.md`, branche `s10-ines-design`) sur le point du
manche : le dome de fronton y est abandonne, remplace par le levier de flanc demande par
l'utilisatrice apres avoir vu le dome rendu. Le relief et le contraste de cette proposition
precedente sont **reconduits** ici a l'identique, comme demande.

## Lignee et methode de mesure

Worktree dedie `s10-ines-meuble`, provisionne depuis `round-two` a `b15bda1`, verifie par
`bash outils/verifier-lignee.sh`. Je n'ai modifie ni `recreo.css` ni aucun gabarit : `git status`
sur le code de ce worktree est vide au moment ou j'ecris ceci.

**Comment j'ai mesure.** Le meme procede que `nadia-prof` a decrit pour son arbitrage du
2026-08-12 : le fat jar reel lance en local (JDK 21 designe par `JAVA_HOME` le temps de la
commande - le `java` du `PATH` de ce poste est un JRE 8), une partie jouee sur "Le bon verbe"
jusqu'a retrouver l'etat exact qu'elle a nomme comme reference - l'enonce *"Autrefois, ils ___ ce
dessin anime chaque mercredi."*, sans indice - puis le fragment HTML **reellement servi** par le
serveur, repose dans une page qui charge les deux feuilles reellement servies
(`vendor/bootstrap.min.css` puis `css/recreo.css`) et reproduit l'en-tete et `.container.zone-jeu`.
Mes regles candidates sont ajoutees dans une feuille chargee apres cette copie, jamais dans
`recreo.css` du depot. Capture par `outils/mesurer-rendu.sh` (Chrome designe par la variable
`CHROME`), a 360 x 640 - le format que `nadia-prof` a fixe comme reference. Mesure pixel par pixel
par balayage de couleur exacte (Pillow, script jetable non commite), jamais a l'oeil.

**Defaut d'outillage confirme, contournement documente plutot que cache.** `theo-archi` a
lui-meme note qu'un chemin de PNG relatif rend "code 0" sans rien ecrire (sa correction est sur
`s10-theo-outillage`, non fusionnee). J'ai rencontre le meme defaut sur l'argument d'URL : un
chemin de fichier au format POSIX (`file:///c/...`) passe a `outils/mesurer-rendu.sh` produit une
capture non vide mais montrant une page qui n'a pas charge ses feuilles - le navigateur natif ne
resout pas cette forme. J'ai fourni un chemin `file:///C:/...` deja converti en argument, et un
chemin de sortie PNG absolu, comme demande. Ce n'est pas un defaut du script sur ce point precis
(il ne convertit que son propre fichier temporaire, jamais l'URL fournie par l'appelant - c'est
son contrat), donc je ne remonte rien de nouveau ici.

**Etat de reference commun a toute cette proposition**, mesure avant tout candidat : cabinet
`x = 12..347` (335 px), `y = 413..634` (221 px), pli a `y = 640`. Identique au pixel a la mesure de
`nadia-prof` du 2026-08-12 sur le meme etat.

## Vue d'ensemble : ce qui tient, ce qui a un cout nomme, ce qui tombe

| Element | Decide par | Cout largeur | Cout hauteur | Statut |
|---|---|---|---|---|
| Corps en deux tons + relief (reconduit) | libre, deja mesure par `nadia-prof` | 0 px | **+1 px** (accepte, N6) | pret |
| Visage souriant + bande d'enseigne | `DEC-0023` point 4 et 5 | 0 px | 0 px | pret, texte a router |
| Rangee de boutons colores | libre | 0 px | 0 px | pret |
| Etoiles fixes sur le socle | libre | 0 px | 0 px | pret |
| Levier a boule sur le flanc | `DEC-0023` point 3 | **-8 px par colonne (3 col), -12 px (2 col)**, tenu au-dessus de tout plancher | 0 px | pret, a confirmer par `theo-archi` |
| Arcs-en-ciel sur les flancs | libre | - | - | **ecarte ce tour-ci**, motif en section 7 |
| Bac de gains | interdit (C6, `DEC-0023` point 2) | - | - | hors perimetre, non instruit |

Rien de ce qui suit n'ajoute de hauteur au-dela du pixel deja accepte par `nadia-prof` pour le
relief. C'est le seul geste qui touche le budget vertical ; tout le reste est mesure a zero.

## 1. Corps en deux tons et relief - reconduit a l'identique

Je reprends mot pour mot ma proposition du 2026-08-12 (`proposition-ines-manche-et-relief.md`,
sections 2 et 3), avec une seule correction : la bordure garde `--recreo-entete-fond` au lieu de
passer elle aussi a `--recreo-cabinet-fond`. Ma proposition precedente changeait la bordure **et**
le corps a la meme teinte sombre, ce qui donnait un objet a un seul ton plus sombre, pas "un corps
en deux tons" au sens de `DEC-0023` point 1. Ici la bordure reste la teinte mesuree a 8,61:1 contre
la page (familiere, c'est celle de l'en-tete) et seul le corps derriere le bandeau et le socle
passe a `--recreo-cabinet-fond` : l'objet se lit desormais en deux epaisseurs distinctes.

```css
:root {
    --recreo-cabinet-fond: #081c2c;
}

.cabinet {
    background-color: var(--recreo-cabinet-fond);
    box-shadow: 0 0.0625rem 0 0 rgba(0, 0, 0, 0.4);
}

.cabinet-bandeau {
    box-shadow: inset 0 -0.0625rem 0 rgba(0, 0, 0, 0.35);
}

.cabinet-socle {
    background-color: #061420;
}
```

**Cout mesure sur l'etat de reference** : bas du cabinet a `y = 635` (contre `634` sans cette
regle), **+1 px exactement**, flancs identiques (`x = 12..347`, inchange). C'est exactement le
chiffre que `nadia-prof` a deja mesure et accepte le 2026-08-12 (sa condition N6) sur mon propre
candidat de relief - je le remesure ici sur mon nouveau worktree plutot que de le recopier sans
verifier, et il tient a l'identique.

**Contraste**, methode WCAG explicite (linearisation sRGB, `L = 0,2126 R + 0,7152 G + 0,0722 B`,
calcul jetable non commite) : `--recreo-cabinet-fond` (`#081c2c`) contre `--recreo-accent`
(`#2e6f9e`, colonnes) = **3,20:1** (contre 1,71:1 aujourd'hui sur l'ancienne teinte unique) ;
`--recreo-cabinet-fond` contre la page = 16,11:1 ; bordure `--recreo-entete-fond` contre la page =
**8,61:1**, valeur que `nadia-prof` a elle-meme corrigee le 2026-08-12 (son 9,25:1 mesurait contre
le blanc pur, pas contre le fond de page). Ces quatre valeurs sont deja dans sa fiche sous mes
conditions N8 : je ne fais que les reprendre, pas les redemontrer.

## 2. Visage souriant et enseigne - zero cout, meme bande

Le fronton (`.cabinet-bandeau`, 2 rem de haut, largeur pleine) porte aujourd'hui une seule etoile.
Je la retire et je mets a sa place, dans la **meme bande, a la meme hauteur declaree**, un visage
et une enseigne, cote a cote.

### 2.1. Le visage : deux yeux, une bouche, joues rosees - un seul geste CSS pour chacun

```css
.cabinet-bandeau {
    position: relative;
    justify-content: flex-start;
    gap: 0.5rem;
    padding: 0 0.75rem;
}

.cabinet-bandeau::before {
    content: none;
}

.cabinet-visage {
    position: relative;
    width: 0.9375rem;
    height: 0.375rem;
    flex-shrink: 0;
}

.cabinet-visage::before {
    content: "";
    position: absolute;
    top: -0.5rem;
    left: 0;
    width: 0.3125rem;
    height: 0.3125rem;
    border-radius: 50%;
    background-color: var(--recreo-accent-texte);
    box-shadow:
        0.625rem 0 0 var(--recreo-accent-texte),
        -0.1875rem 0.3125rem 0 0.125rem #f7a8b8,
        0.9375rem 0.3125rem 0 0.125rem #f7a8b8;
}

.cabinet-visage::after {
    content: "";
    position: absolute;
    top: 0.0625rem;
    left: 0.1875rem;
    width: 0.5625rem;
    height: 0.3125rem;
    border-bottom: 0.09375rem solid var(--recreo-accent-texte);
    border-radius: 0 0 50% 50%;
}
```

**Un seul pseudo-element porte les deux yeux et les deux joues** : `::before` est le premier oeil
(un cercle plein), et chaque `box-shadow` en ajoute une copie decalee de la meme forme - un
deuxieme oeil a `10 px` a droite, et deux joues roses plus larges (`spread` de `2 px`) de part et
d'autre. `::after` est la bouche : un rectangle sans fond, dont seule la bordure basse est visible
et arrondie - le trace d'un sourire, sans `clip-path`.

**Verifie plutot qu'affirme, en zoomant sur le rendu reel** (agrandissement x12, pixels reels, pas
d'interpolation) : le visage se lit sans ambiguite comme deux yeux surmontant des joues rosees, un
sourire dessous. Aucune des deux formes ne bouge, aucune n'est conditionnee par `:hover` ni par
aucune classe de verdict - c'est un dessin statique, comme la croix et la coche du signal
juste/faux.

**C4 tenue par construction, pas par consigne.** Rien dans cette regle ne lit `.signal.est-juste`
ni `.signal.est-faux`, et le cabinet n'existe de toute facon pas sur l'ecran de retour (`ADR-0030`
point 1.d) : le visage ne peut structurellement pas varier entre une bonne et une mauvaise reponse,
puisqu'il n'a nulle part ou le faire.

### 2.2. L'enseigne : je dessine la bande, pas le mot

```css
.cabinet-enseigne {
    font-size: 0.875rem;
    font-weight: bold;
    color: var(--recreo-accent-texte);
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
```

`content` reste limite a la chaine vide partout ou je dessine (`ADR-0027` clause 7) : cette classe
ne porte aucun `content`, c'est un texte que le gabarit devra rendre. **Ce que je ne fais pas et
qui n'est pas de mon ressort** : ajouter un `<span class="cabinet-enseigne">` dans
`fragments/manche.html`. Je le decris pour que `sofia-sm` le route :

```html
<div class="cabinet-bandeau" aria-hidden="true">
    <span class="cabinet-visage"></span>
    <span class="cabinet-enseigne" th:text="${nomEnseigne}"></span>
</div>
```

Le nom lui-meme (parmi les trois candidats de `camille-po`, tranche par `nadia-prof`) est un texte
lu par un enfant : il suit l'exigence de francais integralement accentue de `DEC-0002` amende,
verifiee par `relecteur-pedagogique`, pas cette proposition.

**Reserve d'accessibilite que je nomme sans la trancher.** `.cabinet-bandeau` porte aujourd'hui
`aria-hidden="true"` (S8-1, parce qu'il ne portait qu'une etoile decorative). Si l'enseigne devient
un texte que `nadia-prof` juge porteur de sens, cet attribut le rend invisible a un lecteur d'ecran
en meme temps que le visage. Je ne tranche pas si c'est le cas ici - ce jugement n'est pas de mon
ressort - je nomme seulement que la question existe, pour `theo-archi` et `nadia-prof`.

**Cout mesure : 0 px, dans les deux dimensions.** Bas du cabinet a `y = 635` (identique a la
section 1, donc aucun cout ajoute par cette section), flancs `x = 12..347` (inchange). Verifie sur
le nom le plus long des trois candidats de `camille-po`, **"La Boite a Bonnes Reponses"**
(27 caracteres) : a `0,875rem`, le mot tient entierement dans la largeur du fronton a cote du
visage, sans troncature ni ellipse, y compris **une fois le flanc elargi par le levier** (section
4) - fronton reduit a `296 px` d'interieur, mot encore entierement visible avec une marge a
droite. Verifie aussi bien a trois colonnes ("Le bon verbe", "Le bon son") qu'a deux ("Le bon petit
mot") : le fronton a la meme largeur quel que soit le nombre de colonnes, cette mesure vaut donc
pour les huit jeux du catalogue.

## 3. Rangee de boutons colores et etoiles du socle - libre, zero cout

```css
.propositions .proposition:nth-child(4n+1) { background-color: #c0392b; }
.propositions .proposition:nth-child(4n+2) { background-color: #1f7a3d; }
.propositions .proposition:nth-child(4n+3) { background-color: #8a5d00; }
.propositions .proposition:nth-child(4n+4) { background-color: #6c3fa8; }

.cabinet-socle {
    position: relative;
}

.cabinet-socle::before,
.cabinet-socle::after {
    content: "";
    position: absolute;
    top: 0.25rem;
    width: 0.5rem;
    height: 0.5rem;
    background-color: var(--recreo-accent-texte);
    opacity: 0.55;
    clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%, 32% 57%, 2% 35%, 39% 35%);
}

.cabinet-socle::before { left: 0.5rem; }
.cabinet-socle::after { right: 0.5rem; }
```

**Quatre teintes cycliques sur les boutons de reponse**, plutot que le seul `--recreo-accent`
actuel : chacune verifiee au meme plancher de contraste contre le texte blanc que la couleur
actuelle (calcul WCAG explicite, methode de la section 1) :

| Couleur | Contraste contre le blanc du texte |
|---|---|
| `#c0392b` (rouge) | 5,44:1 |
| `#1f7a3d` (vert) | 5,37:1 |
| `#8a5d00` (ambre) | 5,76:1 |
| `#6c3fa8` (violet) | 7,21:1 |

Les quatre passent le plancher de 4,5:1 avec marge, le plus juste (`5,37:1`) restant au-dessus de
la valeur actuelle mesuree dans le code (`5,41:1` au repos, commentaire de `.proposition::after`).
**Ce que je ne fais pas** : porter un second canal non colore sur ces boutons. La regle du second
canal (mon propre mandat) protege un signal qui porte du sens - juste/faux, avancer/sortir - et ces
couleurs n'en portent aucun : les quatre boutons restent strictement equivalents, la couleur ne
distingue ni une reponse juste d'une fausse, ni une colonne d'une autre au sens ou l'enfant devrait
s'y fier. C'est une decoration, comme un bouton d'arcade reel, pas un indice.

**Deux etoiles fixes ancrees sur `.cabinet-socle`**, jamais sur `.cabinet` (voir section 4 pour le
motif exact) : `content: ""`, `clip-path` en pourcentages, meme geometrie que l'etoile existante du
depot (S8-1), simplement plus petite et opacifiee.

**Cout mesure : 0 px.** Bas du cabinet et flancs inchanges par rapport a la section 2. Verifie sur
trois jeux (2, 3 et 4 colonnes) : aucune largeur de colonne ne bouge, la couleur de fond est la
seule propriete qui change sur `.proposition`.

**Ce que je route a `nadia-prof` plutot que de le trancher** : est-ce qu'une couleur differente par
position de bouton reste bien un decor et non un debut de signal, au sens de sa condition C5 ? Ma
lecture (ci-dessus) est que oui - C5 parle des images du **cabinet**, pas de la couleur du bouton
lui-meme, qui a toujours ete coloree - mais c'est elle qui juge, pas moi.

## 4. Le levier a boule sur le flanc - le point le plus lourd, mesure entierement

### 4.1. Le probleme de largeur, et la solution : des bordures asymetriques

`theo-archi` a nomme le risque exact : un pseudo-element qui deborde du flanc devient payeur
unique du rembourrage de `.container`, ce que `nadia-prof` refuse (C1, section 3 de son arbitrage
du 2026-08-11). Et une bordure elargie sur les **quatre** cotes de `.cabinet` couterait deux fois
son elargissement en hauteur - avec le budget vertical qu'il reste (4 rangees libres sur l'etat le
plus bas du catalogue), la moindre dizaine de pixels de plus le ferait basculer sous le pli.

**Ma proposition : elargir la bordure seulement a gauche et a droite**, jamais en haut ni en bas.
CSS le permet nativement (`border-left-width` / `border-right-width` distincts du raccourci
`border`), et c'est la seule regle qui touche `.cabinet` dans cette section :

```css
.cabinet {
    position: relative;
    border-left-width: 1.25rem;
    border-right-width: 1.25rem;
}
```

**Le budget vertical ne bouge pas d'un pixel** : `border-top-width` et `border-bottom-width`
restent a `0,5rem` (herites du raccourci `border` de la production), donc rien n'est ajoute au
bandeau, au socle ni a la hauteur totale du cabinet.

**Le budget horizontal est repaye par la largeur des colonnes**, exactement le mecanisme que
`nadia-prof` a deja autorise en section 3 de son arbitrage du 2026-08-11 ("le bon payeur"). Mesure
sur l'etat de reference (etat le plus contraint, "Le bon verbe" en 2x2) et sur "Le bon son" (3
colonnes, plancher le plus bas du catalogue a 79 px) :

| Jeu | Colonnes | Largeur avant (bordure 8 px) | Largeur apres (bordure 20 px) | Plancher de `nadia-prof` | Marge |
|---|---|---|---|---|---|
| Le bon son | 3 | 96 px | **88 px, mesure** | 79 px | **9 px** |
| Calcul CP | 3 | 96 px | 88 px (meme formule) | 77 px | 11 px |
| Le bon petit mot | 2 | 152 px | **140 px, mesure** | 106 px | 34 px |
| Le bon verbe | 2 (mise en 2x2) | - | **140 px, mesure** | hors tableau de `nadia-prof` (sa disposition propre) | - |

Les deux valeurs en gras sont mesurees sur un rendu Chrome reel avec le fragment servi par
l'application (pas calculees). Les deux autres suivent la meme formule
(`(336 - 2 x bordure - gaps) / colonnes`) que `nadia-prof` a ecrite elle-meme et que j'ai verifiee
identique sur mes deux cas mesures ; je ne les remesure pas separement, je le nomme comme limite.

**La bordure peut monter jusqu'a `2,09rem` (33,5 px) avant de croiser le plancher de "Le bon son"**
(le plus serre du catalogue) : `1,25rem` (20 px) est ma proposition minimale pour loger le levier
avec une marge de respiration, pas un maximum. Si `nadia-prof` ou `theo-archi` veut un levier plus
grand, il reste jusqu'a `13,5 px` de plus disponibles de chaque cote avant de retoucher a un
plancher qu'elle a fixe.

### 4.2. La forme : un fut et une boule, en position de repos

**Necessite un point d'ancrage neuf dans le gabarit**, sur le meme modele que `.cabinet-bandeau` et
`.cabinet-socle` en S8 - je le decris, je ne l'ecris pas :

```html
<div class="cabinet-socle" aria-hidden="true"></div>
<div class="cabinet-levier" aria-hidden="true"></div>
```

Sibling de `.propositions`, jamais son ancetre : `.cabinet-levier` ne wrappe aucun bouton, donc
aucune regle qui le nomme ne peut jamais entrer dans la population des ancetres de pressables
d'`ADR-0033` - la meme garantie structurelle que `.cabinet-bandeau` et `.cabinet-socle` portent deja
depuis S8-1.

```css
.cabinet-levier {
    position: absolute;
    top: 50%;
    right: -1.25rem;
    width: 1.25rem;
    height: 5rem;
    transform: translateY(-50%);
}

.cabinet-levier::before {
    content: "";
    position: absolute;
    left: 50%;
    top: 0.75rem;
    bottom: 0.5rem;
    width: 0.375rem;
    transform: translateX(-50%);
    background-color: var(--recreo-accent-texte);
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
    background-color: var(--recreo-accent-texte);
    border-radius: 50%;
    box-shadow: inset 0 -0.1875rem 0 rgba(0, 0, 0, 0.18);
}
```

`::before` est le fut (un rectangle arrondi, centre sur l'axe vertical) ; `::after` est la boule
(un cercle, avec un relief interne statique - le meme geste que le bouton-poussoir de `.demarrer`
et `.encore`, deja valide par `nadia-prof` le 2026-08-11 : "elles disent d'appuyer et non de
tirer"). `right: -1.25rem` place `.cabinet-levier` exactement dans l'epaisseur de la bordure
elargie (section 4.1), jamais au-dela : il ne deborde pas de `.cabinet`, il occupe une zone que
`.cabinet` peint deja.

**Verifie sur le rendu, pas suppose** : le pixel le plus a droite de la boule est a `x = 346`, le
bord exterieur du cabinet (couleur de bordure) est a `x = 347`, la page reprend a `x = 348`. La
boule reste **entierement a l'interieur du contour du cabinet**, avec 1 px de marge avant le bord
peint. Aucun pixel n'est ajoute a la largeur totale du cabinet par le levier lui-meme : le seul
cout est celui de la bordure elargie, deja compte en 4.1.

**Position de repos, jamais a mi-course.** Le fut est vertical, centre, sans inclinaison ; la boule
est au sommet, immobile - aucune classe ni geste ne la deplace. Aucune regle ne declare
`transition` ni `animation` sur `.cabinet-levier` ni sur ses pseudo-elements, et aucune n'est
conditionnee par `:hover`, `:active` ni `:focus` - `ADR-0033` le rend desormais executable en CI,
je l'ai verifie a la main sur ma propre regle avant de l'ecrire ici.

**Ce que la forme evite, comme pour le dome ecarte** : pas d'inclinaison (le fut est strictement
vertical, centre par `left: 50%` + `transform: translateX(-50%)`, jamais un `left` fixe ajuste a
l'oeil) ; pas de mi-course (la boule est toujours au sommet du fut, jamais a une fraction de la
hauteur) ; pas de fleche. Zoomee au rendu reel (x6), la forme se lit sans ambiguite comme un levier
monte sur le flanc - fut et boule, contrairement au dome de fronton qui se lisait comme une arche a
20 x 24 px. La difference tient a la taille disponible : `18 px` de diametre pour la boule contre
les `20 x 24 px` de boite entiere qu'avait le dome, et surtout une hauteur totale de `5rem` (80 px)
contre `24 px` - le flanc donne au levier la place de se deployer que le fronton n'avait pas.

**Hauteur du levier et etats les plus courts du catalogue.** `5rem` est choisi pour rester contenu
dans la hauteur du cabinet meme sur l'etat le plus court mesure (une seule rangee de colonnes,
"Calcul CP"/"Le bon son" : hauteur de cabinet **138 px**, bandeau et socle compris). Centre
verticalement (`top: 50%`), le levier de `80 px` de haut reste integralement dans cette hauteur.
Sur les etats plus hauts (deux rangees, indice affiche), il reste centre et n'atteint ni le bandeau
ni le socle - un choix de forme, pas une contrainte de test.

### 4.3. Ce qui reste a confirmer, nomme plutot que suppose

- **`theo-archi`** : `.cabinet-levier`, `.cabinet-levier::before` et `.cabinet-levier::after`
  entrent dans `SELECTEURS_DU_CABINET` (`ADR-0033` section 7.a) avec leur bloc dans le jeu fautif -
  je ne l'ecris pas, ce n'est pas de la forme. Et `border-left-width` / `border-right-width` sur
  `.cabinet` restent en `rem`, comme le reste de la feuille : verifie sur mon propre candidat,
  aucune sous-chaine `px`.
- **`camille-po` / `sofia-sm`** : le nouvel element `<div class="cabinet-levier">` dans
  `fragments/manche.html`, sibling de `.propositions` - une story neuve, pas codee ici.
- **`nadia-prof`** : le jugement sur la forme elle-meme (`ADR-0025` : aucun automate ne voit une
  silhouette), et sur la taille de bordure retenue (`1,25rem` propose, jusqu'a `2,09rem` possible).

## 5. Coins arrondis - rien a changer

`border-radius: 0,75rem` existe deja sur `.cabinet` depuis S8-1 et reste inchange par toute cette
proposition. Verifie sur le rendu : les quatre coins restent visiblement arrondis avec la bordure
asymetrique de la section 4 (le rayon s'applique aux deux axes independamment de l'epaisseur de
bordure sur chaque cote, propriete du modele de boite CSS et non une supposition de ma part -
confirme sur la capture).

## 6. Ce qui est interdit et que je n'ai pas approche

Aucune des regles ci-dessus ne declare ni ne pourrait laisser deviner : `cerise`, `citron`,
`cloche`, `couronne`, `diamant`, `barre`, `BAR`, `sept`, `777`, un recepteur de gain, une fente a
piece, un afficheur de credit. Le visage n'est pas une cloche (mon N2 precedent : base qui ne
s'evase pas, rien au sommet ; le meme principe geometrique s'applique ici, la forme du visage
n'ayant ni evasement ni pastille). La boule du levier est un cercle simple sans facette ni eclat,
pas une gemme.

## 7. Ecarte ce tour-ci : les arcs-en-ciel sur les flancs

`DEC-0023` laisse les arcs-en-ciel a ma latitude, et je ne les propose pas dans ce lot. Motif
nomme plutot que silence : chaque decor decoupe doit pendre d'un bloc qui n'est ancetre d'aucun
pressable, et les seuls blocs de ce type qui existent aujourd'hui sont `.cabinet-bandeau` et
`.cabinet-socle`, chacun limite a deux pseudo-elements. Le visage et l'enseigne consomment les deux
de `.cabinet-bandeau` ; les etoiles et - si elle est retenue - la story du levier consomment ceux
de `.cabinet-socle` (le levier lui-meme demande un troisieme bloc neuf, `.cabinet-levier`). Ajouter
un arc-en-ciel proprement demanderait un quatrieme bloc decoratif neuf dans le gabarit, une
demande supplementaire que je n'ouvre pas dans un lot deja charge sur le point le plus disputable
(le levier). Je le note comme piste libre pour un tour futur plutot que de la bâcler dans celui-ci.

## Priorite, si tout ne tient pas

1. **Corps en deux tons et relief** (section 1) : deja mesure et accepte par `nadia-prof` sous un
   autre nom de sprint, cout connu, aucune dependance.
2. **Visage et enseigne** (section 2) : zero cout, mais l'enseigne attend un texte tranche par
   `nadia-prof` parmi les trois noms de `camille-po` et un slot de gabarit non code ici.
3. **Boutons colores et etoiles du socle** (section 3) : zero cout, entierement independant du
   reste - peut etre livre seul si le levier est retarde.
4. **Levier de flanc** (section 4) : le point le plus lourd de `DEC-0023`, mesure entierement,
   mais le seul qui demande un aller-retour avec `theo-archi` (garde-fou de test) et une story
   neuve (element de gabarit). S'il tombe ce sprint, les trois premiers tiennent seuls et le
   cabinet garde son fronton nu de flanc, sans regression par rapport a l'etat livre de s08.
5. **Arcs-en-ciel** (section 7) : ecarte ce tour-ci, motif ecrit ci-dessus.

## Ce que je remonte, en un endroit

- A `theo-archi` : la confirmation de `SELECTEURS_DU_CABINET` pour les trois nouveaux selecteurs du
  levier, et si `1,25rem` de bordure de flanc lui semble compatible avec le reste de son lot de
  faisabilite sur ce meme point.
- A `sofia-sm` : deux elements de gabarit neufs a router en story - `<span class="cabinet-enseigne">`
  dans le bandeau, `<div class="cabinet-levier">` sibling de `.propositions`.
- A `camille-po` : rien de neuf, ses trois noms d'enseigne restent les candidats.
- A `nadia-prof` : l'arbitrage prealable complet de ce document avant toute ligne de production -
  la forme du visage, la forme du levier, la teinte du corps en deux tons, la lecture de la couleur
  des boutons comme decor et non comme indice (section 3), et la reserve d'accessibilite sur
  `aria-hidden` (section 2.2) que je nomme sans la trancher.
