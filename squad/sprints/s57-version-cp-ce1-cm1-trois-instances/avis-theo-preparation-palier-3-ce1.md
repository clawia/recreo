# [Sprint s57] Palier 3 - avis de faisabilite technique sur les lignes CE1 non couvertes (francais, mathematiques, questionner le monde, EMC, arts plastiques)

- **Date** : 2026-10-19.
- **Auteur** : `theo-archi`.
- **Base** : `round-two` a `ad65827e`. `git diff --quiet efb8d013 HEAD -- adapters domain application bootstrap pom.xml` sort 0 : le code est celui du dernier reacteur vert de `cloture-palier-2.md` section 12 (`efb8d013`, 2669 tests, JDK 21.0.10). **Je n'ai lance aucun `mvn`** pour cet avis : aucun verdict de build ci-dessous n'est le mien, et aucun n'est mesure sur une mutation.
- **Lu** : `cloture-palier-2.md` sections 8, 13, 14 ; `avis-theo-palier-2.md` sections 2 et 3 ; `confirmation-archunit-palier-2.md` sections 4 et 5 ; `squad/programme/MATRICE-couverture.md` ; `squad/programme/francais/competences-ce1.md` ; `squad/programme/mathematiques/competences-ce1.md` ; `squad/conformite/ARBITRAGE-PREALABLE-s57-instruction-questionner-le-monde-et-emc-ce1.md` ; `squad/sprints/inter-s56/avis-faisabilite-sept-dessins.md` ; les 47 paquets `ce1-*.yaml` ; `catalogue.yaml` ; `matieres.yaml` ; les classes de `domain/.../mechanics/`.
- **Ce que ce fichier ne fait pas** : il n'ecrit ni code, ni contenu, ni `ADR`. Il ne choisit ni une forme pedagogique, ni un ordre de lots, ni un plafond. Il n'attend pas `preparation-palier-3-ce1-camille.md`.

## 0. Perimetre et methode

**Les 32 lignes.** Francais et mathematiques : `awk -F'|'` sur la matrice, champs 2 (matiere), 3 (niveau) et 8 (statut), statut commencant par `non couvert` ou `en cours` (les lignes 1038 et 1443, qui contiennent ces mots dans un texte de statut `couvert-valide`, sont exclues a la lecture). Codes lus dans les deux referentiels. Rendu : francais 7 (`FRA-CE1-03`, `-06`, `-07`, `-08`, `-09`, `-11`, `-21`, matrice lignes 1441 a 1459) ; mathematiques 5 `non couvert` (`MAT-CE1-01`, `-10`, `-11`, `-14`, `-15`, lignes 1021, 1031, 1032, 1035, 1036) et 2 `en cours` (`MAT-CE1-02` ligne 1022, `MAT-CE1-09` ligne 1030). Questionner le monde 12 (lignes 2105 a 2116), EMC 4 (lignes 2553 a 2556), arts plastiques 2 (lignes 2314, 2315). Ces comptes concordent avec ceux de `cloture-palier-2.md` ligne 280.

