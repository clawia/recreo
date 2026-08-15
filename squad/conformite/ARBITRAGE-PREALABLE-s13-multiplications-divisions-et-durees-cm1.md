# Arbitrage prealable - S13-2 (multiplier et diviser par 10 et 100) et S13-3 (problemes de durees), CM1 - nadia-prof

> Ce fichier est un **arbitrage prealable, pas un Done pedagogique**. Aucun contenu de ces deux
> lignes n'existe : il n'y a rien a jouer et rien a valider. Ce que j'ecris ici, ce sont les
> conditions auxquelles je validerai, posees **avant** que les developpeurs ecrivent, pour qu'aucun
> lot ne soit jete apres coup.
>
> Les Done pedagogiques de S13-2 et S13-3 seront prononces, chacun apres avoir joue son paquet, dans
> le bloc de validation de `FICHE-bandit-manchot-multiplications-divisions-cm1.md` et de
> `FICHE-bandit-manchot-problemes-durees-cm1.md`. Ces deux fiches renvoient a ce fichier.

- **Date** : 2026-08-15
- **Auteur** : nadia-prof
- **Sprint** : s13
- **Saisi par** : `DEC-0030-perimetre-sprint-13-trois-lignes-mathematiques-cm1-et-le-plafond-de-la-
  mecanique-unique.md`, section 5, cinquieme demande
- **Referentiel** : `squad/programme/mathematiques/competences-cm1.md`
- **Increment de reference** : branche `s13-nadia-arbitrages`, ouverte sur `round-two` a `9430cfd`,
  464 tests, 28 regles - inchanges de mon fait, je n'ecris que de la documentation.

## 0. Ce que j'ai fait, et ce que je declare degrade

**Je n'ai rien joue, et c'est normal ici : il n'y a rien.** Aucun de ces deux paquets n'existe,
aucun de ces deux jeux n'est au catalogue. Un arbitrage prealable ne se joue pas, il se lit. Mon
audit porte sur :

- les deux lignes du referentiel, mot pour mot ;
- les criteres d'acceptation de S13-2 et S13-3 tels que `camille-po` les a ecrits
  (`docs/backlog/backlog.md`, section "Sprint 13") ;
