# Rapport qualite de cloture - s48

**Auteur** : `yanis-qa`. **Date** : 2026-10-03. **Arbre mesure** : `round-two` a `bc1e7835`
(hash complet `bc1e7835fc6dfb6db0e4f177fde742f39d344fe9`), mesure directement sur le checkout
principal (pas de worktree dedie : aucune mutation de materiau de production n'etait prevue dans
cette tache, seule une mesure). Base de sprint : `bfdc7fee0d60dd18657ebda59a9b9996f829045e`
(merge de cloture de s47).

Quatre fichiers portent des modifications non commitees au moment de la mesure
(`docs/backlog/backlog.md`, `squad/decisions/REGISTRE-identifiants.md`,
`squad/decisions/produit/DEC-index.md`, `squad/programme/MATRICE-couverture.md`) : tous en Markdown,
aucun fichier Java ni YAML de contenu. Ils n'entrent pas dans le perimetre de ce rapport, qui ne
porte que sur les sept commits de s48 (`bfdc7fee0..bc1e7835`), et n'affectent pas le resultat de
`mvn verify` ci-dessous.

Contenu du sprint : sept stories, toutes des corrections de contenu ou un lot neuf de contenu
statique - `MAT-CE1-08` (`[MARC]`, `d08d6bbe`, nouveau paquet `ce1-problemes-etapes`), `TAB-CE1-R1`
(`[LEA]`, `b29c900b`), `AUT-CP-R1` (`[LEA]`, `358b3d24`), `ANG-CP-R1`/`ANG-CP-R2` (`[LEA]`,
`f99a47a9`), `EMC-CP-14` (`[SOF]`, `4b6a6294`), `EMC-CM1-10` (`[SOF]`, `b77cabeb`), `SOP-CE1-R1`
(`[SOF]`, `bc1e7835`).

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, lance une seule fois sur cet arbre :
**BUILD SUCCESS**, 6/6 modules, 9 min 01 s. JDK constate par `outils/verifier-jdk.sh` : JDK de
compilation 21.0.10 (toolchain), java du PATH 21.0.12.1 - deux versions 21 distinctes, conforme a
la toolchain declaree.

---

## 1. Compte de tests autoritaire

**1954 tests : 1948 unitaires + 6 tests d'integration.** Methode (amendement s11, inchangee) :
somme de l'attribut `tests="..."` des rapports XML de surefire/failsafe apres ce `clean verify`.

| Module | Tests run (s47 -> s48) |
|---|---|
| recreo-domain | 120 -> 120 |
| recreo-application | 17 -> 17 |
| recreo-adapter-web | 400 -> 400 |
| recreo-adapter-content | 1107 -> 1123 |
| recreo-bootstrap (surefire, unitaires) | 288 -> 288 |
| recreo-bootstrap (failsafe, integration) | 6 -> 6 |

Seul `adapter-content` bouge, **+16**. Decompose par fichier, base commit
`bfdc7fee0d60dd18657ebda59a9b9996f829045e` contre `bc1e7835` (compte de `@Test` sur le fichier
entier a chaque extremite, pas sur le diff - un ajout de methode ne laisse pas toujours une ligne
`+@Test` litterale lisible par un grep sur le commit isole) :

- `PaquetCe1ProblemesEtapesTest` (neuf, `MAT-CE1-08`) : 0 -> 12.
- `PaquetCe1SoustractionsPoseesTest` (`SOP-CE1-R1`) : 13 -> 16, soit +3.
- `PaquetCe1TablesMultiplicationTest` (`TAB-CE1-R1`) : 8 -> 9, soit +1.

12 + 3 + 1 = 16, exactement l'ecart constate sur `adapter-content` (1107 -> 1123). Les quatre
autres stories (`AUT-CP-R1`, `ANG-CP-R1`, `ANG-CP-R2`, `EMC-CP-14`, `EMC-CM1-10`) ne modifient que
du YAML de contenu (confirme par `git diff --stat` sur chacun des quatre commits : une seule
entree de fichier `.yaml`, aucun `.java`), couvertes par des tests de paquet deja existants qui
relisent le fichier a l'execution - aucune methode de test neuve requise ni ajoutee pour elles.

**Zero ligne de `src/main/java` touchee sur l'ensemble du sprint** (`git diff --stat
bfdc7fee0..bc1e7835 -- '*.java' | grep src/main` vide). **ArchUnit inchange** : zero occurrence de
`static final ArchRule` ajoutee ou retiree sur la meme plage - aucune regle neuve due a s48.

## 2. Jobs de la CI (`.github/workflows/ci.yml`), un par un

Quatre jobs. Chacun reproduit localement, aucun deduit.

- **`build`** (`mvn -B verify`) : execute ci-dessus en `clean verify`, BUILD SUCCESS. L'etape
  separee `mvn -B -pl bootstrap -am package` du workflow n'a pas ete relancee a part : la phase
  `package` precede `verify` dans le cycle de vie Maven, et `spring-boot:repackage` a deja tourne
  pendant le `clean verify` (constate dans la sortie : "Replacing main artifact ... avec
  repackaged archive", puis `JarRecreoIT` vert) - l'etape separee du workflow est strictement
  incluse dans ce qui vient d'etre mesure, je ne la deduis pas, je dis ce qui la rend redondante.
- **`prefixes`** : les deux garde-fous de ce job testes avant application
  (`bash .github/scripts/verifier-prefixes-test.sh` et `commit-atteignable-test.sh`), tous deux
  verts. Puis `git log --no-merges --format=%s bfdc7fee0..bc1e7835 | bash
  .github/scripts/verifier-prefixes.sh` -> "Tous les commits portent un prefixe d'agent connu."
  Verifie nommement contre `CLAUDE.md` (section Regles cles) : `d08d6bbe` = `[MARC]`,
  `b29c900b`/`358b3d24`/`f99a47a9` = `[LEA]`, `4b6a6294`/`b77cabeb`/`bc1e7835` = `[SOF]`. Sept
  commits, sept prefixes conformes.
- **`chemins`** : garde-fou teste (`verifier-chemins-test.sh`, vert), puis `git ls-files -- . ':!
  .github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` ->
  "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."
- **`outils`** : la boucle exacte du workflow (trois motifs `outils/*-test.sh`,
  `squad/outils/*/*-test.sh`, `outils/*-test.py`) rejouee localement -> 9 juges purs executes,
  tous verts, sortie "9 juge(s) pur(s) execute(s)." avec code de sortie 0.

**Les quatre jobs sont verts, reproduits localement un par un.**

## 3. Tests instables

Aucun. Chaque lancement cite ici n'a ete execute qu'une fois ; comme a s47, un lancement unique
n'est pas qualifie de stable pour autant.

## 4. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique. Il n'audite pas les quatre fichiers Markdown non commitees
au moment de la mesure (hors perimetre des sept commits de s48). Il n'etablit aucun total sur un
arbre au-dela de `bc1e7835`.

---

**Conclusion** : les quatre jobs de la CI (`build`, `prefixes`, `chemins`, `outils`) sont verts,
reproduits localement un par un, aucun deduit de `mvn verify` seul. 1954 tests (+16 depuis s47),
l'integralite de l'ecart expliquee par trois fichiers de `adapter-content` sans reste. Zero ligne
de production touchee, zero regle ArchUnit ajoutee. Sept commits, sept prefixes d'agent conformes
a `CLAUDE.md`. **La porte technique de s48 est verte.**
