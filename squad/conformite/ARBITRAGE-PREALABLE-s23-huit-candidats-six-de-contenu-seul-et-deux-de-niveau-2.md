# Arbitrage prealable de s23 - les huit candidats poses par `DEC-0057`, section 2

- **Auteure** : `nadia-prof`
- **Date d'ouverture** : 2026-08-30
- **Demande** : `DEC-0057`, section 2 - six candidats de contenu seul jamais arbitres
  individuellement, et deux candidats de niveau 2 de `DEC-0051` poses le 2026-08-28 et jamais
  repondus
- **Ce que ce document est** : huit arbitrages **prealables**, rendus avant qu'une ligne de contenu
  n'existe. Il ne prononce **aucun Done pedagogique**, ne fait bouger **aucune ligne** de
  `squad/programme/MATRICE-couverture.md`, et n'ouvre aucune story - c'est `camille-po` qui en fait
  du backlog.

## 0. Ce que j'ai reellement fait avant d'ecrire, et ce que je n'ai pas pu faire

**Audit degrade, et je le dis en premier parce que c'est la regle que je me suis donnee.** Aucun
des huit candidats n'existe en increment : il n'y a rien de neuf a jouer. Ce que j'ai joue, c'est
**la mecanique qui les porterait**, sur le depot construit depuis `round-two` a `890732f`, lance en
local avec le jar de `bootstrap` :

- `bandit-manchot-comprehension-cm1` : un item complet, deux erreurs volontaires puis la bonne
  reponse. Constate : premiere erreur, verdict *"Ce n'est pas ca. Essaie encore !"* et indice ;
  deuxieme erreur, la reponse est revelee et l'enfant doit la presser pour avancer. **Un enfant
  bloque avance ; c'est le comportement sur lequel je m'appuie dans mes conditions d'indice.**
- `bandit-manchot-complements-10-cp`, `bandit-manchot-comparer-nombres-cp`,
  `bandit-manchot-problemes-additifs-cp`, `bandit-manchot-mot-manquant-cp` : premiers ecrans, pour
  reprendre la mesure de ce qu'un item de CP demande reellement a lire.
- Le rendu du champ `texteSupport` sur un item de comprehension, dans un style distinct de
  l'enonce (`COMP-5`, livree a s22).

**Ce que cela ne remplace pas** : je n'ai vu aucun des items dont je parle ci-dessous, puisqu'ils
n'existent pas. Toutes mes conditions sont donc des conditions **a verifier a l'audit**, et aucune
n'est un constat sur du contenu.

### Un fait technique que j'ai verifie et sur lequel quatre de mes reponses reposent

`texteSupport` est une **cle facultative et generique** du type de contenu `question`
(`QuestionContentSourceFactory`, `Question`, `ADR-0050`) : elle n'est liee ni a un niveau, ni a un
paquet. Le gabarit de manche l'affiche des qu'elle est presente. **Les candidats qui demandent un
texte-support n'exigent donc aucune architecture neuve**, contrairement aux figures planes, qui
attendent depuis s19 une decision sur l'item visuel. C'est une difference de nature entre ces deux
familles, et elle explique une partie de mes verdicts.

### Ce que je corrige de mes propres constats avant de m'en servir

- **La porte de l'accentuation n'est pas la mienne.** `DEC-0056` a tranche le 2026-08-30, en
  lisant le texte primaire que j'avais moi-meme cite : l'amendement du 2026-08-04 a `DEC-0002` a
  ete redige **a ma demande**, pour sortir ce controle de ma seule porte, au motif exact que ma
  porte ne s'exerce qu'une fois par sprint. **Ma lecture du 2026-08-30 - "l'amendement suit la
  porte, il ne la deplace pas" - etait fausse, et mon refus de `S13-2` a ete prononce par une porte
  qui n'etait pas la mienne.** Le defaut etait reel, le juge ne l'etait pas. Je m'y range sans
  reserve, comme je l'avais ecrit. **Aucune condition de ce document ne porte sur l'orthographe,
  l'accentuation ou la ponctuation** : elles appartiennent au `relecteur-pedagogique`.
- **La reference que `DEC-0057` donne pour le refus du proxy de fraction est exacte quant au
  fichier et inexacte quant a la section.** Elle cite `ARBITRAGE-PREALABLE-s12-mathematiques-cm1.md`,
  section 2 ; la section 2 de ce fichier porte le perimetre du carre et du rectangle, et le refus
  du proxy d'encadrement vit dans la **section 1, sous "Proxy 2"**. Cela ne change aucune decision,
  et je le note parce qu'une reference qui pointe a cote se recopie.

### La lecon de s22 que j'applique ici, et comment

s22 a confronte mes vingt-huit conditions a du contenu reel : vingt-cinq tenues, **aucune n'a
exclu a tort du contenu legitime**, et les trois defauts trouves etaient de l'**imprecision** -
`COMP-3`, `D-4`, `D-5` avaient chacune deux lectures chiffrees, toutes deux dans les bornes. Ma
regle *"ecrire la propriete, pas l'exemple"* n'en protege pas. **Donc, dans tout ce qui suit :**

1. chaque condition chiffree nomme son **unite de compte** et sa formule d'arrondi, pour qu'elle
   n'ait qu'une seule lecture ;
2. **chaque clause qui filtre une population enumerable est mesuree contre cette population au
   moment ou je l'ecris**, avec sa date et son perimetre - c'est la regle que j'ai adoptee le
   2026-08-28 apres `SEG-1`, quatre fois trop large et jamais mesuree ;
3. quand deux de mes clauses se contraignent l'une l'autre, je verifie qu'elles restent
   **conjointement satisfaisables** avant de les poser.

### Ce sur quoi je reste la seule garde, et je le dis d'avance

Le garde-fou d'accentuation n'existe pas au moment ou j'ecris (`s23-theo` ne porte aucun commit).
Ma condition 1 de capacite n'est donc pas remplie et **la borne reste a trois paquets**, comme
`camille-po` l'a tenue.

Trois des six candidats que j'accepte portent une propriete du type de `COMP-2` - **qu'aucun
automate ne peut voir et qui se verifie item par item**. Je les marque nommement dans chaque
arbitrage. Ma condition d'**au plus un paquet de ce type par sprint** s'applique telle quelle : ce
n'est pas une preference, c'est ce qui a coute trois refus le 2026-08-29.

## Sommaire des huit verdicts

