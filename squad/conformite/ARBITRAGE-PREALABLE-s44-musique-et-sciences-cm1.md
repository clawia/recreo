# Arbitrage préalable - s44, `S44-MUS-VOCABULAIRE` (`MUS-CP-11`) et `S44-SCI-OBJET-TECHNIQUE` (`SCI-CM1-13`)

- **Auteure** : `nadia-prof`. **Date** : 2026-09-30. **Sprint** : s44.
- **Ce qui m'est posé** : les questions Q1 et Q3 de l'avis de faisabilité de `theo-archi`
  (`squad/rapports/avis-faisabilite-s44-mus-sci.md`, section 10), reprises telles quelles par
  `sofia-sm` (`squad/sprints/s44-musique-sciences-et-division-ce1/planning.md`, section 4). Non
  bloquantes pour le planning, dues avant l'écriture des deux paquets. **Je ne tranche rien
  d'autre** : Q2 appartient à `camille-po`, et sa correction de la story de sciences est déjà
  fusionnée (`ead6038d`) ; je la lis, je ne la rejuge pas.
- **Audit dégradé, et je le déclare** : aucun des deux paquets n'existe, il n'y a rien à jouer. Ce
  que j'ai fait à la place : relu mon référentiel d'éducation musicale CP (`MUS-1`, `MUS-2`, ligne
  11 du jugement), relu mon référentiel de sciences CM1 (ligne 13 du jugement), relu le tableau 2.2
  et la section 2.3 de mon arbitrage de s30, relu `BIA-3` à sa source, lu en entier les deux paquets
  adjacents servis (`cm1-sciences-objet-technique.yaml`, `cm1-sciences-familles-materiaux.yaml`) et
  la méthode de comptage des mots du garde-fou `ADR-0089`, pour compter mes exemples comme il les
  comptera.
- **Ce document ne prononce aucun Done**, pédagogique ou technique. Il n'écrit aucun paquet, aucun
  test, aucune ligne de matrice ni de backlog.
