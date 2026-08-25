# s20 - la piste verticale, chiffree sur l'application servie

- **Auteur** : theo-archi. **Date** : 2026-08-25. **Branche** : `s20-theo`, sur `round-two` a
  `c48c0e6`.
- **Fait declencheur** : `camille-po` ne maintient pas le correctif de largeur, declare la valeur de
  620 px exclue, suspend toute valeur de plafond et **rouvre la famille verticale** - ecartee a
  l'ouverture de s19 pour un motif qui n'existe plus (la famille horizontale couvrait presque tout).
- **Ce qui m'etait demande** : chiffrer la piste verticale, et dire sans l'habiller si elle ne tient
  pas.
- **Ce que je ne fais pas** : aucune decision de produit ni de pedagogie, aucun Done, aucune valeur
  de marge, aucun arbitrage sur `DEC-0040`.

## 0. Methode, et la validation que je fais avant de croire mes propres chiffres

**Outils verses avec ce rapport**, dans `squad/outils/theo-archi/` :
`mesurer-budget-vertical.py` (un jeu, une largeur) et `campagne-budget-vertical.sh` (tous les jeux
du catalogue servi, une largeur). Ils reprennent la plomberie de navigateur de
`squad/outils/nadia-prof/balayage-largeur-rangee.py` par chargement du module, jamais par recopie.

Trois differences avec les scripts existants, et chacune change un chiffre :

1. **Aucun plafond n'est injecte.** Les scripts de `nadia-prof` mesurent avec une declaration
   injectee, parce qu'ils servaient a choisir une valeur de plafond. Ici il n'y a plus de plafond a
   choisir : je mesure le produit **tel qu'il est servi aujourd'hui**.
2. **Je lis le bas de `.message-jeu`**, le bloc entier - signal, verdict, explication, indice -, et
   non le bas du texte du verdict. **C'est la seule cause de l'ecart avec les chiffres que le depot
   cite depuis s19**, et elle explique tout l'ecart : voir la section 8.
3. **Je pousse chaque item jusqu'a l'etat le plus charge** (trois echecs, la revelation), l'etat que
   le critere de `S19-6a` nomme. Un seul paquet servi porte des explications (`cp-le-bon-son`, 6
   items sur 15) : sur les quatorze autres, la revelation n'ajoute aucune boite, et les rapports le
   disent item par item plutot que de le supposer.

**Perimetre derive, jamais une liste** : les jeux viennent de `catalogue.yaml`, les items du chargeur
reel. `bandit-manchot-additions-cp` est le seul jeu servi absent des mesures, et il est **declare
saute** par le script : son paquet est combinatoire, il n'a aucun item a lire. **15 jeux mesures sur
16 servis.** Chaque rapport dit combien d'items il a reellement vus ; une campagne qui en voit moins
que le paquet n'en contient ne ment pas sur sa couverture, elle la declare.

**Environnement** : Chrome pilote par son protocole de deverminage, `deviceScaleFactor` a 1, mode non
mobile ; application servie depuis le fat jar de cet arbre, lance sur le **JDK 21.0.10** de la
toolchain declaree. **`scrollY = 0` verifie a chaque lecture** - zero lecture a defilement non nul
sur les 887 releves des trois campagnes.

### Validation du harnais contre le dossier du depot, avant de l'etendre

Je ne publie pas un chiffre neuf avant que l'outil ait reproduit un chiffre deja au dossier. Quatre
reproductions, les trois premieres exactes au centieme :

