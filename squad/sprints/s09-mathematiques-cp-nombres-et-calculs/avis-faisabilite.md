# [Sprint s09] Avis de faisabilite - theo-archi

- **Date** : 2026-08-12
- **Auteur** : theo-archi
- **Objet** : les **six competences libres** de `squad/programme/MATRICE-couverture.md` en
  mathematiques CP, domaine "Nombres et calculs". Ce n'est pas un avis story par story : au moment ou
  j'ecris, `docs/backlog/backlog.md` s'arrete au sprint 8 et aucune story `S9-` n'existe.
  `camille-po` cadre les siennes en parallele ; cet avis porte donc sur ce que le code **peut** et ne
  peut pas porter, ligne de matrice par ligne de matrice, et il devra etre relu story par story quand
  elles seront ecrites.
- **Contrainte de sprint dont je pars, tranchee par l'utilisatrice** : contenu pur. Pas de mecanique
  neuve, pas de gabarit Thymeleaf neuf, **pas une ligne de `recreo.css`**, aucun travail de design -
  ni mesure de rendu, ni garde-fou CSS neuf, ni arbitrage visuel. Les garde-fous CSS existants restent
  en place et verts.
- **Code relu** : `YamlContentPackLoader`, `ContentSourceFactory`, `QuestionContentSourceFactory`,
  `AdditionsBorneesContentSourceFactory`, `AdditionsBorneesContentSource`, `ListContentSource`,
  `Question`, `Item`, `ContentDescriptor`, `ContentSource`, `BanditManchot`, `BanditManchotSession`,
  `catalogue.yaml`, les quatre paquets servis, `fragments/manche.html`, `accueil.html`,
  `MecaniquesConfiguration`, et les six garde-fous qui jugent le contenu
  (`ChaquePaquetPorteUnIndiceTest`, `ChaquePaquetATrousPorteSonTrouSurChaqueItemTest`,
  `ChaquePaquetExpliqueSesItemsAVariationTest`, `AucunIndiceNeContreditUnAutreItemDuMemePaquetTest`,
  `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`,
  `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest`), plus
  `AucunVocabulaireDeMatiereTest`, `LesGardeFousSontMisALEpreuveTest`, `CatalogueChargeableIT`,
  `PropositionsAccentueesJouablesIT`.
- **Pieces de reference** : `ADR-0006`, `ADR-0007`, `ADR-0012`, `ADR-0023`, `ADR-0024`, `ADR-0028`,
  `ADR-0029`, `DEC-0002`, `DEC-0016` et son amendement du 2026-08-11,
  `squad/conformite/FICHE-bandit-manchot-additions-cp.md` (verdict du 2026-08-11),
  `contenu-repris/README.md`.
- **Nom de ce dossier** : `s09-mathematiques-cp-nombres-et-calculs`, choisi faute de slug existant au
  moment ou j'ecris. `sofia-sm` anime le planning apres moi et **peut le renommer** ; ce fichier suit,
  il n'a aucune autorite sur le nom du sprint.
- **Ce que je ne fais pas ici** : je ne decide ni le contenu, ni le decoupage pedagogique, ni
  l'entree d'un jeu au catalogue, ni le nombre de manches. Tout cela est produit (`camille-po`) ou
  pedagogique (`nadia-prof`).

## Synthese - les six lignes

