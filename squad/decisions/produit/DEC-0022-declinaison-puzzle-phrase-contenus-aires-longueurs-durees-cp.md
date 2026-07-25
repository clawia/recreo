# DEC-0022 — Déclinaison puzzle PHRASE + contenus additionnels + aires / longueurs-durées CP + enrichissement

- **Type** : Produit / Pédagogique — **déclinaison d'une mécanique existante** (Puzzle : ordonner
  des mots) + contenus additionnels sur mécaniques existantes + pur contenu YAML.
  **Aucune nouvelle mécanique, aucun nouveau coup, aucun nouvel ADR.**
- **Date** : 2026-07-25
- **Auteur** : Camille (PO)
- **Sprint** : s25
- **Compétence programme visée** : **Français CP** & **Français CM1** — reconstituer / remettre une
  **phrase** dans l'ordre (syntaxe / ordre des mots) ; **Géométrie CM1** — les **aires** (1er jeu du
  domaine) ; **Maths CP — Grandeurs et mesures** — **longueurs** et **durées** (1res entrées CP du
  domaine) ; **Maths CP** & **Maths CM1** — situer / placer un nombre sur une droite graduée
  (**0 → 50** au CP, **0 → 1000** au CM1). Plus des **contenus additionnels** (associer, memory,
  puzzle lettres) et de l'**enrichissement** de viviers déjà `couvert-validé`, **sans nouvelle
  compétence**.
- **Statut de validation** : ✅ **VALIDÉ (utilisatrice, 2026-07-25)** — Done pédagogique prononcé par
  l'utilisatrice après démo (validé avec s24). File d'attente redescendue à zéro.

## Contexte / besoin
s24 (DEC-0021 : géométrie & grandeurs/mesures approfondies + enrichissement des viviers) est **encore
à valider** — la file porte donc **1 incrément non validé** avant s25. La roadmap
(`squad/programme/ROADMAP.md`) plaçait en tête du restant les **déclinaisons puzzle** (reconstituer
une phrase, tangram, carte de France), puis **d'autres contenus sur les mécaniques disponibles** et
**encore plus de géométrie / mesures** (aires ; longueurs / durées au CP). s25 réalise ces axes
**sans nouvelle mécanique et sans nouvel ADR** :
- **Reconstituer une phrase** est une **déclinaison de la mécanique Puzzle existante** (ADR-0017) —
  exactement comme la fraction sur la droite (s23) était une déclinaison de la droite graduée
  réutilisant `SpinAndPick` sans ADR. Le puzzle **réutilise `PuzzleMechanic` + le coup
  `SubmitArrangement`** ; on ordonne des **mots** au lieu de **lettres** via un petit
  `PhrasePuzzleGenerator` alimenté par un loader `content/phrase/*.yml`.
- Les **autres livrables** sont soit des **contenus additionnels** sur des mécaniques déjà livrées
  (droite graduée câblée, associer, memory, puzzle lettres), soit du **pur contenu YAML
  auto-enregistré** (aires, longueurs / durées CP), soit de l'**enrichissement** de viviers existants.

## Décisions produit

### 1. Déclinaison puzzle « Reconstituer une phrase » — mécanique Puzzle existante (ADR-0017)
La mécanique **Puzzle / assemblage** (ADR-0017, 7e mécanique) reçoit une **2e déclinaison** :
**« Remets la phrase dans l'ordre »**. L'enfant ordonne des **mots** (au lieu des **lettres** de la
1re déclinaison « reconstituer un mot », s21) pour reconstituer une phrase correcte.
- **Aucun nouveau coup** : le puzzle continue de **réutiliser `SubmitArrangement`** (ordonner =
  soumettre un arrangement, cf. ADR-0017 / ADR-0013), l'îlot JS `puzzle.js` affiche les **tuiles-mots**
  fournies au lieu de tuiles-lettres.
- **Aucune nouvelle mécanique, aucun nouvel ADR** : c'est une **déclinaison de la mécanique
  existante**, comme la fraction sur la droite (s23) déclinait la droite graduée sans ADR. Le seul
  ajout de code est un **générateur `PhrasePuzzleGenerator`** (dans `domain.content.impl`, ordonne des
  mots) + un **loader `content/phrase/*.yml`** (même patron d'externalisation que quiz / mots /
  associer / memory — cf. note ADR-index s25).
