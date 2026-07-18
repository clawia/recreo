# ADR-0005 — Catalogue enrichi (métadonnées) + paramètres de session

- **Statut** : Accepté
- **Date** : 2026-07-18
- **Auteur** : theo-archi
- **Sprint** : s03

## Contexte

Deux besoins du sprint s03 (US-s03-1, US-s03-2) touchent la frontière entre le contenu
pédagogique et le front :

1. **Navigation à deux axes** — « par niveau puis jeu » et « par jeu puis niveau ». Le catalogue
   ne portait qu'`entryId` / `title` / `description` / `mechanicId` : impossible de regrouper les
   entrées par niveau ou par jeu sans que le front ne devine ces regroupements à partir des
   titres. Il faut exposer des **métadonnées de regroupement** stables, sans coupler le front au
   contenu (principe directeur du produit, CLAUDE.md / ADR-0001).

2. **Paramétrage des jeux** — régler la difficulté avant de jouer (fenêtre de nombres + opérations
   en maths ; nombre de syllabes max en français). La **décision produit est verrouillée** : les
   réglages sont posés par un **adulte**, appliqués à la **session courante** et **non persistés**
   (aucun stockage de préférences à ajouter). Contraintes : Clean Architecture (domaine pur, ports
   sans framework, wiring en `bootstrap`), `BanditManchotMechanic` inchangée, htmx/Thymeleaf, fat
   jar.

## Décision

### 1. Catalogue enrichi

Chaque entrée de catalogue porte désormais : `entryId`, `matiere`, `niveau`, `gameKey`
(identifiant du **jeu** indépendant du niveau, ex. `calcul-mental`, `syllabes`), `gameLabel`,
`title`, `description`. Le port de sortie `GameCatalog` (`CatalogEntry`) et le port d'entrée
(`CatalogEntryView`) transportent ces champs. Le use case de lecture `CatalogueQuery` offre les
accès de regroupement : `niveaux()` (niveaux distincts), `games()` (`gameKey` distincts + libellé),
`byNiveau(niveau)`, `byGameKey(gameKey)`. Le front ne fait qu'itérer sur ces vues : il n'interprète
jamais la structure du contenu.

Les entrées de calcul mental portent leurs métadonnées et leurs **bornes de réglage** dans les YAML
(`content/*.yml`) ; l'entrée `francais-cp` (jeu `syllabes`) est câblée en `bootstrap`. Trois
entrées : `calcul-mental-cp` (Maths, CP), `calcul-mental-cm1` (Maths, CM1) — même `gameKey`
`calcul-mental` — et `francais-cp` (Français, CP, `syllabes`).

### 2. Paramètres de session → générateurs

`StartSessionCommand` transporte des **paramètres optionnels** (`Map<String,String>`). Au démarrage,
le catalogue (via une `GameFactory` par entrée) construit le `ContentGenerator` en appliquant ces
paramètres **sur la config de base** de l'entrée. **Aucun paramètre ⇒ comportement de s02.**

