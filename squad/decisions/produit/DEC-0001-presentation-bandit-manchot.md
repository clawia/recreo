# DEC-0001 — Présentation « machine à sous » du bandit manchot

- **Type** : Produit / Pédagogique (ergonomie)
- **Date** : 2026-07-18
- **Auteur** : Camille (PO), sur retours de démo/playtest de l'utilisatrice
- **Sprint** : s01
- **Compétence programme visée** : Mathématiques — calcul mental CP (additions/soustractions ≤10)
  et CM1 (multiplications/tables) — la présentation ne doit pas dénaturer le contenu.
- **Statut de validation** : VALIDÉ (user, 2026-07-18)
- **Validé par** : utilisatrice (démo + playtest enfants)

## Contexte / besoin

La première version affichait le calcul et de simples boutons de réponse : la mécanique ne se
lisait pas comme un « bandit manchot ». La démo a précisé l'intention pédagogique et ludique.

## Décision (présentation retenue)

1. **Les 3 rouleaux portent le CALCUL** (opérande | opérateur | opérande), suivis de « = ? ».
2. **Les réponses sont des boutons cliquables séparés, EN DEHORS de la machine** : l'enfant lit le
   calcul sur les rouleaux puis clique la bonne réponse.
3. **Animation machine à sous** : pendant la rotation, les rouleaux font défiler des symboles
   décoratifs (🍒 🔔 ⭐ 7️⃣ 🍋 🍇 🪙 💎) **nets** (pas de flou), qui **tombent vers le bas** et
   **s'arrêtent pile** sur la valeur du calcul. Durée ~**1 s**, défilement calme.
4. **Message de points affiché SOUS les réponses** (jamais au-dessus) pour que la machine ne se
   décale pas d'une manche à l'autre.
5. Feedback immédiat + animation « jackpot » (flash/tremblement) quand la réponse est correcte.

## Justification pédagogique

Rend la mécanique immédiatement compréhensible et engageante pour des enfants de CP/CM1 (grosses
cibles tactiles, lecture du calcul isolée de l'acte de répondre), sans ajouter de charge de
lecture ni de notion hors-programme. Le contenu (le calcul) reste piloté par les règles ; seule la
présentation change — le découplage mécanique/contenu est préservé.

## Impact

- **Front** : `adapter-web` (fragment `banditmanchot.html`, `catalogue.html`, `app.css`,
  `js/bandit.js`). Aucun changement du domaine ni de l'architecture.
- **Backlog** : story s02 « écran de sélection de niveau dédié + bandit centré + flèche retour ».
- **Fiche de conformité** : `FICHE-bandit-manchot-calcul-mental.md` (validée).
