# DEC-0007 - Le sprint 3 complete la ligne mathematiques CP existante et solde la dette de gouvernance, il n'ouvre pas de nouvelle matiere ni de nouvelle competence

- **Type** : perimetre produit, contenu, comportement de mecanique, seuils
- **Date** : 2026-08-05
- **Auteur** : camille-po
- **Sprint** : s03
- **Competence programme visee** : transverse pour les points 1, 4, 5 et 6 ; mathematiques, CP
  (cycle 2), domaine "Nombres et calculs" - "Calcul mental - additions dans la limite de 10" pour
  les points 2 et 3 (meme ligne que `DEC-0001` et `DEC-0004`,
  `squad/programme/mathematiques/competences-cp.md`).
- **Statut** : Acceptee

## Contexte

Trois sprints ont livre un seul jeu (`bandit-manchot-additions-cp`), sur une seule competence du
referentiel, dont la ligne de `squad/programme/MATRICE-couverture.md` n'est toujours pas
`couvert-valide`. s00 a pose le socle et ce jeu ; s01 a leve le refus pedagogique de s00, mais deux
constats de fond de `nadia-prof` sont restes routes et non tranches (repartition des sommes,
indice de S1-5 inatteignable) ; s02 a habille l'application sans toucher au contenu ni a ces deux
constats. Deux d'entre eux ont deja ete reportes une fois (routes "au backlog de s02" par la review
de s01, jamais instruits puisque s02 n'a traite que l'identite visuelle). Un troisieme obstacle,
`squad/validation/FILE-ATTENTE.md` reste sur le verdict `REFUSE` de s00 alors que le Done
pedagogique est accorde depuis le 2026-08-05 (s01) : deux sprints d'ecart entre l'etat reel et ce
que le depot affiche.

Je dois trancher une question de fond : `s03` doit-il enfin etendre la couverture du programme (une
nouvelle competence, une nouvelle matiere), ou la dette accumulee l'interdit-elle encore ?

## Decision

### 1. Perimetre de s03 : completer, pas etendre

**s03 ne cree aucun contenu pour une matiere ou une competence nouvelle.** Il complete la seule
competence deja engagee jusqu'a ce qu'elle merite reellement le statut `couvert-valide`, et il
solde les obstacles de gouvernance restes ouverts depuis un sprint entier ou plus.

Trois motifs, cumulatifs :

1. **`nadia-prof` a pose une condition ecrite et non encore traitee pour que la ligne
   mathematiques CP passe a `couvert-valide`** : la reserve sur la repartition des sommes
   (`squad/conformite/FICHE-bandit-manchot-additions-cp.md`, section "Suites hors de ma main" du
   verdict s01). Ouvrir une deuxieme ligne `en cours` sans avoir ferme la premiere produirait une
   matrice avec deux trous actifs au lieu d'un, ce qui contredit litteralement la regle que
   `docs/backlog/backlog.md` se donne lui-meme : "chaque sprint doit reduire un trou visible de la
   matrice, pas seulement en ajouter un."
2. **Deux obstacles ont deja ete reportes une fois sans etre traites** (repartition des sommes,
   indice de S1-5 inatteignable) : la review de s01 les routait explicitement "au backlog de s02" ;
   s02 ne les a pas repris. Un report qui se represente une deuxieme fois sans avoir ete instruit
   n'est plus une question de priorisation, c'est une dette qui s'accumule. Je le traite maintenant
   plutot que de le reporter une troisieme fois.
3. **Completer une competence jusqu'a `couvert-valide` est un progres de couverture mesurable,
   au meme titre qu'en ouvrir une nouvelle.** La matrice ne compte aujourd'hui aucune ligne
   `couvert-valide` apres trois sprints ; en obtenir une changerait cet etat visible. Ouvrir une
   deuxieme ligne `en cours` sans y arriver ne le changerait pas.

**Ce que je ne fais pas** : je ne pretends pas que ce choix garantit le verdict `couvert-valide`. Ce
verdict n'appartient qu'a `nadia-prof`, apres audit reel de l'increment corrige. Cette decision fixe
les corrections que je juge necessaires pour repondre a la condition qu'elle a ecrite ; elle ne
prejuge pas de son verdict.

**Ce que cette decision ne dit pas** : que francais restera a zero ligne `en cours` indefiniment.
Le trou signale depuis la synthese du sprint 0 ("francais n'a encore aucune ligne en cours") reste
le prochain trou le plus visible de la matrice et le candidat naturel de s04, une fois que s03 aura
soit obtenu `couvert-valide` sur la ligne mathematiques CP, soit precise pourquoi ce n'est toujours
pas le cas apres cette correction.

