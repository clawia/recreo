# Fiche de conformite - bandit-manchot-problemes-etapes-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu** : `cm1-problemes-etapes`, 24 items, contenu neuf, story `S23-ETA`, ecrit par
  `marc-dev`.
- **Arbitrage prealable de `nadia-prof`** : rendu le 2026-08-30,
  `squad/conformite/ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`,
  section 2 (`ETA-1` a `ETA-10`) et section 12.1 (amendement `ETA-4b` du 2026-08-30, qui remplace
  `ETA-4` ; `ETA-7` inchangee, lettre et seuil).
- **Fiche ouverte le 2026-08-30 par `nadia-prof`**, faute de fiche existante au moment de l'audit.
  La section "criteres pedagogiques attendus" appartient a `camille-po` et n'est pas ecrite ici.

## Competence visee

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Resoudre des problemes a plusieurs etapes mobilisant les 4 operations | `squad/programme/mathematiques/competences-cm1.md`, ligne 16 |

Ligne `non couvert`, differee deux fois pour capacite (`DEC-0029` a s12, `DEC-0030` a s13), jamais
pour un motif pedagogique.

## Bloc de validation

### Done pedagogique - 2026-08-30 (s23) - VALIDE SOUS RESERVE

**Done pedagogique prononce le 2026-08-30 par `nadia-prof`.** `camille-po` peut passer la ligne a
`couvert-valide`, **sans mention de couverture partielle** : "a deux etapes" est le "plusieurs" du
CM1, et c'est exactement ce que le paquet fait.

**Audit conduit en jouant**, application lancee depuis le fat jar construit sur l'arbre du
worktree, sur le JDK 21 de la toolchain declaree. Parties completes de dix manches, jouees en se
trompant volontairement sur chaque manche pour voir l'indice. Ce n'est pas un audit degrade.

#### Ce que j'ai verifie, recompte sur la population entiere des 24 items

Je n'ai repris aucun chiffre de l'auteur sans le refaire.

| Condition | Verdict | Mesure, population : les 24 items |
|---|---|---|
| `ETA-1` | **tenue** | 24 items a exactement deux etapes, lues une par une. Aucune etape habillee d'une phrase de plus : la premiere etape produit toujours une quantite que la seconde consomme |
| `ETA-2` | **tenue** | Addition **15**, soustraction **16**, multiplication **11**, division **6**. *Unite de compte : l'item.* Plancher de trois par operation. **Les quatre chiffres declares par `marc-dev` sont exacts** |
| `ETA-3` | **tenue sur 23 items, en defaut sur 1** | Voir reserve A |
| `ETA-4b` | **tenue** | **Les 48 distracteurs recalcules depuis l'enonce et les deux familles declarees : 0 ecart sur 24 items.** Seize items declarent (a) + (b), huit declarent (a) + (c). **La famille (a) figure sur les 24 items**, tres au-dessus du plancher de la moitie |
| `ETA-5` | **tenue** | Aucun operande 0 ni 1 sur les 72 operandes ; aucun resultat intermediaire egal au resultat final |
| `ETA-6` | **tenue** | Les 72 propositions sont des entiers positifs, deux a deux distinctes dans chaque item |
| `ETA-7` | **tenue** | **8 petite / 8 milieu / 8 grande**, soit 33,3 % chacun, contre un plancher de `arrondi superieur(24/4)` = 6. **Le compte declare par `marc-dev` est exact.** Les huit items de rang median sont bien les huit qui declarent (a) + (c) : ce que l'auteur annonce comme force algebriquement l'est, et je l'ai verifie sur les huit |
| `ETA-8` | **tenue** | Les 24 sujets sont des collectifs - bibliotheque, classe, ecole, club, atelier, epicerie, cantine, tri selectif, reserve, magasin scolaire. Aucun jardin, aucune piscine, aucune chambre a soi, aucun animal, aucun depart en vacances, aucun argent de poche. **Aucun role distribue par genre**, aucun enfant nomme |
| `ETA-9` | **tenue** | Les 24 indices donnent la methode de la premiere etape seule, jamais un resultat, et sont propres a leur item. Douze mots au plus. Verifie a l'ecran apres erreur |
| `ETA-10` | **tenue** | 24 items |

**Ce que la satisfaisabilite calculee le 2026-08-30 annoncait est confirme par du contenu reel** :
la construction de la section 12.1.2 prevoyait un lot de 24 items a 8 / 8 / 8 avec la famille (a)
sur les 24. C'est exactement ce que le paquet livre.

#### Les deux proprietes que j'avais annoncees garder seule, et ce que j'en dis

Je les avais nommees en section 12.1.3 pour que personne ne les croie couvertes par un garde-fou.

- **La plausibilite de la famille (c)** comme erreur reelle d'un enfant sur *cet* enonce-la. Verifiee
  item par item sur les huit items concernes. **Cinq tiennent nettement** : les items ou le premier
  enonce dit *"en recoit 15 de plus"*, *"en recoit 20 de plus"*, *"en recoit 14 de plus"*, *"en
  recoit 17 de plus"* ou *"en perd 12"* - c'est litteralement le *"de plus" lu la ou il y a "de
  moins"* que `ETA-4b` decrit. **Trois sont plus minces** : voir reserve B.
- **Le fait que les deux etapes en soient reellement deux.** Tenue sur 24 items sur 24.

#### Reserves

