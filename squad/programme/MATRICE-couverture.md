# Matrice de couverture — programme ↔ jeux

Tenue par `camille-po`. Cette matrice mappe chaque **compétence du programme de l'Éducation
nationale** (par matière et par niveau, CP = cycle 2, CM1 = cycle 3) au(x) **jeu(x)** qui la
couvre(nt), avec un statut. C'est l'outil qui pilote la couverture de **toutes les matières**
(pas seulement le calcul mental) et qui révèle les trous à combler en priorité aux sprints
suivants.

> **Portée** : cette matrice est amorcée avec un socle réaliste de compétences, pas une
> retranscription exhaustive et officielle des programmes. Elle est destinée à être complétée et
> affinée sprint après sprint, avec validation de l'utilisatrice sur les compétences précises
> retenues. Les référentiels détaillés par matière vivent sous `squad/programme/<matiere>/`.

Statuts possibles : `non couvert` · `en cours` · `couvert-validé` (validé = Done pédagogique
prononcé par l'utilisatrice sur ce couple compétence/jeu).

## Mathématiques — nombres et calcul

| Matière | Niveau | Domaine | Compétence (réf. programme) | Jeu(x) couvrant | Mécanique | Statut |
|---|---|---|---|---|---|---|
| Mathématiques | CP | Nombres et calculs | Calcul mental — additions dans la limite de 10 | Bandit manchot — calcul mental CP | Bandit manchot | couvert-validé (s01, 2026-07-18) |
| Mathématiques | CP | Nombres et calculs | Calcul mental — soustractions dans la limite de 10 | Bandit manchot — calcul mental CP | Bandit manchot | couvert-validé (s01, 2026-07-18) |
| Mathématiques | CP | Nombres et calculs | Compléments à 10 | Bandit manchot — Compléments à 10 | Bandit manchot | couvert-validé (s04, 2026-07-19) |
| Mathématiques | CP | Nombres et calculs | Comparer des nombres (ordre) | Bandit manchot — Le plus grand nombre | Bandit manchot | couvert-validé (s04, 2026-07-19) |
| Mathématiques | CP | Nombres et calculs | Ordonner des nombres (croissant ou décroissant, sens aléatoire par question) | Glisser-déposer — Range les nombres | Glisser-déposer | couvert-validé (s12, 2026-07-21) |
| Mathématiques | CP | Nombres et calculs | Comparer deux nombres avec les signes <, >, = | Bandit manchot — Le bon symbole | Bandit manchot | couvert-validé (s13, 2026-07-21) |
| Mathématiques | CP | Nombres et calculs | Valeur positionnelle des chiffres (dizaines, unités) | Bandit manchot — Décompose le nombre | Bandit manchot | couvert-validé (s14, 2026-07-21) |
| Mathématiques | CM1 | Nombres et calculs | Valeur positionnelle des chiffres (centaines, dizaines, unités) | Bandit manchot — Décompose le nombre | Bandit manchot | couvert-validé (s14, 2026-07-21) |
| Mathématiques | CM1 | Nombres et calculs | Ordonner des nombres (plus grands) | Glisser-déposer — Range les nombres CM1 | Glisser-déposer | couvert-validé (s14, 2026-07-21) |
| Histoire | CM1 | Se repérer dans le temps | Ordonner des événements sur une frise chronologique | Glisser-déposer — Frise chronologique | Glisser-déposer | couvert-validé (s14, 2026-07-21) |
| Mathématiques | CM1 | Fractions | Comparer deux fractions avec les signes <, >, = | Bandit manchot — Compare les fractions | Bandit manchot | couvert-validé (s16, 2026-07-21) |
| Mathématiques | CP | Nombres et calculs | Sens de l'égalité (compléter a + ⬚ = c) | Texte à trous — Complète l'égalité | Texte à trous | couvert-validé (s06, 2026-07-19) |
| Mathématiques | CM1 | Nombres et calculs | Calcul mental — tables de multiplication (jusqu'à ×10) | Bandit manchot — calcul mental CM1 | Bandit manchot | couvert-validé (s01, 2026-07-18) |
| Mathématiques | CM1 | Nombres et calculs | Multiplications posées de nombres à 2-3 chiffres | Bandit manchot — calcul mental CM1 | Bandit manchot | couvert-validé (s01, 2026-07-18) |
| Mathématiques | CM1 | Nombres et calculs | Mémorisation des tables de multiplication (1 à 10) — entraînement dédié | Bandit manchot — Tables de multiplication | Bandit manchot | couvert-validé (s08, 2026-07-20) |
| Mathématiques | CP | Nombres et calculs | Associer une quantité à son chiffre (quantité ↔ nombre) | Memory — Quantité et chiffre | Memory | couvert-validé (s17, 2026-07-22) |
| Mathématiques | CM1 | Fractions | Associer une fraction à sa représentation (fraction ↔ dessin) | Memory — Fractions | Memory | couvert-validé (s17, 2026-07-22) |
| Mathématiques | CP | Nombres et calculs | Situer / placer un nombre sur une droite graduée (0 → 20) | Droite graduée — Place le nombre CP | Droite graduée | couvert-validé (2026-07-23) |
| Mathématiques | CM1 | Nombres et calculs | Situer / placer un nombre sur une droite graduée (0 → 100) | Droite graduée — Place le nombre CM1 | Droite graduée | couvert-validé (2026-07-23) |
| Mathématiques | CM1 | Fractions | Situer / placer une fraction sur une droite graduée (0 → 1 : 0, 1/4, 1/2, 3/4, 1) | Droite graduée — Place la fraction CM1 | Droite graduée | couvert-validé (2026-07-23) |
| Mathématiques | CP | Nombres et calculs | Situer / placer un nombre sur une droite graduée (0 → 50) | Droite graduée — Place le nombre CP (0 → 50) | Droite graduée | en cours (s25) — à valider |
| Mathématiques | CM1 | Nombres et calculs | Situer / placer un nombre sur une droite graduée (0 → 1000) | Droite graduée — Place le nombre CM1 (0 → 1000) | Droite graduée | en cours (s25) — à valider |

## Mathématiques — grandeurs et mesures (monnaie, longueurs, durées)

Domaine **ouvert en s20** (DEC-0016) par la monnaie — jusque-là entièrement absent de la matrice —,
**étendu en s23** (DEC-0020) aux **longueurs** et **durées**, **approfondi en s24** (DEC-0021)
par les **masses**, les **contenances**, la **lecture de l'heure** (CM1) et la **comparaison de
grandeurs** (CP), puis **ouvert au CP en s25** (DEC-0022) par les **longueurs** et les **durées** au
CP (1res entrées CP du domaine). Pur contenu YAML sur des mécaniques existantes.

| Matière | Niveau | Domaine | Compétence (réf. programme) | Jeu(x) couvrant | Mécanique | Statut |
|---|---|---|---|---|---|---|
| Mathématiques | CP | Grandeurs et mesures | Monnaie — compter des euros, petites sommes, rendre la monnaie | Quiz-aventure — La monnaie CP | Quiz-aventure | couvert-validé (s20, 2026-07-22) |
| Mathématiques | CM1 | Grandeurs et mesures | Monnaie — rendre la monnaie, conversions euros/centimes | Quiz-aventure — La monnaie CM1 | Quiz-aventure | couvert-validé (s20, 2026-07-22) |
| Mathématiques | CM1 | Grandeurs et mesures | Monnaie — associer une somme en euros à sa valeur en centimes | Associer/Classer — Euros ↔ centimes | Associer/Classer | couvert-validé (s20, 2026-07-22) |
| Mathématiques | CM1 | Grandeurs et mesures | Longueurs — conversions (m / cm / km / mm) | Quiz-aventure — Les longueurs | Quiz-aventure | couvert-validé (2026-07-23) |
| Mathématiques | CM1 | Grandeurs et mesures | Durées — conversions (h / min / jour / semaine) | Quiz-aventure — Les durées | Quiz-aventure | couvert-validé (2026-07-23) |
| Mathématiques | CM1 | Grandeurs et mesures | Équivalences de mesures — apparier des grandeurs équivalentes | Associer/Classer — Les équivalences | Associer/Classer | couvert-validé (2026-07-23) |
| Mathématiques | CM1 | Grandeurs et mesures | Masses — conversions (g / kg / t) | Quiz-aventure — Les masses | Quiz-aventure | en cours (s24) — à valider |
| Mathématiques | CM1 | Grandeurs et mesures | Contenances — conversions (L / cL / mL) | Quiz-aventure — Les contenances | Quiz-aventure | en cours (s24) — à valider |
| Mathématiques | CM1 | Grandeurs et mesures | Lire l'heure (heures / minutes) | Quiz-aventure — Lire l'heure | Quiz-aventure | en cours (s24) — à valider |
| Mathématiques | CP | Grandeurs et mesures | Comparer des grandeurs (longueurs, masses, contenances — plus grand / plus petit) | Quiz-aventure — Compare les grandeurs CP | Quiz-aventure | en cours (s24) — à valider |
| Mathématiques | CP | Grandeurs et mesures | Longueurs — comparer des longueurs / unités simples | Quiz-aventure — Les longueurs CP | Quiz-aventure | en cours (s25) — à valider |
| Mathématiques | CP | Grandeurs et mesures | Durées — se repérer dans le temps (jour / semaine / mois) | Quiz-aventure — Les durées CP | Quiz-aventure | en cours (s25) — à valider |

## Géométrie (nouvelle matière — ouverte en s23)

Matière **ouverte en s23** (DEC-0020) — jusque-là entièrement absente de la matrice —,
**approfondie en s24** (DEC-0021) par la **symétrie** (CP), les **solides**, le **périmètre** et les
**quadrilatères** (CM1), puis **étendue en s25** (DEC-0022) par les **aires** (CM1, 1er jeu du
domaine). Pur contenu YAML sur des mécaniques existantes (quiz-aventure, associer/classer).

| Matière | Niveau | Domaine | Compétence (réf. programme) | Jeu(x) couvrant | Mécanique | Statut |
|---|---|---|---|---|---|---|
| Géométrie | CP | Formes et figures | Reconnaître et nommer les formes usuelles (carré, rond, triangle, rectangle…) | Quiz-aventure — Les formes CP | Quiz-aventure | couvert-validé (2026-07-23) |
| Géométrie | CP | Formes et figures | Apparier une forme à son nom (forme ↔ nom) | Associer/Classer — Les formes | Associer/Classer | couvert-validé (2026-07-23) |
| Géométrie | CM1 | Figures, angles, symétrie | Propriétés des figures, angles, symétrie | Quiz-aventure — Géométrie CM1 | Quiz-aventure | couvert-validé (2026-07-23) |
| Géométrie | CP | Symétrie | Reconnaître un axe de symétrie / une figure symétrique | Quiz-aventure — La symétrie CP | Quiz-aventure | en cours (s24) — à valider |
| Géométrie | CM1 | Solides | Reconnaître et nommer les solides usuels (cube, pavé, cylindre…) | Quiz-aventure — Les solides | Quiz-aventure | en cours (s24) — à valider |
| Géométrie | CM1 | Grandeurs — périmètre | Calculer / comparer le périmètre d'un polygone | Quiz-aventure — Le périmètre | Quiz-aventure | en cours (s24) — à valider |
| Géométrie | CM1 | Figures — quadrilatères | Classer les quadrilatères selon leurs propriétés | Associer/Classer — Les quadrilatères | Associer/Classer | en cours (s24) — à valider |
| Géométrie | CM1 | Grandeurs — aires | Calculer / comparer l'aire d'une figure | Quiz-aventure — Les aires | Quiz-aventure | en cours (s25) — à valider |

## Autres matières (aperçu — pilote la priorisation des sprints suivants)

| Matière | Niveau | Domaine | Compétence (réf. programme) | Jeu(x) couvrant | Mécanique | Statut |
|---|---|---|---|---|---|---|
| Français | CP | Langage oral / phonologie | Conscience phonologique — dénombrer les syllabes orales d'un mot | Bandit manchot — Les syllabes | bandit manchot | couvert-validé (s02, 2026-07-18) |
| Français | CP | Lecture — principe alphabétique | Identifier la lettre initiale (attaque) d'un mot | Bandit manchot — La première lettre | Bandit manchot | couvert-validé (s04, 2026-07-19) |
| Français | CM1 | Orthographe grammaticale | Homophones grammaticaux (a/à, et/est, on/ont, son/sont, ou/où) | Bandit manchot — Le bon petit mot | Bandit manchot | couvert-validé (s04, 2026-07-19) |
| Français | CM1 | Grammaire | Reconnaître la nature d'un mot (nom, verbe, adjectif, déterminant) | Bandit manchot — La nature du mot | Bandit manchot | couvert-validé (s04, 2026-07-19) |
| Anglais (LV) | CM1 | Lexique | Connaître le vocabulaire courant (traduire un mot français) | Bandit manchot — Le bon mot en anglais | Bandit manchot | couvert-validé (s04, 2026-07-19) |
| Français | CM1 | Conjugaison | Présent de l'indicatif (verbes fréquents) | Texte à trous — Conjugaison | Texte à trous | couvert-validé (s06, 2026-07-19) |
| Français | CM1 | Orthographe grammaticale | Accord de l'adjectif (genre/nombre) | Texte à trous — L'accord de l'adjectif | Texte à trous | couvert-validé (s06, 2026-07-19) |
| Français | CP | Lecture / vocabulaire | Compléter une phrase par le mot manquant (sens) | Texte à trous — Le mot qui manque | Texte à trous | couvert-validé (s06, 2026-07-19) |
| Français | CP | Lecture et compréhension | Décodage — association graphème/phonème (voyelles + consonnes simples) | Bandit manchot — Le bon son | Bandit manchot | couvert-validé (s08, 2026-07-20) |
| Français | CP | Écriture — orthographe | Copie et écriture de mots simples sans erreur | — | — | non couvert |
| Français | CM1 | Lecture et compréhension | Compréhension d'un texte narratif court (questions explicites/implicites) | Quiz-aventure — Compréhension de texte | Quiz-aventure | couvert-validé (s19, 2026-07-22) |
| Français | CM1 | Grammaire | Reconnaître la nature des mots (nom, verbe, adjectif) — catégorisation | Associer/Classer — Nom, verbe ou adjectif | Associer/Classer | couvert-validé (s19, 2026-07-22) |
| Questionner le monde | CP | Le vivant, les objets, l'espace et le temps | Connaissances de base sur le vivant et le quotidien (quiz) | Quiz-aventure — Questionner le monde | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Sciences et technologie | CM1 | Matière, vivant, objets techniques | Connaissances de base en sciences (états de la matière, vivant, corps) | Quiz-aventure — Sciences | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Histoire | CM1 | Repères historiques | Repères et personnages (Révolution, Louis XIV, Préhistoire…) | Quiz-aventure — Histoire | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Géographie | CM1 | Se repérer dans l'espace | Repères géographiques de la France (capitale, fleuves, relief, voisins) | Quiz-aventure — Géographie | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Sciences et technologie | CM1 | Matière, vivant, univers | Vérifier une affirmation scientifique (vrai/faux) | Quiz-aventure — Vrai ou Faux (sciences) | Quiz-aventure | couvert-validé (s08, 2026-07-20) |
| EMC | CP | Enseignement moral et civique | Règles de vie de la classe, politesse, respect, sécurité | Quiz-aventure — Vivre ensemble | Quiz-aventure | couvert-validé (s08, 2026-07-20) |
| EMC | CM1 | Enseignement moral et civique | Valeurs et symboles de la République, droits et devoirs du citoyen | Quiz-aventure — Citoyenneté & respect | Quiz-aventure | couvert-validé (s08, 2026-07-20) |
| Questionner le monde | CP | Le vivant, les objets | Distinguer le vivant du non-vivant (catégoriser) | Associer/Classer — Vivant / non-vivant | Associer/Classer | couvert-validé (s17, 2026-07-22) |
| Anglais (LV) | CM1 | Lexique | Apparier un mot français à sa traduction anglaise (lexique) | Associer/Classer — Appariement FR↔EN ; Memory — Anglais | Associer/Classer + Memory | couvert-validé (s17, 2026-07-22) |
| Français | CP | Lecture / vocabulaire | Associer un mot écrit à son image (lecture, reconnaissance de mots) | Memory — Mot et image | Memory | couvert-validé (s17, 2026-07-22) |
| Français | CP | Lecture — principe alphabétique | Correspondance majuscule ↔ minuscule (apparier A ↔ a) | Associer/Classer — Majuscule et minuscule | Associer/Classer | couvert-validé (2026-07-23) |
| Français | CP | Grammaire — genre du nom | Distinguer masculin / féminin (classer selon le / la) | Associer/Classer — Le ou la | Associer/Classer | couvert-validé (2026-07-23) |
| Français | CP | Écriture / lecture | Reconstituer un mot à partir de ses lettres (indice image) | Puzzle — Reconstitue le mot (images) | Puzzle | couvert-validé (2026-07-23) |
| Français | CM1 | Vocabulaire / orthographe lexicale | Reconstituer un mot à partir d'une définition (indice définition) | Puzzle — Reconstitue le mot (définitions) | Puzzle | couvert-validé (2026-07-23) |
| Français | CP | Grammaire — syntaxe / ordre des mots | Reconstituer / remettre une phrase courte dans l'ordre | Puzzle — Remets la phrase dans l'ordre CP | Puzzle | en cours (s25) — à valider |
| Français | CM1 | Grammaire — syntaxe / ordre des mots | Reconstituer / remettre une phrase dans l'ordre (phrases plus longues) | Puzzle — Remets la phrase dans l'ordre CM1 | Puzzle | en cours (s25) — à valider |

> **Note s18 — rien de nouveau à couvrir.** s18 raffine l'UX des jeux de s17 (cœur consommé par le
> memory + écran « Essaye encore » + score max — DEC-0013 ; verrouillage partiel d'associer/classer,
> faces de dés du memory quantités CP, images du memory anglais CM1 — DEC-0014). **Aucune nouvelle
> compétence** : les 5 lignes s17 (associer/classer, memory) sont passées `couvert-validé` à la
> validation groupée s17 + s18 par l'utilisatrice (Done pédagogique prononcé le **2026-07-22**).

> **Note s19 — nouvelles compétences `couvert-validé` + ajustements UX sans couverture.** s19
> (DEC-0015) ajoute 2 compétences : Français CM1 compréhension (quiz `comprehension`) et Français CM1
> grammaire nature des mots (associer/classer `classer`), passées **`couvert-validé (2026-07-22)`** à
> la validation groupée s19 + s20 (Done pédagogique prononcé par l'utilisatrice). Les deux autres
> items de s19 **ne changent aucune couverture** : le capital de cœurs devient **dépendant de la
> mécanique** (memory 6 ❤️, autres 3 — IDEE-1, transverse droit à l'erreur) et les **dés du memory
> quantités CP sont agrandis** (IDEE-2, variante CSS, même compétence Maths CP quantité↔chiffre qu'en
> s17).

> **Note s20 — ouverture du domaine Grandeurs et mesures — monnaie.** s20 (DEC-0016) ajoute 3
> compétences (Maths CP monnaie ; Maths CM1 monnaie rendre/convertir ; Maths CM1 euros↔centimes), pur
> contenu YAML sur quiz-aventure et associer/classer, **`couvert-validé (2026-07-22)`** à la
> validation **groupée s19 + s20** par l'utilisatrice. Une seule correction de contenu appliquée à la
> validation : le **5 € est un billet, pas une pièce** (monnaie CP) ; le reste validé tel quel.

> **Note s21 — 4 compétences `en cours`, une 7e mécanique, refactor sans impact couverture.** s21
> ajoute **4 compétences** (Français), désormais **`couvert-validé (2026-07-23)`** : majuscule ↔ minuscule (associer,
> `cp-majuscule-minuscule`) et le/la masculin-féminin (classer, `cp-masculin-feminin`) — DEC-0018 ;
> reconstituer un mot CP avec indice image et CM1 avec indice définition — nouvelle **mécanique
> Puzzle** (7e, `MotPuzzleGenerator`, réutilise le coup `SubmitArrangement`, ADR-0017) — DEC-0017.
> **Frise chronologique et « Compare les fractions » sont désormais externalisées** (générateurs
> alimentés par `content/frise/*.yml` / `content/fractions/*.yml`) : refactor **technique** à
> comportement constant, **couverture inchangée** (restent `couvert-validé`). Enfin, le **capital de
> cœurs est réglable** (via l'en-tête YAML + réglage adulte — DEC-0017, roadmap A3) : transverse, sans
> nouvelle ligne de couverture. Validation groupée s21 + s22 + s23 : **Done pédagogique prononcé par
> l'utilisatrice après démo le 2026-07-23**.

> **Note s22 — 2 compétences `en cours`, une 8e mécanique ; filtres = ergonomie (pas de compétence).**
> s22 ajoute **2 compétences** en Maths, désormais **`couvert-validé (2026-07-23)`** : situer / placer un nombre sur une droite
> graduée — CP (0 → 20) et CM1 (0 → 100) — via la nouvelle **mécanique Droite graduée** (8e,
> `DroiteGradueeGenerator` combinatoire, **réutilise le coup `SpinAndPick`** — aucun nouveau coup,
> ADR-0018 / DEC-0019). Les **filtres « Par jeu »** (IDEE-3 : filtrer `/jeux` par niveau/matière —
> DEC-0019) sont de l'**ergonomie / navigation** : **aucune nouvelle ligne de couverture** (pas de
> compétence). Validation groupée s21 + s22 + s23 : **Done pédagogique prononcé par l'utilisatrice
> après démo le 2026-07-23**.

> **Note s23 — 5 compétences `en cours`, ouverture de la matière Géométrie ; aucune nouvelle
> mécanique.** s23 (DEC-0020) ajoute **5 compétences**, désormais **`couvert-validé (2026-07-23)`** : Maths CM1 situer une
> **fraction** sur une droite graduée (0 → 1) — **2e déclinaison de la droite graduée** obtenue par
> **généralisation des graduations à une liste d'étiquettes `lineTicks`** (entiers OU fractions),
> **réutilise `SpinAndPick`**, **aucun nouveau coup ni ADR** (`droite-fraction-cm1`) ; **Géométrie CP**
> reconnaître les formes (quiz `cp-geometrie` + associer `cp-formes`) et **Géométrie CM1** propriétés /
> angles / symétrie (quiz `cm1-geometrie`) — **nouvelle matière** ouverte par pur contenu YAML ; Maths
> CM1 Grandeurs et mesures **longueurs** (quiz `cm1-longueurs`) et **durées** (quiz `cm1-durees`, +
> associer `cm1-mesures` pour les équivalences). Géométrie et longueurs/durées = **packs YAML
> auto-enregistrés** (`QuizPackLoader`, `AssocPackLoader`), aucun code. Validation groupée
> s21 + s22 + s23 : **Done pédagogique prononcé par l'utilisatrice après démo le 2026-07-23**.

> **Note s24 — 8 compétences `en cours (s24) — à valider`, enrichissement de viviers sans nouvelle
> couverture ; aucune nouvelle mécanique.** s24 (DEC-0021) approfondit deux axes **100 % contenu
> YAML** sur les mécaniques existantes (quiz-aventure, associer/classer), **aucun code** : **Géométrie**
> — symétrie CP (quiz `cp-symetrie`), solides CM1 (quiz `cm1-solides`), périmètre CM1 (quiz
> `cm1-perimetre`), quadrilatères CM1 (associer `cm1-quadrilateres`) ; **Grandeurs et mesures** — masses
> CM1 (quiz `cm1-masses`), contenances CM1 (quiz `cm1-contenances`), lire l'heure CM1 (quiz `cm1-heure`),
> comparer des grandeurs CP (quiz `cp-comparer`). Ces 8 compétences sont **`en cours (s24)`** en attente
> du Done pédagogique (prononcé par la seule utilisatrice). En parallèle, **enrichissement de viviers
> déjà `couvert-validé`** (plus d'items, **aucune nouvelle compétence**) : `anglais-lexique` (+~20),
> `anglais-associer-cm1` (+ appariements), `cp-emc` & `cm1-emc` (+~6), `cm1-sciences`,
> `cp-questionner-le-monde`, `cm1-histoire`, `cm1-geographie` (+~6 chacun), `cp-le-bon-son` &
> `cp-premiere-lettre` (+~6). Incrément s24 poussé en `FILE-ATTENTE.md` **À VALIDER**.

> **Note s25 — 7 compétences `en cours (s25) — à valider` ; déclinaison puzzle PHRASE, aucune nouvelle
> mécanique.** s25 (DEC-0022) ajoute **7 compétences**, toutes `en cours (s25)` en attente du Done
> pédagogique (prononcé par la seule utilisatrice) : **Français CP** & **Français CM1** reconstituer /
> remettre une **phrase** dans l'ordre — **2e déclinaison de la mécanique Puzzle** (ADR-0017),
> **réutilise `PuzzleMechanic` + le coup `SubmitArrangement`** (on ordonne des **mots** au lieu de
> **lettres** via `PhrasePuzzleGenerator` + loader `content/phrase/*.yml`), **aucun nouveau coup, aucune
> nouvelle mécanique, aucun nouvel ADR** (`phrase-cp`, `phrase-cm1`) ; **Géométrie CM1 aires** (quiz
> `aires-cm1`, 1er jeu du domaine) ; **Maths CP** longueurs (quiz `longueurs-cp`) et durées (quiz
> `durees-cp`) — 1res entrées CP du domaine Grandeurs et mesures ; **Maths CP** droite graduée
> **0 → 50** (`droite-graduee-cinquante-cp`) et **Maths CM1** droite graduée **0 → 1000**
> (`droite-graduee-milliers-cm1`) — nouvelles configurations câblées d'ADR-0018 (**réutilisent
> `SpinAndPick`**). En parallèle, **contenus additionnels** sur mécaniques existantes **sans nouvelle
> compétence** : associer `synonymes-cm1` (Français CM1), `chiffre-mot-cp` (Maths CP) ; memory
> `memory-doubles-cp` (Maths CP), `memory-tables-cm1` (Maths CM1) ; puzzle lettres `puzzle-sciences-cm1`
> (Sciences CM1), `puzzle-animaux-cp` (Français CP) — plus de variété, mêmes mécaniques, **aucune
> nouvelle ligne de couverture**. **Enrichissement de viviers** déjà `couvert-validé` (anglais, EMC,
> sciences / histoire / géo / QLM, le bon son / première lettre) — **sans nouvelle compétence**.
> Incrément s25 poussé en `FILE-ATTENTE.md` **À VALIDER** — file à **2 incréments non validés
> (s24 + s25)**, sous le seuil de gel (2-3).

## Prochaines actions suggérées

- Sprint(s) suivants : prioriser au moins une compétence hors mathématiques (français lecture CP
  est le trou le plus visible côté fondamentaux) pour éviter que la matrice ne reste
  mono-matière au-delà de la Phase 0.
- Chaque nouvelle ligne doit être créée en même temps qu'une story dans `docs/backlog/backlog.md`
  citant explicitement la compétence visée (règle non négociable, voir plan §Partie A).
