# s57 - lecture avant `verify` du premier jet de `ce1-qlm-familles-d-aliments` (`S57-BR-QLM-CE1-04`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 10:40:21 (UTC+01:00), ecriture 11:01:09, lues par `date` ; fin 11:09:09, en section 9.
- **Arbre** : branche `s57-p5-nadia-lecture04`, base `round-two` a `2485dc73`, lue par `git rev-parse`.
- **Commit lu** : `a9b1480a` (`[MARC]`, 2026-10-27 10:33:57 +0100), premier jet de `marc-dev`, fusionne dans `round-two` par
  `2485dc73`. Sa base ne contient ni mon `c9405891` (trois points, `R4`) ni le `659b218e` de `theo-archi` (dernier ajustement,
  `G8`) : lu par `git merge-base --is-ancestor`. Ce qui en suit est en section 6, ligne `T1`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran, le paquet n'est pas au catalogue (`QC3`). J'ai joue chaque item sur le papier,
  comme un enfant de CE1, y compris en me trompant. **Je n'ai pas lu la page de l'Alimentarium** : cette session n'a pas d'acces au
  reseau. Ce que j'en dis vient de la lecture relayee par l'agent lanceur (point `I1`) et de l'en-tete du paquet ; je ne cite pas
  comme lu ce que je n'ai pas lu (section 1).
- **Lu** : `ce1-qlm-familles-d-aliments.yaml` (12 items) ; `PaquetCe1QlmFamillesDAlimentsTest.java` ; le jeu fautif
  `contreexemple/PaquetCe1QlmFamillesDAlimentsTest/contenu/aliments-fautifs.yaml` (30 sondes) et `exclus.yaml` (14 sondes) ; l'entree
  de rang du paquet ; story `S57-BR-QLM-CE1-04` (criteres 1 a 11 et leurs trois blocs de corrections) ; mes quatre arbitrages de
  `-04` ; `FAL-CE1-1`, `FAL-CE1-2`, `QMT-CE1-1`, `NRC-CE1-1`, `TRV-CE1-2`, `-5`, `-6` dans leurs mots
  (`ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`, 2 et 3.4) ; les avis de gardes de `theo-archi` sur
  `-04` (gardes, 2.1 a 2.3 et 6 ; ajustements, 2 et 3 ; dernier ajustement, 3 et 8) ; `DEC-0120` A.272, A.273 ; mes lectures de
  `-01`, `-02`, `-03` ; les voisins `cp-qlm-hygiene` et `cm1-sciences-corps`.
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) : mots par la regle de `PaquetLu.mots`, comparaison stricte de
    longueur de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:199`, familles de chaque menu par la liste
    de l'en-tete, sur le paquet puis sur une copie portant mes corrections ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmFamillesDAlimentsTest
    -Dsurefire.failIfNoSpecifiedTests=false`, JDK 21.0.10 (propriete `java.version` du rapport) : **42 tests, 0 echec, 0 erreur**.
    Perimetre : ce seul test, module `adapter-content` et ses dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **10 justes, 2 a corriger, 0 refuse en l'etat** (section 2) |
| Autocontrole A.273 | **a** (indice qui donne la reponse) : aucun item a corriger. **b** (distracteur ecarte sans la competence) : 2 items de menu (8, 11). **c** (source circulaire) : aucune ; mais quatre lignes de source affirment ce que la page, selon la lecture relayee, ne dit pas (section 1). Le premier jet est plus propre que ceux de `-01`, `-02`, `-03` (5, 6 et 8 items a corriger) |
| Proprietes que je garde seule | `FER-3` : **tenue** ; `QMT-CE1-2` : **familles exactes**, lignes de source a recrire ; `CYC-1` : **tenu** ; `NRC-CE1-1` au-dela du texte : **tenu** ; `BIA-4`, *mauvais* et `TRV-CE1-6` dans leurs paraphrases : **tenus** (section 1) |
| `I1` | **"range par le redacteur" suffit**, a une condition que l'aliment remplit ou non ; les 19 la remplissent (section 3) |
| `B1` | **mon constat du 2026-10-26 ne s'applique pas** ; le compte est 2 items sur 4, pas 3 (section 4) |
| Reouverture d'une formulation | **aucune** (section 7) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 si mes corrections appellent une seconde lecture (section 8) |

---

## 1. Ce que j'ai verifie avant les items

- **`FER-1`, cinq familles en bonne reponse de classement** : feculents (items 1, 7), produits laitiers (3, 10), fruits et legumes (4),
  produits sucres (6), viande, poisson et oeufs (9, 12). Mes corrections n'en retirent aucune. **Tenu.**
