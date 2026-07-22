# Roadmap — ce qu'il reste à faire (au 2026-07-22, après s23)

Vue d'ensemble du **restant**, pour prioriser les prochains sprints. État actuel : **8 mécaniques**
(bandit manchot, texte à trous, quiz-aventure, glisser-déposer, associer/classer, memory,
puzzle/assemblage, **droite graduée**), **~34 compétences couvert-validé** (s17 → s20 validés) **+ 4
`en cours` s21 + 2 `en cours` s22 + 5 `en cours` s23, à valider** (s21 : Français CP
majuscule↔minuscule + le/la ; Français CP/CM1 reconstituer un mot — puzzle ; s22 : Maths CP/CM1
situer/placer un nombre sur une droite graduée ; s23 : Maths CM1 fraction sur la droite ; **Géométrie**
CP formes + CM1 propriétés/angles/symétrie ; Maths CM1 longueurs + durées). Jokers **réglables**
(capital piloté par l'en-tête YAML `jokers` + réglage adulte — s21, A3). **3 axes de navigation** : par
niveau, par jeu (désormais **filtrable** par niveau/matière — s22, E), **par matière** (s21, E).
Contenu à liste finie **entièrement externalisé et auto-enregistré** — y compris **frise + fractions**
(s21, D, dette remboursée) ; plus aucun contenu combinatoire en dur côté listes finies (les
générateurs combinatoires, ex. droite graduée, gardent leurs bornes câblées dans `BeansConfig`).

> 🧊 **File de validation au SEUIL DE GEL (3 incréments : s21 + s22 + s23, tous À VALIDER).** Le
> **prochain planning doit déclencher un GEL DU CONTENU** — priorité à la **démo / validation**, pas
> de nouvelle story de contenu tant que la file n'est pas redescendue sous le seuil (règle d'or,
> `squad/README.md` §4 ; cf. `squad/validation/FILE-ATTENTE.md`).

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
- ✅ **LIVRÉ (s22) — démarré / 1re déclinaison** — **Droite graduée** (8e mécanique,
  `domain.mechanics.droitegraduee`, ADR-0018 / DEC-0019). **Réutilise le coup `SpinAndPick`** (aucun
  nouveau coup — comme puzzle réutilise `SubmitArrangement`, memory `SubmitAssociations`). 1re
  déclinaison **nombres entiers** : l'enfant **place un nombre sur une droite graduée 0 → N** en
  cliquant la bonne graduation ; **jokers automatiques** (tick faux = cœur perdu + tick grisé).
  Générateur **combinatoire** `DroiteGradueeGenerator` (bornes câblées dans `BeansConfig`) ; 2 jeux
  `droite-graduee-cp` (Maths CP, 0 → 20) et `droite-graduee-cm1` (Maths CM1, 0 → 100) ; îlot JS de vue
  `droitegraduee`. *Done pédagogique à valider en démo.*
  - ✅ **LIVRÉ (s23) — 2e déclinaison : fractions sur la droite** (DEC-0020). La mécanique droite
    graduée est **généralisée à une liste d'étiquettes `lineTicks`** (entiers **OU** fractions) — les
    graduations affichées proviennent d'une liste d'étiquettes au lieu d'entiers recalculés ;
    **réutilise toujours `SpinAndPick`**, **aucun nouveau coup, aucune nouvelle mécanique, aucun
    nouvel ADR** (évolution de la mécanique existante). Jeu `droite-fraction-cm1` (Maths CM1, droite
    0 → 1 : 0, 1/4, 1/2, 3/4, 1). *Done pédagogique à valider en démo.*
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
  `cm1-monnaie-centimes` — DEC-0016, *à valider*). ✅ **ÉTENDU (s23)** aux **longueurs / durées**
  (DEC-0020, pur contenu YAML) : `cm1-longueurs` (conversions m/cm/km/mm), `cm1-durees` (h/min/jour/
  semaine), `cm1-mesures` (associer — équivalences). *À valider.* *Reste : contenances, masses,
  aires ; longueurs / durées au CP.*
- **Géométrie** — ✅ **OUVERT (s23) — nouvelle matière, 1re tranche** (DEC-0020, pur contenu YAML) :
  `cp-geometrie` (quiz — reconnaître les formes), `cp-formes` (associer — forme ↔ nom), `cm1-geometrie`
  (quiz — propriétés, angles, symétrie). *À valider.* *Reste : tracés / symétrie active (dépend d'un
  îlot Canvas / traçage, §B), plus de figures et de propriétés.*
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
- ✅ **LIVRÉ (s22)** — **Boutons de filtre (niveau / matière) dans la liste « Par jeu »** (backlog
  **IDEE-3**, demandé 2026-07-22, DEC-0019) : l'écran `/jeux` se filtre par CP/CM1 et/ou par matière
  via des boutons **combinables** et **réinitialisables** ; **filtrage serveur par query params**
  (`/jeux?niveau=&matiere=`), pur **read-model / vue**, sans changement de domaine (pas d'ADR).
  *Done pédagogique à valider.*

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
~~**déclinaisons associer/classer CP** (A2)~~ ✅ (s21) · ~~**droite graduée** (B, 8e mécanique — placer
un nombre entier, réutilise `SpinAndPick`)~~ ✅ (s22) · ~~**filtres « Par jeu »** (E, IDEE-3)~~ ✅ (s22) ·
~~**fractions sur la droite** (B, 2e déclinaison — droite graduée généralisée à `lineTicks`)~~ ✅ (s23) ·
~~**géométrie** (C, nouvelle matière — formes CP, propriétés/angles/symétrie CM1)~~ ✅ ouverte (s23) ·
~~**longueurs / durées** (C, Grandeurs et mesures)~~ ✅ (s23) — **s21 + s22 + s23 à valider pédago**.
🧊 **File au seuil de gel (3 incréments) : le prochain planning priorise la démo / validation, pas un
nouveau contenu** (règle d'or) — une fois la file redescendue, restent, par ordre suggéré :
**Canvas / traçage tactile CP** (B, débloque l'**écriture CP** §C + tracés / symétrie active en
géométrie) → **persistance** (E, DEC-0003 — prérequis d'un vrai suivi de progression) → **plus de
géométrie / mesures** (contenances, masses, aires ; géométrie CP/CM1 approfondie, §C). Le reste
(anglais, sciences/histoire/géo/EMC approfondis, déclinaisons puzzle — phrase, tangram, carte) se
décline sur les mécaniques disponibles (dont droite graduée, puzzle, associer/classer, memory) au fil
des sprints.
