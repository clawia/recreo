# ADR-0013 — 1ère mécanique à îlot JS : le glisser-déposer

- **Statut** : Accepté
- **Date** : 2026-07-20
- **Auteur** : theo-archi
- **Sprint** : s12

## Contexte

`recreo` compte 3 mécaniques (bandit manchot, texte à trous, quiz-aventure), toutes
servies par des interactions HTML « pauvres » (clic sur une option). Le sprint s12
introduit la **4e mécanique** et la **1ère qui nécessite une interaction JS riche** :
c'est l'**« îlot JS/Canvas »** réservé au plan (§C.5), jusqu'ici jamais activé. Le jeu
inaugural est **« Range les nombres du plus petit au plus grand »** (Maths CP, 1ère
ligne « glisser-déposer » de `PROPOSITIONS-jeux.md`).

Rupture conceptuelle : le **coup n'est plus un choix scalaire** (« quelle option ? »)
mais un **arrangement ordonné** (« dans quel ordre ? »). Il faut donc représenter et
corriger une réponse qui est une **liste** sans casser l'existant.

Contraintes inchangées : Clean Architecture (domaine pur), **htmx/Thymeleaf + fat jar
unique** (aucun build Node, aucune dépendance front externe), **5 règles ArchUnit
vertes**, mécaniques **isolées entre elles**, **moteur de session partagé** (ADR-0007)
réutilisé sans être complexifié.

## Décision

### 1. Nouveau variant de `Move` : `SubmitArrangement(List<String> order)`

On ajoute au `sealed interface Move` un variant **`SubmitArrangement(List<String>
order)`**, placé dans le paquet **PARTAGÉ `domain.mechanic`** (aux côtés de
`SpinAndPick` et `Skip`). C'est un **choix honnête et testable côté domaine** : le coup
transporte une **vraie `List<String>`**, pas une chaîne bricolée. On refuse de détourner
`SpinAndPick` avec une chaîne jointe : la sémantique serait mensongère et le domaine ne
pourrait pas raisonner sur l'arrangement.

### 2. Le `SessionEngine` reste AGNOSTIQUE

La correction d'un arrangement est une **égalité d'encodage canonique**, pas un tri.
Le moteur compare `Item.encodeArrangement(order)` — **source unique partagée** entre le
générateur et le moteur, séparateur `« , »` — à `Item.expected().value()`, que le
générateur a **déjà encodé trié**. Le moteur **ne trie pas** et **ne lit pas la meta** ;
il applique le **même scoring (+10)** et le **même marquage « maîtrisé »** que
`SpinAndPick`. Aucune connaissance du contenu ne remonte dans le moteur partagé.

### 3. Contenu : `RangeNombresGenerator` COMBINATOIRE

Comme `ComparaisonGenerator`, le contenu est **généré à la volée** — **pas de fichier
YAML**. `RangeNombresGenerator` produit un `Item` où `reelSymbols` = les **nombres
mélangés** (les tuiles à ranger), `expected` = l'**ordre trié encodé** (via
`Item.encodeArrangement`), et `distractors` **vide** (le jeu n'a pas d'options : il a un
arrangement).

### 4. 1er îlot JS : `static/js/glisser-deposer.js`, drag-and-drop HTML5 natif

Le glisser-déposer est réalisé en **drag-and-drop HTML5 NATIF**, **zéro dépendance
externe** (cohérence fat jar / zéro-Node). Le script `static/js/glisser-deposer.js` est
**chargé globalement** dans `jeu.html`, avec une **garde**
`if (!document.querySelector('.glisser-deposer')) return;` et un **re-branchement sur
`htmx:afterSwap`** — **même pattern que `bandit.js`**. **htmx reste la coquille** : il
porte le flux de session et le swap de `#game-area`. Le fragment
`fragments/glisserdeposer.html` **n'itère jamais `game.options`** afin de **ne pas
fuiter la réponse** dans le DOM.

### 5. Contrat POST enrichi, rétro-compatible

Le POST du coup gagne un champ **`arrangement` RÉPÉTÉ** (l'ordre soumis = l'ordre du DOM
des emplacements), et **`chosenAnswer` devient optionnel**. Les **3 mécaniques
existantes sont totalement rétro-compatibles** : elles continuent d'envoyer
`chosenAnswer` et ignorent `arrangement`.

