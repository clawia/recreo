# s47 - Avis de faisabilite (`theo-archi`) - cinq lots de `DEC-0104` : `MAT-CE1-07`, `QLM-CP-15`, `LVE-CP-06` (instance A), `EMC-CP-14`, `EMC-CM1-10` (instance B)

- **Date** : 2026-10-02
- **Base** : `round-two` a `57b8d68c`, qui porte `DEC-0104` et la section "Sprint 47" de
  `docs/backlog/backlog.md`. Aucun diff de code ni de contenu dans cet avis.
- **Pourquoi ce fichier est ici** : `DEC-0104` section 7 route l'avis de faisabilite a `theo-archi`
  apres l'ouverture du dossier de sprint par `sofia-sm`, qui reprend ou cite cet avis dans
  `planning.md`.
- **Ce que cet avis ne vaut pas** : il ne fixe aucun contenu, aucun critere, aucune affectation, et ne
  rejuge aucune condition. Le Done pedagogique reste a `nadia-prof`.

---

## 1. Verdicts

| Lot | Ligne | Verdict |
|---|---|---|
| `S47-MAT-TABLES` | `MAT-CE1-07` | **infaisable en l'etat des conditions, sans que l'architecture soit en cause.** La mecanique et le chargeur suffisent, aucun `ADR` n'est du. Mais le fait `10 × 1`, que `TAB-CE1-1` exige, n'admet **qu'un seul** distracteur dans les quatre familles de `TAB-CE1-6`, qui exige trois propositions distinctes (section 3.2). Il faut une decision de `nadia-prof` avant le premier item (Q1) |
| `S47-QLM-AUTREFOIS` | `QLM-CP-15` | **faisable en l'etat.** Zero code de production |
| `S47-LVE-CULTURE-CP` | `LVE-CP-06` | **faisable en l'etat.** Zero code de production. Une question non bloquante (Q5) |
| `S47-EMC-ALERTE-CP` | `EMC-CP-14` | **faisable en l'etat.** Zero code de production. Deux pieges de fabrication (section 6), une question non bloquante (Q4) |
| `S47-EMC-VOTE-CM1` | `EMC-CM1-10` | **faisable en l'etat.** Zero code de production. Une adjacence a signaler (Q6) |

**Aucune mecanique neuve, aucune `ArchRule` neuve, aucun `ADR`, aucune persistance.** Les cinq lots
sont des paquets statiques de type `question`, joues au `bandit-manchot`, sur le patron de s46. L'etat
d'une partie reste dans la session HTTP (`DEC-0028`). La seule lecture qui demanderait un `ADR` est
une lecture de `TAB-CE1-3` par partie plutot que par paquet (Q2).

## 2. Ce que coute chaque lot

Le patron de s46 vaut tel quel : le paquet, son entree de catalogue, son test de paquet, et
l'inventaire de `TexteAccentueExposeParLePortEntrantTest` pour les chaines neuves (titre, consigne,
competence). S'y ajoute une ligne que mon avis de s46 n'enumerait pas, alors que chaque lot textuel
de s46 l'a payee : **tout paquet dont aucun item n'a trois propositions numeriques entre dans
`PAQUETS_HORS_DU_DOMAINE_NUMERIQUE`** de `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
avec son motif et son compte d'items (`ADR-0051`). Sans elle, `aucunPaquetNEstEcarteEnSilenceDuCalculDeRang`
est rouge. Cela vaut pour `QLM-CP-15`, `LVE-CP-06` et `EMC-CM1-10`, et pour `EMC-CP-14` selon sa
fabrication (section 6).

Faits verifies sur l'arbre de `57b8d68c` :

- **les cinq codes existent au bon niveau** (`grep -F` sur `squad/programme/`) : `MAT-CE1-07` dans
  `mathematiques/competences-ce1.md`, `QLM-CP-15`, `LVE-CP-06` et `EMC-CP-14` dans les
  `competences-cp.md` de leur matiere, `EMC-CM1-10` dans `enseignement-moral-et-civique/competences-cm1.md`.
  `ChaquePaquetServiDeclareLesLignesDeReferentielQuIlSertTest` verifie le niveau contre le nom du
  referentiel ;
- **aucune matiere neuve** : `LVE-CP-06` est le premier paquet de langues vivantes au CP, mais la
  matiere est servie au CM1 sous la graphie `"Langues vivantes (anglais)"`
  (`cm1-anglais.yaml`). Le paquet CP reprend cette graphie exacte
  (`UneMatiereNAQuUneSeuleGraphieDansLeContenuServiTest`), et le declencheur d'extension
  d'`AucunVocabulaireDeMatiereTest` ("toute matiere neuve au catalogue") ne joue pas. Aucun code de
  `adapter-web`, `application` ni `bootstrap` ne regroupe les jeux par matiere : rien n'est a
  etendre pour un couple matiere-niveau neuf ;
- **trois lots de CP** (`QLM-CP-15`, `LVE-CP-06`, `EMC-CP-14`) passent sous
  `AucunEnonceDeCpNeDepasseQuinzeMotsNiDeuxPhrasesTest` (`LON-1`, `LON-2`) : quinze mots au plus,
  deux phrases au plus, la seconde etant la question ;
- **`FER-2`** : au moins 11 items par paquet pour une entree a 10 manches
  (`ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest`).

En plus, pour le lot `MAT` seulement : une entree de `SousPartieDeMathematiquesCe1`
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`, `ADR-0097`), sur la convention des
entrees existantes : ligne `MAT-CE1-07`, libelle du referentiel sans le point final,
`"Calcul mental - tables de multiplication de 2, 5 et 10, puis de 3 et 4"`. Il ne porte ni
deux-points ni point-virgule, donc aucun blanc insecable de `TYP-1` n'est en jeu. Le titre de l'entree
ne peut pas nommer le niveau (`AucunTitreDeJeuNePorteSonNiveauTest`) ; celui du CM1 est
`"Les tables de multiplication"`, et aucun garde-fou n'interdit deux titres egaux sous deux niveaux.
Le choix est a `camille-po`.

