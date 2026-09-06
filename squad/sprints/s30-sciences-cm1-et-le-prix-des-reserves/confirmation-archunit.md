# s30 - confirmation de la suite ArchUnit, `theo-archi`

**Arbre mesure** : `round-two` a `dbbfe129`, arbre propre, les deux lots de developpement assembles
par `sofia-sm`. **Lieu de la mesure** : mon worktree dedie `recreo-s30-theo-verif`, branche
`s30-theo-verif`, creee sur `dbbfe129` et verifiee descendante de lui avant toute ecriture. **Aucune
commande de ce fichier n'a ete lancee dans le depot principal ni dans le worktree d'un autre agent**
- c'est la regle de mesure tranchee au planning de s30 (section 6), qui generalise a toute mesure du
reacteur, complete ou partielle, ce qui n'etait ecrit jusque-la que pour la premiere mesure d'un
sprint. `yanis-qa` travaille en parallele sur `recreo-s30-yanis`.

**Machine virtuelle des tests** : JDK **21.0.10** (`java.vm.version = 21.0.10+8-LTS-217`,
`java.vm.name = Java HotSpot(TM) 64-Bit Server VM`). Source : les proprietes `java.version` et
`java.vm.version` d'un rapport surefire **produit par cette execution-ci**, pas la ligne de
diagnostic de l'enveloppe et pas l'entree de toolchain qui designe le JDK - c'est la distinction
qu'`ADR-0071` impose et que l'amendement s21 de la confirmation de s20 avait etablie a ses depens.
Le `java` du `PATH` de ce poste n'est pas ce JDK ; la commande passe par `outils/mvn-jdk21.sh`.

## 1. Porte technique - reacteur complet

```
bash outils/mvn-jdk21.sh -B clean verify
```

**BUILD SUCCESS**, le 2026-09-06, fin a 20:24:55, 2 min 35 s, six modules du reacteur en SUCCESS.

**1321 tests, 0 echec, 0 erreur, 0 ignore.**

| Module | Moteur | Tests |
|---|---|---|
| `recreo-domain` | surefire | 99 |
| `recreo-application` | surefire | 13 |
| `recreo-adapter-web` | surefire | 388 |
| `recreo-adapter-content` | surefire | 617 |
| `recreo-bootstrap` | surefire | 198 |
| `recreo-bootstrap` | failsafe | 6 |
| **Total** | | **1321** |

**Methode du 1321, et elle a deux temoins parce qu'un compte a une seule methode n'en a aucun.**
Perimetre : le reacteur complet a la racine, **pas de `-pl`, donc pas de `-am` a citer** - l'exigence
de `CLAUDE.md` sur les mesures partielles ne s'applique pas ici, le reacteur etant complet par
construction. (1) Somme des six blocs `[INFO] Results:` du journal Maven, comptage apres `clean`.
(2) Agregation independante des rapports XML de **tous** les modules du worktree - 179 fichiers
`TEST-*.xml` de `surefire-reports` et de `failsafe-reports`, somme des attributs `tests`,
`failures`, `errors`, `skipped` de la balise `<testsuite>`, methode de l'amendement s11. Les deux
rendent 1321 / 0 / 0 / 0, module par module.

**Ecart avec la mesure d'entree de `sofia-sm`** : elle mesure 1289 sur `5338794a`, avant les lots.
J'en mesure 1321 sur `dbbfe129`, apres. **+32**, apportes par les douze classes de test ajoutees ou
etendues par les deux lots (section 4). Son 1289 n'est pas recopie ici comme s'il etait le mien : il
sert de terme de comparaison, il porte son commit, et le mien porte le sien.

## 2. Suite ArchUnit seule - mesure partielle, donc `-am` et perimetre nomme

```
bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest='com.recreo.archi.*Test' \
  -Dsurefire.failIfNoSpecifiedTests=false
```

**Bloc `Results:` du journal : `Tests run: 142, Failures: 0, Errors: 0, Skipped: 0`. BUILD SUCCESS**,
le 2026-09-06 sur `dbbfe129`.

Je cite le bloc `Results:` **avant** le verdict, et non l'inverse : c'est ce que le rapport de
`yanis-qa` de s20 instruit a partir de mon propre faux vert de ce sprint-la.

**Perimetre exact, parce qu'un module mesure n'est pas le depot** : le module `bootstrap` et ses
dependances (`-am`, pose ici et pas omis), classes du paquet `com.recreo.archi` **seul**. Ce 142 ne
compte **pas** : `AssertionsWebTest` (`adapter-web`, une regle), les deux garde-fous d'architecture
de `domain` (`com.recreo.domain.archi`), ni aucun des garde-fous de balayage de contenu
d'`adapter-content`, ni `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest`
(`com.recreo.bootstrap.rendu`, 3 tests). Tous ceux-la sont verts dans le `clean verify` de la
section 1 ; ils ne sont simplement pas dans ce perimetre-ci.

