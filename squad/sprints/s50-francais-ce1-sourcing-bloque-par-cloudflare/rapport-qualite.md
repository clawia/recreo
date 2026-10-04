# Rapport qualite de cloture - s50

Ce fichier porte deux mesures distinctes, l'une ne remplace pas l'autre : la partie A est la
mesure finale, qui prononce le verdict de ce rapport ; la partie B est la mesure precedente,
conservee telle quelle pour memoire plutot qu'ecrasee (regle de gouvernance de `CLAUDE.md`).

---

# Partie A - Mesure finale, HEAD actuelle de `round-two`

**Auteur** : `yanis-qa`. **Date** : 2026-10-04 (second passage, posterieur a la partie B). **Arbre
mesure** : `round-two` a `1ca58eb1` (hash complet `1ca58eb11584c1ed2e639c3eed97b92f74afafa7`,
confirme par `git rev-parse HEAD`). Mesure sur le checkout principal, pas de worktree dedie.
`git status --porcelain` rendu vide avant le lancement de chaque job et apres l'ecriture de ce
rapport.

**Perimetre de cette mesure** : les 21 commits de s50, `03d152b0` a `1ca58eb1` inclus (borne basse
= `03d152b0^`, soit `1dc095ea`, commit d'ouverture - meme convention que la partie B). Douze
commits de plus que la partie B (qui s'arretait a `6eded9d1`, 9 commits) : `ce72dc03` (confirmation
ArchUnit, `[THEO]`), `06508d48` (mon propre rapport precedent, `[YAN]`, celui de la partie B),
`16df7b68`, `56b6b77c`, `43ec4411`, `4ea72382`, `75be883c`, `df135abd`, `dd98cfe8`, `e9b0c715`,
`1db6348f`, `1ca58eb1`. Les neuf premiers commits de ce lot (`03d152b0` a `6eded9d1`) sont ceux
deja decrits en partie A, section "Contenu du lot" - non repetes ici.

Contenu du lot ajoute par rapport a la partie B : la reouverture de la dette `T6` en seconde
instance par `DEC-0109` - trois oeuvres recuperees a la main (`56b6b77c`, `[THEO]`), deux avis de
faisabilite (`10198d28` deja compte en partie B, `4ea72382`, `[THEO]`), un arbitrage prealable de
`nadia-prof` (`dd98cfe8`, `[NAD]`), la reduction du vivier a huit oeuvres (`75be883c`, `e9b0c715`,
`[CAM]`), le redimensionnement de trois reproductions (`df135abd`, `[THEO]`), et la livraison du
paquet `cp-decrire-une-oeuvre` (`ART-CP-09`, `1db6348f`, `[MARC]`) qui declare enfin les
identifiants d'oeuvre que la dette `T6` attendait. S'y ajoutent deux rapports de confirmation
ArchUnit (`ce72dc03`, `1ca58eb1`, `[THEO]`) et mon propre rapport de la partie B (`06508d48`,
`[YAN]`).

## 1. Les quatre jobs de la CI, un par un (`.github/workflows/ci.yml`), relus avant chaque mesure

### `prefixes`

Deux garde-fous testes avant application, commande identique a la partie B :
`bash .github/scripts/verifier-prefixes-test.sh` -> 15 cas, tous verts ; `bash
.github/scripts/commit-atteignable-test.sh` -> 5 cas, tous verts.

Puis, sur les 21 commits : `git log --no-merges --format=%s 03d152b0^..1ca58eb1 | bash
.github/scripts/verifier-prefixes.sh` -> `"Tous les commits portent un prefixe d'agent connu."`
Liste verifiee contre `CLAUDE.md` (section Regles cles) : les 9 commits de la partie B
(`[CAM]`, `[THEO]`, `[CAM]`, `[MARC]`, `[NAD]`, `[LEA]`, `[LEA]`, `[MARC]`, `[LEA]`), puis
`ce72dc03` = `[THEO]`, `06508d48` = `[YAN]`, `16df7b68` = `[CAM]`, `56b6b77c` = `[THEO]`,
`43ec4411` = `[CAM]`, `4ea72382` = `[THEO]`, `75be883c` = `[CAM]`, `df135abd` = `[THEO]`,
`dd98cfe8` = `[NAD]`, `e9b0c715` = `[CAM]`, `1db6348f` = `[MARC]`, `1ca58eb1` = `[THEO]`. 21
commits, 21 prefixes conformes.

**Verdict : vert.**

### `chemins`

Garde-fou teste avant application : `bash .github/scripts/verifier-chemins-test.sh` -> 22 cas,
tous verts.

Perimetre de cette mesure : les fichiers touches par les 21 commits de s50 (`git diff-tree
--no-commit-id --name-only -r 03d152b0^..1ca58eb1`, dedupliques), pas l'ensemble du depot - meme
perimetre plus etroit que celui du job CI reel, deja nomme en partie B. 55 fichiers (les 20 de la
partie B, le reste ajoute par le chantier `T6` : un registre de corpus, deux decisions `DEC-0109`,
une matrice de couverture, une competence de programme, un arbitrage prealable, deux avis de
faisabilite, 17 fichiers `.jpg` sous `static/oeuvres/`, trois fichiers de test ArchUnit/bootstrap,
le paquet de contenu `cp-decrire-une-oeuvre` et son test, et le fichier de confirmation ArchUnit de
`1ca58eb1`). Commande : `git diff-tree --no-commit-id --name-only -r 03d152b0^..1ca58eb1 | sort -u
| bash .github/scripts/verifier-chemins.sh` -> `"Aucun chemin de poste de travail ni nom de compte
dans les fichiers verifies."`

**Verdict : vert, sur le perimetre des fichiers touches par s50** (pas une preuve sur le reste du
depot, non remis en cause depuis s49).

### `outils`

Boucle rejouee a l'identique (trois motifs `outils/*-test.sh`, `squad/outils/*/*-test.sh`,
`outils/*-test.py`) : 9 juges purs executes, tous verts, sortie finale `"9 juge(s) pur(s)
execute(s)."`, code de sortie 0. Memes neuf juges qu'en partie B, aucun nouveau juge ajoute par ce
lot.

