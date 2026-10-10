# Rapport de lea-dev, s56 - S55-ART-CP-10-PAQUET (fichier du paquet et T4)

`lea-dev`, 2026-10-09. Worktree `s56-lea-paquet`, base `round-two` a `8d40c4a5`. Commit du paquet : voir la section 1.
**Version sur laquelle j'ai mesure : JDK 21.0.10**, valeur `java.version` du rapport Surefire (`bootstrap/target/surefire-reports`), enveloppe
`bash outils/mvn-jdk21.sh`. Toute mesure partielle ci-dessous porte `-pl bootstrap -am` et nomme son perimetre : le reacteur de `bootstrap`
(domain, application, adapter-web, adapter-content, bootstrap), ITs exclus (phase `test`).

## 1. Ce qui est livre

Un seul commit pour le fichier du paquet et T4 (`avis-faisabilite.md` de s56, `AV5`) : `[LEA] s56: paquet cp-rapprocher-deux-oeuvres (ART-CP-10), T4 et les quatre cliquets`.

- `adapters/adapter-content/src/main/resources/contenu/cp-rapprocher-deux-oeuvres.yaml` : six items, `lignesDeReferentiel: ["ART-CP-10"]`. Le bloc
  YAML d'items est la recopie du bloc de `CHOIX-OEUVRES-s56-art-cp-10.md` section 3 : relu par comparaison de chaine (le texte du fichier apres
  son en-tete est egal au bloc du document), 6 occurrences de l'enonce avec espace insecable U+00A0 devant le `?`, 0 espace ordinaire, 6 textes
  alternatifs identiques a ceux de `cp-decrire-une-oeuvre`. Le nom du fichier et l'en-tete de commentaire sont de moi (le document les laisse a `lea-dev`). Le descripteur reprend la
  competence de la ligne `ART-CP-10` du referentiel.
- Quatre reproductions restaurees depuis `1db6348f^` : `git show 1db6348f^:<chemin>`, puis identifiant de blob egal a celui de `1db6348f^`
  (`git hash-object` contre `git rev-parse 1db6348f^:<chemin>`, quatre egalites) et taille lue par `git cat-file -s` : champ-de-ble 31 398, averse-sur-le-pont 21 707,
  jeune-femme-aiguiere 21 336, autoportrait-leyster 24 248. Les quatre entrees de `squad/corpus/oeuvres-domaine-public.yaml` sont restaurees
  du meme commit. J'ai ajoute quatre lignes de commentaire a l'en-tete du registre, qui disait *"ces 8 oeuvres"* : point signale en section 6.
- Quatre cliquets, K2 et K3 : section 2.

## 2. Resultat de chaque propriete

| # | Propriete | Commande | Avant | Apres |
|---|---|---|---|---|
| P1 | Un fichier a propositions en image (critere 1) | `grep -lE '^ *bonneReponse: "oeuvre-' adapters/adapter-content/src/main/resources/contenu/*.yaml \| wc -l` | 0 (sur `8d40c4a5`, par `git grep`) | 1 |
| P2 | Six items, formes tenues (critere 2, `B1`) | quatre `grep -c` du critere 2 sur le fichier | 0 (fichier absent) | 6, 6, 6, 6 |
| P3 | `ITEMS_A_PROPOSITIONS_EN_IMAGE` | `UnItemAPropositionsEnImageTientSesTroisInvariantsTest`, constante | 0 | 6 (le verify, constante ancienne : *"Expected size: 0 but was: 6"*) |
| P4 | `ITEMS_QUI_DECLARENT_UNE_ILLUSTRATION` | `LIllustrationDeContenuResoutEtAucuneNeDortTest`, constante | 81 | 87 (le verify, constante ancienne : *"Expected size: 81 but was: 87"*) |
| P5 | `REPRODUCTIONS_SERVIES` | `ChaqueReproductionServiePorteLaPreuveDeLOeuvreEtCelleDeSaPhotographieTest`, constante | 8 | 12 (le verify, constante ancienne : *"12 reproductions servies pour un plafond de 8"*) |
| P6 | `POIDS_TOTAL_MAXIMUM` | somme de `wc -c` sur `static/oeuvres/*.jpg`, et le cliquet exact vert | 176 474 | 275 163 (la somme des douze fichiers, et `176 474 + 31 398 + 21 707 + 21 336 + 24 248`) |
| P7 | Fichiers servis, entrees de registre | `ls .../static/oeuvres/*.jpg \| wc -l` ; `grep -c "identifiant:" squad/corpus/oeuvres-domaine-public.yaml` | 8 et 8 | 12 et 12 |
| P8 | `PLAFOND_DE_PLUS_GRAND_COTE_EN_PIXELS` | inchange | 352 | 352 (le garde-fou vert) |
| P9 | K1, regle 1 d'`ADR-0024` | `ChaquePaquetExpliqueSesItemsAVariationTest` | non applicable (pas de paquet) | vert, 7 tests : l'amendement d'`ADR-0024`, deja fusionne dans ma base, suffit ; je n'ai rien touche |
| P10 | K2, `PAQUETS_HORS_DU_DOMAINE_NUMERIQUE` | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | 3 echecs sur le paquet seul, constantes anciennes (*"6 items tires, 0 classes - ce garde-fou ne l'a pas juge du tout"*) | 0 echec, avec une entree `cp-rapprocher-deux-oeuvres` et son motif (6 items, 18 propositions, aucune numerique, 0 item classe) |
| P11 | K3, deux morsures | `LIllustrationDeContenuResoutEtAucuneNeDortTest.unIdentifiantDOeuvreResoutDesQueLeFichierDeReproductionExiste` et `.uneOeuvreServieSeulementEnPropositionNEstPasDormante` | 2 echecs (*"oeuvre-autoportrait-leyster.jpg : aucun item d'aucun paquet servi ne le declare"*) | 0 echec : elles recoivent `propositionsDeclarees(itemsDuDepot(racine))` ; la seconde en retire la reproduction mutee avant de la remettre par l'item mute, pour que sa morsure reste mordante |

