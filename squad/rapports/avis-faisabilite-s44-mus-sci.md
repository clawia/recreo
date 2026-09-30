# s44 - Avis de faisabilite (`theo-archi`) - `S44-MUS-VOCABULAIRE` (`MUS-CP-11`) et `S44-SCI-OBJET-TECHNIQUE` (`SCI-CM1-13`)

- **Date** : 2026-09-30
- **Base** : `round-two` a `127ab8dc`, verifie sur un worktree neuf avant ecriture. Aucun diff de
  code ni de contenu dans cet avis, aucun autre fichier touche.
- **Pourquoi ce fichier est ici** : aucun dossier `squad/sprints/s44-...` n'existe, et l'ouvrir
  n'est pas mon role. `sofia-sm` le reprend ou le cite dans `planning.md` a l'ouverture, comme
  `avis-faisabilite-s44-mat-division.md`, que cet avis ne retouche pas.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucun critere d'acceptation
  (`camille-po`), aucune affectation (`sofia-sm`), et le Done pedagogique reste a `nadia-prof`. Il ne
  rejuge aucune condition : il dit comment la tester et ou le texte des stories s'ecarte de sa source.

---

## 1. Verdicts

| Lot | Ligne | Verdict | Complexite |
|---|---|---|---|
| `S44-MUS-VOCABULAIRE` | `MUS-CP-11` | **faisable en l'etat**. Premiere ligne d'une matiere neuve, sans cout technique au-dela de l'inventaire des chaines exposees (section 3.2). Une question de forme a `nadia-prof` est utile avant l'ecriture, non bloquante (Q1) | basse |
| `S44-SCI-OBJET-TECHNIQUE` | `SCI-CM1-13` | **faisable sous condition**. Zero ligne de code de production. **La condition est une correction de story, pas un geste technique** : la story cite `SCI-1` dans sa formulation du 2026-09-03, remplacee deux fois, et son critere pointe vers le mauvais champ (section 5.1). Elle omet aussi `SCI-4` et `FER-1` a `FER-3`, que le test du paquet devra tenir (Q2) | basse sur le mecanisme, moyenne sur le test du paquet |

**Aucun `ADR` neuf, aucune `ArchRule` neuve, aucun garde-fou transverse sur les deux lots** (section
7). Les deux paquets sont des paquets statiques de type `question`, joues au bandit manchot, sans
illustration ni texte a voix haute.

## 2. Citations des stories, ouvertes et non reprises de confiance

| Citation | Resolue ? |
|---|---|
| `MATRICE-couverture.md` ligne 1852, `MUS-CP-11` | oui, `non couvert - jouable au choix textuel, sous MUS-1` |
| `MATRICE-couverture.md` ligne 2136, `SCI-CM1-13` | oui, `non couvert - jouable au choix textuel, sous BIA-3` |
| `education-musicale/competences-cp.md`, ligne 11 du jugement, `MUS-1`, `MUS-2`, mention exigee | oui, lignes 86, 90 a 98 et 102 a 104, mot pour mot |
| `sciences-et-technologie/competences-cm1.md`, ligne 13 du jugement | oui, ligne 109, mot pour mot |
| `BIA-3`, `ARBITRAGE-PREALABLE-s27-les-matieres-absentes-du-programme.md` | oui, ligne 562, mot pour mot |
| `SCI-1` citee "mot pour mot" depuis `competences-cm1.md` | **le texte est exact, mais ce n'est plus la condition qui vaut** - section 5.1 |

Les deux codes resolvent cote referentiel sous la forme `- [CODE] libelle` qu'exige
`ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` (`ADR-0081`), qui derive ses lignes de
`squad/programme` par parcours : `MUS-CP-11` ligne 53 de son referentiel, `SCI-CM1-13` ligne 65.
Aucun geste n'est du pour que le prefixe `MUS` soit reconnu. Les identifiants de condition cites
par cet avis sont tous alloues dans `squad/decisions/REGISTRE-identifiants.md` (`MUS-1` et `MUS-2`
lignes 1370-1371, `SCI-1`, `SCI-3`, `SCI-4` lignes 1553 a 1556, `BIA-3` ligne 1023, `CON-1` ligne
1045, `HIS-3` ligne 1287, `FER-1` a `FER-3` lignes 1192 a 1194).

