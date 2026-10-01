# Arbitrage prealable apres s46 - trois instances refusees : c'est le vivier instruit qui borne, pas
# ma capacite d'audit, et ce qui rouvrirait la derivation

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-10-01.
- **Arbre** : `round-two` a `8b0fe8b4` (cloture de s45). s46 planifie a deux instances (`DEC-0102`),
  non ouvert.
- **Question posee** : une borne a trois instances paralleles de moi est-elle derivable aujourd'hui,
  de facon perenne ou pour une nouvelle fenetre bornee, en reappliquant la methode
  d'`ARBITRAGE-PREALABLE-s29...` (section 13) et d'`ARBITRAGE-PREALABLE-s32...` (sections 4 et 9) ?
- **Reponse, avant tout le reste** : **non. Je refuse de deriver une borne a trois instances, ni
  perenne ni en fenetre.** Le motif tient en une phrase : **ce qui borne le produit aujourd'hui n'est
  plus ma capacite d'audit, c'est le nombre de lignes instruites a auditer**, et une troisieme
  instance ajouterait 4,0 de borne que rien d'instruit ne remplit, en triplant la surface de couture.
  La section 5 dit ce qui me ferait rouvrir la question, en conditions mesurables.
- **Ma borne a deux instances ne bouge pas** : 8,0, plafond individuel 5,0, au plus trois lots par
  instance (`PAR-8`, `PAR-9`). Ce document ne la touche pas.
- **Audit degrade, et je le declare** : aucun increment n'est en jeu, je n'ai rien joue pour ce
  document. J'ai lu mes deux derivations, `DEC-0083`, `DEC-0084`, `DEC-0087`, `DEC-0102`,
  `DEC-0103`, les reviews et retros de s41, s42 et s45, et j'ai recompte la matrice (methode en
  section 2.1). C'est une mesure sur des fichiers, pas un verdict sur un jeu.
- **Ce document n'est pas bloquant pour s46**, qui reste a deux instances tel que `DEC-0102`
  l'engage.

---

## 1. Quatre corrections avant de m'en servir

### 1.1 Le CE1 porte une matiere au sens de ma borne, pas deux

`DEC-0103` ouvre le francais CE1 **sans aucune ligne** : le referentiel
`squad/programme/francais/competences-ce1.md` n'existe pas, il m'est route, et la matrice ne
recevra ses lignes qu'apres (`DEC-0103`, sections 3 et 4). **Une matiere sans ligne n'apporte aucun
lot a auditer.** Pour ce calcul, le CE1 compte une matiere, les mathematiques.

### 1.2 Le CP porte huit matieres dans la matrice, pas cinq

Recompte sur les tableaux de `squad/programme/MATRICE-couverture.md` : mathematiques, francais,
questionner le monde, anglais, arts plastiques, education musicale, EPS et EMC. Le chiffre ne change
pas la conclusion : ce qui compte est ce qui reste a fermer dans ces matieres, et la section 2 le
montre.

### 1.3 `DEC-0087` n'a pas porte de jugement pedagogique sur les trois instances

`DEC-0087` referme la fenetre **parce qu'elle avait ete ouverte pour trois sprints nommes**, pas
parce qu'un verdict aurait condamne le dispositif. **Le motif de fond du bornage est de moi, et il
est ailleurs** : `ARBITRAGE-PREALABLE-s32...`, section 9 - *"Un triplement achete de la couverture
en s32, et en partie en s33. Il cesse d'en acheter en s34"* -, parce que les huit lignes qui
fermaient sans paquet neuf etaient epuisees a la fin de s33. **C'est ce motif-la que je reteste
ici**, pas la date de fermeture d'une fenetre.

### 1.4 La verification qui rendait 12,0 non arbitraire n'est plus disponible

En s32, section 4.2, ma seule preuve etait : *"12,0 / 3 = 4,0 par instance, exactement le budget
de `PAR-9`, tenu a l'unite a s31"*. J'ajoutais une instance a une charge que les deux autres
**soutenaient deja**. **Mesure d'aujourd'hui** : la seconde instance a ete engagee a 4,0 a s42, a
1,5 a s45, a 3,0 dans le plan de s46. La charge de 4,0 par instance n'est plus une charge
**soutenue**, c'est un plafond **que l'on ne remplit plus**. La formule donne encore 12,0. Ce qui
l'empechait d'etre un chiffre invente, non.

---

## 2. La methode, reappliquee poste par poste

### 2.1 La formule de s32 donne toujours 12,0, et ce n'est pas elle qui decide

