# [Sprint s57] Clôture du palier 5

- **Statut : palier 5 livré et trié. Porte technique mesurée en local sur les quatre jobs de `ci.yml`, verte ; la CI GitHub n'a été lue par personne et rien n'est poussé (section 10). Le sprint s57 n'est pas clos.** Ce fichier est écrit une seule fois, comme `cloture-palier-4.md`. **Aucun Done n'est prononcé par `sofia-sm`** : le pédagogique est à `nadia-prof` seule (dix verdicts, section 4), le technique est jugé par la CI, que personne n'a lue. [corrige le 2026-10-29, texte d'origine conserve : l'utilisatrice a decide de clore s57 le 2026-10-29 (relaye par l'agent lanceur, `DEC-0120` A.293, non lu de sa main) ; la cloture est dans `review.md`, `retro.md` et `RAPPORT.md` du dossier. Depuis l'ecriture de ce fichier, `DEC-0120` A.289 a A.294 et les fusions de cloture ont change l'etat de `-12`, la couverture et le contenu servi : voir les marqueurs de ce fichier]
- **Horodatage de début du palier : non écrit.** Aucun amendement de `planning.md` n'ouvre le palier 5 avec une heure (`grep` des titres d'amendement : le premier qui nomme le palier 5 est l'avis de `theo-archi` sur les dessins, 2026-10-23, et celui du 2026-10-26 écrit lui-même *"Il n'ouvre aucun palier"*). Je ne fabrique pas d'ouverture après coup. Deux repères lus dans `git log` : le premier commit du palier, `e18b13e8` (proposition de `camille-po`, 2026-10-23 15:16, UTC+02:00), et la première fusion de lot, `77fc8f8e` (2026-10-26 10:39, UTC+01:00).
- **Horodatage de fin du palier** : 2026-10-29 00:03 (UTC+01:00), lu par `date` à l'écriture. **Durée calendaire** [calcul] : 5 j 9 h 47 min depuis `e18b13e8`, 2 j 13 h 24 min depuis `77fc8f8e`. Ce n'est pas une durée effective : nuits et attentes comprises, sans horloge qui les sépare. Palier 1 : 25 h 52 min, palier 2 : 25 h 11 min, palier 3 : 43 h 3 min, palier 4 : 37 h 55 min.
- **Écrit par** : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` à `fa137660` (fusion de `s57-p5-camille-288`), worktree `s57-p5-sofia-p9`. Ce fichier ne crée ni n'amende aucune décision.
- **`RAPPORT.md` et `RAPPORT-tokens.md` ne sont pas régénérés** : fichiers de fin de sprint, s57 n'est pas clos. Aucune consommation de tokens n'est chiffrée ici. `review.md` et `retro.md` ne sont pas écrits. [corrige le 2026-10-29, texte d'origine conserve : `review.md`, `retro.md`, `RAPPORT.md` et `RAPPORT-tokens.md` sont ecrits a la cloture du sprint, le 2026-10-29]

## 1. Fusions de lot : une ligne par fusion

Catalogue lu par `git show <fusion>:adapters/adapter-content/src/main/resources/catalogue.yaml`, lignes `^  - id:`, pas sur l'arbre de travail. Départ : 163 (`f3b06989`, clôture du palier 4). **172 à `HEAD`**, soit les 9 entrées de la fusion `eaa3ef65`. [corrige le 2026-10-29, texte d'origine conserve : 173 entrees a `a3e05f13` et a `f33993b3` (`grep -c '^  - id:'` sur `catalogue.yaml`, lu) : la dixieme entree, celle de `ce1-qlm-generations` (`-12`), est de `095b9cd4`, voir `DEC-0120` A.291 et A.292]

| Fusion | Lot, paquet | Heure | Avant | Entrées | Après | Base, commits apportés |
|---|---|---|---|---|---|---|
| `77fc8f8e` | `-01`, `ce1-qlm-etapes-de-developpement` | 26 10:39 | 163 | 0 | 163 | `c5fea043`, 1 |
| `ccfd1a0c` | `-03`, `ce1-qlm-besoins-de-la-plante` | 26 14:30 | 163 | 0 | 163 | `e20194d4`, 1 |
| `7c2b448c` | `-02`, `ce1-qlm-ce-que-mangent-les-animaux` | 26 15:22 | 163 | 0 | 163 | `ccfd1a0c`, 1 |
| `2485dc73` | `-04`, `ce1-qlm-familles-d-aliments` | 27 10:38 | 163 | 0 | 163 | `97c67bb2`, 1 |
| `142c3ca7` | `-05`, `ce1-qlm-eau-trois-etats` | 27 11:53 | 163 | 0 | 163 | `2485dc73`, 1 |
| `fbe5295c` | `-06`, `ce1-qlm-aimant` | 27 11:57 | 163 | 0 | 163 | `2485dc73`, 1 |
| `d37ac5f7` | `-08`, `ce1-qlm-dangers-electricite` | 27 12:04 | 163 | 0 | 163 | `2485dc73`, 1 |
| `3f39bb78` | `-10`, `ce1-qlm-paysages` | 27 12:06 | 163 | 0 | 163 | `2485dc73`, 1 |
| `bdfed668` | `-12`, `ce1-qlm-generations` | 27 12:06 | 163 | 0 | 163 | `2485dc73`, 1 |
| `025e613b` | horloge `MAT-CE1-11`, `ce1-lire-l-heure` (`lea-dev`) | 28 13:07 | 163 | 0 | 163 | `802d0227`, 2 |
| `eaa3ef65` | entrées de catalogue, huit lignes `QLM-CE1` et l'horloge (`marc-dev`) | 28 23:02 | 163 | 9 | 172 | `d6933bda`, 1 |

Heures : jour d'octobre 2026, UTC+01:00 à partir du 26 (le changement d'heure est le 25). [corrige le 2026-10-29, texte d'origine conserve : le changement d'heure est le 25 a 03:00 : les heures du 25 apres 03:00 sont deja a `+01:00`, d'ou `UTC+01:00` a partir du 25 a 03:00]

- **Lignée** : `git merge-base <premier parent> <second parent>` et `git rev-list --count <premier parent>..<second parent>` lus ce jour pour ces onze fusions, plus `d6933bda` (lecture des titres par `nadia-prof`), `f64eba26` (relecture d'orthographe) et `fa137660` (matrice) : chaque base est un ancêtre du premier parent, aucune fusion ne rapporte un historique étranger. **C'est ma relecture.** `bash outils/verifier-lignee.sh` lancé dans ce worktree : exit 0, avant toute écriture de ce fichier.
- **Entrées de catalogue** : `grep -c "paquet: <nom>$"` sur `catalogue.yaml`, à `f64eba26` puis à `HEAD` : 0 puis 1 pour huit paquets `ce1-qlm-*` (tous sauf `ce1-qlm-generations`) et pour `ce1-lire-l-heure`. `ce1-qlm-generations` : 0 puis 0, **la ligne `-12` n'a pas d'entrée**.
- **Les titres des entrées** sont ceux que `nadia-prof` a lus (`2f2977fb`, fusion `d6933bda`, qui remplace ceux de `-01` et de `-06`) ; `marc-dev` les a posés dans un commit qui descend de cette lecture (`git merge-base --is-ancestor 2f2977fb 5000092b` exit 0, relu). Orthographe relue par `relecteur-pedagogique` : `cf7c6acf`, aucune correction, fusion `f64eba26`.
- **Fusions de fiches** (verdicts de `nadia-prof`, tous des écrits seuls) : voir section 4, une fusion par verdict.
- **Total des fusions du palier** : 155 commits de fusion dans `git log --merges --first-parent f3b06989..HEAD`. Je ne les liste pas toutes : seules les onze ci-dessus portent du code ou du catalogue.
- **Ce que la fusion `fa137660` apporte** : `git diff --name-status eaa3ef65 HEAD` ne nomme que quatre fichiers, `docs/backlog/backlog.md`, `DEC-0120`, `DEC-index.md`, `MATRICE-couverture.md`. Aucun fichier sous `adapters`, `domain`, `application`, `bootstrap`, `outils`, `.github` : **le code et le contenu servis de `HEAD` sont ceux de `eaa3ef65`**. [corrige le 2026-10-29, texte d'origine conserve : faux depuis `a3e05f13` : `git diff --name-only eaa3ef65 a3e05f13` nomme `catalogue.yaml`, `ce1-qlm-generations.yaml`, `PaquetCe1QlmGenerationsTest.java`, un jeu fautif de test et `TexteAccentueExposeParLePortEntrantTest.java` (relecture de cloture, defaut 20 c) ; et a `f33993b3` un fichier de contenu differe encore de `d9313f9e` : `ce1-qlm-besoins-de-la-plante.yaml` (`29c11e3f`, item 11 de `-03`)]

## 2. Total de tests

**Le total est 3644 à `eaa3ef65`, et le code de `HEAD` est celui de `eaa3ef65`** (section 1). Trois lectures du même chiffre : [corrige le 2026-10-29, texte d'origine conserve : 3644 est mesure a `d9313f9e` par l'agent lanceur et a `a3e05f13` par `yanis-qa` ; le code de `HEAD` n'est plus celui de `eaa3ef65` (voir la section 1)]

- **Relayé** : l'agent lanceur annonce `mvn -B clean verify`, `BUILD SUCCESS`, 3644 tests (120, 31, 523, 2569, 395 unitaires, 6 d'intégration), 0 échec. Somme [calcul] : 120 + 31 + 523 + 2569 + 395 + 6 = 3644. **Je ne l'ai pas relancé pour cette clôture.**
- **Lu dans le dépôt, mesuré par `nadia-prof`** : `FICHE-ce1-lire-l-heure.md` écrit le même `verify` complet, relancé par elle à `371425e4`, 3644 tests, mêmes six lignes de module, 0 échec. `371425e4` précède l'ajout des entrées de catalogue, qui ajoutent 0 test (`rapport-marc-dev-palier-5-entrees-de-catalogue.md`, section 5) : même total avant et après.
- **Mesuré par moi à `1a810b79`** (amendement du 2026-10-28 09:56 de `planning.md`) : 3624. Écart avec 3644 : +20 [calcul], c'est l'horloge (`PaquetCe1LireLHeureTest` et les gardes de paire), sur `adapter-content` (+6) et `bootstrap` (+14).

**Recompte indépendant par les sources.** Aucun fichier `*Test.java` ne déclare `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest` (`git grep -l`, vide). `git grep -c "^\s*@Test"` sur `*Test.java`, somme par commit : `f3b06989` 3206, `1a810b79` 3598, `eaa3ef65` 3618, `HEAD` 3618. **Chaque total mesuré est égal à cette somme plus 26** : 3206 + 26 = 3232 (mesuré à `38a77bf9`, `cloture-palier-4.md` section 10.1), 3598 + 26 = 3624, 3618 + 26 = 3644. Palier : +412 tests, par les sources comme par les mesures (3232 vers 3644). **Écart avec `cloture-palier-4.md`** : cette clôture écrit 3188 `@Test` à `c11b18e1` et un écart constant de 44 ; ma commande rend 3206 au même commit et un écart de 26. Je n'explique pas la différence des deux commandes. L'écart constant est une propriété du compte, que je n'ai pas décomposée : c'est une cohérence, pas une mesure de l'arbre.

## 3. Matrice

La commande de la "Méthode du compte" de `MATRICE-couverture.md`, lancée par moi sur `HEAD` : `cv 165`, `ec 6`, `nc 31`, `ncb 75`, total 277. Dénominateur 277 - 75 = 202. **165 / 202 = 81,68 %** [calcul : 0,8168], égal à `DEC-0120` A.288. Le palier 4 se terminait à 156 / 202 = 77,23 %. [corrige le 2026-10-29, texte d'origine conserve : 166 sur 202 = 82,18 % a `a3e05f13` et a `f33993b3` : la commande de la "Methode du compte", relancee par moi le 2026-10-29, rend `cv 166`, `ec 6`, `nc 30`, `ncb 75`, total 277. `QLM-CE1-12` est `couvert-valide` depuis `DEC-0120` A.292. Les bornes 165 et 171 de ce paragraphe sont celles de leur date]

- Huit lignes `QLM-CE1` (`-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`) et `MAT-CE1-11` passent à `couvert-valide` (A.288 points 3 et 4). `QLM-CE1-12` ne bouge pas : pas d'entrée, accord en attente. [corrige le 2026-10-29, texte d'origine conserve : `-12` passe a `couvert-valide` le 2026-10-29 (`DEC-0120` A.292), apres la correction de l'accord et l'entree de catalogue (A.290, A.291)]
- **Compteur de version (A.32) : non publié en entier.** La part `couvert-valide` est lue, 165. Les six lignes `en cours` ne sont pas relues pour les cinq critères de `nadia-prof`. Borne haute [calcul] : 165 + 6 = 171 sur 202 = 84,65 %. C'est une borne, pas un compteur.
- **Un point que je ne tranche pas** : les fiches de `-03` et de `-10` conditionnent le verdict à la CI complète à la clôture (section 4). La ligne `-10` est passée à `couvert-valide` sur la mesure locale de la section 10, pas sur une exécution de GitHub. C'est la question 1 de la section 6.

## 4. Verdicts du palier, une ligne par ligne couverte

Source : le bloc de validation de `nadia-prof` dans chaque `squad/conformite/FICHE-ce1-qlm-<lot>.md` et dans `FICHE-ce1-lire-l-heure.md`, relu par `grep` du titre *"Verdict du ..."* ce jour : 2 VALIDE, 8 VALIDE SOUS RÉSERVE, **aucun REFUSÉ**. Les verdicts sont les siens, je les lis.

| Ligne | Verdict | Réserves écrites | Fusion du verdict |
|---|---|---|---|
| `-01` | VALIDE | aucune | `d2adbb4e` |
| `-02` | VALIDE SOUS RÉSERVE | `RAL-CE1-R1` | `9a7ad21f` |
| `-03` | VALIDE SOUS RÉSERVE | deux, sans identifiant (le mot *plus* de la tâche privation, gardé : *"on ne corrige pas"* ; la liste des non-besoins) | `740bbc34` |
| `-04` | VALIDE | aucune | `ca957d50` |
| `-05` | VALIDE SOUS RÉSERVE | `ETE-CE1-R1`, `ETE-CE1-R2`, `ETE-CE1-R3` | `1a810b79` |
| `-06` | VALIDE SOUS RÉSERVE | deux, sans identifiant (le plus court gagne en tâche B ; l'entrée de catalogue) | `898b6b61` |
| `-08` | VALIDE SOUS RÉSERVE | `DEL-CE1-R1` | `62757537` |
| `-10` | VALIDE SOUS RÉSERVE | une, sans identifiant (place de l'élément propre dans l'énoncé des items A) | `52eb9c47` |
| `-12` | VALIDE SOUS RÉSERVE | `V1`, repère de fiche, non alloué au registre | `08f7a2ab` |
| horloge `MAT-CE1-11` | VALIDE SOUS RÉSERVE | `a`, `c`, `d`, lettres locales aux textes de `nadia-prof` | `07961bf3` |

- **Fusion de l'horloge** : `07961bf3^2` rend `25374120`, le commit du verdict.
- **Réserve 2 de `-06` et réserve `d` de l'horloge : atteintes par l'entrée de catalogue, levée non écrite aux fiches.** `A.288` (points 3 et 8) l'écrit : *"La condition est atteinte ; la levée s'écrira dans la fiche"*, à `nadia-prof` seule. **Je ne les lève pas.** `grep` des deux fiches : aucune levée écrite. [corrige le 2026-10-29, texte d'origine conserve : `A.288` (point 3) l'ecrit pour `-06` dans les mots de `nadia-prof` (`lecture-nadia-entrees-de-catalogue-ce1-qlm-et-horloge.md`, section 3) : "La condition est atteinte ; la levee s'ecrira dans la fiche" ; pour l'horloge, meme fichier, section 2, point 9 : "L'exigence de la reserve d est atteinte par l'entree telle qu'elle est ecrite ici ; la levee s'ecrira dans la fiche". La phrase citee plus haut n'est pas celle de l'horloge (relecture de cloture, defaut 13)]
- **Réserve `c` de l'horloge** (stratégie par forme des minutes, 10 items sur 12, plancher 8) : maintenue par `nadia-prof`, *"Pas une exigence de ce lot"*. Elle l'a routée *"à garder pour un sprint suivant, sans story avant mon verdict"* (A.285 point 1). Son verdict est maintenant rendu : la question de la story est à `camille-po`, section 12.
- **Rejeux** : aucun, aucun lot refusé sur dix (fiche de l'horloge). **Q3 d'A.271 est close** par la réponse de l'utilisatrice du 2026-10-28, `DEC-0120` A.286 (*"O1 il tombe"*, relayée).
- **Quatorze stories de réserves** : l'utilisatrice répond *"Q-283-1 02"* (A.286), option *non, elles restent des stories non engagées*. Le palier se clôt avec les réserves ouvertes.
- **Les titres et consignes des entrées n'ont été regardés à l'écran par aucun agent** : `rapport-marc-dev-palier-5-entrees-de-catalogue.md`, section 6 : *"aucun rendu à l'écran. Les titres que l'enfant verra n'ont pas été regardés sur la page."* Je ne trouve aucun écrit qui les montre rendus. `nadia-prof` les a lus, ce que `QC3` demande ; l'écran n'est pas un geste de la story.
- **Un clos n'est pas un Done.** Aucune ligne de cette section ne prononce de Done pédagogique, et aucune réserve n'est levée par `sofia-sm`.

## 5. Compte des reports

Je compte, je ne tranche pas (`DEC-0120` A.66 à A.73).

| Poste | État à la clôture | Compte |
|---|---|---|
| Huit lots `-01` à `-10` (sans `-12`) et horloge `MAT-CE1-11` | livrés, fusionnés, verdicts écrits, entrées fusionnées | aucun report |
| `QLM-CE1-12` (paquet livré, verdict écrit) | pas d'entrée, ligne non couverte | **ouvert** : attend le relevé de l'accord, story `S57-QLM-CE1-12-ACCORD` non engagée [corrige le 2026-10-29, texte d'origine conserve : l'entree de `-12` est ecrite (`095b9cd4`), l'accord est corrige (A.291), la ligne est `couvert-valide` (A.292) ; aucun report] |
| quatorze stories de réserves | `NON ENGAGEE, sans palier` (A.286, `O2`) | décision de l'utilisatrice, pas un report |
| `Q-273-1` (i) | non fermée | **seizième mention** (A.288 point 6) [corrige le 2026-10-29, texte d'origine conserve : vingt et unieme mention en `DEC-0120` A.294, non fermee] |
| Q3 d'A.271 | close (A.286) | quatorzième et dernière inscription |
| `LP7`, `LP8`, `LP9` | **état non relu pour cette clôture** | je ne les compte pas ; `cloture-palier-4.md` section 5 les disait à leur deuxième écartement, sans report possible |

## 6. Ce qui a besoin de l'utilisatrice

Rien n'est décidé par moi.

1. **Q1 - Une exécution de la CI GitHub est-elle attendue avant la clôture ?** Ouverte, non tranchée. `A.288` (routage) me la confie ; je la remonte. Faits : la définition du Done technique de `CLAUDE.md` est *"CI verte, c'est-à-dire tous les jobs de `ci.yml`"*, jugée par la CI ; la mesure de la section 10 est locale ; `origin/round-two` pointe `077fae0b` (référence locale, rien n'est fetché), et `HEAD` a 307 commits d'avance sur elle avant le commit de ce fichier ; **rien n'est poussé**. Les fiches de `-03` (*"si cette CI rougit sur ce lot, ce verdict est rouvert"*) et de `-10` (*"ne passe à `couvert-valide` qu'avec le Done technique acquis"*) en dépendent. Pousser est une opération que la demande ne contient pas.
2. **Q2 - Engager ou non `S57-QLM-CE1-12-ACCORD`** (relevé de l'accord de *du plus âgé au plus jeune*, puis entrée de `-12`). `A.286`, `A.287` et `A.288` écrivent *"engager ou non"* comme étant à `sofia-sm`. Je ne l'engage pas ici : aucun lot n'est ouvert pour la porter et le périmètre est à `camille-po`. La question est donc à `camille-po`, puis à l'utilisatrice si elle la lui porte. [corrige le 2026-10-29, texte d'origine conserve : engagee et faite le 2026-10-29 (`DEC-0120` A.290 a A.292) ; la question est close]
3. **`Q-273-1` (i)** : un rejeu au-delà des trois compte-t-il contre le plafond de `PAR-8` du palier 9 ou contre un autre ? La question n'a plus de cas nommé (A.286 point 5). À `nadia-prof` pour le chiffre, à l'utilisatrice si le plafond couvre un palier 10.

## 7. Décisions prises

**Aucune `ADR` n'est créée ni modifiée dans le palier, et ce fichier ne crée ni n'amende aucune décision.** Liste de ce que `git diff --name-status f3b06989 HEAD -- squad/decisions` rend (`f3b06989` : clôture du palier 4) : cinq fichiers, tous `M`, aucun `A`.

| Décision | Créée ou modifiée | Statut |
|---|---|---|
| `DEC-0120` (ouverture de s57) | modifiée : `A.180` à `A.288`, soit 109 numéros (relus : `A.280` porte deux sections) | `Acceptee`, inchangé |
| `DEC-0123` (six réponses au palier 3 CE1) | modifiée : 8e, 9e et 10e amendements (2026-10-23 et 2026-10-25) | `Acceptee`, inchangé |
| `DEC-0125` (retrait de `TYP1` et de `MESSAGE-PARTIE-PERDUE`) | modifiée : premier amendement du 2026-10-23 | `Acceptee`, inchangé |
| `REGISTRE-identifiants.md` | modifié (5 commits du palier) : compte courant et lignes des cinq identifiants | voir ci-dessous |

Les amendements de `DEC-0120` qui portent les décisions de l'utilisatrice du palier :

- **`A.284`** : relèvement du cliquet de poids des illustrations accordé pour l'horloge, de 69 203 à 96 873 octets (*"Q1 O1 on l'accorde"*, relayé). Le code l'avait posé avant l'accord.
- **`A.286`** : `Q-283-1` répondue `O2` (les quatorze stories restent non engagées), Q3 d'A.271 close (*"O1 il tombe"*) ; plafond de `PAR-8` du palier 9 **25,00** (A.282), cas haut 23,70.
- **`A.287`** : entrées de catalogue engagées avant la clôture (*"O2"*), neuf entrées dont l'horloge.
- **`A.288`** : entrées fusionnées, neuf lignes à `couvert-valide`.

Les décisions de l'utilisatrice sont **relayées par l'agent lanceur (forme 2, `DEC-0062` G)**, citées par `camille-po` : je ne les ai pas lues de sa main.

**Registre des identifiants.** `squad/decisions/REGISTRE-identifiants.md`, ligne *"Compte courant, verifié par la CI"* : **1004 identifiants alloués, 179 préfixes distincts**, lu ce jour (ligne 978). Cinq identifiants alloués dans le palier par `nadia-prof` : `RAL-CE1-R1`, `DEL-CE1-R1`, `ETE-CE1-R1`, `ETE-CE1-R2`, `ETE-CE1-R3`. 999 + 5 = 1004 [calcul].

`DEC-index.md` porte les lignes d'amendement de `DEC-0120`, `DEC-0123` et `DEC-0125`.

## 8. Coût : ce que les textes chiffrent

Méthode : je ne chiffre que ce qu'une règle lue chiffre. Aucun prix n'est écrit pour un poste que les textes ne chiffrent pas. Chiffres de `DEC-0120` A.282 et A.286, mêmes calculs, **[calcul]** :

| Grandeur | Valeur | Source ou calcul |
|---|---|---|
| Plafond de `PAR-8` du palier 9 | 25,00 | A.282, valeur de l'utilisatrice |
| Engagé | 13,50 | A.286, inchangé |
| Marge de l'instance neuve | 1,5 | A.286, inchangée |
| Cas haut | 23,70 | A.286 : 17,70 + 4,50 (neuf secondes lectures) + 1,00 (deux troisièmes lectures, `-03` et `-08`) + 0,50 (seconde lecture de l'horloge) [corrige le 2026-10-29, texte d'origine conserve : 24,20 en vigueur depuis `DEC-0120` A.293 point 1] |
| Réserve de plafond | 1,30 | [calcul] 25,00 - 23,70, **non affectée** (A.286) [corrige le 2026-10-29, texte d'origine conserve : 0,80 en vigueur depuis `DEC-0120` A.293 point 1] |

- **Le coût réel du palier n'est pas mesuré.** 23,70 est un cas haut, une borne, pas une consommation (méthode d'A.270 point 3).
- **Postes non chiffrés par les textes, qui le restent** : l'audit à l'écran (dix audits dont l'horloge), la lecture du quatrième rejeu (tombée), les `mvn verify` par lot, la lecture des titres et consignes des entrées de catalogue (A.287 point 4), une quatrième lecture. Les stories de réserves ne sont pas chiffrées.
- **Ce qui n'a pas eu lieu** : aucun rejeu, aucune quatrième lecture, aucune troisième lecture au-delà de `-03` et `-08`. [corrige le 2026-10-29, texte d'origine conserve : une troisieme lecture de `-12` a eu lieu (lecture du diff de l'accord par `nadia-prof`, `DEC-0120` A.291 point 6) ; A.293 point 1 la compte, d'ou 24,20 et 0,80]
- Le prix d'une lecture après correction des quatorze stories est sans objet : aucune n'est engagée (A.286).

## 9. Durées brutes (remesure A.276)

Données brutes, sans conversion en barème, sans seuil (A.276 point 3). Source : l'horloge du poste lue par chaque agent, telle qu'il l'écrit.

| Poste | Durée | Statut de la donnée |
|---|---|---|
| Audits à l'écran de `-01`, `-02`, `-04`, `-05`, `-06`, `-10` | 31 min 24 s, 26 min 08 s, 19 min 46 s, 36 min 40 s, 29 min 20 s, 29 min 39 s | lues dans les fiches (`grep` du chiffre exact, une fiche chacune) |
| Audits à l'écran de `-03`, `-08`, `-12` | 27 min 30 s, 29 min 28 s, 28 min 36 s (31 min 04 s au total) | **rapportées par l'agent principal, absentes des fiches** (`grep` vide) [corrige le 2026-10-29, texte d'origine conserve : 28 min 36 s est la duree de `-12` seul jusqu'au commit, 31 min 04 s est son total ; la parenthese ne porte pas sur les trois audits (relecture de cloture, defaut 5, `planning.md` 09:56)] |
| Audit à l'écran de l'horloge | 16 min 19 s depuis le début du jeu | lue dans la fiche ; la lecture des textes avant le jeu n'est pas chronométrée |
| Troisième lecture de `-03` et `-08` | 1 min 14 s, 1 min 24 s, 2 min 25 s, soit 5 min 3 s [calcul] | lues dans le fichier de la relecture ; lecture et rédaction hors fenêtres, non mesurées |
| Premier jet de l'horloge (`lea-dev`) | 61 min 48 s jusqu'au premier commit | lue dans le rapport, attentes de `mvn` comprises |
| Correction de l'horloge (`lea-dev`) | non écrite | le rapport de correction ne porte aucune durée |
| Lecture du premier jet et seconde lecture de l'horloge (`nadia-prof`) | non mesurées | écrit dans les deux fichiers |
| Entrées de catalogue (`marc-dev`) | environ 5 min pour `bootstrap -am test` | c'est la durée d'un test, pas du travail |
| Lecture des titres (`nadia-prof`), orthographe (`relecteur-pedagogique`) | non écrites | aucun fichier ne porte de durée |

Les neuf durées d'audit sont celles de la section 2 de l'amendement du 2026-10-28 09:56 de `planning.md`, relues. Aucune conclusion n'en est tirée.

## 10. Porte technique : jobs de `ci.yml`, lancés et non lancés

Chaque job est nommé. **Je ne déduis aucun job que je n'ai pas lancé ni lu.** Rien ci-dessous n'est un résultat de GitHub.

- **`build`, étape "Verify"** : `bash outils/mvn-jdk21.sh -B clean verify`, **relayé** par l'agent lanceur à `eaa3ef65` : `BUILD SUCCESS`, 3644 tests, 0 échec. Confirmé en substance par un `verify` de `nadia-prof` à `371425e4`, même total (section 2). **Je ne l'ai pas lancé pour cette clôture.** Le code de `HEAD` est celui de `eaa3ef65` (section 1). [corrige le 2026-10-29, texte d'origine conserve : faux depuis `a3e05f13` (voir le marqueur de la section 1)]
- **`build`, étape "Package du fat jar"** et **"Vérifier la présence du build Maven"** : **non lancées par moi, non rapportées par l'agent lanceur.** Je ne les déduis pas de `verify`.
- **`prefixes`**, lancé par moi sur `HEAD` (`fa137660`), avant l'écriture de ce fichier, `077fae0b` tenant lieu de `github.event.before` :
  - `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) : exit 0 ;
  - `commit-atteignable.sh 077fae0b` : exit 0 ;
  - `git log --no-merges --format=%s 077fae0b..HEAD` : 160 sujets, passés à `verifier-prefixes.sh`, *"Tous les commits portent un préfixe d'agent connu."*, exit 0. À `eaa3ef65` la même plage rend 159 sujets, **égal au chiffre de l'agent lanceur** ; le 160e est le commit `48f45f36` ;
  - `verifier-garde-fou-accent.sh "077fae0b..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, exit 0.
- **`chemins`**, lancé par moi : `verifier-chemins-test.sh` exit 0 ; `git ls-files -- . ':!.github/scripts/verifier-chemins-test.sh'`, **3292 chemins**, passés à `verifier-chemins.sh` par l'entrée standard : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, exit 0. **Écart avec l'agent lanceur : il annonce 3290.** `git ls-tree -r --name-only` hors le test du garde-fou rend 3292 à `eaa3ef65` et à `HEAD`. Je n'explique pas les deux fichiers. [corrige le 2026-10-29, texte d'origine conserve : il n'y a pas d'ecart a relever. Le 3290 relaye par l'agent lanceur etait faux, c'est le compte de `e703bd1d` et `fd324d01` ; son journal lit 3293 fichiers suivis a `eaa3ef65`, ce que `git ls-tree -r --name-only eaa3ef65` confirme (3293, test du garde-fou compris, donc 3292 hors lui, mon chiffre). Mesure a `HEAD` (`7e911fad`) : 3294 avec le test du garde-fou, 3293 hors lui, comme `git ls-files` sur le worktree du lot (3294). Mon "3292 a `eaa3ef65` et a `HEAD`" etait exact pour `eaa3ef65` seul : a `HEAD` c'est 3293.] [corrige le 2026-10-29, texte d'origine conserve : le marqueur precedent ecrit que le 3292 etait exact pour `eaa3ef65` seul et que `HEAD` donne 3293. Le `HEAD` du texte d'origine est `fa137660` : `git ls-tree -r --name-only` rend 3293 avec le test du garde-fou et 3292 hors lui, a `eaa3ef65` comme a `fa137660`. Le 3293 hors garde est celui de `7e911fad`, posterieur (3294 avec le garde). Mon "3292 a `eaa3ef65` et a `HEAD`" etait exact aux deux arbres, voir `DEC-0120` A.289 (relecture de cloture, defaut 11)]
- **`outils`**, lancé par moi, balayage de `ci.yml` rejoué à la main : `outils/*-test.sh` 6, `squad/outils/*/*-test.sh` 2, `outils/*-test.py` 5, **13 juges purs, 0 rouge**, aucun motif vide, égal au chiffre de l'agent lanceur.
- **Gardes de documentation de ce fichier** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest -Dsurefire.failIfNoSpecifiedTests=false`, **`-am` présent**, périmètre `bootstrap` et ses amonts, arbre `fa137660` plus ce fichier et `planning.md` modifié, non commités : exit 0, `BUILD SUCCESS`, 18 tests (5, 3 et 10), 0 échec. C'est une mesure partielle, pas un `verify`. `verifier-chemins.sh` sur `git ls-files` (moins le test du garde-fou) plus ce fichier, 3293 chemins, entrée standard réelle : exit 0.

**Ce que cette section ne couvre pas.** `prefixes`, `chemins` et `outils` sont lancés sur `HEAD` sans ce fichier ni la modification de `planning.md` ; ces deux écrits sont des fichiers de `squad/` et passent ensuite par les gardes de la ligne ci-dessus. Le sujet de leur commit n'est vérifié qu'après son commit, dans le compte rendu. **La CI distante n'est lue par personne** (section 6, question 1).

## 11. ArchUnit

**Je n'ai pas lu de confirmation d'`ArchUnit` du palier 5** : `confirmation-archunit-palier-5.md` n'existe pas dans le dossier (`ls`), pas plus qu'une lecture de lignée de `theo-archi` pour ce palier. Les règles d'architecture sont dans le `verify` ; l'écrit qui les confirme n'existe pas. **L'écart est dit, pas comblé.** À `theo-archi`.

## 12. Obstacles du palier, état à la clôture

Repris dans `review.md` à la clôture du sprint. **Un obstacle n'est clos que quand le fichier qui le tranche existe.**

| Obstacle | Catégorie | Agent | Artefact attendu | État |
|---|---|---|---|---|
| En-tête de `ce1-calendrier-dates.yaml`, ligne 1 : *"DEGRADE : la lecture de l'heure sur un cadran n'est pas servie"*, faux depuis l'horloge ; premier texte du critère 11 de `S57-BR-MAT-CE1-11-HORLOGE` | redaction, contenu | développeur à désigner | une ligne de commentaire corrigée (A.288 point 7) | **ouvert** : développeur non désigné, aucun agent ne tranche, le fichier contredit la matrice |
| Levée de la réserve 2 de `-06` et de la réserve `d` de l'horloge | pédagogie | `nadia-prof` | ligne dans chaque fiche | **ouvert** : non écrite |
| Réserve `c` de l'horloge : une story ou non | produit | `camille-po`, par moi | `DEC` ou story | **ouvert** : routé ici, aucun écrit |
| Accord de *du plus âgé au plus jeune* en `-12` (items 3, 8, 11), entrée de `-12` | rédaction, produit | `relecteur-pedagogique` pour le relevé, `camille-po` pour la story | relevé, story engagée | **ouvert** : `S57-QLM-CE1-12-ACCORD` non engagée, aucun relevé du palier dans le dossier [corrige le 2026-10-29, texte d'origine conserve : accord fautif a deux enonces (3 et 8), corrige et lu ; entree de `-12` ecrite ; ligne `couvert-valide` (`DEC-0120` A.290 a A.292) : **resolu**] |
| Quatorze stories de réserves (`RAL-CE1-R1`, `DEL-CE1-R1`, `ETE-CE1-R1` à `R3`, `-03`, `-06`, `-10`, `V1`, etc.) | pédagogie, produit | `camille-po`, `nadia-prof`, `ines-design`, `theo-archi` selon la réserve | stories engagées | **ouvert, non engagé par décision** (A.286, `O2`) |
| `Q-273-1` (i) | produit | `nadia-prof`, utilisatrice | réponse | **ouvert** : seizième mention [corrige le 2026-10-29, texte d'origine conserve : vingt et unieme mention en `DEC-0120` A.294] |
| La clé de type n'est pas une clé de l'entrée de catalogue : elle est dans le descripteur du paquet, aucune entrée existante ne l'écrit (`marc-dev`, section 1) | technique | `theo-archi` | décision de schéma | **ouvert** : question de schéma, aucun écrit |
| Une exécution de la CI GitHub est-elle attendue ? | qualité, produit | utilisatrice | réponse | **ouvert** (section 6, Q1) |
| `confirmation-archunit-palier-5.md` absente | technique | `theo-archi` | confirmation | **ouvert** (section 11) [corrige le 2026-10-29, texte d'origine conserve : `confirmation-archunit-cloture-s57.md` (`theo-archi`, 2026-10-29) confirme les regles ArchUnit sur l'arbre du palier 5 : l'ecrit qui manquait existe, sous le nom de la cloture du sprint ; ce fichier-ci reste absent] |
| `JAR` : qui lance le `verify` de chaque lot, qui sert le jar à l'audit | capacité | moi | désignation écrite | **ouvert** : cette fois l'agent lanceur a lancé le `verify` et servi le jar, **rien n'est désigné par écrit** |
| `P6` : le drapeau `-Dsurefire.failIfNoSpecifiedTests=false` absent de `CLAUDE.md` et de `squad/README.md` | qualité | `yanis-qa` ; `theo-archi` si c'est une règle d'architecture | amendement ou refus écrit | **ouvert** : je ne choisis pas |
| `P7` : forme de lancement de `verifier-chemins.sh` et `verifier-prefixes.sh` (jamais `< /dev/null`) documentée là où un agent la lit | documentation | moi | amendement de `squad/README.md` | **ouvert, non fait** : hors du périmètre de ce geste |
| `P8` : distracteur *le mulot* de `cm1-sciences-chaine-alimentaire.yaml` (CM1) | pédagogie | `nadia-prof`, puis `camille-po` | avis | **ouvert** |
| `P9` : ordre de fusion de `-03` avant `-02` | technique | `theo-archi` | ADR ou écrit | **ouvert** |
| `P10` : faits du rapport de `marc-dev` absents du dépôt (`-04`, `-12`) | rédaction | `marc-dev`, par moi | rapport complété | **ouvert, reporté** |
| `P4` : intrus en tâche B de `-02` (`RAL-CE1-R1`), pendant pour `-10` (3 bonnes réponses sur 13 manches B, état de la fiche) | pédagogie, produit | `nadia-prof`, `camille-po` | story ou écrit | **ouvert** (voir ligne des réserves) |
| `T1` : anciennes amorces de `EFFETS_CONTRAIRES` gardées dans le test de `-03` | technique | `theo-archi` avec `marc-dev` | ADR ou correction | **ouvert** : état de l'amendement du 2026-10-28 09:56, non relu depuis |
| `C2`, `C4` : fusions sérialisées d'un fichier à ajouts en fin de liste (trois branches ont écrit la même ligne de compte du registre) | capacité | non désigné | règle écrite | **ouvert** |
| Remesure `A276` | capacité | moi | durées inscrites | **close** : section 9, données brutes sans seuil |
| Ligne *"Premier `verify` complet du lot"* des neuf fiches | rédaction | `camille-po` | ligne mise à jour | **close** : `grep` de *"fait au niveau du reacteur, pas par lot"* rend 1 sur chacune des neuf fiches (A.283) |
| Q3 d'A.271, `Q-283-1`, cliquet de poids, entrées de catalogue engagées | produit | `camille-po` | `DEC-0120` | **clos** : `A.286`, `A.284`, `A.287`, `A.288` existent sur le disque |
| `pid.txt` non suivi à la racine du checkout principal | capacité | inconnu | suppression ou explication | **ouvert** : origine inconnue, **non touché** ; lu dans l'état `git` du checkout principal, absent de ce worktree |
| Rapport de tokens | capacité | moi | `RAPPORT-tokens.md` | **à la clôture du sprint** |

## 13. Travers du palier, sans les enjoliver

- **Un `verify` annoncé et non consigné.** L'agent lanceur avait annoncé le `mvn -B clean verify` complet à `d398add4` (3644 tests) sans l'écrire dans le dépôt : `FICHE-ce1-lire-l-heure.md` le constate (*"n'est écrit dans aucun fichier du dépôt"*) et `nadia-prof` l'a relancé à `371425e4`, **doublon de durée** que la consignation aurait évité. Le chiffre est ensuite le même.
- **Deux agents ont échoué au premier lancement** : `camille-po` et `ines-design`, dont les worktrees automatiques partaient d'une base de juillet (fait **relayé** par l'agent lanceur, non vérifiable dans le dépôt). Règle de cette clôture : un worktree se crée à la main sur le `HEAD` de `round-two`, et la lignée se vérifie avant de fusionner (section 1).
- **Chiffres annoncés qui ne tiennent pas, relevés** : 3290 chemins annoncés contre 3292 lus (section 10) [corrige le 2026-10-29, texte d'origine conserve : pas d'ecart, le 3290 etait une erreur de relais de l'agent lanceur, voir la section 10 ; mesure : 3293 fichiers suivis a `eaa3ef65`, 3292 hors le test du garde-fou] ; 3188 `@Test` écrits par la clôture du palier 4 contre 3206 relus (section 2). [corrige le 2026-10-29, texte d'origine conserve : le second marqueur ci-dessus precise le premier, voir `DEC-0120` A.289 et la section 10]