| # | Candidat | Verdict | Conditions |
|---|---|---|---|
| 1 | Comparer et ranger - moitie "ranger" (maths CP) | **ACCEPTE sous conditions** | `RAN-1` a `RAN-8` |
| 2 | Problemes a plusieurs etapes, 4 operations (maths CM1) | **ACCEPTE sous conditions** | `ETA-1` a `ETA-10` |
| 3 | Comprendre un texte narratif tres court (francais CP) | **ACCEPTE sous conditions** | `NAR-1` a `NAR-10` |
| 4 | Marques du pluriel les plus frequentes en `-s` (francais CP) | **ACCEPTE sous conditions** | `PLU-1` a `PLU-9` |
| 5 | Substituts d'un personnage (francais CM1) | **ACCEPTE sous conditions** | `SUB-1` a `SUB-10` |
| 6 | Mots de liaison simples (francais CM1) | **ACCEPTE sous conditions**, couverture partielle nommee | `LIA-1` a `LIA-10` |
| 7 | `contenu-repris/mots/cp-premiere-lettre.yml` sur "Identifier un son (phoneme) dans un mot" | **REFUSE** | - |
| 8 | `contenu-repris/fractions/cm1-compare.yml` sur la moitie "notion de fraction simple" | **REFUSE**, part servable nommee et arbitree en 8bis | `FRA-1` a `FRA-9` |

Le detail de chaque verdict suit, dans cet ordre. Chacun a ete commite des qu'il a ete rendu.

---

## 1. "Comparer et ranger des nombres jusqu'a 100" - moitie "ranger" (mathematiques CP)

**Verdict : ACCEPTE sous conditions `RAN-1` a `RAN-8`.**

### Ce que je verifie d'abord

La ligne est `en cours` depuis s09. La moitie "comparer" est validee pedagogiquement depuis le
2026-08-12 (`FICHE-bandit-manchot-comparer-nombres-cp.md`, second audit). Ce qui manque est nomme
depuis `DEC-0019` et repris dans la matrice : **"ordonner une suite de trois nombres ou plus"**.
C'est bien cette moitie-la que j'arbitre, et c'est elle qui ferait passer la ligne de `en cours` a
`couvert-valide`.

**Le geste est celui de la mecanique.** Ranger trois nombres, c'est choisir la liste bien rangee
parmi trois : presser une proposition parmi trois, sans rien de neuf. Je ne prends pas le raccourci
qui consisterait a faire "trouver le plus petit des trois", qui n'est pas ranger et qui ne serait
que la comparaison deja validee, servie une seconde fois.

### Conditions

- **`RAN-1` - Forme, et un seul sens sur tout le paquet.** L'item presente trois nombres ; chacune
  des trois propositions est une liste de ces trois memes nombres dans un ordre different. La
  consigne est constante sur tout le paquet et nomme un seul sens de rangement. **Le sens ne change
  jamais d'un item a l'autre** : un paquet qui alterne le croissant et le decroissant change
  silencieusement ce que l'enfant doit faire, ce que j'ai deja refuse sous `P-4` le 2026-08-14.
- **`RAN-2` - Bornes.** Les trois nombres sont des entiers de 0 a 100, deux a deux distincts dans
  un meme item.
- **`RAN-3` - Longueur d'une proposition.** Une proposition s'ecrit sous la forme `a, b, c` et ne
  depasse pas **12 caracteres, espaces comprises**. *Mesure de reference, faite le 2026-08-30 sur
  la population entiere des 470 propositions des dix paquets CP servis
  (`adapters/adapter-content/src/main/resources/contenu/cp-*.yaml`) : la proposition la plus longue
  jamais validee au CP fait 15 caracteres (`une trottinette`, `cp-comparer`). Avec trois entiers
  d'au plus 100, dont un seul peut avoir trois chiffres, le pire cas fait 11 caracteres. La borne
  de 12 est donc sous ce qui est deja valide.*
- **`RAN-4` - Les deux distracteurs sont derives, et le fichier nomme la derivation a cote de
  l'item.** Deux familles, et seulement celles-la :
  **(a)** les trois memes nombres dans le sens inverse de celui de la consigne ;
  **(b)** les trois memes nombres ranges en ne comparant que leur chiffre des unites.
  Un tiers doit pouvoir **recalculer** les deux distracteurs a partir des trois nombres et de la
  famille declaree. **Si la famille (b) reproduit la famille (a) ou la bonne reponse, le triplet
  est ecarte** : l'item n'aurait que deux propositions distinctes.
  *Mesure de cette clause d'exclusion contre la population qu'elle filtre, faite le 2026-08-30 :
  sur les **166 650** triplets d'entiers distincts de 0 a 100 - population entiere, pas un
  echantillon -, la collision ecarte **58 515 triplets (35,1 %)** et en laisse **108 135**. La
  clause n'assechera donc pas la matiere.*
- **`RAN-5` - Le premier nombre ne suffit jamais a decider, sur au moins la moitie du paquet.** Sur
  les `N` items du paquet, **au moins `arrondi superieur(N/2)`** items ont **au moins deux
  propositions qui commencent par le meme nombre**. *Unite de compte : l'item.* Motif : sans cette
  clause, un enfant qui regarde uniquement le premier nombre de chaque liste et choisit la plus
  petite reussit sans jamais ranger. C'est la meme famille de defaut que la reserve de `DEC-0021`.
  *Mesure de satisfaisabilite, meme population et meme date : parmi les 108 135 triplets retenus
  par `RAN-4`, **55 305 (51,1 %)** satisfont deja `RAN-5`. Une majorite d'items la tiendra sans
  effort particulier.*
- **`RAN-6` - Au moins un tiers des items opposent deux nombres de la meme dizaine.** Sur les `N`
  items, **au moins `arrondi inferieur(N/3)`** items contiennent deux nombres dont le chiffre des
  dizaines est le meme (par exemple 34 et 38). *Unite de compte : l'item.* Motif : sans cela, le
  rangement se fait sur le seul chiffre des dizaines et n'exerce pas la comparaison a l'unite pres.
  *Verification de satisfaisabilite conjointe, meme population et meme date : **13 275 triplets**
  satisfont `RAN-4`, `RAN-5` et `RAN-6` a la fois. `RAN-5` et `RAN-6` n'ont meme pas besoin de
  porter sur les memes items pour etre tenues toutes les deux.*
- **`RAN-7` - Indice.** Il donne la methode, jamais la liste. *"Cherche d'abord le plus petit des
  trois."* est un indice ; *"C'est 12, 25, 40."* n'en est pas un. **Dix mots au plus**, un mot
  etant une suite de caracteres sans espace.
- **`RAN-8` - Au moins vingt items**, et aucun item ne presente le meme triplet qu'un autre.

### Ce qui reste dans ma seule main sur ce paquet

**Presque rien, et c'est rare.** `RAN-1` a `RAN-6` et `RAN-8` sont toutes calculables a partir du
fichier : un test peut recalculer les deux distracteurs, compter les items ou deux propositions
commencent par le meme nombre, et compter les dizaines partagees. **Ce paquet n'est pas du type
`COMP-2`** et ne consomme pas ma condition d'au plus un par sprint. Je garde la lecture des
indices (`RAN-7`) et le jugement de niveau, rien de plus.

