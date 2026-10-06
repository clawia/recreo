# s52 - Avis de faisabilite (`theo-archi`) - les postes de `DEC-0111`

- **Date** : 2026-10-06
- **Base** : `round-two` a `7350fb07` (apres `ba00cb92` pour `DEC-0111`, `edf15763` pour `ADR-0080` A2 et
  `7350fb07` pour les contraintes de dessin d'`ines-design`).
- **Emplacement** : dossier de sprint de s52, nom fixe pour que `sofia-sm` y ecrive la suite.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucune affectation, et ne rejuge aucune
  condition pedagogique. Le Done pedagogique reste a `nadia-prof`.
- **Lu avant d'ecrire** : `DEC-0111` en entier ; la section "Sprint 52" du backlog et les stories
  `S51-MAT-SYMETRIE`, `S51-MAT-MULTIPLICATION`, `TPS-CE1-R1`, `ALP-CE1-R1` ;
  `ARBITRAGE-PREALABLE-s48-francais-ce1.md` (sections 1.3, 2, 5.8, 6.3, 6.4) ;
  `ARBITRAGE-PREALABLE-s52-instances-ce1.md` ; mon avis de s51 ; `DEC-0009` ; `ADR-0061` ;
  `ADR-0080` A2 ; `squad/design/contrainte-ines-s52-dessins-de-l-instance-c-mathematiques-ce1.md`.

---

## 0. L'etat mesure

Trois executions du reacteur complet, `bash outils/mvn-jdk21.sh -B clean verify
-Dmaven.test.failure.ignore=true`, chacune dans un worktree detache a `7350fb07`, voisin du checkout
principal, cree pour cette mesure et supprime ensuite. Version : **JDK 21.0.10**, telle que la machine
virtuelle des tests la rapporte (20 lignes *"using Java 21.0.10"* par execution, aucune autre version).
Compte : somme des six blocs `Tests run:` de synthese du reacteur, dans l'ordre domain / application /
adapter-web / adapter-content / bootstrap / dernier bloc de bootstrap.

| Etat | Tests | Echecs | Ce qui rougit |
|---|---|---|---|
| `7350fb07` tel quel | **2082** (120 / 17 / 401 / 1243 / 295 / 6) | **0**, `BUILD SUCCESS` | rien |
| plus le retrait seul (deux entrees hors de `catalogue.yaml`, rien d'autre) | 2082 | **1** (bootstrap) | l'inventaire, section 3 |
| plus le retrait et trois paquets-sondes de francais CE1 avec leurs entrees | 2082 | **8** (0 / 0 / 0 / 4 / 4 / 0) | section 1 |

**Job lance : `build` seul**, sur ces trois arbres. `prefixes`, `chemins` et `outils` n'ont pas ete lances sur
eux ; ce que j'ai lance sur mon propre diff est en section 6.

Une quatrieme mesure, sur le lexique lui-meme : une classe de test jetable appelle la fonction pure du
garde-fou d'accentuation (`AucunMotServiNeSEcarteDeSonAccentTest.defautsDe`) et le `LexiqueFrancais` reel
sur 273 mots, `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test
-Dtest=SondeLexiqueS52Test -Dsurefire.failIfNoSpecifiedTests=false` (mesure partielle, `-am`, perimetre :
`adapter-content` et ses dependances ; l'enveloppe rapporte 21.0.10). Resultats en section 1.2.

---

## 1. Instance A - francais CE1 : trois paquets de plus, mesures sur le reacteur reel

### 1.1 Les sondes et ce qui a rougi

Trois paquets `question` de 21 items, francais CE1, une ligne chacun, propositions a trois, blancs
insecables (U+00A0) devant `?` et `:` : `ce1-sonde-present` (`FRA-CE1-17`, distracteurs des familles (a),
(b), (c) de `PRS-CE1-3`), `ce1-sonde-synonymes` (`FRA-CE1-25`, mot cible entre guillemets francais dans une
phrase), `ce1-sonde-categories` (`FRA-CE1-26`, *"Quel mot désigne : pomme, poire, cerise ?"* et
*"Lequel est un fruit ?"*). Sondes de ma main ; ce qu'elles prouvent tient aux garde-fous, qui sont ceux
du depot. Un seul item a ete ecrit pour echouer : *"il ___ au marché"* avec le distracteur *"alle"*.

| Cause | Garde-fou | Methodes | Nature |
|---|---|---|---|
| *"le mot "alle" n'est pas une forme du francais, alors que [allé] en est une du meme squelette"* | `AucunMotServiNeSEcarteDeSonAccentTest` | 1 (un seul defaut sur 63 items) | **contrainte d'ecriture**, 1.2 |
| trois paquets ni juges ni declares | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | 3 (une cause : les deux mises a l'epreuve tournent sur la production) | ecriture attendue : `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` |
| chaines exposees hors inventaire | `TexteAccentueExposeParLePortEntrantTest` | 3 | ecriture attendue : l'inventaire |
| chaines d'inventaire orphelines | meme test, quatrieme methode | 1 | le retrait, section 3 |

**Ce qui n'a rien bloque**, et c'est la reponse a la question posee : l'accentuation de tous les mots justes
(section 1.2), les signes doubles (`AucunSigneDoubleNEstDetachableDeSonMotTest`, blancs insecables poses),
le compte de mots des enonces de CE1 (`AucunEnonceDeCe1NeDepasseVingtMotsNiTroisPhrasesTest`), le registre
(`ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest`), l'illustration qui ne dort pas
(`LIllustrationDeContenuResoutEtAucuneNeDortTest` : aucune illustration en A), les cliquets de poids, la forme
des propositions (`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest`, trois clauses), le nombre de
propositions, la repetition d'ecran, les items par manche. **Aucune clause ne bloque un paquet de plus.**
`A1` d'`ADR-0065` n'a rien a admettre ici : aucun distracteur de ces trois lignes ne differe de sa bonne
reponse par une cedille seule.

### 1.2 Le lexique d'accents rejette-t-il des formes verbales ou des mots courants ? Mesure

Critere du garde-fou : un mot est fautif s'il n'est pas au lexique **et** qu'une forme du lexique a le meme
squelette (lettres sans signes diacritiques). Verdicts sur la liste de 273 mots, ecrite pour cette mesure :

| Population | Mots | Refuses | Admis |
|---|---|---|---|
| formes justes au present des six verbes nommes et de verbes en `-er` (`FRA-CE1-17`) | 51 | 0 | 51 |
| infinitifs, famille (c) | 9 | 0 | 9 |
| formes regularisees inexistantes, famille (b) | 23 | **2** : *alle* (soeur *allé*), *alles* (soeur *allés*) | 21 : *disez*, *faisez*, *faisent*, *allent*, *allont*, *venent*, *vienent*, *avont*, *sontent*... |
| mots frequents, synonymes et contraires (`FRA-CE1-25`) | 77 | 0 | 77 |
| categories et membres (`FRA-CE1-26`) | 71 | 0 | 71 |
| formes privees de leur accent (controle de morsure) | 33 | 25 | 8, tous homographes : *mur*, *sur*, *pres*, *age*, *agite*, *mouille*, *marche*, *flute* |

Neuf mots de la liste etaient mal ranges : huit formes du lexique parmi les regularisees (*venez*,
*direz*...) et *écoutes* parmi les formes privees d'accent. Ils sont exclus des lignes ci-dessus, et tous
admis. Total : 273 mots.

- **Aucun mot juste n'est refuse** : 217 sur 217 (208 du tableau et les 9 mal ranges).
- **`FRA-CE1-17`, le seul bloquant reel** : la forme regularisee d'*aller* aux personnes du singulier
  (*j'alle*, *tu alles*, *il alle*) est refusee, parce qu'*allé* et *allés* existent. Les autres formes (b)
  passent, dont *ils allent*. `PRS-CE1-3` (b) reste servable sans elles. Si `nadia-prof` les veut, c'est un
  second amendement d'`ADR-0065`, de la forme d'`A1` (admission derivee de l'item, jamais une liste de
  mots) : Q1.
- **Le trou connu du garde-fou** (`ADR-0065`, homographes) touche deux lignes : *mur* pour *mûr*, *sur* pour
  *sûr*, *pres* pour *près* (`FRA-CE1-25`) et *marche* pour *marché* (`FRA-CE1-26`, le distracteur (c) que
  `CTG-CE1-3` donne lui-meme en exemple) passent sans accent. Ils restent a la lecture de `nadia-prof`.

### 1.3 Inscriptions de tables de tests attendues, par ligne

Les trois lignes demandent les memes ecritures, toutes en code de test, aucune en `src/main/java` :

| Table ou fichier | `FRA-CE1-17` | `FRA-CE1-25` | `FRA-CE1-26` |
|---|---|---|---|
| `catalogue.yaml` | une entree | une entree | une entree |
| `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) | une entree et son motif | idem | idem |
| `TexteAccentueExposeParLePortEntrantTest`, inventaire | identifiant, titre, competence ; consigne si neuve | idem | idem |
| espaces insecables | devant `?`, `!`, `:`, `;` dans enonce, propositions, indice, explication, et dans titre et consigne de catalogue | idem | idem |
| rang de la bonne reponse | hors domaine numerique : aucun calcul de rang | idem | idem |

Precisions mesurees :

- **Le motif d'une entree de `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` porte des comptes verifies** :
  `chaqueCompteEcritDansUnMotifVautLeCompteMesureSurLePaquet` compare chaque nombre ecrit (*"21 items sur
  21"*) au paquet. On l'ecrit apres le paquet, pas avant.
- **Inventaire** : les domaines *"Orthographe et grammaire"* et *"Vocabulaire"*, la matiere et le niveau y
  sont deja ; une consigne reprise mot pour mot d'une entree existante y est deja aussi.
- **Espaces insecables** : un paquet neuf n'herite d'aucune dette (`DETTE_PAR_FICHIER`), et l'entree de
  catalogue compte dans la ligne `catalogue.yaml` de cette table.
- **Ce qu'aucun garde-fou transverse ne tient** : la regle de longueur de `TRV-CE1-2` (bonne reponse
  strictement la plus longue dans la moitie des items au plus). Le garde-fou de rang ne lit que des
  nombres ; la regle se teste dans le test de paquet, comme pour les paquets de CE1 de s50 et s51.
- **`TRV-CE1-4`** : *synonyme* n'est ni dans la liste admise ni dans la liste exclue. C'est a `nadia-prof` (Q2).

### 1.4 Les deux corrections

- **`TPS-CE1-R1`** : YAML seul. `PaquetCe1TempsTest` ne nomme aucun prenom (lu). Rien a inscrire.
- **`ALP-CE1-R1`** : YAML seul. `PaquetCe1AlphabetiqueTest` exige des indices non tous identiques et de douze
  mots au plus ; l'indice cible y est deja servi. Le critere *"aucun item dont la decision se fait a la
  deuxieme lettre ne porte un indice qui s'arrete a la premiere"* est decidable sur l'item : il peut entrer
  dans le test de paquet, rouge sur l'existant.

---

## 2. Verdicts

| Code | Story | Avis | ADR requis | Risque technique | Regle ArchUnit |
|---|---|---|---|---|---|
| V1 | `S52-RETRAIT-SCIENCES-CM1` | **faisable en l'etat** | non | un test retire a tort (3.3) | aucune |
| V2 | `S52-FRA-PRESENT` (`FRA-CE1-17`) | **faisable en l'etat**, sans *alle* ni *alles* | non ; amendement d'`ADR-0065` seulement si Q1 rend ces formes requises | le distracteur (b) d'*aller* au singulier | aucune |
| V3 | `S52-FRA-SYNONYMES-CONTRAIRES` (`FRA-CE1-25`) | **faisable en l'etat** | non | homographes sans accent invisibles au garde-fou | aucune |
| V4 | `S52-FRA-CATEGORIES` (`FRA-CE1-26`) | **faisable en l'etat** | non | idem (*marche*) | aucune |
| V5 | `TPS-CE1-R1` | **faisable en l'etat**, YAML seul | non | aucun | aucune |
| V6 | `ALP-CE1-R1` | **faisable en l'etat**, YAML seul | non | aucun | aucune |
| V7 | `S51-MAT-SYMETRIE` (`MAT-CE1-17`) | **faisable sous condition** : materiau, dessinateur, message du cliquet (4.1) | non : A2 est commite | zones a 296 px (4.1, F3) ; deux ecrivains (4.3) | aucune |
| V8 | `S51-MAT-MULTIPLICATION` (`MAT-CE1-06`) | **faisable sous condition** : materiau et dessinateur | non | variante en un seul chemin (4.2) | aucune |
| V9 | `S51-MAT-COMPARER-RANGER` (`MAT-CE1-02`, moitie textuelle) | **faisable en l'etat** (V10 de s51 inchange) | non | aucun | aucune |

**Aucune story ne touche `src/main/java`, aucune n'appelle de regle ArchUnit.** Le compte d'`ArchRule` du
depot est inchange par s52. Les garde-fous que ces stories font jouer sont des tests de contenu JUnit.

---

## 3. Le retrait des deux paquets de sciences CM1

### 3.1 Le tarif

**Sur la capacite de `nadia-prof` : 0.** La story ne passe pas devant elle (`DEC-0111` section 3). **Cout de
developpement, mesure et non estime** : deux fichiers, aucune ligne ajoutee.

| Fichier | Edition | Mesure |
|---|---|---|
| `adapters/adapter-content/src/main/resources/catalogue.yaml` | retirer les deux entrees | 12 lignes |
| `bootstrap/src/test/java/com/recreo/bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest.java` | retirer les 8 chaines orphelines et les commentaires qui les annoncent | 8 chaines : les deux identifiants, les deux titres, les deux consignes, les deux competences |

Les 8 chaines sont exactement celles que le garde-fou nomme sous le retrait seul : *"but could not find the
following element(s)"*, puis ces 8 chaines et aucune autre. Le domaine *"La planète Terre. Les êtres vivants
dans leur environnement"* reste : `cm1-sciences-chaine-alimentaire` et `cm1-sciences-phenomenes`, encore
servis, le portent. C'est le cout qu'`ADR-0061` a mesure pour `DEC-0045`, a l'identique dans sa forme.

**Precedent.** `DEC-0009` ne pose aucun tarif a `S5-1`, et il n'existe pas d'unite de tarif de developpement
dans ce depot. Je n'en invente pas : le cout est donne en fichiers et en lignes.

### 3.2 Les tests qui nomment les deux entrees ou leurs paquets

`git grep` des quatre identifiants (`bandit-manchot-activite-humaine-cm1`, `bandit-manchot-terre-cm1`,
`cm1-sciences-activite-humaine`, `cm1-sciences-terre`) hors `squad/` et `docs/` : 7 fichiers.

| Fichier | Ce qu'il nomme | Sous le retrait |
|---|---|---|
| `catalogue.yaml` | les deux entrees | retire |
| `contenu/cm1-sciences-activite-humaine.yaml`, `contenu/cm1-sciences-terre.yaml` | leur propre identifiant | inchanges, toujours lus |
| `PaquetCm1SciencesActiviteHumaineTest`, `PaquetCm1SciencesTerreTest` | le paquet seul, jamais l'entree (aucune occurrence de *catalogue*) | **verts**, inchanges |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | les deux paquets, dans `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | **vert, et a ne pas toucher** (3.3) |
| `TexteAccentueExposeParLePortEntrantTest` | les deux identifiants et six autres chaines | **rouge**, 8 chaines a retirer (3.1) |

**Sous le retrait seul, aucun autre test ne rougit** (section 0 : 1 echec sur 2082). Les deux garde-fous de
coherence du catalogue derivent leur population du catalogue, et une entree retiree en sort (`ADR-0061`).

### 3.3 Le risque d'un test qui exige la presence des fichiers, et celui de l'inverse

- **Les fichiers restent, donc rien ne rougit pour leur absence.** Les garde-fous de contenu (accents, signes
  doubles, rang, texte servi) derivent leurs paquets du balayage de `contenu/`, pas du catalogue : ils
  continuent de juger les deux paquets retires, ce qui est voulu (`ChaquePaquetPorteUnIndiceTest`, s05).
- **Le risque reel est l'inverse : retirer ce qui ne doit pas l'etre.** `DEC-0111` section 8, point 2,
  annonce que le retrait touche `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`. **Il ne doit pas
  le toucher** : ce garde-fou balaie `contenu/`, les deux fichiers y restent, et y retirer leurs deux
  entrees les rendrait *"ni juges ni declares"*. C'est le mecanisme mesure sur les sondes (section 1.1), en
  sens inverse. Le mot *"servi"* de leurs deux motifs devient inexact ; le corriger toucherait un fichier
  que A et C editent aussi, pour un commentaire. Je ne le demande pas.
- **Le critere *"sans redemarrage"* de la story n'a pas d'occurrence en production** : le catalogue est une
  ressource du fat jar, lue une fois au demarrage (`ContenuConfiguration`), et l'etat de partie vit en
  session HTTP en memoire. Le retrait n'atteint l'enfant qu'au redemarrage, qui efface la session. Le
  comportement de `S5-1` (identifiant de jeu inconnu, ecran d'erreur) se teste sur un identifiant fabrique :
  a `yanis-qa`.

### 3.4 L'effet sur "aucun paquet ne dort"

- **Illustrations** : aucune des deux ne declare d'illustration (0 occurrence de *illustration* dans les deux
  YAML). `LIllustrationDeContenuResoutEtAucuneNeDortTest` est vert sous le retrait, mesure.
- **Paquets** : aucun garde-fou n'exige qu'un fichier de `contenu/` ait une entree. Avant le retrait, 131
  fichiers et 131 entrees (`ls contenu/*.yaml` et `grep -c "^  - id:" catalogue.yaml`) ; apres, 2 paquets
  sans entree, premiers occupants de l'etat qu'`ADR-0061` derive sans champ de schema. Le genre
  `PAQUET_SERVI` d'`AucunGabaritNiFeuilleMaisonNeNommeUnContenuParticulierTest`, ecrit pour ce cas et vide
  jusqu'ici, est occupe : vert, mesure.

### 3.5 Sans amender un ADR : oui

L'absence d'entree est le seul mecanisme de retrait (`ADR-0061`, decision 3). Ce qui me ferait changer
d'avis selon cet `ADR` - plusieurs retraits dont le motif demande cinq `DEC` a lire - n'est pas realise :
les deux retraits ont une seule `DEC`, `DEC-0111`.

---

## 4. Instance C - mathematiques CE1

### 4.1 `MAT-CE1-17` : ce qu'A2 leve, et ce qui reste

**A2 leve la condition d'architecture sur le fond.** `edf15763` ecrit `ZON-13` (`DeuxZonesDUnItemNeSeChevauchentJamaisTest`)
et le rang de declaration de la bonne zone (troisieme clause de `LaBonneZoneNEstPasPredictibleSurUnPaquetTest`) ;
tous deux sont verts sur `7350fb07`, dans les 1243 tests d'`adapter-content` de la section 0.

**Ce qui reste, et qui est a moi** :

- **F1 - le message du cliquet n'est pas corrige.** `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest`
  demande encore d'*"etendre le balayage de production"* (T6), ligne 145 (lu a `7350fb07`). `edf15763` ne
  touche pas ce fichier, et `ADR-0080` A2 section 3 le dit : la correction est due *"au plus tard dans le
  diff qui monte ce cliquet de 2 a 3"*. La condition (1) de `DEC-0111` 5.2 demandait les deux dans le meme
  diff : **sur ce point, elle n'est pas remplie a l'heure de cet avis.** A1 ci-dessous.
- **F3 - le plancher de 13 unites d'A2 section 4 est faux a 320 px de fenetre.** `ines-design` mesure
  l'image de designation servie a **296 px** a 320 px de fenetre (section 1.2 de ses contraintes, rendu
  reel). 13 unites y font 38,5 px, sous les 44 px du garde-fou 3, qui calcule a 352 et ne le voit pas. Son
  plancher de **16 unites** (47,4 px a 296) tient. Le dessin suit ses 16 unites ; la correction de mon texte
  est A2bis ci-dessous. Ce n'est pas un blocage, c'est un faux vert possible du garde-fou 3, de la famille
  d'A3.

**Le prerequis de dessin (condition (2))** : les contraintes d'`ines-design` sont commitees (`7350fb07`). Les
figures a lignes candidates (type A) y sont ecrites en entier ; le **quadrillage 6 x 6 n'y est qu'en
esquisse** (sa section 4), et la legende a 36 libelles y est remplacee par une proposition (seules les cases
candidates sont des zones) routee a `nadia-prof`. Dire si l'esquisse suffit a engager les items de completion
est a `sofia-sm` et a `nadia-prof`, pas a moi. **Condition (3)** : aucun dessinateur affecte (`sofia-sm`, Q5 de
s51).

**`nombreDeManches: 8`** (`DEC-0111` 5.4) : compatible avec un dessin par item et dix items au moins
(`FER-2`, plus d'items que de manches). Aucun obstacle technique.

### 4.2 `MAT-CE1-06` et la question d'`ines-design` sur un reseau en un seul chemin

Lu dans `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (non execute) : un
dessin d'un seul `<path>` sans remplissage compte une forme rendue, et `FIG-4` ne se declenche que sur un
remplissage autre que `none`. **Il passerait.** Je le deconseille : il passerait parce que le garde-fou y
voit une forme ouverte unique, pas cent points. Un garde-fou qui accepte un objet qu'il ne sait pas lire
n'en garantit rien. Un `<circle>` par point jusqu'a 5 x 7 (1 246 octets mesures par `ines-design`) est la
forme a moins de pieces mobiles. Un dessin plus lourd que 4 633 octets deplace `POIDS_D_UN_DESSIN_MAXIMUM`
avec sa valeur mesuree et son motif (`ADR-0036` clause 5) ; ce n'est pas un `ADR`.

### 4.3 Les cliquets a monter dans C

Tous **exacts** : chacun echoue au-dessus comme en dessous du compte reel.

| Cliquet | Fichier | `MAT-CE1-17` | `MAT-CE1-06` | `MAT-CE1-02` |
|---|---|---|---|---|
| `PLAFOND_DE_DESIGNATION_EN_PRODUCTION` | `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` | 2 vers 3, message corrige (F1) | - | - |
| `PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE` | `AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest` | 2 vers 3 | - | - |
| `DESSINS_DE_DESIGNATION` | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | un nom par dessin | - | - |
| `POIDS_TOTAL_MAXIMUM` (51 307) | meme fichier | remesure | remesure | - |
| `POIDS_D_UN_DESSIN_MAXIMUM` (4 633) | meme fichier | seulement si un dessin le depasse | idem | - |
| `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` (64) | `LIllustrationDeContenuResoutEtAucuneNeDortTest` | plus un par item illustre | idem | - |
| `FICHIERS_D_ILLUSTRATION` (42) | meme fichier | plus un par fichier | idem ; deux items `MUL-CE1-4` sur un meme dessin n'ajoutent qu'un fichier | - |
| `SousPartieDeMathematiquesCe1` | `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | une valeur | une valeur | une valeur |
| `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` ou `PAQUETS_A_ITEMS_ECARTES` | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | hors domaine (mesure en s51) | ecartes si le paquet est mixte (s51, 5.5) | l'un ou l'autre (V10 de s51) |
| inventaire | `TexteAccentueExposeParLePortEntrantTest` | identifiant, titre, consigne, competence | idem | idem |

### 4.4 Deux branches qui montent le meme cliquet : remesure, pas d'addition

**La regle** : a la fusion, la valeur d'un cliquet exact est **celle que le reacteur complet mesure sur l'arbre
fusionne**, jamais la valeur de base plus les deux increments. Trois motifs :

1. `POIDS_D_UN_DESSIN_MAXIMUM` est un maximum : deux increments ne s'additionnent pas.
2. `POIDS_TOTAL_MAXIMUM` se mesure en octets aux fins de ligne normalisees ; un fichier touche par les deux
   branches compte une fois. L'addition le compte deux fois.
3. Deux branches qui editent deux constantes differentes fusionnent sans conflit textuel, chacune mesuree sur
   un arbre ou manque le materiau de l'autre. Le seul arbre ou le chiffre est vrai est l'arbre fusionne.

**Elle est deja executable** : les clauses d'egalite (`leCliquetVautExactementCeQueCeLotInstalle`, la clause
*"un plafond superieur au compte reel est une place libre"*, les `hasSize` de `LIllustrationDeContenuResoutEtAucuneNeDortTest`)
rougissent sur l'arbre fusionne si la valeur est fausse. Ce qu'elles demandent : lancer le reacteur complet sur le
commit de fusion avant de le pousser. Aucune regle neuve.

**Consequence pour les deux ecrivains de C** (`DEC-0111` section 8, point 1). Des fichiers disjoints ne donnent
pas deux branches vertes chacune : un SVG qu'aucun item ne nomme *dort*, un item qui nomme un SVG absent ne
*resout* pas, et `LIllustrationDeContenuResoutEtAucuneNeDortTest` juge les deux sens. Lu dans le code, non
execute sur un arbre partage. **Le dessin et l'item qui le nomme arrivent dans le meme commit**, et les cliquets
de poids et d'illustration y sont poses une seule fois. Un worktree partage ou un transfert des SVG vers la
branche du paquet le permettent ; le choix est a `sofia-sm`.

**Fichiers partages entre A, C et le retrait** : `catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest`
(edites par les trois), `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (A et C, pas le retrait).
Ce sont des listes, pas des comptes : on garde les ajouts des deux cotes, et le reacteur complet sur la fusion
le verifie. Le retrait fusionnant le premier, A et C partent de lui.

---

## 5. Ecritures, questions et routages, via `sofia-sm`

| Code | Point | A qui | Bloquant ? |
|---|---|---|---|
| A1 | Corriger le message du cliquet de designation (F1), sur la branche de C, avant ou dans le commit qui monte le cliquet de 2 a 3 | `theo-archi` | oui pour le premier commit du paquet de `MAT-CE1-17`, pas pour son engagement |
| A2bis | Amender `ADR-0080` A2 section 4 : plancher de zone derive de la largeur servie la plus etroite (296 px), 16 unites et non 13 ; sans changer le garde-fou 3, ce qui reste A3 | `theo-archi` | non |
| Q1 | `PRS-CE1-3` (b) : les formes regularisees d'*aller* au singulier (*j'alle*, *tu alles*, *il alle*) sont-elles requises ? Le garde-fou les refuse ; sans elles, (b) reste servable (*ils allent*, *vous disez*, *vous faisez*). Si oui, amendement d'`ADR-0065` de la forme d'`A1` | `nadia-prof` | non, sauf si elle repond oui |
| Q2 | `TRV-CE1-4` : *synonyme* est-il un mot que l'enfant de CE1 lit seul dans un enonce de `FRA-CE1-25`, ou la question dit-elle *"un mot qui veut dire presque la même chose"* ? | `nadia-prof` | non ; fixe la redaction |
| Q3 | Homographes invisibles au garde-fou d'accentuation sur A : *mûr*, *sûr*, *près*, *marché* restent a sa lecture | `nadia-prof` | non |
| Q4 | Quadrillage 6 x 6 en esquisse dans les contraintes d'`ines-design` : suffit-il a engager les items de completion de `MAT-CE1-17` ? | `nadia-prof`, `sofia-sm` | oui pour les items de completion |
| Q5 | Dessinateur de C (Q5 de s51), et un seul commit pour un dessin et l'item qui le nomme (4.4) | `sofia-sm` | oui pour V7 et V8 |
| Q6 | Critere *"sans redemarrage"* de `S52-RETRAIT-SCIENCES-CM1` : sans occurrence en production (3.3) ; a tester sur un identifiant fabrique | `yanis-qa` | non |

---

## 6. Mesure

- **Reacteur complet**, trois executions (section 0), deux worktrees detaches a `7350fb07`, crees pour cette
  mesure et supprimes ensuite ; journaux et sondes hors depot.
- **Sondes** : trois YAML et trois entrees de catalogue, ecrits par un script de poste non commite ; le retrait,
  par le meme script ; aucun fichier du depot n'a ete modifie hors des deux worktrees.
- **Lexique** : liste de 266 mots, classe de test jetable dans le worktree, supprimee avant le reacteur
  complet ; verdicts de `defautsDe`, la fonction du garde-fou, et non d'une copie.
- **Cite sans execution** : `FIG-4` sur un chemin unique (4.2), le double sens de
  `LIllustrationDeContenuResoutEtAucuneNeDortTest` entre deux branches (4.4), le chargement du catalogue au
  demarrage (3.3). Lus dans le code, pas mesures.
- **Mon propre diff** : ce fichier seul. Jobs lances dessus avant commit : `chemins`
  (`verifier-chemins-test.sh`, puis `git ls-files` du depot plus ce fichier dans `verifier-chemins.sh` :
  vert), `prefixes` (`verifier-prefixes.sh` sur le sujet du commit : vert) et `build` (reacteur complet,
  `bash outils/mvn-jdk21.sh -B clean verify` sur `7350fb07` plus ce fichier : 2082 tests, 0 echec, fat jar
  empaquete, `BUILD SUCCESS`, JDK 21.0.10 rapporte). `outils` n'est pas lance : aucun outil n'est touche.
