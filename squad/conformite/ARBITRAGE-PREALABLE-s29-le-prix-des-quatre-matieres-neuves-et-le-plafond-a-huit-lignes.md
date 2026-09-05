# Arbitrage prealable de s29 - le prix reel des quatre matieres, pourquoi cinq lots ne tiennent pas
# dans cinq, et le plafond de fermeture avec sa composition

- **Auteur** : `nadia-prof`. **Date** : 2026-09-05. **Sprint** : s29, avant planning, apres la
  cloture de s28.
- **Branche** : `round-two` a `790584e` (cloture de s28, matrice a 43 / 138 = 31,2 %).
- **Perimetre arbitre par l'utilisatrice le 2026-09-05** : cinq lots de vingt items sur quatre
  matieres - sciences et technologie CM1 (19 lignes `non couvert`), EMC CP (12), EMC CM1 (11),
  questionner le monde CP (12 restantes) - annonces au tarif de 1,0 chacun, soit 5,0 sur ma borne
  de 5, pour une cible d'environ dix lignes fermees.
- **Ce que ce document est** : mon prix recompte, l'arithmetique de fermeture verifiee contre le
  contenu servi et contre deux garde-fous existants, ce que chaque lot doit apporter ligne par
  ligne, mes conditions opposables, et mon chiffrage de la marge de rejeu. **Il ne prononce aucun
  Done pedagogique** : aucun increment de s29 n'existe. **Il n'ecrit ni la matrice ni le backlog**
  (`camille-po`) et **il ne priorise pas** (l'utilisatrice).
- **Audit degrade, et je le declare** : je n'ai joue aucun increment pour ce document, et je n'ai
  pas rejoue ceux de s28 - je les ai joues avant-hier et mes verdicts tiennent. Ce que j'ai
  regarde ici : les quatre referentiels concernes, les 48 paquets de contenu servi (comptes item
  par item), les comptes d'items par ligne de referentiel derives du marqueur d'`ADR-0081`, le
  fragment d'ecran de retour, `Question`, `Verdict` et le garde-fou de `DEC-0064`. C'est une mesure
  sur des fichiers, pas une lecture de description - mais ce n'est pas un audit de jeu, et rien de
  ce qui suit n'est un verdict sur un increment.
- **Reponse directe a la question qui decide du sprint, avant tout le reste** : **le perimetre
  annonce ne tient pas. Il vaut 6,0 a 7,0, pas 5,0**, parce que je ne suis la source d'aucune des
  trois matieres neuves et que je l'ai ecrit moi-meme, de ma main, le 2026-09-03, dans les
  referentiels que j'ai rediges. **Ce qui tient dans 5,0 est quatre lots, pas cinq, et le plafond
  de fermeture est de huit lignes, pas dix.**

---

## 1. Cinq corrections que je fais a mes propres constats avant de m'en servir

C'est ma deuxieme habitude et elle passe avant le reste. Les cinq corrections ci-dessous portent
sur des textes que j'ai ecrits il y a moins de soixante-douze heures. **Trois sont des erreurs, une
est une lecture fautive de mon tarif par un tiers, une est une sous-estimation d'une de mes propres
reserves.** Aucune n'efface un verdict.

### 1.1 `SCI-1` est impossible telle que je l'ai ecrite, et elle contredit un arbitrage de moi

**Ce que j'ai ecrit le 2026-09-03** (`squad/programme/sciences-et-technologie/competences-cm1.md`) :

> *"`SCI-1` - **Apres une reponse fausse, le retour dit pourquoi elle est fausse, en une phrase.**"*

**Ce que le produit fait, lu et non suppose** - `adapters/adapter-web/src/main/resources/templates/fragments/retour.html`,
ligne 41 et son commentaire :

> *"Affichee systematiquement, que la reponse soit juste ou fausse - nadia-prof, arbitrage du
> 2026-08-06, S5-9 : 'l'enfant qui a trouve DENT par elimination a autant besoin de l'explication
> que celui qui s'est trompe'."*

**Trois faits, et chacun suffit a invalider ma formulation.** Le champ `explication` (`ADR-0024`)
est porte par l'**item**, pas par la proposition : il est identique quelle que soit la proposition
pressee. Il est affiche **apres chaque reponse**, juste ou fausse - et c'est **moi** qui l'ai
arbitre ainsi le 2026-08-06. Il ne sait donc pas ce que l'enfant a choisi, et un texte redige comme
un commentaire de l'erreur commise serait faux pour l'enfant qui a repondu juste.

**`SCI-1`, amendee le 2026-09-05, remplace la formulation du 2026-09-03** :

> **`SCI-1` (amendee)** - Chaque item de sciences porte une `explication` non vide. Elle dit
> **pourquoi la conception fausse placee en distracteur est fausse**, en une phrase, au present de
> verite generale, et elle se lit aussi bien par l'enfant qui a repondu juste que par celui qui
> s'est trompe. Elle ne commente jamais *"ta reponse"*.

**Ce que cette correction coute a l'equipe, mesure et pas estime** : sur les **48 paquets** du
contenu servi, **un seul** porte le champ `explication` - `cp-le-bon-son.yaml`, sur **6 de ses 15
items**. *Methode : `grep -c "explication" *.yaml` dans
`adapters/adapter-content/src/main/resources/contenu`, les deux autres occurrences du depot etant
de la prose de commentaire, verifiees une par une.* Demander vingt explications sur un lot, c'est
donc multiplier par quatre l'usage de ce champ dans tout le produit. **Ce n'est pas une raison de
retirer `SCI-1` : c'est la raison pour laquelle elle fait baisser mon plafond de fermeture** - un
champ qu'on n'a jamais rempli en serie se rate en serie.

### 1.2 Deux erreurs de fond dans le referentiel de sciences que j'ai ecrit

Mon mandat dit que si un referentiel me parait faux, je le signale plutot que de valider contre
lui. **Il se trouve que celui-la est de moi.**

- **Ligne 7** : *"distinguer les regimes alimentaires (**vegetarien**, carnivore, omnivore)"*.
  **`Vegetarien` est un choix alimentaire humain ; l'animal qui mange des vegetaux est
  `herbivore`.** Le triplet du cycle 3 est *herbivore, carnivore, omnivore*, et `FER-1` fait de
  cette parenthese une liste **contractuelle** : un lot ecrit contre mon libelle produirait dix
  items ou le mot faux est en position de bonne reponse. **Correction a faire avant qu'un item ne
  soit ecrit** : *"(herbivore, carnivore, omnivore)"*.
- **Ligne 9** : *"nommer les grandes fonctions du corps humain (**nutrition**, digestion,
  respiration, circulation) et l'organe principal de chacune"*. **La nutrition n'est pas la
  quatrieme de ces fonctions : c'est la famille qui contient les trois autres.** Ecrite ainsi, la
  ligne demande *"quel est l'organe principal de la nutrition ?"*, question qui a plusieurs
  reponses defendables - c'est-a-dire exactement ce que `HIS-2` refuse. **Correction proposee** :
  *"Nommer les grandes fonctions de nutrition du corps humain (digestion, respiration, circulation)
  et l'organe principal de chacune."*

**Ces deux corrections sont routees a `camille-po` via `sofia-sm`.** Je ne modifie pas la matrice :
elle recopie les libelles, et c'est elle qui porte le lien avec les codes de ligne d'`ADR-0081`.

### 1.3 `EMC-3` et `FER-3` ne peuvent pas tenir ensemble sur la ligne 3, telles que je les ai ecrites

**`EMC-3`, du 2026-09-03** : *"Sur la ligne 3, la bonne reponse est **toujours** *en parler a un
adulte*, jamais une solution que l'enfant appliquerait seul."*
**`FER-3`, du 2026-09-03** : *"Les items qui servent la ligne ne posent pas tous la meme tache."*

Une ligne dont **tous** les items ont litteralement la meme bonne reponse n'a qu'une tache, et
**l'enfant la trouve des le deuxieme item sans lire l'enonce**. Mes deux conditions se refusent l'une
l'autre : la ligne 3 d'EMC CP - et la ligne 2 d'EMC CM1, qui herite d'`EMC-3` - ne pourrait jamais
fermer.

**`EMC-3`, amendee le 2026-09-05** :

