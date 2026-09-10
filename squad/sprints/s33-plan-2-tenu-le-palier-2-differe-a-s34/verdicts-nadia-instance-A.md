# [Sprint s33] Verdicts pedagogiques - `nadia-prof`, **instance A**

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-10.
- **Perimetre de l'instance** : histoire-geographie CM1, anglais CM1, portee D (`PAR-12`).
- **Stories auditees** : `S33-HG-02`, `S33-HG-04`, `S33-HG-08-12`.
- **Arbre** : `round-two` a `b45e5927`, huit stories payees sur onze, arbre propre, rien de pousse.
  Porte technique reprise du planning, sections 13 et 14 : quatre jobs verts, 1443 tests. **Je ne la
  refais pas et je ne la deduis pas** ; je nomme d'ou je la tiens.
- **Ce document prononce des verdicts.** Il ne bouge aucune ligne de
  `squad/programme/MATRICE-couverture.md` - le mouvement de la matrice appartient a la review et a
  `camille-po`.

---

## 0. Ce que j'ai joue, et ce que je n'ai pas pu voir

**J'ai joue.** Jar de l'arbre fusionne, lance sur un port local. **Vingt-huit parties completes de
dix manches, 280 manches servies**, chaque proposition fausse pressee avant la bonne, et un chemin de
revelation joue a part en pressant trois fois la meme proposition fausse.

**Verifie avant de jouer plutot que suppose** : le paquet servi par ce jar est bien celui de l'arbre
fusionne - 110 items, et l'item 85 y porte *"D'ou venait cette armee ?"* / *"d'Espagne"*, c'est-a-dire
l'etat d'apres la fusion de la section 14 du planning et non l'etat d'avant.

| Ce que j'ai verifie en jouant | Ou |
|---|---|
| Les deux items corriges de `HG-CM1-02` et les cinq indices de sa tache d'ordre | manches servies, apres erreur |
| L'item 85 corrige de `HG-CM1-04` et l'indice de l'item 87 | manches servies, apres erreur |
| Les neuf items neufs de `HG-CM1-08` et les neuf de `HG-CM1-12` | manches servies, propositions et indices |
| La consigne servie, relevee a chaque manche | 280 manches |
| Le score, apres erreurs volontaires | 28 parties |
| L'ecran d'echec, l'ecran de revelation, l'ecran de fin de partie | chemins joues |

**Audit degrade sur un point, et je le declare, comme a s32** : j'ai joue par requetes HTTP reelles,
donc j'ai lu le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre
des ecrans, l'enchainement apres une erreur et apres trois, le score et les items servis. Cela **ne**
couvre ni la couleur, ni le contraste, ni la taille, ni la position a l'ecran. **Aucun verdict de ce
document ne repose sur un jugement visuel.**

**Ce que j'ai lu sans le jouer** : les 110 items du fichier servi, un par un, pour les quatre lignes
de ce perimetre et pour la ligne voisine `HG-CM1-11` que `HIS-5` m'oblige a confronter. C'est ecrit la
ou je m'en sers.

**Un fait de conduite, dit pour qu'il ne se relise pas comme un incident** : un `mvn clean verify`
lance en parallele par une autre instance a efface puis reconstruit le jar en cours de session, ce qui
a arrete mon application. **Les 280 manches etaient jouees avant**, sur le jar dont j'ai verifie le
contenu ci-dessus. Rien n'est rejoue apres coup et rien n'est deduit.

---

## 1. Corrections a mes propres constats, avant de m'en servir

**Deux des quatre reserves que ce sprint paie portaient un texte que j'avais dicte moi-meme**, mot
pour mot, dans mes verdicts de s32. `marc-dev` a applique exactement ce que j'avais ecrit. **Ce que
ces textes produisent une fois en place est donc a moi, pas a lui**, et je le dis avant de rendre les
verdicts pour que personne ne compte ces points contre le lot.

### 1.1 Le texte que j'ai dicte pour `HG-R11` produit une proposition qui ne repond pas a la question

`HG-R11` demandait, mot pour mot : *"reprendre, aux items 70 et 71, la formulation que l'item 66 du
meme fichier porte deja - `Clovis devient roi des Francs, en 481`"*. C'est fait, a la lettre.

**Et cette formulation est une proposition verbale placee dans une liste de groupes nominaux.**
L'enonce demande *"Parmi ces trois faits de l'histoire de France, lequel a eu lieu le plus tot ?"* et
les deux autres propositions sont *"le sacre de Charlemagne, empereur, en 800"* et *"la prise de la
Bastille, en 1789"*. Recollee a sa question, la troisieme ne se lit pas : *"lequel a eu lieu le plus
tot ? - Clovis devient roi des Francs, en 481"*.

**Le defaut est de moi.** Je l'ouvre en reserve neuve et datee, `HG-R13`, **non bloquante**, et je ne
le compte pas contre le lot ni contre la ligne. **Je ne durcis pas `HG-R11` : je la leve.**

### 1.2 Le texte que j'ai dicte pour `HG-R10` produit un indice qui n'ajoute rien

`HG-R10` demandait, regime *mot pour mot*, que l'indice de l'item 87 reprenne *"la formulation juste
[...] de l'item 66 du meme fichier"*. C'est fait. L'indice servi est desormais *"Clovis devient roi
des Francs, en 481."*, et il est vrai.

**Il n'aide pas beaucoup.** L'item 87 demande *"Les Francs s'installent en Gaule a la fin du Ve siecle
et fondent un royaume qui deviendra un grand pays d'Europe. Lequel ?"*, contre *"l'Espagne"* et
*"l'Italie"* : l'indice repete *Francs*, que l'enonce donne deja deux fois, et ajoute une date dont le
choix n'a pas besoin. **Il oriente encore - le chemin *Francs* vers *France* est celui que l'enfant
doit prendre - donc il n'est pas inoperant, et je n'ouvre pas de reserve.** Je l'ecris pour que
personne ne le releve plus tard comme un defaut manque, et parce que le geste etait le mien.

### 1.3 Ce que je ne corrige pas, parce que je l'avais deja tranche

Les cinq items *"En quel siecle / millenaire ?"* de `HG-CM1-02` portent un indice qui nomme la bonne
reponse (*"481 se situe entre 401 et 500, dans le Ve siecle."*). **J'ai regarde exactement ces
indices a s32 et j'ai ecrit qu'ils ne sont pas un defaut** - l'indice explique l'intervalle, donc
l'erreur enseigne la regle du siecle. **Je m'y tiens.** Je le redis ici pour qu'un tiers ne le relise
pas comme un oubli d'aujourd'hui.

---

## 2. `HG-CM1-02` - **VALIDE SOUS RESERVE**. La ligne ferme

**Verdict : Done pedagogique prononce. `HG-CM1-02` passe `couvert-valide`.** Story `S33-HG-02`.

### 2.1 `HG-R11`, bloquante - **levee**

