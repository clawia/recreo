# s57 - lecture avant `verify` du premier jet de `ce1-qlm-ce-que-mangent-les-animaux` (`S57-BR-QLM-CE1-02`, `P1`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-26 15:33:04 (UTC+01:00), ecriture 15:46, lues par `date` ; fin en section 9.
- **Arbre** : branche `s57-p5-nadia-lecture02`, base `round-two` a `81d203b1`, lue par `git rev-parse`.
- **Commit lu** : `ebc274b1` (`[MARC]`, 2026-10-26 14:51:28 +0100), premier jet de `marc-dev`, fusionne dans `round-two` par
  `7c2b448c`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran. Le paquet n'est pas au catalogue (`QC3` : `grep -c "ce1-qlm" catalogue.yaml`
  rend 0). J'ai joue chaque item sur le papier, comme un enfant de CE1, y compris en me trompant ; le jeu a l'ecran vient a l'audit (`P2`).
- **Lu** : `ce1-qlm-ce-que-mangent-les-animaux.yaml` (12 items) ; `PaquetCe1QlmCeQueMangentLesAnimauxTest.java` ; son jeu fautif
  `contreexemple/PaquetCe1QlmCeQueMangentLesAnimauxTest/contenu/animaux-fautifs.yaml` (12 sondes) ; l'entree de rang du paquet ; story
  `S57-BR-QLM-CE1-02` (criteres 1 a 6, `RAL-CE1-1`, `RAL-CE1-2`) et le bloc commun (`QC1` a `QC10`, `TRV-CE1-1`, `-2`, `-5`, `-6`,
  `NRC-CE1-1`, `QMT-CE1-1`, `-2`, `CYC-1`) ; `planning.md`, amendement du 2026-10-26 13:35 ; mon
  `avis-nadia-seconde-tache-ce1-qlm-02.md` ; `avis-theo-palier-5-garde-fer-3-ce1-qlm-02.md` ;
  `arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md` (ligne `-02`) ; ma lecture de `-03` (modele de forme et `S4`) ; les
  voisins `cm1-sciences-regimes-alimentaires`, `cm1-sciences-chaine-alimentaire`, `cp-qlm-cycle-de-vie`, et un `grep` de *mange* sur
  tous les paquets `cp-*` et `ce1-*`.
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite), comparaison stricte de longueur, la meme que
    `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199`, compte de mots de `PaquetLu.mots`, et la
    normalisation de `PaquetCe1QlmCeQueMangentLesAnimauxTest.normalise` recopiee, sur le paquet puis sur une copie portant mes corrections ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmCeQueMangentLesAnimauxTest`, JDK 21.0.10
    (propriete `java.version` du rapport) : **23 tests, 0 echec, 0 erreur**. Perimetre : ce seul test, module `adapter-content` et ses
    dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **6 justes, 6 a corriger, 0 refuse en l'etat** (section 2) |
| Ce qui revient le plus | **quatre indices disent la categorie ou l'aliment par le verbe de la predation** (*chasse*, *attrape*, *se coller dans sa toile*, *attraper en volant*), `TRV-CE1-5` (section 2.1) |
| Les trois proprietes que je garde seule | operation distincte : **tenue** ; aucun distracteur de B mange : **tenu** ; categorie contre source et `CYC-1` : **tenue**, la source est a recrire (section 1) |
| `S4` et les six paraphrases | **le test ne les admet pas** : ensemble ferme a trois cles. Ce n'est pas une faute de `marc-dev` : son commit (14:51:28) precede ma lecture de `-03` (14:55:18). Correction en section 5, point 9 |
| `I1` | **reformulee** : *meme type d'article* veut dire l'article partitif ou l'indefini pluriel (*du*, *de la*, *de l'*, *des*), pas le meme article exact (section 3) |
| Constat nouveau, date | dans chaque item de B, la bonne reponse est l'intrus des trois propositions ; c'est ma regle qui le produit. Ni refus ni barre durcie (section 4) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 si mes corrections appellent une seconde lecture (section 8) |

---

## 1. Ce que j'ai verifie avant les items

- **`FER-1`** : *des vegetaux* (items 1, 7), *d'autres animaux* (3, 9), *les deux* (5, 11) sont en bonne reponse de la tache A. Mes
  corrections n'en retirent aucune.
- **`FER-3` et son operation mentale (`QC9`, je suis la seule garde)** : categorie 6 items, aliment 6. Chaque animal ne sert qu'une
  fois, dans une seule tache. En A, l'enfant part de ce qu'il sait de l'animal et le generalise en une categorie. En B, il reconnait un
  aliment concret parmi trois ; s'il passe par la categorie, il doit classer les trois aliments, ce que A ne lui demande jamais. **Deux
  operations, tenue sur les 12 items.** La limite de cette lecture est en section 4.
- **Aucun distracteur de B que l'animal mange (`QC9`, je suis la seule garde)** : verifie item par item en section 2. Escargot :
  mouches, souris ; chevre : souris, mouches ; araignee : graines, feuilles ; chenille : mouches, vers de terre ; lapin : poissons,
  souris ; hirondelle : graines, feuilles. Aucun n'est mange par l'animal de l'enonce. **Tenu.** Deux sont a remplacer pour un autre
  motif, la plausibilite (2.2).
- **La categorie de chaque animal contre sa source et contre `cm1-sciences-regimes-alimentaires` (`CYC-1`, je suis la seule garde)** :
  vache, mouton, chevre, lapin, escargot, chenille *des vegetaux* ; chouette, grenouille, araignee, hirondelle *d'autres animaux* ;
  poule, sanglier *les deux*. Les douze sont exactes au CE1. Le CM1 classe le lapin, la vache et la chevre herbivores, le sanglier
  omnivore (`cm1-sciences-regimes-alimentaires`, items 1, 3, 4, 7) et fait manger le mulot a la chouette
  (`cm1-sciences-chaine-alimentaire`, *"Dans la forêt, que mange la chouette ?"*) : aucun item n'est contredit. `ce1-texte-documentaire`
  (*le lapin mange de l'herbe*, *la chenille mange des feuilles*) ne l'est pas non plus. **Tenue. La source est a recrire** :
  - **Correction de mon propre ecrit.** Le paquet cite *avis-nadia, section 1, tableau animal par animal* comme source de dix des
    douze categories. L'en-tete de la colonne de ce tableau dit *"Categorie (a sourcer, `QMT-CE1-2`)"* : je n'y donnais pas une source,
    j'y disais qu'il en fallait une. Citer ce tableau fait d'une demande de source sa propre source. Comme a `-01` et a `-03`, la source
    devient ma lecture, qui tient ici chaque categorie pour exacte : texte en section 5, point 8.
  - La ligne `cm1-regimes` du commentaire (`:40-41`) attache au sanglier *"herbes, feuilles et racines"*, qui est ce que le CM1 dit du
    lapin. C'est une ligne de source inexacte, pas un item faux : texte en section 5, point 8.
- **La liste fermee dans le commentaire** : douze animaux, un par ligne, categorie et source ; chaque item declare son animal, l'enonce
  le nomme, et le test relit les deux (`I10`). Aucun des quatre animaux exclus. **Tenue**, source a part.
- **Les quatre animaux exclus (`RAL-CE1-2`)** : ni chat, ni chien, ni renard, ni herisson dans la liste ni dans aucun champ lu ; le test
  les refuse (sondes 05 a 08) et refuse la liste qui porterait *chat*. Aucun aliment du commerce. **Tenu.**
- **Critere 4, *"l'enonce le dit"*** : les douze enonces nomment le lieu ou l'animal mange. Ma lecture de mes mots : **le lieu est un
  endroit ou l'animal trouve lui-meme sa nourriture, sans qu'on la lui donne** ; une cuisine, une cage, un clapier, une gamelle n'entrent
  pas. Les douze lieux tiennent cette lecture (detail en `I5`). Ce n'est pas une condition neuve : c'est le motif de `RAL-CE1-2`
  (*croquettes et patee rendent une seconde reponse defendable*), ecrit pour qu'on ne le deduise pas.
- **`NRC-CE1-1`** : `grep` de *mange* sur tous les paquets `cp-*` : aucun item de CP ne demande ce que mange un animal. Le seul qui en
  approche, `cp-qlm-cycle-de-vie` (*"Un chien mange sa gamelle tous les jours, que fait-il ?"*), demande une fonction du vivant, pas un
  aliment ni un regime. **Tenue.** Aucune sonde n'a de voisin de CP a comparer ici, comme je l'avais ecrit (arbitrage avant
  ouverture, ligne `NRC-CE1-1`).
- **`QMT-CE1-1`** : aucun des quinze mots exclus ; *vegetaux* est admis. Je ne le compte pas parmi les mots admis difficiles qui
  appellent des exemples dans l'indice (*feculents*, *littoral*) : c'est le mot du libelle et du programme du cycle 2 (*animal,
  vegetal*). Je regarderai en jouant si un enfant s'y arrete.
- **Lisibilite CE1, `LON-CE1-1`, `-2`, `-5`** : enonces de 6 a 10 mots, une phrase, une question ; propositions de 2 a 4 mots ;
  indices de 9 a 11 mots (8 a 11 apres correction). Aucun mot hors du niveau : *pre*, *prairie*, *potager*, *mare*, *picore* sont des
  mots de l'ecole et des albums.
- **`TRV-CE1-6`** : aucun prenom, aucun foyer, aucun objet possede. *Le jardin* et *le potager de la ferme* ne sont a personne.
- **`TRV-CE1-1`** : niveau `CE1`, aucun `texteSupport`, aucun champ a voix haute.
- **`TRV-CE1-2`, bonne reponse strictement la plus longue** : 3 items sur 12 (2, 3, 9), mesure par script ; 3 sur 12 apres mes
  corrections.
- **Rang** : entree de rang 12 items, 36 propositions, compte exact (12 fois 3). Mes corrections n'ajoutent ni ne retirent d'item :
  l'entree garde ses totaux (`QC10`).
- **Accents, orthographe, ponctuation** : au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`). Je n'ai rien vu qui manque.

