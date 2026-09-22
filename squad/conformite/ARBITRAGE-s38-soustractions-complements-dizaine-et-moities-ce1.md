# Arbitrage du 2026-09-22 - soustractions, complements a la dizaine pris pour eux-memes et
# moities au CE1 (`MAT-CE1-03`)

- **Auteur** : `nadia-prof`. **Date** : 2026-09-22. **Sprint** : s38.
- **Ce qui m'est pose** : `DEC-0091`, section 4, qui route a moi, avant tout avis de faisabilite de
  `theo-archi`, l'arbitrage pedagogique prealable des trois lots de `docs/backlog/backlog.md`,
  section "Sprint 38" : `S38-SOUSTRACTIONS`, `S38-COMPLEMENTS-DIZAINE`, `S38-MOITIES`. Meme motif
  que celui qui a debloque `S37-1` a s37 - `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-
  l-echelle-100.md` - et je m'y tiens au mot : les questions ouvertes sont de la meme famille
  (bornes de tirage, proportion tolerable des cas degeneres, forme des distracteurs).
- **Ce document ne prononce aucun Done**, n'ecrit ni backlog ni garde-fou, ne cree aucune ligne de
  matrice et ne choisit aucun mecanisme technique.
- **Ce que je n'ai pas pu jouer, et pourquoi ce n'est pas un audit degrade au sens ou je le denonce
  d'habitude.** Aucun paquet de soustractions, de complements a la dizaine ou de moities CE1
  n'existe, et aucun generateur de production ne les tire deja : a la difference de s37, ou j'ai pu
  faire tirer `AdditionsBorneesContentSource` 200 000 fois, il n'y a ici rien a faire tourner. Ce
  document est donc une **specification prealable**, du meme genre que
  `ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` : il pose des conditions decidables par item, il sera
  mis a l'epreuve par le premier paquet livre comme n'importe quelle autre, et je le dis d'avance
  plutot que de laisser croire a une mesure que je n'ai pas faite.

---

## 0. Verification d'ancrage avant toute decision - les trois lots servent-ils bien `MAT-CE1-03`,
## et pas une ligne voisine ?

`MAT-CE1-03` dit, mot pour mot, dans `squad/programme/mathematiques/competences-ce1.md` : *"Calcul
mental - additions et soustractions dans la limite de 100 (compléments à la dizaine, doubles et
moitiés)."* La parenthese n'est pas une decoration, je l'ai deja etabli a s37 (section 2 de
l'arbitrage cite plus haut) : elle nomme les strategies attendues, et un paquet qui ne les sert pas
travaille `MAT-CE1-04`/`MAT-CE1-05` (les operations posees) sous le nom de `MAT-CE1-03`.

**Je verifie que les quatre pieces de s37 et s38, mises bout a bout, couvrent la parenthese sans
trou ni doublon non voulu**, avant de statuer sur les trois qui me sont poses :

- **Additions** (`S37-1`, deja livre, `VALIDE SOUS RESERVE`) : sert deja le double (`ADD-CE1-2`,
  forme 3) et le complement a la dizaine **noye dans une somme** (`ADD-CE1-2`, forme 4,
  `a + b = ?` ou la somme tombe sur un multiple de dix).
- **`S38-COMPLEMENTS-DIZAINE`** : le complement a la dizaine **pris pour lui-meme**, forme
  `a + ? = d`, d different de la forme 4 ci-dessus parce que la cible est donnee et l'inconnue est
  un terme, pas la somme (motif deja ecrit par `camille-po` dans le backlog, et je le confirme
  exact).
- **`S38-MOITIES`** : la moitie, strategie nommee par la parenthese et absente de tout paquet CP
  (verifie par moi : ni "double" ni "moitie" n'apparaissent dans
  `squad/programme/mathematiques/competences-cp.md`). C'est une strategie neuve au CE1, pas un
  prolongement d'un acquis CP.
