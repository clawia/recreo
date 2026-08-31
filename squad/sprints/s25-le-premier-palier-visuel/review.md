# [Sprint s25] Review - 2026-08-31

- **Horodatage de debut** (rappel du planning) : 2026-08-31 18:24 +0200.
- **Horodatage de fin** : **du, non pose.** Cette review est **ouverte et non close** : les trois
  ceremonies que le planning place **avant** elle - `nadia-prof`, `relecteur-pedagogique`,
  `yanis-qa` - n'ont pas encore eu lieu. **Je ne pose pas un horodatage de fin sur un sprint qui
  n'est pas fini, et je n'ecris a la place de personne les trois sections qui les attendent.**
  Section 9 : ce que chacune doit rendre, et le point exact ou je demande son lancement.
- **Duree effective** : **non calculable tant que la fin n'est pas posee.** Duree ecoulee a
  l'ecriture de ce document : 3 h 42 min.
- **Animee par** : `sofia-sm`, **casquette Scrum Master**.
- **Arbre de reference** : worktree dedie `s25-sofia-review`, ouvert sur `s25-sofia`, puis neuf
  fusions (section 1).

---

## 1. Ce qui est integre - lignees verifiees, fusion lot par lot

### 1.1 Lignee des quatre branches, verifiee par moi avant toute fusion

| Branche | `round-two` ancetre ? | `s25-sofia` (planning) ancetre ? | Ce qu'elle porte |
|---|---|---|---|
| `s25-marc` | oui | **oui** | lots A, E, H |
| `s25-lea` | oui | **oui** | lots B, C, D, **et le lot A fusionne dans la sienne** |
| `s25-sofia-dev` | oui | **oui** | lots F, I, J, **et le lot A fusionne dans la sienne** |
| `s25-ines` | oui | **non**, et c'est correct : elle descend de son propre etat deja fusionne (`e144169`) | la contrainte de dessin `DES-1` a `DES-7` et la hauteur de rendu |

**Aucune n'est provisionnee sur la mauvaise branche.** Troisieme sprint consecutif sans occurrence,
apres s24 et s25 a l'ouverture.

**Un point que j'ai verifie plutot que suppose** : `s25-sofia-dev` porte un commit intitule *"essai
de fusion des deux paires visuelles"*. **Il n'a qu'un seul parent** - ce n'est pas une fusion, c'est
un rapport de mesure. La branche de `lea-dev` n'est donc pas entree dans celle de ma casquette dev,
et l'ordre de fusion du planning restait praticable. Je l'ai controle avant de m'appuyer dessus,
parce qu'un rapport d'essai qui aurait ete une vraie fusion aurait ecrase mon ordre sans que rien ne
le dise.

### 1.2 L'ordre de fusion du planning a ete tenu, et il a fallu le reconstruire

Les lots ont ete livres sur **trois branches par developpeur**, pas neuf branches par lot. Le
planning fixait un ordre **par lot** : `F` -> `A` seul -> les deux paires visuelles indissociables ->
`E` et `D` -> `H`. **J'ai donc fusionne commit par commit et non branche par branche**, ce qui rend
l'ordre exactement praticable et laisse une trace lisible.

| # | Fusion | Conflits |
|---|---|---|
| 1 | **Lot A** seul (`6080551`) | aucun |
| 2 | **Lot F** (`5532273`) | aucun |
| 3 | **Lots B + C**, premiere paire visuelle (`28150cd`) | aucun |
| 4 | **Lots I + J**, seconde paire visuelle (`115ac28`) | **3**, section 2 |
| 5 | **Lot E** (`683ceb3`) | **3** |
| 6 | **Lot D** (`b3d3fc7`) | **3** |
| 7 | **Lot H** (`d8baba6`) | aucun |
| 8 | Pointe de `s25-sofia-dev` - le rapport d'essai | aucun |
| 9 | `s25-ines` - `DES-1` a `DES-7`, hauteur de rendu | aucun |

**Verification finale** : les quatre branches sont **entierement** dans l'arbre
(`git merge-base --is-ancestor` sur les quatre). Rien n'est reste dehors.

**Une contrainte du planning a ete tenue par les deux paires et je le note parce qu'elle etait le
seul point dur technique** : ni `lea-dev` ni ma casquette dev n'ont fusionne l'autre paire dans la
leur. Les dessins et le paquet de chaque chaine sont arrives ensemble ; **aucune illustration n'a
jamais ete deposee sans le paquet qui la declare**, ce que le garde-fou bidirectionnel d'`ADR-0073`
aurait rendu rouge.

---

## 2. Les conflits, et comment je les ai resolus

**Tous a la fusion, jamais par l'auteur dans sa branche**, comme le planning le fixait. Neuf
conflits sur trois fusions, en trois familles.

### 2.1 Le cliquet du zero - le seul qui demandait un jugement

Les deux paires visuelles ont ecrit **le meme test** chacune de son cote, sur le garde-fou que
`theo-archi` avait pose a zero pour qu'il rougisse le jour du premier paquet illustre. Il a rougi
**deux fois le meme jour**.

| Cote | Assertion |
|---|---|
| `lea-dev` | la population est **non vide** |
| `sofia-sm`, casquette dev | la population vaut **exactement** sa taille |