**Verdict : vert.**

### `build`

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet, lancee le 2026-10-04 sur
l'arbre a `1db6348f` (`git status --porcelain` vide, HEAD confirme avant le lancement). Un
treizieme commit (`1ca58eb1`, `[THEO]`, documentaire seul) a ete pousse par un autre agent de la
squad 33 secondes apres la fin de ce lancement (`Finished at: 2026-10-04T19:42:38+02:00` dans le
journal de build, horodatage `19:43:11` du commit) - aucun chevauchement avec la mesure. Son diff
ne touche qu'un seul fichier, `squad/sprints/s50-francais-ce1-sourcing-bloque-par-cloudflare/confirmation-archunit.md`
(123 lignes ajoutees, confirme par `git show --stat 1ca58eb1`) : aucun `src/main`, aucun
`src/test`, aucun `pom.xml`. Le verdict de build mesure a `1db6348f` vaut donc inchange pour
`1ca58eb1`, l'etat final de HEAD.

**Resultat** : `BUILD SUCCESS`. Reacteur complet, cinq modules reels plus le parent, tous
`SUCCESS` :

| Module | Resultat | Duree |
|---|---|---|
| recreo (parent) | SUCCESS | 0,648 s |
| recreo-domain | SUCCESS | 17,227 s |
| recreo-application | SUCCESS | 5,221 s |
| recreo-adapter-web | SUCCESS | 1 min 40 s |
| recreo-adapter-content | SUCCESS | 1 min 21 s |
| recreo-bootstrap | SUCCESS | 5 min 38 s |

Temps total du reacteur : 9 min 04 s.

