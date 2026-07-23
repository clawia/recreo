# DEC-0020 — Fraction sur la droite + ouverture Géométrie + longueurs / durées

- **Type** : Produit / Pédago — évolution d'une mécanique existante (droite graduée) + pur contenu
  YAML (deux nouveaux domaines).
- **Date** : 2026-07-22
- **Auteur** : Camille (PO), en appui de Théo (archi) — cf. note ADR-index s23.
- **Sprint** : s23
- **Compétence programme visée** : Maths CM1 — situer / placer une **fraction** sur une droite
  graduée (0 → 1) ; **Géométrie CP** — reconnaître les formes (formes ↔ nom) ; **Géométrie CM1** —
  propriétés des figures, angles, symétrie ; Maths CM1 — Grandeurs et mesures — **longueurs**
  (conversions m / cm / km / mm) et **durées** (h / min / jour / semaine).
- **Statut de validation** : **VALIDÉ (utilisatrice, 2026-07-23)** — Done pédagogique prononcé par
  l'utilisatrice après démo (validation groupée s21 + s22 + s23).

## Contexte / besoin
Trois livrables avancent ensemble en s23, tous **sans nouvelle mécanique ni nouveau coup** et
**sans nouvel ADR** :
1. **Fraction sur une droite** (roadmap §B — 2e déclinaison de la droite graduée). Après la 1re
   déclinaison « nombres entiers » (s22), l'enfant place désormais une **fraction** sur une droite.
2. **Géométrie** (roadmap §C — domaine encore **absent** de la matrice). Ouverture d'une **nouvelle
   matière « Géométrie »** par du pur contenu.
3. **Longueurs & durées** (roadmap §C — domaine Grandeurs et mesures, **amorcé** en s20 par la
   monnaie). Extension aux longueurs et aux durées, pur contenu.

## Décisions produit

### 1. Fraction sur une droite — généralisation de la droite graduée (ADR-0018)
La mécanique « droite graduée » (ADR-0018) est **généralisée à une liste d'étiquettes**
(`lineTicks`) : les graduations affichées ne sont plus forcément des **entiers** régulièrement
espacés, mais une **liste d'étiquettes** — entiers **OU fractions**. Nouveau jeu
**`droite-fraction-cm1`** : droite **0 → 1** avec les graduations **0, 1/4, 1/2, 3/4, 1** ; l'enfant
**place la fraction demandée** en cliquant la bonne graduation.
- **Aucun nouveau coup** : la droite graduée continue de **réutiliser `SpinAndPick`** (placer =
  choisir une graduation, cf. ADR-0018) ; l'îlot JS de vue `droitegraduee` affiche les étiquettes
  fournies (`lineTicks`) au lieu de recalculer des entiers.
- **Aucun nouvel ADR** : c'est une **évolution de la mécanique existante** (généralisation des
  graduations à une liste d'étiquettes), pas une nouvelle décision d'architecture (cf. note
  ADR-index s23).
- **Jokers automatiques** (DEC-0013 / DEC-0017) inchangés : tick faux = 1 cœur perdu + tick grisé ;
  à 0 cœur, un tick faux termine la session — comportement natif de `SpinAndPick`.

### 2. Géométrie (nouvelle matière) — pur contenu YAML
Ouverture de la matière **« Géométrie »**, jusque-là absente, par **3 jeux auto-enregistrés** sur
les mécaniques existantes (quiz-aventure, associer/classer) :
- **`cp-geometrie`** (quiz-aventure) — Géométrie CP : **reconnaître les formes** (carré, rond,
  triangle, rectangle…).
- **`cm1-geometrie`** (quiz-aventure) — Géométrie CM1 : **propriétés** des figures, **angles**,
  **symétrie**.
- **`cp-formes`** (associer/classer) — Géométrie CP : apparier **forme ↔ nom**.

### 3. Longueurs & durées (Maths CM1, Grandeurs et mesures) — pur contenu YAML
Extension du domaine **Grandeurs et mesures** (amorcé en s20 par la monnaie) par **3 jeux
auto-enregistrés** :
- **`cm1-longueurs`** (quiz-aventure) — conversions **m / cm / km / mm**.
- **`cm1-durees`** (quiz-aventure) — conversions **h / min / jour / semaine**.
- **`cm1-mesures`** (associer/classer) — apparier des **équivalences** (longueurs / durées).

## Justification pédagogique
- **Fraction sur la droite** : situer une fraction sur une droite 0 → 1 est un fondamental du sens
  de la fraction (fraction = repère sur une graduation, ordre, distance à 0 / à 1) au CM1 ; c'est la
  **suite naturelle** de la droite des entiers (s22), sans nouveau geste à apprendre.
- **Géométrie** : reconnaître et nommer les formes (CP) puis raisonner sur les propriétés, angles et
  symétries (CM1) est un pan entier du programme jusqu'ici absent de l'app.
- **Longueurs & durées** : les conversions d'unités (m/cm/km/mm, h/min/jour/semaine) complètent le
  domaine Grandeurs et mesures aux côtés de la monnaie.

## Impact
- **Domaine** : **aucun nouveau coup** — la droite graduée réutilise toujours `SpinAndPick` (généra-
  lisation des graduations à `lineTicks`) ; géométrie et longueurs/durées = **pur contenu** sur
  quiz-aventure et associer/classer. **Aucune nouvelle mécanique.**
- **Bootstrap / contenu** : 1 jeu combinatoire/paramétré `droite-fraction-cm1` (droite 0 → 1,
  étiquettes 0, 1/4, 1/2, 3/4, 1) + **6 packs YAML auto-enregistrés** (`cp-geometrie`,
  `cm1-geometrie`, `cp-formes`, `cm1-longueurs`, `cm1-durees`, `cm1-mesures`) par les loaders
  existants (`QuizPackLoader`, `AssocPackLoader` ; convention `niveau-jeu.yml`).
- **Matrice** : 5 compétences `en cours (s23)` — Maths CM1 fraction sur la droite ; Géométrie CP
  (reconnaître les formes : quiz + associer) ; Géométrie CM1 (propriétés / angles / symétrie) ;
  Maths CM1 longueurs ; Maths CM1 durées. Ouverture de la matière **Géométrie**.
- **ADR** : **aucun** — fraction = évolution de la droite graduée (ADR-0018) via `lineTicks`,
  réutilise `SpinAndPick` ; géométrie & longueurs/durées = pur contenu YAML (cf. note ADR-index s23).

## À confirmer en démo
- **Fraction sur la droite** : lisibilité de la droite 0 → 1 et des graduations fractionnaires
  (0, 1/4, 1/2, 3/4, 1) ; pas d'ambiguïté sur la graduation attendue ; ergonomie du clic pour de
  jeunes enfants ; jokers (tick faux = 1 cœur perdu + tick grisé ; 0 cœur + tick faux = fin).
- **Géométrie** : justesse et niveau des formes (CP) et des propriétés / angles / symétrie (CM1) ;
  appariement forme ↔ nom correct (`cp-formes`).
- **Longueurs & durées** : exactitude des conversions (m/cm/km/mm, h/min/jour/semaine) et des
  équivalences appariées (`cm1-mesures`).
