# ADR-0011 — Externalisation du contenu « texte à trous » et « syllabes »

- **Statut** : Accepté
- **Date** : 2026-07-20
- **Auteur** : theo-archi
- **Sprint** : s11

## Contexte
Après les quiz (ADR-0009) et les « mot → réponse » (ADR-0010), s11 externalise les **dernières
familles à liste finie** : les **textes à trous** (conjugaison, accord de l'adjectif, mot manquant)
et les **syllabes** (CP). Objectif inchangé (DEC-0006) : contenu éditable sans dev, Clean
Architecture préservée, ArchUnit 5/5, zéro régression. « Complète l'égalité » reste **combinatoire**
(généré à la volée) → non concerné.

## Décision

### 1. Textes à trous : réutilisation du générateur générique existant
Constat : `PhraseATrouGenerator` (phrase à trou + bonne réponse + distracteurs, `reelSymbols` vide)
est **structurellement identique** à `QuizGenerator`. On **supprime `PhraseATrouGenerator` et ses 3
sous-classes** et on sert les textes à trous finis avec le **même `QuizGenerator` générique**,
rattaché à `TexteATrouMechanic` (la mécanique/vue est portée par l'entrée de catalogue, pas par le
générateur). Le contenu vit dans `content/textes-trous/*.yml` (schéma quiz : `question` = la phrase
avec `___`), chargé par `QuizPackLoader.loadAll("classpath*:content/textes-trous/*.yml")`.

### 2. Syllabes : schéma dédié (options numériques + réglage), générateur refactoré
`SyllabesCpGenerator` ne porte plus de liste en dur : il **reçoit la liste `mot → nombre de
syllabes`** en paramètre (record `Mot`). La logique spécifique reste dans le domaine : distracteurs
= nombres voisins, filtre `maxSyllabes`. Le contenu + les options du réglage vivent dans
`content/syllabes/*.yml` (`items: [{mot, syllabes}]`, `syllabesOptions`, bornes), chargés par
`SyllabesPackLoader` (dans `bootstrap`) et validés au démarrage. `SyllabesGameFactory` reçoit la
liste chargée.

### 3. Chargement/validation en `bootstrap`, domaine pur
Comme ADR-0009/0010 : parsing YAML + validation dans `bootstrap` (`QuizPackLoader`,
`SyllabesPackLoader`) ; le `domaine` ne lit aucun fichier. Fichier incohérent → échec du démarrage
avec message explicite.

## Règle exécutable ArchUnit
**Aucune nouvelle règle.** Layering + pureté du domaine tiennent (IO en bootstrap). `QuizGenerator`
et `SyllabesCpGenerator` restent dans `domain.content.impl`, dépendus d'aucune mécanique (règles 4/5
vertes). **5/5 vertes** (vérifié). Couverture : `TexteATrouMechanicTest` (pool en ligne),
`SyllabesCpGeneratorTest` (fixture en ligne), loaders testés par `QuizPackLoaderTest` /
`Syllabes? ` (via `MotsPackLoaderTest`-style) et le boot d'intégration.

## Conséquences
- **Positif** : tout le contenu à **liste finie** est désormais éditable en YAML (quiz, mots, textes
  à trous, syllabes). Une classe générique de moins (`PhraseATrouGenerator` supprimée) : `QuizGenerator`
  sert **deux mécaniques** (quiz-aventure et texte à trous).
- **Positif** : `egalite-cp` (combinatoire) reste en code, par nature — cohérent avec le calcul mental.
- **Limite** : reste en code, par conception, le contenu **génératif** (calcul mental, tables, égalité).
- Voir aussi ADR-0012 (convention de nommage `niveau-jeu.yml`).

## Alternatives considérées
- **Garder `PhraseATrouGenerator` distinct** : duplication inutile (identique à `QuizGenerator`).
  Écarté au profit de la réutilisation.
- **Externaliser les syllabes avec le schéma « mots »** (ADR-0010) : incompatible (options
  numériques + filtre `maxSyllabes` propres). Écarté au profit d'un schéma dédié.
