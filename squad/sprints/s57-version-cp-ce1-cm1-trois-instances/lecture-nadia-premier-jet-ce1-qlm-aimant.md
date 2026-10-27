# s57 - lecture avant `verify` du premier jet de `ce1-qlm-aimant` (`S57-BR-QLM-CE1-06`)

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : debut 2026-10-27 12:08:48 (UTC+01:00), ecriture 12:27:04, lues par `date` ; fin en section 8.
- **Arbre** : branche `s57-p5-nadia-lecture06`, base `round-two` a `bdfed668`, lue par `git rev-parse`.
- **Commit lu** : `c86148d1` (`[MARC]`, 2026-10-27 11:35:56 +0100), premier jet de `marc-dev`, fusionne dans `round-two`. Sa base
  contient mon `c9405891` (trois points, `R1`) et le `659b218e` de `theo-archi` (dernier ajustement, `G9`, `G10`) : lu par
  `git merge-base --is-ancestor`.
- **Type** : lecture avant `verify` (`QC7`). **Aucun Done, aucun verdict de lot**, aucun identifiant neuf, aucune condition neuve.
  Je n'ai modifie ni le paquet, ni les tests.
- **Audit degrade, dit** : rien n'est joue a l'ecran, le paquet n'est pas au catalogue (`QC3`). J'ai joue chaque item sur le papier,
  comme un enfant de CE1, trois fois : avec la competence, avec la croyance fausse *"l'aimant attire les metaux"*, et en lisant
  *papier d'aluminium* comme du papier, ce que fait un enfant qui ne sait pas que l'aluminium est un metal (mon 1.2 du 2026-10-26).
- **Lu** : `ce1-qlm-aimant.yaml` (12 items) ; `PaquetCe1QlmAimantTest.java` ; le jeu fautif
  `contreexemple/PaquetCe1QlmAimantTest/contenu/aimant-fautif.yaml` (sondes 44 a 48) ; l'entree de rang du paquet ; story
  `S57-BR-QLM-CE1-06` (criteres 1 a 10, ses blocs de corrections du 2026-10-27) ; mes arbitrages de `-06`
  (`arbitrage-nadia-premiers-jets-ce1-qlm-06-08.md` 1.1 a 1.7, `arbitrage-nadia-contradictions-ce1-qlm-06-08.md` 1 et 4.1,
  `arbitrage-nadia-dernier-lot-ce1-qlm-04-a-12.md` 4 et 6.3, `arbitrage-nadia-trois-points-ce1-qlm-04-06-10.md` 1, 2, 5.1) ;
  `reponse-nadia-aim-ce1-2-q7.md` ; `avis-theo-palier-5-exemption-aim-ce1-2.md` ; les avis de gardes de `theo-archi` sur `-06`
  (gardes 2.0 et 2.1 ; ajustements 1 ; dernier ajustement 4) ; `DEC-0120` A.273 ; ma lecture de `-04` ; les voisins
  `cp-qlm-matiere` et `cm1-sciences-melanges`, et un `grep` d'*aimant* sur les paquets `cp-*`.
- **Mesures lancees** :
  - recompte par script (Python, hors du depot, non commite) : mots par la regle de `PaquetLu.mots`, longueur en caracteres par la
    comparaison stricte de `ChaquePaquetDeQuestionnerLeMondeDeCe1TientLesConditionsTransversesTest.java:198-199`, objets partages
    entre groupes, composition de B, groupes qui portent *fil de fer* ou *papier d'aluminium* ; sur le paquet, puis sur une copie
    portant mes corrections ;
  - `bash outils/mvn-jdk21.sh -B -q -pl adapters/adapter-content -am test -Dtest=PaquetCe1QlmAimantTest
    -Dsurefire.failIfNoSpecifiedTests=false`, JDK 21.0.10 (propriete `java.version` du rapport) : **48 tests, 0 echec, 0 erreur**.
    Perimetre : ce seul test, module `adapter-content` et ses dependances, sur le premier jet. Ce n'est ni un `verify`, ni la porte
    technique, et il n'a pas tourne sur mes corrections.

## Resume

