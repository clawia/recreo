# [Sprint s34] Verdicts pedagogiques - `nadia-prof`, instance C - 2026-09-11

- **Instance** : **C** (`PAR-2`, matiere reservee : education physique et sportive)
- **Perimetre** : `S34-EPS-SEC` (`EPS-CP-04`), `S34-EPS-JEU` (`EPS-CP-08`),
  `S34-EPS-CORPS-EFFORT` (`EPS-CP-10`, `EPS-CP-11`)
- **Arbre audite** : `a7852398`, quatre branches fusionnees, porte technique verte annoncee par
  `sofia-sm` sur ses quatre jobs (planning, section 14), 1464 tests
- **Commits audites** : `e35ceb4e` (`S34-EPS-SEC`), `a3391bfc` (`S34-EPS-JEU`), `8ee750f0`
  (`S34-EPS-CORPS-EFFORT`)
- **Charge** : 2,5 sur 4,0 (planning, section 5)

## 0. Ce que j'ai joue, et ce que je n'ai pas pu voir

**Jar reconstruit depuis un worktree dedie, application lancee sur un port local, arretee par
l'identifiant de son processus.** Huit parties completes, quatre-vingts manches :

| Paquet | Parties | Ce que j'ai fait |
|---|---|---|
| `cp-eps-regles-de-securite` | 2 | Les onze items vus a l'ecran, erreur volontaire sur chacun pour lire l'indice servi |
| `cp-eps-regles-jeu-collectif` | 2 | Les onze items vus, dont **les deux items de l'epervier dans la meme partie** - la configuration exacte qui avait produit `JEU-R9` |
| `cp-eps-corps-effort` | 4 | **Les vingt-deux items vus**, erreur volontaire sur chacun ; plus une partie sans erreur, une partie avec une seule erreur, et un chemin d'echec repete seize fois sur un meme item |

**Audit degrade sur un point, declare** : joue par requetes HTTP reelles, donc **HTML servi** et non
page rendue par un navigateur. **Ni couleur, ni contraste, ni taille, ni position** - aucune
conclusion de ce document n'en depend.

**Ce que je n'ai pas audite et qui n'est pas a moi** : le palier 2 (instance B), les terrains A et B,
et le remede de `SCO-1` pris comme increment (`S34-SCO1-REMEDE`, instance A). J'ai rencontre son
ecran de fin en jouant mes trois paquets ; ce que j'en dis est en section 6, et **il ne pese sur
aucun de mes quatre verdicts**.

## 1. Les quatre verdicts, par ligne

| Ligne | Increment | Verdict | Done pedagogique |
|---|---|---|---|
| **`EPS-CP-04`** - regles de securite | `S34-EPS-SEC` | **VALIDE SOUS RESERVE** | **prononce** |
| **`EPS-CP-08`** - regles d'un jeu collectif | `S34-EPS-JEU` | **VALIDE SOUS RESERVE** | **prononce** |
| **`EPS-CP-10`** - effets de l'effort sur le corps | `S34-EPS-CORPS-EFFORT` | **VALIDE SOUS RESERVE** | **prononce** |
| **`EPS-CP-11`** - echauffement, hydratation, recuperation | `S34-EPS-CORPS-EFFORT` | **VALIDE SOUS RESERVE** | **prononce** |

**Quatre lignes fermees la ou j'en attendais une a deux** (arbitrage prealable de s34, sections 5.3,
5.4 et 5.6 : *0 a 1* sur chacune des deux reprises, *0 a 1 pour la paire* du lot du corps, et je
penchais vers 0 sur cette derniere). **Je dis d'abord pourquoi ce n'est pas de la complaisance**, et
la reponse tient en une phrase : **les quatre modes de defaillance que j'avais nommes d'avance sur le
lot du corps ne sont pris aucun des quatre**, et les deux motifs bloquants des reprises sont payes a
leur objet, verifie a l'ecran et non sur le diff. Ce que ce sprint produit de plus utile est en
section 4.

**Les quatre lignes ferment avec des reserves ouvertes, et aucune n'est bloquante.** Le detail ligne
par ligne est en section 3 ; les reserves au format `RES-1` sont en section 5.

## 2. Corrections a mes propres constats, avant de m'en servir

**Six de mes ecrits sont faux, incomplets ou inapplicables tels quels. Cinq vont contre moi, et deux
ont directement produit un defaut de ce sprint.**

### 2.1 Mon refus d'avance numero 9 abrege `EPS-3` d'un mot, et cet abregement rendrait `EPS-CP-11` injouable

Le refus 9 de mon arbitrage de s34 dit : *"et tout item dont la bonne reponse est une conduite
(`EPS-3`)"*. **`EPS-3`, dans le referentiel qui la porte, dit *"une attitude, une emotion ou une
conduite morale"*.** J'ai laisse tomber *morale* en recopiant.

**Applique a la lettre, mon abregement refuse `EPS-CP-11` en entier** : *connaitre quelques regles
d'echauffement, d'hydratation et de recuperation* n'a pas d'autre forme que *"avant un effort, on
echauffe ses muscles"*, qui est une conduite. J'aurais refuse une ligne que **j'ai moi-meme declaree
jouable** dans le referentiel (ligne 11) et **financee a 1,5 pour deux lignes**.