- **Reserve A, non bloquante, 2026-08-30 - `ETA-3` en defaut d'un jeton sur un item.** *Mesure sur
  la population entiere des 24 enonces, par le decompte de `ETA-3` - un mot est une suite de
  caracteres sans espace : de 22 a 31 mots. Vingt-trois enonces sont a 30 ou moins ; **l'item 17**
  (*"La classe recolte 40 timbres, puis en recoit 15 de plus pour une collection commune. Elle en
  donne ensuite 10 a une autre ecole. Combien de timbres la classe garde-t-elle ?"*) **est a 31**.*
  **La borne est brechee, je le dis plutot que de l'arrondir.** *J'ai reverifie ma propre mesure de
  reference avant de m'en servir : les 24 enonces de `cm1-problemes-durees` font 19 a 25 mots par ce
  decompte-ci, qui compte le point d'interrogation ; c'est bien la meme methode, la comparaison est
  legitime.*
  **Pourquoi elle ne bloque pas** : le jeton qui breche est **le point d'interrogation**. La charge
  de lecture reelle de cet enonce est de 30 mots, dans ma borne. Un enonce de 30 mots et un enonce
  de 30 mots suivis d'un signe ne se lisent pas differemment, et refuser un increment pour cela
  serait faire de ma porte un caprice. **Ce n'est pas une redefinition de mon decompte** : la breche
  est reelle, elle est mineure, et je la nomme pour qu'elle soit corrigee et non pour qu'elle soit
  oubliee.
  **Levee** : retirer un mot a l'enonce de l'item 17.
- **Reserve B, non bloquante, 2026-08-30 - la famille (c) repose sur une melecture mince sur trois
  items.** `ETA-4b` dit que la famille (c) *"n'est utilisable que si l'operation inversee correspond
  a une melecture plausible de l'enonce"*, et que cette clause reste dans ma seule main. Sur les huit
  items qui declarent (c), **trois** l'appuient sur les verbes *"distribue"* (item 22), *"retire"*
  (item 23) et *"vend"* (item 24). Ces trois verbes designent sans ambiguite une sortie de stock ; les
  lire comme une entree n'est pas l'erreur qu'un CM1 commet reellement, contrairement a *"perd"*
  (item 21) ou a *"de plus"* (items 17 a 20). Le distracteur y reste un **calcul inverse mecanique**
  plutot qu'une **erreur d'enfant**.
  **Pourquoi elle ne bloque pas** : les trois distracteurs restent recalculables, distincts et
  plausibles comme valeurs ; l'item n'enseigne rien de faux et ne devient pas une devinette. Ce qui
  manque est le supplement pedagogique qu'un distracteur qui reproduit une vraie erreur apporte -
  et sur ces trois items, la famille (a), qui est presente, le fournit deja.
  **Levee** : reformuler le premier enonce de ces trois items avec un verbe dont la melecture est
  reelle (*"perd"*, *"en donne"*), ou basculer ces items sur (a) + (b) en compensant le rang median
  ailleurs.
- **Reserve C, non bloquante, 2026-08-30 - "3 ateliers" se lit de deux facons sur deux items.**
  Items 6 et 7 : *"L'atelier de poterie fabrique 75 bols, dont 18 sont abimes. **3 ateliers**
  fabriquent chacun ce meme nombre de bols reussis."* Un enfant peut lire "3 ateliers" comme incluant
  le premier (lecture voulue, 3 x 57 = 171) ou comme trois **autres** ateliers (57 + 3 x 57 = 228).
  **Ce n'est pas un item a deux reponses justes** : 228 n'est pas propose, et l'item se resout donc
  sur 171 dans les deux cas. Mais l'enfant qui a fait la seconde lecture ne comprend pas pourquoi il
  a raison.
  L'item 7 porte la meme construction avec un glissement de sujet en plus - *"L'atelier de couture
  prepare 50 coussins... 4 **ecoles** preparent chacune ce meme nombre"*.
  **Levee** : ecrire *"l'atelier et deux autres ateliers"*, ou *"chacun des 3 ateliers de la ville,
  celui-ci compris"*.

#### `MNC-1`

**`MNC-1`** - Ce Done ne couvre pas que l'enfant voie, sans faire defiler la page, un element
quelconque du jeu, y compris le retour qui suit sa reponse - verdict, indice, proposition. Cette
propriete est sortie de la porte pedagogique par arbitrage de l'utilisatrice du 2026-08-28
(`DEC-0053`) ; sa dette est ouverte, croissante et assumee. Aucune mesure ne l'a instruite sur ce
paquet.

Le rappel n'est pas de pure forme ici : ce sont les enonces les plus longs livres depuis
`cm1-problemes-durees`, jusqu'a 31 mots.

#### Ce sur quoi je reste la seule garde sur ce paquet

- **La plausibilite de la famille (c)** sur chaque enonce (reserve B). Aucun test ne la voit : un
  automate recalcule un nombre, il ne sait pas si un enfant commet cette erreur-la.
- **Que les deux etapes en soient reellement deux** - tenue, et invisible a tout garde-fou.
- **`ETA-8`** (presupposes sociaux, roles de genre) et **`ETA-9`**.
- **L'exactitude des 24 calculs** et le jugement de niveau.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Done pedagogique prononce. La ligne peut passer a `couvert-valide`, sans mention de couverture partielle** | `camille-po` | Ecrire la matrice. Ce n'est pas moi qui l'ecris |
| **Reserves A, B et C, non bloquantes** | `camille-po` | En faire des stories d'un sprint suivant. Chacune porte son critere de levee, applicable sans me le redemander |
| **`ETA-4b` et `ETA-7` sont satisfaisables ensemble, et c'est verifie sur du contenu reel** | `theo-archi`, `yanis-qa` | Le calcul de satisfaisabilite du 2026-08-30 a predit exactement ce que le contenu livre. Rien a corriger |
| **La clause de plausibilite de la famille (c) reste hors de tout garde-fou** | `yanis-qa`, `theo-archi` | Ne pas la compter comme couverte. Elle a mordu ici, sur trois items |

---

### Done pedagogique - 2026-08-30, reaudit apres fermeture des reserves (s23) - VALIDE SOUS RESERVE (maintenu)

**Amendement date. Le bloc du 2026-08-30 ci-dessus n'est pas reecrit.** Le Done prononce ce
jour-la tient : **il n'est ni etendu ni retire**. La ligne reste `couvert-valide`, sans mention de
couverture partielle.

**Les trois reserves A, B et C sont levees.** **Deux reserves nouvelles sont ouvertes**, dont une
que le correctif a creee et une que j'avais moi-meme mal comptee le 2026-08-30. Le paquet passe de
trois reserves a deux.

**Audit conduit en jouant** : quatorze parties completes, chaque proposition fausse pressee avant
la bonne pour voir l'indice, les 24 items rencontres. Ce n'est pas un audit degrade.

#### Ordre d'audit - la regle du 2026-08-30, et ce qu'elle donne ici

Je m'etais impose d'**auditer en premier les conditions du paquet que je n'ai PAS amendees**. Ici,
cela range `ETA-1`, `ETA-2`, `ETA-3`, `ETA-5`, `ETA-6`, `ETA-8`, `ETA-9` et `ETA-10` dans le premier
groupe, et `ETA-4b` et `ETA-7` dans le second.

**Le seul defaut de ce reaudit est sur `ETA-9`, dans le premier groupe.** Et il est sur la part de
`ETA-9` qu'aucun test ne calcule : le compte de mots et le fait que l'indice ne revele pas la
reponse sont gardes, le fait que **l'indice soit propre a son item** ne l'est pas.

**Ce que la regle vaut, mesure sur les trois paquets du sprint plutot qu'affirmee.** *Population :
les trois paquets neufs de s23.* Les trois defauts que j'ai retenus - `RAN-7`, `LIA-3`, et
aujourd'hui `ETA-9` - **tombent tous les trois sur une condition que je n'ai jamais amendee et sur
sa part non calculable.** Zero defaut sur `ETA-4b`, `RAN-4b`, `RAN-5b`, les trois conditions que
j'ai amendees en cours de sprint. **Je ne surestime pas ce que la regle a produit** : sur `RAN` elle
n'a rien donne (un reaudit d'une seule condition), sur `LIA` c'est `sofia-sm` qui m'a route l'item.
**C'est sur `ETA` qu'elle a servi** : elle a mis `ETA-9` dans le premier groupe, et c'est en lisant
les indices a cote de leurs enonces avant de regarder `ETA-4b` que le decalage est apparu.

