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
