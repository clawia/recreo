# [Sprint s22] Review - 2026-08-29

- **Horodatage de debut** : 2026-08-28 17:07 (`planning.md`)
- **Horodatage de fin** : 2026-08-29 20:32
- **Duree effective** : 1 jour 3 h 25
- **Anime par** : sofia-sm

## Increment livre

Trois paquets CM1 neufs entrent au catalogue - `cm1-comprehension` (comprehension de texte, 20
items, `S17-2`), `cm1-multiplications-divisions-10-100` (calcul mental x10/x100, 25 items, `S13-2`)
et `cm1-problemes-durees` (problemes de durees, 24 items, `S13-3`) - portes par un champ
`texteSupport` neuf sur `Question`, lu par `adapter-content` et rendu par `adapter-web` ; le
catalogue passe de 21 a 24 jeux. **Aucun Done pedagogique n'est prononce par ce document** : les
trois paquets restent a auditer par `nadia-prof`, et `camille-po` seule bouge une ligne de
`squad/programme/MATRICE-couverture.md`.

## Lots fusionnes, lignee verifiee par `git merge-base --is-ancestor` (pas par le nom de branche)

Les huit branches descendent toutes de `round-two` a `2968591` (verifie individuellement avant
toute fusion) et sont toutes des ancetres de `HEAD` a la fin de cette review (verifie individuellement
apres) :

| Ordre | Branche | Tete | Contenu | Fusion |
|---|---|---|---|---|
| 1 | `s22-sofia-3` | `fdafcb0` | Chartes `marc-dev`/`lea-dev` corrigees, arbitrage README section 10 | `d8364f8`, sans conflit |
| 2 | `s22-theo-2` | `4527599` | `ADR-0064` (obstacle 16), obstacle 23, amendement regle 4 `ADR-0063`, `TYP-1` (angle mort du test) | `7a3981d`, sans conflit |
| 3 | `s22-camille-5` | `b516111` | Fiche `S17-2` corrigee contre `DEC-0053` | `f127fe2`, sans conflit |
| 4 | `s22-camille-6` | `0b02cf2` | Balayage `M1`-`M21` propage a 22 fiches, amendement `DEC-0025` | `4b3ceec`, sans conflit |
| 5 | `s22-nadia-2` | `a2cd22d` | Balayage des conditions de mecanique, sortie `M4`/`M18`, scission `M17` | `03098ce`, sans conflit |
| 6 | `s22-lea` | `04aa35f` | `S13-2`, paquet `cm1-multiplications-divisions-10-100` | `5efefe1`, sans conflit |
| 7 | `s22-sofia-2` | `8da64f5` | `S13-3`, paquet `cm1-problemes-durees` | `d49ce47`, **conflit a deux avec `s22-lea`** |
| 8 | `s22-marc` | `7e259a4` | `S17-2`, `texteSupport` (`ADR-0050`), paquet `cm1-comprehension`, cinq fichiers de production sur quatre modules | `fd7eb59`, **conflit a trois avec `s22-lea` et `s22-sofia-2`** |

**Ordre choisi, et son motif.** Les cinq lots documentaires/decisionnels (1 a 5) sont fusionnes
d'abord : aucun n'a de dependance sur les trois autres, et les ecarter tot reduit ce qui reste a
raisonner pendant la resolution du conflit. Parmi les trois lots de contenu, `s22-marc` est le seul
qui durcit un lecteur partage par les vingt-et-un jeux existants (`QuestionContentSourceFactory`,
`PartieController`, `manche.html`, `recreo.css`, `Question`) : c'est exactement le cas que
`squad/README.md` (amendement s21, retro de s21) designe comme devant se mesurer sur l'union des
arbres du sprint, jamais sur son seul arbre. Je merge donc `s22-lea` puis `s22-sofia-2` en premier
pour construire cette union de contenu, et `s22-marc` en dernier : la porte technique qui suit sa
fusion mesure ainsi le lecteur durci contre le catalogue complet du sprint (24 jeux), pas contre son
seul paquet.

## Le conflit a trois, resolu et verifie

**Chiffre par `theo-archi`, verifie a la fusion plutot que suppose.** `s22-lea` et `s22-sofia-2`
entrent en conflit entre elles sur `adapters/adapter-content/src/main/resources/catalogue.yaml` et
sur `bootstrap/.../TexteAccentueExposeParLePortEntrantTest.java` ; `s22-marc` touche les deux memes
fichiers et n'entre en conflit avec aucun des deux directement, produisant un conflit a trois
resolu en deux temps (`s22-sofia-2` contre `s22-lea`, puis `s22-marc` contre les deux). Les six
zones en conflit (deux par fichier x deux fusions) sont **toutes des ajouts** : trois entrees de
`catalogue.yaml` (une par jeu neuf) et leurs symetriques dans l'inventaire du garde-fou de texte
accentue (identifiant, titre, consigne, competence), jamais une contradiction de contenu.

**Ce qui a ete verifie plutot que suppose** apres resolution manuelle :

- `catalogue.yaml` : **24 identifiants distincts**, aucun doublon (`python -c "yaml.safe_load(...)"`
  sur le fichier assemble, avant tout commit de resolution).
- `TexteAccentueExposeParLePortEntrantTest.java` : aucun marqueur de conflit residuel
  (`<<<<<<<`, `=======`, `>>>>>>>`), chaque entree neuve presente une seule fois.
- **Porte technique complete** (section suivante) : vert sur l'arbre assemble.

## Porte technique, mesuree apres fusion complete, methode XML

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet (racine, aucun `-pl`), sur `HEAD` (`fd7eb59`) apres les huit fusions.

**Resultat : BUILD SUCCESS.** `recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`,
`recreo-adapter-content`, `recreo-bootstrap` : SUCCESS. JDK de compilation et d'execution des
tests : 21.0.10 (toolchain).

