# Fiche de conformite - bandit-manchot-multiplication-posee-cm1

- **Mecanique** : bandit manchot.
- **Contenu** : `adapters/adapter-content/src/main/resources/contenu/cm1-multiplication-posee.yaml`.
- **Entree de catalogue** : `bandit-manchot-multiplication-posee-cm1`, titre servi *"La
  multiplication posee"*, consigne servie *"Pose l'operation, puis choisis le bon resultat."*,
  `nombreDeManches: 10`.
- **Statut global** : **VALIDE** (Done pedagogique **prononce le 2026-09-04** par `nadia-prof`,
  apres avoir joue). **`MAT-CM1-04` ferme** : `non couvert` -> **`couvert-valide`**, **a la condition
  que la mention de couverture partielle ci-dessous soit ecrite dans la matrice**. Aucune reserve.
- **Arbitrage prealable de `nadia-prof`** :
  `ARBITRAGE-PREALABLE-s28-ce-qui-ferme-une-ligne-et-le-prix-des-quatre-lots.md`, section 5.4
  (`POS-1` a `POS-4`).
- **Derniere mise a jour** : 2026-09-04 (**verdict de `nadia-prof` apres avoir joue**, s28).

## Competence visee

`Effectuer une multiplication posee d'un nombre a 2-3 chiffres par un nombre a 1-2 chiffres.`
(`MAT-CM1-04`, Mathematiques, CM1, Nombres et calculs).

## **`POS-1` - mention exigee d'avance, a reprendre mot pour mot dans la matrice par `camille-po`**

> *"le resultat atteste le calcul ; la technique posee elle-meme - disposition, retenues, produits
> partiels - n'est ni montree ni verifiee par le produit."*

**Sans cette mention ecrite dans la matrice, je ne ferme pas la ligne.** Je l'avais dit bloquant le
2026-09-03 et je le tiens. `marc-dev` l'a ecrite **mot pour mot dans l'en-tete du paquet** pour
qu'elle soit reprise sans reformulation : **c'est la bonne conduite**, et cela vaut le regime du mot
pour mot.

**Pourquoi je ferme malgre cette limite, et ce n'est pas une commodite** : quatre paquets de calcul
sont deja `couvert-valide` sur ce produit sur le meme raisonnement - **le resultat atteste le
calcul**. C'est un precedent que j'ai pose quatre fois et que je ne deplace pas. Et il y a mieux ici
qu'ailleurs : **les erreurs classiques de la technique posee font des distracteurs naturellement
plausibles**, ce qui manque a d'autres matieres.

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.

### Conditions de l'audit

**J'ai joue.** Arbre `round-two` a `aac5d88`, jar reconstruit et lance, **partie complete de dix
manches par appels HTTP reels**, y compris en me trompant deux fois de suite. **Et j'ai recalcule
les onze produits moi-meme**, ainsi que la valeur de chaque distracteur - un item de onze n'est
jamais vu dans une partie de dix manches. **Ce n'est pas un audit degrade.**

### 1. Exactitude - onze produits sur onze

47x6=282, 68x4=272, 52x6=312, 326x4=1 304, 428x3=1 284, 216x4=864, 27x14=378, 34x18=612,
213x18=3 834, 132x13=1 716, 63x7=441. **Onze justes sur onze**, recalcules un par un.

### 2. `POS-4` - les quatre combinaisons du libelle

**Servies, et je les ai comptees** : 2 chiffres par 1 (47x6, 68x4, 52x6, 63x7), 3 par 1 (326x4,
428x3, 216x4), 2 par 2 (27x14, 34x18), 3 par 2 (213x18, 132x13). **Aucune des quatre ne manque.**
Tenue.

### 3. `POS-2` - l'item ne se repond pas sans poser l'operation

Sur les onze items, les trois propositions ont **le meme nombre de chiffres**, et **au moins un
distracteur partage le chiffre des unites** avec la bonne reponse. Verifie sur les onze, et **mordu
par un test** - c'est la seule de mes quatre conditions de calcul pose qui soit calculable, et elle
l'est parce que je l'avais ecrite sous une forme mesurable. Tenue.

