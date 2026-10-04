# Fiche de conformite - bandit-manchot-soustractions-ce1

- **Mecanique** : bandit manchot.
- **Contenu** :
  `adapters/adapter-content/src/main/resources/contenu/ce1-soustractions-jusqu-a-100.yaml`
  (32 items, type `question`).
- **Entree de catalogue** : `bandit-manchot-soustractions-ce1`, titre servi *"Les grandes
  soustractions"*, consigne servie *"Combien ca fait ?"*, `nombreDeManches: 10`.
- **Ligne declaree** : `MAT-CE1-03`, sous-partie *"soustractions dans la limite de 100"*.
- **Statut global** : **VALIDE SOUS RESERVE**. Done pedagogique **prononce le 2026-09-22** par
  `nadia-prof`, **apres avoir joue le jar servi**. Une reserve nommee, `SOU-CE1-R1`,
  **non bloquante**.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` (2026-09-22), section 1,
  clauses `SOU-CE1-1` et `SOU-CE1-2`, distracteurs section 1.4.
- **Derniere mise a jour** : 2026-10-04 (second passage, s50, en fin de fiche) : **VALIDE**,
  `SOU-CE1-R1` levee. Arbitrage de `SOU-CE1-R1` : 2026-10-03. Premier audit : 2026-09-22, s38. Les
  verdicts precedents restent ecrits tels quels.

---

## 1. Ce que j'ai joue, et comment

**Audit non degrade.** Reacteur construit (`mvn -B clean package -DskipTests` via
`outils/mvn-jdk21.sh`, `BUILD SUCCESS`), `bootstrap/target/recreo.jar` lance sur le JDK 21 designe
par la toolchain, jeu sur l'ecran reellement servi.

Une partie jouee manche par manche, erreurs volontaires sur plusieurs manches pour lire le
comportement apres une puis deux erreurs, puis les 32 items relus dans le fichier de contenu pour
recalculer chacun a la main (section 2).

**Ce que l'enfant voit sur une manche** : la consigne *"Combien ca fait ?"*, l'enonce
`74 - 49 = ?`, trois propositions. Sur une erreur : *"Ce n'est pas ca."* (ou *"Essaie encore !"*
selon la forme, voir section 1 des ecrans joues), l'indice affiche (*"Recule de 4 pour atteindre
70, puis encore de 45."*), la proposition deja pressee restant cliquable. La revelation, quand elle
survient, nomme la bonne reponse en toutes lettres.

## 2. Exactitude - les 32 items recalcules

J'ai recalcule les 32 differences a la main : **exactes sans exception**. Les indices
*"recule/enleve/pars"* recalcules terme a terme contre chaque item : corrects sans exception, y
compris la forme composee des sept items avec emprunt (*"Recule de X pour atteindre Y, puis encore
de Z"*, ou `X = unite(a)`, `Y = a - unite(a)`, `Z = b - unite(a)`).

## 3. `SOU-CE1-1` et `SOU-CE1-2` - tenues, et comptees par moi

- **`SOU-CE1-1`** (soustracteur nul et resultat nul, au plus 5 % chacun, sans plancher) : **0 item
  de chaque sur 32 (0 %)**, conforme.
- **`SOU-CE1-2`** (sept bandes) : 6 differences faciles (18,75 %, bande 10-25 %), 5 soustractions
  d'un nombre rond (15,6 %, bande 10-20 %), 7 items "un chiffre soustrait" (21,9 %, bande 15-30 %),
  7 items deux chiffres sans emprunt (21,9 %, bande 10-30 %), 7 items deux chiffres avec emprunt
  (21,9 %, bande 15-30 %). **Les sept bandes sont tenues**, comptees par moi sur la liste avant de
  relire le commentaire du fichier de contenu - les deux comptages concordent.

## 4. `SOU-CE1-R1` - un distracteur non identifiable sur quatre items de la forme "un chiffre soustrait"

**Ouverte le 2026-09-22. Non bloquante.**

**Le fait.** La clause de mon arbitrage (section 1.4) n'autorise, pour les items hors forme 7
(avec emprunt), que deux formes de distracteur : la somme (`a + b`) et le second terme recopie
(`b`). Sur les sept items de la forme 5 (*"un chiffre soustrait"*, `b < 10`), trois items
(`84 - 7`, `91 - 8`, `99 - 9`) portent bien `b` et la somme. **Les quatre autres** (`45 - 3`,
`57 - 4`, `68 - 5`, `79 - 6`) portent `b` recopie, correct, mais leur second distracteur ne
correspond a aucune des deux formes autorisees :

| Item | Bonne reponse | Distracteurs | Second distracteur = somme (`a+b`) ? |
|---|---|---|---|
| `45 - 3` | 42 | `3`, **`20`** | Non (la somme vaut 48) |
| `57 - 4` | 53 | `4`, **`25`** | Non (la somme vaut 61) |
| `68 - 5` | 63 | `5`, **`30`** | Non (la somme vaut 73) |
| `79 - 6` | 73 | `6`, **`35`** | Non (la somme vaut 85) |

**Ce que j'ai identifie, en recalculant plutot qu'en supposant.** Les quatre valeurs (`20`, `25`,
`30`, `35`) correspondent a la moitie de la dizaine inferieure de `a` (`floor(a / 10) x 10 / 2`) :
`45` -> `40` -> `20` ; `57` -> `50` -> `25` ; `68` -> `60` -> `30` ; `79` -> `70` -> `35`. Ce n'est
ni la somme, ni `b` recopie, ni - la clause le reserve a la forme 7 - l'erreur d'emprunt oublie :
**c'est une forme qu'aucune clause de mon arbitrage n'autorise pour cette forme d'item, et je ne
lui trouve pas d'erreur de soustraction plausible qu'elle representerait.** Jouee a l'ecran
(`79 - 6 = ?`, propositions `73`, `6`, `35`) : un enfant qui estime l'ordre de grandeur ecarte vite
`35`, trop loin de `79`, ce qui reduit dans les faits le choix a `73` contre `6` sur ces quatre
manches - une manche qui enseigne moins que ses freres de la meme forme, pas une manche fausse.

**Pourquoi ce n'est pas bloquant.** L'exactitude de la bonne reponse n'est pas en cause, les trois
propositions restent distinctes et positives, et le defaut est confine a un seul champ (le second
distracteur) sur 4 items sur 32 (12,5 %) - meme ordre de grandeur que les deux reserves deja
ouvertes et levees sur le paquet d'additions. Le jeu se joue de bout en bout sans qu'aucun enfant
ne soit bloque ni induit en erreur sur un fait.

**Le correctif que je demande, actionnable sans discussion.** Remplacer le second distracteur des
quatre items par la somme `a + b` (`48`, `61`, `73`, `85`), exactement la forme deja utilisee sur
leurs trois freres de la meme bande (`84 - 7`, `91 - 8`, `99 - 9`) et sur toutes les autres bandes
non-emprunt du paquet (formes 3, 4 et 6). Aucune autre valeur n'est necessaire : la coherence
interne a la forme 5 suffit a fermer la reserve.

*Amendement du 2026-10-03 (`nadia-prof`, s50).* Ce correctif est infaisable : il rougit le garde-fou
de rang. Il est remplace, sans etre efface, par celui de la section "Arbitrage de `SOU-CE1-R1` du
2026-10-03" en fin de fiche.

## 5. Ancrage sur `MAT-CE1-03` et non sur une ligne voisine

Le paquet couvre `a - b`, `0 <= b <= a <= 100`, avec les sept bandes de `SOU-CE1-2` qui font
apparaitre la soustraction facile, le nombre rond, le chiffre unique, et les deux formes a deux
chiffres (avec et sans emprunt). Aucun item ne demande un algorithme colonne par colonne : les
indices donnent tous une strategie de calcul mental (compter les pas, retrancher un nombre rond,
reculer jusqu'a la dizaine). Le paquet travaille bien `MAT-CE1-03`, pas `MAT-CE1-05` (la
soustraction posee).

## 6. Lisibilite, vocabulaire, ton

Consigne : *"Combien ca fait ?"*, quatre mots. Enonce : une ligne (`74 - 49 = ?`). Indices : une
phrase courte par forme, vocabulaire de CE1 (*"pars"*, *"recule"*, *"enleve d'un coup"*), tous
recalcules et corrects (section 2). Aucun biais, aucun ton infantilisant.

## 7. Ce dont je reste la seule garde

`SOU-CE1-1` et `SOU-CE1-2` sont automatisables sur le patron `ADD-CE1-1`/`ADD-CE1-2` ; tant
qu'aucun garde-fou ne les execute, c'est moi qui les tiens, en comptant - fait ce jour, section 3.
Restent en ma seule main : que le paquet serve bien `MAT-CE1-03` (section 5), la lisibilite des
indices (section 6), l'exactitude arithmetique des 32 items (section 2), et **la plausibilite de
chaque distracteur** - c'est exactement ce qui a produit `SOU-CE1-R1`, qu'aucun garde-fou de ce
depot ne lit.

---

## Bloc de validation

> **Done pedagogique : VALIDE SOUS RESERVE.**
>
> **Prononce le 2026-09-22 par `nadia-prof`, apres avoir joue le jar servi.**
>
> **Ce que j'ai verifie** : les 32 differences (exactes), `SOU-CE1-1` (0 % de chaque cas degenere,
> conforme), `SOU-CE1-2` (sept bandes tenues, comptees par moi), les indices (32 recalcules,
> corrects), l'ancrage sur `MAT-CE1-03`, et la plausibilite des distracteurs (defaut trouve sur
> quatre items, section 4).
>
> **Une reserve, non bloquante** : `SOU-CE1-R1` - sur `45 - 3`, `57 - 4`, `68 - 5` et `79 - 6`, le
> second distracteur (`20`, `25`, `30`, `35`) ne correspond a aucune des formes autorisees par ma
> clause (section 1.4) ni a une erreur de soustraction identifiable ; correctif donne en section 4
> (remplacer par la somme `a + b`, comme le font deja leurs trois freres de la meme bande).
>
> **Mouvement de ligne** : ce Done, combine a ceux des trois autres pieces de `MAT-CE1-03`, ouvre
> la voie a un passage de `MAT-CE1-03` a `couvert-valide` - une reserve non bloquante etant un
> Done pedagogique ouvert, pas un Done refuse. Le mouvement lui-meme est ecrit par `camille-po`,
> pas par moi.

---

## Arbitrage de `SOU-CE1-R1` du 2026-10-03 (`nadia-prof`, s50)

**Ce qui m'est pose** : `squad/rapports/avis-faisabilite-s50-cinq-stories.md`, section 6 et
question Q1. Le correctif de la section 4 (la somme `a + b`) rend la bonne reponse intermediaire sur
les quatre items. Le rang "la plus grande" tombe a 0 % (plancher 10 %) et le rang du milieu monte a
53,1 % (plafond 50 %). Aucune exemption n'est ouvrable. **Le Done pedagogique du 2026-09-22 n'est
pas rouvert. `SOU-CE1-R1` reste ouverte.**

**Audit degrade, et je le declare** : je n'ai rien rejoue. Le paquet n'a pas change depuis s38. Les
valeurs ci-dessous sont calculees a la main sur le YAML a `10198d28`.

### 1. Deux corrections de mes propres constats

- **La section 4 dit que le second distracteur ne correspond a aucune forme "autorisee par ma
  clause".** C'est inexact. La section 1.4 de l'arbitrage exige **au moins un** distracteur
  identifiable par item, et `b` est present sur les quatre items : la clause etait tenue. Le motif de
  la reserve est la plausibilite du second distracteur, pas une violation de clause.
- **J'ai ecrit "aucune autre valeur n'est necessaire" sans regarder le rang.** Les quatre items vises
  etaient les seuls du paquet ou la bonne reponse est la plus grande. `theo-archi` l'a mesure, pas
  moi.

### 2. La forme admise

> **Le chiffre soustrait compte comme des dizaines**, de valeur `a - 10 x b`. L'enfant recule de
> `b` dizaines au lieu de `b` unites : `45 - 3` donne `45, 35, 25, 15`, donc `15`.
>
> **Portee** : items de forme 5 (*"un chiffre soustrait"*, section 1.3 de l'arbitrage) uniquement.
> **Admission** : `a - 10 x b` est positif et distinct de `b`. Sinon, remplacement selon la regle
> generale de la section 1.4.

**Pourquoi c'est une vraie erreur de CE1, dans ce paquet-ci.** Le paquet alterne la forme 4
(`87 - 30`, *"enleve-le d'un coup"*) et la forme 5 (`45 - 3`, *"recule de 3, unite par unite"*).
Distinguer `3` de `30` est exactement le point de numeration que ces deux formes travaillent. C'est
la valeur que j'ai admise a s47 sous le nom de "decalage des colonnes" sur le paquet de soustractions
posees (`FICHE-bandit-manchot-soustractions-posees-ce1.md`, section "Arbitrage de `SOP-CE1-R1`") :
la meme confusion unites-dizaines, ici en calcul mental.

**Ce que je n'admets pas** : `a - b - 10` (une dizaine en trop). Proche de la bonne reponse, mais
c'est un emprunt fait sans besoin, que j'ai ecarte a s47 parce qu'il ne donne pas une valeur unique.
Je ne l'admets pas ici pour un motif de rang.

**Ce que la forme ne regle pas, et je le dis.** Un enfant qui estime l'ordre de grandeur ecarte
`19` sur `79 - 6` comme il ecartait `35`. La difference : `35` ne correspondait a aucune erreur,
`19` correspond a une erreur nommee. Il attrape l'enfant qui confond unites et dizaines.

### 3. Ce qui leve `SOU-CE1-R1`

**Barre de levee, qui remplace celle de la section 4 au meme cout (un champ, quatre items)** : le
second distracteur des quatre items est `a - 10 x b`.

| Item | Bonne reponse | Distracteurs corriges | Rang de la bonne reponse |
|---|---|---|---|
| `45 - 3` | 42 | `3`, `15` (remplace `20`) | la plus grande, inchange |
| `57 - 4` | 53 | `4`, `17` (remplace `25`) | la plus grande, inchange |
| `68 - 5` | 63 | `5`, `18` (remplace `30`) | la plus grande, inchange |
| `79 - 6` | 73 | `6`, `19` (remplace `35`) | la plus grande, inchange |

**Rang du paquet : deduit, pas mesure.** Aucun item ne change de rang : 15 / 13 / 4, la repartition
que `theo-archi` a mesuree verte a `03d152b0`. Le developpeur affecte lance
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` sur le YAML corrige avant de commiter.
Les trois freres (`84 - 7`, `91 - 8`, `99 - 9`) gardent la somme : la coherence que demandait la
section 4 n'est plus une exigence.

