# Plan — Jeu « Décompose le nombre » (centaines / dizaines / unités)

Statut : ✅ **IMPLÉMENTÉ en s14** (cf. DEC-0010). Choix retenu : **deux entrées par niveau** (CP :
dizaines/unités ; CM1 : + centaines) plutôt qu'un toggle « centaines » — l'infra « choix » ayant été
retirée en s13, une entrée par niveau est plus simple. Le reste du document garde sa valeur de cadrage.

> ⚠️ **Mise à jour s13** : l'infra de réglage « choix » (`ChoixSettingsView`/`ChoixGameFactory`),
> d'abord introduite en s13, a été **retirée** (le sens du tri/comparaison est finalement aléatoire,
> pas un réglage — cf. ADR-0014/DEC-0009). Le toggle « inclure les centaines » de ce plan devra donc
> **(ré)introduire** ce petit réglage à N options au moment de l'implémentation (l'historique Git en
> garde une version de référence). Repli simple : livrer d'abord l'entrée CP **sans** toggle via
> `SimpleGameFactory`, et n'ajouter le réglage « centaines » (CM1) que si l'utilisatrice le confirme.

> Besoin exprimé par l'utilisatrice : un exercice où l'enfant **décompose un nombre affiché** et
> **clique le bon chiffre** selon la position demandée. Par défaut on interroge **dizaines et
> unités** ; une case « inclure les centaines » (réglage adulte) étend au chiffre des centaines.

## Objectif pédagogique
**Numération — valeur positionnelle des chiffres.** Comprendre qu'un même chiffre « vaut »
différemment selon sa position (dans `342`, le `4` est le chiffre des dizaines, le `2` celui des
unités, le `3` celui des centaines).
- **CP (cycle 2)** : dizaines + unités (nombres à 2 chiffres, 10..99).
- **CM1 (cycle 3)** : + centaines (nombres à 3 chiffres, 100..999).

C'est une compétence de la famille « Nombres et calculs » qui complète le sens du nombre déjà
couvert (comparer, compléter à 10, ordonner) — cf. `PROPOSITIONS-jeux.md` §1.

## Mécanique — bandit manchot (aucune nouvelle mécanique)
On réutilise **strictement** la mécanique bandit manchot existante (`BanditManchotMechanic`,
htmx tour par tour) : le nombre est affiché, l'enfant clique la bonne réponse parmi des options.
**Aucun îlot JS, aucun nouveau moteur.**

Chaque `Item` = **UNE sous-question** portant sur un nombre affiché :

| Champ `Item` | Exemple |
|---|---|
| `reelSymbols` | `["342"]` (le nombre affiché sur le rouleau) |
| `question` | `"Combien de dizaines ?"` |
| `expected` | `Answer("4")` |
| `distractors` | les autres chiffres du nombre + un voisin — ex. `["2", "3", "5"]` |
| `meta` | `nombre=342`, `position=dizaines`, `matiere`, `niveau` |