| Chiffre deja au dossier | Source | Ce que mon harnais rend |
|---|---|---|
| bas du verdict **504,19 px**, marge de 46,81 px sous un pli a 551 | `ADR-0059`, section "ce qui reste ouvert" | `.verdict` bas = **504,19** sur tout item a une rangee |
| bas du verdict **594,19 px** dans la bande | `nadia-prof`, `FICHE-bandit-manchot-synonymes-cm1` | `.verdict` bas = **594,19** sur *"Cet exercice de mathematiques est « difficile »"*, item a deux rangees |
| pile boite par boite a 640 x 551 (`.propositions` 367,19 -> 441,19 ; `.cabinet` 327,19 -> 465,19) | `ines-design`, `proposition-ines-plafond-syn16.md`, section 4 | **identique**, au centieme |
| indice coupe sur **6 a 7 items sur 24** de "Le bon petit mot" sous 720 px | `nadia-prof`, `FICHE-bandit-manchot-homophones-cm1`, `BPM-8` | **8 items** sur les 22 vus, meme ordre de grandeur |

Le harnais reproduit donc trois releves independants faits par deux personnes differentes. Ce qui
suit n'est pas une methode neuve qui rendrait des chiffres neufs ; c'est **la meme mesure, sur un
perimetre que personne n'avait couvert**.

## 1. Le fait qui reorganise tout le reste : la bande 576-719 px est **une seule mise en page**

**251 items mesures aux deux bornes de la bande, 576 px et 719 px. Le bas de `.message-jeu` est
identique au centieme sur les 251. Zero exception.**

Ce n'est pas une surprise une fois ecrit, et c'est exactement l'arithmetique d'`ADR-0054` reprise par
`ADR-0059` : dans toute la bande, la feuille tierce capte la zone a 540 px, donc la rangee recoit
500 px **quelle que soit la largeur du viewport**. Le rendu ne peut pas varier.

Trois consequences immediates :

- **mon propre avis de ce sprint disait faux** en supposant 576 px "le pire cas de la bande" : il n'y
  a pas de pire cas, il n'y a qu'un cas ;
- **une mesure prise a 640 px ou a 700 px vaut pour la bande entiere** - le releve d'`ines-design` a
  640 px et celui de `nadia-prof` a 700/719 px decrivent le meme ecran, et se comparent directement ;
- **`SYN-16` et `BPM-8` ne sont pas deux defauts de mecanismes differents.** `DEC-0043` les a scindes
  au motif que l'un relevait de la largeur et l'autre de la hauteur de contenu. Mesure a l'appui, ce
  sont **le meme ecran, la meme cause et la meme population** : le bloc de message passe sous le pli,
  sur dix jeux, dont ces deux-la.

## 2. Le deficit a combler, sur la bande

Etat le plus charge, pli a 551 px, aucune injection. Le deficit vaut `bas de .message-jeu - 551`.

| Jeu | items vus | sous le pli | pire deficit | fermes par 16 px | par 80 px | par 136 px |
|---|---|---|---|---|---|---|
| `bandit-manchot-grands-nombres-cm1` | 26 | 20 | **308,19** | 0 | 7 | 9 |
| `bandit-manchot-accord-sujet-verbe-cm1` | 19 | 19 | 200,19 | 0 | 6 | 14 |
| `bandit-manchot-synonymes-cm1` | 15 | 14 | 152,19 | 1 | 9 | 10 |
| `bandit-manchot-antonymes-cm1` | 17 | 10 | 122,19 | 2 | 7 | 10 |
| `bandit-manchot-perimetre-cm1` | 19 | 19 | 92,19 | 9 | 9 | 19 |
| `bandit-manchot-accords-groupe-nominal-cm1` | 20 | 7 | 68,19 | 0 | 7 | 7 |
| `bandit-manchot-conversions-unites-cm1` | 21 | 2 | 44,19 | 0 | 2 | 2 |
| `bandit-manchot-problemes-additifs-cp` | 20 | 20 | 32,19 | 7 | 20 | 20 |
| `bandit-manchot-homophones-cm1` | 22 | 8 | 32,19 | 0 | 8 | 8 |
| `bandit-manchot-conjugaison-present-cm1` | 23 | 15 | 32,19 | 0 | 15 | 15 |
| cinq autres jeux | 92 | **0** | - | - | - | - |
| **total** | **294** | **134 (46 %)** | **308,19** | **19** | **90** | **114** |

