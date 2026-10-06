# Confirmation ArchUnit - s51 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-06.
- **Objet** : confirmer, pour la review de s51, sur l'arbre ou les deux instances sont fusionnees
  (francais CE1 : `FRA-CE1-20`, `FRA-CE1-22`, `FRA-CE1-23`, `HOM-CE1-R1` ; sciences CM1 :
  `SCI-CM1-10`, `SCI-CM1-15`, `SCI-CM1-18`) et ou l'amendement A1 d'`ADR-0065` est livre, que les
  regles ArchUnit sont vertes, que leur compte n'a pas bouge depuis `ADR-0104` et que la Clean
  Architecture tient.
- **Arbre juge** : `round-two` a **`ca4c934a`**, mesure dans un worktree detache a ce commit, propre
  avant la mesure (`git status`). **Base de comparaison** : `e4d8ba3b` (planning de s51), ancetre de
  `ca4c934a` (`git merge-base --is-ancestor`).
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte (`ADR-0071`) :
  propriete `java.version` des 30 rapports `TEST-*.xml` de `bootstrap`, valeur `21.0.10` dans les 30,
  aucune autre.

---

## 0. Verdict

| Question | Verdict |
|---|---|
| Les regles ArchUnit sont-elles vertes ? | **oui** : 216 tests dans `com.recreo.archi`, 0 echec, 0 erreur, 0 ignore (section 1) |
| Le compte de regles a-t-il change pendant s51 ? | **non** : 39 `static final ArchRule` et 36 `@ArchTest` par la methode d'`ADR-0104`, sortie par fichier identique a `e4d8ba3b` (section 2) |
| Un commit de s51 touche-t-il `domain`, `application`, `adapter-web` ou du Java de production ? | **non** : aucun fichier, aucune ligne de `src/main/java`, aucune ligne ajoutee contenant `springframework` (section 3) |
| Le garde-fou de poids et l'illustration qui ne dort pas sont-ils verts ? | **oui**, et leurs cliquets valent exactement la population (section 4) |
| `ADR-0104` appelle-t-il un geste a s51 ? | **non** : statut `Propose`, inchange, la reponse de `nadia-prof` sur `C5` clause 1 n'est pas arrivee (section 5) |

## 1. Les tests de `com.recreo.archi`

```
bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false
```

Lance le 2026-10-06 a 14:53:48+02:00, termine a 14:55:51+02:00 : **`BUILD SUCCESS`**, six modules du
reacteur en `SUCCESS` (`-am` construit `domain`, `application`, `adapter-web` et `adapter-content`
depuis le worktree, pas depuis `~/.m2/repository`). Perimetre : le seul paquetage `com.recreo.archi`
de `bootstrap` ; ce n'est **pas** le reacteur complet, et aucun autre test n'est mesure ici.

**Comptes**, methode : somme des attributs `tests`, `failures`, `errors`, `skipped` des 30 rapports
`TEST-com.recreo.archi.*.xml`, pour 30 fichiers suivis sous `bootstrap/src/test/java/com/recreo/archi`.

| | Tests | Echecs | Erreurs | Ignores |
|---|---|---|---|---|
| `com.recreo.archi` a `ca4c934a` | **216** | 0 | 0 | 0 |
| s50, section 5.2, a `1db6348f` | 214 | 0 | 0 | 0 |

**Les +2 sont expliques** : le seul commit qui touche `bootstrap/src/test/java/com/recreo/archi`
entre `1db6348f` et `ca4c934a` est `3ade55cb` (`ADR-0104`, avant le planning de s51), qui ajoute deux
lignes `@Test`/`@ArchTest` et n'en retire aucune : la regle 13 de `DecouplageMecaniqueContenuTest`
(13 tests) et sa morsure dans `LesReglesMordentTest` (37 tests). Methode : `grep -cE` sur la sortie
brute de `git diff 1db6348f ca4c934a -- bootstrap/src/test/java/com/recreo/archi`. **Aucun commit de
s51 ne touche ce paquetage.**

## 2. Le compte de regles

Deux methodes coexistent dans le depot, et elles ne comptent pas la meme chose :

- **Methode de s50** : `git grep -c -F` sur les fichiers suivis de `domain`, `application`,
  `adapters`, `bootstrap`, motifs `static final ArchRule` et `@ArchTest`, avec puis sans
  `':!**/contreexemple/**'`.
- **Methode d'`ADR-0104`** : lignes des `*.java` suivis qui correspondent a
  `^\s*(public |private |protected )?static final ArchRule ` et a `^\s*@ArchTest\s*$`, contre-exemples
  compris.

Toutes les commandes sont lancees par `rtk proxy` (sortie non filtree), sur les arbres de Git et non
sur le disque : `target/` et worktrees sont exclus par construction.

| Commit | s50, contre-exemples exclus | s50, inclus | `ADR-0104` |
|---|---|---|---|
| `3ade55cb^` (avant `ADR-0104`) | 36 / 35 | 38 / 35 | 38 / 35 |
| `3ade55cb` (`ADR-0104`) | 37 / 36 | 39 / 36 | 39 / 36 |
| `e4d8ba3b` (planning s51) | 37 / 36 | 39 / 36 | 39 / 36 |
| **`ca4c934a`** (tete s51) | **37 / 36** | **39 / 36** | **39 / 36** |

Lecture : `static final ArchRule` / `@ArchTest`. La sortie par fichier est **identique ligne pour
ligne** entre `e4d8ba3b` et `ca4c934a` pour les deux motifs (`diff` vide). 13 fichiers hors
contre-exemples portent au moins un `static final ArchRule`, 12 au moins un `@ArchTest`.