**Mecaniques disponibles** (`find domain/src/main/java -path "*mechanic*"`) : `bandit-manchot` (`domain/src/main/java/com/recreo/domain/mechanics/banditmanchot/BanditManchot.java:15`, choix parmi des propositions ecrites ou en image, item avec `texteSupport` ou `illustration` facultatifs), `designation` (`.../designation/Designation.java:19`, choix parmi des zones enumerees d'un dessin, `ADR-0080`), `saisie` (`.../saisie/Saisie.java`, reponse tapee, repliee sur les accents, la casse et les blancs : `NormalisationDeSaisie.java:9` et `:31`).

**Fait central : 12 des 14 lignes de francais et de mathematiques ont deja un paquet, aucun n'est au catalogue.** Commande : pour chaque `contenu/*.yaml`, `grep -q "paquet: <id>$" catalogue.yaml`. Rendu : 31 paquets non branches, dont 12 de CE1, un par ligne `non couvert` de francais et de mathematiques. Ecrits a s53 (`8561498c`, `34fdd167`, `8de2927f`, 2026-10-07), jamais audites : aucune fiche dans `squad/conformite/` (`ls | grep` sur leurs noms ne rend rien). Dix sur douze portent un en-tete `# DEGRADE`.

**Echelle d'ampleur** : **S** = une constante ou une liste d'un test existant a relever, sans regle neuve ; **M** = un garde-fou neuf, ou une option d'une mecanique existante avec l'amendement de son `ADR` ; **L** = une mecanique neuve, ou un `ADR` qui en remplace un autre. Un dessin n'est pas du code : il est a `ines-design`, et je ne compte ici que le code qu'il entraine.

## 1. Ligne par ligne

### 1.1 Francais CE1

| Ligne | Paquet existant | Sert sans code ? | Ce qui demanderait du code |
|---|---|---|---|
| `FRA-CE1-03` | `ce1-mots-irreguliers` (20 items, `type: question`) | **oui**, `bandit-manchot`, une entree de catalogue | La part ecrite DEGRADE ligne 1 (*"reconnaissance immediate ... le produit ne mesure pas le temps"*) : mesurer un temps de reponse est une donnee neuve de la partie et un `ADR` neuf. **L**. `grep -iE "chrono\|minuteur\|temps de reponse"` sur `squad/decisions` ne rend aucune decision |
| `FRA-CE1-06` | `ce1-inference` (20 items, 20 `texteSupport`) | **oui**, `bandit-manchot`. Meme forme que `ce1-texte-narratif`, servi (`catalogue.yaml:913`) | aucun |
| `FRA-CE1-07` | `ce1-texte-documentaire` (20 items, 20 `texteSupport`) | **oui**, meme forme | aucun |
| `FRA-CE1-08` | `ce1-ordre-des-mots` (DEGRADE ligne 1 : choisir la phrase ordonnee parmi trois) | **oui** pour la forme degradee | Remettre les mots dans l'ordre soi-meme : aucune mecanique ne deplace des mots. `saisie` ne peut pas juger la majuscule de la ligne : elle replie la casse (`SAI-3`, `NormalisationDeSaisie.java:9`). Soit une mecanique neuve (**L**), soit une option de `saisie` qui ne replie pas la casse, avec amendement d'`ADR-0058` (**M**). Je ne recommande ni l'une ni l'autre sans forme demandee |
| `FRA-CE1-09` | `ce1-relire-une-phrase` (DEGRADE ligne 1) | **oui** | **L'en-tete DEGRADE est faux depuis le 2026-10-09.** Il ecrit que l'explication qui redonne la phrase juste (`RLP-CE1-3`) *"n'est pas servable"* a cause de `ChaquePaquetExpliqueSesItemsAVariationTest`. Le commit `6f8895a4` (s56, *"explication sans graphie tous ou aucun"*) l'a rendue servable : un paquet sans `graphie` qui explique tous ses items passe (Javadoc du test, lignes 42 a 46 ; test `unPaquetSansGraphieQuiExpliqueTousSesItemsPasseEtUnTrouRougit`, ligne 350). C'est du contenu, sans code. Corriger la phrase soi-meme : meme situation que `-08` |
| `FRA-CE1-11` | `ce1-negation` (24 items, pas de DEGRADE) | **oui**, `bandit-manchot` | aucun |
| `FRA-CE1-21` | `ce1-mots-invariables` (DEGRADE ligne 1, plafond `en cours`) | **oui** pour la reconnaissance | Ecrire le mot sans modele : `saisie` existe et n'a pas besoin de code. Son repli des accents (`SAI-1`) ne gene pas cette liste : aucun des 24 mots cibles n'est accentue (`ce1-mots-invariables.yaml:7` et suivantes). **L'obstacle n'est pas technique** : au CE1 aucun champ a voix haute (`TRV-CE1-1`), donc rien ne dicte le mot, et la saisie alphabetique au CE1 n'est pas arbitree (en-tete, ligne 2) |

### 1.2 Mathematiques CE1

Toutes les formes fideles ci-dessous passent par un dessin. **Aucune ne demande de code Java de production** : `bandit-manchot` sert deja un item avec illustration (`ce1-sens-multiplication.yaml:44`), `designation` sert deja des zones sur un dessin. Le code entraine par un dessin est en section 3.3.

| Ligne | Paquet existant | Sert sans code ? | Ce qui manque, et quel code cela entraine |
|---|---|---|---|
| `MAT-CE1-01` | `ce1-nombres-jusqu-a-1000` (DEGRADE : *"denombrer"* non servi) | **oui** pour lire, ecrire, decomposer | Denombrer : une collection dessinee (plaques, barres, cubes) en `bandit-manchot` avec illustration. Dessin a `ines-design`. Si `nadia-prof` ecrit une condition sur ce que le dessin compte, un garde-fou du type de `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` (336 lignes) : **M**. Hauteur de rendu plafonnee a 176 px hors designation (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java:172`) : qu'une collection proche de 1000 y tienne lisible est **non verifie** |
| `MAT-CE1-02` (`en cours`) | `ce1-comparer-ranger`, servi (`catalogue.yaml:847`) | moitie textuelle servie | *"Placer sur une ligne graduee"* : `designation` sur une droite graduee, chaque position candidate est une zone. Contrainte, par arithmetique et non mesuree : plancher de 16 unites par cote de zone (`ADR-0080` A2bis, `avis-faisabilite-sept-dessins.md` section 1 point 3), donc au plus six zones sur la largeur, et trois au moins (`ZON-8`). Code : **S** (section 3.3) |
| `MAT-CE1-09` (`en cours`) | `ce1-longueurs`, servi (`catalogue.yaml:505`) | part textuelle servie | *"Mesurer avec une regle graduee"* : une regle dessinee sous un segment, propositions en centimetres, `bandit-manchot` avec illustration. Lisibilite des graduations a la taille servie : **non mesuree**. Code : **S** |
| `MAT-CE1-10` | `ce1-masses-contenances` (DEGRADE : *"mesurer"* non servi) | **oui** pour comparer et choisir l'unite | Balance ou recipient gradue dessine, meme forme que `-09`. **S** |
| `MAT-CE1-11` | `ce1-calendrier-dates` (DEGRADE : aucune horloge) | **oui** pour les dates | Un cadran dessine, `bandit-manchot` avec illustration. Les chiffres du cadran sont permis s'ils sont traces en `path` : `ADR-0073`, amendement du 2026-08-31 (`ADR-index.md:196`, `HOR-2`). **S** |
| `MAT-CE1-14` | `ce1-deplacements-quadrillage` (DEGRADE : aucun quadrillage montre) | **oui** pour la forme textuelle | Se reperer et se deplacer : `designation` sur un quadrillage, precedent des cinq `symetrie-grille-*.svg` (`git ls-files ... \| grep -c symetrie-grille` rend 5). **S**. *"Coder"* en production : aucune mecanique ne compose une suite de fleches ; `saisie` accepterait un code tape (les blancs sont retires, `NormalisationDeSaisie.java:31`), recevabilite a `nadia-prof` |
| `MAT-CE1-15` | `ce1-figures-planes` (DEGRADE : aucune figure dessinee, rien de trace) | **oui** pour decrire | Reconnaitre sur un dessin : les figures du CP existent (`figure-carre`, `-cercle`, `-rectangle`, `-triangle`), le triangle rectangle non ; `FIG-4` d'`ADR-0077` s'applique (une forme seule n'est pas remplie). **S**. **Tracer : impossible dans l'architecture actuelle.** Aucune mecanique ne recoit un trace, et `ADR-0080` interdit au client d'envoyer une coordonnee (`ADR-index.md:86`). Il faudrait un `ADR` qui remplace cette decision et une mecanique neuve : **L** |

### 1.3 Questionner le monde CE1

Aucun paquet n'existe (`ls contenu | grep -i qlm` ne rend que 13 paquets `cp-qlm-*`). La matiere est deja une donnee servie (`matieres.yaml:21`) : le premier paquet de CE1 n'entraine aucun code d'accueil.

| Ligne | Sert sans code ? | Laquelle | Attente : technique ou autre |
|---|---|---|---|
| `QLM-CE1-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12` | **oui** | `bandit-manchot`, `type: question`, propositions ecrites (instruction, sections 3.1 a 3.6, 3.8, 3.10, 3.12). Pour `-01`, la seconde tache range trois etapes en propositions de trois mots (`DVP-CE1-1`), sous `LON-CE1-5` | aucune attente |
| `QLM-CE1-07` | **oui** pour la moitie textuelle (`CIR-CE1-1`), **oui** pour la moitie dessinee des que le dessin existe | `bandit-manchot` ; puis `designation`, trois circuits sur un dessin, le circuit ferme est la bonne zone (`avis-faisabilite-sept-dessins.md:59`, `F5`) | **materiau** : le dessin du circuit, absent. Pas de code. Condition deja ecrite : ouvert ou ferme se lit a la forme, jamais a la seule couleur (`COU-2`) |
| `QLM-CE1-09` | **oui** pour la forme degradee (`PLG-CE1-2`, sens du symbole dans l'enonce), des que le plan existe | `designation`, precedent `plan-classe.svg` | **materiau** pour la forme degradee (le plan a symboles, absent). **Technique** pour la forme fidele : la legende n'a pas de place, `ZoneADesigner` n'a ni `texteSupport` ni champ d'appui (`domain/src/main/java/com/recreo/domain/content/ZoneADesigner.java:26`) et un dessin ne porte aucun texte (`ADR-0073` clause 4, `ADR-0073-...md:188`). Un support de legende pour la designation : `ADR` neuf, champ de domaine, gabarit `designation.html`, **M a L**. Ecrire les mots de la legende en `path` passerait la clause 4 et sortirait ces mots de tous les garde-fous de texte (accent, `TYP-1`) : je ne le recommande pas. L'itineraire n'entre pas dans la forme degradee (instruction, 11.2) |
| `QLM-CE1-11` | **oui** des que le dessin existe | `designation` (`avis-faisabilite-sept-dessins.md:61`, `F7`) | **materiau** : planisphere ou globe dessine, absent. Pas de code. Geometrie : deux globes sur un dessin carre tiennent le plancher de 16 unites, un planisphere 2:1 est a la limite (`F2`, arithmetique) |

### 1.4 Enseignement moral et civique CE1

| Ligne | Sert sans code ? | Laquelle |
|---|---|---|
| `EMC-CE1-01` a `-04` | **oui** | `bandit-manchot`, propositions ecrites (instruction, sections 4.1 a 4.4). Matiere deja servie (`matieres.yaml:39`). Aucune attente technique |

### 1.5 Arts plastiques CE1

| Ligne | Sert sans code ? | Attente : technique ou autre |
|---|---|---|
| `ART-CE1-01` | **oui**, forme d'`ART-CP-09` : `bandit-manchot`, une reproduction par item (`cp-decrire-une-oeuvre`, `catalogue.yaml:829`, `lignesDeReferentiel: ["ART-CP-09"]`) | **pas technique : corpus.** Aucun dessin ni photographie parmi les 12 reproductions (`ls static/oeuvres \| wc -l` rend 12). Code entraine par une reproduction neuve : **S** (section 3.3). **Droits (`ART-3`, route a moi par la cellule de matrice ligne 2314)** : `ADR-0082` exige une preuve double par reproduction (l'oeuvre est du domaine public, et la photographie qui la reproduit aussi). Pour un dessin, la regle s'applique telle quelle. **Pour une oeuvre qui est elle-meme une photographie, que les deux preuves restent distinctes est non verifie** : je ne l'ai pas relu dans `ADR-0082`, et je le ferai avant le premier paquet, pas ici |
| `ART-CE1-02` | **oui**, forme d'`ART-CP-10` (`ADR-0104`, `cp-rapprocher-deux-oeuvres`, `catalogue.yaml:835`) | **pas technique : corpus** (deux oeuvres d'un meme sujet faites de deux facons). **La cellule de matrice (ligne 2315) est perimee sur l'autre motif** : elle ecrit la forme *"refusee le 2026-10-11 et pas encore rejouee"* ; `ART-CP-10` est `couvert-valide` depuis la cloture du palier 1 (note de la matrice, ligne 26). La mise a jour est a `camille-po`. Code entraine : **S** (section 3.3) |

### 1.6 Bilan

Commandes : comptes faits sur les tableaux ci-dessus.

- **Servables sans code de production par une mecanique existante, dans une forme au moins** : les 32 lignes. Aujourd'hui : 12 par un paquet qui existe et attend son entree de catalogue et son audit ; 14 par un paquet a ecrire (9 QLM textuelles, la moitie textuelle de `QLM-CE1-07`, 4 EMC). Une fois leur materiau present : les 6 autres (`MAT-CE1-02` et `-09`, deja servies en partie ; `QLM-CE1-09` en forme degradee, `-11` ; `ART-CE1-01`, `-02`).
- **Formes qui n'attendent qu'un dessin ou une reproduction (code S, aucun code de production)** : `MAT-CE1-01`, `-02`, `-09`, `-10`, `-11`, `-14`, `-15` (reconnaitre), `QLM-CE1-07`, `-09` (degradee), `-11`, `ART-CE1-01`, `-02`.
- **Formes qui demandent du code** : `FRA-CE1-03` (temps, L), `FRA-CE1-08` et `-09` (correction par l'enfant, M ou L), `QLM-CE1-09` (legende lue, M a L).
- **Forme impossible sans remplacer un `ADR`** : `MAT-CE1-15`, *"tracer"* (`ADR-0080`).
- **Les cinq lignes `en attente`** : `QLM-CE1-07`, `-11`, `ART-CE1-01`, `-02` attendent un materiau, pas du code. `QLM-CE1-09` attend un materiau pour sa forme degradee et un `ADR` pour sa forme fidele.
- **Prise d'une ligne a dessin** : `PAR-13`, une par sprint (instruction, ligne 378). C'est une regle de `nadia-prof`, je la cite sans la juger.

## 2. Conflits avec ce qui est deja engage au palier 3

| Code | Engagement | Lignes CE1 qui le touchent |
|---|---|---|
| `K1` | `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE` (`Z1`, `Z2`, refus du CSS imbrique, `Z4`, `P1`, `P2`), en tete, *"avant tout poste qui modifie `recreo.css`"* (`docs/backlog/backlog.md:22637`) | **Aucune des formes sans code.** Un paquet et une entree de catalogue ne touchent ni `recreo.css` ni un gabarit, et un gabarit ou la feuille ne peuvent pas nommer un contenu (`ADR-0094`, `ADR-index.md:100`). **Toucheraient la feuille ou un gabarit** : la forme fidele de `QLM-CE1-09` (gabarit `designation.html`), et toute mecanique neuve (`FRA-CE1-08`, `-09`, `MAT-CE1-15`). Elles viennent apres la story. Le critere 9 de la story (`backlog.md:22634`) interdit tout contenu dans son commit : aucun paquet CE1 n'y entre |
| `K2` | Garde de `C1'` (`UnEcranNeGouvernePasLApparenceDUnAutreTest`) et forme commune epinglee (`LaFormeCommuneDesPropositionsLonguesEstEpingleeTest`) | Aucune regle de feuille n'est ajoutee par ces lignes. **Risque non garde, a mesurer** : `FRA-CE1-06` et `-07` ont la forme de `ce1-texte-narratif`, dont la reserve `RCT-CE1-R1` porte des items a trois rangees qui finissent sous 551 a 744 (`avis-theo-palier-2.md`, 3.2). Si `PA7` est tranchee par le bandeau, c'est une regle de `recreo.css` sur 144 jeux : elle vient apres `K1`. Si elle l'est par des propositions plus courtes, aucun conflit |
| `K3` | Commit de `RL5`, `RL6`, `RL7`, *"avant tout autre commit de code et avant le premier jar rejoue"* (`cloture-palier-2.md:285`) | **Toutes** les lignes dont un jar est rejoue par `nadia-prof` viennent apres lui. Un commit de contenu seul n'est pas un commit de code ; l'ordre entre eux est a `sofia-sm` |
| `K4` | `S57-GARDE-NOMBRE-DE-MANCHES` (`backlog.md:22095`) | Toute entree CE1 neuve a `nombreDeManches` different de 10 devra figurer dans la liste fermee avec sa `DEC`. Un paquet a dessins rares (designation) y est expose : `designation-plan-de-la-classe` porte 5, `designation-symetrie-ce1` 8. Le critere 3 de la story veut un `catalogue.yaml` inchange dans son commit : ne pas y joindre d'entree. **Ecart trouve** : le catalogue porte aujourd'hui **8** entrees a une valeur autre que 10 (`awk` sur `catalogue.yaml`), la liste de la story et de `DEC-0121` section 3 en porte **7** ; manque `bandit-manchot-rapprocher-deux-oeuvres-cp`, 5, revenu au catalogue apres l'arbre de mesure de `DEC-0121` (`13affd6f`) par `06c7804f` (2026-10-15). Telle qu'ecrite, la story rougirait sur le catalogue d'aujourd'hui. `ART-CE1-02`, meme forme, y est directement expose. A `camille-po` |
| `K5` | `S57-GARDE-PILE-DE-POLICES` | Aucune : aucune de ces lignes ne declare de police |

## 3. Regles ArchUnit et gardes de contenu que ces lignes risquent de heurter

### 3.1 ArchUnit

**Les formes sans code ne touchent aucune regle ArchUnit** : elles n'ajoutent aucune classe Java. Compte de reference inchange (39 regles reelles, `confirmation-archunit-palier-2.md` section 2). Les formes avec code les touchent :

- **Mecanique neuve** (`FRA-CE1-08`, `-09`, `MAT-CE1-15`) : `DecouplageMecaniqueContenuTest` (13 tests) et `ArchitectureEnCouchesTest` s'appliquent a son paquet. Ce que les regles interdiraient reellement : qu'elle importe un generateur de contenu concret ou une autre mecanique. Une seconde normalisation partagee avec `saisie` serait rouge (clause 8 citee dans `NormalisationDeSaisie.java`, Javadoc).
- **`MAT-CE1-15`, tracer** : `ADR-0080`, garde-fou du client qui n'envoie jamais de coordonnee. Il faut le remplacer, pas l'exempter.
- **`FRA-CE1-03`, temps** : une horloge dans la partie entre dans `domain` ; aucune dependance de framework n'y est permise (`PureteDesCouchesTest`).

### 3.2 Gardes de contenu

| Code | Garde | Effet sur ces lignes |
|---|---|---|
| `G1` | Gardes a perimetre derive du repertoire `contenu/` (dont `ChaquePaquetPorteUnIndiceTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `AucunSigneDoubleNEstDetachableDeSonMotTest` pour les paquets, `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`) | **Deja passees par les 12 paquets existants** : ils sont dans le repertoire et le reacteur de `efb8d013` est vert. L'enum de sous-parties nomme deja les cinq paquets de maths (`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.java:192` a `:207`) |
| `G2` | Gardes a perimetre derive du catalogue, qui commencent a juger un paquet a son branchement : `ChaqueEntreeDuCatalogueChargeBienSonPaquetTest`, `AucunTitreDeJeuNePorteSonNiveauTest`, `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`, `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` (`-06`, `-07`), `LExplicationDUnItemReelAtteintLEcranDeRetourTest` | Un titre ne porte jamais le niveau (`ADR-0078`) ; strictement plus d'items que de manches (`ADR-0023`). Pas de mesure de ma part |
| `G3` | Cliquet `TYP-1` sur le titre et la consigne du catalogue, en egalite : 23 occurrences (`AucunSigneDoubleNEstDetachableDeSonMotTest.java:100`, total 490 ligne 103) | **Une consigne ou un titre neuf avec une espace ordinaire avant `?` ou `:` rend le cliquet rouge.** Ecrire U+00A0 |
| `G4` | `LON-CE1-1` et `-2` (20 mots, 3 phrases) sur tout enonce de paquet CE1 : `AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`, perimetre derive, dette limitee a `ce1-longueurs` (ligne 82) | S'applique deja aux 12 paquets, s'appliquera a tout paquet QLM et EMC de CE1 |
| `G5` | `LON-CE1-3`, `-4`, `-5` et `TRV-CE1-1` ne sont executes que par des tests **nommes par paquet** (`git grep -l "LON-CE1-3"` rend `PaquetCe1TexteNarratifTest` et `PaquetCe1PronomsTest` seuls ; `git grep -l "TRV-CE1-1"` sur les tests rend 18 fichiers, tous `PaquetCe1*Test`) | **Trou de garde.** Aucun des 12 paquets existants, ni aucun paquet QLM ou EMC de CE1 a venir, n'est juge par ces conditions tant qu'un test par paquet n'est pas ecrit. Ma mesure, hors depot, sur les 12 paquets plus `ce1-texte-narratif` et `ce1-pronoms` : 0 proposition de plus de 6 mots, 0 texte support de plus de 30 mots ni de plus de 4 phrases ou d'une phrase de plus de 12 mots, 0 champ `texteAVoixHaute`. Methode : script Python sur les lignes `bonneReponse`, `distracteurs`, `texteSupport`, mots separes par les blancs, phrases coupees apres `.`, `!`, `?` ; commentaires exclus. Ce n'est pas la convention de comptage de s47 citee par `PaquetCe1TexteNarratifTest.java:41`, que je n'ai pas reprise. Un garde unique derive du descripteur `niveau: "CE1"`, sur le patron de `G4`, fermerait le trou : **M**. Je ne l'ecris pas sans demande |
| `G6` | Explication sans `graphie`, regle *"tous ou aucun"* (`ChaquePaquetExpliqueSesItemsAVariationTest`, `6f8895a4`) | Si `RLP-CE1-3` est servie sur `ce1-relire-une-phrase`, chacun des 20 items porte une explication, ou aucun. Un trou rend rouge |

### 3.3 Ce qu'un dessin ou une reproduction neufs entrainent (code S, tests seuls)

- **Cliquet de poids des illustrations, en egalite** : `POIDS_TOTAL_MAXIMUM = 69203` (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java:355`, `isEqualTo` ligne 833), plafond par dessin 4633 octets (ligne 279). Il se deplace dans le commit qui installe les octets, sur la valeur mesuree (`ADR-0077` decision 6). Un dessin de designation s'inscrit dans `DESSINS_DE_DESIGNATION` (ligne 205).
- **Dessin** : aucun `<text` ni `<tspan` (`ADR-0073` clause 4), deux dessins non confondus en gris (`COU-2`, `ADR-0077`), et pour la designation trois zones au moins, non chevauchantes, d'au moins 16 unites de cote.
- **Garde par famille de dessin** quand une condition de `nadia-prof` affirme ce que le dessin montre : precedents `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` (336 lignes) et `LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` (668 lignes). **M** chacun. Le declencheur est la condition, pas le dessin.
- **Reproduction d'oeuvre** (`ART-CE1-01`, `-02`) : `REPRODUCTIONS_SERVIES = 12` et `POIDS_TOTAL_MAXIMUM = 275_163` (`bootstrap/.../ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest.java:178` et `:180`), preuve double d'`ADR-0082`.
- **Second jeu a propositions en image** (`ART-CE1-02`) : la liste exacte `containsExactly("bandit-manchot-rapprocher-deux-oeuvres-cp")` (`bootstrap/.../LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java:441`) rougit, comme elle le doit, et se releve dans le meme commit.

## 4. Questions routees

| Code | Question | A qui |
|---|---|---|
| `Q1` | `K4` : ajouter `bandit-manchot-rapprocher-deux-oeuvres-cp` (5) a la liste de `DEC-0121` et de `S57-GARDE-NOMBRE-DE-MANCHES`, avec la decision qui porte 5 | `camille-po` |
| `Q2` | `FRA-CE1-09` : l'en-tete DEGRADE de `ce1-relire-une-phrase` repose sur un garde-fou amende depuis ; la recevabilite d'une explication par item (`RLP-CE1-3`) | `nadia-prof` ; la correction de l'en-tete, fichier de `src/main`, a un developpeur |
| `Q3` | Cellule `ART-CE1-02` (matrice ligne 2315) perimee sur la forme a deux oeuvres | `camille-po` |
| `Q4` | `G5` : un garde de `LON-CE1-3` a `-5` et `TRV-CE1-1` derive du niveau, avant de brancher les paquets sans test propre, ou un test par paquet comme jusqu'ici | `sofia-sm` (affectation) ; la forme du garde est a moi si elle est demandee |

## 5. Regle ArchUnit

Aucune regle ecrite, amendee ou retiree par ce fichier : c'est un avis, sans decision d'architecture. Compte inchange.

## Decisions prises

Aucune `ADR` ni `DEC` creee ou amendee. `ADR-index.md` inchange.

## Amendement du 2026-10-21 18:32 (UTC+02:00), `theo-archi` - ecarts 34 et 35 de `releve-relecteur-cloture-palier-3.md`

Rien au-dessus n'est reecrit.

**Ecart 34, numeros de ligne.** Ils resolvent a la base declaree, `ad65827e`, et derivent depuis. Verifie par `git show <sha>:<fichier> | sed -n <ligne>p` : a `ad65827e`, la ligne 1021 de la matrice est la competence de `MAT-CE1-01`, 2105 `QLM-CE1-01`, 2314 `ART-CE1-01`, 2553 `EMC-CE1-01` ; a `50dbd73f`, ces lignes sont 1029, 2113, 2322 et 2561 (+8, notes de `camille-po` ajoutees en tete). Dans `backlog.md`, 22634 (critere 9 de `S57-GARDE-C1-VIRGULE-ET-IMBRIQUE`) ne bouge pas, 22637 (*"avant tout poste qui modifie `recreo.css`"*) devient 22641, 22095 (`S57-GARDE-NOMBRE-DE-MANCHES`) ne bouge pas. Les lire comme des ancres nommees au SHA `ad65827e` : la ligne de matrice par son code, la story par son titre.

**Ecart 35, script hors depot.** Traite dans l'amendement du meme jour de `decision-theo-garde-ce1-e3.md`, qui ecrit le script en entier et le rejoue a deux SHA. La mesure `G5` de la section 3.2 ci-dessus n'est pas celle-la : elle portait sur 14 paquets, avec une methode que j'ai remplacee le 2026-10-19 par celle de la decision. **Elle n'est pas rejouable et je ne la donne plus comme mesure** : la mesure qui vaut est celle de la decision, 47 paquets.

**Ecart trouve par moi en ecrivant l'avis du palier 4, hors du releve.** La ligne `MAT-CE1-15` de la section 1.2 ecrit *"les figures du CP existent (...), le triangle rectangle non"*. C'etait faux a sa date : `figure-triangle-rectangle.svg` est au depot depuis `28150cd4` (2026-08-31, `git log --diff-filter=A`) et sert `cp-figures-planes.yaml`. La forme *reconnaitre* de `MAT-CE1-15` n'attend donc aucun dessin.
