# Rapport qualite de cloture - s49

**Auteur** : `yanis-qa`. **Date** : 2026-10-03. **Arbre mesure** : `round-two` a `90c36ab0`
(hash complet `90c36ab07eb8c8bd86502c0607d8aa873a964843`), mesure sur le checkout principal (pas de
worktree dedie : aucune mutation de materiau de production n'etait prevue, seule une mesure, et le
contre-exemple pose en section 3 a ete retire avant d'ecrire ce rapport - `git status --porcelain`
rendu vide, verifie).

**Perimetre de ce rapport** : les huit commits explicitement demandes, `d7360cc5` a `90c36ab0`
inclus (`d7360cc5`, `fcd7c3ee`, `98346560`, `224803e0`, `f5a5df9b`, `0e68e658`, `db14e371`,
`90c36ab0`). Les deux commits de ceremonie qui precedent ce lot dans l'historique de s49
(`cafd9fe5` ouverture, `e78acbb6` planning) ne sont pas dans ce perimetre et ne sont pas audites
ici. Base de comparaison pour "qui a change quoi" : `d7360cc5^` = `cafd9fe5`.

Contenu du lot : quatre stories de contenu sous `DEC-0107` - `FRA-CE1-01` (`[MARC]`, `224803e0`,
nouveau paquet `ce1-graphemes-complexes`), `PRO-CE1-R1`/`PRO-CE1-R2` (`[LEA]`, `f5a5df9b`),
`ANG-CP-R3` (`[LEA]`, `0e68e658`), `EMC-CP-14` item 7 (`[LEA]`, `db14e371`) - plus le mecanisme
T2-T4 d'`ADR-0082` (`[SOF]`, `90c36ab0`), et trois commits de gouvernance sans `.java` ni `.yaml`
(`d7360cc5` avis de faisabilite, `fcd7c3ee` correction factuelle, `98346560` choix de 28 oeuvres
candidates par `nadia-prof`, en Markdown seul).

`bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, lance deux fois sur cet arbre (une
pour le chiffre, une pour confirmer apres le test du garde-fou en section 3) : **BUILD SUCCESS**,
6/6 modules a chaque fois, ~9 min. JDK constate par `outils/verifier-jdk.sh` : JDK de compilation
21.0.10 (toolchain), java du PATH 21.0.12.1 - deux versions 21 distinctes, conforme a la toolchain
declaree.

---

## 1. Compte de tests autoritaire

**1975 tests : 1969 unitaires + 6 tests d'integration.** Methode (amendement s11, inchangee) :
somme de l'attribut `tests="..."` des rapports XML de surefire/failsafe apres `clean verify`.

| Module | Tests run (s48 -> s49) |
|---|---|
| recreo-domain | 120 -> 120 |
| recreo-application | 17 -> 17 |
| recreo-adapter-web | 400 -> 401 |
| recreo-adapter-content | 1123 -> 1138 |
| recreo-bootstrap (surefire, unitaires) | 288 -> 293 |
| recreo-bootstrap (failsafe, integration) | 6 -> 6 |

**+21** au total depuis la cloture de s48 (1954 -> 1975). Decompose par fichier, compte de `@Test`
sur le fichier entier a chaque extremite du commit qui le touche (pas sur le diff isole) :

- `PaquetCe1GraphemesComplexesTest` (`FRA-CE1-01`, `224803e0`) : 0 -> 13, soit **+13**.
- `PaquetCpAnglaisCultureTest` (`ANG-CP-R3`, `0e68e658`) : 10 -> 11, soit **+1**.
- `PaquetCpEmcAlerteTest` (`EMC-CP-14` item 7, `db14e371`) : 8 -> 9, soit **+1**.
- `RessourcesTiercesTest` (T2-T4, `90c36ab0`) : 21 -> 22, soit **+1**.
- `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` (T4, `90c36ab0`) :
  18 -> 20, soit **+2**.
- `LIllustrationDeContenuResoutEtAucuneNeDortTest` (T2, `90c36ab0`) : 15 -> 18, soit **+3**.
- `PaquetCe1ProblemesEtapesTest` (`PRO-CE1-R1`/`PRO-CE1-R2`, `f5a5df9b`) : 12 -> 12, **+0** - les
  deux corrections (familles a/b sur les items 9-11, item 8 sans vente, lexique accentue) relisent
  le YAML corrige avec des methodes de test deja existantes, sans nouvelle methode requise.
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` et
  `TexteAccentueExposeParLePortEntrantTest`, touches par `224803e0` : inchanges (28 -> 28, 5 -> 5)
  - generalisation d'un test existant au nouveau paquet, pas de methode neuve.

