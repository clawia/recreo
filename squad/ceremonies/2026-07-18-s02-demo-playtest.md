# [Sprint s02] Démo — 2026-07-18

- Sprint : s02 (Navigation + Français CP via le bandit manchot)
- Animé par : Sofia ; présents : Camille, Léa, l'utilisatrice (validation)
- Résultat : ✅ **Done pédagogique prononcé** (calcul mental + français CP + navigation)

## Déroulé
Démo du nouveau flux (sélection → jeu centré + flèche retour) et du jeu Français CP « Les
syllabes ». Deux retouches d'ergonomie demandées et appliquées en direct (couche `adapter-web`
uniquement, aucun changement domaine/archi) :

| # | Retour utilisatrice | Correction | Fichiers |
|---|---------------------|------------|----------|
| 1 | Le mot ne s'affiche pas en entier sur le rouleau | Largeur de rouleau **adaptative au contenu** (mot en `nowrap`, padding) → mot lisible en entier | `app.css` |
| 2 | La consigne « Combien de syllabes ? » prend trop de place **dans** la machine | Consigne (`game.question`) **sortie de la machine**, placée entre le casier et les réponses (`.consigne`) — vaut aussi pour « = ? » en maths | `fragments/banditmanchot.html`, `app.css` |

## Validations
- Fiche `FICHE-francais-cp-syllabes.md` : Done pédagogique ✅ (2026-07-18).
- DEC-0002 (exercice syllabes) → **VALIDÉ**.
- `FILE-ATTENTE.md` : incrément s02 → **VALIDÉ**.
- Matrice : phonologie/syllabes CP → **couvert-validé**.

## Suites (demandées par l'utilisatrice pour s03)
- Navigation à **deux axes** : « par niveau puis jeux » et « par jeux ».
- **Bouton paramètres par jeu** (maths : fenêtre + opérations ; français : nb de syllabes max…).
→ ajoutées au backlog / planning s03.
