# [Sprint s57] Avis de `nadia-prof` à la clôture du sprint

- **Nature** : avis de sprint, pas un verdict de lot. Je ne prononce ici **aucun Done neuf, aucun refus, aucune levée de réserve** : un verdict ou une levée s'écrit dans la fiche du jeu, et ce geste n'en touche aucune. Ce fichier est le seul que j'écris.
- **Contexte** : l'utilisatrice a décidé le 2026-10-29 de clore s57 (décision relayée par l'agent lanceur, que je n'ai pas lue de sa main). La review demande mon avis et le Done pédagogique du sprint.
- **Arbre lu** : `round-two` à `a3e05f13`. `git diff --name-only d9313f9e a3e05f13 -- adapters domain application bootstrap pom.xml outils .github` ne rend rien : le code et le contenu servis sont ceux de `d9313f9e`.
- **Sources** : `cloture-palier-1.md` à `cloture-palier-5.md` de ce dossier, les blocs de validation des fiches de `squad/conformite/`, `docs/backlog/backlog.md`, `squad/programme/MATRICE-couverture.md`. Plus ce que j'ai joué ce jour, section 4.

## 1. Verdicts du sprint, tels qu'ils sont écrits

Je recopie l'état final écrit, je ne le réécris pas. "Hors `PAR`" veut dire : poste sans contenu servi, sans verdict pédagogique de ma main, et ce n'est ni un report ni un glissement.

### 1.1 Paliers 1 à 4

