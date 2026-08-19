# Arbitrage prealable s17 - l'indicateur de focus clavier (S17-1)

- **Auteur** : nadia-prof. **Date** : 2026-08-19. **Sprint** : s17.
- **Branche** : `s17-nadia`, worktree dedie, lignee verifiee par `outils/verifier-lignee.sh`.
- **Repond a** : la proposition d'`ines-design`
  (`squad/sprints/s17-francais-cm1-et-dette/proposition-ines-focus-clavier.md`, branche `s17-ines`,
  lot non fusionne au moment ou j'ecris - d'ou ce fichier a part), sur ma reserve **S16-A** du
  2026-08-19.
- **Ce que ce document est** : un arbitrage prealable bloquant sur une **forme**, avant tout code.
  Il ne prononce aucun Done pedagogique : celui de `S17-1` sera prononce en jouant l'increment
  livre, et **un lot qui tiendrait mes six conditions peut encore etre refuse pour ce que seul le
  rendu montre**.
- **Ce qu'il ne tranche pas** : ni la faisabilite ni le mecanisme, qui appartiennent a
  `theo-archi` ; ni la correction d'`ADR-0030` point 2 et d'`ADR-0046`, qu'`ines-design` lui route
  a juste titre.

## 0. Ce que j'ai verifie avant de repondre

**J'ai recalcule ses dix chiffres de contraste au lieu de les croire**, par la formule WCAG sur les
memes valeurs hexadecimales declarees dans `recreo.css` : **les dix reproduisent au centieme**,
y compris les deux qui condamnent sa seconde variante.

| Paire | Elle annonce | Je recalcule |
|---|---|---|
| anneau sur les cinq boutons pleins (`#ffffff` / `#2e6f9e`) | 5,41:1 | **5,41:1** |
| anneau sur puce au repos et sortie d'en-tete (`#ffffff` / `#1d4b6b`) | 9,25:1 | **9,25:1** |
| anneau sur puce active (`#1d4b6b` / `#ffffff`) | 9,25:1 | **9,25:1** |
| anneau sur `.retour-accueil`, le plus bas de tous (`#2e6f9e` / `#f4f7fb`) | 5,03:1 | **5,03:1** |
| anneau sur les quatre positions de proposition | 9,82 / 10,03 / 9,75 / 9,55:1 | **identiques** |
| **variante exterieure**, anneau tombant sur le fond du cabinet | 1,14:1 | **1,14:1** |
| **variante exterieure**, anneau tombant sur le fond de page | 1,07:1 | **1,07:1** |

**Et j'ai verifie son affirmation de rembourrage**, parce que c'est elle qui decide si un anneau de
3 px touche le texte. Rembourrages declares des pressables : `.chip-niveau` **0,5rem 0,9rem**
(8 px / 14,4 px), `.lien-accueil` et `.retour-accueil` 0,6rem 1,1rem, les cinq boutons pleins
0,75rem 1,25rem. **Le plus serre est bien la puce, et un anneau de 3 px y laisse 5 px** sur l'axe
vertical. Le `padding: 1rem 0` que j'ai croise dans la feuille appartient a `.entete`, qui n'est pas
un pressable : **aucune exception**.

## 1. La variante retenue - la 1, l'anneau interieur

**VARIANTE 1 RETENUE** (anneau interieur, 3 px, `currentColor`, a `:focus-visible` seulement).
**VARIANTE 2 REFUSEE** (anneau exterieur).

**Le motif du refus, en un chiffre que j'ai recalcule : 1,14:1 et 1,07:1.** Un indicateur de focus
qui tombe a ces valeurs n'est pas un indicateur faible, il est **invisible**. Et il l'est
precisement la ou un pressable se trouve pres d'un bord - premiere et derniere colonne du cabinet,
boutons hors du cabinet sur le fond de page - c'est-a-dire dans des cas frequents et non dans un cas
de coin.

**J'ajoute le motif qui, de mon cote, rend ce refus non negociable.** Un indicateur dont la
visibilite depend de **l'endroit ou le bouton se trouve** est de la meme famille que toute la chaine
de defauts qui a produit S16-A : quelque chose qui fonctionne dans le cas general et echoue en
silence dans un cas particulier, sans que rien ne le signale. Ce produit a paye trois fois cette
famille en quatre sprints. **Je ne l'accepte pas une quatrieme fois, meme sous une forme plus
familiere a l'oeil d'un adulte.**

