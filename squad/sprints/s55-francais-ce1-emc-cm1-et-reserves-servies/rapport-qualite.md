# Rapport qualite de cloture - s55

**Auteur** : `yanis-qa`. **Date** : 2026-10-08. **Arbre mesure** : `round-two` a `af8b326e` (hash complet
`af8b326e2b2a7f33c47ecef98b7f4cf37be9aeba`), sommet fusionne de s55, mesure dans le worktree de la branche
`s55-yanis-cloture`. `bash outils/verifier-lignee.sh` : "descend de la lignee de `round-two`", code 0.
`git status --porcelain` vide avant, apres chaque mutation et apres la derniere mesure.

**Perimetre** : les quatre jobs de `.github/workflows/ci.yml`, relu avant la mesure ; les totaux de tests ; le rejeu des
mutations que les developpeurs ont rapportees sans que je les rejoue ; la tenue des criteres corriges par `camille-po`
(`rapport-camille-criteres.md`) ; A6. Ce rapport porte la porte **technique** uniquement. Il ne prononce aucun Done,
ni technique (c'est la CI qui juge) ni pedagogique (c'est `nadia-prof`).

Adaptations de ce poste : `mvn -B verify` est lance par l'enveloppe `bash outils/mvn-jdk21.sh -B clean verify` (JDK de la
toolchain, `clean` en plus). Maven imprime a chaque lancement dix avertissements "profile ... could not be
activated" (repetes en fin de sortie) venus des profils actifs du poste, sans effet sur le verdict. Les mesures partielles portent `-am`.

## 1. Les quatre jobs, sur `af8b326e`

### `prefixes` : vert, tel que lance ici

- `bash .github/scripts/verifier-prefixes-test.sh` : "Tous les cas passent", code 0.
- `bash .github/scripts/commit-atteignable-test.sh` : "Tous les cas passent", code 0.
- `git log --no-merges --format=%s origin/round-two..HEAD | bash .github/scripts/verifier-prefixes.sh` : "Tous les
  commits portent un prefixe d'agent connu.", code 0. Plage : 22 sujets hors fusions (32 commits, 10 fusions).
  `origin/round-two` est `fa4ddc7e` sur ce poste, une reference locale ancienne : ce n'est pas la plage
  `github.event.before..GITHUB_SHA` d'un push reel, la branche `BASE` du workflow n'est pas exercee.
  `commit-atteignable.sh origin/round-two` : code 0.
- Le commit de ce rapport n'est pas dans cette plage ; il est verifie apres son commit (compte rendu de la tache).

### `chemins` : vert, tel que lance ici

- `bash .github/scripts/verifier-chemins-test.sh` : "Tous les cas passent", code 0.
- `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` : "Aucun
  chemin de poste de travail ni nom de compte dans les fichiers verifies.", code 0, mesure avant l'ajout de ce rapport ;
  reverifie apres son ajout (compte rendu de la tache).

### `outils` : vert

Boucle du workflow rejouee, trois motifs : `outils/*-test.sh` 5 juges, `squad/outils/*/*-test.sh` 2 juges,
`outils/*-test.py` 3 juges. **10 juges purs executes, 10 codes de sortie 0**, aucun motif vide. `python3` repond
(3.14.0). Les trois juges Python, dont `outils/rapport-tokens-journal-test.py` (7 tests), sont verts. A cloture de s54 on
en comptait 10 : inchange.

### `build` : vert, les deux etapes du job

