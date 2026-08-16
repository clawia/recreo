# Arbitrage prealable - la voie de rendu des propositions du bandit manchot

- **Date** : 2026-08-16
- **Auteur** : nadia-prof
- **Objet** : trancher, avant qu'un lot soit ecrit, laquelle des quatre voies chiffrees par
  `theo-archi` corrige le fait qu'un jeton de proposition ne tient pas dans sa colonne a 360 px.
  Repondre en meme temps aux trois points qu'il me renvoie nommement : ma condition C5 face a un
  jeton en image, les neuf items qui debordent **en production**, et la variation du nombre de
  colonnes d'une manche a l'autre.
- **Saisine** : `theo-archi`, `avis-jeton-en-image.md` (branche `s13-theo-jeton`, `e4a73db`),
  section "Ce que je remonte, et a qui". Question d'origine de l'utilisatrice : *"transformer le
  nombre en image est-il possible, on pourrait les integrer au sources ?"*
- **Arbre juge** : `round-two` a `c0c51c5`, arbre propre, 486 tests, 28 regles. Worktree dedie
  `s13-nadia-voie-rendu`, hors de l'arbre du depot.
- **Ce document ne valide aucun increment.** C'est un arbitrage prealable : il dit ce que je
  refuserai et ce que j'accepterai quand le lot arrivera. Les deux verdicts de Done qu'il entraine
  sur des jeux deja livres sont ecrits dans leurs fiches respectives, pas ici.

## 1. Mode : j'ai joue les voies, je ne les ai pas lues

Ce n'est pas un audit degrade, et c'est le point qui donne du poids a ce qui suit. Pour chacun des
quatre etats compares, j'ai reconstruit le jar depuis mon worktree, relance l'application, et
**joue des parties reelles au navigateur** a 360 x 640 : le catalogue, le demarrage, la lecture des
trois propositions, la reponse, la sortie, puis la manche suivante - un tirage complet a chaque
fois, jusqu'a avoir vu la totalite des items de chaque paquet.

- **Etat actuel** : 24 items sur 24 vus sur "Les mesures", 22 sur 22 sur "Le perimetre".
- **Voie 4** : les **dix jeux du catalogue** joues, 24/24 et 21/22 items sur les deux paquets longs.
- **Voie 3** : quatre jeux joues, dont les deux paquets longs et deux paquets courts.
- **Voie 2 et voie 1** : **je ne les ai pas jouees**, et je le signale plutot que de le taire. Elles
  ne franchissent pas mes planchers de lisibilite : les jouer n'aurait pas change ma reponse, mais
  mes chiffres sur ces deux voies sont ceux de `theo-archi`, pas les miens.
- **`cm1-grands-nombres` n'a pas pu etre joue** : `DEC-0031` l'a sorti du catalogue et il n'y a plus
  de chemin pour l'atteindre depuis l'ecran d'accueil. Ce que je dis de lui repose sur mes deux
  audits precedents et sur la mesure de `theo-archi`. **C'est la seule zone degradee de cet
  arbitrage, et je la nomme.**

