# Proposition ines-design - rang unique de l'en-tete et etat actif de la puce de niveau

- **Auteur** : ines-design
- **Date** : 2026-08-18
- **Branche** : `s15-ines-etat-actif`, worktree dedie, cree depuis la tete de `round-two` a
  `b109f98` (`outils/verifier-lignee.sh` confirme la lignee, aucun autre commit intercale).
- **Ne modifie aucun fichier de production.** Ce document propose une forme pour deux demandes
  deja tranchees par l'utilisatrice (rang unique de l'en-tete, couleur d'etat actif sur la puce de
  niveau) ; il ne tranche ni le contrat du controleur (une donnee de niveau courant a exposer au
  gabarit, un mecanisme pour la propager sur un echange htmx qui ne swape que `#jeu`), ni
  l'arbitrage pedagogique final (`nadia-prof`), ni une question d'architecture (`theo-archi`,
  section 4).

## 0. Methode

Jar reconstruit (`bash outils/mvn-jdk21.sh -q -B -DskipTests package`, toolchain `jdk`/`21` de
l'utilisateur, `mvn` lui-meme lance sur un JDK 17+ - deux prerequis distincts du pom parent),
lance sur le JDK de la toolchain (`JAVA_HOME` positionne sur ce JDK pour cette seule commande,
jamais ecrit en dur ici, `ADR-0010`), servi sur un port local. Mesures par
`outils/mesurer-rendu.sh <largeur> <url> <png> [hauteur]`, navigateur designe par la variable
`CHROME` (un Chrome installe sur le poste, jamais son chemin ecrit ici).

Deux familles de mockup, memes feuilles reelles (`bootstrap.min.css`, `recreo.css`) chargees
depuis le jar en cours d'execution, aucune ligne de production modifiee :

1. **Mockups visuels** (`mockup-a.html`, `mockup-b.html`) : la structure candidate, jugee par
   capture d'ecran a plusieurs largeurs.
2. **Mockups de mesure** (`mesure-largeur.html`, `mesure-overflow-b.html`, `mesure-hauteur.html`) :
   un script pose dans la page candidate lit `getBoundingClientRect()`/`scrollWidth` apres
   chargement et **ecrit le chiffre exact sur la page**, capture ensuite par le meme outil - la
   lecture n'est plus une estimation visuelle mais une valeur que le navigateur calcule lui-meme.
   C'est la methode qui a mis en evidence le debordement de page entiere de la section 1.2, invisible
   sur une simple capture d'ecran (le decoupage du PNG a la largeur demandee masque un debordement
   qui existe reellement dans le document).

Aucun de ces fichiers n'est commite (ils vivent dans un repertoire de travail hors du depot).

## 1. Demande A - rang unique de l'en-tete

### 1.0 Le fait mesure qui encadre les deux variantes

Sur `accueil.html`/`recreo.css` aujourd'hui, l'en-tete tient sur deux `<nav>` empiles a
l'interieur de `.entete` : `.entete-contenu` (icone, `h1`, `.menu > .lien-accueil`) puis
`.bandeau-niveaux` (les `.chip-niveau`). Mesure JS (`getBoundingClientRect`, section 0) a 360 px :
**151 px de haut**.

**Largeurs naturelles**, mesurees par le meme moyen (`Element.getBoundingClientRect().width`,
jamais une estimation de largeur de caractere) :

| Element | Largeur |
|---|---|
| icone + titre + puce CP + puce CM1, groupe insecable (`gap: 1rem`) | **322 px** |
| bouton "Les jeux" seul | **109 px** |
| gap entre le groupe et "Les jeux" (`1rem`, valeur deja portee par `.entete-contenu`) | 16 px |
| rembourrage lateral de `.container` (Bootstrap, `--bs-gutter-x: 1.5rem`, verifie dans
  `bootstrap.min.css`) | 24 px (12 px x 2) |
| **total pour un seul rang complet** | **471 px** |

