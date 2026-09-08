# Arbitrage prealable de s32, s33 et s34 - le passage a trois instances, ma borne a 12,0, la decote
# par paire de coutures, l'affectation des trois terrains, et le prix reel d'une ligne marginale

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-08.
- **Arbre** : `round-two` a `690f0cde`, s31 clos. Jar reconstruit depuis cet arbre, application
  lancee sur le port 8091.
- **Ce document est un arbitrage prealable.** Il ne prononce aucun Done pedagogique, n'ecrit pas la
  matrice, n'ouvre aucun perimetre, n'ecrit aucune story et n'affecte personne. Il dit ce qui
  fermerait, ce que cela coute, ce que je refuse d'avance, et ce dont je reste la seule garde.
- **Il ne redige pas le `DEC`.** `camille-po` le fera apres moi ; les chiffres de ce document sont
  a reprendre, pas a recalculer.

---

## 0. Ce que j'ai joue aujourd'hui, et ce que je n'ai pas pu voir

**Aucun increment de s32 n'existe : le sprint n'est pas ouvert.** Ce que j'ai joue, c'est l'etat
servi aujourd'hui, sur les deux paquets dont ce document tire ses conclusions de couverture, plus une
mesure ciblee de la mecanique.

| Ce que j'ai joue | Ce que j'y ai verifie |
|---|---|
| `bandit-manchot-histoire-geographie-cm1`, **partie complete de dix manches**, une erreur volontaire par manche, une manche poussee a trois erreurs jusqu'a la revelation | l'etat reel du vivier des six lignes `en cours` ; le reliquat de `CONS-1` (section 1.2) ; la charge de lecture des items des lignes ouvertes |
| `bandit-manchot-anglais-cm1`, **partie complete de dix manches**, une erreur volontaire par manche | les cinq items de `LVE-CM1-07` servis, quatre d'entre eux dans une seule partie ; la consigne a deux taches, exacte sur les dix manches |
| **Mesure de score dediee** : cinq manches d'`anglais-cm1`, en pressant successivement les distracteurs puis la bonne reponse | **`SCO-1` est rouge** (section 2). Le score affiche passe de 2 a 3 apres deux erreurs suivies de la bonne reponse, et de 3 a 4 de la meme facon |
| **Le catalogue servi** : 66 entrees, 64 `bandit-manchot`, 2 `saisie` | **aucune entree de mecanique `designation`** : le palier 2 n'est jouable par personne (section 12) |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, le score et le tirage. Cela **ne** couvre ni la
couleur, ni le contraste, ni la taille, ni la position a l'ecran. **Aucune conclusion de ce document
ne repose sur un jugement visuel.**

**Ce que je n'ai pas joue** : les soixante-quatre autres jeux du catalogue. Ce document ne dit rien
d'eux, sauf la ou il nomme explicitement une passe transverse.

**Ce que je n'ai pas re-verifie et que je ne suppose pas** : la loi de tirage. Je donne des
proportions d'items, jamais une probabilite de tirage - j'ai deja enregistre un tirage comme
uniforme alors qu'il ne l'etait pas, et je ne recommence pas.

---

## 1. Trois corrections a mes propres constats, avant de m'en servir

### 1.1 Le residu du groupe 2a coute 3,0 et non 1,5 - mon erreur va dans le sens qui m'arrange

**Ce que j'ai ecrit le 2026-09-07**, section 6.2 de mon arbitrage prealable de s31 : le groupe 2a
vaut *"3,0, dont 1,5 solde gratuitement par le groupe 1"*.

**Recompte dans l'unite de ma propre clause `PAR-10` - un rejeu se paie par paquet, pas par
reserve.** Les dix reserves du groupe 2a se repartissent sur **huit paquets** :

| Paquet | Reserves 2a |
|---|---|
| `signal-information-cm1` | `SIG-R1`, `SIG-R2` |
| `melanges-cm1` | `MEL-R2`, `MEL-R3` |
| `division-posee-cm1` | `POS-R3` |
| `chaine-alimentaire-cm1` | `CHA-R1` |
| `objet-technique-cm1` | `OBT-R2` |
| `histoire-geographie-cm1` | `HG-R8` |
| `fait-opinion-cm1` | `FOP-R4` |
| `droit-devoir-cm1` | `DRO-R4` |

**Huit paquets a 0,5 font 4,0, pas 3,0.** Le groupe 1, paye a s31, a solde **deux** paquets
(`melanges-cm1`, `objet-technique-cm1`), soit **1,0** et non 1,5.

> **Le residu du groupe 2a est de 3,0, reparti sur six paquets, et non de 1,5.**

**Mes deux chiffres etaient faux, et tous les deux dans le sens qui minimise la dette.** Je le
signale parce que c'est exactement le motif pour lequel `camille-po` a corrige le compte de huit
reserves en neuf a la cloture de s31 (`DEC-0082`, section 1) : un ecart qui allege le cout de ce
qu'on vient de choisir. Le mien est du meme genre, et il est deux fois plus gros.

### 1.2 Le reliquat de `CONS-1` sur `histoire-geographie-cm1` a ete paye a moitie le jour meme ou je l'ecrivais

**Ce que j'ai ecrit le 2026-09-07**, section 6.3 : *"La consigne servie nomme deux taches qui
couvrent 26 des 57 items."*

**C'etait vrai a l'heure ou je l'ecrivais, et faux le soir.** Une troisieme reecriture est arrivee
dans le sprint. La consigne servie aujourd'hui nomme **quatre** taches :

> *"Lis bien, puis situe dans le temps, associe un roi a son fait, decris un espace ou une
> ressource, ou lis un document."*

**Je ne durcis rien et je ne rouvre rien : je constate ce que j'ai joue.** Deux des dix manches de
ma partie de ce matin demandent une tache qu'aucune des quatre ne nomme :

| Manche | Enonce servi | La tache reelle |
|---|---|---|
| 1 | *"Quel texte est adopte le 26 aout 1789, affirmant que les hommes naissent et demeurent libres et egaux en droits ?"* | **nommer un texte**, pas le situer |
| 7 | *"Le vent fait tourner de grandes helices qui produisent de l'electricite. Comment appelle-t-on ces installations ?"* | **nommer une installation**, pas decrire un espace |

**Et le fait qui compte pour la suite de ce document** : les items servant `HG-CM1-03` (*"Quel
peuple les a construits ?"*), `HG-CM1-04` (*"Comment appelle-t-on ces peuples ?"*) et `HG-CM1-08`
(*"Quel nouveau regime politique commence donc en 1804 ?"*) sont **dans le meme cas**, et ces trois
lignes sont trois des six que la reserve `en cours` fermerait. **Le reliquat de `CONS-1` a diminue,
il n'a pas disparu, et il tombe exactement sur les lignes que ce plan veut fermer.**

### 1.3 Mon refus d'avance numero 1 de s31 sur `EMC-CP-04` est leve - sa condition est remplie

**Ce que j'ai ecrit le 2026-09-07**, table de la section 10, refus numero 1 : *"Tout item sur
`EMC-CP-04`. Aucun lot n'y depense d'items"*, avec pour motif *"la ligne est `non couvert` **sans
annotation**. Route a `camille-po` : l'annoter, ou vingt items partiront dedans"*.

**L'annotation existe dans la matrice** : *"jouable au choix, sous `EMC-4`, `BIA-1` et `BIA-2`,
seulement sous la forme de l'interdit, jamais sous celle de l'enumeration des differences."*

> **Je leve ce refus.** Le motif que j'avais ecrit est le seul que j'avais ; il est paye. `EMC-CP-04`
> est prenable a partir de s32, sous `EMC-4`, `BIA-1` et `BIA-2`, dont aucun terme ne bouge.

**Le refus n'est pas efface** : il reste lisible dans le document de s31, avec sa date et son motif,
et cette levee est ecrite ici, datee du 2026-09-08. **Une equipe qui atteint la barre que j'ai
ecrite recupere sa ligne**, et je ne me sers pas d'un poteau paye pour en planter un autre.

---

## 2. Ce que j'ai trouve en jouant, et qui n'est dans aucune liste de dette : le score reste parfait par elimination

**Mesure de ce matin, sur `bandit-manchot-anglais-cm1`, score lu dans la reponse de chaque appui :**

| Manche | Ce que j'ai presse | Score apres |
|---|---|---|
| 3 | `le drapeau de l'Australie` (faux), `le drapeau du Canada` (faux), `le drapeau des Etats-Unis` (juste) | **2 -> 3** |
| 4 | `town` (faux), `field` (faux), `hill` (juste) | **3 -> 4** |
| 5 | `castle` (faux), `witch` (juste) | **4 -> 5** |

**Sur un item a trois propositions, un enfant qui ne sait rien et qui presse les trois sans jamais
represser la meme a necessairement raison au dernier appui, et ce dernier appui compte un point.**
C'est `SCO-1`, mot pour mot, tel que `docs/backlog/backlog.md` l'ecrit : *"Aujourd'hui elle l'est :
`resoudre(true)` fait `reussites + 1` quel que soit le nombre d'essais deja depenses."*

**Etat de cette dette, et il n'est pas dans les listes que l'on me transmet :**

- `DEC-0061` l'arbitre le 2026-08-31 comme **un defaut a corriger**, sur ma propre mesure de s24 :
  **582 items du catalogue sur 615, soit 94,6 pour cent, portent deux ou trois propositions.**
- **Premier report** : non engage a s24. **Second report** : pose par `sofia-sm` au planning de s25.
- **Le compteur s'arrete la.** Le backlog ecrit *"un report a s26 serait le troisieme"* et rien n'a
  ete enregistre depuis. **s26, s27, s28, s29, s30, s31 : six sprints sans report ecrit, sur une
  dette dont le troisieme report etait annonce comme comptable.**
- **Elle est vivante aujourd'hui, je viens de la jouer.**

**Ce que cela vaut, dit une fois et sans emphase** : le produit revendique 63 lignes
`couvert-valide`, et sur chacune d'elles, le score qu'un enfant rapporte de la partie ne distingue
pas celui qui savait de celui qui a elimine. **C'est le defaut de la plus grande portee du depot -
il touche 64 des 66 entrees du catalogue - et c'est le seul de cette taille dont le report a cesse
d'etre compte.** `REG-R1` et `PER-R1` sont a leur quatrieme sprint et tout le monde le sait ;
`SCO-1` est a son huitieme et personne ne le compte.

**Ce que je fais de ce fait, et ce que je ne fais pas.**

- **Je ne fais pas de sa correction une condition du Done.** Ce serait deplacer un poteau : aucune
  de mes conditions de fermeture, de `FER-1` a `FER-3`, ne porte sur le score, et les 63 lignes ont
  ete validees sans lui. **Un verdict prononce ne se reecrit pas.**
- **Je pose une reserve neuve et datee du 2026-09-08, qui ne vaut que pour l'avenir** :
  a partir de la cloture de s32, **chaque fiche que j'ecris nomme, dans son bloc de validation, si
  le score du jeu reste parfait par elimination.** Motif : je refuse que le chiffre de couverture
  soit lu comme un nombre de lignes qu'un enfant ne peut pas simuler, alors que ce n'en est pas un.
  C'est une mention de portee, de la meme famille que *"connaissance de la formule seule"*, et elle
  se retire le jour ou `SCO-1` est vert.
- **Je chiffre ma part et pas celle des autres** : ma verification apres correction vaut **0,5**,
  parce qu'elle porte sur le depot servi entier et se compte plutot qu'elle ne se lit. Le remede
  lui-meme appartient a `theo-archi` et son cout n'est pas de mon ressort. `SCO-1` a `SCO-7` sont
  **entierement automatisables** - le backlog le dit lui-meme : *"Ce qui n'est pas testable par un
  automate : rien, une fois le remede choisi."*

---

## 3. Le rejugement que j'ai promis a la cloture de s31 - le critere de couture ne bouge pas

**Ce que j'ai ecrit le 2026-09-08 au matin**, section 4.4 de `conclusion-nadia.md` : *"je
rejugerai, a l'arbitrage prealable de s32 et pas ici, si 'une occurrence de l'une des trois formes'
reste le bon critere quand la seule famille qui survit est celle-la"* - la famille etant **le nombre
agrege d'une ressource qu'aucune branche ne voit entierement**.

**Je rejuge, et le critere ne bouge pas d'un mot.**

Motif, et il est arithmetique : a deux instances, un agregat a **une** moitie invisible. A trois
instances, il en a **deux tiers**. La famille qui survit est precisement celle que le passage a
trois instances multiplie. **Retrecir mon critere le jour ou sa surface triple serait baisser une
barre au moment ou elle commence a servir**, et c'est la definition meme du poteau deplace.

**Et je dis ce qui me le ferait retirer, pour que ce ne soit pas a redemander.** La forme (b) - *un
chiffre qu'aucune des deux moities ne peut verifier et qu'un tiers trouve faux en recomptant* -
cesse d'etre mon affaire le jour ou un garde-fou **calcule l'agregat sur l'arbre assemble et fait
echouer le build quand le compte propre d'une branche le dement**. S'il existe et qu'il est vert a
la cloture d'un sprint, **je retire la forme (b) de mes trois formes** : je ne garde que ce que rien
d'autre ne garde. Je dis la propriete ; **la forme du test appartient a `theo-archi`.**

**Etat des deux conditions de levee au 2026-09-08 :**

| Condition | Etat |
|---|---|
| Le registre d'identifiants de `PAR-5` ecrit | **acquise le 2026-09-07, et elle le demeure** : elle n'aura pas a etre repayee |
| Un sprint parallele clos **sans aucune occurrence** des formes (a), (b), (c) | **non acquise.** s29 : une occurrence. s30 : deux, forme (a). s31 : forme (c) a l'assemblage du planning, forme (b) sur le compte du registre |

---

## 4. Ma borne a trois instances

### 4.1 Pourquoi elle ne se derive pas de la precedente

**Le precedent est ecrit, et c'est moi qui l'ai disqualifie comme methode.** Section 1.1 de mon
arbitrage de s31 : la derivation de `DEC-0077` section 8.2 **retranche de la capacite deux postes de
0,5 qui sont ensuite depenses a l'interieur du sprint**, et j'ai refuse le +1,0 que cette correction
me devait. **Le 8,0 est empirique, pas derive.** Je ne peux donc pas multiplier une formule que j'ai
declaree fausse ; je raisonne sur l'increment.

**Ce que la troisieme instance ajoute** : 5,0 bruts de capacite d'audit de lots.
**Ce qu'elle cree** : **le nombre de paires de coutures passe de 1 a 3.** Avec n instances, il y a
n(n-1)/2 paires : deux instances forment une paire, trois en forment trois. **La capacite croit de
50 pour cent, la surface de couture triple.**

### 4.2 La decote se compte par paire, et la borne est 12,0

**La question m'est posee sans etre tranchee a ma place, et je tranche : la decote se compte par
paire.** Motif : l'objet d'une couture n'est ni une instance ni un sprint, c'est **une jointure entre
deux travaux** - c'est la definition que j'ai ecrite le 2026-09-05 et je n'y touche pas. Une decote
indexee sur le nombre d'instances serait indexee sur la mauvaise grandeur.

| Poste | Effet |
|---|---|
| Capacite brute : 3 x 5,0 | **15,0** |
| **Retenue de defiance sur la couture, 1,0 par paire, trois paires** | **-3,0** |
| **Borne totale de s32, s33 et s34 a trois instances** | **12,0** |

**Ce que je ne retranche pas, et le motif de chaque non-retranchement :**

- **La passe transverse (`PAR-4`)** : je ne la retranche pas de la capacite, je la **depense dedans**,
  a 0,5. C'est la correction de ma section 1.1 de s31, et je l'applique a moi-meme ici plutot que de
  reproduire l'erreur a une echelle plus grande. Son objet est le depot servi entier, dont la taille
  ne depend pas du nombre d'instances : elle reste a **0,5** malgre le passage de 58 a 66 entrees.
- **Les dettes de portee D** : meme raison. Elles se paient dans la borne, poste par poste
  (section 7), pas en amont d'elle.
- **Un poste distinct pour le premier sprint de la troisieme instance** : la decote par paire le
  couvre deja. Les deux paires neuves (A-C et B-C) **sont** l'exposition de la troisieme instance.
  Le compter deux fois serait la double soustraction que je viens de corriger.

**La verification qui fait que 12,0 n'est pas un chiffre invente, et c'est la seule qui compte** :

> **12,0 / 3 = 4,0 par instance.** C'est **exactement** le budget opposable par instance de `PAR-9`,
> tenu a s30 et tenu **a l'unite** a s31 (8,00 sur 8,0, instance A 4,00 / 4,00, instance B
> 4,00 / 4,00 - premier sprint sans depassement en quatre).

