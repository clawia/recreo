# Fiche de conformité - bandit-manchot-tables-multiplication-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-tables-multiplication.yaml`
  (40 items, type `question`), écrit par `marc-dev` (`bc0c69d8`). La fiche avait été ouverte le
  2026-10-02 avant le premier item.
- **Entrée de catalogue** : `bandit-manchot-tables-multiplication-ce1`, titre servi *"Mémoriser ses
  tables de multiplication"*, consigne servie *"Quel est le résultat ?"*, `nombreDeManches: 10`.
- **Ligne déclarée** : `MAT-CE1-07`. **Story** : `S47-MAT-TABLES` (`docs/backlog/backlog.md`,
  section "Sprint 47").
- **Conditions opposables** : `TAB-CE1-1` à `TAB-CE1-6`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-2.md`, section 1.3), **dans leur
  version du 2026-10-02** (sections 2 à 5 ci-dessous).
- **Statut global** : **VALIDÉ** le 2026-10-03 (s48, instance A, second passage), `TAB-CE1-R1`
  levée. Historique : **VALIDÉ SOUS RÉSERVE** le 2026-10-02 (s47, instance A), une réserve non
  bloquante, `TAB-CE1-R1`. Avant ce verdict : aucun verdict, fiche ouverte avant le premier item pour
  que les conditions que le développeur lit soient celles que j'auditerai.
- **Dernière mise à jour** : 2026-10-03 (second passage, s48) : **`TAB-CE1-R1` levée**, aucune
  réserve ouverte. Le verdict du 2026-10-02 reste écrit tel quel.

---

## Conditions

### 1. Ce qui m'est posé, et ce que j'ai vérifié

`theo-archi`, avis de faisabilité de s47 (`squad/rapports/avis-faisabilite-s47-cinq-lots.md`,
section 3, questions Q1 à Q3) : le fait `10 × 1`, que `TAB-CE1-1` exige, n'a qu'un distracteur dans
les quatre familles de `TAB-CE1-6`, qui en exige deux. Q1 est bloquante, Q2 et Q3 ne le sont pas.

**Audit dégradé, et je le déclare** : aucun paquet n'existe, je n'ai rien joué. J'ai lu l'avis en
entier, relu `TAB-CE1-1` à `TAB-CE1-6` et la section 1.4 de l'arbitrage source, et lu dans le code
de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` qu'un item dont le nombre de
distracteurs n'est pas 2 est ignoré (`item.distracteurs().size() != 2`).

**Le constat de `theo-archi` est exact.** Je l'ai refait par un script de poste, non commité : sur
les 40 faits, `10 × 1` est le seul à moins de deux distracteurs, et sa seule valeur est 20.

**Deux omissions de ma part, avant de trancher.** À s43, j'ai vérifié que la famille (c) exclut
`b = 1`. Je n'ai vérifié ni que (a) l'exclut aussi, ni que (d) est vide pour la table de 10. Et le
compte "30 / 20" de ma section 1.4 est fait par le premier facteur, sur 50 couples : il y a 40
faits.

### 2. `TAB-CE1-1` : 40 faits, pas 50

Les 50 couples de `TAB-CE1-1` comptent deux fois les 10 paires dont les deux facteurs sont dans
{2, 3, 4, 5, 10}. **Le paquet couvre les 40 faits distincts, et un item `4 × 3` couvre aussi le
couple `(3, 4)`.** Le facteur de table reste celui de `TAB-CE1-2`. Le texte de la condition ne
change pas. Par la règle de `TAB-CE1-2`, un item par fait donne 57,5 % / 22,5 % / 20 % : les
planchers sont tenus.

### 3. Amendement de `TAB-CE1-6` du 2026-10-02 (`nadia-prof`, s47)

> **Cinquième famille admise : (e) la confusion de `× 1` avec `+ 1`.** Valeur `a + 1`,
> **uniquement si `b = 1`**, quel que soit l'ordre d'écriture (`10 × 1` ou `1 × 10`). Le fichier
> nomme la famille (e) comme les quatre autres. Pour `10 × 1`, les propositions sont 10, 11 et 20.
>
> **Lecture des familles quand les deux facteurs sont dans {2, 3, 4, 5, 10}.** Les deux couples
> appartiennent à l'espace de `TAB-CE1-1`. Les familles (a) à (d) se calculent donc sur l'une ou
> l'autre lecture : pour `5 × 10`, la lecture `(5, 10)` donne 45, 55 et 40, et la lecture
> `(10, 5)` ajoute 60 (`10 × 6`, voisin par excès). Le marqueur nomme la famille, le test la
> recalcule sur l'une des deux lectures.
>
> **Les cinq familles, et seulement celles-là.** Le reste de la clause ne change pas : trois
> propositions deux à deux distinctes, et chaque rang entre 10 % et 50 % des items.

