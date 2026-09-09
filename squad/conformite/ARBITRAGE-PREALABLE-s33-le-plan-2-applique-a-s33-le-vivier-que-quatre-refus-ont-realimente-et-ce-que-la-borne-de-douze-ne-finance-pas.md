# Arbitrage prealable de s33 - le Plan 2 applique a s33, le vivier sans paquet neuf que quatre
# refus ont realimente, le prix des treize reserves neuves, et ce que la borne de douze ne finance pas

- **Auteur** : `nadia-prof`, professeure des ecoles, **seule autorite sur le Done pedagogique**.
- **Date** : 2026-09-09.
- **Arbre** : `round-two` a `aec78f3a`, s32 clos, arbre propre, quatre jobs verts, 1 439 tests,
  aligne sur `origin`. Jar reconstruit depuis mon worktree dedie, application lancee sur un port
  local, arretee **par son identifiant de processus** a la fin de mes parties.
- **Ce document est un arbitrage prealable.** Il ne prononce aucun Done pedagogique, ne rejuge aucun
  verdict de s32, n'ecrit pas la matrice, n'ouvre aucun perimetre, n'ecrit aucune story et n'affecte
  personne. Il dit ce qui fermerait, ce que cela coute, ce que je refuse d'avance, et ce dont je
  reste la seule garde.
- **Il ne redige pas le `DEC`.** `camille-po` le fera apres moi ; les chiffres de ce document sont a
  reprendre, pas a recalculer.
- **Le Plan 2 est acquis et n'est pas rouvert ici.** `DEC-0083` l'a retenu pour s32 a s34, contre un
  Plan 1 de couverture maximale que l'utilisatrice a vu et ecarte, et il tient pour s33. **Ce
  document est l'execution de cet arbitrage, pas sa reouverture** : il ne chiffre aucune composition
  alternative et n'en propose aucune. La meilleure couverture se cherche **a l'interieur** du
  Plan 2.
- **La regle du Plan 2 gouverne le chiffrage de ce document** : *les reserves non bloquantes d'un
  lot se soldent dans le sprint qui l'a ouvert.* Les **treize reserves neuves de s32** sont donc dues
  a s33 ; leur prix entre dans la borne **avant** qu'on ne compte une ligne (section 4).

---

## 0. Ce que j'ai joue aujourd'hui, et ce que je n'ai pas pu voir

**Aucun increment de s33 n'existe : le sprint n'est pas ouvert.** Ce que j'ai joue, c'est l'etat
servi par l'arbre de cloture de s32, sur les quatre paquets dont ce document tire ses conclusions.

| Ce que j'ai joue | Ce que j'y ai verifie |
|---|---|
| `bandit-manchot-histoire-geographie-cm1`, **deux parties completes de dix manches**, chaque proposition fausse pressee jusqu'a la revelation | l'etat des quatre lignes `en cours` ; les deux faits faux de `HG-R11` et `HG-R12` toujours servis ; la consigne a 31 mots ; le reliquat de `CONS-1` mesure sur les vingt manches servies |
| `bandit-manchot-regle-souhait-loi-cp` (`EMC-CP-07`), **partie complete**, erreurs volontaires | l'item 4 de la tache A et l'item 11, les deux points du troisieme refus, **et la cause que ce refus ne nomme pas** (section 3.4) |
| `bandit-manchot-regles-securite-eps-cp` (`EPS-CP-04`), **partie complete** | les quatre items de `SEC-R3` un par un, **et les quatre indices que la reprise laisserait derriere elle** (section 3.5) |
| `bandit-manchot-regles-jeu-collectif-eps-cp` (`EPS-CP-08`), **partie complete** | les six items sur onze que les motifs de refus touchent (section 5.5) |
| **Le catalogue servi**, par niveau | **68 entrees** : 36 au CP, 32 au CM1. C'etaient 66 a l'ouverture de s32 ; les deux entrees neuves sont les deux paquets d'EPS refuses. **Aucune entree de mecanique `designation`** : le palier 2 n'est jouable par personne, cinquieme sprint |

**Audit degrade sur un point, et je le declare** : j'ai joue par requetes HTTP reelles, donc j'ai lu
le **HTML servi**, pas la page rendue par un navigateur. Cela couvre les textes, l'ordre des ecrans,
l'enchainement apres une erreur et apres trois, le score et le tirage. Cela **ne** couvre ni la
couleur, ni le contraste, ni la taille, ni la position a l'ecran. **Aucune conclusion de ce document
ne repose sur un jugement visuel.**

**Ce que je n'ai pas joue** : les soixante-quatre autres entrees du catalogue. Ce document ne dit
rien d'elles, sauf la ou il nomme une passe transverse.

**Ce que je n'ai pas re-verifie et que je ne suppose pas** : la loi de tirage. Je donne des
proportions d'items servis, jamais une probabilite de tirage - j'ai deja enregistre un tirage comme
uniforme alors qu'il ne l'etait pas, et je ne recommence pas.

**Un fait de mes parties, dit d'emblee parce qu'il conditionne la lecture de la section 5.2** : sur
mes vingt manches d'histoire-geographie, **aucune n'a servi l'item de `HG-CM1-08` ni celui de
`HG-CM1-12`**. Ces deux lignes portent un item chacune sur les 92 du fichier. Je les ai lus **dans
le fichier servi, pas a l'ecran**, et c'est ecrit la ou je m'en sers.

---

## 1. L'etat, repris et non recalcule

**66 `couvert-valide`, 4 `en cours`, 68 `non couvert`, 71 `non couvrable en l'etat`.** 209 lignes au
total, **denominateur atteignable 138**, **66 / 138 = 47,8 %**.

**Ce compte n'est pas de moi.** Il est recompte sur les dix-huit tableaux de
`squad/programme/MATRICE-couverture.md` par `camille-po` (`DEC-0084`, section 1) et il m'est transmis
concordant. **Je ne le recalcule pas et je ne touche pas la matrice** : le compte lui appartient.

**Les quatre lignes `en cours`, et un fait qui gouverne tout le chiffrage de ce document** :

| Ligne | Libelle | Items servis | Etat |
|---|---|---|---|
| `HG-CM1-02` | Situer un fait dans un siecle ou un millenaire et ordonner des faits | 10 | **refusee** a s32 (`HG-R11`) |
| `HG-CM1-04` | Les grands mouvements de populations du IVe au Xe siecle et leurs effets | 10 | **refusee** a s32 (`HG-R12`) |
| `HG-CM1-08` | Identifier le passage de la Revolution a l'Empire et ce que Napoleon y change | **1** | jamais remplie |
| `HG-CM1-12` | Decrire comment on se loge, on travaille, on se cultive et on se deplace | **1** | jamais remplie |

> **Les quatre vivent dans un seul fichier**, `cm1-histoire-geographie.yaml`, 92 items. **Sous
> `PAR-10` - un rejeu se paie par paquet, pas par reserve - la totalite du travail
> d'histoire-geographie de s33 porte sur un paquet unique.** C'est ce qui rend ce terrain bon
> marche, et c'est aussi ce qui fait que `CONS-4` y est structurel (section 3.6).

**Compte des items, verifie par extraction sur le fichier servi et non repris d'une note de
paquet** : `HG-CM1-01` 10, `-02` 10, `-03` 10, `-04` 10, `-06` 10, `-07` 10, `-08` **1**, `-11` 10,
`-12` **1**, `-13` 10, `-14` 10. **`FER-2` demande dix items par ligne** : il manque **9 items sur
`-08` et 9 sur `-12`**, soit **18**.

---

## 2. Corrections a mes propres constats, avant de m'en servir

**Je verifie ce que j'ai ecrit avant de m'appuyer dessus.** Cinq constats de moi sont faux, perimes
ou incomplets.

### 2.1 Mon "terrain sans paquet neuf epuise fin s33" est faux - un refus le realimente

**Ce que j'ai ecrit le 2026-09-08**, section 9 de mon arbitrage prealable de s32, repris mot pour
mot par `DEC-0083` : le terrain qui ferme une ligne sans creer de paquet neuf *"compte exactement
huit lignes"* - les sept `en cours` plus `EMC-CP-07` - et *"il est epuise a la fin de s33"*.

**Le raisonnement supposait que ce terrain ne se remplit pas. Il se remplit, et s32 vient de le
remplir.** Un lot neuf refuse au premier passage **cree son paquet** puis **laisse sa ligne
ouverte** : la ligne quitte la colonne *"exige un paquet neuf"* et entre dans la colonne *"le paquet
existe deja"*. C'est exactement ce qui est arrive a `EPS-CP-04` et a `EPS-CP-08`.

| | A l'ouverture de s32 | A l'ouverture de s33 |
|---|---|---|
| Lignes fermables sans creer de paquet neuf | **8** | **7** |
| dont fermees a s32 | - | 3 (`HG-CM1-01`, `-03`, `LVE-CM1-07`) |
| dont refusees a s32, paquet desormais existant | - | 4 (`HG-CM1-02`, `-04`, `EPS-CP-04`, `EPS-CP-08`) |
| dont jamais remplies, paquet existant | - | 2 (`HG-CM1-08`, `HG-CM1-12`) |
| plus `EMC-CP-07`, refusee trois fois | 1 | 1 |

**8 - 3 = 5, et le terrain en compte 7.** Les deux de plus sont les deux lignes d'EPS que s32 a
refusees.

> **Mon "epuise a la fin de s33" est retire, date du 2026-09-09.** Ce terrain n'a pas de fond tant
> que des lots neufs sont refuses au premier passage, et **six lots sur sept l'ont ete depuis s29**.
>
> **Et ce n'est pas une bonne nouvelle : c'est le meme argent depense deux fois.** Un lot neuf
> refuse coute son ecriture, son audit, sa reecriture et son reaudit, pour la meme ligne. **Le
> terrain "bon marche" de s33 est fait, pour quatre lignes sur sept, de lots que le depot a deja
> payes une fois.** Je le dis parce que la lecture inverse - *"le vivier de s33 est
> inhabituellement favorable"* - est vraie sur le prix marginal et fausse sur le prix total.

**Ce que cette correction retire d'autre.** La phrase *"le triplement cesse d'acheter de la
couverture a partir de s34"*, que `DEC-0083` reprend de moi, n'est plus derivable de l'argument que
j'avais donne. Elle reste peut-etre vraie ; **je ne peux plus la soutenir avec cet argument-la**, et
je ne la remplace pas par un autre que je n'ai pas mesure. Ce que je peux soutenir sur s34 est en
section 8.

### 2.2 Le plafond de 6 lignes que `DEC-0083` annonce pour s33 est perime, et il l'est a la hausse

`DEC-0083`, section 8, chiffre s33 a un **plafond de 6 lignes** et une attente de 4 a 5. **Ce
chiffre suppose que s32 ferme les 8 lignes qu'il visait.** s32 en a ferme 3. Les 5 manquees ne sont
pas perdues : **4 d'entre elles sont sur des paquets qui existent maintenant**, et elles reviennent
a s33 au prix d'une reprise.

> **Le plafond de s33 sous le Plan 2 est de 9 lignes, et non de 6** (section 6). Le 6 de `DEC-0083`
> n'est pas une erreur de `camille-po` : c'est un chiffre indicatif dont l'hypothese - *"s32 ferme
> ce qu'il vise"* - a ete dementie par s32 lui-meme.

**Et le poste que `DEC-0083` place chez l'instance B a s33 n'est plus financable, pour la meme
raison** : son perimetre indicatif y met *"un lot de sciences CM1 neuf, avec reserves soldees dans
le sprint"*. **Les treize reserves que s32 a ouvertes coutent 3,0, dont 2,5 tombent sur l'autre
matiere de l'instance B** (section 4). Il n'y a plus de place pour un lot de sciences dans 4,0.
**Ce n'est pas un arbitrage de ma part : c'est de l'arithmetique**, et je la donne en section 6.3.

### 2.3 Mon refus d'avance numero 6 de s32 n'a pas ete applique, et je ne le replante pas

**Ce que j'ai ecrit le 2026-09-08**, table de la section 13, refus numero 6 : *"Toute ligne
d'histoire-geographie `en cours` fermee sans que la consigne du jeu nomme la tache de ses items
(`CONS-1`)"*, avec pour lieu de rupture *"`HG-CM1-03`, `-04` et `-08`"*.

**Ce qui s'est passe** : `DEC-0083` a transforme ce refus en **critere d'acceptation** de `S32-1`,
et mon **instance A** a ferme `HG-CM1-03` le 2026-09-09 **en ecrivant dans le meme document** que
`CONS-1` est *"non tenue"* sur ce paquet, et que 7 des items de `HG-CM1-03` portent une tache
qu'aucune clause de la consigne ne nomme.