Capacite brute 3 x 5,0 = 15,0, retenue de couture 1,0 par paire, trois paires, -3,0 : **12,0**. Je
ne change rien a la formule. **En s32 ce n'est pas elle qui a decide** : c'est le test de la
section 9, ce qu'un triplement achete. Je le refais.

**Etat de depart, recompte et non recopie.** *Methode : lecture des tableaux de competence de
`squad/programme/MATRICE-couverture.md` (lignes 290 a 2201), une ligne de donnees classee par la
premiere cellule commencant par un statut.* **227 lignes : 108 `couvert-valide`, 3 `en cours`, 45
`non couvert`, 71 `non couvrable en l'etat`, soit 156 atteignables et 108 / 156 = 69,2 %.** Cela
confirme les chiffres transmis et la review de s45 (section 8).

### 2.2 La demande - ce qui reste instruit apres s46

**48 lignes restent a fermer. La question n'est pas leur nombre, c'est combien sont instruites et
jouables**, avec un verdict prealable ecrit par moi.

| Population, apres les cinq lignes de s46 | Lignes | Source |
|---|---|---|
| Hors mathematiques CE1, instruites jouables | **4** (`QLM-CP-15`, `EMC-CP-14`, `EMC-CM1-10`, `LVE-CP-06`) | `DEC-0102`, section 3 |
| Hors mathematiques CE1, ecartees pour motif de fond | 4 (`QLM-CP-16`, `EMC-CP-04`, `EMC-CM1-02`, `EMC-CM1-03`) | idem |
| Mathematiques CE1 `non couvert`, toutes instruites | 10 | `DEC-0102`, section 2 |
| Francais CE1 | **0** : aucun referentiel | `DEC-0103` |

**Les mathematiques CE1 sont plafonnees a une ligne par sprint** par une regle de `camille-po`,
motif de marge, reconduite pour la quatrieme fois a s46 (`DEC-0102`, section 2). Je ne la juge pas :
ce n'est pas une regle pedagogique. **Je constate seulement son effet** : un sprint peut puiser au
plus 1,0 dans ce vivier de dix lignes.

**Ce qu'un sprint peut donc planifier en lignes instruites, au tarif en vigueur** : une ligne de
mathematiques CE1 (1,0) plus les quatre lignes hors mathematiques (de 4,0 a 6,0 selon que je suis ou
non la source). **Soit 5,0 a 7,0 : moins que la borne a deux instances.** Et ce vivier se vide en un
sprint.

### 2.3 L'offre - la borne a deux instances n'est deja plus remplie

| Sprint | Instances | Borne | Engage | Lignes fermees |
|---|---|---|---|---|
| s41 | 2 | 8,0 | 8,0 | 6 |
| s42 | 2 | 8,0 | **7,0** | 4, plus une ouverte en partie |
| s43 | 1 | 5,0 | 4,0 | 3 |
| s44 | 1 | 5,0 | 4,0 | 3 |
| s45 | 2 | 8,0 | **5,5** (A 4,0, B 1,5 apres le retrait de `LVE-CM1-06`) | 4 |
| s46, planifie | 2 | 8,0 | **7,0** | 5 visees |

*Sources : reviews de s41 (section 6), s42 (section 7), s45 (section 6) ; rapports de s43 et s44 ;
`DEC-0102`, section 6.*

> **Sur les quatre derniers dispositifs a deux instances, trois n'ont pas rempli la borne de 8,0.**
> `DEC-0102` le dit en toutes lettres pour s46 : *"aucune combinaison entiere du vivier instruit
> n'atteint 8,0"*. Une troisieme instance ajouterait 4,0 a une borne dont 1,0 a 2,5 restent deja
> vides.

### 2.4 Le test de la section 9 de s32 - y a-t-il un terrain sans paquet neuf ?

En s32, le triplement achetait de la couverture **a une seule condition** : qu'une instance travaille
sur un terrain qui ne cree aucun paquet neuf. Il y en avait huit lignes.

**Aujourd'hui, la population `en cours` compte trois lignes** (deux en mathematiques CP, une en
mathematiques CE1). L'une d'elles, `MAT-CP-10`, est ouverte sur sa moitie textuelle et attend une
autre moitie (`ARBITRAGE-PREALABLE-s32...`, section 17.2). **Je n'ai pas verifie, ligne par ligne,
ce qu'il manque aux deux autres, et je ne le suppose pas.** Meme dans le cas le plus favorable, trois
lignes ne font pas le terrain d'une instance pendant un sprint.