#### Reserve A - levee

**Critere que j'avais ecrit** : "retirer un mot a l'enonce de l'item 17."

*Mesure sur la population entiere des 24 enonces, par le decompte de `ETA-3` - un mot est une suite
de caracteres sans espace, le point d'interrogation compris des qu'une espace l'en separe.* L'item
17 passe de **31 a 30 mots** ("a une autre ecole" devient "a une ecole"). **Aucun enonce du paquet
ne depasse 30 ; le maximum est exactement 30.** Borne tenue.

**Deux observations que je nomme sans en faire des reserves.**

- Le mot retire est **"autre"**, celui qui distinguait l'ecole destinataire de celle de la classe.
  L'enonce reste resoluble et le calcul est inchange ; il est seulement un peu moins net. Ma
  demande disait "retirer un mot" sans dire lequel, et c'est ma formulation qui laissait ce choix.
- **La fermeture de la reserve C a consomme toute la marge restante sur deux items** : les items 6
  et 7 passent de 28 a 30 mots. *Sept enonces sur 24 sont maintenant exactement a la borne* -
  items 5, 6, 7, 11, 13, 17 et 19. Le paquet tient `ETA-3`, et il la tient sans marge.

#### Reserve B - levee

**Critere que j'avais ecrit** : "reformuler le premier enonce de ces trois items avec un verbe dont
la melecture est reelle (*perd*, *en donne*), ou basculer ces items sur (a) + (b)."

Les trois enonces emploient **"perd"** - le verbe que j'avais deja juge bon sur l'item 21. C'est
exactement la premiere voie que j'avais nommee.

| Item | Avant | Apres | Famille (c) |
|---|---|---|---|
| 22 | "La reserve a 40 cahiers, en **distribue** 15" | "en **perd** 15" | 40 + 15 + 14 = 69 : lire "perd 15" comme "recoit 15" est une melecture reelle |
| 23 | "L'ecole a 50 chaises, en **retire** 22" | "en **perd** 22" | 50 + 22 + 10 = 82, idem |
| 24 | "Le magasin scolaire a 35 crayons en rayon, en **vend** 13" | "La classe a 35 crayons en reserve, en **perd** 13" | 35 + 13 + 16 = 64, idem |

**Verifie et non repris** : aucun nombre ne change, les 48 distracteurs se recalculent depuis
l'enonce sans ecart, `ETA-2` reste a 15 additions / 16 soustractions / 11 multiplications / 6
divisions, `ETA-7` reste a **8 petite / 8 milieu / 8 grande**, `ETA-8` reste tenue - "La classe" est
un collectif comme "Le magasin scolaire" l'etait. *Population : les 24 items.*

#### Reserve C - levee sur les deux items que j'avais nommes

**Critere que j'avais ecrit** : "ecrire *l'atelier et deux autres ateliers*, ou *chacun des 3
ateliers de la ville, celui-ci compris*."

Les items 6 et 7 portent "3 ateliers, **celui-ci compris**" et "4 ecoles, **celle-ci comprise**".
C'est la seconde voie que j'avais nommee. **Vu a l'ecran** sur les deux items. La lecture qui
excluait le premier atelier n'est plus possible.

#### Reserve D, nouvelle, non bloquante, 2026-08-30 - trois indices nomment un evenement absent de leur enonce

**Le correctif de la reserve B a change les enonces et pas les indices.** Les verbes que j'avais
rejetes de l'enonce y ont disparu et **survivent dans l'indice servi apres un echec** :

| Item | Ce que l'enonce dit | Ce que l'indice dit, apres l'erreur |
|---|---|---|
| 22 | "La reserve a 40 cahiers, en **perd** 15 en debut d'annee." | "Soustrais d'abord les cahiers **distribues** en debut d'annee." |
| 23 | "L'ecole a 50 chaises, en **perd** 22 pour une autre activite." | "Soustrais d'abord les chaises **retirees** pour l'autre activite." |
| 24 | "La classe a 35 crayons en reserve, en **perd** 13 le premier jour." | "Soustrais d'abord les crayons **vendus** le premier jour." |

