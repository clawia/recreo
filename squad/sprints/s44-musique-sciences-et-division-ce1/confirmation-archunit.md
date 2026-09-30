# Confirmation ArchUnit - s44 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-09-30.
- **Objet** : confirmer par ma propre mesure, sur l'arbre final de `round-two` une fois les trois
  lots de s44 fusionnes, ce que mes deux avis de faisabilite concluaient avant le developpement
  (`squad/rapports/avis-faisabilite-s44-mat-division.md`,
  `squad/rapports/avis-faisabilite-s44-mus-sci.md`) : aucune regle neuve, aucun `ADR` du. Le
  rapport de `yanis-qa` (`rapport-qualite.md`, `2c3be252`) n'est repris nulle part comme preuve ;
  il est seulement compare a ma mesure en fin de section 1.
- **Perimetre mesure** : `round-two` a **`048118bc`**, worktree dedie `s44-theo-confirmation`
  branche depuis ce commit. **Base de comparaison** : `e4cfcc20`, fusion de la cloture de s43 dans
  `round-two`, dernier commit de s43 sur la branche.
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte d'elle-meme
  (`ADR-0071`) : 20 lignes *"using Java 21.0.10"* dans le journal du reacteur, aucune autre
  version. Maven 3.9.9.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Le compte de regles ArchUnit est-il inchange depuis s43 ? | **oui** : **36 `static final ArchRule`, 35 `@ArchTest`**, a `e4cfcc20` comme a `048118bc`, et la table fichier par fichier est identique (section 2) |
| 2. `mvn -B clean verify` est-il vert sur ma propre mesure ? | **oui** : `BUILD SUCCESS`, 6 modules sur 6, **1805 tests, 0 echec, 0 erreur, 0 ignore** (section 1) |
| 3. Un `ADR` est-il du sur ce perimetre ? | **non** : aucune ligne de Java de production, aucun `pom.xml`, aucune mecanique, aucune cle de schema ; la matiere neuve est une donnee et non du code (section 3) |
| 4. Les trois paquets respectent-ils la Clean Architecture ? | **oui** : rien de neuf n'entre dans `domain`, `application` ni aucun `src/main/java` ; les trois tests neufs n'importent aucune classe Spring (section 4) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique et n'annonce pas
la porte technique du sprint, qui se prononce sur la CI en `review.md` par `sofia-sm`. `mvn verify`
n'est que le job `build` ; les trois autres jobs sont lances a part en section 5, sur mon diff.