**Je constate la contradiction et je l'arbitre dans le sens qui ne coute rien a l'equipe.**
`CONS-1` est **non bloquante depuis `DEC-0078`**, et je n'avais pas le pouvoir d'en faire un motif
de refus par une ligne de tableau, sans le dire et sans le dater. **Le poteau n'a pas ete deplace
par l'equipe : il a ete plante deux fois par moi, a deux hauteurs differentes, le meme jour.**

> **Le refus d'avance numero 6 de s32 est retire, date du 2026-09-09.** `CONS-1` reste **non
> bloquante** a s33 : elle ne refusera ni `HG-CM1-02`, ni `-04`, ni `-08`, ni `-12`. **Je n'efface
> rien** - le refus reste lisible ou il a ete ecrit, avec sa date - et ce retrait est ecrit ici.
>
> **Ce que je mets a la place, et qui n'est pas une barre de Done** : la decision de grain de
> `CONS-4` est due **avant** le remplissage, pas apres (section 9.1). Motif mesure : la consigne a
> grossi de 12 mots a 22 puis a 31, une clause par sprint, et le remplissage ajoute 18 items dont
> la tache dominante n'est nommee par aucune des cinq clauses. Une sixieme clause porterait la
> consigne au-dela de 35 mots. **Ce n'est pas un motif de refus, c'est un motif d'ordre.**

### 2.4 Mon 0,5 sur `EMC-CP-07` - je le maintiens, et je corrige le motif qui le portait

**Ce que j'ai ecrit le 2026-09-08** : `EMC-CP-07` est *"la ligne la moins chere du depot"*, 0,5,
*"aucun item neuf, seulement la reecriture de cinq propositions"*, sur `FAB-1c` seul.

**`FAB-1c` est paye** (instance B, 2026-09-09 : 0 item fautif sur 5 la ou la barre en autorise 1),
et le troisieme refus porte sur autre chose. **Mon 0,5 avait donc perdu son objet, et j'ai joue le
paquet avant de le reconduire par habitude.**

**Le prix tient - pour un motif entierement different**, donne en section 3.4 et chiffre en
section 5.3 : la cause du troisieme refus se corrige en **retirant une categorie**, pas en ajoutant
une exigence. **0,5, quatrieme passage, et c'est toujours le meilleur rapport du depot.**

### 2.5 Le residu du groupe 2a vaut 2,5 payables et non 3,0 - et cette correction est en faveur de l'equipe

**Ce que j'ai ecrit le 2026-09-08**, section 1.1 : le residu du groupe 2a coute **3,0**, reparti sur
six paquets, apres que je l'avais deux fois chiffre 1,5.

**Recompte sous ma propre clause `PAR-10`.** `HG-R8` porte sur `cm1-histoire-geographie`, **le
paquet meme que l'instance A reprend a s33** pour `HG-R9` a `HG-R12` et pour le remplissage. Sous
`PAR-10`, elle **voyage gratuitement**, exactement comme je l'avais ecrit a s32 pour ce paquet.

> **Le residu du groupe 2a est de 2,5 payables, plus 0,5 qui voyage gratuitement dans le travail de
> s33.** Cinq paquets a 0,5 : `signal-information-cm1`, `chaine-alimentaire-cm1`,
> `division-posee-cm1`, `fait-opinion-cm1`, `droit-devoir-cm1`. **Mes deux erreurs precedentes sur
> ce poste allegeaient la dette ; celle-ci l'allege aussi, et c'est la premiere fois que c'est pour
> une raison que je peux montrer.**

---
## 3. Ce que j'ai trouve en jouant et en lisant, et qui n'est dans aucune liste de dette

### 3.1 La cellule "se loger dans un espace urbain" de `HG-CM1-12` est deja depensee sur une ligne fermee

**C'est le fait le plus important de ce document pour le chiffrage, et aucun audit de lot ne peut le
voir.**

`HG-CM1-12` : *"Decrire comment on se loge, on travaille, on se cultive et on se deplace dans un
espace urbain et dans un espace touristique."* Sous `FER-1`, ce libelle enumere **quatre pratiques**
et **deux espaces** : huit cellules, pour dix items.

**J'ai lu les dix items de `HG-CM1-11`, ligne voisine, fermee `couvert-valide` a s28.** Cette
ligne-la dit *"Identifier les caracteristiques d'un lieu de vie et le distinguer d'un autre"*.
Voici ce que trois de ses items demandent reellement :

| Item du fichier | Enonce servi | Ce qu'il demande |
|---|---|---|
| 25 | *"Dans un espace urbain, quel type de logement est le plus frequent ?"* -> *"des immeubles collectifs"* | **on se loge, dans un espace urbain** |
| 23 | *"Dans un espace periurbain, comment la plupart des habitants se deplacent-ils... ?"* -> *"en voiture"* | on se deplace, espace periurbain |
| 24 | *"Dans un espace periurbain, quel type de logement est le plus frequent ?"* | on se loge, espace periurbain |

**`ADR-0081`, clause 5 : un item ne compte que sur une ligne.** L'item 25 est **dans le libelle de
`HG-CM1-12`** - il decrit comment on se loge dans un espace urbain - et il est **compte sur
`HG-CM1-11`**, ligne fermee.

**Je corrige ma premiere lecture avant de m'en servir.** En parcourant la liste, j'avais compte
**six** items de `HG-CM1-11` tombant dans le libelle de `HG-CM1-12`. **Le recompte en donne un
seul** : les items 16, 23, 24, 27 et 28 portent sur l'espace **rural** ou **periurbain**, et
`HG-CM1-12` ne nomme que l'espace **urbain** et l'espace **touristique**. Hors de ses deux
contextes, il n'y a pas de collision. **Mon chiffre de six etait faux et il exagerait le probleme ;
le chiffre est un.**

> **Consequence chiffree pour le remplissage** : la cellule *se loger / espace urbain* est
> **depensee**. Il reste **sept cellules libres**, dont l'item existant de `HG-CM1-12` occupe une
> (*"Dans un espace touristique de montagne, quelle activite se developpe surtout en hiver ?"*).
> **Neuf items neufs pour six cellules libres, avec l'interdiction de retomber sur la cellule
> prise.**

**Je ne rouvre pas `HG-CM1-11`.** Elle est fermee, son verdict est prononce, et un verdict prononce
ne se reecrit pas. **La frontiere que j'ecris en section 9.2 ne gouverne que les items neufs.**

### 3.2 "On se cultive" n'existe nulle part dans le paquet

Mesure sur le fichier servi `cm1-histoire-geographie.yaml`, commentaires retires : **zero**
occurrence de *cultive*, *cultiver*, *bibliotheque*, *cinema*, *musee*, *station*. `HG-CM1-12` porte
quatre pratiques dans son libelle ; **la troisieme n'a aucun contenu dans ce paquet.**

Ce n'est pas un defaut - la ligne n'a jamais ete servie - **c'est un cout**. Un des quatre elements
de `FER-1` sur cette ligne se fabrique a partir de rien, et c'est le seul des quatre dont je n'aie
aucun precedent a montrer a l'equipe. **C'est ce qui me fait porter le prix du remplissage de 1,5 a
2,0** (section 5.2).

### 3.3 `SCO-1` est toujours rouge, et je viens de la mesurer une troisieme fois en trois jours

**Mesure du 2026-09-09, cinq parties completes de dix manches, chaque proposition fausse pressee
avant la bonne, score lu dans la reponse de chaque appui :**

| Jeu | Score final | Appuis depenses pour dix manches |
|---|---|---|
| `bandit-manchot-regles-securite-eps-cp` | **10 / 10** | 18 |
| `bandit-manchot-regles-jeu-collectif-eps-cp` | **10 / 10** | 20 |
| `bandit-manchot-histoire-geographie-cm1` | **10 / 10** | 22 |
| `bandit-manchot-histoire-geographie-cm1` | **10 / 10** | 18 |
| `bandit-manchot-regle-souhait-loi-cp` | **10 / 10** | 18 |

**Cinq parties, cinq scores parfaits, et jamais moins de huit appuis de trop pour dix manches.** Un
enfant qui ne sait rien rapporte 10 sur 10 de ces cinq jeux, **dont les deux paquets d'EPS livres la
veille**.

> **Ce que j'en fais, et c'est une liberation de budget, pas une aggravation.** `DEC-0084` porte
> `D7` - ma verification de `SCO-1` - a **0,5, planifiee a s33**. Cette verification a maintenant
> ete faite **trois fois en trois jours, gratuitement**, dans des parties que je jouais de toute
> facon : le 2026-09-08 sur l'anglais, le 2026-09-09 par l'instance A sur huit parties, aujourd'hui
> sur cinq.
>
> **`D7` n'est plus due pour l'observation. Je la ramene a 0,0 et je libere 0,5 du registre de
> dette.** Elle redeviendra due, a 0,5, **le jour ou il y aura un remede a verifier** - et il n'y en
> a pas. Le remede appartient a `theo-archi`, `SCO-1` a `SCO-7` sont entierement automatisables, et
> je ne les chiffre pas.
>
> **Je ne recompte pas le compteur de reports** : l'instance A l'a etabli a neuf sprints le
> 2026-09-09, et un compte date ne se refait pas a chaque document.

### 3.4 `EMC-CP-07` - les trois categories de la tache A ne sont pas disjointes, et le libelle n'en demande que deux

**Le troisieme refus nomme deux items** : l'item 4 (*"En France, le casque a velo est obligatoire
avant douze ans. Est-ce une regle ?"* -> `une loi`, avec *"une regle qui vaut pour tous"* marquee
fausse) et l'item 11 (distracteur mort). **J'ai joue le paquet et lu les onze items, et la cause est
plus large que les deux items.**

**Le libelle de la ligne** : *"Distinguer une regle qui vaut pour tous d'un simple souhait, et
reconnaitre qu'une loi s'impose a tous."*

**Deux parties, et elles ne sont pas de meme nature** : (a) une **distinction** entre deux termes -
regle qui vaut pour tous contre simple souhait ; (b) une **propriete a reconnaitre** - une loi
s'impose a tous. **Le libelle ne demande nulle part de distinguer une loi d'une regle.**

**Ce que le paquet fait, item par item, releve dans le fichier servi et joue :**

| Items | Propositions | Ce que le libelle demande |
|---|---|---|
| 1, 2, 3 | binaire : *une regle qui vaut pour tous* / *un simple souhait* | **exactement (a)** |
| 4, 5, 6 | ternaire : les deux precedentes **plus** *une loi* | une distinction que le libelle ne demande pas |
| 7 a 11 | *"Une loi... Qui doit / Qui peut ?"* | **exactement (b)** |

**Et le ternaire n'est pas disjoint.** Une loi **est** une regle qui vaut pour tous - c'est
precisement ce que la partie (b) du libelle enseigne. Sur l'item 4, le jeu marque fausse une
proposition que la ligne elle-meme apprend a tenir pour vraie. **Les items 5 et 6 portent le meme
ternaire** et ne sont sauves que par le hasard de leur bonne reponse : sur l'item 5 (*attendre son
tour a la cantine*) et sur l'item 6 (*un souhait d'Aya*), *"une loi"* est un distracteur. **Le
defaut de l'item 4 est structurel et il dort dans les items 5 et 6.**

> **Ce que je route, et je ne fournis pas les textes.** La sortie ne demande **aucun item neuf** et
> **aucune exigence de plus** : elle demande d'en **retirer** une. Que la tache A redevienne le
> binaire que le libelle demande - *une regle qui vaut pour tous* contre *un simple souhait* - sur
> les items 4, 5 et 6 comme sur les items 1, 2 et 3. **L'item 4 devient alors juste tel quel** : le
> casque a velo obligatoire **est** une regle qui vaut pour tous, et c'est la bonne reponse. **La
> partie (b) du libelle reste entierement servie par les cinq items de la tache B**, qui la servent
> deja et que personne ne conteste.
>
> **`FER-3` tient** : tache A a 6 items, tache B a 5, minorite a 5, au-dessus du plancher de 3 -
> plancher que l'instance C a confirme dans le code le 2026-09-09 (`MINIMUM_PAR_TACHE = 3`).
> **Quatre items touches sur onze**, propositions seules, aucun enonce change. **C'est la condition
> `EMC-11`, allouee en section 13.**

### 3.5 Sur `EPS-CP-04`, les quatre indices portent le repere lexical que la reprise doit retirer des propositions

`SEC-R3` demande que, sur les items 8, 9, 10 et 11, **les trois** propositions nomment un danger,
une blessure ou une consequence, dont deux fausses - pour que la bonne reponse cesse d'etre la seule
a parler de se faire mal. **J'ai joue le paquet, et l'indice servi apres une erreur porte le meme
repere que les propositions.**