**Compte de tests, methode XML** (somme des attributs `tests`/`failures`/`errors`/`skipped` de
l'element `<testsuite>` des rapports `TEST-*.xml` de `target/surefire-reports/` et
`target/failsafe-reports/` des cinq modules, methode de `theo-archi`, section 2 de
`porte-technique-lot-theo-s22.md` - **pas** le rapport texte, qui sous-compte de 7 sur ce depot les
classes portees par le moteur JUnit d'ArchUnit) :

| Module | Surefire | Failsafe |
|---|---|---|
| `domain` | 49 | - |
| `application` | 13 | - |
| `adapters/adapter-web` | 332 | - |
| `adapters/adapter-content` | 250 | - |
| `bootstrap` | 111 | 6 |
| **Total** | **755** | **6** |

**761 tests, 0 echec, 0 erreur, 0 ignore.**

**Ecart avec la derniere mesure XML citee au sprint (728, `theo-archi`, sur `s22-theo-2` seul, avant
que `s22-lea`, `s22-sofia-2` et `s22-marc` ne soient fusionnes) : +33, explique integralement,
aucun residu** :

| Delta | Origine |
|---|---|
| +10 | `PaquetCm1MultiplicationsDivisionsTest` (classe neuve, `s22-lea`, `adapter-content`) |
| +9 | `PaquetCm1ProblemesDureesTest` (classe neuve, `s22-sofia-2`, `adapter-content`) |
| +6 | `PaquetCm1ComprehensionTest` (classe neuve, `s22-marc`, `adapter-content`) |
| +1 | `AucunItemNeRepeteLEcranDUnAutreItemDuMemePaquetTest` (une methode `@Test` neuve, `s22-marc`, `adapter-content`, identite d'ecran avec `texteSupport`) |
| +7 | `LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` (classe neuve, `s22-marc`, module `bootstrap`, executee par Surefire) |
| **+33** | **total, verifie egal a 761 - 728** |

`domain` (49), `application` (13) et `adapter-web` (332) sont inchanges : aucun des trois lots ne
touche leurs sources de test.

`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (touchee par `s22-marc`) ne contribue
aucun test au delta : sa map d'exemption gagne une entree (`cm1-comprehension`, reponses en texte
libre), mais son nombre de methodes `@Test` (20) est inchange avant/apres - verifie par comptage
`@Test` sur le fichier a `2968591` et sur `HEAD`.

**Regles ArchUnit (`ArchRule` declarees) : 30, inchange.** Methode : derivation du depot lui-meme,
`LesGardeFousSontMisALEpreuveTest.toutesLesRegles(racine)` invoquee directement (jamais un `grep`),
sur `HEAD`, le 2026-08-29. **0 regle sans mise a l'epreuve**
(`reglesSansMiseALEpreuve`, meme derivation). **Garde-fous de balayage recenses : 50, inchange**
(meme derivation, `balayagesDeProduction`) - les trois classes `PaquetCm1*Test` neuves lisent leur
paquet par flux de classpath (`getResourceAsStream`) plutot que par un chemin litteral
`src/main/`, la marque que cette derivation recherche : elles n'entrent donc pas dans ce
denombrement precis, sans que cela change ce qu'elles couvrent reellement.

**Juges purs des outils** : `bash outils/verifier-jdk-test.sh`, **23 cas** (`verifier_egal`,
comptage structurel sur le script, pas un grep heuristique), tous verts, plus le test negatif
global. Inchange : aucun des sept autres lots ne touche `outils/` ni `squad/outils/` (verifie par
`git diff --stat` de chaque branche contre `2968591`).

## Rapport qualite - yanis-qa

Aucun rapport sur le disque pour ce sprint. La porte technique ci-dessus est mesuree par
`sofia-sm` en clôture de fusion (etape 1 de la procedure de fusion), pas par `yanis-qa` : ce n'est
pas un substitut a son rapport, seulement ce qui existe reellement a cette date.

## Confirmation ArchUnit - theo-archi

`porte-technique-lot-theo-s22.md` (fusionne, `s22-theo-2`) confirme 30 regles et 728 tests sur son
propre arbre (`s22-theo-2` seul, avant `s22-lea`/`s22-sofia-2`/`s22-marc`). La confirmation sur
l'arbre assemble complet est celle de la section precedente, mesuree par `sofia-sm` faute d'une
seconde passe de `theo-archi` posterieure aux huit fusions.

## Relecture - relecteur-pedagogique

Aucune relecture sur le disque pour ce sprint.

## Obstacles du sprint

| # | Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|---|
| 1 | Obstacle 16 (s21, elargi) - cinq garde-fous de contenu bornaient leurs tirages a 60 (un sixieme a 150) alors qu'un paquet en declare 99, 39 items non examines en silence | Technique, garde-fou | `theo-archi` | `ADR-0064` | **CLOS** - bornes desormais derivees du paquet, morsure prouvee par mutation sur materiau reel (39 items) |
| 2 | Obstacle 23 (s21) - `outils/mvn-jdk21.sh` affirmait une version de JDK sans la verifier | Technique | `theo-archi` | - | **CLOS** - l'enveloppe confirme desormais ce que le JDK resolu rapporte, juge pur `verifier-jdk-test.sh` etendu a 23 cas |
| 3 | La regle 4 du contrat de forme (`ADR-0063` decision 3, "aucun item ne laisse l'indice ni le verdict sous le pli") a perdu son demandeur avec l'arbitrage 1 du 2026-08-28 sur le pli | Technique, redaction | `theo-archi` | `ADR-0063` amendee | **CLOS** - amendement date, verifie contre le balayage des conditions de `nadia-prof` du 2026-08-29 |
| 4 | Contradiction entre les chartes `marc-dev`/`lea-dev` (interdisant de rediger le contenu d'un paquet) et la pratique reelle depuis s21 (paquets `cp-syllabes`, `cp-mot-manquant` deja rediges et audites) | Produit, gouvernance | arbitrage de l'utilisatrice | amendement `squad/README.md` section 10 | **CLOS** - arbitrage du 2026-08-29, chartes corrigees dans `s22-sofia-3` |
| 5 | Conflit a trois sur `catalogue.yaml` et `TexteAccentueExposeParLePortEntrantTest.java` entre `s22-lea`, `s22-sofia-2` et `s22-marc`, chiffre par `theo-archi` avant fusion | Technique, fusion | `sofia-sm` | - | **CLOS** - resolution additive, 24 identifiants distincts verifies, porte technique verte |
| 6 | Angle mort `TYP-1` : `Element.text()` de jsoup normalise l'espace insecable en espace ordinaire, faisant rougir a tort le futur correctif typographique sur `TexteAccentueDesGabaritsTest`/`LaConsigneDUnJeuServiAtteintLEcranDeMancheTest` | Technique, garde-fou | `theo-archi` (route par `lea-dev`) | - | **CLOS** - comparaison neutralisant l'insecable des deux cotes, morsure prouvee (1/4 tests rouge la normalisation neutralisee) |
| 7 | `TYP-1` - le **moyen** (choix du caractere d'espace insecable et son application a `catalogue.yaml`) reste a choisir, confie conjointement a `theo-archi` et `sofia-sm` par l'arbitrage d'origine | Redaction, typographie | `theo-archi`, `sofia-sm` | - | **OUVERT** - `catalogue.yaml` porte, sur l'arbre fusionne de ce sprint, **17 espaces ordinaires devant `?`** et **4 devant `!`** dans le champ `consigne` (compte par point de code du caractere precedent, methode de `theo-archi`, hors `titre` ; 16/3 a l'ouverture du sprint, +1/+1 par les paquets neufs), zero insecable |
| 8 | Ecart de methode de comptage des tests (rapport texte vs XML, faux par defaut de 7 sur ce depot) | Qualite, mesure | `theo-archi` | - | **CLOS pour la mesure de ce sprint** - methode XML utilisee et documentee ci-dessus ; l'inscrire au README reste a la retro |
| 9 | Le recensement d'`ADR-0064` (derive de la forme de la boucle) trouve 13 bornes de tirage explicites sur deux modules, la ou le grep par nom de `theo-archi` a l'ouverture de s21 n'en voyait que 9 | Technique, methode | `theo-archi` | `ADR-0064` | **CLOS** - aucune des quatre boucles manquees n'etait partielle, omission sans consequence, mais nommee comme angle mort de methode (un grep par nom sous-denombre) |
| 10 | Compte de fiches citant le bloc `M1`-`M13` : 19 (balayage lexical de `nadia-prof`) contre 22 (recompte independant de `camille-po`, trois citations au format `M1 a M13` sans lister les numeros individuels echappaient au balayage lexical) | Produit, redaction | `camille-po` | `DEC-0025` amendee, `DEC-index.md` | **CLOS** - 22 fiches confirmees et corrigees, aucun Done anterieur au 2026-08-28 rouvert |
| 11 | Troisieme fois en deux jours (apres `COMP-4`, `COMP-6`, `COMP-7`) que `nadia-prof` trouve une de ses propres conditions vivant sur une reference que le produit avait deja abandonnee (le declencheur de reouverture de `M4`, `DEC-0025` point 4, citait "360 x 640", remplace par "744 x 551" depuis `DEC-0040`, six jours avant l'arbitrage du 2026-08-28) | Pedagogie, methode | `nadia-prof` | - | **CLOS par nadia-prof elle-meme** - declencheur declare sans objet, remplacant ecrit (arbitrage de l'utilisatrice ou garde-fou executable, jamais une mesure d'elle) |
| 12 | Obstacle 25 (s21), residuel - cinq scripts de `nadia-prof` executes a l'import, non testes par un juge pur | Qualite, CI | `theo-archi` | - | **OUVERT, reportee** (confirme par `avis-faisabilite.md` de ce sprint, non traitee) |
| 13 | Obstacle 21 (s21) - 4 items sans levier connu (`cm1-grands-nombres` x2, `cm1-reperes` x2) | Pedagogie, produit | `camille-po` | - | **OUVERT, hors perimetre de s22** |
| 14 | Regime de retrait immediat du catalogue sur un `REFUSE` (`DEC-0045` section 3) | Produit, gouvernance | `camille-po` | - | **OUVERT** - verse a une prochaine ceremonie, non tranche par ce document |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement - verifie pour
chacune des lignes ci-dessus (`ADR-0064`, `ADR-0063` amendee, `DEC-0025` amendee, `DEC-index.md`,
amendement `squad/README.md`).

## Porte pedagogique - nadia-prof

**Non tenue a cette review.** Les trois paquets neufs (`cm1-comprehension`, `cm1-multiplications-
divisions-10-100`, `cm1-problemes-durees`) sont fusionnes et techniquement verts, mais **aucun
Done pedagogique n'est prononce** : `nadia-prof` n'a pas encore audite l'increment fusionne, et
`squad/programme/MATRICE-couverture.md` n'a ete modifie par aucun lot de ce sprint - verifie
(aucune des trois lignes de matiere ne cite encore les trois jeux neufs). Le sprint n'est donc pas
clos ; ni `retro.md` ni `RAPPORT.md` ne sont ecrits par ce document.

### Audit rendu le 2026-08-29 par `nadia-prof` - TROIS REFUS, aucun Done pedagogique

*Ajout de `nadia-prof`, 2026-08-29, branche `s22-nadia-3`. Le paragraphe ci-dessus reste tel qu'il a
ete ecrit : il etait exact a sa date. Ce qui suit le complete, il ne l'efface pas.*

**Les trois paquets ont ete joues** - fat jar construit sur l'arbre de la branche, parties entieres,
erreurs volontaires jusqu'a la fin de partie. **Audit non degrade sur les trois.** Verdicts :

| Story | Paquet | Verdict | Motif | Cout de levee |
|---|---|---|---|---|
| `S13-2` | `cm1-multiplications-divisions-10-100` | **REFUSE** | Les **25 indices sur 25** - tout le texte lu apres une erreur - ne sont pas accentues (*ecrire*, *zero*, *a droite*). Amendement du 2026-08-04 a `DEC-0002` | **4 chaines** |
| `S13-3` | `cm1-problemes-durees` | **REFUSE** | **`D-6`** : les 48 distracteurs sont la bonne reponse deplacee d'un ecart constant (+15/+45, -15/+30, -45/-15), et l'en-tete declare deux familles d'erreurs qui ne les produisent pas. L'erreur de base 100 n'est proposee sur **aucun** des 24 items | **48 distracteurs** |
| `S17-2` | `cm1-comprehension` | **REFUSE** | **`COMP-2`, bloquante** : 3 items sur 20 se resolvent texte masque, dont un par culture generale pure (*"Que vend le vieux pecheur au marche ?"*). Ce sont exactement les 3 items non marques `COMP-3` | **3 questions, plus un mot** |

**Sur les vingt-huit conditions applicables, vingt-cinq sont tenues**, et les soixante-neuf calculs
des deux paquets de mathematiques sont justes sans une seule erreur. **Trois lots ne sont pas trois
echecs** : trois defauts localises dans trois lots par ailleurs solides, dont deux se levent pour
moins cher qu'ils n'ont coute a trouver.

**`MNC-1` n'apparait dans aucun des trois blocs, et ce n'est pas un oubli** : elle porte sur tout
Done `VALIDE` ou `VALIDE SOUS RESERVE`, et un `REFUSE` n'est pas un Done. Les trois blocs le disent
explicitement.

**Ce que la CI verte n'a pas vu.** Dix-neuf de mes vingt-six conditions sont desormais gardees
automatiquement (`yanis-qa`) - **et aucun de ces dix-neuf garde-fous ne cherche l'une des trois
proprietes qui ont fait tomber ces lots** : l'accentuation du texte d'un item n'est couverte par
aucun test du depot, qu'un distracteur soit une erreur reelle ne l'est pas davantage, et `COMP-2` se
verifie en masquant le texte et en essayant de repondre.

**Trois de mes propres conditions se sont revelees imprecises** au contact du contenu reel -
`COMP-3`, `D-4`, `D-5` : leur libelle ne mesure pas exactement ce que leur motif annonce. **Les trois
paquets les tiennent sous toutes les lectures, et je les declare tenues** ; elles seront reecrites
par moi, en conditions neuves et datees, jamais appliquees retroactivement.

**Motifs complets, tableaux condition par condition et criteres de levee** : dans les blocs de
validation des trois `squad/conformite/FICHE-*.md`, et rassembles dans
`squad/sprints/s22-couverture-maximale-du-catalogue/avis-nadia-done-pedagogique-s22.md`.

**Aucune ligne de `MATRICE-couverture.md` ne passe `couvert-valide`.** Les trois refus retournent a
`camille-po`, qui en fait du backlog ; ils ne bloquent pas le sprint suivant.

## Decisions prises

Aucune decision de perimetre, d'architecture ou de pedagogie n'est prise par ce document : les
resolutions de conflit qu'il rapporte sont additives et deja couvertes par les decisions citees
dans le tableau des obstacles (`ADR-0064`, `ADR-0063` amendee, `ADR-0050`, `DEC-0053`, `DEC-0025`
amendee). Ce que ce document tranche en propre, sous ma responsabilite de fusion : l'ordre des huit
fusions et la resolution manuelle des deux conflits additifs, verifiees ci-dessus.

## Fusion des corrections post-REFUSE (2026-08-30) - sofia-sm, casquette Scrum Master

*Ajout de sofia-sm, 2026-08-30. Les sections precedentes restent telles qu'elles ont ete ecrites :
exactes a leur date. Ce qui suit les complete, il ne les efface pas. Le sprint reste ouvert : la
porte pedagogique n'a pas ete repassee par ce document, aucun statut de `MATRICE-couverture.md`
n'est prononce ici et aucune fiche de conformite n'est modifiee par ce document.*

Les trois `REFUSE` du 2026-08-29 ont chacun recu une correction, livree sur la tete de la branche
d'origine plutot que sur une branche neuve. Six lots restaient a fusionner dans `round-two`. Trois
partent de `HEAD` de la review precedente (`62037c7`) - `yanis`, `sofia-4`, `nadia-3`. Les trois
autres reprennent chacun la tete de son propre lot de contenu du sprint, tres en arriere de
`62037c7` - `04aa35f` pour `lea`, `7e259a4` pour `marc`, `8da64f5` pour `sofia-2` : verifie individuellement par
`git merge-base --is-ancestor round-two <branche>` (faux pour les six) et
`git merge-base --is-ancestor <branche> round-two` (faux pour les six) avant toute fusion, puis
`git merge-base --is-ancestor <branche> HEAD` (vrai pour les six) apres. Aucun des six lots ne
touche `catalogue.yaml`, donc aucun des trois conflits rencontres a la fusion precedente ne se
reproduit ici.

| Ordre | Branche | Tete | Contenu | Fusion | Conflit |
|---|---|---|---|---|---|
| 1 | `s22-lea` | `be63ce3` | `S13-2` corrigee - accentuation des 25 indices, `MD-6` declaree | `83f3898` | aucun |
| 2 | `s22-marc` | `1c4a9c8` | `S17-2` corrigee - trois items `COMP-2` reecrits en `COMP-3`, item du lion en "principalement", commentaire de feuille de style, plus un accent releve apres coup | `89d323b` | aucun |
| 3 | `s22-sofia-2` | `0158b4c` | `S13-3` corrigee - 48 distracteurs recalcules par item (`ERR-INVERSE`, `ERR-REPORT`, `ERR-BASE100`), en-tete corrige (Iris et Nour, pas un garcon), `D-7` remesure | `fb2f3e8` | aucun |
| 4 | `s22-yanis` | `e29a640` | Rapport qualite de s22 | `5f58131` | aucun |
| 5 | `s22-sofia-4` | `22c452b` | Obstacle de gouvernance sur l'accentuation route, journal hors sprint (troisieme cas de discretion `DEC-0045` section 3) | `db6f4ff` | aucun |
| 6 | `s22-nadia-3` | `904ea4d` | Les trois blocs `REFUSE`, l'avis de Done pedagogique, l'ajout du 2026-08-29 a ce meme fichier | `a76c653` | aucun |

**Ordre choisi et son motif** : les trois corrections de contenu d'abord, dans l'ordre du tableau
des lots d'origine (`lea`, `marc`, `sofia-2` avait ete l'ordre inverse a la premiere fusion ; ici
l'ordre ne conditionne aucune resolution puisqu'aucun conflit n'apparait), puis `yanis` et
`sofia-4` qui n'ajoutent que des fichiers neufs sans rapport entre eux, puis `nadia-3` en dernier
parce que c'est la seule branche qui modifie `review.md` - la fusionner en dernier isole le risque
de conflit sur mon propre fichier au dernier pas, ou il est le plus facile a inspecter.

### Verification que les trois corrections sont dans l'arbre fusionne

Pas seulement que la fusion a reussi - relu directement dans les fichiers apres fusion :

- `cm1-multiplications-divisions-10-100.yaml` : les 25 `indice:` accentues (*ecrire*, *zero*,
  *a droite*) ; en-tete de fichier declarant `MD-6` (erreur reelle + plafond du tiers).
- `cm1-comprehension.yaml` : item du lion en "principalement" (ligne 65) ; les trois items
  auparavant non marques (cahier rouge, pecheur, bougies) portent desormais `# COMP-3` avec les
  deux informations qu'ils relient.
- `cm1-problemes-durees.yaml` : `ERR-BASE100` present dans l'en-tete des trois familles nommees ;
  la recette est portee par Iris et Nour, plus par un garcon.

### Porte technique remesuree sur l'arbre fusionne, methode XML

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet (racine, aucun `-pl`), sur `HEAD` (`a76c653`), le 2026-08-30. **BUILD SUCCESS**,
les six modules en `SUCCESS`.

Compte de tests, methode XML (somme des attributs `tests`/`failures`/`errors`/`skipped` de
l'element `<testsuite>` de tous les `TEST-*.xml` de `target/surefire-reports/` et
`target/failsafe-reports/` sur le reacteur complet, pas le rapport texte) : **761 tests, 0 echec,
0 erreur, 0 ignore.**

**Aucun ecart avec le dernier chiffre fusionne connu (761, section precedente)**, et c'est attendu :
aucune des trois corrections n'ajoute, ne retire ni ne modifie un fichier de test - `lea`, `sofia-2`
et `marc` (son second commit excepte, un accent dans un commentaire YAML) ne touchent que du
contenu YAML ; `marc` touche en plus `recreo.css` (six lignes, un commentaire) ; aucun des six lots
fusionnes ici ne touche un fichier `.java` (verifie par `git diff --name-only 62037c7 HEAD -- '*.java'`,
vide). Les regles ArchUnit (30, confirmees a la fusion precedente) sont donc inchangees par
construction, pour la meme raison.

**Les comptes annonces par les auteurs sur leurs propres worktrees - 732 (`lea-dev`), 736
(`marc-dev`), 731 (moi-meme, casquette dev, sur `sofia-2`) - ne sont pas des ecarts avec 761 : ce
sont trois totaux de worktree**, chacun mesurant sa propre branche (baseline a `04aa35f`/`7e259a4`/
`8da64f5`, tres en arriere de `round-two`) plus son propre lot, jamais l'arbre fusionne. Aucun des
trois ne pretendait mesurer autre chose ; le seul chiffre qui engage la porte technique du sprint
est celui mesure ici, sur le reacteur complet apres fusion.

### Obstacles - mise a jour

| # | Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|---|
| 14 | Regime de retrait immediat du catalogue sur un `REFUSE` (`DEC-0045` section 3) | Produit, gouvernance | `camille-po` | - | **OUVERT, reste ouvert** - l'utilisatrice a arbitre le 2026-08-29 de ne pas retirer les trois paquets pendant que les corrections etaient en cours (troisieme cas de discretion d'execution sur cette regle, apres `cm1-reperes` s20 et `cp-mot-manquant` s21 : `JOURNAL-gouvernance-hors-sprint.md`, entree du 2026-08-29). Les trois corrections sont desormais fusionnees, donc la question qui restait explicitement suspendue a leur issue - `camille-po` juge-t-elle que trois cas suffisent a instruire un amendement de `DEC-0045` section 3 - est murie et n'est toujours pas tranchee. Aucun `DEC` ne l'acte a ce jour |
| 15 | Deux textes du depot assignent la meme verification (l'accentuation du texte lu par l'enfant) a deux roles differents sans se citer l'un l'autre - `CLAUDE.md` nomme `relecteur-pedagogique`, la fiche de `nadia-prof` se nomme elle-meme. Revele par le `REFUSE` de `S13-2` (25 indices non accentues), route par `sofia-sm` (`obstacle-qui-verifie-l-accentuation-d-un-item.md`, `s22-sofia-4`) | Produit, perimetre d'un role, et technique, faisabilite d'une regle | `camille-po` (perimetre), `theo-archi` (faisabilite d'un garde-fou) | - | **OUVERT** - deux questions distinctes routees, aucune tranchee par ce document |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement. Les deux lignes
ci-dessus restent `OUVERT` precisement parce qu'aucun `DEC` ni `ADR` ne les tranche a ce jour -
seul un journal de gouvernance et un fichier de routage existent, et ni l'un ni l'autre n'est une
decision.

### Porte pedagogique

**Toujours non tenue.** Les trois corrections sont fusionnees et techniquement vertes ; `nadia-prof`
doit reauditer l'arbre fusionne avant qu'un Done pedagogique quelconque ne soit prononce. Aucune
ligne de `MATRICE-couverture.md` n'est touchee par ce document, aucune fiche de conformite ne l'est
davantage : les blocs de validation restent la seule main de `nadia-prof`.

### Decisions prises par ce document

Aucune. Ce document fusionne six lots, verifie leur contenu et remesure la porte technique ; il ne
tranche ni le perimetre de la verification d'accentuation, ni l'amendement eventuel de `DEC-0045`
section 3, ni le Done pedagogique.

---

## Porte pedagogique - reaudit du 2026-08-30 par `nadia-prof` : TROIS DONE PRONONCES

*Ajout de `nadia-prof`, 2026-08-30, branche `s22-nadia-4` ouverte sur `round-two` a `9bb7b8a`. Les
deux sections precedentes de ce fichier restent telles qu'elles ont ete ecrites : elles etaient
exactes a leur date. Ce qui suit les complete, il ne les efface pas. Aucun de mes trois `REFUSE` du
2026-08-29 n'a ete reecrit dans aucune fiche - un refus efface n'a aucune valeur de preuve.*

**Les trois lots de correction ont ete joues**, pas lus. Fat jar reconstruit sur l'arbre de ma
branche, application lancee, chaque item servi au moins une fois, les deux propositions fausses
pressees avant la bonne, puis une troisieme pression pour atteindre la revelation.
**69 items sur 69 joues** : 25/25 sur `S13-2` en neuf parties, 24/24 sur `S13-3` en neuf parties,
20/20 sur `S17-2` en cinq parties. **Audit non degrade sur les trois.**

| Story | Paquet | Verdict | Ce qui leve le refus du 2026-08-29 |
|---|---|---|---|
| `S13-2` | `cm1-multiplications-divisions-10-100` | **VALIDE** | Accentuation mesuree sur la **population entiere** (129 chaines, 29 jetons distincts), pas sur les 4 chaines que j'avais nommees. **Aucune reserve nouvelle** |
| `S13-3` | `cm1-problemes-durees` | **VALIDE SOUS RESERVE** | Les 48 distracteurs recalcules un par un depuis les nombres de leur item : `D-6` tenue sur sa substance. **Cinq reserves non bloquantes** |
| `S17-2` | `cm1-comprehension` | **VALIDE** | `COMP-2` refaite par ma procedure sur les **vingt** items, pas seulement les trois reecrits. **Deux reserves non bloquantes** |

**`camille-po` peut passer les trois lignes de `MATRICE-couverture.md` a `couvert-valide`.** Aucune
ligne de matrice n'est touchee par moi : ce n'est pas ma main.

**`MNC-1` mord pour la premiere fois.** Les trois blocs de validation la portent, dans son texte
integral du 2026-08-28. La clause n'est plus vide de cas.

**Les trois declarations d'auteurs ont ete verifiees et les trois sont exactes** : le balayage
d'accentuation de `lea-dev`, le compte `D-7` de `sofia-sm` (25,0 / 50,0 / 25,0, recompte a la main
sur les valeurs converties en minutes), l'integrite de `COMP-1` declaree par `marc-dev` (remesuree
mecaniquement, zero recouvrement sur 20 items et 60 propositions). Je ne les ai pas crues ; je les
ai refaites.