- Etape 1, `bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, sans `-pl` : `BUILD SUCCESS`, 3 min 28 s,
  code 0, les six modules `SUCCESS`, 6 tests d'integration (failsafe) verts.
- Etape 2, `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` (le `-am` est celui du workflow) : `BUILD SUCCESS`,
  2 min 28 s, code 0, journal `Replacing main artifact ... with repackaged archive`. Ce pas relance les tests
  unitaires : memes comptes (120, 18, 407, 1419, 333).
- **Cas du jar reproductible**, `bash outils/mvn-jdk21.sh -B clean verify -Dproject.build.outputTimestamp=1980-01-01T00:00:02Z` :
  `BUILD SUCCESS`, 5 min 50 s, code 0, memes comptes (120, 18, 407, 1419, 333, puis 6 d'integration). Le jar produit porte
  bien la forme du cas : `unzip -l bootstrap/target/recreo.jar` donne `1980-01-01 00:00` sur les premieres entrees listees. Les trois
  copies de `ProvenanceDesClassesAmontEstLeDepotTest` (correctif `a3606b2f` de `theo-archi`, ancetre de `HEAD`) passent :
  application 3 tests, adapter-web 4, adapter-content 4. **Aucun POM ne fixe `project.build.outputTimestamp`** (`grep`
  sur tous les `pom.xml` : rien) : les deux premiers lancements produisent donc un jar a horodatages reels, et c'est ce
  troisieme lancement, seul, qui exerce la branche "jar reproductible". Je n'ai pas mute le correctif de `a3606b2f`
  (hors de ma commande) : le jeu fautif a trois cas qu'il a commite n'est pas rejoue ici.
- **JDK** : l'enveloppe rend `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10` ; la machine virtuelle des tests
  rapporte `Started ... using Java 21.0.10` (22 occurrences dans le journal de `verify`, une seule valeur, 22 aussi dans
  celui du cas reproductible).

Jobs non lances : aucun des quatre. Ce qui n'est pas lance : le workflow lui-meme sous GitHub Actions, la branche `BASE`
du job `prefixes`, tout ce qui tourne sous Linux (section 6).

## 2. Totaux de tests apres fusion

Methode : lignes `Tests run: N, Failures: F, Errors: E, Skipped: S` de fin de module de surefire dans la sortie du
`clean verify`, recoupees par la somme de l'attribut `tests` des rapports XML de surefire et de failsafe sous chaque
`target/` apres le dernier lancement. Les deux concordent, et les trois lancements complets rendent les memes comptes.

| Module | surefire | failsafe | echecs | erreurs | ignores |
|---|---|---|---|---|---|
| domain | 120 | - | 0 | 0 | 0 |
| application | 18 | - | 0 | 0 | 0 |
| adapter-web | 407 | - | 0 | 0 | 0 |
| adapter-content | 1419 | - | 0 | 0 | 0 |
| bootstrap | 333 | 6 | 0 | 0 | 0 |

**2303 tests (2297 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur, 0 ignore.** Perimetre : reacteur complet de
`af8b326e`, phases `test` et `integration-test`.

**Delta : +87 sur la base 2216** (rapport qualite de s54, arbre `db69bbc6`, ancetre de `HEAD` ; je n'ai pas remesure
cette base, je cite son rapport). Repartition par module : application +1, adapter-web +6, adapter-content +59,
bootstrap +21, domain 0, failsafe 0. Origine, par classe de test ajoutee ou modifiee entre `db69bbc6` et `HEAD`
(`git diff --name-status db69bbc6 HEAD -- '*/src/test/java/*'`, compte par classe lu dans les rapports XML) :

| Source | Classe | Tests |
|---|---|---|
| `S55-BR-FRA-CE1-L1` (`c9c80937`, `2b66a441`) | `PaquetCe1AccordSujetVerbeTest` (neuve) | 17 |
| `S55-BR-FRA-CE1-L2` (`2aa1e03a`) | `PaquetCe1ClassesDeMotsTest` (neuve) | 13 |
| `S55-BR-EMC-CM1-03` (`0839f9dc`) | `PaquetCm1EmcRespectDifferencesTest` (neuve) | 12 |
| `S55-BR-EMC-CM1-06` (`4636aa28`) | `PaquetCm1EmcPrincipesRepubliqueTest` (neuve) | 12 |
| `S55-ART-CP-10-MECANIQUE` T2 (`dfd2f107`) | `LIllustrationDeContenuResoutEtAucuneNeDortTest` (+4), `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` (neuve, 7), `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest` (+4) | +15 |
| `S55-ART-CP-10-MECANIQUE` T3 (`363793e3`) | `PartieControllerPropositionsEnImageTest` (neuve, 5), `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (neuve, 10) | +15 |
| correctif de provenance (`a3606b2f`) | `ProvenanceDesClassesAmontEstLeDepotTest`, une methode de plus dans chacune de ses trois copies | +3 |
| **Total** | | **87** |

