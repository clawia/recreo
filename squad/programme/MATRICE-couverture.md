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
| Mathématiques | CP | Nombres et calculs | Calcul mental — additions dans la limite de 10 | Bandit manchot — calcul mental CP | Bandit manchot | en cours |
| Mathématiques | CP | Nombres et calculs | Calcul mental — soustractions dans la limite de 10 | Bandit manchot — calcul mental CP | Bandit manchot | en cours |
| Mathématiques | CM1 | Nombres et calculs | Calcul mental — tables de multiplication (jusqu'à ×10) | Bandit manchot — calcul mental CM1 | Bandit manchot | en cours |
| Mathématiques | CM1 | Nombres et calculs | Multiplications posées de nombres à 2-3 chiffres | Bandit manchot — calcul mental CM1 | Bandit manchot | en cours |

## Autres matières (aperçu — pilote la priorisation des sprints suivants)

| Matière | Niveau | Domaine | Compétence (réf. programme) | Jeu(x) couvrant | Mécanique | Statut |
|---|---|---|---|---|---|---|
| Français | CP | Langage oral / phonologie | Conscience phonologique — dénombrer les syllabes orales d'un mot | Bandit manchot — Les syllabes | bandit manchot | couvert-validé (s02, 2026-07-18) |
| Français | CP | Lecture et compréhension | Décodage — association graphème/phonème (voyelles + consonnes simples) | — | — | non couvert |
| Français | CP | Écriture — orthographe | Copie et écriture de mots simples sans erreur | — | — | non couvert |
| Français | CM1 | Orthographe | Accord sujet-verbe et accord dans le groupe nominal | — | — | non couvert |
| Français | CM1 | Lecture et compréhension | Compréhension d'un texte narratif court (questions explicites/implicites) | — | — | non couvert |
| Questionner le monde | CP | Le vivant, sa diversité, les besoins des êtres vivants | Repérer les caractéristiques du vivant (animal/végétal) | — | — | non couvert |
| Questionner le monde / Sciences | CM1 | Matière, mouvement, énergie, information | Distinguer les états de la matière (solide/liquide/gaz) | — | — | non couvert |

## Prochaines actions suggérées

- Sprint(s) suivants : prioriser au moins une compétence hors mathématiques (français lecture CP
  est le trou le plus visible côté fondamentaux) pour éviter que la matrice ne reste
  mono-matière au-delà de la Phase 0.
- Chaque nouvelle ligne doit être créée en même temps qu'une story dans `docs/backlog/backlog.md`
  citant explicitement la compétence visée (règle non négociable, voir plan §Partie A).
