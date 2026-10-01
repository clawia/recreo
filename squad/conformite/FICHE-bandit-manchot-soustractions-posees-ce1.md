# Fiche de conformité - bandit-manchot-soustractions-posees-ce1

- **Mécanique** : bandit manchot.
- **Contenu** : aucun à ce jour. Le paquet est à écrire en s46.
- **Ligne déclarée** : `MAT-CE1-05`. **Story** : `S46-MAT-SOUSTRACTION` (`docs/backlog/backlog.md`,
  section "Sprint 46").
- **Conditions opposables** : `SOP-CE1-1` à `SOP-CE1-6`
  (`squad/conformite/ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`, section 5.2), **`SOP-CE1-5`
  dans sa version amendée du 2026-10-01** (section 2 ci-dessous).
- **Statut global** : **aucun verdict de Done**. Fiche ouverte avant le premier item, par
  l'amendement de `SOP-CE1-5`, pour que la condition que le développeur lit soit celle que j'auditerai.
- **Dernière mise à jour** : 2026-10-01.

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
