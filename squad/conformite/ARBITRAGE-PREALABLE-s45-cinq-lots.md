# Arbitrage préalable - s45, `S45-MAT-ADDITION` (`MAT-CE1-04`), `S45-MUS-INSTRUMENTS` (`MUS-CP-05`), `S45-EPS-ROLES` (`EPS-CM1-09`) et `S45-LVE-ECRITURE` (`LVE-CM1-06`)

- **Auteure** : `nadia-prof`. **Date** : 2026-10-01. **Sprint** : s45.
- **Ce qui m'est posé** : les questions Q1, Q3, Q4 et Q5 de l'avis de faisabilité de `theo-archi`
  (`squad/rapports/avis-faisabilite-s45-cinq-lots.md`, section 12). Aucun dossier de sprint s45
  n'existe encore. **Je ne tranche rien d'autre** : Q2, Q6 et Q7 appartiennent à `camille-po`, Q8 à
  `sofia-sm`, et `S45-SCI-NUMERIQUE` n'est pas dans ma commande.
- **Audit dégradé, et je le déclare** : aucun des paquets n'existe, il n'y a rien à jouer. Ce que
  j'ai fait à la place : lu l'avis en entier ; relu à leur source `ADP-CE1-1` à `ADP-CE1-6`
  (`ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 4), `MUS-1`, `MUS-2` et la ligne 5
  de mon référentiel d'éducation musicale CP, `MUS-CP-R1` à sa fiche, la ligne 9 et `EPS-1` à
  `EPS-3` de mon référentiel d'EPS CM1, `EPS-9` dans `docs/backlog/backlog.md`, `FER-1` à `FER-3`
  (`ARBITRAGE-PREALABLE-s28-...`, section 2), l'arbitrage de saisie de s19 en entier (dont `R6` et
  ses quatre blocs datés), la section 7.6 de l'arbitrage de s27 (`ANG-S1`, `ANG-S2`) et la section
  4.5 de l'arbitrage de s26 ; lu en entier `cp-eps-regles-jeu-collectif.yaml`. Calculs de la section
  2 refaits par un script de poste, non commité, sur huit couples d'opérandes.
- **Ce document ne prononce aucun Done**, pédagogique ou technique. Il n'écrit aucun paquet, aucun
  test, aucune ligne de matrice ni de backlog.
- **Identifiant écrit ici** : `EPS-10`, condition, alloué dans
  `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même commit.
- **Corrections reportées à leur source dans le même commit**, par un bloc daté qui n'efface rien :
  `ADP-CE1-5` (s43), l'exemple de la ligne 5 du référentiel d'éducation musicale CP, l'exemple de la
  ligne 9 du référentiel d'EPS CM1, la section 7.6 de s27 et la fin de l'arbitrage de saisie de s19.

---

## 1. Verdicts

| Code | Question | Réponse |
|---|---|---|
| Q1 | Laquelle des deux lectures de la retenue oubliée, et que sont la "mauvaise colonne" et la "retenue reportée deux fois" | **La définition fait foi : `48 + 26 -> 64`.** L'exemple `614` est une erreur de ma main. Les trois formes sont définies colonne par colonne en section 2. La mauvaise colonne est **celle des centaines** |
| Q3 | La forme *"La guitare est-elle un instrument à cordes ou à vent ?"* reste-t-elle admise | **Non. À éviter dès l'écriture**, et mon exemple du référentiel est remplacé (section 3) |
| Q4 | Le discriminant CM1/CP face à `cp-eps-regles-jeu-collectif`, et la lecture de `FER-1` sur les trois rôles | **Posé : `EPS-10`** (section 4). **Le rôle est porté par l'énoncé ; la bonne réponse est l'acte du rôle**, jamais le nom du rôle ni ce que devient un joueur |
| Q5 | Le motif de `R6` vaut-il sur `LVE-CM1-06` | **Oui. `LVE-CM1-06` est refusée à la saisie, et `S45-LVE-ECRITURE` sort du périmètre de s45.** Points 2 et 3 sans objet. En vérifiant, j'ai trouvé une omission de ma part sur une ligne de CP déjà `couvert-valide` (section 5.3) |

## 2. Q1 - les trois erreurs de `ADP-CE1-5`

### 2.1 Je corrige d'abord mon propre texte

La clause définit la retenue oubliée ainsi : *"chaque colonne additionnée séparément, le chiffre des
dizaines de la somme d'une colonne étant perdu"*. Sur `48 + 26`, les unités donnent `14`, le `1` est
perdu, on écrit `4` ; les dizaines donnent `6`. **Résultat : `64`.** C'est la lecture juste.

Le motif qui suit dit *"un enfant qui choisit `48 + 26 -> 614`"*. **Cet exemple est faux, et il est
de moi.** `614` est une autre erreur : l'enfant écrit la somme entière de chaque colonne côte à
côte. Elle n'est pas l'une des trois formes admises, et `ADP-CE1-4` l'exclut de toute façon : elle a
toujours plus de chiffres que la bonne réponse. La démonstration de `theo-archi` (avis, section 4.4)
est exacte, je la confirme. Le texte de s43 reste lisible, un bloc daté y renvoie ici.

### 2.2 Les trois formes, définies sur l'opération posée

L'opération se fait colonne par colonne, de droite à gauche. **Une retenue n'est soumise aux trois
formes que si elle entre dans une colonne où l'un des deux nombres au moins a un chiffre.** La somme
de la colonne la plus à gauche est écrite entière.

1. **Retenue oubliée** : la retenue n'est pas ajoutée à la colonne suivante. Le chiffre des dizaines
   de la somme de la colonne est perdu.
2. **Retenue reportée deux fois** : la retenue est ajoutée deux fois à la colonne suivante. C'est
   l'enfant qui ajoute le petit `1` écrit au-dessus de la colonne, puis l'ajoute encore en disant
   *"je retiens 1"*.
3. **Retenue dans la mauvaise colonne** : la retenue des unités est ajoutée à **la colonne des
   centaines**, en sautant celle des dizaines. Cette forme n'existe que si l'un des deux nombres au
   moins a trois chiffres. Ce n'est jamais la colonne des dizaines : c'est la bonne colonne.

**Une forme porte sur une retenue ou sur plusieurs.** Sur un item à deux retenues, oublier l'une des
deux est l'erreur la plus fréquente en classe, plus fréquente que les oublier toutes les deux. Les
formes partielles sont donc admises. La déclaration dans le paquet nomme la forme et la ou les
retenues concernées (unités, dizaines, ou les deux).

| Item | Bonne réponse | Retenue oubliée | Reportée deux fois | Mauvaise colonne |
|---|---|---|---|---|
| `48 + 26` | 74 | 64 | 84 | sans objet (deux chiffres) |
| `67 + 56` | 123 | 113 | 133 | sans objet (deux chiffres) |
| `247 + 186` | 433 | 423 (unités), 333 (dizaines), 323 (les deux) | 443 (unités), 533 (dizaines), 543 (les deux) | 523 |
| `178 + 265` | 443 | 433, 343, 333 | 453, 543, 553 | 533 |
| `247 + 128` | 375 | 365 | 385 | 465 |
| `253 + 184` | 437 | 337 | 537 | sans objet (aucune retenue des unités) |

Sur `67 + 56`, la retenue des dizaines entre dans une colonne vide : elle n'est soumise à aucune
forme, et le `12` des dizaines s'écrit entier.

### 2.3 Ce que `ADP-CE1-4` fait à ces formes

**Une forme qui change le nombre de chiffres n'est pas admise sur cet item.** Elle se traite comme la
coïncidence que prévoit la dernière phrase d'`ADP-CE1-5` : une autre forme de la liste est prise.
S'il ne reste pas deux formes admises et distinctes, l'item ne s'écrit pas avec ces nombres.
Exemples : `45 + 47` (reportée deux fois : `102`), `48 + 57` (oubliée : `95`). Ce n'est pas une
barre neuve : `ADP-CE1-4` portait déjà sur chacune des trois propositions.

**Les trois formes laissent toujours le chiffre des unités intact**, puisqu'aucune retenue n'entre
dans la colonne des unités. La seconde moitié d'`ADP-CE1-4` (un distracteur au moins partage ce
chiffre) est donc tenue par construction. C'est voulu : l'enfant ne peut pas éliminer par le
dernier chiffre.

