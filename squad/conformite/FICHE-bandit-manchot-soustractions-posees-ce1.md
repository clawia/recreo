# Fiche de conformité - bandit-manchot-soustractions-posees-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/ce1-soustractions-posees.yaml`
  (18 items, type `question`), écrit par `marc-dev` (commit `712f395c`). Entrée de catalogue
  `bandit-manchot-soustractions-posees-ce1`, titre servi *"Les soustractions posées"*, consigne
  servie *"Pose l'opération, puis choisis le bon résultat."*, `nombreDeManches: 10`. Cette ligne
  disait, le 2026-10-01 : *"aucun à ce jour. Le paquet est à écrire en s46."*
- **Ligne déclarée** : `MAT-CE1-05`. **Story** : `S46-MAT-SOUSTRACTION` (`docs/backlog/backlog.md`,
  section "Sprint 46").
- **Conditions opposables** : `SOP-CE1-1` à `SOP-CE1-6`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 5.2), **`SOP-CE1-5`
  dans sa version amendée du 2026-10-01** (section 2 ci-dessous).
- **Statut global** : **VALIDÉ** le 2026-10-03 au troisième passage par `nadia-prof` (s48, instance
  A), `SOP-CE1-R1` levée sur le paquet repris. Historique : **VALIDÉ SOUS RÉSERVE** le 2026-10-02 au
  second passage (s46,
  instance A), une réserve neuve (`SOP-CE1-R1`),
  après avoir rejoué le jar servi sur le paquet entier. Premier passage du même jour : **REFUSÉ**
  (`SOP-CE1-4` non tenue sur deux items), verdict conservé tel quel dans le bloc de validation en
  bas de fiche. Fiche ouverte le 2026-10-01 avant le premier
  item, par l'amendement de `SOP-CE1-5`, pour que la condition que le développeur lit soit celle que
  j'auditerai. **Arbitrage de `SOP-CE1-R1` du 2026-10-02 (s47)** : cinquième forme admise (le
  décalage des colonnes), obligatoire sur les items sans emprunt ; réserve ouverte jusqu'au rejeu
  d'un paquet repris (dernière section).
- **Dernière mise à jour** : 2026-10-03 (troisième passage, s48).

---

## Conditions

### 1. Ce qui m'est posé, et ce que j'ai vérifié