> **Le motif qui avait borne la fenetre en s32 tient toujours, et il est plus fort qu'alors** : en
> s32, huit lignes justifiaient deux sprints de triplement. Aujourd'hui, aucun terrain de cette
> nature n'a la taille d'un sprint.

### 2.5 La retenue de couture ne tombe pas, et trois instances l'exposeraient davantage

Ma barre est inchangee depuis s29 (section 13.5) et s32 (section 4.3) : **un sprint parallele clos
sans aucune occurrence des formes (a), (b) ou (c)**.

- **s32, a trois instances** : une occurrence de la forme (c), trois allocations dans le registre
  d'identifiants a partir du meme compte (`DEC-0084`, section 3).
- **s42, a deux instances** : les deux instances ont ecrit leurs identifiants de reserve sous deux
  formes differentes, dont l'une echappe au garde-fou `PAR-5` (`retro.md` de s42, section 2 :
  *"tournant en parallele sans se lire, n'ont eu aucune occasion de converger"*). **Ce n'est pas
  une collision constatee, mais c'est la famille de la forme (c) : deux instances qui ecrivent dans
  le meme espace de noms sans se lire.** S'y ajoutent les trois incidents d'identifiant que j'ai causes moi-meme
  entre s40 et s42.
- **s45, a deux instances** : la retro ne note aucune collision. **Je ne l'ai pas reverifiee contre
  les trois formes, et je ne m'en sers donc pas comme d'une preuve.**

A trois instances, le nombre de paires passe de 1 a 3 (`ARBITRAGE-PREALABLE-s32...`, section 4.1).
**Un dispositif qui a produit une occurrence de la forme (c) il y a trois sprints, a une seule
paire, ne se triple pas sur la foi d'un sprint propre que personne n'a verifie.**

### 2.6 Ce que perd une troisieme instance, et ce qu'elle ne perd pas

**Elle ne perd pas de lecture croisee, parce qu'il n'y en a pas.** Je l'ai ecrit en s29, section
13.7 : *"deux paires d'yeux sur des lots disjoints, ce sont deux paires d'yeux, pas une
verification croisee."* Ce motif ne peut donc pas fonder mon refus, et je ne m'en sers pas.

**Elle disperse l'accumulation, et c'est mesure.** `PAR-0` : en serie, les trouvailles qui comparent
viennent gratuitement. Le dernier exemple date de s45 : en instruisant le motif `R6` sur l'anglais
CM1, j'ai trouve qu'il n'avait jamais ete instruit sur `saisie-mot-dicte-cp`, une ligne fermee depuis
s26 dans une autre matiere et a un autre niveau (`S46-VERIF-SAISIE-MOT-DICTE-CP`, review de s45,
section 7). **Cette trouvaille vient d'une instance qui tenait les deux terrains.** Avec cinq lignes
instruites reparties sur trois instances, chacune tiendrait un ou deux lots : **plus de jointures, et
moins d'image dans chaque tete.**

**Je n'ai pas de marge de qualite a convertir en debit.** A s45, en corrigeant un marqueur de source
au premier passage, j'ai moi-meme introduit une erreur de fait (`USN-CM1-R9`). Je ne l'impute pas au
parallelisme. Je dis seulement qu'un enchainement refus, correction et revalidation dans le meme
apres-midi est deja a la limite de ce que je verifie bien.

---

## 3. Le bilan de la fenetre a trois instances, recopie sans l'adoucir

| | Plafond | Mon attente | Resultat |
|---|---|---|---|
| s32 | 8 | 6 a 7 | **3** (`DEC-0084`) |
| s33 | 9 | 4 a 6 | **3** (`DEC-0087`, section 4) |
| s34 | 6 | 2 a 4 | **6** (`DEC-0087`, section 1) |
| **Fenetre** | | **14 a 17** (`ARBITRAGE-PREALABLE-s32...`, section 12) | **12** |

**s34 a ete un bon sprint a trois instances**, a 8,0 depenses pour six lignes. Je l'ecris parce que
c'est vrai, et parce que mon refus ne tient pas sur une idee que trois instances jugent moins bien.
**Il tient sur le fait que le terrain qui les rendait utiles n'existe plus.** Sur la fenetre entiere,
le resultat reste sous le bas de ma fourchette.

---

## 4. Le verdict