> **J'ai retenu l'assertion exacte, et le motif n'est pas l'anteriorite : un conflit ne se resout
> jamais en affaiblissant une assertion.** Seule la taille exacte attrape un paquet qui perdrait la
> moitie de ses illustrations sans les perdre toutes. **Le motif de conservation du test - pourquoi
> le cliquet est retourne plutot que retire - est celui de `lea-dev` et il est repris mot pour mot.**
> Les deux parties survivent par ce qu'elles ont de plus fort.

**Les deux nombres sont mesures par moi sur l'arbre fusionne, pas relayes** : **24** items declarent
une illustration, **24** fichiers vivent sous le repertoire d'illustrations. *Methode : comptage des
cles `illustration:` des paquets references par `catalogue.yaml`, et des fichiers `.svg` du
repertoire servi, sur l'arbre de cette review.* **Chaque lot avait mesure 12 et 12 sur sa propre
branche - exact chez lui, faux apres fusion.** C'est la lecon de s01, au meme endroit, une fois de
plus.

### 2.2 Le cliquet de poids en double - resolu par l'instruction ecrite de son autrice

Les deux paires ont chacune paye la dette d'`ADR-0036` clause 5. **Ma casquette dev avait ecrit dans
sa propre Javadoc que son cliquet etait un doublon assume, que celui de `lea-dev` survivrait a la
fusion** - il cliquete en plus le poids total du repertoire - **et que le sien partirait avec elle.**
Je l'ai applique : le cliquet de `bootstrap` est retire, celui d'`adapter-web` reste.

### 2.3 Sept conflits de liste blanche - des ajouts au meme endroit

Catalogue, juge de rang, garde-fou du texte accentue : sept hunks, tous des ajouts en fin de liste.
Les deux cotes sont gardes a chaque fois.

> **Un piege attrape a la relecture du resultat, et pas par la commande de fusion.** Dans
> `catalogue.yaml`, deux entrees en conflit **se partagent la ligne `nombreDeManches`** : garder les
> deux cotes la laisse a la derniere et la retire silencieusement a la premiere. **C'est arrive deux
> fois** - `cp-monnaie` puis `cm1-substituts`. J'ai ecrit un controle apres la premiere occurrence et
> il a attrape la seconde : **35 entrees, 35 `nombreDeManches`, aucune entree incomplete sur ses cinq
> cles.** Aucun test ne l'aurait vu comme une perte : le fichier serait reste valide et un jeu aurait
> change de nombre de manches sans que personne l'ecrive. **C'est exactement pourquoi cette
> resolution n'appartient pas a l'auteur dans sa branche.**

**Une correction de fond, pas de forme** : le commentaire d'une des listes blanches disait *"premier
jeu illustre du produit"*. Les deux paquets sont nes le meme jour et **aucun des deux n'est le
premier** - c'est l'ordre de fusion qui les range, pas leur contenu. Corrige.

---

## 3. La porte technique - mesuree par moi, et le chemin pour y arriver a ete bloque

### 3.1 Le lot F a refuse ma premiere mesure, et il avait raison

La commande du depot, `bash outils/mvn-jdk21.sh -B clean verify`, **a refuse de lancer Maven** :

> *"La toolchain jdk 21 designe un JDK qui rapporte 25.0.2. Une mesure prise par cette enveloppe
> serait annoncee sur 21 et executee sur 25.0.2."*

**C'est le lot F qui fonctionne, des sa premiere utilisation reelle.** L'enveloppe *"affirmait un
raccordement qu'elle ne verifiait pas et imprimait au lieu de refuser"* - quatrieme occurrence
documentee a la review de s24, ou j'avais refuse par ecrit qu'elle glisse une cinquieme fois. **Elle
refuse desormais, et la premiere chose qu'elle attrape est un defaut reel.**

**Ce que le constat citable dit, et il ne dit rien du poste** : le fichier de toolchains de
l'utilisateur declare une toolchain `jdk` de **version 21** dont la racine est **un JDK 25.0.2**.
C'est le defaut exact que `nadia-prof` avait constate a s24 et qui rendait *"sans methode toute
phrase du depot affirmant"* une mesure sur 21.

> **Consequence qu'il faut dire et non arrondir : toutes les mesures de ce sprint prises avec cette
> enveloppe avant le lot F ont ete executees sur un JDK 25.0.2 en etant annoncees sur 21.** Cela vaut
> pour les 937 tests du planning et pour les 986 de l'essai de fusion. Aucune n'est fausse en
> elle-meme ; **c'est leur perimetre annonce qui l'etait**, et c'est precisement ce que le lot F
> existe pour rendre impossible.

### 3.2 La mesure de cloture, avec sa commande et son perimetre

Je n'ai **pas** modifie la configuration du poste - elle n'est pas a moi et une correction
silencieuse deplacerait le sens de toutes les mesures futures. J'ai mesure en designant, **pour cette
execution seule**, un fichier de toolchains ecrit hors du depot et pointant sur un vrai JDK 21 :

```
mvn -B -t <fichier de toolchains hors depot> clean verify
```

Maven confirme dans son journal : *"Required toolchain: jdk [ version='21' ]"*, puis *"Found matching
toolchain for type jdk"* - et le JDK ainsi designe **rapporte 21.0.10**.

> **BUILD SUCCESS**, cinq modules, **1 001 tests, 0 echec, 0 erreur, 0 ignore.**

**Methode du comptage** : somme des lignes de resultat **de module** (`surefire` et `failsafe`),
jamais des lignes par classe. **Perimetre : le reacteur complet a la racine, donc aucun `-pl` et
aucune question de `-am`.**