**Je n'eleve la charge de personne. J'ajoute une instance a la charge que les deux autres ont deja
soutenue deux fois, dont une exactement.** C'est le seul argument que j'aie, et il est mesure.

**Et je dis ce que je refuse de faire de ce chiffre** : 12,0 est la borne **si et seulement si** le
passage a trois instances est arbitre. **Si l'utilisatrice reste a deux instances, ma borne est
8,0**, comme je l'ai ecrit ce matin meme a la cloture de s31, et rien dans ce document ne la change.
12,0 n'est pas une revision a la hausse de 8,0 : c'est la borne d'un autre dispositif.

### 4.3 Ce qui la fait tomber a 15,0, et c'est atteignable des la cloture de s32

**Ma barre du 2026-09-05 est inchangee, et elle porte sur la retenue entiere.**

> **Si s32 clot sans aucune occurrence des formes (a), (b) ou (c), la retenue de 3,0 tombe en une
> seule fois et la borne de s33 est 15,0.**

**Elle tombe en bloc, pas paire par paire, et ce n'est pas une severite : c'est le contraire.**
Faire tomber la decote paire par paire exigerait d'attribuer une occurrence a une paire, et mes
formes (b) et (c) sont precisement les occurrences qu'aucune moitie ne peut attribuer. **Une
condition qu'on ne peut pas constater n'est pas une condition.** Le tout ou rien est ici la lecture
la plus favorable a l'equipe, et c'est pour cela que je la retiens.

**Ce que cela vaut, dit clairement pour que l'incitation soit visible** : 3,0, soit deux lots de
contenu neuf, sur s33 et s34. **C'est le poste le plus rentable de tout ce document, et il ne coute
pas une ligne de couverture** - seulement de la discipline a la jointure.

---

## 5. `PAR-8`, `PAR-9` et `PAR-11` a trois instances

### 5.1 `PAR-8` - je confirme, et je dis pourquoi je n'ajoute pas de plafond de lots

**Le plafond individuel dur de 5,0 et le plafond de trois lots par instance sont reconduits sans
changement.** Le 5,0 n'est toujours pas le chiffre de planification (`PAR-9`).

**On m'invite a ajouter un plafond de lots pour le sprint entier. Je ne l'ajoute pas, et le motif
est que `PAR-9` le rend inutile.** L'arithmetique, faite plutot que supposee : une instance a 4,0
qui reserve sa marge de rejeu (1,0 le plus souvent) dispose de **3,0 pour des lots**. Cela lui
autorise **deux lots a 1,5, ou trois lots a 1,0**, jamais plus. Le sprint plafonne donc de lui-meme
a **six lots chers ou neuf lots bon marche**, sans qu'une clause de plus soit necessaire.

**Je pourrais poser 7 ou 8 comme plafond de sprint. Je ne le fais pas parce que je n'ai aucune
mesure qui designe 7 ou 8 plutot qu'un autre nombre**, et un chiffre sans methode dans un document
que l'equipe lit est de la meme famille que la reference qui pointe vers rien.

### 5.2 `PAR-9` - je confirme, avec une clause que trois instances rendent necessaire

**Budget opposable : 4,0 par instance, marge de rejeu comprise. Somme = 12,0 = la borne.** Inchange.

> **`PAR-9`, clause ajoutee, datee du 2026-09-08.** **Aucun budget ne se transfere d'une instance a
> une autre en cours de sprint sans etre ecrit au moment du transfert**, dans le fichier de planning
> du sprint, avec l'instance qui cede et le poste cede. Le plafond de `PAR-8` (5,0) borne le
> transfert. **Motif** : a deux instances, un transfert non ecrit se voyait dans le total ; a trois,
> il se noie. Et un transfert decouvert a la cloture est un chiffre qu'aucune instance ne peut
> verifier depuis sa moitie - **c'est ma forme (b), et il maintiendrait la retenue.**

### 5.3 `PAR-11` - je l'amende, et cette fois j'ai la mesure que je n'avais pas

**A s31 j'ai refuse d'amender `PAR-11` le lendemain de son ecriture**, en nommant l'ecart de 0,5 a
1,0 qu'elle ne couvrait pas plutot que de deplacer ma propre regle. **La mesure est arrivee, et elle
dit que `PAR-11` est bien dimensionnee et sans une once de jeu :**

| Instance de s31 | Lots neufs | Marge due | Reaudits reellement consommes |
|---|---|---|---|
| A (EMC CP) | 2 | 1,0 | 2 paquets (`EMC-CP-03` leve par reecriture, `EMC-CP-07` maintenu refuse) = **1,0** |
| B (sciences CM1) | 2 | 1,0 | 2 paquets refuses au premier passage, revalides = **1,0** |

**La marge a ete consommee exactement, des deux cotes, sans reste.** Une regle juste a l'unite deux
fois de suite n'est pas a corriger sur son taux ; elle est a corriger sur le cas qu'elle ne
distingue pas.

> **`PAR-11` amendee, datee du 2026-09-08, applicable a s32 et au-dela, non retroactive.**
> **0,5 par lot neuf audite, par instance** - inchange - **plus 0,5 pour toute instance qui audite
> une matiere dont mes conditions n'ont jamais rencontre de contenu reel.** La marge du sprint est
> leur somme.
>
> **Motif, et il est mesure sur les deux seules populations que j'aie** : les deux taux de refus au
> premier passage de ce depot portent tous les deux sur une matiere dont les conditions etaient
> inedites - **EMC a s29, quatre paquets refuses sur cinq** ; **sciences a s31, deux paquets sur
> quatre**. Aucune matiere dont les conditions avaient deja mordu du contenu n'a produit ce taux.
> **Ce n'est pas la nouveaute de la matiere qui coute, c'est la nouveaute de la condition**, et c'est
> le seul cas que `PAR-11` ne distinguait pas.

**Ce que cet amendement coute a l'equipe, dit franchement** : il augmente la marge, donc il diminue
ce que le sprint peut planifier en lots. **Il ne releve la barre d'aucun increment** - c'est une
regle de mon budget, pas une condition de fermeture. Et il ne s'applique pas retroactivement a s31,
qui est clos.

---

## 6. `PAR-7` et l'affectation des trois terrains

### 6.1 Ce que mes propres clauses imposent, et qui n'est pas un choix

**`PAR-2` change de nature a trois instances, et il faut le dire avant de composer.** A deux
instances, une seule portait un terrain de contenu et l'autre pouvait recevoir toutes les dettes. A
trois instances, `PAR-2` - *une matiere et un niveau ne sont jamais partages* - **force la
repartition des rejeux** : un rejeu de paquet est un travail **sur une matiere**, donc il va a
l'instance qui detient cette matiere, et a aucune autre.

**Consequence directe sur les six paquets du residu 2a, et ce n'est pas negociable :**

| Paquet a rejouer | Matiere et niveau | Va donc a l'instance qui detient |
|---|---|---|
| `signal-information-cm1` (`SIG-R1`, `SIG-R2`) | sciences et technologie CM1 | **sciences CM1** |
| `chaine-alimentaire-cm1` (`CHA-R1`) | sciences et technologie CM1 | **sciences CM1** |
| `histoire-geographie-cm1` (`HG-R8`) | histoire-geographie CM1 | **histoire-geographie CM1** |
| `division-posee-cm1` (`POS-R3`) | mathematiques CM1 | l'instance a qui les mathematiques CM1 sont **explicitement** attribuees |
| `fait-opinion-cm1` (`FOP-R4`) | EMC CM1 | l'instance a qui l'EMC est **explicitement** attribue |
| `droit-devoir-cm1` (`DRO-R4`) | EMC CM1 | idem |