**Zero echec, confirme en relisant le journal entier, pas en faisant confiance au mot
`SUCCESS` seul** : `grep -E "Tests run:.*Failures: [1-9]|Tests run:.*Errors: [1-9]"` sur le journal
complet de ce lancement ne rend aucune ligne (272 lignes `Tests run:` au total, aucune avec un
echec ou une erreur). En particulier, la classe qui portait les deux echecs de la dette `T6` en
partie B, `com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest`, rend maintenant
`Tests run: 18, Failures: 0, Errors: 0` : le paquet `cp-decrire-une-oeuvre` livre par `1db6348f`
declare les identifiants d'oeuvre que cette classe reclamait, et ferme la dette constatee en
partie B.

`maven-failsafe-plugin` s'execute cette fois jusqu'au bout (le reacteur ne s'arrete plus a la
phase `test` de `recreo-bootstrap`) : les six tests d'integration Spring Boot habituels
(`CatalogueChargeableIT`, `PropositionsAccentueesJouablesIT`, `JarRecreoIT`,
`LeLivrableNeSertQueLesRessourcesDeSesModulesIT` x3) s'executent et rendent tous `Tests run: 6,
Failures: 0, Errors: 0`.

JDK constate, pas devine : la banniere de demarrage Spring Boot de chaque test rapporte `Started
... using Java 21.0.10` - la version que la machine virtuelle des tests elle-meme annonce. Le
`java` du `PATH` de ce poste rapporte une version differente (21.0.12.1) ; c'est la version de
compilation et d'execution des tests ci-dessus qui fait foi, conformement a l'amendement de
`sofia-sm` (s25, lot F) cite dans `CLAUDE.md`.

**Verdict : vert.**

## 2. Compte de tests, methode

Methode (amendement s11) : somme de l'attribut `tests="..."` des rapports XML de
surefire/failsafe sous chaque `target/`, apres le `clean verify` ci-dessus, sur l'arbre a
`1db6348f` (`1ca58eb1` n'ajoute aucun fichier Java ni YAML, voir section 1).

| Module | Tests run (surefire) | Tests run (failsafe) |
|---|---|---|
| recreo-domain | 120 | - |
| recreo-application | 17 | - |
| recreo-adapter-web | 401 | - |
| recreo-adapter-content | 1167 | - |
| recreo-bootstrap | 293 | 6 |

**2004 tests au total (1998 unitaires + ArchUnit, 6 d'integration), 0 echec, 0 erreur.** Depuis la
mesure de la partie B (1991 unitaires, 0 d'integration execute) : +7 unitaires dans
`recreo-adapter-content` (le nouveau `PaquetCpDecrireUneOeuvreTest` du paquet `ART-CP-09`,
`1167 - 1160`), et les 6 tests d'integration qui existaient deja dans le depot s'executent pour la
premiere fois depuis que la dette `T6` est levee - ce ne sont pas des tests nouveaux, c'est une
execution qui etait mecaniquement empechee par l'echec de phase de la partie B.

## 3. Tests instables

Aucun constate sur cette mesure. Le `build` a ete lance une seule fois pour produire ce verdict
(contrairement a la partie B, qui avait du relancer une fois pour un verrou de fichier transitoire
sans rapport avec le code) - un lancement unique n'est pas qualifie de stable pour autant, et aucun
des quatre jobs n'a ete relance dans l'espoir d'un meilleur resultat.

## 4. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique. Il ne modifie et ne ferme aucune ligne de
`squad/programme/MATRICE-couverture.md`. Il n'etablit aucun total de depot au-dela de `1ca58eb1`
(`sofia-sm` etablit le total sur l'arbre fusionne).

---

## Decisions prises (partie A)

- **Les quatre jobs sont verts**, chacun reproduit individuellement avec la commande exacte du
  workflow, sur le perimetre complet des 21 commits de s50 jusqu'a `1ca58eb1`.
- **Le `build` est passe du rouge (partie B, dette `T6`) au vert** : la livraison du paquet
  `cp-decrire-une-oeuvre` (`1db6348f`, `[MARC]`) ferme exactement les deux echecs nommes en partie
  B, confirme en relisant le journal complet plutot qu'en supposant.
- **Le Done technique de s50 est prononce vert.** C'est mon mandat seul : `CLAUDE.md` definit le
  Done technique comme la CI verte dans son ensemble, et les quatre jobs le sont, mesures un par un
  sur l'etat final de HEAD.
- **Aucune ligne de `MATRICE-couverture.md` n'est touchee par ce rapport** et aucun Done
  pedagogique n'y est prononce : hors mandat de `yanis-qa`. La livraison de `cp-decrire-une-oeuvre`
  reste soumise a l'arbitrage pedagogique de `nadia-prof`, independamment de ce verdict technique.

---

**Conclusion (partie A) : les quatre jobs de la CI sont verts** (`prefixes`, `chemins`, `outils`,
`build`), chacun reproduit localement avec la commande exacte lue dans
`.github/workflows/ci.yml`, sur les 21 commits de s50 (`03d152b0` a `1ca58eb1`). `build` rend
`BUILD SUCCESS`, 2004 tests executes, 0 echec, 0 erreur - confirme en relisant le journal complet,
pas en reprenant l'annonce d'un autre agent. **La porte technique de s50 est verte. Le Done
technique du sprint est prononce.**

---

# Partie B - Mesure precedente (2026-10-04, premier passage, arbre a `6eded9d1`)

Conservee telle qu'ecrite, non modifiee. Supplantee par la partie A pour tout ce qui concerne le
verdict `build` et le Done technique du sprint.

**Auteur** : `yanis-qa`. **Date** : 2026-10-04. **Arbre mesure** : `round-two` a `6eded9d1`
(hash complet `6eded9d153bbbec20228fe0b8f44b8f2b024d832`, confirme par `git rev-parse HEAD` au debut
de la mesure). Mesure sur le checkout principal, pas de worktree dedie : aucune mutation de
materiau de production n'etait prevue, seule une mesure. `git status --porcelain` rendu vide avant
d'ecrire ce rapport.

**Perimetre de ce rapport** : les neuf commits de s50, `03d152b0` a `6eded9d1` inclus (borne basse
= `03d152b0`, demandee explicitement) - `03d152b0`, `10198d28`, `ed1ced11`, `fc6f9c16`, `e979b575`,
`c4250c2d`, `60dc1188`, `a7af90b3`, `6eded9d1`. Base de comparaison pour "qui a change quoi" :
`03d152b0^` (parent de `03d152b0`, soit `1dc095ea`, commit d'ouverture).

Contenu du lot : deux lignes neuves de francais CE1 sous `DEC-0108` - `FRA-CE1-02` (`[MARC]`,
`fc6f9c16`, nouveau paquet `ce1-valeur-lettre`) et `FRA-CE1-16` (`[MARC]`, `a7af90b3`, nouveau
paquet `ce1-homophones`) - trois corrections differees (`GRC-CE1-R1` `[LEA]` `60dc1188`,
`DIV-CE1-R1` `[LEA]` `c4250c2d`, `SOU-CE1-R1` `[LEA]` `6eded9d1`), l'arbitrage `nadia-prof` qui les
a conditionnees (`[NAD]` `e979b575`), un avis de faisabilite (`[THEO]` `10198d28`) et deux
corrections de gouvernance sans `.java` ni `.yaml` de contenu (`[CAM]` `03d152b0`, `[CAM]`
`ed1ced11`).

---

## 1. Les quatre jobs de la CI, un par un (`.github/workflows/ci.yml`)

Chaque job reproduit localement avec la commande exacte lue dans le workflow, aucun deduit.

### `prefixes`

Deux garde-fous testes avant application :
`bash .github/scripts/verifier-prefixes-test.sh` -> 15 cas, tous verts ; `bash
.github/scripts/commit-atteignable-test.sh` -> 5 cas, tous verts.

Puis, sur le perimetre de s50 : `git log --no-merges --format=%s 03d152b0^..6eded9d1 | bash
.github/scripts/verifier-prefixes.sh` -> `"Tous les commits portent un prefixe d'agent connu."`
Verifie nommement contre `CLAUDE.md` (section Regles cles) : `03d152b0` = `[CAM]`, `10198d28` =
`[THEO]`, `ed1ced11` = `[CAM]`, `fc6f9c16` = `[MARC]`, `e979b575` = `[NAD]`, `c4250c2d` = `[LEA]`,
`60dc1188` = `[LEA]`, `a7af90b3` = `[MARC]`, `6eded9d1` = `[LEA]`. Neuf commits, neuf prefixes
conformes.

**Verdict : vert.**

### `chemins`

Garde-fou teste avant application : `bash .github/scripts/verifier-chemins-test.sh` -> 22 cas,
tous verts.

Perimetre de cette mesure : les fichiers touches par les neuf commits de s50 (`git diff-tree
--no-commit-id --name-only -r 03d152b0^..6eded9d1`, dedupliques), pas l'ensemble du depot - c'est
le perimetre demande pour cette ceremonie, plus etroit que celui du job CI reel qui balaie
`git ls-files` sur tout le depot a chaque push. 20 fichiers : 5 YAML de contenu, 1 YAML de
catalogue, 5 fichiers de test Java, 2 YAML de contre-exemple, 1 fichier de documentation
(`docs/backlog/backlog.md`) et 6 fichiers de gouvernance (`squad/...`). Commande : `git diff-tree
--no-commit-id --name-only -r 03d152b0^..6eded9d1 | sort -u | bash
.github/scripts/verifier-chemins.sh` -> `"Aucun chemin de poste de travail ni nom de compte dans
les fichiers verifies."`

