# [Sprint s57] Palier 4 - avis de faisabilite de `theo-archi` : lots candidats, `MAT-CE1-14` et `A3`, trois instances en parallele

- **Date** : 2026-10-21, ecrit a partir de 18:40 (UTC+02:00), heure lue par `date`.
- **Auteur** : `theo-archi`.
- **Arbre** : `round-two` a `50dbd73f`. Le checkout est partage : pendant l'ecriture, `git status --short` montre des fichiers modifies et non commites d'autres agents (`DEC-0062`, `DEC-0123`, `DEC-0124`, matrice, fichiers de `nadia-prof`) ; je ne les ai pas lus et rien ici n'en depend.
- **Lu** : `preparation-palier-4-camille.md` ; `DEC-0120` A.126 a A.132 ; `composition-palier-3.md` sections 4 a 6 et 12 ; `cloture-palier-3.md` sections 3, 8 a 10, 13 et 14 ; les stories du palier 4 en fin de `docs/backlog/backlog.md` ; mes fichiers du palier 3.
- **Ce que ce fichier ne fait pas** : il ne compose pas le palier (`sofia-sm`), ne fixe ni borne, ni prix, ni instance (`nadia-prof`), ne choisit ni une forme pedagogique ni l'ordre des lots (`camille-po`). Il n'ecrit ni code, ni `ADR`, ni story. Aucun identifiant de condition : les reperes `LP1` a `LP9` et `QP1` a `QP7` sont ceux de `preparation-palier-4-camille.md`, les miens (`V`, `T`, `P`) n'appartiennent qu'a ce fichier.
- **Echelle** : celle de `avis-theo-preparation-palier-3-ce1.md` section 0. **S** = une liste ou une constante d'un test existant, sans regle neuve ; **M** = un garde neuf, ou une option d'une mecanique existante avec l'amendement de son `ADR` ; **L** = une mecanique neuve, ou un `ADR` qui en remplace un autre.

## 0. Verdict

| Code | Point | Avis |
|---|---|---|
| `V1` | `LP1` a `LP8` | **Faisables en l'etat, sans code de production.** Mecaniques existantes ; le code est celui des tests de lot et de trois listes de tests (section 1) |
| `V2` | `LP9`, `MAT-CE1-14` avec grille | `A3` ne bloque pas une grille carree. **La contrainte reelle est la taille de grille avec etiquettes dessinees, et le nombre de zones par item, jamais servi au-dessus de 6.** Forme (b) : deux paquets, sans code ; un seul paquet est **L** (section 2) |
| `V3` | Trois instances en parallele | Faisable si les verify complets passent **un a la fois** et les fusions **en file**, avec un seul verify complet par fusion. Ne pas restructurer `catalogue.yaml` ni l'inventaire (section 3) |
| `V4` | Avant le palier | Le garde generique `E3` (pret, plafond 0 mesure), l'extension de `verifier-chemins.sh` (ecart 39), les regles de file de `sofia-sm`. Aucun `ADR` n'est un prealable des lots (section 4) |

## 1. Lots candidats