> **L'instance d'accumulation cesse d'etre le depotoir des dettes.** C'etait sa fonction a deux
> instances ; a trois, elle ne garde que ce qui est de portee **D** - la passe transverse, `CONS-*`,
> la verification de `SCO-1` - et les lignes `en cours`. **Le reste suit sa matiere.** Une lecture
> qui empilerait les dettes sur A violerait `PAR-2` sur quatre matieres a la fois, et les portees
> **L** et **M** de ma table de s29 tomberaient en silence.

### 6.2 Les trois terrains, et ma correction a la lecture proposee

**La lecture qui m'est transmise est juste sur deux terrains sur trois, et fausse sur le premier.**

| | Lecture proposee | Mon arbitrage |
|---|---|---|
| **A** | les dettes **et** l'histoire-geographie CM1 | **Non sur les dettes** (section 6.1) : A prend l'histoire-geographie CM1, l'anglais CM1, et la portee **D**. Les dettes suivent leur matiere |
| **B** | sciences et technologie CM1 | **Confirme.** B en a l'image depuis s30 et s31, quatre lignes fermees, `SCI-1` a `SCI-4` eprouvees. `SIG-R1`, `SIG-R2` et `CHA-R1` viennent avec, par `PAR-2` |
| **C** | l'education physique et sportive aux deux niveaux | **Confirme, et c'est le bon choix.** C'est bien le seul terrain de cette taille dont personne n'a l'image |

> **`PAR-12` - nouvelle, datee du 2026-09-08.** **Avec n instances, une seule porte l'accumulation.**
> Elle prend les travaux de portee **D** (passe transverse, `CONS-*`, verification de `SCO-1`), les
> lignes `en cours` et les matieres qu'elle a deja auditees. Les autres prennent un terrain chacune.
> **`PAR-7` est generalisee, pas remplacee** : sa phrase *"on ne demande pas a une instance de
> rattraper une image qu'elle n'a pas"* est le motif, et il est intact.
>
> **`PAR-13` - nouvelle, datee du 2026-09-08.** **Au plus une instance par sprint prend un terrain
> dont mes conditions n'ont jamais rencontre de contenu reel.** **Motif** : c'est le profil exact des
> deux seuls taux de refus au premier passage que j'aie mesures (section 5.3), et **je n'ai aucune
> mesure de deux tels terrains menes en parallele**. Un dispositif inedit ne se decouvre pas a pleine
> charge - c'est le meme raisonnement que celui qui m'a fait recommander quatre lots et non cinq a
> s29.

