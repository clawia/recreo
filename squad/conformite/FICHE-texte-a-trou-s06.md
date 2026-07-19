# Fiche de conformité — Mécanique « Texte à trous » (s06)

- **Mécanique** : Texte à trous (`domain/mechanics/texteatrou/TexteATrouMechanic`, id `texte-a-trou`)
  — 2e mécanique de la bibliothèque, **isolée** du bandit manchot (ArchUnit 5), logique de session
  **partagée** via `domain/mechanic/SessionEngine` (ADR-0007).
- **Contenus** : 4 `ContentGenerator` (listes vérifiées / combinatoire).
- **Statut global** : ✅ Validé pédago (2026-07-19) — les 4 jeux acceptés par l'utilisatrice ;
  playtest enfants à venir (jalon).
- **Dernière mise à jour** : 2026-07-19, par camille-po
- **Ergonomie** : cf. DEC-0004 (phrase avec trou visible + boutons de mots, écrans communs
  score/message/fin réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `conjugaison-cm1` | Conjugaison (présent) | Français | CM1 | présent de l'indicatif | en cours |
| `accords-cm1` | L'accord de l'adjectif | Français | CM1 | accord genre/nombre | en cours |
| `mot-manquant-cp` | Le mot qui manque | Français | CP | compléter une phrase (sens) | en cours |
| `egalite-cp` | Complète l'égalité | Maths | CP | sens de l'égalité (a + ⬚ = c) | en cours |

## Critères pédagogiques
- [x] La phrase/égalité est affichée en entier avec un **trou visible** ; la réponse attendue est
      unique et les distracteurs sont plausibles (contenu vérifié, testé en déterministe).
- [x] Contenu conforme au niveau (CM1 : conjugaison/accords usuels ; CP : phrases simples,
      égalités ≤ 10).
- [x] **Nouvelle mécanique isolée** (aucune dépendance vers bandit manchot) + logique de session
      mutualisée (mode session, réglage « nombre de questions », écran de fin réutilisés).
- [x] **Présentation distincte** du bandit manchot (phrase à compléter, pas de machine à sous).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-19
- **Validée par l'utilisatrice** : ✅ Oui
- **Commentaires** : Validé après plusieurs retouches d'ergonomie (écran de fin sans double
  carte, score/score max, bandeau de résultat espacé, engrenage emoji, correctif d'affichage de
  l'écran de fin).

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