Base commune, mesuree sur les deux lots du palier 3 dont la forme est reprise : un lot de branchement commite le paquet (ou rien s'il existe), un `PaquetCe1<Nom>Test` de 650 a 711 lignes, ses catalogues de contre-exemple, 6 lignes de `catalogue.yaml` et 11 lignes de `TexteAccentueExposeParLePortEntrantTest` (`git show --stat 43cd625d` : 10 fichiers ; `git show --stat bd99abcf` : 9 fichiers). Un lot d'EMC ajoute une entree a `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (`43cd625d`, +4 lignes ; `K2`, `backlog.md:23358`). Aucun des lots ci-dessous n'ajoute de classe de production : **aucune regle ArchUnit n'est touchee**, compte inchange (39 regles reelles, `confirmation-archunit-palier-3.md:42`).

| Lot | Ligne | Mecanique | Code | Taille | Ce qui reste, et a qui |
|---|---|---|---|---|---|
| `LP1` | `FRA-CE1-21`, `ce1-mots-invariables` en saisie | `saisie`, existante (`domain/.../mechanics/saisie/`) | paquet passe a `type: saisie` (contenu) ; **la liste en egalite `PAQUETS_DE_SAISIE_SERVIS_AU_2026_08_31` rougit** et gagne une entree (`LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest.java:114-119`) ; test du lot | **S** | `QP1` (section 1.1) ; `DA4` concerne ce lot plus qu'aucun autre (section 1.2) |
| `LP2` | `MAT-CE1-10`, `ce1-masses-contenances` | `bandit-manchot`, textuel | entree de catalogue, test du lot, inventaire | **S** | les gardes derives du catalogue ne l'ont jamais juge : verdict **non verifie** avant le premier `verify` du lot |
| `LP3` | `MAT-CE1-11`, `ce1-calendrier-dates` | `bandit-manchot`, textuel | idem | **S** | idem |
| `LP4`, `LP5`, `LP6` | `EMC-CE1-02`, `-03`, `-04`, paquets a ecrire | `bandit-manchot`, textuel, forme de `ce1-emc-biens-communs` | paquet, entree, test, inventaire, une entree `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` chacun | **S** | noms proposes `ce1-emc-regle-sanction`, `ce1-emc-droit-devoir`, `ce1-emc-interet-commun` (`backlog.md:23425`, `:23442`, `:23457`) : conformes au patron `<niveau>-emc-<sujet>`, meme lecture que `X5a` (`reponses-theo-questions-stories-palier-3.md:13`) ; aucun n'existe (`ls contenu`) |
| `LP7` | `MAT-CE1-15` illustre, `ce1-figures-planes` | `bandit-manchot` avec illustration | paquet reecrit, entree, test ; **aucun dessin neuf** : les douze `figure-*.svg` existent, dont `figure-triangle-rectangle.svg` (`28150cd4`, 2026-08-31) | **S** | le cliquet de poids ne bouge pas tant qu'aucun octet n'entre ; `FIG-CE1-3` (angle visiblement droit) est lu par `nadia-prof` (`backlog.md:23569`), donc aucun garde par famille de dessin. Mon avis du palier 3 disait le contraire sur le triangle rectangle : corrige ce jour dans son amendement |
| `LP8` | `FRA-CE1-03`, `ce1-mots-irreguliers` | `bandit-manchot`, textuel | entree, test, inventaire | **S** | la part *"reconnaissance immediate"* (mesure d'un temps) reste **L** et non demandee (`avis-theo-preparation-palier-3-ce1.md`, ligne `FRA-CE1-03`) ; cousinage a `nadia-prof` |
| `LP9` | `MAT-CE1-14` avec grille | voir section 2 | voir section 2 | (a) **S** ; (b) **S** a deux paquets, **L** a un seul | `QP3`, et la mesure d'ecran de la section 2.3 |

### 1.1 `QP1` : l'apostrophe d'*"Aujourd'hui"* en saisie

Fait : `ce1-mots-invariables.yaml:45` porte `bonneReponse: "Aujourd'hui"` ; `NormalisationDeSaisie.replier` retire les signes combines et les blancs et replie la casse, rien d'autre (`NormalisationDeSaisie.java`, Javadoc et methode `replier`). Une apostrophe typographique tapee par un clavier qui la substitue est donc jugee fausse.

| Option | Ce qu'elle touche | Taille |
|---|---|---|
| `P1` | `formesAcceptees` de l'item porte la graphie a apostrophe typographique, a cote de `formeDeReference` | contenu seul, aucun code. Le bareme est a `nadia-prof` (`backlog.md:23375`) |
| `P2` | `replier` replie les apostrophes | **M** : amendement d'`ADR-0058`, change le verdict de tous les paquets de saisie servis, donc celui de `cp-mot-dicte` (Done de s26) |
| `P3` | l'item est retire | contenu seul ; a `nadia-prof` |

**Mon avis : `P1`**, la moins de pieces mobiles ; la traversee de `LesFormesAccepteesDUnPaquetDeSaisieServiSontLeBaremeReelTest` la juge sans changement. Ce qui me ferait changer d'avis : un second mot a apostrophe dans un paquet de saisie ; deux occurrences justifieraient `P2`.

### 1.2 `DA4`, `QP4` : les mots proposes au-dessus du clavier

**Non instruit, et je ne l'instruis pas ici** : aucune source externe n'est lisible dans cette tache, et je n'affirme pas un attribut que je n'ai pas lu. Ce qui est sur, par le code : le produit ne controle que le HTML servi ; `EcranDeSaisieTest` peut garder un attribut, pas l'effet du clavier (`ADR-0058`, amendement du 2026-10-21, section 3). **Ce que je signale** : la reserve de `nadia-prof` (*"un mot propose au-dessus du clavier n'est pas un mot ecrit"*, `backlog.md:23381`) pese plus sur `LP1`, dont la competence est l'orthographe du mot, que sur `FRA-CP-09`. Si elle vaut condition pour `LP1`, c'est a elle de l'ecrire ; ce n'est pas un prealable technique.

`QP2` (gardes de `texteAVoixHaute` d'`ADR-0075` et texte de cinq phrases) vise `FRA-CE1-27`, renvoye a s58 ou a la fin d'A' (`preparation-palier-4-camille.md:88`) : je reponds quand sa story est engagee, pas ici.

## 2. `MAT-CE1-14` avec grille (`LP9`)

### 2.1 `A3` ne bloque pas une grille carree

`A3` (garde-fou 3 qui derive la taille rendue de la largeur servie) n'est requis que pour un dessin non carre : sur un carre de 352 px le calcul du garde est exact (`ADR-0080...md:1164-1166`). Les quadrillages servis sont carres, `viewBox="0 0 100 100"` (`symetrie-grille-a.svg:4`). La barre de refus est **15 unites** sur un carre de 100 (44,4 px a 296), la marge de dessin 16 (`ADR-0080...md:1183-1187`).

**La contrainte est arithmetique et vient des etiquettes.** Le critere 13 de la story veut des etiquettes de colonnes et de lignes **dessinees** (`backlog.md:23541`) ; un dessin ne porte aucun texte (`ADR-0073` clause 4), elles sont des traces dans le meme carre. Avec la bordure de 2 unites des grilles servies (traits de 2 a 98, `symetrie-grille-a.svg:6-7`), pour des cases de 15 unites au moins, la bande d'etiquettes `L` vaut au plus : 4 x 4, 36 unites ; 5 x 5, 21 ; **6 x 6, 6 unites (17,8 px a 296)**. Calcul : `L <= 96 - 15 x n`. A 6 x 6 etiquete, les cases sont a la barre, sans marge, et le faux vert deja ecrit s'y applique : `LEcranDeMancheDuJeuDeDesignationReelEstServiTest` ne juge que la premiere manche (`ADR-0080...md:1167-1169`), une case sous 15 unites sur un autre item passe. **Lisibilite d'un chiffre trace a 17,8 px : non mesuree.**

**Ce que je recommande** : borner la grille etiquetee a 5 x 5, ou garder 6 x 6 sans etiquettes dessinees. Le choix est a `nadia-prof` (son refus d'avance est *"une grille plus grande que 6 x 6"*, `backlog.md:23541`) et la mesure a `ines-design`. Si 6 x 6 etiquete est retenu, le seul mecanisme automatique est d'etendre le garde de l'ecran de designation a **chaque item** : **M**, par amendement d'`ADR-0080`.

### 2.2 Un ou deux paquets

Le type d'item est porte par le paquet : le chargeur lit `type` une fois par fichier (`YamlContentPackLoader.java:106`), et `ce1-symetrie.yaml` declare `type: designation` pour tous ses items. La forme (b) a deux moities de types differents : appuyer sur la case d'arrivee (designation) et choisir un code parmi trois textes sous la grille (question avec illustration). **Deux paquets et deux entrees de catalogue : aucun code. Un seul paquet : un contrat de contenu a type par item, donc **L**** ; je ne le recommande pas sans usage constate.

### 2.3 Zones par item, jamais servi a cette echelle

Le critere 11 veut *"chaque case est une zone designable"* (`backlog.md:23541`) : 16 a 36 zones par item. Le maximum servi aujourd'hui est **6** (`cp-plan-de-la-classe`), 4 sur `ce1-symetrie` (compte par `awk` des lignes `      - id:` par item). Avec `emplacementDuLibelleDeZone: hors-de-la-boite`, 36 boutons numerotes et une legende de 36 libelles s'ajoutent sous le dessin : effet sur le pli a 744 x 551 **non mesure**, et c'est le lieu exact des reserves `DA3` du palier 3. L'autre valeur admise est `dans-la-boite` (`DesignationContentSourceFactory.java:50-52`, `:76-84` : toute autre valeur est refusee au chargement ; le `au-milieu` du depot est un paquet fautif de test). **Mesure prealable a l'engagement, par `ines-design`** : un item de 6 x 6 a 36 zones, dans les deux emplacements, a 744 x 551 et a 320 px. Si aucun emplacement existant ne tient, un emplacement neuf est **M** (amendement d'`ADR-0094`, fabrique de designation).

### 2.4 Ce qu'il faut amender, et cout

| Quoi | Quand | Taille |
|---|---|---|
| `ADR-0080`, section 6 (*"A3 pour s52"*) : un paragraphe *"A3 pour `MAT-CE1-14`"* qui ecrit la contrainte de 2.1 et la borne retenue | dans le commit qui engage la story, comme `DA5` le prevoit (`confirmation-archunit-palier-3.md:89`) | texte seul si la grille est bornee a 5 x 5 ou non etiquetee ; **M** si 6 x 6 etiquete (garde par item) |
| Cliquet de poids, en egalite : `POIDS_TOTAL_MAXIMUM = 69203`, plafond par dessin 4633 octets (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java:279`, `:355`, `:833`) | dans le commit qui installe les octets, sur la valeur mesuree (`ADR-0077` decision 6) | **S**. Ordre de grandeur, non mesure : une grille servie pese 1462 a 2245 octets (`wc -c symetrie-grille-*.svg`) ; un dessin par item a vingt items ajoute 30 a 90 ko. Aucun plafond absolu ne s'y oppose dans le depot |
| `DESSINS_DE_DESIGNATION` (`:205`) | meme commit | **S** |