### 6. ArchUnit INCHANGÉ — aucune règle assouplie

On acte qu'**aucune des 5 règles n'a besoin d'être assouplie**. La nouvelle mécanique
`domain.mechanics.glisserdeposer` **reste isolée** : elle délègue au **moteur partagé**,
ne dépend d'**aucune autre mécanique** ni d'un **générateur concret** ; le variant
`SubmitArrangement` vit dans le paquet **partagé** `domain.mechanic`, hors des slices
`mechanics.*`.

## Règle exécutable ArchUnit

**Aucune nouvelle règle**, et **aucune règle assouplie**. L'isolation des mécaniques
(règle 5) reste verte parce que `glisserdeposer` délègue à `SessionEngine`
(paquet parent) et que `SubmitArrangement` est un variant partagé de `Move`, jamais
un type propre à une autre mécanique. Le découplage mécanique/contenu (règle 4) reste
vert : `RangeNombresGenerator` réside dans `domain.content.impl` et n'est dépendu
d'aucune mécanique. La pureté du domaine (règles 2/3) tient : `Move`,
`Item.encodeArrangement` et la `List<String>` sont du domaine sans framework. **5/5
vertes** (vérifié).

```java
// Règle (5) existante, inchangée — verte : glisserdeposer délègue au moteur partagé :
slices().matching("com.recreo.domain.mechanics.(*)..")
    .should().notDependOnEachOther()
    .as("les mecaniques ne doivent pas dependre les unes des autres");
```

Le comportement (correction par égalité d'encodage, scoring +10, marquage « maîtrisé »,
non-fuite de la réponse, rétro-compat des 3 mécaniques) est couvert par des tests
unitaires (`GlisserDeposerMechanicTest`, `RangeNombresGeneratorTest`) et MockMvc
(`GlisserDeposerWebTest`).

## Conséquences

- **Positif** : une **nouvelle famille d'interaction** est ouverte — ranger / associer /
  classer / frises chronologiques à venir réutiliseront `SubmitArrangement` et l'îlot JS.
- **Positif** : le **moteur de session est réutilisé sans être complexifié** (il ignore
  qu'il s'agit d'un arrangement : il compare deux encodages).
- **UX « réessayer » (DEC-0008, demande utilisatrice s12)** : un arrangement **faux** ne fait
  **PAS avancer** — le moteur renvoie la session **inchangée** + un message, donc le fragment
  re-rendu **remet les tuiles à l'état initial** (réserve) et l'enfant retente le **même** puzzle
  (la bonne réponse n'est pas révélée). C'est spécifique au coup `SubmitArrangement` : `SpinAndPick`
  (autres mécaniques) continue d'avancer sur une erreur. Conséquence : pour ce jeu, `answered`
  ne compte que les **réussites** (session = N puzzles résolus).
- **Limite mobile** : le drag-and-drop HTML5 natif est **peu ergonomique au doigt** sur
  mobile → un **fallback tap-to-place** est à prévoir en itération future.
- **Anti-doublon best-effort** : la clé est dérivée des `reelSymbols` **mélangés** (comme
  les autres générateurs combinatoires) ; l'unicité n'est pas garantie à 100 %.
- **Encodage `« , »`** : suppose des **jetons sans virgule** (OK pour des nombres) ; un
  futur jeu d'**ordonnancement de mots** devra revoir l'encodage.

## Alternatives considérées

- **Réutiliser `SpinAndPick` avec une chaîne jointe côté JS** : sémantique **malhonnête**
  et **non testable en domaine** (on perd la nature « liste » du coup). Écarté au profit
  d'un variant `SubmitArrangement` explicite.
- **Une lib JS de drag-and-drop** : **dépendance externe** incompatible avec le fat jar
  zéro-Node. Écarté au profit du **drag-and-drop HTML5 natif**.
- **Faire trier l'arrangement par le moteur** : le moteur deviendrait **conscient du
  contenu**. Écarté ; le moteur **compare des encodages** et reste agnostique (le tri est
  fait par le générateur, dans `expected`).