### 2.4 Conséquences sur le rang, que `theo-archi` a vues

Je les confirme, sans prescrire de répartition :

- un item aux deux nombres de deux chiffres n'a que deux formes, une plus petite et une plus grande.
  **La bonne réponse y est toujours au milieu** ;
- la bonne réponse n'est **la plus grande** qu'avec deux formes de retenue oubliée, donc sur un item
  à trois chiffres et à deux retenues ;
- elle n'est **la plus petite** qu'avec deux formes plus grandes (reportée deux fois, mauvaise
  colonne), donc sur un item à trois chiffres.

## 3. Q3 - la forme "cordes ou vent ?"

**Elle n'est pas admise, et elle s'évite dès l'écriture.** *"La guitare est-elle un instrument à
cordes ou à vent ?"* nomme deux des trois propositions. La troisième (les percussions) s'écarte à la
lecture, et l'item devient un choix entre deux : un enfant qui ne sait rien a une chance sur deux.
C'est la famille de `MUS-CP-R1`, en plus grave, puisque l'énoncé nomme aussi la bonne réponse.

**La règle qui s'applique est celle de `MUS-CP-R1`, sous sa forme générale : un énoncé ne nomme
aucune de ses propositions.** Ce n'est pas une condition neuve, c'est ma réserve de s44 appliquée
avant l'écriture. `theo-archi` la rend exécutable (avis, section 5, dernière ligne du tableau).

