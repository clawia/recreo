# [Sprint s56] Rapport qualite - yanis-qa

- **Date** : 2026-10-11 (UTC+02:00), lue par `date`.
- **Arbre mesure** : `80c06e64` (`round-two` avec tout le code de s56 fusionne), branche `s56-yanis-qa`, `git status --porcelain` vide au depart et a la fin.
  `bash outils/verifier-lignee.sh` sort 0.
- **JDK des mesures** : **21.0.10**, propriete `java.version` des rapports `TEST-*.xml` de Surefire (`ADR-0071`). Enveloppe `bash outils/mvn-jdk21.sh`.
  Toute mesure partielle porte `-pl <module> -am`.
- **Ce rapport ne prononce aucun Done.** Il rapporte ce qui a ete lance ici. Le Done pedagogique est a `nadia-prof` seule ; le jugement de la CI distante est a la CI, que personne n'a lue (`QS8` du planning).
- **Rien n'est corrige dans le code des devs** : tout ce qui ne mord pas est remonte (section 5).

## 1. Porte technique, jobs lances (`.github/workflows/ci.yml`)

| Job | Lance | Commande | Resultat |
|---|---|---|---|
| `build` | oui, deux fois plus une | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`. (1) arbre `80c06e64` seul : `BUILD SUCCESS`, exit 0, 5 min 34 s, 2403 tests, 0 echec, 0 erreur, 0 ignore. (2) arbre avec ce fichier, premiere version : **`BUILD FAILURE`**, 1 echec sur 2397 tests de `bootstrap` (le reacteur s'arrete avant les 6 ITs) : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent`, qui lit `squad/` et refusait mes codes de mutation `XX-NN` (identifiants de condition non alloues, `ADR-0085`). (3) arbre avec ce fichier, codes renomme sans tiret : `BUILD SUCCESS`, exit 0, 2403 tests, 0 echec, 0 erreur, 0 ignore ; deux lancements (3 min 12 s, 3 min 9 s), le second sur le contenu commite, cette phrase exceptee |
| `build`, etape "Package du fat jar" | **non, telle quelle** | `mvn -B -pl bootstrap -am package` n'est pas lancee ; seul `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` l'a ete, pour le jar du releve F1 | sans verdict sur l'etape |
| `prefixes` | oui | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, puis `git log --no-merges --format=%s 4cd44098..HEAD \| bash .github/scripts/verifier-prefixes.sh` (39 sujets) | trois exit 0. Le sujet de mon commit passe dans `verifier-prefixes.sh` apres sa creation, hors de ce fichier |
| `chemins` | oui | `verifier-chemins-test.sh`, puis `git ls-files` (moins le test du garde-fou) plus ce fichier, passes a `verifier-chemins.sh` | voir section 8 |
| `outils` | oui | les trois motifs de la CI (`outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`) un a un | 10 juges, 10 exit 0, aucun motif vide |

Non lance : la plage `BASE..SHA` de GitHub, la version de Maven du runner, tout ce qui est distant.

## 2. Comptage

**2403 tests** a `80c06e64`. Methode : somme des attributs `tests` des fichiers `TEST-*.xml` de `target/surefire-reports` et `target/failsafe-reports` de chaque module, lus apres le `clean verify`
de la section 1, sans reprendre aucun chiffre des rapports des devs ; exclusions : aucune. Par module : `domain` 120, `application` 18, `adapter-web` 442, `adapter-content` 1470, `bootstrap` 347 plus 6 d'integration.
Les six modules ont des tests ; `adapter-persistence` n'existe pas (`DEC-0028`).

