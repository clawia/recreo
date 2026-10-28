# s57, palier 5 : lecture du premier jet de l'horloge `MAT-CE1-11` (`ce1-lire-l-heure`, `lea-dev`)

- **Auteure** : `nadia-prof`, professeure des écoles, seule autorité sur le Done pédagogique.
- **Date** : 2026-10-28, 12:55 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-lecture-horloge`, créée depuis `s57-p5-lea-horloge` à `d242fe5b`. Aucun push, aucun rebase, aucune
  fusion. Ce fichier est le seul que j'écris.
- **Type** : lecture du premier jet avant `verify` (`arbitrage-nadia-palier-5-lp7-d5.md` 5 ; `DEC-0120` A.272). **Aucun Done, aucun
  refus formel, aucun bloc de validation rempli, aucun identifiant de condition ou de réserve neuf** : les exigences et les réserves
  ci-dessous sont numérotées dans ce fichier, sans préfixe. Ni paquet, ni dessin, ni test, ni catalogue, ni matrice, ni registre.
- **Audit dégradé, déclaré** : je n'ai pas joué le jar et je n'ai rien regardé à l'écran. J'ai lu le paquet, les douze dessins, les
  deux tests et le rapport de `lea-dev`. Ce que je dis de la lisibilité est un calcul, pas une lecture d'enfant.
- **Lu** : `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` (toutes les sections) ; `ce1-lire-l-heure.yaml` ; `horloge-a.svg` à
  `horloge-l.svg` ; `PaquetCe1LireLHeureTest.java` ; `LesCadransMontrentCeQueLeursItemsAffirmentTest.java` ;
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` (Javadoc, lignes 40 à 129) ; `arbitrage-nadia-palier-5-lp7-d5.md`
  (2.2, 2.3, 3, 3.5, 4, 5, 8) ; `arbitrage-nadia-palier-4.md` (titres de sections, ligne 161) ;
  `reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md` ; `reponse-nadia-palier-5-rejeu-horloge-et-double-compte.md` ;
  `ARBITRAGE-PREALABLE-s25-palier-1-visuel-mon-ordre-du-15-aout-et-la-mesure-de-pli.md` 2.1 (`HOR-1` à `HOR-8`) ;
  `FICHE-bandit-manchot-calendrier-dates-ce1.md` ; ligne `MAT-CE1-11` de `MATRICE-couverture.md` ; `competences-ce1.md` ligne 52 ;
  `DEC-0120` A.272, A.273, A.280.

## Résumé

| Point | Verdict de lecture |
|---|---|
| Les douze items | **Tous justes** : chaque cadran montre l'heure que l'item affirme (angles vérifiés par moi sur les douze fichiers, section 1.1), une seule lecture par cadran, distracteurs plausibles un par un, textes lisibles pour un CE1 |
| Le paquet entier | **Un défaut à corriger, et il vient de ma propre condition** : une stratégie qui ne regarde pas le cadran gagne 10 items sur 12 (section 1.3, exigence 1) |
| Forme des réponses | **Je retiens la forme chiffrée** (`3 h`, `3 h 30`, `3 h 15`, `2 h 45`). Aucune forme en toutes lettres (section 2.1) |
| Glyphes | relus par `ines-design` avant l'audit à l'écran (exigence 2) |
| Renvoi à une section 3.5 | **la bonne est la 3.5 de `arbitrage-nadia-palier-5-lp7-d5.md`**, et le renvoi fautif est le mien (section 2.2, Q13) |
| Coût | premier passage, compris dans le lot ; une seconde lecture sera due après la correction (section 5) |

## 1. Lecture item par item

### 1.1 Ce que j'ai vérifié moi-même sur les dessins

- **Angles** : relus dans les douze fichiers (`grep` sur les `rotate`) et recalculés à la main contre la bonne réponse, petite aiguille
  30 x heures + 0,5 x minutes, grande 6 x minutes. Douze sur douze exacts `[calcul]`. Petite aiguille longue de 28, épaisse de 7 ;
  grande longue de 42, épaisse de 4.
- **Cadran** : les douze fichiers sont identiques hors des deux lignes d'aiguilles (empreinte unique sur les douze, lignes `rotate`
  retirées). La place des quinze glyphes, recalculée sur trois nombres (1, 3, 11), tombe à la bonne heure du cadran, à un rayon
  d'environ 59 `[calcul]`.
