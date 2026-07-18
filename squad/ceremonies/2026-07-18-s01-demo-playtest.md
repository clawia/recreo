# [Sprint s01] Démo + Playtest — 2026-07-18

- Sprint : s01 (Amorçage — bandit manchot calcul mental CP/CM1)
- Animé par : Sofia ; présents : Camille, Léa, l'utilisatrice (validation)
- Type : démo pédagogique + playtest enfants (même journée)
- Résultat : ✅ **Done pédagogique prononcé** par l'utilisatrice + **playtest enfants validé**

## Déroulé

Démo jouée sur `java -jar recreo.jar` (http://localhost:8080), niveaux CP et CM1. Le contenu
(calcul mental) et le découplage mécanique/contenu sont conformes. Les retours ont porté
exclusivement sur la **présentation** de la mécanique bandit manchot (couche `adapter-web`),
sans toucher au domaine ni à l'architecture.

## Évolutions & corrections appliquées pendant la démo (front)

| # | Retour utilisatrice | Correction | Fichiers |
|---|---------------------|------------|----------|
| 1 | Pas de rouleaux ni d'animation, juste le calcul et des boutons | Refonte en vraie machine à sous : casier + rouleaux + levier | `fragments/banditmanchot.html`, `app.css` |
| 2 | Les rouleaux doivent porter le CALCUL (ex. `2` `+` `2`), les réponses cliquables en dehors | 3 rouleaux = opérande/opérateur/opérande + « = ? » ; réponses en boutons séparés sous la machine | `fragments/banditmanchot.html`, `app.css` |
| 3 | Défilement trop rapide | Ralenti à ~1 s, défilement calme (7 symboles) | `js/bandit.js` |
| 4 | Ajouter des symboles en décor sur les rouleaux qui tournent | Symboles machine à sous (🍒 🔔 ⭐ 7️⃣ 🍋 🍇 🪙 💎) défilant pendant la rotation | `js/bandit.js` |
| 5 | Le message de points décale la machine | Message déplacé SOUS les réponses (position stable) | `fragments/banditmanchot.html`, `app.css` |
| 6 | Les rouleaux ne tournent pas du tout | Bug : `bandit.js` n'était chargé que dans `jeu.html` (inutilisé), pas sur la page catalogue où le jeu se joue via htmx → ajout du script au catalogue | `catalogue.html` |
| 7 | Symboles flous | Suppression du flou de mouvement (symboles nets) | `app.css` |
| 8 | Rouleaux tournent « à l'envers » | Sens inversé : les symboles tombent vers le bas et s'arrêtent sur la valeur | `js/bandit.js` |

Correctif technique annexe : alignement de l'arrêt (bordure de vitre → liseré `box-shadow` pour
que la fenêtre fasse exactement 1 item de haut). Décision produit consolidée : **DEC-0001**.

## Portée / non-régression

- Aucune modification du `domain`, de l'`application` ni des règles ArchUnit.
- Modifications limitées à `adapter-web` (templates, CSS, JS). Tests back inchangés et toujours
  verts (CI = Done technique déjà prononcé en review s01).

## Décisions & validations

- **DEC-0001** (présentation machine à sous) — VALIDÉ (user, 2026-07-18).
- Fiche `FICHE-bandit-manchot-calcul-mental.md` — validation utilisateur + playtest renseignés.
- `FILE-ATTENTE.md` — incrément s01 passé à **VALIDÉ** (2026-07-18).

## Suites

- **s02** : écran de sélection de niveau dédié, bascule vers le jeu avec **flèche retour** seule,
  **bandit manchot centré** (story ajoutée au backlog).
- Rétro s01 puis planning s02.