## 3. Le patron couvre les deux lots, mesure sur les commits qui l'ont servi

### 3.1 Le cout d'un paquet textuel est de cinq fichiers, aucune classe de production

Methode : `git show --stat` sur le commit qui a ajoute chaque paquet de reference.

| Commit | Paquet | Fichiers |
|---|---|---|
| `a3655b62` (s39) | `cp-arts-outils`, **premiere ligne d'une matiere neuve** | `catalogue.yaml`, le paquet, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `PaquetCpArtsOutilsTest`, `TexteAccentueExposeParLePortEntrantTest` |
| `e11a9325` (s43) | `cm1-eps-effets-activite`, premier paquet d'un couple (matiere, niveau) neuf | les memes cinq |
| `5718f84d` (s40) | `cm1-sciences-extraire-information`, lot de sciences CM1 | les memes, sans test de paquet (quatre) |

Les deux lots de s44 touchent ces cinq fichiers et pas un de plus. Aucun n'entre dans
`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, qui ne concerne que le CE1 de
mathematiques. La mecanique `bandit-manchot` ne lit aucun champ de matiere (`Question`, `ADR-0006`)
et aucun code de production ne nomme une matiere : les seules occurrences Java de `"Arts
plastiques"` et `"Histoire des arts"` sont dans `PaquetCpArtsOutilsTest` et dans l'inventaire de
`TexteAccentueExposeParLePortEntrantTest`.

### 3.2 `MUS-CP-11` : une matiere neuve ne coute que des entrees d'inventaire

**Zero paquet d'education musicale servi** : sur les 103 paquets du repertoire de contenu, la cle
`matiere` prend dix valeurs, et aucune n'est l'education musicale. Le paquet de s44 en ouvre une
onzieme et il est seul dans son lot, au sens d'`ADR-0079`.

Ce que cela coute, et rien d'autre : dans `TexteAccentueExposeParLePortEntrantTest`, la matiere, le
domaine (`"Échanger, partager et argumenter"`, absent de l'inventaire aujourd'hui), la competence,
le titre et la consigne. La graphie de la matiere est libre puisqu'aucun paquet ne l'ecrit encore
(`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`, `ADR-0049`, compare des paquets entre eux) ;
le titre du referentiel ecrit `Éducation musicale`, sur le modele de `"Éducation physique et
sportive"`, et c'est la graphie a prendre pour ne pas en ouvrir une seconde au prochain paquet.

