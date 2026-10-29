# [Sprint s57] Review

- **Horodatage de fin** : 2026-10-29 17:08 (UTC+01:00), lu par `date` à l'écriture. **Horodatage de début** : 2026-10-14 15:45 (UTC+02:00), `planning.md`, ligne 3. **Durée** [calcul] : 15 j 2 h 23 min depuis 2026-10-14 15:45 (UTC+02:00), nuits et attentes comprises. Ce n'est pas une durée de travail : le sprint n'a pas de timebox (`planning.md`, `D1`) et aucune horloge ne sépare les attentes. Durées calendaires des paliers, telles que les clôtures les écrivent : palier 1 25 h 52 min, palier 2 25 h 11 min, palier 3 43 h 3 min, palier 4 37 h 55 min, palier 5 5 j 9 h 47 min depuis le premier commit du palier (`cloture-palier-5.md`).
- **Écrit par** : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` à `f33993b3`, plus le commit `c72d15bf` de ce worktree (corrections de `cloture-palier-5.md` et de `planning.md`).
- **Pourquoi le sprint s'arrête** : par `D3` de `planning.md` (*"quand l'utilisatrice l'arrête"*), dont `DEC-0120` A.293 est le seul témoin écrit. Mots de l'utilisatrice du 2026-10-29, transmis par l'agent lanceur : *"clos s57"* ; *"Q1 02"* (plus tôt le même jour : corriger avant la clôture ce qui est sans ambiguïté) ; puis *"Q1 O1 et Q2 O1"* (le pli et la coupure de `-12`) et *"O2"* (ne pas fusionner la correction U+2011 de `-12`). **[corrige le 2026-10-29 : `DEC-0120` A.293 point 3 (`camille-po`) écrit que les mots de la clôture ne lui avaient pas été transmis ; l'agent lanceur les a transmis ensuite, le 2026-10-29, et ils sont cités ici avec leur date. Ils ne sont pas lus de la main de l'utilisatrice ; texte d'origine : *"forme 2"* sans mots]** Le palier 5 est clos par `cloture-palier-5.md`. Aucun palier 6 n'est ouvert.
- **Ce que ce fichier ne fait pas** : il ne prononce **aucun** Done. Le Done pédagogique est à `nadia-prof` seule. Le Done technique est celui de la CI, que personne n'a lue : ce fichier nomme les mesures locales et ne les appelle pas "CI verte".

## 1. Ce qui a été livré

**En une phrase** : `recreo` couvre 166 lignes de programme sur 202 à la clôture contre 142 sur 200 à l'ouverture, par vingt-neuf postes de jeu jugés chacun par un verdict écrit de `nadia-prof`, surtout au CE1 (français, mathématiques, EMC), et la page *Questionner le monde* du CE1 passe de zéro à neuf jeux.

| Palier | Postes engagés | Verdict final écrit par `nadia-prof` | Total de tests en fin de palier |
|---|---|---|---|
| 1 | `FRA-CE1-13`, `FRA-CE1-10`, `FRA-CE1-15`, `SCI-CM1-20`, `FRA-CE1-04`, `ART-CP-10` | tous **VALIDE** (cinq après la levée d'une réserve au palier 2, `SCI-CM1-20` au premier passage) | 2597 |
| 2 | `FRA-CE1-05`, `FRA-CE1-24` | **VALIDE** les deux (`FRA-CE1-05` refusé le 2026-10-16, rejoué) | 2669 |
| 3 | `FRA-CE1-06`, `FRA-CE1-07`, `FRA-CE1-11`, `MAT-CE1-01`, `EMC-CE1-01` | `FRA-CE1-07` et `MAT-CE1-01` **VALIDE** ; les trois autres **VALIDE SOUS RÉSERVE** | 2855 |
| 4 | `MAT-CE1-10`, `EMC-CE1-02`, `FRA-CE1-21`, `MAT-CE1-11` (calendrier), `EMC-CE1-03`, `EMC-CE1-04` | `MAT-CE1-10` **VALIDE** ; les cinq autres **VALIDE SOUS RÉSERVE**, après un à trois refus chacun | 3232 |
| 5 | neuf lots `QLM-CE1` (`-01`, `-02`, `-03`, `-04`, `-05`, `-06`, `-08`, `-10`, `-12`) et l'horloge `MAT-CE1-11` | **2 VALIDE** (`-01`, `-04`), **8 VALIDE SOUS RÉSERVE**, **0 refus** | 3644 |

Sources : `avis-nadia-cloture-s57.md`, section 1 (verdicts), `rapport-qualite-cloture-s57.md`, section 3.1 (totaux de tests, **mesures de leurs auteurs que `yanis-qa` n'a pas rejouées**, sauf le dernier). Le décompte des paliers 1 à 4 est celui des clôtures de palier. Le point de départ des tests, 2498, n'est pas confirmé comme total d'ouverture par `yanis-qa`.

**Palier 5, ce qu'un enfant de CE1 trouve à l'écran** (joué par `nadia-prof` le 2026-10-29, jar construit par le `verify` de `d9313f9e`) : neuf tuiles sur la page *Questionner le monde*, *L'heure sur l'horloge* en vingtième tuile de la page *Maths* ; chaque jeu se lance, se joue en 10 manches, dit *"Ce n'est pas ça. Essaie encore !"* avec un indice à la première erreur, nomme la bonne réponse quand l'enfant persiste, et se termine. Le catalogue passe de 163 à 173 entrées (`grep -c '^  - id:'`, lu à `f33993b3`).

### Couverture, remesurée par moi

Commande de la "Méthode du compte" de `MATRICE-couverture.md`, lancée telle quelle (périmètre : la matrice, rien d'autre) :

| Arbre | `couvert-valide` | `en cours` | `non couvert` | `non couvrable en l'état` | Total | Ratio |
|---|---|---|---|---|---|---|
| `f3b06989` (clôture du palier 4) | 156 | 7 | 39 | 75 | 277 | 156 / 202 = 77,23 % |
| `f33993b3` (clôture) | 166 | 6 | 30 | 75 | 277 | 166 / 202 = 82,18 % |