## 2. Verdict par item

Numerotation : ordre du fichier, ligne de l'enonce. *Juste* : rien a changer. *A corriger* : je demande la correction avant `verify` ;
s'il restait tel quel a l'audit, ce serait une reserve. Aucun item n'est refuse en l'etat : aucun fait n'est faux, aucun item n'a deux
reponses defendables.

| # | Ligne | Tache, animal, lieu | Bonne reponse ; distracteurs | Verdict | Motif |
|---|---|---|---|---|---|
| 1 | `:79` | categorie, vache, pre | *des végétaux* ; *d'autres animaux*, *les deux* | **juste** | Fait sur (CM1 herbivore). Indice *"Imagine ce que la vache trouve autour d'elle dans le pré"* : une situation a simuler, l'enfant trouve l'herbe puis classe |
| 2 | `:85` | aliment, escargot, jardin | *des feuilles de salade* ; *des mouches*, *des souris* | **a corriger** | Fait sur. **Distracteur *des souris*** : un escargot ne mange pas une souris parce qu'elle est plus grosse que lui ; l'enfant l'ecarte par la taille, sans rien savoir de ce que mange l'escargot (2.2). Indice : le lieu a simuler, une methode |
| 3 | `:91` | categorie, chouette, foret la nuit | *d'autres animaux* ; *des végétaux*, *les deux* | **a corriger** | Fait sur (CM1 : le mulot). Indice *"Pense à ce que la chouette cherche quand elle chasse"* : *chasser*, c'est poursuivre un animal ; le verbe dit la categorie (2.1) |
| 4 | `:97` | aliment, chevre, bord du chemin | *des herbes* ; *des souris*, *des mouches* | **juste** | Fait sur (CM1 : *broute des herbes*). Les distracteurs sont de l'autre categorie, ma regle ; une chevre ne chasse pas, c'est ce que l'enfant doit savoir. Indice : simulation, une methode |
| 5 | `:103` | categorie, poule, cour de la ferme | *les deux* ; *des végétaux*, *d'autres animaux* | **juste** | Fait sur. Indice *"Pense à ce que la poule picore dans la cour"* : *picorer* ne dit pas la categorie (on picore des graines comme des vers) ; l'enfant doit savoir ce qu'elle picore |
| 6 | `:109` | aliment, araignee, toile | *des mouches* ; *des graines*, *des feuilles* | **a corriger** | Fait sur. Indice *"Imagine ce qui vient se coller dans sa toile"* : la fonction de la toile designe la reponse, comme l'arrosoir de `-03` (2.1) |
| 7 | `:115` | categorie, mouton, prairie | *des végétaux* ; *les deux*, *d'autres animaux* | **juste** | Fait sur. Indice *"sous ses pattes"* : simulation |
| 8 | `:121` | aliment, chenille, arbre | *des feuilles* ; *des mouches*, *des vers de terre* | **juste** | Fait sur, et coherent avec `-01` (la chenille, etape du papillon) et `ce1-texte-documentaire`. Indice : simulation |
| 9 | `:127` | categorie, grenouille, bord de la mare | *d'autres animaux* ; *les deux*, *des végétaux* | **a corriger** | Fait sur (`I7`). Indice *"Pense à ce que la grenouille attrape avec sa langue"* : *attraper* designe une proie, le verbe dit la categorie (2.1) |
| 10 | `:133` | aliment, lapin, potager de la ferme | *des carottes* ; *des poissons*, *des souris* | **a corriger** | Fait sur (`I3`). **Distracteur *des poissons*** : il n'y a pas de poisson dans un potager ; l'enfant l'ecarte par le lieu, sans rien savoir du lapin (2.2). Indice *"Pense à ce que le lapin trouve dans ce potager"* : simulation |
| 11 | `:139` | categorie, sanglier, foret | *les deux* ; *des végétaux*, *d'autres animaux* | **juste** | Fait sur (CM1 omnivore, `I6`). Indice *"en fouillant le sol"* : le sol porte des racines et des vers, l'enfant doit savoir lesquels |
| 12 | `:145` | aliment, hirondelle, ciel | *des insectes* ; *des graines*, *des feuilles* | **a corriger** | Fait sur. Indice *"Pense à ce que l'hirondelle peut attraper en volant"* : designe ce qui vole, donc la reponse, et redit l'enonce (2.1) |

