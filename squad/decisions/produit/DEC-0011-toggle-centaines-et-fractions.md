# DEC-0011 — Toggle « centaines » (décomposition) + « Range les fractions » (CM1)

- **Type** : Produit / Contenu + réglage
- **Date** : 2026-07-21
- **Auteur** : Camille (PO), sur demande de l'utilisatrice.
- **Sprint** : s15
- **Compétence programme visée** : Maths CP/CM1 — valeur positionnelle ; Maths CM1 — comparer et
  ordonner des fractions.
- **Statut de validation** : À VALIDER (démo s15 à venir)

## Décisions
1. **Toggle « inclure les centaines »** sur « Décompose le nombre » (affine DEC-0010) : une **case à
   cocher** dans les réglages permet d'ajouter les centaines. Défaut par niveau : **CP décoché**
   (dizaines/unités), **CM1 pré-coché** (+ centaines). L'adulte peut inverser à chaque niveau.
   Réglage réutilisable (cf. ADR-0015).
2. **« Range les fractions »** (Maths CM1, glisser-déposer) : ranger des fractions **de la plus
   petite à la plus grande** (pool vérifié : 1/5, 1/4, 1/3, 1/2, 2/3, 3/4, 4/5 ; comparaison exacte
   par produit croisé). Nouvelle déclinaison du glisser-déposer.

## Justification pédagogique
Le toggle « centaines » répond à la demande initiale (« par défaut dizaines et unités, on peut cocher
les centaines ») tout en gardant le jeu visible aux deux niveaux. Comparer/ordonner des fractions est
un attendu CM1 ; le geste « ranger » (déjà validé) s'y prête bien.

## Impact
- **Application** : `ToggleSettingsView` (case à cocher). **Bootstrap** : `DecompositionGameFactory`
  (toggle + défaut par niveau) ; entrée `fractions-cm1` (glisser-déposer). **Domaine** :
  `FractionsGenerator`. **Web** : `@RequestParam("centaines")` + bloc case à cocher dans `parametres.html`.
- **Matrice** : fractions CM1 « en cours (s15) ». La décomposition (s14) est enrichie du toggle
  (couverture inchangée).
- ADR-0015 (toggle) ; pas d'ADR pour les fractions (déclinaison glisser-déposer).

## À confirmer en démo
- La case « Inclure les centaines » est claire ; le défaut par niveau convient.
- Lisibilité des fractions dans les tuiles ; ordre attendu correct.

## Réserve
Pool de fractions restreint (7) ; enrichissable (voire externalisable comme les quiz) plus tard.
