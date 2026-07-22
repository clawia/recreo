# Fiche de conformité — Géométrie (nouvelle matière, s23)

- **Mécaniques** : Quiz-aventure (inchangée, id `quiz-aventure`) pour les formes CP et la géométrie
  CM1 ; Associer/Classer (inchangée, id `associer`) pour forme ↔ nom CP. **Aucune nouvelle
  mécanique, aucun nouveau coup, aucun code.**
- **Contenus** : 3 jeux **externalisés en YAML** et **auto-enregistrés** par les loaders existants
  (`QuizPackLoader`, `AssocPackLoader` ; convention `niveau-jeu.yml`).
- **Statut global** : ⏳ **À VALIDER** — Done pédagogique à prononcer par l'utilisatrice après démo
  (DEC-0020). **Ouvre la matière « Géométrie »**, jusque-là absente de la matrice (1re tranche).
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0005 (carte-question, quiz) + DEC-0014 (plateau d'appariement) + DEC-0020.

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `quiz-aventure` | Les formes CP (`cp-geometrie.yml`) | Géométrie | CP | reconnaître / nommer les formes usuelles (carré, rond, triangle, rectangle…) | ⏳ à valider |
| `associer` | Les formes (`cp-formes.yml`) | Géométrie | CP | apparier une forme à son nom (forme ↔ nom) | ⏳ à valider |
| `quiz-aventure` | Géométrie CM1 (`cm1-geometrie.yml`) | Géométrie | CM1 | propriétés des figures, angles, symétrie | ⏳ à valider |

## Critères pédagogiques
- [ ] Ouvre la matière **Géométrie**, jusque-là absente de la matrice.
- [ ] Formes et noms **exacts** au CP ; propriétés, angles et symétrie **corrects** au CM1 (contenu
      vérifié, relecture croisée).
- [ ] Niveau adapté : CP (reconnaître / nommer, apparier forme ↔ nom) ; CM1 (raisonner sur les
      propriétés, angles, symétrie).
- [ ] Pur contenu sur mécaniques existantes (quiz-aventure, associer/classer) ; contenus
      **externalisés YAML auto-enregistrés** (aucun code) ; réponse / appariement non fuités dans le DOM.
- [ ] **Jokers** cohérents (DEC-0013) sur les deux mécaniques.
- [ ] **Réserve** : contenus initiaux restreints (1re tranche) — à relire/enrichir par l'utilisatrice
      directement dans les YAML ; justesse et niveau des formes / propriétés = point de vigilance
      principal de la démo.

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — · **Validée** : — · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