**Mon exemple du référentiel est remplacé**, par un bloc daté dans
`squad/programme/education-musicale/competences-cp.md` : *"De quelle famille d'instruments est la
guitare ?"*, 7 mots et une phrase, comptés comme le garde-fou d'`ADR-0089`, les trois familles en
propositions. Sur la tâche inverse (*"Lequel de ces instruments est une percussion ?"*), la famille
est dans l'énoncé et n'est pas une proposition : la règle est tenue.

## 4. Q4 - `EPS-CM1-09` face à `cp-eps-regles-jeu-collectif`

### 4.1 Ce que le CP sert déjà, lu item par item

Le constat de `theo-archi` est exact. Le paquet de CP porte six jeux de cour (1, 2, 3 soleil ; chat
perché ; épervier ; balle au prisonnier ; loup et moutons ; déménageurs) et deux tâches :

- **items 1 à 6** : connaître une règle d'un jeu nommé ;
- **items 7 à 11** : une situation décrite, *"Que se passe-t-il ?"*. **La bonne réponse est toujours
  ce que devient un joueur nommé** : *"rien, Hugo est perché"*, *"Malo devient épervier à son
  tour"*, *"Aya est attrapée"*, *"Hugo doit reposer un des deux objets"*, *"Hugo doit retourner au
  départ"*.

C'est la tâche de l'arbitre, au niveau du CP. **Mon exemple du référentiel CM1 a la même forme, et
il est de plus incomplet** : *"Le ballon sort sur la ligne de côté, à qui la remise en jeu ?"* n'a
pas de réponse tant que l'énoncé ne dit pas qui l'a touché en dernier. Je le remplace par un bloc
daté (section 4.4).

### 4.2 `EPS-10` - le discriminant

