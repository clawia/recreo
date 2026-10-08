# Rapport qualite de cloture - s54

**Auteur** : `yanis-qa`. **Date** : 2026-10-08. **Arbre mesure** : `round-two` a `542c1c6e` (hash complet
`542c1c6ea943085835dd0ca8b5c8b75809585982`), sommet fusionne de s54, mesure dans le worktree de la branche
`s54-yanis-cloture`. `git status --porcelain` vide avant et apres les mesures.

**Perimetre** : les quatre jobs de `.github/workflows/ci.yml`, relu avant la mesure ; puis les criteres d'acceptation
chiffres de s54 (backlog "Sprint 54", `DEC-0114`, planning "Criteres d'acceptation"), un par un. Ce rapport porte la
porte **technique** uniquement. Il ne prononce aucun Done pedagogique : les verdicts cites sont ceux de `nadia-prof`,
lus dans ses fiches, et ne sont pas rejoues ici.

Adaptations de ce poste, sans effet sur le verdict : `mvn -B verify` est lance par l'enveloppe
`outils/mvn-jdk21.sh -B clean verify` (JDK de la toolchain, `clean` en plus) ; dans le job `outils`, la sortie des
juges n'a pas ete conservee, seul leur code de sortie l'a ete.

## 1. Les quatre jobs, sur `542c1c6e`

### `prefixes` : vert

- `bash .github/scripts/verifier-prefixes-test.sh` : `Tous les cas passent`, code 0.
- `bash .github/scripts/commit-atteignable-test.sh` : `Tous les cas passent`, code 0.
- `git log --no-merges --format=%s origin/round-two..HEAD | bash .github/scripts/verifier-prefixes.sh` : `Tous les
  commits portent un prefixe d'agent connu.`, code 0. Plage : 29 sujets hors fusions (48 commits, 19 fusions).
  `origin/round-two` est `52e2b0cd` sur ce poste. Ce n'est pas la plage `github.event.before..GITHUB_SHA` d'un push
  reel : la branche `BASE` du workflow n'est pas exercee. `commit-atteignable.sh origin/round-two` : code 0.
- Le commit de ce rapport n'est pas dans cette plage ; il est verifie apres son commit (section 7).

### `chemins` : vert

- `bash .github/scripts/verifier-chemins-test.sh` : `Tous les cas passent`, code 0.
- `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh' | bash .github/scripts/verifier-chemins.sh` :
  `Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies.`, code 0, mesure avant l'ajout de ce
  rapport (reverifie en section 7).

### `outils` : vert

Boucle du workflow rejouee, trois motifs : `outils/*-test.sh` 5 juges, `squad/outils/*/*-test.sh` 2 juges,
`outils/*-test.py` 3 juges. **10 juges purs executes, 10 codes de sortie 0**, aucun motif vide. `python3` repond
(3.14.0). Le troisieme juge Python, `outils/rapport-tokens-journal-test.py`, est celui de `S51-OUTIL-JOURNAL-TOKENS` ;
s53 en comptait 9, s54 en compte 10.

### `build` : vert

- Commande : `bash outils/mvn-jdk21.sh -B clean verify`, racine, reacteur complet, sans `-pl`. Resultat :
  `BUILD SUCCESS`, 3 min 23 s, code 0, les six modules `SUCCESS`.