Les classes modifiees sans changement de compte : `AucunSigneDoubleNEstDetachableDeSonMotTest` (5), `PaquetCpEmcExclusionTest`
(9), `PaquetCm1EmcAtteinteAutruiTest` (10), `PaquetCm1SciencesActiviteHumaineTest` (12), `RessourcesTiercesTest` (22),
`TexteAccentueDesGabaritsTest` (9), `TexteAccentueExposeParLePortEntrantTest` (5). Aucun module n'est sans test. Delta
annonce : **+87 sur `db69bbc6`** ; `sofia-sm` etablit le total sur l'arbre fusionne.

## 3. Rejeu des mutations

Methode commune : un script applique la mutation (remplacement exact, refuse si le motif n'est pas trouve exactement une
fois ou, pour une ligne donnee, pas sur cette ligne), lance la commande, puis `git checkout -- <fichier>` ; la sortie de
chaque lancement est conservee hors du depot. Lancement temoin sans mutation : `PartieControllerPropositionsEnImageTest`
(5 tests) et `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (10 tests) : 0 echec. Commande
d'`adapter-content` : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=<classes>
-Dsurefire.failIfNoSpecifiedTests=false`. Commande de la mecanique : meme forme avec `-pl adapters/adapter-web,bootstrap`,
et `-Dmaven.test.failure.ignore=true` pour que `bootstrap` tourne meme quand `adapter-web` est rouge (sans cela, le
reacteur s'arrete avant `bootstrap` et le garde-fou de `bootstrap` n'est pas mesure : c'etait le cas de mon premier
passage sur les mutations rouges dans `adapter-web`, refait). `git status --porcelain` vide apres chaque mutation (22 mutations distinctes).

