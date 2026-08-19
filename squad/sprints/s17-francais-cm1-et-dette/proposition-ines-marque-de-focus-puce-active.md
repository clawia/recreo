# Proposition - marque de focus clavier sur la puce de niveau active (S17-A)

- **Auteur** : ines-design.
- **Repond a** : `S17-A` (bloquante), `squad/conformite/FICHE-barre-entree-niveau.md`, section "Sonde
  post-livraison du 2026-08-19 (s17)", sous-sections 3 et 5.
- **Ce que ce document fait** : il propose une regle de style pour une seule famille de pressable
  (la puce de niveau active, `body:has(.filtre-niveau) .chip-niveau.est-actif`), a l'etat
  `:focus-visible`. Aucun code de production n'est modifie. `theo-archi` garde le mecanisme (comment
  isoler cette regle de celle, partagee, des neuf autres familles sans rien y casser) ; `nadia-prof`
  garde seule le jugement sur le rendu reel.
- **Ce que je ne fais pas** : je ne mesure pas de rendu (aucun navigateur sur ce poste), je ne
  tranche pas, je ne touche a aucune feuille ni gabarit de production. Je ne remets en cause aucune
  des neuf familles deja fermees par la sonde de `nadia-prof` (`S16-A`), ni `S15-8`/`S15-9`, ni
  `ADR-0046`. Je ne fais rien dependre de l'etat `:active` : `S17-D` n'est pas mesure comme corrige,
  et ma proposition ne cite ni ne modifie `:hover` ni `:active`.

## 0. Le diagnostic exact - pourquoi `currentColor` s'effondre sur cette seule famille

Les neuf familles fermees par `nadia-prof` partagent une propriete que je n'avais pas nommee
explicitement dans ma proposition initiale (`proposition-ines-focus-clavier.md`, section 2) parce
qu'elle allait de soi partout ailleurs : **`currentColor` (la couleur du texte au repos) differe
toujours de ce qui l'entoure immediatement.** Sur un bouton plein, le texte est blanc et le fond est
bleu (5,41:1). Sur une proposition, le texte est sombre et le fond est pastel (9,82:1). Sur les trois
boutons a contour au repos (inactifs), le texte est blanc et ce qui apparait par transparence est le
bandeau sombre (9,25:1).

