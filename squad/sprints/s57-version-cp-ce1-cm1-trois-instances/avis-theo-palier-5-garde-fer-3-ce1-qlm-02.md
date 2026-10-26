# s57 - palier 5 : avis de theo-archi sur la garde de `FER-3` de S57-BR-QLM-CE1-02 (recompte par items)

- **Auteur** : `theo-archi`. Date : 2026-10-26 14:18 (UTC+01:00), lue par `date`.
- **Arbre** : worktree `s57-p5-theo-fer3`, base `round-two` a `0ad323e9` (`git rev-parse`). Le paquet `ce1-qlm-ce-que-mangent-les-animaux` n'existe pas a cette base.
- **Demande** : `avis-nadia-seconde-tache-ce1-qlm-02.md`, section 4 et texte du critere 6 (section 5) : recompte de `FER-3` sur les items, trois sondes, forme a `theo-archi` ou *"non garde : `FER-3`"*.
- **Lu** : cet avis en entier ; la story `S57-BR-QLM-CE1-02` et les reperes communs `QC5`, `QC9`, `QC10` (`docs/backlog/backlog.md:24047-24111`), dont `RAL-CE1-1`, `RAL-CE1-2`, `FER-1`, `FER-3` dans les mots de `nadia-prof` (`:24056`, `:24099`) ; `PaquetCe1QlmEtapesDeDeveloppementTest.java`, `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java` et `PaquetLu.java` en entier ; mon avis `avis-theo-palier-5-garde-rotation-ce1-qlm-01.md`.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni test, ni jeu fautif, ni contenu ; il ne cree aucun identifiant de condition et ne prononce aucun Done. Aucun `mvn verify` lance.

## 0. Verdict

| Code | Question | Reponse |
|---|---|---|
| `V1` | Faisable sur le YAML tel que le chargeur le lit | **Oui.** La tache d'un item se calcule sur un seul champ, `bonneReponse`, que `PaquetLu.Item` expose deja. Aucun enonce, aucun indice, aucun commentaire n'est lu pour classer |
| `V2` | Reconnaissance des trois categories | **Egalite** apres normalisation de forme (casse, accents, apostrophe, blancs, insecables, ponctuation finale, article de tete), jamais une recherche de sous-chaine. Mesuree sur le corpus reel (section 2.3) |
| `V3` | Ce que la garde refuse | Les trois sondes de `nadia-prof`, plus un refus tire de son texte de la tache A (section 3, `S4`), a confirmer par elle |
| `V4` | Lieu | **Le test du paquet de `-02`**, pas le test transverse (section 4) |
| `V5` | Ce qui reste a `nadia-prof` | Les trois proprietes de sa section 4, plus quatre bornes de la tache B que ce recompte ne lit pas (section 5) |
| `V6` | ArchUnit, ADR | Aucune regle neuve, aucun `ADR` : garde de contenu sur du YAML, dans la forme d'`ADR-0013` (jeu fautif commite) et du precedent `-01` |

## 1. Pourquoi c'est faisable

**La bonne reponse est un champ, pas une lecture.** Le chargeur sert `bonneReponse` tel quel ; `PaquetLu` le lit avec SnakeYAML et l'expose en `Item.bonneReponse()`, les distracteurs en `Item.distracteurs()`. Le critere de `nadia-prof` ne porte que sur ces deux champs : *"un item dont la bonne reponse est des vegetaux, d'autres animaux ou les deux compte pour A, tout autre item pour B"*, et *"un item de B dont une proposition est [une categorie] est refuse"*. Le test n'a donc aucun texte libre a interpreter : il compare trois chaines courtes a un ensemble ferme de trois formes.