**Vu a l'ecran, pas deduit du fichier.** Sur l'item 24, j'ai presse 64, recu "Ce n'est pas ca.
Essaie encore !" et, sous les propositions, "Soustrais d'abord les crayons vendus le premier jour."
**Rien n'a ete vendu dans cet enonce, et il n'y a plus de magasin** : le correctif a remplace "Le
magasin scolaire" par "La classe". L'item 22 se lit de la meme facon : rien n'a ete distribue.

*Mesure : les 24 indices confrontes aux 24 enonces, chaque nom et chaque participe de l'indice
cherche dans l'enonce de son item. **Trois ecarts, items 22, 23 et 24**, et ce sont exactement les
trois items dont l'enonce a ete corrige. L'item 21, seul item de famille (c) que le correctif n'a
pas touche, porte "les ballons perdus" et son enonce dit "perd" : il n'a pas d'ecart.*

**Pourquoi elle ne bloque pas, et je tiens a etre explicite sur la frontiere.** Ma frontiere entre
ce qui bloque et ce qui reserve est constante sur ce sprint : **bloque ce qui conduit l'enfant a
une reponse fausse, ou ce qui le compte faux quand il a raison** - c'est ce qui a fonde le refus de
`RAN-7`, ou l'indice designait un distracteur present a l'ecran, et celui de `LIA-3`. **Reserve ce
qui degrade sans egarer.** Ici l'indice donne la bonne methode (soustraire) et la bonne quantite
est la seule candidate a ce moment de l'enonce : l'enfant retrouve son chemin. Il lit en revanche
un fait qui n'existe pas dans son probleme, dans un contenu scolaire, et **ce n'est pas rien** :
c'est le supplement de sens que l'indice devait apporter qui devient un obstacle de plus.
L'item 24 est le plus net des trois, l'item 23 le moins - "retirees" paraphrase "perd 22 pour une
autre activite".

**Levee** : aligner les trois indices sur le verbe de leur enonce - "les cahiers perdus en debut
d'annee", "les chaises perdues pour l'autre activite", "les crayons perdus le premier jour". Aucun
autre indice du paquet n'est concerne : les 21 autres sont coherents avec leur enonce, verifie.

#### Reserve E, nouvelle, non bloquante, 2026-08-30 - ma reserve C ne nommait pas tous les items concernes

**C'est une correction de mon propre compte, pas un durcissement.** Le 2026-08-30 j'ai ecrit la
reserve C sur les items 6 et 7. **La meme construction porte la meme ambiguite sur quatre autres
items, que je n'avais pas nommes** :

| Item | Formule | Les deux lectures |
|---|---|---|
| 3 | "3 **classes** fabriquent chacune ce meme nombre" | 3 x 63 = 189, ou 63 + 3 x 63 = 252 |
| 4 | "4 **ecoles** impriment chacune ce meme nombre" | 4 x 20 = 80, ou 20 + 4 x 20 = 100 |
| 5 | "2 **clubs** preparent chacun ce meme nombre" | 2 x 32 = 64, ou 32 + 2 x 32 = 96 |
| 8 | "2 **ecoles** preparent chacune ce meme nombre" | 2 x 60 = 120, ou 60 + 2 x 60 = 180 |

**Ce n'est pas un item a deux reponses justes** : sur les quatre, le total de la seconde lecture
n'est pas propose (252, 100, 96 et 180 sont absents des propositions). L'item se resout sur la
lecture voulue dans les deux cas. **C'est exactement le statut que j'avais donne a la reserve C, et
c'est pour cela qu'elle reste non bloquante.**

**Un second point, sur l'item 7 corrige.** "L'atelier de couture prepare 50 coussins [...] 4
**ecoles**, **celle-ci** comprise, preparent chacune ce meme nombre." Le pronom "celle-ci" n'a pas
d'antecedent feminin dans l'enonce : la seule entite nommee est *l'atelier* de couture. La
correction ferme bien l'ambiguite de compte, et elle laisse le glissement de sujet que j'avais
signale le 2026-08-30 - il est desormais porte par un pronom qui ne renvoie a rien. L'item 6, ou le
sujet et le groupe sont du meme genre et du meme mot, est net.

**Levee** : porter "celui-ci compris" / "celle-ci comprise" sur les items 3, 4, 5 et 8 comme sur
les items 6 et 7 ; et, sur l'item 7, nommer le groupe par le mot du sujet ("4 ateliers, celui-ci
compris") plutot que par "ecoles". `ETA-3` etant a la borne, cet ajout de deux mots demandera d'en
retirer autant sur les items concernes.

#### Les conditions recomptees apres correctif, sur la population entiere

*Population : les 24 items. Recomptees sur l'arbre reaudite, jamais reprises de mon bloc du
2026-08-30 ni de la declaration de l'auteur.*

| Condition | Verdict | Mesure |
|---|---|---|
| `ETA-1` | **tenue** | 24 items a exactement deux etapes |
| `ETA-2` | **tenue** | add **15**, sub **16**, mul **11**, div **6**. *Unite de compte : l'item.* Plancher 3 |
| `ETA-3` | **tenue** | Maximum **30 mots** sur les 24 enonces, borne 30. **La breche de la reserve A est fermee** ; sept enonces sont a la borne exacte |
| `ETA-4b` | **tenue** | 48 distracteurs recalcules depuis l'enonce : 0 ecart. 16 items en (a) + (b), 8 en (a) + (c) |
| `ETA-5` | **tenue** | Aucun operande 0 ni 1 ; aucun resultat intermediaire egal au resultat final |
| `ETA-6` | **tenue** | 72 propositions, entiers positifs, deux a deux distinctes par item |
| `ETA-7` | **tenue** | **8 petite / 8 milieu / 8 grande**, plancher 6. Inchange : aucun nombre n'a bouge |
| `ETA-8` | **tenue** | 24 sujets collectifs, aucun role distribue par genre, aucun enfant nomme. "La classe" remplace "Le magasin scolaire" sans changer ce verdict |
| `ETA-9` | **tenue sur 21 items, en defaut sur 3** | Douze mots au plus, aucun indice ne revele une proposition. Voir reserve D |
| `ETA-10` | **tenue** | 24 items |