**Le paquet est au CP, donc borne des son premier commit** par
`AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (`ADR-0089`) : `PLAFOND_DE_DETTE = 5`, dette
nommee sur trois paquets d'EMC, un paquet neuf entre a zero. L'exemple de `nadia-prof` (*"Quelle
partie d'une chanson revient après chaque couplet ?"*) compte 8 mots.

### 3.3 `SCI-CM1-13` : le nom du paquet et l'identifiant de jeu evidents sont pris

`cm1-sciences-objet-technique` est le paquet de `SCI-CM1-12`, et `bandit-manchot-objet-technique-cm1`
son entree de catalogue. `AucuneEntreeDuCatalogueNEnDoubleUneAutreTest` (`ADR-0045`) rougit sur un
identifiant de jeu repete ; un nom de fichier repete ne se cree simplement pas. Le paquet neuf prend
un nom qui dit l'evolution. C'est un piege de fabrication, pas un cout.

## 4. `MUS-CP-11` - `MUS-1` et `MUS-2` dans le test du paquet

Sur le patron de `PaquetCpArtsOutilsTest` : items tires par le chargeur reel, casts en `Question`.
Champs lus : `enonce`, `bonneReponse`, `distracteurs`, `indice`, `explication`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `MUS-2` | **structurel** : `texteAVoixHaute()` vide sur chaque item. C'est le seul canal du produit par lequel un adulte recoit une consigne (`ADR-0075`) ; et `LeTexteAVoixHauteEtLaConsigneAdulteSeDeclarentEnsembleTest` rend alors rouge toute `consigneAdulte` posee sur l'entree de catalogue. **Lexical** : aucun texte ne contient, sans casse, `adulte`, `fredonn`, `frapp`. `chant` n'y est pas : *"Quelle partie d'une chanson chante-t-on plusieurs fois ?"* est un enonce legitime et serait rouge | un geste demande avec un autre verbe (*"tape dans tes mains"*), une consigne de chanter |
| `MUS-1` | **partiel, lexical** : aucun texte ne contient `écout` ni `entend` | *"en effacant l'ecoute, la reponse reste-t-elle trouvable ?"* : un enonce qui decrit un son sans aucun de ces deux mots passe |
| `FER-1` | les quatre termes de la parenthese (`refrain`, `couplet`, `pulsation`, `silence`) sont chacun la bonne reponse d'au moins un item, sur le modele de `TERMES_DE_LA_PARENTHESE` de `PaquetCpArtsOutilsTest` | que chaque item serve vraiment le terme |
| `FER-2` | au moins 11 items (`nombreDeManches: 10`, `DEC-0064`) | rien |
| `FER-3` | deux taches, la moins servie a trois items au moins, distinguees par une propriete lisible (forme de l'enonce ou ensemble de bonnes reponses, comme `PaquetCm1SciencesObjetTechniqueTest`) | que ce soient deux operations mentales et non deux phrases |
| ligne declaree | `lignesDeReferentiel: ["MUS-CP-11"]` ; deja tenu par `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest`, a ne pas repeter | rien |
| mention exigee | **non encodable par le test du paquet** : c'est un texte de la matrice, et le critere de la story en confie l'adaptation a `nadia-prof` | entierement |

**Le lexique de `MUS-1` n'est pas prouve sur materiau reel, et je le dis** : aucun paquet de musique
n'existe, et `contenu-repris/` n'en porte aucun (zero occurrence de `refrain`, `couplet`,
`pulsation` hors d'un texte-support de `cm1-substituts`). Sa direction est sure : un faux rouge se
voit au premier `mvn -B verify` et se corrige en reformulant, jamais en affaiblissant l'assertion.

**Pourquoi Q1 est utile avant l'ecriture.** `FER-1` exige `pulsation` et `silence` en bonne
reponse. Les enonces qui y menent sont ceux ou `MUS-1` et `MUS-2` mordent le plus : la definition
courante de la pulsation passe par un battement qu'on frappe ou qu'on entend, et celle du silence
par un son qu'on n'entend pas. L'exemple de `nadia-prof` ne porte que sur le refrain. Une forme
validee d'avance pour ces deux termes evite une reecriture a l'audit ; elle ne se decide pas ici.

## 5. `SCI-CM1-13` - les conditions, dans leur version qui vaut

### 5.1 La story cite une `SCI-1` remplacee, et son critere nomme le mauvais champ

La story cite *"Apres une reponse fausse, le retour dit pourquoi elle est fausse, en une phrase"* et
en tire le critere *"quand le retour s'affiche, alors il dit en une phrase pourquoi la reponse est
fausse"*. C'est la formulation du 2026-09-03. Elle a ete remplacee le 2026-09-05
(`ARBITRAGE-PREALABLE-s29-...`, section 1.1), puis de nouveau le 2026-09-06
(`ARBITRAGE-PREALABLE-s30-ce-qui-ferme-une-ligne-de-sciences-et-le-prix-des-quinze-reserves.md`,
section 1.1, lignes 83 a 93) : *"c'est celle-ci qui vaut"*. Le referentiel n'a pas ete amende, et la
story l'a recopie.

**Ce que change la version qui vaut, et c'est technique autant que pedagogique** :

1. le porteur est l'`indice`, affiche des la premiere erreur (`BanditManchotSession.indice()`, M1),
   et non l'`explication` de l'ecran de retour, que `nadia-prof` a mesuree invisible pour la
   quasi-totalite des enfants qui se trompent (meme document, lignes 60 a 81) ;
2. elle porte sur les **items dont un distracteur est une conception fausse courante**, pas sur tous ;
3. l'indice ne nomme pas la bonne reponse et ne dit jamais *"ta reponse"* ;
4. elle s'accompagne de **`SCI-4`** : un tel item offre trois propositions au moins.

Un developpeur qui suit la story a la lettre remplit `explication` et croit tenir `SCI-1` : c'est
exactement le defaut que `nadia-prof` nomme ligne 110 a 112. **C'est la condition du verdict**, et
elle ne coute qu'une correction de texte par `camille-po` avant que le paquet s'ecrive (Q2).

### 5.2 Conditions encodables

Sur le patron de `PaquetCm1SciencesObjetTechniqueTest` et `PaquetCm1SciencesEtatsMatiereTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `SCI-1` (2026-09-06) | la presence de l'indice est deja tenue par `ChaquePaquetPorteUnIndiceTest`, a ne pas repeter. Test du paquet : chaque indice compte **une phrase** (convention de `phrases` d'`ADR-0089`) et ne contient ni la bonne reponse de son item ni `ta réponse` | que l'indice dise pourquoi la conception est fausse |
| `SCI-4` | chaque item porte deux distracteurs. Plus simple que de designer les items "a conception fausse", que rien ne declare ; mesure du lot, 124 items sur 134 ont deja deux distracteurs | quels items portent une conception fausse |
| `SCI-3` | **partiel** : items dont la bonne reponse porte un chiffre ou un siecle en chiffres romains (`\b[IVXL]+e\b`), au plus `floor(n / 6)` (demonstration ci-dessous) | les valeurs ecrites en lettres (*"il y a cent ans"*), et si un siecle est une valeur a restituer |
| `BIA-3` | **partiel, lexical** : aucun texte ne contient `\bdifficiles?\b`, `\bpauvres?\b`, `\bpas pratiques?\b`, `\bdurs?\b` (detail ci-dessous) | *"aucun n'affirme que l'objet ancien etait moins bien"*, qui se lit et ne se compte pas |
| `HIS-3` | **partiel** : chaque ligne `- enonce:` du fichier brut porte un commentaire `# HIS-3:` suivi d'un texte non vide, lu comme `UneConditionDeLotSeCompteSurLeLotDeMatiereEtNonSurUnFichierTest` lit ses marqueurs | que la source existe et dise le fait |
| `CON-1` | rien de plus que l'existant : `LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` (`ADR-0086`) et `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (`ADR-0083`) tiennent la part formelle. La story la reecrit *"sans l'enonce complet"* ; la transposition sans image est celle de s30, section 2.5 : *"aucun item ne se repond sans lire ce qu'il faut lire"* | le fond, entierement |
| `FER-1` | ligne **disjonctive** a deux elements (s30, tableau 2.2, ligne 13) : l'evolution, et la fabrication **ou** l'usage ; un ensemble de bonnes reponses par element, sur le modele de `REPONSES_DE_FONCTION` | que l'ensemble dise l'element |
| `FER-2`, `FER-3` | au moins 11 items ; deux taches, trois items au moins pour la moins servie. Le couple d'exemple de s30 est *"dire ce qui a change / dire ce qui n'a pas change"* | que ce soient deux taches |
| ligne declaree | `lignesDeReferentiel: ["SCI-CM1-13"]`, deja tenu par le garde-fou d'`ADR-0081` | rien |

