# ADR-0007 — Support multi-mécaniques : moteur de session partagé + sélection de vue

- **Statut** : Accepté
- **Date** : 2026-07-19
- **Auteur** : theo-archi
- **Sprint** : s06

## Contexte

Jusqu'en s05, `recreo` n'avait qu'une mécanique (`BanditManchotMechanic`). Le sprint
s06 introduit la **2e mécanique** — « Texte à trous » (`TexteATrouMechanic`) — et doit
prouver que l'architecture supporte **plusieurs mécaniques proprement**. Trois
propriétés structurantes sont en jeu :

1. **Logique de session partagée sans coupler les mécaniques entre elles.** Toute la
   logique du mode session (démarrage, score +10, incrément `answered`, ajout de la
   clé aux `masteredKeys` si correct, fin à `questionsTarget` ou à l'épuisement,
   tirage de l'item suivant en excluant les réussis — cf. ADR-0006) vivait dans
   `BanditManchotMechanic`. La dupliquer dans chaque mécanique serait une dette ; la
   faire porter par une mécanique dont l'autre dépendrait **casserait la règle ArchUnit
   5** (`mechanics.(*) notDependOnEachOther`).
2. **Identité d'item valable pour tout type de contenu.** `Item.key()` (ADR-0006)
   dérivait de `reelSymbols` seuls. Or un item « texte à trous » n'utilise **pas** de
   rouleaux (`reelSymbols` vide) : tous ses items auraient la clé vide → collision →
   anti-doublon cassé.
3. **Sélection de la vue selon la mécanique.** Le front insérait en dur le fragment
   `banditmanchot`. Il faut présenter chaque mécanique différemment (machine à sous
   vs phrase à compléter) sans que le front ne **devine** la mécanique.

Contraintes : Clean Architecture (domaine pur), htmx/Thymeleaf, fat jar, **5 règles
ArchUnit vertes** (surtout la 5).

## Décision

### 1. Moteur de session partagé dans le paquet parent `domain.mechanic`

La logique commune est extraite dans **`com.recreo.domain.mechanic.SessionEngine`**,
placée dans le paquet **PARENT** partagé — pas dans `domain.mechanics.*`. Chaque
mécanique concrète (`domain.mechanics.banditmanchot`, `domain.mechanics.texteatrou`)
**délègue** son `start`/`apply` à un `SessionEngine` construit avec sa propre
`MechanicId`. Une mécanique ne porte plus que **son identité et sa vue** ; **zéro
duplication** de la logique de session.

Comme `SessionEngine` réside dans `domain.mechanic` (et non dans une slice
`domain.mechanics.<x>`), les deux mécaniques en dépendent **sans dépendre l'une de
l'autre** : la règle ArchUnit 5 (`slices matching "com.recreo.domain.mechanics.(*).."
.notDependOnEachOther`) reste **verte**. `SessionEngine` ne référence que
`ContentGenerator`/`Item` — jamais un générateur concret — donc la règle 4 reste verte.
La construction des **options** (bonne réponse + distracteurs mélangés) est identique
pour les deux mécaniques : c'est une donnée commune ; seule leur **présentation** diffère,
côté vue.

### 2. Clé d'item dérivée de `reelSymbols` **et** `question`

`Item.key()` devient `String.join("␟", reelSymbols) + "␝" + question` (␝ = U+241D,
absent des contenus), via une méthode statique `Item.keyOf(reelSymbols, question)` —
**source unique** de la formule. La clé reste **opaque** pour la mécanique. Les items
« texte à trous » (rouleaux vides) sont désormais distingués par leur **consigne** (la
phrase à trou, unique par item) ; les clés bandit manchot restent distinctes (leurs
`reelSymbols` diffèrent). Les générateurs à **liste finie** (anglais, syllabes,
première-lettre, homophones, nature, phrase-à-trou) filtrent leur vivier via
`Item.keyOf(...)` — cohérent avec `key()`.

### 3. Enregistrement de plusieurs mécaniques

Le **bootstrap** enregistre `BanditManchotMechanic` (`bandit-manchot`) **et**
`TexteATrouMechanic` (`texte-a-trou`) dans l'`InMemoryGameCatalog` ;
`GameCatalog.mechanicFor(mechanicId)` les résout. Chaque entrée de catalogue référence
**sa** mécanique (`CatalogEntryDef.mechanicId`). Les 4 jeux « texte à trous »
(`conjugaison-cm1`, `accords-cm1`, `mot-manquant-cp`, `egalite-cp`) réutilisent la même
`SimpleGameFactory` (réglage commun « nombre de questions » borné par le vivier), mais
rattachée à `TexteATrouMechanic.ID`.

### 4. Sélection de la vue portée par la mécanique

`GameMechanic` gagne **`String view()`** (`"banditmanchot"` / `"texteatrou"`). Cet
identifiant remonte de la mécanique → `SessionView`/`MoveOutcomeView` (`mechanicView`)
→ `GameView.mechanicView`. La page `jeu.html` insère **dynamiquement**
`~{fragments/__${game.mechanicView}__ :: game}` ; au coup (fragment htmx), le
`GameController` renvoie `"fragments/" + mechanicView + " :: game"`. Le `mechanicView`
**vient donc de la session/mécanique, jamais deviné** par le front. Les éléments communs
aux deux vues (scoreboard, emplacement de message réservé, écran de fin) sont
**mutualisés** dans `fragments/commun.html` (inclus via `th:replace`).

Le contenu « texte à trous » réutilise `Item` : `question` = la phrase avec le marqueur
`___`, `reelSymbols` = liste **vide**, `expected` = le bon mot, `distractors` = les
autres mots. La mécanique et le moteur restent **agnostiques** du contenu.

## Règle exécutable ArchUnit

Aucune **nouvelle** règle n'est requise : la propriété clé (mécaniques isolées entre
elles) est **déjà** couverte par la règle 5 existante, qui reste verte grâce au choix de
placer `SessionEngine` dans le paquet parent `domain.mechanic` (hors des slices
`mechanics.*`). La règle 4 reste verte (`SessionEngine` ne dépend que d'abstractions de
`domain.content`). Les règles 2/3 (domaine/application sans framework) restent
respectées (`view()` est un `String`, `mechanicView` transite en `String`). Les **5
règles ArchUnit restent vertes**.

```java
// Règle (5) existante, inchangée — verte grâce au moteur dans le paquet parent :
slices().matching("com.recreo.domain.mechanics.(*)..")
    .should().notDependOnEachOther()
    .as("les mecaniques ne doivent pas dependre les unes des autres");
```

Les propriétés de comportement (bonne mécanique/vue servie selon l'entrée, session sans
doublon sur la nouvelle mécanique, présentation phrase-à-trou) portent sur du
comportement : couvertes par des tests unitaires (`TexteATrouMechanicTest`,
`PhraseATrouGeneratorTest`, `EgaliteGeneratorTest`, `MultiMechanicTest`) et MockMvc
(`TexteATrouWebTest`).

## Conséquences

- **Positif** : ajouter une mécanique = une classe qui délègue au moteur partagé
  (identité + vue) + un fragment de vue ; aucune duplication de la logique de session,
  aucun couplage entre mécaniques.
- **Positif** : la clé d'item est désormais valable pour tout type de contenu ;
  l'anti-doublon (ADR-0006) fonctionne aussi en « texte à trous ».
- **Positif** : le front sélectionne la vue à partir d'une donnée portée par la
  mécanique (`mechanicView`), sans deviner ; les éléments communs sont mutualisés.
- **Coût** : signatures enrichies (`GameMechanic.view()`, `mechanicView` sur
  `SessionView`/`MoveOutcomeView`/`GameView`) ; ré-alignement des filtres d'exclusion
  des générateurs finis sur `Item.keyOf(...)`.
- **Dette évitée** : pas de logique de session dupliquée par mécanique ; pas de
  routage de vue par le front à partir du contenu.

## Alternatives considérées

- **Logique commune dans une mécanique de base dont les autres héritent/dépendent** :
  ferait dépendre une mécanique d'une autre (ou d'une base dans `mechanics.*`) →
  fragilise la règle 5. Écarté au profit d'un moteur dans le paquet **parent**.
- **Clé d'item = `reelSymbols` seuls** (statu quo ADR-0006) : collision pour « texte à
  trous » (rouleaux vides). Écarté ; on dérive la clé de `reelSymbols` **et**
  `question`.
- **Front qui route la vue selon `matiere`/`gameKey`** : recouple la vue au contenu,
  non testable proprement. Écarté au profit d'un `mechanicView` porté par la mécanique.
- **Mécanique « texte à trous » avec un modèle de contenu dédié** : dupliquerait
  `Item`. Écarté : `Item` suffit (`question` = phrase, `reelSymbols` vide).
