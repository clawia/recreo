# DEC-0040 - La cible de deploiement devient ordinateur et tablette : le telephone sort du perimetre

- **Type** : perimetre produit - support materiel vise
- **Date** : 2026-08-22
- **Auteur** : camille-po
- **Sprint** : hors sprint - arbitrage de l'utilisatrice rendu entre la cloture de s17 et
  l'ouverture de s18 par `sofia-sm`. Trace ici dans le meme geste que l'arbitrage, comme
  `squad/README.md` section 9 l'exige du mode par arbitrages successifs.
- **Competence programme visee** : transverse - pas de contenu pedagogique. Aucune ligne de
  `squad/programme/MATRICE-couverture.md` ne bouge par cette decision.
- **Statut** : Acceptee

## Contexte

L'utilisatrice a rendu l'arbitrage suivant, en deux temps : *"l'application va etre accessible sur
un ordinateur ou une tablette"*, puis, apres qu'on lui ait expose le choix, *"partons sur
ordinateur et tablette"*. Elle nomme deux supports, et seulement deux.

Cela contredit une decision de moi, tenue depuis quatre sprints. `DEC-0007` point 5 (s03) tranche
*"oui, [le telephone et la tablette portrait sont] un support vise"*, au motif qu'*"un enfant
revise a la maison sur l'appareil disponible plutot que sur un ordinateur"*, et fixe 375 px comme
largeur cible - *"une approximation produit, pas une mesure"*, ecrit-elle deja elle-meme.
`DEC-0016` (s07) y appareille une hauteur de reference de 640 px, motivee non par une donnee sur
les appareils mais par le fait que c'etait *"deja la convention que `nadia-prof` appliquait de
fait"*. `ines-design`, en s10
(`squad/sprints/s10-la-forme-du-bandit-manchot/mesure-ines-hauteur-de-pli.md`), a explicitement
exclu les neuf tablettes de sa mesure du pli au motif que *"une tablette n'est pas le pire cas de
hauteur"* pour une cible telephone. Elle y ecrit aussi que 640 px n'est pas un pire cas de
telephone actuel : c'est le plancher d'une generation d'appareils sortie d'usage, 92 px sous la
mediane de sa propre distribution (732 px), et que 800 px avec deux leviers de mise en page ferme
les sept jeux du catalogue sur cette hypothese de reference telephone.

Le produit est donc mesure depuis quatre sprints contre un telephone en portrait a 360 x 640,
**parce qu'une decision de moi avait etabli que l'ordinateur n'etait pas l'appareil probable**.
L'utilisatrice dit l'inverse. Je ne peux pas laisser cette contradiction ecrite dans deux fichiers
sans arbitrage : le depot est la seule memoire de la squad, et une decision qui n'y est pas ecrite
n'a pas ete prise.

## Decision

### 1. La cible de deploiement est ordinateur et tablette

**recreo est desormais construit pour un ordinateur ou une tablette.** Ce sont les deux supports
que l'utilisatrice a nommes, dans les deux formulations de son arbitrage, et aucun troisieme n'y
figure.

**Le telephone sort du perimetre des supports vises.** Je le dis explicitement plutot que de le
laisser dans un silence qui se lirait comme un oubli : la cible fixee par `DEC-0007` point 5
(*"une largeur d'ecran de telephone ou de tablette portrait est un support vise"*) est restreinte.
Ce n'est pas un elargissement qui ajoute deux supports a un telephone deja vise ; c'est un
remplacement de la cible entiere par les deux supports nommes. Restreindre une cible est une
decision au meme titre que l'ouvrir, et je l'ecris comme telle.

**Ce que cela ne dit pas.** Rien n'empeche mecaniquement `recreo` de continuer a s'afficher sur un
telephone - aucun code de blocage d'appareil n'existe ni n'est demande ici. Ce que cette decision
change, c'est ce contre quoi le produit est **construit, mesure et audite** : un rendu qui se
degraderait sur un telephone cesse d'etre, a compter de cette decision, un motif de correction ou
de refus au titre du support vise. Seuls l'ordinateur et la tablette le sont.

