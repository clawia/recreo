# Journal des durées de sprint

Pilotage par incrément (pas de timebox fixe). On consigne ici la durée réelle de chaque sprint —
horodatage de début (planning) → fin (review) — et l'incrément livré. Le vrai frein du calendrier
reste la **validation pédagogique humaine**, pas la vitesse des agents.

| Sprint | Début | Fin | Durée effective | Incrément livré | Done technique | Done pédago |
|--------|-------|-----|-----------------|-----------------|----------------|-------------|
| s01 | 2026-07-17 09:00 | 2026-07-17 | 1 salve d'amorçage | Socle + squad + moteur de jeu + bandit manchot CP/CM1 (fat jar jouable) | ✅ CI verte (12 tests) | ✅ VALIDÉ 2026-07-18 (+ playtest) |
| s02 | 2026-07-18 | 2026-07-18 | 1 salve (+ 2 retouches ergo en démo) | ADR-0004 (tokens génériques) + Français CP « syllabes » (même mécanique) + navigation (sélection → jeu centré + retour) | ✅ CI verte (18 tests) | ✅ VALIDÉ 2026-07-18 |
| s03 | 2026-07-18 | 2026-07-18 | 1 salve | ADR-0005 (catalogue enrichi + paramètres) + navigation à deux axes (par niveau / par jeu) + bouton paramètres par jeu (maths/français) | ✅ CI verte (38 tests) | ✅ VALIDÉ 2026-07-18 |
| s04 | 2026-07-18 | 2026-07-18 | 1 salve | 6 déclinaisons du bandit manchot (maths CP, français CP/CM1, anglais CM1) — mécanique inchangée | ✅ CI verte (52 tests) | ✅ VALIDÉ 2026-07-19 |
| s05 | 2026-07-19 | 2026-07-19 | 1 salve | ADR-0006 mode session (N questions, sans doublon des réussis, écran de fin) + anglais ≥ 50 mots + réglage « nombre de questions » | ✅ CI verte (63 tests) | ✅ VALIDÉ 2026-07-19 |
| s06 | 2026-07-19 | 2026-07-19 | 1 salve | ADR-0007 archi multi-mécaniques (SessionEngine partagé) + 2e mécanique « Texte à trous » (4 jeux) | ✅ CI verte (81 tests) | ✅ VALIDÉ 2026-07-19 |

> Note : « 1 salve d'amorçage » = les agents ont produit l'incrément en une session de travail
> continue (temps réel de l'ordre de la dizaine de minutes à quelques heures selon les tâches),
> et non 48 h d'activité. Le sprint attend maintenant la démo et le Done pédagogique.
