# [Sprint s57] RAPPORT de clôture

- **Sprint** : `s57-version-cp-ce1-cm1-trois-instances`, sprint à paliers, sans timebox. Début 2026-10-14 15:45 (UTC+02:00), fin 2026-10-29 17:08 (UTC+01:00), soit 15 j 2 h 23 min calendaires (`review.md`). Il s'arrête par `D3` de `planning.md` (*"quand l'utilisatrice l'arrête"*). Mots de l'utilisatrice du 2026-10-29, transmis par l'agent lanceur : *"clos s57"* ; *"Q1 02"* (plus tôt le même jour : corriger avant la clôture ce qui est sans ambiguïté) ; puis *"Q1 O1 et Q2 O1"* (le pli et la coupure de `-12`) et *"O2"* (ne pas fusionner la correction U+2011 de `-12`). **[corrige le 2026-10-29 : `DEC-0120` A.293 point 3 (`camille-po`) écrit que les mots de la clôture ne lui avaient pas été transmis ; l'agent lanceur les a transmis ensuite, le 2026-10-29, et ils sont cités ici avec leur date. Ils ne sont pas lus de la main de l'utilisatrice ; texte d'origine : *"forme 2"* sans mots]**
- **Écrit par** : `sofia-sm`, casquette Scrum Master. Branche `s57-cl-sofia-s57`, base `round-two` à `f33993b3`. **Je ne fusionne ni ne pousse** : la fusion est à l'agent lanceur, après lecture.
- **Les deux autres fichiers de la clôture** : `review.md` et `retro.md`, dans ce dossier.

## 1. Ce qui est livré

**En une phrase** : le produit de révision scolaire `recreo` couvre 166 lignes de programme sur 202 (82,18 %) contre 142 sur 200 à l'ouverture du sprint, grâce à vingt-neuf postes de jeu du CP, du CE1 et du CM1 jugés chacun par la professeure des écoles de l'équipe, dont les dix du dernier palier : neuf jeux de *Questionner le monde* et l'horloge au CE1.

| Quoi | État | Où |
|---|---|---|
| Couverture | 166 / 202 = 82,18 % (156 / 202 à la fin du palier 4), remesurée par moi | `review.md`, section 1 |
| Verdicts de `nadia-prof`, palier 5 | 2 VALIDE, 8 VALIDE SOUS RÉSERVE, 0 refus | section 3.2 |
| Tests | 3644 au `verify` complet, 0 échec | section 3.1 |
| Catalogue | 163 entrées au palier 4, 173 à la clôture | `review.md`, section 1 |
| Budget de `PAR-8` | cas haut 24,20 sur un plafond de 25,00, réserve 0,80 ; coût réel non mesuré | `review.md`, section 8 |
| Non engagé | douze stories de réserves, trois stories de pli et de trait d'union, la correction de `-12` | `review.md`, section 9 |

## 2. Décisions du sprint

Méthode : `git diff --name-status 771b55de HEAD -- squad/decisions` (`771b55de` est le dernier commit avant l'horodatage de début de `planning.md`) et `git log` sur chaque fichier. **Aucune `ADR` n'est créée.** Cinq `DEC` sont créées, trois `DEC` sont amendées, huit `ADR` sont modifiées. Ni `review.md`, ni `retro.md`, ni ce fichier, ni les corrections de `cloture-palier-5.md` et de `planning.md` ne créent ni n'amendent aucune décision.

### 2.1 `DEC` créées pendant le sprint

| Numéro | Titre (abrégé) | Créée le | Statut |
|---|---|---|---|
| `DEC-0121` | `nombreDeManches: 10` est une exigence de produit, les sept dérogations du catalogue sont nommées, deux dérogations orphelines sont ratifiées, et leur garde est routée | 2026-10-15 | Acceptée |
| `DEC-0122` | Le périmètre de garantie d'une cote de mise en page est la pile de polices que la feuille sert, rendue sur le poste de mesure ; les polices imposées sont une sensibilité rapportée | 2026-10-16 | Acceptée |
| `DEC-0123` | Six réponses de l'utilisatrice au palier 3 du CE1 (grille de `MAT-CE1-14`, dessins hors périmètre, prémisse de l'adulte étendue, arts plastiques, saisie CE1 suspendue) ; dix amendements, le dixième du 2026-10-25 | 2026-10-21 | Acceptée pour `Q-05`, `Q-06`, `Q-07`, `Q-08` et `Q-10` ; `Q-09` suspendue puis levée |
| `DEC-0124` | `Q-09` : l'utilisatrice revient sur la correction automatique du clavier ; `DEC-0047` section 2 est amendée ; quatre amendements | 2026-10-21 | Acceptée pour le sens ; appliquée (amendement second du 2026-10-21) |
| `DEC-0125` | Troisième non-entrée de `S57-TYP1-TEXTES-DE-GABARIT` et de `S57-MESSAGE-PARTIE-PERDUE` : les deux stories sont retirées du backlog actif ; un amendement | 2026-10-23 | Acceptée |