Deux seuils s'en deduisent, et se verifient tous les deux par capture a la largeur exacte
(`outils/mesurer-rendu.sh <largeur> ...`, une capture par pixel testee entre les bornes) :

- **Rang unique complet a partir de 470 px** (471 px calcule, arrondi au pixel superieur sur
  chacune des deux mesures qui le composent - l'ecart de 1 px avec la bascule observee est cet
  arrondi, pas une erreur de methode). En dessous, au moins un element passe a la ligne.
- **Le groupe icone+titre+CP+CM1 a besoin de 346 px pour ne jamais se rompre ni deborder**
  (322 px + 24 px de rembourrage). Verifie pixel par pixel : rompu/deborde a 345 px, intact a
  346-347 px selon la variante (l'ecart de 1-2 px entre les deux variantes tient au rendu
  sous-pixel de Chrome, jamais a une difference de regle).

**Correction du chiffre qui avait ete donne a l'utilisatrice.** L'estimation "retour a la ligne
force sous environ 420 px" ne se confirme pas a la mesure : la bascule reelle est **470 px**, pas
420 px. L'ecart (50 px) vient probablement d'un calcul qui n'aura pas compte le rembourrage de
`.container` et/ou le poids reel de "Recreo" en graisse 500 (heritee de `h1` Bootstrap) a 32 px -
deux valeurs qu'une addition a la main ne verifie pas aussi facilement qu'un
`getBoundingClientRect()`. La consequence pour l'utilisatrice ne change pas (le plancher de
360 px reste dans la zone a deux rangs, quelle que soit la bascule exacte), mais le chiffre cite
plus haut est celui qui doit rester dans le depot.