Texte servi, vu a l'ecran :

| Ou | Avant (s32) | Aujourd'hui |
|---|---|---|
| Item 70, bonne reponse | *"le sacre de Clovis, roi des Francs, en 481"* | *"Clovis devient roi des Francs, en 481"* |
| Item 71, distracteur | *"le sacre de Clovis, roi des Francs, en 481"* | *"Clovis devient roi des Francs, en 481"* |

**Le fait faux a disparu des deux endroits, et il n'en reste aucun troisieme.** Verifie sur le fichier
entier : plus aucune occurrence de *sacre de Clovis*. Le seul *sacre* qui subsiste dans le paquet est
celui de Charlemagne en 800 et celui de Napoleon en 1804, tous deux justes. **`HG-R11` est levee.**

### 2.2 `HG-R9`, non bloquante - **levee**

Ma barre disait : *"sur les items d'ordre de `HG-CM1-02`, l'indice porte la methode [...] et jamais la
date de la bonne reponse quand chaque proposition affiche la sienne."*

Les cinq indices de la tache d'ordre, lus a l'ecran apres erreur :

| Ancien indice | Indice servi aujourd'hui |
|---|---|
| *"Repense a la date la plus ancienne des trois : 481."* | *"Compare les trois dates : cherche celle qui est arrivee en premier."* |
| *"Repense a la date la plus recente des trois : 1789."* | *"Compare les trois dates : cherche celle qui est arrivee en dernier."* |
| idem avec 800, 1804, 1598 | meme forme, sans aucune date |

**Aucun des cinq ne porte plus de date. Chacun nomme l'operation a faire.** La barre est atteinte,
**`HG-R9` est levee** - et je ne recharge pas la barre ailleurs alors que je pourrais : les cinq
indices sont desormais identiques deux a deux, ce que je n'avais pas interdit et ce dont je ne veux
pas plus.

### 2.3 `FER-1`, `FER-2`, `FER-3` - reverifiees, non rediscutees

`FER-2` : 10 items. `FER-3` : cinq items *situer dans un siecle ou un millenaire*, cinq items
*ordonner*, minorite a 5, au-dessus du plancher de 3. `FER-1` : les deux elements du libelle sont
servis en bonne reponse. **Elles etaient tenues a s32, je les reverifie et je ne les rediscute pas.**

### 2.4 Exactitude - les dix items

Les dix faits dates de la ligne sont justes : 481, 800, 1429, 1598, 1643, 1789, 1804, et la
convention de siecle appliquee a 800 comme a 1789. **Aucune inexactitude sur cette ligne.**

### 2.5 `HG-R13` - **reserve neuve, datee du 2026-09-10, non bloquante**

**La faute est la mienne** (section 1.1). Trois propositions de la tache d'ordre sont des phrases a
verbe conjugue placees parmi des groupes nominaux, et ne se lisent pas recollees a leur question :

| Ou | Proposition |
|---|---|
| Item 70, bonne reponse | *"Clovis devient roi des Francs, en 481"* |
| Item 71, distracteur | *"Clovis devient roi des Francs, en 481"* |
| Item 73, distracteur | *"Louis XIV devient roi, en 1643"* - **anterieure a s33**, elle portait deja la meme forme et je ne l'avais pas relevee |

**Pourquoi ce n'est pas bloquant** : aucune bonne reponse n'est fausse, la tache reste faisable, et
mon precedent sur cette famille est ecrit - `PER-R1` et `PER-R2` sont des reserves non bloquantes sur
des lignes que j'ai fermees. **Je m'y tiens plutot que de durcir parce que le texte est de moi.**

> **`HG-R13`. Propriete a tenir** : sur les cinq items de la tache d'ordre de `HG-CM1-02`, chaque
> proposition est un groupe nominal qui se lit comme reponse a *"lequel a eu lieu le plus tot / le
> plus tard ?"*. **Je ne fournis pas les textes cette fois** - c'est en dictant le texte que j'ai
> fabrique le defaut. **Regime : rejeu borne, 0,25** ; je rejoue la seule tache d'ordre.

---

## 3. `HG-CM1-04` - **VALIDE SOUS RESERVE**. La ligne ferme

**Verdict : Done pedagogique prononce. `HG-CM1-04` passe `couvert-valide`.** Story `S33-HG-04`.

### 3.1 `HG-R12`, bloquante - **levee**

Ma reserve nommait **deux directions et disait qu'il n'y avait pas a m'en redemander une
troisieme** : *"soit l'item nomme l'armee par son origine plutot que par un nom de peuple, soit
l'item cesse de porter sur l'identite de l'armee"*. Le lot a pris la premiere.

Texte servi, vu a l'ecran :

> *"En 732, une armee est arretee pres de Poitiers par Charles Martel. **D'ou venait cette armee ?**"*
> -> **`d'Espagne`**, contre *"d'Italie"* et *"d'Angleterre"*.
> Indice : *"Cette armee avait traverse les Pyrenees avant d'arriver en Gaule."*

**La question ne demande plus un peuple, et la reponse n'en nomme plus aucun.** Verifie sur les dix
items de la ligne : aucune bonne reponse ne designe desormais un groupe humain par un exonyme
qu'aucun groupe ne revendique. **`HG-R12` est levee.**

**Et je ne demande pas le quatrieme item.** J'avais ecrit a s32 qu'un quatrieme mouvement s'enseigne -
l'installation des Bretons en Armorique - **pour montrer que la sortie existait**, pas pour l'exiger.
La premiere direction garde l'item dans sa tache et n'a besoin d'aucun ajout. **Reclamer maintenant le
quatrieme item serait deplacer mon propre poteau ; je ne le fais pas.**

**Deux points d'exactitude que je verifie et que je signe** :

- *"d'Espagne"* designe un **territoire**, pas un Etat de 732 : il n'y a pas d'Espagne en 732, et
  l'armee est celle du gouverneur omeyyade d'al-Andalus. **C'est la formulation du programme de cycle
  3, famille (a) de `HIS-3`**, et l'indice la porte geographiquement en nommant les Pyrenees plutot
  qu'un pouvoir. **Je la signe.**
- *"la peninsule Iberique"*, terme que j'avais releve a s32 comme plus difficile que la question qu'il
  aidait, **a disparu** de l'indice avec la reecriture. Je l'avais dit sans lui allouer de code ; c'est
  paye.

### 3.2 `HG-R10`, non bloquante - **levee**

L'indice de l'item 87 n'attribue plus a 481 l'unification du royaume. **Le fait faux a disparu du
troisieme et dernier endroit du fichier ou l'annee 481 portait un evenement qu'elle ne porte pas.**
Voir la section 1.2 pour ce que je ne demande pas de plus.

### 3.3 `FER-1`, `FER-2`, `FER-3` - reverifiees

