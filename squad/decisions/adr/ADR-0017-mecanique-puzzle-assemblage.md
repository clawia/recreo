# ADR-0017 — Mécanique « Puzzle / assemblage » (7e) réutilisant le coup `SubmitArrangement`

- **Statut** : Accepté
- **Date** : 2026-07-22
- **Auteur** : theo-archi
- **Sprint** : s21

## Contexte

`recreo` compte 6 mécaniques (bandit manchot, texte à trous, quiz-aventure, glisser-déposer,
associer/classer, memory). Deux coups d'interaction structurent le domaine, tous deux dans le
paquet **partagé `domain.mechanic`** :

- **`SubmitArrangement(List<String>)`** — un **ordonnancement** (« dans quel ordre ? »), introduit
  par le glisser-déposer (ADR-0013) et déjà réutilisé par ses déclinaisons (frise chronologique,
  range les nombres/fractions).
- **`SubmitAssociations(Map<String,String>)`** — un **appariement/catégorisation**, introduit par
  associer/classer et **réutilisé** par le memory (ADR-0016).

La roadmap §B demande une **7e mécanique « Puzzle / assemblage »** (reconstituer un mot, une phrase,
plus tard un tangram / une carte). Sa 1re déclinaison — **reconstituer un mot** (remettre des lettres
mélangées dans l'ordre, avec un indice image/définition) — est **exactement un ordonnancement** :
l'enfant produit une **séquence** de tuiles (lettres) qui doit égaler la séquence attendue. Côté
**domaine**, c'est le **même coup** que le glisser-déposer ; seule l'**interaction** (assemblage de
lettres avec indice, présentation « puzzle ») diffère.

Il ne faut donc **pas** créer un nouveau coup : comme le memory a réutilisé `SubmitAssociations`
d'associer/classer, la mécanique puzzle **réutilise `SubmitArrangement`** du glisser-déposer.
Contraintes inchangées : Clean Architecture (domaine pur), **htmx/Thymeleaf + fat jar unique** (zéro
build Node, zéro dépendance front externe), **5 règles ArchUnit vertes**, mécaniques **isolées entre
elles**, **moteur de session partagé** (ADR-0007) réutilisé sans être complexifié.

## Décision

### 1. Aucun nouveau coup — réutilisation de `SubmitArrangement`

La mécanique puzzle **ne introduit aucun variant de `Move`**. Reconstituer un mot est un
**ordonnancement de tuiles** : le coup transporte la `List<String>` des lettres dans l'ordre soumis,
exactement comme « Range les nombres ». Le parallèle est **le même que memory ↔ associer** : deux
mécaniques distinctes côté interaction, **un seul coup partagé** côté domaine (ici
`SubmitArrangement`, comme le glisser-déposer et ses déclinaisons).

### 2. Le `SessionEngine` reste AGNOSTIQUE — correction par encodage canonique

La correction reste une **égalité d'encodage canonique** déjà en place pour l'arrangement : le
moteur compare `Item.encodeArrangement(soumis)` à `Item.expected().value()` (encodé par le
générateur), applique le **même scoring (+10)** et le **même marquage « maîtrisé »**. Aucune
connaissance du contenu ne remonte dans le moteur partagé, aucune évolution du contrat de session.

### 3. Nouveau paquet mécanique isolé `domain.mechanics.puzzle` (7e)

- **`domain.mechanics.puzzle`** (7e mécanique) : assemblage / reconstitution. 1re déclinaison
  **« reconstituer un mot »** — lettres mélangées à remettre dans l'ordre + **indice** (image ou
  définition). La mécanique **délègue au moteur partagé** et ne dépend d'aucune autre mécanique ni
  d'un générateur concret.
- **Interaction** : îlot JS `static/js/puzzle.js` sur le patron `glisser-deposer.js` / `memory.js`
  (garde + re-branchement `htmx:afterSwap`, drag-and-drop / clic de lettres, zéro dépendance front).

### 4. Contenu externalisé DÈS LE DÉPART (pas de contenu en dur)

