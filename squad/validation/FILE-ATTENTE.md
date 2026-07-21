# File d'attente — validation pédagogique

> **Règle d'or** : jamais plus de **2-3 sprints** d'incréments non validés pédagogiquement dans
> cette file. Au-delà, le planning suivant déclenche un **GEL DU CONTENU** (priorité à la
> préparation de démo, pas de nouvelle story de contenu tant que la file n'est pas redescendue
> sous le seuil). Voir `squad/README.md` §4.

| Sprint | Incrément | Jeu / contenu | Compétence programme visée | Poussé en file le | Statut | Décidé le |
|---|---|---|---|---|---|---|
| s01 | Bandit manchot calcul mental jouable CP + CM1 (mécanique + contenu, socle Clean Architecture) | Bandit manchot — calcul mental | Mathématiques — CP : additions/soustractions ≤10 ; CM1 : tables/multiplications | 2026-07-17 | ✅ **VALIDÉ** (Done technique + Done pédago + playtest enfants) | 2026-07-18 |
| s02 | Navigation (écran de sélection + jeu centré + retour) **et** Français CP « Les syllabes » (même mécanique) | Bandit manchot — Les syllabes ; navigation | Français CP — dénombrer les syllabes | 2026-07-18 | ✅ **VALIDÉ** (Done technique + Done pédago, après 2 retouches ergo) | 2026-07-18 |
| s03 | Navigation à deux axes (par niveau / par jeu) **et** bouton paramètres par jeu (maths fenêtre+opérations ; français nb syllabes max) | navigation ; réglages | transverse (adaptation difficulté) | 2026-07-18 | ✅ **VALIDÉ** (Done technique + Done pédago) | 2026-07-18 |
| s04 | 6 déclinaisons du bandit manchot (compléments à 10, comparaison, première lettre, homophones, nature du mot, lexique anglais) | Bandit manchot (contenus) | Maths CP · Français CP/CM1 · Anglais CM1 | 2026-07-18 | ✅ **VALIDÉ** (Done technique + Done pédago) | 2026-07-19 |
| s05 | Mode session (N questions réglables, sans doublon des réussis, écran de fin) + vocabulaire anglais ≥ 50 | transverse (session) + Anglais CM1 | transverse | 2026-07-19 | ✅ **VALIDÉ** (Done technique + Done pédago, après retouches ergo) | 2026-07-19 |
| s06 | 2e mécanique « Texte à trous » (conjugaison/accords CM1, mot manquant CP, égalité CP) + archi multi-mécaniques | Texte à trous | Français CM1/CP · Maths CP | 2026-07-19 | ✅ **VALIDÉ** (Done technique + Done pédago, après retouches ergo) | 2026-07-19 |
| s07 | 3e mécanique « Quiz-aventure » (questionner le monde CP ; sciences/histoire/géo CM1) + tests de non-régression UI + quiz doublés | Quiz-aventure | Questionner le monde CP · Sciences/Histoire/Géo CM1 | 2026-07-19 | ✅ **VALIDÉ** (Done technique + Done pédago) | 2026-07-19 |
| s08 | 5 jeux de contenu (Le bon son CP ; Tables de multiplication CM1 ; EMC CP/CM1 ; Vrai/Faux sciences CM1) — mécaniques existantes | Bandit manchot + Quiz-aventure | Français CP décodage · Maths CM1 · EMC CP/CM1 · Sciences CM1 | 2026-07-20 | ✅ **VALIDÉ** (Done technique + Done pédago ; plafond tables porté à 20) | 2026-07-20 |
| s09 | Externalisation du contenu quiz (7 jeux en YAML éditables, ADR-0009) — refactor sans changement de contenu | Quiz-aventure (technique) | transverse (contenu éditable) | 2026-07-20 | ✅ **VALIDÉ** (Done technique + Done pédago, non-régression OK) | 2026-07-20 |
| s10 | Externalisation du contenu « mot → réponse » (5 jeux en YAML éditables, ADR-0010) — refactor, contenu quasi identique (léger changement distracteurs « première lettre ») | Bandit manchot (technique) | transverse (contenu éditable) | 2026-07-20 | ✅ **VALIDÉ** (Done technique + Done pédago, non-régression OK) | 2026-07-20 |

| s11 | Durcissement (renommage `niveau-jeu.yml` + découplage des tests du contenu) + fin d'externalisation (textes à trous + syllabes en YAML) — refactor, contenu inchangé | transverse (technique) | transverse (contenu éditable) | 2026-07-20 | ✅ **VALIDÉ** (non-régression CP + CM1 confirmée) | 2026-07-21 |
| s12 | 4e mécanique **glisser-déposer** (1er îlot JS) — jeu « Range les nombres » (Maths CP) + feedback partiel | Glisser-déposer | Maths CP — ordonner des nombres | 2026-07-20 | ✅ **VALIDÉ** (CP) | 2026-07-21 |
| s13 | Retours maths CP (complément=équation, sens tri/comparaison aléatoire, défaut 3) + nouveau jeu « Le bon symbole » (< > =) | Bandit manchot + Glisser-déposer | Maths CP — comparer/ordonner | 2026-07-21 | ✅ **VALIDÉ** (CP) | 2026-07-21 |

| s14 | « Décompose le nombre » (valeur positionnelle, CP+CM1) + déclinaisons glisser-déposer : Frise chronologique (Histoire CM1) & Range les nombres CM1 | Bandit manchot + Glisser-déposer | Maths CP/CM1 valeur positionnelle · Histoire CM1 frise · Maths CM1 ordonner | 2026-07-21 | **À VALIDER** (Done technique ✅ ; démo à jouer) | — |

| s15 | Toggle « inclure les centaines » (décomposition) + « Range les fractions » (Maths CM1, glisser-déposer) | Bandit manchot + Glisser-déposer | Maths CP/CM1 valeur positionnelle · Maths CM1 fractions | 2026-07-21 | **À VALIDER** (Done technique ✅ ; démo à jouer) | — |

| s16 | « Compare les fractions » (< > =, remplace le rangement) + frise chronologique VERTICALE | Bandit manchot + Glisser-déposer | Maths CM1 comparer fractions · Histoire CM1 frise | 2026-07-21 | **À VALIDER** (Done technique ✅ ; démo à jouer) | — |

> ⚠️ **3 incréments en attente** (s14, s15, s16) = **seuil de la règle d'or atteint**. Une validation
> utilisatrice est souhaitée avant d'empiler un nouvel incrément de contenu. s11/s12/s13 validés le 2026-07-21.