**Ce que le contenu corrige a mis en defaut, et ce n'est pas ce que je cherchais.**

- **Ma reserve du 2026-08-29 est en tension avec ma propre condition `D-6`.** J'ai exige une valeur
  de base 100 par forme ; sur la forme "duree", cette erreur produit mecaniquement plus de
  59 minutes, ce que `D-6` n'admet qu'a la condition de ne pas etre la seule anomalie du paquet -
  et elle l'est. **Le defaut est le mien.** Il ne bloque pas : cette clause ne figure pas dans ma
  liste de ce que je refusais d'avance (arbitrage du 2026-08-19, section 3.5).
- **`TYP-1`, mesure au navigateur reel a 744 x 551 avec mon instrument** : **18 signes orphelins
  dans le catalogue contre 14 le 2026-08-28, et les 4 nouveaux sont tous dans `cm1-problemes-
  durees`**, seul des trois paquets a ne pas porter d'espace insecable. Ne bloque pas - `TYP-1`
  porte ma phrase "aucun lot n'est un prealable au Done pedagogique d'aucun paquet". **Je clarifie
  en condition neuve et datee : a compter du 2026-08-30, tout paquet neuf applique `TYP-1`**, sans
  effet retroactif sur aucun paquet existant.
- **Une regularite creee par la correction que j'ai exigee** : sur les 12 items d'horaire d'arrivee,
  la bonne reponse n'est **jamais** la plus petite des trois. `D-7` se mesure sur le paquet, ou elle
  est tenue ; **c'est un constat neuf et date, pas un durcissement de `D-7`.**