| Point | Reponse |
|---|---|
| Items | **8 justes, 4 a corriger, 0 refuse en l'etat** (section 2) |
| Autocontrole A.273 | **a** (indice qui donne la reponse) : aucun item ; une correction de vocabulaire (item 9). **b** (distracteur ecarte sans la competence) : 3 items (5, 6, 12), par le mot *papier* ; l'item 6 aussi par la taille. **c** (source circulaire) : aucune |
| Proprietes que je garde seule | exactitude des 16 objets : **tenue** ; regle 3 de A (forme, taille, usage) : **un item** (6) ; *doré* : **absent** ; les quatre cas d'`AIM-CE1-2` hors garde : **aucun** ; operation mentale de `FER-3` : **tenue** ; matiere des distracteurs dans l'indice : **aucune** ; `QMT-CE1-2`, `CYC-1`, `NRC-CE1-1`, `TRV-CE1-6` : **tenus** (section 1) |
| Doutes et points de `marc-dev` | reponses en section 3 ; sa regle du bon groupe *seul plus court* est **tenue** (2 sur 4, au plafond) |
| Reserve neuve, datee | en caracteres, le bon groupe est le plus court dans les 4 items de B ; je ne durcis pas ma regle en mots (1, dernier point) |
| Reouverture d'une formulation | **aucune** dans la story ; je corrige une phrase de mon propre arbitrage (section 6) |
| Cout | **1,0 au bareme a cette ecriture** ; 1,5 si mes corrections appellent une seconde lecture (section 7) |

---

## 1. Ce que j'ai verifie avant les items

- **Exactitude des seize objets (`QMT-CE1-2`, je suis la seule garde)** : le clou, le trombone, l'epingle, l'aiguille, l'agrafe et le
  fil de fer sont en fer ou en acier ordinaire et sont attires ; le papier et la barquette en aluminium, le fil de cuivre ne le sont
  pas ; les sept objets sans metal ne le sont pas. **Tenue.** Aucun objet exclu, aucun objet en or, ni *acier* ni *doré* dans un
  champ (lu a la main sur les 60 textes des 12 items, en plus de `g2`).
- **Source (defaut c)** : les lignes de classe citent `AIM-CE1-1` (instruction, 3.6) et ma regle 6, deux textes du depot qui
  affirment les faits. C'est ce que ma regle 6 prescrit. **Pas circulaire.**
- **`AIM-CE1-2`, les quatre cas que la garde ne voit pas (je suis la seule garde)** : aucune paraphrase sans *attirer* (*"colle"*,
  *"tient"*), aucun metal nomme presente comme attire, aucune designation hors des quatre formes, aucune negation fausse sans
  designation, aucun pronom qui reprend la regle fausse. Aucun indice ne dit d'ecarter ce qui n'est pas en metal. La premisse
  *"Tous ces objets sont en métal."* suivie de la question de B dit le contraire de la croyance fausse : tous sont en metal, et un seul
  groupe est attire en entier. **Tenu.**
- **Le quart, par le motif et non seulement par `g4`** : 8 items sur 12 comptent par la lettre de ma regle 1 (A : 1, 3, 6, 7 ; B : 2,
  5, 9, 12). J'ai rejoue ces huit items avec la croyance fausse : elle ne trouve la bonne reponse dans aucun. **Mais trois d'entre
  eux (5, 6, 12) se resolvent par une seconde lecture fausse, que la premisse ne bloque pas** : l'enfant lit *papier* dans *papier
  d'aluminium* et l'ecarte comme du papier. Section 2.1. Apres correction, les huit servent le piege.
- **`FER-3`, deux operations (`QC9`, je suis la seule garde)** : en A, l'enfant juge trois objets et prend celui qui est seul de son
  cote ; en B, chaque groupe distracteur porte un objet en aluminium ou en cuivre, et il faut juger au moins deux objets non attires
  dans deux groupes differents pour garder le bon. Aucun item de B ne se resout par un seul jugement. **Tenue sur les 12 items**, et
  sur les items corriges.
- **Regle 3 de A, forme, taille, usage (je suis la seule garde)** : 7 items sur 8 tenus ; l'item 6 ne l'est pas (2.2).
- **Regle 3 de B, partage d'objets** : dans les 4 items, chaque paire de groupes partage exactement un objet [calcul par script] :
  chaque groupe partage deux objets avec les deux autres, le bon groupe ne se reconnait pas ainsi. **Tenue**, et sur les items
  corriges.