**Pourquoi c'est une vraie erreur de CE1.** *"10 fois 1, 11"* est l'erreur la plus courante sur le
facteur 1 : l'enfant lit `× 1` comme *"un de plus"*. La famille (c) contient déjà l'idée de lire `×`
comme `+`. Je l'avais fermée sur `b = 1` par la clause `a + b < a × b`, écrite pour `2 × 2`, où la
somme égale le produit. Sur `b = 1`, cette exclusion n'avait pas de motif pédagogique : c'est une
conséquence de la clause, pas une décision. Je l'écris comme une famille séparée pour que le marqueur
dise quelle erreur l'enfant a faite, et pour ne pas toucher au texte de (c).

**Ce que la famille change aux autres faits à facteur 1.** Elle leur donne une valeur de plus. Elle
coïncide parfois avec une valeur déjà admise (`3 × 1` : 4 est aussi `4 × 1`, famille (d)) : le
marqueur nomme l'une ou l'autre, la valeur est la même.

**Ce que je n'admets pas.**

| Sortie envisagée | Pourquoi elle n'entre pas |
|---|---|
| `10 × 1` avec un seul distracteur | Deux propositions, c'est une chance sur deux. Et l'item sort du garde-fou de rang sans que rien ne le compte. J'ai refusé cette limite à s47 sur `SOP-CE1-R1`, je ne l'accepte pas ici |
| Retirer `{1, 10}` de `TAB-CE1-1` | Le motif serait le garde-fou, pas l'enfant. `10 × 1` est dans la table de 10, que la ligne nomme. Garder `2 × 1` à `5 × 1` et retirer `10 × 1` n'aurait aucun motif de classe |
| `10 × 1 = 1` (*"fois 1 donne 1"*) | Cette erreur vient de `× 0`, qui n'est pas dans l'espace du CE1 |
| `10 × 1 = 0` | Même motif |

**La famille est tenable.** Même script de poste, sur les 40 faits, avec les cinq familles et la
lecture double ci-dessus :

- aucun fait n'a moins de deux distracteurs ;
- `10 × 1` donne 10 < 11 < 20 : la bonne réponse est toujours la plus petite ;
- rang imposé : la plus petite sur 2 faits (`2 × 1`, `10 × 1`), le milieu sur 9 (`2 × 2`, `3 × 3`,
  `4 × 4`, `5 × 5`, `10 × 6` à `10 × 10`). `5 × 1` n'a plus de rang imposé ;
- rangs atteignables : la plus petite sur 27 faits, le milieu sur 38, la plus grande sur 26.

Mes comptes ne sont pas ceux de `theo-archi` (24 / 38 / 25 sur 39 faits), parce que j'applique les
familles aux deux lectures. La conclusion est la même : un paquet de 40 items à 14 / 13 / 13 est
constructible. C'est le test du paquet qui le mesure.

### 4. `TAB-CE1-3` : l'unité de compte est l'item du paquet (Q2)

**Les items à facteur 1 font au plus 20 % des items du paquet.** *"Une manche sur cinq"* était un
mot impropre de ma part : l'unité de `TAB-CE1-2`, à côté, est l'item, et c'est la même ici.

**Motif.** Le facteur 1 est plafonné parce qu'il ne demande pas de rappel de table, pas parce qu'il
induit une erreur. Une partie de 10 manches qui en tire trois (8,9 % des parties pour 5 items sur
40, chiffre de `theo-archi` que j'ai refait) est plus facile, pas fausse. Cela ne justifie pas une
source de contenu contrainte ni un `ADR`.

**Correction.** *"Pas de plancher : zéro est conforme"* était faux : `TAB-CE1-1` impose les cinq faits
`2 × 1`, `3 × 1`, `4 × 1`, `5 × 1` et `10 × 1`. Le plancher réel est cinq items. Sur 40 items, cela
fait 12,5 %. Un paquet qui répète des items à facteur 1 reste sous 20 %.

### 5. `TAB-CE1-4` : la population du tiers (Q3)

**Le tiers se compte sur les items dont les deux facteurs diffèrent et dont aucun ne vaut 1.** C'est
la lecture de `theo-archi` (section 3.5 de son avis), et c'est celle que j'avais en tête : les carrés
et le facteur 1 n'ont pas de *"second ordre"* à montrer. Quand les deux facteurs sont dans
{2, 3, 4, 5, 10}, le facteur de table est le plus petit (`TAB-CE1-2`) : `4 × 3` compte comme facteur
de table en seconde position. Sur un item par fait, la population est de 30 faits, donc au moins 10
items.

### 6. La ligne peut être engagée dans s47

Q1 est tranchée : **`MAT-CE1-07` peut être engagée**. Ce qui reste à faire, pour d'autres que moi :

| Fait | À qui |
|---|---|
| La story cite les cinq familles de `TAB-CE1-6`, les 40 faits de `TAB-CE1-1`, l'unité item de `TAB-CE1-3` et la population de `TAB-CE1-4`. Le critère d'acceptation *"les 50 produits"* devient *"les 40 faits"* | `camille-po` |
| Le test du paquet recalcule la famille (e) (`a + 1`, `b = 1`) et accepte les deux lectures quand les deux facteurs sont dans l'ensemble | `theo-archi` et le développeur affecté, via `sofia-sm` |

### 7. Ce dont je reste la seule garde

Que les distracteurs soient des erreurs qu'un enfant de CE1 commet sur ces items-là, et pas
seulement des valeurs que la formule autorise. Que l'indice d'un item à facteur 1 ou de la table de
10 aide l'enfant à comprendre son erreur. Je le vérifierai en jouant. Tout le reste des sections 2 à
5 se calcule sur le paquet.

**Identifiants** : aucun neuf, aucun renommé. `TAB-CE1-3`, `TAB-CE1-4` et `TAB-CE1-6` changent de
texte, pas de nom.

**Signé** : `nadia-prof`, s47, 2026-10-02.

---

## Bloc de validation pédagogique

Aucun verdict. Le paquet n'existe pas. *(État à l'ouverture de la fiche, laissé tel quel.)*

