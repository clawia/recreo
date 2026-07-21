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
| Mathématiques | CP | Nombres et calculs | Ordonner des nombres (croissant ou décroissant, sens aléatoire par question) | Glisser-déposer — Range les nombres | Glisser-déposer | en cours (s12) |
| Mathématiques | CP | Nombres et calculs | Comparer deux nombres avec les signes <, >, = | Bandit manchot — Le bon symbole | Bandit manchot | en cours (s13) |
| Mathématiques | CP | Nombres et calculs | Sens de l'égalité (compléter a + ⬚ = c) | Texte à trous — Complète l'égalité | Texte à trous | couvert-validé (s06, 2026-07-19) |
| Mathématiques | CM1 | Nombres et calculs | Calcul mental — tables de multiplication (jusqu'à ×10) | Bandit manchot — calcul mental CM1 | Bandit manchot | couvert-validé (s01, 2026-07-18) |
| Mathématiques | CM1 | Nombres et calculs | Multiplications posées de nombres à 2-3 chiffres | Bandit manchot — calcul mental CM1 | Bandit manchot | couvert-validé (s01, 2026-07-18) |
| Mathématiques | CM1 | Nombres et calculs | Mémorisation des tables de multiplication (1 à 10) — entraînement dédié | Bandit manchot — Tables de multiplication | Bandit manchot | couvert-validé (s08, 2026-07-20) |

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
| Français | CM1 | Lecture et compréhension | Compréhension d'un texte narratif court (questions explicites/implicites) | — | — | non couvert |
| Questionner le monde | CP | Le vivant, les objets, l'espace et le temps | Connaissances de base sur le vivant et le quotidien (quiz) | Quiz-aventure — Questionner le monde | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Sciences et technologie | CM1 | Matière, vivant, objets techniques | Connaissances de base en sciences (états de la matière, vivant, corps) | Quiz-aventure — Sciences | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Histoire | CM1 | Repères historiques | Repères et personnages (Révolution, Louis XIV, Préhistoire…) | Quiz-aventure — Histoire | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Géographie | CM1 | Se repérer dans l'espace | Repères géographiques de la France (capitale, fleuves, relief, voisins) | Quiz-aventure — Géographie | Quiz-aventure | couvert-validé (s07, 2026-07-19) |
| Sciences et technologie | CM1 | Matière, vivant, univers | Vérifier une affirmation scientifique (vrai/faux) | Quiz-aventure — Vrai ou Faux (sciences) | Quiz-aventure | couvert-validé (s08, 2026-07-20) |
| EMC | CP | Enseignement moral et civique | Règles de vie de la classe, politesse, respect, sécurité | Quiz-aventure — Vivre ensemble | Quiz-aventure | couvert-validé (s08, 2026-07-20) |
| EMC | CM1 | Enseignement moral et civique | Valeurs et symboles de la République, droits et devoirs du citoyen | Quiz-aventure — Citoyenneté & respect | Quiz-aventure | couvert-validé (s08, 2026-07-20) |

## Prochaines actions suggérées

- Sprint(s) suivants : prioriser au moins une compétence hors mathématiques (français lecture CP
  est le trou le plus visible côté fondamentaux) pour éviter que la matrice ne reste
  mono-matière au-delà de la Phase 0.
- Chaque nouvelle ligne doit être créée en même temps qu'une story dans `docs/backlog/backlog.md`
  citant explicitement la compétence visée (règle non négociable, voir plan §Partie A).
