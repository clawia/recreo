# [Sprint s23] Review

- **Horodatage de debut** (`planning.md`) : 2026-08-30 14:15
- **Horodatage de fin** : 2026-08-30 16:48
- **Duree effective** : environ 2 h 33

## Increment livre

Trois paquets neufs entrent au catalogue - `cp-ranger-nombres` (ranger des nombres jusqu'a 100,
CP, 24 items, `S23-RAN`), `cm1-problemes-etapes` (problemes a deux etapes, quatre operations, CM1,
24 items, `S23-ETA`) et `cm1-mots-de-liaison` (mots de liaison simples, CM1, 20 items, `S23-LIA`) -
le catalogue passe de 24 a 27 jeux. Un defaut de lecture du garde-fou d'egalite de valeurs sur les
propositions non numeriques est corrige (`ADR-0068`). **Aucun Done pedagogique n'est prononce par
ce document** : `nadia-prof` n'a pas audite les trois paquets en jeu, et la couverture reste a
21/43 (48,8 %, `DEC-0057`), inchangee.

## Lots fusionnes, lignee verifiee par `git merge-base --is-ancestor` (pas par le nom de branche)

Ordre de fusion, sur `round-two` a `b1d8d9f` : `s23-yanis`, `s23-lea`, `s23-sofia-dev`, puis
`s23-marc` en dernier.

`s23-marc` contient deja `s23-theo-2` et `s23-nadia-2` : verifie avant toute fusion par
`git merge-base --is-ancestor s23-theo-2 s23-marc` et `git merge-base --is-ancestor s23-nadia-2
s23-marc`, les deux vrais (`marc-dev` les avait integres dans son propre arbre par des commits
d'integration explicites, `dccb138` notamment). Les deux branches ne sont donc **pas** fusionnees
separement : les compter en plus de `s23-marc` les aurait comptees deux fois, l'avertissement de
cette fusion et la lecon retenue depuis s22 sur les lots de `nadia-prof`.

Les cinq branches sont toutes des descendantes directes de `round-two` a `b1d8d9f` (verifie par
`git merge-base round-two <branche>` pour chacune, identique a `b1d8d9f` dans les six cas) : aucune
n'a divergé sur une base perimee.

Motif de l'ordre : `s23-yanis` et `s23-lea` ne touchent aucun des trois fichiers en conflit connu
et sont fusionnees en premier pour reduire le nombre de branches encore a fusionner sur un arbre en
mouvement. `s23-sofia-dev` (mon propre lot de dev, casquette developpeuse) entre ensuite, seule a
toucher `catalogue.yaml` et `TexteAccentueExposeParLePortEntrantTest` avant `s23-marc`. `s23-marc`
ferme la fusion : c'est la branche qui porte le plus de contenu (`S23-RAN`, `S23-ETA`, plus
`ADR-0068` et les amendements de `nadia-prof` deja integres), et la seule a toucher les trois
fichiers a la fois.

## Les trois conflits, resolus et verifies

**`catalogue.yaml`** : ajout pur, aucun chevauchement de contenu. `s23-sofia-dev` ajoutait une
entree (`cm1-mots-de-liaison`) en queue de fichier, `s23-marc` en ajoutait deux (`cp-ranger-nombres`,
`cm1-problemes-etapes`) au meme endroit. Resolu en conservant les trois, dans cet ordre. Verifie
apres resolution : **27 entrees** (`grep -c "^  - id:" catalogue.yaml`), et correspondance
bidirectionnelle complete avec les 27 fichiers de `adapters/adapter-content/src/main/resources/
contenu/` (aucune entree sans fichier, aucun fichier sans entree, verifie par script).

**`TexteAccentueExposeParLePortEntrantTest`** : meme nature, quatre points d'insertion (identifiants,
titres, consignes, competences) touches par les deux branches, sans chevauchement de contenu mais
avec un couplage syntaxique sur la derniere entree de chaque liste Java (virgule finale vs `);`).
Resolu en concatenant les deux jeux de chaines a chaque point d'insertion, avec recomposition
manuelle de la ponctuation Java sur la liste des competences (dernier element). Verifie : le
fichier compile et le test passe dans la porte technique ci-dessous.