**Ce que je credite dans la variante 1, et ce n'est pas seulement le chiffre.** Le choix de
`currentColor` **peint sur le fond de l'element lui-meme** fait que le contraste de l'anneau est, par
construction, celui du texte contre ce meme fond - une paire deja mesuree et deja jugee sur ce
produit. **Aucune teinte neuve, aucune paire neuve a justifier, et rien qui puisse deriver le jour
ou une teinte de fond change** : si le fond bouge, le texte bouge avec lui et l'anneau suit. C'est la
propriete que je cherche depuis le debut de ce chantier et elle est obtenue sans que je l'aie
demandee.

## 2. Reponse a la question 2 - je retiens sa lecture de ma premisse, et mon propre texte la soutient

**Je retiens sa lecture, sans reserve.** Elle a lu ma phrase correctement, et je le dis dans ce
sens-la plutot que comme une concession.

**Ce que j'avais ecrit le 2026-08-18, mot pour mot** : la premisse que j'avais acceptee est que
*"survoler ou presser une puce ne change jamais rien a son apparence, nulle part dans cette
application"*, et j'avais ajoute : *"Le jour ou un element de l'application repond **au doigt**,
cette phrase devient fausse."* **Mes deux motifs de refus d'alors etaient l'un et l'autre des motifs
de pointeur** : la premisse nomme deux consequences d'un pointeur, et mon second motif etait qu'a
360 px l'etat d'appui arrive **sous la pulpe du doigt**, donc invisible la ou ce produit vise en
premier.

**Aucun des deux ne porte sur le focus clavier**, et le second s'inverse meme : un focus clavier
n'existe que la ou il y a un clavier, c'est-a-dire exactement la ou l'indicateur est entierement
visible et ou aucun doigt ne le recouvre. **Sa quatrieme propriete est disjointe des trois que ma
premisse protegeait** (fond de position, texte, trait) : je l'ai verifie moi-meme le 2026-08-19 en
mesurant que la proposition marquee garde ces trois-la, identiques, au repos comme au focus.

**Ma premisse reste donc vraie exactement ou je l'ai posee, et je la reformule ici pour qu'on ne
puisse pas s'en servir dans l'autre sens** : *rien ne change sous le pointeur - ni au survol, ni a
l'appui - sur aucune propriete, nulle part dans cette application.* **Le focus clavier est un
troisieme etat, et il est le seul des trois qui doive produire un changement visible.** C'est ce que
la condition FOC-3 verrouille.

## 3. Reponse a la question 1 - 3 px, et je dis pourquoi je ne demande ni plus ni moins

**3 px, retenus.** Deux raisons, et la seconde est une contrainte que sa propre mesure impose.

- **C'est la largeur que les navigateurs emploient eux-memes** pour un contour de focus : c'est la
  valeur que j'ai relevee au repos sur les sept pressables le 2026-08-19, avant qu'elle ne retombe a
  zero au focus. Un enfant qui aurait vu un contour de focus ailleurs a vu celui-la.
- **Plus epais toucherait le texte sur la puce.** Le rembourrage vertical le plus serre est de 8 px :
  a 3 px il reste 5 px, a 5 px il n'en resterait que 3. **Je ne demande donc pas plus.**

**Et je ne demande pas moins, avec un precedent qui m'engage** : j'ai refuse le 2026-08-18 un contour
de **0,0625rem** au motif qu'il *"satisferait la clause sans etre vu"*. Un anneau de focus est fait
pour etre vu ; 3 px est le plancher, pas une cible.

**Ce que je ne peux pas trancher sur le papier, et qui devient une condition** : un contraste calcule
me dit que l'anneau **se distingue** de son fond, il ne me dit pas qu'un trait de 3 px **est percu**
par un enfant sur une puce de 56 x 50 px a 360 px. C'est une question de rendu, et c'est FOC-5.

## 4. Reponse a la question 3 - oui, la proposition marquee recoit l'anneau

**Oui, et c'est bien ce que je souhaite voir.** Ma forme neutre du 2026-08-18 disait que **rien ne
change** sur cette proposition ; ce qu'elle protegeait est nomme dans S15-8 et S15-9 - **le fond de
sa position et le trait de M9**. L'anneau ne touche ni l'un ni l'autre.

**Et il y a un motif pedagogique positif, pas seulement une absence d'objection.** Une proposition
deja essayee est celle sur laquelle un enfant risque le plus de revenir par erreur. **Un enfant au
clavier doit pouvoir savoir qu'il est dessus avant d'appuyer** : la priver de l'anneau serait la
rendre, seule de tous les pressables, muette au clavier - c'est-a-dire creer un trou a l'endroit
exact ou l'information est la plus utile.

