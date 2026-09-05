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
- **Ajout du 2026-09-05 (s29), sans rien retirer de ce qui precede** : un **second audit** est
  ecrit en fin de fiche - rejeu borne de `POS-R1` sur le commit `8644932`, **REFUSE**. `POS-R1`
  reste ouverte. **Le Done du 2026-09-04 n'est pas revoque et `MAT-CM1-05` reste
  `couvert-valide`.**

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

---

# Second audit - rejeu borne de `POS-R1` (s29, 2026-09-05)

- **Commit audite** : `8644932` (`[MARC] s29: S29-6`).
- **Objet du rejeu** : payer `POS-R1`, ouverte le 2026-09-04 et aggravee par mon propre recompte du
  2026-09-05 (arbitrage prealable de s29, sections 1.4 et 7.1 - **onze items sur onze**, et non
  neuf, portaient un distracteur eliminable sans diviser).
- **Verdict du rejeu** : **REFUSE**. **`POS-R1` n'est pas levee.** La forme interdite a bien
  disparu ; **une seconde regularite, de la meme famille et plus exploitable que la premiere, l'a
  remplacee.**
- **Ce qui ne change pas** : `MAT-CM1-05` reste **`couvert-valide`**. **Je ne revoque pas le Done du
  2026-09-04** - il a ete rendu, son motif est ecrit, et un verdict efface n'a aucune valeur de
  preuve. Le verdict ci-dessus porte sur le **rejeu**, pas sur la ligne. **Rien du bloc precedent
  n'est modifie ni retire.**
- **Audite par** : `nadia-prof`, instance B de la parallelisation (`PAR-0` a `PAR-8`).

## Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `ee3ef70`, reacteur reconstruit, jar relance, **partie complete
de dix manches par appels HTTP reels**. **Et j'ai recalcule les onze divisions et les vingt-deux
distracteurs**, un par un, comme le 2026-09-04. **Ce n'est pas un audit degrade.**

## 1. Ce que la correction tient, et je le verifie avant de refuser

| Ce que j'avais prescrit le 2026-09-05 (section 1.4) | Etat |
|---|---|
| La forme *"quotient - 1, reste + diviseur"* est **interdite** | **disparue des vingt-deux distracteurs** |
| Chaque distracteur a un **reste valide**, strictement inferieur au diviseur | **tenu sur les vingt-deux**, verifie un par un |
| Chaque distracteur a un **quotient faux** | tenu sur les vingt-deux |
| `POS-2` sur sa lettre : meme nombre de chiffres au quotient, chiffre des unites partage | tenu sur les onze items |
| `POS-4` : diviseur a un chiffre, reste demande, reste nul sur deux items | tenu, inchange |
| Les onze divisions restent exactes | **onze justes sur onze**, quotient et reste recalcules |

**La propriete que j'ai ecrite noir sur blanc a ete tenue.** L'echappatoire du 2026-09-04 - eliminer
un distracteur par la seule regle *"le reste est plus petit que le diviseur"* - **n'existe plus sur
aucun des onze items.** C'est un travail fait, et il faut le dire avant le reste.

## 2. Une correction que je fais a mon propre constat avant de m'en servir

**Ma prescription du 2026-09-05 etait insuffisante, et c'est mon erreur, pas celle de
`marc-dev`.** J'avais ecrit : *"un distracteur de division a un reste valide et un quotient faux ;
la forme 'quotient - 1, reste + diviseur' est interdite"*, en ajoutant *"propriete a tenir, pas de
valeurs prescrites"*. **Cette phrase decrit un distracteur pris seul. `POS-R1` porte sur une
serie.** Une prescription item par item ne pouvait pas, par construction, empecher une regularite
d'un item a l'autre - et c'est exactement ce qui s'est produit.

**Ce qui gouverne reste donc le texte de `POS-R1` lui-meme**, ecrit le 2026-09-04 et cite ici sans
un mot de changement :

> *"Ce que je demande pour le prochain lot de cette ligne : **varier la troisieme proposition plutot
> que de la fabriquer toujours de la meme facon.**"*

**Ce n'est pas un poteau deplace : c'est le poteau d'origine, et il n'est pas atteint.** Les deux
distracteurs sont desormais fabriques **chacun toujours de la meme facon** - distracteur A =
quotient juste **+ 10**, reste recopie ; distracteur B = quotient juste **- 2**, reste recalcule -
sur les onze items sans exception.

## 3. Le motif du refus - la bonne reponse est la valeur du milieu, onze fois sur onze

**Mesure, faite par moi sur les onze items du fichier, puis verifiee a l'ecran sur les dix manches
tirees :**

| Item | Les trois quotients proposes | Bonne reponse | Rang |
|---|---|---|---|
| 224 / 6 | 35, 37, 47 | 37 | **milieu** |
| 299 / 7 | 40, 42, 52 | 42 | **milieu** |
| 213 / 4 | 51, 53, 63 | 53 | **milieu** |
| 234 / 9 | 24, 26, 36 | 26 | **milieu** |
| 323 / 5 | 62, 64, 74 | 64 | **milieu** |
| 152 / 8 | 17, 19, 29 | 19 | **milieu** |
| 215 / 3 | 69, 71, 81 | 71 | **milieu** |
| 177 / 2 | 86, 88, 98 | 88 | **milieu** |
| 312 / 9 | 32, 34, 44 | 34 | **milieu** |
| 879 / 7 | 123, 125, 135 | 125 | **milieu** |
| 351 / 6 | 56, 58, 68 | 58 | **milieu** |

**Onze sur onze.** C'est arithmetiquement force : un distracteur est toujours au-dessus (+10),
l'autre toujours en dessous (-2). **La bonne reponse ne peut donc jamais etre ni la plus grande ni
la plus petite.**

