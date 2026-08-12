# DEC-0016 - Une hauteur de viewport de reference, et ce qui doit rester au-dessus du pli sur un ecran de manche

- **Type** : regle transverse, aucun contenu pedagogique nouveau
- **Date** : 2026-08-10
- **Auteur** : camille-po
- **Sprint** : s07 (ouverture de la regle) - la correction du jeu qui l'a revelee n'est pas
  affectee a ce sprint, voir "Impact"
- **Competence programme visee** : transverse - accessibilite et lisibilite, aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge par cette decision.
- **Statut** : Acceptee

## Contexte

`DEC-0007` point 5 fixe une largeur cible (375 px) pour l'affichage des ecrans de jeu. Aucune
decision du depot ne fixe de hauteur de viewport, ni de regle sur ce qui doit rester visible sans
defilement. `nadia-prof` a signale ce trou deux fois le meme jour, avec une gravite differente :

- une premiere fois au point 6.5 de son verdict sur `bandit-manchot-additions-cp.md`, a propos du
  pli mesure sur "Le bon verbe" - qu'elle croyait alors limite a un jeu retire du catalogue
  (`DEC-0014`) ;
- une seconde fois, plus grave, en mesurant les vingt-quatre items de "Le bon petit mot"
  (`bandit-manchot-homophones-cm1`, jeu **catalogue**, Done pedagogique accorde, ligne
  `couvert-valide`) : sur trois d'entre eux, l'affichage de l'indice pousse les deux boutons de
  reponse sous une ligne de flottaison mesuree a 360 x 640, et sur l'item 24 il n'en reste rien du
  tout a l'ecran. Elle a mesure que la cause est la longueur de l'enonce a 2 rem qui se replie sur
  cinq ou six lignes - ni le nombre de propositions, ni la mise en page en colonnes - et que le
  defaut existe donc a l'identique dans l'affichage empile servi aujourd'hui.

Ce second constat est le plus grave des deux parce qu'**il ne survient qu'au moment ou l'enfant
vient d'echouer trois fois et recoit son aide** : mesure des deux cotes, zero item sur vingt-quatre
en defaut sans l'indice, trois avec. L'ecran donne l'indice et retire les boutons de reponse dans
le meme geste.

`nadia-prof` ne fait pas de ce constat un motif de refus - le Done pedagogique de "Le bon petit
mot" reste accorde, la ligne reste `couvert-valide`, et elle explique pourquoi : aucune decision du
depot ne fixe de hauteur de viewport, et elle refuse d'opposer apres coup a un paquet valide un
defaut qu'elle n'avait pas oppose au paquet qu'elle refusait le meme jour. Elle nomme le trou de
decision et me le route nommement, sans choisir entre les deux pistes possibles (reduire la
longueur des enonces, jugement pedagogique qui reviendrait devant elle ; ou traiter la hauteur de
l'ecran, question d'habillage qui passerait par `theo-archi` et `ines-design`) - et elle precise
explicitement que ce constat n'est pas une raison de ralentir le chantier machine a sous (S7-1).

Elle signale enfin qu'`ines-design`, en proposant son repli de mise en page pour "Le bon verbe", a
elle-meme choisi son pire cas au nombre de lettres (`mangez`, 6 caracteres) et manque de ce fait son
pire cas reel, puisque `theo-archi` a deja mesure (`ADR-0026`) que le budget qui compte est une
largeur de glyphes rendus, pas un compte de caracteres.

## Decision

**1. La hauteur de viewport de reference est fixee a 640 px, appariee a la largeur de 360 px deja
en usage dans les audits de `nadia-prof` et dans l'outillage du depot
(`outils/mesurer-rendu.sh 360 <url> <png>`).** Je ne l'invente pas : c'est deja la convention que
`nadia-prof` applique de fait a chaque audit faute d'une decision qui la fixe, et je la rends
explicite plutot que d'en choisir une nouvelle sans raison. Cette hauteur s'ajoute a la largeur
cible de `DEC-0007` point 5 (375 px) ; elle ne la remplace pas, et je ne touche pas non plus au
plancher de 500 px que `yanis-qa` a etabli pour ses propres besoins de rapport de qualite - c'est
une contrainte d'outillage de test, pas une cible produit.

