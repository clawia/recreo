# s57 - arbitrage de la preparation du palier 3 : lignes CE1 de francais, mathematiques, questionner le monde, EMC et arts plastiques

- **Auteure** : `nadia-prof`, professeure des ecoles, seule autorite sur le Done pedagogique.
- **Date** : 2026-10-19, 20:42 (UTC+02:00), lu par `date`.
- **Arbre** : `round-two` a `ad65827e`, checkout principal. Non suivis avant ecriture : `.claude/settings.local.json`,
  `preparation-palier-3-ce1-camille.md`, `avis-theo-preparation-palier-3-ce1.md`.
- **Type** : arbitrage sur une preparation, **pas l'arbitrage prealable du palier 3** (condition 3 de 11.3). Le palier 3 n'est pas
  ouvert : aucune story des lots, aucune composition, aucun planning. Quand ils existeront, j'ecrirai l'arbitrage prealable par
  instance en reprenant ce document, sans le reecrire.
- **Audit degrade, declare** : rien n'est joue, rien n'est branche. Des douze paquets de s53 je n'ai lu que l'en-tete et les
  premiers items (sections 1 et 3), ce qui suffit a juger une recevabilite, jamais un contenu.
- **Ce que ce document n'est pas** : ni une composition (`camille-po`), ni un planning (`sofia-sm`), ni une reponse de
  l'utilisatrice. Il ne touche ni la matrice, ni `DEC-0120`, ni un paquet, ni le registre.

## Resume

| # | Point | Decision | Section |
|---|---|---|---|
| AR1 | Questionner le monde CE1 | **Aucun lot de questionner le monde CE1 n'est auditable a s57**, par ma regle ecrite le 2026-10-13 (instruction, 5.2). Les paquets peuvent s'ecrire au palier 3 (`Q-01`) ; leur premier audit est a s58 au plus tot | 1.3 |
| AR2 | EMC CE1 | `EMC-CE1-01` a `-04` recevables, plafond `couvert-valide`, paquet ecrit dans le palier | 1.4 |
| AR3 | Mathematiques CE1 | `MAT-CE1-01`, `-10`, `-11` recevables, plafond `en cours`. **`MAT-CE1-15` et `MAT-CE1-14` non recevables sous leur forme textuelle** : leurs paquets violent mes propres conditions | 1.2 |
| AR4 | Francais CE1 | `-06`, `-07`, `-11` recevables (A). `-03`, `-21` : hors de mon accord sur `PAR-2`. `-08`, `-09` : condition 4 maintenue | 1.1 |
| AR5 | Capacite | A 4,0 (pleine en lots). Reste 4,0 : **A' 2,0 et B 2,0, un lot chacun** ; mathematiques CE1 a B, EMC CE1 a A' | 2 |
| AR6 | Ecart dit `E4` (explication de `ce1-relire-une-phrase`) | **Recevable, et obligatoire** : `RLP-CE1-3` n'est pas une option. Plafond `couvert-valide`. Sans effet a s57 | 3.1 |
| AR7 | `Q-05`, `Q-07` a `Q-10` | Avis, sans valeur de reponse | 3.2 |
| AR8 | Conditions de Done par lot | Section 4 | 4 |

---

## 0. Ce que j'ai lu et mesure