### 2. Repartition des sommes : tirage uniforme sur les couples valides, exclusion du couple (0, 0)

Le tirage actuel produit le premier operande uniformement dans 0-10 puis le second dans ce qui
reste : cela favorise mecaniquement les grandes sommes (43,9 % des manches sur les sommes 9 et 10,
7,6 % sous 5, mesure sur 171 manches par `nadia-prof`). Je retiens la premiere piste qu'elle
nomme elle-meme comme "deja une nette amelioration" : **un tirage uniforme sur l'ensemble des
couples (a, b) valides** (a et b entiers de 0 a 10, a + b inferieur ou egal a 10), plutot qu'une
progression du bas vers le haut de l'intervalle (sa "bonne reponse" a elle, mais qui suppose une
notion d'adaptation dans la session que je ne fais pas porter a cette correction, pour rester a
l'interieur du contrat de contenu deja ouvert par `ADR-0006` et ne pas retarder la levee de sa
condition).

**Le couple (0, 0) est retire du tirage.** `nadia-prof` le nomme explicitement (reserve 8, verdict
s01) : c'est la seule addition du contenu qui ne demande rien du tout, et elle ne devrait pas
pouvoir sortir. Le retirer fait disparaitre la somme 0 du jeu (elle ne peut naitre que de ce
couple), ce qui ne contredit pas le seuil d'operandes nuls deja fixe par `DEC-0004` point 5 (5 % a
15 % de tirages avec au moins un operande nul) : les couples (0, k) et (k, 0) pour k de 1 a 10
restent presents et suffisent largement a tenir ce seuil.

Le tirage porte donc sur 65 couples valides (66 moins le couple exclu). Repartition theorique
cible pour chaque somme s (nombre de couples valables pour cette somme, divise par 65) :

| Somme | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|---|
| Couples valides | 0 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 |
| Cible | 0 % | 3,1 % | 4,6 % | 6,2 % | 7,7 % | 9,2 % | 10,8 % | 12,3 % | 13,8 % | 15,4 % | 16,9 % |

Contre 0,6 %, 0 %, 1,2 %, 2,3 %, 3,5 %, 8,8 %, 9,9 %, 14,0 %, 15,8 %, 15,8 % et 28,1 % mesures ce
sprint : la somme 10 passe de pres de trois fois sur dix a moins de deux sur dix, et les petites
sommes (hors 0, desormais absente par construction) redeviennent representees a proportion de leur
poids reel dans l'intervalle. Le detail testable est porte dans `docs/backlog/backlog.md`, story
S3-1.

Aucun `ADR` n'est requis : ce changement porte sur l'algorithme de tirage a l'interieur du contrat
deja ouvert par `ADR-0006` (aucun nouveau champ de contenu, aucune nouvelle capacite exposee a la
mecanique generique). Si `theo-archi` juge que la mise en oeuvre en decide autrement, c'est a lui
de l'ecrire.

### 3. Le declenchement de l'indice de S1-5 change : echecs consecutifs, plus repetition d'un item

`nadia-prof` a mesure que le troisieme echec sur un meme item ne peut pas arriver avant la 11e
manche dans la sequence la plus favorable, parce que le retour d'un item deja vu ne depend pas du
nombre d'echecs mais de la probabilite qu'il soit retire au hasard - mesuree a un seul retour
naturel sur 24 parties. **Abaisser le seuil d'echecs sur le meme item (la premiere piste qu'elle
nomme) ne resoudrait donc rien** : le probleme n'est pas le nombre d'echecs necessaires, c'est que
l'item revient presque jamais.