**Ce que cela evite, par rapport a `-01`.** Le test de `-01` reconnait sa tache par deux motifs d'enonce (`FORME_VOISINE`, `FORME_ORDRE`) et la fait declarer item par item en commentaire (`DVP-CE1-1 tache : ...`). Ici, ni l'un ni l'autre n'est necessaire : la tache est une fonction de la bonne reponse. **Aucune declaration de tache par item n'est demandee** ; en ajouter une serait une piece mobile qui ne refuse rien de plus que le calcul.

## 2. La forme

### 2.1 Classe, lieu, marqueur

- **Classe** : le test du paquet de `-02`, `PaquetCe1Qlm<Nom>Test` (`QC4`), dans `adapters/adapter-content/src/test/java/com/recreo/adapter/content/`, a cote de `PaquetCe1QlmEtapesDeDeveloppementTest`. Le nom suit celui que `marc-dev` donne au paquet.
- **Jeu fautif** (`ADR-0013` obligation 3) : `adapters/adapter-content/src/test/resources/contreexemple/<classe>/contenu/<fichier>.yaml`, sondes reperees par un commentaire `sonde NN` au-dessus de l'item, lues par `sonde(fautif, "sonde NN")` comme a `-01`.
- **Marqueur porte par le paquet** : aucun par item. L'en-tete porte **deux lignes de compte**, une par tache, de la forme que `-01` lit deja (`TACHE_DE_L_ENTETE`, `:113`) :

```
#   categorie : <ce que la tache demande> (N items)
#   aliment : <ce que la tache demande> (N items)
```

  Le test les relit avec `^#\s+(categorie|aliment) : .*\((\d+) items?` et exige qu'elles egalent les comptes **calcules sur les items**. Le commentaire est donc verifie, jamais cru : c'est exactement le deplacement que `nadia-prof` demande. Les deux noms de tache sont une proposition ; `marc-dev` peut en prendre d'autres, en ASCII, tant que le motif les nomme.

### 2.2 La reconnaissance d'une categorie

Une seule fonction, `categorie(String proposition)`, qui rend l'une des trois categories ou rien. Elle normalise puis compare **par egalite** a un ensemble ferme de trois formes, cle normalisee vers forme ecrite :

| Forme normalisee | Categorie |
|---|---|
| `vegetaux` | *des vegetaux* |
| `autres animaux` | *d'autres animaux* |
| `deux` | *les deux* |

Normalisation, dans cet ordre :

1. minuscule et sans accent : `PaquetLu.sansAccent`, deja ecrite et deja utilisee par `QMT-CE1-1` ;
2. apostrophe typographique `U+2019` remplacee par l'apostrophe droite (le corpus en porte dans un fichier sur 188) ;
3. toute suite de blancs, espace insecable `U+00A0` et espace fine insecable `U+202F` comprises, ramenee a une espace, puis `strip` ;
4. ponctuation finale `.` ou `!` retiree ;
5. **un** article de tete retire, sur une liste fermee : `des `, `les `, `de la `, `de l'`, `du `, `de `, `d'`, `l'`, `le `, `la `.

**Pourquoi l'egalite et pas la recherche.** Une garde qui cherche `vegetaux` ou `deux` dans la chaine refuserait une proposition qui contient le mot sans etre la categorie, et la tentation de la sous-chaine est exactement le defaut de s05 (`!important` trouve dans un nom de variable). L'egalite apres normalisation ne reconnait que la categorie ecrite autrement.

**Ce que l'article retire couvre, et ce qu'il ne couvre pas.** *"Les vegetaux"*, *"vegetaux"*, *"des autres animaux"*, *"Les deux."* sont reconnus. *"des plantes"*, *"des animaux"*, *"de la viande"*, *"un peu des deux"*, *"les deux a la fois"* ne le sont pas : ce sont des **paraphrases**, pas des variantes de forme. Les reconnaitre serait ecrire une liste de synonymes que `nadia-prof` n'a pas ecrite ; elles restent a sa lecture (section 5). Si elle les nomme, chacune entre dans l'ensemble ferme comme une ligne.