**Dix jeux sur quinze sont touches, et 46 % des items servis.** Le chiffre que le depot portait -
43,19 px, puis 68,19 px - decrivait un item d'un paquet. Le deficit de la population est
**308,19 px**.

## 3. Le budget vertical recuperable, re-mesure et non repris

`ines-design` a conclu que le seul terme recuperable sans rouvrir un arbitrage acte etait la marge du
score, 16 px au mieux. **Je re-mesure plutot que de conclure de son travail** : elle l'a fait quand
la question etait secondaire, et elle n'a pas cherche au-dela de ce qui etait gratuit.

Termes verticaux **independants du contenu**, lus sur le style calcule du rendu servi :

| Terme | Valeur mesuree | Etat |
|---|---|---|
| `.entete` rembourrage haut + bas | 16 + 16 = **32 px** | generique a tous les ecrans, jamais resserre |
| `.zone-jeu` rembourrage haut | **32 px** | generique a tous les ecrans, jamais resserre |
| `.score` marges haut + bas | 8 + 8 = **16 px** | generique, jamais cite dans un arbitrage |
| **sous-total sans rouvrir d'arbitrage** | **80 px** | |
| `.cabinet-bandeau` | 32 px | meuble, gele par trois arbitrages (S8-1, S10) |
| `.cabinet-socle` | 16 px | idem |
| `.cabinet` bordure haute | 8 px | idem |
| **plafond absolu, meuble compris** | **136 px** | |
| `.consigne` / `.enonce` marges | 4 px chacune | deja resserrees a S10 pour `M4` |
| `.propositions` ecart | 1 rem | plancher nomme par `nadia-prof` a S3-5 |
| `.icone-signal` | 3 rem | plancher d'accessibilite, mandat d'`ines-design` |

**Le chiffre de 80 px d'`ines-design` se reproduit exactement.** Sa qualification aussi : sur ces
80 px, **16 px seulement** se prennent sans toucher a un ecran autre que celui du jeu.

## 4. Ce que ce budget ferme, et ce qu'il ne ferme pas

Sur les 134 items sous le pli, tries par nombre de rangees de propositions :

| Rangees | items sous le pli | deficit min | median | max | fermes par 16 px | par 80 px | par 136 px |
|---|---|---|---|---|---|---|---|
| 1 | 76 | 2,19 | 32,19 | 80,19 | 19 | 63 | **76** |
| 2 | 48 | 32,19 | 68,19 | 200,19 | 0 | 27 | 38 |
| 3 | 10 | 164,19 | 212,19 | 308,19 | **0** | **0** | **0** |

**C'est la ligne qui decide.** Le budget vertical, meme pousse a son plafond absolu en supprimant le
visage et le socle du meuble, ne touche **aucun** des dix items a trois rangees. Il ne peut pas :
`.propositions` mesure 74 px a une rangee, 164 px a deux, **398 px a trois** - un terme qui depasse a
lui seul le budget entier, et qui n'est pas un terme vertical mais la consequence d'une largeur
demandee superieure a la largeur offerte.

## 5. Verdict

**La piste verticale ne tient pas comme fermeture de la bande, et je le dis sans l'habiller.**

- A son budget honnete - 16 px, sans toucher a un autre ecran - elle ferme **19 items sur 134**.
- A son budget maximal sans rouvrir d'arbitrage - 80 px, en vidant le rembourrage haut de **tous**
  les ecrans de l'application, accueil compris - elle ferme **90 sur 134**.
- A son plafond absolu - 136 px, en rouvrant le meuble que `nadia-prof` a arbitre trois fois - elle
  ferme **114 sur 134**, et laisse vingt items dont le pire manque encore 172 px.
