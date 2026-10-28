# s57 - palier 5 : avis de theo-archi sur le premier jet de l'horloge `MAT-CE1-11` (`ce1-lire-l-heure`)

- **Auteur** : `theo-archi`. Date : 2026-10-28 13:16 (UTC+01:00), lue par `date`.
- **Arbre** : worktree propre, branche `s57-p5-theo-horloge` créée depuis `round-two` à `b37d476a` (fusion de `s57-p5-nadia-lecture-horloge`), par `git switch -c`. Aucun reset, aucun rebase, aucune fusion, aucun push.
- **Lu** : `rapport-lea-dev-palier-5-horloge-mat-ce1-11.md` (en entier, section 7 comprise) ; `lecture-nadia-premier-jet-horloge-mat-ce1-11.md` (en entier) ; `ADR-0102`, `ADR-0013`, `ADR-0086`, `ADR-0087` ; `ce1-lire-l-heure.yaml` ; `horloge-g.svg` ; `LesCadransMontrentCeQueLeursItemsAffirmentTest.java` (en entier) ; `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java` (Javadoc et motifs de lecture, lignes 30 à 350) ; `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest.java` (lignes 1 à 330) ; le test de cliquet de poids (constantes et `leCliquetVautExactementCeQueCeLotInstalle`).
- **Ce que ce fichier ne fait pas** : il n'écrit ni code de production, ni test, ni paquet, ni dessin, ni jeu fautif, ni ADR. Il ne prononce aucun Done. Aucune règle `ArchRule` n'est ajoutée : le compte du dépôt est inchangé.

## 0. Verdict

| Code | Question | Réponse |
|---|---|---|
| `V1` | Forme chiffrée et `ADR-0102` | **Servie sans modification.** `DUREE_HEURES` lit `3 h`, `3 h 30`, `3 h 15`, `2 h 45` ; le garde de rang est vert sur le paquet (28 tests). Rien ne manque |
| `V2` | Aiguilles que la garde M sait lire | Deux `<line>` d'une forme exacte, à coordonnées entières. **`lea-dev` ne change rien aujourd'hui.** Un redessin hors de cette forme rougit, bruyamment ; un élément en plus passe en silence (section 2) |
| `V3` | Q9 | **`LIRE_L_HEURE` est retenue**, telle que `lea-dev` l'a écrite. Les autres questions de la section 7 sont routées en section 3 |
| `V4` | Poids | La constante vaut **96 873** et le garde l'exige à l'octet près : cohérents. **L'accord de l'utilisatrice n'est écrit nulle part dans le dépôt**, et la Javadoc du cliquet dit encore le contraire (section 4) |
| `V5` | Stratégie de paire | **Mesurable par un garde exécutable, et il doit exister.** Mais la mesure de `nadia-prof` (10 sur 12) n'est pas la plus forte : lue dans l'ordre du cadran, la même stratégie gagne **12 sur 12** (section 5) |

## 1. `V1` : la forme chiffrée et `ADR-0102`

`DUREE_HEURES` (`LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest.java:255-256`) est `^(\d+)\s*h(?:eures?)?\.?(?:\s+(\d+)\s*(?:min(?:utes?)?)?\.?)?$` : les minutes et l'unité `min` sont facultatives. Les 36 propositions du paquet ont toutes la forme `<n> h` ou `<n> h <mm>`, et toutes sont lues. `aucuneEcritureDeDureeDeProductionNEchappeALaLecture` est vert sur l'arbre qui contient le paquet (section 6).

Le quart d'heure **en toutes lettres** reste refusé par `ADR-0102`, et `nadia-prof` ne le demande pas (lecture, 2.1). Le refus de l'ADR et sa mise à l'épreuve (`uneEcritureDeDureeInconnueDeLaLectureEstRouge`) ne changent donc pas.

Un point de lecture, sans modification : sur ce paquet, le garde de rang ordonne `12 h 15` (735) au-dessus de `1 h 15` (75). C'est l'ordre des nombres écrits, celui qu'une stratégie *"presser la plus petite"* utilise. Sur le paquet, la bonne réponse est la plus petite sur 5 items, l'intermédiaire sur 5 et la plus grande sur 2, soit 41,7 %, 41,7 % et 16,7 %. Les deux seuils (au plus 50 %, au moins 10 %) sont tenus. Ces chiffres sont ceux de `nadia-prof`, recalculés à la main sur les douze items.