Mesure avant : le verify sur le commit du paquet avec les constantes **anciennes**, `bash outils/mvn-jdk21.sh -B clean verify
-Dmaven.test.failure.ignore=true` (racine, sans `-pl`) : 8 echecs, tous dans les cinq tests ci-dessus. Les valeurs 6, 87 et 12 sont celles que les messages rapportent, non recopiees.
Le poids n'est pas rapporte par ce verify (le cliquet s'arrete au compte) : sa valeur est la somme mesuree sur disque, confirmee par le cliquet exact vert au verify apres.

### Mutations sur materiau reel

Chacune mute en memoire du depot le fichier reel du paquet (une ligne), lance `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test
-Dmaven.test.failure.ignore=true`, puis restaure le fichier (identifiant de blob verifie egal apres la serie).

| # | Mutation | Test rouge | Lecture |
|---|---|---|---|
| M1 | une `explication` retiree sur un item (`B10`, `K1`) | `ChaquePaquetExpliqueSesItemsAVariationTest.chaquePaquetDeProductionExpliqueSesItemsAVariation` : *"ce paquet ne declare aucune graphie et porte une explication ; il la porte donc sur tous ses items ou sur aucun"* | mordue |
| M2 | la reference (`oeuvre-chambre`) mise en distracteur de son propre item | `UnItemAPropositionsEnImageTientSesTroisInvariantsTest.chaqueItemServiAPropositionsEnImageTientSesTroisInvariants` : *"la reference n'est pas une proposition"* | mordue |
| M3 | un distracteur `oeuvre-inconnue` | `LIllustrationDeContenuResoutEtAucuneNeDortTest` : le garde-fou reel et les deux morsures de K3 (3 echecs) | mordue ; effet de K3 : les deux morsures lisent maintenant les propositions reelles, elles rougissent aussi sur un depot dont une proposition reelle ne resout pas |
| M4 | une espace ordinaire devant un `?` | `AucunSigneDoubleNEstDetachableDeSonMotTest.leCliquetDeLaDetteDeTyp1EstExact` : *"un contenu neuf n'herite d'aucune dette"* | mordue |
| M5 | `oeuvre-hippopotame` en distracteur (`B6`) | aucun | **non garde** |
| M6 | un distracteur en texte (`"une chambre"`) parmi des images (invariant 1, tout ou rien) | `UnItemAPropositionsEnImageTientSesTroisInvariantsTest` et `AucunMotServiNeSEcarteDeSonAccentTest.chaqueMotServiEnProductionPorteSonAccent` | mordue |
| M7 | un couple (reference, bonne) repete : l'item 2 devient `chambre` -> `champ-de-ble`, comme l'item 1 (`B3`) | aucun | **non garde** |
| M8 | `oeuvre-autoportrait-leyster` retiree de ses deux items, la reproduction ne dort pas | `LIllustrationDeContenuResoutEtAucuneNeDortTest` : le garde-fou reel *"aucune ne dort"* et les deux morsures de K3 (3 echecs) | mordue |