**Ecart avec la consigne recue** : elle annoncait "36/35 avant `ADR-0104`, 39/36 apres". Ces deux
paires ne sont pas de la meme methode. 36/35 est la methode de s50 contre-exemples exclus, qui passe a
**37**/36 ; 39/36 est la methode d'`ADR-0104`, contre-exemples compris, qui partait de **38**/35. La
regle 13 ajoute une `ArchRule` hors contre-exemple, le contre-exemple de `ADR-0104` est une classe
sans `ArchRule` : les deux methodes avancent de un, et aucune ne passe de 36 a 39.

## 3. Clean Architecture

```
git diff --stat e4d8ba3b ca4c934a -- . ':!squad' ':!docs'
```

22 fichiers, 2 865 lignes ajoutees, 12 retirees, tous sous `adapters/adapter-content` sauf un :

- **Contenu** (`adapter-content/src/main/resources`) : `catalogue.yaml`, les six paquets neufs
  `ce1-temps`, `ce1-ordre-alphabetique`, `ce1-graphie`, `cm1-sciences-activite-humaine`,
  `cm1-sciences-corps`, `cm1-sciences-terre`, et `ce1-homophones` (`HOM-CE1-R1`, absent de la liste
  de la consigne, meme nature).
- **Tests** (`adapter-content/src/test`) : les six `Paquet*Test` neufs, `AucunMotServiNeSEcarteDeSonAccentTest`
  et son jeu fautif `contreexemple/.../cedille/` (A1 d'`ADR-0065`),
  `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, quatre jeux fautifs sous `contreexemple/`.
- **Un test de `bootstrap`** : `bootstrap/catalogue/TexteAccentueExposeParLePortEntrantTest`.

Verifications, chacune sur `e4d8ba3b..ca4c934a` :

- `git diff --name-only ... -- domain application adapters/adapter-web '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
  est **vide** : aucun fichier de `domain`, d'`application` ni d'`adapter-web`, aucun `pom.xml`,
  aucun gabarit, aucun script de CI ou d'outil.
- `git diff --numstat ... -- '*/src/main/java/**'` : **0 ligne**.
- **A1 d'`ADR-0065`** : livre deux fois a l'identique, `c2802a32` (instance A) et `02ba9e80`
  (instance B), meme `git patch-id --stable`, fusionnes par `84d5b509` et `7716be0e`. Les deux ne
  touchent que l'`ADR`, l'index, `AucunMotServiNeSEcarteDeSonAccentTest` et son jeu fautif.
- Imports ajoutes par le diff Java (lignes `+import`, sortie brute) : `com.recreo.domain.content`,
  `java.io`, `java.nio.charset`, `java.nio.file`, `java.text`, `java.util`, `java.util.regex`,
  `org.assertj.core.api`, `org.junit.jupiter.api`, `org.yaml.snakeyaml`. **Aucune ligne ajoutee ne
  contient `springframework`** (`grep -c` : 0).
- Les regles de couches (dependances vers l'interieur, `domain` sans framework, ports
  d'`application` sans Spring, mecaniques independantes du contenu et entre elles, regle 13) sont
  parmi les 216 tests verts de la section 1.

## 4. Garde-fou de poids et illustration qui ne dort pas

Tous deux dans la mesure de la section 1 :

| Test | Tests | Echecs | Erreurs | Ignores |
|---|---|---|---|---|
| `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` | 20 | 0 | 0 | 0 |
| `LIllustrationDeContenuResoutEtAucuneNeDortTest` | 18 | 0 | 0 | 0 |

- **Cliquet de poids** : `REPRODUCTIONS_SERVIES = 8`, `POIDS_TOTAL_MAXIMUM = 176_474`, inchanges
  depuis `1db6348f`. Population a `ca4c934a` : 8 fichiers suivis sous
  `*/src/main/resources/static/oeuvres/` (`git ls-files`), 176 474 octets (`wc -c` sur ces fichiers).
  **Le plafond vaut exactement la dette.**
- **Illustration** : `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION = 64` et `FICHIERS_D_ILLUSTRATION = 42`,
  inchanges. Aucun des sept paquets touches par s51 n'ajoute de ligne contenant `illustration`
  (`grep -c` sur le diff de `contenu/*.yaml` : 0), et aucun fichier sous `static/` n'est modifie :
  la population ne bouge pas, le test le confirme en vert.

## 5. `ADR-0104`

Statut **`Propose`**, inchange : `git diff --stat e4d8ba3b ca4c934a` sur le fichier de l'`ADR` est
vide, et aucune ligne ajoutee ou retiree pendant s51 ne mentionne `ADR-0104` ou `C5` (diff de
`ADR-index.md` et du backlog). Son passage a `Accepte` attend la seule reponse de `nadia-prof` sur
`C5` clause 1, qui n'est pas dans l'arbre ; `ART-CP-10` est hors capacite de s51 (planning, `R6`). La
regle 13 est en vigueur et verte (section 1). **Rien a faire a s51.**

## 6. Ce que cette confirmation ne fait pas

- Elle ne mesure pas le reacteur complet : seul `com.recreo.archi` est execute (section 1). Elle
  n'annonce donc pas la porte technique, dont aucun job (`build`, `prefixes`, `chemins`, `outils`)
  n'est lance ici en entier.
- Elle ne prononce aucun Done pedagogique et ne ferme aucune ligne de `MATRICE-couverture.md`.
- Elle n'ouvre aucun `ADR`, aucune regle, aucune dette, et ne modifie aucun test ni contenu.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas.