- **Identifiants écrits ici** : `MUS-3`, `EVO-1`, `EVO-2`, conditions, alloués dans
  `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même commit.

---

## 1. Verdicts

| Code | Question | Réponse |
|---|---|---|
| Q1 | Une forme d'énoncé pour `pulsation` et `silence`, sans écoute (`MUS-1`) ni geste frappé (`MUS-2`) | **Ligne tenable. Forme posée : `MUS-3`** (section 2). `FER-1` reste entière : les quatre termes de la parenthèse sont exigibles en bonne réponse |
| Q3 | Le discriminant entre un item d'évolution et un item de `SCI-CM1-11` ou `SCI-CM1-12` | **Posé : `EVO-1` et `EVO-2`** (section 3). La formulation de la tâche ne suffit pas à elle seule ; le discriminant porte sur la bonne réponse et les distracteurs |

## 2. Q1 - `pulsation` et `silence` sans écoute ni geste

### 2.1 Ce que `MUS-1` interdit, relu avant de m'en servir

`MUS-1` (`squad/programme/education-musicale/competences-cp.md`, lignes 90 à 93) interdit deux
choses : l'item qui suppose une écoute (*"écoute puis réponds"*), et l'énoncé *"qui décrirait un son
à la place de le faire entendre"*. Le second visait un **son particulier** donné à identifier par sa
description - c'est la contrefaçon de `MUS-CP-04` que je nomme ligne 79 (*"le tonnerre fait-il un
son grave ou aigu ?"*).

**Une définition générale d'un mot du vocabulaire musical n'est pas ce cas.** Elle ne présente
aucun son à reconnaître ; elle nomme une notion, et demande le mot. Le test de `MUS-1` s'y applique
ainsi : en effaçant l'écoute, la réponse reste trouvable - par la seule connaissance du mot - et
l'item ne prétend pas tester l'écoute. C'est exactement ce que `MUS-CP-11` vise (*"nommer quelques
repères du vocabulaire musical **rencontré**"*) : le mot a été rencontré en classe, le jeu le fait
nommer. Ma propre question de départ pour le refrain (ligne 86) a déjà cette forme ; je l'étends,
je ne la change pas.

### 2.2 `MUS-3` - la forme admise pour `pulsation` et `silence`

> **`MUS-3` - Sur `MUS-CP-11`, un item dont la bonne réponse est `la pulsation` ou `le silence`
> donne une définition générale de la notion et demande le mot qui la nomme.** Six exigences, toutes
> opposables à l'audit :
>
> 1. **Définition générale, jamais un cas particulier** : aucun item ne nomme une chanson, un
>    morceau, un instrument ou une situation d'écoute. *"Dans Frère Jacques, où est le silence ?"*
>    suppose que l'enfant a entendu la chanson : c'est `MUS-1`.
> 2. **Aucun geste, ni demandé ni décrit** : l'item ne définit pas la pulsation par ce qu'on en fait
>    (*"on la frappe dans les mains"*, *"on la marque du pied"*). C'est la compétence de `MUS-CP-03`,
>    `non couvrable en l'etat`, et c'est une invitation au geste que rien ne vérifie (`MUS-2`).
> 3. **Aucun comptage** (*"combien de pulsations ?"*) : compter des temps suppose de les percevoir.
> 4. **Le silence est un silence de musique**, situé dans une chanson ou une musique. Le sens de
>    conduite (*"attendre en silence"*, déjà servi au CP par `cp-emc-regles-de-vie` et `cp-pluriel`,
>    avis de `theo-archi`, section 6) est celui de `MUS-CP-10`, `non couvrable en l'etat`, et n'entre
>    pas dans ce paquet, ni en énoncé ni en distracteur.
> 5. **Distracteurs** : les autres mots du vocabulaire musical du paquet (`le refrain`, `le couplet`,
>    `la pulsation`, `le silence`, ou un terme du même registre si le paquet en sert un). **Exclus
>    parce que défendables** : `le rythme`, `le tempo` et `la mesure` sur l'item de pulsation - un
>    adulte dit couramment *"un rythme régulier"* ; `la pause` et `le soupir` sur l'item de silence
>    - ce sont des noms de silences en musique, donc des réponses vraies.
> 6. **Les textes de l'item** (énoncé, propositions, indice, explication) ne contiennent ni `écout`,
>    ni `entend`, ni `frapp`, ni `adulte`, ni `fredonn`, ni le verbe *taper* sous aucune forme :
>    c'est le lexique de l'avis de `theo-archi` (section 4), auquel j'ajoute *taper*, le verbe qu'il
>    nomme lui-même comme résidu. *Taper* se cherche en mot entier, jamais en sous-chaîne : `tape`
>    est dans *étape*.

### 2.3 Deux énoncés qui tiennent `MUS-3`, comptés comme le garde-fou compte

Comptage d'`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest.mots()` : découpage aux espaces,
un jeton compte s'il porte une lettre ou un chiffre ; `s'appelle` et `d'une` comptent un mot, le
point d'interrogation zéro.

| Terme | Énoncé | Mots | Distracteurs admis |
|---|---|---|---|
| pulsation | *"Comment s'appelle le battement régulier d'une musique ?"* | 7 | `le refrain`, `le silence` |
| silence | *"Comment s'appelle le court moment où la musique s'arrête, au milieu d'une chanson ?"* | 13 | `le refrain`, `la pulsation` |

**Exactitude** : la pulsation est le battement régulier qui sous-tend une musique ; le silence est
un moment sans son à l'intérieur d'une musique. *"Au milieu d'une chanson"* écarte la fin du
morceau, qui serait une lecture défendable. Je n'écris pas *"toujours à la même vitesse"* pour la
pulsation : une musique peut accélérer, et la phrase deviendrait fausse.

**Vocabulaire** : *battement* et *régulier* sont les deux mots les plus exigeants de ces énoncés
pour un lecteur de CP. Je les accepte parce que ce sont les mots de la définition elle-même, et je
les vérifierai en jouant. Ces deux énoncés sont des exemples qui tiennent `MUS-3`, pas des textes
imposés : l'auteur du paquet peut en écrire d'autres, et je les jugerai contre `MUS-3`.

### 2.4 Conséquence pour `FER-1`

