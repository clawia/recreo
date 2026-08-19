# Proposition - mise en page de la manche de comprehension (S17-2), pixels manquants

- **Auteur** : ines-design.
- **Repond a** : `squad/conformite/FICHE-bandit-manchot-comprehension-cm1.md`, section "Jugement de
  lisibilite du texte-support (COMP-5)", point 5, moitie mise en page du budget que `nadia-prof`
  route (`COMP-5` reste satisfaite et n'est pas rouverte ici ; `COMP-6` et `COMP-7` ne sont pas
  rouvertes non plus).
- **Ce que ce document fait** : il propose des regles de style, scopees a un gabarit generique
  (tout ecran de manche qui porte un champ `.texte-support`, jamais "la comprehension CM1" au sens
  du contenu), pour reduire l'espace vertical que la mise en page consomme sans le payer sur une
  taille de police, une couleur, un contraste ou une cible tactile deja actee.
- **Ce que je ne propose pas** : un contenu, un nombre ou une longueur de propositions, une
  consigne ni une question - ce qui reste du mecanisme (nombre d'items par rangee, repli du
  cabinet a quatre colonnes) est nomme comme hors de mon mandat et route a `theo-archi`. Je ne
  mesure aucun rendu : les chiffres ci-dessous sont de l'arithmetique sur des valeurs deja
  declarees dans `recreo.css` et sur les constantes deja mesurees par `nadia-prof` (30 px par
  ligne de `.texte-support`, 52-100 px pour `.enonce`) - pas des pixels lus sur un ecran.

## 0. Le budget dont je pars, dans les mots de `nadia-prof`

Marge disponible dans le meilleur assemblage encore realiste (consigne une ligne, question une
ligne, propositions courtes) : **4,81 px**. Le plafond qui en resulte est de 4 lignes de
texte-support, soit environ 20 mots - un support trop court pour porter une inference, ce qui
rend `COMP-3` inatteignable. Il faudrait retrouver de l'ordre de 90 a 120 px (3 a 4 lignes
supplementaires, au tarif de 30 px/ligne deja mesure) pour revenir pres du budget initial de 8
lignes / 45 mots qu'elle avait juge suffisant sur le bloc seul.

Ce que je vais chercher ci-dessous n'atteint pas ce chiffre a lui seul, et je le dis des
maintenant plutot qu'a la fin : **ma part de la mise en page vaut de l'ordre d'une a trois lignes
selon la variante retenue, pas les trois a quatre lignes necessaires.** Le reste revient au
mecanisme que traite `theo-archi` en parallele.

## 1. Ce que je ne touche pas, et pourquoi

Trois familles de regles sont deja tranchees ou protegees, et rogner dessus reviendrait a rouvrir
un arbitrage ferme ou a depenser un acquis d'un autre sprint plutot qu'a en trouver un nouveau :

- **La taille et la graisse de `.texte-support` (1,25rem, normale)** : `COMP-5` est ACCEPTEE sur
  cette forme precise ; `nadia-prof` ecrit "je ne demande pas un troisieme signal". Je ne rouvre
  pas un point qu'elle vient de fermer.