- **`CYC-1` (je suis la seule garde)** : aucun paquet de CP ne parle d'aimant (`grep`) ; `cp-qlm-matiere` dit le clou et la
  casserole *en métal*, ce que `-06` ne contredit pas ; `cm1-sciences-melanges` fait separer des *morceaux de fer* par l'aimant,
  coherent avec la liste. **Tenu.**
- **`NRC-CE1-1` au-dela du texte (je suis la seule garde)** : `cp-qlm-matiere` apprend la matiere d'un objet (*"En quelle matière
  sont-ils faits ?"*) ; aucun item de `-06` ne demande une matiere, tous demandent ce que l'aimant attire. Ce que le CP apprend
  (*le clou est en metal*) donne la croyance fausse, pas la reponse. **Tenu.**
- **`TRV-CE1-6`** : aucun prenom, aucun objet qui appartient a l'enfant, aucun foyer ; la barquette et le papier d'aluminium ne sont
  rattaches a aucune cuisine. **Tenu.**
- **Lisibilite CE1, `LON-CE1-1`, `-2`, `-5`** : enonces de 6 a 14 mots, une ou deux phrases ; propositions de 1 a 6 mots ; indices de 7
  a 11 mots (`PaquetLu.mots`, par script). *Attire-t-il* et *n'attire-t-il pas* sont des interrogations inversees que le CE1 lit ; la negative est la plus
  difficile, et elle est dans 3 items sur 12. *Agrafe*, *trombone*, *barquette*, *cuivre* sont des mots que l'enfant rencontre ;
  *cuivre* et *aluminium* sont la competence elle-meme. Seule exception : *passe en revue* (item 9, 2.3).
- **`TRV-CE1-2`, bonne reponse strictement la plus longue** : 2 items sur 12 (3, 6), mesure par script, comme `marc-dev` ; **2 apres
  mes corrections** (3, 6).
- **Reserve neuve, datee du 2026-10-27, pas un durcissement de `G10`.** En caracteres, ce que l'oeil voit, le bon groupe est
  strictement le plus court dans **les 4 items de B** (24 contre 39 et 28 ; 30 contre 37 et 34 ; 25 contre 37 et 29 ; 25 contre 40 et
  36) [calcul par script], et la bonne reponse l'est dans **7 items sur 12** (A : 1, 7, 10). La cause est la liste que j'ai ecrite : les
  noms des trois objets en metal non attires sont les plus longs de la liste, et *fil de fer* est le seul nom long d'un objet attire.
  Ma regle de 6.3 est ecrite en mots, `marc-dev` la tient (2 sur 4, au plafond) et mon propre exemple (item 2) est le plus court en
  caracteres : **je ne releve pas ma barre apres coup**. Je regarderai a l'ecran si un enfant prend le plus court ; si oui, ce sera une
  reserve d'audit, a traiter par la liste, pas par un item. Mes corrections ne changent ni l'un ni l'autre compte (4 sur 4, 7 sur 12).
- **Accents, orthographe, ponctuation** : au `relecteur-pedagogique` (`DEC-0002`, `DEC-0056`). Je n'ai rien vu qui manque.

## 2. Verdict par item

Numerotation : ordre du fichier. *Juste* : rien a changer. *A corriger* : correction demandee avant `verify` ; restee telle quelle a
l'audit, ce serait une reserve. Aucun item n'est refuse en l'etat : aucun fait n'est faux, aucun item n'a deux reponses defendables.
*Compte* : l'item compte pour le quart (regle 1).

