# DEC-0009 — Retours d'ergonomie s13 (maths CP) + nouveau jeu « Le bon symbole »

- **Type** : Produit / Ergonomie + contenu
- **Date** : 2026-07-21
- **Auteur** : Camille (PO), sur retours de l'utilisatrice.
- **Sprint** : s13
- **Compétence programme visée** : Maths CP — sens du nombre, comparaison (`<`, `>`, `=`), ordonner.
- **Statut de validation** : À VALIDER (démo s13 à venir)

## Décisions
1. **Complément à 10 — afficher l'équation** : les rouleaux montrent « n + ⬚ = 10 » (au lieu du seul
   « n »). La consigne « Combien pour aller à 10 ? » est conservée.
2. **Range les nombres (glisser-déposer)** :
   - **Sens réglable** par l'adulte : *croissant* (défaut, « du plus petit au plus grand ») ou
     *décroissant* (« du plus grand au plus petit »).
   - **Défaut 3 questions** (plancher abaissé à 1 pour ce jeu) — une courte session suffit.
3. **Comparaison des nombres** : consigne réglable — demander *le plus grand* (défaut) **ou** *le
   plus petit*.
4. **Nouveau jeu « Le bon symbole »** (Maths CP, bandit manchot) : deux nombres, l'enfant choisit le
   bon signe parmi `<`, `>`, `=` (cas d'égalité inclus).

Les réglages (2/3) reposent sur le nouveau réglage « choix » générique (cf. ADR-0014).

## Justification pédagogique
Voir l'équation entière renforce le sens de la relation « compléter à 10 ». Varier le sens (tri,
comparaison) évite l'automatisme « toujours le plus grand » et travaille la relation d'ordre dans les
deux sens. Le choix du symbole `<`/`>`/`=` est un attendu explicite du programme (comparer des nombres).

## À confirmer en démo
- L'équation du complément est lisible ; le réglage « sens » est compris par l'adulte.
- « Le bon symbole » : difficulté et proportion de cas d'égalité adaptées au CP.

## Planifié (non implémenté) — cf. `squad/programme/PLAN-decomposition-nombre.md`
Exercice de **décomposition** (centaines/dizaines/unités) : clic sur le bon chiffre, toggle
« centaines » (réutilise le réglage « choix » d'ADR-0014). À arbitrer/planifier ultérieurement.