### Verdict du 2026-10-02 (`nadia-prof`, s47, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** Les six conditions sont tenues sur les 40 items, dans leur version du
2026-10-02. Une réserve neuve, non bloquante, `TAB-CE1-R1` (section 4). `camille-po` peut passer
`MAT-CE1-07` à `couvert-valide`.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Worktree dédié `s47-nadia-audit-a`, fondé sur `round-two` à
`eef35179`. `bash outils/mvn-jdk21.sh -B clean verify` : `BUILD SUCCESS`, JDK de compilation 21.0.10.
C'est le contenu du job `build` de la CI. Je n'ai lancé aucun autre job et je n'en déduis rien.
`bootstrap/target/recreo.jar` lancé sur un port dédié, dont j'ai vérifié qu'il était servi par mon
propre processus.

*Une première série de trois parties a été jouée sur un port déjà occupé par un autre jar, qui n'était
pas le mien. Je ne la compte pas.*

**Dix parties complètes de dix manches** : quatre justes du premier coup, deux avec une erreur puis
la bonne réponse, deux avec deux erreurs, deux avec trois erreurs jusqu'à la révélation. Après une
erreur : *"Ce n'est pas ça. Essaie encore !"* et l'indice. Après la troisième : *"Appuie sur la bonne
réponse : ..."*. Écran de fin atteint sur les dix parties. Les propositions affichées sont celles du
paquet sur chaque manche jouée. Rendu vu à l'écran à 768 px de large, sur `3 × 1` et `10 × 1` après
une erreur.

Les 40 items recalculés par un script de poste, non commité : produit, famille de chaque
distracteur sur les deux lectures quand les deux facteurs sont dans {2, 3, 4, 5, 10}, comptes de
`TAB-CE1-1` à `TAB-CE1-4`, rangs. `PaquetCe1TablesMultiplicationTest` : 8 tests, 0 échec, dans le
`clean verify` ci-dessus.

#### 2. Les six conditions

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `TAB-CE1-1` | **oui** | 40 items, 40 faits distincts, un item par fait |
| `TAB-CE1-2` | **oui** | facteur de table 2, 5 ou 10 : 23 items (57,5 %) ; 3 : 9 (22,5 %) ; 4 : 8 (20 %) |
| `TAB-CE1-3` | **oui** | 5 items à facteur 1 (12,5 %), les cinq imposés |
| `TAB-CE1-4` | **oui** | 15 des 30 items éligibles écrivent le facteur de table en seconde position (50 %) |
| `TAB-CE1-5` | **oui** | 40 énoncés de forme `a × b = ?`, signe U+00D7, espace insécable devant le `?`. Aucun facteur manquant, aucune situation, aucune formulation par le double. Les indices nomment le double comme stratégie, ce que la condition admet |
| `TAB-CE1-6` | **oui** | les 80 distracteurs correspondent à leur famille déclarée, cinq familles seulement. `10 × 1` : 10, 11, 20. Rangs : la plus petite 14, le milieu 13, la plus grande 13 |

**Exactitude** : 40 produits justes, trois propositions deux à deux distinctes sur chaque item.

**Distracteurs** : ce sont des erreurs de CE1. Le voisin de table (`7 × 4` : 32, 35), la somme
(`2 × 7` : 9), le `+ 1` sur le facteur 1 (`10 × 1` : 11). Aucun distracteur absurde.

#### 3. Niveau, lisibilité, ton