Une **session** enchaîne plusieurs sous-questions (unités, dizaines, + centaines si activé) sur des
**nombres tirés aléatoirement** — comme le calcul mental enchaîne des opérations. La `key()` d'un
`Item` étant dérivée de `reelSymbols` **et** de `question` (cf. `Item.keyOf`), deux sous-questions
distinctes sur le même nombre (« combien de dizaines ? » vs « combien d'unités ? ») ont des clés
différentes : l'anti-doublon de session les traite correctement, sans travail supplémentaire.

## Modèle de contenu — `DecompositionGenerator` (combinatoire)
Nouveau générateur **combinatoire** dans `domain/content/impl`, sur le modèle de
`ComparaisonGenerator` / `EgaliteGenerator` (exclusion « best-effort », renvoie toujours un item,
`distinctPoolSize()` vide) :

- **Paramètre** : un booléen `inclureCentaines`.
  - `false` (défaut) → nombre à 2 chiffres, tiré dans **10..99**, positions interrogées ∈ {unités, dizaines}.
  - `true` → nombre à 3 chiffres, tiré dans **100..999**, positions ∈ {unités, dizaines, centaines}.
- **Construction d'un item** :
  1. tirer un nombre `n` dans la plage ;
  2. tirer **aléatoirement une position** parmi l'ensemble autorisé ;
  3. `expected` = le **chiffre** de cette position (voir « Risque valeur positionnelle » ci-dessous) ;
  4. `distractors` = les autres chiffres de `n` + éventuellement **un voisin plausible** (chiffre
     proche non présent) pour garantir un nombre d'options constant même quand `n` a des chiffres
     répétés (ex. `n=44`) ;
  5. `question` = consigne dépendante de la position (`"Combien d'unités ?"` /
     `"Combien de dizaines ?"` / `"Combien de centaines ?"` — formulation à figer avec
     l'utilisatrice, cf. risque).

Le générateur est **piloté par sa `ContentDescriptor`** (matière/niveau/domaine), comme tous les
autres. Le domaine ne connaît ni Spring ni le réglage : `inclureCentaines` lui est passé au
constructeur par la factory du bootstrap.

## Réglage « inclure les centaines »
Un **toggle** (2 options : « non » / « oui », défaut **« non »**, `paramName="centaines"`).

Il **réutilise l'infra de réglage « choix »** introduite en **s13** (`ChoixSettingsView` côté
`application`, `ChoixGameFactory` côté bootstrap) — la même mécanique de réglage à N options
discrètes que le paramètre `"sens"` de s13. Une fois cette infra en place, ajouter ce toggle est du
paramétrage, pas du nouveau code d'infra.

> ⚠️ **Contrainte connue — `@RequestParam` nommés.** `GameController.startSession` ne collecte que
> des paramètres **explicitement nommés** (aujourd'hui `minOperand`, `maxOperand`, `operations`,
> `maxSyllabes`, `questionsTarget` ; en s13 s'ajoute `"sens"`). Un paramètre non déclaré est
> **silencieusement perdu**. Il faudra donc **ajouter `@RequestParam(value = "centaines", …)`** dans
> `startSession` et l'injecter dans la map `parameters` (via `putIfPresent`), **exactement comme
> `"sens"` en s13**. Sans cette ligne, cocher « inclure les centaines » n'aurait aucun effet.
> (Piste d'amélioration transverse, hors périmètre : passer à une collecte générique des params de
> réglage pour ne plus dépendre d'un nom codé en dur — à arbitrer par Théo.)

## Niveau / entrée(s) de catalogue
Deux options :

**Option A — deux entrées** :
- `decomposition-cp` (CP, dizaines + unités, **sans toggle** → `simpleGame`, pas de bouton
  « inclure centaines ») ;
- `decomposition-cm1` (CM1, **avec toggle** centaines, défaut **off** → `ChoixGameFactory`).

**Option B — une seule entrée** avec toggle (défaut off), rangée en CP, l'adulte cochant les
centaines pour un CM1.

**Recommandation : Option A.** Elle respecte la navigation à deux axes (niveau/jeu) et la logique
« un contenu par niveau » déjà en place dans le catalogue ; l'entrée CP reste sans réglage de
contenu (bouton « Paramètres » limité au nombre de questions), et l'entrée CM1 porte le toggle. Cela
évite qu'un réglage « centaines » apparaisse sur une entrée estampillée CP, plus lisible pour
l'utilisatrice qui range son programme par niveau. Coût quasi nul (deux `defs.add(...)` dans
`BeansConfig`).

## Impact
- **Domaine** : 1 nouveau `DecompositionGenerator` (+ un `enum`/constante de position en interne).
  Aucun impact sur les mécaniques (réutilise bandit manchot).
- **Bootstrap** : entrée(s) de catalogue dans `BeansConfig.gameCatalog` — via `ChoixGameFactory`
  (entrée CM1 avec toggle) et/ou `simpleGame` (entrée CP sans toggle). La factory instancie le
  générateur avec le bon `inclureCentaines` selon le paramètre reçu.
- **Web** : ajout d'un `@RequestParam("centaines")` dans `GameController.startSession` (cf.
  contrainte ci-dessus). La vue `parametres` affiche le toggle via le bloc « choix » générique de s13
  (aucun template ad hoc).
- **Tests** : voir lot 4. Coût **marginal faible** une fois l'infra « choix » de s13 posée : c'est
  un générateur de plus dans un pattern déjà rôdé + une entrée de catalogue.

## Découpage en lots
1. **Domaine** — `DecompositionGenerator(inclureCentaines, descriptor)` + tests unitaires
   déterministes (`RandomSource` contrôlé). *(Marc ou Léa)*
2. **Bootstrap** — entrée(s) de catalogue (Option A recommandée) via `ChoixGameFactory` /
   `simpleGame` ; `CatalogParametersTest` couvrant le passage du paramètre `centaines`. *(Léa)*
3. **Web** — `@RequestParam("centaines")` dans `startSession` + `putIfPresent` ; test MVC vérifiant
   que le toggle est bien transmis (non perdu). *(Marc)*
4. **Tests / recette** — voir ci-dessous ; démo à l'utilisatrice pour le **Done pédagogique**.

## Tests (générateur déterministe)
- La **bonne réponse = le chiffre de la position demandée** (ex. `n=342`, `position=dizaines` →
  `expected="4"`).
- Les **distracteurs sont plausibles** : ce sont des chiffres (0..9), distincts de la bonne réponse,
  en nombre constant même si `n` a des chiffres répétés.
- Les **plages** sont respectées : 2 chiffres ⇒ 10..99 ; 3 chiffres ⇒ 100..999 (jamais de zéro non
  significatif en tête).
- **Sans centaines**, la position `centaines` **n'est jamais** interrogée.
- **Web** : cocher « inclure les centaines » produit bien une session à 3 chiffres (test de
  non-régression de la contrainte `@RequestParam`).

## Risques
- **⚠️ Valeur positionnelle — « chiffre » vs « nombre de ».** Distinguer sans ambiguïté le **chiffre
  des dizaines** (`4` dans `342`) du **nombre de dizaines** (`34` dans `342`, qui inclut les
  centaines). Ce sont deux compétences différentes ; les mélanger induirait l'enfant en erreur.
  **Reco : commencer par « chiffre des dizaines / unités / centaines » pour le CP** (plus concret,
  aligné sur la manipulation des chiffres) et **trancher la formulation exacte avec l'utilisatrice**
  avant dev. La consigne (`question`) doit être limpide (« Quel est le chiffre des dizaines ? »
  plutôt que « Combien de dizaines ? » si l'on retient l'interprétation « chiffre »).
- **Dépendance s13** : ce jeu suppose l'infra « choix » livrée. Sans elle, soit on l'attend, soit
  l'entrée CP (sans toggle) peut être livrée seule via `simpleGame`, l'entrée CM1 suivant en s13+.
- **Contrainte `@RequestParam` nommé** : oublier la ligne `"centaines"` = réglage silencieusement
  perdu (aucune erreur, comportement faux) → couvrir par un test web dédié (lot 3).
- **Distracteurs quand chiffres répétés** (`n=44`, `n=100`) : prévoir le voisin plausible pour
  garder un nombre d'options constant — à vérifier explicitement en test.

---
Rite habituel : story + compétence visée (Camille) → éventuel ADR si l'infra « choix » de s13 doit
être étendue (Théo) → dev + tests (Marc/Léa, CI verte = Done technique) → démo + **Done pédagogique
prononcé par l'utilisatrice** → `MATRICE-couverture.md` mise à jour.
