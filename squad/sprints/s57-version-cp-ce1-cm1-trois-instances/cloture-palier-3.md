# [Sprint s57] Cloture du palier 3

- **Statut : palier 3 clos. Le sprint s57 n'est pas clos.** Le palier 4 n'est pas ouvert (section 14). Ce fichier est ecrit une seule fois, comme `cloture-palier-1.md` et `cloture-palier-2.md` (`squad/README.md`, section 4). **Aucun Done n'est prononce par `sofia-sm`** : le pedagogique est a `nadia-prof` (section 1), le technique est juge par la CI, que personne n'a lue (section 12).
- **Horodatage de debut du palier** : 2026-10-19 23:09 (UTC+02:00), amendement d'ouverture de `planning.md`, *"inscrite avant l'ouverture de tout worktree du palier 3"* ; fusionne par `6b6d271a` a 23:12.
- **Horodatage de fin du palier** : 2026-10-21 18:12 (UTC+02:00), lu par `date` a l'ecriture. **Duree effective : 43 h 3 min**. Le palier 1 avait dure 25 h 52 min, le palier 2 25 h 11 min. **Le seuil d'information de 41 h 38 min (`P5'`, `D4` de `planning.md`) est franchi de 1 h 25 min** : il tombait le 2026-10-21 a 16:47. La condition d'alerte de `D4` (*"avec un poste engage non audite"*) n'etait pas remplie a cette heure : les cinq lots avaient un verdict ecrit, le dernier a 14:49 (`c17c3f9e`). Je le dis quand meme, ici, le jour meme.
- **Ecrit par** : `sofia-sm`, casquette Scrum Master. Arbre : `round-two` a `d09655bf` (confirmation par l'utilisatrice des mots relayes, `[CAM]`, 2026-10-21 17:36), checkout principal. Je n'ai fusionne aucune branche pour ce fichier ; la lignee des 21 fusions du palier est relue en section 3.
- **Ce que "clos" veut dire ici** : chaque poste engage a un verdict ecrit de `nadia-prof` ou un report ecrit (section 1) ; les totaux sont recomptes (sections 3 a 5) ; chaque job de `ci.yml` est nomme, lance ou non lance, jamais deduit (section 12) ; les 44 ecarts du releve de cloture sont traites ou routes avec leur destinataire (section 9). **Des decisions sont a l'utilisatrice et ne sont pas prises** (section 13). **Aucune ne conditionne un verdict** : `nadia-prof` ecrit qu'*"aucune reponse de l'utilisatrice n'est necessaire"* pour les Done de `-06`, `-11` et de l'EMC (`verdict-nadia-palier-3-ce1.md`, rectification, point 3).

## 1. Verdict de chaque poste engage

Les verdicts sont ceux de `nadia-prof`, relayes sans etre juges. Les postes hors `PAR` n'ont pas de verdict pedagogique : leur colonne dit ce qui les juge. **`R6` designe deux choses ce palier** : le releve de cloture du palier 2 (trois commentaires d'en-tete de paquet, poste `R6` de ce tableau) et le refus de saisie de `nadia-prof` (poste *saisie*) ; `theo-archi` signale un troisieme sens dans `ADR-0054` (`EP7`).

| Poste | Instance | Verdict de `nadia-prof`, ou etat | Fichier | Fusion du code, puis du verdict |
|---|---|---|---|---|
| p3-1, arbitrage prealable et lecture complete | A, A', B | livre ; une ligne *"lecture complete"* datee du 2026-10-20 par paquet (20, 20, 24, 22, 24 items) avant le premier verdict | `arbitrage-nadia-preparation-palier-3-ce1.md`, `reponses-nadia-questions-stories-palier-3.md`, `lecture-nadia-palier-3.md` | `b8fa7f9c`, `c3d1ebcf`, lecture `7301cbbc`, rejeu de lecture `014047a3` |
| p3-2, marges de rejeu | A, A', B | **non entamees a aucun poste** ; le rejeu de `-07` n'a pas de prix ecrit (section 6) | aucun | sans objet |
| p3-5, `-06`, `ce1-inference`, `FRA-CE1-06` | A | **VALIDE SOUS RESERVE** (2026-10-21) : reserves du pli deja ecrites (`V3`, `V4` et `V6`), plus une neuve, le mot a trait d'union coupe en fin de ligne (*"au-"* puis *"dessus"*), rectifiee comme non bloquante **sur sa seule autorite** | `verdict-nadia-palier-3-ce1.md`, `FICHE-bandit-manchot-inference-ce1.md` | lot `59d6f245` ; corrections `27a8a56a`, `78ab8a5b` ; verdict `9d892663` |
| p3-6, `-07`, `ce1-texte-documentaire`, `FRA-CE1-07` | A | **REFUSE** le 2026-10-21 (items 5, 9, 17 : se resolvent sans le texte, condition du jeu texte masque non tenue) ; correction `ac986aca` ; rejeu sur le fichier ; releve a l'ecran d'`ines-design` ; **VALIDE**, aucune reserve, 14:49 | `verdict-nadia-palier-3-ce1.md` (trois sections datees), `FICHE-bandit-manchot-texte-documentaire-ce1.md` | lot `a6b1bf1b` ; corrections `27a8a56a`, `afd33e88` ; releve `89f4afeb` (fusion `cb18ebfa`) ; verdicts `9d892663`, `4d924f2d`, `c17c3f9e` |
| p3-7, `-11`, `ce1-negation`, `FRA-CE1-11` | A | **VALIDE SOUS RESERVE** : `V3` (item 13 a 744, 18 a 720 avec barre) | `FICHE-bandit-manchot-negation-ce1.md` | lot `03acf639` ; correction `27a8a56a` ; verdict `9d892663` |
| p3-8, `MAT-CE1-01`, `ce1-nombres-jusqu-a-1000` | B | **VALIDE**, plafond `en cours` ; *decomposer* : 0 occurrence sur 516 ecrans ; mention de `NUM-CE1-2` dans la cellule de matrice | `FICHE-bandit-manchot-nombres-jusqu-a-1000-ce1.md` | lot `4912d211` ; correction `c7dab42b` ; verdict `9d892663` |
| p3-9, `EMC-CE1-01`, `ce1-emc-biens-communs` | A' | **VALIDE SOUS RESERVE** : `V3` (13 a 744, 21 a 720 avec barre), item 17 (la forme elimine), plus une neuve (*"Que fait-"* puis *"elle"*) | `FICHE-bandit-manchot-biens-communs-ce1.md` | lot `5d3d7a96` ; corrections `c7dab42b`, `bf402bc3` ; verdict `9d892663` |
| p3-3, `Z1` et `Z2`, garde de `C1'` | hors `PAR` | pas de verdict pedagogique. Garde ecrite par `theo-archi` (`ADR-0031` amendee), remesuree sur l'arbre fusionne : `T1` 2 echecs, `T2` 15 echecs, `T3` 2, `T4` 2 | `confirmation-archunit-palier-3.md` section 3 | `78b260e3` / `55328470` |
| pile de polices, `S57-GARDE-PILE-DE-POLICES` (`AD6`) | hors `PAR` | pas de verdict pedagogique. Garde (`ADR-0054` amendee deux fois) ; `T5` 3 echecs, `T6` 3, `T7` 0 comme l'`ADR` l'ecrit ; `ME8`, `ME9`, `ME10` de `yanis-qa` fermes par `19aa6c98` (non remesures par lui) | `confirmation-archunit-palier-3.md`, `rapport-qualite-palier-3-mutations-css.md` | `558dd887` / `4650aeab` ; `19aa6c98` / `d3fce602` |
| p3-4, `RL5` a `RL7` | hors `PAR` | livre le 2026-10-20 : **pas de second glissement**. Pas de verdict, **pas d'autre juge** : `RG5` prevoyait la relecture de `yanis-qa`, et, avant l'ecriture de ce fichier et des amendements de ce jour, `git grep -l 4bf3c359` sur le dossier ne rendait que `planning.md`, `preparation-palier-4-camille.md` et mon rapport | `rapport-sofia-dev-palier-3.md` sections 1 a 3 | `4bf3c359` / `20470501` |
| `R6` du releve, `S57-R6-COMMENTAIRES-DE-PAQUET` (`AD7`) | hors `PAR` | livre : trois commentaires d'en-tete. Pas de verdict. Critere 6 (relecture par `relecteur-pedagogique`) : aucun fichier ne l'atteste : avant l'ecriture de ce fichier et des amendements de ce jour, `git grep -l e6c371ad` sur le dossier ne rendait que `planning.md`, mon rapport et le releve de cloture, qui releve la meme famille d'ecart sur cinq autres paquets (ecarts 40 a 44) | `rapport-sofia-dev-palier-3.md` section 3 | `e6c371ad` / `91ecf180` |
| saisie sans correction, `S57-SAISIE-CLAVIER-SANS-CORRECTION` | hors `PAR`, hors composition | livree par `theo-archi` avec trois ecarts aux criteres de `camille-po` (`DEC-0120` A.121). **Le refus `R6` de `nadia-prof` est leve par l'arbitrage de l'utilisatrice** (verification sur tablette, 2026-10-21 16:12), avec une reserve neuve non bloquante sur les mots proposes au-dessus du clavier ; `FRA-CP-09` : Done de s26 maintenu sans rejeu ; l'ordinateur n'est pas mesure | `arbitrage-nadia-q05-q10.md` sections 7 et 8, `DEC-0124`, `FICHE-saisie-mot-dicte-cp.md` | `5a0b55f3` / `03de0f6d` ; arbitrage `b5dd1d4c` |
| correctif de gabarit du pli | hors `PAR` | veto de `nadia-prof` : `b383b0bb` **recevable seul** ; `edf7f344` (score a droite de la consigne) **REFUSE**, 22 jeux passent a deux lignes a cause du score. `edf7f344` n'est pas fusionne | `veto-nadia-gabarit-pli.md` | `b383b0bb` fusionne comme `5302af53` par `edb2ed8a` ; `edf7f344` non fusionne (section 13) |

**Ce que le verdict couvre, et ce qu'il ne couvre pas.** Chaque audit est **degrade, declare** : `nadia-prof` n'a pas lance le jar. Le jeu est celui du pilote d'`ines-design` (110 items sur 110 : arrivee, deux erreurs, revelation, bonne reponse), lu dans son texte DOM et sur 45 de ses 133 captures. Ce que cela ne montre pas, dit par elle : une police et un appareil reels, le geste d'un enfant, le lecteur d'ecran. Elle ecrit qu'aucune de ses conditions de Done ne l'exige.

**Ce qui est fusionne est ce qui a ete joue, a trois fichiers pres** : `git diff --stat 8407f73f d09655bf -- adapters domain application bootstrap pom.xml outils .github` rend exactement trois fichiers, `ce1-texte-documentaire.yaml` (le fichier de `-07` apres correction, juge a part : meme blob `5f3e6c95` a `ac986aca` et a `HEAD`), `fragments/saisie.html` et `EcranDeSaisieTest.java` (la saisie, `03de0f6d`). Les quatre autres paquets et le catalogue sont identiques (`git diff --quiet 8407f73f HEAD -- <quatre paquets et catalogue.yaml>` sort 0).

## 2. Glissements et reports