## 2. `V2` : la forme des aiguilles que la garde M sait lire

### 2.1 Ce que le garde accepte

Le motif `AIGUILLE` (`LesCadransMontrentCeQueLeursItemsAffirmentTest.java:72-74`) et `aiguilles` (`:159-181`) acceptent un `<line>` qui remplit quatre conditions :

1. ses attributs sont `x1`, `y1`, `x2`, `y2` en entiers, puis `stroke-width` (entier ou décimal), puis `transform="rotate(<angle> <cx> <cy>)"`, dans cet ordre et sans autre attribut ;
2. le trait est vertical et pointe vers midi (`x1 == x2`, `y2 < y1`) ;
3. la rotation se fait autour du pied (`cx == x1`, `cy == y1`) ;
4. il y a exactement deux traits de cette forme, la plus courte étant la petite aiguille.

Longueurs, épaisseurs et centre sont libres dans cette forme. Un redessin qui garde des `<line>` avec d'autres longueurs ou d'autres épaisseurs reste lisible par le garde sans modification.

### 2.2 Ce qui est fragile, et dans quel sens

| Code | Situation | Effet |
|---|---|---|
| `F1` | Aiguille en `<path>` ou `<polygon>` (aiguille effilée), attribut en plus ou dans un autre ordre, contrepoids derrière le pivot | **Rouge bruyant** : moins de deux traits lus, ou un trait qui ne pointe pas vers midi, donc *"le dessin ne trace pas exactement deux aiguilles"* |
| `F2` | Coordonnées décimales (`y2="59.5"`) | Rouge bruyant, même message |
| `F3` | Les morsures en mémoire sont épinglées sur la géométrie actuelle : `rotate(... 88 88)` (`:329`) et `y2="60"` vers `y2="46"` (`:340`) | Un redessin qui change le centre ou la longueur de la petite aiguille rend ces morsures **rouges** (la mutation ne trouve rien à remplacer). C'est bruyant, et `lea-dev` les ajuste dans le diff du redessin |
| `F4` | Un **troisième** élément en forme d'aiguille, écrit hors de la forme exacte (un `<line>` avec un attribut de plus, un `<path>`) | **Vert silencieux** : le garde ne compte que ce qui correspond à son motif. Les douze dessins n'ont aucun autre `<line>`. Le défaut n'existe donc pas aujourd'hui |

**Ce que `lea-dev` change : rien sur les douze dessins actuels.** Si l'exigence 2 de `nadia-prof` conduit `ines-design` à une autre géométrie, le diff du redessin doit contenir trois choses : l'extension du motif si la forme n'est plus un `<line>` (`F1`), l'ajustement des morsures (`F3`) et, puisque le motif aura bougé, une clause *"le nombre de `<line` du dessin vaut le nombre d'aiguilles lues"* qui ferme `F4`. Je ne l'impose pas avant ce redessin : aujourd'hui, elle ne refuserait aucun objet réel.

### 2.3 La petite aiguille à 7,5 degrés : le calcul de `nadia-prof`, vérifié

Méthode : `horloge-g.svg`, `viewBox="0 0 176 176"` et `width="176"`, donc 1 unité vaut 1 px au rendu de 176 px. La petite aiguille mesure 28 unités et son trait 7 (`stroke-linecap="round"`).

- Écart latéral du bout à 7,5 degrés : 28 x sin 7,5° = **3,65 px**. La demi-épaisseur vaut 3,5 px. **Le bout se déplace d'à peu près sa propre demi-épaisseur**, et le calcul de `nadia-prof` (environ 4 px pour une épaisseur de 7) est exact.
- Vu du centre, l'aiguille occupe à son bout atan(3,5 / 28) = 7,1 degrés de chaque côté. Aux quarts, son bord est donc à peu près sur le rayon du nombre.
- À la demie (15 degrés), l'écart vaut 28 x sin 15° = 7,25 px, soit environ une épaisseur entière.