Aucune. Les quatre termes (`refrain`, `couplet`, `pulsation`, `silence`) restent exigibles chacun en
bonne réponse d'au moins un item, sous la forme littérale que `theo-archi` propose d'encoder (avis,
section 4, sur le modèle de `TERMES_DE_LA_PARENTHESE`). Une seconde tâche au sens de `FER-3`, par
exemple *"mot -> définition"* (la bonne réponse est une phrase qui définit le mot), est admise sous
les mêmes exigences 1 à 6, mais **ne compte pas pour `FER-1`** : le terme n'y est pas en position
de bonne réponse.

## 3. Q3 - ce qui distingue un item d'évolution d'un item de `SCI-CM1-11` ou `SCI-CM1-12`

### 3.1 Pourquoi la formulation de la tâche ne suffit pas

Le couple de tâches que j'ai donné pour cette ligne en s30 (*"dire ce qui a changé / dire ce qui
n'a pas changé (la fonction demeure)"*, tableau 2.2, ligne 13) reste valable. Mais **le verbe de la
consigne ne distingue rien à lui seul** :

- *"La lampe à pétrole est devenue la lampe de poche. Qu'est-ce qui n'a pas changé ?"*, bonne
  réponse *"elle sert à éclairer"*, distracteurs *"elle sert à chauffer"*, *"elle sert à cuisiner"* :
  c'est l'item *"À quoi sert une lampe de poche ?"* de `cm1-sciences-objet-technique.yaml` (ligne
  73), précédé d'une phrase d'histoire. L'enfant répond par la fonction d'un seul des deux objets,
  sans comparer. `ADR-0081`, clause 5, le compterait sur `SCI-CM1-13` ; pédagogiquement, il sert
  `SCI-CM1-12`.
- *"Autrefois, le seau était en bois. En quoi est-il souvent fait aujourd'hui ?"*, bonne réponse
  *"le plastique"*, distracteurs *"le verre"*, *"le tissu"* : c'est la tâche A de
  `cm1-sciences-familles-materiaux.yaml` (*"de quelle famille de matériau est-il fait ?"*, lignes 62
  à 81), avec la même liste fermée de réponses.

Ce que la ligne `SCI-CM1-13` exige en propre, c'est **l'opération de comparaison entre deux états
du même objet dans le temps**. Le discriminant porte donc sur ce que la bonne réponse et les
distracteurs obligent à faire, pas sur la question.

### 3.2 `EVO-1` - la bonne réponse énonce une relation entre deux états

> **`EVO-1` - Sur `SCI-CM1-13`, aucune bonne réponse n'est un terme nu que servent déjà
> `SCI-CM1-11` ou `SCI-CM1-12`** : ni une famille de matériaux seule (`le bois`, `le métal`, `le
> verre`, `le plastique`, `le tissu`), ni un composant seul (`la chaîne`, `la pile`...), ni la
> fonction d'un objet pris isolément. **Chaque bonne réponse énonce une relation entre un état
> ancien et un état récent du même objet** : ce qui est passé de l'un à l'autre, ou ce qui est
> resté. Les distracteurs ont la même forme (`ADR-0086` : la forme des propositions ne désigne pas
> la bonne réponse).
>
> **Sur un changement de matériau**, la bonne réponse nomme le passage (*"le bois a laissé la place
> au plastique"*), et **le distracteur le plus utile est le passage inverse** (*"le plastique a
> laissé la place au bois"*) : il teste l'ordre dans le temps, qui est la compétence de la ligne,
> et aucune connaissance de famille ne permet de l'écarter. Une famille de matériaux peut donc
> figurer dans une proposition, jamais en être toute la réponse.

**Ce que `EVO-1` n'interdit pas** : les familles de matériaux et les objets de `SCI-CM1-11` ne sont
pas exclus du paquet. `BIA-3`, tel que je l'ai transposé le 2026-09-03 (référentiel de sciences
CM1, ligne 109), nomme expressément *"un changement de matériau"* comme objet de la ligne ; exclure
les familles viderait la ligne d'une moitié de son contenu. C'est la forme de la réponse qui
distingue, pas le vocabulaire.