- Pas de package : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` (le `-am` est celui du workflow). Resultat :
  `BUILD SUCCESS`, 2 min 28 s, code 0, `bootstrap/target/recreo.jar` present (22 371 254 octets), journal :
  `Replacing main artifact ... with repackaged archive`. Ce pas relance les tests unitaires : memes comptes que
  ci-dessous (120, 17, 401, 1360, 312).
- **JDK** : l'enveloppe rend `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10` ; la machine virtuelle des tests
  rapporte `Started ... using Java 21.0.10` (20 occurrences dans le journal de `verify`, une seule valeur).

## 2. Compte de tests et ecart avec la base

Methode : lignes `Tests run: N, Failures: F, Errors: E, Skipped: S` de fin de module de surefire dans la sortie du
`clean verify`, recoupees par la somme de l'attribut `tests` des rapports XML de surefire et de failsafe sous chaque
`target/`. Les deux concordent.

| Module | surefire | failsafe | echecs | erreurs | ignores |
|---|---|---|---|---|---|
| domain | 120 | - | 0 | 0 | 0 |
| application | 17 | - | 0 | 0 | 0 |
| adapter-web | 401 | - | 0 | 0 | 0 |
| adapter-content | 1360 | - | 0 | 0 | 0 |
| bootstrap | 312 | 6 | 0 | 0 | 0 |

**2216 tests au total (2210 unitaires et ArchUnit, 6 d'integration), 0 echec, 0 erreur, 0 ignore.**

**Delta : +42 sur la base 2174 de s53** (`54953faa`, rapport de s53 ; arbre de tests identique a `52e2b0cd` :
`git diff --stat 54953faa 52e2b0cd -- '*/src/test/*'` est vide). Tout le delta est dans `adapter-content` (1318 a
1360) ; les quatre autres modules ont le meme compte qu'a la base. Origine, par classe de test ajoutee
(`git diff --diff-filter=A 52e2b0cd HEAD -- '*/src/test/java/*'`, compte par classe lu dans les rapports XML) :

| Branchement | Commit | Classe | Tests |
|---|---|---|---|
| `S54-BR-FRA-CE1-18` | `3c52821a` | `PaquetCe1FuturTest` | 13 |
| `S54-BR-FRA-CE1-19` | `b9e9b9b3` | `PaquetCe1PasseComposeTest` | 13 |
| `S54-BR-EMC-CM1-02` | `ce6614db` | `PaquetCm1EmcAtteinteAutruiTest` | 10 |
| `S54-BR-ART-CP-10` | `c7d56d60` | `PaquetCpArtsOeuvresFamillesTest` | 6 |
| | | **Total** | **42** |

Aucun autre test n'est ajoute ni retire. Les classes modifiees n'ont aucun `@Test` ni `@ParameterizedTest` en plus
ou en moins (`git diff` filtre sur ces annotations : vide) : `PaquetCm1SciencesTerreTest` et
`PaquetCm1SciencesActiviteHumaineTest` (nom du paquet cible, `8edb4bb9`, `1e10ad3c`), `PaquetCm1GrandsNombresTest` et
`AucunSigneDoubleNEstDetachableDeSonMotTest` (`d958cfc6`), `TexteAccentueExposeParLePortEntrantTest` (5 tests a la
base et a `HEAD`, 67 lignes d'inventaire ajoutees, 0 supprimee). Les deux branchements de sciences (`SCI-CM1-15`,
`SCI-CM1-18`) n'ajoutent aucun test : ils recible des classes existantes. `theo-archi` avait annonce "le total ne bouge
pas" pour les cinq entrees seules (avis de faisabilite, passage 2) ; le total bouge parce que les developpeurs ont
ecrit un test de paquet pour quatre des six branchements (question `T1`). Delta annonce : **+42 sur `52e2b0cd`**.
`sofia-sm` etablit le total sur l'arbre fusionne.

## 3. Criteres communs

- **C1, decision d'abord : tenu.** `git merge-base --is-ancestor 5b759d1d <commit>` (`5b759d1d` ajoute `DEC-0114`,
  2026-10-07 21:02) rend OUI pour les six commits de branchement (`8edb4bb9`, `1e10ad3c`, `3c52821a`, `b9e9b9b3`,
  `ce6614db`, `c7d56d60`). `DEC-0114` a le statut `Proposee` ; je ne juge pas ce que cela change.
- **C2, diff borne : tenu, avec quatre YAML de paquet touches.** Chaque commit de branchement touche
  `catalogue.yaml`, un test de paquet (quatre cas) et l'inventaire du test d'accent. Les YAML de paquet touches avec
  leur diff (`git diff --numstat`) : `ce1-futur` 3/2 (`3c52821a`, K1 et K2 de `nadia-prof`), `ce1-passe-compose` 20/19
  (`b9e9b9b3`, K3 et K4), `cm1-sciences-terre-jour-nuit-saisons` 7/5 (`1e10ad3c`, item 4), `cm1-emc-atteinte-autrui` 4/4
  (`ce6614db`, items 2, 3, 9, 10), `cp-arts-oeuvres-familles` 21/17 (`ffbd2157`, corrections de l'instance C). Que
  chacune soit "decidee par `nadia-prof`" : `K1` a `K4` figurent dans son arbitrage prealable d'instance A
  (`grep -c` rend 1 pour chacune) ; pour les trois autres, la decision est citee dans le message de commit, je ne
  l'ai pas relue.
- **C3 : tenu.** `grep -c "paquet: <nom>$"` sur `catalogue.yaml` : base 0, `HEAD` 1 pour `ce1-futur`,
  `ce1-passe-compose`, `cm1-sciences-terre-jour-nuit-saisons`, `cm1-sciences-environnement-activite-humaine`,
  `cm1-emc-atteinte-autrui`, `cp-arts-oeuvres-familles`. `git diff 52e2b0cd HEAD -- catalogue.yaml` ajoute ces six
  lignes `paquet:` et n'en supprime aucune. Les autres paquets : 0 ligne ajoutee. Fichiers de `contenu/` sans entree :
  47 a la base, 41 a `HEAD` (methode : chaque nom de fichier cherche comme `paquet: <nom>$`) ; le planning dit 45 puis
  40, ecart de deux sur les deux dates que je n'explique pas.
- **C4 : tenu.** Section 1, `clean verify` de la racine vert. Les mesures partielles de la section 5 portent `-am`.
- **C5, aucun garde-fou assoupli : tenu sur le diff.** Diff des tests existants : deux constantes de nom de paquet
  (sciences), deux constantes de prefixe en espace insecable, et un cliquet qui descend :
  `AucunSigneDoubleNEstDetachableDeSonMotTest`, `cm1-grands-nombres` 26 a 12 et `DETTE_TOTALE` 516 a 502, donc plus
  strict. `TexteAccentueExposeParLePortEntrantTest` : 0 suppression.
- **C6 : tenu.** Les quatre jobs sont nommes avec leur commande en section 1.
- **C7, un branchement s'arrete au verdict : tenu.** `MATRICE-couverture.md` change sur cinq lignes entre `52e2b0cd` et
  `HEAD` : `FRA-CE1-18`, `FRA-CE1-19`, `SCI-CM1-15`, `SCI-CM1-18`, `EMC-CM1-02` passent a `couvert-valide`, chacune apres
  un verdict ecrit dans sa fiche (commits de `nadia-prof` `cd886512`, `aec2d9e2`, avant le commit de matrice `292763f7`) ;
  deux lignes sont reecrites sans changer de statut (`FRA-CE1-25`, `corps-cm1`) et `ART-CP-10` reste `non couvert`.
  Commande de la "Methode du compte" de la matrice, lancee sur les deux arbres : base 132 `couvert-valide`, 4 `en cours`,
  46 `non couvert`, 76 `non couvrable`, 258 lignes ; `HEAD` **137**, 4, **41**, 76, 258. 137 / 182 = 75,27 %, comme
  annonce par la matrice. Mention de couverture de `EMC-CM1-02` : la phrase "connaissances civiques et reconnaissance de
  situations seules ; aucun comportement, ... par ce produit" est dans la ligne et dans `competences-cm1.md`
  (comparaison apres retrait des accents et de la casse de balisage, deux sources, meme texte).

## 4. Criteres specifiques

Methode : chaque commande est lancee depuis la racine du worktree, base `52e2b0cd` puis `HEAD`. "Tenu" dit que ce qu'un
automate peut juger est tenu ; ce qu'aucun ne juge est dit ligne par ligne.

- **`S51-SCI-CORPS-CM1-RESERVES` : tenu pour le mesurable.** `grep -c "ne fait rien d'autre"` sur
  `cm1-sciences-corps.yaml` : base 1, `HEAD` 0. `grep -c '^ *- enonce:'` : 12 et 12. Le diff touche cinq items
  (3, 5, 6, 7, 11, commit `e9dd4d21`). Les six chaines ajoutees sont, une a une, dans l'arbitrage prealable de
  l'instance B (comparaison par script) ; le texte d'enonce de l'item 11 y est aussi (`grep -c` 1). Le backlog ne
  recopie pas les textes des items : "comme ecrits au backlog" se lit donc contre l'arbitrage, pas contre le backlog.
- **`S54-BR-SCI-CM1-15` et `-18` : C3 tenu, motifs en en-tete tenus, motifs de refus non gardes.** Les en-tetes de
  `cm1-sciences-terre-jour-nuit-saisons.yaml` et de `cm1-sciences-environnement-activite-humaine.yaml` nomment la fiche
  refusee et chaque motif ("motif 1 (item 4)", "motif 2 (item 11)", "motif (item 11)"). **Mutation** (section 5) : le
  distracteur "le printemps" remis sur l'item 11 de Terre, a la place de "l'automne", laisse verts
  `PaquetCm1SciencesTerreTest` seul (10 tests) et la suite `adapter-content,bootstrap` (1360 + 312). Aucun test
  ne ferme ce motif de refus : il tient a `nadia-prof`, comme le backlog le dit. Je n'ai mute ni l'item 4 de Terre ni
  l'item 11 de l'environnement.
- **`S54-BR-FRA-CE1-18` et `-19` : C3 tenu ; `TRV-CE1-5` relue par le developpeur, resultat ecrit dans son rapport : non
  trouve.** Le critere demande un resultat ecrit "dans son rapport". Il n'y a pas de rapport de `marc-dev` dans le
  repertoire du sprint (`propositions-marc-dev.md` seul), et `git log --grep="TRV-CE1-5"` ne rend aucun des deux commits
  de branchement. `nadia-prof` applique `TRV-CE1-5` dans les deux fiches. Je ne conclus pas que la relecture n'a pas eu
  lieu : je dis qu'aucun fichier ne la porte.
- **`S54-BR-EMC-CM1-02` : C3 tenu ; `EMC-9` relue par le developpeur, resultat ecrit : partiel.** Le message de
  `ce6614db` dit "EMC-9 sur la forme seulement" et renvoie la lecture de l'ecran entier a `nadia-prof`. Pas de rapport
  de `lea-dev` dans le repertoire. Mention de couverture : section 3, C7.
- **`S54-BR-ART-CP-10` : criteres 1, 3, 4, 5, 6, 8 tenus ; 2 et 7 en partie.**
  1. `grep -c "paquet: cp-arts-oeuvres-familles$"` : base 0, `HEAD` 1.
  2. C2 : le paquet est touche par `ffbd2157`, corrections de l'instance C (messages de commit), non relues ici.
  3. `grep -inE "reconna|rapproch"` sur les lignes `id`, `titre`, `consigne` de l'entree : rien (code 1).
  4. Les commits `c7d56d60` et `ffbd2157` ne nomment pas `MATRICE-couverture.md` ; `ART-CP-10` est `non couvert` a `HEAD`.
  5. Aucune espace ordinaire avant `? ! : ;` dans le bloc de l'entree ; titre accentue (`Les familles d'œuvres`).
  6. Plafond avant l'audit : la ligne de plafond (`Q6`) est dans l'arbitrage d'instance A, commit `b59e5150`, 2026-10-07
     21:31 ; l'arbitrage de l'instance C est `89910897`, 2026-10-08 12:14. Ordre tenu. `nadia-prof` y confirme la ligne
     au lieu d'en ecrire une seconde.
  7. Isolation de C : `89910897` ne touche qu'un fichier (`arbitrage-nadia-c.md`) et `46e3651c` qu'un fichier (la fiche).
     La branche `s54-nadia-c` est entierement fusionnee, je n'ai pas reconstitue son diff complet ni les noms de
     worktree et de scratchpad : non verifie.
  8. `c7d56d60` est ancetre de `89910897` : branchement avant audit, tenu.
  Fiche de l'audit : `46e3651c`, **VALIDE SOUS RESERVE**, la ligne reste `non couvert` (la fiche le dit, section "Mouvement de ligne : aucun").
