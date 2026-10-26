# s57 - lecture avant `verify` du premier jet de `ce1-qlm-etapes-de-developpement` (`S57-BR-QLM-CE1-01`, `P0`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-26 10:52 (UTC+01:00), lue par `date`.
- **Arbre** : branche `s57-p5-nadia-lecture01`, base `round-two` a `77fc8f8e`, lue par `git`.
- **Commit lu** : `7c922786` (`[MARC]`, 2026-10-26 10:22:24 +0100), premier jet de `marc-dev`, commite seul.
- **Type** : lecture avant `verify` (`P0`, `QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition
  neuve. Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue. Le paquet n'est pas au catalogue (`QC3` : `grep -c 'ce1-qlm' catalogue.yaml` rend 0),
  un enfant ne peut pas le jouer, et je n'ai pas lance l'application. Je lis le YAML, ses deux tests et les paquets voisins. Le jeu
  vient a l'audit (`P2`).
- **Lu** : `ce1-qlm-etapes-de-developpement.yaml` (15 items) ; `PaquetCe1QlmEtapesDeDeveloppementTest.java` ;
  `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java` ; l'entree de rang ajoutee a
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` ; `cp-qlm-cycle-de-vie.yaml` et
  `cm1-sciences-etapes-developpement.yaml` (voisins de la story) ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md`
  (2, 3.1) ; story `S57-BR-QLM-CE1-01` et `QC1` a `QC9` (`docs/backlog/backlog.md:24047-24093`) ;
  `arbitrage-nadia-palier-5-qlm-neuf-lots-avant-ouverture.md` ; `reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md` (3).
- **Mesure lancee** : `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmEtapesDeDeveloppementTest`,
  JDK 21.0.10. Rapport Surefire : **26 tests, 0 echec, 0 erreur**. Perimetre : ce seul test, module `adapter-content` et ses
  dependances. Ce n'est ni un `verify`, ni la porte technique.

## Resume

| Point | Reponse |
|---|---|
| Items | **8 justes, 5 a corriger, 2 refuses en l'etat** (section 2) |
| Ce qui ferait refuser le lot | items 12 et 15 : deux suites defendables, parce qu'un developpement est un cycle (`DVP-CE1-2`) |
| Garde manquante | la tache *ordre* ne refuse pas un distracteur qui est la bonne suite qu'on fait tourner dans le cycle (section 3) |
| Comptes de `TRV-CE1-2` et `TRV-CE1-5` | **par paquet** : c'est ma condition, pas une lecture plus stricte (section 4.1) |
| `NRC-CE1-1`, egalite des enonces | **suffit pour la partie textuelle** ; le sujet reste a moi, et je l'ai exerce : l'item 6 ne compte pas (section 4.2) |
| Cout de `P0` | **seuil de 1,67 non depasse a cette ecriture, et il ne peut pas l'etre par `P0` seul au bareme** ; la correction n'est pas encore faite (section 5) |

---

## 1. Ce que j'ai verifie avant les items

- **Liste fermee de `DVP-CE1-1`** (`ce1-qlm-etapes-de-developpement.yaml:21-24`, test `:85-90`) : identique, mot pour mot et dans
  l'ordre, a ma liste de 3.1 (grenouille : oeuf, tetard, jeune grenouille, grenouille ; papillon : oeuf, chenille, chrysalide,
  papillon ; poule : oeuf, poussin, poule ; plante : graine, jeune pousse, plante, fleur, fruit). Chacun des quinze items nomme une
  espece de la liste et une etape de la liste.
- **La liste elle-meme, contre ce que j'enseigne** : je la relis comme source et je la tiens pour exacte au niveau du CE1. La
  jeune grenouille est le stade ou la queue n'a pas fini de regresser ; la chrysalide suit la chenille ; le poussin devient la poule
  dans la vie *de la poule* ; la fleur donne le fruit, qui porte les graines.
- **`QMT-CE1-2`, la source** (`:18-20`) : *"cette liste est celle de nadia-prof (ibid., 3.1)"*. Je l'accepte : chaque fait d'un item
  est une place dans la liste fermee, et chaque item declare son espece a cote de lui (`DVP-CE1-1 espece : ...`), ce qui le rattache
  a la liste et a sa source. C'est le precedent de `cp-qlm-cycle-de-vie.yaml:17-19`. **Ce que cela veut dire, et que je nomme** :
  aucune source externe n'a ete consultee par le redacteur ; l'exactitude de chaque ordre repose sur ma relecture, faite item par
  item en section 2.
- **`FER-1`** : les six etapes de la parenthese sont en bonne reponse d'un item de la tache *voisine* : oeuf (item 5), tetard (13),
  grenouille (9), graine (11), jeune pousse (3), plante (7). Metamorphose et germination servies. Mes corrections de la section 2
  n'en retirent aucune.
- **`FER-3`** : *voisine* 11 items, *ordre* 4 ; la moins servie en porte au moins 3.
- **Jamais la premiere ni la derniere** : aucun item ne la demande ; aucun ne demande ce qui vient apres l'etape finale ni avant
  l'etape initiale.
- **`BPL-CE1-2`** : aucun champ ne lie la graine a la lumiere.
- **`CYC-1`** : aucun item ne contredit `cm1-sciences-etapes-developpement` (le tetard qui devient grenouille, la chenille qui sort
  de l'oeuf, le poussin qui sort de l'oeuf de poule) ni `cp-qlm-cycle-de-vie` (*"Ses graines donneront de nouvelles plantes"* est
  le fruit qui donne la graine).
- **`QMT-CE1-1`** : aucun des quinze mots exclus ; *tetard*, *chenille*, *chrysalide*, *graine*, *pousse* sont admis.
- **Lisibilite CE1** : quinze enonces d'une phrase, 13 a 15 mots, toujours la meme construction (*"Dans la vie de ..., quelle etape
  vient juste avant / apres ... ?"*, *"... quelle suite est dans le bon ordre ?"*). Un enfant de CE1 la lit seul. Les indices font
  une ou deux phrases courtes. Aucun mot hors du niveau.
- **`TRV-CE1-6`, biais** : aucun prenom, aucun foyer, aucun objet possede.
- **Accents** : je n'ai vu aucun accent manquant dans ce que l'enfant lit. Ce controle est au `relecteur-pedagogique`
  (`DEC-0002`, `DEC-0056`), pas a moi.
- **Rang de la bonne reponse** : aucune proposition numerique ; l'entree de rang dit 15 items et 45 propositions, ce qui est le
  compte exact (15 fois 3). La mecanique melange les propositions a chaque manche (`BanditManchotSession.java:171`, `:196`) : la
  place a l'ecran ne trahit pas la reponse. Sur le texte, la bonne reponse est strictement la plus longue dans **5 items sur 15**
  (1, 2, 3, 9, 14), sous la moitie (`TRV-CE1-2`) ; apres mes corrections, 6 sur 15 [calcul a la main, a recompter par le test].

## 2. Verdict par item

Numerotation : ordre du fichier. *Juste* : rien a changer. *A corriger* : je demande la correction avant `verify` ; s'il restait
tel quel a l'audit, ce serait une reserve. *Refuse en l'etat* : s'il restait tel quel a l'audit, je refuserais le lot.

| # | Ligne | Item (enonce abrege, bonne reponse) | Verdict | Motif |
|---|---|---|---|---|
| 1 | `:56` | grenouille, apres le tetard : *la jeune grenouille* | **juste** | Ordre exact. *La grenouille* n'est pas proposee (`DVP-CE1-2`, mon exemple). *La chenille* : la larve d'une autre metamorphose, piege plausible |
| 2 | `:63` | papillon, apres la chenille : *la chrysalide* | **juste** | Ordre exact ; *l'oeuf* n'est pas defendable |
| 3 | `:70` | plante, apres la graine : *la jeune pousse* | **a corriger** | Ordre exact. **Distracteur *le tetard*** : une etape d'animal dans la vie d'une plante s'ecarte sans rien savoir du developpement. L'item devient un choix entre deux. Motif en 2.1 |
| 4 | `:77` | grenouille, ordre : *oeuf, tetard, grenouille* | **juste** | La suite du libelle. Aucun distracteur n'est la bonne suite qu'on fait tourner (section 3) |
| 5 | `:84` | grenouille, avant le tetard : *l'oeuf* | **juste** | Ordre exact. Indice *"Que vient avant ?"* : construction incorrecte, routee au relecteur (2.2) |
| 6 | `:91` | poule, apres l'oeuf : *le poussin* | **a corriger** | Ordre exact, mais **`NRC-CE1-1`** : `cp-qlm-cycle-de-vie.yaml:45` dit *"Un poussin sort de l'œuf"*. Un enfant de CP le resout avec ce seul item, sans rien de plus : il sert la ligne de CP et **ne compte pas** pour `QLM-CE1-01`. L'indice *"Pense à ce qui sort de l'œuf de la poule"* le confirme |
| 7 | `:98` | plante, apres la jeune pousse : *la plante* | **a corriger** | Ordre exact. **Distracteur *le poussin*** : meme motif que l'item 3 |
| 8 | `:105` | papillon, ordre : *chenille, chrysalide, papillon* | **juste** | Aucun distracteur n'est une rotation de la bonne suite |
| 9 | `:112` | grenouille, apres la jeune grenouille : *la grenouille* | **juste** | Ordre exact. Observation, pas une demande : la bonne reponse est aussi le nom de l'espece de l'enonce, c'est l'item le plus facile du paquet. Je le regarderai en jouant |
| 10 | `:119` | papillon, apres l'oeuf : *la chenille* | **juste** | Ordre exact. *Le tetard*, qui sort aussi d'un oeuf, est le meilleur piege du paquet |
| 11 | `:126` | plante, avant la jeune pousse : *la graine* | **a corriger** | Ordre exact. **Distracteur *la chrysalide*** : meme motif que l'item 3. Indice *"Que vient juste avant ?"* : 2.2 |
| 12 | `:133` | plante, ordre : *graine, plante, fleur* | **refuse en l'etat** | **Distracteur *"fleur, graine, plante"* defendable** : la fleur donne le fruit, le fruit la graine, la graine une nouvelle plante. C'est le cycle, et `DVP-CE1-2` dit *"Jamais deux etapes defendables"*. Section 3 |
| 13 | `:140` | grenouille, avant la jeune grenouille : *le tetard* | **juste** | Ordre exact |
| 14 | `:147` | papillon, avant le papillon : *la chrysalide* | **juste** | Ordre exact. Indice : 2.2 |
| 15 | `:154` | poule, ordre : *oeuf, poussin, poule* | **refuse en l'etat** | **Distracteur *"poule, œuf, poussin"* defendable** : la poule pond l'oeuf, le poussin en sort. Un enfant qui sait que la poule pond choisira cette suite avec raison. Section 3 |

### 2.1 Les distracteurs d'animal dans la vie d'une plante (items 3, 7, 11)

`DVP-CE1-2` admet *"une etape d'une autre espece (tetard pour le papillon)"*, et je ne retire pas cette famille : entre deux
animaux, le piege porte sur le developpement (deux larves, deux sorties de l'oeuf). **Ma liste ne compte qu'une plante** : pour un
item de la plante, la famille b est forcement une etape d'animal, et un enfant l'ecarte parce qu'un tetard n'est pas une plante, pas
parce qu'il connait l'ordre. Je ne l'avais pas vu en ecrivant la liste ; ce n'est pas une condition neuve, c'est la verification de
plausibilite que je fais sur tout distracteur.

### 2.2 Les indices *"Que vient (juste) avant ?"* (items 5, 11, 14)

`:87`, `:129`, `:150`. La construction correcte est *"Qu'est-ce qui vient (juste) avant ?"*. C'est une question de francais
correct, qui est au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04) : je le signale, je ne le tranche pas. La
methode de l'indice est juste.

## 3. La garde qui manque : la tache *ordre* et le cycle

`DVP-CE1-2` vaut pour les deux taches. Dans la tache *ordre*, le test (`PaquetCe1QlmEtapesDeDeveloppementTest.java:287-295`) verifie
qu'un distracteur porte les trois memes etapes dans un autre ordre. **Il laisse passer la bonne suite qu'on fait tourner dans le
cycle**, qui est defendable : apres la derniere etape revient la premiere (la grenouille, le papillon et la poule pondent un oeuf ;
le fruit porte la graine). Sur trois etapes, trois ordres sur six sont des rotations du bon ordre, dont la bonne reponse ; les trois
autres seuls sont des distracteurs.

**Correction de mon propre ecrit** : en 3.1 j'ai motive *"jamais la premiere ni la derniere"* par le cycle, pour la tache *voisine*,
et je n'ai pas ecrit que la tache *ordre* le rencontrait aussi. `DVP-CE1-2` (*"Jamais deux etapes defendables"*) le couvre deja :
ce n'est pas une condition neuve.

**Sondes, la forme est a `theo-archi`** :

- doit etre refuse : un item *ordre* de la poule, bonne reponse *"œuf, poussin, poule"*, distracteur *"poule, œuf, poussin"* ;
- doit etre refuse : un item *ordre* de la plante, bonne reponse *"graine, plante, fleur"*, distracteur *"fleur, graine, plante"* ;
- doit passer : le meme item de la poule avec *"poussin, œuf, poule"* et *"poule, poussin, œuf"*.

Si la forme n'est pas possible : *"non garde : `DVP-CE1-2`, rotation dans le cycle"* dans le rapport de qualite, et je lis.

## 4. Les points que `marc-dev` a laisses ouverts

### 4.1 `TRV-CE1-2` et `TRV-CE1-5` : par paquet

`ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:43-46` compte par paquet et demande si je l'entends sur
le lot. **Par paquet, et c'est ma condition, pas une lecture plus stricte.**

- `TRV-CE1-5` le dit en toutes lettres : *"pas le meme sur tout le paquet"*.
- `TRV-CE1-2` : l'enfant joue un paquet ; c'est dans une partie que la bonne reponse la plus longue se devine. Un lot ou un paquet
  serait a moitie et un autre a zero ne protegerait pas l'enfant qui joue le premier.
- Ici *lot* a deux sens : sur cette matiere, une ligne fait un paquet et un lot (`M1`), donc *par lot* au sens de `PAR-8` est *par
  paquet* ; le test emploie *lot* pour l'ensemble des paquets de la matiere, et sur cet ensemble le compte par paquet suffit, comme
  le Javadoc le dit. La phrase conditionnelle *"si nadia-prof entend ..."* peut devenir la reponse ci-dessus.

### 4.2 `NRC-CE1-1` : l'egalite des enonces

`PaquetCe1QlmEtapesDeDeveloppementTest.java:398-412` refuse un enonce egal, a la casse et aux espaces pres, a celui d'un item de
`cp-qlm-cycle-de-vie` ou de `cm1-sciences-etapes-developpement`.

- **Pour la partie textuelle, cela suffit** : c'est la sonde que j'ai validee (*"un item copie mot pour mot d'un paquet de CP voisin
  est refuse"*). Un item copie a le meme enonce ; comparer les propositions n'ajouterait rien que je puisse demander a un test.
- **Le CM1 dans la comparaison** : `NRC-CE1-1` ne vise que le CP. Le comparer au CM1 ne refuse aucun item juste ici ; je ne le
  demande pas et je ne m'y oppose pas.
- **Le sujet reste a moi** (*meme bonne reponse a la meme tache*, *"un item qu'un enfant de CP resoudrait"*), et c'est la que le
  premier jet tombe : l'item 6, que l'egalite des enonces ne pouvait pas voir.

### 4.3 La forme de la garde `DVP-CE1-2` dans la tache *voisine*

Le test refuse toute etape de la meme espece voisine de la bonne reponse (`:325-328`), et le Javadoc la dit plus stricte que ma
condition (`:52-55`). **Elle l'est**, et je l'accepte : elle ecarte par exemple *l'oeuf* pour *"juste avant la jeune grenouille"*,
qui n'est pas defendable, mais aucun distracteur qu'elle ecarte ne manque a ce paquet. La forme reste a `theo-archi`.

## 5. Ce que je demande a `marc-dev`

1. **Item 6** (`:91-94`) : remplacer par un item de la poule qui ne se resout pas avec `cp-qlm-cycle-de-vie`. Proposition :
   *"Dans la vie de la poule, quelle étape vient juste après le poussin ?"*, bonne reponse *"la poule"*, distracteurs *"l'œuf"*
   (famille a) et une etape d'un autre animal (famille b), indice qui ne parle pas de la sortie de l'oeuf.
2. **Items 3, 7, 11** (`:72`, `:100`, `:128`) : aucune etape d'animal dans un item de la plante. Deux etapes de la plante, hors de
   leur place et non voisines de la bonne reponse (familles *a, a*). Propositions : item 3 *"la fleur"*, *"le fruit"* ; item 7
   *"la graine"*, *"le fruit"* ; item 11 *"la fleur"*, *"le fruit"*.
3. **Item 12** (`:135`) : remplacer *"fleur, graine, plante"* par une suite qui n'est pas une rotation du bon ordre :
   *"graine, fleur, plante"* ou *"fleur, plante, graine"*.
4. **Item 15** (`:156`) : remplacer *"poule, œuf, poussin"* par *"poule, poussin, œuf"* ou *"œuf, poule, poussin"*.
5. **Garde** : la section 3, sous la forme que `theo-archi` donnera, ou la mention *"non garde"*.
6. **Commentaire du paquet** (`:41`) : la famille c devient *"les trois memes etapes, dans un ordre qui n'est pas celui du cycle,
   meme en le faisant tourner"*. Les declarations `TRV-CE1-2` des items 3, 7 et 11 deviennent *a, a*.

Le nombre d'items ne change pas (15) : l'entree de rang garde ses totaux. Les indices de 2.2 vont au `relecteur-pedagogique`.

**Ce dont je reste la seule garde sur ce paquet, apres correction** : l'exactitude de chaque ordre contre ma liste ; `NRC-CE1-1`
sur le sujet ; `CYC-1` ; la plausibilite des distracteurs ; la methode de chaque indice ; l'operation mentale de la tache *ordre*
(`FER-3`). Aucune n'est couverte par un test.

## 6. Cout reel de `P0`

**Au bareme.** Mon bareme : 1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*
(`reponse-nadia-palier-5-qlm-marge-instance-neuve-horloge.md:133`). La seule unite qui s'y ajoute avant l'audit est une seconde
lecture avant `verify`, a 0,5 sur la marge (`docs/backlog/backlog.md:23991`).

- A cette ecriture : **1,0**, une lecture, aucune seconde lecture.
- La correction de `marc-dev` n'est pas faite : son cout n'est pas mesurable aujourd'hui.
- Si sa correction demande une seconde lecture : **1,5**. `P0` ne depasse 1,67 qu'a une troisieme lecture.
- **Seuil de 1,67 : non depasse a cette ecriture.** Je le reecris si une seconde lecture le rapproche.

**A la consommation** (compteurs de tokens des transcriptions de sous-agents, lus par moi, aucune conversion en bareme n'existe :
`outils/rapport-tokens.py` n'en fait aucune) :

| Geste | Fenetre (UTC) | Tours | Output | Cache lu | Cache ecrit |
|---|---|---|---|---|---|
| Premier jet de `marc-dev` (pas `P0`, donne en repere de l'instance neuve) | 08:56 a 09:22 | 99 | 224 748 | 19 592 360 | 660 638 |
| Cette lecture, partielle (mesuree avant l'ecriture de ce fichier et le commit) | 09:40 a 09:50 | 36 | 47 966 | 2 878 630 | 278 783 |
| Correction de `marc-dev` | - | - | non faite | - | - |

**Ce que je ne peux pas dire** : si la consommation depasse 1,67. Aucune regle ne convertit des tokens en bareme, et je n'en invente
pas. La projection contre le plafond reste a `camille-po` (A.244).

## 7. Un fait de procedure, signale

`planning.md:2440-2442` : a `77fc8f8e`, la ligne du premier jet (lot, commit, heure) n'est pas inscrite ; le tableau dit *"(aucun
premier jet commite)"*. L'ordre de `planning.md:2435` met l'inscription (b) avant ma lecture (c). Aucun `verify` complet n'a ete
lance par moi ; le test unique de la section d'en-tete n'en est pas un. A `sofia-sm`.

## 8. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 5, points 1 a 4 et 6 |
| `theo-archi` | la forme de la garde de la section 3 ; 4.3 pour sa forme de la tache *voisine* |
| `relecteur-pedagogique` | les indices des items 5, 11, 14 (2.2) |
| `sofia-sm` | section 7 ; le chiffre de `P0` est en section 6, le seuil n'est pas depasse |

## 9. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-26.
