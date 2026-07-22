# Roadmap — ce qu'il reste à faire (au 2026-07-22, après s20)

Vue d'ensemble du **restant**, pour prioriser les prochains sprints. État actuel : **6 mécaniques**
(bandit manchot, texte à trous, quiz-aventure, glisser-déposer, **associer/classer**, **memory**),
**~29 compétences couvert-validé** (s17 validé) **+ 4 nouvelles `en cours` s19/s20, à valider**
(Français CM1 compréhension + grammaire nature des mots ; Maths CP/CM1 monnaie). Jokers **adaptés à
la mécanique** (memory 6 ❤️, autres 3 — s19). Nouveau domaine **Grandeurs et mesures — monnaie**
amorcé (s20). Contenu à liste finie **externalisé et auto-enregistré** (quiz, mots, textes à trous,
syllabes, associer, memory).

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
   *Reste à décliner : majuscule↔minuscule…*
3. **Capital de jokers dépendant de la mécanique** — ✅ **LIVRÉ (s19)** : memory à **6 cœurs**, autres
   à 3 (`SessionEngine(mechanicId, defaultJokers)`, `MemoryMechanic.JOKERS = 6` — DEC-0015, réalise
   IDEE-1). *Reste ouvert : rendre les cœurs réglables par l'adulte (0..5).*

## B. Nouvelles mécaniques (îlot JS)
- ✅ **LIVRÉ (s17)** — **Memory** (paires) — îlot JS léger (flip CSS), réutilise `SubmitAssociations`
  (ADR-0016 / DEC-0014). 6e mécanique. 4 jeux YAML : `memory-quantite-cp`, `memory-fraction-cm1`,
  `memory-anglais-cm1`, `memory-mot-image-cp`. *Done pédagogique à valider en démo.*
- **Puzzle / assemblage** — tangram, reconstituer un mot/une phrase, carte de France.
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
- **Externaliser les derniers contenus en dur** : événements de la **frise**, pool de **fractions**
  comparées — listes finies → même patron YAML que les quiz (facile, permet l'enrichissement sans dev).
- **Enrichir les viviers** existants (quiz, EMC, anglais, le bon son…) — l'utilisatrice via les YAML.

## E. Fonctionnalités produit
- **Persistance** des profils élèves / scores / reprise de session (dette **DEC-0003** — aujourd'hui
  tout est en mémoire). Prérequis d'un vrai **suivi de progression** / tableau de bord adulte.
- **Axe de navigation « par matière »** (aujourd'hui : par niveau / par jeu).

## F. Intendance / technique
- 🔑 **Révoquer/régénérer le token GitHub** (a transité en clair) — à faire.
- Rendu **pixel / navigateur** (Playwright) si un environnement le permet un jour (dette rétro ; on
  couvre aujourd'hui au niveau HTML via `UiNonRegressionWebTest`).

## Suggestion d'ordre
~~**Jokers** (A1)~~ ✅ · ~~**associer/classer** (A2)~~ ✅ · ~~**memory** (B)~~ ✅ (s17) ·
~~**cœurs adaptés** (A3)~~ ✅ (s19) · ~~**compréhension CM1** (C)~~ ✅ (s19) ·
~~**nature des mots CM1** (A2)~~ ✅ (s19) · ~~**monnaie** (C, Grandeurs et mesures)~~ ✅ amorcée (s20) —
tous **à valider pédago** (s19 + s20 groupés). Suite : **puzzle / droite graduée** (B, nouvelle
mécanique) → **persistance** (E) quand un suivi est voulu. Le reste (géométrie, autres grandeurs et
mesures — longueurs/durées, anglais, écriture CP…) se décline ensuite sur les mécaniques disponibles
(dont associer/classer et memory) ou dépend d'une nouvelle mécanique (traçage tactile pour l'écriture CP).
