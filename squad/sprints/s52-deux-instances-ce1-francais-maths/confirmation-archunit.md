# Confirmation ArchUnit - s52 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-06.
- **Objet** : confirmer, pour la review de s52, sur l'arbre ou le retrait des deux paquets de sciences
  CM1, l'instance A (francais CE1), l'instance C (mathematiques CE1) et les amendements sont fusionnes,
  que les regles ArchUnit sont vertes, que leur compte n'a pas bouge et que la Clean Architecture tient.
- **Arbre juge** : `round-two` a **`40daa419`**, mesure dans un worktree dedie ouvert a ce commit,
  propre avant la mesure (`git status`). **Base de comparaison** : `b4ce7d93` (planning de s52),
  ancetre de `40daa419` (`git merge-base --is-ancestor`, code 0).
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte (`ADR-0071`) :
  propriete `java.version` des 32 rapports `TEST-*.xml` de `bootstrap`, valeur `21.0.10` dans les 32,
  aucune autre.

---

## 0. Verdict

| Question | Verdict |
|---|---|
| Les regles ArchUnit sont-elles vertes ? | **oui** : 229 tests dans `com.recreo.archi`, 0 echec, 0 erreur, 0 ignore (section 1) |
| Le compte de regles a-t-il change pendant s52 ? | **non** : 39 `static final ArchRule` et 36 `@ArchTest` contre-exemples compris, 37 / 36 exclus, par les deux methodes ; sortie par fichier identique a `b4ce7d93` (section 2) |
| Un commit de s52 touche-t-il `domain`, `application` ou du Java de production ? | **non** : aucun fichier, 0 ligne de `src/main/java` ; `adapter-web` ne recoit que 14 SVG sous `static/illustrations` (section 3) |
| Les cliquets de poids, d'illustration et de designation valent-ils exactement leur population ? | **oui**, remesures hors test (section 4) |
| `ADR-0080` est-il en accord avec `DEC-0111` A.7 ? | **oui depuis ce commit** : amendement A2ter (section 5) |
| Le constat de toolchain de `nadia-prof` est-il un defaut de l'enveloppe ? | **non** : le fichier qui designe un JDK 25 n'est pas celui que Maven lit (section 6) |

## 1. Les tests de `com.recreo.archi`

```
bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false
```

Lance le 2026-10-06 a 23:25:36+02:00, termine a 23:30:42+02:00 : **`BUILD SUCCESS`**, six modules du
reacteur en `SUCCESS` (`-am` construit `domain`, `application`, `adapter-web` et `adapter-content`
depuis le worktree). Premiere ligne de l'enveloppe : *"Maven lance sur le JDK de la toolchain jdk 21 :
21.0.10"*. Perimetre : le seul paquetage `com.recreo.archi` de `bootstrap`, pas le reacteur complet.

**Comptes**, methode : somme des attributs `tests`, `failures`, `errors`, `skipped` des 32 rapports
`TEST-com.recreo.archi.*.xml`, pour 32 fichiers suivis sous `bootstrap/src/test/java/com/recreo/archi`
(30 a `b4ce7d93`).

| | Tests | Echecs | Erreurs | Ignores |
|---|---|---|---|---|
| `com.recreo.archi` a `40daa419` | **229** | 0 | 0 | 0 |
| s51, a `ca4c934a` | 216 | 0 | 0 | 0 |

**Les +13 sont expliques.** Aucun commit entre `ca4c934a` et `b4ce7d93` ne touche le paquetage (`git
diff --stat` vide). Entre `b4ce7d93` et `40daa419`, les lignes `@Test`/`@ParameterizedTest`/`@ArchTest`
ajoutees ou retirees du diff brut du paquetage sont 13 `+ @Test` et aucune autre : 6 dans
`LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` et 7 dans
`LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest`, deux classes neuves, verts tous les 13.
Commits de s52 qui touchent le paquetage : `ceffde66` (A1, `theo-archi`), `92344c23`, `fb1c7ad3`,
`173b48f7` (`lea-dev`).

## 2. Le compte de regles