#### `MNC-1`

**`MNC-1` s'applique a ce Done, et ce Done ne le couvre pas.** Rien ne garantit ici que l'enfant
voie, sans faire defiler la page, un element quelconque du jeu, y compris le retour qui suit sa
reponse - verdict, indice, proposition. Propriete sortie de la porte pedagogique par arbitrage de
l'utilisatrice du 2026-08-28 (`DEC-0053`) ; dette ouverte, croissante, assumee. **Aucune mesure ne
l'a instruite sur ce paquet.**

**Le rappel n'est pas de pure forme, et il pese plus qu'au 2026-08-30** : ces enonces etaient les
plus longs du catalogue, et sept d'entre eux sont maintenant a la borne de 30 mots au lieu d'un
seul a 31. La reserve D porte en outre sur un texte - l'indice - qui s'affiche **sous** les
propositions, c'est-a-dire a l'endroit le plus expose de l'ecran a cette dette.

#### Ce que ce Done ne dit pas

**Le `relecteur-pedagogique` n'est pas passe sur ce sprint.** L'orthographe, l'accentuation et la
ponctuation des 24 enonces, des 72 propositions et des 24 indices ne sont pas jugees par moi
(`DEC-0002` amende le 2026-08-04, `DEC-0056`). Ce Done n'en dit rien. Le pronom sans antecedent de
l'item 7, en revanche, est bien de ma porte : c'est le sens de ce que l'enfant lit, pas sa forme.

#### Ce sur quoi je reste la seule garde sur ce paquet

- **La coherence entre l'indice et son enonce** - la propriete que la reserve D nomme. Elle n'est
  gardee par aucun test, et **le correctif de ce sprint l'a cassee sur trois items sans qu'une CI
  entierement verte le voie**. C'est le quatrieme defaut de ce sprint invisible a la CI.
- **La plausibilite de la famille (c)** comme melecture reelle : tenue sur les huit items depuis le
  passage a "perd".
- **Que les deux etapes en soient reellement deux** : tenue sur 24.
- **`ETA-8`**, les presupposes sociaux et les roles de genre. **Le jugement de niveau.**

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Verdict maintenu : `VALIDE SOUS RESERVE`. La ligne reste `couvert-valide`, sans mention partielle** | `camille-po` | Rien a ecrire sur la matrice. Le Done du 2026-08-30 tient tel quel |
| **Reserves A, B et C levees, avec leur critere verifie** | `camille-po` | Fermer les trois stories |
| **Reserve D, nouvelle : trois indices nomment un evenement absent de leur enonce (items 22, 23, 24)** | `camille-po` | Une story. Trois mots a changer, le critere de levee est ecrit |
| **Reserve E, nouvelle : ma reserve C ne nommait que 2 des 6 items concernes (3, 4, 5, 8) et l'item 7 garde un pronom sans antecedent** | `camille-po` | Une story. **C'est mon compte du 2026-08-30 qui etait incomplet, pas le travail de `marc-dev`** : il a corrige exactement les items que j'avais nommes |
| **Le correctif d'une de mes reserves en a cree une autre, invisible a la CI** | `yanis-qa`, `theo-archi` | Aucun test ne relie l'indice a l'enonce de son item. Ne pas compter cette propriete comme couverte |
| **`ETA-3` est tenue sans marge : sept enonces sur 24 a la borne exacte** | `camille-po` | Signalement. Toute correction qui allonge un enonce devra en raccourcir un autre |
| **`marc-dev` a aligne son propre test d'`ETA-3` sur ma convention de comptage** | `yanis-qa` | Il a trouve contre lui-meme que son test retirait le "?" avant de compter et n'aurait jamais pu attraper la breche. Le garde-fou mesure maintenant ce que je mesure |
| **`MNC-1` non couvert, et le `relecteur-pedagogique` n'est pas passe** | `sofia-sm` | Ce Done ne dit rien de la hauteur utile ni de l'orthographe |

---

### Done pedagogique - 2026-08-30, confirmation des fermetures des reserves D et E (s23) - VALIDE

**Amendement date. Les deux blocs precedents ne sont pas reecrits et restent lisibles en entier**,
avec leurs cinq reserves et leurs deux corrections de mes propres comptes. Ce bloc s'ajoute.

**Ce qui change, et ce qui ne change pas.** Le Done prononce le 2026-08-30 tient : il n'est **ni
etendu ni retire**, la ligne reste `couvert-valide` **sans mention de couverture partielle**, et le
perimetre de ce que ce paquet couvre est exactement celui du premier bloc. **Ce qui change est la
mention de reserve, et elle seule** : les reserves D et E sont levees, aucune reserve nouvelle n'est
ouverte, **les cinq reserves A a E de ce paquet sont donc toutes fermees**. Un increment sans
reserve pendante n'est plus "sous reserve" : le verdict passe a `VALIDE`.

**Note de datation.** Mes blocs precedents portent 2026-08-30 ; les commentaires ecrits dans le
paquet par `marc-dev` datent mon reaudit du 2026-09-01. La date d'auteur des commits du second tour
est 2026-08-30 pour tous. Je date ce bloc du 2026-08-30 et je signale l'ecart, sans corriger un
fichier qui n'est pas le mien.

**Correction d'un fait que j'avais enregistre.** Mes deux blocs precedents ecrivent que l'audit a
ete conduit "sur le JDK 21 de la toolchain declaree" ; **je ne l'avais pas constate**, et
l'enveloppe de lancement dit des sa premiere ligne que le JDK designe par la toolchain de type
`jdk` et de version `21` rapporte 25.0.2. Cela ne change aucun verdict de contenu ; je ne nomme
plus une version que je n'ai pas verifiee. Route comme sur l'autre fiche du sprint.

#### Ce que j'ai fait