**Verification de l'affectation contre mes clauses, faite et non supposee** : `PAR-1` - aucun lot
partage. `PAR-2` - HG CM1 et anglais CM1 chez A seule ; sciences CM1 chez B seule ; **EPS CP et EPS
CM1 comptent pour une seule matiere** (comme EMC CP et EMC CM1) puisque `EPS-1` a `EPS-3`
s'appliquent aux deux niveaux, donc les deux niveaux vont chez C. `PAR-3` - les sept lignes `en
cours` vont a A, qui a fait leur attribution a la main a s28 et les a auditees a s29. `PAR-13` - une
seule instance sur un terrain a conditions inedites : C.

### 6.3 L'EPS ne demande aucun arbitrage prealable de matiere, et je le chiffre a zero

**La question m'est posee et la reponse est non. Verifie fichier par fichier :**

| Ce qu'un terrain doit avoir avant qu'un item n'existe | Etat de l'EPS |
|---|---|
| Un referentiel ecrit | **oui**, aux deux niveaux, ecrit par moi le 2026-09-03 (`DEC-0074`) |
| Des codes de ligne alloues | **oui** : `EPS-CP-01` a `EPS-CP-11`, `EPS-CM1-01` a `EPS-CM1-10`. Sans eux, `FER-1` et `FER-2` ne seraient pas calculables et le terrain serait imprenable |
| Des conditions opposables posees | **oui** : `EPS-1`, `EPS-2`, `EPS-3`, plus `BIA-4` |
| Les lignes jouables annotees | **oui**, les six : `EPS-CP-04`, `-08`, `-10`, `-11`, `EPS-CM1-09`, `-10` |
| Les mentions de couverture partielle pre-ecrites | **oui**, dans la matrice, aux deux niveaux, et je les redemanderai mot pour mot |

> **Cout d'un arbitrage prealable de matiere sur l'EPS : 0,0. Il est deja fait.** Le precedent du
> palier 2 a s29 etait hors borne parce que le referentiel et les conditions n'existaient pas ; ici
> ils existent depuis cinq jours. **Rien de ce terrain n'est hors borne.**

**Ce que l'EPS demande a la place, et qui n'est pas gratuit** : ses six lignes sont **toutes** des
moities de competence, et **aucune de ses trois conditions n'a jamais rencontre un item**. C'est le
profil de `PAR-13`, et c'est pour cela que C porte la marge supplementaire de 0,5.

**Et une contrainte d'arithmetique que je nomme avant qu'elle ne surprenne** : six lignes a onze
items (`FER-2` plancher dix, `DEC-0064` onze dans un paquet de fermeture a dix manches) font
**66 items**, soit **trois lots**. A 1,0 pour les lignes de regle et 1,5 pour celles qui touchent le
corps, cela fait 4,0 de lots seuls, **au-dela du budget d'une instance marge comprise**.
**L'EPS ne se ferme pas en un sprint. Il en faut deux, et c'est arithmetique.**

### 6.4 La collision EPS / sciences / questionner le monde sur le corps et l'effort - la seule couture que je vois d'avance

**C'est le point le plus important de ce document apres `SCO-1`, et il vient de l'affectation
elle-meme.** Trois lignes de trois matieres differentes portent le meme objet, et la matrice le
signale deja deux fois de son propre chef :

| Ligne | Libelle | Instance qui la detiendrait |
|---|---|---|
| `EPS-CP-10` / `EPS-CP-11` | effets de l'effort sur le corps ; echauffement, hydratation, recuperation | **C** |
| `EPS-CM1-10` | effets de l'activite physique sur le corps, et les regles de securite | **C** |
| sciences CM1, *"Relier l'alimentation, l'activite physique, le sommeil et l'hygiene au bon fonctionnement du corps"* | | **B** |
| questionner le monde CP, *"Connaitre quelques regles d'hygiene de vie (sommeil, alimentation, hygiene du corps)"* | | **A**, et cette ligne est **sans annotation** (section 11) |

**`ADR-0081` clause 5 : un item n'est compte que sur une ligne.** Trois instances qui ecrivent des
items sur *l'activite physique et le corps* sans se voir produisent exactement ce que ma definition
appelle un defaut de couture : **un defaut a la jointure, invisible de l'interieur de chacune des
trois moities.** Aucune ne peut le voir, aucune ne fait rien de faux, et deux lignes se ferment sur
les memes connaissances.

> **La frontiere, ecrite avant qu'un item n'existe. Elle est de moi et elle est opposable.**
>
> - **L'EPS ne porte que l'effort lui-meme** : ce que l'effort fait au corps dans l'instant
>   (essoufflement, coeur qui bat vite, chaleur, soif), ce qui l'entoure (echauffement, hydratation,
>   recuperation) et les regles de securite de la pratique. **Jamais l'hygiene de vie en general.**
> - **Les sciences CM1 ne portent que les quatre facteurs de sante generale** - alimentation,
>   activite physique, sommeil, hygiene - **rapportes au fonctionnement du corps, jamais aux effets
>   immediats d'un effort.**
> - **Questionner le monde CP ne porte que le niveau CP de l'hygiene de vie**, et `CYC-1` s'applique :
>   aucun item de cycle 3 ne contredit ce que le CP enseigne sur le meme objet.
>
> **Je refuse d'avance toute ligne des trois qui fermerait sans que cette frontiere ait ete ecrite
> dans la specification de son lot, avant le premier item.** Ce n'est pas une exigence de plus :
> c'est la seule facon dont trois instances peuvent servir cet objet sans que deux d'entre elles
> ferment la meme chose.

**Cout : 0,0 en unites d'audit.** Ce que cela coute est une obligation nommee dans le planning, et
c'est `sofia-sm` qui la porte, pas moi.

---

## 7. Le prix de chaque poste

**Tarifs de lot, reconduits de s31 sans changement** : 1,5 pour un lot dont je ne suis pas la source
et ou chaque fait se verifie contre une source ; 1,0 pour un lot dont je suis la source. **Une seule
source a verifier dans un lot ouvre la verification et fait passer le lot a 1,5.**

### 7.1 La dette ouverte a l'ouverture de s32 - 7,5, plus `CONS-3` non chiffree

| Code | Poste | Paquets | Prix | Lignes |
|---|---|---|---|---|
| **D1** | `CAT-1` + `REG-R1` + `PER-R1`, **quatrieme sprint de report** | `regles-de-vie-cp`, `permis-interdit-cp` | **1,0** | 0 |
| **D2** | `EMC-CP-07`, refusee sur **`FAB-1c` seul** | `regle-souhait-loi-cp` | **0,5** | **+1** |
| **D3** | Residu du groupe 2a, **corrige en section 1.1** | 6 paquets | **3,0** | 0 |
| **D4** | Groupe 2b : reliquat de `CONS-1`, `CONS-2`, `SAI-R1`, `ERR-R1` | plusieurs | **1,0** | 0 |
| **D5** | Reserves neuves de s31 : `EMO-R1/R2`, `POL-R1/R2`, `EXC-R1` a `R5` | `emotions-autrui-cp`, `formules-de-politesse-cp`, `exclusion-cp` | **1,5** | 0 |
| **D6** | `CONS-3` | - | **non chiffree**, et je le dis comme manquant plutot que de l'estimer | 0 |
| **D7** | Verification de `SCO-1` apres correction, portee depot | 66 entrees | **0,5** | 0 |
| | **Total** | | **7,5 + `CONS-3`** | **+1** |

**`MEL-R4` n'y figure pas et ne coute rien** : elle voyage gratuitement dans le prochain rejeu de
`melanges-cm1` et **ne justifie pas un rejeu a elle seule** - c'est ecrit dans sa fiche.
**`HG-R8` ne coute rien non plus dans ce plan** : elle voyage gratuitement avec le lot de
remplissage de `histoire-geographie-cm1` (`PAR-10`, un rejeu se paie par paquet).

### 7.2 Le prix des lignes ouvrables, et la correction d'une lecture qui m'est proposee

**On me demande si je vois la reserve `en cours` comme le meilleur ratio du depot. Oui pour la
conclusion, non pour le motif, et le motif change la suite.**

**Le vivier reel, recompte sur les paquets servis :**

| Ligne | Items deja servis | Items a ajouter pour atteindre 10 |
|---|---|---|
| `HG-CM1-01` | 2 | 8 |
| `HG-CM1-02`, `-03`, `-04`, `-08`, `-12` | 1 chacune | 9 chacune |
| `LVE-CM1-07` | 5 | 5 |
| **Sept lignes** | **12** | **58** |

**L'arithmetique, faite** : 58 items pour 7 lignes, soit **une ligne pour 8,3 items**. Un lot neuf
donne une ligne pour 11 items (22 items, 2 lignes). **L'economie est de 24 pour cent sur les items,
pas un facteur.** Ce n'est pas ce que la formule *"2 pour 22"* laisse esperer, et je le corrige.

**Ce qui fait vraiment la valeur de ce vivier, et qui est plus gros que les 24 pour cent :**

1. **Aucun risque de refus au premier passage sur une condition.** `HIS-1`, `HIS-2`, `HIS-3` ont
   rencontre du contenu reel a s27, s28 et s29 et ont tenu ; `ANG-4`, `BIA-1`, `BIA-2` a s28. Au
   regard de `PAR-11` amendee, cela vaut **1,0 de marge non due**, soit plus que l'economie d'items.
2. **Aucune surface de couture.** Les items entrent dans **deux paquets existants**. Aucune entree de
   catalogue, aucune consigne, aucun gabarit, aucun fichier partage a ecrire. A trois instances, la
   couture est mon premier mode de defaillance ; **c'est le seul terrain du depot qui n'en ajoute
   pas un octet.**
3. **`HG-R8` et le reliquat de `CONS-1` voyagent gratuitement** dans le meme rejeu de paquet.

**Et la ligne la moins chere du depot n'est pas dans ce vivier** : c'est **`EMC-CP-07`**, refusee sur
`FAB-1c` seul - cinq bonnes reponses sur cinq commencant par les memes deux mots sur la tache B.
**Elle ne demande aucun item neuf, seulement la reecriture de cinq propositions. 0,5 pour une
ligne.** C'est le meilleur rapport du depot, et c'est une dette deja routee (`S32-EMCCP07`).

**Le reste, pour memoire, avec son tarif :**

| Terrain | Lignes ouvrables | Tarif du lot | Conditions eprouvees ? |
|---|---|---|---|
| Sciences et technologie CM1 | 9 | 1,5 | **oui**, quatre lignes fermees |
| EMC CM1 | 9 | 1,5 | **oui**, deux lignes fermees a s29 |
| EMC CP (`04`, `08`, `09`, `10`, `13`, `14`) | 6 | 1,5 | **oui**, cinq lignes fermees |
| **EPS, deux niveaux** | 6 | 1,0 (regle) / 1,5 (corps) | **non**, aucune |
| Anglais CM1 (`05`, `06`, `07`) | 3 | 1,5 | oui, sauf `ANG-S1`/`ANG-S2` en saisie |
| Arts plastiques CP | 2 | 1,0 | **non** au-dela d'`ART-1` |
| Education musicale CP | 2 | 1,0 | **non** |
| Histoire des arts CM1 (une seule sans image) | 1 | 1,5 | **non** |

**Rappels d'arithmetique de fermeture, sans degre de liberte** : `FER-2` dix items par ligne ;
`ADR-0081` clause 5 un item sur une ligne seulement ; `DEC-0064` onze items au moins dans un paquet
de fermeture a dix manches. **Un lot de 22 items ferme deux lignes au plus.**

### 7.3 Le poste que personne n'a chiffre - ce qu'un lot neuf laisse derriere lui

**Le fait, mesure sur les deux derniers sprints** : un lot porte deux paquets neufs, et **chaque
paquet neuf laisse des reserves non bloquantes qui coutent 0,5 a solder plus tard**, quel que soit
leur nombre (`PAR-10`). s30 : 9 reserves sur quatre paquets. s31 : 13 reserves sur les paquets
neufs, dont 9 sur les trois seuls paquets d'EMC CP qui ont ferme.

> **Chaque lot neuf ouvre 1,0 de dette differee. Chaque ligne ouverte par un lot neuf en porte 0,5.**

**Ce chiffre n'apparait dans aucune composition de sprint de ce depot, et c'est lui qui explique la
suite de ce document.**

---

## 8. Le plan sur trois sprints - deux plans, chiffres, et celui que je recommande

**Je ne priorise pas et je n'ouvre aucun perimetre.** Les deux plans tiennent dans 12,0 par sprint,
`camille-po` tranche.

### 8.1 Plan 1 - couverture maximale

**s32**

| | Postes | Total | Lignes | Paquets neufs |
|---|---|---|---|---|
| **A** | Remplissage `histoire-geographie-cm1`, tranche 1 : `HG-CM1-01`, `-02`, `-03`, `-04` (35 items) **2,5** ; `LVE-CM1-07` (5 items) **0,5** ; passe transverse **0,5** ; marge `PAR-11` **0,5**. `HG-R8` et le reliquat de `CONS-1` voyagent gratuitement | **4,0** | **+5** | **0** |
| **B** | `D1` **1,0** ; `D2` **0,5** ; `D5` **1,5** ; marge (cinq paquets rejoues, aucun lot neuf) **0,5** ; report de marge **0,5** | **4,0** | **+1** | **0** |
| **C** | Lot EPS CP regles (`EPS-CP-04`, `-08`) **1,0** ; lot EPS CP corps (`EPS-CP-10`, `-11`) **1,5** ; marge `PAR-11` : deux lots neufs **1,0** + clause conditions inedites **0,5** | **4,0** | **+4** | **4** |
| | | **12,0** | **+10** | **4** |

**s33** (borne 12,0 si la retenue n'est pas levee ; 15,0 si s32 clot sans couture)

| | Postes | Total | Lignes | Paquets neufs |
|---|---|---|---|---|
| **A** | Remplissage `histoire-geographie-cm1`, tranche 2 : `HG-CM1-08`, `-12` (18 items) **1,5** ; `D4` **1,0** ; `D7` **0,5** ; instruction des dix lignes sans annotation **0,5** ; passe transverse **0,5** | **4,0** | **+2** | **0** |
| **B** | Deux lots sciences CM1 **1,5** + **1,5** ; marge **1,0** | **4,0** | **+4** | **4** |
| **C** | Lot EPS CM1 (`EPS-CM1-09`, `-10`) **1,5** ; lot arts plastiques CP **1,0** ; marge : deux lots **1,0** + conditions inedites **0,5** | **4,0** | **+4** | **4** |
| | | **12,0** | **+10** | **8** |

**s34**

| | Postes | Total | Lignes | Paquets neufs |
|---|---|---|---|---|
| **A** | Lot EMC CM1 **1,5** ; `FOP-R4` + `DRO-R4` **1,0** ; `POS-R3` **0,5** ; passe transverse **0,5** ; marge **0,5** | **4,0** | **+2** | **2** |
| **B** | Lot sciences CM1, dont la ligne de collision sous la frontiere ecrite **1,5** ; `SIG-R1`/`R2` **0,5** ; `CHA-R1` **0,5** ; marge **1,5** | **4,0** | **+2** | **2** |
| **C** | Lot education musicale CP **1,0** ; histoire des arts CM1, la seule ligne sans image **1,0** ; marge : deux lots **1,0** + conditions inedites **0,5** ; report **0,5** | **4,0** | **+3** | **4** |
| | | **12,0** | **+7** | **8** |

> **Plan 1 - plafond arithmetique sur trois sprints : 27 lignes. 63 + 27 = 90 / 138 = 65,2 %.**

**Ce que le Plan 1 fait a la dette, et c'est le chiffre a lire :**

| | Dette payee | Dette differee creee (0,5 par paquet neuf) | Solde du sprint |
|---|---|---|---|
| s32 | 3,0 | 2,0 | **-1,0** |
| s33 | 2,0 | 4,0 | **+2,0** |
| s34 | 4,0 | 4,0 | **0,0** |
| **Trois sprints** | **9,0** | **10,0** | **+1,0** |

> **Le Plan 1 solde la totalite du registre actuel de 7,5, et clot s34 sur un registre de 10,0.**
> **Trois sprints a capacite maximale laissent la dette plus grosse qu'ils ne l'ont trouvee.**

### 8.2 Plan 2 - couverture soutenable, celui que je recommande

**Une seule difference, et elle est structurelle** : **les reserves non bloquantes d'un lot se
soldent dans le sprint qui l'a ouvert**, a 0,5 par paquet neuf, dans la borne.

| Sprint | A | B | C | Plafond |
|---|---|---|---|---|
| **s32** | identique au Plan 1 : **4,0**, **+5** | identique : **4,0**, **+1** | 1 lot EPS CP regles **1,0** ; reserves du sprint **1,0** ; marge **1,5** ; report **0,5** = **4,0**, **+2** | **+8** |
| **s33** | tranche 2 **1,5** ; `D4` **1,0** ; `D7` **0,5** ; annotation **0,5** ; passe **0,5** = **4,0**, **+2** | 1 lot sciences **1,5** ; reserves **1,0** ; `SIG` **0,5** ; `CHA` **0,5** ; marge **0,5** = **4,0**, **+2** | 1 lot EPS CP corps **1,5** ; reserves **1,0** ; marge **1,0** ; report **0,5** = **4,0**, **+2** | **+6** |
| **s34** | lot EMC CM1 **1,5** ; reserves **1,0** ; `FOP`+`DRO` **1,0** ; marge **0,5** = **4,0**, **+2** | 1 lot sciences **1,5** ; reserves **1,0** ; `POS-R3` **0,5** ; marge **1,0** = **4,0**, **+2** | lot EPS CM1 **1,5** ; reserves **1,0** ; marge **1,0** ; report **0,5** = **4,0**, **+2** | **+6** |

> **Plan 2 - plafond arithmetique sur trois sprints : 20 lignes. 63 + 20 = 83 / 138 = 60,1 %.**
> **Registre de dette a la cloture de s34 : environ 0,5, plus `CONS-3`.** Le registre est solde.

### 8.3 Le choix, chiffre des deux cotes

| | Plan 1 | Plan 2 |
|---|---|---|
| Plafond de lignes sur trois sprints | **27** | **20** |
| Registre de dette a la cloture de s34 | **10,0** + `CONS-3` | **~0,5** + `CONS-3` |
| Lignes de l'EPS fermees | **6** | **4** (les deux dernieres tombent a s35) |

**Le Plan 1 achete 7 lignes de plafond contre 9,5 de dette. Ma recommandation est le Plan 2**, et
mes deux motifs sont mesures et non prudentiels :

1. **Une reserve differee ne reste pas ou elle est.** `REG-R1` et `PER-R1` sont a leur quatrieme
   sprint. `CAT-1` **a change de nature entre deux sprints** - `camille-po` l'ecrit en toutes lettres
   a la cloture de s31 : d'un defaut sur deux lignes fermees, elle est devenue un motif structurel
   qui a fait echouer une ligne neuve entiere. Et **le residu du groupe 2a coute deux fois ce que je
   l'avais chiffre** (section 1.1) : la dette que ce depot croit connaitre est plus grosse que son
   registre.
2. **Le Plan 1 est le pari de s29 refait a une echelle triple.** A s29, doubler la borne n'a pas
   ferme une ligne de plus qu'un sprint en serie ; ce qu'il a achete etait des dettes payees. Le
   Plan 1 inverse ce marche : il achete des lignes en laissant la dette croitre, et **son solde a
   trois sprints est plus mauvais que le point de depart.**

---

## 9. Le fait de s29, affronte - a quelle condition un triplement achete de la couverture

**Ce que j'ai ecrit moi-meme en tete de ma section 13.6, le 2026-09-05, avant que ce ne soit
decouvert en review** : *"doubler ma borne n'achete pas de couverture. Il achete des dettes payees
et une marge."* La variante A a 8,0 plafonnait a 9 lignes ; la composition 2 a 5,0 en serie
plafonnait deja a 9.

**Ce plan ne le contourne pas. Voici la mesure, et elle est defavorable :**

| Sprint | Borne | Plafond de lignes | Cout par ligne de plafond |
|---|---|---|---|
| s31, deux instances, realise | 8,0 | 8 | **1,00** |
| s32, trois instances, Plan 1 | 12,0 | 10 | **1,20** |
| s32, trois instances, Plan 2 | 12,0 | 8 | **1,50** |

> **La ligne marginale coute plus cher a trois instances qu'a deux.** 50 pour cent de capacite en
> plus achetent 25 pour cent de plafond en plus, dans le meilleur des deux plans.

**A quelle condition un triplement achete quand meme de la couverture, et c'est une condition
precise :** **il faut qu'une instance au moins travaille sur un terrain qui ne cree aucun paquet
neuf.** C'est la seule facon d'echapper au 0,5 par ligne de la section 7.3, et c'est ce qui distingue
s32 de s29.

**Ce terrain existe aujourd'hui et il n'existait pas a s29.** Il compte exactement **huit lignes** :

- les **sept lignes `en cours`**, qui se remplissent dans deux paquets existants ;
- **`EMC-CP-07`**, qui se ferme par la reecriture de cinq propositions.

> **Un triplement achete de la couverture en s32, et en partie en s33. Il cesse d'en acheter en
> s34.** Les huit lignes qui ferment sans creer un paquet neuf sont epuisees a la fin de s33 ; a
> partir de la, chaque ligne porte ses 0,5 de dette differee, et l'on est revenu au marche de s29 -
> a capacite triple, donc a dette triple.

**Ce qu'un triplement n'achetera pas, et aucune capacite d'audit ne l'achete :**

- **les 10 lignes du palier 2.** `Designation`, `DesignationSession`, `DesignationVerdict`, `Zone`,
  `ZoneADesigner`, `CadreDeZone` sont dans `domain`, `DesignationContentSourceFactory` est dans
  `adapter-content`, et **rien n'existe dans `application`, `adapter-web` ni `bootstrap`**. `ADR-0080`
  est toujours *Propose*. **J'ai compte le catalogue servi ce matin : 66 entrees, aucune de mecanique
  `designation`. Personne ne peut jouer le palier 2 aujourd'hui, et cela dure depuis quatre
  sprints.**
- **les 7 lignes bloquees sur une reproduction d'oeuvre.** `squad/corpus/oeuvres-domaine-public.yaml`
  porte six entrees et le garde-fou de double preuve existe, mais le repertoire de reproductions
  servies **n'existe pas** sous les ressources statiques de l'adapter web. **Zero reproduction
  servie.** Et la question de droits de `DEC-0075` est ouverte.
- **un score qui veut dire quelque chose** (section 2).

> **Le poste de plus grand rendement de tout ce document n'est pas un lot de contenu : c'est
> l'achevement du palier 2, qui debloque 10 lignes - plus que ce que l'EPS entier rapporte sur trois
> sprints. Je ne peux pas le chiffrer : ce n'est pas de la capacite d'audit, et je ne decide pas de
> l'architecture.** Je nomme le symptome et je laisse `theo-archi` traiter la cause.

---

## 10. La dette - ce qui se paie dans les trois sprints, et ce qui ne se paiera pas

| Poste | Plan 1 | Plan 2 | Motif |
|---|---|---|---|
| `D1` `CAT-1` + `REG-R1` + `PER-R1` | **s32** | **s32** | Quatrieme sprint de report. `camille-po` demande a `sofia-sm` de la traiter comme une priorite nommee (`DEC-0082`, section 3) ; un cinquieme changerait la nature du constat. **Je ne differe pas cette dette une quatrieme fois et je nomme le motif : `CAT-1` porte sur les deux seules lignes d'EMC CP qui servaient de modele aux paquets neufs de s31** |
| `D2` `EMC-CP-07` | **s32** | **s32** | Meilleur rapport du depot : 0,5 pour une ligne, aucun item neuf |
| `D5` reserves neuves de s31 | **s32** | **s32** | Elles sont sur les trois paquets qui viennent de fermer trois lignes. Les laisser passer un sprint, c'est reproduire `CAT-1` |
| `D4` groupe 2b | **s33** | **s33** | Aucun apprentissage faux ; le reliquat de `CONS-1` tombe cependant sur trois des six lignes que A ferme (section 1.2), donc il ne peut pas aller au-dela de s33 |
| `D7` `SCO-1`, ma verification | **s33** | **s33** | Portee depot. Le remede est de `theo-archi` |
| `D3` residu du groupe 2a | **s34**, en trois morceaux suivant `PAR-2` | **s33 et s34** | Rien de faux n'est enseigne ; **mais la matrice declare couvertes des lignes que ces paquets ne servent pas sur ces items-la** |
| **`D6` `CONS-3`** | **ne se paiera pas** | **ne se paiera pas** | **Je ne la chiffre pas et je ne la planifie pas : je n'ai pas son cout.** Je le dis comme manquant plutot que de l'estimer |
| **`SCO-1`, le remede** | **hors de mon ressort** | **hors de mon ressort** | Je ne peux ni le chiffrer ni le planifier |
| **Les 7 lignes d'oeuvres** | **ne se paieront pas** | **ne se paieront pas** | Le repertoire de reproductions n'existe pas et `DEC-0075` est ouverte. Aucun de mes postes ne les debloque |
| **Les 10 lignes du palier 2** | **ne se paieront pas dans ce plan** | idem | Trois couches manquantes et un `ADR` *Propose* |

---

## 11. Les dix lignes sans annotation de jouabilite - a instruire, et dans ces trois sprints

**On me demande si elles attendent. Non, et le motif est mon propre refus de s31.**

Les dix : **mathematiques CP 2** (*denombrer, lire, ecrire et decomposer les nombres jusqu'a 100* ;
*se reperer et decrire des deplacements sur un quadrillage simple*), **francais 2** (*decoder un mot
regulier inconnu par assemblage syllabique*, CP ; *lire silencieusement un texte de longueur
croissante et en restituer l'essentiel*, CM1), **questionner le monde CP 6** (hygiene de vie ;
matiere d'un objet ; objets numeriques du quotidien ; vocabulaire de position ; mois et saisons ;
modes de vie autrefois et aujourd'hui).

**Le motif est celui que j'ai ecrit le 2026-09-07 en refus numero 1 et qui vient d'etre paye pour
`EMC-CP-04`** : *"un developpeur composant depuis la matrice, ou la ligne est `non couvert` sans
annotation ; l'annoter, ou vingt items partiront dedans."* Ces dix lignes sont dans exactement cet
etat, et **deux d'entre elles sont dans les deux matieres les plus fournies du depot**, donc les plus
susceptibles d'etre prises par defaut.

**Et l'une d'elles est deja dans une collision que ce plan cree** : *"regles d'hygiene de vie
(sommeil, alimentation, hygiene du corps)"* est du meme objet que `EPS-CP-10`, `EPS-CP-11`,
`EPS-CM1-10` et la ligne de sciences CM1 (section 6.4). **Instruire cette ligne n'est pas une
formalite : c'est la moitie CP de la frontiere que je viens d'ecrire.**

> **Prix : 0,5, dans la borne, a s33, sur l'instance d'accumulation.** C'est le poste le moins cher
> de ce document et **le plus susceptible d'eviter un lot perdu.**

---

## 12. Mon attente raisonnable, sprint par sprint

**Les deux taux de refus au premier passage que j'ai mesures, et ce que j'en fais.**

| Population mesuree | Taux | Ce qu'elle a en commun |
|---|---|---|
| EMC, s29 | **4 paquets refuses sur 5** | conditions inedites, aucune accumulation |
| Sciences, s31 | **2 paquets sur 4** | conditions inedites au premier passage de s30 |

**Je les applique, mais pas partout, et le critere est ecrit** : ces deux taux portent tous les deux
sur un terrain a conditions inedites. **Je ne les applique pas a un terrain dont mes conditions ont
deja mordu du contenu reel et tenu.** Le contre-exemple est mesure : le second passage
d'histoire-geographie a s28 a ferme **deux lignes sur deux, sans un refus**, sur des conditions
(`HIS-1`, `HIS-2`, `HIS-3`) qui avaient deja rencontre du contenu a s27.

**Attente, Plan 2 - celui que je recommande :**

| Sprint | Plafond | Attente | Ou elle descend, et pourquoi |
|---|---|---|---|
| **s32** | 8 | **6 a 7** | A : **5 sur 5**, conditions eprouvees trois sprints, aucun paquet neuf. B : **1 sur 1** sur `EMC-CP-07`, motif unique et ecrit mot pour mot, mais **c'est un troisieme passage sur `FAB-1c`, la propriete que je ne tiens que partiellement**. C : **0 a 1 sur 2**, profil `PAR-13` |
| **s33** | 6 | **4 a 5** | A : 2 sur 2. B : 1 a 2 sur 2. C : **1 sur 2**, l'EPS a un sprint d'accumulation mais `EPS-2` et `EPS-3` n'auront mordu qu'une fois |
| **s34** | 6 | **4 a 5** | A : 1 a 2 sur 2, l'EMC CM1 est la matiere au pire taux mesure. B : 2 sur 2. C : 1 a 2 sur 2 |
| **Trois sprints** | **20** | **14 a 17** | **77 a 80 / 138, soit 55,8 % a 58,0 %** |

**Attente, Plan 1 :** **19 a 23 lignes**, soit **82 a 86 / 138**, soit **59,4 % a 62,3 %**.

**Le fait qui doit accompagner ce chiffre, et il est genant** : le Plan 1 attend **6,3 a 7,7 lignes
par sprint**. Le debit mesure des quatre derniers sprints a **deux** instances est de **8, 9, 4 et 7**
lignes. **Le Plan 1, a trois instances et 12,0, attend le meme debit par sprint que s31 a deux
instances et 8,0.** Ce n'est pas une erreur de mon estimation : c'est ce que la section 9 mesure, et
c'est le fait de s29 qui se represente.

**Ma calibration passee, verifiee avant de m'en servir** : a s29 j'attendais 7 a 8 sur un plafond de
9 et le resultat a ete 9 ; a s30 j'attendais 3 a 4 sur un plafond de 4 et le resultat a ete 4, donc
**dans ma fourchette et non au-dessus** - j'avais ecrit a s31 m'etre trompee *"deux fois de suite"*
et je l'ai corrige moi-meme a la cloture ; a s31 j'attendais 5 a 7 sur un plafond de 8 et le resultat
a ete 7. **Le resultat a atteint le haut de ma fourchette deux fois sur trois.** Je ne remonte pas
pour autant, et le motif est arithmetique et non prudentiel : **le plafond de s32 repose pour deux
lignes sur trois sur un terrain a conditions inedites (`PAR-13`) ou j'ai un seul chiffre, 4 sur 5.**

---

## 13. Ce que je refuse d'avance, et ou cela cassera d'abord

**Chaque refus porte l'endroit ou il se realisera en premier**, pour qu'il soit evitable plutot que
constate.

| # | Ce que je refuse | Ou cela cassera d'abord |
|---|---|---|
| 1 | **Toute ligne d'EPS, de sciences CM1 ou de questionner le monde CP sur l'activite physique et le corps qui fermerait sans que la frontiere de la section 6.4 soit ecrite dans la specification de son lot, avant le premier item** | Sur `EPS-CP-11` et la ligne de sciences CM1 des que deux instances ecriront *hydratation* le meme jour. **C'est le seul defaut de ce plan que je vois d'avance et qu'aucune des trois instances ne peut voir depuis sa part** |
| 2 | **Tout item d'EPS qui compare des corps, des performances ou des capacites entre enfants, ou qui presente une performance chiffree comme une norme** (`EPS-2`) | Sur `EPS-CP-10` (*"effets de l'effort"*), qui est la pente naturelle de la ligne : *"au bout de combien de minutes est-on essouffle ?"* n'a pas de reponse et n'en aura jamais |
| 3 | **Tout item d'EPS dont la bonne reponse est une attitude, une emotion ou une conduite morale** (`EPS-3`) | Sur `EPS-CM1-09` (roles d'arbitre), ou *"que fais-tu si un joueur triche ?"* est l'item le plus evident de la ligne et le plus faux |
| 4 | **Tout item d'EPS portant sur la regle d'un jeu dont la regle n'est ni rappelee dans l'enonce ni universellement scolaire** (`EPS-1`) | Sur `EPS-CP-08` : un item sur le hors-jeu au football exclut tout enfant qui n'y joue pas, et ne mesure pas la competence |
| 5 | **Toute fermeture d'une ligne d'EPS sans sa mention de couverture partielle**, reprise mot pour mot de la matrice : *"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste et aucune performance ne sont servis par ce produit"* | A la cloture. La mention est due aux deux niveaux et je refuse la ligne sans elle |
| 6 | **Toute ligne d'histoire-geographie `en cours` fermee sans que la consigne du jeu nomme la tache de ses items** (`CONS-1`) | Sur `HG-CM1-03`, `-04` et `-08`, **mesure ce matin en jouant** : leurs items demandent de nommer un peuple, des peuples et un regime, et la consigne servie ne nomme aucune de ces taches. Trois lignes sur six |
| 7 | **Tout item ajoute a `histoire-geographie-cm1` qui porterait la mediane de charge de lecture du paquet au-dela de ce qu'elle est aujourd'hui** (`HG-R4`) | Des le remplissage : l'item de `HG-CM1-04` que j'ai joue fait **33 mots**, et il est deja le plus long de sa ligne. Cinquante-trois items neufs sur ce paquet peuvent en faire un paquet illisible sans qu'aucune ligne ne rougisse |
| 8 | **Tout item de `LVE-CM1-07` qui servirait une competence linguistique**, et **toute fermeture de cette ligne sans sa mention** : *"decouverte culturelle seule, en francais ; aucune competence linguistique n'est servie"* | Sur les cinq items neufs. Les cinq existants tiennent - je les ai joues ce matin, quatre d'entre eux dans une seule partie |
| 9 | **Tout item d'arts plastiques CP dont la reponse se porte par une couleur percue** (`ART-1`) | Sur la ligne des couleurs primaires, qui est **la** ligne ou `ART-1` est le plus tentant a violer. On demande le nom du melange, jamais de reconnaitre une teinte |
| 10 | **Tout item d'histoire des arts qui decrirait l'oeuvre dans son enonce** (`ART-4`, `CON-1`) | Sur la seule ligne de la matiere sans image requise. Un enonce qui decrit l'oeuvre repond a sa propre question |
| 11 | **Tout item de sciences CM1 calculant une vitesse** compte sur la ligne du mouvement | Elle compte sur les mathematiques CM1 (`ADR-0081` clause 5), et le lot croira avoir dix items quand il en aura sept. **Refus reconduit de s31, inchange** |
| 12 | **Tout Done pedagogique sur un lot que je n'ai pas joue**, et tout reaudit compte comme gratuit hors de la marge | A la cloture, si un sprint deborde. Un reaudit se paie ; s'il n'y a plus de budget, la ligne ne ferme pas - elle ne ferme pas *quand meme*. **Refus reconduit de s31** |
| 13 | **Tout dispositif qui verifierait ce qu'un adulte a dit a l'enfant** | Refuse d'avance depuis le 2026-09-01, motif ecrit, et l'EPS le rendra tentant : *"demande a un adulte de te montrer l'echauffement"* n'est pas un item |
| 14 | **Tout enonce de CP de plus de 15 mots, ou de plus de deux phrases** (`LON-1`, `LON-2`, section 16, ajoutee le 2026-09-08) | Sur les quatre lots de CP de ce plan - deux d'EPS CP a s32 et s33, un d'arts plastiques CP a s33, un d'education musicale CP a s34 - et sur la reprise de `POL-R1`, ou cinq enonces sur cinq sont au-dela |
| 15 | **Tout lot de designation audite avant que `DesignationVerdict` ne porte l'enonce de l'item joue** (section 17.6, ajoutee le 2026-09-08) | A la fin de la premiere partie de designation jouee : le dessin a disparu de l'ecran et l'enfant lit une reponse sans sa question ni son terrain |
| 16 | **Tout ecran de fin de partie de designation qui montrerait la zone attendue**, par son identifiant ou par son libelle (section 17.6) | Sur `QLM-CP-12`, ou le libelle est positionnel par construction : *"la bonne reponse : le pays en bas a droite"* n'apprend rien de la France |

**Les quatre proprietes de fabrication de s31 - `PLA-1`, `FAB-1a`, `FAB-1b`, `FAB-1c` - et `BIN-1`,
`CAT-1`, `CYC-1` restent opposables telles quelles.** Aucune n'est amendee ici. **`FAB-1c` mordra
d'abord sur l'EPS** : *"il faut boire de l'eau"* et *"il faut s'echauffer"* sont deux amorces qui
signeront la bonne reponse sur tout un paquet, et c'est exactement le defaut qui a fait echouer
`EMC-CP-07` deux fois.

---

## 14. Ce dont je reste la seule garde, et ce qui devient mordable

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Qui la tient | Automatisable ? |
|---|---|---|
| **La frontiere EPS / sciences / questionner le monde sur le corps et l'effort** (section 6.4) | **moi seule** | **non.** Un garde-fou lit la ligne qu'un item **declare**, jamais la competence qu'il sert reellement. C'est la propriete neuve la plus dangereuse de ce plan |
| **La couture entre deux moities paralleles** | **moi seule**. Trois occurrences en trois sprints, aucune attrapee autrement que par un comptage ou une relecture | **non**, sauf la forme (b) : voir section 3, un garde-fou d'agregat sur l'arbre assemble me la retirerait des mains |
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule** | **non** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **moi seule** | **non** |
| **`EPS-2`, `EPS-3`** - aucune comparaison entre enfants, aucune conduite portee par une bonne reponse | **moi seule** | **non** |
| **`EPS-1`** - la regle d'un jeu est rappelee ou universellement scolaire | **moi seule** | **non** |
| **`ART-1`** sur les couleurs primaires | **moi seule** sur le fond | **non** ; sa forme visuelle est d'`ines-design`, qui propose sans trancher |
| **`HIS-2`, `EMC-5`** - aucune situation a deux reponses defendables | **moi seule** | non |
| **`HG-R4`** - la charge de lecture d'un paquet et sa mediane | **moi**, par comptage | **oui**, et 53 items neufs sur un paquet de 57 est exactement le moment de le mordre |
| **`FAB-1a`, `FAB-1b`, `BIN-1`** | `theo-archi` peut les mordre, **routees le 2026-09-07** | **oui** |
| **`FAB-1c`** | **moi**, partiellement | **partiellement** : l'amorce commune et la longueur se comptent ; le registre, non. **Et c'est le seul motif qui reste sur `EMC-CP-07`** |
| **`SCO-1` a `SCO-7`** | personne aujourd'hui | **oui, entierement.** Le backlog l'ecrit : *"Ce qui n'est pas testable par un automate : rien"* |
| **`CONS-1`** - la consigne nomme les taches du paquet qu'elle introduit | **moi**, en jouant | **partiellement** : qu'un item declare sa tache et que la consigne les nomme toutes se compte, si le paquet declare la tache de chaque item - ce qu'il ne fait toujours pas |

**Deux proprietes de cette table sont neuves et arrivent avec ce plan** : la frontiere de la
section 6.4, et les trois conditions d'EPS. **Cinq sont mordables aujourd'hui** (`FAB-1a`, `FAB-1b`,
`BIN-1`, `HG-R4`, `SCO-1` a `SCO-7`), et `SCO-1` est celle qui rendrait le plus : **elle porte sur
64 des 66 entrees du catalogue et sur les 63 lignes fermees.** Je dis la propriete, jamais la forme
du test.

---

## 15. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique.** Aucun increment de s32 n'existe.
- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`.** J'ai repris ses chiffres tels
  qu'ils m'ont ete transmis - 63 `couvert-valide`, 7 `en cours`, 68 `non couvert`, 71 `non couvrable
  en l'etat`, 209 au total, denominateur atteignable 138, **63 / 138 = 45,7 %** - **et je ne les ai
  pas recalcules.** Le compte appartient a `camille-po`.