La 1re déclinaison s'appuie sur un générateur `MotPuzzleGenerator` (lettres à remettre dans l'ordre
+ indice image/définition) alimenté par un **loader YAML** sur le patron `QuizPackLoader` /
`AssocPackLoader`. 2 jeux livrés en YAML d'emblée (convention `niveau-jeu.yml`, ADR-0012) :
`content/puzzle/cp-mots-images.yml` (indice image, Français CP écriture/lecture) et
`content/puzzle/cm1-mots.yml` (indice définition, vocabulaire CM1). **Aucun contenu combinatoire en
dur** (dette évitée vs glisser-déposer d'origine).

## Règle exécutable ArchUnit

**Aucune nouvelle règle, aucune règle assouplie.** L'isolation des mécaniques (règle 5) couvre
**AUTOMATIQUEMENT** le nouveau paquet `domain.mechanics.puzzle`, car elle est écrite en **slices
dynamiques** sur le sous-paquet `mechanics.(*)` : toute nouvelle mécanique y tombe sans édition de la
règle.

```java
// Règle (5) existante, inchangée — couvre puzzle sans modification :
slices().matching("com.recreo.domain.mechanics.(*)..")
    .should().notDependOnEachOther()
    .as("les mecaniques ne doivent pas dependre les unes des autres");
```

- Le coup `SubmitArrangement` et `Item.encodeArrangement` vivent dans le paquet **partagé**
  `domain.mechanic` (hors des slices `mechanics.*`) → puzzle et glisser-déposer partagent le coup
  **sans dépendance croisée** entre leurs paquets `mechanics.*` (comme memory ↔ associer).
- Le découplage mécanique/contenu (règle 4) reste vert : `puzzle` ne dépend d'aucun générateur ; le
  jeu est chargé via loader YAML en `bootstrap`.
- La pureté du domaine (règles 2/3) tient : `Move`, `List<String>` et `encodeArrangement` sont du
  domaine sans framework.
- **5/5 vertes** (à confirmer par la CI). Comportement couvert par tests unitaires (mécanique +
  générateur `MotPuzzleGenerator` + encodage) et MockMvc (plateau puzzle, indice affiché, non-fuite
  de l'ordre attendu dans le DOM).

## Conséquences

- **Positif** : **zéro nouveau coup** — le puzzle réutilise `SubmitArrangement` exactement comme le
  memory a réutilisé `SubmitAssociations`. Le domaine ne gagne aucune dette de contrat pour ouvrir
  une 7e mécanique et une nouvelle famille pédagogique (reconstituer un mot, une phrase…).
- **Positif** : le **moteur de session reste agnostique** (il compare deux encodages d'arrangement) ;
  l'isolation ArchUnit en slices dynamiques **absorbe gratuitement** la nouvelle mécanique.
- **Positif** : contenu **externalisé dès le départ** → enrichissement sans dev.
- **Coût / risque** : l'îlot JS puzzle est un nouveau front (drag/clic de lettres + indice) à couvrir
  en non-régression UI (niveau HTML, ADR-0008) ; l'ampleur du sprint s21 (7e mécanique + refactor de
  générateurs + nouvel axe de navigation en parallèle) est le vrai point de vigilance (cf. rétro s21).

## Alternatives considérées

- **Nouveau coup `SubmitPuzzle`** : redondant — reconstituer un mot **est** un ordonnancement ;
  introduire un coup équivalent à `SubmitArrangement` dupliquerait la correction et alourdirait le
  `sealed interface Move` sans gain sémantique. Écarté au profit de la réutilisation.
- **Décliner le puzzle dans le paquet glisser-déposer** : casserait l'isolation par mécanique et la
  lisibilité (l'interaction « assemblage + indice » est propre au puzzle). Nouveau paquet isolé, coup
  partagé — même patron que memory vs associer.
- **Contenu en dur pour démarrer** : rejouerait la dette frise/fractions (justement remboursée en
  s21) ; externalisation YAML d'emblée.

## Note — chantiers s21 SANS ADR

Deux chantiers de s21 **n'exigent aucun ADR** (Théo) :

- **Jokers paramétrables via YAML + réglage adulte** : simple **paramètre de settings**. Le champ
  optionnel `jokers` de l'en-tête YAML est lu par `SessionEngine` (`settings["jokers"]`) et exposé en
  réglage adulte (`JokersSettingsView`, param POST `jokers`) ; le capital 6 du memory provient
  désormais du YAML. C'est l'aboutissement de la réserve laissée par DEC-0013 / roadmap A3
  (`SessionEngine(mechanicId, defaultJokers)` de s19) — **extension d'un service existant**, pas de
  nouveau contrat ni de nouvelle règle d'architecture.
- **Navigation par matière (3e axe)** : nouveau **read-model** (`CatalogueQuery.matieres()` /
  `byMatiere()`) et deux routes de lecture (`/matieres`, `/matiere/{matiere}`) sur le catalogue
  enrichi (ADR-0005). Aucun changement de domaine ni de coup ; les slices/règles ArchUnit sont
  inchangées. **Aucune décision d'architecture requise.**

Reste également **sans ADR** l'externalisation frise/fractions (chantier D) : `FriseChronologiqueGenerator`
et `CompareFractionsGenerator` reçoivent leur liste **par constructeur** (injection depuis
`content/frise/*.yml` et `content/fractions/*.yml` via de nouveaux loaders) — **même patron
d'externalisation** que les quiz/mots/associer/memory (ADR-0009 à ADR-0011), générateurs inchangés
côté mécanique, ArchUnit 5/5. Remboursement de dette, pas de décision structurante.
