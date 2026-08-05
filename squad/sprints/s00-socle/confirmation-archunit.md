# [Sprint s00] Confirmation ArchUnit avant review - theo-archi

- **Date** : 2026-08-04
- **Auteur** : theo-archi
- **Objet** : `squad/README.md` section 9, etape 4. Statut ArchUnit confirme par la commande et son
  resultat, pas sur parole.

## Commande et resultat

```
JAVA_HOME=<JDK 21> mvn -B verify
```

**BUILD SUCCESS**, code de sortie 0, `Total time: 38.192 s`, executee le 2026-08-04 apres tous les
ajouts decrits ci-dessous.

| Module | Tests | Echecs |
|---|---|---|
| `recreo-domain` | 26 | 0 |
| `recreo-application` | 6 | 0 |
| `recreo-adapter-web` | 10 | 0 |
| `recreo-adapter-content` | 4 | 0 |
| `recreo-bootstrap` (unitaires, Surefire) | 44 | 0 |
| `recreo-bootstrap` (integration, Failsafe) | 2 | 0 |
| **Total** | **92** | **0** |

Le `JAVA_HOME` explicite n'est pas decoratif : le `java` du `PATH` de ce poste est un JRE 1.8.0_202,
et le JDK 21 est installe ailleurs sur le poste. Sans lui, le build ne compile pas.

`yanis-qa` mesurait 80 tests. Les 12 tests de plus sont ceux de ce passage : 11 tests de
non-vacuite dans `LesReglesMordentTest` et 1 dans `AssertionsWebTest`. Aucun test existant n'a ete
supprime, aucune regle n'a ete affaiblie pour obtenir le vert - une regle a ete **durcie** parce
qu'elle ne mordait pas (voir plus bas).

Second garde-fou, hors Maven, ajoute par ADR-0009 :

```
bash .github/scripts/verifier-prefixes-test.sh
```

14 cas, tous verts, code de sortie 0. La CI l'execute dans le job `prefixes`, avant l'etape de
verification elle-meme.

## Les regles du depot, et qui les a ecrites

Le depot compte **21 regles ArchUnit** (`static final ArchRule`), toutes ecrites au sprint s00, et
toutes des regles de garde : aucune n'est purement descriptive. Le compte se relit dans le journal
de la commande, ou le moteur ArchUnit rapporte ses regles separement des tests JUnit : 4
(`AbstractionsCiteesTest`) + 2 (`ArchitectureEnCouchesTest`) + 1 (`ChargementDuContenuTest`) + 7
(`DecouplageMecaniqueContenuTest`) + 3 (`DifficulteTest`) + 1 (`LesReglesMordentTest`) + 2
(`PureteDesCouchesTest`) + 1 (`AssertionsWebTest`, module `adapter-web`).

- **theo-archi, commit `ef3fda6`** : les 21 regles, avec 5 contre-exemples.
- **yanis-qa, commit `cd8a808`** : aucune regle nouvelle. Deux contre-exemples et deux tests de
  non-vacuite pour les deux regles d'ADR-0001, plus trois gardes `isNotEmpty()` contre la vacuite
  de type 1 dans `FrontieresDuJavaScriptTest`, plus `JarRecreoIT` et deux tests de comportement.
- **theo-archi, ce passage** : aucune regle nouvelle non plus. Une regle **reecrite** parce qu'elle
  ne mordait pas (`DifficulteTest.leContratDeContenuNePorteQueDuTexte`, ADR-0006), 13 fichiers de
  contre-exemple, 12 tests de non-vacuite, et un `ImportOption` sur `AssertionsWebTest` pour rendre
  sa regle contre-exemplifiable sans en changer le texte.

Ce qui a change ce sprint n'est donc pas le nombre de regles, c'est le nombre de regles dont on sait
qu'elles mordent : **5 sur 21 au premier commit, 7 apres `yanis-qa`, 21 aujourd'hui.**

## Application d'ADR-0003, regle par regle

C'est ce tableau qui rendra visible au sprint prochain toute regle qui n'aurait jamais ete mise a
l'epreuve. Les 21 regles y sont, sans exception. Tous les tests de non-vacuite cites sans module
sont dans `bootstrap/src/test/java/com/recreo/archi/LesReglesMordentTest.java`.