- **Ce que je n'ai pas vérifié** : la forme rendue des glyphes, la lisibilité à 176 px, le pli. Je n'ai lancé aucun test.

### 1.2 Les douze items

Colonnes : **juste** (le cadran montre la bonne réponse, une seule lecture possible) ; **lisible CE1** (énoncé, propositions, indice) ;
**niveau** (la compétence de la ligne, pas une voisine plus facile) ; **distracteurs** (plausibles un par un, au titre de `HOR-5`
transposée) ; **accents** (ce que j'ai vu, sans me substituer au contrôle systématique du `relecteur-pedagogique`, `DEC-0002`).

| Item | Réponse | Distracteurs | Juste | Lisible CE1 | Niveau | Distracteurs | Accents |
|---|---|---|---|---|---|---|---|
| `horloge-a` | 3 h | 12 h 15, 4 h | oui | oui | oui, heure pleine | aiguilles inversées, heure voisine : plausibles | rien à signaler |
| `horloge-b` | 6 h | 12 h 30, 7 h | oui | oui | oui | idem | rien à signaler |
| `horloge-c` | 9 h | 12 h 45, 10 h | oui | oui | oui | idem | rien à signaler |
| `horloge-d` | 3 h 30 | 6 h, 4 h 30 | oui, petite aiguille à mi-chemin de 3 et 4 | oui | oui, demi-heure | grande aiguille lue comme heure ; heure suivante, la petite aiguille étant à mi-chemin : plausibles | `passé` juste |
| `horloge-e` | 8 h 30 | 6 h, 9 h 30 | oui | oui | oui | idem | idem |
| `horloge-f` | 12 h 30 | 6 h, 1 h 30 | oui | oui | oui | idem | idem |
| `horloge-g` | 12 h 15 | 3 h, 1 h 15 | oui | oui | oui, quart | grande aiguille lue comme heure : plausible ; `1 h 15` : la petite aiguille est à un quart du chemin vers 1, distracteur faible mais non absurde | idem |
| `horloge-h` | 4 h 15 | 3 h, 5 h 15 | oui | oui | oui | idem | idem |
| `horloge-i` | 10 h 15 | 3 h, 11 h 15 | oui | oui | oui | idem | idem |
| `horloge-j` | 2 h 45 | 9 h, 3 h 45 | oui, petite aiguille aux trois quarts du chemin vers 3 | oui | oui, quart (*moins le quart*) | `3 h 45` est l'erreur classique de cette heure : le meilleur distracteur du paquet | idem |
| `horloge-k` | 9 h 45 | 9 h, 10 h 45 | oui | oui | oui | idem | idem |
| `horloge-l` | 11 h 45 | 9 h, 12 h 45 | oui | oui | oui | idem | idem |

**Un seul sens de lecture, sur chaque cadran.** Les angles sont exacts et la grande aiguille est sur 12, 3, 6 ou 9 : aucun cadran ne se
lit de deux façons à la minute près. Le seul double sens possible, `3 h` et `15 h`, est écarté des propositions par la garde M
(`lecturesDe`). Douze cadrans distincts.

**Textes, communs aux douze.**

- *"Quelle heure est-il ?"* : trois mots, une question, vocabulaire de CE1. **Lisible seul.**
- Texte alternatif, *"Une horloge ronde avec douze nombres et deux aiguilles, une courte et une longue."* : il dit quoi regarder et ne
  livre ni l'heure ni la place d'une aiguille. **`CON-5` tenue sur les douze**, vérifiée par moi.
- Indice des heures pleines, *"La petite aiguille montre l'heure."* (5 mots) ; indice des neuf autres, *"La petite aiguille a passé quel
  nombre ?"* (7 mots). Les deux disent quelle aiguille regarder, aucun ne dit l'heure. Pour les quarts *moins le quart*, la réponse à
  l'indice est bien le nombre de l'heure écrite (2 pour `2 h 45`) : l'indice mène à la bonne réponse sans la donner. **`HOR-7` tenue.**
  *"Petite aiguille"* est le mot de la classe ; le texte alternatif dit *courte* : les deux se comprennent, je ne demande rien.

### 1.3 Le défaut du paquet entier : une stratégie sans le cadran gagne 10 items sur 12

**Le constat** `[calcul]`. Dans chacun des douze items, deux propositions ont la même forme de minutes et diffèrent d'une heure (`3 h` et
`4 h`, `3 h 30` et `4 h 30`, `2 h 45` et `3 h 45`), la troisième a une autre forme. La proposition d'une autre forme est toujours fausse
(12 sur 12). Dans la paire, la bonne réponse est l'heure la plus petite sur 10 items : seuls `horloge-f` (`12 h 30` contre `1 h 30`) et
`horloge-g` (`12 h 15` contre `1 h 15`) font exception. **Un enfant qui écarte l'intrus puis prend la plus petite heure de la paire gagne
10 manches sur 12, soit 83,3 %, sans regarder l'horloge.**