**Ce que cela donne pour l'enfant, et je l'ai vu a l'ecran** : *ranger trois nombres a deux chiffres
et presser celui du milieu*. C'est un geste de CE1. **Il gagne les onze items sans poser une seule
division**, sur un jeu qui s'appelle *La division posee* et dont la consigne dit *"Pose
l'operation"*.

**Une seconde lecture donne le meme resultat** : sur les onze items, **exactement deux propositions
partagent le meme reste** (la bonne et le distracteur A, qui le recopie), et **la plus petite des
deux est la bonne**. Onze fois sur onze.

**Et c'est plus exploitable que le defaut que ce rejeu corrige.** Le 2026-09-04, la regularite
donnait le **quotient** sur neuf items, et il restait a trouver le **reste** - l'item gardait donc
une part de sa mesure. **Ici la regularite donne la reponse entiere**, reste compris, puisque le
reste voyage avec le quotient choisi. **`POS-2` - *l'item ne doit pas se repondre sans poser
l'operation* - n'est pas tenue, sur les onze.**

> **Propriete a tenir, et cette fois elle porte sur la serie, pas sur l'item** :
> **le rang de la bonne reponse dans l'ordre des trois quotients varie d'un item a l'autre.** Sur un
> paquet de onze, aucun des trois rangs - la plus petite, celle du milieu, la plus grande - ne porte
> plus de la moitie des items, et aucun n'est absent. Concretement : sur certains items les **deux**
> distracteurs sont au-dessus du quotient juste, sur d'autres les **deux** sont en dessous.
> **Les valeurs sont a `marc-dev` ou `lea-dev`.**

## 4. Ce que je n'oppose pas, et je le dis pour que la reprise ne corrige pas la mauvaise chose

- **Les deux erreurs declarees sont legitimes.** *Retenue oubliee* et *produit partiel non decale*
  sont deux des quatre erreurs que **j'ai moi-meme enumerees** dans `POS-3` le 2026-09-04. Je ne
  peux pas les refuser aujourd'hui, et je ne le fais pas.
- **Le fait qu'aucun distracteur ne reconstitue le dividende** (47 x 6 + 2 ne fait pas 224) **n'est
  pas un motif.** Un enfant qui oublie une retenue produit precisement un resultat qui ne se verifie
  pas ; l'exiger reviendrait a n'admettre que des erreurs coherentes, ce qui est le contraire d'une
  erreur classique. J'avais loue la coherence des anciens distracteurs parce qu'*arreter la division
  trop tot* est, elle, une erreur coherente. **Les deux familles sont recevables, et je ne fais pas
  de ma louange d'hier une exigence d'aujourd'hui.**
- **La consigne, l'indice, le chemin d'erreur et `POS-1`** sont inchanges et restent bons.

## 5. Un fait technique qui explique pourquoi personne d'autre ne l'a vu

**Le garde-fou qui aurait attrape ce defaut existe, il connait la strategie en cause, et il est
aveugle sur ce paquet.** `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` classe chaque
item en `LA_PLUS_PETITE`, `INTERMEDIAIRE` ou `LA_PLUS_GRANDE` et refuse qu'une strategie de rang
depasse **50 %** d'un paquet. **Ce paquet est a 100 % `INTERMEDIAIRE`** - et il est **declare hors
du domaine du garde-fou**, dans le fichier de test lui-meme, depuis s28 :

> *"11 items sur 11 ecrivent chaque proposition sous la forme 'quotient reste X' : la lecture de
> valeur s'arrete au quotient [...] si bien que valeurDe rend null sur les trois propositions de
> chaque item."*

**Je decris le symptome et je laisse la cause a `theo-archi`** : c'est sa frontiere, pas la mienne.
Le symptome est celui-ci - **le seul paquet du depot dont la bonne reponse porte un rang constant
sur la totalite de ses items est aussi le seul que le garde-fou de rang ne sait pas lire.** Ce n'est
pas une coincidence a mes yeux : c'est la definition d'un garde-fou qui ne garde plus rien, et le
depot a deja sanctionne cette famille plusieurs fois.

**Tant que ce paquet reste hors du domaine, je suis la seule garde de son rang, et je le suis en
jouant.**

## 6. Etat des reserves de ce jeu au 2026-09-05

- **`POS-1`** (mention de couverture partielle dans la matrice) : **inchangee**, toujours exigee.
- **`POS-R1`** (une structure de propositions qui se repete au point de donner la reponse) :
  **toujours ouverte, non levee, reformulee ci-dessous et datee. Elle n'est ni effacee ni durcie** -
  son texte du 2026-09-04 tient tel quel ; ce qui change est le motif de fait qui la maintient.

  > **`POS-R1`, etat du 2026-09-05.** La forme *"quotient - 1, reste + diviseur"* a bien ete
  > retiree. **Elle est remplacee par une regularite de rang** : distracteur A = quotient + 10,
  > distracteur B = quotient - 2, sur les onze items, si bien que **la bonne reponse est la valeur
  > du milieu onze fois sur onze**. La reserve reste ouverte au meme motif qu'a son ouverture : *une
  > structure de propositions ne doit pas se repeter au point de donner la reponse*. **Ce qui est
  > demande est ecrit en section 3.**

## 7. Ce que ce refus ne dit pas

- **Il ne revoque pas le Done pedagogique du 2026-09-04.** La ligne reste verte, avec sa reserve
  ouverte et son historique lisible.
- **Il ne dit pas que le travail est nul** : la premiere echappatoire est reellement fermee, et la
  propriete que j'avais prescrite est reellement tenue. **La prescription etait insuffisante, et
  elle etait de moi.**
- **Il ne bloque pas le sprint suivant.** L'increment retourne a `camille-po`.
