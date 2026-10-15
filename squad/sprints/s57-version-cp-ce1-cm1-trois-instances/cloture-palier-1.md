# [Sprint s57] Cloture du palier 1

- **Statut : palier 1 clos. Le sprint s57 n'est pas clos.** Le palier 2 n'est pas ouvert (section 9). Ce fichier remplace en une seule ecriture l'etat intermediaire du 2026-10-15 10:40 (`a5ca3771`) et le squelette du 2026-10-14 (`6ca84317`) : le diff garde l'histoire. Il est la premiere application de la ceremonie "cloture de palier" (`squad/README.md`, section 4).
- **Horodatage de debut du palier** : 2026-10-14 15:45 (UTC+02:00), `planning.md`, ligne 3.
- **Horodatage de fin du palier** : 2026-10-15 17:37 (UTC+02:00), lu par `date` a l'ecriture. **Duree effective : 25 h 52 min**, sous le seuil d'alerte de 41 h 38 min (section 7).
- **Ecrit par** : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` a `e7497db7`, branche `s57-sofia-cloture2`, `bash outils/verifier-lignee.sh` sort 0.
- **Ce que "clos" veut dire ici** : chaque poste engage a un verdict ecrit de `nadia-prof` (section 1), la porte technique est verte sur l'arbre fusionne avec ses jobs nommes (section 3), les totaux sont recomptes (section 4). **Deux conditions de ma propre liste ne sont pas remplies et sont reportees, nommees** : la lecture de l'isolation par `nadia-prof` (section 6) et la lecture du diff de B1 par `theo-archi` (section 10, `E3`). Elles ne conditionnent aucun verdict ; elles conditionnent l'ouverture du palier 2 (section 9).

## 0. Les dix lignes de l'etat du 2026-10-15 10:40, mises a jour

| Code | Quoi | Etat | Preuve |
|---|---|---|---|
| `R1` | Fusion du poste 7, puis comparaison au commit joue | **fait** | merge `13affd6f` ; `git diff 4dd6f298 HEAD` sur `ce1-texte-narratif.yaml`, `PaquetCe1TexteNarratifTest.java` et `contreexemple/PaquetCe1TexteNarratifTest` : 0 ligne ; entree de catalogue (`paquet: ce1-texte-narratif`) identique a `4dd6f298` (comparaison de bloc) ; `grep -c "paquet: ce1-texte-narratif$"` : 1. **La ligne de matrice de `FRA-CE1-04` n'est pas passee a `couvert-valide`** : la matrice date du 2026-10-14 22:41 et ne cite pas `ce1-texte-narratif` (`grep -c` : 0). A `camille-po` |
| `R2` | Poste 8 en code : B1, mesure au jar, B2, rejeu | **fait, en quatre commits de B1 et non trois** | `25e4a823` (feuille, `L4`), `472dce51` (forme C, `ADR-0067` B.7), `6a80fe66` (voie (b), `ADR-0104`, `ADR-0084`, `ADR-0024`), `7ab7bfd7` (phrase de `M4`, `ADR-0067` D.4) ; releves `ebceb838` ; B2 `06c7804f` ; rejeu `7983b4cc`. Les deux ecarts de `CR5` sont tranches en section 10 |
| `R3` | `P-8` : story de la forme C | **faite** | `S57-FORME-C-VERDICT`, `docs/backlog/backlog.md` ligne 22000 ; livree par `472dce51` |
| `R4` | Annexe A.1 a re-figer | **fait** | annexe C de `avis-theo-poste-8-art-cp-10.md` (`49e9c475`) ; versee par `6a80fe66`, dont le message cite cinq morsures |
| `R5` | Juge pur du releve (`Q-Y4`, `Q-T2`) | **fait** | `6661e530` puis `ac9ae080` (`J5` corrige selon l'annexe E.5) ; `python outils/juger-pli-deux-oeuvres.py` : test `outils/juger-pli-deux-oeuvres-test.py` sort 0 dans le job `outils` (section 3) ; sortie sur le releve de B2 recopiee dans `rejeu-nadia-b2-palier-1.md` section 2 |
| `R6` | `F1` : garde du distracteur egal au verbe cite | **fait, et la morsure est refaite par moi** (aucun rapport de `marc-dev` dans le depot, section 10 `E1`) | `9eb47c34`, merge `cb559acf` ; sur l'arbre fusionne, `ce1-verbe-sujet.yaml` ligne 61 portee a `["rangent", "le sac"]` : `PaquetCe1VerbeSujetTest`, 29 tests, **1 echec**, `vsjCe12LeSujetEstAvantLeVerbeCiteAuMoinsUnDistracteurApresEtAuPlusUnAvant`, message `VSJ-CE1-3 ... aucun distracteur au rang du verbe cite, trouves : [rangent]` ; fichier restaure par `git checkout`, `git diff` vide. Mesure partielle : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-content -am test -Dtest=PaquetCe1VerbeSujetTest`, perimetre `adapter-content` et ses amonts. Paquet inchange entre `e61e946c` et `a6f64e94` |
| `R7` | Stories de `camille-po` | **faites** | `S57-RCT-CE1-R1`, amendement `Q-R4`, `S57-MESSAGE-PARTIE-PERDUE`, `S57-VSJ-GARDE-VERBE-CITE`, `S57-GARDE-NOMBRE-DE-MANCHES`, `S57-FORME-C-PHRASE-M4`, `S57-TYP1-TEXTES-DE-GABARIT` (backlog lignes 22019 a 22156) ; `DEC-0121` (`F4` : 10 est une exigence). Le pleonasme d'`ines-design` : `nadia-prof` le renvoie a elle (`reponse-nadia-forme2-palier-1.md` ligne 252), aucun fichier ne le traite |
| `R8` | Mesure sans `L1` ni `L2` (`TPH-CE1-R1`, `GNA-CE1-R1`, `VSJ-CE1-R2`) | **ouvert, palier 2** | aucun releve dans `squad/conformite/releves-s57/` sur ces trois jeux ; a `ines-design` |
| `R9` | Releve du `relecteur-pedagogique` | **partiel** | `N1` a `N4`, `D1` a `D4` traites par `nadia-prof` (`reponse-nadia-q-palier-1.md` sections 4 et 5) ; `D5` **ouvert** : `grep -rln "brouillon soumis" adapters/adapter-content/src/main/resources/contenu` rend encore 10 paquets, dont les trois de `nadia-prof` (`ce1-phrases-types`, `ce1-verbe-sujet`, `ce1-accord-groupe-nominal`), `P12` ; `D3` et `S1` a `S5` : les mots de l'utilisatrice manquent, `P5` |
| `R10` | Cloture | **ce fichier**, sauf isolation | sections 3, 4, 6, 8 |

