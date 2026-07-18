# DEC-0003 — Paramétrage des jeux : réglé par un adulte, portée session, non persisté

- **Type** : Produit
- **Date** : 2026-07-18
- **Auteur** : Camille (PO)
- **Sprint** : s03
- **Compétence programme visée** : transverse (adapte la difficulté au niveau/à l'élève sans
  sortir du programme).
- **Statut de validation** : VALIDÉ (user, 2026-07-18)
- **Validé par** : utilisatrice (décision de cadrage, avant implémentation)

## Contexte / besoin
Le bouton « paramètres » (US-s03-2) permet de régler la difficulté d'un jeu (maths : fenêtre de
nombres + opérations ; français : nombre de syllabes max…). Il fallait trancher qui règle et si
les réglages sont mémorisés.

## Décision
- Les paramètres sont **réglés par un adulte / accompagnant** (écran intitulé « Réglages
  (adulte) »), pas par l'enfant.
- Ils s'appliquent à la **session de jeu en cours**.
- Ils ne sont **pas persistés** (aucune mémorisation entre sessions en s03).
- Des **valeurs par défaut** reproduisent le comportement validé en s01/s02 (CP/CM1, français CP).
- Les bornes proposées restent **cohérentes avec le niveau** (pas de réglage hors-programme).

## Justification
Simplicité pour s03 (pas de persistance à introduire), et pertinence pédagogique : l'adaptation
de la difficulté relève de l'adulte qui accompagne l'enfant.

## Impact
- **Domaine/app** : paramètres passés au démarrage de session → `ContentGenerator` (cf. ADR-0005).
- **Front** : écran de réglages par jeu (maths / français).
- **Report** : une éventuelle **persistance** des réglages (profil élève) est renvoyée à un sprint
  ultérieur.