Je retiens sa seconde piste : **l'indice se declenche desormais sur trois echecs consecutifs,
quel que soit l'item concerne par chacun** - le meme evenement declencheur que la "manche plus
simple" deja fixee par `DEC-0004` point 4. L'indice accompagne alors la question suivante
presentee, qu'il s'agisse d'un item deja reussi (si la manche plus simple s'applique) ou du tirage
normal (si aucun item n'a encore ete reussi dans la session). Ce choix garantit que l'indice peut
s'afficher au plus tard a la quatrieme manche d'une partie qui en compte dix, ce qui repond au
critere que `nadia-prof` a elle-meme formule : "un enfant qui rate trois fois de suite doit
recevoir une aide dans la partie en cours."

Cela remplace le premier critere d'acceptation de S1-5 tel qu'ecrit initialement dans
`docs/backlog/backlog.md` ("un meme item auquel l'enfant repond faux a trois reprises [...] est
presente avec un indice") : ce critere reposait sur une hypothese de recurrence d'item qui ne se
verifie pas en jeu reel. Le detail du critere reecrit est porte par la story S3-2 du backlog.

Aucun `ADR` n'est requis : le comportement change reste a l'interieur du perimetre deja ouvert par
`theo-archi` pour S1-5 (chemin 2, aucun `ADR` requis, confirme par `DEC-0004`).

### 4. L'appui rate compte comme une erreur de calcul (reserve 3, S2-1) : aucune mecanique de correction ajoutee ce sprint

`nadia-prof` nomme le fait sans le qualifier de defaut : un doigt qui derape declenche la meme
reponse "fausse" qu'un calcul errone, donc le score peut sous-estimer ce que l'enfant sait. Elle le
qualifie elle-meme de question produit, pas de defaut de S2-1.

Je tranche de ne pas ajouter de mecanique de confirmation ou d'annulation avant de valider une
reponse : une telle mecanique ajouterait une etape supplementaire a chaque manche pour un enfant de
six ans, ce qui va a l'encontre du principe de charge cognitive minimale deja acquis sur ce jeu
("une seule question, une seule action possible", verifie et salue par `nadia-prof` en s00). La
correction deja engagee sur la cible tactile (boutons pleine largeur depuis s02, ecart porte a au
moins 1 rem par la story S3-5 de ce sprint) est la reponse que je retiens pour l'instant : elle
reduit directement le risque de derapage plutot que d'en corriger la consequence apres coup. Je ne
classe pas ce point comme clos definitivement : si l'audit de `nadia-prof` sur l'increment corrige
montre que les appuis rates restent frequents malgre la cible agrandie, la question sera rouverte
avec les donnees en main plutot que sur une supposition.

### 5. Le trou d'audit sous ~500 px de large : une largeur d'ecran de telephone ou de tablette portrait est un support vise

`nadia-prof` a signale, sans la trancher, une question de perimetre : son harnais ne peut pas
juger le rendu sous ~500 px, alors qu'une tablette ou un telephone tenu debout est un support
plausible pour une application destinee a des enfants de CP et de CM1, susceptible d'etre utilisee
a la maison sur l'appareil disponible plutot que sur un ordinateur.

**Je tranche : oui, c'est un support vise.** `recreo` n'a aucune raison produit d'exclure les
largeurs d'ecran de telephone ou de tablette portrait ; rien dans `CLAUDE.md` ni dans le backlog
existant ne les avait exclues par une decision explicite, elles etaient seulement non verifiees.
Je fixe une largeur cible testable par automate (375 px, largeur de telephone portrait courante) et
j'ouvre la story S3-8 en consequence. Cette story garantit l'absence de defaut structurel a cette
largeur (chevauchement, depassement horizontal, bouton hors champ) ; elle ne pretend pas remplacer
le jugement pedagogique de `nadia-prof` sur le confort de lecture a cette taille, qui restera hors
de portee jusqu'a ce qu'un harnais de rendu capable de descendre sous 500 px existe - question
d'outillage que je route a `yanis-qa` et `theo-archi`, pas que je tranche ici.

### 6. La question de process de DEC-0006 (fiche de conformite distincte) reste ouverte, sans nouvelle reponse

`DEC-0006` avait laisse a `nadia-prof` la question d'une fiche de conformite distincte pour un
chantier transverse ; elle a tranche pour s02 (consigner dans la fiche existante) et m'a renvoye la
question de process pour le jour ou un deuxieme jeu existera. Puisque s03 ne cree aucun jeu
nouveau (section 1 ci-dessus), cette question n'a pas de terrain sur lequel se poser ce sprint :
je ne la tranche pas, je constate qu'elle reste sans objet pour l'instant.

### 7. Le titre du produit devient accentue : "Recreo" devient "Récréo"

Reserve 10 du verdict s01 de `nadia-prof` (fiche, section 6) : le titre `<h1>Recreo</h1>` est le
seul mot de l'ecran d'accueil, l'enfant le dechiffre, et il n'est pas accentue - "un enfant de CP
qui le dechiffre lit /rekreo/ et non 'récréo'". Elle le porte en reserve plutot qu'en motif de
refus ("c'est un nom de produit, je ne le refuse pas") et me renvoie explicitement la question
("a trancher comme une question de marque, pas par moi").

Je tranche : **le titre devient "Récréo".** Un nom de marque qui s'ecarte deliberement de
l'orthographe standard (a la maniere de marques commerciales adultes) n'a aucune justification
ici, alors que l'intention entiere de la categorie B de `DEC-0002` et de son amendement du
2026-08-04 est qu'aucun mot mis sous les yeux d'un enfant en train d'apprendre a lire ne porte une
graphie fausse - et le titre est structurellement le premier mot que l'enfant dechiffre a chaque
visite. Garder "Recreo" non accentue contredirait le principe que l'equipe applique par ailleurs a
chaque autre mot de l'interface. Story S3-9 dans `docs/backlog/backlog.md`.

### 8. L'ecran de catalogue vide sans action (reserve 11) reste en l'etat, sans story ce sprint

Reserve 11 du verdict s01 : l'ecran affiche quand aucun jeu n'est disponible n'offre aucune action
a l'enfant. `nadia-prof` l'accepte "pour aujourd'hui" tout en notant qu'"un enfant devant un ecran
sans rien a faire reste un enfant bloque". Je ne l'instruis pas en story ce sprint : le catalogue
contient aujourd'hui exactement un jeu et n'a aucune raison de se vider dans le cours normal du
produit (ce n'est pas un chemin qu'un enfant peut atteindre en jouant, seulement un ecran de
secours pour une erreur de configuration). Je la laisse ouverte et je le dis plutot que de la
classer close : elle redevient pertinente des qu'un deuxieme jeu existe et qu'un catalogue
partiellement configure devient un scenario reel, ou si le mecanisme de gel de contenu
(`squad/validation/FILE-ATTENTE.md`) retire un jour un jeu du catalogue.

## Impact

- `docs/backlog/backlog.md`, sprint 3 : neuf stories (S3-1 a S3-9) ecrites en consequence des
  points 2, 3, 5 et 7 ci-dessus, plus les reserves nommees de S2-1/S2-2/S2-3 qui ne demandaient pas
  d'arbitrage produit nouveau (S3-4 a S3-7).
- `squad/programme/MATRICE-couverture.md` : la ligne mathematiques CP reste `en cours`. Cette
  decision ne la fait pas passer a `couvert-valide` - seule `nadia-prof` le peut, apres audit de
  l'increment corrige. La note de la ligne est mise a jour pour refleter que la condition qu'elle a
  posee est desormais instruite en story.
- `squad/validation/FILE-ATTENTE.md` : corrige pour refleter le verdict `VALIDE SOUS RESERVE`
  accorde depuis le 2026-08-05 (s01), au lieu du verdict `REFUSE` de s00 qu'il portait encore.
- `squad/conformite/FICHE-bandit-manchot-additions-cp.md` : les quatre criteres de la section
  "Criteres pedagogiques attendus" que `nadia-prof` a explicitement confirmes satisfaits en s01
  (consigne et retours en vocabulaire de CP, texte accentue, score lisible, erreur franchissable)
  sont coches. Deux criteres nouveaux sont ajoutes pour suivre S3-1 et S3-2. Le bloc de validation
  pedagogique n'est pas touche.

## Amendement du 2026-08-05 (s03) - point 2, table de repartition recalculee sur le tirage compose

**Ce que corrige cet amendement.** La table du point 2 ci-dessus est calculee sur le tirage nu
(uniforme sur les 65 couples valides, sans tenir compte du filtre qui rejette une partie des
tirages a operande nul pour tenir `DEC-0004` point 5). `theo-archi` a mesure, dans son avis de
faisabilite du sprint 3, que la table telle qu'ecrite ne survit pas a la composition reelle : avec
le filtre en place, les sommes 1 et 10 sortent de leurs fourchettes (0,82 % mesure contre 1,1 %
attendu pour la somme 1 ; 19,2 % contre 18,9 % pour la somme 10), quel que soit le reglage du
filtre qui tient par ailleurs `DEC-0004` point 5. La table doit porter sur le tirage tel qu'il sera
reellement joue, pas sur une etape intermediaire de son calcul.

**Table corrigee**, calculee sur le tirage reellement compose (uniforme sur les 65 couples valides,
puis filtre a un operande nul accepte une fois sur trois - voir l'amendement du meme jour a
`DEC-0004-textes-score-et-seuils-lever-refus-s01.md`, point 5, qui fixe 12,9 % comme cible
pedagogique retenue) :

| Somme | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---|---|---|---|---|---|---|---|---|---|---|
| Cible (tirage compose) | 0 % | 1,29 % | 3,23 % | 5,16 % | 7,10 % | 9,03 % | 10,97 % | 12,90 % | 14,84 % | 16,77 % | 18,71 % |

Mesuree par `theo-archi` sur 20 graines a 6500 tirages chacune, avec une tolerance de +/- 2 points
de pourcentage autour de cette cible : 0 echec sur 20, contre 4 echecs sur 10 avec l'ancienne
table (calee sur le tirage nu). Le detail des fourchettes basse et haute qui en decoulent est
porte dans `docs/backlog/backlog.md`, story S3-1, recalcule dans le meme geste que cet amendement.

**Ce que cet amendement ne change pas** : le tirage lui-meme (uniforme sur les 65 couples valides,
couple (0, 0) exclu) reste celui decide au point 2 ci-dessus. Seule la table de cibles qui sert a
le verifier est corrigee, parce qu'elle avait ete calculee sur une etape du calcul qui ne
correspond a aucun tirage reellement joue par l'application.

## Ce qui reste non testable par construction

- Que le tirage corrige "travaille effectivement toute la competence" plutot que son bord
  superieur, au sens pedagogique et pas seulement statistique, reste un jugement de `nadia-prof` a
  l'audit - la table du point 2 fixe une cible mesurable, elle ne remplace pas son verdict.
- Que le nouveau declenchement de l'indice aide reellement l'enfant, plutot que de coincider avec
  un moment ou il n'en a plus besoin (parce qu'il vient de recevoir une "manche plus simple"), est
  un jugement pedagogique de fond que je ne tranche pas : `nadia-prof` en jugera l'effet en jouant
  l'increment, exactement comme elle l'a fait pour la premiere version de S1-5.
- Que 375 px soit une largeur suffisante pour couvrir "une tablette ou un telephone tenu debout"
  reste une approximation produit, pas une mesure ; je la retiens parce qu'elle correspond a une
  largeur de telephone courante en portrait, pas parce qu'elle epuise la question.

## Note du 2026-08-22 (camille-po) - le point 5 est amende par `DEC-0040`, texte d'origine inchange

Le point 5 ci-dessus tranche *"oui, c'est un support vise"* pour une largeur d'ecran de telephone
ou de tablette portrait, au motif qu'*"un enfant revise a la maison sur l'appareil disponible
plutot que sur un ordinateur"*. L'utilisatrice a rendu l'arbitrage inverse le 2026-08-22 : la
cible de deploiement devient ordinateur et tablette, et le telephone en sort. Je ne corrige pas le
texte du point 5 ci-dessus, qui reste lisible tel qu'ecrit le 2026-08-05 et reste le motif exact
qui a fonde quatre sprints de mesure contre un telephone en portrait : voir
`DEC-0040-cible-de-deploiement-ordinateur-et-tablette-le-telephone-sort-du-perimetre.md`, qui
porte la decision, son contexte et ce qu'elle rouvre.

## Note du 2026-08-22 (camille-po, seconde) - la largeur de 375 px est remplacee par 744 px

L'amendement du 2026-08-22 (second) a `DEC-0040` fixe la largeur de reference a 744 px (iPad mini,
pire cas de largeur reel de la cible ordinateur et tablette), en remplacement des 375 px retenus
au point 5 ci-dessus. Le texte d'origine reste lisible tel qu'ecrit ; c'est la reference qu'il
fixait qui change, pas son raisonnement, qui restait valide pour la cible telephone qu'il visait
alors.

