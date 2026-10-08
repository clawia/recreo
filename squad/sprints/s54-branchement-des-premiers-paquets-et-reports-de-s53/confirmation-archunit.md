# Confirmation ArchUnit - s54 (`theo-archi`)

- **Auteur** : `theo-archi`. **Date** : 2026-10-08.
- **Objet** : confirmer, pour la cloture de s54, que les regles ArchUnit sont vertes sur l'arbre fusionne, que leur compte
  n'a pas bouge et que le diff du sprint ne touche aucune frontiere ; rendre le prix en tailles de `S54-ART-CP-10-PRIX` et
  dire si T6 ou T7 appellent une regle.
- **Arbre juge** : `round-two` a **`db69bbc6`** (fusion de `s54-camille-matrice`), worktree dedie, branche `s54-theo-cloture`,
  `git status --short` vide avant mesure. **Base** : `5e1332ca`, fin de s53.
- **Version** : **JDK 21.0.10**, propriete `java.version` des 282 rapports `TEST-*.xml` du reacteur complet (section 1),
  seule valeur trouvee (`ADR-0071`).

## 0. Verdict

| Question | Verdict |
|---|---|
| Les regles ArchUnit sont-elles vertes ? | **oui**, 229 tests de `com.recreo.archi`, 0 echec ; reacteur complet `BUILD SUCCESS` (section 1) |
| Le compte de regles a-t-il change ? | **non** : 37 `static final ArchRule` et 36 `@ArchTest` hors contre-exemples (section 2) |
| Le diff du sprint touche-t-il une frontiere ? | **non** : aucune ligne de `src/main/java`, aucun POM, aucun workflow (section 3) |
| `ADR-0104` | **`Accepte` depuis le 2026-10-07** (`4e8ae7b4`), regle (13) verte a la cloture ; il n'y a pas de statut a changer (section 4) |
| T6, T7 appellent-ils une regle ArchUnit ? | **non** (section 5) |

## 1. Commandes et resultats

| Mesure | Commande | Horloge | Resultat |
|---|---|---|---|
| Paquetage ArchUnit | `bash outils/mvn-jdk21.sh -B clean test -pl bootstrap -am '-Dtest=com/recreo/archi/**/*' -Dsurefire.failIfNoSpecifiedTests=false` ; perimetre : `com.recreo.archi` de `bootstrap`, `-am` construisant les quatre modules amont | 12:45:38 a 12:47:29 | `BUILD SUCCESS`, 32 rapports, **229 tests, 0 echec, 0 erreur, 0 ignore** ; `DecouplageMecaniqueContenuTest` 13, `LesReglesMordentTest` 38 |
| Reacteur complet (job `build`) | `bash outils/mvn-jdk21.sh -B clean verify` sur la racine | 12:48:13 a 12:51:31 | `BUILD SUCCESS`, **2 216 tests, 0 echec, 0 erreur, 0 ignore** : domain 120, application 17, adapter-web 401, adapter-content 1 360, bootstrap 312 Surefire et 6 Failsafe |

Comptes : somme des attributs `tests`, `failures`, `errors`, `skipped` des rapports `TEST-*.xml`. **Ecart avec s53** (2 174) :
+42, tous dans `adapter-content` (1 318 -> 1 360 ; 282 rapports contre 278 a la confirmation de s53), soit les quatre classes de
test de paquet neuves de la section 3.
`LesReglesMordentTest` compte 38 dans son rapport XML et 37 dans son rapport texte : le XML porte un cas de plus sous le nom de
classe court (`aucunContreExempleEnProduction`, moteur ArchUnit), que le rapport texte ne garde pas.

## 2. Compte de regles

Methode d'`ADR-0104` : lignes des `*.java` suivis (`git ls-files`, worktrees d'agents exclus) qui correspondent a
`^\s*(public |private |protected )?static final ArchRule ` d'une part, a `^\s*@ArchTest\s*$` d'autre part. **39 et 36** contre-exemples
compris ; **37 et 36** hors `RegleContreExemple.java` (2 regles, 0 `@ArchTest`). Identique a s53. Aucun fichier de `com.recreo.archi`
n'est dans le diff du sprint.

## 3. Diff non-contenu du sprint