**Pourquoi aucun test ne le voit.** `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` lit les trois propositions comme des
durées depuis 0 h : à ce compte, la bonne réponse est la plus petite sur 5 items, l'intermédiaire sur 5, la plus grande sur 2 `[calcul]`,
et le garde reste vert. La stratégie de l'enfant n'est pas un rang de valeur sur les trois propositions : c'est une comparaison
dans la paire. C'est une propriété dont je suis la seule garde (section 6).

**Ce n'est pas une barre relevée.** Le principe est ancien et écrit : *"une stratégie qui ignore le contenu ne doit jamais être
majoritaire"* (Javadoc du garde de rang, seuil de majorité). Je l'applique à une stratégie que le garde ne mesure pas.

**La cause est ma condition, pas le travail de `lea-dev`.** Ma section 3.5 nomme deux familles de distracteurs : *"les aiguilles
inversées ; l'heure suivante"*. `lea-dev` les a suivies à la lettre. Une heure **suivante** dans chaque item met mécaniquement la bonne
réponse en bas de la paire. Je le dis et je corrige mon texte ainsi, au 2026-10-28 : **la famille *"heure voisine"* comprend l'heure
précédente comme l'heure suivante**, comme `HOR-5` le disait pour le CP (*"une heure voisine d'une ou deux graduations"*). C'est un
assouplissement de ma condition, pas un durcissement : l'heure suivante reste recevable partout.

## 2. Réponses aux questions de la section 7 qui relèvent de moi

### 2.1 Q1 : la forme des réponses

**Je veux la forme chiffrée, telle que le premier jet l'écrit** : `3 h`, `3 h 30`, `3 h 15`, `2 h 45`. Pas de *"3 heures et quart"*, pas
de *"3 heures moins le quart"*, pas de mélange des deux écritures.

**Motifs.**

1. **C'est l'écriture de l'heure que l'enfant de CE1 rencontre écrite**, en classe comme sur un affichage. *"Et quart"* et *"moins le
   quart"* sont des formes orales ; l'adulte et l'enfant les disent, je ne les veux pas écrites dans une proposition.
2. **Une seule écriture par position.** Deux écritures dans le paquet (*"3 h 30"* ici, *"3 heures et demie"* là) ouvriraient une
   question de notation que la ligne ne pose pas, et feraient de la forme un indice.
3. **Lisibilité.** *"3 heures moins le quart"* fait quatre mots, trois fois par manche : la charge de lecture dépasserait celle de la
   lecture du cadran, qui est la compétence.
4. **`3 h 15` ne fait pas lire des minutes au sens où je l'ai écarté pour le CP.** Ma phrase de s25 (*"ni les demies, ni les quarts, ni
   les minutes"*) bornait le CP. En CE1, `15`, `30` et `45` sont les trois seules écritures que la grande aiguille sur 3, 6 et 9 produit ;
   l'enfant lit un quart d'heure, il ne lit pas une minute quelconque. Lire *n'importe quelle* minute reste hors de la ligne.

**Ce que ma forme ne demande pas** : aucune modification de la lecture de valeur (`ADR-0102`). Si `theo-archi` veut y ajouter le quart
d'heure pour un autre motif, c'est sa décision ; elle n'est pas due à cette ligne.

### 2.2 Q13 : quelle section 3.5

**La bonne est la section 3.5 de `arbitrage-nadia-palier-5-lp7-d5.md`**, celle que `lea-dev` a suivie. `arbitrage-nadia-palier-4.md`
n'a pas de section 3.5 (titres 3.1 à 3.4) ; sa ligne 161 ne porte que le paquet de dates.

