# ADR-0004 — Tokens de rouleaux génériques (le contenu fournit ce qui s'affiche)

- **Statut** : Accepté
- **Date** : 2026-07-18
- **Auteur** : theo-archi
- **Sprint** : s02

## Contexte

L'affichage du bandit manchot construisait les rouleaux en **parsant le prompt côté front** :
le fragment `fragments/banditmanchot.html` découpait la chaîne `« a op b = ? »` via
`#strings.substringBefore(...,'=')` puis `#strings.arraySplit(...,' ')` pour fabriquer un rouleau
par symbole, et affichait un « = ? » codé en dur.

Cette logique est **couplée au calcul mental** : le front connaît la structure d'un contenu
mathématique. Elle est inutilisable pour d'autres matières (US-s02-2, français CP : « Combien de
syllabes ? » avec un seul rouleau affichant un mot). Cela violait l'esprit du découplage
mécanique/contenu (ADR-0001/CLAUDE.md) : la structure d'un contenu ne doit pas fuir dans la vue.

Contraintes : Clean Architecture (le domaine reste pur, les vues restent en types primitifs prêts
à afficher — jamais de records du domaine au-delà de l'application), htmx + Thymeleaf, fat jar.

## Décision

Le **contenu** fournit désormais **explicitement** ce qui s'affiche, via deux champs sur
`domain/content/Item` :

- `List<String> reelSymbols` : les symboles à afficher, **un rouleau par symbole**.
- `String question` : la consigne/texte affiché (ex. « = ? », « Combien de syllabes ? »).

Le champ `prompt` (chaîne à parser) est **supprimé**. `CalculMentalGenerator` remplit
`reelSymbols = [operandeA, opérateur, operandeB]` et `question = "= ?"` ; `SyllabesCpGenerator`
(français CP) remplit `reelSymbols = [MOT]` et `question = "Combien de syllabes ?"`.

Ces deux champs sont propagés sans interprétation : `SessionView`/`MoveOutcomeView` (application) →
`SessionService` → `GameView`/`WebViewMapper` (adapter-web) → fragment. Le fragment **itère sur
`game.reelSymbols`** (chaque `.reel` porte `data-value`) et **affiche `game.question`** ; il ne
parse plus rien. `js/bandit.js` est inchangé (il lit toujours `data-value`).

Les **rouleaux d'affichage** (`reelSymbols`, fournis par le contenu) restent distincts des
**rouleaux de réponses** (`Reel`/options, construits par la mécanique à partir de
`expected`/`distractors`). La mécanique `BanditManchotMechanic` n'est pas modifiée.

## Règle exécutable ArchUnit

Aucune **nouvelle** règle n'est requise : le découplage mécanique/contenu est déjà matérialisé par
la règle (4) existante de `bootstrap/src/test/java/com/recreo/bootstrap/ArchitectureTest.java`, qui
interdit aux mécaniques de dépendre d'un générateur de contenu concret. L'enabler renforce ce
découplage côté **vue** (le front n'interprète plus la structure du contenu), mais cette propriété
n'est pas exprimable en ArchUnit (elle porte sur un template Thymeleaf). Elle est donc couverte par
un test web MockMvc (`SelectionNavigationWebTest`) vérifiant que la consigne du contenu
(`game.question`) transite jusqu'à la page rendue. Les **5 règles ArchUnit restent vertes**.

```java
// Règle (4) existante, inchangée — suffit à garder le découplage :
noClasses()
    .that().resideInAnyPackage("com.recreo.domain.mechanic..", "com.recreo.domain.mechanics..")
    .should().dependOnClassesThat().resideInAPackage("com.recreo.domain.content.impl..")
    .as("une mecanique ne doit pas dependre d'un generateur de contenu concret");
```

## Conséquences

- **Positif** : le front est agnostique de la matière ; une nouvelle matière (français CP) se joue
  sans toucher au fragment ni à la mécanique. La structure d'un contenu ne fuit plus dans la vue.
- **Positif** : le calcul mental CP/CM1 fonctionne à l'identique (rouleaux = calcul, consigne
  « = ? »).
- **Coût** : signature de `Item` modifiée → propagation dans `SessionView`, `MoveOutcomeView`,
  `SessionService`, `GameView`, `WebViewMapper` et le fragment (change mécanique mais localisé).
  L'état persisté (`PersistedState`/JSON) porte les nouveaux champs — round-trip Jackson des records
  inchangé.
- **Dette évitée** : suppression du parsing de chaîne fragile côté template.
- **À faire** : le **smoke test navigateur (Playwright)** du flux sélection → jeu n'a pas pu être
  exécuté (pas de Node/navigateur dans l'environnement) ; le flux est couvert par MockMvc
  (`SelectionNavigationWebTest`, `BanditManchotEndToEndTest`). Le smoke Playwright reste à faire.

## Alternatives considérées

- **Garder `prompt` et ajouter seulement `reelSymbols`/`question`** : redondant, laisse une chaîne
  ambiguë à parser potentiellement réutilisée ; écarté au profit d'un remplacement propre.
- **Exposer un modèle d'affichage structuré depuis la mécanique** (la mécanique décide des tokens) :
  recouplerait la mécanique au contenu ; c'est précisément ce que l'on veut éviter. Le contenu est
  la bonne source des tokens d'affichage.
- **Laisser le front router selon la matière** : reporte le couplage dans la vue, non testable en
  ArchUnit, ne passe pas à l'échelle des matières. Écarté.