- **`S38-SOUSTRACTIONS`** : la soustraction generale dans la limite de 100. Elle n'a **pas** a
  reproduire le complement a la dizaine ni le double comme categorie a plancher garanti : ces deux
  strategies ont deja leur propre lot dedie (additions pour le double, `S38-COMPLEMENTS-DIZAINE`
  pour le complement). Lui imposer les deux en plus ferait de la meme strategie une exigence
  triplement comptee, sans gain pedagogique. Ce que je demande a ce lot est ecrit en section 1.

**Conclusion de l'ancrage : les trois lots servent bien `MAT-CE1-03`, chacun une part disjointe de
sa parenthese, et aucun ne glisse vers `MAT-CE1-04`/`MAT-CE1-05` (les operations posees avec
retenue) tant qu'il reste du calcul mental sur des faits, pas un algorithme colonne par colonne.**
Je le revérifierai en jouant le paquet livre, comme je l'ai fait pour les additions.

---

## 1. `S38-SOUSTRACTIONS` - soustractions dans la limite de 100

### 1.1 Ce que l'AC du backlog fixe deja, et que je ne rouvre pas

`a - b`, entiers, `0 <= b <= a <= 100`, jamais de resultat negatif. C'est le bon perimetre pour
"dans la limite de 100" et je ne le retouche pas.

### 1.2 `SOU-CE1-1` - deux plafonds sans plancher, sur les cas degeneres

> **`SOU-CE1-1`** - dans un paquet de soustractions de CE1 : la proportion de tirages a
> **soustracteur nul** (`b = 0`) est **au plus 5 %**, et la proportion de tirages a **resultat
> nul** (`a = b`, donc `a - a = 0`) est **au plus 5 %**, chacune independamment. **Il n'y a pas de
> plancher pour l'une ni pour l'autre : 0 % est conforme.**

**Motif, transpose de `ADD-CE1-1` sans en changer le fond.** `a - 0 = a` et `a - a = 0` sont des
proprietes du zero deja rencontrees au CP - le paquet CP de soustractions couvre deja le resultat 0
via `7 - 7` et `5 - 5` (`FICHE-bandit-manchot-soustractions-cp.md`, "Ce qui a ete verifie"). Aucune
des deux n'est nommee par la parenthese de `MAT-CE1-03` ("compléments à la dizaine, doubles et
moitiés") : ce ne sont ni un complement, ni un double, ni une moitie, seulement une propriete deja
acquise. Un plancher les imposerait a depenser une manche sur vingt pour reviser du CP dans un
paquet de CE1 - exactement le raisonnement de `ADD-CE1-1`, applique ici a la soustraction plutot
qu'a l'addition, et j'assume l'identite de raisonnement plutot que de la deguiser en nuance.

### 1.3 `SOU-CE1-2` - une table de repartition par forme, decidable par item

> **`SOU-CE1-2`** - dans un paquet de soustractions de CE1, la repartition des tirages par forme
> tient les bandes ci-dessous. Un couple `(a, b)` appartient a la **premiere** case dont il remplit
> la condition, jamais a deux.

| # | Forme | Condition sur `(a, b)` | Cible |
|---|---|---|---|
| 1 | Soustracteur nul | `b = 0` | 0 % a 5 % (`SOU-CE1-1`) |
| 2 | Resultat nul | `a = b` (et donc `b != 0` par priorite) | 0 % a 5 % (`SOU-CE1-1`) |
| 3 | Difference facile, hors 1 et 2 | `a - b <= 20` | 10 % a 25 % |
| 4 | Soustraction d'un nombre rond, hors 1 a 3 | `b` multiple non nul de 10 | 10 % a 20 % |
| 5 | Un chiffre soustrait, hors 1 a 4 | `b < 10` | 15 % a 30 % |
| 6 | Deux a deux chiffres sans emprunt, hors 1 a 5 | `(a mod 10) >= (b mod 10)` | 10 % a 30 % |
| 7 | Deux a deux chiffres avec emprunt, hors 1 a 6 | `(a mod 10) < (b mod 10)` | 15 % a 30 % |