**Audit conduit en jouant.** Seize parties completes, chaque proposition fausse pressee avant la
bonne ; **les 24 items distincts rencontres**, compte fait sur les enonces rendus par l'ecran et
non sur le fichier. Le tirage m'ayant servi la bonne reponse en premier sur deux des trois items de
la reserve D, **j'ai rejoue en pressant une proposition fausse nommee sur ces items-la** pour voir
l'indice a l'ecran, au lieu de conclure du fichier. Ce n'est pas un audit degrade.

#### Reserve D - levee

**Critere que j'avais ecrit** : *"aligner les trois indices sur le verbe de leur enonce - 'les
cahiers perdus en debut d'annee', 'les chaises perdues pour l'autre activite', 'les crayons perdus
le premier jour'."*

**Les trois indices portent exactement ces trois formulations, mot pour mot.**

| Item | Enonce | Indice servi apres l'erreur | Ecart |
|---|---|---|---|
| 22 | "La reserve a 40 cahiers, en **perd** 15 en debut d'annee." | "Soustrais d'abord les cahiers **perdus** en debut d'annee." | **aucun** |
| 23 | "L'ecole a 50 chaises, en **perd** 22 pour une autre activite." | "Soustrais d'abord les chaises **perdues** pour l'autre activite." | **aucun** |
| 24 | "La classe a 35 crayons en reserve, en **perd** 13 le premier jour." | "Soustrais d'abord les crayons **perdus** le premier jour." | **aucun** |

**Vu a l'ecran, pas deduit du fichier.** Sur l'item 24, j'ai presse 22, recu "Ce n'est pas ca.
Essaie encore !" et, sous les propositions, "Soustrais d'abord les crayons perdus le premier jour."
**Plus rien n'est vendu dans un enonce ou rien ne se vend.** Meme verification conduite sur les
items 22 et 23, avec 25 et 28.

*Recompte sur la population entiere, et pas seulement sur les trois items corriges : les 24
indices confrontes a leurs 24 enonces, chaque nom et chaque participe de l'indice cherche dans
l'enonce de son item.* **Zero ecart de l'espece qui a fonde la reserve D** - aucun indice ne nomme
un evenement absent de son enonce ni contraire a lui.

**Deux indices meritent d'etre nommes sans etre comptes en defaut**, parce qu'une lecture pressee
pourrait les y ranger et que je prefere avoir dit pourquoi je ne le fais pas. L'item 17 dit
*"Additionne d'abord les timbres des deux **collectes**"* la ou l'enonce dit "recolte 40 timbres,
puis en recoit 15 de plus", et l'item 18 dit *"les deux **dons** de livres recus"* la ou l'enonce
dit "recoit 50 livres, puis en recoit 20 de plus donnes par une association". **Dans les deux cas,
le mot de l'indice recouvre la quantite de l'enonce sans le contredire** : une reception est
compatible avec une collecte et avec un don, et la quantite designee reste la seule candidate. **Ce
n'est pas le defaut de la reserve D**, ou l'indice affirmait un evenement que l'enonce niait.

#### Reserve E - levee, et un peu au-dela de ce que j'avais demande

**Critere que j'avais ecrit** : *"porter 'celui-ci compris' / 'celle-ci comprise' sur les items 3,
4, 5 et 8 comme sur les items 6 et 7 ; et, sur l'item 7, nommer le groupe par le mot du sujet ('4
ateliers, celui-ci compris') plutot que par 'ecoles'."*

| Item | Sujet de l'enonce | Formule livree | Le pronom a-t-il son antecedent ? |
|---|---|---|---|
| 3 | La classe | "3 classes, **celle-ci comprise**" | oui, feminin |
| 4 | L'ecole | "4 ecoles, **celle-ci comprise**" | oui, feminin |
| 5 | Le club | "2 clubs, **celui-ci compris**" | oui, masculin |
| 7 | L'atelier de couture | "4 **ateliers**, **celui-ci compris**" | oui - le groupe est renomme comme je l'avais demande |
| 8 | L'atelier de peinture | "2 **ateliers**, **celui-ci compris**" | oui - **et je n'avais pas demande ce renommage-la** |

**`marc-dev` a trouve contre moi, et il a eu raison.** Ma reserve E nommait l'item 8 pour
l'ambiguite de compte, et le defaut de pronom **seulement** pour l'item 7. L'item 8 portait le meme
defaut : son sujet est *l'atelier* de peinture, son groupe etait nomme "2 **ecoles**", et appliquer
ma demande a la lettre y aurait pose un "celle-ci comprise" sans antecedent feminin - **c'est-a-dire
qu'il aurait cree, en fermant ma reserve, exactement le defaut que ma reserve corrigeait ailleurs.**
Il a applique l'esprit et pas la lettre. **C'est la troisieme fois de ce sprint qu'un membre de la
squad complete un de mes comptes**, et c'est la conduite qui rend la porte utile.

**Vu a l'ecran** sur les six items (3, 4, 5, 6, 7, 8). La lecture qui excluait le groupe initial
n'est plus possible sur aucun d'eux.

#### `ETA-3` - le prix paye, mesure

`marc-dev` a du retirer trois mots a l'enonce de l'item 5 ("lors du tournoi") pour loger les deux
mots de "celui-ci compris". **C'est ce que j'avais annonce en ecrivant la reserve E** : le paquet
etait a la borne, cet ajout devait etre compense.

*Mesure sur la population entiere des 24 enonces, par le decompte de `ETA-3` - un mot est une suite
de caracteres sans espace, le point d'interrogation compris des qu'une espace l'en separe.* **De 22
a 30 mots. Aucun enonce ne depasse 30. La borne est tenue.**

**Je corrige un chiffre qui vient de moi et que le fichier a recopie.** Le commentaire du paquet
ecrit *"sept enonces a la borne exacte de 30 mots"*. **C'etait vrai avant le correctif, ce ne l'est
plus apres.** Les enonces a exactement 30 mots sont maintenant **six** - items 6, 7, 11, 13, 17,
19 - parce que l'item 5, qui etait le septieme, est descendu a 29 du fait du correctif lui-meme. Le
chiffre est le mien, date de mon reaudit ; il a ete recopie sans etre refait apres un changement qui
le rendait faux. **Non bloquant, et ce n'est pas une reserve : c'est un chiffre a rafraichir dans un
commentaire, pas un defaut de contenu.**

