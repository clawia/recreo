# DEC-0033 - BARRE-7 : le lien "Les jeux" de l'en-tete disparait de l'accueil, il n'y est pas remplace par un comportement neuf

> **Remplacee le 2026-08-17 (s15) par `DEC-0034-utilisatrice-retablit-les-jeux-et-unifie-lapparence-
> des-puces.md`.** L'utilisatrice, apres avoir joue l'increment livre, retablit le lien "Les jeux"
> dans l'en-tete dans son etat d'avant s14, en connaissance du motif BARRE-7 que cette decision
> resolvait par le retrait. Le corps de cette decision reste tel qu'ecrit ci-dessous : il documente
> pourquoi le retrait avait ete choisi et quel motif il fermait, et c'est ce motif que l'arbitrage de
> l'utilisatrice rouvre sciemment plutot que d'ignorer. Voir `DEC-0034` pour le detail et pour la
> chronologie complete.

- **Type** : arbitrage de story, corrige un obstacle bloquant la porte pedagogique d'une story deja
  livree
- **Date** : 2026-08-17
- **Auteur** : camille-po
- **Sprint** : s14
- **Competence programme visee** : transverse - pas de contenu pedagogique. Cette decision ne
  cible aucune ligne de `squad/programme/MATRICE-couverture.md` ; elle retire un element de
  navigation devenu sans effet, elle ne touche a aucun contenu.
- **Statut** : Remplacee par DEC-0034

## Contexte

`S14-1` (`docs/backlog/backlog.md`, section "Sprint 14") est livree et fusionnee sur `round-two` :
la barre d'entree par niveau (CP / CM1) est en place dans l'en-tete, permanente sur l'accueil et
sur les etats de manche, et un appui pendant une partie passe par l'ecran de confirmation. Huit des
neuf conditions bloquantes de l'arbitrage prealable de `nadia-prof`
(`squad/conformite/FICHE-barre-entree-niveau.md`, section 6, arbitrage du 2026-08-17) sont tenues.
**BARRE-7 reste ouverte** et bloque le Done pedagogique de la story entiere.

**Ce que BARRE-7 constate.** Le lien `a.lien-accueil` de l'en-tete, libelle "Les jeux"
(`data-role="sortie"`, `hx-post="/retour"`), n'est visible sur aucun ecran de jeu - il y est masque
par la regle `body:has(.ecran-de-jeu) .lien-accueil { display: none }` (`ADR-0022`), qui couvre
aujourd'hui les cinq etats de manche, la confirmation, la fin de partie et l'erreur. Le seul ecran
ou ce lien est visible est donc l'accueil lui-meme (filtre ou non, avec ou sans reprise). Sur
l'accueil sans filtre - l'etat par defaut, celui de tout premier chargement - appuyer sur ce lien
rend exactement le meme document : le lien ne fait rien, parce que l'enfant est deja a l'endroit
qu'il pretend rejoindre. `nadia-prof` avait deja constate ce defaut le 2026-08-14 (PORTE-1,
`DEC-0027`, amendement du meme jour) sans le rendre bloquant a l'epoque ; `S14-1` le rend
bloquant, pour un motif qu'elle nomme elle-meme et que je reprends sans le rejuger : la meme zone
d'en-tete installe desormais, a cote de ce lien inerte, deux puces de niveau qui, elles, repondent.
Un enfant qui presse l'une puis l'autre boite voisine apprend que la seconde est la sortie
uniquement parce que la premiere ne fait rien - ce n'est pas une lecture qu'on peut lui demander de
faire.

`nadia-prof` nomme deux issues acceptables sans trancher entre elles (fiche citee, BARRE-7) : cette
boite disparait de l'accueil, ou elle y fait autre chose que rendre l'ecran identique. Le choix
m'appartient. `sofia-sm` a etabli que le Done pedagogique complet de `S14-1` ne peut pas etre
demande a `nadia-prof` avant que ce choix soit fait et applique.

## Ce que je verifie avant de trancher

J'ai relu `PartieController` et `accueil.html` du worktree plutot que de reprendre la description
de la fiche sur parole :

- Le lien de l'en-tete n'est visible que lorsque le contenu rendu par `#jeu` ne porte pas le
  marqueur `ecran-de-jeu` - c'est-a-dire uniquement sur `accueil :: zoneAccueil` (catalogue complet
  ou filtre par niveau, message de reprise perdue compris). Il est deja absent, par la regle
  existante, des trois etats de manche, de la confirmation, de la fin de partie et de l'erreur.
- Sur l'accueil **filtre** par un niveau, appuyer sur ce lien n'est plus un no-op depuis `S14-1` :
  `POST /retour` rend `jeuxDisponibles()`, le catalogue complet, qui differe de la liste filtree
  affichee. Le defaut mesure par `nadia-prof` ne subsiste donc que sur l'accueil **non filtre**,
  c'est-a-dire l'etat de tout premier chargement de l'application et celui auquel toute navigation
  finit par revenir.
