# Confirmation ArchUnit - s45 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-01.
- **Objet** : confirmer par ma propre mesure, sur l'arbre final de `round-two` une fois les quatre
  lots de s45 fusionnes, qu'aucune regle neuve ni aucun `ADR` n'est du au-dela d'`ADR-0103`, et
  trancher l'ecart de compte de regles signale par `yanis-qa` sur le rapport de s44. Le rapport de
  `yanis-qa` (`rapport-qualite.md`, fusion `8bf88702`) n'est repris nulle part comme preuve.
- **Perimetre mesure** : `round-two` a **`8bf88702`**, branche dediee `s45-theo-confirmation`
  creee depuis ce commit dans le checkout principal, `git status` vide avant la mesure. **Bases de
  comparaison** : `00c9139b` (fusion du dernier lot de s44, le commit que `yanis-qa` remesure,
  ancetre de `048118bc` que mesurait ma confirmation de s44 et identique a lui hors `squad/` :
  `git diff --name-only 048118bc 00c9139b` ne rend que des fichiers sous `squad/`) et `5ef0ac7f` (dernier commit avant le premier lot code, apres la fusion d'`ADR-0103`).
- **Version** : **JDK 21.0.10**, telle que la machine virtuelle des tests la rapporte d'elle-meme
  (`ADR-0071`) : 20 lignes *"using Java 21.0.10"* dans le journal du reacteur, aucune autre version.

---

## 0. Verdict, en une table

| Question posee | Verdict |
|---|---|
| 1. Combien de regles ArchUnit sur `round-two` ? | **36 `static final ArchRule`, 35 `@ArchTest`**, arbres `contreexemple` **exclus**. Avec eux : 38 et 35. Identique a `00c9139b`, fichier par fichier (section 1) |
| 2. Le 36 de s44 est-il faux ? | **non**. Le 38 de `yanis-qa` compte deux declarations d'un fichier de donnees jamais compile. Le rapport de s44 donnait deja les deux chiffres et sa methode. C'est le rapport qualite de s45 qui est a amender, pas celui de s44 (section 2) |
| 3. `mvn -B clean verify` est-il vert sur ma propre mesure ? | **oui** : `BUILD SUCCESS`, 6 modules sur 6, **1850 tests, 0 echec, 0 erreur, 0 ignore** (section 3) |
| 4. Un `ADR` est-il du au-dela d'`ADR-0103` ? | **non** : aucun `src/main/java`, aucun `pom.xml`, aucune cle de schema neuve, aucune mecanique neuve, aucune matiere neuve (section 4) |
| 5. Les quatre paquets respectent-ils la Clean Architecture ? | **oui** : aucun fichier de `domain` ni d'`application` touche, aucun import `org.springframework` ajoute (section 5) |

**Ce que cette confirmation ne fait pas** : elle ne prononce aucun Done pedagogique et n'annonce pas
la porte technique du sprint. `mvn verify` n'est que le job `build` ; les trois autres jobs sont
lances a part en section 6, sur mon diff.

## 1. Le compte de regles, avec sa methode

Methode : `git grep -c -F` sur les fichiers **suivis** de `domain`, `application`, `adapters` et
`bootstrap`, motifs `static final ArchRule` et `@ArchTest`, somme des comptes par fichier, lancee
deux fois par arbre : une fois **avec** le pathspec d'exclusion `':!**/contreexemple/**'`, une fois
**sans**. Commande lancee par `rtk proxy git grep`, pour que la sortie ne passe par aucun filtre.

| Compte | `00c9139b` exclus | `00c9139b` inclus | `8bf88702` exclus | `8bf88702` inclus |
|---|---|---|---|---|
| `static final ArchRule` | 36 | 38 | **36** | 38 |
| `@ArchTest` | 35 | 35 | **35** | 35 |

Fichiers portant au moins une occurrence, `contreexemple` exclus : 13 pour `ArchRule`, 12 pour
`@ArchTest` (`ScanDeComposantsTest` declare une `ArchRule` sans `@ArchTest`), 13 en union. La sortie
par fichier de `git grep -c` est **identique ligne pour ligne** entre `00c9139b` et `8bf88702` pour
les deux motifs (`diff` vide) : s45 n'ajoute, ne deplace ni ne retire aucune regle.

## 2. L'ecart 36 / 38 : une methode, pas une erreur du rapport de s44

**Ou vivent les deux `ArchRule` de l'ecart** :
`bootstrap/src/test/resources/contreexemple/LesGardeFousSontMisALEpreuveTest/faux-module/src/test/java/com/recreo/faux/RegleContreExemple.java`,
lignes 13 et 15. Ce fichier est **une donnee lue par `LesGardeFousSontMisALEpreuveTest`, jamais
compile** (sa Javadoc le dit, et il vit sous `src/test/resources`). Ses deux `static final ArchRule`
sont d'un type `ArchRule` local au fichier, sans rapport avec ArchUnit. Ce ne sont pas des regles
du depot. **Le compte des regles est 36.**

**Pourquoi `yanis-qa` obtient 38.** Sa methode, ecrite dans son rapport (section 1), compte les
occurrences *"sous `src/test/java` des cinq modules, `target/` exclu"*. Le faux module reproduit
l'arborescence d'un vrai module : son chemin contient lui-meme `src/test/java`, sous
`src/test/resources`. Un filtre sur `src/test/java` ne l'exclut donc pas : `git grep -c` filtre par
`'*/src/test/java/*'` rend **38** sur `8bf88702`, le meme chiffre que sans aucune exclusion. Son
chiffre est reproductible et sa methode est fidelement ecrite. C'est la methode qui ne mesure pas
ce qu'elle annonce.

**Le rapport de s44 n'est pas en tort.** Sa section 2 ecrit sa methode (exclusion
`':!**/contreexemple/**'`), donne **36** et, dans une colonne *"Sans l'exclusion"*, **38**, avec le
fichier qui porte l'ecart. Remesure ici a `00c9139b` : 36 et 38, comme il l'ecrivait. **Aucun
amendement du rapport de s44 n'est du.**

**Ce qui est du, par un futur geste et pas par moi.** La section 1 de
`squad/sprints/s45-addition-musique-eps-et-numerique-cm1/rapport-qualite.md` affirme que *"le
chiffre de 36 cite au rapport de s44 ne resiste pas a une remesure sur son propre commit"* et
demande que la serie reparte de 38. Les deux phrases sont a amender par `yanis-qa`, qui en est
l'auteur : la serie repart de **36**, `contreexemple` exclus. Le reste de son rapport n'est pas
touche par cet ecart, et aucune de ses autres conclusions ne depend de ce chiffre.

**Ce qui empecherait le prochain ecart.** Tout compte de regles cite dans un fichier commite porte
son pathspec d'exclusion en entier. Un filtre par nom de repertoire (`src/test/java`) ne suffit pas
dans ce depot, parce que les fixtures de modules en reproduisent l'arborescence.

## 3. `mvn verify`, la commande et sa sortie

```
bash outils/mvn-jdk21.sh -B clean verify
```

Reacteur complet depuis la racine : aucune mesure partielle, aucune question de `-am`.

```
Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10
[INFO] recreo ............................................. SUCCESS [  0.588 s]
[INFO] recreo-domain ...................................... SUCCESS [ 13.921 s]
[INFO] recreo-application ................................. SUCCESS [  5.480 s]
[INFO] recreo-adapter-web ................................. SUCCESS [ 46.882 s]
[INFO] recreo-adapter-content ............................. SUCCESS [ 40.865 s]
[INFO] recreo-bootstrap ................................... SUCCESS [02:23 min]
[INFO] BUILD SUCCESS
[INFO] Total time:  04:11 min
[INFO] Finished at: 2026-10-01T13:14:33+02:00
```

**Comptes, avec leur methode** : somme des six blocs `[INFO] Results:` de ce lancement apres
`clean` (methode autoritaire de `squad/README.md`, section 9). Perimetre : les six modules du
reacteur. Base : ma confirmation de s44 (1805, mesure a `048118bc`).

| Module | Tests | Ecart avec s44 |
|---|---|---|
| `domain` | 120 | 0 |
| `application` | 17 | 0 |
| `adapter-web` | 400 | 0 |
| `adapter-content` | 1019 | **+45** |
| `bootstrap` (surefire) | 288 | 0 |
| `bootstrap` (failsafe) | 6 | 0 |
| **total** | **1850** | **+45** |

Chaque bloc rend 0 echec, 0 erreur, 0 ignore. Le detail des +45 (3 par `ADR-0103`, 42 par les quatre
paquets) est celui du rapport qualite ; je ne l'ai pas recompte annotation par annotation, et je ne
le cite pas comme ma mesure.

**Ce qui a tourne dans `com.recreo.archi`** : les **30** classes suivies de
`bootstrap/src/test/java/com/recreo/archi` ont chacune leur rapport `TEST-*.xml` (30 fichiers), dont
la somme des attributs donne **209 tests, 0 echec, 0 erreur, 0 ignore**. Meme chiffre qu'en s44.

## 4. Aucun `ADR` n'est du : ce qui a reellement change

### 4.1 Le diff du code, hors `squad/` et `docs/`

`git diff --stat 5ef0ac7f 8bf88702 -- . ':!squad' ':!docs'` (developpement des quatre lots seul,
`ADR-0103` etant fusionne avant `5ef0ac7f`) rend 13 fichiers, 1838 insertions, 3 suppressions :
`catalogue.yaml`, les quatre paquets YAML neufs, leurs quatre tests, un jeu fautif sous
`contreexemple`, et trois tables de test transverses
(`ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest`,
`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`,
`TexteAccentueExposeParLePortEntrantTest`).

`git diff --name-only 00c9139b 8bf88702 -- domain application '*/src/main/java/**' '*pom.xml' '*/templates/**'`
est **vide** : zero ligne de Java de production, zero `pom.xml`, zero gabarit, sur tout le sprint.

### 4.2 Les trois questions, verifiees sur l'arbre final

| Ce qui aurait appele un `ADR` | Constat sur `8bf88702` | Methode |
|---|---|---|
| un champ neuf | **aucun** : les quatre paquets portent `schema`, `id`, `type`, `descripteur` (`matiere`, `niveau`, `domaine`, `competence`), `lignesDeReferentiel`, `items` et, par item, `enonce`, `bonneReponse`, `distracteurs`, `indice`. Aucune de ces cles n'est absente des paquets servis a `5ef0ac7f`. Aucun `tableauSupport` ni `illustration` | extraction des cles YAML des quatre fichiers, commentaires retires, comparee par `comm` a l'ensemble des cles des paquets de `5ef0ac7f` : difference vide ; `AucuneCleDUnPaquetServiNEstIgnoreeDeToutLeDepotTest` vert dans le reacteur |
| une mecanique neuve | **aucune** : les quatre entrees ajoutees a `catalogue.yaml` declarent `mecanique: bandit-manchot`, servie par 102 entrees a `5ef0ac7f` | diff de `catalogue.yaml` |
| une matiere geree en code | **aucune, et aucune matiere neuve** : `Mathématiques`, `Éducation musicale`, `Éducation physique et sportive` et `Sciences et technologie` sont toutes portees par au moins un paquet a `5ef0ac7f` (32, 1, 4 et 13 paquets). Aucun `src/main/java` ni gabarit n'est touche (4.1) | `git grep` des valeurs de `matiere` des paquets a `5ef0ac7f` |

Les quatre paquets declarent `schema: 1` et `type: question`, deja servis.

### 4.3 `squad/decisions/` sur le sprint

`git diff --name-status 00c9139b 8bf88702 -- squad/decisions` rend, sous `adr/`, `ADR-0103` (ajoute),
`ADR-0096` (modifie, amendement qui renvoie a `ADR-0103`) et `ADR-index.md`. Aucun des trois ne
bouge apres `5ef0ac7f` (`git diff --name-only 5ef0ac7f 8bf88702 -- squad/decisions/adr` vide) :
ils sont entres avec `ADR-0103`, avant le developpement. `ADR-0103` est le dernier `ADR` du depot et
`ADR-index.md` le porte au statut `Accepte`. Le developpement n'en appelle aucun autre.

**Verdict : aucun `ADR` n'est du au-dela d'`ADR-0103`.**

## 5. La Clean Architecture sur les quatre paquets

- **`domain` et `application`** : aucun fichier modifie entre `00c9139b` et `8bf88702` (4.1). Les
  regles qui les gardent (`domain` sans framework, ports sans Spring, dependances vers l'interieur)
  sont parmi les 209 tests verts de `com.recreo.archi`.
- **Spring hors des adapters et de `bootstrap`** : aucune classe de production neuve. Les imports
  ajoutes par le diff Java du sprint (`rtk proxy git diff 00c9139b 8bf88702 -- '*.java'`, lignes
  `+import`) se rangent en `com.recreo.domain.content`, `java.*`, `org.junit.jupiter.api`,
  `org.assertj.core.api` et `org.yaml.snakeyaml`, deja dependance d'`adapter-content`. **Aucune
  ligne ajoutee ne contient `springframework`.**
- **Decouplage mecanique et contenu** : aucune classe de `domain/mechanic` ni de `domain/mechanics`
  touchee. Les quatre paquets passent par le chargeur unique pilote par schema, sans chargeur neuf.

## 6. Les trois autres jobs de la CI, lances sur mon diff

Lances dans le checkout, ce fichier-ci indexe, avant le commit.

| Job | Commande | Resultat |
|---|---|---|
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | vert : *"Tous les cas passent"*, puis *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, ce fichier-ci inclus |
| `prefixes` | `bash .github/scripts/verifier-prefixes-test.sh`, `bash .github/scripts/commit-atteignable-test.sh`, puis le sujet du commit de ce fichier passe a `bash .github/scripts/verifier-prefixes.sh` | vert : les deux juges *"Tous les cas passent"*, puis *"Tous les commits portent un prefixe d'agent connu."* |
| `outils` | les trois motifs de juges purs (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) | vert : **9** juges purs executes (5 sous `outils/*-test.sh`, 2 sous `squad/outils/*/*-test.sh`, 2 sous `outils/*-test.py`), code de sortie 0 pour chacun, aucun motif vide |

## 7. Ce que je n'ai pas fait

- Aucun Done pedagogique, sur aucun des quatre paquets.
- Aucune annonce de la porte technique du sprint.
- Aucune modification du rapport de s44 ni du rapport qualite de s45 (section 2).
- Aucune mutation : `ADR-0103` a ete mis a l'epreuve dans les deux sens par `yanis-qa` sur le
  materiau servi ; je ne l'ai pas rejoue.
- Aucun `ADR`, aucune dette ouverte, aucune regle ecrite.

## Decisions prises

Aucune. Ce fichier constate, il ne decide pas.
