# DEC-0015 — Compréhension & grammaire CM1 + cœurs adaptés à la mécanique

- **Type** : Produit / Pédagogique + UX (contenu + polish, aucune nouvelle mécanique)
- **Date** : 2026-07-22
- **Auteur** : Camille (PO)
- **Sprint** : s19
- **Compétence programme visée** : Français CM1 — compréhension d'un texte narratif court
  (questions explicites/implicites) ; Français CM1 — grammaire (reconnaître la nature des mots :
  nom, verbe, adjectif). Volet transverse : posture d'apprentissage / droit à l'erreur (cœurs).
- **Statut de validation** : **À VALIDER** (implémenté s19 ; Done pédagogique à prononcer par
  l'utilisatrice — validation à venir, groupée avec s20).

## Contexte / besoin
Direction s19 : **élargir la couverture par du contenu et polir l'existant**, sur des mécaniques
déjà livrées (quiz-aventure, associer/classer, memory), **sans nouvelle mécanique ni nouvel ADR**.
Deux trous de la matrice côté Français CM1 : la **compréhension de texte** (`non couvert`, repérée
comme le trou le plus visible en C de la roadmap) et la **grammaire (nature des mots)** au-delà du
bandit manchot. En parallèle, deux idées produit remontées après la validation s17 + s18 sont
mûres pour être livrées à coût quasi nul (IDEE-1, IDEE-2 du backlog).

## Décisions produit
1. **Compréhension de texte CM1** (S19-3, mécanique **quiz-aventure**) : nouveau jeu
   `content/quiz/cm1-comprehension.yml` (gameKey `comprehension`), **8 items** « lis un court texte
   + question ». Chaque item présente un petit texte narratif, puis une question à réponse unique
   avec distracteurs plausibles. Comble le trou **Français CM1 — compréhension** (était
   `non couvert`). Externalisé YAML, **auto-enregistré** par le loader (aucun code).
2. **Nom, verbe ou adjectif CM1** (S19-4, mécanique **associer/classer**) : nouveau jeu
   `content/associer/cm1-nature-mots.yml` (gameKey `classer`), **3 puzzles** de catégorisation
   (classer des mots par nature : nom / verbe / adjectif). Ouvre **Français CM1 — grammaire
   (nature des mots)** et **décline la mécanique associer/classer** de s17 (catégorisation, comme
   `vivant-non-vivant-cp`). Externalisé YAML, auto-enregistré.
3. **Capital de jokers dépendant de la mécanique** (S19-1, réalise **IDEE-1** du backlog) : le
   **memory démarre à 6 cœurs** (au lieu de 3), les autres mécaniques restent à **3** (comportement
   DEC-0013 inchangé). Le memory génère mécaniquement plus d'essais/erreurs qu'un jeu à clic ; un
   capital fixe de 3 était trop punitif. Réalisé sans nouvelle mécanique : `SessionEngine` prend un
   paramètre `defaultJokers` (`SessionEngine(mechanicId, defaultJokers)`) et `MemoryMechanic`
   déclare `JOKERS = 6`. L'affichage `❤️ × jokers` reflète le capital réel.
4. **Dés agrandis sur le memory des quantités CP** (S19-2, réalise **IDEE-2** du backlog) : les
   faces de dés (⚀⚁⚂⚃⚄⚅) de `memory-quantite-cp` sont affichées **plus grandes** pour de jeunes
   lecteurs. **Ajustement purement CSS** : variante `.memory-des .memory-face` activée par classe
   conditionnelle sur l'entryId, sans changement de contenu ni de domaine.

## Justification pédagogique
- La **compréhension de texte** est un fondamental du CM1 ; le format « court texte + question »,
  sans nouvelle mécanique, la couvre immédiatement sur le quiz-aventure.
- La **nature des mots** en catégorisation (associer/classer) matérialise le tri grammatical mieux
  qu'un simple choix, et enrichit une matière encore peu couverte par une nouvelle mécanique.
- **Cœurs adaptés** : un capital de cœurs proportionné à la mécanique respecte le droit à l'erreur
  (DEC-0013) sans arrêter une partie de memory trop tôt ; **dés agrandis** : lisibilité pour un
  jeune enfant.

## Impact
- **Domaine** : `SessionEngine(mechanicId, defaultJokers)` (nouveau paramètre) ;
  `MemoryMechanic.JOKERS = 6`. Aucun nouveau coup, aucune nouvelle mécanique.
- **Bootstrap** : 2 nouveaux packs YAML auto-enregistrés par les loaders existants
  (`QuizPackLoader` pour `cm1-comprehension.yml` ; `AssocPackLoader` pour `cm1-nature-mots.yml`).
- **Web** : variante CSS `.memory-des .memory-face` (classe conditionnelle sur l'entryId).
- **Matrice** : Français CM1 compréhension (quiz) et grammaire nature des mots (associer/classer)
  → `en cours (s19)` ; note sur l'ajustement cœurs memory (IDEE-1) et dés (IDEE-2).
- **Backlog** : **IDEE-1** et **IDEE-2** marquées **✅ LIVRÉES (s19)**.
- **ADR** : **aucun nouvel ADR** (cf. note ADR-index s19). Seul ajout structurel : le paramètre
  `defaultJokers` de `SessionEngine`.

## À confirmer en démo
- Justesse et niveau des 8 textes/questions de compréhension CM1 (point de vigilance principal).
- Pertinence des 3 puzzles de tri nom/verbe/adjectif (mots non ambigus).
- Le capital **6 cœurs** au memory est-il bien calibré (ni trop punitif, ni trop permissif) ?
- Lisibilité des dés agrandis sur `memory-quantite-cp` (pas de débordement du plateau).
