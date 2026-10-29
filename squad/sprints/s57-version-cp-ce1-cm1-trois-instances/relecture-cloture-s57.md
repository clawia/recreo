# [Sprint s57] Relecture rédactionnelle de clôture (`relecteur-pedagogique`)

- **Écrit par** : `relecteur-pedagogique`, le 2026-10-29, pour la clôture du sprint s57 décidée par l'utilisatrice le même jour. Arbre lu : `round-two` à `a3e05f13` (worktree `s57-cl-rel-s57`). Ce fichier est le seul fichier ajouté : **aucun fichier existant n'est modifié**. Les corrections sont proposées mot pour mot (tableaux, puis annexe) ; leur auteur les applique, `sofia-sm` route.
- **Ce que ce fichier ne fait pas** : il ne prononce aucun Done, ne juge ni le fond pédagogique des fiches, ni un choix produit, ni un choix d'architecture. Un point de fond est classé **à trancher** et nommé à son propriétaire.
- **Périmètre relu** : `cloture-palier-5.md` en entier ; les amendements de `planning.md` à partir du 2026-10-23 (palier 5), dont les 19 marqueurs *[corrige par l'amendement du 2026-10-29 ...]* ; les amendements A.280 à A.292 de `DEC-0120` ; les lignes de `MATRICE-couverture.md` de `QLM-CE1-01` à `-12` et de `MAT-CE1-11`, plus les notes du 2026-10-28 et du 2026-10-29 ; les stories du backlog écrites à partir de l'annotation du 2026-10-25 (les neuf `S57-BR-QLM-CE1-*`, les quatorze stories de réserves, `S57-QLM-CE1-ENTREES-CATALOGUE`, `S57-BR-MAT-CE1-11-HORLOGE`, les annotations du 2026-10-28 au 2026-10-29) ; les neuf `FICHE-ce1-qlm-*.md` et `FICHE-ce1-lire-l-heure.md`, **pour leur seule forme**.
- **Provenance** : `DEC-0062` section 3.1 fait foi pour les trois formes.

## Résumé

**23 défauts**, tous dans des fichiers de `squad/` et `docs/`, aucun dans du code : 10 de forme (section 2), 10 d'exactitude, fait faux ou périmé sans marqueur (section 3), 3 renvois d'état à `sofia-sm` (section 4). **4 points de fond à trancher** (section 6), non touchés. Les plus graves :

1. **Cas haut du coût : deux chiffres en vigueur** (point à trancher 1). La clôture et le planning disent 23,70 et une réserve de 1,30 ; `DEC-0120` A.291 point 6 constate qu'une troisième lecture de `-12` a eu lieu et donne 24,20 et 0,80, sans dire lequel est le courant. La clôture écrit "aucune troisième lecture au-delà de `-03` et `-08`".
2. **La clôture est périmée à `a3e05f13`** (défaut 20) : 165 sur 202, `-12` sans entrée, accord ouvert, "le code de `HEAD` est celui de `eaa3ef65`" ; l'arbre dit 166 sur 202, `-12` à `couvert-valide`, et cinq fichiers de code ou de test ont changé.
3. **Marqueur faux, `cloture-palier-5.md` ligne 170** (défaut 11) : il retire un chiffre exact (3292) en affirmant qu'il ne l'était pas à `HEAD`.
4. **`DEC-0120` A.288 à A.292 n'ont pas de titre d'amendement** (défaut 7) : l'index les numérote soixante-sixième à soixante-dixième, le fichier n'a aucun titre `##` pour eux.
5. **Huit cellules de matrice et un tableau du backlog nomment "fusion du paquet" la fusion du verdict** (défaut 18).

## 1. Contrôles mécaniques

| Contrôle | Périmètre | Résultat |
|---|---|---|
| Tiret cadratin, tiret demi-cadratin | les 15 fichiers du périmètre | 0 occurrence |
| Guillemets typographiques courbes | les 15 fichiers | 0 occurrence |
| Guillemets en chevrons | stories du palier 5, matrice, fiches | 0 occurrence ; les 59 du backlog et les 2 de la matrice sont dans des annotations des paliers 2 à 4, hors périmètre |
| Emoji, symboles décoratifs | les 15 fichiers | 0 occurrence (hors lettres, seuls signes : multiplication, division, degré, exposant) |
| Chemin absolu de poste, nom de compte | les 15 fichiers | 0 ; la forme POSIX de lecteur de `planning.md` lignes 917 et 999 est une citation du palier 3, que `ADR-0010` admet |
| Commits cités | 211 jetons hexadécimaux du périmètre, `git cat-file -e` | 208 commits résolus, 0 mort ; 3 jetons sont des empreintes de jar ou de blob (`FICHE-ce1-qlm-02`, `-05`, `-10`) |
| Fichiers cités | tous les noms `.md`, `.yaml`, `.java`, `.sh` du périmètre, contre `git ls-files` | 0 renvoi mort ; les noms absents (`confirmation-archunit-palier-5.md`, `revision-nadia-bpl-ce1-1.md`, `reouverture-nadia-regles-2-et-3-ce1-qlm-08.md`, `reponse-nadia-ecran-de-fin-bravo.md`, `reponse-nadia-bravo-dernier-choix.md`, `composition-palier-5.md`, `mesure-ines-palier-5-horloge-pli-744x551.md`) sont dits absents ou proposés par le texte qui les nomme |
| Références `A.nnn` | planning, clôture, backlog, matrice, `DEC-0120`, `DEC-index.md` | aucune au-delà de `A.292` ; `A.272`, `A.273`, `A.276` à `A.282` existent |
| Marqueurs *[corrige ...]* du palier 5, `planning.md` lignes 2380 à 3435 | 19 marqueurs de l'amendement du 2026-10-29 | posés aux 19 lignes que sa section 3 nomme (2613, 2655, 2749, 2769, 2824, 2858, 2950, 3083, 3102, 3192, 3212, 3357, 3375, 3252, 3372, 3349, 3210, 3303, 3358) ; chaque marqueur du palier renvoie à un amendement qui existe (`A.273`, `A.285`, `A.286`, ou un amendement daté : 13:35, 16:09, 08:19, 15:40, 20:46, 21:34, 09:56, 2026-10-29 00:03). Aucun marqueur orphelin ; un est mal écrit (défaut 1) |
| Provenance, `DEC-0062` | clôture, planning, `DEC-0120` A.280 à A.292, backlog, matrice | aucun énoncé reçu de MASTER dans le périmètre (forme 3 absente). Forme 2 : *"Q1 O1 on l'accorde"*, *"Q-283-1 02"*, *"O1 il tombe"*, *"O2"*, *"traite entierement le passage a couvert-valide de QLM-CE1-12"* sont cités entre guillemets, datés et relayés par l'agent lanceur dans `DEC-0120` (l'amendement G de `DEC-0062` existe). Dans la clôture section 7 et dans l'amendement de `planning.md` du 2026-10-29 section 2, les mêmes mots renvoient à `camille-po` et à `DEC-0120` A.284, A.286, A.287 (forme 1, le renvoi résout) ; la date n'y est pas répétée, elle est dans `DEC-0120` |
| Tests de garde, avec `-am` | `bash outils/mvn-jdk21.sh -B -pl bootstrap -am test -Dtest=ToutIdentifiantDeConditionEstAlloueDansLeRegistreTest,AucuneLigneDeTableauNePortePlusDeCellulesQueSonEnTeteTest,ToutesLesReferencesDeDecisionResolventTest -Dsurefire.failIfNoSpecifiedTests=false` sur `a3e05f13`, avant l'ajout de ce fichier | `BUILD SUCCESS`, 18 tests (5, 3, 10), 0 échec. Périmètre : `bootstrap` et ses amonts, pas le dépôt |