### Couverture

Si le paquet tient ces conditions et passe l'audit, la ligne `mathematiques | CP | Nombres et
calculs | Comparer et ranger des nombres jusqu'a 100` passe de `en cours` a `couvert-valide`,
**sans mention de couverture partielle** : les deux moities seront servies. C'est `camille-po` qui
ecrit la matrice, pas moi.

---

## 2. "Resoudre des problemes a plusieurs etapes mobilisant les 4 operations" (mathematiques CM1)

**Verdict : ACCEPTE sous conditions `ETA-1` a `ETA-10`.**

### Ce que je verifie d'abord

La ligne est `non couvert` et a ete differee deux fois pour capacite (`DEC-0029` a s12,
`DEC-0030` a s13), jamais pour un motif pedagogique. `DEC-0057` la donne accompagnee d'un
"risque nomme d'avance par `DEC-0021`". **J'ai lu `DEC-0021` avant de m'en servir** : il ne porte
pas sur les problemes a plusieurs etapes, il reporte une reserve que j'avais posee le 2026-08-12
sur `bandit-manchot-problemes-additifs-cp` - *la bonne reponse y est onze fois sur onze le plus
grand des trois nombres proposes*. C'est un defaut de **construction des distracteurs**, pas un
defaut de la competence, et il est transposable a n'importe quel paquet de problemes. C'est ce que
`ETA-7` traite.

**J'ai recompte cette reserve avant de l'invoquer**, parce que je ne m'appuie pas sur un chiffre de
2026-08-12 sans le reverifier. *Mesure du 2026-08-30 sur la population entiere des 22 items de
`cp-problemes-additifs.yaml` : la bonne reponse est **la plus grande des trois propositions 11
fois**, celle du milieu **7 fois**, la plus petite **4 fois**. La reserve de `DEC-0021` est donc
toujours ouverte aujourd'hui.* Mon chiffre de 2026-08-12 disait "onze fois sur onze" sur les seuls
items d'addition ; sur le paquet entier, additions et soustractions confondues, c'est 11 sur 22.
**Les deux comptes sont exacts et ne portent pas sur la meme population** ; je le precise pour que
personne ne recopie l'un en croyant citer l'autre.

**Le geste est celui de la mecanique**, et la forme a choix multiple est ici un atout plutot qu'un
pis-aller : le distracteur naturel d'un probleme a deux etapes est **le resultat de la premiere
etape**, c'est-a-dire l'erreur que l'enfant fait reellement quand il s'arrete en chemin. Un enfant
qui la choisit apprend quelque chose de precis.

### Conditions

- **`ETA-1` - Exactement deux etapes par item.** Un item a une etape appartient a la ligne
  "problemes simples a 1 etape" du CP ; un item a trois etapes depasse ce que ce socle nomme pour
  le CM1.
- **`ETA-2` - Les quatre operations sont servies.** Chacune des quatre operations (addition,
  soustraction, multiplication, division) est l'une des deux etapes d'**au moins trois items** du
  paquet. *Unite de compte : l'item ; un item ou l'operation apparait aux deux etapes compte une
  fois.* Motif : la ligne dit "mobilisant les 4 operations", et un paquet ou la division n'apparait
  pas ne la couvre pas.
- **`ETA-3` - Longueur de l'enonce : au plus 30 mots**, un mot etant une suite de caracteres sans
  espace. *Mesure de reference, faite le 2026-08-30 sur la population entiere des 24 enonces de
  `cm1-problemes-durees.yaml`, valides la veille : ils comptent de **19 a 25 mots** par ce meme
  decompte. La borne de 30 laisse de la place pour la seconde etape sans sortir de ce qui est deja
  valide au CM1.*
- **`ETA-4` - Les deux distracteurs sont derives, et le fichier nomme la derivation a cote de
  l'item.** Deux familles, et seulement celles-la :
  **(a)** le **resultat intermediaire**, c'est-a-dire le resultat de la premiere etape seule -
  l'enfant s'est arrete en chemin ;
  **(b)** le resultat obtenu en remplacant l'operation de la **seconde** etape par son operation
  inverse (addition contre soustraction, multiplication contre division).
  Un tiers doit pouvoir **recalculer** les deux distracteurs a partir de l'enonce et de la famille
  declaree. C'est la condition `D-6`, dont s22 a montre qu'elle attrape ce qu'aucun automate ne
  voit tant que la derivation n'est pas declaree.
- **`ETA-5` - Aucune etape n'a pour operande 0 ni 1**, et **aucun resultat intermediaire n'est egal
  au resultat final**. Sans cela, la famille (a) de `ETA-4` produit un doublon et l'item n'a plus
  trois propositions distinctes.
- **`ETA-6` - Les trois propositions d'un item sont deux a deux distinctes et sont des entiers
  positifs.** Aucun nombre decimal, aucune virgule : le socle CM1 de ce depot ne nomme que les
  entiers jusqu'au million et les fractions inferieures a 1. *Reprise de `P-1` (2026-08-14),
  appliquee ici, non elargie.*
- **`ETA-7` - Rang de grandeur de la bonne reponse.** Sur les `N` items du paquet, la bonne reponse
  est **la plus grande** des trois propositions sur au moins `arrondi superieur(N/4)` items, **la
  plus petite** sur au moins `arrondi superieur(N/4)` items, et **celle du milieu** sur au moins
  `arrondi superieur(N/4)` items. *Unite de compte : l'item ; le rang se lit sur la valeur
  numerique des trois propositions, que `ETA-6` rend deux a deux distinctes.* Motif : c'est la
  reserve de `DEC-0021`, rendue verifiable. **Cette condition n'est pas retroactive** : elle vaut
  pour le paquet a ecrire, jamais contre `cp-problemes-additifs`, qui ne la tiendrait pas
  aujourd'hui (11 / 7 / 4 sur 22, mesure ci-dessus) et que je ne rouvre pas.
  *Verification de satisfaisabilite avant de la poser : elle exige au moins un quart d'items ou la
  bonne reponse est la plus petite des trois, donc au moins un quart d'items dont la seconde etape
  est une soustraction ou une division - ce que `ETA-2` demande deja par ailleurs. Les deux clauses
  vont dans le meme sens et ne se contredisent pas.*
- **`ETA-8` - Situations.** Aucune situation ne suppose que l'enfant a un jardin, une piscine, une
  chambre a lui, un animal, un depart en vacances ou de l'argent de poche. *Reprise de `P-10`.* Et
  **aucun role n'est distribue par genre** : pas la fille qui achete des fleurs pendant que le
  garcon compte des billes.