`FER-2` : 10 items. `FER-3` : trois items *nommer ou identifier le mouvement* (les peuples
germaniques, les Vikings, l'origine de l'armee de 732) contre sept *dire un effet sur le territoire* ;
minorite exactement au plancher de 3. **La substitution de s33 ne fait pas sortir l'item 85 de sa
tache** : identifier un mouvement par son origine est l'une des deux directions que j'ai moi-meme
declarees recevables, et je ne peux pas la declarer recevable puis la compter hors de la tache.
`FER-1` : les deux elements du libelle sont servis.

**Ce que je nomme sans le facturer** : le partage des taches de cette ligne est epingle dans le test
par un ensemble de trois chaines ecrit a la main. **C'est le bon choix** - `theo-archi` l'a route ainsi
et il a raison : un ensemble ecrit a la main rougit quand le contenu change, une sous-chaine passe au
vert. **Mais ce test compte des chaines, pas des taches** : le jour ou un item de la tache des effets
portera l'une de ces trois reponses, le compte sera juste et faux en meme temps. **Cette propriete
reste a moi.**

### 3.4 Exactitude - les dix items

Verifies un par un : 476, la fin de l'Empire romain d'Occident ; l'installation des Francs a la fin du
Ve siecle ; les Vikings au VIIIe siecle et le siege de Paris a la fin du IXe ; la Normandie ; 732 et
l'arret de l'expansion ; la dynastie carolingienne issue de la famille de Charles Martel. **Aucune
inexactitude.** L'item *"Que devient son expansion en Europe ? - elle s'arrete"* reste la
simplification du programme, comme a s32, et je ne la reprends pas.

---

## 4. `HG-CM1-08` - **VALIDE SOUS RESERVE**. La ligne ferme

**Verdict : Done pedagogique prononce. `HG-CM1-08` passe `couvert-valide`.** Story `S33-HG-08-12`,
moitie `HG-CM1-08`. **Trois reserves neuves, toutes non bloquantes.**

### 4.1 Mon refus d'avance numero 3 est evite - la seconde moitie du libelle est servie

**J'avais ecrit** : *"Toute fermeture de `HG-CM1-08` sans item sur *ce que Napoleon Bonaparte y
change*"* est refusee, et *"neuf items de plus sur le passage de la Revolution a l'Empire est la pente
naturelle"*.

**Le lot ne l'a pas prise.** Les dix items se partagent exactement :

| Moitie du libelle | Items, par leur bonne reponse |
|---|---|
| *le passage de la Revolution a l'Empire* | l'Empire (item de s27), un coup d'Etat, premier consul, le Consulat, la Republique |
| *ce que Napoleon Bonaparte y change* | le Code civil, des lycees, la Legion d'honneur, un prefet, la Banque de France |

**Cinq contre cinq, en bonne reponse et non en decor.** `FER-1` sur les deux moities : tenue. `FER-2`
: 10 items. `FER-3` : minorite a 5, au-dessus du plancher de 3.

### 4.2 Mon refus d'avance numero 4 est evite - la charge de lecture n'a pas bouge

**J'avais ecrit** : *"Tout item ajoute a `cm1-histoire-geographie` qui porterait la mediane de charge
de lecture du paquet au-dela de ce qu'elle est aujourd'hui"* est refuse, et *"dix-huit items neufs sur
un paquet de 92 peuvent l'alourdir sans qu'aucune ligne ne rougisse"*.

**Compte par moi, sur les enonces du fichier servi, mot par mot** : mediane a **16 mots avant les
dix-huit items neufs, 16 mots apres**. Elle n'a pas bouge. **`HG-R4` est tenue.**

**Un ecart de comptage, dit a cote de son chiffre plutot que tu.** Le commentaire du paquet annonce
*"les 18 items neufs comptent chacun entre 13 et 18 mots"* et un maximum de paquet a 30 ; mon propre
comptage donne **12 a 19** pour les items neufs et un maximum de paquet a **28**. L'ecart vient du
decoupage des mots - les formes elidees et les traits d'union se comptent differemment - **pas du
contenu**. La grandeur que `HG-R4` nomme est la **mediane**, et les deux comptages lui donnent la
meme valeur inchangee. **Je n'ouvre rien.**

### 4.3 `HIS-1` - reverifiee sur le fichier entier apres 18 items neufs

Compte sur le fichier, seul fichier du lot : **23 items de restitution sur 110, soit 20,9 %**, sous le
tiers. **Tenue.** Je reprends le chiffre du lot parce que je l'ai recompte, pas parce qu'il est ecrit.

### 4.4 Exactitude - les neuf faits neufs, un par un

| Fait servi | Verdict |
|---|---|
| 1799, la prise de pouvoir de Napoleon Bonaparte est un coup d'Etat | juste |
| Il porte d'abord le titre de premier consul, avant celui d'empereur | juste |
| Le regime entre 1799 et 1804 est le Consulat | juste |
| La Revolution avait installe la Republique avant 1804 | juste |
| Le Code civil est un meme ensemble de lois pour toute la France | juste |
| Il cree des lycees pour les eleves les plus ages | juste |
| Il cree la Legion d'honneur pour honorer le merite, civil ou militaire | juste |
| Il place un prefet a la tete de chaque departement | juste |
| Il cree la Banque de France | **juste sur la creation, inexact sur la mission** - voir `HG-R16` |

### 4.5 Les distracteurs - `PLA-1` sur les dix items

**Chacun des dix items porte au moins un distracteur qui est un candidat serieux du meme genre que la
bonne reponse** : un regime contre des regimes, une institution contre des institutions, un elu contre
un nomme, une decoration contre des decorations. *"la Declaration des droits de l'homme et du
citoyen"* contre *"le Code civil"*, *"un maire"* et *"un depute"* contre *"un prefet"*, *"la Bourse de
Paris"* contre *"la Banque de France"* : ce sont de vraies confusions d'enfant de neuf ans. **`PLA-1`
est tenue sur cette ligne.**

**Deux distracteurs romains ne sont pas des candidats** - *"la Republique romaine"* et *"l'Empire
romain"*, sur des questions qui portent sur la France de 1792 a 1804. Ils rendent deux items
localement plus faciles. **Ils ne fondent pas de reserve** : chacun des deux items porte a cote un
second distracteur serieux (*"la monarchie absolue"*, *"la monarchie"*), et mon precedent est ecrit -
`ANG-R3` a traite `PLA-1` en reserve non bloquante, `SIG-R2` et `MEL-R3` avant elle. **Je le note pour
qu'il ne se relise pas comme un manque.**

### 4.6 `HG-R14` - **reserve neuve, datee du 2026-09-10, non bloquante** : un enonce ou personne ne devient empereur

**Enonce servi, vu a l'ecran :**

> *"**Avant de devenir empereur en 1804**, quel regime la Revolution avait-elle installe en France ?"*