**Obstacle 15 - a qui appartient l'accentuation du texte d'un item.** Il m'a ete demande de dire si
je maintiens que cette porte est la mienne : **je la maintiens**, sur trois appuis ecrits dans mon
avis (section 11), dont le principal est que l'exigence d'accentuation a ete ajoutee par l'amendement
du 2026-08-04 **a `DEC-0002`**, c'est-a-dire a la clause qui est deja la mienne, et non aux
conventions de redaction du depot. **Cela ne change rien au verdict du 2026-08-29** : le defaut
etait reel quel qu'en soit le juge. **Et je ne demande pas a rester seule dessus** - contrairement a
`COMP-2`, la propriete se teste sur un lexique, sans jugement ; un garde-fou executable aurait evite
ce refus et ce lot. L'obstacle reste `OUVERT` chez `camille-po` : ma position n'est pas une
decision.

**Ma capacite pour s23**, demandee : **quatre a cinq paquets par sprint**, contre trois a s22, sous
trois conditions nommees dans mon avis (section 12.3), dont une qui engage le planning -
**au plus un paquet par sprint portant une propriete du type de `COMP-2`**, qu'aucun automate ne
peut voir et qui se verifie item par item. **Ce que je ne ferai pas pour tenir un chiffre :
echantillonner.** Deux de mes trois refus du 2026-08-29 seraient passes au travers d'un echantillon.