### 2.1 Quatre indices qui disent la reponse par le verbe (items 3, 6, 9, 12)

`TRV-CE1-5` : *"une methode, jamais la reponse"*. Le critere de ma lecture de `-03` (section 2.1) vaut ici sans changement : un indice
*"Pense à ce que ..."* dont l'objet designe est la bonne reponse est une periphrase. Sur ce paquet, il se lit ainsi, et c'est la
reponse a `I8` :

- **Methode** : l'indice nomme le lieu, ou un geste qui ne dit pas ce qui est mange (*trouve autour d'elle*, *sous ses pattes*,
  *picore*, *en fouillant le sol*). L'enfant doit savoir ce que l'animal y trouve ou y picore : c'est la competence.
- **Periphrase** : le verbe porte la categorie par son sens, quel que soit l'animal. *Chasser* et *attraper* ont une proie pour objet,
  donc un animal ; *ce qui vient se coller dans sa toile* et *ce qu'elle peut attraper en volant* designent ce qui vole. L'enfant n'a
  rien a savoir de la chouette ou de l'hirondelle : il traduit le verbe. *Brouter* serait du meme cote (brouter, c'est manger de l'herbe) ;
  aucun indice ne l'emploie.

Mes remplacements nomment une partie du corps ou une attitude de l'animal (griffes et bec, langue collante, l'araignee qui attend, le bec
ouvert en vol) : l'enfant en tire lui-meme ce que l'animal mange. Ce n'est pas une barre neuve : ma condition le disait, le premier jet
ne la tient pas sur quatre items. **Non garde, a ma lecture** : la garde de `TRV-CE1-5` compte les mots et refuse un indice repete, elle
ne lit pas ce qu'un indice designe.