- **`FER-3`, deux operations (`QC9`, je suis la seule garde)** : classement 8 items, menu 4. En classement, l'enfant range **un**
  aliment. En menu, il range neuf ou douze aliments, compte les familles de chaque menu et compare trois comptes ; aucun item de menu
  ne se resout par un seul classement, et aucun item de classement ne demande de comparer. **Tenue sur les 12 items**, et sur les deux
  items corriges.
- **`QMT-CE1-2`, exactitude de chaque famille (`QC9`, je suis la seule garde)** : pomme de terre, pates, riz, pain : feculents ;
  yaourt, fromage, lait : produits laitiers ; poulet, saumon, poisson, oeufs : viande, poisson et oeufs ; pomme, banane, poire, carottes,
  courgettes : fruits et legumes ; gateau, confiture, chocolat : produits sucres. **Les dix-neuf sont exactes au CE1**, selon la
  classification enseignee a l'ecole. Deux que j'ai pesees : le **gateau** est fait de farine, mais aucune classification ne le range
  parmi les feculents (sa seconde famille possible, les matieres grasses, n'est pas une famille de la ligne) ; la **confiture** est
  faite de fruits, mais elle se range dans les produits sucres, et dans les deux menus qui la portent (items 2, 11 avant correction),
  la compter en fruits et legumes ne change le nombre de familles d'aucun menu [calcul par script].
- **La source, ligne par ligne.** Ce que la page dit, selon la lecture relayee : sept groupes (boissons, sucreries, produits
  laitiers, fruits et legumes, matieres grasses, cereales et feculents, viandes), et, parmi les aliments de la liste, *pommes de terre*,
  *oeufs*, *lait*, *fromages* ; elle cite aussi *sucre*, qui n'est pas dans la liste. L'en-tete (`:47-53`, `:55-72`) dit davantage :
  il fait nommer par la page *les gateaux*, *la confiture*, *le chocolat*, *les poissons*, et ecrit pour d'autres lignes des mots de la
  page (*produits cerealiers*, *laitages*, *les viandes*, *les poissons*) que la lecture relayee ne confirme pas. **Ce n'est pas une
  source circulaire** (defaut **c**) : la page est hors du depot et porte les cinq familles. **C'est une ligne de source qui affirme ce
  que je ne peux pas verifier**, la meme faute que la ligne du sanglier de `-02`. Correction en section 5, point 3 : n'ecrire *nomme*
  que pour les quatre aliments que la lecture relayee confirme. **Les familles ne changent pas** : leur exactitude ne depend pas de ces
  mots (section 3).
- **Liste fermee** : 19 aliments, chacun avec sa famille et sa source ; tout aliment d'un enonce, d'un menu ou d'un indice y figure
  (verifie a la main sur les 12 indices, que le test ne lit pas) ; aucun des treize exclus, ni dans la liste ni dans un champ.
  **Tenue.**
- **`CYC-1` (`cm1-sciences-corps`, je suis la seule garde)** : le CM1 dit *"des aliments varies"*, le calcium *"contenu dans le lait
  et dans le fromage"*, les fibres *"des fruits, des legumes et des cereales completes"*, le fer de *"la viande rouge"*. Aucun item ne
  range le fromage, le lait, un fruit, un legume ou une viande ailleurs. Les lentilles du CM1 sont exclues ici et ne sont pas
  contredites. **Tenu.**
- **`NRC-CE1-1` au-dela du texte (je suis la seule garde)** : `cp-qlm-hygiene` apprend *"manger des aliments varies"*, *"des fruits
  et des legumes"*, *"pas trop de sucreries"*. J'ai joue chaque item de menu avec ces trois regles seules :
  - *"des aliments differents"* : les trois menus d'un item ont tous des aliments differents ; elle ne distingue rien ;
  - *"le plus de fruits et legumes"* : item 5, elle choisit *pates, courgettes, banane, poire*, qui est faux ; items 2 et 8, plusieurs
    menus en ont autant ; item 11, aucun menu n'en a ;
  - *"le moins de sucreries"* : item 2, elle ecarte un distracteur et laisse deux menus ; item 11 avant correction, elle choisit
    *lait, poulet, oeufs*, qui est faux.
  Aucune regle du CP ne donne la bonne reponse : il faut ranger chaque aliment dans sa famille. En classement, aucun paquet de CP ne
  range un aliment dans une famille (`cp-qlm-matiere` classe des objets). **Tenu.**
- **`BIA-4` dans ses paraphrases, *mauvais* dans les siennes, `TRV-CE1-6` (je suis la seule garde)** : aucun item ne demande a
  l'enfant ce qu'il mange, aime ou prefere (ni *"te plait"*, ni *"choisirais-tu"*) ; aucun menu n'est dit *moins bon*, *a eviter*,
  *pas sain* ; aucun aliment n'est defendu ; aucun foyer, aucun logement. *La cantine* et *le centre de loisirs* sont des lieux de
  l'ecole et du temps periscolaire, pas des lieux de vie ; *Sacha*, seul prenom, est epicene. **Tenus.**
- **`QMT-CE1-1`** : aucun des quinze mots exclus. *Feculents* et *produits laitiers* sont expliques par des aliments dans les indices
  des items ou ils sont la bonne reponse (1, 7 ; 3, 10), jamais par une definition. Reponse a `I7` en section 3.
- **Lisibilite CE1, `LON-CE1-1`, `-2`, `-5`** : enonces de 7 a 12 mots, une ou deux phrases, la derniere est la question ; familles
  de 1 a 4 mots ; menus de 3 ou 4 mots ; indices de 3 a 8 mots. *Se range*, *appartient*, *varie*, *centre de loisirs* sont des mots
  de l'ecole. **Une reserve de jeu, pas une correction** : trois menus de quatre aliments font douze aliments a ranger dans un seul
  item ; je regarderai a l'ecran si un enfant de CE1 tient ce compte sans abandonner.
- **`TRV-CE1-2`, bonne reponse strictement la plus longue** : 3 items sur 12 (9, 11, 12), mesure par script, comme `marc-dev` ;
  **2 sur 12 apres mes corrections** (9, 12). Strictement la plus courte : 4 (1, 5, 7, 8), puis 3 (1, 5, 7). Aucun texte ne pose de
  limite a la plus courte sur `-04`, et je n'en pose pas.
- **Rang** : 12 items, 36 propositions, toutes textuelles. Mes corrections ne changent ni le nombre d'items ni le nombre de
  propositions : l'entree de rang garde son motif (`QC10`).
- **Accents, orthographe, ponctuation** : au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`). Je n'ai rien vu qui manque.

## 2. Verdict par item

Numerotation : ordre du fichier. *Juste* : rien a changer. *A corriger* : correction demandee avant `verify` ; restee telle quelle a
l'audit, ce serait une reserve. Aucun item n'est refuse en l'etat : aucun fait n'est faux, aucun item n'a deux reponses defendables.
Pour la tache menu, les familles sont ecrites F (feculents), PL (produits laitiers), FL (fruits et legumes), V (viande, poisson et
oeufs), PS (produits sucres).

| # | Ligne | Tache, sujet | Bonne reponse ; distracteurs | Indice | Verdict | Motif |
|---|---|---|---|---|---|---|
| 1 | `:94` | classement, pomme de terre | *féculents* ; *fruits et légumes*, *produits laitiers* | *Pense aux pâtes et au riz.* | **juste** | Le piege de la ligne, servi : *fruits et legumes* est le distracteur que l'enfant choisit vraiment. Ecarter *produits laitiers* est la competence (mon 1.6). Indice : deux autres aliments de la famille, la regle 7 |
| 2 | `:99` | menu, cantine | *riz, poisson, carottes, yaourt* (F, V, FL, PL : 4) ; *pâtes, pain, poulet, pomme* (F, V, FL : 3), *fromage, banane, chocolat, confiture* (PL, FL, PS : 3) | *Compte les familles de chaque menu.* | **juste** | Une seule reponse, 4 contre 3 et 3. Regle 3 tenue : chaque famille de l'attendu est dans un autre menu, et la seule famille commune aux deux autres (FL) est dans l'attendu. Indice : la methode |
| 3 | `:105` | classement, yaourt | *produits laitiers* ; *féculents*, *fruits et légumes* | *Compare-le avec le fromage.* | **juste** | Une comparaison avec un autre aliment de la famille, par un pronom : exactement la regle 7 et l'exemple de `X3`. L'enfant doit savoir ou se range le fromage (doute de `marc-dev`, section 3) |
| 4 | `:111` | classement, carottes | *fruits et légumes* ; *féculents*, *produits laitiers* | *Pense aux courgettes et à la poire.* | **juste** | L'item le plus facile, comme je l'avais prevu (contradictions `-04`/`-05`, 2) : c'est le libelle qui le veut. Indice : un legume et un fruit, la regle 7 |
| 5 | `:116` | menu, centre de loisirs | *riz, pain, fromage, pomme* (F, PL, FL : 3) ; *pâtes, courgettes, banane, poire* (F, FL : 2), *yaourt, lait, poulet, saumon* (PL, V : 2) | *Dans chaque menu, cherche les familles différentes.* | **juste** | Une seule reponse. Le distracteur qui a le plus de fruits et legumes est faux : l'enfant doit compter, la regle du CP le trompe. Regle 3 tenue |
| 6 | `:122` | classement, gateau | *produits sucrés* ; *féculents*, *fruits et légumes* | *Pense à la confiture et au chocolat.* | **juste** | Exact (section 1). Seul item qui sert *produits sucres* en bonne reponse (`FER-1`). Indice : deux autres aliments de la famille |
| 7 | `:128` | classement, riz | *féculents* ; *produits sucrés*, *fruits et légumes* | *Compare-le avec les pâtes et le pain.* | **juste** | Indice : comparaison avec deux aliments de la famille. Explique *feculents* par des exemples (`QMT-CE1-1`) |
| 8 | `:133` | menu, cantine | *pain, poulet, œufs, poire* (F, V, FL : 3) ; *lait, yaourt, fromage, banane* (PL, FL : 2), *riz, pâtes, saumon, poisson* (F, V : 2) | *Range chaque aliment dans sa famille, puis compare.* | **a corriger** | Fait sur, une seule reponse. **Le second distracteur nomme *saumon* et *poisson*** : le saumon est un poisson. Un enfant de CE1 lit le meme aliment deux fois, ou se demande quel autre poisson on lui sert ; il s'arrete sur une bizarrerie du texte et non sur les familles (2.1) |
| 9 | `:139` | classement, poulet | *viande, poisson et œufs* ; *féculents*, *produits laitiers* | *Compare-le avec le saumon.* | **juste** | Indice : comparaison, regle 7. *Viande* se lit dans la famille ; savoir que le poulet est une viande est un savoir sur l'aliment, la regle 6 ne porte que sur les mots de l'enonce |
| 10 | `:145` | classement, fromage | *produits laitiers* ; *féculents*, *viande, poisson et œufs* | *Pense au yaourt.* | **juste** | Distracteurs plausibles. Indice : un autre aliment de la famille |
| 11 | `:150` | menu, Sacha a la cantine | *riz, fromage, confiture* (F, PL, PS : 3) ; *pâtes, pain, chocolat* (F, PS : 2), *lait, poulet, œufs* (PL, V : 2) | *Pour chaque menu, compte les familles différentes.* | **a corriger** | Fait sur, une seule reponse, regle 3 tenue. **Deux motifs, 2.2** : sur les quatre items de menu, *riz* est dans le menu attendu trois fois sur quatre et *pates* n'y est jamais (0 sur 4 apparitions) ; et le menu attendu, *riz, fromage, confiture*, n'est pas un repas qu'un enfant reconnait, alors que l'item lui apprend que c'est le plus varie |
| 12 | `:156` | classement, saumon | *viande, poisson et œufs* ; *féculents*, *produits sucrés* | *Pense au poulet.* | **juste** | Facile : *poisson* est dans la bonne reponse. C'est la regle 6 telle que je l'ai voulue (les mots de l'enonce, pas le sens). Indice : un autre aliment de la famille |

### 2.1 Item 8 : un aliment et son nom general dans le meme menu

*Riz, pâtes, saumon, poisson* compte bien deux familles, et la regle 3 tient. Mais un menu qui nomme *saumon* puis *poisson* ne se lit
pas comme un menu : l'enfant cherche ce que *poisson* ajoute. Ce n'est pas un distracteur plausible au sens de ma methode (etape 4),
et ce n'est pas une regle neuve. Le remplacement garde les deux familles et la taille : *œufs* au lieu de *poisson* (*riz, pâtes,
saumon, œufs*, F et V, 2 familles). Recompte par script : 3 contre 2 et 2 ; chaque famille de l'attendu (F, V, FL) est dans un autre
menu ; les deux distracteurs n'ont aucune famille commune. *Œufs* est aussi dans le menu attendu : un aliment partage oblige a
comparer, il ne designe rien.

### 2.2 Item 11 : un aliment qui designe le menu attendu, et un menu attendu qui n'est pas un repas

**Le signe.** Compte par script sur les quatre items de menu : *riz* est dans le menu attendu des items 2, 5 et 11, et dans un seul
distracteur (item 8) ; *pates* est dans quatre distracteurs et dans aucun menu attendu. Apres deux ou trois manches, *"le menu avec
du riz"* trouve la reponse sans ranger un seul aliment. C'est le defaut **b** d'A.273, sous la forme que j'ai deja nommee a `-06`
(*fil de fer*, *"le signe du bon groupe"*) : un mot qui revient et designe la reponse.

**Le repas.** *Riz, fromage, confiture* a bien trois familles. Mais aucun enfant de CE1 n'a mange ce repas a la cantine, et l'item
lui apprend que c'est *le plus varie* ; un enfant qui sait ce qu'est un repas hesite sur la bonne reponse pour une raison qui n'a rien a
voir avec les familles.

**Le remplacement** : *poulet, pâtes, yaourt* (V, F, PL : 3). Un repas de cantine. Recompte par script : 3 contre 2 (*pâtes, pain,
chocolat*, F et PS) et 2 (*lait, poulet, œufs*, PL et V) ; chaque famille de l'attendu est dans un autre menu (F dans le premier, V et
PL dans le second) ; les deux distracteurs n'ont aucune famille commune ; aucun menu n'est fait seulement de produits sucres. Le menu
attendu partage *pâtes* avec l'un et *poulet* avec l'autre : l'enfant doit compter. Apres correction, *riz* est dans le menu attendu
2 fois sur 3 apparitions, *pates* 1 fois sur 5 : plus aucun aliment n'est toujours d'un cote. La bonne reponse n'est plus la plus
longue (21 caracteres, comme le premier distracteur).

Ce que je ne demande pas : que chaque distracteur soit un repas equilibre. Un menu fait de trois produits laitiers et d'une banane est
le distracteur ordinaire de cet exercice, et l'ecarter, c'est voir qu'il n'est pas varie : c'est la competence.

## 3. Les points interpretes par `marc-dev`

| Code | Interpretation | Reponse | Motif |
|---|---|---|---|
| `I1` | La page de l'Alimentarium nomme sept groupes et ne nomme ni les pates, ni le riz, ni le pain, ni le yaourt, ni le poulet, ni le saumon, ni la pomme ; `FAL-CE1-1` exige-t-elle que la source nomme chaque aliment ? | **Non. "Range par le redacteur" suffit, a une condition. Ni autre source, ni liste plus courte** | Ma regle 5 demande *"un document nomme hors du depot, qui porte les cinq familles"*, et *"chaque aliment avec sa famille et sa source"* : la source d'un aliment est le groupe de la page ou il se range. Je ne releve pas cette barre apres coup. **La condition**, qui est le motif meme de `FAL-CE1-1` (*"deux familles sont defendables selon la source"*) : l'aliment appartient a la categorie que le groupe nomme (les pates, le riz et le pain sont des cereales ; le yaourt est un produit laitier ; le saumon, un poisson ; la pomme, un fruit), et aucune classification ne le range dans une autre des cinq familles. Un aliment qui ne la remplit pas n'est pas range par le redacteur : il est exclu. **Les 19 la remplissent** (section 1 : le gateau et la confiture compris). Ce que la ligne ne doit pas faire : dire *nomme* pour un aliment que la page ne nomme pas (section 5, point 3). L'exactitude de chaque rangement reste a ma lecture (`QC9`) |
| `I2` | Les cinq familles sont dans les sept groupes, *sucreries* lu *produits sucres*, *viandes* incluant poissons et oeufs | **confirmee** | *Sucreries* et *produits sucres* designent la meme famille. *Viandes* pour *viande, poisson et oeufs* est la lecture du redacteur ; la page nomme les oeufs (lecture relayee). L'en-tete le dit comme une lecture, pas comme un mot de la page (section 5, point 2) |
| `I3` | Triples de classement a intrus par la forme ecartes ; *feculents* dans les 8 triples | **confirmee** | Une seule famille d'un mot (*feculents*), deux en *"produits ..."*, deux en liste avec *"et"* : chaque triple prend une forme de chaque sorte, et aucune proposition ne se detache par sa forme. *Feculents* en est la consequence. Il est la bonne reponse de 2 items sur 8 ; ce n'est pas *rien ne change* de `-05`, qui n'etait jamais juste. Et c'est le mot le plus difficile de la ligne que l'enfant rencontre a chaque item : il doit decider chaque fois si l'aliment en est un. Je regarderai a l'ecran si un enfant l'ecarte sans lire |
| `I4` | Un menu est lu des qu'il a au moins deux elements separes par des virgules, sans verifier qu'ils sont dans la liste (ecart a la lettre de `T04-1`) | **confirmee pour le contenu ; la forme a `theo-archi`** | Ce que je veux est tenu : un element de menu absent de la liste est refuse par `verifierLesAlimentsDesItems` (`PaquetCe1QlmFamillesDAlimentsTest.java:388-395`), sonde 05 du jeu fautif. L'ecart ne change que le message qui refuse. Ligne `T2` en section 6 |
| `I7` | *Feculents* est dans les 8 items de classement, explique par des aliments dans 2 indices seulement | **confirmee** | `QMT-CE1-1` : *"Un mot admis difficile (feculents, littoral) est explique par l'indice avec des exemples, jamais par une definition."* L'indice sert a trouver la bonne reponse. Dans un item ou *feculents* est un distracteur, l'expliquer dans l'indice aiderait a l'ecarter : une demi-reponse, que mes regles refusent a `-10`. Il est explique la ou il est la reponse (items 1, 7), et *produits laitiers* aussi (3, 10). Tenu |
| `I8` | Un prenom, *Sacha* ; menus de la cantine ou du centre de loisirs | **confirmee** | Epicene, aucun role. Ni la cantine ni le centre de loisirs ne supposent un foyer ou un logement (`TRV-CE1-6`, regle 4) |
| Doute, indices | Items de classement dont l'indice nomme un aliment de la meme famille (*"Compare-le avec le fromage."*, *"Pense au yaourt."*), items 3 et 10 du fichier | **justes, les huit** | Les huit indices de classement font tous ainsi, et c'est ma regle 7 : *"il donne d'autres aliments de la liste en exemple, ou une comparaison"*. L'enfant doit encore savoir ou se range le fromage : l'indice deplace la question vers un aliment plus familier, il ne donne pas la famille. Aucun ne nomme l'aliment de l'enonce, aucun ne porte un mot de la famille attendue, aucun ne la definit. Les paires reciproques (yaourt et fromage, poulet et saumon) ne sont pas un defaut : elles disent la meme chose dans les deux sens |
| `B1` | Le menu attendu est l'un des deux seuls sans famille repetee, dans 3 items de menu sur 4 | **ni refus ni correction ; le compte est 2, pas 3** | Section 4 |
| `TRV-CE1-2` | Strictement la plus longue dans 3 items sur 12 (limite 6), strictement la plus courte dans 4 | **confirme**, mesure par script | 2 et 3 apres mes corrections. Aucune limite n'est ecrite pour la plus courte a `-04` ; dans la tache menu, elle l'est dans 2 items sur 4 avant correction, 1 apres. Je n'en deduis rien |

## 4. `B1` : la structure de l'intrus, mon constat du 2026-10-26

**Correction du compte d'abord** [calcul par script] : le menu attendu est le **seul** des trois sans famille repetee dans **2** items
sur 4 (2 et 11) ; dans les items 5 et 8, les trois menus repetent une famille (*riz, pain* ; *poulet, œufs*). Apres ma correction de
l'item 11, c'est 1 item sur 4 (item 2).

**Mon constat ne s'applique pas.** A `-02`, les deux distracteurs partageaient une categorie que l'enfant voyait sans rien savoir de
l'animal : la propriete commune contournait la competence. Ici, **"aucune famille repetee" est la competence elle-meme** : trois menus
de meme taille, celui qui a le plus de familles est celui qui en repete le moins. Pour voir une repetition, l'enfant doit ranger chaque
aliment. Et ma regle 3 ferme deja le seul contournement par les familles (une famille commune aux deux distracteurs et absente de
l'attendu). **Rien a demander.**

Ce que la tache menu avait de contournable n'etait pas la structure, c'etait un mot : *riz* (2.2).

## 5. Ce que je demande a `marc-dev`

Texte exact. Rien d'autre ne change : ni les enonces, ni les autres propositions, ni les indices, ni les declarations des items. Les
lignes de l'en-tete restent sans accent, comme le reste de l'en-tete. Aucun texte neuf ne porte d'espace avant `?` ou `:`.

**Dans `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-familles-d-aliments.yaml`** :

| Point | Item, ligne | Texte actuel | Texte a ecrire |
|---|---|---|---|
| 1 | 8, `:135` | `distracteurs: ["lait, yaourt, fromage, banane", "riz, pâtes, saumon, poisson"]` | `distracteurs: ["lait, yaourt, fromage, banane", "riz, pâtes, saumon, œufs"]` |
| 2 | 11, `:151` | `bonneReponse: "riz, fromage, confiture"` | `bonneReponse: "poulet, pâtes, yaourt"` |

3. **En-tete, la source.** Remplacer les lignes `:45` a `:53` :

```
# gratin) n'y figure ni n'apparait dans un champ de l'item. Source unique, hors du depot : page
# "Les groupes alimentaires" de l'Alimentarium (fondation, Vevey, Suisse), lue par le redacteur le
# 2026-10-27. Elle nomme sept groupes, dont les cinq de la ligne : fruits et legumes ; cereales et
# feculents (les produits cerealiers, les legumineuses et "les pommes de terre") ; produits laitiers
# (le lait, les laitages, les fromages) ; viandes (les viandes, les poissons et les oeufs) ;
# sucreries (le sucre, la confiture, le chocolat, les gateaux). Chaque ligne dit si l'aliment est
# nomme par la page ou range par le redacteur dans un groupe qu'elle nomme : pates, riz, pain,
# poulet, saumon, poisson, pomme, banane, poire, carottes, courgettes et yaourt ne sont pas nommes
# un a un, le redacteur les range dans le groupe de leur categorie (nadia-prof les confronte, QC9).
```

   par :

```
# gratin) n'y figure ni n'apparait dans un champ de l'item. Source unique, hors du depot : page
# "Les groupes alimentaires" de l'Alimentarium (fondation, Vevey, Suisse,
# https://www.alimentarium.org/fr/node/5477), lue par le redacteur le 2026-10-27. Elle nomme sept
# groupes : boissons, sucreries, produits laitiers, fruits et legumes, matieres grasses, cereales et
# feculents, viandes. Les cinq familles de la ligne en sont cinq, lues par le redacteur : sucreries
# pour produits sucres, viandes pour viande, poisson et oeufs. Des aliments de la liste, la page ne
# nomme que les pommes de terre, les oeufs, le lait et les fromages ; chaque autre aliment est range
# par le redacteur dans le groupe de sa categorie, et sa ligne le dit (nadia-prof les confronte, QC9).
```

   puis, dans les lignes d'aliment, remplacer la parenthese finale de dix lignes, le reste de chaque ligne ne changeant pas :

| Ligne | Aliment | Parenthese actuelle | Parenthese a ecrire |
|---|---|---|---|
| `:55` | pates | `(produits cerealiers, range par le redacteur)` | `(range par le redacteur)` |
| `:56` | riz | `(produits cerealiers, range par le redacteur)` | `(range par le redacteur)` |
| `:57` | pain | `(produits cerealiers, range par le redacteur)` | `(range par le redacteur)` |
| `:58` | yaourt | `(les laitages, range par le redacteur)` | `(range par le redacteur)` |
| `:61` | poulet | `(les viandes, range par le redacteur)` | `(range par le redacteur)` |
| `:62` | saumon | `(les poissons, range par le redacteur)` | `(range par le redacteur)` |
| `:63` | poisson | `(les poissons, nommes)` | `(range par le redacteur)` |
| `:70` | gateau | `(les gateaux, nommes)` | `(range par le redacteur)` |
| `:71` | confiture | `(la confiture, nommee)` | `(range par le redacteur)` |
| `:72` | chocolat | `(le chocolat, nomme)` | `(range par le redacteur)` |

   Les lignes `:54` (pomme de terre), `:59` (fromage), `:60` (lait), `:64` (oeufs) et `:65` a `:69` (fruits et legumes) ne changent
   pas. **Une seule exception** : si `marc-dev`, en relisant la page, y lit un de ces mots, il garde *nomme* sur cette ligne et ecrit
   dans son rapport de correction le mot tel que la page l'ecrit. Il n'ecrit *nomme* sur la foi de sa memoire pour aucune ligne.

4. **En-tete, ligne `:27`**, remplacer :

```
#   menu : quel menu a strictement le plus de familles parmi trois menus de la cantine (4 items)
```

   par :

```
#   menu : quel menu a strictement le plus de familles parmi trois menus de la cantine ou du centre de loisirs (4 items)
```

   L'item 5 est au centre de loisirs. Le motif `TACHE_DE_L_ENTETE` lit toujours *"(4 items)"*.

5. **Rang et comptes** : 12 items, 36 propositions, toutes textuelles ; l'entree de rang garde son motif (`QC10`) ; les deux lignes
   de compte (8 et 4) ne changent pas. Aucune sonde du jeu fautif ne reprend un texte que je change.

**Recompte, sur une copie portant les points 1 et 2** [calcul par script] : items 8 et 11, 3 familles contre 2 et 2 ; regle 3 tenue
sur les deux ; propositions de 3 et 4 mots ; bonne reponse strictement la plus longue dans 2 items sur 12 (`TRV-CE1-2`). Tous les
aliments des deux propositions neuves (*riz*, *pâtes*, *saumon*, *œufs*, *poulet*, *yaourt*) sont dans la liste.

**Ce dont je reste la seule garde sur ce paquet, apres correction** : l'exactitude de chaque famille contre la classification de
l'ecole, et que chaque ligne de source dise ce que la page dit ; que classement et menu soient deux operations sur chaque item ;
qu'un indice soit une methode et ne definisse pas une famille ; la plausibilite de chaque distracteur, menu compris ; qu'aucun aliment
ne devienne le signe du menu attendu ; `NRC-CE1-1` sur le sujet et `CYC-1` ; `BIA-4`, *mauvais* et `TRV-CE1-6` dans leurs
paraphrases. **Aucune n'est couverte par un test.**

## 6. Ce qui revient a `theo-archi` (forme), une ligne par garde

| Repere | Garde | Ajustement |
|---|---|---|
| `T1` | `T04-3e` (`MOT_DE_FAMILLE`, `PaquetCe1QlmFamillesDAlimentsTest.java:138-139`) | Porter ta forme du `659b218e` (dernier ajustement, 3) : `feculentes?` et `produits?` manquent, parce que la base de `a9b1480a` precede ce commit. Sondes refusees *"... la purée féculente ?"*, *"... ce produit ?"*. La sonde du lait que je demandais (`A2`, trois points) est la sonde 20 ; celle du sucre, la 26. Aucun enonce du paquet n'est touche |
| `T2` | `T04-1`, lecture d'un menu (`elementsDuMenu`, `:216-223`) | L'ecart de `I4` : aligner sur ta lettre (chaque element est un nom de la liste) ou ecrire l'ecart dans ton avis. Pour le contenu, rien ne manque (section 3) ; le choix est a toi |
| `T3` | aucune garde neuve | Mes deux corrections (2.1, 2.2) relevent de la plausibilite et du signe d'un aliment : *non garde : `TRV-CE1-2`*, je le lis |

## 7. Cette lecture rouvre-t-elle une formulation ?

**Non.** Aucun texte de la story ni de mes quatre arbitrages n'est a reecrire. `I1` est la lecture de ma regle 5 a la lettre, et la
condition que j'y mets est le motif de `FAL-CE1-1`, ecrit des 3.4. Les corrections des items 8 et 11 appliquent ma methode (plausibilite
d'un distracteur) et le defaut **b** d'A.273, deja ecrits ; elles ne posent aucune regle pour `-04` ni pour un autre lot. Le compte de
`B1` corrige un chiffre de `marc-dev`, pas un de mes textes. Rien a reporter par `camille-po`.

## 8. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes
corrections touchent deux items et l'en-tete : si `marc-dev` les applique telles qu'ecrites, leur relecture est une seconde lecture,
**0,5 hors marge** (`DEC-0120` A.272, A.270 point 4), soit **1,5**. Aucun seuil n'est ecrit pour `-04`, et je n'en deduis aucun. Je ne
convertis pas une duree en bareme : aucune regle ne le fait.

## 9. Duree et verifications

**Duree**, mesuree par `date` : debut **10:40:21**, fin **11:09:09**, avant le commit, soit **28 min 48 s**, dont la mesure du
test du paquet et les executions du garde-fou des identifiants. Aucun seuil de duree ne s'applique a une lecture.

**Verifications avant commit**, ce fichier present dans l'arbre, base `2485dc73`. Il n'ecrit ni ne renomme aucun identifiant de
condition : `I1`, `B1`, `T1` a `T3` sont des reperes de demande, comme ceux de `marc-dev` et de `theo-archi`.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers precedents.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee, ce fichier indexe : *"Aucun chemin de poste de travail ni nom de
  compte dans les fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

## 10. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 5, points 1 a 5 |
| `theo-archi` | section 6, `T1` a `T3` |
| `sofia-sm` | le cout est en section 8 ; la seconde lecture, s'il y en a une, s'inscrit sur la ligne hors marge |
| `camille-po` | rien (section 7) |

## 11. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
