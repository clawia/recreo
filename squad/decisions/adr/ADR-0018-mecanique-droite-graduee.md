# ADR-0018 — Mécanique « Droite graduée » (8e) réutilisant le coup `SpinAndPick`

- **Statut** : Accepté
- **Date** : 2026-07-22
- **Auteur** : theo-archi
- **Sprint** : s22

## Contexte

`recreo` compte 7 mécaniques (bandit manchot, texte à trous, quiz-aventure, glisser-déposer,
associer/classer, memory, puzzle/assemblage). Trois coups d'interaction structurent le domaine, tous
dans le paquet **partagé `domain.mechanic`** :

- **`SpinAndPick`** — un **choix** parmi des options présentées (« clique la bonne »), introduit par
  le bandit manchot et déjà réutilisé par toutes ses déclinaisons (calcul mental, le bon son,
  compare les fractions, décompose le nombre…).
- **`SubmitArrangement(List<String>)`** — un **ordonnancement**, introduit par le glisser-déposer
  (ADR-0013) et **réutilisé** par le puzzle (ADR-0017).
- **`SubmitAssociations(Map<String,String>)`** — un **appariement/catégorisation**, introduit par
  associer/classer et **réutilisé** par le memory (ADR-0016).

La roadmap §B demande une **8e mécanique « Droite graduée »** : l'enfant **place un nombre sur une
droite graduée** (0 → N) en **cliquant la bonne graduation**. Sa 1re déclinaison — **placer un
nombre entier** — est **exactement un choix** : l'enfant sélectionne, parmi les graduations
proposées (les *ticks* de la droite), celle qui correspond au nombre cible. Côté **domaine**, c'est
le **même coup** que le bandit manchot ; seule l'**interaction** (rendu d'une droite graduée, clic
sur un tick, feedback visuel) diffère.

Il ne faut donc **pas** créer un nouveau coup : comme le puzzle a réutilisé `SubmitArrangement` et le
memory a réutilisé `SubmitAssociations`, la mécanique droite graduée **réutilise `SpinAndPick`** du
bandit manchot. Contraintes inchangées : Clean Architecture (domaine pur), **htmx/Thymeleaf + fat jar
unique** (zéro build Node, zéro dépendance front externe), **5 règles ArchUnit vertes**, mécaniques
**isolées entre elles**, **moteur de session partagé** (ADR-0007) réutilisé sans être complexifié.

## Décision

### 1. Aucun nouveau coup — réutilisation de `SpinAndPick`

La mécanique droite graduée **n'introduit aucun variant de `Move`**. Placer un nombre sur la droite
est un **choix parmi les graduations** : le coup transporte la graduation cliquée, exactement comme
« clique la bonne réponse » du bandit manchot. Le parallèle est **le même que puzzle ↔ glisser-déposer
et memory ↔ associer** : deux mécaniques distinctes côté interaction, **un seul coup partagé** côté
domaine (ici `SpinAndPick`).

### 2. Le `SessionEngine` reste AGNOSTIQUE

La correction reste le **choix de la bonne option** déjà en place pour `SpinAndPick` : le moteur
compare la graduation choisie à la bonne réponse encodée par le générateur, applique le **même
scoring (+10)** et le **même marquage « maîtrisé »**. Aucune connaissance du contenu ne remonte dans
le moteur partagé, aucune évolution du contrat de session. **Les jokers (DEC-0013 / DEC-0017)
bénéficient automatiquement à la mécanique** : un tick faux = **1 cœur perdu** + tick grisé (retente),
à 0 cœur un tick faux termine la session — c'est le comportement natif de `SpinAndPick`.

### 3. Nouveau paquet mécanique isolé `domain.mechanics.droitegraduee` (8e)

- **`domain.mechanics.droitegraduee`** (8e mécanique) : placer un nombre sur une droite graduée
  0 → N. 1re déclinaison **nombres entiers**. La mécanique **délègue au moteur partagé** et ne dépend
  d'aucune autre mécanique ni d'un générateur concret.
- **Interaction** : îlot JS de vue `static/js/droitegraduee.js` sur le patron `puzzle.js` /
  `memory.js` / `glisser-deposer.js` (garde + re-branchement `htmx:afterSwap`, clic de graduation,
  tick faux grisé, zéro dépendance front).

### 4. Contenu combinatoire (bornes câblées dans `BeansConfig`)