- **Maths (`calcul-mental`)** : `minOperand`, `maxOperand`, `operations` (sous-ensemble
  d'ADDITION/SOUSTRACTION/MULTIPLICATION) construisent une `DifficultyConfig`. Les valeurs sont
  **bornées** par une `MathBounds` par entrée (plancher/plafond d'opérande + opérations autorisées)
  : un réglage hors-programme est impossible (CP : pas de multiplication, opérande ≤ 20).
- **Français (`syllabes`)** : `maxSyllabes` filtre la liste de mots de `SyllabesCpGenerator` (mots
  de ≤ `maxSyllabes` syllabes, garanti non vide). Absence de paramètre = pas de filtre.

Répartition par couche : le **domaine** expose des générateurs configurables (`SyllabesCpGenerator`
gagne un `maxSyllabes` optionnel ; `CalculMentalGenerator` était déjà piloté par `DifficultyConfig`) ;
l'**application** reçoit les paramètres via la commande et les mémorise dans l'état de la session
(`GameSession.parameters`) pour reconstruire le même générateur à chaque coup ; le **bootstrap**
fait le wiring (`MathGameFactory`, `SyllabesGameFactory`). Aucun Spring/JPA dans domain/application.
La `BanditManchotMechanic` est **inchangée** : un constructeur secondaire (historique) de
`GameSession` conserve sa signature d'appel, les paramètres étant attachés par l'application.

**Non-persistance** : aucun stockage de préférences n'est ajouté. Les paramètres voyagent dans la
commande et, pour la seule durée de vie de la session courante, dans l'état de session déjà
persisté (colonne JSON existante) — ce qui est nécessaire à la cohérence des manches successives,
et non un stockage de réglages durable.

## Règle exécutable ArchUnit

Aucune **nouvelle** règle n'est requise : la propriété structurante (le découplage
mécanique/contenu) reste couverte par la règle (4) existante de
`bootstrap/src/test/java/com/recreo/bootstrap/ArchitectureTest.java`, et les règles (2)/(3)
garantissent que domain/application restent sans framework — ce que respectent les nouveaux types
(`GameFactory`, ports enrichis, `GameSession.parameters` en `java.util.Map`). Les **5 règles
ArchUnit restent vertes**. Les propriétés propres à s03 (application des paramètres, bornes
hors-programme impossibles, navigation à deux axes) portent sur du comportement, non sur des
dépendances de packages : elles sont couvertes par des tests unitaires (`CatalogParametersTest`,
`StartSessionParametersTest`, générateurs) et MockMvc (`TwoAxisNavigationWebTest`,
`SelectionNavigationWebTest`).

```java
// Règle (4) existante, inchangée — suffit à garder le découplage :
noClasses()
    .that().resideInAnyPackage("com.recreo.domain.mechanic..", "com.recreo.domain.mechanics..")
    .should().dependOnClassesThat().resideInAPackage("com.recreo.domain.content.impl..")
    .as("une mecanique ne doit pas dependre d'un generateur de contenu concret");
```

## Conséquences

- **Positif** : les deux axes de navigation sont alimentés par des métadonnées stables ; le front
  reste agnostique du contenu. Le paramétrage réutilise l'abstraction `ContentGenerator` sans
  toucher à la mécanique.
- **Positif** : les bornes par entrée (`MathBounds`, options de syllabes) rendent un réglage
  hors-programme impossible, indépendamment du formulaire (le front ne fait pas foi).
- **Coût** : signatures enrichies (`CatalogEntry`, `CatalogEntryView`, `CatalogueQuery`,
  `GameCatalog`, `StartSessionCommand`) et propagation dans `SessionService`, la vue web et les
  templates. `GameSession` porte un champ `parameters` (round-trip JSON étendu) ; un constructeur
  secondaire préserve la mécanique inchangée.
- **Dette évitée** : pas de stockage de préférences prématuré ; les réglages restent liés à la
  session courante.

## Alternatives considérées

- **Ajouter les paramètres comme composant du constructeur canonique de `GameSession`** :
  aurait forcé une modification de `BanditManchotMechanic` (appel constructeur). Écarté au profit
  d'un constructeur secondaire préservant la signature historique.
- **Persister les réglages dans une table dédiée** : contredit la décision produit (non
  persistés) et ajoute un schéma inutile en Phase 0. Écarté.
- **Router la navigation côté front à partir des titres** : recouple la vue au contenu, non
  testable proprement, ne passe pas à l'échelle des matières. Écarté au profit de métadonnées
  explicites (`gameKey`, `niveau`, `matiere`).
- **Interpréter les paramètres dans le domaine** (parsing des clés `minOperand`…) : ferait fuiter
  une préoccupation de présentation/wiring dans le domaine. Le domaine expose des générateurs
  typés ; le bootstrap traduit les `Map<String,String>` en configs.