| Fait | A qui |
|---|---|
| Criteres d'acceptation de la story `SOU-CE1-R1` : `15`, `17`, `18`, `19` au lieu de `48`, `61`, `73`, `85` | `camille-po`, via `sofia-sm` |
| Correction du YAML et mesure du garde-fou de rang | developpeur affecte par `sofia-sm` |
| Rejouer les quatre items corriges | `nadia-prof` |

**Ce dont je reste la seule garde** : que `a - 10 x b` soit une erreur commise sur ces items. Aucun
test ne lit la forme d'un distracteur sur ce paquet.

**Identifiants** : aucun neuf, aucun renomme. `SOU-CE1-R1` reste ouverte.

### 4. Q2 de `theo-archi` (avis de s50, sections 5 et 7)

- **`GRC-CE1-R1`, *aucun distracteur sur plus de deux items du paquet* : oui.** C'est la propriete
  que j'ai constatee au second passage (*"ne reviennent plus que deux fois"*), pas une barre neuve :
  a `03d152b0`, seul *colline* la viole. La barre de levee de `GRC-CE1-R1` ne change pas. Un mot de
  remplacement qui reviendrait sur trois items reproduirait le defaut que la reserve nomme.
- **`DIV-CE1-R1`, *le diviseur `b` sur chaque item* : oui, dans le test du paquet `ce1-division`
  seulement.** Apres la correction, les 21 items le portent. L'assertion empeche la reserve de
  revenir. Elle ne modifie pas `DIV-CE1-5`, qui reste *"au moins une erreur parmi `a x b`, `a - b`,
  `b`"* pour tout autre paquet.

