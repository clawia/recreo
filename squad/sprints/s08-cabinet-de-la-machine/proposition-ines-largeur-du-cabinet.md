# Proposition ines-design - largeur des colonnes de "Le bon verbe" dans le cabinet

Chantier sprint 8, suite au refus pedagogique du 2026-08-11
(`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict "Troisieme audit,
chantier de la conjugaison etendue"). Je propose, je ne tranche pas : `nadia-prof` garde seule le
veto pedagogique et rejouera l'increment. Ce document ne modifie ni `recreo.css` ni
`fragments/manche.html` : c'est `lea-dev` qui implementera si une piste ci-dessous est retenue.

## Lignee et methode de mesure

Worktree dedie `s08-ines-largeur`, forke de `round-two` a `f958dbf`, verifie par
`outils/verifier-lignee.sh` ("le worktree sur la branche 's08-ines-largeur' descend de la lignee de
round-two"). Aucun commit d'un autre agent n'a ete fusionne dans ma branche.

**Mesure en Chrome reel, pas en calcul.** `JAVA_HOME` pointe sur le JDK 21 le temps de la commande
(`outils/verifier-jdk.sh` : `java` du `PATH` en 1.8.0_202, JDK de compilation 21.0.10) pour
construire le fat jar depuis ce worktree (`mvn -B -DskipTests clean package`), lance sur un port
dedie. J'ai ensuite **joue reellement** "Le bon verbe" par ses vraies routes HTTP (`/parties/...`,
`/parties/.../coups`) pour recuperer les fragments authentiques des deux items en cause -
"Vous ___ du chocolat." (bonne reponse `mangez`, distracteurs `mange`, `mangeons`, `mangent`) et
"Je ___ mon cahier dans mon sac." (bonne reponse `cherche`, distracteurs `cherches`, `cherchons`,
`cherchent`) - et des trois autres jeux du catalogue, sans rien fabriquer a la main. Ces fragments
reels sont poses dans une page qui charge les **deux feuilles reellement servies** et reproduit la
structure reelle (`<header class="entete">`, `<main class="container zone-jeu">`), a 360 px de
largeur CSS exacte via un iframe de largeur fixe (meme principe qu'`ADR-0025`/`outils/mesurer-rendu.sh`).
La largeur de glyphes est lue par `Range.getBoundingClientRect()` sur le noeud de texte de chaque
bouton, la boite par `getBoundingClientRect()` du bouton lui-meme - la meme methode que
`nadia-prof` decrit dans son verdict.

**Verification de la methode avant de m'en servir.** Sur la feuille telle que livree (`533967d`),
je mesure `mangeons` a **156,02 px** de glyphes dans une boite de **152,00 px**, marge de
**-2,01 px** de chaque cote, et `cherchons` a **152,17 px** dans **152,00 px**, marge de
**-0,08 px**. Ce sont, a la deuxieme decimale, les chiffres que `nadia-prof` rapporte
(**156,02 / 152,00 / -2,02** et **152,17 / 152,00 / -0,09**) : ma methode reproduit la sienne, je
peux m'appuyer sur mes propres mesures pour chiffrer des variantes qu'elle n'a pas encore vues.

**Limite de ma methode, nommee.** Comme dans ma proposition precedente (`proposition-ines-cabinet.md`),
je n'exerce pas l'echange htmx lui-meme : je demande au serveur ses vrais fragments par ses vraies
routes, je les pose sous les vraies feuilles. Je n'ai pas rejoue l'animation ni le clavier ici,
question hors du perimetre de cette proposition.

## 1. Ce que le mandat me demande d'examiner - et ce que je trouve

Les deux pistes nommees ne sont pas des payeurs *inexamines* au sens ou il resterait de la marge
dessus : ce sont les deux planchers que `nadia-prof` a elle-meme ecrits pour ce chantier, et les
deux sont deja consommes **a l'exacte valeur du plancher, sans aucune marge**.

- **Le cadre.** Son arbitrage prealable du 2026-08-11 pose "un contour ferme... d'une epaisseur
  **d'au moins 0,5 rem (8 px)**" (section 3), reaffirme au point 1 de sa section "suites hors de ma
  main" : "8 px au moins sur chacun des quatre cotes". La feuille livree porte `border: 0.5rem`
  (`recreo.css`, regle `.cabinet`) : **exactement** ce plancher, zero marge au-dessus.
- **La police.** Son acquis 3, cite dans `proposition-ines-cabinet.md` et garde par
  `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` : "`.proposition` >= 2 rem, au moins aussi
  grosse que l'enonce". La feuille livree porte `font-size: 2rem` sur `.proposition` :
  **exactement** ce plancher aussi.

Reduire l'un ou l'autre n'est donc pas "essayer une piste neuve" : c'est demander a `nadia-prof` de
**revoir un chiffre qu'elle a ecrit elle-meme** pour ce chantier precis. Je chiffre les deux
demandes exactement, pour qu'elle mesure ce qu'elle donnerait dans chaque cas, plutot que de
choisir a sa place.

## 2. Piste A - le cadre seul, chiffree

Colonne a deux (le cas de "Le bon verbe" replie) = `(336 - 2 x cadre - 16) / 2` = `160 - cadre`.

| Cadre propose | Colonne resultante | Marge `mangeons` (156,02 px) | Marge `cherchons` (152,17 px) | Breche sur le plancher C1 (8 px) |
|---|---|---|---|---|
| 0,5 rem (8 px, livre) | 152,00 px | **-2,01 px** | **-0,08 px** | aucune (c'est le plancher) |
| 0,25 rem (4 px) | **156,00 px** mesure | **-0,01 px** (deborde encore) | +1,92 px | **-50 %** |
| 0,125 rem (2 px) | **158,00 px** mesure | +0,99 px | +2,92 px | **-75 %** |

Meme en allant jusqu'a 2 px - la valeur la plus fine deja utilisee ailleurs dans la feuille
(`.indice`, `.explication`) - la marge sur `mangeons` reste sous 1 px de chaque cote, plus etroite
que la marge la plus serree jamais acceptee dans ce depot (2,0 px, sur "Le bon verbe" avant le
cabinet). **Cette piste seule exige de couper le plancher C1 d'au moins la moitie, pour un resultat
qui reste a peine confortable.** Effet vertical, mesure : le cadre etant sur les quatre cotes, le
reduire fait aussi baisser le haut et le bas du cabinet (**-8 px** de hauteur totale a 4 px de
cadre, **-12 px** a 2 px) - un effet positif, mais qui ne compense pas le cout pedagogique de
rouvrir C1.

**Je ne recommande pas cette piste seule** : le prix (jusqu'a 75 % du plancher qu'elle a ecrit)
est disproportionne au gain (une marge qui reste inferieure a 1 px).

## 3. Piste B - la police seule, scopee au repli a quatre colonnes, chiffree

Le selecteur existant `.propositions:has(.proposition:nth-child(4)) .proposition` (ADR-0026,
S7-2, deja dans `recreo.css` pour `flex: 1 1 calc(50% - 0.5rem)`) ne matche **que** quand une
quatrieme proposition existe. Aujourd'hui, aucun autre jeu du catalogue n'a quatre propositions :
y ajouter une regle de `font-size` **n'a structurellement aucun effet** sur "Calcul CP", "Le bon
son" ni "Le bon petit mot" - verifie sur le rendu, pas seulement affirme (section 5). Le cadre
reste a 0,5 rem, plancher C1 intact.

| Police proposee | Colonne (inchangee) | Marge `mangeons` | Marge `cherchons` | Breche sur le plancher acquis 3 (2 rem) |
|---|---|---|---|---|
| 2 rem / 32 px (livre) | 152,00 px | -2,01 px | -0,08 px | aucune (c'est le plancher) |
| 1,875 rem / 30 px | 152,00 px | **+2,86 px** | **+4,67 px** | **-6,25 %** |
| 1,8125 rem / 29 px | 152,00 px | **+5,31 px** | **+7,05 px** | **-9,4 %** |

A 1,875 rem, la breche du plancher est six fois plus petite en proportion que la plus petite
breche que la piste A pouvait obtenir sur le cadre (6,25 % contre 50 %), pour une marge trois fois
plus large (2,86 px contre a peine 1 px). C'est la piste qui demande la plus petite revision d'un
chiffre que `nadia-prof` a ecrit elle-meme.

**Effet vertical, mesure et non laisse implicite.** La hauteur totale du cabinet, mesuree sur le
rendu (le meme item, indice non affiche) : **228 px a 2 rem, 222 px a 1,875 rem** - une **baisse**
de 6 px, jamais une hausse. Sur "Le bon verbe", ou 24 items sur 25 poussent deja une rangee sous le
pli des que l'indice s'affiche (`DEC-0016`, motif que je ne rouvre pas), cette piste ne peut
qu'attenuer marginalement ce defaut prexistant, jamais l'aggraver.

**Verification de la non-regression sur les trois autres jeux, mesuree et non supposee.** Avec la
regle ajoutee, "Calcul CP" (3 colonnes, 96,00 px, police 32px), "Le bon son" (3 colonnes, 96,00 px,
32px) et "Le bon petit mot" (2 colonnes, 152,00 px, 32px) sont **identiques au pixel et a la
police pres** a ce qu'ils sont sans la regle - hauteur du cabinet inchangee (138 px). Le selecteur
ne les touche jamais, puisqu'aucun n'a de quatrieme proposition.

**Verification sur d'autres items du meme paquet, pas seulement les deux signales.** J'ai mesure
trois items de plus, choisis pour leurs formes longues (le futur/imparfait ajoutes en S8-4) :

| Item (le mot le plus etroit en marge) | Marge a 2 rem (livre) | Marge a 1,875 rem |
|---|---|---|
| `finissons` | +10,52 px | +14,60 px |
| `aimeront` | +7,54 px | +11,81 px |
| `faisaient` | +11,95 px | +15,95 px |

Aucun n'approche le defaut de `mangeons`/`cherchons` a 2 rem, et tous gagnent de la marge a
1,875 rem : reduire la police n'introduit aucune regression ailleurs dans le meme paquet, elle ne
fait qu'ajouter de la marge partout ou il en restait deja.

**A 375 px (deuxieme largeur mesuree), le defaut n'existe deja plus a 2 rem** (`mangeons` a
+1,74 px) et la marge grossit encore a 1,875 rem (+6,61 px) : 360 px reste, comme attendu, le cas
le plus contraint, et la piste tient aux deux largeurs.

### Regle proposee (fusionnee dans le bloc existant, pas un second bloc)

```css
.propositions:has(.proposition:nth-child(4)) .proposition {
    flex: 1 1 calc(50% - 0.5rem);
    font-size: 1.875rem;
}
```

**Point technique que je remonte a `theo-archi`, pas que je tranche.** Si cette ligne est ajoutee
comme un **second** bloc CSS portant le meme selecteur litteral plutot que fusionnee dans le bloc
existant, `leRepliEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement`
(`TailleDesTextesEtBoutonsTest`) risque de ne plus lire le bon corps de regle si son analyseur
indexe les blocs par selecteur dans une table associative (un second bloc au meme selecteur
ecraserait alors le premier). La fusionner dans le bloc existant, comme ecrit ci-dessus, evite la
question.

**Second point que je remonte, plus important.** `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul`
lit `tailleEnRem(blocs, ".proposition")` - la valeur declaree du selecteur **litteral**
`.proposition`, qui resterait `2rem` et donc **vert**, meme si un enfant sur le repli a quatre
colonnes voit une police effective de 1,875 rem. Je ne propose pas d'ecrire ce test moi-meme (je ne
touche pas au code Java), mais je nomme la chose : **si cette piste est retenue, ce test reste vert
a tort** sur le cas precis qu'il devrait signaler comme une exception au plancher qu'il garde. C'est
a `theo-archi` de decider s'il etend la regle pour lire la valeur effective sous
`:has(.proposition:nth-child(4))`, ou s'il documente l'exception comme deliberee.

## 4. Combinaison des deux pistes, chiffree pour memoire

| Cadre | Police (scopee) | Colonne | Marge `mangeons` | Marge `cherchons` | Breche C1 | Breche acquis 3 |
|---|---|---|---|---|---|---|
| 0,375 rem (6 px) | 1,9375 rem (31 px) | 154,00 px | +1,43 px | +3,29 px | -25 % | -3,1 % |

Je la chiffre parce qu'elle m'a ete nommee comme a examiner, mais elle **ne fait pas mieux que la
piste B seule** (marge plus etroite sur `mangeons`, 1,43 px contre 2,86 px) tout en demandant a
`nadia-prof` de revoir **ses deux** planchers au lieu d'un seul. Je ne la recommande pas.

## 5. Troisiemes chemins envisages, et pourquoi je ne les propose pas

- **Empiler les quatre propositions en une seule colonne** (au lieu du repli 2x2) donnerait une
  largeur de colonne proche de 320 px, largement suffisante - mais couterait environ 180 px de
  hauteur supplementaire (deux rangees de plus a ~74 px + gap), sur un ecran ou 24 items sur 25
  passent deja sous le pli avec l'indice. C'est exactement l'aggravation que le mandat demande de
  ne pas laisser implicite : je la chiffre pour l'ecarter, pas pour la retenir.
- **Rangees asymetriques (3 colonnes courtes + 1 pleine largeur pour le mot le plus long)** exigerait
  de savoir, cote serveur ou cote feuille, **quelle proposition est la plus longue** - une
  information que ni le gabarit ni la mecanique ne portent aujourd'hui, et qui rendrait la mise en
  page dependante du contenu d'un item precis. C'est precisement le couplage mecanique/contenu que
  mon mandat m'interdit de creer : je m'arrete ici plutot que de le dessiner.
- **Laisser le mot se couper sur deux lignes** (`overflow-wrap: break-word`) romprait l'acquis
  explicite de `nadia-prof` ("une proposition tient sur une seule ligne, entiere") et couperait un
  verbe conjugue en deux fragments illisibles pour un enfant de CM1 - une regression de lisibilite
  pire que 2 px de debordement, et hors de mon mandat de decider seule.
- **Reduire le `gap` ou le rembourrage lateral de `.proposition`** : deja refuses nommement par
  `nadia-prof` ("tenu a l'egalite exacte" pour le premier, acquis de saillance pour le second), je
  ne les rouvre pas.

## 6. La reserve neuve, a cout nul en largeur : le contraste cadre/colonnes

`nadia-prof` releve 1,71:1 entre le cadre (`#1d4b6b`, `--recreo-entete-fond`) et les colonnes
(`#2e6f9e`, `--recreo-accent`), sans en faire un motif. **Je peux la corriger sans depenser un
pixel de largeur ni de hauteur** : c'est une question de couleur, pas de dimension.

**Pourquoi pas en fonçant `--recreo-entete-fond` directement.** Cette variable est aussi celle de
l'en-tete permanent (`.entete`), et son commentaire dans `recreo.css` dit deja pourquoi elle ne
peut pas foncer : "plus sombre coute le crayon de favicon.ico, dont le contour et la mine tombent
deja sous 3:1 sur cette valeur" (S6-10). La toucher rouvrirait cette contrainte-la pour rien.

**Proposition : une variable dediee au cabinet, decouplee de l'en-tete.**

```css
:root {
    /* ... variables existantes inchangees ... */
    /* S8, reserve de nadia-prof du 2026-08-11 (contraste cadre/colonnes a 1,71:1) : nuance dediee
       au cabinet, distincte de --recreo-entete-fond pour ne pas rouvrir la contrainte du favicon
       (S6-10). Rapport de contraste avec --recreo-accent (#2e6f9e) : 3,20:1 (calcul WCAG,
       luminances relatives), contre 1,71:1 avec --recreo-entete-fond. Avec la page (#f4f7fb) :
       16,11:1. */
    --recreo-cabinet-fond: #081c2c;
}

.cabinet {
    /* ... */
    background-color: var(--recreo-cabinet-fond);
    border: 0.5rem solid var(--recreo-cabinet-fond);
}
```

Mesure sur le rendu (memes items, meme methode) : geometrie des colonnes **strictement
identique** (152,00 px, aucun changement de largeur ni de hauteur) - seule la couleur change.
Capture a 360 px : le cadre se lit desormais comme une paroi nettement plus sombre que les
boutons, ou "mangeons" (a 1,875 rem, piste B) se tient a l'interieur avec une marge visible des
deux cotes.

**Limite que je nomme.** Mon calcul de contraste reproduit exactement le chiffre de `nadia-prof`
sur le couple cadre/colonnes (1,71:1, verifie), ce qui valide ma methode pour comparer des
candidats entre eux. Le contraste cadre/page que je calcule (8,61:1 sur la valeur actuelle) ne
reproduit pas exactement le 9,25:1 qu'elle rapporte - un ecart probablement du a un seuil de
linearisation sRGB legerement different entre nos deux calculs. Je le signale plutot que de
choisir le chiffre qui m'arrangerait : la comparaison relative (1,71 -> 3,20, une multiplication
par 1,87) reste valide quelle que soit la petite variante de methode, mais l'un de nous deux doit
reverifier la valeur absolue avant de l'ecrire dans une fiche de conformite.

