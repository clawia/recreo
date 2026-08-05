# [Sprint s01] Confirmation ArchUnit avant review - theo-archi

- **Date** : 2026-08-05
- **Auteur** : theo-archi
- **Objet** : ADR-0003, "l'inventaire complet est tenu a chaque fin de sprint dans
  `squad/sprints/<sprint>/confirmation-archunit.md`". L'avis de cloture, lui, est dans
  `avis-architecture-cloture.md` a cote de ce fichier.
- **Arbre juge** : `round-two`, commit `d534451` (fusion des trois lots paralleles par `sofia-sm`),
  plus les corrections du present passage.

## Commande et resultat

```
mvn -B clean verify
```

**BUILD SUCCESS**, code de sortie 0, `Total time: 44.300 s`, sur un JDK 21.0.10 - la version que la
CI utilise, et non une approximation.

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 31 | 0 |
| `recreo-application` | 7 | 0 |
| `recreo-adapter-web` | 26 | 0 |
| `recreo-adapter-content` | 5 | 0 |
| `recreo-bootstrap` (unitaires, Surefire) | 49 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 2 | 0 |
| **Total** | **120** | **0** |

Prerequis : `JAVA_HOME` sur un JDK 21, et non sur le JRE 8 que le `PATH` de ce poste expose par
defaut. Le bytecode produit est bien du 21 : `javap -verbose` rend `major version: 65` sur
`GameMechanic.class`, verifie et non suppose.

**Un echec transitoire, observe une fois, non reproduit, et signale plutot que tu.** Le premier
`clean verify` de ce passage a rendu `Tests run: 21, Failures: 2` sur `LesReglesMordentTest`, puis
un `BUILD SUCCESS` a l'execution suivante sans qu'une seule ligne ait change entre les deux.
Tentatives de reproduction : 3 `clean verify` complets et 5 executions ciblees de
`LesReglesMordentTest`, toutes vertes, 0 echec. Le rapport Surefire de l'execution fautive avait
deja ete ecrase quand je l'ai lu, donc **je ne sais pas lesquels des 21 cas ont echoue** : je ne
peux pas en nommer la cause, et je n'en invente pas.

Ce que je peux etablir : d'autres agents ecrivaient dans le meme arbre de travail pendant ce
passage. Sept fichiers hors de mon perimetre ont change sous moi, un `err.log` est apparu a la
racine, un commit d'un autre agent a emporte mes fichiers en cours d'indexation, et l'arbre a
ensuite ete remis a `d534451` par une reinitialisation dure qui a detruit tout le travail non
commite - le mien comme celui des autres. Une seconde construction Maven concurrente dans le meme
`target/` expliquerait sans peine un `ClassFileImporter` qui lit des classes a demi reecrites. C'est
une hypothese vraisemblable, pas une conclusion. Ce qui tranchera est la CI : elle construit un
checkout neuf, sans ecrivain concurrent. Si l'echec y reapparait, il faudra le traiter comme un
defaut reel de ces tests, pas comme un artefact de poste.

## Les garde-fous hors Maven

Les trois sont executes, et chacun est lui-meme teste avant d'etre applique (ADR-0009, ADR-0010).

| Garde-fou | Script juge | Script de test | Cas | Resultat |
|---|---|---|---|---|
| Prefixe de commit par agent | `.github/scripts/verifier-prefixes.sh` | `verifier-prefixes-test.sh` | 15 | tous verts, sortie 0 |
| Plage de commits atteignable | `.github/scripts/commit-atteignable.sh` | `commit-atteignable-test.sh` | 5 | tous verts, sortie 0 |
| Chemins de poste de travail | `.github/scripts/verifier-chemins.sh` | `verifier-chemins-test.sh` | 20 | tous verts, sortie 0 |

Les deux juges ont ete passes sur le materiau reel de ce sprint, et pas seulement sur leurs cas de
test :