**Les vingt-quatre lignes de resultat de classe pour vingt-et-une classes ne sont pas une anomalie**
: trois classes (`AbstractionsCiteesTest`, `ChargementDuContenuTest`, `LesReglesMordentTest`)
apparaissent deux fois, une fois par moteur JUnit - le moteur `archunit` pour leurs `@ArchTest`, le
moteur Jupiter pour leurs `@Test`. C'est l'une des trois causes de divergence du compte errone de
s05 ; elle est nommee ici plutot que subie. 31 (moteur ArchUnit) + 111 (moteur Jupiter) = 142.

## 3. Compte des regles ArchUnit

**33 regles `ArchRule` reelles, le 2026-09-06 sur `dbbfe129`, reparties sur 12 fichiers** - 11 dans
`bootstrap/src/test/java/com/recreo/archi/`, 1 dans
`adapters/adapter-web/src/test/java/com/recreo/adapter/web/archi/AssertionsWebTest.java`.

**Perimetre du balayage** : les fichiers `.java` du **seul** worktree `recreo-s30-theo-verif`.
Exclusions declarees : tout repertoire `target/` (classes compilees et sources recopiees), et tout
chemin ou nom de fichier contenant le segment `contreexemple`. Les huit worktrees d'agents de ce
sprint sont des repertoires **freres** du mien, hors de l'arbre balaye - deuxieme cause de
divergence de s05, elle ne joue pas ici.

**Trois methodes convergent, et je les donne toutes les trois.**

1. **Declarations de champ** : motif `static ArchRule <nom>` ou `static final ArchRule <nom>`,
   perimetre et exclusions ci-dessus - **33**.