**Satisfaisabilite, verifiee et pas supposee** : somme des bornes basses = 60, somme des bornes
hautes = 145. Une repartition qui totalise 100 existe a l'interieur des sept bandes.

**Ce que chaque ligne apporte :**

- **Ligne 3, plancher 10 %** : un point d'entree par partie, meme role que la ligne "somme <= 20"
  d'`ADD-CE1-2`. Plafond a 25 % pour la meme raison inverse : au-dela, le paquet redevient le
  paquet de CP (`MAT-CP-05` va jusqu'a 10) reetiquete CE1.
- **Ligne 4** : la soustraction d'un nombre rond (`87 - 30`) est une strategie de calcul mental a
  part entiere, rapide et fiable ; elle merite un plancher pour ne pas rester un accident du
  tirage.
- **Ligne 7, plancher 15 %** : **c'est la ligne ou la strategie du "complement a la dizaine"
  s'applique reellement a la soustraction** - reculer d'abord jusqu'a la dizaine inferieure de `a`,
  puis retrancher le reste (`52 - 27` : `52 - 22 = 30`, puis `30 - 5 = 25`). Je ne cree pas de
  categorie separee nommee "complement a la dizaine" dans cette table parce que `S38-COMPLEMENTS-
  DIZAINE` sert deja cette strategie pour elle-meme (section 0) ; je m'assure seulement que la
  soustraction avec emprunt, qui est l'endroit ou elle se rejoue naturellement, n'est pas rare.
  Plafond a 30 % pour la meme raison que la ligne 8 d'`ADD-CE1-2` : au-dela, le paquet glisse vers
  `MAT-CE1-05` (la soustraction posee) jouee mentalement, plus dure que ce que la ligne declaree
  demande.

### 1.4 Distracteurs

> **Au moins un distracteur par item correspond a une erreur identifiable parmi** : la somme au
> lieu de la difference (`a + b`), le second terme recopie (`b`), ou, **pour les items de forme 7
> (avec emprunt) uniquement**, l'erreur d'"emprunt oublie" : `10 * (dizaine(a) - dizaine(b)) +
> |unite(a) - unite(b)|`. Tous les distracteurs sont des entiers positifs, distincts entre eux et
> distincts de la bonne reponse ; si une des formules ci-dessus coincide avec la bonne reponse ou
> avec un autre distracteur deja choisi, un distracteur de remplacement est pris.

**Motif du troisieme cas.** L'"emprunt oublie" - soustraire chiffre par chiffre sans emprunter,
donc `52 - 27` traite comme `(5-2)` et `|2-7|` -> `35` - est l'erreur la plus frequente et la plus
identifiable a cette echelle, symetrique de la "retenue oubliee" que l'addition sert deja
(`ADD-CE1-2`, note de theo-archi citee dans son avis de faisabilite de s37). Elle n'a de sens que
sur les items avec emprunt : l'imposer sur les formes 1 a 6 produirait un distracteur egal ou
absurde.

---

## 2. `S38-COMPLEMENTS-DIZAINE` - `a + ? = d`

### 2.1 `CDZ-CE1-1` - bornes de `a` et de `d`

> **`CDZ-CE1-1`** - `a` est un entier tel que `1 <= a <= 99` et `a` n'est **pas** un multiple de
> dix. `d` est le plus petit multiple de dix strictement superieur a `a` (donc `d` va de 10 a 100
> inclus). La bonne reponse est `d - a`, un entier de 1 a 9.

**Motif de l'exclusion des multiples de dix.** Si `a` etait lui-meme un multiple de dix
(`40 + ? = 50`), la reponse (10) ne demande aucune strategie : l'enfant lit deja `a` sur une
dizaine ronde. C'est le meme raisonnement que celui qui exclut l'operande nul des cas utiles à
s37 : un cas degenere n'est pas interdit en soi, mais il ne doit pas porter la competence.
**Ici je vais plus loin qu'un simple plafond et je l'exclus totalement**, parce que ce lot n'a
qu'une seule forme d'item - contrairement a la table `ADD-CE1-2` qui melange plusieurs formes, un
item degenere pris sur 100 % du paquet couterait bien plus cher qu'un item degenere pris sur un
douzieme d'un paquet d'additions.

**Motif du "plus petit multiple de dix superieur"**, plutot que tout multiple de dix au-dessus de
`a`. La strategie que la parenthese de `MAT-CE1-03` nomme est "le complement **a la dizaine**" -
la distance jusqu'a la dizaine suivante, pas jusqu'a une dizaine quelconque plus loin. Autoriser
`47 + ? = 80` deplacerait la tache vers une soustraction generale a deux chiffres, deja servie par
la section 1 ; cela diluerait la strategie specifique que ce lot existe pour isoler.

### 2.2 `CDZ-CE1-2` - couverture de la table et repartition des dizaines

> **`CDZ-CE1-2`** - sur l'ensemble des items du paquet : chacune des neuf valeurs de complement (1
> a 9) apparait au moins une fois, et les valeurs de `a` sont reparties sur au moins cinq dizaines
> differentes parmi les neuf possibles (10-19, 20-29, ..., 90-99).