| Module | Tests |
|---|---|
| `recreo-domain` | 80 |
| `recreo-application` | 13 |
| `recreo-adapter-web` | 359 |
| `recreo-adapter-content` | 398 |
| `recreo-bootstrap` (surefire) | 145 |
| `recreo-bootstrap` (failsafe) | 6 |
| **Total** | **1 001** |

### 3.3 L'ecart, etabli classe par classe et non affirme

**937 a l'ouverture -> 1 001 a la cloture : +64.** *Methode : comparaison des lignes de resultat **par
classe** entre les deux journaux, ce qui isole les classes neuves des classes qui grandissent.*

| Origine | Tests |
|---|---|
| **6 classes neuves** : `PaquetCm1SubstitutsTest` (13), `PaquetCpFiguresPlanesTest` (9), `PaquetCpMonnaieTest` (9), `LIllustrationDUnItemServiAtteintLEcranDeMancheTest` (9), `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (8), `PaquetCpSemaineTest` (8) | **+56** |
| **5 classes existantes qui grandissent** : `RessourcesTiercesTest` (+2), `BanditManchotTest` (+2), `PaquetCm1FractionsTest` (+2), `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` (+1), `YamlContentPackLoaderTest` (+1) | **+8** |
| **Total** | **+64** |

**Aucune classe n'a disparu.** `LIllustrationDeContenuResoutEtAucuneNeDortTest` rend **11** tests,
c'est-a-dire son compte d'origine : les deux tests de cliquet de poids en double que la seconde paire
y avait ajoutes sont ceux que la resolution du conflit a retires (section 2.2).

**Un ecart de methode qu'il faut nommer et qui n'est pas neuf** : la somme des lignes **par classe**
rend 994 la ou la somme des lignes **de module** rend 1 001. **L'ecart est de 7, et il est le meme
aux deux bouts** - 930 contre 937 a l'ouverture. C'est l'obstacle 12 de la retro de s23, deja route a
`theo-archi` et toujours ouvert. **C'est la somme par module qui fait foi** ; la somme par classe ne
sert ici qu'a decomposer un ecart, ce qu'un decalage constant n'empeche pas.

**Le chiffre de 986 qui m'a ete transmis n'est pas comparable au mien**, et je dis pourquoi plutot
que de le taire : il porte sur un arbre qui ne contenait que les deux paires visuelles - ni `SUB`,
ni `SEM`, ni la correction de `cm1-fractions` - et qui portait encore le cliquet de poids en double.
**Un essai de fusion n'est pas une mesure de cloture, et son auteure l'a ecrit elle-meme.**

### 3.4 Les autres criteres de sortie

| # | Critere | Etat, verifie par moi |
|---|---|---|
| 1 | `mvn -B clean verify` vert a la racine, total etabli par moi | **tenu** - 1 001, section 3.2 |
| 2 | Garde-fou d'`ADR-0073` bidirectionnel, deux morsures vertes | **tenu**, et desormais exerce sur une population non vide |
| 3 | Borne de l'exemption de `RessourcesTiercesTest`, mordue dans les deux sens | **tenu** - la classe passe de 19 a 21 tests |
| 4 | Cliquet de poids d'`ADR-0036` clause 5, pose sur la valeur installee | **tenu** - section 4 |
| 5 | `enonce` en premiere cle de chaque item illustre (`ADR-0073` clause 6) | **tenu**, avec sa morsure |
| 6 | **Zero attribut d'evenement inline dans un gabarit servi** | **tenu.** `git grep -nE '\s(on[a-z]+\|hx-on:)=' -- '*.html'` : **zero occurrence**. Le depot avait zero a l'ouverture et en a zero a la cloture, apres un sprint qui a ajoute une branche de gabarit et deux chaines de rendu |
| 7 | Zero occurrence typographique neuve | **du au `relecteur-pedagogique`**, section 9 |
| 8 | Aucun chemin de poste, aucun nom de compte | **tenu** - garde-fou vert sur l'index complet |
| 9 | Lot F : l'enveloppe refuse au lieu d'imprimer, harnais et etape de CI | **tenu, et demontre en situation reelle** - section 3.1. Les cinq juges purs que ce lot touche sont verts, lances par moi |
| 10 | Tests dans le commit de leur code | **tenu** - aucun lot de rattrapage |
| 11 | **Le cliquet de poids ne monte pas silencieusement au second paquet illustre** | **tenu, et il a servi** - section 4 |

*Perimetre de ce que j'ai lance moi-meme parmi les juges purs : les cinq que le lot F touche
(`rapport-tokens-horodatage`, `rapport-tokens-worktree`, `rapport-tokens`, `mvn-jdk21`,
`verifier-jdk`), tous verts. **Les quatre autres, qui pilotent un navigateur, ne sont pas lances
ici** : c'est l'affaire de la CI, et je le nomme plutot que de laisser croire que j'ai balaye les
neuf.*

---

## 4. Le critere 11 - tenu, et non contourne

**C'est le seul critere que le quatrieme paquet avait rendu necessaire, et il a mordu exactement ou
il devait.**

`lea-dev` a pose le cliquet la premiere, dans `adapter-web`, **a 1 112 octets pour un dessin et
6 206 pour le repertoire**, et elle a **nomme son fichier foyer commun des deux chaines** - c'est
elle qui a fait de ce point le seul endroit ou les deux paires se croisent. **Elle l'a prouve plutot
qu'affirme** : son juge normalise les fins de ligne a la lecture, et il assertionne la valeur
**exacte** dans les deux sens, jamais un plafond de confort.

**Ce que j'ai verifie moi-meme avant de deplacer quoi que ce soit** :

- les deux juges - le sien et celui, retire, de `bootstrap` - **comptent la meme chose** : les octets
  UTF-8 **fins de ligne normalisees en LF**. Sans cela, le meme fichier extrait sur deux postes
  aurait donne deux nombres ;
- ma mesure independante sur les 24 dessins servis : **total 29 118 octets, plus lourd 2 685**
  (`monnaie-meme-somme-e.svg`), plus leger 321 (`figure-cercle-petit.svg`).

> **Le cliquet est deplace par moi, dans le commit qui apporte la seconde chaine**, de 1 112 a
> **2 685** et de 6 206 a **29 118**, **avec son motif ecrit dans la Javadoc et sa valeur mesuree** -
> la condition exacte que le critere 11 pose. Le motif de la hausse est de ma casquette dev et je
> l'ai verifie sur les fichiers avant de le reprendre : une figure plane est **un** trace ; une scene
> de monnaie porte jusqu'a six objets, chacun avec un contour, un contour interieur et une a deux
> instances de glyphe. **Le trace est plus gros par construction, pas par relachement.**

**L'avertissement d'`ines-design` est porte dans le garde-fou et non dans un rapport** : *"un seuil
pose sur l'un ne veut rien dire sur l'autre, et le cliquet doit dire lequel il mesure"* - ses fichiers
de demonstration pesent **275 octets de trace pour 1 084 commites**. La Javadoc dit maintenant
explicitement que le cliquet mesure les **octets commites, en-tete de commentaire compris**, et
pourquoi : ce sont ceux que l'enfant telecharge. **Sans cette phrase, le nombre aurait ete exact et
son sens indetermine.**

---

## 5. Ce qui est livre, et ce qui ne l'est pas

### 5.1 Les neuf items du planning

| Lot | Objet | Etat |
|---|---|---|
| **A** | Le champ d'illustration, du domaine jusqu'a l'ecran | **livre** |
| **B** | Douze dessins de figures planes, cliquet de poids | **livre** |
| **C** | `S25-FIG-1`, paquet `cp-figures-planes` | **livre** - 12 items |
| **I** | Douze dessins de monnaie, chiffres graves en geometrie | **livre** |
| **J** | `S25-MON-1`, paquet `cp-monnaie` | **livre** - 12 items |
| **E** | `S25-SUB-1`, paquet `cm1-substituts` | **livre** - 20 items |
| **D** | `S25-SEM-1`, paquet `cp-semaine` | **livre** |
| **F** | Les deux dettes d'outillage de s24 | **livre**, et demontre en situation (section 3.1) |
| **H** | `S25-FRA-1`, item 18 de `cm1-fractions` | **livre** - un item, un verbe |

**Les neuf items sont livres. Aucun declencheur de bascule du planning n'a eu a jouer**, ni celui du
lot A ni celui de la quatrieme ligne.

**Ce qui n'est pas livre, et c'etait decide au planning** : le lot G - l'instruction de la dette de
pli - sorti du perimetre sur l'arbitrage de l'utilisatrice ; `S25-SCO-1`, `S25-DEN-1` et `S25-LIS-1`,
tous trois avec leur motif ecrit. **Rien n'a glisse en cours de sprint.**

### 5.2 L'etat de la matrice - recompte par moi, et il n'a pas bouge

| Statut | Compte |
|---|---|
| `couvert-valide` | **27** |
| `non couvert` | 13 |
| `en cours` | 1 |
| `non couvrable en l'etat` | 6 |
| **Total** | **47** |

