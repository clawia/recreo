# Fiche de conformité — Compréhension & grammaire CM1 (s19)

- **Mécaniques** : Quiz-aventure (inchangée, id `quiz-aventure`) pour la compréhension ;
  Associer/Classer (inchangée, id `associer`) pour la nature des mots. **Aucune nouvelle mécanique
  ni nouveau coup.**
- **Contenus** : 2 jeux **externalisés en YAML dès le départ**, **auto-enregistrés** par les loaders
  existants (`QuizPackLoader` pour la compréhension, `AssocPackLoader` pour la nature des mots ;
  convention `niveau-jeu.yml`).
- **Statut global** : En cours — Done technique attendu (CI) puis Done pédagogique (démo, validation
  groupée s19 + s20).
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0005 (carte-question, compréhension) + DEC-0014 (plateau de catégorisation,
  nature des mots) + DEC-0015.

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `comprehension` | Compréhension de texte (`cm1-comprehension.yml`, 8 items) | Français | CM1 | compréhension d'un texte narratif court (questions explicites/implicites) | en cours (s19) |
| `classer` | Nom, verbe ou adjectif (`cm1-nature-mots.yml`, 3 puzzles) | Français | CM1 | reconnaître la nature des mots (nom, verbe, adjectif) — catégorisation | en cours (s19) |

## Critères pédagogiques
- [x] Compréhension : chaque item présente un **court texte** puis une question à réponse unique +
      distracteurs plausibles (contenu vérifié).
- [x] Nature des mots : chaque item→catégorie a une correspondance **correcte unique** (correction
      par encodage canonique trié côté domaine) ; mots non ambigus.
- [x] Aucune nouvelle mécanique : compréhension réutilise le quiz-aventure ; nature des mots décline
      la catégorisation d'associer/classer (comme `vivant-non-vivant-cp`).
- [x] Contenus **externalisés YAML auto-enregistrés** (aucun code) ; la réponse attendue n'est jamais
      révélée dans le DOM.
- [x] **Jokers** cohérents (DEC-0013) : un essai faux consomme 1 cœur ; associer/classer retente le
      plein plateau avec verrouillage partiel (DEC-0014, s18).
- [ ] **Réserve** : contenus initiaux volontairement restreints (8 textes / 3 puzzles) — à
      relire/enrichir par l'utilisatrice directement dans les YAML (justesse des textes/questions et
      du tri grammatical = point de vigilance principal de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — · **Validée** : — · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