> **`EMC-3` (amendee)** - Sur ces lignes, **aucune bonne reponse n'est une solution que l'enfant
> appliquerait seul**, et aucun item ne met l'enfant lui-meme en position de victime ou d'auteur.
> Aucun item ne detaille une violence. La bonne reponse **peut** etre *en parler a un adulte* ; elle
> peut tout aussi bien etre la **reconnaissance de la situation** (*"est-ce une moquerie ou un
> accident ?"*) ou l'**identification de l'adulte a qui s'adresser selon le lieu**. Ce sont les deux
> taches que `FER-3` exige, et elles existent toutes deux dans le libelle de la ligne.

**Ce que je ne change pas** : l'interdiction elle-meme. Ce que je change est la confusion entre une
interdiction et une reponse unique imposee.

### 1.4 Ma reserve `POS-R1` est sous-estimee, et le recompte l'aggrave

**Ce que j'ai ecrit le 2026-09-04** : *"sur neuf items sur onze, un distracteur porte exactement le
quotient de la bonne reponse avec un reste a zero ; sur deux items sur onze (234/9, 152/8), les deux
distracteurs ont un reste superieur ou egal au diviseur."*

**Recompte item par item sur `cm1-division-posee.yaml`** (methode : lecture des onze triplets,
comparaison de chaque reste de distracteur a son diviseur) :

| Fait | Compte |
|---|---|
| Items dont un distracteur porte le quotient juste avec reste 0 | **9 / 11** - *confirme* |
| Items dont les **deux** distracteurs ont un reste >= au diviseur, donc reponse trouvable sans diviser | **2 / 11** - *confirme* |
| **Items dont au moins un distracteur a un reste >= au diviseur** | **11 / 11** - *ce que je n'avais pas vu* |

Les onze distracteurs de la forme *"quotient - 1, reste + diviseur"* ont, **par construction**, un
reste superieur ou egal au diviseur : `36 reste 8` pour un diviseur 6, `41 reste 12` pour 7,
`52 reste 5` pour 4, et ainsi de suite sur les onze. **Sur la totalite du paquet, l'enfant elimine
un distracteur par la seule regle "le reste est plus petit que le diviseur", sans poser
l'operation** ; sur deux items il elimine les deux.

**Ce que cela change.** Ce n'est pas onze maladresses : c'est **une regle de fabrication qui produit
un distracteur invalide a tous les coups**. Et c'est un manquement a `POS-2`, la condition que
j'avais moi-meme posee - *"l'item ne doit pas se repondre sans poser l'operation"* - sur la ligne
que j'ai fermee le 2026-09-04 sous cette condition. **Je ne revoque pas ce Done** : il a ete rendu,
le motif est ecrit, un verdict efface n'a aucune valeur de preuve. **Je dis que la reserve est plus
lourde que je ne l'ai ecrite, et que c'est le meilleur argument pour la payer a s29.**

**Propriete a tenir, pas de valeurs prescrites** : un distracteur de division a un **reste valide**
(strictement inferieur au diviseur) et un quotient faux. La forme *"quotient - 1, reste + diviseur"*
est interdite. Les valeurs sont a `marc-dev` ou `lea-dev`.

### 1.5 Mon regime du mot pour mot est lu par item ; il se compte par passage

**Ce que le backlog en a tire** (`docs/backlog/backlog.md`, s29) : *"0,25 chacune si le texte fourni
est repris tel quel"* - six corrections, donc 1,50.

**Ce que j'ai mesure a s28** : j'ai chiffre la dette des paquets a voix - **onze textes** sur six
jeux, tous fournis mot pour mot - a **0,25 au total**. Lues ensemble, les deux lectures different
d'un facteur onze. **La mienne est la bonne, et voici son motif** : le 0,25 paie une **reverification
d'identite**, pas une pedagogie ; lire onze phrases pour verifier qu'elles sont bien celles que j'ai
ecrites coute une passe de lecture, que le lot en contienne deux ou onze.

> **Precision de mon tarif, datee du 2026-09-05.** Le regime du mot pour mot se compte **par passage
> de reverification**, pas par texte. Un passage de textes que j'ai fournis **verbatim** vaut
> **0,25**. Un passage de textes que l'equipe a **reformules** vaut **0,5** - c'est un texte neuf,
> et un texte neuf se juge. Les deux passages peuvent coexister dans un sprint.

**Consequence immediate, et je la paie ici** : `COR-R2` et `EAU-R1` etaient les deux seules reserves
textuelles de s28 pour lesquelles je n'avais pas fourni de texte, ce qui les faisait basculer dans
le passage a 0,5. **Je fournis les deux textes ci-dessous, et les six reserves textuelles retombent
donc a un seul passage a 0,25.**

| Reserve | Cle | Texte que je fournis |
|---|---|---|
| `COR-R2` (`cp-qlm-corps-sens.yaml`, item du gout) | `bonneReponse` | `la langue` |
| `COR-R2` | `indice` | *"C'est elle qui reconnait le sucre et le sale."* |
| `EAU-R1` (`cp-qlm-etats-eau.yaml`, item de la glace qui fond) | `indice` | *"La glace qui fond se met a couler."* |

Les distracteurs de l'item du gout (`le nez`, `les yeux`) restent inchanges : *le nez* est un
distracteur plausible et non absurde, l'odorat participant reellement au gout.

**Comme a s28 : je fournis le fond, le niveau et le ton. L'orthographe, l'accentuation et la
ponctuation de ces textes appartiennent au `relecteur-pedagogique`** (`DEC-0002`, amendement du
2026-08-04 ; `DEC-0056`), y compris parce qu'ils sont destines a l'enfant.

### 1.6 Une phrase de moi que je retire, parce qu'elle contredit ma propre regle du meme jour

J'ai ecrit dans le referentiel d'EMC CP, le 2026-09-03 : *"Un paquet d'EMC ne se relit pas comme un
paquet d'additions, et **je le facturerai en consequence** le jour ou il sera propose."*

**Le meme jour, dans mon arbitrage de s28, section 4.2, j'ai refuse exactement ce supplement** :
*"Une condition inedite ne fait pas monter mon prix : elle fait baisser mon plafond de fermeture."*
Les deux ne peuvent pas tenir ensemble. **C'est la regle de tarif qui gagne**, parce qu'elle est
generale, motivee, et qu'elle vaut contre moi : facturer mes propres clauses est le moyen le plus
simple de faire monter mon prix sans que personne puisse le contester. **La phrase du referentiel
est retiree comme annonce de prix.** Son fond - un paquet d'EMC est plus dangereux qu'un paquet
d'additions - est conserve, et il agit en section 5, sur le plafond.

---

## 2. Le prix reel, recompte par moi - le perimetre annonce vaut 6,0 a 7,0, pas 5,0

### 2.1 Ma borne ne bouge pas, son unite non plus

**Quatre a cinq lots de contenu par sprint sur une mecanique existante** (`ADR-0079`). Inchangee
depuis s22, reconfirmee ici. L'unite reste **le lot de matiere et de niveau, jusqu'a vingt items,
prorata au-dela ; le nombre de fichiers ne compte pas**, et le prix ne descend pas sous vingt items.

**Une precision de l'unite, datee du 2026-09-05, et elle est dans le sens qui me coute.** La
section 3 montre qu'un lot qui ferme deux lignes doit porter **vingt-deux** items et non vingt, pour
une raison qui n'a rien de pedagogique : un garde-fou existant interdit qu'un paquet ait autant
d'items que de manches. **Ces deux items-la ne sont pas du contenu de plus, ils sont la marge d'un
garde-fou : ils ne changent pas le prix du lot.** Un lot vaut son tarif jusqu'a **vingt-deux**
items quand il est compose de deux paquets fermant chacun une ligne.

### 2.2 Le critere reste "suis-je la source ?", et il repond seul a la question posee

Mon critere du 2026-09-03 : **non pas *neuve* contre *connue*, mais *suis-je la source ?*** Ce cout
ne s'eteint pas quand la matiere devient connue - il se paie a l'item, a chaque item neuf.

**La question A m'etait posee comme ouverte. Elle etait deja fermee, par moi, dans les referentiels
que j'ai ecrits le 2026-09-03. Je n'ai eu qu'a les relire.**

| Matiere | Ce que j'ai ecrit le 2026-09-03, cite | Verdict |
|---|---|---|
| **Sciences et technologie CM1** | *"**Je ne suis pas la source en sciences comme je le suis en calcul** : l'exactitude de chaque fait se verifie contre une source, et c'est le cout principal de cette matiere pour moi."* | **1,5** |
| **EMC CM1** | *"l'exactitude institutionnelle de chaque item - un droit, un devoir, une competence de mairie, une regle de vote se verifient contre une source, et **je n'en suis pas la source**."* | **1,5** |
| **EMC CP** | *"`EMC-7` - exactitude des numeros d'urgence et des symboles \| **moi, contre source**"* | **mixte, voir 2.3** |
| **Questionner le monde CP** | *"J'en suis la source - le vivant, le corps, les objets, je les enseigne"* (s28, 4.2) | **1,0** |

**Il n'y a donc pas d'arbitrage a rendre sur trois des quatre matieres : le prix etait deja ecrit.**
Ce qui a ete annonce a 1,0 est a 1,5 pour deux d'entre elles, et l'ecart n'est pas une opinion sur
la difficulte : c'est le fait qu'un fait institutionnel ou scientifique se verifie contre une
source et qu'un fait de CP que j'enseigne ne le demande pas.

**Un fait qui confirme que ce cout est structurel et non une preference.** Le commentaire de l'item
romain de `cm1-histoire-geographie.yaml`, ecrit par un developpeur, porte en toutes lettres : *"je
n'ai pas d'outil de recherche en ligne cette session, nadia-prof doit confirmer"*. **La verification
de source ne se delegue pas en aval de moi ; elle s'arrete a moi.** C'est ce que paie le 1,5.

### 2.3 EMC CP est mixte, et c'est un levier pour l'utilisatrice

Sur les douze lignes `non couvert`, cinq portent des faits que je verifie contre une source -
**8** (symboles de la Republique), **9** (devise), **10** (droits de l'enfant), **13** (roles et
services de la commune), **14** (numeros d'urgence). Les sept autres - emotions, permis et interdit
entre eleves, exclusion, differences, politesse, regles de vie, regle et loi - sont ce que
j'enseigne.

> **Tarif d'EMC CP, date du 2026-09-05.** Un lot restreint aux lignes **1 a 7** vaut **1,0**. Un lot
> qui touche l'une des lignes **8, 9, 10, 13 ou 14** vaut **1,5**, quel que soit le nombre d'items
> concernes : une seule source a verifier suffit a ouvrir la verification.

**Ce n'est pas un decoupage de confort** : c'est mon critere applique ligne par ligne, sur une
matiere ou il ne tombe pas du meme cote partout. Et il donne a `camille-po` un levier reel - 0,5,
soit un tiers de lot - qui se decide en choisissant les lignes du lot, pas en negociant mon tarif.

### 2.4 Ce que vaut le perimetre annonce

| Composition de cinq lots sur les quatre matieres | Prix |
|---|---|
| La moins chere possible : 2 lots QLM CP + 1 EMC CP restreint + 1 sciences + 1 EMC CM1 | **6,0** |
| Une par matiere plus un second lot de sciences ou d'EMC | **6,5** |
| La plus chere : EMC CP non restreint et deux lots a 1,5 en double | **7,0** |

> **Le perimetre annonce depasse ma borne de 1,0 a 2,0, c'est-a-dire d'un a deux lots entiers.
> Je le decline par ecrit.** Ce n'est pas un refus de travailler davantage : c'est que la borne
> existe pour que mon audit reste un audit, et qu'un sprint a 7,0 me ferait rendre des verdicts que
> je n'aurais pas verifies.

**Ce qui tient exactement dans 5,0, et c'est quatre lots :**

| Lot | Tarif applique | Prix |
|---|---|---|
| Questionner le monde CP | je suis la source | **1,0** |
| EMC CP **restreint aux lignes 1 a 7** | je suis la source de ces sept lignes | **1,0** |
| Sciences et technologie CM1 | je ne suis pas la source | **1,5** |
| EMC CM1 | je ne suis pas la source | **1,5** |
| **Total** | **jusqu'a 88 items** | **5,0, zero marge** |

---

## 3. L'arithmetique de fermeture, verifiee - et les deux contraintes qui la gouvernent

Le raisonnement transmis dit qu'un lot de vingt items ferme environ deux lignes quelle que soit la
matiere. **Il est juste, et je le confirme avec ses deux appuis, dont l'un impose un correctif de
deux items.**

### 3.1 Un item ne compte que sur une ligne - je confirme la clause 5 d'`ADR-0081`

`theo-archi` m'a route ce point nommement : *"Si `FER-2` doit compter un item sur plusieurs lignes,
la clause 4 change et le compte avec."* **Je confirme la clause 5 sans changement.** Son motif est
le mien : *"si un item pouvait compter sur trois lignes, vingt items fermeraient six lignes a dix
items chacune, ce qui est exactement l'illusion que `FER-2` existe pour tuer."* **Un compte
calculable et faux est pire qu'un compte incalculable.** Un item est compte sur la ligne qu'il
**evalue**, celle ou sa bonne reponse se trouve.

**Consequence directe** : `FER-2` a dix items, un item par ligne, donc **un lot de vingt items ferme
deux lignes au plus**. Cinq lots plafonnent a dix lignes, quatre lots a huit.

### 3.2 Vingt items ne suffisent pas : il en faut vingt-deux, et c'est un garde-fou qui le dit

`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` (`DEC-0064`) exige **strictement plus**
d'items que de manches - `isGreaterThan(jeu.nombreDeManches())`. Au defaut de dix manches, un paquet
qui sert une ligne a dix items ne passe pas la porte technique : **il en faut onze**.

**Deux formes possibles, et je tranche entre elles :**

- **Un paquet unique de vingt items declarant deux lignes**, dix items chacune, dix manches. Le
  garde-fou passe (20 > 10) et `FER-2` est tenue sur les deux lignes.
- **Deux paquets de onze items**, un par ligne, dix manches chacun. Vingt-deux items pour le lot.

> **Je prescris la seconde forme, et voici pourquoi.** Un paquet unique melangerait deux
> competences dans une meme partie : l'enfant tirerait dix manches au hasard entre *"les mois de
> l'annee"* et *"ce qui caracterise un etre vivant"* sans savoir a quel jeu il joue. J'ai loue a
> s27 le decoupage de `lea-dev` - un paquet, une ligne - et je le maintiens. **Le lot de s29 est
> donc de deux paquets de onze items, soit vingt-deux, au prix d'un lot de vingt.**

**Je note l'exception qui existe deja et qui reste legitime** : `cm1-histoire-geographie.yaml`
declare onze lignes dans un seul paquet, parce que la matiere est une et que le jeu porte un titre
unique. Ce qui vaut pour une matiere ne vaut pas pour deux competences sans rapport.

### 3.3 Les comptes d'items par ligne, derives et non reconstitues a la main

**C'est la premiere fois que je peux ecrire ce tableau sans l'avoir fabrique moi-meme.**
*Methode : `grep -oE 'ligneDeReferentiel: "[A-Z0-9-]+"' | sort | uniq -c` sur les deux seuls paquets
servis qui declarent plusieurs lignes ; les autres paquets servent une ligne unique et leur compte
est leur nombre d'items.*

| Ligne `en cours` | Items servis | Manque a `FER-2` |
|---|---|---|
| `HG-CM1-06` (roi / realisation) | 4 | **+6** |
| `HG-CM1-07` (Revolution) | 3 | **+7** |
| `HG-CM1-14` (comprendre un document) | 3 | **+7** |
| `LVE-CM1-07` (decouverte culturelle) | 5 | **+5** |
| `HG-CM1-01` | 2 | +8 |
| `HG-CM1-02`, `-03`, `-04`, `-08`, `-12` | 1 chacune | +9 chacune |

**Verification de ce que j'avais ecrit a la main a s28** : j'y attribuais 4 items a la ligne 6, 3 a
la ligne 7, 3 a la ligne 14, 2 a la ligne 1 et 1 aux cinq autres. **Le compte derive confirme mon
attribution ligne par ligne, sans un ecart.** C'est la seule de mes verifications d'aujourd'hui qui
confirme au lieu de corriger, et je l'ecris pour la meme raison que les autres.

**Et ce tableau porte le fait le plus important de tout ce document**, developpe en section 6 :
**les lignes `HG-CM1-06`, `-07` et `-14` sont a 4, 3 et 3 items, donc un lot de vingt items en
ferme trois, la ou un lot sur une matiere vierge en ferme deux.**

---

## 4. Ce que chaque lot doit apporter pour que je ferme, matiere par matiere

`FER-1`, `FER-2` et `FER-3` sont **reconduites sans un mot de changement**. Une precision, parce
qu'elle a deja failli couter un lot :

> **Precision de `FER-1`, datee du 2026-09-05.** `FER-1` porte sur ce que **le libelle enumere**.
> Quand la parenthese enumere, la parenthese fait contrat : les trois regimes alimentaires, les
> quatre symboles de la Republique, les huit termes de position sont chacun a servir en position de
> bonne reponse. **Quand le libelle n'enumere pas, `FER-1` porte sur ses moities, jamais sur une
> liste implicite** : *"nommer et ordonner les mois de l'annee et les saisons"* demande **les mois**
> et **les saisons** en bonne reponse, **pas les douze mois un par un**.

### 4.1 Questionner le monde CP - les deux lignes les plus rentables, et celles qui ne fermeront pas

Dix des douze lignes restantes sont servables au choix textuel ; `QLM-CP-11` et `QLM-CP-12` (plan,
carte, globe) attendent le palier 2 et sortent du perimetre.

| Ligne | Rentabilite | Ce qu'il faut pour que je ferme |
|---|---|---|
| **`QLM-CP-02`** - *ce qui caracterise un etre vivant : il nait, se nourrit, grandit, a des petits, meurt* | **la plus rentable de la matiere** | `FER-1` : les cinq caracteres, chacun en bonne reponse. `FER-3` : deux taches naturelles - reconnaitre le caractere decrit, et dire si un etre donne le possede. **Elle est la suite exacte de `QLM-CP-01` qui vient de fermer** : le produit dit aujourd'hui a l'enfant qu'un chat est vivant, sans jamais lui dire pourquoi |
| **`QLM-CP-07`** - *fonction d'un objet technique et nommer ses parties* | **tres rentable** | `FER-1` : les deux moities, fonction et parties. `FER-3` : deux taches par construction. Objets du quotidien, exactitude sure, aucune source a verifier |
| **`QLM-CP-13`** - *nommer et ordonner les mois de l'annee et les saisons* | rentable | `FER-1` sur les deux moities, **pas sur les douze mois**. `FER-3` : nommer, et ordonner (*"quel mois vient apres... ?"*) |
| **`QLM-CP-08`** - *objets numeriques et regles d'usage* | moyenne | Les regles d'usage au CP sont peu nombreuses ; risque de moraliser plutot que d'enseigner |
| **`QLM-CP-04`** - *regles d'hygiene de vie* | moyenne, **sous `BIA-4`** | Terrain sur si aucun item n'interroge l'enfant sur sa propre vie |
| **`QLM-CP-06`** - *matiere d'un objet et trier des materiaux* | **demi-ligne** | *Trier une collection* est une manipulation refusee au palier 1 par `V3`. **Mention exigee d'avance si elle bouge** : *"identification de la matiere seule ; le tri d'une collection reste non couvert"* |
| **`QLM-CP-14`** - *frise chronologique* | **demi-ligne** | Mention deja exigee et deja ecrite dans la matrice |
| **`QLM-CP-10`** - *vocabulaire de position* | **ne fermera pas en s29** | `FER-1` exige **huit** termes en bonne reponse ; et *gauche / droite* sans image est la difficulte classique du CP - un enfant qui hesite sur sa propre gauche ne repond pas a une phrase. La ligne demande le palier 1 illustre, donc `REC-1` et **+0,5 sur le lot** |
| **`QLM-CP-15`** - *autrefois et aujourd'hui* | ne fermera pas en s29 | `BIA-3` : la chronologie n'est pas un progres. Dix items sans qu'un seul suggere que l'ancien etait moins bien, c'est faisable, mais pas sur un premier lot |
| **`QLM-CP-16`** - *ici et ailleurs* | **ne fermera pas en s29** | **La ligne la plus a risque de biais de tout le referentiel** : `BIA-1` et `BIA-2` mordent toutes les deux, et une mecanique a trois propositions qui decrit un ailleurs fabrique un stereotype en une phrase. Je l'ouvrirai ; je ne la fermerai pas sur un premier lot |

**Ce que je demande au lot** : `QLM-CP-02` et `QLM-CP-07`, onze items chacune, deux paquets.

### 4.2 Sciences et technologie CM1 - les cinq lignes rentables, et les quatre qui ne fermeront pas

**A faire avant d'ecrire un item** : les deux corrections de libelle de la section 1.2.

| Ligne | Rentabilite | Ce qu'il faut pour que je ferme |
|---|---|---|
| **1** - *etats et changements d'etat (solide, liquide, gaz ; fusion, solidification, vaporisation, condensation)* | **la plus rentable du lot** | `FER-1` : **sept elements** en bonne reponse, trois etats et quatre changements. `FER-3` : classer un etat, nommer un changement. Exactitude sure, vocabulaire du programme. **Et la ligne ou `SCI-1` est la plus facile a ecrire** : la conception fausse *"la vapeur d'eau se voit"* est le distracteur naturel |
| **4** - *sources et formes d'energie, un exemple de conversion* | **tres rentable** | `FER-1` : sources, formes, une conversion. `FER-3` : identifier une source, identifier une conversion. **J'ai note moi-meme que les distracteurs y sont naturels et plausibles, ce qui est rare** |
| **5** - *signal et information (feu tricolore, panneau, alarme, ecran)* | rentable | `FER-1` : les quatre objets. Vocabulaire du quotidien, exactitude sure |
| **17** - *milieu proche et chaine alimentaire* | rentable | *"Qui mange qui ?"* a deux maillons. `FER-3` : identifier le mangeur, identifier le mange. **Construire** une chaine entiere reste refuse par `V3` |
| **2** - *melange homogene et heterogene, separer les constituants* | rentable | `FER-1` : trois elements. Les deux mots se definissent dans l'item |
| **7** - *besoins et regimes alimentaires* | rentable **apres correction du libelle** | `FER-1` : herbivore, carnivore, omnivore, et les besoins. **Attention aux distracteurs absurdes** : classer un lion en herbivore est une devinette, pas un item |
| **15** - *Terre, systeme solaire, jour, nuit, saisons* | rentable, **conditionnee a `SCI-1`** | La conception fausse *"la Terre est plus pres du Soleil en ete"* est le meilleur distracteur de la ligne **et une reponse fausse** : sans une `explication` qui la demonte, l'enfant repart avec elle |
| **9** - *grandes fonctions du corps* | rentable **apres correction du libelle** | En l'etat, la ligne a une reponse indefendable |
| **3** - *mouvement, trajectoire, vitesse* | **ne fermera pas en s29** | **Le produit ne montre aucun mouvement.** `FER-1` demande trajectoire, vitesse et point de repere en bonne reponse ; la vitesse sans chiffre est tres mince, et avec chiffres elle tombe sous `SCI-3` et double-compte les mathematiques |
| **13** - *evolution d'un objet technique* | ne fermera pas | `BIA-3`, et chaque fait de datation demande une source |
| **16** - *phenomenes geologiques et risques* | ne fermera pas | `BIA-2` : dix items sans situer tous les risques ailleurs demande une geographie que je devrai verifier fait par fait |
| **18** - *impact humain et preservation* | **ne fermera pas en s29** | `SCI-2`, et je l'ai nommee la ligne la plus dangereuse du lot. Un lot de fermeture ne commence pas par elle |
| **20** - *extraire d'un document scientifique* | ne fermera pas | Dix items exigeraient dix `texteSupport`, et la mention *"texte et tableau seuls"* est due d'avance |

**Ce que je demande au lot** : lignes 1 et 4, onze items chacune, deux paquets, avec `explication`
sur **chaque** item.

### 4.3 EMC CP - deux lignes rentables dans le perimetre a 1,0

Le lot restreint aux lignes 1 a 7 vaut 1,0 ; il n'a acces ni aux symboles, ni aux droits, ni aux
numeros d'urgence.

| Ligne | Rentabilite | Ce qu'il faut pour que je ferme |
|---|---|---|
| **6** - *regles de vie de la classe et de l'ecole, et a quoi elles servent* | **la plus rentable de la matiere, et je l'avais deja ecrit** | `FER-1` : les deux moities. `FER-3` : nommer la regle, dire ce qu'elle protege. **Un paquet qui n'enoncerait que des regles ne sert pas la ligne : il fait reciter un reglement** |
| **2** - *permis et interdit entre eleves (moquerie, insulte, violence)* | rentable | `FER-1` : les trois termes. `FER-3` : reconnaitre l'interdit, dire ce que la regle protege. **Ce n'est pas une opinion**, la reponse est unique et exacte |
| **7** - *regle, souhait, loi* | moyenne | Le mot *loi* est au programme et accessible **sur des exemples concrets**. **Il ne doit jamais etre defini dans l'enonce** : une definition juridique en CP est illisible avant d'etre fausse |
| **3** - *reconnaitre une situation d'exclusion* | **redevenue possible par l'amendement d'`EMC-3`** | Deux taches desormais nommees : reconnaitre la situation, identifier l'adulte selon le lieu. Ligne delicate, je la traiterai en dernier |
| **1** - *emotions d'autrui* | **ne fermera pas en s29** | `EMC-2` n'accepte que les situations a emotion unique et evidente ; dix items de ce type existent, mais la **seconde tache** de `FER-3` n'existe pas naturellement sur cette ligne au CP |
| **4** - *differences sans hierarchiser* | **ne fermera pas en s29** | `EMC-4` interdit la forme de l'enumeration, ce qui ne laisse que la forme de l'interdit, donc **une seule tache**. `FER-3` ne peut pas etre tenue. **Je l'ecris d'avance pour que personne n'y depense vingt items** |
| **5** - *formules de politesse* | demi-ligne | Mention deja exigee : *"connaissance des formules seule"* |

**Ce que je demande au lot** : lignes 6 et 2, onze items chacune.

### 4.4 EMC CM1 - la meilleure ligne du perimetre, et la plus dangereuse

| Ligne | Rentabilite | Ce qu'il faut pour que je ferme |
|---|---|---|
| **11** - *distinguer un fait d'une opinion, verifier l'origine d'une information* | **la ligne la plus rentable de tout le perimetre de s29** | La forme est exactement celle de `bandit-manchot-comprehension-cm1`, deux fois validee. `FER-3` : classer fait ou opinion, et dire comment on verifie. **Sous `EMC-10`** (section 5) |
| **7** - *distinguer un droit d'un devoir* | **tres rentable** | J'ai note moi-meme que les distracteurs y sont naturels et plausibles. `FER-3` : classer, et associer un droit a ce qu'il protege. `BIA-4` s'applique |
| **8** - *regle et loi, ce que la loi autorise, interdit et protege* | rentable | `FER-1` : les trois verbes de la parenthese. Jamais de definition juridique dans l'enonce |
| **5** - *symboles de la Republique et ce qu'ils representent* | rentable | `FER-1` : les cinq symboles. **Sous `ART-1`** : jamais *"de quelle couleur est... ?"*, qui exclut un enfant daltonien et ne teste pas le symbole. Chaque fait porte sa source |
| **4** - *regles de la vie collective et leur raison d'etre* | rentable | Meme exigence qu'a la ligne 6 du CP : la moitie *"a quoi elles servent"* est ce qui distingue la ligne d'un reglement recite |
| **10** - *sens du vote et de la representation* | moyenne | Mention exigee d'avance : *"connaissance du vote et de la representation seule ; participer a une decision collective reste non couvrable"* |
| **9** - *institutions proches* | moyenne | `EMC-6` : aucun item ne suppose la commune de l'enfant |
| **2** - *atteinte a autrui* | ne fermera pas en s29 | `EMC-3` amendee **et** `EMC-9`. Faisable, mais je ne la fermerai pas sur un premier lot |
| **3** - *ce qu'interdit le respect des differences* | **ne fermera pas en s29** | Meme motif qu'a la ligne 4 du CP : `EMC-4` ne laisse qu'une tache |
| **6** - *sens des principes de la Republique, dont la laicite* | **ne fermera pas en s29, et je le dis d'avance** | `EMC-4`, `EMC-5` et `BIA-5` mordent toutes les trois. **Un item faux sur cette ligne fait plus de degats qu'une absence.** Je l'ouvrirai volontiers ; je ne la fermerai pas sur un premier lot d'une matiere dont je ne suis pas la source |

**Ce que je demande au lot** : lignes 11 et 7, onze items chacune.

---

## 5. Les conditions opposables aux lots de s29

### 5.1 Reconduites sans changement

`FER-1`, `FER-2`, `FER-3` (avec la precision de `FER-1` en section 4) ; `CON-1` a `CON-4` ;
`HIS-2` et `HIS-3` sur les trois matieres neuves - **`HIS-3` s'applique a l'identique : chaque fait
porte sa source dans le commentaire du paquet**, et c'est la seule des proprietes de source qui soit
mordable, sur le procede et jamais sur la verite ; `V1`, `V2`, `V3` ; `ART-1` sur les lignes de
symboles ; `SCI-2` ; `EMC-1`, `EMC-2`, `EMC-4`, `EMC-5`, `EMC-6`, `EMC-7`, `EMC-8`, `EMC-9`.

### 5.2 Amendees ce jour

- **`SCI-1`** - section 1.1. Impossible telle qu'ecrite, remplacee.
- **`EMC-3`** - section 1.3. Incompatible avec `FER-3`, remplacee.

### 5.3 Precisees, sans durcissement

- **`SCI-3`** (au plus un item sur six demande une valeur numerique a restituer) **se calcule sur le
  lot et, en plus, sur tout paquet de dix items ou davantage pris seul** - meme extension que celle
  faite a `HIS-1` le 2026-09-03, meme motif : le paquet est ce que l'enfant joue. **Sur un paquet de
  onze items, cela fait un item chiffre, pas deux.**
- **`EMC-8`** - la forme refusee est **l'interrogation de l'enfant sur lui-meme**, pas la presence
  d'une situation. *"Que fait Lea si un camarade se moque d'un autre ?"* est autorise ; *"que
  fais-tu si..."* est refuse. **Sans cette precision, les developpeurs eviteront toute situation et
  les lignes deviendront de la recitation**, ce qui est exactement ce qu'`EMC-8` cherche a empecher.
- **`REC-1`** ne concerne aucun des quatre lots s'ils restent textuels. Elle s'appliquerait, a
  **+0,5**, a un lot illustre - notamment sur `QLM-CP-10`.

### 5.4 Une condition nouvelle, une seule, et je dis pourquoi une seule

**Douze de mes conditions - `SCI-1` a `SCI-3`, `EMC-1` a `EMC-9` - vont rencontrer du contenu reel
pour la premiere fois.** En ajouter beaucoup d'autres ferait baisser mon plafond sans rien proteger
de neuf. J'en ajoute **une**, parce que le risque qu'elle couvre est previsible, serieux, et
presque mordable.

> **`EMC-10` - nouvelle, datee du 2026-09-05.** Aucun item d'EMC ne nomme une **personne reelle
> vivante**, un parti, un syndicat ni une organisation engagee dans un debat en cours. Les exemples
> sont pris dans la vie de l'ecole, la meteo, un match, une rumeur de cour de recreation.
> **Motif** : les lignes *fait ou opinion*, *le vote* et *les principes de la Republique* attirent
> naturellement l'exemple d'actualite, et une mecanique de choix qui **couronne** une reponse sur
> une personne vivante fait prendre parti au produit. Ce n'est pas une precaution politique : c'est
> `EMC-5` - aucun item n'a deux reponses defendables - appliquee la ou elle est le plus facile a
> violer sans s'en apercevoir. **Elle est presque mordable** : un nom propre se cherche.

### 5.5 Reponse a la question posee sur `BIA-1` a `BIA-5` en EMC

**La reponse n'est pas "toutes s'appliquent". Une condition qui n'ajoute rien est du bruit, et le
bruit fait rater les autres.**

| Condition | En EMC | Verdict |
|---|---|---|
| **`BIA-1`** - aucun ailleurs compare a un ici en position de norme | **s'applique et ajoute quelque chose** : la ligne 3 de CM1 (origine, convictions) et la ligne 6 (laicite) sont exactement ou la forme *"chez nous... chez eux..."* revient | **opposable** |
| **`BIA-5`** - aucun jugement de valeur sur un personnage | **s'applique et ajoute quelque chose** : je l'avais deja opposee a la ligne 6 de CM1. **Elle s'etend en EMC aux personnes decrites dans les situations** - un enfant decrit dans un item n'est ni gentil ni mechant, il fait ou ne fait pas ce que la regle prevoit | **opposable, etendue ce jour** |
| **`BIA-2`** - jamais un seul trait | s'applique, mais **`EMC-4` est strictement plus forte** : elle interdit d'attribuer un trait a une categorie de personnes **meme en distracteur** | **redondante en EMC. Opposer `EMC-4`** |
| **`BIA-4`** - aucun item n'interroge l'enfant sur sa propre vie | s'applique, mais **`EMC-1` en est l'extension et ajoute *croit*** | **redondante en EMC. Opposer `EMC-1`** |
| **`BIA-3`** - la chronologie n'est pas un progres | **sans objet en EMC.** Elle vise `QLM-CP-15` et la ligne 13 des sciences | **non opposable en EMC** |

**Et la reponse a la remarque qui accompagnait la question - l'EMC est la matiere ou un jugement de
valeur passe le plus facilement pour une connaissance - est oui, et c'est exactement pourquoi
`EMC-5` et `EMC-8` sont les deux barres qui comptent.** `EMC-5` refuse l'item dont la reponse se
discute ; `EMC-8` refuse l'item dont la reponse se devine par la politesse. Un item qui passe les
deux enseigne une regle ou un fait ; un item qui en rate une enseigne une obeissance.

---

## 6. Le plafond honnete de s29, avec sa composition - huit lignes, et neuf par un chemin de cote

**C'est la lecon de s26 appliquee pour la quatrieme fois.** Le chiffre de dix lignes est une
estimation d'orchestrateur ; le mien est le suivant, avec la composition qui le produit.

**Etat de depart, verifie ligne par ligne dans la matrice** : 43 `couvert-valide` sur 138
atteignables, **31,2 %**.

### 6.1 Le plafond du perimetre annonce, ramene a ce qui tient dans 5,0

| Lot | Lignes visees | Prix | Fermetures au plafond |
|---|---|---|---|
| Questionner le monde CP | `QLM-CP-02`, `QLM-CP-07` | 1,0 | **+2** |
| EMC CP restreint aux lignes 1 a 7 | lignes 6 et 2 | 1,0 | **+2** |
| Sciences et technologie CM1 | lignes 1 et 4 | 1,5 | **+2** |
| EMC CM1 | lignes 11 et 7 | 1,5 | **+2** |
| **Plafond** | | **5,0** | **+8, soit 51 / 138 = 37,0 %** |

### 6.2 Neuf lignes pour le meme prix, par un lot que le perimetre annonce a laisse dehors

**Le fait mesure en 3.3 change la reponse a la question de la couverture maximale.** Les lignes
`HG-CM1-06`, `-07` et `-14` portent deja 4, 3 et 3 items : un lot de vingt items sur ces trois
lignes en apporte 6, 7 et 7, et **ferme trois lignes**. C'est la composition A que j'avais ecrite a
s28 et que le sprint n'a pas retenue.

| Lot | Prix | Lignes fermees | **Prix par ligne** |
|---|---|---|---|
| **Histoire-geographie CM1, composition A** | 1,5 | **3** | **0,50** |
| Questionner le monde CP | 1,0 | 2 | **0,50** |
| EMC CP restreint aux lignes 1 a 7 | 1,0 | 2 | **0,50** |
| Sciences CM1, EMC CM1, EMC CP non restreint | 1,5 | 2 | 0,75 |

> **Aucun lot sur une matiere vierge ne peut faire mieux que deux lignes, parce qu'aucune ligne
> vierge ne part avec des items deja au compteur. Le lot d'histoire-geographie est le seul du depot
> a en fermer trois pour 1,5.** Il ne s'agit pas d'une preference pour l'histoire : c'est une
> propriete arithmetique du compte derive, verifiable par n'importe qui depuis `ADR-0081`.

**Composition qui atteint neuf lignes a 5,0**, quatre lots : QLM CP (1,0) + EMC CP restreint (1,0) +
histoire-geographie composition A (1,5) + sciences **ou** EMC CM1 (1,5). **Plafond +9, soit
52 / 138 = 37,7 %.** Elle coute une des deux matieres neuves de CM1.

**Je ne choisis pas entre 6.1 et 6.2** : c'est de la priorisation. **Je dis que le choix existe,
qu'il est chiffre, et que l'ecart est d'une ligne pour le meme prix.**

### 6.3 Mon attente raisonnable, et ou le plafond cassera d'abord

**Six a sept lignes, pas huit, et sept a huit dans la composition de 6.2.** Soit **49 a 51 sur 138,
c'est-a-dire 35,5 % a 37,0 %.** La cible annoncee de dix lignes et de 38 % n'est pas atteignable a
ma borne : elle demanderait cinq lots, et cinq lots sur ces matieres coutent 6,0 a 7,0.

**Ou cela cassera d'abord, dit avant et pas apres :**

1. **Le lot d'EMC, quel que soit le niveau.** Neuf conditions inedites, dont `EMC-5` et `EMC-8` qui
   refusent deux familles entieres d'items, et `FER-3` qui exige une seconde tache la ou une ligne
   de connaissance n'en a qu'une naturellement. **C'est la premiere fermeture que je perdrai.**
2. **Le lot de sciences, si `SCI-1` n'est pas amendee avant que les items ne soient ecrits.**
   Vingt-deux `explication` sur un champ que le depot a rempli six fois en vingt-huit sprints.
3. **Une erreur de fait.** A s28, le seul refus est venu de la matiere dont j'etais la source, sur
   trois items ou *un caillou* etait declare *un objet*. Le risque n'est pas la ou on le range :
   il est la ou personne ne regarde.

**Deux avertissements de denominateur, pour que personne ne decouvre un chiffre en review :**

- **Le denominateur ne bouge pas a s29** : les dix referentiels manquants sont entres le
  2026-09-03, 138 est stable, et toute hausse du taux sera un gain reel de couverture. C'est le
  premier sprint depuis s26 dont on peut dire cela d'avance.
- **Fermer une ligne n'ouvre rien.** Un sprint entierement consacre a la fermeture fait monter le
  numerateur et n'ajoute **aucune matiere neuve** au produit. Le perimetre annonce, lui, fait
  l'inverse : il ouvre quatre matieres et n'en ferme que huit lignes sur les cinquante-quatre
  qu'elles portent. **Les deux objectifs se disputent les memes items ; c'est un arbitrage
  legitime, a prendre en le sachant.**

---

## 7. Ce que ce perimetre laisse dehors, et ce que l'attente coute

### 7.1 `POS-R1` - une condition de moi violee sur une ligne que j'ai fermee

Section 1.4 : **onze items sur onze** portent un distracteur eliminable sans diviser. **C'est
`POS-2` - *l'item ne doit pas se repondre sans poser l'operation* - qui n'est pas tenue sur la ligne
que j'ai declaree fermee sous `POS-2` le 2026-09-04.**

**Prix : 0,5**, au regime du rejeu borne a des proprietes nommees - c'est un recalcul de onze
divisions et un jugement sur onze distracteurs, pas une lecture de phrases.

**Ce que l'attente coute** : la ligne est **verte**. Une ligne verte n'est plus regardee - c'est
l'argument que j'ai fait a s28 pour la dette a voix et il etait juste. Pendant ce temps, un jeu qui
s'appelle *division posee* se gagne sans poser une division.

### 7.2 Les six reserves textuelles - 0,25 pour les six ensemble

`VIV-R2`, `HG-R4`, `HG-R5`, `ANG-R1` avaient deja leur texte ; `COR-R2` et `EAU-R1` ont le leur
depuis la section 1.5. **Les six forment un passage de reverification mot pour mot : 0,25.**

Une seule de ces six merite d'etre nommee a part : **`COR-R2` est une erreur de fait**. Le produit
dit aujourd'hui a un enfant de CP que l'on goute avec la bouche ; l'organe du gout est la langue.
Elle est ouverte depuis s27 et j'ai eu raison de ne pas la rendre bloquante ; elle a maintenant deux
sprints.

### 7.3 La dette des paquets a voix - quatre sprints si elle n'est pas payee

Six jeux, cinq indices, textes fournis mot pour mot le 2026-09-03. **Prix : 0,25**, un passage.

**Trouvee a s26, elargie a s27, chiffree a s28, non payee.** Si s29 ne la paie pas, elle aura
survecu a quatre sprints. Le cas grave n'a pas bouge : **`cp-son-dans-mot` demande a l'enfant, par
ecrit, de faire redire le mot *"lentement"* a l'adulte, quand `VOI-3` exige *"a vitesse normale"* -
sur le paquet ou la competence est d'entendre un son dans un mot, c'est-a-dire la ou un mot ralenti
donne la reponse.** `VOI-3` n'a aucun garde-fou et n'en aura jamais : je suis la seule garde, et je
ne suis pas dans la piece quand l'enfant joue.

### 7.4 Les neuf lignes d'histoire-geographie `en cours` - ce que coute leur attente

Elles ne bougent pas dans le perimetre annonce. **Ce que cela coute est chiffre en 6.2 : une ligne
de couverture pour le meme prix**, plus un fait que je dois dire clairement.

**Une ligne `en cours` n'est pas une ligne en attente : c'est une ligne servie par un ou deux
items.** Cinq d'entre elles - `HG-CM1-02`, `-03`, `-04`, `-08`, `-12` - portent **exactement un
item**. Le produit affiche donc, sur cinq competences du programme, un unique exercice, et la
matrice dit *en cours*. Ce n'est pas mensonger, mais **c'est le mot le plus optimiste qui soit
exact**. Personne ne doit lire *neuf lignes en cours* comme *neuf lignes presque faites* : cinq
d'entre elles demandent **+9 items** chacune, soit 45 items, plus de deux lots a elles seules.

### 7.5 Le palier 2 - bloque sur un arbitrage prealable de moi, qui n'est pas ecrit

`ADR-0080` et `DEC-0074` disent la meme chose : *"un paquet de designation exige un arbitrage
prealable de `nadia-prof` [...] et cet arbitrage n'est pas ecrit"*. **Six lignes en dependent** -
mathematiques CP (quadrillage), questionner le monde CP (plan, carte ou globe, frise),
histoire-geographie CM1 (carte et planisphere, lieu de vie).

**Ce que cela coute contre ma borne : rien.** Un arbitrage prealable n'est pas un lot de contenu et
n'a jamais ete compte dans les 5,0 - ceux de s26, s27 et s28 se sont ajoutes par-dessus, et
`DEC-0074` le montre. **Ecrire celui-la a s29 ne retire aucun lot au sprint et debloque six lignes
pour s30.** C'est le meilleur rapport du document, et il n'est pas dans le perimetre annonce.

**Je ne priorise pas.** Je dis le prix, je dis que la depense est nulle contre ma borne, et je dis
ce que l'attente bloque.

---

## 8. La marge de rejeu - mon chiffrage et ma recommandation

### 8.1 Ce que la marge doit couvrir, chiffre

Un refus tardif appelle l'une de deux reprises, et elles n'ont pas le meme prix :

| Reprise | Prix |
|---|---|
| Correction reprise **mot pour mot** d'un texte que j'ai fourni, puis reverification | **0,25** |
| **Rejeu borne** a des proprietes nommees - le lot est reecrit par l'equipe et je le rejuge | **0,5** |

**Une marge de 0,25 couvre une correction verbatim. Une marge de 0,5 couvre un vrai rejeu. Une
marge de 0,75 couvre les deux.** C'est ce que s28 a rencontre : un refus, une correction verbatim,
une reverification - 0,25 - et le sprint est parti a 5,25 parce qu'il n'y avait rien.

### 8.2 Les compositions, chiffrees

| # | Composition | Prix | Marge | Plafond |
|---|---|---|---|---|
| 1 | QLM CP + EMC CP restreint + sciences + EMC CM1 | **5,0** | **0** | 8 lignes, 37,0 % |
| 2 | QLM CP + EMC CP restreint + HG composition A + sciences | **5,0** | **0** | 9 lignes, 37,7 % |
| 3 | Composition 2 + les six reserves textuelles | **5,25** | **-0,25** | **je decline par ecrit** |
| 4 | **QLM CP + EMC CP restreint + HG composition A + six reserves (0,25) + `POS-R1` (0,5)** | **4,25** | **0,75** | **7 lignes, 36,2 %** |
| 5 | Composition 4 + la dette a voix (0,25) | **4,50** | **0,50** | 7 lignes, 36,2 % |
| 6 | Composition 1 moins le lot d'EMC CM1, plus les six reserves | **3,75** | **1,25** | 6 lignes, 35,5 % |

### 8.3 Ce que je recommande

> **Je recommande la composition 5 : trois lots, les six reserves textuelles, `POS-R1`, la dette a
> voix. 4,50 sur 5, marge de 0,50, plafond de sept lignes fermees, soit 50 / 138 = 36,2 %.**

**Et je dis ce qu'elle coute, sans l'adoucir : deux lignes de couverture** - sept au lieu de neuf,
36,2 % au lieu de 37,7 % - **et une matiere neuve de moins ouverte a s29.**

**Trois motifs, dans cet ordre :**

1. **`POS-R1` n'est pas une reserve de confort.** C'est une de mes conditions violee sur une ligne
   que j'ai fermee sous cette condition. **La payer est plus urgent qu'ouvrir une neuvieme ligne**,
   et si je ne le disais pas, personne ne le dirait a ma place.
2. **La marge de 0,50 est dimensionnee, pas arrondie.** Elle couvre exactement un rejeu borne, ou
   deux corrections verbatim. C'est ce qui a manque a s28, et le chiffre vient de ce qu'a coute s28.
3. **Elle paie trois dettes en une fois** - six reserves, `POS-R1`, la dette a voix - dont deux ont
   deja deux et trois sprints. Un depot ou les dettes s'accumulent pendant que les lignes vertes
   augmentent finit par avoir un beau taux et des jeux faux.

**Ce que je ne recommande pas, et je le dis parce que la retro de s28 avait raison** : je ne
recommande **pas** d'abaisser ma borne pour se menager de la marge. *"Une borne avec de la marge
cesse d'etre une borne et le sprint suivant gonflerait pour la remplir."* **La borne reste 5,0. Ce
que je propose est de n'en planifier que 4,50 et de nommer les 0,50 restants d'avance**, ce qui est
une autre chose : une reserve annoncee n'est pas une borne elargie.

### 8.4 La mesure qui ne coute rien, et qui est la vraie reponse au probleme de s28

`sofia-sm` a raison : *"la reponse par defaut a un refus tardif n'a jamais ete posee comme un choix
assume"*. **Mais le probleme de s28 n'etait pas d'abord la marge, il etait le calendrier, et il
etait de moi.** J'ai rendu **sept verdicts dans un seul commit** (`537a0e1`), a la cloture. Le
refus est arrive quand il ne restait plus de sprint.

> **Ce que je m'engage a faire a s29, et cela ne coute rien a personne** : j'audite **chaque lot le
> jour ou il est livre**, pas tous a la cloture. Et le planning ordonne les lots de facon que
> **celui dont les conditions n'ont jamais rencontre de contenu reel passe en premier** - a s29,
> c'est le lot d'EMC.

Un refus rendu au tiers du sprint se corrige dans le sprint sans arbitrage exceptionnel. Un refus
rendu la veille de la cloture n'a que deux issues, et l'utilisatrice a du en arbitrer une dans
l'urgence a s28. **La marge protege du second cas ; le calendrier fait qu'il ne se produit pas.**

---

## 9. Ce qui rendait mes prescriptions fragiles - la reponse, et elle est unique

Quatre defauts de moi a s28, cinq de plus aujourd'hui - `SCI-1` impossible, `EMC-3` incompatible
avec `FER-3`, deux libelles de sciences faux, une phrase de tarif qui contredit mon tarif. **Neuf
defauts en trois jours sur des textes que j'ecris. Il faut que je dise ce qu'ils ont en commun,
sinon la famille reviendra une dixieme fois.**

**Ils ont tous la meme cause : j'ecris dans le registre du commentaire, et on me lit dans le
registre de la specification.**

| Ce que j'ai ecrit | Le registre ou je l'ecrivais | Le registre ou il a ete lu |
|---|---|---|
| *"(vegetarien, carnivore, omnivore)"* | une illustration du libelle | **`FER-1` en fait la liste des bonnes reponses obligatoires** |
| *"le retour dit pourquoi elle est fausse"* | une intention pedagogique | **un critere d'acceptation sur un champ du modele** |
| *"la partie epuisant exactement le vivier"* (s28) | une remarque de forme | **une contrainte que `DEC-0064` interdit** |
| *"je le facturerai en consequence"* | un avertissement | **une annonce de prix** |
| *"la bonne reponse est toujours en parler a un adulte"* | une interdiction | **une reponse unique imposee, qui tue `FER-3`** |

**Aucun de ces textes n'etait faux dans le registre ou je l'ai ecrit. Tous le sont devenus quand une
regle executable, un garde-fou ou un developpeur les a pris au mot - c'est-a-dire quand ils ont
servi.** La cause n'est pas l'inattention : c'est que ce depot a rendu mes phrases executables plus
vite que je n'ai change ma facon de les ecrire. `FER-1` a transforme mes parentheses en contrats
le 2026-09-03 ; les parentheses avaient ete ecrites la veille comme des exemples.

**Le remede est concret, il est teste sur ce document, et il a fonctionne :**

> **Avant qu'un lot ne soit ecrit sur une ligne dont je suis l'auteur, je relis son libelle comme si
> je devais satisfaire `FER-1` item par item, et je lis chaque condition que j'ai posee contre
> l'ecran, le champ ou le test qui devra la porter.**

C'est exactement ce que j'ai fait aujourd'hui : j'ai ouvert `retour.html` avant d'ecrire une ligne
sur `SCI-1`, et le defaut est apparu **avant** qu'un item n'existe, au lieu d'apparaitre a la porte
comme celui du calcul pose. La difference entre les deux n'est pas ma vigilance : c'est que j'ai
lu le fichier.

**Ce que je ne peux pas promettre** : que cela suffise. Sur les cinq defauts d'aujourd'hui, quatre
ont ete trouves en relisant mes propres textes pour leur usage, et un - le libelle de la nutrition -
n'a ete trouve qu'en imaginant l'item qu'il produirait. **Le seul test fiable d'un libelle est
d'essayer d'ecrire le pire item qu'il autorise.**

---

## 10. Les proprietes dont je reste la seule garde

Dit explicitement, comme a chaque fois, pour que l'equipe ne croie pas couvert ce qui ne l'est pas.

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **L'exactitude factuelle** en sciences et l'exactitude **institutionnelle** en EMC | **moi seule** | non - et c'est la plus lourde des trois matieres neuves |
| `EMC-2`, `EMC-5`, `HIS-2` - aucun item a deux reponses defendables | **moi seule** | non, par nature |
| `EMC-8` - aucun item ne demande a l'enfant de declarer un comportement | **moi seule** pour le jugement ; la forme *"que fais-tu"* est cherchable | **partiellement** |
| `EMC-3` amendee, `EMC-4`, `EMC-9` - la posture des items sensibles | **moi seule** | non |
| `EMC-10` - aucune personne reelle vivante nommee | **moi**, mais un nom propre se cherche | **partiellement** |
| `SCI-1` amendee - l'`explication` demonte bien la conception fausse | **moi seule** pour le fond ; **sa presence est mordable** | **partiellement** |
| `SCI-2`, `BIA-1`, `BIA-5` | **moi seule** | non |
| `FER-1` - chaque element du libelle en position de bonne reponse | **moi**, sur une population desormais derivee | non - `theo-archi` l'a dit contre son propre `ADR`, et il a raison |
| `FER-2` - dix items par ligne | **le compte est derive** depuis `ADR-0081` ; le **seuil** reste mon verdict | **le compte oui, le seuil non** |
| `FER-3` - deux taches distinctes | **moi seule** | non - une tache est une operation mentale, pas une forme de phrase |
| `POS-2` - l'item ne se repond pas sans poser l'operation | **moi**, et **je viens de manquer 11 items sur 11** | **oui, sur la division** : un reste de distracteur >= au diviseur est calculable |
| `VOI-3`, `VOI-5`, `VOI-8` - ce que l'adulte fait de sa voix | **personne, pas meme moi** | **non, par nature** |
| Le fait qu'un item rare puisse n'etre jamais tire (10 manches sur 11 ou 22 items) | **moi**, en lisant le fichier et non en jouant | non |

**Une ligne de ce tableau est neuve et je la souligne** : `POS-2` sur la division est **calculable**,
et il a fallu que je manque onze items pour m'en apercevoir. Un garde-fou qui verifierait que
*"aucun reste de distracteur n'est superieur ou egal a son diviseur"* aurait rougi sur les onze. Je
dis la propriete ; la forme est a `theo-archi`, la priorite a l'utilisatrice.

---

## 11. Ce que je route

| Fait | A qui | Ce qu'il en fait |
|---|---|---|
| **Le perimetre annonce vaut 6,0 a 7,0 et non 5,0. Je le decline par ecrit** (2.4) | `camille-po`, `sofia-sm`, l'utilisatrice | Quatre lots tiennent dans 5,0, pas cinq |
| **Sciences CM1 et EMC CM1 sont a 1,5 : je ne suis pas la source, et je l'ai ecrit de ma main le 2026-09-03** (2.2) | `camille-po`, `sofia-sm` | Verifiable sans me le redemander, dans les referentiels eux-memes |
| **EMC CP vaut 1,0 restreint aux lignes 1 a 7, et 1,5 des qu'il touche 8, 9, 10, 13 ou 14** (2.3) | `camille-po` | Un levier de 0,5 qui se decide en choisissant les lignes |
| **Un lot de fermeture porte 22 items en deux paquets de 11, pas 20 en un** (3.2) | developpeurs, `camille-po` | Impose par `DEC-0064`. **Le prix du lot ne change pas** |
| **Le lot d'histoire-geographie ferme trois lignes pour 1,5 ; aucun lot vierge ne peut faire mieux que deux** (6.2) | l'utilisatrice, `camille-po` | Le meilleur rapport de couverture du depot, laisse dehors par le perimetre annonce |
| **Plafond 8 lignes (perimetre annonce) ou 9 (avec le lot d'histoire-geographie). Attente : 6 a 8** (6) | `sofia-sm`, `camille-po`, l'utilisatrice | La cible de dix lignes et de 38 % n'est pas atteignable a ma borne |
| **`SCI-1` etait impossible et contredisait mon arbitrage du 2026-08-06 ; amendee** (1.1) | `camille-po`, developpeurs | Le champ `explication` est servi apres chaque reponse, juste ou fausse, et il est porte par l'item |
| **`EMC-3` et `FER-3` s'excluaient sur la ligne 3 ; `EMC-3` amendee** (1.3) | `camille-po`, developpeurs | Sans cet amendement, la ligne 3 de CP et la ligne 2 de CM1 ne fermaient jamais |
| **Deux libelles faux dans le referentiel de sciences que j'ai ecrit** : *vegetarien* pour *herbivore*, et *nutrition* rangee parmi ses propres composantes (1.2) | `camille-po` **via** `sofia-sm` | A corriger **avant** qu'un item ne soit ecrit : `FER-1` fait de la parenthese un contrat |
| **`POS-R1` est plus lourde que je ne l'ai ecrite : 11 items sur 11, pas 2** (1.4) | `camille-po`, `marc-dev`, `lea-dev` | `POS-2` n'est pas tenue sur une ligne fermee sous `POS-2`. Prix du rejeu : 0,5 |
| **`POS-2` est calculable sur la division** : aucun reste de distracteur >= a son diviseur (10) | `theo-archi` | Je dis la propriete, pas la forme |
| **Le regime du mot pour mot se compte par passage, pas par texte** (1.5) | `camille-po` | Les six reserves textuelles valent **0,25 ensemble**, pas 1,50 |
| **Textes fournis pour `COR-R2` et `EAU-R1`** (1.5) | `marc-dev` ou `lea-dev` | Les six reserves rentrent toutes dans le passage a 0,25 |
| **`EMC-10` - aucune personne reelle vivante nommee en EMC** (5.4) | `camille-po`, developpeurs | Nouvelle, datee, presque mordable |
| **`BIA-2` et `BIA-4` sont redondantes en EMC ; opposer `EMC-4` et `EMC-1`. `BIA-3` est sans objet** (5.5) | `camille-po`, developpeurs | Une condition qui n'ajoute rien fait rater celles qui ajoutent |
| **Precision de `FER-1`** : quand le libelle n'enumere pas, `FER-1` porte sur ses moities (4) | `camille-po`, developpeurs | Evite qu'un lot depense douze items sur les douze mois |
| **Clause 5 d'`ADR-0081` confirmee** : un item ne compte que sur une ligne (3.1) | `theo-archi` | Le point qui m'etait route. La clause 4 ne change pas |
| **Cinq des neuf lignes d'histoire-geographie `en cours` portent exactement un item** (7.4) | `camille-po`, `sofia-sm` | *En cours* est le mot le plus optimiste qui soit exact |
| **La dette a voix aura quatre sprints si s29 ne la paie pas ; `cp-son-dans-mot` reste le cas grave** (7.3) | l'utilisatrice, via `camille-po` | 0,25, un passage. `VOI-3` n'a aucun garde-fou et n'en aura jamais |
| **Le palier 2 est bloque sur un arbitrage prealable de moi, non ecrit, qui bloque six lignes - et il ne coute rien contre ma borne** (7.5) | l'utilisatrice, `camille-po` | Le meilleur rapport du document, hors du perimetre annonce |
| **Je recommande 4,50 avec 0,50 de marge, et cela coute deux lignes** (8.3) | l'utilisatrice, via `camille-po` | Le chiffrage est a moi, la decision est a elle |
| **J'audite chaque lot le jour de sa livraison, et le lot d'EMC passe en premier** (8.4) | `sofia-sm`, `camille-po` | Ne coute rien. A s28 j'ai rendu sept verdicts dans un seul commit, a la cloture |

---

## 12. Ce que ce document ne fait pas

- Il **ne prononce aucun Done pedagogique** : rien de s29 n'est construit.
- Il **ne revoque aucun Done de s28**. Les huit verdicts du 2026-09-04 tiennent, y compris sur la
  ligne de la division posee dont j'aggrave la reserve en 1.4. Ce que j'ajoute est **ajoute, date,
  et lisible comme nouveau**. Aucun refus de s28 n'est efface : le refus du paquet du vivant reste
  ecrit dans sa fiche avec son motif.
- Il **n'ecrit ni la matrice ni le backlog** : je donne des motifs, `camille-po` en fait des stories
  et des lignes. Les deux corrections de libelle de 1.2 sont routees, pas appliquees.
- Il **ne priorise pas**. Je dis ce que le perimetre annonce coute, je dis qu'il ne rentre pas, je
  dis ce qui rentre, je recommande - et l'utilisatrice tranche.
- Il **ne tranche aucun choix technique** : ni la forme d'un garde-fou de reste de distracteur, ni
  celle d'une liste de mots transparents, ni le palier 2. `theo-archi`.
- Il **ne rouvre pas la premisse de l'adulte accompagnant** et ne demande pas son extension.
- Il **ne durcit aucune barre anterieure**. `FER-1` a `FER-3`, `CON-1` a `CON-4`, `REC-1` a `REC-6`,
  `BIA-1` a `BIA-5`, `HIS-1` a `HIS-4`, `POS-1` a `POS-4`, `ANG-4`, `ANG-5`, `ANG-S1`, `ANG-S2`,
  `VOI-1` a `VOI-13`, `SCI-2`, `SCI-3`, `EMC-1`, `EMC-2`, `EMC-4` a `EMC-9` sont reconduites sans
  une virgule de changement. **Les seules barres qui bougent dans ce document sont deux des miennes,
  et elles bougent parce qu'elles etaient impossibles** : `SCI-1` et `EMC-3`. La seule barre neuve
  est `EMC-10`. **Les seules baisses sont sur mon propre prix** : le regime du mot pour mot lu par
  passage, et le lot de fermeture porte a vingt-deux items sans supplement.