**Le seul sujet de la phrase est *la Revolution*.** L'infinitif *devenir empereur* n'a pas d'autre
sujet a quoi se rattacher, et l'enonce dit donc, a la lettre, que **la Revolution devient empereur**.
**Napoleon Bonaparte n'est nomme nulle part dans cet item** : ni dans l'enonce, ni dans les
propositions, ni dans l'indice. Un enfant de neuf ans qui lit seul ne peut pas savoir qui devient
empereur.

**C'est exactement la classe de defaut que la retro de s32 a nommee et que la section 11 du planning
demandait d'eviter** : une phrase raccourcie ou remontee sans que le francais soit repris autour.

**Pourquoi ce n'est pas bloquant** : la bonne reponse - *"la Republique"* - est juste, la date de 1804
sert d'ancrage meme mal attachee, et mon precedent sur les referents casses est ecrit et recent
(`PER-R1`, `PER-R2`, `JEU-R5`, toutes non bloquantes). **Je m'y tiens.**

> **`HG-R14`. Propriete a tenir** : l'enonce de cet item nomme celui qui devient empereur, ou renonce
> a la subordonnee. **Je ne fournis pas le texte.** **Regime : mot pour mot, 0,25** - un seul enonce.

### 4.7 `HG-R15` - **reserve neuve, datee du 2026-09-10, non bloquante** : l'indice du Code civil ne designe aucune des trois propositions

**Item servi, et son indice apres erreur :**

> *"Napoleon Bonaparte fait rediger un meme ensemble de lois pour toute la France. Comment
> appelle-t-on ce texte ?"* -> *"le Code civil"*, contre *"la Declaration des droits de l'homme et du
> citoyen"* et *"la Constitution de 1791"*.
> Indice : *"Il porte encore aujourd'hui le nom de son createur dans plusieurs pays."*

**Aucune des trois propositions ne porte un nom de personne.** L'enfant qui s'est trompe lit un indice
qui ne discrimine rien, sauf s'il connait deja l'expression *Code Napoleon* - ce qu'un CM1 ne connait
pas, et que le paquet n'ecrit nulle part. **Un enfant bloque doit pouvoir avancer ; sur cet item il ne
peut pas.**

**Et l'indice dit autre chose que l'enonce** : l'enonce ecrit soigneusement *"fait rediger"*, l'indice
dit *"son createur"*. C'est la seule des trois propositions du meme item qui perde cette precaution.

**Pourquoi ce n'est pas bloquant** : l'indice n'est lu qu'apres une erreur, la bonne reponse est
juste, et toutes mes reserves d'indice sur ce paquet ont ete non bloquantes (`HG-R8`, `HG-R9`,
`HG-R10`).

> **`HG-R15`. Propriete a tenir** : l'indice de cet item nomme une propriete qui distingue *le Code
> civil* des deux autres propositions - ce qu'il contient, pour qui il vaut, quand il est ecrit -
> **et non une propriete que l'enfant ne peut verifier sur aucune des trois.** **Je ne fournis pas le
> texte.** **Regime : mot pour mot, 0,25.**

### 4.8 `HG-R16` - **reserve neuve, datee du 2026-09-10, non bloquante** : ce que la Banque de France est chargee de faire

**Enonce servi :**

> *"Napoleon Bonaparte cree une nouvelle banque **chargee de fabriquer la monnaie** et de stabiliser
> l'economie. Comment s'appelle-t-elle ?"*

**La Banque de France de 1800 n'est pas chargee de fabriquer la monnaie.** Elle recoit le privilege
d'**emettre des billets** a Paris en 1803, et ne l'obtient pour tout le pays que bien plus tard ; la
frappe des pieces est et reste une affaire d'Etat. L'enfant sort de cet item en croyant que la banque
fabrique l'argent.

**Pourquoi ce n'est pas un refus, alors qu'une erreur de fait est chez moi un motif de refus a elle
seule.** Ma doctrine est ecrite depuis le 2026-09-06 et je l'applique dans les deux sens : **la
reponse attendue ne depend pas de cette phrase.** L'enfant repond *"la Banque de France"* que la
mission soit decrite juste ou faux, comme sur `HG-R7` (le 14 juillet dans un texte-support) et sur
`ANG-R2`. **La condition de ma propre doctrine est remplie dans le sens de la reserve.**

> **`HG-R16`. Propriete a tenir** : l'enonce decrit une mission que la Banque de France a reellement
> recue a sa creation, ou renonce a en decrire une. **Je ne fournis pas le texte.** **Regime : mot
> pour mot, 0,25.**

### 4.9 Ce que je verifie et qui ne fonde aucune demande

- **Cinq des neuf items neufs portent le meme moule** : *"Napoleon Bonaparte cree X. Comment
  appelle-t-on X ?"*. C'est monotone, et chacun enseigne pourtant un fait distinct que le programme
  demande. **`HIS-1` est la clause qui gouverne cette famille, elle se compte sur le lot, et elle est
  tenue a 20,9 %.** Poser aujourd'hui une barre sur la repetition d'un moule serait l'appliquer au lot
  que je suis en train de juger. **Je ne la pose pas ; si je la pose, ce sera avant un lot.**
- **L'indice de la Banque de France - *"porte le nom du pays"* - fait de l'item un appariement de
  mots.** Il est operant, il permet a l'enfant bloque d'avancer, et c'est ce que je demande d'un
  indice. Je le nomme pour qu'il ne se relise pas comme un manque.

---

## 5. `HG-CM1-12` - **REFUSE**. La ligne ne ferme pas et reste `en cours`

**Verdict : la ligne ne passe pas `couvert-valide`.** Story `S33-HG-08-12`, moitie `HG-CM1-12`.

**Je dis d'abord ce que le refus ne porte pas sur**, parce que le lot a fait plusieurs choses
difficiles et qu'elles sont acquises :

- **`HIS-5`, ma frontiere ecrite avant le premier item, est respectee.** Aucun des neuf items neufs ne
  reprend la cellule *se loger dans un espace urbain*, deja depensee par l'item des immeubles
  collectifs de `HG-CM1-11`, ligne fermee. **Verifie par moi sur les vingt items des deux lignes, et
  non sur le test** : le test n'ecarte qu'une chaine de caracteres, ma frontiere porte sur la cellule.
  **Mon refus d'avance numero 1 est evite.**
- **Les six cellules libres sont toutes servies, et *on se cultive* la premiere.** C'etait la pratique
  que je disais que le lot oublierait ; elle est servie trois fois - un musee, des monuments
  historiques, un festival. **Mon refus d'avance numero 2 est evite.** `FER-1` est tenue au sens
  formel : les quatre pratiques sont en bonne reponse.
- `FER-2` : 10 items. `FER-3` : quatre items *se loger et se deplacer* contre six *travailler et se
  cultiver*, minorite a 4, au-dessus du plancher de 3.
- **Aucune inexactitude factuelle sur les neuf items neufs.** Chaque bonne reponse est juste.