## 3. Delta de tests

**+0 test.** Mesure sur `8d40c4a5` (worktree jetable, `bash outils/mvn-jdk21.sh -B clean verify`, racine) : 120 + 18 + 408 + 1447 + 333 + 6 = 2332 ;
mesure sur mon commit : memes comptes par module, 2332. Ce lot modifie quatre tests existants (trois constantes, une entree de table,
deux morsures) et n'en ajoute aucun : le paquet est juge par les garde-fous deja ecrits, qui lisent les repertoires de contenu. Le total apres fusion est a `sofia-sm`.

## 4. Jobs de `ci.yml` lances, sur mon arbre

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine. Resultat sur le commit du paquet, section 7.
- **`prefixes`** : `verifier-prefixes-test.sh` et `commit-atteignable-test.sh` (exit 0), et `verifier-prefixes.sh` sur le sujet de mon commit (exit 0). **Non lance** : l'etape sur la plage `BASE..SHA` de GitHub.
- **`chemins`** : `verifier-chemins-test.sh` (exit 0), puis `git ls-files | verifier-chemins.sh` (exit 0).
- **`outils`** : les dix juges `outils/*-test.sh`, `squad/outils/*/*-test.sh` et `outils/*-test.py` presents sur l'arbre, un a un, exit 0 pour chacun. Perimetre de la boucle : les memes motifs que le job.
- **Non lance** : l'etape `mvn -B -pl bootstrap -am package` du job `build` et la version de Maven du runner.

## 5. Conditions de `nadia-prof` : gardees ou non gardees (C8)

| Condition | Garde | Preuve |
|---|---|---|
| `B1` formes, trois invariants d'`ADR-0104` | gardee | P2 ; M2, M6 |
| `B2` texte alternatif identique | **non garde** | je l'ai verifie par `grep -c` (6 sur 6), aucun test ne le mord ; non mute |
| `B3` couple unique, reference unique | **non garde** | M7 vert |
| `B5` format (aucune bonne oeuvre seule de son format) | **non garde** | aucun test ne lit les en-tetes JPEG ; non mute. Je l'ai relu sur le tableau du document des oeuvres, pas sur les fichiers |
| `B6` pas de statuette en proposition | **non garde** | M5 vert |
| `B7` bornes 4 a 6 items | **non garde** | non mute ; le paquet porte 6 |
| `B8` enonce identique | **non garde** | `grep -c` 6 sur 6, non mute |
| `B10` explications | gardee pour la presence (M1), **non gardee pour l'exactitude** (a `nadia-prof`) | |
| `B11` cliquets exacts | gardee | P3 a P6 |

## 6. Ecarts et ce qui n'est pas verifie

- **Ecart de comptage** : l'avis de `theo-archi` parle de 2307 tests ; ma base `8d40c4a5` en compte 2332. Les fusions de s56 (ADR-0024, ADR-0026) sont entrees entre les deux. Mon delta est +0 sur cette base.
- **En-tete du registre** : il affirmait *"CE QUE CES 8 OEUVRES SONT"* ; j'ai ajoute quatre lignes qui disent que quatre reproductions sont restaurees et que le registre en porte 12, sans reecrire le paragraphe. Hors des criteres : a `theo-archi` ou `camille-po` si elle veut l'ecrire autrement.
- **Une execution rouge, hors changement** : un premier verify sur le commit du paquet a donne 1 erreur dans `JarRecreoIT` (*"Web server failed to start. Port 51729 was already in use"*, journal du jar), les cinq autres modules verts. Collision de port, pas un echec de contenu. Voir la section 7 pour la reexecution.
- **Fragilite de K3 nommee** : `unItemReelQuiMontreUneReproduction` prend le premier item reel qui montre une reproduction ; il est aujourd'hui dans `cp-decrire-une-oeuvre` (ordre alphabetique). La morsure retire de la population les propositions egales a sa reproduction, donc elle tiendrait aussi si le premier item etait dans le nouveau paquet.
- **Je n'ai pas joue le jeu a l'ecran** : le paquet est hors catalogue, aucune entree ne le sert, donc le jar ne peut pas le montrer. Les ecrans (reference, trois images, revelation, fin), la visibilite des proprietes a 176 px, la fidelite des quatre images restaurees et l'exactitude des six explications ne sont pas verifies par moi. Le premier endroit ou je peux le faire est apres l'entree de catalogue.
- Les garde-fous `PLAFOND` et `ECHEC` n'existent pas encore : la case et la marque ne sont pas jugees.

