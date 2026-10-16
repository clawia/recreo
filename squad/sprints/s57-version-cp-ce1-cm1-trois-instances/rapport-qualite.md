# [Sprint s57] Rapport qualite de `yanis-qa` - cloture du palier 2, arbre fusionne `bb322938`

- **Auteur** : `yanis-qa`. **Date** : 2026-10-16. **Arbre mesure** : `round-two` a `bb322938` (pousse), worktree propre (`git status --short` vide avant et apres). Code inchange depuis `a5a486ad` : `git diff --quiet a5a486ad bb322938 -- adapters domain application bootstrap pom.xml outils .github` sort 0 (le commit `bb322938` ajoute 108 lignes a `planning.md`, rien d'autre).
- **Ce que ce fichier ne fait pas** : il ne prononce ni Done technique ni Done pedagogique, ne fusionne rien, ne corrige rien. Le Done technique est celui de la CI sur le commit pousse, **que je n'ai pas lue**. Le Done pedagogique est a `nadia-prof`.
- **Verdict, par job de `ci.yml`, tel que je l'ai lance sur mon poste** : voir la section 1. Aucun verdict n'est deduit d'un autre job.

## Resume

| Code | Constat | Etat |
|---|---|---|
| F1 | `build` : reacteur complet, 2669 tests, 0 echec, 0 erreur, 0 ignore, JDK 21.0.10. Ecart avec 2669 : 0 | verte, constatee |
| F2 | `chemins`, `prefixes` : toutes les etapes lancees, sorties 0 | vertes, constatees |
| F3 | `outils` : 13 juges sur 13 sortent 0 avec `TMPDIR` en forme POSIX ; avec `TMPDIR` en forme mixte, 12 sur 13 (`mvn-jdk21-test.sh` : 10 cas en echec, 4 ok) | verte / rouge selon la forme de `TMPDIR` |
| F4 | Etape "Package du fat jar" : lancee sous une forme voisine (sans tests), le jar demarre sur le port 18541 | partielle, dite |
| F5 | 14 lancements sur copies (13 mutations, `X2` en deux perimetres), au moins une par lot : 7 rouges, 7 verts. Les verts sont 5 proprietes non gardees (dont `E3` et `nombreDeManches` de `-24`) et 1 trou de garde-fou (`Z1`) | section 2 |
| F6 | Commandes `PN8`, `FM5`, `FM6`, `RC1`, `RD3` : toutes rendent la valeur attendue sur `bb322938` | verte, constatee |
| F7 | `RL5`, `RL6`, `RL7` : les trois commentaires sont encore faux, aucun commit de code depuis `19c8a7b2` | confirme |
| F8 | Avis `TMPDIR` : reconfirme, avec une mesure qui le prouve. Avis `node` et Python : reconfirme | section 4 |

## 1. Jobs de `ci.yml` lances

**Methode.** Worktree `s57-yanis-p2-cloture`, cree depuis `bb322938`, jamais modifie par une mesure (les mutations vivent dans des copies, section 2). `TMPDIR` dans mon repertoire de session, en forme POSIX. JDK : celui que la machine virtuelle des tests rapporte, lu dans `java.version` de **313** rapports Surefire et Failsafe, une seule valeur, **21.0.10** ; la premiere ligne de l'enveloppe dit `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10`. La CI distante n'est pas lue : ces verdicts sont ceux de mon poste.

| Job | Etapes lancees | Sortie | Porte technique du palier 2, ce job, sur mon poste |
|---|---|---|---|
| `build` | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` | `BUILD SUCCESS`, 6 min 33 s, six modules `SUCCESS` | **verte** |
| `build`, etape "Package du fat jar" | **non lancee sous sa forme** (`mvn -B -pl bootstrap -am package`). Lancee : `bash outils/mvn-jdk21.sh -B -DskipTests package` | `BUILD SUCCESS`, 8 s, sortie 0 | **non jugee** : forme voisine (tests sautes), voir le detail ci-dessous |
| `chemins` | `verifier-chemins-test.sh` ; `git ls-files` (moins le test du garde-fou) dans `verifier-chemins.sh` | sorties 0 et 0, *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* | **verte** (relancee avec ce rapport present, section 7) |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-prefixes.sh`, `verifier-garde-fou-accent-test.sh`, `verifier-garde-fou-accent.sh` | tous 0 (detail ci-dessous) | **verte** |
| `outils` | trois balayages (6, 2, 5 fichiers), 13 juges | 13 sortis 0, aucun balayage vide, *"13 juge(s) pur(s) execute(s)."* | **verte** avec `TMPDIR` POSIX ; **rouge** avec `TMPDIR` mixte (F3) |

**`build`, comptes.** Somme des six lignes `Tests run` du reacteur : 120 (domain), 31 (application), 501 (adapter-web), 1630 (adapter-content), 381 (bootstrap Surefire), 6 (bootstrap Failsafe) = **2669**. Recomptee a part par l'attribut `tests` des 313 fichiers `TEST-*.xml` : **2669**, 0 echec, 0 erreur, 0 ignore. **Ecart avec 2669 : 0**, comme attendu : les fusions de `a5a486ad` a `bb322938` ne portent que `squad/`. Classes des lots, comptees dans leurs XML : `PaquetCe1PronomsTest` 37, `PaquetCe1TexteNarratifTest` 32, `PaquetCe1FamillesDeMotsTest` 24, `LaPhraseDeRevelation...Test` 2, `PartieControllerPropositionsEnImageTest` 5, `TexteAccentueDesGabaritsTest` 9, `LesPropositionsEnImageAtteignentLEcran...Test` 14, `LaFormeCommune...Test` 5, `UnEcranNeGouvernePasLApparenceDUnAutreTest` 7, `AucunMotServiNeSEcarteDeSonAccentTest` 7, tous 0 echec.

**Etape "Package du fat jar".** Ce que j'ai lance n'est pas la commande de la CI : la CI lance `mvn -B -pl bootstrap -am package` (tests compris), j'ai lance `-DskipTests package` sur le reacteur entier, parce que `verify` venait de jouer les tests. `bootstrap/target/recreo.jar` : 22 493 243 octets, SHA-256 `814c20c09a341f5b...` (16 premiers caracteres). **Je ne sais pas si le second repackage a reecrit le fichier** : sa date de modification est celle du `verify` (20:26), la ligne `Replacing main artifact ... with repackaged archive` est dans la sortie. Demarrage : `java -jar bootstrap/target/recreo.jar --server.port=18541`, journal : `Starting RecreoApplication ... using Java 21.0.10`, `Tomcat started on port 18541`, `Started RecreoApplication in 5.835 seconds`. Requetes : `GET /` 200 (8421 octets), `/niveaux/CP`, `/niveaux/CE1`, `/niveaux/CM1`, `/css/recreo.css` 200 chacune. Arret par le PID lu dans le journal (25180) ; apres : 0 ecoute sur 18541, PID absent. Le port 19241 n'a pas servi (aucun navigateur). Aucun ecran n'est joue.

**`prefixes`, plages.** `github.event.before` d'un push n'est pas connu de moi. Deux bases essayees : `0ea24709` (12 sujets hors fusions, `commit-atteignable.sh` 0, `verifier-prefixes.sh` 0, *"Tous les commits portent un prefixe d'agent connu."*) et `bb322938^` (1 sujet, memes sorties). `verifier-garde-fou-accent.sh` sur `0ea24709..HEAD` et sur `bb322938^..HEAD` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0. `verifier-garde-fou-accent-test.sh` : *"21 cas passent"*. Le job fait `fetch-depth: 0`, mon worktree a l'historique complet.

**`outils`, deux reserves.** (a) J'ai lance les juges avec `python` : sur ce poste `python3` est un executable du Store qui echoue, alors que `ci.yml` choisit `python3` des que `command -v python3` reussit. La branche `python3` de `ci.yml` n'est donc pas celle que j'ai exercee. (b) `TMPDIR` : F3 et section 4.1.

**Verification apres ecriture de ce fichier.** `chemins` et les tests de bootstrap qui lisent `squad/` sont relances apres l'ajout du fichier, voir la section 7.

## 2. Controle croise par lot : 14 lancements de mutation

**Methode.** Copies de l'arbre `bb322938` par `git archive` (quatre copies et une reference, hors de tout worktree du depot). Chaque mutation passe par un script que j'ai ecrit et qui **refuse** si le motif n'est pas trouve le bon nombre de fois (une seule fois partout, les refus rencontres sont en section 6). Apres chaque mutation : controle que la ressource compilee de la copie est egale a la source mutee (`cible==source`, 11 mutations de ressources), puis restauration par copie de la reference et comparaison octet a octet (`RESTAURE identique`, 14 fois sur 14). A la fin, `diff -rq` de chaque copie contre la reference, hors `target` : 0 difference, quatre fois. **Temoin vert avant toute mutation**, meme commande sur une copie non mutee : `BUILD SUCCESS`, lignes 120, 31, 501, 1630, 381, 0 echec. **Perimetre des commandes** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` (domain, application, adapter-web, adapter-content, bootstrap Surefire, **sans Failsafe**) pour les mutations de contenu, de gabarit et de feuille ; `-pl adapters/adapter-web -am test` pour la garde `C1'` et la forme ; `-pl bootstrap -am verify` limite aux 6 tests Failsafe pour `X2b` ; le juge `.sh` seul pour le script. `-am` partout. Les copies viennent de `git archive` : fins de ligne CRLF (conversion de l'extraction), pas de depot `.git`.

| Code | Lot | Mutation | Motifs | Resultat |
|---|---|---|---|---|
| `X1` | `-05` corrige | indice de l'item 9 : les trois U+00A0 (deux chevrons, le deux-points) remplaces par des espaces ordinaires | 1 | **rouge**, 2 echecs : `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` et `LeTexteServiPorteLeCaractereDecideJamaisSaNotationTest.chaqueTexteServiEnProductionPorteSesCaracteres` |
| `X2a` | `-24` | `nombreDeManches: 10` devient `5` dans l'entree `bandit-manchot-familles-de-mots-ce1` | 1 | **vert** (Surefire, 5 modules) |
| `X2b` | `-24` | meme mutation, Failsafe seul | 1 | **vert** (6 tests, 0 echec) |
| `X3` | `-24`, point `E3` | `bonneReponse: "chaton"` devient `"chatte"` | 1 | **vert** : `E3` est toujours ouvert sur l'arbre fusionne |
| `X4` | `N1` et `N2` | indice de l'item 11 : le mot cite *aussi* devient *fleur*, qui figure deux fois dans la phrase designee | 1 | **vert** |
| `X5` | `RCT-CE1-R1` | `bonneReponse` d'Ibrahim : *"s'assoit pres de la fenetre"* devient *"... de la porte"* (mot absent du texte) | 1 | **rouge**, 1 echec : `PaquetCe1TexteNarratifTest`, `verifierQueChaquePropositionEstDansLeTexte` |
| `X6` | `RCT-CE1-R1` | Ines : la bonne reponse et un distracteur echanges (bonne reponse *"sort dehors"*, le premier fait au lieu du dernier) | 1 | **vert** |
| `X7` | script d'`additions-cp` | `spawn('taskkill', ['/IM', 'chrome.exe'])` ajoute | 1 | **rouge** : `ECHEC le script porte une commande d'arret par nom d'image` (juge `.sh`, sortie 1) |
| `X8` | script d'`additions-cp` | `spawn('powershell', ['-Command', 'Stop-Process -Name chrome'])` ajoute, arret par nom d'image sans l'un des trois mots du balayage | 1 | **vert** (juge `.sh`, sortie 0, 8 `ok`) |
| `X9` | script d'`additions-cp` | `a + b <= somme` devient `a + b < somme` | 1 | **rouge**, 2 echecs : *"54 enonces ... attendu 65"* et la reference a 5 |
| `X10` | `RDO-CP-R3` | l'espace ordinaire entre les deux phrases devient `&nbsp;` | 1 | **rouge**, 1 echec sur 2 : `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest`, *"la premiere phrase n'est pas suivie d'une espace ordinaire"* |
| `X11` | forme commune | `R3-5` : `margin-top: 0` devient `0.5rem` | 1 | **rouge**, 1 echec sur 5 : `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.laFormeCommuneEstCelleQuiAEteMesureeSurLaProduction` |
| `X12` | garde `C1'` | `Z1` rejoue : `.ecran-de-jeu:has(.consigne, .est-faux) .propositions { row-gap: 0; }` en fin de feuille | 1 | **vert**, adapter-web 501 tests, 0 echec : `Z1` reste ouvert |
| `X13` | garde `C1'` | `.ecran-de-jeu:has(:where(.est-faux)) .propositions { row-gap: 0; }` (forme non listee dans mes rapports precedents) | 1 | **rouge**, 1 echec : `UnEcranNeGouvernePasLApparenceDUnAutreTest.aucuneRegleDontLeSujetEstHorsDuMessageNeNommeCeQueSeulLeMessagePose` |

Les 14 lancements sont `X1` a `X13`, `X2` comptant deux perimetres (`X2a`, `X2b`) : 7 rouges (`X1`, `X5`, `X7`, `X9`, `X10`, `X11`, `X13`), 7 verts.

**Lecture, sans conclusion au-dela de la mesure.**

- **`E3`, ou il en est.** La correction de `sofia-sm` (en tete de `rapport-sofia-dev-palier-2.md`) l'a classe *"non garde en Java"* et refuse le test d'egalite ; `FM5-2` et `FM5-3` gardent l'item par commande (section 3). `X3` confirme sur l'arbre fusionne que le reacteur ne rougit pas. Rien n'a change depuis mon rapport sur le lot `-24`.
- **`nombreDeManches` de `-24`** (`X2a`, `X2b`) : non garde, ni par Surefire ni par Failsafe. C'est la valeur que le palier 1 (`C8`) a notee non gardee ; elle l'est encore, pour ce paquet.
- **`N2`** : la clause *"chaque mot cite figure une fois dans la phrase designee"* n'est lue par aucun test du reacteur (`X4`). Je n'ai pas mesure les autres items de la clause.
- **`X1`** : le reacteur voit la perte des U+00A0 de l'indice de `-05` par le cliquet de dette `TYP-1`, un garde-fou qui compte des fichiers, pas par un test du paquet. Je n'ai pas determine laquelle des trois substitutions chacun des deux tests voit.
- **`RCT-CE1-R1`** : une proposition hors du texte est refusee (`X5`) ; l'echange de la bonne reponse avec un distracteur ne l'est pas (`X6`). C'est la propriete *"ordre des faits"* de la section 5.
- **Script** : le balayage du critere 5 mord sur `taskkill` (`X7`) et ne voit pas `Stop-Process -Name` (`X8`). Le critere dit *"aucun arret par nom"* ; le juge en lit trois formes (`taskkill|pkill|killall`). Le fait a un precedent ecrit (arret par nom d'image ayant ferme des navigateurs personnels). **Je ne propose pas de correction** : le juge est a `marc-dev`, le critere a `camille-po`.
- **`Z1`** (`X12`) : la cause est inchangee, `entete.split(",")` a la ligne 217 de `LAnimationRetardeMaisNeDetermineRienTest.java` (`git grep -n 'split(",")'`), et aucun commit n'a touche cette classe ni la garde depuis `b0c47c97`. `Z2` (CSS imbrique) n'est pas rejoue.

## 3. Commandes des stories, sur `bb322938`

**Methode.** Les commandes sont extraites de `docs/backlog/backlog.md` par un script de mon espace de travail (je ne le commite pas), puis executees telles quelles, `<ref>` = `bb322938`. Exceptions, ecrites comme dans le backlog : `PN8-6` et `FM5-6` prennent leur base amendee (`aa9d703c`, `4004fab767102ace22230516a28ddceb98fe30c1`), et non `git merge-base round-two <ref>` ; `RD3-6`, `RD3-7`, `RD3-8` se lisent sur le commit livre `23222465`. Rouge sur la base : les memes commandes rejouees sur `e8d14ddb` (`PN8`) et `78270b4c` (`RC1`, `RD3`).

| Code | Attendu | `bb322938` | Sur la base (rouge attendu) |
|---|---|---|---|
| `PN8-1` a `PN8-5` | 2, `10 13 `, 0, 5, 0 | 2, `10 13 `, 0, 5, 0 | memes valeurs (clauses) |
| `PN8-6` (base `aa9d703c`) | 13 | 13 | 5 |
| `PN8-7` a `PN8-11` | 5 chacun | 5, 5, 5, 5, 5 | 3, 3, 4, 3, 3 |
| `PN8-12` | 0 | 0 | 2 |
| `PN8-13a`, `PN8-13b` (base `e8d14ddb...`) | 8, 18 | 8, 18 | 0, 0 |
| precondition `PN8-13` | 1 | 1 | |
| forme a parents, `02b4a34e` (3 mots) | 8, 18 | 8, 18 | |
| `PN8-14` | 37 tests, 0 echec | 37 tests, 0 echec (XML du reacteur, pas la commande `-pl`) | |
| `FM5-1` | 0 | 0 | |
| `FM5-2` | 4 | 4 | |
| `FM5-3` | `  # FAM-CE1-2: a, b` | `  # FAM-CE1-2: a, b` | |
| `FM5-4`, `FM5-5` | 24, 14 | 24, 14 | |
| `FM5-6` (base `4004fab767102ace22230516a28ddceb98fe30c1`) | 34 | 34 | |
| precondition `FM5-6` | 1 | 1 | |
| forme a parents, `9f110983` (3 mots) | 34 | 34 | |
| `FM6` a, b, c | 14, 0, 28 | 14, 0, 28 | |
| `RC1-1` a `RC1-3` | 3 chacun | 3, 3, 3 | 1, 1, 2 |
| `RC1-4`, `RC1-5`, `RC1-6` | 3, 10, 0 | 3, 10, 0 | 0, 0, 0 |
| `RC1-7` (sur `c77560f2`) | 0 | 0 | |
| precondition `RC1` | 1 | 1 | |
| forme a parents, `4e5c9314` (3 mots) | 3, 10, 0 | 3, 10, 0 | |
| `RD3-1` a `RD3-5` | 1, 0, 0, 0, 3 | 1, 0, 0, 0, 3 | 0, 1, 0, 4, 1 |
| `RD3-6`, `RD3-7`, `RD3-8` (sur `23222465`) | 5, 0, 0 | 5, 0, 0 | |
| `RD3-9`, `RD3-10` (base `78270b4c`) | 2, 0 | 2, 0 | 0, 0 |
| precondition `RD3-9`/`RD3-10` | 1 | 1 | |
| forme a parents, `19c8a7b2` (3 mots) | 2, 0 | 2, 0 | |
| `RD3-7` en forme fusion ; fichiers hors `squad/` | 0 ; 5 | 0 ; 5 | |
| `RD3-11`, `RD3-12`, `RD3-13` | 9, 6, 2 | 9, 6, 2 | |

- Les colonnes "base" de `PN8-7` a `PN8-12` et de `RC1-1` a `RC1-5` redonnent les valeurs rouges annoncees par `camille-po` ; `marc-dev` ne les avait pas rejouees pour `PN8` (son rapport, section 1). `FM5` n'a pas de colonne rouge ici : `round-two` contient deja le lot.
- **Ce que ces commandes ne disent pas** : `PN8-14` et `RC1` (test du paquet) sont lues dans les XML du reacteur, pas par la commande `-pl ... -Dtest=` de la story ; elle n'a pas ete lancee. `FM4` (famille exacte de *chaton*) et l'orthotypographie ne se lisent pas par commande.

## 4. Les deux avis

### 4.1 `TMPDIR` et `outils/mvn-jdk21-test.sh`

**Avis reconfirme : normaliser, `BAC="$(cd "$(mktemp -d)" && pwd)"`, pas refuser.** `ci.yml` relu : le job `outils` lance `bash "${test}"` pour tout `outils/*-test.sh`, sans fixer `TMPDIR` ; sur `ubuntu-latest` il est POSIX, le cas ne s'y produit pas, et le script ne change rien la-bas. Ce que j'avais ecrit sans le mesurer est maintenant mesure :

| Cas | Sortie |
|---|---|
| script du depot, `TMPDIR` POSIX | sortie 0, *"Tous les cas passent"* |
| script du depot, `TMPDIR` en forme mixte (lettre de lecteur, deux-points, barres obliques) | **sortie 1, 10 cas en echec, 4 `ok`** : les chiffres de `sofia-sm` sont reproduits |
| script du depot, `TMPDIR` a barres inverses | sortie 0 ; **je n'explique toujours pas pourquoi** cette forme passe |
| copie du script avec la forme `cd + pwd`, `TMPDIR` mixte | **sortie 0, 14 `ok`** |
| meme copie, `TMPDIR` a barres inverses, puis POSIX | sortie 0 dans les deux cas |

La copie vit hors du depot, `ci.yml` et le script ne sont pas modifies. **Correction a mon avis precedent** : je ne savais pas si le defaut touchait d'autres juges. Les 13 juges lances un par un avec `TMPDIR` mixte : **12 sortent 0, seul `mvn-jdk21-test.sh` sort 1** (je n'ai pas lu pourquoi les douze autres y echappent : seule la sortie est mesuree). La correction tient donc en une ligne dans un seul fichier. **Ce que l'avis ne garantit pas** : la CI Linux n'executera jamais la forme mixte, la correction n'aura pas de contre-exemple en CI (preuve manuelle et datee, `ADR-0009`). Le script est a `theo-archi` ou `sofia-sm`.

### 4.2 `node` et Python dans le job `outils`

**Avis reconfirme : declarer `node` par `actions/setup-node` avec une version majeure fixee, dans le seul job `outils`, et, meme famille, Python ; sinon aucun des deux. Ce n'est pas un blocage et ce n'est pas un faux vert.** `ci.yml` relu ce jour : dernier commit `bac0a6d1`, aucun `setup-node`, aucun `setup-python` ; Java est declare par `actions/setup-java` dans le job `build`. Faits :

- Un seul des 13 juges lance `node` : `outils/mesurer-additions-cp-test.sh` (`command -v node` ligne 34, sortie 1 avec `ECHEC node absent` s'il manque, puis `node` aux lignes 46 a 96). L'absence est rouge et bruyante, pas silencieuse.
- Python : cinq juges `.py` (la commande vient de `ci.yml`, `python3` puis `python`) et `squad/outils/nadia-prof/balayage-largeur-rangee-test.sh`, qui cherche lui-meme un Python 3 et refuse sinon. Les modules importes sont `datetime`, `pathlib`, `contextlib`, `importlib.util`, `io`, `json`, `sys`, `tempfile`, `unittest` : toute version 3 convient, le choix de la majeure est libre.
- **Ce qui a change depuis mon avis** : rien dans `ci.yml`. **Ce que je corrige** : l'en-tete du job dit que les juges s'executent *"sans aucune dependance au poste"* ; depuis `baefae1d` (`[MARC]`, juge du script d'`additions-cp`) l'un d'eux depend de `node`, et le commentaire n'est plus exact.
- **Non mesure** : la version de `node` et de Python sur l'image `ubuntu-latest` (CI distante non lue). Sur mon poste : `node` v24.12.0, Python 3.14.0, valeurs sans portee pour la CI.

## 5. Ce que personne ne garde

**Methode.** Lecture des tests par `git grep` sur `bb322938` et, quand c'est possible, mutation (section 2). `nadia-prof` est seule garde de ces proprietes : aucun test du reacteur, aucun juge de `ci.yml` ne les lit.

| Propriete | Ce qui existe a cote | Preuve de l'absence |
|---|---|---|
| Position de l'indice au-dessus de y = 551 a 744 de large | `LaFormeCommune...Test` epingle les 14 declarations de la feuille ; `outils/juger-pli-deux-oeuvres.py` juge des cotes mesurees, pour l'ecran a deux oeuvres seulement ; la mesure elle-meme (Chrome, jar) n'est lancee par aucun job | `git grep -n '\b551\b'` dans les `*Test.java`, `*-test.sh`, `*-test.py` : seuls des commentaires et le juge de l'ecran a deux oeuvres. Une valeur de contenu qui allonge un indice ne rougit rien |
| Roles par genre (`TRV-CE1-6`) | equilibre des prenoms distincts (`PaquetCe1PronomsTest`, `PaquetCe1TexteNarratifTest`) ; absence de sujet *tu* (`PaquetCe1AccordSujetVerbeTest`) | le Javadoc de `PaquetCe1PronomsTest` l'ecrit : *"qu'aucun role ni aucune activite ne soit distribue par genre (TRV-CE1-6)"* est dans "Ce que ce test ne lit pas" ; `marc-dev`, section 4.4 de son rapport, idem. Lu, pas mute |
| Ordre des faits (`RCT-CE1-R1`) | une question d'ordre exige un texte d'au moins trois phrases (`rct3...`) ; toute proposition est dans le texte (`rct2s6`) | `X6` : la bonne reponse d'Ines echangee avec un distracteur ne fait rougir aucun test. `rct3` compte des phrases, pas des faits |
| Phrase de revelation dans sa boite (`RD3`, critere 1) | le texte et ses U+00A0 (`LaPhrase...Test`, `X10` rouge), la valeur des cotes de la feuille (`X11` rouge) | aucun test ne mesure un bord droit contre une boite. La mesure est celle de `lea-dev` (36 sur 36, marge minimale 38 px) puis de `nadia-prof`, au jar, une police du poste, un navigateur. Texte et feuille epingles : la propriete tient tant que ni l'un ni l'autre ne bouge et que la pile de polices est celle mesuree |

**Autres proprietes que les rapports du palier declarent non gardees**, relevees sans les remesurer : la famille exacte de *chaton* (`FM4`, `E3`) ; que le sens departage deux candidats de meme genre (`PRN-CE1-2`) ; que la phrase de revelation se lise comme la suite du verdict (*"aucune cote ne le garde"*, planning du 20:15, section 3) ; l'accentuation d'un affixe cite (`ADR-0065` A3, decision 3) ; la classe des homographes.

**Les trois commentaires devenus faux, confirmes sur `bb322938`.** `git log --oneline 19c8a7b2..bb322938 -- adapters bootstrap` ne rend rien : le commit de `sofia-sm` qui devait les corriger n'existe pas. La source de la phrase est `reponse-nadia-pa7-pa8.md`, section 2.4 (ligne 122) et `NP4` (ligne 21). L'ancienne phrase, *"C'est cette oeuvre qui va avec. Appuie dessus."*, est dans `ARBITRAGE-PREALABLE-s55-instance-a-francais-ce1.md`, section `M4` (ligne 162).

| Code | Fichier, lignes | Ce qui est faux |
|---|---|---|
| `RL5` | `adapters/adapter-web/src/main/resources/templates/fragments/manche.html`, 207 a 213 | *"cette phrase, de nadia-prof (M4)"* : la phrase servie n'est plus celle de `M4` de l'arbitrage s55 |
| `RL6` | `adapters/adapter-web/src/test/java/com/recreo/adapter/web/TexteAccentueDesGabaritsTest.java`, 158 a 160 | *"M4 de ARBITRAGE-PREALABLE-s55... section 3.2 ... copie tel quel"* : le texte copie est celui de `reponse-nadia-pa7-pa8.md` 2.4 |
| `RL7` | `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest.java`, 107 | *"M4 du meme arbitrage, mot pour mot"* |

**A relire, que je ne range pas parmi les faux** : `LaPhraseDeRevelationDUneMancheEnImageNeSeCoupeJamaisDansUnePhraseTest` garde le nom `M4` dans son Javadoc (ligne 20, `S57-FORME-C-PHRASE-M4`), dans la constante `PHRASE_DE_M4` (ligne 43) et dans trois messages (lignes 65, 71, 133). `M4` y designe l'emplacement du texte, qui existe toujours ; c'est le texte de l'arbitrage s55 qui n'est plus le sien. A `theo-archi` de dire si c'est un quatrieme commentaire de la meme famille. Je ne corrige rien : le commit est engage par `sofia-sm`, pas par moi.

## 6. Ecarts de ma methode

| Code | Ecart |
|---|---|
| `W1` | **J'ai ecrase mon propre journal.** Mon premier temoin (copie 1) et le juge du script (copie 4) ecrivaient dans le meme fichier de journal ; le journal du temoin est devenu illisible. Les comptes de ce temoin restent dans ses XML (2663, 0 echec). **Le temoin a ete relance seul, avec un journal propre : `BUILD SUCCESS`, lignes 120, 31, 501, 1630, 381.** C'est celui que cite la section 2 |
| `W2` | **J'ai lance deux mutations dans la meme copie en meme temps** (`X4` et `X11`), par une erreur de ma commande. Les deux ont ete relancees seules, l'une apres l'autre, avec les ressources compilees supprimees avant. Les resultats du premier lancement (vert et rouge) sont les memes ; je ne cite que ceux des relances |
| `W3` | Deux scripts de mutation ont refuse avant de tourner : `X2` (motif cherche en fins de ligne LF, le fichier extrait est CRLF) et `X5` (le motif court figure deux fois dans le fichier, une fois dans le texte support). Corriges, puis chaque mutation de ce rapport a trouve son motif exactement une fois |
| `W4` | Mon comptage par XML (`tests` de chaque fichier) agrege aussi les rapports d'une execution precedente restes dans la meme copie : pour les mutations, je ne cite que les lignes `Tests run` de Maven |
| `W5` | Les mutations vertes (`X2a`, `X3`, `X4`, `X6`, `X8`, `X12`) sont vertes **dans le perimetre de la commande** (section 2) : `X2b` a ferme le cas Failsafe pour `X2a` seulement ; `X3`, `X4`, `X6` n'ont pas ete rejouees avec Failsafe ; `X12` n'a pas ete rejouee dans `bootstrap` |
| `W6` | Une redirection ecrite par erreur vers un chemin a la racine du systeme de fichiers a ete refusee (*Permission denied*) : rien n'a ete cree, ni en `/tmp` ni ailleurs |
| `W7` | Je n'ai pas lu la CI distante, pas rejoue `FM5-2` sur le fichier mute de `X3` (le rapport sur `-24` donne 3 sur ce meme motif), pas mesure `Z2` |

## 7. Verifications apres ecriture de ce fichier

Faites une fois ce fichier indexe (`git add`), avant le commit.

- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins le test du garde-fou), ce fichier compris, dans `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`prefixes`** : le sujet prevu de mon commit passe dans `verifier-prefixes.sh`, sortie 0.
- **Tests de `bootstrap` qui lisent `squad/`** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test` restreint a `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` (10), `AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest` (5), `ConcordanceDesTagsDAgentTest` (1), `ToutesLesReferencesDeDecisionResolventTest` (3) : `BUILD SUCCESS`, 19 tests, 0 echec (perimetre `bootstrap` et ses amonts, `-am` present). **Ce n'est pas `mvn verify`.**
- **Second `build` complet**, ce fichier indexe (sans cette ligne, ajoutee apres le run) : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, 5 min 13 s, lignes 120, 31, 501, 1630, 381 et 6 d'integration, **2669 tests**, 0 echec, 0 erreur, 0 ignore ; 313 fichiers `TEST-*.xml`, `java.version` 21.0.10 seule valeur. `git diff --quiet bb322938 -- adapters domain application bootstrap pom.xml outils .github` sort 0 : le code est celui de `bb322938`.

## 8. Ce que ce rapport ne fait pas

Ne prononce aucun Done, technique ou pedagogique. Ne juge ni un texte, ni une cote, ni la typographie. Ne fusionne rien, ne pousse rien. Ne decide rien : le texte des commentaires est a `theo-archi` et a l'agent qui prend le commit, le juge du script est a `marc-dev`, le critere 5 de `S57-SCRIPT-ADDITIONS-CP` est a `camille-po`, `ci.yml` et `mvn-jdk21-test.sh` sont a `theo-archi` ou `sofia-sm`.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est creee ou amendee.**
