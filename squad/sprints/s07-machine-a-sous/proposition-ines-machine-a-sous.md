# Proposition de forme - la machine a sous (S7-1 a S7-4)

- **Auteur** : ines-design
- **Date** : 2026-08-10
- **Nature** : proposition de forme, pas un Done pedagogique. `nadia-prof` garde seule le veto ;
  tout ce qui suit est sous reserve de son jugement en jouant l'increment livre (section 9 de son
  arbitrage prealable, `squad/conformite/FICHE-bandit-manchot-additions-cp.md`).
- **Ce que je ne tranche pas** : l'architecture (`theo-archi`), le perimetre produit (`camille-po`),
  la pedagogie (`nadia-prof`). Je ne touche a aucun fichier de `domain/`, `application/`,
  `bootstrap/`, ni au code de production. Aucun fichier CSS ou HTML n'est modifie par ce commit :
  ce document decrit des selecteurs et des valeurs, a charge de `lea-dev`/`theo-archi` de les
  ecrire dans `recreo.css` et dans les gabarits, avec leurs tests.
- **Lignee** : worktree dedie `s07-ines-design`, provisionne depuis `round-two` a `28a419f`,
  verifie par `outils/verifier-lignee.sh` avant la premiere ecriture.
- **Ce que j'ai lu** : `docs/backlog/backlog.md` (S7-1 a S7-4), `DEC-0015` et ses deux amendements
  (branche `s07-camille-po`, commit `ed2f96a`), les quatre fiches de `squad/conformite/` de
  l'arbitrage prealable du 2026-08-10 (branche `s07-nadia`, commit `04c6b53`), `ADR-0026` et
  `ADR-0027` (branche `theo-s07-machine-a-sous`, commit `9c2f1c2`).

## 0. Ce qui n'est pas encore fusionne, et ce que ca change pour cette proposition

Aucun des trois lots n'est sur `round-two`. En particulier, `[data-arrivee=par-un-geste]`
(`ADR-0027`) n'existe pas encore dans `fragments/manche.html` de ce worktree - je l'ai verifie
avant d'ecrire ce document. Toute regle CSS ci-dessous qui nomme ce selecteur est donc ecrite pour
le moment ou `ADR-0027` sera fusionne, pas pour l'etat actuel du depot. Si `lea-dev` implemente le
marqueur sous une autre forme que celle decrite par `ADR-0027` section 1, les selecteurs ci-dessous
sont a adapter en consequence - je nomme la propriete qu'ils doivent verifier, pas seulement le
texte a copier.

## 1. La forme du rouleau : un decor qui glisse devant un bouton immobile, jamais une fenetre qui defile

**Je retiens le pseudo-element decoratif devant un bouton immobile, pas la fenetre
`overflow: hidden` avec bande qui glisse.**

**Motif, dans l'ordre de ce qui compte le plus.**

1. `theo-archi` nomme un risque reel et non hypothetique sur la fenetre qui defile (`ADR-0026`,
   section "La reponse au clavier") : le bouton reste focusable mais peut se trouver hors de la
   partie visible de sa colonne pendant le defilement, et le navigateur fait alors defiler la page
   pour ramener le focus a l'ecran - un mouvement de page que personne n'a demande, au moment
   precis ou `nadia-prof` interdit tout mouvement non voulu. Un bouton qui ne bouge jamais ferme
   cette question a la racine plutot que de la geser au cas par cas.
2. Nadia-prof formule elle-meme sa preference (section 2 de son arbitrage) : *"le mot reste
   lisible du debut a la fin et seul un element decoratif passe devant (voile, reflet, volet,
   flou)."* C'est exactement la forme que je retiens, et elle ecrit que c'est celle des deux
   latitudes qui ne pose aucun des deux problemes qu'elle nomme (mot faux vu plus souvent que le
   bon ; mot envoye different de celui vu). Avec un bouton immobile et son texte reel toujours
   present, il n'y a jamais de decalage entre ce qui est affiche et ce qui serait envoye.
3. Elle ne coute aucune ligne de JavaScript et reste un delta CSS pur (`ADR-0008`, `ADR-0014`
   point 7 restent intacts).

