# Arbitrage de forme - s56, instance B : le dispositif d'`ART-CP-10` avant le branchement (`S56-ART-CP-10-FORME`)

`nadia-prof`, instance B, le 2026-10-09 (20:58, UTC+02:00). Branche `s56-nadia-b`, base `dfcec37c`. Reponses a `Q1` a `Q6` de
`cloture-nadia.md` (s55, section 4.4). Arbitrage prealable de B : `ARBITRAGE-PREALABLE-s56-instance-b-art-cp-10-et-emc-cm1.md`.

**Qui fait quoi** (`cloture-nadia.md` 4.4) : `ines-design` propose la forme, `theo-archi` dit la voie et la structure, je pose les conditions
et je juge. **A l'ecriture de ce fichier, ni la proposition d'`ines-design` pour s56 ni l'avis de `theo-archi` n'existent** (branches
`s56-ines-design` et `s56-theo-avis` sans commit au-dela de `dfcec37c`, worktrees sans modification). Je pose donc les conditions que la
forme doit remplir ; **je jugerai la proposition contre elles, dans ce fichier, par un bloc date**, sans prix de plus. Sources : la
proposition d'`ines-design` de s55 (`proposition-ines-s55-deux-oeuvres-ensemble.md`, sections 2, 3 et 8), le rapport de `lea-dev` (`E1` a
`E6`), `ADR-0104` (decision 4).

Aucun Done, aucun identifiant neuf : `N1` a `N6` sont des reperes locaux.

## Resume

| Question | Reponse |
|---|---|
| `Q1` canal de forme de l'echec | conditions `N1` a `N6` ; critere pour `S56-ART-CP-10-ECHEC` ; proposition et voie attendues |
| `Q2` case egale ou image egale | **case egale**, sous la condition `B5` de l'arbitrage B ; aucun recadrage |
| `Q3` pli a 551 px | **recevable** si les cases, leurs marques, la reference, l'indice entier (echec) et la phrase de `M4` (revelation) tiennent au-dessus du pli ; `Q7` le mesure |
| `Q4` fond clair derriere chaque reproduction | **accepte**, identique pour les trois, sans motif |
| `Q5` meuble | **je ne le demande pas** ; je demande `Q3` et `N2`. La structure est a `theo-archi` |
| `Q6` rang, nom de groupe, taille de la phrase | liste acceptee, non exigee ; **aucun nom de groupe** ; phrase de revelation a 1,25 rem |

---

## Q1 - Le canal de forme de l'echec : conditions

Le constat est celui du 2026-10-08 (`cloture-nadia.md` 4.2, precision de `M3`) : *"l'oeuvre deja essayee porte une marque de forme qui ne
depend pas de ce que la reproduction montre ; l'attenuation peut s'y ajouter, jamais la remplacer ; rien n'est ecrit ni dessine dans l'image
(`X3`)"*. C'est la propriete de `F16` appliquee a une image. Je la decompose pour qu'elle se juge piece par piece :

- **`N1` - Hors de l'image.** La marque est posee autour de la reproduction (cadre, bord, rembourrage de la case), jamais sur ses pixels :
  aucun texte, pictogramme, trait, voile colore ou aplat dans l'image (`X3`). L'anneau de `E2`, pose sur le bord de l'image dans le
  rembourrage, est a cette place : la meme voie convient.
