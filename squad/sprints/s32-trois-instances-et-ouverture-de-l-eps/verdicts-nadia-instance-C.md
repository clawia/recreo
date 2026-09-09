# s32 - Verdicts de `nadia-prof`, instance C - education physique et sportive CP

- **Auteur** : `nadia-prof`, instance C
- **Date** : 2026-09-09
- **Terrain** : education physique et sportive, `EPS-CP-04` et `EPS-CP-08` (lot `S32-5`)
- **Branche** : `s32-nadia-c`, partie de `round-two` a `fd19d18f`
- **Budget opposable** : **4,0**, dont 1,5 de marge `PAR-11` amendee et 0,5 non allouee

> **Terrain sans image (`PAR-13`).** Aucune de mes conditions d'EPS - `EPS-1`, `EPS-2`, `EPS-3` -
> n'avait jamais rencontre un item avant aujourd'hui. Ce document est leur premier contact reel, et
> il produit les conditions inedites de la section 7, opposables au second lot.
>
> **Je n'audite ni l'histoire-geographie, ni l'anglais, ni l'EMC CP, ni la ligne de corps-et-effort
> (`EPS-CP-10`, `EPS-CP-11`).** Rien de ce document ne dit quoi que ce soit des terrains des
> instances A et B, et je n'ai relu ni leurs verdicts ni leurs commits.

## 1. Les deux verdicts, en tete

| Jeu | Ligne | Verdict | Statut de matrice que je route |
|---|---|---|---|
| `bandit-manchot-regles-securite-eps-cp` | `EPS-CP-04` | **REFUSE** | reste **`non couvert`** |
| `bandit-manchot-regles-jeu-collectif-eps-cp` | `EPS-CP-08` | **REFUSE** | reste **`non couvert`** |

**Zero ligne sur deux.** Mon attente annoncee d'avance etait de *0 ou 1 ligne sur les deux visees* ;
le resultat est dans cette fourchette, a sa borne basse. **Je ne l'ai pas durcie apres coup et je ne
l'ai pas relachee** : les deux motifs de refus reposent sur des conditions ecrites avant que le lot
n'existe - `FAB-1c` (2026-09-07) pour la securite, `EPS-1` (2026-09-03) plus l'exactitude du contenu
pour le jeu collectif - et sur aucune condition neuve.

**Mon taux de refus au premier passage sur une matiere a conditions inedites passe de quatre paquets
sur cinq (EMC, s29) a six sur sept.** Le profil se confirme, et il est desormais mesure deux fois.

**Ou cette conclusion doit etre reprise, et par qui.** Ma charte me demande de reprendre ma
conclusion dans `review.md`. **Je ne cree pas ce fichier depuis ce worktree** : `review.md` est la
ceremonie de `sofia-sm`, ecrite sur l'**arbre assemble** des trois instances, et trois instances qui
l'ecriraient en parallele produiraient trois fichiers qui s'ecrasent - le mode de defaillance exact
que la parallelisation est censee eviter. **Les deux lignes du tableau ci-dessus sont ma conclusion
et elles sont a reprendre telles quelles**, avec le renvoi a ce fichier et aux deux fiches.

## 2. J'ai joue, et voici comment

**Audit joue : oui.** Jar reconstruit depuis ce worktree, application lancee sur le port 8099.

| Partie | Jeu | Ce que j'ai fait |
|---|---|---|
| 1 | securite | dix manches, toutes justes du premier coup |
| 2 | securite | dix manches, **trois erreurs volontaires par manche** - ecran de revelation atteint dix fois sur dix |
| 3 | securite | premiere passe a l'aveugle, en pressant toujours la premiere proposition affichee |
| 4 | jeu collectif | dix manches, **une erreur volontaire par manche** puis la bonne reponse |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, les indices, l'ecran de fin et le tirage. **Cela ne
couvre ni la couleur, ni le contraste, ni la taille, ni la position a l'ecran**, et aucune
conclusion de ce document n'en depend.

**Ce que jouer m'a donne et que la lecture des fichiers ne donnait pas** : l'ordre des propositions
est tire a chaque manche - verifie, la bonne reponse a occupe les trois rangs sur le meme item d'une
partie a l'autre ; l'indice s'affiche des la premiere erreur et la revelation a la troisieme ;
**aucun enfant ne reste bloque** sur aucun des deux jeux ; le tirage sert dix des onze items, et
l'item laisse de cote change d'une partie a l'autre. Les onze items des deux paquets ont ete vus a
l'ecran.

**`yanis-qa` avait raison** : aucune trace ecrite d'une partie jouee de bout en bout n'existait sur
ces deux paquets avant ce document. Elle existe maintenant.

## 3. Le prealable que je verifie avant tout : le Done technique

Les quatre jobs de `.github/workflows/ci.yml`, lances un par un et nommes en section 8. **Le Done
technique est acquis**, donc les deux increments sont evaluables. Un jeu qui plante n'est pas
evaluable ; ces deux-la tournent.

## 4. La frontiere corps-et-effort - verifiee, pas crue

**J'avais refuse d'avance toute fermeture des trois lignes du meme objet sans que la frontiere soit
ecrite dans la specification du lot.** Elle y est, recopiee mot pour mot dans le commentaire des deux
paquets. **Mais une frontiere recopiee n'est pas une frontiere tenue, et c'est le fait que seule une
lecture item par item donne.**

**Verification, les vingt-deux items un par un** :

| Ce que la frontiere interdit a l'EPS | Item qui l'approche |
|---|---|
| les effets de l'effort dans l'instant (essoufflement, coeur, chaleur, soif) - `EPS-CP-10` | **aucun** |
| l'echauffement, l'hydratation, la recuperation - `EPS-CP-11` | **aucun** |
| l'hygiene de vie en general (sommeil, repas, hygiene du corps) - questionner le monde CP, sciences CM1 | **aucun** |

**La frontiere est tenue, et aucun item ne compte deux fois** (`ADR-0081` clause 5). Les vingt-deux
items portent des regles de securite d'une pratique et des regles de jeux collectifs ; le mot
*effort* n'apparait dans aucun enonce, aucune proposition et aucun indice. **La decote de 3,0 sur ma
borne a paye une couture qui, cette fois, n'a pas cede.** Je le dis parce que c'est le seul des
quatre points de ce sprint dont le resultat est bon.