**Une observation que je nomme sans en faire une reserve**, comme je l'avais fait pour le mot
"autre" retire de l'item 17 : les trois mots retires sont *"lors du tournoi"*, c'est-a-dire la
situation qui donnait sa raison d'etre aux medailles. L'enonce reste resoluble et le calcul est
inchange ; il est seulement un peu plus sec. **C'est le cout de `ETA-3` sur un paquet qui n'a plus
de marge, et je l'ai accepte en ecrivant la borne.**

#### Les conditions recomptees apres correctif, sur la population entiere

*Population : les 24 items. Recomptees sur l'arbre reaudite, jamais reprises de mes blocs
precedents ni de la declaration de l'auteur.*

| Condition | Verdict | Mesure |
|---|---|---|
| `ETA-1` | **tenue** | 24 items a exactement deux etapes, la premiere produisant la quantite que la seconde consomme |
| `ETA-2` | **tenue** | add **15**, sub **16**, mul **11**, div **6**. *Unite de compte : l'item ; une operation par etape.* Plancher 3 |
| `ETA-3` | **tenue** | Maximum **30 mots** sur les 24 enonces, borne 30. **Six** enonces a la borne exacte |
| `ETA-4b` | **tenue** | **Les 48 distracteurs recalcules depuis les nombres de l'enonce et les deux familles declarees : 0 ecart sur 24 items.** 16 items en (a) + (b), 8 en (a) + (c). Famille (a) sur les 24 |
| `ETA-5` | **tenue** | Aucun operande 0 ni 1 sur les 72 operandes ; aucun resultat intermediaire egal au resultat final |
| `ETA-6` | **tenue** | 72 propositions, entiers positifs, deux a deux distinctes dans chaque item |
| `ETA-7` | **tenue** | **8 petite / 8 milieu / 8 grande**, plancher `arrondi superieur(24/4)` = 6. Inchange : aucun nombre n'a bouge |
| `ETA-8` | **tenue** | 24 sujets collectifs, aucun role distribue par genre, aucun enfant nomme. Le passage de "ecoles" a "ateliers" sur les items 7 et 8 ne change pas ce verdict |
| `ETA-9` | **tenue sur 24 items sur 24** | Onze mots au plus, borne 12 ; aucun indice ne revele un resultat ; **les 24 indices sont coherents avec leur enonce, la reserve D est fermee** (deux formulations larges nommees ci-dessus, sans ecart). Vus a l'ecran apres erreur |
| `ETA-10` | **tenue** | 24 items |

**La plausibilite de la famille (c)** comme melecture reelle - la clause qui a fonde la reserve B et
qui reste dans ma seule main - **tient sur les huit items concernes** depuis le passage a "perd", et
les indices alignes la renforcent au lieu de la contredire.

#### Correction d'un constat que j'avais ecrit sur ce paquet, avant de m'en servir

Le 2026-08-30, premier bloc, j'ai ecrit sous `ETA-9` : *"Les 24 indices donnent la methode de la
premiere etape seule, jamais un resultat, **et sont propres a leur item**."* **La derniere clause
est fausse, et elle l'etait deja quand je l'ai ecrite** : les items 11, 12 et 13 partagent le meme
indice mot pour mot - *"Multiplie d'abord le nombre de plateaux par les parts de chacun."* Il y a
**22 textes d'indice distincts pour 24 items**, pas 24. *Verifie sur l'arbre d'aujourd'hui et sur
celui de mon premier audit : le doublon est le meme aux deux dates, le correctif n'y est pour
rien.*

**Ce que cela change au verdict : rien, et je refuse de faire semblant du contraire.** `ETA-9`, tel
que je l'ai arbitre avant le sprint, exige trois choses et trois seulement : la methode de la
premiere etape seule, jamais un resultat, douze mots au plus. **Elle n'a jamais exige qu'un indice
soit unique.** "Propres a leur item" etait une glose que j'ai ajoutee dans un bloc d'audit, pas une
condition ; et l'indice partage est **juste** pour les trois items qu'il sert, qui posent la meme
premiere etape. **Transformer aujourd'hui ma glose en exigence serait deplacer mes poteaux au
moment ou je valide.** Je ne le fais pas. Je corrige seulement le fait, parce que je l'ai affirme
sans le compter.

#### Condition nouvelle et datee du 2026-08-30, non bloquante, qui n'est pas une reserve

**Ce paquet est construit sur peu de trames, et je ne l'avais jamais mesure.**