Chiffres recoupés sans écart : 3644 = 120 + 31 + 523 + 2569 + 395 + 6 ; 3618 `@Test` à `eaa3ef65`, `d9313f9e` et `a3e05f13`, plus 26 ; 3206 à `f3b06989` ; 160 sujets non fusion de `077fae0b` à `fa137660` (159 à `eaa3ef65`) ; 155 fusions premier parent de `f3b06989` à `fa137660` ; 307 commits d'avance sur `077fae0b` ; 6 + 2 + 5 = 13 juges d'`outils` ; 5 j 9 h 47 min de `e18b13e8` (15:16, +02:00) à 00:03 (+01:00) ; 156 + 8 + 1 = 165, 165 / 202 = 81,68 %, 166 / 202 = 82,18 %, 171 / 202 = 84,65 %, 172 / 202 = 85,15 % ; 17,70 + 4,50 + 1,00 + 0,50 = 23,70 ; 25,00 - 23,70 = 1,30 ; 999 + 5 = 1004 ; 163 + 9 = 172.

## 2. Défauts de forme, à corriger

Numéros de ligne à `a3e05f13`. "Auteur" : propriétaire du fichier ou de l'amendement.

| N | Fichier : ligne | Ce qui ne va pas | Correction proposée | Auteur |
|---|---|---|---|---|
| 1 | `planning.md` : 3252 (marqueur du 2026-10-29) | "aucun rejeu du (fiche de l'horloge)" : le mot est "dû", l'accent perdu rend la phrase incorrecte. La fiche écrit *"Rejeu : aucun n'est dû."* | remplacer `aucun rejeu du (fiche de l'horloge)` par `aucun rejeu dû (fiche de l'horloge)` | `sofia-sm` |
| 2 | `planning.md` : 3323 (tableau du coût, ligne *Rejeu*, texte d'origine du 09:56) | même graphie : "aucun rejeu du ou decide" | remplacer par `aucun rejeu dû ou decide` | `sofia-sm` |
| 3 | `planning.md` : 2389, 2393, 2397, 2401, 2405, 2409, 2458, 2491 | huit titres d'amendement des 2026-10-25 et 2026-10-26 portent `(UTC+02:00)`. Le changement d'heure est le 2026-10-25 à 03:00 ; les commits de ces lignes sont à `+0100` (`git blame` : 2026-10-25 09:35:26 +0100 pour la ligne 2389, 2026-10-26 10:02:32 +0100 pour la ligne 2491) et le titre du 2026-10-26 10:43 écrit déjà `(UTC+01:00)` | remplacer `(UTC+02:00)` par `(UTC+01:00)` dans ces huit titres seulement | `sofia-sm` |
| 4 | `cloture-palier-5.md` : 100 | titre sans accent dans un fichier accentué | `## 7. Décisions prises` à la place de `## 7. Decisions prises` | `sofia-sm` |
| 5 | `cloture-palier-5.md` : 148 (section 9) | "27 min 30 s, 29 min 28 s, 28 min 36 s (31 min 04 s au total)" : la parenthèse se lit comme le total des trois. Le 09:56 de `planning.md` (ligne 3268) l'attache à `-12` seul : *"28 min 36 s jusqu'au commit, 31 min 04 s au total"* | texte en annexe A, défaut 5 | `sofia-sm` |
| 6 | `DEC-0120` : 5304 (A.292 point 8) | la phrase finale n'a pas de point : "... ne sont pas relances" | ajouter `.` après `ne sont pas relances` | `camille-po` |
| 7 | `DEC-0120` : 5099, 5155, 5161, 5209, 5271 | A.288 à A.292 sont des `###` posés sous le titre `##` d'A.287 (soixante-cinquième). Tous les amendements précédents ont leur `## Amendement du ..., <ordinal> (...)` ; `DEC-index.md` lignes 589 à 594 numérote ces cinq amendements soixante-sixième à soixante-dixième, le fichier ne porte aucun de ces titres | cinq titres en annexe A, défaut 7 (ordinal et date fixés par l'index, libellé à l'auteur) | `camille-po` |
| 8 | `backlog.md` : 25159 (titre de `S57-QLM-CE1-ENTREES-CATALOGUE`) | cinq marqueurs dans un ordre ni chronologique ni inverse (A.291, A.292, A.290, A.287, A.288) ; le marqueur d'A.287 est le seul sans date | réordonner A.287, A.288, A.290, A.291, A.292 ; dater A.287 : `[corrige le 2026-10-28 par A.287, texte d'origine conserve : ...]` | `camille-po` |
| 9 | `MATRICE-couverture.md` : 1056 (cellule de statut de `MAT-CE1-11`) | (a) la mention citée se ferme sur un guillemet droit puis enchaîne sur `**Réserves non bloquantes` sans ponctuation ; (b) la même cellule écrit `VALIDE SOUS RÉSERVE` (horloge) et `VALIDE SOUS RESERVE` (calendrier) | (a) ajouter un point après le guillemet fermant de la mention (`... l'horloge à affichage numérique".`) ; (b) voir point à trancher 4 | `camille-po` |
| 10 | `DEC-0120` : 5120 (A.288 point 3) | citation de `nadia-prof` entre guillemets, accents retirés : *"La condition est atteinte ; la levee s'ecrira dans la fiche"*. La source (`lecture-nadia-entrees-de-catalogue-ce1-qlm-et-horloge.md`, section 3, ligne 93) est accentuée : une citation mot pour mot garde ses accents | `la levée s'écrira dans la fiche` à la place de `la levee s'ecrira dans la fiche` | `camille-po` |