Methodes de s51, inchangees : **`ADR-0104`** (lignes des `*.java` suivis correspondant a
`^\s*(public |private |protected )?static final ArchRule ` et a `^\s*@ArchTest\s*$`, contre-exemples
compris puis exclus par `':!**/contreexemple/**'`) et **s50** (`git grep -F` sur `domain`,
`application`, `adapters`, `bootstrap`, memes deux variantes). Sur les arbres de Git, pas sur le
disque : `target/` et worktrees exclus par construction.

| Commit | `ADR-0104`, compris | `ADR-0104`, exclus | s50, compris | s50, exclus |
|---|---|---|---|---|
| `ca4c934a` (tete s51) | 39 / 36 | 37 / 36 | 39 / 36 | 37 / 36 |
| `b4ce7d93` (planning s52) | 39 / 36 | 37 / 36 | 39 / 36 | 37 / 36 |
| **`40daa419`** (tete s52) | **39 / 36** | **37 / 36** | **39 / 36** | **37 / 36** |

Lecture : `static final ArchRule` / `@ArchTest`. La sortie par fichier (`git grep -cE`) est identique
entre `b4ce7d93` et `40daa419` pour les deux motifs (`diff` vide). **Ecart avec s51 : aucun.**

**Les gardes ajoutes ou touches a s52 sont des tests JUnit, pas des `ArchRule`.** Par paquetage :

| Garde | Classe | Dans `com.recreo.archi` ? |
|---|---|---|
| Test geometrique de la symetrie | `LesFiguresDeSymetrieMontrentCeQueLeursItemsAffirmentTest` (neuf) | **oui** |
| Reseaux de points de `MAT-CE1-06` | `LesReseauxDePointsMontrentCeQueLeursItemsAffirmentTest` (neuf) | **oui** |
| Balayages de designation (message A1, plafond 2 vers 3) | `AucunPaquetDeDesignationNEntreEnProductionSansEtendreLesTroisBalayagesTest` | **oui** |
| Dette de lisibilite du libelle (2 vers 3) | `AucunPaquetDeDesignationNEntreEnProductionSansLaDetteDeLisibiliteDuLibelleTest` | **oui** |
| Illustration qui ne dort pas (64 vers 81, 42 vers 56) | `LIllustrationDeContenuResoutEtAucuneNeDortTest` | **oui** |
| `ZON-13` (A2) | `DeuxZonesDUnItemNeSeChevauchentJamaisTest` | non : `com.recreo.adapter.content`, commit `edf15763`, anterieur au planning |
| Rang de declaration de la bonne zone (A2) | `LaBonneZoneNEstPasPredictibleSurUnPaquetTest` | non : `com.recreo.adapter.content`, meme commit |
| Rang de la bonne reponse (retrait, `DEC-0111` A.1) | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | non : `com.recreo.adapter.content` |
| Absence des jeux retires | `LesJeuxRetiresDuCatalogueNeSontPasServisTest` (neuf) | non : `com.recreo.bootstrap.catalogue` |
| Cliquet de poids, `DESSINS_DE_DESIGNATION` | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | non : `com.recreo.adapter.web` |

## 3. Clean Architecture

```
git diff --name-status b4ce7d93 40daa419 -- . ':!squad' ':!docs'
```

50 fichiers, 4 773 lignes ajoutees, 59 retirees (`--stat`). Repartition :

- **`adapter-content`** : `catalogue.yaml`, sept paquets (`ce1-categories`, `ce1-comparer-ranger`,
  `ce1-conjugaison-present`, `ce1-sens-multiplication`, `ce1-symetrie`, `ce1-synonymes-contraires`
  neufs ; `ce1-ordre-alphabetique` et `ce1-temps` modifies), leurs tests et trois jeux fautifs.
- **`adapter-web`, `src/main`** : exactement **14 SVG ajoutes** sous `static/illustrations/` - les 11
  de `ce1-symetrie` (`symetrie-figure-a` a `-f`, `symetrie-grille-a` a `-e`) et les 3 de
  `ce1-sens-multiplication` (`reseau-points-a` a `-c`). Aucun autre fichier de `src/main`.