**Ce que je propose, concretement.** Chaque `.proposition` porte, en plus de son texte reel
(inchange), un pseudo-element `::after` : un bandeau translucide (un "voile"), qui glisse une
seule fois de haut en bas de la colonne au moment ou le levier est tire, puis disparait. Le texte
du bouton n'est jamais efface, jamais remplace, jamais recouvert entierement : le voile est
translucide (45 % de blanc), donc le mot reste lisible en permanence, y compris pendant le
passage du voile.

```css
.proposition {
    position: relative;
    overflow: hidden;
}

.proposition::after {
    content: "";
    position: absolute;
    inset: 0;
    border-radius: inherit;
    background: linear-gradient(
        180deg,
        rgba(255, 255, 255, 0.45) 0%,
        rgba(255, 255, 255, 0.45) 22%,
        transparent 22%,
        transparent 100%
    );
    transform: translateY(-140%);
}
```

`position: relative` et `overflow: hidden` sont ajoutes uniquement sur `.proposition`, pas sur
`.demarrer`, `.recommencer`, `.encore` ni `.sortie-pleine`, qui partagent aujourd'hui le meme bloc
de proprietes communes dans `recreo.css` : c'est une regle separee, comme `.proposition` en porte
deja une pour `font-size`. `overflow: hidden` ne rogne aucun texte - il ne fait que clore la
fenetre dans laquelle le voile glisse ; le libelle du bouton, lui, n'est jamais plus grand que sa
boite (section 6) et n'est donc jamais rogne.

`content: ""` est la seule valeur que ce pseudo-element declare pour `content` : la meme regle que
les deux barres de la croix du signal faux (`.signal.est-faux .icone-signal::before/::after`).
`ADR-0027` clause 7 l'exige (aucune valeur de `content` autre que la chaine vide ou `none`), et ce
n'est pas une contrainte nouvelle pour ce depot, seulement la meme regle appliquee a un troisieme
endroit.

Je n'ajoute pas `pointer-events: none` sur ce pseudo-element : un `::after` n'est pas un noeud du
DOM separement atteignable au clic, un appui sur sa zone visuelle est recu par le bouton qui le
porte. L'ajouter n'apporterait rien et risquerait de faire lire la regle 3 d'`ADR-0026` (qui
refuse `pointer-events: none` sur toute regle nommant une classe portee par `[data-role=action]`,
hors `@keyframes`) comme concernee alors qu'elle ne devrait pas l'etre. Point a verifier par
`lea-dev` a l'ecriture, je ne l'affirme pas a sa place.

## 2. La duree, la courbe et le decalage

Valeurs choisies a l'interieur des bornes de `nadia-prof` (0,3 s a 1,2 s, decalage 0,25 s au
plus, compris dans le budget) et d'`ADR-0027` clause 6 (memes bornes, gardees par l'automate).

| Grandeur | Valeur retenue | Motif |
|---|---|---|
| Duree de chaque voile | **0,6 s** | La valeur que `nadia-prof` conseille elle-meme ; `ADR-0027` la cite nommement comme celle qui doit passer la clause de relecture adverse |
| Courbe | **`ease-out`** | Demarre vite, ralentit vers la fin : c'est la forme executable de "le mouvement ralentit avant de s'arreter" qu'elle exige, sans suite d'images cles ni rebond |
| Decalage entre colonnes | **0,08 s par colonne**, cumulatif | Sous le plafond de 0,25 s meme a quatre colonnes (0,24 s pour la quatrieme), et proportionne au nombre de colonnes plutot que fixe |
| Nombre de passages du voile | **1**, jamais infini | `ADR-0026` clause 1 : `animation: rouleau-glisse 0.6s ease-out 1;` |

```css
[data-arrivee=par-un-geste] .proposition::after {
    animation: rouleau-glisse 0.6s ease-out 1;
}
[data-arrivee=par-un-geste] .proposition:nth-child(2)::after { animation-delay: 0.08s; }
[data-arrivee=par-un-geste] .proposition:nth-child(3)::after { animation-delay: 0.16s; }
[data-arrivee=par-un-geste] .proposition:nth-child(4)::after { animation-delay: 0.24s; }

@keyframes rouleau-glisse {
    from { transform: translateY(-140%); }
    to   { transform: translateY(140%); }
}
```

Verification des chiffres contre `ADR-0027` clause 6 : duree declaree 0,6 s (dans 0,3-1,2 s) ;
decalage maximal declare 0,24 s (sous 0,25 s) ; duree + decalage au pire cas (quatrieme colonne)
= 0,84 s (sous 1,2 s). Les trois inegalites sont tenues avec marge, pas a l'egalite.