| Mesure | Commande ou fichier | Rendu |
|---|---|---|
| Arbre | `git log -1 --format=%h` | `ad65827e` |
| Lu en entier | `preparation-palier-3-ce1-camille.md` ; `avis-theo-preparation-palier-3-ce1.md` ; `arbitrage-nadia-palier-2.md` ; `duree-et-paliers.md` ; `cloture-palier-2.md` sections 13 a 15 ; `ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` | - |
| Relu pour la regle | criteres de s57, sections 1, 2, 4.2 a 4.4, 10, 11 ; s55 instance A, 1.1 ; s48, 2 (`TRV-CE1-5`), 4.1, 4.2, 7 ; s43 lot 1 section 2, lot 2 sections 3 et 4, lot 3 sections 1 et 2 ; `ARBITRAGE-COMPLEMENT-s55-instance-a.md` section 3 ; s45 section 5 (`R6`) ; `DEC-0120` A.32, A.33, A.35, A.114, A.115 | - |
| En-tetes des paquets de s53 | `head -3` et `grep -m4 "enonce:"` sur `ce1-nombres-jusqu-a-1000`, `-masses-contenances`, `-calendrier-dates`, `-figures-planes`, `-deplacements-quadrillage` ; `sed -n 1,60p` sur `ce1-relire-une-phrase` | section 1.2 et 3.1 |
| Explication sans graphie deja servie | pour chaque paquet de `contenu/` qui contient `explication:`, `grep -c "graphie:"` et `grep -c "explication:"` | `cp-rapprocher-deux-oeuvres` : 0 graphie, 6 explications ; `cp-le-bon-son` : 15 et 6 |
| Amendement du garde-fou des explications | `git log -1 6f8895a4` ; Javadoc de `ChaquePaquetExpliqueSesItemsAVariationTest` | 2026-10-09, `theo-archi`, *"explication sans graphie tous ou aucun"* |
| Dessins de figures | `git ls-files \| grep -i "figure-"` | 12 dessins servis, dont `figure-triangle-rectangle.svg` |
| EMC dans s57 | `grep -n "EMC"` sur `planning.md`, `composition-palier-1.md`, `composition-palier-2.md`, `cloture-palier-1.md`, `cloture-palier-2.md` | 0 : aucune instance de s57 ne porte l'EMC |
| Mentions de couverture de `MAT-CE1-10` et `-11` | `grep -n "mot pour mot\|mention"` sur s43 lot 2 | **aucune mention ecrite mot pour mot** : un patron seulement (section 4.3) |

**Deux faits des fichiers que je juge, a corriger** :

- **`theo-archi`** (avis, 1.2, `MAT-CE1-15`) ecrit que les figures du CP existent *"le triangle rectangle non"*. `figure-triangle-rectangle.svg`
  est suivi et servi au CP ; s43 lot 3 (`FIG-CE1-2`) l'autorise *"tel quel"*. Le dessin manque d'autant moins.
- **Le lanceur nomme `E4` un ecart de l'avis de `theo-archi`.** Cet avis ne porte aucun `E1` a `E5` ; le point est son `Q2` (et sa
  ligne `FRA-CE1-09`, 1.1). Dans la preparation de `camille-po`, `E4` est le lot d'`EMC-CE1-04`. Je traite ici `Q2` de `theo-archi`
  sous le nom `E4`, et je designe les lots d'EMC par leur code de ligne.

---

## 1. Recevabilite, ligne par ligne

`Recevable au palier 3` se lit : un lot peut etre audite au palier 3, sous la forme dite, si la capacite (section 2) et l'ordre de
`camille-po` le placent. `Plafond` est le statut le plus haut que mon verdict peut faire atteindre a la ligne.

### 1.1 Francais CE1