| # | Regle | ADR | Contre-exemple | Test de non-vacuite | Ajoute |
|---|---|---|---|---|---|
| 1 | `ArchitectureEnCouchesTest.lesDependancesVontVersLInterieur` | 0001 | `domain/contreexemple/DomaineDependantDeLApplicationContreExemple` | `leDomaineNeDoitPasDependreDeLApplication` | yanis-qa |
| 2 | `ArchitectureEnCouchesTest.lesAdaptateursNeSeConnaissentPas` | 0001 | `adapter/web/contreexemple/AdaptateurWebDependantDuContenuContreExemple` | `deuxAdaptateursNeDoiventPasSeConnaitre` | yanis-qa |
| 3 | `PureteDesCouchesTest.leDomaineNeDependQueDuJdk` | 0002 | `domain/contreexemple/DomainePollueContreExemple` | `laPureteDuDomaineEchoueSurUnDomaineQuiUtiliseSpring` | theo (ef3fda6) |
| 4 | `PureteDesCouchesTest.lApplicationNeDependQueDuJdkEtDuDomaine` | 0002 | `application/contreexemple/ApplicationPollueeContreExemple` | `lApplicationNeDoitPasDependreDUnFramework` | theo (ce passage) |
| 5 | `LesReglesMordentTest.aucunContreExempleEnProduction` | 0003 | `domain/contreexemple/DomainePollueContreExemple` (reutilise) | `unContreExempleEnProductionEstRefuse` | theo (ce passage) |
| 6 | `DecouplageMecaniqueContenuTest.leContratDeContenuEstFerme` | 0004 (1) | `domain/content/MoteurDeContenuPartageContreExemple` | `unMoteurDeContenuPartageEstRefuse` | theo (ce passage) |
| 7 | `DecouplageMecaniqueContenuTest.leContratDeMecaniqueEstFerme` | 0004 (2) | `domain/mechanic/MoteurPartageContreExemple` | `leContratDeMecaniqueRefuseUnMoteurPartage` | theo (ef3fda6) |
| 8 | `DecouplageMecaniqueContenuTest.uneMecaniqueNeVoitQueLesContrats` | 0004 (3) | `domain/mechanics/contreexemple/MecaniqueCoupleeContreExemple` | `uneMecaniqueQuiUtiliseUnGenerateurConcretEstRefusee` | theo (ef3fda6) |
| 9 | `DecouplageMecaniqueContenuTest.uneMecaniqueIgnoreLaMatiere` | 0004 (4) | `domain/mechanics/contreexemple/MecaniqueCoupleeContreExemple` | `uneMecaniqueQuiLitLaMatiereEstRefusee` | theo (ef3fda6) |
| 10 | `DecouplageMecaniqueContenuTest.leContenuIgnoreLesMecaniques` | 0004 (5) | `domain/content/contreexemple/ContenuQuiSaitJouerContreExemple` | `unContenuQuiConnaitSaMecaniqueEstRefuse` | theo (ef3fda6) |
| 11 | `DecouplageMecaniqueContenuTest.uneMecaniqueVitDansSonProprePaquet` | 0004 (6) | `domain/mechanics/MecaniqueHorsPaquetContreExemple` | `uneMecaniqueHorsDeSonPaquetEstRefusee` | theo (ce passage) |
| 12 | `DecouplageMecaniqueContenuTest.lesMecaniquesNeSeConnaissentPas` | 0004 (7) | `domain/mechanics/premiere/MecaniquePremiereContreExemple` et `domain/mechanics/seconde/MecaniqueSecondeContreExemple` | `deuxMecaniquesNeDoiventPasSeConnaitre` | theo (ce passage) |
| 13 | `AbstractionsCiteesTest.aucunTypeNommeRuleSet` | 0005 | `domain/content/contreexemple/RuleSet` | `unTypeNommeRuleSetEstRefuse` | theo (ce passage) |
| 14 | `AbstractionsCiteesTest.contentSourceExiste` | 0005 | dispense 1 : jeu de classes sans le type annonce | `uneAbstractionCiteeQuiDisparaitEstRefusee` | theo (ce passage) |
| 15 | `AbstractionsCiteesTest.gameMechanicExiste` | 0005 | dispense 1 : jeu de classes sans le type annonce | `uneAbstractionCiteeQuiDisparaitEstRefusee` | theo (ce passage) |
| 16 | `AbstractionsCiteesTest.itemExiste` | 0005 | dispense 1 : jeu de classes sans le type annonce | `uneAbstractionCiteeQuiDisparaitEstRefusee` | theo (ce passage) |
| 17 | `DifficulteTest.aucunTypeDifficultyDansLeDomaine` | 0006 | `domain/content/contreexemple/DifficultyConfigContreExemple` | `unTypeDeDifficulteDansLeDomaineEstRefuse` | theo (ce passage) |
| 18 | `DifficulteTest.aucunTypeDifficulteDansLeDomaine` | 0006 | `domain/content/contreexemple/DifficulteConfigContreExemple` | `unTypeDeDifficulteDansLeDomaineEstRefuse` | theo (ce passage) |
| 19 | `DifficulteTest.leContratDeContenuNePorteQueDuTexte` **(regle reecrite)** | 0006 | `domain/content/ReglageArithmetiqueContreExemple` (primitif) et `domain/content/ReglageEnCollectionContreExemple` (argument generique) | `uneBorneArithmetiqueDansLeContratDeContenuEstRefusee` et `uneBorneArithmetiqueCacheeDansUneCollectionEstRefusee` | theo (ce passage) |
| 20 | `ChargementDuContenuTest.seulLAdaptateurDeContenuLitDesFichiers` | 0007 | `bootstrap/contreexemple/ChargeurSauvageContreExemple` | `unChargeurHorsDeLAdaptateurDeContenuEstRefuse` | theo (ce passage) |
| 21 | `AssertionsWebTest.aucuneAssertionParExpressionReguliere` | 0008 | `adapter-web` : `adapter/web/contreexemple/AssertionRegexContreExemple` (dispense 2) | `AssertionsWebTest.uneAssertionParExpressionReguliereEstRefusee` | theo (ce passage) |