| Item | Bonne reponse | Indice servi apres une erreur | Le mot qui les relie |
|---|---|---|---|
| 8 | *pour verifier qu'aucune **voiture** n'arrive* | *"Une **voiture** peut arriver de la gauche comme de la droite."* | voiture |
| 9 | *pour porter secours en cas de **danger*** | *"Un **danger** dans l'eau peut survenir tres vite."* | danger |
| 10 | *pour eviter que deux enfants **se heurtent*** | *"Une glissade a deux peut faire **tomber** l'un des deux enfants."* | le choc |
| 11 | *pour eviter de **se blesser** en touchant le fond* | *"Plonger dans une eau trop basse peut **blesser** la tete ou le cou."* | blesser |

**Une reprise qui reecrit les huit distracteurs et laisse les quatre indices intacts ne corrige
rien** : l'enfant qui se trompe une fois lit un indice qui nomme le champ lexical de la bonne
reponse, et **ce champ redevient discriminant** des lors que les trois propositions le partagent -
c'est meme pire, parce que l'indice designe alors **laquelle** des trois.

> **Je le dis maintenant plutot que de refuser la reprise pour cela.** C'est la condition `EPS-7`,
> allouee en section 13, et **je la compte a l'interieur du meme 0,5** : elle ne rencherit pas la
> reprise, elle en nomme le perimetre reel - **huit distracteurs et quatre indices**, et non huit
> distracteurs.
>
> **Ce n'est pas un durcissement d'`EPS-4`.** `EPS-4` est datee du 2026-09-09 et **n'a encore
> rencontre aucun contenu** : je complete une condition avant son premier lot, je ne releve pas une
> barre qu'un lot a atteinte.

### 3.6 `CONS-1` mesure en jouant : neuf des vingt manches servies portent une tache que la consigne ne nomme pas

Consigne servie, relevee a chacune de mes vingt manches, **31 mots, cinq clauses** :

> *"Lis bien, puis situe dans le temps, nomme un peuple ou un mouvement de population, associe un
> roi a son fait, decris un espace ou une ressource, ou lis un document."*