- **`adapter-web`, `src/test`** : le seul `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest`,
  qui ajoute les 11 noms `symetrie-*.svg` a `DESSINS_DE_DESIGNATION` et monte `POIDS_TOTAL_MAXIMUM`.
  Les 3 reseaux n'y sont pas : ce sont des illustrations de paquet `question`, pas de designation.
- **`bootstrap`, `src/test`** : les cinq classes de `com.recreo.archi` de la section 2,
  `LesJeuxRetiresDuCatalogueNeSontPasServisTest`, `TexteAccentueExposeParLePortEntrantTest`, et deux
  jeux fautifs.

Verifications, chacune sur `b4ce7d93..40daa419` :

- `git diff --numstat ... -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
  est **vide** : aucun fichier de `domain` ni d'`application`, **0 ligne de `src/main/java`** dans
  aucun module, aucun `pom.xml`, gabarit, script de CI ou d'outil.
- Lignes ajoutees contenant `springframework` dans le diff Java : 2, toutes deux dans
  `bootstrap/.../catalogue/LesJeuxRetiresDuCatalogueNeSontPasServisTest.java` (`@SpringBootTest`,
  `@Autowired`), un test de `bootstrap`, la couche qui voit Spring. Aucune dans `domain`,
  `application` ni un adapter.
- Les regles de couches sont parmi les 229 tests verts de la section 1.

## 4. Cliquets

Remesure hors test, sur l'arbre de Git a `40daa419`, puis confrontee a la constante et au test vert.

| Cliquet | Constante | Remesure | Methode |
|---|---|---|---|
| `POIDS_TOTAL_MAXIMUM` | 69 203 | **69 203** | `git ls-files` sous `adapters/adapter-web/src/main/resources/static/illustrations`, `git show HEAD:<f> \| tr -d '\r' \| wc -c`, somme ; meme total sur le disque |
| `FICHIERS_D_ILLUSTRATION` | 56 | **56** | meme `git ls-files`, nombre de fichiers ; 42 + 3 + 11 |
| `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` | 81 | **81** par le test | 64 + 6 (`ce1-sens-multiplication`) + 11 (`ce1-symetrie`) ; le test juge l'egalite exacte et il est vert |
| `PLAFOND_DE_DESIGNATION_EN_PRODUCTION` | 3 | **3** par le test | le test rougit dans les deux sens (au-dessus et au-dessous) et il est vert |
| `PAQUETS_DE_DESIGNATION_PORTANT_LA_DETTE_DE_LISIBILITE` | 3 | **3** par le test | idem |

Les 14 SVG de s52 pesent 17 896 octets en LF (3 905 pour les reseaux, 13 991 pour la symetrie),
soit exactement l'ecart 51 307 vers 69 203 que la Javadoc du cliquet ecrit en deux marches. **Chaque
plafond vaut exactement sa population.** Le cliquet de poids lui-meme est dans
`com.recreo.adapter.web` : son vert vient du job `build` de la section 7, pas de la section 1.

## 5. `ADR-0080`, amendement A2ter

A2bis ecrivait *"le plancher retenu est 16 unites"* ; `DEC-0111` A.7 (`camille-po`) arbitre que la
barre de refus d'une zone est celle de `nadia-prof` (44 px a 296 px, soit 15 unites sur un carre de
100) et que 16 unites est la marge de dessin d'`ines-design`. **A2ter** remet l'`ADR` en accord, dans
une section d'amendement datee, sans reecrire A2bis. Regle executable : aucune, meme motif qu'A2bis
point 1 ; le mecanisme automatique reste A3. `ADR-index.md`, ligne d'`ADR-0080`, mise a jour. Le refus
5 des contraintes d'`ines-design` reste a elle.

## 6. Le constat de toolchain de `nadia-prof`

**Constat recu** : l'entree de version 21 de `~/.m2/toolchains.xml` designe un dossier `jdk-25.0.2`,
et le build ne refuse pas. **Reproduit pour le fichier, infirme pour le defaut.**

Mesures du 2026-10-06, sans toucher aucun fichier de toolchains :

1. Le shell et la machine virtuelle ne designent pas le meme repertoire utilisateur : `HOME` du shell
   POSIX et `user.home` rapporte par `java -XshowSettings:properties` different, et `test -ef` sur
   les deux `.m2/toolchains.xml` rend **distincts**.
2. Le fichier du shell declare `<version>21</version>` sur une racine dont `bin/java -version`
   rapporte **25.0.2** : c'est le fichier que `nadia-prof` a lu, et le constat est exact pour lui.
3. Le fichier sous le `user.home` de la machine virtuelle, **celui que Maven lit**, declare
   `<version>21</version>` sur une racine qui rapporte **21.0.10**.
4. `bash outils/mvn-jdk21.sh -v` imprime *"Verdict rendu sur le fichier de toolchains que Maven lit, qui
   n'est pas celui de ce shell."* puis *"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*, et
   Maven rapporte `Java version: 21.0.10`. `bash outils/verifier-jdk.sh` rend : *"JDK de compilation :
   21.0.10 | ... | toolchains lu par Maven : oui (un autre que celui lu par ce script)"*.
5. Les 32 rapports de la section 1 portent `java.version` = `21.0.10`.

**Pourquoi il n'a pas refuse** : l'enveloppe juge le fichier que Maven lit
(`fichier_de_toolchains_gouvernant`, correction du 2026-08-31), pas celui du shell. Juger celui du
shell est le faux rouge que cette correction a ferme : refuser une mesure qui se fait sur 21.0.10.
Ce cas exact est un cas nomme de `outils/mvn-jdk21-test.sh` (*"le fichier du shell ment, celui que
Maven lit est bon : Maven est lance"*), et le cas inverse, ou c'est le fichier de Maven qui ment, y
est verifie en refus. **Aucun correctif de script.** Le fichier du shell est un fichier de poste
perime que Maven ne lit pas ; le corriger ou le supprimer est un geste de poste, hors du depot.

## 7. Jobs de CI lances sur ce diff

Les quatre jobs de `.github/workflows/ci.yml`, lances depuis ce worktree sur la branche
`s52-theo-archunit` :

| Job | Commande | Resultat |
|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine, 23:38:03 a 23:51:18 | **`BUILD SUCCESS`**, six modules en `SUCCESS` ; Surefire : `domain` 120, `application` 17, `adapter-web` 401, `adapter-content` 1 318, `bootstrap` 310 ; Failsafe `bootstrap` 6 ; 0 echec, 0 erreur partout. `java.version` = `21.0.10` dans les 277 rapports `TEST-*.xml` des cinq modules. Le cliquet de poids : 17 tests, 0 echec |
| `chemins` | `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh`, plus ce fichier avant son ajout | code 0, *"Aucun chemin de poste de travail ni nom de compte"* |
| `outils` | `for t in outils/*-test.sh; do bash "$t"; done`, puis les deux `outils/*-test.py` | code 0 pour les 5 scripts `sh` et les 2 `py` |
| `prefixes` | `git log --no-merges --format=%s round-two..HEAD \| bash .github/scripts/verifier-prefixes.sh` | sujet verifie avant le commit (sortie : *"Tous les commits portent un prefixe d'agent connu."*), relance apres |

Ce fichier a ete ecrit pendant le job `build` : la suite de `com.recreo.archi`, qui lit `squad/`, a
ete relancee une fois l'arbre final en place (commande de la section 1), 23:52:07 a 23:57:44 : `BUILD SUCCESS`, 229 tests, 0 echec, 0 erreur, 0
ignore.

## 8. Ce que cette confirmation ne fait pas

- Elle ne prononce aucun Done pedagogique et ne ferme aucune ligne de `MATRICE-couverture.md`.
- Elle n'ouvre aucune regle ni dette, ne modifie aucun test ni contenu, n'alloue aucun identifiant.
- Elle corrige, hors de ce fichier, la ligne 108 de la confirmation de s51 (A3 de la retro de s51) :
  `02ba9e80` est sur la branche de l'instance A et entre par la fusion `84d5b509`, sur son second
  parent ; `git merge-base --is-ancestor` le donne absent du second parent de `7716be0e` (instance B).

## Decisions prises

Une : l'amendement A2ter d'`ADR-0080` (section 5), qui met l'`ADR` en accord avec un arbitrage deja
pris par `camille-po` et n'en prend aucun nouveau.
