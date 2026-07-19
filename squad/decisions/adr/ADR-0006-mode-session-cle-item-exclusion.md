# ADR-0006 — Mode session (N questions, sans doublon des réussis) : clé d'item + exclusion + fin de session

- **Statut** : Accepté
- **Date** : 2026-07-19
- **Auteur** : theo-archi
- **Sprint** : s05

## Contexte

La démo s04 a fait émerger un besoin fort (US-s05-1) : jouer une **session d'un nombre choisi de
questions**, sans qu'un item **réussi** ne revienne (un item **raté** peut réapparaître), avec un
**écran de fin** affichant le score. Contraintes structurantes du produit : préserver le
**découplage mécanique/contenu** (la mécanique ne doit jamais interpréter le contenu — ADR-0001,
ADR-0004, règle ArchUnit (4)), rester en Clean Architecture (domaine pur), et ne pas casser les
**5 règles ArchUnit**.

Trois problèmes de conception se posent :

1. **Reconnaître un item déjà vu** sans que la mécanique lise le contenu.
2. **Exclure** les items maîtrisés au tirage suivant, y compris pour des générateurs à **liste
   finie** (qui peuvent s'épuiser) et des générateurs **combinatoires** (espace de tirage large).
3. **Enrichir `GameSession`** d'un état de session (cible, réponses données, clés maîtrisées) —
   la rétro s03 avait alerté sur « l'enflure de `GameSession` ».

## Décision

### 1. Clé de contenu opaque sur `Item`

`Item` gagne une méthode `String key()` **dérivée déterministe** de `reelSymbols` :
`String.join("␟", reelSymbols)` (␟ = U+241F, séparateur d'unité, absent des contenus). La clé est
**opaque pour la mécanique** : celle-ci l'utilise comme identité d'item (égalité de chaîne) sans
jamais l'interpréter. Deux items « équivalents pédagogiquement » (mêmes symboles affichés) ont la
même clé — c'est exactement l'identité voulue pour « ne pas reproposer ».

### 2. Contrat générateur : exclusion + signal d'épuisement

`ContentGenerator` gagne la méthode principale :

```java
Optional<Item> next(RandomSource random, Set<String> excludeKeys);
```

- L'ancien `Item next(RandomSource random)` devient un **`default`** qui délègue avec un ensemble
  vide (`orElseThrow` : un vivier non filtré n'est jamais vide) — **tous les appelants existants et
  tests restent inchangés**.
- Générateurs à **liste finie** (anglais, syllabes, première-lettre, homophones, nature) : filtrent
  leur vivier par `excludeKeys` ; **tout exclu → `Optional.empty()`** = signal d'**épuisement**.
- Générateurs **combinatoires** (calcul mental, comparaison, compléments) : exclusion
  **best-effort** (quelques essais, puis on accepte un item même déjà maîtrisé) — ils ne renvoient
  jamais `empty`. C'est un choix assumé : leur invariant « pas de doublon des réussis » est
  best-effort, pas garanti (leur vivier distinct peut être petit, ex. compléments à 10).
- Un `default OptionalInt distinctPoolSize()` (vide par défaut, renseigné par les générateurs
  finis) permet au **bootstrap** de borner « nombre de questions » ; la mécanique ne l'utilise pas.

`Optional` est le signal d'épuisement le plus propre pour le domaine (pas de `null`, pas
d'exception de flux).

### 3. Modèle de session enrichi (choix délibéré)

`GameSession` porte désormais **`int questionsTarget`, `int answered`, `Set<String> masteredKeys`**
(clés des items **réussis**). C'est un **choix délibéré** (réponse à l'alerte « enflure » de la
rétro s03) : l'état d'une session *est* légitimement composé de ces éléments, et les concentrer dans
l'agrégat `GameSession` (plutôt que d'introduire un service d'état parallèle) garde la mécanique
simple et l'état sérialisable d'un bloc. Un **constructeur secondaire** conserve un démarrage
« léger » (cible par défaut, `answered=0`, `masteredKeys` vide) pour ne pas réécrire les appelants.

### 4. Fin de session dans la mécanique

`BanditManchotMechanic.apply` :
- calcule la justesse ; si **correct** → ajoute `currentItem.key()` à `masteredKeys` ; incrémente
  `answered` ;
- si `answered >= questionsTarget` → **TERMINÉE** (`FINISHED`, pas d'item suivant) ;
- sinon tire l'item suivant en **excluant `masteredKeys`** ; si le générateur renvoie `empty`
  (**vivier épuisé**) → **TERMINÉE**.

`start` : `answered=0`, `masteredKeys` vide, `questionsTarget` lu depuis `GameConfig.settings`
(clé `questionsTarget`, défaut **20**), 1er item tiré. La longueur de session est **résolue et
bornée dans le bootstrap** (`GameFactory`, qui connaît le vivier) puis passée à la mécanique via
`GameConfig.settings` : la mécanique ne connaît que l'entier final.

La mécanique ne référence toujours que `ContentGenerator`/`Item`/`Item.key()` : **jamais un contenu
concret**. La règle ArchUnit (4) reste donc verte.

## Règle exécutable ArchUnit

Aucune **nouvelle** règle n'est requise. La propriété structurante (découplage mécanique/contenu)
reste couverte par la règle (4) existante : la mécanique n'ajoute qu'une dépendance à `Item.key()`
et `ContentGenerator.next(random, keys)` — des abstractions de `domain.content`, jamais
`domain.content.impl`. Les règles (2)/(3) (domaine/application sans framework) restent respectées
(les nouveaux champs sont des types JDK : `int`, `java.util.Set`, `java.util.Optional`,
`java.util.OptionalInt`). Les **5 règles ArchUnit restent vertes**.

```java
// Règle (4) existante, inchangée — suffit à garder le découplage :
noClasses()
    .that().resideInAnyPackage("com.recreo.domain.mechanic..", "com.recreo.domain.mechanics..")
    .should().dependOnClassesThat().resideInAPackage("com.recreo.domain.content.impl..")
    .as("une mecanique ne doit pas dependre d'un generateur de contenu concret");
```

Les propriétés de comportement (pas de doublon des réussis, fin à la cible / à l'épuisement,
bornage 5..50 et ≤ vivier) portent sur du comportement, non sur des dépendances de packages : elles
sont couvertes par des tests unitaires (`BanditManchotSessionTest`, générateurs) et MockMvc.

## Conséquences

- **Positif** : une même mécanique gère un vrai mode session pour toutes les matières, sans
  interpréter le contenu. L'écran de fin réutilise `GameView.finished` (déjà présent).
- **Positif** : « nombre de questions » devient un réglage commun à **tous** les jeux (les 6 jeux
  « simples » passent `hasSettings=true`), borné par le vivier au bootstrap → réglage impossible
  hors-limites.
- **Coût** : signatures enrichies (`GameSession`, `ContentGenerator`, vues `SessionView`/
  `MoveOutcomeView`/`GameView`, `PersistedState`). L'état de session sérialisé porte
  `answered`/`questionsTarget`/`masteredKeys` (round-trip Jackson étendu) — nécessaire pour que
  l'exclusion tienne d'un coup à l'autre.
- **Limite assumée** : sur les jeux **combinatoires**, l'exclusion est best-effort ; un réussi peut
  théoriquement revenir si le vivier distinct est saturé. Documenté ici et borné par la conception
  (bornes de session à 50, cf. US-s05-1).

## Alternatives considérées

- **Clé = `ItemId` (UUID)** : chaque tirage a un UUID neuf → deux items « identiques » auraient des
  clés différentes ; inutilisable pour « ne pas reproposer ». Écarté au profit d'une clé dérivée du
  **contenu affiché**.
- **La mécanique interprète le contenu (meta) pour dédupliquer** : recouple mécanique et contenu,
  casse la règle ArchUnit (4). Écarté.
- **`next` renvoie `null` en cas d'épuisement** : signal implicite, fragile. `Optional` est plus
  propre pour le domaine.
- **État de session dans un service/table à part** : ajoute un agrégat parallèle et une source de
  vérité concurrente pour un état intrinsèquement lié à la session. Écarté : on assume l'enrichement
  de `GameSession` (documenté ici).
- **Exclusion stricte partout (y compris combinatoire)** : imposerait aux générateurs combinatoires
  d'énumérer leur espace pour détecter l'épuisement (coûteux, hors besoin). Best-effort suffit.