### 2. `DEC-0007` point 5 et `DEC-0016` sont amendees par note datee, pas reecrites

J'ajoute a chacun des deux fichiers une note datee du jour, sous cette meme forme que celle deja
pratiquee six fois au sprint 17. Le texte d'origine reste lisible tel quel : c'est la note qui dit
ce qu'il devient, jamais une reecriture qui l'effacerait. Voir les notes ajoutees dans
`DEC-0007-le-sprint-3-complete-la-ligne-mathematiques-cp.md` et
`DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md`.

### 3. Ce que cet arbitrage rouvre, sans que rien n'y soit leve

**La borne est absolue : cette decision ne leve aucun refus pedagogique.** Un refus ne se leve que
par une mesure de `nadia-prof` sur la nouvelle reference, jamais par une redefinition de la
reference qui l'a produit - ce serait la facon la plus elegante d'effacer quatre sprints de refus,
et c'est exactement ce que ce depot s'interdit. Je nomme ci-dessous ce qui redevient une question
ouverte ; aucun des points suivants ne change de statut par cette seule decision.

- **`S17-2`** est sortie du perimetre de s17 sur un ecart mesure de 90 a 120 px, irreductible sans
  toucher a l'identite partagee de l'ecran de manche (`DEC-0039`, amendement du 2026-08-19). Cet
  ecart a ete mesure et juge irreductible contre une reference de telephone. Rien ne dit qu'il le
  reste contre une reference d'ordinateur ou de tablette, et rien ne dit non plus qu'il disparait :
  la question est rouverte, elle n'est pas resolue ici.
- **`S17-3`** (`bandit-manchot-synonymes-cm1`) a ete refusee deux fois par `nadia-prof`, la
  seconde fois sur sept items sur seize a 39-87 px sous le pli, defaut variable selon le tirage
  (`squad/conformite/FICHE-bandit-manchot-synonymes-cm1.md`, verdict du 2026-08-21). Ce refus
  reste entier. Ce qu'il a mesure - le pli a 360 x 640 - n'est plus la reference du produit ; cela
  ne rend pas le paquet valide, cela rend la mesure elle-meme a refaire le jour ou quelqu'un la
  reprend.
- **"Le bon petit mot"** (`bandit-manchot-homophones-cm1`) est tenu hors du catalogue depuis s10
  dans l'attente de deux conditions cumulatives : la mesure de rendu `BPM-1` et un Done
  pedagogique complet sur le paquet raccourci (`DEC-0024`, amendement du 2026-08-19). `DEC-0039`
  section 3 a transfere la production de `BPM-1` a l'utilisatrice, hors du perimetre de la squad,
  faute d'un agent capable de piloter un navigateur reel sur ce poste - un motif de capacite, pas
  de reference. Je ne touche pas ce transfert ici. Mais la reference contre laquelle `BPM-1`
  mesurerait le pli, elle, est directement concernee par cette decision : la question de savoir ce
  que `BPM-1` doit mesurer desormais est rouverte, sans que je la tranche.
- **La famille `M4`**, historiquement : `nadia-prof` a accepte par ecrit, a la cloture de s10, un
  defaut de pli sur six jeux sur sept a 360 x 640, jusqu'a -251,19 px (jusqu'a -321 px sur l'item
  le plus charge de "Le bon verbe") - un cout assume, pas corrige (`DEC-0025`). Ce cout a ete
  pese contre une reference de telephone. Le meme cout pese contre une reference d'ordinateur ou
  de tablette n'a jamais ete mesure ; je ne presume pas qu'il s'annule, je nomme qu'il reste a
  regarder.

Aucun de ces quatre points n'entre en instruction par cette decision. Ils sont nommes pour que
leur statut reel - un refus tenu, une condition non levee, un cout historiquement accepte - ne
soit pas confondu avec une clôture que cette decision ne prononce pas.

