# ADR-0016 — Coup générique `SubmitAssociations` + mécaniques « Associer/Classer » & « Memory »

- **Statut** : Accepté
- **Date** : 2026-07-22
- **Auteur** : theo-archi
- **Sprint** : s17

## Contexte

`recreo` compte 4 mécaniques (bandit manchot, texte à trous, quiz-aventure, glisser-déposer).
Le glisser-déposer a ouvert la famille des interactions à îlot JS avec le coup
`SubmitArrangement(List<String>)` — un **ordonnancement** (« dans quel ordre ? »). Les besoins
suivants (proposés depuis s14/s15 comme « le prochain vrai type de coup ») vont **au-delà de
l'ordonnancement** :

- **Associer / Classer** : apparier des paires (mot FR↔EN) ou catégoriser des items (vivant /
  non-vivant). Le coup n'est plus une liste ordonnée mais un **ensemble de couples clé→valeur**.
- **Memory** : cartes retournées, l'enfant retourne deux cartes et cherche les paires. Sur le plan
  du **domaine**, c'est le **même coup** qu'associer (des couples validés) ; seule l'**interaction**
  (flip de cartes) diffère.

Il faut donc un **coup d'appariement/catégorisation** honnête et testable côté domaine, sans
détourner `SubmitArrangement` (une liste ordonnée ne représente pas fidèlement un ensemble de
couples). Contraintes inchangées : Clean Architecture (domaine pur), **htmx/Thymeleaf + fat jar
unique** (zéro build Node, zéro dépendance front externe), **5 règles ArchUnit vertes**, mécaniques
**isolées entre elles**, **moteur de session partagé** (ADR-0007) réutilisé sans être complexifié.

## Décision

### 1. Nouveau variant de `Move` : `SubmitAssociations(Map<String,String>)`

On ajoute au `sealed interface Move` (paquet **PARTAGÉ `domain.mechanic`**, aux côtés de
`SpinAndPick`, `Skip`, `SubmitArrangement`) un variant **`SubmitAssociations(Map<String,String>
associations)`**. C'est le **1er coup d'appariement/catégorisation** au-delà de l'ordonnancement :
le coup transporte une **vraie `Map`** (clé item → valeur choisie), pas une chaîne bricolée ni une
liste détournée. La sémantique reste honnête et le domaine peut raisonner dessus.

### 2. Le `SessionEngine` reste AGNOSTIQUE — correction par encodage canonique