**Motifs complets, tableaux condition par condition, methodes de mesure avec leurs chiffres** : dans
les blocs de validation des trois `squad/conformite/FICHE-*.md`, et rassembles dans l'amendement du
2026-08-30 a `squad/sprints/s22-couverture-maximale-du-catalogue/avis-nadia-done-pedagogique-s22.md`.

---

## Fusion des trois derniers lots et cloture du sprint (2026-08-30) - sofia-sm, casquette Scrum Master

*Ajout de `sofia-sm`, 2026-08-30. Les sections precedentes restent telles qu'elles ont ete ecrites :
exactes a leur date. Ce qui suit les complete, il ne les efface pas.*

Trois branches restaient a fusionner : `s22-nadia-4` (4 commits - les trois Done pedagogique, les
blocs de validation, le reaudit), `s22-camille-7` (5 commits - matrice a 21/43, backlog, `DEC-0055`,
`DEC-0056`, note a `DEC-0045`), `s22-relecteur` (1 commit - un compte perime corrige dans
`cm1-comprehension.yaml` et son garde-fou de test).

**Lignee verifiee avant fusion**, pas supposee : `git merge-base --is-ancestor round-two <branche>`
vrai pour les trois (les trois sont ouvertes sur `round-two` a `9bb7b8a`, tete de la fusion
precedente). **Fichiers touches verifies avant fusion, pas crus sur parole** :