## Note du 2026-08-23 (camille-po, troisieme) - le garde-fou technique de S3-8 (`ViewportEtLargeurTelephoneTest`) est lui aussi revise a 744 px, jamais supprime

**Ce qui m'est remonte.** `theo-archi`, en instruisant un correctif de mise en page pour `SYN-11`
(`squad/decisions/adr/ADR-0056-...md`), etablit que `ViewportEtLargeurTelephoneTest` voit desormais
reellement ce qu'il jugeait deja en droit - un defaut de lecture qui le rendait aveugle a une
declaration nichee sous une regle `@media` est corrige dans le meme lot - et qu'il **interdit** en
consequence tout plafond maison au-dela de 375 px, sur les trois proprietes qu'il examine (`width`,
`min-width`, `max-width`). C'est exactement le garde-fou ouvert par le point 5 ci-dessus et sa story
S3-8 (sprint 3), et sa premisse - la cible telephone - est celle que `DEC-0040` a retiree du
perimetre. `theo-archi` route la decision plutot que de la prendre : *"la clause reste telle
qu'elle est, et elle est desormais visible"*, et fournit un fait sur le modele de boite sans le
trancher - un `max-width` au-dela de la cible ne peut pas produire le debordement que borner
`width`/`min-width` empeche reellement.

