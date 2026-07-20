# Plan — Découpler les tests du contenu externalisé

Statut : **planifié** (non exécuté). Demandé par l'utilisatrice le 2026-07-20. Proposé pour **s11**
(éventuellement groupé avec le renommage des configs).

## Constat (audit du 2026-07-20)
Depuis l'externalisation du contenu (quiz s09, mots s10), **éditer un fichier de contenu casse des
tests** — ce qui contredit le but de l'externalisation (éditer sans toucher au code). Déjà vécu :
ajouter des questions « système solaire » a cassé `sciences-cm1 = 12` (corrigé en 17).

**Aucun test ne dépend du _texte_ des questions** (bon). Le couplage porte sur des **nombres** :

| Test (méthode) | Couplage au contenu éditable |
|---|---|
| `QuizPackLoaderTest.poolSizesMatchTheVerifiedContent` | 7 tailles exactes (14/17/10/12/10/10/10) |
| `MotsPackLoaderTest.poolSizesMatchTheVerifiedContent` | 5 tailles exactes (12/10/12/16/50) |
| `S04GamesNavigationWebTest.finiteGameQuestionsAreCappedByTheirPool` | caps homophones=10, premiere-lettre=12, nature-mot=16, anglais=50 |
| `S08ContentNavigationWebTest.finiteGameQuestionsAreCappedByTheirPool` | caps le-bon-son=12, emc-cp/emc-cm1/vrai-faux=10 |
| `CatalogParametersTest.questionsTargetIsCappedByFinitePool` | homophones=10, anglais=50 |
| `QuizPackLoaderTest.loadsTheSevenQuizFiles` / `MotsPackLoaderTest.loadsTheFiveMotsFiles` | **liste exacte** des fichiers (casse si on **ajoute** un jeu) |

> Combinatoire = **non concerné** : `comparaison`, `complement-dix`, `tables-multiplication-cm1`
> ont un cap de 50 fixé par la logique (vivier infini), pas par du contenu éditable → à conserver.

## Principe cible
Les tests doivent valider le **schéma**, la **logique** et les **invariants** — jamais une valeur de
contenu éditable. Ajouter/retirer une question ou un mot ne doit **jamais** exiger de toucher un test.

## Refacto proposé

### A. Tester la logique de plafonnement en UNITAIRE (nouveau `SessionQuestionsTest`)
`SessionQuestions` (package `bootstrap.config`, package-private) porte toute la logique
(`maxFor = min(50, vivier)`, plancher 5, défaut 20, `resolve`). Un test unitaire **même package**,
avec des tailles de vivier **synthétiques** (3, 10, 20, 60, absent/vide/non numérique), couvre :
- cap = min(50, vivier) ; défaut = min(20, vivier) ; plancher = min(5, vivier) ;
- `resolve` borne la demande dans [min, max] ; défaut si vide/invalide.
→ Remplace **toute** la couverture « plafonnement » aujourd'hui faite via le contenu réel, sans dépendre d'aucun fichier.

### B. Supprimer les assertions de TAILLE liées au contenu éditable
- Supprimer `poolSizesMatchTheVerifiedContent` (Quiz + Mots).
- `finiteGameQuestionsAreCappedByTheirPool` (S04 + S08) : retirer les jeux **externalisés** ; ne
  garder (si utile) que les jeux **combinatoires** (cap 50 stable), ou supprimer la méthode puisque
  A couvre la logique.
- `CatalogParametersTest.questionsTargetIsCappedByFinitePool` : retirer les cas homophones/anglais ;
  conserver les cas sur `calcul-mental-cp` (combinatoire, lignes 132-139) — non éditable.

### C. Rendre non-brittle les tests de « présence » de fichiers
- `loadsTheSevenQuizFiles` / `loadsTheFiveMotsFiles` : remplacer `containsExactlyInAnyOrder(...)` par
  `contains(...)` sur les entryId **livrés connus** (vérifie qu'ils existent, tolère l'ajout de
  nouveaux fichiers). Ne plus figer le **nombre total** de fichiers.

### D. (Optionnel) Garantie d'intégration RELATIVE, sans littéral
Si l'on veut conserver un test bout-en-bout « un jeu fini plafonne à son vivier » : charger le pack,
puis `assertThat(cap).isEqualTo(min(50, pack.questions().size()))` — la valeur est **dérivée** du
contenu chargé, donc robuste à l'édition.

### E. À CONSERVER tel quel (déjà robustes — invariants, pas de contenu figé)
- `everyQuestionIsWellFormed` / `everyEntryIsWellFormed` (distracteurs distincts ≠ bonne, prompts
  uniques…), `modesAreParsed`, `vraiFauxHasExactlyTwoOptions`, tests de **rejet** de fichiers
  invalides, `MotChoixGeneratorTest`, `QuizGeneratorTest` (pools **en ligne**).

### Frontière (couplage secondaire, priorité basse — hors périmètre sauf demande)
- Métadonnées de catalogue en dur : `gameKeysArePreserved` (4 gameKeys) et les `containsString` de
  **titres/libellés** dans les tests de navigation (ex. « Sciences CM1 — Vrai ou Faux »). Ce sont de
  la config d'identité, pas du contenu de question ; elles ne cassent qu'en cas de **renommage** de
  jeu (rare). Recommandation légère : à terme, ancrer la navigation sur `href`/`entryId` (identité
  stable) plutôt que sur le libellé.

## Effort / risque
Petit à modéré, **test-only** (aucun code de prod touché). Risque faible. Un sprint suffit ; se
combine bien avec le renommage des configs (`PLAN-renommage-config.md`) en un **s11 « durcissement »**.

## Vérification
`mvn -B verify` vert après refacto ; puis **preuve** : ajouter une question à un `.yml` de contenu
et vérifier que **la CI reste verte sans modifier aucun test**.