Les contre-exemples des lignes 1 a 20 vivent dans `bootstrap/src/test/java/com/recreo/...`. Celui de
la ligne 21 est le seul hors de `bootstrap`, pour la raison motivee dans l'amendement d'ADR-0003
(sa regle juge le code de test de son propre module).

## Les tests d'architecture qui ne sont pas des regles ArchUnit

Ils n'ont pas de contre-exemple, et l'amendement d'ADR-0003 dit pourquoi : ce sont des assertions
directes, leur seul mode de vacuite est l'ensemble vide, et il est garde explicitement. Ils sont
listes ici pour que personne ne les croie oublies.

| Test | ADR | Garde de vacuite |
|---|---|---|
| `AbstractionsCiteesTest.itemEstScelle` | 0004 | assertion directe sur `Item.class.isSealed()` |
| `ChargementDuContenuTest.ilNYAQuUnSeulChargeurDeContenu` | 0007 | `containsExactly(...)`, rouge si la liste est vide |
| `AucunVocabulaireDeMatiereTest.aucuneMecaniqueNeNommeUneMatiere` | 0004 | `isDirectory()` sur le paquet, `isNotEmpty()` sur les sources |
| `ToutesLesMecaniquesSontTesteesTest.chaqueMecaniqueAUnTestDeComportement` | 0008 | `isNotEmpty()` sur la liste des mecaniques |
| `FrontieresDuJavaScriptTest`, 3 tests | 0008 | `isNotEmpty()` avant chaque boucle (ajoute par `yanis-qa`) |

## Comment la non-vacuite a ete verifiee, et pas seulement affirmee

Un test de non-vacuite peut lui aussi mentir : si le contre-exemple n'est pas la ou le test le
cherche, `check` echoue avec "failed to check any classes", qui est le meme `AssertionError`.
Methode appliquee, celle de l'amendement du 2026-08-04 d'ADR-0003 :

1. chaque `hasMessageContaining` porte sur le **nom pleinement qualifie** de la classe fautive, et
   sur la dependance interdite quand deux regles visent la meme classe. Le nom qualifie ne peut pas
   apparaitre dans la description d'une regle, contrairement au nom simple - c'est pour cela que le
   test de `RuleSet` asserte `com.recreo.domain.content.contreexemple.RuleSet` et non `RuleSet`, mot
   que la description de la regle contient deja ;