**Ce que je verifie avant de trancher.** La note precedente (ci-dessus) remplace *"la largeur de
reference"* que ce point fixait - celle contre laquelle `nadia-prof` mesure un pli - par 744 px. Le
garde-fou technique de S3-8 est un mecanisme distinct : il ne mesure aucun pli, il interdit une
declaration de largeur fixe ou plancher dans la feuille maison au-dela d'une cible, pour eviter
qu'un enfant sur l'appareil le plus etroit reellement vise voie un element deborder horizontalement.
Les deux mecanismes visaient la meme cible (le telephone, 375 px) pour deux raisons differentes ; je
n'avais pas ecrit explicitement, dans la note precedente, que le remplacement s'etendait aussi a ce
second mecanisme. Je le fais ici, pour que la question ne reste pas en suspens entre deux notes qui
se lisent comme si elles se recouvraient sans le dire.

**Ce que ce seuil gardait.** Sur l'appareil le plus etroit alors vise (un telephone en portrait, 375
px), toute declaration maison de `width` ou `min-width` en pixels au-dela de cette largeur aurait
force un element a deborder horizontalement sur cet appareil - le defaut structurel nomme par S3-8
("chevauchement, depassement horizontal, bouton hors champ"). `max-width` etait borne par la meme
inegalite, sans que la story ni moi n'ayons jamais distingue les trois proprietes entre elles.