**Verdict : vert, sur le perimetre des fichiers touches par s50** (pas une preuve sur le reste du
depot, non remis en cause depuis s49).

### `outils`

Boucle rejouee a l'identique (trois motifs `outils/*-test.sh`, `squad/outils/*/*-test.sh`,
`outils/*-test.py`) : 9 juges purs executes, tous verts, sortie finale `"9 juge(s) pur(s)
execute(s)."`, code de sortie 0.

**Verdict : vert.**

### `build`

Commande : `bash outils/mvn-jdk21.sh -B clean verify`, reacteur complet.

Premier lancement : faux rouge d'environnement, sans rapport avec le code - `maven-clean-plugin`
a echoue sur `recreo-domain` ("Le processus ne peut pas acceder au fichier car ce fichier est
utilise par un autre processus" sur `recreo-domain-0.1.0-SNAPSHOT.jar"), aucun processus Java
trouve verrouillant ce fichier au moment du controle (`tasklist` vide sur `java`/`mvn`). Relance
immediate sans aucune autre action : le verrou n'etait plus pose, `clean` a reussi sur les cinq
modules. Ce premier lancement n'est pas compte comme mesure et n'entre dans aucun chiffre de ce
rapport.

Second lancement (celui qui compte) : `BUILD FAILURE`, reacteur arrete au module
`recreo-bootstrap`, a la phase `test` (avant la phase `verify` ou s'executerait
`maven-failsafe-plugin`). Verdict des quatre premiers modules : `recreo-domain`, `recreo-application`,
`recreo-adapter-web`, `recreo-adapter-content` tous `SUCCESS`.