| # | Ligne | Tache | Bonne reponse ; distracteurs | Verdict | Motif |
|---|---|---|---|---|---|
| 1 | `:91` | A, *attire*, premisse | *le clou* ; *le papier d'aluminium*, *le fil de cuivre* | **juste**, compte | Mon exemple de 5.1. La croyance fausse ne choisit pas ; ecarter le papier comme du papier laisse encore le cuivre a juger. Distracteurs : aucun mot, aucune forme ni usage communs. Indice : ma methode, mot pour mot |
| 2 | `:96` | B | *fil de fer, clou, agrafe* ; *fil de fer, épingle, papier d'aluminium*, *clou, épingle, fil de cuivre* | **juste**, compte | Mon exemple de 6.3. 5, 6, 5 mots. Ecarter le papier laisse le cuivre a juger. Indice : une methode |
| 3 | `:101` | A, *n'attire pas*, premisse | *le fil de cuivre* ; *le fil de fer*, *l'agrafe* | **juste**, compte | Mon exemple de 5.1. Les trois sont des fils ou faits d'un fil (l'agrafe est un fil plie) : aucun trait propre aux deux distracteurs (doute de `marc-dev`, section 3) |
| 4 | `:106` | A, *attire* | *le trombone* ; *le papier d'aluminium*, *la gomme* | **juste**, ne compte pas | L'item de `camille-po`, admis pour `FER-1`. Indice : une methode (doute a, section 3) |
| 5 | `:111` | B | *fil de fer, trombone, aiguille* ; *trombone, épingle, papier d'aluminium*, *aiguille, clou, papier d'aluminium* | **a corriger** | **Les deux groupes distracteurs portent *papier d'aluminium*** : l'enfant qui le lit comme du papier ecarte les deux et trouve le bon groupe sans rien savoir de l'aluminium ni du cuivre (2.1) |
| 6 | `:116` | A, *n'attire pas*, premisse | *le papier d'aluminium* ; *le clou*, *le trombone* | **a corriger** | **Deux motifs** : la bonne reponse est le seul nom qui porte *papier*, et la croyance fausse plus cette lecture la trouvent (2.1) ; les deux distracteurs sont deux petits objets durs contre une feuille, une taille et une forme que la bonne reponse n'a pas, la lettre de ma regle 3 (2.2) |
| 7 | `:121` | A, *attire*, premisse | *le fil de fer* ; *la barquette en aluminium*, *le fil de cuivre* | **juste**, compte | Mon exemple de 1.2 (avec la barquette). *Fer* dans le nom est la competence. Distracteurs : un recipient et un fil, rien de commun |
| 8 | `:126` | A, *n'attire pas* | *la gomme* ; *le clou*, *l'agrafe* | **juste**, ne compte pas | Deux objets en fer : admis au negatif (1.1 du 2026-10-27). Usages differents (bricolage, bureau) ; la gomme est petite aussi, aucune taille ne la met a part (doute b) |
| 9 | `:131` | B | *épingle, agrafe, trombone* ; *épingle, clou, barquette en aluminium*, *trombone, clou, fil de cuivre* | **a corriger** | Contenu juste, 3, 5, 5 mots. **L'indice dit *"Passe en revue"*** : une locution qu'un enfant de CE1 ne connait pas (2.3) |
| 10 | `:136` | A, *attire* | *l'épingle* ; *la règle en plastique*, *la bille en verre* | **juste**, ne compte pas | Item de `FER-1`. Les deux noms portent leur matiere, comme ma regle 6 le veut ; les ecarter, c'est savoir que l'aimant n'attire ni le plastique ni le verre, la competence |
| 11 | `:141` | A, *n'attire pas* | *l'élastique* ; *l'aiguille*, *le trombone* | **juste**, ne compte pas | Les trois sont fins : la finesse ne met pas l'elastique a part. Ce que les distracteurs ont en commun est d'etre en fer, ce que la question demande (doute b) |
| 12 | `:146` | B | *aiguille, agrafe, épingle* ; *aiguille, fil de fer, papier d'aluminium*, *agrafe, trombone, papier d'aluminium* | **a corriger** | **Meme motif que l'item 5** : *papier d'aluminium* dans les deux groupes distracteurs (2.1) |

### 2.1 Items 5, 6, 12 : le mot *papier*

Mon motif du 2026-10-26 (1.2) : *"un enfant de CE1 ne sait pas que le papier d'aluminium est un metal : pour lui, c'est du papier"*.
La premisse le lui dit, mais un enfant de 7 ans croit souvent ce qu'il lit dans le nom plus que ce que dit la phrase d'avant. Je joue
alors la croyance fausse avec cette lecture :