**`A3` n'est donc pas un prealable de `LP9`** ; ses prealables sont `QP3` et la mesure de 2.3. Que la grille soit un dessin au sens de `Q-06` et de `PAR-13` est a `camille-po` et `nadia-prof`, pas a moi.

## 3. Trois instances en parallele

### 3.1 Les mesures, avec leur source

| Mesure | Valeur | Source |
|---|---|---|
| `verify` complet, cloture du palier 2 | 4 min 57 s | `cloture-palier-2.md:63` |
| `verify` complet sur arbre propre, palier 3 | 6 min 23 s, puis 11 min 51 s, 12 min 39 s, 11 min 56 s, 11 min 17 s, 11 min 55 s, 12 min 11 s | `rapport-sofia-dev-palier-3.md:142`, `:195`, `:230`, `:269`, `:305`, `:340`, `:369` |
| meme mesure, `theo-archi` | 12 min 36 s | `confirmation-archunit-palier-3.md:24` |
| etape *"Package du fat jar"* (`-pl bootstrap -am package`, tests rejoues) | 9 min 27 s a 10 min 22 s | `rapport-sofia-dev-palier-3.md:196`, `:231`, `:270`, `:306`, `:341`, `:370` |
| `verify` a 7 h 26 sous quatre agents | **non verifie** : aucun fichier du depot ne le porte | `cloture-palier-3.md:263` |
| *"9 min 30 s a 12 min 40 s"* (`DEC-0120:2001`, `preparation-palier-4-camille.md:199`) | **ecart** : les durees de 9 min 27 s a 10 min 22 s sont celles du `package`, pas du `verify` ; le `verify` mesure va de 11 min 17 s a 12 min 39 s. A `camille-po` | lignes ci-dessus |
| cause du passage de 5 a 12 min | **non verifie** : le nombre d'agents qui compilaient n'est pas mesure (`cloture-palier-3.md:263`) ; les tests ne passent que de 2669 a 2855 | `cloture-palier-2.md:210`, `confirmation-archunit-palier-3.md:24` |
| `verify` complet, ce jour, copie propre de `50dbd73f` | **11 min 39 s**, 2855 tests, 0 echec | section 3.5 |

### 3.2 Ce qui casse