2. **Toutes les occurrences, defalquees** : **38** lignes portent la sous-chaine `ArchRule `. Moins
   **3** dans le faux depot de contre-exemple de `LesGardeFousSontMisALEpreuveTest`
   (`bootstrap/src/test/resources/contreexemple/.../RegleContreExemple.java` : deux faux champs et
   la declaration d'une fausse classe `ArchRule` du meme nom, jamais executees comme regles). Moins
   **2** qui ne sont pas des declarations, dans ce meme fichier de garde-fou : la constante
   `DECLARATION_DE_REGLE = "ArchRule "` et un texte de message. 38 - 3 - 2 = **33**.
3. **Par les annotations, avec sa correction connue** : **32** `@ArchTest`, **plus 1** regle
   executee par un `.check()` explicite et donc non annotee -
   `aucuneClasseDeTestSurLeClasspathDeLApplicationNePorteDeStereotype` de `ScanDeComposantsTest`,
   passee deux fois au `.check()`, sur les sources puis sur la production. 32 + 1 = **33**.

**C'est exactement le piege que la consigne de ce sprint nomme** : compter les `@ArchTest` seuls
aurait annonce 32 et sous-estime d'une unite. Le piege est verifie ici, sur cet arbre, pas suppose
de memoire - il est le meme depuis `ADR-0058`, mais rien ne garantissait qu'il soit reste unique, et
c'est la methode 1 qui le dit, pas ma confiance.

**Ce que je n'ai pas compte comme regle** : les 39 appels a `.check(...)` de `LesReglesMordentTest`
mettent a l'epreuve des regles **declarees ailleurs**, sur des paquets de contre-exemple ; ils ne
declarent aucune regle neuve et ne sont donc pas comptes. La methode 1 le montre : ce fichier de 33
tests ne porte **qu'une** declaration.

### Delta du sprint - zero regle ajoutee, et c'est conforme, pas manquant

| Commit | Ce qu'il est | Declarations `ArchRule` | `@ArchTest` |
|---|---|---|---|
| `a5f2812b` | cloture de s29 | 33 | 32 |
| `339ba75c` | `ADR-0084` (REV-3), le seul commit de s30 a toucher du Java de production | 33 | 32 |
| `5914c36e` | planning de s30, avant les lots | 33 | 32 |
| `dbbfe129` | arbre assemble de s30 | 33 | 32 |

Methode : meme motif que la methode 1 ci-dessus, applique aux fichiers `.java` de chaque arbre lus
par `git show <rev>:<fichier>`, memes exclusions.

**`ADR-0084` n'ajoute aucune `ArchRule`, et son ADR l'annonce avant que je le mesure.** Sa section
"Regle executable" ecrit pourquoi - la propriete porte sur le texte servi a l'enfant et sur la
structure du document rendu, objets qu'ArchUnit ne modelise pas - et nomme l'autre mecanisme
automatique qui la garde :
`bootstrap/src/test/java/com/recreo/bootstrap/rendu/AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest.java`,
**3 tests, 0 echec** dans le `clean verify` de la section 1
(`aucuneReponseDeFinDePartieNEstOrphelineDeSaQuestion`, plus ses deux epreuves de morsure). L'ADR est
donc complet au sens de `CLAUDE.md`. **Un ADR qui aurait promis une regle ArchUnit et laisse ce
tableau a 33 serait, lui, incomplet - et c'est le tableau qui permettrait de le voir.**

**La morsure de ce garde-fou sur le materiau de production reel n'est pas mesuree ici** : elle est
verifiee ce sprint par `yanis-qa`, comme l'amendement de s10 l'exige. Un point de perimetre que je
lui dois : la population du garde-fou n'est pas ecrite dans le test, elle est derivee de
`new YamlContentPackLoader().games()`. Le catalogue servait **54** paquets a l'arbre de mon avis
(`89ff207e`), il en sert **58** a `dbbfe129`. Un denombrement de griefs superieur aux 54 de mon avis
n'est donc pas un ecart avec lui.

## 4. L'increment de contenu a-t-il coute du code ? Non - zero fichier Java de production

**Verdict : les deux lots de developpement de s30 ne touchent aucun fichier Java de production.**

**Methode de lecture, perimetre et exclusions.** Perimetre : `git diff --name-only 5914c36e
dbbfe129`, c'est-a-dire du commit de planning de `sofia-sm` a l'arbre assemble - les dix commits de
`lea-dev` et de `marc-dev`, et eux seuls. La liste des chemins est ecrite dans un fichier, puis
classee par motif de chemin sur ce fichier ; elle n'est pas comptee au fil d'un tube, parce que le
premier comptage que j'ai lance l'etait et qu'il a rendu **1** fichier sous `src/main/java` la ou il
y en a **zero** - la decoration ajoutee par l'enveloppe de shell de ce poste etait comptee comme une
ligne de resultat. Le chiffre faux etait visible ; rien ne garantissait qu'il le soit.

**24 fichiers touches, et aucun autre :**

| Famille | Nombre | Detail |
|---|---|---|
| Paquets de contenu YAML | 11 | 4 neufs de sciences CM1, 7 repris ou corriges |
| Catalogue YAML | 1 | `catalogue.yaml` |
| Java sous `src/test/java` | 12 | tests de paquet et garde-fous de contenu |
| **Java sous `src/main/java`** | **0** | |
| Autre (gabarit, CSS, script, POM, `squad/`) | 0 | |
| **Total** | **24** | |

Les quatre paquets neufs portent **11 items chacun** (motif `- enonce:` compte fichier par fichier),
et `catalogue.yaml` passe de **54 a 58 entrees**, 54 a 58 consignes. Les 10 occurrences de la
consigne generique *"Lis bien la question, puis choisis la bonne réponse."* que `DEC-0078` avait
denombrees tombent a **0**.

**Le controle qui vaut plus que le compte de fichiers** : les quatre paquets neufs n'utilisent
**aucune cle de schema absente des paquets anterieurs**. Methode : ensemble des cles YAML des quatre
fichiers neufs, moins l'union des cles de tous les paquets de `contenu/` tels qu'ils etaient a
`5914c36e` - **ensemble vide**. C'est ce qui explique le zero code au lieu de le constater : une cle
neuve aurait force le chargeur, donc du Java de production. C'est la dette 3 de `main` - neuf
familles de chargeurs quasi identiques - qui ne se reforme pas, mesuree plutot qu'esperee.

**Le seul Java de production touche dans tout s30 est le mien**, et il ne vient pas d'un lot de
contenu : `339ba75c`, `ADR-0084` / REV-3, cinq fichiers - `PartieController` (`adapter-web`),
`BanditManchotSession`, `Verdict`, `SaisieSession`, `SaisieVerdict` (`domain`). Methode :
`git diff --name-only a5f2812b dbbfe129` filtre sur `src/main/java` donne cinq chemins, puis
attribution commit par commit sur les six commits de s30 anterieurs aux lots - les cinq sont tous
dans `339ba75c`, les cinq autres commits (`f194fe10`, `89ff207e`, `5338794a`, `10ae5193`,
`5914c36e`) n'en portent aucun. **Ce n'est pas un depassement de l'increment de contenu : c'est un
correctif de mecanique, tranche par son propre ADR avant l'ouverture des lots.**

## 5. Verdict

**Porte technique verte sur `dbbfe129`** : `bash outils/mvn-jdk21.sh -B clean verify` rend BUILD
SUCCESS, 1321 tests, 0 echec, 0 erreur, 0 ignore, sur JDK 21.0.10 rapporte par la machine virtuelle
des tests. **33 regles ArchUnit reelles**, toutes vertes, inchangees depuis la cloture de s29.

Le Done **pedagogique** ne m'appartient pas : il revient a `nadia-prof`.