Pour `ines-design`, sans rien trancher : l'écart vaut longueur x sin 7,5°, soit 0,13 px par unité de longueur. Les nombres commencent à environ 50 px du centre (le `1` de midi s'arrête à y = 37,5, d'après `y="67"` et la hauteur de glyphe de 56 x 0,3). Il faut aussi que la petite aiguille reste plus courte que la grande (`HOR-3`). L'épaisseur est libre. La lisibilité reste à `nadia-prof`, à l'écran (sa section 6).

## 3. `V3` : Q9, et le routage des douze autres questions

### 3.1 Q9 : `LIRE_L_HEURE` est retenue

1. **Le précédent existe.** `MAT-CE1-03` porte quatre sous-parties. Depuis `ADR-0098`, l'entrée nomme sa ligne, et plusieurs entrées peuvent nommer la même. `lesSousPartiesAlloueesSontDistinctes` reste vert (section 6).
2. **L'alternative serait fausse par inclusion.** Si le paquet d'horloge déclarait `HORLOGE_ET_CALENDRIER`, deux paquets partageraient une sous-partie. Un garde de sous-partie dérivé de cette entrée lirait alors les dates et les cadrans dans une seule population : c'est le défaut de périmètre que ce dépôt sanctionne le plus souvent.
3. **Le libellé suit la convention.** C'est le libellé du référentiel, coupé avant *"et se repérer"*, exactement comme le paquet le déclare.

Reste un écart, non bloquant et préexistant : `ce1-calendrier-dates` déclare toujours une compétence qui nomme *"lire l'heure"*. Le garde ne juge pas la vérité d'une déclaration (sa Javadoc le dit). Pour le corriger, il faut une sous-partie *calendrier* et le retrait de `HORLOGE_ET_CALENDRIER`, dans le même diff que la réécriture de l'en-tête `DEGRADE` (Q11), pas avant.

### 3.2 Les autres questions