- `s22-camille-7` ne touche aucun fichier de `squad/sprints/s22-...` : `docs/backlog/backlog.md`,
  trois `DEC` (`DEC-0045` amendee, `DEC-0055` creee, `DEC-0056` creee), `DEC-index.md`,
  `squad/programme/MATRICE-couverture.md`. Confirme.
- `s22-relecteur` ne touche que deux fichiers : `cm1-comprehension.yaml` (commentaire d'en-tete,
  "17 items sur 20" corrige en "les vingt items") et `PaquetCm1ComprehensionTest.java` (meme
  correction dans le commentaire du test). Confirme.
- `s22-nadia-4` touche les trois `FICHE-*.md` de conformite, `avis-nadia-done-pedagogique-s22.md`
  et ce fichier (`review.md`, addendum date ci-dessus). Confirme.

Fusion dans l'ordre nadia, camille, relecteur - aucun des trois lots ne touche un fichier modifie
par un autre, aucun conflit sur les trois fusions.

### Porte technique, reacteur complet, methode XML

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet (racine, aucun `-pl`), sur `HEAD` apres les trois fusions. **BUILD SUCCESS**, les
six modules en `SUCCESS`. Compte de tests, methode XML (somme des attributs
`tests`/`failures`/`errors`/`skipped` de l'element `<testsuite>` de chaque `TEST-*.xml` de
`target/surefire-reports/` et `target/failsafe-reports/`, sur les 116 fichiers de rapport produits
par ce build) : **761 tests, 0 echec, 0 erreur, 0 ignore.**