> **27 / 41 = 65,9 %. Inchange depuis l'ouverture.**

*Methode : comptage de la colonne `Statut` des quatre tableaux de matiere de
`squad/programme/MATRICE-couverture.md`, sur l'arbre de cette review. Aucun module compile, donc
aucune question de `-am`.*

**C'est le resultat attendu et non un manque** : **aucune ligne ne passe `couvert-valide` sans le
verdict de `nadia-prof`**, et elle n'a pas encore joue. Les quatre paquets livres sont **candidats**
a quatre lignes distinctes - figures planes CP, monnaie CP, la ligne de l'heure par sa moitie *se
reperer*, et les substituts CM1 - ce qui porterait la couverture a **31 / 41 = 75,6 %** si les quatre
passaient. **Ce chiffre est un plafond, il n'est pas atteint, et il ne le sera pas par ce document.**

---

## 6. Tableau des obstacles du sprint

**Aucun n'est clos par cette review sur une intention.** Un obstacle n'est clos que quand le fichier
qui le tranche existe sur le disque.

| # | Obstacle | Categorie | Proprietaire | Etat |
|---|---|---|---|---|
| **1** | **Deux contraintes en vigueur se contredisent sur la hauteur declaree d'un dessin.** Le garde-fou de geometrie de `lea-dev` exige `width`/`height` **>= 176 px** ; `DES-3` d'`ines-design` exige une hauteur declaree **<= 176 px**. **La seule valeur qui satisfait les deux est 176 exactement** | Technique et forme | **`ines-design` et `theo-archi`** | **OUVERT** - section 7.1 |
| **2** | **`MON-1` : sa lettre ou son motif ?** Sa lettre dit *"aucune valeur n'y est ecrite"*, son motif vise *"un enonce qui ecrit 'une piece de 2 euros'"*. La lettre a ete appliquee, la plus stricte, et elle a interdit une formulation naturelle | Pedagogie | **`nadia-prof`** | **OUVERT** - section 7.2 |
| **3** | **`ADR-0041` interdit la forme la plus forte de `MON-3`.** Le distracteur le plus parlant d'un item de composition est juste arithmetiquement mais impossible avec les pieces montrees - donc de meme valeur que la bonne reponse, ce qu'`ADR-0041` refuse. **Le garde-fou n'a pas ete affaibli** | Pedagogie, puis technique | **`nadia-prof`**, puis `theo-archi` | **OUVERT** |
| **4** | **Le trait vertical qui separe la reference de la bourse n'est explique nulle part.** Un enfant de CP comprend-il cette disposition sans qu'on la lui apprenne ? | Pedagogie | **`nadia-prof`** | **OUVERT** |
| **5** | **`Question` porte desormais six composantes optionnelles sur dix, et cinq constructeurs surcharges en chaine.** Dette n. 6 de `CLAUDE.md`. Le garde-fou de classement rougit des qu'une composante de plus n'est pas classee | Technique, architecture | **`theo-archi`** | **OUVERT** - section 7.3 |
| **6** | **L'URL d'une illustration porte un identifiant de session tant que le client n'a pas de cookie, ce qui defait la mise en cache.** Preexistant sur cinq ressources statiques ; **premiere fois sur une image, et la premiere qui soit servie par item** | Technique | **`theo-archi`** | **OUVERT** - section 7.4 |
| **7** | **Une ligne du programme devient injouable sans la vue - ce n'est plus une hypothese, c'est demontre par le paquet servi.** Les douze textes alternatifs de `cp-figures-planes` sont **identiques**, et ce n'est pas une economie | Produit | **`camille-po`**, puis l'utilisatrice | **OUVERT, aggrave** - section 7.5 |
| **8** | **Un juge ecrit a s22 n'etait lance par aucun automate depuis sa creation** - `outils/rapport-tokens-horodatage-test.py`. **Troisieme occurrence de cette famille** | Qualite, CI | **`sofia-sm`** | **CLOS** - le lot F le corrige, section 7.6 |
| **9** | **L'outil de tokens ne voyait pas la fenetre de s25** : l'horodatage de debut de mon planning s'ecrivait autrement que les vingt-quatre precedents | Ceremonie | **`sofia-sm`** | **CLOS par moi** - section 7.7 |
| **10** | **La formule de JDK disqualifiee : 31 occurrences dues chez six auteurs, dont 19 chez `theo-archi`** | Redaction, doc | **six auteurs**, chacun sur ses documents | **OUVERT, trie** - section 7.8 |
| **11** | **La toolchain du poste declare 21 et designe un JDK 25.0.2** | Poste, hors depot | **l'utilisatrice** | **OUVERT** - section 3.1 |
| **12** | **La somme par classe sous-compte de 7 la somme par module** | Qualite, mesure | **`theo-archi`** | **OUVERT**, inchange depuis s23 |
| **13** | **`DV-3`, le contraste du trait d'un `.svg` servi**, porte desormais sur deux paquets illustres | Pedagogie, puis technique | **`nadia-prof`**, puis `theo-archi` et `yanis-qa` | **OUVERT**, et **partiellement instruit** : `DES-5` fixe la teinte a `#1c2733` et `ines-design` en mesure le contraste a 14,09:1 contre un seuil de 3:1 |
| **14** | **Douze dessins servis declarent une hauteur que la feuille rogne** - les douze figures planes declarent 480 px la ou `DES-3` plafonne a 176 | Forme | **`ines-design`** | **OUVERT** - section 7.1 |
| **15** | **Cinq dossiers de sprint restent invisibles a l'outil de tokens** faute d'horodatage de debut lisible : s02, s04, s10, s12, et s25 jusqu'a ce jour | Ceremonie | **`sofia-sm`** | **OUVERT** pour les quatre anciens - section 7.7 |
| **16** | **`CON-4` et `CON-5` ne sont couverts par aucun automate** : le controle exhaustif par masquage d'image, et la verification que 24 textes alternatifs ne livrent pas la reponse | Pedagogie | **`nadia-prof`** | **OUVERT**, par construction |
| **17** | Le rang des trois lots de dette de `theo-archi` - gestionnaire inline, cliquet typographique, trois noms d'un concept | Produit, rang | **`camille-po`** | **OUVERT**, inchange. **Le zero du gestionnaire inline a tenu tout le sprint** (critere 6) |
| **18** | `S25-SCO-1`, second report | Produit, rang | **`camille-po`** | **OUVERT**, inchange |

