# DEC-0014 — Mécaniques « Associer/Classer » & « Memory » + 6 jeux externalisés

- **Type** : Produit / Nouvelles mécaniques + contenu
- **Date** : 2026-07-22
- **Auteur** : Camille (PO), en appui de l'ADR-0016 (Théo).
- **Sprint** : s17
- **Compétence programme visée** : Questionner le monde CP (vivant/non-vivant) ; Anglais CM1
  (appariement lexique) ; Maths CP (quantité↔chiffre) ; Maths CM1 (fractions↔représentation) ;
  Français CP (mot↔image / lecture).
- **Statut de validation** : **VALIDÉ (utilisatrice, 2026-07-22)** (implémenté s17, raffiné s18 ;
  Done pédagogique prononcé lors de la validation groupée s17 + s18).

## Contexte
Le glisser-déposer couvrait l'**ordonnancement**. « Associer » (paires) et « classer » (catégories)
sont un **type d'interaction différent** (repéré depuis s14/s15 comme « le prochain vrai type de
coup »), et « memory » (cartes retournées) est, côté domaine, le **même appariement**. Ces trois
besoins reposent sur un **nouveau coup générique `SubmitAssociations(Map)`** (cf. ADR-0016).

## Décisions produit
1. **Mécanique « Associer / Classer »** (5e mécanique) : l'enfant **apparie** des paires ou
   **catégorise** des items sur un plateau complet.
   - **`anglais-associer-cm1`** — appariement mot **FR → EN** (Anglais CM1, lexique).
   - **`vivant-non-vivant-cp`** — **catégorisation** vivant / non-vivant (Questionner le monde CP).
2. **Mécanique « Memory »** (6e mécanique) : cartes **retournées**, l'enfant cherche les paires (îlot
   JS de flip). Réutilise le coup `SubmitAssociations`.
   - **`memory-quantite-cp`** — quantité ↔ chiffre (Maths CP).
   - **`memory-fraction-cm1`** — fraction ↔ représentation (Maths CM1).
   - **`memory-anglais-cm1`** — mot FR ↔ EN (Anglais CM1).
   - **`memory-mot-image-cp`** — mot ↔ image / lecture (Français CP).
3. **Contenu externalisé DÈS LE DÉPART** : les 6 jeux sont livrés en **fichiers YAML éditables**
   (loaders `AssocPackLoader` / `MemoryPackLoader` sur le patron `QuizPackLoader`), pour permettre
   l'enrichissement sans dev — on n'introduit pas de contenu en dur (dette évitée vs glisser-déposer).
4. **Scope différé assumé** : le **verrouillage partiel** type DEC-0008 (tuiles bien placées
   conservées) **n'est PAS repris** pour associer/classer. Sur erreur : **retente plein plateau +
   1 cœur** (aligné sur DEC-0013). Ce raffinement reste propre au glisser-déposer.

## Justification pédagogique
- **Associer/classer** matérialise des relations (correspondance, appartenance à une catégorie) plus
  riches que le simple choix ou l'ordre — appariement lexical, tri vivant/non-vivant.
- **Memory** ajoute une charge de **mémoire de travail** ludique sur les mêmes contenus (quantité,
  fractions, lexique, lecture), très adaptée aux jeunes enfants.
- Deux matières encore peu couvertes progressent (Anglais CM1 au-delà du bandit manchot ; Français CP
  lecture mot↔image).

## Impact
- **Domaine** : coup `SubmitAssociations(Map)` + `Item.encodeAssociations` (encodage trié par clé) ;
  mécaniques isolées `mechanics/associer` et `mechanics/memory`. Cf. ADR-0016.
- **Bootstrap** : 6 entrées catalogue + loaders YAML (`AssocPackLoader`, `MemoryPackLoader`).
- **Web** : vue « plateau » (associer) et îlot JS de flip (`memory.js`, pattern `glisser-deposer.js`).
- **Matrice** : 5 nouvelles compétences en `en cours` (à passer `couvert-validé` après démo).
- **ArchUnit** : 5/5 — la règle d'isolation en slices dynamiques couvre les 2 nouveaux paquets.

## Raffinements s18 (après démo utilisatrice de s17)
Retours de l'utilisatrice sur s17, corrigés en s18 — pas de nouvelle mécanique ni nouveau coup :
1. **Verrouillage partiel d'associer/classer** (scope explicitement différé en s17, décision 4
   ci-dessus, maintenant repris). Sur une erreur, **seules les cases mal placées reviennent en
   réserve** — les éléments bien classés restent **verrouillés en place**. Aligné sur DEC-0008
   (feedback partiel du glisser-déposer). Note archi : nouveau record domaine pur `AssociationBoard`
   (calqué sur `ArrangementBoard`, aide de calcul pure) ; règles ArchUnit inchangées (cf. note ADR-index s18).
2. **Memory quantités CP** : les faces montrent désormais des **faces de dés** (⚀⚁⚂⚃⚄⚅) au lieu de
   points ; le jeu passe à **6 paires**.
3. **Memory anglais CM1** : le côté français est remplacé par des **images** (🐶🐱🏠🐟☀️🍎) associées
   au mot anglais (au lieu du mot FR écrit).

### Raffinement (2026-07-25) — bouton « Suivant » à la complétion du memory
Demande utilisatrice : quand la **dernière paire** est retournée, le memory **n'enchaîne plus
automatiquement** l'écran suivant. L'enfant voit d'abord sa **planche complète**, puis un **bouton
« Suivant ▶ »** (révélé par `memory.js` à la complétion) déclenche le passage à l'écran suivant
(item suivant ou écran de fin). C'est ce clic qui soumet le form caché — **aucun changement serveur**
(même coup `SubmitAssociations`, même endpoint) ; purement **vue + îlot JS** (`memory.html`,
`memory.js`, CSS `.memory-suivant`). Repli : si le bouton est absent, comportement historique
(soumission immédiate). Ergonomie — aucune compétence nouvelle.

## À confirmer en démo
- Justesse des contenus des 6 packs YAML (paires FR↔EN, quantité↔chiffre, fractions↔représentation,
  mot↔image, catégorisation vivant/non-vivant).
- Ergonomie de l'appariement (plateau) et du flip memory pour un enfant (souris / tactile).
- Interaction avec les jokers (DEC-0013) sur ces mécaniques : un essai faux consomme 1 cœur, plein
  plateau retenté.