| Mutation | Mutant | Resultat | Fichier restaure |
|---|---|---|---|
| **Q-Y8** apostrophe typographique | `\\bt['\\u2019]` ramene a `\\bt[']` dans les quatre tests de paquet qui portent l'expression (`PaquetCm1EmcAtteinteAutruiTest`, `PaquetCm1SciencesActiviteHumaineTest`, `PaquetCm1EmcPrincipesRepubliqueTest`, `PaquetCm1EmcRespectDifferencesTest`) | **vert : 46 tests, 0 echec. La mutation survit.** | oui |
| **M2** d'`EXC-R15` | `cp-emc-exclusion.yaml` ligne 232 (item 10, tache B), `bonneReponse` remplacee par `une mise à l'écart qui se répète` | rouge : `PaquetCpEmcExclusionTest`, 9 tests, 2 echecs : `aucuneBonneReponseDeLaTacheBNEstUneCategorieDeClassement`, `exc11AucuneBonneReponseDeLaTacheBNEstLaSeulePropositionANommerUnAdulte` | oui |
| **A8** `test_6`, a | `outils/rapport-tokens.py` ligne 463 `if not options.sprint:` remplace par `if False:` (refus neutralise) | rouge : `test_6_un_lancement_sans_sprint_est_refuse_et_n_ecrit_rien`, `AssertionError: 2 != 1`, 7 tests, 1 echec | oui |
| A8 `test_6`, b | ligne 470 `return 2` remplace par `return 1` | rouge : meme test, `2 != 1` | oui |
| A8 `test_6`, c | ligne 470 `return 2` retire (le refus s'affiche, le lancement continue) | rouge : meme test | oui |
| **P3** | `cm1-emc-principes-republique.yaml` ligne 87, distracteur `"la démocratie"` remplace par `"la liberté"` (item de laicite) | rouge : `PaquetCm1EmcPrincipesRepubliqueTest`, 12 tests, 1 echec : `p3LaLiberteNEstPasUnDistracteurDeLItemDeLaLaicite` | oui |
| **L1** controle "aucun indice ne contient une proposition de son item" | `ce1-accord-sujet-verbe.yaml` ligne 55 (item 6), `lequel est le bon` remplace par `lequel va avec lui` | rouge : `PaquetCe1AccordSujetVerbeTest`, 17 tests, 1 echec : `trv5AucunIndiceNeContientUnePropositionDeSonItem` | oui |

**Q-Y8 est un resultat rouge pour le critere, pas pour le build.** Le critere de `S54-ASCII-APOSTROPHES-TESTS`, tel que
`camille-po` l'a corrige, demande une chaine fabriquee (`t`, U+2019, `as`) rejetee par le motif d'adresse a l'enfant, et
dit que `yanis-qa` rejoue la mutation et la voit rouge. Je la rejoue et elle reste verte. Cause, constatee : `marc-dev` a
livre l'echappement (`51c81763`, deux fichiers, une ligne chacun ; `grep -cP '\x{2019}'` sur les tests de paquet : 0, tenu) mais aucun cas fabrique ;
`git grep -n "u2019" -- '*Test.java'` ne rend que les quatre lignes d'expression, aucune chaine de test. La clause
"reconnaissent toujours l'apostrophe typographique" n'est gardee par rien, comme a la lecture de testabilite. Aucun fichier
de `contenu/` ne porte U+2019 (`grep -rlP '\x{2019}'` : aucun). Ce que cela autorise : un retour a l'apostrophe droite seule
dans l'un des quatre motifs passe la CI. Route : `sofia-sm` (`marc-dev` a livre le poste sans le cas ; la forme du cas est a
`theo-archi`).

`A8` sur le depot reel, trois cas, `python outils/rapport-tokens.py --projects-dir <dossier>` sans `--sprint` : dossier
vide, dossier inexistant, dossier reel des transcriptions de ce poste. Code **2** dans les trois cas, `git status --short`
vide apres chacun. L'ordre des controles que `camille-po` a corrige (Q-Y10) tient sur `HEAD` : le refus de `--sprint` (ligne
463) precede celui du dossier (lignes 481 a 492). Les numeros de `rapport-camille-criteres.md` (483 et 495) decrivent l'ordre
d'avant `A8` (`69d9d6a2`), lu sur `fa4ddc7e` : ils ne valent plus sur `HEAD`.

### Morsures du garde-fou 4 d'`ADR-0104` (`S55-ART-CP-10-MECANIQUE` T3)

Ici les mutants sont poses sur le **vrai** gabarit `manche.html` ou le **vrai** `PartieController`, pas en memoire. Deux
classes jugent : `W` = `PartieControllerPropositionsEnImageTest` (adapter-web, 5 tests), `B` =
`LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` (bootstrap, 10 tests). Colonnes : echecs.

| Mutant | W | B | Rouge, nom du premier test touche |
|---|---|---|---|
| gabarit : image remplacee, `th:text="${proposition}"` toujours rendu | 2 | 7 | `chaqueEcranDeChaqueItemAPropositionsEnImageEstSainEtLePerimetreReelEstVide` |
| gabarit : `alt` = identifiant | 0 | 5 | idem (B seul) |
| gabarit : `alt` decrit une oeuvre (Q-Y14) | 0 | 5 | idem (B seul) |
| gabarit : nom accessible (`aria-label`) retire | 1 | 4 | idem ; W : `uneMancheDontToutesLesPropositionsSontDesReproductionsRendUneImageParBouton` |
| gabarit : reponse en texte rendue a la revelation | 1 | 3 | W : `laRevelationNePoseAucuneReponseEnTexteEtMarqueLaBonneOeuvreSeule` ; B : `chaqueEcran...` |
| gabarit : lecture du prefixe par proposition (`#strings.startsWith`) | 0 | 1 | B : `leGabaritNeLitJamaisLePrefixeDUneProposition` |
| gabarit : classe `ecran-deux-oeuvres` retiree | 1 | 3 | W : `uneMancheDontToutesLesProposition...` ; B : `chaqueEcran...` |
| gabarit : phrase M4 retiree | 1 | 3 | W : `laRevelationNePose...` ; B : `chaqueEcran...` |
| controleur C1 : `propositionsEnImage` toujours faux | 2 | 5 | idem |
| controleur C1 de `lea-dev` : `reponseEnImage` toujours faux (ecran de fin) | 1 | 3 | `lEcranDeFinNeRendJamaisLIdentifiantDeLaBonneReponse` (via W) |
| controleur C2 : la revelation pose la reponse en texte | 1 | 3 | W : `laRevelationNePose...` |
| controleur C3 : `mancheRevelee` toujours faux | 1 | 3 | W : `laRevelationNePose...` |
| controleur C4 : `allMatch` remplace par `anyMatch` | 1 | **0** | W : `uneRangeeMelangeeDeTexteEtDeReproductionsResteUneRangeeDeBoutonsTexte` |
| controleur : `reponseAttendueVisible` sans le test `reponseEnImage` (ligne 770) | 1 | 3 | W : `lEcranDeFinNeRendJamaisLIdentifiantDeLaBonneReponse` |
| controleur : `bonneReponse` non masquee seule (ligne 761) | 0 | 0 | **vert, mutant equivalent** : `retour.html` ne rend `bonneReponse` que sous `reponseAttendueVisible` (lignes 97-98), que la ligne 770 laisse a faux |