- Le critere de `S19-6a`, *"sur la totalite des items"*, **n'est atteignable par aucune valeur de ce
  budget**.

**Ce serait malhonnete de s'arreter la, parce qu'elle n'est pas nulle.** Elle est la **seule** famille
qui touche les 76 items a une rangee, et elle ferme **entierement trois jeux sur les dix touches**, a
80 px : `bandit-manchot-problemes-additifs-cp` (20 items sur 20),
`bandit-manchot-conjugaison-present-cm1` (15 sur 15) et **`bandit-manchot-homophones-cm1` (8 sur 8) -
c'est-a-dire `BPM-8` en entier**.

**Ce que cela change a mon avis de ce sprint** : j'ai ecrit `S19-6b` infaisable ce sprint, au motif
qu'une mesure prise avant `S19-6a` decrirait une mise en page sur le point de changer. `S19-6a` est
retiree, donc ce motif est tombe - et la mesure existe desormais. `BPM-8` est **le cas le moins cher
des dix**, entierement ferme par le plus modeste des budgets verticaux. Il n'a plus besoin d'un avis
de faisabilite : il a une mesure.

## 6. Le fait que je n'allais pas chercher, et qui deplace le probleme

J'ai passe la meme campagne **a la reference du produit, 744 x 551** (`DEC-0040`), pour savoir de
combien la bande s'ecarte de l'etat normal. Elle ne s'en ecarte pas autant qu'on le croit.

**95 items sur 296 (32 %) ont leur bloc de message sous le pli a la reference, sur dix jeux, pire
deficit 164,19 px.**

| Jeu (744 x 551) | items vus | sous le pli | pire deficit |
|---|---|---|---|
| `bandit-manchot-problemes-additifs-cp` | 21 | 21 | 2,19 |
| `bandit-manchot-perimetre-cm1` | 20 | 19 | 134,19 |
| `bandit-manchot-accord-sujet-verbe-cm1` | 20 | 19 | 32,19 |
| `bandit-manchot-grands-nombres-cm1` | 22 | 13 | **164,19** |
| `bandit-manchot-accords-groupe-nominal-cm1` | 22 | 8 | 68,19 |
| `bandit-manchot-synonymes-cm1` | 16 | 7 | 32,19 |
| `bandit-manchot-conjugaison-present-cm1` | 21 | 3 | 32,19 |
| `bandit-manchot-homophones-cm1` | 23 | 2 | 2,19 |
| `bandit-manchot-conversions-unites-cm1` | 20 | 2 | 44,19 |
| `bandit-manchot-antonymes-cm1` | 17 | 1 | 2,19 |
| cinq autres jeux | 95 | **0** | - |

**Ce que je dis de ce chiffre, et ce que je me garde d'en dire.** Je ne le nomme pas un defaut et je
ne prononce aucun Done : que l'indice doive ou non etre au-dessus du pli a la reference est la
question de `nadia-prof`, et une partie de ces items est deja couverte par des reserves **non
bloquantes** qu'elle a explicitement acceptees (`SYN-16`, `BPM-8`). Ce que j'apporte est que **la
population n'avait jamais ete comptee** : elle vaut 95 items sur dix jeux, dont deux
(`cm1-synonymes`, `cm1-antonymes`) sont `couvert-valide`.

**Et ce que cela fait au cadrage** : `SYN-16` a ete instruit pendant trois sprints comme un defaut de
la bande 576-719 px. Il ne l'est pas. La bande **aggrave** un ecart qui existe deja a la reference,
elle ne le cree pas - 95 items a la reference, 134 dans la bande, la meme population plus large.
Un correctif borne a la bande, de largeur comme de hauteur, n'aurait jamais touche les 95.

## 7. La troisieme option de `camille-po`, chiffree

Elle a nomme, sans la choisir, la revision de la bande visee elle-meme - un amendement de `DEC-0040`
plutot que du produit. Cette option n'avait aucun chiffre ; elle en a un maintenant.