**Limite que je nomme plutot que de la laisser decouvrir** : ces quatre regles couvrent les
colonnes 1 a 4, le maximum mesure aujourd'hui dans le catalogue (`ADR-0026` section 4). Si un
paquet futur proposait une cinquieme colonne, elle demarrerait sans decalage (delai par defaut
nul) faute d'une cinquieme regle - un defaut d'harmonie visuelle mineure, pas un depassement de
barre. A completer par qui ecrira ce paquet.

## 3. La forme du levier : un gros bouton-poussoir rond, jamais une manette a tirer

**Je ne dessine pas de manette.** Nadia-prof interdit nommement toute forme qui suggere un geste
autre qu'un appui simple - "pas de fleche vers le bas, pas de manette a demi baissee qui attend
qu'on la tire, pas de glissement" (section 5). Un enfant de CP n'a jamais besoin de comprendre
qu'il "tire un levier" : il doit seulement comprendre "j'appuie ici et le jeu avance", et il le
comprend deja par le mot ecrit sur le bouton. Je choisis donc un decor qui ne mime pas un geste de
traction : un gros bouton-poussoir rond, comme un enfant en reconnait sur un jouet ou une borne
d'arcade - un objet qu'on presse, jamais qu'on tire.

```css
.demarrer,
.encore {
    position: relative;
    border-radius: 999px;
    box-shadow:
        inset 0 -0.25rem 0 rgba(0, 0, 0, 0.18),
        0 0.125rem 0 rgba(0, 0, 0, 0.15);
}

.demarrer::before,
.encore::before {
    content: "";
    position: absolute;
    top: 0.4rem;
    left: 50%;
    transform: translateX(-50%);
    width: 0.6rem;
    height: 0.6rem;
    border-radius: 50%;
    background-color: rgba(255, 255, 255, 0.55);
}
```

- `border-radius: 999px` arrondit le bouton en pilule/cercle plutot que le rectangle a coins
  legerement arrondis des autres boutons pleins, pour le distinguer visuellement sans toucher a sa
  taille.
- Le `box-shadow` (un liseret sombre en bas, un leger relief en dessous) donne un relief de bouton
  physique qu'on enfonce, sans ajouter de pixel a la boite : `box-shadow` ne modifie ni la largeur
  ni la hauteur rendues.
