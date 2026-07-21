# ADR-0014 — Défaut/plancher de questions par jeu (+ sens aléatoire par question)

- **Statut** : Accepté (amendé en cours de s13 — voir la note)
- **Date** : 2026-07-21
- **Auteur** : theo-archi
- **Sprint** : s13

## ⚠️ Note d'amendement (retour utilisatrice, même sprint)
Une **première version** de cet ADR introduisait un **réglage « choix » générique**
(`ChoixSettingsView`/`ChoixOptionView`/`ChoixGameFactory`) pour rendre *réglable* le sens du tri et
de la comparaison. L'utilisatrice a demandé, avant validation, que le **sens ne soit PAS un
réglage** mais **aléatoire à chaque question**. En conséquence :
- l'infra « choix » (records + `ChoixGameFactory`, champ `GameSettingsView.choix`, `@RequestParam
  "sens"`, bloc de template) a été **retirée** (YAGNI : plus aucun consommateur) ;
- le **sens est tiré aléatoirement par item** dans les générateurs (voir DEC-0009) ;
- **seul subsiste** de cet ADR le **défaut/plancher de questions par jeu**, décrit ci-dessous.
Si un futur jeu a besoin d'un vrai réglage à options (ex. toggle « centaines » de la décomposition),
l'infra « choix » sera réintroduite à ce moment-là (elle reste dans l'historique Git).

## Contexte
« Range les nombres » doit démarrer par défaut sur **3 questions** (une courte session suffit), et
non le défaut global 20. Il faut donc un **défaut — et un plancher — de questions propres à un jeu**,
sans changer le comportement global des autres jeux.

## Décision
`SessionQuestions` expose des surcharges `resolve(raw, poolCap, floor, def)` et `view(poolCap,
floor, def)` ; les versions 2-args conservent les valeurs GLOBALES (plancher `MIN=5`, défaut
`DEFAULT=20`). `SimpleGameFactory` gagne un constructeur optionnel `(…, int defaultQuestions, int
questionsFloor)` (le constructeur historique délègue avec les valeurs globales). « Range les
nombres » l'utilise avec `defaultQuestions = 3`, `questionsFloor = 1` — car un défaut 3 avec le
plancher global 5 serait re-clampé à 5 : défaut et plancher par jeu vont ensemble.

## Règle exécutable ArchUnit
Aucune nouvelle règle. Changement purement en `bootstrap.config`. Les 5 règles restent vertes
(vérifié). Couverture : `SessionQuestionsTest` (défauts globaux inchangés) + `CatalogParametersTest`
(« Range les nombres » : défaut 3, plancher 1).

## Conséquences
- **Positif** : défaut de session par jeu sans casser le défaut global (5/20).
- **Positif (YAGNI)** : pas d'infra de réglage spéculative — le sens est géré côté contenu (aléatoire).
- **Coût** : un constructeur de plus sur `SimpleGameFactory` (surcharge, rétro-compatible).

## Alternatives considérées
- **Réglage « choix » générique** (v1 de cet ADR) : retiré suite au retour utilisatrice (sens =
  aléatoire, pas un réglage). Réintroductible si un vrai toggle apparaît (décomposition).
- **Défaut global abaissé à 3** : casserait les autres jeux. Écarté au profit d'un défaut par jeu.
