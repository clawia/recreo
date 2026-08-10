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