**Sur les vingt manches que le tirage m'a servies, neuf demandent une tache qu'aucune des cinq
clauses ne nomme.** Exemples releves tels quels : *"Comment appelle-t-on cela ?"* (le reseau de
routes romain), *"Comment appelle-t-on cette etape ?"* (le traitement de l'eau), *"Comment
appelle-t-on ce type d'energie ?"*, *"Que devient son expansion en Europe ?"*, *"Que devient ce
territoire ?"*, *"Comment appelle-t-on cet evenement ?"*.

**Je donne le compte et sa methode, pas un pourcentage du paquet** : l'attribution d'une tache a un
item est la mienne, faite a la main, sur les vingt manches jouees et non sur les 92 items. Un
chiffre que personne ne peut refaire ne vaut pas mieux qu'aucun chiffre. **Neuf sur vingt suffit a
agir**, et c'est coherent avec ce que l'instance A a compte autrement le 2026-09-09 (quatorze items
crees dont la tache n'est nommee par aucune clause).

---

## 4. Ma borne de s33, et le registre de dette a son ouverture

### 4.1 La borne : 12,0, reprise et non rediscutee

**`DEC-0084` a tranche : la retenue de 3,0 ne tombe pas, la borne de s33 reste 12,0.** Le
declencheur que j'avais ecrit le 2026-09-08 porte sur **une occurrence**, pas sur sa gestion ; une
occurrence de la forme (c) a eu lieu a la fusion des trois branches.

> **Je reprends 12,0 et je ne demande pas sa revision.** Trois instances a 4,0 (`PAR-9`), plafond
> individuel dur 5,0 (`PAR-8`), `PAR-11` amendee, `PAR-12` et `PAR-13` opposables. **Rien de ce
> document ne touche a ces clauses.**

**Et je dis ce que je pense du fait, sans en tirer une revision.** Les trois instances avaient
annonce ce risque d'avance, chacune dans son document, avant la fusion ; le conflit a ete **reel et
visible** sur le fichier, jamais silencieux ; le recompte a ete refait sur l'arbre assemble. **C'est
la premiere fois qu'une couture de ce depot est annoncee avant d'etre subie**, et c'est exactement
le comportement que la retenue voulait acheter. **Ma condition ne distingue pas une couture annoncee
d'une couture decouverte, et c'est un defaut de ma condition, pas un merite de la retenue.**

**Ce que je ne fais pas** : je ne reecris pas la condition apres coup pour qu'elle rende un autre
verdict. **Une barre qu'on abaisse une fois atteinte a l'usure vaut aussi peu qu'une barre qu'on
releve.** Si l'utilisatrice veut, pour s34, une incitation qui morde encore, je proposerai une
formulation qui distingue une occurrence annoncee d'une occurrence subie - **ce n'est pas une
proposition de ce document et je ne la chiffre pas.**

**Un fait de calibration, contre ma propre borne** : les trois instances ont depense **9,75 sur
12,0** a s32, et **2,25 n'ont pas ete depenses**. Le motif que l'instance C a ecrit est le bon -
*"deux paquets refuses coutent moins cher a auditer que deux paquets valides"*, un refus s'arretant
au premier defaut suffisant. **Ce sous-emploi ne se reproduira pas a s33** : une composition de
reprises se juge en balayant toutes les conditions jusqu'a la derniere, comme une validation.
**12,0 sera plus pleine a s33 qu'a s32, pour un plafond de lignes plus grand.**

### 4.2 Les treize reserves neuves de s32 sont dues, et elles coutent 3,0

**La regle du Plan 2 s'applique** : les reserves non bloquantes d'un lot se soldent dans le sprint
qui l'a ouvert. **Compte par paquet (`PAR-10`), et non par code** - c'est l'unite de ma propre
clause, et compter par code triplerait le prix de trois paquets.

| Paquet | Reserves neuves de s32 qu'il porte | Prix |
|---|---|---|
| `cm1-histoire-geographie` | `HG-R9`, `HG-R10` | **0,0** - voyagent avec la reprise du meme paquet (section 5.1) |
| `cm1-anglais` | `ANG-R3`, `ANG-R4`, `ANG-R5`, `ANG-R6` | **0,5** |
| `cp-emc-emotions-autrui` | `EMO-R3` | **0,5** |
| `cp-emc-exclusion` | `EXC-R6`, plus `EXC-R1` et `EXC-R5` impayees depuis le 2026-09-07 | **0,5** |
| `cp-emc-formules-de-politesse` | `POL-R3`, `POL-R4` | **0,5** |
| `cp-emc-permis-interdit` | `PER-R2`, plus `CAT-1` non payee | **0,5** |
| `cp-emc-regles-de-vie` | `REG-R2`, `REG-R3`, plus `CAT-1` non payee | **0,5** |
| portee D | `CONS-4` | **non chiffree** : le geste n'est pas de mon ressort (decision de grain, `ADR-0081`) |
| | **Total** | **3,0**, plus `CONS-4` |

> **Trois des treize sont nees de la lecture d'une de mes propres barres et cinq de mes propres
> comptes incomplets.** L'instance B l'ecrit : `EMO-R3`, `EXC-R6` et `POL-R3` sont des enonces que
> `EMO-R1`, `EXC-R4` et `POL-R1` n'avaient pas comptes ; `POL-R4` est le defaut de `PER-R1` que je
> n'avais pas vu sur le paquet voisin ; `REG-R2` sont deux distracteurs que `REG-R1` n'avait pas
> comptes. **Je ne facture pas moins pour autant** - le texte reste a ecrire quel que soit le
> responsable de son oubli - **mais je le dis, parce qu'une part de ces 3,0 est le prix de mes
> comptes a la main.**

**Et `REG-R3` est d'une autre nature, je la nomme a part** : *"l'adulte de l'ecole est une femme 7
fois sur 7 sur ce paquet, 17 contre 1 sur l'EMC CP entier"*. **J'avais pose ce constat le 2026-09-05
pour que le prochain lot ne l'aggrave pas ; il l'a aggrave.** C'est un stereotype de genre, pas une
maladresse de formulation, et c'est le seul des treize dont le motif touche `BIA-1`.

### 4.3 Le registre de dette a l'ouverture de s33, compte par paquet

**Je le donne dans l'unite de `PAR-10`, parce que c'est la seule dans laquelle je sais le payer.**
Un registre par code a fait croire, a s32, que six reserves sur un paquet coutaient six fois le
prix d'une.

| Paquet ou portee | Codes ouverts | Prix | Lignes en jeu |
|---|---|---|---|
| `cm1-histoire-geographie` | `HG-R8`, `HG-R9`, `HG-R10`, `HG-R11`, `HG-R12`, reliquat de `CONS-1` | **0,5** | **+2** (`HG-CM1-02`, `-04`) |
| `cp-emc-regle-souhait-loi` | motif du troisieme refus | **0,5** | **+1** (`EMC-CP-07`) |
| `cp-eps-regles-de-securite` | `SEC-R1`, `SEC-R2`, `SEC-R3` | **0,5** | **+1** (`EPS-CP-04`) |
| `cp-eps-regles-jeu-collectif` | `JEU-R1` a `JEU-R8` | **1,0** (`PAR-14`, section 5.5) | **+1** (`EPS-CP-08`) |
| `cp-emc-regles-de-vie` | `CAT-1`, `REG-R2`, `REG-R3` | **0,5** | 0 |
| `cp-emc-permis-interdit` | `CAT-1`, `PER-R2` | **0,5** | 0 |
| `cp-emc-emotions-autrui` | `EMO-R3` | **0,5** | 0 |
| `cp-emc-formules-de-politesse` | `POL-R3`, `POL-R4` | **0,5** | 0 |
| `cp-emc-exclusion` | `EXC-R1`, `EXC-R5`, `EXC-R6` | **0,5** | 0 |
| `cm1-anglais` | `ANG-R3` a `ANG-R6` | **0,5** | 0 |
| `cm1-sciences-signal-information` | `SIG-R1`, `SIG-R2` | **0,5** | 0 |
| `cm1-sciences-chaine-alimentaire` | `CHA-R1` | **0,5** | 0 |
| `cm1-division-posee` | `POS-R3` | **0,5** | 0 |
| `cm1-emc-fait-opinion` | `FOP-R4` | **0,5** | 0 |
| `cm1-emc-droit-devoir` | `DRO-R4` | **0,5** | 0 |
| `cm1-sciences-melanges` | `MEL-R4` | **0,0** - ne justifie pas un rejeu a elle seule | 0 |
| portee D | groupe 2b hors histoire-geographie : `CONS-2`, `SAI-R1`, `ERR-R1` | **1,0** | 0 |
| portee D | `CONS-3` | **non chiffree**, et je le dis comme manquant plutot que de l'estimer | 0 |
| portee D | `CONS-4` | **non chiffree**, decision de grain due avec `camille-po` | 0 |
| portee D | `SCO-1`, ma verification (`D7`) | **0,0** - payee gratuitement trois fois (section 3.3) | 0 |
| | **Total payable** | **9,0**, plus `CONS-3` et `CONS-4` | **+5** |

> **Le registre etait de 7,5 a l'ouverture de s32. Il est de 9,0 a l'ouverture de s33.** s32 a ferme
> trois lignes **et a laisse la dette plus grosse qu'il ne l'a trouvee**, sous le Plan 2, c'est-a-dire
> sous le plan que j'ai recommande **precisement pour eviter cela**.
>
> **Pourquoi le Plan 2 ne l'a pas empeche, et c'est un defaut de ma regle, pas de son execution.**
> La regle du Plan 2 solde *les reserves qu'un lot neuf ouvre*. La croissance de s32 ne vient pas de
> la : elle vient (a) de **quatre refus**, qui ne sont pas des reserves de lot mais des lignes
> bloquees, et (b) de **sept reserves neuves sur des paquets deja fermes**, trouvees en les
> reauditant. **Ma regle ne dit rien du reaudit d'un paquet ancien**, et c'est exactement la
> population qui a grossi.
>
> **Je ne comble pas ce trou par une clause dans ce document**, et le motif est que cinq des sept
> viennent de mes propres comptes incomplets : une clause qui ferait payer a l'equipe le
> recomptage de mes barres serait le durcissement que je m'interdis. **Je nomme le trou et je le
> route a `camille-po`.**
---

## 5. Le prix de chaque poste du vivier

**Tarifs reconduits de s31 et de s32 sans changement** : **1,5** pour un lot dont je ne suis pas la
source et ou chaque fait se verifie contre une source ; **1,0** pour un lot dont je suis la source.
**Une seule source a verifier dans un lot ouvre la verification et fait passer le lot a 1,5.**
`PAR-10` : **un rejeu se paie par paquet, pas par reserve.**

### 5.1 La reprise du paquet d'histoire-geographie - 0,5 pour deux lignes, et c'est le meilleur rapport du depot

**Les quatre reserves d'histoire-geographie vivent dans un seul paquet.** Sous `PAR-10`, elles
coutent **0,5 en tout**, et non 0,25 + 0,5 + 0,5 + 0,25 comme la lecture par code le suggererait.
Les regimes que l'instance A a ecrits - *mot pour mot* pour `HG-R11` et `HG-R10`, *rejeu borne* pour
`HG-R9` et `HG-R12` - decrivent **ce que le developpeur doit faire**, pas ce que je facture.

| Reserve | Ce qu'elle demande | Verifie par moi aujourd'hui |
|---|---|---|
| `HG-R11`, **bloquante** | *"le sacre de Clovis, roi des Francs, en 481"* remplace par la formulation que l'item 66 du meme fichier porte deja | **le fait faux est toujours servi**, en bonne reponse d'un item et en distracteur d'un autre |
| `HG-R12`, **bloquante** | *"les Sarrasins"* cesse d'etre la reponse a *"Quel peuple ?"*, par l'une des deux directions nommees | **toujours servi**, avec son commentaire de paquet qui route le terme a mon arbitrage |
| `HG-R9` | les cinq indices de la tache d'ordre portent la methode, non la date de la bonne reponse | inchange |
| `HG-R10` | l'indice de l'item 87 cesse d'attribuer a 481 l'unification du royaume | inchange |
| `HG-R8` | reserve du groupe 2a, **voyage gratuitement** (section 2.5) | - |

> **Prix : 0,5. Lignes : +2** (`HG-CM1-02`, `HG-CM1-04`). **0,25 par ligne, et c'est le meilleur
> rapport de tout le depot** - meilleur que le 0,5 par ligne d'`EMC-CP-07` que j'avais nomme comme
> tel a s32.
>
> **Ce que ce 0,5 achete de mon cote** : je rejoue les deux lignes entierement - `FER-1`, `FER-2`,
> `FER-3` et l'exactitude sur leurs vingt items - et non seulement les items corriges. **Dix-huit de
> ces vingt items ont deja ete verifies par l'instance A le 2026-09-09**, ce qui est exactement ce
> qui rend ce reaudit bon marche.

**`HG-R12` demande un item et ne ferme pas la ligne** : l'instance A l'a etabli le 2026-09-09 - le
plafond de trois items de la tache de nomination **n'est pas structurel** sur `HG-CM1-04`, le libelle
ne fermant aucune liste, et un quatrieme mouvement du IVe-Xe siecle s'enseigne (l'installation des
Bretons en Armorique). **Je reprends ce constat et je ne le refais pas.**

### 5.2 Le remplissage de `HG-CM1-08` et `HG-CM1-12` - 2,0, et je rechiffre a la hausse un prix que j'ai publie

**Ce que j'ai publie**, et que `DEC-0083` reprend : *"tranche 2 : `HG-CM1-08`, `-12` (18 items)
1,5"*.

**Ce que je n'avais pas mesure quand j'ai ecrit 1,5, et que j'ai mesure aujourd'hui** :

1. **`HG-CM1-12` porte le `FER-1` le plus lourd des quatre lignes d'histoire-geographie de s33** :
   quatre pratiques et deux espaces, huit cellules pour dix items.
2. **Une de ses huit cellules est deja depensee sur `HG-CM1-11`, ligne fermee** (section 3.1). La
   verification n'est pas de lire dix items : c'est de confronter chacun des neuf items neufs aux
   dix items d'une ligne voisine fermee, sous `ADR-0081` clause 5. **C'est un travail de couture, et
   c'est le seul du sprint que je ne peux pas deleguer a une condition.**
3. **Un des quatre elements de son libelle - *on se cultive* - n'a aucun contenu dans le paquet**
   (section 3.2), donc aucun precedent contre lequel je puisse juger le niveau des items neufs.
4. **`HG-CM1-08` porte un `FER-1` par *et*** : *"le passage de la Revolution a l'Empire **et ce que
   Napoleon Bonaparte y change**"*. La pente naturelle du remplissage est d'ecrire neuf items de
   plus sur le passage. **La seconde moitie du libelle est celle qui sera oubliee.**

> **Prix : 2,0 pour les deux lignes. Lignes : +2.** Je porte mon propre chiffre de 1,5 a 2,0 et je
> dis franchement pourquoi.
>
> **Et je dis ce que ce rechiffrage est et ce qu'il n'est pas.** Un prix est **mon cout d'audit**,
> pas une barre sur l'increment : je ne demande rien de plus au lot que `FER-1`, `FER-2`, `FER-3` et
> l'exactitude, qui sont toutes anterieures. **Je ne deplace aucun poteau ; je corrige une estimation
> que j'avais faite sans avoir lu la ligne voisine.**

### 5.3 `EMC-CP-07`, quatrieme passage - 0,5 pour une ligne

**Perimetre reel, mesure en jouant et en lisant les onze items** (section 3.4) : les propositions
des items 4, 5 et 6 redeviennent binaires, et le distracteur mort de l'item 11 est remplace.
**Quatre items touches sur onze, propositions seules, aucun enonce change, aucun item neuf.**

> **Prix : 0,5. Lignes : +1.** `DEC-0084` note que je n'avais pas chiffre ce quatrieme passage ; le
> voici, et il est **plus petit que les trois premiers**, parce que la sortie retire une categorie
> au lieu d'ajouter une exigence.
>
> **Ce que ce 0,5 achete** : une partie complete de dix manches avec erreurs volontaires, les cinq
> faits de droit reverifies, `FAB-1c` recomptee sur la tache B pour verifier qu'elle **reste** payee,
> `EMC-5` sur les onze items, `PLA-1` sur le distracteur neuf, et `FER-3` recomptee apres le
> changement de tache A.

### 5.4 La reprise de `EPS-CP-04` - 0,5 pour une ligne, avec les indices dans le prix

**Perimetre reel** : les **huit distracteurs** des items 8, 9, 10 et 11 (`SEC-R3`), **plus les
quatre indices** de ces memes items (`EPS-7`, section 3.5), plus `SEC-R1` (deux items qui servent la
securite routiere du pieton et non l'activite physique, `EPS-5`) et `SEC-R2` (une reponse
defendable). **Quatre items touches sur onze pour `SEC-R3` et `EPS-7`, deux de plus pour `SEC-R1`.**

**Le contenu est par ailleurs exact et lisible** : l'instance C l'a verifie item par item et je le
reprends. Les onze enonces, les onze bonnes reponses et toute la tache A restent tels quels.

> **Prix : 0,5. Lignes : +1.** `EPS-7` est **dans ce 0,5**, pas au-dessus.
>
> **Ce que ce 0,5 achete, et pourquoi il n'est pas plus eleve malgre `SEC-R1`** : les deux items de
> `SEC-R1` sont remplaces, ce qui est le geste le plus lourd du poste, mais `FER-2` reste tenue a
> onze items et `FER-3` ne bouge pas. **Un remplacement d'item dans un paquet dont le reste est
> verifie n'est pas un lot.**

### 5.5 La reprise de `EPS-CP-08` - 1,0, et la clause de prix que ce cas me fait ecrire

**Perimetre reel, compte sur les onze items du paquet servi que j'ai joue et lu** :

| Item | Ce qui doit changer | Reserve |
|---|---|---|
| 4 | *"quand on est touche"* puis *"il va dans le camp adverse"* : le pronom n'a pas d'antecedent | `JEU-R5` |
| 5 | la regle du loup et des moutons est une variante que l'enonce ne rappelle pas (`EPS-1`), et son indice *"Le loup grandit au fil de la partie"* est faux au premier degre (`EPS-6`) | `JEU-R2`, `JEU-R3`, `JEU-R8` |
| 7 | *"Lea touche Hugo sur un banc. A-t-elle respecte la regle ?"* -> *"non"*. **C'est faux** : toucher un joueur perche n'est pas une infraction, cela n'a simplement aucun effet | `JEU-R1` |
| 8 | *"Malo traverse sans se faire toucher. A-t-il respecte la regle ?"* : aucune regle n'est en jeu et l'enonce porte deja son verdict | `JEU-R4` |
| 10, 11 | la tache B se gagne a la forme de l'enonce, et les distracteurs ne sont pas des sanctions plausibles (`PLA-1`) | `JEU-R6`, `JEU-R7` |

**Six items sur onze changent, dont un qui enseigne quelque chose de faux.**

> **`PAR-14` - nouvelle, datee du 2026-09-09.** **Une reprise dont les corrections touchent plus de
> la moitie des items d'un paquet se paie au tarif d'un lot** - 1,0 quand je suis la source des
> motifs, 1,5 sinon - **et non au tarif d'un rejeu (0,5).**
>
> **Motif, mesure sur ce cas** : `PAR-10` fixe le rejeu a 0,5 par paquet, et ce chiffre a ete
> calibre sur des paquets ou quelques propositions changent - les dix reserves du groupe 2a, les neuf
> de s31. **Quand la majorite des items change, l'audit precedent ne couvre plus rien** : je dois
> rebalayer `FER-2`, `FER-3`, `BIN-1`, `FAB-1a`, `FAB-1b`, `FAB-1c`, `PLA-1`, `EPS-1` a `EPS-6` et
> l'exactitude sur onze items, c'est-a-dire faire le travail d'une validation. **Un refus qui
> demande la reecriture de six items sur onze n'est pas un rejeu, c'est un second premier passage.**
>
> **Ce que cette clause coute et ce qu'elle ne fait pas** : elle augmente le prix d'un poste, donc
> elle diminue ce que le sprint peut planifier ailleurs. **Elle ne releve la barre d'aucun
> increment** - c'est une regle de mon budget. Et elle est **non retroactive** : elle ne rechiffre ni
> s32, ni le groupe 2a, ni les neuf reserves de s31.

> **Prix : 1,0. Lignes : +1.**

### 5.6 Le lot de l'effort et du corps - 1,5, et une recommandation de grain que je ne tranche pas seule

`EPS-CP-10` (*effets de l'effort sur le corps*) et `EPS-CP-11` (*echauffement, hydratation,
recuperation*) sont les deux lignes d'EPS CP qui restent apres les deux reprises. **Vingt items au
moins, `FER-2` demandant dix par ligne, et `DEC-0064` onze au moins dans un paquet de fermeture a
dix manches.**

> **Prix : 1,5** - je ne suis pas la source du contenu et **chaque fait sur le corps a l'effort se
> verifie contre une source** : essoufflement, frequence cardiaque, chaleur, soif, delai de
> recuperation. **Lignes : +2.**

**Et une recommandation de grain, que je nomme sans la trancher parce qu'`ADR-0081` la confie a
`camille-po` et a moi ensemble** : **un seul paquet pour les deux lignes**, et non deux.

| | Deux paquets | Un paquet |
|---|---|---|
| Dette differee ouverte (`0,5` par paquet neuf) | **1,0** | **0,5** |
| Entrees de catalogue a ecrire | 2 | 1 |
| Consignes a ecrire, et clauses par consigne | 2 | 1, **a deux clauses** |
| Surface de couture entre deux moities | oui | **aucune** |

**Le contre-argument est `CONS-4` elle-meme, et il ne mord pas ici** : le defaut de
`histoire-geographie-cm1` est qu'**onze** lignes de referentiel partagent une consigne. **Deux
lignes voisines qui partagent une consigne a deux clauses ne sont pas ce cas-la** - c'est ce que
font deja `regles-de-vie` et `regle-souhait-loi` sans que `CONS-1` y morde. **Je nomme le seuil que
je ne sais pas fixer : je n'ai aucune mesure qui dise a partir de combien de lignes une consigne
commune cesse d'informer l'enfant.** Je dis seulement que deux n'est pas onze.

### 5.7 Le ratio d'items du vivier, et la correction de mon propre argument

**Ce que j'ai ecrit a s32** : un remplissage de ligne `en cours` coute **8,3 items par ligne**, un
lot neuf **11**, *"l'economie est de 24 pour cent sur les items, pas un facteur"*.

**Le vivier de s33, recompte item par item** :

| Ligne | Items a creer |
|---|---|
| `HG-CM1-08` | 9 |
| `HG-CM1-12` | 9 |
| `HG-CM1-04` | 1 (`HG-R12` demande un item) |
| `HG-CM1-02`, `EMC-CP-07`, `EPS-CP-04`, `EPS-CP-08` | **0** - leurs dix ou onze items existent, ils se corrigent |
| **Sept lignes** | **19 items** |

**19 items pour 7 lignes : 2,7 items par ligne.** Contre 8,3 pour un remplissage et 11 pour un lot
neuf : **un facteur 3 a 4, et non 24 pour cent.**

**Et voici la correction qui compte, parce qu'elle va contre l'argument que ce chiffre suggere** :

| | Prix de mon audit | Lignes | Par ligne |
|---|---|---|---|
| Le vivier de s33 (5 postes) | 0,5 + 2,0 + 0,5 + 0,5 + 1,0 = **4,5** | 7 | **0,64** |
| Un lot neuf de deux lignes (lot 1,5 + marge 0,5 + dette differee 0,5) | **2,5** | 2 | **1,25** |

> **L'economie d'items est un facteur 3 ; l'economie d'audit est un facteur 2.** Le nombre d'items
> n'est pas mon poste de cout - **une ligne se ferme en balayant des conditions, pas en comptant des
> items**, et une ligne de dix items deja ecrits demande le meme balayage qu'une ligne de dix items
> neufs, moins l'exactitude de ceux qui ont deja ete verifies.
>
> **Ce que cela retire a la lecture "le vivier est inhabituellement favorable"** : il l'est, d'un
> facteur deux sur mon budget, et pas de trois ni de quatre. **Et il ne l'est pas du tout sur le prix
> total du depot** (section 2.1) : quatre de ses sept lignes ont deja ete payees une fois.

---

## 6. La composition de s33 sous le Plan 2

**Je ne priorise pas et je n'ouvre aucun perimetre.** Cette composition tient dans la borne de 12,0,
respecte `PAR-1`, `PAR-2`, `PAR-7`, `PAR-8`, `PAR-9`, `PAR-11`, `PAR-12` et `PAR-13`, et applique la
regle du Plan 2. **`camille-po` tranche, `sofia-sm` planifie.**

### 6.1 Les trois terrains, et la reponse sur les sciences CM1 de l'instance B

| | Terrain | Motif |
|---|---|---|
| **A** | **instance d'accumulation** (`PAR-12`) : portee **D**, les quatre lignes `en cours`, l'histoire-geographie CM1, l'anglais CM1 | inchange depuis s32. Elle a l'image du paquet et elle vient d'auditer ses 92 items |
| **B** | **l'EMC CP, en entier et jusqu'a son terme** | elle en a l'image depuis s31 et s32 : six paquets joues, neuf reserves instruites, trois passages sur `EMC-CP-07` |
| **C** | **l'education physique et sportive, CP puis CM1** | inchange depuis s32. `EPS-1` a `EPS-6` sont ses conditions, et les deux paquets refuses sont sa matiere par `PAR-2` |

**La question m'est posee : l'instance B retrouve-t-elle son terrain permanent de sciences CM1,
qu'aucun lot n'a finance a s32 ? La reponse est non, et ce n'est pas un arbitrage de terrain -
c'est de l'arithmetique.**

**B detient deux images** : les sciences CM1 depuis s30 et s31 (quatre lignes fermees, `SCI-1` a
`SCI-4` eprouvees) et l'EMC CP depuis s32. **`PAR-7` lui permet les deux ; 4,0 lui en paie une.**

| Ce que B pourrait faire de ses 4,0 | Cout | Lignes |
|---|---|---|
| L'EMC CP en entier : six paquets, `CAT-1`, les sept reserves neuves, `EXC-R1` et `EXC-R5` | **3,0** | **+1** |
| Un lot de sciences CM1 neuf, reserves soldees dans le sprint (Plan 2) | 1,5 + 1,0 + 0,5 de marge = **3,0** | **+2** |
| **Les deux** | **6,0** | +3 |

**6,0 depasse le plafond individuel dur de 5,0 (`PAR-8`), et il n'y a rien a transferer** : A est a
4,0 et C est a 4,0 (sections 6.2 et 6.4). **Le sciences CM1 ne rentre pas.**

> **La dette la plus ancienne du depot et le terrain de contenu le plus fourni appartiennent a la
> meme instance.** C'est la contrainte reelle de s33, et elle n'est pas dans ma borne : elle est
> dans `PAR-2`. **Je ne propose pas de la lever** - une quatrieme instance n'est pas ouverte, et
> partager l'EMC CP entre deux instances est exactement ce que `PAR-2` interdit et ce que trois
> coutures en trois sprints justifient.
>
> **Ce que je recommande, et le motif est mesure** : **B finit l'EMC CP.** `CAT-1` en est a son
> **cinquieme sprint de report** et son paiement a ete refuse **deux fois** ; `EXC-R5` traverse un
> deuxieme sprint et c'est **le seul endroit des six paquets ou le produit dit non a un enfant qui a
> raison** ; `REG-R3` est un stereotype de genre que j'ai signale pour qu'il ne s'aggrave pas et que
> le dernier lot a aggrave. **Aucune ligne de sciences ne vaut ces trois-la.**

**`PAR-13` - verifiee et non supposee** : **aucune instance ne prend, a s33, un terrain dont mes
conditions n'ont jamais rencontre de contenu reel.** L'histoire-geographie a mordu a s27, s28, s29 et
s32 ; l'EMC CP a s29, s31 et s32 ; l'EPS a s32. **C'est le premier sprint depuis s29 dans cet
etat**, et c'est le fait qui me permet de ne pas descendre mon attente plus bas que la section 7.

**Et je nomme la moitie de `PAR-13` qui n'est pas satisfaite, sans la facturer** : `EPS-2` et
`EPS-3` - aucune comparaison entre enfants, aucune conduite morale portee par une bonne reponse -
**n'ont encore rencontre aucun contenu**, et ce sont exactement les conditions des deux lignes du
corps et de l'effort. **`PAR-11` amendee dit *"une matiere dont mes conditions n'ont jamais rencontre
de contenu reel"*, et l'EPS n'en est plus une : je ne prends donc pas le supplement de 0,5.**
J'applique ma clause telle qu'elle est ecrite, comme j'ai refuse de l'amender le lendemain de son
ecriture a s31, et **je nomme le trou plutot que de le combler a mon profit.**

### 6.2 Instance A - 4,0, quatre lignes

| Poste | Prix | Lignes |
|---|---|---|
| Remplissage `HG-CM1-08` et `HG-CM1-12`, 18 items (section 5.2) | **2,0** | **+2** |
| Reprise du meme paquet : `HG-R8` a `HG-R12` et le reliquat de `CONS-1` (section 5.1) | **0,5** | **+2** |
| `cm1-anglais` : `ANG-R3` a `ANG-R6`, dues sous la regle du Plan 2 | **0,5** | 0 |
| Passe transverse (`PAR-4`, `PAR-12`) | **0,5** | 0 |
| Marge `PAR-11` : un lot neuf audite (le remplissage) | **0,5** | 0 |
| **Total** | **4,0** | **+4** |

**Les reserves que le remplissage ouvrira voyagent dans le 0,5 de la reprise** : c'est le meme
paquet, et `PAR-10` compte par paquet. **La regle du Plan 2 est donc tenue sans poste
supplementaire** - c'est le seul endroit du sprint ou elle est gratuite, et c'est parce que le lot
n'ouvre aucun paquet neuf.

### 6.3 Instance B - 3,5, une ligne, et 0,5 que je n'alloue pas

| Poste | Prix | Lignes |
|---|---|---|
| `EMC-CP-07`, quatrieme passage (section 5.3) | **0,5** | **+1** |
| `cp-emc-regles-de-vie` : `CAT-1`, `REG-R2`, `REG-R3` | **0,5** | 0 |
| `cp-emc-permis-interdit` : `CAT-1`, `PER-R2` | **0,5** | 0 |
| `cp-emc-emotions-autrui` : `EMO-R3` | **0,5** | 0 |
| `cp-emc-formules-de-politesse` : `POL-R3`, `POL-R4` | **0,5** | 0 |
| `cp-emc-exclusion` : `EXC-R1`, `EXC-R5`, `EXC-R6` | **0,5** | 0 |
| Marge non allouee | **0,5** | 0 |
| **Total** | **3,5** | **+1** |
| **Non alloue par moi** | **0,5** | |

**Marge `PAR-11` : 0,0, non due et je ne la prends pas.** B n'audite aucun lot neuf - ses six postes
sont des corrections sur des paquets existants. C'est la meme lecture qu'a s32, ou B n'a pas tire
cette marge pour ce motif exact.

**La marge non allouee de 0,5 n'est pas un confort** : a s32, B a depasse de 0,25 le chiffre de
`S32-EMCCP07` parce qu'*"instruire un refus coute plus cher que constater un paiement"*, et s33 lui
demande **un quatrieme passage** et **une seconde tentative de paiement d'une condition qui a echoue
deux fois**. **Une composition qui laisserait B a 4,0 de postes secs deborderait, et le depassement
serait annonce d'avance dans ce document.**

**Les 0,5 restants : je ne les alloue pas, et je dis ce qu'ils peuvent acheter.** Un paquet du residu
du groupe 2a sur les sciences (`chaine-alimentaire-cm1` ou `signal-information-cm1`), ou un transfert
ecrit vers A ou C au moment ou il est fait (`PAR-9`, clause du 2026-09-08). **Aucun des deux n'achete
une ligne.** Le choix appartient a `sofia-sm` ; il n'appartient pas a moi, et je ne le prends pas.

### 6.4 Instance C - 4,0, quatre lignes

| Poste | Prix | Lignes |
|---|---|---|
| Reprise `EPS-CP-04` : `SEC-R3`, `EPS-7`, `SEC-R1`, `SEC-R2` (section 5.4) | **0,5** | **+1** |
| Reprise `EPS-CP-08` : `JEU-R1` a `JEU-R8`, au tarif de lot (`PAR-14`, section 5.5) | **1,0** | **+1** |
| Lot de l'effort et du corps, `EPS-CP-10` et `EPS-CP-11`, un paquet (section 5.6) | **1,5** | **+2** |
| Reserves du lot, soldees dans le sprint (Plan 2, un paquet neuf) | **0,5** | 0 |
| Marge `PAR-11` : un lot neuf audite | **0,5** | 0 |
| **Total** | **4,0** | **+4** |

**Si le lot du corps est livre en deux paquets et non en un, le poste des reserves passe a 1,0 et C
depasse 4,0.** C'est la seule consequence chiffree de la recommandation de grain de la section 5.6,
et c'est `camille-po` et moi qui la tranchons ensemble.

### 6.5 Le total, et le plafond arithmetique de s33

| Instance | Depense | Lignes |
|---|---|---|
| A | 4,0 | +4 |
| B | 3,5 | +1 |
| C | 4,0 | +4 |
| **Total** | **11,5 sur une borne de 12,0** | **+9** |

> **Plafond arithmetique de s33 sous le Plan 2 : 9 lignes. 66 + 9 = 75 / 138 = 54,3 %.**
>
> **Dette differee creee : 0,5**, par le seul paquet neuf du sprint, **et soldee dans le sprint**
> par le poste de C - c'est la regle du Plan 2 et elle est tenue.
> **Registre de dette a la cloture, si le plafond est atteint : 9,0 - 6,5 payes = 2,5**, plus
> `CONS-3` et `CONS-4` non chiffrees. **Le registre baisse pour la premiere fois depuis s30.**

**Sept des neuf lignes du plafond ne creent aucun paquet neuf.** C'est ce qui distingue s33 de tous
les sprints depuis s29, et c'est exactement la condition que j'avais nommee a s32 pour qu'un
triplement de capacite achete de la couverture.

---

## 7. Mon attente raisonnable, a cote du plafond

### 7.1 Ma calibration passee, verifiee avant de m'en servir

| Sprint | Mon attente | Plafond | Resultat |
|---|---|---|---|
| s29 | 7 a 8 | 9 | **9** - au-dessus de ma fourchette |
| s30 | 3 a 4 | 4 | **4** - haut de ma fourchette |
| s31 | 5 a 7 | 8 | **7** - haut de ma fourchette |
| s32 | 6 a 7 | 8 | **3** - **sous la borne basse, pour la premiere fois en quatre** |

**Le manque de s32 a une cause nommable, et elle gouverne s33** : son plafond reposait, pour cinq
lignes sur huit, sur des **premiers passages** - deux sur un terrain a conditions inedites, trois
sur des items dont l'exactitude n'a ete verifiee qu'a l'audit. **Quatre des cinq ont ete refusees.**

**La population de s33 est presque l'inverse** : **cinq des neuf lignes sont des reprises dont j'ai
ecrit le motif item par item**, et quatre sont des premiers passages (deux remplissages, deux lignes
du corps). **Je ne descends donc pas tout mon bareme d'un cran : je l'applique poste par poste.**

### 7.2 Ligne par ligne

| Poste | Plafond | Attente | Motif |
|---|---|---|---|
| `HG-CM1-02` | 1 | **1** | La formulation juste **existe mot pour mot dans le meme fichier** (item 66). C'est le motif de refus le plus actionnable que j'aie ecrit |
| `HG-CM1-04` | 1 | **1** | Deux directions nommees, et le quatrieme mouvement a servir nomme. Conditions `HIS-1` a `HIS-3` eprouvees quatre sprints |
| `HG-CM1-08` | 1 | **0 a 1** | Remplissage sur conditions eprouvees, mais `FER-1` mord sur *"ce que Napoleon y change"*, et **les deux refus de s32 portaient sur l'exactitude, qu'aucune condition n'attrape** |
| `HG-CM1-12` | 1 | **0 a 1** | **La ligne que j'attends de manquer** : six cellules libres pour neuf items, une cellule interdite parce que depensee sur une ligne fermee, et un element de libelle sans aucun precedent |
| `EMC-CP-07` | 1 | **0 a 1** | **Quatrieme passage, zero sur trois.** Ma lecture du libelle est neuve et **retire** une exigence, ce qui joue en faveur du lot ; trois echecs consecutifs jouent contre |
| `EPS-CP-04` | 1 | **0 a 1** | Motif ecrit item par item, mais c'est le **premier contact d'`EPS-4` et d'`EPS-7`** avec du contenu reel, et `FAB-1c` sur la branche registre est la propriete qu'aucun automate ne mord |
| `EPS-CP-08` | 1 | **0 a 1** | **Six items sur onze reecrits**, dont un qui enseigne quelque chose de faux : c'est un second premier passage, et c'est ce que `PAR-14` dit |
| `EPS-CP-10`, `EPS-CP-11` | 2 | **0 a 1** | Premier contact d'`EPS-2`, d'`EPS-3` et de la frontiere corps-et-effort. **Le seul taux mesure sur l'EPS au premier contact est 0 sur 2** |
| **Total** | **9** | **3 a 7** | |

### 7.3 Le chiffre que je donne

> **Attente raisonnable : 4 a 6 lignes sur un plafond de 9.** **70 a 72 / 138, soit 50,7 % a
> 52,2 %.**

**Pourquoi 4 a 6 et non 3 a 7** : la somme des extremes d'une table n'est pas une prevision. Les
neuf lignes ne sont pas independantes - trois developpeurs travaillent en parallele, et un sprint qui
va mal va mal sur plusieurs postes a la fois -, mais **deux lignes de la table portent une attente de
1 et non un intervalle** (`HG-CM1-02`, `HG-CM1-04`), et elles suffisent a ce que la borne basse ne
soit pas 0.

**Ce que je dis du fait que ce chiffre franchit 50 pour cent** : il le franchit **dans les deux
bornes de ma fourchette**, et ce serait la premiere fois que le denominateur atteignable est a
moitie couvert. **Je ne le presente pas comme un objectif** : c'est une consequence arithmetique du
vivier, et le vivier est fait pour plus de la moitie de lots que le depot a deja payes une fois.

**Et le fait qui doit accompagner ce chiffre, sans etre adouci** : si s33 se comporte comme s32 - le
resultat sous la borne basse de mon attente - **le sprint ferme 3 lignes, la couverture passe a 69 /
138 = 50,0 %, et le registre de dette redescend quand meme a 2,5**, parce que 6,5 des 9,0 payes ne
dependent pas de la fermeture d'une ligne. **C'est la seule chose que le Plan 2 garantit, et c'est
pour cela qu'il a ete retenu.**
---

## 8. Ce qu'il reste pour s34 - le vivier apres s33, et ce que le Plan 2 arbitre exactement

**La question m'est posee parce que j'avais ecrit que ce terrain est epuise fin s33.** Ma correction
de la section 2.1 change la reponse, et je la donne dans les deux cas.

### 8.1 Si s33 atteint son plafond de 9 lignes

**Le vivier sans paquet neuf tombe a zero.** Les sept lignes du vivier sont consommees, les deux
lignes du corps ferment, et il ne reste **aucune ligne `en cours` dans le depot** - ce serait une
premiere.

**Ce qui reste alors a s34, et chaque poste exige un paquet neuf :**

| Terrain | Lignes ouvrables | Tarif du lot | Conditions eprouvees ? |
|---|---|---|---|
| Sciences et technologie CM1 | **9** sur les 15 `non couvert` (mon compte de s32, inchange, aucun lot n'y a touche) | 1,5 | **oui**, quatre lignes fermees |
| EMC CM1 | 7 | 1,5 | **oui**, deux lignes fermees a s29 |
| EMC CP (`04`, `08`, `09`, `10`, `13`, `14`) | 6 | 1,5 | **oui**, cinq lignes fermees |
| EPS CM1 (`09`, `10`) | 2 | 1,0 / 1,5 | **oui apres s33**, si les lignes du corps ont ferme |
| Anglais CM1 (`05`, `06`) | 2 | 1,5 | oui |
| Arts plastiques CP | 2 | 1,0 | **non** au-dela d'`ART-1` |
| Education musicale CP | 2 | 1,0 | **non** |
| Histoire des arts CM1, la seule ligne sans image | 1 | 1,5 | **non** |

> **A s34, chaque ligne fermee porte a nouveau ses 0,5 de dette differee, et le depot est revenu au
> marche de s29 - a capacite triple, donc a dette triple.** C'est ce que j'ai ecrit a s32 ; ce qui
> change est le motif. **Ce n'est pas que le vivier soit epuisable par nature** : c'est que le
> vivier de s33 est ce que s32 a rate, et qu'un vivier alimente par les echecs du sprint precedent
> n'est pas une ressource, c'est un arriere.

### 8.2 Si s33 se comporte comme s32 - et c'est le cas que le Plan 2 arbitre

**Le vivier de s34 est exactement ce que s33 n'aura pas ferme : 3 a 5 lignes**, sur des paquets qui
existeront. **Elles seront gratuites en items et payantes une deuxieme fois en audit** - c'est la
section 2.1 appliquee au sprint suivant.

> **C'est cela que le Plan 2 arbitre, et c'est pour cela qu'il a ete retenu contre le Plan 1.** Le
> Plan 1 achetait des lignes de plafond en laissant croitre la dette differee ; le Plan 2 refuse de
> consommer le vivier pour un chiffre de couverture **et paie la dette dans le sprint qui l'ouvre**.
> A s33, les deux objectifs ne s'opposent pas : **la composition de la section 6 est a la fois celle
> qui ferme le plus de lignes que la borne permette et celle qui solde 6,5 des 9,0 du registre.**
> Cette coincidence est propre a s33 et elle vient du vivier ; **elle ne se reproduira pas a s34.**

### 8.3 Le palier 2 - ce qu'il coute, ce qu'il peut rendre a s33, et pourquoi je ne le finance pas ici

**Il est finance a son entree minimale par l'utilisatrice** (`DEC-0083`, section 13 : `QLM-CP-11` +
`QLM-CP-14`, 2,0 pour deux lignes), avec trois stories au backlog de s33. **Je ne rouvre pas cet
arbitrage.** Je dis ce que je sais et ce que je ne sais pas.

**Ce que j'avais chiffre, et le doute que j'avais nomme** : base 1,0 par ligne dont je suis la source,
plus **1,0 de supplement image** pour le lot - la passe image effacee (`ZON-1` a `ZON-3`) et la passe
jouee zone par zone (`ZON-9`) -, **soit 2,0 pour les deux lignes**. Et j'avais ecrit que **je ne
savais pas si mon forfait de +1,0 tient a six dessins.** *Je ne le sais toujours pas.* Le lot en
porte trois (un plan de classe, deux frises) d'apres la story ; **mon doute portait sur six et il ne
se transpose pas a trois par une regle de trois** - une passe jouee zone par zone se compte en
**zones**, pas en dessins, et le nombre de zones n'est pas ecrit.

**Ce qu'il peut rendre a s33 : zero ligne, selon ses propres prealables.** `S33-PAL2-1` porte **cinq
prealables bloquants nommes** - les lots de developpement `L-a`, `L-c` et `L-d`, plus les deux trous
de contrat `S33-PAL2-TROU-A` et `-TROU-B` - et `L-c` est decrit par `theo-archi` comme **indivisible,
un seul developpeur, un sprint entier**. Le contenu ne peut donc etre ecrit qu'apres `L-c`, servi
qu'apres `L-d`, et audite qu'apres cela. **Je n'ai jamais vu ce depot enchainer trois maillons de
cette taille dans un sprint, et je n'ai aucune mesure qui dise qu'il le peut.**

> **Ces trois stories ne contredisent pas la composition de la section 6 : elles ne la touchent
> pas.** Mon budget n'est pas ce qui les bloque - **je n'y depense rien tant qu'il n'y a rien a
> jouer**, et 68 entrees de catalogue sans une seule `designation` disent qu'il n'y a rien a jouer
> depuis cinq sprints.
>
> **Ce qu'elles consomment et que je ne chiffre pas** : `L-c` prend **un developpeur pour le sprint
> entier**. Ce n'est pas mon ressort et je ne l'estime pas. **Mais je nomme la consequence
> arithmetique plutot que de la laisser decouvrir a la cloture : mon plafond de 9 lignes suppose
> trois producteurs de contenu pour trois instances d'audit. Sous `L-c`, il en reste deux.** C'est a
> `sofia-sm` de dire si le plafond tient dans ce cas ; je dis seulement de quoi il depend.

---

## 9. Ce que je refuse d'avance, et ou cela cassera d'abord

**Chaque refus porte l'endroit ou il se realisera en premier**, pour qu'il soit evitable plutot que
constate.

### 9.1 Un motif d'ordre, qui n'est pas un refus, et je le distingue expressement

**La decision de grain de `CONS-4` est due avant le remplissage, pas apres.** La consigne de
`bandit-manchot-histoire-geographie-cm1` est passee de 12 mots a 22 puis a 31, une clause par sprint,
et **neuf de mes vingt manches portent une tache qu'aucune des cinq clauses ne nomme** (section 3.6).
Le remplissage ajoute 18 items dont la tache dominante - *dire ce que Napoleon change*, *decrire
comment on se loge et on se deplace* - n'est nommee par aucune clause. **Une sixieme clause
porterait la consigne au-dela de 35 mots.**

> **Ce n'est pas un motif de refus** (`CONS-1` est non bloquante, section 2.3) **et je ne le
> transforme pas en critere de Done.** C'est une decision que `ADR-0081` confie a `camille-po` et a
> moi ensemble, et **elle est moins chere avant les 18 items qu'apres.** Si elle arrive apres, elle
> sera prise sur un paquet de 110 items au lieu de 92.

### 9.2 La frontiere `HG-CM1-11` / `HG-CM1-12`, ecrite avant le premier item

**Elle est de moi, elle est opposable, et elle ne gouverne que les items neufs** - `HG-CM1-11` est
fermee et je ne la rouvre pas.

> **`HIS-5` - nouvelle, datee du 2026-09-09.**
>
> - **`HG-CM1-11` porte l'identification et la distinction d'un type d'espace** : nommer l'espace a
>   partir d'une description, ou donner une caracteristique qui le distingue d'un autre. **Y compris
>   les items deja fermes qui portent le logement, le deplacement et l'activite dominante d'un espace
>   periurbain ou rural : ils y restent, ils sont comptes, et ils ne bougent pas.**
> - **`HG-CM1-12` porte les quatre pratiques - se loger, travailler, se cultiver, se deplacer -
>   dans l'espace urbain et dans l'espace touristique**, l'espace etant **donne par l'enonce** et non
>   demande.
> - **La cellule *se loger dans un espace urbain* est depensee** : elle est servie par l'item 25,
>   compte sur `HG-CM1-11`. **Aucun item neuf de `HG-CM1-12` ne la reprend** (`ADR-0081`, clause 5).
>
> **Motif** : trois lignes de cette matiere portent des objets voisins dans un paquet unique, et un
> item compte sur une ligne seulement. **Sans cette frontiere ecrite, `HG-CM1-12` fermerait sur des
> connaissances que `HG-CM1-11` sert deja, et la matrice compterait deux lignes pour une
> competence.** C'est la meme famille de defaut que la collision corps-et-effort de s32, et c'est
> pourquoi je l'ecris de la meme facon : avant le premier item.

### 9.3 La table des refus

| # | Ce que je refuse | Ou cela cassera d'abord |
|---|---|---|
| 1 | **Tout item neuf de `HG-CM1-12` qui compte sur une cellule deja servie par `HG-CM1-11`** (`HIS-5`) | Sur *se loger dans un espace urbain*. L'item existe deja, item 25 du meme fichier, sur une ligne **fermee** : le lot ne le verra pas en lisant sa propre ligne |
| 2 | **Toute fermeture de `HG-CM1-12` dont les items neufs ne servent pas les quatre pratiques du libelle en bonne reponse** (`FER-1`) | Sur *on se cultive*, qui n'a **aucun contenu dans le paquet** : c'est celle des quatre que le lot oubliera |
| 3 | **Toute fermeture de `HG-CM1-08` sans item sur *ce que Napoleon Bonaparte y change*** (`FER-1`, qui s'applique par le *et* du libelle comme sur `HG-CM1-04`) | Des le remplissage : neuf items de plus sur le passage de la Revolution a l'Empire est la pente naturelle, et elle ne sert que la moitie du libelle |
| 4 | **Tout item ajoute a `cm1-histoire-geographie` qui porterait la mediane de charge de lecture du paquet au-dela de ce qu'elle est aujourd'hui** (`HG-R4`) | Des le remplissage. L'unique item de `HG-CM1-08` fait **27 mots** et sert de modele aux neuf suivants. **Dix-huit items neufs sur un paquet de 92 peuvent l'alourdir sans qu'aucune ligne ne rougisse** |
| 5 | **Toute reprise de `EPS-CP-04` qui satisfait `EPS-4` sur les propositions et laisse les indices nommer le meme champ lexical** (`EPS-7`) | Sur l'item 9 : la bonne reponse est *"pour porter secours en cas de danger"* et l'indice dit *"Un danger dans l'eau peut survenir tres vite"*. **Trois propositions qui parlent toutes de danger et un indice qui dit *danger* designent la bonne** |
| 6 | **Tout quatrieme passage sur `EMC-CP-07` qui corrige l'item 4 sans retirer *une loi* des propositions de la tache A** (`EMC-11`) | Sur les items 5 et 6, qui portent le **meme ternaire non disjoint** et que le troisieme refus ne nomme pas. Une correction item par item laisserait le defaut dormir a cote |
| 7 | **Toute fermeture d'une ligne d'EPS CP sans sa mention de couverture partielle**, reprise mot pour mot de la matrice : *"connaissances qui entourent la pratique physique seules ; aucune activite physique, aucun geste et aucune performance ne sont servis par ce produit"* | A la cloture, sur les quatre lignes d'EPS CP a la fois. **La mention est pre-ecrite dans la matrice et je refuse la ligne sans elle** |
| 8 | **Tout item du lot du corps qui compare des corps, des performances ou des capacites entre enfants, ou qui presente une performance chiffree comme une norme** (`EPS-2`) | Sur `EPS-CP-10` : *"au bout de combien de minutes est-on essouffle ?"* est la pente naturelle de la ligne, n'a pas de reponse et n'en aura jamais |
| 9 | **Tout item d'EPS dont la bonne reponse est une attitude, une emotion ou une conduite morale** (`EPS-3`) | Sur `EPS-CP-11` : *"que dois-tu faire si tu as soif pendant le sport ?"* fait porter une conduite par une bonne reponse. La ligne porte **ce que l'hydratation fait au corps**, pas ce que l'enfant doit faire |
| 10 | **Tout item d'EPS portant sur la regle d'un jeu dont la regle n'est ni rappelee dans l'enonce ni universellement scolaire** (`EPS-1`) | Sur la reprise de `EPS-CP-08` elle-meme : c'est **deja** un des motifs du refus, sur *le loup et les moutons*, et une reecriture qui change de jeu sans rappeler sa regle refera la meme faute |
| 11 | **Tout indice d'EPS qui decrit la dynamique d'un jeu ou d'un effort par une image** (`EPS-6`) | Sur le lot du corps : *"le coeur s'emballe"*, *"les jambes deviennent lourdes"*. **Au CP l'indice est lu au premier degre**, et il est le seul texte que lit l'enfant qui vient d'echouer |
| 12 | **Toute fermeture d'une ligne d'EPS, de sciences CM1 ou de questionner le monde CP sur l'activite physique et le corps sans que la frontiere corps-et-effort soit ecrite dans la specification de son lot, avant le premier item** | **Sur le lot du corps de s33, et c'est le sprint ou ce refus mord.** Il a ete pose le 2026-09-08, repris par `DEC-0083` section 9.2, et **rien ne l'a paye a s32** : aucun lot n'a touche ces lignes |
| 13 | **Tout item de sciences CM1 calculant une vitesse** compte sur la ligne du mouvement | Elle compte sur les mathematiques CM1 (`ADR-0081`, clause 5). **Refus reconduit de s31 et de s32, inchange**, et aucun lot de sciences n'est finance a s33 : il attend s34 |
| 14 | **Tout Done pedagogique sur un lot que je n'ai pas joue**, et tout reaudit compte comme gratuit hors de la marge | A la cloture, si un sprint deborde. Un reaudit se paie ; s'il n'y a plus de budget, la ligne ne ferme pas - elle ne ferme pas *quand meme*. **Refus reconduit de s31 et de s32** |
| 15 | **Tout enonce de CP de plus de 15 mots, ou de plus de deux phrases** (`LON-1`, `LON-2`) | Sur les onze enonces neufs du lot du corps, et sur les six items reecrits de `EPS-CP-08`. **Je cesse d'en etre la garde - `ADR-0089` les execute - mais ils restent un motif de refus**, et une reecriture faite pour tenir 15 mots a deja casse trois referents a s32 (section 10.2) |
| 16 | **Tout lot de designation audite avant que `DesignationVerdict` ne porte l'enonce de l'item joue**, et **tout ecran de fin de partie de designation qui montrerait la zone attendue**, par son identifiant ou par son libelle | A la fin de la premiere partie de designation jouee. **Refus reconduit de s32, et il est desormais porte par deux stories** (`S33-PAL2-TROU-A`, `-TROU-B`) |
| 17 | **Tout dispositif qui verifierait ce qu'un adulte a dit a l'enfant** | Refuse d'avance depuis le 2026-09-01, motif ecrit, et le lot du corps le rendra tentant : *"demande a un adulte de te montrer l'echauffement"* n'est pas un item |

**Les proprietes de fabrication restent opposables telles quelles et aucune n'est amendee ici** :
`PLA-1`, `FAB-1a`, `FAB-1b`, `FAB-1c`, `BIN-1`, `CAT-1`, `CYC-1`, `EMC-5`, `HIS-1` a `HIS-4`,
`FER-1` a `FER-3`, `EPS-1` a `EPS-6`, `BIA-1` a `BIA-5`, `EXA-1`.

**`FAB-1c` mordra d'abord sur le lot du corps** : *"il faut boire de l'eau"* et *"il faut
s'echauffer"* sont deux amorces qui signeraient la bonne reponse sur tout un paquet. **L'equipe a
evite ces amorces a s32 et le defaut est arrive par le registre** ; les deux branches sont
desormais nommees, la seconde par `EPS-4`.

---

## 10. Mes conditions opposables sur chaque reprise, et les deux corrections a ma methode

### 10.1 Ce que chaque reprise doit satisfaire pour que je rouvre le Done

| Reprise | Ce qui la leve | Ce qui reste opposable et ne se redemande pas |
|---|---|---|
| `HG-CM1-02` | `HG-R11` : les items 70 et 71 portent la formulation de l'item 66 du meme fichier. `HG-R9` : les cinq indices d'ordre portent la methode et non la date de la bonne reponse | `FER-1`, `FER-2`, `FER-3` sont **deja tenues** et je ne les redemande pas ; je les reverifie et je ne les rediscute pas |
| `HG-CM1-04` | `HG-R12` : aucune bonne reponse ne designe un groupe humain par un exonyme qu'aucun groupe ne revendique, **par l'une des deux directions deja nommees**, et je n'en demande pas une troisieme. `HG-R10` : l'indice de l'item 87 cesse d'attribuer a 481 l'unification | idem. **Le plafond de trois items de la tache de nomination n'est pas structurel** : un quatrieme mouvement s'enseigne |
| `HG-CM1-08` | `FER-2` a dix items ; `FER-1` sur les deux moities du libelle ; `HG-R4` sur la mediane du paquet ; `HIS-1` recomptee sur le fichier entier apres 18 items neufs | `HIS-2`, `HIS-3` famille (b) : **je ne signe aucun fait date, dispute ou dont la formulation engage une couverture** sans source ou sans reformulation qui retire le besoin de source |
| `HG-CM1-12` | `FER-2` ; `FER-1` sur les quatre pratiques ; **`HIS-5`** ; `HG-R4` | idem |
| `EMC-CP-07` | **`EMC-11`** ; `EMC-5` sur les onze items ; `FAB-1c` **reste** payee sur la tache B ; `FER-3` recomptee apres le changement de tache A ; `PLA-1` sur le distracteur neuf de l'item 11 | `LON-1` et `LON-2`, desormais executees par `ADR-0089` |
| `EPS-CP-04` | `SEC-R3` : les trois propositions des items 8, 9, 10 et 11 nomment un danger, une blessure ou une consequence, dont deux fausses. **`EPS-7`** sur les quatre indices. `SEC-R1` : les items 3 et 8 portent une activite physique ou un lieu de pratique (`EPS-5`). `SEC-R2` : *mettre de la creme solaire* cesse d'etre une reponse defendable ecartee | `EPS-1`, `EPS-2`, `EPS-3`, `EPS-6`, `FER-2`, `FER-3`, la mention de couverture partielle |
| `EPS-CP-08` | `JEU-R1` a `JEU-R6`, un par un, tels que l'instance C les a ecrits ; `JEU-R7` et `JEU-R8` dans la meme reprise puisque le paquet est de toute facon rouvert | idem, plus **`PAR-14`** : ce paquet se paie au tarif d'un lot et son reaudit balaie toutes les conditions |
| `EPS-CP-10`, `EPS-CP-11` | `FER-1`, `FER-2`, `FER-3`, `EPS-1` a `EPS-7`, `FAB-1c` sur les deux branches, `LON-1`, `LON-2`, la frontiere corps-et-effort **ecrite dans la specification avant le premier item**, la mention de couverture partielle | - |

### 10.2 Deux corrections a ma methode que s32 a mesurees, et ce qu'elles changent a s33

**1. Ma definition litterale de la phrase etait fausse, et la barre a cesse d'etre a moi.** *"Un
segment termine par un point, un point d'interrogation ou un point d'exclamation"* coupait
`"0 + ? = 10"` en deux et rendait **22 enonces servis rouges**. `ADR-0089` execute desormais `LON-1`
et `LON-2` avec un critere **derive** - un `.`, `?` ou `!` qui termine le texte ou qu'une majuscule
suit - **0 fautif contre 22**, sur 528 enonces de CP et 36 paquets, avec un cliquet a egalite sur
trois paquets.

> **Je l'ai adoptee pour tous mes comptages et je cesse d'etre la garde de `LON-1` et `LON-2`.**
> C'est **la premiere de mes barres de contenu qui devient mordable**, et le sprint a mesure ce que
> son absence coutait. **Ce que cela change a s33** : les onze enonces neufs du lot du corps et les
> six items reecrits de `EPS-CP-08` sont mordus par un test, pas par mon comptage - **et je ne
> recompte pas a la main ce qu'un garde-fou compte mieux que moi.**

**2. Ma barre a produit du francais incorrect, et elle est necessaire sans jamais etre
suffisante.** Un partitif retire pour tenir 15 mots ; corrige ; **et deux autres referents casses
par la meme compression** trouves en relisant, avec une cause unique nommee - une subordonnee
retiree sans reconstruire la phrase autour.

> **Ce que cela change a s33, et c'est un refus deja dans la table** (numero 15) : le lot du corps
> ecrit onze enonces neufs **au CP**, et la reprise de `EPS-CP-08` en reecrit six. **C'est le sprint
> ou cette cause peut se reproduire trois fois.** `EPS-7` et `SEC-R3` demandent par ailleurs de
> reecrire douze propositions et quatre indices de CP : **chaque reecriture faite pour tenir une
> barre est un endroit ou un referent peut casser.**
>
> **Et je nomme le partage** : la longueur est a `ADR-0089`, l'orthographe, l'accentuation et la
> ponctuation sont au `relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`),
> **et le referent casse n'est ni l'un ni l'autre** - c'est du sens, donc c'est a moi, et aucun
> automate ne le mord.

---

## 11. Ce dont je reste la seule garde, et ce qui est devenu mordable

**Dit explicitement pour que l'equipe ne croie pas couvert ce qui ne l'est pas.**

| Propriete | Qui la tient | Mordable par un automate ? |
|---|---|---|
| **`HIS-5`** - la frontiere `HG-CM1-11` / `HG-CM1-12` | **moi seule** | **non.** Un garde-fou lit la ligne qu'un item **declare**, jamais la competence qu'il sert. **C'est la propriete neuve la plus dangereuse de s33** |
| **La frontiere corps-et-effort** entre EPS, sciences CM1 et questionner le monde CP | **moi seule** | **non**, meme motif. Elle n'a pas encore rencontre de contenu et s33 est le sprint ou elle en rencontrera |
| **`EMC-11`** - la tache A de `EMC-CP-07` ne met pas en concurrence deux categories que le libelle ne demande pas de distinguer | **moi seule** | **non** |
| **`EPS-4`, `EPS-7`** - ni les propositions ni l'indice ne font du champ lexical du danger le discriminant | **moi seule** | **non**, et c'est un jugement de registre qu'aucun automate ne rend |
| **`CAT-1`** - la bonne reponse ne se reconnait pas a son ton | **moi seule**, et **son paiement a echoue deux fois** | **non.** s32 a ajoute un fait : **le repere de registre peut etre remplace par un repere lexical** sans qu'aucun test bouge - *maitresse* de 1 a 7 occurrences, dans aucune bonne reponse |
| **`FAB-1c` branche registre** | **moi seule** | **non.** C'est le motif du refus de `EPS-CP-04` : **sans moi, cette ligne fermait** |
| **`EXA-1` et l'exactitude factuelle** - chaque enonce, chaque bonne reponse, chaque distracteur, chaque indice | **moi seule** | **non**, et `EXA-1` ne la rend pas mordable : elle dit ce qu'on fait d'une inexactitude, pas comment on la trouve. **Trois des quatre refus de s32 en sont la preuve : les tests des lots etaient verts** |
| **`PLA-1`** - chaque proposition est un candidat serieux | **moi seule** | **non.** La plausibilite pour un enfant de six ou de neuf ans ne se calcule pas |
| **`EMC-5`** - aucune proposition vraie n'est marquee fausse | **moi seule** | **non.** Les quatre jobs sont verts sur l'arbre ou l'item 4 de `regle-souhait-loi` dit quelque chose de faux, et ou `EXC-R5` traverse un deuxieme sprint |
| **`HIS-2`** - aucun item a deux reponses defendables | **moi seule** | non |
| **`HIS-3` famille (b)** - une formulation disputee | **moi seule.** C'est ce qui a refuse `HG-CM1-04` | non |
| **Qu'un pronom de proposition ait un antecedent dans son enonce** | **moi seule** (`PER-R2`, `JEU-R5`). **Quatre occurrences en un sprint** | non |
| **Qu'une question et ses propositions forment une phrase correcte recollees** | **moi seule** (`PER-R1` payee, `POL-R4` ouverte) | non |
| **Le genre de l'adulte d'ecole dans la serie des paquets d'une matiere** (`REG-R3`, `BIA-1`) | **moi seule.** 17 contre 1 sur l'EMC CP entier | **mesurable** - un comptage de mots sur les paquets d'une matiere - **mais je n'ai compte cette population que sur l'EMC CP et je ne pose aucune barre chiffree en cours de sprint** |
| **`CONS-1`, `CONS-4`** - la consigne nomme la tache de son paquet et informe l'enfant | **moi seule**, en jouant, et c'est de portee **D** | **partiellement**, et seulement si le paquet declare la tache de chaque item - **ce qu'il ne fait toujours pas** |
| **`HG-R4`** - la charge de lecture d'un paquet et sa mediane | **moi**, par comptage | **oui**, et 18 items neufs sur un paquet de 92 est le moment de le mordre |
| **`LON-1`, `LON-2`** | **`ADR-0089`. Je cesse d'en etre la garde** | **oui, entierement**, avec une dette declaree de 5 enonces qui sont `EMO-R3`, `EXC-R6` et `POL-R3` |
| **`FAB-1a`, `FAB-1b`, `BIN-1`** | `theo-archi` peut les mordre, routees le 2026-09-07 | **oui** |
| **`FER-2`, `FER-3` sur les paquets d'EPS** | **moi, a la main.** Les tests de paquet a la `PaquetCm1EmcDroitDevoirTest` **n'existent pas pour l'EPS** | **oui**, et l'instance C l'a route a `theo-archi` : *"je les ai comptes a la main sur vingt-deux items ; ce n'est pas une bonne facon de depenser une porte pedagogique"* |
| **`SCO-1` a `SCO-7`** - le score distingue celui qui savait | **personne**, et c'est le probleme. **Mesure une troisieme fois aujourd'hui** | **oui, entierement.** Le backlog l'ecrit : *"Ce qui n'est pas testable par un automate : rien"* |
| **La couleur, le contraste, la taille, la position a l'ecran** | **personne sur mes terrains ce sprint** (audit degrade, section 0) | hors de ma portee |
| **La couture entre deux moities paralleles** | **moi seule**, quatre occurrences en quatre sprints | **non**, sauf la forme (b), qu'un garde-fou d'agregat sur l'arbre assemble me retirerait des mains |

**Un fait de fragilite que je confirme du cote pedagogique, et qui n'est pas mon ressort a traiter** :
`theo-archi` a compte **25 fichiers de test de paquet sur 95 qui lisent l'enonce par une operation
de chaine**, et **deux occurrences neuves ont ete introduites a s32 dans le sprint meme ou une etait
corrigee**. **C'est la premiere fois qu'un garde-fou de ce depot a rendu une de mes reserves
impayable** - un test qui fige la formulation d'un enonce que ma porte demande de reecrire. **s33
demande la reecriture de douze propositions, quatre indices, six items d'EPS et onze enonces
neufs** : c'est le sprint ou cette fragilite coutera le plus.

---

## 12. Ce que ce document ne fait pas

- **Il ne prononce aucun Done pedagogique.** Aucun increment de s33 n'existe.
- **Il ne rejuge aucun verdict de s32.** Les trois fermetures, les quatre refus et le paiement refuse
  de `CAT-1` sont ceux que mes trois instances ont prononces le 2026-09-09 ; ce document les reprend
  et n'en revise aucun. **Il ne rouvre aucune ligne `couvert-valide`**, y compris `HG-CM1-11`, sur
  laquelle il mesure une collision (section 3.1).
- **Il ne rouvre pas le Plan 2** (`DEC-0083`), qui est acquis pour s32 a s34, et **il ne chiffre
  aucune composition alternative.**
- **Il ne touche pas a `squad/programme/MATRICE-couverture.md`.** J'ai repris ses chiffres tels
  qu'ils m'ont ete transmis - 66, 4, 68, 71, denominateur 138, **66 / 138 = 47,8 %** - **et je ne les
  ai pas recalcules.** Le compte appartient a `camille-po`.
- **Il ne redige pas le `DEC`, ne priorise pas et n'ouvre aucun perimetre** (`camille-po`) ; **il ne
  gele rien, n'affecte personne et n'alloue pas les 0,5 non alloues de l'instance B** (`sofia-sm`) ;
  **il n'ecrit aucune story.**
- **Il ne decide pas de l'architecture** : le palier 2, le repertoire de reproductions, le remede de
  `SCO-1` et la fragilite des 25 fichiers de test sont des symptomes que je nomme, des causes que
  `theo-archi` traite.
- **Il ne modifie aucun fichier sous `domain/`, `application/`, `adapters/` ou `bootstrap/`.** J'ai
  lu quatre paquets, un catalogue, un referentiel et un garde-fou, et joue cinq parties completes ;
  je n'y ai rien ecrit. **Je ne corrige aucun contenu.**
- **Il ne corrige ni la documentation technique, ni la Javadoc, ni le nommage, ni l'orthographe,
  l'accentuation ou la ponctuation du texte destine a l'enfant** : c'est le `relecteur-pedagogique`
  (`DEC-0002`, amendement du 2026-08-04 ; `DEC-0056`).
- **Il n'efface aucun verdict et aucun refus.** Le retrait du refus d'avance numero 6 de s32
  (section 2.3) est **ajoute et date** ; le refus reste lisible ou il a ete ecrit.
- **Il ne durcit aucune clause anterieure.** `PAR-0` a `PAR-13`, `CYC-1`, `PLA-1`, `FAB-1`, `BIN-1`,
  `CAT-1`, `EMC-1` a `EMC-10`, `HIS-1` a `HIS-4`, `FER-1` a `FER-3`, `EPS-1` a `EPS-6`, `LON-1`,
  `LON-2`, `EXA-1` sont intactes. **Ce qui est neuf est neuf et date du 2026-09-09** : `PAR-14`,
  `EMC-11`, `EPS-7`, `HIS-5`, et les deux rechiffrages de mes propres prix (sections 5.2 et 5.5).
- **Il ne rechiffre pas s32, ni le groupe 2a, ni les neuf reserves de s31** : `PAR-14` est **non
  retroactive**.
- **Il ne dit rien de ce qu'il n'a pas mesure** : `CONS-3` reste **non chiffree**, le supplement
  d'image du palier 2 reste **incertain** faute de connaitre le nombre de zones, et la capacite de
  developpement n'est pas de mon ressort.

---

## 13. Les identifiants alloues dans ce meme diff (`PAR-5`)

**Quatre identifiants neufs**, tous sur des prefixes deja alloues, **donc aucun prefixe neuf**.
Ligne d'etat courant du registre portee de **578 / 97 a 582 / 97**.

| Identifiant | Ce qu'il designe | Section |
|---|---|---|
| `PAR-14` | Une reprise dont les corrections touchent plus de la moitie des items d'un paquet se paie au tarif d'un lot, non d'un rejeu. Non retroactive | 5.5 |
| `EMC-11` | Sur `EMC-CP-07`, la tache A porte le binaire que le libelle demande ; *une loi* n'y est pas une troisieme proposition | 3.4 |
| `EPS-7` | Aucun indice d'EPS ne nomme le champ lexical qui distingue la bonne reponse ; `EPS-4` porte sur les propositions, `EPS-7` sur l'indice | 3.5 |
| `HIS-5` | La frontiere `HG-CM1-11` / `HG-CM1-12` : identification du type d'espace contre description des quatre pratiques dans un espace donne | 9.2 |

**Methode du comptage, a cote de son chiffre** : comptage des lignes de la table de la section 4 du
registre sous le motif du garde-fou lui-meme (`ALLOCATION`, identifiant et document entre accents
graves, `ADR-0085` clause 5, `ADR-0088` clause D), **avant** mon diff - **578 identifiants
distincts, 97 prefixes distincts, 0 doublon**, ce qui confirme la ligne d'etat courant que
`sofia-sm` a portee a l'assemblage de s32. Plus mes quatre allocations : **582 / 97**.

**Ce que ce total est et ce qu'il n'est pas** : il est juste **sur mon worktree**, qui est le seul de
ce sprint - je travaille seule aujourd'hui, l'arbitrage prealable n'etant pas divise en instances.
**La limite structurelle de `PAR-5` que `theo-archi` a laissee nommee ne me concerne donc pas ici**,
et il n'y aura pas de reparation arithmetique a la fusion pour ce diff.

---

## 14. La porte technique sur mon propre diff - les quatre jobs, nommes un par un

**Je ne deduis aucun job d'un autre, et je ne confonds pas la CI avec `mvn verify`** (`CLAUDE.md`,
amendement du 2026-09-08). Lances dans mon worktree, sur l'arbre que je livre.

