# Proposition ines-design - silhouette du manche, relief du cabinet, contraste cadre/colonnes

Chantier sprint 10. Je propose, je ne tranche pas : `nadia-prof` rend un arbitrage prealable sur ce
document avant qu'une ligne de production soit ecrite (`squad/README.md`, section 10). Je lis
`DEC-0022` (branche `s10-camille-po`, non fusionnee) et `ADR-0033` (branche `s10-theo-archi`, non
fusionnee) comme les sources qui bornent chaque choix ci-dessous, et je les cite par leurs clauses.

## Lignee et methode de mesure

Worktree dedie provisionne depuis `round-two` a `b15bda1`, verifie par `outils/verifier-lignee.sh`.
Je n'ai pas fusionne `s10-camille-po` ni `s10-theo-archi` dans ma branche : j'ai lu leurs commits
(`0c03f2c` pour `DEC-0022`, `8502fa5` pour `ADR-0033`) par `git show`, sans les amener dans mon
arbre. `recreo.css` et les gabarits de mon worktree sont ceux de `round-two`, intacts (`git status`
vide sur le code au moment ou j'ecris ceci) - je n'ai ni modifie `recreo.css` ni les gabarits, comme
le mandat le demande.

**Comment j'ai mesure.** Une maquette jetable, non commitee, hors de toute story : une page HTML
statique chargeant `vendor/bootstrap.min.css` puis une copie de `recreo.css`, dans cet ordre reel,
et reproduisant la structure exacte de `accueil.html` (en-tete, `.container.zone-jeu`) et de
`fragments/manche.html` (le cabinet a trois colonnes de "Calcul CP" : `10`, `7`, `9`). J'ai ajoute
mes regles candidates dans une feuille chargee apres cette copie, jamais dans `recreo.css` du depot.
Capture par `outils/mesurer-rendu.sh` (Chrome designe par la variable `CHROME`) a 360 px et 375 px,
puis mesure pixel par pixel (balayage de couleur exacte, Pillow) plutot qu'estimee a l'oeil pour
chaque chiffre cite ci-dessous. Ce que cette methode ne couvre pas : les 33 etats reels de "Le bon
verbe" et leurs enonces exacts - je n'ai que les plages agregees que `nadia-prof` a deja publiees
(`FICHE-bandit-manchot-additions-cp.md`, section 8, point 1) ; je le nomme comme limite a chaque
endroit ou elle joue.

## 1. La silhouette du manche

### Forme retenue : un dome symetrique sur socle plein, en `clip-path: polygon()`

Un seul point d'ancrage, exactement celui que `DEC-0022` et `ADR-0033` fixent tous les deux :
`.cabinet-bandeau::after`, `position: absolute`, `.cabinet-bandeau` recevant `position: relative`
comme seul ancrage nouveau (`ADR-0033` section 7.b - "rien d'autre ne doit apparaitre"). Boite :
`top: 0.25rem; right: 0.5rem; width: 1.25rem; height: 1.5rem`, la piste chiffree du mandat, que j'ai
verifiee plutot que reprise sans regarder (section 1.2).

```css
.cabinet-bandeau {
    position: relative;
}

.cabinet-bandeau::after {
    content: "";
    position: absolute;
    top: 0.25rem;
    right: 0.5rem;
    width: 1.25rem;
    height: 1.5rem;
    background-color: var(--recreo-accent-texte);
    clip-path: polygon(
        50% 0%, 75% 5.58%, 93.3% 20.83%, 100% 41.67%,
        100% 100%, 0% 100%, 0% 41.67%, 6.7% 20.83%, 25% 5.58%
    );
}
```

**Geometrie du polygone, et pourquoi elle n'est pas devinee.** Le point de depart demandait une
tete ronde (langage de `.demarrer`/`.encore`) et une embase pleine. J'ai dessine un demi-cercle
reel - pas une approximation vague - inscrit dans la moitie superieure de la boite : rayon `r =
10 px`, exactement `largeur / 2`, centre a `(10 px, 10 px)` dans une boite de `20 x 24 px`. Le
demi-cercle va d'un equateur a l'autre (`x = 0` et `x = 20 px`, a `y = 10 px`) en passant par le
sommet (`x = 10 px`, `y = 0`) ; sept points le composent, a `30 deg` d'ecart, convertis en
pourcentages de la boite (`x / 20`, `y / 24`, jamais la meme echelle sur les deux axes puisque la
boite n'est pas carree - c'est ce qui rend le demi-cercle reellement rond a l'ecran plutot
qu'ovale). Les cotes descendent ensuite tout droit, a largeur constante (`0 %` et `100 %`), jusqu'au
bord bas de la boite (`100 %`), qui ferme le polygone par une arete horizontale pleine sur toute la
largeur - l'embase. Aucune valeur n'est une estimation : chaque pourcentage est `sin`/`cos` d'un
angle multiple de 30 degres, calcule et non arrondi a la main au-dela de deux decimales.

**Symetrie gauche/droite** : exacte par construction - les points a `x = 75 %` et `x = 25 %`, `x =
93,3 %` et `x = 6,7 %`, `x = 100 %` et `x = 0 %` sont des paires miroir autour de `x = 50 %`, jamais
une paire ajustee separement.

**Ce que la forme evite, verifie plutot qu'affirme** :
- pas d'inclinaison ni de degrade directionnel : le polygone est symetrique, aucun sommet ne penche ;
- pas de fleche ni de pointe : le sommet est un arc, pas un angle aigu, et le bas est une arete
  pleine, pas un point ;
- pas de neck etroit qui isolerait la tete du reste, contrairement a une piste que j'ai testee et
  ecartee (section 1.3) - les flancs de la tete rejoignent directement les cotes du socle a
  largeur egale, sans etranglement.

### 1.1. Mesure du cout : 0 px de largeur, 0 px de hauteur, prouve sur le rendu

Deux captures a 360 px, memes trois colonnes, memes couleurs - l'une sans le bloc `::after`, l'autre
avec :

| | Bande du cabinet (couleur exacte `#1d4b6b`, balayage de pixels) | Largeur du cabinet |
|---|---|---|
| Sans manche | `y = 312..449` (138 px) | `x = 12..347` (336 px) |
| Avec manche | `y = 312..449` (138 px) | `x = 12..347` (336 px) |

**Identique au pixel**, dans les deux dimensions. C'est la consequence attendue de
`position: absolute` (le manche sort du flux, `.cabinet-bandeau` garde sa hauteur declaree de
`2 rem` et `.cabinet` garde sa largeur de flex), mais je ne me suis pas contentee de la deduire : je
l'ai mesuree sur un rendu Chrome reel plutot que sur la seule lecture de la regle CSS.

**La boite du manche elle-meme**, mesuree par balayage des pixels blancs (`--recreo-accent-texte`)
isoles de l'etoile par leur position en x : `x = 312..331` (largeur mesuree **20 px**, contre
20 px declares - l'ecart de 1 px vient de l'antialiasing du bord droit, pas d'un debordement),
`y = 324..346` (hauteur mesuree **23 px**, contre 24 px declares, meme cause). La boite est bien a
l'endroit annonce par le mandat : le fronton mesure `x = 20..340` a l'interieur du cabinet (`336 px`
moins la bordure de `8 px` de chaque cote) a 360 px de large. L'etoile existante occupe `x =
173..186` a 360 px (`x = 180..194` a 375 px), et le manche occupe `x = 312..331` a 360 px (`x =
327..346` a 375 px) - **126 px** les separent a 360 px, **133 px** a 375 px, jamais un
chevauchement. A 375 px, le manche garde 16 px de degagement par rapport au bord droit du cabinet
(bordure comprise) : la boite du manche ne touche jamais le coin arrondi de `.cabinet`
(`border-radius: 0.75rem`).

### 1.2. Lisibilite a 20 x 24 px : regardee, pas supposee

Capture zoomee (agrandissement x8, interpolation Lanczos, sur les pixels reels rendus) : le dome se
lit clairement comme une forme ronde posee sur une base rectangulaire pleine, sans confusion ni flou
de contour a cette taille. Je n'ai donc pas eu besoin d'activer le repli de la section 1.4 pour ce
candidat.

### 1.3. Une piste ecartee, et pourquoi - important pour eviter qu'elle revienne

J'ai teste une seconde forme avant de retenir le dome : une boule ronde plus large que le fronton
n'aurait pas pu passer sous `20 px` sans une tige etroite, alors j'ai dessine une boule sur une tige
resserree (epaulement a mi-hauteur, boule occupant les deux tiers superieurs, tige pleine sur le
tiers restant, meme methode de calcul par angles). **Rendue et zoomee, cette forme se lit sans
ambiguite comme un trou de serrure**, pas comme une poignee de machine - un signe deja pris, avec un
sens oppose a celui voulu (une serrure appelle a inserer une cle, pas a presser). Je l'ecarte pour
ce motif precis, pas parce qu'elle serait techniquement fautive : `LHabillageNeRogneJamaisUnElementPressableTest`
et `LHabillageResteImmobileTest` seraient verts sur elle aussi (meme ancrage, memes proprietes
statiques). C'est un motif de lisibilite pour un enfant, pas un motif technique, et je le nomme pour
qu'un futur agent ne redecouvre pas cette impasse en la redessinant.

### 1.4. Repli sans `clip-path`, meme cout, si le dome est juge trop confus

```css
.cabinet-bandeau {
    position: relative;
}

.cabinet-bandeau::after {
    content: "";
    position: absolute;
    top: 0.25rem;
    right: 0.5rem;
    width: 1.25rem;
    height: 1.5rem;
    background-color: var(--recreo-accent-texte);
    border-radius: 50% 50% 15% 15% / 60% 60% 10% 10%;
    box-shadow: inset 0 -0.1875rem 0 rgba(0, 0, 0, 0.18);
}
```

Meme boite, memes 0 px de cout (mesure : cabinet `y = 312..449`, `x = 12..347`, identique au cas
sans manche). Le rendu, zoome de la meme facon, montre une courbe nettement plus lisse que le
polygone (arrondi natif du navigateur plutot qu'une approximation a sept points) et porte deja un
relief interne (l'ombre `inset` du bas, sans cout puisqu'elle reste dans la boite). Je le garde en
reserve explicite : si `nadia-prof` juge le dome en polygone trop anguleux ou pas assez "rond" a son
gout, ce repli est deja chiffre et rendu, pas a inventer apres son refus.

### 1.5. Contrainte de garde-fou a nommer, pas a coder moi-meme

`ADR-0033` section 7.a l'ecrit deja : `CabinetDeLaMachineTest` epingle cinq selecteurs et exige zero
`px` sur chacun ; le manche en est un sixieme, et son ordre d'ajout dans la liste compte (avant ou
apres `.cabinet-bandeau::before` change ce que le jeu fautif prouve). Je ne l'ecris pas - c'est un
test, pas de la forme - mais je verifie que **mon propre candidat ne contient aucune sous-chaine
`px`** : ni la boite (`top`, `right`, `width`, `height` en `rem`), ni le polygone (pourcentages
uniquement), ni le repli (`rem` pour l'ombre, pourcentages pour `border-radius`). Le developpeur qui
implementera doit ecrire le selecteur court `.cabinet-bandeau::after` (jamais `.cabinet
.cabinet-bandeau::after` ni `.cabinet::after`) - `ADR-0033` section 3 des constats de son avis de
faisabilite le mesure comme la difference entre vert et rouge sur
`LHabillageNeRogneJamaisUnElementPressableTest`.

## 2. Le relief du cabinet, a cout mesure

Trois gestes, chacun mesure isolement sur le rendu, puis ensemble.

### 2.1. Ombre externe sur `.cabinet` - decalage vertical seul

```css
.cabinet {
    box-shadow: 0 0.0625rem 0 0 rgba(0, 0, 0, 0.4);
}
```

`0.0625rem = 1 px`, `spread = 0`, `flou = 0`, declares explicitement plutot que laisses a defaut.

**Mesure, ligne par ligne, comparee au rendu sans cette regle** (balayage horizontal, `x` non fond de
page uniquement) :

| Rangee (y) | Sans ombre | Avec ombre |
|---|---|---|
| 448 | `x = 17..342` | `x = 16..343` |
| 449 | `x = 19..340` (derniere rangee non vide) | `x = 17..342` |
| 450 | fond de page (aucun pixel de cadre) | `x = 19..340` (derniere rangee non vide) |
| 451 | fond de page | fond de page |

Le cadre s'arrete a `y = 449` sans l'ombre et a `y = 450` avec elle : **+1 px de hauteur visible,
exactement**. Et la coincidence est plus precise qu'une simple egalite de largeur : la silhouette de
la rangee `450` avec ombre (`x = 19..340`) est **identique**, pixel pour pixel, a celle de la rangee
`449` sans ombre (`x = 19..340`) - la meme chose pour chaque rangee au-dessus (`448` avec ombre =
`447` sans ombre, etc.). C'est la preuve que l'ombre ne fait que recopier tout le bord du cadre, coin
arrondi compris, un pixel plus bas, sans jamais l'elargir - **0 px sur les flancs**, mesure et non
deduit de la seule syntaxe `spread = 0`.

**Le chiffre qui borne ce choix** : la reserve 1 de `nadia-prof` mesure `4 px` de marge sur le
meilleur etat du pire jeu ("Le bon verbe" sans indice, bas de cadre a `636 px`, pli a `640 px`).
`1 px` de decalage laisse **3 px** de marge sur cet etat precis. Ce que je ne peux pas garantir avec
mes seules donnees : les etats "avec indice" de ce meme jeu sont donnes par une plage agregee
(`636..792 px` sur 25 etats) dans la fiche de `nadia-prof`, pas par une liste par etat - je ne sais
donc pas si un ou plusieurs de ces 25 etats se situent entre `637` et `639 px`, ce qui ferait basculer
un etat aujourd'hui juste au-dessus du pli en etat juste au-dessous avec ce decalage d'1 px. Je le
nomme comme verification que `nadia-prof` seule peut faire sur le rendu livre avec le contenu reel,
pas comme un risque que j'ecarte par supposition.

### 2.2. Arete basse sur le fronton - ombre interne, cout nul par construction

```css
.cabinet-bandeau {
    box-shadow: inset 0 -0.0625rem 0 rgba(0, 0, 0, 0.35);
}
```

Mesure sur la colonne `x = 20` : la rangee `y = 351`, a l'interieur de la zone du bandeau et juste
au-dessus du haut des boutons de reponse, passe de `rgb(8, 28, 44)` (couleur du corps) a
`rgb(5, 18, 28)` sur un seul pixel puis revient a `rgb(8, 28, 44)` - le filet attendu d'une ombre
`inset` de `1 px`. Une ombre `inset` ne peut, par definition, jamais depasser la boite qui la porte : son
cout est **0 px**, dans les deux dimensions, verifie par le fait que la hauteur totale du cabinet
(`138 px`) et sa largeur (`336 px`) restent identiques a la section 2.3 en ajoutant cette regle.

### 2.3. Plinthe plus sombre sur le socle - un aplat, aucune ombre, hauteur inchangee

```css
.cabinet-socle {
    background-color: #061420;
}
```

Mesure sur la colonne `x = 20` : le socle occupe **exactement** `y = 426..441`, soit **16 px**,
`1 rem` au pixel pres - **hauteur inchangee**, comme le mandat l'exige sans negociation. Le
changement est une couleur, jamais une dimension : `#061420` est chaque canal de
`--recreo-cabinet-fond` (`#081c2c`, section 3) multiplie par un facteur constant (`0,75` sur le
rouge, `0,71` sur le vert, `0,73` sur le bleu - une teinte uniformement plus sombre, pas une nuance
differente). Rapport de contraste avec `--recreo-cabinet-fond` : `1,07:1` (methode de la section 3) -
un chiffre bas au sens WCAG, attendu et sans consequence ici : la plinthe n'est pas un texte ni un
signal a lire, c'est un repere de relief entre deux aplats deja tres sombres, et sa lisibilite
s'est verifiee a l'oeil sur la capture (bande visiblement plus sombre que le corps du cabinet), pas
au rapport de contraste.

### 2.4. Les trois ensemble - capture finale

Capture combinee (silhouette de la section 1, relief de cette section, contraste de la section 3),
360 px, sur deux maquettes distinctes (trois colonnes type "Calcul CP", deux colonnes type "Le bon
petit mot") : le cabinet se lit comme un objet a quatre couches distinctes (fronton legerement
souligne, corps, plinthe plus sombre, ombre basse qui le detache de la page) sans qu'aucune largeur
ni hauteur de colonne ne change - remesure a `96 px` par colonne sur les trois colonnes et `152 px`
sur les deux, memes valeurs qu'avant tout ce chantier (`nadia-prof`, `FICHE-bandit-manchot-additions-cp.md`,
section 3 de son arbitrage prealable de s08 : `(336 - 16 - 32) / 3 = 96`, `(336 - 16 - 16) / 2 = 152`).

### 2.5. Rien d'autre propose a cout nul

J'ai cherche un quatrieme geste et ne le propose pas : un filet clair en haut du bandeau (l'inverse
symetrique de l'arete basse) aurait ete a cout nul lui aussi, mais je l'ai teste et il n'ajoute rien
de lisible sur un fond deja tres sombre (`#081c2c`) - une ombre `inset` claire s'y voit beaucoup
moins qu'une ombre sombre, et je prefere ne rien ajouter qu'ajouter une regle qui ne se voit pas.

## 3. Le contraste cadre/colonnes - `--recreo-cabinet-fond`, methode explicite

Je reprends exactement la variable et la valeur que j'avais deja chiffrees en s08
(`proposition-ines-largeur-du-cabinet.md`, section 6, jamais fusionnee dans `recreo.css`), avec la
methode ecrite au mot pres cette fois, comme demande.

```css
:root {
    --recreo-cabinet-fond: #081c2c;
}

.cabinet {
    background-color: var(--recreo-cabinet-fond);
    border-color: var(--recreo-cabinet-fond);
}
```

`--recreo-entete-fond` (`#1d4b6b`) n'est pas touchee : c'est elle qui porte le plancher du crayon de
`favicon.ico` (commentaire S6-10 dans `recreo.css`, "plus sombre coute le crayon"), et cette
proposition ne rouvre pas cette contrainte. `--recreo-cabinet-fond` est une nuance separee, utilisee
uniquement par `.cabinet`.

**Methode de calcul, ecrite explicitement** : formule WCAG de contraste par luminance relative -
pour chaque canal, normalisation sur `255`, puis linearisation sRGB par seuil (`c <= 0,03928` :
`c / 12,92` ; sinon `((c + 0,055) / 1,055) ^ 2,4`), luminance `L = 0,2126 R + 0,7152 G + 0,0722 B`,
rapport `(L_clair + 0,05) / (L_sombre + 0,05)`. **Applique directement aux valeurs hexadecimales
declarees dans la feuille de style**, pas a un pixel echantillonne sur une capture.

| Paire | Rapport (ma methode) |
|---|---|
| `--recreo-entete-fond` (`#1d4b6b`) vs `--recreo-accent` (`#2e6f9e`, colonnes) | **1,71:1** |
| `--recreo-cabinet-fond` (`#081c2c`) vs `--recreo-accent` (colonnes) | **3,20:1** |
| `--recreo-entete-fond` vs `--recreo-fond` (`#f4f7fb`, page) | **8,61:1** |
| `--recreo-cabinet-fond` vs `--recreo-fond` (page) | **16,11:1** |

La premiere ligne reproduit exactement le `1,71:1` que `nadia-prof` mesure elle-meme sur le couple
cadre/colonnes actuel (`FICHE-bandit-manchot-additions-cp.md`, section 8, point 2) : ma methode est
donc validee comme comparable a la sienne sur cette paire precise. La troisieme ligne, en revanche,
**ne reproduit pas** le `9,25:1` qu'elle rapporte pour la meme paire contre la page - j'obtiens
`8,61:1`, un ecart deja constate en s08 et non resolu depuis. J'ai retente avec le second seuil de
linearisation sRGB de l'erratum WCAG (`0,04045` au lieu de `0,03928`) : **le resultat ne change pas**
(les canaux en cause, `29/255` et au-dela, sont loin de ce seuil, qui ne concerne que les valeurs
proches du noir pur). Ce n'est donc pas la cause. L'hypothese que je retenais sans la trancher :
`nadia-prof` mesurerait vraisemblablement par echantillonnage de pixels sur une capture reelle
(sujette a l'antialiasing, a la compression PNG, a un rendu colorimetrique du navigateur), alors
que je calcule a partir des valeurs hexadecimales declarees, sans jamais recharger une image.

> **Correction du 2026-08-12, ajoutee sans rien effacer** : cette hypothese aussi est fausse.
> `nadia-prof` a etabli (`FICHE-bandit-manchot-additions-cp.md`, section 4, verdict du 2026-08-12)
> que l'ecart ne venait ni d'un echantillonnage de capture ni d'une divergence de calcul, mais
> d'une paire de couleurs fausse de son cote : le cadre compare au blanc pur `#ffffff` au lieu du
> fond de page `#f4f7fb`. La valeur juste contre la page est **8,61:1**, celle que j'avais avancee ;
> son `9,25:1` est retire des fiches.

Le rapport **relatif** (`1,71 -> 3,20`, une multiplication par `1,87`) tient quelle que soit la
methode exacte utilisee - c'est lui que je recommandais deja de lire comme le fait acquis, et la
valeur absolue est desormais reconciliee plutot que restant a reconcilier.

**Geometrie des colonnes verifiee strictement identique** : `152,00 px` par colonne pour "Le bon
petit mot", `96,00 px` pour "Calcul CP" a 360 px de large, memes valeurs que sans ce changement de
variable - seule la couleur du cadre change.

## 4. Ce que je laisse ouvert pour `nadia-prof`

1. **Le jugement sur la silhouette elle-meme** : le dome de la section 1 franchit-il ou non C6 (aucun
   geste de traction) et le langage "tete ronde" qu'elle a elle-meme nomme ? Ce n'est pas ma question
   a trancher, c'est exactement celle que son arbitrage prealable doit rendre.
2. **Le repli de la section 1.4**, a activer si elle juge le polygone trop anguleux a l'oeil, une fois
   le rendu livre regarde sur un vrai ecran plutot que sur ma capture.
3. **La marge reelle de la reserve 1** apres le decalage d'ombre d'1 px de la section 2.1, sur les
   etats "avec indice" de "Le bon verbe" et de "Le bon petit mot" que je n'ai pas individuellement -
   seule une mesure sur le contenu reel, qu'elle est seule outillee a faire comme elle l'a deja fait,
   peut confirmer que la reserve 1 n'est pas aggravee au-dela de ce que j'ai mesure sur ma maquette
   synthetique a trois colonnes.
4. **La valeur absolue du contraste cadre/page** (`8,61:1` chez moi, `9,25:1` chez elle sur la valeur
   actuelle) : je nommais l'ecart sans le resoudre ; resolu depuis par sa correction du 2026-08-12
   (voir la section 3 ci-dessus) - `8,61:1` est la valeur juste.
5. **Le choix entre `#081c2c` et une autre valeur** pour `--recreo-cabinet-fond`, si elle juge que
   `3,20:1` ne suffit pas ou qu'une teinte differente de la famille de couleurs de l'application
   conviendrait mieux - je ne fais que reprendre et reverifier ma proposition de s08, jamais restee
   fusionnee.

## Ce que je remonte, en un endroit

- A `theo-archi` : la contrainte d'ordre dans `SELECTEURS_DU_CABINET` (`ADR-0033` section 7.a) est
  la sienne a poser dans le test, je ne fais que la rappeler ici pour qu'elle ne se perde pas entre ce
  document et le commit qui portera le manche.
- A `sofia-sm` : le lot technique tient dans `recreo.css` seul (`.cabinet`, `.cabinet-bandeau`,
  `.cabinet-bandeau::after`, `.cabinet-socle`, la variable `--recreo-cabinet-fond`), comme `DEC-0022`
  le prevoit ; `fragments/manche.html` n'est pas touche.