Cette correction est **entierement independante** des pistes A/B/combinaison ci-dessus : elle peut
etre retenue, ajournee ou refusee sans changer la question de la largeur.

## 7. Ma recommandation

**Piste B seule (police scopee a 1,875 rem sur le repli a quatre colonnes, cadre inchange a
0,5 rem)**, parce qu'elle demande la plus petite revision d'un seul plancher (6,25 % de l'acquis
3, contre 50 % ou plus pour toute piste qui touche au cadre), qu'elle donne la marge la plus large
pour le cout le plus faible (2,86 px et 4,67 px, contre moins de 1 px pour le cadre seul), qu'elle
n'a aucun effet mesure sur les trois autres jeux du catalogue, et qu'elle **reduit** legerement la
hauteur du cabinet au lieu de l'augmenter. **La correction de contraste de la section 6 peut
l'accompagner dans le meme commit, a cout nul, si `nadia-prof` la juge utile - ou attendre, sans
lien avec la decision sur la largeur.**

Ce n'est pas un Done : `nadia-prof` mesure a son tour sur le rendu livre, et c'est son verdict qui
tranche si 1,875 rem tient sa propre barre ("chiffre de reponse >= 2 rem") ou si elle prefere
l'une des variantes chiffrees en section 2, 3 ou 4 malgre leur cout plus eleve.