Dix lignes passent à `couvert-valide` pendant le palier 5 [calcul : 166 - 156] : les neuf lignes `QLM-CE1` et `MAT-CE1-11`. À l'ouverture, `DEC-0120` A.57 écrit 142 sur 200 : **chiffre relayé, non remesuré** (le dénominateur passe de 200 à 202 quand deux lignes deviennent atteignables, note de la matrice du 2026-10-21). **Ce ratio compte des lignes, il ne mesure pas la qualité d'un jeu** : huit des dix lignes du palier 5 y entrent sous réserve (`avis-nadia-cloture-s57.md`, section 3). Les 36 lignes qui restent (6 `en cours`, 30 `non couvert`) ne sont jugées par aucun fichier de cette review.

## 2. Rapport qualité de `yanis-qa`

Source : `rapport-qualite-cloture-s57.md`, arbre `a3e05f13`.

| Code | Constat | Ce que j'en retiens |
|---|---|---|
| F1 | `verify` complet à `a3e05f13` : 3644 tests, 0 échec, 0 erreur, 0 ignoré, JDK 21.0.10 | mesure locale, par `yanis-qa` |
| F2 | `chemins`, `prefixes`, `outils` lancés par lui, sorties 0 | mesures locales |
| F3 | étapes "Package du fat jar" et "Vérifier la présence du build Maven" non lancées | non jugées |
| F4 | la CI GitHub a été déclenchée par le push de `a3e05f13`, **lue par personne** | inconnue |
| F5 | tests : de 2498 à 3644 (+1146), dont +412 au palier 5 | recomptés par classe : 18 + 25 + 24 + 27 + 42 + 53 + 51 + 54 + 58 + 40 + 6 + 14 = 412 |
| F6 | deux écarts de comptage écrits, **non expliqués** : 3206 contre 3188 `@Test` (constant à 18), et l'écart constant de 26 entre `@Test` comptés et total mesuré | ouvert, section 10 |
| F7 | le code n'est plus celui de `eaa3ef65` : cinq fichiers de code ou de test diffèrent | `cloture-palier-5.md` porte un marqueur daté |
| F8 | aucune mutation lancée sur les gardes du palier 5, aucun rapport de mutation du palier 5 | ouvert, section 10 |
| F9 | obstacles de qualité routés à `sofia-sm` | repris section 10 |

