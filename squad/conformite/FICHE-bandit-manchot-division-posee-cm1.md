# Fiche de conformite - bandit-manchot-division-posee-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-division-posee.yaml`.
- **Entree de catalogue** : `bandit-manchot-division-posee-cm1`, titre servi *"La division posee"*,
  consigne servie *"Pose l'operation, puis choisis le bon quotient et le bon reste."*,
  `nombreDeManches: 10`.
- **Statut global** : **VALIDE SOUS RESERVE** (Done pedagogique **prononce le 2026-09-04** par
  `nadia-prof`, apres avoir joue). **`MAT-CM1-05` ferme** : `non couvert` -> **`couvert-valide`**,
  **a la condition que la mention de couverture partielle ci-dessous soit ecrite dans la matrice**.
  Une reserve nommee, `POS-R1`, non bloquante.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, section 5.4
  (`POS-1` a `POS-4`).
- **Derniere mise a jour** : 2026-09-04 (**verdict de `nadia-prof` apres avoir joue**, s28).

## Competence visee

`Effectuer une division euclidienne simple (quotient et reste) par un diviseur a 1 chiffre.`
(`MAT-CM1-05`, Mathematiques, CM1, Nombres et calculs).

## **`POS-1` - mention exigee d'avance, a reprendre mot pour mot dans la matrice par `camille-po`**

> *"le resultat atteste le calcul ; la technique posee elle-meme - disposition, retenues, produits
> partiels - n'est ni montree ni verifiee par le produit."*

**Sans cette mention ecrite dans la matrice, je ne ferme pas la ligne.** Elle est ecrite mot pour
mot dans l'en-tete du paquet pour etre reprise sans reformulation.

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit et lance, **partie complete de dix
manches par appels HTTP reels**, y compris en me trompant deux fois de suite. **Et j'ai recalcule
les onze divisions et les vingt-deux distracteurs moi-meme** - un item de onze n'est jamais vu dans
une partie de dix manches. **Ce n'est pas un audit degrade.**

### 1. Exactitude - onze divisions sur onze

224/6 = 37 r 2 ; 299/7 = 42 r 5 ; 213/4 = 53 r 1 ; 234/9 = 26 r 0 ; 323/5 = 64 r 3 ; 152/8 = 19 r 0 ;
215/3 = 71 r 2 ; 177/2 = 88 r 1 ; 312/9 = 34 r 6 ; 879/7 = 125 r 4 ; 351/6 = 58 r 3. **Onze justes
sur onze**, quotient **et** reste recalcules un par un.

### 2. `POS-4` - les deux exigences du libelle

- **Diviseur a un seul chiffre : les onze**, sans exception. Tenue.
- **Le reste est demande a chaque fois** : la reponse est toujours de la forme *"Q reste R"*, jamais
  le quotient seul. **Un paquet qui ne demanderait que le quotient ne servirait que la moitie de la
  ligne** ; celui-ci sert *"quotient et reste"* en entier. Tenue.
- **Reste nul sur deux items** (234/9, 152/8), **non nul sur les neuf autres**. C'est ce que j'avais
  demande. Tenue.

### 3. `FER-1`, `FER-2`, `FER-3`

- **`FER-1`** : la parenthese du libelle est *"(quotient et reste)"*, et les deux sont demandes dans
  chaque bonne reponse. Tenue.
- **`FER-2`** : **11 items**, au-dessus du plancher de dix. Tenue.
- **`FER-3`** : **non applicable**, meme lecture que sur la multiplication posee - le libelle
  demande une seule operation. C'est une lecture, pas une derogation.

### 4. `POS-3` - chaque distracteur est une erreur classique, et elles sont arithmetiquement vraies

Deux erreurs declarees : **reste superieur au diviseur** (la division s'est arretee un cran trop
tot) et **quotient sans reste** (le reste non nul est annonce a zero).

