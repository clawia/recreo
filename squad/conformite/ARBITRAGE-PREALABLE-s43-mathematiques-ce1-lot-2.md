# Arbitrage prealable - s43, lot 2 : cinq lignes de mathematiques CE1 (`MAT-CE1-07`, `-08`, `-10`,
# `-11`, `-12`)

- **Auteur** : `nadia-prof`. **Date** : 2026-09-25. **Sprint** : s43, hors lot de contenu.
- **Ce qui m'est pose** : arbitrage pedagogique prealable, demande par l'utilisatrice, sur quatorze
  lignes de mathematiques CE1 encore `non couvert` que `DEC-0099`, section 4, range en candidates de
  second rang **faute d'arbitrage pedagogique ecrit** - *"Engager l'une de ces quatorze lignes
  suppose, comme a chaque sprint CE1 precedent (s37 a s39), un arbitrage neuf demande a `nadia-prof`
  avant tout avis de faisabilite de `theo-archi`"*. Le travail est decoupe en trois lots paralleles ;
  **ce document ne traite que le lot 2** : `MAT-CE1-07`, `MAT-CE1-08`, `MAT-CE1-10`, `MAT-CE1-11`,
  `MAT-CE1-12`.
- **Ce document ne prononce aucun Done**, pedagogique ou technique, n'ecrit ni backlog ni garde-fou,
  ne cree ni ne deplace aucune ligne de `squad/programme/MATRICE-couverture.md`, ne choisit aucun
  mecanisme technique, **ne fixe aucun tarif et ne rend aucun avis de faisabilite**.
- **Ce que je n'ai pas joue, et je le dis plutot que de le laisser croire.** Les cinq lignes sont
  `non couvert` : aucun paquet ne les sert, il n'y a rien a lancer. C'est un **audit degrade** au
  sens de ma methode, et c'est le meme genre de document que
  `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`. Ce que j'ai fait a la place, et qui
  n'est pas rien : relu les trois referentiels de mathematiques (CP, CE1, CM1), relu mes quatre
  arbitrages CE1 anterieurs, et **lu le contenu servi lui-meme** - les sept paquets `ce1-*.yaml`,
  `cp-semaine.yaml`, `cp-qlm-saisons.yaml` - pour verifier les collisions sur des items reels plutot
  que sur des intitules.
- **Lien** : `DEC-0099` (section 4), `DEC-0089`, `DEC-0091`, `DEC-0092`,
  `squad/conformite/ARBITRAGE-s36-longueur-d-enonce-au-ce1.md` (`LON-CE1-1`, `LON-CE1-2`),
  `ARBITRAGE-s37-operande-nul-et-forme-du-tirage-a-l-echelle-100.md` (`ADD-CE1-1`, `ADD-CE1-2`),
  `ARBITRAGE-s38-soustractions-complements-dizaine-et-moities-ce1.md` (`SOU-CE1-1` a `MOI-CE1-2`),
  `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md` (`MES-CE1-1` a `MES-CE1-3`, `MON-CE1-1`
  a `MON-CE1-4`, `SOL-CE1-1` a `SOL-CE1-4`),
  `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md` (conditions `T-1` a `T-7`),
  `ARBITRAGE-PREALABLE-s13-multiplications-divisions-et-durees-cm1.md` (conditions `D-1` a `D-11`),
  `ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md` (`ETA-1` a
  `ETA-10`, et `ETA-4b` en section 12.1.1),
  `squad/conformite/FICHE-bandit-manchot-comparer-cp.md` (`GRA-1` a `GRA-17`),
  `squad/conformite/FICHE-bandit-manchot-semaine-cp.md` (`SEM-1`, amendee par moi le 2026-09-24).

---

## 0. Deux corrections sur mes propres constats, avant de m'appuyer dessus

Je verifie ce que j'ai ecrit avant de le reemployer. Deux ecarts, l'un de reference et l'autre de
fond, et le second change un verdict de ce document.

**0.1 - Une reference fausse dans mon arbitrage de s39, et elle porte precisement sur une ligne de
ce lot.** `ARBITRAGE-PREALABLE-s39-den-longueur-monnaie-solides.md`, section 2, ecrit que *"lire
l'heure sur une horloge"* (`MAT-CE1-11`) est *"deja note `non couvert` 'faute de ce rendu' dans la
matrice"*. **C'est faux sur le document cite.** La ligne `MAT-CE1-11` de
`squad/programme/MATRICE-couverture.md` porte `non couvert` **sans aucun motif** : la colonne "Jeu(x)
couvrant" y vaut `-` et rien n'y explique pourquoi. La formule *"encore non couvert faute de ce
rendu"* vient de `docs/backlog/backlog.md`, dans la story de `S39-LONGUEUR` ecrite par `camille-po`,
et je l'ai attribuee a la matrice en la recopiant. Le fond de mon raisonnement de s39 n'en depend
pas - l'absence de rendu d'horloge, je l'ai verifiee autrement - mais **une reference qui pointe
vers un document qui ne dit pas ce qu'on lui fait dire est de la meme famille qu'une reference qui
pointe vers rien**, et ce depot sanctionne cette famille-la. Je le corrige ici plutot que de
batir mon verdict de la section 4 dessus.

**0.2 - J'ai moi-meme retire les mois du perimetre d'une ligne de mathematiques il y a un jour, et
cela decide la moitie de `MAT-CE1-11`.** Le 2026-09-24, j'ai amende `SEM-1` sur
`bandit-manchot-semaine-cp` (`adapters/adapter-content/src/main/resources/contenu/cp-semaine.yaml`,
en-tete : *"les mois et les saisons sortent du perimetre (recouvrement avec `QLM-CP-13`,
`DEC-0096`) : onze items en sont retires"*). `QLM-CP-13` - *"Nommer et ordonner les mois de l'annee
et les saisons"* - est **`couvert-valide` depuis s41**, servi par `bandit-manchot-saisons-cp`, et
j'ai relu son contenu item par item avant d'ecrire cette ligne : `cp-qlm-saisons.yaml` porte
*"Quel mois vient après le mois de juin ?"*, *"Quel est le premier mois de l'année ?"*, *"Le mois de
mai vient-il avant ou après le mois d'avril ?"*, *"Parmi ces trois mois, lequel vient en dernier dans
l'année : octobre, décembre, août ?"* - **l'ordre des mois y est servi et ferme.** Un paquet de
mathematiques CE1 qui demanderait *"quel mois vient apres mars"* ne servirait donc pas `MAT-CE1-11`,
il servirait `QLM-CP-13` un niveau plus haut. La section 4 en tire les consequences.

---

## 1. `MAT-CE1-07` - tables de multiplication de 2, 5 et 10, puis de 3 et 4

> *"Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et 4."*

**Verdict : ACCEPTE SOUS CONDITIONS `TAB-CE1-1` a `TAB-CE1-6`.** La ligne est entierement servable
par du texte seul, sans rendu visuel, et elle peut atteindre `couvert-valide` sur un paquet unique.

### 1.1 Ce que la ligne dit, et ce qu'elle ne dit pas