> **`EPS-10` - Sur `EPS-CM1-09`, un item ne refait pas la tâche B de `cp-eps-regles-jeu-collectif`.**
> Quatre exigences, toutes opposables à l'audit :
>
> 1. **Aucun item ne porte sur un des six jeux du CP.** Les items portent sur un jeu collectif à deux
>    équipes qui compte des points ou des buts. **Cette exigence est nécessaire, pas suffisante** :
>    changer de jeu en gardant la tâche ne fait pas un item de CM1. Comme pour `EPS-9`, c'est la
>    tâche demandée qui est comptée, pas la phrase.
> 2. **La bonne réponse est l'acte du rôle, jamais ce que devient un joueur** (attrapé, éliminé,
>    change de camp, retourne au départ) :
>    - **arbitre** : une décision qui porte sur les équipes. À quelle équipe revient le ballon, où ou
>      comment le jeu reprend, si un point ou un but est accordé ;
>    - **observateur** : ce qu'il relève sur une action décrite, selon un critère donné dans
>      l'énoncé. Jamais une appréciation (*bien*, *assez*, *pas assez*), jamais une comparaison entre
>      deux enfants (`EPS-2`), jamais ce qu'il dit au camarade : c'est la ligne 6, `non couvrable en
>      l'etat` ;
>    - **marqueur** : à quelle équipe va le point, ou le score après **une seule** action, selon la
>      règle de marque donnée. **La difficulté est la règle, jamais le calcul** : les distracteurs
>      sont des erreurs de règle (le point donné à l'autre équipe, un point compté ou refusé à
>      tort), jamais des erreurs de calcul. Sinon l'item sert les mathématiques.
> 3. **Chacun des trois rôles porte au moins trois items** dont la bonne réponse est son acte.
>    `FER-3` est tenue par construction : ce sont trois tâches.
> 4. **`CYC-1` dans les deux sens** : aucune bonne réponse du paquet de CP n'est un distracteur du
>    paquet de CM1, et inversement.
>
> *Rappel, pas une exigence neuve* : l'arbitre décide sur un fait décrit (ballon sorti, pied dans la
> zone, ballon porté en marchant), jamais sur une intention (*exprès*, *tricher*). C'est `EPS-3` et
> le refus 3 de s32.

### 4.3 `FER-1` sur les trois rôles

**Le rôle est porté par l'énoncé. Ce qui est en position de bonne réponse, c'est l'acte du rôle**
(exigence 2). Un item dont la bonne réponse est le nom du rôle (*"Qui compte les points ? - le
marqueur"*) ne compte pour aucun rôle au titre de `FER-1` : il teste le mot, pas la tenue du rôle.
Lue au pied de la lettre, `FER-1` laisserait fermer la ligne sur un quiz de vocabulaire, ce qui
serait une contrefaçon (`CON-1`).

**Ce n'est pas la lecture que j'ai faite à s44 sur `MUS-CP-11`, et je dis pourquoi.** Là, le verbe
du libellé est *nommer* : le terme en bonne réponse est la compétence elle-même. Ici, le verbe est
*tenir* : l'élément servi est ce que fait le rôle. `FER-1` se lit sur ce que le libellé demande de
faire avec chaque élément. Je l'écris avant le premier item, pour que ce ne soit pas une barre posée
après coup. L'assertion proposée par `theo-archi` (chacun des trois mots figure dans un énoncé) va
dans ce sens et reste partielle.

La mention de couverture partielle exigée d'avance pour cette ligne (référentiel, ligne 9) ne change
pas.

### 4.4 Trois exemples qui tiennent `EPS-10`

Ce sont des exemples, pas des textes imposés.

| Rôle | Énoncé | Bonne réponse | Distracteurs |
|---|---|---|---|
| arbitre (remplace mon exemple) | *"Un joueur bleu envoie le ballon hors du terrain, sur le côté. Qui remet le ballon en jeu ?"* | *"l'équipe rouge, sur le côté"* | *"l'équipe bleue, sur le côté"*, *"l'équipe rouge, au centre du terrain"* |
| observateur | *"L'observateur coche une case pour chaque passe attrapée sans que le ballon touche le sol. Le ballon de Léa rebondit une fois, puis Malo l'attrape. Que fait l'observateur ?"* | *"il ne coche rien"* | *"il coche une case"*, *"il coche une case pour Léa et une pour Malo"* |
| marqueur | *"Au handball, chaque but vaut un point. Les rouges mènent 4 à 3, puis les bleus marquent un but. Quel est le score ?"* | *"4 à 4"* | *"5 à 3"*, *"4 à 3"* |

La règle de la remise en jeu (l'équipe qui n'a pas touché le ballon en dernier) est commune aux sports
collectifs pratiqués à l'école ; un paquet qui la rappelle dans l'énoncé tient `EPS-1` sans débat.

## 5. Q5 - `LVE-CM1-06` et le motif de `R6`

### 5.1 Le motif vaut, et il retire la ligne

`R6` refuse à la saisie une ligne qui demande à l'enfant **de produire les lettres d'un mot**, parce
que sur un clavier qui corrige, c'est l'appareil qui les produit. *"Écrire des mots et des
expressions mémorisés"* demande exactement cela : l'orthographe anglaise du mot, de mémoire. Trois
façons dont l'appareil la produit ou la défait :

1. **Un clavier qui connaît l'anglais corrige un mot faux en mot juste** (*castel* en *castle*,
   *freind* en *friend*). La ligne est servie par l'appareil, pas par l'enfant.
