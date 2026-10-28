# s57, palier 5 : arbitrage de l'horloge `MAT-CE1-11`, stratégie de paire et pli (`nadia-prof`)

- **Auteure** : `nadia-prof`, professeure des écoles, seule autorité sur le Done pédagogique.
- **Date** : 2026-10-28, 13:43 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-tranche-horloge`, base `round-two` à `bd838dd5`. Aucun push, aucun rebase, aucune fusion. Ce
  fichier est le seul que j'écris.
- **Mandat** : l'utilisatrice, le 2026-10-28, mots exacts : *"Q1 c'est nadia qui tranche et pour Q2 aussi"*. Q1 est la question
  `A1` de `theo-archi` (son avis, 5.3) ; Q2 est la recevabilité du pli (`ines-design`, F4 et F5).
- **Type** : arbitrage avant la correction de `lea-dev`. **Aucun Done, aucun refus, aucun bloc de validation rempli, aucun
  identifiant de condition ou de réserve neuf.** Ma lecture du premier jet n'est pas réécrite : ce qui change est écrit ici, daté.
- **Lu** : `lecture-nadia-premier-jet-horloge-mat-ce1-11.md` (en entier) ; `avis-theo-palier-5-horloge-mat-ce1-11.md` (en entier) ;
  `avis-ines-palier-5-horloge-mat-ce1-11.md` (en entier) ; `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` (en entier) ;
  `arbitrage-nadia-palier-5-lp7-d5.md` (3 et 3.5) ; `veto-nadia-gabarit-pli.md` (sections 3 et 4, V3 à V6) ; story
  `S57-BR-MAT-CE1-11-HORLOGE` de `docs/backlog/backlog.md` ; `squad/conformite/FICHE-ce1-lire-l-heure.md` ; les lignes
  `bonneReponse` et `distracteurs` de `ce1-lire-l-heure.yaml`.
- **Audit dégradé, déclaré** : je n'ai pas joué le jar et je n'ai rien regardé à l'écran. Les cotes sont celles d'`ines-design`.

## Résumé

| Point | Ce que je tranche |
|---|---|
| Q1, stratégies comptées | **Les quatre** de `theo-archi` (`S1` à `S4`), chacune **au plus 6 items sur 12**. Comme l'intrus est toujours faux, cela vaut **exactement 6 et 6** dans les deux ordres |
| Mon 10 sur 12 | **Inexact comme mesure de ma propre stratégie.** Un enfant de CE1 sait que 1 h vient après 12 h : lue dans l'ordre du cadran, elle gagne 12 sur 12. `theo-archi` a raison |
| Ma condition 3.5 | *"l'heure suivante reste recevable partout"* est faux : je retire *"partout"* |
| Q2, signal à 556 à 744 x 551 | **Recevable.** Ma barre est le bas de `.indice` (530, tenu) ; les 5 px sont sous le dernier texte |
| Q2, 360 px | **Pas un critère de ce paquet.** La réserve b est levée : le relevé existe |
| Q2, hauteur de 551 | **Je la garde**, comme convention de mesure du dépôt |
| Petite aiguille à 7,5 degrés | **Je garde la géométrie (O1).** La lecture reste ma garde à l'écran |
| Glyphes (F1) | **D'accord** : aucun remplacement. L'exigence 2 de ma lecture est tenue |
| Coût | seconde lecture due, 0,50 hors marge (A.272, A.280) ; je ne fixe aucun prix |

## 1. Q1 : la stratégie de paire

### 1.1 Correction de mon propre constat

J'ai écrit (lecture, 1.3) : *"Un enfant qui écarte l'intrus puis prend la plus petite heure de la paire gagne 10 manches sur 12"*, et
j'ai désigné `horloge-f` et `horloge-g` comme les deux exceptions. **Ce compte lit les heures comme des nombres écrits, où 12 est plus
grand que 1. Ce n'est pas ainsi qu'un enfant de CE1 lit une horloge** : 1 h vient juste après 12 h, il l'apprend en même temps que
le cadran. Lue dans l'ordre du cadran, la même stratégie gagne **12 items sur 12**. Je l'ai recalculé moi-même sur les douze lignes
`bonneReponse` et `distracteurs` du paquet, par un script hors dépôt qui suit la définition de `theo-archi` (5.2) : `S1` = 10,
`S2` = 2, `S3` = 12, `S4` = 0, ses chiffres. Ma mesure n'est pas fausse sur `S1`, elle a mesuré la variante la plus faible.

### 1.2 Ce que le garde compte

**Les quatre stratégies, `S1` à `S4`.** Motif : ma section 6 disait déjà *"qu'aucune stratégie qui ignore le cadran ne gagne plus de
la moitié des items"*, et c'est aussi le principe du garde de rang (*"une stratégie qui ignore le contenu ne doit jamais être
majoritaire"*). `S2` et `S4` ignorent le cadran autant que `S1` et `S3` : prendre toujours la plus grande heure n'est pas plus une
lecture d'horloge que prendre la plus petite. Retenir `S1` seul aurait laissé passer une correction à 5 heures suivantes et 7
précédentes, que `S2` gagne à 7 sur 12.

**Ce n'est pas une barre relevée après coup.** Aucune correction n'a encore été livrée contre mon exigence 1 ; la barre que je fixe
est celle de ma section 6, écrite le même jour, et l'exigence 1 n'en nommait qu'un cas.

### 1.3 Définition, pour le code

Pour chaque item du paquet :

1. Lire les trois propositions par le motif de la garde M (`^(\d{1,2}) h(?: (\d{2}))?$`) : une heure écrite `h` (entier de 1 à 12) et
   un champ de minutes (absent compte comme `00`).
2. Regrouper les trois propositions par champ de minutes. **La stratégie s'applique** si et seulement s'il y a exactement deux groupes,
   l'un de deux propositions (la paire), l'autre d'une (l'intrus). Sinon, l'item compte comme **perdu** pour les quatre stratégies.
3. Dans la paire, d'heures écrites `a` et `b` :
   - `S1` choisit la proposition de plus petite heure écrite (comparaison des entiers, 12 plus grand que 1) ; `S2` choisit l'autre.
   - Soit `d = (b - a) mod 12`. Si `d` vaut de 1 à 5, `a` précède `b` dans l'ordre du cadran : `S3` choisit `a`, `S4` choisit `b`.
     Si `d` vaut de 7 à 11, `S3` choisit `b`, `S4` choisit `a`. Si `d` vaut 6, `S3` et `S4` ne s'appliquent pas : item perdu pour les
     deux. Ne pas utiliser `position()`, qui ramène 12 à 0 (`theo-archi`, 5.2).
4. Une stratégie **gagne** l'item si la proposition qu'elle choisit est la bonne réponse.

**Barre** : pour chacune des quatre stratégies, `gains * 2 <= nombre d'items du paquet` (12 aujourd'hui, soit au plus 6). Le
dénominateur est le nombre total d'items, pas le nombre d'items où la stratégie s'applique. Le message d'échec nomme la stratégie et
son compte. La population (au moins un item où la stratégie s'applique), le lieu du garde, la mise à l'épreuve (`ADR-0013`) et le
matériau figé du premier jet sont ceux de `theo-archi` (5.2) : je ne les tranche pas, ils ne sont pas pédagogiques.

### 1.4 La conséquence arithmétique, dite pour que `lea-dev` ne la découvre pas

L'intrus est toujours faux, donc la bonne réponse est toujours dans la paire, et `S1 + S2 = S3 + S4 = 12`. La barre impose donc
**exactement 6 et 6 dans les deux ordres**. Avec les bonnes réponses et les cadrans inchangés, `S1` et `S3` ne diffèrent que sur une
paire 12 et 1. Pour qu'ils valent tous deux 6 :

- `horloge-f` (`12 h 30`) et `horloge-g` (`12 h 15`) prennent **l'heure précédente** : `11 h 30` et `11 h 15`. C'est forcé.
- Sur les dix autres items, **6 heures suivantes et 4 heures précédentes**.

Plausibilité de ces deux distracteurs forcés, jugée sur le texte : `11 h 15` est l'erreur de l'enfant qui voit la petite aiguille à
peine passée sur 12 et croit qu'elle n'y est pas encore, plausible. `11 h 30` est plus faible, la petite aiguille étant à mi-chemin
de 12 et de 1, mais il est à la même distance que `1 h 30` et non absurde. Je le relis à la seconde lecture, pas avant.

Exemple qui tient, vérifié par le même script (6, 6, 6, 6), **donné comme preuve de faisabilité, pas comme prescription** : heure
précédente sur `horloge-a` (`2 h`), `-b` (`5 h`), `-h` (`3 h 15`), `-i` (`9 h 15`), `-f` et `-g` ; heure suivante sur les six autres.

### 1.5 Ce que je retire ou assouplis, mot pour mot

| Texte | Ce que je fais |
|---|---|
| Exigence 1 : *"la stratégie "écarter la proposition d'une autre forme, puis prendre la plus petite heure de la paire" gagne au plus 6 items sur 12 (pas plus de la moitié)"* | **Remplacé** par 1.3 : les quatre stratégies, chacune au plus 6. *"La plus petite heure"* était ambigu, je le retire |
| Exigence 1 : *"en priorité sur les heures pleines et sur les quarts à 3"* | **Gardé comme préférence**, au choix de `lea-dev`. Sur les sept items libres (`-a` à `-e`, `-h`, `-i`), 3 heures suivantes et 4 précédentes ; je conseille de garder l'heure suivante sur les demies `-d` et `-e`, où l'heure précédente est l'erreur la moins plausible |
| Exigence 1 : *"Les quarts à 9 gardent l'heure suivante : c'est l'erreur réelle."* | **Gardé.** Compatible avec la barre |
| Exigence 1 : *"`lea-dev` écrit dans son rapport de correction, item par item, ce que la stratégie choisit et le compte obtenu."* | **Assoupli** : le compte est celui que le garde affiche, recopié de sa sortie, pas recalculé à la main. Item par item, `lea-dev` écrit seulement si le distracteur de voisinage est l'heure précédente ou suivante |
| Section 6, ligne 1 : *"Qu'aucune stratégie qui ignore le cadran ne gagne plus de la moitié des items (exigence 1)"* | **Restreint**, une fois le garde écrit : les quatre stratégies de paire sont tenues par le garde. Je reste la seule garde des autres stratégies qui ignorent le cadran (section 4.3) |
| Lecture 1.3 : *"l'heure suivante reste recevable partout"* | **Retiré : "partout"**. Voir 1.6 |

### 1.6 Ce que le 12 sur 12 change à ma condition 3.5

Ma condition 3.5 écrit les distracteurs ainsi : *"les aiguilles inversées ; l'heure suivante, lue sur le nombre que la petite aiguille
approche."* Ma lecture (1.3) disait déjà que cette condition était la cause du défaut. **Le 12 sur 12 montre qu'elle en est la cause
entière** : suivie à la lettre sur tous les items, elle fait gagner une stratégie sans cadran à chaque manche, pas à dix sur douze. Ma
correction du matin, *"la famille "heure voisine" comprend l'heure précédente comme l'heure suivante"*, reste ; sa fin, *"l'heure
suivante reste recevable partout"*, est fausse et je la retire. Lecture de ma 3.5 à partir de ce jour : **distracteurs de voisinage,
l'heure précédente ou l'heure suivante, dans les proportions que le garde de paire admet.** Les aiguilles inversées sont inchangées.
C'est un assouplissement de la famille et une borne sur sa répartition, pas une famille nouvelle.

## 2. Q2 : le pli

### 2.1 Les 5 px du signal à 744 x 551 : recevables

Ma barre sur les lots de ce palier est le **bas de `.indice` au plus à 551** (critère repris du palier 3, `veto-nadia-gabarit-pli.md`
section 3 : *"Ma barre de S2 était l'indice entier"*). Elle est tenue : **530**, 21 px de marge, 0 item sur 12, sur 70 relevés après
une première erreur et 30 après une seconde. Le signal qui finit à 556 n'est pas dans ma barre ; je ne l'y ajoute pas.

Le précédent que j'ai écrit pour ce cas dit : *"Si les 19 px sous le pli ne sont que le bas de la boîte et de l'icône, la réserve est
acceptable"*. Ici, l'indice est le dernier texte du signal et il finit à 530 : les 5 px sous 551 sont sous tout texte lu, donc du bas
de boîte. **Recevable pour ce paquet, sans réserve.** Ce que je regarderai à mon audit à l'écran, sans en faire une exigence : que rien
de lisible ne soit coupé à ce bas, ce qu'`ines-design` n'a pas relevé (F6).

### 2.2 Le 360 px : pas un critère de ce paquet

À 360 de large et 551 de haut, les propositions finissent à 702, avant toute erreur. Je ne le retiens pas contre ce paquet :

1. 360 n'est pas une largeur du périmètre de mesure de mes fiches ; mon critère de pli est à 744 x 551.
2. Les gabarits de référence eux-mêmes passent sous le pli à 360 (bas de `.indice` de 958 à 1102, relevé de `LP6` cité par
   `ines-design`). C'est une propriété de la mise en page à cette largeur, pas de ce contenu.
3. Ce qui doit être vrai à 360 est tenu : aucun débordement horizontal (`scrollWidth` égal à `clientWidth` à chaque relevé), cadran
   entier à 176 px et centré.

**Réserve b de ma lecture : levée.** Son objet était *"Le pli à 360 px n'est relevé par personne"* ; il l'est (F5). Le constat reste
écrit dans l'avis d'`ines-design` ; je n'en fais pas une réserve de ce paquet.

### 2.3 La hauteur de 551 : gardée

551 est la convention de mesure du dépôt, pas la hauteur d'un appareil (`ines-design`, 3.3) : elle sert à comparer les lots entre eux,
et un appareil plus haut en voit davantage. Je ne la change ni pour ce paquet ni à 360.

## 3. Petite aiguille et glyphes

- **Petite aiguille à 7,5 degrés : je garde la géométrie (O1).** Le déport de 3,65 px est confirmé par `theo-archi` et `ines-design`.
  O2 affaiblit `HOR-3` (rapport des longueurs de 0,67 à 0,81), O3 réduit l'écart d'épaisseur entre les aiguilles : les deux retirent à
  la distinction heure et minute, qui est la compétence, plus qu'ils n'apportent. La lecture par un enfant reste ma garde, à l'écran
  sur `horloge-g` à `-l`. Si elle tombe, je l'écrirai alors avec son motif ; aucun redessin n'est dû avant.
- **Glyphes (F1) : d'accord, aucun remplacement.** Lecture d'`ines-design` sur les six glyphes que je visais (3, 4, 6, 7, 8, 9), à
  leur taille rendue, plancher d'encre de 14 px tenu. Je les relirai à l'écran comme des chiffres, ce qui reste ma garde.
- **Exigence 2 de ma lecture : tenue.** Glyphes relus, relevé à 744 x 551 mesuré au `getBoundingClientRect()`, petite aiguille
  regardée sur un quart à 3 (`horloge-g`) et un quart à 9 (`horloge-j`). Les *"six ordres"* du critère 6 de la story ne sont pas dans
  mon exigence ; les 44 ordres vus et la rangée unique constante me suffisent.
- **Aiguilles qui se touchent près du moyeu** (F3, `horloge-h` et `-k`) : je regarde à l'écran, je n'exige rien.

Conséquence pour le poids : aucun dessin ne change, la correction ne touche que le paquet. Le cliquet de 96 873 octets n'est pas
remis en question par cet arbitrage.

## 4. Ce qui est exigé, ce qui reste en réserve

### 4.1 Exigé de `lea-dev`, avant ma seconde lecture

1. **Distracteurs de voisinage corrigés** pour que `S1` à `S4` valent chacun au plus 6 sur 12 (1.3), donc 6 exactement (1.4), avec
   `horloge-f` et `horloge-g` sur l'heure précédente. Restent tenus, sans changement : les douze cadrans, les douze bonnes réponses,
   la forme chiffrée, les distracteurs d'aiguilles inversées, `ADR-0086` clause 2, aucune double lecture d'une position, `HOR-5`
   transposée, l'heure suivante sur les quarts à 9.
2. **Le garde de paire écrit** selon 1.3, dans le commit qui corrige le contenu (forme et lieu : `theo-archi`, 5.2).
3. **Le rapport de correction** donne, item par item, le distracteur de voisinage (heure précédente ou suivante) et recopie le compte
   des quatre stratégies tel que le garde l'affiche.

Rien d'autre : ni pli, ni dessin, ni glyphe, ni indice, ni texte alternatif.

### 4.2 En réserve, non bloquante

- **Réserve a, maintenue.** La proposition d'une autre forme est toujours fausse (12 sur 12). Je n'exige pas de la lever dans ce lot ;
  si `lea-dev` le fait sur certains items, la barre de 1.3 reste la même et le garde la mesure.

### 4.3 Ce dont je reste la seule garde, une fois le garde de paire écrit

| Propriété | Pourquoi aucun test ne la tient |
|---|---|
| Qu'aucune stratégie sans cadran autre que `S1` à `S4` ne soit majoritaire (par exemple selon la forme des minutes) | le garde de paire ne compte que quatre stratégies |
| Que chaque distracteur soit plausible, en particulier `11 h 30` et `11 h 15` | aucun test ne lit un distracteur |
| Que la petite aiguille se lise entre deux nombres à 176 px | la garde M vérifie l'angle, pas la lecture |
| Que les glyphes se lisent comme des chiffres | la garde M compte des `<use>` |
| Que rien de lisible ne soit coupé dans les 5 px sous le pli | le critère mesuré est le bas de `.indice` |
| `CON-5` sur le texte alternatif | le test de paquet cherche des chiffres et une liste de mots |

Ce que je ne demande pas, pour qu'on ne le lise pas en creux : une stratégie conditionnée par la forme des minutes n'est pas dans la
barre. Avec trois items par forme, toute répartition laisse au moins deux items sur trois à l'un des deux sens dans une forme ; la
barre porte sur le paquet entier, comme celle du garde de rang.

## 5. Coût

- **La seconde lecture reste due** après la correction de `lea-dev` : l'exigence 1 change des propositions (A.272, condition C2).
  A.272 (règle point 1) et A.280 (point 3, geste (a)) la classent à 0,50 hors marge.
- **Je ne fixe aucun prix**, ni pour cet arbitrage ni pour la seconde lecture, et je ne ferme pas `Q-273-1`. Durée : non mesurée.

## 6. Routage

| À qui | Quoi |
|---|---|
| `lea-dev` | 4.1, points 1 à 3 |
| `theo-archi`, par `sofia-sm` | 1.3 est la définition et la barre du garde ; le reste de sa forme est à lui |
| `camille-po` | le critère 5 de la story cite mon exigence 1 et ma convention *"12 h reste 12"* : remplacés par 1.3 et 1.4 ; critère 7 (360) : réserve b levée, pas de seuil (2.2) ; critère 6 : le relevé d'`ines-design` est dans `avis-ines-palier-5-horloge-mat-ce1-11.md`, non dans un fichier `mesure-ines` |
| `sofia-sm` | l'ordre est inchangé : correction de `lea-dev`, ma seconde lecture, `verify` complet du lot, mon audit à l'écran |

**Signé** : `nadia-prof`, s57, palier 5, 2026-10-28.
