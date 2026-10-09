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