- **Jeux** : **`phrase-cp`** (Français CP — phrases courtes) et **`phrase-cm1`** (Français CM1 —
  phrases plus longues), jeu **« Remets la phrase dans l'ordre »**.
- **Jokers automatiques** (DEC-0013 / DEC-0017) inchangés — comportement natif du puzzle.

### 2. Contenus additionnels sur mécaniques existantes — sans nouvelle compétence
Étoffement du catalogue par de **nouveaux jeux sur des mécaniques déjà livrées et validées**, **sans
ajouter de compétence** à la matrice (mêmes mécaniques, mêmes gestes, plus de variété) :
- **Droite graduée (jeux câblés)** — 2 nouvelles configurations de la mécanique Droite graduée
  (ADR-0018, réutilise `SpinAndPick`), **bornes câblées dans `BeansConfig`** (générateur combinatoire
  `DroiteGradueeGenerator`) : **`droite-graduee-cinquante-cp`** (Maths CP, **0 → 50**) et
  **`droite-graduee-milliers-cm1`** (Maths CM1, **0 → 1000**).
- **Associer/Classer** — **`synonymes-cm1`** (Français CM1 — apparier des synonymes) et
  **`chiffre-mot-cp`** (Maths CP — apparier un chiffre à son mot).
- **Memory** — **`memory-doubles-cp`** (Maths CP — les doubles) et **`memory-tables-cm1`** (Maths CM1
  — tables de multiplication).
- **Puzzle (lettres)** — **`puzzle-sciences-cm1`** (Sciences et technologie CM1 — reconstituer un mot,
  indice) et **`puzzle-animaux-cp`** (Français CP — reconstituer un mot, indice image).

### 3. Aires (Géométrie CM1) + longueurs / durées au CP — pur contenu YAML
Extension par **pur contenu YAML auto-enregistré** (quiz-aventure) :
- **`aires-cm1`** (quiz-aventure) — **Géométrie CM1** : **les aires** (1er jeu du domaine « aires »).
- **`longueurs-cp`** (quiz-aventure) — **Maths CP — Grandeurs et mesures** : **longueurs**
  (comparaisons / unités simples) — **1re entrée CP** du domaine.
- **`durees-cp`** (quiz-aventure) — **Maths CP — Grandeurs et mesures** : **durées** (jour / semaine /
  mois, ordre du temps) — **1re entrée CP** du domaine.