- les commits `05647a1..HEAD` : "Tous les commits portent un prefixe d'agent connu", sortie 0. Le
  cas `[INES]`, neuvieme prefixe ouvert par `sofia-sm` au debut du sprint, a son cas dedie dans le
  script de test et il passe ;
- tous les fichiers suivis : "Aucun chemin de poste de travail ni nom de compte dans les fichiers
  verifies", sortie 0.

Le garde-fou de chemins a d'ailleurs servi pendant ce passage : il a refuse
`.claude/settings.local.json`, un fichier de configuration locale non suivi qui contient un chemin
de JDK en dur. Il ne doit pas etre commite, et il ne l'a pas ete.

## Le compte des regles

**23 regles ArchUnit** (`static final ArchRule`), toutes executees, toutes vertes. Le compte se
relit dans les rapports Surefire, ou chaque `@ArchTest` est un cas de test nomme : 22 dans
`bootstrap`, 1 dans `adapter-web`.

| Fichier | Regles | Dont ecrites en s01 |
|---|---|---|
| `AbstractionsCiteesTest` | 4 | 0 |
| `ArchitectureEnCouchesTest` | 2 | 0 |
| `ChargementDuContenuTest` | 1 | 0 |
| `DecouplageMecaniqueContenuTest` | 7 | 0 |
| `DifficulteTest` | 3 | 0 |
| `GestionDesErreursTest` | 2 | **2** (ADR-0011) |
| `LesReglesMordentTest` | 1 | 0 |
| `PureteDesCouchesTest` | 2 | 0 |
| `AssertionsWebTest` (`adapter-web`) | 1 | 0 |
| **Total** | **23** | **2** |

21 a la cloture de s00, 23 aujourd'hui. Aucune regle n'a ete supprimee ni affaiblie pendant le
sprint : le `git diff 05647a1..HEAD` sur les fichiers de regles ne contient que des ajouts
(`GestionDesErreursTest` cree, `LesReglesMordentTest` allonge). Verifie fichier par fichier, pas
deduit du vert.

## Application d'ADR-0003, regle par regle

Les 20 premieres lignes sont celles de `s00-socle/confirmation-archunit.md`, reverifiees sur l'arbre
fusionne. Les lignes 22 et 23 sont neuves. Sauf mention de module, tous les tests de non-vacuite
sont dans `bootstrap/src/test/java/com/recreo/archi/LesReglesMordentTest.java`.