**Ce qu'il garde encore, une fois sa cible corrigee.** La meme propriete, contre le meme mode de
defaillance, sur l'appareil le plus etroit **reellement vise aujourd'hui** : un `width` ou un
`min-width` maison en pixels au-dela de 744 px forcerait un debordement horizontal sur l'iPad mini,
le pire cas de largeur etabli par `DEC-0040`. Rien dans le changement de cible ne rend ce risque
caduc - il reste exactement aussi reel qu'en s03, seulement mesure contre un autre appareil. Je
**revise le seuil a 744 px plutot que de le supprimer** : le supprimer perdrait ce garde-fou par
inadvertance, sur la foi d'une premisse perimee qui n'est pas la seule chose qu'il protegeait.

**Sur `max-width` specifiquement, et sur l'argument que `theo-archi` fournit sans trancher.** Je ne
retiens pas l'option de scoper le seuil a `width`/`min-width` seules en laissant `max-width` non
borne, pour les deux raisons qu'il nomme lui-meme et que je fais miennes : cela relacherait un
garde-fou dans le lot meme qui en beneficie, et cela laisserait tout `max-width` maison hors de la
chaine d'une rangee (`ADR-0054` ne table que sur cette chaine) sans aucune garde. Je retiens plutot
un seuil **uniforme aux trois proprietes, revise a 744 px** - la meme reference que celle qui
gouverne deja le pli, pas un troisieme nombre invente pour l'occasion. **Consequence verifiee et
non supposee** : ce choix suffit a laisser passer la variante proposee par `ines-design` sur
`SYN-11` (`max-width: 720px`, strictement sous 744), sans qu'aucune exception nommee ne soit
necessaire - la variante rentre dans la regle generale, elle n'en sort pas. La regle categorique
independante de la valeur - ne jamais ecrire `width` en pixels dans la feuille maison, utiliser
`max-width` pour rester capable de retrecir - n'est pas concernee par cette note et reste telle
qu'elle est.

**Ce qui n'est pas decide ici, et reste route a `theo-archi`.** Le mecanisme du correctif
(`Regle 1` d'`ADR-0056`, deja ecrit et fusionne independamment de cette note), la mise a jour de la
constante et des messages du garde-fou (`LARGEUR_CIBLE_PX`, le nom "largeur cible telephone" qui
devrait se lire comme la largeur cible de la cible produit actuelle), et toute consequence sur les
tables d'`ADR-0054`/`ADR-0055`. Je ne touche ni au code ni aux gabarits.

**Un declencheur explicite pour la prochaine fois**, sur le modele de ce que ce depot exige deja
ailleurs (`CLAUDE.md`, references qui se perimenent en silence) : **si la largeur de reference
change de nouveau, ce seuil doit etre revu dans le meme geste**, pas suppose suivre. Je le nomme
pour qu'il ne redevienne pas un second seuil perime sans qu'on l'ait decide.