## Ce que je remonte, en un endroit

- A `theo-archi` : fusionner la regle de police dans le bloc `:has()` existant plutot que d'en
  ecrire un second (section 3) ; et decider si
  `leChiffreDeReponseEstAuMoinsAussiGrosQueLeCalcul` doit lire la valeur effective sous le repli a
  quatre colonnes ou documenter l'exception (meme section).
- A `nadia-prof` : les chiffrages des sections 2, 3 et 4, pour son arbitrage sur l'ampleur de
  breche qu'elle accepte sur l'un ou l'autre de ses planchers ; et la nuance de couleur de la
  section 6, avec la limite de methode que j'y nomme sur le contraste cadre/page.
- A `camille-po` / `theo-archi` : les deux chemins ecartes en section 5 (empilement vertical,
  rangees asymetriques) restent des options theoriques si aucune des pistes chiffrees ici n'est
  jugee acceptable ; je les ecarte pour les raisons donnees, je ne les interdis pas.

## Ce que je n'ai pas touche

`recreo.css`, `fragments/manche.html`, tout `ADR`, toute fiche de conformite, `docs/backlog/backlog.md`.
Toutes les mesures ci-dessus viennent de copies de la feuille reellement servie, modifiees dans un
dossier de travail hors du depot, jamais commitees : `git status` sur ce worktree ne montre que ce
fichier.
