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
- [x] **Viviers doublés** (s07, à la demande de l'utilisatrice) : QLM CP 7→14, Sciences CM1 6→12,
      Histoire CM1 5→10, Géographie CM1 6→12 — contenu vérifié, `QuizGeneratorTest` synchronisé.
- [ ] **Réserve** : même doublé, le socle n'est pas une base exhaustive du programme ; il reste à
      enrichir/valider par l'utilisatrice. Orientation actée : **externaliser le contenu** dans des
      fichiers éditables (cf. DEC-0006) pour permettre l'enrichissement sans dev.

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-19 · **Validée** : Oui (« c'est ok ») · **Commentaires** : les 3
  présentations (machine à sous / phrase à trou / carte-quiz) sont distinctes ; viviers de quiz
  doublés jugés suffisants pour la démo. Enrichissement futur via externalisation (DEC-0006).

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