## 3. `S47-MAT-TABLES` (`MAT-CE1-07`)

### 3.1 L'espace reel est de 40 faits, pas de 50

Les 50 couples `(a, b)` de `TAB-CE1-1` (`a` dans {2, 3, 4, 5, 10}, `b` de 1 a 10) comptent deux fois
chacune des 10 paires dont les deux facteurs sont dans {2, 3, 4, 5, 10} : `3 × 4` et `4 × 3` sont le
meme produit. Il reste **40 faits distincts**. Le plus petit paquet qui tient `TAB-CE1-1` a donc 40
items, pas 50.

Avec la regle d'unite de `TAB-CE1-2` (le facteur de table est celui qui appartient a {2, 3, 4, 5, 10},
le plus petit si les deux y appartiennent), ces 40 faits se repartissent en 10 / 9 / 8 / 7 / 6 sur les
tables de 2, 3, 4, 5 et 10. Un item par fait donne 57,5 % / 22,5 % / 20 % : `TAB-CE1-2` est tenue.
Le compte de la section 1.4 de l'arbitrage source (30 / 20, soit 60 % / 20 % / 20 %) est fait par le
premier facteur, pas par cette regle. Sa conclusion tient quand meme.

### 3.2 Le fait `10 × 1` n'a qu'un distracteur