**Les deux echecs sont exactement les deux attendus, rien d'autre** : `grep` sur le journal
complet pour tout `Tests run:` portant `Failures: [1-9]` ou `Errors: [1-9]` ne rend que deux
lignes, toutes deux dans `com.recreo.archi.LIllustrationDeContenuResoutEtAucuneNeDortTest` -
`chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort` et
`unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste`. Le message lu en entier :
`ADR-0073 : [oeuvre-amerique-ancienne.jpg : aucun item d'aucun paquet servi ne le declare. ...]`,
liste de 25 noms de fichiers `oeuvre-*.jpg`, chacun avec le meme motif - "une reproduction que
personne ne montre est du poids mort dans le fat jar" (`ADR-0073` clause 2, `ADR-0082` decision 2).
C'est exactement la dette `T6` nommee et assumee par `DEC-0108` section 1 : les 25 oeuvres du
chantier de sourcing (`T2`-`T4`, deja clos par `90c36ab0` et `8f6ee008`, tous deux anterieurs au
perimetre de s50) sont entrees au registre et au repertoire `static/oeuvres/`, mais aucun paquet de
contenu ne declare encore un identifiant `oeuvre-` - cette fermeture est `T6`, hors perimetre de
s50 (`DEC-0108` section 2, bloque par un defi Cloudflare chez le fournisseur, pas par la squad).
Aucun des neuf commits de s50 ne touche a `ADR-0082`, au registre d'oeuvres ni a `static/oeuvres/` :
confirme par la liste des fichiers touches en section 0 (aucun `.jpg`, aucun fichier sous
`static/oeuvres/`, aucun fichier sous `squad/corpus/`).