### 2.2 Deux distracteurs qui s'ecartent sans la competence (items 2, 10)

*Des souris* pour l'escargot s'ecarte par la taille, *des poissons* dans le potager par le lieu. Les deux sont de l'autre categorie,
comme ma regle le demande, mais un distracteur qui s'ecarte sans la competence en jeu fait de l'item un choix entre deux (motif de ma
lecture de `-01`, 2.1, et de `-03`, 2.2). Le remplacement est *des vers de terre* : un animal qui vit au meme endroit que l'escargot et
que le lapin, que ni l'un ni l'autre ne mange (`QC9`, je suis la seule garde de ce dernier point). *Des mouches* (escargot) et *des
souris* (lapin) restent : la mouche se pose sur la salade, la souris court dans le potager.

## 3. Les points interpretes par `marc-dev`

| Code | Interpretation | Reponse | Motif |
|---|---|---|---|
| `I1` | *"au meme type d'article"* lu comme le meme article exact (*des*) sur les trois propositions de B ; la vache passe en A | **reformulee** | **Correction de mon propre ecrit** : j'ai ecrit *"le meme type d'article (de l', des, du)"*, et mon exemple de la meme phrase portait *de l'herbe* a cote de *des vers de terre* et *des souris*. La parenthese enumerait les formes d'un seul type. **La regle, ecrite** : les trois propositions d'un item de B commencent par un article partitif ou indefini pluriel, *du*, *de la*, *de l'* ou *des* ; elles peuvent les meler ; aucune ne commence par *le*, *la*, *les*, *un*, *une*, un nombre, ou n'a pas d'article. Le motif est celui de mon avis : aucune proposition ne se designe par sa grammaire, et *"Que mange la vache ?"* accepte toutes ces formes. Ma lecture de `-03` a deja tenu *de l'eau* a cote de *du sucre*, *du bruit*. **Rien a changer dans les items** : la lecture stricte tient ma regle, elle est plus etroite. La vache en A est juste ; mon critere 6 la donnait en exemple, pas en demande. Seule la ligne du commentaire qui ecrit *"(des)"* comme regle est a recrire (5, point 7). **Non garde, a ma lecture** |
| `I3` | *des carottes* pour le lapin, dans aucune source du depot ; le CM1 dit *racines* | **confirmee** | La carotte est une racine : *des carottes* dit, avec le mot de l'enfant, ce que `cm1-sciences-regimes-alimentaires` dit du lapin (*"herbe, feuilles et racines"*), donc `CYC-1` tient. Un lapin dans un potager mange les carottes et leurs fanes ; c'est exact. La source s'ecrit dans la ligne du lapin (5, point 8). *De l'herbe*, qui est le lapin de `cm1-sciences-chaine-alimentaire` et de `ce1-texte-documentaire`, n'aurait pas ete plus sur, et le potager aurait alors ete un faux lieu |
| `I4` | *des feuilles de salade* (escargot), *des herbes* (chevre), *des feuilles* (chenille), *des mouches* (araignee), *des insectes* (hirondelle) | **confirmees, les cinq** | Exactes au CE1. *Des herbes* pour la chevre est la formule du CM1 (*"broute des herbes, des feuilles et l'écorce"*) ; *de l'herbe* serait aussi admis par la regle de `I1`, je ne le demande pas. *Des insectes* est un aliment, pas une categorie (ma lecture de `-03`, section 4) : il n'entre pas dans l'ensemble ferme. *Des feuilles* pour la chenille est vrai de presque toutes les chenilles qu'un enfant voit ; je ne demande pas *de l'arbre* nomme |
| `I5` | Lieux *dans le jardin*, *dans le potager de la ferme*, *dans sa toile*, *au bord du chemin* : critere 4 tenu ? | **tenu, les quatre** | Par la lecture ecrite en section 1 : un lieu ou l'animal trouve lui-meme sa nourriture. Le jardin et la toile ne sont ni *la nature* ni *la ferme* au sens strict des mots, mais personne n'y donne a manger a l'escargot ni a l'araignee, et c'est ce que *"dans la nature ou a la ferme"* veut ecarter. Le potager est a la ferme ; le bord du chemin est dans la nature |
| `I6` | Sanglier *les deux* et *familier* | **confirmee** | *Les deux* : exact, le CM1 le dit omnivore et le decrit (racines, glands, vers de terre, petits animaux). *Familier* : oui au CE1 ; le sanglier est dans les albums, les documentaires, les forets proches et parfois les villes. Il n'entre pas en B : ma table de l'avis l'en ecartait, le paquet le tient |
| `I7` | *La grenouille au bord de la mare*, sans *adulte* | **confirmee** | Le tetard vit dans l'eau, pas au bord, et il s'appelle *tetard* : c'est le nom que `-01` lui donne, distinct de *la grenouille*. *Adulte* ajouterait un mot et laisserait croire qu'un tetard est une grenouille. La jeune grenouille mange aussi des insectes : aucune seconde reponse |
| `I8` | Les 12 indices sont des methodes, mais plusieurs orientent vers la categorie (*picorer*, *brouter*, *attraper*) | **reformulee** | Quatre indices sont des periphrases (items 3, 6, 9, 12), huit sont des methodes ; *picorer* est une methode, *brouter* n'est employe par aucun indice. Le critere est en 2.1 |
| `I9` | La garde de `RAL-CE1-2` refuse aussi *granules* | **confirmee** | Ma table de l'avis nommait *"des granules du commerce"* pour le lapin de compagnie. Le motif (*rendent une seconde reponse defendable*) vaut pour les trois. Le motif est cherche sur le texte sans accent, donc *granulés* est vu ; *graines* ne l'est pas, et ne doit pas l'etre |
| `I10` | Le test relit la liste (animal declare, present avec une source, bonne reponse de A = categorie de la liste) : suffisant ou trop ? | **suffisant, pas trop** | Il verifie ce que le paquet declare contre ce qu'il sert, sans ajouter de regle. Ce qu'il ne verifie pas reste a moi : que la categorie de la liste soit exacte, et que la ligne de source dise vrai (section 1). Une remarque, sans la demander : la liste etant lue, *"un animal classe les deux n'entre pas en B"* (point 5 de `theo-archi`) tiendrait en une assertion. Si `theo-archi` l'ecrit, ce point quitte ma seule garde ; sinon il y reste, tenu aujourd'hui par la lecture des items 5 et 11 |
| `S4` | Les six paraphrases de ma lecture de `-03`, section 4 : le test de `-02` les admet-il ? | **non** | `CATEGORIES` (`PaquetCe1QlmCeQueMangentLesAnimauxTest.java:81-84`) ne porte que les trois cles *vegetaux*, *autres animaux*, *deux*. *Des plantes*, *des animaux*, *de la viande*, *un peu des deux*, *les deux à la fois*, *de tout* en proposition de B passent la garde de `S3`. Ce n'est pas une faute du premier jet : `ebc274b1` (14:51:28) est commite avant ma lecture de `-03` (`30ba34de`, 14:55:18). Correction en 5, point 9 ; le `S4` de `theo-archi` lui-meme (item de A sans les trois categories) est ecrit et mord (sonde 02) |

## 4. Constat nouveau, date du 2026-10-26 : dans la tache B, la bonne reponse est toujours l'intrus

**Ce que j'ai vu en jouant les six items de B.** Les deux distracteurs sont de la meme categorie, la bonne reponse de l'autre : *des
feuilles de salade* parmi deux animaux, *des insectes* parmi deux vegetaux. Un enfant de CE1 connait le jeu de l'intrus depuis le CP. Il
peut trouver chaque item de B en cherchant la proposition qui ne va pas avec les deux autres, sans rien savoir de l'animal. Il doit
encore classer trois aliments, ce qui est une partie de la ligne ; il ne relie plus l'animal a ce qu'il mange, ce qui en est le verbe.

**C'est ma regle qui le produit.** J'ai ecrit dans mon avis : *"Les deux distracteurs sont des aliments de l'autre categorie"*, pour que
la source de la categorie suffise a dire que l'animal ne les mange pas. Le premier jet tient cette regle exactement. **Je ne refuse
aucun item pour l'avoir tenue, et je ne durcis pas ma regle apres coup.**

**Ce que j'en fais** : je le regarderai a l'audit (`P2`), en jouant plusieurs manches : si l'enfant ecarte les deux distracteurs avant
d'avoir lu l'animal, ce sera une reserve neuve et datee, sur ma regle et non sur ce paquet. La sortie que je vois aujourd'hui demande un
distracteur de la meme categorie que la bonne reponse dont la source dit que l'animal ne le mange pas ; c'est exactement ce que mon avis
avait ecarte, parce qu'il demande une source de plus. Je ne la demande pas a ce premier jet. Signale a `camille-po` par `sofia-sm`, pour
qu'elle sache ce que l'audit regardera.

## 5. Ce que je demande a `marc-dev`

Texte exact. Rien d'autre ne change : ni les enonces, ni les bonnes reponses, ni les declarations des items, ni l'en-tete hors des
lignes nommees. Aucun texte neuf ne porte d'espace avant `?` ou `:`.

**Dans `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-ce-que-mangent-les-animaux.yaml`** :

| Point | Item, ligne | Texte actuel | Texte a ecrire |
|---|---|---|---|
| 1 | 2, `:87` | `distracteurs: ["des mouches", "des souris"]` | `distracteurs: ["des mouches", "des vers de terre"]` |
| 2 | 10, `:135` | `distracteurs: ["des poissons", "des souris"]` | `distracteurs: ["des vers de terre", "des souris"]` |
| 3 | 3, `:94` | `Pense à ce que la chouette cherche quand elle chasse.` | `Pense aux griffes et au bec de la chouette.` |
| 4 | 6, `:112` | `Imagine ce qui vient se coller dans sa toile.` | `Imagine l'araignée qui attend, immobile, au milieu de sa toile.` |
| 5 | 9, `:130` | `Pense à ce que la grenouille attrape avec sa langue.` | `Pense à la longue langue collante de la grenouille.` |
| 6 | 12, `:148` | `Pense à ce que l'hirondelle peut attraper en volant.` | `Imagine l'hirondelle qui vole le bec grand ouvert.` |

Les quatre indices neufs font 9, 10, 9 et 8 mots (`PaquetLu.mots`, douze au plus), sont distincts entre eux et des huit autres, et
ne nomment ni une categorie ni un aliment. Apres les points 1 et 2, la bonne reponse reste strictement la plus longue dans 3 items sur
12 (`TRV-CE1-2`).

7. **Commentaire, ligne `:29`**, remplacer :

```
# aliments au meme article (des), aucune n'est une categorie. Seuls entrent en tache aliment des
```

par :

```
# aliments au meme type d'article (du, de la, de l' ou des), aucune n'est une categorie. Seuls entrent en tache aliment des
```

8. **Commentaire, sources.** Remplacer les lignes `:38` a `:41` :

```
#   avis-nadia : l'avis de nadia-prof sur la seconde tache (squad/sprints/s57-version-cp-ce1-cm1-
#     trois-instances/avis-nadia-seconde-tache-ce1-qlm-02.md), section 1, tableau animal par animal
#   cm1-regimes : cm1-sciences-regimes-alimentaires (CM1) : lapin, vache et chevre herbivores,
#     sanglier omnivore (herbes, feuilles et racines ; racines, glands, vers de terre et petits animaux)
```

par :

```
#   lecture-nadia : la lecture de nadia-prof du premier jet (squad/sprints/s57-version-cp-ce1-cm1-
#     trois-instances/lecture-nadia-premier-jet-ce1-qlm-ce-que-mangent-les-animaux.md, section 1),
#     qui tient chaque categorie de cette liste pour exacte au CE1
#   cm1-regimes : cm1-sciences-regimes-alimentaires (CM1) : lapin herbivore (herbe, feuilles et
#     racines ; la carotte est une racine), vache et chevre herbivores, sanglier omnivore (racines,
#     glands, vers de terre et petits animaux)
```

   et, sur les lignes `:45` a `:56`, remplacer chaque `avis-nadia` par `lecture-nadia` (douze lignes, une par animal ; le reste de
   chaque ligne ne change pas, et le motif `ANIMAL_DE_L_ENTETE` du test la lit toujours).

**Dans `adapters/adapter-content/src/test/java/com/recreo/adapter/content/PaquetCe1QlmCeQueMangentLesAnimauxTest.java`** :

9. **`S4`, les six paraphrases.** `CATEGORIES` (`:81-84`) doit reconnaitre, par egalite apres `normalise`, les six paraphrases de ma
   lecture de `-03` (section 4). Leurs cles normalisees, mesurees par la normalisation du test recopiee : *plantes*, *animaux*, *viande*,
   *un peu des deux*, *deux a la fois*, *tout*. Texte :

```java
    private static final Map<String, String> CATEGORIES = Map.of(
            "vegetaux", "des végétaux",
            "plantes", "des végétaux",
            "autres animaux", "d'autres animaux",
            "animaux", "d'autres animaux",
            "viande", "d'autres animaux",
            "deux", "les deux",
            "un peu des deux", "les deux",
            "deux a la fois", "les deux",
            "tout", "les deux");
```

   Aucune des 36 propositions du paquet ne se normalise en l'une de ces six cles (mesure par script) : le paquet reste vert. Le jeu
   fautif porte au moins une sonde ou une paraphrase est proposee en tache B et refusee ; combien de sondes, et la forme, sont a
   `marc-dev` et `theo-archi`. Une consequence a connaitre, que j'accepte : une bonne reponse *des plantes* compterait pour *des
   végétaux* au recompte de `FER-1` ; aucun item ne l'ecrit.

10. **Rang et comptes** : le nombre d'items ne change pas (12), l'entree de rang garde ses totaux (`QC10`) ; les deux lignes de compte de
    l'en-tete (6 et 6) ne changent pas.

**Ce dont je reste la seule garde sur ce paquet, apres correction** : l'exactitude de chaque categorie et de chaque aliment contre sa
source ; qu'aucun distracteur de B ne soit mange par l'animal ; que A et B soient deux operations sur chaque item, et le constat de la
section 4 ; qu'un indice soit une methode et pas une periphrase ; *"meme type d'article"* (`I1`) ; *"un animal classe les deux n'entre
pas en B"*, sauf si `theo-archi` l'encode (`I10`) ; les paraphrases hors des six ; le lieu de l'enonce (critere 4) ; `NRC-CE1-1` sur le
sujet et `CYC-1`. **Aucune n'est couverte par un test.**

## 6. Signale, hors de mon perimetre

- **A `theo-archi`, symptome** : `ANIMAL_DE_L_ENTETE` et `ANIMAL_DECLARE` lisent un nom d'animal en `[a-z]+`. Un animal a deux mots ou a
  trait d'union (*ver de terre*, *ours brun*) ne pourrait pas entrer dans la liste. Aucun animal du paquet n'est dans ce cas
  aujourd'hui ; je decris le symptome, la forme est a `theo-archi` le jour ou il se presente.

## 7. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog. Le constat de la section 4 n'a pas d'identifiant :
s'il devient une reserve a l'audit, elle sera allouee a ce moment.

## 8. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes
corrections touchent six items, l'en-tete et le test : si `marc-dev` les applique telles qu'ecrites, une seconde lecture courte suffit,
a 0,5 **hors marge** (`DEC-0120` A.270, point 4 : toute seconde lecture avant `verify` d'un lot de l'instance neuve), soit **1,5**.
Aucun seuil n'est ecrit pour `-02` ; A.268 n'en a pose que pour `-01`, et je n'en deduis aucun. Je ne convertis pas une duree en
bareme : aucune regle ne le fait.

## 9. Duree de lecture

Mesuree par `date` : debut **2026-10-26 15:33:04**, ecriture de ce fichier 15:46:31, fin **15:52:04**, avant le commit. **Duree :
19 min 00 s**, dont la mesure du test du paquet et celle du garde-fou des identifiants
(`bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` : *"Tests run: 10,
Failures: 0, Errors: 0, Skipped: 0"*, `BUILD SUCCESS`, ce fichier present dans l'arbre ; ce fichier n'ecrit aucun identifiant neuf), et
`.github/scripts/verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* Aucun seuil de
duree ne s'applique a une lecture. Au bareme, section 8.

## 10. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 5, points 1 a 10 |
| `theo-archi` | `I10` (l'assertion *les deux hors de B*, s'il la veut) ; `S4` (forme des sondes des paraphrases) ; section 6 |
| `camille-po`, par `sofia-sm` | section 4 (constat de l'intrus, ce que l'audit regardera) ; `I1` (la regle, si elle veut la porter dans la story) |
| `sofia-sm` | le cout est en section 8 ; la ligne de seconde lecture, s'il y en a une, s'inscrit sur la ligne hors marge |

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