**Correction de mon propre texte.** Le renvoi fautif est le mien, et il n'est pas dans `lp7-d5` 2.3, qui dit *"mes conditions de 3.5"*
(du même fichier) : il est dans `reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md`, section 2, ligne `M1` à `M6` du tableau :
*"Les conditions de l'horloge sont celles de 3.5 de mon arbitrage du palier 4"*. Il faut lire *"3.5 de
`arbitrage-nadia-palier-5-lp7-d5.md`"*. Je ne réécris pas ce fichier : la correction vit ici, datée. Aucune autre condition de
l'horloge n'existe ailleurs que `HOR-1` à `HOR-3` (s25, 2.1), `CON-5` et les communes de la section 3 de `lp7-d5`.

### 2.3 Les autres questions qui me regardent

| Question | Réponse |
|---|---|
| Q2, ce que *lire l'heure* couvre | Heures, demi-heures, quarts d'heure aux quatre positions de la grande aiguille : c'est la ligne. Un enfant qui lit `9 h 45` a lu un quart d'heure. La lecture sur 24 heures (`15 h`) n'est pas demandée en CE1 par la ligne : je ne la veux pas. Les heures 1, 5 et 7 ne sont la petite aiguille d'aucune bonne réponse : ma 3.5 ne l'exige pas, je ne l'exige pas |
| Q3, aiguilles ou chiffres | Aiguilles seules. Un affichage numérique ne travaille pas la ligne |
| Q5, petite aiguille à 176 px | Reste ma garde, à l'écran. Calcul, pas une preuve : aux quarts, la petite aiguille est à 7,5 degrés d'un nombre ; son bout est déporté d'environ 4 px pour une épaisseur de 7 `[calcul]`. Le risque est réel sur `horloge-g` à `-l` ; je le juge à l'écran après le relevé de l'exigence 2 |
| Q6, distracteurs | Ce sont les familles que je voulais, chacune plausible. Le `6 h` répété sur les trois demies, le `3 h` sur trois quarts et le `9 h` sur trois autres ne sont pas une faute de variété en eux-mêmes : chacun est l'inversion propre à son cadran. Le défaut est collectif (1.3) |
| Q7, indice | Les deux indices me conviennent tels quels. Aucun indice sur la grande aiguille n'est dû |
| Q8, nombre d'items | 12 est mon plancher et suffit à la ligne. Plus d'items est une question de poids et de produit, pas une condition |
| Q10, 12 h pile | Je ne l'exige pas : les aiguilles superposées cachent la petite, et la ligne ne nomme pas midi |
| Q4, glyphes | Section 3, exigence 2 |

## 3. Ce que j'exige avant l'audit à l'écran, et ce que je laisse en réserve

### 3.1 Exigences

1. **Exigence 1 (paquet entier, section 1.3).** Sur les douze items, la stratégie *"écarter la proposition d'une autre forme, puis
   prendre la plus petite heure de la paire"* gagne **au plus 6 items sur 12** (pas plus de la moitié). Moyen recevable, au choix de
   `lea-dev` : l'heure précédente comme distracteur de voisinage sur une partie des items, en priorité sur les heures pleines et sur les
   quarts à 3, où la petite aiguille est sur le nombre ou juste après lui. Les quarts à 9 gardent l'heure suivante : c'est l'erreur
   réelle. Restent tenus : `ADR-0086` clause 2, aucune double lecture d'une position, `HOR-5` transposée, les douze cadrans inchangés.
   `lea-dev` écrit dans son rapport de correction, item par item, ce que la stratégie choisit et le compte obtenu.
2. **Exigence 2 (dessins).** Avant mon audit à l'écran : `ines-design` relit ou remplace les glyphes 3, 4, 6, 7, 8 et 9 (le chiffre doit
   se lire comme le chiffre écrit en classe, à sa taille rendue) et fait le relevé à 744 x 551 que ma section 3 de `lp7-d5` demande
   pour toute ligne de `D5`, **avec la lecture de la petite aiguille à 7,5 degrés d'un nombre** sur au moins un quart à 3 et un quart à
   9. Si elle propose une autre géométrie d'aiguilles, la garde M doit rester verte sur les douze dessins redessinés.

Rien d'autre n'est à corriger dans le premier jet : énoncé, texte alternatif, indices, bonnes réponses et forme d'écriture restent.