**Remesuré par moi** : `@Test` comptés dans les sources à `f33993b3`, `git grep -c '^\s*@Test'` sur `*Test.java`, somme par fichier : **3618**, comme à `eaa3ef65` et à `a3e05f13`. Aucun fichier ne déclare `@ParameterizedTest`, `@TestFactory` ni `@RepeatedTest`. Le total du `verify` de la section 5 est mesuré à part ; je ne déduis pas l'un de l'autre.

## 3. Confirmation ArchUnit de `theo-archi`

Source : `confirmation-archunit-cloture-s57.md`, arbre `a3e05f13`, JDK 21.0.10.

- **Règles vertes** : 43 classes des répertoires `archi`, 286 tests, 0 échec, dont les 15 classes porteuses de règles (88 tests).
- **Compte de règles inchangé** pendant s57 : 41 déclarations, 38 `@ArchTest`, 16 fichiers, de `771b55de` à `a3e05f13`. Aucune règle ArchUnit ajoutée ni modifiée ; des gardes non ArchUnit sont ajoutés ou étendus, chacun avec son jeu fautif ou sa morsure (section 3 de son fichier).
- **`ADR`** : aucun créé, huit modifiés, statuts inchangés (liste dans `RAPPORT.md`, section 2).
- **Faux vert de mesure** (`DA2`) : la commande qui lui avait été transmise, `-Dtest='com.recreo.archi.*'` avec `-Dsurefire.failIfNoSpecifiedTests=false`, lance 0 test et rend `BUILD SUCCESS`. Il l'a mesurée (`M0`) et l'a remplacée par `-Dtest='**/archi/*Test'`. L'auteur de la commande transmise n'est pas écrit dans le dépôt ; voir la rétro.
- **Dix dettes d'architecture** ouvertes, chacune avec un propriétaire (`DA1` à `DA10`), reprises section 10.
- **L'obstacle de `cloture-palier-5.md` section 11** (`confirmation-archunit-palier-5.md` absente) : le fichier qui confirme les règles sur l'arbre du palier 5 existe, sous le nom de la clôture du sprint. Je le tiens pour clos, avec ce renvoi.

## 4. Corrections du `relecteur-pedagogique`

Source : `relecture-cloture-s57.md` (23 défauts, 4 points de fond à trancher) et `relecture-correction-03-item-11.md` (aucun défaut).

| Défauts | Propriétaire | État à cette review |
|---|---|---|
| 1, 2, 3, 4, 5, 11, 13, 19, 20, 21, 22, 23 | `sofia-sm` | **faits** par `c72d15bf` : marqueurs datés, coquilles de graphie, de titre et de fuseau, amendement de clôture de `planning.md` |
| 6, 7, 8, 9 (a), 10, 12, 14, 15, 16, 17, 18 | `camille-po` | faits dans `DEC-0120` A.293 point 9 (commits `[CAM]` du 2026-10-29) ; non vérifiés un à un par moi |
| 9 (b), ligne 25083 du backlog | `camille-po`, `nadia-prof` | **non faits** : A.293 point 9 nomme le premier comme non tranché (graphie *VALIDE SOUS RÉSERVE*), la seconde ne dit pas *"non couvert"* |
| 2 bis (en-tête de `ce1-calendrier-dates.yaml`) | développeur à désigner | **non fait**, section 10 |