Convention de mesure : **debord par cote = (largeur du texte rendu - largeur de la colonne) / 2**,
celle de mon verdict du 2026-08-16 et celle de `theo-archi`. Ma sonde retrouve ses chiffres au
dixieme de pixel sur les cas qu'il nomme - `6000000 km` a **16,4 px** par cote, `sa surface
interieure` a **25,3 px** - par un chemin different du sien : lui sur la feuille rendue, moi sur le
jar reellement joue. Nous mesurons la meme chose.

Releve : `releves-s13/releve-voies-de-rendu-360x640.json`. Captures :
`releves-s13/voie-rendu-*.png`.

## 2. Ce que je confirme de l'avis de `theo-archi`, et sur quoi je le corrige

### Confirme, par ma propre mesure

**Les neuf items en production debordent, exactement comme il l'ecrit.** 8 items sur 24 sur "Les
mesures" a 16,4 px par cote, 1 item sur 22 sur "Le perimetre" a 25,3 px. Ses comptes sont justes et
ses causes aussi.

**Voie 4 ferme le defaut sans rien coter aux paquets qui vont bien, et je l'ai verifie jeu par
jeu.** Sur les huit jeux du catalogue dont le contenu est court, **rien ne bouge du tout** : la
hauteur du bloc de propositions reste a **74 px** exactement (additions, comparer les nombres,
complements a 10, le bon son, soustractions, problemes additifs, tables de multiplication) et a
**158 px** pour la conjugaison, et les trois colonnes restent trois colonnes cote a cote. Ce n'est
pas une moyenne rassurante : c'est zero, jeu par jeu, item par item.

### Corrige - une nuance qui compte, et elle ne va pas dans le sens de la voie qu'il recommande

Sa moyenne de **+16,8 px** et son "**252 items sur 319 a cout nul**" sont exacts a l'echelle des
douze paquets, mais ils cachent ceci, que ma partie jouee montre et que sa table par paquet ne dit
pas : **sur les deux jeux qui debordent en production, la voie 4 n'est jamais gratuite.** Aucun item
de "Les mesures" ni du "Perimetre" ne conserve ses trois colonnes sur une seule rangee. Pas un.

| jeu | rangees aujourd'hui | rangees avec la voie 4 | bloc aujourd'hui | bloc avec la voie 4 |
|---|---|---|---|---|
| "Les mesures" | 1 rangee de 3, toujours | 3, ou 1+2, ou 2+1 - **jamais 3 de front** | 122 px | 164 a 254 px |
| "Le perimetre" | 1 rangee de 3, toujours | 3, ou 2+1 - **jamais 3 de front** | 122 a 218 px | 164 a 398 px |

Le cout par item n'est donc pas "median 0" pour l'enfant qui joue **ces** deux jeux : il est de
+42 px a +180 px sur chaque manche. La moyenne de 319 items est vraie et elle est trompeuse ici,
comme il le dit lui-meme au point 2 de ses objections. Je le confirme et je le chiffre.

### Corrige - la voie 3 est plus chere qu'il ne la donne sur un point, et moins sur un autre

**Et je commence par corriger ma propre mesure avant de m'appuyer dessus.** J'ai d'abord pose la
voie 3 en changeant la seule direction du conteneur, mesure un bloc constant de 164 px, et j'ai
failli en conclure qu'elle etait deux fois moins chere qu'annonce. **C'etait faux, et la capture
`voie-rendu-voie3-naive-fautive-360x640.png` le montre** : avec une base de flexibilite nulle
appliquee a la hauteur, les trois boites font 44 px, le texte de 32 px sort par le bas et les trois
propositions se recouvrent verticalement. J'avais mesure un ecran illisible en croyant mesurer une
voie. Refaite correctement, la voie 3 donne un bloc de **254 px constant**, soit **+180 px** sur les
jeux de CP - exactement le chiffre de `theo-archi`.

Ce que ma mesure ajoute a la sienne, en revanche, et qui est plus grave que ce qu'il annonce :
**sous la voie 3, sur "Le perimetre", 10 items sur 21 ont une proposition entierement invisible
sous le pli** a 360 x 640, et les 21 en ont une coupee par le pli. Sous la voie 4, c'est **1 item**.
Sur ce critere-la - le seul qui m'interesse vraiment, "l'enfant voit-il ses trois choix" - la voie 4
ne fait pas seulement mieux que la voie 3, elle fait dix fois mieux.

## 3. La voie tranchee : **voie 4**, la colonne recule devant son contenu

### Le motif, et il tient en un tableau

J'ai mis les quatre voies devant la seule question qui decide : **ce que l'enfant a sous les yeux.**

| | voie 1, l'image | voie 2, la police | voie 3, empilees | **voie 4, la colonne recule** |
|---|---|---|---|---|
| le defaut est-il ferme ? | oui | **non** - 1 item a 2,7 px | oui | **oui, 0 sur tous les items joues** |
| touche un plancher pedagogique ? | **oui** - jusqu'a 1,184 rem | **oui** - 2 rem et 1,25 rem | non | **non** - police mesuree a 32 px partout |
| fait payer les jeux qui vont bien ? | non | oui, partout | **oui, +180 px partout** | **non - 8 jeux sur 10 inchanges au pixel** |
| une proposition passe sous le pli ? | non | non | **10 items** ("Le perimetre") | **1 item** |
| le contenu reste-t-il verifiable ? | **non** | oui | oui | **oui** |

**Voie 2, morte sur mes planchers.** Le plancher general de 1,25 rem est un acquis de mon refus de
S2-1 ; les 2 rem de `.proposition` sont un acquis de ma reserve 1 sur ce meme S2-1. Au plancher, il
reste un item a **2,7 px** de debord, au-dessus des **2,01 px** qui ont declenche `DEC-0018` et une
reprise entiere au sprint 8. La voie ne ferme donc pas le defaut **sans** descendre sous le
plancher, et elle ne le ferme **qu'en** descendant dessous. Je ne troque pas deux acquis de refus
contre une correction de mise en page qui echoue de toute facon.

**Voie 3, ecartee et non refusee.** Elle marche. Elle coute +180 px aux sept jeux de CP qui n'ont
jamais eu le moindre defaut, et elle met dix items du "Perimetre" avec un choix hors ecran. Elle
reste ma solution de repli si un fait neuf ferme la voie 4 ; elle n'est pas mon choix.

**Voie 1 : voir la section 4.**

**Voie 4 retenue.** Elle ferme les 29 debordements, elle ne touche aucun plancher, elle est la seule
gratuite pour huit jeux sur dix, elle est celle qui laisse le moins de choix sous le pli, et elle est
la seule dont la correction s'ecrit en regle qui rougit sur l'etat que j'ai refuse.

### Ce que j'accepte de payer, dit franchement

Je n'achete pas cette voie en pretendant qu'elle est gratuite. Elle coute deux choses :

1. **Sur "Les mesures" et "Le perimetre", la forme a trois rouleaux disparait.** Voir la section 5.
2. **Sur un item du "Perimetre" - `Le perimetre d'une figure, c'est...` - la troisieme proposition
   passe entierement sous le pli** a 360 x 640 : le bas du bloc tombe a 779 px pour un ecran de
   640. L'enfant doit defiler pour voir son troisieme choix, et comme les propositions sont
   melangees a chaque manche, **une fois sur trois c'est la bonne reponse qui est en dessous.**
   C'est un fait neuf dans ce depot : `DEC-0025` a admis que le message et l'indice passent sous le
   pli, **jamais un choix**.

Je l'accepte quand meme, et voici pourquoi je ne me raconte pas d'histoire. Sur ce meme item,
aujourd'hui, les trois choix sont a l'ecran et **la bonne reponse n'est pas lisible** : le mot
`longueur` est percute par le mot `nombre` de la colonne voisine, le `r` final de `contour` passe
sous la boite d'a cote, et `interieure` sort de l'ecran. Un enfant qui defile finit par lire ses
trois choix. Un enfant qui a trois mots superposes sous les yeux ne peut rien faire. **Un obstacle
qu'on surmonte n'est pas du meme ordre qu'un texte qu'on ne peut pas lire** - c'est le meme
raisonnement, mot pour mot, que celui par lequel j'ai tranche le debordement contre la coupure le
2026-08-16, et je l'applique dans le meme sens.

### Mes conditions pour le lot qui portera la voie 4

Numerotees, fermees. Ce qui n'y est pas ne bloquera pas le Done et ne pourra pas m'etre oppose comme
l'ayant bloque.

- **VR-1** : a 360 x 640, sur les dix jeux du catalogue, **aucune proposition ne deborde de sa
  boite**, ni sur les cotes ni par le bas. Verification : les dix jeux joues, tous les items de
  chaque paquet vus.
- **VR-2** : **aucun plancher de police n'est touche.** `.proposition` reste a 2 rem, l'exception
  nommee du repli a quatre colonnes reste a 1,875 rem, et aucune troisieme regle de taille
  n'apparait. Verification : police relevee a 32 px sur chaque proposition rendue.
- **VR-3** : **les huit jeux qui ne debordent pas aujourd'hui ne changent pas d'un pixel** - meme
  nombre de colonnes, meme hauteur de bloc. C'est la condition qui distingue la voie 4 de la voie 3,
  et c'est elle qui me la fait accepter. Si le lot livre une voie 4 qui deplace ces huit jeux, il
  aura livre une voie 3 sous un autre nom, et je le refuserai.
- **VR-4** : **aucune proposition entierement invisible sous le pli, sauf sur l'item
  `Le perimetre d'une figure, c'est...`**, seul cas que j'admets et que je nomme, parce que je l'ai
  mesure et pese. Un deuxieme item dans ce cas me revient.
- **VR-5** : le lot **reprend** `leRepliEnDeuxRangeesEstScopeALaQuatriemePropositionSeulement`, dont
  `theo-archi` etablit qu'il devient decoratif. Ce point n'est pas de mon ressort et je ne le juge
  pas ; je le reporte parce qu'un test qui affirme une portee qui n'existe plus est un mensonge
  ecrit dans le depot, et cela, c'est de mon ressort.
- **VR-6** : la regle executable qui accompagne la voie **rougit sur l'etat de `c0c51c5`**. C'est le
  critere de `theo-archi` et je le reprends tel quel. Une regle verte sur l'etat que j'ai refuse ne
  garde rien.

## 4. Ma reponse sur C5 : oui, elle s'applique - et pas pour la raison qu'on attend

`theo-archi` a raison de me la renvoyer plutot que de la lever lui-meme. Voici ma reponse, en trois
temps, parce qu'une reponse d'un seul mot serait malhonnete.

**1. Sur la lettre, C5 ferme la voie 1, sans discussion.** Sa clause 1 dit : *"aucune image, aucun
pictogramme, aucun aplat decoratif a l'interieur d'une boite de proposition, ni derriere son
texte."* Un jeton en image est une image a l'interieur d'une boite de proposition. `theo-archi`
m'oppose la clause 2 (l'image ne depend ni de l'item, ni de la colonne, ni de la reponse) ; c'est
juste, mais c'est la **clause 1** qui mord en premier et le plus directement.

**2. Sur le motif d'origine, et je le dis meme si cela affaiblit ma position : C5 ne visait pas
ca.** J'ai ecrit ces deux clauses le 2026-08-11 contre un danger precis, que j'avais nomme : *"un
enfant de CP qui a le choix entre dechiffrer `an` et regarder un dessin regarde le dessin"*. Une
image **du nombre lui-meme** n'ouvre pas ce raccourci-la : elle ne montre rien d'autre que ce que le
texte montrait. Si je m'abritais derriere la seule lettre de C5 en laissant croire que son motif
d'origine couvre ce cas, je durcirais une regle apres coup en faisant semblant de ne pas la
deplacer. Je ne le fais pas.

**3. Je maintiens donc C5 sur ce cas, mais sur un motif neuf, date du 2026-08-16, et je l'ecris
comme tel.** Le motif n'est plus le raccourci visuel, c'est ceci :

> **Ce qu'un enfant lit dans une boite de proposition doit rester verifiable par autre chose que
> moi.** Aujourd'hui, 103 tests dans 14 classes jugent la chaine de caracteres que le YAML ecrit, et
> cette chaine est exactement ce que l'enfant voit. Un jeton en image casse ce lien : les 103 tests
> resteraient verts en jugeant une chaine qui n'atteint plus l'oeil de l'enfant, et
> **rien ne prouverait jamais que l'image de `1 000 000` montre `1 000 000`**. `ADR-0036` section 4
> l'a deja ecrit : un garde-fou de contenu redactionnel ne lit pas un binaire.

Une image fausse serait une erreur de fait dans un contenu scolaire - le motif de refus que je
traite comme suffisant a lui seul - et elle serait **verte en CI**. Je ne veux pas etre le seul
garde-fou de l'exactitude de 354 fichiers binaires que je ne peux pas ouvrir et que je ne peux plus
corriger moi-meme, alors que je corrige aujourd'hui un contenu en editant une ligne de YAML.

**Et de toute facon, la voie 1 meurt avant d'arriver a C5.** Le fait decisif est arithmetique et je
l'ai refait a la main plutot que de le croire : pour qu'une image de `cinquante-` (162,2 px a la
police du bouton) tienne dans une colonne de 96 px, il faut la reduire de 96/162,2, ce qui donne des
glyphes de **18,9 px, soit 1,18 rem - sous mon plancher de 1,25 rem**. `theo-archi` annonce 18,95 px
et 1,184 rem ; je retrouve son chiffre. **Une image n'achete pas un pixel de largeur.** Elle donne
exactement ce que donne la voie 2, avec 354 fichiers en plus, un lecteur d'ecran a nourrir par un
`alt` qu'aucun test ne verifie, et un paquet (`cp-additions-jusqu-a-10`) dont les propositions
n'existent dans aucun fichier et pour lequel aucune image ne peut etre fabriquee d'avance.

**Reponse a l'utilisatrice, en une phrase** : oui, des images peuvent techniquement entrer dans les
sources - il y en a deja -, mais transformer le nombre en image **ne reglerait pas le rendu**, parce
que le probleme n'est pas la nature du nombre, c'est la largeur de la colonne. **C5 est maintenue,
et la voie 1 est fermee.**

## 5. Ma reponse sur le nombre de colonnes qui varie : **c'est acceptable, et voici ma limite**

C'est la question ou `theo-archi` me dit lui-meme que je peux refuser sa voie sur ce seul motif. Je
ne la refuse pas, et je ne le fais pas par facilite : j'ai regarde les ecrans avant de repondre.

**Ce que j'ai vu.** Deux manches consecutives des "Mesures" sous la voie 4
(`voie-rendu-voie4-mesures-*.png`) : l'une montre deux boites en haut et une large en bas, l'autre
une large en haut et deux en bas. Les deux sont **parfaitement lisibles**, chaque nombre entier dans
sa boite, le cabinet ferme sur ses quatre cotes. La machine ressemble toujours a une machine.

**Pourquoi cela ne me fait pas refuser.**

- Un enfant de CM1 ne compte pas les rouleaux : il cherche sa reponse. La metaphore du meuble sert a
  rendre l'ecran familier, pas a enseigner quoi que ce soit. J'ai ecrit des le depart que je voulais
  cette metaphore **diminuee plutot que fidele** ; une machine dont les tiroirs n'ont pas tous la
  meme taille reste une machine.
- **La variation ne porte aucune information sur la reponse, et je l'ai verifie plutot que de le
  supposer.** C'etait la seule chose qui aurait pu me faire refuser : si la boite la plus large
  designait la bonne reponse plus souvent que le hasard, la voie 4 fabriquerait un indice que
  personne n'a valide, et je l'aurais refusee sur ce motif seul. Mesure sur **120 manches reellement
  jouees** des "Mesures" en pressant toujours la boite la plus large : **26,7 % de reussite**, sous
  le hasard. Sur "Le perimetre", 37,5 %. **Presser la plus grande boite ne fait pas gagner.**
- La forme a trois colonnes de front reste la forme **de huit jeux sur dix**, inchangee au pixel.

**Ma limite, et elle est une condition du lot** : la variation est acceptable tant qu'elle reste un
effet de la longueur du texte. **Le jour ou un habillage compte les colonnes** - un rouleau dessine
par colonne, une animation qui suppose trois boites, un decor cale sur une grille de trois - la
question se rouvre et elle me revient. `ines-design` doit le savoir avant de dessiner, et non
apres : **aucune proposition de forme ne peut desormais presupposer trois colonnes.** C'est la
condition **VR-7**.

## 6. Ma reponse sur les neuf items qui debordent en production

`theo-archi` me les remonte comme un fait et me laisse le jugement. J'ai joue les deux jeux, vu les
neuf items, et je ne les traite pas de la meme facon - parce qu'ils ne sont pas de la meme gravite,
et parce que mon rapport a chacun des deux n'est pas le meme.

### "Le perimetre" - **REFUSE**, nouveau verdict date, un item, un motif

Sur `Le perimetre d'une figure, c'est...`, **c'est la bonne reponse qui est illisible** : `la
longueur de son contour` deborde de 20,3 px par cote, le mot `longueur` est percute par le mot
`nombre` de la colonne de gauche, et `interieure` de la colonne de droite sort de l'ecran par la
droite. Capture : `voie-rendu-v0-perimetre-collision-360.png`.

C'est exactement le motif sur lequel j'ai refuse `cm1-grands-nombres` deux fois : **un enfant lit a
l'ecran autre chose que ce qui est ecrit, et une erreur de fait dans un contenu scolaire est un
motif de refus a elle seule, meme quand la cause est un rendu.** Je ne peux pas tenir ce motif sur
un jeu et l'oublier sur un autre parce que celui-la porte deja mon Done. **Le verdict est ecrit dans
`FICHE-bandit-manchot-perimetre-cm1.md`, section du 2026-08-16.**

**Ce n'est pas un deplacement de poteau, c'est la correction d'un constat manque.** Le 2026-08-14
j'ai joue ce jeu a 360 x 640 et j'ai ecrit trois reserves - dont une sur le pli. **Je n'avais pas
mesure la largeur des colonnes**, exactement comme je l'avais deja manque sur `cm1-grands-nombres`
le 2026-08-15 et confesse le lendemain. C'est la **deuxieme fois** que ce meme angle mort me
echappe. Je l'ecris ici pour que ce soit lisible : **tant qu'aucun automate ne mesure la largeur
d'un texte rendu, mon audit doit mesurer la largeur autant que la hauteur**, et j'ai omis de le
faire deux fois.

**Ce que je ne demande pas** : je ne demande pas le retrait du jeu du catalogue. Le perimetre du
retrait appartient a `camille-po`, pas a moi. Mon avis, non contraignant : 21 items sur 22 sont bons
et le defaut se ferme avec les deux lignes de CSS deja arbitrees ici ; retirer le jeu couterait plus
a l'enfant que de le laisser une iteration de plus. `camille-po` tranche.

### "Les mesures" - **VALIDE SOUS RESERVE maintenu**, deux reserves neuves et datees

Les huit items qui debordent portent tous le meme jeton : un distracteur a sept chiffres
(`4000000 tonnes`, `6000000 km`), dont le chiffre de tete tombe en sombre sur le fond sombre du
cabinet et le dernier chiffre passe sur le montant du meuble
(`voie-rendu-v0-mesures-6000km-360.png`). **La bonne reponse, elle, est lisible sur les 24 items.**

C'est ce qui fait la difference avec "Le perimetre", et j'applique la meme frontiere que le
2026-08-16 : **le debordement qui detruit la bonne reponse est un refus ; celui qui abime un
distracteur est une reserve.** Un distracteur illisible n'est plus un distracteur - il transforme
l'item en question a deux choix -, mais l'enfant ne lit pas un nombre faux a la place du bon.
**Reserve, pas refus.** Elle tombe avec la voie 4.

Je note au passage que ces huit distracteurs sont ceux-la memes que j'avais deja mis en reserve le
2026-08-14 comme invraisemblables (*"6 000 000 kg ne teste rien"*). Le rendu ne fait qu'ajouter une
raison de les corriger a celle que j'avais deja donnee.

### Et un fait que je n'y cherchais pas, sur "Les mesures" - **je corrige mon propre constat**

En jouant les 120 manches destinees a verifier que la largeur des boites ne trahit pas la reponse,
j'ai trouve autre chose, et c'est plus grave que le rendu.

**Un enfant qui presse toujours la proposition la plus courte gagne 47,5 % des manches** (57 sur
120 reellement jouees), contre 33,3 % au hasard. Verifie ensuite sur le contenu, hors de tout
rendu : **la bonne reponse est la plus courte des trois chaines sur 13 items sur 24**, la plus
longue sur 7, intermediaire sur 4. Comme les trois propositions d'un item ne different que par leurs
zeros, "la plus courte" veut dire "le plus petit nombre" : **la regle gagnante est "presse le plus
petit", et elle s'apprend en trois manches sans convertir quoi que ce soit.** Aucun autre paquet du
catalogue ne depasse 25 % sur ce critere ; "Les mesures" est seul a 54 %.

**Et le constat que je dois corriger est le mien.** Le 2026-08-14, dans ce meme bloc de validation,
j'ai mesure exactement cette distribution - *"la bonne reponse est la plus petite dans 54,2 % des
items, la plus grande dans 29,2 %, intermediaire dans 16,7 %"* - et j'ai ecrit : **"cet equilibre-la
est un acquis a preserver"**. Mes chiffres d'aujourd'hui reproduisent les siens au dixieme. Ce que
j'avais credite comme un equilibre est un raccourci, et je ne l'avais pas vu parce que j'avais
regarde la repartition comme une statistique au lieu de la jouer comme une strategie.

**Ce que j'en fais : une reserve neuve et datee, pas un refus.** J'ai regarde ce nombre le
2026-08-14, je l'ai approuve, et l'equipe n'a rien livre depuis. Transformer ma propre approbation
en refus dix jours plus tard, sur le meme chiffre inchange, serait deplacer mon poteau apres coup -
et une porte qui fonctionne ainsi est un caprice. **Je corrige mon constat, j'ecris la reserve, je
laisse l'ancien texte lisible, et je ne rouvre pas le Done sur ce motif.** Detail dans la fiche.

## 7. Ce dont je reste la seule garde apres cet arbitrage

Je les nomme pour que personne ne croie couvert ce qui ne l'est pas - et parce que la voie 4
**agrandit** cette liste au lieu de la reduire.

1. **Qu'un texte tienne dans sa boite a 360 px.** Inchange, et c'est le fait central : le correctif
   qui ferme les 29 debordements passe une CI verte, et l'etat qui les porte aussi. La regle VR-6
   gardera **la cause** (une base de colonne fixee d'avance), jamais **l'effet** (un texte lisible).
   Tant qu'aucun automate ne mesure une largeur rendue, c'est moi, en jouant, a 360 px.
2. **Que le nombre de colonnes reste un effet du texte et jamais un signal** (VR-7). Nouveau, et
   apporte par la voie que je retiens. Rien ne le mesure.
3. **Qu'aucune proposition ne passe sous le pli, hors le seul item que j'ai nomme** (VR-4). Nouveau.
   `DEC-0025` ne parle que du message et de l'indice ; un choix sous le pli n'a aucun garde-fou.
4. **Que la largeur d'une boite ne designe pas la bonne reponse.** Mesure a 26,7 % aujourd'hui, donc
   tenue - mais tenue par le contenu, pas par une regle. Un paquet ou les bonnes reponses seraient
   systematiquement les plus longues la romprait sans qu'une ligne rougisse.
5. **Qu'aucune propriete de forme d'une proposition - sa longueur, sa largeur, son rang - ne fasse
   gagner mieux que le hasard.** Le rang est garde depuis `lea-dev` ; **la longueur ne l'est pas**,
   et "Les mesures" prouve aujourd'hui que ce n'est pas theorique.

## 8. Ce que `camille-po` doit retenir

- **La voie est tranchee : voie 4.** Les voies 1 et 2 sont fermees et n'ont pas a etre rouvertes ;
  la voie 3 reste le repli si un fait neuf ferme la voie 4.
- **Une seule story de rendu** ferme les 29 debordements des trois paquets a la fois, sous mes
  conditions VR-1 a VR-7. `theo-archi` traite la cause ; ma part est ecrite ici.
- **`cm1-grands-nombres`** : le motif de mon refus du 2026-08-16 tombe le jour ou cette story est
  livree, **sans toucher au YAML**. Je l'avais ecrit d'avance et je le confirme.
- **"Le perimetre"** : Done rouvert, **REFUSE** au 2026-08-16, un item, un motif, leve par la meme
  story. Le retrait ou non du catalogue est **ta decision, pas la mienne**.
- **"Les mesures"** : Done maintenu ouvert. **Deux reserves neuves** : le rendu des huit
  distracteurs a sept chiffres (levee par la meme story), et **le raccourci de la reponse la plus
  courte, 13 items sur 24, qui demande une passe de contenu** et que je te signale comme la plus
  utile des deux.
- **Aucune ligne de matrice ne bouge de mon fait aujourd'hui**, dans aucun sens.