*Mesure sur la population entiere des 24 items, par le couple d'operations declare en `ETA-4b` :*
**sept couples distincts pour 24 items** - `add,sub` 6 items, `sub,add` 4, `add,mul` 3, `sub,mul`
3, `mul,div` 3, `div,sub` 3, `mul,add` 2. **Aucun item n'est seul de sa forme.** Le groupe
`add,sub` se scinde en deux trames lexicales (le pret de la bibliotheque, et le "de plus" suivi
d'un don), ce qui fait huit trames lexicales pour 24 items.

**Consequence, et elle est certaine, pas probable** : une partie compte dix manches tirees de 24
items repartis sur sept couples d'operations ; **l'enfant rencontre donc necessairement au moins
deux items de meme forme dans chaque partie**, et le plus souvent bien davantage.

**Ce que j'en dis, des deux cotes.** Repeter une procedure sur des nombres differents est une facon
legitime de l'installer, et c'est meme ce qu'on fait en classe. Mais la competence visee est
*resoudre des problemes*, pas *appliquer une procedure* : le risque est que l'enfant reconnaisse la
trame au lieu de lire la situation, et reussisse le second item sans l'avoir compris. **Je ne
tranche pas ce point aujourd'hui et je ne le transforme pas en reserve** : le paquet etait construit
ainsi quand je l'ai valide le 2026-08-30, et le decouvrir maintenant ne me donne pas le droit de
relever la barre apres coup. **C'est une condition datee, pour la conception d'un paquet de
problemes ulterieur**, a la main de `camille-po`.

#### Le garde-fou que `marc-dev` a jete, et ce qu'il laisse a ma charge

Il a prototype une regle **generique** de coherence entre indice et enonce, l'a mesuree contre la
morphologie verbale francaise, l'a trouvee inexploitable et **l'a jetee en documentant son
renoncement** dans la Javadoc du test livre. Il a livre a la place un test etroit qui fige les
trois items que j'avais nommes. **C'est la bonne decision et c'est un resultat**, pas une absence :
un garde-fou qui crie sans raison finit desactive, et c'est alors une propriete non gardee qu'on
croit gardee - la famille de defaut que ce depot sanctionne.

**Ce que cela laisse a ma charge, et que je nomme pour que personne ne s'y trompe** : le test livre
verifie que **les indices des items 22, 23 et 24** nomment "perdu". **Il ne verifie rien sur les 21
autres.** La propriete "l'indice ne nomme pas un evenement absent de son enonce" reste **entierement
dans ma main** partout ailleurs, et le sprint vient de montrer qu'elle se casse par un correctif
d'un autre point, sans qu'une CI entierement verte le voie. **Un prochain correctif qui touche
l'enonce d'un des 21 autres items peut refaire exactement le meme defaut, et rien ne l'attrapera.**

#### `MNC-1`

**`MNC-1` s'applique a ce Done, et ce Done ne le couvre pas.** Rien ne garantit ici que l'enfant
voie, sans faire defiler la page, un element quelconque du jeu, y compris le retour qui suit sa
reponse - verdict, indice, proposition. Propriete sortie de la porte pedagogique par arbitrage de
l'utilisatrice du 2026-08-28 (`DEC-0053`) ; dette ouverte, croissante, assumee. **Aucune mesure ne
l'a instruite sur ce paquet.**

**Le rappel n'est pas de pure forme et il ne s'allege pas** : ce sont toujours les enonces les plus
longs du catalogue, six d'entre eux a la borne de 30 mots, et l'indice - le texte sur lequel portait
la reserve D - s'affiche **sous** les propositions, c'est-a-dire a l'endroit le plus expose de
l'ecran a cette dette. **La fermeture de la reserve D rend cet indice juste ; elle ne le rend pas
visible.**

#### Ce que ce Done ne dit pas

**Le `relecteur-pedagogique` n'est pas passe sur ce sprint.** L'orthographe, l'accentuation et la
ponctuation des 24 enonces, des 72 propositions et des 24 indices ne sont pas jugees par moi
(`DEC-0002` amende le 2026-08-04, `DEC-0056`). **Ce Done n'en dit rien.** Les accords de "perdus" /
"perdues" que j'ai verifies plus haut, je les ai verifies comme **sens** - le participe designe bien
la quantite de l'enonce - et non comme orthographe.

#### Ce sur quoi je reste la seule garde sur ce paquet

- **La coherence entre l'indice et son enonce, sur 21 des 24 items.** Trois seulement sont gardes,
  et par un test qui fige un cas, pas une regle.
- **La plausibilite de la famille (c)** comme melecture reelle d'un enfant : tenue sur les huit
  items depuis le passage a "perd". Aucun automate ne la voit.
- **Que les deux etapes en soient reellement deux** : tenue sur 24.
- **`ETA-8`**, les presupposes sociaux et les roles de genre, et **le jugement de niveau**.
- **La lisibilite reelle d'un enonce de 30 mots pour un CM1** - ma borne est un decompte, pas une
  mesure de lecture.

#### Ce que je route

| Suite | Destinataire | Ce que je demande |
|---|---|---|
| **Reserves D et E levees, avec leur critere verifie en jouant. Les cinq reserves A a E sont fermees** | `camille-po` | Fermer les deux stories. **Aucune reserve nouvelle** |
| **Verdict porte de `VALIDE SOUS RESERVE` a `VALIDE`. Le perimetre du Done ne bouge pas** | `camille-po` | **Rien a ecrire sur la matrice** : la ligne est deja `couvert-valide` sans mention partielle, et elle le reste. Seule la mention de reserve disparait |
| **`marc-dev` a corrige sur l'item 8 un defaut de pronom que je n'avais nomme que sur l'item 7** | `sofia-sm`, `camille-po` | Appliquer ma reserve a la lettre aurait cree le defaut qu'elle corrigeait. C'est un compte de moi complete par un tiers, le troisieme du sprint |
| **Le commentaire du paquet ecrit "sept enonces a la borne exacte" ; ils sont six depuis le correctif** | `marc-dev`, par `sofia-sm` | Chiffre a rafraichir. **C'est mon chiffre**, recopie sans etre refait apres un changement qui le rendait faux. Non bloquant |
| **Ma glose "les 24 indices sont propres a leur item" etait fausse : 22 textes distincts pour 24 items** | `camille-po`, `yanis-qa` | **Correction de mon propre constat, sans consequence de verdict** : `ETA-9` n'a jamais exige l'unicite. **Ne pas en faire une exigence retroactive** |
| **La coherence indice/enonce reste non gardee sur 21 items sur 24** | `yanis-qa`, `theo-archi` | Le test livre fige trois cas nommes. **Ne pas compter la propriete comme couverte.** Le renoncement de `marc-dev` au garde-fou generique est le bon choix et ne change pas ce fait |
| **Sept couples d'operations pour 24 items ; aucun item seul de sa forme ; deux items de meme forme au moins par partie de dix manches** | `camille-po` | **Condition datee du 2026-08-30 pour un paquet de problemes ulterieur.** Pas une reserve, pas un motif : ce paquet etait ainsi quand je l'ai valide |
| **`MNC-1` non couvert, et il pese plus ici qu'ailleurs** | `sofia-sm` | Enonces les plus longs du catalogue, indice affiche sous les propositions |
| **Le `relecteur-pedagogique` n'est pas passe** | `sofia-sm` | Ce Done ne dit rien de l'orthographe ni de la ponctuation |