Ce que la relecture de clôture a trouvé qui m'est imputable : le 3292 de `cloture-palier-5.md` que mon marqueur retirait à tort, et un cas haut à deux valeurs entre mes fichiers et `DEC-0120`. La relecture de la correction `29c11e3f` (`-03`, item 11) conclut *"Aucun defaut"*. La relecture de la correction `180889a4` (`-12`) relève 1 défaut, le renvoi `DEC-0120, Q2 O1`, résolu par A.293 point 3 ; cette correction n'est pas fusionnée.

## 5. Done technique

**Le Done technique est celui de la CI : tous les jobs de `ci.yml`, jugés par la CI.** Aucun texte de ce fichier ne dit qu'il est atteint.

| Mesure | Par | Arbre | Périmètre | Résultat |
|---|---|---|---|---|
| `bash outils/mvn-jdk21.sh -B clean verify`, racine | agent lanceur (relayé) | `d9313f9e` | job `build`, étape "Verify" | 3644 tests, 0 échec ; **seule mesure sur laquelle `DEC-0120` A.292 fonde le passage de `-12` à `couvert-valide`** |
| `verify` complet et jobs `prefixes`, `chemins`, `outils` | `yanis-qa` | `a3e05f13` | quatre jobs de `ci.yml`, étapes "Package du fat jar" et "Vérifier la présence du build Maven" exclues | `build` 3644 tests et sorties 0 pour les trois autres jobs |
| `verify` complet et jobs `prefixes`, `chemins`, `outils` | `sofia-sm` | `c72d15bf`, qui porte le code de `f33993b3` | voir `RAPPORT.md`, section 3 | voir `RAPPORT.md`, section 3 |

- **Le code a changé depuis `d9313f9e`** : un fichier de contenu, `ce1-qlm-besoins-de-la-plante.yaml` (`29c11e3f`, item 11 de `-03`), est fusionné après les mesures de l'agent lanceur et de `yanis-qa`. Le résultat de ma propre mesure sur l'arbre qui le porte est dans `RAPPORT.md`.
- **La CI GitHub** : déclenchée par les pushes (fait écrit par `yanis-qa` pour `a3e05f13`, relayé), **lue par personne**. Je ne l'ai pas lue et je ne sais pas quels SHA ont été poussés. Les fiches de `-03` et de `-10` conditionnent leur verdict à la CI complète à la clôture : *"si cette CI rougit sur ce lot, ce verdict est rouvert"*. La ligne `-10` et la ligne `-12` sont `couvert-valide` sur une mesure locale. Question à l'utilisatrice, `RAPPORT.md`, section 7.

## 6. Done pédagogique

**Seuls les verdicts de `nadia-prof` comptent, tels qu'écrits dans ses fiches.** Pour le palier 5 : dix lots, **2 VALIDE** (`QLM-CE1-01`, `QLM-CE1-04`), **8 VALIDE SOUS RÉSERVE** (`-02`, `-03`, `-05`, `-06`, `-08`, `-10`, `-12`, horloge), **0 refus** (`avis-nadia-cloture-s57.md`, section 1.2). Pour les paliers 1 à 4, ses verdicts sont ceux de la section 1.1 du même avis.