| Question | Relève de | État |
|---|---|---|
| Q1, côté lecture de valeur | `theo-archi` | `V1` : aucune modification |
| Q1 (forme), Q2, Q3, Q6, Q7, Q10, Q13 | `nadia-prof` | répondues dans sa lecture, 2.1 à 2.3 |
| Q5 | `nadia-prof`, à l'écran | la part géométrique est en 2.3, la lecture n'est pas de mon ressort |
| Q4 (glyphes), Q12 (pli) | `ines-design` | exigence 2 de `nadia-prof` |
| Q8 (nombre d'items et poids) | `camille-po`, l'utilisatrice | voir aussi `V4` |
| Q11 (en-tête `DEGRADE`, matrice) | `camille-po` | au Done ; la sous-partie *calendrier* de 3.1 va dans le même diff |

## 4. `V4` : le poids

- `POIDS_TOTAL_MAXIMUM = 96873` (`ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest.java:361`).
- `leCliquetVautExactementCeQueCeLotInstalle` (`:832-844`) exige `isEqualTo` entre le poids installé (fins de ligne normalisées en LF) et la constante. Il est vert (section 6). Le répertoire pèse donc exactement 96 873 octets et le cliquet n'a aucune marge. `POIDS_D_UN_DESSIN_MAXIMUM` reste à 4 633 (les cadrans font de 2 303 à 2 308 octets).

Deux écarts, que je ne corrige pas parce qu'ils ne sont pas les miens :

- **L'accord n'est écrit nulle part dans le dépôt.** Une recherche de `96 873`, `96873` et *"on l'accorde"* sous `squad/` et `docs/` ne trouve que le rapport, la lecture et le test. Le *"Q1 O1"* présent dans `DEC-0120` est la réponse du 2026-10-27 (A.280), pas celle du 2026-10-28. Je n'ai que le relais de l'agent lanceur. Tant que l'accord n'est pas écrit (`camille-po`, annotation de `DEC-0120`), il n'a pas été pris au sens du dépôt.
- **La Javadoc du cliquet dit l'inverse** (`:357-358`) : *"Le poids de ce lot n'est pas couvert par un accord de l'utilisatrice"*. `lea-dev` la corrige dans son commit de correction, avec la référence de l'accord une fois celui-ci écrit.

Une conséquence à connaître dès maintenant : si l'exigence 2 fait changer les glyphes, le poids change, et le cliquet exact le fera voir. **Un total au-dessus de 96 873 octets n'est pas couvert par l'accord relayé** et doit être redemandé (`camille-po`).

## 5. `V5` : la stratégie de paire

### 5.1 La mesure, avec sa méthode

Méthode : script hors dépôt, lancé sur `ce1-lire-l-heure.yaml` à `b37d476a` (inchangé depuis `a5d5026b`). Chaque proposition est lue par le motif de la garde M, `^(\d{1,2}) h(?: (\d{2}))?$`. Les trois propositions d'un item sont regroupées par minutes. Si l'item a une paire et un intrus, la stratégie écarte l'intrus et choisit dans la paire. Un item où la stratégie ne s'applique pas compte comme perdu.

| Stratégie, après avoir écarté l'intrus | Gains sur 12 |
|---|---|
| `S1` : la plus petite heure **écrite** (12 > 1), celle que mesure `nadia-prof` | **10** (perdus : `horloge-f`, `horloge-g`) |
| `S2` : la plus grande heure écrite | 2 |
| `S3` : l'heure qui **précède dans l'ordre du cadran** (12 précède 1) | **12** |
| `S4` : l'heure qui suit dans l'ordre du cadran | 0 |
| L'intrus est faux | 12 sur 12 (réserve a de `nadia-prof`) |

Le 10 sur 12 de `nadia-prof` est exact pour `S1`. Les deux exceptions qu'elle relève sont précisément les deux paires 12 / 1. Pour un enfant qui sait que 1 vient après 12 sur le cadran, ce ne sont plus des exceptions : chaque distracteur de voisinage est l'heure suivante, et `S3` gagne tout sans regarder une aiguille.

### 5.2 Le garde : il est exécutable, et il doit exister

**Il est exécutable.** Il raisonne par paire et non par valeur : il lit les heures et les minutes que la garde M lit déjà (`LECTURE`), sans rien ajouter au garde de rang.

**Il doit exister, pour trois raisons.** `nadia-prof` se dit seule garde de cette propriété (sa section 6). Son exigence 1 sera remesurée à chaque correction. Et la mesure à la main a déjà manqué `S3`. Ce n'est pas un reproche : c'est l'erreur qu'un garde ne commet pas.

**Ce qu'il interdirait réellement.** Le premier jet tel qu'il est servi, et une correction qui passerait `S1` en jouant sur les paires 12 / 1, laissant `S3` majoritaire.

**Sa forme, à écrire par `lea-dev` dans la garde M, dans le commit qui corrige le contenu.** Sinon `round-two` rougit : `S1` vaut 10 et `S3` vaut 12, pour un seuil de 6.

| Point | Contenu |
|---|---|
| Lieu | `LesCadransMontrentCeQueLeursItemsAffirmentTest`, clause du paquet entier, à côté de `MINIMUM_PAR_FORME`. **Pas le garde de rang** : la *forme* (le champ des minutes) n'est définie que pour une lecture d'heure, et il n'existe qu'un usage constaté. Ce qui me ferait changer d'avis : un second paquet construit en *"même forme, valeur voisine"* (des durées, par exemple) |
| Définition | Pour chaque item, regrouper les trois propositions par minutes. S'il y a exactement deux groupes, compter les gains de `S1` à `S4`. Comparer les heures **écrites** (`group(1)` entier) pour `S1` et `S2`, et l'ordre du cadran (`b == a % 12 + 1`) pour `S3` et `S4`. Ne pas utiliser `position()`, qui ramène 12 à 0 |
| Seuil | Celui de `nadia-prof` : au plus la moitié des items (`gains * 2 <= items`). Le seuil n'est pas le mien |
| Mise à l'épreuve (`ADR-0013`) | Le premier jet **figé octet pour octet** sous `contreexemple/LesCadransMontrentCeQueLeursItemsAffirmentTest/`, depuis `a5d5026b`. Le garde doit y nommer `S1` = 10 et `S3` = 12. C'est du matériau réel, pas une fixture, et la preuve survit à la correction (`ADR-0086`, amendement de s32, section 2) |
| Population (`ADR-0051`) | Au moins un item où la stratégie s'applique, sinon rouge |
| ADR | Aucun. C'est une clause d'un garde de paquet qui applique un seuil écrit par `nadia-prof`. Aucune règle d'architecture ne change |

### 5.3 Ce que `nadia-prof` doit trancher avant l'écriture du garde

Son exigence 1 ne nomme que `S1`. Sa section 6 écrit *"qu'aucune stratégie qui ignore le cadran ne gagne plus de la moitié des items"*. Je ne choisis pas entre les deux. Voici l'arithmétique qui rend le choix nécessaire : l'intrus est toujours faux, donc la bonne réponse est toujours dans la paire, et `S1 + S2 = S3 + S4 = 12`. Plafonner les deux sens à 6 impose donc **exactement 6 et 6** dans chaque ordre. Une correction à 5 heures suivantes et 7 heures précédentes passe son exigence telle qu'elle est écrite (`S1` = 5), mais `S2` gagne alors 7 items. Elle dit si le garde retient `S1` seul, `S1` et `S3`, ou les quatre.

## 6. Mesures

Une seule commande, depuis la racine du worktree :

`bash outils/mvn-jdk21.sh -B -pl bootstrap -am test "-Dtest=LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest,PaquetCe1LireLHeureTest,ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest,ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest,LesCadransMontrentCeQueLeursItemsAffirmentTest,LesGardeFousSontMisALEpreuveTest,LIllustrationDeContenuResoutEtAucuneNeDortTest" -Dsurefire.failIfNoSpecifiedTests=false`

**Code 0, `BUILD SUCCESS`, 0 échec, 0 erreur.** Le périmètre est le réacteur de `bootstrap` et de ses amonts, avec sept classes filtrées. Domain et application ne lancent aucun test sous ce filtre.

| Module | Classe | Tests |
|---|---|---|
| adapter-web | `ChaqueIllustrationDeContenuDeclareSaGeometrieEtResteSousLeCliquetDePoidsTest` | 17 |
| adapter-content | `ChaquePaquetDeMathematiquesDeCe1DeclareSaSousPartieTest` | 14 |
| adapter-content | `LeRangDeLaBonneReponseNEstPasPredictibleSurUnPaquetTest` | 28 |
| adapter-content | `PaquetCe1LireLHeureTest` | 6 |
| bootstrap | `LesCadransMontrentCeQueLeursItemsAffirmentTest` | 10 |
| bootstrap | `LesGardeFousSontMisALEpreuveTest` | 10 |
| bootstrap | `LIllustrationDeContenuResoutEtAucuneNeDortTest` | 22 |

Soit 107 tests, comptés sur ces sept classes seules. Ce n'est pas le dépôt.

**Version** : JDK **21.0.10**, celle que l'enveloppe a lue du JDK résolu par la toolchain (*"Maven lance sur le JDK de la toolchain jdk 21 : 21.0.10"*). Aucun test ArchUnit n'est dans ce périmètre, donc la ligne `Detected Java version` n'apparaît pas dans le journal.

**Non lancés** : `mvn -B verify`, les jobs de `ci.yml`. Je ne déclare pas la porte technique.

## 7. Routage

| Code | À qui | Quoi |
|---|---|---|
| `A1` | `nadia-prof` | 5.3 : quelles stratégies le garde de paire retient, sachant que les plafonner toutes impose 6 et 6 |
| `A2` | `lea-dev` | après `A1` : le garde de paire (5.2) et le premier jet figé, dans le commit de correction ; la Javadoc du cliquet (section 4) |
| `A3` | `camille-po` | écrire l'accord de poids du 2026-10-28 dans `DEC-0120` ; redemander tout total au-dessus de 96 873 octets ; au Done, la sous-partie *calendrier* (3.1) avec Q11 |
| `A4` | `ines-design` | 2.2 et 2.3 : la forme que la garde M lit, et l'écart du bout en fonction de la longueur |
