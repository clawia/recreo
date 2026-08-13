# DEC-0023 - Le perimetre du sprint 10 est repris sur les references de l'utilisatrice : la machine est redessinee en CSS pur, le veto sur la manette est leve, l'ecran intermediaire est questionne

- **Type** : portee
- **Date** : 2026-08-12
- **Auteur** : camille-po
- **Sprint** : s10
- **Competence programme visee** : transverse - habillage de la mecanique (`ADR-0004`), aucun
  contenu pedagogique hors le texte de l'enseigne (point 4), qui suit la porte pedagogique
  ordinaire d'un texte lu par un enfant. Aucune ligne de `squad/programme/MATRICE-couverture.md`
  ne bouge par cette decision.
- **Statut** : Acceptee sur les points 1, 2, 3, 4 et 5 ; le point 6 est ouvert, non tranche, en
  attente d'arbitrage prealable de `nadia-prof` (section "Decision", point 6, ci-dessous).

## Contexte

`DEC-0022` a acte, le matin du 2026-08-12, un perimetre construit sur une silhouette de manche
dessine sur le fronton, validee par `nadia-prof` en arbitrage prealable. L'utilisatrice a vu ce
rendu dans l'application reelle et l'a refuse : a la taille du fronton, la forme se lit comme un
petit rectangle blanc arrondi, et agrandie comme une arche - jamais comme une poignee. Ce constat
n'est pas nouveau : `nadia-prof` l'avait deja ecrit elle-meme dans son arbitrage du 2026-08-12
("la forme rendue ne se lit pas comme une poignee, mais comme une arche"), en precisant
explicitement que ce n'etait pas un motif de refus mais une information que l'utilisatrice, qui a
demande un manche dessine, devait pouvoir contester. Elle la conteste.

Elle a fourni en reponse deux images de reference (un meuble d'arcade illustre, version CP en bleu
et jaune et version CM1 en laiton et bois sombre : fronton avec enseigne et visage souriant, trois
rouleaux, rangee de boutons colores, bac de gains, levier a boule) et pris six decisions sur ce
qu'elle en retient. Ce document les instruit une par une, dans son propre role : trancher le
produit, nommer le cout de chacune, et router ce qui ne lui appartient pas.

## Decision

### 1. La machine est redessinee en CSS pur, a partir des references

Aucun fichier image n'entre au depot : le garde-fou qui interdit toute ressource tierce
(`RessourcesTiercesTest`) n'est pas amende, et cette decision ne le rouvre pas. Ce que
l'utilisatrice n'a pas eu a arbitrer parce que rien ne s'y oppose : un corps en deux tons, des
flancs decores d'etoiles et d'arcs-en-ciel, une rangee de boutons colores sous les rouleaux, du
relief, des coins arrondis. Ces elements sont de la peau de la machine au sens de la condition C5
de `nadia-prof` (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage du
2026-08-11) : fixes, ne variant ni avec l'item tire ni avec la colonne, jamais a l'interieur d'une
boite de proposition. Rien ici ne demande de lever une condition existante.