2. **La barre de suggestions propose le mot après deux ou trois lettres.** Elle montre le mot, ce que
   `ANG-S2` interdit à l'indice lui-même.
3. **Un clavier réglé en français corrige un mot anglais juste en mot français** (*friend* en
   *friand*, *house* en *housse*). La réponse juste est refusée et coûte un essai : c'est le scénario
   de `SEUIL-2`. Le retour de `SAI-9a` montre alors un mot que l'enfant n'a jamais écrit.

**Je raisonne, je ne mesure pas** : aucun agent de cette squad ne dispose de l'appareil, comme à s19.
*friand* et *housse* sont des mots français voisins des mots anglais, pas des corrections observées.

**La protection de `SAI-2b` ne joue pas ici.** Elle reposait sur le fait qu'un correcteur répare des
non-mots et n'échange pas deux mots valides. En anglais, les fautes attendues sont des non-mots
anglais, exactement ce qu'un correcteur anglais répare. Et un mot anglais juste est souvent un
non-mot en français. Le défaut joue dans les deux sens, plus fortement qu'en français.

**L'adulte ne protège rien** : au CM1, l'enfant n'est pas supposé accompagné (s19, deuxième
amendement, section 5), et de toute façon la présence d'un adulte est un fait, pas un garde-fou (s19,
troisième amendement, section 5).

**Aucune autre mécanique du produit ne sert cette ligne.** Au choix, l'enfant reconnaît le mot, il ne
l'écrit pas : c'est `LVE-CM1-04`, déjà servie.

**Verdict : `LVE-CM1-06` est refusée à la saisie, au motif de `R6`, à partir du 2026-10-01, et
`S45-LVE-ECRITURE` sort du périmètre de s45.** Levable aux mêmes conditions que `R6` : que la
correction automatique cesse d'être acceptée, ou que la ligne ne soit servie que sur un appareil où
elle ne s'applique pas. **Je ne demande ni l'un ni l'autre**, l'utilisatrice a écarté la
neutralisation. Le classement de la ligne dans la matrice appartient à `camille-po`.

### 5.2 Une omission de ma part, sur cette ligne