## 5. `bandit-manchot-regles-securite-eps-cp` (`EPS-CP-04`) - REFUSE

### 5.1 Les conditions, une par une

| Condition | Etat | Ce que j'ai mesure |
|---|---|---|
| **`FER-1`** | **tenue, vacuement** | Le libelle *"Connaitre et respecter les regles de securite propres a l'activite et au lieu de pratique"* ne porte **aucune parenthese enumerative** : il n'y a rien a couvrir element par element. Je le confirme au lieu de laisser croire que la condition a mordu |
| **`FER-2`** | **tenue** | Onze items enumeres, plancher de dix. Aucune tache n'est deflatee par `FAB-1a`. La ligne est servie par ce seul paquet |
| **`FER-3`** | **tenue** | Deux operations mentales distinctes, et non une phrase a mot variable : **reconnaitre la regle qui s'applique** (items 1 a 6) et **dire pourquoi la regle existe** (items 7 a 11). La moins servie en porte **cinq**, au-dessus du plancher de **trois** |
| **`EPS-1`** | **sans objet** | Aucun item ne porte sur la regle d'un jeu |
| **`EPS-2`** | **tenue** | Aucun item ne compare des corps, des performances ni des capacites ; aucune performance chiffree nulle part |
| **`EPS-3`** | **tenue** | Chaque item porte sur une regle de securite factuelle ou sur son motif. Aucune attitude, aucune emotion, aucune conduite morale n'est portee par une bonne reponse |
| **`BIA-4`** | **tenue** | Les onze enonces sont impersonnels (*on*). Aucun n'interroge l'enfant sur sa pratique, son corps ni sa situation |
| **`CAT-1`** | **tenue sur la tache A, non tenue sur la tache B** | Voir 5.3. Sur la tache A, seul l'item 6 presente ses deux distracteurs comme imprudents. Sur la tache B, quatre items sur cinq se gagnent au registre |
| **`FAB-1a`** | **tenue** | Six bonnes reponses distinctes sur la tache A, cinq sur la tache B. Aucune repetition |
| **`FAB-1b`** | **tenue** | Aucun distracteur n'est la bonne reponse d'un autre item de la meme tache. Verifie sur les vingt-deux distracteurs |
| **`FAB-1c`** | **NON TENUE sur la tache B** | Voir 5.3. C'est le motif du refus |
| **`PLA-1`** | **tenue** | Les vingt-deux distracteurs sont des reponses qu'un enfant de CP peut serieusement envisager. Aucun bouton de remplissage |
| **`BIN-1`** | **tenue** | Zero item binaire sur onze |
| **`LON-1`, `LON-2`** | **tenues** | Enonces de **6 a 10 mots**, une phrase chacun. Loin sous la barre de quinze |

### 5.2 Ce qui est bon, et je le dis avant le motif

- **L'exactitude du contenu est irreprochable.** Les onze enonces, les onze bonnes reponses, les
  vingt-deux distracteurs et les onze indices ont ete verifies un par un. **Je n'ai trouve aucune
  erreur de fait.** Le casque a velo, l'autorisation de l'adulte au bassin, la profondeur avant de
  plonger, un enfant a la fois sur le toboggan : ce sont les regles reelles, dites juste.
- **La tache A est du bon travail.** Les items 4 et 5 sont exactement ce qu'une mecanique de choix
  peut faire d'une regle de securite : *"Sur un toboggan, qu'attend-on avant de glisser ?"* avec
  *que la file soit vide* et *que la maitresse compte jusqu'a dix* en face - trois attentes
  plausibles, une seule juste, et il faut connaitre la regle pour trancher.
