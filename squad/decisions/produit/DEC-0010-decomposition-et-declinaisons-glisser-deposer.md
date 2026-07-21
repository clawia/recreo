# DEC-0010 — Décomposition des nombres + déclinaisons du glisser-déposer

- **Type** : Produit / Contenu
- **Date** : 2026-07-21
- **Auteur** : Camille (PO), sur demande de l'utilisatrice.
- **Sprint** : s14
- **Compétence programme visée** : Maths CP/CM1 — valeur positionnelle des chiffres ; Maths CM1 —
  ordonner des nombres ; Histoire CM1 — se repérer dans le temps (frise chronologique).
- **Statut de validation** : VALIDÉ (user, 2026-07-21).

## Décisions
1. **« Décompose le nombre »** (bandit manchot) : un nombre s'affiche, l'enfant clique le **chiffre**
   d'une position tirée au hasard. Deux entrées **par niveau** (pas de toggle — l'infra « choix » a
   été retirée en s13) :
   - **CP** : nombres à 2 chiffres → positions **dizaines / unités**.
   - **CM1** : nombres à 3 chiffres → positions **centaines / dizaines / unités**.
   Choix pédagogique : « **chiffre** des dizaines » (valeur positionnelle : 4 dans 342), pas « nombre
   de dizaines ».
2. **Déclinaisons du glisser-déposer** (mécanique existante, ordonnancement) :
   - **Frise chronologique** (Histoire CM1) : ranger des événements **du plus ancien au plus récent**
     (Préhistoire → Gaulois → Moyen Âge → Révolution française → tour Eiffel → aujourd'hui).
   - **Range les nombres — CM1** : mêmes règles que CP mais **plus grands nombres** (jusqu'à 100).
   Le feedback partiel (tuiles bien placées conservées) et le sens aléatoire s'appliquent aussi.

## Justification pédagogique
La valeur positionnelle est un fondamental de la numération (CP : dizaines/unités ; CM1 : +
centaines). La frise travaille le repérage dans le temps (attendu d'histoire CM1) en réutilisant le
geste « ranger » déjà validé.

## Impact
- **Domaine** : `DecompositionGenerator` (combinatoire, paramétré CP/CM1) ; `FriseChronologiqueGenerator`
  (glisser-déposer). Aucune nouvelle mécanique, **aucun nouvel ADR**.
- **Catalogue** : 4 entrées (decomposition-cp, decomposition-cm1, frise-cm1, range-nombres-cm1) ;
  décompo groupée sous `gameKey=decomposition`, range CP/CM1 sous `range-nombres`.
- **Matrice** : 4 lignes « en cours (s14) ».

## À confirmer en démo
- Formulation « chiffre des … » comprise ; difficulté CM1 (centaines) adaptée.
- Frise : lisibilité des étiquettes d'événements dans les tuiles ; ordre chronologique attendu correct.

## Réserve
Frise : socle de 6 événements volontairement restreint (à enrichir/valider). Une future
externalisation du contenu frise (comme les quiz) est envisageable.