### 2.2 `DEC` amendées pendant le sprint

| Numéro | Titre (abrégé) | Amendements du sprint | Statut |
|---|---|---|---|
| `DEC-0120` | Ouverture de s57, limitée : l'ordre des matières repris sous un motif de forme 1 par rang | **232 amendements numérotés, `A.63` à `A.294`**, sans trou (lus par `grep` des titres `### A.` : `A.280` porte deux titres, 232 numéros distincts) | Acceptée le 2026-10-14 (`A.77`), inchangé |
| `DEC-0047` | Périmètre du sprint 20 : `NAT-5` en trois gestes, scope limité à `cp-nature-mot` | un amendement du 2026-10-21 (renvoi de `DEC-0124`) | Acceptée, inchangé |
| `DEC-0062` | Convention d'attribution d'un énoncé reçu hors des neuf agents | amendements E, F et G du 2026-10-21 | Acceptée le 2026-08-31, inchangé |

Les sept derniers amendements de `DEC-0120`, ceux du palier 5 et de la clôture, sont tous de `camille-po` et tous du 2026-10-28 ou du 2026-10-29 : `A.288` (neuf lignes à `couvert-valide`), `A.289` (3293 et non 3290), `A.290` (accord de `-12` engagé), `A.291` (accord fautif à deux énoncés, entrée de `-12` écrite), `A.292` (`QLM-CE1-12` à `couvert-valide`, Done technique par mesure locale), `A.293` (clôture décidée, cas haut 24,20) et `A.294` (`O2` : correction de `-12` non fusionnée). Statut `Acceptee` inchangé pour chacun.

### 2.3 `ADR` modifiées, aucune créée

Source : `confirmation-archunit-cloture-s57.md`, section 4 (`git diff --name-status 771b55de HEAD -- squad/decisions/adr/` : huit `M` plus `ADR-index.md`, aucune `A`).

| Numéro | Titre (abrégé) | Statut |
|---|---|---|
| `ADR-0024` | Faire porter l'explication d'un item par le verdict de la manche jouée, et laisser la graphie qui la justifie dans le paquet qui la déclare | Accepté, inchangé |
| `ADR-0031` | Un écran ne gouverne jamais l'apparence d'un autre | Accepté, amendé les 2026-10-16 et 2026-10-20 |
| `ADR-0054` | La largeur offerte à une rangée repliable est une fonction épinglée, jamais un effet de bord d'un seuil tiers | Accepté, amendé les 2026-10-20 et 2026-10-21 |
| `ADR-0058` | La saisie normalise dans sa propre mécanique, déclare son mode strict dans l'item, et aucun contrat ne porte de comportement | Accepté pour ses clauses de conception, une seule règle écrite |
| `ADR-0065` | Un mot lu par un enfant est jugé sur un lexique extérieur au dépôt, et la classe des homographes reste portée à la main | Accepté, amendement A3 du 2026-10-16 |
| `ADR-0067` | Le moyen de `TYP-1` vit dans la donnée servie, et la dette est arrêtée par un cliquet exact avant d'être corrigée | Accepté pour le cliquet, deux lignes d'amendement dans l'index |
| `ADR-0084` | La réponse d'une fin de partie voyage avec la question qu'elle commente : l'énoncé est une composante obligatoire du verdict | Accepté, inchangé |
| `ADR-0104` | Rapprocher deux oeuvres se sert par des propositions qui sont des reproductions, sans mécanique ni forme d'item neuve, et la mécanique ne lit jamais une proposition | Accepté, une ligne d'amendement dans l'index |