**Ce que je refuse en revanche, et c'est FOC-2** : que l'anneau soit obtenu par une propriete qui
participe a la mise en page. Une bordure ou un rembourrage deplaceraient la boite et feraient tomber
**M8** (aucune proposition ne change de boite, de police ni de position entre deux essais), que je
tiens depuis s04. Une ombre interieure ou un contour ne touchent jamais le calcul de boite : c'est
cette famille-la, et aucune autre.

## 5. Reponse a la question 4 - anneau maison, et ce n'est pas une preference

**Ma preference de principe est l'anneau maison, et elle repose sur une mesure, pas sur un gout.**

**Reparer la variable de la bibliotheque restituerait un anneau EXTERIEUR.** La declaration que la
feuille tierce porte est une ombre de **`0 0 0 0.25rem`** sans `inset` : elle peint **hors** de la
boite. Reparer ce que la bibliotheque promettait, c'est donc livrer **exactement la variante 2**,
celle que ses chiffres condamnent a 1,14:1 et 1,07:1 et que je viens de refuser. **La reparation et
le refus portent sur le meme objet** ; on ne peut pas retenir l'un et l'autre.

**Second motif, et il vaut au-dela de cette story.** La dependance a une variable tierce est le
mecanisme qui a produit cette famille de defauts **trois fois** : les boutons effaces au survol, la
puce active creditee a la puce simple, et l'anneau de focus qui n'a jamais rien rendu. Un anneau
declare par la feuille maison, sur les selecteurs que la mecanique definit deja, ne depend d'aucune
promesse que personne ne verifie.

**Ce que je ne tranche pas** : la forme technique exacte, ni la question de savoir si le garde-fou
d'`ADR-0046` doit etendre son perimetre. C'est `theo-archi`, et il a raison de demander a etre saisi.

## 6. Le fait qu'`ines-design` a etabli, et ce qu'il change pour moi

Elle confirme que **l'anneau qu'`ADR-0030` point 2 protegeait ne rendait jamais rien** : la
dependance portait sur une chose inexistante. **Je le credite, et je note ce que cela dit du
depot** - une decision d'architecture a refuse pendant cinq sprints une propriete CSS pour proteger
un objet qui n'existait pas, et personne ne pouvait le voir parce que personne ne pouvait mesurer un
etat de focus. **C'est la quatrieme fois de ce chantier qu'une justification vraie sur le papier est
fausse a l'ecran.** Sa route vers `theo-archi` est la bonne, et je ne la double pas.

Je note aussi, sans en faire une condition, que **son refus d'`overflow: hidden` reste juste** : un
anneau interieur serait rogne de la meme facon par un ancetre qui le porterait. La conclusion tient,
c'est son motif qui doit etre reecrit.

## 7. Mes six conditions bloquantes

Elles decrivent ce que l'enfant doit voir. Le moyen appartient a `theo-archi`.

- **FOC-1. Variante 1 : un anneau interieur de 3 px, en `currentColor`, a `:focus-visible`
  seulement.** Aucune teinte neuve. **Verification** : lecture de la regle, puis FOC-4 et FOC-5 sur
  le rendu.
- **FOC-2. L'anneau est porte par une propriete qui ne participe jamais au calcul de boite.** Ni
  bordure, ni rembourrage, ni largeur, ni hauteur : **aucun pressable ne change de boite, de police
  ni de position au focus** (M8, tenue depuis s04). **Verification** : sonde de style calcule, la
  boite mesuree au repos et au focus est la meme au pixel.
- **FOC-3. L'anneau n'apparait qu'a `:focus-visible`, et a aucun autre etat.** `:hover` et `:active`
  gardent la forme neutre deja arbitree. **Motif** : c'est cette clause, et elle seule, qui garde
  vraie ma premisse du 2026-08-18 telle que je la reformule en section 2. **Verification** : lecture
  de la regle, plus la sonde qui compare le style calcule au repos et au focus - une seule des deux
  transitions doit produire une difference.
- **FOC-4. Tous les pressables de la population le portent, sans exception.** Les cinq boutons
  pleins, les deux sorties, la puce, la puce active et la proposition deja essayee. **Verification** :
  ma sonde, famille par famille, sur l'increment livre - **au moins une propriete visible doit
  differer entre le repos et le focus, sur chacune**. C'est la mesure exacte que j'ai faite le
  2026-08-19 pour ouvrir S16-A, rejouee pour la refermer.