**Motif, transpose de `DEC-0020` (couverture complete de la table des complements a 10 au CP).**
Un paquet qui ne servirait que les complements faciles (8 ou 9, proches d'une dizaine deja atteinte)
ne couvrirait pas la table ; un paquet qui ne servirait qu'une seule dizaine (par exemple toujours
les 40) laisserait croire a l'enfant que la strategie ne s'applique qu'a un cas particulier plutot
qu'a n'importe quel nombre a deux chiffres.

### 2.3 Distracteurs, avec un cas de collision a traiter explicitement

**Amendement du 2026-09-22, en reponse au routage de `sofia-sm`
(`squad/sprints/s38-fermeture-mat-ce1-03/routage-complements-dizaine-et-adr-mecanisme.md`), sur le
fait demontre - pas suppose - par `theo-archi` (avis de faisabilite, section 10) : la clause
d'origine, ci-dessous barree, prescrivait `d` comme second distracteur. Comme `CDZ-CE1-1` borne la
bonne reponse `d - a` a un entier de 1 a 9 et `d` lui-meme a un multiple de dix d'au moins 10, `d`
est **systematiquement** la plus grande des trois propositions, jamais la bonne reponse : le rang
"la plus grande" vaudrait 0 % sur tout paquet conforme a la lettre, ce qui fait rougir
`DETTE_DE_SEUIL_MINIMUM` sans recours (cliquet a son plafond de trois noms). Cette clause n'a
jamais ete mise a l'epreuve d'un paquet livre : aucun Done pedagogique n'a ete prononce dessus. La
corriger ici n'est donc pas revenir sur une barre deja franchie - c'est fermer une specification
qui s'est revelee inexecutable avant tout code, exactement le role que ce document se donne
lui-meme en section 0 ("il sera mis a l'epreuve... je le dis d'avance").**