| Palier | Poste | Verdict écrit | Fiche ou fichier |
|---|---|---|---|
| 1 | 3, `FRA-CE1-13` | VALIDE SOUS RÉSERVE (`VSJ-CE1-R1` levée, `VSJ-CE1-R2` neuve), puis au palier 2 `VSJ-CE1-R2` levée, **VALIDE** | `FICHE-bandit-manchot-verbe-sujet-ce1.md` |
| 1 | 4, `FRA-CE1-10` | VALIDE SOUS RÉSERVE (`TPH-CE1-R1`), puis au palier 2 réserve levée, **VALIDE** | `FICHE-bandit-manchot-phrases-types-ce1.md` |
| 1 | 5, `FRA-CE1-15` | VALIDE SOUS RÉSERVE (`GNA-CE1-R1`), puis au palier 2 réserve levée, **VALIDE** | `FICHE-bandit-manchot-accord-groupe-nominal-ce1.md` |
| 1 | 6, `SCI-CM1-20` | **VALIDE** ; au palier 2, `N1` et `N2` pris en compte, ligne non rouverte | `FICHE-bandit-manchot-sciences-extraire-information-cm1.md` |
| 1 | 7, `FRA-CE1-04` | VALIDE SOUS RÉSERVE (`RCT-CE1-R1`), puis au palier 2 réserve levée, **VALIDE** | `FICHE-bandit-manchot-texte-narratif-ce1.md` |
| 1 | 8, `ART-CP-10` | refus du 2026-10-11 levé, VALIDE SOUS RÉSERVE (`RDO-CP-R3`), puis au palier 2 réserve levée, **VALIDE** | `FICHE-bandit-manchot-rapprocher-deux-oeuvres-cp.md` |
| 1 | 9, branche B2 | pas de verdict propre : l'entrée est jouée par le rejeu du poste 8 | `rejeu-nadia-b2-palier-1.md` |
| 2 | p2-3, `FRA-CE1-05` | **REFUSÉ** le 2026-10-16, corrigé, rejoué : **VALIDE** | `FICHE-bandit-manchot-pronoms-ce1.md` |
| 2 | p2-4, `FRA-CE1-24` | **VALIDE** au premier passage | `FICHE-bandit-manchot-familles-de-mots-ce1.md` |
| 2 | p2-2, marges de rejeu ; p2-6 ; p2-7 | marges non entamées ; p2-6 hors `PAR` ; p2-7 hors `PAR`, jugé au rejeu de p2-8 | `cloture-palier-2.md`, section 1 |
| 3 | p3-5, `FRA-CE1-06` | **VALIDE SOUS RÉSERVE** (réserves de pli et mot à trait d'union coupé) | `FICHE-bandit-manchot-inference-ce1.md` |
| 3 | p3-6, `FRA-CE1-07` | **REFUSÉ**, levé le même jour : **VALIDE** | `FICHE-bandit-manchot-texte-documentaire-ce1.md` |
| 3 | p3-7, `FRA-CE1-11` | **VALIDE SOUS RÉSERVE** | `FICHE-bandit-manchot-negation-ce1.md` |
| 3 | p3-8, `MAT-CE1-01` | **VALIDE**, plafond `en cours` | `FICHE-bandit-manchot-nombres-jusqu-a-1000-ce1.md` |
| 3 | p3-9, `EMC-CE1-01` | **VALIDE SOUS RÉSERVE** | `FICHE-bandit-manchot-biens-communs-ce1.md` |
| 3 | saisie sans correction, `FRA-CP-09` | mon refus levé **par l'arbitrage de l'utilisatrice**, pas par moi ; réserve neuve non bloquante sur les mots proposés par le clavier | `arbitrage-nadia-q05-q10.md`, sections 7 et 8 |
| 3 | correctif de gabarit du pli | veto : `b383b0bb` recevable, `edf7f344` **REFUSÉ** | `veto-nadia-gabarit-pli.md` |
| 3 | p3-2 ; p3-3 ; p3-4 ; pile de polices ; commentaires de paquet | marges non entamées ; les quatre autres hors `PAR` | `cloture-palier-3.md`, section 1 |
| 4 | `LP2`, `MAT-CE1-10` | VALIDE SOUS RÉSERVE, puis au rejeu **VALIDE** | `FICHE-bandit-manchot-masses-contenances-ce1.md` |
| 4 | `LP4`, `EMC-CE1-02` | **REFUSÉ** trois fois, puis au rejeu 4 **VALIDE SOUS RÉSERVE** | `FICHE-bandit-manchot-regle-sanction-ce1.md` |
| 4 | `LP1`, `FRA-CE1-21` | **REFUSÉ** deux fois, puis au rejeu 3 **VALIDE SOUS RÉSERVE** | `FICHE-saisie-mots-invariables-ce1.md` |
| 4 | `LP3`, `MAT-CE1-11` moitié calendrier | **REFUSÉ**, puis au rejeu **VALIDE SOUS RÉSERVE** | `FICHE-bandit-manchot-calendrier-dates-ce1.md` |
| 4 | `LP5`, `EMC-CE1-03` | **REFUSÉ**, puis au rejeu **VALIDE SOUS RÉSERVE** | `FICHE-bandit-manchot-droit-devoir-ce1.md` |
| 4 | `LP6`, `EMC-CE1-04` | **REFUSÉ** deux fois, puis au second rejeu **VALIDE SOUS RÉSERVE** ; réserve 3 levée ensuite | `FICHE-bandit-manchot-interet-commun-ce1.md` |
| 4 | `p4-10` | hors `PAR` | `cloture-palier-4.md`, section 1 |

### 1.2 Palier 5

| Poste | Verdict écrit | Réserves écrites | Fiche |
|---|---|---|---|
| `QLM-CE1-01` | **VALIDE** (2026-10-27) | aucune | `FICHE-ce1-qlm-01.md` |
| `QLM-CE1-02` | **VALIDE SOUS RÉSERVE** (2026-10-27) | `RAL-CE1-R1` | `FICHE-ce1-qlm-02.md` |
| `QLM-CE1-03` | **VALIDE SOUS RÉSERVE** (2026-10-27) | le mot *plus* de la tâche privation (gardée, *"on ne corrige pas"*) ; la liste des non-besoins | `FICHE-ce1-qlm-03.md` |
| `QLM-CE1-04` | **VALIDE** (2026-10-27) | aucune | `FICHE-ce1-qlm-04.md` |
| `QLM-CE1-05` | **VALIDE SOUS RÉSERVE** (2026-10-27) | `ETE-CE1-R1`, `ETE-CE1-R2`, `ETE-CE1-R3` | `FICHE-ce1-qlm-05.md` |
| `QLM-CE1-06` | **VALIDE SOUS RÉSERVE** (2026-10-27) | réserve 1, le plus court gagne en tâche B ; réserve 2, l'entrée de catalogue | `FICHE-ce1-qlm-06.md` |
| `QLM-CE1-08` | **VALIDE SOUS RÉSERVE** (2026-10-27) | `DEL-CE1-R1` | `FICHE-ce1-qlm-08.md` |
| `QLM-CE1-10` | **VALIDE SOUS RÉSERVE** (2026-10-27) | le premier nom de l'énoncé des items A | `FICHE-ce1-qlm-10.md` |
| `QLM-CE1-12` | **VALIDE SOUS RÉSERVE** (2026-10-27), inchangé par l'ajout daté du 2026-10-29 (accord corrigé, entrée écrite) | `V1`, repère de fiche | `FICHE-ce1-qlm-12.md` |
| `MAT-CE1-11`, horloge | **VALIDE SOUS RÉSERVE** (2026-10-28) | `a`, `c`, `d`, lettres locales à mes textes | `FICHE-ce1-lire-l-heure.md` |

**Postes engagés au palier 5 sans verdict de ma main** : les entrées de catalogue (`S57-QLM-CE1-ENTREES-CATALOGUE`, A.287) et le relevé de l'accord de `-12` (`S57-QLM-CE1-12-ACCORD`, A.290). Je les ai lus (titres et consignes avant l'entrée, diff de l'accord), je ne les ai pas jugés par un verdict : ce ne sont pas des verdicts manquants. `LP7`, `LP8` et `LP9` ne sont pas engagés ; leur compte de reports est à `sofia-sm`, que `cloture-palier-5.md` section 5 dit non relu. **Aucun poste engagé n'attend de verdict de ma part** à ma lecture ; si `sofia-sm` en compte un, c'est un report ou un glissement qu'elle écrit, je ne le comble pas ici.

## 2. Réserves pédagogiques ouvertes à la clôture

Toutes non bloquantes. Les stories nommées sont `NON ENGAGEE, sans palier` dans `docs/backlog/backlog.md`, par la réponse de l'utilisatrice à `Q-283-1` (`O2`, `DEC-0120` A.286). Des quatorze stories de l'annotation A.283, deux ont été engagées et faites depuis (entrées de catalogue et accord de `-12`) ; les douze autres restent non engagées.

| Réserve | Jeu | Story | Propriétaire |
|---|---|---|---|
| `RAL-CE1-R1` | `-02` | `S57-RAL-CE1-R1` | `camille-po`, puis ma lecture |
| mot *plus*, tâche privation | `-03` | aucune, *"on ne corrige pas"* (utilisatrice) | moi seule, gardée en jouant |
| liste des non-besoins | `-03` | `S57-BPL-CE1-1-REVISION` | `camille-po`, puis ma lecture |
| `ETE-CE1-R1`, indice sous le pli | `-05` | `S57-ETE-CE1-R1` | cause à `theo-archi` et `ines-design` |
| `ETE-CE1-R2`, mot coupé à son trait d'union | `-05` | `S57-ETE-CE1-R2` | cause à `theo-archi` et `ines-design` |
| `ETE-CE1-R3`, indices de la vapeur d'eau | `-05` | `S57-ETE-CE1-R3` | `camille-po` et le développeur, puis ma lecture |
| réserve 1, le plus court gagne | `-06` | `S57-QLM-CE1-06-LISTE-D-OBJETS` | `camille-po` |
| réserve 2, entrée de catalogue | `-06` | faite par A.287 | **levée à écrire par moi** dans la fiche (section 4.3) |
| `DEL-CE1-R1` | `-08` | `S57-DEL-CE1-R1` | `camille-po` |
| premier nom de l'énoncé, items A | `-10` | `S57-QLM-CE1-10-ORDRE-DES-MOTS` | `camille-po` |
| `V1`, rendu des propositions longues | `-12` | `S57-QLM-CE1-12-V1-MESURE` | `camille-po` ; la mesure de ce jour est en section 4.2 |
| réserve `a`, l'intrus d'une autre forme toujours faux | horloge | aucune écrite à ma connaissance | `camille-po` |
| réserve `c`, stratégie par forme des minutes, 10 sur 12 | horloge | aucune ; question ouverte par `cloture-palier-5.md` section 12 | `camille-po` |
| réserve `d`, entrée de catalogue | horloge | faite par A.287 | **levée à écrire par moi** dans la fiche (section 4.3) |
| défilement à 360 des items *ordre* (mesure, pas réserve de lot) | `-01` | `S57-QLM-CE1-01-DEFILEMENT-360` | `camille-po`, `ines-design` |
| *"Bravo !"* sans point, *"Bravo, c'est juste !"* à zéro trouvé seul | mécanique commune | `S57-MECA-BRAVO-SANS-POINT`, `S57-MECA-FIN-DE-PARTIE-BRAVO-ZERO` | `theo-archi` pour la mécanique, `camille-po` |

Les réserves ouvertes des paliers 3 et 4 restent celles de leurs fiches (section 1.1) et de leurs stories `S57-EMC-CE1-02-R1`, `S57-EMC-CE1-03-R1`, `S57-EMC-CE1-04-R1`, `S57-PLI-MOTS-INVARIABLES-REVELATION`, `S57-SAISIE-MOTS-PROPOSES-PAR-LE-CLAVIER`, `S57-PLI-LP3-LP5-MESURE`, toutes `NON ENGAGEE`. Je ne les ai pas relues une par une pour cet avis : je ne dis pas qu'elles sont toutes encore vraies à l'écran.

## 3. Ce que je certifie, et ce que je ne certifie pas, pour la version CP, CE1, CM1

**La couverture.** La commande de la "Méthode du compte" de `MATRICE-couverture.md`, lancée par moi à `a3e05f13` : `cv 166`, `ec 6`, `nc 30`, `ncb 75`, total 277. 166 / 202 = 82,18 % [calcul]. **Ce chiffre compte des lignes `couvert-valide`, il ne mesure pas la qualité d'un jeu** : une ligne y entre avec un VALIDE comme avec un VALIDE SOUS RÉSERVE, et huit des dix lignes du palier 5 y sont entrées sous réserve.

**Je certifie** : les verdicts de la section 1 sont les miens, écrits dans leurs fiches, et je n'en retire aucun. Pour les dix lignes du palier 5, la compétence annoncée est celle que le jeu travaille, l'exactitude des bonnes réponses et la plausibilité des distracteurs ont été vérifiées sur les paquets, et ce que j'ai joué ce jour (section 4) ne rouvre aucun de ces jugements. **Je ne certifie pas les 166 lignes une par une** : celles qui étaient couvertes avant s57 le sont par leurs fiches, que je n'ai pas relues pour cet avis.

**Je ne certifie pas** :

1. Que les réserves ouvertes de la section 2 sont sans effet sur l'enfant : elles sont non bloquantes, pas nulles.
2. Le rendu à l'écran hors de 744 x 551 pour neuf des dix jeux du palier 5 ; la mesure à 360 de large n'est faite que pour `-12`, ce jour.
3. Le Done technique : je n'ai lancé aucun `verify` pour cet avis, et la CI GitHub n'est lue par personne (`cloture-palier-5.md`, section 6, question 1). Les fiches de `-03` et de `-10` en font une condition de leur verdict ; si cette CI rougit sur l'un d'eux, le verdict concerné est rouvert, comme elles l'écrivent.
4. Les 36 lignes non couvertes, `en cours` ou `non couvert` : rien de cet avis ne les juge.

**Ce qu'un enfant trouve aujourd'hui à l'écran pour les dix entrées du palier 5**, joué ce jour (section 4) : sur la page *Questionner le monde* du CE1, neuf jeux qui n'existaient pas au début du palier ; sur la page *Maths* du CE1, l'horloge en dernière tuile. Chaque jeu se lance, se joue en 10 manches, dit *"Ce n'est pas ça. Essaie encore !"* avec un indice à la première erreur, nomme la bonne réponse quand l'enfant persiste, et se termine. Ce qui ne va pas, l'enfant le voit sur un écran de 744 x 551 après une erreur ou à la révélation : un retour coupé ou caché sous le pli, un mot coupé à son trait d'union (section 4.2).

**Ce dont je reste la seule garde**, aucun test ne les tient : tout ce que chaque fiche du palier 5 nomme sous ce titre, plus ce que la section 4 a mesuré à la main ce jour, le pli à la révélation et la coupure des mots à trait d'union sur tous les jeux du palier. Aucun garde-fou ne mesure un état d'écran après une erreur.

## 4. Les dix entrées réelles, jouées ce jour

### 4.1 Comment j'ai joué, et ce que cela ne montre pas

- **Jar** : celui que l'agent lanceur m'a donné, construit par le dernier `verify` complet à `d9313f9e` (`BUILD SUCCESS`, 3644 tests, fait relayé, non relancé par moi), hors dépôt, non commité. `catalogue.yaml`, `matieres.yaml` et les dix paquets du jar sont égaux à ceux de `a3e05f13`, aux fins de ligne près (`sha256` après retrait des retours chariot : 12 fichiers sur 12). **Ce sont les entrées réelles**, pas des entrées de sonde.
- **Lancement** : `java -jar` sur un JDK 21, propriété `server.port` sur un port libre de la plage donnée, démarré à 11:39:39, arrêté **par son PID** à 12:09:52, port libre ensuite.
- **Parcours d'un enfant** : page de la matière du CE1, appui sur la tuile par son titre, puis la partie, dans un navigateur sans tête à 744 x 551, piloté par un script. Par entrée : une partie avec une erreur à chaque manche et une révélation, une partie juste du premier coup sauf une erreur, puis trois parties où chaque manche va jusqu'à la révélation (deux erreurs, puis un nouvel appui sur une proposition barrée). Pour `-12`, trois parties de plus à 360 x 640. **Tous les items de chaque paquet ont été servis** sur l'ensemble des parties. Cotes lues par `getBoundingClientRect()`, coupures de ligne lues caractère par caractère ; captures regardées pour une partie des états.
- **Ce que cela ne montre pas** : une tablette, un doigt, une vraie lenteur de lecture. Les clics sont ceux d'un script. À 360 de large, la page défile seule à l'arrivée : mes cotes à cette largeur ne disent rien du pli, je n'en retiens que les coupures de mots.

### 4.2 Ce que j'ai vu, entrée par entrée

**Les deux pages.** *Questionner le monde*, CE1 : neuf tuiles, exactement les neuf entrées, sous les titres du catalogue et dans son ordre ; la neuvième, *"Les générations et les moments de la vie"*, sur deux lignes, est à moitié sous le pli à 744 x 551 (526 à 612) : il faut défiler pour la lire en entier. *Maths*, CE1 : vingt tuiles, *"L'heure sur l'horloge"* en vingtième, juste après *"Les dates et le calendrier"*, sous le pli (866 à 922). C'est le défilement d'une liste, pas un défaut.

**Commun aux dix** : consigne affichée au-dessus de l'énoncé, *"Lis bien, puis choisis la bonne réponse."* pour les neuf de questionner le monde, *"Regarde les deux aiguilles, puis choisis la bonne heure."* pour l'horloge ; 10 manches (*"Tu as joué toutes les questions : 10"*) ; aucune manche répétée dans une partie ; retour faux *"Ce n'est pas ça. Essaie encore !"* avec la croix, l'indice et la proposition barrée ; révélation *"Ce n'est pas ça."* puis *"Appuie sur la bonne réponse :"* et la réponse ; écran de fin avec le nombre trouvé seul. À l'arrivée d'une manche, les trois propositions sont à l'écran sur les dix jeux (au pire 5 px de cadre sous le pli, `-12` item 8, texte lisible). Les deux comportements de mécanique déjà en story (section 2, dernière ligne) sont toujours visibles.

| Entrée | Titre à l'écran | Partie, erreur, révélation à 744 x 551 | Constat |
|---|---|---|---|
| `-01` | Grandir chez les animaux et les plantes | après une ou deux erreurs, tout le retour au-dessus du pli ; à la révélation, l'indice passe sous le pli sur les 4 items *ordre* (596) | aucun mot coupé |
| `-02` | Ce que mangent les animaux | tout au-dessus du pli, révélation comprise (indice au plus 530) | rien |
| `-03` | Ce qu'il faut à une plante | après une erreur, au-dessus du pli ; à la révélation, indice sous le pli sur 4 items (items 6, 9, 12, 14), réponse nommée sous le pli sur l'item 6 (590) ; item 11 non mesuré à la révélation | **item 11 : *"a-"* en fin de ligne, *"t-elle"* à la suivante** |
| `-04` | Les familles d'aliments | après une erreur, au-dessus du pli ; à la révélation, indice sous le pli sur 4 items (560 à 626) | rien d'autre |
| `-05` | Les trois états de l'eau | items 4, 7, 9 : *"Essaie encore !"* coupé (568), indice de l'item 9 sous le pli (580) ; à la révélation, réponse nommée sous le pli sur les items 4, 7, 8, 9 (582 à 630) | items 5, 7, 9 : *"passe-"* puis *"t-il"*, *"trouve-t-"* puis *"on"* |
| `-06` | Ce que l'aimant attire ou non | après une erreur, au-dessus du pli ; à la révélation, indice sous le pli sur 6 items | rien d'autre |
| `-08` | Les dangers de l'électricité | après une erreur, au-dessus du pli ; à la révélation, indice sous le pli sur les 12 items, réponse nommée sous le pli sur les items 6, 7, 11 (590) | une proposition de l'item 11 sur deux lignes, sans mot coupé |
| `-10` | Les paysages | tout au-dessus du pli, révélation comprise (indice au plus 494) | rien |
| `-12` | Les générations et les moments de la vie | **item 8, après une erreur : ni croix, ni *"Ce n'est pas ça"*, ni indice à l'écran** (capture ; bas de l'indice 630, du signal 648) ; item 9 : *"Essaie encore !"* coupé (568), indice sous le pli (580) ; à la révélation, réponse nommée sous le pli sur les items 3, 8, 9, 12 (582 à 710) | **item 8 : deux propositions coupées dans *"grands-"* puis *"parents"*** |
| horloge | L'heure sur l'horloge | après une ou deux erreurs, au-dessus du pli (indice 530, signal 548) ; **à la révélation, sur les 12 items, la réponse nommée est coupée par le bas de l'écran (562) et l'indice est dessous (616)** | rien d'autre |