## 1. Verdict de chaque poste engage

| Poste | Instance | Verdict de `nadia-prof`, ou report ecrit | Fichier du verdict | Fusion |
|---|---|---|---|---|
| 3, `VSJ-CE1-R1` | A | `VSJ-CE1-R1` levee ; `FRA-CE1-13` VALIDE SOUS RESERVE, reserve neuve non bloquante `VSJ-CE1-R2` | `rejeu-nadia-palier-1.md` ; `FICHE-bandit-manchot-verbe-sujet-ce1.md` | `65d00d8d` |
| 4, `ce1-phrases-types` | A' | VALIDE SOUS RESERVE, `TPH-CE1-R1` non bloquante | `FICHE-bandit-manchot-phrases-types-ce1.md` | `c935d764` |
| 5, `ce1-accord-groupe-nominal` | A' | VALIDE SOUS RESERVE, `GNA-CE1-R1` non bloquante | `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` | `d67204de` |
| 6, `S54-OBS-SCI-CONSIGNE` | B | VALIDE, sans reserve ; `SCI-CM1-20` non rouverte | `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` | `14d6cf47` |
| 7, `ce1-texte-narratif` | A | **VALIDE SOUS RESERVE**, jar joue sur `4dd6f298` ; reserve neuve non bloquante `RCT-CE1-R1` ; titre *"Lis et réponds"* tenu | `FICHE-bandit-manchot-texte-narratif-ce1.md` ; `reponse-nadia-forme2-palier-1.md` section 1 | `13affd6f` |
| 8, correction et rejeu d'`ART-CP-10` | B | **VALIDE SOUS RESERVE, 2026-10-15**, jar joue sur `ac9ae080` : refus du 2026-10-11 leve, `RDO-CP-R1` et `RDO-CP-R2` levees, reserve neuve non bloquante `RDO-CP-R3` (la premiere phrase de `M4` deborde de sa colonne entre 720 et 768 px, 21,2 px a 720 avec barre) | `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` (rejeu du 2026-10-15) ; `rejeu-nadia-b2-palier-1.md` | B1 `24075b12` et `a6f64e94`, B2 `e7497db7` |
| 9, branche B2 | B | pas de verdict propre (hors `PAR`) : entree `bandit-manchot-rapprocher-deux-oeuvres-cp` rendue au catalogue, jouee par le rejeu du poste 8 | `rejeu-nadia-b2-palier-1.md` section 1 (`R1` a `R3` de `avis-faisabilite.md` 3.3) | `e7497db7` |

`R1` de `avis-faisabilite.md` 3.3, execute par moi : `git diff ac9ae080 HEAD -- adapters domain application bootstrap` rend 0 ligne. Le contenu servi par l'arbre fusionne est celui que `nadia-prof` a joue.

## 2. Glissements, reports, limite