- **`S53-REPORT-GRANDS-NOMBRES` : tenu.** `grep -cE '^ *- enonce: ".* :'` sur `cm1-grands-nombres.yaml` : base 14,
  `HEAD` 0. Un seul commit (`d958cfc6`) touche le fichier et change les 14 lignes (14/14) et les deux constantes de
  `PaquetCm1GrandsNombresTest` (2/2) ; la constante de `AucunSigneDoubleNEstDetachableDeSonMotTest` descend dans le meme
  commit. `clean verify` vert, aucun `@Test` ajoute ou retire dans ces classes.
- **`S51-OUTIL-JOURNAL-TOKENS` : tenu.** `outils/rapport-tokens-journal-test.py` : 7 tests, OK, lance par le job `outils`
  (motif `outils/*-test.py`). Les sept tests nomment : section chiffree conservee sans transcription, sortie qui nomme
  les sections conservees, sprint mesure recalcule, section sans chiffres inchangee, rapport de sprint chiffre conserve,
  lancement sans sprint qui ne modifie aucun rapport chiffre, mesure partielle qui ne remplace pas une section plus
  complete. **Rejeu sur le depot reel** : `python outils/rapport-tokens.py` sans `--sprint`, code 0,
  `sections conservees ... : s22, ..., s31`, puis `git status --short` : aucune modification d'un `RAPPORT-tokens.md` de
  s22 a s27 (0 ligne). Il modifie en revanche cinq autres fichiers suivis (`JOURNAL-tokens.md` et les rapports de s32,
  s37, s38, s40, dont les totaux sont recalcules) et cree un `RAPPORT-tokens.md` pour s54. Ce n'est pas un ecart au
  critere (il vise s22 a s27) ; j'ai restaure ces fichiers (`git checkout`) et supprime le fichier cree. `grep -c
  "Aucune consommation mesuree"` sur les six rapports s22 a s27 : 0.
- **`S53-PASSE-TRANSVERSE` : tenu.** `find squad -iname 'PASSE-TRANSVERSE-s54*'` rend un fichier. Il porte sept lignes
  `D1` a `D7` (je n'ai pas recompte les proprietes de la section 13.2 de l'arbitrage de s29 : `D1` a `D7` sont celles
  que la passe annonce), l'arbre `5cad6650`, le prix `0,5`.
- **`SYC-CE1-R1` et item 16 : non automatisable.** Commit `0a1f2b80` : un fichier, 3 lignes. Le rejeu est ecrit par
  `nadia-prof` dans la fiche de `FRA-CE1-25` ("`SYC-CE1-R1` levee", items 16, 19, 23). Non juge ici.
- **`S53-REPORT-REDACTION` : partiel, R3 non tenu.** R1 : `grep -c "0 fichier"` sur `DEC-0112` : base 1, `HEAD` 0.
  R5 : l'erratum est au backlog (`S53SPE-RELECTURE`). **R3 : `grep -n "six fichiers"` sur l'arbitrage de s53 rend la
  ligne 315 a la base et a `HEAD`** : le critere (rouge sur l'existant) n'est pas leve. Le backlog dit que R3 reste a
  `nadia-prof` ; il reste donc ouvert a la cloture.
- **`S54-ART-CP-10-PRIX` : tenu pour les trois points.** (1) l'avis de `theo-archi` (`avis-faisabilite.md`, `T6`)
  donne `ADR-0104` a `Accepte` (le fichier de l'ADR dit `Accepte le 2026-10-07 (s54)`) et un tableau de tailles ;
  (2) le plafond est la ligne `Q6` de l'arbitrage d'instance A ; (3) `git diff --name-only 52e2b0cd HEAD` ne nomme
  hors `squad/`, `docs/`, tests, `contenu/` et `catalogue.yaml` que `outils/rapport-tokens.py` et son test : aucun
  fichier sous `domain/`, `application/`, `adapters/*/src/main/java` ni `bootstrap/src/main`.
- **`PCO-CE1-R1`, hors engagement.** Le commit `c876fadc` (`marc-dev`) applique la reserve : `grep -c 'indice: "Ne
  recopie pas le verbe'` sur `ce1-passe-compose.yaml` rend **19** et `'indice: "Avec ce sujet, comment'` rend **2**, les
  chiffres du critere ; 8 lignes d'indice changent (8/8). Le backlog dit pourtant `OUVERTE, non engagee`.

## 5. Mise a l'epreuve

- **Mutation, motif de refus de `SCI-CM1-15` (item 11)** : fichier `cm1-sciences-terre-jour-nuit-saisons.yaml`, ligne 102,
  `"l'automne"` remplace par `"le printemps"`. Commande 1 : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am
  test -Dtest=PaquetCm1SciencesTerreTest -Dsurefire.failIfNoSpecifiedTests=false` : `BUILD SUCCESS`, 10 tests, 0 echec.
  Commande 2 : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content,bootstrap -am test` : `BUILD SUCCESS`, 1360 et 312
  tests, 0 echec. **Le garde-fou n'existe pas : un retour du motif refuse reste vert.** Fichier restaure par
  `git checkout`, `git status --porcelain` vide ensuite. Aucun commit ne porte cette mutation.
- Pas d'autre mutation : je n'ai pas mis a l'epreuve les garde-fous de population (accent, signe double, motifs de rang)
  ni les autres motifs de refus de s54, qui sont des jugements de `nadia-prof`.

## 6. Ecart de toolchain (`ADR-0071`)

- **Constat.** Deux fichiers `~/.m2/toolchains.xml` coexistent sur ce poste. Celui du `HOME` de ce shell declare une entree
  `21` qui designe un JDK 25 (`jdk-25.0.2`) : c'est celui que `nadia-prof` a lu. Celui que Maven lit (repertoire
  utilisateur de la machine virtuelle Java, different de `HOME` ici) declare l'entree `21` sur `jdk-21.0.10`, et les
  entrees 17 et 25. Mes mesures de la section 1 tournent sur ce second fichier : `21.0.10` rapporte par l'enveloppe et par
  la machine virtuelle des tests. Aucun fichier de l'utilisatrice n'est modifie.
- **L'enveloppe refuse.** Pour faire lire a l'enveloppe le fichier qui designe le JDK 25 sans toucher a aucun fichier, j'ai
  place devant `java` dans le `PATH` un script temporaire qui force le repertoire utilisateur de la machine virtuelle sur
  `HOME`, puis lance `bash outils/mvn-jdk21.sh -B -v` : code **1**, Maven non lance, message `erreur : refus de lancer
  Maven. La toolchain jdk 21 designe un JDK qui rapporte 25.0.2. Une mesure prise par cette enveloppe serait annoncee sur
  21 et executee sur 25.0.2.` Script temporaire supprime ensuite.
- **Sans cette substitution**, la meme commande rend `Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10` et code 0 :
  l'enveloppe juge le fichier que Maven lit, et elle le dit (`Verdict rendu sur le fichier de toolchains que Maven lit,
  qui n'est pas celui de ce shell`). Le risque reste pour tout poste ou les deux fichiers different et ou la machine
  virtuelle de Maven lit le mauvais : c'est le cas qu'`ADR-0071` ferme, et il est ferme.
- Observation : avec `JAVA_TOOL_OPTIONS` (premiere tentative), la ligne `Picked up JAVA_TOOL_OPTIONS` s'ajoute a la
  sortie de `java -version` et l'enveloppe rend `inconnue` au lieu de `25.0.2` : le refus tient (code 1), le chiffre cite
  est faux. Non corrige, hors de cette tache.
- `bash outils/mvn-jdk21-test.sh` (job `outils`) : code 0.

## 7. Ce qui n'est pas verifie

- **La CI distante** : aucune mesure sous GitHub Actions ni sous Linux. Je ne dis rien de son verdict.
- **Fins de ligne** : worktree en `core.autocrlf=true`, suite verte dans cette condition ; l'autre sens n'est pas mesure.
- **Stabilite** : la suite complete a tourne deux fois (`verify` puis `package`, puis une troisieme fois sous mutation, avec
  les memes comptes) ; aucun echec intermittent vu, rien conclu au-dela de trois lancements.
- **Criteres qu'un automate ne juge pas** : `TRV-CE1-5`, `EMC-9` (l'ecran entier), le rejeu de `SYC-CE1-R1`, la passe
  transverse `PAR-4`, le critere de refus des deux paquets de sciences, le titre de l'entree d'arts (ce que l'enfant
  comprend).
- **Tracabilite** : les quatre tests de paquet portent le nom du paquet, pas l'identifiant de story ; les criteres
  `grep` de la section 4 ne sont pas des tests.
- Aucun module n'est sans test : domain 120, application 17, adapter-web 401, adapter-content 1360, bootstrap 318 dont 6
  d'integration.
- **Ecarts de texte trouves, non corriges (hors de ma tache)** : la cellule de matrice de `ART-CP-10` dit "aucun verdict
  n'est prononce" et le backlog dit que la fiche n'existe pas, alors que `46e3651c` (12:42) la porte avec un verdict ; la
  matrice (12:43) a ete ecrite une minute apres. Le backlog place `PCO-CE1-R1` a `OUVERTE, non engagee` alors que
  `c876fadc` l'a livree.
- Verification apres commit : `prefixes` sur ce commit et `chemins` sur l'arbre avec ce rapport, rendues dans le compte rendu
  de la tache, pas dans ce fichier.

## 8. Verdict

- **Done technique** : les quatre jobs sont verts sur `542c1c6e`, tels que lances ici : `prefixes`, `chemins`, `outils`,
  `build` (y compris le pas de package et les six tests d'integration). Jugement de la CI distante : a la CI.
- **Criteres de s54** : tenus, `S51-SCI-CORPS-CM1-RESERVES` (mesurable), `S54-BR-*` (C1 a C7, criteres 1 a 8 de
  `S54-BR-ART-CP-10` sauf 7 non verifie), `S53-REPORT-GRANDS-NOMBRES`, `S51-OUTIL-JOURNAL-TOKENS`, `S53-PASSE-TRANSVERSE`,
  `S54-ART-CP-10-PRIX`. **Non tenu** : `S53-REPORT-REDACTION` R3 (ligne 315 toujours presente). **Non evidencie** :
  le resultat de `TRV-CE1-5` et d'`EMC-9` par les developpeurs. **Non garde par un test** : les deux motifs de refus de
  sciences.
- **Done pedagogique** : non prononce par ce rapport. Les verdicts cites sont ceux de `nadia-prof`.