- `contenu-repris/quiz/cm1-heure.yml`, lu item par item, calcul par calcul, les dix ;
- le garde-fou de rang reellement code (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`),
  ligne par ligne, pour savoir **ce qu'il mesure vraiment** sur ces deux contenus-la.

**Ce que ce mode ne me dit pas, et je le nomme plutot que de le taire** : je ne saurai pas si un
enonce de ces paquets est lisible a l'ecran, si un indice arrive au bon moment, ni si une partie de
dix manches tient l'attention d'un CM1, avant d'avoir joue les paquets livres. Aucune condition
ecrite ici ne vaut Done pedagogique par avance, et un lot qui tient mes vingt-et-une conditions peut
encore etre refuse pour ce que seul le jeu montre.

## 1. La lecon que j'applique ici, avant d'ecrire une seule condition

Au sprint 12, ma condition P-6 enumerait **deux familles de distracteurs** pour le perimetre et
interdisait les autres. Les deux familles choisies etaient l'une toujours en dessous de la bonne
reponse, l'autre presque toujours au-dessus : **ma condition, et non celui qui l'appliquait,
produisait un rang constant**. Un enfant qui pressait toujours le nombre du milieu gagnait 91,7 %
des manches sans lire un enonce. Je l'ai corrigee le 2026-08-15 en posant une condition **sur le
resultat et non sur une liste** (`FICHE-bandit-manchot-perimetre-cm1.md`, complement du 2026-08-15).

**S13-2 est exactement le terrain ou la meme faute se refait.** Multiplier par 10 ou par 100 range
les valeurs sur une echelle : `n/100 < n/10 < n < 10n < 100n`. Si les distracteurs d'un item sont
"le facteur d'a cote" et "le sens inverse", comme les criteres de `camille-po` les nomment, alors la
bonne reponse tombe **au milieu, mecaniquement, sur tout le paquet** - et si les distracteurs sont
"les deux valeurs plus petites", elle est **toujours la plus grande**. Ce n'est pas une hypothese :
c'est la meme arithmetique que celle du perimetre, sur une echelle encore plus reguliere.

**Donc, dans les deux paquets ci-dessous, je n'enumere aucune famille fermee de distracteurs.** Je
dis quelles erreurs sont reelles - pour que personne n'invente un nombre de remplissage - et je pose
une **condition d'aboutissement sur le resultat**, verifiable par un tiers sans me la redemander.
Celui qui ecrit choisit ses familles ; c'est le compte final qui doit tenir.

**Les deux garde-fous livres depuis me dispensent de compter a la main, et je le dis pour ne pas
laisser croire qu'ils font tout mon travail** : aucun rang de la bonne reponse n'est majoritaire
au-dela de 50 %, aucun ne tombe sous 10 %. Ce sont des **planchers de la CI**, pas mes barres. Ce
qu'ils ne voient pas reste entier a ma garde : la plausibilite de chaque distracteur, l'utilite de
l'indice, l'exactitude de chaque calcul, et le niveau tel qu'il se joue. Et sur S13-3, ils voient
**moins que cela** - section 3.6, ou j'explique ce que leur code fait reellement d'une heure.

## 2. S13-2 - multiplier et diviser par 10 et par 100

### 2.1 Ce que la ligne dit, et la lecture que je retiens

La ligne du referentiel dit : *"Calcul mental - multiplications et divisions simples (multiples de
10, 100)."* Elle se lit de deux facons, et il faut trancher avant d'ecrire vingt-quatre items :

- **lecture stricte** : l'operateur est **10 ou 100**, et rien d'autre ;
- **lecture large** : l'operateur est un **multiple** de 10 ou de 100 - donc aussi 20, 50, 200, 300.

**Je retiens la lecture stricte pour ce paquet**, et voici pourquoi ce n'est pas un repli : multiplier
par 20 ou par 300, c'est **deux gestes** - multiplier par 2 puis par 10, ou par 3 puis par 100. C'est
une competence reelle de CM1, mais ce n'est pas la meme, elle s'appuie sur celle-ci, et l'y melanger
ferait varier d'un item a l'autre ce que l'enfant doit faire - le defaut que j'ai refuse ailleurs
sous C-5 et P-4. **1000 est egalement hors du paquet** : le referentiel ne le nomme pas.

**Consequence pour la matrice** : sous cette lecture, la ligne ouvre **en entier**, sans mention de
couverture partielle. **Je m'engage sur ce point** : un paquet qui tient MD-1 a MD-10 me suffit, et
je ne reviendrai pas dans trois sprints reclamer les multiples de 20 comme s'ils avaient toujours
ete dus.

**Une remarque sur le referentiel, que j'adresse a `camille-po` par `sofia-sm` et que je ne tranche
pas moi-meme** - meme geste qu'au sprint 12 sur les fractions inferieures a 1. Le texte "multiples de
10, 100" est ambigu, et un CM1 travaille reellement `x 1000` et `x 20`, `x 50`, `x 200`. Si ce socle
est elargi un jour, ce paquet deviendra une **couverture partielle nommee** de la ligne elargie, et
il faudra l'ecrire ainsi ce jour-la plutot que de faire comme si de rien n'etait. Je le dis
maintenant pour que le compte ne se perde pas.

### 2.2 Mes dix conditions

1. **MD-1 - Operateur.** Chaque item est une multiplication par **10** ou par **100**, ou une
   division par **10** ou par **100**. Aucun autre operateur, aucun autre facteur, **pas de 1000**,
   pas de multiple compose (20, 50, 200, 300).
2. **MD-2 - Les quatre familles, chacune presente pour de bon.** Les quatre combinaisons - `x 10`,
   `x 100`, `: 10`, `: 100` - sont chacune portees par **au moins un cinquieme du paquet**. La
   condition de `camille-po` (multiplication et division a une unite d'ecart, 10 et 100 chacun au
   moins un quart) laisse passer un paquet ou `: 100` n'apparaitrait qu'une fois ; celle-ci ne le
   laisse pas. Elle a en outre un effet que je veux : un enfant qui presse "la proposition qui a un
   zero de plus que le nombre de l'enonce" ne peut gagner que sur la famille `x 10`, donc **au plus
   un item sur cinq**.
3. **MD-3 - Bornes des nombres.** Le nombre de depart et le resultat sont des **entiers positifs**,
   aucune ecriture decimale nulle part - je confirme la borne de `camille-po` et j'en donne le motif :
   ce depot n'enseigne l'ecriture decimale sur aucune ligne livree, et faire lire `2,3` a un enfant
   pour verifier qu'il sait diviser, c'est verifier deux choses et n'en enseigner aucune. En outre :
   **le resultat reste sous le million** (coherence avec la ligne des grands nombres ouverte le meme
   sprint) ; le nombre de depart porte **au plus quatre chiffres significatifs** ; **au plus un quart
   du paquet** part d'un nombre a un seul chiffre (`7 x 100` est un item de CE2, il en faut peu) ; et
   **au moins trois items** partent d'un nombre a **zero intercalaire** (`305 x 10`, `2 007 x 100`),
   parce que c'est la que se voit si l'enfant a compris la valeur des positions ou s'il recopie des
   zeros.
4. **MD-4 - Ce que l'enfant lit.** Le **signe multiplie** (U+00D7) et le **signe divise** (U+00F7),
   jamais la lettre `x` du clavier, **jamais la barre oblique** `/` - qui n'est pas un signe de
   division a l'ecole elementaire francaise et que les criteres du backlog emploient pourtant dans
   leur exemple (`4 500 / 100 = ?`). Meme remarque qu'en T-4 au sprint 12 : le backlog est en ASCII
   par convention de redaction, **le contenu lu par l'enfant ne l'est pas et n'a pas a l'etre** - et
   les enonces que ce fichier-ci cite en ASCII (`23 x 10 = ?`) sont **cites, pas prescrits sous cette
   forme**. Les
   milliers sont **separes par une espace** (`4 500`, jamais `4500`), de facon **constante dans tout
   le paquet**, enonces et propositions comprises. **Aucune unite de grandeur** nulle part : c'est du
   calcul mental, pas la ligne des conversions, deja couverte.
5. **MD-5 - Condition d'aboutissement, sur le resultat et non sur une liste.** Sur l'ensemble du
   paquet, **aucun des trois rangs de la bonne reponse - la plus petite, l'intermediaire, la plus
   grande - ne depasse 40 % des items, et aucun ne tombe sous 20 %.** Celui qui ecrit choisit ses
   distracteurs comme il l'entend, du moment que ce compte tient.
   **Pourquoi ma barre est plus haute ici que les 50 % et 10 % de la CI**, et pourquoi ce n'est pas
   un caprice : sur ce contenu precis, l'echelle des valeurs est parfaitement reguliere et la
   repartition equilibree est **atteignable sans contorsion** - la famille `x 100` donne facilement
   "la plus grande", la famille `: 100` donne facilement "la plus petite", et les familles `x 10` et
   `: 10` donnent les trois rangs selon les distracteurs retenus. Une repartition a 33/33/33 est
   accessible. Je le verifie moi-meme en recomptant, comme au sprint 12.
   **Un piege a ne pas se tendre** : le garde-fou de majorite compare **strictement** a 50 %, donc un
   paquet a 50,0 % pile passe au vert tout en etant a la limite exacte de ce que je refuse. Ne
   naviguez pas a cette valeur.
6. **MD-6 - Distracteurs : une erreur reelle, nommee, et pas de proposition gratuite a eliminer.**
   Chaque distracteur correspond a une erreur qu'un CM1 fait vraiment - un zero de trop, un zero de
   moins, le mauvais sens, le bon sens avec le mauvais facteur - et cette erreur est **nommee dans le
   fichier**, comme au sprint 12 : un nombre choisi pour remplir est refuse. **Aucune proposition
   egale a une autre** dans un item, meme exigence que P-7, C-7 et T-5, et pour la meme raison :
   rien dans le code ne le verifie - le garde-fou livre depuis couvre ce point, je le note sans
   retirer la condition.
   **Et une clause neuve, qui est la version generale de ce que j'ai appris sur le rang** : la valeur
   **egale au nombre de l'enonce** ("je n'ai rien fait") est un distracteur legitime, mais elle ne
   peut **jamais** etre la bonne reponse par construction. Si elle apparait sur tous les items,
   l'enfant apprend a eliminer une proposition sur trois gratuitement, a chaque manche, sans rien
   savoir - c'est le meme defaut que le rang a 0 %, sur un autre axe, et **aucun garde-fou ne le
   voit**. Elle est donc admise **sur un tiers des items au plus**. Cela vaut pour toute valeur qui
   ne peut jamais etre correcte par construction, pas seulement pour celle-la.
7. **MD-7 - Indice : une methode, pas un slogan.** L'indice donne la regle ou le chemin - *"Multiplier
   par 100, c'est ecrire deux zeros de plus."*, *"Diviser par 10, c'est enlever un zero a droite."*,
   *"Diviser, c'est rendre le nombre plus petit."* - jamais le resultat. **Au moins quatre indices
   distincts**, un par famille au minimum ; il peut etre partage par les items d'une meme famille,
   **il ne peut pas etre le meme sur tout le paquet**. Je le pose explicitement parce que le critere
   automatique verifie que le champ est rempli, et qu'un *"Calcule."* copie vingt-quatre fois le
   remplirait en n'apprenant rien a personne.
8. **MD-8 - Formes exclues, nommement.** Aucune **mise en situation** ("7 paquets de 10 gateaux...") :
   la ligne dit "calcul mental", et les problemes ont leurs propres lignes. Aucun **facteur ou
   diviseur manquant** ("23 x ? = 230") : c'est le verbe inverse, il ne se melange pas au calcul
   direct sans avertir l'enfant. Aucune **operation a trou** sur le nombre de depart. Aucune
   **unite**. Aucun item **definitionnel** ("multiplier par 10, c'est...") : contrairement au
   perimetre, ou j'en avais admis deux, il n'y a pas ici de mot dont l'enfant doive apprendre le sens
   avant de calculer.
9. **MD-9 - Enonce.** Une seule ligne, le calcul et rien d'autre, sous la forme `23 x 10 = ?` (cite en ASCII, voir MD-4). C'est
   la forme la plus courte de tout le catalogue et c'est un avantage que je veux garder : les enonces
   longs de `cm1-perimetre` poussent le message d'erreur et l'indice sous le pli a 360 x 640 sur
   100 % de ses echecs. Ce paquet-ci n'a aucune raison de le refaire.
10. **MD-10 - Titre du jeu.** Il annonce **les deux operations**, pas seulement l'une. Un enfant qui
    a choisi "Les multiplications" et qui recoit une division a le droit de se croire en faute -
    meme motif que C-9 au sprint 12. La consigne du paquet dit, elle, ce qu'il y a a faire.

## 3. S13-3 - problemes de durees

### 3.1 Etat de la matiere premiere, verifie item par item

J'ai relu les dix questions de `contenu-repris/quiz/cm1-heure.yml`. **Le compte de `camille-po` est
juste : deux items seulement relevent de cette ligne**, et je le confirme plutot que de le repeter -
"De 14 h a 16 h, combien de temps s'est ecoule ?" et "Un film commence a 8 h 30 et finit a 10 h. Il
dure...". Les huit autres sont trois lectures de l'heure (competence CP), quatre conversions d'unite
de temps seule, et une conversion inverse ; aucun n'est un horaire d'arrivee. **Les dix reponses sont
justes.**

**Deux choses que je releve dans cette matiere et qui doivent ne pas etre reprises telles quelles** :

- ces items portent **trois distracteurs**, pas deux ; la mecanique de ce depot en attend deux ;
- surtout, l'item du film **melange deux ecritures dans les memes propositions** : la bonne reponse
  est `1 h 30` (une duree) et l'enonce donne `8 h 30` (un instant), avec **exactement la meme
  forme**. C'est le defaut central de ce contenu et il commande ma condition D-2 ci-dessous.

### 3.2 La ligne peut ouvrir en entier, et je m'y engage

Le referentiel dit : *"Resoudre des problemes de durees (calcul d'une duree, d'un horaire
d'arrivee)."* La parenthese nomme **exactement deux formes**. Un paquet qui les porte toutes les deux
couvre la ligne **en entier**, sans mention de couverture partielle - meme raisonnement que pour le
perimetre, dont le referentiel ne nommait que le carre et le rectangle.

**Ce que je refuse d'avance et qui n'est pas un manque** : la troisieme forme naturelle - retrouver
**l'heure de depart** a partir de l'arrivee et de la duree - n'est pas dans la ligne. Elle n'entre
pas dans ce paquet, exactement comme j'ai exclu l'item inverse du perimetre en P-4 : c'est un
troisieme geste, et melanger trois sens sans avertir l'enfant fait varier d'un item a l'autre ce
qu'il doit faire. **C'est un bon motif de story a part**, et je le signale a `camille-po` comme tel.
Son absence ne pourra pas m'etre opposee comme un trou de couverture, et je ne la reclamerai pas
apres coup.

### 3.3 Ce que je confirme des bornes de `camille-po`, et ce que j'y ajoute

Ses trois bornes - une seule journee, minutes rondes de 5 en 5, aucune seconde - **sont justes toutes
les trois pour un CM1**, et je les reprends telles quelles. Elles ne suffisent pas : elles bornent la
difficulte sans garantir qu'il y en ait. Un paquet entierement compose de "de 14 h a 16 h" tient ses
trois bornes et n'apprend rien. Mes conditions D-4 et D-5 portent la ou les siennes s'arretent.

### 3.4 Mes onze conditions

11. **D-1 - Deux formes, et seulement elles.** Chaque item est soit un **calcul de duree** (deux
    instants donnes, duree a trouver), soit un **horaire d'arrivee** (instant de depart et duree
    donnes, arrivee a trouver). Chacune porte **au moins un tiers du paquet** - je confirme
    `camille-po`. **Exclus** : la lecture de l'heure ("huit heures et quart, c'est..."), la
    conversion d'unite de temps seule ("combien de minutes dans une heure ?"), et la recherche de
    l'heure de depart (section 3.2).
12. **D-2 - Un instant ne s'ecrit pas comme une duree. C'est ma condition la plus importante sur ce
    paquet.** Un enfant de CM1 confond exactement cela : `8 h 30` sur une horloge et `8 h 30` de
    trajet ne sont pas le meme objet, l'un est un point, l'autre une longueur, et on ne les additionne
    pas de la meme facon. Le contenu repris les ecrit **a l'identique**. Donc : **les instants et les
    durees portent deux ecritures visiblement differentes, constantes dans tout le paquet** - par
    exemple `8 h 30` pour un instant et `1 h 30 min` ou `1 heure 30 minutes` pour une duree ; le choix
    de la forme appartient a qui ecrit, pas la constance, qui est ma condition. **Et dans un meme
    item, les trois propositions portent la meme ecriture** : comparer `45 min` a `1 h 15` dans la
    meme liste ajoute une conversion a une question qui n'en demandait pas.
13. **D-3 - Horaires.** Journee de 24 heures (`14 h 30`, jamais "2 h 30 de l'apres-midi" ni "2 h 30 du
    matin" : ce serait une ambiguite, pas une difficulte). **Aucun item ne franchit minuit** - je
    confirme. **Aucune seconde** - je confirme. Toutes les heures d'un item sont dans la meme journee
    et l'enfant n'a jamais a la deduire.
14. **D-4 - Les minutes ne sont pas toutes des quarts d'heure.** Multiples de 5, je confirme ; mais
    **au plus la moitie du paquet** utilise `00`, `15`, `30` ou `45`. Un paquet entierement en quarts
    d'heure se resout par reconnaissance de figures apprises a l'horloge, pas par calcul de duree.
15. **D-5 - Il faut que la retenue de 60 apparaisse, et pas partout.** **Entre un tiers et deux tiers
    des items** exigent de franchir une heure ronde (`9 h 50` -> `10 h 25`, ou `1 h 45` ajoutee a
    `10 h 30`). C'est **la** difficulte de cette competence : l'enfant qui compte en base 100 tombe
    juste tant qu'aucune heure n'est franchie, et faux des qu'elle l'est. Un paquet sans franchissement
    ne teste rien ; un paquet ou tous les items en portent un ne laisse aucune marche basse a l'enfant
    qui commence. **Duree maximale de 5 heures** : au-dela, ce n'est plus du calcul, c'est de la
    patience.
16. **D-6 - Distracteurs : les erreurs sont reelles et nommees, la liste reste ouverte.** Les trois
    erreurs vraies de cette competence, que je nomme pour que personne n'invente : **compter en base
    100** (`10 h 30` + `45 min` = `10 h 75`, ou une duree annoncee `1 h 50` la ou elle vaut `2 h 10`),
    **le sens inverse** (soustraire au lieu d'ajouter), et **l'oubli du report d'heure** (bonnes
    minutes, mauvaise heure). Cette liste est un **plancher de ce qui est permis**, pas une
    prescription : tout autre distracteur est admis s'il correspond a une erreur nommee dans le
    fichier. **Aucune proposition egale a une autre** dans un item. **Aucune proposition impossible en
    soi** - `10 h 75` est un distracteur excellent parce que l'enfant l'ecrit vraiment ; mais si une
    ecriture de ce genre est retenue, elle ne doit pas etre la seule anomalie visible du paquet, sinon
    elle s'elimine a l'oeil sans calcul.
17. **D-7 - Condition d'aboutissement, sur le resultat.** Mesurees **sur la valeur reelle**, c'est-a-
    dire sur les durees et les instants convertis en minutes, **aucun des trois rangs de la bonne
    reponse ne depasse 50 % des items et aucun ne tombe sous 10 %.** Je m'aligne ici sur les seuils de
    la CI et je ne les durcis pas, contrairement a MD-5 : sur ce contenu, une repartition equilibree
    est bien plus difficile a atteindre - l'erreur de base 100 tombe presque toujours du meme cote -
    et je ne veux pas d'une barre que je n'ai pas verifiee moi-meme comme atteignable. **Ce compte
    doit etre donne par qui ecrit le paquet**, dans le rapport de la story, parce qu'aucun automate ne
    sait le calculer ici - section 3.6. Je le recompterai.
18. **D-8 - Enonce.** **Vingt-cinq mots au plus, deux phrases au plus.** Toutes les donnees sont dans
    l'enonce ; l'enfant n'a jamais a se souvenir de l'item precedent ni a deviner ce qu'on lui demande.
    Je pose ce plafond en connaissance de cause : un enonce de probleme est le plus long du catalogue,
    et `cm1-perimetre`, dont les enonces sont pourtant courts, pousse deja son message d'erreur et son
    indice sous le pli a 360 x 640 sur **100 %** de ses echecs. Ce paquet sera le pire du catalogue sur
    ce point. **M4 ne bloque plus depuis `DEC-0025` et je ne la ressuscite pas** ; je mesurerai, je
    l'ecrirai, et ce sera un fait, pas un refus.
19. **D-9 - Situations : celles que tous les enfants connaissent.** L'ecole, la recreation, la
    cantine, un trajet en bus ou en train, un film, un match, une recette, une visite. **Aucune
    situation qui suppose** que l'enfant prend l'avion, part en vacances, a une voiture a la maison,
    ou qu'un adulte le conduit quelque part. Les prenoms et les roles sont **varies et non
    stereotypes** : ni les garcons au sport et les filles a la danse, ni la mere a la cuisine. Et le
    temps qui passe reste un temps ordinaire : pas de retard dramatise, pas d'enfant en faute d'etre
    arrive trop tard.
20. **D-10 - Indice.** Il donne la methode, jamais le resultat - *"Va d'abord jusqu'a l'heure ronde,
    puis compte le reste."*, *"Une heure, c'est 60 minutes, pas 100."*, *"Ajoute d'abord les heures,
    puis les minutes."*. **Au moins un indice distinct par forme**, jamais le meme sur tout le paquet.
21. **D-11 - Vingt items au moins**, je confirme `camille-po`, et j'ajoute que les **deux items repris
    de `cm1-heure.yml` repassent devant moi comme du contenu neuf** : ils doivent recevoir un indice,
    perdre leur troisieme distracteur, et l'item du film doit se conformer a D-2.

### 3.5 Ce que je refuse d'avance, en une liste

Pour qu'aucun lot ne revienne avec ceci dedans, et pour que personne n'ait a me le redemander :

- **S13-2** : tout operateur autre que 10 et 100, y compris 1000 et les multiples composes (MD-1) ;
  toute ecriture decimale, toute virgule (MD-3) ; la lettre `x` et la barre oblique `/` comme signes
  d'operation (MD-4) ; toute unite de grandeur (MD-4) ; la mise en situation, le facteur manquant,
  l'operation a trou, l'item definitionnel (MD-8) ; le distracteur egal au nombre de l'enonce sur
  plus d'un tiers des items (MD-6).
- **S13-3** : la lecture de l'heure et la conversion d'unite de temps seule (D-1) ; la recherche de
  l'heure de depart (section 3.2) ; la meme ecriture pour un instant et pour une duree (D-2) ; deux
  ecritures differentes parmi les propositions d'un meme item (D-2) ; le franchissement de minuit,
  les secondes, l'heure ambigue du matin ou de l'apres-midi (D-3) ; une duree de plus de cinq heures
  (D-5) ; la situation qui suppose l'avion, les vacances ou la voiture familiale (D-9).
- **Les deux** : le distracteur qui n'est pas une erreur nommee dans le fichier ; deux propositions
  egales dans un item ; l'indice qui paraphrase l'enonce, l'indice qui donne la reponse, l'indice
  identique sur tout un paquet.

### 3.6 Le fait technique que je dois signaler avant que quiconque n'ecrive une ligne de S13-3

**Les deux garde-fous de rang ne savent pas lire une heure, et sur ce paquet-la ils diront quelque
chose de faux.** J'ai lu leur code plutot que de le supposer.
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` classe le rang d'un item en extrayant
**l'entier de tete de chaque proposition** (`^-?\d+`), puis en comparant ces trois entiers. Sur des
nombres nus - le perimetre, les tables, S13-2 - c'est exact. Sur des durees, non :

- `1 h 30` est lu **1** et `45 min` est lu **45** : la premiere valeur est classee comme la plus
  petite alors qu'elle vaut le double de la seconde ;
- si les trois propositions d'un item ont **la meme heure de tete** (`1 h 15`, `1 h 30`, `1 h 45`),
  les trois entiers sont egaux, aucun distracteur n'est "inferieur", et le calcul range la bonne
  reponse en **"la plus petite"** - sur **tous** les items de cette forme.

**Consequence concrete, et je la donne comme une prevision, pas comme une menace** : un paquet de
durees correctement ecrit, dont les propositions partagent souvent la meme heure, fera **rougir le
garde-fou de majorite** en affichant quelque chose comme "100 % la plus petite", alors que le rang
reel peut etre parfaitement equilibre. Le garde-fou ne sera pas plus juste dans l'autre sens : il
peut aussi rester vert sur un paquet reellement predictible.

**Ce que je demande, et ce que je refuse** :

- **Personne ne modifie le contenu pour faire verdir ce garde-fou.** Changer une bonne reponse ou un
  distracteur pour satisfaire une mesure fausse, c'est degrader ce que l'enfant lit au service d'un
  chiffre. Ce serait un motif de refus a lui seul.
- **Le symptome appartient a `theo-archi` et a `yanis-qa`**, pas a moi : je decris ce que j'ai lu, je
  ne prescris ni exemption, ni correction du test, ni forme de contenu. Trois voies existent
  visiblement - lire la valeur reelle plutot que l'entier de tete, sortir du domaine de la propriete
  les propositions qui ne sont pas des nombres nus, ou exempter ce paquet en le nommant - et le choix
  ne m'appartient pas.
- **En attendant, le rang de ce paquet est a ma garde seule**, et c'est pour cela que D-7 demande le
  compte a la main dans le rapport de la story.

Je le remonte a `sofia-sm` comme un **obstacle a S13-3**, decouvert avant le code et non apres : sans
decision de `theo-archi`, ce lot arrivera en review avec une CI rouge dont le motif n'est pas un
defaut pedagogique.

## 4. Les proprietes dont je reste la seule garde sur ces deux paquets

L'equipe doit savoir ce qui n'est **pas** couvert par un automate, sinon elle croira couvert ce qui
ne l'est pas. Apres avoir lu le schema `type: question`, le record `Question` et les garde-fous
existants :

1. **Que chaque resultat est arithmetiquement juste.** Rien ne recalcule un produit, un quotient ni
   une duree. Un paquet entier peut etre faux avec une CI verte, et cela s'est deja produit.
2. **Que chaque distracteur est une erreur reelle et non un nombre de remplissage.** Aucun automate
   ne sait ce qu'est une confusion plausible.
3. **Que l'indice aide au lieu de meubler.** Le critere existant verifie que le champ est rempli,
   ce qui est une condition de forme et non d'utilite.
4. **Que la valeur qui ne peut jamais etre correcte par construction n'est pas partout** (MD-6). Le
   garde-fou de rang ne voit pas cet axe-la.
5. **Que le rang de la bonne reponse est reellement equilibre sur S13-3** (D-7), pour la raison de la
   section 3.6 : ici, l'automate ne se contente pas d'etre aveugle, il se trompe.
6. **Que les deux ecritures - instant et duree - ne se confondent jamais** (D-2). Rien ne le verifie
   et rien ne peut le verifier sans savoir ce que le texte veut dire.
7. **Que la repartition entre les quatre familles d'operation tient** (MD-2) et **que la retenue de
   60 apparait sur la bonne part du paquet** (D-5). Ce sont des proprietes de distribution sur un
   paquet entier ; aucun test ne les porte.
8. **Que le niveau est le bon** : la lisibilite de l'enonce, la charge de ce que l'enfant tient en
   tete, et la progression au fil d'une partie de dix manches. Cela ne se lit pas, cela se joue, et
   je le jugerai sur l'increment.

## 5. Suites hors de ma main

- `camille-po` : **les deux lignes peuvent ouvrir en entier**, sans couverture partielle - S13-2 sous
  la lecture stricte de la section 2.1, S13-3 sur les deux formes que le referentiel nomme.
- `camille-po` par `sofia-sm` : la remarque sur l'ambiguite de "multiples de 10, 100" dans le
  referentiel (section 2.1). Je ne modifie pas le socle moi-meme.
- `camille-po`, deux motifs de story que j'ecarte de ces paquets **sans les condamner** : multiplier
  et diviser par 1000 et par les multiples composes (20, 50, 200), et retrouver **l'heure de depart**
  a partir de l'arrivee et de la duree.
- `sofia-sm`, `theo-archi`, `yanis-qa` : le fait de la section 3.6, comme obstacle a S13-3, avant le
  code.
- Qui ecrira S13-3 : les deux items repris de `cm1-heure.yml` ne sont pas transposables tels quels
  (trois distracteurs, aucun indice, et l'item du film enfreint D-2).
