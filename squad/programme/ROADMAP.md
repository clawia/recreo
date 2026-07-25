# Roadmap — ce qu'il reste à faire (au 2026-07-26, après s25 livré — à valider)

Vue d'ensemble du **restant**, pour prioriser les prochains sprints. État actuel : **8 mécaniques**
(bandit manchot, texte à trous, quiz-aventure, glisser-déposer, associer/classer, memory,
puzzle/assemblage, **droite graduée**) — **ni s24 ni s25 n'ajoutent de mécanique** (s25 = déclinaison
de la mécanique Puzzle + contenus / contenu YAML). **~45 compétences couvert-validé** (s17 → s23 tous
validés) **+ 8 compétences `en cours (s24)` à valider** (géométrie : symétrie CP, solides / périmètre /
quadrilatères CM1 ; grandeurs et mesures : masses / contenances / lire l'heure CM1, comparer des
grandeurs CP — DEC-0021) **+ 7 compétences `en cours (s25)` à valider** (Français CP/CM1 reconstituer
une **phrase** — déclinaison puzzle ; Géométrie CM1 **aires** ; Maths CP **longueurs** + **durées** ;
Maths CP/CM1 droite graduée **0 → 50** / **0 → 1000** — DEC-0022). Rappel des validés s21 → s23 :
s21 : Français CP majuscule↔minuscule + le/la, Français CP/CM1 reconstituer un mot (puzzle) ; s22 :
Maths CP/CM1 situer/placer un nombre sur une droite graduée ; s23 : Maths CM1 fraction sur la droite,
**Géométrie** CP formes + CM1 propriétés/angles/symétrie, Maths CM1 longueurs + durées. Jokers
**réglables**
(capital piloté **uniquement** par l'en-tête YAML `jokers` — s21, A3). **3 axes de navigation** : par
niveau, par jeu (désormais **filtrable** par niveau/matière — s22, E), **par matière** (s21, E).
Contenu à liste finie **entièrement externalisé et auto-enregistré** — y compris **frise + fractions**
(s21, D, dette remboursée) ; plus aucun contenu combinatoire en dur côté listes finies (les
générateurs combinatoires, ex. droite graduée, gardent leurs bornes câblées dans `BeansConfig`).

> ✅ **File redescendue à ZÉRO le 2026-07-23, gel levé** — s21 + s22 + s23 validés ensemble (Done
> pédagogique prononcé par l'utilisatrice après démo). ⏳ **s24 (DEC-0021)** puis **s25 (DEC-0022)**
> poussent **2 incréments non validés en file** — s24 (géométrie & grandeurs/mesures approfondies +
> enrichissement des viviers, 100 % contenu YAML) et s25 (déclinaison puzzle **phrase** + contenus
> additionnels + aires / longueurs / durées CP + enrichissement) : **À VALIDER** tous les deux. La file
> est à **2 incréments non validés (s24 + s25)** — **sous le seuil de gel (2-3), pas de gel** ; mais un
> **3e sprint non validé le déclencherait** (règle d'or, `squad/README.md` §4 ; cf.
> `squad/validation/FILE-ATTENTE.md`). Priorité à la démo avant d'empiler un 3e incrément de contenu.

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
   `cp-masculin-feminin` — classer le / la (catégorisation) — **Français CP** (DEC-0018, ✅ validé 2026-07-23).
3. **Capital de jokers dépendant de la mécanique** — ✅ **LIVRÉ (s19)** : memory à **6 cœurs**, autres
   à 3 (`SessionEngine(mechanicId, defaultJokers)`, `MemoryMechanic.JOKERS = 6` — DEC-0015, réalise
   IDEE-1). ✅ **LIVRÉ (s21) — cœurs réglables par le YAML** : capital porté par l'**en-tête YAML
   `jokers`** (lu par `SessionEngine` via `settings["jokers"]` ; le 6 du memory vient désormais du YAML)
   — DEC-0017, ✅ validé 2026-07-23. ⚠️ **Le réglage adulte « nombre de cœurs »** (`JokersSettingsView`,
   param POST `jokers`), initialement prévu par DEC-0017, a été **retiré sur demande de l'utilisatrice**
   (2026-07-22) : les cœurs se paramètrent **uniquement dans le YAML**. `JokersSettingsView` supprimée,
   plus de bloc jokers dans `parametres.html`.

## B. Nouvelles mécaniques (îlot JS)
- ✅ **LIVRÉ (s17)** — **Memory** (paires) — îlot JS léger (flip CSS), réutilise `SubmitAssociations`
  (ADR-0016 / DEC-0014). 6e mécanique. 4 jeux YAML : `memory-quantite-cp`, `memory-fraction-cm1`,
  `memory-anglais-cm1`, `memory-mot-image-cp`. *Done pédagogique à valider en démo.*
- ✅ **LIVRÉ (s21) — démarré / 1re déclinaison** — **Puzzle / assemblage** (7e mécanique,
  `domain.mechanics.puzzle`, ADR-0017 / DEC-0017). **Réutilise le coup `SubmitArrangement`** (aucun
  nouveau coup — comme memory réutilise `SubmitAssociations`). 1re déclinaison **« reconstituer un
  mot »** (`MotPuzzleGenerator`, lettres à remettre dans l'ordre + indice) : 2 jeux YAML
  `cp-mots-images` (indice image, Français CP écriture/lecture) et `cm1-mots` (indice définition,
  vocabulaire CM1) ; îlot JS `puzzle.js`. *Done pédagogique ✅ validé 2026-07-23.*
  - ✅ **LIVRÉ (s25) — 2e déclinaison : reconstituer une PHRASE** (DEC-0022). Le puzzle **ordonne des
    mots** au lieu de **lettres** — jeu **« Remets la phrase dans l'ordre »** — via un petit
    **`PhrasePuzzleGenerator`** (dans `domain.content.impl`) + un **loader `content/phrase/*.yml`** ;
    l'îlot `puzzle.js` affiche des tuiles-mots. **Réutilise toujours `PuzzleMechanic` + le coup
    `SubmitArrangement`**, **aucun nouveau coup, aucune nouvelle mécanique, aucun nouvel ADR**
    (déclinaison de la mécanique existante — exactement comme la fraction sur la droite déclinait la
    droite graduée en s23). Jeux `phrase-cp` (Français CP, phrases courtes) et `phrase-cm1`
    (Français CM1, phrases plus longues). *⏳ Done pédagogique à valider (démo).*
  - 🔜 **PLANIFIÉ (s26) — tangram + carte de France = SPRINT MÉCANIQUE 2-D DÉDIÉ.** Ces deux
    déclinaisons ne relèvent **pas** du puzzle 1-D (ordonner une liste) : elles demandent un
    **placement spatial 2-D** et donc une **nouvelle mécanique** — **nouveau coup type
    `SubmitPlacement`**, **plateau géométrique** (cibles / positions 2-D), **îlot Canvas/SVG**, et un
    **nouvel ADR**. Ordre au sein du sprint : **carte de France d'abord** (placer des éléments sur des
    **cibles**, **sans rotation** — plus simple), **tangram ensuite** (placement + rotation des
    pièces). C'est la **prochaine grosse pierre** (voir §Suggestion d'ordre).
- ✅ **LIVRÉ (s22) — démarré / 1re déclinaison** — **Droite graduée** (8e mécanique,
  `domain.mechanics.droitegraduee`, ADR-0018 / DEC-0019). **Réutilise le coup `SpinAndPick`** (aucun
  nouveau coup — comme puzzle réutilise `SubmitArrangement`, memory `SubmitAssociations`). 1re
  déclinaison **nombres entiers** : l'enfant **place un nombre sur une droite graduée 0 → N** en
  cliquant la bonne graduation ; **jokers automatiques** (tick faux = cœur perdu + tick grisé).
  Générateur **combinatoire** `DroiteGradueeGenerator` (bornes câblées dans `BeansConfig`) ; 2 jeux
  `droite-graduee-cp` (Maths CP, 0 → 20) et `droite-graduee-cm1` (Maths CM1, 0 → 100) ; îlot JS de vue
  `droitegraduee`. *Done pédagogique ✅ validé 2026-07-23.*
  - ✅ **LIVRÉ (s23) — 2e déclinaison : fractions sur la droite** (DEC-0020). La mécanique droite
    graduée est **généralisée à une liste d'étiquettes `lineTicks`** (entiers **OU** fractions) — les
    graduations affichées proviennent d'une liste d'étiquettes au lieu d'entiers recalculés ;
    **réutilise toujours `SpinAndPick`**, **aucun nouveau coup, aucune nouvelle mécanique, aucun
    nouvel ADR** (évolution de la mécanique existante). Jeu `droite-fraction-cm1` (Maths CM1, droite
    0 → 1 : 0, 1/4, 1/2, 3/4, 1). *Done pédagogique ✅ validé 2026-07-23.*
  - ✅ **LIVRÉ (s25) — contenus additionnels câblés** (DEC-0022) : 2 nouvelles étendues de la droite
    graduée — `droite-graduee-cinquante-cp` (Maths CP, **0 → 50**) et `droite-graduee-milliers-cm1`
    (Maths CM1, **0 → 1000**) — nouvelles configurations combinatoires (**bornes câblées dans
    `BeansConfig`**, **réutilisent `SpinAndPick`**), **aucune nouvelle mécanique, aucun nouvel ADR**.
    *⏳ Done pédagogique à valider (démo).*
- 🔜 **PLANIFIÉ (s26) — Mécanique de placement 2-D (îlot Canvas/SVG)** — **prochaine grosse pierre.**
  Nouvelle mécanique pour **tangram** et **carte de France** : **nouveau coup type `SubmitPlacement`**,
  **plateau géométrique** (cibles / positions 2-D), **îlot Canvas/SVG**, **nouvel ADR**. Carte de
  France d'abord (placement sur cibles, sans rotation), tangram ensuite (placement + rotation).
- **Animation-intensive (îlot Canvas)** — attrape-taupe, runner. *(Le **traçage tactile CP** est
  écarté : voir §C, écriture CP hors scope.)*

## C. Trous de couverture du programme
Déjà `non couvert` dans la matrice :
- 🚫 **HORS SCOPE — Français CP — écriture / copie** de mots (traçage tactile / saisie) : écarté du
  produit (décision utilisatrice, 2026-07-23). La lecture CP reste couverte (le bon son, mot↔image,
  reconstitue le mot…) ; le geste d'écriture n'est pas visé.
- ✅ **LIVRÉ (s19)** — **Français CM1 — compréhension** d'un texte narratif court : jeu quiz
  `cm1-comprehension` (8 items « lis un court texte + question », DEC-0015, *à valider*).
- ✅ **LIVRÉ (s19)** — **Français CM1 — grammaire (nature des mots)** : jeu associer/classer
  `cm1-nature-mots` (3 puzzles nom/verbe/adjectif, DEC-0015, *à valider*).
Domaines encore **absents** (à ouvrir progressivement) :
- **Grandeurs et mesures** — ✅ **AMORCÉ (s20)** par la **monnaie** (`cp-monnaie`, `cm1-monnaie`,
  `cm1-monnaie-centimes` — DEC-0016, ✅ validé 2026-07-23). ✅ **ÉTENDU (s23)** aux **longueurs / durées**
  (DEC-0020, pur contenu YAML) : `cm1-longueurs` (conversions m/cm/km/mm), `cm1-durees` (h/min/jour/
  semaine), `cm1-mesures` (associer — équivalences). *✅ Validé 2026-07-23.* ✅ **APPROFONDI (s24)**
  (DEC-0021, pur contenu YAML) : `cm1-masses` (g/kg/t), `cm1-contenances` (L/cL/mL), `cm1-heure` (lire
  l'heure), `cp-comparer` (comparer des grandeurs CP). *⏳ À valider (démo).* ✅ **OUVERT AU CP (s25)**
  (DEC-0022, pur contenu YAML) : `longueurs-cp` (comparer des longueurs / unités simples) et
  `durees-cp` (se repérer dans le temps : jour / semaine / mois) — **1res entrées CP** du domaine.
  *⏳ À valider (démo).* *Reste : approfondissements CP ; conversions plus riches.*
- **Géométrie** — ✅ **OUVERT (s23) — nouvelle matière, 1re tranche** (DEC-0020, pur contenu YAML) :
  `cp-geometrie` (quiz — reconnaître les formes), `cp-formes` (associer — forme ↔ nom), `cm1-geometrie`
  (quiz — propriétés, angles, symétrie). *✅ Validé 2026-07-23.* ✅ **APPROFONDI (s24)** (DEC-0021, pur
  contenu YAML) : `cp-symetrie` (quiz — symétrie CP), `cm1-solides` (quiz — solides), `cm1-perimetre`
  (quiz — périmètre), `cm1-quadrilateres` (associer — classer les quadrilatères). *⏳ À valider (démo).*
  ✅ **ÉTENDU (s25)** (DEC-0022, pur contenu YAML) : `aires-cm1` (quiz — **les aires** CM1, 1er jeu du
  domaine). *⏳ À valider (démo).* *Reste : tracés / symétrie active et **tangram / carte de France**
  (dépendent de la **mécanique de placement 2-D** — sprint dédié s26, §B), plus de figures et de
  propriétés.*
- **Anglais** — ✅ **ENRICHI (s24)** — `anglais-lexique` (+~20 mots), `anglais-associer-cm1`
  (+ appariements) (DEC-0021, *à valider*, sans nouvelle compétence). Reste : consignes de classe.
  **Questionner le monde CM1**. Approfondir Sciences / Histoire / Géo / EMC — ✅ **ENRICHIS (s24)**
  (`cm1-sciences`, `cp-questionner-le-monde`, `cm1-histoire`, `cm1-geographie`, `cp-emc`, `cm1-emc`
  — +~6 items chacun ; DEC-0021, *à valider*, sans nouvelle compétence).

## D. Contenu
- ✅ **LIVRÉ (s21) — dette remboursée** : **derniers contenus en dur externalisés** — événements de la
  **frise** (`content/frise/*.yml`) et pool de **fractions** comparées (`content/fractions/*.yml`).
  `FriseChronologiqueGenerator` et `CompareFractionsGenerator` reçoivent leur liste **par
  constructeur** (nouveaux loaders, même patron que quiz/mots/associer/memory) — DEC-0018.
  Comportement constant (couverture inchangée). **Plus aucun contenu à liste finie codé en Java.**
- ✅ **LIVRÉ (s24) — 1re passe d'enrichissement** (DEC-0021, pur contenu YAML, **sans nouvelle
  compétence**) : `anglais-lexique` (+~20), `anglais-associer-cm1` (+ appariements), `cp-emc` &
  `cm1-emc` (+~6), `cm1-sciences`, `cp-questionner-le-monde`, `cm1-histoire`, `cm1-geographie`
  (+~6 chacun), `cp-le-bon-son` & `cp-premiere-lettre` (+~6). *⏳ À valider (démo).* Enrichissement
  **continu** des viviers existants (quiz, EMC, anglais, le bon son, frise, fractions, puzzle…) par
  l'utilisatrice via les YAML.
- ✅ **LIVRÉ (s25) — contenus additionnels + 2e passe d'enrichissement** (DEC-0022, pur contenu YAML /
  câblage, **sans nouvelle compétence**) : nouveaux jeux sur mécaniques existantes — associer
  `synonymes-cm1`, `chiffre-mot-cp` ; memory `memory-doubles-cp`, `memory-tables-cm1` ; puzzle lettres
  `puzzle-sciences-cm1`, `puzzle-animaux-cp` ; droite graduée câblée `droite-graduee-cinquante-cp`
  (0→50), `droite-graduee-milliers-cm1` (0→1000). Enrichissement de `anglais-lexique`, `cp-emc`,
  `cm1-emc`, `cm1-sciences`, `cp-questionner-le-monde`, `cm1-histoire`, `cm1-geographie`,
  `cp-le-bon-son`, `cp-premiere-lettre`. *⏳ À valider (démo).*