- **Premiers reports de `MAT-CE1-01` et d'`EMC-CE1-01` : soldes.** Retenus par la composition, engages a 23:44 le 2026-10-19 apres la fusion de l'amendement de 23:09 (`AD1`), donc premier report chacun (`DEC-0120` A.66, `planning.md` amendement de 23:44, section 3). Verdicts ecrits dans le palier : `MAT-CE1-01` **VALIDE**, `EMC-CE1-01` **VALIDE SOUS RESERVE**. Aucun second report ; l'engagement tardif n'a pas efface le premier (A.83).
- **`RL5` a `RL7` : livres, pas de second glissement.** Premier glissement ecrit au palier 2 (amendement du 2026-10-16 21:46, section 9). Livres le 2026-10-20 00:07 (`4bf3c359`), fusionnes a 00:11, avant tout commit de lot : la porte tient (`DEC-0120` A.117 ; relue par `git merge-base --is-ancestor` dans l'amendement de `rapport-sofia-dev-palier-3.md`, section 1).
- **`-06`, `-07`, `-11` : deuxieme ecartement solde par l'entree.** Premier ecartement : composition du palier 1 ; deuxieme : palier 2 ; entres au palier 3. **Pas de troisieme.**
- **`FRA-CE1-07` : refuse, puis valide.** Lecture du 2026-10-20 : *"a corriger"* sur 11 items, corriges avant le verdict. Verdict du 2026-10-21 13:46 : **REFUSE** sur les items 5, 9, 17 (motif ecrit, correction actionnable). Correction `ac986aca` (14:10), rejeu sur le fichier (14:15), releve a l'ecran d'`ines-design` (`89f4afeb`, 14:47, fusionne ensuite par `cb18ebfa` a 14:58), **VALIDE** a 14:49 (`c17c3f9e`). Le refus n'a pas rouvert le calcul de prix : *aucun prix de rejeu n'est ecrit* (section 6).
- **Trois stories hors `PAR` a leur deuxieme ecartement** : `S57-GARDE-NOMBRE-DE-MANCHES`, `S57-TYP1-TEXTES-DE-GABARIT`, `S57-MESSAGE-PARTIE-PERDUE` (`DEC-0120` A.118). **Je ne recalcule pas.** Un troisieme ecartement au palier 4 demande une `DEC` avec son motif.
- **`E5`, la regle pour une reponse relayee : troisieme palier ouvert** (`DEC-0120` A.122). Ce n'est pas un poste ; c'est un report que `camille-po` compte. Voir la section 13.
- **Engagements livres sans amendement de `planning.md`** : `S57-R6-COMMENTAIRES-DE-PAQUET`, `S57-GARDE-PILE-DE-POLICES`, `S57-SAISIE-CLAVIER-SANS-CORRECTION`. Ecart de ma main, dit en section 11 et annote dans `planning.md` ce jour.

## 3. Fusions et comptage du catalogue

Compte de depart sur `round-two` a `ee9ecd44` : 152. `git show <fusion>:adapters/adapter-content/src/main/resources/catalogue.yaml`, lignes `^  - id:`, lu **dans chaque commit de fusion** et dans son premier parent, pas sur l'arbre de travail. Sur les 21 fusions du palier (`git log --first-parent --merges 6b6d271a..HEAD`), le compte ne bouge qu'a cinq, celles des lots. **Il est de 157 a `HEAD`**, egal a ce que `composition-palier-3.md` section 5 annoncait.

| Fusion | Branche | Heure (2026-10-) | Avant | Entrees | Apres | Base de fusion, commits apportes |
|---|---|---|---|---|---|---|
| `20470501` | `s57-sofia-rl5-rl7` | 20 00:11 | 152 | 0 | 152 | `13e5d4c8`, 1 |
| `91ecf180` | `s57-sofia-r6` | 20 00:40 | 152 | 0 | 152 | `b9c6cb69`, 1 |
| `55328470` | `s57-theo-garde-c1-virgule-imbrique` | 20 08:28 | 152 | 0 | 152 | `b9c6cb69`, 1 |
| `59d6f245` | `s57-marc-fra-ce1-06` | 20 08:28 | 152 | 1 | **153** | `b9c6cb69`, 1 |
| `a6b1bf1b` | `s57-marc-fra-ce1-07` | 20 08:28 | 153 | 1 | **154** | `b9c6cb69`, 1 |
| `03acf639` | `s57-marc-fra-ce1-11` | 20 08:29 | 154 | 1 | **155** | `b9c6cb69`, 1 |
| `4912d211` | `s57-lea-mat-ce1-01` | 20 08:29 | 155 | 1 | **156** | `b9c6cb69`, 1 |
| `5d3d7a96` | `s57-lea-emc-ce1-01` | 20 08:29 | 156 | 1 | **157** | `d640121d`, 1 |
| `27a8a56a` | `s57-marc-corr-p3-fra` | 20 09:17 | 157 | 0 | 157 | `7301cbbc`, 3 |
| `c7dab42b` | `s57-lea-corr-p3` | 20 09:17 | 157 | 0 | 157 | `7301cbbc`, 2 |
| `4650aeab` | `s57-theo-garde-pile-de-polices` | 20 09:43 | 157 | 0 | 157 | `5ee20db7`, 1 |
| `46313f13` | `s57-ines-p3` | 21 09:26 | 157 | 0 | 157 | `4650aeab`, 1 |
| `9f8b84be` | `s57-yanis-mutations-css` | 21 09:26 | 157 | 0 | 157 | `558dd887`, 1 |
| `bf402bc3` | `s57-lea-corr2-p3` | 21 11:29 | 157 | 0 | 157 | `a0a69859`, 1 |
| `78ab8a5b` | `s57-marc-inf-il` | 21 11:30 | 157 | 0 | 157 | `a0a69859`, 1 |
| `d3fce602` | `s57-theo-garde-polices-r5-r6` | 21 11:30 | 157 | 0 | 157 | `a0a69859`, 1 |
| `edb2ed8a` | `s57-ines-p3-b383` | 21 12:43 | 157 | 0 | 157 | `859cf327`, 2 |
| `afd33e88` | `s57-marc-doc-p3` | 21 14:15 | 157 | 0 | 157 | `9d892663`, 1 |
| `4d39b47d` | `s57-ines-r1` | 21 14:15 | 157 | 0 | 157 | `8407f73f`, 1 |
| `cb18ebfa` | `s57-ines-r2` | 21 14:58 | 157 | 0 | 157 | `ea5d9429`, 1 |
| `03de0f6d` | `s57-theo-autocorrect` | 21 15:54 | 157 | 0 | 157 | `63d7a0f0`, 1 |

- **`grep -c "paquet: <nom>$"` sur `catalogue.yaml`**, lu a `ee9ecd44`, a la fusion du lot et a `HEAD` : `ce1-inference` 0, 1, 1 ; `ce1-texte-documentaire` 0, 1, 1 ; `ce1-negation` 0, 1, 1 ; `ce1-nombres-jusqu-a-1000` 0, 1, 1 ; `ce1-emc-biens-communs` 0, 1, 1. **Le paquet d'EMC est un fichier neuf** : il n'existait pas au depot avant son lot.
- **Lignee relue ce jour, pour les 21 fusions** : `git merge-base <premier parent> <second parent>` et `git rev-list --count <premier parent>..<second parent>` : la base est un sommet de `round-two` ou un commit de branche ancetre (colonne de droite), et chaque fusion apporte 1 a 3 commits, aucune ne rapporte un historique etranger. Je n'ai pas lance l'enveloppe `outils/verifier-lignee.sh` pour cette relecture : elle juge le worktree courant, pas une branche a fusionner. **Le rapport de developpeuse ne nomme ce script pour aucune des 21 fusions** : la lignee y est lue par `git merge-base` ou par la base citee dans les sections 7, 8, 11, 12, 14 et 16, mal ecrite en section 6 (section 11 ci-dessous), non ecrite pour les autres.
- **Conflits : quatre fusions sur 21** (`a6b1bf1b`, `03acf639`, `4912d211`, `5d3d7a96`), `catalogue.yaml` et l'inventaire de `TexteAccentueExposeParLePortEntrantTest`. `git diff-tree --cc` rend 63, 77, 94 et 111 lignes pour ces quatre, et 0 pour les dix-sept autres. Resolues par ajout des deux cotes, aucune assertion modifiee (`rapport-sofia-dev-palier-3.md` section 6 : zero ligne supprimee a chaque resolution).
- **A trois instances**, `camille-po` attend sept a huit conflits de cette sorte (`DEC-0120` A.130), chacun suivi d'un `verify` entier ; quatre des cinq fusions de lot de ce palier en ont eu un.

## 4. Total de tests

**2855 sur l'arbre fusionne, mesure par moi** : `bash outils/mvn-jdk21.sh -B clean verify`, racine, sans `-pl`, sur `03de0f6d` (`rapport-sofia-dev-palier-3.md` section 16) : `BUILD SUCCESS`, 12 min 11 s. Lignes `Tests run` du reacteur : domain 120, application 31, adapter-web 523, adapter-content 1794, bootstrap 381, integration 6 ; 0 echec, 0 erreur, 0 ignore. **Je ne l'ai pas rejoue pour ce fichier** (consigne : les fichiers de ce commit sont sous `squad/`). Ce qui le rattache a `HEAD` : `git diff --quiet 03de0f6d d09655bf -- adapters domain application bootstrap pom.xml outils .github` sort 0.

**Egal a la mesure de `theo-archi`**, faite sur une copie par `git archive` de `b52c4ac5`, reacteur complet, sans `-pl` : 2855, memes six chiffres de module, recompte par l'attribut `tests` des 319 `TEST-*.xml` (`confirmation-archunit-palier-3.md` section 1, `R1`, 12 min 36 s, JDK 21.0.10). Deux mesures independantes, deux arbres au code identique.

**Ecart avec 2669** (total de la cloture du palier 2) : **+186**, par module adapter-web +22 (501 a 523), adapter-content +164 (1630 a 1794), domain, application, bootstrap et integration 0. Chaine des totaux, chacun mesure par moi a la fusion ou au sommet indique :

| Fusion ou sommet | Ce qu'elle ajoute | Delta | Total |
|---|---|---|---|
| `efb8d013` (cloture du palier 2) | - | - | 2669 |
| `e6c371ad`, apres `RL5` a `RL7` et `R6` | commentaires seulement | 0 | 2669 |
| `5d3d7a96`, apres les six fusions du rang 1 a 6 | `Z1`/`Z2` 7, `-06` 32, `-07` 36, `-11` 31, `MAT-CE1-01` 30, `EMC-CE1-01` 34 | +170 | 2839 |
| `c7dab42b`, corrections de `marc-dev` et `lea-dev` | un test d'`EMC-CE1-01` (34 a 35) | +1 | 2840 |
| `4650aeab`, pile de polices | `LaPileDePolicesServieNeChangePasEnSilenceTest`, 8 | +8 | 2848 |
| `d3fce602`, garde de polices `R5` et `R6` | 4 tests du meme fichier | +4 | 2852 |
| `edb2ed8a`, mesuree a `ad9ef86d` | 2 tests de `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` | +2 | 2854 |
| `03de0f6d`, autocorrect | `EcranDeSaisieTest.leChampDemandeAuClavierDeNePasCorrigerLaFrappe` | +1 | 2855 |

0 + 170 + 1 + 8 + 4 + 2 + 1 = 186. Les fusions de contenu, de rapports et de releves ne changent aucun compte Maven (le total a `ad9ef86d`, `4d39b47d` et `cb18ebfa` est 2854 a chaque fois).

**D'ou vient l'ecart entre les chiffres annonces et le total.** `yanis-qa` ecrit **2834** et dit lui-meme que ce n'est pas le chiffre du sommet (`rapport-qualite-palier-3.md` section 1) : somme de l'attribut `tests` de rapports Surefire du checkout datant du 2026-10-20 09:17 a 09:19, avant les fusions de `4650aeab`, `d3fce602`, `edb2ed8a` et `03de0f6d`, et sans rapport Failsafe. **Il n'a pas reconfirme 2855 et ne l'explique pas** ; l'ecart de 21 s'explique par l'arithmetique des deux documents : 6 tests d'integration (Failsafe, absents de son comptage) + 15 tests d'adapter-web ajoutes depuis (8 + 4 + 2 + 1). Je n'ai pas remesure cet arbre-la : c'est une explication, pas une mesure. Les 164 tests de ses cinq classes (32, 36, 31, 30, 35) sont egaux aux +164 d'adapter-content. `theo-archi` annoncait 2847 sur une branche coupee sur `5ee20db7` : cet arbre ne contenait pas le test de `bb23e556` (2839 + 8). **Je ne relaie aucun de ces chiffres : je donne le mien.**

**ArchUnit.** Methode d'`ADR-0104`, mesuree par `theo-archi` a `bb322938`, `31fe0126` et `b52c4ac5` : **41 declarations, 38 `@ArchTest`, 16 fichiers**, ecart nul sur le palier ; regles reelles : 39 declarations dans 15 fichiers ; 15 classes porteuses, 88 tests, 0 echec. Aucune ligne ajoutee ne porte `@Disabled`, `@Ignore`, `allowEmptyShould(true)` ni `assume` ; **les 45 suppressions de `*.java` du palier ne sont pas lues une a une** (dit par lui). Je n'ai pas recompte : le code n'a pas change depuis `b52c4ac5`.

## 5. Matrice

Commande de la "Methode du compte" de `squad/programme/MATRICE-couverture.md`, lancee par moi sur `d09655bf`, sortie telle quelle :

```
cv 152
ec 5
nc 43
ncb 77
total 277
```

`couvert-valide` 152, `en cours` 5, `non couvert` 43, `non couvrable en l'etat` 77, total 277. Denominateur atteignable 277 - 77 = 200. **152 / 200 = 76,00 %.** Par niveau (lignes atteignables, `couvert-valide`) : CP 59 / 52, CE1 62 / 34, CM1 79 / 66, meme commande restreinte au niveau (`non couvrable` ecartees).

**Le calcul ligne par ligne**, par la meme commande lue dans la matrice a `ad65827e` (cloture du palier 2) puis a `HEAD`, comparaison du statut de chacune des 277 lignes : **exactement cinq lignes changent**, aucune autre.

| Ligne de matrice | Avant | Apres | Verdict |
|---|---|---|---|
| `FRA-CE1-06`, inference | `non couvert` | `couvert-valide` | `-06`, VALIDE SOUS RESERVE |
| `FRA-CE1-07`, texte documentaire | `non couvert` | `couvert-valide` | `-07`, VALIDE (apres refus) |
| `FRA-CE1-11`, negation | `non couvert` | `couvert-valide` | `-11`, VALIDE SOUS RESERVE |
| `EMC-CE1-01`, biens communs | `non couvert` | `couvert-valide` | VALIDE SOUS RESERVE |
| `MAT-CE1-01`, nombres jusqu'a 1000 | `non couvert` | `en cours` | VALIDE, plafond `en cours` |

148 + 4 = 152 `couvert-valide` ; 48 - 5 = 43 `non couvert` ; 4 + 1 = 5 `en cours` ; 77 et 277 inchanges ; CE1 30 + 4 = 34. Deux commits de `camille-po` portent le passage : `6842437b` (151 / 200 : trois fermetures et `MAT-CE1-01`) puis `cb4d81d0` (152 / 200 : `FRA-CE1-07`), `b52c4ac5` pour la levee de `R6` dans les cellules (aucun statut ne bouge). **La matrice est a `camille-po`** : je ne la modifie pas. Compteur de version (`DEC-0120` A.32) : 152 + 5 = 157 / 200 = 78,50 % **par la formule** ; son critere 2 (CI verte sur l'arbre de la version) n'est pas lu.

## 6. Consommation par instance, et tokens

Prix de `nadia-prof`. Registre commite par `camille-po` : `registre-prix-palier-3.txt` (`53dc13ff`, onze lignes). Les deux commandes `awk` du registre, rejouees ce jour (la premiere somme par instance, la seconde par etat) : `A 4.00`, `Ap 2.00`, `B 2.00`, **`total 8.00`** ; `facture 6.50`, `marge 1.50` ; cinq lignes `lot`.

| Instance | Composition | Facture | Detail | Marge | Non affecte |
|---|---|---|---|---|---|
| A (groupe X) | 4,00 | **3,50** | arbitrage 0,5 ; `-06`, `-07`, `-11` 1,0 chacun | 0,50 | 0 |
| A' | 2,00 | **1,50** | arbitrage 0,5 ; `EMC-CE1-01` 1,0 | 0,50 | 0 |
| B | 2,00 | **1,50** | arbitrage 0,5 ; `MAT-CE1-01` 1,0 | 0,50 | 0 |
| **total** | **8,00** | **6,50** | | **1,50** | **0** |

Bornes : somme 8,0 (`PAR-9`) ; plafond de 5,0 par instance (`PAR-8`), la plus chargee est A a 4,0 ; trois lots au plus par instance, A en porte trois (borne atteinte), A' et B un. **`nadia-prof` n'a ecrit aucun prix consomme** pour ce palier ; les cinq lots sont inscrits `facture` a leur prix de composition. **Le rejeu de `-07` n'a pas de prix** : au palier 2 un rejeu apres refus valait 0,5 (`DEC-0120` A.105). S'il vaut 0,5 ici, la marge de A est consommee, A passe a 4,00 facture et 0 de marge, le palier a 7,00 et 1,00 ; **le total ne change pas, le fichier se corrige d'une ligne, et la ligne est a `nadia-prof`** (`DEC-0120` A.119). Les marges de B et d'A' n'ont subi aucun rejeu. Les prix sont ceux de `nadia-prof` : je ne les juge pas.

**Rapport de tokens.** `python outils/rapport-tokens.py --sprint s57 --dry-run` fonctionne sur ce poste (`python`, pas `python3`) et rend, sans rien ecrire (`git status` propre apres), un tableau de dix lignes : les neuf agents et l'orchestrateur (fil principal), tours, input, output, cache cree, cache lu. **Sa fenetre est celle du sprint entier**, du 2026-10-14 13:45 UTC a l'instant du lancement, *"sprint en cours, fenetre ouverte"* : il ne separe ni le palier 3, ni les instances. Un `RAPPORT-tokens.md` ecrit aujourd'hui serait une fenetre partielle et mouvante presentee comme un rapport, et ses chiffres ne separeraient pas le palier. **Je n'ai pas ecrit `RAPPORT-tokens.md` et je n'ai commite aucun chiffre de tokens, ni recopie ceux du script.** Le rapport est un seul pour le sprint, a la review (`squad/README.md`, section 6). La consommation en prix de `nadia-prof` ci-dessus n'est pas une consommation de tokens.

## 7. Controle croise de `yanis-qa` : ce que la mutation a montre

Sur `b52c4ac5`, un worktree sans commit, 1 temoin vert (164 tests des cinq classes) puis 25 lancements pour **98 mutations valides : 96 rouges et 2 verts**. Restauration verifiee apres chaque lancement (`git status --short` vide). Deux invalidations dites et refaites : une salve jetee (un autre `bash` que celui du depot, Maven n'avait pas tourne, signal : sortie 1 avec 0 echec), et une mutation de la consigne du catalogue qui avait touche une autre entree. **Total au sommet non relu, aucun job de `ci.yml` lance** (section 4).

| Lot | Mutations | Rouges | Verts |
|---|---|---|---|
| `FRA-CE1-06` | 18 | 18 | 0 |
| `FRA-CE1-07` | 22 | 21 | 1 (`DO2`) |
| `FRA-CE1-11` | 20 | 19 | 1 (`NE5`, voulu) |
| `MAT-CE1-01` | 19 | 19 | 0 |
| `EMC-CE1-01` | 19 | 19 | 0 |

- **`DO2`, trou de garde, le seul** : le test `do2LesDeuxGenresSontMarquesEtRecomptesSurLeFichier` recompte les consignes sur le commentaire `# consigne` au-dessus de chaque item, et non sur l'enonce. Mutation : les sept enonces `faut-il` et `met-on` reecrits, marqueurs intacts : le recompte de la story passe de 9 a 2, sous le quart exige, **le test reste vert**. Verifie par moi a `d09655bf` : le recompte de la story (`grep -cE 'enonce: .*(faut-il|met-on)'`) rend **6** (9 au moment de la story : les corrections ont reecrit des enonces), le fichier porte 9 marqueurs de consigne et 11 de documentaire. **La story dit "enonce", le test dit "marqueur" : c'est a `camille-po` de reformuler le critere, puis a un developpeur de mettre le test d'accord.** `nadia-prof` est la garde de la propriete (elle a compte 11 documentaires et 9 consignes sur `ac986aca`). Aucune mutation n'a rougi un verdict.
- **`NE5`, vert voulu** : `Q-X2` est repondue, le test asserte les caracteres seuls ; la mutation prouve que la mesure en mots est retiree.
- **Non gardes, nommes par `yanis-qa`** (section 4 de son rapport) : les clauses de procedure `K1` a `K10` (se verifient par commande) ; la seconde moitie de `K4` et `EB8` (`bootstrap`, non mute) ; les audits `IN7` et `DO7` ; `IN3`, `NE3`, `EB2`, `EB5` limites ecrites par les stories ; **l'ecart `E3`, la garde generique de `LON-CE1-3` a `-5` et `TRV-CE1-1`, decidee par `theo-archi`, non livree** (`git ls-files | grep -c LesPropositionsEtLesTextesSupportsDeCe1` rend 0) : un paquet CE1 depose sans `PaquetCe1<Nom>Test` n'est juge par rien.
- **A `nadia-prof` seule** : exactitude des faits, `TRV-CE1-6` des lots qui ne le portent pas, `NRC-CE1-1`, `BCO-CE1-2`, le pli a 744 x 551, les items joues texte masque.

**Ce qui est route** : `DO2` a `camille-po` (critere) ; l'ecart `E3` a `camille-po` (story et moment, `DA1` de `theo-archi`) ; le reste est a `nadia-prof` (lecture) ; rien d'autre n'est engage pour garder ces proprietes. `yanis-qa` ne propose aucune correction et ne corrige rien.

## 8. Architecture : confirmation de `theo-archi`, dettes, ecarts de procedure

**Confirmation** (`confirmation-archunit-palier-3.md`, 2026-10-21 16:38) : regles vertes au sommet (`V1`), compte inchange 41 / 38 / 16 (`V2`), **les sept gardes du palier executables et mordants sur l'arbre fusionne, neuf mutations rejouees** (`V3`, section 3 : `GA1` a `GA7`, `T0` a `T9`), cinq dettes (`V4`), sept ecarts de procedure (`V5`). Aucune `ArchRule` neuve : chaque garde ecrit son motif (feuille de style, gabarit, fichier Markdown). `F1` et `F2` d'`ADR-0031` (propositions du palier 2) sont versees par le commit `78b260e3` (`P1`, `P2`, `Z4` dans son sujet).

**Dettes d'architecture, routees** (section 4 de son fichier) :

| Code | Dette | Destinataire |
|---|---|---|
| `DA1` | garde generique de `LON-CE1-3` a `-5` et `TRV-CE1-1` (`E3`), forme `F1` retenue, **non ecrite**, story non ecrite | `camille-po` (story, moment), puis un developpeur ; l'`ADR` dans le commit du test |
| `DA2` | les memes regles verifiees par paquet seulement : 24 fichiers de test les citent, 48 YAML portent `niveau: "CE1"` ; un paquet sans test les passe en silence | meme route que `DA1`, se ferme avec elle |
| `DA3` | six items dont la reponse attendue passe sous le pli a la revelation (744 x 551), huit a 720 avec barre ; aucun garde du reacteur ne mesure une cote de pli | `camille-po` (stories et levier) ; `nadia-prof` seule garde de la lecture |
| `DA4` | les mots proposes au-dessus du clavier : un attribut qui les supprime existe-t-il ? **Non instruite**, rien lu ce jour | `theo-archi`, reponse ecrite a `nadia-prof` |
| `DA5` | `MAT-CE1-14` conditionnee a `A3` d'`ADR-0080` : sa lecture (`A3` ne bloque pas un dessin carre a 352 px, marge mince a 6 x 6) n'est pas actee | `theo-archi` (amendement d'`ADR-0080`), `camille-po` (condition) |

**Ecarts de procedure vus par `theo-archi`** (section 5) : `EP1`, `round-two` rouge deux fois apres une fusion faite en le sachant : **de ma main**, section 11 ; `EP2`, trois allocations apres coup d'identifiants ecrits par `ines-design` : `ines-design`, la regle est celle d'`ADR-0085` ; `EP3`, mon controle de perimetre mal forme : **de ma main** ; `EP4`, 96 lignes de chemins de session dans `releve-ecran-ines-palier-3.md` : `ines-design` et `sofia-sm` (ecart 26 ; section 9) ; `EP5`, le sujet de `4650aeab` nomme mal sa classe : sans suite, un sujet ne se corrige pas ; `EP6` et `EP7`, **de sa main**, dits par lui.

**Ce que le releve de cloture ajoute a la garde** : le chemin `%TEMP%` d'une session passe `verifier-chemins.sh` (ecart 39, a `theo-archi`, section 9).

## 9. Releve du `relecteur-pedagogique` : 44 ecarts, traitement et destinataires

`releve-relecteur-cloture-palier-3.md` (2026-10-21 17:04, `01105a16`) : 44 ecarts, **36 corrections sans ambiguite et 8 points a trancher** (les numeros 8, 9, 10, 15, 17, 20, 37 et 39). Il ne cree aucun identifiant ; ses numeros de 1 a 44 sont les siens. **Etat a l'ecriture de ce fichier.** Les 8 ecarts qui sont a moi (numeros 1 a 8) : sept corriges par annotation datee, un a trancher et route. Les 36 autres sont routes avec leur destinataire : **je n'ai modifie aucun fichier d'un autre agent**.

**Ecarts de `sofia-sm`**, corriges par annotation datee (rien d'origine n'est reecrit) :

| No | Etat | Ou, et ce qui reste |
|---|---|---|
| 1 | **traite** | `rapport-sofia-dev-palier-3.md`, amendement du 2026-10-21 17:43, section 1 : les cinq branches contiennent `RL5` a `RL7` et pas `R6` (`git merge-base --is-ancestor`, six tetes) |
| 2 | **traite** | meme amendement, section 2 : deux casquettes dites, heure de `git log` de chaque section, perimetre et commande des totaux. Je ne dispose pas de l'heure lue par `date` au moment de chaque section et je ne la fabrique pas |
| 3 | **traite** | meme amendement, section 3 : une lettre de lecteur retiree de la lecture |
| 4 | **traite en partie** | meme amendement, section 4 : la colonne inexacte est corrigee ; le registre mixte est dit et non reecrit (je n'ai pas reecrit l'original) |
| 5 | **traite** | `composition-palier-3.md` section 13.1 et `planning.md`, amendement du 2026-10-21 17:46, section 1 : les deux citations separees de leur source |
| 6 | **traite** | `planning.md`, meme amendement, section 2 : les mots de la delegation du 2026-10-19 avec leur source ; **ils ne sont pas dans la liste confirmee a 17:36** (section 13) |
| 7 | **traite** | ce fichier, section 3, existe maintenant ; `composition-palier-3.md` section 13.2 le dit |
| 8 | **a trancher, route** | quatre noms pour le meme role (*"le lanceur"*, *"l'agent lanceur"*, *"l'agent qui m'a lance"*, *"le coordinateur"*) : a l'utilisatrice (section 13, point g) ; j'ecrirai la definition dans `squad/README.md`, section 2 bis, a la retro du sprint |

**Ecarts routes** (les 36 autres) :

| No | Etat et ce qui reste | Destinataire |
|---|---|---|
| 9 | a trancher. *"Relayee"* n'est pas une des trois formes de `DEC-0062` ; AD1, AD2, AD4, Q-05 a Q-10, Q-09, la levee de `R6` et la consigne du palier 4 reposent sur des mots recus par le fil. **Etat : les mots de la liste sont confirmes en forme 2 depuis 17:36** (`DEC-0120` A.132). Restent : `E5` pour tout le reste, la delegation du 2026-10-19, le canal du relais (section 13, point h) | `camille-po` (`E5`), utilisatrice |
| 10 | a trancher. La reserve sur les mots proposes au clavier est citee *"mot pour mot"* avec une formulation qui n'est pas celle de sa source : retirer les guillemets ou rapporter en discours indirect tant que `nadia-prof` n'a pas dit quel fichier gouverne (ecart 17) | `camille-po` |
| 11 | correction : `DEC-0123` ligne 8 et `DEC-index.md` disent `Q-09` suspendue ; elle est close | `camille-po` |
| 12 | correction : `DEC-0124` et `DEC-index.md` disent *"non appliquee"*, faux depuis `5a0b55f3` | `camille-po` |
| 13 | correction : amendement de la story `S57-SAISIE-CLAVIER-SANS-CORRECTION` (criteres 1 et 3 a reecrire, critere 4 (a) tenu) | `camille-po` |
| 14 | correction : chronologie inversee dans `DEC-index.md` ligne 53 | `camille-po` |
| 15 | a trancher : la citation MDN relayee (`DEC-0124`) est une formulation parmi trois (avec ecart 37) ; la dire en forme 3, citee telle quelle | `camille-po` |
| 16 | correction : numeros de ligne de `saisie.html` perimes ; nommer l'element et le SHA | `camille-po` |
| 17 | a trancher : deux formulations de la meme reserve dans `arbitrage-nadia-q05-q10.md` et `FICHE-saisie-mot-dicte-cp.md` ; dire quel fichier gouverne | `nadia-prof` |
| 18 | correction : le haut de `verdict-nadia-palier-3-ce1.md` (resume, 3.2, routage) dit encore `-07` refuse ; ajouter *"Etat final : `-07` VALIDE"* en tete | `nadia-prof` |
| 19 | correction : accents perdus dans la citation de `competences-ce1.md` de `FICHE-bandit-manchot-negation-ce1.md` | `nadia-prof` |
| 20 | a trancher : texte servi a l'enfant cite sans accents dans trois fichiers, la citation ne retrouve pas le YAML par `grep` ; fixer la convention de citation | `nadia-prof` |
| 21 | correction : *"le coordinateur"* a remplacer par la forme 1 (`sofia-sm`, `rapport-sofia-dev-palier-3.md` section 13, jobs et 2854 tests) | `nadia-prof` |
| 22 | correction : `afd33e88` est la fusion, la correction est `ac986aca` | `nadia-prof` |
| 23 | correction : *"mots de l'utilisatrice"* sans ses mots ni sa date dans `lecture-nadia-palier-3.md` ; renvoyer a la rectification du verdict | `nadia-prof` |
| 24 | correction : une phrase composee entre guillemets, des guillemets imbriques, une coupure sans marque, dans `arbitrage-nadia-q05-q10.md` | `nadia-prof` |
| 25 | correction : numeros de ligne de matrice et de `saisie.html` perimes | `nadia-prof` |
| 26 | correction : un chemin de session du poste sous le repertoire temporaire, repete 169 fois sur 96 lignes dans `releve-ecran-ines-palier-3.md` ; ne citer que le nom du fichier de capture et dire une fois que le dossier est hors depot | `ines-design` |
| 27 | correction : *"le coordinateur"*, meme remplacement que 21 | `ines-design` |
| 28 | correction : `afd33e88` pour `ac986aca`, meme que 22 | `ines-design` |
| 29 | correction : retirer *"le texte dit"*, aucun indice de `-07` ne le dit | `ines-design` |
| 30 | correction : *"avant"* et *"apres"* prennent deux sens pour le meme fait dans deux tableaux | `ines-design` |
| 31 | correction : `PL-9` et `PL-10` sont alloues depuis `21e994a4` | `ines-design` |
| 32 | correction : deux cotes du bas du signal different de 4 px (D2 contre C4) pour trois paquets ; harmoniser ou dire pourquoi | `ines-design` |
| 33 | correction : un commentaire de `recreo.css` (ajoute par `5302af53`) dit faux pour `ce1-inference` item 14. C'est un fichier de `src/main` : **un commit de developpeur a engager par moi a la composition du palier 4**, auteure `ines-design` | `ines-design` ; engagement `sofia-sm` |
| 34 | correction : numeros de ligne du backlog et de la matrice perimes dans `reponses-theo` et `avis-theo` ; citer titre et SHA | `theo-archi` |
| 35 | correction : le script de mesure de `E3` est hors depot, les chiffres ne se rejouent pas ; le commiter sous `squad/outils/` ou ecrire l'algorithme | `theo-archi` |
| 36 | correction : `ADR-0058` compte quatre amendements precedents, il y en a trois | `theo-archi` |
| 37 | a trancher : trois formulations de la meme page MDN ; dire si la lecture est directe (forme 1) ou relayee (forme 3) | `theo-archi` |
| 38 | correction : citer `DEC-0124` section 0 au lieu de recopier une citation sans son code ; le Javadoc du test attribue une decision sans ses mots | `theo-archi` |
| 39 | a trancher : `verifier-chemins.sh` laisse passer la forme `%TEMP%` d'une session (ecart 26) ; etendre le motif ou ecrire que la forme est toleree, et dans ce cas l'ajouter aux conventions de `CLAUDE.md`. **C'est un garde-fou de la CI : je ne le touche pas** | `theo-archi` |
| 40 | correction : `ce1-inference.yaml` lignes 13 a 14 dit *"attend l'audit"* (faux, VALIDE SOUS RESERVE) et lignes 17 a 18 enumere mal les sorties | `marc-dev` ; engagement `sofia-sm` |
| 41 | correction : `ce1-texte-documentaire.yaml` lignes 13 a 15, meme motif (VALIDE) | `marc-dev` ; engagement `sofia-sm` |
| 42 | correction : `ce1-negation.yaml` ligne 1 renvoie a un repertoire, la fiche existe | `marc-dev` ; engagement `sofia-sm` |
| 43 | correction : `ce1-emc-biens-communs.yaml` lignes 19 a 21, une bonne reponse ne nomme qu'un exemple | `lea-dev` ; engagement `sofia-sm` |
| 44 | correction facultative : pas de point final sur `competence` de `ce1-nombres-jusqu-a-1000.yaml` | `lea-dev` ; engagement `sofia-sm` |

**Les cinq ecarts 40 a 44 sont des fichiers de `src/main`** : un commit de developpeur, que je ne fais pas sous cette casquette (une seule casquette par tache) et que je n'engage pas ici. **Ils sont a engager a la composition du palier 4, avec l'ecart 33, comme `R6` au palier 2, avant tout jar rejoue sur ces paquets**, sous une story de `camille-po` (aucune n'existe). Le releve n'applique aucune correction : sa consigne lui interdit de modifier les fichiers des autres agents.

## 10. Isolation

**Ma mesure** (consigne de `composition-palier-3.md` section 6 : *"je mesure a la cloture du palier 3 les racines que ma session peut lire"*) : une mesure, un instantane, pas une garde. Premier listing le 2026-10-21 vers 17:34 (heure non lue par `date` a ce moment), puis relu a 18:05 (derniere ligne de cette section).

- **Racine du scratchpad de la session** : 23 repertoires et **69 fichiers** (`find -maxdepth 1`). Au palier 2 : 16 repertoires, **0 fichier**. Les 69 fichiers sont des journaux de build et de mutation, des scripts de mesure (`.py`, `.sh`) et un fichier de sauvegarde, dates du 2026-10-19 21:04 au 2026-10-21 16:51. **Aucun rapport du palier ne declare ces ecritures** ; je ne sais pas les attribuer tous : `verify-k7.log` et `package-k7.log` portent le nom de mon worktree de reference (section 7 de mon rapport), les journaux `mut-*.log` sont ceux de mesures de mutation (`theo-archi` ou `yanis-qa`, sans aucune declaration de racine). La consigne de `composition-palier-3.md` section 6, point 4 (*"la racine du scratchpad"* est interdite) **n'a pas tenu**.
- **Repertoires de la table du palier 3** : 6 sur 12 lignes existent : `s57-p3-marc-dev-a`, `s57-p3-lea-dev-b`, `s57-p3-lea-dev-ap`, `s57-p3-sofia-dev-t`, `s57-p3-sofia-sm-t`, `s57-p3-theo-archi-t`. **Absents** : les trois de `nadia-prof`, ceux de `yanis-qa`, `camille-po` et `relecteur-pedagogique`. **Seize repertoires hors table** : `theo-p3`, `theo-ce1-palier3`, `camille-po`, `camille-po-palier-3`, `p3`, `p4`, `p5`, `p6`, `ines-pli`, `r1`, `r1-releve`, `r2`, `camille-p3`, `arbre`, `theo-p3-cloture`, `yanis-p3` ; plus `s57-p4-camille-po-t`, cree a 17:31 pour le palier 4, qui n'est dans aucune table.
- **`ines-design`** : aucune ligne dans la table (`composition-palier-3.md` section 6 : *"Un lancement qu'elle recevrait recoit une ligne ajoutee par amendement date avant son lancement"*). **Aucun amendement n'existe** : ses rapports declarent des ports 18671, 18681, 18691, 18692, 18701, 18711 et 18721 (et 19xxx), qui ne sont dans aucune table. **C'est mon ecart** (section 11).
- **Repertoire parent des worktrees** : `find -newermt "2026-10-19 23:00"` rend trois entrees : `recreo-ines-p3-pli` (10:05), `recreo-ines-integ-pli` (11:59), et un repertoire `ctxharness` (2026-10-20 16:16) qui n'est ni un worktree de `recreo` ni nomme par aucun fichier du sprint : **je ne sais pas qui l'a cree**. Aucun fichier d'agent de s57 a la racine de ce parent. `git worktree list` rend **4 lignes** (checkout, deux worktrees d'`ines-design`, `main`) contre 94 lignes qui portaient `s57` au palier 2 ; `git branch --list 's57-*'` rend une branche, `s57-ines-p3-pli`. **Qui a nettoye les autres n'est ecrit nulle part.**
- **Ce que je n'ai pas mesure** : le repertoire temporaire du systeme hors scratchpad ; ce que les processus ont ecrit sans passer par un outil du depot.

**Ce qui a tenu**

- **La lignee** : 21 fusions sur 21 apportent 1 a 3 commits sur une base recente (section 3). Aucune fusion n'a affaibli une assertion.
- **Les portes jugees sur arbre propre** : celles des sections 7 a 16 de mon rapport le sont sur des worktrees detaches a `git status` vide, avant et apres. Ce choix a permis d'expliquer le premier rouge du palier : un fichier non suivi d'un autre agent dans l'arbre principal (`mesure-ines-palier-3-pli-744x551.md`), que le `verify` de l'arbre propre ne voyait pas.
- **Les worktrees et branches de lot supprimes apres fusion** : `git branch --list 's57-*'` ne rend plus que `s57-ines-p3-pli`.
- **Les arrets par PID** : declares par `ines-design` (`D7` de sa mesure : *"tous arretes par PID, aucun processus arrete par nom d'image"*).
- **Rien n'a ete pousse** : aucune CI distante n'a vu un seul des deux sommets rouges.

**Ce qui a casse**

1. **Worktrees de lot a trois par agent.** Enonce du message de lancement de cette tache, sans source dans le depot (forme 3 de `DEC-0062`, ne vaut pas motif). Ce que le depot montre : `RG4` (*"un developpeur n'entrelace pas ses worktrees : un a la fois"*, `composition-palier-3.md` 4.1) et l'ordre `-06`, `-07`, `-11` de `marc-dev` ; les trois tetes de `marc-dev` sont commitees a 08:05, 08:07 et 08:09 le 2026-10-20, **sur le meme sommet `b9c6cb69`** ; celles de `lea-dev` a 08:06 (`b9c6cb69`) et 08:21 (`d640121d`). Le rapport de developpeuse (section 3) lit deux worktrees de lot ouverts a 00:14 seulement. **Les heures de creation des worktrees ne sont dans aucun fichier.**
2. **Worktrees detaches** : bons pour juger un arbre (section 7 de mon rapport), mais **conserves apres usage** : `recreo-k7` avait ete garde (*"conserve, non supprime"*) ; il n'est plus dans `git worktree list` et personne n'a ecrit sa suppression ; `recreo-ines-integ-pli` est detache et jetable, declare tel par son auteure, et existe encore. **Aucun de ces worktrees n'est dans la table.**
3. **`--force`.** Enonce du message de lancement, sans source dans le depot : `git grep -n -w force` sur les fichiers de palier 3 du dossier (mon rapport, les rapports de `yanis-qa` et de `theo-archi`, les mesures et releves d'`ines-design`) ne rend que des lignes qui disent le contraire : *"sans rebase, reset, force ni push"* (mon rapport, cinq lignes), *"sans `--force`"* (`yanis-qa`, un `git worktree remove`), et une phrase d'`ines-design` sur une erreur qu'elle n'a pas provoquee a dessein. **Je ne confirme ni n'infirme** : un `--force` ecrit nulle part n'est pas lisible.
4. **Deux sommets de `round-two` rouges apres une fusion** (`46313f13` a `21e994a4`, 12 minutes ; `edb2ed8a` a `ad9ef86d`, 13 minutes) : `leDepotReelEstCoherent` en echec parce qu'`ines-design` avait ecrit des identifiants non alloues. **Fusions faites en le sachant, de ma main** (section 11). Rien n'a ete pousse.
5. **Contention de build.** Chiffres du message de lancement, cites par `DEC-0120` A.130 comme *"relayes"* : un `verify` complet a 7 h 26 au mur sous quatre agents. **Aucun fichier du depot ne porte ce chiffre.** Ce que le depot porte : mes `verify` sur arbre propre, 4 min 57 s a la cloture du palier 2, puis 6 min 23 s (2026-10-20 09:43), 11 min 51 s, 12 min 39 s, 11 min 56 s, 11 min 17 s, 11 min 55 s, 12 min 11 s ; celui de `theo-archi`, 12 min 36 s. **Le temps d'un `verify` a plus que double entre la cloture du palier 2 et la fin du palier 3** (4 min 57 s contre 11 a 12 min) ; je n'ai pas mesure combien d'agents compilaient en meme temps.
6. **Arrets du watchdog** de `marc-dev` et de `lea-dev` : enonce du message de lancement, cite par `DEC-0120` A.130. **Aucun fichier du depot ne dit quand, combien, ni ce que la branche portait.** C'est le point que `A10` de la retro de s56 demandait d'ecrire dans le depot (*"qui, quand, ce que chaque branche portait"*), et qui ne l'est toujours pas (`DEC-0116` A.36, point 3). Les cinq lots sont livres : je ne peux pas dire si un arret a coute un lot.
7. **Un checkout partage ecrit par plusieurs agents.** Le premier `verify` du palier a ete rouge pour un fichier non suivi d'`ines-design` (section 7 du rapport) ; un fichier de `nadia-prof` est apparu modifie et non commite pendant une fusion (section 12) ; **pendant l'ecriture de ce fichier (17:36), cinq fichiers de `squad/decisions/` etaient modifies et non commites par `camille-po`**, et son commit est arrive quelques minutes apres. Un test de `bootstrap` qui lit `squad/` lit aussi les fichiers non suivis des autres.
8. **Un chemin de session du poste commite 169 fois** (ecart 26) ; `verifier-chemins.sh` ne le voit pas (ecart 39).
9. **Les rapports de developpeur** : `K7` (`rapport-<developpeur>-palier-3.md`, avec les jobs lances et, par paquet, la ligne `TRV-CE1-5`) n'existe que pour moi. `ls squad/sprints/s57-*/rapport-*palier-3.md` rend deux fichiers : `rapport-qualite-palier-3.md` (`yanis-qa`) et le mien. **Aucun rapport de `marc-dev` ni de `lea-dev` pour ce palier**, donc aucune declaration de racine ni de jobs lances de leur part. Je ne l'ai pas reclame avant la cloture (section 11).

**Lecture des transcriptions par `nadia-prof`** : la lecture de l'isolation du palier 2 n'est dans aucun fichier du depot (`reponses-nadia-questions-stories-palier-3.md` 7.1 : *"Reste ouvert, a moi"*), et il n'y en a pas pour le palier 3. **Ouvert, a `nadia-prof`.** Je ne m'y substitue pas : les racines des autres sessions ne sont lisibles que dans les transcriptions.

**Dernier listing** (relu avant le commit de ce fichier) : deux listings, vers 17:34 puis a 18:05 le 2026-10-21, rendent le meme compte : **23 repertoires et 69 fichiers** a la racine du scratchpad, aucun fichier ajoute entre les deux ; mes propres ecritures de cette tache (journaux de `mvn`, copie de controle, repertoire temporaire) sont dans `s57-p3-sofia-sm-t`, ligne de la table. `git worktree list` : 4 lignes ; `git branch --list 's57-*'` : 1 ; le parent des worktrees : les memes trois entrees recentes qu'en debut de section.

## 11. Erreurs du palier, ecrites comme telles

**Les miennes d'abord.**

1. **Le sommet de `round-two` rouge deux fois, et je le savais.** Le `[THEO]` de `21e994a4` et celui d'`ad9ef86d` sont les deux allocations d'identifiants que `ines-design` avait ecrits sans les allouer (`EP2`, `ADR-0085`). J'ai fusionne `s57-ines-p3` et `s57-yanis-mutations-css` (`46313f13`, `9f8b84be`, 09:26) puis `s57-ines-p3-b383` (`edb2ed8a`, 12:43) **en ecrivant que le garde etait rouge *"attendu"*** (`rapport-sofia-dev-palier-3.md` sections 9 et 12), au lieu de demander l'allocation a `theo-archi` avant de fusionner. Resultat : `leDepotReelEstCoherent` rouge au sommet de `round-two` pendant 12 puis 13 minutes (`git log` : 09:26 a 09:38, 12:43 a 12:56). Rien n'a ete pousse, aucune CI ne l'a vu. **La regle que j'ai suivie etait *"router, fusionner, attendre la decision"* ; la bonne etait *"attendre l'allocation, puis fusionner"*.** `theo-archi` le nomme `EP1` et dit *"en le sachant"* : c'est exact.
2. **La verification de perimetre mal formee.** Avant les fusions de `s57-ines-p3` et `s57-yanis-mutations-css`, ma commande de controle n'a rien liste ; je l'ai refaite apres la fusion (`git diff --name-only 132ac960 9f8b84be`). Le resultat est le meme, **l'ordre prescrit n'a pas ete tenu** (`rapport-sofia-dev-palier-3.md` section 9, `EP3`).
3. **La section 6 de mon rapport dit les branches de lots coupees avant `RL5` a `RL7`, et `git merge-base` dit l'inverse.** `git merge-base --is-ancestor 4bf3c359 b9c6cb69` sort 0 : les cinq branches coupees sur `b9c6cb69` **contiennent** `RL5` a `RL7`. La phrase de la section 3 du meme fichier disait juste. `camille-po` l'a trouve en verifiant (`DEC-0120` A.117), le releve l'a trouve (ecart 1). **Corrige par un amendement date du 2026-10-21 17:43, l'original n'est pas reecrit.** Cause : j'ai ecrit sans relancer la commande.
4. **Mes autres ecarts du releve** (numeros 2 a 7) : titre et casquette du rapport qui ne valent que pour ses cinq premieres sections, sections sans heure, totaux sans commande ni perimetre ; une lettre de lecteur dans la section 7 ; un registre ASCII et accentue melanges et une colonne *fichier ajoute* inexacte ; deux citations rangees sous une seule source dans `composition-palier-3.md` et `planning.md` ; la delegation du 2026-10-19 relayee sans ses mots ; un renvoi vers `cloture-palier-3.md` avant que le fichier existe. Tous traites en section 9.
5. **Trois engagements livres sans amendement de `planning.md`** : `S57-GARDE-PILE-DE-POLICES`, `S57-R6-COMMENTAIRES-DE-PAQUET`, `S57-SAISIE-CLAVIER-SANS-CORRECTION`. L'amendement de 23:44 les laissait chez `camille-po` (*"puis `sofia-sm` (engagement)"*) ; `camille-po` les a rendus (`AD6`, `AD7`), je les ai livres ou laisse livrer sans ecrire l'engagement, **le meme defaut que la lecon 6 de `retro-palier-2.md` voulait eviter**. L'amendement de `planning.md` du 2026-10-21 17:46 (section 4) le dit ; il ne fabrique pas l'engagement.
6. **Le journal des fusions n'est pas dans `planning.md`.** Aux paliers 1 et 2, chaque etat des postes etait un amendement date. Au palier 3, `planning.md` n'avait, avant les amendements de cloture de ce jour, que deux amendements de moi (23:09 et 23:44) ; les 21 fusions et leurs portes sont dans mon rapport de developpeuse. Le point 5 ci-dessus en est la consequence.
7. **`ines-design` lancee sans ligne d'isolation.** La table lui promettait une ligne ajoutee par amendement avant son lancement ; je ne l'ai pas ecrite, et ses ports 186xx et 187xx n'etaient dans aucune table.
8. **`K7` non verifie avant la cloture** : je n'ai pas relance `marc-dev` ni `lea-dev` pour leurs rapports de palier (section 10, point 9).
9. **`RL5` a `RL7` et `R6` sans second juge** (section 1) : `RG5` me demandait de le dire, je le dis, et je n'ai pas demande la relecture de `yanis-qa` avant de fusionner.

**Ce que les fichiers des autres disent d'eux-memes, tel qu'ils l'ecrivent.**

- **`theo-archi`** : `EP6` (`5a0b55f3` commite sur une mesure partielle, sans `verify` complet ni les jobs `chemins`, `prefixes`, `outils` nommes ; pour `78b260e3` et `19aa6c98`, *"non verifie"*) ; `EP7` (le code `R6` pris trois fois dans le sprint, dont deux le meme jour). Ses ecarts 34 a 39 du releve.
- **`yanis-qa`** : deux invalidations de sa propre methode (la salve jetee, la consigne d'une autre entree) ; le total au sommet *"non relu"*, 2855 *"ni confirme ni infirme"*.
- **`nadia-prof`** : la phrase *"le pli et les coupures, que l'utilisatrice a dits non bloquants"* etait inexacte pour moitie, **rectifiee sur le constat de `camille-po`** : l'utilisatrice a dit le pli, pas les coupures, la coupure est une decision de `nadia-prof` ; sa correction de `V6` point 3 (le Done ne se prononce pas *"apres mon jeu de l'arbre fusionne"*, `V5` point 6 n'a jamais ete une barre) ; la phrase du verdict de `-07` qui dit *"par la correction d'`afd33e88`"* (c'est la fusion, ecart 22).
- **`camille-po`** : la phrase *"avant tout autre commit de code"* de `A.115`, corrigee par `A.117` ; `DEC-0124` et son index qui disent *"non appliquee"* apres l'application (ecart 12) ; les `DEC` qui qualifient *"relayee"* une reponse que `DEC-0062` ne classe pas (ecart 9, traite pour la liste a 17:36).
- **`ines-design`** : trois identifiants ecrits avant leur allocation (`EP2`) ; 169 chemins de session dans un fichier commite (ecart 26) ; ses ecarts 27 a 33.
- **`marc-dev`, `lea-dev`** : pas de rapport de palier ; des commentaires de paquet devenus faux au verdict (ecarts 40 a 44).
- **`relecteur-pedagogique`** : il declare lui-meme ne pas avoir lu `preparation-palier-3-ce1-camille.md`, `reponses-nadia-questions-stories-palier-3.md`, `arbitrage-nadia-preparation-palier-3-ce1.md` en entier ni les rapports de `yanis-qa`, ni les fichiers non commites d'autres agents.

**Un point verifie, pour qu'un ecart ne soit pas recopie plus large qu'il n'est** : le releve note que le nombre 152 designe trois grandeurs differentes dans le sprint (entrees de catalogue avant les lots, entrees `bandit-manchot` apres, lignes `couvert-valide`). Dans ce fichier, 152 est une entree de catalogue en section 3 et une ligne `couvert-valide` en section 5 ; chaque occurrence nomme la sienne.

## 12. Porte technique : jobs de `ci.yml` lances pour ce commit

Lances depuis le checkout principal, sur `round-two` a `d09655bf`, avec `cloture-palier-3.md`, `retro-palier-3.md` et les trois amendements (`rapport-sofia-dev-palier-3.md`, `composition-palier-3.md`, `planning.md`) presents dans l'arbre, **avant tout commit**. Journaux dans mon repertoire de la table, `TMPDIR` en forme POSIX dans ce repertoire. **Je ne deduis aucun job que je n'ai pas lance.**

- **`chemins`** : `bash .github/scripts/verifier-chemins-test.sh` sort 0 (*"Tous les cas passent : le garde-fou de chemins mord, et sur le bon motif."*) ; `git ls-files` (moins le test du garde-fou) plus les deux fichiers neufs, 3058 chemins, passent a `verifier-chemins.sh` : *"Aucun chemin de poste de travail ni nom de compte dans les fichiers verifies."*, sortie 0.
- **`build`, partiel** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest -Dsurefire.failIfNoSpecifiedTests=false`, **`-am` present**, perimetre `bootstrap` et ses amonts : `BUILD SUCCESS`, exit 0, **18 tests, 0 echec, 0 erreur, 0 ignore** (10, 5 et 3), lance deux fois : 3 min 03 s, puis 2 min 55 s (18:09 a 18:12) sur les fichiers dans leur etat quasi final. Lance parce que ces trois classes lisent `squad/` par `Files.walk`, fichiers non suivis compris : elles ont lu les deux fichiers neufs et les trois amendements. **Ce que la seconde execution n'a pas lu** : la presente section 12, reecrite apres elle, et une phrase de `retro-palier-3.md` corrigee pendant son execution. Aucune des deux n'ajoute un tableau ni une reference de decision, et la presente section ne contient aucun tableau. Je n'ai pas relance les trois classes apres ces deux ecritures.
- **`build`, le reste** : le reacteur complet `mvn -B verify` et l'etape *"Package du fat jar"* **ne sont pas lances** : ces commits ne touchent ni code ni contenu servi (`git diff --name-only d09655bf` ne rend que trois fichiers de `squad/sprints/`, plus les deux fichiers neufs non suivis). **2855 est le total de `03de0f6d`, mesure par moi, non rejoue** (section 4).
- **`prefixes`** : `verifier-prefixes-test.sh`, `commit-atteignable-test.sh` et `verifier-garde-fou-accent-test.sh` (21 cas) sortent 0 ; les cinq sujets prevus des commits passent dans `verifier-prefixes.sh` : *"Tous les commits portent un prefixe d'agent connu."*, sortie 0 ; `commit-atteignable.sh d09655bf` sort 0 ; `verifier-garde-fou-accent.sh "d09655bf..HEAD"` : *"0 commit(s) le touchent dans la plage, tous [THEO]."*, sortie 0. **La base d'un push (`github.event.before`) n'existe pas en local** : `d09655bf` en tient lieu.
- **`outils`** : **non lance**. Aucun outil n'est modifie par ces commits.

**La CI distante n'est lue par personne, et elle n'a rien a lire.** `git status -sb` rend `round-two...origin/round-two [ahead 98]` : **aucun des 98 commits d'avance n'est pousse**, le palier 3 compris ; il n'y a pas de `.env` dans ce checkout et je n'ai lance aucune commande `gh`. Aucun des resultats ci-dessus n'est celui de la CI. **Le Done technique n'est pas prononce par moi** : il est juge par la CI sur le commit pousse, et personne n'a pousse. Les jobs lances par les autres agents a la cloture, tels qu'ils les declarent : `theo-archi` (`confirmation-archunit-palier-3.md` section 6) : `build` partie `verify` seule sur une copie de `b52c4ac5`, `chemins` sur son fichier seul, deux classes de `bootstrap`, ni `prefixes` ni `outils` ; `yanis-qa` (`rapport-qualite-palier-3.md`, en tete) : **aucun job de `ci.yml`**, une mesure partielle de cinq classes ; `relecteur-pedagogique` (`releve-relecteur-cloture-palier-3.md` section 5) : `verifier-chemins.sh` sur un fichier, l'equivalent Python de deux gardes de `bootstrap`, **pas `mvn`** ; `camille-po` (`DEC-0120` A.117) : n'a pas lance `mvn`.

## 13. A decider par l'utilisatrice

**Aucune decision n'est prise ici.** Je les pose avec l'avis ecrit de chaque agent qui en a donne un, et je dis quand il n'y en a pas.

### (a) `QP6` : les developpeurs de B et d'A' au palier 4 (`preparation-palier-4-camille.md`, section 7.2)

**Question.** `RG2` demande des developpeurs distincts sur des instances distinctes ; `RG1` exclut `lea-dev` du francais CE1 ; `marc-dev` est sur A. B (`MAT-CE1-10`, `-11`) et A' (`EMC-CE1-02`, `-03`, `-04` selon la borne) se reduisent a `lea-dev` et `sofia-sm`, **sens non deduit**. Au palier 3 elle a repondu *"ok"* a ma recommandation (`lea-dev` sur les deux, ecart a `RG2` dit). **Ce que ca coute sans reponse** : un **premier report pour chacun des quatre lots non engages a l'ouverture** (`DEC-0120` A.66), comme `MAT-CE1-01` et `EMC-CE1-01` au palier 3. **C'est la seule question qui coute un report, et elle est bloquante a l'ouverture.**

| Option | Ce qu'elle donne | Ce qu'elle coute |
|---|---|---|
| O1, `lea-dev` sur B et A' (comme `AD1`) | deja pratiquee au palier 3 : cinq lots livres | cinq lots l'un apres l'autre (`RG4`) a six ou huit lots ; contention de build ; `RG2` ecartee, dite |
| O2, `lea-dev` sur une instance, `sofia-sm` sur l'autre | `RG2` tenue | `sofia-sm` fusionne sa propre branche, relue par `yanis-qa` seul (`RG5`), et porte les fusions et la cloture |
| O3, un lot de B ou d'A' a `marc-dev` | decharge `lea-dev` | `RG2` ecartee sur l'instance de `marc-dev` ; `marc-dev` est l'auteur de `ce1-nombres-jusqu-a-1000` |

**Avis ecrits** : `camille-po` pose la question sans recommandation (`preparation-palier-4-camille.md` 7.2). `nadia-prof`, `theo-archi`, `yanis-qa` : aucun avis ecrit. **Ma recommandation, non une decision** : O1, avec une file pour le `verify` entier (section 14), parce qu'elle garde `sofia-sm` sans lot. Le palier 3 n'a pas mesure la charge de `lea-dev`.

### (b) `QP7` : s57 se clot-il apres le palier 4, ou apres un palier 5 ?

**Question.** Tout ce que `nadia-prof` renvoie a *"s58"* ne s'ouvre pas dans s57, quelle qu'en soit la duree : `-09` puis `-08`, `FRA-CE1-29`, les neuf lots de questionner le monde CE1, puis les terrains neufs si A' n'existe plus. **16 lignes sur 43 `non couvert` attendent ce sprint-la.** `P4'` de `duree-et-paliers.md` : le sprint s'arrete *"quand l'utilisatrice l'arrete"*. **Sans reponse** : s57 se prolonge sans pouvoir ouvrir plus que `EMC-CE1-04`, `MAT-CE1-15` illustre et `MAT-CE1-14`, forme exacte du report repete que `DEC-0007` a nomme. `camille-po` : *"tout le reste en depend"*. **Avis ecrit de `nadia-prof`** : les renvois a s58 sont les siens (`arbitrage-nadia-q05-q10.md` section 2) ; aucun avis sur la cloture de s57. Les autres agents : aucun avis.

### (c) `QP5` : le maximum de la version passe-t-il de 200 a 202 ?

**Question.** Si `FRA-CE1-27` et `FRA-CE1-29` sont reclassees de `non couvrable en l'etat` a `non couvert` (premisse de l'adulte etendue par `Q-08`), le denominateur passe a 202 ; elle l'a fixe a 200 (`Q-U10`). 152 / 202 = 75,25 % a numerateur egal : **le taux baisse de 0,75 point sans qu'aucune ligne ne bouge**. **Sans reponse** : `camille-po` ne reclasse pas, les deux lignes restent hors du denominateur et `nadia-prof` ne peut pas les auditer sous un statut qui dit qu'elles ne le sont pas. Une information avec une confirmation.

### (d) `QP3` : `MAT-CE1-14`, *"ajouter une grille pour infos a l'enfant"*

**Question.** Un quadrillage montre sur lequel l'enfant se repere, se deplace et appuie sur la case (forme visee par `camille-po`, et que `nadia-prof` dit lever `DEP-CE1-2` en entier par une designation sur la grille), ou un tableau d'informations sans cases designables (qui ne leve pas `DEP-CE1-2` ; plafond `en cours`) ? **Sans reponse** : `LP9` reste hors palier. **Avis ecrits** : `nadia-prof` (`arbitrage-nadia-q05-q10.md` section 1, plafonds) ; `theo-archi` : `A3` d'`ADR-0080`, sa lecture n'est pas actee (`DA5`).

### (e) La confirmation de la lecture de la consigne sur le seuil et les instances

**Les mots sont confirmes** (`DEC-0120` A.132, 17:36) : *"pour le palier 4 augmente le seuil avec des instances en // jusqu'a 3 si besoin"*. **La lecture, non** (`DEC-0120` A.126 et A.132, *"reste a confirmer"*) : le seuil est la borne de 8,0 par palier (`PAR-9`) ; les instances sont A, A' et B ; *"jusqu'a 3"* est l'etat des paliers 1 a 3, ou un plafond de trois. **La borne est a `nadia-prof`** : elle ecrit `O2` et la capacite par instance, ou maintient 8,0. `camille-po` propose 8,0 (repli, cinq lots), 9,0 (six lots prets) ou 11,5 (huit lots, deux conditions) ; **le neuvieme lot n'existe pas** ; elle mesure les trois conditions de `4.3` : (i) non montree, (ii) non verifiee, (iii) tenue. **Sans reponse** : `nadia-prof` n'a pas ecrit d'arbitrage prealable du palier 4 (`ls` : aucun fichier), le palier 4 ne s'ouvre pas.

### (f) Les quatre decisions de `cloture-palier-2.md` section 13, toujours sans reponse

Aucune n'a de reponse dans le depot. Etat relu ce jour : `ci.yml` rend 0 pour `grep -c "setup-node\|setup-python"` ; la ligne 23 de `outils/mvn-jdk21-test.sh` est celle d'origine ; `retro.md` de s57 n'existe pas. **Je n'ai modifie ni `ci.yml` ni `outils/mvn-jdk21-test.sh`.**

| Decision | Question | Avis ecrits (rappel de `cloture-palier-2.md`) |
|---|---|---|
| f1, `Q4` | un crochet du harnais sur les redirections du shell : texte de la commande, controle de l'effet, ou rien de plus que la mesure de cloture | `theo-archi` : (1) non recommande, (2) et (3) sans choix. `nadia-prof` : constat. **Ce palier ajoute un fait : la consigne ecrite n'a pas tenu, 69 fichiers a la racine (section 10).** `sofia-sm` : la consigne n'est pas un moyen de garde. Les autres : aucun |
| f2, `node` et Python | declarer les deux dans le job `outils` de `ci.yml`, a majeure fixee | `theo-archi` et `yanis-qa` concordent : declarer. Les autres : aucun avis |
| f3, `TMPDIR` | normaliser par `cd` puis `pwd` dans `outils/mvn-jdk21-test.sh` | `theo-archi` et `yanis-qa` concordent : normaliser. Les autres : aucun avis |
| f4, la retro des erreurs | verser les lecons de `retro-palier-2.md` dans `retro.md` du sprint, ou repartir d'une page neuve | aucun agent n'a ete interroge ; **ma recommandation, non une decision** : les verser comme sources citees. Meme question pour `retro-palier-3.md` |

### (g) Le terme pour le role que quatre noms designent (ecart 8 du releve)

**Question.** Le releve trouve *"le lanceur"*, *"l'agent lanceur"*, *"l'agent qui m'a lance"* et *"le coordinateur"* pour le meme role ; `DEC-0062` section 6 note que *"le coordinateur"* n'est defini nulle part ; le terme designe un canal que `DEC-0062` laisse a l'utilisatrice, dont elle a nomme la voix *"MASTER"* (`DEC-0062` A.1). **Un terme, defini une fois dans `squad/README.md` section 2 bis.** **Ma recommandation, non une decision** : *"agent lanceur"* pour l'agent qui lance une tache, *"MASTER"* pour la voix de l'utilisatrice ; abandonner *"coordinateur"*. Aucun autre avis ecrit.

### (h) La provenance des mots de l'utilisatrice

**Dit tel que le releve le pose (ecart 9).** *"Relayee"* n'est **pas** l'une des trois formes de `DEC-0062` section 3.1 (de l'agent, de l'utilisatrice avec ses mots cites et la date, ou recu par le fil d'animation sans source dans le depot, qui ne vaut jamais motif, 3.2). `AD1`, `AD2`, `AD4`, `Q-05` a `Q-10`, la levee de `R6` et la consigne du palier 4 **reposent sur des mots recus par le fil**. **`E5`, ouvert depuis `cloture-palier-1.md`, est ouvert pour le troisieme palier.** La correction la plus simple : que l'utilisatrice confirme ces mots en une fois, ce qui les fait passer en forme 2.

**Etat a l'ecriture** : cette confirmation a ete demandee et enregistree **apres** l'ecriture du releve : `d09655bf`, 2026-10-21 17:36, `DEC-0120` A.132 et `DEC-0062` amendement E (`camille-po`) : l'utilisatrice a repondu *"confirme"*, **relayee a son tour par l'agent lanceur, non lue de sa main par `camille-po`**. La liste couverte est exactement : le *"ok"* sur `AD1`, `AD2`, `AD4` ; `Q-05` a `Q-10` ; *"il n'y aura pas de correction automatique du clavier"* ; *"pour moi c'est bon R6 peut etre leve"* ; la consigne sur le seuil. **Ce qui n'est pas couvert, a lui demander** : (1) la delegation du 2026-10-19 (*"continue au mieux pour obtenir la meilleure couverture"*), sur laquelle repose la variante (i) de la composition du palier 3 ; (2) tout enonce relaye hors de la liste (`DEC-0120` A.114, `Q-U11`, les paliers 1 et 2) ; (3) **la regle pour une reponse relayee**, `E5`, qui reste ouverte ; (4) le canal par lequel les mots arrivent a l'agent lanceur la premiere fois, que la confirmation ne dit pas.

### (i) Le sort de `s57-ines-p3-pli` et de `recreo-ines-integ-pli`

**Faits.** `s57-ines-p3-pli` (`80781f61`, worktree voisin du checkout) porte quatre commits `[INES]` hors de `round-two` : `b383b0bb` (fusionne par cherry-pick comme `5302af53`), **`edf7f344` (REFUSE par `nadia-prof`, 22 jeux a deux lignes)**, deux mesures (`af8fa4c9`, `80781f61`) dont le contenu est deja dans `round-two` (le fichier de mesure y est un surensemble : `git diff --stat round-two s57-ines-p3-pli` ne rend, sur ce fichier, que 135 suppressions et aucune insertion). `recreo-ines-integ-pli` est un worktree **detache** a `bee3bb37`, *"fusion locale jetable"*, non pousse, hors de `round-two` (`git merge-base --is-ancestor bee3bb37 round-two` sort 1), et declare jetable par son auteure. `git status --short` y est vide dans les deux. **Deux autres leviers pour le score a droite n'ont pas de commit** (`veto-nadia-gabarit-pli.md` V1) : un flottant reserve aux consignes courtes, le score dans l'en-tete.

**Question.** Les supprimer, garder la branche tant que le score a droite n'est pas definitivement abandonne, ou autre chose ? **Avis ecrits** : aucun. `ines-design` ecrit que le worktree d'integration est jetable. **Ma recommandation, non une decision** : supprimer le worktree detache (jetable, propre) ; garder `s57-ines-p3-pli` jusqu'a ce que `nadia-prof` ou `camille-po` ferme le sort du score a droite, puis la supprimer avec son worktree. Le nettoyage des worktrees de s57 n'est decide par personne (section 10).

## 14. Ce qui reste a faire au palier 4

Rien n'est decide ici : le palier 4 n'est pas ouvert et ce fichier ne le compose pas. **Pour l'ouvrir** (`D2` de `planning.md`, quatre conditions) : (a) ce fichier sur `round-two` ; (b) l'arbitrage prealable de `nadia-prof` pour chaque instance, qui ecrit la capacite et, si elle le juge, `O2` : **il n'existe pas** (`ls` : aucun fichier de palier 4 de sa main) ; (c) la composition de `camille-po`, avec un amendement date de `planning.md` inscrit avant l'ouverture des worktrees (`K1 (a)` des stories) ; (d) la table d'isolation, que j'ecris apres la composition.

**Pas de composition ici.** Etat des preparatifs, sans engagement :

- **`preparation-palier-4-camille.md`** (`camille-po`, `53dc13ff`) : une proposition, pas une composition. Repli a 8,0 (cinq lots), 9,0 (six lots prets : A 2,0, B 3,0, A' 4,0), 11,5 (huit lots, deux conditions). Quatorze stories ecrites a la fin de `docs/backlog/backlog.md`, aucune engagee. **`LP1` a `LP6` prets**, `LP7` a `LP9` conditionnels ou non prets. **Ses prix sont indicatifs, ceux de `nadia-prof`.** Le gain du 9,0, si les verdicts tombent : `couvert-valide` 152 a 156 (78,00 %).
- **L'arbitrage prealable de `nadia-prof`, option `O2`** : a venir. Elle dit si elle maintient 8,0 ou ecrit une borne neuve, la capacite de chaque instance, l'instance et le prix de `FRA-CE1-21`, la confirmation que `PAR-13` tient pour `FRA-CE1-21`, le cousinage de `FRA-CE1-03`, le prix de rejeu de `-07`, l'annotation de `TRV-CE1-1` pour `FRA-CE1-27` et `-29`.
- **L'avis de `theo-archi`** : a venir. `QP1` (l'apostrophe d'*"Aujourd'hui"* en saisie), `QP2` (les gardes de `texteAVoixHaute` d'`ADR-0075`), `QP4` (`DA4`), `A3` d'`ADR-0080` (`DA5`), le developpeur et la story de `DA1`, la double preuve d'une photographie.
- **Ce qui est a moi pour la composition** (`DEC-0120` A.130, A.129) : les regles de charge et de parallele a trois instances. **A ecrire a la composition, et je les pose ici sans les ecrire** : (1) le nombre de `verify` entiers simultanes (un par poste de mesure, file d'attente) ; (2) la reprise apres un arret d'agent ou du watchdog : sur le meme worktree ou sur un neuf, et **l'arret ecrit au moment ou il survient** (qui, quand, ce que la branche portait, `A10` de s56) ; (3) les lignes de la table d'isolation de trois instances sur six lots ou plus, **une ligne pour chaque agent qui sera lance, `ines-design`, `yanis-qa`, `nadia-prof`, `camille-po`, `relecteur-pedagogique` compris**, ajoutee avant son lancement ; (4) la lignee de chaque branche, jugee avant chaque fusion ; (5) l'allocation des identifiants **avant** toute fusion qui les ecrit ; (6) un amendement de `planning.md` par etat des postes, **hors composition compris**.
- **Les ecarts routes** (section 9) : 40 a 44 et 33 sont des commits de developpeur a engager a la composition ; 9 a 25, 34 a 39 sont chez leurs proprietaires.
- **Les reserves du palier 3 sans story** : la reponse attendue sous le pli a la revelation (`-06` 14, 16, 19, 20 ; `-11` 13 ; EMC 13), le verdict et l'indice coupes de `-06` 14 apres une erreur, la coupure d'un mot a trait d'union (`-06` 14, EMC 13), l'item 17 d'EMC : a `camille-po`, si l'utilisatrice veut leur correction (`verdict-nadia-palier-3-ce1.md` section 7).
- **Trois stories hors `PAR` a leur deuxieme ecartement** (section 2) ; `S57-GARDE-CE1-PROPOSITIONS-ET-SUPPORTS` attend l'avis de `nadia-prof` sur `CA4`.
- **`DO2`** (section 7) : critere a reformuler, test a mettre d'accord.
- **Le reclassement de `FRA-CE1-27` et `-29`** apres l'annotation de `TRV-CE1-1` (`DEC-0120` A.123, `QP5`).
- **L'amendement de `CLAUDE.md` sur le CE1** (`DEC-0123` 5.4) : **non fait** ; c'est un fichier que tous les agents lisent, je ne l'ecris pas sans l'utilisatrice.
- **A la cloture du sprint** (pas avant) : `review.md`, `retro.md`, `RAPPORT.md`, `RAPPORT-tokens.md` et la mise a jour de `squad/rapports/JOURNAL-tokens.md`, en une fois. Les amendements a `squad/README.md` qu'appelle `retro-palier-3.md` s'ecrivent a la retro du sprint.

## 15. Obstacles du palier

Repris dans `review.md` a la cloture du sprint. **Un obstacle n'est clos que quand le fichier qui le tranche existe sur le disque.**

| Obstacle | Categorie | Agent | Artefact | Etat |
|---|---|---|---|---|
| `AD1`, `AD2`, `AD4` | capacite | utilisatrice | decision | **clos** : mots confirmes, `DEC-0120` A.132 (la confirmation est elle-meme relayee) ; inscrits `planning.md` 23:44 |
| `AD3`, `AD5`, `AD6`, `AD7` | produit | `camille-po` | stories, `DEC` | **clos** : `DEC-0120` A.117 et `preparation-palier-3-ce1-camille.md` |
| `Z1`, `Z2`, `Z3`, `Z4`, `Z5` d'`ADR-0031` | technique | `theo-archi` | amendement | **clos** : `78b260e3`, `confirmation-archunit-palier-3.md` |
| Pile de polices | produit, technique | `camille-po`, `theo-archi` | `DEC-0122`, `ADR-0054` | **clos** : `558dd887`, `19aa6c98` |
| `RL5` a `RL7`, `R6` du releve | redaction | `sofia-sm` | commits | **clos** : `4bf3c359`, `e6c371ad` (sans second juge) |
| `PL-1` a `PL-13`, identifiants non alloues | technique | `theo-archi` | registre | **clos** : `21e994a4`, `ad9ef86d`, et allocation de `PL-1` a `PL-8` avec la mesure `6d2e5b38` |
| Verdicts des cinq lots | pedagogie | `nadia-prof` | fiches | **clos** : section 1 |
| `R6` de `nadia-prof`, refus de saisie | produit, pedagogie | utilisatrice, `nadia-prof` | `DEC-0124`, arbitrage | **clos** sous une reserve non bloquante ; l'ordinateur n'est pas mesure |
| Correctif de gabarit du pli | pedagogie | `nadia-prof` | veto | **clos** pour `5302af53` ; `edf7f344` refuse, sort de la branche a l'utilisatrice (section 13 (i)) |
| `DO2`, trou de garde | qualite | `camille-po`, developpeur | critere, test | **ouvert** (section 7) |
| `E3`, garde generique `LON-CE1-3` a `-5` et `TRV-CE1-1` | technique | `camille-po`, `theo-archi` | story, `ADR` | **ouvert** : `DA1`, `DA2` |
| Reserves du palier 3 sans story (pli, verdict, coupure, item 17) | produit | `camille-po` | stories | **ouvert** : `DA3` |
| `DA4`, `DA5` | technique | `theo-archi` | avis, amendement d'`ADR-0080` | **ouvert** |
| Ecarts 9 a 25 et 34 a 39 du releve | redaction | `camille-po`, `nadia-prof`, `ines-design`, `theo-archi` | corrections et choix | **ouvert** (section 9) |
| Ecarts 33 et 40 a 44 du releve, commentaires de `src/main` | redaction | `sofia-sm`, `marc-dev`, `lea-dev`, `ines-design` | commit de developpeur, story | **ouvert**, a engager au palier 4 |
| `R6` du releve, critere 6, et `RL5` a `RL7` sans relecture | redaction | `relecteur-pedagogique`, `yanis-qa` | relecture | **ouvert** |
| `E5`, regle pour une reponse relayee | produit | `camille-po`, utilisatrice | regle | **ouvert, troisieme palier** (section 13 (h)) |
| Delegation du 2026-10-19, hors de la liste confirmee | produit | utilisatrice | confirmation | **ouvert** |
| `QP3`, `QP5`, `QP6`, `QP7` | produit | utilisatrice | decisions | **ouvert** (section 13) |
| Lecture de la consigne sur le seuil | produit | utilisatrice | confirmation | **ouvert** |
| Quatre decisions de `cloture-palier-2.md` section 13 | technique | utilisatrice | decisions | **ouvert** (section 13 (f)) |
| Terme du role *"lanceur"* | redaction | utilisatrice | `README.md` | **ouvert** (section 13 (g)) |
| Sort de `s57-ines-p3-pli`, de `recreo-ines-integ-pli` et des worktrees | capacite | utilisatrice | decision | **ouvert** (section 13 (i)) |
| Chemin `%TEMP%` d'une session passe `verifier-chemins.sh` | qualite, technique | `theo-archi` | `ADR` ou garde | **ouvert** (ecart 39) |
| Racine du scratchpad et lecture des transcriptions | qualite | `nadia-prof`, utilisatrice | lecture ecrite | **ouvert** (section 10) |
| Rapports de palier de `marc-dev` et de `lea-dev` (`K7`) | redaction | `marc-dev`, `lea-dev` | rapports | **ouvert** |
| Arrets d'agent et watchdog, non ecrits | capacite | `sofia-sm`, utilisatrice | ligne datee | **ouvert** (`A10` de s56) |
| Prix de rejeu de `-07` | capacite | `nadia-prof` | ligne du registre | **ouvert** |
| `Q-N5` | pedagogie | `nadia-prof` | reponse | **ouvert**, sans echeance |
| Arbitrage prealable du palier 4, avis de `theo-archi` | pedagogie, technique | `nadia-prof`, `theo-archi` | arbitrage, avis | **a venir** (section 14) |
| Rapport de tokens | capacite | `sofia-sm` | rapport | **a la review** (section 6) |

## Decisions prises

**Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`.** Liste des decisions du palier 3 (de `ad65827e` a `d09655bf`, `git diff --name-status ad65827e d09655bf -- squad/decisions`), avec statut tel que le fichier le porte :

| Decision | Cree ou modifiee | Statut |
|---|---|---|
| `DEC-0120` (ouverture de s57) | modifiee : amendements dix-neuf a vingt et un (`A.117` a `A.132`) : ordre tenu et ecart au rapport, reports, prix, reponses `Q-05` a `Q-10`, matrice, seuil et instances du palier 4, confirmation des mots relayes | `Acceptee`, inchange |
| `DEC-0121` (`nombreDeManches` 10) | modifiee : la liste fermee des derogations amendee le 2026-10-19 (`a5748847`) | `Acceptee`, inchange |
| `DEC-0123` (six reponses de l'utilisatrice au palier 3 CE1) | **creee**, 2026-10-21, `camille-po` ; amendee depuis | `Acceptee` pour `Q-05`, `Q-06`, `Q-07`, `Q-08`, `Q-10` ; sa mention *"`Q-09` suspendue"* est perimee (ecart 11) |
| `DEC-0124` (`Q-09`, le clavier ne corrige pas) | **creee**, 2026-10-21, `camille-po` ; amendee depuis | `Acceptee` pour le sens ; sa mention *"non appliquee"* est perimee depuis `03de0f6d` (ecart 12) |
| `DEC-0047` (perimetre de s20) | modifiee : section 2 remplacee en partie par `DEC-0124` | `Acceptee` |
| `DEC-0062` (convention d'attribution d'un enonce recu) | modifiee : amendement E, les mots relayes de la liste passent de la forme 3 a la forme 2 (`d09655bf`) | `Acceptee`, inchange |
| `ADR-0031` (un ecran ne gouverne jamais l'apparence d'un autre) | modifiee : amendement du 2026-10-20, `Z1` a `Z5` (`78b260e3`) | `Accepte` |
| `ADR-0054` (la largeur offerte a une rangee) | modifiee deux fois : 2026-10-20 (pile de polices servie, `558dd887`) et 2026-10-21 (echappements decodes, `inherit` admis, `19aa6c98`) | `Accepte`, sa regle ecrite dans le meme commit |
| `ADR-0058` (la saisie normalise dans sa mecanique) | modifiee : amendement du 2026-10-21, `autocorrect="off"` (`5a0b55f3`) | `Accepte` pour ses clauses de conception (son statut porte une suite que je ne recopie pas) |
| `REGISTRE-identifiants.md` | modifie : allocation de `PL-1` a `PL-13` (`ADR-0085`, clause 2) | sans objet |

`ADR-index.md` et `DEC-index.md` portent les lignes correspondantes. **Les corrections du releve de cloture ecrites par ce palier (amendements de `rapport-sofia-dev-palier-3.md`, de `composition-palier-3.md` et de `planning.md`) ne creent ni n'amendent aucune decision.** `retro-palier-3.md`, a cote de ce fichier, porte les lecons du palier ; il n'est pas la `retro.md` du sprint.

## Amendement du 2026-10-21 (`sofia-sm`, casquette Scrum Master) - reponses de l'utilisatrice aux questions de la section 13

**Ce fichier est ecrit une seule fois ; ceci est un amendement date, non une reecriture.** Aucune decision n'est prise ici et aucune `DEC` ni `ADR` n'est creee ou amendee : `camille-po` consigne les siennes (`DEC-0062` amendement G, `DEC-0120` A.133 et suivants, `DEC-0123`).

**Provenance.** Mots de l'utilisatrice, 2026-10-21, forme 2, **transmis par l'agent lanceur et cites par lui mot pour mot**, sans la question posee : *"E5 oui ça vaut pour mes mots, QP6 lea dev pour B et sofia pour A', QP7 après un palier 5 voir 6 je ne sais pas encore, QP5 oui, QP3 un tableau d'informations"*. Les questions sont celles de la section 13 (a) a (d) et de `DEC-0062` amendement F. **L'agent lanceur n'a pas cite la question de `QP5` ni celle de `E5` : je les lis dans ce fichier et dans l'amendement F, et je le dis plutot que de le taire.**

| Point | Reponse | Ce qui suit, et qui l'ecrit |
|---|---|---|
| `E5`, section 13 (h) point 3 | *"oui ça vaut pour mes mots"* | **Ferme.** La regle est ecrite dans `squad/README.md` section 2 bis (paragraphe *"Un enonce de l'utilisatrice transmis par l'agent lanceur"*). Les points (1), (2) et (4) de la section 13 (h) **ne sont pas couverts par cette reponse** : la delegation du 2026-10-19, les enonces hors liste et le canal restent sans confirmation ; sans balayage retroactif, ils gardent leur forme |
| `QP6`, section 13 (a) | *"lea dev pour B et sofia pour A'"* | `lea-dev` sur B, `sofia-sm` sur A' : l'option O2, sens tranche. `RG2` est tenue. Le report des quatre lots non engages a l'ouverture est evite. `camille-po` l'ecrit dans la composition. Mes regles pour les deux casquettes : ci-dessous |
| `QP7`, section 13 (b) | *"après un palier 5 voir 6 je ne sais pas encore"* | **Pas de decision.** s57 ne se clot pas apres le palier 4 ; la cloture apres le palier 5, voire 6, n'est pas arretee. Le palier 5 est a composer. Ce que la section 13 (b) renvoie a *"s58"* reste ou il est |
| `QP5`, section 13 (c) | *"oui"* | Le maximum passe de 200 a 202 (sens lu dans la section 13 (c), la question n'etant pas citee). Le reclassement de `FRA-CE1-27` et `-29` et le calcul sont a `camille-po` et a `nadia-prof` ; je ne touche ni la matrice ni le taux |
| `QP3`, section 13 (d) | *"un tableau d'informations"* | Le tableau sans cases designables : il ne leve pas `DEP-CE1-2`, plafond `en cours` (section 13 (d)). La story `S57-BR-MAT-CE1-14-GRILLE` et `LP9` sont a `camille-po` |
| Seuil du palier 4 | **sans reponse : ouvert** | Conflit `PAR-8` : 5,0 par instance et trois lots par instance donnent **15,0 pour trois instances**, ce qui ne se concilie pas avec un seuil de 20. La section 13 (e) reste ouverte ; la borne est a `nadia-prof`, la composition a `camille-po` |

Obstacles de la section 15 : `E5` passe a **clos** (regle ecrite dans `squad/README.md`, qui existe sur le disque) ; `QP3`, `QP5`, `QP6` : **repondus**, leur artefact (`DEC` de `camille-po`) n'est **pas verifie par moi** et ces lignes ne passent a *clos* qu'a son existence ; `QP7` : **repondu sans decision** ; la lecture de la consigne sur le seuil : **ouvert**.

### Proposition de `sofia-sm` pour ses deux casquettes sur le palier 4

**Proposition, pas un engagement : elle n'engage pas le palier et la composition reste a `camille-po`.** Je ne porte jamais les deux casquettes dans la meme tache ; sur A', un lot est une tache de developpeuse, tout le reste est une tache de Scrum Master.

1. **Un lot de A' commence et finit sous la seule casquette de developpeuse.** Pendant un lot, je ne fusionne rien, je ne juge aucune lignee, je n'anime aucun amendement de `planning.md` : ces gestes attendent la fin du lot (`RG4` : un worktree a la fois). Le seul geste de Scrum Master que je ne retarde pas est l'ecriture d'un arret d'agent, au moment ou il survient (`A10` de s56).
2. **Ma propre branche n'est pas jugee par moi.** La lignee de la branche de A' est lue par `theo-archi` (`git merge-base --is-ancestor`, resultat ecrit), le rapport qualite est celui de `yanis-qa` seul (`RG5`), et le Done pedagogique reste a `nadia-prof`. **Je ne fusionne la branche de A' qu'apres ces deux ecrits sur le disque.** Sans eux, elle attend.
3. **Le total de tests d'apres fusion d'un lot de A' est mesure par `yanis-qa`, pas par moi.** Je mesure celui de A et de B (lots de `marc-dev` et de `lea-dev`).
4. **Mon `verify` entier de developpeuse prend sa place dans la file** du nombre de `verify` simultanes (regle (1) de la section 14), comme celui des autres ; je ne le double pas par un `verify` de fusion sous mon autre casquette.
5. **Retard assume** : daily, routage et fusions de A et de B se font entre deux lots de A', pas pendant. Si un obstacle bloque `marc-dev` ou `lea-dev` pendant un lot, il est route a `camille-po` ou `theo-archi` sans attendre ma casquette de Scrum Master, ou je reprends la main a la fin du lot. **La charge de `lea-dev` (B, jusqu'a trois lots) et la mienne (A', jusqu'a trois lots) ne sont pas mesurees** ; le palier 3 n'a mesure ni l'une ni l'autre.
6. **Reste a l'utilisatrice** : si elle juge que 2 a 4 ne suffisent pas pour une branche dont l'auteure fusionne, elle l'ecrit ; je ne tranche pas mon propre controle.

## Amendement du 2026-10-21 22:16 (UTC+02:00), ouverture du palier 4 : ce que les sections 13, 14 et 15 ne disent plus

Annotation datee, heure lue par `date`, casquette Scrum Master. **Ce fichier est ecrit une seule fois ; ceci est un amendement date, non une reecriture** : les sections 13 a 15 restent telles qu'elles etaient a leur date. Arbre : `round-two` a `eca79363`. Cet amendement ne ferme pas le palier 3, que ce fichier a clos, et ne cree ni n'amende aucune `ADR` ni aucune `DEC`.

### 1. Section 14, *"Pour l'ouvrir"* : les quatre conditions de `D2`

| Condition | Ce que la section 14 disait | Etat a cette date |
|---|---|---|
| (a) ce fichier sur `round-two` | a faire | tenue : `git show HEAD:<chemin>` sort 0 |
| (b) arbitrage prealable de `nadia-prof`, chaque instance | *"il n'existe pas"* | **existe** : `arbitrage-nadia-palier-4.md` (`cae06461`, section 8 en `f197dc39`). Borne 8,0 maintenue au 18:32, puis composition recevable a quatre instances au 20:56 (8.4) ; `PAR-9` et la ligne de `PAR-8` pour quatre instances restent a elle (`DEC-0120` A.138) |
| (c) composition de `camille-po` | a faire | **faite** : `preparation-palier-4-camille.md` section 11, `DEC-0120` A.148, approuvee par l'utilisatrice (*"composition de palier 4 OK"*) |
| (d) table d'isolation | *"que j'ecris apres la composition"* | **faite** : `composition-palier-4.md` section 6 |

L'amendement de `planning.md` du 2026-10-21 22:13 ouvre le palier 4. **Les six regles de charge et de parallele** que la section 14 posait sans les ecrire (*"Ce qui est a moi pour la composition"*) sont ecrites dans `composition-palier-4.md` : nombre de `verify` entiers simultanes (`RF1`), reprise apres un arret (`RF9`), lignes d'isolation par agent lance (section 6), lignee avant fusion (`RF10`), allocation avant fusion (`RF8`), un amendement de `planning.md` par etat des postes (`RF11`).

### 2. Section 13, ce que les reponses de l'utilisatrice ferment

| Point | Etat a cette date |
|---|---|
| (a) `QP6` | `lea-dev` sur B, `sofia-sm` sur A' : composition ecrite, `RG2` tenue, aucun report pour ce motif |
| (d) `QP3` | la reponse *"un tableau d'informations"* est precisee par *"QP3 c'est une grille déssinée"*, puis par *"QP3 plafond accepté"* et *"LP9 oui on peut augmenter le poids des dessins"* : forme (a), plafond `en cours`, `LP9` engageable sous conditions (`DEC-0120` A.141 a A.149) |
| (e) la lecture de la consigne sur le seuil | **tranchee par ses mots** : *"1 quatre instances"*, soit 20,0 = quatre instances de 5,0 (`DEC-0120` A.138). La ligne *"seuil du palier 4 : sans reponse, ouvert"* de l'amendement de 2026-10-21 (20:59) et son conflit de 15,0 pour trois instances sont **leves**. Correction de `nadia-prof` a lire avec : le palier 3 n'a eu **aucune instance pleine** (A 4,0 sur 4,0 composes, A' 1,5 sur 2,0, B 1,5 sur 2,0 ; plafond de 5,0 chacune) ; ce qui s'est rempli est sa fenetre, 7,75 sur 8,0 (`arbitrage-nadia-palier-4.md` 8.4) |
| (f) les quatre decisions de `cloture-palier-2.md` section 13 | **inchangees** : `grep -c setup-node` et `grep -c setup-python` sur `ci.yml` rendent 0, `retro.md` de s57 n'existe pas ; je n'ai modifie ni `ci.yml` ni `outils/mvn-jdk21-test.sh` |
| (i) `s57-ines-p3-pli` et `recreo-ines-integ-pli` | **fait constate, decision non lue** : `git branch --list 's57-*'` rend 0 et `git worktree list` rend 2 lignes ; la branche et les deux worktrees de la section 13 (i) n'existent plus. Je ne sais pas qui les a supprimes ni sur quelle decision ; le commit `e4d54795` annoncait leur suppression prochaine |

### 3. Section 15, obstacles

Un obstacle n'est clos que quand le fichier qui le tranche existe. Relu par `grep -n "^### A.13[4-8]"` sur `DEC-0120` : les titres de `A.134` (`QP6`), `A.135` (`QP5`), `A.136` (`QP7`), `A.137` (`QP3`) et `A.138` (le seuil) existent.

| Obstacle | Etat |
|---|---|
| `QP3`, `QP5`, `QP6` | **clos** : `DEC-0120` A.134, A.135, A.137, precisees par A.141 a A.149 pour `QP3` |
| `QP7` | **repondu sans decision, clos sur ce point** : `DEC-0120` A.136, la cloture de s57 est reposee a la cloture du palier 5 |
| Lecture de la consigne sur le seuil | **clos** : `DEC-0120` A.138 |
| `E5` | clos (`squad/README.md` section 2 bis, amendement du 20:59) |

**Reste ouvert, a l'ouverture du palier 4** : les points `AP1` a `AP6` de `composition-palier-4.md` section 9, et `OV1` a `OV10` de la section 8, dont **les rapports de developpeur `marc-dev` et `lea-dev` du palier 3**, que la section 9 (point 9) disait absents et qui le sont toujours. L'amendement de `CLAUDE.md` sur le CE1 (`DEC-0123` 5.4) n'est pas fait.

### 4. Decisions prises

**Aucune `ADR` ni aucune `DEC` creee ou modifiee.** Cet amendement ne modifie ni prix, ni lot, ni instance, ni table d'isolation. Les verifications de son commit sont nommees dans l'amendement de `planning.md` du 2026-10-21 22:13, section 12.