**Et la ligne ne ferme quand meme pas, pour un motif unique, mesure, et actionnable.**

### 5.1 `HG-R17` - **reserve neuve, datee du 2026-09-10, BLOQUANTE** : le registre des distracteurs est le discriminant de la ligne entiere

**Les dix-huit distracteurs neufs de la ligne, releves un par un dans le fichier servi et vus a
l'ecran :**

| Item, par sa bonne reponse | Ses deux distracteurs |
|---|---|
| un chalet ou un appartement de location | *une ferme isolee*, un immeuble de bureaux |
| les services | *l'agriculture*, *la peche* |
| dans un musee | *dans une ferme*, *sur un bateau de peche* |
| des monuments historiques | des usines modernes, *des champs cultives* |
| le metro | *le tracteur*, *le bateau de peche* |
| un telesiege | un metro, *une peniche* |
| un camping | *une ferme d'elevage*, une usine desaffectee |
| dans un bureau | *dans un champ*, *sur un bateau de peche* |
| un festival | *une moisson*, *une peche en mer* |

**Compte, avec sa methode.** Attribution faite a la main par moi, sur les dix-huit distracteurs neufs
de la ligne, sur le fichier servi : **quatorze appartiennent au registre de la ferme, du champ, de la
peche ou de la batellerie** (en italique ci-dessus). **Et aucune des dix bonnes reponses de la ligne
n'y appartient - zero sur dix.**

**Ce que cela donne devant l'ecran, et c'est ce que j'ai vu en jouant** : *"la ferme, le champ, le
bateau de peche, la moisson ne sont jamais la bonne reponse"*. Un enfant qui a joue deux parties tient
cette regle, et **il repond aux neuf items sans lire les questions.** Il ne se trompe pas : il n'a
simplement rien lu.

**Ce que le refus ne dit pas, pour qu'on ne le lise pas de travers.** La ligne porte sur l'espace
urbain et l'espace touristique : **il est normal qu'une reponse rurale y soit fausse**, et je ne le
reproche a personne. Le defaut n'est pas que les reponses rurales soient fausses, c'est qu'elles
soient **les seules** a l'etre, **systematiquement**, sur toute la ligne, de sorte que la faussete se
lit dans le registre sans passer par la question.

**Et il y a le corollaire, qui est le meme fait vu de l'autre cote** : les distracteurs ne sont pas
des candidats. *"Sur un bateau de peche"* n'est pas une reponse a *"ou travaillent les employes d'une
banque ?"* ; *"une moisson"* n'est pas un evenement culturel ; *"un tracteur"* n'est pas un transport
en commun. **Sur au plus deux des neuf items neufs l'enfant a devant lui un distracteur serieux du
meme genre que la bonne reponse** - contre dix items sur dix pour `HG-CM1-08`, ecrit par le meme
developpeur dans le meme commit. Le meme lot montre donc qu'il sait faire.

**Deux items ajoutent un appariement de mots par-dessus** : *"une ville touristique riche en
**histoire**"* -> *"des monuments **historiques**"*, et *"quel logement **loue**-t-on"* -> *"un chalet
ou un appartement de **location**"*, avec un indice qui repete *"le logement loue"*.

**Pourquoi c'est un refus, et pourquoi ce n'est pas un durcissement de `PLA-1`, de `CAT-1` ni de
`ANG-R3`.** Il faut que je m'explique la-dessus, parce que j'ai ecrit trois fois le contraire en
quatre jours et que je ne veux pas qu'on croie que j'ai change d'avis :

- **`ANG-R3`, le 2026-09-09** : trois items sur cinq etaient unaires, et j'ai ferme la ligne. **Mon
  motif ecrit etait** : *"Aucune bonne reponse n'est fausse, et **les sept autres items de la ligne
  tiennent**."* **Sur `HG-CM1-12` ce motif ne peut pas s'appliquer** : neuf des dix items de la ligne
  **sont** les items neufs. Il n'y a pas de majorite qui tienne a cote. **C'est le critere que j'avais
  moi-meme ecrit qui echoue ici, pas une barre que je releve.**
- **`CAT-1`, deux paiements refuses** : je n'ai pas rouvert `EMC-CP-02` ni `EMC-CP-06` pour autant.
  **Ces deux lignes etaient deja `couvert-valide`** ; rouvrir une ligne fermee et refuser d'en fermer
  une neuve sont deux gestes differents, et le second est celui que ma porte est faite pour faire.
- **`PLA-1` seule ne refuse pas**, et je ne refuse pas sur `PLA-1` seule. **Je refuse sur un fait
  qu'aucune des trois n'attrape** : la faussete d'une proposition est predictible **au niveau de la
  ligne entiere**, sans lire l'item. Sur l'anglais, l'enfant devait au moins lire l'enonce - c'est
  l'enonce qui contenait sa reponse. **Ici il n'a meme pas a le lire.**
- **Et l'effet sur la matrice est la vraie raison pour laquelle je ne peux pas signer** : passer
  `HG-CM1-12` a `couvert-valide` affirmerait que le produit couvre *"decrire comment on se loge, on
  travaille, on se cultive et on se deplace dans un espace urbain et dans un espace touristique"*.
  **Une ligne dont les dix items se repondent par une regle sur le registre des distracteurs ne
  couvre pas cette competence** - elle ne la met jamais a l'epreuve.

> **`HG-R17`. Propriete a tenir, et je ne fournis pas les textes** - ecrire ces distracteurs est le
> jugement qu'il faut faire. **Sur `HG-CM1-12`, les deux distracteurs de chaque item appartiennent au
> meme genre que la bonne reponse et au meme espace que celui de l'enonce** : un autre lieu de la
> ville contre le musee, un autre transport urbain contre le metro, un autre hebergement de vacances
> contre le camping, un autre metier de ville contre le bureau. **Un enfant qui ne sait pas doit
> pouvoir se tromper serieusement.** Et sur les deux items nommes plus haut, le mot de la bonne
> reponse cesse d'etre appariable a un mot de l'enonce.
>
> **Ce que je ne demande pas** : aucun item neuf, aucune cellule de plus, aucun changement d'enonce
> sauf sur les deux items d'appariement. `FER-1`, `FER-2`, `FER-3` et `HIS-5` sont **tenues** et je ne
> les redemande pas ; je les reverifierai sans les rediscuter. **Le geste est la reecriture des
> distracteurs.**
>
> **Regime : rejeu borne, 0,5** - une partie complete avec erreurs volontaires plus la relecture des
> dix items de la ligne.

### 5.2 `HG-R18` - **reserve neuve, datee du 2026-09-10, non bloquante** : un enonce qui enumere deux des trois propositions

**Item servi, vu a l'ecran :**

> *"Dans un espace urbain, quel secteur emploie le plus d'habitants, **les services ou
> l'agriculture** ?"* -> *"les services"*, contre *"l'agriculture"* et *"la peche"*.