### 4. Enrichissement des viviers existants — pur contenu YAML, aucune nouvelle compétence
Étoffement de viviers déjà **`couvert-validé`** (plus d'items dans les mécaniques déjà validées),
**sans ajouter de compétence** à la matrice : **`anglais-lexique`**, **`cp-emc`** & **`cm1-emc`**,
**`cm1-sciences`**, **`cp-questionner-le-monde`**, **`cm1-histoire`**, **`cm1-geographie`**,
**`cp-le-bon-son`** & **`cp-premiere-lettre`**.

## Justification pédagogique
- **Reconstituer une phrase** : ordonner des mots pour former une phrase correcte travaille la
  **syntaxe** et le **sens de la phrase** (majuscule, ordre sujet / verbe / complément, ponctuation) —
  un fondamental de la lecture / écriture, du CP (phrases courtes) au CM1 (phrases plus longues). Le
  geste (glisser pour ordonner) est **déjà connu** des enfants via le puzzle « reconstituer un mot ».
- **Aires (CM1)** : après les formes, propriétés, angles, symétrie (s23), les solides, le périmètre et
  les quadrilatères (s24), l'aire complète le raisonnement géométrique et mesuré.
- **Longueurs / durées au CP** : premières entrées **CP** du domaine Grandeurs et mesures (jusqu'ici
  surtout CM1) — comparer des longueurs et se repérer dans les durées (jour / semaine / mois) sont des
  compétences CP fondamentales.
- **Droite graduée 0 → 50 (CP) / 0 → 1000 (CM1)** : nouvelles étendues de la droite graduée déjà
  validée (0 → 20 CP / 0 → 100 CM1), pour travailler des nombres plus grands sans nouveau geste.
- **Contenus additionnels & enrichissement** : plus de jeux et d'items = plus de variété par session
  (moins de répétition), sans changer les compétences déjà validées ni les mécaniques.

## Impact
- **Domaine** : **aucun nouveau coup, aucune nouvelle mécanique.** Le puzzle phrase **réutilise
  `PuzzleMechanic` + `SubmitArrangement`** (déclinaison, comme la fraction sur la droite réutilisait
  `SpinAndPick`) ; la droite graduée câblée réutilise `SpinAndPick`. Seul ajout de code : le générateur
  `PhrasePuzzleGenerator` (ordonne des mots) + le loader `content/phrase/*.yml`, et 2 bornes câblées
  dans `BeansConfig` pour les nouvelles droites.
- **Bootstrap / contenu** :
  - **Déclinaison puzzle phrase** : `phrase-cp`, `phrase-cm1` (packs `content/phrase/*.yml`
    auto-enregistrés via le nouveau loader).
  - **Droite graduée câblée** : `droite-graduee-cinquante-cp` (0 → 50), `droite-graduee-milliers-cm1`
    (0 → 1000) — bornes câblées dans `BeansConfig`.
  - **Contenus additionnels YAML auto-enregistrés** : associer `synonymes-cm1`, `chiffre-mot-cp` ;
    memory `memory-doubles-cp`, `memory-tables-cm1` ; puzzle lettres `puzzle-sciences-cm1`,
    `puzzle-animaux-cp`.
  - **Pur contenu YAML auto-enregistré** : quiz `aires-cm1`, `longueurs-cp`, `durees-cp`.
  - **Enrichissement** de packs existants (`anglais-lexique`, `cp-emc`, `cm1-emc`, `cm1-sciences`,
    `cp-questionner-le-monde`, `cm1-histoire`, `cm1-geographie`, `cp-le-bon-son`, `cp-premiere-lettre`).
- **Web** : rien de spécifique (vues quiz-aventure, plateau d'appariement, memory, puzzle et droite
  graduée existantes ; l'îlot `puzzle.js` affiche des tuiles-mots au lieu de tuiles-lettres).
- **Matrice** : **7 nouvelles compétences** `en cours (s25) — à valider` (Français CP reconstituer une
  phrase ; Français CM1 reconstituer une phrase ; Géométrie CM1 aires ; Maths CP longueurs ; Maths CP
  durées ; Maths CP droite graduée 0 → 50 ; Maths CM1 droite graduée 0 → 1000). Les **contenus
  additionnels** (associer / memory / puzzle lettres) et l'**enrichissement des viviers** **ne
  changent aucune couverture** (compétences déjà `couvert-validé`, mêmes mécaniques).
- **ADR** : **aucun nouvel ADR** (cf. note ADR-index s25) — puzzle phrase = **déclinaison de la
  mécanique Puzzle** (ADR-0017) réutilisant `SubmitArrangement` (nouveau générateur + loader, patron
  d'externalisation connu) ; droite graduée câblée = nouvelle configuration d'ADR-0018 ; aires /
  longueurs / durées CP = pur contenu YAML ; matière / niveau restent des chaînes libres.

## À confirmer en démo
- **Reconstituer une phrase** : lisibilité des tuiles-mots (CP : phrases courtes ; CM1 : phrases plus
  longues) ; gestion de la majuscule / ponctuation ; ergonomie du glisser pour de jeunes enfants ;
  jokers automatiques.
- **Aires (CM1)** : justesse et niveau des items (aire vs périmètre, unités d'aire) ; placement du jeu
  côté **Géométrie** à confirmer avec l'utilisatrice.
- **Longueurs / durées au CP** : niveau adapté au CP (comparaisons / repères de temps simples, pas de
  conversions complexes) ; placement sous **Grandeurs et mesures**.
- **Droite graduée 0 → 50 / 0 → 1000** : lisibilité des graduations sur de plus grandes étendues ; pas
  d'ambiguïté sur la graduation attendue.
- **Contenus additionnels & viviers enrichis** : justesse et niveau des nouveaux items (synonymes,
  chiffre ↔ mot, doubles, tables, sciences / animaux en puzzle, anglais, EMC, sciences / histoire /
  géo / QLM, le bon son / première lettre) — à relire / affiner par l'utilisatrice dans les YAML.