**`SCI-3` se mesure sur le lot, et le test du paquet suffit aujourd'hui.** `nadia-prof` l'ecrit :
*"`SCI-3` ne se verifie pas paquet par paquet"* (s30, section 2.5). Mesure de ce jour, script Python
de poste non commite, cle `bonneReponse` des douze paquets `cm1-sciences-*` servis : **134 items, 0
bonne reponse portant un chiffre**. Avec `k` items numeriques sur `n` dans le paquet neuf, le lot
vaut `k / (134 + n)`, et `k <= floor(n / 6)` entraine `k <= (134 + n) / 6`. L'assertion de paquet
est donc **plus stricte** que la condition de lot et la tient. Elle cesse de suffire le jour ou un
autre paquet du lot sert une valeur numerique : **ce qui me ferait changer d'avis**, et alors la
condition se branche sur le mecanisme d'`ADR-0079` avec un marqueur declare, comme `HIS-1`. Pas
avant : une clause de lot sur un lot sans aucune valeur numerique serait verte sur une population
vide.

**`BIA-3`, lexique prouve sur materiau reel.** Methode : le meme script, champs `enonce`,
`bonneReponse`, `distracteurs`, `indice`, `explication`, `texteSupport` des 103 paquets servis.

| Motif | Occurrences | Lecture |
|---|---|---|
| sous-chaine `dur` | 72, dans 15 paquets | inutilisable : `durée`, `duré` |
| mot `dure` | 32 | **le verbe durer** (*"la récréation dure"*, *"un effort qui dure"*). Exclu : un paquet sur l'evolution d'un objet ecrira *"une ampoule qui dure plus longtemps"*, et ce serait un faux rouge |
| mots `dur`, `durs` | 3 | l'adjectif, retenu |
| `pratique` seul | 3 | le nom (*"des pratiques très anciennes"*) : on retient `pas pratique`, la forme que cite `BIA-3` |
| `difficile`, `pauvre` | 2 et 0 | retenus |