`git diff --stat 5e1332ca..HEAD -- ':!*.yaml' ':!squad' ':!docs'` : **11 fichiers, +1 517 -12**.

| Fichier | Nature |
|---|---|
| `PaquetCe1FuturTest`, `PaquetCe1PasseComposeTest`, `PaquetCm1EmcAtteinteAutruiTest`, `PaquetCpArtsOeuvresFamillesTest` | neufs, tests de paquet d'`adapter-content` |
| `PaquetCm1SciencesTerreTest`, `PaquetCm1SciencesActiviteHumaineTest`, `PaquetCm1GrandsNombresTest`, `AucunSigneDoubleNEstDetachableDeSonMotTest` | modifies, 4 a 6 lignes chacun |
| `TexteAccentueExposeParLePortEntrantTest` (`bootstrap`, test) | +67, inventaire d'accent des entrees branchees |
| `outils/rapport-tokens.py`, `outils/rapport-tokens-journal-test.py` | outil Python et son test (T4) |

`git diff --name-only 5e1332ca..HEAD | grep -E "src/main|pom.xml|\.github"` ne rend que `catalogue.yaml` et des paquets
`contenu/*.yaml` d'`adapter-content`. **Aucune frontiere n'est touchee** : zero ligne de Java de production, `domain` et `application`
absents du diff, aucun POM, aucun workflow, aucun garde-fou d'architecture modifie.

## 4. `ADR-0104` et le prix de `S54-ART-CP-10-PRIX`

**Statut.** Le lancement, le planning (lignes de `S54-ART-CP-10-PRIX`) et `DEC-0114` (section 6, A.1, A.10) disent `Propose`. Le
fichier de l'`ADR` et `ADR-index.md` disent **`Accepte` le 2026-10-07**, par `4e8ae7b4` (mon avis de faisabilite de s54, T6), sur la
seule condition ecrite (reponse de `nadia-prof` sur `C5` clause 1, 2026-10-06). La regle (13) est ecrite depuis la creation de
l'`ADR` (2026-10-05) et reste verte et mordue a la cloture (section 1). **Je ne change aucun statut.** L'ecart est dans des fichiers
qui ne sont pas a moi : `DEC-0114` a `camille-po`, le planning a `sofia-sm`.

**Prix.** Ecrit en tailles dans `avis-faisabilite.md`, section 4, amendement de T6 date du 2026-10-08, fichier que `DEC-0114`
section 6 et le planning designent (T6). Resume : production **2 fichiers d'`adapter-web`, 20 a 38 lignes**, plus 3 images (74 441
octets) et 30 lignes de registre ; tests **3 fichiers neufs, 5 modifies, 15 a 18 tests, 680 a 1 400 lignes** ; zero ligne dans
`domain`, `application`, le chargeur, les POM et la CI ; **zero regle ArchUnit neuve**. Methode et plages, dans l'amendement.

## 5. T6 et T7 : regle ArchUnit appelee ?

- **T6 : non.** La seule contrainte du chantier qui porte sur du bytecode (une mecanique ne lit pas une proposition) est la regle (13),
  deja en vigueur. La contrainte "le prefixe d'une proposition n'est lu que dans `PartieController`" n'est pas encodable en ArchUnit :
  le prefixe est une constante de chaine, inlinee a la compilation, et il est aussi lu par le gabarit. Les garde-fous 1 a 4
  d'`ADR-0104` jugent des YAML, des images et du HTML rendu ; les 2 et 4 auraient une population vide avant le lot, donc verts sur
  rien.
- **T7 : non.** La regle du push est une discipline de poste ; aucun type Java n'en est l'objet.

## 6. Jobs de CI lances sur ce commit

| Job | Commande | Resultat |
|---|---|---|
| `build` | section 1, reacteur complet, sur `db69bbc6` (ce commit n'ajoute que deux fichiers de `squad/`) | `BUILD SUCCESS` |
| `chemins` | `bash .github/scripts/verifier-chemins-test.sh`, puis `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' \| bash .github/scripts/verifier-chemins.sh` | rendu dans le compte rendu de la tache |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis le sujet de ce commit dans `verifier-prefixes.sh` | rendu dans le compte rendu de la tache |
| `outils` | non lance : aucun outil touche par ce commit | - |