Comme pour l'arrangement, la correction est une **égalité d'encodage canonique**, pas une
comparaison structurelle spécifique. On ajoute **`Item.encodeAssociations(Map<String,String>)`** —
**source unique partagée** entre le générateur et le moteur — qui produit une chaîne **triée par
clé** (déterministe, indépendante de l'ordre de saisie). Le moteur compare
`Item.encodeAssociations(soumis)` à `Item.expected().value()` (déjà encodé trié par le générateur),
applique le **même scoring (+10)** et le **même marquage « maîtrisé »** que les autres coups.
Aucune connaissance du contenu ne remonte dans le moteur partagé.

### 3. Deux mécaniques isolées qui partagent le même coup

- **`domain.mechanics.associer`** (5e mécanique) : appariement / catégorisation. Interaction :
  glisser-déposer / clic (plateau complet).
- **`domain.mechanics.memory`** (6e mécanique) : cartes retournées, **îlot JS de flip** ; elle
  **réutilise `SubmitAssociations`** (mêmes couples validés côté domaine). Seule la vue (flip CSS,
  `static/js/memory.js` sur le pattern `bandit.js`/`glisser-deposer.js`) est propre à memory.

Les deux mécaniques **délèguent au moteur partagé** et ne dépendent d'aucune autre mécanique ni
d'un générateur concret.

### 4. Contenu externalisé DÈS LE DÉPART (pas de contenu en dur)

Contrairement au glisser-déposer (contenu combinatoire en dur à l'origine, cf. rétro s14/s15), les
nouveaux jeux sont **externalisés en YAML dès la 1re version** : loaders **`AssocPackLoader`** et
**`MemoryPackLoader`** calqués sur `QuizPackLoader` (chargés + validés au bootstrap, convention
`niveau-jeu.yml` d'ADR-0012). 6 jeux : `anglais-associer-cm1`, `vivant-non-vivant-cp` (associer) ;
`memory-quantite-cp`, `memory-fraction-cm1`, `memory-anglais-cm1`, `memory-mot-image-cp` (memory).

### 5. `GameSession` porte `jokers` + `triedWrong` (persistés)

Le mécanisme des 3 cœurs (cf. DEC-0013) est **transverse** : `GameSession` gagne `jokers`
(restants) et `triedWrong` (réponses déjà tentées à tort sur l'item courant), tous deux
**persistés**. C'est une évolution du modèle de session partagé, orthogonale au type de coup (elle
s'applique à toutes les mécaniques à clic).

### 6. Scope différé assumé — pas de verrouillage partiel type DEC-0008

Le **feedback partiel** du glisser-déposer (DEC-0008 : les tuiles bien placées restent verrouillées,
seules les mal placées repartent en réserve) **n'est PAS repris** pour « associer/classer ». Sur
erreur : **retente sur le plateau complet + consomme 1 cœur**. Ce raffinement reste **propre au
glisser-déposer** ; on l'ajoutera à associer/memory seulement si un besoin utilisatrice l'exige.

## Règle exécutable ArchUnit

**Aucune nouvelle règle, aucune règle assouplie.** L'isolation des mécaniques (règle 5) couvre
**AUTOMATIQUEMENT** les 2 nouveaux paquets `domain.mechanics.associer` et `domain.mechanics.memory`,
car elle est écrite en **slices dynamiques** sur le sous-paquet `mechanics.(*)` : toute nouvelle
mécanique y tombe sans édition de la règle.

```java
// Règle (5) existante, inchangée — couvre associer + memory sans modification :
slices().matching("com.recreo.domain.mechanics.(*)..")
    .should().notDependOnEachOther()
    .as("les mecaniques ne doivent pas dependre les unes des autres");
```

- Le variant `SubmitAssociations` et `Item.encodeAssociations` vivent dans le paquet **partagé**
  `domain.mechanic` (hors des slices `mechanics.*`) → pas de dépendance croisée introduite.
- Le découplage mécanique/contenu (règle 4) reste vert : `associer`/`memory` ne dépendent d'aucun
  générateur ; les jeux sont chargés via loaders YAML en `bootstrap`.
- La pureté du domaine (règles 2/3) tient : `Move`, `Map<String,String>` et `encodeAssociations`
  sont du domaine sans framework.
- **5/5 vertes** (à confirmer par la CI). Comportement couvert par tests unitaires (mécaniques +
  encodage canonique trié) et MockMvc (plateau associer, flip memory, non-fuite de la réponse).

## Conséquences

- **Positif** : un **seul coup** (`SubmitAssociations`) sert deux mécaniques (associer/classer et
  memory) et débloque une large famille pédagogique (appariement mot↔image, FR↔EN, quantité↔chiffre,
  fraction↔représentation, catégorisation vivant/non-vivant, nature du mot…).
- **Positif** : le **moteur de session reste agnostique** (il compare deux encodages) ; l'isolation
  ArchUnit en slices dynamiques **absorbe gratuitement** les nouvelles mécaniques.
- **Positif** : contenu **externalisé dès le départ** → enrichissement sans dev (dette évitée par
  rapport au glisser-déposer).
- **Coût / risque** : `jokers`/`triedWrong` sur `GameSession` changent la **sémantique du coup faux
  pour toutes les mécaniques à clic** (blast radius, cf. DEC-0013) — moteur + view-models + fragments
  + tests de non-régression.
- **Encodage `Map`** : `encodeAssociations` trie par **clé** ; suppose des clés/valeurs sans le
  séparateur réservé (OK pour les contenus visés).

## Alternatives considérées

- **Détourner `SubmitArrangement`** (encoder les paires dans une liste ordonnée) : sémantique
  **malhonnête** (un ensemble de couples n'est pas un ordre) et correction fragile. Écarté au profit
  d'un variant `SubmitAssociations(Map)` explicite.
- **Deux coups distincts (associer ≠ memory)** : redondant — memory est, côté domaine, un
  appariement identique ; seule l'interaction change. Un seul coup partagé, deux vues.
- **Une mécanique unique paramétrée (associer + memory dans le même paquet)** : casserait la
  lisibilité et l'isolation par mécanique. Deux paquets isolés, un coup commun.
- **Reprendre le verrouillage partiel (DEC-0008) d'emblée** : sur-ingénierie sans demande ; différé
  (cf. Décision §6).