Le feminin `dure` echappe donc a l'assertion : c'est le residu nomme, a `nadia-prof`. *Une limite
de plus* : `dur` est aussi une propriete de materiau (`SCI-CM1-11`). *"Le plastique est moins dur
que le métal"* serait rouge ; la reformulation est facile, et je laisse l'assertion stricte.

### 5.3 Le piege du marqueur `HIS-1`

`HIS-1` est derivee sur le lot (`ADR-0079`) : un seul item marque `# HIS-1:` soumet tout le lot, et
chaque item du lot doit alors en porter un. Mesure : **zero marqueur `# HIS-1:` dans les douze
paquets de sciences CM1**. Or la forme inline de `HIS-3` qui existe dans le depot est celle des
paquets d'histoire-geographie, ou elle suit un `HIS-1` sur la meme ligne (`# HIS-1: restitution ;
HIS-3: ...`). **La recopier rendrait rouges les treize paquets du lot d'un coup.** Le marqueur du
paquet neuf commence par `# HIS-3:` et ne porte jamais `HIS-1`.

## 6. Collisions et adjacences avec le contenu servi

`AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` et
`AucunIndiceNeContreditUnAutreItemDuMemePaquetTest` ont le meme paquet pour perimetre : **aucun
garde-fou du depot ne compare deux paquets**, et rien ci-dessous n'est vu par un automate.

**`MUS-CP-11` : aucune collision.** Aucun paquet servi ne porte `refrain`, `couplet` ni `pulsation`
en item. Une adjacence de sens, nommee pour qu'elle ne soit pas trouvee a l'audit : `silence` est
servi deux fois au CP dans son sens de conduite (`cp-emc-regles-de-vie`, distracteur *"attendre en
silence"* ; `cp-pluriel`, *"en silence"*), et ce sens-la est celui de `MUS-CP-10`, `non couvrable en
l'etat`. Le paquet neuf sert le silence comme repere musical.

**`SCI-CM1-13` : aucune ligne servie ne porte la meme tache, deux adjacences de materiau.** Mesure :
aucun item servi n'interroge l'evolution d'un objet dans le temps ; les occurrences de `autrefois`
sont toutes des exercices de conjugaison ou d'accord. `QLM-CP-15` (autrefois et aujourd'hui, source
de `BIA-3`) est `non couvert`, ligne 1608. Les deux adjacences :

1. **`cm1-sciences-objet-technique` (`SCI-CM1-12`, `couvert-valide`)** sert la fonction de quatre
   objets (velo, lampe de poche, taille-crayon, ventilateur). La seconde tache d'exemple de s30,
   *"dire ce qui n'a pas change (la fonction demeure)"*, a pour bonne reponse une fonction : sur ces
   quatre objets, elle reprendrait une bonne reponse deja servie. Ce n'est pas un double-compte tant
   que l'item evalue l'evolution et declare `SCI-CM1-13` (`ADR-0081` clause 5) ; c'est un item qui
   ressemblerait a un autre.
2. **`cm1-sciences-familles-materiaux` (`SCI-CM1-11`)** a pour bonnes reponses `le bois`, `le
   métal`, `le verre`, `le plastique`, `le tissu`. Un item de changement de materiau dont la bonne
   reponse est une famille de materiaux reprend la tache *"nommer la famille"* de cette ligne.

Une adjacence verticale au CP, deja traitee sous `CYC-1` par le paquet de `SCI-CM1-12` :
`cp-qlm-objet-technique` sert la selle, le guidon et la pedale du velo.

Je route les deux adjacences a `nadia-prof`, via `sofia-sm`, avant l'ecriture. Je ne dis pas quel
discriminant poser, et je ne propose pas de regle qui comparerait deux paquets sur le sens.

## 7. Aucun `ADR`, aucune `ArchRule`