**L'espace est ferme par l'intitule, et il n'est pas celui du CM1.** `MAT-CM1-02` dit *"tables de
multiplication (jusqu'a x10), rappel automatise"* : 55 couples, toutes les tables. `MAT-CE1-07` en
nomme **cinq**, 2, 5, 10, 3 et 4. L'espace de ce paquet est donc :

> les produits `a x b` avec `a` dans {2, 3, 4, 5, 10} et `b` entier de 1 a 10, soit **50 produits**.

**Le mot *puis* n'est pas une virgule.** Il ordonne : 2, 5 et 10 d'abord - ce sont les tables qui se
construisent sur un comptage (de deux en deux, de cinq en cinq, de dix en dix) - 3 et 4 ensuite,
qui demandent un vrai rappel. `TAB-CE1-2` traduit cet ordre en proportions plutot que de le laisser
comme une intention.

**Ce que je ne transpose pas du CM1, et le motif.** `T-1` exige au CM1 les 55 couples ; je reprends
le principe, pas le chiffre. Surtout, **`T-2` plafonnait au CM1 le facteur 10 avec le facteur 1**,
comme deux cas qui *"ne demandent aucun rappel de table"*. **Cette moitie-la ne se transpose pas** :
au CE1, la table de 10 est **nommee par la ligne** comme une competence a acquerir, pas comme un cas
degenere. Plafonner le facteur 10 ici reviendrait a plafonner un tiers de ce que la ligne demande.
C'est exactement le mouvement que j'ai fait a s37 entre `DEC-0004` (plancher d'operande nul au CP)
et `ADD-CE1-1` (plafond seul au CE1) : une condition se transpose avec son motif, jamais avec son
chiffre. **Le facteur 1, lui, reste degenere aux deux niveaux**, et `TAB-CE1-3` le plafonne.

### 1.2 Une collision reelle avec une ligne deja fermee, et ce qu'elle coute

**La table de 2, ce sont les doubles, et les doubles sont deja servis.** `MAT-CE1-03` est
`couvert-valide` depuis s38 sur quatre paquets, dont `ce1-additions-jusqu-a-100` (forme 3
d'`ADD-CE1-2`, *"double"*, plancher de 5 %) et `ce1-moities`. `2 x 7` et *"le double de 7"* sont le
**meme fait numerique**.

Ce n'est pas un motif de refus, et je le dis nettement : qu'un fait soit rencontre sous deux gestes
differents est bon pedagogiquement - c'est meme ce qui le rend disponible. Ce qui serait fautif,
c'est que le **geste** soit le meme. `ADR-0081` attribue un item a une ligne et une seule ; un item
ecrit *"Quel est le double de 7 ?"* dans un paquet declarant `MAT-CE1-07` servirait `MAT-CE1-03`
sous un autre nom. `TAB-CE1-5` ferme ce glissement par la forme de l'enonce.

### 1.3 Les conditions

> **`TAB-CE1-1` - espace couvert.** Chacun des 50 produits `a x b` (`a` dans {2, 3, 4, 5, 10},
> `b` de 1 a 10) apparait **au moins une fois** dans le paquet. Motif : un paquet qui ne porte que
> les entrees faciles ne travaille pas les tables - je reprends `T-1` du CM1 dans son principe.

> **`TAB-CE1-2` - l'ordre du *puis*, en proportions.** Les items dont le facteur de table est 2, 5
> ou 10 font **au moins la moitie** du paquet ; ceux dont le facteur de table est 3 font **au moins
> 15 %** ; ceux dont le facteur de table est 4 font **au moins 15 %**. *Unite de compte : l'item.
> Le facteur de table d'un item est celui qui appartient a {2, 3, 4, 5, 10} ; si les deux facteurs y
> appartiennent, c'est le plus petit.*

> **`TAB-CE1-3` - le facteur 1 est un cas degenere.** Les items comportant un facteur 1 font **au
> plus une manche sur cinq**. Pas de plancher : zero est conforme. **Ce plafond ne vaut pas pour le
> facteur 10**, pour le motif de la section 1.1.

> **`TAB-CE1-4` - les deux ordres.** Pour les produits dont les deux facteurs different et dont
> aucun ne vaut 1, le paquet presente **au moins un tiers de ses items avec le facteur de table en
> seconde position** (`7 x 3` et pas seulement `3 x 7`). Motif : un enfant qui ne voit jamais que le
> facteur de table en tete apprend sa table dans un seul sens, et la commutativite est precisement
> ce qui rend les cinq tables suffisantes pour en couvrir beaucoup plus.

> **`TAB-CE1-5` - forme de l'enonce, et ce qu'elle exclut.** L'enonce est une expression numerique
> nue portant le **signe multiplie** `×` (U+00D7), jamais la lettre minuscule `x`. Les propositions
> sont des entiers sans unite. **Sont exclus** : le facteur manquant (`3 × ? = 21`, geste inverse),
> la division, la mise en situation (*"3 sachets de 5 billes"* - c'est `MAT-CE1-06`, voir section 6),
> l'addition reiteree ecrite comme telle (`5 + 5 + 5` - `MAT-CE1-06` egalement), et **toute
> formulation par le double ou la moitie** (*"le double de 7"* - c'est `MAT-CE1-03`, deja ferme,
> section 1.2). *`T-4` du CM1 est reprise sans changement sur le signe ; le reste est une reecriture
> de `T-7` pour l'espace du CE1.* L'indice, lui, **peut** nommer le double comme strategie
> (*"2 × 7, c'est le double de 7."*) : l'indice enseigne un chemin, il ne definit pas le geste.

> **`TAB-CE1-6` - distracteurs, et les trois rangs.** Chaque distracteur appartient a l'une des
> quatre familles suivantes, et le fichier nomme la famille a cote de l'item :
> **(a)** le produit voisin par defaut, `a × (b - 1)`, si `b >= 2` ;
> **(b)** le produit voisin par exces, `a × (b + 1)` ;
> **(c)** la somme des facteurs, `a + b`, **uniquement si `a + b < a × b` et `a + b > (a × b) / 2`** ;
> **(d)** le produit de la table voisine, `(a - 1) × b` ou `(a + 1) × b`, le facteur restant dans
> {2, 3, 4, 5, 10}.
> Les trois propositions d'un item sont deux a deux distinctes. **Sur le paquet entier, chacun des
> trois rangs de la bonne reponse - la plus petite, celle du milieu, la plus grande - porte au moins
> 10 % et au plus 50 % des items.**

### 1.4 Les deux verifications que je fais avant de poser ces conditions, et non apres

**Satisfaisabilite de `TAB-CE1-1` et `TAB-CE1-2` ensemble.** Les 50 produits se repartissent en 30
sur les tables de 2, 5 et 10, et 20 sur celles de 3 et 4 (10 chacune). Un paquet de 50 items a
raison d'un par produit donne 60 % / 20 % / 20 % : les trois planchers de `TAB-CE1-2` (50 %, 15 %,
15 %) sont tenus avec de la marge. Les deux conditions sont donc satisfaisables **au plus petit
paquet qui tient `TAB-CE1-1`**, et a fortiori au-dela.

**Atteignabilite des trois rangs, faite a la main - c'est l'omission que j'ai payee a s38 sur les
moities, et je ne la refais pas.** Le garde-fou
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` refuse un rang sous 10 % et un rang
au-dessus de 50 % ; sa liste de dettes `DETTE_DE_SEUIL_MINIMUM` est **a son plafond exact de trois
paquets** (`cm1-perimetre`, `cp-soustractions`, `cp-complements-10`), donc **aucun paquet neuf ne
peut y etre ajoute**. Un jeu de distracteurs qui condamne un rang a 0 % est donc rouge sans recours.

| Paire de familles declarees | Position des deux distracteurs | Rang de la bonne reponse |
|---|---|---|
| (a) + (b) | un dessous, un dessus | **intermediaire** |
| (a) + (c) | les deux dessous | **la plus grande** |
| (a) + (d) avec `(a - 1) × b` | les deux dessous | **la plus grande** |
| (b) + (d) avec `(a + 1) × b` | les deux dessus | **la plus petite** |

Les trois rangs sont atteignables, et chacun par au moins une paire realisable sur une grande partie
de l'espace. **Le piege que la famille (c) porte, et que je nomme pour qu'on ne le decouvre pas a
l'ecriture** : pour `2 × 2`, la somme des facteurs vaut 4, c'est-a-dire le produit lui-meme - c'est
`T-5` du CM1, et la clause `a + b < a × b` de `TAB-CE1-6` l'exclut par construction. Le cas `b = 1`
est exclu de la meme facon (`2 × 1 = 2`, somme 3, **superieure** au produit). J'ai verifie ces deux
cas sur l'espace entier des 50 produits plutot que de les supposer isoles : **ce sont les seuls
deux ou la somme n'est pas strictement inferieure au produit.**

---

## 2. `MAT-CE1-08` - problemes d'addition, de soustraction et de multiplication a une ou deux etapes

> *"Résoudre des problèmes d'addition, de soustraction et de multiplication à une ou deux étapes."*

**Verdict : ACCEPTE SOUS CONDITIONS `PRO-CE1-1` a `PRO-CE1-7`.** Servable par du texte seul, et la
ligne peut atteindre `couvert-valide` - **sous reserve d'une exclusion de forme que je pose parce
qu'une ligne deja fermee possede cette forme.**

### 2.1 La collision avec `MAT-CE1-13`, verifiee sur les items et non sur les intitules

`MAT-CE1-13` (monnaie) est **`couvert-valide` sans reserve depuis s39**. J'ai relu
`ce1-monnaie.yaml` : six de ses douze items sont exactement des problemes de soustraction en
situation, a une etape -

> *"Un crayon coûte 180 centimes. Léo paye avec une pièce de 2 euros. Combien lui rend-on ?"*
> *"Un vélo coûte 4200 centimes. Yanis paye avec un billet de 50 euros. Combien lui rend-on ?"*

et c'est **moi** qui ai exige cette part par `MON-CE1-3` (*"au moins un tiers des items portent
explicitement sur le rendu de monnaie"*). La forme du probleme d'achat appartient donc a
`MAT-CE1-13`, par une condition que j'ai ecrite et par un Done que j'ai prononce. `ADR-0081`
attribuant un item a une ligne et une seule, un paquet de `MAT-CE1-08` qui la reprendrait
doublonnerait une ligne fermee au lieu d'en ouvrir une neuve. `PRO-CE1-4` l'exclut.

**Ce que cette exclusion n'est pas** : ce n'est pas une jalousie de perimetre, et elle ne coute
presque rien - les situations d'un probleme de CE1 sont innombrables hors de l'achat. Je la pose
parce qu'a l'audit je la verifierai, et qu'il vaut mieux qu'elle soit lue avant d'ecrire vingt items.

### 2.2 Ce que la ligne demande vraiment, et le glissement vers le CP

`MAT-CP-06` dit *"Résoudre des problèmes simples d'addition/soustraction en situation (1 étape)"*, et
`bandit-manchot-problemes-additifs-cp` la sert. Ce qui fait de `MAT-CE1-08` une ligne de CE1 et non
un CP reetiquete, c'est **la seconde etape** et **la multiplication**. Un paquet entierement compose
de problemes additifs a une etape tiendrait la lettre de l'intitule (*"une ou deux etapes"*) et
servirait `MAT-CP-06`. `PRO-CE1-1` et `PRO-CE1-2` bornent cela des deux cotes.

### 2.3 Les conditions

> **`PRO-CE1-1` - une ou deux etapes, et les deux existent.** Les items a **deux etapes** font **au
> moins un tiers** du paquet, ceux a **une etape** au moins un quart. Aucun item ne porte trois
> etapes - c'est `MAT-CM1-07`, deja servie et fermee au CM1.

> **`PRO-CE1-2` - les trois operations sont servies.** Addition, soustraction et multiplication sont
> chacune une etape d'**au moins un cinquieme** des items. *Unite de compte : l'item ; un item ou une
> operation apparait aux deux etapes compte une fois.* Motif : la ligne nomme les trois, et un paquet
> sans multiplication ne la couvre pas.

> **`PRO-CE1-3` - l'unique etape d'un item a une etape n'est jamais une multiplication.** La
> multiplication apparait **uniquement comme l'une des deux etapes d'un item a deux etapes**. Motif :
> un probleme a une seule etape multiplicative (*"3 sachets de 5 billes, combien de billes ?"*) est
> mot pour mot ce que `MAT-CE1-06` nomme - *"comprendre le sens de la multiplication (addition
> réitérée, disposition en lignes et en colonnes)"*. Laisser cette forme ici rendrait les deux lignes
> indiscernables, et `ADR-0081` n'attribue un item qu'a une seule. *`MAT-CE1-06` n'est pas dans mon
> lot : je ferme ma porte de ce cote, je ne prejuge pas de ce que l'instance qui la traite decidera
> du sien.*

> **`PRO-CE1-4` - formes exclues.** Aucun item ne porte un achat, un prix, une piece, un billet, un
> euro ni un centime (section 2.1, `MAT-CE1-13` fermee). Aucun item ne porte un **partage** ni un
> **groupement** (*"partager 12 billes entre 3 enfants"*, *"combien de sachets de 4 dans 20"*) : c'est
> `MAT-CE1-18`, hors de mon lot, voir section 6. Aucune division. Aucun nombre superieur a 1000 et
> aucun resultat intermediaire superieur a 1000 (`MAT-CE1-01` borne l'univers numerique du CE1).
> **Aucune operation qui exigerait une technique posee** : les deux etapes sont a la portee du calcul
> mental que `MAT-CE1-03` a ferme - sinon l'item sert `MAT-CE1-04` ou `MAT-CE1-05`, hors de mon lot.

> **`PRO-CE1-5` - distracteurs, trois familles et non deux.** Chaque distracteur appartient a l'une
> des familles suivantes, et le fichier nomme la famille a cote de l'item :
> **(a)** le **resultat intermediaire** - l'enfant s'est arrete en chemin (items a deux etapes) ;
> **(b)** le resultat obtenu en remplacant l'operation de la **derniere** etape par son inverse ;
> **(c)** le resultat obtenu en remplacant l'operation de la **premiere** etape par son inverse, la
> seconde etant ensuite conduite correctement - l'enfant a mal lu la relation puis a poursuivi sans
> faute (items a deux etapes) ;
> **(d)** un **nombre de l'enonce recopie sans calcul** (items a une etape).
> La famille (c) n'est utilisable que si l'inversion donne un entier positif **et** correspond a une
> melecture plausible de cet enonce-la ; sinon l'item declare deux autres familles. *Cette derniere
> clause n'est calculable par aucun test : elle reste dans ma seule main, item par item, a l'audit.*
> Les trois propositions sont des entiers positifs deux a deux distincts. **Sur le paquet entier,
> chacun des trois rangs de la bonne reponse porte au moins 10 % et au plus 50 % des items.**

> **`PRO-CE1-6` - enonce et situations.** `LON-CE1-1` (20 mots) et `LON-CE1-2` (3 phrases, la
> derniere etant la question) s'appliquent, sans amenagement. Aucune situation ne suppose que
> l'enfant a un jardin, une chambre a lui, un animal, de l'argent de poche, une voiture a la maison
> ou un depart en vacances. Aucun role n'est distribue par genre. *Reprise de `ETA-8` du CM1 et de
> `D-9`.*

> **`PRO-CE1-7` - indice, et volume.** L'indice donne **la methode de la premiere etape seule**,
> jamais un resultat (*"Cherche d'abord combien il y a de billes dans les trois sachets."*), **douze
> mots au plus**, et il n'est pas identique sur tout le paquet. **Au moins vingt items.**

### 2.4 Les deux verifications faites avant de poser, et non apres

**`LON-CE1-1` tient-elle sur un probleme a deux etapes ?** C'est le point ou ma propre borne de s36
pouvait rendre la ligne inecrivable : au CM1, `ETA-3` autorise **30 mots** et `D-8` **25**, la ou
`LON-CE1-1` en autorise 20. Je verifie sur un enonce reel plutot que de l'esperer :

> *Léa a 12 billes. Sami lui donne 3 sachets de 5 billes. Combien Léa a-t-elle de billes ?*

**17 mots, 3 phrases, la derniere etant la question**, sous la convention de comptage d'`ADR-0089`
reprise par `ARBITRAGE-s36`. Deux etapes (`3 × 5 = 15`, puis `12 + 15 = 27`), aucune donnee hors de
l'enonce. La borne tient avec trois mots de marge, et **20 reste un plafond, pas une cible** - je
me repete de s36 exprès, parce que la marge de trois mots est ici la tentation.

**Atteignabilite des trois rangs - et c'est la correction que `ETA-4b` a deja payee au CM1, que je
n'attends pas de refaire.** Avec les seules familles (a) et (b), le rang median est
**structurellement inatteignable**, et je le reverifie sur le CE1 plutot que de le recopier du CM1 :
si la derniere etape est une addition, (a) et (b) sont tous deux **sous** le resultat ; si c'est une
soustraction, tous deux **au-dessus** ; si c'est une multiplication, tous deux **sous**. Le rang du
milieu vaut 0 % sur tout paquet ainsi construit - exactement le defaut que
`DETTE_DE_SEUIL_MINIMUM`, a son plafond de trois, ne peut plus absorber. Les familles (c) et (d)
sont donc **necessaires**, pas decoratives :

| Composition de l'item | Distracteurs | Rang de la bonne reponse |
|---|---|---|
| Deux etapes, 2e = addition, familles (a) + (b) | les deux dessous | la plus grande |
| Deux etapes, 2e = soustraction, familles (a) + (b) | les deux dessus | la plus petite |
| Deux etapes, familles (a) + (c) | de part et d'autre selon les operations | **intermediaire, atteignable** |
| Une etape, soustraction `24 - 7 = 17`, familles (b) + (d) | `31` dessus, `7` dessous | **intermediaire** |
| Une etape, addition, familles (b) + (d) | les deux dessous | la plus grande |

Les trois rangs sont atteignables, et le rang median l'est par deux voies independantes.

---

## 3. `MAT-CE1-10` - comparer et mesurer des masses et des contenances (gramme, kilogramme, litre)

> *"Comparer et mesurer des masses et des contenances (gramme, kilogramme, litre)."*

**Verdict : COUVERTURE PARTIELLE nommee.** ACCEPTE SOUS CONDITIONS `MAS-CE1-1` a `MAS-CE1-6` pour la
part *"comparer"* et *"utiliser le gramme, le kilogramme, le litre"*. **Le verbe *mesurer* n'est pas
servable par du texte seul et ne l'est pas ici.** La ligne ne peut donc pas passer `couvert-valide`
sur un paquet textuel : **au mieux `en cours`**, avec mention.

### 3.1 Pourquoi *mesurer* tombe, et ce n'est pas une deduction depuis `MAT-CE1-09`

Le precedent existe et il est de moi : `ARBITRAGE-PREALABLE-s39`, section 2, a separe *"mesurer...
avec une regle graduee"* de *"comparer"* et *"utiliser le centimetre et le metre"*, et la ligne
`MAT-CE1-09` est restee `en cours` pour ce motif apres un Done **VALIDE** sur sa part servie.
**Mais je ne deduis pas ce verdict-ci de celui-la** : mesurer une masse et mesurer une longueur ne
demandent pas le meme instrument, et un verdict qui se transporterait par ressemblance serait
exactement ce que je reproche aux autres. Je le reverifie :

- **Mesurer une masse**, c'est poser un objet sur une **balance** et lire une aiguille ou un
  affichage, ou equilibrer deux plateaux avec des masses marquees. **Aucun rendu de balance n'existe
  dans le depot** ; la seule mecanique visuelle est la designation sur image, et elle ne porte aucune
  balance.
- **Mesurer une contenance**, c'est **verser** dans un recipient gradue et lire le trait atteint.
  C'est un geste **sequentiel** sur un etat qui change - precisement ce que j'ai constate absent de
  `Designation` en lisant son code a s39 pour `DEN-2` : *"une zone designee ne change jamais d'etat
  au sein d'une manche"*.
- Donner un nombre de grammes dans un enonce n'est pas mesurer : **c'est deja avoir mesure a la place
  de l'enfant.** Meme phrase qu'a s39, et elle reste vraie ici pour une raison verifiee, pas
  recopiee.

### 3.2 Le second risque, propre a cette ligne : `MAT-CP-08` reetiquete CE1

`MAT-CP-08` dit *"Comparer des longueurs, des masses, des contenances (sans instrument gradué)"*, et
`bandit-manchot-comparer-cp` la sert, **`couvert-valide`** : j'ai verifie ses 18 items le
2026-08-27, **6 longueurs, 6 masses, 6 contenances**, comparaisons qualitatives entre objets
(`baleine`/`chat`/`sardine`, `piscine`/`gourde`/`tasse`).

Un paquet de CE1 qui demanderait *"Qu'est-ce qui est le plus lourd : une plume ou une brique ?"* ne
servirait donc pas `MAT-CE1-10` : il servirait `MAT-CP-08`, deja ferme, deux ans trop tard. **Ce qui
fait la difference entre les deux lignes, ce sont les unites** - `MAT-CP-08` dit *"sans instrument
gradué"*, `MAT-CE1-10` nomme le gramme, le kilogramme et le litre. `MAS-CE1-3` en fait une condition.

### 3.3 Une borne arithmetique que cette ligne rencontre et que `MAT-CE1-09` ne rencontrait pas

**Le gramme et le kilogramme sont separes par un facteur 1000, la ou le centimetre et le metre le
sont par 100.** Consequence que je n'ai vue qu'en ecrivant des exemples : `5 m = 500 cm` reste dans
l'univers numerique du CE1 (`MAT-CE1-01`, *"jusqu'à 1000"*), mais **`3 kg = 3000 g` en sort**. Les
conversions g/kg sont donc **presque toutes hors de portee du CE1**, et un paquet qui en ferait son
ordinaire enseignerait a ecrire des nombres que l'enfant n'a pas appris a lire.

Ce n'est pas une raison de refuser la ligne : c'est une raison de la servir **par le choix de
l'unite** plutot que par la conversion. *"Pour peser une pomme, quelle unite choisis-tu ?"* est le
geste que `MAT-CE1-10` nomme reellement par *"utiliser le gramme, le kilogramme"*, et il ne demande
aucun nombre a quatre chiffres.

**Et j'applique ici, d'avance, la correction que j'ai payee a s39 sur `MES-CE1-3`.**
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (`ADR-0041`) refuse un item dont les
propositions laissent des restes d'unite differents, et cite le cas *"'cm' face a 'm'"*. Un item de
choix d'unite dont les propositions seraient `150 g` et `150 kg` tombe **exactement** dans ce cas -
et il y tombe a bon droit, parce que ce ne sont pas deux reponses mais deux ecritures qu'un enfant
doit departager sans que le nombre l'y aide. **La forme que je prescris met le nombre dans l'enonce
et l'unite seule dans les propositions** (*"le gramme"*, *"le kilogramme"*, *"le litre"*) : trois
propositions sans nombre, donc sans reste divergent. C'est la forme que `ce1-solides` sert deja avec
des propositions en mots. Je l'ecris avant qu'on me le signale, parce que je me suis fait prendre une
fois sur ce point precis et que je ne compte pas l'etre deux.

### 3.4 Les conditions

> **`MAS-CE1-1` - grandeurs servies.** Les **masses** et les **contenances** sont chacune servies par
> **au moins un tiers** des items. La ligne nomme les deux ; un paquet de masses seules ne la couvre
> pas. *Transposition de `GRA-2` du CP, dont le plancher etait le quart pour trois grandeurs.*

> **`MAS-CE1-2` - unites, et leur ecriture.** Seuls le **gramme** (`g`), le **kilogramme** (`kg`) et
> le **litre** (`L`) sont employes : ni milligramme, ni tonne, ni centilitre, ni millilitre, hors de
> ce que la ligne nomme. **Le symbole du litre s'ecrit en majuscule `L`, jamais en minuscule** : `1 l`
> est illisible pour un enfant qui apprend a lire, le `l` et le `1` etant le meme trait dans la
> plupart des polices. *Motif de meme famille que `MES-CE1-2`, qui exclut les millimetres au CE1.*

> **`MAS-CE1-3` - toute comparaison porte sur des valeurs chiffrees avec leur unite.** Aucun item ne
> compare deux objets par leur seule nature (*"une plume et une brique"*) : ce geste-la est
> `MAT-CP-08`, ferme (section 3.2). Chaque proposition d'un item de comparaison est un objet dont
> l'enonce a donne la masse ou la contenance en chiffres.

> **`MAS-CE1-4` - aucun nombre superieur a 1000**, dans l'enonce comme dans les propositions
> (`MAT-CE1-01`). **Les seules conversions admises sont `1 kg = 1000 g` et `500 g = un demi-
> kilogramme`** ; toute autre conversion g/kg sort de l'univers numerique du CE1 (section 3.3). Les
> masses et contenances tirees restent plausibles pour un objet qu'un enfant de CE1 connait : une
> pomme en grammes, un sac de farine en kilogrammes, une bouteille ou un seau en litres - jamais une
> valeur qui ne correspond a rien de concret. *Meme exigence que `MES-CE1-1`.*

> **`MAS-CE1-5` - les trois propositions d'un item portent la meme unite, ou aucune n'en porte.** Un
> item de comparaison propose trois objets ou trois valeurs dans la **meme** unite ; un item de choix
> d'unite propose trois **noms d'unite** sans nombre. **Jamais un item ou une proposition porte une
> unite differente des deux autres** (section 3.3).

> **`MAS-CE1-6` - distracteurs, indice, et les trois rangs.** Les distracteurs plausibles sont : une
> erreur de facteur de conversion (multiplier ou diviser par 10, par 100, ou ne pas convertir du
> tout, la ou il faut 1000) ; l'objet le plus lourd la ou l'on demande le plus leger, et l'inverse ;
> l'unite voisine dans un item de choix d'unite. Jamais une masse ni une contenance negative, jamais
> une valeur absurde. **Aucun indice n'enseigne que la taille donne la masse** - c'est la reserve
> `GRA-15` que j'ai ouverte sur le paquet du CP le 2026-08-28, et c'est la confusion meme que
> *"comparer des masses"* existe pour defaire. **Sur le paquet entier, chacun des trois rangs de la
> bonne reponse porte au moins 10 % et au plus 50 % des items.**

### 3.5 La verification des trois rangs, faite ici aussi

Sur un item de comparaison, le rang de la bonne reponse se choisit par la **question**, pas par les
distracteurs : *"la plus lourde"* la met au rang haut, *"la plus legere"* au rang bas, *"ni la plus
lourde ni la plus legere"* au milieu. `ce1-longueurs.yaml` sert deja les trois formes sur ses sept
items de comparaison (*"Quelle est la plus longue ?"*, *"Quelle est la plus courte ?"*, *"Quelle
longueur n'est ni la plus grande ni la plus petite ?"*) : la construction est donc **demontree sur du
contenu servi**, pas supposee. Sur un item de conversion (`1 kg = ? g`, reponse `1000`, distracteurs
`100` et `10`), la bonne reponse est la plus grande ; sur un item de choix d'unite, les propositions
sont des mots et le rang se lit comme le garde-fou le lit, sans que l'auteur ait a le forcer.
**Les trois rangs sont atteignables sans contorsion.**

---

## 4. `MAT-CE1-11` - lire l'heure sur une horloge, et se reperer dans le calendrier

> *"Lire l'heure sur une horloge (heures, demi-heures, quarts d'heure) et se repérer dans le
> calendrier (mois, date)."*

**Verdict : COUVERTURE PARTIELLE nommee, et la part servable est plus etroite que l'intitule ne le
laisse croire.**

- **Premiere moitie, *lire l'heure sur une horloge* : REFUSEE par du texte seul.** Motif en 4.1.
- **Seconde moitie, *se reperer dans le calendrier*, partie *mois* : REFUSEE**, non pour un motif de
  forme mais parce qu'elle est **deja servie et fermee** par `QLM-CP-13`. Motif en 4.2.
- **Seconde moitie, partie *date* : ACCEPTEE SOUS CONDITIONS `CAL-CE1-1` a `CAL-CE1-5`.**

La ligne ne peut donc pas passer `couvert-valide` : **au mieux `en cours`**, avec une mention qui
nomme ce qui est servi et ce qui ne l'est pas, sur le patron exact de celle de `MAT-CP-09`
(*"moitie 'se reperer dans la journee, la semaine' seule ; la moitie 'lire l'heure pleine sur une
horloge' reste non couverte"*).

### 4.1 *Lire l'heure sur une horloge* - refusee, et le refus existe deja au CP

**J'ai relu mon propre verdict avant de m'en servir**, comme je dois le faire. `MAT-CP-09` dit
*"Se repérer dans la journée, la semaine ; lire l'heure pleine sur une horloge"* ;
`bandit-manchot-semaine-cp` est **`couvert-valide` avec une mention de couverture partielle que j'ai
exigee et redigee** : la moitie *lire l'heure pleine* **reste non couverte**, faute de cadran. Ce
refus est de moi, il est ecrit, il n'a jamais ete leve.

`MAT-CE1-11` demande **plus** : les demi-heures et les quarts d'heure, c'est-a-dire la lecture d'une
position d'aiguille sur un cadran, la ou le CP ne demandait que l'heure pleine. **Si l'heure pleine
n'etait pas servable sans cadran, le quart d'heure l'est encore moins.**

**Et la voie textuelle ne sauve pas cette moitie, pour un motif que j'ai deja ecrit ailleurs.** Un
item du genre *"Il est huit heures et quart. Quelle heure est-il ?"* avec les propositions `8 h 15`,
`8 h 45`, `9 h 15` ne fait pas **lire une horloge** : il fait traduire une phrase en chiffres. C'est
mot pour mot le motif par lequel j'ai refuse le texte seul pour les solides a s39 - *"ce n'est pas
de la reconnaissance, c'est de la traduction... l'enfant associe un nom a une definition qu'on vient
de lui donner"*. Un enfant qui reussirait ce jeu pourrait rester incapable de lire l'horloge de sa
classe.

**Ce que j'ecris pour que la question ne me revienne pas sous une autre forme** : un rendu de cadran
- deux aiguilles, douze reperes, aux positions **declarees par le contenu** et non par une police -
ouvrirait cette moitie, sur le meme registre que `FIG-1` a `FIG-4` et `SOL-CE1-1` a `SOL-CE1-4`. **Je
ne prejuge pas de sa faisabilite** : `item illustre` existe, seul le materiau manque, et c'est a
`theo-archi` de dire ce que cela coute.

### 4.2 *Les mois* - refusee parce que deja fermee ailleurs, pas parce qu'elle serait mauvaise

Section 0.2 : `QLM-CP-13` (*"Nommer et ordonner les mois de l'annee et les saisons"*) est
`couvert-valide` depuis s41, et `cp-qlm-saisons.yaml` sert l'ordre des mois de facon exhaustive -
mois suivant, mois precedent, premier mois, dernier mois, avant/apres, choix parmi trois. **J'ai
moi-meme retire les mois du paquet de mathematiques `cp-semaine` le 2026-09-24 pour ce recouvrement
exact.** Les remettre dans un paquet de mathematiques CE1 serait defaire d'une main ce que j'ai fait
de l'autre la veille.

**Ce n'est pas un refus de la competence, c'est un refus du doublon** : l'enfant travaille l'ordre
des mois, simplement il le fait sur la ligne qui le porte deja.

### 4.3 *La date* - acceptee, et c'est une matiere reellement neuve et reellement mathematique

Ce qui reste n'est pas un residu : c'est **le nombre de jours de chaque mois, la lecture et
l'ecriture d'une date, le lendemain et la veille d'une date, et le reperage d'une date dans le
mois**. Rien de tout cela n'est servi par `QLM-CP-13`, qui ne porte aucun numero de jour, et rien
n'est servi par `cp-semaine`, borne aux moments de la journee et aux jours de la semaine. C'est du
comptage et de l'ordre sur des nombres - de la mathematique, pas de la culture calendaire.

> **`CAL-CE1-1` - perimetre servi, et sa mention.** Le paquet porte **uniquement** des items de date.
> **Sont exclus** : tout item sur l'ordre ou le nom des mois pris pour eux-memes (`QLM-CP-13`,
> section 4.2), tout item sur les saisons, tout item sur les jours de la semaine ou les moments de la
> journee (`MAT-CP-09`, servi), et tout item demandant de lire une heure (section 4.1). **La mention
> de couverture partielle est due et non negociable** ; sans elle la matrice dirait plus que ce que
> le jeu sert.

> **`CAL-CE1-2` - formes servies, au moins trois des quatre.** (i) nombre de jours d'un mois donne ;
> (ii) lendemain ou veille d'une date, y compris au changement de mois ; (iii) ordre de deux ou trois
> dates ; (iv) lecture d'une date ecrite en toutes lettres vers sa forme chiffree, ou l'inverse.
> Chacune des formes servies porte **au moins un cinquieme** des items.

> **`CAL-CE1-3` - exactitude du calendrier, et le cas de fevrier.** Les nombres de jours sont ceux du
> calendrier reel. **Fevrier compte 28 jours**, et **aucun item ne porte sur l'annee bissextile** :
> la regle des 29 jours n'est pas au programme du CE1 et transformerait un item exact en piege. Les
> dates employees sont des dates reelles (jamais un 31 avril, jamais un 30 fevrier), **y compris dans
> les distracteurs**.

> **`CAL-CE1-4` - aucun ancrage a une annee, a une fete ni a une culture.** Aucune date ne suppose
> une rentree scolaire, des vacances, une fete religieuse ou nationale, un anniversaire familial, ni
> une annee en cours qui rendrait le paquet faux dans douze mois. *Motif de meme famille que `SEM-7`,
> que j'ai posee sur le paquet de la semaine au CP.*

> **`CAL-CE1-5` - enonce, distracteurs, indice.** `LON-CE1-1` et `LON-CE1-2` s'appliquent.
> Distracteurs plausibles : le nombre de jours d'un mois voisin (30 pour 31 et l'inverse), la date
> decalee d'un jour, le mois decale d'un rang sur un changement de mois. Trois propositions deux a
> deux distinctes. L'indice donne une methode (*"Compte sur tes doigts : les mois en creux ont 30
> jours."*), jamais la reponse, et il n'est pas identique sur tout le paquet. **Sur le paquet entier,
> chacun des trois rangs de la bonne reponse porte au moins 10 % et au plus 50 % des items.**

**Verification des trois rangs, faite a la main sur la forme (i), qui est la plus contrainte** :
janvier (31, distracteurs 30 et 28) met la bonne reponse au rang **le plus grand** ; fevrier (28,
distracteurs 30 et 31) au rang **le plus petit** ; avril (30, distracteurs 31 et 28) au rang
**intermediaire**. Les trois rangs sont atteignables sur la seule forme (i), sans rien forcer, parce
que les trois valeurs reelles du calendrier suffisent a les produire.

---

## 5. `MAT-CE1-12` - calculer une duree simple en heures entieres ou en demi-heures

> *"Calculer une durée simple en heures entières ou en demi-heures."*

**Verdict : ACCEPTE SOUS CONDITIONS `DUR-CE1-1` a `DUR-CE1-6`.** Servable par du texte seul -
c'est de l'arithmetique sur des instants, aucun cadran n'est requis - et la ligne peut atteindre
`couvert-valide` sur un paquet unique. **C'est la ligne la plus nette des cinq**, et c'est aussi
celle ou le precedent du CM1 est le plus directement utilisable.

### 5.1 Ce que la ligne nomme, et la forme que je refuse d'avance

`MAT-CM1-10` dit *"Résoudre des problèmes de durées (calcul d'une durée, d'un horaire d'arrivée)"* -
**deux formes nommees**. `MAT-CE1-12` n'en nomme **qu'une** : *"calculer une duree"*.

**J'applique donc ici, sans changement, le principe que j'ai pose a s13, section 3.2** : la seconde
forme naturelle - donner l'instant de depart et la duree, demander l'arrivee - **n'est pas dans la
ligne**, elle n'entre pas dans ce paquet, et **son absence ne pourra pas m'etre opposee comme un trou
de couverture. Je ne la reclamerai pas apres coup.** C'est un bon motif de story a part, et je le
signale comme tel a `camille-po`. Melanger deux gestes sans avertir l'enfant fait varier d'un item a
l'autre ce qu'il doit faire, et au CE1 ce cout est plus eleve qu'au CM1.

**Un paquet de `MAT-CE1-12` est donc entierement compose d'items de la forme : deux instants donnes,
la duree a trouver.**

### 5.2 Ce que je transpose du CM1, et les deux choses que je ne transpose pas

**Je transpose** `D-2` (un instant ne s'ecrit pas comme une duree), `D-3` (journee de 24 heures, pas
de franchissement de minuit, pas de secondes, pas d'heure ambigue), le principe de `D-5` (la
difficulte est le franchissement, et elle doit etre presente sans etre partout), `D-6` (les
distracteurs sont des erreurs reelles et nommees), `D-9` (situations) et `D-10` (indice).

**Je ne transpose pas `D-4`** (*"les minutes ne sont pas toutes des quarts d'heure"*). Elle n'a aucun
objet ici : `MAT-CE1-12` borne les minutes a `00` et `30` par son propre intitule, *"heures entieres
ou demi-heures"*. Une condition qui exigerait d'autres minutes contredirait la ligne.

**Je ne transpose pas le plafond de duree de `D-5`** (cinq heures au CM1). Je le baisse a **quatre
heures**, et je dis pourquoi plutot que de l'affirmer : au CE1 l'enfant compte encore les heures une
a une pour franchir les demi-heures, et au-dela de quatre heures la manche mesure sa patience, pas sa
competence. **Ce n'est pas un durcissement d'une barre deja franchie** : aucun paquet de duree CE1
n'existe, `D-5` reste inchangee pour le CM1, et ce plafond-ci entre avant le premier item - la seule
fenetre ou une borne est gratuite.

### 5.3 Les conditions

> **`DUR-CE1-1` - une seule forme.** Chaque item donne **deux instants** et demande la **duree
> ecoulee**. Sont exclus : l'horaire d'arrivee et l'heure de depart (section 5.1) ; la lecture de
> l'heure (`MAT-CE1-11`, section 4.1) ; la conversion d'unite de temps seule (*"combien de minutes
> dans une heure ?"*) ; la comparaison de durees.

> **`DUR-CE1-2` - bornes des instants et des durees.** Les deux instants ont des minutes valant
> **`00` ou `30`**, et rien d'autre. Ils sont dans la **meme journee** ; **aucun item ne franchit
> minuit**. Aucune seconde. La journee s'ecrit sur 24 heures (`14 h 30`), **jamais** *"2 h 30 de
> l'apres-midi"* - ce serait une ambiguite, pas une difficulte. La duree cherchee vaut **au moins
> 30 minutes et au plus 4 heures**.

> **`DUR-CE1-3` - un instant ne s'ecrit pas comme une duree, et c'est ma condition la plus importante
> sur ce paquet.** Un enfant confond exactement cela : `2 h 30` sur une horloge et `2 h 30` de trajet
> ne sont pas le meme objet, l'un est un point, l'autre une longueur. **Les instants et les durees
> portent deux ecritures visiblement differentes, constantes dans tout le paquet** - par exemple
> `14 h 30` pour un instant et `2 heures et demie` ou `2 h 30 min` pour une duree. Le choix de la
> forme appartient a qui ecrit ; la constance est ma condition. **Et dans un meme item, les trois
> propositions portent la meme ecriture.** *`D-2` du CM1, reprise sans changement de fond : c'est le
> defaut que j'avais trouve dans `contenu-repris/quiz/cm1-heure.yml`, et il se reproduira ici si
> personne ne l'ecrit.*

> **`DUR-CE1-4` - le franchissement de la demi-heure est present, et il n'est pas partout.** **Entre
> un tiers et deux tiers des items** portent deux instants dont les minutes different (l'un a `00`,
> l'autre a `30`), c'est-a-dire une duree comportant une demi-heure. C'est **la** difficulte de cette
> competence a ce niveau : l'enfant qui compte en base 100 tombe juste tant que les minutes
> coincident et faux des qu'elles different. Un paquet sans aucun franchissement ne teste rien ; un
> paquet ou tous les items en portent un ne laisse aucune marche basse a l'enfant qui commence.

> **`DUR-CE1-5` - distracteurs, et les trois rangs.** Les trois erreurs reelles de cette competence,
> que je nomme pour que personne n'invente : **(a)** la demi-heure oubliee - l'enfant ne compte que
> les heures pleines (`de 9 h 30 a 11 h` repondu `2 heures` au lieu de `1 heure et demie`) ;
> **(b)** le comptage en base 100 - l'enfant retranche `30` de `00` en empruntant 100 au lieu de 60
> (`de 9 h 30 a 11 h` repondu `1 h 70 min`) ; **(c)** l'heure decalee d'un rang, minutes justes.
> Cette liste est un **plancher de ce qui est permis** : tout autre distracteur est admis s'il
> correspond a une erreur nommee dans le fichier a cote de l'item. Aucune proposition egale a une
> autre. **Sur le paquet entier, chacun des trois rangs de la bonne reponse porte au moins 10 % et au
> plus 50 % des items.**

> **`DUR-CE1-6` - enonce, situations, indice, volume.** `LON-CE1-1` et `LON-CE1-2` s'appliquent -
> **et non les 25 mots de `D-8`, qui valent pour le CM1 et pour lui seul**. Toutes les donnees sont
> dans l'enonce ; l'enfant n'a jamais a se souvenir de l'item precedent. Situations : l'ecole, la
> recreation, la cantine, un trajet, un film, un match, une recette, une visite - **aucune situation
> qui suppose que l'enfant prend l'avion, part en vacances, ou qu'un adulte le conduit quelque
> part** ; prenoms et roles varies et non stereotypes ; pas de retard dramatise. L'indice donne la
> methode, jamais le resultat (*"Va d'abord jusqu'a l'heure ronde, puis compte le reste."*,
> *"Une heure, c'est 60 minutes, pas 100."*), et il n'est pas identique sur tout le paquet.
> **Au moins vingt items.**

### 5.4 Les deux verifications faites avant de poser

**`LON-CE1-1` tient-elle sur un enonce de duree ?** Verifie sur un enonce reel :

> *Le film commence à 14 h et se termine à 16 h 30. Combien de temps dure-t-il ?*

**17 mots, 2 phrases**, la derniere etant la question, sous la convention d'`ADR-0089`. Sous cette
convention, `h` compte comme un mot - il contient une lettre - et j'ai compte ainsi. La borne de 20
tient avec trois mots de marge, alors que `D-8` avait du monter a 25 au CM1 : c'est que la duree se
dit en moins de mots que le probleme a deux etapes.

**Atteignabilite des trois rangs**, faite a la main comme pour les quatre autres lignes :

| Paire de distracteurs declaree | Position par rapport a la bonne reponse | Rang de la bonne reponse |
|---|---|---|
| (a) demi-heure oubliee + (c) heure decalee en moins | les deux dessous | **la plus grande** |
| (b) base 100 + (c) heure decalee en plus | les deux dessus | **la plus petite** |
| (a) demi-heure oubliee + (b) base 100 | un dessous, un dessus | **intermediaire** |

Les trois rangs sont atteignables, chacun par une paire realisable sur n'importe quel item a
franchissement. **Le paquet n'est donc pas structurellement condamne a un rang mort** - c'est la
verification que j'avais omise a s38 sur les moities, que j'ai payee par un amendement, et que je
fais desormais avant d'ecrire la clause, pas apres.

---

## 6. Collisions avec les neuf lignes CE1 hors de mon lot - signalees, non jugees

Une autre instance traite `MAT-CE1-01`, `-02`, `-04`, `-05`, `-06`, `-14`, `-15`, `-17`, `-18`. **Je
ne rends aucun verdict sur elles.** Je nomme ce que mon lot touche, pour que la coherence entre les
trois lots ne repose pas sur une coordination que nous n'avons pas.

| Ligne hors lot | Ce que mon lot en dit, et ou | Nature de la collision |
|---|---|---|
| `MAT-CE1-01` (nombres jusqu'a 1000) | `PRO-CE1-4` et `MAS-CE1-4` plafonnent tout nombre ecrit a 1000 | **Borne empruntee**, pas disputee. Si l'autre instance deplace l'univers numerique du CE1, mes deux clauses suivent |
| `MAT-CE1-02` (comparer, ranger, encadrer jusqu'a 1000) | `MAS-CE1-3` impose des comparaisons chiffrees. **Ranger trois masses chiffrees est aussi ranger trois nombres** | **Collision de geste, reelle.** Je la signale sans la trancher pour l'autre lot. Ce qui separe les deux ici, c'est que `MAS-CE1-2` et `MAS-CE1-5` imposent l'unite : l'enfant compare des grandeurs, pas des nombres nus. **Le meme point vaut deja, non signale a l'epoque, pour `ce1-longueurs.yaml` que j'ai valide a s39** (*"Un crayon mesure 18 cm, une trousse mesure 24 cm... Quelle est la plus longue ?"*). **Observation neuve et datee du 2026-09-25 ; je ne rouvre pas ce Done** et je n'en durcis pas les conditions - je l'ecris pour que le lot de `MAT-CE1-02` ne croie pas le terrain vide |
| `MAT-CE1-04` / `MAT-CE1-05` (operations posees) | `PRO-CE1-4` exclut toute etape hors de portee du calcul mental | **Frontiere**, deja tracee a s37 et s38 entre `MAT-CE1-03` et ces deux lignes. Je la reprends telle quelle |
| `MAT-CE1-06` (sens de la multiplication) | `PRO-CE1-3` lui laisse le probleme multiplicatif a une etape ; `TAB-CE1-5` lui laisse l'addition reiteree et la disposition en lignes et colonnes | **Collision majeure, et je ferme ma porte des deux cotes.** Un item *"3 sachets de 5 billes"* n'est ni `MAT-CE1-07` ni `MAT-CE1-08` sous mes conditions. Reste a l'autre instance de dire si c'est `MAT-CE1-06` |
| `MAT-CE1-14`, `-15`, `-17` (quadrillage, figures planes, symetrie) | Rien | **Aucune collision.** Je le dis plutot que de laisser le silence l'ambiguer |
| `MAT-CE1-18` (sens de la division, partage et groupement) | `PRO-CE1-4` exclut tout partage, tout groupement et toute division | **Collision majeure, fermee de mon cote.** La ligne 08 nomme trois operations et pas la division ; la ligne 18 nomme le partage et le groupement. Le decoupage est ecrit dans les deux intitules, je m'y tiens |

**Un avertissement sur les identifiants, parce que trois instances ecrivent en parallele et que je
l'ai constate plutot que suppose.** Mes cinq prefixes neufs sont `TAB`, `PRO`, `MAS`, `CAL`, `DUR`.
**Le lot 1 a deja ecrit les siens sur le meme arbre de travail quand je verifie** - `NUM`, `ORD`,
`ADP`, `SOP`, `MUL` - donc **aucune collision de mon cote avec lui**. Le lot 3 n'a rien ecrit a cette
heure. Le prefixe le plus expose reste **`PRO`**, qu'une instance traitant `MAT-CE1-18` (problemes de
partage) pourrait choisir pour la meme raison que moi. Si collision il y a, la CI rougira a
l'assemblage comme `ADR-0085` clause 5 le prevoit, et **la reparation sera un renommage, pas un
arbitrage** : aucun sens ne sera touche.

---

## 7. Collisions avec les sept jeux CE1 deja servis - verifiees sur les items

Verification demandee explicitement, faite sur le contenu et non sur les intitules.

| Jeu servi | Ligne | Collision avec mon lot |
|---|---|---|
| `bandit-manchot-additions-ce1` | `MAT-CE1-03`, fermee | **Oui, sur `MAT-CE1-07`** : la forme 3 d'`ADD-CE1-2` (double, plancher 5 %) est le meme fait numerique que la table de 2. **Fermee par `TAB-CE1-5`** (la formulation par le double est exclue de l'enonce ; l'indice peut la nommer). Section 1.2 |
| `bandit-manchot-moities-ce1` | `MAT-CE1-03`, fermee | **Oui, meme collision, meme cloture** |
| `bandit-manchot-soustractions-ce1` | `MAT-CE1-03`, fermee | Aucune. `PRO-CE1-4` garde les operations de mes problemes a portee du calcul mental, ce qui les rapproche de cette ligne sans les y faire tomber : la difference de geste est la **situation**, absente de ce paquet |
| `bandit-manchot-complements-dizaine-ce1` | `MAT-CE1-03`, fermee | Aucune |
| `bandit-manchot-longueurs-ce1` | `MAT-CE1-09`, `en cours` | **Oui, sur `MAT-CE1-10`, et de deux natures.** (i) **Forme identique** : ses sept items de comparaison sont exactement la forme que `MAS-CE1-3` prescrit, transposee des longueurs aux masses. Ce n'est pas un defaut, c'est une reussite a reutiliser. (ii) **Precedent de perimetre** : le verbe instrumente (*mesurer avec une regle graduee*) y est non servi ; le mien (*mesurer* une masse ou une contenance) l'est aussi, **mais verifie separement** (section 3.1) |
| `bandit-manchot-monnaie-ce1` | `MAT-CE1-13`, fermee | **Oui, sur `MAT-CE1-08`, et c'est la collision la plus concrete des cinq.** Six items sur douze sont des problemes de soustraction en situation, a une etape, exiges par ma propre condition `MON-CE1-3`. **Fermee par `PRO-CE1-4`** : aucun achat, aucun prix, aucune monnaie dans un paquet de `MAT-CE1-08`. Section 2.1 |
| `bandit-manchot-solides-ce1` | `MAT-CE1-16`, fermee | Aucune |

---

## 8. Ce que ce document ne fait pas

- Il ne prononce **aucun Done**, pedagogique ou technique. Aucune des cinq lignes ne bouge dans la
  matrice du fait de ce document.
- Il ne **chiffre aucun tarif** et ne rend **aucun avis de faisabilite** : ni le cout d'un paquet, ni
  la capacite d'un sprint, ni le choix entre un mecanisme statique et un mecanisme generatif.
  `DEC-0099` reste ce qu'il est ; le perimetre de s43 et de s44 appartient a `camille-po` et a
  `sofia-sm`, l'avis technique a `theo-archi`.
- Il ne rouvre ni `LON-CE1-1`, ni `LON-CE1-2`, ni `ADD-CE1-1`/`ADD-CE1-2`, ni `SOU-CE1-1` a
  `MOI-CE1-2`, ni `MES-CE1-1` a `MES-CE1-3`, ni `MON-CE1-1` a `MON-CE1-4`, ni `SOL-CE1-1` a
  `SOL-CE1-4` : toutes restent en vigueur, inchangees, et les miennes de ce document s'y ajoutent
  sans les amender.
- Il ne rouvre **aucun Done deja prononce**, et notamment pas celui de `bandit-manchot-longueurs-ce1`
  malgre l'observation de la section 6 sur `MAT-CE1-02`. Une barre qu'on releve apres qu'une equipe
  l'a atteinte fait de ma porte un caprice.
- Il ne touche **aucune des neuf lignes du lot voisin**, ni les quatre lignes candidates de s43 et
  s44 que `DEC-0099` chiffre.
- Il ne garantit `couvert-valide` sur **aucune** des cinq lignes : `MAT-CE1-07`, `MAT-CE1-08` et
  `MAT-CE1-12` peuvent l'atteindre ; `MAT-CE1-10` et `MAT-CE1-11` sont plafonnees `en cours` pour les
  motifs ecrits. Dans tous les cas, le Done se prononce apres que j'aie joue le jar servi, jamais
  avant.

---

## 9. Ce dont je reste la seule garde, et qu'aucun automate ne verra

Les trente conditions de ce document sont **decidables sur l'item seul ou sur la liste des items**,
donc automatisables sur le meme patron qu'`ADD-CE1-1`/`ADD-CE1-2`. **Tant qu'aucun garde-fou ne les
execute, c'est moi qui les tiens, en comptant.** Restent en plus dans ma seule main, et je les nomme
parce que sinon l'equipe croira couvert ce qui ne l'est pas :

1. **Que chaque paquet livre travaille reellement la ligne qu'il declare**, et pas la voisine plus
   facile ou deja fermee. Les sept collisions de la section 7 rendent ce glissement plus difficile ;
   elles ne le rendent pas impossible, et `ADR-0081` verifie qu'un code resout, jamais qu'il dit
   vrai.
2. **La plausibilite d'un distracteur comme erreur reelle d'un enfant sur cet enonce-la** - clause de
   `PRO-CE1-5`, de `TAB-CE1-6`, de `MAS-CE1-6` et de `DUR-CE1-5`. Aucun test ne sait juger qu'un
   nombre exact est une erreur credible.
3. **La lisibilite reelle d'un enonce conforme a `LON-CE1-1` et `LON-CE1-2`**, l'accessibilite de son
   vocabulaire, et la charge cognitive d'une situation a deux temps - un compteur de mots ne voit ni
   une subordonnee, ni un referent elide.
4. **L'exactitude arithmetique et calendaire** de chaque item, de chaque bonne reponse et de chaque
   distracteur. Un paquet entier peut etre faux avec une CI verte, et cela s'est deja produit dans ce
   depot.
5. **La progression a l'interieur d'une partie.** Toutes mes proportions sont des distributions ;
   aucune ne dit l'ordre des manches. Vingt items conformes servis dans le pire ordre restent vingt
   items conformes.
6. **La constance des deux ecritures instant/duree** (`DUR-CE1-3`) : rien ne la verifie, et c'est le
   defaut precis que j'ai trouve dans la matiere premiere du CM1.
7. **Que l'indice enseigne un chemin et pas une regle fausse** - `GRA-15` au CP m'a appris qu'un
   indice peut etre exact sur l'item et faux comme regle.

---

## 10. Ce que je route, et a qui

| Fait | A qui | Pourquoi ce n'est pas a moi |
|---|---|---|
| Les trente conditions `TAB-CE1-1` a `DUR-CE1-6` sont automatisables, avec les tables et les familles de ce document comme specification | `theo-archi`, via `sofia-sm` | Je dis la propriete ; la forme du test et le mecanisme lui appartiennent. Leur population est vide aujourd'hui, et `ADR-0051` a une doctrine sur ce cas qui est la sienne |
| **`MAT-CE1-11` demande un rendu de cadran** pour sa moitie *lire l'heure*, et **`MAT-CE1-10` un rendu de balance ou de recipient gradue** pour son verbe *mesurer*. Deux manques de **materiau**, pas de mecanique - contrairement a `DEN-2`, ou c'est la mecanique qui manque | `theo-archi` | Je nomme la nature du manque pour qu'il ne confonde pas les deux ; le cout est a lui |
| **Trois lignes plafonnees ou etroites**, avec leur mention : `MAT-CE1-10` et `MAT-CE1-11` au mieux `en cours` ; la moitie *mois* de `MAT-CE1-11` fermee par `QLM-CP-13` | `camille-po` | Elle ecrit la matrice et le backlog ; je produis les motifs |
| **Une story a part, et je m'engage a ne pas la reclamer comme un trou** : l'horaire d'arrivee au CE1 (instant de depart et duree donnes, arrivee a trouver), hors de `MAT-CE1-12` par son intitule | `camille-po` | Meme geste qu'a s13 section 3.2 pour l'heure de depart au CM1 |
| **Une reference fausse dans mon arbitrage de s39**, section 2, sur ce que la matrice dit de `MAT-CE1-11` (section 0.1) | moi-meme, fait ici | C'est mon document ; une correction s'ecrit chez son auteur |
| **`ce1-longueurs.yaml` porte des comparaisons chiffrees qui touchent aussi `MAT-CE1-02`** (section 6). Observation neuve et datee, **aucun Done rouvert** | `camille-po`, via `sofia-sm` | Elle arbitre le double-compte de couverture entre deux lignes ; moi je constate |
| Les trente identifiants ci-dessous sont alloues dans `squad/decisions/REGISTRE-identifiants.md`, section 4, **dans le meme commit que ce document** | moi-meme, fait | `PAR-5`, `ADR-0085` clause 2, `ADR-0100` |

**Les cinq lignes sont arbitrees, donc engageables** : `theo-archi` peut rendre un avis de
faisabilite sur chacune. Aucune n'aura de Done pedagogique avant que je n'aie joue le paquet livre,
exactement comme pour les additions a s37 et les quatre pieces de `MAT-CE1-03` a s38.

---

## 11. Les trente identifiants, et la forme que je n'ai pas inventee

**La forme se copie, elle ne s'invente pas.** Les trente identifiants portent **trois segments**,
`PREFIXE-CE1-<n>`, la forme exacte d'`ADD-CE1-1`, `SOU-CE1-2`, `CDZ-CE1-1`, `MOI-CE1-2`, `MES-CE1-3`,
`MON-CE1-4` et `SOL-CE1-1`, tous deja alloues en section 4 du registre. Aucun ne porte de marqueur de
reserve : ce sont des conditions, pas des reserves. Je ne reprends pas la forme a cinq segments que
j'ai employee a s42 et que `ADR-0100` a disqualifiee.

| Ligne servie | Identifiants |
|---|---|
| `MAT-CE1-07` | `TAB-CE1-1`, `TAB-CE1-2`, `TAB-CE1-3`, `TAB-CE1-4`, `TAB-CE1-5`, `TAB-CE1-6` |
| `MAT-CE1-08` | `PRO-CE1-1` a `PRO-CE1-7` |
| `MAT-CE1-10` | `MAS-CE1-1` a `MAS-CE1-6` |
| `MAT-CE1-11` | `CAL-CE1-1` a `CAL-CE1-5` |
| `MAT-CE1-12` | `DUR-CE1-1` a `DUR-CE1-6` |

**Les cinq prefixes sont neufs**, verifies contre la section 4 du registre avant d'etre ecrits :
`TAB`, `PRO`, `MAS`, `CAL` et `DUR` n'y figuraient a aucune forme. `HOR` y figure deja (`HOR-1` a
`HOR-8`, palier 1 visuel) et **je ne l'ai pas repris** pour l'heure, precisement pour qu'aucune
lecture ne les confonde ; j'ai pris `CAL`.

**Le compte, lu sur l'outil et non de memoire - et ce qu'il n'est pas.** La ligne d'etat courant du
registre passe de **712 / 115 a 742 / 120**, et ce chiffre est celui que
`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` rend sur l'arbre de ce commit, jamais un
chiffre que je recalcule depuis le precedent. **Je corrige au passage un constat que j'avais moi-meme
mal pose en ecrivant ce paragraphe** : je m'appretais a ecrire que les deux autres instances
allouaient *"sur des branches soeurs que je ne peux pas lire"*, la formule de mon arbitrage de s36.
C'est faux ici - **elles ecrivent dans ce meme fichier, sur le meme arbre de travail que moi**, et je
l'ai vu en lisant le diff avant de commiter. Consequence que j'en tire : **mon commit ne porte que
mes trente allocations et mon seul document**, et pas les leurs - une allocation qui pointerait vers
un document que je ne commite pas ferait rougir la clause 3 sur un clone de mon commit.

**Ce que le garde-fou rend sur cet arbre, recopie tel quel et sans le lisser** : 32 defauts, tous de
la clause 2, **tous sur `ARBITRAGE-PREALABLE-s43-mathematiques-ce1-lot-1.md`** - le document du lot
voisin, present sur l'arbre et non encore commite, dont les 32 identifiants ne sont pas alloues par
la version du registre que je commite. **Aucun defaut ne nomme mon document, et aucun ne porte sur le
compte courant** : mes trente allocations sont completes et mon chiffre est celui de ma table. Ces 32
rouges se fermeront au commit du lot 1, qui portera ses 32 lignes de registre avec son document.

---

## 12. Recapitulatif des cinq verdicts

| Ligne | Intitule abrege | Verdict | Plafond de statut atteignable |
|---|---|---|---|
| `MAT-CE1-07` | Tables de 2, 5, 10, puis 3 et 4 | **ACCEPTE SOUS CONDITIONS** `TAB-CE1-1` a `TAB-CE1-6` | `couvert-valide` |
| `MAT-CE1-08` | Problemes a une ou deux etapes (+, -, ×) | **ACCEPTE SOUS CONDITIONS** `PRO-CE1-1` a `PRO-CE1-7` | `couvert-valide` |
| `MAT-CE1-10` | Masses et contenances (g, kg, L) | **COUVERTURE PARTIELLE** - *comparer* et *utiliser g/kg/L* acceptes sous `MAS-CE1-1` a `MAS-CE1-6` ; ***mesurer* refuse par du texte seul** | `en cours` |
| `MAT-CE1-11` | Lire l'heure ; se reperer dans le calendrier | **COUVERTURE PARTIELLE** - *date* acceptee sous `CAL-CE1-1` a `CAL-CE1-5` ; ***lire l'heure* refuse par du texte seul** ; ***mois* refuse, deja ferme par `QLM-CP-13`** | `en cours` |
| `MAT-CE1-12` | Duree simple en heures ou demi-heures | **ACCEPTE SOUS CONDITIONS** `DUR-CE1-1` a `DUR-CE1-6` | `couvert-valide` |