### 4. Ce que cela fait au verdict de `DEC-0037`

`DEC-0037` (s16), et son execution ecrite le 2026-08-21 dans `DEC-0039` et dans
`squad/programme/MATRICE-couverture.md` (synthese de cloture de s17), pose que s17 est le
**quatrieme sprint consecutif sans mouvement reel de couverture programme**.

**Je ne le reecris pas : c'est un fait historique et il reste vrai tel qu'ecrit.** Le compte ne
bouge pas - aucune ligne n'a atteint `couvert-valide`, aucun increment jouable sur une competence
neuve ou avancee n'a ete livre, et cela reste vrai independamment de la reference d'ecran contre
laquelle les refus de ce sprint ont ete prononces. Un sprint sans mouvement de couverture n'en
redevient pas un avec mouvement parce que la cause d'un des blocages est requalifiee apres coup.

**Ce que cet arbitrage change, c'est une part de l'interpretation, pas le decompte.** Le refus de
`S17-3` portait sur un defaut de pli mesure a 360 x 640 - une reference qui n'est plus celle du
produit depuis cette decision. Cela ouvre la possibilite que ce blocage precis ait ete, au moins
en partie, un blocage de reference plutot qu'un blocage de contenu ou de mecanisme. **Je le pose
comme une possibilite ouverte, pas comme un fait acquis** : je ne l'attele pas a l'espoir qu'une
remesure sur ordinateur ou tablette me donne raison. Tant qu'aucune mesure n'existe sur la
nouvelle reference, je m'interdis de presumer qu'elle ferait disparaitre le defaut, exactement la
borne que je pose au point 3 ci-dessus. Si une telle mesure vient un jour a exister et confirme
que le defaut de `S17-3` ne se reproduit pas sur ordinateur ou tablette, ce sera un fait a verser
a l'histoire du sprint 17 - qui resterait, malgre cela, le sprint ou aucune ligne n'a bouge.

### 5. Le mandat borne du 2026-08-21 sur le repli des rangees de propositions

J'ai ouvert, dans `DEC-0039` (amendement du 2026-08-21), un mandat route a `ines-design` et
`theo-archi`, borne a la disposition des propositions : rendre le nombre de rangees d'un bloc de
trois etiquettes independant de l'ordre du tirage. Son declencheur ecrit est un defaut *"mesure,
confirme sur 80 tirages, et severe"* : des propositions qui ne tiennent jamais sur une seule ligne
a 320 px de large, faisant basculer l'item sous un pli mesure a 640 px de haut.

**Le mandat dit encore quelque chose, mais son declencheur n'est plus garanti de se produire tel
quel.** La cause qu'il corrige - un bloc de trois etiquettes qui se replie sur plusieurs rangees
faute de largeur - est une propriete de largeur d'ecran, pas de hauteur : rien n'indique qu'un
ordinateur ou une tablette, dont la largeur utile depasse tres probablement 320-360 px, produirait
le meme repli. Si ce repli ne se produit plus a la largeur retenue pour ordinateur et tablette, le
defaut que le mandat corrige n'a simplement plus de terrain, et le mandat n'aurait alors plus rien
a fermer. Je ne peux pas le trancher moi-meme : c'est une question de mesure, qui suppose que la
nouvelle largeur de reference existe d'abord.

**Je ne referme pas le mandat et je ne l'etends pas non plus.** Il reste ouvert, route a
`ines-design` et `theo-archi`, tel qu'ecrit le 2026-08-21. Ce que j'ajoute : sa premiere etape,
avant tout travail de mise en page, doit verifier si son declencheur existe encore a la largeur et
a la hauteur que `ines-design` retiendra pour ordinateur et tablette. S'il n'existe plus, ce
constat se documente et se route a moi comme n'importe quelle autre reponse a un mandat ; je ne
le presume pas ici dans un sens ou dans l'autre.

