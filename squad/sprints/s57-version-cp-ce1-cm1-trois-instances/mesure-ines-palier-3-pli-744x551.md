# s57 - palier 3 : mesure du pli a 744 x 551 apres une erreur (FRA-CE1-07, et FRA-CE1-06 et EMC-CE1-01 que `nadia-prof` nomme aussi)

- **Auteure** : `ines-design`. **Date** : 2026-10-20, 09:13 (UTC+02:00), lue par `date`.
- **Arbre** : `round-two` a `7301cbbc68c34088cedd66da9f11ce0a80f6add9` (sommet au moment de la mesure, `git rev-parse round-two` relu apres la mesure : inchange), worktree dedie, branche `s57-ines-p3`. Aucun fichier de `domain`, `application`, `adapters/*/src/main`, `bootstrap` n'est touche, rien n'est commite.
- **Ce que ce fichier est** : la mesure que `lecture-nadia-palier-3.md` demande (4.1 point 4). Il compte des pixels. Il ne prononce aucun Done, ne dit pas si une reserve est recevable et ne decide rien : le veto est a `nadia-prof`.

## Resume

| Code | Constat | Section |
|---|---|---|
| PL-1 | **Les items 14 et 20 que `nadia-prof` cite pour le pli sont ceux de `FRA-CE1-06`** (sa section 1, ligne 67), pas de `FRA-CE1-07`. Pour `-07` elle nomme l'item 12 (sa section 2, lignes 89-90). Les trois paquets ont ete mesures en entier | 2 |
| PL-2 | **`-07`, 744 x 551, apres une erreur** : le bas de `.indice` est au plus a 551 sur **19 items sur 20**. Un seul le depasse : le **13** (582, soit 31 px, trois rangees de propositions). L'item 12 (4 phrases) tient : texte sur une ligne, bas de l'indice a **478** | 3, 4 |
| PL-3 | **`-06`, 744 x 551** : **5 items sur 20** depassent dans au moins un ordre des propositions : 14 (556 ou 630), 16, 19 et 20 (582 chacun), 17 (552 dans 2 ordres sur 6, soit 1 px). L'item 14 est le seul de 4 phrases ; l'item 20 compte **3 phrases** dans le texte rendu | 3, 4 |
| PL-4 | **`EMC-CE1-01`, 744 x 551** : **16 items sur 24** depassent dans au moins un ordre (de 15 a 45 px), dont 10 dans les six ordres | 4 |
| PL-5 | Ce qui descend l'indice n'est pas le nombre de phrases mais le nombre de rangees de propositions (74 px par rangee) et le nombre de lignes du texte (30 px par ligne). A 992 et 1200, **aucun item des trois paquets ne depasse** (bas de l'indice 508 au plus) | 4, 5 |
| PL-6 | 720, 744 et 768, avec ou sans barre, rendent les memes cotes, sauf trois items a 720 avec barre : `-07` 13 (612 au lieu de 582), `EMC` 15 (566 au lieu de 518) et 19 (596 au lieu de 548) | 5 |
| PL-7 | Un bouton de reponse depasse le pli dans un seul cas : `-06` 14, 556 dans 2 ordres sur 6, a 720, 744 et 768. Le chemin de sortie (`a.btn.lien-accueil`, `a.btn.chip-niveau`) finit a y = 66 dans les 2560 etats mesures (etat x largeur x mode x item). Aucun `.explication` n'est rendu dans les trois paquets | 3, 5 |
| PL-8 | Hash du texte et de l'item pour chacun des 64 items, avec la methode pour les recalculer : section 6 | 6 |

## 1. Arbre, jar, methode

- **Jar** : copie hors depot de `bootstrap/target/recreo.jar` de `7301cbbc` (`bash outils/mvn-jdk21.sh -B clean package -DskipTests`, sortie 0), SHA-256 `db1f441af79088a73c0868d68c34423c5d9fec8d18f903a06cde9eccda9ad287`. Feuille servie `/css/recreo.css`, SHA-256 `e1cd65c255a5142efc19c5cb616134b1d26bd17ffb401684e1e0a0ea2728cf41`. JDK 21.0.10, port 18671, PID 39964 lu a la creation du processus, arrete par ce PID (le port ne repond plus).
- **Methode** : `pilote-p3.mjs` (derive de `pilote-p2.mjs` et `pilote-pa8.mjs`, hors depot), Chrome 155 sans tete par le protocole devtools, Node 24. Un seul navigateur, a mon nom, profil jetable propre, port de debogage 19671. Chaque item est identifie par son enonce, son texte et ses propositions, puis joue : arrivee, une erreur (`echec1`, la premiere proposition fausse servie), une seconde erreur (`echec2`), revelation. A chaque etat, **sur le meme DOM**, a cinq largeurs (720, 744, 768, 992, 1200 ; hauteur 551 ; `mobile: false`, facteur 1) et dans deux modes (barre de defilement classique ; `html { scrollbar-width: none }` injecte a l'execution, comme dans la mesure PA8). A `echec1` et a la revelation, **les six ordres des trois propositions** : le serveur tire l'ordre au hasard et le repli en rangees en depend (methode de `cote-de-reference-forme-commune-2026-10-16.md`). Aucune regle de style n'est injectee : c'est la feuille servie seule.
- **Ce que "pli" veut dire ici** : y = 551 dans la page, hauteur de la fenetre emulee. Cote = `getBoundingClientRect().bottom + scrollY`. Critere lu : celui de `arbitrage-nadia-preparation-palier-3-ce1.md` 4.1 point 4, et celui de `cote-de-reference-forme-commune-2026-10-16.md` (bas de `.indice` au plus a 551). Je compte, je ne juge pas.
- **Ce que "bas du texte", "proposition" et "bouton" veulent dire ici** : *bas du texte* = bas de la derniere ligne de `.texte-support` (`Range.getClientRects`) ; *proposition* = bas de la plus basse des trois `.proposition`, qui sont les boutons de reponse (`button.btn.proposition`) ; les autres boutons et liens (sortie, niveaux) sont releves a part. Ce gabarit n'a pas de bouton "suivant" ni de bouton de revelation distinct : apres une erreur, l'enfant appuie sur une proposition.
- **Couverture** : `-07` 20 items sur 20 en 5 parties, `-06` 20 sur 20 en 5 parties, `EMC` 24 sur 24 en 8 parties, aucune anomalie (item non retrouve ou ambigu : 0).
- **Limites** : la proposition fausse cliquee est la premiere servie ; les cotes de l'indice ne dependent pas de laquelle. Une seule police (celle du poste), un seul navigateur, pas d'appareil reel. Le releve brut (JSON, 6 Mo environ) reste hors depot.

## 2. Ce que `nadia-prof` demande, lu dans son fichier

- 4.1 point 4 (`arbitrage-nadia-preparation-palier-3-ce1.md`) : *"a 744 x 551, apres une erreur, un indice ou une explication sous le pli est une reserve non bloquante, nommee et datee"*. 4.2 : pour `-06` et `-07`, *"un item a trois rangees qui finit sous le pli"* releve de ce point.
- Ligne 67 (section 1, `FRA-CE1-06`) : *"le pli a 744 x 551 sur les textes de 4 phrases (items 14, 20), apres une erreur"*. Lignes 89-90 (section 2, `FRA-CE1-07`) : *"Le pli sur le texte de 4 phrases (item 12)"*. Ligne 170 (section 5, `EMC-CE1-01`) : *"l'indice apres une erreur et le pli"*.
- **Ecart** : la consigne qui m'a ete transmise attribue les items 14 et 20 a `-07`. Dans son fichier ce sont ceux de `-06`. Je ne tranche pas : `-07`, `-06` et `EMC-CE1-01` sont mesures en entier. `-11` et `MAT-CE1-01` ne le sont pas : elle n'y demande pas le pli.
- **Phrases comptees dans le texte rendu** (coupure apres `.`, `!` ou `?` suivi d'une espace) : `-07` item 12 = 4 phrases ; `-06` item 14 = 4 phrases ; `-06` item 20 = **3 phrases** (*"Zoé revient de l'école avec une grande feuille. Elle la montre à tout le monde, les yeux brillants. Elle a une étoile en haut de la page."*). Aucun autre texte de `-06` ou `-07` n'a 4 phrases.

## 3. Les items nommes, a 744 x 551, apres une erreur

Mode sans barre, six ordres. Toutes les cotes sont en pixels.

| Item | Phrases | Lignes du texte | Bas du texte | Bas de la proposition la plus basse | Rangees | Bas de `.indice` | Par rapport a 551 |
|---|---|---|---|---|---|---|---|
| `-07` 12 (4 phrases) | 4 | 1 | 170 | 404 | 2 | **478** | 73 au-dessus |
| `-06` 14 (4 phrases) | 4 | 2 | 200 | 482 (4 ordres) ou 556 (2 ordres) | 2 ou 3 | **556** (4 ordres) ou **630** (2 ordres) | **5 ou 79 sous le pli**, dans les six ordres |
| `-06` 20 | 3 | 2 | 200 | 508 | 3 | **582** | **31 sous le pli** |
| `-07` 13 (seul item de `-07` sous le pli) | 3 | 2 | 200 | 508 | 3 | **582** | **31 sous le pli** |

- `-06` 14, ordre servi : signal de 506 a 582, indice de 506 a 556, verdict de 514 a 574. Le haut de l'indice est au-dessus du pli, son bas le depasse de 5.
- `-06` 20 et `-07` 13, ordre servi : signal de 532 a 608, indice de 532 a 582, verdict de 540 a 600. Le haut de l'indice est 19 au-dessus du pli, son bas 31 au-dessous.
- `-06` 14 : les trois propositions font 335,8 ; 328,3 et 680 de large (ordre servi). *"pour qu'il ne soit pas mouillé"* prend une rangee entiere ; selon l'ordre, le meuble tient sur 2 ou 3 rangees. Dans les 2 ordres a 3 rangees, **le bouton de la troisieme rangee finit a 556** : c'est le seul cas des trois paquets ou un bouton de reponse depasse le pli apres une erreur (720, 744 et 768).
- `-06` 17 : meme mecanique, 2 ordres sur 6 a 3 rangees, indice a 552 (1 px sous le pli), bouton a 478.

## 4. Tous les items, 744 x 551, mode sans barre, apres une erreur

Colonnes : phrases du texte rendu ; lignes du texte ; bas du texte ; bas de la proposition la plus basse (minimum-maximum sur les six ordres) ; rangees ; bas de `.indice` (idem) ; position par rapport a 551. *au-dessus* = bas de l'indice au plus a 551. *autres* = largeur ou mode dont la cote differe de celle-ci (les non listes sont identiques).

### FRA-CE1-07, `ce1-texte-documentaire`

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 2 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 3 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 4 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 5 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 6 | 2 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 7 | 2 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |
| 8 | 3 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |
| 9 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 10 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 11 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 12 | 4 | 1 | 170 | 404-404 | 2-2 | 478-478 | au-dessus |
| 13 | 3 | 2 | 200 | 508-508 | 3-3 | 582-582 | sous le pli, tous ordres (+31 au pire) ; autres: 720-barre 508-508 / 612-612 |
| 14 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 15 | 3 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |
| 16 | 3 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |
| 17 | 3 | 1 | 170 | 404-404 | 2-2 | 478-478 | au-dessus |
| 18 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 19 | 3 | 1 | 170 | 404-404 | 2-2 | 478-478 | au-dessus |
| 20 | 3 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |

### FRA-CE1-06, `ce1-inference`

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 2 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |
| 2 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 3 | 3 | 1 | 170 | 404-404 | 2-2 | 478-478 | au-dessus |
| 4 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 5 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 6 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 7 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 8 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 9 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 10 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 11 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 12 | 3 | 2 | 200 | 360-360 | 1-1 | 434-434 | au-dessus |
| 13 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 14 | 4 | 2 | 200 | 482-556 | 2-3 | 556-630 | sous le pli, tous ordres (+79 au pire) |
| 15 | 2 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 16 | 3 | 2 | 200 | 508-508 | 3-3 | 582-582 | sous le pli, tous ordres (+31 au pire) |
| 17 | 2 | 1 | 170 | 404-478 | 2-3 | 478-552 | sous le pli a 1 px dans certains ordres |
| 18 | 3 | 1 | 170 | 404-404 | 2-2 | 478-478 | au-dessus |
| 19 | 3 | 2 | 200 | 508-508 | 3-3 | 582-582 | sous le pli, tous ordres (+31 au pire) |
| 20 | 3 | 2 | 200 | 508-508 | 3-3 | 582-582 | sous le pli, tous ordres (+31 au pire) |

### EMC-CE1-01, `ce1-emc-biens-communs` (aucun texte-support : pas de phrase, pas de ligne de texte)

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 0 | - | - | 444-444 | 3-3 | 548-548 | au-dessus |
| 2 | 0 | - | - | 418-492 | 2-3 | 492-566 | sous le pli a 15 px dans certains ordres |
| 3 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 4 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |
| 5 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 6 | 0 | - | - | 418-492 | 2-3 | 522-596 | sous le pli a 45 px dans certains ordres |
| 7 | 0 | - | - | 370-444 | 2-3 | 444-518 | au-dessus |
| 8 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 9 | 0 | - | - | 418-492 | 2-3 | 492-566 | sous le pli a 15 px dans certains ordres |
| 10 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 11 | 0 | - | - | 418-492 | 2-3 | 522-596 | sous le pli a 45 px dans certains ordres |
| 12 | 0 | - | - | 418-492 | 2-3 | 522-596 | sous le pli a 45 px dans certains ordres |
| 13 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 14 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 15 | 0 | - | - | 444-444 | 3-3 | 518-518 | au-dessus ; autres: 720-barre 492-492 / 566-566 |
| 16 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 17 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 18 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |
| 19 | 0 | - | - | 444-444 | 3-3 | 548-548 | au-dessus ; autres: 720-barre 492-492 / 596-596 |
| 20 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 21 | 0 | - | - | 418-492 | 2-3 | 522-596 | sous le pli a 45 px dans certains ordres |
| 22 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) ; autres: 720-barre 492-492 / 596-596 |
| 23 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 24 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |

## 5. Largeurs et modes

Nombre d'items dont le bas de `.indice` depasse 551 apres une erreur, **dans au moins un des six ordres** (entre parentheses : dans les six).

| Largeur, mode | `-07` (20) | `-06` (20) | `EMC` (24) |
|---|---|---|---|
| 720, barre | 1 (1) | 5 (4) | 18 (12) |
| 720, sans barre | 1 (1) | 5 (4) | 16 (10) |
| 744, barre | 1 (1) | 5 (4) | 16 (10) |
| 744, sans barre | 1 (1) | 5 (4) | 16 (10) |
| 768, barre | 1 (1) | 5 (4) | 16 (10) |
| 768, sans barre | 1 (1) | 5 (4) | 16 (10) |
| 992, les deux modes | 0 | 0 | 0 |
| 1200, les deux modes | 0 | 0 | 0 |

- Valeur maximale du bas de l'indice, par paquet, de 720 a 768 : `-07` 612 (item 13, 720 avec barre ; 582 ailleurs), `-06` 630 (item 14), `EMC` 596. A 992 : 478, 508 et 492. A 1200 : 478, 508 et 444.
- La largeur utile (`clientWidth`) avec la barre classique depend de la hauteur du document : releve a 720, `-07` item 1 = 720, `EMC` item 1 = 705. Les cotes different de celles du mode sans barre pour trois items seulement (PL-6).
- Revelation (hors du critere lu en 4.1 point 4, donnee pour information) : a 744, mode sans barre, le bas de `.indice` depasse 551 dans au moins un ordre pour 9 items de `-07`, 14 de `-06` et 23 de `EMC`.
- Seconde erreur (`echec2`), 744, sans barre, ordre servi : 1 item de `-07`, 4 de `-06`, 13 de `EMC` ont le bas de l'indice au-dela de 551.

## 6. Hash des items mesures, et ce que dit un hash different

Cette mesure ne vaut que pour le texte, l'enonce, les propositions, l'indice **et** les gabarits et la feuille de style qu'elle a rencontres. Le rendu d'un item depend de ses trois propositions (largeur des boutons, donc rangees) et de son indice autant que de son texte : le hash du texte seul ne suffit pas, le hash de l'item est donc donne aussi.

- **Hash du texte** : SHA-256 du champ `texteSupport` tel que le DOM le rend (UTF-8, sans autre transformation). Il est egal, pour les 40 textes de `-06` et `-07`, au SHA-256 du champ du YAML (verifie, 0 ecart). `EMC` n'a pas de texte-support.
- **Hash de l'item** : SHA-256 de la chaine UTF-8 de `[enonce, texteSupport, bonneReponse, distracteurs tries, indice]` serialisee en JSON compact sans echappement ASCII (en Python : `json.dumps(l, ensure_ascii=False, separators=(',', ':'))`), les valeurs etant celles du YAML lu (`texteSupport` vaut `null` pour `EMC`). Recalcule en Python independamment du pilote (Node) sur les 64 items : 0 ecart.
- **Un hash d'item inchange et ni les gabarits ni la feuille modifies** : la cote de cet item reste celle de la section 4. **Un hash d'item different** : la cote de cet item est a refaire. **`git diff --stat 7301cbbc <nouveau-sommet> -- adapters/adapter-web/src/main`** non vide : toutes les cotes sont a refaire, quel que soit le hash.
- **Etat du YAML a `7301cbbc`** (blobs, pour `git diff` rapide) : `ce1-texte-documentaire.yaml` `a61c9a2ab1d88bbd5415bf155c10761c88fb0ed7` ; `ce1-inference.yaml` `7eb3b08f7e34697537654f4f205afa6b20fd4455` ; `ce1-emc-biens-communs.yaml` `766f77519f149a16e3c7ed2da251d20dcf98dc10` ; `recreo.css` `7cff4fd3751a6184ebe8391e017bd0b082fbfff1`.
- **Les corrections que `nadia-prof` demande touchent** : `-07` les items 1, 2, 3, 7, 9, 10, 12, 13, 14, 16, 18 ; `-06` l'item 19 ; `EMC` les items 1 a 12, 14, 15, 17, 20, 21 (sa lecture, sections 1, 2 et 5). Items sous le pli a 744 hors de ces listes, donc dont la cote ne change que si un correcteur y touche : `-06` 14, 16, 17, 20 ; `EMC` 13, 16, 22, 23, 24. Items de la section 3 : `-07` 12 et 13 sont dans la liste, leur cote est a refaire apres correction.
- **Rangees** : un distracteur ou une bonne reponse plus long ou plus court d'un item peut faire passer le meuble de 2 a 3 rangees (74 px) : la correction d'une proposition peut changer la cote meme quand le texte ne bouge pas.

#### Hash, FRA-CE1-07
| Item | SHA-256 du texte | SHA-256 de l'item |
|---|---|---|
| 1 | ce939573e83e176f3a9a9a1e82e1498df5edfeb7ca7f5ee4d9bc8d1b931c9ba1 | 000081b5ed18352bb04229cf3125d0b2dcc5aa93b642e398a103be2367bba5d3 |
| 2 | b3b7c88332fc12cf3161c39dbeee32f69fe721d797191d797cae80340edec0ae | b21dfb9aa147c0805a594f6250e31b26d7e8a9ecd2f8b6a09c73c4a311eceaa2 |
| 3 | 8f12c5bc66af5644d7f9186ef65fff02b01d33e41f890010e1384de54733b6ac | a3ca3ea182e223527d66b7e7f539c22e9c17ddfb9763ebf1f8297188184a1f78 |
| 4 | f6b0f97749dbf51d40899fbcd935d47406dd3e8caaafb5b75ad0fe427481c6d0 | 1e432c0b17f413ede2cefdeb8779fe086251e78bd5e05e7ffedf1251d12a9741 |
| 5 | 4498635b71cb747b8479136e236a8bfb2ad9888843f3f2d697e3b6c5420d8551 | 81329493bf039cc7325d2a2cd6acc9388df0a3b1172c4801f2ddf894da50b96c |
| 6 | f22f03ecd377bd9e09c6591349295f69b6611de1bd909c0ca74f38afd1915822 | 3ab6ac734eb03c26860a90397771fc65c9a72e1e07e84f7afb7e0bf6bd106a01 |
| 7 | d93c46901af8eff4c84eb6741f5d57e5b840710ab3c982d255da0f39ab1b4c95 | e87fe572b81e5243be302b0b997a0c6919e810948d48dcb2ecff25dce263c63d |
| 8 | d0c8699aa6fc5844444661779ef05b2f6ffa8cdd8d5e086f0bcf485ff0d70848 | 5fd32c700aebf2fc616be0c919b13e44cedd365e1d82a675a3b02e041260fc81 |
| 9 | 6f59a6fb7cd1ecdb2d2dfe2f2080a71df7c69d372dd47f4c0ea692610945df10 | 49f6ea63372373290474f8550839462e9041178169eec3c4e96a3aeb88124414 |
| 10 | 7eb5d4414ff7d7ab1e716f30dabeef0dba37d699baea37953bfb6dd7de9014de | 14ff725d4cdcb78507c4ba04ef6eeac31b79cd2954514183e76ed01b4a801f32 |
| 11 | c3fcf6e9911daf64cc5394c3ec1a7681c28d49392a35e7fb8f0e21214acd419a | 7be46ff2beb16e5cc70ee5181b6e3fc9308d368d662f15b0ebe8acc82ca0022a |
| 12 | e81cfdf42f26690b15f78148602184f34014b6175cde1ec1c6c4858b90d000fe | c8da320ab706c854008c3103bbd31d6010bc4be7393d11db947355c0b48180ac |
| 13 | 90bc24b898b075005ca76429b3ad52c1cfb9f110cd1c5c7b73fe6e372865f91c | 3313f61143ea52da6f3852a49ec2f9fc30f07c9ff147a534db4062c8de6dd275 |
| 14 | 4033d8f0b17773fbb42086a4c8871b93e95ec89da64cd93f8a67a5ea16daca9d | ad93f5906e27581ca773a0284d86cba379eb8f0b5d2f15e9812fdb1f176f0f77 |
| 15 | 79fbdd96b15ec2d96244e45f8be7f565b1249dcd078a034e844788b2b3e1b3cb | 5e44532f924b06876cc0d24422459659b3cec6e815400fd99191d7f0b04043bc |
| 16 | fe092c0d0a14e46bbf660cb8e492e70afd598008abd46c2d7ef1b3e402c74bf2 | f779f367e2b8117f5f8dd2f2e1d3616ae943fc4f73cf1328b454d5b55c28c27b |
| 17 | 1ed4f8d883a63cf688efdb6f53dae3a8ecdcac6138e6242526d94ae0964a6751 | 1e237bdf4ff53a81eb4023ac7723f5b8ae17e00a6ae40c72361e609dcc4189ac |
| 18 | 7ed834cbc36f6d4f09263128ba2dff0fd69dfa29703382e2fec65ffa3379197c | 0bc8c3f1ab9293dae5b3185b7b29cb6d3edf3cc898424c20207b5ffe3a37ee87 |
| 19 | a633a1d08533c0ec401a8c33e3f7d1bcd15c6175c2668458deab7ca5c68b0108 | 5e9b25e49f91c78d0233551b4d87da4909d24c707fdcffc6299c0ca5f1ebb7f8 |
| 20 | 02810c93cc335450517561e1bbecd0b693a9eba91fa8be471ea4939718e50eae | 5d14d30a9aff82c4dfd6c9e6ab366b1727e470a9ecf9ec3c2788e113eb66a3a1 |

#### Hash, FRA-CE1-06
| Item | SHA-256 du texte | SHA-256 de l'item |
|---|---|---|
| 1 | d7af1b76de1402018361327dd593b29c8883ffa70076eca21c3d24627d300157 | 09cf645081e83346c52900a908a42407a38a3bafa4ed896809969b689b2e81ee |
| 2 | 2f063d7faae560e474155a7f9b5c2a8ee5aae65b00e546ec46624a00134ccccf | fe647ff2993b6666a1433f33150fdb0d4ebf65f1b6756a2060abffac6e14c5ee |
| 3 | b95cf25ad57e2cdc3e69bb8ad5a0588317650bf046cc72953e21b2e10897d1d4 | 5b47a8dbc0c661a8747de78861fdab64c217643364020d00743d45ca033df5ba |
| 4 | be29d89dad0a2bf4816793b732388c8e371b32a3603d9cce21452debbc331765 | 0e0d196948dbe9462387d716b4f120d72b9d6fb06ad3a6bb471af1d89a55766e |
| 5 | 159cf43991d1b92f90b1bdb0c709df4ee9b91252dc7c271ef3f7962e43dfeaaa | c9f15be81f8b2081214d95f90fed2f74ec15a204f6799ef5c8785742671427c2 |
| 6 | 5f2070e8787599717527d8f278a7bb2b5973af11b7a11ba4d406907766e56f38 | ffe0ad3e72df22d37f63da1e77c131608f6d0c788b3fb54aea85f156ddc9f8d0 |
| 7 | 51020b9ef57a55ec14c3472eadddb59b815636b25bf571e0ac48664f6ddc499c | 55ee091f16e1f14ab3713f1b28076e133a1dd368c50920a7292c46755083bdae |
| 8 | eec58098ff6a2294639e1a8a5ccf1d6d57b6612254980d784d3d0e46936ed9c7 | f6c0d98107a822c533d610a21b84e734518ad2a8a9ce6df7f1d0357b3eaedf77 |
| 9 | 74276daf161a81e2dc446ffcace40d21e3c0c89269464354e51a2bbcd67cfa5b | 9e6003f9ea24f2f1fcae42eff99152ec53a444a71b482aadd16269deb0e06b74 |
| 10 | 29e2915a2727e80e0307825808b7401390c76fc88083547a8b8ffc0b328b3047 | f71d6ae4db09ef4898029d17b2424da5d7e77d4ef0c0a14528272ec79f1e319c |
| 11 | 680f9e6d2d2ee81637cf2b94b8fc92a86a3819898dc62887393c94d30b91bfa9 | c295cdc37cb9e86bb7cef7d313c83b1827e7ce01745f40f3d28a0ca66fd6ba86 |
| 12 | 42c9b5ca8d72af74621210a2138b2e6a24f8cbc40eb282f5f5a8c6442a541ab7 | 96d86dc31f73ad2240c06fe2c5fe60f059f99ee76fcbf723224e20d499cba472 |
| 13 | f4c633c3d9b746016bdf03c11ed919193c4bc405dcc6827c2ca57b3073f39787 | 63a8992d12f3432bed09f30c7db84f09dddcfde33db7a12334bdc8e4c490710f |
| 14 | 74307b4ac1d0fed31a05c14dcc0a0febf4cc923b4d8eb5122ac708de488e873b | bb8b8dcab00366a99f01c4e1fed58dc72a619497cb580408014ef4cb29e4d0e6 |
| 15 | c0088eea0e284ef65a6c1e9838fcc55b36bf1bbc436cbc9c9a00f900074f3036 | d87f3bac08b0d2c49ce675e51d665c64ad78d996899c3ac554ead39e1ead856e |
| 16 | 528f3077fc72a1e680f13222e80ae352ed5c9a1dc4cbca14e6d0e55aa351bbe5 | 2e98a4c98e2038ee168c802532c10d41a66fe91abb61a2b1499a71b0c64e284b |
| 17 | 760bb34efdf5854c826a4b93df9a557bf7cfb7f190bd63ea5a5af6b472e4f19c | 7e195cd4a8787f3a303b577313492d4a2f6918dcaadb1b063978448cf6a8ac28 |
| 18 | 1efc9bc845a68dc9a87bcbe70bea06e04c79267d8d73e4e00c9db2b475216191 | c6fabf899d6cc8974c7071486687cd5b1945c2cec66c9c1838198b8e36d483d5 |
| 19 | b888d8804f3cb822d43b3a9c501fccf63da7ad4d2fdb35fc9ec6f3dcc595817c | 372bdb5a2dd011b20daad925ce6917b8484daae4697c0bd8256f17799f15e8fa |
| 20 | f46e219db617817c50ea82b3d5743d77969da5284c709cc1724acfe94df4435e | 7806062f81288bbe2bf53f92e1660ba6121497f7929baf2f38127cf79350b7d8 |

#### Hash, EMC-CE1-01
| Item | SHA-256 de l'item |
|---|---|
| 1 | 887cef19713cbfc1718aeaef961342381b2f4e753fffdfc691466d2c50db2654 |
| 2 | 1eb4e1cf4485a6f7c481daf3dce1bbc2bb08d6c7c9157952a554840f5490501c |
| 3 | 51bd07bd8aa33ede64ae4f5fc1ef6888c3f54e3f3fcb74df9428bf093dcbd595 |
| 4 | 5019eb01ffcfe36bbf162c23cc755f99858b3b1a1460ab425efe7a0bbb541848 |
| 5 | 35118c700d9de00d6c9a97a9498d77c01fe3bbb59c06a7953ac2eafe7714c31b |
| 6 | 29114936435a5c5da31bbd57948d93d8c387d3154b577a91bb4389db05afab9f |
| 7 | 0a85358879ac76474fa64fc8bfe49eb4ab992f695106b827bd7b17c558d03999 |
| 8 | 216074a633fd0d93dd2ddd6d74da4f38291328b5d205304b80afbea462e9807a |
| 9 | 9d44b9b32fb84d444a76add2189660cb6f6fa515fcaee80d5984a4c287e7ab61 |
| 10 | 84340747d0b27bdfe7b57fad7df22cf19c4ac94ee2e035bdc69e5d67e802dcb3 |
| 11 | 5b084adfa0fe175040fa803926ed087b4b25eb1223c730245a815a7baa107ce9 |
| 12 | 3ea4930c4921d2c44d4aaa6bf93fda712fa077fb7ca5c3a27296bd86130df40d |
| 13 | 4f3254f9137a93362c5692793a4ce9fcfccaba0c2f0c09831c8ea6219fccc918 |
| 14 | 0931db6ea6bec331bc56208b149bcc785106e8c4726f954332fd1f2c1af83cfe |
| 15 | e26f9d68afd9775c217af708fe23b5426e5b852a16790bbc39ef5e12c3ebeab3 |
| 16 | afe4133424f77c7ad596c0bad282adfeb6bc3b1d964eeb5d21b8a7fddb6daa0d |
| 17 | e71efbe01acfed5dadd2d49b1a0b6cb21aaa2c5bafe6864d22a9b44d76026261 |
| 18 | 6a71bb908571cd6561880df041858c648818c3f63a895c3500887f8d246e9564 |
| 19 | 316f66d2f50f7982755bcce9bb88ea0cea07e65a6da588767246a6f9e3ba9270 |
| 20 | bd3db04f0b03c2de12d3b3dea57116fcab35dfaa6665b9de2910627976ddd1ae |
| 21 | 6dbd5fe15fc73521c648c81493c59255e5c08ae60a1fb09972f0e27b3966d0e5 |
| 22 | daeae94abcf76599f95ccc69ceac1a277f0eafebc35fb878aea18445dbee6439 |
| 23 | 0d760390e57ce5c500bb22139968181655add0006edfd95db65e64b20905b6ca |
| 24 | 810a206c786fe47f9c4120ca038b47482d5569ef04621897c6660b5373dfd4b1 |


## 7. Ce qui tient, ce qui ne tient pas (au sens du critere lu, sans jugement)

- **Tient** : `-07`, 19 items sur 20, dont le 12 (4 phrases, bas de l'indice a 478) ; a 992 et 1200 tous les items des trois paquets ; le chemin de sortie, au-dessus du pli dans tous les etats.
- **Ne tient pas a 744 x 551** : `-07` 13 ; `-06` 14, 16, 17 (1 px, 2 ordres sur 6), 19, 20 ; `EMC` 2, 3, 6, 8, 9, 11, 12, 13, 14, 16, 17, 20, 21, 22, 23, 24. `EMC` 1 et 19 tiennent a 3 px pres (bas de l'indice 548) ; `EMC` 15 (518) et 19 tiennent a 744 et ne tiennent plus a 720 avec barre (566 et 596).
- **Observe, sans cause isolee par une variante** : le `-07` 12 (4 phrases) tient, sa phrase unique tenant sur une ligne ; le `-06` 14 (4 phrases) a une proposition de 680 px qui occupe seule une rangee ; le `-06` 20 (3 phrases) a trois rangees de propositions. Je releve des cotes.

## 8. Ce que je n'ai pas mesure

- `FRA-CE1-11` et `MAT-CE1-01` (pas de demande du pli dans la lecture de `nadia-prof`).
- Les neuf polices imposees, un appareil reel, le clavier, le focus, un doigt.
- Le rendu apres les corrections des developpeurs : section 6.
- Aucune variante de style : le jar est mesure tel quel.

## 9. Isolation

Un jar sur le port 18671, un Chrome sur le 19671 avec son profil, arretes par leur PID (le jar : PID 39964 lu a sa creation ; le Chrome : plus aucun processus a ma ligne de commande apres la fin du pilote, controle par liste). Aucun processus par nom d'image. Worktree `s57-ines-p3` cree et laisse en place, non commite. Aucun fichier d'un tiers deplace ou supprime.

## Decisions prises

**Aucune.** Ce fichier ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done.
