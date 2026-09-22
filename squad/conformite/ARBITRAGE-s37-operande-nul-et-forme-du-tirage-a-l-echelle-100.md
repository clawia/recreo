# Arbitrage du 2026-09-21 - l'opérande nul et la forme du tirage à l'échelle 100 (CE1)

- **Auteur** : `nadia-prof`. **Date** : 2026-09-21. **Sprint** : s37.
- **Ce qui m'est posé** : section 5 du planning de s37
  (`squad/sprints/s37-premier-paquet-mathematiques-ce1-et-ordre-des-niveaux/planning.md`), qui
  m'affecte nominativement les deux réponses pédagogiques bloquant `S37-1` - la cible d'opérande nul
  à `sommeMaximale = 100`, et la nécessité ou non d'une table de répartition équivalente à celle de
  `DEC-0007` point 2.
- **Ce document ne prononce aucun Done**, n'écrit ni backlog ni garde-fou, ne crée aucune ligne de
  matrice et ne choisit aucun mécanisme technique.
- **Ce que j'ai joué, et ce que je n'ai pas pu jouer.** Aucun paquet de CE1 n'existe : il n'y a pas
  d'application à lancer sur ce contenu. J'ai fait ce qui en tient lieu et je le dis plutôt que de
  le laisser croire - j'ai **fait tirer le générateur de production** et j'ai **lu quarante manches
  consécutives comme un enfant les verrait**, puis j'ai compté ce que ces manches contiennent.
  Méthode en section 1.

---

## 1. Ma propre mesure, et sa méthode à côté de son chiffre

**Méthode.** Classe de production réelle
`domain/src/main/java/com/recreo/domain/content/source/AdditionsBorneesContentSource.java`, jamais
une réimplémentation ; 200 000 tirages par graine, `dejaVus` vide à chaque tirage ; périmètre de
build `-pl domain -am` ; `bash outils/mvn-jdk21.sh`, JDK 21.0.10 rapporté par la machine virtuelle
des tests. La classe de mesure a été écrite, exécutée, puis supprimée - `git status --porcelain`
vide après coup, aucune mesure jetable n'est commitée. C'est le protocole de `DEC-0007` et celui de
l'avis de faisabilité de `theo-archi`, repris sans changement pour que les deux séries soient
comparables.

**Je retrouve ses chiffres, et je le dis avant de m'en écarter sur un point.** Opérande nul 1,34 %
(il annonce 1,32 %), somme > 80 36,13 % (35,97 %), retenue aux unités 42,07 % (42,23 %), forme
"un chiffre plus un chiffre" 1,71 % (1,76 %), somme moyenne 67,15 (67,1), somme <= 10 1,02 %
(1,04 %). Les écarts sont ceux de deux graines différentes. **Son avis est exact sur tout ce qui
fonde sa conclusion**, et je m'appuie dessus.

**Ce que j'ai mesuré en plus, et qui décide les deux questions.** La proportion de tirages à
retenue - 42 % - ne dit pas ce qu'un enfant rencontre, parce qu'elle mélange `36 + 28` et `58 + 4`,
qui ne demandent pas le même travail. J'ai donc classé chaque tirage par **forme**, sous une
partition à huit cases dont l'ordre de priorité est fixé et reproductible (section 4) :

| Forme | `sommeMaximale = 10` | `sommeMaximale = 100` |
|---|---|---|
| Opérande nul | 12,93 % | **1,34 %** |
| Somme <= 20, sans opérande nul | 87,07 % | **3,78 %** |
| Double (`a = b`) | 0 % | **0,82 %** |
| Complément à la dizaine | 0 % | **9,27 %** |
| Ajout d'un nombre rond | 0 % | **16,60 %** |
| Deux chiffres plus un chiffre | 0 % | **23,05 %** |
| Deux nombres à deux chiffres, sans retenue | 0 % | **25,52 %** |
| Deux nombres à deux chiffres, avec retenue | 0 % | **19,63 %** |

Et, parce qu'un enfant ne joue pas 200 000 manches mais dix, sur 100 000 parties simulées de dix
manches à `sommeMaximale = 100` :

- **59,08 %** des parties ne contiennent **aucune** manche d'entrée (ni somme <= 20, ni opérande
  nul) ;