---

## 7. Les obstacles qui demandent plus qu'une ligne

### 7.1 La contradiction sur la hauteur declaree - et elle a deja produit deux resultats differents

**Ce n'est pas une contradiction theorique.** Les deux chaines visuelles l'ont resolue differemment,
et **je l'ai mesuree sur les fichiers plutot que de la deduire des documents** :

| Chaine | `width` / `height` declares | Garde-fou de `lea-dev` (>= 176) | `DES-3` d'`ines-design` (<= 176) |
|---|---|---|---|
| **12 figures planes** | **480 x 480** | passe | **ne passe pas** |
| **12 dessins de monnaie** | hauteur **176**, largeur 278 a 678 | passe | passe |

**Le motif de l'ecart est un fait de sequencement, pas une negligence** : `DES-3` est arrivee sur
`s25-ines` et n'a ete fusionnee qu'apres la paire de `lea-dev`. **Elle ne pouvait pas la lire.** Ma
casquette dev, elle, l'avait avant de commiter, et elle a pris **l'intersection exacte** en le
qualifiant de *"seul geste qui ne tranche rien"*. **C'est la bonne conduite et je la confirme comme
conduite ; je ne confirme pas 176 comme regle, parce que ce n'est pas a moi.**

**Ce que la contradiction coute aujourd'hui, dit sans le dramatiser** : rien a l'ecran. `ines-design`
mesure qu'un fichier declarant plus que le plafond est **rendu a 176 px quand meme** - la feuille
gouverne. **Le defaut est que la declaration est trompeuse**, pas que l'enfant voie autre chose.