> **REFUS de deriver une borne a trois instances, le 2026-10-01, ni perenne ni pour une fenetre
> bornee.**
>
> **Motif, actionnable** : apres s46, il reste au plus 5,0 a 7,0 de lots instruits par sprint
> (section 2.2), pour une borne a deux instances de 8,0 deja non remplie trois fois sur quatre
> (section 2.3). Aucun terrain sans paquet neuf n'a la taille d'un sprint (section 2.4). La retenue
> de couture ne tombe pas (section 2.5). **Une troisieme instance ne financerait aucune ligne de
> plafond de plus, et triplerait la surface ou mes defauts de couture se produisent.**
>
> **Une borne perenne est refusee aussi pour une raison de methode.** Mes deux derivations, s29 et
> s32, reposent chacune sur un terrain qui s'epuise. Une borne sans terrain n'a pas d'objet, et je
> n'en pose pas.

**Ce qui ne changerait pas ce verdict** : le nombre de matieres ouvertes, ni le denominateur. Ce qui
compte est le nombre de lignes instruites et jouables au moment du planning.

---

## 5. Ce qui me ferait rouvrir la derivation

Ces trois conditions sont cumulatives. Ce ne sont pas des barres neuves : (ii) est ma barre de couture
de s29 et s32, (iii) est `PAR-13`, et (i) est le test de la section 9 de s32 ecrit du cote de la
demande.

1. **(i) Un vivier instruit superieur a la borne a deux instances.** Un document de planning de
   `camille-po`, ecrit avant le sprint, montre une composition a deux instances qui remplit 8,0
   (`PAR-8` respecte) **et laisse dehors au moins 4,0 de lots instruits**, soit la capacite d'une
   instance. **Deux leviers peuvent y mener, et aucun n'est a moi** : mon referentiel de francais
   CE1, une fois ecrit et ses lignes annotees (`DEC-0103`), ou une decision de `camille-po` sur la
   regle d'une ligne de mathematiques CE1 par sprint, qui retient aujourd'hui dix lignes instruites.
2. **(ii) Le dernier sprint parallele avant la derivation est clos sans aucune occurrence des formes
   (a), (b) ou (c)**, et c'est moi qui le verifie contre les trois formes dans le document de
   derivation. s45 en serait peut-etre un, mais je ne l'ai pas verifie (section 2.5).
3. **(iii) L'affectation des trois terrains tient `PAR-13`** : au plus une instance sur un terrain
   dont mes conditions n'ont jamais rencontre de contenu reel. Le francais CE1, quand il existera,
   sera ce terrain-la. Il ne s'ouvre donc pas a cote d'un autre terrain neuf.

**Si les trois sont reunies, la derivation se fera dans un document neuf, sur une fenetre bornee au
nombre de sprints que dure l'excedent du vivier**, comme en s32. La formule de s32 restera ma
methode, mais le chiffre sera recalcule sur les mesures de ce jour-la, sans reprendre 12,0
d'avance.

---

## 6. Ce dont je reste la seule garde, a deux instances comme a trois

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| Forme (c) sur les identifiants de condition et de reserve | le garde-fou `PAR-5` pour les formes qu'il reconnait, **moi pour les autres** (retro de s42, section 2) | partiellement |
| Formes (a) et (b) de couture | **moi seule** | (b) seulement, par un garde-fou d'agregat sur l'arbre assemble (`ARBITRAGE-PREALABLE-s32...`, section 3) |
| `CYC-1` quand les deux cycles d'un meme objet sont audites par deux instances | **moi seule**, par l'obligation de lecture | non |
| Les proprietes de portee D (`ARBITRAGE-PREALABLE-s29...`, section 13.2) | **moi seule**, par la passe transverse | non |

---

## 7. Ce que ce document ne fait pas

- Il ne prononce aucun Done pedagogique et ne touche a aucune fiche de jeu.
- Il ne modifie ni la matrice, ni le backlog, ni `DEC-0102`. Il ne priorise rien. Les deux leviers
  de la condition (i) appartiennent a `camille-po`, et je les nomme sans les actionner.
- Il ne change pas ma borne a deux instances (8,0), et il ne prononce pas la levee de sa retenue de
  1,0 prevue en s29, section 13.5 : je n'ai pas verifie s41 et s45 contre les trois formes.
- Il n'ecrit pas le referentiel de francais CE1 : c'est un geste distinct, qui m'est route par
  `DEC-0103`.
- Il ne pose aucune condition neuve et ne cree aucun identifiant de condition ou de reserve.
- Il ne rouvre pas la fenetre de `DEC-0083` et n'efface rien de mes deux derivations, qui restent
  lisibles telles qu'elles ont ete ecrites.
