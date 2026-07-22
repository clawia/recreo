# Plan — « 3 jokers » : réessayer au lieu d'enchaîner sur une erreur

Statut : 📋 **PLANIFIÉ — non implémenté** (ajouté au backlog sur demande de l'utilisatrice, 2026-07-21).
Réflexion pédagogique : sur une **erreur**, ne pas passer à une nouvelle question mais **redemander à
l'enfant**. Chaque session offre **3 jokers (3 ❤️)** = 3 nouvelles tentatives au total. Sur un jeu à
clic, **la case fausse cliquée devient non cliquable**.

## Comportement visé
- **Bonne réponse** → +10, item suivant (inchangé).
- **Mauvaise réponse ET il reste des jokers** → **consomme 1 joker**, **ne change pas d'item** :
  l'enfant **retente le même**. Sur un jeu à **clic** (bandit manchot / quiz / texte à trous), la/les
  **option(s) déjà tentée(s)** pour cet item sont **désactivées** (non cliquables). Message type
  « Presque, réessaie ! » + jauge de ❤️ restants.
- **Mauvaise réponse ET plus de joker (0 ❤️)** → **la session SE TERMINE** immédiatement (écran de
  fin avec le score courant). Décision utilisatrice (2026-07-22) : les 3 jokers sont le « capital »
  de la session ; une fois épuisés, une erreur y met fin.
- Fin de session : soit la **cible de questions** est atteinte, soit **une erreur sans joker
  restant**. Le glisser-déposer, qui **retente déjà** le même puzzle avec feedback partiel
  (DEC-0008), s'aligne sur le **budget de jokers** (un joker par essai faux ; à 0 joker, un essai
  faux termine la session).

## Pistes d'implémentation (à confirmer au sprint)
- **Domaine** : `GameSession` porte `jokers` (restants, défaut 3) et, pour l'item courant, l'ensemble
  des **réponses déjà tentées à tort** (`triedWrong`) — remis à zéro à chaque nouvel item.
  `SessionEngine.apply` : sur coup faux, si `jokers > 0` → `jokers--`, ajoute la réponse à
  `triedWrong`, **ne pas** `advance()` (même item/round/score) ; si `jokers == 0` → **terminer la
  session** (`SessionStatus.FINISHED`, pas d'item suivant).
- **Vues** : le scoreboard (`fragments/commun`) affiche `❤️ × jokers` ; chaque fragment de jeu
  **désactive** les options dont la valeur ∈ `triedWrong` (bandit manchot : `answer-btn` désactivé ;
  quiz : `quiz-btn` ; texte à trous : `word-btn`). View-models enrichis (`jokers`, `triedWrong`).
- **Réglage** (option) : nombre de jokers réglable (toggle/réutilise le champ « nombre de questions »
  style) — par défaut 3. À trancher.

## Questions à trancher avec l'utilisatrice (PO)
1. Un joker se consomme-t-il **par clic faux** ou **par question ratée** (quel que soit le nb de
   clics faux sur cette question) ? *(proposition : par clic faux, cohérent avec « 3 tentatives ».)*
2. Une bonne réponse **après** retenter vaut-elle +10 plein, ou un score réduit ? *(proposition : +10
   plein — l'objectif est d'apprendre en réessayant.)*
3. ✅ **TRANCHÉ (utilisatrice, 2026-07-22)** : quand les jokers sont épuisés, une erreur **termine la
   session** (écran de fin avec le score courant).
4. Les jokers sont-ils **réglables** par l'adulte (0 à 5) ou fixés à 3 ?
5. Affichage : ❤️ pleins/vidés dans le bandeau de score — position et animation ?

## Impact / risques
- Change la **sémantique de coup faux** pour TOUTES les mécaniques à clic (aujourd'hui : avance).
  Toucher `SessionEngine` (partagé) + view-models + 3 fragments + scoreboard commun + tests.
- Interaction avec l'anti-doublon : un item **réussi (éventuellement après retentes)** est marqué
  maîtrisé. Il n'y a plus d'« item abandonné » : une erreur sans joker **termine** la session.
- Non-régression UI (`UiNonRegressionWebTest`) à étendre : l'écran de fin ne doit PAS apparaître
  pendant les retentes (jokers > 0) ; il DOIT apparaître sur une erreur à 0 joker ; options
  désactivées présentes.

## Découpage (sprint futur)
Domaine (session + moteur + tests) → view-models + fragments (❤️ + options désactivées) → tests
MockMvc (retente sans avancer, désactivation de l'option fausse, jokers épuisés → **fin de session**) → DEC + démo.