- **`N2` - Independante de l'image.** Son contraste se mesure contre ce qui l'entoure hors de l'image (le fond de la case, ou le meuble s'il
  reste), **au moins 3:1** (plancher d'un composant non textuel), calcule comme les contrastes de la proposition de s55. Elle se voit autant
  autour d'une reproduction pale que d'une sombre.
- **`N3` - Distincte par la forme des trois autres etats de la meme case** : le cadre ordinaire (plein, 2 px), l'anneau de focus et l'anneau
  de revelation (`E2`, plein, 0,25 rem). Elle ne differe pas d'eux seulement par la couleur ou l'epaisseur : **un enfant ne doit jamais lire
  "deja essayee" comme "la bonne"**. Un cadre discontinu (tirets ou pointilles) remplit cette condition ; un cadre plein plus epais, non.
- **`N4` - L'attenuation s'ajoute, ne remplace pas.** L'opacite a 50 % peut rester ; seule, elle est refusee (`E3`).
- **`N5` - Elle tient jusqu'a la fin de la manche** : apres la deuxieme erreur, les deux oeuvres essayees la portent ; a la revelation, elles
  la gardent, et la bonne oeuvre porte seule l'anneau de `E2`.
- **`N6` - Rien ne bouge** : la case et l'image ne changent ni de taille ni de place quand la marque arrive (`M1`).

**Critere a ecrire par `camille-po` pour `S56-ART-CP-10-ECHEC`** (mes mots, `N1` a `N6`), avec ce qui se mesure, a traduire par `yanis-qa` :

1. la regle qui porte la marque de `.deja-essayee` dans la branche image declare au moins une propriete autre que `opacity` (aujourd'hui, la
   commande du backlog, `awk` suivi de `grep -vc opacity`, rend 0 ; elle doit rendre au moins 1, sur le selecteur que la voie retient) ;
2. cette propriete est une forme discontinue (`dashed` ou `dotted`) ou une autre forme que `ines-design` propose et que j'accepte ici par
   bloc date ;
3. `line-through` reste a 2 dans `recreo.css` (branche texte inchangee) ;
4. `N2` : le contraste calcule de la marque contre son entourage est ecrit dans la proposition d'`ines-design`, au moins 3:1 ;
5. `N3` a `N6` et la marque vue sur une reproduction pale : **a moi, en jouant**, a l'audit.

**Ce que je lis deja de la proposition de s55** (cadre en tirets de meme epaisseur, image a 50 %) : sur le papier, elle remplit `N1`, `N3`,
`N4` et `N6` ; `N2` depend de la couleur du cadre et de la presence du meuble (encre sur fond clair, 14,09:1 ; `--recreo-accent` sur le
meuble, 3,20:1, au plancher). Elle a ete abandonnee par `lea-dev` (`E3`) pour une raison de voie (dette d'etats d'`ADR-0046`), pas pour une
raison que je retiens. **La voie est a `theo-archi`** ; si elle est celle de `E2` (sur l'image, dans le rembourrage), je n'ai pas d'objection.

**Etat** : conditions posees. **Jugement de la proposition d'`ines-design` et de la voie de `theo-archi` : a venir dans ce fichier.** Sans
lui, le worktree de `S56-ART-CP-10-ECHEC` ne s'ouvre pas (`DEC-0116` 3.1).

## Q2 - Case egale ou image egale (`M1`)

**Case egale**, carree, une reproduction entiere par case (`object-fit: contain`). **Aucune proposition n'est recadree** : `X5` l'interdit pour
la reference, `ADR-0082` (decision 2, fichier source redimensionne sans traitement) pour toute reproduction ; je l'etends ici aux propositions.

**Correction de mon propre constat.** `M1` ecrit *"chaque proposition a la meme taille rendue que les autres"*. Dans une case carree, une
reproduction en largeur se rend plus petite qu'une en hauteur (132 x 67 contre 99 x 132 a 744 px, mesure de s55) : a la lettre, `M1` est
impossible a tenir sans recadrer, ce que j'interdis. Ce que `M1` voulait, son motif le dit : *"une image plus grande serait un indice"*. **La
taille ne doit pas designer la bonne oeuvre.** Avec des cases egales, cela se tient par le contenu : condition `B5` de l'arbitrage B (aucune
bonne oeuvre seule de son format parmi les trois propositions). C'est une precision datee de `M1`, pas un assouplissement : la propriete
gardee est la meme.

## Q3 - Le pli de l'echec et de la revelation a 551 px

`M1` ne parle que de l'ecran avant tout geste, et il tient en disposition "cote" des 720 px (mesure de s55). Apres un geste, le depassement
est **recevable** a ces conditions, a 744 x 551 (`DEC-0040`) et aux largeurs de 720 px et plus :

- **echec** : la reference, les trois cases, la marque de l'echec et **l'indice entier** sont au-dessus du pli. L'indice est la raison d'etre
  de cet etat : un indice coupe ne dit plus ou regarder ;
- **revelation** : la reference, les trois cases, l'anneau de `E2` et **la phrase de `M4` entiere** sont au-dessus du pli ; la fin de
  l'explication (`M6`) peut demander de faire defiler.

C'est `Q7` (`ines-design`, releve a dix largeurs **sur le gabarit livre**) qui dit si c'est tenu. Le releve de s55 laisse un doute que lui
seul leve : avec le meuble, l'echec depasse de 37 px a 744. Sous 720 px de large, la disposition empilee ne tient rien a 551 : je ne l'exige
pas (`DEC-0040`) ; a 1024 de haut, tout tient.

## Q4 - Le fond clair identique derriere chaque reproduction

**Accepte.** `C5` clause 1 (lecture du 2026-10-06) mord sur ce qui est ajoute a cote ou derriere un texte ; ici la reproduction constitue la
proposition, et le fond est la marge de la case, pas un decor. Conditions : **le meme fond pour les trois cases**, uni, sans degrade ni motif ;
les teintes cycliques des boutons-texte neutralisees (`X6`) ; aucun voile sur l'image.

## Q5 - Le meuble, conserve ou retire

**Pedagogiquement, je ne demande pas le meuble** pour ce type d'item : il n'apprend rien a l'enfant sur les oeuvres. **Je demande `Q3` et
`N2`.** Le releve de s55 dit que seule la variante sans meuble tient l'echec a 744 x 551, et que le cadre `--recreo-accent` sur le meuble est
a 3,20:1. Le retirer ou le garder touche `ADR-0030` et ses conditions : **la structure est a `theo-archi`**. S'il le garde, `Q7` doit montrer
`Q3` et `N2` tenues avec lui ; sinon il n'est pas branchable sur ce motif.

## Q6 - Rang, nom de groupe, taille de la phrase de revelation

- **Rang annonce par une liste** (`ul` et `li`, "element 1 sur 3" dit par l'aide technique) : **acceptee, non exigee**. Elle n'ecrit aucun mot
  et n'ajoute rien a l'ecran (`X2` tenu). La competence est visuelle : un enfant qui n'y voit pas ne la travaille pas avec ce dispositif, et je
  ne fais pas d'une structure d'accessibilite une condition de branchement. Son prix et sa structure sont a `theo-archi`. Rang dans le nom
  accessible : toujours permis par `M5`, non exige.
- **Nom de groupe** sur les trois boutons : **aucun**. Ce serait une phrase de plus ; l'enonce `B8` joue ce role.
- **Taille de la phrase de revelation** : **1,25 rem** (`.reponse-revelee`, aucune taille neuve), comme `ines-design` le propose. Ce que
  l'enfant doit voir en premier a cet ecran est l'anneau, pas la phrase. La variante a 2 rem est ecartee.

## Q7 et Q8 - ce qui reste

- `Q7` (`ines-design`) : releve a dix largeurs sur le gabarit livre, apres `Q1`, **avant l'entree de catalogue**. Il juge `Q3`.
- `Q8` (moi) : la visibilite de chaque propriete a 132-137 px et a 84 px, la disposition "cote" : **a l'audit, a l'ecran, sur les paires
  reelles.**

## Prix

**0,25**, confirme (`cloture-nadia.md` 4.4 ; `DEC-0116` A.1, couvert par l'ecart). Le jugement a venir de la proposition d'`ines-design` et de
la voie de `theo-archi` est compris dans ce prix.

## Ce dont je reste la seule garde sur la forme

`N3` a `N6` a l'ecran, la marque vue sur une reproduction pale et une sombre, `M1` a l'ecran, `X2`, `X3`, `X6`, `E2`, `Q8`. Le garde-fou 4
d'`ADR-0104` exige un nom accessible non vide, pas les mots de `M5`.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.

---

## Bloc du 2026-10-09, 21:37 - jugement de `Q1` (marque de l'echec) et de `Q3` (les 40 px) - nadia-prof UNIQUEMENT

Sources lues sur `a772c822` : la proposition d'`ines-design` (`releves-s56/proposition-ines-forme-art-cp-10.md`, `c4a79010`) et ses quatre
captures a 744 x 551 (`echec-base`, `echec-A`, `echec-B`, `revelation-A`), que j'ai regardees ; l'avis de `theo-archi` (`avis-faisabilite.md`
de s56, `026b2e17`, sections 2 et 4). **Jugement sur un vehicule statique et des captures, pas sur le jar** : degrade par nature, aucun paquet
n'etant servi. Le jugement a l'ecran reste a l'audit.

### Q1 - O1 retenue ; O2 recevable en repli seulement

| Condition | O1, pastille portant une croix sous la case | O2, cadre en tirets sur l'image, attenue a 50 % |
|---|---|---|
| `N1` hors de l'image | oui : sous la case, hors du bouton et de l'image | oui : bord de l'image |
| `N2` au moins 3:1, independante de l'image | oui : disque sur meuble 16,11:1, croix sur disque 5,23:1, anneau sur fond de page 14,09:1 | **3,04:1**, au plancher ; l'attenuation de l'image attenue le cadre. Sur `echec-B`, je le vois, faiblement, autour d'une estampe pale |
| `N3` distincte par la forme de l'anneau de revelation | oui : un signe, pas un anneau | oui : discontinu contre plein |
| `N4` attenuation en plus, pas seule | oui | oui |
| `N5` jusqu'a la fin de la manche | oui : `revelation-A` montre les deux croix, et l'anneau sur la bonne seule | oui |
| `N6` rien ne bouge | oui : les cases ne bougent pas, la rangee grandit sous elles | oui |

**O1 est retenue.** Elle remplit `N1` a `N6` avec de la marge, et elle dit quelque chose a un enfant de CP : c'est la croix du cahier, la
meme que celle du signal faux qu'il vient de voir sous le meuble. Des tirets ne disent rien a cet age.

**`X2` et `X6`, que `ines-design` me demande de lire** : la croix n'est ni un numero, ni une lettre, ni une legende. `X2` refuse une etiquette
qui permet de repondre en lisant ; la croix arrive apres le geste et ne marque que ce qui a deja ete essaye, ce que `M3` demande. `X6` ne
vaut qu'avant le geste. **Ni l'un ni l'autre n'est touche.**

**La voie n'est pas a moi.** L'avis de `theo-archi` designe la boite de l'image ; il a ete ecrit avant O1, qui passe par un `::before` sur le
bouton, positionne sous la case. Il lui reste a dire si cette voie tient (`ADR-0046`, garde-fou de pseudo-elements d'`ADR-0027`,
`HabillageDesEcransTest`) : `ines-design` n'en a lance aucun. **Si la voie de O1 est refusee, O2 est recevable** : elle remplit `N1` a `N6`
a la lettre. Je la jugerai a l'ecran, sur une reproduction pale ; si je ne la vois pas, ce sera un refus sur `N2`, pas une barre neuve. O3
n'est pas demandee.

### Q3 - les 40 px : je corrige ma reponse de ce matin

**Le fait mesure.** Ce matin j'ai ecrit, pour l'echec a 744 x 551 : l'indice entier au-dessus du pli. Les mesures d'`ines-design` disent
que **le rendu livre ne le tient deja pas, sans aucune marque** : bas du contenu de l'echec a 588 avec le meuble, 37 px sous le pli ; sur
`echec-B`, le cadre de l'indice commence a la ligne du pli. O1 porte ce bas a 628 (77 px), la revelation de 622 a 662. Retirer le meuble
entier rendrait 64 px, pas 77 ; la forme de retrait de `theo-archi` n'en masque qu'une partie, et c'est une story que personne n'a ecrite.

**Les deux conditions sont a moi, et je les ordonne.** `N1` a `N6` sont un motif de refus depuis le 2026-10-08 : sans marque, l'enfant presse
deux fois la meme oeuvre sans le savoir. Un indice sous le pli, sur l'ecran d'ordinateur le plus bas du perimetre, est un defaut moindre :
l'indice n'est pas perdu, il est un defilement plus bas, et la croix, les cases et la reference restent visibles (pastille a 437-477).
**Donc, a la place de ma reponse de ce matin** (que je laisse lisible plus haut) :

- **Bloquant a l'audit** : a 744 x 551, la reference, les trois cases, les croix (echec) ou l'anneau (revelation), et la phrase de `M4` sont
  au-dessus du pli. Mesure : tenu avec O1 et le meuble.
- **Non bloquant** : l'indice coupe par le pli a 744 x 551. S'il l'est sur le jeu reel, c'est une **reserve datee**, ouverte au verdict,
  dont la levee est une story de retrait du decor du meuble. Les indices du paquet font huit mots au plus (`CHOIX-OEUVRES-s56-art-cp-10.md`)
  pour tenir sur une ligne a 744 ; `Q7` le mesure sur le gabarit livre.
- **`Q5`, en consequence** : le meuble est conserve pour le branchement, zero code. Sa forme de retrait (`theo-archi`, section 4) est la voie
  de la reserve si elle s'ouvre. Ma phrase de ce matin (*"sinon il n'est pas branchable sur ce motif"*) est remplacee par ce point.

C'est un assouplissement de ma condition de ce matin, et je le dis comme tel : je l'avais ecrite sans connaitre la mesure du rendu livre
ni le prix de la marque. Personne ne l'avait encore atteinte ni manquee.

### Critere de la marque, en proprietes decidables, pour `S56-ART-CP-10-ECHEC`

A recopier par `camille-po` mot pour mot. Ecrit pour O1 ; la variante O2, si la voie de O1 est refusee, est entre crochets.

1. **(`N1`, `N4`)** `recreo.css` porte une regle dont le selecteur contient `.propositions-oeuvres` et `.proposition.deja-essayee` et qui
   declare une propriete autre que `opacity` [O2 : la regle `.propositions-oeuvres .proposition.deja-essayee img` declare une propriete de
   bordure en plus de `opacity`]. Aujourd'hui : aucune. Apres : au moins une.
2. **(`N1`)** Aucune regle dont le selecteur contient `deja-essayee` et se termine par `img` ne declare `filter`, `background`,
   `background-image`, `mix-blend-mode`, `content` ni `mask` : rien n'est dessine sur l'image.
3. **(`N1`)** [O1] Le pseudo-element de la marque declare `pointer-events: none` et une position `top` de la forme
   `calc(100% + <longueur positive>)` : il commence sous le bord du bouton.
4. **(`N2`)** Les couleurs de la marque (remplissages et traits du fichier SVG de la marque [O2 : la couleur de la bordure, composee avec
   l'opacite de l'image sur le fond de case]) et celles de son entourage (fond du meuble, fond de page) donnent, par la formule de luminance
   relative WCAG 2.x, au moins 3:1 entre le bord exterieur de la marque et son entourage, et au moins 3:1 entre le signe et son fond.
   Calculable sur les valeurs hexadecimales, sans navigateur.
5. **(`N3`)** Le selecteur de la marque contient `deja-essayee` et pas `data-oeuvre-revelee` ; la regle de `[data-oeuvre-revelee=true]` ne
   reference pas le fichier de la marque.
6. **(`N5`)** Sur une manche rendue a trois propositions, apres deux erreurs puis a la revelation : les deux boutons essayes portent la
   classe `deja-essayee`, la bonne porte `data-oeuvre-revelee="true"` et pas `deja-essayee`. Se juge sur la page rendue (banc du garde-fou 4).
7. **(`N6`)** Aucune regle dont le selecteur contient `deja-essayee` et vise `.proposition` ou son `img` ne declare `width`, `height`,
   `min-width`, `min-height`, `max-width`, `max-height`, `padding`, `margin`, `aspect-ratio`, ni une largeur de bordure differente de celle
   de la meme regle sans `deja-essayee`. La hauteur gagnee par la rangee (`padding-bottom` de `.propositions-oeuvres`) n'est pas visee : elle
   deplace ce qui est sous la rangee, pas les cases.
8. **(branche texte)** `grep -c 'line-through'` sur `recreo.css` rend 2.

**Non decidable, a moi seule, en jouant** : que la croix se reconnaisse a 40 px et se lise "pas celle-la" ; que la marque se voie autour
d'une reproduction pale et d'une sombre ; qu'elle ne se confonde ni avec l'anneau de focus ni avec celui de la revelation.

### Ce qui n'est pas juge ici

- La correction du plafond proposee par `ines-design` (section 2, l'ecart de 2 px) : affaire de `theo-archi` et de `lea-dev`. Les deux
  formes me conviennent ; je n'exige pas que la reference soit plus grande que les propositions.
- Le pli de `LIS-2` (section 3) : hors de ce poste, a `camille-po` pour le perimetre de s57.

**Prix** : compris dans les 0,25 de ce poste.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.

---

## Bloc du 2026-10-09, 22:07 - lecture du critere 7 de la marque, reecrit - nadia-prof UNIQUEMENT

Source : amendement de l'avis de `theo-archi` (`3f5d68c7`, A.1, *"Deux precisions pour `camille-po`"*). Il signale que mon critere 7
(*"vise `.proposition` ou son `img`"*) se lit de deux facons, et que l'une refuse O1, que j'ai retenue au bloc de 21:37.

**Ma lecture : le sujet.** Le critere 7 traduit `N6` : *"la case et l'image ne changent ni de taille ni de place quand la marque arrive"*. Il vise
ce que l'enfant voit bouger, la case et l'image ; pas la marque elle-meme, qui a forcement une taille. La pastille de O1 declare `width`,
`height` et `margin-left` sur son `::before`, hors du flux : elle ne deplace ni la case ni l'image. **La lecture "le selecteur contient
`.proposition`" n'etait pas ce que j'ai voulu** ; elle refuserait toute marque posee par un pseudo-element, ce que ma propre decision de 21:37
accepte. Le defaut est dans ma redaction. Je ne change pas la propriete, j'en ferme l'ambiguite.

**Critere 7, reecrit. Il remplace le 7 du bloc de 21:37**, que je laisse lisible ; `camille-po` recopie celui-ci mot pour mot :

> **7. (`N6`)** On appelle *sujet* d'un selecteur son dernier compose, pseudo-element compris. Pour toute regle de `recreo.css` dont le
> selecteur contient `deja-essayee` et `propositions-oeuvres` :
>
> - **(a)** si son sujet porte la classe `proposition` ou le type `img`, **et ne porte aucun pseudo-element**, la regle ne declare ni `width`,
>   ni `height`, ni `min-width`, ni `min-height`, ni `max-width`, ni `max-height`, ni `padding`, ni `margin`, ni `aspect-ratio`, ni une
>   largeur de bordure differente de celle que declare la regle de meme sujet sans `deja-essayee` ;
> - **(b)** si son sujet est un pseudo-element (`::before` ou `::after`) d'un compose qui porte la classe `proposition`, la regle declare
>   `position: absolute` : la marque sort du flux et ne deplace ni la case ni l'image. Elle peut declarer sa propre taille et ses marges ;
> - **(c)** une regle dont le sujet porte la classe `propositions-oeuvres` (la rangee, par exemple son `padding-bottom`) n'est pas visee : elle
>   deplace ce qui est sous la rangee, pas les cases.

**Ce que cela donne sur O1** : `.propositions-oeuvres .proposition.deja-essayee::before` tombe sous (b) et declare `position: absolute` :
tenu. `.propositions-oeuvres:has(.proposition.deja-essayee)` tombe sous (c) : non vise. **Mutations qui doivent rougir** : `max-height: 9rem`
ajoute a `.propositions-oeuvres .proposition.deja-essayee img` (mutation E7 de `theo-archi`, clause (a)) ; `position: absolute` retire du
`::before` (clause (b)) ; `padding: 1rem` ajoute a `.propositions-oeuvres .proposition.deja-essayee` (clause (a)).

Les criteres 1 a 6 et 8 ne changent pas. **Le faux rouge d'`ADR-0026` clause 3** (A.1) est a `theo-archi` : je n'en juge que ce qui touche
l'enfant, et `pointer-events: none` sur la pastille est exige par mon critere 3, parce qu'une pastille qui represse l'oeuvre au doigt
etendrait la case deja essayee de 40 px vers le bas.

**Signe** : `nadia-prof`, s56, instance B, 2026-10-09.

---

## Bloc du 2026-10-11 - correction de mon constat de `Q3`, apres l'audit joue - nadia-prof UNIQUEMENT

Rien au-dessus n'est efface. **La phrase *"Mesure : tenu avec O1 et le meuble"* du bloc de 21:37 est fausse.** Ce
n'etait pas une mesure de la phrase de `M4` : j'avais lu le bas du contenu de revelation du vehicule statique
d'`ines-design` comme la place de la phrase. Sur le jar, a 744 x 551, la phrase va de y = 626 a 656, entierement sous le
pli. **La condition bloquante elle-meme ne change pas.** Elle n'est pas remplie : le paquet est **REFUSE** sur ce motif,
`FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md`, bloc du 2026-10-11, avec la methode de mesure. L'indice coupe
par le pli, que j'annoncais comme reserve datee, y est ouvert (`RDO-CP-R1`).

Les trois lectures du critere de la marque (`rapport-lea-dev.md`, 11.2) sont confirmees dans le meme bloc de la fiche.

**Signe** : `nadia-prof`, s56, 2026-10-11.