Les statuts sont ceux d'avant s57 : aucune ligne `Statut` ne figure dans le diff (`confirmation-archunit-cloture-s57.md`). `ADR-0065` A3 et `ADR-0058` n'ont pas de ligne d'amendement dans `ADR-index.md` : écart nommé par `theo-archi`, non corrigé par moi.

**Autres fichiers de décision modifiés, qui ne sont pas des décisions** : `squad/decisions/REGISTRE-identifiants.md` (compte courant **1004 identifiants, 179 préfixes**, vrai à la clôture : la garde `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` est verte), `DEC-index.md`, `ADR-index.md`.

## 3. Les deux Done

### 3.1 Done technique : non prononcé

**Le Done technique est celui de la CI : tous les jobs de `ci.yml`, jugés par la CI.** Aucune des mesures ci-dessous n'est la CI GitHub, que **personne n'a lue** : elle a été déclenchée par les pushes (fait écrit par `yanis-qa` pour `a3e05f13`, relayé), et je ne sais pas quels SHA ont été poussés. Je ne dis pas *"CI verte"*.

Mesures locales que j'ai lancées, sur le worktree de cette branche, `python` pour le job `outils` (la branche `python3` de `ci.yml` n'est pas exercée sur ce poste) :

| Job de `ci.yml` | Ce que j'ai lancé | Arbre | Résultat |
|---|---|---|---|
| `build`, étape "Verify" | `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl` | `c72d15bf` (code de `f33993b3`) | `BUILD SUCCESS`, 6 min 9 s, six modules `SUCCESS` ; 120 + 31 + 523 + 2569 + 395 + 6 = **3644 tests**, 0 échec, 0 erreur, 0 ignoré [calcul] ; JDK 21.0.10 (première ligne de l'enveloppe ; `java.version` des 334 rapports `TEST-*.xml` présents ensuite, une seule valeur) |
| `build`, étape "Package du fat jar" | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package`, `-am` présent | `cae661ba` | `BUILD SUCCESS`, 6 min 58 s ; 120 + 31 + 523 + 2569 + 395 = 3638 tests des modules amont et de `bootstrap` (Surefire), 0 échec [calcul] ; `spring-boot:repackage` exécuté |
| `build`, étape "Vérifier la présence du build Maven" | **non lancée** | - | non jugée |
| `prefixes` | `verifier-prefixes-test.sh`, `commit-atteignable-test.sh`, `verifier-garde-fou-accent-test.sh` ; `commit-atteignable.sh 077fae0b` ; `git log --no-merges --format=%s 077fae0b..HEAD` (194 sujets) dans `verifier-prefixes.sh` ; `verifier-garde-fou-accent.sh "077fae0b..HEAD"` | `cae661ba` | six sorties 0 ; *"Tous les commits portent un préfixe d'agent connu."* ; *"0 commit(s) le touchent dans la plage, tous [THEO]."* ; `077fae0b` tient lieu de `github.event.before` |
| `prefixes`, mes commits | `git log --no-merges --format=%s f33993b3..HEAD` dans `verifier-prefixes.sh` | `cae661ba` | sortie 0 |
| `outils` | balayage de `ci.yml` rejoué à la main : `outils/*-test.sh` 6, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 5 | `cae661ba` | **13 juges, 0 rouge**, aucun motif vide |
| `chemins` et gardes de documentation | `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'` dans `verifier-chemins.sh`, entrée standard réelle, ce fichier indexé ; `verifier-chemins-test.sh` ; gardes de documentation `-pl bootstrap -am` (19 tests) | arbre de `2c789417` | `chemins` : 3312 chemins, sortie 0, test du garde-fou 0 ; gardes : 0 échec, périmètre `bootstrap` et amonts |

- **Le `verify` couvre le code de `f33993b3`.** Entre ce `verify` et le dernier commit de cette branche, seuls des fichiers de `squad/` et de `docs/` changent. Il couvre un fichier de contenu que ni l'agent lanceur (`d9313f9e`) ni `yanis-qa` (`a3e05f13`) n'avaient mesuré, `ce1-qlm-besoins-de-la-plante.yaml` (`29c11e3f`, item 11 de `-03`).
- **Ce que ces mesures ne disent pas** : que la CI de GitHub est verte, que le job `build` est vert sur Linux avec Temurin 21 plutôt que sur ce JDK 21.0.10, ni que la fiche de `-03` (verdict rouvert si la CI rougit) ou celle de `-10` (ligne `couvert-valide` avec le Done technique acquis) peut le garder. Les lignes `-10` et `-12` sont `couvert-valide` sur une mesure locale.

### 3.2 Done pédagogique : celui de `nadia-prof` seule

- **Dix verdicts au palier 5** : **2 VALIDE** (`QLM-CE1-01`, `QLM-CE1-04`), **8 VALIDE SOUS RÉSERVE** (`-02`, `-03`, `-05`, `-06`, `-08`, `-10`, `-12`, horloge `MAT-CE1-11`), **0 refus**. Les verdicts des paliers 1 à 4 sont ceux de la section 1.1 de `avis-nadia-cloture-s57.md`.
- **Aucun Done neuf, aucun refus, aucune levée de réserve** n'est prononcé à la clôture. Ses lectures du 2026-10-29 sont des mesures, pas des verdicts : `-03` item 11 *tenue* (fusionnée), `-12` *tenue à 744 x 551, à refaire à 360 de large* (non fusionnée).
- **Ce qu'elle ne certifie pas** : les 166 lignes une par une, le rendu hors de 744 x 551 pour neuf des dix jeux, le Done technique.
- **À écrire par elle** : la levée de la réserve 2 de `-06` et de la réserve `d` de l'horloge (conditions atteintes à l'écran), la mesure de `V1` et le débordement à 360 dans la fiche de `-12`.

## 4. Obstacles résolus

Un obstacle est résolu quand le fichier qui le tranche existe. Détail et agent : `review.md`, section 10.

| Obstacle | Fichier qui le tranche |
|---|---|
| Accord de `-12` et entrée de `-12` | `DEC-0120` A.290, A.291, A.292 |
| Cas haut du coût à deux valeurs (23,70 et 24,20) | `DEC-0120` A.293 point 1 |
| Compte de fichiers du job `chemins` (3290 relayé) | `DEC-0120` A.289 |
| `Q3` d'A.271, `Q-283-1`, cliquet de poids, entrées de catalogue engagées | `DEC-0120` A.284, A.286, A.287, A.288 |
| Confirmation ArchUnit du palier 5 absente | `confirmation-archunit-cloture-s57.md` |
| Remesure `A276`, ligne *Premier verify complet du lot* | `cloture-palier-5.md`, sections 9 et 12 |
| Constat 3 de `nadia-prof` (`-03`, item 11) | `29c11e3f` fusionné par `12bc666e`, `avis-nadia-corrections-cloture-s57.md` |
| Rapport de tokens | `RAPPORT-tokens.md` de ce dossier (section 8) |

## 5. Obstacles ouverts

Vingt-trois lignes dans `review.md`, section 10. Chacune a un propriétaire ; **aucune n'est close sans son fichier**.

| Obstacle | Propriétaire | Artefact attendu |
|---|---|---|
| En-tête de `ce1-calendrier-dates.yaml`, ligne 1 : dit que l'heure sur un cadran n'est pas servie, faux depuis l'horloge | développeur à désigner (question Q2) | une ligne de commentaire ; texte proposé : `relecture-cloture-s57.md`, annexe A, défaut 2 bis |
| Levée de la réserve 2 de `-06` et de la réserve `d` de l'horloge | `nadia-prof` | une ligne datée dans chaque fiche |
| Réserve `c` de l'horloge : une story ou non | `camille-po` | `DEC` ou story |
| `T1` (`DA6`) : deux entrées mortes de `EFFETS_CONTRAIRES` dans le test de `-03` (*elle grandit plus vite*, *elle devient plus solide*) | `theo-archi`, `marc-dev` | correction avec son test, ou `ADR` |
| `P6` (`DA2`) : drapeau `-Dsurefire.failIfNoSpecifiedTests=false` ; faux vert mesuré, 0 test et `BUILD SUCCESS` | `yanis-qa` | amendement ou refus écrit |
| Clé `type` du catalogue : 0 ligne `type:` sur 173 entrées, le type vit dans le paquet | `theo-archi` | `ADR` ou refus écrit |
| Écarts de comptage : 3206 contre 3188 `@Test` (constant à 18), écart de 26 non décomposé | `sofia-sm`, `yanis-qa` | explication écrite |
| Gardes du palier 5 non mutées | `yanis-qa` | rapport de mutation |
| `pid.txt` non suivi à la racine du checkout principal : un seul nombre (24347), modifié le 2026-10-27 à 21:57, aucun processus de ce numéro ne tourne ; origine non établie | non désigné | suppression ou explication (question Q6) |
| `JAR` : qui lance le `verify` de chaque lot et sert le jar à l'audit | `sofia-sm` | désignation écrite |
| `P7` : forme de lancement de `verifier-chemins.sh` et `verifier-prefixes.sh` | `sofia-sm` | amendement de `squad/README.md` (question Q5) |
| `P8` : distracteur *le mulot* de `cm1-sciences-chaine-alimentaire.yaml` | `nadia-prof`, `camille-po` | avis |
| `P9` (`DA8`) : ordre de fusion de `-03` avant `-02` | `theo-archi` | `ADR` ou écrit |
| `P10` : faits du rapport de `marc-dev` absents du dépôt (`-04`, `-12`) | `marc-dev` | rapport complété |
| `P4` : intrus en tâche B de `-02` (`RAL-CE1-R1`) | `nadia-prof`, `camille-po` | story ou écrit |
| `C2`, `C4` : fusions sérialisées d'un fichier à ajouts en fin de liste (registre) | non désigné | règle écrite |
| `DA3`, `DA4`, `DA5`, `DA7`, `DA9`, `DA10` de la confirmation ArchUnit | `theo-archi`, `camille-po` | selon chaque ligne |
| `TMPDIR` mixte, version de Python et de `node` du job `outils` | `theo-archi`, `sofia-sm` | avis ; état non relu à la clôture |
| `Q-273-1` (i) : un rejeu au-delà des trois compte-t-il contre le plafond du palier 9 | `nadia-prof`, utilisatrice | réponse ; vingt et unième mention (A.294 point 7) |
| Graphie *VALIDE SOUS RÉSERVE* contre *VALIDE SOUS RESERVE* | `nadia-prof`, `camille-po` | décision |
| Constats de pli et de largeur (C1, C2, C4, C5 de `review.md`, section 7) | `camille-po`, `ines-design`, `theo-archi` | stories engagées ; **non engagées par décision** (A.293, A.294) |
| Une exécution de la CI GitHub est-elle attendue (question Q1) | utilisatrice | réponse |
| Sort des branches `s57-cl-marc-12nb` et `s57-cl-rel-12nb` (question Q4) | utilisatrice | `DEC` |

## 6. Ce que je n'ai pas pu vérifier

- **La CI GitHub**, et quels SHA ont été poussés.
- **L'étape "Vérifier la présence du build Maven"** et la branche `python3` du job `outils`.
- **Les corrections de `camille-po` aux défauts 6 à 18** de la relecture de clôture : citées par `A.293` point 9, non relues une à une.
- **Le chiffre d'ouverture, 142 sur 200** (`DEC-0120` A.57) et les totaux de tests des paliers 1 à 4 : relayés de leurs sources, non rejoués. 2498 n'est pas confirmé comme total d'ouverture.
- **Les ADR modifiées** : lues dans la confirmation de `theo-archi`, non relues une à une.
- **`review.md`, `retro.md` et ce fichier** ont été relus par le `relecteur-pedagogique` après leur écriture (`relecture-ceremonies-cloture-s57.md`, 9 défauts, corrigés dans les commits `[SOF]` qui suivent `2c789417`).
- **L'état des actions A-1 à A-10 de `retro-palier-3.md`** et des leçons de `retro-palier-2.md`.
- **Le rendu à 360 de large hors de Segoe UI**, sur un vrai téléphone, avec un lecteur d'écran : non vérifié par `nadia-prof` non plus.

## 7. Questions à l'utilisatrice

Aucune option par défaut n'est prise sans réponse.

1. **Q1. La CI GitHub est-elle lue, et par qui, avant que s57 soit dit terminé ?** Faits : les fiches de `-03` et de `-10` rouvrent leur verdict si la CI rougit ; les lignes `-10` et `-12` sont `couvert-valide` sur une mesure locale ; personne ne l'a lue. Options : **O1** lire la CI du SHA poussé de `round-two`, jobs nommés, et l'écrire en `DEC` ; **O2** ne pas la lire et l'écrire en `DEC`.
2. **Q2. Qui corrige l'en-tête de `ce1-calendrier-dates.yaml`, ligne 1 ?** `marc-dev`, `lea-dev` ou moi sous ma casquette de développeuse. Le texte proposé est dans `relecture-cloture-s57.md`, annexe A, défaut 2 bis. L'affectation entre `marc-dev`, `lea-dev` et moi est à moi selon la table de routage ; je la pose en question plutôt que de me l'attribuer.
3. **Q3. Que deviennent les stories non engagées et les constats de `nadia-prof` ?** Les douze stories de réserves, `S57-PLI-REVELATION-GABARIT-COMMUN`, `S57-HORLOGE-CADRAN-A-LA-REVELATION`, `S57-QLM-CE1-12-TRAIT-D-UNION-INSECABLE`, et les constats C1, C2, C4 et C5 de `review.md`. Options : **O1** les engager à l'ouverture du sprint suivant, `camille-po` en tête avec les décisions préalables `P1` à `P4` ; **O2** les laisser non engagées ; **O3** en abandonner par `DEC`. Le compte de reports d'un sprint à l'autre s'écrit à l'ouverture suivante (A.293 point 7).
4. **Q4. Que deviennent les branches `s57-cl-marc-12nb` et `s57-cl-rel-12nb` ?** Elles portent la correction U+2011 de `-12` et la relecture, non fusionnées et conservées par `A.294`, et le seul exemplaire de la garde *trait d'union ordinaire interdit dans une proposition*. Reprise ou abandon, écrit en `DEC`.
5. **Q5. Les amendements R-1 à R-4 de `squad/README.md` proposés dans `retro.md` sont-ils appliqués ?** Je ne les ai pas appliqués : la tâche ne les contenait pas et ce fichier est lu par tous les agents. [fermée le 2026-10-30, texte d'origine conservé : l'utilisatrice a répondu "Q5 O1" dans la conversation du 2026-10-30, à une question posée par l'agent lanceur avec trois options qui ne sont pas écrites dans le dépôt : O1 appliquer les quatre amendements, O2 en appliquer certains, O3 ne pas les appliquer. Mots verbatim relayés par l'agent lanceur, options posées par lui, non lus de la main de l'utilisatrice ; le numéro de la question et le texte des options ne sont pas dans le dépôt, leur sens est celui du relais. R-1 à R-4 sont appliqués dans `squad/README.md` (commit `00298fe4`), voir `retro.md`.]
6. **Q6. Peut-on supprimer `pid.txt` du checkout principal ?** Je ne le supprime ni ne le commite.
7. **Q7. `Q-273-1` (i), vingt et unième mention.** Un rejeu au-delà des trois compte-t-il contre le plafond de `PAR-8` du palier 9 ou contre un autre ? À `nadia-prof` pour le chiffre ; à l'utilisatrice seulement si le plafond couvre un palier 10. Aucun cas nommé ne la porte plus (A.286 point 5).

## 8. Consommation de tokens

Table produite par `python outils/rapport-tokens.py --sprint s57`, copiée sans modification depuis `RAPPORT-tokens.md` de ce dossier (mis à jour aussi : `squad/rapports/JOURNAL-tokens.md`). Fenêtre du 2026-10-14 13:45 au 2026-10-29 16:08 (UTC), soit de l'horodatage de début de `planning.md` à celui de fin de `review.md`. Compteurs de tokens, non convertis, **non additionnés entre colonnes**. Les tours de cette session écrits après la fin de `review.md` (`RAPPORT.md`, rapport de tokens, vérifications) tombent hors de cette fenêtre : le script les comptera en `hors sprint` à sa prochaine exécution.

| Agent | Modèle | Tours | Input | Output | Cache créé | Cache lu |
|---|---|---|---:|---:|---:|---:|
| `camille-po` | claude-sonnet-5-5 | 5704 | 11 448 | 6 555 310 | 31 186 103 | 798 261 112 |
| `sofia-sm` | claude-sonnet-5-5 | 4908 | 9 886 | 3 485 550 | 39 527 619 | 696 058 818 |
| `theo-archi` | claude-opus-5-5 | 3903 | 7 834 | 3 249 381 | 24 586 989 | 515 860 500 |
| `marc-dev` | claude-sonnet-5-5 | 3086 | 6 206 | 3 579 186 | 22 544 953 | 514 465 284 |
| `lea-dev` | claude-sonnet-5-5 | 1719 | 3 452 | 830 924 | 17 540 307 | 335 960 055 |
| `yanis-qa` | claude-sonnet-5-5 | 1538 | 3 088 | 795 962 | 14 634 787 | 200 936 741 |
| `relecteur-pedagogique` | claude-sonnet-5-5 | 1106 | 2 212 | 758 293 | 4 634 759 | 247 172 828 |
| `nadia-prof` | claude-opus-5-5 | 6736 | 13 544 | 5 882 440 | 39 681 517 | 900 865 943 |
| `ines-design` | claude-sonnet-5-5 | 2025 | 4 072 | 1 159 867 | 13 247 118 | 338 417 780 |
| orchestrateur (fil principal) | claude-sonnet-5-5 | 3516 | 9 374 | 5 214 362 | 23 999 225 | 872 295 700 |
| **Total** | | **34241** | **71 116** | **31 511 275** | **231 583 377** | **5 420 294 761** |

Le script a trouvé des données pour chacun des neuf agents et pour le fil principal : aucune ligne *"aucune invocation mesurée"*.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est créée ou amendée par ce fichier.** Les décisions du sprint sont listées à la section 2 : cinq `DEC` créées, trois `DEC` amendées (dont `DEC-0120`, de `A.63` à `A.294`), huit `ADR` modifiées, aucune `ADR` créée. Les décisions de l'utilisatrice du 2026-10-29 sont **relayées par l'agent lanceur**. Mots de l'utilisatrice du 2026-10-29, transmis par l'agent lanceur : *"clos s57"* ; *"Q1 02"* (plus tôt le même jour : corriger avant la clôture ce qui est sans ambiguïté) ; puis *"Q1 O1 et Q2 O1"* (le pli et la coupure de `-12`) et *"O2"* (ne pas fusionner la correction U+2011 de `-12`). Elles sont citées par `camille-po` dans `DEC-0120` A.293 et A.294 ; je ne les ai pas lues de la main de l'utilisatrice. Aucun Done n'est prononcé par moi.