**J'ai verifie que les distracteurs du premier type sont coherents**, c'est-a-dire que
`quotient x diviseur + reste` retombe bien sur le dividende : *36 reste 8* pour 224/6, *41 reste 12*
pour 299/7, *17 reste 16* pour 152/8, et ainsi de suite - **les onze le sont.** Ce n'est pas un
detail : un distracteur incoherent serait une erreur qu'aucun enfant ne commet, donc un distracteur
mort. **Ceux-ci sont exactement l'erreur de l'enfant qui s'arrete trop tot.** Tenue.

### 5. `POS-2` - tenue sur sa lettre, et **`POS-R1` sur son esprit**

**Sur sa lettre** : les quotients des trois propositions ont le meme nombre de chiffres sur les onze
items. Tenue, et mordue par un test.

- **`POS-R1` (nouvelle, datee du 2026-09-04, non bloquante) - la structure des trois propositions se
  repete, et elle donne le quotient.**

  **Mesure, faite par moi sur les onze items** : **neuf items sur onze** portent un distracteur de la
  forme *"Q reste 0"* **avec exactement le quotient de la bonne reponse**. A l'ecran, cela donne
  *"71 reste 2 / 70 reste 5 / 71 reste 0"* : **le quotient qui apparait deux fois est le bon**, et
  l'enfant qui remarque la regularite n'a plus a le chercher. Sur ces neuf items, il lui reste a
  decider entre *reste 2* et *reste 0* - ce qui, lui, demande toujours de poser la division.

  **Et sur les deux items a reste nul (234/9 et 152/8), l'item se repond sans diviser du tout** :
  leurs **deux** distracteurs ont un reste **superieur ou egal au diviseur** (*25 reste 9*,
  *24 reste 18* ; *18 reste 8*, *17 reste 16*). La seule regle *"le reste est plus petit que le
  diviseur"* - que l'indice enonce lui-meme - les elimine tous les deux. **Deux items sur onze ne
  mesurent rien de la technique.**

  **Pourquoi c'est une reserve et non un refus** : sur les neuf autres, **le reste ne se trouve pas
  sans poser l'operation**, donc l'item garde sa mesure ; et **savoir que le reste est plus petit que
  le diviseur est une competence reelle de la division euclidienne**, pas une astuce de test - un
  enfant qui elimine par cette regle raisonne juste. **Le defaut est la repetition, pas l'erreur.**

  **Ce que je demande pour le prochain lot de cette ligne** : varier la troisieme proposition plutot
  que de la fabriquer toujours de la meme facon. Un distracteur au **quotient faux et au reste
  valide** (par exemple *"36 reste 5"* pour 224/6, ou 36x6+5 ne fait pas 224 mais ou rien dans la
  forme ne le trahit) oblige a poser l'operation entiere, et ferme les deux echappatoires d'un coup.
  **Je donne la propriete, pas la liste : les valeurs sont a `marc-dev`.**

### 6. Joue jusqu'au bout

Partie complete, **dix manches**, ecran de fin atteint. Sur erreur, l'enfant reste sur l'item et
recoit un indice qui **enonce la regle du reste** sans donner la reponse. **La consigne de catalogue
est la meilleure formulation du sprint** : *"Pose l'operation, puis choisis le bon quotient et le bon
reste."* Elle nomme les deux choses que la ligne demande.

### 7. Ce dont je reste la seule garde

| Propriete | Qui la tient |
|---|---|
| **`POS-1`** - que la mention soit ecrite dans la matrice | **moi**, et **`camille-po`** en l'ecrivant |
| **`POS-3`** - qu'un distracteur soit vraiment l'erreur qu'il declare | **moi seule** pour le jugement |
| **`POS-R1`** - qu'une structure de propositions ne se repete pas au point de donner la reponse | **moi seule.** Aucun test ne compare la **forme** des triplets d'un item a l'autre, et c'est en jouant, pas en lisant, que je l'ai vue |
| **L'exactitude des onze divisions** | **moi**, mais recalculable par quiconque |