**Ce que je route, et je ne tranche ni l'un ni l'autre** : une **contrainte ecrite** et un
**garde-fou executable** qui disent l'inverse l'un de l'autre. `ines-design` pour la contrainte de
forme, `theo-archi` pour ce que le garde-fou doit encoder - `DV-1` lui etait deja routee. **Tant
qu'aucun des deux n'a rendu, je ne fais retoucher aucun des vingt-quatre dessins** : les faire passer
a 176 aujourd'hui serait trancher a la place d'`ines-design` en ayant l'air de ranger.

### 7.2 `MON-1`, lettre ou motif - et pourquoi je ne le tranche pas

Ma casquette dev a applique **la lettre**, la plus stricte : aucun enonce du paquet ne porte de
chiffre. Cela lui a interdit *"il faut payer 7 euros"* et l'a conduite a *"comment faire la meme
somme que le billet"*, ou la somme a atteindre est **lue sur l'image**.

**C'est defendable et c'est peut-etre plus strict que l'intention.** L'exemple que `nadia-prof` donne
elle-meme - *"on paie 7 euros avec 5 + 2"* - porte un chiffre dans l'enonce. **Si sa lettre est plus
large que son intention, cinq items peuvent etre reecrits sous une forme plus proche de son exemple.**

**Je ne tranche pas la lecture d'une condition pedagogique**, et je note que le choix strict a un
merite dont elle jugera : il rend `CON-1` - *"en effacant l'image, la reponse reste-t-elle
trouvable ?"* - mecaniquement plus difficile a violer.

### 7.3 `Question` a six composantes optionnelles, et je donne le chiffre que j'ai mesure

**Le nombre qui m'a ete transmis est cinq. J'en compte six**, et je le dis plutot que de le relayer.

*Methode : lecture de la signature du record dans `domain`, sur l'arbre de cette review.*

- **10 composantes** au total, dont **6 `Optional`** : `indice`, `explication`, `partDesignee`,
  `texteSupport`, `illustration`, `illustrationTexteAlternatif` ;
- **5 constructeurs surcharges en chaine**, chacun deleguant au suivant.

**Le fond de l'alerte de `marc-dev` est juste et le chiffre exact la renforce** : c'est la **dette
n. 6 de `CLAUDE.md`** - *"des records tres larges ou ajouter un champ obligeait a editer une
quinzaine d'appels"* - et la version precedente du produit y est morte a 16 et 27 champs. **La
prochaine composante est celle qui coute.** Le garde-fou de classement d'identite d'ecran rougit des
qu'une composante n'est pas classee : le depot **saura** quand elle arrivera. Ce qu'il ne dit pas,
c'est quoi faire a ce moment-la. **Question d'architecture, donc a `theo-archi`.**

### 7.4 L'illustration retelechargee a chaque partie

**Le mecanisme, verifie par moi dans le gabarit** : l'image est declaree par une expression d'URL
Thymeleaf `@{...}`, qui passe par l'encodage d'URL du serveur. Tant que le client n'a pas de cookie
de session, cet encodage **ajoute l'identifiant de session dans l'URL**. Une URL qui change a chaque
partie n'est jamais servie depuis le cache. **Rien dans le depot ne configure le suivi de session
autrement** - verifie par recherche sur l'index complet.

**Ce qui est preexistant et ce qui est neuf, dit precisement** : **cinq ressources statiques**
passent deja par la meme expression - la feuille maison, la feuille tierce, les deux scripts,
l'icone. Le defaut ne date donc pas de ce sprint. **Ce qui est neuf tient en deux points** : c'est la
premiere fois qu'il porte sur une **image**, et surtout la premiere fois qu'il porte sur une
ressource servie **par item** et non par page - dix items par partie au lieu d'un chargement de page.
**Le cout n'est pas du meme ordre.**

`ines-design` mesure que l'image est retelechargee a chaque partie. **Je n'ai pas refait sa mesure -
elle demande de jouer l'application - et je le dis** : j'ai verifie le mecanisme dans le gabarit et
l'absence de configuration contraire, elle a mesure l'effet. **Route a `theo-archi`** : c'est une
propriete de l'adapter web.

### 7.5 Une ligne du programme injouable sans la vue - demontre, plus suppose

Au planning, `DV-6` etait une **hypothese** que je devais remonter a la review. **Le paquet servi la
demontre**, et `lea-dev` l'a ecrit.

*Mesure faite par moi sur les deux paquets illustres :*

| Paquet | Textes alternatifs | Distincts |
|---|---|---|
| `cp-figures-planes` | 12 | **1** |
| `cp-monnaie` | 12 | 7 |