**Consequence non negligeable de cet echec de phase, a nommer plutot qu'a passer sous silence** :
parce que le reacteur s'arrete a la phase `test` de `recreo-bootstrap`, **`maven-failsafe-plugin`
ne s'execute pas du tout sur cette mesure** - aucun `bootstrap/target/failsafe-reports/` n'est
produit (verifie, repertoire absent). Les tests d'integration Spring Boot que les sprints
precedents comptaient (6 en s49) ne sont ni executes ni verifies ici, par effet mecanique de la
dette connue, pas par une exclusion volontaire ni par une regression nouvelle.

**Verdict : rouge, et c'est le rouge attendu** - exactement les deux echecs de la dette `T6`
assumee par `DEC-0108`, rien d'autre. Le job CI reel (`mvn -B verify`, sans le wrapper JDK)
rendrait le meme verdict sur ce meme arbre.

JDK constate, pas devine : la banniere de demarrage Spring Boot de chaque test d'integration
rapporte `Started ... using Java 21.0.10` - c'est la version que la machine virtuelle des tests
elle-meme annonce, pas une lecture de la toolchain. `outils/verifier-jdk.sh` rapporte en parallele :
`java` du `PATH` 21.0.12.1, JDK de compilation designe par la toolchain 21.0.10 - deux JDK 21
distincts sur ce poste, et c'est le second qui a compile et execute les tests ici.

## 2. Compte de tests, methode et limite de cette mesure

Methode (amendement s11) : somme de l'attribut `tests="..."` des rapports XML de
surefire/failsafe sous chaque `target/`, apres le `clean verify` qui a produit l'echec ci-dessus.

| Module | Tests run (surefire) |
|---|---|
| recreo-domain | 120 |
| recreo-application | 17 |
| recreo-adapter-web | 401 |
| recreo-adapter-content | 1160 |
| recreo-bootstrap (surefire, unitaires + ArchUnit) | 293 |
| recreo-bootstrap (failsafe, integration) | **non execute** (voir section 1) |

**1991 tests unitaires executes, 0 test d'integration execute sur cette mesure** (le reacteur ne
remonte jamais jusqu'a la phase `verify` qui lancerait `failsafe`). Ce n'est pas un recul de
couverture : les six tests d'integration existent toujours dans le depot, ils ne tournent
simplement pas dans ce `clean verify`-la, a cause de l'echec de phase anterieur.

**+22 tests unitaires depuis la cloture de s49** (1969 -> 1991), decompose par fichier (`@Test`
compte sur le fichier entier a `03d152b0^` puis a `6eded9d1`, pas sur le diff isole) :

- `PaquetCe1HomophonesTest` (`FRA-CE1-16`, `a7af90b3`, nouveau fichier) : 0 -> 9, soit **+9**.
- `PaquetCe1ValeurLettreTest` (`FRA-CE1-02`, `fc6f9c16`, nouveau fichier) : 0 -> 11, soit **+11**.
- `PaquetCe1GraphemesComplexesTest` (`GRC-CE1-R1`, `60dc1188`) : 13 -> 14, soit **+1**.
- `PaquetCe1DivisionTest` (`DIV-CE1-R1`, `c4250c2d`) : 7 -> 8, soit **+1**.
- `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest`, touche par `a7af90b3` et `fc6f9c16` :
  inchange (28 -> 28) - generalisation d'un test existant aux deux nouveaux paquets, pas de
  methode neuve.
- `TexteAccentueExposeParLePortEntrantTest`, touche par `fc6f9c16` : inchange (5 -> 5) - meme
  motif.