## Ce que je ne fais pas

- Je ne fixe pas la nouvelle largeur ni la nouvelle hauteur de reference : c'est une mesure, pas
  un arbitrage produit. `ines-design` produit en parallele l'inventaire des appareils de la
  nouvelle cible.
- Je ne juge aucun rendu : ce jugement reste celui de `nadia-prof`.
- Je ne touche ni au code ni au contenu.
- Je ne fais bouger aucun statut de `squad/programme/MATRICE-couverture.md`.
- Je ne leve aucun refus pedagogique deja prononce.

## Impact

- `squad/decisions/produit/DEC-0007-le-sprint-3-complete-la-ligne-mathematiques-cp.md` : note
  datee du 2026-08-22 ajoutee sous le point 5, sans reecriture du texte d'origine.
- `squad/decisions/produit/DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md` :
  note datee du 2026-08-22 ajoutee, sans reecriture du texte d'origine.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0040 ajoutee ; notes d'amendement ajoutees
  sur les lignes DEC-0007 et DEC-0016.
- `squad/programme/MATRICE-couverture.md` : inchangee. Aucune ligne ne bouge par cette decision.
- Aucun fichier de conformite, aucun Done pedagogique, aucun statut de catalogue n'est modifie par
  cette decision.

## Ce que je route

- **`sofia-sm`** : verser cet arbitrage dans le planning de s18 (nomme comme tel, au sens de
  `squad/README.md` section 9) ; affecter le mandat du point 5 des que `ines-design` et
  `theo-archi` sont disponibles.
- **`ines-design`** : produire l'inventaire des appareils d'ordinateur et de tablette qui
  remplacera la distribution de telephones mesuree en s10, et proposer la largeur et la hauteur de
  reference qui en decoulent. Verifier, dans le meme geste ou dans la foulee, si le declencheur du
  mandat du point 5 existe encore a cette largeur.
- **`theo-archi`** : toute consequence technique de ce changement de reference sur l'outillage de
  mesure (`outils/mesurer-rendu.sh`, les largeurs et hauteurs citees en dur dans les scripts et les
  tests) - je ne la presuppose pas, je la nomme comme relevant de son perimetre.
- **`nadia-prof`** : aucune demande de rejugement aujourd'hui. Le jour ou une mesure existe sur la
  nouvelle reference pour `S17-3`, "Le bon petit mot" ou la famille `M4`, c'est a elle seule de
  dire si le defaut persiste, disparait, ou change de nature.

## Amendement du 2026-08-22 (second, camille-po) - la largeur et la hauteur de reference sont fixees a 744 x 551 px

**Sprint.** L'utilisatrice a prolonge s17 plutot que de laisser cette decision attendre
l'ouverture de s18 : ce qui suit s'inscrit dans s17, pas dans l'intervalle "hors sprint" nomme a
l'ouverture de cette decision. Je ne corrige pas le champ "Sprint" en tete de ce document, qui
restait exact au moment ou il a ete ecrit ; je le precise ici.

**Ce qui declenche cet amendement.** `ines-design` (`proposition-ines-cible-ordinateur-et-tablette.md`,
branche `s17-ines-cible-ordinateur-tablette`, non fusionnee, forkee de `round-two` a `db2bff6`) a
etabli l'inventaire d'appareils que je routais au point "Ce que je route" ci-dessus. `nadia-prof`
(`squad/conformite/mesure-hauteur-utile-nouvelle-cible.md`, branche `s17-nadia-hauteur-utile`, non
fusionnee, meme fork) a verifie sur navigateur reel le point qu'`ines-design` nommait elle-meme
comme le plus fragile de son document. Les deux documents ne tranchent ni l'un ni l'autre la
reference : c'est l'objet du present amendement.

### 1. Ce que les deux mesures etablissent

