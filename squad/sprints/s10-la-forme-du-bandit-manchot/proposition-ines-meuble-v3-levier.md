# Proposition ines-design v3 - le levier seul : ancrages et embase rendus, regardes un par un

Repond au retour sur le rendu reel de la v2 : le levier en gris acier, a mi-hauteur du flanc, se lit
comme un curseur de defilement - exactement la lecture que `nadia-prof` avait predite pour la
variante *dans la paroi* et refusee pour cette raison, alors qu'elle avait valide la silhouette
*hors de la paroi* comme se lisant "poignee". Sa prediction ne survit pas au rendu : je ne
deduis plus, je rends et je regarde, six variantes, comme elle l'a fait elle-meme sur les siennes.

Le visage n'est pas retouche - explicitement demande. La teinte du levier n'est pas retouchee non
plus : `#8e8e8e`, `2,82:1` contre la bordure, `3,05:1` contre la page, inchangee. Seuls la position
et le dessin du levier changent d'une variante a l'autre.

## Methode

Fragment reellement servi de "Le bon verbe" (item *"Vous ___ du chocolat."*, `mangeons` present),
reassemble avec les deux feuilles reelles et une feuille candidate, capture par l'equivalent Python
direct de `outils/mesurer-rendu.sh` deja utilise dans mes deux notes precedentes. Chaque variante
est regardee a taille reelle **et** agrandie x6 (recadrage + redimensionnement sans interpolation),
comme `nadia-prof` le fait. Le cout (contour du cabinet, largeur de colonne, chevauchement avec une
proposition) est mesure sur chacune par balayage de couleur exacte, pas suppose.

**Cout, verifie une fois pour toutes les six variantes** : aucune ne touche `.propositions`, la
largeur de bordure ni aucun rembourrage. Le contour du cabinet reste `x = 12..347/348`,
`y = 317..538` sur les six rendus, au pixel pres - identique a l'etat sans levier. F5 et F6 restent
sans objet, je ne les rouvre pas.

## Six variantes, chacune avec sa lecture

### A - Ancrage a mi-hauteur, fut long (8 px) + embase etroite (la v2 telle que jugee)

Fut de 8 px sur ~56 px de haut, boule de 18 px, une petite plaque de 22 x 8 px ajoutee a la base.
**Lecture : curseur de defilement.** L'ajout de la plaque ne change rien - agrandie, elle se lit
comme la butee basse d'une piste de defilement, pas comme une fixation. C'est le rendu que
`sofia-sm` a deja juge, confirme a l'identique.

### B - Ancrage plus bas sur le flanc (75 % de la hauteur), meme fut long

Meme forme que A, translatee vers le bas d'un quart de la hauteur du cabinet. **Lecture :
identique, toujours un curseur** - simplement une position differente sur la meme piste. Deplacer
un curseur de defilement le long de sa piste ne le transforme pas en autre chose.

### C - Ancrage en bas du flanc, contre la plinthe, meme fut long

`.cabinet-levier` ancre par `bottom: 0` plutot que par un pourcentage de hauteur : la plaque touche
desormais le bord bas du cabinet, au niveau du socle. **Lecture : mieux, mais insuffisant seule.**
La boule se rapproche du coin et la plaque touche une limite reelle (le bas du meuble) plutot que de
flotter, ce qui attenue la lecture de curseur - mais le long fut mince qui la relie au sommet reste
la piste de defilement vue en C, seulement arretee en bas plutot qu'au milieu (l'etat "scrolle tout
en bas" d'un curseur, qui est encore un curseur).

### D - Ancrage en haut du flanc, pres du fronton, meme fut long

**Lecture : la pire des quatre.** La boule se pose pres du coin superieur, mais le long fut et la
plaque du bas se retrouvent visuellement detaches d'elle, flottant au milieu du flanc sans rien
rejoindre ni en haut ni en bas. Je lis deux objets disjoints - une boule au coin, une barre
flottante plus bas - plutot qu'un seul levier.

### E - Ancrage contre la plinthe, fut COURT (7 px) + plaque large et visible (24 x 12 px, arete haute et basse)

Rupture avec les quatre precedentes : le fut est reduit a un moignon (`0,4375rem`, environ 7 px sur
14 px de haut au lieu de 56), et la plaque a la base est large (`1,5rem`, 24 px, contre `1,375rem`
pour la boule elle-meme) et visiblement biseautee (un filet clair en haut, un filet sombre en bas).
Ancre par `bottom: 0`, dans le rembourrage du `.container`, au niveau du socle.

**Lecture : un objet compact, mont​é sur une platine.** Agrandi x6, je lis une boule posee sur une
courte tige elle-meme fixee sur une plaque a rebord visible - une piece rapportee, montee sur le
meuble, et non un element qui glisse le long d'un bord. C'est la premiere des six variantes ou la
plaque se lit comme une **fixation** plutot que comme la butee d'une piste.

