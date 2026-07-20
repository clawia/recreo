# ADR-0009 — Externalisation du contenu quiz dans des fichiers YAML éditables

- **Statut** : Accepté
- **Date** : 2026-07-20
- **Auteur** : theo-archi
- **Sprint** : s09

## Contexte

DEC-0006 a acté l'orientation : le **contenu à liste finie** doit vivre hors du code, dans
des fichiers éditables, pour que l'utilisatrice (autorité pédagogique, non-développeuse)
puisse **enrichir/corriger le contenu sans dev ni recompilation**. s09 en réalise la
**première tranche : la famille quiz-aventure** (7 jeux), la plus nombreuse et la plus
homogène — chaque jeu est déjà une simple **liste de questions** `(question, bonne,
distracteurs)`.

Jusqu'ici, chaque jeu quiz était une **sous-classe Java** de `QuizGenerator` portant sa
liste en dur (`QuestionnerLeMondeCpGenerator`, `SciencesCm1Generator`, …). Contraintes à
respecter : **Clean Architecture** (le `domain` ne doit ni lire de fichier ni dépendre d'un
framework), **5 règles ArchUnit vertes**, fat jar `java -jar` (contenu embarqué dans le jar),
et **aucune régression** de jeu (les quiz doivent jouer exactement comme avant).

## Décision

### 1. Le contenu quiz vit dans `content/quiz/*.yml` (ressources du classpath)
Un fichier YAML **par jeu**, éditable, embarqué dans le jar. Schéma :
```yaml
entryId: histoire-cm1
title: "Histoire CM1 — Quiz-aventure"
description: "..."
gameKey: quiz-aventure          # tuile de regroupement (par jeu)
gameLabel: "Quiz-aventure"
matiere: "Histoire"
niveau: "CM1"
domaine: "Repères historiques"
questions:
  - question: "En quelle année a eu lieu la Révolution française ?"
    bonne: "1789"
    distracteurs: ["1515", "1945"]
```

### 2. Le chargement + la validation vivent dans le `bootstrap` (composition root)
`QuizPackLoader` (dans `com.recreo.bootstrap.config`, comme `ContentPackLoader` pour le
calcul mental) scanne `classpath*:content/quiz/*.yml` (tri par nom de fichier = ordre
déterministe), parse chaque fichier (SnakeYAML) en `QuizContentPack`, et **valide au
chargement** : champs obligatoires présents, ≥ 1 question, chaque question a une bonne
réponse et ≥ 1 distracteur, **distracteurs distincts et différents de la bonne réponse**,
**pas de question en double**. Un fichier incohérent fait **échouer le démarrage** avec un
message explicite (plutôt que de servir un jeu cassé). **Le `domain` ne lit aucun fichier.**

### 3. Le domaine n'expose qu'un `QuizGenerator` **générique** alimenté par la liste
Les **7 sous-classes sont supprimées**. `BeansConfig.gameCatalog` boucle sur les paquets
chargés et construit, pour chacun, une entrée de catalogue via l'helper existant
`quizAventureGame(...)` avec `descriptor -> new QuizGenerator(descriptor, quiz.questions())`.
Mécanique (`QuizAventureMechanic`), moteur de session, vue et réglages sont **inchangés** :
l'externalisation ne touche que la **provenance** des questions.

## Règle exécutable ArchUnit

**Aucune nouvelle règle requise.** La garde reste le **layering** (règle 1) + la **pureté du
domaine** (règle 2) : le chargement de fichiers (SnakeYAML, `PathMatchingResourcePatternResolver`,
`ClassPathResource`) vit dans `bootstrap`, jamais dans `domain`/`application`. La règle 4
(mécaniques/moteur ne dépendent d'aucun générateur concret) et la règle 5 (mécaniques isolées)
restent vertes — `QuizGenerator` est le générateur générique de `domain.content.impl`, inchangé.
**Les 5 règles ArchUnit restent vertes** (vérifié).

La validation du contenu (invariants pédagogiques) et le chargement des 7 fichiers réels sont
couverts par `QuizPackLoaderTest` (bootstrap) ; la mécanique de tirage générique par
`QuizGeneratorTest` (domaine, pool en ligne).

## Conséquences

- **Positif** : l'utilisatrice édite/ajoute un quiz en modifiant un YAML, sans code ni
  recompilation (un **nouveau** fichier `content/quiz/*.yml` devient un jeu au redémarrage,
  grâce au scan classpath). Suppression de 7 sous-classes quasi identiques.
- **Positif** : validation stricte au démarrage → un contenu invalide est détecté tôt, pas en jeu.
- **Positif** : les métadonnées (entryId, gameKey/gameLabel, titre) restent identiques → **aucune
  régression** de navigation ni de gameplay (mêmes tests verts).
- **Coût / limite** : première tranche = **quiz uniquement**. Les autres familles (mots→réponse du
  bandit manchot : le bon son, anglais, syllabes, première lettre, homophones, nature du mot ;
  textes à trous) restent en dur — à externaliser aux sprints suivants (schémas propres à chacune).
- **Coût** : édition YAML sensible à l'indentation (mitigé par en-tête d'aide dans chaque fichier
  + validation au démarrage).

## Alternatives considérées

- **Lister les 7 fichiers en dur dans `BeansConfig`** (comme les 2 packs de calcul mental) :
  simple, mais ajouter un quiz exigerait une ligne de code. Écarté au profit du **scan classpath**
  (`classpath*:content/quiz/*.yml`) → ajouter un fichier suffit.
- **Charger le contenu dans le `domain`** : violerait la pureté du domaine (IO + dépendance de
  chargement). Écarté : le chargement reste dans `bootstrap`, le domaine ne voit qu'une `List`.
- **Tout externaliser d'un coup** (quiz + mots + textes à trous) : trois schémas différents en une
  passe, plus risqué. Écarté au profit d'un **découpage par famille** (quiz d'abord, arbitré avec
  l'utilisatrice).
- **JSON plutôt que YAML** : YAML est déjà utilisé (calcul mental), plus lisible pour de l'édition
  humaine. Conservé pour la cohérence.