**Largeur.** Le pire cas de largeur reel identifie sur la cible ordinateur et tablette est
**744 px** (iPad mini, 6e/7e generation) : toute configuration d'ordinateur releve par
`ines-design` est plus large (1280 px au plus etroit), et tout autre iPad du catalogue actuel est
a 768 px ou plus. L'axe largeur est donc entierement porte par la tablette.

**Hauteur.** Le pire cas de hauteur n'est pas porte par la tablette (1024 px au plus bas, dalle
entiere, aucune deduction de chrome puisque le navigateur mobile retracte sa barre d'adresse a
l'usage) mais par l'ordinateur, ou aucun retrait de ce type n'existe : la barre d'adresse, les
onglets et la barre des taches Windows restent visibles en permanence. `nadia-prof` a mesure sur
un poste reel (Chrome et Edge, profil isole, fenetre maximisee, capture d'ecran croisee avec la
lecture pour exclure un artefact) que cette chrome coute **135 px sans barre de favoris et 169 px
avec**, contre l'estimation non verifiee de 110 a 160 px qu'`ines-design` avait posee. Appliquee au
pire cas de dalle qu'elle a identifie (1280 x 720, deux chemins materiels distincts - panneau natif
720p ou panneau 1080p mis a l'echelle a 150 % - qui convergent vers la meme valeur), cette mesure
derive une hauteur utile de **585 px sans barre de favoris et 551 px avec**.

### 2. La largeur de reference : 744 px, en dessous du seuil de 768 px, deliberement

`nadia-prof` a mesure que la mise en page de la rangee de propositions est un escalier, pas une
pente, sur la feuille de style tierce du produit : la bande de viewport 576-767 px plafonne la
rangee a 500 px (cinq items sur seize a deux rangees, un item a 1,51 px de marge sur le dernier
mot cible qui tient) ; a 768 px pile, un seuil Bootstrap fait passer la rangee a 680 px et les
seize items sur une seule rangee.