**Aucun ecart avec le dernier chiffre fusionne (761, section precedente), et c'est attendu** :
aucune des trois branches ne touche un fichier `.java` autre que le commentaire de
`PaquetCm1ComprehensionTest.java` (`s22-relecteur`), qui ne change ni le nombre ni le corps
d'aucune methode `@Test`. Les regles ArchUnit (30, confirmees a la fusion precedente) restent donc
inchangees par construction, pour la meme raison.

### Couverture, verifiee ligne par ligne sur la matrice fusionnee, pas recopiee de `camille-po`

Comptage direct des 47 lignes de competence de `squad/programme/MATRICE-couverture.md` (les quatre
tableaux de matiere), par statut : **20 `non couvert`, 2 `en cours`, 21 `couvert-valide`, 4
`non couvrable en l'etat`.** Denominateur atteignable (47 moins les 4 `non couvrable en l'etat`) :
**43**. **21/43 = 48,8 %.** Meme comptage sur `HEAD` de la fusion precedente (`9bb7b8a`, avant
`s22-camille-7`) : 18 `couvert-valide`, 4 `en cours`, 21 `non couvert`, 4 `non couvrable en l'etat` -
**18/43 = 41,9 %.** Le mouvement **18/43 (41,9 %) -> 21/43 (48,8 %)** annonce par `camille-po` est
confirme, chiffre par chiffre, sur l'arbre fusionne.

