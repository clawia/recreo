# DEC-0022 - Perimetre du sprint 10 : le manche du bandit manchot est dessine et immobile, trois demandes de l'utilisatrice ecartees avec leurs declencheurs de reouverture

- **Type** : portee
- **Date** : 2026-08-12
- **Auteur** : camille-po
- **Sprint** : s10
- **Competence programme visee** : transverse - habillage de la mecanique (`ADR-0004`), aucun
  contenu pedagogique. Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge par cette
  decision.
- **Statut** : Acceptee

## Contexte

**Le chantier visuel avait ete arrete, pas abandonne.** A l'ouverture du sprint 9, j'ai ecrit dans
`docs/backlog/backlog.md` (section "Autres intentions") que l'utilisatrice jugeait le rendu CSS de
l'increment du sprint 8 insatisfaisant et ne voulait plus de cycle de design pour l'instant : ni
nouvelle mesure de rendu, ni nouveau garde-fou CSS, ni arbitrage visuel d'`ines-design` ou de
`nadia-prof` sur des pixels. Le sprint 9 a livre quatre jeux de pur contenu sous cette consigne.
**Elle rouvre maintenant ce chantier**, et le sprint 10 lui est dedie : la forme du bandit manchot.

Sa demande initiale portait cinq elements. L'exploration a etabli que deux sont deja livres - la
machine dessinee au centre avec la question au-dessus depuis S8-1, les rouleaux qui balaient a la
question suivante depuis s07 - et que les trois autres coutaient chacun soit un amendement de
garde-fou, soit un acquis pedagogique deja pose. Les trois lui ont ete soumis avec leur cout, et
elle a tranche les trois dans le meme sens : elle les ecarte.

## Decision

### 1. Reessayer la meme question apres une mauvaise reponse - Ecarte

**Ce que ca aurait coute**, nomme avant d'etre ecarte : un etat neuf dans `BanditManchotSession`,
l'arbitrage de ce qui compte comme une tentative, le sort du seuil des trois echecs consecutifs qui
declenche l'indice (`DEC-0004`), l'amendement des clauses 3 et 4 d'`ADR-0026` (qui interdisent
respectivement de rendre une action inactionnable et de desactiver un bouton porteur de
`data-role`, sur les etats qu'une re-tentative introduirait), et le retrait de la revelation
immediate de la bonne reponse que l'ecran de retour porte aujourd'hui - puisque rejouer la meme
question suppose de ne pas encore la reveler.

**Declencheur de reouverture** : cet element se rouvre si l'utilisatrice le redemande en
connaissance de ce cout architectural (l'amendement d'`ADR-0026` devient alors un prealable
ecrit, pas une consequence decouverte en cours de sprint), ou si `nadia-prof` nomme un besoin
pedagogique precis qu'une seule tentative desservirait sur un jeu donne - ce qu'elle n'a pas
nomme a ce jour.

### 2. Un manche qui s'actionne a la bonne reponse - Ecarte, le manche sera dessine et immobile