| Job de `.github/workflows/ci.yml` | Ce que j'ai lance | Resultat |
|---|---|---|
| `build` | `mvn -B clean verify` sur la racine, par l'enveloppe de toolchain | **vert** - `BUILD SUCCESS`, **1 439 tests**, 0 echec, 0 erreur, 0 ignore. **JDK 21.0.10 rapporte par la machine virtuelle des tests**, et non lu dans l'entree de toolchain (`ADR-0071`) |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `bash .github/scripts/verifier-chemins.sh` | **vert** - *"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."* puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* |
| `outils` | les trois motifs du job (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`), avec la regle du balayage vide | **vert** - **9 juges purs executes**, aucun motif vide |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis mon propre message de commit passe a `verifier-prefixes.sh` | **vert** - *"Tous les commits portent un prefixe d'agent connu."* |

**Le geste que ce depot a paye cher** - lancer la commande de la CI sur son propre diff avant de
commiter - **est fait ici, et les quatre jobs sont nommes plutot que deduits.** Le job `build` a ete
lance **apres** l'ecriture de mes quatre allocations dans `squad/decisions/REGISTRE-identifiants.md`,
donc le garde-fou d'`ADR-0085` a bien juge la ligne d'etat courant a **582 / 97** : c'est lui qui
verifie ce chiffre, pas moi.

**Ce que je n'ai pas ecrit, et le motif** : je ne reprends pas de conclusion dans un `review.md`.
**s33 n'est pas ouvert et son repertoire de sprint n'existe pas** ; `review.md` est un fichier de
ceremonie de `sofia-sm`, et un arbitrage prealable n'a pas de review a alimenter. C'est le meme
regime qu'a s32, ou mon arbitrage prealable ne touchait aucun fichier de ceremonie. **Les
conclusions de ce document sont reprises par `camille-po` dans le `DEC` de strategie de s33, et par
`sofia-sm` dans le planning ; ni l'un ni l'autre n'est mon fichier.**

**Une chose a mon debit sur ce point, et elle illustre exactement l'exigence que `CLAUDE.md`
declare sans qu'aucun `mvn` ne la verifie.** Voulant reverifier le seul garde-fou que mon diff
concerne apres avoir complete cette section, j'ai lance une **mesure partielle** -
`-pl bootstrap -am -Dtest=<un seul test>` - et elle a rendu `BUILD FAILURE`. **L'arbre n'y etait
pour rien** : `-am` construit les modules amont, aucun ne porte ce test, et Surefire arrete le build
pour ce motif. **Un `BUILD FAILURE` cause par mes propres options n'est pas un Done technique
rouge**, mais il aurait pu etre lu comme tel par qui n'aurait vu que la derniere ligne. **Je l'ecris
pour que personne n'ait a le deduire**, et la mesure qui compte est celle du tableau ci-dessus :
`mvn -B clean verify` sur la racine, reacteur complet, relance **apres** cette section, **1 439
tests, 0 echec**.