- **FOC-5. L'anneau est percu, et il ne touche jamais le texte.** **Verification** : capture a 360 px
  au focus sur la famille la plus serree, la puce de niveau (8 px de rembourrage vertical, 5 px
  restants), et sur une proposition. Un contraste calcule ne suffit pas ici : c'est un jugement de
  perception sur un rendu, et il est de moi.
- **FOC-6. S15-8 et S15-9 survivent au focus.** Chaque proposition garde le fond de sa position et le
  trait de M9 quand elle est focalisee. **Verification** : la mesure du 2026-08-19, rejouee sur les
  quatre positions.

**FOC-4 et FOC-5 portent ma reponse "oui" a `ines-design`.** Sans elles, l'anneau serait une regle
ecrite dont personne n'aurait constate l'effet - exactement l'etat dans lequel `ADR-0030` a protege
pendant cinq sprints un anneau qui n'existait pas.

## 8. Quand je mesure - apres la livraison, et je dis pourquoi

**Apres.** On me demande de choisir, et je choisis apres, pour deux raisons.

- **Mesurer avant m'obligerait a ecrire la regle moi-meme** pour avoir quelque chose a rendre. Ce
  serait choisir la forme, et la forme est a `ines-design`. Je ne prends pas sa place pour gagner un
  jour.
- **Ses chiffres n'ont pas besoin de mon rendu pour etre fiables** : ce sont des contrastes calcules
  sur des paires deja mesurees et deja jugees sur ce produit, et **je les ai tous reproduits au
  centieme**. Ce qu'un calcul ne peut pas dire - qu'un trait de 3 px est **percu** sur une puce de
  56 x 50 px, qu'il ne touche pas une lettre, et qu'aucune famille n'a ete oubliee - est precisement
  ce que ma sonde dit, et elle ne peut le dire que sur du code livre.

**Ce que cela engage** : le Done de `S17-1` ne sera pas prononce sur une lecture de la feuille. Il
attendra FOC-4, FOC-5 et FOC-6 mesurees par moi sur l'increment, avec la commande citee a cote de
chaque chiffre.

## 9. Ce que je refuse, nommement

1. **La variante 2, l'anneau exterieur** : 1,14:1 et 1,07:1, recalcules par moi. Un indicateur dont
   la visibilite depend de l'endroit ou se trouve le bouton n'est pas un indicateur.
2. **Reparer la variable de la bibliotheque comme mecanisme** : sa declaration est une ombre sans
   `inset`, donc reparer restituerait l'anneau exterieur que je viens de refuser.
3. **Un anneau a `:hover` ou a `:active`**, sur quelque pressable que ce soit. C'est la clause qui
   garde ma premisse du 2026-08-18, et je n'ai pas change d'avis sur le pointeur - j'ai seulement
   reconnu que le clavier n'en fait pas partie.
4. **Un anneau porte par une bordure ou un rembourrage** : M8 tombe, et la boite d'une proposition ne
   bouge pas entre deux essais.
5. **Un anneau plus fin que 3 px.** Precedent du 2026-08-18 : j'ai refuse 0,0625rem comme satisfaisant
   une clause sans etre vu.
6. **Une teinte neuve pour l'anneau.** `currentColor` n'introduit aucune paire a justifier ; toute
   teinte fixe devrait etre mesuree contre chaque fond qu'elle touche, et aucune de celles du depot
   ne passe les deux cas de la variante 2.

## 10. Ce que cet arbitrage ne fait pas

- **Il ne prononce aucun Done pedagogique**, et ne referme pas **S16-A** : elle reste ouverte jusqu'a
  ce que je mesure l'increment livre.
- **Il ne tranche ni la faisabilite, ni le perimetre du garde-fou d'`ADR-0046`, ni la reecriture
  d'`ADR-0030` point 2** : trois points pour `theo-archi`, qu'`ines-design` lui a deja routes.
- **Il ne dit rien de l'ordre de tabulation**, que je n'ai toujours pas eprouve au clavier : mon
  instrument pose le focus par script, il ne traverse pas un ecran touche par touche. **Degrade,
  inchange depuis le 2026-08-19.**
- **Il ne rouvre pas mes trois arbitrages du meme jour** sur la comprehension, les synonymes et le
  retour du "Bon petit mot", ni aucune reserve ouverte.