**`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`** : ce n'etait pas un ajout pur, contrairement
aux deux precedents. `theo-archi` (`s23-theo-2`, deja integre dans `s23-marc`) modifie le corps de
`lireValeur` et ajoute la methode prive `unChiffreEstRestePourCompte` (`ADR-0068`) ; `s23-marc`
ajoute une entree a la map `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` (`cp-ranger-nombres`) ; `s23-sofia-
dev` ajoute une entree distincte a la meme map (`cm1-mots-de-liaison`), a un autre point de la map.
Git a fusionne les trois automatiquement (aucun marqueur de conflit sur ce fichier) parce que les
zones touchees ne se chevauchent pas dans le texte ; verifie explicitement apres fusion plutot que
suppose : les deux entrees neuves sont presentes (`grep -n "cp-ranger-nombres\|cm1-mots-de-liaison"`
renvoie les deux lignes), la modification de `lireValeur` et `unChiffreEstRestePourCompte` sont
presentes en integralite, et le test passe.

## Porte technique, mesuree apres fusion complete, methode XML

`bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet : **BUILD SUCCESS**, six modules
(`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`,
`recreo-bootstrap`).

**Tests : 800**, methode XML (somme de l'attribut `tests` de tous les `TEST-*.xml` de
`target/surefire-reports/` et `target/failsafe-reports/` du reacteur fusionne, 122 fichiers), **0
echec, 0 erreur**.

Chiffres annonces pendant le sprint, sur des arbres differents avant fusion : 772 (base
`b1d8d9f`), 772 (`s23-yanis`), 772 (`s23-lea`), 782 (`s23-sofia-dev`), 773 (`s23-theo-2`), 790
(`s23-marc`, qui contient deja `s23-theo-2` et `s23-nadia-2`). Aucun de ces chiffres n'est celui du
sprint - chacun mesure la base plus son propre lot, jamais l'union. Le total fusionne s'explique
integralement, sans residu :

| Delta | Origine |
|---|---|
| 772 | base (`b1d8d9f`) |
| +0 | `s23-yanis` - corrige une assertion existante, n'ajoute et ne retire aucune methode `@Test` |
| +0 | `s23-lea` - modifie une donnee de contenu et des assertions existantes, aucune methode `@Test` neuve |
| +10 | `s23-sofia-dev` - `PaquetCm1MotsDeLiaisonTest` (classe neuve) |
| +18 | `s23-marc` (delta total de la branche, `s23-theo-2` et `s23-nadia-2` inclus) : `PaquetCpRangerNombresTest` (10 methodes) + `PaquetCm1ProblemesEtapesTest` (7 methodes) + une methode neuve sur `LaValeurDUnePropositionEstDeriveeDeCeQuElleEcritTest` (`s23-theo-2`, `ADR-0068`, verifiee a 1 par comptage `@Test` sur le diff `b1d8d9f`..`s23-theo-2`) ; `s23-nadia-2` ne contribue aucune methode, ce sont des amendements de conditions en Markdown |
| **800** | **total, verifie egal a 800 - 772 mesure directement sur l'arbre fusionne** |

`domain` et `application` sont inchanges par ce sprint : aucun des cinq lots ne touche leurs
sources de test.

**Regles ArchUnit (`ArchRule` declarees) : 30, inchange depuis s22.** Methode : derivation du
depot lui-meme, `LesGardeFousSontMisALEpreuveTest.toutesLesRegles(racine)` invoquee directement
(jamais un `grep`), sur l'arbre fusionne, le 2026-08-30. **0 regle sans mise a l'epreuve**
(`reglesSansMiseALEpreuve`, meme derivation). Aucun des cinq lots ne touche `ArchRule` ou son
outillage de recensement.

**Aucun fichier de confirmation ArchUnit signe par `theo-archi` n'existe sur le disque pour ce
sprint** : la mesure ci-dessus est celle de `sofia-sm` a la fusion, pas une confirmation de
`theo-archi`. Ce qu'il a produit ce sprint est `ADR-0068` (le correctif de `lireValeur`, deja
integre) et l'avis de faisabilite section 6 ; aucun des deux n'est un rapport de confirmation
ArchUnit au sens du gabarit de ceremonie.

## Rapport qualite - yanis-qa

Aucun fichier de rapport qualite sur le disque pour ce sprint. Ce qui existe : le commit
`5d13ee3` (`s23-yanis`), qui documente sa methode et sa mesure directement dans le message - population
etablie par recherche du motif `.text()` (parentheses vides) sur tous les `src/test/java` du
reacteur (`-am`), **66 occurrences sur 17 fichiers, dont 63 de code reel** (3 en Javadoc/commentaire).
**Sur ces 63, une seule mentait** : `LExplicationDUnItemReelAtteintLEcranDeRetourTest`, qui compare
une explication rendue "telle quelle" via `Element.text()` de jsoup, lequel normalise l'espace
insecable (U+00A0) en espace ordinaire (jsoup 1.23.1) - le meme defaut et le meme correctif que
`LeTexteSupportDUnItemReelAtteintLEcranDeMancheTest` (`ADR-0050`). Correctif : `wholeText().strip()`
a la place de `text()`. Morsure prouvee sur materiau reel (`cp-le-bon-son.yaml`, item DENT), avant
et apres correctif, plus une regression simulee separement. **Le compte qui comptait n'etait pas
celui des 63 occurrences mais celui des proprietes qu'elles mentent effectivement : 1.**

## Corrections - relecteur-pedagogique

Aucun lot de `relecteur-pedagogique` sur ce sprint : ni branche, ni commit, ni fichier de
correction sur le disque. Non traite, faute de matiere.

## Obstacles du sprint

| # | Description | Categorie | Agent route | Decision liee | Statut |
|---|---|---|---|---|---|
| 1 | `ETA-4` et `ETA-7` s'excluaient mathematiquement : sous les deux seules familles de `ETA-4`, le resultat intermediaire est structurellement la valeur du milieu, donc la bonne reponse ne peut jamais l'etre (enumeration sur 54 218 items realisables : 27 109 petite, 0 milieu, 27 109 grande) | Pedagogie, specification de contenu | `nadia-prof` | Amendement dans `squad/conformite/ARBITRAGE-PREALABLE-s23-...md` (`s23-nadia-2`) | **CLOS** - `ETA-4b` remplace `ETA-4` (troisieme famille de distracteurs, inversion de l'operation de la premiere etape), `ETA-7` est tenue, implemente par `marc-dev` (`S23-ETA`, 24 items a 8/8/8) |
| 2 | `RAN-5` ne bloquait pas le raccourci que son propre motif nommait : satisfaite quand les deux distracteurs partagent leur premier nombre, cas ou le raccourci reste gagnant a 100 % (21 780 triplets sur 41 580, 52,4 %) | Pedagogie et technique, specification de contenu | `nadia-prof` et `theo-archi`, convergence independante (enumeration sur la population pour l'une, mesure sur le brouillon reel pour l'autre) | Amendement `ARBITRAGE-PREALABLE-s23-...md` (`s23-nadia-2`) et `ADR-0068` (`s23-theo-2`) | **CLOS** - `RAN-5b` remplace `RAN-5`, plus stricte, portee sur les premiers nombres des trois propositions ; implemente par `marc-dev` (`S23-RAN`) |
| 3 | Le garde-fou d'egalite de valeurs (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `lireValeur`) rendait un verdict qui n'etait pas celui de sa propriete : sur une liste ordonnee ("32, 33, 40"), il ne lisait que le premier nombre et annoncait a tort que deux propositions valaient la meme chose - une liste n'a pas de valeur scalaire, et sa propre Javadoc nommait deja cette hypothese, restee implicite | Technique, garde-fou | `theo-archi` | `ADR-0068` | **CLOS** - `lireValeur` rend desormais `null` des que le reste de sa lecture porte encore un chiffre ; morsure par mutation sur le corpus reel (1 032 mutants, 1 032 survivants sans la regle, 0 avec) |
| 4 | `RAN-4` famille (b) indefinie quand deux nombres partagent leur chiffre des unites (43 050 triplets sur 166 650), tranchee par un detail d'outil non nomme dans la condition d'origine | Pedagogie, specification de contenu | `nadia-prof`, route a `camille-po` pour la story | Amendement `ARBITRAGE-PREALABLE-s23-...md` (`s23-nadia-2`) | **CLOS pour ce sprint** - `RAN-4b` ajoute la clause d'exclusion, population corrigee a 81 180 retenus (et non 108 135) ; le chiffre corrige reste a ne pas recopier tel quel dans une story future, releve pour `camille-po` |
| 5 | Sur 63 appels reels a `.text()` de jsoup dans les sources de test, un seul mentait une propriete que la normalisation de l'espace insecable empeche de voir | Qualite, garde-fou de test | `yanis-qa` | - | **CLOS** - `LExplicationDUnItemReelAtteintLEcranDeRetourTest` corrige (`wholeText().strip()`), morsure prouvee sur materiau reel |
| 6 | Conflit a trois fichiers a la fusion (`catalogue.yaml`, `TexteAccentueExposeParLePortEntrantTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`) entre `s23-sofia-dev` et `s23-marc` (ce dernier portant deja `s23-theo-2`) | Technique, fusion | `sofia-sm` | - | **CLOS** - resolution additive sur les deux premiers, resolution verifiee non triviale sur le troisieme (modification de corps de methode + deux ajouts de map disjoints), porte technique verte, 27 entrees de catalogue verifiees bidirectionnelles |
| 7 | Deux agents sur quatre ont commence leur lot dans le depot principal au lieu d'ouvrir un worktree dedie avant leur premiere ecriture, alors que la regle est ecrite dans `squad/README.md` section 9 depuis s22 (et deja constatee en s06, s18, s21 sous des formes voisines) - deuxieme occurrence en trois jours, sans qu'aucun garde-fou executable ne la fasse rougir | Process, gouvernance | `sofia-sm` (pour reaffectation de pratique), route a la retrospective | - | **OUVERT** - reporte a `retro.md`, la regle relue ne suffit pas seule a empecher la recurrence ; aucun garde-fou n'existe encore pour la faire rougir automatiquement |
| 8 | `nadia-prof` signale que les trois premieres etapes de sa nouvelle procedure de specification (enumeration de la population, verification de satisfaisabilite, mesure du taux de reussite d'un raccourci nomme) sont du calcul et non du jugement pedagogique, et propose qu'elles soient outillees plutot que tenues dans ses seules mains - signalement, non priorise, non demande par elle | Qualite, outillage | `yanis-qa`, `theo-archi` (deja nommes par elle dans `ARBITRAGE-PREALABLE-s23-...md`, section 12.3) | - | **OUVERT** - signale, non priorise ; a verser au backlog par `camille-po` si retenu |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement - verifie pour
chacune des lignes ci-dessus (`ADR-0068`, amendements dans `ARBITRAGE-PREALABLE-s23-huit-candidats-
six-de-contenu-seul-et-deux-de-niveau-2.md`).

## Cliquet `TYP-1`

`lea-dev` (`s23-lea`, reserve B) fait descendre `DETTE_TOTALE` de 540 a 516 occurrences dans
`AucunSigneDoubleNEstDetachableDeSonMotTest` : `cm1-problemes-durees` passe de 27 a 3 (les 3
restantes sont des ":" dans des indices, hors perimetre de la reserve), seule baisse du sprint.
Verifie par sommation directe des quatorze entrees de `DETTE_PAR_FICHIER` sur l'arbre fusionne :
179+44+31+27+27+26+25+24+24+21+21+18+14+6+3+3+23 = **516**, egal a `DETTE_TOTALE`. Les trois
paquets neufs (`cp-ranger-nombres`, `cm1-problemes-etapes`, `cm1-mots-de-liaison`) sont absents de
la table et y entrent donc a zero occurrence par construction (« un paquet absent de la table doit
valoir zero »), confirme par `marc-dev` et `sofia-dev` dans leurs commits respectifs et par la
porte technique verte. Le cliquet atterrit a **516** sur l'arbre fusionne.

## Porte pedagogique - nadia-prof

Non prononcee pour les trois paquets neufs de ce sprint. `nadia-prof` n'a pas joue l'increment
livre ; son activite de ce sprint (`s23-nadia-2`) porte sur l'amendement de conditions de
specification en amont du developpement, pas sur un audit de Done. La couverture reste a **21/43
(48,8 %)**, inchangee.

## Ce que ce document ne fait pas

Ni retro, ni rapports (`RAPPORT.md`, `RAPPORT-tokens.md`) : ils appartiennent a la cloture du
sprint, qui n'a pas eu lieu ici. Aucune fiche de conformite ni la matrice de couverture ne sont
touchees par ce document.