**Un effet a connaitre** : la forme `deux` seule est reconnue comme *les deux*. Une proposition *"deux"* n'a aucun sens dans ce paquet ; ailleurs elle en aurait un, et c'est l'un des motifs de la section 4.

### 2.3 Mesure sur le materiau reel

Les fixtures de `nadia-prof` et les miennes ressemblent a ce que nous imaginons. J'ai donc applique `categorie` a **toutes les propositions que le chargeur sert aujourd'hui**, pour voir ce qu'elle reconnait dans un contenu ecrit par d'autres.

- **Perimetre** : tout fichier `*.yaml` de `adapters/adapter-content/src/main/resources/contenu/` a `0ad323e9`, dont le corps a une cle `items` ; dans chacun, tout item qui porte `bonneReponse` ; propositions = `bonneReponse` plus chaque distracteur. Exclus : les jeux fautifs de `src/test/resources`, les items sans `bonneReponse`.
- **Resultat** : 187 fichiers, 3 156 items, 9 297 propositions ; **0 reconnue** comme categorie.
- **Variantes** (ecrites par moi, donc de valeur moindre, dites comme telles) : 16 variantes de forme essayees (7 de *des vegetaux*, 5 de *d'autres animaux*, 4 de *les deux*) sont reconnues dans leur categorie, insecable et apostrophe typographique comprises ; les 10 aliments et paraphrases de 2.2 et de la section 1 de `nadia-prof` (*de l'herbe*, *des vers de terre*, *des souris*, *du foin*, ...) ne le sont pas.
- **Methode** : programme Java a fichier unique, hors du depot et non commite, lance par le lanceur de sources du JDK 21.0.10 (propriete `java.version` imprimee par le programme), classpath SnakeYAML 2.6, version que l'avis de `-01` a lue par `dependency:build-classpath` pour `adapters/adapter-content` et que je n'ai pas relue. Il lit chaque fichier avec `new Yaml().load`, comme `PaquetLu`. **Ce n'est pas le test** : la preuve dans le depot sera l'execution de la classe de test sur son jeu fautif commite.

Ce que ce zero prouve : la reconnaissance ne mord sur rien de ce qui existe, donc elle n'est pas trop large sur du contenu reel. Ce qu'il ne prouve pas : qu'elle mord sur le paquet de `-02`, qui n'existe pas. C'est le role du jeu fautif.

## 3. Ce que le test refuse, et un contre-exemple pour chaque refus

Calcul commun : pour chaque item, `tache = categorie(bonneReponse).isPresent() ? "categorie" : "aliment"`.

| Code | Refus | Origine | Contre-exemple | Message attendu |
|---|---|---|---|---|
| `S1` | Le paquet ne sert pas les deux taches | `nadia-prof`, sonde 1 | **Le paquet reel restreint a ses items de tache A** (filtre sur la tache calculee), en-tete inchange | `FER-3` et *"deux tâches"* |
| `S2` | La tache la moins servie porte moins de 3 items | `nadia-prof`, sonde 2 | **Le paquet reel restreint a ses items A plus les deux premiers items B**, en-tete inchange | `FER-3` et *"la moins servie"* |
| `S3` | Un item de B porte une categorie en proposition | `nadia-prof`, sonde 3 | jeu fautif, *"Que mange la vache dans le pré ?"*, bonne *"de l'herbe"*, distracteurs *"des souris"*, *"d'autres animaux"* ecrit avec l'apostrophe typographique `U+2019` (le jeu fautif porte le caractere, ce fichier le nomme) | `FER-3` et le texte de la proposition fautive |
| `S4` | Un item de A ne porte pas les trois categories en propositions | **ajoutee par moi**, tiree du texte de la tache A du critere 6 (*"les trois propositions sont des vegetaux, d'autres animaux, les deux"*), pas une condition neuve | jeu fautif, meme enonce, bonne *"des végétaux"*, distracteurs *"des souris"*, *"les deux"* | `FER-3` et *"trois catégories"* |
| `S5` | Les comptes que l'en-tete ecrit ne sont pas ceux des items | `QC5`, forme de `-01` (`unCommentaireQuiEcritUnMauvaisCompteDeTachesEstRefuse`) | **l'en-tete reel** avec un compte change, items inchanges | `FER-3` et *"comptes"* |
| `P1` | (passe) un item de B a trois aliments | tire de la section 1 de `nadia-prof` | jeu fautif, meme enonce, bonne *"de l'herbe"*, distracteurs *"des souris"*, *"des vers de terre"* | aucune exception |