## 1. La commande, sa sortie, ses comptes

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet depuis la racine du worktree : aucune mesure partielle, donc aucune question de
`-am`.

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  0.465 s]
[INFO] recreo-domain ...................................... SUCCESS [  9.435 s]
[INFO] recreo-application ................................. SUCCESS [  4.070 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 35.326 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 25.048 s]
[INFO] recreo-bootstrap ................................... SUCCESS [01:37 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  02:51 min
[INFO] Finished at: 2026-09-30T21:09:52+02:00
```

**Comptes, avec leur methode.** Methode autoritaire de `squad/README.md`, section 9 : somme des six
blocs `[INFO] Results:` de ce lancement apres `clean`. Perimetre : les six modules du reacteur.
Base s43 : les chiffres de ma confirmation de s43 (`9e1622dc`, relances a l'identique sur
`e50f0dd7`), `e4cfcc20` n'ayant ajoute depuis que des fichiers sous `squad/` et un distracteur
corrige (section 3.1).

| Module | Tests | Ecart avec s43 |
|---|---|---|
| `domain` | 120 | 0 |
| `application` | 17 | 0 |
| `adapter-web` | 400 | 0 |
| `adapter-content` | 974 | **+30** |
| `bootstrap` (surefire) | 288 | 0 |
| `bootstrap` (failsafe) | 6 | 0 |
| **total** | **1805** | **+30** |

**Recoupe par une seconde methode** : somme des attributs `tests="..."` de la balise `testsuite`
des **242** fichiers `TEST-*.xml` de `target/surefire-reports` et `target/failsafe-reports` des six
modules : **1805**. Les deux methodes concordent. Le rapport de `yanis-qa` cite 243 rapports XML pour
le meme total : l'ecart porte sur le nombre de fichiers et non sur le nombre de tests, et je n'en ai
pas verifie la cause.

**D'ou viennent les +30**, par comptage des annotations `@Test` et `@ParameterizedTest` de chaque
fichier Java modifie entre `e4cfcc20` et `048118bc` :

| Classe | s43 | s44 | Origine |
|---|---|---|---|
| `PaquetCe1DivisionTest` | 0 | 7 | `S44-MAT-DIVISION`, neuve |
| `PaquetCpMusiqueVocabulaireTest` | 0 | 11 | `S44-MUS-VOCABULAIRE`, neuve |
| `PaquetCm1SciencesEvolutionObjetTechniqueTest` | 0 | 12 | `S44-SCI-OBJET-TECHNIQUE`, neuve |
| `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | 28 | 28 | entrees de table, aucun test neuf |
| `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | 14 | 14 | idem |
| `TexteAccentueExposeParLePortEntrantTest` (`bootstrap`) | 5 | 5 | idem, d'ou `bootstrap` inchange |

7 + 11 + 12 = **30**, tous dans `adapter-content` : c'est exactement l'ecart mesure par les blocs
`Results:`. Aucune de ces classes n'est parametree, ce qui rend le comptage d'annotations egal au
comptage d'executions.

**Ce qui a tourne dans `com.recreo.archi`** : les **30** classes suivies de
`bootstrap/src/test/java/com/recreo/archi` ont chacune leur rapport, **209 tests, 0 echec, 0 erreur,
0 ignore** (somme des attributs de leurs 30 `TEST-*.xml`). Aucune n'est modifiee par s44.

## 2. Le compte de regles, avec sa methode

Methode : `git grep -c -F` sur les fichiers **suivis** de `domain`, `application`, `adapters` et
`bootstrap`, motifs `static final ArchRule` et `@ArchTest`, pathspec d'exclusion
`':!**/contreexemple/**'`, lance sur les deux arbres `e4cfcc20` et `048118bc`.

| Compte | s43 (`e4cfcc20`) | s44 (`048118bc`) | Sans l'exclusion |
|---|---|---|---|
| `static final ArchRule` | 36 | **36** | 38 |
| `@ArchTest` | 35 | **35** | 35 |

Les 2 `ArchRule` de l'ecart vivent dans
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/.../RegleContreExemple.java`,
faux module destine a etre juge faux. Les deux motifs rendent **13** fichiers, et la sortie par
fichier de `git grep -c` est **identique ligne pour ligne** entre les deux arbres (`diff` vide) :
aucune regle ajoutee, deplacee ni supprimee.

## 3. Aucun `ADR` n'est du : ce qui a reellement change

### 3.1 Le diff du code, hors `squad/` et `docs/`

`git diff --stat e4cfcc20 048118bc -- . ':!squad' ':!docs'` :

```
 .../src/main/resources/catalogue.yaml              |  18 ++
 .../src/main/resources/contenu/ce1-division.yaml   | 135 +++++++++
 .../cm1-sciences-evolution-objet-technique.yaml    | 113 +++++++
 .../resources/contenu/cp-musique-vocabulaire.yaml  |  81 +++++
 .../contenu/cp-qlm-objets-numeriques.yaml          |   2 +-
 ...eMathematiquesDeCe1DeclareSaSousPartieTest.java |   9 +-
 ...neReponseNEstPasPredictibleSurUnPaquetTest.java |  19 +-
 .../adapter/content/PaquetCe1DivisionTest.java     | 276 +++++++++++++++++
 ...quetCm1SciencesEvolutionObjetTechniqueTest.java | 328 +++++++++++++++++++++
 .../content/PaquetCpMusiqueVocabulaireTest.java    | 245 +++++++++++++++
 .../TexteAccentueExposeParLePortEntrantTest.java   |  45 ++-
 11 files changed, 1267 insertions(+), 4 deletions(-)
```

**Zero fichier sous un `src/main/java`, zero `pom.xml`.** La modification de
`cp-qlm-objets-numeriques.yaml` est la correction d'un distracteur par le `relecteur-pedagogique`
(`d2a799cb`, hors sprint), une chaine et non une cle.

### 3.2 Les trois questions que l'avis posait, verifiees sur l'arbre final

| Ce qui aurait appele un `ADR` | Constat sur `048118bc` | Methode |
|---|---|---|
| un champ neuf | **aucun** : les trois paquets portent `schema`, `id`, `type`, `descripteur` (`matiere`, `niveau`, `domaine`, `competence`), `lignesDeReferentiel`, `items` et, par item, `enonce`, `bonneReponse`, `distracteurs`, `indice`. Aucun `tableauSupport` ni `illustration` ; `ce1-division.yaml` ne nomme `tableauSupport` qu'en commentaire, pour dire qu'il n'en porte pas | extraction des cles YAML des trois fichiers ; `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` vert dans le reacteur |
| une mecanique neuve | **aucune** : les trois entrees neuves de `catalogue.yaml` declarent `mecanique: bandit-manchot`, deja servie | diff de `catalogue.yaml` |
| une matiere geree en code | **aucune**. `Éducation musicale` est une valeur de `matiere` qu'aucun paquet ne portait a `e4cfcc20` (dix valeurs distinctes, aucune musicale). Elle entre sans une ligne de Java parce que `ContentDescriptor` porte la matiere comme une `String` (`domain/src/main/java/com/recreo/domain/content/ContentDescriptor.java`) et qu'aucun `src/main/java` ni aucun gabarit ne nomme une matiere. `Sciences et technologie` et `Mathématiques` etaient deja servies (12 et 31 paquets a `e4cfcc20`) | `git grep` des valeurs de `matiere` a `e4cfcc20` ; `git grep` des noms de matiere dans `src/main/java` et `templates` |

C'est ce que l'avis `avis-faisabilite-s44-mus-sci.md` annoncait en section 3.2 (*"une matiere neuve
ne coute que des entrees d'inventaire"*), et c'est ce que le diff montre : les seules entrees
touchees par la matiere neuve sont les tables de test de `TexteAccentueExposeParLePortEntrantTest`.

### 3.3 `squad/decisions/` entre les deux arbres

`git diff --name-status e4cfcc20 048118bc -- squad/decisions` rend `REGISTRE-identifiants.md`,
`DEC-0099` (modifie), `DEC-0100` (ajoute) et `DEC-index.md`. **Aucun fichier sous
`squad/decisions/adr/`** : le dernier `ADR` reste `ADR-0102`, et `ADR-index.md` n'a pas a bouger.

**Verdict : aucun `ADR` n'est du.**

## 4. La Clean Architecture sur les trois paquets

Verification de routine, sans regle neuve :

- **`domain` et `application`** : aucun fichier modifie (section 3.1). Les regles qui les gardent
  (`domain` sans framework, ports sans Spring, dependances vers l'interieur) sont parmi les 209
  tests verts de `com.recreo.archi`.
- **Spring hors des adapters et de `bootstrap`** : aucune classe de production neuve, donc aucun
  point d'entree possible. Les imports ajoutes par le diff, releves un a un
  (`git diff ... -- '*.java' | grep '^+import'`), sont `com.recreo.domain.content.*`, `java.*`,
  `org.junit.jupiter.api.Test`, `org.assertj` et `org.yaml.snakeyaml.Yaml`, deja dependance
  d'`adapter-content` (aucun `pom.xml` touche). **Aucun `org.springframework`.**
- **Decouplage mecanique et contenu** : aucune classe de `domain/mechanic` ni de
  `domain/mechanics` touchee ; les trois paquets passent par le chargeur unique pilote par schema,
  sans chargeur neuf.

## 5. Les trois autres jobs de la CI, lances sur mon diff

Lances dans le worktree, ce fichier-ci indexe, avant le commit.

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert : *"Tous les cas passent"*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, ce fichier-ci inclus |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis le sujet du commit de ce fichier passe a `bash .github/scripts/verifier-prefixes.sh` | vert : les deux juges *"Tous les cas passent"*, puis *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | les trois motifs de juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | vert : **9** juges purs executes (5 sous `outils/*-test.sh`, 2 sous `squad/outils/*/*-test.sh`, 2 sous `outils/*-test.py`), code de sortie 0 pour chacun, aucun motif vide |

La derniere etape de `prefixes` juge le sujet du commit que ce fichier va porter, et non la plage
`round-two..HEAD`, vide tant que le commit n'existe pas : un garde-fou sur une population vide ne
prouve rien.

## 6. Ce que je n'ai pas fait

- Aucun Done pedagogique, sur aucun des trois paquets.
- Aucune annonce de la porte technique du sprint.
- Aucune mutation : s44 ne porte aucun `ADR` de ma main dont un chiffre serait a remesurer, a la
  difference de s43 (`ADR-0101`, `ADR-0102`). Les garde-fous qui voient le contenu neuf sont verts
  dans le reacteur ; les mettre a l'epreuve releve du rapport qualite.
- Aucun `ADR`, aucune dette ouverte, aucune regle ecrite.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas.