Consigne de quatre mots, énoncé d'une ligne. Les indices emploient des mots de CE1 (*double*,
*moitié*, *table*) et tiennent sur une ligne sur les deux écrans vus. Ceux des tables de 2, 3, 4 et 5
sont mot pour mot ceux que j'ai validés au CM1 dans `cm1-tables-multiplication.yaml`, celui de la
table de 10 en reprend le fond. Aucun biais possible sur des expressions nues.

#### 4. La réserve

**`TAB-CE1-R1` (2026-10-02, non bloquante).** Les cinq items à facteur 1 (`2 × 1`, `3 × 1`,
`4 × 1`, `5 × 1`, `10 × 1`) portent l'indice de leur table, pas un indice sur le facteur 1. Joué :
sur `3 × 1`, un enfant qui répond 4 (il a lu `× 1` comme `+ 1`) lit *"Table de 3 : prends le double
du nombre, puis ajoute le nombre une fois."* Sur `5 × 1`, il lit *"prends la moitié de la table de
10"*. Ces indices mènent au bon résultat, mais ils ne disent pas l'erreur que la famille (e) nomme.
C'est la barre que j'avais écrite avant le paquet (section 7 ci-dessus : *"que l'indice d'un item à
facteur 1 [...] aide l'enfant à comprendre son erreur"*) : elle n'est pas atteinte. Sur `10 × 1`,
*"ajoute un zéro à la fin du nombre"* est en plus ambigu : si *"le nombre"* est 10, l'enfant obtient
100.

**Action** : remplacer l'indice des cinq items à facteur 1 par un indice sur le facteur 1. Forme
déjà validée au CM1 : *"Multiplier par 1, le nombre ne change pas."* Aucun autre item n'est touché.
**Pourquoi ce n'est pas un refus** : les cinq indices sont exacts, l'enfant est révélé après trois
erreurs, et l'item lui-même est juste.

#### 5. Ce dont je reste la seule garde

Que les distracteurs soient des erreurs d'enfant et pas seulement des valeurs que la formule
autorise (section 2), et que l'indice aide à comprendre l'erreur (section 4). Aucun test ne lit un
indice. Le test du paquet garde `TAB-CE1-1` à `TAB-CE1-4` et `TAB-CE1-6`. `TAB-CE1-5` n'a pas de test
propre au paquet : je l'ai vérifiée à la lecture, `TYP-1` est gardée par le test transverse.

**Identifiants** : un neuf, `TAB-CE1-R1`, forme copiée de `SOP-CE1-R1`, alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4, dans le même commit. Aucun renommé.

**Signé** : `nadia-prof`, s47, instance A, 2026-10-02.

### Second passage du 2026-10-03 (`nadia-prof`, s48, instance A) : **VALIDÉ**, `TAB-CE1-R1` levée

**`TAB-CE1-R1` est levée. Aucune réserve ouverte sur ce paquet.** `MAT-CE1-07` reste `couvert-valide`.

**Ce que j'ai fait.** Contenu à `b29c900b` (`lea-dev`), arbre `round-two` à `bc1e7835`. Jar du
`clean verify` de `yanis-qa` à ce commit, YAML du jar vérifié identique à l'arbre, lancé sur un port
dédié servi par mon propre processus. Aucun job de la CI lancé par moi. **Dix-huit parties complètes de
dix manches** par appels HTTP réels : quatorze avec une erreur sur une manche sur deux, quatre avec une
erreur sur chaque item à facteur 1 jusqu'à les avoir tous tirés. Les 40 items ont été tirés ; chacun
des cinq items à facteur 1 a été joué au moins une fois avec une erreur (sur `3 × 1`, la réponse 4 ;
sur `10 × 1`, la réponse 11). Rendu non revu au navigateur : aucun
gabarit n'a changé.

**La barre que j'avais écrite est atteinte, mot pour mot.** `git show b29c900b` : cinq lignes
`indice:`, celles des items `2 × 1`, `3 × 1`, `4 × 1`, `5 × 1` et `10 × 1`, et aucune autre. Après une
erreur sur chacun, l'enfant lit *"Multiplier par 1, le nombre ne change pas."*, la forme validée au
CM1. Sur `3 × 1`, l'enfant qui a répondu 4 (il a lu `× 1` comme `+ 1`) lit désormais ce que son erreur
appelle. L'ambiguïté de *"ajoute un zéro"* sur `10 × 1` a disparu avec l'ancien indice.

**Ce qui change dans ma garde.** `PaquetCe1TablesMultiplicationTest` lit maintenant l'indice des items
à facteur 1 (`tabR1ToutItemAFacteur1PorteLIndiceSurLeFacteur1`) : `TAB-CE1-R1` n'est plus de ma seule
garde. Reste à moi : que les distracteurs soient des erreurs d'enfant, et que les indices des 35
autres items aident à comprendre l'erreur.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s48, instance A, 2026-10-03.