- **34,82 %** des parties ne contiennent **ni un complément à la dizaine, ni un double** ;
- 0,64 % des parties contiennent six manches ou plus de la forme la plus dure. **Ce chiffre-là est
  rassurant, et je l'écris parce qu'il contredit la crainte que la mesure de 42 % pouvait
  inspirer** : le tirage actuel ne sert pas un régime de manches dures. Son défaut est ailleurs.

## 2. Une correction préalable, sur l'énoncé même de la question qui m'est posée

**La question m'a été transmise en décrivant la compétence visée comme *"additions posées avec
retenue, pas calcul mental de bas niveau"*. C'est faux, et l'inverse de mon arbitrage en dépend.**

`S37-1` déclare `MAT-CE1-03`, et cette ligne, que j'ai écrite le 2026-09-21, dit :
*"Calcul mental - additions et soustractions dans la limite de 100 (compléments à la dizaine,
doubles et moitiés)"*. L'addition posée avec retenue est la ligne **suivante**, `MAT-CE1-04`
(*"Poser et effectuer une addition avec retenue"*), qui n'est pas ouverte et qu'aucune story ne
sert.

Ce n'est pas une nuance de vocabulaire, c'est le partage entre deux lignes du référentiel :

- si la compétence était `MAT-CE1-04`, un régime de `36 + 28` serait exactement ce qu'il faut, et
  je n'aurais aucune table à demander ;
- comme la compétence est `MAT-CE1-03`, **la parenthèse de l'intitulé n'est pas de la décoration :
  elle nomme les stratégies**. Compléments à la dizaine, doubles, moitiés. Un paquet qui produit un
  double une fois sur 122 tirages ne travaille pas cette ligne-là ; il travaille `MAT-CE1-04`
  mentalement, c'est-à-dire une compétence voisine **plus dure**, sous le nom d'une compétence
  qu'il ne sert qu'à la marge.

Je prends cette erreur d'énoncé au sérieux parce qu'elle est la forme exacte du défaut que ma porte
existe pour attraper - un increment qui annonce une ligne et en travaille une autre - et parce
qu'elle serait arrivée intacte jusqu'au développeur si je m'étais contentée de répondre aux deux
questions telles qu'elles étaient posées.

## 3. Première décision - l'opérande nul au CE1 : un plafond, et aucun plancher

> **`ADD-CE1-1`** - dans un paquet d'additions de CE1, la proportion de tirages comportant au moins
> un opérande nul est **au plus 5 %**. **Il n'y a pas de plancher : zéro est conforme.**

**Ce n'est pas la cible du CP, et ce n'en est pas non plus une version affaiblie : c'est une cible
de nature inverse.** `DEC-0004` point 5 fixe au CP une **bande** de 5 % à 15 %, précisée à 12,9 %
par son amendement du 2026-08-05 - une bande avec un plancher, parce qu'au CP il faut **garantir**
la présence du zéro. `ADD-CE1-1` est un **plafond seul**, parce qu'au CE1 il faut seulement
**empêcher** qu'il encombre.

**Le motif, et il tient dans la compétence.** Au CP, `3 + 0` est un item du répertoire additif que
`MAT-CP-03` construit : savoir qu'ajouter zéro ne change rien est une propriété du nombre que
l'enfant est en train d'acquérir. C'est ce que j'ai écrit en s01 et que `DEC-0004` cite - *"la
propriété du zéro fait partie du programme de calcul **de CP**"*. **J'avais déjà borné cette phrase
à un niveau le jour où je l'ai écrite**, je l'ai relu avant de m'en servir, et c'est elle qui
interdit de la transposer.

Au CE1, cette propriété est un acquis, pas un objectif. Ni l'intitulé de `MAT-CE1-03` ni sa
parenthèse ne nomment le zéro. Un tirage `0 + 73` ne demande **aucune** stratégie de calcul mental :
il consomme une manche sur dix et ne rend rien sur la ligne déclarée. Lui imposer un plancher de
5 %, ce serait dépenser une manche sur vingt pour réviser du CP dans un paquet de CE1.

**Donc, très explicitement : oui, il est pédagogiquement acceptable qu'un enfant de CE1 ne
rencontre presque jamais d'opérande nul dans ce paquet, et il est acceptable qu'il n'en rencontre
jamais.** Les 1,34 % mesurés sont conformes ; 0 % le serait aussi.