| Ligne | Paquet | Recevable au palier 3 | Forme, plafond | Motif |
|---|---|---|---|---|
| `FRA-CE1-06` | `ce1-inference` | **oui**, A | jouable au choix, texte support ; `couvert-valide` | groupe X, criteres deja ecrits (`arbitrage-nadia-palier-2.md` 6.2) |
| `FRA-CE1-07` | `ce1-texte-documentaire` | **oui**, A | idem | idem ; je joue chaque item texte masque |
| `FRA-CE1-11` | `ce1-negation` | **oui**, A | jouable au choix ; `couvert-valide` | idem ; `TRV-CE1-2` recompte |
| `FRA-CE1-03` | `ce1-mots-irreguliers` | **non** | plafond `en cours` (complement s55, section 3) | hors de X et de Y. Mon accord sur `PAR-2` (criteres, 4.2, condition 1) porte sur le decoupage de `DEC-0120` section 1, rien d'autre ; A est pleine en lots. L'etendre a A' demanderait de juger le cousinage du paquet (ma regle de s55, 1.1), que je n'ai pas fait. Je le ferai si `camille-po` me le demande, hors de ce document |
| `FRA-CE1-21` | `ce1-mots-invariables` | **non** | plafond `en cours`, inchange (3.2, `Q-08`, `Q-09`) | meme motif que `-03` |
| `FRA-CE1-08` | `ce1-ordre-des-mots` | **non, a aucun palier de s57** | forme du choix parmi trois phrases ; **plafond `couvert-valide`** (s48, 4.1 : c'est la forme que j'ai retenue, l'en-tete `DEGRADE` le dit lui-meme) | condition 4 (criteres, 4.2 et 11.3) ; avis en 3.2, `Q-07` |
| `FRA-CE1-09` | `ce1-relire-une-phrase` | **non, a aucun palier de s57** | nommer l'erreur parmi trois, **explication qui redonne la phrase juste obligatoire** ; **plafond `couvert-valide`** (s48, 4.2) | condition 4 ; `E4` tranche en 3.1 |
| `FRA-CE1-27` a `-31` | aucun | **non** | `non couvrable en l'etat` | seule une decision de l'utilisatrice les rouvre ; avis sur `-27` et `-29` en 3.2 |

### 1.2 Mathematiques CE1

| Ligne | Paquet | Recevable au palier 3 | Forme, plafond | Motif |
|---|---|---|---|---|
| `MAT-CE1-01` | `ce1-nombres-jusqu-a-1000` | **oui** | textuel ; plafond `en cours`, mention `NUM-CE1-2` mot pour mot | l'en-tete (*"seuls lire, ecrire et decomposer"*) decrit ce que la mention dit servir |
| `MAT-CE1-10` | `ce1-masses-contenances` | **oui** | textuel ; plafond `en cours`, mention ecrite en 4.3 | l'en-tete (*"comparer"* et les trois unites, *"mesurer"* non servi) correspond a mon verdict de s43, 3 |
| `MAT-CE1-11` | `ce1-calendrier-dates` | **oui** | textuel, date seule ; plafond `en cours`, mention ecrite en 4.3 | l'en-tete (date seule, ni cadran ni mois pour eux-memes) correspond a `CAL-CE1-1` |
| `MAT-CE1-15` | `ce1-figures-planes` | **non sous sa forme textuelle** ; oui illustre | plafond `en cours` **sur des figures dessinees** | ma mention fixee d'avance (s43 lot 3, 2) dit *"reconnaitre le triangle rectangle et decrire des figures planes **dessinees**"*, et `FIG-CE1-3` veut l'angle droit *"visiblement droit sur le dessin"*. L'en-tete dit *"aucune figure n'est dessinee"* et un item demande *"Combien d'angles droits a un carre ?"* : un refus ecrit d'avance. Les douze dessins existent : la forme illustree est un lot illustre, **1,5** a mon bareme |
| `MAT-CE1-14` | `ce1-deplacements-quadrillage` | **non** | - | `DEC-0120` A.35, maintenu par l'utilisatrice, la laisse conditionnee a `A3` d'`ADR-0080` ; et le paquet textuel viole `DEP-CE1-2` (avis en 3.2, `Q-05`) |
| `MAT-CE1-02`, `-09` | servis, `en cours` | **non** | plafond deja atteint | un dessin (ligne graduee, regle) ; aucun dessin n'est engage (`Q-06` sans reponse) |

### 1.3 Questionner le monde CE1 : aucun lot audite a s57

J'ai ecrit le 2026-10-13 (instruction, 5.2, troisieme point) : *"Un premier lot de questionner le monde CE1 releve de ma regle de s55
sans l'amender : il ne prend pas le sprint ou une autre instance audite un paquet sans cousine."* s57 est ce sprint : B y a audite
le rejeu d'`ART-CP-10` au palier 1, et je l'ai nomme moi-meme, dans le meme paragraphe, *"un terrain sans cousine"*. C'est le meme
motif, et la meme portee, que la condition 4 sur `-08` et `-09` (*"quelle que soit sa duree"*). **Je ne deplace pas ce poteau** :
ni la preparation de `camille-po` ni l'avis de `theo-archi` ne l'ont lu, et la reponse `Q-01` de l'utilisatrice porte sur le moment
ou les paquets s'ecrivent, pas sur leur audit.

| Ligne | Recevabilite pedagogique, toutes deux sous l'instruction du 2026-10-13 | A s57 |
|---|---|---|
| `QLM-CE1-01` a `-06`, `-08`, `-10`, `-12` | recevables, jouables au choix textuel, plafond `couvert-valide` | **paquets ecrivables au palier 3 ; aucun audit** |
| `QLM-CE1-07` | moitie textuelle recevable, plafond `en cours`, mention de 3.7 | idem |
| `QLM-CE1-09`, `-11` | en attente d'un dessin, terrains neufs (`PAR-13`) | non : aucun dessin, et aucun terrain neuf tant qu'A' existe (condition 5) |

**Consequence pour `Q-01`** : ecrire les dix paquets au palier 3 est permis ; ils attendront s58. Ce qui est ecrit maintenant et
audite plus tard sera audite **sur le commit joue**, avec la ligne de lecture complete (4.1, point 2) : un paquet ecrit tot n'obtient
aucun credit d'audit.

### 1.4 EMC CE1

| Ligne | Recevable au palier 3 | Forme, plafond |
|---|---|---|
| `EMC-CE1-01` a `-04` | **oui**, un paquet ecrit dans le palier (`Q-01`), une story avant mon arbitrage prealable | jouable au choix textuel ; `couvert-valide` |
| `EMC-CE1-05` | non | `non couvrable en l'etat`, confirme le 2026-10-13 |

Ni terrain neuf (instruction, 5.1) ni ligne sans cousine : ma regle de s55 ne la touche pas. **`PAR-2`** (instruction, 5.3) : l'EMC CE1
va a l'instance qui porte l'EMC. Aucune instance de s57 ne la porte (section 0) ; **celle qui recoit le premier lot d'EMC CE1 devient
la seule a porter l'EMC, tous niveaux, pour le reste de s57.**

**Ordre interne, mon avis pedagogique** (l'ordre est a `camille-po`) : `-01`, `-02`, `-03`, `-04`. `-01` d'abord parce que son
recouvrement avec le CP est celui que j'ai trouve en lisant les items (instruction, 1.2, *ranger*) : il me dit, sur le premier paquet,
si `NRC-CE1-1` tient en EMC. `-04` en dernier : *"une seule lecture defendable"* (`INT-CE1-1`) est la condition la plus difficile a
ecrire.

### 1.5 Arts plastiques CE1

`ART-CE1-01`, `-02` : **non**. Instruction non faite, corpus absent, terrains neufs (`PAR-13`, condition 5). Avis en 3.2, `Q-10`.

### 1.6 Bilan pour le palier 3

| Recevable au palier 3 | Lignes | Plafond |
|---|---|---|
| A, deja engagees | `FRA-CE1-06`, `-07`, `-11` | `couvert-valide` |
| oui | `MAT-CE1-01`, `-10`, `-11` | `en cours` |
| oui, sous forme illustree seulement, 1,5 | `MAT-CE1-15` | `en cours` |
| oui | `EMC-CE1-01` a `-04` | `couvert-valide` |
| non a s57 | les 27 autres des 38 de `camille-po` | - |

---

## 2. Capacite par instance

**Bornes, inchangees** (criteres, 11.3) : 8,0 pour le palier (`PAR-9`) ; 5,0 et trois lots au plus par instance (`PAR-8`) ; arbitrage
prealable 0,5 par instance ouverte ; marge de rejeu 0,5 par instance qui porte un lot ou un rejeu, allouee a aucun lot ; lot neuf
textuel 1,0, lot illustre 1,5. Aucun chiffre au-dela de 8,0. Les restes du palier 2 sont tombes (`DEC-0120` A.114). Les stories hors
`PAR` (`Z1`, `Z2`, `RL5` a `RL7`, `S57-GARDE-NOMBRE-DE-MANCHES`, `S57-GARDE-PILE-DE-POLICES`) valent 0 et ne prennent aucune place
de lot.

### 2.1 L'engage

| Instance | Postes | Somme | Lots |
|---|---|---|---|
| A | arbitrage 0,5 ; marge 0,5 ; `-06`, `-07`, `-11` 3,0 | **4,0** sur 5,0 | **3 sur 3 : pleine en lots** ; le 1,0 sous le plafond n'achete aucun lot |
| **Reste du palier** | | **4,0** | |

### 2.2 Ce qui tient dans 4,0, avec A' et B ouvertes (`Q-03`)

**Affectation (`DEC-0120` A.36, a moi)** : les lots de mathematiques CE1 vont tous a la meme instance (`PAR-2`), et je retiens B,
comme A.35 l'ecrit. L'EMC CE1 va a l'autre, A'. Motif du choix d'A' pour l'EMC : A' juge depuis deux paliers des items de CE1 lus
sans texte support, sous `LON-CE1-1`, `-2`, `-5` et `TRV-CE1-6`, exactement ce qu'un paquet d'EMC CE1 met en jeu ; B n'a audite a
s57 que des arts plastiques CP et des sciences CM1. Une limite, routee : je ne sais pas si l'amendement d'`ADR-0065` pour deux
instances suppose qu'A' ne porte que du francais. `theo-archi` le dit avant le premier worktree.

| Composition | A' | B | Somme | Lots du palier | Ce qu'elle suppose |
|---|---|---|---|---|---|
| **(i)** | arbitrage 0,5 ; marge 0,5 ; un lot d'EMC CE1 1,0 = **2,0** | arbitrage 0,5 ; marge 0,5 ; un lot de `MAT-CE1-01`, `-10` ou `-11` 1,0 = **2,0** | **8,0**, rien de non affecte | 5 | `Q-03` lu comme deux instances qui auditent ; la seconde place va a l'EMC parce qu'un second lot de mathematiques sur A' couperait la matiere (`PAR-2`) et que le rang 4b n'a pas de lot recevable (1.1) |
| (ii) | **rien a auditer** : je n'ecris pas d'arbitrage pour une instance sans lot ni rejeu ; 0 | arbitrage 0,5 ; marge 0,5 ; deux lots de mathematiques 2,0 = **3,0** | 7,0 ; **1,0 non affecte**, qui tombe a la cloture | 5 | `Q-02` lu a la lettre jusqu'a la derniere place ; A' n'est ouverte que de nom, ce que `Q-03` ne dit pas |
| (iii) | 0, comme (ii) | arbitrage 0,5 ; marge 0,5 ; `MAT-CE1-15` illustre 1,5 ; un lot textuel 1,0 = **3,5** | 7,5 ; 0,5 non affecte | 5 | (ii), avec `MAT-CE1-15` repris sur les dessins existants |

**Ce que je tranche, et ce que je ne tranche pas.** Les trois compositions tiennent `PAR-8` et `PAR-9`. **Choisir entre (i) et (ii)
revient a lire ensemble `Q-02` et `Q-03`** : c'est un ordre, donc a `camille-po`, et si les deux lectures s'opposent, a l'utilisatrice.
Je dis seulement ce que chacune achete, en plus des trois lignes de A : (i) deux lignes au compteur, dont une `couvert-valide` ;
(ii) et (iii) deux lignes au compteur, aucune `couvert-valide`. **Aucune composition ne porte plus de cinq lots, ni un lot de questionner le monde.**

**`MAT-CE1-15` illustre ne tient pas dans (i)** : B a 0,5 + 0,5 + 1,5 = 2,5 et A' 2,0 font 8,5.

---

## 3. `E4`, et mes avis sur `Q-05`, `Q-07` a `Q-10`

### 3.1 `E4` : l'explication par item de `ce1-relire-une-phrase` est recevable, et elle est due

**Le fait technique, verifie** : l'en-tete `DEGRADE` du paquet ecrit que l'explication qui redonne la phrase juste *"n'est pas
servable"*, a cause du garde-fou des explications. C'etait vrai le 2026-10-07 ; **c'est faux depuis le 2026-10-09** (`6f8895a4`) :
un paquet sans `graphie` qui explique tous ses items passe, et un trou rougit. Un paquet servi le fait deja
(`cp-rapprocher-deux-oeuvres` : 0 graphie, 6 explications, section 0). `theo-archi` a raison.

**Ce que je tranche** :

1. **L'explication n'est pas une option du paquet, c'est ma condition.** `RLP-CE1-3` (s48, 4.2) et la seconde clause de `TRV-CE1-5`
   l'exigent : *"un enfant ne quitte pas une manche avec, sous les yeux, la derniere phrase fausse qu'on lui a montree"*. Le paquet
   tel qu'il est serait **refuse** a l'audit. Ce n'est pas une barre neuve : c'est celle de s48, que le garde-fou empechait de tenir.
2. **Forme recevable** : une explication sur **chacun des 20 items**, apres une bonne comme apres une mauvaise reponse, qui **redonne
   la phrase juste entiere**, entre chevrons, identique a la phrase de l'enonce a la seule erreur pres. Elle ne nomme aucune regle hors
   des mots admis par `TRV-CE1-4`.
3. **Plafond : `couvert-valide`.** *"Nommer l'erreur parmi trois"* est la forme de `RLP-CE1-2`, que j'ai acceptee pour *reperer* ; elle
   n'est pas degradee au regard de la ligne. L'en-tete `DEGRADE` est faux sur ses deux motifs, et sa correction est a un developpeur
   (fichier de `src/main`), dans le commit qui ecrit les explications.
4. **Sans effet a s57** : `-09` reste hors de s57 (condition 4, 3.2). Ce que je tranche vaut pour le premier sprint qui le prendra.

### 3.2 Avis sur les questions a l'utilisatrice. **Aucun ne vaut reponse.**

| Question | Mon avis | Motif |
|---|---|---|
| `Q-05` (`MAT-CE1-14`) | **Ne pas prendre le paquet textuel ; attendre le quadrillage montre.** | **Correction de mon propre constat** : en 1.4 de mes criteres de s57, j'ai liste six lignes plafonnees `en cours` sans `MAT-CE1-14`, alors que s43 lot 3 section 1 ecrit qu'un paquet textuel *"plafonne a `en cours`"*. Mais la meme section pose `DEP-CE1-2` : *"le quadrillage est montre des qu'un item demande de se reperer ou de se deplacer"*. Le plafond dit le statut le plus haut, il ne dispense d'aucune condition. Les items de `ce1-deplacements-quadrillage` (*"Zoe est en B2 et va 2 cases a droite [...] Ou arrive-t-elle ?"*) demandent de se deplacer sans quadrillage : refus ecrit d'avance. La liste de six d'A.32 est donc juste dans son effet. Le moyen (designation sur un quadrillage, precedent des `symetrie-grille`) est a `theo-archi` |
| `Q-07` (`-08`, `-09`) | **La condition 4 tient pour tout s57.** | Elle dit *"jamais dans le sprint ou une autre instance audite le paquet illustre d'`ART-CP-10`"*, et j'ai ecrit le 2026-10-13, apres avoir admis les paliers, *"quelle que soit sa duree"*. Le rejeu a eu lieu dans s57 : qu'il soit fini ne change pas le sprint. La relire maintenant serait relever ma barre d'un cote et la baisser de l'autre au gre du calendrier. **A s58** : au plus un des deux par sprint (s55, 1.1), sur l'instance du francais CE1. Ma preference pedagogique : `-09` d'abord, parce que ses items d'accord reprennent `-14` et `-15`, valides depuis peu, et que `E4` est tranche |
| `Q-08` (premisse de l'adulte au CE1) | **Recevable pour la moitie *ecouter* de `FRA-CE1-27`, plafond `en cours` ; elle ne rend pas `-29` couvrable et ne releve pas `-21` a elle seule.** | *Ecouter un texte lu* : l'adulte lit un texte que l'enfant ne voit pas, l'enfant repond au choix. C'est servable, *s'exprimer* reste irreversible. Il faudrait amender `TRV-CE1-1` (aucun champ a voix haute au CE1), ce qui est a moi le jour ou elle decide. **Correction de mon propre constat** : le 2026-10-08 (complement s55, section 3), j'ai donne l'extension de la premisse comme une voie de levee de `-21`. Une dictee fait **ecrire** l'enfant, donc une saisie, et `R6` (s19, s45 5.1) refuse a la saisie une ligne qui fait produire les lettres d'un mot sur un clavier qui corrige. Je ne l'avais pas relu. La premisse seule ne leve ni `-29` ni `-21` ; j'annoterai le complement de s55 dans un commit a moi |
| `Q-09` (saisie alphabetique au CE1, `-21`) | **Je refuserais aujourd'hui, au motif de `R6`.** | *Ecrire sans modele un mot invariable* est exactement ce qu'un correcteur repare : *boucoup* devient *beaucoup*, et c'est l'appareil qui ecrit. `R6` est levable aux conditions ecrites (correction automatique refusee, ou appareil ou elle ne s'applique pas), que l'utilisatrice a ecartees. L'omission de s45, 5.3 (`saisie-mot-dicte-cp` servie sans examen de `R6`) reste ouverte, et je ne m'appuie pas sur elle |
| `Q-10` (arts plastiques CE1) | **L'instruction d'`ART-CE1-02` est possible, celle d'`ART-CE1-01` attend une reponse sur les droits ; aucune n'ouvre un lot a s57.** | Le verdict d'`ART-CP-10` est ecrit : je peux instruire `-02` hors des 8,0, comme les autres instructions. Pour `-01`, un corpus de dessins et de photographies pose la double preuve d'`ADR-0082` pour une oeuvre qui est elle-meme une photographie, que `theo-archi` n'a pas encore lue (son avis, 1.5) : j'instruis apres. Les deux sont des terrains neufs, exclus tant qu'A' existe |

---

## 4. Conditions que je poserai au Done pedagogique, par lot

### 4.1 Communes a tous les lots du palier 3

Reprises de `arbitrage-nadia-palier-2.md` 6.1, sans changement :

1. `TRV-CE1-1` a `-7`, `LON-CE1-1`, `-2`, `-5` ; `LON-CE1-3` et `-4` pour `-06` et `-07`, comptes par la regle de s47.
2. **Lecture complete avant le premier verdict** : une ligne datee *"lecture complete : <paquet>, N items lus"*, N egal au compte, dans un
   commit ancetre de celui du verdict.
3. Mes points d'audit deja ecrits (bonne reponse jamais seule a majuscule, intrus du premier mot, mot cite entre chevrons, indice qui
   dit ou regarder).
4. **Le pli** : a 744 x 551, apres une erreur, un indice ou une explication sous le pli est une reserve non bloquante, nommee et datee
   (mots de l'utilisatrice, *"le pli n'est pas bloquant"*).
5. **Exactitude** : chaque bonne reponse et chaque distracteur relus a l'ecran. Une erreur de fait est un refus a elle seule.
6. **Un lot sans story n'est pas arbitre** ; un paquet ecrit dans le palier est audite sur le commit que je joue, jamais sur un etat
   anterieur.

### 4.2 Francais CE1, instance A

`-06`, `-07`, `-11` : **la table de `arbitrage-nadia-palier-2.md` 6.2, inchangee** (`INF-CE1-1` a `-3` et la bonne reponse jamais
ecrite telle quelle dans le texte ; `DOC-CE1-1` a `-3` et chaque item joue texte masque ; `NEG-CE1-1` a `-3` et `TRV-CE1-2` recompte
sur les champs). Pour `-06` et `-07`, un item a trois rangees qui finit sous le pli releve du point 4 de 4.1, comme `RCT-CE1-R1`.

### 4.3 Mathematiques CE1, instance B : `en cours`, jamais `couvert-valide`

Commun : mon verdict, quel qu'il soit, ne fait pas passer la ligne a `couvert-valide`. La ligne compte dans la version par les cinq
criteres de mes criteres de s57, 1.2 ; **la cellule porte la mention ci-dessous mot pour mot**, sinon je ne valide pas le Done qui la
fait bouger (precedent `NUM-CE1-2`).

| Lot | Conditions | Mention exacte | Refus ecrit d'avance |
|---|---|---|---|
| `MAT-CE1-01` | `NUM-CE1-1` a `-7` (s43 lot 1, 2.2) | celle de `NUM-CE1-2` : *"le verbe 'denombrer', premier de cette ligne, n'est travaille par aucun jeu ; seuls 'lire', 'ecrire' et 'decomposer' le sont"* | moins de la moitie des items a trois chiffres ; le mot *decomposer* lu par l'enfant |
| `MAT-CE1-10` | `MAS-CE1-1` a `-6` (s43 lot 2, 3.4) | **ecrite ici, sur le patron de s43** : *"comparer des masses et des contenances et utiliser le gramme, le kilogramme et le litre, par du texte ; 'mesurer' reste non couvert, aucune balance ni aucun recipient gradue n'est rendu"* | un indice qui fait deduire la masse de la taille ; un litre ecrit en minuscule |
| `MAT-CE1-11` | `CAL-CE1-1` a `-5` (s43 lot 2, 4.3) | **ecrite ici, sur le patron de `MAT-CP-09`** : *"se reperer dans le calendrier par la date seule ; 'lire l'heure sur une horloge' reste non couvert, et l'ordre des mois est servi par `QLM-CP-13`"* | un item sur l'ordre ou le nom des mois pour eux-memes, les saisons ou les jours ; une erreur sur le nombre de jours d'un mois |
| `MAT-CE1-15`, illustre seulement | `FIG-CE1-1` a `-3` (s43 lot 3, 2), sur les dessins `figure-*` existants | celle de s43 lot 3 : *"reconnaitre le triangle rectangle et decrire des figures planes dessinees, seuls ; 'tracer a la regle et sur quadrillage' reste non couvert, aucune mecanique du produit ne recoit un trace"* | un item d'angle droit sans figure dessinee ; *"un triangle"* compte faux devant un triangle rectangle |

Ces deux mentions ne sont pas des barres neuves : s43 exigeait une mention sans l'ecrire mot pour mot, et `camille-po` ne peut pas
reprendre mot pour mot ce qui n'existe pas. Elles ne s'allouent pas : ce sont des textes de cellule, pas des conditions.

### 4.4 EMC CE1, instance A'

Communes aux quatre lignes : `EMC-1` a `EMC-7` ; `NRC-CE1-1`, **je lis a chaque audit** `cp-emc-regles-de-vie`, `cp-emc-regle-souhait-loi`,
`cp-emc-droits-enfant` et les cousines de CM1 (`cm1-emc-regle-loi`, `-droit-devoir`, `-regles-vie-collective`) ; `CYC-1` ; aucun texte
support ; au moins 20 items ; `TRV-CE1-2` avec les familles de distracteurs nommees a cote de chaque item.

| Ligne | Ce qui decide le Done, en plus | Refus ecrit d'avance |
|---|---|---|
| `EMC-CE1-01` | `BCO-CE1-1`, `-2` : les deux taches, les trois exemples en bonne reponse ; un bien personnel est celui d'un personnage nomme, a l'ecole | *ranger* ou *remettre a sa place* en bonne reponse ; un item sur ce que l'enfant possede |
| `EMC-CE1-02` | `RSA-CE1-1`, `-2` : regle et sanction distinguees ; rappeler la regle et reparer le tort en bonne reponse ; acte d'un personnage nomme | une humiliation, une punition physique, une sanction collective ou une privation de repas, **meme en distracteur** ; un personnage qualifie |
| `EMC-CE1-03` | `DDE-CE1-1`, `-2` : couples fermes ecrits dans le commentaire, servis dans les deux sens | un droit gagne en faisant son devoir, ou perdu en y manquant |
| `EMC-CE1-04` | `INT-CE1-1`, `-2` : une seule lecture defendable, *pour une seule personne* et *pour toute la classe* | un personnage mis en balance avec le groupe pour lui faire honte ; *interet general* ou *bien commun* lus par l'enfant |

### 4.5 Pour memoire, hors de s57

- **Questionner le monde CE1** : les conditions de l'instruction (sections 2 et 3), `NRC-CE1-1`, `QMT-CE1-1` et `-2`, au premier audit
  de s58. Je n'en ajoute aucune.
- **`-09`** : 3.1 ; **`-08`** : `OMP-CE1-1` a `-3`.

---

## 5. Ce dont je reste la seule garde, ajoute pour le palier 3

| Propriete | Pourquoi aucun test ne la tient |
|---|---|
| `LON-CE1-3` a `-5` et `TRV-CE1-1` sur les douze paquets de s53 et sur tout paquet d'EMC CE1 | ils ne sont executes que par des tests nommes par paquet (`G5` de `theo-archi`) ; aucun de ces paquets n'en a. Je compte a l'audit, par la regle de s47 |
| Qu'un paquet de mathematiques textuel ne serve pas plus que sa mention ne dit | un test voit un champ, pas un verbe de la ligne |
| `NRC-CE1-1`, `RSA-CE1-1`, `DDE-CE1-2`, `INT-CE1-1` en EMC CE1 | aucun garde-fou ne compare deux paquets ni ne lit une intention |
| Qu'aucun lot de questionner le monde CE1 ni `-08`, `-09` ne soit audite a s57 | aucun garde-fou ne lit une composition |

## 6. Routage

| A qui | Quoi |
|---|---|
| `camille-po` | Le choix entre (i), (ii) et (iii) (2.2), et l'utilisatrice si `Q-02` et `Q-03` s'opposent ; les stories des lots retenus ; les deux mentions de 4.3 dans les cellules le jour du verdict ; le retrait de questionner le monde CE1 de la composition du palier 3 (1.3) |
| `sofia-sm` | Ce qu'ecrire dix paquets de questionner le monde au palier 3 coute contre leur audit a s58 (1.3) ; la table d'isolation |
| `theo-archi` | Si l'amendement d'`ADR-0065` suppose qu'A' ne porte que du francais (2.2) ; le quadrillage montre de `MAT-CE1-14` (3.2) ; la double preuve d'`ADR-0082` pour une photographie (3.2, `Q-10`) ; le fait des dessins de figures (section 0) |
| un developpeur | l'en-tete de `ce1-relire-une-phrase` et ses 20 explications, quand `-09` sera compose (3.1) |
| moi | annoter le complement de s55, section 3, sur `R6` (3.2, `Q-08`) ; l'arbitrage prealable du palier 3, par instance, a l'ouverture |

## 7. Ce que ce document ne fait pas

- Il ne prononce aucun Done, ne joue aucun jeu, ne compose pas le palier 3, n'engage aucun lot, ne designe aucun developpeur.
- Il ne repond a aucune question de l'utilisatrice : `Q-05` a `Q-10` restent ouvertes.
- Il n'ecrit ni n'alloue **aucun identifiant de condition ou de reserve**. `AR1` a `AR8` et les compositions (i) a (iii) sont des
  reperes de ce document.
- Il ne touche aucun autre fichier, et il n'est pas commite.

**Tarif** : aucun prix neuf. Ce document n'est pas un poste de capacite : il precede l'ouverture du palier.

**Signe** : `nadia-prof`, s57, preparation du palier 3, 2026-10-19.