**Ce qui tient, dans tous les cas** : à la révélation, la bonne proposition reste à l'écran, seule non barrée dans la machine. L'enfant bloqué peut toujours avancer. Ce qui manque, c'est de lire la réponse que l'écran lui nomme, et pour `-12` item 8, de savoir pourquoi la proposition s'est barrée.

**`V1` de `-12`, mesurée ce jour.** L'exigence de la fiche : *"a 744 x 551 et a 360 de large, aucune proposition coupee a l'interieur de arrière-grands-parents ou de grands-parents, et aucune reponse attendue sous le pli a la revelation"*. **Elle n'est pas tenue** : à 744, item 8, coupure dans *grands-parents* et réponse nommée sous le pli sur 4 items ; à 360, coupures dans *arrière-grands-parents* ou *grands-parents* sur les items 1, 3, 4, 6, 8 et 9. La fiche dit la suite elle-même : *"si elle montre une coupure, camille-po en fait une story"*. Je n'écris pas cette mesure dans la fiche par ce geste ; je l'y écrirai.

### 4.3 Constats, et routage proposé

Aucun n'est un refus, aucun ne rouvre un Done. Chacun nomme l'entrée, l'écran, ce que l'enfant voit, et à qui je le propose.

1. **`-12`, item 8, 744 x 551, après une erreur** : l'enfant voit sa proposition barrée et rien d'autre, ni croix, ni message, ni indice, sans défiler. Avec le point 2, c'est le fond de `V1`. Proposition : `camille-po` fait de `S57-QLM-CE1-12-V1-MESURE` une story de correction, cause à `theo-archi` et `ines-design` (longueur des trois suites de l'item 8 ou mise en page).
2. **`-12`, item 8, 744 x 551, à l'arrivée** : deux des trois propositions coupent *grands-parents* entre deux lignes. Même routage que le point 1.
3. **`-03`, item 11, 744 x 551** : *"De quoi d'autre a-"* en fin de ligne, *"t-elle besoin pour germer ?"* à la suivante. C'est la famille d'`ETE-CE1-R2`, sur un autre paquet. Mon audit de `-03` n'avait capturé que les items 6 et 12 à cette taille. Proposition : `camille-po` élargit `S57-ETE-CE1-R2` à `-03` ou ouvre une story sœur ; le texte, s'il change, repasse par le `relecteur-pedagogique`.
4. **Horloge, 744 x 551, à la révélation** : la réponse nommée est coupée à mi-hauteur et l'indice est sous le pli, sur les 12 items, parce que le cadran occupe le haut de l'écran. Proposition : `ines-design` pour la mesure, `theo-archi` pour la cause, `camille-po` pour la story ; même famille que `S57-PLI-MOTS-INVARIABLES-REVELATION`.
5. **Cinq jeux sur dix, 744 x 551, à la révélation** : réponse nommée sous le pli sur `-03` (1 item), `-05` (4), `-08` (3), `-12` (4) et l'horloge (12) ; indice sous le pli sur huit jeux. Seuls `-02` et `-10` tiennent tout au-dessus du pli. C'est le gabarit commun de la révélation plus qu'un paquet. Proposition : une story transverse à `camille-po`, cause à `theo-archi` et `ines-design`.
6. **`-05`** : `ETE-CE1-R1` et `ETE-CE1-R2` sont toujours vraies à l'écran, comme attendu, rien n'ayant changé dans le paquet. Rien à router de plus.
7. **Réserve 2 de `-06` et réserve `d` de l'horloge** : sur l'écran, l'entrée de `-06` porte la consigne *"Lis bien, puis choisis la bonne réponse."* et 10 manches, sous le titre *"Ce que l'aimant attire ou non"* que j'avais lu ; celle de l'horloge porte le titre et la consigne exigés, la mécanique `bandit-manchot` et 10 manches. La condition de chacune est atteinte à l'écran. **La levée n'est pas écrite ici** : elle s'écrit dans les deux fiches, par moi, dans un geste qui les touche. L'obstacle de `cloture-palier-5.md` section 12 reste donc ouvert.

**Correction de mon propre constat.** La fiche de l'horloge, point 5, écrit *"Rien de lisible n'est coupé"* à 744 x 551. C'était vrai des états mesurés ce jour-là, après une et après deux erreurs (indice à 530, cotes retrouvées identiques ce jour). **Je n'avais pas mesuré l'état de révélation**, où la réponse nommée est coupée (point 4). Même limite pour `-05`, dont la fiche mesure *"apres une erreur"* seulement. Je laisse les deux textes tels quels et je le dis ici.

## 5. Coût

Mes lectures de clôture et le jeu de ce jour ne sont chiffrés par aucune règle que j'aie lue. Je ne fixe aucun prix. Donnée brute, sans conversion : serveur ouvert de 11:39:39 à 12:09:52.

**Signé** : `nadia-prof`, s57, clôture, 2026-10-29.