**Et ce que ce plafond refuse, parce qu'une cible qui ne refuse rien n'est pas une cible.** Il
refuse toute réponse qui consisterait à *ramener* le zéro dans le tirage pour retrouver une
proportion de la bande CP. C'est la réponse la moins chère au constat de `theo-archi` - baisser
`ACCEPTATION_OPERANDE_NUL_SUR`, ou changer de mécanisme pour atteindre les 5 % - et c'est
précisément celle que j'interdis. Le plafond de 5 % est le plancher du CP retourné : cette
inversion est le fond de ma réponse, et elle est vérifiable d'un coup d'oeil.

**Ce que `ADD-CE1-1` ne fait pas.** Il n'amende pas `DEC-0004` point 5, qui reste inchangé, bande
et cible comprises, pour le CP. Il ne vaut ni pour le CM1 ni pour le CM2. Il ne dit rien des
soustractions de `MAT-CE1-03`, hors périmètre de `S37-1`.

## 4. Seconde décision - oui, une table est nécessaire, mais pas sur les sommes

### 4.1 Pourquoi une table de bandes de sommes serait la mauvaise table

**Je réponds d'abord à la question telle qu'elle est posée : non, la dissymétrie des sommes n'est
pas, en elle-même, le problème, et je ne demande pas de table de sommes.** Je l'écris nettement
pour que personne n'en écrive une en croyant que je l'ai demandée.

`DEC-0007` point 2 fixait une table de sommes à l'échelle 10 et elle avait raison de le faire,
**pour une raison qui ne survit pas au changement d'échelle** : sur 0-10, la somme **est** la
difficulté. Additionner pour arriver à 9 ou 10 est le franchissement que le CP travaille ;
additionner pour arriver à 3 ne l'est pas. La table des sommes de `DEC-0007` était donc une table
de difficulté déguisée, et c'est pour cela qu'elle a marché.

À l'échelle 100, les deux variables se décrochent complètement :

- `40 + 50 = 90` est immédiat, et tombe dans la bande 90-99, la plus haute ;
- `27 + 15 = 42` est l'une des formes les plus coûteuses, et tombe dans la bande 40-49, au milieu.

Une table qui redescendrait le tirage le long de l'échelle des sommes rendrait le paquet plus
facile **sans aucun contrôle sur ce qui le rend facile**, et pourrait le rendre plus dur ailleurs.
Elle contraindrait la mauvaise variable. Les 36,13 % de sommes au-dessus de 80 ne me gênent donc
pas : à ce niveau l'enfant lit et écrit les nombres jusqu'à 1000 (`MAT-CE1-01`), et un résultat à
deux chiffres élevés n'est pas plus difficile à produire ni à lire qu'un résultat moyen.

### 4.2 Ce que le motif de `DEC-0007` point 2 devient, lui, à l'échelle 100

Le **motif** de `DEC-0007` point 2, en revanche, se transpose mot pour mot : *"éviter qu'un enfant
ne voie que des additions faciles ou que des additions avec retenue"*. À l'échelle 100 il se lit sur
les formes, et trois mesures de la section 1 montrent que le tirage actuel y échoue :

1. **Le double est absent** - 0,82 %, soit un tirage sur 122. Le double est **nommé dans l'intitulé
   de la ligne servie**. Un enfant qui joue dix manches a moins d'une chance sur douze d'en voir un.
2. **Une partie sur trois (34,82 %) ne touche ni un complément à la dizaine ni un double**,
   c'est-à-dire ne touche aucune des deux stratégies que `MAT-CE1-03` nomme. Le complément à la
   dizaine est par ailleurs le prolongement direct de `MAT-CP-05` (*"Compléments à 10"*) : il est
   nommé deux fois dans les référentiels, à deux niveaux, ce qui dit assez son poids.
3. **Six parties sur dix (59,08 %) n'offrent aucune manche d'entrée.** Un enfant de CE1 qui ouvre
   le jeu en septembre tombe une fois sur deux sur dix additions à deux chiffres d'affilée, sans un
   seul appui pour se mettre en route.

Ces trois faits ne sont pas une question de goût : ils disent que le paquet, tel qu'il sortirait du
générateur inchangé, **sert une autre ligne que celle qu'il déclare**. C'est le motif que la
section 2 a établi.

### 4.3 La table

> **`ADD-CE1-2`** - dans un paquet d'additions de CE1 à `sommeMaximale = 100`, la répartition des
> tirages par forme tient les bandes ci-dessous.

