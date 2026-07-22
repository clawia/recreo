# Roadmap — ce qu'il reste à faire (au 2026-07-22, après s21)

Vue d'ensemble du **restant**, pour prioriser les prochains sprints. État actuel : **7 mécaniques**
(bandit manchot, texte à trous, quiz-aventure, glisser-déposer, associer/classer, memory,
**puzzle/assemblage**), **~34 compétences couvert-validé** (s17 → s20 validés) **+ 4 nouvelles
`en cours` s21, à valider** (Français CP majuscule↔minuscule + le/la ; Français CP/CM1 reconstituer un
mot — puzzle). Jokers **réglables** (capital piloté par l'en-tête YAML `jokers` + réglage adulte — s21,
A3). **3 axes de navigation** : par niveau, par jeu, **par matière** (s21, E). Contenu à liste finie
**entièrement externalisé et auto-enregistré** — y compris désormais **frise + fractions** (s21, D,
dette remboursée) ; plus aucun contenu combinatoire en dur.

Priorisation recommandée en tête de chaque section. Chaque item devient une story (compétence visée +
critères) au planning, met à jour la matrice, et suit le double Done (technique CI + pédago user).

## A. Évolutions transverses (mécanique / UX)
1. ✅ **LIVRÉ (s17)** — **« 3 jokers » — réessayer sur erreur** (`PLAN-jokers-retenter.md`, DEC-0013).
   3 cœurs fixes, cœur consommé par clic faux, +10 plein après retente, **0 cœur = fin de session**.
   Transverse à toutes les mécaniques à clic. *Done pédagogique à valider en démo.*
2. ✅ **LIVRÉ (s17)** — **Nouveau type de coup « associer / classer »** (coup `SubmitAssociations(Map)`,
   ADR-0016 / DEC-0014). 5e mécanique. 2 jeux YAML : `anglais-associer-cm1` (appariement FR↔EN),
   `vivant-non-vivant-cp` (catégorisation QLM CP). ✅ **Décliné (s19)** : `cm1-nature-mots` — classer
   **nom / verbe / adjectif** (Français CM1 grammaire, DEC-0015, *à valider*). ✅ **Décliné (s20)** :
   `cm1-monnaie-centimes` — apparier euros ↔ centimes (Maths CM1 monnaie, DEC-0016, *à valider*).
   ✅ **Décliné (s21)** : `cp-majuscule-minuscule` — apparier A ↔ a (appariement) et
   `cp-masculin-feminin` — classer le / la (catégorisation) — **Français CP** (DEC-0018, *à valider*).
3. **Capital de jokers dépendant de la mécanique** — ✅ **LIVRÉ (s19)** : memory à **6 cœurs**, autres
   à 3 (`SessionEngine(mechanicId, defaultJokers)`, `MemoryMechanic.JOKERS = 6` — DEC-0015, réalise
   IDEE-1). ✅ **LIVRÉ (s21) — cœurs réglables** : capital porté par l'**en-tête YAML `jokers`** (lu
   par `SessionEngine` via `settings["jokers"]` ; le 6 du memory vient désormais du YAML) + **réglage
   adulte « nombre de cœurs »** (`JokersSettingsView`, param POST `jokers`) — DEC-0017, *à valider*.

## B. Nouvelles mécaniques (îlot JS)
- ✅ **LIVRÉ (s17)** — **Memory** (paires) — îlot JS léger (flip CSS), réutilise `SubmitAssociations`
  (ADR-0016 / DEC-0014). 6e mécanique. 4 jeux YAML : `memory-quantite-cp`, `memory-fraction-cm1`,
  `memory-anglais-cm1`, `memory-mot-image-cp`. *Done pédagogique à valider en démo.*
