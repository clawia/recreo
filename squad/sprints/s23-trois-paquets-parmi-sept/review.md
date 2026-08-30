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
| 8 | `nadia-prof` signale que les trois premieres etapes de sa nouvelle procedure de specification (enumeration de la population, verification de satisfaisabilite, mesure du taux de reussite d'un raccourci nomme) sont du calcul et non du jugement pedagogique, et propose qu'elles soient outillees plutot que tenues dans ses seules mains - signalement, non priorise, non demande par elle | Qualite, outillage | `yanis-qa`, `theo-archi` (deja nommes par elle dans `ARBITRAGE-PREALABLE-s23-...md`, section 12.3) | `DEC-0058` | **CLOS** - `camille-po` verse la story au backlog de s24, sans capacite prise sur s23 ; faisabilite et forme technique restent a `theo-archi` et `yanis-qa` |
| 9 | `nadia-prof` refuse `RAN-7` (`cp-ranger-nombres`) et `LIA-3` (`cm1-mots-de-liaison`) ; les deux correctifs sont fusionnes (indice recalcule pour `RAN-7`, deux distracteurs remplaces par "car" pour `LIA-3`), mais aucun reaudit n'a encore ete joue sur les deux paquets corriges | Pedagogie, Done | `nadia-prof` | - | **OUVERT** - les deux lignes restent `en cours` (`cp-ranger-nombres`) et `non couvert` (`cm1-mots-de-liaison`) tant que le reaudit n'est pas joue ; le sprint reste ouvert pour ce motif |
| 10 | Trois agents sur cinq ont commence leur travail sur ce sprint dans le checkout principal au lieu d'un worktree dedie avant leur premiere ecriture - `marc-dev`, `sofia-sm` (casquette developpeuse) et `camille-po`, en plus des deux deja portes par l'obstacle 7 sur la premiere moitie de ce meme sprint. Les trois s'en sont apercus et ont rejoue proprement avant tout commit sur la branche visee | Process, gouvernance | `sofia-sm` (reaffectation de pratique), route a la retrospective | - | **OUVERT** - reporte a `retro.md` ; la regle ecrite dans `squad/README.md` section 9 depuis s22 ne suffit pas seule, aucun garde-fou ne la fait rougir, et le compte monte a chaque sprint |
| 11 | Ecart de consigne assume : j'avais demande a `marc-dev` de ne chercher aucun garde-fou pour `RAN-7` et de router la question a `yanis-qa` et `theo-archi` ; il en a ecrit un quand meme, une methode de test propre a `PaquetCpRangerNombresTest` qui encode la regle de validite de `nadia-prof` pour ce seul paquet. Le fond est defendable et le resultat est bon - ma consigne visait un garde-fou generique et etait trop large | Process, ma consigne (casquette Scrum Master) | moi-meme (constat), question de fond routee a `yanis-qa` et `theo-archi` | - | **OUVERT sur la question de fond, pas sur le test** - le test de `RAN-7` reste, defendable et verifie ; la question de `yanis-qa` et `theo-archi` reste entiere sur `LIA-3` et sur la plausibilite de la famille (c) d'`ETA-4b`, pas sur `RAN-7` |

Rappel : un obstacle n'est `clos` que si le fichier de decision existe physiquement - verifie pour
chacune des lignes ci-dessus (`ADR-0068`, `DEC-0058`, amendements dans
`ARBITRAGE-PREALABLE-s23-huit-candidats-six-de-contenu-seul-et-deux-de-niveau-2.md`).

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

**Le paragraphe ci-dessous decrivait l'etat du 2026-08-30 au moment de la review, avant l'audit. Il
est conserve tel quel et corrige par le bloc qui le suit, comme le veut la regle de ce depot : on
ajoute et on date, on n'efface pas.**

> Non prononcee pour les trois paquets neufs de ce sprint. `nadia-prof` n'a pas joue l'increment
> livre ; son activite de ce sprint (`s23-nadia-2`) porte sur l'amendement de conditions de
> specification en amont du developpement, pas sur un audit de Done. La couverture reste a **21/43
> (48,8 %)**, inchangee.

### Prononcee le 2026-08-30 (branche `s23-nadia-3`), apres avoir joue les quatre jeux

Avis complet et motifs : `squad/sprints/s23-trois-paquets-parmi-sept/avis-nadia-done-pedagogique-s23.md`.
Les blocs de validation sont dans les fiches de conformite de chaque paquet.

| Paquet | Verdict | Ligne de matrice |
|---|---|---|
| `cp-ranger-nombres` (`S23-RAN`) | **REFUSE** - `RAN-7`, cinq items sur 24 dont deux ou l'indice conduit sur un distracteur present a l'ecran | reste `en cours` |
| `cm1-problemes-etapes` (`S23-ETA`) | **VALIDE SOUS RESERVE** - `ETA-1` a `ETA-10` tenues, trois reserves non bloquantes | **peut passer `couvert-valide`**, sans mention partielle |
| `cm1-mots-de-liaison` (`S23-LIA`) | **REFUSE** - `LIA-3`, deux distracteurs sur 40 rendent le texte moins bon et non faux | reste `non couvert` |
| `cm1-problemes-durees` (reaudit) | **VALIDE SOUS RESERVE, maintenu** - quatre reserves levees sur cinq, reserve D non levee | reste `couvert-valide` |

**Un seul Done neuf sur trois paquets neufs.** Sous reserve de l'ecriture de la matrice par
`camille-po`, qui seule la tient, la couverture passerait de **21/43** a **22/43 (51,2 %)**. Je ne
l'ecris pas moi-meme.

**Les deux refus portent sur les deux seules conditions de leur paquet qu'aucun calcul ne juge**, et
la porte technique etait verte sur les deux. Toutes les conditions calculables des trois paquets
sont tenues, y compris les trois que j'avais amendees en cours de sprint (`ETA-4b`, `RAN-4b`,
`RAN-5b`), recomptees par moi sur les populations entieres.