**La partition, et son ordre de priorité.** Un couple `(a, b)` appartient à la **première** case
dont il remplit la condition, jamais à deux. Cet ordre fait partie de la clause : sans lui,
`25 + 25` serait à la fois un double et une somme ronde, et deux personnes compteraient deux choses
différentes. C'est l'ordre sous lequel j'ai mesuré la section 1.

| # | Forme | Condition sur `(a, b)` | Mesure actuelle | **Cible** |
|---|---|---|---|---|
| 1 | Opérande nul | `a = 0` ou `b = 0` | 1,34 % | **0 % à 5 %** (`ADD-CE1-1`) |
| 2 | Somme <= 20 | `a + b <= 20` | 3,78 % | **10 % à 20 %** |
| 3 | Double | `a = b` | 0,82 % | **5 % à 15 %** |
| 4 | Complément à la dizaine | `a + b` multiple de 10, `a` et `b` non multiples de 10 | 9,27 % | **10 % à 20 %** |
| 5 | Ajout d'un nombre rond | `a` ou `b` multiple non nul de 10 | 16,60 % | **10 % à 20 %** |
| 6 | Deux chiffres plus un chiffre | `a < 10` ou `b < 10` | 23,05 % | **15 % à 25 %** |
| 7 | Deux à deux chiffres, sans retenue | `(a mod 10) + (b mod 10) < 10` | 25,52 % | **10 % à 25 %** |
| 8 | Deux à deux chiffres, avec retenue | `(a mod 10) + (b mod 10) >= 10` | 19,63 % | **10 % à 20 %** |

**Bornes incluses.** Mesure sur 200 000 tirages à graine fixée pour un paquet génératif, protocole
de `DEC-0007` ; **sur la liste des items elle-même** pour un paquet statique, où la proportion est
exacte et non estimée. Aucune tolérance supplémentaire n'est accordée au-delà des bandes : elles
sont déjà larges de dix à quinze points, ce qui est la tolérance.

**Les bandes sont satisfaisables, je l'ai vérifié plutôt que supposé** : la somme des bornes basses
vaut 70 et la somme des bornes hautes vaut 150. Une répartition qui totalise 100 existe donc à
l'intérieur des huit bandes, et il y en a beaucoup.

**Ce que chaque ligne coûte et pourquoi elle vaut son prix**, en manches sur une partie de dix :

- **Ligne 2, somme <= 20, plancher à 10 %** : une à deux manches d'entrée par partie. Le plafond à
  20 % compte autant que le plancher, et pour la raison inverse : au-delà, le paquet redevient un
  paquet de CP réétiqueté CE1, `MAT-CP-03` allant explicitement jusqu'à 20. C'est la compétence
  voisine plus facile, et je la borne des deux côtés.
- **Ligne 3, double, plancher à 5 %** : au moins une manche sur deux parties, en moyenne une par
  partie à 10 %. Le double est nommé par la ligne servie ; il passe de quasi absent à présent.
- **Ligne 4, complément à la dizaine, plancher à 10 %** : une à deux manches par partie sur la
  stratégie que le référentiel nomme deux fois.
- **Ligne 8, plafond à 20 %** : la forme la plus coûteuse reste présente - le calcul mental de
  `36 + 28` est un travail légitime de CE1 - mais elle ne prend pas le paquet. Au-delà de 20 %, le
  paquet bascule sur `MAT-CE1-04`, joué mentalement, donc plus dur que la ligne posée ne le demande.
- **Lignes 5, 6, 7** : les bandes encadrent la mesure actuelle sans la bouleverser. Elles ne sont
  pas là pour corriger un défaut, elles sont là pour que le budget libéré par les lignes 2, 3 et 4
  soit pris quelque part de façon décidée, et pas au hasard du mécanisme.

### 4.4 Ce que cette table **ne** garantit pas, et que je m'interdis d'exiger plus tard

Une répartition conforme sur 200 000 tirages **n'empêche pas** qu'une partie de dix manches tombe
sans aucune manche d'entrée : au plancher strict, 10 % sur la ligne 2 et 1,3 % sur la ligne 1, cela
arrive encore environ **une fois sur trois** (`0,887` puissance dix, soit 30 %). La table fait donc
passer ce cas de 59 % à 30 % au pire, pas à zéro. Un tirage manche par manche qui garantirait un
appui dans chaque partie serait une propriété plus forte, et je ne la demande pas.