- **Aucun glissement.** Les postes 7 et 8 sont audites dans le palier ; le poste 9 est fusionne ; `S54-OBS-SCI-CONSIGNE` est VALIDE (audite le 2026-10-14, deux reports comptes, plus rien a craindre). `RP-ACCUEIL-147` : fait par `nadia-prof` le 2026-10-15, voie (b) ; que cela satisfasse le critere 1 amende reste a `camille-po`.
- **Premier report du poste 8** (la reprise, A.83) : reste ecrit. La ligne `ART-CP-10` a maintenant un rejeu et un verdict ecrit ; **sa fermeture dans la matrice est a `camille-po`** et n'est pas faite (`grep -n "ART-CP-10" squad/programme/MATRICE-couverture.md` : ligne 2275, *"etat a la cloture de s56"*, `non couvert`). Je ne la ferme pas.
- **Poste 7** : un report neuf du planning (le poste n'etait pas engage a l'ouverture) ; audite dans le palier, il est solde.

## 3. Porte technique

Arbre : `round-two` a `e7497db7`, worktree dedie, aucun fichier modifie avant la mesure (`git status --short` : 0 ligne). **Les quatre jobs de `ci.yml` et l'etape de package ont ete lances par moi, sur ce poste.** Aucun resultat n'est celui de la CI distante, que je n'ai pas lue.