Une fois ces deux assertions ecrites, je ne suis plus la seule garde de ces deux proprietes.

**Signe** : `nadia-prof`, s50, 2026-10-03.

---

## Second passage du 2026-10-04 (`nadia-prof`, s50, instance A) : **VALIDE**, `SOU-CE1-R1` levee

**Done pedagogique sans reserve.** Le Done du 2026-09-22 etait prononce sous reserve ; la reserve
tombe. Je ne rouvre pas le paquet entier : je juge le seul point corrige par `lea-dev` (`6eded9d1`),
contre la barre de mon arbitrage du 2026-10-03 (section 3 ci-dessus). `MAT-CE1-03` ne change pas de
statut.

### 1. Ce que j'ai fait

**Audit joue, pas degrade** (l'arbitrage du 2026-10-03, lui, l'etait). Arbre `round-two` a
`655b319b`. Jar construit par `bash outils/mvn-jdk21.sh -B -q -pl bootstrap -am package -DskipTests`
(JDK 21.0.10), YAML du jar identique octet pour octet a l'arbre, lance sur un port dedie servi par
mon propre processus. Aucun job de la CI lance par moi. **Rendu non vu au navigateur** : appels HTTP
reels, HTML servi lu. `git diff 6eded9d1~1 6eded9d1` : quatre valeurs changees, rien d'autre.

