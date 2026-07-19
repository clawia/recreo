# Fiche de conformité — Mécanique « Quiz-aventure » (s07)

- **Mécanique** : Quiz-aventure (`domain/mechanics/quizaventure/QuizAventureMechanic`, id
  `quiz-aventure`) — 3e mécanique, **isolée**, délègue au `SessionEngine` partagé (ADR-0007).
- **Contenus** : 4 `ContentGenerator` (listes de questions/réponses vérifiées).
- **Statut global** : En cours — Done technique attendu (CI) puis Done pédagogique (démo).
- **Dernière mise à jour** : 2026-07-19, par camille-po
- **Ergonomie** : cf. DEC-0005 (carte-question + boutons de réponses ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `questionner-le-monde-cp` | Questionner le monde | Questionner le monde | CP | vivant, objets, quotidien | en cours |
| `sciences-cm1` | Sciences | Sciences et technologie | CM1 | matière, vivant, corps | en cours |
| `histoire-cm1` | Histoire | Histoire | CM1 | repères et personnages | en cours |
| `geographie-cm1` | Géographie | Géographie | CM1 | repères de la France | en cours |

## Critères pédagogiques
- [x] Chaque question a une réponse correcte unique et des distracteurs plausibles (contenu
      vérifié, testé en déterministe).
- [x] Contenu de niveau raisonnable (CP : vie quotidienne / vivant ; CM1 : repères usuels).
- [x] Nouvelle mécanique **isolée** ; réutilise mode session, réglages et écran de fin.
- [x] Présentation distincte (carte-question) des deux autres mécaniques.
- [ ] **Réserve** : le socle de questions est volontairement restreint et à enrichir/valider par
      l'utilisatrice (ce n'est pas une base exhaustive du programme).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — (à venir) · **Validée** : Non (en attente) · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
