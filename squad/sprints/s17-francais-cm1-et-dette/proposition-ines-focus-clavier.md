# Proposition - indicateur de focus clavier sur les pressables (S17-1)

- **Auteur** : ines-design.
- **Repond a** : `S17-1` (`docs/backlog/backlog.md`), reserve **S16-A** de `nadia-prof`
  (`squad/conformite/FICHE-barre-entree-niveau.md`, section "Constat du 2026-08-19 (s16) sur
  S16-3 et S16-4", points 3 et 4).
- **Ce que ce document fait** : il propose une forme, il ne la tranche pas. Aucun code de
  production n'est modifie par ce lot. `nadia-prof` garde seule le veto pedagogique ; `theo-archi`
  garde seul l'avis de faisabilite et la mise a jour d'`ADR-0046`/`ADR-0030` qu'un choix ici
  entrainerait.

## 0. Le defaut, rappele avec sa cause exacte

Sur les sept pressables d'un ecran de manche - et sur tout autre pressable de l'application - la
regle de focus clavier de la feuille tierce fait deux choses dans le meme bloc :
`outline: 0` (elle retire le contour natif) puis `box-shadow: var(--bs-btn-focus-box-shadow)`
(elle le remplace par une ombre). Cette seconde variable n'est definie que par les dix-sept
classes de variante de la bibliotheque, qu'aucun gabarit de ce depot ne porte : l'ombre retombe a
rien, et le contour a deja ete retire. **Aucun des pressables de ce produit n'affiche donc
aujourd'hui le moindre indicateur de focus clavier** - ce n'est pas une regression locale, c'est
un etat qui n'a jamais existe sur ce produit.

## 1. La population couverte - une regle de mecanique, pas de contenu

Les deux blocs `:hover, :active, :focus-visible` deja ecrits dans `recreo.css` (`ADR-0046`, et son
second amendement de s16) definissent deja la population exacte des pressables du depot, derivee
de `TailleDesTextesEtBoutonsTest` - je la reprends a l'identique, je n'en invente pas une
troisieme :

- `.continuer-partie`, `.demarrer`, `.proposition`, `.recommencer`, `.sortie-pleine` (les cinq
  boutons pleins - confirmation, lancement de manche, propositions de reponse, ecran d'erreur, fin
  de partie) ;
- `.proposition.deja-essayee` (sujet compose, distinct au sens d'`ADR-0046`) ;
- `.retour-accueil` (sortie sur un ecran de jeu) ;
- `.lien-accueil` (sortie d'en-tete hors ecran de jeu) ;
- `.chip-niveau`, et `.chip-niveau.est-actif` sous `body:has(.filtre-niveau)` (puce simple et puce
  active).

C'est exactement la liste que la question pose - les sept pressables d'une manche (les deux puces,
la sortie, les trois propositions, la proposition marquee) et les boutons des autres ecrans
(confirmation, fin de partie, erreur). **Aucune des deux variantes ci-dessous ne cite un nom de
jeu, un contenu ou une matiere** : la regle s'ecrit sur les memes selecteurs deja portes par la
mecanique generique des pressables, jamais sur un bouton particulier.

## 2. Le mecanisme commun aux deux variantes

Dans les deux cas, la feuille maison declare un **anneau maison**, chez chaque groupe de
selecteurs ci-dessus, uniquement a `:focus-visible` (jamais a `:hover` ni `:active`, qui restent
la forme neutre deja arbitree). L'anneau utilise `currentColor` : la couleur du texte de l'element
au repos, jamais une teinte neuve.

**Pourquoi `currentColor` et pas une teinte choisie** : chaque paire texte/fond de ce depot a deja
ete mesuree et actee par `nadia-prof` ou `theo-archi` a un autre titre (F9, F10, S10, le second
arbitrage de s15). Reprendre exactement cette meme couleur pour l'anneau, **peinte sur le fond de
l'element lui-meme**, ne cree aucune paire neuve a justifier : le contraste de l'anneau contre son
fond immediat est, par construction, celui du texte contre ce meme fond - deja mesure. C'est la
raison technique du choix, pas une esthetique.

## 3. Contraste - chiffre et methode

Methode identique a celle de s15 (`FICHE-barre-entree-niveau.md`, section 1, chiffres deja repris
par `nadia-prof`) : formule WCAG de contraste sur luminance relative, calculee a la main sur les
valeurs hexadecimales declarees dans `recreo.css`, aucun navigateur requis pour ce calcul. Je ne
mesure pas le rendu - je ne le peux pas sur ce poste - je calcule sur les memes valeurs que la
feuille livrerait.

**Seuil retenu** : WCAG 2.1, critere 1.4.11 (contraste des composants non textuels), plancher
**3:1**, puisqu'un anneau de focus est un indicateur d'interface, pas du texte a lire (le plancher
texte, 4,5:1, est atteint partout aussi, sans que ce soit l'exigence pertinente ici).

| Pressable | Paire (anneau `currentColor` / fond immediat) | Contraste |
|---|---|---|
| `.continuer-partie`, `.demarrer`, `.proposition` (fond de base), `.recommencer`, `.sortie-pleine` | `#ffffff` / `#2e6f9e` | **5,41:1** |
| `.chip-niveau` (repos), `.lien-accueil` | `#ffffff` / `#1d4b6b` | **9,25:1** |
| `.chip-niveau.est-actif` | `#1d4b6b` / `#ffffff` | **9,25:1** |
| `.retour-accueil` | `#2e6f9e` / `#f4f7fb` | **5,03:1** |
| `.proposition` (positions 1 a 4), `.proposition.deja-essayee` | `#1c2733` / `#bdd2ef`, `#a9dcd8`, `#dcc8ed`, `#eec1d8` | **9,82 / 10,03 / 9,75 / 9,55:1** |

Les quatre dernieres valeurs de propositions sont identiques a celles que `nadia-prof` cite deja
en s10 pour le texte de ces memes boutons (S10, F10) : c'est attendu, puisque l'anneau reprend le
meme texte sur le meme fond. Le plus bas de tous, `.retour-accueil` a **5,03:1**, reste 67 % au-
dessus du plancher de 3:1.

## 4. Deux variantes, chiffrees, et ce qui casse en premier dans chacune

### Variante 1 - anneau interieur (`box-shadow: inset ... currentColor`)

`box-shadow: inset 0 0 0 0.1875rem currentColor;` (3 px), a `:focus-visible` seulement, sur les
memes groupes de selecteurs. Peint a l'interieur de la boite du bouton, sur son propre fond -
c'est la variante dont les chiffres de la section 3 sont directement valides, puisque l'anneau
reste sur le fond dont la paire est deja mesuree.

**Cout en place** : nul sur la mise en page. `*, *::before, *::after { box-sizing: border-box }`
est deja la regle universelle du depot (`recreo.css`, ligne 45) et `min-height`/`min-width`
incluent deja le rembourrage, jamais l'inverse (meme commentaire, `S2-1`) : un `box-shadow` ne
participe jamais au calcul de boite, avec ou sans ce reglage, mais je le cite parce que c'est la
meme logique qui garantit qu'aucune boite de pressable ne change de taille. Le pli mesure par
`nadia-prof` a `y = 411` (`FICHE-barre-entree-niveau.md`, section "S15-7") n'est pas concerne :
aucun pixel de mise en page ne bouge. Le cout reel est un **rembourrage visuel mange de 3 px sur
chaque bord** au moment du focus - sur le plus etroit (`.chip-niveau`, 0,5rem soit 8 px de
rembourrage vertical), il reste 5 px visibles, ce qui laisse le texte respirer sans le toucher.

**Ce qui casse en premier** : si un bouton venait a exister avec un rembourrage inferieur a 3 px
sur un axe, l'anneau toucherait le texte. Ce n'est le cas d'aucun pressable existant (le plus
serre est `.chip-niveau` a 8 px). C'est une limite a surveiller a chaque pressable neuf, pas un
defaut actuel.

### Variante 2 - anneau exterieur (`outline`, avec decalage)

`outline: 0.1875rem solid currentColor; outline-offset: 0.125rem;`, a `:focus-visible` seulement.
Techniquement plus proche du contour natif disparu (3 px, la valeur meme que la fiche de
`nadia-prof` cite comme repos observe avant regression) et charge cognitivement moins l'enfant
puisqu'il retrouve la forme qu'un contour de focus a "toujours" - sur un ordinateur qui en aurait
deja montre un ailleurs.

**Cout en place** : `outline` ne participe pas non plus au calcul de boite (aucun pixel de mise en
page ne bouge, meme argument que la variante 1, le pli reste a `y = 411`), mais il **peint hors de
la boite du bouton, sur ce qui est derriere**, et c'est la que `currentColor` casse :

| Ou l'anneau exterieur tombe | Paire | Contraste |
|---|---|---|
| `.proposition` en premiere ou derniere colonne, contre le fond du cabinet dans l'espacement de 1rem qui separe les colonnes | `#1c2733` / `#081c2c` | **1,14:1** |
| `.demarrer`, `.recommencer`, `.sortie-pleine` rendus hors du cabinet, contre le fond de page autour du bouton | `#ffffff` / `#f4f7fb` | **1,07:1** |

**Ce qui casse en premier, et c'est immediat** : l'anneau devient quasiment invisible des qu'il
sort de la boite dont le fond a ete mesure, parce que ce depot juxtapose des fonds tres contrastes
entre eux (page claire, en-tete sombre, cabinet tres sombre) a des endroits ou un pressable est a
la fois pres du bord et pres d'un autre fond. `currentColor` ne peut pas tenir cette variante :
il faudrait soit une teinte fixe verifiee contre **tous** les fonds environnants possibles (aucune
des teintes du depot ne passe les deux cas ci-dessus a la fois, voir le calcul), soit un double
anneau (un trait clair et un trait sombre l'un contre l'autre, comme un contour de focus a deux
couleurs) - ce qui double le nombre de declarations et introduit une forme neuve que je n'ai pas
chiffree ici parce que la variante 1 repond au meme besoin sans ce cout.

## 5. Ma recommandation

**Variante 1 (anneau interieur, `currentColor`).** Elle est la seule des deux dont le contraste
est garanti par construction sur tous les pressables de la population, sans introduire de teinte
ni de paire neuve a mesurer, et son seul cout - le rembourrage visuel mange de 3 px - ne touche
aucun pressable existant en dessous de son plancher. La variante 2, plus proche du contour natif
perdu, casse des qu'un pressable est pres d'un bord dont le fond contraste avec le sien, ce qui est
frequent sur ce depot (premiere et derniere colonne du cabinet, boutons hors cabinet sur fond de
page) et n'a pas de correction a cout nul.

## 6. La premisse "rien ne change sous le pointeur" - elle ne s'applique pas ici, et je dis pourquoi

`nadia-prof` a accepte l'aplat de la puce active le 2026-08-18 sur une premisse **globale** :
*"survoler ou presser une puce ne change jamais rien a son apparence, nulle part dans cette
application"* (`FICHE-barre-entree-niveau.md`, section 4). Elle a refuse ma variante 2 de
l'epoque (un contour au survol/appui de la proposition deja essayee) precisement parce qu'elle
aurait rendu cette phrase fausse.

**Le focus clavier n'est pas couvert par cette phrase, et il ne devrait pas l'etre.** La phrase
nomme deux etats precis - *survoler* et *presser* - qui sont tous deux **des consequences d'un
pointeur**. Le focus clavier n'est ni l'un ni l'autre : c'est un troisieme etat, produit par une
touche de tabulation, qui existe independamment de tout pointeur et qui n'a jamais ete cite dans
l'accord du 2026-08-18. `nadia-prof` l'a elle-meme etabli le 2026-08-19 en le mesurant : la
proposition marquee garde une apparence identique au repos **et** au focus clavier sur les deux
proprietes qu'elle avait posees en condition (S15-8, S15-9) - ce que ma proposition ne touche pas.

Ce que ma proposition ajoute est une **quatrieme propriete**, disjointe des trois que la premisse
protege (couleur de fond de position, texte, trait) : un anneau, qui n'existe qu'a `:focus-visible`
et nulle part a `:hover` ni `:active`. La premisse continue donc de tenir exactement ou elle a ete
posee - rien ne change sous le pointeur, sur aucune des proprietes qu'elle nommait - et le focus
clavier reste un etat distinct, qui n'a jamais ete couvert par elle et que la reserve S16-A demande
justement de rendre visible.

**Comment un enfant distingue les trois etats.** Avec la forme neutre deja en vigueur sur `:hover`
et `:active`, et l'anneau ajoute uniquement a `:focus-visible` :

- au survol (souris) : rien ne change - c'est deja le cas, hors de portee de mesure sur ce depot,
  mais c'est la regle ecrite ;
- a l'appui (doigt ou clic) : rien ne change non plus ;
- au focus clavier : l'anneau apparait, et lui seul. Un enfant au clavier voit donc une forme qui
  n'apparait a aucun autre moment de son parcours - ce n'est pas une nuance parmi d'autres, c'est
  le seul signal des trois etats qui produit un changement visible.

`:focus-visible` (par opposition a `:focus`) ne s'active en general pas sur un focus produit par un
clic de souris dans les navigateurs qui l'implementent - c'est le mecanisme meme qui a permis a
`nadia-prof` de verifier l'etat clavier sans le confondre avec un clic. Je ne l'affirme pas comme
une propriete garantie par ce depot, cette heuristique appartient au navigateur et non a la feuille
maison, mais elle va dans le meme sens que la regle : l'anneau reste un signal du clavier et non de
la souris.

## 7. Ce qui se perd si `nadia-prof` refuse

Si aucune des deux variantes n'est retenue, ou si une forme differente est demandee et qu'aucune
n'est arbitree avant la fin du sprint : **l'etat actuel demeure**, c'est-a-dire rien. Un enfant qui
navigue au clavier continue de presser une proposition, une puce ou une sortie sans savoir
laquelle est actuellement selectionnee avant d'appuyer. Aucune des conditions S15-8/S15-9 n'est
menacee par ce statu quo - elles portent sur d'autres proprietes - mais la reserve S16-A reste
ouverte, et `theo-archi` ne peut pas corriger `ADR-0046` (le declencheur qu'il a lui-meme ecrit
attend "que l'une des deux repondent" - `nadia-prof` et moi).

## 8. Ce que je route a `theo-archi`

Je ne tranche ni la faisabilite ni la mise en oeuvre. Deux points que je nomme pour que `sofia-sm`
les route :

- `ADR-0046` point 4 exclut aujourd'hui `:focus-visible` de la derivation de couleurs par un motif
  qui n'a plus lieu d'etre a l'identique (le "declencheur ecrit" y est deja pose) : la variante
  retenue ici ajoute une propriete neuve (`box-shadow` ou `outline`), pas une couleur, et reste
  hors du perimetre compare par `AucunEtatDePressableNEstAbandonneAuTiersTest` (box-shadow deja
  ecartee des proprietes comparees a cet etat) - a `theo-archi` de confirmer que la regle neuve
  n'a donc pas besoin d'etendre ce garde-fou, ou qu'elle le doit.
- `ADR-0030` point 2 refusait `overflow: hidden` sur le cabinet pour proteger l'anneau de focus
  **de la bibliotheque** - anneau dont on sait depuis le 2026-08-19 qu'il ne rendait jamais rien.
  Le refus lui-meme reste juste avec la variante 1 ou 2 : l'anneau maison propose ici serait rogne
  de la meme facon par un `overflow: hidden` sur un ancetre. Mais sa justification doit etre
  corrigee dans le meme geste que la fiche de `nadia-prof` corrige la sienne : ce n'est plus "cet
  anneau existe et fonctionne", c'est "cet anneau n'existait pas, et celui-ci le remplace".

## 9. Ce que je ne peux pas verifier moi-meme

Je n'ai pas lance de rendu pour cette proposition : les chiffres de la section 3 et 4 sont
calcules a la formule sur les valeurs hexadecimales de `recreo.css`, comme au sprint 15, pas
mesures sur un navigateur. Je n'ai pas verifie l'ordre de tabulation, ni si un pressable porte deja
une autre propriete qui entrerait en collision visuelle avec l'anneau sur un ecran precis. Ces
deux points appartiennent a la sonde de `nadia-prof` (appel du focus, lecture du style calcule),
qu'elle a etablie pouvoir produire depuis le 2026-08-19.

## Mes questions a `nadia-prof`

1. La variante 1 (anneau interieur, 3 px, `currentColor`) est-elle la forme que vous arbitrez, ou
   preferez-vous une epaisseur differente - et si oui, laquelle, pour que je verifie qu'elle reste
   au-dessus de 3:1 sur les cinq paires de la section 3 ?
2. Le raisonnement de la section 6 - la premisse "rien ne change sous le pointeur" ne couvre pas
   le focus clavier parce que ce n'est pas un pointeur - vous convient-il, ou faut-il l'etendre
   d'une facon que je n'ai pas vue ?
3. Sur `.proposition.deja-essayee` precisement : l'anneau a `:focus-visible` s'ajoute sans toucher
   au fond de position ni au trait (S15-8, S15-9 restent intacts par construction, la propriete
   ajoutee est disjointe) - confirmez-vous que c'est bien ce que vous souhaitez voir, ou la
   proposition deja essayee doit-elle rester strictement identique a son repos, anneau compris ?
4. Le mecanisme technique (`box-shadow` maison plutot que reparer la variable de la bibliotheque)
   reste a `theo-archi` : avez-vous une preference de principe entre "reparer ce que la
   bibliotheque promettait" et "declarer un anneau maison independant", avant qu'il ne tranche la
   faisabilite ?