**La question offre une alternative fermee entre deux termes, et l'ecran en presente trois.** L'enfant
qui lit bien apprend de l'enonce lui-meme que *"la peche"* n'est pas a considerer : la question l'en
exclut. **Le jeu passe de trois choix a deux pour celui qui lit, ce qui est exactement l'inverse de ce
que la lecture devrait lui rapporter.**

**Non bloquante** : la bonne reponse est juste, l'item reste faisable, et le geste est un enonce.

> **`HG-R18`. Propriete a tenir** : l'enonce de cet item pose sa question sans enumerer de
> propositions. **Regime : mot pour mot, 0,25** ; il voyage avec `HG-R17` sur le meme paquet sans cout
> supplementaire (`PAR-10`).

### 5.3 Ce que je verifie sur cette ligne et qui ne fonde aucune demande

- **Biais social.** J'ai regarde si les neuf items presupposent un contexte que tous les enfants n'ont
  pas - une station de ski, un telesiege, un camping sur la Cote d'Azur, un chalet loue. **Ils ne le
  presupposent pas** : chaque enonce donne l'espace dont il parle, et decrire un espace touristique
  est precisement le libelle de la ligne. **Je n'ouvre rien la-dessus.** Je le dis parce que sans cela
  ce point se relira comme manque.
- **`BIA-1` a `BIA-5`** : aucun stereotype de genre, d'origine, de milieu ou de configuration
  familiale sur les dix items. Aucun personnage n'est juge.

---

## 6. `CONS-1` - **non levee**, et le compte que ce sprint deplace

La consigne servie est **inchangee**, relevee a chacune de mes 280 manches, **31 mots, cinq clauses** :

> *"Lis bien, puis situe dans le temps, nomme un peuple ou un mouvement de population, associe un roi
> a son fait, decris un espace ou une ressource, ou lis un document."*

**Ce que ce sprint y change, mesure sur ce que le tirage m'a servi.** Sur les **200 manches** de ma
seconde serie, appariees a leur item du fichier (199 sur 200 appariees ; une manche n'a pas pu l'etre
par une difference d'apostrophe, et je le dis plutot que d'arrondir) :

| Ligne servie | Manches | La consigne nomme-t-elle sa tache ? |
|---|---|---|
| `HG-CM1-08` | **17** | **aucune des cinq clauses.** *Comment appelle-t-on ce texte, cette recompense, ce representant ?* n'est ni situer dans le temps, ni nommer un peuple, ni associer un roi a son fait, ni decrire un espace, ni lire un document |
| `HG-CM1-12` | 18 | *decris un espace*, largement |
| `HG-CM1-04`, item 85 | comprises dans les 17 de la ligne 4 | *d'ou venait cette armee* n'est plus *nomme un peuple ou un mouvement* depuis `S33-HG-04` |

**Avant s33, `HG-CM1-08` portait un item sur 92 ; elle en porte dix sur 110.** La part de manches dont
la tache n'est nommee par aucune clause augmente donc mecaniquement d'environ huit points du seul fait
de cette ligne. **Napoleon Bonaparte n'est pas un roi** : la clause *"associe un roi a son fait"* ne le
couvre pas, et je ne fais pas semblant qu'elle le couvre.

**Je ne refais pas le comptage a la main de mon arbitrage prealable** - neuf manches sur vingt le
2026-09-09 - **et je ne le fusionne pas avec celui-ci** : un chiffre date ne se refait pas a chaque
document, et les deux n'ont pas la meme methode.

> **`CONS-1` reste non levee sur cette entree, et elle reste non bloquante.** Ce n'est pas un reproche
> au lot : refondre la consigne n'etait pas son objet, et `S33-CONS-4` est restee bloquee tout le
> sprint faute de la decision de grain. **C'est le constat que la methode ne converge toujours pas**,
> et que la decision de grain de `CONS-4` est maintenant a prendre sur un paquet de 110 items au lieu
> de 92 - exactement ce que mon arbitrage prealable disait qu'elle couterait si elle arrivait apres.

**`CONS-4` reste ouverte, non bloquante, portee D, non chiffree.** La decision appartient a
`camille-po` et a moi ensemble (`ADR-0081`), et **elle m'est due autant qu'a lui** : je ne l'ai pas
rendue ce sprint, je l'ecris.

---

## 7. `SCO-1` - mesuree une quatrieme fois, et je ne la chiffre toujours pas

**Mesure du 2026-09-10, vingt-huit parties completes de dix manches, chaque proposition fausse pressee
avant la bonne, score lu dans la reponse de chaque appui :**

| | |
|---|---|
| Parties | **28** |
| Scores finaux | **28 fois 10 / 10** |
| Appuis depenses pour dix manches | **de 15 a 27** |

**Un enfant qui ne sait rien rapporte 10 sur 10 de ce jeu, en pressant tout.** L'ecran de fin dit
*"Bravo, c'est juste ! C'est fini !"* apres vingt-sept appuis dont dix-sept faux.

**Ce que j'en fais, et c'est inchange** : `D7` reste a **0,0**. Cette observation est gratuite, faite
dans des parties que je jouais de toute facon. **Elle redeviendra due, a 0,5, le jour ou il y aura un
remede a verifier - et il n'y en a toujours pas.** Le remede appartient a `theo-archi` ; `SCO-1` a
`SCO-7` sont entierement automatisables et je ne les chiffre pas.

**Et la seule chose qui m'est due sur ce dossier, je ne l'ai pas rendue ce sprint** : l'instruction 1
de `DEC-0061` - *ce que trois essais doit vouloir dire quand l'elimination est possible* - est un
arbitrage pedagogique, il est a moi, il attend depuis neuf sprints et il n'attend plus que moi.
`theo-archi` a etabli qu'*"ajouter une proposition"* n'est pas un remede disponible. **Le choix entre
crediter moins et permettre moins reste ouvert et je le porte a s34.** Je l'ecris comme une dette de
ma porte, pas comme un obstacle de l'equipe.

---

## 8. Le poste anglais de l'instance A - **non engage, aucun verdict a rendre**

La composition de `DEC-0085` finance **0,5 pour `cm1-anglais`, `ANG-R3` a `ANG-R6`**, et `sofia-sm` a
nomme en section 10 de son planning que ce poste n'a **aucune story ecrite**.

**Verifie sur le diff de s33 plutot que suppose** : `git diff --name-only` entre le commit d'ouverture
du planning et la tete de `round-two` ne contient **aucun fichier d'anglais**. Ni le paquet
`cm1-anglais`, ni son test, ni son entree de catalogue ne sont touches.

> **Rien n'a ete livre sur ce terrain, donc je ne prononce rien.** `ANG-R3`, `ANG-R4`, `ANG-R5` et
> `ANG-R6` **restent ouvertes, non bloquantes, dans l'etat exact ou s32 les a laissees**.
> `LVE-CM1-07` reste `couvert-valide` et ne bouge pas. **Le 0,5 finance de l'instance A reste
> inemploye**, et c'est le sort que le planning avait annonce pour un poste sans story.