**Trente-cinq parties completes de dix manches** : trente (une juste, une avec une erreur par
manche, une jusqu'a la revelation, vingt-sept en melant les trois au hasard), ou les 32 items ont ete
tires, les quatre items vises de 4 a 10 fois chacun ; puis cinq parties ciblees, ou j'ai presse
trois fois le distracteur corrige jusqu'a la revelation sur chaque item vise tire (`57 - 4` quatre
fois, `68 - 5` et `79 - 6`). `57 - 4` etait le seul des quatre ou le hasard des trente premieres
parties n'avait jamais presse le distracteur corrige.

### 2. La forme servie, contre celle que j'ai ecrite

| Item | Bonne reponse | Distracteurs servis | `a - 10 x b` | Rang de la bonne reponse | Joue |
|---|---|---|---|---|---|
| `45 - 3` | 42 | `3`, `15` | 45 - 30 = 15 | la plus grande | `15` refuse, revelation de `42` |
| `57 - 4` | 53 | `4`, `17` | 57 - 40 = 17 | la plus grande | `17` refuse, revelation de `53` |
| `68 - 5` | 63 | `5`, `18` | 68 - 50 = 18 | la plus grande | `18` refuse, revelation de `63` |
| `79 - 6` | 73 | `6`, `19` | 79 - 60 = 19 | la plus grande | `19` refuse, revelation de `73` |

