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

## Mathématiques — grandeurs et mesures (monnaie)

Domaine **ouvert en s20** (DEC-0016) — jusque-là entièrement absent de la matrice. Pur contenu YAML
sur des mécaniques existantes.

| Matière | Niveau | Domaine | Compétence (réf. programme) | Jeu(x) couvrant | Mécanique | Statut |
|---|---|---|---|---|---|---|
| Mathématiques | CP | Grandeurs et mesures | Monnaie — compter des euros, petites sommes, rendre la monnaie | Quiz-aventure — La monnaie CP | Quiz-aventure | couvert-validé (s20, 2026-07-22) |
| Mathématiques | CM1 | Grandeurs et mesures | Monnaie — rendre la monnaie, conversions euros/centimes | Quiz-aventure — La monnaie CM1 | Quiz-aventure | couvert-validé (s20, 2026-07-22) |
| Mathématiques | CM1 | Grandeurs et mesures | Monnaie — associer une somme en euros à sa valeur en centimes | Associer/Classer — Euros ↔ centimes | Associer/Classer | couvert-validé (s20, 2026-07-22) |

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
| Français | CP | Lecture — principe alphabétique | Correspondance majuscule ↔ minuscule (apparier A ↔ a) | Associer/Classer — Majuscule et minuscule | Associer/Classer | en cours (s21) |
| Français | CP | Grammaire — genre du nom | Distinguer masculin / féminin (classer selon le / la) | Associer/Classer — Le ou la | Associer/Classer | en cours (s21) |
| Français | CP | Écriture / lecture | Reconstituer un mot à partir de ses lettres (indice image) | Puzzle — Reconstitue le mot (images) | Puzzle | en cours (s21) |
| Français | CM1 | Vocabulaire / orthographe lexicale | Reconstituer un mot à partir d'une définition (indice définition) | Puzzle — Reconstitue le mot (définitions) | Puzzle | en cours (s21) |

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
> ajoute **4 compétences `en cours (s21)`**, toutes en Français : majuscule ↔ minuscule (associer,
> `cp-majuscule-minuscule`) et le/la masculin-féminin (classer, `cp-masculin-feminin`) — DEC-0018 ;
> reconstituer un mot CP avec indice image et CM1 avec indice définition — nouvelle **mécanique
> Puzzle** (7e, `MotPuzzleGenerator`, réutilise le coup `SubmitArrangement`, ADR-0017) — DEC-0017.
> **Frise chronologique et « Compare les fractions » sont désormais externalisées** (générateurs
> alimentés par `content/frise/*.yml` / `content/fractions/*.yml`) : refactor **technique** à
> comportement constant, **couverture inchangée** (restent `couvert-validé`). Enfin, le **capital de
> cœurs est réglable** (via l'en-tête YAML + réglage adulte — DEC-0017, roadmap A3) : transverse, sans
> nouvelle ligne de couverture. À passer `couvert-validé` après la démo/validation de s21.

## Prochaines actions suggérées

- Sprint(s) suivants : prioriser au moins une compétence hors mathématiques (français lecture CP
  est le trou le plus visible côté fondamentaux) pour éviter que la matrice ne reste
  mono-matière au-delà de la Phase 0.
- Chaque nouvelle ligne doit être créée en même temps qu'une story dans `docs/backlog/backlog.md`
  citant explicitement la compétence visée (règle non négociable, voir plan §Partie A).