- **Le cabinet (`.cabinet`, `.cabinet-bandeau`, `.cabinet-socle`, `.cabinet-levier`) et l'ecart
  entre propositions (`.propositions { gap: 1rem }`)** : c'est l'identite visuelle du meuble,
  actee sur plusieurs sprints (`ADR-0030`, `ADR-0033`, `ADR-0036`, F5 "aucune colonne ne paye ce
  lot"), et le `gap` de 1rem vient nommement d'une reserve de `nadia-prof` sur le geste de
  l'enfant (S3-5 : "un ecart d'au moins 1rem laisserait plus de place a un geste d'enfant").
  Le rogner gagnerait quelques pixels sur cet ecran precis en retirant de la marge de geste sur
  tous les ecrans qui partagent le meme selecteur - un cout a l'enfant que je ne fais pas payer
  pour ce budget.
- **`S15-8`/`S15-9`, `ADR-0046`, l'anneau de focus clavier de `S17-1`** : aucune des regles
  ci-dessous ne touche une pseudo-classe interactive (`:hover`, `:focus-visible`), une couleur de
  fond de bouton ni une propriete de boite d'un element pressable.

## 2. Le levier que j'ai : scoper au champ, pas au contenu

`recreo.css` utilise deja `:has()` pour faire varier une regle selon la forme du contenu plutot
que selon son sujet (`.propositions:has(.proposition:nth-child(4))`, S13). Je reprends exactement
ce principe : une regle conditionnee par la **presence d'un champ generique** (`.texte-support`)
plutot que par une matiere ou un niveau, s'applique a tout futur jeu qui porterait un texte a
lire - pas seulement a "comprehension CM1". Elle ne change **rien** sur les ecrans qui n'ont pas
ce champ : aucun budget deja mesure pour un autre jeu (M4, S8-5, etc.) n'est requalifie par ce que
je propose ici.

## 3. Variante recommandee - resserrement scope, score conserve

Trois regles, toutes lues sur des valeurs deja declarees dans `recreo.css` :

```css
/* padding-top seul, jamais le raccourci "padding" - c'est le raccourci qui avait efface le
   rembourrage lateral en s05 (lecon retenue, meme s'il ne s'agit pas ici de rembourrage lateral). */
.zone-jeu:has(.texte-support) {
    padding-top: 0.5rem;
}

.ecran-de-jeu:has(.texte-support) .score {
    margin: 0.25rem 0;
}

.ecran-de-jeu:has(.texte-support) .consigne,
.ecran-de-jeu:has(.texte-support) .texte-support,
.ecran-de-jeu:has(.texte-support) .enonce {
    margin-bottom: 0;
}
```

Delta declare, chaque ligne isolee :

| Regle | Valeur actuelle | Valeur proposee | Delta declare |
|---|---|---|---|
| `.zone-jeu` `padding-top` | 2rem (32 px) | 0,5rem (8 px) | **-24 px** |
| `.score` `margin` | 0,5rem haut+bas (8+8 px) | 0,25rem haut+bas (4+4 px) | **-8 px** |
| `margin-bottom` de `.consigne`/`.texte-support`/`.enonce` | 0,25rem chacun (4 px) | 0 | **-12 px** (3 x 4 px) |
| **Total declare** | | | **-44 px** |

44 px, c'est un peu moins d'une ligne et demie de `.texte-support` au tarif de 30 px/ligne deja
mesure par `nadia-prof`. Applique au meilleur assemblage (marge de 4,81 px), cela porte la marge a
environ 48,81 px avant le pli - de quoi ajouter une ligne pleine et garder encore une petite marge,
soit environ 25 mots au lieu de 20.

**Ce que cela coute a l'enfant** : rien sur une police, une couleur, une cible tactile ou une
information affichee - uniquement de l'espace blanc. Concretement, sur cet ecran precis : le
contenu commence plus pres de l'en-tete (8 px au lieu de 32 px sous le bandeau), et les blocs
consigne/texte/question se touchent presque (0 px au lieu de 4 px entre eux). Le score reste
visible partout, a la meme place, avec le meme texte - seule sa respiration verticale se resserre
d'un quart. Aucun autre ecran (accueil, un autre jeu, la revelation, la fin de partie) ne change,
puisqu'aucun ne porte `.texte-support`.

**Ce que cela ne resout pas** : 44 px ne comblent pas l'ecart de 90-120 px nomme en section 0. Un
support de 25 mots reste court pour porter une inference reelle au sens de `COMP-3` - c'est mieux
que 20, ce n'est pas encore ce que `nadia-prof` visait au depart.

## 4. Variante alternative - meme resserrement, score masque sur ces ecrans

Meme trois regles que la section 3, sauf que la troisieme ligne du tableau est remplacee par un
masquage complet plutot qu'un resserrement de marge :

```css
.ecran-de-jeu:has(.texte-support) .score {
    display: none;
}
```

Delta declare pour cette ligne seule : au lieu de -8 px (marge resserree), le bloc entier
disparait du flux - taille de police (20 px), interligne herite (30 px) et les deux marges
(8+8 px), soit **-46 px** pour cette seule regle. Combine aux deux autres regles inchangees de la
section 3 (zone-jeu -24 px, marges des trois blocs -12 px), le total declare monte a **-82 px**,
soit pres de trois lignes de `.texte-support` au lieu d'une et demie - environ 33-35 mots au lieu
de 25.

**Ce que cela coute a l'enfant, et ce n'est pas neutre** : il ne voit plus son score pendant ce
jeu, alors qu'il le voit sur tous les autres jeux du catalogue - "Score : X" n'a jamais ete
conditionnel jusqu'ici. Ce n'est pas seulement de la place gagnee, c'est une information retiree
et une rupture de coherence entre deux jeux de la meme famille (bandit manchot). La regle reste
techniquement generique (elle s'applique a tout futur jeu portant `.texte-support`, jamais a
"comprehension CM1" nommement), mais elle cree une divergence de comportement que l'enfant peut
remarquer d'un jeu a l'autre, ce qu'aucune autre regle de cette proposition ne fait.

## 5. Ma recommandation

**La variante recommandee (section 3).** Elle est la seule des deux dont le cout est purement
spatial - rien que l'enfant perde en information ou en coherence avec le reste de l'application.
Le gain plus large de la variante alternative (82 px contre 44) ne suffit de toute facon pas a lui
seul a fermer l'ecart de 90-120 px nomme en section 0 : dans les deux cas, la mise en page seule
n'y arrive pas, et le cout supplementaire de la variante alternative (masquer une information
affichee partout ailleurs) achete moins d'une ligne de plus que ce qu'il coute. Je ne la retiens
que si le lot mecanisme de `theo-archi` ne suffit pas non plus a fermer l'ecart a lui seul - et
dans ce cas, masquer le score n'est pas ma decision seule a prendre : c'est un arbitrage a nommer
a `camille-po`/`nadia-prof`, pas quelque chose que je glisse ici comme un simple resserrement.

## 6. Ce que je n'ai pas tranche

- **Je ne sais pas si mes 44 (ou 82) px, combines a ce que `theo-archi` degage cote mecanisme,
  ferment reellement l'ecart de 90-120 px.** Je n'ai pas de navigateur sur ce poste ; c'est
  `nadia-prof` qui mesurera l'assemblage complet, comme pour `COMP-4`.
- **Je ne propose rien pour la reserve qu'elle a routee vers moi** (un signal visuel entre
  `.consigne` et `.texte-support` qui ne coute aucun pixel, pour le jour ou une consigne de ce jeu
  passerait a deux lignes). Ce n'est pas le sujet de cette demande - la consigne de `S17-2` reste a
  une ligne (`COMP-6`) - et je ne veux pas resoudre a la hate un point qu'elle a explicitement
  qualifie de non bloquant. Il reste ouvert.
- **Je ne sais pas si `0,5rem` de `padding-top` reste assez respirant sous l'en-tete** au jugement
  de `nadia-prof` une fois le rendu joue - je propose une valeur, je ne la valide pas moi-meme.