- ✅ **LIVRÉ (s21) — démarré / 1re déclinaison** — **Puzzle / assemblage** (7e mécanique,
  `domain.mechanics.puzzle`, ADR-0017 / DEC-0017). **Réutilise le coup `SubmitArrangement`** (aucun
  nouveau coup — comme memory réutilise `SubmitAssociations`). 1re déclinaison **« reconstituer un
  mot »** (`MotPuzzleGenerator`, lettres à remettre dans l'ordre + indice) : 2 jeux YAML
  `cp-mots-images` (indice image, Français CP écriture/lecture) et `cm1-mots` (indice définition,
  vocabulaire CM1) ; îlot JS `puzzle.js`. *Done pédagogique à valider en démo.* *Reste à décliner :
  reconstituer une phrase, tangram, carte de France.*
- **Droite graduée** — placer un nombre / une fraction sur une ligne.
- **Animation-intensive (plus tard, îlot Canvas)** — attrape-taupe, runner ; **traçage tactile CP**
  (geste d'écriture — clé pour combler l'écriture CP, cf. §C).

## C. Trous de couverture du programme
Déjà `non couvert` dans la matrice :
- **Français CP — écriture / copie** de mots (nécessite traçage tactile ou saisie → dépend de §B).
- ✅ **LIVRÉ (s19)** — **Français CM1 — compréhension** d'un texte narratif court : jeu quiz
  `cm1-comprehension` (8 items « lis un court texte + question », DEC-0015, *à valider*).
- ✅ **LIVRÉ (s19)** — **Français CM1 — grammaire (nature des mots)** : jeu associer/classer
  `cm1-nature-mots` (3 puzzles nom/verbe/adjectif, DEC-0015, *à valider*).
Domaines encore **absents** (à ouvrir progressivement) :
- **Grandeurs et mesures** — ✅ **AMORCÉ (s20)** par la **monnaie** (`cp-monnaie`, `cm1-monnaie`,
  `cm1-monnaie-centimes` — DEC-0016, *à valider*). *Reste : longueurs, durées.*
- **Géométrie** (formes, symétrie, tracés).
- **Anglais** — plus de lexique + consignes de classe. **Questionner le monde CM1**. Approfondir
  Sciences / Histoire / Géo / EMC.

## D. Contenu
- ✅ **LIVRÉ (s21) — dette remboursée** : **derniers contenus en dur externalisés** — événements de la
  **frise** (`content/frise/*.yml`) et pool de **fractions** comparées (`content/fractions/*.yml`).
  `FriseChronologiqueGenerator` et `CompareFractionsGenerator` reçoivent leur liste **par
  constructeur** (nouveaux loaders, même patron que quiz/mots/associer/memory) — DEC-0018.
  Comportement constant (couverture inchangée). **Plus aucun contenu à liste finie codé en Java.**
- **Enrichir les viviers** existants (quiz, EMC, anglais, le bon son, frise, fractions, puzzle…) —
  l'utilisatrice via les YAML.

## E. Fonctionnalités produit
- **Persistance** des profils élèves / scores / reprise de session (dette **DEC-0003** — aujourd'hui
  tout est en mémoire). Prérequis d'un vrai **suivi de progression** / tableau de bord adulte.
- ✅ **LIVRÉ (s21)** — **Axe de navigation « par matière »** (3e axe, en plus de par niveau / par
  jeu) : pages `/matieres` et `/matiere/{matiere}` via `CatalogueQuery.matieres()` + `byMatiere()`
  (read-model sur le catalogue enrichi, sans nouvel ADR) — DEC-0017. *Done pédagogique à valider.*

## F. Intendance / technique
- 🔑 **Révoquer/régénérer le token GitHub** (a transité en clair) — à faire.
- Rendu **pixel / navigateur** (Playwright) si un environnement le permet un jour (dette rétro ; on
  couvre aujourd'hui au niveau HTML via `UiNonRegressionWebTest`).

## Suggestion d'ordre
~~**Jokers** (A1)~~ ✅ · ~~**associer/classer** (A2)~~ ✅ · ~~**memory** (B)~~ ✅ (s17) ·
~~**cœurs adaptés** (A3)~~ ✅ (s19) · ~~**compréhension CM1** (C)~~ ✅ (s19) ·
~~**nature des mots CM1** (A2)~~ ✅ (s19) · ~~**monnaie** (C, Grandeurs et mesures)~~ ✅ amorcée (s20) ·
~~**puzzle / assemblage** (B, 7e mécanique)~~ ✅ démarré (s21) · ~~**cœurs réglables** (A3)~~ ✅ (s21) ·
~~**navigation par matière** (E)~~ ✅ (s21) · ~~**externalisation frise + fractions** (D, dette)~~ ✅ (s21) ·
~~**déclinaisons associer/classer CP** (A2)~~ ✅ (s21) — **s21 à valider pédago**.
Restent, par ordre suggéré : **droite graduée** (B — placer un nombre/une fraction, éventuellement en
réutilisant l'ordonnancement) → **Canvas / traçage tactile CP** (B, débloque l'**écriture CP**, §C) →
**persistance** (E, DEC-0003 — prérequis d'un vrai suivi de progression) → **géométrie** (formes,
symétrie) et **longueurs / durées** (Grandeurs et mesures, §C). Le reste (anglais, sciences/histoire/
géo/EMC approfondis, déclinaisons puzzle — phrase, tangram, carte) se décline sur les mécaniques
disponibles (dont puzzle, associer/classer, memory) au fil des sprints.