- `6eded9d1` (`SOU-CE1-R1`) ne touche qu'un fichier YAML de contenu, aucun fichier de test : **+0**
  confirme (`git show --stat 6eded9d1`).

9 + 11 + 1 + 1 = **22**, exactement l'ecart constate, sans reste. Repartition par module : tout
dans `adapter-content` (1138 -> 1160), `bootstrap` inchange a 293.

Les commits `03d152b0`, `10198d28` et `ed1ced11` ne touchent aucun `.java` ni `.yaml` de contenu
(`docs/backlog/backlog.md` et des fichiers `squad/` seulement) - confirme par `git show --stat` sur
chacun. Ils n'entrent pour rien dans ce delta.

## 3. Tests instables

Aucun constate. Le `build` a ete lance deux fois sur cet arbre ; la premiere execution a echoue
pour une cause d'environnement (verrou de fichier transitoire sur `recreo-domain`, voir section 1),
pas pour une cause liee au code ou aux tests - ce n'est pas un test instable, c'est un
environnement de build qui a change d'etat entre les deux lancements sans aucune intervention de ma
part. La seconde execution, celle qui compte, n'a ete lancee qu'une fois : un lancement unique
n'est pas qualifie de stable pour autant.

## 4. Ce que ce rapport ne fait pas

Il ne prononce aucun Done pedagogique. Il ne modifie et ne ferme aucune ligne de
`squad/programme/MATRICE-couverture.md`. Il n'etablit aucun total de depot au-dela de `6eded9d1`
(`sofia-sm` etablit le total sur l'arbre fusionne). Il ne revérifie pas le perimetre `chemins` au
dela des fichiers touches par s50 (section 1), ce perimetre plus etroit que le job CI reel est
nomme, pas dissimule.

---

## Decisions prises

- **Le `build` est rouge, et c'est le rouge attendu** : les deux seuls echecs sont ceux de la dette
  `T6` assumee par `DEC-0108`, confirmes en lisant le message d'echec en entier plutot qu'en les
  absorbant sur la foi du nom de classe seul. Aucun autre echec, aucune regression nouvelle
  introduite par les neuf commits de s50.
- **`prefixes`, `chemins`, `outils` sont verts**, reproduits individuellement avec la commande
  exacte du workflow.
- **Le Done technique de s50 n'est pas prononce vert** : un job sur quatre est rouge, et
  `CLAUDE.md` definit le Done technique comme la CI verte dans son ensemble, pas comme trois jobs
  sur quatre. Ce rouge est une dette deja nommee et actee par `camille-po` (`DEC-0108`), pas un
  obstacle nouveau a router.
- **La consequence de cette dette sur les tests d'integration est signalee explicitement** (section
  1) : `maven-failsafe-plugin` ne s'execute pas tant que l'echec de phase `test` de
  `recreo-bootstrap` n'est pas leve, donc les six tests d'integration Spring Boot habituels ne sont
  pas executes par cette mesure. Ce n'est pas routable comme un obstacle qualite autonome : c'est
  le meme fil que `T6`, deja route par `DEC-0108` section 2.
- **Aucune ligne de `MATRICE-couverture.md` n'est touchee par ce rapport** et aucun Done
  pedagogique n'y est prononce : hors mandat de `yanis-qa`.

---

**Conclusion** : trois jobs sur quatre verts (`prefixes`, `chemins`, `outils`), reproduits
localement un par un. Le quatrieme (`build`) est rouge sur exactement les deux echecs de la dette
`T6` deja nommee et assumee par `DEC-0108`, rien d'autre - confirme en lisant le message d'echec en
entier, pas en le supposant. 1991 tests unitaires executes (+22 depuis s49), l'integralite de
l'ecart expliquee par quatre fichiers sans reste ; 0 test d'integration execute sur cette mesure,
par effet mecanique de l'echec de phase anterieur, signale plutot que tu. **La porte technique de
s50 n'est pas verte : un job sur quatre (`build`) est rouge, pour la dette deja connue et assumee
`T6`, hors perimetre de developpement de s50.**