**Cout, mesure specifiquement sur celle-ci** puisque la plaque est plus large que les precedentes :
contour du cabinet inchange (`12..347`), colonne `mange` inchangee (bbox `188..339`). **Zero
chevauchement avec une proposition**, verifie ligne par ligne : aux rangees ou la plaque atteint sa
largeur maximale (`y = 522` a `528`), aucune proposition n'est plus rendue a cet endroit - la plaque
vit entierement dans la zone du socle, sous la derniere rangee de boutons, jamais a cote d'elle.
Le gris et le rose de "mange" ne se recouvrent a aucune ligne, sur toute la hauteur mesuree.

### F - Meme fut court + plaque, mais ancre a mi-hauteur (pour isoler forme et position)

Je teste la forme d'E **sans** son ancrage, pour savoir lequel des deux fait le travail. **Lecture :
meilleure qu'A, moins bonne qu'E - un compromis, pas une reponse.** La boule et son moignon sont
moins allonges qu'en A, donc moins clairement une piste, mais l'ensemble flotte encore au milieu du
flanc sans toucher aucune limite reelle du meuble, et cette position isolee reste celle d'un
curseur ou d'un bouton d'action flottant plutot que d'une fixation. **Conclusion de la comparaison
E/F : la forme compacte aide, mais c'est l'ancrage contre une limite reelle (la plinthe) qui fait
la difference decisive.**

## Ce qui ne marche pas, dit comme demande

Si `nadia-prof` ou l'utilisatrice juge que E se lit encore comme un element d'interface plutot que
comme une piece de machine, alors les six variantes rendues ici montrent que **ni la position seule
(A a D), ni la forme seule (F), ni leur combinaison au niveau le plus travaille que j'ai rendu (E)**
ne suffisent a couper radicalement le rapprochement - et ce serait une information sur ce que
18 px de flanc peuvent porter, pas un echec de ma recherche : je n'ai pas trouve de septieme
variante meilleure qu'E dans le temps donne, et je le dis plutot que d'en inventer une sur le
papier sans l'avoir rendue.

## Recommandation

**E, sans hesitation parmi les six.** C'est la seule ou la plaque se lit comme une fixation et non
comme une butee de piste, et la seule ou la boule ne flotte pas dans un espace vide du flanc. Cout
nul, chevauchement nul, teinte inchangee (`2,82:1` / `3,05:1`, jamais retouchee comme demande).

```css
.cabinet-levier {
    position: absolute;
    right: -1.125rem;
    width: 1.5rem;
    bottom: 0;
    height: 2.25rem;
}

.cabinet-levier-embase {
    position: absolute;
    left: 50%;
    bottom: 0;
    width: 1.5rem;
    height: 0.75rem;
    transform: translateX(-50%);
    background-color: var(--recreo-levier-teinte);
    border-radius: 0.125rem;
    box-shadow:
        inset 0 0.0625rem 0 rgba(255, 255, 255, 0.25),
        inset 0 -0.0625rem 0 rgba(0, 0, 0, 0.25);
}

.cabinet-levier::before {
    content: "";
    position: absolute;
    left: 50%;
    top: 0;
    bottom: 0.75rem;
    width: 0.4375rem;
    transform: translateX(-50%);
    background-color: var(--recreo-levier-teinte);
    border-radius: 999px;
}

.cabinet-levier::after {
    content: "";
    position: absolute;
    left: 50%;
    top: -1.125rem;
    width: 1.125rem;
    height: 1.125rem;
    transform: translateX(-50%);
    background-color: var(--recreo-levier-teinte);
    border-radius: 50%;
    box-shadow: inset 0 -0.1875rem 0 rgba(0, 0, 0, 0.18);
}
```

`<div class="cabinet-levier" aria-hidden="true"><span class="cabinet-levier-embase"></span></div>` -
un enfant de plus dans le gabarit par rapport a la v2 (le `span` de la plaque), meme famille que les
deux enfants deja portes par le bandeau. Ni `button`, ni `a`, aucun `data-role`, aucune regle
`:hover`/`:active`/`:focus`, hors du parcours clavier par construction.

## Ce que je remonte

- **A `nadia-prof`** : le jugement final sur E reste le sien, sur le rendu reel et non sur ma
  lecture - exactement ce que l'utilisatrice a tranche pour le visage, et je l'applique ici aussi
  sans qu'on ait besoin de me le redemander.
- **A `theo-archi`** : `.cabinet-levier-embase` entre dans le meme regime que `.cabinet-levier` -
  frere non ancetre d'un pressable, aucun `clip-path` ni `overflow` a garder hors de cette classe,
  entree correspondante dans `SELECTEURS_DU_CABINET`.
- **Si E est aussi refusee** : je n'ai plus de septieme variante en reserve pour ce tour-ci. La
  question reviendrait alors comme demande a l'utilisatrice, chiffree par les six lectures
  ci-dessus plutot que par une septieme improvisee.

Je ne fusionne pas, je ne pousse pas.