- **`ETA-9` - Indice.** Il donne la methode de la **premiere etape seule**, jamais un resultat.
  *"Commence par chercher le prix des trois cahiers."* est un indice. **Douze mots au plus.**
- **`ETA-10` - Au moins vingt items.**

### Ce qui reste dans ma seule main sur ce paquet

`ETA-1`, `ETA-2`, `ETA-4` a `ETA-7` et `ETA-10` sont **calculables** : un test peut recalculer les
distracteurs depuis la derivation declaree, compter les operations et compter les rangs de
grandeur. `ETA-3` est mesurable. **Ce paquet n'est pas du type `COMP-2`** et ne consomme pas ma
condition d'au plus un par sprint. Je garde `ETA-8` (les presupposes sociaux et les roles de
genre), `ETA-9` et le jugement de niveau.

### Couverture

Ligne entiere, sans mention de couverture partielle : "resoudre des problemes a plusieurs etapes
mobilisant les 4 operations" est exactement ce que le paquet ferait, a deux etapes, qui est le
"plusieurs" du CM1.

---

## 3. "Comprendre un texte narratif tres court (questions explicites : qui, ou, quoi)" (francais CP)

**Verdict : ACCEPTE sous conditions `NAR-1` a `NAR-10`.** C'est celui des six que j'ai failli
refuser, et je dis pourquoi je ne le fais pas.

### Le point ou je ne transpose pas mes conditions de CM1, et c'est deliberer

`DEC-0057` classe ce candidat avec `S17-2` comme portant "le meme risque d'appariement". **La
transposition serait fausse, et l'appliquer serait deplacer mes poteaux d'un niveau a l'autre.**

`COMP-1`, ecrite pour le CM1 le 2026-08-19, interdit qu'un item se resolve en cherchant laquelle
des propositions figure dans le texte, parce que la ligne CM1 demande des **inferences simples**.
**La ligne CP ne demande pas cela** : elle dit, mot pour mot, *"questions explicites : qui, ou,
quoi"*. Au CP, retrouver dans un texte qui a fait quoi **est** la competence, et c'est deja un
resultat : cela demande d'avoir decode et d'avoir tenu le sens de deux phrases. **Exiger une
inference au CP serait exiger la ligne du CM1 sur un enfant de six ans.** Je ne le fais pas.

**Mais le risque d'appariement existe bel et bien au CP, sous une autre forme, et il est plus
grave.** Un lecteur debutant apparie des chaines de caracteres : si la bonne reponse est le seul
mot des trois qui figure dans le texte, il la trouve **sans lire**. La bonne condition n'est donc
pas `COMP-1` transposee, c'est `COMP-1` **retournee** - c'est `NAR-5`.

### La charge de lecture, mesuree plutot qu'estimee

*Mesure du 2026-08-30, sur la population entiere des enonces des dix paquets CP servis :* l'enonce
le plus long jamais valide au CP fait **15 mots et 67 caracteres** (`cp-problemes-additifs` :
*"Le clown a 7 ballons et en gonfle 2 de plus. Combien en a-t-il ?"*), pour une mediane de 13 mots
sur ce paquet. Un item de ce candidat demande un texte **plus** une question. Les bornes de `NAR-2`
placent le total a **25 mots au plus**, soit environ **1,7 fois** ce qui est deja valide au CP.

**C'est plus, et je l'accepte, avec une limite que je nomme** : ce paquet s'adresse a un enfant qui
decode deja. Il n'est pas utilisable en debut de CP. Ce n'est pas propre a ce candidat -
`cp-mot-manquant` et `cp-problemes-additifs` sont dans le meme cas depuis qu'ils existent - et ce
produit n'a pas de niveau a l'interieur du CP. Je le dis pour que la matrice ne laisse pas croire
autre chose.

### Conditions

- **`NAR-1` - Le support est porte par la cle `texteSupport`**, l'item par `enonce`. Aucune
  architecture neuve : la cle est facultative et generique (`ADR-0050`), verifie au depot.
- **`NAR-2` - Longueur.** Le `texteSupport` compte **au plus 18 mots** et **au plus deux phrases** ;
  le `texteSupport` et l'`enonce` reunis comptent **au plus 25 mots**. *Un mot est une suite de
  caracteres sans espace ; l'unite de compte est l'item.*
- **`NAR-3` - Vocabulaire.** Aucun mot du `texteSupport` ni de l'`enonce` ne depasse **neuf
  lettres**, l'apostrophe n'etant pas comptee. *Mesure de cette clause contre la population qu'elle
  filtre, faite le 2026-08-30 sur le corpus CP entier du depot - 2 215 occurrences de mots dans les
  champs `enonce`, `indice` et `explication` des dix paquets CP servis : **99,4 % des occurrences
  font neuf lettres ou moins**, et les trois seuls mots plus longs (`additionne`, `fabriquent`,
  `facilement`) n'apparaissent que dans des indices, jamais dans un enonce. **Cette clause n'ecarte
  donc rien de ce que le CP de ce produit sert deja**, et c'est exactement la verification qui
  manquait a `SEG-1`.*
- **`NAR-4` - Question explicite, et rien d'autre.** L'enonce commence par *"Qui"*, *"Ou"* ou
  *"Que"*, et sa reponse se lit litteralement dans le texte. **Aucune inference n'est demandee** :
  relier deux informations est la ligne du CM1, et un paquet de CP qui l'exigerait empieterait sur
  une ligne deja ouverte ailleurs.
- **`NAR-5` - Les trois propositions figurent toutes les trois, en toutes lettres, dans le
  `texteSupport` de leur propre item.** Motif : au CP, l'enfant est un lecteur debutant qui apparie
  des chaines ; si une seule proposition figure dans le texte, il reussit sans comprendre. **C'est
  la branche inverse de `COMP-1`**, et elle **s'automatise** par un test de la meme forme que
  `aucuneCompositionNeRecopieUnMotDuTexteSupport` de `PaquetCm1ComprehensionTest`, l'assertion
  retournee. **Cette propriete sort de ma main le jour ou ce test existe** ; je ne demande pas
  qu'il soit ecrit dans le meme lot, je dis qu'il est possible.
- **`NAR-6` - Texte masque, la question ne se repond pas.** Un enfant qui n'a pas lu le
  `texteSupport` ne fait pas mieux que le hasard. Cela interdit les questions dont la reponse est un
  fait de culture generale et les distracteurs qui s'eliminent seuls. **Verification par un tiers :
  masquer le `texteSupport`, item par item, sur la totalite des items et non sur un echantillon.**
  **Aucun automate ne voit cette propriete.** C'est `COMP-2`, et **ce paquet compte donc a lui seul
  contre ma condition d'au plus un paquet de ce type par sprint.**
