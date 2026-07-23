# Fiche de conformité — Géométrie (nouvelle matière, s23)

- **Mécaniques** : Quiz-aventure (inchangée, id `quiz-aventure`) pour les formes CP et la géométrie
  CM1 ; Associer/Classer (inchangée, id `associer`) pour forme ↔ nom CP. **Aucune nouvelle
  mécanique, aucun nouveau coup, aucun code.**
- **Contenus** : 3 jeux **externalisés en YAML** et **auto-enregistrés** par les loaders existants
  (`QuizPackLoader`, `AssocPackLoader` ; convention `niveau-jeu.yml`).
- **Statut global** : ✅ **VALIDÉ (2026-07-23)** — Done pédagogique prononcé par l'utilisatrice après
  démo (validation groupée s21 + s22 + s23 ; DEC-0020). **Ouvre la matière « Géométrie »**, jusque-là
  absente de la matrice (1re tranche).
- **Dernière mise à jour** : 2026-07-23, par camille-po
- **Ergonomie** : cf. DEC-0005 (carte-question, quiz) + DEC-0014 (plateau d'appariement) + DEC-0020.

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `quiz-aventure` | Les formes CP (`cp-geometrie.yml`) | Géométrie | CP | reconnaître / nommer les formes usuelles (carré, rond, triangle, rectangle…) | ✅ validé (2026-07-23) |
| `associer` | Les formes (`cp-formes.yml`) | Géométrie | CP | apparier une forme à son nom (forme ↔ nom) | ✅ validé (2026-07-23) |
| `quiz-aventure` | Géométrie CM1 (`cm1-geometrie.yml`) | Géométrie | CM1 | propriétés des figures, angles, symétrie | ✅ validé (2026-07-23) |

## Critères pédagogiques
- [x] Ouvre la matière **Géométrie**, jusque-là absente de la matrice.
- [x] Formes et noms **exacts** au CP ; propriétés, angles et symétrie **corrects** au CM1 (contenu
      vérifié, relecture croisée).
- [x] Niveau adapté : CP (reconnaître / nommer, apparier forme ↔ nom) ; CM1 (raisonner sur les
      propriétés, angles, symétrie).
- [x] Pur contenu sur mécaniques existantes (quiz-aventure, associer/classer) ; contenus
      **externalisés YAML auto-enregistrés** (aucun code) ; réponse / appariement non fuités dans le DOM.
- [x] **Jokers** cohérents (DEC-0013) sur les deux mécaniques.
- [ ] **Réserve** : contenus initiaux restreints (1re tranche) — à relire/enrichir par l'utilisatrice
      directement dans les YAML ; justesse et niveau des formes / propriétés = point de vigilance
      principal de la démo.

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-23 · **Validée** : Oui (VALIDÉ le 2026-07-23, groupée s21 + s22 + s23) ·
  **Commentaires** : ouverture de la matière **Géométrie** validée — formes CP (quiz + associer
  forme ↔ nom) et propriétés / angles / symétrie CM1 (quiz) ; contenus (1re tranche) à
  relire/enrichir par l'utilisatrice dans les YAML.

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