## E. Fonctionnalités produit
- 💤 **ÉVOLUTION POSSIBLE (non envisagée pour l'instant)** — **Persistance** des profils élèves /
  scores / reprise de session (dette **DEC-0003** — aujourd'hui tout est en mémoire) et **tableau de
  bord adulte** / suivi de progression. Piste ouverte, mais **pas au programme actuel** (décision
  utilisatrice, 2026-07-23) ; à ré-évaluer si un besoin de suivi apparaît.
- ✅ **LIVRÉ (s21)** — **Axe de navigation « par matière »** (3e axe, en plus de par niveau / par
  jeu) : pages `/matieres` et `/matiere/{matiere}` via `CatalogueQuery.matieres()` + `byMatiere()`
  (read-model sur le catalogue enrichi, sans nouvel ADR) — DEC-0017. *Done pédagogique ✅ validé 2026-07-23.*
- ✅ **LIVRÉ (s22)** — **Boutons de filtre (niveau / matière) dans la liste « Par jeu »** (backlog
  **IDEE-3**, demandé 2026-07-22, DEC-0019) : l'écran `/jeux` se filtre par CP/CM1 et/ou par matière
  via des boutons **combinables** et **réinitialisables** ; **filtrage serveur par query params**
  (`/jeux?niveau=&matiere=`), pur **read-model / vue**, sans changement de domaine (pas d'ADR).
  *Done pédagogique ✅ validé 2026-07-23.*

## F. Intendance / technique
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
~~**longueurs / durées** (C, Grandeurs et mesures)~~ ✅ (s23) — **s21 + s22 + s23 ✅ validés pédago le
2026-07-23** ·
~~**plus de géométrie / mesures** (masses, contenances, lire l'heure ; symétrie CP, solides / périmètre /
quadrilatères CM1, §C)~~ ✅ **livré (s24) — ⏳ à valider** ·
~~**enrichir les viviers** (anglais, EMC, sciences/histoire/géo/QLM, décodage CP ; via les YAML, §D)~~
✅ **livré (s24) — ⏳ à valider** (100 % contenu YAML, aucune nouvelle mécanique — DEC-0021) ·
~~**déclinaison puzzle PHRASE** (reconstituer une phrase, réutilise `PuzzleMechanic` + `SubmitArrangement`,
§B)~~ ✅ **livré (s25) — ⏳ à valider** ·
~~**autres contenus sur les mécaniques existantes** (droite graduée câblée 0→50 / 0→1000, associer,
memory, puzzle lettres)~~ ✅ **livré (s25) — ⏳ à valider** ·
~~**aires + longueurs / durées au CP**~~ ✅ **livré (s25) — ⏳ à valider** (DEC-0022, aucune nouvelle
mécanique).
⏳ **s24 + s25 en file d'attente : À VALIDER (2 incréments, sous le seuil de gel ; un 3e non validé le
déclencherait)** (règle d'or). Restent, par ordre suggéré :
1. 🔜 **Mécanique de placement 2-D — sprint dédié (s26), EN TÊTE** : **carte de France** (placement sur
   cibles, sans rotation) puis **tangram** (placement + rotation) — nouveau coup type `SubmitPlacement`,
   plateau géométrique, îlot Canvas/SVG, **nouvel ADR** (§B).
2. **Encore plus de géométrie / mesures** (approfondir les aires ; grandeurs et mesures CP) et
   **enrichissement continu des viviers** (via les YAML).
Écartés du programme : **écriture CP / traçage tactile** (hors scope, §C) ; **persistance / tableau de
bord adulte** (évolution possible mais non envisagée pour l'instant, §E).