**`EVO-1` tient aussi `FER-1`** : puisque chaque bonne réponse énonce un changement ou une
permanence entre deux états, l'élément *"l'évolution"* est en position de bonne réponse sur chaque
item, et l'élément *"fabrication ou usage"* sur ceux qui disent ce qui a changé dans l'une ou dans
l'autre.

### 3.3 `EVO-2` - la tâche "ce qui n'a pas changé" se joue contre ce qui a changé

> **`EVO-2` - Sur la tâche *"dire ce qui n'a pas changé"*, chaque distracteur est une
> caractéristique vraie de l'une des deux versions de l'objet et fausse de l'autre**, c'est-à-dire
> quelque chose qui a changé. Jamais une fonction fausse. **Et les quatre objets de `SCI-CM1-12`**
> (le vélo, la lampe de poche, le taille-crayon, le ventilateur, `cm1-sciences-objet-technique.yaml`,
> lignes 16 à 18) **ne portent pas cette tâche** : leur fonction y est déjà servie en bonne réponse,
> et un enfant qui a joué ce paquet répondrait de mémoire sans comparer.

Exemple de forme, dont chaque fait se source comme `HIS-3` l'exige : *"La plume d'oie est devenue
le stylo à bille. Qu'est-ce qui n'a pas changé ?"*, bonne réponse *"on s'en sert pour écrire"*,
distracteurs *"on trempe sa pointe dans l'encre"* et *"sa pointe vient d'une plume d'oiseau"* - vrais
de la plume d'oie, faux du stylo à bille. L'enfant doit séparer ce qui demeure de ce qui a changé :
c'est une opération que ni `SCI-CM1-11` ni `SCI-CM1-12` ne demandent.

**Les quatre objets de `SCI-CM1-12` restent admis sur la tâche *"dire ce qui a changé"*** sous
`EVO-1` : l'évolution du vélo, en particulier, est un objet classique du cycle 3, et une bonne
réponse comme *"on fait tourner la roue avec des pédales, et plus en poussant le sol avec ses
pieds"* n'est ni un composant nu ni une fonction.

### 3.4 Ce que `EVO-1` et `EVO-2` ne changent pas

`BIA-3` reste entière : une comparaison de propriétés (*"plus léger"*) est un fait ; *"plus
pratique"*, *"mieux"*, *"moins bien"* sont des jugements et restent interdits. `SCI-3` reste entière :
une date à restituer est une valeur numérique. `SCI-1` et `SCI-4` s'appliquent aux items dont un
distracteur porte une conception fausse courante ; les distracteurs d'`EVO-2` sont des faits vrais
d'un des deux états, pas des conceptions fausses, et `SCI-1` n'y est pas engagée par construction.

**Lisibilité des deux conditions pour un automate** : `EVO-1` se lit sur les chaînes (une bonne
réponse égale à une bonne réponse des deux paquets adjacents est un terme nu) ; `EVO-2`, sur sa
seconde moitié, se lit sur les noms d'objets. Je le signale sans le prescrire : l'encoder ou non, et
comment, relève de `theo-archi` et du développeur affecté. Le sens des distracteurs d'`EVO-2` ne se
lit pas, il se joue.

## 4. Ce que ce document ne fait pas

Il n'écrit aucun item, ne choisit aucun objet au-delà des exemples, ne fixe aucun nombre d'items par
terme ni par tâche au-delà de `FER-1` à `FER-3`, ne rédige pas la mention de couverture de
`MUS-CP-11` (la story me la confie au Done pédagogique), ne touche pas `CYC-1` ni l'adjacence au CP
de `cp-qlm-objet-technique`, et ne prononce aucun Done.

## 5. Ce dont je reste la seule garde

- `MUS-3`, exigences 1 à 5 : qu'une définition soit générale, que le silence soit musical, que les
  distracteurs ne soient pas défendables. Seule l'exigence 6 est lexicale.
- `EVO-1` et `EVO-2` sur le sens : qu'une réponse énonce vraiment une relation entre deux états, et
  que chaque distracteur d'`EVO-2` soit vrai d'un état et faux de l'autre.
- L'exactitude de chaque fait d'histoire des techniques, que je vérifierai contre la source portée
  par le commentaire du paquet (`HIS-3`).

---

**Signé** : `nadia-prof`, 2026-09-30.