**Pourquoi `S1`, `S2` et `S5` partent du paquet reel.** C'est la forme de `-01` (`unPaquetQuiNePorteQueDeuxItemsDeLaTacheOrdreEstRefuse`) et elle prouve la garde sur le contenu ecrit, pas sur un paquet de ma main. En-tete inchange sur `S1` et `S2` : **c'est ce qui tue une garde qui lirait les comptes de l'en-tete au lieu des items**, le defaut que `nadia-prof` nomme ; l'en-tete du paquet reel annonce deux taches d'au moins trois items, donc une telle garde laisserait passer `S1` et `S2`. L'ordre des assertions de `-01` vaut ici : comptes calcules d'abord, comparaison a l'en-tete ensuite, sinon `S1` et `S2` rougiraient sur le message de `S5`.

**Pourquoi `S3` porte l'apostrophe typographique.** Une garde par egalite brute sur *"d'autres animaux"* passerait la sonde ecrite avec l'apostrophe droite et laisserait entrer la meme categorie ecrite autrement. Une seule sonde suffit a tuer l'egalite non normalisee ; je n'en ajoute pas une par etape de normalisation.

**Pourquoi `S4`.** Sans lui, un item dont la bonne reponse est une categorie et les distracteurs deux aliments compte pour A : il gonfle A sans poser A, et c'est la symetrie exacte du refus `S3`. Le texte du critere 6 le dit deja. **A confirmer ou retirer par `nadia-prof`** (`A4`).

**Pourquoi `P1`.** Une garde qui refuserait tout item dont les propositions ne sont pas toutes des categories passerait `S1` a `S5` et rendrait la tache B impossible a ecrire. `P1` la tue. Ses propositions sont celles que `nadia-prof` ecrit en section 1, pas les miennes.

**Le paquet vide ne passe pas en silence.** Un paquet sans item n'a aucune tache : `S1` le refuse. Un paquet sans item B n'a pas de branche B a lire : `S1` le refuse aussi. Le refus `S3` ne peut donc pas etre vert faute d'avoir lu.

**FER-1, critere 1** : il se lit avec la meme fonction (les trois categories rendues par `categorie` sur les bonnes reponses de A). Je le signale parce que les deux gardes partagent `categorie` ; je n'en change pas la forme.

## 4. Le test du paquet, pas le test transverse

- **La tache est un fait de la ligne, pas une condition transverse.** `FER-3` est transverse dans ses mots (*"au moins deux taches distinctes"*), mais **ce qui distingue deux taches est propre a chaque ligne** : *voisine* et *ordre* sur `-01`, lues sur l'enonce ; *categorie* et *aliment* sur `-02`, lues sur la bonne reponse. Le test transverse derive sa population des descripteurs et n'enumere aucun fichier (sa Javadoc, `:32-35`) ; y loger la definition des taches de `-02` l'obligerait a reconnaitre un paquet par son nom, ce qu'il s'interdit par construction.
- **Dans le test transverse, la garde mordrait par inclusion.** Sur un autre lot de questionner le monde CE1, *"les deux"* est une bonne reponse plausible qui ne veut pas dire *"des vegetaux et d'autres animaux"* (*"Qui a besoin d'eau, la plante ou l'animal ?"*). L'item serait classe tache A, et `S3`/`S4` refuseraient un item juste. **Ce faux rouge n'est pas observe** : la mesure de 2.3 ne trouve aucune categorie dans le corpus servi aujourd'hui. L'argument est structurel, et je le dis comme tel.
- **Ne mordre que sur `-02`** : le test du paquet ne lit que son fichier (`FICHIER_CONTENU`) et son jeu fautif. Aucun autre paquet n'est lu.
- **Ce qui me ferait changer d'avis** : une deuxieme ligne dont les taches se distinguent par le meme ensemble ferme de categories de bonne reponse. La fonction se recopie alors, et l'extraction se discute a ce deuxieme usage.