- **Il ne redige pas le `DEC`, ne priorise pas et n'ouvre aucun perimetre** (`camille-po`) ; **il ne
  gele rien et n'affecte personne** (`sofia-sm`) ; **il n'ecrit aucune story.**
- **Il ne decide pas de l'architecture** : le palier 2, le repertoire de reproductions et le remede
  de `SCO-1` sont des symptomes que je nomme, des causes que `theo-archi` traite.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu deux paquets, un catalogue et une consigne, et joue deux parties completes plus cinq manches de
  mesure ; je n'y ai rien ecrit.
- **Il ne rouvre aucune ligne `couvert-valide`**, y compris les 63 sur lesquelles je viens de mesurer
  que le score reste parfait par elimination. **La mention de la section 2 ne vaut que pour les
  fiches que j'ecrirai a partir de la cloture de s32.**
- **Il n'efface aucun verdict.** La levee du refus numero 1 de s31 (section 1.3) est **ajoutee et
  datee** ; le refus reste lisible ou il a ete ecrit.
- **Il ne durcit aucune clause anterieure.** `PAR-1` a `PAR-7`, `PAR-10`, `CYC-1`, `PLA-1`, `FAB-1`,
  `BIN-1`, `CAT-1`, `EPS-1` a `EPS-3` sont intactes. Ce qui est neuf est neuf et date du
  2026-09-08 : la decote par paire, la clause de transfert de `PAR-9`, l'amendement de `PAR-11`,
  `PAR-12`, `PAR-13`, la frontiere de la section 6.4 et la mention de score de la section 2.
