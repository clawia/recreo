# DEC-0017 — Mécanique « Puzzle » (7e) + jokers paramétrables + navigation par matière

- **Type** : Produit / Nouvelle mécanique + réglage transverse + axe de navigation
- **Date** : 2026-07-22
- **Auteur** : Camille (PO), en appui de l'ADR-0017 (Théo).
- **Sprint** : s21
- **Compétence programme visée** : Français CP — écriture / lecture (reconstituer un mot à partir de
  ses lettres, avec indice image) ; Français CM1 — vocabulaire / orthographe lexicale (reconstituer
  un mot à partir d'une définition) ; transverse (droit à l'erreur — capital de cœurs défini dans le YAML du jeu) ; sans objet pour l'axe « par matière » (fonctionnalité de navigation, pas de contenu).
- **Statut de validation** : **VALIDÉ (utilisatrice, 2026-07-23)** — Done pédagogique prononcé par
  l'utilisatrice après démo (validation groupée s21 + s22 + s23).
- **⚠️ PARTIELLEMENT ANNULÉE (2026-07-22)** : le **réglage adulte « nombre de cœurs »** initialement
  envisagé (écran de paramètres + `JokersSettingsView` + paramètre POST `jokers`) a été **abandonné
  sur demande de l'utilisatrice** — les jokers se paramètrent **uniquement via l'en-tête YAML**
  `jokers`. Les décisions **1 (mécanique Puzzle)**, **3 (navigation par matière)** et le principe
  **« jokers pilotés par le YAML » (2, partie retenue)** restent **en vigueur**. Voir la §2 ci-dessous
  (déjà rédigée dans cette version définitive) et la ROADMAP §A3.

## Contexte / besoin
Trois évolutions produit avancent ensemble en s21 (gros incrément parallèle) :
1. Ouvrir une **7e mécanique « Puzzle / assemblage »** (roadmap §B) — première façon pour l'enfant de
   **reconstituer** un objet (ici un mot) plutôt que de choisir, ordonner des nombres ou apparier.
2. Rendre le **capital de jokers défini dans le YAML du jeu** — la réserve laissée ouverte par DEC-0013 et
   déjà amorcée en s19 (`SessionEngine(mechanicId, defaultJokers)`, memory à 6). On veut désormais un
   capital **piloté uniquement par le contenu (YAML)** (roadmap A3).
3. Ajouter un **3e axe de navigation « par matière »** (roadmap §E) — aujourd'hui on parcourt par
   niveau ou par jeu ; avec un catalogue qui couvre désormais de nombreuses matières, entrer « par
   matière » devient naturel.

## Décisions produit

### 1. Mécanique « Puzzle / assemblage » (7e) — 1re déclinaison « reconstituer un mot »
L'enfant **remet dans l'ordre des lettres mélangées** pour reconstituer un mot, guidé par un
**indice** (image ou définition). Côté domaine c'est un **ordonnancement** : le puzzle **réutilise le
coup `SubmitArrangement`** du glisser-déposer (comme le memory a réutilisé `SubmitAssociations` —
cf. ADR-0017), sans nouveau coup. Interaction propre (assemblage de lettres + indice, îlot JS
`puzzle.js`). 2 jeux **externalisés en YAML dès le départ** (loader dédié sur le patron
`QuizPackLoader`) :
- **`content/puzzle/cp-mots-images.yml`** — reconstituer un mot **court** avec **indice image**
  (Français CP écriture / lecture) ;
- **`content/puzzle/cm1-mots.yml`** — reconstituer un mot avec **indice définition** (vocabulaire /
  orthographe lexicale CM1).
Générateur `MotPuzzleGenerator` (lettres à replacer dans l'ordre + indice), alimenté par le YAML.

### 2. Jokers paramétrables UNIQUEMENT via le YAML
Le capital de cœurs devient **une donnée de contenu** : champ **`jokers` optionnel dans l'en-tête
YAML** du jeu, lu par `SessionEngine` via `settings["jokers"]`. Le **capital 6 du memory** n'est plus
une constante Java (`MemoryMechanic.JOKERS`) mais provient désormais **du YAML**. Défaut inchangé
(3 cœurs — DEC-0013) quand le YAML ne précise rien. **Décision utilisatrice (2026-07-22) : le nombre
de cœurs se règle UNIQUEMENT dans le YAML** — il n'y a **pas** de réglage adulte à la volée dans
l'écran de paramètres (l'infra `JokersSettingsView` / paramètre POST envisagée a été retirée).

### 3. Navigation « par matière » (3e axe)
Nouvelles pages de lecture **`/matieres`** (liste des matières couvertes) et
**`/matiere/{matiere}`** (jeux d'une matière, tous niveaux), reposant sur le catalogue enrichi
(ADR-0005) via `CatalogueQuery.matieres()` et `byMatiere()`. Les axes existants (par niveau, par jeu)
sont conservés ; « par matière » s'ajoute sans les remplacer. Pur **read-model**, aucun changement de
domaine (cf. note ADR-0017).

## Justification pédagogique
- **Puzzle « reconstituer un mot »** : mobilise l'ordre des lettres et l'orthographe active (au CP,
  appui image pour relier le mot à son sens ; au CM1, appui définition pour le vocabulaire) — une
  entrée concrète vers l'**écriture CP**, trou repéré de la matrice, sans exiger le traçage tactile.
- **Cœurs paramétrables (YAML)** : le droit à l'erreur s'adapte à l'enfant et à la mécanique ; un adulte peut
  élargir (enfant plus jeune / mécanique plus exigeante) ou resserrer le capital.
- **Navigation par matière** : reflète l'ambition « toutes les matières » du produit ; entrer par
  « Français », « Maths »… devient aussi direct qu'entrer par niveau ou par jeu.

## Impact
- **Domaine** : **aucun nouveau coup** — puzzle réutilise `SubmitArrangement` (ADR-0017) ; paquet
  isolé `mechanics/puzzle`. Jokers : lecture de `settings["jokers"]` par `SessionEngine` (extension
  du service existant). Navigation par matière : aucun impact domaine.
- **Bootstrap** : loader YAML puzzle + 2 packs (`cp-mots-images`, `cm1-mots`) + 2 entrées catalogue ;
  le capital memory migre de la constante Java vers l'en-tête YAML.
- **Web** : îlot JS `puzzle.js` (assemblage lettres + indice) ; pages `/matieres` et `/matiere/{matiere}`.
- **Matrice** : 2 nouvelles compétences `en cours (s21)` (Français CP reconstituer un mot ; Français
  CM1 reconstituer un mot). Cœurs paramétrables (YAML) et navigation par matière = transverse / fonctionnalité,
  sans nouvelle ligne de couverture.
- **ADR** : **ADR-0017** (puzzle). Jokers-en-YAML et navigation par matière **sans ADR** (paramètre
  de settings ; read-model — cf. note ADR-0017).

## À confirmer en démo
- Justesse et niveau des mots des 2 puzzles (longueur CP courte + indice image lisible ; définitions
  CM1 claires et non ambiguës) — contenus à relire/enrichir par l'utilisatrice dans les YAML.
- Ergonomie de l'assemblage des lettres (souris / tactile) et lisibilité de l'indice.
- Capital de cœurs piloté par le YAML : `jokers` de l'en-tête appliqué à la session, défaut 3
  préservé quand le champ est absent ; capital memory (6) correct depuis le YAML. *(Le réglage adulte
  à la volée a été retiré — cf. note « partiellement annulée » en tête.)*
- Navigation « par matière » : liste des matières complète et cohérente ; retour vers l'axe d'origine.
