# Roadmap — ce qu'il reste à faire (au 2026-07-21, après s16)

Vue d'ensemble du **restant**, pour prioriser les prochains sprints. État actuel : **4 mécaniques**
(bandit manchot, texte à trous, quiz-aventure, glisser-déposer), **~24 compétences couvert-validé**
(Maths CP/CM1, Français CP/CM1, Anglais CM1, Questionner le monde CP, Sciences/Histoire/Géo CM1, EMC
CP/CM1, Histoire frise). Contenu à liste finie **externalisé** (quiz, mots, textes à trous, syllabes).

Priorisation recommandée en tête de chaque section. Chaque item devient une story (compétence visée +
critères) au planning, met à jour la matrice, et suit le double Done (technique CI + pédago user).

## A. Évolutions transverses (mécanique / UX)
1. **« 3 jokers » — réessayer sur erreur** (`PLAN-jokers-retenter.md`) — *reco : prochain sprint*.
   Fort impact pédagogique, touche toutes les mécaniques à clic. Questions PO à trancher (cf. plan).
2. **Nouveau type de coup « associer / classer »** — matching (paires) & catégorisation. Va **au-delà
   de l'ordonnancement** (`SubmitArrangement`) → **nouvelle interaction + ADR**. Débloque : associer
   mot↔image (Français CP), classer nom/verbe/adjectif (CM1), classer vivant/non-vivant (QLM CP),
   majuscule↔minuscule…

## B. Nouvelles mécaniques (îlot JS)
- **Memory** (paires) — îlot JS léger (flip CSS) : quantité↔chiffre, mot↔image, FR↔EN, fraction↔dessin.
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
**Jokers** (A1) → **compréhension CM1** (C, sans nouvelle mécanique) ou **associer/classer** (A2, gros
déblocage) → **memory/puzzle** (B) → **persistance** (E) quand un suivi est voulu. Le reste (géométrie,
mesures, anglais…) se décline ensuite sur les mécaniques disponibles.