**Delta s56 : +96 sur la base `4cd44098`** (tip de `round-two` a l'ecriture du planning). Methode : `clean verify` racine sur un worktree detache a `4cd44098`, `BUILD SUCCESS`, **2307** (120, 18, 407, 1423, 333 plus 6 ITs), puis
comparaison classe par classe des deux jeux de `TEST-*.xml`. Les 15 classes dont le compte change : `PaquetCe1VerbeSujetTest` +22, `LaMarqueDeLOeuvreDejaEssayeeNeDependPasDeLImageTest` +23, `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest` +11,
`PaquetCpRapprocherDeuxOeuvresTest` +9, `PaquetCe1AccordSujetVerbeTest` +6, `PaquetCe1ClassesDeMotsTest` +5, `LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest` +5, `LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest` +4,
`LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest` +3, `PaquetCm1EmcPrincipesRepubliqueTest` +2, `ChaquePaquetExpliqueSesItemsAVariationTest` +2, `PaquetCm1EmcRespectDifferencesTest` +1, `LAnimationRetardeMaisNeDetermineRienTest` +1,
`AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` +1, `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` +1. Par module : `adapter-content` +47, `adapter-web` +35, `bootstrap` +14, `domain` et `application` 0.
Reconciliation avec les annonces : `lea-dev` 60 (0, 11, 27, 2, 3, 17), `marc-dev` 33 (22, 6, 5), `theo-archi` 3 (ADR-0024, ADR-0026), soit 96. Le total sur l'arbre fusionne reste a `sofia-sm`.

Aucun test instable vu : onze lancements temoins (arbre non mute, classes nommees) et les deux `clean verify` sont verts du premier coup, y compris avec jusqu'a six builds en parallele. `lea-dev` a signale une collision de port dans `JarRecreoIT` (section 6 de son rapport) : non reproduite ici, non expliquee ici.

## 3. A8 : `Q-Y8`, mutation d'A7, morsures de T2

**Methode commune.** Chaque mutation est un remplacement de chaine exact, refuse si le motif n'est pas trouve le nombre de fois attendu, applique a un fichier reel du depot (jamais une fixture), suivie de
`bash outils/mvn-jdk21.sh -B -pl <modules> -am test -Dmaven.test.failure.ignore=true`, avec `-Dtest=<classes> -Dsurefire.failIfNoSpecifiedTests=false` quand le perimetre est une classe nommee ; sans `-Dtest`, c'est le reacteur de
`bootstrap` entier avec `-am` (2397 tests, ITs exclus). Les echecs sont lus dans les `TEST-*.xml`, pas dans la console. Le fichier est restaure par `git checkout -- <fichier>` et `git status --porcelain` verifie vide apres chaque mutation.
Les mutations Maven tournent dans six worktrees jetables detaches a `80c06e64` (en parallele), supprimes a la fin ; celle d'A7 tourne dans le worktree de la branche. **Un lancement qui rapporte 0 test est invalide** : une premiere serie a ete jetee
sans etre lue, le `bash` du `PATH` etant celui de WSL, qui n'executait aucun test et rendait `0 echec`.

| # | Mutation | Perimetre | Verdict |
|---|---|---|---|
| MT01 | `Q-Y8` d'origine : `\\bt['\\u2019]` ramene a `\\bt[']` dans les quatre tests de paquet (`PaquetCm1EmcAtteinteAutruiTest`, `PaquetCm1EmcPrincipesRepubliqueTest`, `PaquetCm1EmcRespectDifferencesTest`, `PaquetCm1SciencesActiviteHumaineTest`) | `adapter-content`, 4 classes, 53 tests ; temoin 0 echec | **rouge, 4 echecs** : `lElisionDeTuEstReconnueAvecLApostropheDroiteEtLApostropheTypographique` dans chaque classe |
| MT02 | meme mutation, un fichier a la fois (4 lancements) | une classe chacun | **rouge, 1 echec chacun**, le meme test de sa classe : chaque motif est garde seul |
| MT03 | variante : le motif ne garde que U+2019 (apostrophe droite perdue) | 4 classes | rouge, 5 echecs : les quatre tests ci-dessus et `sci2LesDeuxMotifsMordentUnTexteFautif` |
| MT04 | `outils/mvn-jdk21.sh` : `\| grep -m1 'version "'` retire (la mutation annoncee par la retro de s55) | `outils/mvn-jdk21-test.sh`, 13 cas ok au temoin | **verte, exit 0, 0 cas rouge** (voir F8) |
| MT05 | meme ligne : `-m1` retire, `grep 'version "'` | idem | verte, exit 0 |
| MT06 | meme ligne : retour a la valeur d'avant `8541cffc`, `\| head -n1` | idem | **rouge, exit 1, 2 cas** : *"un JDK 21 conforme qui imprime Picked up JAVA_TOOL_OPTIONS avant sa version laisse passer"* et *"la version annoncee est celle du JDK et non la ligne Picked up"* |
| MT07 | garde-fou 1 : `if (!reproductions.contains(proposition.identifiant()))` devient `if (false)` | `LIllustrationDeContenuResoutEtAucuneNeDortTest` (22 tests, temoin 0 echec), `bootstrap -am` | **rouge, 2** : `unePropositionEnOeuvreInconnueFaitEchouerLeGardeFou`, `leJeuFautifAPropositionsEnImageEstRefuse` |
| MT08 | garde-fou 1 : le test reel ne transmet plus les propositions (`List.of()`) | idem | rouge, 1 : `chaqueIllustrationDeclareeParUnPaquetServiResoutEtAucunFichierNeDort` (une oeuvre servie en proposition seule est dormante) |
| MT09 | garde-fou 1 : une proposition ne compte plus pour *"aucune ne dort"* | idem | rouge, 3 : le garde-fou reel et les deux morsures de K3 |
| MT10 | garde-fou 2 : clause 1 (tout ou rien) desactivee | `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` (7 tests, temoin 0 echec) | rouge, 2 : `unePropositionEnTexteParmiDesReproductionsFaitEchouerLeGardeFou`, `leJeuFautifCommiteEstRefuseSurSesTroisClauses` |
| MT11 | garde-fou 2 : clause 2 (la reference existe) desactivee | idem | rouge, 2 |
| MT12 | garde-fou 2 : clause 3 (la reference n'est pas une proposition) desactivee | idem | rouge, 2 |
| MT13 | garde-fou 2 : perimetre `anyMatch` devient `allMatch` (un item mixte sort du perimetre) | idem | rouge, 4, dont le garde-fou reel et le cliquet de population |
| MT14 | garde-fou 2 sur le YAML reel : la reference de l'item 1 devient un distracteur | reacteur de `bootstrap` | rouge, 1 : `chaqueItemServiAPropositionsEnImageTientSesTroisInvariants` |
| MT15 | garde-fou 2 sur le YAML reel : un distracteur de l'item 1 devient le texte *"une peinture"* | reacteur de `bootstrap` | rouge, 8, dont le garde-fou 2, le garde-fou d'accent, B5, le garde-fou 4 et les deux exclusions |
| MT16 | garde-fou 2 sur le YAML reel : l'item 1 perd son `illustration` | reacteur de `bootstrap` | rouge, 7, dont B2, B3 et le cliquet des illustrations declarees |
| MT17 | garde-fou 3 : `boolean lues = !propositionsEnImage(item)` devient `true` (les identifiants sont de nouveau lus par l'enfant) | `adapter-content` entier, 1608 tests | **rouge, 3** : le garde-fou d'accent reel `chaqueMotServiEnProductionPorteSonAccent` (faux rouge sur `oeuvre-champ-de-ble`), `lesPropositionsDUnItemEnImageNeSontPasLuesParLEnfant`, `unPaquetCommiteAPropositionsEnImagePasseLeGardeFouDAccent` |
| MT18 | garde-fou 3 : `propositionsEnImage` passe de `allMatch` a `anyMatch` | idem | rouge, 1 : `unItemDontUneSeulePropositionEstUnTexteResteLuEnEntier` |

**Lecture.** `Q-Y8` est rejouee et rouge ; chaque motif de chaque classe est garde seul (MT02). Les morsures de T2 mordent toutes : chacune des trois clauses du garde-fou 2 rougit quand on la desactive (MT10 a MT12), et le garde-fou 1 comme le 3 aussi.
**La mutation d'A7 telle qu'ecrite en s55 ne prouve rien** : retirer le `grep` laisse la suite verte parce que `extraire_version_java` (`outils/verifier-jdk.sh`, `sed -n 's/.*version "\([^"]*\)".*/\1/p'` sur toute l'entree) choisit deja la ligne `version "`. Le garde-fou d'A7 est mordu par `head -n1` (MT06), pas par le retrait du `grep`.
Un lancement reel confirme la correction : `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8 bash outils/mvn-jdk21.sh -v` sort 0 et annonce `21.0.10`.

## 4. Tirage de garde-fous de s56, mutes sur les fichiers reels

### 4.1 Plafond de rendu (`S55-ART-CP-10-PLAFOND`) et marque d'echec (`S56-ART-CP-10-ECHEC`), sur `recreo.css`

| # | Mutation | Perimetre | Verdict |
|---|---|---|---|
| MT19 | `.propositions-oeuvres .proposition img` : `max-width: min(100%, 11rem)` devient `14rem`, seule | `LePlafondDeRendu...Test` + `LaMarqueDeLOeuvre...Test` (34 tests, temoin 0 echec) | rouge, 2 : `laFeuilleReelleTientLesTroisClauses` et un test de morsure |
| MT20 | meme regle : `max-height` devient `14rem`, seule | idem | rouge, 3 |
| MT21 | `.image-item { max-height: 11rem }` devient `14rem` (ancre) | reacteur de `bootstrap` | rouge, 3 : `unPlafondDeBaseQuiMonteFaitEchouerLaClause1` et deux autres |
| MT22 | critere d'origine du backlog : `max-width: calc(11rem + 0.5rem + 0.25rem)` de la case devient `calc(14rem ...)` (ligne 1780 de `4cd44098`) | reacteur de `bootstrap` | **verte, 0 echec sur 2397**, comme l'amendement A.2 le dit (voir F9) |
| MT23 | `aspect-ratio: 1 / 1` retire de la case `.propositions.propositions-oeuvres .proposition` | reacteur de `bootstrap` | **verte, 0 echec sur 2397** (voir F1, releve de rendu) |
| MT24 | regle `@media (min-width: 1px) { .propositions-oeuvres .proposition img { max-height: 20rem; } }` ajoutee apres la regle de base | reacteur de `bootstrap` | **verte, 0 echec sur 2397** (voir F2) |
| MT25 | la regle `.propositions-oeuvres .proposition.deja-essayee::before` devient un selecteur mort (`::beforex`) | `LaMarque...Test`, `LePlafond...Test`, `RessourcesTiercesTest`, `LAnimationRetarde...Test`, `HabillageDesEcransTest` (91 tests) | rouge, 9, dont `laRegleDeLaMarqueRetireeFaitEchouerLeCritere1` |
| MT26 | `url('/images/marque-echec.svg')` devient un fichier absent | idem | rouge, 3 : `laFeuilleEtLeFichierReelsTiennentLesCriteres`, `RessourcesTiercesTest.aucuneFeuilleNiAucunGabaritNeVaChercher...` |
| MT27 | pastille de la marque : `width` et `height` de `2.5rem` a `0.25rem` (et `margin-left` en proportion) | 91 tests, puis reacteur de `bootstrap` | **verte, 0 echec** aux deux perimetres (F3) |
| MT28 | pastille : `left: 50%` devient `left: 0` | idem | **verte** (F3) |
| MT29 | rangee : `padding-bottom: 3.25rem` (la place sous les cases) devient `0` | idem | **verte** (F3) |
| MT30 | `.deja-essayee img` : `opacity: 0.5` devient `1` (l'attenuation retiree) | idem | **verte** (F3) |
| MT31 | `[data-oeuvre-revelee=true] img` : `outline: 0.25rem solid ...` devient `none` (l'anneau de la revelation retire) | idem | **verte** (F3) |

### 4.2 `ADR-0026` clause 3 amendee, `ADR-0024` regle 1 amendee

| # | Mutation | Perimetre | Verdict |
|---|---|---|---|
| MT32 | `pointer-events: none` ajoute a la regle `.proposition.deja-essayee` (le bouton) | `adapter-web` entier, 580 tests | rouge, 2 : `aucuneRegleMaisonNeRendUneActionInactionnable`, `laClause3AdmetLePseudoElementDecoratifEtRefuseLeBoutonSurLaFeuilleReelle` |
| MT33 | l'exemption s'applique a tout selecteur qui *contient* le pseudo-element (`endsWith` devient `contains`) | `LAnimationRetardeMaisNeDetermineRienTest` (28 tests, temoin 0 echec) | rouge, 1 : `laClause3AdmetLePseudoElement...` |
| MT34 | l'exemption du pseudo-element retiree | idem | rouge, 2 |
| MT35 | `explication: ""` sur l'item 3 du paquet | `ChaquePaquetExpliqueSesItemsAVariationTest` (7 tests) | rouge, 1 : `chaquePaquetDeProductionExpliqueSesItemsAVariation` |
| MT36 | `explication` retiree sur l'item 3 | idem | rouge, 1 |

### 4.3 Exclusions des reponses en image (REV-3 et `ADR-0083`, `ADR-0104` amende)

Perimetre : `AucuneReponseDeFinDePartieNEstOrphelineDeSaQuestionTest` et `AucunTexteServiNEnchasseLaReponseDansUnePhraseTest` (8 tests, temoin 0 echec), `bootstrap -am`.

| # | Mutation | Verdict |
|---|---|---|
| MT37 | exclusion de REV-3 retiree (`if (false)`) | rouge, 3, dont `seulsLesJeuxADesReponsesEnImageSontHorsDeLaPopulation` |
| MT38 | exclusion d'`ADR-0083` retiree | rouge, 3, dont le test de non-exclusion |
| MT39 | predicat d'exclusion elargi a toute `Question` | rouge, 3, dont le test de non-exclusion de chaque classe (la correction 1 de l'amendement tient) |
| MT40 | REV-3 decide de nouveau sur la premiere manche (`items.get(0)`) | **verte, 0 echec** (F4) |
| MT41 | l'oracle du test de non-exclusion de REV-3 lit la premiere manche | **verte, 0 echec** (F4) |

### 4.4 Garde-fous B2, B3, B5, B7, B8 du paquet `cp-rapprocher-deux-oeuvres`

Perimetre : `PaquetCpRapprocherDeuxOeuvresTest` (9 tests, `adapter-content -am`), `LeFormatDUneOeuvreNeDesignePasLaBonneReponseTest` (5) et `LEnonceDuPaquetCpRapprocherDeuxOeuvresEstUniqueEtCourtTest` (3), `bootstrap -am` ; temoin des 17 tests : 0 echec.

| # | Mutation | Verdict |
|---|---|---|
| MT42 | B2, YAML reel : texte alternatif de l'item 3 remplace par *"Une vague geante."* | rouge, 2 |
| MT43 | B3, YAML reel : l'item 2 attend `oeuvre-marche-aux-chevaux` au lieu de `oeuvre-chambre` (paire a sens unique) | rouge, 1 : `b3LesReferencesLesCouplesEtLesPairesTiennent` |
| MT44 | B3, YAML reel : la reference de l'item 6 devient celle de l'item 5 | rouge, 2 |
| MT45 | B5, YAML reel : distracteur de l'item 3 echange contre une oeuvre en largeur (la bonne, en hauteur, est seule de son format) | rouge, 1 |
| MT46 | B5, YAML reel : item 1, les deux distracteurs en hauteur, la bonne et la reference en largeur | rouge, 1 |
| MT47 | B5, garde-fou : `memeFormat < 2` devient `< 1` (clause 1 desactivee) | rouge, 2 |
| MT48 | B5, garde-fou : `if (!unDistracteurLePartage)` devient `if (false)` (clause 2 desactivee) | rouge, 1, **tue par une assertion sur le texte du grief seul** (F6) |
| MT49 | B5, garde-fou : une image carree est rangee d'office en hauteur | rouge, 2 |
| MT50 | B7, YAML reel : items 4 a 6 retires (trois items) | rouge, 4 |
| MT51 | B7, garde-fou : `ITEMS_AU_MOINS = 4` devient `3` | **verte, 0 echec** (F5) |
| MT52 | B7, garde-fou : `ITEMS_AU_PLUS = 6` devient `7` | rouge, 1 : `unPaquetDeSeptItemsFaitEchouerLeGardeFou` |
| MT53 | B7, garde-fou : `size < 4` devient `size <= 4` | rouge, 1 |
| MT54 | B8, YAML reel : un mot de plus (*"... que celle-ci aussi ?"*) dans l'enonce des six items | rouge, 1 |
| MT55 | B8, YAML reel : l'enonce du seul item 1 devient *"Quelle oeuvre va avec celle-ci ?"* | rouge, 1 |
| MT56 | B8, garde-fou : `mots > 10` devient `mots >= 10` | rouge, 1 (l'enonce reel compte dix mots) |

### 4.5 Garde-fou 4 sur le sixieme item, registre et cliquets, accents

| # | Mutation | Perimetre | Verdict |
|---|---|---|---|
| MT57 | YAML reel : l'`explication` de l'item 6 (hors des cinq manches du jeu) porte `oeuvre-champ-de-ble` | `LesPropositionsEnImageAtteignent...Test` (14 tests, temoin 0 echec) | **rouge, 1** : le sixieme item est bien joue seul par le garde-fou |
| MT58 | temoin positif : la meme mutation sur l'item 1 | idem | rouge, 1 |
| MT59 | registre : `anneeDeMortDeLAuteur` de `oeuvre-champ-de-ble` 1890 devient 2000 | `ChaqueReproductionServiePorteLaPreuve...Test` (20 tests, temoin 0 echec) | rouge, 7 |
| MT60 | registre : licence de la reproduction `CC0-1.0` devient `CC BY 4.0` | idem | rouge, 7 |
| MT61 | cliquet `POIDS_TOTAL_MAXIMUM` 275_163 devient 275_164 | idem | rouge, 1 : `leCliquetVautExactementLaTailleDeLaPopulationServie` |
| MT62 | cliquet `POIDS_TOTAL_MAXIMUM` releve de 25 000 octets | idem | rouge, 1 : le cliquet est une egalite, pas un plafond |
| MT63 | cliquet `REPRODUCTIONS_SERVIES` 12 devient 13 | idem | rouge, 2 |
| MT64 | YAML reel : `œuvres` devient `oeuvres` dans l'explication des items 1 et 2 | reacteur de `bootstrap` (2397), puis `AucunMotServiNeSEcarteDeSonAccentTest` seul (5 tests) | **verte aux deux perimetres** (F7) |
| MT65 | YAML reel : `oeuvres` devient `oeuvrse` (un non-mot) | `AucunMotServiNeSEcarteDeSonAccentTest` | **verte** (F7) |
| MT66 | temoin positif : `fenêtre` devient `fenetre` (items 5 et 6) | idem | rouge, 1 |
| MT67 | `catalogue.yaml` : la consigne de l'entree du paquet perd ses accents et sa ligature | reacteur de `bootstrap` | rouge, 5, dont l'inventaire de `TexteAccentueExposeParLePortEntrantTest` |

### 4.6 Paquets de francais et d'EMC (`marc-dev`)

Perimetre : `PaquetCe1VerbeSujetTest`, `PaquetCe1AccordSujetVerbeTest`, `PaquetCe1ClassesDeMotsTest` (63 tests, `adapter-content -am`, temoin 0 echec), ou `adapter-content` entier (1608) quand la mutation touche `catalogue.yaml`.

| # | Mutation | Verdict |
|---|---|---|
| MT68 | `-13`, propriete 5 : l'indice de l'item 1 nomme de nouveau *"le verbe cite"* | rouge, 1 : `propriete5LeMotCiteSortDesIndices...` |
| MT69 | `-13`, propriete 3 : un distracteur de l'item 1 n'est pas dans la phrase | rouge, 1 : `propriete3...` |
| MT70 | `-13`, propriete 2 : `bonneReponse: "boit"` devient `"Boit"` | rouge, 1 : `propriete2J1...` |
| MT71 | `-13`, entree de catalogue : la consigne finit par `?` (espace insecable) | rouge, 1 : `propriete7J5...` |
| MT72 | `-13`, entree de catalogue : la consigne finit par une espace **ordinaire** puis `?` | rouge, 2 : `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` et `propriete7J5...` |
| MT73 | `-13`, entree de catalogue : `nombreDeManches: 10` devient 30 pour 24 items | rouge, 1 : `ChaqueJeuDuCatalogueOffrePlusDItemsQueDeManchesTest` |
| MT74 | `ACV-CE1-R2` : l'indice de nombre revient sur l'item 15 (ses distracteurs ont le meme nombre) | rouge, 2 : `k1LeNombreSeulNEstUnCritere...` |
| MT75 | `ACV-CE1-R2` : l'item 11 passe a `jeter` / `jettent` (-eter, hors de la regle de `PRS-CE1-1`) | rouge, 2 : `acvR2LesVerbesSontCeuxDePrsCe11`, `acvR2HuitItemsAuMoinsASujetPluriel` |
| MT76 | `CLM-CE1-R1` : les cinq indices d'adjectif deviennent un texte de quatorze mots | rouge, 1 : `trv5ChaqueIndiceCompteAuPlusDouzeMots...` |
| MT77 | `CLM-CE1-R1`, P5 : les cinq indices d'adjectif deviennent *"Ce mot est-il un <mot> ?"* pour chacun des onze termes de P5 (`graphème`, `phonème`, `préfixe`, `suffixe`, `radical`, `participe`, `auxiliaire`, `complément`, `antonyme`, `homophone`, `inférence`) | rouge pour les onze, 1 echec chacun : `trv4AucunMotDuLexiqueExcluNEstServi...` (P5 n'a pas de test propre, `TRV-CE1-4` le garde) |

**Bilan des mutations (methode : decompte des lignes MT, une ligne pour une mutation, sauf MT02 (4 lancements) et MT77 (11 lancements)) : 90 mutations distinctes ; 75 rouges (dont 2 temoins positifs, MT58 et MT66) ; 15 vertes** :
MT04, MT05, MT22, MT23, MT24, MT27 a MT31, MT40, MT41, MT51, MT64, MT65.
Les 12 temoins (arbre non mute : 11 lancements Maven et celui d'`outils/mvn-jdk21-test.sh`) rendent 0 echec sur un nombre de tests non nul. Chaque mutation est annulee ; `git status --porcelain` est vide dans les six worktrees jetables et dans celui de la branche a la fin.

## 5. Ce qui ne mord pas

Chaque ligne cite sa mutation. Aucun correctif n'est ecrit ici.

| # | Ce qui ne mord pas | Preuve | Route |
|---|---|---|---|
| **F1** | **La case des propositions peut perdre `aspect-ratio: 1 / 1` sans qu'aucun test ne rougisse.** Le plafond de l'image tient (176 px), mais les cases ne sont plus carrees ni egales (M1, X6) et la rangee grandit | MT23, 0 echec sur 2397. Releve : voir ci-dessous | `theo-archi` (l'amendement A.2 a retire la clause de la case), `ines-design` (M1) |
| **F2** | Une regle sur l'image sous `@media` echappe au garde-fou du plafond : une declaration `max-height: 20rem` y passe sans rouge (rendu non mesure). Angle mort declare par `theo-archi` (A.2) et `lea-dev` (9.5), **confirme sur la feuille reelle** ; la feuille a deja un `@media (min-width: 720px)` pour `.ecran-deux-oeuvres` | MT24, 0/2397 | `theo-archi` |
| **F3** | **Cinq proprietes de la marque et de la revelation ne sont gardees par rien** : la taille de la pastille, son centrage, la place reservee sous la rangee, l'attenuation a demi, l'anneau de la revelation. Le test de la marque juge les criteres 1 a 5, 7 et 8 ; ces cinq-la n'en sont pas. Rien ne rougit si la pastille tombe a 4 px ou si la bonne oeuvre n'est plus marquee a la revelation (la phrase `M4` reste) | MT27 a MT31, 0/91 puis 0/2397 | `nadia-prof`, `ines-design` (propriete visible ; le README, amendement s15, nomme ce qu'un outil ne juge pas), `theo-archi` (ce que la feuille peut epingler) |
| **F4** | **Le choix de la derniere manche par REV-3 n'est pas mordu, ni l'oracle qui le juge.** Revenir a la premiere manche laisse tout vert. Constat deja ecrit par `theo-archi` (*"non mordu sur materiau reel"*) : aucun paquet servi n'est melange ; confirme | MT40, MT41, 0/8 | `theo-archi` (un jeu fautif commite, ou un item melange fabrique) |
| **F5** | **La borne basse de B7 n'est epinglee par rien.** Les tests s'ecrivent contre la constante (`subList(0, ITEMS_AU_MOINS - 1)`) : `ITEMS_AU_MOINS = 3` laisse tout vert, alors que le garde-fou refuse un paquet de trois items (MT50) tant que la constante vaut 4. La borne haute est epinglee par un cas litteral de sept items (MT52) | MT51, 0/9 | `lea-dev` |
| **F6** | La clause 2 de B5 ne change jamais le verdict : si la bonne oeuvre partage le format de la reference et qu'aucun distracteur ne le partage, `memeFormat` vaut 1 et la clause 1 rougit deja. Seule l'assertion sur le texte du grief la tue | MT48, rouge par le seul message | `theo-archi` |
| **F7** | **La ligature et l'orthographe hors accents ne sont jugees par aucun test sur un texte de paquet.** `œuvres` ecrit `oeuvres` passe toute la suite (MT64) et `oeuvrse` passe le garde-fou d'accent (MT65, classe seule) ; `fenêtre` ecrit `fenetre` rougit. Le garde-fou d'accent juge les accents (MT66), les chaines du catalogue sont epinglees par l'inventaire (MT67), un paquet n'a pas cet epinglage. Cause lue dans `LexiqueFrancais.squelette` (la normalisation retire les signes diacritiques, la ligature n'en est pas un) : lecture du code, non mesuree | MT64, MT65 | `relecteur-pedagogique` (porte l'orthographe), `theo-archi` (`ADR-0065`) |
| **F8** | La mutation d'A7 annoncee en s55 (retirer le `grep -m1` de la ligne `version`, retro de s55, A8) est un mutant equivalent : elle ne peut pas rougir, `extraire_version_java` selectionne deja la ligne. La regression reelle est `head -n1` et elle est mordue par deux cas | MT04, MT05 vertes ; MT06 rouge | `sofia-sm` (corriger le libelle d'A7 et de la retro) |
| F9 | Le critere d'origine du plafond (*"ligne 1780, `calc(11rem` vers `calc(14rem`"*) reste lisible dans le corps de la story du backlog ; il est insatisfiable, la mutation rend 0 echec. L'amendement du backlog (`P1'` a `P5'`, ligne 21151) le remplace, ce que `DEC-0116` A.11 dit | MT22 | `camille-po` (lien ou retrait dans le corps de la story) |

**Releve de F1.** Jar du meme arbre (`bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests`), lance sur un port libre (JDK 21.0.10), arrete par son PID. Fragment obtenu par
`POST /parties/bandit-manchot-rapprocher-deux-oeuvres-cp`, pose dans une page minimale qui charge les deux feuilles du jar ; les trois sources d'image des propositions sont remplacees par trois reproductions en hauteur
(242x352, 258x352, 308x352). Chrome headless (`--headless=new --dump-dom --virtual-time-budget=8000 --window-size=<L>,1400`), un script a l'evenement `load` lit `getBoundingClientRect()` de chaque case et de son `img`. La mutation est une
declaration `aspect-ratio: auto` posee apres la feuille (equivalente au retrait de la ligne).

| Fenetre | Etat | Case (L x H) | Image (L x H) |
|---|---|---|---|
| 744 | feuille reelle | 154,7 x 154,7 (les trois) | 100,7 / 107,1 / 127,1 x 144,7 |
| 744 | sans `aspect-ratio` | 154,7 x 218,6 | 122,3 / 130,1 / 144,7 x 176 / 176 / 164,8 |
| 992 | feuille reelle | 188 x 188 | 122,3 / 130,1 / 154,5 x 176 |
| 992 | sans `aspect-ratio` | 188 x 264,2 | 122,3 / 130,1 / 154,5 x 176 |

Plafond : tenu (aucune image au-dessus de 176 px). Cases : de carrees a environ 1 : 1,4 (744 et 992), soit +64 px et +76 px de hauteur de rangee. Limites : sans htmx, sans meuble, sans la page reelle ; un seul tirage de trois images ; Chrome n'est pas la tablette
du perimetre ; la fenetre passee a `--window-size` n'est pas la largeur de contenu. Le pli (551 px) n'est pas mesure.

## 6. Criteres, tracabilite, modules

- **Tracabilite des stories dans les tests.** Methode : `git grep -l -- <identifiant> -- '*Test.java'` a `80c06e64`. `S55-ART-CP-10-PAQUET` : 8 fichiers ; `S56-ART-CP-10-ECHEC` : 2 ; `S56-BR-FRA-CE1-13` : 2 ; `ACV-CE1-R2`, `CLM-CE1-R1`, `EMC-CM1-R8`, `EMC-CM1-R9` : 1 chacun (sous la forme de la reserve, sans le prefixe `S55-`).
  **`S55-ART-CP-10-PLAFOND` : 0 fichier** : `LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest` ne cite ni la story ni la decision, seulement l'objet. Le critere se retrouve par le nom de la classe, pas par la story.
- **Aucun test de s56 n'assertionne du HTML par expression reguliere.** Methode : lecture des 20 classes de test modifiees depuis `4cd44098` (`git diff --name-only 4cd44098 HEAD -- '*Test.java'`), recherche de `Pattern.compile`, `replaceAll`, `matches(` et de Jsoup. Les regex des tests de paquet portent sur du YAML ;
  les deux `replaceAll("\\s+", " ")` des tests de rendu (REV-3, `ADR-0083`) normalisent un noeud de texte extrait par Jsoup et sont anterieurs a s56.
- **Donnees d'entree** : les mutations MT14 a MT16, MT42 a MT46, MT50, MT54, MT55, MT57, MT64, MT65 changent le YAML reel du paquet, pas une fixture. Les jeux fautifs commites existent pour les garde-fous qui lisent `src/main/` (`ADR-0013`).
- **Lecture de testabilite de s56 : non produite.** Le planning la met a `yanis-qa` avant la premiere livraison de code ; `ls squad/sprints/s56-*/lecture-testabilite.md` ne rend aucun fichier, et la premiere livraison de code (`550df967`) l'a precedee. Elle n'est pas rattrapee ici : ce rapport mesure l'arbre fusionne.
  La liste des verbes de `PRS-CE1-1` n'a pas ete ecrite avec `marc-dev` non plus : il l'a remplacee par une regle dans le test (`E7` de son rapport), que MT75 mord.
- **Q7** (`ines-design`) n'existe pas dans le depot (`rapport-lea-dev.md` 11.3) : non mesure ici.

## 7. Ce qui n'est pas verifie

- La CI distante, sous Linux ; les fins de ligne dans l'autre sens que `core.autocrlf=true` (poste Windows).
- Tout ce qui se juge en jouant : la marque, le plafond a l'ecran, la propriete rapprochee, la lisibilite des indices. Le releve F1 est une mesure de boites, pas un jugement.
- La stabilite : une suite complete par arbre, plus les lancements partiels ; aucun echec intermittent vu, rien conclu au-dela.
- Les mutations n'epuisent pas les garde-fous : les tests ajoutes par `lea-dev` pour `EMC-CM1-R8` et `R9` ne sont pas rejoues (ses `M1` et `M2` restent a lui), ni `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` (l'entree du paquet y est une exemption par nom).
- Le poste a fait tourner jusqu'a six builds en parallele : les durees des tableaux sont indicatives, pas les comptes.

## 8. Chemins et cloture

`chemins` : `bash .github/scripts/verifier-chemins-test.sh` exit 0 ; `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` plus le chemin de ce fichier, passes a `bash .github/scripts/verifier-chemins.sh` : exit 0, *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."* Aucun chemin d'interpreteur, de JDK ou de worktree n'est ecrit ici.

## Decisions prises

Aucune decision n'est prise par ce rapport. Il route : F1, F2, F4, F6 a `theo-archi` ; F3 a `nadia-prof` et `ines-design` ; F5 a `lea-dev` ; F7 au `relecteur-pedagogique` ; F8 a `sofia-sm` ; F9 a `camille-po`, tous par `sofia-sm`.
Ce rapport ne prononce ni le Done technique (la CI le juge, tous ses jobs) ni le Done pedagogique (`nadia-prof`).