### 3.2 Réserves, non bloquantes, pour un sprint suivant

- **Réserve a.** La proposition d'une autre forme est toujours fausse (12 sur 12). L'exigence 1 la laisse : elle ne réduit le choix
  qu'à deux, et le choix dans la paire demande alors de lire la petite aiguille. Une famille qui rendrait parfois la bonne réponse
  intruse (par exemple `3 h` et `4 h` contre `3 h 30` : l'heure lue sans la grande aiguille) la lèverait.
- **Réserve b.** Le pli à 360 px n'est relevé par personne. La story `S57-PLI-LP3-LP5-MESURE` porte sur `ce1-calendrier-dates` (cellule
  de la matrice) et ne couvre pas ce paquet.

Ce que je ne demande pas, pour qu'on ne le lise pas en creux : les heures 1, 5, 7, midi pile, la lecture sur 24 heures, plus de douze
items.

## 4. Routage

| À qui | Quoi |
|---|---|
| `lea-dev` | exigence 1 ; rapport de correction avec le compte item par item |
| `theo-archi`, par `sofia-sm` | aucune lecture du quart d'heure n'est due à ma forme (2.1) ; le poids : 96 873 octets sous le cliquet, et **aucun accord de poids de l'utilisatrice ne couvre ce lot** (`D5` : le poids se demande à l'ouverture de chaque lot) ; si l'exigence 2 change la géométrie des aiguilles, la garde M lit des `<line>` à coordonnées entières ; Q9 (`LIRE_L_HEURE`) est à lui |
| `ines-design` | exigence 2 : glyphes 3, 4, 6, 7, 8, 9 ; relevé à 744 x 551 mesuré (le rapport n'a que des cotes lues à l'oeil) ; réserve b, le pli à 360 |
| `camille-po` | la story d'horloge, inexistante selon le rapport de `lea-dev` ; vérifié par moi seulement en partie : `backlog.md` ne nomme pas le paquet (`grep`), et ses 8 mentions de `MAT-CE1-11` ne sont pas relues ici. À écrire avec mes conditions de 3.5, la correction de 1.3 et l'exigence 1 ; aucune entrée de catalogue avant un Done (`QC3`) ; l'accord de poids, à demander à l'utilisatrice avant mon audit à l'écran, pour que je n'audite pas un lot qu'un refus de poids changerait ; au Done, l'en-tête `DEGRADE` de `ce1-calendrier-dates` et la mention de la cellule `MAT-CE1-11` (Q11) |
| `sofia-sm` | l'ordre : correction de `lea-dev` et relevé d'`ines-design`, puis ma seconde lecture, puis `verify` complet du lot (A.280 point 2), puis mon audit à l'écran |

## 5. Coût

- **Cette lecture est le premier passage** sur le premier jet de l'horloge. Elle est comprise dans le prix du lot
  (`arbitrage-nadia-palier-5-lp7-d5.md` 4 : lecture avant `verify` et correction qui la suit comprises).
- **Une seconde lecture sera due** après la correction : l'exigence 1 change des propositions, ce qui fait de ma relecture une seconde
  lecture et non un arbitrage de formulation (A.272, condition C2). A.272 (règle point 1) et A.280 (point 3, geste (a)) la classent à
  0,50 hors marge, et A.280 admet ce coût au-delà de l'ancien plafond.
- **Je ne fixe aucun prix** et je ne ferme pas `Q-273-1` ici. Durée de cette lecture : non mesurée.

## 6. Ce dont je reste la seule garde, sur ce lot

| Propriété | Pourquoi aucun test ne la tient |
|---|---|
| Qu'aucune stratégie qui ignore le cadran ne gagne plus de la moitié des items (exigence 1) | le garde de rang compare des valeurs sur les trois propositions, pas une paire |
| Que la petite aiguille se lise entre deux nombres à 176 px, aux quarts et à la demie | la garde M vérifie l'angle, pas la lecture |
| Que les glyphes se lisent comme des chiffres | la garde M compte des `<use>`, elle ne lit pas une forme |
| Que le texte alternatif ne livre ni l'heure ni la place d'une aiguille (`CON-5`) | le test de paquet cherche des chiffres et une liste de mots, pas un sens |
| Que chaque distracteur soit plausible | aucun test ne lit un distracteur |

**Signé** : `nadia-prof`, s57, palier 5, 2026-10-28.