1. **Trois fichiers partages par les lots.** `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest` (`:88`, une seule liste, trois sections ou chaque lot ajoute ses lignes au meme endroit) : 4 conflits sur 5 fusions de lots au palier 3, resolus par ajout des deux cotes, 0 ligne supprimee (`cloture-palier-3.md:73`). **Le troisieme est neuf au palier 4** : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`, que chacun des trois lots d'EMC touche (`K2`), et que le palier 3 n'a touche qu'une fois. Les trois lots d'A' sont d'un meme developpeur, l'un apres l'autre (`RG4`, `composition-palier-3.md:97`) : une branche coupee avant la fusion de la precedente entre en conflit avec elle.
2. **La contention de build** (3.1) : six a huit lots, plus leurs corrections, plus les rejeux de jar.
3. **Le checkout partage.** Les gardes de `bootstrap` qui lisent `squad/` par `Files.walk` lisent les fichiers non suivis des autres agents ; le premier rouge du palier 3 en venait (`cloture-palier-3.md:252`, `:265`). Ce fichier est ecrit dans ces conditions (en-tete).
4. **Les identifiants et les numeros d'`ADR`** : un seul registre, un seul `ADR-index.md`. Deux `ADR` crees en parallele prennent le meme numero.

### 3.3 Ordre de fusion propose, non implemente

**Ne pas restructurer.** Une entree par fichier pour le catalogue changerait le chargeur unique et les 60 fichiers Java qui nomment `catalogue.yaml` (`git grep -l "catalogue.yaml" -- '*.java' | wc -l`) : **L**, pour supprimer des conflits qui ont coute 0 ligne perdue. Scinder l'inventaire en fichiers par lot change la forme d'un garde d'`ADR-0039` : **M**, pour le meme gain. Ce qui me ferait changer d'avis : une resolution de conflit qui supprime une ligne, constatee par `git diff-tree --cc`.

**Fusion en file**, regle de `composition-palier-3.md:132` reprise et precisee :

1. **Un seul `verify` complet a la fois sur le poste**, tous agents confondus. Les autres mesures sont partielles, avec `-am` et leur perimetre nomme.
2. **Une seule fusion a la fois**, premier pret premier fusionne ; resolution par ajout des deux cotes, comme au palier 3.
3. **Un seul `verify` complet par fusion.** Invariant : le `verify` qui juge une fusion est lance sur un arbre qui contient le sommet courant de `round-two`. Si la branche du lot a fusionne `round-two` (fusion, jamais rebase) avant son propre `verify`, la fusion dans `round-two` n'est pas rejugee quand `git diff --quiet <commit verifie> <commit de fusion>` sort 0 ; sinon elle l'est. La mecanique exacte est a `sofia-sm`.
4. **Les lots d'une meme instance partent du sommet qui contient le precedent** : le deuxieme lot d'A' est coupe apres la fusion du premier.
5. **Les postes sans fichier partage passent en tete, groupes** (section 4) : ils ne touchent ni le catalogue ni l'inventaire.
6. Le `verify` se lance sur une copie ou un worktree propre, jamais dans le checkout partage (3.2, point 3).

**Ce que la file coute, calcule et non mesure** : a `verify` seul, 6 lots x 11 min 17 s a 12 min 39 s = **1 h 08 a 1 h 16** de file ; au job `build` entier (`verify` puis `package`, `.github/workflows/ci.yml` job `build`), de 11 min 17 s + 9 min 27 s = 20 min 44 s a 12 min 39 s + 10 min 22 s = 23 min 01 s par fusion, 6 fusions = **2 h 04 a 2 h 18** ; a 8 lots, 1 h 30 a 1 h 41, ou 2 h 46 a 3 h 04. A mettre en regard d'un chiffre de 7 h 26 que le depot ne porte pas.

### 3.4 Ce que le parallele apporte reellement

Le parallele de developpement est **deux**, pas trois : `marc-dev` sur A, `lea-dev` sur B et A' l'un apres l'autre (`QP6`, option `O1`, recommandation de `sofia-sm`, `cloture-palier-3.md:325`) ; trois avec l'option `O2`. Le parallele de lecture (`nadia-prof`, trois instances) n'ajoute aucune compilation. **Ma recommandation** : trois instances de lecture, deux developpeurs, une file de fusion et un jeton de `verify` complet. L'option de capacite est a l'utilisatrice et a `sofia-sm`.

### 3.5 Mesure de ce jour

`bash outils/mvn-jdk21.sh -B clean verify` sur la racine d'une copie de `50dbd73f` par `git archive`, hors depot, sans `-pl`, 2026-10-21 de 18:31:39 a 18:43:19 : `BUILD SUCCESS`, exit 0, **11 min 39 s** (`time`, `real`). 2855 tests, 0 echec, 0 erreur, 0 ignore (120, 31, 523, 1794, 381, 6, lignes `Tests run` de fin de module ; recompte par l'attribut `tests` des 319 `TEST-*.xml` : 2855). JDK 21.0.10, version que rapporte la machine virtuelle des tests (propriete `java.version` des rapports). Charge du poste : un seul autre processus `java.exe` au lancement (`tasklist`), aucune lecture pendant la mesure.

**Ce que cela dit** : sur ce poste, un `verify` complet dure environ 12 min **sans contention connue**. Le passage de 4 min 57 s a 11-12 min n'est donc pas, ou pas seulement, un effet de concurrence ; sa cause reste **non verifiee**. La phrase *"l'ecart de 12 min a 7 h 26 est l'effet de la concurrence"* (`DEC-0120:2005`) ne peut pas s'appuyer sur ce depot. La file de 3.3 se calcule donc sur 12 min par `verify`, pas sur 5.

## 4. Ce qui s'ecrit avant le palier, et ce qui peut attendre

| Quoi | Avant ou pendant | Motif | Qui |
|---|---|---|---|
| Garde generique `E3` (`DA1`, `DA2`), `LesPropositionsEtLesTextesSupportsDeCe1TiennentLeursBornesTest`, avec son `ADR` dans le meme commit (`decision-theo-garde-ce1-e3.md:61`, `:76`) | **avant, recommande, non bloquant** | Pret : forme `F1` decidee ; le perimetre par matiere est ecrit par `nadia-prof` (`reponses-nadia-questions-stories-palier-3.md:18`, `Q-X1`) ; **plafond 0 mesure a `50dbd73f`** (`decision-theo-garde-ce1-e3.md`, amendement du 2026-10-21). Ecrit avant, il juge chacun des six a huit paquets a son commit, et les tests de lot n'ont plus a reasserter ces regles. Ecrit pendant, un paquet fusionne avant lui peut rendre rouge son commit (`reponses-theo-questions-stories-palier-3.md:16`). **Ecart** : la story le dit bloquee par `CA4` absent (`backlog.md:23532`) ; `CA4` est fourni depuis le palier 3. A `camille-po` | developpeur designe par `sofia-sm` ; forme : moi |
| Extension de `verifier-chemins.sh` (ecart 39), section 5 | avant | trois instances produisent des releves a captures ; le motif actuel (`verifier-chemins.sh:30-35`) laisse passer un chemin de session | moi, un commit |
| Regles de file et de jeton (3.3), table d'isolation, reprise apres arret | avant, a la composition | `cloture-palier-3.md:379` | `sofia-sm` |
| Commit des commentaires de `src/` : ecarts 33 et 40 a 44, et la part code des ecarts 37 et 38 (`saisie.html`, Javadoc d'`EcranDeSaisieTest`) | avant ou en tete de file, groupe | aucun fichier partage avec les lots ; un seul `verify` | `sofia-sm` (engagement) |
| `QP1` | avant `LP1` | bareme | `nadia-prof` |
| Amendement d'`ADR-0080` pour `MAT-CE1-14` | pendant, au commit qui engage `LP9`, si `LP9` entre | `LP9` n'est dans aucune des trois compositions proposees (`preparation-palier-4-camille.md:15`) | moi |
| `DA4` | pendant ou apres | non prealable (1.2) | moi |
| Story | aucune neuve requise par l'architecture | | |
| `ADR` | aucun prealable aux lots | `V1` | |

## 5. Ecarts 34 a 39 du releve de cloture : traitement

| No | Traitement |
|---|---|
| 34 | **corrige** : amendements dates de `reponses-theo-questions-stories-palier-3.md` (base declaree fausse, `ee9ecd44` au lieu de `98dc6574`, ancres nommees) et d'`avis-theo-preparation-palier-3-ce1.md` (numeros justes a `ad65827e`, decales a `50dbd73f`, ancres nommees) |
| 35 | **corrige** : le script est ecrit en entier dans l'amendement de `decision-theo-garde-ce1-e3.md`, rejoue a `ad65827e` (sortie identique a la section 2) et a `50dbd73f` |
| 36 | **corrige** : correction datee en fin d'`ADR-0058` (trois amendements precedents, quatrieme amendement) |
| 37 | **tranche** : la page MDN est de forme 3, `recu de MASTER, sans source dans le depot` ; `DEC-0124` section 4 (`59aabaa5`) gouverne par anteriorite ; la citation d'`ADR-0058` y est alignee ; la decision ne change pas (correction d'`ADR-0058`, point 2) |
| 38 | **corrige pour l'`ADR`** (renvoi a `DEC-0124` section 0) ; **non corrige** dans `saisie.html` et la Javadoc d'`EcranDeSaisieTest` : fichiers de `src/`, commit de code a engager (section 4) |
| 39 | **tranche** : la forme n'est pas toleree. `%TEMP%` est une variable, mais le segment qui suit encode le chemin absolu du checkout (lettre de lecteur, dossiers) et un identifiant de session : c'est une valeur figee de poste, le critere d'`ADR-0010` est la portabilite. **Decision a ecrire avec sa regle, dans un seul commit** : amendement d'`ADR-0010`, un motif de plus dans `verifier-chemins.sh` (une lettre de lecteur encodee en segment, `[\/][A-Za-z]--[0-9A-Za-z]`), un cas de test dans `verifier-chemins-test.sh` pris sur le materiau reel (la ligne du releve). **Dette exacte a `50dbd73f`** : `git grep -c -E '[\\/][A-Za-z]--[0-9A-Za-z]' HEAD` rend 2 lignes dans un seul fichier, `releve-relecteur-cloture-palier-3.md`, qui cite le chemin comme preuve ; `releve-ecran-ines-palier-3.md` en portait 96 et n'en porte plus (`50dbd73f`). Elles sont corrigees dans le meme commit, par leur auteur, ou le commit attend. Ce qui me ferait changer d'avis : une occurrence legitime de ce motif dans un fichier de code |

**Ce qui n'est pas a moi** dans le releve : les ecarts 1 a 33 et 40 a 44, routes par `cloture-palier-3.md` section 9.

## 6. Jobs de `ci.yml` et mesures lances pour ce fichier

- **`build`, partie `verify`** : section 3.5, sur `50dbd73f` **sans** les fichiers de ce geste.
- **`build`, mesure partielle sur les fichiers de ce geste** : les cinq fichiers (ce fichier, les amendements de `avis-theo-preparation-palier-3-ce1.md`, `reponses-theo-questions-stories-palier-3.md`, `decision-theo-garde-ce1-e3.md`, la correction d'`ADR-0058`) copies dans la meme copie propre ; `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest,LesGardeFousSontMisALEpreuveTest -Dsurefire.failIfNoSpecifiedTests=false`, `-am` present, perimetre ces quatre classes : **28 tests, 0 echec**, `BUILD SUCCESS`, 18:44 a 18:47, puis relance sur la version de ce fichier qui porte les sections 3.5 et 6 : 28 tests, 0 echec, 18:47 a 18:50. Seule cette phrase-ci a ete ecrite apres.
- **`chemins`** : `verifier-chemins.sh` sur les cinq fichiers : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, exit 0. Le cas de test du garde-fou n'est pas relance (garde non modifie).
- **`build`, etape `package`** ; **`prefixes`** ; **`outils`** : **non lances**. Aucun commit, aucun outil touche.
- **Le Done technique n'est pas prononce par moi.**

## Decisions prises

Aucune `ADR` ni `DEC` creee. `ADR-0058` porte une correction datee (ecarts 36 a 38), sans changement de statut ni de regle ; `ADR-index.md` inchange, aucun statut ne bouge. La decision sur l'ecart 39 est prise dans son sens et **n'est pas ecrite comme regle** : elle le sera par amendement d'`ADR-0010` avec son motif et son cas de test, dans un seul commit ; jusque-la elle n'est pas une regle. Compte des regles ArchUnit inchange.

## 7. Complement du 2026-10-21, 21:31 (UTC+02:00) : `MAT-CE1-14` en forme (a) seule

- **Mots de l'utilisatrice**, cites par l'agent lanceur : *"QP3 c'est une grille déssinée"*. Lecture fixee par `camille-po` : forme (a) seule (`backlog.md:23715-23734`). **Arbre** : `round-two` a `e4d54795`, heure lue par `date`.
- **Ce que cette section ne fait pas** : ni prix, ni taille de grille, ni nombre d'items, ni reponse a la question d'`A.143` (`DEC-0120`) : elles sont a `nadia-prof`, `camille-po` et l'utilisatrice. Aucun identifiant neuf.

### 7.1 Deux corrections de ma section 2, et de ce qui la cite

1. **La section 2 calculait a 352 px, la forme (a) rend a 176 px.** Un item `question` montre son dessin dans `.image-item` (`manche.html:121`), plafonne a `11rem` (`recreo.css:447-449`). Les 352 px sont ceux de `.image-designation` (`designation.html:43`, `recreo.css:492-493`), absents de la forme (a). Les *"17,8 px a 296"* de 2.1, recopies par le critere 6 de la story (`backlog.md:23316`, `:23734`), ne valent donc pas pour (a). Les chiffres justes sont en 7.3.
2. **Les grilles servies pesent 1442 a 2215 octets** sur le perimetre du cliquet (fichier commite, LF : `git show HEAD:<fichier> | tr -d '\r' | wc -c`). Mes *"1462 a 2245"* de 2.4 etaient des `wc -c` sur l'arbre de travail, fins de ligne CRLF.
3. La phrase *"marge de 1,7 unite au-dessus de la barre de 15"* ne vient pas de ce fichier. 2.1 ecrit le contraire : a 6 x 6 etiquete, les cases sont *"a la barre, sans marge"*. Elle est de toute facon sans objet en forme (a), voir 7.4.

### 7.2 Mecanique : rien a construire

`bandit-manchot` sert deja des items `question` illustres, avec une image par item : `ce1-solides.yaml` (15 `illustration:`, type `question` ligne 3), `ce1-sens-multiplication.yaml:35`, `:44`, `cp-figures-planes.yaml`. **Un seul paquet** suffit, puisque tous les items sont `question` et que le chargeur lit `type` une fois par fichier (`YamlContentPackLoader.java:106`). C'est la reponse au point *"un ou deux paquets"* de `DEC-0120:2203`.

**Ce qui existe pour `MAT-CE1-14`** : `ce1-deplacements-quadrillage.yaml`, 20 items en texte, grille de 4 x 4, aucune illustration (en-tete, ligne 1), absent de `catalogue.yaml` (`grep -c` rend 0). Ses 20 enonces nomment le depart par sa coordonnee (`:44`, `:85` par exemple) et trois indices disent *"Dessine le quadrillage"* (`grep -c` rend 3). Les points 2 et 5 de `arbitrage-nadia-q05-q10.md` 1.3 l'obligent donc a etre reecrit. **`cp-deplacements-quadrillage` n'a pas de SVG** : son en-tete l'ecrit (*"ce paquet ne porte aucune illustration"*, `cp-deplacements-quadrillage.yaml:46`), et `grep -c svg` rend 0. **Les cinq `symetrie-grille-*.svg` ne sont pas reutilisables tels quels** : declares a 352 px (`symetrie-grille-a.svg:4`), sans etiquettes, trait de 0,7 (`:5`). A 176 px, ce trait rend `floor(0,7 x 1,76) = 1` px, sous le plancher de 2 px du garde de geometrie (`ChaqueIllustration...Test.java:240`, `:554`, calcul `:782-797`).

### 7.3 Les dessins : combien, quel gabarit, quel nom, qui

**Nombre : un dessin par item, soit 20 si le paquet garde 20 items** (critere 1 de la story, `backlog.md:23725` ; 20 items, `grep -c "^  - enonce"`). Chaque item marque une case qui lui est propre (objet, depart, ou depart et arrivee), comme `cp-monnaie` et `ce1-solides`. Le nombre d'items est a `camille-po` et `nadia-prof`.

**Gabarit**, chaque point juge par un garde existant :

| Propriete | Valeur | Ce qui la garde |
|---|---|---|
| Cadre | `viewBox="0 0 100 100"`, `width="176" height="176"` | plafond de 176 px (`ChaqueIllustration...Test.java:172`, `:539-546`) ; meme rapport (`:547`) |
| Trait | au moins 1,14 unite, donc 1,2 : `floor(1,2 x 1,76) = 2` px | plancher (`:240`, `:554`) |
| Etiquettes | tracees, bande de 10 unites a gauche (lignes, de bas en haut) et en bas (colonnes, de gauche a droite) | aucun `<text` ni `<tspan` (`LIllustrationDeContenuResoutEtAucuneNeDortTest.java:611`) |
| Liste `DESSINS_DE_DESIGNATION` | **le dessin n'y entre pas** | `:205`, `:539` : y figurer serait declarer une designation qui n'existe pas |
| Nom | proposition : `quadrillage-a` a `quadrillage-t` | patron des familles servies (`solide-cube-a`, `symetrie-grille-a`). Aucun fichier ne commence ainsi (`ls static/illustrations`) |

**Taille rendue, calcul et non mesure** : la grille occupe `96 - 10 = 86` unites. Une case vaut 14,3 unites, soit 25,2 px a 6 x 6 ; 30,3 px a 5 x 5 ; 37,8 px a 4 x 4. Pour un glyphe de 8 unites, l'encre rend 14,1 px, juste au plancher de 14 px qu'`ines-design` pose pour un chiffre trace. **Ce plancher n'est verifie par aucun garde** (`ChaqueIllustration...Test.java:233-238`).

**Qui dessine.** Toute la geometrie est arithmetique : lignes, positions des etiquettes, marques placees au centre d'une case calculee. **Un script la produit sans juger de l'oeil.** Je l'ai fait en prototype hors depot, non commite (7.4). **Ce que le script ne peut pas produire** : la forme des glyphes et des marques. Le corpus ne definit que les chiffres 0, 1, 2 et 5 (`grep -ho '<path id=' static/illustrations/*.svg` rend `g0`, `g1`, `g2`, `g5`), dessines par `ines-design` (`monnaie-en-tout-a.svg`, en-tete : *"Glyphes d'ines-design, recopies a l'identique"*). **Il manque A a F, et 3, 4 et 6**, plus trois marques distinctes (objet, depart, arrivee). Elles sont a `ines-design`, puis le script les recopie dans les 20 fichiers. **Le script n'a pas a etre commite** : ce sont les 20 fichiers qui sont juges (7.5), et non l'outil qui les a produits. Un script commite sous `outils/` devrait porter un juge pur et son test (`ci.yml:90-96`). Ce qui me ferait changer d'avis : une deuxieme regeneration des 20 fichiers.

### 7.4 Cliquet de poids : il bouge, et il ne l'interdit pas

**Etat mesure** : 56 fichiers sous `static/illustrations` (`git ls-files | wc -l`). Total de 69 203 octets (somme de `git show HEAD:<f> | tr -d '\r' | wc -c` sur les 56), egal a `POIDS_TOTAL_MAXIMUM` (`:355`). Les deux constantes sont **en egalite** (`:830-837`). Le plus gros dessin reste `frise-journee-ecole.svg`, a 4633 octets (`:279`).

**Prototype mesure**, ecrit par un script hors depot, non commite. Glyphes provisoires de ma main, que le monde ne dessinera pas ainsi. Deux lignes de commentaire d'en-tete, une marque de depart. Taille LF : **6 x 6 : 3060 octets ; 5 x 5 : 2662 ; 4 x 4 : 2277** (`wc -c`). Le prototype ne porte aucun `<text` (`grep -c` rend 0). Le garde de geometrie n'a pas ete lance sur lui.

| Grille | 20 dessins (calcul sur le prototype) | Total apres | Hausse |
|---|---|---|---|
| 6 x 6 | 61 200 | 130 403 | +88 % |
| 5 x 5 | 53 240 | 122 443 | +77 % |
| 4 x 4 | 45 540 | 114 743 | +66 % |

Ces chiffres **remplacent mon ordre de grandeur de 2.4** (*"30 a 90 ko"*, non mesure) et la borne de 92 660 octets d'`A.143`, qui la calculait au plafond par dessin. Marge d'erreur non mesuree : les glyphes reels d'`ines-design`, les commentaires d'en-tete reels, une marque d'arrivee en plus sur les items de codage (environ 80 octets par fichier, d'apres le prototype). **Le plafond par dessin (4633) ne bouge pas** tant qu'aucun fichier ne le depasse : le prototype 6 x 6 en est a 1573 octets.

**Ce qu'il faut faire** : **S**, deplacer `POIDS_TOTAL_MAXIMUM` sur la valeur mesuree dans le commit qui installe les octets, avec une ligne de motif dans la Javadoc (`:281-353`), comme les dix mouvements precedents. Rien dans le depot n'interdit ce mouvement. Que le poids soit acceptable reste la question d'`A.143`, posee a l'utilisatrice et non a moi.

### 7.5 `ADR-0080`, et la condition de `nadia-prof` en test

**`ADR-0080` n'est pas a amender pour la forme (a).** Il gouverne la designation. Il ecrit lui-meme qu'un paquet `question` est hors du garde-fou 3 (`ADR-0080...md:1164-1165`). La barre de 15 unites est celle d'une zone a toucher (`:1183-1186`), et la forme (a) n'en a aucune. Le paragraphe *"A3 pour `MAT-CE1-14`"* que 2.4 prevoyait et que `DEC-0120:2203` attend est sans objet. **Ecart a `camille-po`** : `DEC-0120:785` (*"`MAT-CE1-14` reste conditionnee a `A3` d'`ADR-0080`"*) ne vaut plus pour (a).

**Ce qu'un agent ne peut pas faire, c'est voir que les 20 dessins disent la meme chose que les 20 reponses.** C'est la condition que je fais executer. Elle prend la forme d'**un test de lot qui croise chaque dessin avec son item**. Il vit dans `bootstrap`, seul module qui lit a la fois le contenu et `static/illustrations` (le motif du placement est ecrit a `ChaqueIllustration...Test.java:195-199` ; le precedent est `LIllustrationDeContenuResoutEtAucuneNeDortTest`). La forme de lecture du dessin est imposee : une marque porte un `id` (`depart`, `arrivee`, `objet`), une etiquette est un `<use>` vers son glyphe. Le test en deduit la case par les coordonnees, jamais par le nom.

| Point de 1.3 (`arbitrage-nadia-q05-q10.md:68-80`), garde par 8.1 (`arbitrage-nadia-palier-4.md:275`) | Encodable | Ce que le test refuserait reellement |
|---|---|---|
| 1, et 8 *"plus grande que 6 x 6"* | oui, dans le dessin : nombre de lignes, ecarts egaux | une grille de 7 x 7, des cases inegales |
| 1, `DEP-CE1-1` (un tiers des chemins change d'axe) et `DEP-CE1-4` (trois codes de meme longueur, formes des quatre mots) | oui, dans l'enonce et les reponses | un paquet sous le tiers, un code plus court |
| 2, depart marque | oui : marque `depart` presente ; la case d'arrivee calculee depuis cette marque et les pas de l'enonce **egale `bonneReponse`** | un depart dessine sur la mauvaise case, ce que seule une lecture de l'image verrait autrement |
| 2, *"ne la nomme pas par sa coordonnee seule"* | oui, des que `nadia-prof` a repondu a la question de la story (`backlog.md:23726`) | un enonce qui recopie la coordonnee du depart |
| 4, codage | oui : marques `depart` et `arrivee` ; seul le bon code mene de l'une a l'autre ; chaque code mene a une case reelle | un distracteur qui mene aussi a l'arrivee, ou qui sort de la grille |
| 5, axes | oui : colonnes dans l'ordre de A vers la droite, ligne 1 la plus basse, sur les 20 dessins ; aucun indice *"Dessine le quadrillage"* | un axe inverse sur un seul dessin |
| 8, distracteur hors grille ; item sans grille | oui : chaque distracteur dans la grille ; chaque item porte une illustration | une case F7, un item sans dessin |
| Se reperer | oui : la case de la marque `objet` egale `bonneReponse` | un objet dessine ailleurs que la reponse |
| 6, `LON-CE1`, `TRV-CE1` | **non verifie** ici, gardes existants ou garde `E3` (section 4) | - |
| Lisibilite des glyphes et des marques ; *"lecture complete avant verdict"* | **non** : aucune propriete du fichier ne le dit | audit d'`ines-design` et de `nadia-prof` |
| 7, mention | non : texte de la fiche | `nadia-prof` |

**Preuve exigee** : les morsures portent sur les 20 dessins reels, mutes en memoire (une marque deplacee d'une case, un axe inverse, une grille de 7). C'est la forme du juge pur de `ChaqueIllustration...Test.java:488-495`, jamais une fixture de ma main. Ce test ne cree aucune classe de production : **aucune regle ArchUnit n'est touchee**, et je n'ecris pas d'`ADR`, car c'est le test d'un paquet. Ce qui me ferait changer d'avis : un second paquet de quadrillage illustre, qui justifierait un garde commun.

### 7.6 Taille

**M** pour le lot, et non le **S** que `LP9` portait (tableau de la section 1). Le paquet, l'entree de catalogue et le cliquet sont **S**. Le test croise est un garde neuf, donc **M** sur mon echelle (en-tete). Sans lui, le lot redevient **S**, mais les points 1, 2, 4, 5 et 8 sur les 20 dessins ne reposent alors que sur une lecture de l'image, que l'agent auteur ne peut pas faire.

### 7.7 Mesure d'ecran : faisable par `ines-design` avant l'engagement

La mesure des 36 zones de 2.3 est sans objet (`backlog.md:23734`). Le prealable que `nadia-prof` cite (`arbitrage-nadia-palier-4.md:279`) se lit donc, pour (a), comme ceci : **un dessin candidat de 6 x 6 rendu a 176 px**, encre des glyphes et marques distinctes dans une case de 25 px, a 744 x 551 et a 320 px. Il ne faut aucun code : l'ecran d'item `question` illustre est deja servi (`ce1-solides`). **Faisable avant l'engagement, dans cet ordre** :

1. `ines-design` dessine les glyphes manquants et les trois marques ;
2. le script produit un candidat ;
3. elle le substitue a l'illustration d'un item servi, sur une copie non commitee, et capture avec `outils/mesurer-rendu.sh <largeur> <url> <png> [hauteur]` (`mesurer-rendu.sh:35`).

Cout : un `package` du jar, de 9 min 27 s a 10 min 22 s au palier 3 (section 3.1). Que le script accepte une URL `file://` sur le SVG seul est **non verifie**. C'est cette mesure qui departage 6 x 6, 5 x 5 et 4 x 4, et le choix revient a `nadia-prof`.

### 7.8 Pret, pas pret

| Pret | Pas pret, et a qui |
|---|---|
| mecanique, type `question` illustre, gardes de geometrie, de texte et de resolution, entree de catalogue sur le patron existant | glyphes A a F, 3, 4, 6 et les trois marques (`ines-design`) |
| aucun `ADR` a ecrire ni a amender, aucune regle ArchUnit touchee | mesure de 7.7 (`ines-design`), puis taille de grille et nombre d'items (`nadia-prof`, `camille-po`) |
| poids estime par prototype (7.4) | reponse de l'utilisatrice a `A.143` (poids) ; reponse de `nadia-prof` sur *"par sa coordonnee seule"* et sur `PAR-13` pour (a) (`DEC-0120:2203`) |
| | paquet reecrit, 20 dessins, test croise de 7.5 : le lot lui-meme (developpeur designe par `sofia-sm`, forme du test : moi) |
| | ecart `DEC-0120:785` et chiffre du critere 6 (`backlog.md:23316`, `:23734`) : `camille-po` |

**Jobs de `ci.yml` lances pour cette section** : voir 7.9.

### 7.9 Jobs et mesures lances pour cette section

- **`build`, mesure partielle** : copie propre de `e4d54795` par `git archive`, hors depot, plus ce fichier. Commande : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` sur les quatre classes nommees en section 6. `-am` est present, le perimetre est ces quatre classes. Resultat : **28 tests, 0 echec, 0 erreur** (attributs des quatre `TEST-*.xml`), exit 0, de 21:35 a 21:39, puis relance de 21:39 a 21:42 sur la version qui porte 7.9 : 28 tests, 0 echec. JDK 21.0.10, version que rapporte la machine virtuelle des tests (`java.version`).
- **`chemins`** : `verifier-chemins.sh` sur ce fichier : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, exit 0.
- **`verify` complet, `package`, `prefixes`, `outils`** : **non lances**. Aucun code, aucun outil, aucun commit.