Catalogue de contenu (fichiers YAML sous `adapters/adapter-content/src/main/resources/contenu/`) :
**24**, inchange par ces trois fusions (deja a 24 avant elles, la croissance de 21 a 24 vient des
trois paquets neufs fusionnes a l'etape precedente du sprint). Confirme par comptage direct de
l'arbre, pas recopie.

### Deux chartes d'agent corrigees - `DEC-0056` routee, executee

`DEC-0056` tranche que la porte de l'accentuation du texte lu par l'enfant reste celle de
`relecteur-pedagogique` (`DEC-0002`, amendement du 2026-08-04), et route sa correction a
`sofia-sm` sans trancher elle-meme le texte des deux fiches. Corrige, hors casquette Scrum Master
(casquette developpeuse, meme regle de non-arbitrage sur mon propre obstacle) :

- `.claude/agents/relecteur-pedagogique.md` : la phrase conditionnelle ("si tu reperes une faute
  d'orthographe evidente [...], signale-la a `nadia-prof`") remplacee par un controle systematique,
  a chaque relecture de livrable, de l'orthographe, l'accentuation et la ponctuation du texte
  destine a l'enfant - `nadia-prof` garde seule le vocabulaire, le ton, le fond et le niveau
  scolaire.
- `.claude/agents/nadia-prof.md` : la phrase "tu corriges [...] ce que l'enfant lit a l'ecran",
  assez large pour laisser croire qu'elle couvrait l'orthographe, precisee a son perimetre reel -
  vocabulaire, ton, fond, niveau scolaire - avec renvoi explicite vers `DEC-0002` et `DEC-0056`
  pour l'orthographe, l'accentuation et la ponctuation.

Commit `[SOF] s22: aligne relecteur-pedagogique et nadia-prof sur DEC-0056 [...]`. Aucun autre
passage des deux fiches ne contredit `DEC-0002` ou `DEC-0056` (verifie par recherche des occurrences
d'"accentu" et "orthograph" dans les deux fichiers apres correction).

### Obstacles - mise a jour finale

| # | Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|---|
| 14 | Regime de retrait immediat du catalogue sur un `REFUSE` (`DEC-0045` section 3) | Produit, gouvernance | `camille-po` | `DEC-0055` | **CLOS** - troisieme cas instruit comme annonce, `DEC-0045` section 3 reste le regime par defaut, une exception a trois conditions cumulatives et une borne de fermeture obligatoire lui est ajoutee |
| 15 | A qui appartient la verification de l'accentuation du texte lu par l'enfant, entre `CLAUDE.md` (`relecteur-pedagogique`) et la fiche de `nadia-prof` (plus large) | Produit, perimetre d'un role ; technique, faisabilite d'une regle | `camille-po` (perimetre), `theo-archi` (faisabilite) | `DEC-0056` | **Perimetre CLOS** - la porte reste celle du `relecteur-pedagogique`, sans arbitrage neuf, deux fiches d'agent corrigees. **Faisabilite d'un garde-fou executable toujours OUVERTE chez `theo-archi`**, non traitee par ce document |

Les treize autres lignes du tableau (obstacles 1 a 13) restent dans l'etat ou la review precedente
les a laissees : dix `CLOS` (1 a 6, 8 a 11), trois `OUVERT` - obstacle 7 (`TYP-1`, "le moyen"),
obstacle 12 (l'obstacle 25 residuel), obstacle 13 (l'obstacle 21). **Aucun obstacle non ferme par
ce document n'est declare clos ici.**

### Porte pedagogique - confirmee

Les trois Done pedagogique de `nadia-prof` (`S13-2` **VALIDE**, `S13-3` **VALIDE SOUS RESERVE**,
`S17-2` **VALIDE**) sont ecrits dans les trois `squad/conformite/FICHE-*.md` fusionnees et repris
dans `squad/programme/MATRICE-couverture.md` par `camille-po`. Les sept reserves non bloquantes
(cinq sur `S13-3`, deux sur `S17-2`) sont versees en stories dans `docs/backlog/backlog.md`.

### Ce que ce document tranche, ce qu'il ne tranche pas

Tranche : l'ordre et la resolution des trois dernieres fusions, la porte technique remesuree, le
chiffre de couverture verifie, la correction des deux fiches d'agent routee par `DEC-0056`.
Ne tranche pas : la faisabilite d'un garde-fou d'accentuation (`theo-archi`), les sept reserves non
bloquantes (backlog, `camille-po`), `TYP-1` et l'obstacle 25 residuel (`theo-archi`), l'obstacle 21
(`camille-po`) - aucun n'est ferme ici, tous restent ouverts.

**Le sprint est clos par ce document.** `retro.md` et `RAPPORT.md` suivent.

---

## Horodatage de fin reel, pour `outils/rapport-tokens.py`

*Ajout de `sofia-sm`, 2026-08-30. Le champ d'en-tete de ce fichier ("Horodatage de fin :
2026-08-29 20:32") reste tel qu'il a ete ecrit : il datait la premiere tentative de cloture, avant
que le cycle `REFUSE` -> correction -> reaudit ne rouvre la porte pedagogique pour deux jours de
plus. Ce sprint n'a reellement fini qu'ici.*

- **Horodatage de fin** : 2026-08-30 10:40

`outils/rapport-tokens.py` lit desormais le **dernier** horodatage d'une etiquette dans un fichier
de ceremonie, pas le premier (corrige a cette cloture, `outils/rapport-tokens-horodatage-test.py`) :
sans ce correctif, la fenetre de mesure des tokens du sprint serait restee figee au 2026-08-29
20:32 et aurait silencieusement exclu deux journees de travail reel - les trois Done pedagogique,
`DEC-0055`, `DEC-0056`, les trois dernieres fusions, la correction des deux fiches d'agent et cette
retro. C'est le meme mode de defaillance que le fil rouge de ce sprint et du precedent : un chiffre
s'affiche sans que la fenetre qui le produit ait ete regardee.