**Ce que ca aurait coute**, nomme avant d'etre ecarte : la reouverture du refus chiffre
qu'`ADR-0026` section 3 a deja pose et budgete pour le levier sur l'ecran de manche lui-meme (un
etat de domaine de plus dans `BanditManchotSession`, une route de plus, un aller-retour de plus par
manche) ; l'amendement de la clause 9 d'`ADR-0030` ("toute animation maison s'applique a un
element pressable ou a son pseudo-element decoratif, jamais a un habillage") ; et la condition C3
de `nadia-prof` (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, arbitrage du 2026-08-11,
section 2), dont elle ecrit elle-meme qu'elle est "la charniere de tout mon avis" : "une machine
qui s'anime toute seule agit ; une machine immobile est un meuble que l'enfant actionne. Le jour ou
le cabinet pulse en attendant, l'argument qui sauve la metaphore tombe, et mon avis avec lui."

**Declencheur de reouverture** : `ADR-0026` a deja ecrit le sien, mot pour mot, dans sa section "Ce
qui me ferait changer d'avis" - "`nadia-prof` constate en jouant que le geste du levier manque de
sa cause visible [...] La reponse serait le levier sur l'ecran de manche, avec son etat de domaine,
sa route et son cout." Ce chemin n'a pas besoin d'etre reinvente ; il est deja chiffre. A cela
s'ajoute une condition propre a ce sprint : la reouverture suppose aussi que `nadia-prof` revise
elle-meme sa condition C3, puisqu'un manche qui reagit a la reponse est exactement le cas qu'elle
nomme comme faisant tomber son avis.

### 3. Un message d'erreur sous la machine - Ecarte, le canal existant est conserve

**Ce que ca aurait coute**, nomme avant d'etre ecarte : la condition 3 de l'arbitrage prealable de
`nadia-prof` du 2026-08-10 ("Le seul retour de resultat du jeu reste celui qui existe : la coche ou
la croix de `fragments/retour.html`, apres le choix de l'enfant. Le chantier n'ajoute aucun second
canal de verdict.") et la condition C4 de son arbitrage du 2026-08-11 ("La machine ne dit jamais le
resultat [...] le seul canal de verdict reste la coche ou la croix de `fragments/retour.html`,
apres le choix de l'enfant.").

**Declencheur de reouverture** : cet element se rouvre si une des deux conditions ci-dessus est
elle-meme revisee par `nadia-prof` - ce qui suppose qu'elle identifie un besoin pedagogique qu'un
second canal de verdict porte et que le canal existant ne porte pas - ou si l'utilisatrice demande
explicitement un second canal en connaissance de ce que les deux conditions protegent (l'arret des
colonnes reste neutre, la machine ne rend jamais un verdict a la place de l'ecran de retour).

### Un fait de gouvernance a tracer

L'utilisatrice n'a pas eu a exercer de veto sur `nadia-prof` ici, puisqu'elle a ecarte les trois
elements en tension elle-meme. Mais `nadia-prof` a deja pose par ecrit, sur le refus de la manette
(geste de traction) du 2026-08-10, le principe qui gouvernerait un desaccord futur : "si
l'utilisatrice tranche qu'elle veut la manette malgre eux, mon veto ne survit pas a sa decision -
je ne detiens pas la porte produit, et je prefere l'ecrire que de laisser croire que je la
detiens." Ce principe n'est pas rejoue ce sprint, et je l'inscris ici pour qu'il ne soit pas
redecouvert au sprint suivant : une demande visuelle future pourrait mettre l'utilisatrice et
`nadia-prof` en desaccord reel, et cette decision-ci trace que la porte finale reste produit, pas
pedagogique, sur ce type de question - sans que cela retire jamais a `nadia-prof` sa seule autorite
sur le Done pedagogique.

### Le perimetre technique retenu

Quatre stories, `docs/backlog/backlog.md`, section "Sprint 10" : `S10-1` a `S10-4`. Le perimetre
technique tient dans un seul fichier de production,
`adapters/adapter-web/src/main/resources/static/css/recreo.css` ; `fragments/manche.html` n'est pas
modifie.

Le fait qui commande le dimensionnement de `S10-1`, et qui doit figurer dans ses criteres : le
verdict de `nadia-prof` du 2026-08-11 mesure que le cabinet ajoute 40 px de hauteur, sous sa cible
de 100 px et sa limite dure de 150 px - mais sa reserve 1 est ouverte, et elle est serree : le bas
du cadre passe deja sous le pli sur 11 etats sur 30 de "Le bon petit mot" et 24 sur 33 de "Le bon
verbe", soit 4 px de marge sur le meilleur etat du pire jeu. Le manche doit donc couter 0 px de
hauteur.

## Ce qui reste dehors, explicitement

- La forme exacte du manche (`ines-design` propose), la clause exacte du garde-fou neuf
  (`theo-archi`) et le jugement sur la silhouette (`nadia-prof`, en arbitrage prealable avant toute
  ligne de code, `squad/README.md` section 10 - cet ecran est deja accorde) ne sont pas tranches
  ici.
- La reserve 1 de `nadia-prof` sur le pli (defaut de `DEC-0016`, aggrave de 40 px par le cabinet) et
  la reserve 2 sur le contraste ne sont pas rouvertes par cette decision au-dela de ce que `S10-3`
  ferme explicitement (reserve 2 seule) : `S10-1` et `S10-2` ne font que ne pas aggraver la reserve
  1, ils ne la ferment pas.
- Aucune ligne de `squad/programme/MATRICE-couverture.md` ne bouge : ce sprint ne couvre aucune
  competence neuve.

## Justification pedagogique

Transverse : ce chantier ne juge aucun contenu et ne fait bouger aucune ligne de
`squad/programme/MATRICE-couverture.md`. Les trois arbitrages ci-dessus sont des arbitrages de
l'utilisatrice sur un cout qui lui a ete soumis, pas des jugements pedagogiques que je me
substituerais a rendre ; le jugement sur ce qui sera livre (la silhouette du manche, le contraste
corrige) reste entierement celui de `nadia-prof`.

## Impact

- `docs/backlog/backlog.md`, nouvelle section "Sprint 10" : quatre stories (`S10-1` a `S10-4`).
- Aucun changement a `squad/programme/MATRICE-couverture.md`.
- `squad/decisions/produit/DEC-index.md` : ligne `DEC-0022` ajoutee.

## Ce que cette decision ne fait pas

- Elle ne tranche aucune question technique : la forme du manche, la clause du garde-fou neuf et la
  mise en oeuvre du contraste corrige appartiennent a `theo-archi`, a `ines-design` et aux
  developpeurs, saisis en parallele par `sofia-sm`.
- Elle ne prononce aucun Done pedagogique sur les livrables de ce sprint : ce jugement reste
  entierement celui de `nadia-prof`, en arbitrage prealable puis sur le rendu livre.
- Elle ne rouvre pas le veto de `nadia-prof` sur le geste de traction (la manette) du 2026-08-10 :
  ce veto reste entier, et l'element 2 ci-dessus le confirme plutot que de le contredire - le manche
  retenu est dessine et immobile, jamais actionne par un geste de l'enfant ni par une reponse.
- Elle ne ferme pas la reserve 1 de `nadia-prof` sur le pli : elle en fait une contrainte
  d'acceptation de `S10-1` (0 px de hauteur ajoutee), pas une fermeture.

## Alternatives considerees

**Ouvrir les trois elements comme des stories a instruire, plutot que de les ecarter.** Ecartee :
l'utilisatrice, informee du cout de chacun, les a ecartes elle-meme ; les inscrire quand meme comme
stories a instruire laisserait croire a une question encore ouverte, alors qu'elle est tranchee
jusqu'a l'un des declencheurs nommes ci-dessus.

**Ne pas ecrire de declencheur de reouverture, et se contenter du mot "ecarte".** Ecartee : c'est
exactement le mode de defaillance que ce depot a deja connu sur d'autres sujets fermes puis
re-instruits sans que personne n'ait retrouve le motif d'origine. Un declencheur ecrit permet de
rouvrir par une decision, jamais par oubli.