## 3. Défauts d'exactitude, à corriger

| N | Fichier : ligne | Ce qui ne va pas | Correction proposée | Auteur |
|---|---|---|---|---|
| 11 | `cloture-palier-5.md` : 170 (marqueur du 2026-10-29, section 10), 214 | le marqueur écrit que le 3292 de `eaa3ef65` et de `HEAD` "etait exact pour `eaa3ef65` seul : a `HEAD` c'est 3293". Le `HEAD` du texte d'origine est `fa137660` : `git ls-tree -r --name-only fa137660` hors le test du garde-fou rend **3292**, comme `eaa3ef65`. Le 3293 est celui de `7e911fad`, postérieur. Le marqueur nomme `HEAD` pour deux commits et retire un chiffre exact ; aucun des deux marqueurs (170, 214) ne cite `DEC-0120` A.289 | texte en annexe A, défaut 11 | `sofia-sm` |
| 12 | `DEC-0120` : 5284 (A.292 point 2), 5304 (point 8 b) | "`chemins` (3301 fichiers suivis, forme CI)" et "forme CI, sortie 0, 3301 fichiers suivis". 3301 est `git ls-files` brut à `d9313f9e` ; la forme CI exclut `.github/scripts/verifier-chemins-test.sh` et passe **3300** chemins à `verifier-chemins.sh` (`git ls-tree -r --name-only d9313f9e` hors ce test : 3300, mesuré). La note de la matrice du 2026-10-29 écrit juste : "3301 fichiers suivis, lus par `git ls-files`" | texte en annexe A, défaut 12 | `camille-po` |
| 13 | `cloture-palier-5.md` : 72 | "`A.288` (points 3 et 8) l'écrit : *"La condition est atteinte ; la levée s'écrira dans la fiche"*" pour `-06` **et** l'horloge. Ces mots sont ceux de `nadia-prof` pour `-06` seul (`lecture-nadia-entrees-de-catalogue-ce1-qlm-et-horloge.md`, section 3, ligne 93) ; pour l'horloge elle écrit autre chose (même fichier, section 2, point 9, lignes 77 et 78). Le point 8 d'A.288 ne cite aucun des deux | texte en annexe A, défaut 13 | `sofia-sm` |
| 14 | `DEC-0120` : 5116 (A.288 point 1) | "Les fiches portent toutes, ligne 14, *"Plafond de la ligne : `couvert-valide`"*". Vrai pour les neuf fiches `QLM-CE1` (ligne 14) ; la fiche de l'horloge écrit la phrase sur deux lignes, 16 et 17 | texte en annexe A, défaut 14 | `camille-po` |
| 15 | `backlog.md` : 25006, 25020, 25033, 25046, 25059, 25071, 25083, 25094, 25105, 25127 | dix lignes de stories de réserves écrites le 2026-10-28 avant A.288 disent "ligne `non couvert`" pour `QLM-CE1-02`, `-03`, `-08`, `-10`, `-06`, `-05` (trois fois), `-12`, `-01`. Ces lignes sont `couvert-valide` (A.288, A.292). Les neuf `S57-BR-QLM-CE1-*` portent un marqueur de statut sur leur titre ; ces dix lignes n'en ont aucun | marqueur en annexe A, défaut 15, en fin de chaque ligne | `camille-po` |
| 16 | `backlog.md` : 24682 (titre de `S57-BR-QLM-CE1-12`) | les huit autres titres `S57-BR-QLM-CE1-*` portent le marqueur d'A.288 ; celui de `-12` n'en porte aucun, alors que la ligne est `couvert-valide` depuis A.292. L'annotation du 2026-10-28, quatorzième (ligne 25235) écrit "inchangée, `QLM-CE1-12` reste `non couvert`", vrai à sa date, non repris dans l'annotation du 2026-10-29, dix-septième | marqueur en annexe A, défaut 16 | `camille-po` |
| 17 | `backlog.md` : 25245 (annotation du 2026-10-29, quinzième) | "Critere 3 (correction si fautif) inchange : enonces 3, 8 et 11". A.291 corrige : deux énoncés, 3 et 8. `DEC-0120` A.291 point 9 liste les passages marqués et omet cette ligne | marqueur en annexe A, défaut 17 | `camille-po` |
| 18 | `backlog.md` : 25167 à 25177 ; `MATRICE-couverture.md` : 2129 à 2134, 2136, 2138 | la colonne **"Fusion du paquet"** du backlog et la formule **"paquet ... fusionné dans `round-two` (SHA)"** de huit cellules de matrice citent la fusion de la **fiche de verdict** de `nadia-prof` (`d2adbb4e` s'intitule "Done pedagogique de ce1-qlm-01"), pas celle du paquet (`77fc8f8e`, clôture section 1). Les deux commits existent et sont vrais, mais ils ne sont pas le même | deux options en annexe A, défaut 18 ; le choix est à `camille-po`, une seule forme dans les deux fichiers | `camille-po` |
| 19 | `planning.md` : 3318, 3320, 3326, 3329, 3330 (amendement du 2026-10-28 09:56, section 6) | ces lignes donnent le cas haut à 24,20 et la réserve à 0,80 ; 3326 écrit que "le cas haut de 24,20" suppose le quatrième rejeu. A.286, le même jour après le 09:56, ramène le cas haut à 23,70 et ferme le quatrième rejeu ; l'amendement du 2026-10-29 marque les lignes de Q3 (sa section 3) mais pas celles-ci | marqueur en annexe A, défaut 19. Voir le point à trancher 1 avant de le poser | `sofia-sm` |
| 20 | `cloture-palier-5.md` : 3, 7, 11, 34, 48, 86, 88, 97, 138, 163, 189 | écrite à `fa137660`, la clôture est périmée à `a3e05f13` : (a) "s57 n'est pas clos" (3, 7), l'utilisatrice a décidé de clore le 2026-10-29 ; (b) 172 entrées de catalogue (11), 173 à `a3e05f13` ; (c) "le code et le contenu servis de `HEAD` sont ceux de `eaa3ef65`" (34, 163), faux à `a3e05f13` : `git diff --name-only eaa3ef65 a3e05f13` nomme `catalogue.yaml`, `ce1-qlm-generations.yaml`, `PaquetCe1QlmGenerationsTest.java`, un jeu fautif de test et `TexteAccentueExposeParLePortEntrantTest.java` ; (d) 165 sur 202 (48), 166 à `a3e05f13` ; (e) `QLM-CE1-12` "pas d'entrée, ligne non couverte" (86) ; (f) `Q-273-1` "seizième mention" (88), dix-neuvième en A.292 ; (g) question 2 "engager ou non `S57-QLM-CE1-12-ACCORD`" (97), engagée et faite (A.290 à A.292) ; (h) "aucune troisième lecture au-delà de `-03` et `-08`" (138), voir point à trancher 1 ; (i) obstacle "accord ... `-12` (items 3, 8, 11) ... ouvert" (189) : deux énoncés, fait ; (j) aucune mention de A.289 à A.292 | option en annexe A, défaut 20, à choisir par `sofia-sm` ; le texte d'origine reste | `sofia-sm` |

## 4. Renvois d'état à `sofia-sm`

| N | Fichier : ligne | Ce qui ne va pas | Correction proposée | Auteur |
|---|---|---|---|---|
| 21 | `planning.md` : fin de fichier (dernier amendement, ligne 3386) | aucun amendement n'inscrit A.289 à A.292 (`grep -c` de ces cinq références rend 0). Les routages de `DEC-0120` A.290, A.291 et A.292 demandent à `sofia-sm` d'inscrire l'engagement de `S57-QLM-CE1-12-ACCORD`, l'entrée de `-12` au périmètre, l'élargissement du critère 3, la mesure locale à `d9313f9e` et la ligne `couvert-valide` | un amendement `## Amendement du 2026-10-29 (UTC+01:00), palier 5 : A.289 à A.292` qui inscrit ces faits | `sofia-sm` |
| 22 | `planning.md` : 3386 à 3435 (amendement du 00:03, section 1 et section 4) | "165 sur 202", "`-12` hors périmètre, sans entrée", "9 entrées fusionnées", "engager ou non `S57-QLM-CE1-12-ACCORD`" : vrais à `fa137660`, périmés à `a3e05f13`. Ces lignes ne sont pas dans la liste des 19 marqueurs | même amendement qu'au défaut 21, qui donne 166 sur 202, 10 entrées, `S57-QLM-CE1-12-ACCORD` faite | `sofia-sm` |
| 23 | `cloture-palier-5.md` : 21 | "UTC+01:00 à partir du 26 (le changement d'heure est le 25)" : le changement est le 25 à 03:00, les heures du 25 après 03:00 sont déjà à `+01:00` | `UTC+01:00 à partir du 25 à 03:00` | `sofia-sm` |

## 5. Contradictions de chiffres entre fichiers

| Chiffre | Valeurs trouvées | Verdict |
|---|---|---|
| Couverture | 165 sur 202 : clôture, `planning.md` (00:03), `DEC-0120` A.288, matrice (note du 2026-10-28) ; 166 sur 202 : `DEC-0120` A.292, matrice (note du 2026-10-29), backlog (dix-septième annotation) | deux dates, pas une contradiction ; la clôture n'est pas à jour (défaut 20 d) |
| Tests | 3644 dans clôture, planning, `DEC-0120` A.288 et A.292, matrice | cohérent ; 3618 `@Test` aux trois commits mesurés |
| Plafond de `PAR-8` | 25,00 partout (A.282, A.286 à A.292, clôture, planning) | cohérent |
| Cas haut et réserve | 23,70 et 1,30 : A.286, A.287, A.292 point 5 ("inchangés"), clôture, planning 00:03 ; 24,20 et 0,80 : A.290 point 3 (hypothèse), A.291 point 6 ("se réalise"), A.292 point 5 | **contradiction** : point à trancher 1 |
| Fichiers suivis | 3290 (faux, corrigé par A.289) ; 3292 (`eaa3ef65` et `fa137660`, hors garde) ; 3293 (`eaa3ef65` avec garde, `7e911fad` hors garde) ; 3294 (`7e911fad` avec garde) ; 3300 et 3301 (`d9313f9e`, hors garde et brut) | cohérent une fois chaque commit et chaque périmètre nommés ; deux défauts de rédaction (défaut 11, défaut 12). 3293 et 3301 ne se contredisent pas : A.292 point 2 le dit |
| `Q-273-1` (i) | seizième (A.288, clôture), dix-septième (A.290), dix-huitième (A.291), dix-neuvième (A.292) | cohérent, croissant d'une unité |
| Registre des identifiants | 1004 identifiants, 179 préfixes (clôture, planning, garde vert) | cohérent |
| Entrées de catalogue | 163 au départ, 172 (clôture), 173 à `a3e05f13` (`grep -c '^  - id:'`) | cohérent par date |
| Borne haute du compteur de version | 171 sur 202 = 84,65 % (A.288, clôture), 172 sur 202 = 85,15 % (A.292) | cohérent par date |
| Quatorze stories de réserves | 13 titres de réserve plus `S57-QLM-CE1-ENTREES-CATALOGUE` (annotation du 2026-10-28, douzième ; `DEC-0120` A.286 point 2 compte 14 titres) | cohérent ; la clôture section 12 range `S57-QLM-CE1-ENTREES-CATALOGUE` parmi les stories de réserves alors qu'elle est faite depuis A.292 : à `sofia-sm` en défaut 20 |

## 6. Points à trancher (fond ou routage, non touchés)

1. **Quel cas haut est en vigueur, 23,70 ou 24,20 ?** `DEC-0120` A.291 point 6 écrit que le cas d'A.290 point 3 "se réalise" (troisième lecture de `-12`, que `nadia-prof` nomme ainsi) et donne 24,20 et 0,80 ; A.292 point 5 écrit "inchangés" 23,70 et 1,30, puis calcule 24,20 "sans l'affecter". Les deux phrases coexistent dans A.292. La clôture et le planning (00:03) disent 23,70. **Propriétaire : `camille-po`** pour le chiffre ; `sofia-sm` reprend sa réponse dans la clôture (défaut 20 h).
2. **En-tête de `ce1-calendrier-dates.yaml`, ligne 1** (obstacle ouvert de la clôture section 12 ; A.288 point 7). Le commentaire dit que la lecture de l'heure sur un cadran n'est pas servie ; elle l'est par `ce1-lire-l-heure` (`grep -c "paquet: ce1-lire-l-heure$"` sur le catalogue rend 1). C'est un commentaire, donc de forme, mais A.288 en confie la formulation au développeur que `sofia-sm` désigne. Proposition de texte en annexe A, défaut 2 bis, à reprendre ou à remplacer. **Propriétaire : `sofia-sm`, qui désigne le développeur.**
3. **Marqueur par ligne ou bloc daté pour la clôture** (défaut 20) : décision de méthode, à `sofia-sm`. Le précédent du 3290 est le marqueur par ligne.
4. **Graphie du verdict dans les fiches et la matrice.** `FICHE-ce1-lire-l-heure.md` écrit `VALIDE SOUS RÉSERVE` (ligne 89) ; les neuf fiches `QLM-CE1` écrivent `VALIDE SOUS RESERVE` ; les en-têtes de verdict varient aussi (`lot 4 sur 9`, `rang 2 sur 9`, `audit P2`, `passe P2`). Un `grep` sur la formule non accentuée manque la fiche de l'horloge. Les blocs de validation sont à `nadia-prof`, je ne les touche pas. **À trancher : `nadia-prof` pour ses fiches, `camille-po` pour la matrice et `DEC-0120`.**

## Annexe A. Textes proposés, mot pour mot

Chaque bloc est le texte à lire après correction. Les parties entre crochets sont des consignes de placement, pas du texte.

### Défaut 2 bis (en-tête de `ce1-calendrier-dates.yaml`, ligne 1, point à trancher 2)

```
# DEGRADE : ce paquet ne sert que le reperage d'une date dans le calendrier, par du texte ; la lecture de l'heure sur un cadran est servie par ce1-lire-l-heure, et l'ordre des mois par QLM-CP-13.
```

### Défaut 5 (`cloture-palier-5.md`, ligne 148)

```
27 min 30 s, 29 min 28 s, 28 min 36 s jusqu'au commit (31 min 04 s au total pour `-12`)
```

### Défaut 7 (`DEC-0120`, cinq titres à insérer avant les `###` des lignes 5099, 5155, 5161, 5209, 5271)

```
## Amendement du 2026-10-28, soixante-sixième (`camille-po`, palier 5 : les entrées de catalogue sont fusionnées, neuf lignes passent à `couvert-valide`)

## Amendement du 2026-10-29, soixante-septième (`camille-po`, palier 5 : correction du compte de fichiers du job `chemins`)

## Amendement du 2026-10-29, soixante-huitième (`camille-po`, palier 5 : accord de `-12` engagé, entrée de `-12` au périmètre)

## Amendement du 2026-10-29, soixante-neuvième (`camille-po`, palier 5 : accord de `-12` fautif à deux énoncés, entrée de `-12` écrite)

## Amendement du 2026-10-29, soixante-dixième (`camille-po`, palier 5 : `QLM-CE1-12` passe à `couvert-valide`)
```

### Défaut 11 (`cloture-palier-5.md`, marqueur de la ligne 170, à partir de "Mesure a")

```
Mesure a `7e911fad`, posterieur a `fa137660` : 3294 avec le test du garde-fou, 3293 hors lui, comme `git ls-files` sur le worktree du lot (3294). Mon "3292 a `eaa3ef65` et a `HEAD`" etait exact aux deux arbres, `fa137660` compris ; le compte passe a 3293 a `7e911fad`. Voir `DEC-0120` A.289.]
```

Au marqueur de la ligne 214, ajouter avant le crochet fermant : `, voir `DEC-0120` A.289`.

### Défaut 12 (`DEC-0120`, lignes 5284 et 5304)

```
ligne 5284 : `chemins` (3301 fichiers suivis par `git ls-files`, 3300 passes a `verifier-chemins.sh` en forme CI)
ligne 5304 : forme CI, sortie 0, 3300 chemins (3301 fichiers suivis)
```

### Défaut 13 (`cloture-palier-5.md`, ligne 72, phrase "A.288 ... l'écrit")

```
A.288 (point 3) l'écrit pour `-06`, dans les mots de `nadia-prof` (`lecture-nadia-entrees-de-catalogue-ce1-qlm-et-horloge.md`, section 3) : *"La condition est atteinte ; la levée s'écrira dans la fiche"* ; pour l'horloge, même fichier, section 2, point 9 : *"L'exigence de la réserve d est atteinte par l'entrée telle qu'elle est écrite ici ; la levée s'écrira dans la fiche"*.
```

### Défaut 14 (`DEC-0120`, ligne 5116)

```
Les neuf fiches QLM-CE1 portent, ligne 14, *"Plafond de la ligne : `couvert-valide`"* ; celle de l'horloge, lignes 16 et 17.
```

### Défaut 15 (`backlog.md`, fin de chacune des dix lignes)

```
**[statut au 2026-10-28, A.288 : la ligne est `couvert-valide` ; texte d'origine conserve]**
```

Pour la ligne 25105 (`-12`) :

```
**[statut au 2026-10-29, A.292 : la ligne est `couvert-valide` ; texte d'origine conserve]**
```

### Défaut 16 (`backlog.md`, fin du titre de la ligne 24682)

```
**[statut au 2026-10-29, A.292 : la ligne `QLM-CE1-12` est `couvert-valide`, entree `bandit-manchot-generations-ce1` fusionnee ; texte d'origine conserve]**
```

### Défaut 17 (`backlog.md`, ligne 25245, après "enonces 3, 8 et 11")

```
**[corrige par A.291, texte d'origine conserve : enonces 3 et 8]**
```

### Défaut 18 (deux options, une seule forme dans les deux fichiers)

Option 1, renommer : colonne du backlog `Fusion du verdict de nadia-prof`, et dans les huit cellules de matrice `Done de nadia-prof fusionne dans round-two (SHA)` à la place de `paquet X fusionne dans round-two (SHA)`.

Option 2, citer le paquet : remplacer les SHA par ceux des fusions de paquet de la clôture section 1.

```
-01 77fc8f8e   -02 7c2b448c   -03 ccfd1a0c   -04 2485dc73   -05 142c3ca7
-06 fbe5295c   -08 d37ac5f7   -10 3f39bb78   -12 bdfed668
```

### Défaut 19 (`planning.md`, fin de chacune des lignes 3318, 3320, 3326, 3329, 3330, après décision du point 1)

```
**[corrige par l'amendement du 2026-10-29 00:03 : cas haut 23,70 et reserve 1,30 depuis `DEC-0120` A.286 ; texte d'origine conserve]**
```

### Défaut 20 (`cloture-palier-5.md`, deux options)

Option 1, un bloc daté en fin de fichier, qui reprend (a) à (j) de la ligne 20 du tableau de la section 3 en renvoyant à `DEC-0120` A.289 à A.292 :

```
## 14. État au 2026-10-29, `round-two` à `a3e05f13`
```

Option 2, un marqueur par ligne, de la forme déjà employée pour le 3290 :

```
[corrige le 2026-10-29, texte d'origine conserve : <fait lu à a3e05f13>, voir `DEC-0120` A.29x]
```

## 7. Vérifications lancées avant le commit

- **Gardes de documentation**, avec `-am` : même commande qu'à la section 1, relancée sur `a3e05f13` plus ce fichier ; résultat dans le message de commit.
- **Chemins en forme CI** : `git ls-files` hors `.github/scripts/verifier-chemins-test.sh`, liste passée à `.github/scripts/verifier-chemins.sh` par l'entrée standard, jamais `< /dev/null`.
- **Préfixe de commit** : `[REL]`, ASCII, vérifié par `.github/scripts/verifier-prefixes.sh` sur le sujet du commit.
- **Non lancés** : `mvn -B clean verify`, le job `outils`, la CI GitHub. Ce fichier n'ajoute aucun fichier de code.