**La forme est celle que j'ai ecrite** : le second distracteur de chacun des quatre items vaut
`a - 10 x b`, recalcule a la main, positif et distinct de `b` (clause d'admission tenue). Le premier
distracteur `b` est inchange. Les trois freres (`84 - 7`, `91 - 8`, `99 - 9`) gardent la somme, comme
l'arbitrage le prevoyait. Sur chaque erreur, l'indice *"Pars de 57 et recule de 4, unité par
unité."* est celui qui defait exactement cette confusion : reculer de `4` unites, pas de `4`
dizaines. La revelation nomme la bonne reponse (*"Appuie sur la bonne réponse : 53"*).

**Le rang**, que je n'avais que deduit le 2026-10-03, est confirme par l'ecran : sur les quatre
items, la bonne reponse est la plus grande des trois propositions, et `lea-dev` a mesure la
repartition du paquet a 15 / 13 / 4 avec le garde-fou vert (message de `6eded9d1`).

### 3. Ce dont je reste la seule garde

**Aucun test ne lit la forme d'un distracteur de ce paquet** : `6eded9d1` ne touche que le YAML.
Que `15`, `17`, `18`, `19` soient `a - 10 x b`, et que cette valeur soit une erreur qu'un enfant de
CE1 commet sur ces items, n'a ete verifie qu'ici, en jouant. Le reste de la section 7 est inchange.

**Identifiants** : aucun neuf, aucun renomme. `SOU-CE1-R1` levee.

**Signe** : `nadia-prof`, s50, instance A, 2026-10-04.