**La mise en page ne depend pas de la hauteur du viewport** : seule la position du pli change. La
courbe est donc exacte et non interpolee. Sur les 294 items de la bande :

| Hauteur de viewport | items encore sous le pli |
|---|---|
| **551 px** (reference actuelle) | 134 sur 294 (46 %) |
| 600 px | 58 (20 %) |
| 650 px | 29 (10 %) |
| 700 px | 20 (7 %) |
| 750 px | 9 (3 %) |
| 800 px | 4 (1 %) |
| **860 px** | **0** |

C'est un arbitrage de `camille-po` et de l'utilisatrice, pas le mien. Je note seulement que **les
cinquante premiers pixels de hauteur valent plus que le budget vertical entier** : porter le pli de
551 a 600 px ferme 76 items, la ou 136 px de budget vertical en ferment 114 au prix de trois
arbitrages rouverts et de tous les ecrans de l'application modifies.

## 8. Ce que j'ai trouve de faux, chez moi d'abord

1. **Chez moi, avis de faisabilite de s19, section 6.2** : *"il manque 43,19 px"*. Le chiffre est
   juste **pour le bas du texte du verdict**, il est faux comme deficit de l'ecran. Sur le meme item,
   au meme viewport, l'indice - qui vit sous le verdict et qui est l'objet meme de `BPM-8` - porte le
   deficit reel a **152,19 px**. J'ai chiffre une famille en oubliant la boite pour laquelle l'autre
   moitie du defaut avait ete ouverte, et ce chiffre a servi trois sprints a comparer deux pistes.
2. **Chez moi, meme section** : j'ai qualifie la famille verticale de *"bien plus petite"* que la
   famille horizontale. Mesure : 308,19 px a trouver contre 80 px disponibles. Elle etait la plus
   grande des deux.
3. **Chez moi, avis de ce sprint, section 4** : j'ai traite 576 px comme le pire cas de la bande. Il
   n'y a pas de pire cas - 251 items rendent le meme pixel a 576 et a 719.
4. **Chez moi, avis de ce sprint, section 4.3** : `BPM-8` infaisable ce sprint. Le motif etait la
   dependance a `S19-6a`, qui est retiree ; le verdict tombe avec lui.
5. **Dans `DEC-0043`** : la scission de `S19-6` au motif de *"mecanismes differents"*. Meme ecran,
   meme cause, meme population - dix jeux, pas deux.
6. **Le brief qui me route** dit que le releve d'`ines-design` *"rend la piste difficile"*. Il la rend
   **impossible**, et son propre document le disait deja a demi-mot sans pouvoir le prouver : elle
   avait le bon chiffre de budget (80 px) et le mauvais chiffre de cible (43,19 px, le mien). Avec la
   vraie cible, sa recommandation de ne pas ouvrir ce chantier etait juste pour une raison plus forte
   que celle qu'elle a pu ecrire. **Sa conclusion tient ; c'est ma cible qui etait fausse.**
7. **Le brief** dit que la revision de `nadia-prof` etait de *"68,19 px pour les items a quatre
   propositions"*. Verifie et exact : `cm1-accords-groupe-nominal` porte bien un pire deficit de
   **68,19 px** dans la bande, au centieme. Je le confirme plutot que de le corriger.

## 9. L'`ADR` : je ne l'ecris pas, et le motif n'est pas celui de s19

Mon constat tient : **aucun garde-fou de ce depot n'epingle un terme vertical.** J'avais refuse
d'ecrire cet `ADR` a s19 avec un motif precis - une table epinglee contre une mise en page que le
correctif de `S19-6` allait changer serait perimee le jour de son premier usage. **Ce motif est
tombe** : `camille-po` retire le correctif, la mise en page ne changera pas de ce fait.

Je ne l'ecris pas quand meme, pour un motif different, et je prefere l'ecrire plutot que de livrer
une regle qui ne mord pas :