| Ligne de la matrice | Avis | Ce qui manquerait, s'il manque quelque chose |
|---|---|---|
| 1. Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100 | **Faisable sans une ligne de code**, en texte | rien de technique. Une variante "objets a compter" est portable **en caracteres** sans gabarit ni CSS, mais son rendu n'est pas garanti et sa recevabilite appartient a `nadia-prof` (`DEC-0002`). Une variante en **image** (SVG, `img`) demanderait un gabarit : hors perimetre |
| 2. Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`) | **Faisable sans une ligne de code** | rien. Un piege de forme a signaler a l'auteur, pas un manque : voir la section 2 |
| 3. Calcul mental - soustractions dans la limite de 10 | **Faisable sans une ligne de code**, en paquet enumere | rien. Le **generer** comme les additions demanderait du code : hors perimetre, et ce n'est pas necessaire |
| 4. Complements a 10 | **Faisable sans une ligne de code**, mais c'est la seule des six qui **bute sur une regle du depot** | rien a coder ; il manque une **decision produit** de `camille-po` sur `nombreDeManches` ou sur les formes d'items admises. Voir la section 4, avec le calcul |
| 5. Resoudre des problemes simples d'addition/soustraction en situation (1 etape) | **Faisable sans une ligne de code**. Le sujet n'est pas le schema, c'est le pli - et vous avez raison sur ce point | rien a coder. Un **defaut d'affichage connu, mesure, ouvert au backlog** touche exactement cette forme d'enonce, et la contrainte du sprint interdit de le mesurer. Voir la section 5 |
| 6. Reconnaitre et utiliser la monnaie dans des problemes concrets | **Faisable sans une ligne de code**, et c'est prouve par du materiau reel deja joue | rien de technique. Meme reserve de pli qu'a la ligne 5 |

**Aucune des six lignes n'est infaisable, et aucune ne demande une ligne de code de production.** Le
soupcon qui m'a ete transmis - lignes 1 et 6 hors perimetre parce qu'elles appellent des visuels - est
**contredit par le depot** sur les deux lignes, et pour deux raisons differentes : la ligne 6 possede
un materiau textuel deja joue en production, et la ligne 1 n'a jamais eu besoin d'image pour etre
posee en texte. Le detail est en sections 1 et 6, avec ce qui reste vrai dans le soupcon.

Un seul avis remonte a `camille-po` comme obstacle a trancher avant developpement : **la ligne 4**
(section 4). Un seul remonte comme risque a accepter les yeux ouverts : **les lignes 5 et 6**
(section 5).

## 0. Ce que le schema de contenu offre reellement aujourd'hui

C'est la question qui commande les six autres. Reponse en trois faits verifies dans le code, pas
supposes.

### 0.1 Deux formes de paquet, et une seule enveloppe

`ADR-0007` : un seul chargeur, `YamlContentPackLoader`, avec une enveloppe commune - `schema`, `id`,
`type`, `descripteur` (quatre chaines : matiere, niveau, domaine, competence) - puis un registre de
fabriques indexe par `type`. Deux types existent :

| `type` | Ce que le paquet declare | Source de contenu | Sert aujourd'hui |
|---|---|---|---|
| `question` | `items:` - une liste, chaque item portant `enonce`, `bonneReponse`, `distracteurs`, et deux textes optionnels `indice` et `explication` | `ListContentSource`, vivier fini, tirage sans remise | les 3 paquets de francais |
| `additions-bornees` | `parametres:` - `sommeMaximale`, `nombreDePropositions`, `indice` | `AdditionsBorneesContentSource`, tirage combinatoire, ne s'epuise jamais | le paquet d'additions CP |

**Le depot fait donc les deux**, et la question posee - "l'arithmetique se genere-t-elle ou
s'enumere-t-elle" - a une reponse factuelle : **l'unique paquet d'arithmetique servi aujourd'hui se
genere** (`type: additions-bornees`, aucun item ecrit a la main), et **tout le reste s'enumere**. Ce
n'est pas un principe, c'est l'etat livre : la generation a ete ecrite pour les additions parce que
`DEC-0007` demandait une repartition statistique par somme, ce qu'une liste ecrite a la main ne sait
pas tenir.

### 0.2 Une soustraction, une comparaison par signe, un complement a 10 : ils se **declarent**

`Question` ne porte que du texte : `enonce`, `bonneReponse`, `distracteurs` (des chaines),
`indice`, `explication`. Il n'y a **aucun type d'operation, aucun operande, aucune borne** dans le
chemin partage. Concretement, et c'est verifiable ligne par ligne dans un YAML de type `question` :

- une soustraction se declare : `enonce: "9 - 4"`, `bonneReponse: "5"`, `distracteurs: ["4", "6"]` ;
- une comparaison par signe se declare : `enonce: "47 ___ 38"`, `bonneReponse: ">"`,
  `distracteurs: ["<", "="]` - les trois signes sont des chaines comme les autres, et le
  formulaire les rapporte tels quels (le gabarit rend le libelle par `th:text`, donc l'echappement
  HTML est fait par Thymeleaf, et `PropositionsAccentueesJouablesIT` prouve deja l'aller-retour de
  formulaire sur des propositions non ASCII) ;
- un complement a 10 se declare : `enonce: "7 ___ = 10"` ou `"7 + ___ = 10"`, `bonneReponse: "3"`.

**Aucune de ces trois formes ne demande du code.** Ce qui demanderait du code, c'est de les
**generer** : une nouvelle source de contenu dans `domain/content/source` et sa fabrique dans
`adapter-content`. C'est permis par l'architecture (le sous-paquet `content/source` est
**volontairement** hors du perimetre d'`AucunVocabulaireDeMatiereTest`, `ADR-0006` y logeant les
bornes d'une matiere dans la source qui en a besoin), mais c'est du code de production, donc hors du
perimetre de ce sprint - et rien ne l'exige.

### 0.3 La dette 2 de la version precedente est fermee, et je dis par quoi

La dette nommee dans `CLAUDE.md` etait une **configuration de difficulte taillee pour
l'arithmetique**, que chaque contenu de francais devait remplir de valeurs factices. Sa forme exacte
est encore lisible dans le depot, dans la reserve : `contenu-repris/cp-calcul-mental.yml` declare

```
difficulty:
  minOperand: 0
  maxOperand: 10
  operations: [ADDITION, SOUSTRACTION]
  distractorCount: 2
