# ADR-0008 — Stratégie de test UI : niveau HTML (MockMvc), pas de navigateur

- **Statut** : Accepté
- **Date** : 2026-07-19
- **Auteur** : Théo (architecte) + Yanis (QA)
- **Sprint** : s07

## Contexte
Plusieurs régressions d'interface sont passées en démo (s06) : écran de fin affiché en permanence
(`th:if`+`th:replace` sur le même élément), engrenage rétréci (glyphe `⚙` au lieu de l'emoji
`⚙️`), tailles de boutons, « double carte » blanche. La rétro réclame des tests de non-régression
UI. Or l'environnement d'exécution n'a **ni Node ni navigateur** (Playwright/Selenium ont échoué,
comme la toolchain Homebrew).

## Décision
On teste l'UI au **niveau markup/HTML rendu**, via **MockMvc** (`UiNonRegressionWebTest`), qui
tourne dans la CI Maven existante, sans dépendance externe :
- présence/absence de l'écran de fin selon l'état de session (start / en cours / terminé) ;
- présence de l'emoji `⚙️` (codepoint exact) sur le bouton Paramètres ;
- classes CSS attendues sur les boutons (proxy de taille : ex. `play-btn` sans `big`) ;
- assertions sur le **CSS servi** (`/css/app.css`) pour les régressions structurelles (ex. règle
  `.game-over` sans `background`/`box-shadow`).

## Conséquences
- **Positif** : couvre exactement les régressions vues en s06, zéro installation, rapide, dans la CI.
- **Limite assumée** : le **rendu pixel** (taille réelle, icône réellement peinte, chevauchements)
  n'est PAS vérifié → reste une **vérification manuelle en démo** (inscrit à la DoD).
- **Réversible** : si un environnement avec Node/navigateur devient disponible, ajouter une couche
  Playwright (smoke) par-dessus, sans retirer les tests HTML.

## Alternatives écartées
- **Playwright/Selenium maintenant** : impossible (ni Node ni navigateur ; installations bloquées).
- **Aucun test UI** : rejeté — c'est précisément le trou qui a laissé passer les bugs s06.