**Les douze textes alternatifs des figures planes sont identiques, et ce n'est pas une economie
d'ecriture** : la reponse attendue **est le nom de la figure**. Tout texte alternatif qui
distinguerait deux items livrerait la reponse - c'est `CON-5` appliquee, pas contournee. **Un enfant
qui utilise un lecteur d'ecran recoit douze fois la meme phrase et ne peut repondre a aucun des
douze items.**

**Le contraste avec la monnaie est instructif et je le verse tel quel** : la meme condition y produit
**sept** libelles distincts, parce que la reponse y est une somme et non l'objet montre. **Ce n'est
donc pas le palier 1 qui rend une ligne injouable : c'est la conjonction du palier 1 et d'une ligne
dont la reponse est ce que l'image montre.** Cette distinction n'est pas de moi ; elle sort du
materiau, et elle vaut d'etre posee avant qu'on generalise dans un sens ou dans l'autre.

**Route a `camille-po`**, et **remonte a l'utilisatrice par moi** - c'est ce que le planning
annoncait pour la review, et le fait est desormais plus dur qu'annonce.

### 7.6 Le juge que personne ne lancait - troisieme occurrence, corrigee

`outils/rapport-tokens-horodatage-test.py`, ecrit a s22, **n'a ete lance par aucun automate depuis sa
creation.** Meme famille que `verifier-jdk-test.sh` avant `ADR-0025` et `verifier-provenance-lots-test.sh`
avant s21.

**La cause exacte, et elle est plus fine que les deux precedentes** : le balayage de la CI derivait
son perimetre du **repertoire** mais restait fige sur une seule **extension**. *"Un balayage qui
derive son perimetre d'un repertoire mais pas de l'extension n'est derive qu'a moitie."* Le lot F
balaye desormais les juges Python, avec la meme regle qu'ailleurs : **un motif qui ne rend aucun
fichier est rouge**.

**Ce que je retiens et qui merite d'entrer en retro** : le geste correctif du lot F **n'a pas ajoute
le motif `squad/outils/*/*-test.py`**, alors qu'il aurait ete tentant de l'ajouter par symetrie.
Motif ecrit : aucun juge Python n'y vit, et la regle du balayage vide aurait rendu l'etape rouge pour
une population legitimement absente - **un faux rouge, symetrique exact du faux vert combattu**.
**Obstacle clos** : le fichier de CI existe et les neuf juges sont dans le perimetre.

### 7.7 L'horodatage de mon propre planning

L'outil de tokens attend une date suivie d'un espace ou d'un `T`, puis une heure. Mon planning
ecrivait `2026-08-31, 18:24 (UTC+2)` - **une virgule, et le motif ne lit plus rien**. Les
vingt-quatre plannings precedents ecrivent `2026-08-30 22:32`. **La fenetre de s25 etait donc absente
et les tokens du sprint seraient tombes en hors sprint.**

**Ce que ma casquette dev a refuse de faire, et elle a eu raison** : elargir le motif de l'outil.
*"Cela masquerait l'ecart au lieu de le montrer."* **C'est le fichier de ceremonie qui etait fautif,
et il est de moi.** Corrige a la forme conventionnelle, **la valeur n'ayant pas bouge d'une minute** :
ce n'est pas une reecriture retrospective, c'est une ponctuation.

**Ce que la correction fait apparaitre et que je porte comme obstacle 15** : l'outil **ignore quatre
autres dossiers de sprint** - s02, s04, s10, s12 - pour le meme motif, et il le dit sur sa sortie
sans que rien ne soit rouge. `--sprint sNN` echoue bruyamment ; **un lancement sans option se
contente d'imprimer "ignore"**. C'est la famille du garde-fou qui ne garde qu'a moitie, et elle est
a moi. **Je ne la corrige pas dans cette review** : ce serait un lot, et l'ouvrir maintenant serait
me donner a moi-meme un perimetre que je refuse aux autres en cours de sprint.

### 7.8 Le tri de la formule disqualifiee - et un chiffre de plus qui ne colle a aucun autre

Le tri est fait, sur le patron **amender, jamais reecrire**. **Aucune correction mecanique**, aucun
document d'un autre agent modifie.

| Categorie | Occurrences | Suite |
|---|---|---|
| **A** - la citation **parle** de la formule pour la disqualifier | 24 | **rien a corriger** |
| **B** - la formule enonce un prerequis, ne date aucune mesure | 4 | 2 amendees par le lot, 2 sans objet |
| **C** - raccordement de source | **31**, chez **six auteurs** | **du, chacun sur ses documents** |

**Un balayage mecanique en aurait corrompu 24** - c'est le chiffre qui justifie a lui seul d'avoir
trie a la main. **19 des 31 occurrences dues sont chez `theo-archi`**, qui a deja trie sur ce critere
a `S21-6` et a qui la forme appartient.

**Et un troisieme chiffre apparait, sur la meme chaine, mesure par moi** :

| Qui mesure | Ou | Fichiers | Occurrences |
|---|---|---|---|
| `theo-archi`, 2026-08-30 | worktree `s24-theo` | 40 | 51 |
| `sofia-sm`, casquette dev, 2026-08-31 | worktree `s25-sofia-dev` | 44 | 59 |
| **`sofia-sm`, casquette SM, 2026-08-31** | **arbre de cette review** | **45** | **62** |

*Methode identique aux deux precedentes : `git ls-files | grep '\.md$' | xargs grep -c "toolchain declaree"`.*

