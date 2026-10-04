# Confirmation ArchUnit - s50 (`theo-archi`)

> **Amendement du 2026-10-04, second passage (`theo-archi`).** Ce document a d'abord ete ecrit sur
> `6eded9d1` (commit `ce72dc03`), avant la levee de `T6`. Les sections 0 a 4 restent telles qu'elles
> ont ete mesurees alors, et ne decrivent plus l'arbre final. La **section 5** porte la confirmation
> qui vaut pour la review : arbre `1db6348f`, reacteur relance apres `clean`, regles recomptees,
> diff relu. En cas de divergence entre les sections 0 a 4 et la section 5, c'est la section 5 qui
> dit l'etat du depot.

- **Auteur** : `theo-archi`. **Date** : 2026-10-04.
- **Objet** : confirmer, pour la review de s50, sur l'arbre ou les cinq stories de `DEC-0108` sont
  livrees (`FRA-CE1-02`, `FRA-CE1-16`, `GRC-CE1-R1`, `SOU-CE1-R1`, `DIV-CE1-R1`), que les regles
  ArchUnit sont vertes, que leur compte n'a pas bouge et que la Clean Architecture tient, comme mon
  avis de faisabilite (`squad/rapports/avis-faisabilite-s50-cinq-stories.md`) le prevoyait.
- **Perimetre mesure** : `round-two` a **`6eded9d1`**, tete verifiee par `git log` avant la mesure ;
  `git merge-base --is-ancestor` confirme que `fc6f9c16`, `e979b575`, `c4250c2d`, `60dc1188`,
  `a7af90b3` et `6eded9d1` sont dans cette lignee. **Base de comparaison** : `03d152b0`, base de mon
  avis. `git diff --name-only 03d152b0 fc6f9c16^` ne rend que `docs/backlog/backlog.md` et l'avis
  lui-meme : la base et le dernier commit avant le premier lot sont identiques hors `squad/` et
  `docs/`.
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte (`ADR-0071`) :
  20 lignes *"using Java 21.0.10"* dans le journal du second lancement de la section 1, aucune autre
  version.

---

## 0. Verdict

| Question | Verdict |
|---|---|
| Le reacteur complet est-il dans l'etat attendu ? | **oui** : 1997 tests, **2 echecs**, 0 erreur, 0 ignore, les deux echecs etant exactement la dette `T6` que `DEC-0108` assume, et rien d'autre (section 1) |
| Les regles ArchUnit sont-elles vertes ? | **oui**, hors ces deux echecs : 214 tests dans `com.recreo.archi`, 212 verts, les 2 rouges etant ceux de `LIllustrationDeContenuResoutEtAucuneNeDortTest` (section 1) |
| Le compte de regles a-t-il change ? | **non** : **36 `static final ArchRule`, 35 `@ArchTest`**, sortie par fichier identique a `03d152b0` (section 2) |
| Un commit de s50 viole-t-il la Clean Architecture ? | **non** : aucun fichier de `domain` ni d'`application`, aucun `src/main/java`, aucune ligne ajoutee contenant `springframework` (section 3) |

## 1. Le reacteur complet, apres `clean`

```
bash outils/mvn-jdk21.sh -B clean verify
```

**Premier lancement**, dans l'arbre principal a `6eded9d1`, le 2026-10-04 : `BUILD FAILURE` sur
`recreo-bootstrap` (surefire), les cinq autres modules en `SUCCESS`. Les tests d'integration de
`bootstrap` (failsafe) ne sont donc pas atteints. Comptes surefire : 120 / 17 / 401 / 1160 / 293,
2 echecs.