```

`round-two` ne l'a pas reproduite, et pas seulement par intention :

1. **Structurellement.** `ADR-0006` fait vivre les bornes **dans le paquet qui en a besoin**, sous
   `parametres`, et `ADR-0007` ne passe ce bloc qu'a **la seule fabrique du `type` declare**. Un
   paquet de type `question` ne declare **aucun** parametre : `QuestionContentSourceFactory` n'en lit
   aucun. Un contenu de francais n'a donc rien de factice a remplir, et un contenu de mathematiques
   enumere non plus.
2. **Par un garde-fou, pas par la vigilance.** `AucunVocabulaireDeMatiereTest` refuse le vocabulaire
   de matiere - dont `addition`, `soustraction`, `operande`, `somme`, `calcul` - dans les mecaniques
   **et** dans les types partages de `domain/content` (le paquet racine seul). Sa Javadoc nomme
   explicitement cette dette. C'est ce test, et non une convention, qui empeche la configuration
   d'arithmetique de revenir dans le chemin que toutes les matieres traversent.

**Ce sprint ne la rouvre donc pas**, a une condition que je nomme pour qu'elle soit surveillee en
review : que l'arithmetique reste **dans son paquet**. Le geste qui la rouvrirait est precis - faire
lire a la fabrique d'additions un parametre du genre `operations: [ADDITION, SOUSTRACTION]` pour
qu'une seule fabrique serve plusieurs operations. Ce geste-la est du code, donc hors perimetre ce
sprint, et **aucune regle du depot ne l'interdirait** : je le dis plutot que de pretendre le garder.
Je n'ecris pas la regle correspondante, et le motif est net - toute formulation que j'ai essayee est
soit tautologique (le registre garantit deja qu'un `parametres` ne va qu'a une fabrique), soit
decorative (interdire un mot dans un fichier de `content/source`, alors qu'`ADR-0006` y autorise
justement le vocabulaire de matiere). **Le jour ou une story demande de generer une deuxieme
operation, cela demande un `ADR`** : deuxieme generateur, ou generateur parametre. Ce n'est pas ce
sprint.

### 0.4 Ce qu'un paquet neuf doit porter pour passer la CI, en une liste

Six proprietes, toutes derivees et executables aujourd'hui. Un auteur de contenu qui les tient n'a
aucune surprise a `mvn verify` :

1. **Un indice sur chaque item, non vide** (`ChaquePaquetPorteUnIndiceTest`, `ADR-0016`). La dette est
   a zero et le plafond a zero : aucune exemption n'est possible sans un diff visible. L'indice est
   tire par la chaine reelle, pas relu dans le YAML.
2. **Le trou est homogene** (`ChaquePaquetATrousPorteSonTrouSurChaqueItemTest`). Des qu'**un** item
   d'un paquet contient le marqueur `___`, **tous** doivent le contenir. Consequence directe pour ce
   sprint : un paquet ne peut pas melanger `"7 + ___ = 10"` et `"Quel est le complement de 7 a 10 ?"`.
   Le choix est libre, l'homogeneite ne l'est pas.
3. **Plus d'items que de manches, strictement** (`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`,
   `ADR-0023`), pour un paquet inscrit au catalogue. Les quatre entrees actuelles declarent
   `nombreDeManches: 10`, donc onze items distincts au moins.
4. **Au plus quatre propositions par item** - non pas par une regle qui le dit, mais par la pince
   d'`ADR-0029` : une cinquieme colonne rougirait
   `ChaqueColonneQueLeContenuPeutProduirePorteSonDecalageTest` (aucun `animation-delay` ne la nomme),
   et la faire declarer rougirait la borne de 250 ms de `nadia-prof` dans `adapter-web`. **Le seul
   chemin vert d'une cinquieme colonne est de revoir le decalage, c'est-a-dire du design** : hors
   perimetre de ce sprint. Verifie sur le materiau : les 429 listes de distracteurs du depot (4
   paquets servis, 61 paquets de reserve) donnent un maximum de **4** propositions, jamais 5. Un
   contenu repris tel quel reste donc dans les clous ; un contenu neuf a quatre distracteurs n'y est
   pas.
5. **Une explication par item a variation**, mais seulement pour un paquet qui declare une `graphie`
   (`ChaquePaquetExpliqueSesItemsAVariationTest`, `ADR-0024`). Un paquet de mathematiques n'en declare
   pas : cette clause ne le concerne pas.
6. **Aucun item ne repete l'ecran d'un autre item du meme paquet** - clause neuve de ce planning,
   `ADR-0032`, voir la section 4.

Deux choses qu'un paquet neuf **n'a pas** a faire, et je le dis parce que cela evite du travail
inutile : il n'a pas besoin de code de cablage (une entree de `catalogue.yaml` et un fichier sous
`contenu/` suffisent, les mecaniques etant injectees en `List<GameMechanic>`), et il n'a pas besoin
d'etre nomme dans un test - les six garde-fous ci-dessus derivent tous leur perimetre du repertoire ou
du catalogue.

## 1. Denombrer, lire, ecrire et decomposer les nombres jusqu'a 100

**Faisable sans une ligne de code, en texte.** Les quatre verbes de la competence se posent tous en
`enonce` + `bonneReponse` + `distracteurs` :

- lire et ecrire : chiffre vers mot-nombre et l'inverse ;
- decomposer : `"45 = 40 + ___"`, ou `"Combien y a-t-il de dizaines dans 63 ?"` ;
- denombrer : c'est le seul des quatre ou la question du visuel se pose.

**Sur le visuel, le depot dit autre chose que le soupcon, et je donne les deux moities.** Ce qui est
faux dans le soupcon : il n'existe **aucune contrainte technique** qui obligerait a un gabarit ou a du
CSS pour montrer une quantite. `DEC-0002` cree explicitement un registre - le contenu destine a
l'enfant - qui **echappe** a la regle de redaction du depot et n'est "ni interdit ni autorise par
principe" d'employer un pictogramme ; il est gouverne par la porte de `nadia-prof`, cas par cas. Le
gabarit rend l'enonce par `th:text`, donc n'importe quelle chaine passe, et la reserve contient du
materiau exactement de cette forme : `contenu-repris/memory/memory-quantite-cp.yml` associe un chiffre
a une **face de de** rendue par un caractere. Aucun garde-fou automatique du depot n'interdit un
caractere de ce genre dans un paquet (les scripts de CI verifient les prefixes de commit et les
chemins de poste, jamais les pictogrammes).

Ce qui reste vrai dans le soupcon, et que je ne minimise pas : **un caractere n'est pas une image**.
`DEC-0002` elle-meme, pour le signal juste/faux, a ecarte l'emoji au motif que son rendu varie selon
la police et l'appareil, et a prefere une forme CSS. La ou un signal d'interface se devait d'etre
identique partout, un pictogramme de contenu qui rend mal ne rend pas la competence fausse, mais il
peut la rendre illisible - et **c'est un jugement de `nadia-prof`, pas un verdict d'architecture**.
Constater le rendu demanderait une mesure (`outils/mesurer-rendu.sh`), que la contrainte de sprint
exclut. Mon avis d'architecte est donc : **techniquement portable, sans code ; recevabilite et rendu
non tranches ce sprint**.

**Consequence pratique pour le cadrage** : si la story ecrit "l'enfant compte des objets **dessines**"
(SVG, `img`), alors elle demande un gabarit et sort du perimetre. Si elle ecrit une formulation
textuelle - avec ou sans caractere-pictogramme - elle ne demande rien d'autre qu'un fichier YAML.
Le choix entre les deux est pedagogique.

## 2. Comparer et ranger des nombres jusqu'a 100 (`<`, `>`, `=`)

**Faisable sans une ligne de code.** Espace d'items tres large, aucune contrainte de schema, les trois
signes sont des chaines ordinaires.

Deux pieges de forme a transmettre a l'auteur du paquet - ce sont des pieges, pas des manques :

1. **Trois signes, trois propositions au maximum utile.** `BanditManchotSession.melanger` empile la
   bonne reponse et les distracteurs **sans dedoublonner** : si un distracteur repete la bonne
   reponse, l'enfant voit deux boutons identiques dont les deux sont comptes justes (la comparaison
   est une egalite de chaines). Rien dans le depot ne l'interdit aujourd'hui - je n'ecris pas la regle
   correspondante, parce que sur les **397 items reels** du depot (4 paquets servis, 61 de reserve)
   ce defaut n'apparait **aucune fois**, et qu'un generateur de propositions le previent deja de son
   cote (`distinct()` dans `AdditionsBorneesContentSource`). C'est une consigne de redaction, pas un
   automate.
2. **Le format de l'enonce se choisit une fois pour tout le paquet** (point 2 de la section 0.4) : soit
   tous les items portent le trou (`"47 ___ 38"`), soit aucun (`"Quel nombre est le plus grand ?"`).
   La forme sans trou avec des nombres en propositions est licite et **restera verte** meme si le meme
   enonce revient sur onze couples de nombres differents : c'est exactement le cas que la clause
   d'`ADR-0032` prend soin de ne pas refuser.

## 3. Calcul mental - soustractions dans la limite de 10

**Faisable sans une ligne de code**, en paquet enumere de type `question`. Espace d'items calcule :
**66** couples `(a, b)` avec `b <= a <= 10`, ou **45** en ecartant les cas degeneres `b = 0` et
`b = a`. Onze items pour une partie de dix manches ne posent donc aucun probleme d'espace.

**Ce qui serait du code, et n'est pas necessaire** : generer les soustractions comme les additions le
sont. Cela demanderait une source de contenu neuve et sa fabrique - et, si quelqu'un preferait
parametrer la fabrique d'additions pour qu'elle serve les deux operations, cela demanderait un `ADR`
et rouvrirait la discussion de la dette 2 (section 0.3). Je le signale pour que personne ne le fasse
"en passant" dans un sprint de contenu.

**Consequence assumee d'un paquet enumere** : la repartition statistique par somme que `DEC-0007`
exigeait des additions n'existe pas pour un paquet enumere - elle est ce que l'auteur ecrit. Ce n'est
pas un defaut technique ; c'est un point que `nadia-prof` juge en jouant, et je le nomme pour qu'il ne
surprenne personne a la porte pedagogique.

## 4. Complements a 10 - la seule ligne qui bute sur une regle du depot

**Faisable sans une ligne de code, mais elle demande une decision produit avant developpement.**

Le calcul, avec sa methode. Les quatre entrees de `catalogue.yaml` declarent `nombreDeManches: 10`.
`ADR-0023` exige **strictement plus** d'items distincts que de manches, donc **onze** au minimum. Or
la forme canonique `a + ? = 10` n'admet que **onze** items, pour `a` de 0 a 10 - et **neuf** si l'on
ecarte les deux cas degeneres (`0 + ? = 10` et `10 + ? = 0`), ce que le generateur d'additions ecarte
deja largement de son cote parce que le programme ne demande pas de calculer avec un operande nul.

**Onze items exiges pour un espace de onze, ou de neuf.** C'est la seule des six lignes ou la regle du
depot et la competence se touchent. Trois sorties, dont une seule m'appartient :

1. **Baisser `nombreDeManches` pour ce jeu** (par exemple 8, le plancher technique etant 4 :
   `CatalogueChargeableIT` exige `nombreDeManches >= BanditManchotSession.LONGUEUR_MINIMALE_POUR_AIDE`,
   pour que l'aide apres trois echecs reste atteignable). C'est une **decision produit**,
   `ADR-0012` : la longueur d'une partie vit dans le catalogue. Je la route a `camille-po`, je ne la
   prends pas.
2. **Elargir les formes d'items admises** dans le paquet (`10 = 7 + ___`, `___ + 7 = 10`, et
   l'inverse) pour depasser onze items distincts. **Decision pedagogique** : est-ce la meme competence
   pour un enfant de CP ? `nadia-prof`.
3. **Completer le paquet par des copies.** C'est la sortie que la CI laissait ouverte, et c'est ce que
   j'ai ferme dans le meme geste que cet avis.

**`ADR-0032`, ecrit ce jour.** `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` compte des
`ItemId` distincts, et `QuestionContentSourceFactory` derive l'identifiant d'un item de sa **position**
dans le YAML : deux items rigoureusement identiques comptent donc pour deux, et un paquet trop court
se verdissait par duplication sans qu'aucun diff ne le montre. Pire, la propriete que `nadia-prof` a
verifiee a la main - vingt-quatre parties sans une seule repetition,
`FICHE-bandit-manchot-additions-cp.md` section "0 bis" point C - devenait fausse en silence. La clause
neuve refuse que deux items d'un meme paquet fini presentent le **meme ecran** (enonce + jeu de
propositions), elle est verte sur les 397 items reels du depot, et elle est prouvee mordante par trois
mutations. Detail complet, alternatives et limites dans
`squad/decisions/adr/ADR-0032-deux-items-d-un-meme-paquet-ne-presentent-jamais-le-meme-ecran.md`.

**Ce que cela change pour le cadrage de la story** : la ligne 4 n'est pas plus difficile a ecrire, mais
elle ne peut plus etre "remplie". `camille-po` doit trancher le point 1 ou le point 2 **avant** que le
developpement commence, sans quoi le lot arrivera en review avec un paquet de neuf ou dix items et un
test rouge que personne n'aura vu venir.

## 5. Problemes simples d'addition/soustraction en situation (1 etape)

**Faisable sans une ligne de code, et le sujet n'est pas le schema : c'est le pli.** Sur ce point le
soupcon qui m'a ete transmis est **juste**, et je peux le chiffrer.

Le schema ne pose aucune limite de longueur d'enonce, et `DEC-0016` decision 2 **interdit
explicitement** tout critere en nombre de caracteres - `nadia-prof` a etabli qu'un raisonnement par
lettres manque son pire cas reel. `ADR-0028` declare la propriete "la reponse est au-dessus du pli"
**non gardable** par un automate de ce depot, la confie a `nadia-prof`, et lui livre l'outil pour la
constater sans main (`outils/mesurer-rendu.sh <largeur> <url> <png> [hauteur]`).

**Le defaut n'est pas hypothetique, il est mesure, et il porte precisement sur cette forme d'enonce.**
Verdict de `nadia-prof` du 2026-08-11 (`FICHE-bandit-manchot-additions-cp.md`, C1), viewport de
reference 360 x 640, pli lu a `y = 640` :

| Jeu | Etats ou un cote du cabinet passe sous le pli |
|---|---|
| "Calcul CP" (additions, enonces de 3 a 6 caracteres) | **0 sur 10** |
| "Le bon son" (un mot en capitales) | **0 sur 19** |
| "Le bon petit mot" (phrases, jusqu'a 97 caracteres) | **11 sur 30** |
| "Le bon verbe" (phrases) | **24 sur 33** |

L'amendement du 2026-08-11 a `DEC-0016` nomme la cause commune : **la longueur de l'enonce a 2 rem**,
"pas le nombre de propositions ni la mise en page en colonnes", et precise que le defaut ne survient
**jamais sans indice** - donc il touche l'enfant qui vient d'echouer trois fois, celui qui en a le
moins les moyens.

Les problemes en situation sont des phrases. Pour donner un ordre de grandeur - et non un critere,
puisque `DEC-0016` l'interdit - les plus longs enonces de ce type dans la reserve font **71
caracteres** (`contenu-repris/quiz/cp-monnaie.yml`), contre 97 pour le plus long des items en defaut
de "Le bon petit mot". Plus courts, donc, mais dans le meme regime de longueur que les deux jeux en
defaut, et tres loin des 3 a 6 caracteres du jeu de mathematiques qui est propre.

**Mon avis, sans deborder sur ce qui n'est pas a moi** : rien n'empeche techniquement d'ecrire ce
contenu, aucune regle ne le refusera, et la CI sera verte. Mais ce sprint introduirait des ecrans qui
entrent dans la zone d'un defaut **connu, mesure, ouvert au backlog et non corrige**, et la contrainte
du sprint - aucune mesure de rendu, aucune ligne de CSS - fait que ni le constater ni l'attenuer n'est
possible en cours de sprint. Deux consequences a accepter les yeux ouverts, ou a eviter :

- `nadia-prof` peut ouvrir une reserve, voire refuser, sur ce motif, et elle en a seule le droit ;
- l'une des deux pistes de correction que `DEC-0016` garde ouvertes est du **design** (agrandir le
  budget vertical), donc indisponible ce sprint ; l'autre est pedagogique (raccourcir les enonces) et
  reste ouverte a l'auteur du contenu, gratuitement, en ecrivant court.

Si `camille-po` veut minimiser ce risque sans rien decider de design, la seule variable qui lui reste
est **la longueur des enonces qu'elle demande** - et c'est une consigne de redaction, pas un seuil.

## 6. Monnaie (pieces et billets simples) dans des problemes concrets

**Faisable sans une ligne de code, et c'est la ligne ou le depot contredit le plus nettement le
soupcon** - parce que la preuve n'est pas un raisonnement, c'est du contenu deja joue.

`contenu-repris/quiz/cp-monnaie.yml` couvre cette competence **entierement en texte**, sans une seule
image : "Tu as une piece de 2 euros et une piece de 1 euro. Combien as-tu en tout ?", bonne reponse
"3 euros", distracteurs textuels. Huit items de cette forme, joues en production sur `main`. La piece
n'y est jamais **reconnue visuellement** : elle est **nommee**. Techniquement, ce paquet se transpose
dans le schema `question` sans un octet de Java - il faut lui ajouter un `indice` par item (point 1 de
la section 0.4), reecrire l'enveloppe au format de `round-two`, et il est servi.

Deux nuances honnetes :

1. **Le maximum de propositions y est de 4** (un item a trois distracteurs), soit exactement le
   plafond que la feuille couvre aujourd'hui (`ADR-0029`). Repris tel quel, c'est vert ; un item de
   plus en propositions serait rouge et deviendrait un chantier de design. A signaler a l'auteur.
2. **"Reconnaitre" la monnaie n'est peut-etre pas la meme competence quand la piece est nommee plutot
   que montree.** C'est exactement la question que `contenu-repris/README.md` reserve a `nadia-prof` -
   un contenu repris repasse la porte pedagogique comme un contenu neuf. Je constate que le code peut
   porter les deux formes textuelles ; **je ne dis pas** qu'un enonce nomme couvre la competence, et
   ce n'est pas a moi de le dire.

Meme reserve de pli qu'a la section 5 : ce sont des phrases.

## Ce que ce sprint ajoute au depot, cote architecture

- **Un `ADR`** : `ADR-0032`, avec sa clause executable, ses cinq cas de mise a l'epreuve et ses trois
  mutations mesurees. Ce n'est **pas** une regle ArchUnit - le contenu d'un YAML charge a l'execution
  n'est pas visible d'ArchUnit - donc le compte de regles ArchUnit du depot reste a **28**. Methode du
  compte : inchange par ce lot, aucune `ArchRule` neuve n'est declaree.
- **Six tests de plus** : 347 avant ce lot, **353** apres. Methode : somme des blocs `Results:` du
  reacteur sur `mvn -B verify` (`domain` 43, `application` 7, `adapter-web` 178, `adapter-content` 39
  puis 45, `bootstrap` 77) plus les 3 tests d'integration de la phase `failsafe`. Mesure sur JDK
  21.0.10 ; le `java` du `PATH` de ce poste est un JRE 8 qui ne compile pas, et `JAVA_HOME` a ete
  pointe sur un JDK 21 pour cette mesure.
- **Aucune ligne de code de production, aucun gabarit, aucune ligne de `recreo.css`.** La contrainte
  du sprint est tenue par ce lot.

## Ce que je n'ai pas fait, et pourquoi

- **Aucune mesure de rendu, aucun garde-fou CSS neuf, aucun arbitrage visuel** : la contrainte du
  sprint l'exclut. Les garde-fous CSS existants tournent et sont verts, je les cite sans les toucher.
- **Aucun `ADR` sur le choix generer/enumerer.** Il n'y a pas de decision a prendre ce sprint - le
  contenu s'enumere, faute de code neuf - et je ne sais pas ecrire aujourd'hui de regle non
  decorative sur ce choix (section 0.3). Le jour ou une story demande de generer une operation de
  plus, cet `ADR` devient necessaire et je l'ecrirai alors, avec le choix reel : deuxieme generateur,
  ou generateur parametre.
- **Aucune regle sur les propositions dupliquees dans un item** (section 2), parce que le defaut
  n'apparait sur aucun des 397 items reels du depot. Une regle contre un defaut que personne n'a
  commis, dans un depot qui en compte deja assez, coute plus qu'elle ne rapporte tant que le materiau
  ne la demande pas.
- **Aucun avis sur le decoupage en stories, la priorite, ni le contenu lui-meme.** `camille-po`,
  `sofia-sm`, `nadia-prof`.
