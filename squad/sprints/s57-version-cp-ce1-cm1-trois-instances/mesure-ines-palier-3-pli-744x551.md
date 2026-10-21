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



---

# Remesure du 2026-10-20 (apres les corrections de contenu), 744 x 551 apres une erreur

- **Auteure** : `ines-design`. **Date** : 2026-10-20, 09:57 (UTC+02:00), lue par `date` (mesure de 09:47 a 09:54). Cette section complete les sections 1 a 9 ci-dessus, qui restent la mesure de `7301cbbc` et ne sont pas modifiees.
- **Ce que cette section est** : des cotes en pixels. Elle ne prononce aucun Done, ne dit pas si une reserve est recevable et ne propose aucun changement de contenu ou de style : le veto est a `nadia-prof`.
- **Identifiants** : PL-1 a PL-8 gardent leur sens ; ceux dont la valeur change sont mis a jour ci-dessous sous le meme code. **PL-9 et PL-10 sont nouveaux et provisoires** : je les ai nommes dans ce texte, ils ne sont pas alloues au registre. `theo-archi` doit les allouer (ou en retenir d'autres) et reporter les codes ici.

## R1. Arbre, jar, methode

- **Arbre** : `round-two` a `4650aeabb0412c3da0d974728c43637af5f4145d` (fusion de `ADR-0054`, pile de polices ; contient la fusion `c7dab42b`), worktree `s57-ines-p3` avance en avant rapide, branche `s57-ines-p3`. `git diff --stat 7301cbbc 4650aeab -- adapters/adapter-web/src/main` : **vide** (aucun gabarit, aucune feuille, aucun octet servi n'a change). `recreo.css` : blob `7cff4fd3751a6184ebe8391e017bd0b082fbfff1` (identique), SHA-256 servi `e1cd65c255a5142efc19c5cb616134b1d26bd17ffb401684e1e0a0ea2728cf41` (identique a la section 1).
- **Jar** : `bash outils/mvn-jdk21.sh -B clean package -DskipTests`, sortie 0, copie hors depot, SHA-256 `bccaf1e76b8a0905629ded6cf506e8e12670cc73811fb2763c4c31929b6c31bb`. JDK 21.0.10, port 18681, PID 59992 lu sur le port en ecoute, arrete par ce PID (le port ne repond plus).
- **Methode** : celle de la section 1, inchangee. Meme pilote (`pilote-p3.mjs`, hors depot), Chrome sans tete, un seul navigateur a mon nom (PID 60808, profil jetable, port 19681 ; a la fin du pilote, aucun processus ne portait plus ce profil, controle par liste). Cinq largeurs (720, 744, 768, 992, 1200), hauteur 551, deux modes (avec et sans barre), arrivee, erreur 1 (six ordres des propositions), erreur 2, revelation (six ordres). Aucune regle de style injectee. Critere : bas de `.indice` au plus a 551.
- **Couverture** : cinq paquets en entier, pas seulement les items modifies : `-07` 20 sur 20 (8 parties), `-06` 20 sur 20 (6), `EMC` 24 sur 24 (5), `-11` 24 sur 24 (5), `MAT-CE1-01` 22 sur 22 (8). Anomalies (item non retrouve ou ambigu) : 0. Texte rendu egal au texte du YAML sur les 110 items. 4400 etats releves (110 items x 4 etats x 10 combinaisons largeur-mode).
- **Paquets** : `FRA-CE1-11` = `ce1-negation` (jeu `bandit-manchot-negation-ce1`), `MAT-CE1-01` = `ce1-nombres-jusqu-a-1000` (jeu `bandit-manchot-nombres-jusqu-a-1000-ce1`), lus dans `catalogue.yaml` et `lignesDeReferentiel`.

## R2. Resume

| Code | Constat a `4650aeab`, 744 x 551, apres une erreur | Section |
|---|---|---|
| PL-1 | Inchange (l'ecart de numerotation des items 14 et 20 entre `-06` et `-07` reste dans la section 2) | 2 |
| PL-2 (mis a jour) | `-07` : **0 item sur 20** a un bas d'indice au-dela de 551 (avant : 1). Maximum **508**. L'item 13 passe de 3 a 2 rangees (582 -> 508). L'item 12 (4 phrases) passe de 478 a **404** (propositions sur 1 rangee au lieu de 2) | R3, R4 |
| PL-3 (mis a jour) | `-06` : **5 items sur 20** depassent encore, les memes qu'avant : 14 (556 ou 630), 16, 19 et 20 (582), 17 (552 dans 2 ordres sur 6). Seul le 19 a un hash change, sa cote est restee 582 | R3, R4 |
| PL-4 (mis a jour) | `EMC-CE1-01` : **11 items sur 24** depassent dans au moins un ordre, **8 dans les six** (avant : 16 et 10). Maximum 596 (items 11 et 21) | R3, R4 |
| PL-5 | Reste vrai : ce qui descend l'indice est le nombre de rangees de propositions (74 px) et de lignes de texte (30 px) ; l'indice lui-meme occupe 1 ligne (50 px) ou 2 lignes (80 px). A 992 et 1200 : **aucun item des cinq paquets** ne depasse (bas de l'indice 518 au plus) | R4, R5 |
| PL-6 (mis a jour) | 720 avec barre rend des cotes differentes de 744 pour **4 items** : `EMC` 14 (522 -> 596), 15 (518 -> 566), 19 (548 -> 596) et `MAT` 5 (518 -> 548). `-07`, `-06`, `-11` : aucune difference de 720 a 768, avec ou sans barre. 720 sans barre, 744 et 768 : identiques | R5 |
| PL-7 (mis a jour) | Un bouton de reponse depasse le pli dans un seul cas : `-06` 14, 556 dans 2 ordres sur 6, de 720 a 768. Chemin de sortie (`a.btn.lien-accueil`, `a.btn.chip-niveau`) : bas a **66** dans les 4400 etats. Aucun `.explication` rendu, aucun debordement horizontal | R5 |
| PL-8 (mis a jour) | Hash du texte et de l'item des 110 items, avec la liste de ceux qui ont change | R6 |
| **PL-9 (nouveau)** | `FRA-CE1-11` : **22 items sur 24 depassent, dans les six ordres** (bas de l'indice 566 pour 8 items, 596 pour 14) ; les items 23 et 24 tiennent (492). Les propositions finissent a 492 (3 rangees) pour 22 items ; l'indice occupe 1 ligne (566) ou 2 lignes (596). A 992 et 1200 : aucun item ne depasse (518 au plus) | R4 |
| **PL-10 (nouveau)** | `MAT-CE1-01` : **22 items sur 22 tiennent**, maximum **548** (item 6, 3 px sous le critere). L'item 5 est a 518, et a 548 a 720 avec barre. 17 items sur 22 ont leurs propositions sur 1 rangee | R4, R5 |

## R3. Items dont le hash a change, et leur cote

Le hash est celui de la section 6, recalcule en Python sur le YAML de `4650aeab` et compare, pour chaque item, a celui que le pilote a calcule : **0 ecart sur 110 items**. La comparaison se fait avec le YAML de `7301cbbc`.

| Paquet | Items dont le hash de l'item a change | Dont la cote a change (744, sans barre) | Hash change, cote egale |
|---|---|---|---|
| `-07` | 1, 2, 3, 7, 9, 10, 12, 13, 14, 16, 18 (11) ; hash du texte change : 3 | 9 (434 -> 508), 12 (478 -> 404), 13 (582 -> 508) | 1, 2, 3, 7, 10, 14, 16, 18 |
| `-06` | 19 (1) | aucun | 19 (582) |
| `EMC` | 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 20, 21 (17) | 1 (548 -> 474), 3 (596 -> 522), 6 (522-596 -> 522), 7 (444-518 -> 444), 9 (492-566 -> 492), 12 (522-596 -> 522), 14 (596 -> 522), 17 (566 -> 492-566), 21 (522-596 -> 596) | 2, 4, 5, 8, 10, 11, 15, 20 |
| `-11` | 2, 5, 7, 10, 12, 15, 17, 20, 22, 24 (10) | non mesure avant | - |
| `MAT-CE1-01` | 15, 16, 19 (3) | non mesure avant | - |

- Ces listes coincident avec celles de la consigne et de `lecture-nadia-palier-3.md` : `-07` 11 items, `-06` item 19, `EMC` 17 items (la tache 1 en entier et 14, 15, 17, 20, 21), 10 indices de `-11`, `MAT` items 15, 16, 19.
- **Verification inverse** : aucun item au hash inchange n'a une cote differente de celle de la section 4, ni a 744 ni aux neuf autres combinaisons largeur-mode.
- `EMC` 21 est le seul item dont la cote **monte** : 522 dans certains ordres et 596 dans d'autres avant, 596 dans les six apres (3 rangees partout). `EMC` 17 ne descend que dans certains ordres (566 -> 492 a 566).
- `EMC` encore au-dela de 551 dans au moins un ordre : 2, 8, 11, 13, 16, 17, 20, 21, 22, 23, 24.

## R4. Cotes a 744 x 551, mode sans barre, apres une erreur (cinq paquets)

Colonnes comme en section 4 : phrases, lignes du texte, bas du texte, bas de la proposition la plus basse (minimum-maximum sur les six ordres), rangees, bas de `.indice`, position par rapport a 551. *autres* : combinaison largeur-mode dont la cote differe.

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
| 9 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 10 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 11 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
| 12 | 4 | 1 | 170 | 330-330 | 1-1 | 404-404 | au-dessus |
| 13 | 3 | 2 | 200 | 434-434 | 2-2 | 508-508 | au-dessus |
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

### EMC-CE1-01, `ce1-emc-biens-communs` (aucun texte-support)
| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 2 | 0 | - | - | 418-492 | 2-3 | 492-566 | sous le pli a 15 px dans certains ordres |
| 3 | 0 | - | - | 418-418 | 2-2 | 522-522 | au-dessus |
| 4 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |
| 5 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 6 | 0 | - | - | 418-418 | 2-2 | 522-522 | au-dessus |
| 7 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 8 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 9 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |
| 10 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 11 | 0 | - | - | 418-492 | 2-3 | 522-596 | sous le pli a 45 px dans certains ordres |
| 12 | 0 | - | - | 418-418 | 2-2 | 522-522 | au-dessus |
| 13 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 14 | 0 | - | - | 418-418 | 2-2 | 522-522 | au-dessus ; autres: 720-barre 418-492 / 522-596 |
| 15 | 0 | - | - | 444-444 | 3-3 | 518-518 | au-dessus ; autres: 720-barre 492-492 / 566-566 |
| 16 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 17 | 0 | - | - | 418-492 | 2-3 | 492-566 | sous le pli a 15 px dans certains ordres |
| 18 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |
| 19 | 0 | - | - | 444-444 | 3-3 | 548-548 | au-dessus ; autres: 720-barre 492-492 / 596-596 |
| 20 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 21 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 22 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) ; autres: 720-barre 492-492 / 596-596 |
| 23 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 24 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |

### FRA-CE1-11, `ce1-negation` (aucun texte-support ; mesure nouvelle)
| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 2 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 3 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 4 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 5 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 6 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 7 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 8 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 9 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 10 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 11 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 12 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 13 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 14 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 15 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 16 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 17 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 18 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 19 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 20 | 0 | - | - | 492-492 | 3-3 | 566-566 | sous le pli, tous ordres (+15 au pire) |
| 21 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 22 | 0 | - | - | 492-492 | 3-3 | 596-596 | sous le pli, tous ordres (+45 au pire) |
| 23 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |
| 24 | 0 | - | - | 418-418 | 2-2 | 492-492 | au-dessus |

### MAT-CE1-01, `ce1-nombres-jusqu-a-1000` (aucun texte-support ; mesure nouvelle)
| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 2 | 0 | - | - | 370-444 | 2-3 | 444-518 | au-dessus |
| 3 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 4 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 5 | 0 | - | - | 444-444 | 3-3 | 518-518 | au-dessus ; autres: 720-barre 444-444 / 548-548 |
| 6 | 0 | - | - | 444-444 | 3-3 | 548-548 | au-dessus |
| 7 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 8 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 9 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 10 | 0 | - | - | 296-296 | 1-1 | 400-400 | au-dessus |
| 11 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 12 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 13 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 14 | 0 | - | - | 296-296 | 1-1 | 400-400 | au-dessus |
| 15 | 0 | - | - | 344-344 | 1-1 | 448-448 | au-dessus |
| 16 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 17 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 18 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 19 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 20 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |
| 21 | 0 | - | - | 296-296 | 1-1 | 400-400 | au-dessus |
| 22 | 0 | - | - | 296-296 | 1-1 | 370-370 | au-dessus |


## R5. Largeurs et modes

Nombre d'items dont le bas de `.indice` depasse 551 apres une erreur, dans au moins un des six ordres (entre parentheses : dans les six).

| Largeur, mode | `-07` (20) | `-06` (20) | `EMC` (24) | `-11` (24) | `MAT` (22) |
|---|---|---|---|---|---|
| 720, barre | 0 (0) | 5 (4) | 14 (10) | 22 (22) | 0 (0) |
| 720, sans barre | 0 (0) | 5 (4) | 11 (8) | 22 (22) | 0 (0) |
| 744, barre | 0 (0) | 5 (4) | 11 (8) | 22 (22) | 0 (0) |
| 744, sans barre | 0 (0) | 5 (4) | 11 (8) | 22 (22) | 0 (0) |
| 768, barre | 0 (0) | 5 (4) | 11 (8) | 22 (22) | 0 (0) |
| 768, sans barre | 0 (0) | 5 (4) | 11 (8) | 22 (22) | 0 (0) |
| 992, les deux modes | 0 | 0 | 0 | 0 | 0 |
| 1200, les deux modes | 0 | 0 | 0 | 0 | 0 |

- Valeur maximale du bas de l'indice, de 720 a 768 : `-07` 508, `-06` 630 (item 14), `EMC` 596, `-11` 596, `MAT` 548. A 992 : 434, 508, 492, 518, 518. A 1200 : 404, 508, 444, 444, 444.
- Revelation (hors du critere lu en 4.1 point 4, pour information), 744 sans barre, bas de `.indice` au-dela de 551 dans au moins un ordre : `-07` 9 items, `-06` 14, `EMC` 22, `-11` 24, `MAT` 3.
- Seconde erreur, 744 sans barre, ordre servi : `-07` 0, `-06` 4, `EMC` 9, `-11` 22, `MAT` 0.
- Boutons de reponse au-dela de 551 apres une erreur : `-06` 14 seulement (556, 2 ordres sur 6). Boutons de sortie et de niveau : bas a 66, dans les 4400 etats.

## R6. Hash des items mesures (PL-8 mis a jour)

Methode de la section 6 (hash du texte : SHA-256 du champ `texteSupport` rendu ; hash de l'item : SHA-256 du JSON compact de `[enonce, texteSupport, bonneReponse, distracteurs tries, indice]`). La colonne *Hash de l'item* dit si le hash a change par rapport a `7301cbbc` (non mesure avant : `-11`, `MAT`). Etat du YAML a `4650aeab` (blobs) : `ce1-texte-documentaire.yaml` `6256e555235e190695daf272529d45aee8299e3e` ; `ce1-inference.yaml` `be32340de90326406057a11fccd9a72309fd3cdf` ; `ce1-emc-biens-communs.yaml` `57a99de875c51fdf1e363621d4932a27a62df7ea` ; `ce1-negation.yaml` `0a505f3ba1aae430ebbacddc8c2e53a7d77e05e7` ; `ce1-nombres-jusqu-a-1000.yaml` `f6b5a42e32f7162cb0da1b9a2db520d889872111`. Une cote reste valable tant que le hash de l'item, les gabarits (`git diff --stat 4650aeab <sommet> -- adapters/adapter-web/src/main` vide) et la feuille sont inchanges.

#### Hash, FRA-CE1-07
| Item | SHA-256 du texte | SHA-256 de l'item | Hash de l'item |
|---|---|---|---|
| 1 | ce939573e83e176f3a9a9a1e82e1498df5edfeb7ca7f5ee4d9bc8d1b931c9ba1 | 75f66c6a3f78a93f6e46a6a9f432a0ef1500d47a5c856632bd1f5de122951ff3 | change |
| 2 | b3b7c88332fc12cf3161c39dbeee32f69fe721d797191d797cae80340edec0ae | 3ca034fbef009744ab558ae9ec5c97ccb3894d0fb0e395f13ed6d75f33d2ed9f | change |
| 3 | 0db7c3a294309bee8cef6684b9d841f90ee6f60315825b21978575632b6ef83d | c1b8a64b826ff4e2739022fd9d03f6270e9a51dbdc7dcf916d89655db13d0676 | change |
| 4 | f6b0f97749dbf51d40899fbcd935d47406dd3e8caaafb5b75ad0fe427481c6d0 | 1e432c0b17f413ede2cefdeb8779fe086251e78bd5e05e7ffedf1251d12a9741 | egal |
| 5 | 4498635b71cb747b8479136e236a8bfb2ad9888843f3f2d697e3b6c5420d8551 | 81329493bf039cc7325d2a2cd6acc9388df0a3b1172c4801f2ddf894da50b96c | egal |
| 6 | f22f03ecd377bd9e09c6591349295f69b6611de1bd909c0ca74f38afd1915822 | 3ab6ac734eb03c26860a90397771fc65c9a72e1e07e84f7afb7e0bf6bd106a01 | egal |
| 7 | d93c46901af8eff4c84eb6741f5d57e5b840710ab3c982d255da0f39ab1b4c95 | 0f700e20ccec0937afa6f555ec1edd977aae3287837468884d82958801fbdae4 | change |
| 8 | d0c8699aa6fc5844444661779ef05b2f6ffa8cdd8d5e086f0bcf485ff0d70848 | 5fd32c700aebf2fc616be0c919b13e44cedd365e1d82a675a3b02e041260fc81 | egal |
| 9 | 6f59a6fb7cd1ecdb2d2dfe2f2080a71df7c69d372dd47f4c0ea692610945df10 | 221d59eb2e78f5442edb8a735293627154c0420f355d802a1ba9bad827b17f19 | change |
| 10 | 7eb5d4414ff7d7ab1e716f30dabeef0dba37d699baea37953bfb6dd7de9014de | fa7f21a8fd006921798470a9e3ad393f2cdce20e4938fbbb9fd5382808c2b401 | change |
| 11 | c3fcf6e9911daf64cc5394c3ec1a7681c28d49392a35e7fb8f0e21214acd419a | 7be46ff2beb16e5cc70ee5181b6e3fc9308d368d662f15b0ebe8acc82ca0022a | egal |
| 12 | e81cfdf42f26690b15f78148602184f34014b6175cde1ec1c6c4858b90d000fe | bb122a7b1761a95c7fc11278e5ba603fd4b83ed3622c97b464c6838a9f97ef3e | change |
| 13 | 90bc24b898b075005ca76429b3ad52c1cfb9f110cd1c5c7b73fe6e372865f91c | 74f2115a4fc25c59afd8ed2047fc215ab05da982a434eeea98ded5adcb4e319a | change |
| 14 | 4033d8f0b17773fbb42086a4c8871b93e95ec89da64cd93f8a67a5ea16daca9d | 4d3448bef47491d226f4c3e01c0e67956981232bf6a241c72b1a79a8c16e1971 | change |
| 15 | 79fbdd96b15ec2d96244e45f8be7f565b1249dcd078a034e844788b2b3e1b3cb | 5e44532f924b06876cc0d24422459659b3cec6e815400fd99191d7f0b04043bc | egal |
| 16 | fe092c0d0a14e46bbf660cb8e492e70afd598008abd46c2d7ef1b3e402c74bf2 | 507d8a01b413053b9ff99ad165bf95665d50b63a216cfaa7085bd992f485c50f | change |
| 17 | 1ed4f8d883a63cf688efdb6f53dae3a8ecdcac6138e6242526d94ae0964a6751 | 1e237bdf4ff53a81eb4023ac7723f5b8ae17e00a6ae40c72361e609dcc4189ac | egal |
| 18 | 7ed834cbc36f6d4f09263128ba2dff0fd69dfa29703382e2fec65ffa3379197c | 1e26c41c76cb03ac3b1b45f7bc87a05221f7f0b8cf5aabe8866cc0b94ea47842 | change |
| 19 | a633a1d08533c0ec401a8c33e3f7d1bcd15c6175c2668458deab7ca5c68b0108 | 5e9b25e49f91c78d0233551b4d87da4909d24c707fdcffc6299c0ca5f1ebb7f8 | egal |
| 20 | 02810c93cc335450517561e1bbecd0b693a9eba91fa8be471ea4939718e50eae | 5d14d30a9aff82c4dfd6c9e6ab366b1727e470a9ecf9ec3c2788e113eb66a3a1 | egal |

#### Hash, FRA-CE1-06
| Item | SHA-256 du texte | SHA-256 de l'item | Hash de l'item |
|---|---|---|---|
| 1 | d7af1b76de1402018361327dd593b29c8883ffa70076eca21c3d24627d300157 | 09cf645081e83346c52900a908a42407a38a3bafa4ed896809969b689b2e81ee | egal |
| 2 | 2f063d7faae560e474155a7f9b5c2a8ee5aae65b00e546ec46624a00134ccccf | fe647ff2993b6666a1433f33150fdb0d4ebf65f1b6756a2060abffac6e14c5ee | egal |
| 3 | b95cf25ad57e2cdc3e69bb8ad5a0588317650bf046cc72953e21b2e10897d1d4 | 5b47a8dbc0c661a8747de78861fdab64c217643364020d00743d45ca033df5ba | egal |
| 4 | be29d89dad0a2bf4816793b732388c8e371b32a3603d9cce21452debbc331765 | 0e0d196948dbe9462387d716b4f120d72b9d6fb06ad3a6bb471af1d89a55766e | egal |
| 5 | 159cf43991d1b92f90b1bdb0c709df4ee9b91252dc7c271ef3f7962e43dfeaaa | c9f15be81f8b2081214d95f90fed2f74ec15a204f6799ef5c8785742671427c2 | egal |
| 6 | 5f2070e8787599717527d8f278a7bb2b5973af11b7a11ba4d406907766e56f38 | ffe0ad3e72df22d37f63da1e77c131608f6d0c788b3fb54aea85f156ddc9f8d0 | egal |
| 7 | 51020b9ef57a55ec14c3472eadddb59b815636b25bf571e0ac48664f6ddc499c | 55ee091f16e1f14ab3713f1b28076e133a1dd368c50920a7292c46755083bdae | egal |
| 8 | eec58098ff6a2294639e1a8a5ccf1d6d57b6612254980d784d3d0e46936ed9c7 | f6c0d98107a822c533d610a21b84e734518ad2a8a9ce6df7f1d0357b3eaedf77 | egal |
| 9 | 74276daf161a81e2dc446ffcace40d21e3c0c89269464354e51a2bbcd67cfa5b | 9e6003f9ea24f2f1fcae42eff99152ec53a444a71b482aadd16269deb0e06b74 | egal |
| 10 | 29e2915a2727e80e0307825808b7401390c76fc88083547a8b8ffc0b328b3047 | f71d6ae4db09ef4898029d17b2424da5d7e77d4ef0c0a14528272ec79f1e319c | egal |
| 11 | 680f9e6d2d2ee81637cf2b94b8fc92a86a3819898dc62887393c94d30b91bfa9 | c295cdc37cb9e86bb7cef7d313c83b1827e7ce01745f40f3d28a0ca66fd6ba86 | egal |
| 12 | 42c9b5ca8d72af74621210a2138b2e6a24f8cbc40eb282f5f5a8c6442a541ab7 | 96d86dc31f73ad2240c06fe2c5fe60f059f99ee76fcbf723224e20d499cba472 | egal |
| 13 | f4c633c3d9b746016bdf03c11ed919193c4bc405dcc6827c2ca57b3073f39787 | 63a8992d12f3432bed09f30c7db84f09dddcfde33db7a12334bdc8e4c490710f | egal |
| 14 | 74307b4ac1d0fed31a05c14dcc0a0febf4cc923b4d8eb5122ac708de488e873b | bb8b8dcab00366a99f01c4e1fed58dc72a619497cb580408014ef4cb29e4d0e6 | egal |
| 15 | c0088eea0e284ef65a6c1e9838fcc55b36bf1bbc436cbc9c9a00f900074f3036 | d87f3bac08b0d2c49ce675e51d665c64ad78d996899c3ac554ead39e1ead856e | egal |
| 16 | 528f3077fc72a1e680f13222e80ae352ed5c9a1dc4cbca14e6d0e55aa351bbe5 | 2e98a4c98e2038ee168c802532c10d41a66fe91abb61a2b1499a71b0c64e284b | egal |
| 17 | 760bb34efdf5854c826a4b93df9a557bf7cfb7f190bd63ea5a5af6b472e4f19c | 7e195cd4a8787f3a303b577313492d4a2f6918dcaadb1b063978448cf6a8ac28 | egal |
| 18 | 1efc9bc845a68dc9a87bcbe70bea06e04c79267d8d73e4e00c9db2b475216191 | c6fabf899d6cc8974c7071486687cd5b1945c2cec66c9c1838198b8e36d483d5 | egal |
| 19 | b888d8804f3cb822d43b3a9c501fccf63da7ad4d2fdb35fc9ec6f3dcc595817c | 34d2e2091eb04c419d729b484ad33d90ff15c0d4f8412ab4bca85d111ac74dec | change |
| 20 | f46e219db617817c50ea82b3d5743d77969da5284c709cc1724acfe94df4435e | 7806062f81288bbe2bf53f92e1660ba6121497f7929baf2f38127cf79350b7d8 | egal |

#### Hash, EMC-CE1-01
| Item | SHA-256 de l'item | Hash de l'item |
|---|---|---|
| 1 | e7bbde0d326aefeb1c646942344a6b47b9f6ac0d47221adb11de27f016547a17 | change |
| 2 | bf0e852fa5d0e25d06d43baf9c4f25049814015a1768857a307e13a62a820349 | change |
| 3 | 3b9b49a532416b26d61665bbacc545639d03942219f9d1d48e81f019c57768a4 | change |
| 4 | a984eff57c1044e6e882deb344d746314a09c70b601ed21acd933d77ac3ec54f | change |
| 5 | fbdf6b6007f4089cf8300b0dff7ed22cb283a3bc491b29d734571c563057687e | change |
| 6 | 6b34953cf536569aea04e2fefb7b2ede44db79a44503fd721efea4b0763a21b8 | change |
| 7 | 78bbd9262a20021ed7a7b5219bdfd117a93aad060287d577a9ff15214596c84b | change |
| 8 | 6dd3c5881c08555c4a07f8cfa6a90ed5f314f35e8e4cf1e048edae6428cbb6b4 | change |
| 9 | b2f826f628a245637a695fef2998870841b43ee1623b3e5d23206bd6287c0772 | change |
| 10 | 1e7b185271e78e0b19d3581a6a54733b3e2dffec886dede976d8f000baa667f4 | change |
| 11 | d392c8cc06cac45e63274d92ad8d0a77250dafd846c3c6ada194ad38de149be2 | change |
| 12 | 5ae5a12eb56985554967e65b1fc333ef179099f5889795e47041cba62ecc9faa | change |
| 13 | 4f3254f9137a93362c5692793a4ce9fcfccaba0c2f0c09831c8ea6219fccc918 | egal |
| 14 | c5c216250a18df061509613b09ab8a383623ba89be2e871e33d0d5de087f8345 | change |
| 15 | b38cee1d0ff4d36d1e57f2d2f82ec59407924e6c44b03e0d31fe1891e18c4eb3 | change |
| 16 | afe4133424f77c7ad596c0bad282adfeb6bc3b1d964eeb5d21b8a7fddb6daa0d | egal |
| 17 | 9cedf9689e1a8b6c8a7e3de29c08c5073b09cd2c6f76c1bee74852d04413949b | change |
| 18 | 6a71bb908571cd6561880df041858c648818c3f63a895c3500887f8d246e9564 | egal |
| 19 | 316f66d2f50f7982755bcce9bb88ea0cea07e65a6da588767246a6f9e3ba9270 | egal |
| 20 | d928a6515b0726085d3d80c46e935fc93c00e496a8c7ab259ea9db5fc42fdd85 | change |
| 21 | fe072d9add7b103ae73db6c8e76608ef29f5b7c5fa475d234f44891903ed3990 | change |
| 22 | daeae94abcf76599f95ccc69ceac1a277f0eafebc35fb878aea18445dbee6439 | egal |
| 23 | 0d760390e57ce5c500bb22139968181655add0006edfd95db65e64b20905b6ca | egal |
| 24 | 810a206c786fe47f9c4120ca038b47482d5569ef04621897c6660b5373dfd4b1 | egal |

#### Hash, FRA-CE1-11
| Item | SHA-256 de l'item | Hash de l'item |
|---|---|---|
| 1 | 2881bd14cd626b59a9fa18102fb0ac7e475285058d92a442f3db6ad2d06d4850 | non mesure avant |
| 2 | 11508fad92c234b4568650692d58260e04944f5e70652a38243ab2e810d0b5e7 | non mesure avant |
| 3 | 49ea5198d1fe3a8d3e5ae29e1a07567f8ddb6852d708a9b8f09ec20863e6812b | non mesure avant |
| 4 | 48576e05661c3eef08afaccdd14271f3f138d0be557e6c6967bdab99b7fa2eec | non mesure avant |
| 5 | 31a5c62660374207983af1924fd20406c115570a7deb59a4dec4a8204291cb90 | non mesure avant |
| 6 | 37c21274d8051854b2f6e0ec417a9aba07921e4cf881035abcbf51322944a585 | non mesure avant |
| 7 | d298de015ae10f3fe6f816803b21686f2656eb82d9339bea9cb38e2f9d06798b | non mesure avant |
| 8 | 5c98597f81935b2b3f7a130a8fa89e1d13dec35fb0306527e34ca59081a8e623 | non mesure avant |
| 9 | ccf5d7e5a0edbb019b28188bba25695a59c131fb510b7562d8a7379dba9e0b16 | non mesure avant |
| 10 | efcd1f76710bb7c072926fe5055763f645345c6c90532fa7390513826be3c087 | non mesure avant |
| 11 | f843b5d59f3c00afd143844824b10fb2a76faf66e87c99b0f7f1cb45068ef083 | non mesure avant |
| 12 | 445f400466fbf9edc8d7139cce3a2682322e82b33068594a70a2b5632ff4d05e | non mesure avant |
| 13 | 23c51762c8005022f5bd3fbbb1dbb79d37512cab9dfc0f220089de96e8f2773c | non mesure avant |
| 14 | 0466f5087159df7ce857c2840c02500196f713416936cc2aa2c786ec1fc623ee | non mesure avant |
| 15 | 395480948bba5d30021fea7faba07484d8a41df8b1684cb68229a621698cc360 | non mesure avant |
| 16 | e081343f07cbb872de77e57ba766d26099c31e04b28f6a63ff2fa54a14061205 | non mesure avant |
| 17 | 7534ab9987494ce3dfe3c8e584b9792d576ae6b53519091e2252b222a92f956f | non mesure avant |
| 18 | 0eb15dcce21b62432a6288c3cc73e7fc5e702af36f1258d2e194e6b7477e2335 | non mesure avant |
| 19 | 35793434cdf3a4d959cae7fbc7a684bc5f599e65abebda090357028ffe69fee6 | non mesure avant |
| 20 | 62673a7a20c8ef7385e9e549d712feedb258b34bf62d83bc921fd764ce04dd10 | non mesure avant |
| 21 | f092dc88751dfafbf2116022d1c66bd2a258a92a7d129e21b8edc923c9aff33c | non mesure avant |
| 22 | a0ce2c144fee539c108dd25364f85f8f02cb8dc1be7f972714e20ded44e93e58 | non mesure avant |
| 23 | e41efabd4efce0137a66f5a2141cb355e554ea054bc5b92f862a533ba42b4e26 | non mesure avant |
| 24 | eb582ece2bbfbc0bb6f331e3c37fc1644b9527637c7f909c5b4e81386569e68b | non mesure avant |

#### Hash, MAT-CE1-01
| Item | SHA-256 de l'item | Hash de l'item |
|---|---|---|
| 1 | ba5c1515290fe293fb3f178356a186fd756a089521415fa5c5e85e51ee8fb14a | non mesure avant |
| 2 | 5787c869d3e2ab029f1c600dbc2f2e4bb1cb984b8529f94823028571893e63fc | non mesure avant |
| 3 | 2abcc7fd6c3b1baa842d31d72241ab3a90b77730fab4520ee9e3dd420edf8096 | non mesure avant |
| 4 | fd64e6c83749fa71424bcb29beb7dd51c2149200f3a7d3b334e97f668a4c04f0 | non mesure avant |
| 5 | 1865fdfb1314e860b81e60f9c0f58e8b3c193b743ce9f70ff55da4e6c10abbfd | non mesure avant |
| 6 | dd48911cec101a7f3c6697ba99475b0ac972e25e5685a0580917d5c8248e120d | non mesure avant |
| 7 | bb409c3a9c1850d612fd643561bb0c973fd665815a56fed822759d6474e1932a | non mesure avant |
| 8 | ac6889db58af94630046d7875e7e3f7475929f348d6d4810346bf6337f1d6bbb | non mesure avant |
| 9 | 1135e3d586bacc809f2a64ac37df117dfdc5919930a0ff01250a5a2b43cbd820 | non mesure avant |
| 10 | 42b92b54a040a831950830e10382113964a5c5da70baf958836ef7b65f19904c | non mesure avant |
| 11 | 6848548f0c3b8a1f4f6084b3607a42becdc57afee8980e034bb034b4931d087b | non mesure avant |
| 12 | 2529d40c39ba89f9711fa5b3759fc1252245f904a72eec636aaf49fae3f00abc | non mesure avant |
| 13 | 3ed18a91e00a2e2609a06be7097c9c8f1fad5f347371fdb95531e18df4e18c3f | non mesure avant |
| 14 | c72d6627a8a057449ebdd6874f583f880c15e548044bf28d5dc26715b1f6dded | non mesure avant |
| 15 | b6a38c60f289188fe56d37cfcdee30709a07028ac117dd507b9a50a88baa208a | non mesure avant |
| 16 | 37aeb9a60f313783b733aa052778019feb96ed045c7e298b2b5a8d881760e84d | non mesure avant |
| 17 | 823339465d1af651cd4be44c12692a51b75efbf1b85e463cd401cef7c29252b0 | non mesure avant |
| 18 | e1f7986ee6af58edfe36ce3b666d326da6db2f9eae2cee26f845b8e34b5a8a7c | non mesure avant |
| 19 | ab88307d69ad8c9d3874051ea8aa7d8b9a33a3e6b701e022d817b554fd3a96d0 | non mesure avant |
| 20 | d3d9395c96fb522458542e0181149dc0f7169dab0111c66e6c763612ff70b415 | non mesure avant |
| 21 | dc6943353ad8885c649d0f77dc95819400c32afd5a9620cce2c3727277c019ec | non mesure avant |
| 22 | 88965c047fed88d768657e8cf97f40bb1b55a66650a567fadd950899f967502e | non mesure avant |


## R7. Ce qui tient, ce qui ne tient pas (au sens du critere lu, sans jugement)

- **Tient a 744 x 551** : `-07` 20 items sur 20 ; `MAT-CE1-01` 22 sur 22 (marge minimale 3 px, item 6) ; `-06` 15 sur 20 ; `EMC` 13 sur 24 ; `-11` 2 sur 24 (23 et 24). A 992 et 1200 : tous les items des cinq paquets. Chemin de sortie au-dessus du pli dans tous les etats.
- **Ne tient pas** : `-06` 14, 16, 17 (1 px, 2 ordres sur 6), 19, 20 ; `EMC` 2, 8, 11, 13, 16, 17, 20, 21, 22, 23, 24 ; `-11` 1 a 22.
- **Observe, sans cause isolee par une variante** : dans `-11`, 22 items ont trois rangees de propositions (492) et un indice de 1 ou 2 lignes ; 14 items ont un indice de 2 lignes (596), 8 un indice de 1 ligne (566), les items 23 et 24 un indice de 1 ligne a 492. Dans `MAT`, les propositions sont courtes et tiennent sur 1 rangee pour 17 items (296 ou 344), sur 2 ou 3 rangees pour 5 (items 1, 2, 3, 5, 6). Je releve des cotes.

## R8. Ce que je n'ai pas mesure

- Les neuf polices imposees, un appareil reel, le clavier, le focus, un doigt. Aucune variante de style.
- Une seule police (celle du poste) et un seul navigateur. Le releve brut (JSON, 20 Mo environ) reste hors depot.
- Les corrections de gabarit ou de feuille : il n'y en a pas, le jar est mesure tel quel.

## R9. Isolation

Un jar sur le port 18681 (PID 59992, arrete par ce PID), un Chrome sur le port 19681 avec son profil jetable (PID 60808). Aucun processus arrete par nom d'image. Worktree `s57-ines-p3` (avance en avant rapide sur `round-two`, branche `s57-ines-p3`, aucun commit dans le checkout principal, aucune fusion). Aucun fichier d'un tiers deplace ou supprime.

## Decisions prises (remesure)

**Aucune.** Cette section ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done. PL-9 et PL-10 sont a allouer au registre par `theo-archi`.


---

# Correctif de gabarit du pli du 2026-10-21 (propose, mis en oeuvre, remesure), 744 x 551 apres une erreur

- **Auteure** : `ines-design`. **Date** : 2026-10-21, 11:27 (UTC+02:00), lue par `date` (mesures de 10:59 a 11:16).
- **Ce que cette section est** : la reponse a `lecture-nadia-palier-3.md` (S2) : un correctif de **gabarit** pour `-11`, `-06` et `EMC`, propose et mis en oeuvre dans la feuille maison, avec la remesure des cinq paquets. Elle compte des pixels. **Elle ne prononce aucun Done, ne juge aucun contenu et ne decide rien** : le veto est a `nadia-prof`, qui peut refuser l'un ou l'autre des deux commits (C1).
- **Identifiants** (provisoires, **non alloues** : `theo-archi` doit les allouer au registre ou en retenir d'autres, et reporter les codes ici) : **PL-11** = la regle de base du correctif (en-tete plus mince, interligne de l'enonce et des propositions) ; **PL-12** = la regle du score a droite de la consigne, separee dans son propre commit ; **PL-13** = ce qui reste sous le pli apres le correctif (C8). Ils ne sont ecrits ni dans `recreo.css` ni dans les tests : `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest` refuse un identifiant que le registre n'alloue pas, et je l'ai appris en le lancant.

## C1. Ce qui a change

Arbre : `round-two` a `a0a69859b9db0e7ea9f1f4c85c93b26439979b43` a la coupe (`git rev-parse round-two`, relu ensuite : `6927d926`, un seul commit de `camille-po` en plus, aucun fichier de `adapters/`), worktree dedie `recreo-ines-p3-pli`, branche `s57-ines-p3-pli`. Rien n'est fusionne ni pousse. Aucun gabarit n'est modifie (`git diff a0a69859 edf7f344 -- adapters/adapter-web/src/main/resources/templates` : vide) : l'ordre du document, donc de lecture et de tabulation, est celui d'avant.

| Commit | Fichier | Regle, sous `@media (min-width: 720px)` | Effet mesure |
|---|---|---|---|
| `b383b0bb` (PL-11) | `static/css/recreo.css` | `.entete { padding: 0.5rem 0; }` | l'en-tete passe de 82 a 66 px (-16) ; le bouton de sortie garde sa taille (109 x 50) |
| `b383b0bb` (PL-11) | idem | `.ecran-de-jeu:has(.propositions):not(.ecran-deux-oeuvres) .enonce { line-height: 1.25; }` | l'interligne passe de 1,5 (bibliotheque tierce ; mesure : 48 px pour 32 px) a 1,25 (40 px) : -16 px pour un enonce sur deux lignes |
| `b383b0bb` (PL-11) | idem | meme selecteur, `.proposition { line-height: 1.25; }` | la proposition passe de 680 x 66 a 680 x 58 : -8 px par rangee, -24 px pour trois rangees |
| `edf7f344` (PL-12) | idem | `.ecran-de-jeu:has(.propositions):not(.ecran-deux-oeuvres):not(:has(.bloc-adulte)) > .score { float: right; margin-left: 1rem; }` | le score se lit a droite de la ligne de la consigne : -30 px. Il reste le premier enfant de l'ecran dans le document |
| les deux | `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest.java` et `src/test/resources/contreexemple/LaFormeCommuneDesPropositionsLonguesEstEpingleeTest/` | les quatre valeurs sont ajoutees a la forme epinglee (l'en-tete entre dans la population par son nom, seulement sous 720 px) ; trois jeux fautifs commites (`interligne-change`, `entete-change`, `score-sans-flottant`) et trois tests qui les mordent | une valeur qui change, ou une regle qui s'ajoute, rougit au lieu d'invalider la mesure sans bruit |

- **Ce qui ne bouge pas** (releve sur le DOM, `getComputedStyle`, avant et apres, apres une erreur sur `-11`) : `font-size` de l'enonce et de la proposition (32 px), de la consigne, de l'indice, du verdict et du score (20 px) ; `padding` de la proposition (8 px 20 px), du bouton de sortie (8 px 17,6 px) et des puces de niveau (8 px 14,4 px) ; `min-height` (44 px) ; graisse ; couleurs ; largeur des propositions (680 px) ; boite du signal (240 x 76) et de l'indice (444 x 80). L'interligne du **texte-support n'est pas touche** (aucune regle ne le nomme) : c'est le texte que l'enfant lit en continu.
- **Ce qui bouge pour l'enfant** : tout le jeu monte de 16 px (en-tete), les lignes de l'enonce et des propositions sont un peu plus serrees (la lettre est la meme), et le score est a droite de la consigne au lieu d'avoir sa ligne (comme sur l'ecran a deux oeuvres). L'en-tete est aussi plus mince sur l'accueil et les listes de jeux (mesure : 82 -> 66 sur `/`, `/niveaux/CE1/matieres/francais` et `/niveaux/CE1/matieres/vivre-ensemble`) : la regle porte sur la coquille permanente, pas sur un ecran. Sous 720 px rien ne change.
- **Les conditions de `nadia-prof`** : (a) la taille du texte que lit l'enfant est inchangee (ligne precedente) ; (b) les trois propositions restent sur le meme ecran que l'enonce (bas de la proposition la plus basse : 470 au plus, C5) ; (c) l'ordre est celui du document. **Son veto** (cacher le texte support ou les propositions) : aucune regle ne masque, ne tronque ni ne deplace hors de l'ecran un element de lecture ; `scrollWidth` n'excede `clientWidth` dans aucun des 1100 etats releves apres une erreur (110 items x 10 largeurs-modes).

## C2. Ce que j'ai ecarte, et pourquoi

| Levier | Statut | Motif |
|---|---|---|
| Reduire la taille ou la graisse du texte | **ecarte** | exigence (a) de `nadia-prof` |
| Cacher ou tronquer le texte-support, les propositions | **ecarte** | son veto |
| Retirer le visage, les etoiles, abaisser le bandeau ou le socle (`L1`) | **non touche** | `reponse-nadia-forme2-palier-1.md`, section 3 : *"on cherche sans eux"* ; le meuble garde sa hauteur |
| Rembourrage vertical de la proposition sous 0,5 rem | **non essaye** | `ADR-0017` compare le rembourrage vertical de la proposition a celui du bouton de sortie (0,5 rem de part et d'autre) : descendre sous 0,5 rem la ferait passer sous la sortie. Abaisser aussi celui de la sortie l'amenuiserait, ce que la saillance ne demande pas |
| Ecart entre deux rangees de propositions | **non touche** | deja a 0,5 rem sous 720 px (forme commune R3-3), sous le plancher de 1 rem d'`ADR-0026` ; je n'y ajoute rien |
| Deux colonnes de propositions | **non essaye** | une rangee prend deja ce qui tient : les items a trois rangees sont ceux ou une proposition occupe plus de la moitie de la largeur (680 px) et ne peut pas en partager une. Une grille qui regroupe les plus courtes placerait avant d'autres propositions qui les precedent dans le document : l'ordre visuel ne serait plus celui de la lecture et de la tabulation, et la couleur par position (`nth-child`) ne dirait plus la meme chose. Ce choix ne m'appartient pas ; non mesure |
| Interligne sous 1,25 | **non retenu** | 1,2 gagne 1,6 px par ligne : rien de mesurable contre une lecture plus serree |
| Score a droite de la consigne (`L2`) | **propose, commit separe** | `reponse-nadia-forme2-palier-1.md`, section 3 : *"`L2` me coute moins que `L1` ; je ne l'accorde pas pour autant d'avance"*. Les cotes avec et sans sont en C6 : sans cette regle, un item de `-06` reste sous le pli |

## C3. Arbre, jar, methode

- **Jar** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` sur `edf7f344` (sortie 0), copie hors depot, SHA-256 `f34578e4a5aa4644d430fa31f848bccbe09438009202dd9303fbf2a4c4493579`. Feuille servie `/css/recreo.css` : SHA-256 `4b1294bd31474bbdb2a772e7b88ebab3bd499c00853a54e711ae5aee4fd75cf2`, egale a celle du worktree, blob `422180644bd9c9f827be3a80e2cd0c269380d40d`. JDK 21.0.10, port 18692, PID 39956 lu sur le port en ecoute, arrete par ce PID (le port ne repond plus). Un premier jar, construit un quart d'heure plus tot, differait par deux mots de commentaire (les codes PL-11 et PL-12, retires pour le test du registre) : il avait donne les memes cotes, et c'est le second qui est decrit ici.
- **Methode** : celle de la section 1, inchangee : meme pilote (`pilote-p3.mjs`, hors depot, avec deux ajouts de releve : la ligne de consigne et la boite du score), Chrome 155.0.8059.85 sans tete, **un seul navigateur a mon nom a la fois** (PID 40980 pour la mesure de reference, 47540 pour la mesure sans la regle du score ; profil jetable propre, ports de debogage 19698 et 19699 ; aucun processus ne portait plus ces profils a la fin, controle par liste), cinq largeurs (720, 744, 768, 992, 1200), hauteur 551, deux modes (avec et sans barre), arrivee, erreur 1 (six ordres), erreur 2, revelation (six ordres). Aucune regle injectee dans la mesure de reference. Critere : bas de `.indice` au plus a 551.
- **Couverture** : cinq paquets en entier, 110 items sur 110, 4400 etats (110 items x 4 etats x 10 combinaisons largeur-mode). Texte rendu egal au YAML pour les 110. **Une anomalie** : `-06`, dans une partie sur six, la revelation de l'item 5 n'est pas arrivee dans le delai du pilote (`pas de revelation`) ; l'item a ete mesure en entier dans une autre partie (20 sur 20, aucun item manquant). Les cotes de `-06` sont celles de la partie qui a abouti.
- **Avant** : les cotes de la section R4 (jar de `4650aeab` ; `git diff --stat 4650aeab a0a69859 -- adapters/adapter-web/src/main` : vide), que je relis sans les rejouer.
- **Limites** : une seule police (celle du poste), un seul navigateur, pas d'appareil reel, une seule hauteur (551), ni clavier ni doigt. Le releve brut (JSON, 20 Mo environ) reste hors depot.

## C4. Resultat a 744 x 551, mode sans barre, apres une erreur

| Paquet | Items | Bas de `.indice` au plus a 551, avant | idem, apres | Max du bas de `.indice`, avant | idem, apres | Max du bas du signal, apres | Items dont le signal depasse 551, apres |
|---|---|---|---|---|---|---|---|
| `-07` | 20 | 20 sur 20 | **20 sur 20** | 508 | 438 | 464 | 0 |
| `-06` | 20 | 15 sur 20 | **20 sur 20** | 630 | 544 | 570 | 1 (item 14) |
| EMC | 24 | 13 sur 24 | **24 sur 24** | 596 | 510 | 510 | 0 |
| `-11` | 24 | 2 sur 24 | **24 sur 24** | 596 | 510 | 510 | 0 |
| `MAT` | 22 | 22 sur 22 | **22 sur 22** | 548 | 470 | 470 | 0 |

Le bas de `.indice` est mesure dans les six ordres des trois propositions ; *au-dessus* = au plus a 551 dans les six. **Marge la plus faible** : `-06` item 14, 544 (7 px), dans les deux ordres sur six ou ses trois propositions ont chacune leur rangee ; les quatre autres paquets ont 41 px ou plus (EMC et `-11` 510, `MAT` 470, `-07` 438).

## C5. Largeurs et modes

Nombre d'items dont le bas de `.indice` depasse 551 apres une erreur, dans au moins un des six ordres (entre parentheses : dans les six), puis valeur maximale du bas de `.indice`.

| Largeur, mode | `-07` | `-06` | EMC | `-11` | `MAT` |
|---|---|---|---|---|---|
| 720, barre | 0 (0), max 438 | 0 (0), max 544 | 0 (0), max 510 | 0 (0), max 510 | 0 (0), max 470 |
| 720, sans | 0 (0), max 438 | 0 (0), max 544 | 0 (0), max 510 | 0 (0), max 510 | 0 (0), max 470 |
| 744, barre | 0 (0), max 438 | 0 (0), max 544 | 0 (0), max 510 | 0 (0), max 510 | 0 (0), max 470 |
| 744, sans | 0 (0), max 438 | 0 (0), max 544 | 0 (0), max 510 | 0 (0), max 510 | 0 (0), max 470 |
| 768, barre | 0 (0), max 438 | 0 (0), max 544 | 0 (0), max 510 | 0 (0), max 510 | 0 (0), max 470 |
| 768, sans | 0 (0), max 438 | 0 (0), max 544 | 0 (0), max 510 | 0 (0), max 510 | 0 (0), max 470 |
| 992, barre | 0 (0), max 372 | 0 (0), max 438 | 0 (0), max 414 | 0 (0), max 440 | 0 (0), max 440 |
| 992, sans | 0 (0), max 372 | 0 (0), max 438 | 0 (0), max 414 | 0 (0), max 440 | 0 (0), max 440 |
| 1200, barre | 0 (0), max 342 | 0 (0), max 438 | 0 (0), max 374 | 0 (0), max 374 | 0 (0), max 374 |
| 1200, sans | 0 (0), max 342 | 0 (0), max 438 | 0 (0), max 374 | 0 (0), max 374 | 0 (0), max 374 |

- 720 avec barre rend les memes cotes que 744 (avant le correctif, trois items de `EMC` et un de `MAT` y descendaient de 30 a 74 px de plus, PL-6) ; le maximum est le meme dans les deux modes.
- Aucune consigne ne passe a deux lignes a cote du score : 1 ligne dans les 1100 etats releves apres une erreur, pour les 110 items. Le score finit a 96 (haut a 66), la consigne aussi.
- Boutons de reponse au-dela de 551 apres une erreur : **aucun** (le cas de `-06` 14 a 556 a disparu ; bas maximal 470). Chemin de sortie (`a.btn.lien-accueil`, `a.btn.chip-niveau`) : bas a 58 dans les 1100 etats releves apres une erreur (66 avant). Aucun debordement horizontal.

## C6. Les items sous le pli avant, un par un, et la cote sans la regle du score

Colonnes : bas de `.indice`, en pixels, a 744 sans barre, dans les six ordres (min-max). **Sans la regle du score** = le meme jar, une seule regle annulee a l'execution (`float: none; margin-left: 0`) : c'est l'etat du commit `b383b0bb` seul, mesure sur les 110 items a 744 uniquement.

**`-06`**

| Item | Rangees | Bas de `.indice`, avant (min-max) | apres (min-max) | Bas du signal, apres (max) | sans la regle du score, 744 (min-max) |
|---|---|---|---|---|---|
| 14 | 2-3 | 556-630 | 478-544 | 570 | 508-574 |
| 16 | 3 | 582 | 504 | 530 | 534 |
| 17 | 2-3 | 478-552 | 408-474 | 500 | 438-504 |
| 19 | 3 | 582 | 504 | 530 | 534 |
| 20 | 3 | 582 | 504 | 530 | 534 |

**EMC**

| Item | Rangees | Bas de `.indice`, avant (min-max) | apres (min-max) | Bas du signal, apres (max) | sans la regle du score, 744 (min-max) |
|---|---|---|---|---|---|
| 2 | 2-3 | 492-566 | 414-480 | 506 | 444-510 |
| 8 | 3 | 566 | 480 | 506 | 510 |
| 11 | 2-3 | 522-596 | 444-510 | 510 | 474-540 |
| 13 | 3 | 566 | 480 | 506 | 510 |
| 16 | 3 | 566 | 480 | 506 | 510 |
| 17 | 2-3 | 492-566 | 414-480 | 506 | 444-510 |
| 20 | 3 | 566 | 480 | 506 | 510 |
| 21 | 3 | 596 | 510 | 510 | 540 |
| 22 | 3 | 566 | 480 | 506 | 510 |
| 23 | 3 | 566 | 480 | 506 | 510 |
| 24 | 3 | 566 | 480 | 506 | 510 |

**`-11`** (3 rangees dans les 22 items ; regroupes par cote)

| Items | Bas de `.indice`, avant | apres | Bas du signal, apres | sans la regle du score, 744 |
|---|---|---|---|---|
| 1, 2, 3, 6, 7, 8, 11, 12, 13, 16, 17, 18, 21, 22 | 596 | 510 | 510 | 540 |
| 4, 5, 9, 10, 14, 15, 19, 20 | 566 | 480 | 506 | 510 |

Comptes, jar entier contre jar sans la regle du score (744, sans barre, apres une erreur) :

| Paquet | Items au-dessus (avec la regle) | Items au-dessus (sans la regle) | Max `.indice` avec | Max `.indice` sans | Items dont le signal depasse 551, avec | Items dont le signal depasse 551, sans |
|---|---|---|---|---|---|---|
| `-07` | 20 sur 20 | 20 sur 20 | 438 | 468 | 0 | 0 |
| `-06` | 20 sur 20 | 19 sur 20 | 544 | 574 | 1 | 4 |
| EMC | 24 sur 24 | 24 sur 24 | 510 | 540 | 0 | 0 |
| `-11` | 24 sur 24 | 24 sur 24 | 510 | 540 | 0 | 0 |
| `MAT` | 22 sur 22 | 22 sur 22 | 470 | 500 | 0 | 0 |

**Lecture, sans jugement** : le commit `b383b0bb` seul met 109 items sur 110 au-dessus du pli. L'item restant est `-06` 14 : bas de `.indice` a 574 (23 px sous le pli) dans les deux ordres sur six ou ses propositions occupent trois rangees. La regle du score (`edf7f344`) l'amene a 544. C'est la seule difference de compte entre les deux etats ; les autres paquets gagnent 30 px de marge avec la regle, sans changer de camp. Si `nadia-prof` refuse la regle du score, il faut un autre levier pour cet item ; je n'en ai pas qui respecte ses conditions.

## C7. En dehors du critere lu, pour information : revelation et seconde erreur

| Paquet | Revelation : `.indice` au-dela de 551 (au moins un ordre), avant | apres | Seconde erreur : `.indice` au-dela de 551 (ordre servi), avant | apres |
|---|---|---|---|---|
| `-07` | 9 sur 20 | 0 sur 20 | 0 sur 20 | 0 sur 20 |
| `-06` | 14 sur 20 | 5 sur 20 | 4 sur 20 | 0 sur 20 |
| EMC | 22 sur 24 | 12 sur 24 | 9 sur 24 | 0 sur 24 |
| `-11` | 24 sur 24 | 22 sur 24 | 22 sur 24 | 0 sur 24 |
| `MAT` | 3 sur 22 | 1 sur 22 | 0 sur 22 | 0 sur 22 |


Le critere de `nadia-prof` est l'indice **apres une erreur**. A la revelation (la reponse est affichee en plus du signal et de l'indice), 40 items sur 110 ont encore le bas de `.indice` au-dela de 551 dans au moins un ordre, dont 22 sur 24 pour `-11` : la revelation ajoute deux lignes de texte au message. Je n'ai rien fait pour elle, personne ne me l'a demande.

## C8. Ce qui tient encore sous le pli (PL-13)

- **Le signal de `-06` item 14** (l'icone et le verdict, a gauche de l'indice) finit a 570, 19 px sous le pli, dans 2 ordres sur 6. Son haut est a 494 ; l'indice, a droite, finit a 544. Aucun autre item des cinq paquets n'a le signal au-dela de 551.
- **La revelation** (C7).
- **Marge de 7 px** sur `-06` 14 : un correctif de contenu qui ajoute une ligne a son texte, une proposition qui change de rangee, ou une police plus haute que celle du poste la ferait disparaitre. La condition de validite des cotes est celle de la section 6 : un hash d'item different, ou un `git diff --stat edf7f344 <sommet> -- adapters/adapter-web/src/main` non vide, et les cotes sont a refaire.
- **Non mesure** : les ecrans a bloc adulte (la regle du score les exclut ; l'en-tete, lui, y est plus mince), l'ecran a deux oeuvres (exclu des trois regles d'ecran ; son en-tete est aussi 16 px plus mince, ses cotes ne sont pas rejouees ici), les jeux de saisie et de designation (aucune regle d'ecran ne les nomme), les largeurs sous 720 px.

## C9. Saillance

`ADR-0017` garde la taille de police, la hauteur minimale et le rembourrage ; il ne garde ni la couleur, ni la position, ni la largeur des libelles, ni la hauteur reelle d'une boite. Releve, `-11`, 744 sans barre :

| Boite | Avant | Apres |
|---|---|---|
| Proposition (action) | 680 x 66 | 680 x 58 |
| `Les jeux` (sortie) | 109 x 50 | 109 x 50 |
| Puces de niveau | 56 a 74 x 50 | inchange |
| Rapport de hauteur proposition / sortie | 1,32 | 1,16 |
| Position de la sortie dans la page | 16 a 66 | 8 a 58 |
| Couleur, largeur, contour des deux familles | - | inchanges |

La proposition garde son aplat plein, sa largeur (six fois celle de la sortie) et une hauteur superieure a celle de la sortie ; elle perd huit pixels de hauteur, ce qui rapproche les deux. C'est le point a regarder avec la couleur et la position, que le garde-fou ne tient pas : l'oeil de `nadia-prof` et le mien.

## C10. Tests

Mesures partielles, `-am` porte, sur l'arbre `edf7f344` : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web,bootstrap -am test` (modules construits : `domain`, `application`, `adapter-content`, `adapter-web`, `bootstrap`). Je n'ai **pas** lance `verify` complet (d'autres agents compilent ; `sofia-sm` le fera apres fusion), ni les jobs de la CI autres que ce contenu partiel : la porte technique n'est pas annoncee ici.

Resultat, relu dans la sortie Maven (`EXIT 0`, `BUILD SUCCESS`, 9 min 22 s) :

| Module | Tests | Echecs | Erreurs | Ignores |
|---|---|---|---|---|
| `domain` | 120 | 0 | 0 | 0 |
| `application` | 31 | 0 | 0 | 0 |
| `adapter-web` | 519 | 0 | 0 | 0 |
| `adapter-content` | 1794 | 0 | 0 | 0 |
| `bootstrap` | 381 | 0 | 0 | 0 |

- `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` : 8 tests, 0 echec (5 existants, 3 neufs : un par jeu fautif commite). Les quatre valeurs du correctif y sont epinglees ; une valeur d'interligne, de marge d'en-tete ou de flottant qui change fait rougir.
- Ce qui a ete rouge en route, et pourquoi c'est ecrit ici : (1) apres le commit de base, le test de la forme commune a echoue tant que les quatre valeurs n'y etaient pas ajoutees (1 echec sur 516, aucun autre garde-fou de `adapter-web`) ; (2) `ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest.leDepotReelEstCoherent` (module `bootstrap`) a echoue sur mes deux commentaires qui citaient PL-11 et PL-12 : les codes sont retires de la feuille et des tests, et les deux commits ont ete refaits avant la mesure decrite ici.
- Aucun garde-fou existant sur la feuille (`TailleDesTextesEtBoutonsTest`, `LActionNeDeclarePasMoinsQueLaSortieTest`, `LaLargeurOffreeAUneRangeeEstUneFonctionEpingleeTest`, `LaLargeurDemandeeParUneRangeeEstEpingleeTest`, `UnEcranNeGouvernePasLApparenceDUnAutreTest`, `CabinetDeLaMachineTest`) n'a ete modifie ni assoupli. Les regles ajoutees ne declarent ni `font-size`, ni `min-height`, ni `padding` sur un bouton, ni `gap`, ni terme lateral sur la chaine d'une rangee, ni `width` ; elles ne sont ni imbriquees ni suivies d'une virgule de premier niveau, et aucune ne nomme une police.
- Scripts de la CI lances en local sur mon diff : `verifier-prefixes.sh` sur `a0a69859..HEAD` (sortie 0, *"Tous les commits portent un prefixe d'agent connu"*) et `verifier-chemins.sh` sur `git ls-files` (sortie 0). **Non lances** : le job `build` complet (`mvn -B verify`) et le job des juges purs des outils.


## C11. Isolation

Un jar sur le port 18692 (PID 39956) et, plus tot, deux sur 18690 et 18691 (PID 43096 et 34404) : tous arretes par PID, aucun processus arrete par nom d'image. Un Chrome a la fois a mon nom, profils jetables sous le repertoire de travail de la session. Worktree `recreo-ines-p3-pli` (branche `s57-ines-p3-pli`) laisse en place, hors du checkout principal. Aucun fichier d'un tiers deplace ou supprime.

## Decisions prises (correctif du pli)

**Aucune.** Cette section ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve et ne prononce aucun Done. PL-11, PL-12 et PL-13 sont a allouer au registre par `theo-archi`. Les commits sont locaux, non fusionnes, non pousses ; `nadia-prof` peut refuser `edf7f344` sans toucher a `b383b0bb`.



---

# Remesure du 2026-10-21 sur l'arbre fusionne (`round-two` + `s57-ines-p3-pli`), a la demande du veto de `nadia-prof`

- **Auteure** : `ines-design`. **Date** : 2026-10-21, 12:17 (UTC+02:00), lue par `date` (mesures de 12:01 a 12:15). Cette section complete les precedentes, qui ne sont pas modifiees. Elle compte des pixels : aucun Done, aucune decision de design, aucun commit de gabarit ou de feuille. Le veto sur `edf7f344` est a `nadia-prof` (`veto-nadia-gabarit-pli.md`, `8805c4dc`).
- **Nombre de commits de la branche** : `git log round-two..s57-ines-p3-pli` rend **trois** commits `[INES]`, pas quatre : `b383b0bb` (PL-11, gabarit), `edf7f344` (PL-12, gabarit), `af8fa4c9` (la mesure, un fichier markdown). Deux commits touchent `recreo.css`, un seul touche la mesure.
- **Identifiants** : PL-11, PL-12 et PL-13 gardent leur sens (PL-11 regle de base, PL-12 score a droite, PL-13 ce qui tient encore sous le pli). **Je n'ai pas besoin de nouvel identifiant** : le constat des consignes est le cout de PL-12, celui du verdict et de la reponse attendue prolonge PL-13. Les deux reserves que `nadia-prof` date du 2026-10-21 (verdict de `-06` 14, phrase et reponse a la revelation) sont a nommer par elle et `camille-po`, pas par moi.

## D1. Arbre, jar, methode

- **Arbre** : worktree d'integration jetable `recreo-ines-integ-pli`, coupe de `round-two` a `8805c4dcdde05181a3b0434a757e26cfe9e8cbd4`, puis `git merge --no-ff s57-ines-p3-pli` (sans conflit) : commit de fusion local `bee3bb37b80f634c58beb827823fb16f3731f55d`, **non pousse, non fusionne dans `round-two`**, parents `8805c4dc` et `af8fa4c9`. `git diff --stat round-two HEAD -- adapters/adapter-web/src/main` : un fichier, `recreo.css`, 25 lignes ajoutees (les deux regles de `b383b0bb` et `edf7f344`) ; aucun gabarit. Feuille servie : SHA-256 `4b1294bd31474bbdb2a772e7b88ebab3bd499c00853a54e711ae5aee4fd75cf2`, **egale** a celle du jar de `edf7f344` mesure en C3.
- **Contenu depuis ma derniere mesure** (`4650aeab` -> `8805c4dc`) : le hash de l'item a change pour `-06` 14 (et son texte-support : *"La pluie se met a tomber."*), `EMC` 8, 9 et **14** ; aucun pour `-07`, `-11`, `MAT-CE1-01`. Meme methode de hash que la section 6 (recalcul Python compare au pilote : 0 ecart sur 110 items). `EMC` 14 n'est pas dans la liste du veto, le hash le dit.
- **Jar** : `bash outils/mvn-jdk21.sh -B clean package -DskipTests` (sortie 0, tout le reacteur, pas de mesure partielle), copie hors depot, SHA-256 `96be35bf926e4987c9fdb7e18b4315978335ec12a0eeef728228d21b3f4bdce0`. Port 18691, PID 50052 lu sur le port en ecoute, arrete par ce PID.
- **Methode** : celle de la section 1 (pilote `pilote-p3.mjs`, avec en plus le bas de `.reponse-revelee` et de `.reponse-attendue` dans chacun des six ordres). Un seul Chrome a mon nom a la fois (PID 37484 puis 42436, profils jetables, ports 19691 et 19692, disparus a la fin de chaque pilote : controle par liste), cinq largeurs (720, 744, 768, 992, 1200), hauteur 551, deux modes (avec et sans barre), arrivee, erreur 1 et revelation dans les six ordres, erreur 2. Aucune regle de style injectee pour les paquets. Critere : bas de `.indice` au plus a 551.
- **Couverture** : 5 paquets en entier, 110 items sur 110, 0 anomalie, texte rendu egal au YAML.
- **Limites** : une police (celle du poste), un navigateur, pas d'appareil reel. Les bas de boite sont mesures ; je n'ai pas mesure le bas des glyphes d'une ligne coupee.

## D2. Cotes apres fusion, 744 x 551, apres une erreur (PL-11, PL-12)

| Paquet | Items | Au plus a 551 dans les six ordres, avant correctif (section R2) | idem, apres fusion | Max du bas de `.indice` | Marge la plus faible | Max du bas du signal | Items dont le signal depasse 551 |
|---|---|---|---|---|---|---|---|
| `-07` | 20 | 20 | **20** | 438 | 113 | 464 | 0 |
| `-06` | 20 | 15 | **20** | 544 (item 14) | **7** (item 14) | 570 | **1** (item 14, 2 ordres sur 6) |
| `EMC` | 24 | 13 | **24** | 510 | 41 | 506 | 0 |
| `-11` | 24 | 2 | **24** | 510 | 41 | 506 | 0 |
| `MAT-CE1-01` | 22 | 22 | **22** | 470 | 81 | 466 | 0 |

- **De 720 a 768, avec et sans barre, et a 992 et 1200 : 0 item** des 110 a un bas d'indice au-dela de 551. Aucun debordement horizontal.
- **Les maxima sont ceux de la section C4** (jar de `edf7f344` sur `a0a69859`) : `-06` 544, `EMC` 510, `-11` 510, `-07` 438, `MAT` 470. Les corrections de contenu fusionnees depuis (`-06` 14, `EMC` 8, 9, 14) ne les deplacent pas : `EMC` 8 est a 440, 9 a 374, 14 a 444.
- **`-06` item 14** : texte-support de 2 lignes, indice de 1 ligne, propositions sur 2 rangees (bas 404, indice 478) ou 3 rangees (bas 470, indice 544) selon l'ordre. Dans les 2 ordres sur 6 a trois rangees (`021` et `120`), le signal occupe 494 a 570 et **le verdict (boite de 60 px, deux lignes de 30 px : *"Ce n'est pas ça. Essaie encore !"*) occupe 502 a 562** : la premiere ligne finit a 532, la seconde a 562. La boite du verdict depasse 551 de 11 px, celle du signal de 19 px. Je n'ai pas mesure si le trait des lettres de la seconde ligne est coupe.
- **Boutons de sortie et de niveau** (`a.btn.lien-accueil`, `a.btn.chip-niveau`) : bas a **58** (66 avant `b383b0bb`) dans les 4400 etats.

### Cotes par item, `FRA-CE1-06`, `ce1-inference` (744, sans barre, apres une erreur, six ordres)

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 2 | 1 | 124 | 268-268 | 1-1 | 342-342 | au-dessus |
| 2 | 3 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 3 | 3 | 1 | 124 | 334-334 | 2-2 | 408-408 | au-dessus |
| 4 | 3 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 5 | 3 | 2 | 154 | 298-298 | 1-1 | 372-372 | au-dessus |
| 6 | 3 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 7 | 3 | 2 | 154 | 298-298 | 1-1 | 372-372 | au-dessus |
| 8 | 3 | 2 | 154 | 298-298 | 1-1 | 372-372 | au-dessus |
| 9 | 3 | 2 | 154 | 298-298 | 1-1 | 372-372 | au-dessus |
| 10 | 3 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 11 | 3 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 12 | 3 | 2 | 154 | 298-298 | 1-1 | 372-372 | au-dessus |
| 13 | 3 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 14 | 4 | 2 | 154 | 404-470 | 2-3 | 478-544 | au-dessus |
| 15 | 2 | 2 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 16 | 3 | 2 | 154 | 430-430 | 3-3 | 504-504 | au-dessus |
| 17 | 2 | 1 | 124 | 334-400 | 2-3 | 408-474 | au-dessus |
| 18 | 3 | 1 | 124 | 334-334 | 2-2 | 408-408 | au-dessus |
| 19 | 3 | 2 | 154 | 430-430 | 3-3 | 504-504 | au-dessus |
| 20 | 3 | 2 | 154 | 430-430 | 3-3 | 504-504 | au-dessus |

### Cotes par item, `EMC-CE1-01`, `ce1-emc-biens-communs` (aucun texte-support)

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 0 | - | - | 300-300 | 2-2 | 404-404 | au-dessus |
| 2 | 0 | - | - | 340-406 | 2-3 | 414-480 | au-dessus |
| 3 | 0 | - | - | 340-340 | 2-2 | 444-444 | au-dessus |
| 4 | 0 | - | - | 340-340 | 2-2 | 414-414 | au-dessus |
| 5 | 0 | - | - | 300-300 | 2-2 | 374-374 | au-dessus |
| 6 | 0 | - | - | 340-340 | 2-2 | 444-444 | au-dessus |
| 7 | 0 | - | - | 300-300 | 2-2 | 374-374 | au-dessus |
| 8 | 0 | - | - | 366-366 | 3-3 | 440-440 | au-dessus |
| 9 | 0 | - | - | 300-300 | 2-2 | 374-374 | au-dessus |
| 10 | 0 | - | - | 300-300 | 2-2 | 404-404 | au-dessus |
| 11 | 0 | - | - | 340-406 | 2-3 | 444-510 | au-dessus |
| 12 | 0 | - | - | 340-340 | 2-2 | 444-444 | au-dessus |
| 13 | 0 | - | - | 406-406 | 3-3 | 480-480 | au-dessus |
| 14 | 0 | - | - | 340-340 | 2-2 | 444-444 | au-dessus |
| 15 | 0 | - | - | 366-366 | 3-3 | 440-440 | au-dessus |
| 16 | 0 | - | - | 406-406 | 3-3 | 480-480 | au-dessus |
| 17 | 0 | - | - | 340-406 | 2-3 | 414-480 | au-dessus |
| 18 | 0 | - | - | 340-340 | 2-2 | 414-414 | au-dessus |
| 19 | 0 | - | - | 366-366 | 3-3 | 470-470 | au-dessus |
| 20 | 0 | - | - | 406-406 | 3-3 | 480-480 | au-dessus |
| 21 | 0 | - | - | 406-406 | 3-3 | 510-510 | au-dessus |
| 22 | 0 | - | - | 406-406 | 3-3 | 480-480 | au-dessus |
| 23 | 0 | - | - | 406-406 | 3-3 | 480-480 | au-dessus |
| 24 | 0 | - | - | 406-406 | 3-3 | 480-480 | au-dessus |

Les cotes par item de `-07`, `-11` et `MAT-CE1-01` sont dans le releve brut (hors depot) ; leurs maxima sont au tableau ci-dessus.

## D3. Condition de `nadia-prof` sur `edf7f344` : chaque consigne sur une ligne a cote du score

- **Perimetre** : les **157 jeux** de `catalogue.yaml` (152 `bandit-manchot`, 3 `designation`, 2 `saisie` ; 59 CP, 40 CE1, 58 CM1, niveau lu dans le descripteur du paquet), joues un par un depuis l'accueil de leur niveau. La consigne est celle du catalogue, fixee au demarrage de la partie ; elle ne change pas ensuite : 0 changement releve entre l'arrivee et l'ecran qui suit un premier coup (152 jeux ; les 5 jeux de saisie et de designation n'ont pas de proposition a cliquer). A chaque etat : cinq largeurs x deux modes. Trois variantes sur le meme DOM : (A) telle que servie ; (B) avec "Score : 10", pire largeur du score ; (C) `float: none` sur `.score` (la consigne telle qu'elle serait avec `b383b0bb` seul).
- **Regle appliquee a** 146 jeux (propositions ecrites, sans bloc adulte, sans deux oeuvres). Non appliquee a 11 : 6 a bloc adulte (dont `saisie-mot-dicte-cp`), 4 sans `.propositions` (`saisie-complements-10-cp` et les 3 `designation`), 1 a deux oeuvres. `float` calcule du score : `right` pour 146 jeux, `none` pour 11.
- **Etat "apres un coup"** : la premiere proposition cliquee, juste ou fausse selon l'item. Je n'ai pas force une erreur sur chaque jeu.
- **Resultat (variante A)** : **31 jeux sur 157** ont une consigne sur **deux lignes** a au moins une largeur ; **126 tiennent sur une ligne partout**. Par niveau : 14 CP, 1 CE1, 16 CM1. Par largeur : 31 a 720, 30 a 744, 30 a 768, **3 a 992, 0 a 1200**. Aucun jeu sur trois lignes ou plus. Sur les 146 jeux soumis a la regle : 30 passent sur deux lignes, 116 tiennent.
- **Par quel commit** :
  - **22 jeux par `edf7f344`** : une ligne dans la variante C, deux dans la variante A. Le flottant retire a la consigne la largeur du score (environ 100 px, marge de 16 px en plus). 18 ou 19 jeux a 720, 744 et 768 ; **3 a 992** (`melanges-cm1`, `roles-eps-cm1`, `emotion-ressentir-agir-cm1`) ; `institutions-cm1` seulement a 720 avec barre, dans l'etat qui suit un premier coup.
  - **13 jeux sans le flottant** (variante C), donc ni par `edf7f344` ni par `b383b0bb` (qui ne touche ni la taille ni la largeur de la consigne) : la consigne depasse la ligne a elle seule, a 720-768. Ce sont les plus longues (81 a 107 caracteres). 4 jeux sont dans les deux listes : `melanges-cm1`, `roles-eps-cm1` et `emotion-ressentir-agir-cm1` (deux lignes a 720-768 sans flottant, et a 992 par le flottant), `hygiene-cp` (deux lignes sans flottant a 720 avec barre seulement, par le flottant aux autres largeurs). Un de ces 13 est un jeu a bloc adulte (`position-syllabe-cp`), hors de la regle.
  - **Variante B** (score a deux chiffres, pire cas ; je n'ai pas verifie qu'un ecran de manche l'affiche) : 2 jeux de plus, `etapes-developpement-cm1` (720 avec barre) et `classer-etres-vivants-cm1` (720 a 768).
- **Seuil de longueur** : tous les jeux de **67 caracteres ou moins** (108 des 146) tiennent sur une ligne a toutes les largeurs et dans les deux modes. Au-dela, la largeur des lettres compte : la plus courte consigne qui passe sur deux lignes en compte 68, la plus longue qui tient a 744 en compte 73. Les consignes des cinq paquets mesures (40 a 52 caracteres) tiennent.

### Les 31 consignes sur deux lignes

| Niveau | Jeu | Caracteres | 2 lignes a cause du score a droite (largeurs, px) | 2 lignes meme sans le flottant (largeurs, px) | Regle du score appliquee |
|---|---|---|---|---|---|
| CP | `bandit-manchot-autrefois-aujourdhui-cp` | 81 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-corps-effort-eps-cp` | 76 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-couleurs-arts-cp` | 74 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-cycle-de-vie-cp` | 76 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-emotions-autrui-cp` | 74 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-etats-eau-cp` | 81 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-formules-de-politesse-cp` | 74 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-hygiene-cp` | 81 | 720, 744, 768 | 720 (avec barre) | oui |
| CP | `bandit-manchot-matiere-objets-cp` | 99 | - | 720, 744, 768 | oui |
| CP | `bandit-manchot-objets-numeriques-cp` | 87 | - | 720, 744, 768 | oui |
| CP | `bandit-manchot-position-syllabe-cp` | 92 | - | 720, 744, 768 | non (bloc adulte) |
| CP | `bandit-manchot-regles-jeu-collectif-eps-cp` | 84 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-regles-securite-eps-cp` | 80 | 720, 744, 768 | - | oui |
| CP | `bandit-manchot-saisons-cp` | 78 | 720, 744, 768 | - | oui |
| CE1 | `bandit-manchot-longueurs-ce1` | 68 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-effets-activite-eps-cm1` | 91 | - | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-emotion-ressentir-agir-cm1` | 106 | 992 | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-energie-cm1` | 88 | - | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-etats-matiere-cm1` | 86 | - | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-fait-opinion-cm1` | 88 | - | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-familles-materiaux-cm1` | 85 | - | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-histoire-des-arts-relier-contexte-cm1` | 80 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-institutions-cm1` | 73 | 720 (avec barre) | - | oui |
| CM1 | `bandit-manchot-melanges-cm1` | 99 | 992 | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-regimes-alimentaires-cm1` | 80 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-regle-loi-cm1` | 75 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-regles-vie-collective-cm1` | 81 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-roles-eps-cm1` | 107 | 992 | 720, 744, 768 | oui |
| CM1 | `bandit-manchot-symboles-cm1` | 73 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-synonymes-cm1` | 70 | 720, 744, 768 | - | oui |
| CM1 | `bandit-manchot-vote-election-cm1` | 90 | - | 720, 744, 768 | oui |

## D4. Revelation : la phrase et la reponse attendue au-dessus de 551 ? (reserve datee du 2026-10-21 de `nadia-prof`)

Mesure : bas de `.reponse-revelee` ("Appuie sur la bonne réponse :") et de `.reponse-attendue`, dans les six ordres, 110 items, a 720, 744, 768 (avec et sans barre), 992 et 1200.

| Paquet | Phrase au-dela de 551 | Reponse attendue au-dela de 551, 744 sans barre (items, ordres sur 6) | Max du bas de la phrase | Max du bas de la reponse attendue | Reponse attendue au-dela de 551 a 720 avec barre | 992 et 1200 |
|---|---|---|---|---|---|---|
| `-07` | 0 | 0 | 418 | 470 | 0 | 0 |
| `-06` | 0 | **1** : item 14, 576 (2 ordres sur 6 ; 510 dans les 4 autres) | 524 | 576 | 1 (item 14) | 0 |
| `EMC` | 0 | **1** : item 13, 560 (6 sur 6, 3 rangees) | 460 | 560 | 2 (13 et 21) | 0 |
| `-11` | 0 | **1** : item 13, 560 (6 sur 6, 3 rangees) | 460 | 560 | 2 (13 et 18) | 0 |
| `MAT-CE1-01` | 0 | 0 | 420 | 520 | 0 | 0 |

- **La phrase** reste au-dessus du pli partout (bas 524 au plus), dans les six ordres, a toutes les largeurs.
- **La reponse attendue** depasse le pli pour **3 items sur 110** a 744 : `-06` 14 (576, 25 px sous le pli), `EMC` 13 et `-11` 13 (560, 9 px sous le pli). Pour `EMC` 13 et `-11` 13 elle tient sur 3 lignes de 32 px (boite de 96 px, de 464 a 560, colonne de 444 px de large a droite du signal) : *"elle tourne les pages doucement"* et *"Yanis ne range pas ses crayons."*. Le bas de la boite est mesure, pas le trait des lettres de la derniere ligne.
- **Boutons de proposition a la revelation** : aucun au-dela de 551 (bas 404 a 470). Les 110 revelations ont ete jouees jusqu'au bout.
- Hors critere, comme en C7 : le bas de `.indice` a la revelation reste au-dela de 551 pour 5 items de `-06`, 11 d'`EMC`, 22 de `-11`, 1 de `MAT` et 0 de `-07` (744 sans barre). `nadia-prof` n'exige rien sur l'indice a la revelation.

## D5. Ce que je propose, sans l'appliquer (`edf7f344` est a `nadia-prof`)

Le seul item qui ne tient que grace a `edf7f344` est `-06` 14 : 574 sans le flottant, 544 avec (C6, mesure faite sur l'ancien texte, non refaite sans le flottant sur le nouveau). Les 109 autres tiennent avec `b383b0bb` seul, avec 30 px de marge en moins. Trois leviers, tous non appliques :

1. **Fusionner `b383b0bb`, pas `edf7f344`** : les 22 consignes reviennent sur une ligne, les 13 qui sont sur deux lignes sans flottant y restent. `-06` 14 reste sous le pli (indice 574) : il faut un autre levier pour lui, que `nadia-prof` choisit (contenu ou gabarit).
2. **Garder le flottant pour les consignes courtes seulement** : le CSS ne connait pas la longueur d'un texte, et le rendu ne fournit aujourd'hui aucune donnee qui la porte. Il faudrait une classe ou un attribut pose par le controleur (par exemple selon un seuil de 67 caracteres, d'apres la mesure ci-dessus). **Je ne l'ecris pas : a remonter a `sofia-sm`.** Effet attendu : 108 jeux gardent le gain, 38 ne l'ont pas, `-06` 14 (consigne de 47 caracteres) le garde, ce que ce releve confirme sur les cotes de D2.
3. **Sortir le score de la ligne de la consigne** (dans la bande de l'en-tete, a droite des boutons de niveau) : modification de gabarit, de l'ordre du document (le score est aujourd'hui lu et tabule en premier) et de sa place. Non essayee, non mesuree, et elle ne se conclut pas sans l'avis de `nadia-prof`.

Je ne propose pas de reduire la consigne : sa taille et son texte relevent du veto de `nadia-prof`.

## D6. Ce qui n'est pas mesure ici

- La lecture a l'ecran (les six points de la section 5 du veto), sur laquelle je n'ai aucune autorite.
- Une erreur forcee sur chacun des 152 jeux de proposition ; la place du score sur les ecrans a deux oeuvres, a bloc adulte, de saisie et de designation (la regle ne s'y applique pas).
- Le trait des lettres de la seconde ligne du verdict de `-06` 14 et de la derniere ligne de la reponse attendue d'`EMC` 13 et `-11` 13.

## D7. Isolation

Un jar (port 18691, PID 50052) et un Chrome a la fois (PID 37484 puis 42436, ports 19691 et 19692), tous arretes par PID, aucun processus arrete par nom d'image ; les deux navigateurs ont disparu a la fin de leur pilote, le port du jar ne repond plus. Aucun fichier de `domain`, `application`, `bootstrap` ni de gabarit touche. Le worktree d'integration `recreo-ines-integ-pli` (detache, fusion locale `bee3bb37`) reste en place, jetable ; ma branche `s57-ines-p3-pli` ne gagne que cette section. Rien n'est pousse ni fusionne dans `round-two`. `mvn -B verify` n'a pas ete lance (build du jar seulement) : je n'annonce pas la porte technique.

## Decisions prises (remesure du 2026-10-21)

**Aucune.** Cette section ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve, ne prononce aucun Done et n'applique aucun des trois leviers de D5.



---

# Remesure du 2026-10-21 apres le refus d'`edf7f344` : `b383b0bb` seul sur `round-two`

- **Auteure** : `ines-design`. **Date** : 2026-10-21, 12:40 (UTC+02:00), lue par `date` (mesures de 12:31 a 12:38). Cette section suit les precedentes, reprises telles quelles de la branche `s57-ines-p3-pli` (`80781f61`), **sans qu'un chiffre y soit change**. Elle compte des pixels : aucun Done, aucune decision, aucune correction de contenu ni de gabarit. Ce que `nadia-prof` decide sur les restes sous le pli est a elle (`veto-nadia-gabarit-pli.md`, sections V1 a V5).
- **Une correction de lecture, sans changer un chiffre** : en D4, j'ai ecrit que la reponse attendue d'`EMC` 13 et de `-11` 13 "tient sur 3 lignes". C'etait une deduction de la hauteur de la boite (96 px). **Elle tient sur 2 lignes** (releve des lignes ci-dessous) ; la boite est plus haute que ses deux lignes.

## E1. Identifiants

- **PL-11** (regle de base du correctif : en-tete plus mince, interligne 1,25 de l'enonce et des propositions) : garde son sens. C'est le seul gabarit de cette branche.
- **PL-12** (score a droite de la consigne) : **retire du dispositif**. `edf7f344` est refuse par `nadia-prof` (V1) ; il n'est pas dans cette branche. Je ne reutilise pas ce code pour autre chose. Les sections precedentes qui le citent le decrivent comme une regle proposee, mesuree, refusee : je ne les modifie pas. Ce que j'en fais : rien, la regle n'existe plus dans le depot de travail de cette branche. `theo-archi` : si PL-12 a ete alloue au registre, il doit y figurer comme refuse, pas comme actif.
- **PL-13** (ce qui reste sous le pli) : garde son sens, **mis a jour par E4 ci-dessous**.
- **Identifiants dont la seule definition est dans ma mesure, a allouer par `theo-archi`** : **PL-11**, **PL-13**, et, des sections precedentes, **PL-9** et **PL-10** (le fichier ne dit pas qu'ils l'ont ete). Je n'en cree aucun nouveau ici.

## E2. Branche, tests, jar

- **Branche** : `s57-ines-p3-b383`, worktree `recreo-ines-p3-b383`, coupee de `round-two` a `859cf327ff75df615c79abc2ea9010d785947ce4`, **un seul commit** par `git cherry-pick b383b0bb` (sans conflit) : `5302af53c4ad906db86e2e1753709e11824b7962`. `s57-ines-p3-pli` est intacte (`80781f61`). `git diff --stat 8805c4dc 859cf327 -- adapters bootstrap` : vide (le contenu est celui de la section precedente).
- **Le test n'a pas eu besoin d'etre ajuste.** `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` a ete ecrit dans `b383b0bb`, avant `edf7f344` : il n'epingle que les trois valeurs de ce commit (`.entete` `padding: 0.5rem 0`, interligne 1,25 de l'enonce, interligne 1,25 de la proposition) et ses deux jeux fautifs (`entete-change`, `interligne-change`). Il ne contient ni `float` ni `margin-left` du score (le seul `.score` de la liste est la regle de forme existante `margin: 0`). Il n'y a donc pas de second commit : un commit sans changement serait faux. Les hauteurs en pixels (en-tete 66, proposition 58) ne sont pas epinglees, ce sont des cotes mesurees ; l'epingle est sur les valeurs de feuille dont elles decoulent.
- **Tests** : `bash outils/mvn-jdk21.sh -B -pl adapters/adapter-web -am test` (mesure partielle, `-am` present, sortie 0) : domain 120, application 31, adapter-web **522**, soit 673 tests, 0 echec, 0 erreur, 0 ignore. `LaFormeCommuneDesPropositionsLonguesEstEpingleeTest` : 7 tests, 0 echec. **Jobs lances : cette commande seulement.** Ni `mvn -B verify` complet, ni les autres jobs de `ci.yml` : je n'annonce pas la porte technique.
- **Jar** : `bash outils/mvn-jdk21.sh -B -pl bootstrap -am package -DskipTests` (sortie 0), SHA-256 `c3bdf2caa037a39e05b589d14d572e089bf8557290a86fb0198b7896300f1e12`. Feuille servie `/css/recreo.css` : SHA-256 `1fa775cadc55f9b7a2252312340aae077c36218b019cdbf9ad2ebc11dc3654b5`. Port 18701, PID 37012 lu sur le port en ecoute, arrete par ce PID.
- **Methode** : celle de la section 1, avec deux releves de plus : le bas de la derniere ligne de `.verdict`, de `.reponse-revelee` et de `.reponse-attendue` (rectangles de lignes du texte, pas la boite), et le nombre de lignes de la reponse attendue. Un seul Chrome (PID 25212, profil jetable, port 19701, disparu a la fin du pilote), cinq largeurs (720, 744, 768, 992, 1200), hauteur 551, avec et sans barre, erreur 1 et revelation dans les six ordres. Couverture : 110 items sur 110, 0 anomalie, texte rendu egal au YAML. Critere : bas au plus a 551.

## E3. Apres une erreur, `b383b0bb` seul, 744 x 551

| Paquet | Items | Bas de `.indice` au plus a 551 dans les six ordres | Max du bas de `.indice` | Marge la plus faible |
|---|---|---|---|---|
| `-07` | 20 | **20** | 468 | 83 |
| `-06` | 20 | **19** | 574 (item 14) | **-23** (item 14, 2 ordres sur 6) ; 17 pour les autres (534) |
| `EMC` | 24 | **24** | 540 | 11 |
| `-11` | 24 | **24** | 540 | 11 |
| `MAT-CE1-01` | 22 | **22** | 500 | 51 |

- **Un seul item des 110 a l'indice sous le pli : `-06` 14**, dans 2 ordres sur 6 (574, 23 px sous le pli) ; dans les 4 autres l'indice est a 508. C'est ce que prevoyait C6. Ces maxima sont ceux de C6 ("sans la regle du score"), a l'identique : `-07` 468, `-06` 574, `EMC` 540, `-11` 540, `MAT` 500.
- **A 720 et 768, avec et sans barre** : memes comptes et memes maxima (574, 540, 540, 468, 500). **A 992 et 1200** : 0 item ; maxima 468 et 468 (`-06`), 444 et 404 (`EMC`), 470 et 404 (`-11`), 402 et 372 (`-07`), 470 et 404 (`MAT`). Aucun debordement horizontal. Aucun bouton de proposition au-dela de 551, apres une erreur comme a la revelation. Boutons de sortie et de niveau : bas a 58 dans tous les etats.
- **`-06` 14, apres une erreur**, texte-support de 2 lignes ("La pluie se met a tomber."), indice de 1 ligne : dans les 4 ordres a deux rangees de propositions, signal 458 a 534, verdict 526 (derniere ligne 524), indice 508 ; dans les 2 ordres `021` et `120` (trois rangees, propositions a 500), **signal 524 a 600, verdict 592 (derniere ligne a 590), indice 574**. Le verdict est deux lignes de 30 px ("Ce n'est pas ça." puis "Essaie encore !") : la premiere occupe environ 532 a 562 (**elle traverse le pli**), la seconde environ 562 a 592 : **elle est entierement sous le pli, sa derniere ligne de texte finit a 590, 39 px sous le pli**. Avec le flottant (D2) la boite du verdict finissait a 562 : elle descend de 30 px.
- **`-06` 16, 19, 20** (nouveau par rapport a D2, qui comptait le flottant) : indice a 534, **signal a 560** (9 px sous le pli) et boite du verdict a 552 (1 px sous le pli) dans les six ordres ; la derniere ligne du verdict finit a 550, au-dessus. L'indice et le verdict (texte) tiennent ; la boite du signal depasse.

### Cotes par item, `FRA-CE1-06`, `ce1-inference` (744, sans barre, apres une erreur, six ordres)

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 2 | 1 | 154 | 298-298 | 1-1 | 372-372 | au-dessus |
| 2 | 3 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 3 | 3 | 1 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 4 | 3 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 5 | 3 | 2 | 184 | 328-328 | 1-1 | 402-402 | au-dessus |
| 6 | 3 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 7 | 3 | 2 | 184 | 328-328 | 1-1 | 402-402 | au-dessus |
| 8 | 3 | 2 | 184 | 328-328 | 1-1 | 402-402 | au-dessus |
| 9 | 3 | 2 | 184 | 328-328 | 1-1 | 402-402 | au-dessus |
| 10 | 3 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 11 | 3 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 12 | 3 | 2 | 184 | 328-328 | 1-1 | 402-402 | au-dessus |
| 13 | 3 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 14 | 4 | 2 | 184 | 434-500 | 2-3 | 508-574 | sous le pli a 23 px dans certains ordres |
| 15 | 2 | 2 | 184 | 394-394 | 2-2 | 468-468 | au-dessus |
| 16 | 3 | 2 | 184 | 460-460 | 3-3 | 534-534 | au-dessus |
| 17 | 2 | 1 | 154 | 364-430 | 2-3 | 438-504 | au-dessus |
| 18 | 3 | 1 | 154 | 364-364 | 2-2 | 438-438 | au-dessus |
| 19 | 3 | 2 | 184 | 460-460 | 3-3 | 534-534 | au-dessus |
| 20 | 3 | 2 | 184 | 460-460 | 3-3 | 534-534 | au-dessus |

### Cotes par item, `EMC-CE1-01`, `ce1-emc-biens-communs` (aucun texte-support)

| Item | Phr. | Lignes texte | Bas texte | Bas proposition (min-max) | Rangees | Bas indice (min-max) | Pli |
|---|---|---|---|---|---|---|---|
| 1 | 0 | - | - | 330-330 | 2-2 | 434-434 | au-dessus |
| 2 | 0 | - | - | 370-436 | 2-3 | 444-510 | au-dessus |
| 3 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 4 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 5 | 0 | - | - | 330-330 | 2-2 | 404-404 | au-dessus |
| 6 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 7 | 0 | - | - | 330-330 | 2-2 | 404-404 | au-dessus |
| 8 | 0 | - | - | 396-396 | 3-3 | 470-470 | au-dessus |
| 9 | 0 | - | - | 330-330 | 2-2 | 404-404 | au-dessus |
| 10 | 0 | - | - | 330-330 | 2-2 | 434-434 | au-dessus |
| 11 | 0 | - | - | 370-436 | 2-3 | 474-540 | au-dessus |
| 12 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 13 | 0 | - | - | 436-436 | 3-3 | 510-510 | au-dessus |
| 14 | 0 | - | - | 370-370 | 2-2 | 474-474 | au-dessus |
| 15 | 0 | - | - | 396-396 | 3-3 | 470-470 | au-dessus |
| 16 | 0 | - | - | 436-436 | 3-3 | 510-510 | au-dessus |
| 17 | 0 | - | - | 370-436 | 2-3 | 444-510 | au-dessus |
| 18 | 0 | - | - | 370-370 | 2-2 | 444-444 | au-dessus |
| 19 | 0 | - | - | 396-396 | 3-3 | 500-500 | au-dessus |
| 20 | 0 | - | - | 436-436 | 3-3 | 510-510 | au-dessus |
| 21 | 0 | - | - | 436-436 | 3-3 | 540-540 | au-dessus |
| 22 | 0 | - | - | 436-436 | 3-3 | 510-510 | au-dessus ; autres: 720-barre 436-436 / 540-540 |
| 23 | 0 | - | - | 436-436 | 3-3 | 510-510 | au-dessus |
| 24 | 0 | - | - | 436-436 | 3-3 | 510-510 | au-dessus |

## E4. A la revelation, `b383b0bb` seul (PL-13 mis a jour)

Bas de `.reponse-revelee` ("Appuie sur la bonne réponse :") et de `.reponse-attendue`, boite et **derniere ligne du texte**, dans les six ordres, 110 items. *Dans* = nombre d'ordres sur 6 ou la cote depasse 551.

**Liste des items dont la phrase ou la reponse attendue passe sous le pli, 744, avec ou sans barre** (768 et 720 sans barre : identiques) :

| Item | Phrase "Appuie sur la bonne réponse :" (boite / derniere ligne) | Reponse attendue : boite / derniere ligne (nb de lignes) | Ordres sur 6 | Marge sous le pli de la derniere ligne |
|---|---|---|---|---|
| `-06` 14 | 554 / 552 dans 2 ordres (488 / 486 dans les 4 autres) | 606 / 603 (1 ligne) dans 2 ordres (540 / 537 dans les 4 autres) | 2 sur 6 | phrase 1 px ; reponse 52 px |
| `-06` 16 | 514 / 512, au-dessus | 566 / 563 (1 ligne) | 6 sur 6 | 12 px |
| `-06` 19 | 514 / 512, au-dessus | 566 / 563 (1 ligne) | 6 sur 6 | 12 px |
| `-06` 20 | 514 / 512, au-dessus | 566 / 563 (1 ligne) | 6 sur 6 | 12 px |
| `EMC` 13 | 490 / 488, au-dessus | 590 / 587 (**2 lignes**) | 6 sur 6 | 36 px |
| `-11` 13 | 490 / 488, au-dessus | 590 / 587 (**2 lignes**) | 6 sur 6 | 36 px |

- **Comptes, 744 sans barre** : phrase sous le pli : 1 item sur 110 (`-06` 14, dans 2 ordres sur 6, de 1 px pour la derniere ligne) ; reponse attendue sous le pli : **6 items sur 110** (`-06` 14, 16, 19, 20, `EMC` 13, `-11` 13) ; aucun dans `-07` ni `MAT-CE1-01`. Avec le flottant (D4) c'etaient 3 items ; **le refus d'`edf7f344` ajoute `-06` 16, 19 et 20**, par 30 px de marge en moins.
- **A 720 avec barre** : 8 items : les 6 ci-dessus plus `EMC` 21 et `-11` 18 (590 / 587, 2 lignes, six ordres) ; `-06` 14 y est a 588 a 654 dans les six ordres. **A 992 et 1200 : 0.**
- **Ce qui est dans la phrase et la reponse attendue entieres au-dessus du pli** : `-07` 20 sur 20, `MAT-CE1-01` 22 sur 22, `-06` 16 sur 20, `EMC` 23 sur 24, `-11` 23 sur 24.
- **Bas de l'indice a la revelation** : `nadia-prof` n'exige rien dessus (V3) ; je ne l'ai pas releve pour cette branche.
- Je decris, je ne corrige pas : la reponse attendue d'`EMC` 13 et de `-11` 13 est la plus longue de leur paquet (deux lignes de 32 px dans une colonne de 444 px, a droite du signal) ; celles de `-06` 16, 19 et 20 sont sur une ligne et finissent 12 px sous le pli.

## E5. Ce qui tient encore sous le pli, 744 x 551 (PL-13)

- **Indice apres une erreur** : 1 item sur 110 (`-06` 14, 2 ordres sur 6, a 574).
- **Signal et verdict apres une erreur** : `-06` 14 (signal 600, verdict 592, premiere ligne du verdict a 562 sous le pli, 2 ordres sur 6) ; `-06` 16, 19, 20 (signal 560, verdict a 552, derniere ligne a 550). Aucun autre item des cinq paquets.
- **Revelation** : E4 (phrase pour 1 item, reponse attendue pour 6, 8 a 720 avec barre).
- **Marge de 11 px** : indice a 540 pour `EMC` 11 et 21 et pour 14 items de `-11` : une ligne de plus a un indice ou un changement de rangee les ferait passer sous le pli ; la condition de validite est celle de la section 6 (hash d'item, `git diff` des gabarits et de la feuille).

## E6. Ce qui n'est pas mesure ici

- La lecture a l'ecran (les points de V5), le trait des lettres, une police et un appareil reels. Je mesure des rectangles de lignes de texte : un descendant (`p`, `q`, `y`) ou un accent peut s'etendre sous ou au-dessus de ce rectangle de quelques pixels.
- Les consignes du catalogue : la mesure D3 (31 jeux sur deux lignes avec le flottant) est celle de `edf7f344` ; sur cette branche, **la consigne est celle de `round-two`** (13 jeux sur deux lignes sans le flottant, que `nadia-prof` ne compte pas contre `b383b0bb`). Je ne l'ai pas rejouee ici.
- Une erreur forcee sur chacun des 152 jeux de proposition ; la regle `PL-11` sur les ecrans a bloc adulte, a deux oeuvres, de saisie et de designation (l'en-tete y est plus mince aussi).

## E7. Isolation

Un jar (port 18701, PID 37012) et un Chrome (PID 25212, port 19701), arretes par PID, aucun processus arrete par nom d'image ; le Chrome a disparu a la fin du pilote, le port du jar ne repond plus. Aucun fichier de `domain`, `application`, `bootstrap`, ni gabarit touche. Aucune fusion dans `round-two`, aucun push. Les worktrees `recreo-ines-p3-pli` et `recreo-ines-integ-pli` sont laisses en place.

## Decisions prises (remesure apres le refus d'`edf7f344`)

**Aucune.** Cette section ne cree ni n'amende aucune `ADR` ni `DEC`, ne leve aucune reserve, ne prononce aucun Done et ne corrige ni contenu ni gabarit. Elle laisse a `nadia-prof` les restes sous le pli de E5.

## Amendements du 2026-10-21, 18:40 (UTC+02:00) : ecarts 31 et 32 du releve de cloture du palier 3

- **Auteure** : `ines-design`. Source : `releve-relecteur-cloture-palier-3.md`, section 3.4. Le texte des sections precedentes n'est pas reecrit. Aucun identifiant nouveau, aucun Done, aucune decision.
- **Ecart 31 (section E1, ligne 1008)** : la phrase "**PL-11**, **PL-13**, et ... **PL-9** et **PL-10** (le fichier ne dit pas qu'ils l'ont ete)" se lit : **PL-9** et **PL-10** sont alloues par `theo-archi` (`21e994a4`, 2026-10-21 09:38), **PL-11**, **PL-12** et **PL-13** le sont aussi (`ad9ef86d`, 2026-10-21 12:56) ; `decisions/REGISTRE-identifiants.md`, lignes 2074 a 2078. La section E1 est de 12:40 : elle precede l'allocation de PL-11 a PL-13, qui n'etaient donc pas encore alloues quand elle a ete ecrite, et ne l'est plus aujourd'hui.
- **Ecart 32 (sections C4 et D2)** : le bas du signal de D2 (`-07` 464, `-06` 570, EMC 506, `-11` 506, `MAT` 466) est lu dans le releve brut du jar de D2 (`signalBas`, maximum sur les six ordres, 744 x 551, apres une erreur) : il est exact. Celui de C4 (464, 570, 510, 510, 470) donne, pour EMC, `-11` et `MAT`, le **meme nombre que le bas de `.indice`** de la meme table (510, 510, 470) ; pour `-07` et `-06` le signal est plus bas que l'indice et les deux tables concordent. Lecture retenue : la colonne de C4 donne le plus bas du signal et de l'indice, celle de D2 le bas de `.signal` seul ; l'ecart de 4 px est donc celui de l'indice sous le signal sur EMC, `-11` et `MAT`, pas une difference de jar. Je n'ai pas relu le releve brut de C4 pour le confirmer ; la phrase "les maxima sont ceux de C4" reste vraie pour `.indice` et inexacte pour le signal.