- **Il ne remplace pas ma borne de deux instances.** Si le passage a trois instances n'est pas
  arbitre, **ma borne reste 8,0**, telle que je l'ai ecrite ce matin a la cloture de s31.

---

## 16. Amendement du 2026-09-08, apres coup - la barre chiffree de longueur d'enonce au CP

**Ce que ce document devait porter et ne portait pas.** Ma fiche de `emotions-autrui-cp` route,
le 2026-09-07, *"un plafond chiffre de longueur d'enonce au CP"* a **moi-meme, arbitrage prealable
de s32**, avec le motif : *"trois paquets d'EMC CP livres et aucune barre ecrite : c'est a moi de la
poser avant un sprint, pas pendant."* Ma fiche de `exclusion-cp` s'appuie dessus a `EXC-R4` : *"je
ne cree aucune barre chiffree, qui est due dans mon arbitrage prealable de s32."*

**Les sections 1 a 15 ne la portent pas. `camille-po` est venue la chercher, ne l'a pas trouvee, et
a refuse d'en inventer une - elle a eu raison, et l'oubli est le mien.** Je l'ecris ici plutot que
de la glisser dans une section anterieure : une barre datee se pose a sa date.

### 16.1 La convention de comptage, avant le chiffre

**Sans elle, la barre n'est pas verifiable, et mes propres chiffres de fiches le prouvent.**

> **Un mot est une suite de caracteres separee par des espaces et contenant au moins une lettre ou
> un chiffre. Une forme elidee compte pour un mot** (`qu'il`, `l'ami`, `Qu'est-ce`). **Une phrase
> est un segment termine par un point, un point d'interrogation ou un point d'exclamation.**

### 16.2 Quatre corrections a mes propres chiffres, avant de m'en servir

**J'ai recompte sous cette convention ce que j'avais moi-meme enregistre, et quatre chiffres sont
faux - tous d'une unite, tous vers le haut, parce que je comptais les formes elidees pour deux.**

| Ce que j'ai ecrit | Mesure sous la convention de 16.1 |
|---|---|
| *"les deux paquets d'EMC CP que j'ai fermes le 2026-09-05 plafonnaient a **15 et 14** mots"* (`EMO-R1`, `POL-R1`, repris trois fois) | `regles-de-vie` **14**, `permis-interdit` **13** |
| `EMO-R1` : item 4 a **18** mots, item 5 a **19** | **17** et **18** |
| `EXC-R4` : les deux plus longs de la tache B a **17 et 19** mots | **16** et **18** |
| `POL-R1` : item 11 a **26** mots, *"le plus long enonce de CP que ce depot ait jamais servi"* | **25** mots, **et ce n'est pas le plus long** : `cp-emc-regle-souhait-loi` en porte un a **30 mots**, et ce paquet **est servi** - il est au catalogue a l'entree `bandit-manchot-regle-souhait-loi-cp`, meme si sa ligne est refusee |

**Aucune de ces corrections ne change un verdict** : les reserves portaient sur le fait qu'un enonce
etait trop long, et il l'est. **Elles changent la barre**, parce qu'une barre posee sur des chiffres
d'une unite trop hauts serait d'une unite trop laxiste.

### 16.3 La population, mesuree et non estimee

**Methode** : tous les enonces des 33 paquets de CP servis, lus dans les fichiers de contenu de
l'adapter de contenu, comptes sous la convention de 16.1. **506 enonces, 606 phrases.**

| | Valeur |
|---|---|
| Mediane d'un enonce de CP | **6 mots** |
| 90e centile | **13 mots** |
| 95e centile | **15 mots** |
| Maximum du depot | **30 mots** |
| Enonces au-dela de 15 mots | **23 sur 506, soit 4,5 %** |
| Phrases au-dela de 15 mots | **7 sur 606, soit 1,2 %** |

**Et la mesure qui decide, parce qu'elle porte sur ce que j'ai deja accepte et non sur ce que je
decouvre :** des 33 paquets de CP, **quatre portent une reserve de longueur** ouverte par moi
(`regle-souhait-loi` refuse, `formules-de-politesse`, `emotions-autrui`, `exclusion`). **Sur les 29
autres, tous valides sans reserve de longueur :**

| | Valeur acceptee |
|---|---|
| Enonce le plus long | **15 mots** (`cp-qlm-vivant-non-vivant`, deux items, valide a s27) |
| Phrase la plus longue | **15 mots** (le meme) |
| Nombre de phrases par enonce | **2 au plus** (`regles-de-vie`, `problemes-additifs`, `permis-interdit`, `emotions-autrui`) |

### 16.4 La barre