**Second lancement**, pour atteindre les tests d'integration, avec `-Dmaven.test.failure.ignore=true`.
Lance d'abord dans l'arbre principal, il s'arrete au `clean` de `recreo-domain` : le jar de
`domain/target` est tenu par un autre processus. Je n'ai arrete aucun processus que je n'ai pas lance.
Mesure refaite dans un worktree detache a `6eded9d1`, hors de l'arbre du depot, supprime apres la
mesure : `BUILD SUCCESS` (echecs ignores par l'option), 6 modules sur 6, termine le
2026-10-04T13:55:14+02:00.

**Comptes**, methode : somme des six blocs `Tests run:` de synthese du second lancement, perimetre les
six modules du reacteur ; base, mon avis de s50 (1975 a `03d152b0`, meme methode).

| Module | Tests | Echecs | Ecart avec l'avis |
|---|---|---|---|
| `domain` | 120 | 0 | 0 |
| `application` | 17 | 0 | 0 |
| `adapter-web` | 401 | 0 | 0 |
| `adapter-content` | 1160 | 0 | **+22** |
| `bootstrap` (surefire) | 293 | 2 | 0 |
| `bootstrap` (failsafe) | 6 | 0 | 0 |
| **total** | **1997** | **2** | **+22** |

0 erreur et 0 ignore dans chaque bloc. **Les +22 sont expliques** : le diff Java `03d152b0..6eded9d1`
ajoute 22 lignes `@Test`, n'en retire aucune et n'ajoute aucun `@ParameterizedTest`, `@TestFactory`
ni `@RepeatedTest` ; tous les fichiers Java touches sont des tests, dont les quatre tests de paquet
d'`adapter-content`. Methode : `grep -cE` sur la sortie brute de `git diff 03d152b0 6eded9d1 -- '*.java'`.

**Identite des deux echecs**, identique dans les deux lancements :

- `com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort`
- `com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest.unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste`

Message lu en entier pour chacun : il nomme **25 objets, tous des fichiers `oeuvre-*.jpg`**, chacun
avec le motif *"aucun item d'aucun paquet servi ne le declare"* (`ADR-0073` clause 2, `ADR-0082`
decision 2), et aucun autre objet. Methode : sur chacune des deux lignes du bloc `[ERROR] Failures:`
du journal, nombre d'occurrences de `<nom>.(jpg|png|svg|yaml) :` (25) et nombre d'occurrences
distinctes de `oeuvre-<nom>.jpg : aucun item d'aucun paquet servi ne le declare` (25). C'est la dette
que `DEC-0108` assume jusqu'a `T6`, a l'identique de mon avis.

**Tests de `com.recreo.archi`** : **214 tests, 2 echecs, 0 erreur**, somme des attributs des
30 rapports `TEST-com.recreo.archi.*.xml` du premier lancement, pour 30 fichiers suivis sous
`bootstrap/src/test/java/com/recreo/archi`. Meme chiffre que l'avis.

## 2. Le compte de regles

Methode : `git grep -c -F` sur les fichiers **suivis** de `domain`, `application`, `adapters` et
`bootstrap`, motifs `static final ArchRule` et `@ArchTest`, somme des comptes par fichier, lancee
par `rtk proxy` pour que la sortie ne passe par aucun filtre, avec puis sans le pathspec
`':!**/contreexemple/**'`. `target/` et worktrees sont exclus par construction. Les deux motifs sont
comptes parce qu'une regle executee par `.check()` explicite n'a pas d'`@ArchTest`.

| Compte | `03d152b0` exclus | `6eded9d1` exclus | `6eded9d1` inclus |
|---|---|---|---|
| `static final ArchRule` | 36 | **36** | 38 |
| `@ArchTest` | 35 | **35** | 35 |

13 fichiers portent au moins un `static final ArchRule`, 12 au moins un `@ArchTest`. La sortie par
fichier est **identique ligne pour ligne** entre `03d152b0` et `6eded9d1` pour les deux motifs (`diff`
vide) : s50 n'ajoute, ne deplace ni ne retire aucune regle. C'est ce que l'avis prevoyait : aucune
des cinq stories n'appelait d'`ArchRule`.

## 3. Clean Architecture

Chemins touches, par `git show --name-only` sur chaque commit :

| Commit | Chemins |
|---|---|
| `fc6f9c16` `FRA-CE1-02` | `catalogue.yaml`, `contenu/ce1-valeur-lettre.yaml` (`adapter-content/src/main/resources`), `PaquetCe1ValeurLettreTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, un jeu fautif sous `contreexemple/`, `TexteAccentueExposeParLePortEntrantTest` (`bootstrap/src/test`) |
| `e979b575` arbitrage `SOU-CE1-R1` | deux fichiers de `squad/conformite/` |
| `c4250c2d` `DIV-CE1-R1` | `contenu/ce1-division.yaml`, `PaquetCe1DivisionTest` |
| `60dc1188` `GRC-CE1-R1` | `contenu/ce1-graphemes-complexes.yaml`, `PaquetCe1GraphemesComplexesTest` |
| `a7af90b3` `FRA-CE1-16` | `catalogue.yaml`, `contenu/ce1-homophones.yaml`, `PaquetCe1HomophonesTest`, `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, un jeu fautif sous `contreexemple/`, `TexteAccentueExposeParLePortEntrantTest` |
| `6eded9d1` `SOU-CE1-R1` | `contenu/ce1-soustractions-jusqu-a-100.yaml` |

- `git diff --name-only 03d152b0 6eded9d1 -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
  est **vide** : aucun fichier de `domain` ni d'`application`, aucune ligne de Java de production,
  aucun `pom.xml`, aucun gabarit, aucun script de CI ou d'outil. L'avis disait que les cinq stories
  ne toucheraient pas `src/main/java` : c'est reste vrai.
- Imports ajoutes par le diff Java du sprint (lignes `+import`, sortie brute) : `com.recreo.domain.content`,
  `java.io`, `java.nio.file`, `java.util`, `java.util.regex`, `org.assertj.core.api`,
  `org.junit.jupiter.api`, `org.yaml.snakeyaml`. **Aucune ligne ajoutee ne contient
  `springframework`** (`grep -c` : 0).
- Les regles qui gardent les couches (`domain` sans framework, ports d'`application` sans Spring,
  dependances vers l'interieur, mecaniques independantes du contenu) sont parmi les 212 tests verts de
  `com.recreo.archi`.

## 4. Ce que cette confirmation ne fait pas

- Elle ne prononce aucun Done pedagogique et ne ferme aucune ligne de `MATRICE-couverture.md`.
- Elle n'annonce pas la porte technique : seul le job `build` (`mvn verify`) est mesure ici, et il est
  rouge par la dette `T6` assumee.
- Elle n'ouvre aucun `ADR`, aucune regle, aucune dette, et ne modifie aucun test ni contenu.

## 5. Amendement du 2026-10-04 - confirmation sur l'arbre final `1db6348f`

### 5.1 Perimetre

- **Arbre juge** : `round-two` a **`1db6348f`**, tete verifiee par `git rev-parse HEAD` avant la
  mesure, arbre principal propre.
- **Commits relus**, message integral par `git log ce72dc03..1db6348f` : dix commits, pas les six du
  resume qui m'a ete transmis. `06508d48` (`[YAN]` rapport qualite, build rouge sur `T6`),
  `16df7b68` (`[CAM]` amendement `DEC-0108`, `T6` bloque la porte du sprint), `56b6b77c` (`[THEO]`
  trois oeuvres recuperees a la main), `43ec4411` (`[CAM]` `DEC-0109`, `T6` engage), `4ea72382`
  (`[THEO]` avis de faisabilite `S50-ART`), `75be883c` (`[CAM]` amendement `DEC-0109`, O1 retenu),
  `df135abd` (`[THEO]` A1, trois reproductions redimensionnees), `dd98cfe8` (`[NAD]` arbitrage
  prealable `S50-ART`), `e9b0c715` (`[CAM]` `S50-ART` reduit a `ART-CP-09`), `1db6348f` (`[MARC]`
  paquet `cp-decrire-une-oeuvre`, retrait O1, correction de test).
- **Provisionnement** : le reacteur est lance dans un worktree detache a `1db6348f`, hors de l'arbre
  du depot, retire apres la mesure, pour ne pas retomber sur le jar de `domain/target` tenu par un
  autre processus (section 1).
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte (`ADR-0071`) :
  20 lignes *"using Java 21.0.10"* dans le journal, aucune autre version, et 261 rapports
  `TEST-*.xml` dont la propriete `java.version` vaut `21.0.10`, aucune autre valeur.

### 5.2 Le reacteur complet, apres `clean`

```
bash outils/mvn-jdk21.sh -B clean verify
```

Lance le 2026-10-04 a 19:29:29+02:00, termine a 19:38:25+02:00, **sans** option d'ignorance des
echecs : **`BUILD SUCCESS`**, 6 modules sur 6 en `SUCCESS`, failsafe atteint.

**Comptes**, methode inchangee depuis la section 1 : somme des six blocs `Tests run:` de synthese du
journal de ce seul lancement, perimetre les six modules du reacteur. Base : la section 1 (`6eded9d1`).

| Module | Tests | Echecs | Ecart avec la section 1 |
|---|---|---|---|
| `domain` | 120 | 0 | 0 |
| `application` | 17 | 0 | 0 |
| `adapter-web` | 401 | 0 | 0 |
| `adapter-content` | 1167 | 0 | **+7** |
| `bootstrap` (surefire) | 293 | 0 | 0 |
| `bootstrap` (failsafe) | 6 | 0 | 0 |
| **total** | **2004** | **0** | **+7** |

0 erreur et 0 ignore dans chaque bloc. **Les +7 sont expliques** : le diff Java `ce72dc03..1db6348f`
ajoute 7 lignes `@Test`, toutes dans `PaquetCpDecrireUneOeuvreTest`, n'en retire aucune et ne touche
aucun `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest`. Methode : `grep -cE` sur la sortie
brute de `git diff ce72dc03 1db6348f -- '*.java'`. Le chiffre de 2004 annonce par `marc-dev` dans
`1db6348f` est retrouve par cette mesure independante.

**Tests de `com.recreo.archi`** : **214 tests, 0 echec, 0 erreur, 0 ignore**, somme des attributs
des 30 rapports `TEST-com.recreo.archi.*.xml` de ce lancement, pour 30 fichiers suivis sous
`bootstrap/src/test/java/com/recreo/archi`. Meme nombre de tests qu'en section 1 : les deux echecs de
`LIllustrationDeContenuResoutEtAucuneNeDortTest` sont passes au vert, aucun test n'a ete ajoute ni
retire dans ce paquetage.

### 5.3 Le compte de regles

Methode identique a la section 2 (`git grep -c -F`, fichiers suivis de `domain`, `application`,
`adapters`, `bootstrap`, sous `rtk proxy`, avec puis sans `':!**/contreexemple/**'`), mesuree le
2026-10-04 :

| Compte | `6eded9d1` exclus | `ce72dc03` exclus | `1db6348f` exclus | `1db6348f` inclus |
|---|---|---|---|---|
| `static final ArchRule` | 36 | 36 | **36** | 38 |
| `@ArchTest` | 35 | 35 | **35** | 35 |

La sortie par fichier est **identique ligne pour ligne** entre `ce72dc03` et `1db6348f` pour les deux
motifs (`diff` vide). Ni `S50-ART` ni les corrections (`df135abd`, retrait O1) n'ajoutent, ne
retirent ni ne deplacent une seule regle.

Les deux garde-fous de `com.recreo.archi` touches ne changent que des constantes de cliquet, pas leur
logique :

- `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest` : 25 fichiers et
  620 639 octets a `ce72dc03`, puis 28 / 692 809 (`df135abd`), puis **8 / 176 474** (`1db6348f`). Le
  plafond vaut exactement la population : 8 fichiers et 176 474 octets sous
  `*/src/main/resources/static/oeuvres/` dans le worktree de mesure, par `find -printf '%s'` et par
  `wc -c` sur les fichiers suivis, qui concordent, aucune exclusion.
- `LIllustrationDeContenuResoutEtAucuneNeDortTest` : `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` passe de
  56 a 64, les huit items du paquet. `FICHIERS_D_ILLUSTRATION` est inchange.

### 5.4 Clean Architecture

- `git diff --name-only ce72dc03 1db6348f -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**' '.github/**' 'outils/**'`
  est **vide** : aucun fichier de `domain` ni d'`application`, aucune ligne de Java de production,
  aucun `pom.xml`, aucun gabarit, aucun script de CI ou d'outil.
- Hors `squad/` et `docs/`, le diff touche 30 fichiers : `catalogue.yaml` et
  `contenu/cp-decrire-une-oeuvre.yaml` (`adapter-content/src/main/resources`), un jeu fautif sous
  `contreexemple/`, 21 reproductions sous `adapter-web/src/main/resources/static/oeuvres/` (19
  retirees, 2 ajoutees en net, la troisieme de `56b6b77c` etant entree puis retiree), et six fichiers
  Java, **tous sous `src/test/java`**.
- **Le bug corrige par `marc-dev` ne touche que les tests** :
  `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LIllustrationDUnItemServiAtteintLEcranDeMancheTest.java`
  gagne une methode `cibleAttendue` qui attend `/oeuvres/<id>.jpg` pour un identifiant prefixe
  `oeuvre-` et `/illustrations/<id>.svg` sinon. Le code de production qui pose ces deux cibles n'est
  pas modifie : c'est le test qui rattrape un comportement deja livre en s49 (`ADR-0082`, T2/T3).
- Imports ajoutes par le diff Java (lignes `+import`, sortie brute) : `com.recreo.domain.content`,
  `java.io`, `java.nio.file`, `java.util`, `java.util.regex`, `org.assertj.core.api`,
  `org.junit.jupiter.api`, `org.yaml.snakeyaml`. **Aucune ligne ajoutee ne contient
  `springframework`** (`grep -c` : 0).
- Les regles de couches sont parmi les 214 tests verts de `com.recreo.archi` (section 5.2).

### 5.5 Verdict sur l'arbre final

| Question | Verdict |
|---|---|
| Le reacteur complet est-il vert ? | **oui** : `BUILD SUCCESS`, 2004 tests, 0 echec, 0 erreur, 0 ignore, failsafe compris (5.2) |
| Les regles ArchUnit sont-elles vertes ? | **oui** : 214 tests dans `com.recreo.archi`, 214 verts (5.2) |
| Le compte de regles a-t-il change ? | **non** : 36 `static final ArchRule`, 35 `@ArchTest`, sortie par fichier identique a `ce72dc03` (5.3) |
| Un commit depuis `ce72dc03` viole-t-il la Clean Architecture ? | **non** : aucun `src/main/java`, aucun fichier de `domain` ni d'`application`, aucune ligne ajoutee contenant `springframework` (5.4) |

Ce passage, comme le premier, ne mesure que le job `build` : il n'annonce pas la porte technique,
dont les jobs `prefixes`, `chemins` et `outils` ne sont pas lances ici. Il ne prononce aucun Done
pedagogique, ne ferme aucune ligne de `MATRICE-couverture.md`, et n'ouvre aucun `ADR`, aucune regle ni
aucune dette.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas.