**Je l'écris comme un engagement, pas comme une réserve dormante : je ne refuserai pas `S37-1` au
motif qu'une partie donnée ne contient pas de manche facile, dès lors que `ADD-CE1-2` est tenue.**
Une barre qu'on relève après qu'une équipe l'a atteinte fait de ma porte un caprice. Si je vois
plus loin après avoir joué le paquet livré, j'ouvrirai une réserve neuve et datée ; je ne durcirai
pas `ADD-CE1-2`.

## 5. Une correction que je dois à `theo-archi`, sur un de ses chiffres

Son avis de faisabilité écrit, parmi ses trois points opérationnels : *"un distracteur peut dépasser
100 (1,32 % des cas, moins fréquent qu'au CP où c'est déjà servi et validé)"*.

**J'ai mesuré 2,96 %, soit plus du double**, et `1,32 %` est **exactement** le chiffre de son
opérande nul dans la même page. C'est une recopie, pas une mesure. Le calcul le confirme : un
distracteur dépasse 100 quand la somme vaut 98, 99 ou 100 et que le rang tiré place assez de
voisins au-dessus, ce qui donne environ 2,9 % sur la combinatoire - pas 1,3 %.

**Pédagogiquement, cela ne change rien et je ne le retiens pas contre le paquet** : une proposition
à 101 ou 102 reste dans l'univers numérique du CE1, qui va jusqu'à 1000 (`MAT-CE1-01`), et le CP
sert déjà 26,20 % de manches dont un distracteur dépasse `sommeMaximale`, ce que j'ai validé. Sa
conclusion - *"moins fréquent qu'au CP"* - reste vraie. Je le signale parce qu'un chiffre recopié
d'un autre chiffre dans un avis de faisabilité est de la famille de défauts que ce dépôt sanctionne,
et parce que le prochain pourrait porter sur autre chose que sur un point mineur.

## 6. Ce dont je reste la seule garde, et qu'aucun automate ne verra

`ADD-CE1-1` et `ADD-CE1-2` sont l'une et l'autre **entièrement décidables sur le couple `(a, b)`**,
donc entièrement automatisables. **Tant qu'aucun garde-fou ne les exécute, c'est moi qui les tiens,
en comptant.** Restent en plus dans ma seule main, et je les nomme pour que l'équipe ne croie pas
couvert ce qui ne l'est pas :

1. **Que le paquet livré travaille réellement `MAT-CE1-03` et pas `MAT-CE1-04`.** La table rend ce
   glissement beaucoup plus difficile ; elle ne le rend pas impossible, et aucun automate ne lit une
   déclaration de ligne pour en juger la vérité (`ADR-0081` vérifie qu'un code résout, jamais qu'il
   dit vrai).
2. **La progression à l'intérieur d'une partie.** La table est une distribution ; elle ne dit rien
   de l'ordre des manches. Dix manches conformes servies dans le pire ordre restent dix manches
   conformes.
3. **La lisibilité des quatre propositions à cette échelle.** Un tirage mesuré donne `85` avec
   `84`, `86`, `87` : quatre entiers consécutifs à deux chiffres, à discriminer après avoir calculé.
   La structure est la même qu'au CP (`7` avec `5`, `6`, `8`), qui est servie et validée, **donc je
   ne fais de ce point ni une condition ni une réserve de `S37-1`** - c'est une observation neuve et
   datée du 2026-09-21, que je regarderai en jouant le paquet livré.
4. **L'indice.** Il est unique pour tout le paquet (`DEC-0005`), il ne nomme pas la somme, et il a
   été rédigé pour une addition jusqu'à 10. Rien ne garantit qu'il aide à `36 + 28`. Je le jugerai à
   l'audit.
5. **L'exactitude** de chaque énoncé, de chaque bonne réponse et de chaque distracteur, et la
   plausibilité des distracteurs.
6. **`LON-CE1-1` et `LON-CE1-2`** (arbitrage du 2026-09-21) restent dues par ce paquet s'il porte un
   champ `enonce` distinct de l'expression elle-même.

## 7. Ce que je route, et à qui