## 7. Verify final

- Commit du paquet `25f0c1ee`, arbre propre. Premiere execution : domain 120, application 18, adapter-web 408, adapter-content 1447, bootstrap 333, tests d'integration 6, avec 1 erreur dans `JarRecreoIT` (collision de port, section 6).
- Reexecution, meme commande, meme arbre avec ce rapport en plus (non commite au moment de la mesure) : `BUILD SUCCESS`, 120 + 18 + 408 + 1447 + 333 + 6 = 2332 tests, 0 echec, 0 erreur, JDK 21.0.10.

## 8. Ce qui reste pour les postes suivants

1. **`S55-ART-CP-10-PLAFOND`** : le test seul, quatre clauses, sept morsures P1 a P7 sur `recreo.css` (backlog, section 8 ; critere amende par `camille-po` pour P1' selon `avis-faisabilite.md` A.2). Avant `ECHEC`.
2. **`S56-ART-CP-10-ECHEC`** : la marque de l'echec, regle sur `.propositions-oeuvres .proposition.deja-essayee img` (`AV3`), apres le plafond et apres l'amendement d'`ADR-0026` (deja fusionne dans ma base). Le critere de la marque est a `nadia-prof` et `ines-design`, non ecrit : je ne commence pas sans lui.
3. **Entree de catalogue** (`catalogue.yaml`) : titre *"Des œuvres faites de la même façon"*, consigne *"Regarde l'œuvre, puis trouve celle qui est faite de la même façon."*, `nombreDeManches: 5` (`B9`, `ADR-0023`), l'inventaire de `TexteAccentueExposeParLePortEntrantTest`, et l'assertion du garde-fou 4 (`LesPropositionsEnImageAtteignentLEcranSansJamaisSonIdentifiantTest`, *"aucun jeu reel du catalogue ne sert de propositions-images"*) qui devient la liste exacte du jeu branche. Prealables : `PLAFOND` et `ECHEC` ancetres (`git merge-base --is-ancestor`), `S56-ART-CP-10-FORME` et `Q7` ecrits. Le premier endroit ou je joue le jar.
4. **R8 et R9** (`S55-EMC-CM1-R8`, `S55-EMC-CM1-R9`) : ordre de `DEC-0116` 3.1, apres l'entree de catalogue. Rien commence.
5. **Non garde, a `theo-archi`** : `B2`, `B3`, `B5`, `B6`, `B7`, `B8` (section 5). Je n'ai ecrit aucun test pour eux : ni plus ni moins que la demande, et la forme d'un garde-fou est une decision d'architecture.

## 9. S55-ART-CP-10-PLAFOND (worktree `s56-lea-plafond`, base `round-two` a `e8155d7e`)

Commit du garde-fou : `1f0fe8e4`. **JDK 21.0.10** (`java.version` du rapport Surefire, enveloppe `bash outils/mvn-jdk21.sh`). La forme et les mutations sont celles de l'amendement de `theo-archi`
(`avis-faisabilite.md`, A.2) et du backlog amende par `camille-po` (`DEC-0116` A.11) : plafond porte par l'image, clauses 1 a 3, mutations `P1'` a `P5'`.

### 9.1 Ce qui est livre

- `adapters/adapter-web/src/main/resources/static/css/recreo.css` : `.propositions-oeuvres .proposition img` declare `max-width: min(100%, 11rem)` et `max-height: min(100%, 11rem)` (avant : `100%` et `100%`), plus un commentaire de trois lignes. La case et son arithmetique ne changent pas.
- `adapters/adapter-web/src/test/java/com/recreo/adapter/web/LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest.java` : un juge pur (`griefs`) et onze tests. Il lit la feuille par `TailleDesTextesEtBoutonsTest.blocsMaison`.
  - clause 1 : `max-height` de `.image-item`, en rem, fois 16, egale 176 ;
  - clause 2 : tout selecteur contenant `propositions-oeuvres` dont le sujet est `img` ne declare `max-width` et `max-height` que sous la forme `min(100%, <x>rem)`, `x` fois 16 au plus 176, et l'un d'eux declare les deux ;
  - clause 3 : aucun de ces selecteurs ne declare `min-width`, `min-height`, ni `width` ou `height` autres que `auto`.
- `adapters/adapter-web/src/test/resources/contreexemple/LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest/static/css/recreo.css` : le jeu fautif commite. **Ecart au plan, trouve par le verify** : `LesGardeFousSontMisALEpreuveTest.aucunGardeFouDeBalayageNEchappeALaMiseALEpreuve` (ADR-0013) exige
  qu'un test qui lit `src/main/` possede un jeu fautif sous `contreexemple/<NomDeLaClasse>` et l'execute en exigeant le rouge. Il rougissait sur la classe neuve (*"Expecting empty but was: [LePlafondDeRendu..."*) ; le jeu fautif viole les trois clauses a la fois et `leJeuFautifCommiteEstRefuse` les exige toutes.

### 9.2 Resultat de chaque propriete

| # | Propriete | Commande | Avant | Apres |
|---|---|---|---|---|
| Q1 | La forme est dans la feuille | `grep -c 'min(100%, 11rem)' adapters/adapter-web/src/main/resources/static/css/recreo.css` | 0 | 2 |
| Q2 | Le test sur la feuille d'avant | `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test -Dtest=LePlafondDeRenduDUneReproductionEnPropositionEstGardeTest -Dsurefire.failIfNoSpecifiedTests=false` (perimetre : `adapter-web` avec `-am`, classe nommee), `recreo.css` remis a `e8155d7e` | 5 echecs sur 10, dont `laFeuilleReelleTientLesTroisClauses` : *"clause 2 : ... declare max-width: 100%, qui n'est pas min(100%, <x>rem)"* | 0 echec sur 10 avec la forme |
| Q3 | Arbre non mute, reacteur complet | `bash outils/mvn-jdk21.sh -B clean verify` (racine, sans `-pl`) | base : 0 echec, 2343 tests | 0 echec, 2354 tests |
| Q4 | Un test lit l'ancre | clause 1 sur la feuille reelle | aucun test ne lisait `.image-item` | `laFeuilleReelleTientLesTroisClauses` vert, ancre 176 |

(Q2 : le test avait 10 tests a cette mesure ; le onzieme, `leJeuFautifCommiteEstRefuse`, est venu apres, avec le jeu fautif.)

### 9.3 Mutations sur la feuille reelle

Deux niveaux, tous deux sur materiau reel.

**Sur le fichier** (`recreo.css` mute, puis restaure ; identifiant de blob verifie egal avant et apres la serie), commande du backlog : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dmaven.test.failure.ignore=true`, perimetre le reacteur de `bootstrap` avec `-am`, ITs exclus. Le critere demande au moins un echec nomme dans la clause nommee.

| # | Mutation | Echecs (adapter-web / bootstrap) | Message de `laFeuilleReelleTientLesTroisClauses` |
|---|---|---|---|
| P1' | `min(100%, 11rem)` devient `min(100%, 14rem)` (les deux declarations) | 5 / 0 | clause 2, `max-width: min(100%, 14rem)` |
| P2' | `min(100%, 11rem)` devient `100%` | 5 / 0 | clause 2, `max-width: 100%` |
| P3' | `max-height: 14rem` ajoute a `.propositions-oeuvres .proposition.deja-essayee img` | 2 / 0 | clause 2, selecteur `.deja-essayee img` nomme |
| P4' | `min-height: 14rem` ajoute a la regle de base de l'image | 2 / 0 | clause 3, `min-height` |
| P5' | `.image-item { max-height: 11rem }` devient `14rem` | 3 / 0 | clause 1, *"vaut 14.0rem, soit 224 px, et non 176 px"* |

Les echecs en plus du test de la feuille sont les tests de morsure du meme fichier, qui mutent une feuille deja mutee (`unPlafondPlusBas...`, `uneRegleDeBaseSansMaxHeight...`, `unPlafondDeImage...`) : ils rougissent pour la meme cause et ne sont pas des echecs independants.

**En memoire, dans le test** : `unPlafondDeImageQuiMonteFaitEchouerLaClause2` (P1'), `unPlafondDeImageRemplaceParCentPourCentFaitEchouerLaClause2` (P2'), `unMaxHeightHorsDeLaFormeSurLEtatEssayeFaitEchouerLaClause2` (P3'),
`unMinHeightSurLImageFaitEchouerLaClause3` (P4'), `unPlafondDeBaseQuiMonteFaitEchouerLaClause1` (P5'), et quatre de plus : `uneHauteurAutreQueAutoSurLImageFaitEchouerLaClause3`, `uneRegleDeBaseSansMaxHeightFaitEchouerLaClause2`,
`lePerimetreSeDeriveDuSelecteurEtPasDUneListe` (un selecteur neuf `...nouvelle-regle img` est lu sans etre liste), `leJeuFautifCommiteEstRefuse`. Une borne dans l'autre sens : `unPlafondPlusBasQueLeNombreDecideNeFaitPasEchouerLeGardeFou` (9rem reste vert).

### 9.4 Delta de tests, jobs

- **+11 tests**, mesure sur `e8155d7e` : 120 + 18 + 408 + 1458 + 333 + 6 = 2343 ; sur `1f0fe8e4` : 120 + 18 + 419 + 1458 + 333 + 6 = 2354, l'ecart est tout dans `adapter-web` (408 vers 419). Le total apres fusion est a `sofia-sm`.
- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify` depuis la racine, `BUILD SUCCESS`, 0 echec, 0 erreur.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, et `verifier-prefixes.sh` sur `e8155d7e..HEAD` (exit 0). Non lance : la plage `BASE..SHA` de GitHub.
- **`chemins`** : `verifier-chemins-test.sh` et `git ls-files | verifier-chemins.sh` (exit 0).
- **`outils`** : les dix juges `outils/*-test.sh`, `squad/outils/*/*-test.sh`, `outils/*-test.py`, exit 0 chacun.
- Non lance : l'etape `mvn -B -pl bootstrap -am package`.

### 9.5 Ce qui n'est pas verifie

- **Le rendu.** Je n'ai pas lance le jar : le jeu n'a pas d'entree de catalogue, aucune page ne montre les trois images. Que `min(100%, 11rem)` rende une image de 172 px au plus dans la case, et que la propriete se voie a cette taille, est a l'ecran (`Q7`) et a `nadia-prof`. `max-height: min(100%, 11rem)` repose sur une case de hauteur definie (`aspect-ratio: 1 / 1` de la case, inchange) : je ne l'ai pas mesure dans un navigateur.
- **Ce que le test ne voit pas** (liste de `theo-archi`, A.2, reprise) : une regle de la feuille vendorisee, une regle sous `@media` (le decoupage par accolades de `blocsMaison` range une regle imbriquee sous la cle de son `@media`, donc un selecteur `... img` ecrit dans un `@media` echappe), une regle dont le selecteur atteint l'image sans nommer `propositions-oeuvres`, un attribut `style` du gabarit.
- Le plafond 176 est une constante de ce test, la meme valeur que celle de `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` (privee, non partagee) : deux copies du nombre, ecart signale sans y toucher.

### 9.6 Ce qui reste pour `S56-ART-CP-10-ECHEC`

1. **Prerequis, remplis cote code** : `PLAFOND` est ancetre (`1f0fe8e4`) ; l'amendement d'`ADR-0026` (clause 3, pseudo-element decoratif) est fusionne dans `round-two`. **Non remplis** : le critere de la marque (`nadia-prof`, `Q1`) et la proposition d'`ines-design` (`marque-echec-candidate.svg`, regle `::before` de la section 1.4 de `releves-s56/proposition-ines-forme-art-cp-10.md`) doivent etre ecrits ; je ne commence pas sans eux.
2. **Ce que la marque doit tenir devant ce garde-fou** : la clause 2 lit toute regle dont le sujet est `img` sous `propositions-oeuvres`. Une marque posee sur la regle `.deja-essayee img` ne peut declarer ni `max-width`, ni `max-height`, ni `width`, `height`, `min-width`, `min-height` hors de la forme (P3' et P4'). Une `border` ou un `outline` sur l'image sont admis. La marque en `::before` de la case est hors perimetre du garde-fou (sujet `::before`, pas `img`).
3. **Le garde de la marque** (A.1 de l'avis) : un test de feuille dans `adapter-web` pour les criteres 1 a 5, 7 et 8, une clause de rendu au banc du garde-fou 4 pour le critere 6, mutations `E1` a `E9`. L'ambiguite que `theo-archi` signale entre *"`.proposition` ou son `img`"* et *"dont le selecteur contient `.proposition`"* est a `nadia-prof`.
4. **Comme ici** : tout test qui lit `src/main/` doit porter son jeu fautif commite sous `contreexemple/<NomDeLaClasse>` (ADR-0013), sinon `LesGardeFousSontMisALEpreuveTest` rougit. A inscrire dans le prix du commit d'`ECHEC`.
5. Entree de catalogue, R8 et R9 : inchanges (section 8, points 3 et 4). Le point 1 de la section 8 est livre par cette section.
