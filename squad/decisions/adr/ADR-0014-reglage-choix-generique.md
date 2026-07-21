# ADR-0014 — Réglage « choix » générique + défaut/plancher de questions par jeu

- **Statut** : Accepté
- **Date** : 2026-07-21
- **Auteur** : theo-archi
- **Sprint** : s13

## Contexte
Jusqu'ici, les réglages « adulte » spécifiques étaient au cas par cas : `MathSettingsView`
(fenêtre + opérations) et `SyllabesSettingsView` (nb de syllabes max), chacun avec son bloc de vue,
sa fabrique et son rendu. s13 apporte plusieurs réglages de forme identique — un **choix parmi
quelques options** : sens du rangement (croissant/décroissant), consigne de comparaison (plus
grand/plus petit), et bientôt « inclure les centaines » (décomposition, cf.
`PLAN-decomposition-nombre.md`). Créer un bloc dédié par cas dupliquerait la tuyauterie.

Second besoin : le jeu « Range les nombres » doit démarrer par défaut sur **3 questions** (et non le
défaut global 20), donc un **défaut — et un plancher — de questions propres au jeu**.

## Décision

### 1. Un réglage « choix » générique et réutilisable
- `application/port/in/ChoixSettingsView(paramName, legend, List<ChoixOptionView>, selected)` +
  `ChoixOptionView(value, label)` — records purs (aucun framework, règle ArchUnit 3 respectée).
- `GameSettingsView` porte désormais `List<ChoixSettingsView> choix` (0..n blocs) ; `hasSettings()`
  en tient compte. Les 3 fabriques existantes passent `List.of()`.
- `bootstrap/config/ChoixGameFactory` : fabrique **générique** paramétrée par le schéma de choix +
  une `Function<String, ContentGenerator>` (la valeur choisie → le bon générateur). Rattachable à
  **n'importe quelle mécanique** (portée par `mechanicId`). Utilisée en s13 par « Range les nombres »
  (glisser-déposer) et « Comparaison » (bandit manchot) ; réutilisable pour la décomposition.
- Rendu : un unique bloc `th:each="c : ${settings.choix}"` dans `parametres.html` (`<select
  th:name="${c.paramName}">`).

### 2. Le controller lit des `@RequestParam` NOMMÉS
Contrainte structurante : `GameController.startSession` ne collecte pas un map générique mais des
`@RequestParam` explicites. **Tout nouveau réglage « choix » impose d'ajouter son `@RequestParam`**
(sinon la valeur est silencieusement perdue). En s13, un seul nom partagé : **`sens`** (interprété
par chaque fabrique). La décomposition ajoutera `centaines`.

### 3. Défaut + plancher de questions par jeu
`SessionQuestions` gagne des surcharges `resolve(raw, poolCap, floor, def)` et `view(poolCap, floor,
def)` ; les versions 2-args conservent les valeurs GLOBALES (plancher 5, défaut 20). « Range les
nombres » utilise `defaultQuestions = 3`, `floor = 1` (sans abaisser le plancher, un défaut 3 serait
re-clampé à 5 — les deux vont ensemble).

## Règle exécutable ArchUnit
**Aucune nouvelle règle.** `ChoixSettingsView`/`ChoixOptionView` sont des records d'`application.port.in`
(pas de framework) ; `ChoixGameFactory` vit en `bootstrap.config` ; les générateurs restent en
`domain.content.impl`. Les 5 règles restent vertes (vérifié). Couverture : `CatalogParametersTest`
(sens croissant/décroissant, plus grand/petit, défaut 3, bon-symbole), `SessionQuestionsTest`.

## Conséquences
- **Positif** : ajouter un réglage « choix » à un jeu = un `ChoixSettingsView` + un builder + (si
  nouveau nom) un `@RequestParam`. Zéro nouveau bloc de vue/template.
- **Positif** : défaut de session par jeu (ex. 3) sans casser le défaut global.
- **Coût / vigilance** : le `@RequestParam` nommé reste un point manuel (documenté) ; le template
  générique ne suffit pas seul.

## Alternatives considérées
- **Un bloc de vue par réglage** (comme math/syllabes) : duplication. Écarté au profit du bloc « choix » générique.
- **Map de params générique dans le controller** : plus souple mais moins typé/validé et divergent du
  style Spring MVC existant. Écarté ; on garde les `@RequestParam` nommés + validation en fabrique.