**La passe transverse de 0,5 de l'instance A** est dans le meme cas : aucune story, non engagee. Ce
document ne porte donc pas de passe transverse, et ce qu'il dit de `CONS-1` et de `SCO-1` releve du
reaudit des paquets que j'ai joues, pas d'une passe.

---

## 9. Ce dont je reste la seule garde sur ce perimetre

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Mordable par un automate ? |
|---|---|
| **`HG-R17`** - le registre des distracteurs comme discriminant d'une ligne entiere | **non**, et c'est ce qui refuse `HG-CM1-12` aujourd'hui. Les tests du lot sont **verts** sur cette ligne. Un comptage de mots par registre serait faisable si quelqu'un decidait des registres ; **personne ne l'a fait et je ne pose pas cette barre chiffree en cours de sprint** |
| **`HIS-5`** - la frontiere `HG-CM1-11` / `HG-CM1-12` | **non.** Le test ecrit par le lot ecarte **une chaine de caracteres** (*"des immeubles collectifs"*), pas une cellule. Il aurait laisse passer un item neuf qui dit la meme chose avec d'autres mots. **C'est un proxy, pas ma clause, et je le nomme plutot que de laisser croire qu'elle est encodee** |
| **`PLA-1`** - la plausibilite pour un enfant de neuf ans | **non** |
| **`EXA-1` et l'exactitude factuelle** - chaque enonce, chaque bonne reponse, chaque indice | **non.** `HG-R16` en est la preuve de plus : les quatre jobs sont verts sur un arbre ou un enonce dit que la Banque de France fabrique la monnaie |
| **`HIS-3` famille (b)** - une formulation disputee ou qui engage une couverture | **non** |
| **Qu'une question et ses propositions forment une phrase correcte recollees** (`HG-R13`) | **non**, et c'est mon propre texte qui l'a cassee cette fois |
| **Qu'un enonce garde un sujet a son infinitive** (`HG-R14`) | **non.** Douze mots, aucune barre de longueur ne le voit |
| **Qu'un indice permette a l'enfant bloque d'avancer** (`HG-R15`) | **non** |
| **`FER-3` sur `HG-CM1-04`** | **mordue par un proxy plus etroit que ma clause** : un ensemble de trois chaines de bonnes reponses ecrit a la main. Il rougira au bon moment tant qu'aucun item de l'autre tache ne portera l'une de ces chaines. **Le choix de la forme est bon** (`theo-archi`, avis de faisabilite) ; c'est sa portee que je nomme |
| **`FER-1` sur `HG-CM1-12`** | **mordue par un proxy** : le test cherche trois mots dans les enonces (*oeuvres d'art*, *monuments*, *evenement culturel*). Il prouve que *se cultiver* est servie, pas que les quatre pratiques le sont |
| **`CONS-1`, `CONS-4`** - la consigne nomme la tache et informe l'enfant | **partiellement**, et seulement si le paquet declarait la tache de chaque item - **ce qu'il ne fait toujours pas** |
| **`HG-R4`** - la mediane de charge de lecture du paquet | **oui**, et rien ne la mord : j'ai recompte a la main sur 110 enonces |
| **`SCO-1`** - le score distingue celui qui savait | **oui, entierement**, et **personne ne le tient** |
| **La couleur, le contraste, la taille, la position a l'ecran** | **personne sur ce terrain ce sprint** (audit degrade, section 0) |

---

## 10. Table des verdicts et des reserves

### 10.1 Les quatre lignes auditees

| Ligne | Story | Verdict | Etat de matrice demande | Mention |
|---|---|---|---|---|
| `HG-CM1-02` | `S33-HG-02` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** | aucune n'est due |
| `HG-CM1-04` | `S33-HG-04` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** | aucune n'est due |
| `HG-CM1-08` | `S33-HG-08-12` | **VALIDE SOUS RESERVE** | `en cours` -> **`couvert-valide`** | aucune n'est due |
| `HG-CM1-12` | `S33-HG-08-12` | **REFUSE** (`HG-R17`, bloquante) | **reste `en cours`** | sans objet |

**Bilan de la matiere apres ces verdicts : 10 `couvert-valide`, 1 `en cours`, 3 `non couvert`, 14 au
total.** `HG-CM1-05`, `-09`, `-10` restent `non couvert`, sans contenu, comme avant. **Le mouvement de
la matrice appartient a `camille-po` et a la review ; je ne l'ecris pas.**

### 10.2 Les reserves, avant et apres

| Reserve | Etat au 2026-09-10 | Regime |
|---|---|---|
| `HG-R1` a `HG-R8` | **levees** (s28, s29, s30, s32) | - |
| `HG-R9` | **levee ce jour** (section 2.2) | - |
| `HG-R10` | **levee ce jour** (section 3.2) | - |
| `HG-R11`, bloquante | **levee ce jour** (section 2.1) | - |
| `HG-R12`, bloquante | **levee ce jour** (section 3.1) | - |
| `HG-R13` | **ouverte**, neuve, non bloquante | rejeu borne, 0,25 |
| `HG-R14` | **ouverte**, neuve, non bloquante | mot pour mot, 0,25 |
| `HG-R15` | **ouverte**, neuve, non bloquante | mot pour mot, 0,25 |
| `HG-R16` | **ouverte**, neuve, non bloquante | mot pour mot, 0,25 |
| `HG-R17` | **ouverte, BLOQUANTE**, neuve | rejeu borne, 0,5 |
| `HG-R18` | **ouverte**, neuve, non bloquante | mot pour mot, 0,25, voyage avec `HG-R17` |
| `CONS-1` | **non levee** sur cette entree | non chiffree |
| `CONS-4` | **ouverte**, non bloquante, portee D | non chiffree, decision due |
| `ANG-R3` a `ANG-R6` | **ouvertes**, inchangees, terrain non engage | inchange |

**Sous `PAR-10`, les six reserves neuves vivent dans un seul paquet et se soldent ensemble : 0,5 en
tout**, et non la somme de leurs regimes. Les regimes disent ce que le developpeur doit faire, pas ce
que je facture.

### 10.3 Ce que l'instance A a depense de sa borne

| Poste finance (`DEC-0085`, section 3) | Depense | Ce que cela a achete |
|---|---|---|
| Reprise du paquet, `HG-R8` a `HG-R12` plus reliquat `CONS-1` (0,5) | **0,5** | 28 parties, les quatre reserves reverifiees une par une, `CONS-1` recomptee sur 200 manches |
| Remplissage `HG-CM1-08` / `-12` (2,0) | **2,0** | les vingt items des deux lignes lus un par un, les dix de `HG-CM1-11` reconfrontes sous `HIS-5`, `HIS-1` et `HG-R4` recomptees sur le fichier entier |
| `cm1-anglais`, `ANG-R3` a `R6` (0,5) | **0,0** | rien livre, rien audite (section 8) |
| Passe transverse (0,5) | **0,0** | aucune story, non engagee |
| Marge `PAR-11` (0,5) | **0,0** | non consommee |
| **Total** | **2,5 sur 4,0** | **+3 lignes fermees, 1 refusee** |

**1,5 de la borne de l'instance A n'est pas depensee**, faute de story sur deux de ses cinq postes.
C'est le sort que `sofia-sm` avait annonce en section 10 de son planning, et il s'est produit.

---

## 11. Ce que je route, et ce que je ne tranche pas

| Objet | Categorie | Route a | Attendu |
|---|---|---|---|
| `HG-R13` a `HG-R18` | Contenu | `camille-po`, via `sofia-sm` | Des stories. **`HG-R17` d'abord** : c'est la seule bloquante, et la seule qui ferme une ligne |
| La decision de grain de `CONS-4` | Produit / pedagogie | `camille-po` **et moi**, ensemble (`ADR-0081`) | Elle est due, elle n'a pas ete rendue a s33, **et je n'ai pas rendu ma moitie** |
| L'instruction 1 de `DEC-0061` sur `SCO-1` | **Pedagogie, a moi** | moi-meme, a s34 | Ce que *trois essais* veut dire quand l'elimination est possible. Neuf sprints. **Personne n'attend un autre que moi** |
| Le remede technique `SCO-1` a `SCO-7` | Technique | `theo-archi` | Inchange, non chiffre par moi |
| La portee du proxy de `FER-3` sur `HG-CM1-04` et de `FER-1` sur `HG-CM1-12` | Technique | `theo-archi` | **Aucune demande** : je nomme ce que ces tests couvrent et ce qu'ils ne couvrent pas, pour que personne ne croie ma clause encodee |
| Le paquet ecrit *"événement"* treize fois et *"évènement"* une fois, sur l'item neuf du festival | **Orthographe** | `relecteur-pedagogique` | **Ce n'est pas a moi** (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`), meme sur un texte lu par l'enfant. Je le signale et je n'y touche pas |

---

## 12. Les identifiants alloues dans ce meme diff (`PAR-5`)

**Six identifiants neufs**, tous sur le prefixe `HG`, deja alloue : **aucun prefixe neuf**.

| Identifiant | Ce qu'il designe | Section |
|---|---|---|
| `HG-R13` | Sur la tache d'ordre de `HG-CM1-02`, chaque proposition est un groupe nominal qui se lit recolle a sa question | 2.5 |
| `HG-R14` | L'enonce *"Avant de devenir empereur en 1804..."* nomme celui qui devient empereur, ou renonce a sa subordonnee | 4.6 |
| `HG-R15` | L'indice du Code civil nomme une propriete qui distingue l'une des trois propositions | 4.7 |
| `HG-R16` | L'enonce de la Banque de France decrit une mission qu'elle a reellement recue a sa creation | 4.8 |
| `HG-R17` | **Bloquante.** Sur `HG-CM1-12`, les deux distracteurs de chaque item sont du meme genre et du meme espace que la bonne reponse | 5.1 |
| `HG-R18` | L'enonce de l'item des services n'enumere pas ses propositions | 5.2 |

**Methode du comptage, a cote de son chiffre** : comptage des lignes de la table de la section 4 du
registre sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents
graves, `ADR-0085` clause 5), **avant** mon diff - **582 identifiants distincts, 97 prefixes distincts,
0 doublon**, ce qui confirme la ligne d'etat courant que `sofia-sm` a portee a l'assemblage. Plus mes
six allocations : **588 / 97**.

**Verifie plutot que suppose** : j'ai lance `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`
sur l'arbre apres mon diff, module `bootstrap` avec `-am` (perimetre nomme a cote du chiffre :
`bootstrap` et ses dependances, pas le depot). **Aucune de mes six allocations n'est en defaut** :
`HG-R13` a `HG-R18` resolvent, le document qu'elles designent les ecrit, et la ligne d'etat courant a
588 / 97 est acceptee.

**Et je dis ce que le meme lancement montre d'autre, plutot que de le taire.** Le garde-fou est
**rouge** sur l'arbre, sur des identifiants qui ne sont pas les miens : `EXC-R7` a `EXC-R9`, `PER-R3`,
`REG-R4`, `REG-R5`, `SEC-R4`, `SEC-R5`, `EPS-8`, `JEU-R9` a `JEU-R12` - ecrits par les instances B et
C dans leurs propres verdicts et non encore alloues. **C'est exactement la limite structurelle de
`PAR-5` que `theo-archi` a choisi de laisser nommee plutot que d'encoder**, et ce n'est pas a moi de
la reparer : chaque instance alloue ce qu'elle ecrit, et `sofia-sm` fait la reparation arithmetique de
la ligne d'etat courant a l'assemblage. **Je le nomme ici pour que personne ne decouvre ce rouge au
moment de la fusion en croyant qu'il vient de mon diff.**

**Et je dis d'avance ce que mon total ne peut pas etre.** Des que les instances B et C alloueront,
**588 sera faux** : la reparation est arithmetique - aucune allocation ajoutee, aucune retiree, aucun
sens touche - et elle appartient a `sofia-sm`, a l'endroit prevu. Je n'ecris pas de total pour l'arbre
assemble : l'instance A l'a fait a s31 et il etait faux.

---

## 13. Ce que ce document ne fait pas

- **Il ne bouge aucune ligne de `squad/programme/MATRICE-couverture.md`.** Il dit quel etat chaque
  ligne doit prendre ; `camille-po` l'ecrit a la review.
- **Il n'ecrit aucune story et ne repriorise rien.** Il produit des motifs.
- **Il ne rejuge aucun verdict anterieur.** `HG-CM1-01`, `-03`, `-06`, `-07`, `-11`, `-13`, `-14` et
  `LVE-CM1-07` restent exactement dans l'etat ou leurs verdicts les ont laissees. **Je n'ai pas rouvert
  `HG-CM1-11` en la relisant sous `HIS-5`** : je l'ai lue pour juger les items neufs de sa voisine, et
  elle ne bouge pas.
- **Il n'efface aucun refus.** Les refus de s32 sur `HG-CM1-02` et `HG-CM1-04` restent ecrits dans la
  fiche, avec leur date et leur motif, sous les levees d'aujourd'hui.
- **Il ne juge ni l'EMC CP ni l'EPS CP** : ce sont les instances B et C.
- **Il ne touche a aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.**
- **Il ne corrige ni l'orthographe, ni l'accentuation, ni la ponctuation de ce que l'enfant lit** :
  cela appartient au `relecteur-pedagogique`, et la seule occurrence trouvee lui est routee.