- **`NAR-7` - Personnages.** Sur l'ensemble du paquet, les prenoms sont **equilibres en genre a un
  pres** (*unite de compte : le prenom distinct*), et aucun role, aucune activite, aucune emotion
  n'est distribuee par genre.
- **`NAR-8` - Aucune situation ne suppose** une configuration familiale, un logement, un animal, un
  depart en vacances ou un objet possede.
- **`NAR-9` - Indice.** Il dit **ou regarder** dans le texte, jamais la reponse. *"Relis la deuxieme
  phrase."* est un indice. **Huit mots au plus.**
- **`NAR-10` - Au moins vingt items**, et deux items ne partagent jamais le meme `texteSupport` :
  la mecanique tire une manche au hasard et ne sait pas poser deux questions sur un meme texte.

### Ce qui reste dans ma seule main sur ce paquet

**`NAR-6`, et elle est irreductible** : c'est la propriete du type de `COMP-2`, celle que s22 a
montre qu'aucun automate ni aucun auteur ne voit a ma place - elle a attrape trois items sur vingt
le 2026-08-29, precisement les trois que l'auteur croyait bons. `NAR-4`, `NAR-7`, `NAR-8` et
`NAR-9` sont aussi de mon jugement. `NAR-2`, `NAR-3`, `NAR-5` et `NAR-10` sont calculables.

### Couverture

Ligne entiere pour ce que la ligne dit, **avec la meme limite de mecanisme que `S17-2`**, et la
mention exacte que je demande sur la matrice le jour ou la ligne bouge est : **"textes de deux
phrases, un texte par question, questions explicites seulement"**. Un enfant ne lira jamais, dans
ce jeu, un texte assez long pour que la comprehension d'ensemble soit en jeu ; ce n'est pas un
defaut du lot a venir, c'est la limite du mecanisme, et elle doit etre ecrite.

---

## 4. "Reconnaitre et utiliser les marques du pluriel les plus frequentes (`-s`)" (francais CP)

**Verdict : ACCEPTE sous conditions `PLU-1` a `PLU-9`.**

### Ce que je verifie d'abord

La ligne est `non couvert`. Elle porte deux verbes : *reconnaitre* et *utiliser*. Dans une mecanique
ou l'enfant presse une proposition, *utiliser* veut dire **choisir la forme correcte**, pas
l'ecrire : c'est une couverture reelle du verbe, pas un contournement, parce que le geste
orthographique demande ici est un **choix entre des formes**, ce qu'un enfant de CP fait aussi sur
sa feuille avant de savoir ecrire vite.

**Le `-s` du pluriel est muet en francais.** C'est precisement pourquoi la competence est
orthographique et pourquoi un exercice ecrit est la bonne forme : rien ne s'entend, tout se voit et
se raisonne a partir du determinant. C'est un des rares candidats ou la mecanique est mieux placee
que la voix.

**Le piege que je dois fermer** : si l'item montre *"les ___ dorment"* avec les propositions
`chat` / `chats`, l'enfant peut apprendre "choisis toujours celle qui a un `s`" et reussir tout le
paquet sans regarder le determinant. `PLU-1`, `PLU-4` et `PLU-5` ferment ce chemin.

### Conditions

- **`PLU-1` - Forme : la proposition porte le groupe entier, determinant compris.** L'item presente
  une phrase dont un groupe est remplace par `___` ; les trois propositions sont trois ecritures du
  **meme groupe determinant + nom**, par exemple `les chats` / `les chat` / `le chats`. **La phrase
  ne contient aucun autre indice de nombre pour ce groupe** : c'est l'accord interne au groupe qui
  decide, et rien d'autre.
- **`PLU-2` - Determinants admis, population fermee et enumeree :** `un`, `une`, `le`, `la`, `les`,
  `des`. **Six elements, et aucun autre.** Motif : c'est le seul ensemble dont le passage du
  singulier au pluriel est mecanique et sans ambiguite pour un enfant de six ans, ce qui rend la
  derivation de `PLU-4` recalculable par un tiers. *Verifie le 2026-08-30 : les six figurent deja
  dans le corpus CP du depot.*
- **`PLU-3` - Population des noms admis.** Sont **exclus** les noms dont le pluriel n'est pas en
  `-s` : ceux termines par `-eau`, `-au`, `-eu`, `-al`, `-ail`, les sept noms en `-ou` qui prennent
  `-x` (`bijou`, `caillou`, `chou`, `genou`, `hibou`, `joujou`, `pou` - liste fermee et complete),
  et les noms deja termines par `-s`, `-x` ou `-z` au singulier.
  *Mesure de cette clause contre la population qu'elle filtre, faite le 2026-08-30 sur le corpus CP
  entier du depot - 30 fichiers, dix paquets servis et vingt fichiers CP de `contenu-repris`, champs
  lus par l'enfant, **797 mots distincts** : elle ecarte **14 mots en `-eau`** (`agneau`, `bateau`,
  `cadeau`, `chateau`, `eau`, `gateau`, `manteau`, `marteau`, `pinceau`, `seau`, `tableau`, `veau`,
  plus `beau` et `au` qui ne sont pas des noms communs), **2 en `-eu`**, **7 en `-al`** (dont
  `animal`, `bocal`, `cheval`), **0 en `-ail`**, **3 des sept noms en `-ou`** (`caillou`, `genou`,
  `hibou`) et les mots deja termines par `-s`, `-x` ou `-z`. **Elle laisse tres largement de quoi
  ecrire vingt items** et n'ecarte que les mots sur lesquels la reponse attendue serait fausse.*
  **Ce n'est pas un retrecissement de la ligne** : la ligne elle-meme dit *"les marques du pluriel
  **les plus frequentes (`-s`)**"*. Les pluriels en `-x` ne sont pas dans son perimetre.
- **`PLU-4` - Les deux distracteurs sont derives, et le fichier nomme la derivation.** Deux
  familles, et seulement celles-la, obtenues **a partir de la bonne reponse** :
  **(a)** le nom porte la marque inverse de celle qu'exige son determinant, le determinant restant
  celui de la bonne reponse (`les chats` donne `les chat`) ;
  **(b)** le determinant passe au nombre inverse, le nom restant celui de la bonne reponse
  (`les chats` donne `le chats`).
  Un tiers doit pouvoir **recalculer** les deux distracteurs a partir de la bonne reponse et de la
  table de `PLU-2` (`un` et `une` donnent `des` ; `le` et `la` donnent `les` ; `les` et `des`
  donnent le singulier du genre du nom).
- **`PLU-5` - Le `-s` ne decide pas.** Sur les `N` items du paquet, la bonne reponse porte la marque
  `-s` sur le nom sur **au moins `arrondi inferieur(N/3)`** items et **ne la porte pas** sur **au
  moins `arrondi inferieur(N/3)`** items. *Unite de compte : l'item.* Motif : sans cette clause, un
  enfant qui choisit toujours la forme la plus longue reussit tout le paquet.