- **item 6** : *"Lequel l'aimant n'attire-t-il pas ?"* ; le clou et le trombone sont en metal, donc attires ; le papier ne l'est pas :
  **bonne reponse trouvee**, sans savoir que l'aluminium n'est pas attire ;
- **items 5 et 12** : les deux groupes distracteurs portent *papier d'aluminium* ; l'enfant les ecarte comme *"des groupes avec du
  papier"* : **bon groupe trouve**, sans juger le cuivre ni l'aluminium ;
- **items 1, 2** : un seul distracteur porte le papier ; l'ecarter laisse le fil de cuivre a juger. Le piege tient.

C'est le defaut **b** d'A.273 et le motif d'`AIM-CE1-2` (*"le piege est servi"*), pas une regle neuve. Les items 5, 6 et 12
tiennent la lettre de ma regle 1 et continuent de compter apres correction : le quart (8 sur 12) n'est pas en cause, la qualite de ces
trois items l'est.

**Correction d'un constat a moi.** Le 2026-10-27 (contradictions `-06`/`-08`, 1.1), j'ai ecrit, pour un item au negatif dont la bonne
reponse est *"en aluminium ou en cuivre"* avec la premisse : *"c'est l'item du quart le plus net"*. C'est vrai pour *la barquette en
aluminium* et *le fil de cuivre*, faux pour *le papier d'aluminium*, que l'enfant trouve comme du papier. L'item 6 est la lettre de ma
phrase ; la faute est dans ma phrase.

**Les corrections** (texte exact en section 4) :

- **item 5** : le second groupe distracteur devient *"aiguille, épingle, fil de cuivre"*. C'est l'item `Y` de `theo-archi` (sonde 48),
  deja verifie par lui. 5, 4, 5 mots ; chaque paire de groupes partage un objet (*trombone*, *aiguille*, *épingle*) ; le bon groupe porte
  *fil de fer* et un distracteur a moins de mots ;
- **item 12** : le premier groupe distracteur devient *"aiguille, trombone, fil de cuivre"*. 3, 5, 4 mots ; paires (*aiguille*,
  *agrafe*, *trombone*). *Fil de fer* ne peut pas rester dans un groupe distracteur de cet item : il n'y tient qu'a cote du papier
  (*"fil de fer, fil de cuivre"* ou *"fil de fer, barquette en aluminium"* avec un troisieme objet font 7 mots) ;
- **item 6** : 2.2.

### 2.2 Item 6 : la taille et la forme, puis la correction

*Le clou* et *le trombone* sont deux petits objets durs ; *le papier d'aluminium* est une feuille. Un enfant qui cherche l'objet a
part le trouve sans rien savoir de l'aimant. Ma regle 3 refuse *"une forme, une taille ou un usage que la bonne reponse n'a pas"*. Ce
que j'ai admis au negatif (1.1 du 2026-10-27), c'est que les deux distracteurs soient en fer, pas qu'ils partagent une taille.

Ce que j'ai pese avant de choisir :

- *la barquette en aluminium* a la place du papier, memes distracteurs : un recipient parmi deux petits objets, meme defaut ;
- *le fil de cuivre* a la place du papier : les deux seuls items au negatif avec premisse (3 et 6) auraient la meme bonne reponse, une
  devinette des la deuxieme rencontre ;
- **retenu** : *la barquette en aluminium* ; *le fil de fer*, *l'épingle*. Un fil long qu'on plie, une petite epingle de couture, un
  recipient : aucune forme, aucune taille, aucun usage commun aux deux distracteurs ; aucun mot commun (`g9`). La premisse est vraie,
  l'item compte, la croyance fausse ne choisit pas, et aucun nom ne porte *papier*. Enonce, indice et familles (`c, c`) ne changent
  pas.

### 2.3 Item 9 : *"Passe en revue"*

*Passer en revue* est une locution que l'enfant de CE1 n'a pas apprise ; il lit *passe* et *revue* et ne comprend pas la methode. Le
contenu de l'item est juste. Remplacement : *"Regarde les trois objets de chaque groupe, un par un."* (10 mots), une methode que
l'enfant applique a chaque objet, sans matiere et sans *intrus* ; elle n'est la copie d'aucun autre indice du paquet.

## 3. Les doutes et les points de `marc-dev`