Les quatorze mutants non equivalents rougissent au moins un test. **C4 est mordu par `W` seul** : le jeu de preuve de `B`
est homogene, comme `lea-dev` l'ecrit ; tant que `W` tourne, la morsure tient. Le mutant "ligne 761 seul" est vert parce
qu'il est sans effet sur la page, pas parce que le garde-fou est troue : je le dis equivalent, j'ai verifie que la ligne
770 le masque en mutant la ligne 770 seule (rouge, ligne ci-dessus). Les mutants de gabarit en memoire de `lea-dev` ne
sont pas rejoues un par un : ce sont des tests, ils passent (10 tests de `B` verts au temoin).

Les morsures de T2 (garde-fous 1 a 3) ne sont pas dans ma commande et ne sont pas rejouees ici.

## 4. Criteres corriges, verifies sur `af8b326e`

Commande de chaque ligne : celle de `rapport-camille-criteres.md` ou du backlog, lancee avec le `grep` GNU. Un critere
est "tient" quand sa commande rend la valeur annoncee sur l'arbre fusionne.

| Q-Y | Critere corrige | Mesure sur `af8b326e` | Verdict |
|---|---|---|---|
| Q-Y1 | `grep -c "mise à l'écart"` sur `PaquetCpEmcExclusionTest.java` au moins 1 | 3 ; l'ancien motif `mise . l.cart` rend 0 (insatisfiable, confirme sur `printf`) | tient |
| Q-Y2 | `hasSize(3)` vers `hasSize(4)` dans `RessourcesTiercesTest` | `hasSize(3)` : 0 ; la methode a ete renommee `quatreReferences...` et porte `hasSize(4)` (ligne 732) | tient |
| Q-Y3 | cliquet de `TYP-1` : `grep -cP '^ *indice: ".* [:;!?]'` rend 0 sur `cm1-grands-nombres.yaml`, la meme avec U+00A0 rend 12 | 0 et 12 ; `DETTE_TOTALE` 490, entree retiree | tient |
| Q-Y4 | `grep -cE '^\s*@Test\s*$'` sur `PartieControllerTest.java` : 41 | 41 ; `grep -c '@Test'` rend 42 (une ligne `@TestConfiguration`) | tient |
| Q-Y5(a) | regle de comptage de `PaquetCe1ConjugaisonPresentTest.mots` | 11 mots au plus sur les 24 indices avec cette regle (coupe sur espace, jeton avec lettre ou chiffre) ; 19 et 20 indices au-dessus de 12 avec `split()` sur tout blanc ; la regle est dans `PaquetCe1AccordSujetVerbeTest.mots` | tient |
| Q-Y5(b) | "jamais la reponse", item 6 | non reecrit ; l'indice de l'item 6 est devenu `lequel est le bon` au poste `ACV-CE1-R1` (`2b66a441`), et le controle de la section 3 (L1) est rouge si l'ancien indice revient | tient |
| Q-Y6 | titre ou consigne avec U+00A0 devant `?`, commande bornee a l'entree | 0 pour `bandit-manchot-accord-sujet-verbe-ce1` et `bandit-manchot-classes-de-mots-ce1` ; **sur tout le catalogue la meme commande rend 23, pas 2** | **ecart de texte** |
| Q-Y7 | lettre litterale a la place de `.` | `il ___ un gâteau`, `Pense à deux amis...`, `Cette œuvre est faite avec de la peinture` : 1 en `LC_ALL=C` et 1 en `LC_ALL=C.UTF-8`, les trois | tient |
| Q-Y8 | cas fabrique pour l'apostrophe typographique | absent (section 3) | **non tenu** |
| Q-Y9 | commandes de la porte 6, `QC4`, `G-NADIA-A`, `G-INES` | `git merge-base --is-ancestor 4893771b HEAD` : 0 ; `git grep -l "Choisir cette œuvre" -- squad/conformite` : 1 fichier ; `git grep -l QC4 -- squad/conformite` : **2 fichiers** (le backlog en annonce 1) ; `ls squad/conformite \| grep -ci s55` : 4 | tient avec un ecart de compte |
| Q-Y10 | A8 : sort 2 avant tout controle du dossier | trois cas, code 2, arbre propre ; ordre des controles lu sur `HEAD` : section 3 | tient |
| Q-Y11 | liste blanche de C2 par story | `c9c80937` 6 fichiers, `2aa1e03a` 6, `0839f9dc` 5, `4636aa28` 5, `2b66a441` 3 : chacun est dans la liste de sa story (catalogue, YAML, test de paquet, fixture fautive, inventaire du port entrant ; `2b66a441` sans catalogue ni inventaire) | tient |
| Q-Y12 | matrice vide au branchement | `git log db69bbc6..HEAD -- squad/programme/MATRICE-couverture.md` : quatre commits, tous de s54 (`b76ec8dc`, `a46220ab`, `b5779885`, `c1763f41`) ; `git diff --stat fa4ddc7e HEAD -- squad/programme/MATRICE-couverture.md` : vide | tient |
| Q-Y13 | prenoms de `K4` : `Mia\|Léa\|Inès\|Fatou\|Lina` et `Tom\|Karim\|Noé` sur les lignes `enonce` | 7 et 6 ; `Fatou` 1, `Lina` 1, `Tom.*range` 0, `Karim aime` 0 | tient |
| Q-Y14 | `alt` d'une image de proposition | decide `alt=""` ; le garde-fou le juge (section 3, mutant "alt decrit une oeuvre", rouge) | tient |