La 1re déclinaison s'appuie sur un générateur **`DroiteGradueeGenerator`** **combinatoire** (tire un
nombre cible dans [0, N] et construit la droite graduée 0 → N), dont les **bornes sont câblées dans
`BeansConfig`** (comme les autres générateurs combinatoires — pas de contenu à liste finie, donc pas
d'externalisation YAML). 2 jeux livrés d'emblée :
- **`droite-graduee-cp`** — Maths CP, droite **0 → 20** ;
- **`droite-graduee-cm1`** — Maths CM1, droite **0 → 100**.

## Règle exécutable ArchUnit

**Aucune nouvelle règle, aucune règle assouplie.** L'isolation des mécaniques (règle 5) couvre
**AUTOMATIQUEMENT** le nouveau paquet `domain.mechanics.droitegraduee`, car elle est écrite en
**slices dynamiques** sur le sous-paquet `mechanics.(*)` : toute nouvelle mécanique y tombe sans
édition de la règle.

```java
// Règle (5) existante, inchangée — couvre droitegraduee sans modification :
slices().matching("com.recreo.domain.mechanics.(*)..")
    .should().notDependOnEachOther()
    .as("les mecaniques ne doivent pas dependre les unes des autres");
```

- Le coup `SpinAndPick` vit dans le paquet **partagé** `domain.mechanic` (hors des slices
  `mechanics.*`) → droite graduée et bandit manchot partagent le coup **sans dépendance croisée**
  entre leurs paquets `mechanics.*` (comme puzzle ↔ glisser-déposer, memory ↔ associer).
- Le découplage mécanique/contenu (règle 4) reste vert : `droitegraduee` ne dépend d'aucun
  générateur ; `DroiteGradueeGenerator` est câblé en `bootstrap` (`BeansConfig`).
- La pureté du domaine (règles 2/3) tient : `Move`, `SpinAndPick` et la construction de la droite
  sont du domaine sans framework.
- **5/5 vertes** (à confirmer par la CI). Comportement couvert par tests unitaires (mécanique +
  générateur `DroiteGradueeGenerator` + bornes 0→20 / 0→100) et MockMvc (droite graduée affichée,
  clic de graduation, non-fuite de la bonne graduation dans le DOM, cœur perdu sur tick faux).

## Conséquences

- **Positif** : **zéro nouveau coup** — la droite graduée réutilise `SpinAndPick` exactement comme le
  puzzle a réutilisé `SubmitArrangement` et le memory `SubmitAssociations`. Le domaine ne gagne aucune
  dette de contrat pour ouvrir une 8e mécanique.
- **Positif** : les **jokers bénéficient automatiquement** à la mécanique (tick faux = cœur perdu +
  tick grisé), sans code de jokers propre à la droite graduée.
- **Positif** : le **moteur de session reste agnostique** ; l'isolation ArchUnit en slices dynamiques
  **absorbe gratuitement** la nouvelle mécanique.
- **Coût / risque** : l'îlot JS `droitegraduee` est un nouveau front (rendu de droite + clic de tick +
  tick grisé) à couvrir en non-régression UI (niveau HTML, ADR-0008) ; ajouter une **8e mécanique**
  est le point de vigilance (garder l'isolation ArchUnit verte — cf. rétro s22).

## Alternatives considérées

- **Nouveau coup `PlaceOnNumberLine`** : redondant — placer un nombre sur une droite graduée **est**
  un choix parmi les graduations ; introduire un coup équivalent à `SpinAndPick` dupliquerait la
  correction et alourdirait le `sealed interface Move` sans gain sémantique. Écarté au profit de la
  réutilisation.
- **Décliner la droite graduée dans le paquet bandit manchot** : casserait l'isolation par mécanique
  et la lisibilité (l'interaction « droite graduée + clic de tick » est propre à la mécanique).
  Nouveau paquet isolé, coup partagé — même patron que puzzle vs glisser-déposer.
- **Externaliser le contenu en YAML** : sans objet — le contenu est **combinatoire** (bornes 0→N),
  pas une liste finie ; les bornes sont câblées dans `BeansConfig` comme les autres générateurs
  combinatoires.

## Note — les filtres « Par jeu » n'exigent PAS d'ADR

L'autre livrable de s22 — les **filtres « Par jeu »** (IDEE-3 : filtrer l'écran `/jeux` par niveau et
matière via des boutons combinables/réinitialisables, filtrage serveur par query params
`?niveau=&matiere=`) — **n'exige aucun ADR** (Théo) : c'est un pur **read-model / vue** sur le
catalogue enrichi (ADR-0005), **aucun changement de domaine**, aucune règle d'architecture touchée.
Même nature que la navigation « par matière » de s21 (read-model, sans ADR).