**2. Sur un ecran de manche, l'ensemble des elements necessaires pour repondre - l'enonce, l'indice
s'il est affiche, et les propositions ou colonnes de reponse - doit rester visible sans defilement
sur ce viewport de reference.** La regle porte sur la visibilite effective de ces elements, pas sur
une propriete de leur code source : je n'ecris aucun critere en nombre de caracteres, precisement
parce que `nadia-prof` vient de signaler qu'`ines-design` a elle-meme manque son pire cas reel en
raisonnant par lettres plutot que par largeur de glyphes rendus (`ADR-0026`). Toute mesure de
conformite a cette regle se fait sur le rendu reel, par la methode deja pratiquee dans ce depot
(capture a une largeur de viewport donnee, pli lu a une hauteur donnee, position des boites et des
mots relevee sur le rendu), jamais sur un compte de caracteres de l'enonce ou de l'indice.

**3. Cette regle est nommee ; sa mise en oeuvre ne l'est pas.** Je ne choisis pas ici comment elle
se garde (revue humaine, mesure outillee, regle automatisee) : c'est une question technique, routee
a `theo-archi` par `sofia-sm` le moment venu, dans l'ordre habituel (`squad/README.md` section 9).
Je ne choisis pas non plus, pour le paquet qui a revele le trou, entre reduire la longueur des cinq
enonces les plus longs (jugement pedagogique, `nadia-prof`) et traiter la hauteur de l'ecran par la
mise en page (habillage, `theo-archi` et `ines-design`) : les deux pistes restent ouvertes et ce
choix n'est pas fait par cette decision.

## Justification pedagogique

Transverse : cette decision ne juge aucun contenu et ne fait bouger aucune ligne de la matrice. Le
motif qui la justifie est enonce par `nadia-prof` elle-meme - un enfant qui vient d'echouer trois
fois de suite et recoit son aide est celui qui est le moins en etat de comprendre qu'il doit encore
faire defiler la page pour trouver ou repondre. Le jugement sur ce qui constitue une aggravation
pedagogique reste entierement le sien ; je ne fais que fixer le viewport de reference et la
propriete a garder, qu'elle a demandee et pas choisie elle-meme.

## Impact

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants" : nouvelle intention decrivant le
  defaut de "Le bon petit mot" (trois items sur vingt-quatre, symptome nomme, deux pistes de
  correction non tranchees) et renvoyant a cette decision pour la regle. **Ce n'est pas une story
  prete a developper ce sprint** : `nadia-prof` dit elle-meme que ce constat n'est pas une raison de
  ralentir S7-1, le Done de "Le bon petit mot" reste accorde et rien ne bloque, et le sprint 7 porte
  deja le chantier machine a sous, la correction des cinq indices de "Le bon verbe" et son repli de
  mise en page. Son entree a un sprint donne reste une decision de capacite de `sofia-sm`.
- `squad/programme/MATRICE-couverture.md` : inchangee, cette decision ne porte aucun contenu
  pedagogique.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0016 ajoutee.
- `squad/conformite/FICHE-bandit-manchot-homophones-cm1.md` : le Done pedagogique et le statut
  `couvert-valide` restent inchanges par cette decision, comme deja pose par `nadia-prof`.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique sur la maniere de garder cette regle (revue, mesure
  outillee, automate) : `theo-archi`.
- Elle ne choisit pas, pour "Le bon petit mot", entre reduire la longueur des enonces (jugement
  pedagogique, `nadia-prof`) et traiter la hauteur de l'ecran par la mise en page (`theo-archi`,
  `ines-design`).
- Elle ne retire aucun jeu du catalogue et ne modifie aucun Done pedagogique deja prononce.
- Elle n'affecte aucune story a un sprint : cette decision est de perimetre et de regle, pas de
  capacite.