**Une observation, datee du 2026-09-04, que je ne compte pas comme un defaut** : sur les quatre
items a multiplicateur de deux chiffres, le distracteur *"produit partiel non decale"* vaut environ
la moitie de la bonne reponse (135 contre 378 ; 306 contre 612 ; 1 917 contre 3 834). **Un enfant
qui estime l'ordre de grandeur l'elimine sans poser l'operation.** Je l'accepte, et je dis pourquoi
plutot que de le poser : **estimer un ordre de grandeur avant de calculer est une competence du
programme**, pas une astuce ; et les deux propositions qui restent (378 / 358, 612 / 812,
3 834 / 3 814) **ne se departagent que par le calcul**. L'item garde donc sa mesure.

### 4. `POS-3` - chaque distracteur est une erreur classique nommee

**Declaree dans le paquet** - retenue oubliee, chiffre de produit errone, produit partiel non
decale - **et verifiee par moi sur chaque valeur**. Les deux plus parlantes : 27x14 -> 135, qui est
exactement 27x4 + 27 **non decale** ; 47x6 -> 242, qui est le produit **sans la retenue** reportee.
**Aucun distracteur tire au hasard, aucun distracteur absurde.**

**C'est la meilleure serie de distracteurs du sprint, et je le dis nommement** : sur cette matiere,
**l'erreur classique est le contenu**. Un enfant qui choisit 242 vient de commettre, dans sa tete,
l'erreur exacte que l'exercice veut lui faire voir - et l'indice qui suit (*"calcule chaque produit
avec sa retenue, puis ajoute-la a la colonne suivante"*) **nomme cette erreur-la**.

### 5. `FER-1`, `FER-2`, `FER-3`

- **`FER-1`** : la parenthese du libelle est *"2-3 chiffres par 1-2 chiffres"*, et les quatre
  combinaisons sont servies (section 2). Tenue.
- **`FER-2`** : **11 items**, au-dessus du plancher de dix. Tenue.
- **`FER-3`** : **je ne l'applique pas a cette ligne, et je dis pourquoi plutot que de la passer
  sous silence.** Le libelle demande **une** operation - effectuer une multiplication posee. Exiger
  deux taches distinctes reviendrait a exiger que la ligne serve autre chose qu'elle-meme. `FER-3`
  vise les libelles qui enumerent plusieurs verbes ou plusieurs objets ; celui-ci n'en a qu'un.
  **C'est une lecture, pas une derogation, et elle vaut identiquement pour la division posee.**

### 6. Joue jusqu'au bout

Partie complete, **dix manches**, ecran de fin atteint. Sur erreur, l'enfant **reste sur l'item**,
recoit *"Ce n'est pas ca. Essaie encore !"* et un indice qui **decrit la technique** sans donner le
resultat. **La consigne de catalogue dit a l'enfant ce qu'il doit faire** - *"Pose l'operation, puis
choisis le bon resultat"* - ce qui est exactement ce qu'il faut sur une ligne ou le produit ne voit
pas la technique : **le produit demande le geste qu'il ne peut pas verifier, plutot que de faire
comme s'il n'existait pas.**

### 7. Ce dont je reste la seule garde

| Propriete | Qui la tient |
|---|---|
| **`POS-1`** - que la mention soit reellement ecrite dans la matrice | **moi**, et **`camille-po`** en l'ecrivant. Aucun test ne lit la matrice |
| **`POS-3`** - qu'un distracteur soit vraiment l'erreur classique qu'il declare | **moi seule.** Le marqueur textuel est mordable, **sa verite non** |
| **L'exactitude des onze produits** | **moi**, mais elle est ici **recalculable par quiconque** - c'est la seule matiere du sprint ou mon controle est reproductible sans moi |
| **`POS-2`** | **partagee** : mordue par un test sur les items reels. C'est ce que j'avais annonce comme calculable, et ca l'est |