**Je fixe la largeur de reference a 744 px, en dessous du seuil de 768 px, et je le fais sciemment
plutot que par defaut.** La raison n'est pas une preference pour le cas le plus dur : c'est que
**744 px est un appareil reel** (l'iPad mini) et que **768 px n'en est aucun** parmi ceux
identifies - l'iPad le plus etroit du catalogue actuel est justement celui qui reste sous le
seuil. Fixer la reference a 768 px ne rendrait pas un iPad mini plus confortable : cet appareil
continuerait a rendre une rangee de 500 px quel que soit le nombre que j'ecris dans une decision,
puisque son viewport reel est 744 px et que rien dans cette decision ne le change. Une reference
fixee au-dessus du pire cas reel identifie ne protegerait personne, elle ferait seulement en sorte
que la squad cesse de mesurer l'endroit ou le defaut se produit reellement - exactement le mode de
defaillance que ce depot nomme ailleurs comme un ecart muet plutot qu'un echec visible. La marche
de 768 px est un fait sur le mecanisme de mise en page, pas un choix de reference qui m'est
ouvert : elle reste un probleme a router a `theo-archi` et `ines-design` (item a 1,51 px, cinq
items a deux rangees), pas une facon de m'en affranchir en deplacant le curseur.

### 3. La hauteur de reference : 551 px, la configuration avec barre de favoris retenue

**Je retiens 551 px, la valeur mesuree avec la barre de favoris visible, plutot que 585 px sans
elle.** `nadia-prof` le nomme elle-meme : *"551 px est une configuration ordinaire, pas un cas
extreme"* - un ordinateur familial sur lequel personne ne masque la barre de favoris pour reviser.
Ecarter cette configuration reviendrait a choisir la reference la plus confortable a mesurer
plutot que la plus fidele a l'usage reel, ce que `DEC-0007` nommait deja comme le motif retenu
pour l'"appareil disponible a la maison" plutot qu'un appareil ideal. Je ne retiens pas 585 px : ce
serait ecarter, sans motif produit, une configuration que `nadia-prof` elle-meme qualifie de
courante plutot que d'exception.

### 4. Reference unique, pas un pire cas par appareil

`ines-design` nomme un fait qui n'existait pas quand la reference etait un telephone : le pire cas
de largeur et le pire cas de hauteur viennent desormais de deux familles differentes (tablette,
ordinateur), la ou le tout-telephone de s10 les reunissait dans la meme famille d'appareils. Une
reference unique combinant les deux pires cas ne correspond donc, par construction, a aucun
appareil reel.

**Je retiens neanmoins une reference unique : 744 x 551 px**, pour deux raisons.

Premiere raison, deja posee par `ines-design` : ce depot a deja fait ce choix une fois sans le
nommer comme tel - le couple 360 x 640 px de `DEC-0016` n'etait pas non plus un appareil unique de
la distribution mesuree en s10, seul le Galaxy S8 s'en approchait a 100 px pres sur la hauteur. Le
precede existe ; je ne l'invente pas ici.

Seconde raison, verifiee par `nadia-prof` et pas seulement supposee : **la position de la derniere
proposition ne depend d'aucune unite relative a la hauteur du viewport**, verifie en mesurant les
memes seize items aux trois hauteurs 945, 585 et 551 px et en obtenant le jeu de valeurs identique
au centieme de pixel pres. Les deux axes sont independants dans ce produit. Un point qui cumule le
pire cas de chacun des deux axes est donc, par construction, au moins aussi contraignant que
n'importe quel appareil reel sur chacun des deux axes pris separement - ce n'est pas une reference
fictive qui invente un risque, c'est une reference fictive qui borne strictement tous les risques
reels connus.

**Ce que cela coute, et je le dis parce que `nadia-prof` vient d'en payer le prix sur un seul
format : rien de plus que ce qu'elle fait deja.** Une reference unique ne change rien a sa methode
de mesure - un rendu, un pli, comme aujourd'hui. Passer a un pire cas par appareil aurait multiplie
ce cout par le nombre d'appareils retenus, alors qu'elle vient de consacrer seize parties et cent
soixante tirages a un seul format pour un seul paquet (`SYN-5`/`SYN-10`, s17). Ce que cela ne
donne pas en echange : si un gabarit se comporte bien sur la reference fictive mais mal sur un
appareil reel qui ne cumule pas les deux pires cas mais en approche un seul de tres pres (par
exemple un ordinateur a la fois etroit et bas, ou une tablette a la fois etroite et basse), une
reference unique ne le revelerait que si cet appareil venait a etre mesure explicitement un jour -
ce que je ne fais pas ici, faute d'un tel appareil identifie dans l'inventaire actuel.

### 5. Ce que je fixe, formellement

**La largeur de reference devient 744 px, remplacant les 375 px de `DEC-0007` point 5.**
**La hauteur de reference devient 551 px, remplacant les 640 px de `DEC-0016` point 1.**
Ces deux decisions amendent `DEC-0007` et `DEC-0016` par note datee, comme au premier amendement
de cette meme decision : le texte d'origine reste lisible, la note dit ce qu'il devient.

### 6. Ce que cela ne fait pas

**Ceci n'est pas une levee de `SYN-5`.** `nadia-prof` l'ecrit elle-meme avant que je ne le
reprenne : `SYN-5` se mesure contre la reference que je fixe ici, et tant qu'aucune reference
n'existait, il n'y avait rien contre quoi la mesurer. Le refus du 2026-08-21 reste entier. Une
mesure existe desormais a 744 px de large (section 1), avec un resultat favorable au pli le plus
severe qu'elle a mesure (551 px : zero item sur seize sous le pli) - mais **ce constat reste le
sien a prononcer, pas le mien**, et il suppose qu'elle le fasse dans les formes habituelles
(fiche de conformite, Done pedagogique), pas par le detour d'une mesure hors fiche comme celle
qu'elle vient de produire pour informer cette decision.

**Aucun statut de `squad/programme/MATRICE-couverture.md` ne bouge par cette decision non plus.**

**Je ne tranche pas la question du survol** qu'`ines-design` pose en section 5 de sa proposition
(la premisse du 2026-08-18 sur le survol neutre, dont elle nomme elle-meme qu'elle devient plus
sollicitee sur ordinateur) : elle la pose pour `nadia-prof` et pour moi, je ne la traite pas dans
cet amendement qui porte sur la reference d'ecran, pas sur le comportement au survol. Je la garde
nommee pour ne pas la perdre : elle attend un arbitrage separe.

### 7. Deux faits annexes, nommes sans etre tranches

**Un trou de garde-fou.** La largeur effective de la rangee de propositions est une fonction en
escalier d'un seuil de la feuille de style tierce (Bootstrap), qu'aucun test du depot n'assertionne.
Un changement de version de cette dependance, ou une modification de la classe `container` sur
l'ecran de manche, ramenerait la rangee vers 320 px sans qu'aucun test ne rougisse - le meme mode
de defaillance que celui deja nomme ailleurs dans ce depot sous le nom d'ecart muet. `nadia-prof`
l'a deja route directement a `theo-archi` dans son propre document ; je ne la reroute pas, je la
nomme ici pour qu'elle reste tracee au fil de cette decision et pas seulement dans une mesure hors
fiche.

**Le bloc juste/faux, pas une proposition, qui depasse le pli sur les cinq items a deux rangees.**
`nadia-prof` a vu, en jouant, que ce qui depasse le pli sur les cinq items qui restent a deux
rangees a 744 x 551 n'est pas une troisieme proposition mais le bloc de retour juste/faux
lui-meme : un enfant qui repond faux sur l'un de ces cinq items ne verrait pas le message qui le
lui dit sans defiler. Elle le note et ne le juge pas, et precise que ce fait est hors de `SYN-5`
(qui porte sur les propositions, pas sur le bloc de retour). Je le nomme ici dans les memes
termes : ce n'est pas une condition aujourd'hui, cela peut le devenir le jour ou `nadia-prof`
mesure formellement contre la reference que je viens de fixer.

## Impact de cet amendement

- `squad/decisions/produit/DEC-0007-perimetre-s03-completer-avant-etendre.md` : seconde note datee
  du 2026-08-22 ajoutee, precisant que 375 px est remplace par 744 px.
- `squad/decisions/produit/DEC-0016-hauteur-de-viewport-de-reference-et-contenu-au-dessus-du-pli.md` :
  seconde note datee du 2026-08-22 ajoutee, precisant que 640 px est remplace par 551 px.
- `squad/decisions/produit/DEC-index.md` : ligne DEC-0040 mise a jour avec cet amendement ; notes
  d'amendement sur les lignes DEC-0007 et DEC-0016 completees.
- `squad/programme/MATRICE-couverture.md` : inchangee.
- Aucun fichier de conformite, aucun Done pedagogique, aucun statut de catalogue n'est modifie.

## Ce que je route (ajout du 2026-08-22, second)

- **`sofia-sm`** : verser cet amendement au planning de s17 prolonge, pas de s18 - le sprint n'a
  pas ete rouvert en tant que nouveau sprint, il a ete prolonge par l'utilisatrice.
- **`ines-design`** et **`theo-archi`** : le mandat du 2026-08-21 sur le repli des rangees et le
  trou de garde-fou de la section 7 restent leur perimetre, sur la reference desormais fixee
  (744 x 551) plutot que sur une reference encore ouverte.
- **`nadia-prof`** : aucune obligation nouvelle de rejuger `S17-3` dans un delai donne. Si elle
  choisit de mesurer `SYN-5` contre 744 x 551 dans les formes (fiche de conformite), le verdict
  reste entierement le sien, y compris sur le fait annexe du bloc juste/faux nomme en section 7 et
  sur la reserve qu'elle a posee elle-meme sur la police de caracteres (Segoe UI mesuree, San
  Francisco non verifiee, item a 1,51 px de marge).