## Amendement du 2026-08-11 (s08) - le meme defaut mesure sur deux jeux a des severites tres differentes, une story ouverte au backlog, pas de seuil chiffre

`nadia-prof` a remesure ce defaut sur le chantier du cabinet (S8-1 a S8-3) et sur l'increment de
la conjugaison etendue (S8-4), tous deux livres le 2026-08-11
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, verdict du 2026-08-11, section 8,
reserve 1 ; `squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, verdict du
2026-08-11, section 7). Elle refuse d'en faire un motif de refus sur l'un ou l'autre jeu, pour la
raison qu'elle a posee elle-meme a l'ouverture de cette decision : elle ne peut pas appliquer deux
barres a un seul defaut. Elle ecrit neanmoins que c'est cette ligne, et non un ecart de largeur de
quelques pixels par ailleurs mesure le meme jour, qui fait le plus de mal a l'enfant, et que cette
decision "merite mieux qu'une intention non ouverte au backlog".

**Les chiffres, corriges avant d'etre compares.** Les deux fiches ne comptent pas sur la meme
base, et je ne les compare pas telles quelles :

| Jeu | Etats sans indice, en defaut | Etats avec indice, en defaut | Sur l'ensemble des etats |
|---|---|---|---|
| "Le bon petit mot" (24 items, jeu catalogue, Done accorde) | 0 / 8 | 11 / 22 | 11 / 30 |
| "Le bon verbe" (25 items, activation refusee, voir `DEC-0018`) | 0 / 8 | 24 / 25 | 24 / 33 |

Le defaut ne survient jamais sans indice, sur aucun des deux jeux (mesure confirmee) : la seule
base comparable est donc "etats avec indice", puisque c'est elle seule que le defaut touche. Sur
cette base, "Le bon petit mot" est en defaut sur la moitie de ses etats avec indice (50 %), "Le bon
verbe" sur la quasi-totalite des siens (96 %). L'ecart est reel et vaut environ un facteur deux, ce
qui n'est pas rien pour un enfant qui joue - mais ce n'est pas l'ordre de grandeur qu'une lecture
rapide des deux fractions publiees ailleurs (11 sur 30, 24 sur 25) peut laisser croire : ces deux
fractions n'utilisent pas le meme denominateur, l'une comptant tous les etats d'une partie et
l'autre seulement ceux ou l'indice s'affiche. Je le corrige ici pour que personne ne compare deux
choses qui ne se comparent pas.

**Ma decision : une story au backlog, pas un seuil chiffre.**

Je n'ecris pas de seuil chiffre (par exemple "motif de refus au-dela de X % d'etats avec indice en
defaut") pour trancher a l'avenir si un cas comme celui-ci doit ou non devenir un motif. Deux
raisons :

1. **Ce jugement n'est pas le mien.** `DEC-0016` le dit deja au point 3 : "le jugement sur ce qui
   constitue une aggravation pedagogique reste entierement le sien [`nadia-prof`]". Un seuil que
   je fixerais unilateralement reviendrait a prejuger, a sa place, du point ou un defaut d'affichage
   devient inacceptable pour un enfant qui vient d'echouer trois fois - une decision pedagogique,
   pas une decision de perimetre.
2. **Un seuil chiffre invite a le viser plutot qu'a corriger la cause.** `nadia-prof` a nomme
   ailleurs (s05) le risque qu'une formulation commode a mesurer ("majorite des items") se
   substitue a l'intention qu'elle protege. Un seuil en pourcentage sur ce defaut precis courrait
   le meme risque : il recompenserait qui passe juste sous la barre plutot que qui corrige la
   longueur de l'enonce ou le budget vertical de l'ecran, les deux causes que `DEC-0016` a deja
   nommees.

A la place, j'ouvre une story au backlog (`docs/backlog/backlog.md`, section "Backlog - sprints
suivants", "Autres intentions") qui porte les deux jeux ensemble, puisque la cause est commune :
la longueur de l'enonce a 2 rem, pas le nombre de propositions ni la mise en page en colonnes
(constat d'origine de cette decision, confirme depuis). Elle n'est pas affectee a un sprint -
l'objectif de s08 reste gele (`DEC-0017`) - et reste, comme au point 3 de cette decision, ouverte
entre les deux pistes (raccourcir les enonces, jugement de `nadia-prof` ; ou agrandir le budget
vertical par la mise en page, `theo-archi` et `ines-design`).

**Ce que je maintiens, assume par ecrit : aucun des deux jeux n'est refuse sur ce seul defaut,
aujourd'hui.** Je ne le decide pas a la place de `nadia-prof` - elle l'a deja decide, sur "Le bon
petit mot" comme sur "Le bon verbe" - je le note pour qu'il soit clair que ce silence sur un motif
est une position tenue, pas un oubli. Le motif qui pese aujourd'hui sur l'activation de "Le bon
verbe" est distinct (l'ecart de largeur de quelques pixels, `DEC-0018`) et le repli en deux
rangees qui l'entoure n'est pas repris ici.

### Impact de cet amendement

- `docs/backlog/backlog.md`, section "Backlog - sprints suivants", "Autres intentions" : intention
  neuve, portant les deux jeux, ouverte a l'instruction quand `sofia-sm` juge la capacite
  disponible.
- `squad/programme/MATRICE-couverture.md` : inchangee, cette decision ne porte toujours aucun
  contenu pedagogique.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee.
- Aucun Done pedagogique ni statut de catalogue n'est modifie par cet amendement.

## Amendement du 2026-08-12 (s10) - correction d'attribution et de chiffres sur le meme constat, aucun changement de sens

Pendant l'arbitrage prealable du sprint 10, `nadia-prof` rejoue les deux jeux (60 manches sur "Le
bon verbe", 40 sur "Le bon petit mot") et remesure ce constat sur le rendu
(`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section 3, verdict du 2026-08-12). Elle
corrige deux choses qu'elle avait ecrites elle-meme le 2026-08-11, sans que le constat d'origine
change de sens.

