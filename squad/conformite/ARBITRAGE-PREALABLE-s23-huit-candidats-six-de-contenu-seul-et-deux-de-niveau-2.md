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