**Sur la puce de niveau active, cette propriete casse, et c'est la seule ou elle casse** : l'etat actif
(S15, quatrieme arbitrage de l'utilisatrice) inverse deliberement les couleurs - fond blanc, texte et
bordure `--recreo-entete-fond` (`#1d4b6b`) - et le bandeau qui l'entoure porte **cette meme variable**.
`currentColor` a cet endroit vaut donc exactement la couleur de la bordure et celle du bandeau : trois
surfaces confondues. L'anneau (`box-shadow: inset ... currentColor`) peint en `#1d4b6b` **a l'interieur
du remplissage blanc, juste sous la bordure** : il ne cree aucune frontiere de couleur avec ce qui le
touche a l'exterieur (la bordure, puis le bandeau, tous deux `#1d4b6b`) - seule la limite avec le
blanc restant, plus loin a l'interieur, reste visible. C'est exactement la mesure de `nadia-prof` :
1,00:1 contre le pixel immediatement exterieur, et un effet percu de retrecissement plutot que
d'apparition.

**Ce que j'en tire pour la forme** : je ne peux pas corriger ce defaut en gardant `currentColor` sur
cette famille, puisque `currentColor` est precisement la valeur qui est devenue indistincte de son
environnement. Il faut soit une couleur declaree differente, soit peindre ailleurs que sur le cote qui
s'est effondre.

## 1. Ce que j'ai verifie avant de choisir une couleur - la fenetre est trop etroite pour un anneau interieur recolore

J'ai cherche si une couleur declaree, differente de `currentColor`, peut remplacer l'anneau **sans
changer sa geometrie** (toujours interieur, `inset`, sur le remplissage blanc). Une telle couleur doit
alors contraster suffisamment **des deux cotes a la fois** : contre le blanc du remplissage restant
(cote interieur) pour rester visible comme un trait distinct, et contre `#1d4b6b` (bordure et bandeau,
cote exterieur) pour satisfaire la condition de fermeture de `nadia-prof`.

Formule WCAG (memes valeurs intermediaires que ma proposition precedente, canaux lineaires,
`contraste = (L1 + 0,05) / (L2 + 0,05)`) :

- `#1d4b6b` -> R=29, G=75, B=107 -> R_lin=0,012288, G_lin=0,070381, B_lin=0,147070 -> **L=0,063569**
- blanc `#ffffff` -> **L=1,000000**

Pour qu'une couleur de luminance `L` tienne 3:1 des deux cotes a la fois :
- contre `#1d4b6b` : `(L + 0,05) / (0,063569 + 0,05) >= 3` => `L >= 0,290707`
- contre le blanc : `(1,05) / (L + 0,05) >= 3` => `L <= 0,300000`

**La fenetre qui satisferait les deux est `[0,290707 ; 0,300000]`** - une largeur de 0,0093 sur une
echelle de 0 a 1, soit moins de 1 % de l'intervalle. J'ai cherche une teinte reelle dans cette fenetre
pour verifier si elle existe : un gris `#949494` (148, 148, 148) donne `L = 0,29614`, au centre de la
fenetre. Ses deux contrastes : contre `#1d4b6b`, **3,05:1** ; contre le blanc, **3,03:1**. **Les deux
marges sont sous 2 %** - un ecart d'arrondi du type que `nadia-prof` a deja trouve sur mon calcul
precedent (14,08 contre son 14,09) suffirait a faire tomber l'un des deux sous 3:1 selon qui
recalcule, et elle a deja refuse une valeur "a peine visible" pour ce motif exact (le contour de
0,0625rem de la puce active, section arbitrage du 2026-08-18). **Je n'ai pas de teinte a proposer sur
ce chemin-la avec une marge que je jugerais defendable.** C'est pourquoi je ne recolore pas l'anneau
interieur : je change ou il est peint.

## 2. Le style propose - peindre a l'exterieur, pas a l'interieur

**Meme geometrie de largeur (3 px, `0,1875rem`), meme etat (`:focus-visible` seul), sur le seul
selecteur compose `body:has(.filtre-niveau) .chip-niveau.est-actif:focus-visible`** - je ne touche a
rien pour les neuf autres familles. Seule la peinture change de cote : au lieu d'un `box-shadow`
`inset` qui mord sur le remplissage blanc, un `box-shadow` **sans `inset`**, qui peint juste a
l'exterieur de la bordure, sur le bandeau :

```css
body:has(.filtre-niveau) .chip-niveau.est-actif:focus-visible {
    box-shadow: 0 0 0 0.1875rem var(--recreo-accent-texte);
}
```

`var(--recreo-accent-texte)` est le blanc deja utilise partout ailleurs sur ce meme bandeau (texte des
puces inactives, de "Les jeux", bordures a `:focus-visible` des huit autres familles) : **aucune
teinte neuve**, la meme regle que celle que j'avais posee pour les neuf autres (section 2 de ma
proposition initiale). `box-shadow` reste hors du calcul de boite (`ADR-0046` point 4, second
amendement) : aucun pressable ne change de taille ni de position au focus, `FOC-2` continue de tenir
a l'identique.

## 3. Pourquoi c'est une addition, et pas une variante de la meme reduction

**Le remplissage blanc n'est touche par aucun pixel de cette regle.** L'anneau ne mord plus dessus :
il occupe exclusivement l'espace qui, au repos, est un bandeau uni sans aucune marque - ni bordure
visible (elle se confond deja avec le bandeau, section 0), ni ombre. Rien de blanc ne retrecit.

**Et ce n'est pas non plus l'epaississement observe sur les trois boutons a contour (`S17-B`,
non bloquante).** Sur `.chip-niveau` inactive, `.lien-accueil` et `.retour-accueil`, la bordure au
repos est **deja visible** (blanche ou bleue sur le bandeau ou la page), et l'anneau interieur,
`currentColor` identique a cette bordure, l'epaissit de facon contigue - c'est pourquoi `nadia-prof`
lit "un trait qui s'epaissit" plutot qu'une marque neuve. **Sur la puce active, la bordure au repos
est invisible** (meme couleur que le bandeau, section 0) : il n'y a rien a epaissir. Le blanc qui
apparait a `:focus-visible` n'a donc aucun predecesseur visible dont il serait le prolongement - c'est
une forme qui n'existait pas et qui apparait, sur un fond qui ne portait rien.