- Un comportement qui "ferait quelque chose" resoudrait donc, au mieux, le seul cas filtre - deja
  resolu par construction - et laisserait intact le cas non filtre, qui est le plus frequent et
  celui que `nadia-prof` a mesure. Faire disparaitre cette boite est la seule des deux issues qui
  ferme BARRE-7 dans tous les etats de l'accueil a la fois, sans etat intermediaire ou le lien
  repondrait tantot, ne repondrait pas tantot - une incoherence qui serait elle-meme une source de
  confusion neuve.

## Decision

**BARRE-7 est resolue par la premiere issue que `nadia-prof` a declaree acceptable : le lien "Les
jeux" de l'en-tete (`a.lien-accueil`, `data-role="sortie"`, `nav.menu`) est retire de tout ecran
rendu par l'application, accueil compris.** Il n'est pas remplace par un comportement neuf.

Motif, en deux temps :

1. Ce lien n'est aujourd'hui visible que sur l'accueil - toutes les autres regles de masquage
   d'`ADR-0022` l'excluent deja des ecrans de jeu. Le retirer de l'accueil revient donc a le
   retirer de partout ou il apparait encore, sans toucher a aucun autre ecran ni a aucune autre
   regle de masquage.
2. Lui inventer un second comportement ("elle y fait quelque chose") ne fermerait BARRE-7 que sur
   l'accueil filtre, ou le defaut est deja resolu par le filtrage lui-meme depuis `S14-1` ; l'etat
   non filtre - le point d'entree de toute session - resterait un lien qui repond ou non selon un
   etat que l'enfant ne voit pas. Retirer la boite ferme les deux etats du meme geste et n'ajoute
   aucun mecanisme.

**Ce que cette decision ne change pas.** Le mot "Les jeux" reste le seul mot de sortie de
l'application (`ADR-0022` point 3, `DEC-0027`) : il continue de porter la sortie de la zone de
contenu sur l'ecran de fin de partie (`sortie-pleine`, PORTE-2 de `DEC-0027`) et le bouton de
confirmation d'abandon (`retour-accueil`, `fragments/confirmation.html`). Cette decision retire
uniquement l'exemplaire de l'en-tete, jamais visible ailleurs qu'a l'accueil et jamais outil de
sortie d'une partie en cours au sens ou `ADR-0022` l'entend.

## Ce que cette decision ne fait pas

- Elle ne choisit aucun moyen technique de retrait (suppression de l'element du gabarit, ou
  extension permanente de la regle de masquage a l'accueil) - route a `theo-archi` par `sofia-sm`,
  avec l'impact que ce choix a sur `LActionNeDeclarePasMoinsQueLaSortieTest` et sur
  `EnTeteEtMenuTest.leMenuDeLEnTeteContientExactementUneEntreeVersLAccueil`, qui comptaient jusqu'ici
  ce lien dans leur population.
- Elle ne prononce aucun Done pedagogique. Le Done pedagogique complet de `S14-1` - BARRE-1 a
  BARRE-9 rejouees, y compris BARRE-7 sous la forme decidee ici - reste entierement a `nadia-prof`,
  sur `squad/conformite/FICHE-barre-entree-niveau.md`.
- Elle n'ouvre et ne ferme aucune ligne de `squad/programme/MATRICE-couverture.md`.
- Elle ne tranche pas les deux points que `sofia-sm` route par ailleurs (reserve J des grands
  nombres CM1, nomenclature accentuee de la matiere) : hors perimetre de cette decision, voir note
  de routage ci-dessous.

## Impact

- `docs/backlog/backlog.md` : story neuve `S14-2 - Le lien "Les jeux" de l'en-tete disparait, il ne
  survit qu'a l'accueil ou il ne repondait plus a rien`, section "Sprint 14", avec ses criteres
  d'acceptation et sa porte de sortie.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0033 ajoutee.
- `squad/decisions/produit/DEC-0027-retrait-bouton-sortie-fragment-manche-reouvre-s6-9-arbitrage-
  prealable-requis.md` : note datee ajoutee, PORTE-1 (2026-08-14) est desormais fermee par cette
  decision.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- `squad/conformite/FICHE-barre-entree-niveau.md` : inchangee par cette decision ; le verdict sur
  BARRE-7 y reste celui deja ecrit par `nadia-prof`, cette decision ne fait qu'exercer le choix
  qu'elle m'a laisse.
- Signale a `sofia-sm`, hors de ce fichier : saisine de `theo-archi` sur le moyen technique de
  retrait et sur l'impact aux deux garde-fous cites plus haut, une fois `S14-2` affectee au
  planning.

## Ce qui reste hors perimetre de cette decision

Deux points routes par `sofia-sm` avec l'obstacle BARRE-7, traites separement :

- **La reserve J des grands nombres CM1** (R-BARRE-1, `FICHE-barre-entree-niveau.md`, section 7),
  aggravee par la barre de niveaux : reste une reserve non bloquante, deja routee comme candidate
  de story pour un sprint a venir. Non traitee ici pour ne pas elargir ce lot correctif.
- **La nomenclature accentuee de la matiere** (`DEC-0032`, section 2) : question posee pour
  l'instruction de s15, qui touche a la fois une decision technique (ou vit la correspondance) et
  une decision de redaction. Non traitee ici : l'avis consultatif de `theo-archi` est deja versé
  dans `DEC-0032`, la decision elle-meme reste a rendre avant que s15 n'instruise le filtre par
  matiere.
