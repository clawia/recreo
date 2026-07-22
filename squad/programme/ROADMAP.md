# Roadmap — ce qu'il reste à faire (au 2026-07-22, après s17)

Vue d'ensemble du **restant**, pour prioriser les prochains sprints. État actuel : **6 mécaniques**
(bandit manchot, texte à trous, quiz-aventure, glisser-déposer, **associer/classer**, **memory**),
**~24 compétences couvert-validé** (+ 5 nouvelles `en cours` s17, à valider), **jokers / 3 cœurs
livrés (transverse, à valider)**. Contenu à liste finie **externalisé** (quiz, mots, textes à trous,
syllabes, associer, memory).

Priorisation recommandée en tête de chaque section. Chaque item devient une story (compétence visée +
critères) au planning, met à jour la matrice, et suit le double Done (technique CI + pédago user).

## A. Évolutions transverses (mécanique / UX)
1. ✅ **LIVRÉ (s17)** — **« 3 jokers » — réessayer sur erreur** (`PLAN-jokers-retenter.md`, DEC-0013).
   3 cœurs fixes, cœur consommé par clic faux, +10 plein après retente, **0 cœur = fin de session**.
   Transverse à toutes les mécaniques à clic. *Done pédagogique à valider en démo.*
2. ✅ **LIVRÉ (s17)** — **Nouveau type de coup « associer / classer »** (coup `SubmitAssociations(Map)`,
   ADR-0016 / DEC-0014). 5e mécanique. 2 jeux YAML : `anglais-associer-cm1` (appariement FR↔EN),
   `vivant-non-vivant-cp` (catégorisation QLM CP). *Reste à décliner : classer nom/verbe/adjectif (CM1),
   majuscule↔minuscule… Done pédagogique à valider en démo.*

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
- **Français CM1 — compréhension** d'un texte narratif court (quiz de compréhension possible dès
  maintenant, sans nouvelle mécanique).
Domaines encore **absents** (à ouvrir progressivement) :
- **Géométrie** (formes, symétrie, tracés) · **Grandeurs et mesures** (longueurs, durées, **monnaie**).
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
~~**Jokers** (A1)~~ ✅ · ~~**associer/classer** (A2)~~ ✅ · ~~**memory** (B)~~ ✅ (livrés s17, à valider
pédago). Suite : **compréhension CM1** (C, sans nouvelle mécanique) → **puzzle** (B) → **persistance**
(E) quand un suivi est voulu. Le reste (géométrie, mesures, anglais…) se décline ensuite sur les
mécaniques disponibles (dont associer/classer et memory).