**Contraste contre le pixel immediatement exterieur** (le bandeau, `#1d4b6b`, des deux cotes de
l'anneau puisque la bordure qui le separe du bandeau est de la meme couleur) :

- Contraste = (1,000000 + 0,05) / (0,063569 + 0,05) = 1,05 / 0,113569 = **9,25:1**

Plus de trois fois le plancher de 3:1 que `nadia-prof` fixe (WCAG 2.1, critere 1.4.11, indicateur
d'interface non textuel) - la meme paire, et le meme chiffre, que celui deja mesure pour
`.chip-niveau`/`.lien-accueil` contre ce bandeau dans ma proposition de `S17-1`.

## 4. Ce que je ne peux pas verifier moi-meme - la place disponible autour de la puce

Sans navigateur, je ne peux pas mesurer si l'anneau exterieur touche un element voisin a 360 px.
Ce que je peux calculer sur les valeurs declarees : `.entete-contenu` porte `gap: 1rem` (16 px) entre
les puces et les autres elements du rang, et `row-gap: 0,5rem` (8 px) si le rang se replie sur deux
lignes. Un anneau de 3 px sans decalage (`box-shadow` sans `outline-offset`, donc colle a la bordure)
ne consomme que 3 px de cet ecart, dans les deux configurations. Je le signale comme un calcul sur les
valeurs declarees, pas comme une mesure de rendu - c'est a `nadia-prof` de le confirmer sur l'ecran
reel, au meme titre que le reste de sa sonde.

## 5. Ma recommandation

**La variante exterieure (section 2).** C'est la seule dont la marge de contraste est confortable
(9,25:1, trois fois le plancher) sans inventer de teinte neuve, et la seule qui repond directement au
motif exact que `nadia-prof` nomme - aucun pixel du remplissage blanc n'est touche, donc aucune lecture
possible en "reduction". L'alternative que j'ai chiffree (section 1, un anneau interieur recolore) ne
peut satisfaire les deux contrastes requis qu'a l'interieur d'une fenetre de moins de 1 % de
l'echelle de luminance, avec des marges de 2 a 3 % sur chaque bord - le genre d'ecart qu'un
recalcul independant peut faire basculer, et `nadia-prof` a deja refuse une marque de largeur
"a peine visible" sur cette meme puce pour un motif de fragilite comparable. Je ne retiens donc pas
cette piste comme une alternative viable, je la documente pour montrer pourquoi j'en suis partie.

## 6. Ce que je route a `theo-archi`

**Le seul point de mecanisme que je nomme, et que je ne tranche pas** : la regle `box-shadow` de
`FOC-1` regroupe aujourd'hui dix selecteurs, dont `body:has(.filtre-niveau)
.chip-niveau.est-actif:focus-visible`, sous une seule declaration `inset ... currentColor`. Ma
proposition retire ce dixieme selecteur de ce groupe et lui donne sa propre regle, avec une valeur
differente. Je ne sais pas si la forme la plus sure est de sortir le selecteur du groupe (comme je
l'ai ecrit en section 2) ou de le laisser dans le groupe et de le surcharger par une regle plus
specifique declaree apres - a `theo-archi` de choisir la forme qui reste la plus lisible pour
`ADR-0046` et pour les regles ArchUnit qui derivent la population des pressables.

## Mes questions a `nadia-prof`

1. La variante exterieure (blanc, 3 px, hors du remplissage) satisfait-elle votre condition de
   fermeture de `S17-A` - une marque a au moins 3:1 contre le pixel immediatement exterieur, qui soit
   une addition et non une reduction - ou attendez-vous une epaisseur differente ?
2. Sur le rendu reel a 360 px, l'anneau exterieur touche-t-il un element voisin dans le rang de
   l'en-tete (une autre puce, "Les jeux") ? Je ne peux pas le mesurer moi-meme (section 4).
3. Le fait que cette famille recoive desormais un mecanisme different des neuf autres (exterieur au
   lieu d'interieur) vous parait-il devoir etre visible ailleurs que dans le code - par exemple dans
   la fiche de conformite elle-meme - ou la difference de mecanisme, invisible a l'enfant, n'appelle
   aucune mention particuliere de votre point de vue ?