- Le petit disque translucide en haut du bouton est **purement decoratif** (`content: ""`, la
  meme regle qu'ailleurs dans ce document) : un reflet de "capuchon" de bouton-poussoir, jamais un
  second mot, jamais une fleche.
- Mesure sur rendu reel a 360 px (methode en section 7) : le disque se place a 0,4rem du bord
  haut du bouton et ne touche a aucun moment le texte, qui reste centre par le
  `display: inline-flex; align-items: center` deja en place. Maquette jetable, supprimee apres
  la mesure, comme celles de la section 5.

**Ce que je ne touche pas** : `.demarrer` et `.encore` gardent leur `min-height: 2,75rem`,
`min-width: 2,75rem`, `padding: 0.75rem 1.25rem` et `font-size: 1,25rem` inchanges (le mot du
levier reste au moins a 1,25rem, comme nadia-prof l'exige nommement y compris pour ce bouton).
`.recommencer` et `.sortie-pleine` partagent aujourd'hui le meme bloc commun de proprietes que
`.demarrer`/`.encore` mais ne recoivent aucune des deux regles ci-dessus : elles ne sont scopees
qu'aux deux classes qui deviennent le levier. C'est ce qui garantit structurellement qu'aucun
decor de levier n'atteint jamais `.sortie-pleine` sur l'ecran de fin de partie (acquis 6 de
`nadia-prof`) : la regle ne le cite pas.

## 4. Le repli de mouvement (`prefers-reduced-motion`)

Forme litterale, meme selecteur que celui qui porte l'animation, comme `ADR-0026` clause 2
l'exige :

```css
@media (prefers-reduced-motion: reduce) {
    [data-arrivee=par-un-geste] .proposition::after { animation: none }
}
```

**Ce que cela donne a l'ecran, et pas seulement dans la feuille de style.** Sans l'animation, le
pseudo-element reste a son etat de base, `transform: translateY(-140%)` : entierement hors de la
zone visible de la colonne (clipisee par `overflow: hidden` sur `.proposition`). L'enfant voit
donc un bouton parfaitement immobile, son texte plein et net, sans meme le voile translucide
passer dessus une fois. C'est le repli le plus simple possible : rien ne s'ajoute au bouton
d'aujourd'hui. Rien n'est ajoute non plus au bouton-poussoir de la section 3 : le decor du levier
n'est pas une animation, il n'est concerne par aucun repli de mouvement.

Je n'ai pas pu verifier ce point dans un vrai navigateur avec le reglage systeme de reduction des
animations active, faute d'increment ecrit a auditer : c'est nomme dans la liste de
`nadia-prof` (section 9 point 4 de son arbitrage) comme ce qu'elle reverifiera elle-meme sur le
rendu livre, "pas sur la feuille de style". Je le nomme aussi pour ne pas laisser croire que ce
point est clos.

## 5. Le repli au-dela de trois colonnes

**Je trouve une disposition qui tient, et je l'ai mesuree, pas seulement calculee.**

### Le choix : deux colonnes par rangee des la quatrieme proposition, jamais pour deux ou trois

```css
.propositions:has(.proposition:nth-child(4)) {
    flex-wrap: wrap;
}

.propositions:has(.proposition:nth-child(4)) .proposition {
    flex: 1 1 calc(50% - 0.5rem);
}
```

Le selecteur `:has()` ne cree aucune classe nouvelle : il s'appuie sur `.propositions` et
`.proposition`, deja existantes, exactement comme `body:has(.ecran-de-jeu) .lien-accueil` le fait
deja dans `recreo.css` depuis S6-9. Il ne modifie donc rien pour les cas a deux ou trois
propositions deja valides par `nadia-prof` (`cm1-homophones` a 161 px, `cp-additions-jusqu-a-10`
et `cp-le-bon-son` a 101 px) : `.proposition:nth-child(4)` ne matche que si une quatrieme
proposition existe, donc la regle ne s'applique qu'a partir de quatre colonnes. Les deux
dispositions deja accordees restent inchangees a l'octet pres du CSS qui les regit.

### Ce que j'ai mesure, et comment

Maquette jetable, hors du depot, non commitee, supprimee apres mesure (comme au sprint 6). Deux
feuilles reellement servies (`vendor/bootstrap.min.css` puis `css/recreo.css`, dans cet ordre),
structure reelle `.container.zone-jeu > .ecran-de-jeu > form.propositions > button.btn.proposition`,
seul delta : `.propositions { flex-direction: row }`, `.proposition { flex: 1 1 0 }` (le delta
d'`ADR-0026`) plus les deux regles `:has()` ci-dessus. Capture par
`outils/mesurer-rendu.sh <largeur> <url> <png>`, Chrome designe par la variable `CHROME`, mesure
des largeurs par balayage des suites de pixels correspondant a `--recreo-accent`
(`rgb(46, 111, 158)`, tolerance 6) sur une ligne prise a 8 px sous le bord haut de chaque bouton,
pour eviter les angles arrondis et les glyphes, sur les deux rangees.

**Items testes : les deux cas les plus defavorables des douze items a quatre propositions de "Le
bon verbe"** (avant la reduction a trois propositions que `nadia-prof` a par ailleurs acceptee sur
un autre motif, `FICHE-bandit-manchot-conjugaison-present-cm1.md`) - les deux items qui portent
les mots les plus longs du paquet, neuf lettres :

- item 8 : `finis`, `finit`, `finissons`, `finissent` ;
- item 6 : `dessines`, `dessine`, `dessinent`, `dessinez`.

| Largeur de viewport | Largeur de colonne | Ecart mesure (les deux axes) | Hauteur de rangee |
|---|---|---|---|
| 360 px | **160 px** (les deux colonnes) | **16 px** | 74 px par rangee, deux rangees |
| 375 px | **168 px et 167 px** | **16 px** | 74 px par rangee, deux rangees |

Capture regardee a 360 px sur l'item 8 (`finis`, `finit`, `finissons`, `finissent`) et sur l'item 6
(`dessines`, `dessine`, `dessinent`, `dessinez`) : aucune image n'est jointe a ce document, la
maquette jetable qui a produit ces captures est supprimee apres mesure, conformement a la
consigne. Les nombres du tableau ci-dessus sont ceux releves dessus, par la methode de la
section 7.

**Aucune troncature, aucune coupure, aucun debordement sur les deux items testes, aux deux
largeurs.** `finissons` et `finissent` (nadia-prof note ces memes items comme ceux qui debordaient
a trois colonnes, `FICHE-bandit-manchot-conjugaison-present-cm1.md` section "La mesure qui fonde
la seconde phrase") tiennent chacun entierement dans leur boite de 160 px, avec de l'air visible
de part et d'autre du mot.

### Confrontation aux six conditions de lisibilite de `nadia-prof`

| Condition (section 3 de son arbitrage) | Verifie |
|---|---|
| 1. une proposition tient sur une seule ligne, entiere, a 2rem au moins | oui, `font-size: 2rem` de `.proposition` n'est pas touche, aucun retour a la ligne observe sur les neuf lettres de `finissons`/`dessinent` |
| 2. aucune troncature, jamais | oui, aucun `text-overflow`, aucun `overflow: hidden` n'est ajoute sur le texte - le seul `overflow: hidden` ajoute (section 1) porte sur le pseudo-element decoratif, pas sur le libelle |
| 3. aucune reduction de `font-size` sous 2rem | oui, aucune regle de cette proposition ne touche `font-size` de `.proposition` |
| 4. aucune compression du glyphe | oui, aucun `letter-spacing`, `transform: scale`, police condensee ni `font-stretch` introduit |
| 5. pas de cesure | oui, aucun `hyphens` ni `word-break` introduit |
| 6. pas de texte vertical | oui, aucun `writing-mode` introduit |

### Confrontation aux six acquis de `DEC-0015`/`nadia-prof`

| Acquis | A 360 px, disposition a deux rangees |
|---|---|
| cible tactile >= 2,75rem | inchangee : `min-height`/`min-width: 2,75rem` non touches, hauteur reelle mesuree 74 px par bouton comme dans le cas a une seule rangee |
| lisibilite >= 1,25rem | sans objet ici (porte sur `.indice`/`.score`, non touches) |
| chiffre de proposition >= 2rem | inchange, voir tableau ci-dessus |
| `gap` >= 1rem sur les deux axes | **16 px mesures sur les deux axes**, egalite exacte comme le cas a une seule rangee, aucune marge ni aucun recul |
| hierarchie de saillance `ADR-0017` | inchangee : aucune taille ni couleur de `.proposition` n'est modifiee |
| `sortie-pleine` | sans objet : cette disposition ne concerne que `.propositions`, jamais rendue sur l'ecran de fin de partie |

### Un gain que je mesure aussi, dans le style de nadia-prof elle-meme

Quatre propositions empilees verticalement occupent aujourd'hui 4 x 74 px + 3 x 16 px = 344 px de
hauteur. La disposition en deux rangees de deux en occupe 2 x 74 px + 1 x 16 px = 164 px, soit
**180 px liberes** - le meme calcul et le meme ordre de grandeur que celui qu'elle relevait
elle-meme pour trois colonnes en une seule rangee (section 3 de son arbitrage). Un paquet a quatre
propositions gagne donc autant de place au-dessus du pli qu'un paquet a trois.

### Reponse a la question 4

**Je trouve une disposition qui tient pour quatre propositions, mesuree a 360 px et a 375 px, sans
abaisser aucun plancher et sans violer aucune des six conditions de lisibilite : deux colonnes par
rangee, activee uniquement a partir de la quatrieme proposition.**

**Limite que je nomme et qui reste ouverte** : je n'ai teste que N=4 (le seul cas reel du
catalogue aujourd'hui, `ADR-0026` section 4). Pour un N impair superieur a trois (cinq
propositions, par exemple), la derniere rangee ne compterait qu'un seul element, qui grandirait
alors pour occuper toute la largeur disponible (`flex: 1 1 calc(50% - 0.5rem)` avec
`flex-grow: 1` et un seul element dans sa rangee) - un defaut d'harmonie visuelle, pas un
debordement ni une violation de plancher, mais une chose que je n'ai pas mesuree faute de contenu
reel a cette taille. A revisiter si un paquet a cinq propositions ou plus se presente.

**Je ne tranche pas si "Le bon verbe" doit effectivement utiliser cette disposition a quatre
colonnes plutot que la reduction a trois deja acceptee par `nadia-prof` sur son propre motif**
(`FICHE-bandit-manchot-conjugaison-present-cm1.md`) : `DEC-0015`, second amendement, laisse ce
choix entre deux sorties - le repli de mise en page que je decris ici, ou le report du jeu hors du
sprint - a `camille-po`. Ce document ferme la question technique ("une disposition existe-t-elle
pour plus de trois propositions sans abaisser un plancher") ; il ne ferme pas la question de
perimetre ("laquelle des deux sorties retenir pour ce jeu precis").

## 6. Ce que j'ai verifie et qui n'est pas dans les sections precedentes

- **Le focus clavier n'est pas affecte** : le bouton reste un `<button type="submit">` inchange,
  dans le meme `<form>`, a la meme place dans l'ordre du document. Rien dans mes regles ne touche
  `disabled`, `tabindex`, ni l'ordre des elements.
- **Aucun mot autre que sa propre proposition n'entre dans une colonne** : le voile de la section 1
  ne porte aucun texte (`content: ""`), et le texte du bouton n'est jamais remplace ni masque
  completement - c'est la forme que je choisis precisement pour ne jamais y contrevenir.
- **Aucune ligne n'est ajoutee a `recreo.js`** : tout ce document est du CSS et un attribut de
  gabarit deja porte par `ADR-0027` (`data-arrivee`), jamais du script.
- **Toute dimension nouvelle est en `rem`, `px` de `box-shadow` ou pourcentage** : les seules
  valeurs en pixels de ce document sont celles d'un `box-shadow` (0,25rem et 0,125rem, en realite,
  je les ai laissees en `rem` ci-dessus) - aucune largeur, hauteur ni `min-width`/`min-height` en
  pixels.

## 7. Methode de mesure, pour qu'elle soit rejouable

- Maquette jetable construite localement, servie par un serveur de fichiers statique local sur les
  memes deux feuilles que l'application (`vendor/bootstrap.min.css`, `css/recreo.css`), delta
  decrit en section 5.
- Mesure par `outils/mesurer-rendu.sh 360 <url> <fichier.png>` et
  `outils/mesurer-rendu.sh 375 <url> <fichier.png>`, `CHROME` designant l'executable Chrome du
  poste.
- Lecture des largeurs par balayage de pixels sur l'image produite (suites de pixels dans la
  tolerance de `--recreo-accent`), a 8 px du bord haut de chaque bouton pour eviter les coins
  arrondis - la meme precaution que celle que `nadia-prof` et `theo-archi` ont adoptee au sprint 7
  apres avoir mesure une premiere fois dans les angles.
- Maquettes et serveur local supprimes apres la mesure, avant ce commit : aucun fichier de test
  visuel n'est laisse dans le depot.

## 8. Ce que je remonte plutot que de trancher

- **La forme exacte du marqueur `data-arrivee`** (section 0) : mes selecteurs supposent la forme
  litterale d'`ADR-0027`. A confirmer par `theo-archi`/`lea-dev` au moment de l'ecriture.
- **`pointer-events` sur le pseudo-element decoratif** (section 1) : je raisonne qu'il n'est pas
  necessaire, mais je ne l'ai pas verifie sur un clic reel pendant l'animation. A `lea-dev` de le
  confirmer a l'ecriture du test.
- **Le repli au-dela de quatre colonnes** (section 5) : la disposition proposee degrade sur un N
  impair superieur a trois (dernier element qui s'etire). Je ne l'ai pas mesuree faute de contenu
  reel a cette taille aujourd'hui.
- **Le choix entre repli de mise en page et report de "Le bon verbe"** (section 5, derniere
  sous-section) : reste a `camille-po`, `DEC-0015` second amendement.
- **Que le geste du levier ait bien sa cause visible une fois l'increment joue** (le point que
  `nadia-prof` nomme elle-meme comme non tranchable sans jouer, section 5 de son arbitrage) : je
  n'ai rien ajoute qui pourrait l'aggraver ou l'ameliorer, ma proposition ne porte que sur
  l'apparence du bouton, pas sur l'ecran ou il se trouve.
- **`nadia-prof` reverifiera tout ce qui bouge sur le rendu livre** (section 9 de son arbitrage) :
  rien de ce document ne vaut Done pedagogique, y compris les points ou j'affirme une conformite
  aux planchers - ces planchers sont mesurables par moi, la comprehension de l'enfant ne l'est pas.