2. mutation du montage : les deux fabriques d'import de `LesReglesMordentTest` ont ete pointees sur
   un paquet inexistant (`...absent`), et `AbstractionsCiteesTest.contentSourceExiste` passee a
   `allowEmptyShould(true)`. Resultat : **17 des 18 tests de `LesReglesMordentTest` deviennent
   rouges**, plus celui d'`AssertionsWebTest`. Le seul qui reste vert est
   `leDomaineNeDoitPasDependreDeLApplication`, qui n'utilise pas ces fabriques mais l'import complet
   (obligatoire : `layeredArchitecture()` echoue sur une couche declaree vide). Les fichiers ont ete
   restaures a l'identique apres mesure ;
3. pour le garde-fou de prefixes, meme methode transposee : cinq mutations du script juge
   (ancre `^` retiree, `grep -Eqi`, compteur jamais incremente, tag inconnu ajoute, ligne vide plus
   ignoree) rendent chacune le script de test rouge. Detail dans ADR-0009.

## Ce que ce passage a decouvert

**La regle centrale d'ADR-0006 ne mordait pas.** `leContratDeContenuNePorteQueDuTexte` etait
presentee dans son ADR comme "celle qui mord vraiment" ; son premier contre-exemple l'a fait passer
au vert, ce qui est le signal exact que la regle est fausse. Deux erreurs cumulees :

1. le paquet du primitif `int`, tel qu'ArchUnit 1.5 le rend pour le type brut d'un champ, est
   `java.lang` et non le paquet vide que l'ADR supposait. Comme `java.lang` etait dans la liste
   blanche, **tous les primitifs passaient** : `DifficultyConfig(int minOperand, int maxOperand,
   ...)` aurait ete accepte sans un mot. Mesure par sonde, pas deduit ;
2. le type brut de `Set<Operation> operations` est `java.util.Set`, egalement dans la liste blanche.
   `haveRawType` ne regarde pas les arguments generiques.

La regle interdisait donc un champ `java.time.LocalDate` et laissait passer les deux seules formes
que la dette n°2 de `main` a reellement prises. Elle est reecrite en `ArchCondition` qui inspecte
tous les types cites par la declaration, arguments generiques compris, et n'admet de `java.lang` que
`String`. ADR-0006 est amende, erreur nommee. Aucune relecture n'avait vu le defaut ; le
contre-exemple l'a montre en une execution.

Six autres regles sans contre-exemple, que le rapport qualite ne cite pas, ont ete trouvees en
faisant l'inventaire : la purete de `application` (ADR-0002), les regles (1), (6) et (7) d'ADR-0004
- dont l'isolation entre mecaniques, celle qui a menti 25 sprints sur `main` -, la regle de
suffixe d'ADR-0003 elle-meme, et la regle d'assertion web d'ADR-0008. Toutes mordent desormais, et
aucune n'etait fausse.

## ADR amendes ou ecrits pendant ce passage

| ADR | Nature |
|---|---|
| ADR-0001 | amende : contre-exemples de `yanis-qa` inscrits, piege de la couche declaree vide |
| ADR-0002 | amende : contre-exemple de la purete de `application`, nuance sur les primitifs |
| ADR-0003 | amende : perimetre de l'exigence, deux dispenses motivees, deux exceptions d'ecriture |
| ADR-0004 | amende : trois de ses sept regles n'avaient pas de contre-exemple |
| ADR-0005 | amende : contre-exemples des quatre regles, dont les trois regles d'existence |
| ADR-0006 | amende : sa regle centrale ne mordait pas, reecrite |
| ADR-0007 | amende : contre-exemple du dixieme chargeur |
| ADR-0008 | amende : contre-exemple de la regle qui juge du code de test |
| ADR-0009 | **nouveau** : les garde-fous de CI sont des scripts versionnes, testes par contre-exemples |

## Ce que ce vert ne prouve pas

- Il ne prouve rien de pedagogique. Le Done pedagogique est prononce par `nadia-prof` seule, et ce
  document n'y touche pas.
- Il ne prouve pas que la plage de commits comparee par la CI est la bonne : ADR-0009 nomme cette
  limite.
- Il ne prouve pas qu'une regle **utile** manque. Les 21 regles mordent ; savoir s'il en faut une
  vingt-deuxieme est une question a reposer a chaque ADR, et la reponse par defaut reste non.