**Correction d'attribution.** Une story de sprint a designe ce constat comme "sa reserve 1", au
risque de le confondre avec la reserve 1 de
`squad/conformite/FICHE-bandit-manchot-conjugaison-present-cm1.md`, qui est un fait distinct
(`mangeons` a 2,0 px, devenue le motif du refus du 2026-08-11, denoue par `DEC-0018`). Ce que le
present amendement traite reste ce que l'amendement du 2026-08-11 fixait deja : un constat sous
`DEC-0016`, explicitement pas une reserve qui se leverait un jour.

**Correction de chiffres.** Ses propres releves du 2026-08-11 etaient inexacts dans les deux sens :

| Jeu | Etats coupes par le pli (etait) | Bas de cabinet sans indice (etait) |
|---|---|---|
| "Le bon petit mot" | 5 / 29 (etait 11 / 30) | 454..694 (etait 450..690) |
| "Le bon verbe" | 16 / 34, tous avec indice (etait 24 / 33) | 538..634 (etait 540..636) |

Elle ecrit elle-meme ne pas savoir reconstituer la cause de l'ecart entre ses deux mesures - je le
rapporte tel quel plutot que d'en inventer une. **Le sens du constat ne change pas** : sur les deux
jeux, l'indice continue d'emporter une partie substantielle des etats ou il s'affiche, et la story
ouverte a l'amendement du 2026-08-11 (backlog, "Autres intentions", non affectee a un sprint) reste
ouverte dans les memes termes, sans seuil chiffre.

### Impact de cet amendement

- `docs/backlog/backlog.md` : aucune figure de pli n'y etait recopiee au niveau de `round-two` a
  cette date ; la meme correction reste due, dans le meme geste que sa fusion, sur toute branche non
  fusionnee qui aurait recopie les chiffres du 2026-08-11 avant cet amendement.
- `squad/decisions/produit/DEC-index.md` : note d'amendement ajoutee.
- Aucun Done pedagogique ni statut de catalogue n'est modifie par cet amendement.