- **`build`** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` : `BUILD SUCCESS`, exit 0, 3 min 39 s. **2597 tests, 0 echec, 0 erreur, 0 ignore** : domain 120, application 31, adapter-web 492, adapter-content 1567, bootstrap 381, integration 6. JDK 21.0.10 (`java.version` des 60 rapports Surefire de `bootstrap`, une seule valeur). Recompte par l'attribut `tests` des fichiers `TEST-*.xml` de chaque module (60 fichiers de `bootstrap`, 4 du Failsafe) : meme valeur que les lignes `Tests run` de la sortie Maven. Trois lignes `ERROR` apparaissent dans la sortie (`GestionnaireDesErreurs`, `defaut imprevu sur GET /test-voix/manche`, `POST /parties/un-jeu`, `POST /parties/jeu-fabrique-absent-du-catalogue/coups`) : ce sont les journaux de tests qui provoquent l'erreur a dessein, aucun test n'echoue. **Le `build` a ete relance une seconde fois avec ce fichier present sur disque** (des tests de `bootstrap` lisent `squad/`) : `BUILD SUCCESS`, exit 0, memes six totaux, 2597. Il a lu ce fichier avant l'ajout de cette phrase, qui ne contient aucun tableau.
- **Etape "Package du fat jar"** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package` : `BUILD SUCCESS`, exit 0 ; `bootstrap/target/recreo.jar` produit (21,5 M). Sans `clean` : c'est la commande de l'etape.
- **`chemins`** : `verifier-chemins-test.sh` sort 0 ; `git ls-files` (moins `.github/scripts/verifier-chemins-test.sh`) passe a `verifier-chemins.sh` : sort 0.
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` sortent 0 ; les 48 sujets de commit hors fusions de `0aec34f3^..HEAD`, puis les sujets avec les fusions, passent dans `verifier-prefixes.sh` : sortent 0 ; `verifier-garde-fou-accent.sh 0aec34f3^..HEAD` sort 0 (la plage, et non l'etape de CI, dont la base `github.event.before` n'existe pas en local). Le commit qui porte ce fichier n'existe pas encore : son sujet est a passer au meme script avant le commit, resultat dans le retour de ma tache.
- **`outils`** : les trois balayages (`outils/*-test.sh` : 5 ; `squad/outils/*/*-test.sh` : 2 ; `outils/*-test.py` : 5), **12 juges lances avec `python`** (le `python3` de ce poste est un leurre du magasin d'applications, non un interpreteur), 12 sortis 0, aucun balayage vide.

**ArchUnit.** Compte par la methode de `avis-theo-poste-8-art-cp-10.md` 3.4 (fichiers `*.java` suivis par git, motifs `static final ArchRule` et `@ArchTest`), sur `e7497db7` : **41 et 38, dans 16 fichiers**, identiques au compte de `theo-archi` sur `05598be4` : aucune regle neuve dans le palier apres l'annexe D. Les regles s'executent dans le `build` ci-dessus (module `bootstrap`). Un `grep -rh` sur l'arbre de travail rend 43 : il compte en plus une copie de ressource de test sous `bootstrap/target/test-classes/contreexemple/`, non suivie par git ; seule la methode de l'avis fait foi.

## 4. Fusions et comptage du catalogue

Compte de depart sur `round-two` a `6ca84317` : 146. `grep -c "^  - id:" adapters/adapter-content/src/main/resources/catalogue.yaml` lu **dans chaque commit de fusion** (`git show <fusion>:...`), pas sur l'arbre de travail.

| Fusion | SHA | Compte avant | Entrees | Compte apres | `grep -c "paquet: <nom>$"` | Tests du reacteur apres la fusion | Base de la branche, ancetre de `round-two` |
|---|---|---|---|---|---|---|---|
| Poste 4 `s57-fra-ce1-10` | `c935d764` | 146 | 1 | 147 | `ce1-phrases-types` : 1 | 2520 | `3a3ea97e`, oui |
| Poste 5 `s57-sofia-fra-ce1-15` (branche de `sofia-sm` en casquette developpeuse, fusionnee en casquette Scrum Master ; juge : `yanis-qa`, `F2`) | `d67204de` | 147 | 1 | 148 | `ce1-accord-groupe-nominal` : 1 | 2541 | `ea6d6f92`, oui |
| Poste 6 `s57-sci-consigne` | `14d6cf47` | 148 | 0 | 148 | sans objet | 2542 | `faf7e285`, oui |
| Poste 3 `s57-marc-vsj-ce1-r1` | `65d00d8d` | 148 | 0 | 148 | sans objet | 2548 | `6ca84317`, oui |
| Poste 7 `s57-marc-fra-ce1-04` | `13affd6f` | 148 | 1 | **149** | `ce1-texte-narratif` : 1 | non remesure a cette fusion | `afade53a`, oui |
| F1 `s57-marc-vsj-garde-verbe` | `cb559acf` | 149 | 0 | 149 | sans objet | non remesure | `e61e946c`, oui |
| B1, feuille et forme C `s57-lea-b1` | `24075b12` | 149 | 0 | 149 | sans objet | non remesure | `05598be4`, oui |
| B1, voie (b) et `M4` `s57-lea-b1-c3` | `a6f64e94` | 149 | 0 | 149 | sans objet | non remesure | `0bf35a06`, oui |
| B2 (poste 9) et rejeu `s57-nadia-rejeu-b2` | `e7497db7` | 149 | 1 | **150** | `bandit-manchot-rapprocher-deux-oeuvres-cp` : 1 | **2597** (section 3) | `a6f64e94`, oui |

Les fusions de documents seuls (`033311a6`, `af53d483`, `05598be4`, `f53852e5`, `cb28115b`, `e61e946c`, `0bf35a06`) laissent le compte a 148 puis 149 ; `git diff-tree --cc` ne rend aucune ligne pour aucune des douze fusions du palier apres `6ca84317` : **aucun conflit, aucune resolution a relire**.

**Total de tests : 2597 sur l'arbre fusionne, recompte par moi (section 3). Ecart avec le dernier total que j'ai mesure (2548 a `c468c8dd`) : +49, par module : adapter-web +6 (486 a 492), adapter-content +33 (1534 a 1567), bootstrap +10 (371 a 381), domain, application et integration 0.** Origine, par comptage des annotations de test ajoutees dans les commits (`git show <commit> -- '*Test.java'`, lignes `@Test`, `@ParameterizedTest`, `@ArchTest`) : adapter-content 32 (`4dd6f298`, poste 7) + 1 (`9eb47c34`, `F1`) = 33 ; adapter-web 4 (`472dce51`, forme C) + 2 (`7ab7bfd7`, `M4`) = 6 ; bootstrap 10 `@Test` (`6a80fe66`, test de `D7`). Les trois sommes egalent l'ecart par module. Ce comptage statique n'est pas une mesure par fusion : je n'ai pas relance Maven apres chacune des cinq fusions de code de la seconde moitie du palier. `nadia-prof` annonce le meme total (2597, `rejeu-nadia-b2-palier-1.md` section 10, sur un arbre dont le code est celui-ci) ; je ne le relaie pas, je l'ai mesure.

**Ce qui est fusionne est ce qui a ete joue** : poste 7 (section 0, `R1`) et poste 8 (section 1) : 0 ligne de difference. Les quatre paquets des postes 3 a 6 : 0 ligne (mesure de la cloture precedente). `ce1-verbe-sujet.yaml` differe de `65d00d8d` d'un seul commentaire d'en-tete (`f4e9190c`, `[REL]`, `D5`), `git diff` : 1 ligne ajoutee, 1 retiree, toutes deux des lignes `#`.

## 5. Ordre du critere 3 de `S57-ARB-PALIER-1`

Inchange depuis l'etat du 2026-10-15 10:40.

- `ce1-accord-groupe-nominal` : verdict `0688b581`, `git merge-base --is-ancestor 13d6285f 0688b581` sort 0.
- `ce1-phrases-types` : la ligne *"lecture complete"* et le premier verdict sont dans le meme commit `13d6285f` ; l'ordre est tenu a la lettre, non demontre entre deux commits.
- `ce1-texte-narratif` : verdict `bdc7049b` ; la fiche cite la lecture complete de la version d'avant `D1` et `D2` (`7161ab69`) et dit avoir joue les 20 items de la version corrigee au jar. **Ouvert** : `nadia-prof` n'a pas dit si cela vaut la ligne du critere 3 (palier 2, `P7`).

## 6. Isolation

**Non lue.** La lecture de `nadia-prof` des transcriptions du palier (repertoires et ports de la table de `planning.md`, section "Isolation", respectes ou ecart nomme) n'est dans aucun fichier du depot : `rejeu-nadia-b2-palier-1.md` cite son repertoire et son port (`s57-p1-nadia-prof-b`, 18513) sans dire qu'elle a lu la table. Ce que je constate moi-meme, sans m'y substituer : le journal de `ebceb838` dit le jar de B2 sert sur 18531, port de `lea-dev` de la table ; `engagement-poste-8.md` attribue 19231 (`lea-dev`) et 19243 (`ines-design`) au debogage de Chrome ; aucun script du depot ne code un autre port (`git grep -n "1923[0-9]\|1924[0-9]\|1853[0-9]\|1851[0-9]"` sur `squad/conformite/releves-s57/*.mjs` : aucune ligne, les scripts recoivent leur port en argument). A `nadia-prof`, condition du palier 2 (section 9).

## 7. Duree effective, seuil, reevaluation

- **Duree** : du 2026-10-14 15:45 (horodatage de `planning.md`, commit `b7a64ab4` a 15:57) a 2026-10-15 17:37 : **25 h 52 min**. Derniere fusion de code et dernier verdict : `7983b4cc` et `e7497db7`, 2026-10-15 17:12 (`git log --format='%h %ad'`).
- **Seuil d'alerte (`P5'`, `D4` de `planning.md`)** : 41 h 38 min par palier. **Non atteint**, aucune alerte a ecrire. Mediane observee avant s57 : 17 h 33 min (7 h 38 min a 41 h 38 min, s54 a s56). Ce palier est au-dessus de la mediane et dans l'intervalle observe.
- **Reevaluation (`P6'`, `D5`)** : un point de mesure pour des paliers a trois instances sur du francais CE1. Je ne propose aucun changement de la duree : un amendement de `planning.md` s'ecrirait sur une seule mesure. L'ordre de grandeur de `duree-et-paliers.md` section 3 (10 paliers, environ 175 h 30 min a la mediane) n'est ni confirme ni infirme.
- **Charge** : engage 7,25 sur 8,0, 0,75 non affectes (instance B), plafond de 5,0 non approche (A 2,5, A' 3,0, B 1,75). Le rejeu de B2 est chiffre 0,5 par `nadia-prof`, prix du planning. Le chemin critique a ete la chaine B1 puis B2 de `lea-dev`, comme `engagement-poste-8.md` l'avait dit.

**`Q-S1`, forme des ceremonies d'un sprint a paliers : repondue et ecrite avant ce fichier.** Reponse de l'utilisatrice, 2026-10-14, forme 2 (*"ok"*, relayee par l'agent principal, non lue de sa main) ; amendement de `squad/README.md` section 4 (lignes 164 a 176) fait avec `planning.md`. La forme : une seule `review.md` et une seule `retro.md` a la cloture du sprint, plus un `cloture-palier-N.md` par palier clos, ecrit une seule fois ; un palier suivant s'ouvre par un amendement date de `planning.md`. **Ce fichier est ecrit une seule fois a la cloture** ; ses deux etats precedents etaient des etats intermediaires marques non clos, remplaces en une ecriture. Aucun fichier de `review.md`, de `retro.md` ni de `RAPPORT.md` n'est ecrit ici, et le rapport de tokens (`python outils/rapport-tokens.py --sprint s57`) reste un seul pour le sprint, a la review.

## 8. Obstacles du palier

Repris dans `review.md` a la cloture du sprint. **Un obstacle n'est clos que quand le fichier qui le tranche existe sur le disque.**

| Obstacle | Categorie | Agent | Artefact | Etat |
|---|---|---|---|---|
| Forme C sans story | produit | `camille-po` | story | **clos** : `S57-FORME-C-VERDICT`, backlog ligne 22000 |
| Annexe A.1 contredite par `Q1` et `Q2` | technique | `theo-archi` | texte re-fige | **clos** : annexe C de `avis-theo-poste-8-art-cp-10.md` |
| Amendement d'`ADR-0067` (B.7, puis D.4) | technique | `theo-archi`, verse par `lea-dev` | amendements | **clos** : `## Amendement du 2026-10-14` (ligne 156) et `## Amendement du 2026-10-15` (ligne 192) de `ADR-0067`, lignes d'`ADR-index.md` 223 et 225 |
| Amendements d'`ADR-0104`, `ADR-0084`, `ADR-0024` | technique | `theo-archi`, verse par `lea-dev` | amendements | **clos** : `6a80fe66`, `ADR-0104` ligne 496, ligne d'`ADR-index.md` 224 |
| Juge pur du releve | qualite | `yanis-qa` | juge dans le job `outils` | **clos** : `outils/juger-pli-deux-oeuvres.py` et son test, 12 juges lances (section 3) |
| `F1`, distracteur egal au verbe cite | qualite | `marc-dev` | test et jeu fautif | **clos**, morsure refaite par moi (section 0 `R6`) ; le rapport de `marc-dev` manque (`E1`) |
| `F4`, `nombreDeManches: 10` | produit | `camille-po` | decision | **clos** : `DEC-0121`, `Acceptee`. Le garde reste une story non engagee (`S57-GARDE-NOMBRE-DE-MANCHES`) |
| Poste 7 non fusionne | capacite | `sofia-sm` | fusion | **clos** : `13affd6f` |
| `Q-T3`, procedure de versement du texte de `theo-archi` | redaction | `sofia-sm` | reponse | **clos** : `engagement-poste-8.md` section 3 |
| `Q-T2`, qui ecrit le juge pur | capacite | `sofia-sm` | affectation | **clos** : juge livre par `yanis-qa` |
| `RP-ACCUEIL-147` | produit | `nadia-prof` | annotation | **clos** (section 2) |
| `Q-T8`, 65 enonces d'`additions-cp` | pedagogie | `nadia-prof` | reponse | **clos** : `reponse-nadia-q8.md` ; **qui ecrit le script et mesure : ouvert**, `P3` |
| Forme commune des propositions longues, mesure | pedagogie et interface | `ines-design`, `nadia-prof` | releve, puis avis | **ouvert, palier 2**, `P3` |
| `RDO-CP-R3`, premiere phrase de `M4` | pedagogie et interface | `ines-design`, `theo-archi`, `camille-po` | story, forme | **ouvert, palier 2**, `P2` |
| Provenance `S1` a `S4`, `D3` | redaction | `sofia-sm`, `camille-po` | mots de l'utilisatrice, avec leur date | **ouvert** : les mots ne sont pas dans le depot, `P5` |
| Lecture du diff de B1 par `theo-archi` | technique | `theo-archi` | trace ecrite | **ouvert**, `E3` |
| Isolation lue par `nadia-prof` | pedagogie | `nadia-prof` | lecture ecrite | **ouvert**, section 6 |

## 9. Ce qui reste pour le palier 2, avec son titulaire

Rien n'est decide ici : chaque ligne est routee. Le palier 2 n'est pas ouvert et ce fichier ne le compose pas.

**Pour ouvrir le palier 2** (`D2` de `planning.md`, quatre conditions) :

| Condition | Etat | Qui doit faire quoi |
|---|---|---|
| (a) `cloture-palier-1.md` existe sur `round-two` | **ce fichier, apres sa fusion** | `sofia-sm` ecrit ; le fichier est sur la branche `s57-sofia-cloture2`, non fusionne : la fusion est a l'agent principal, apres sa lecture |
| (b) arbitrage prealable de `nadia-prof` pour chaque instance du palier | absent | **`nadia-prof`**, avant le premier lot du palier (11.3, condition 3) ; elle y dit aussi la lecture de l'isolation (section 6) et la valeur du critere 3 pour `ce1-texte-narratif` (section 5) |
| (c) composition par `camille-po`, portee par un amendement date de `planning.md` avec la reference de l'arbitrage, heure inscrite avant l'ouverture des worktrees | absente | **`camille-po`** compose (lots restants du rang 1 : X `-05`, `-06`, `-07`, `-11`, Y `-24` ; reserves et stories ci-dessous) |
| (d) table d'isolation du palier, repertoires et ports neufs | absente | **`sofia-sm`**, dans le meme amendement, apres la composition |

Ma part de l'amendement date de `planning.md` : l'heure d'ouverture, la table d'isolation, les affectations nominatives, et l'inscription des corrections de provenance `S1` a `S4` quand les mots seront dans le depot. Je ne le redige pas avant la composition et l'arbitrage.

**Ce qui reste, par titulaire :**

| Code | Quoi | Titulaire |
|---|---|---|
| `P1` | `RCT-CE1-R1` (story `S57-RCT-CE1-R1`, palier cible 2, rejeu 0,25 si le paquet est inchange, 0,5 si des textes changent) | `camille-po` compose ; `nadia-prof` rejoue |
| `P2` | `RDO-CP-R3` en story, avec les cotes et la levee de la fiche (sections 2 et 6), sans reformulation ; forme : `ines-design`, `theo-archi` (le garde-fou de `ADR-0067` D.4 si la coupure est retenue) | `camille-po`, `ines-design`, `theo-archi` |
| `P3` | Mesure de la forme commune sans `L1` ni `L2` sur `TPH-CE1-R1`, `GNA-CE1-R1`, `VSJ-CE1-R2` (`A5`, avant la composition) ; la cote de reference de `Q-R4` et les 65 enonces d'`additions-cp` par script (`Q-T8`, `CR4`) ; mesure prealable de `Q-T6`. **Qui ecrit le script et mesure est une affectation de capacite, a moi** ; je ne l'engage pas avant la composition. Proposition, sans engagement : `ines-design` mesure sur le jar de base de `Q-R5` (le commit d'ou part la branche de la forme), un developpeur ecrit le script des 65 enonces ; le nom est dans l'amendement date | `ines-design` (`A5`), `sofia-sm` (affectation) |
| `P4` | `S57-MESSAGE-PARTIE-PERDUE` (prix 0, regard de `nadia-prof` au premier rejeu qui suit), `S57-TYP1-TEXTES-DE-GABARIT` (libelles *"Score : "* et *"Tu as écrit : "*, aucun rejeu, `Q-T12` de `theo-archi` sur le perimetre `th:text`), `S57-GARDE-NOMBRE-DE-MANCHES` : stories non engagees | `camille-po` (palier), `theo-archi` (`Q-T12`), `sofia-sm` (developpeur) |
| `P5` | **`D3` : les mots de l'utilisatrice sur `Q-N4` ne sont dans aucun fichier du depot** (`DEC-0120` A.87 porte *"Q-N4 = non"*, en forme 2 relayee). Meme manque pour `S1` (*"lea dev"*, session du 2026-10-13, `DEC-0120` A.38 : mots et date **trouves**, a inscrire), `S3` (*"oui pour le retrait"*, recus le 2026-10-12 d'apres `DEC-0116` A.26, et non le 2026-10-11 : a corriger), `S4` (date du *"oui plus que 8"* : 2026-10-13, `DEC-0120` A.38) et `S2` (`DEC-0120` 4.5 ne porte pas ses mots : **non trouves**). `S5` est a `theo-archi`. **L'agent lanceur doit fournir les mots et la date de `Q-N4` et de `S2`** ; `nadia-prof` annote ensuite `D3` | agent lanceur, `nadia-prof`, `sofia-sm`, `theo-archi` (`S5`) |
| `P6` | `N1` et `N2` : recommandations non bloquantes de `nadia-prof` sur `cm1-sciences-extraire-information` (rejeu 0,25 si prises) ; la prise est un choix de produit | `camille-po` |
| `P7` | `ce1-texte-narratif` et le critere 3 (section 5) | `nadia-prof` |
| `P8` | Collision du code `Q-T11` : `avis-theo-poste-8-art-cp-10.md` annexe E.6 (la reduction a 720) et `docs/backlog/backlog.md` `S57-TYP1-TEXTES-DE-GABARIT` (les libelles). `nadia-prof` a repondu aux deux en nommant l'auteur (`rejeu-nadia-b2-palier-1.md` sections 4, 5 et 7) et dit que ce sont des reperes locaux, que `PAR-5` ne garde pas. **Je ne renomme rien** : les deux fichiers sont signes par d'autres auteurs, et un nommage est un sujet de redaction. Route : `relecteur-pedagogique` dit s'il faut un code distinct ; jusqu'a sa reponse, je cite chacune des deux avec son auteur | `relecteur-pedagogique` |
| `P9` | `F1` du decompte de `theo-archi` : l'annexe E.4/E.5 ecrit six lignes hors verdict ; le juge en imprime **sept** (720 : largeur du meuble, hauteur et largeur des cases ; gauche du meuble a 744, 768, 992 et 1200 : `rejeu-nadia-b2-palier-1.md` section 2). Le juge est conforme au releve ; le texte de l'annexe compte autrement. A `theo-archi` de dire laquelle des deux lectures est la sienne et d'amender son annexe | `theo-archi` |
| `P10` | Trois polices de tablette non mesurees : le depot en nomme **deux** (San Francisco sur iPad, Roboto sur Android : `reponse-nadia-forme2-palier-1.md` section 2.1) et dit que personne ne les a mesurees. Je n'ai pas trouve de troisieme. C'est un risque nomme, pas une barre ; un rejeu sur un de ces appareils est a `nadia-prof` s'il devient possible | `nadia-prof`, `ines-design` |
| `P11` | Matrice : `FRA-CE1-04` et `ART-CP-10` a `couvert-valide`, sous les reserves `RCT-CE1-R1` et `RDO-CP-R3` ; amendement de `DEC-0120` pour les verdicts des postes 7 et 8, dans le meme geste (`DEC-0120` n'a pas ete amendee depuis le 2026-10-14 22:41, sa treizieme fois) | `camille-po` |
| `P12` | `D5` : l'en-tete *"brouillon soumis a nadia-prof"* ne porte plus d'etat de validation, commentaires seuls (`git diff -U0` : aucune ligne qui ne commence pas par `#`), aucun rejeu, prix 0 (`reponse-nadia-q-palier-1.md` 5.5). Perimetre mesure le 2026-10-15 par `grep -rln` : `ce1-accord-groupe-nominal`, `ce1-accord-sujet-verbe`, `ce1-classes-de-mots`, `ce1-familles-de-mots`, `ce1-futur`, `ce1-mots-invariables`, `ce1-negation`, `ce1-passe-compose`, `ce1-phrases-types`, `ce1-verbe-sujet`. Le releve du `relecteur-pedagogique` en nomme neuf autres (dont `ce1-conjugaison-present` et `ce1-temps`, absents de cette liste) a verifier un a un, et le rapport de s56 de `marc-dev`. Tache de capacite, a engager au planning du palier 2 | `marc-dev`, `sofia-sm` |

## 10. Ecarts constates pendant cette cloture

- **`E1` : aucun rapport de `lea-dev` (B1, B2, mesure au jar) ni de `marc-dev` (`F1`, `rapport-marc-dev-f1.md` promis par `tache-marc-dev-f1.md` section 2) n'existe dans le depot** (`git ls-files | grep s57`, dossier du sprint : seul `rapport-marc-dev.md` du poste 7 et `rapport-sofia-dev.md` du poste 5). Les engagements demandaient pour `lea-dev` les points `C1'`, `C2`, `C3`, les six tests de `C3` et les jobs nommes avec leur sortie. Le message de commit de `9eb47c34` (`F1`) n'a pas de corps. **Je n'ai donc pas relaye leurs nombres** : les morsures de `F1` sont refaites par moi, et la porte est la mienne. Les points de `C1'`, `C2` et `C3` pour B1 ne sont attestes que par `J1` a `J5` du juge sur le releve de B2 et par le verbe des messages de commit. A `lea-dev` et `marc-dev` d'ecrire leur rapport, ou de dire qu'il n'existe pas.
- **`E2` : numerotation de B1.** `engagement-poste-8.md` section 3 prevoyait trois commits ; B1 en compte quatre (`7ab7bfd7`, phrase de `M4`, que son message appelle *"commit 3 de B1"* comme `S57-FORME-C-PHRASE-M4`, alors que la voie (b) `6a80fe66` est deja le troisieme). Sans consequence sur le contenu ; dit pour qu'un lecteur ne cherche pas un troisieme commit unique.
- **`E3` : lecture du diff de B1 par `theo-archi`.** `engagement-poste-8.md` section 3 et l'avis 2.2 la posent avant la fusion de B1, et ce que la garde de `C1'` dit : *"a la lecture du diff, par moi avant la fusion de B1"*. Aucun fichier du depot, aucun message de fusion (`24075b12`, `a6f64e94` : corps vide) ne la porte. Ce que le depot atteste : `J5` corrige sur le releve de B2, qui mesure l'effet et pas le selecteur (annexe E.4 : *"la garde premiere de `C1'` reste la lecture du diff"*). A `theo-archi` : une trace ecrite, meme a posteriori, datee comme telle.
- **`E4` : les deux ecarts de `CR5`, tranches (c'est mon engagement).** (i) *"Trois morsures de A.1"* contre cinq : l'annexe C de `theo-archi` (11:19, apres mon engagement de 10:40, auteur du texte) fait foi ; `6a80fe66` en mesure cinq. (ii) Le test de `D7` : `bootstrap/src/test/java/com/recreo/bootstrap/rendu/LEcranDeFinDUnJeuAReponseEnImageNeRendAucunTexteDeLItemTest.java`, tel que `TC2` le fixe ; `6a80fe66` l'y a mis (10 tests). Les trois elements de `M4` (ligne 212 de `manche.html`, garde-fou, texte D.4 d'`ADR-0067`) sont tous dans `7ab7bfd7`. **Je ne modifie pas `engagement-poste-8.md`** : c'est un fichier de ceremonie signe, B1 est livre et fusionne, et personne n'implemente plus a partir de lui ; la correction est ici.
- **`E5` : le qualificatif *"relayee"*.** Les mots de l'utilisatrice de `Q-S1`, `Q3`, `Q-N4` sont tous en forme 2 relayee ; `relecteur-pedagogique` a note que ce qualificatif n'est pas l'une des trois formes de `DEC-0062` et ne tranche pas. La regle est a `camille-po`, la convention de redaction a moi. Aucune decision ici.

## Decisions prises

**Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`.** Liste des decisions du palier 1 (de `6ca84317^` a `e7497db7`, `git diff --name-status` sur `squad/decisions`), avec statut :

| Decision | Cree ou modifie | Statut |
|---|---|---|
| `DEC-0120` (ouverture de s57) | modifiee, amendements neuf a treize (`A.63` a `A.90`), dont le passage de `Proposee` a `Acceptee` (`A.77`, `A.78`, 2026-10-14 16:31) | `Acceptee` |
| `DEC-0121` (`nombreDeManches: 10` est une exigence, sept derogations nommees) | **creee**, 2026-10-15, `camille-po` | `Acceptee` |
| `ADR-0067` (moyen de `TYP-1`, cliquet) | modifiee deux fois : amendement du 2026-10-14 (B.7, `472dce51`) et du 2026-10-15 (D.4, `7ab7bfd7`) | `Accepte` pour le cliquet, tel que le fichier l'ecrit |
| `ADR-0104` (rapprocher deux oeuvres) | modifiee : amendement du 2026-10-14 (point 5 de la decision 4, `6a80fe66`) | `Accepte` |
| `ADR-0084` | modifiee : renvoi du 2026-10-14 (`6a80fe66`) | `Accepte` |
| `ADR-0024` | modifiee : renvoi du 2026-10-14 (`6a80fe66`) | `Accepte` |

`ADR-index.md` et `DEC-index.md` portent les lignes correspondantes (`ADR-index.md` lignes 223 a 225 ; `DEC-index.md` ligne 127 pour `DEC-0121`).