## 5. Ce que ce recompte ne garde pas, et qui reste lu par `nadia-prof`

Les trois proprietes de sa section 4, inchangees :

1. que chaque item de B soit une reconnaissance d'aliment et non une devinette, donc que A et B soient deux operations distinctes item par item (`QC9`, l'operation mentale de `FER-3`) ;
2. qu'aucun distracteur de B ne soit mange par l'animal, *du foin* a cote de *de l'herbe* compris (`TRV-CE1-2`, *"une seule acceptable"*) ;
3. la categorie de chaque animal contre sa source et contre `cm1-sciences-regimes-alimentaires` (`QMT-CE1-2`, `CYC-1`), donc l'exactitude de la bonne reponse de chaque item de A.

Quatre bornes de la tache B que le critere 6 ecrit et que **cette forme ne lit pas** :

4. **les paraphrases d'une categorie** en proposition de B (*"des plantes"*, *"des animaux"*, *"de la viande"*) : la garde ne reconnait que les trois formes ecrites (2.2) ;
5. *"n'entrent en tache B que les animaux dont la source donne une seule categorie"* (la poule) : il faudrait lire l'animal dans l'enonce et tenir la liste des animaux avec leur categorie, que le paquet ecrit en commentaire ;
6. *"les deux distracteurs sont des aliments de l'autre categorie"* : il faudrait classer chaque aliment, une liste que personne n'a ecrite ;
7. *"trois aliments au meme type d'article"* : lisible sur l'article de tete, mais *"meme type"* demande une lecture que je ne tranche pas (*du*, *de la*, *de l'*, *des* sont-ils un seul type ?).

Les points 5 et 7 sont encodables si `nadia-prof` le demande : 5 par une liste fermee des animaux ecrite dans le test, comme `ESPECES` a `-01` ; 7 par une liste fermee d'articles admis. Je ne les propose pas : ce n'est pas la demande, et aucun refus n'en depend aujourd'hui. Dans le rapport de qualite, le recompte s'ecrit **garde** ; les points 1 a 7 s'ecrivent *"non garde : `FER-3`"* pour 1 et 4, *"non garde : `TRV-CE1-2`"* pour 2 et 6, *"non garde : `QMT-CE1-2`, `CYC-1`"* pour 3, et *"non garde"* avec le texte du critere 6 pour 5 et 7.

## 6. Actions et routage

| Code | Quoi | Qui |
|---|---|---|
| `A1` | `categorie`, le calcul de tache et les refus `S1` a `S5` dans le test du paquet de `-02`, avec le paquet, dans le commit du premier jet | `marc-dev` |
| `A2` | Jeu fautif commite portant `S3`, `S4` et `P1` ; `S1`, `S2`, `S5` construits sur le paquet reel ; chaque test attend `FER-3` et son message propre | `marc-dev` |
| `A3` | Deux lignes de compte dans l'en-tete du paquet, au format de 2.1 | `marc-dev` |
| `A4` | `S4` est ajoute par moi, tire du texte de la tache A : le confirmer ou le retirer ; dire si les paraphrases du point 4 entrent dans l'ensemble ferme | `nadia-prof`, par `sofia-sm` |

**Signe** : `theo-archi`, s57, palier 5, 2026-10-26.
