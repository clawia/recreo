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
| s07 | 2026-07-19 | 2026-07-19 | 1 salve (+ doublage des quiz en démo) | 3e mécanique « Quiz-aventure » (4 jeux, ouvre sciences/histoire/géo/questionner-le-monde) + tests de non-régression UI (ADR-0008) + viviers de quiz doublés (DEC-0006) | ✅ CI verte (99 tests) | ✅ VALIDÉ 2026-07-19 |
| s08 | 2026-07-20 | 2026-07-20 | 1 salve (+ plafond tables porté à 20) | 5 jeux de contenu sur mécaniques existantes (DEC-0007) : Le bon son (décodage CP), Tables de multiplication CM1, EMC CP/CM1, Vrai/Faux sciences — aucune nouvelle mécanique, pas de nouvel ADR | ✅ CI verte (114 tests) | ✅ VALIDÉ 2026-07-20 |
| s09 | 2026-07-20 | 2026-07-20 | 1 salve | Externalisation du contenu quiz (ADR-0009 / DEC-0006) : 7 jeux quiz en fichiers YAML éditables, chargés + validés au démarrage ; 7 sous-classes supprimées ; zéro régression | ✅ CI verte (118 tests) | ✅ VALIDÉ 2026-07-20 |
| s10 | 2026-07-20 | 2026-07-20 | 1 salve | Externalisation du contenu « mot → réponse » (ADR-0010 / DEC-0006) : 5 jeux (le bon son, homophones, première lettre, nature, anglais) en YAML éditables ; générateur générique MotChoixGenerator (modes explicit/pool) ; 5 sous-classes supprimées | ✅ CI verte (129 tests) | ✅ VALIDÉ 2026-07-20 |
| s11 | 2026-07-20 | 2026-07-20 | 1 salve | Durcissement + fin d'externalisation : renommage `niveau-jeu.yml` (ADR-0012), découplage des tests du contenu (SessionQuestionsTest), textes à trous + syllabes externalisés (ADR-0011, PhraseATrouGenerator supprimé). DEC-0006 réalisé | ✅ CI verte (113 tests) | ✅ VALIDÉ 2026-07-21 (non-régr. CP+CM1) |
| s12 | 2026-07-20 | 2026-07-20 | 1 salve (orchestration multi-sous-agents) | 4e mécanique **glisser-déposer** (1er îlot JS, ADR-0013) : jeu « Range les nombres » CP ; variant `SubmitArrangement`, moteur agnostique, drag-and-drop HTML5 natif ; +DEC-0008 (+ feedback partiel : tuiles bien placées conservées) | ✅ CI verte (130 tests) | ✅ VALIDÉ 2026-07-21 (CP) |
| s13 | 2026-07-21 | 2026-07-21 | 1 salve (orchestration multi-sous-agents) | Retours maths CP : complément=équation, sens tri/comparaison **aléatoire par question** (pas de réglage), défaut 3 questions, nouveau jeu « Le bon symbole » (< > =) ; +plan décomposition | ✅ CI verte (135 tests) | ✅ VALIDÉ 2026-07-21 (CP) |

> Note : « 1 salve d'amorçage » = les agents ont produit l'incrément en une session de travail
> continue (temps réel de l'ordre de la dizaine de minutes à quelques heures selon les tâches),
> et non 48 h d'activité. Le sprint attend maintenant la démo et le Done pédagogique.