| Fait | À qui | Pourquoi ce n'est pas à moi |
|---|---|---|
| `ADD-CE1-1` et `ADD-CE1-2` sont automatisables sur le couple `(a, b)`, avec la partition ordonnée de la section 4.3 comme spécification | `theo-archi`, via `sofia-sm` | Je dis la propriété, la forme du test lui appartient. Sa population est vide aujourd'hui, et `ADR-0051` a une doctrine sur ce cas qui est la sienne |
| Le chiffre de 1,32 % de distracteurs au-dessus de 100 est une recopie ; la mesure est 2,96 % (section 5) | `theo-archi`, via `sofia-sm` | C'est son avis de faisabilité, et une correction s'écrit chez son auteur |
| Choisir entre un mécanisme génératif tenant `ADD-CE1-2` et un paquet statique de type `question` | `theo-archi` | Décision technique, explicitement hors de ma main (planning s37, section 7) |
| Le 6e critère d'acceptation de `S37-1` compare une mesure à une cible qui existe désormais, et une seconde cible s'y ajoute | `camille-po` | Je produis les motifs, elle écrit le backlog |
| La formulation *"additions posées avec retenue"* employée pour décrire `MAT-CE1-03` (section 2) | `camille-po`, via `sofia-sm` | Le référentiel est à moi, le libellé des stories et des affectations ne l'est pas |

## 8. Ce qu'un développeur peut et ne peut pas faire avec cette réponse

**`S37-1` est engageable.** Les deux questions qui la bloquaient sont tranchées. Ce qui suit est la
conséquence directe de `ADD-CE1-1` et de `ADD-CE1-2`, et rien n'y est laissé à l'interprétation.

**Ce qui est fermé, et ne demande plus rien :**

- **Ne pas toucher `ACCEPTATION_OPERANDE_NUL_SUR`.** La ligne 1 de la table est **déjà conforme** à
  1,34 % pour un plafond de 5 %. Recalibrer cette constante ne peut que la faire sortir par le haut.
  Aucune valeur de cette constante n'est en cause dans ce que je refuse.
- **Le défaut latent signalé par `theo-archi` - la constante `private static final` partagée entre
  tous les paquets `additions-bornees` - n'est pas réveillé par `S37-1`**, puisque la cible CE1 est
  tenue sans y toucher. Il reste ce qu'il était : un point à traiter un jour, pas une dette que
  cette story crée.
- **Ne pas écrire cette constante au niveau du paquet.** Mon arbitrage ne le demande pas. Si
  `theo-archi` le fait pour un autre motif, c'est le sien.
- **Ne pas écrire de table de répartition par bandes de sommes.** Section 4.1.

**Ce qui est refusé :**

- **Le chemin à zéro ligne de Java - `sommeMaximale: 100` dans un paquet YAML et rien d'autre - ne
  passe pas ma porte.** Il tient `ADD-CE1-1` et il **échoue `ADD-CE1-2` sur trois lignes sur huit**,
  mesurées : somme <= 20 à 3,78 % pour un plancher de 10 %, double à 0,82 % pour un plancher de 5 %,
  complément à la dizaine à 9,27 % pour un plancher de 10 %. Je l'écris avec ses chiffres pour que
  personne n'ait à me redemander ce que je voulais dire. Le verdict de faisabilité de `theo-archi`
  n'est pas contredit : il concluait *"faisable, chemin le moins cher ouvert, **et il le reste tant
  que la cible CE1 d'opérande nul est celle que le code produit déjà**"*. Elle l'est ; c'est la
  seconde question, celle de la forme du tirage, qui referme ce chemin.

**Ce qui est ouvert, au choix de `theo-archi` :**

- **Un paquet statique de type `question`.** C'est le chemin le moins cher vers la conformité :
  l'auteur place chaque item dans la case qu'il veut, la répartition est exacte et se compte sur la
  liste, sans graine ni protocole de 200 000 tirages. `theo-archi` le préférait déjà si une cible
  hors de portée du filtre était fixée ; c'est le cas.
- **Un mécanisme génératif, neuf ou modifié, qui tient `ADD-CE1-2`.** Autorisé. Quel mécanisme et à
  quel coût n'est pas de mon ressort.

**Ce qu'aucun des deux chemins ne dispense de faire** : passer devant moi. Un paquet conforme aux
deux clauses peut encore être refusé sur l'un des six points de la section 6, et notamment sur le
premier - servir `MAT-CE1-04` sous le nom de `MAT-CE1-03`.

**Et ce qui ne change pas** : quel que soit le chemin retenu, `MAT-CE1-03` ne passera pas à
`couvert-valide` avec ce seul paquet. Les soustractions, les compléments à la dizaine pris pour
eux-mêmes et les moitiés restent hors périmètre - au plus `en cours`, comme le septième critère
d'acceptation de `S37-1` le dit déjà.