- **Le traitement de l'erreur fait ce qu'il doit.** Verifie a l'ecran trente fois : l'indice tombe a
  la premiere erreur, la bonne reponse est nommee a la troisieme, l'enfant avance. Les indices
  expliquent le danger plutot que de repeter la reponse (*"Deux enfants en meme temps sur le
  toboggan peuvent se cogner."*).
- **La lisibilite est la meilleure que j'aie mesuree sur un paquet de CP** : dix mots au maximum, une
  seule phrase par enonce, aucun mot hors du vocabulaire de CP. La barre `LON-1` que j'ai posee hier
  est tenue avec cinq mots de marge.

### 5.3 Le motif du refus - `SEC-R3`, la tache B se gagne au champ lexical du danger

**`FAB-1c`, branche *registre*, sur quatre des cinq items de la tache B.**

La tache B demande *"pourquoi cette regle existe"*. Voici, item par item, quelles propositions
nomment un danger, une blessure, un secours ou un choc :

| Item | Bonne reponse | Distracteurs | Propositions du champ du danger |
|---|---|---|---|
| 7 - casque | *pour proteger la tete en cas de chute* | *pour aller plus vite en pedalant* ; *pour etre vu de loin par les voitures* | **2 sur 3** - l'item ne tombe pas |
| 8 - regarder des deux cotes | *pour verifier qu'aucune voiture n'arrive* | *pour voir si un ami arrive* ; *pour observer les vitrines des magasins* | **1 sur 3** |
| 9 - adulte a la piscine | *pour porter secours en cas de danger* | *pour choisir la musique diffusee* ; *pour compter les longueurs nagees* | **1 sur 3** |
| 10 - un par un sur le toboggan | *pour eviter que deux enfants se heurtent* | *pour faire moins de bruit en glissant* ; *pour user moins vite le toboggan* | **1 sur 3** |
| 11 - profondeur avant de plonger | *pour eviter de se blesser en touchant le fond* | *pour savoir combien de temps on peut nager* ; *pour choisir la couleur de son maillot* | **1 sur 3** |

**Sur quatre items sur cinq, la bonne reponse est la seule proposition qui parle de danger, de
blessure, de secours ou de choc. Les distracteurs parlent de musique, de vitrines, de bruit,
d'usure, de couleur de maillot et de duree de baignade.** Un enfant de six ans qui n'a jamais entendu
parler d'aucune de ces regles gagne quatre manches sur cinq par une seule strategie : *prendre la
reponse qui parle de se faire mal*.

**Le seuil de `FAB-1c` est *plus d'un tiers des items de la tache*.** Un tiers de cinq est 1,67 ; le
seuil est donc franchi a deux items. **Il y en a quatre.** La condition n'est pas tenue, et l'ecart
n'est pas marginal.

**C'est le defaut de `CAT-1` transpose du registre moral au registre du danger**, et c'est le meme
defaut que celui que j'ai trouve dans l'EMC le 2026-09-07 en jouant : *"l'enfant coche la reponse
gentille sans que rien n'ait ete travaille"* devient ici *"l'enfant coche la reponse qui parle de se
faire mal"*. **Je l'avais annonce sur la mauvaise branche** : la story `S32-5` porte ma vigilance sur
les **amorces** (*"il faut boire de l'eau"*, *"il faut s'echauffer"*), et l'equipe a effectivement
evite les amorces signantes. Le defaut est arrive par le registre.

**Ce qui le corrige, et c'est actionnable sans un item de plus et sans toucher a la mecanique** : sur
les items 8, 9, 10 et 11, les **trois** propositions nomment un danger, une blessure ou une
consequence, dont **deux fausses**. Sur l'item 9, cela donne : *pour porter secours en cas de danger*
/ *pour verifier que l'eau n'est pas trop froide* / *pour compter les nageurs qui entrent dans le
bassin* - trois raisons serieuses, deux fausses, et l'enfant doit alors savoir a quoi sert un
surveillant de bassin. **C'est la *"forme qui tient"* que j'ai ecrite dans `CAT-1` elle-meme, et elle
s'applique ici sans adaptation.** Huit distracteurs a reecrire ; les onze enonces, les onze bonnes
reponses, les onze indices et toute la tache A restent tels quels.

### 5.4 Pourquoi c'est un refus et non un `VALIDE SOUS RESERVE`

**Mon critere du 2026-09-06, reconduit au 2026-09-07 et que je n'amende pas** : un defaut est
bloquant si *quelque chose de faux est enseigne* **ou** si *un item se gagne sans la competence*.

Rien de faux n'est enseigne ici - je l'ai verifie item par item et je viens de l'ecrire. **La seconde
branche mord, et elle mord sur une tache entiere** : quatre items sur cinq. Or cette tache B est
precisement la seconde tache dont `FER-3` a besoin pour que la ligne puisse fermer. **Ouvrir le Done
reviendrait a faire passer `EPS-CP-04` a `couvert-valide` avec une moitie de competence que le jeu ne
verifie pas.** Je ne le fais pas.

Le refus est proportionne : **huit distracteurs a reecrire**, aucun item a ajouter, aucune mecanique
a toucher, aucun enonce a changer. Il renvoie l'increment a `camille-po`, qui en fait du backlog, et
il ne bloque pas le sprint suivant.

### 5.5 Les deux reserves non bloquantes de ce paquet

#### `SEC-R1` - les items 3 et 8 servent la securite routiere du pieton, pas `EPS-CP-04`

**Constat.** L'item 3 (*"Avant de traverser la rue, que doit-on faire ?"*) et l'item 8 (*"Pourquoi
regarde-t-on des deux cotes avant de traverser ?"*) portent sur la traversee de rue **a pied**. Le
libelle de `EPS-CP-04` dit *"propres a l'activite et au lieu de pratique"*, et le domaine ou je l'ai
rangee est *"Adapter ses deplacements a des environnements varies"*, dont la parenthese que j'ai
moi-meme ecrite est *"(dans l'eau, sur un engin roulant, sur un parcours d'equilibre)"*. **Un pieton
qui traverse une rue n'est ni dans une activite physique, ni dans un lieu de pratique d'EPS** : il
est dans le champ de la premiere education a la route, qui n'est pas cette ligne.

**Ce que je ne fais pas, et le motif est de gouvernance.** Je **ne retire pas** ces deux items du
compte de `FER-2`, ce qui ferait tomber la ligne a neuf items et rendrait la condition non tenue. **La
barre que j'avais ecrite est *au moins dix items servent la ligne*, et le lot l'atteint sur la lecture
qui avait cours quand il a ete ecrit.** L'exigence d'ancrage que je formule ici est une lecture de
**premier contact** ; l'appliquer aujourd'hui pour refuser serait deplacer mon poteau apres le tir.
Elle devient la condition `EPS-5` de la section 7, **datee du 2026-09-09 et opposable au second
lot**, pas a celui-ci.

**Mon propre exemple est en cause, et je le dis.** J'ai ecrit le 2026-09-03, dans le referentiel :
*"Ou regarde-t-on avant de traverser en trottinette ?"*. L'engin roulant y etait, et c'est lui qui
rattachait l'exemple a la ligne. Les items 3 et 8 l'ont laisse tomber. **Une equipe qui suit mon
exemple mot pour mot n'aurait pas produit ce defaut ; c'est mon exemple qui etait a un mot de le
permettre.**

**Ce que j'attends** : que les deux items portent un engin ou un lieu de pratique (*"Avant de
traverser un carrefour en trottinette..."*), ou qu'ils cedent la place a deux items d'une activite
d'EPS. Le contenu lui-meme est juste et utile : **c'est son ancrage qui est ailleurs**, pas sa verite.

#### `SEC-R2` - item 1, *mettre de la creme solaire* est une reponse defendable

**Constat.** *"Avant d'entrer dans une piscine, que doit-on faire ?"*, bonne reponse *attendre
l'autorisation de l'adulte*, distracteur *mettre de la creme solaire*. La consigne du jeu demande
*"quelle regle de securite s'applique"*. **Mettre de la creme solaire avant un bassin decouvert est
une regle de securite reelle**, et c'est une phrase que beaucoup d'enfants ont entendue de leurs
parents. L'enfant qui la choisit est marque faux, et l'indice qu'il recoit (*"Un adulte doit d'abord
donner le signal d'entrer dans l'eau."*) **ne lui dit pas pourquoi sa reponse ne convenait pas** : il
affirme l'autre regle sans ecarter la sienne.

**Non bloquant** : la bonne reponse reste la meilleure des trois, et la regle enseignee est juste. Ce
distracteur fait par ailleurs exactement ce que `CAT-1` demande - il sonne prudent, donc il empeche
de gagner l'item au ton. **C'est son effet de bord qui pose probleme, pas son intention.**

**Ce que j'attends** : un distracteur prudent qui ne soit pas une regle de securite concurrente
(*"compter les carreaux du fond du bassin"* sonne serieux sans etre une regle), **ou** un indice qui
ecarte explicitement la creme (*"La creme solaire protege du soleil, pas de l'eau."*).

### 5.6 Ce que je ne transforme pas en reserve, et je le dis pour qu'on ne me le redemande pas

- **La bonne reponse est la plus courte des trois sur les items 1, 2 et 6** (trois items sur six de
  la tache A). J'ai regarde si `FAB-1c` mordait par la longueur : **elle ne mord pas**, parce que la
  direction n'est pas constante - la bonne reponse est la plus longue sur l'item 4 et a egalite sur
  l'item 5. Un enfant qui prendrait systematiquement la plus courte gagnerait trois items sur six,
  soit a peine au-dessus du hasard a trois propositions. **Je le mesure et je l'ecarte.**
- **L'item 6 est le seul de la tache A ou les deux distracteurs sont imprudents** (*sauter dessus
  des qu'on arrive*, *monter a plusieurs en meme temps*). C'est le motif de `CAT-1`, sur **un** item
  de six. Un item sur six ne fait pas une tache qui se gagne au ton, et je ne fais pas une reserve
  d'un item isole quand j'en fais une d'une tache entiere a cote.
- **La consigne du catalogue** (*"Lis bien, puis dis quelle regle de securite s'applique, ou pourquoi
  elle existe."*) nomme les deux taches, tient sur une ligne et ne reprend pas la formule generique.
  **`DEC-0078` est tenue. Je ne la conteste pas.**
- **L'indice de l'item 7** (*"En cas de chute, la tete est la partie la plus fragile."*). Le
  superlatif est discutable en anatomie ; il est juste sur ce qui compte, la gravite de la
  consequence, et c'est la formulation d'usage au CP. **Je l'accepte et je ne le route pas.**

## 6. `bandit-manchot-regles-jeu-collectif-eps-cp` (`EPS-CP-08`) - REFUSE

### 6.1 Les conditions, une par une

| Condition | Etat | Ce que j'ai mesure |
|---|---|---|
| **`FER-1`** | **tenue, vacuement** | *"Connaitre et respecter les regles d'un jeu collectif simple"* ne porte aucune parenthese enumerative |
| **`FER-2`** | **tenue sur le compte** | Onze items enumeres. **Mais quatre d'entre eux sont en cause en 6.3** : le compte tient, la substance non |
| **`FER-3`** | **tenue sur le compte, fragile** | Deux taches : **reconnaitre la regle d'un jeu nomme** (items 1 a 6) et **dire si une situation respecte ou viole une regle** (items 7 a 11). La moins servie en porte cinq. **Si les items 7 et 8 tombent, elle en porte trois - exactement le plancher, sans marge** |
| **`EPS-1`** | **NON TENUE sur l'item 5** | Voir 6.3. C'est un des motifs du refus |
| **`EPS-2`** | **tenue** | Aucune comparaison de corps, de performances ni de capacites |
| **`EPS-3`** | **tenue** | Les items de la tache B jugent le respect factuel d'une regle, jamais une intention ni une conduite morale. La contrefacon que j'avais refusee d'avance (*"que fais-tu si un joueur triche ?"*) n'est pas reproduite |
| **`BIA-4`** | **tenue** | Les situations mettent en scene des personnages nommes, exterieurs a l'enfant. Aucun item n'interroge l'enfant sur sa pratique |
| **`CAT-1`** | **non tenue sur les items 10 et 11** | Voir 6.4 |
| **`FAB-1a`** | **tenue** | Six bonnes reponses distinctes sur la tache A, cinq sur la tache B |
| **`FAB-1b`** | **tenue a la lettre, contournee en esprit** | Voir `JEU-R7` : le distracteur *en portant deux objets a la fois* de l'item 6 est la **premisse** de l'item 10. Ce n'est pas le vivier des bonnes reponses que `FAB-1b` nomme, donc la lettre est tenue ; l'effet est le meme et je l'ecris |
| **`FAB-1c`** | **non tenue sur les items 10 et 11** | Voir 6.4 |
| **`PLA-1`** | **NON TENUE sur l'item 8** | La seule proposition en face de *oui* est *non*, sur un item dont l'enonce porte deja le verdict. **Un bouton de remplissage, exactement ce que `PLA-1` nomme** |
| **`BIN-1`** | **tenue** | **Deux items binaires sur onze** (items 7 et 8). Le seuil est *au plus la moitie des items d'un paquet* ; deux sur onze est loin dessous. **La condition est tenue et je le dis clairement** - ce sont ces deux items eux-memes qui posent probleme, pour un autre motif |
| **`LON-1`, `LON-2`** | **tenues** | Enonces de **8 a 14 mots** ; les cinq items de la tache B comptent **deux phrases, la seconde etant la question**. Conforme aux deux barres |

### 6.2 Ce qui est bon

- **La tache A est exacte sur cinq de ses six items** et bien construite. *"A 1, 2, 3, soleil, quand
  doit-on s'arreter de bouger ?"* avec *quand on touche le mur* et *quand un joueur crie stop* en face
  est un bon item : trois moments plausibles, un seul juste, la regle est necessaire.
- **Le traitement de l'erreur fonctionne** : indice a la premiere erreur, revelation a la troisieme,
  l'enfant avance toujours.
- **Le choix des six jeux est judicieux** : 1, 2, 3 soleil, le chat perche, l'epervier, la balle au
  prisonnier, les demenageurs sont bien des jeux de cour et de cycle 2, pas des sports a reglement.
  **C'est la bonne echelle pour du CP.**
- **Les quatre personnages** (Lea, Hugo, Aya, Malo) se repartissent les roles sans stereotype de
  genre : une fille et un garcon enfreignent, une fille et un garcon respectent.

### 6.3 Les motifs du refus - quatre items sur onze enseignent ou verifient quelque chose de faux

#### `JEU-R1` - item 7 : toucher un joueur perche n'est pas une infraction. **C'est faux.**

**A l'ecran, manche 4** : *"Au chat perche, Lea touche Hugo sur un banc. A-t-elle respecte la
regle ?"* - propositions *non* / *oui*, bonne reponse **non**, indice servi apres mon erreur : *"Un
joueur perche en hauteur ne peut pas etre touche."*

**La regle du chat perche est que le joueur perche ne peut pas etre *pris*.** Le toucher n'a
simplement **aucun effet** : Hugo n'est pas attrape, et Lea n'a enfreint aucune regle. **L'item
transforme un geste sans effet en faute, et il le fait au nom d'une enfant qu'il nomme.** L'enfant de
CP en ressort en croyant que toucher un camarade perche est de la triche - ce qui est faux du jeu, et
ce qui lui fera reprocher a tort quelque chose dans la cour.

**L'indice redouble la confusion au lieu de la lever** : un joueur perche **peut** etre touche, il ne
peut pas etre pris. C'est exactement la distinction que l'item aurait du enseigner, et l'indice
l'ecrase.

**Une erreur factuelle dans un contenu scolaire est un motif de refus a elle seule, sans
discussion.** Celle-ci est servie deux fois : dans la bonne reponse et dans l'indice.

**Ce que j'attends** : l'item pose la bonne question, qui est celle de l'effet et non celle de la
faute - *"Au chat perche, Malo touche Aya montee sur un banc. Que se passe-t-il ?"*, avec *rien, Aya
est perchee* / *Aya est attrapee* / *Aya devient chat*. La regle enseignee redevient vraie, l'item
cesse d'etre binaire, et il rejoint la forme de l'item 9, qui est deja bonne.

#### `JEU-R2` - item 5 : la regle du loup et des moutons est une variante, et `EPS-1` l'interdit

**A l'ecran, manche 9** : *"Au loup et les moutons, que devient un mouton touche ?"* - bonne reponse
*il devient loup a son tour*, distracteurs *il sort du jeu* et *il attend la fin de la partie*.

**`EPS-1`, que j'ai ecrite le 2026-09-03** : *"un item sur la regle d'un jeu ne porte que sur un jeu
dont la regle est explicitement rappelee dans l'enonce, ou universellement scolaire."*

Les deux formes scolaires du loup et des moutons coexistent : dans l'une le mouton touche devient
loup, dans l'autre il sort du jeu. **Le paquet met la seconde en distracteur, ce qui est la preuve la
plus directe qu'elle existe** : on ne fabrique pas un distracteur plausible avec une regle qui n'a
cours nulle part. Et l'enonce ne rappelle pas laquelle des deux il juge.

**L'enfant qui joue la seconde variante dans sa cour est declare faux sur son propre jeu.** C'est le
motif meme de `EPS-1` - *"un item qui suppose une pratique anterieure fait passer un examen qui n'a
pas ete enseigne"* - dans sa forme la plus dure, puisqu'ici le produit ne se contente pas de
supposer : il **contredit**.

**Ce que j'attends** : l'enonce rappelle la variante qu'il juge (*"Au jeu du loup et des moutons, un
mouton touche devient loup. Que fait-il ensuite ?"*), **ou** l'item porte sur une regle que les deux
variantes partagent, **ou** il cede la place a un item d'un des cinq autres jeux, dont les regles ne
divergent pas.

#### `JEU-R3` - item 5 : l'indice *"Le loup grandit au fil de la partie"* est faux au premier degre

**A l'ecran, manche 9, apres mon erreur volontaire** : *"Le loup grandit au fil de la partie."*

Ce que la phrase veut dire est *"les loups deviennent de plus en plus nombreux"*. **Ce qu'elle dit est
que le loup devient plus grand.** Un enfant de six ans lit *grandir* au premier degre - c'est ce que
le mot signifie dans sa langue et c'est le sens qu'on lui enseigne. **La phrase est fausse telle
qu'elle est lue, et c'est la phrase qui est servie precisement a l'enfant qui vient d'echouer**,
c'est-a-dire celui qui a le moins de moyens de la redresser.

**C'est un defaut de la meme famille que les deux indices menteurs que j'ai trouves a s31** (`MEL-R1`,
`OBT-R1`) : un indice qui dement ou brouille ce qu'il est cense expliquer. Je nomme la condition
inedite qui le generalise en `EPS-6`.

**Ce que j'attends** : *"Chaque mouton touche devient un loup de plus."*

#### `JEU-R4` - item 8 : aucune regle n'est en jeu, et l'enonce porte deja son verdict

**A l'ecran** : *"A l'epervier, Malo traverse sans se faire toucher. A-t-il respecte la regle ?"* -
propositions *oui* / *non*, indice *"Traverser sans etre touche est justement le but du jeu."*

**Ne pas se faire toucher n'est pas respecter une regle : c'est reussir.** L'item pose une question de
conformite sur une action ou aucune regle n'etait en cause, et **son propre indice l'admet** en disant
que c'est le but du jeu. Il ne verifie aucune connaissance : l'enonce dit que tout s'est bien passe,
et demande si tout s'est bien passe.

S'y ajoutent deux choses. **Il est binaire**, donc a une chance sur deux au hasard. Et **`PLA-1` n'est
pas tenue** : *non* n'est pas une reponse qu'un enfant peut serieusement envisager apres avoir lu cet
enonce - c'est un bouton de remplissage, ce que `PLA-1` nomme en toutes lettres.

**Ce que j'attends** : que l'item mette une vraie regle en jeu (*"A l'epervier, Malo traverse en
sortant du couloir. A-t-il respecte la regle ?"*), et qu'il porte trois propositions plutot que deux.

#### `JEU-R5` - item 4 : *"quand on est touche"* puis *"il va dans le camp adverse"*

**A l'ecran, manche 10** : *"A la balle au prisonnier, que se passe-t-il quand on est touche ?"* - les
trois propositions sont *il gagne un point*, *il va dans le camp adverse*, *il devient arbitre du
jeu*.

**L'enonce dit *on*, les trois reponses disent *il*.** Le pronom des propositions n'a aucun antecedent
dans la phrase que l'enfant vient de lire. Un lecteur adulte comble le trou sans le voir ; **un enfant
de CP, qui lit encore mot a mot, doit d'abord decider qui est *il* avant de pouvoir juger la
reponse.** C'est de la charge cognitive ajoutee a un item qui n'en avait pas besoin, et elle porte sur
la reprise pronominale, qui est justement une difficulte de lecture du CP.

**Ce n'est ni de l'orthographe, ni de l'accentuation, ni de la ponctuation** : c'est le niveau
scolaire de ce que l'enfant lit, donc mon domaine et non celui du `relecteur-pedagogique`.

**Ce que j'attends** : *"A la balle au prisonnier, que devient un joueur touche ?"* L'antecedent est
alors dans l'enonce, et les trois propositions ne changent pas d'un mot.

### 6.4 `JEU-R6` - la tache B se gagne a la forme de l'enonce sur les items 10 et 11

**`FAB-1c`, branche registre, et `CAT-1` par le meme fait.**

| Item | Enonce | Bonne reponse | Ce qui suffit pour gagner |
|---|---|---|---|
| 10 | *"Aux demenageurs, Hugo porte deux objets a la fois. Que se passe-t-il ?"* | *Hugo n'a pas respecte la regle* | L'enonce **signale lui-meme** que quelque chose cloche (*deux objets a la fois*), et c'est la seule proposition qui parle de regle. Les deux autres (*Hugo a gagne la partie*, *l'equipe change de camp*) ne sont pas des sanctions |
| 11 | *"A 1, 2, 3, soleil, Hugo avance quand le meneur regarde. Que se passe-t-il ?"* | *Hugo doit retourner au depart* | Meme forme : l'enonce dit la transgression, et *retourner au depart* est la seule proposition de forme *sanction* en face de deux recompenses (*gagne la partie*, *devient meneur*) |

**Deux items sur cinq, sur une tache de cinq items dont le seuil de `FAB-1c` est franchi a deux.** La
condition n'est pas tenue. C'est moins spectaculaire que les quatre items sur cinq du paquet de
securite, mais c'est le meme mecanisme : la **forme** de la bonne reponse la designe, et l'enfant n'a
pas besoin de connaitre le jeu.

**Ce que j'attends** : que les deux distracteurs soient eux aussi des consequences plausibles d'une
faute (*Hugo passe son tour*, *Hugo laisse un objet a l'arrivee*), de sorte que l'enfant doive savoir
**laquelle** est la sanction du jeu, et non seulement qu'il y en a une.

### 6.5 Les deux reserves non bloquantes de ce paquet

#### `JEU-R7` - la regle *un objet a la fois* n'est jamais posee, elle n'apparait qu'en distracteur

**Constat.** L'item 10 juge Hugo sur la regle *un seul objet a la fois* aux demenageurs. Cette regle
n'est **rappelee dans aucun enonce**. Elle apparait dans le paquet a deux endroits seulement : en
**distracteur** de l'item 6 (*en portant deux objets a la fois*) et dans l'**indice** de l'item 10.

**Je ne le retiens pas comme motif de refus.** La regle *un objet par passage* est bien la forme
courante des demenageurs a l'ecole, donc `EPS-1` est plausiblement satisfaite par sa branche
*universellement scolaire*. **Je ne fabrique pas un manquement sur une variante dont je ne suis pas
sure**, et je prefere le dire que de laisser croire que j'ai tranche.

**Ce qui reste** : un enfant rencontre cette regle pour la premiere fois **en position de mauvaise
reponse**. C'est un ordre d'apprentissage a l'envers.

**Ce que j'attends** : que l'enonce de l'item 10 pose la regle avant de la faire juger, **ou** qu'un
item de la tache A ait cette regle en **bonne** reponse avant que la tache B ne la juge. Si la
premiere voie est retenue, le compte de mots et de phrases est a recontroler contre `LON-1` et
`LON-2`.

#### `JEU-R8` - *"Au loup et les moutons"* ne se lit pas au CP

**Constat.** Cinq enonces sur onze ouvrent par le nom du jeu. Quatre sont lisibles (*Au chat perche*,
*A l'epervier*, *A la balle au prisonnier*, *Aux demenageurs*). Le cinquieme, **"Au loup et les
moutons"**, ne l'est pas : la contraction *au* ne peut pas gouverner les deux membres du nom, et un
enfant qui lit mot a mot bute sur la phrase avant d'arriver a la question.

**La forme grammaticale de cette tournure appartient au `relecteur-pedagogique`** (`DEC-0002`,
amendement du 2026-08-04 ; `DEC-0056`), et **je la lui route sans la corriger**. Ce qui m'appartient
et que je retiens ici, c'est l'obstacle de lecture au niveau vise.

**Ce que j'attends** : *"Au jeu du loup et des moutons"*, ou la forme que le
`relecteur-pedagogique` retiendra - avec le compte de mots recontrole, l'enonce etant a dix mots
aujourd'hui.

### 6.6 Ce que je ne transforme pas en reserve

- **Les items 7, 10 et 11 declarent qu'un enfant nomme n'a pas respecte la regle.** J'ai regarde si
  `EPS-3` mordait : **elle ne mord pas**. La condition interdit qu'une **attitude, une emotion ou une
  conduite morale** soit portee par une bonne reponse ; juger le respect **factuel** d'une regle de
  jeu n'est pas de cette famille, et c'est exactement la seconde tache que la story demande. **Ce qui
  cloche sur l'item 7 est que le jugement soit faux, pas qu'il existe.**
- **La repartition des jeux entre les taches** : le chat perche sert trois items, 1, 2, 3 soleil deux,
  les demenageurs deux, et l'epervier, la balle au prisonnier et le loup un chacun. Ce n'est pas
  equilibre, et cela ne me gene pas : `FER-1` n'enumere rien a couvrir sur cette ligne.
- **La consigne du catalogue** (*"Lis bien, puis dis quelle est la regle du jeu, ou ce qui se passe
  dans la situation."*) nomme les deux taches et ne reprend pas la formule generique. **`DEC-0078` est
  tenue.**

## 7. Les conditions inedites que ce premier contact me fait ecrire

**C'est le propre d'un premier passage, et ces trois conditions sont opposables au second lot d'EPS,
pas a celui-ci.** Aucune n'a servi a refuser quoi que ce soit aujourd'hui : les deux refus reposent
sur `FAB-1c` (2026-09-07), sur `EPS-1` (2026-09-03) et sur l'exactitude du contenu, toutes trois
anterieures au lot.

> **`EPS-4` - nouvelle, datee du 2026-09-09. Sur une ligne d'EPS, la bonne reponse n'est pas la seule
> proposition a nommer un danger, une blessure, un secours ou un choc.** Les trois propositions
> appartiennent au meme champ ; ce qui les distingue est **laquelle** est la vraie raison, jamais le
> fait que l'une seule parle de se faire mal.
>
> **Motif, mesure en jouant** : c'est `CAT-1` transposee du registre moral au registre du danger, et
> c'est le defaut qui a fait refuser la tache B du paquet de securite - quatre items sur cinq.
> `CAT-1` est ecrite pour l'EMC ; cette condition-ci porte le meme mecanisme sur l'EPS, dont toutes
> les lignes jouables parlent de securite ou de sante. **Je ne l'ai pas vu venir sur la bonne
> branche** : ma vigilance annoncee a la story `S32-5` portait sur les amorces, et l'equipe a bien
> evite les amorces.

> **`EPS-5` - nouvelle, datee du 2026-09-09. Un item de `EPS-CP-04` porte sur une activite physique
> ou sur un lieu de pratique d'EPS.** La securite routiere du pieton seule ne sert pas cette ligne :
> elle releve de la premiere education a la route. Un engin roulant, l'eau, un agres, un parcours, un
> gymnase, une cour la servent.
>
> **Motif** : le libelle dit *"propres a l'activite et au lieu de pratique"*, et le domaine ou j'ai
> range cette ligne borne les milieux par une parenthese. Deux items sur onze du premier lot y
> derivent (`SEC-R1`). **Mon propre exemple du 2026-09-03 portait l'engin roulant et c'est lui qui
> tenait l'ancrage** ; je le rends explicite au lieu de compter sur un exemple.

> **`EPS-6` - nouvelle, datee du 2026-09-09. Aucun indice d'EPS ne decrit la dynamique d'un jeu ou
> d'un effort par une image : au CP, l'indice est lu au premier degre.** *"Le loup grandit au fil de
> la partie"* pour dire que les loups deviennent plus nombreux est faux tel qu'il est lu.
>
> **Motif** : l'EPS se decrit par des dynamiques - un role qui se propage, une equipe qui se vide, un
> effort qui monte - et la langue courante les dit par des images. L'indice est le seul texte que lit
> l'enfant **qui vient d'echouer** ; il ne peut pas etre celui qui demande une lecture figuree.
> Troisieme paquet sur lequel je trouve un indice qui brouille ce qu'il explique, apres `MEL-R1` et
> `OBT-R1` a s31.

## 8. La porte technique - les quatre jobs, nommes un par un

**Je ne deduis aucun job d'un autre.** Lances dans ce worktree, sur l'arbre que je juge.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `build` | `mvn -B clean verify` sur la racine, via l'enveloppe de toolchain | **vert** - `BUILD SUCCESS`, **1439 tests**, 0 echec, 0 erreur, 0 ignore |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files` filtre sur `verifier-chemins.sh` | **vert** - *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | les trois motifs du job (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | **vert** - **9 juges purs executes**, aucun motif vide |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis mes messages de commit passes a `verifier-prefixes.sh` | **vert** |

**Le geste que le depot a paye cher** - lancer la commande de la CI sur son propre diff avant de
commiter - est fait ici, et les quatre jobs sont nommes plutot que deduits.

**Une chose a mon debit sur ce point** : mon premier `mvn -B clean verify` a echoue, et pour une
raison qui n'a rien a voir avec l'arbre - l'application que je jouais tenait encore le fat jar
ouverte, et `clean` n'a pas pu le supprimer. **Un echec de build cause par l'auditrice n'est pas un
Done technique rouge**, mais il aurait pu etre lu comme tel par qui n'aurait vu que la ligne
`BUILD FAILURE`. Je l'ecris pour que personne n'ait a le deduire, et l'ordre correct est : arreter
l'application **par son identifiant de processus**, puis mesurer.

## 9. Ce dont je reste la seule garde sur ces deux paquets

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Garde executable ? |
|---|---|
| **`FAB-1c` branche registre** - la bonne reponse ne se reconnait pas a son champ lexical | **aucun**, et c'est un jugement de registre qu'aucun automate ne rend. **C'est le motif de mes deux refus : sans moi, les deux lignes fermaient** |
| **`CAT-1`, `EPS-4`** | **aucun** |
| **`EPS-1`** - la regle du jeu est rappelee ou universelle | **aucun**, et il en faudrait un catalogue de variantes que personne n'a |
| **`EPS-2`, `EPS-3`** | **aucun** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **aucun**, et c'est elle qui tombe sur l'item 8 du jeu collectif |
| **`EPS-6`** - un indice n'est pas une image | **aucun** |
| **L'exactitude de chaque regle de jeu et de chaque regle de securite** | **aucun**, et c'est ce qui a produit `JEU-R1`. **Un automate ne sait pas ce que dit le chat perche** |
| **La reprise pronominale entre un enonce et ses propositions** (`JEU-R5`) | **aucun** |
| **`LON-1`, `LON-2`** | **`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, depuis `ADR-0089`.** Premier sprint ou je ne recompte pas a la main - et **ma barre est necessaire, jamais suffisante** : elle n'a rien vu de `JEU-R5`, qui est un defaut de lecture a douze mots |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`** | **aucun sur ces deux paquets** : les tests de paquet a la `PaquetCm1EmcDroitDevoirTest` n'existent pas pour l'EPS. **Je les ai comptes a la main** |

**Confirmation demandee sur le plancher de `FER-3`** : il est **3**, et non 4. Le depot l'ecrit dans
son code - `MINIMUM_PAR_TACHE = 3`, dans `PaquetCm1EmcDroitDevoirTest` et dans
`PaquetCm1EmcFaitOpinionTest` - et c'est bien ce que j'ai ecrit le 2026-09-03 : *"au moins deux
taches distinctes, la moins servie portant au moins trois items"*. **Une consigne qui a dit 4 etait
fautive ; le chiffre est 3, je le confirme et je ne le releve pas.**

## 10. Deux corrections a mes propres constats, faites avant de m'en servir

### 10.1 J'ai compte la longueur des propositions a la main et je me suis trompee de sens

**Ce que j'avais releve** en lisant le paquet de securite : *"sur la tache B, la bonne reponse est la
plus longue ou a egalite sur les items 8, 9, 10 et 11"* - j'allais en faire une seconde branche du
motif de refus, la branche **longueur** de `FAB-1c`.

**Ce que la mesure dit** : sur l'item 8, la bonne reponse (*pour verifier qu'aucune voiture n'arrive*,
**5 mots** sous la convention d'`ADR-0089` ou une forme elidee compte pour un mot) est la **plus
courte** des trois, en face de deux distracteurs a 6 mots. **J'avais compte les elisions comme des
mots separes** - la meme faute de methode que ma definition litterale de la phrase, qui coupait
`"0 + ? = 10"` en deux et rendait vingt-deux enonces servis rouges.

**Consequence** : la branche longueur de `FAB-1c` **ne mord pas** sur la tache B, et je la retire du
motif. Le refus tient sur la branche **registre** seule, qui est mesuree en 5.3 et qui suffit. **Sans
ce recomptage j'aurais refuse sur un chiffre faux, et le motif aurait ete actionne de travers** :
l'equipe aurait rallonge des distracteurs au lieu de changer leur champ lexical.

### 10.2 Mon *"deux sprints"* du 2026-09-08 n'est plus une prevision valide

**Ce que j'ai ecrit hier** : les six lignes jouables d'EPS font 66 items, soit trois lots, donc
*"l'EPS ne se ferme pas en un sprint. Il en faut deux, et c'est arithmetique."*

**L'arithmetique tient. L'hypothese implicite non** : elle supposait que chaque lot ferme ses lignes
au premier passage. **Aucune des deux premieres n'a ferme.** A un taux de refus de six paquets sur
sept au premier contact, la fermeture des six lignes d'EPS demande **trois lots plus leurs
reprises**, et non trois lots.

**Je ne rechiffre pas le calendrier ici** - ce n'est ni mon role ni ce sprint - mais je refuse de
laisser mon *"deux sprints"* se lire comme encore vrai. C'est mon chiffre, et c'est moi qui le
retire.

## 11. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **`EPS-CP-04` reste `non couvert`** | `camille-po` | La ligne ne passe pas a `couvert-valide`. Le compte de la matrice lui appartient ; je ne la touche pas |
| **`EPS-CP-08` reste `non couvert`** | `camille-po` | Idem |
| **`SEC-R3`** (motif de refus, securite) | `camille-po`, via `sofia-sm` | Reecrire les huit distracteurs des items 8, 9, 10 et 11 pour que les trois propositions de chaque item nomment un danger, une blessure ou une consequence, dont deux fausses. **Puis rejeu par moi** |
| **`JEU-R1` a `JEU-R6`** (motifs de refus, jeu collectif) | `camille-po`, via `sofia-sm` | L'item 7 refait sur l'effet et non sur la faute ; l'item 5 rappelle sa variante ou change de jeu ; son indice cesse d'etre une image ; l'item 8 met une vraie regle en jeu et porte trois propositions ; l'item 4 remplace *on* par un antecedent repris ; les distracteurs des items 10 et 11 deviennent des sanctions plausibles. **Puis rejeu par moi** |
| **`SEC-R1`, `SEC-R2`, `JEU-R7`** (reserves non bloquantes) | `camille-po`, via `sofia-sm` | A traiter dans la meme reprise que les motifs, les deux paquets etant de toute facon rouverts |
| **`JEU-R8`** - *"Au loup et les moutons"* | `relecteur-pedagogique` | La forme grammaticale est la sienne, pas la mienne. Je route l'obstacle de lecture, je ne corrige pas la tournure |
| **`EPS-4`, `EPS-5`, `EPS-6`** | `camille-po`, pour la matrice ; toute l'equipe, pour le second lot | Trois conditions opposables au second lot d'EPS, allouees au registre dans ce meme diff |
| **Un test de paquet pour les deux paquets d'EPS** | `theo-archi`, via `sofia-sm` | `FER-2`, `FER-3`, `BIN-1`, `FAB-1a` et `FAB-1b` sont mordables sur un paquet enumere - `PaquetCm1EmcDroitDevoirTest` le fait deja pour l'EMC. **Je les ai comptes a la main sur vingt-deux items** ; ce n'est pas une bonne facon de depenser une porte pedagogique |

## 12. Le cout de cet audit contre mon budget

| Poste | Chiffre |
|---|---|
| Budget opposable | **4,0** |
| dont marge `PAR-11` amendee (terrain sans image, `PAR-13`) | 1,5 |
| dont marge non allouee | 0,5 |
| **Depense reelle** | **2,5** |
| Lot `S32-5`, audit des deux paquets, tarif annonce | 1,0 |
| Verification de la frontiere corps-et-effort item par item (section 4) | 0,5 |
| Trois conditions inedites ecrites (section 7) | 0,5 |
| Deux corrections a mes propres constats (section 10) | 0,5 |
| **Marge non tiree** | **1,5** |

**Je n'ai pas tire sur ma marge de `PAR-11`, et il faut dire pourquoi plutot que de s'en feliciter.**
Elle etait la pour le cas ou les conditions inedites de l'EPS se reveleraient couteuses a instruire ;
elles l'ont ete **moins** que prevu, pour une raison qui n'est pas a mon credit : **deux paquets
refuses coutent moins cher a auditer que deux paquets valides.** Un refus s'arrete au premier defaut
suffisant ; une validation doit balayer toutes les conditions jusqu'a la derniere. Les 1,5 non tires
sont l'economie d'un travail que je n'ai pas eu a finir.

**Ce que la marge a reellement paye** : les sections 4 et 10. Verifier la frontiere sur vingt-deux
items plutot que de croire un commentaire qui la recopie, et recompter mes propres longueurs avant de
m'en servir. **Sur un terrain sans aucune image, c'est exactement ce que la marge etait censee
financer**, et c'est le seul endroit ou elle a servi.

**Les 1,0 de reserves du sprint ne sont pas depenses** : les deux paquets etant refuses, leurs
reserves partent en reprise avec leurs motifs et non en story separee. **Je ne facture pas un solde
que je n'ai pas eu a faire.**