**Ce que cette decision ne tranche pas** : le dessin exact (`ines-design` propose), la mesure de
son cout en pixels (0 px de hauteur et de largeur ajoutes reste l'exigence, tenue depuis le
sprint 8) et le jugement sur le rendu (`nadia-prof`, en arbitrage prealable avant toute ligne de
code, l'ecran etant deja accorde - `squad/README.md` section 10).

### 2. Pas de bac de gains - confirmation, aucun developpement

La condition C6 de `nadia-prof` l'interdit au mot pres depuis le 2026-08-10 et le 2026-08-11 :
"pas de recepteur de gain ni de coupelle" (`FICHE-bandit-manchot-additions-cp.md`, arbitrage du
2026-08-11, section 2). L'utilisatrice ne demande pas de lever cette interdiction. Ce point ne
donne lieu a aucune story : il n'y a rien a construire, seulement a ne pas construire.

### 3. Un levier a boule, dessine et immobile, sur le flanc du meuble - le veto de `nadia-prof` sur la manette est leve

**C'est le point le plus lourd de cette decision, et je l'ecris comme une levee nommee.**

`nadia-prof` a pose, le 2026-08-10 puis confirme le 2026-08-11, un veto categorique et non un
simple motif de refus : "pas de manette ni de bras lateral (mon veto sur tout geste de traction)"
(C6, section 2 de son arbitrage du 2026-08-11), et elle l'assume nommement dans la section
"La manette : oui, c'est mon veto, et je l'assume nommement" de la meme fiche. Ses deux motifs,
qu'elle qualifie elle-meme d'inchanges : *"un enfant de CP qui essaie de tirer une manette et voit
que rien ne se passe se croit en faute ; et un geste de glissement est un obstacle de motricite
fine sur le bouton le plus presse de l'application, en plus d'etre un obstacle pour un enfant qui
utilise une aide technique."* Ce veto portait sur toute silhouette lisible comme manette ou bras
lateral, decorative ou non - pas seulement sur un controle interactif.

**Ce que l'utilisatrice tranche ici** : elle veut ce levier, en connaissance de ce cout, sur le
flanc du meuble plutot que sur le fronton (la forme illisible a cette taille). `nadia-prof` a
elle-meme ecrit le principe qui gouverne ce cas : *"si l'utilisatrice tranche qu'elle veut la
manette malgre eux, mon veto ne survit pas a sa decision - je ne detiens pas la porte produit, et
je prefere l'ecrire que de laisser croire que je la detiens."* Son veto est donc leve par cette
decision, pas par un jugement pedagogique que je me substituerais a rendre.

**Ce qu'elle perd, et ce qui n'est pas resolu par la levee du veto** : le second motif de
`nadia-prof` (obstacle de motricite fine et d'accessibilite) portait sur un geste reellement
actionne - il ne s'applique pas ici, puisque ce levier reste **dessine et immobile**, sans
`data-role`, jamais un `<button>`, jamais dans le parcours clavier des elements pressables ; le
bouton qui fait avancer le jeu reste `.demarrer` ou `.encore`, inchange (`ADR-0026` section 3).
Le premier motif, lui, n'est pas eteint par l'immobilite : un enfant peut tenter de toucher ou de
tirer une forme qui ressemble a un levier, et rien ne se passera. Ce risque residuel n'est pas
tranche par cette decision - il reste a la vigilance de `nadia-prof` en arbitrage prealable sur la
silhouette retenue, exactement comme pour tout element visuel de ce sprint.

**Criteres attendus de la story qui portera ce point** (a instruire avec `ines-design` et
`theo-archi`, non codes ici) :
- le levier est porte par un pseudo-element decoratif du flanc, en `position: absolute`, sans
  ajouter de pixel de hauteur ni de largeur au cabinet rendu (meme exigence que le manche du
  fronton refuse, mesuree par `outils/mesurer-rendu.sh`) ;
- aucune regle ne lui declare `animation`, `transition`, ni de pseudo-classe `:hover`, `:focus`
  ou `:active` propre (condition C3, "la machine attend, elle ne decide pas") ;
- il n'est jamais un `<button>` ni porteur de `data-role`, et n'entre donc pas dans la derivation
  `button, a[href]` d'`ADR-0017` ;
- la geometrie (fut surmonte d'une boule, embase fixee au flanc) est dessinee en position de
  repos, jamais a mi-course ni inclinee - rien dans le dessin ne suggere un mouvement en cours.

### 4. Une enseigne sur le fronton, nom fixe a valider par `nadia-prof`

Le nom est un texte lu par un enfant : il suit la porte pedagogique ordinaire de tout contenu
destine a l'enfant, pas cette decision. La seule contrainte que je porte ici est celle que
l'utilisatrice a fixee elle-meme : aucun mot de la liste interdite qu'elle a nommee ne peut y
figurer - `bandit`, `manchot`, `machine a sous`, `jackpot`, `mise`, `piece`, `jeton`, `credit`,
`gain`, `levier`, `manette`, `fente`, `coupelle`, `pari`, `tirage` - liste qui recouvre et
prolonge celle que `nadia-prof` tient depuis le 2026-08-10 (C1, section 1 de son arbitrage) et
le 2026-08-11 (C6).

Je propose trois noms a la validation de `nadia-prof`, chacun verifie contre la liste interdite et
en francais correct integralement accentue :

1. **"Le Copain Malin"** - personnifie la machine, coherent avec le visage souriant du point 5.
2. **"Tourne-Cervelle"** - joue sur le mouvement des rouleaux sans aucune reference a l'argent ni
   au hasard.
3. **"La Boite a Bonnes Reponses"** - le plus litteral des trois, decrit la fonction sans
   ambiguite.

Aucun des trois n'est retenu par cette decision : c'est `nadia-prof` qui juge, comme pour tout
texte enfant, et elle peut en proposer un autre.

### 5. Un visage souriant sur le fronton

Rien ne l'interdit. Une seule contrainte, sur le meme modele que la condition C4 de `nadia-prof`
("la machine ne dit jamais le resultat") : le visage est rigoureusement identique apres une bonne
et apres une mauvaise reponse - il ne reagit jamais, il ne devient donc jamais lui-meme un second
canal de verdict.

### 6. L'ecran intermediaire disparait - ouvert, non tranche, arbitrage prealable requis avant tout code

Bonne reponse : la question suivante se charge directement. Mauvaise reponse : l'enfant reste sur
l'ecran de la machine, et un message s'affiche sous le meuble.

**Je ne tranche pas ce point : je pose les trois questions qui doivent etre arbitrees par
`nadia-prof` avant qu'une ligne de code ne soit ecrite**, conformement a `squad/README.md`
section 10 (toute story qui modifierait un ecran ou un comportement deja accorde par elle est
signalee pour arbitrage prealable, pas seulement auditee apres coup). L'ecran de retour
(`fragments/retour.html`) est un ecran deja accorde depuis le sprint 1.

1. **Que dit le message sous le meuble ?** Une formule du type "Ce n'est pas ca, essaie encore"
   sans reveler la bonne reponse ? Ou revele-t-il apres un certain nombre d'essais - et si oui,
   lequel, en articulation avec le seuil de trois echecs consecutifs qui declenche deja un indice
   (`DEC-0004`, S1-5) ?
2. **L'explication survit-elle, et sur quel ecran ?** `nadia-prof` a tranche le 2026-08-06 qu'elle
   s'affiche systematiquement, juste ou faux - *"l'enfant qui a trouve DENT par elimination a
   autant besoin de l'explication que celui qui s'est trompe"*. Sans ecran de retour separe pour
   une bonne reponse (la question suivante se charge directement), cette explication n'a plus de
   support evident.
3. **Si l'enfant reste sur la question, la proposition fautive devient-elle inactive, ou reste-t-
   elle cliquable ?** `ADR-0026` l'interdit aujourd'hui par deux clauses executables : aucune regle
   maison permanente ne rend une action inactionnable (clause 3) et aucun gabarit ne desactive un
   bouton porteur de `data-role` (clause 4). Amender ces clauses n'est pas de mon ressort.

Ces trois questions sont routees a `nadia-prof` par `sofia-sm`. Aucune story de developpement
n'est ouverte sur ce point avant sa reponse.

## Ce que cette decision fait de `DEC-0022`

`DEC-0022` reste au depot, non efface. Deux de ses trois exclusions tombent, une tient et change
de place :

- **Point 1 de `DEC-0022` ("reessayer la meme question apres une mauvaise reponse") - tombe.** Le
  point 6 ci-dessus reintroduit exactement ce cas : sur une mauvaise reponse, l'enfant reste sur
  le meme ecran plutot que d'enchainer sur une nouvelle question. Le cout que `DEC-0022` avait
  chiffre pour l'ecarter (etat neuf de `BanditManchotSession`, sort du seuil des trois echecs,
  amendement des clauses 3 et 4 d'`ADR-0026`, retrait de la revelation immediate de la bonne
  reponse) redevient un cout reel a instruire, pas un motif d'exclusion : c'est l'objet des
  questions 1 et 3 ci-dessus.
- **Point 3 de `DEC-0022` ("un message d'erreur sous la machine") - tombe.** `DEC-0022` l'ecartait
  au nom de la condition 3 de l'arbitrage prealable de `nadia-prof` du 2026-08-10 (*"le seul retour
  de resultat du jeu reste celui qui existe [...] le chantier n'ajoute aucun second canal de
  verdict"*) et de sa condition C4 du 2026-08-11. Le point 6 de cette decision **est** ce second
  canal, nommement. Cette contradiction n'est pas resolue par ce document : elle est la matiere de
  la question 1 ci-dessus, que je route plutot que de la trancher a la place de `nadia-prof`.
- **Point 2 de `DEC-0022` ("un manche qui s'actionne a la bonne reponse") - tient, change de
  place.** Le manche reste dessine et immobile, jamais actionne par un geste de l'enfant ni par
  une reponse : c'est exactement ce que le point 3 de cette decision reconduit, sur le flanc
  plutot que sur le fronton. La condition C3 de `nadia-prof` ("la machine attend, elle ne decide
  pas") n'est pas rouverte.

## Correction de fait, portee dans la reecriture des stories

Le backlog reecrit par cette decision (`docs/backlog/backlog.md`, section "Sprint 10") corrige
deux erreurs deja identifiees et corrigees ailleurs, pour ne pas les reintroduire dans les stories
neuves : le contraste cadre/page mesure par `nadia-prof` est **8,61:1**, pas 9,25:1 (elle avait
mesure ce dernier chiffre contre le blanc pur et non contre le fond de page, erreur qu'elle a
elle-meme corrigee) ; et les etats coupes par le pli sont **5 sur 29** pour "Le bon petit mot" et
**16 sur 34** pour "Le bon verbe" apres sa remesure du 2026-08-12, un **constat trace sous
`DEC-0016`** et non "la reserve 1 de `nadia-prof`" - cette derniere formulation, que j'avais
propagee, attribuait a tort a une reserve nommee ce qui est un constat de mesure.

## Ce qui reste dehors, explicitement

- La forme exacte de tous les elements visuels (levier, boutons, etoiles, arcs-en-ciel, relief,
  coins arrondis, enseigne, visage) : `ines-design` propose, `nadia-prof` juge en arbitrage
  prealable avant toute ligne de code.
- Le nom retenu de l'enseigne : trois candidats proposes ci-dessus, aucun tranche.
- Les trois questions du point 6 : routees, non arbitrees.
- La reserve 2 de `nadia-prof` sur le contraste cadre/colonnes (1,71:1 mesure contre 3,20:1 vise)
  n'est pas rouverte par cette decision au-dela de la story qui la porte deja au backlog
  (voir "Sprint 10" du backlog reecrit) : elle est independante des six points ci-dessus.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge.

## Justification pedagogique

Transverse sur les points 1, 2, 3 et 5 : aucun jugement de contenu, le jugement sur le rendu
livre reste entierement celui de `nadia-prof`. Le point 4 (nom de l'enseigne) est un texte enfant
et suit la porte pedagogique ordinaire, pas cette decision - je propose des noms, je ne les valide
pas. Le point 6 n'est pas justifie ici : c'est precisement ce qui reste a arbitrer, et je ne me
substitue pas au jugement pedagogique de `nadia-prof` sur la revelation de la bonne reponse ni sur
le sort de l'explication.

## Impact

- `docs/backlog/backlog.md`, section "Sprint 10" reecrite : quatre stories de developpement
  (levier sur le flanc, habillage redessine, enseigne et visage, garde-fou d'immobilite), une
  story de contraste conservee (reserve 2), et un point ouvert non affecte a un sprint (le point
  6, en attente d'arbitrage prealable).
- Aucun changement a `squad/programme/MATRICE-couverture.md`.
- `squad/decisions/produit/DEC-index.md` : lignes `DEC-0022` et `DEC-0023` a jour.
- Routage a `sofia-sm`, dans le meme geste que cette decision : affectation des quatre stories de
  developpement au planning, et transmission des trois questions du point 6 a `nadia-prof` pour
  arbitrage prealable avant toute story de developpement sur ce point.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la forme exacte de chaque element, l'implementation
  du garde-fou d'immobilite et la mise en oeuvre du contraste appartiennent a `theo-archi`, a
  `ines-design` et aux developpeurs, saisis par `sofia-sm`.
- Elle ne prononce aucun Done pedagogique : ce jugement reste entierement celui de `nadia-prof`,
  en arbitrage prealable puis sur le rendu livre.
- Elle ne tranche pas le nom de l'enseigne ni le sort de la revelation, de l'explication et de la
  proposition fautive du point 6 : ces jugements restent a `nadia-prof`.
- Elle ne rouvre pas la condition C3 de `nadia-prof` ("la machine attend, elle ne decide pas") : le
  levier du point 3 reste dessine et immobile, comme le manche qu'il remplace.

## Alternatives considerees

**Ecrire une story de developpement complete sur le point 6, en anticipant les reponses de
`nadia-prof`.** Ecartee : ce serait deviner un arbitrage pedagogique a sa place, exactement ce que
je n'ai pas autorite a faire. Les trois questions sont ecrites pour qu'elle y reponde, pas pour
que je les tranche a sa place.

**Garder le manche sur le fronton et ne changer que sa forme.** Ecartee par l'utilisatrice
elle-meme : le constat qu'a cette taille toute forme se lit mal (petit rectangle ou arche) est un
constat de taille, pas de dessin ; deplacer l'element sur le flanc, plus grand, est la reponse
qu'elle a choisie plutot que d'iterer sur la meme surface contrainte.