**Le `relecteur-pedagogique` n'est pas passe sur ce sprint** : l'orthographe, l'accentuation et la
ponctuation de ces trois paquets ne sont pas jugees par ces verdicts (`DEC-0056`).


### Reaudit du 2026-08-30 (branche `s23-nadia-4`), apres les quatre lots de correction

**Ajoute par `nadia-prof`. Les deux blocs ci-dessus ne sont pas reecrits** : ils restent exacts a
leur date, et l'un decrit l'etat d'avant l'audit, l'autre les verdicts du premier passage. On
ajoute et on date.

Amendement complet et motifs :
`squad/sprints/s23-trois-paquets-parmi-sept/avis-nadia-done-pedagogique-s23.md`, section "Amendement
du 2026-08-30 - reaudit". Les blocs de validation sont en fin de chaque fiche de conformite.

**Joues, pas lus** : dix parties sur `RAN`, douze sur `LIA`, quatorze sur `ETA`, chaque proposition
fausse pressee avant la bonne pour voir l'indice. Les 24 items de `RAN`, les 20 de `LIA` et les 24
d'`ETA` rencontres a l'ecran. Aucun des trois audits n'est degrade.

| Paquet | Verdict du reaudit | Ce qui change | Ligne de matrice |
|---|---|---|---|
| `cp-ranger-nombres` (`S23-RAN`) | **VALIDE**, sans reserve | `RAN-7` levee. Les cinq indices reapparies sont les cinq items nommes ; ma condition tenue sur les 24 items | **peut passer `couvert-valide`** |
| `cm1-problemes-etapes` (`S23-ETA`) | **VALIDE SOUS RESERVE (maintenu)** | Reserves A, B et C levees ; reserves D et E ouvertes. Le Done du 2026-08-30 tient tel quel | reste `couvert-valide` |
| `cm1-mots-de-liaison` (`S23-LIA`) | **REFUSE** - second refus, motif nouveau | `LIA-3` levee sur les items 3 et 18 ; en defaut sur l'item 10, distracteur `mais`, que `sofia-sm` m'a route sans y toucher | reste `non couvert` |

**Sous reserve de l'ecriture de la matrice par `camille-po`, qui seule la tient, la couverture
passerait de 22/43 a 23/43 (53,5 %).** Je ne l'ecris pas moi-meme.

**Ce que ce reaudit ajoute au sprint, en trois points.**

- **Les deux correctifs font exactement ce que j'avais demande**, verifie ligne a ligne sur la
  difference des fichiers : cinq lignes `indice` echangees sur `RAN` sans qu'aucun des six textes
  soit reecrit, deux valeurs de distracteur sur `LIA` avec l'item 20 intact. **Les trois reserves
  d'`ETA` sont fermees par les criteres que j'avais ecrits**, y compris le passage a "perd", verbe
  que j'avais deja juge bon.
- **Les deux defauts que ce reaudit ajoute sont des corrections de mes propres comptes**, pas des
  defauts du travail livre. Mon compte du 2026-08-30 sur `LIA` disait 38 distracteurs bons sur 40 :
  il y en avait 37 - `sofia-sm` a verifie ce que j'avais declare bon et a trouve l'item 10. Ma
  reserve C sur `ETA` nommait deux des six items qui portent la meme ambiguite : `marc-dev` a
  corrige exactement ceux que j'avais nommes.