- **`PLU-6` - Longueur.** La phrase support compte **au plus 10 mots** ; une proposition ne depasse
  pas **15 caracteres, espaces comprises**. *Mesure de reference, 2026-08-30, population entiere des
  470 propositions des dix paquets CP servis : la plus longue jamais validee au CP fait exactement
  15 caracteres (`une trottinette`).*
- **`PLU-7` - Vocabulaire.** Aucun mot de la phrase ni d'une proposition ne depasse **neuf lettres**,
  apostrophe non comptee. *Meme mesure et meme motif que `NAR-3`, meme population, meme date.*
- **`PLU-8` - Indice.** Il renvoie au determinant, jamais a la reponse. *"Regarde le petit mot placé
  devant le nom."* est un indice. **Huit mots au plus.**
- **`PLU-9` - Au moins vingt items**, et aucun nom n'est utilise plus de deux fois dans le paquet.

### Ce qui reste dans ma seule main sur ce paquet

**Presque rien.** `PLU-2` a `PLU-7` et `PLU-9` sont calculables a partir du fichier : la table de
derivation de `PLU-4` se recalcule, la repartition de `PLU-5` se compte, les longueurs se mesurent.
**Ce paquet n'est pas du type `COMP-2`** et ne consomme pas ma condition d'au plus un par sprint.
Je garde `PLU-1` (la phrase ne doit porter aucun autre indice de nombre - un jugement de langue),
`PLU-8` et le niveau.

### Couverture

Ligne entiere pour ce que la ligne dit. **Aucune mention de couverture partielle n'est necessaire** :
la ligne se restreint deja d'elle-meme au `-s`, et `PLU-3` suit cette restriction au lieu de
l'inventer.

---

## 5. "Reperer les substituts d'un personnage (pronoms, groupes nominaux) dans un texte" (francais CM1)

**Verdict : ACCEPTE sous conditions `SUB-1` a `SUB-10`.**

### Ce que je verifie d'abord

La ligne est `non couvert`. Elle est l'une des trois du domaine "Lecture et comprehension de
l'ecrit" en francais CM1, et la matrice dit deja, depuis le 2026-08-30, que `cm1-comprehension`
**ne l'ouvre pas et ne la couvre pas** : je l'avais exige a l'audit de `S17-2`. J'arbitre donc bien
une ligne distincte, pas une extension de celle qui vient d'etre validee.

**Le geste est celui de la mecanique et le mecanisme existe deja** : un texte-support, une
question, trois propositions. `texteSupport` est generique, verifie au depot. **Aucune architecture
neuve.**

**Les deux chemins par lesquels un enfant peut reussir sans lire, et que je ferme** :
- **le genre et le nombre.** Si le texte nomme un seul personnage feminin et qu'on demande a qui
  renvoie *"elle"*, la reponse se donne sans lire le texte. C'est `SUB-4`.
- **la proximite.** Si le referent est toujours le groupe nominal compatible le plus proche a
  gauche, l'enfant apprend la regle du plus proche et cesse de comprendre. C'est `SUB-5`, et c'est
  la condition la plus utile du lot : un enfant qui ne bute jamais sur un referent eloigne n'a
  jamais travaille la competence.

### Conditions

- **`SUB-1` - Forme.** Le `texteSupport` porte le texte ; l'`enonce` demande a qui renvoie un mot
  du texte, **cite entre guillemets dans l'enonce**. Les trois propositions sont trois personnages
  ou groupes nominaux **presents dans le texte de leur propre item**.
- **`SUB-2` - Longueur.** Le `texteSupport` compte **au plus 45 mots** et **au plus trois phrases** ;
  l'`enonce` compte **au plus 12 mots**. *Un mot est une suite de caracteres sans espace ; unite de
  compte : l'item. Mesure de reference, 2026-08-30, population entiere des 20 textes-supports de
  `cm1-comprehension.yaml`, valides la veille : ils comptent de **20 a 38 mots**. La borne de 45
  laisse la place d'un troisieme personnage sans sortir de ce qui est deja valide au CM1.*
- **`SUB-3` - Les deux familles de la ligne sont servies.** Sur les `N` items du paquet, le
  substitut interroge est **un pronom** sur au moins `arrondi inferieur(N/3)` items et **un groupe
  nominal** (*"le garcon"*, *"la fillette"*, *"l'animal"*) sur au moins `arrondi inferieur(N/3)`
  items. *Unite de compte : l'item.* Motif : la ligne dit *"pronoms, groupes nominaux"*, et un
  paquet qui ne servirait que les pronoms ne la couvrirait qu'a moitie.
- **`SUB-4` - Le genre et le nombre ne decident jamais seuls.** Sur **chaque** item, **au moins deux
  des trois propositions s'accordent en genre et en nombre avec le substitut interroge**.
  *Verification par un tiers, item par item, sur la totalite du paquet.*
- **`SUB-5` - La proximite ne decide pas toujours.** Sur **au moins `arrondi inferieur(N/3)`** items
  du paquet, le referent **n'est pas** le groupe nominal compatible en genre et en nombre le plus
  proche a gauche du substitut. *"Le plus proche a gauche" se compte en nombre de mots separant le
  dernier mot du groupe du premier mot du substitut, dans le texte tel qu'il est ecrit ; unite de
  compte : l'item.* Cette definition est donnee pour que la clause n'ait **qu'une seule lecture
  chiffree** - c'est exactement ce qui manquait a `D-4` et `D-5`.
- **`SUB-6` - Texte masque, la question ne se repond pas.** Meme procedure que `NAR-6` : masquer le
  `texteSupport`, item par item, sur la totalite du paquet. **Aucun automate ne voit cette
  propriete.** C'est `COMP-2`, et **ce paquet compte a lui seul contre ma condition d'au plus un
  paquet de ce type par sprint.**
- **`SUB-7` - Un seul substitut par item, et il n'apparait qu'une fois dans le texte.** Si le mot
  cite figure deux fois, l'enfant ne sait pas duquel on lui parle, et l'item n'a plus de reponse
  unique.
- **`SUB-8` - Personnages.** Les prenoms du paquet sont **equilibres en genre a un pres** (*unite de
  compte : le prenom distinct*), et aucun role, aucun metier, aucune activite n'est distribue par
  genre. Aucune situation ne suppose une configuration familiale ou un logement particulier.
- **`SUB-9` - Indice.** Il dit **quelle partie du texte relire**, jamais le referent. *"Relis la
  phrase qui precede ce mot."* est un indice. **Dix mots au plus.**
- **`SUB-10` - Au moins vingt items**, et deux items ne partagent jamais le meme `texteSupport`.