`theo-archi`, avis de faisabilité de s46 (`squad/rapports/avis-faisabilite-s46-cinq-lots.md`, Q1,
sections 3.2 à 3.4) : les trois formes de `SOP-CE1-5` donnent toujours une valeur supérieure à la
bonne réponse, donc la bonne réponse est la plus petite sur 100 % des items à trois propositions, et
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` est rouge par construction.

**Audit dégradé, et je le déclare** : aucun paquet n'existe, je n'ai rien joué. J'ai lu l'avis en
entier, relu `SOP-CE1-1` à `SOP-CE1-6` et `ADP-CE1-5` à leur source, la section 2 de
`ARBITRAGE-PREALABLE-s45-cinq-lots.md`, et les seuils du garde-fou de rang dans son code
(`SEUIL_MAJORITE = 0.5`, `SEUIL_MINIMUM = 0.10`).

**Le constat de `theo-archi` est exact.** Je l'ai refait par un script de poste, non commité, sur
un périmètre plus étroit que le sien (`10 <= b <= a <= 999`, d'où un autre compte de couples) :
329 670 couples portent au moins un emprunt, et sur ces couples aucune valeur des trois formes n'est
inférieure ou égale à la bonne réponse.

**Une omission de ma part, avant de trancher.** `SOP-CE1-4` renvoie à `ADP-CE1-4` ; `SOP-CE1-5` est
la clause sœur d'`ADP-CE1-5`. Or `ADP-CE1-5` admet des erreurs dans les deux sens : la retenue
oubliée (en moins) et la retenue reportée deux fois (en plus). En soustraction, je n'ai écrit que
des erreurs dans un seul sens : l'emprunt qu'on ne fait pas, ou qu'on fait sans le reporter. Il
manque l'erreur où l'enfant reporte trop. Je ne l'ai pas vue en s43 ; c'est le garde-fou qui me la
montre, mais elle existe en classe sans lui.

### 2. Amendement de `SOP-CE1-5` du 2026-10-01 (`nadia-prof`, s46)

> **Quatrième forme admise : l'emprunt reporté deux fois.** Après un emprunt, la colonne suivante
> perd 2 au lieu de 1. Selon la technique apprise en classe, c'est le même geste :
>
> - **par compensation** (*"j'ajoute 10 en haut, je retiens 1 en bas"*) : l'enfant écrit le petit
>   `1` sous la colonne suivante, puis l'ajoute encore en disant *"je retiens 1"* ;
> - **par cassage** : l'enfant barre le chiffre du haut de la colonne suivante, écrit un de moins,
>   puis retire encore 1 en calculant cette colonne.
>
> Pour un emprunt fait sur la colonne de rang `i`, la valeur est `a - b - 10^(i+1)` : **toujours
> inférieure à la bonne réponse**, et le chiffre des unités est intact.
>
> **Portée.** Comme pour `ADP-CE1-5` (s45, section 2.2), la forme porte sur un emprunt ou sur
> plusieurs : sur un item à deux emprunts, elle peut porter sur celui des unités, sur celui des
> dizaines, ou sur les deux. Le marqueur du paquet nomme la forme et le ou les emprunts concernés.
>
> **Admission sur un item.** La forme n'est admise que si la colonne qui reçoit l'emprunt en double
> se calcule **sans emprunt de plus ni de moins** que dans le calcul juste. Sinon l'item porterait
> deux erreurs enchaînées, et l'enfant qui l'a choisi ne pourrait pas reconnaître la sienne.
> `SOP-CE1-4` s'applique comme aux trois autres formes : une forme qui change le nombre de chiffres
> n'est pas admise sur cet item, et une autre forme de la liste est prise.
>
> **Les quatre formes, et seulement celles-là** : l'emprunt oublié, l'emprunt non reporté, la somme
> au lieu de la différence, l'emprunt reporté deux fois. Le reste de la clause ne change pas.
>
> **Le rang, sur le paquet entier.** Sur les items sans emprunt, seule la somme est disponible :
> ces items portent deux propositions, et le garde-fou de rang ne les lit pas. Je l'accepte, parce
> que `SOP-CE1-2` exige ces items. Mais la propriété qui a fait naître ce garde-fou (refus de
> `S12-3`) vaut pour eux aussi : **sur le paquet entier, items à deux propositions compris, choisir
> toujours la plus petite proposition ne donne pas la bonne réponse sur plus de la moitié des
> items.**

Exemples, recalculés par le même script :

| Item | Bonne réponse | Emprunt oublié | Emprunt non reporté | Somme | Emprunt reporté deux fois |
|---|---|---|---|---|---|
| `52 - 27` | 25 | 35 | 35 | 79 | 15 |
| `347 - 129` | 218 | 222 | 228 | 476 | 208 |
| `432 - 178` | 254 | 346 | 364 | 610 | 244 (unités), 154 (dizaines), 144 (les deux) |
| `613 - 247` | 366 | 434 | 476 | 860 | 356 (unités), 266 (dizaines), 256 (les deux) |

Sur `52 - 27`, l'emprunt oublié et l'emprunt non reporté coïncident (35) : la dernière phrase de la
clause s'applique, une autre forme est prise. Sur `432 - 178`, `244` et `154` font de la bonne
réponse la plus grande.

### 3. Ce que l'amendement fait au garde-fou de rang

Le garde-fou exige, sur les items à trois propositions, qu'aucun rang ne dépasse 50 % et qu'aucun ne
tombe sous 10 %. Donc au moins la moitié des items portent un distracteur plus petit que la bonne
réponse, et au moins 10 % en portent deux.

- **Un distracteur plus petit** : sur le même périmètre, 260 640 couples à emprunt sur 329 670
  admettent au moins une forme "reporté deux fois" qui tient les règles d'admission et
  `SOP-CE1-4`.
- **Deux distracteurs plus petits** : seulement sur un item à deux emprunts. 67 725 couples à deux
  emprunts sur 110 970 en admettent deux.

La contrainte est tenable. **Une conséquence de fabrication, pas une barre neuve** : `SOP-CE1-2` ne
demande qu'un item à deux emprunts, mais le rang "la plus grande" à 10 % en demandera
vraisemblablement plus. C'est le test du paquet qui compte, pas moi.

**Un point que je regarderai à l'audit, sans en faire une condition** : sur un item à deux chiffres
et à un emprunt, "non reporté" et "reporté deux fois" encadrent la bonne réponse à égale
distance (`r + 10`, `r - 10`). J'ai validé la même forme en addition (`64`, `74`, `84`). Mêler
l'emprunt oublié et la somme évite que "celle du milieu" devienne une stratégie.

### 4. Ce que je n'admets pas, et pourquoi

| Erreur envisagée | Pourquoi elle n'entre pas |
|---|---|
| Confondre les deux nombres (`b - a`) | Résultat négatif : exclu par `SOP-CE1-6` et par la clause elle-même (entiers positifs). L'erreur réelle de l'enfant, retirer le petit chiffre du grand, est déjà l'emprunt oublié |
| Emprunt pris sur la colonne des centaines au lieu de celle des dizaines | Plausible, mais je ne l'ai pas examinée comme item à ce niveau, et je n'admets pas une forme que je n'ai pas regardée. Elle n'est pas nécessaire : la quatrième forme suffit |
| Emprunt fait sans besoin (le réflexe que nomme le motif de `SOP-CE1-2`) | Ce que l'enfant écrit dans la colonne n'est pas déterminé (un chiffre ou deux), donc ce n'est pas une erreur nommée qui produit une valeur unique |

### 5. La ligne peut être engagée dans s46

**Oui. `MAT-CE1-05` peut être engagée dans s46, périmètre inchangé**, avec `SOP-CE1-5` amendée. Rien
d'autre ne change dans `SOP-CE1-1` à `SOP-CE1-6`. Aucune des trois sorties refusées par `theo-archi`
(avis, section 3.3) n'est nécessaire. Deux gestes avant le premier item :

| Fait | À qui | Pourquoi ce n'est pas à moi |
|---|---|---|
| Le critère d'acceptation de `SOP-CE1-5` de `S46-MAT-SOUSTRACTION` cite les quatre formes et la phrase sur le rang du paquet entier | `camille-po`, via `sofia-sm` | Rédaction de story |
| Le test du paquet relit le marqueur de la quatrième forme et le compare à `a - b - 10^(i+1)`, vérifie la règle d'admission (aucun emprunt de plus ni de moins), et compte la stratégie "toujours la plus petite" sur tous les items, deux propositions comprises | `theo-archi` et le développeur affecté, via `sofia-sm` | La forme du test leur appartient |

### 6. Ce dont je reste la seule garde

Que les quatre formes soient des erreurs qu'un enfant de CE1 commet, et pas seulement des nombres
voisins : c'est mon choix, et je le vérifierai en jouant. Tout le reste de cet amendement est
calculable sur le paquet.

**Identifiants** : aucun neuf, aucun renommé. `SOP-CE1-5` est déjà alloué dans
`squad/decisions/REGISTRE-identifiants.md`, section 4 ; son texte change, pas son nom.

---

**Signé** : `nadia-prof`, 2026-10-01.

---

## Bloc de validation pédagogique

### Verdict du 2026-10-02 (`nadia-prof`, s46, instance A) : **REFUSÉ**

**Motif, en une phrase** : les items `85 - 23` et `76 - 34` ne tiennent pas `SOP-CE1-4` - leur seul
distracteur (`108`, `110`) a trois chiffres face à une bonne réponse qui en a deux et ne partage pas
son chiffre des unités, si bien que l'item se répond sans poser l'opération : une différence ne peut
pas dépasser le premier terme.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Worktree dédié `s46-nadia-audit-a`, fondé sur `round-two` à
`7a507b2a`. Jar construit par `bash outils/mvn-jdk21.sh -pl bootstrap -am package -DskipTests`, le
Done technique étant acquis par `yanis-qa` sur le job `build` (`mvn clean verify`,
`squad/sprints/s46-soustraction-ce1-emc-et-sciences-cm1/rapport-qualite.md`, conclusion) ; je n'ai
lancé aucun autre job de la CI et je n'en déduis rien. Jar lancé sur le JDK 21 de la toolchain
(21.0.10), sur un port dédié.

**Six parties complètes de dix manches**, jouées par appels HTTP réels sur les routes de l'écran :
dans chaque partie, un tiers des manches juste du premier coup, un tiers après une erreur, un tiers
après trois erreurs jusqu'à la révélation. **Les dix-huit items ont été tirés et joués** ; sur
chacun, les propositions affichées sont celles du paquet. Après une erreur : *"Ce n'est pas ça.
Essaie encore !"* et l'indice. Après la troisième : *"Appuie sur la bonne réponse : ..."*. Écran de
fin atteint sur les six parties.

Les dix-huit différences et les trente-quatre distracteurs ont été recalculés par un script de
poste, non commité, qui applique colonne par colonne les quatre formes de `SOP-CE1-5` et la règle
d'admission de la quatrième (section 2).

#### 2. Ancrage au programme

`MAT-CE1-05` : *"Poser et effectuer une soustraction (nombres à deux ou trois chiffres)."* **C'est
bien cette compétence** : nombres nus, consigne qui demande de poser, seize items sur dix-huit à
emprunt. Ce n'est pas `MAT-CE1-03` sous un autre code (`SOP-CE1-3`, ci-dessous).

#### 3. Conditions, vérifiées une par une

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `SOP-CE1-1` | sans objet tant que la ligne ne ferme pas | La consigne servie commence par *"Pose l'opération"*. La mention de la matrice sera due au verdict qui fermera la ligne |
| `SOP-CE1-2` | **oui** | Cinq items à deux chiffres, treize à trois chiffres. Seize à emprunt, dont quatre à deux emprunts (`432 - 178`, `613 - 247`, `523 - 287`, `641 - 375`). Deux sans emprunt. Comptes faits au calcul |
| `SOP-CE1-3` | **oui** | 13 sur 18 (72 %) ont un premier terme supérieur à 100, au-dessus des deux tiers |
| `SOP-CE1-4` | **non, sur deux items** | Les seize items à trois propositions la tiennent : même nombre de chiffres, chiffre des unités partagé. **`85 - 23` (62 contre 108) et `76 - 34` (42 contre 110) ne la tiennent pas** : trois chiffres contre deux, aucun chiffre des unités partagé. Section 4 |
| `SOP-CE1-5` | **oui** | Les trente-quatre distracteurs correspondent à la forme que leur marqueur déclare, recalculée. Sur `52 - 27` et `452 - 237`, l'emprunt oublié et l'emprunt non reporté coïncident ; le marqueur dit *non-reportée*, la valeur est juste sous les deux noms. Chaque *reportée deux fois* tient la règle d'admission. **Règle de rang du paquet entier** : la plus petite proposition est la bonne sur 7 items sur 18 (39 %) |
| `SOP-CE1-6` | **oui** | `0 <= b <= a <= 1000`, aucun résultat négatif, aucun habillage, aucune unité |

**Le point que j'avais dit regarder** (section 3) : sur les seize items à trois propositions, la
bonne réponse est au milieu sur 7 (44 %), la plus petite sur 5, la plus grande sur 4. *"Celle du
milieu"* ne gagne pas sur plus de la moitié du paquet. Rien à redire.

**Les quatre formes sont des erreurs d'enfant de CE1**, et je l'ai vérifié en jouant, ce qui était
la seule chose dont je restais la garde : `613 - 247 -> 356` est le résultat exact d'un enfant qui,
par compensation, ajoute deux fois le `1` retenu sous les dizaines (`4 + 1 + 1 = 6`, `11 - 6 = 5`).

#### 4. Le motif, et pourquoi il est opposable

`SOP-CE1-4` (s43, section 5.2) : *"l'item ne se répond pas sans poser l'opération. Mêmes exigences
que `ADP-CE1-4` : même nombre de chiffres, même ordre de grandeur, au moins un distracteur
partageant le chiffre des unités avec la bonne réponse."* Elle porte sur **tout** item, et mon
amendement du 2026-10-01 la redit pour les quatre formes, somme comprise (section 2, *"Admission sur
un item"*).

Ce que l'enfant voit à l'écran, sur les six parties : `85 - 23 = ?`, deux boutons, `62` et `108`.
Un enfant de CE1 qui sait qu'en enlevant on obtient moins que ce qu'on avait n'a rien à calculer. Et
après une erreur il ne reste qu'un bouton. **L'item mesure une devinette**, ce que la clause existe
pour empêcher.

**Ce n'est pas une barre neuve, et la condition était tenable.** Sur un item sans emprunt, la somme
tient `SOP-CE1-4` dès que le choix des nombres le permet : un second terme qui finit par `0` ou
par `5`, et une somme qui ne passe pas la centaine (ou le millier). Par exemple `47 - 20 = 27`
(somme `67`), `65 - 15 = 50` (somme `80`), `368 - 125 = 243` (somme `493`). Le remplacement ne change
ni `SOP-CE1-2` ni la règle de rang du paquet entier.

**Une correction de mon propre texte, avant de juger sur lui.** Ma section 2 dit *"Sur les items
sans emprunt, seule la somme est disponible : ces items portent deux propositions, et le garde-fou
de rang ne les lit pas. Je l'accepte"*. Ce que j'acceptais, c'est le nombre de propositions et
l'absence de lecture par le garde-fou de rang - pas une exemption de `SOP-CE1-4`, que le paragraphe
suivant de la même section maintient. Mais je n'ai pas écrit que la somme devait elle-même la tenir,
ni que c'est le choix des nombres qui le permet, et ma phrase laissait la lecture ouverte. Je le dis
ici plutôt que de le laisser porter au développeur.

**Pourquoi la CI est verte.** `PaquetCe1SoustractionsPoseesTest.lesTroisPropositionsOntLeMemeNombreDeChiffresEtUnDistracteurPartageLeChiffreDesUnites`
saute tout item qui ne porte pas deux distracteurs, et sa Javadoc attribue cette exemption à
*"fiche de conformité, section 3"*. Ma section 3 ne parle que du garde-fou de rang. **Ce test
opérationnalise `SOP-CE1-4` plus étroitement que la condition** : son vert ne garde pas les deux
items sans emprunt.

#### 5. Ce qui doit changer pour que le lot repasse

| Fait | À qui |
|---|---|
| Remplacer `85 - 23` et `76 - 34` par deux items sans emprunt dont la somme a le même nombre de chiffres que la différence et partage son chiffre des unités. Garder au moins deux items sans emprunt et au moins trois items à deux chiffres (`SOP-CE1-2`) | `camille-po` en fait la story, développeur affecté par `sofia-sm` |
| Retirer du test l'exemption des items à deux propositions pour `SOP-CE1-4`, et corriger sa Javadoc qui cite ma section 3 pour une exemption qu'elle ne donne pas | `theo-archi` et le développeur affecté, via `sofia-sm` |

Rien d'autre n'est à reprendre : les seize autres items passent toutes les conditions, et
je les rejouerai tels quels.

#### 6. Ce dont je reste la seule garde

Que les quatre formes soient des erreurs qu'un enfant de CE1 commet, et pas seulement des nombres
voisins. Tout le reste de `SOP-CE1-1` à `SOP-CE1-6` est calculable sur le paquet - **à condition que
le test lise tous les items**, ce qui n'est pas le cas aujourd'hui pour `SOP-CE1-4`.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s46, instance A, 2026-10-02.

---

### Verdict du 2026-10-02, second passage (`nadia-prof`, s46, instance A) : **VALIDÉ SOUS RÉSERVE**

**Done pédagogique prononcé.** Le motif du premier passage est levé, rien d'autre n'a bougé, et les
six conditions sont tenues sur les dix-huit items. Une réserve neuve, non bloquante, `SOP-CE1-R1`
(section 4) : elle porte sur ma propre condition, pas sur le travail de la correction. `camille-po`
peut passer `MAT-CE1-05` à
`couvert-valide`, **avec la mention de `SOP-CE1-1` reprise mot pour mot** : *"le résultat atteste le
calcul ; la technique posée elle-même - disposition en colonnes, emprunts - n'est ni montrée ni
vérifiée par le produit."* Sans cette mention, la ligne ne ferme pas.

#### 1. Ce que j'ai fait

**Audit complet, pas dégradé.** Worktree dédié `s46-nadia-audit-a2`, fondé sur `round-two` à
`7ff80b67`, qui porte la correction de `marc-dev` (`79e04615`). Jar construit par
`bash outils/mvn-jdk21.sh -pl bootstrap -am package -DskipTests`, lancé sur le JDK 21 de la
toolchain (21.0.10), sur un port dédié. Je n'ai lancé aucun job de la CI et je n'en déduis rien ; le
Done technique de la correction reste à annoncer par qui lance les jobs.

**Six parties complètes de dix manches, sur le paquet entier**, pas seulement les deux items
remplacés : un tiers des manches juste du premier coup, un tiers après une erreur, un tiers après
trois erreurs jusqu'à la révélation. **Les dix-huit items ont été tirés et joués**, et sur chacun les
propositions affichées sont exactement celles du paquet. Après une erreur : *"Ce n'est pas ça.
Essaie encore !"* et l'indice. Après la troisième : *"Appuie sur la bonne réponse : ..."*. Écran de
fin atteint sur les six parties.

Les dix-huit différences et les trente-quatre distracteurs recalculés par le même script de poste,
non commité : valeur de chaque forme déclarée, règle d'admission de la quatrième forme, nombre de
chiffres, chiffre des unités partagé, doublons. Aucun écart.

`PaquetCe1SoustractionsPoseesTest` lancé sur l'arbre audité
(`bash outils/mvn-jdk21.sh -pl adapters/adapter-content -am test -Dtest=PaquetCe1SoustractionsPoseesTest`,
périmètre : ce seul test) : 13 tests, 0 échec. J'ai lu son diff : l'exemption des items à deux
propositions est retirée, et sa Javadoc ne m'attribue plus une exemption que je n'ai pas donnée.

#### 2. Le motif est levé

| Item | Bonne réponse | Seul distracteur (somme) | `SOP-CE1-4` |
|---|---|---|---|
| `47 - 20` | 27 | 67 | **tenue** : deux chiffres contre deux, unités `7` partagées |
| `65 - 15` | 50 | 80 | **tenue** : deux chiffres contre deux, unités `0` partagées |

Ce sont deux des trois exemples que j'avais donnés au premier passage, et c'est exactement la barre
que ma section 5 demandait : même nombre de chiffres, chiffre des unités partagé. Je lève le motif.

#### 3. Rien d'autre n'a bougé

| Condition | Tenue | Ce que j'ai vérifié |
|---|---|---|
| `SOP-CE1-1` | **oui, à reporter** | Consigne servie *"Pose l'opération, puis choisis le bon résultat."* Mention à porter dans la matrice (en tête de ce verdict) |
| `SOP-CE1-2` | **oui** | Cinq items à deux chiffres, treize à trois. Seize à emprunt, dont quatre à deux emprunts. Deux sans emprunt. Comptes recalculés |
| `SOP-CE1-3` | **oui** | 13 sur 18 ont un premier terme supérieur à 100 |
| `SOP-CE1-4` | **oui, sur les dix-huit items** | Section 2 pour les deux items neufs ; les seize autres inchangés et recalculés |
| `SOP-CE1-5` | **oui** | Les trente-quatre distracteurs correspondent à leur marqueur. Règle de rang du paquet entier : la plus petite proposition est la bonne sur 7 items sur 18 (39 %), le même compte qu'au premier passage, puisque `27 < 67` et `50 < 80` remplacent `62 < 108` et `42 < 110` |
| `SOP-CE1-6` | **oui** | Aucun résultat négatif, aucun habillage |

Sur les seize items à trois propositions, la bonne réponse est au milieu sur 7, la plus petite sur
5, la plus grande sur 4 : inchangé.

#### 4. Une correction de mon premier verdict, et la réserve qui en sort

**Mon motif du premier passage disait trop.** Il finissait par *"l'item se répond sans poser
l'opération : une différence ne peut pas dépasser le premier terme"*. Ce second argument ne
distinguait pas `108` d'un distracteur admis : **une somme dépasse toujours le premier terme**
(`67 > 47`, `80 > 65`). Ce que `SOP-CE1-4` tient, et ce que j'ai demandé, c'est le nombre de chiffres
et le chiffre des unités. Je ne relève pas la barre après coup : les deux items la tiennent.

**`SOP-CE1-R1` (2026-10-02, non bloquante).** Sur un item sans emprunt, `SOP-CE1-5` n'admet que la
somme, toujours plus grande que le premier terme. En jouant `47 - 20`, un enfant de CE1 qui sait
qu'en enlevant on obtient moins que ce qu'on avait écarte `67` sans poser l'opération. La limite
vient de ma condition, pas du paquet, et elle touche les deux items sans emprunt, que `SOP-CE1-2`
exige pour que l'enfant apprenne à ne pas emprunter sans besoin : c'est justement ce que ces deux
items ne lui font pas vérifier. **Action** : `camille-po` en fait une story d'arbitrage qui m'est
adressée. Je dois soit admettre, pour les items sans emprunt, une erreur nommée dont la valeur est
inférieure au premier terme, soit accepter cette limite par écrit. Le paquet n'est pas à reprendre
avant cet arbitrage.

#### 5. Ce dont je reste la seule garde

Que les quatre formes soient des erreurs qu'un enfant de CE1 commet, et pas seulement des nombres
voisins : vérifié en jouant au premier passage, rien n'a changé sur les seize items concernés.
**`SOP-CE1-4` n'est plus de ma seule garde** : le test du paquet la lit désormais sur tous les items.
`SOP-CE1-R1` n'est gardée par aucun test.

**Identifiants** : un neuf, `SOP-CE1-R1`, alloué dans `squad/decisions/REGISTRE-identifiants.md`,
section 4, dans le même commit. Aucun renommé.

**Signé** : `nadia-prof`, s46, instance A, 2026-10-02.

---

## Arbitrage de `SOP-CE1-R1` du 2026-10-02 (`nadia-prof`, s47)

**Décision : j'admets une cinquième forme de distracteur, le décalage des colonnes, et je la rends
obligatoire sur les items sans emprunt.** Je n'accepte pas la limite telle quelle. `SOP-CE1-R1`
reste ouverte jusqu'à ce que je rejoue un paquet repris (section 4 ci-dessous). Le Done pédagogique
du 2026-10-02 n'est pas rouvert.

**Ce qui m'est posé** : `DEC-0104`, section 5, et la story d'arbitrage de `docs/backlog/backlog.md`
(*"Story d'arbitrage différée, adressée à `nadia-prof` - `SOP-CE1-R1`"*). **Une correction du
routage avant de trancher** : le message qui me route cette question attribue le constat à
`theo-archi`. Il vient de mon second passage (section 4 de ce verdict). L'avis de `theo-archi` de s46
portait sur les trois premières formes, qui dépassent toujours la bonne réponse. Cela ne change pas
le fond.

**Audit dégradé, et je le déclare** : je n'ai rien rejoué. Rien n'a changé dans le paquet depuis mon
second passage. Les valeurs ci-dessous sortent d'un script de poste non commité.

### 1. La forme admise

> **Cinquième forme : le décalage des colonnes.** L'enfant pose le second terme aligné à gauche
> sous le premier, au lieu de l'aligner sur les unités. Pour un second terme qui a un chiffre de
> moins que le premier, la valeur est `a - 10 x b`. Exemple : `786 - 25` posé avec `25` sous `78`
> donne `786 - 250 = 536`.
>
> **Admission sur un item.** La forme n'est admise que si `a - 10 x b` est positif, a le même nombre
> de chiffres que la bonne réponse, et se calcule **sans emprunt**. Avec un emprunt, l'enfant
> pourrait enchaîner une seconde erreur et ne reconnaîtrait pas la sienne. `SOP-CE1-4` s'applique
> comme aux quatre autres formes.
>
> **Sur un item sans emprunt, cette forme est obligatoire.** Ces items sont donc choisis avec un
> second terme d'au moins deux chiffres et d'un chiffre de moins que le premier (trois chiffres
> moins deux chiffres). La somme reste admise à côté d'elle, si elle tient `SOP-CE1-4`.
>
> **Les cinq formes, et seulement celles-là** : l'emprunt oublié, l'emprunt non reporté, la somme,
> l'emprunt reporté deux fois, le décalage des colonnes. Le reste de `SOP-CE1-5` ne change pas.

**Pourquoi c'est une vraie erreur de CE1.** Poser une soustraction, c'est d'abord placer les unités
sous les unités. Quand les deux nombres n'ont pas le même nombre de chiffres, l'enfant de CE1 cale
souvent le second à gauche, comme il écrit une ligne. C'est une erreur de la technique posée
elle-même, celle que la consigne *"Pose l'opération"* demande. Et l'énoncé est servi en ligne
(`786 - 25 = ?`) : c'est l'enfant qui pose, donc c'est lui qui décale.

**Ce que la forme change pour l'enfant.** La bonne réponse et le décalage sont tous les deux plus
petits que le premier terme. Le sens de grandeur ne suffit plus : il faut avoir aligné les unités
pour choisir. C'est la vérification que `SOP-CE1-2` demandait à ces items.

### 2. Ce que je n'admets pas

| Erreur envisagée | Pourquoi elle n'entre pas |
|---|---|
| Emprunt fait sans besoin | Déjà écartée (section 4 des conditions) : ce que l'enfant écrit dans la colonne n'est pas déterminé |
| Erreur de table dans une colonne (`7 - 5 = 3`) | Ce n'est pas une erreur nommée qui donne une valeur unique, c'est un nombre voisin |
| Décalage sur un item où les deux termes ont le même nombre de chiffres | L'erreur n'existe pas : il n'y a rien à décaler |

### 3. La forme est tenable

Vérifié sur les couples `100 <= a <= 999`, `10 <= b <= 99`, sans emprunt : 22 275 couples, dont
11 022 admettent le décalage, et 3 110 donnent un item qui tient `SOP-CE1-4` (même nombre de
chiffres pour tous les distracteurs, un distracteur au moins qui partage le chiffre des unités).

| Item | Bonne réponse | Décalage | Somme | `SOP-CE1-4` |
|---|---|---|---|---|
| `786 - 25` | 761 | 536 | 811 | tenue : trois chiffres partout, la somme partage le `1` |
| `785 - 40` | 745 | 385 | 825 | tenue : trois chiffres partout, les deux partagent le `5` |
| `968 - 50` | 918 | 468 | 1018, non admise (quatre chiffres) | tenue sur le seul décalage, qui partage le `8` |
| `786 - 75` | 711 | 36, non admis (deux chiffres) | 861 | le décalage n'est pas admis : un autre couple est pris |

`SOP-CE1-2` reste tenable : sur les cinq items à deux chiffres du paquet, les deux items sans emprunt
remplacés par des items à trois chiffres en laissent trois. `SOP-CE1-3` passe de 13 à 15 items sur
18.

**Une conséquence de fabrication, pas une barre neuve.** Sur un item sans emprunt à trois
propositions, la bonne réponse est toujours au milieu (décalage plus petit, somme plus grande). Si
les deux items sans emprunt en portent trois, *"celle du milieu"* passe de 7 à 9 items sur 18, soit
50 %. Le garde-fou de rang compare en strict (`ratio > SEUIL_MAJORITE`), donc 50 % passe. C'est le
test du paquet qui décide, pas moi. Je le regarderai en jouant.

### 4. Ce qui lève `SOP-CE1-R1`

**Barre de levée, écrite aujourd'hui et que je ne déplacerai pas** : chaque item sans emprunt du
paquet porte le décalage des colonnes, admis selon la section 1. Les autres conditions sont tenues
sur les dix-huit items. Je rejoue le paquet entier.

| Fait | À qui |
|---|---|
| Story de reprise : remplacer `47 - 20` et `65 - 15` par deux items sans emprunt de trois chiffres moins deux chiffres, qui portent le décalage | `camille-po`, développeur affecté par `sofia-sm` |
| Le test du paquet relit le marqueur de la cinquième forme, la compare à `a - 10 x b`, vérifie qu'elle se calcule sans emprunt, et exige sa présence sur tout item sans emprunt | `theo-archi` et le développeur affecté, via `sofia-sm` |
| Le critère d'acceptation de `SOP-CE1-5` cite les cinq formes | `camille-po` |

### 5. Ce dont je reste la seule garde

Que le décalage soit une erreur qu'un enfant de CE1 commet sur ces items-là, et pas seulement un
nombre plus petit : je le vérifierai en jouant. Tout le reste de la section 1 se calcule sur le
paquet. `SOP-CE1-R1` n'est gardée par aucun test tant que le test du paquet n'est pas repris.

**Identifiants** : aucun neuf, aucun renommé. `SOP-CE1-5` change de texte, pas de nom. `SOP-CE1-R1`
reste ouverte.

**Signé** : `nadia-prof`, s47, 2026-10-02.

---

## Troisième passage du 2026-10-03 (`nadia-prof`, s48, instance A) : **VALIDÉ**, `SOP-CE1-R1` levée

**`SOP-CE1-R1` est levée. Aucune réserve ouverte sur ce paquet.** `MAT-CE1-05` reste `couvert-valide`.

### 1. Ce que j'ai fait

Contenu à `bc1e7835` (`sofia-sm`, casquette dev), arbre `round-two` au même commit. Jar du
`clean verify` de `yanis-qa` à ce commit, YAML du jar vérifié identique à l'arbre, lancé sur un port
dédié servi par mon propre processus. Aucun job de la CI lancé par moi : le rapport qualité de s48
compte `PaquetCe1SoustractionsPoseesTest` à 16 tests, contre 13. **Neuf parties complètes de dix
manches** par appels HTTP réels : huit avec une erreur sur une manche sur deux, une en pressant la même
mauvaise réponse jusqu'à la révélation. **Les 18 items tirés** ; `786 - 25` et `785 - 40` joués avec
une erreur. Rendu non revu au navigateur : aucun gabarit n'a changé.

### 2. Les deux items neufs sont ceux de mon arbitrage

`git show bc1e7835` : `47 - 20` et `65 - 15` sont remplacés par `786 - 25` et `785 - 40`, les deux
premières lignes du tableau de la section 3 de mon arbitrage, avec les valeurs que j'y avais écrites :

| Item | Bonne réponse | Décalage | Somme | Servi |
|---|---|---|---|---|
| `786 - 25` | 761 | 536 | 811 | 761, 536, 811 |
| `785 - 40` | 745 | 385 | 825 | 745, 385, 825 |

Recalculés avant de m'appuyer sur mon propre tableau : `786 - 250 = 536` et `785 - 400 = 385`, sans
emprunt ; `786 - 25` et `785 - 40` se posent sans emprunt ; trois chiffres partout ; la somme partage
le `1` de 761, les deux distracteurs partagent le `5` de 745 (`SOP-CE1-4`). Mon tableau était exact.

### 3. La barre de levée, atteinte

Ma barre (section 4 de l'arbitrage) : chaque item sans emprunt porte le décalage des colonnes, admis
selon la section 1, et les autres conditions sont tenues sur les dix-huit items.

- **Le décalage sur chaque item sans emprunt** : les deux seuls items sans emprunt sont les deux neufs,
  et tous deux le portent. Les seize autres portent un emprunt et n'ont pas changé.
- **`SOP-CE1-2`** : trois items à deux nombres de deux chiffres (`52 - 27`, `85 - 57`, `91 - 68`),
  plancher de trois. **`SOP-CE1-3`** : quinze items à trois chiffres sur dix-huit.
- **Rangs**, comptés sur les dix-huit items servis : la plus petite 5, le milieu 9, la plus grande 4.
  Le milieu tient exactement 50 %, ce que j'avais annoncé comme une conséquence de fabrication et non
  une barre ; le garde-fou de rang passe, la règle propre au paquet aussi (5 sur 18).
- **Ce que je m'étais réservé de vérifier en jouant** : que le décalage soit une erreur qu'un enfant
  de CE1 commet sur ces items-là. Joué sur `786 - 25` : l'enfant qui pose `25` sous `78` obtient 536,
  la proposition est là ; celui qui ajoute obtient 811. La bonne réponse et le décalage sont tous deux
  plus petits que 786 : le sens de grandeur ne suffit plus, il faut avoir aligné les unités. C'est ce
  que `SOP-CE1-2` demandait à ces items.
- **L'indice neuf** de ces deux items, *"Pose la soustraction en colonnes, aligne bien le second
  nombre sous les unités, puis calcule les unités et les dizaines."*, nomme exactement l'erreur que le
  décalage décrit. Il ne cite pas la colonne des centaines, où il n'y a rien à soustraire : il reste
  exact.

### 4. Ce qui change dans ma garde

`PaquetCe1SoustractionsPoseesTest` relit désormais le marqueur `decalage`, le recalcule en
`a - 10 × b`, vérifie sa règle d'admission, exige sa présence sur tout item sans emprunt et mord sur un
jeu fautif commité (`ce1-soustractions-posees-fautif-decalage.yaml`) : **`SOP-CE1-R1` n'est plus de ma
seule garde.** Reste à moi : que les cinq formes soient des erreurs qu'un enfant de CE1 commet, et pas
seulement des valeurs que la formule autorise.

**Identifiants** : aucun neuf, aucun renommé.

**Signé** : `nadia-prof`, s48, instance A, troisième passage, 2026-10-03.