- **Une reserve nouvelle a ete creee par le correctif d'une autre**, et une CI entierement verte ne
  l'a pas vue : les enonces des items 22 a 24 d'`ETA` passent a "perd" et leurs indices gardent
  "distribues", "retirees", "vendus". Aucun test ne relie un indice a l'enonce de son item.
  **Quatrieme defaut de ce sprint invisible a la porte technique.**

**Sur le test de `RAN-7`, ecrit par `marc-dev` hors de la consigne de `sofia-sm` (obstacle 11).**
Il encode ma condition et il ne rend pas un verdict etranger a sa propriete : *enumeration de tous
les triplets admissibles par `RAN-2`, `RAN-4b` et `RAN-4` - **81 180 triplets** - croises avec les
six textes d'indice, soit **487 080 couples** juges deux fois, par le test et par ma condition :
**zero desaccord, dans les deux sens**.* **Je le garde.** Trois manques a traiter, ecrits dans la
fiche : pas de branche par defaut, couplage muet a `RAN-4` / `RAN-4b`, Javadoc au motif inexact.

**Ce que ces verdicts ne disent pas.** `MNC-1` n'est couvert par aucun des deux Done prononces
(dette assumee, `DEC-0053`). Le `relecteur-pedagogique` n'est pas passe sur ce sprint :
l'orthographe, l'accentuation et la ponctuation des trois paquets ne sont pas jugees par ces
verdicts (`DEC-0056`). Une date "2026-08-31" est ecrite dans `cm1-mots-de-liaison.yaml` pour un
commit du 2026-08-30, signalee a `sofia-sm` et au `relecteur-pedagogique`.

## Suite de review, 2026-08-30 - fusion des quatre lots de correction

Quatre branches fusionnees dans cette meme review, apres le bloc ci-dessus : `s23-nadia-3`,
`s23-marc-2`, `s23-sofia-dev-2`, `s23-camille-2`, dans cet ordre. Lignee verifiee par
`git merge-base --is-ancestor 86eea1d <branche>` pour les quatre : vraie dans les quatre cas,
aucune n'a divergé sur une base perimee. Les quatre fusions sont additives : aucun marqueur de
conflit sur aucun fichier (recherche des marqueurs `<<<<<<<`/`=======`/`>>>>>>>` sur l'arbre
fusionne, zero occurrence), et `git status` rend un arbre propre apres les quatre `merge --no-ff`.

### Increment complete par ces quatre lots

- `cp-ranger-nombres` (`RAN-7`) : l'indice est desormais choisi pour son item et non distribue par
  un cycle independant du contenu - corrige par `marc-dev`, verifie en jouant six parties completes
  (`verification-en-jeu-marc-corrections.md`). **Aucun reaudit de `nadia-prof` n'a encore ete
  joue** : la ligne de matrice reste `en cours`.
- `cm1-problemes-etapes` : les trois reserves non bloquantes A, B, C du `VALIDE SOUS RESERVE` sont
  fermees (item 17 ramene de 31 a 30 mots ; verbes des items 22 a 24 remplaces par "perd" ; enonce
  des deux ateliers de poterie et de couture corrige). Ces fermetures ne rouvrent pas le Done deja
  prononce : c'etaient des reserves explicitement non bloquantes.
- `cm1-mots-de-liaison` (`LIA-3`) : les deux distracteurs en defaut, items 3 et 18, sont remplaces
  par "car" (`sofia-dev`, casquette developpeuse). **Aucun reaudit de `nadia-prof` n'a encore ete
  joue** : la ligne de matrice reste `non couvert`.
- Matrice de couverture ecrite par `camille-po` : **22/43 (51,2 %)**, 22 lignes `couvert-valide`
  comptees directement dans `squad/programme/MATRICE-couverture.md` (une seule ligne bouge par
  rapport a la version precedente, `cm1-problemes-etapes`), egal au tableau recapitulatif que le
  document y ajoute.
- `DEC-0058` cree : l'outillage des trois etapes calculables de la procedure de specification de
  `nadia-prof` est verse au backlog de s24, sans capacite prise sur le perimetre deja engage de s23
  (obstacle 8 ci-dessus, desormais clos par cette decision).
- `DEC-0057` corrige par sa propre autrice : la mention "21 signes orphelins mesures" (section 3.4)
  n'avait aucune mesure du depot derriere elle. Detail plus bas.

### Porte technique, mesuree apres la fusion complete des quatre lots