| Point | Reponse | Motif |
|---|---|---|
| Doute b, item 6 (clou, trombone ; une feuille plate parmi deux objets rigides) | **a corriger** | 2.2 |
| Doute b, item 8 (clou, agrafe) | **juste** | La gomme est petite aussi : la taille ne la met pas a part. Ce qui reste commun aux deux distracteurs est d'etre en fer, admis au negatif. Usages differents |
| Doute b, item 11 (aiguille, trombone ; l'elastique parmi deux objets fins) | **juste** | L'elastique est fin aussi. Meme motif que l'item 8 |
| Doute b, item 3 (fil de cuivre parmi fil de fer et agrafe, deux fils) | **juste** | Le trait *fil* est partage par la bonne reponse et un distracteur, pas par les deux distracteurs seuls ; l'agrafe est un fil plie. Rien ne met le fil de cuivre a part, sinon le cuivre |
| Doute b, items 4 et 10 sans premisse | **confirme : ils ne comptent pas, et ils sont justes** | Ils servent `FER-1` ; la croyance fausse les resout, c'est voulu (1.3 du 2026-10-26) |
| Doute a, indices des items 4 (*"touche chaque objet"*) et 8 | **justes** | Ma regle 4 demande une methode que l'enfant applique a chaque objet. Sur cette ligne, la competence est la question elle-meme : toute methode ressemble a la question. Aucun indice ne nomme ni ne designe la bonne reponse, aucune matiere, aucun *intrus*. Mon exemple de la regle 4 a la meme nature |
| Quart : 8 sur 12 ; chaque paire de groupes de B partage exactement un objet | **confirme**, par script | Apres correction : 8 sur 12 (l'item 6 compte encore), paires a un objet dans les 4 items |
| *Fil de fer* par item de B : 2, 1, 0, 1 ; `S` = 2 sur 4 ; dans 2 et 5 un distracteur a autant de mots ou moins : la regle est-elle tenue ? | **oui, tenue** | Items 9 et 12 : bon groupe seul plus court (2 sur 4, la moitie, ce que ma regle admet). Item 2 : il porte *fil de fer*, 5 mots contre 6 et 5. Item 5 : il porte *fil de fer*, 5 contre 4 et 4. **Apres correction** : `S` reste 2 sur 4 ; item 5, 5 contre 4 et 5 ; *fil de fer* par item 2, 1, 0, 0. Sur les quatre items, *fil de fer* ne decide que dans l'item 5 (seul dans le bon groupe) ; il est dans deux groupes a l'item 2 et absent des items 9 et 12. Ce n'est pas un signe du bon groupe ; je le regarderai a l'ecran |
| `TRV-CE1-2` : strictement la plus longue dans 2 items sur 12 (3, 6) | **confirme**, par script | 2 apres correction (3, 6) |
| `g3`, le meme item dans les deux colonnes du tableau de `theo-archi` ; `marc-dev` suit le fond (deux non attires refuses) | **confirme** | Ma regle 2 de A : *"une seule proposition attiree (ou non attiree, selon la question)"*. *La gomme* ; *le clou*, *la bille en verre* au negatif a deux non attires : refuse. La colonne *doit passer* de l'avis est a corriger par `theo-archi` (`T1`) |
| Source : `g1` ne refuse qu'une source qui nomme un test ; je prescris des fichiers du depot | **confirme** | Ma regle 6 nomme ces deux textes. Ce que le test ne lit pas (une source qui affirme ce qu'elle ne dit pas), je l'ai lu : chaque ligne dit ce que son texte dit |
| En-tete : 16 objets, dont 3 inemployes (bouchon en liege, feuille de papier, pate a modeler) | **admis** | La liste fermee est un plafond, pas un quota. Rien a ajouter ni a retirer |
| `g8` lit la matiere par le nom de l'objet | **admis** | Sur la liste fermee, le nom donne la meme matiere que la classe ; la forme est a `theo-archi` |
| Aucun indice n'ecrit *metal* | **confirme** | Et aucun ne dit d'ecarter ce qui n'est pas en metal |

## 4. Ce que je demande a `marc-dev`

Texte exact. Rien d'autre ne change : ni les enonces, ni les autres propositions, ni les autres indices, ni les declarations
`TRV-CE1-2` (les familles des quatre items restent `d, d`, `c, c`, `d, d`, `d, d`), ni l'en-tete. Aucun texte neuf ne porte d'espace
avant `?` ou `:`.

**Dans `adapters/adapter-content/src/main/resources/contenu/ce1-qlm-aimant.yaml`** :

| Point | Item, ligne | Texte actuel | Texte a ecrire |
|---|---|---|---|
| 1 | 5, `:113` | `distracteurs: ["trombone, épingle, papier d'aluminium", "aiguille, clou, papier d'aluminium"]` | `distracteurs: ["trombone, épingle, papier d'aluminium", "aiguille, épingle, fil de cuivre"]` |
| 2 | 6, `:117` | `bonneReponse: "le papier d'aluminium"` | `bonneReponse: "la barquette en aluminium"` |
| 3 | 6, `:118` | `distracteurs: ["le clou", "le trombone"]` | `distracteurs: ["le fil de fer", "l'épingle"]` |
| 4 | 9, `:134` | `indice: "Passe en revue les trois objets de chaque groupe."` | `indice: "Regarde les trois objets de chaque groupe, un par un."` |
| 5 | 12, `:148` | `distracteurs: ["aiguille, fil de fer, papier d'aluminium", "agrafe, trombone, papier d'aluminium"]` | `distracteurs: ["aiguille, trombone, fil de cuivre", "agrafe, trombone, papier d'aluminium"]` |

6. **Comptes et rang** : 12 items, 36 propositions, toutes textuelles ; les deux lignes de compte de l'en-tete (8 et 4) et l'entree de
   rang ne changent pas (`QC10`). Tous les objets des propositions neuves sont dans la liste et ont leur ligne de classe. Aucune sonde
   du jeu fautif ne lit un item du paquet reel par son texte ; `s2` retire la ligne du fil de cuivre, encore employe.
7. **Rapport de correction** : pour chaque item de B, le nombre de groupes qui portent *fil de fer* (attendu : 2, 1, 0, 0), comme ma
   regle 2 le demande.

**Recompte, sur une copie portant les points 1 a 5** [calcul par script] : items 5 et 12, composition de B tenue (trois objets en fer ;
deux en fer et un en aluminium ou en cuivre), groupes de 6 mots au plus, chaque paire de groupes partage exactement un objet,
*papier d'aluminium* dans un seul groupe distracteur ; item 6, une seule proposition non attiree, premisse vraie, compte pour le quart ;
quart 8 sur 12, dont 4 items de A ; `S` 2 sur 4 ; bonne reponse strictement la plus longue 2 sur 12 ; strictement la plus courte en
caracteres, 7 sur 12, inchange. Le test du paquet n'a pas tourne sur cette copie : c'est la mesure de `marc-dev` apres correction.

**Ce dont je reste la seule garde sur ce paquet, apres correction** : l'exactitude de chaque objet contre sa classe ; la forme, la
taille et l'usage partages par deux distracteurs de A ; qu'aucun nom ne donne la reponse par une lecture fausse (*papier*) ; qu'un
indice soit une methode et que son vocabulaire soit du CE1 ; que *fil de fer* ne devienne pas le signe du bon groupe ; la longueur en
caracteres du bon groupe (reserve, section 1) ; *doré* ; les quatre cas d'`AIM-CE1-2` que la garde ne voit pas ; l'operation mentale
de `FER-3` ; `CYC-1`, `NRC-CE1-1` sur le sujet, `TRV-CE1-6`. **Aucune n'est couverte par un test**, sauf ce que `T2` ci-dessous
encoderait.

## 5. Ce qui revient a `theo-archi` (forme), une ligne par garde

| Repere | Garde | Ajustement |
|---|---|---|
| `T1` | `g3` (`avis-theo-palier-5-gardes-ce1-qlm-06-08.md`, 2.1) | Le test est juste ; ton tableau ne l'est pas : la colonne *doit passer* porte *n'attire pas, la gomme ; le clou, la bille en verre*, deux non attires, que la colonne *doit refuser* porte aussi. A corriger dans ton avis (par exemple *la gomme ; le clou, l'agrafe*). Aucun changement de code |
| `T2` | garde neuve, si tu la juges encodable | Par egalite sur la liste fermee : refus d'un item de A *n'attire pas* dont la bonne reponse est *papier d'aluminium* ; refus d'un item de B dont les deux groupes distracteurs portent *papier d'aluminium*. Sondes refusees : les items 6, 5 et 12 du premier jet ; passent : les items 1, 2 et 6 corrige. Sinon *"non garde : `AIM-CE1-2`"*, je le lis |
| `T3` | `G10` | Inchangee, en mots. Aucune garde en caracteres : c'est ma reserve de jeu (section 1), pas une regle |
| `T4` | `g8`, `g1` (source) | Rien : la lecture de `marc-dev` (matiere par le nom, source qui nomme un test refusee) tient sur la liste fermee |

## 6. Cette lecture rouvre-t-elle une formulation ?

**Non, dans la story.** Aucun texte de la story ni des regles reportees n'est a reecrire. Les corrections des items 5, 6 et 12
appliquent le defaut **b** d'A.273, le motif d'`AIM-CE1-2` et la lettre de ma regle 3 (taille, forme) ; celle de l'item 9 applique ma
methode (vocabulaire du niveau). La reserve de longueur en caracteres est datee et nouvelle ; elle ne durcit pas ma regle de 6.3.

**Une phrase de mon propre arbitrage est corrigee ici**, sans etre reportee nulle part : *"c'est l'item du quart le plus net"*
(contradictions `-06`/`-08`, 1.1) ne vaut pas pour *le papier d'aluminium* (2.1). Cette phrase n'est pas dans la story. Rien a
reporter par `camille-po`.

## 7. Cout

**Au bareme** (1,0 par lot, *"lecture avant `verify` et correction qui la suit comprises"*) : **1,0** a cette ecriture. Mes
corrections touchent quatre items : si `marc-dev` les applique telles qu'ecrites, leur relecture est une seconde lecture, **0,5 hors
marge** (`DEC-0120` A.272, A.270 point 4), soit **1,5**. Je ne deduis aucun seuil que les textes ne posent pas, et je ne convertis pas
une duree en bareme.

## 8. Duree et verifications

**Duree**, mesuree par `date` : debut **12:08:48**, fin **12:33:28**, apres les verifications et avant le commit, soit
**24 min 40 s**, dont la mesure du test du paquet et les executions du garde-fou des identifiants. Aucun seuil de duree ne
s'applique a une lecture.

**Verifications avant commit**, ce fichier present dans l'arbre et indexe, base `bdfed668`. Il n'ecrit ni ne renomme aucun
identifiant de condition : `T1` a `T4` sont des reperes de demande.

- `bash outils/mvn-jdk21.sh -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`, telle quelle :
  *"BUILD FAILURE"* sur `recreo-domain`, *"No tests matching pattern "ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest" were
  executed!"*. Le garde-fou n'a pas ete lance ; meme symptome que dans mes fichiers precedents.
- La meme commande avec `-Dsurefire.failIfNoSpecifiedTests=false` : *"Tests run: 10, Failures: 0, Errors: 0, Skipped: 0"*,
  *"BUILD SUCCESS"*. Perimetre : ce seul test, module `bootstrap` et ses dependances ; ni un `verify`, ni la porte technique.
- `.github/scripts/verifier-chemins.sh`, entree standard fermee (`< /dev/null`) : *"Aucun chemin de poste de travail ni nom de
  compte dans les fichiers verifies."*
- Aucun autre job de `.github/workflows/ci.yml` lance.

## 9. Routage

| A qui | Quoi |
|---|---|
| `marc-dev` | section 4, points 1 a 7 |
| `theo-archi` | section 5, `T1` a `T4` |
| `sofia-sm` | le cout est en section 7 ; la seconde lecture, s'il y en a une, s'inscrit sur la ligne hors marge |
| `camille-po` | rien (section 6) |

## 10. Ce que ce document ne fait pas

Il ne prononce aucun Done et aucun verdict de lot, n'ecrit aucune condition neuve, n'alloue aucun identifiant, ne modifie ni le
paquet, ni un test, ni le catalogue, ni `DEC`, ni matrice, ni registre, ni backlog.

**Signe** : `nadia-prof`, s57, palier 5, 2026-10-27.