- **Ce qu'elle certifie** : ses verdicts, et que pour les dix lignes du palier 5 la compétence annoncée est celle que le jeu travaille et que les bonnes réponses et distracteurs sont exacts. **Ce qu'elle ne certifie pas** : les 166 lignes une par une, le rendu hors de 744 x 551 pour neuf des dix jeux, le Done technique, les réserves ouvertes comme "sans effet sur l'enfant" (non bloquantes, pas nulles).
- **Cet avis ne prononce aucun Done neuf, aucun refus, aucune levée de réserve.** Ses lectures du 2026-10-29 des deux corrections de contenu ne le font pas non plus : `-03` item 11 est *tenue*, ce n'est pas un Done ; `-12` est *tenue à 744 x 551, à refaire à 360 de large*, et cette correction n'est pas fusionnée.
- **Deux levées de réserve restent à écrire par elle dans les fiches** : la réserve 2 de `-06` et la réserve `d` de l'horloge (condition atteinte à l'écran, levée non écrite).
- **`V1` de `-12`** : mesurée non tenue sur le catalogue servi (744 : coupure dans *grands-parents* à l'item 8, réponse nommée sous le pli sur quatre items ; 360 : coupures sur six items), puis **partiellement tenue** sur l'arbre qui porte la correction non fusionnée (clause 1 tenue, clause 2 non tenue à 744). Elle n'est pas écrite dans la fiche. Comme la correction n'est pas fusionnée, **le catalogue sert l'état non tenu**.

## 7. Ce que `nadia-prof` a vu à l'écran, et le routage

Aucun constat n'est un refus ni ne rouvre un Done (`avis-nadia-cloture-s57.md`, section 4.3). Routage à l'état du dépôt à la clôture, relu dans `DEC-0120` A.293 et A.294.

| Code | Constat à 744 x 551 sauf mention | Routage et état |
|---|---|---|
| C1 | `-12`, item 8 après une erreur : ni croix, ni message, ni indice à l'écran | `S57-QLM-CE1-12-V1-MESURE`, **non engagée** (annotée) |
| C2 | `-12`, item 8 à l'arrivée : deux propositions coupent *grands-parents*. Correction par trait d'union insécable (`180889a4`) : coupure supprimée aux deux tailles, **débordement du bouton à 360 de large** (première lettre rognée avec une barre classique) | **`O2` de l'utilisatrice : correction non fusionnée.** `S57-QLM-CE1-12-TRAIT-D-UNION-INSECABLE` **non engagée** (A.294) ; branches `s57-cl-marc-12nb` et `s57-cl-rel-12nb` conservées |
| C3 | `-03`, item 11 : *"a-"* en fin de ligne, *"t-elle"* à la suivante | corrigé (`29c11e3f`), relu sans défaut, lu par `nadia-prof` : **tenu**, constat levé ; **fusionné** par `12bc666e`. `S57-BPL-CE1-11-COUPURE-A-T-ELLE` faite |
| C4 | horloge, à la révélation, sur les 12 items : la réponse nommée est coupée par le bas de l'écran, l'indice est dessous | `S57-HORLOGE-CADRAN-A-LA-REVELATION`, **non engagée** |
| C5 | à la révélation, **cinq jeux sur dix** (`-03`, `-05`, `-08`, `-12`, horloge) ont la réponse nommée sous le pli ; huit jeux ont l'indice dessous ; seuls `-02` et `-10` tiennent tout au-dessus | `S57-PLI-REVELATION-GABARIT-COMMUN`, **non engagée**, prérequis de la story du trait d'union |
| C6 | `-05` : `ETE-CE1-R1` et `ETE-CE1-R2` toujours vraies à l'écran | rien de plus à router |
| C7 | réserve 2 de `-06` et réserve `d` de l'horloge : condition atteinte à l'écran | levée à écrire par `nadia-prof` |

**Mesure que `nadia-prof` corrige d'elle-même** : la fiche de l'horloge écrit *"Rien de lisible n'est coupé"* ; elle n'avait pas mesuré l'état de révélation. Elle laisse les deux textes (horloge, `-05`) tels quels et le dit.

**Limites de la mesure à 360** : faite sur l'arbre non fusionné, avec une police (Segoe UI) et un navigateur sans tête. Android, iOS, macOS, Linux, un vrai téléphone et un lecteur d'écran ne sont pas vérifiés.

## 8. Budget

Source : `DEC-0120` A.293 point 1 et A.294 point 5. Plafond de `PAR-8` du palier 9, valeur de l'utilisatrice.

| Grandeur | Valeur | Calcul ou source |
|---|---|---|
| Plafond | 25,00 | A.282 |
| Cas haut en vigueur | **24,20** | [calcul] 17,70 + 4,50 + 1,00 + 0,50 + 0,50 (troisième lecture de `-12`) ; A.293 point 1 le tranche |
| Réserve de plafond | **0,80** | [calcul] 25,00 - 24,20, non affectée |

- **24,20 est un cas haut, pas une consommation.** Le coût réel n'est pas mesuré.
- **Coûts du jour non chiffrés** (A.293 point 2, A.294 point 5) : les deux lectures de `nadia-prof` du 2026-10-29 (quatrièmes lectures de `-03` et de `-12`, en un seul geste), la relecture rédactionnelle de la correction, les `verify` locaux, la correction de contenu, la construction du jar et le serveur de l'audit. Aucune règle lue ne les chiffre. Données brutes : serveur de `nadia-prof` ouvert de 11:39:39 à 12:09:52 pour les dix entrées, de 15:35:13 à 15:51:59 pour les deux corrections.
- **Pour mémoire, non retenu, pas un prix** : si les deux quatrièmes lectures étaient chiffrées comme une troisième, le cas haut serait 25,20 (0,20 au-dessus du plafond) ; avec une seule, 24,70 (A.293 point 2). **Au-delà de 25,00, A.273 point 1 (b) vaut** : le coût s'inscrit au registre et ne bloque le Done d'aucun lot. Fixer le prix d'une quatrième lecture est à l'utilisatrice ou à `nadia-prof`.
- **La consommation de tokens** est dans `RAPPORT-tokens.md`, produite par le script et non recopiée.

## 9. Écarté ou non engagé

| Objet | Statut | Source |
|---|---|---|
| Douze stories de réserves (`S57-RAL-CE1-R1`, `S57-BPL-CE1-1-REVISION`, `S57-ETE-CE1-R1`, `S57-ETE-CE1-R2`, `S57-ETE-CE1-R3`, `S57-QLM-CE1-06-LISTE-D-OBJETS`, `S57-DEL-CE1-R1`, `S57-QLM-CE1-10-ORDRE-DES-MOTS`, `S57-QLM-CE1-12-V1-MESURE`, `S57-QLM-CE1-01-DEFILEMENT-360`, `S57-MECA-BRAVO-SANS-POINT`, `S57-MECA-FIN-DE-PARTIE-BRAVO-ZERO`) | **non engagées, sans palier** : `O2` de l'utilisatrice à `Q-283-1` | `DEC-0120` A.286 ; deux des quatorze de A.283 sont faites (entrées de catalogue, accord de `-12`) |
| `S57-PLI-REVELATION-GABARIT-COMMUN`, `S57-HORLOGE-CADRAN-A-LA-REVELATION` | non engagées (`Q1 O1`) | A.293 point 6 |
| `S57-QLM-CE1-12-TRAIT-D-UNION-INSECABLE` | engagée en A.293 puis **non engagée** par `O2` ; ce n'est pas encore un report d'un sprint à l'autre | A.294 points 3 et 7 |
| Quatrième rejeu de l'horloge | **tombé** (*"O1 il tombe"*, réponse du 2026-10-28, `DEC-0120` A.286), aucun rejeu dû, la marge de 0,5 non consommée | A.286 point 3 |
| `LP7`, `LP8`, `LP9` (palier 4) | non engagés ; leur compte de reports est à écrire à l'ouverture suivante | `cloture-palier-4.md` section 5 ; état non relu ici |
| Réserve `c` de l'horloge (stratégie par forme des minutes, 10 items sur 12) | maintenue par `nadia-prof`, sans story | A.285 point 1 |
| Les 36 lignes `en cours` ou `non couvert` | hors de ce sprint | matrice |

Le compte des reports d'un sprint à l'autre pour les douze stories et pour `S57-QLM-CE1-12-V1-MESURE` s'écrit à l'ouverture du sprint suivant, avec s57 pour premier sprint (A.293 point 7). Je ne le fabrique pas ici.

## 10. Tableau des obstacles du sprint

Un obstacle n'est clos que quand le fichier qui le tranche existe. Catégories et agents : table de routage de `sofia-sm`.

| Obstacle | Catégorie | Agent | Artefact attendu | État |
|---|---|---|---|---|
| Accord de *du plus âgé au plus jeune* en `-12`, entrée de `-12` | rédaction, produit | `camille-po` | `DEC-0120` A.290 à A.292 | **résolu**, les trois amendements existent |
| Cas haut du coût à deux valeurs (23,70 et 24,20) | produit | `camille-po` | `DEC-0120` A.293 point 1 | **résolu**, 24,20 |
| Compte de fichiers du job `chemins` (3290 relayé) | qualité | `camille-po` | `DEC-0120` A.289 | **résolu**, 3293 avec le test du garde-fou |
| `Q3` d'A.271, `Q-283-1`, cliquet de poids, entrées de catalogue engagées | produit | `camille-po` | `DEC-0120` A.284, A.286 à A.288 | **résolus**, les amendements existent |
| `confirmation-archunit-palier-5.md` absente | technique | `theo-archi` | confirmation | **résolu** par `confirmation-archunit-cloture-s57.md` |
| Remesure `A276`, ligne *Premier verify complet du lot* | capacité, rédaction | `sofia-sm`, `camille-po` | durées inscrites, ligne mise à jour | **résolus** (`cloture-palier-5.md`) |
| Correction de `-03` item 11 (constat C3) | pédagogie, rédaction | `marc-dev`, `nadia-prof` | correction lue | **résolu** pour la lecture ; Done technique de ce SHA non lu par la CI |
| En-tête de `ce1-calendrier-dates.yaml`, ligne 1 : *"DEGRADE : la lecture de l'heure sur un cadran n'est pas servie"*, faux depuis l'horloge | rédaction | développeur à désigner | une ligne de commentaire (`relecture-cloture-s57.md`, annexe A, 2 bis) | **ouvert** : question à l'utilisatrice |
| Levée de la réserve 2 de `-06` et de la réserve `d` de l'horloge | pédagogie | `nadia-prof` | ligne dans chaque fiche | **ouvert** |
| Réserve `c` de l'horloge : une story ou non | produit | `camille-po` | `DEC` ou story | **ouvert** |
| `T1` (`DA6`) : deux entrées mortes de `EFFETS_CONTRAIRES` dans le test de `-03` | technique | `theo-archi`, `marc-dev` | correction ou `ADR` | **ouvert** |
| `P6` (`DA2`) : drapeau `-Dsurefire.failIfNoSpecifiedTests=false` absent de `CLAUDE.md` et de `squad/README.md` ; faux vert mesuré | qualité | `yanis-qa` | amendement ou refus écrit | **ouvert** |
| Clé `type` du catalogue (`DA1`) : 0 ligne `type:` sur 173 entrées, le type vit dans le paquet | technique | `theo-archi` | `ADR` ou refus écrit | **ouvert** |
| Écarts de comptage : 3206 contre 3188 `@Test` (constant à 18), écart constant de 26 non décomposé | qualité | `sofia-sm`, `yanis-qa` | explication écrite | **ouvert**, non expliqué |
| Gardes du palier 5 non mutées | qualité | `yanis-qa` | rapport de mutation | **ouvert** |
| `pid.txt` non suivi à la racine du checkout principal : contient un seul nombre (24347), modifié le 2026-10-27 21:57, aucun processus de ce PID ne tourne | capacité | inconnu | suppression ou explication | **ouvert** : origine non établie, **non touché** |
| `JAR` : qui lance le `verify` de chaque lot et sert le jar à l'audit | capacité | `sofia-sm` | désignation écrite | **ouvert** |
| `P7` : forme de lancement de `verifier-chemins.sh` et `verifier-prefixes.sh` (jamais `< /dev/null`) | documentation | `sofia-sm` | amendement de `squad/README.md` | **ouvert, non fait** |
| `P8` : distracteur *le mulot* de `cm1-sciences-chaine-alimentaire.yaml` | pédagogie | `nadia-prof`, puis `camille-po` | avis | **ouvert** |
| `P9` (`DA8`) : ordre de fusion de `-03` avant `-02` | technique | `theo-archi` | `ADR` ou écrit | **ouvert** : non instruit |
| `P10` : faits du rapport de `marc-dev` absents du dépôt (`-04`, `-12`) | rédaction | `marc-dev` | rapport complété | **ouvert** |
| `P4` : intrus en tâche B de `-02` (`RAL-CE1-R1`) | pédagogie, produit | `nadia-prof`, `camille-po` | story ou écrit | **ouvert** (story non engagée) |
| `C2`, `C4` : fusions sérialisées d'un fichier à ajouts en fin de liste (registre) | capacité | non désigné | règle écrite | **ouvert** |
| `DA3`, `DA4`, `DA5`, `DA7`, `DA9`, `DA10` de la confirmation ArchUnit | technique | `theo-archi`, `camille-po` | selon chaque ligne | **ouverts**, aucun payé |
| `TMPDIR` mixte et version de Python et de `node` du job `outils` | qualité | `theo-archi` ou `sofia-sm` | avis | état non relu à la clôture |
| `Q-273-1` (i) : un rejeu au-delà des trois compte-t-il contre le plafond de `PAR-8` du palier 9 | produit | `nadia-prof`, utilisatrice | réponse | **ouvert** : vingt et unième mention (A.294 point 7), la question n'a plus de cas nommé |
| Graphie *VALIDE SOUS RÉSERVE* contre *VALIDE SOUS RESERVE* (fiches et matrice) | rédaction | `nadia-prof`, `camille-po` | décision | **ouvert** (point à trancher 4 de la relecture) |
| Pli à la révélation, horloge, `V1` de `-12`, trait d'union insécable (constats C1, C2, C4, C5) | produit | `camille-po`, `ines-design`, `theo-archi` | stories engagées | **ouvert, non engagé par décision** (A.293, A.294) |
| Une exécution de la CI GitHub est-elle attendue, et qui la lit | qualité, produit | utilisatrice | réponse | **ouvert** |
| Sort des branches `s57-cl-marc-12nb` et `s57-cl-rel-12nb` | produit | utilisatrice | `DEC` | **ouvert** : conservées par A.294 |
| Rapport de tokens | capacité | `sofia-sm` | `RAPPORT-tokens.md` | **résolu** : `RAPPORT-tokens.md` du dossier, voir `RAPPORT.md` |

## 11. Avis de `nadia-prof` et état des deux Done

- **Avis de sprint** : `avis-nadia-cloture-s57.md` (dix entrées jouées le 2026-10-29) et `avis-nadia-corrections-cloture-s57.md` (lecture des deux corrections). Ils sont lus et résumés aux sections 6 et 7.
- **Done pédagogique** : celui que ses fiches écrivent, dix verdicts au palier 5, section 6. Je n'en prononce aucun.
- **Done technique** : non prononcé. Mesures locales, section 5 ; CI GitHub non lue.

## Decisions prises

**Aucune `ADR` ni `DEC` n'est créée ou amendée par ce fichier.** La liste complète des décisions que le sprint a créées ou modifiées (cinq `DEC` créées, `DEC-0121` à `DEC-0125` ; `DEC-0120` amendée de A.63 à A.294 ; deux `DEC` antérieures amendées, `DEC-0047` et `DEC-0062` ; huit `ADR` modifiées, aucune créée) est dans `RAPPORT.md`, section 2. Les décisions de l'utilisatrice du 2026-10-29 sont **relayées par l'agent lanceur** : corriger avant la clôture ce qui est sans ambiguïté, traiter les constats de pli en stories non engagées, ne pas fusionner la correction de `-12`. Mots de l'utilisatrice du 2026-10-29, transmis par l'agent lanceur : *"clos s57"* ; *"Q1 02"* (plus tôt le même jour : corriger avant la clôture ce qui est sans ambiguïté) ; puis *"Q1 O1 et Q2 O1"* (le pli et la coupure de `-12`) et *"O2"* (ne pas fusionner la correction U+2011 de `-12`). Elles sont citées par `camille-po` dans `DEC-0120` A.293 et A.294 ; je ne les ai pas lues de la main de l'utilisatrice.