> **`LON-1` - nouvelle, datee du 2026-09-08.** **Au CP, un enonce compte au plus 15 mots**, sous la
> convention de comptage de 16.1.
>
> **`LON-2` - nouvelle, datee du 2026-09-08.** **Au CP, un enonce compte au plus deux phrases**, et
> quand il en compte deux, **la seconde est la question**.

**Pourquoi 15 et pas 14.** 14 est la valeur que j'accepterais volontiers aujourd'hui, et c'est
exactement pour cela que je ne la pose pas : **les deux enonces a 15 mots de
`cp-qlm-vivant-non-vivant` sont sur une ligne que j'ai fermee sans reserve a s27.** Une barre a 14
la rendrait retroactivement fautive, et **une barre qu'on serre apres coup sur une ligne deja verte
fait de ma porte un caprice.** 15 est la plus haute longueur que j'aie jamais acceptee au CP ; c'est
donc la barre, et pas une de moins.

**Pourquoi 15 n'est pas une cible, et je le dis pour qu'on ne compose pas dessus.** Les deux seuls
enonces de CP a 15 mots que j'aie valides sont *"Parmi ces trois choses, laquelle est un etre
vivant : le chien, la chaise, le caillou ?"* - **six de ces quinze mots sont l'enumeration des trois
candidats**, pas une proposition subordonnee. **Hors forme enumerative, la plus longue longueur que
j'aie acceptee est 14 mots.** Un enonce de 15 mots qui porte deux circonstances et une relative
n'est pas au niveau, et il passera `LON-1` : voir 16.6.

### 16.5 Ce que la barre fait aux trois reserves qui l'attendaient

**Elle est mesurable item par item, elle ne demande aucun jugement, et elle tranche les trois.**

| Reserve | Items au-dela de `LON-1` ou `LON-2` | Ce qui reste a faire |
|---|---|---|
| **`EMO-R1`** | item 4 a **17** mots, item 5 a **18** | Deux enonces a ramener a 15 au plus. Les neuf autres du paquet sont conformes |
| **`EXC-R4`** | les deux plus longs a **16** et **18** mots | Deux enonces a ramener a 15 au plus |
| **`POL-R1`** | cinq enonces de la tache B a **20, 22, 23, 24** et **25** mots, dont un en **trois** phrases - `LON-2` mord aussi | Cinq enonces a reecrire, et c'est le plus gros des trois |

**Et un quatrieme cas qu'aucune reserve ne portait, trouve en mesurant** : les onze enonces de
`cp-emc-regle-souhait-loi` vont de **13 a 30 mots**, mediane **24**. Le plus long est **15 mots de
situation plus une question de 15 mots qui reenumere les trois propositions** - *"Est-ce une regle
qui vaut pour tous, un simple souhait, ou une loi ?"* - **alors que les trois propositions sont deja
sur les boutons.** `S32-EMCCP07` est deja au perimetre pour `FAB-1c` ; **`LON-1` mord sur le meme
paquet, dans le meme geste, et supprimer l'enumeration de la question retire d'un coup entre 9 et 11
mots sur chacun des onze items.** Je ne cree pas de reserve neuve : le paquet est refuse, sa ligne
n'est pas fermee, et cette mesure est un moyen de la fermer, pas un motif de plus.

### 16.6 Ce que cette barre ne fait pas, et ce dont je reste donc la seule garde

- **Elle est necessaire, jamais suffisante.** Un comptage de mots ne voit ni une subordonnee, ni un
  referent elide, ni deux circonstances posees avant la question - qui sont les motifs de fond de
  `EMO-R1` et de `EXC-R4`, et que j'ai ecrits en toutes lettres dans les deux fiches. **Un paquet
  conforme a `LON-1` et `LON-2` peut rester illisible au CP, et je le refuserai.**
- **Elle ne vaut que pour l'enonce**, pas pour les propositions, pas pour l'indice, pas pour la
  consigne. Je n'ai pas mesure ces trois populations et je ne pose aucune barre dessus - **je le dis
  comme manquant plutot que d'etendre une barre a un objet que je n'ai pas compte.**
- **Elle ne vaut que pour le CP.** Aucune barre de longueur n'existe pour le CM1, et `HG-R4` - la
  charge de lecture d'`histoire-geographie-cm1` - reste une reserve qualitative. Le remplissage de
  53 items sur ce paquet a s32 et s33 en fait le bon moment pour la mesurer ; **je ne la pose pas
  d'avance sur une population que je n'ai pas encore comptee.**
- **Elle est entierement automatisable**, et c'est le plus mecanique de tous les garde-fous que
  j'aie demandes : deux comptages sur un champ de contenu. Je dis la propriete ; **la forme du test
  appartient a `theo-archi`.** Tant qu'il n'existe pas, `LON-1` et `LON-2` sont tenues par moi, en
  comptant.
- **Elle ne rouvre aucune ligne `couvert-valide`.** `EMC-CP-01`, `EMC-CP-03` et `EMC-CP-05` restent
  fermees ; `LON-1` et `LON-2` ne valent que pour les enonces ecrits ou reecrits a partir du
  2026-09-08, et les trois reserves qui les attendaient etaient deja ouvertes et non bloquantes.

---

## 17. Amendement du 2026-09-08, apres coup - combien des lignes du palier 2 se ferment reellement

**Ce que `theo-archi` me route**, section 3 de son avis de faisabilite (branche `s32-theo`,
`dcbcd65a`) : *"Le palier 2 touche 10 lignes `non couvert` (11 avec le quadrillage). Combien il en
ferme n'est pas une question d'architecture [...] et je le route a `nadia-prof`."* Il route aussi les
deux trous de contrat de sa section 4.5 et le fond de la cellule du quadrillage.

**Ma section 9 nommait le palier 2 comme le poste de plus grand rendement du depot, en disant que je
ne pouvais pas le chiffrer. Je peux chiffrer la moitie qui est de moi - le nombre de lignes - et
c'est celle-la qui decide.** Le voici, et il est plus bas que ce que ma propre section 9 laissait
esperer.

### 17.1 Ce que j'ai verifie avant de repondre

**Les onze codes de ligne, retrouves un par un dans les referentiels et non deduits de la matrice**
(`grep` du code entre crochets dans `squad/programme/<matiere>/competences-<niveau>.md`) :
`MAT-CP-10`, `QLM-CP-11`, `QLM-CP-12`, `QLM-CP-14`, `HG-CM1-09`, `HG-CM1-10`, `ART-CM1-08`,
`HDA-CM1-06`, `HDA-CM1-09`, `SCI-CM1-09`, `SCI-CM1-20`. **Onze codes, onze lignes, aucune
ambiguite.**

**Je confirme le compte de `theo-archi` et l'ecart qu'il nomme.** Onze lignes de tableau citent le
palier 2, dont une `non couvrable en l'etat` (`EPS-CM1-04`, *suivre un itineraire*, dont la cellule
dit elle-meme de ne pas double-compter avec `HG-CM1-09`). Restent dix `non couvert` citant le palier
2, plus `MAT-CP-10` dont la cellule ne dit que *"non couvert"* - **invisible a tout recompte par
motif, et c'est la ligne 1 de ma propre instruction du 2026-09-05.**

**Et je confirme sa correction d'un chiffre qui est le mien** : j'avais ecrit que les dix *"portent
toutes le motif palier 2 requis"*. Quatre le portent. **Mon compte etait juste, mon motif etait
faux, et un compte dont le motif ne vaut que pour quatre de ses dix membres n'est pas
reproductible** - c'est exactement ce qui a laisse `MAT-CP-10` hors de la liste pendant cinq jours.

### 17.2 Ligne par ligne - ce qui ferme, ce qui ouvre sans fermer, ce qui n'est pas touche

**Etat de depart identique sur les onze : aucun item, aucun dessin.** *Ferme* signifie : `FER-1`,
`FER-2` et `FER-3` tenues, mecanique servie, contenu audite et valide par moi.

| Code | Ligne | Effet du palier 2 acheve **plus** son contenu | Mention de couverture partielle due |
|---|---|---|---|
| `QLM-CP-11` | Situer un lieu ou un objet sur un plan simple | **FERME.** Les deux enumerations sont disjonctives : un plan de classe et un type de cible suffisent. **Un seul dessin.** La moins chere des onze | aucune |
| `QLM-CP-12` | Situer la France et quelques espaces sur une carte ou un globe | **FERME.** Support disjonctif, une carte suffit ; la France en bonne zone plus trois autres espaces | **`VUE`** : les libelles de zone sont positionnels (`ZON-10`), l'ecran est navigable sans etre jouable sans voir (`ZON-6`) |
| `QLM-CP-14` | Situer des evenements sur une frise (avant, apres, en meme temps) | **FERME**, et la mention partielle actuelle **tombe**. Un paquet de designation sert les trois relations a lui seul ; `FER-2` compte toutes moities confondues. **Deux dessins au minimum** | **`VUE`**. La mention *"moitie anteriorite/posteriorite seule"* est retiree, pas deplacee |
| `MAT-CP-10` | Se reperer **et decrire** des deplacements sur un quadrillage | **OUVRE SANS FERMER.** *Decrire* un deplacement, c'est le produire ; l'enfant qui appuie sur une case n'a rien decrit. **Aucun item de designation ne servira jamais cette moitie** | **due** : *"moitie 'se reperer et se deplacer sur un quadrillage' seule ; la moitie 'decrire un deplacement' reste non couverte"*. Elle tombe le jour ou un **second paquet, textuel**, sert *decrire* |
| `HDA-CM1-09` | Se reperer dans un musee, et en respecter les regles | **OUVRE SANS FERMER.** Support disjonctif - un plan de musee suffit - mais le libelle est conjonctif : *se reperer* **et** *en respecter les regles*. La moitie des regles se sert en choix textuel, dans un **second paquet** | **due** tant que le second paquet n'existe pas. Mon refus du 2026-09-03 tient : le quiz *"que fait-on dans un musee ?"* n'est pas un substitut du reperage - il est legitime **sur la moitie qui est la sienne** |
| `HG-CM1-09` | Se reperer sur une carte de France **et** sur un planisphere | **OUVRE SANS FERMER a la taille d'image d'aujourd'hui.** Une zone doit atteindre 44 px de cible tactile, la hauteur rendue d'un dessin d'item est plafonnee a 176 px : **une zone occupe au moins un quart de la hauteur.** Sur un planisphere, l'Europe n'y arrive pas, une grande ville encore moins | **due**, et je l'ecrirai le moment venu. Elle tombe si l'image grandit - `ines-design`, deja route par `ADR-0080`. **Je ne tranche pas une taille d'image** |
| `HG-CM1-10` | Situer **son** lieu de vie a differentes echelles | **NON TOUCHEE, et le palier 2 n'est pas son obstacle.** La ligne demande a l'enfant ou **il** vit : `EMC-1`, `EMC-6` et `BIA-4` me font refuser tout item qui interroge l'enfant sur son foyer ou sa commune, et `DEC-0028` fait que le produit ne le sait pas et ne peut pas le savoir. Un exercice generique d'emboitement d'echelles serait une **autre** competence | sans objet : rien ne ferme |
| `ART-CM1-08` | Decrire une oeuvre (support, format, matiere, couleur, sujet, **composition**) | **NON TOUCHEE dans ces trois sprints.** Deux verrous, et le palier 2 n'est que le second : **aucune reproduction d'oeuvre n'est servie** (`DEC-0075`, section 9 de ce document). Le palier 2 acheve n'achete rien ici tant que le premier verrou tient | la mention pre-ecrite dans la matrice reste due si la ligne bouge un jour |
| `HDA-CM1-06` | Identifier quelques oeuvres et les situer sur une frise | **NON TOUCHEE, et pour trois motifs.** Les reproductions manquent ; et **`DES-7` interdit tout element de texte dans un dessin servi**, donc une frise d'histoire des arts ne peut porter ni date ni titre. **C'est ma propre condition qui mord ma propre ligne** : sans texte et sans reproduction, il ne reste rien a designer sur cette frise | la mention pre-ecrite reste due |
| `SCI-CM1-09` | Nommer les fonctions de nutrition et l'organe principal de chacune | **NON TOUCHEE, et la matrice le dit deja d'elle-meme** : *"designer un organe sur un schema serait du palier 2, **non exige**"*. **Cette ligne ferme en texte, aujourd'hui, sans palier 2.** Je confirme le *"non exige"* | aucune |
| `SCI-CM1-20` | Extraire une information d'un document (texte, tableau, **schema, graphique**) | **NON TOUCHEE au sens du compte.** La ligne ferme en texte sur sa part servie, avec sa mention. Le palier 2 l'**enrichirait** - schema, graphique - mais un item ne compte que sur une ligne (`ADR-0081`, clause 5) : **enrichir une ligne deja fermee rapporte zero ligne** | la mention pre-ecrite est due a la fermeture textuelle : *"document textuel et tableau seuls ; le schema et le graphique restent non couverts"* |

