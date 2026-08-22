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