**Le plancher accepte de 360 px reste a deux rangs, avec une marge de 14 px** (360 - 346) avant que
le groupe icone+titre+CP+CM1 lui-meme ne casse. C'est une marge reelle mais etroite : une police
systeme legerement plus large (la pile `system-ui`/`sans-serif` heritee de Bootstrap rend
"Recreo"/"CM1" avec des largeurs qui varient d'un systeme a l'autre - Segoe UI, Roboto, San
Francisco) peut la consommer. Les deux variantes ci-dessous se comportent **a l'identique** entre
346 px et 470 px (c'est le meme total qui les gouverne) ; elles ne different que dans ce qui casse
**si cette marge de 14 px est mangee**, ce qui est le coeur du choix entre elles.

### 1.1 Variante 1 - un seul flex, sans regroupement

```css
.entete-contenu {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
  row-gap: 0.5rem;
}
.entete-contenu .lien-accueil {
  margin-left: auto;
}
```

Markup : les deux `<nav>` actuels fusionnent en un seul conteneur (icone, `h1`, les deux
`.chip-niveau`, puis `.lien-accueil`) ; `<nav class="bandeau-niveaux">` disparait comme element
distinct. Aucune composante de boite d'aucun bouton ne change (`font-size`, `min-height`,
`padding`, `border` identiques a aujourd'hui) : ce que je propose est une disposition, jamais une
taille.

**Comportement mesure, capture par capture** :

| Largeur | Rendu |
|---|---|
| >= 470 px | un seul rang : icone, "Recreo", "CP", "CM1", puis "Les jeux" plaque a droite |
| 346-469 px | deux rangs : rang 1 = icone, "Recreo", "CP", "CM1" ; rang 2 = "Les jeux" seul, **plaque a droite par le meme `margin-left: auto`** (il reste dans sa propre ligne de flex, l'auto-marge le pousse au bord droit de cette ligne) |
| 360 px (plancher accepte) | dans la zone 346-469 : rendu identique a la ligne "346-469 px" |
| < 346 px (hors plancher, jamais accepte) | **"CM1" se detache de "CP"** : le rang 1 ne garde que "icone, Recreo, CP", "CM1" tombe seul sur le rang 2 a cote de "Les jeux". Verifie exactement entre 300 et 345 px sur les captures, et de nouveau reuni a 346-347 px. |

**Ce qui casse en premier, et ce que l'enfant y perd.** Rien ne deborde jamais du viewport a
aucune largeur testee (300 a 480 px) : la mise en page reste toujours entierement visible, sans
geste de defilement. La seule defaillance est le detachement de "CM1" hors du plancher accepte :
un enfant y verrait "CM1" a cote de "Les jeux" plutot qu'a cote de "CP", ce qui casse la paire
visuelle des deux niveaux sans rien rendre inatteignable - les deux boutons restent entiers,
lisibles et pressables, seulement mal groupes. C'est un defaut de lisibilite de l'intention (la
paire de niveaux n'en a plus l'air), pas une perte de fonction.

### 1.2 Variante 2 - un groupe insecable a gauche

```css
.entete-contenu {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 1rem;
  row-gap: 0.5rem;
}
.entete-groupe-gauche {
  display: flex;
  align-items: center;
  flex-wrap: nowrap;
  gap: 1rem;
}
.entete-contenu .lien-accueil {
  margin-left: auto;
}
```

Markup : icone, `h1`, les deux `.chip-niveau` rejoignent un `<div class="entete-groupe-gauche">`
qui ne se replie jamais lui-meme (`flex-wrap: nowrap`) ; ce groupe et `.lien-accueil` sont les deux
seuls enfants directs de `.entete-contenu`, qui replie a son tour seulement entre ces deux blocs.

**Comportement mesure** : identique a la variante 1 sur toute la plage 346-470 px (meme rendu,
meme cout). En dessous de 346 px, en revanche, "CM1" ne se detache plus jamais de "CP" - mais le
groupe entier **deborde du document**, pas seulement de l'en-tete. Mesure directe (pas une lecture
d'image) : `document.documentElement.scrollWidth` depasse `document.documentElement.clientWidth`
de **33 px a 300 px et 320 px de large**, et retombe a 0 px de debordement dans les deux cas a
partir de 345-346 px. **C'est un debordement de la page entiere**, pas seulement du bandeau visuel
: dans un navigateur reel (contrairement a la capture, qui decoupe silencieusement a la largeur
demandee et masque le phenomene), cela produit une barre de defilement horizontale sur
**toute la page**, jamais seulement sur l'en-tete - exactement la classe de defaut que
`ViewportEtLargeurTelephoneTest` et `LeConteneurTierGardeSonRembourrageLateralTest` existent deja
pour empecher ailleurs dans ce depot.

**Ce qui casse en premier.** Sous 346 px, une partie du bouton "CM1" devient invisible et
inatteignable sans un geste de defilement horizontal qu'aucun autre ecran de l'application ne
demande aujourd'hui - une perte de fonction, pas seulement un defaut de groupement.

### 1.3 Comparatif et recommandation

| | Variante 1 (flex simple) | Variante 2 (groupe insecable) |
|---|---|---|
| Rendu de 346 a 470 px (couvre le plancher de 360 px) | identique | identique |
| Rendu au-dessus de 470 px | identique (un seul rang) | identique |
| Panne si la marge de 14 px est mangee | "CM1" change de voisin (visible, rien d'inatteignable) | la page entiere deborde horizontalement (une partie de "CM1" invisible sans geste) |
| Cout d'implementation | une regle, aucun element de markup ajoute | un `<div>` de groupe en plus dans le gabarit |

**Je recommande la variante 1.** Les deux variantes partagent le meme seuil (346 px) et la meme
marge (14 px) au-dessus du plancher accepte de 360 px : le choix ne se joue pas sur la plage
normale, ou elles sont rigoureusement identiques, mais sur la maniere dont chacune tombe si cette
marge disparait (police systeme plus large, zoom, futur libelle de matiere plus long a s15+). Un
regroupement mal aligne reste un defaut cosmetique reversible ; un debordement de page entiere
introduit une classe de defaut que ce depot traite par ailleurs comme bloquant. Je n'ai trouve
aucun avantage de la variante 2 qui compense ce risque a la marge.

**Hauteur totale, verifiee sans regression** (`getBoundingClientRect().height`, memes deux
structures, 360 px) : l'en-tete actuel (deux `<nav>` empiles) mesure **151 px** ; la variante 1
repliee a 360 px mesure **143 px** - **8 px de moins**, parce que le seul `row-gap` (0,5rem = 8 px)
entre les deux rangs remplace le `padding-top` + `padding-bottom` (0,5rem chacun, 16 px au total)
que `.bandeau-niveaux` portait en propre. Rien ne coute au budget du pli (`DEC-0016`,
`ADR-0028`) ; c'est un gain, mesure et non suppose.

## 2. Demande B - un etat actif sur la puce de niveau

### 2.1 Le second porteur, et pourquoi celui-ci

**Forme proposee : plein contre contour**, exactement le motif que je proposais deja en s14 pour
ce meme besoin (`proposition-ines-barre-de-niveaux.md`, section 1) - jamais code a l'epoque
(`ADR-0044` point 3 empechait l'en-tete de porter un etat de filtre), et cite depuis par
`nadia-prof` comme une forme qu'elle avait "creditee" (`FICHE-barre-entree-niveau.md`, ligne 511,
R-BARRE-3) :

- **inactif (aujourd'hui, inchange)** : fond transparent, texte et bordure blancs
  (`--recreo-accent-texte`).
- **actif (propose)** : fond plein blanc, texte et bordure dans la teinte de l'en-tete
  (`--recreo-entete-fond`).

```css
.chip-niveau.est-actif,
.chip-niveau.est-actif:hover,
.chip-niveau.est-actif:active {
  background-color: var(--recreo-accent-texte);
  color: var(--recreo-entete-fond);
  border-color: var(--recreo-entete-fond);
}
```

**Pourquoi la forme plutot qu'une teinte a luminance egale.** Un enfant qui ne distingue pas les
couleurs voit deux familles de boite radicalement differentes (une boite pleine contre une boite a
bordure), exactement la distinction que l'application applique deja partout entre une action
principale (`.demarrer`, `.continuer-partie`, aplat) et une action secondaire (`.retour-accueil`,
contour). Rien de nouveau n'est invente : c'est le meme vocabulaire visuel que l'enfant a deja
rencontre ailleurs sur l'ecran, applique ici a un troisieme usage (lequel des niveaux est
selectionne).

**Honnetete sur la limite de cette reprise.** `nadia-prof` a valide "plein contre contour" pour
distinguer une action principale d'une action secondaire (`ADR-0022`) - un usage different de
celui que je propose ici (distinguer, parmi plusieurs boutons de meme famille, lequel est
actuellement selectionne). Je reprends la meme forme parce qu'elle a deja fait ses preuves sur cet
enfant-la et que je n'ai trouve aucune raison de croire qu'elle se lirait autrement dans ce nouvel
usage, mais ce report d'un contexte a l'autre n'a jamais ete rejoue par elle sur ce cas precis :
c'est une hypothese, pas un acquis, et je la nomme comme telle plutot que de la faire passer pour
deja tranchee.

### 2.2 Contraste, chiffre

Methode : luminance relative WCAG (`(L1+0.05)/(L2+0.05)`, `L` calcule depuis les composantes sRGB
lineraisees), sur les couleurs telles qu'elles sont ecrites dans `recreo.css` aujourd'hui :

| Paire | Contraste |
|---|---|
| texte/bordure blancs sur fond d'en-tete (`--recreo-accent-texte` / `--recreo-entete-fond`) - **etat inactif actuel, deja en usage** | **9,25:1** |
| texte/bordure `--recreo-entete-fond` sur fond blanc (`--recreo-entete-fond` / `--recreo-accent-texte`) - **etat actif propose** | **9,25:1** (memes deux couleurs, inversees - le rapport de luminance est symetrique) |

Les deux etats tiennent la meme marge au-dessus du plancher de 4,5:1, avec une avance de plus du
double. Aucune couleur neuve n'est introduite : la puce active reutilise exactement les deux
teintes deja mesurees et jugees ailleurs dans ce fichier (`--recreo-entete-fond`,
`--recreo-accent-texte`), seulement inversees entre fond et texte.

**Variante alternative envisagee et ecartee ici, pour memoire.** Remplir la puce active avec
`--recreo-accent` (la teinte des boutons d'action, aplat blanc sur bleu) donnerait un contraste de
5,41:1 - toujours au-dessus du plancher, mais avec une marge trois fois plus etroite, et surtout
**la meme couleur exacte que le bouton "Jouer" (`.demarrer`) visible simultanement sur le meme
ecran** quand la liste filtree s'affiche. Je ne retiens pas cette option : elle risquerait de
faire lire la puce active comme une invitation a agir plutot que comme un rappel de ce qui est
deja selectionne, exactement le type de confusion de hierarchie de saillance que ce depot traite
par ailleurs comme un motif de refus.

### 2.3 Distinction avec les etats de survol et d'appui (`ADR-0046`)

`ADR-0046` exige une forme neutre pour `.chip-niveau:hover`/`.chip-niveau:active` : les valeurs du
repos, inchangees sous le pointeur et sous l'appui - deja en place, et je ne la modifie pas.

**J'ajoute une seconde famille d'etat, sur le meme principe.** `.chip-niveau.est-actif` a son
propre repos (plein) ; `.chip-niveau.est-actif:hover`/`.chip-niveau.est-actif:active` reprennent ce
meme repos a l'identique (la regle du bloc CSS ci-dessus les ecrit deja toutes les trois
ensemble). Consequence : il existe exactement deux apparences possibles pour une puce, jamais
quatre - contour (inactif, quel que soit le pointeur) et plein (actif, quel que soit le pointeur).

**Reponse a la question posee : comment un enfant distingue "je survole" de "c'est le filtre
courant".** Il ne peut pas les confondre, parce que **survoler ou presser une puce ne change
jamais rien a son apparence, nulle part dans cette application** (`ADR-0046` point 4, deja en
vigueur sur les cinq familles de bouton). Le plein n'est donc jamais un effet passager du geste en
cours : c'est la seule chose qui puisse le produire, puisque rien d'autre n'en produit. C'est
precisement parce que l'application n'a **aucun** retour visuel transitoire au pointeur que
l'etat "actif" ne risque de se confondre avec aucun.

**Point d'implementation a ne pas perdre, si ceci est code un jour.**
`.chip-niveau.est-actif` a la meme specificite que `.chip-niveau:hover` (deux classes, `(0,2,0)`
dans les deux cas) : sans ordre de declaration explicite, c'est la regle ecrite en dernier dans le
fichier qui l'emporterait, quelle que soit celle qui "devrait" gagner. Le bloc CSS de la section
2.1 doit donc etre ecrit **apres** la regle `.chip-niveau:hover, .chip-niveau:active` existante -
exactement le type de piege que `ADR-0046` a deja nomme une fois pour la feuille tierce, ici entre
deux regles maison.

### 2.4 Extinction quand la partie demarre

**Je propose : une extinction instantanee, sans transition ni animation dediee.** Trois motifs :

1. **Coherence avec la regle de mouvement du depot** : `ADR-0026` clause 2 limite l'animation aux
   cas explicitement arbitres et bornes (le rouleau du bandit manchot, l'arrivee par geste).
   Ajouter une transition sur l'extinction de l'etat actif ouvrirait une troisieme surface animee
   qu'aucun besoin ne motive et qu'aucun budget (`0,3s a 1,2s`, deja cite pour l'unique animation
   existante) ne couvre encore.
2. **Le changement d'ecran est deja, par lui-meme, un signal massif.** Demarrer une partie remplace
   entierement le contenu de `#jeu` (de la liste de jeux au cabinet et a l'enonce) dans le meme
   geste qui eteint l'etat actif de la puce - c'est exactement le raisonnement que `nadia-prof` a
   deja tenu pour R-BARRE-3 ("l'enfant presse et la liste change sous ses yeux dans le meme geste
   [...] plus lisible qu'une marque sur un ecran statique"). Un enfant qui vient d'appuyer sur
   "Calcul CP" regarde `#jeu`, pas l'en-tete ; l'extinction discrete d'un detail qu'il ne fixe plus
   ne lui manque pas.
3. **Le risque inverse (silencieux) me parait moins grave que celui d'ajouter du mouvement non
   arbitre.** Si l'extinction passe inapercue, l'enfant n'en tire aucune consequence fausse (il ne
   croit ni que le niveau a change, ni qu'un jeu a echoue) - c'est une information qui redevient
   utile seulement s'il revient a l'accueil, moment ou la puce est de toute facon re-evaluee par le
   controleur.

**Question que je pose plutot que de trancher** : `nadia-prof` juge-t-elle qu'un enfant qui
revient sur l'accueil apres une partie a besoin de retrouver la puce dans l'etat ou il l'avait
laissee (persistance du filtre), ou l'extinction pendant la partie doit-elle valoir aussi apres
(le filtre "CP" ne survivant pas a une sortie) ? Cette question n'est pas une question de forme :
elle determine quelle donnee le controleur doit porter (section 2.5), et je ne la tranche pas.

### 2.5 Ce qui manque cote controleur - je route, je ne resous pas

Deux constats, lus dans le code et les commentaires deja ecrits, qui empechent cette section
d'etre codee en l'etat :

1. **Aucune donnee de "niveau actif" n'existe aujourd'hui.** `PartieController.niveaux` lit le
   niveau demande (`@PathVariable String niveau`) mais ne l'ajoute au modele que pour filtrer la
   liste de jeux (`modele.addAttribute("jeux", partie.jeuxDuNiveau(niveau))`) - jamais pour le
   marquer sur une puce. Le commentaire d'`accueil.html` (ADR-0044 point 3, s14) le dit
   explicitement : *"aucun etat de filtre courant ici, l'en-tete ne peut pas etre rafraichi par un
   echange htmx - le niveau demande ne vit que dans la route que chaque puce appelle."*
2. **Meme si cette donnee existait, rien ne la propagerait jusqu'a l'en-tete.** `DEC-0012` fait de
   l'en-tete un frere permanent de `#jeu`, jamais remplace par un echange htmx qui ne cible que
   `#jeu` (`hx-target="#jeu"`, sur toutes les puces). Un chargement complet de page (navigation
   directe, pas de clic sur une puce) peut recevoir un attribut de modele frais ; un clic sur une
   puce, lui, ne rafraichit que `#jeu` et laisse l'en-tete strictement inchange, quel que soit
   l'attribut que le controleur ajouterait au modele.

**Ce que je route, et a qui** :

- **`theo-archi`** : est-ce qu'un echange htmx "hors bande" (`hx-swap-oob`, une capacite standard
  de htmx qui met a jour un element hors de la cible principale dans la meme reponse, sans
  JavaScript ecrit a la main) est une architecture acceptable pour rafraichir une puce de l'en-tete
  au meme instant qu'un clic rafraichit `#jeu` - ou si `ADR-0044` point 3 doit rester une regle
  absolue, auquel cas l'etat actif ne peut vivre que jusqu'au prochain chargement complet de page.
- **`marc-dev`/`lea-dev`** : si la reponse ci-dessus l'autorise, exposer au modele quel niveau est
  actif (et son absence pendant une partie, question 2.4) et cabler le fragment hors bande
  correspondant. Je ne l'ecris pas, je le decris comme une donnee manquante au sens de mon mandat.
- **`camille-po`** : la question 2.4 (le filtre survit-il a une sortie de partie) est un choix de
  produit autant que de forme.

### 2.6 Ce qui est perdu si `nadia-prof` refuse la couleur - le repli pour R-BARRE-3

Si l'etat actif en couleur/forme est refuse, la reserve **S14-B/R-BARRE-3** (*"rien ne dit le
filtre en cours [...] l'enfant voit six jeux et rien a l'ecran ne dit pourquoi la liste a
raccourci"*, `FICHE-barre-entree-niveau.md`) redevient sans reponse visuelle sur la puce
elle-meme. **Le repli le moins cher n'est pas sur la puce, mais dans la liste** : une ligne de
texte au-dessus de `.jeux` (par exemple "Jeux pour le CP"), portee par le fragment que la route
`/niveaux/{niveau}` rend deja a chaque clic.

**Pourquoi ce repli coute structurellement moins cher que la couleur de la puce.** Le nom du niveau
est deja lu par le controleur a cet instant precis (`@PathVariable String niveau`, meme methode) ;
il suffit de l'ajouter au modele existant, dans la reponse qui **remplace deja `#jeu`** a chaque
clic - aucun mecanisme hors bande, aucune question ouverte a `theo-archi`. C'est une phrase de
plus dans un fragment deja swape, jamais une donnee qui doit franchir la frontiere de l'en-tete
que `DEC-0012` protege. Le libelle exact ("Jeux pour le CP", ou une autre formulation) reste un
choix de redaction pour un enfant qui commence a lire, donc a `nadia-prof`.

## 3. Questions pour `nadia-prof`

1. **Rang unique, variante.** Je recommande la variante 1 (section 1.3). Est-ce l'arbitrage a
   retenir, ou le risque de la variante 2 (debordement de page entiere sous le plancher) pese-t-il
   moins, a ses yeux, que celui de la variante 1 (detachement visuel de "CM1")?
2. **Le report du plein/contour a un nouvel usage (section 2.1).** J'ai deja fait juger cette forme
   pour distinguer une action principale d'une secondaire ; je la reutilise ici pour distinguer,
   parmi des boutons de meme famille, lequel est actif. Ce report tient-il, ou un enfant de CP
   lirait-il "plein" comme "c'est ce qu'il faut presser maintenant" plutot que "c'est ce qui est
   deja choisi" ?
3. **L'extinction silencieuse (section 2.4).** Je propose de ne rien animer et de laisser le
   changement d'ecran porter seul le signal. Est-ce suffisant, ou un enfant qui reviendrait vite a
   l'accueil a-t-il besoin que l'etat "avant" et l'etat "apres" different visiblement l'un de
   l'autre plutot que de dependre de ce qu'il a vu passer ?
4. **La persistance du filtre apres une sortie (section 2.4, question posee a `camille-po`
   egalement).** Le filtre doit-il survivre a une partie terminee ou abandonnee, ou repartir a
   zero ?
5. **Le repli textuel (section 2.6).** Si la couleur de la puce est refusee, la phrase au-dessus de
   la liste est-elle une reponse suffisante a R-BARRE-3, ou cette reserve doit-elle rester ouverte
   jusqu'a ce qu'un second axe de filtre force la main (le seuil qu'elle a elle-meme date au
   2026-08-17) ?

## 4. Ce que je route, et a qui

- **`theo-archi`** : la question d'architecture de la section 2.5 (un echange htmx hors bande pour
  rafraichir l'en-tete est-il acceptable au regard d'`ADR-0044` point 3 et de `DEC-0012`).
- **`marc-dev`/`lea-dev`** : le contrat de donnees manquant (section 2.5) une fois la question
  d'architecture tranchee ; les deux fichiers de test qui nomment deja la structure actuelle de
  l'en-tete et qu'un changement de markup (section 1) devrait mettre a jour dans le meme commit -
  `LEntreeDeMenuNeSurvitPasAUnEcranDeJeuTest` et `AucunEtatDePressableNEstAbandonneAuTiersTest`
  (les deux seuls, sur recherche du depot, a nommer `.chip-niveau` ou `.bandeau-niveaux`).
- **`camille-po`** : si le filtre de niveau doit survivre a une sortie de partie (section 2.4).
- **`nadia-prof`** : l'arbitrage complet de ce document (section 3), avant tout code.

## 5. Tete de branche

`s15-ines-etat-actif`, worktree dedie, descendant direct verifie de la tete de `round-two`
(`b109f98`) sans aucun autre commit intercale (`outils/verifier-lignee.sh`).