`bash outils/mvn-jdk21.sh -B clean verify` sur le reacteur complet des six modules
(`recreo`, `recreo-domain`, `recreo-application`, `recreo-adapter-web`, `recreo-adapter-content`,
`recreo-bootstrap`) : **BUILD SUCCESS**.

**Tests : 801**, par sommation des lignes de synthese `Tests run:` de chaque module sur l'arbre
fusionne (surefire puis failsafe) : 49 (`domain`) + 13 (`application`) + 332 (`adapter-web`) + 287
(`adapter-content`) + 114 (`bootstrap`, surefire) + 6 (`bootstrap`, failsafe/IT) = **801**.
Conforme au chiffre annonce par `marc-dev` : 800 de base (le total mesure a la premiere moitie de
cette review) plus un test neuf, la mise a l'epreuve de `RAN-7` dans `PaquetCpRangerNombresTest`.

### Les trois corrections, verifiees sur l'arbre fusionne - pas relayees sur la parole de leur auteur

- `cp-ranger-nombres.yaml` : les indices sont reaffectes selon les deux contraintes decrites dans
  le commentaire du fichier (lignes 39 a 46) - verifie sur le fichier de l'arbre fusionne, pas
  suppose depuis le message de commit.
- `cm1-mots-de-liaison.yaml` : item 3 (`distracteurs: ["mais", "car"]`) et item 18
  (`distracteurs: ["car", "pourtant"]`) portent tous les deux "car" - verifie par comptage
  positionnel des vingt items du fichier fusionne.
- `cm1-problemes-etapes.yaml`, item 17 : l'enonce du timbre compte **30 mots** par comptage direct
  (methode de reference de `nadia-prof`, qui compte la ponctuation separee comme un jeton), contre
  31 avant correction.

### Quatre mesures fautives trouvees ce sprint, dont trois par leur propre auteur

De la meme famille : un chiffre annonce se reverifie, il ne se relaie pas.

1. **`nadia-prof`** : son instrument de mesure de signes orphelins
   (`squad/outils/nadia-prof/chercher-un-signe-orphelin.py`) enveloppe chaque jeton dans une balise
   et coupe a l'espace insecable comme a une espace ordinaire - faux positif sur tout texte qui en
   porte une. Chiffre reel du catalogue sur le balisage reellement servi : **14**, pas 17. Trouve
   et corrige par elle-meme, documente dans `squad/conformite/FICHE-bandit-manchot-problemes-durees-
   cm1.md`, section du reaudit.
2. **`camille-po`** : `DEC-0057`, son propre document, ecrivait "21 signes orphelins mesures" - un
   chiffre sans rapport avec aucune mesure du depot. Elle identifie elle-meme la confusion, avec le
   "21/43" de couverture ecrit dans le meme document, et corrige sur place.
3. **`marc-dev`** : son test de `ETA-3` retirait le point d'interrogation avant de compter les mots
   - une methode qui n'aurait jamais pu attraper la breche de l'item 17 (31 mots) que `nadia-prof`
   a trouvee en jouant, avec sa propre methode qui compte la ponctuation separee comme un mot.
   Corrige, la breche et la methode de comparaison sont commentees en toutes lettres dans le test.
4. **Trouve en amont, rappele pour la meme famille** : le garde-fou d'egalite de valeurs
   (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, `lireValeur`) rendait un verdict qui
   n'etait pas celui de sa propriete sur une liste ordonnee - deja clos par `ADR-0068` (obstacle 3
   ci-dessus).

**Point de methode de `camille-po`, porte tel quel** : le chiffre **18** deja commite dans
`round-two` (mesure du 2026-08-30, avant la correction de `lea-dev` sur `cm1-problemes-durees`)
**reste exact a sa date** et n'est pas reecrit - le paquet portait alors l'espace ordinaire, la
mesure etait juste. Le **14** est ecrit la ou il a un sens : le reaudit, sur le balisage
effectivement servi apres le correctif. Un depot qui reecrit ses anciens chiffres perd la trace de
ce qu'il savait quand.

## Ce que ce document ne fait pas

Ni retro, ni rapports (`RAPPORT.md`, `RAPPORT-tokens.md`) : ils appartiennent a la cloture du
sprint, qui n'a pas eu lieu ici. Aucune fiche de conformite ni la matrice de couverture ne sont
touchees par ce document. **Le sprint reste ouvert** : `cp-ranger-nombres` (`RAN-7`) et
`cm1-mots-de-liaison` (`LIA-3`) attendent un reaudit de `nadia-prof` avant de pouvoir passer
`couvert-valide`.