Avec `a = 10` (seul facteur dans l'ensemble) et `b = 1` :

1. **(a) produit voisin par defaut** : exige `b >= 2`, exclu ;
2. **(b) produit voisin par exces** : `10 × 2 = 20`, admis ;
3. **(c) somme des facteurs** : `11`, superieur au produit `10`, exclu ;
4. **(d) table voisine** : `9 × 1` et `11 × 1`, et ni 9 ni 11 n'est dans {2, 3, 4, 5, 10}, exclus.

Une seule valeur, 20. `TAB-CE1-6` exige trois propositions distinctes. `TAB-CE1-1` exige ce fait.
**Les deux conditions ne sont pas tenables ensemble.** La section 1.4 de l'arbitrage source verifie
que la famille (c) exclut `b = 1`. Elle ne verifie pas que (a) l'exclut aussi, ni que (d) est vide
pour la table de 10. `10 × 1` est le seul fait des 40 dans ce cas.

Constat voisin, non bloquant : `TAB-CE1-3` dit *"zero est conforme"* pour les items a facteur 1,
alors que `TAB-CE1-1` en exige cinq (`2 × 1` a `10 × 1`). Le plancher reel est cinq items.

### 3.3 Le reste de l'espace tient sous le garde-fou de rang

Mesure exhaustive sur les 39 autres faits (section 10) :

- **rang unique impose** : `2 × 1` ne peut etre que "la plus petite". `5 × 1`, `2 × 2`, `3 × 3`,
  `4 × 4`, `5 × 5` et `10 × 6` a `10 × 10` ne peuvent etre que "au milieu" (10 faits). La table de
  10 ne donne jamais d'autre rang que le milieu, et la table de 5 jamais "la plus petite" ;
- **rangs atteignables** : la plus petite sur 24 faits, le milieu sur 38, la plus grande sur 25. Un
  paquet de 39 items a 13 / 13 / 13 est constructible.

Un paquet qui renforce la table de 10 par des items repetes augmente le rang du milieu sans recours.
Les deux dettes du garde-fou sont a leur plafond exact (`DETTE_DE_RANG` 2 sur 2,
`DETTE_DE_SEUIL_MINIMUM` 3 sur 3) : aucune exemption n'est disponible, et je n'en ouvrirai pas.

### 3.4 Ce qu'il faut pour que la ligne devienne faisable

Une decision pedagogique, que je ne prends pas (Q1). Trois sorties, avec leur seul effet technique :

1. **`10 × 1` avec un seul distracteur** (deux propositions). Le garde-fou de rang et
   `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` ignorent un item qui n'a pas deux
   distracteurs (lu dans leur code, non execute). Cet item sort alors de toute garde de rang sans que
   rien ne le compte ;
2. **une famille qui donne une seconde valeur a `10 × 1`**. Le test de paquet la recalcule comme les
   quatre autres ;
3. **`TAB-CE1-1` exclut le fait `{1, 10}`**. Rien a changer cote test, sauf l'espace enumere.

### 3.5 Conditions encodables, une fois Q1 tranchee

Sur le patron de `PaquetCe1SoustractionsPoseesTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `TAB-CE1-1` | chacun des 40 faits `{a, b}` est l'enonce d'au moins un item, sans ordre | rien |
| `TAB-CE1-2` | facteur de table derive de l'enonce par la regle d'unite ; parts calculees sur les items | rien |
| `TAB-CE1-3` | items a facteur 1 au plus 20 % des items du paquet | la lecture "par manche" (Q2) |
| `TAB-CE1-4` | parmi les items aux deux facteurs differents et sans facteur 1, au moins un tiers ont le facteur de table en seconde position | la population du tiers (Q3) |
| `TAB-CE1-5` | enonce strictement `x × y = ?` avec U+00D7, aucun `x` minuscule, propositions faites de chiffres seuls | rien |
| `TAB-CE1-6` | marqueur en commentaire nommant la famille de chaque distracteur, relu et compare au calcul a partir des operandes ; trois propositions distinctes ; rangs entre 10 % et 50 % | rien |

Aucun garde-fou existant ne lit le signe `×` hors des tests de paquet (`git grep` sur les sources
Java) : `cm1-tables-multiplication` l'ecrit deja, entre guillemets doubles, sans difficulte.

## 4. `S47-QLM-AUTREFOIS` (`QLM-CP-15`)

Sur le patron de `PaquetCpQlmObjetTechniqueTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `FER-1` | marqueur par item `# element:`, l'element "objets et images du quotidien" present en bonne reponse | que l'item serve l'element |
| `FER-2`, `FER-3` | au moins 11 items ; marqueur `# tache: rattacher` ou `# tache: comparer`, au moins 3 pour la moins servie | que ce soient deux taches |
| `BIA-3` | aucun texte servi (enonce, propositions, indice) ne contient les mots entiers `difficile`, `dur`, `pauvre`, ni la suite `pas pratique` | les autres formes de "moins bien" |

**Piege d'encodage de `BIA-3`.** Le critere est le mot entier, pas la sous-chaine : `dur` est dans
`durée`, et c'est la faute de s05. `dure` est a la fois l'adjectif (*"la vie etait dure"*) et le
verbe *durer* : une liste qui l'ajoute rougit sur le verbe. Je le laisse hors de la liste et a la
relecture de `nadia-prof`.

## 5. `S47-LVE-CULTURE-CP` (`LVE-CP-06`)

Meme patron.

- `FER-1` : marqueur par item `# element: fete`, `lieu` ou `vie-quotidienne`, les trois presents en
  bonne reponse.
- `BIA-2` en partie calculable : marqueur par item `# pays:`. Assertion : tout pays nomme l'est sur au
  moins deux items de bonnes reponses differentes. Que ce soient deux *traits* distincts reste a
  `nadia-prof`.
- `BIA-1` en partie calculable : aucun texte servi ne contient `chez nous`. Le reste reste a
  `nadia-prof`.
- La mention de couverture partielle est une ligne de la matrice, pas une propriete de paquet.

**Noms propres anglais.** `AucunMotServiNeSEcarteDeSonAccentTest` ne rougit que sur un mot qui n'est
pas francais et dont une autre accentuation l'est : un nom de fete anglais ne le declenche pas, et
`cm1-anglais` le prouve deja. La question est ailleurs. Le critere *"sans demander de lire ou de
produire un mot d'anglais"* dit-il quelque chose d'un nom de fete anglais en bonne reponse (Q5) ?

## 6. `S47-EMC-ALERTE-CP` (`EMC-CP-14`)

Sur le patron de `PaquetCpEmcRolesEcoleCommuneTest`.

| Condition | Assertion | Ce qui reste a `nadia-prof` |
|---|---|---|
| `FER-1` | marqueur par item `# element: prevenir-un-adulte` ou `numero-d-urgence`, les deux presents | que l'item serve l'element |
| `FER-2`, `FER-3` | au moins 11 items ; marqueur `# tache:`, deux valeurs, au moins 3 pour la moins servie | que ce soient deux taches |
| `EMC-7` | tout item qui porte un numero porte un marqueur `# EMC-7:` suivi d'une source non vide | que la source dise le fait, et l'unicite (Q4) |
| `EMC-1`, `EMC-2`, `EMC-5` | aucune | entierement |

**Piege 1, les numeros nus entrent dans le garde-fou de rang.** Un item dont les trois propositions
sont `"15"`, `"17"`, `"18"` est lu par `lireValeur`. Si toutes les propositions numeriques ne prennent
que ces trois valeurs, le rang de la bonne reponse est fixe par le numero : 15 est toujours la plus
petite, 18 toujours la plus grande. Equilibrer les rangs revient alors a equilibrer les bonnes
reponses, aucune au-dela de la moitie des items numeriques, chacune a au moins 10 %. Il faut au moins
trois items numeriques : a un ou deux, l'un des deux seuils est rouge par construction. Le paquet
melange items textuels et items numeriques, donc il entre dans `PAQUETS_A_ITEMS_ECARTES` de
`LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` avec le nombre exact d'items ecartes. Ecrire
`"le 18"` sort l'item du domaine et rend le garde-fou muet sur une strategie qui existe ("presser le
plus grand numero"). Ce n'est pas une solution.

**Piege 2, la repetition de la meme bonne reponse.** La clause 1 de
`LaFormeDesPropositionsNeDesigneJamaisLaBonneReponseTest` rougit sur trois items ou plus qui portent
la meme bonne reponse d'au moins trois mots avec des propositions qui se correspondent une a une.
*"prevenir un adulte"* sur plusieurs situations est exactement cette forme. Faire varier la formulation
ou les distracteurs l'evite.

**Adjacence, sans collision.** `cp-emc-roles-ecole-commune` (`EMC-CP-13`) sert `"les pompiers"` en
bonne reponse sur le role des services. Un item ne sert qu'une ligne (`ADR-0081`, clause 5), rien
n'est compte deux fois.

## 7. `S47-EMC-VOTE-CM1` (`EMC-CM1-10`)

Sur le patron de `PaquetCm1EmcInstitutionsTest`. `FER-1` est calculable par marqueur
(`# element: delegue`, `election`, `suffrage-universel`, les trois presents en bonne reponse).
`FER-2`, `FER-3` comme en section 6. `EMC-8` en partie calculable, comme `EMC-CM1-01` a s46 : aucun
enonce ne s'adresse a l'enfant a la deuxieme personne pour lui demander ce qu'il ferait ou voterait.
`EMC-1`, `EMC-2`, `EMC-5` restent a `nadia-prof`.

**Adjacence a signaler a l'instance B.** `cm1-emc-institutions` (`EMC-CM1-09`, fermee a s46) porte deux
items dont la bonne reponse est `"le conseil municipal"` a la question *"Qui a voté cette décision ?"*.
Un item de `EMC-CM1-10` sur l'election du conseil municipal porterait sur le meme objet. Rien n'est
compte deux fois. La frontiere entre les deux lignes n'est pas ecrite : `DEC-0104` section 4.6 ne
traite que les frontieres entre instances (Q6). Releve par recherche lexicale de `élu`, `vote`,
`élection`, `délégué` et `suffrage` sur les paquets de `contenu/`.

## 8. Architecture : rien de neuf a encoder

Chaque condition des sections 3 a 7 porte sur un item ou un paquet YAML. ArchUnit lit du bytecode :
les tests de paquet les gardent, executes par le job `build`. Aucune dependance neuve entre
mecaniques ni vers un contenu concret, donc `DecouplageMecaniqueContenuTest` n'a rien de neuf a
interdire.

Le seul cas qui toucherait l'architecture est une lecture de `TAB-CE1-3` par partie (Q2) :
`ListContentSource` tire sans remise et au hasard, et ne borne pas une famille d'items par partie.
Garantir *"au plus deux manches sur dix"* exigerait une source de contenu qui contraint le tirage,
donc un `ADR` et sa regle. Je ne l'engage pas sur une lecture que la condition ne tranche pas.

Compte inchange depuis s46 : **36 `static final ArchRule`, 35 `@ArchTest`**. Methode : `git grep -o`
sur les fichiers suivis de `domain`, `application`, `adapters` et `bootstrap`, arbres `contreexemple`
exclus, donc `target/` et worktrees exclus par construction.

## 9. Questions routees, via `sofia-sm`

| Code | Question | A qui | Bloquante ? |
|---|---|---|---|
| Q1 | `10 × 1` : `TAB-CE1-1` l'exige, `TAB-CE1-6` ne lui donne qu'un distracteur (20) et en exige deux (section 3.2). Laquelle des conditions s'amende, ou comment se concilient-elles ? | `nadia-prof` | **oui, avant le premier item du lot `MAT`** |
| Q2 | `TAB-CE1-3` : *"au plus une manche sur cinq"* se lit-il sur les items du paquet (encodable tel quel) ou sur chaque partie (exige une source contrainte et un `ADR`) ? Avec 5 items a facteur 1 sur 40, une partie de 10 manches en tire 3 ou plus dans 8,9 % des cas | `nadia-prof` | non, si la lecture est par paquet |
| Q3 | `TAB-CE1-4` : le tiers se compte-t-il sur les items aux deux facteurs differents et sans facteur 1, ou sur tout le paquet ? | `nadia-prof` | non |
| Q4 | `EMC-7` : le 112 est valable dans toute situation d'urgence. Peut-il apparaitre en distracteur d'un item dont la bonne reponse est 15, 17 ou 18, ou en bonne reponse a cote d'eux ? | `nadia-prof` | non, mais elle fixe les valeurs du piege 1 |
| Q5 | `LVE-CP-06` : un nom de fete anglais en bonne reponse contredit-il *"sans demander de lire un mot d'anglais"* ? | `nadia-prof`, puis `camille-po` pour la story | non |
| Q6 | `EMC-CM1-10` contre `EMC-CM1-09` : ou passe la frontiere sur l'election et le vote du conseil municipal ? | `nadia-prof` | non |
| Q7 | Si Q1 n'est pas tranchee avant l'ecriture, le lot `MAT` ne peut pas etre livre vert sans contredire `TAB-CE1-1` ou `TAB-CE1-6` | `sofia-sm` | planification du lot |

## 10. Mesure

- **Espace et rangs de `MAT-CE1-07`** (sections 3.1 a 3.3) : script Python de poste, non commite,
  qui enumere les 50 couples, les reduit aux faits distincts, attribue le facteur de table par la
  regle de `TAB-CE1-2`, construit les valeurs des familles (a) a (d) avec leurs gardes, retire celles
  qui egalent le produit, et classe le rang de chaque paire de distracteurs de valeurs distinctes. Le
  8,9 % de Q2 est une loi hypergeometrique (5 items a facteur 1 sur 40, 10 tirages sans remise, 3 ou
  plus).
- **Garde-fous cites** : lus dans le code a `57b8d68c`. Aucun n'a ete execute sur un paquet neuf,
  puisqu'aucun n'existe : leurs verdicts sur les lots sont deduits de leur code.
- **Adjacences** : recherches lexicales sur `adapters/adapter-content/src/main/resources/contenu/`.
- **Gardes lancees sur ce fichier** : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10
  tests) et `ToutesLesReferencesDeDecisionResolventTest` (3 tests), par `bash outils/mvn-jdk21.sh -B
  -pl bootstrap -am test -Dtest=...`, zero echec, machine virtuelle des tests en Java 21.0.10 (version
  lue dans les rapports Surefire). `verifier-chemins.sh` sur ce fichier : vert. `mvn -B clean verify`
  complet non lance : ce diff ne touche que ce fichier.
