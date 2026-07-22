# DEC-0013 — Jokers : 3 cœurs, retente sur erreur, 0 cœur = fin de session

- **Type** : Produit / UX + pédagogie (transverse à toutes les mécaniques)
- **Date** : 2026-07-22
- **Auteur** : Camille (PO), décisions tranchées avec l'utilisatrice (2026-07-22).
- **Sprint** : s17
- **Compétence programme visée** : transverse (posture d'apprentissage / droit à l'erreur — apprendre
  en corrigeant plutôt qu'en enchaînant).
- **Statut de validation** : **À VALIDER** (implémenté s17, Done pédagogique en attente de démo).

## Contexte
Jusqu'à présent, sur une **erreur**, les mécaniques à clic (bandit manchot, texte à trous,
quiz-aventure) **passaient à la question suivante**. Le glisser-déposer, lui, **retentait déjà** le
même puzzle (DEC-0008). L'utilisatrice veut, sur une erreur, **redemander le même item** à l'enfant
plutôt que d'avancer — avec un **capital de 3 cœurs (❤️)** par session. Réflexion cadrée dans
`squad/programme/PLAN-jokers-retenter.md`.

## Décisions tranchées (utilisatrice, 2026-07-22)
1. **Capital de 3 cœurs par session** : nombre **fixe** de 3 (pas de réglage adulte pour l'instant).
2. **Bonne réponse** → +10 (inchangé), item suivant.
3. **Erreur ET cœurs restants** → **consomme 1 cœur** et **RETENTE le même item** (on n'avance pas ;
   item / round / score inchangés). Message « Presque, réessaie ! » + jauge de ❤️ restants.
4. **Cœur consommé PAR CLIC faux** (et non par question ratée) — cohérent avec « 3 tentatives ». Sur
   un jeu à clic, l'**option fausse cliquée devient non cliquable** (désactivée) pour cet item.
5. **Bonne réponse après retente = +10 plein** (l'objectif est d'apprendre en réessayant ; pas de
   score réduit).
6. **Erreur ET 0 cœur** → la **session SE TERMINE immédiatement** (écran de fin avec le score
   courant). Les 3 cœurs sont le **capital** de la session ; une fois épuisés, une erreur y met fin.
7. **Alignement du glisser-déposer** : il retente déjà le même puzzle (DEC-0008, feedback partiel) ;
   il s'aligne désormais sur le **budget de cœurs** — un cœur par essai faux, à 0 cœur un essai faux
   termine la session.

## Justification pédagogique
Le droit à l'erreur et la reprise immédiate valorisent l'apprentissage par correction plutôt que la
sanction (« mauvaise réponse → question suivante »). Le budget de 3 cœurs borne l'acharnement et
donne un enjeu (« il me reste 2 ❤️ »), tout en gardant l'expérience courte et lisible pour un enfant.

## Impact
- **Domaine** : `GameSession` porte `jokers` (défaut 3) et `triedWrong` (réponses déjà tentées à tort
  sur l'item courant, remis à zéro à chaque nouvel item), **persistés**. `SessionEngine.apply` : coup
  faux + `jokers > 0` → `jokers--`, ajoute à `triedWrong`, **pas d'`advance()`** ; `jokers == 0` →
  session `FINISHED`.
- **Vues** : scoreboard commun affiche `❤️ × jokers` ; chaque fragment désactive les options ∈
  `triedWrong` (bandit manchot `answer-btn`, quiz `quiz-btn`, texte à trous `word-btn`).
- **Non-régression UI** : l'écran de fin ne doit PAS apparaître pendant les retentes (jokers > 0) ;
  il DOIT apparaître sur une erreur à 0 joker.

## Raffinements s18 (après démo utilisatrice de s17)
Retours de l'utilisatrice sur s17, corrigés en s18 — pas de nouvelle décision, ajustements de la
règle des cœurs et de l'écran de fin :
1. **Le memory consomme aussi un cœur.** En s17, le memory ne consommait aucun cœur ; désormais
   **chaque mauvaise paire retournée coûte 1 cœur** (aligné sur les autres mécaniques à clic). À
   **0 cœur**, une mauvaise paire **termine la session**. Note archi : cela a nécessité une synchro
   cœurs client/serveur (fetch de l'état + fin de session côté îlot JS `memory.js`).
2. **Écran de fin « Essaye encore ».** Le titre de l'écran de fin dépend désormais de la cause de fin :
   **« Bravo »** quand la session se termine sur **victoire** (toutes les questions traitées) ;
   **« Essaye encore »** quand elle s'arrête **faute de cœurs** (0 cœur + erreur).
3. **Score max corrigé.** En fin par cœurs épuisés, le **dénominateur** du score compte la question
   **en cours** (répondues + 1) : `SessionEngine.onWrong` incrémente `answered` **avant** de passer
   la session à `FINISHED`. Le score affiché reflète ainsi la question ratée qui a mis fin à la session.

## Réserves / à confirmer en démo
- Le **capital de 3** est-il le bon niveau de difficulté (ni trop punitif, ni trop permissif) ?
- **Blast radius** : la sémantique du coup faux change pour **toutes** les mécaniques à clic — à
  vérifier en démo qu'aucune n'a régressé.
- Rendre les cœurs **réglables** par l'adulte (0..5) reste ouvert (non retenu pour s17).
- Affichage / animation des ❤️ (pleins ↔ vidés) à valider visuellement.
