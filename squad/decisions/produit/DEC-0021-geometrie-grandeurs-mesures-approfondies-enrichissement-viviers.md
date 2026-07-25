# DEC-0021 — Géométrie & grandeurs/mesures approfondies + enrichissement des viviers

- **Type** : Produit / Pédagogique — **100 % contenu YAML** sur mécaniques existantes
  (quiz-aventure + associer/classer). **Aucune nouvelle mécanique, aucun nouveau coup, aucun nouvel ADR.**
- **Date** : 2026-07-24
- **Auteur** : Camille (PO)
- **Sprint** : s24
- **Compétence programme visée** : **Géométrie CP** — symétrie ; **Géométrie CM1** — solides,
  périmètre, quadrilatères ; **Maths CM1 — Grandeurs et mesures** — masses, contenances, lire
  l'heure ; **Maths CP — Grandeurs et mesures** — comparer des grandeurs. Plus l'**enrichissement**
  (sans nouvelle compétence) de viviers déjà `couvert-validé` (anglais, EMC, sciences/histoire/géo,
  questionner le monde, décodage CP).
- **Statut de validation** : ✅ **VALIDÉ (utilisatrice, 2026-07-25)** — Done pédagogique prononcé par
  l'utilisatrice après démo (validé avec s25). File d'attente redescendue à zéro.

## Contexte / besoin
La file de validation est **redescendue à zéro** le 2026-07-23 (s01 → s23 tous validés
pédagogiquement, gel levé — cf. `squad/validation/FILE-ATTENTE.md`). Le planning suivant peut donc
reprendre des stories de contenu. La roadmap (`squad/programme/ROADMAP.md`) plaçait en tête du
restant **« plus de géométrie / mesures »** puis **« enrichir les viviers »**. s24 réalise ces deux
axes **par du pur contenu**, sur les mécaniques déjà livrées et validées — **aucune nouvelle
mécanique, aucun nouveau coup, aucun nouvel ADR** (cf. note ADR-index s24). Chaque jeu est
**externalisé en YAML et auto-enregistré** par les loaders existants (`QuizPackLoader`,
`AssocPackLoader` ; convention `niveau-jeu.yml`).

## Décisions produit

### 1. Géométrie approfondie (matière ouverte en s23) — pur contenu YAML
Extension de la matière **Géométrie** (ouverte en s23, DEC-0020) par **4 jeux auto-enregistrés** sur
les mécaniques existantes :
- **`cp-symetrie`** (quiz-aventure) — Géométrie CP : **la symétrie** (axes de symétrie, figure
  symétrique).
- **`cm1-solides`** (quiz-aventure) — Géométrie CM1 : **les solides** (cube, pavé, cylindre… :
  reconnaître / nommer).
- **`cm1-perimetre`** (quiz-aventure) — Géométrie CM1 : **le périmètre** d'un polygone
  (calculer / comparer).
- **`cm1-quadrilateres`** (**associer/classer**) — Géométrie CM1 : **classer les quadrilatères**
  selon leurs propriétés.

### 2. Grandeurs et mesures approfondies (Maths, domaine amorcé s20 / étendu s23) — pur contenu YAML
Extension du domaine **Grandeurs et mesures** (monnaie en s20, longueurs / durées en s23) par
**4 jeux auto-enregistrés** (tous quiz-aventure) :
- **`cm1-masses`** (quiz-aventure) — Maths CM1 : **les masses** (conversions g / kg / t).
- **`cm1-contenances`** (quiz-aventure) — Maths CM1 : **les contenances** (conversions L / cL / mL).
- **`cm1-heure`** (quiz-aventure) — Maths CM1 : **lire l'heure** (heures / minutes).
- **`cp-comparer`** (quiz-aventure) — Maths CP : **comparer des grandeurs** (longueurs, masses,
  contenances — plus grand / plus petit).

### 3. Enrichissement des viviers existants — pur contenu YAML, aucune nouvelle compétence
Étoffement de viviers déjà **`couvert-validé`** (plus d'items dans les mécaniques déjà validées),
**sans ajouter de nouvelle compétence** à la matrice :
- **`anglais-lexique`** (bandit manchot) — **+~20** mots.
- **`anglais-associer-cm1`** (associer/classer) — **+ appariements** (puzzles d'appariement FR↔EN).
- **`cp-emc`** & **`cm1-emc`** (quiz-aventure) — **+~6** items chacun.
- **`cm1-sciences`**, **`cp-questionner-le-monde`**, **`cm1-histoire`**, **`cm1-geographie`**
  (quiz-aventure) — **+~6** items chacun.
- **`cp-le-bon-son`** & **`cp-premiere-lettre`** (bandit manchot) — **+~6** items chacun.

## Justification pédagogique
- **Géométrie** : symétrie (CP) puis solides, périmètre et classification des quadrilatères (CM1)
  approfondissent un pan du programme ouvert en s23 (formes / propriétés / angles) — passage du
  « reconnaître » au « raisonner sur les propriétés et mesurer ».
- **Grandeurs et mesures** : masses, contenances et lecture de l'heure complètent le domaine aux
  côtés de la monnaie (s20) et des longueurs / durées (s23) ; la comparaison de grandeurs (CP)
  installe le sens de la grandeur avant les conversions.
- **Enrichissement des viviers** : plus d'items = plus de variété par session (moins de répétition),
  sans changer les compétences déjà validées ni les mécaniques.

## Impact
- **Domaine** : **aucun** — pur contenu. Aucune nouvelle mécanique, aucun nouveau coup.
- **Bootstrap / contenu** : **8 nouveaux packs YAML auto-enregistrés** (7 quiz : `cp-symetrie`,
  `cm1-solides`, `cm1-perimetre`, `cm1-masses`, `cm1-contenances`, `cm1-heure`, `cp-comparer` ;
  1 associer : `cm1-quadrilateres`) + **enrichissement** de packs existants (anglais, EMC, sciences,
  QLM, histoire, géo, le bon son, première lettre). **Aucune ligne de code.**
- **Web** : rien de spécifique (vues quiz-aventure et plateau d'appariement existantes).
- **Matrice** : **8 nouvelles compétences** `en cours (s24) — à valider` (Géométrie CP symétrie ;
  Géométrie CM1 solides / périmètre / quadrilatères ; Maths CM1 masses / contenances / lire l'heure ;
  Maths CP comparer des grandeurs). Les enrichissements de viviers **ne changent aucune couverture**
  (compétences déjà `couvert-validé`).
- **ADR** : **aucun nouvel ADR** (cf. note ADR-index s24) — pur contenu YAML sur mécaniques
  existantes ; matière/niveau restent des chaînes libres.

## À confirmer en démo
- **Géométrie** : justesse et niveau des items (symétrie CP ; solides / périmètre CM1) ; classement
  correct des quadrilatères (`cm1-quadrilateres`).
- **Grandeurs et mesures** : exactitude des conversions (masses g/kg/t ; contenances L/cL/mL) et de
  la lecture de l'heure (CM1) ; pertinence des comparaisons de grandeurs (CP).
- **Viviers enrichis** : justesse et niveau des nouveaux items (anglais, EMC, sciences/histoire/géo,
  QLM, le bon son, première lettre) — contenus à relire / affiner par l'utilisatrice dans les YAML.