### 17.3 Le chiffre demande

> **Le palier 2 acheve, plus ses paquets de contenu, ferme 3 des onze lignes** : `QLM-CP-11`,
> `QLM-CP-12`, `QLM-CP-14`.
>
> **Il en ouvre 3 de plus sans les fermer** : `MAT-CP-10` et `HDA-CM1-09`, qui demandent chacune
> **un second paquet textuel** ; `HG-CM1-09`, qui demande **une taille d'image plus grande**.
>
> **Il n'en touche pas 5** : `HG-CM1-10`, `ART-CM1-08`, `HDA-CM1-06`, `SCI-CM1-09`, `SCI-CM1-20`.
>
> **3 + 3 + 5 = 11. Aucune n'est perdue.**

**Ce que le maximum atteignable vaut, si tout est finance** : 3 lignes par la designation seule, **5
avec les deux paquets textuels** de `MAT-CP-10` et `HDA-CM1-09`, **6 si l'image grandit**. **Jamais
10, jamais 11.**

**Et deux des cinq non touchees sont deja comptees ailleurs, ce qui est un double compte a ne pas
faire.** `SCI-CM1-09` et `SCI-CM1-20` sont annotees *jouables au choix textuel* et entrent donc dans
la reserve de lignes jouables des maintenant, celle que le Plan 2 de la section 8.2 exploite.
**Les compter aussi comme un gain du palier 2 les compterait deux fois.**

### 17.4 Mon prix d'audit d'un lot de designation - je le donne, il est deja pose

**On me demande si je peux le donner sans avoir vu un dessin. Oui : je l'ai pose le 2026-09-05, et
je ne deplace pas mes poteaux.**

> **Tarif d'un lot de designation : base + 1,0**, le supplement payant deux gestes d'audit qu'un lot
> textuel ne demande pas - la **passe image effacee** (`ZON-1` a `ZON-3` : tout le texte servi sous
> les yeux, la reponse doit rester introuvable) et la **passe jouee, zone par zone** (`ZON-9` : je
> presse chaque zone de chaque item pour voir ou tombe chaque cadre).
>
> | Ce que le lot sert | Prix |
> |---|---|
> | Lignes dont je suis la source - quadrillage, plan de classe, frise de la vie de l'enfant | **2,0** |
> | Lignes portant un fait a verifier contre une source - carte, planisphere, frise historique, musee | **2,5** |
>
> **Unite** : jusqu'a 20 items de contenu, plus un item par paquet au titre de la marge de manches,
> et jusqu'a **6 dessins**. Au-dela de l'un ou de l'autre, prorata.
>
> **Aucun supplement de premiere application** : *une condition inedite ne fait pas monter mon prix,
> elle fait baisser mon plafond de fermeture*. **Ce qui baisse a la place, et c'est une contrainte
> que je m'impose : pas plus d'un lot de designation dans le sprint ou la mecanique est servie pour
> la premiere fois.**

**Ce qu'un specimen de dessin reviserait, et je le nomme comme non mesure plutot que de le
provisionner maintenant.** Le supplement de 1,0 est forfaitaire par lot, alors que **deux de mes
gestes se comptent par dessin et non par item** - l'exactitude d'un plan ou d'une carte, et l'accord
des cadres avec le dessin. Je l'ai pose sans precedent : `ZON-9` est **le premier geste d'audit de ce
depot qui exige de jouer plutot que de lire.** **Sur un lot a un ou deux dessins je tiens le
forfait ; a six dessins je ne sais pas s'il tient, et c'est la seule chose que le specimen demande
par `theo-archi` me dirait.** Je ne gonfle pas le tarif d'avance : je nomme le point ou il pourrait
etre faux.

**Ce que je ne chiffre pas, et ce n'est pas une esquive** : le cout de **produire** un plan, une
carte ou une frise. Le depot sert 24 illustrations, toutes des figures geometriques ou des pieces de
monnaie ; un planisphere n'est ni l'un ni l'autre. **Ce cout n'est pas de la capacite d'audit et il
ne m'appartient pas.** Je confirme en revanche ce que le specimen doit porter pour que je puisse
juger : **quatre cadres de zone declares par le contenu, et le dessin sous le plafond de poids**.

### 17.5 Ce que ce chiffre dit du financement, contre les autres postes de ce document

**L'arithmetique, faite et non supposee.** Mon instruction du 2026-09-05 chiffre les six paquets a
**7,0 de ma borne**, en trois lots (2,0 / 2,5 / 2,5). Ces trois lots ferment **3 lignes** et en
ouvrent 3.

| Poste | Cout de ma borne par ligne fermee |
|---|---|
| `EMC-CP-07`, `FAB-1c` seul | **0,50** |
| Le vivier `en cours`, sept lignes | **0,58** |
| Un lot neuf de sciences ou d'EMC CM1 | **0,75** |
| Un lot neuf d'EPS | 0,50 a 0,75 |
| **Le palier 2, contenu compris** | **2,33** |

> **La ligne du palier 2 coute quatre fois la ligne la moins chere du depot, et trois fois la ligne
> moyenne** - et c'est avant les lots `L-a` a `L-e` de `theo-archi`, **qui cablent le chemin de jeu
> et ne ferment aucune ligne**, et avant le cout des dessins, que personne ne sait chiffrer.

**Et ma propre clause de s29 borne le debit, quel que soit le financement** : pas plus d'un lot de
designation dans le sprint de la premiere mise en service. Si `L-a` a `L-e` tombent a s32, le premier
lot de contenu est a s33 et le second a s34 : **au plus 4 lignes de designation sur s32-s34, dont 3
fermees et 1 ouverte**, pour 4,5 de ma borne plus les cinq lots de developpement.

> **Je corrige donc ma propre section 9.** J'y ai ecrit que *"le poste de plus grand rendement de
> tout ce document"* etait l'achevement du palier 2, sur la foi de 10 lignes debloquees. **Ce
> chiffre etait faux : il en ferme 3 dans ces trois sprints, et c'est le plus mauvais rendement de
> tous les postes que ce document chiffre.** L'erreur est la mienne et elle allait dans le sens du
> chantier le plus spectaculaire.

**Mon avis, et il ne tranche aucun perimetre - `camille-po` priorise, l'utilisatrice arbitre.**

- **Pour la couverture sur s32-s34 : je ne recommande pas de financer le palier 2.** Trois lignes au
  prix de six a huit ailleurs.
- **Ce qui plaide pour, et je le dis parce que ce n'est pas rien** : `QLM-CP-11`, `QLM-CP-12` et
  `QLM-CP-14` sont les **trois seules lignes du depot que rien d'autre n'ouvrira jamais**, sur une
  matiere - questionner le monde CP - dont mes conditions sont eprouvees. Elles ne se perimeront pas,
  mais elles ne s'ouvriront pas non plus tout seules.
- **Si l'utilisatrice finance quand meme, l'entree la moins chere et la plus sure est un seul lot :
  `QLM-CP-11` plus `QLM-CP-14`, base 1,0 tous les deux, prix 2,0, pour deux lignes fermees.** Un
  plan de classe et deux frises de la vie de l'enfant : **aucun fait a verifier contre une source,
  aucun probleme de taille d'image, aucun besoin de reproduction d'oeuvre, trois dessins.** C'est le
  seul endroit du palier 2 ou le rendement approche celui du reste du depot - **1,0 par ligne** -, et
  c'est aussi le lot ou mes huit conditions `ZON-*` inedites rencontreront un contenu reel au moindre
  risque.

### 17.6 Les deux trous de contrat - les deux sont bloquants pour moi, et le second a une reponse

**Trou A - `DesignationVerdict` ne porte pas d'enonce. Bloquant.**

Motif, et il est plus fort sur une designation que sur les deux autres mecaniques. `ADR-0084` et
`REV-3` existent parce que j'ai refuse un ecran de fin qui donne une reponse sans la question
qu'elle commente. **Sur une designation, la reponse est un endroit sur un dessin - et a la fin de la
partie, le dessin n'est plus a l'ecran.** L'enfant lirait donc une reponse sans la question **et**
sans le terrain. `Verdict` et `SaisieVerdict` portent tous deux un `enonce` obligatoire ; celui-la
doit le porter aussi.

> **Condition, et elle est verifiable** : `DesignationVerdict` transporte l'enonce de l'item joue, au
> meme titre que les deux autres verdicts. **Je ne valide aucun lot de designation avant.** C'est du
> `domain`, donc du ressort de `theo-archi` ; je dis la propriete, pas la forme.

**Trou B - `bonneZone` est un `Zone.id()` et `retour.html` le donne a lire a un enfant. Bloquant, et
voici ma reponse a la question qu'`ADR-0080` section 8 me laisse.**

**Ni l'identifiant, ni le libelle.** L'identifiant est une valeur de formulaire, et le montrer serait
une faute grossiere. **Mais le libelle n'est pas meilleur, et c'est le point que je suis seule a
pouvoir faire** : sur `QLM-CP-12` j'ai deja arbitre que les libelles de zone doivent etre
**positionnels** (`ZON-10`), parce qu'un garde-fou -
`AucunEnonceDeDesignationNeNommeLeLibelleDeSaBonneZoneTest` - interdit qu'un enonce nomme le libelle
de sa bonne zone, et il a raison de l'interdire. **Afficher *"La bonne reponse : le pays en bas a
droite"* a la fin d'une partie n'apprend rien de la France.**

> **Ma reponse : sur une designation, l'ecran de fin de partie porte l'enonce et ne montre pas la
> zone attendue - il ne montre rien a sa place.** Un endroit ne se dit pas en mots sans soit nommer
> ce que le garde-fou interdit, soit dire une position qui n'enseigne rien.
>
> **Et la revelation ne disparait pas pour autant : elle reste ou elle est utile, sur l'ecran de
> manche, ou le dessin est encore la et ou l'enfant voit *ou* c'etait** - ce que
> `DesignationSession.bonneZoneRevelee()` sert deja. `SCO-5` est intacte : une manche resolue par la
> revelation n'est jamais une reussite.

**Ce que cette reponse coute : rien de plus, et probablement moins.** `theo-archi` ecrit que deux
issues sont faisables et que faire transporter au verdict quelque chose de presentable est la moins
chere. **Ma reponse rend la moins chere suffisante** : le verdict transporte l'enonce (trou A) et
aucune zone presentable, et le fragment de fin omet le bloc de reponse pour une designation. **La
forme appartient a `theo-archi`.**

### 17.7 Le fond de la cellule du quadrillage, que `theo-archi` me route

**Oui, `MAT-CP-10` demande le palier 2, et pour une moitie seulement.** *Se reperer et se deplacer
sur un quadrillage* est une designation sur un dessin et rien d'autre ne la sert ; *decrire un
deplacement* n'est pas une designation et se sert en choix textuel, dans un second paquet.
**La ligne ne ferme donc pas par le palier 2 seul**, et la mention du 17.2 est due.

**Le cinquieme jour de cet ecart, et je le dis parce que je suis dans la chaine qui ne l'a pas
paye.** `ADR-0080` l'a signale le 2026-09-03 ; ma propre instruction du 2026-09-05 en fait sa ligne
1 sans que je regarde sa cellule ; **ma section 12 de ce document, ce matin, a instruit dix lignes
sans annotation et n'a pas vu que celle-ci en etait une onzieme.** La cellule appartient a
`camille-po`, le fond est ci-dessus, et **je l'ajoute a la liste de la section 11** : les dix lignes
sans annotation sont onze, et la onzieme est celle-la.