> ~~**Un distracteur est `unite(a)`** (l'enfant repond le chiffre des unites de `a`, confondant "ce
> qui est deja la" avec "ce qu'il manque"), **sauf s'il est egal a la bonne reponse** - ce qui
> arrive exactement quand `unite(a) = 5` (`a + ? = d` avec `unite(a) = 5` donne un complement de
> 5 aussi) ; dans ce cas, ce distracteur est remplace par `unite(a) - 1` s'il est positif, sinon
> par `d`. **Un second distracteur est `d`** (l'enfant repond la cible plutot que ce qu'il faut lui
> ajouter). Les deux distracteurs sont toujours des entiers positifs distincts de la bonne reponse
> et l'un de l'autre.~~

> **Un distracteur est `unite(a)`** (l'enfant repond le chiffre des unites de `a`, confondant "ce
> qui est deja la" avec "ce qu'il manque"), **sauf s'il est egal a la bonne reponse** - ce qui
> arrive exactement quand `unite(a) = 5` ; dans ce cas, ce distracteur est remplace par
> `unite(a) - 1`, toujours positif dans ce cas precis (`unite(a) = 5` donne `4`).
>
> **Un second distracteur est `d - a - 1`** - l'enfant compte le complement en s'arretant un pas
> trop tot (il compte `a+1, a+2, ..., d-1` et oublie le dernier pas jusqu'a `d`), l'erreur de
> decompte d'un rang deja acceptee comme distracteur valide sur les complements a 10 du CP
> (`FICHE-bandit-manchot-complements-10-cp.md`, meme famille citee en section 3.4 pour les
> moities de ce document) - **sauf dans deux cas ou il est remplace par `d - a + 1`**, le meme
> decompte d'un rang mais par exces (l'enfant compte un pas de trop, `a+1, ..., d, d+1`) plutot
> que par defaut :
> - quand `d - a - 1` n'est pas strictement positif, ce qui arrive exactement quand `d - a = 1`
>   (donc `unite(a) = 9`) ;
> - quand `d - a - 1` coinciderait avec le premier distracteur une fois sa propre collision
>   traitee, ce qui arrive exactement quand `unite(a) = 5` (`d - a - 1 = 4`, et le premier
>   distracteur, deja remplace par `unite(a) - 1` pour la raison ci-dessus, vaut aussi 4).
>
> Les deux distracteurs sont toujours des entiers positifs, distincts entre eux et de la bonne
> reponse.

Je nomme la collision `unite(a) = 5` explicitement parce qu'un item ou elle n'est pas traitee
livrerait deux fois la meme valeur sous deux libelles differents - un defaut que je refuserais
immediatement en jouant, exactement du meme ordre que les defauts que ma porte existe pour
attraper. Le meme motif justifie de nommer les deux cas de repli du second distracteur plutot que
de les laisser decouvrir a l'ecriture du premier item ou ils se produisent.

**Verification que la correction leve reellement le blocage, faite a la main sur les neuf valeurs
possibles de `unite(a)` (1 a 9), puisque `CDZ-CE1-2` impose que chacune apparaisse au moins une
fois et que le rang ne depend que de `unite(a)`, jamais de la dizaine de `a`.**

| `unite(a)` | bonne reponse (`d-a`) | 1er distracteur | 2e distracteur | rang de la bonne reponse |
|---|---|---|---|---|
| 1 | 9 | 1 | 8 | plus grande |
| 2 | 8 | 2 | 7 | plus grande |
| 3 | 7 | 3 | 6 | plus grande |
| 4 | 6 | 4 | 5 | plus grande |
| 5 | 5 | 4 | 6 | intermediaire |
| 6 | 4 | 6 | 3 | intermediaire |
| 7 | 3 | 7 | 2 | intermediaire |
| 8 | 2 | 8 | 1 | intermediaire |
| 9 | 1 | 9 | 2 | plus petite |

Sur un paquet qui respecte `CDZ-CE1-2` en repartissant a peu pres egalement ses neuf valeurs de
complement, la bonne reponse est "la plus petite" sur 1 item sur 9 (~11,1 %), "intermediaire" sur 4
sur 9 (~44,4 %) et "la plus grande" sur 4 sur 9 (~44,4 %) : **les trois rangs restent au-dessus du
seuil de 10 % de `DETTE_DE_SEUIL_MINIMUM`, et aucun n'atteint la majorite de 50 % qui ferait rougir
le premier garde-fou.** Le cas signale par `theo-archi` (au moins un item sur dix ou les deux
distracteurs sont inferieurs a la bonne reponse) est couvert plus largement que le minimum demande
: les quatre lignes ou la bonne reponse est "la plus grande" (`unite(a)` de 1 a 4) le verifient par
construction, puisque c'est la definition meme de ce rang.

---

## 3. `S38-MOITIES` - moitie d'un nombre

### 3.1 Ce que l'AC du backlog fixe deja, et que je ne rouvre pas

Le sixieme critere d'acceptation du lot exige explicitement que la bonne reponse soit "la moitie de
`n`, un entier". **Cela tranche deja la question de parite que le backlog me posait** : `n` doit
etre pair, sans exception, sinon aucune reponse entiere n'existe. Je le dis pour que ce ne soit pas
laisse en suspens : il n'y a pas de "moitie non entiere" a ce niveau, ni tolerance d'arrondi.

### 3.2 `MOI-CE1-1` - bornes de `n` et plafond du cas degenere

> **`MOI-CE1-1`** - `n` est un entier pair, `0 <= n <= 100`. La proportion de tirages a `n = 0`
> (cas degenere, moitie de zero) est **au plus 5 %**, sans plancher.

**Motif du plafond sur `n = 0`.** Meme famille que `SOU-CE1-1` : `0` est un cas degenere qui ne
demande aucune strategie de moitie, et rien dans la parenthese de `MAT-CE1-03` ne le nomme.

**Motif de la borne haute a 100.** "Dans la limite de 100" gouverne l'ensemble de la ligne
`MAT-CE1-03`, moities comprises ; je ne vois aucune raison de la restreindre davantage ni de
l'etendre.

### 3.3 `MOI-CE1-2` - une table de repartition, parce que "moities" n'est pas un acquis du CP

> **`MOI-CE1-2`** - dans un paquet de moities de CE1, la repartition des tirages par forme tient
> les bandes ci-dessous. Un `n` appartient a la premiere case dont il remplit la condition.

| # | Forme | Condition sur `n` | Cible |
|---|---|---|---|
| 1 | Degenere | `n = 0` | 0 % a 5 % (`MOI-CE1-1`) |
| 2 | Repertoire de base, hors 1 | `2 <= n <= 20` | 30 % a 50 % |
| 3 | Nombre rond, hors 1 et 2 | `n` multiple de 10 (`30` a `100`) | 15 % a 30 % |
| 4 | Deux chiffres general, hors 1 a 3 | le reste (`22` a `98` pair, non multiple de 10) | 15 % a 35 % |

**Satisfaisabilite** : bornes basses = 45, bornes hautes = 115. Realisable.

**Motif du plancher eleve sur la ligne 2 (30 % a 50 %).** Contrairement au double et au complement
a la dizaine, la moitie n'a **aucun acquis CP** a s'appuyer dessus (verifie section 0). Le CE1 doit
donc construire, pas seulement consolider, un repertoire de moities de petits nombres (moitie de 6,
de 14, de 18) avant d'en attendre l'usage sur des nombres plus grands. C'est le meme raisonnement
que celui qui a fixe le plancher CP de `DEC-0020` sur la table des complements a 10 : une table
neuve merite d'etre vue en repertoire, pas seulement croisee au hasard d'un tirage plus large.

**Motif de la ligne 3.** La moitie d'un nombre rond (`moitie de 80 = 40`) est la strategie qui
prolonge directement le repertoire de base vers l'echelle de la centaine, sans exiger de
decomposition dizaine/unite. Elle merite un plancher pour ne pas rester accidentelle.

**Motif de la ligne 4, plafond a 35 % et pas plus.** La moitie d'un nombre a deux chiffres non rond
(`moitie de 58 = 29`) demande de decomposer en dizaines et unites (`58 = 50 + 8`, moitie
`25 + 4 = 29`) - une vraie competence de CE1, appuyee sur `MAT-CE1-01` (decomposition des nombres
jusqu'a 1000), mais plus couteuse. Je la laisse presente et significative, sans la laisser dominer
un paquet qui doit d'abord construire le repertoire de base.

### 3.4 Distracteurs

**Amendement du 2026-09-22, a l'audit du paquet livre (`S38-MOITIES`), meme famille de correction
que la section 2.3 - une clause fermee avant tout code, revelee inexecutable par le premier paquet
qui la met a l'epreuve, et non un durcissement retroactif d'une barre deja franchie.** La clause
d'origine, ci-dessous barree, ne nommait que trois formes : le double, la moitie plus un, la moitie
moins un. **Avec ces trois seules formes, la bonne reponse ne peut mathematiquement jamais etre la
plus grande des trois propositions** : le double vaut toujours plus que la moitie (`n x 2 > n / 2`
des que `n > 0`), donc tout item qui l'utilise range la bonne reponse en position la plus petite ou
intermediaire ; et la paire restante (`moitie plus un`, `moitie moins un`) encadre la bonne reponse
sans jamais la laisser en tete non plus. Sur un paquet entier, le rang "plus grande" resterait a 0 %
- exactement le defaut que `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` existe pour
attraper (section 2.3 le cite, le garde-fou lui-meme cite mon propre REFUS de `S12-3`), et je
l'aurais moi-meme refuse en le decouvrant a l'ecran. Je ne l'ai pas vu en ecrivant la clause parce
que je n'ai pas refait, pour les moities, la verification a la main que j'avais faite pour les
complements a la dizaine en section 2.3 - c'est une omission de ma part, que je corrige ici plutot
que de la laisser peser sur l'increment qui l'a heritee.

> ~~**Au moins un distracteur par item correspond a une erreur identifiable parmi** : le double au
> lieu de la moitie (`n x 2`), la moitie plus un (`n / 2 + 1`), ou la moitie moins un (`n / 2 - 1`,
> uniquement si positive ou nulle). Tous les distracteurs sont des entiers positifs, distincts
> entre eux et de la bonne reponse.~~

> **Au moins un distracteur par item correspond a une erreur identifiable parmi** : le double au
> lieu de la moitie (`n x 2`), la moitie plus un (`n / 2 + 1`), la moitie moins un (`n / 2 - 1`,
> uniquement si positive ou nulle), ou **la moitie moins deux** (`n / 2 - 2`, uniquement si positive
> ou nulle, et uniquement associee a `n / 2 - 1` comme second distracteur). Tous les distracteurs
> sont des entiers positifs, distincts entre eux et de la bonne reponse.

**Motif du "double au lieu de la moitie".** C'est l'erreur de confusion d'operation la plus
frequente sur cette competence, symetrique de la "somme au lieu de la difference" deja acceptee
pour les soustractions (section 1.4) et pour le CP (`FICHE-bandit-manchot-soustractions-cp.md`).
**Motif du "plus ou moins un", et de la quatrieme forme "moins deux" qu'il entraine.** Erreur de
decompte d'un rang, deja utilisee comme distracteur valide sur les complements a 10 du CP
(`FICHE-bandit-manchot-complements-10-cp.md`, "le nombre de depart recopie... plus le complement a
9" - meme famille, erreur d'un rang plutot que de nature) et sur les complements a la dizaine du
CE1 (section 2.3, `d - a - 1`). La paire `(moitie moins un, moitie moins deux)` est la meme famille
d'erreur de decompte, portee un cran plus loin pour etre la seule combinaison, parmi celles que ces
quatre formes permettent, ou la bonne reponse peut se trouver au rang "plus grande" - ce qui est
precisement ce qu'elle doit faire pour que le paquet ne soit pas structurellement condamne a ne
jamais y placer la bonne reponse.

**Verification que la correction couvre reellement les trois rangs, faite sur les six combinaisons
que les quatre formes permettent** (chaque paire de deux distracteurs distincts parmi les quatre,
en excluant les paires non demandees par la clause) :

| Paire de distracteurs | Rang de la bonne reponse |
|---|---|
| double, moitie + 1 | plus petite |
| double, moitie - 1 | intermediaire |
| double, moitie - 2 | intermediaire |
| moitie + 1, moitie - 1 | intermediaire |
| moitie + 1, moitie - 2 | intermediaire |
| **moitie - 1, moitie - 2** | **plus grande** |

Seule la derniere paire atteint le rang "plus grande" ; c'est pourquoi la clause corrigee la
reserve nommement a cet usage plutot que de laisser un generateur la combiner autrement.

**Ce que je ne fais pas ici.** Je ne reproche rien au paquet livre, qui a applique cette paire
exactement comme la clause corrigee le demande, avant meme que je ne l'ecrive noir sur blanc - c'est
son commit qui m'a fait relire ma propre section 3.4 et y trouver le trou. Voir
`squad/conformite/FICHE-bandit-manchot-moities-ce1.md` pour la verification chiffree du paquet
livre contre cette clause corrigee.

---

## 4. Ce que ces trois arbitrages ne font pas

- Ils ne fixent aucun mecanisme technique (paquet statique ou generatif, reutilisation ou creation
  d'une classe) : reste a `theo-archi`.
- Ils ne fixent aucun texte d'indice ni de consigne : je les jugerai a l'audit, comme pour les
  additions, ou je n'avais rien pu arbitrer d'avance non plus sur ce point.
- Ils ne prononcent aucun Done, ni ne deplacent aucune ligne de
  `squad/programme/MATRICE-couverture.md`. `MAT-CE1-03` ne passera a `couvert-valide` que lorsque
  les quatre pieces (additions, soustractions, complements a la dizaine, moities) auront chacune
  leur Done pedagogique prononce, comme le repete chaque critere du backlog.
- Ils ne rouvrent ni `MAT-CE1-04`, ni `MAT-CE1-05`, ni aucune autre ligne du referentiel.

## 5. Ce dont je reste la seule garde, et qu'aucun automate ne verra

Toutes les clauses ci-dessus (`SOU-CE1-1`, `SOU-CE1-2`, `CDZ-CE1-1`, `CDZ-CE1-2`, `MOI-CE1-1`,
`MOI-CE1-2`) sont **decidables sur l'item seul ou sur la liste des items**, donc automatisables sur
le meme patron que `ADD-CE1-1`/`ADD-CE1-2`. Tant qu'aucun garde-fou ne les execute, c'est moi qui
les tiens, en comptant. Restent en plus dans ma seule main, comme pour les additions :

1. **Que chaque paquet livre travaille reellement la sous-partie qu'il declare**, et pas
   `MAT-CE1-04`/`MAT-CE1-05` sous un autre nom - le glissement le plus couteux, deja nomme a s37.
2. **La progression a l'interieur d'une partie** : ces tables sont des distributions, pas un ordre
   de manches.
3. **La lisibilite reelle, le vocabulaire et l'exactitude** de chaque enonce s'il porte un champ
   `enonce` distinct de l'expression elle-meme, et le respect de `LON-CE1-1`/`LON-CE1-2` sur ce
   champ.
4. **L'exactitude arithmetique** de chaque item, de chaque distracteur, et la coherence de
   l'indice.
5. **La coherence entre la consigne du jeu et la forme des enonces** - point deja releve comme
   reserve, jamais comme motif, sur `bandit-manchot-soustractions-cp` ; je verifierai qu'il n'est
   pas reproduit au CE1.

## 6. Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| `SOU-CE1-1`, `SOU-CE1-2`, `CDZ-CE1-1`, `CDZ-CE1-2`, `MOI-CE1-1`, `MOI-CE1-2` sont automatisables, avec les tables des sections 1 a 3 comme specification | `theo-archi`, via `sofia-sm` | Je dis la propriete, la forme du test et le mecanisme lui appartiennent |
| Choisir entre un mecanisme statique et un mecanisme generatif pour chacun des trois lots, et affecter nominativement le developpement qui en decoule | `theo-archi` puis `sofia-sm` (`DEC-0091`, section 2.2) | Decision technique et de process, hors de ma main |
| Les six identifiants ci-dessus sont alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, dans le meme commit que ce document | moi-meme, fait | `PAR-5` |

Les trois lots sont **arbitrables et donc engageables** : `theo-archi` peut rendre son avis de
faisabilite sur chacun. Aucun des trois n'aura de Done pedagogique avant que je n'aie joue le
paquet livre, exactement comme pour les additions a s37.