**la table que j'avais promise n'aurait pas attrape le lot qui declenche la dette.** Le declencheur
que j'ai nomme est `S20-1`, dont le renfort `.mot-designe` ajoute 7,19 px a la boite de ligne de
`.enonce`. Or cette croissance est **dependante du contenu** : elle nait d'un `font-size` sur un
fragment de phrase, pas d'une declaration de rembourrage. Une table de termes fixes - la seule qu'un
test de CI puisse lire, faute de navigateur dans `mvn verify` - serait **verte** pendant que
`.mot-designe` pousse 46 % des items un peu plus bas. Ce serait une regle decorative au sens exact ou
ce depot emploie le mot : elle interdirait quelque chose de reel, mais **pas ce qui a motive son
ecriture**.

**Ce que je livre a la place, et qui n'est pas rien** : les deux outils de ce rapport sont commites.
Ils mesurent ce qu'aucun test de CI ne peut mesurer, sur le materiau reel, en une commande par
largeur. **Le declencheur devient donc une mesure et non une table** : tout lot qui touche un terme
vertical du flux au-dessus du verdict - `S20-1` en tete - fait tourner
`campagne-budget-vertical.sh` avant et apres, et porte les deux chiffres dans son commit. Ce n'est
pas un garde-fou automatique et je ne le presente pas comme tel : c'est une mesure obligatoire, plus
faible qu'un test, et **plus honnete qu'un test vert qui ne regarde pas l'objet**.

**Ce qui rendrait l'`ADR` du** : le jour ou un lot de forme touche un terme **fixe** de cette pile
(`.entete`, `.zone-jeu`, `.score`, le meuble, les rembourrages de `.signal` et de `.indice`), la table
de ces termes-la devient a la fois ecrivable, lisible par un test de CI et non decorative - elle
attraperait exactement ce que ce lot ferait. Declencheur ecrit, date du 2026-08-25.

## 10. Ce que je route

- **`camille-po`** : la piste verticale ne ferme pas la bande (section 5) ; elle ferme entierement
  trois jeux sur dix, dont `BPM-8` ; le probleme n'est pas celui de la bande mais celui de la
  reference (section 6, 95 items sur 296) ; et sa troisieme option est chiffree (section 7 - le pli a
  860 px ferme tout, a 600 px il ferme 76 items sur 134).
- **`nadia-prof`** : 95 items sur dix jeux ont leur bloc de message sous le pli **a la reference**,
  dont sept de `cm1-synonymes` et un de `cm1-antonymes`, tous deux `couvert-valide`. Combien de ces
  95 relevent d'une reserve qu'elle a deja acceptee, et combien n'ont jamais ete regardes, est sa
  lecture et pas la mienne.
- **`sofia-sm`** : `S19-6b` n'a plus besoin d'un avis de faisabilite, il a une mesure ; et tout lot
  qui touche un terme vertical porte les deux passages de la campagne (section 9).
- **`ines-design`** : sa conclusion de s19 tient, et pour une raison plus forte que celle qu'elle
  pouvait ecrire - la cible qu'on lui avait donnee etait fausse, pas son budget.

## Impact

- `squad/outils/theo-archi/mesurer-budget-vertical.py` : cree.
- `squad/outils/theo-archi/campagne-budget-vertical.sh` : cree.
- `squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/mesure-budget-vertical.md` (ce
  fichier) : cree.
- `squad/sprints/s20-mot-cible-designe-et-conception-de-la-saisie/avis-faisabilite.md` : amende
  (trois de mes affirmations tombent avec cette mesure).
- `squad/decisions/adr/ADR-0059-...md` : amende une seconde fois (la dette verticale recoit sa
  mesure, et son declencheur change de nature).
- Aucun fichier sous `domain/`, `application/`, `adapters/` ni `bootstrap/` n'est modifie.