| # | Regle | ADR | Contre-exemple | Test de non-vacuite |
|---|---|---|---|---|
| 1 | `ArchitectureEnCouchesTest.lesDependancesVontVersLInterieur` | 0001 | `domain/contreexemple/DomaineDependantDeLApplicationContreExemple` | `leDomaineNeDoitPasDependreDeLApplication` |
| 2 | `ArchitectureEnCouchesTest.lesAdaptateursNeSeConnaissentPas` | 0001 | `adapter/web/contreexemple/AdaptateurWebDependantDuContenuContreExemple` | `deuxAdaptateursNeDoiventPasSeConnaitre` |
| 3 | `PureteDesCouchesTest.leDomaineNeDependQueDuJdk` | 0002 | `domain/contreexemple/DomainePollueContreExemple` | `laPureteDuDomaineEchoueSurUnDomaineQuiUtiliseSpring` |
| 4 | `PureteDesCouchesTest.lApplicationNeDependQueDuJdkEtDuDomaine` | 0002 | `application/contreexemple/ApplicationPollueeContreExemple` | `lApplicationNeDoitPasDependreDUnFramework` |
| 5 | `LesReglesMordentTest.aucunContreExempleEnProduction` | 0003 | `domain/contreexemple/DomainePollueContreExemple` (reutilise) | `unContreExempleEnProductionEstRefuse` |
| 6 | `DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme` | 0004 (1) | `domain/content/MoteurDeContenuPartageContreExemple` | `unMoteurDeContenuPartageEstRefuse` |
| 7 | `DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme` | 0004 (2) | `domain/mechanic/MoteurPartageContreExemple` | `leContratDeMecaniqueRefuseUnMoteurPartage` |
| 8 | `DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats` | 0004 (3) | `domain/mechanics/contreexemple/MecaniqueCoupleeContreExemple` | `uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee` |
| 9 | `DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaMatiere` | 0004 (4) | `domain/mechanics/contreexemple/MecaniqueCoupleeContreExemple` | `uneMecaniqueQuiLitLaMatiereEstRefusee` |
| 10 | `DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques` | 0004 (5) | `domain/content/contreexemple/ContenuQuiSaitJouerContreExemple` | `unContenuQuiConnaitSaMecaniqueEstRefuse` |
| 11 | `DecouplageMecaniqueContenuTest.uneMecaniqueVitDansSonProprePaquet` | 0004 (6) | `domain/mechanics/MecaniqueHorsPaquetContreExemple` | `uneMecaniqueHorsDeSonPaquetEstRefusee` |
| 12 | `DecouplageMecaniqueContenuTest.lesMecaniquesNeSeConnaissentPas` | 0004 (7) | `domain/mechanics/premiere/...` et `domain/mechanics/seconde/...` | `deuxMecaniquesNeDoiventPasSeConnaitre` |
| 13 | `AbstractionsCiteesTest.aucunTypeNommeRuleSet` | 0005 | `domain/content/contreexemple/RuleSet` | `unTypeNommeRuleSetEstRefuse` |
| 14 | `AbstractionsCiteesTest.contentSourceExiste` | 0005 | dispense 1 : jeu de classes sans le type annonce | `uneAbstractionCiteeQuiDisparaitEstRefusee` |
| 15 | `AbstractionsCiteesTest.gameMechanicExiste` | 0005 | dispense 1 | `uneAbstractionCiteeQuiDisparaitEstRefusee` |
| 16 | `AbstractionsCiteesTest.itemExiste` | 0005 | dispense 1 | `uneAbstractionCiteeQuiDisparaitEstRefusee` |
| 17 | `DifficulteTest.aucunTypeDifficultyDansLeDomaine` | 0006 | `domain/content/contreexemple/DifficultyConfigContreExemple` | `unTypeDeDifficulteDansLeDomaineEstRefuse` |
| 18 | `DifficulteTest.aucunTypeDifficulteDansLeDomaine` | 0006 | `domain/content/contreexemple/DifficulteConfigContreExemple` | `unTypeDeDifficulteDansLeDomaineEstRefuse` |
| 19 | `DifficulteTest.leContratDeContenuNePorteQueDuTexte` | 0006, **0012** | `domain/content/ReglageArithmetiqueContreExemple`, `domain/content/ReglageEnCollectionContreExemple`, **`domain/content/ReglageDeLongueurDePartieContreExemple` (nouveau)** | `uneBorneArithmetiqueDansLeContratDeContenuEstRefusee`, `uneBorneArithmetiqueCacheeDansUneCollectionEstRefusee`, **`uneLongueurDePartieDansLeContratDeContenuEstRefusee` (nouveau)** |
| 20 | `ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers` | 0007 | `bootstrap/contreexemple/ChargeurSauvageContreExemple` | `unChargeurHorsDeLAdaptateurDeContenuEstRefuse` |
| 21 | `AssertionsWebTest.aucuneAssertionParExpressionReguliere` | 0008 | `adapter-web` : `adapter/web/contreexemple/AssertionRegexContreExemple` (dispense 2) | `AssertionsWebTest.uneAssertionParExpressionReguliereEstRefusee` |
| 22 | **`GestionDesErreursTest.uneAdviceDErreurExiste`** | **0011** | dispense 1 : jeu de classes sans `@ControllerAdvice` | `unePorteDErreurAbsenteEstRefusee` |
| 23 | **`GestionDesErreursTest.uneErreurNeRendPasUnCorpsBrut`** | **0011** | `adapter/web/contreexemple/GestionnaireErreurBruteContreExemple` | `unGestionnaireDErreurQuiRendUnCorpsBrutEstRefuse` |