13 + 1 + 1 + 1 + 2 + 3 = **21**, exactement l'ecart constate module par module, sans reste. La
repartition par module colle aussi sans reste : adapter-content +15 (13 + 1 + 1), adapter-web +1,
bootstrap surefire +5 (1 + 2 + 2, cf. ci-dessus).

Les trois commits de gouvernance (`d7360cc5`, `fcd7c3ee`, `98346560`) ne touchent aucun `.java` ni
`.yaml` - confirme par `git show --stat` sur chacun, aucune ligne de code. Ils n'entrent pour rien
dans ce delta.

## 2. Jobs de la CI (`.github/workflows/ci.yml`), un par un

Quatre jobs. Chacun reproduit localement, aucun deduit.

- **`build`** (`mvn -B verify`) : execute ci-dessus en `clean verify`, BUILD SUCCESS, deux fois.
- **`prefixes`** : les deux garde-fous testes avant application
  (`verifier-prefixes-test.sh`, `commit-atteignable-test.sh`), tous deux verts. Puis `git log
  --no-merges --format=%s cafd9fe5..90c36ab0 | bash .github/scripts/verifier-prefixes.sh` ->
  "Tous les commits portent un prefixe d'agent connu." Verifie nommement contre `CLAUDE.md`
  (section Regles cles) : `d7360cc5` = `[THEO]`, `fcd7c3ee` = `[CAM]`, `98346560` = `[NAD]`,
  `224803e0` = `[MARC]`, `f5a5df9b`/`0e68e658`/`db14e371` = `[LEA]`, `90c36ab0` = `[SOF]`. Huit
  commits, huit prefixes conformes.
- **`chemins`** : garde-fou teste (`verifier-chemins-test.sh`, vert), puis `git ls-files -- . ':!
  .github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` ->
  "Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."
- **`outils`** : la boucle exacte du workflow (trois motifs `outils/*-test.sh`,
  `squad/outils/*/*-test.sh`, `outils/*-test.py`) rejouee localement -> 9 juges purs executes,
  tous verts, sortie "9 juge(s) pur(s) execute(s)." avec code de sortie 0.

**Les quatre jobs sont verts, reproduits localement un par un.**

## 3. Le dispositif T2-T4 (`90c36ab0`) mis a l'epreuve, pas seulement lu

`squad/corpus/oeuvres-domaine-public.yaml` declare `oeuvres: []` et aucun fichier n'existe sous
`static/oeuvres/` dans aucun module (`git ls-files | grep -i static/oeuvres` vide). Le seul JPEG du
depot est `bootstrap/src/test/resources/contreexemple/.../oeuvres/oeuvre-faux-format.jpg`, un
contre-exemple de test, hors de tout repertoire servi. Aucune entree de catalogue
(`adapters/adapter-content/src/main/resources/contenu/*.yaml`) ne reference un identifiant
`oeuvre-*`.

Un vert ne prouve rien tant qu'il n'a pas ete casse : j'ai copie ce contre-exemple sous
`adapters/adapter-web/src/main/resources/static/oeuvres/oeuvre-test-fuite.jpg` (une fuite
simulee dans une ressource reellement servie) et relance les trois garde-fous concernes.
Resultat : **rouge**, sur les trois angles a la fois -
`ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` refuse l'absence
d'entree de registre et le cliquet a plafond 0 depasse ; `LIllustrationDeContenuResoutEtAucuneNeDortTest`
refuse le fichier qu'aucun paquet ne declare. Cinq echecs, zero faux vert. Le fichier plante a
ensuite ete supprime, et les deux memes garde-fous reconfirmes verts (`git status --porcelain`
vide avant de clore). **Confirme : aucune entree de catalogue ne reference ces fixtures de test, et
le garde-fou qui le garantirait structurellement mord reellement.**

## 4. Tests instables

Aucun. Chaque lancement cite ici n'a ete execute qu'une fois (deux pour `clean verify`, par
construction de la section 3) ; un lancement unique n'est pas qualifie de stable pour autant.

## 5. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique. Il n'audite pas `cafd9fe5` ni `e78acbb6` (ouverture et
planning de s49, hors du perimetre demande). Il n'etablit aucun total sur un arbre au-dela de
`90c36ab0`.

---

**Conclusion** : les quatre jobs de la CI (`build`, `prefixes`, `chemins`, `outils`) sont verts,
reproduits localement un par un. 1975 tests (+21 depuis s48), l'integralite de l'ecart expliquee
par six fichiers sans reste. Huit commits, huit prefixes d'agent conformes a `CLAUDE.md`. Le
mecanisme T2-T4 ne sert aucun octet de fixture au catalogue, verifie en cassant le correctif qui
le garantit puis en le remettant. **La porte technique de s49 est verte.**