**Defauts de critere restants.**

1. **Q-Y6, "2" sur tout le catalogue.** Le texte dit que la commande non bornee rend 2 (`Nom ou verbe ?`, `Majuscule ou
   point ?`, lignes 100 et 106). Elle rend 23 : les 23 occurrences declarees par l'entree `CATALOGUE` du cliquet, sur les
   lignes `titre` et `consigne`. Le 2 ne compte que les `titre`. Le critere borne a l'entree (0) est juste.
2. **Q-Y8** : voir section 3.
3. **Q-Y9, `QC4`** : 2 fichiers de `squad/conformite` portent `QC4`, le backlog en annonce 1. La condition "au moins un"
   tient, "exactement un" non.
4. **Le planning porte encore les anciennes commandes** (`mise . l.cart`, `g.teau`, "de 12 a 0" : `grep -c` rend 2 sur
   `planning.md` pour les deux premiers ; lignes 154, 267 et 496 pour le troisieme). `camille-po` l'a laisse a `sofia-sm` et
   le backlog dit qu'il l'emporte pour le produit ; l'ecart est connu, il n'est pas leve.
5. **C7 et `EMC-9`.** Le critere des stories EMC demande `EMC-9` ; `lea-dev` ecrit "sans objet" (arbitrage B 1.2) et lit a la
   place `EMC-4`, `EMC-8`, la regle de s53 4.1 et `BIA-5`. Non tranche, `camille-po` l'a signale ; il reste ouvert.

**Criteres de branchement, mesurables** (commandes de la lecture de testabilite, sur `af8b326e`) :