**23 regles, 23 mises a l'epreuve.** Aucune ligne vide dans les deux dernieres colonnes.

## Comment la morsure a ete verifiee ce sprint, et pas seulement affirmee

Un test de non-vacuite ecrit dans le meme geste que sa regle peut se tromper de cible sans que rien
ne le signale : les deux passent, et le vert ressemble a une preuve. Trois mutations ont donc ete
appliquees sur un JDK 21, mesurees, puis annulees. Les fichiers sont restaures a l'identique
(`git diff` vide sur chacun, verifie apres restauration).

| Mutation | Attendu | Constate |
|---|---|---|
| `ReglageDeLongueurDePartieContreExemple` : `int nombreDeManches` devient `String nombreDeManches` | seule `uneLongueurDePartieDansLeContratDeContenuEstRefusee` devient rouge | 1 echec sur 21 dans `LesReglesMordentTest`, et c'est bien celui-la |
| `GestionnaireDesErreurs.partieIntrouvable` retourne `ResponseEntity<String>` au lieu du nom de vue | `uneErreurNeRendPasUnCorpsBrut` devient rouge | 1 echec sur 2 dans `GestionDesErreursTest`, message : "Rule 'ADR-0011 : un gestionnaire d'erreur rend une vue HTML, jamais un corps technique' was violated (1 times)" |
| `@ControllerAdvice` retire de `GestionnaireDesErreurs` | `uneAdviceDErreurExiste` devient rouge par `allowEmptyShould(false)` | 1 echec sur 2, message : "failed to check any classes" |

Les deux mutations d'ADR-0011 portent volontairement sur le **code de production**, pas sur le
contre-exemple : c'est la seule facon de montrer que ces deux regles refuseraient le vrai
contournement, et pas seulement une classe ecrite pour etre refusee.

## Les tests d'architecture qui ne sont pas des regles ArchUnit

Inchanges depuis s00, reverifies presents et verts. Ce sont des assertions directes, dispensees de
contre-exemple par l'amendement d'ADR-0003 ; leur seul mode de vacuite est l'ensemble vide, et il
est garde explicitement.

| Test | ADR | Garde de vacuite |
|---|---|---|
| `AbstractionsCiteesTest.itemEstScelle` | 0004 | assertion directe sur `Item.class.isSealed()` |
| `ChargementDuContenuTest.ilNYAQuUnSeulChargeurDeContenu` | 0007 | `containsExactly(...)` |
| `AucunVocabulaireDeMatiereTest.aucuneMecaniqueNeNommeUneMatiere` | 0004 | `isDirectory()` et `isNotEmpty()` |
| `ToutesLesMecaniquesSontTesteesTest.chaqueMecaniqueAUnTestDeComportement` | 0008 | `isNotEmpty()` |
| `FrontieresDuJavaScriptTest`, 3 tests | 0008 | `isNotEmpty()` avant chaque boucle |

Ces trois derniers tests couvrent sans modification les gabarits neufs du sprint
(`fragments/erreur.html`) et les gabarits remanies par le lot `ines-design` : ils balaient tout
`.html` sous `templates/`, la liste n'est pas ecrite en dur.

## Ce que ce vert ne prouve pas

- **Rien de pedagogique.** Le Done pedagogique appartient a `nadia-prof` seule. Aucune ligne de ce
  document, y compris l'assertion `isPositive()` ajoutee sur `nombreDeManches`, ne dit qu'une partie
  de dix manches est la bonne longueur - elle dit seulement qu'une partie de zero manche serait une
  erreur de forme.
- **Rien sur la stabilite de l'execution.** Voir l'echec transitoire non reproduit, plus haut.
- **Rien sur une regle utile qui manquerait.** Les 23 mordent ; savoir s'il en faut une
  vingt-quatrieme se repose a chaque ADR, et la reponse par defaut reste non.