> **Je corrige mon abregement, date du 2026-09-11.** `EPS-3` reste ce que le referentiel dit :
> **conduite morale**. Une regle de securite ou de pratique, formulee de facon impersonnelle et
> factuelle, n'est pas une conduite morale et n'a jamais ete refusee par `EPS-3` - c'est deja la
> forme validee sur `cp-eps-regles-de-securite` (*"que doit-on faire ?"* -> *"attendre l'autorisation
> de l'adulte"*). **La contrefacon que `EPS-3` refuse est celle de la ligne 9 du referentiel** :
> noter une attitude (*"je le felicite"*), jamais enoncer une regle.
>
> **Ce que cette correction ne fait pas** : elle n'ouvre rien de neuf. `BIA-4` continue d'interdire
> qu'un item interroge l'enfant sur sa propre pratique, et le lot la tient - aucun *"que dois-tu
> faire"*, aucun *"ton corps"*.

**`sofia-sm` a vu le point avant moi** et l'a ecrit dans l'en-tete du paquet, en nommant le paquet
deja valide comme precedent. **C'est la bonne facon de traiter une de mes conditions dont la lettre
derape : la nommer, pas la contourner en silence.**

### 2.2 `EPS-7` et `EPS-8` se contredisent sur les indices, et c'est `EPS-8` qui vaut

`EPS-7` (2026-09-09) interdit qu'un indice nomme le champ lexical qui distingue la bonne reponse.
`EPS-8` (2026-09-10) exige qu'un indice apporte un fait que l'enfant ne peut pas deviner. **Sur
`cp-eps-regles-jeu-collectif`, plusieurs indices apportent le fait juste en nommant ce champ** -
l'item 2 : *"Un joueur touché en hauteur n'est pas pris."*

> **Je tranche, date du 2026-09-11 : `EPS-7` est retiree, `EPS-8` la remplace pour les indices.**
> Motif, et il est ecrit dans ma propre table de s34 : `EPS-7` est **une condition de retrait seul**,
> exactement ce que `RES-2` interdit desormais, et elle a produit `SEC-R5` le jour ou elle a ete
> appliquee. **`EPS-8` est sa reparation et dit ce qui doit occuper la place.**
>
> **Ce retrait n'abaisse la barre d'aucun increment** : un indice qui redonne la bonne reponse sans
> apporter de fait tombe sous `EPS-8`, qui est plus exigeante et non moins.

### 2.3 Le perimetre de `SEC-R5` que j'ai mesure omet l'indice de l'item 3, et c'est la que le seul defaut neuf de la reprise est apparu

Mon arbitrage (section 5.3) mesure le perimetre ainsi : *"items 3 et 8 - enonce, bonne reponse et
deux distracteurs chacun - plus les indices des items 8, 10 et 11"*. **L'indice de l'item 3 n'y est
pas.** Or la voie que j'ai recommandee remplace les items 3 et 8 par deux items neufs : **l'indice de
l'item 3 est donc un texte neuf de l'etat d'arrivee, et mon perimetre ne l'a pas compte.**

`sofia-sm` a couvert exactement le perimetre que j'ai ecrit. **Le seul defaut neuf de cette reprise
(`SEC-R6`, section 5.1) est dans le texte que mon perimetre a laisse dehors.** C'est un cas de la
partie 4 de `RES-1` - *le perimetre est mesure sur l'etat d'arrivee* - mal appliquee par son autrice
le lendemain de son ecriture. **Le defaut est le mien, et `SEC-R6` est ouverte a mon cout.**

### 2.4 Mon perimetre de `S34-EPS-JEU` transporte l'item 6 sans transporter l'objet de `JEU-R11`, et la reserve n'a pas ete payee

Mon arbitrage (section 5.4) enumere le perimetre et finit par *"plus l'indice de l'item 6"*, **sans
dire pourquoi**. La raison etait `JEU-R11`, ecrite a s33 : *l'indice presente une regle comme un
moyen d'aller plus vite*. Ce numero d'item, sorti de son objet, est devenu dans la story un critere
*"l'indice de l'item 6 (...) reste coherent avec la regle de l'epervier telle que corrigee"* - **une
exigence qui n'a rien a voir : l'item 6 porte sur les demenageurs, pas sur l'epervier.** L'indice a
ete reecrit en renforcant la formulation meme que `JEU-R11` refuse.

> **Ce que je corrige a `RES-1`, date du 2026-09-11, et qui n'est pas un durcissement** : la partie 4
> d'une reserve (le perimetre) **cite regulierement des items qui appartiennent a d'autres
> reserves**. Un numero d'item transporte sans son objet arrive chez le developpeur sans rien pour le
> guider. **Un perimetre nomme, pour chaque item qu'il cite, la reserve dont cet item vient.** C'est
> une contrainte sur ce que j'ecris, pas sur ce qu'un increment doit atteindre.
>
> **A reporter dans mon arbitrage prealable de s35, ou `RES-1` vit.** Je ne retouche pas le document
> de s34 : il est commite, et une regle datee du 11 n'a pas a s'y glisser sous la date du 10.

### 2.5 Mon refus d'avance numero 7 exige une mention *"pre-ecrite"* qui n'existe pas pour les lignes 10 et 11

Le refus 7 dit : *"Toute fermeture d'une ligne d'EPS CP sans sa mention de couverture partielle,
reprise mot pour mot de la matrice (...). La mention est pre-ecrite et je refuse la ligne sans
elle."* **Verifie dans la matrice ce jour** : les lignes 4 et 8 portent chacune une mention
pre-ecrite ; **les lignes 10 et 11 n'en portent aucune.** Appliquer la lettre de mon refus
reviendrait a refuser deux lignes parce que je n'ai pas ecrit un texte qui m'incombe.

> **Je fournis les deux mentions manquantes en section 3.4, datees du 2026-09-11.** Elles sont a
> recopier mot pour mot par `camille-po` dans la matrice. **Le refus 7 vaut ensuite comme il est
> ecrit** : une fermeture sans sa mention reste refusee.

### 2.6 Ma confirmation de grain du 2026-09-10 supposait deux taches, le paquet en declare quatre

Ma section 4.3 confirme un paquet unique pour deux lignes en disant : *"c'est bien le nombre de
taches, non de lignes, qui compte"*, et *"deux n'est pas cinq"*. **Le paquet livre declare quatre
taches** (reconnaitre l'effet, dire pourquoi, reconnaitre la regle, dire pourquoi), pour une consigne
a deux clauses. **Ma confirmation reste valable - le paquet fonctionne et je ne rouvre pas le
grain** - mais la premisse sur laquelle je l'ai rendue etait fausse, et la consequence est reelle :
**dix des vingt-deux items posent une question que la consigne n'annonce pas** (`EFF-R7`).

## 3. Les quatre lignes, une par une

### 3.1 `EPS-CP-04` - `S34-EPS-SEC` - **VALIDE SOUS RESERVE**, Done pedagogique **prononce**

**`SEC-R4` est levee, sur ses deux elements, et je l'ai verifie a l'ecran et non sur le diff.**

`sofia-sm` a pris la premiere des deux voies, celle que je recommandais : les items 3 et 8 sont
remplaces par un couple sur un agres de cour, la cage a grimper.

| Element de l'objet de `SEC-R4` | Etat | Ce que j'ai verifie a l'ecran |
|---|---|---|
| *L'enfant repart avec la regle qui protege son corps* | **rendu** | Item 3 : *"Sur la cage à grimper, avant de descendre, que doit-on vérifier ?"* -> *"qu'aucun camarade n'est en dessous"*. **La regle creditee est la regle reelle, et elle est entiere** : il n'y a pas de geste anterieur qui manquerait, comme *descendre de la trottinette* manquait |
| *La situation de l'enonce est celle que decrivent les trois propositions* | **rendu** | Les trois propositions - *"qu'aucun camarade n'est en dessous"*, *"que la maîtresse regarde"*, *"qu'on n'est pas trop haut"* - **decrivent toutes un enfant en haut d'un agres sur le point de descendre**. Aucune ne decrit quelqu'un d'autre, ailleurs |
| **Le mode de defaillance que j'avais nomme** - *ajouter le geste manquant a la bonne reponse et laisser les distracteurs decrire un pieton* | **evite** | La voie retenue supprime la situation fautive au lieu de la rapiecer |

**`SEC-R5` est levee sur les trois indices que j'avais nommes.** Items 10 et 11, lus apres un echec
volontaire : *"Une glissade est si rapide qu'on n'a pas toujours le temps de s'écarter."* et *"Un
plongeon est si rapide qu'on touche le fond avant de pouvoir réagir."* **Chacun apporte le fait qui
fait exister la regle**, et ce fait n'est pas devinable de l'enonce. L'item 8 est neuf et son indice
nomme la consequence pour l'autre enfant. **`EPS-8` tenue sur ces trois-la.**

**Le reste du paquet** : `FER-1` vacuement tenue, `FER-2` onze items, `FER-3` deux taches a six et
cinq, `BIN-1` zero item binaire, `FAB-1a`, `FAB-1b`, `FAB-1c`, `PLA-1`, `CAT-1`, `EPS-1` a `EPS-6`,
`BIA-4` tenues - **comptees a la main pour la troisieme fois, aucun test de paquet n'existant pour
l'EPS**. `COH-1`, opposable depuis ce lot, est **tenue** : les onze items ne disent rien de
contradictoire d'une meme activite, et le couple item 3 / item 8 de la cage a grimper est construit
comme le couple enseigne/juge du paquet voisin.

**Reserve neuve, non bloquante** : `SEC-R6` (section 5.1), **a mon cout** (section 2.3).

### 3.2 `EPS-CP-08` - `S34-EPS-JEU` - **VALIDE SOUS RESERVE**, Done pedagogique **prononce**

**`JEU-R9` est levee, et c'est la levee la plus propre des deux reprises.**

Les items 3 et 8 forment desormais le couple que j'avais nomme : l'item 3 (tache A) credite *"il
devient épervier à son tour"*, l'item 8 (tache B) credite *"Malo devient épervier à son tour"*. **Le
couloir et l'elimination, qui ne correspondaient a aucune regle universelle du jeu, ont disparu.**

**Verifie dans la configuration qui avait produit le defaut** : sur une de mes deux parties, **les
deux items de l'epervier sont sortis dans la meme partie de dix manches**, a six manches
d'intervalle. L'enfant qui a repondu juste au premier ne peut plus etre marque faux avec ce qu'il
vient d'apprendre. **C'est exactement l'objet de `JEU-R9`, et il est rendu.**

**`COH-1` tenue sur le paquet entier**, verifiee jeu par jeu : 1, 2, 3 soleil (items 1 et 11), chat
perche (2, 7, 9), epervier (3, 8), demenageurs (6, 10), balle au prisonnier (4), loup et moutons (5).
**Aucun couple ne se contredit.** `EPS-1` tenue : les six jeux sont universellement scolaires et
aucune regle jugee ne depend d'une variante locale non nommee.

**`JEU-R10` est levee.** Les trois propositions de l'item 10 sont desormais des consequences de meme
forme, et l'enfant doit savoir **laquelle est la sanction de ce jeu-la** - ce que ma reserve
demandait en toutes lettres.

**`JEU-R11` n'est pas levee, et `JEU-R12` non plus.** Les deux sont reconduites telles quelles,
**sans durcissement** (section 5.2). Elles etaient non bloquantes a s33 ; elles le restent, et la
ligne ferme avec elles.

### 3.3 `EPS-CP-10` et `EPS-CP-11` - `S34-EPS-CORPS-EFFORT` - **VALIDE SOUS RESERVE** chacune, Done pedagogique **prononce** sur les deux

**Les vingt-deux items joues, un par un, avec erreur volontaire sur chacun.**

**L'exactitude, d'abord, parce que c'est le poste que j'avais chiffre a 1,5 pour cette raison.** **Les
vingt-deux faits credites sont exacts** : essoufflement, frequence cardiaque, chaleur produite par le
muscle, soif par perte d'eau, retour au calme non immediat, rougeur du visage, echauffement contre la
blessure, hydratation autour de l'effort, repos apres l'effort, courbatures le lendemain. Aucune
reponse creditee n'est fausse, aucune n'est approximative au point d'egarer.

**Les quatre modes de defaillance que j'avais nommes d'avance : aucun des quatre n'est pris.**

| Mode de defaillance nomme le 2026-09-10 | Etat |
|---|---|
| Une performance chiffree presentee comme une norme (*"au bout de combien de minutes est-on essouffle ?"*) | **evite.** Aucun chiffre dans aucun item du paquet |
| Une conduite en bonne reponse a la deuxieme personne (*"que dois-tu faire si tu as soif ?"*) | **evite.** Les vingt-deux enonces sont impersonnels : *"que doit-on faire"*, *"de quoi le corps a-t-il besoin"* |
| Un indice qui decrit l'effort par une image (*"le coeur s'emballe"*) | **evite.** Les vingt-deux indices apportent un fait mecanique, jamais une image |
| Un item qui ne fixe pas le moment de l'effort dont il parle | **evite, et c'est le point le plus visible du lot.** Les vingt-deux enonces posent leur moment en tete : *"Pendant"*, *"Juste après"*, *"Le lendemain"*, *"Avant"*, *"autour d'un effort"* |

**`EPS-2` tenue** : aucun item ne compare des corps, des performances ou des capacites, et il n'y a
pas un chiffre dans le paquet. **`BIA-4` tenue** : aucun item n'interroge l'enfant sur son propre
corps ni sur sa propre pratique, y compris la consigne (*"ce que l'effort fait au corps"*, et non
*"a ton corps"*). **`EPS-3` tenue**, au sens que le referentiel lui donne (section 2.1).

**La frontiere corps-et-effort (`DEC-0083`, section 9.2) est tenue, et je l'ai verifiee item par
item.** Aucun item ne porte sur le sommeil, l'alimentation ou l'hygiene du corps. Les trois seules
mentions d'un repas ou du soir vivent **dans des distracteurs**, a l'interieur d'une question ancree
sur l'effort (*"le soir avant de dormir"* comme mauvais moment pour boire autour d'un effort, par
exemple). **Aucun item de ce paquet ne pourrait etre compte sur la ligne de sciences CM1 ni sur celle
de questionner le monde CP** ; le recouvrement que `DEC-0083` craignait n'existe pas.

**La frontiere est ecrite dans la specification du lot avant le premier item** (refus d'avance
numero 11) : elle est dans la story, mot pour mot, et repetee en tete du fichier de paquet. **Ce
refus attendait son increment depuis deux sprints ; il est paye.**

**`FER-2`, comptee a la main** : vingt-deux items, **onze par ligne** - plancher de dix par ligne
tenu. **`FER-1`** : les quatre elements enumeres par `EPS-CP-10` (essoufflement, coeur qui bat vite,
chaleur, soif) sont chacun servis en bonne reponse aux items 1 a 4 ; les trois de `EPS-CP-11`
(echauffement, hydratation, recuperation) aux items 12, 13 et 14. **`FER-3`** : quatre taches, deux
par ligne. **`BIN-1`** : zero item binaire sur vingt-deux. **`LON-1` et `LON-2`** : tenues, le plus
long enonce fait quatorze mots ; **je ne les recompte plus a la main, `ADR-0089` les execute**.

**Sept reserves neuves, toutes non bloquantes** : `EFF-R1` a `EFF-R7` (section 5.3). **Trois portent
sur `EPS-CP-10`** (`EFF-R2`, `EFF-R3`, `EFF-R5`), **deux sur `EPS-CP-11`** (`EFF-R4`, `EFF-R6`) et
**deux sur les deux a la fois** (`EFF-R1`, `EFF-R7`). **Aucune ne fait apprendre a l'enfant quelque
chose de faux sur la competence visee, et c'est pourquoi les deux lignes ferment.**

**Ce que `PAR-10` demandait et que le calendrier ne permet plus** : les reserves ouvertes par un lot
neuf devaient etre soldees dans le meme sprint. **Les quatre branches sont fusionnees et la fenetre
de developpement de s34 est fermee** ; ces sept reserves partent donc a s35. **Je le dis comme un
fait, pas comme une attenuation** : le lot a ete livre en fin de fenetre, et le prix de cette
position est que ses reserves vivent un sprint de plus. **Tarif que je rends pour leur solde : 0,5,
un rejeu** - elles vivent toutes dans un seul paquet, aucune tache n'est reecrite en entier, ni
`PAR-14` ni `PAR-15` ne mordent.

### 3.4 Les mentions de couverture partielle, exigees d'avance (refus numero 7)

**A recopier mot pour mot par `camille-po` dans la matrice. Une fermeture sans sa mention est
refusee.**

| Ligne | Mention |
|---|---|
| `EPS-CP-04` | *"connaissance de la regle de securite seule ; son respect en situation n'est pas servi"* - **deja pre-ecrite dans la matrice, a reprendre telle quelle** |
| `EPS-CP-08` | *"connaissance de la regle seule ; la tenue du role en situation reste non couverte"* - **deja pre-ecrite dans la matrice, a reprendre telle quelle** |
| `EPS-CP-10` | *"connaissance des effets de l'effort seule ; ce produit ne recoit aucun corps en mouvement et n'observe aucun effet reel sur l'enfant qui joue"* - **ecrite aujourd'hui, elle n'existait pas** (section 2.5) |
| `EPS-CP-11` | *"connaissance des regles qui entourent l'effort seule ; ce produit n'observe ni echauffement, ni boisson, ni repos reels"* - **ecrite aujourd'hui, elle n'existait pas** (section 2.5) |

**Et la mention d'ensemble du referentiel reste due des qu'une de ces lignes bouge**, mot pour mot :
*"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste et
aucune performance ne sont servis par ce produit"*. **Quatre lignes vertes sur onze ne doivent en
aucun cas se lire comme une couverture partielle de l'EPS.**

## 4. La question que ce sprint devait trancher : la lettre ou l'objet

**`sofia-sm` annonce avoir applique les trois lectures de `RES-3` avant chaque correction plutot
qu'apres. Voici ce que cela a change, mesure sur les six reserves que mon perimetre porte, et non
declare.**

| Reserve | Format dans lequel elle a ete recue | Resultat mesure a l'ecran |
|---|---|---|
| **`SEC-R4`** (bloquante) | **Format `RES-1`** : objet en deux parties, mode de defaillance nomme d'avance, perimetre mesure sur l'arrivee | **Payee a l'objet.** La voie retenue supprime la situation fautive ; le mode de defaillance que j'avais nomme n'est pas pris |
| **`JEU-R9`** (bloquante) | **Format `RES-1`** | **Payee a l'objet**, et le paquet est coherent avec lui-meme, verifie sur une partie ou les deux items sortent ensemble |
| **`SEC-R5`** (non bloquante, s33) | Ancien format, mais **perimetre reecrit au format `RES-1`** | **Payee a l'objet sur les trois items que mon perimetre nomme** ; **non portee au quatrieme texte, neuf, que la meme correction creait** - et ce quatrieme texte est hors de mon perimetre ecrit (`SEC-R6`, section 2.3) |
| **`JEU-R10`** (non bloquante, s33) | Ancien format, mais son texte de s33 portait deja *"de sorte que l'enfant doive savoir laquelle est la sanction du jeu"* - **un objet, ecrit avant que `RES-1` n'existe** | **Payee a l'objet** |
| **`JEU-R11`** (non bloquante, s33) | **Ancien format, sans objet etiquete** ; arrivee dans la story comme un numero d'item sorti de sa reserve | **Ni la lettre ni l'objet.** L'indice a ete reecrit **en renforcant la formulation meme que la reserve refuse** (section 2.4) |
| **`JEU-R12`** (non bloquante, s33) | Ancien format ; ma demande tenait pourtant en une phrase - *"cela ne coute qu'un prenom"* | **Non payee.** La reprise ne l'aggrave pas, ce que la story demandait ; elle ne la paie pas non plus |

**Ce que cette table dit, et je la tiens pour le fait le plus utile de ce sprint :**

1. **Sur les deux motifs bloquants, ceux que j'avais reecrits au format `RES-1` avec objet et mode de
   defaillance, la correction a suivi l'objet - deux sur deux.** A s33, sur ce meme terrain, deux
   corrections fideles a la lettre avaient produit deux refus bloquants (`SEC-R4`, `JEU-R9`). **Le
   renversement est complet et il est mesure sur les memes paquets, par la meme developpeuse, a un
   sprint d'intervalle.**
2. **Sur les quatre reserves non bloquantes ecrites avant `RES-1`, deux sur quatre ont porte** - et
   celles qui ont porte sont **celles dont le texte de s33 contenait deja un objet**, meme non
   etiquete (`JEU-R10` : *"que l'enfant doive savoir laquelle est la sanction"* ; `SEC-R5` : *"que
   chaque indice donne un fait sur la situation que l'enfant ne peut pas deviner"*). **Celles qui
   n'ont pas porte sont celles qui n'en portaient pas**, et l'une d'elles est arrivee chez la
   developpeuse **sous la forme d'un numero d'item sans motif** (`JEU-R11`).
3. **La correlation n'est donc pas *"reserve bloquante contre reserve non bloquante"*, c'est
   *"reserve qui porte un objet contre reserve qui n'en porte pas"*.** Quatre reserves avec objet,
   quatre payees a l'objet. Deux reserves sans objet, zero payee. **C'est une population de six, je
   ne la presente pas comme une mesure statistique** - mais elle separe exactement selon la variable
   que `RES-1` introduit, et elle ne separe pas selon le caractere bloquant.
4. **Le troisieme point de mesure est le lot neuf** : les quatre modes de defaillance nommes d'avance
   sont evites tous les quatre, sur vingt-deux items ecrits sans qu'aucun ne me revienne
   entre-temps. **C'est la partie 3 de `RES-1` qui est en cause ici, et c'est son premier contact
   avec du contenu neuf.**
5. **Et le contre-fait, que je dois ecrire parce qu'il limite les quatre precedents** : **les
   declarations d'en-tete de paquet ne sont pas des preuves.** L'en-tete de
   `cp-eps-regles-jeu-collectif` declare `JEU-R11` traitee - elle ne l'est pas ; declare que le seul
   personnage qui enfreint une regle est Hugo a l'item 10 - il enfreint aussi a l'item 11 ; et
   declare qu'aucun marqueur de restriction n'est employe - *"un seul objet"* en porte un depuis s33.
   **Les trois declarations sont fausses et les quatre jobs sont verts.** `RES-4` ne rend mordable
   que la **presence** d'un objet dans une reserve ; **elle ne rendra jamais mordable la
   correspondance entre ce qu'une correction declare et ce qu'elle fait.** Cela reste ma part, et je
   ne l'obtiens qu'en jouant.

## 5. Les reserves, au format `RES-1`

### 5.1 `EPS-CP-04`

> #### `SEC-R6` - item 3 : l'indice servi apres un echec n'ecarte aucune des deux propositions fausses
>
> 1. **Constat, date du 2026-09-11, vu a l'ecran sur deux parties.** Item 3 : *"Sur la cage à
>    grimper, avant de descendre, que doit-on vérifier ?"*, propositions *"qu'aucun camarade n'est en
>    dessous"* (creditee), *"que la maîtresse regarde"*, *"qu'on n'est pas trop haut"*. Indice servi
>    apres le premier echec : *"Une chute peut arriver vite, sans qu'on ait le temps de prévenir."*
>    **J'ai joue l'echec sur les deux distracteurs : le meme indice arrive, et il est compatible avec
>    les deux.** Une chute rapide est un argument pour verifier qu'on n'est pas trop haut ; *prevenir*
>    se lit aussi comme *prevenir la maitresse*.
> 2. **Objet.** *L'enfant qui vient de se tromper sur cet item doit pouvoir ecarter la proposition
>    qu'il a choisie, en lisant l'indice, et repartir en sachant ce qu'on verifie avant de descendre :
>    ce qu'il y a en dessous de soi.*
> 3. **Mode de defaillance, nomme d'avance.** Ajouter le mot *camarade* a l'indice et le laisser
>    parler de la vitesse d'une chute. L'indice nommerait alors la bonne reponse sans jamais dire **ce
>    qu'on verifie**, et la seconde proposition fausse resterait entiere. **Ce que l'indice doit
>    apporter est un fait sur la situation** - ce qu'on ne voit pas d'en haut, ou ce qu'on ne peut
>    plus arreter une fois lance - **pas une reformulation de la reponse**.
> 4. **Perimetre, mesure sur l'etat d'arrivee.** **Un item, un indice.** Conditions que l'etat
>    d'arrivee doit encore satisfaire : `EPS-8`, `PLA-1`, `FER-2` a onze items, `FER-3`, `COH-1`, et
>    la mention de couverture partielle. **Ce que je n'ai pas compte** : je ne demande aucun
>    changement aux propositions de l'item 3 - elles tiennent `SEC-R4` et je n'y touche pas.
> 5. **Regime et prix.** **Non bloquante.** La ligne ferme avec elle. **Rejeu, 0,5**, solde par
>    paquet (`PAR-10`). **La cause est chez moi** : mon perimetre de `SEC-R5` a omis ce texte
>    (section 2.3).

### 5.2 `EPS-CP-08` - deux reserves reconduites, aucune neuve

**Je n'ouvre aucune reserve neuve sur ce paquet.** Deux restent ouvertes, **dans leur redaction de
s33, sans un mot de plus** - je ne les reecris pas au format `RES-1` pour ne pas donner l'impression
de les durcir apres coup. **Leur objet, en revanche, je le redis ici, puisque c'est precisement son
absence qui les a fait echouer** (section 4).

> **`JEU-R11` - reconduite, non levee.** Indice de l'item 6, servi aujourd'hui : *"Porter un seul
> objet permet d'aller plus vite qu'en portant deux à la fois."*
>
> **Objet, ecrit aujourd'hui parce qu'il manquait** : *l'enfant doit repartir en sachant que porter un
> objet a la fois est la regle du jeu, pas une facon d'aller plus vite.* Un enfant de six ans qui a
> joue aux demenageurs sait qu'on va plus vite en prenant deux objets ; **l'indice lui dit le
> contraire de ce qu'il a vecu, et il le dit au moment ou il vient d'echouer.**
>
> **Ce que la reprise a change** : la formulation de s33 (*"pour aller plus vite entre les deux
> zones"*) est devenue *"plus vite qu'en portant deux à la fois"*, **ce qui rend le motif refuse plus
> explicite au lieu de le retirer**. Ce n'est pas un reproche a `sofia-sm` : la story ne lui demandait
> pas cela, et ce qu'elle lui demandait sur cet item n'avait pas de sens (section 2.4).
>
> **Non bloquante, et pour la meme raison qu'a s33, verifiee de nouveau** : l'item 10 dit desormais
> clairement que c'est une regle (*"La règle des déménageurs impose de transporter un objet à chaque
> passage"*), ce qui borne le degat.

> **`JEU-R12` - reconduite, non levee.** Apres la reprise, les deux seuls personnages qui enfreignent
> une regle sont Hugo, a l'item 10 et a l'item 11. **Aucune fille n'enfreint rien.**
>
> **Correction a l'en-tete du paquet** : il declare *"le seul personnage qui enfreint encore une regle
> du paquet est Hugo (item 10)"*. **Il enfreint aussi a l'item 11**, inchange depuis s32. La
> conclusion de l'en-tete tient - la reprise n'aggrave pas - **l'enumeration est fausse**.
>
> **Ce que je maintiens, mot pour mot depuis s33** : deux occurrences sur onze items et quatre
> personnages **ne font pas une mesure**, **je ne pose aucune barre chiffree**, et ce que j'attends
> tient en un prenom. **Non bloquante. Je ne la transforme pas en story et je ne la retire pas non
> plus** : une reserve retiree parce qu'elle est petite est une reserve qu'on ne paie jamais.

### 5.3 `EPS-CP-10` et `EPS-CP-11` - sept reserves neuves, toutes non bloquantes

**Format `RES-1` pour les sept. Le regime est le meme pour toutes - non bloquante, rejeu 0,5 pour le
paquet entier, solde a s35.**

> #### `EFF-R1` - items 1 et 14 : deux propositions portent un mot qu'un enfant de CP ne dechiffre pas
>
> - **Constat.** Item 1, distracteur *"somnolent"* ; item 14, distracteur *"d'un repas très
>   copieux"*. Aucun des deux mots n'est du vocabulaire du CP. **Le paquet dispose pourtant de la
>   forme accessible** : l'item 4 dit *"on a sommeil"*.
> - **Objet.** *Les trois propositions d'un item doivent etre trois reponses que l'enfant peut lire et
>   envisager.* Une proposition qu'il ne dechiffre pas n'est pas un mauvais candidat, **c'est un
>   bouton qu'il elimine sans le comprendre**, et l'item se joue alors entre deux propositions.
> - **Mode de defaillance.** Remplacer les deux mots par deux autres mots savants pris dans le meme
>   registre. **Le critere n'est pas la longueur du mot, c'est qu'un enfant de six ans puisse le lire
>   et savoir ce qu'il designe.**
> - **Perimetre.** Deux propositions, deux items. `PLA-1`, `FAB-1a`, `FAB-1b` a retenir a l'arrivee.
>
> #### `EFF-R2` - item 2 : un distracteur fait dire au coeur qu'il s'arrete
>
> - **Constat.** Item 2 : *"Pendant un effort physique, que fait le cœur ?"*, distracteur *"il
>   s'arrête un instant"*.
> - **Objet.** *Sur un lot qui parle du corps de l'enfant, aucune proposition ne doit lui presenter
>   comme envisageable une chose qui lui ferait peur si elle etait vraie.* Un enfant de six ans ne
>   mesure pas l'invraisemblance de cette proposition ; il en retient l'idee.
> - **Mode de defaillance.** La remplacer par une autre anomalie du coeur. **La bonne famille de
>   distracteur ici est un rythme faux, pas un arret** : *"il bat plus lentement"*, le second
>   distracteur de l'item, est exactement ce qu'il faut, et il suffirait d'un second du meme genre.
> - **Perimetre.** Un distracteur, un item. `PLA-1` a retenir.
>
> #### `EFF-R3` - item 6 : la rougeur du visage est donnee comme l'effet de l'effort, et tous les enfants ne l'observent pas
>
> - **Constat.** Item 6 : *"Pendant un effort physique intense, que devient souvent le visage ?"* ->
>   *"il devient rouge"*.
> - **Objet.** *Chaque effet de l'effort enseigne par ce paquet doit etre un effet que l'enfant qui
>   joue peut reconnaitre sur lui-meme.* **La rougeur du visage est visible sur les peaux claires et
>   pas sur les peaux foncees** ; le fait physiologique sous-jacent, lui, est universel et l'indice le
>   dit deja (*"Le sang afflue davantage vers la peau"*). **L'item enseigne donc comme universel un
>   signe qui ne l'est pas.**
> - **Mode de defaillance.** Ajouter une precaution a l'enonce (*"chez certains"*) : cela alourdirait
>   la lecture sans rien rendre a l'enfant dont le visage ne rougit pas. **Un effet que tous peuvent
>   sentir - la peau qui transpire - est du meme registre et se substitue sans rien couter.**
> - **Perimetre.** Un item sur vingt-deux. **A l'arrivee** : `FER-1` reste tenue quoi qu'il arrive -
>   les quatre effets enumeres par la ligne sont aux items 1 a 4, pas a l'item 6 - et `FER-2` reste a
>   onze items par ligne seulement si l'item est remplace et non retire.
> - **C'est celle des sept que je paierais en premier.**
>
> #### `EFF-R4` - item 17 : il travaille `EPS-CP-10` et il est compte sur `EPS-CP-11`
>
> - **Constat.** Item 17 : *"Le lendemain d'un effort physique intense, comment se sentent souvent les
>   muscles ?"* -> *"un peu fatigués"*, attribue a `EPS-CP-11`. **C'est une sensation du corps apres
>   l'effort, ce qui est la definition meme de `EPS-CP-10`** (*reconnaitre les effets de l'effort sur
>   son corps*), et non une regle d'echauffement, d'hydratation ou de recuperation. **Le paquet se
>   donne lui-meme le critere** : ses taches separent *reconnaitre un effet* de *reconnaitre une
>   regle* ; l'item 17 est dans la seconde et fait la premiere.
> - **Objet.** *Chaque item doit travailler la ligne sur laquelle il est compte* - c'est `ADR-0081`,
>   et c'est aussi la seule chose qui empeche une ligne de se fermer avec les items d'une autre.
> - **Mode de defaillance.** Deplacer l'etiquette sans regarder le compte : **`EPS-CP-11` tomberait a
>   dix items, ce qui tient encore le plancher de `DEC-0064`, et `EPS-CP-10` monterait a douze.** Les
>   deux comptes restent valides ; c'est la seule chose a verifier. L'autre sortie est de reecrire
>   l'item en regle de recuperation, ce qui est un item neuf.
> - **Perimetre.** Un item, son etiquette ou son enonce. `FER-2` et `FER-1` a recompter a l'arrivee.
>
> #### `EFF-R5` - items 1 et 3 : un distracteur vrai a un autre moment, et l'indice ne dit jamais quand il est vrai
>
> - **Constat.** Item 3 : *"Pendant un effort physique, que ressent-on souvent ?"* -> *"on a chaud"*,
>   distracteur *"on a froid"* ; indice *"Le corps produit de la chaleur quand les muscles
>   travaillent."* Item 1, meme forme avec *"affamé"*. **L'enonce fixe le moment, et mon refus
>   d'avance numero 8 est donc tenu** : c'est parce que le moment est fixe que le distracteur est
>   legitimement faux.
> - **Objet, qui va un cran plus loin que le refus 8 et que j'ecris comme neuf et date du
>   2026-09-11.** *L'enfant qui a choisi une proposition vraie a un autre moment doit repartir en
>   sachant quand elle est vraie, pas seulement qu'elle est fausse ici.* **On a bien froid apres un
>   effort, quand la sueur s'evapore** ; l'enfant qui l'a vecu et qui lit seulement *"ce n'est pas
>   ça"* apprend a se mefier de ce qu'il a observe.
> - **Mode de defaillance.** Retirer *"on a froid"* du jeu pour eviter la difficulte. **Ce serait
>   perdre l'item le plus instructif du lot** : ces deux propositions vraies a deux moments sont
>   exactement ce que la ligne enseigne.
> - **Perimetre.** Deux indices, deux items. **Ce que je n'ai pas compte** : je ne demande rien aux
>   vingt autres indices, qui tiennent `EPS-8`.
>
> #### `EFF-R6` - item 22 : un distracteur factuellement vrai est marque faux
>
> - **Constat.** Item 22 : *"Que risque-t-on si l'on ne boit pas assez d'eau pendant un effort qui
>   dure ?"*, distracteur *"de transpirer moins vite"*. **Un corps qui manque d'eau transpire
>   effectivement moins** : c'est un fait de physiologie, et c'est meme un des mecanismes par
>   lesquels la deshydratation devient dangereuse. **Marque faux, il enseigne le contraire par
>   elimination.**
> - **Objet.** *Aucune proposition vraie n'est marquee fausse* - `EMC-5`, `EMC-11`, et c'est le motif
>   qui a refuse `EMC-CP-07` trois fois. **La difference avec `EMC-CP-07`, que je dois dire pour qu'on
>   ne me reproche pas deux poids deux mesures** : la, l'enfant etait penalise pour une connaissance
>   juste qu'il pouvait avoir ; ici, aucun enfant de six ans ne connait ce fait, donc personne n'est
>   penalise - **ce qui reste est qu'un enfant apprend par elimination une chose fausse**, et c'est
>   plus petit, mais c'est de la meme famille.
> - **Mode de defaillance.** Remplacer par un distracteur invraisemblable pour etre sur qu'il soit
>   faux. **Le distracteur doit rester un risque plausible de l'effort** - le second, *"d'avoir moins
>   chaud"*, est faux et plausible, c'est le bon calibre.
> - **Perimetre.** Un distracteur, un item.
>
> #### `EFF-R7` - la consigne annonce deux taches, le paquet en sert quatre
>
> - **Constat.** Consigne du catalogue : *"Lis bien, puis dis ce que l'effort fait au corps, ou quelle
>   règle l'entoure."* **Le paquet declare quatre taches** et **dix de ses vingt-deux items demandent
>   *"pourquoi"* ou *"que risque-t-on"***, ce qu'aucune des deux clauses n'annonce.
> - **Objet.** *L'enfant doit savoir, en lisant la consigne une fois au debut de la partie, quel genre
>   de reponse on attend de lui sur chacune des questions qu'il va rencontrer.*
> - **Mode de defaillance.** Ajouter deux clauses de plus. **Une consigne de quatre clauses au CP est
>   pire que le defaut qu'elle repare** - c'est la raison meme pour laquelle j'ai decoupe
>   `histoire-geographie` plutot que d'allonger sa consigne. La sortie n'est donc pas mecanique, et
>   **je ne la choisis pas ici** : elle appartient a la meme question de grain que `CONS-4`.
> - **Perimetre.** Une consigne d'entree de catalogue, zero item. **Non bloquante** : chaque enonce
>   commence par son propre mot interrogatif, et l'enfant n'est jamais egare devant la question
>   elle-meme.
> - **Ce que cette reserve corrige chez moi** : ma confirmation de grain du 2026-09-10 supposait deux
>   taches (section 2.6).

### 5.4 Points que je ne transforme pas en reserve, et je le dis pour qu'on ne me les redemande pas

- **Item 10 de `jeu-collectif` : le distracteur *"Hugo doit recommencer au départ"* est presque mot
  pour mot la bonne reponse de l'item 11, *"Hugo doit retourner au départ"*, meme prenom, meme
  tache.** J'ai regarde s'il fallait une reserve : **non.** Les deux enonces nomment leur jeu des le
  premier mot, et **savoir que la sanction depend du jeu est exactement ce que `JEU-R10`
  demandait**. Ecarte volontairement.
- **Les items 12 et 15, 18 et 21, 19 et 22 du lot du corps disent la meme chose dans deux
  directions.** La ligne `EPS-CP-11` porte onze items pour **trois objets** - echauffement,
  hydratation, recuperation. **C'est exactement ce que son libelle enumere** (`FER-1`), et au CP
  rencontrer trois fois la meme regle sous trois angles est un moyen d'apprentissage, pas un
  remplissage. **Ecarte.**
- **Le groupe de mots *"un effort physique intense"* revient dans la moitie des enonces du lot du
  corps, et ni *physique* ni *intense* n'est du vocabulaire de CP.** J'ai hesite, et je tranche contre
  la reserve : **les mots qui portent la discrimination sont *pendant*, *juste apres*, *le lendemain*
  et *avant*, qui sont tous lisibles au CP.** Les deux mots savants sont dans le decor de la question,
  jamais dans ce qui la fait gagner. **Je note la densite, je n'en fais pas une reserve, et je ne
  demanderai pas une reecriture pour tenir un compte de mots** - j'ai deja vu ce que coute une
  reecriture faite pour un compte.
- **Les indices du lot du corps sont longs** (jusqu'a douze mots, avec *articulations* et
  *progressivement* a l'item 21). **`LON-1` et `LON-2` portent sur les enonces**, et je n'ai aucune
  barre sur la longueur des indices. **Je note la question, je ne cree pas une barre en cours de
  sprint.** Troisieme sprint ou je l'ecris.
- **L'orthographe de *coeur*, sans ligature, dans quatre items et deux indices du lot du corps.** Ce
  n'est pas mon registre : l'orthographe et la typographie du texte lu par l'enfant appartiennent au
  `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`). **Je le route, je ne le
  corrige pas.**

## 6. Mon refus d'avance numero 6 s'est realise, et ce n'est pas sur mon terrain

**Fait mesure en jouant, sur les trois paquets, et identique sur un paquet ferme avant ce sprint :**

| Partie jouee | Ce que l'ecran de fin affiche |
|---|---|
| Dix manches, toutes trouvees du premier coup | *"Score : 10"* |
| Dix manches, une seule trouvee au second essai | *"Score : 9"* |
| **Dix manches, toutes trouvees au second essai** | **_"Score : 0"_, et rien d'autre que _"C'est fini !"_** |

**Mon refus 6, ecrit le 2026-09-10 :** *"Je refuse d'avance un ecran de fin qui n'afficherait que le
nombre reduit. Un enfant qui a fait les dix questions doit lire qu'il les a faites, a cote de ce
qu'il a trouve seul."* **Il est realise a la premiere partie jouee apres le remede, exactement a
l'endroit ou j'avais ecrit qu'il casserait.** Le remede de `SCO-1` fait ce que j'ai demande - le
score ne credite plus une manche trouvee apres une erreur - **et le corollaire que j'avais nomme dans
le meme geste n'a pas ete ecrit.**

**Ce que je fais de ce constat, et ce que je ne fais pas :**

- **Je ne le porte au debit d'aucune de mes quatre lignes.** L'ecran est le meme sur les soixante-six
  entrees du catalogue, y compris celles qui sont `couvert-valide` depuis des sprints. **Facturer un
  defaut transverse a l'increment de contenu qui passe devant moi ce jour-la serait une erreur
  d'attribution**, et cela reviendrait a refuser tout le produit sans le dire.
- **Je ne prononce pas de verdict sur `S34-SCO1-REMEDE`** : la story est a l'instance A (planning,
  section 5), et `PAR-2` me reserve a une seule matiere. **Le fait est ici, date et mesure ; le
  verdict appartient a l'instance qui tient la story.**
- **Je maintiens le refus entier.** Il n'est pas leve par le fait que le remede soit par ailleurs
  juste, et **il ne se perime pas** : tant que l'ecran n'affiche que le nombre reduit, un enfant qui a
  fait les dix questions lit qu'il n'a rien fait.

## 7. Ce dont je reste la seule garde sur ce terrain - mise a jour du 2026-09-11

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Garde executable ? |
|---|---|
| **L'exactitude de chaque regle de securite, de chaque regle de jeu et de chaque fait sur le corps a l'effort** | **aucun.** **Quarante-quatre faits verifies a la main ce jour**, et les quatre jobs sont verts sur l'arbre qui les porte |
| **`COH-1`** - un paquet ne se contredit pas avec lui-meme | **aucun.** **Premiere epreuve sur du contenu reel : tenue sur les trois paquets**, verifiee jeu par jeu et effet par effet |
| **La frontiere corps-et-effort** (`DEC-0083`, 9.2) | **aucun**, et elle vient d'etre tenue pour la premiere fois. **Elle redeviendra dangereuse au premier lot de sciences CM1 ou de questionner le monde CP sur la sante**, qui sera ecrit par une autre instance que celle-ci |
| **`EPS-8`** - un indice apporte un fait | **aucun.** **Premier lot ou elle est opposable : tenue sur quarante-deux indices sur quarante-quatre**, et les deux qui manquent sont `SEC-R6` et `JEU-R11` |
| **`PLA-1`** - chaque proposition est un candidat serieux | **aucun**, et ce sprint en donne deux formes que rien ne distingue : le mot illisible (`EFF-R1`) et la proposition factuellement vraie (`EFF-R6`) |
| **Ce qu'une proposition enseigne par elimination** | **aucun**, et **c'est neuf a partir d'aujourd'hui** : `EFF-R6` est le premier cas ou le defaut n'est pas dans ce que l'item credite mais dans ce qu'il ecarte |
| **L'universalite d'un fait sur le corps** (`EFF-R3`) | **aucun**, et **c'est neuf aussi**. Aucune de mes conditions ne disait qu'un effet enseigne doit etre observable par tout enfant. **Je ne pose pas de condition nouvelle en cours de sprint** ; la reserve suffit pour ce lot |
| **`FER-1`, `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`, `FAB-1c`** | **aucun** : **aucun test de paquet n'existe pour l'EPS**, et il y en a maintenant trois. **Comptes a la main pour la troisieme fois ; route a `theo-archi` a s32, a s33, et de nouveau ici** |
| **Le texte de l'ecran de fin de partie** | **aucun**, et **mon refus 6 s'est realise dessus** (section 6) |
| **La correspondance entre ce qu'un en-tete de paquet declare et ce que le paquet fait** | **aucun, et je ne demande pas qu'on en cree un** : trois declarations fausses mesurees ce jour sur un seul en-tete (section 4, point 5) |
| **`LON-1`, `LON-2`** | **`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest`, depuis `ADR-0089`.** Troisieme sprint ou je ne recompte pas |

## 8. Ce que je route

| Suite | Destinataire | Ce que j'attends |
|---|---|---|
| **Les quatre lignes passent a `couvert-valide`** | `camille-po` | **Done pedagogique prononce sur `EPS-CP-04`, `EPS-CP-08`, `EPS-CP-10`, `EPS-CP-11`.** **Je ne touche pas la matrice.** Chaque ligne porte sa mention de couverture partielle (section 3.4), et **les deux mentions des lignes 10 et 11 sont ecrites aujourd'hui, elles n'existaient pas** |
| **`SEC-R6`** | `camille-po`, via `sofia-sm` | Un indice. **Rejeu 0,5 avec le paquet.** La cause est chez moi (section 2.3) |
| **`JEU-R11`, `JEU-R12`** | `camille-po`, via `sofia-sm` | Reconduites, **non levees**. `JEU-R11` n'a jamais eu d'objet ecrit : il est en section 5.2, et **c'est cette absence qui l'a fait echouer**, pas la developpeuse |
| **`EFF-R1` a `EFF-R7`** | `camille-po`, via `sofia-sm` | **Rejeu 0,5 pour le paquet entier**, solde a s35. `PAR-10` demandait le meme sprint ; la fenetre est fermee (section 3.3) |
| **Le critere de story sur l'indice de l'item 6 de `jeu-collectif`** | `camille-po` | Il demande une coherence avec l'epervier pour un item qui porte sur les demenageurs. **A reecrire depuis l'objet de `JEU-R11`, pas depuis mon numero d'item** |
| **Trois declarations fausses dans l'en-tete de `cp-eps-regles-jeu-collectif`** | `relecteur-pedagogique`, via `sofia-sm` | `JEU-R11` declaree traitee ; *"le seul personnage qui enfreint est Hugo (item 10)"* ; *"aucun marqueur de restriction n'est employe"*. **La troisieme est routee depuis s33 et non payee** |
| **L'orthographe de *coeur*** dans `cp-eps-corps-effort` | `relecteur-pedagogique` | Texte lu par l'enfant, registre orthographique : **ce n'est pas ma porte** (`DEC-0002`, `DEC-0056`) |
| **Un test de paquet pour les trois paquets d'EPS** | `theo-archi`, via `sofia-sm` | `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b` sont mordables. **Route a s32 et s33, non paye ; trois paquets maintenant, comptes a la main a chaque passage** |
| **`EPS-7` retiree, `EPS-8` la remplace** | toute l'equipe | Section 2.2. **Aucune barre n'est relevee par ce retrait** |
| **Mon refus d'avance numero 6, realise** | **l'instance A** (`S34-SCO1-REMEDE`), `sofia-sm`, `camille-po` | Section 6. **Le verdict sur cette story n'est pas le mien** |
| **La note du 2026-09-11 a `RES-1`** (un perimetre nomme, pour chaque item cite, la reserve dont il vient) | moi-meme, arbitrage prealable de s35 | Section 2.4. **Je ne retouche pas mon document de s34** |

## 9. Ce que j'ai alloue au registre des identifiants

**Huit identifiants, dont un prefixe neuf** : `SEC-R6` ; `EFF-R1` a `EFF-R7`, prefixe `EFF` (le lot de
l'effort et du corps). **`COR` n'etait pas libre** - il designe `corps-sens` CP - et je ne l'ai pas
reutilise.

**Recompte deux fois, sur le fichier que j'avais sous les yeux, et l'ecart que je ne repare pas a
l'aveugle.** Methode dans les deux cas : comptage des lignes de la table de la section 4 a la forme
`| identifiant | document |`, identifiants distincts et prefixes distincts.

| Moment | Ce que le fichier portait | Ce que sa ligne d'etat disait | Ecart |
|---|---|---|---|
| A l'ouverture de mon audit | **611 / 100** | **611 / 100** | **aucun.** Aucune modification non commitee sur ce fichier a cet instant |
| Au moment d'ecrire mes allocations | **632 / 102** | **632 / 102** | **aucun.** Une autre instance a alloue entre mes deux comptages, et elle a tenu sa ligne |

**Je porte la ligne a 640 / 103**, avec mes huit allocations et mon prefixe neuf.

**Ce que je ne fais pas** : je ne repare aucun ecart a l'aveugle, et je n'en ai trouve aucun. **Si une
troisieme instance alloue apres moi sans recompter**, la ligne d'etat sera fausse a la fusion, **la CI
rougira comme `ADR-0085` clause 5 le prevoit, et la reparation sera arithmetique** - aucune allocation
a ajouter, aucune a retirer.

## 10. Ce que je n'ai pas fait

- **Je n'ai pas touche `squad/programme/MATRICE-couverture.md`.** Les quatre mouvements de statut sont
  a `camille-po`.
- **Je n'ai pas commite et je n'ai pas pousse.**
- **Je n'ai audite aucune story hors de mon perimetre**, et je n'ai prononce aucun verdict sur le
  palier 2, sur les terrains A et B, ni sur `S34-SCO1-REMEDE`.
- **Je n'ai corrige aucun fichier de contenu, aucun test, aucune documentation technique.**
- **Je n'ai rien efface** dans les deux fiches de conformite existantes : les deux refus de s32 et de
  s33 y restent verbatim, et le verdict de ce jour s'ajoute dessous.