- `ce1-accord-sujet-verbe` : `indice: "Remplace le sujet par` 12 ; `indice: "Lis le sujet avec chaque verbe` 5 ; `compte-les` 1 ;
  `^ *indice:` 24 ; `il ___ au march` 1 ; `il ___ un gâteau` 1 ; en-tete "satisfait par la forme d'une autre personne" 1 ;
  balayage `oreille|entends|écoute|sonne|prononce|dans ta tête` sur les lignes `indice` : 0. Consigne `Lis bien, puis choisis
  la bonne forme du verbe` dans `catalogue.yaml` : 3.
- `ce1-classes-de-mots` : consigne `dis quelle sorte de mot est entre guillemets` dans `catalogue.yaml` : 1.
- `cm1-emc-respect-differences` : ancienne bonne reponse de l'item 4 : 0, nouvelle : 1, `jeu de cartes` : 1, `disparaissent
  avec le temps` : 0, `puni si un adulte le voit` : 1, item 7 : 1, 11 items.
- `cm1-emc-principes-republique` : ancien distracteur de l'item 6 : 0, nouveau : 1, item 8 : ancienne 0 et nouvelle 1, `2018-717` : 1.
- `catalogue.yaml` : `grep -c "paquet: <nom>$"` rend 1 pour chacun des quatre paquets branches.
- `EXC-R15` : `bonneReponse: "une moquerie qui se répète"` 1, `bonneReponse: "une mise à l'écart qui se répète"` 2, la meme
  chaine 3, ancienne consigne dans `catalogue.yaml` 0.
- `S55-ART-CP-10-MECANIQUE` : critere 1 (`grep -c "oeuvres/' + \${proposition}"` sur `manche.html` : 1 ; `th:text="${proposition}"` : 1,
  branche des jeux en texte) ; critere 7 (`git diff --name-only dfd2f107^ 363793e3` filtre sur `domain`, `application`,
  `adapter-content/src/main/java`, `bootstrap/src/main`, `pom.xml`, `.github` : 0 ligne ; meme filtre de `db69bbc6` a
  `HEAD` sur le code de production hors `adapter-web` : 0) ; critere 8 (aucun `catalogue.yaml`, `MATRICE` ni fichier de
  `contenu/` dans T2 et T3 : 0) ; `ITEMS_A_PROPOSITIONS_EN_IMAGE` = 0, aucun paquet branche n'a de proposition-image.

### Ce qu'un automate ne juge pas

- **`K1` a `K5` et les indices** : que chaque indice convienne a son item, qu'il ne se satisfasse d'aucun distracteur de son
  item, que `une photographe` se lise naturellement, que `Fatou` et `Lina` soient des prenoms naturels dans ces phrases, que
  la consigne generique soit juste a l'ecran. Les controles mesurables ci-dessus ne disent que la forme (nombre de mots,
  mots-cles absents, un indice ne contient pas une proposition). Le verdict est a `nadia-prof`.
- **`P4`** : un indice qui redit la bonne reponse. `lea-dev` l'ecrit "non garde : P4", les six indices lus (rapport EMC,
  section 5). Aucun automate.
- **`EMC-4`, `EMC-5`, `EMC-8`, `BIA-5`** et l'effet cote a cote des trois items de laicite : "relu" dans le rapport de
  `lea-dev`, une ligne, pas une preuve.
- **`TRV-CE1-5`** : le rapport de `marc-dev` (section 3) dit la regle executable `k1LeNombreSeulNEstUnCritereQue...` ; la
  mesure l'a reconnue, la lecture est a `nadia-prof`.
- **Mise en page de T3** : `M1` (meme taille rendue, sans defilement), la marque de revelation, `X2`, `X3`, `X5`, `X6`, le pli a 551 px.
  Rien n'a ete joue sur le jar (aucun paquet a propositions-images n'est au catalogue) ; le HTML a ete lu par jsoup, jamais
  a l'oeil, et je ne l'ai pas rejoue non plus.
- **Le nom `une mise à l'écart qui se répète`** (est-ce le bon nom) et le titre servi `Une moquerie qui se répète` a l'ecran :
  rejeu de `nadia-prof`.

## 5. A6 : le diff de `s54-nadia-c`

**Faisable, et tenu.** Le nom de branche ne sert pas : la copie locale `s54-nadia-c` a ete reecrite depuis la fusion
(`46e3651c`, hashes differents pour les memes sujets, base `c52a9ff7`). Le diff qui compte est celui du second parent de la
fusion `3334d5d2` (parents `c72ae2f4` et `d6660a70`).