Chaque condition des sections 4 et 5 est une propriete d'un item ou de la liste des items d'un seul
paquet. ArchUnit lit du bytecode, et un enonce YAML n'y est pas une propriete lisible (meme motif
qu'`ADR-0101`) : le mecanisme automatique qui garde ces deux stories est le test de chaque paquet,
execute par le job `build`. Aucune decision d'architecture n'est prise : pas de matiere a declarer
en code, pas de champ neuf, pas de mecanique.

Compte inchange depuis s43 : **36 `static final ArchRule`, 35 `@ArchTest`**. Methode :
`git grep -c` sur les fichiers suivis de `domain`, `application`, `adapters` et `bootstrap`, arbres
`contreexemple` exclus, donc `target/` et worktrees exclus par construction. Sans l'exclusion, 38 :
les deux unites d'ecart sont dans l'unique `RegleContreExemple.java` du faux module de
`LesGardeFousSontMisALEpreuveTest`.

## 8. Pieges de fabrication communs, nommes avant le premier rouge

1. **`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`** de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` :
   les deux paquets sont textuels et s'y declarent avec leur compte mesure, comme `cp-arts-outils`
   et `cm1-eps-effets-activite`. La table est verifiee dans les deux sens.
2. **`AucunTitreDeJeuNePorteSonNiveauTest`** : aucun titre ne porte `CP` ni `CM1`.
3. **`TexteAccentueExposeParLePortEntrantTest`** : toute chaine de descripteur neuve entre dans
   l'inventaire. Pour `SCI-CM1-13`, le domaine `"Matériaux et objets techniques"` y est deja.
4. **Le test de paquet n'est pas un garde-fou obligatoire** (s43, section 10.4) ; ici il est la
   seule garde automatique des conditions des sections 4 et 5, et il est donc du.

## 9. Risques

| Code | Risque | Direction | Parade |
|---|---|---|---|
| R1 | Paquet de sciences ecrit contre la `SCI-1` citee par la story : `explication` remplie, indice sans conception fausse | faux vert, refus a l'audit | Q2 avant l'ecriture |
| R2 | Marqueur `HIS-1` recopie depuis l'histoire-geographie (5.3) | rouge sur treize paquets | marqueur `# HIS-3:` seul |
| R3 | Lexiques de `MUS-1` et `MUS-2` non prouves sur materiau reel (section 4) | faux rouge | reformuler, ne pas affaiblir |
| R4 | `BIA-3` partiel : feminin `dure` et jugement de valeur hors lexique | faux vert sur le residu | `nadia-prof`, en jouant |
| R5 | `SCI-3` par chiffres : valeur ecrite en lettres | faux vert | `nadia-prof` ; branchement sur `ADR-0079` si un autre paquet du lot sert un chiffre |
| R6 | Items d'evolution qui reprennent une bonne reponse de `SCI-CM1-11` ou `SCI-CM1-12` (section 6) | invisible a tout automate | discriminant pose par `nadia-prof` avant l'ecriture |

## 10. Questions routees, via `sofia-sm`

| Code | Question | A qui |
|---|---|---|
| Q1 | `FER-1` exige `pulsation` et `silence` en bonne reponse. Quelle forme d'enonce les sert sans supposer une ecoute (`MUS-1`) ni un geste frappe (`MUS-2`) ? Non bloquant | `nadia-prof` |
| Q2 | Remplacer, dans `S44-SCI-OBJET-TECHNIQUE`, la citation de `SCI-1` par sa version du 2026-09-06 (s30, section 1.1) et le critere *"le retour dit"* par *"l'indice dit"*, sur les items a conception fausse ; ajouter `SCI-4` ; citer `FER-1` a `FER-3`, que `S44-MUS-VOCABULAIRE` ne cite pas non plus. **Condition du verdict sur le lot de sciences** | `camille-po` |
| Q3 | Les deux adjacences de la section 6 : quel discriminant entre un item d'evolution et un item de fonction (`SCI-CM1-12`) ou de famille de materiau (`SCI-CM1-11`) ? | `nadia-prof` |

## 11. Ce que cet avis ne fait pas

Il n'ecrit ni les paquets ni leurs tests : les sections 4 et 5.2 sont la specification que le
developpeur affecte encode, dans le meme commit que son paquet. Il ne corrige pas la story (Q2 est a
`camille-po`), ne tranche aucune question, et ne touche aucun garde-fou existant.