J'ai écrit le 2026-08-25, dans `R6`, qu'il n'existait *"aucune ligne équivalente au CM1"*. C'était
vrai ce jour-là. Le référentiel d'anglais CM1 date du 2026-09-02, et **ce même jour j'ai écrit
`ANG-S1` et `ANG-S2` et déclaré la saisie possible sur cette ligne sans relire `R6`.** La mention de
la matrice (*"jouable par saisie, sous conditions `ANG-S1` et `ANG-S2`"*) vient de cette omission.
Bloc daté ajouté à s19 et à s27. La mention de la matrice est à corriger par `camille-po`.

### 5.3 Une omission plus lourde, trouvée en vérifiant, que je ne tranche pas ici

En cherchant si `R6` avait été levé, j'ai trouvé que **la ligne de `R6` elle-même**, *"Écrire de
manière autonome un mot simple"* (français CP), est `couvert-valide` depuis s26 par
`saisie-mot-dicte-cp`. Mon acceptation de cette ligne (`ARBITRAGE-PREALABLE-s26-la-voix-est-un-adulte.md`,
section 4.5) examine `SAI-11` et `SAI-1`, **jamais `R6`**. Aucun texte du dépôt ne lève `R6`. C'est le
cas que `R6` annonçait lui-même : *"le jour où une story ouvrira cette ligne en saisie, la CI sera
verte et je serai la seule à dire non"*. Je n'ai pas dit non.

**Je ne le tranche pas ici** : ce n'est pas dans Q1 à Q5, et rouvrir un Done se fait en jouant, pas
dans un arbitrage préalable. Je le route à `camille-po` via `sofia-sm`, pour une instruction dans un
prochain sprint. Deux choses que l'équipe doit savoir dès maintenant :

- **une omission ne lève pas un refus.** Appliquer `R6` à l'anglais reste juste, même si le CP a été
  validé contre lui ;
- **je ne vois aucune différence qui sauverait la ligne de CP.** `VOI-9` y impose des mots réguliers,
  dont les fautes attendues (*bato*) sont des non-mots, exactement ce qu'un correcteur répare. Le
  refus de l'anglais et la validation du CP ne sont donc pas cohérents aujourd'hui, et personne ne
  doit lire l'un comme confirmant l'autre.

### 5.4 Points 2 et 3

**Sans objet** : la ligne ne reste pas jouable. Je ne pose ni mention de couverture partielle ni
seconde tâche d'avance : une condition écrite sur une ligne refusée se lirait comme une ouverture. Les
faits relevés par `theo-archi` (aucune illustration sur un item de saisie, aucune expression dans le
lexique servi) restent ceux à instruire le jour où `R6` serait levé.

## 6. Ce que ce document ne fait pas

Il n'écrit aucun item, ne fixe aucune répartition par rang ni par rôle au-delà d'`EPS-10`, ne touche
pas `ADP-CE1-2` ni `ADP-CE1-4` au-delà de leur lecture en section 2.3, ne rouvre pas le Done de
`saisie-mot-dicte-cp`, ne classe aucune ligne dans la matrice, ne redécoupe pas le périmètre de s45
au-delà du retrait de `S45-LVE-ECRITURE`, et ne prononce aucun Done.

## 7. Ce dont je reste la seule garde

- **`ADP-CE1-5`** : que les trois formes soient des erreurs d'enfant. Leur calcul est encodable,
  section 2.2.
- **`MUS-CP-05`** : l'exactitude de la famille de chaque instrument. La règle de `MUS-CP-R1` est
  encodable.
- **`EPS-10`, exigence 2** : que la bonne réponse soit l'acte du rôle et non le statut d'un joueur,
  et que l'observateur relève sans juger. Seuls les noms des jeux et des rôles se lisent sur les
  chaînes. L'exactitude de chaque règle de sport aussi.
- **`R6`, désormais sur deux lignes** : rien n'empêche une story de rouvrir `LVE-CM1-06` en saisie.
  La CI sera verte.

---

**Signé** : `nadia-prof`, 2026-10-01.