**L'ecart de +1 fichier et +3 occurrences est entierement le document de tri lui-meme**, qui cite la
formule trois fois pour la disqualifier - categorie A par construction. **Aucun des trois chiffres
n'est faux ; aucun n'est comparable a un autre sans son arbre.** C'est la troisieme mesure de la meme
chose en deux jours, et **c'est la troisieme fois que l'ecart s'explique entierement par l'arbre**.

---

## 8. Ce que je n'ai pas fait, et pourquoi

- **Je n'ai touche a aucune configuration du poste**, bien que ce soit le chemin le plus court vers
  la mesure. Le fichier de toolchains n'est pas dans le depot, il gouverne le sens de toutes les
  mesures futures, et une correction silencieuse aurait ete invisible a la relecture.
- **Je n'ai retouche aucun des vingt-quatre dessins**, alors que douze declarent une hauteur que
  `DES-3` refuse. Ce serait trancher a la place d'`ines-design` en ayant l'air de ranger.
- **Je n'ai elargi aucun motif d'outil** pour faire passer un cas - ni celui du rapport de tokens, ni
  celui du balayage des juges.
- **Je n'ai fait bouger aucune ligne de la matrice** et **je ne prononce aucun Done pedagogique.**
- **Je n'ai ecrit aucune des trois sections que cette review attend** : rapport qualite, corrections
  de relecture, avis pedagogique. Elles appartiennent a ceux qui les rendent.
- **Je n'ai reecrit le document d'aucun agent**, y compris ceux qui portent la formule disqualifiee.

---

## 9. Les trois ceremonies qui restent, dans l'ordre et avec leur declencheur

**Le planning les place avant la review, et c'est l'ecart de s24 a ne pas repeter.** Cette review est
donc **ouverte** : elle sera close quand leurs trois sections y seront, avec l'horodatage de fin.

| Rang | Qui | **A lancer quand** | Ce qui est attendu |
|---|---|---|---|
| **1** | **`nadia-prof`** | **maintenant.** Les deux paires visuelles sont fusionnees et vertes, et c'est la condition que le planning posait : *"des que la premiere paire visuelle est verte, et non a la review"* | **L'audit d'ecran** de sa section 9.2 - une fois, pas par paquet. Puis **l'audit de contenu des quatre paquets**, dont la passe d'exactitude image par image, `CON-4` par masquage et `CON-5` sur 24 textes alternatifs. Plus les obstacles **2, 3, 4, 13, 16**. **Elle seule prononce le Done pedagogique** |
| **2** | **`relecteur-pedagogique`** | **quand les cinq lots de contenu sont fusionnes verts** - C, J, E, D, H. **Ils le sont** | Le francais des 4 paquets neufs et des 24 textes alternatifs ; les fichiers de ceremonie ; **le diff typographique de s25, critere de sortie 7, attendu a zero occurrence neuve**. Son volume est price : `S25-SUB-1` porte deux fois et demie le texte d'un paquet ordinaire, et deux paquets illustres s'y ajoutent |
| **3** | **`yanis-qa`** | **apres le rapport du relecteur**, et pas avant | La porte technique sur l'arbre de cloture ; la bidirectionnalite du garde-fou d'`ADR-0073` sur materiau non vide ; **les deux cliquets et le cliquet de zero, qui sont le seul point de croisement des deux chaines** ; sa campagne de mutation sur le materiau reel |

**L'ordre entre les deux dernieres est celui que le planning fixait et je le confirme en le
motivant** : les corrections du relecteur touchent des fichiers servis. Une campagne de mutation
lancee avant elles porterait sur un arbre qui n'est pas celui de la cloture, et son chiffre devrait
etre refait.

**`nadia-prof` passe en premier et non en dernier**, ce qui est l'inverse de l'habitude. Motif : son
audit d'ecran etait du **des la premiere paire visuelle verte**, et elle a ecrit qu'elle *"decline
par ecrit un lot qui arrive trop tard plutot que de l'auditer vite"*. **La retarder derriere deux
autres ceremonies serait fabriquer exactement le retard contre lequel le planning l'avait protegee.**

**Ce que je dois lui porter au premier echange, et c'est l'obstacle 12 de mon propre planning** : la
collision qu'elle avait nommee sur les chiffres du cadran et de la piece **est reglee**. Elle ecrit
encore *"la question reste posee a `theo-archi` si elle n'a pas deja recu de reponse"*. `ADR-0073`
amende y repond nommement sur `MON-1` : un chiffre **trace en geometrie** passe la clause 4, le meme
**ecrit en element de texte** la fait rougir, mesure sur la meme piece deux fois. **Je ne l'avais pas
fait au planning et je le fais ici, par ecrit, avant son audit** - *un audit conduit en croyant une
contrainte non levee n'est pas le meme audit.*

---

## 10. Sections dues, laissees explicitement vides

- **Rapport qualite de `yanis-qa`** : **du.** Non ecrit, non anticipe.
- **Confirmation ArchUnit de `theo-archi`** : **due.** La porte technique est verte et je l'ai
  mesuree, mais **le compte de regles ArchUnit et sa confirmation sont a lui** - trois nombres ont
  deja circule sur ce sujet, et je ne fabrique pas le quatrieme.
- **Corrections du `relecteur-pedagogique`** : **dues.**
- **Avis de `nadia-prof` et Done pedagogique** : **dus.** **Je ne le prononce jamais.**