### Ce qui reste dans ma seule main sur ce paquet

**`SUB-4`, `SUB-5` et `SUB-6`.** `SUB-5` est **partiellement** calculable - la distance en mots se
compte -, mais l'accord en genre et en nombre d'un groupe nominal francais n'est pas decidable par
un automate sans lexique morphologique, et ce depot n'en a pas. **Je porte donc `SUB-4` et `SUB-5`
a la main, item par item, en plus de `SUB-6`.** C'est le paquet du lot dont l'audit me coutera le
plus cher apres `NAR`, et je le dis avant qu'on me l'assigne.

### Couverture

Ligne entiere pour ce que la ligne dit, avec la meme limite de mecanisme que `S17-2` et `NAR` : un
texte par question, trois phrases au plus. La mention exacte que je demande sur la matrice le jour
ou la ligne bouge est : **"textes de trois phrases au plus, un texte par question, pronoms et
groupes nominaux servis tous les deux"**.

---

## 6. "Utiliser a bon escient des mots de liaison simples pour structurer un texte court" (francais CM1)

**Verdict : ACCEPTE sous conditions `LIA-1` a `LIA-10`, avec couverture partielle nommee.** C'est le
plus risque des six, et le risque n'est pas celui qu'on croit.

### Le risque reel, et ce n'est pas l'appariement

Ce n'est pas qu'un enfant reussisse sans lire ; c'est que **deux propositions soient correctes a la
fois**. *"Il pleut ___ je prends mon parapluie"* accepte `donc` et `alors` sans qu'on puisse
departager. Un item a deux reponses justes dont une seule est comptee bonne n'apprend pas une
regle : il apprend a l'enfant qu'il a tort quand il a raison, et c'est le pire defaut qu'un contenu
scolaire puisse avoir. C'est la meme famille que *"aucun item n'admet deux lectures correctes"*, que
je tiens sur les homophones et sur la conjugaison depuis s10.

**Ce risque est fermable, et voici par quoi** : en exigeant que chaque distracteur exprime une
relation logique **fausse dans ce texte**, pas seulement moins bonne, et que le fichier le
**declare**. C'est `LIA-3`.

### La population des mots de liaison, enumeree en entier plutot qu'illustree

C'est une population fermee et petite : je l'enumere donc integralement, dans les deux sens - ce qui
entre et ce qui sort, avec le motif de chaque sortie. C'est ce que `SEG-1` n'avait pas fait, et
c'est ce qui lui a coute d'etre quatre fois trop large.

### Conditions

- **`LIA-1` - Forme.** L'item presente **deux ou trois phrases**, dont l'une comporte `___` a la
  place du mot de liaison. La consigne est constante sur tout le paquet. Le choix du champ qui porte
  ces phrases (`enonce` seul ou `texteSupport` plus `enonce`) est une decision technique : ce n'est
  pas la mienne, et aucune de mes conditions n'en depend.
- **`LIA-2` - Population fermee des mots de liaison admis**, et **seulement** ceux-la :
  `mais`, `donc`, `car`, `puis`, `ensuite`, `enfin`, `d'abord`, `alors`, `pourtant`, `parce que`.
  **Dix elements.**
  **Sont exclus, avec leur motif :**
  - **`et`** - il est compatible avec presque toute succession de deux propositions. En bonne
    reponse il rend l'item non unique ; en distracteur il rend le distracteur non falsifiable.
    **C'est la seule exclusion du lot qui soit indispensable**, et `LIA-3` serait inapplicable sans
    elle.
  - **`ou`, `ni`, `or`** - `ou` et `ni` n'expriment pas une relation entre deux idees mais une
    alternative ou une negation double, hors de ce que la ligne appelle "structurer" ; `or` n'est
    pas un mot d'enfant de neuf ans.
  - **`cependant`, `neanmoins`, `toutefois`, `en effet`, `par consequent`** - registre ecrit
    soutenu, hors de l'usage d'un CM1. `pourtant` les remplace tous pour l'opposition.
- **`LIA-3` - Reponse unique, et le fichier le demontre.** Pour **chaque** item, le fichier nomme
  **la relation logique** exprimee par la bonne reponse (opposition, consequence, cause,
  succession) et, **pour chacun des deux distracteurs**, la relation qu'il exprime **et la raison
  pour laquelle cette relation est fausse dans ce texte**. **Un distracteur qui produit un texte
  seulement moins bon, et non faux, est refuse.** *Verification par un tiers, item par item, sur la
  totalite du paquet.* **Aucun automate ne voit cette propriete** : c'est du type `COMP-2`, et **ce
  paquet compte a lui seul contre ma condition d'au plus un paquet de ce type par sprint.**
- **`LIA-4` - Trois relations distinctes par item.** Les deux distracteurs d'un item expriment deux
  relations logiques differentes l'une de l'autre et differentes de celle de la bonne reponse.
  Motif : deux distracteurs de meme relation reduisent l'item a un choix entre deux, et le hasard
  y passe de un tiers a une moitie.
- **`LIA-5` - Les quatre relations sont servies.** Chacune des quatre relations (opposition,
  consequence, cause, succession) est la relation de la **bonne reponse** sur **au moins
  `arrondi inferieur(N/6)`** items du paquet. *Unite de compte : l'item.* Motif : la ligne dit
  "structurer", et un paquet qui ne ferait que de la succession n'enseignerait qu'une relation sur
  quatre.
- **`LIA-6` - Longueur.** Les phrases de l'item comptent **au plus 35 mots au total**, un mot etant
  une suite de caracteres sans espace.
- **`LIA-7` - Vocabulaire.** Aucun mot de l'item ne depasse **onze lettres**, apostrophe non
  comptee. *Mesure de cette clause contre la population qu'elle filtre, faite le 2026-08-30 sur le
  corpus CM1 entier du depot - **6 788 occurrences de mots** dans les champs `enonce`,
  `texteSupport`, `indice` et `explication` des quatorze paquets CM1 servis : **99,8 % des
  occurrences font onze lettres ou moins**. La clause n'ecarte donc quasiment rien de ce que le CM1
  de ce produit sert deja.*
- **`LIA-8` - Indice.** Il nomme **la relation a chercher**, jamais le mot. *"Cherche si la seconde
  phrase s'oppose a la premiere."* est un indice ; *"C'est mais."* n'en est pas un. **Douze mots au
  plus.**
- **`LIA-9` - Au moins vingt items**, et aucun mot de liaison n'est la bonne reponse de plus de six
  items.