- `git log --format='%h %p | %s' 3334d5d2^1..3334d5d2^2` : trois commits, `c5c2d50f` (arbitrage prealable de l'instance C),
  `5cdadef3` (fusion "round-two dans s54-nadia-c pour l'audit"), `d6660a70` (fiche, `VALIDE SOUS RESERVE`).
- Fichiers par commit non-fusion (`git diff-tree --no-commit-id --name-only -r`) : `c5c2d50f` 1 fichier
  (`squad/sprints/s54-.../arbitrage-nadia-c.md`), `d6660a70` 1 fichier
  (`squad/conformite/FICHE-bandit-manchot-familles-d-oeuvres-cp.md`).
- `git diff --name-only 3334d5d2^1...d6660a70` : ces deux fichiers, rien d'autre. La fusion `5cdadef3`, contre son second
  parent, n'ajoute que `arbitrage-nadia-c.md` (`git diff --name-status 5cdadef3^2 5cdadef3`).
- Aucun fichier de l'instance A ou B de s54 : `grep -ciE "instance-a|instance-b|FICHE-.*(futur|passe-compose|sciences|atteinte)"`
  sur la liste rend 0.
- `git diff --stat` entre `d6660a70` et la copie locale reecrite, sur ces deux fichiers : vide.
- **Non verifie** : le nom du worktree et du scratchpad de l'instance C, jamais ecrits ; l'isolation d'ecriture est constatee
  par l'absence de fichier partage, pas par les noms.

## 6. Ce qui n'est pas verifie

- **La CI distante** : aucune mesure sous GitHub Actions ni sous Linux. Je ne dis rien de son verdict.
- **Fins de ligne** : worktree en `core.autocrlf=true`, suite verte dans cette condition ; l'autre sens n'est pas mesure.
- **Stabilite** : la suite complete a tourne trois fois (`verify`, `package`, cas reproductible) avec les memes comptes,
  plus les lancements partiels des mutations ; aucun echec intermittent vu. Rien conclu au-dela de ces lancements. Aucun
  test instable a nommer.
- **Poste** : le troisieme lancement complet a tourne pendant que la boucle du job `outils` s'executait sur le meme poste ;
  les durees sont donc indicatives, pas les comptes.
- **Mutations non rejouees** : les morsures de T2 (garde-fous 1 a 3), le correctif `a3606b2f`, `A7`
  (`JAVA_TOOL_OPTIONS` dans `outils/mvn-jdk21.sh`), les cliquets de `PONCT` au-dela de ce que `lea-dev` rapporte. La
  lecture de testabilite annoncait la mutation de `A7` ("retirer le `grep -m1 'version "'`") : **non faite**. `bash
  outils/mvn-jdk21-test.sh` est vert dans le job `outils`, rien de plus.
- **Tracabilite** : les tests de paquet portent le nom du paquet, pas l'identifiant de story ; plusieurs criteres de la
  section 4 sont des `grep`, pas des tests.
- **Ecarts de texte trouves, non corriges** (hors de ma tache) : section 4, points 1 a 5.

## 7. Verdict

- **Porte technique, telle que lancee ici** : les quatre jobs sont verts sur `af8b326e` (`prefixes`, `chemins`, `outils`,
  `build` avec ses deux etapes, plus le cas du jar reproductible). 2303 tests, 0 echec. Jugement de la CI distante : a la CI.
- **Mutations** : `M2`, `A8` `test_6`, `P3`, `L1` et les morsures du garde-fou 4 rougissent comme annonce. **`Q-Y8` reste
  verte** : la clause "reconnaissent toujours l'apostrophe typographique" n'est gardee par rien, et le critere corrige qui
  le demandait n'est pas livre.
- **Criteres** : tenus sauf Q-Y8 ; ecarts de texte listes en section 4 (Q-Y6, Q-Y9, planning, `EMC-9`).
- **Done pedagogique** : non prononce par ce rapport. Les verdicts de `nadia-prof` ne sont pas rejoues ici.