- **`LIA-10` - Couverture partielle nommee, et c'est une condition, pas une remarque.** La ligne dit
  *"utiliser a bon escient des mots de liaison simples **pour structurer un texte court**"*. Ce
  paquet fait **choisir** un mot de liaison entre deux ou trois phrases ; il ne fait jamais ecrire
  un texte, ni ordonner plus de trois phrases. La mention exacte que j'exige sur la matrice le jour
  ou la ligne bouge est : **"choix du mot de liaison entre deux ou trois phrases, sans production
  de texte"**. Je prefere une case a moitie vraie et dite telle qu'une case verte qui ment.

### Ce qui reste dans ma seule main sur ce paquet

**`LIA-3` et `LIA-4`, qui sont l'essentiel.** `LIA-2`, `LIA-6`, `LIA-7` et `LIA-9` sont calculables.
Mais decider qu'un distracteur rend le texte **faux** et non seulement moins bon est un jugement de
langue qu'aucun serveur ne rendra, et la declaration de `LIA-3` ne me dispense pas de la verifier -
elle me permet seulement de la **recompter** au lieu de l'etablir, ce qui est le gain le plus
rentable de s22.

---

## 7. `contenu-repris/mots/cp-premiere-lettre.yml` sur "Identifier un son (phoneme) dans un mot" (francais CP)

**Verdict : REFUSE.** Trois motifs, dont un qui suffirait seul.

### Motif 1 - la tache ne demande aucun phoneme, et c'est deja juge

Le fichier montre un mot en capitales et demande **par quelle lettre il commence**. La reponse est
**le premier caractere affiche a l'ecran**. Un enfant qui ne sait pas lire du tout reussit les
vingt-quatre items en appariant deux formes.

**Ce n'est pas une barre neuve : c'est le critere que j'ai ecrit le 2026-08-10** dans
`FICHE-bandit-manchot-le-bon-son-cp.md` et qui n'a jamais ete contredit depuis :

> *"Tant qu'aucun son n'est emis, cette tache ne peut exiger le phoneme que la ou le grapheme ne
> suffit pas a le donner, c'est-a-dire la ou le son admet plusieurs graphies."*

*Mesure : sur les **24 items** du fichier - population entiere, pas un echantillon -, le grapheme
donne le phoneme **24 fois sur 24**, puisque la reponse attendue est litteralement la premiere
lettre ecrite.* Il n'y a pas un seul item ou quelque chose se travaille.

### Motif 2 - trois items sont faux, et une erreur factuelle suffit a refuser

Lus comme des items de **phoneme**, ce que la ligne visee demande, trois items du fichier enoncent
une correspondance fausse. *Mesure sur les 24 items :*

| Item | Reponse attendue | Ce qui est faux |
|---|---|---|
| `CHAT` | `C` | Le premier phoneme de *chat* est le son **[ch]**, ecrit par le digramme `CH`. `C` seul ne le note pas. |
| `HIBOU` | `H` | Le `h` francais **ne note aucun phoneme**. *hibou* commence par le son [i]. |
| `WAGON` | `W` | En francais, *wagon* se prononce avec le son **[v]**. La lettre `W` n'y note pas le son qu'elle nomme. |

Un enfant de CP a qui l'on enseigne que *chat* commence par le son `C` devra desapprendre cela
l'annee suivante, au moment precis ou il aborde les digrammes. **Une erreur factuelle dans un
contenu scolaire est un motif de refus a elle seule, sans discussion**, et c'est la regle que je
tiens depuis le premier sprint.

### Motif 3 - les distracteurs ne sont pas des erreurs, ce sont des lettres au hasard

Le fichier declare `distractorMode: pool` : les deux mauvaises propositions sont tirees parmi les
autres bonnes reponses du jeu, c'est-a-dire **parmi les vingt-trois autres lettres**. Un distracteur
tire au hasard dans l'alphabet n'est pas plausible : il ne correspond a aucune erreur qu'un enfant
fait. Cela transforme l'exercice en recherche visuelle et prive le verdict de toute valeur
d'apprentissage. C'est le meme motif que celui pour lequel j'exige, partout ailleurs, des familles
de distracteurs **nommees et derivees** (`P-6`, `D-6`, `ETA-4`, `PLU-4`, `RAN-4`).

### Ce qui manque, precisement

**Un son emis, donc une decision d'utilisatrice, pas un travail de developpement.** La ligne
"Identifier un son (phoneme) dans un mot" appartient au domaine *"Langage oral et conscience
phonologique"* du referentiel. Sans emission sonore, on ne peut pas demander a un enfant
d'identifier un son autrement qu'en lui montrant les lettres qui l'ecrivent. **Ce n'est ni un
manque de contenu, ni un manque de mecanique, ni un manque d'architecture : c'est la source d'un
enregistrement de voix humaine, que `DEC-0053` section 7.3 dit deja qu'aucun agent de cette squad
ne peut produire.** Tant que l'utilisatrice n'a pas tranche cette source, la ligne est **hors
d'atteinte**, quel que soit le nombre de sprints.

### La part servable, et je la nomme sans l'arbitrer

Mon propre critere du 2026-08-10 laisse ouverte une porte etroite : la tache peut exiger le phoneme
**la ou le son admet plusieurs graphies**, ou bien la ou l'ecriture **contredit** le son (le `t`
muet de *petit*, le `s` qui se dit [z] dans *maison*, le `h` de *hibou*). Un paquet construit
uniquement sur ces cas travaillerait un phoneme sans emettre de son.

**Je ne l'arbitre pas ici, et je dis pourquoi plutot que de le laisser en suspens** : un tel paquet
aurait la meme forme que `cp-le-bon-son`, qui sert deja la ligne voisine *"Associer les graphemes
aux phonemes"*, aujourd'hui `en cours`. **Le risque est de couvrir deux lignes du referentiel avec
le meme exercice**, ce qui gonflerait la matrice sans rien apprendre de plus a l'enfant. Trancher
cela demande de savoir si les deux lignes du socle designent bien deux competences distinctes -
c'est une question sur **le referentiel**, pas sur un paquet, et elle appartient a `camille-po` et a
l'utilisatrice. **Je la leur route, formulee ainsi** : *les lignes "Identifier un son (phoneme) dans
un mot" et "Associer les graphemes aux phonemes" designent-elles deux competences distinctes dans ce
socle, ou la premiere est-elle la version orale de la seconde ?* Si la reponse est "deux
competences distinctes", je rendrai un arbitrage sur la part non sonore ; si c'est "la version
orale", la ligne reste bloquee sur la voix et je n'ai rien de plus a dire.

### Sur l'artefact d'accentuation du fichier

`DEC-0052` avait note que ce fichier porte `VELO` sans accent. **Ce point ne m'appartient plus** :
`DEC-0056` a rendu l'accentuation du texte lu par l'enfant au `relecteur-pedagogique`, et je m'y
range. Je le signale ici uniquement pour que la trace ne se perde pas, sans en faire un motif.
