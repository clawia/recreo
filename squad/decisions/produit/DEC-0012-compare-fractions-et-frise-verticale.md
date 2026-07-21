# DEC-0012 — « Compare les fractions » (< > =) + frise chronologique verticale

- **Type** : Produit / Contenu + ergonomie
- **Date** : 2026-07-21
- **Auteur** : Camille (PO), sur retours de l'utilisatrice.
- **Sprint** : s16
- **Compétence programme visée** : Maths CM1 — comparer des fractions ; Histoire CM1 — se repérer
  dans le temps (frise).
- **Statut de validation** : À VALIDER (démo s16 à venir)

## Décisions
1. **« Range les fractions » → « Compare les fractions »** (affine/remplace la partie fractions de
   DEC-0011) : au lieu de RANGER des fractions (glisser-déposer), le jeu affiche **deux fractions** et
   l'enfant choisit le bon **signe** parmi `<`, `>`, `=` (mécanique **bandit manchot**, comme « Le bon
   symbole »). Pool avec fractions équivalentes (1/2 = 2/4 = 3/6…) → cas d'égalité possibles ;
   comparaison **exacte** par produit croisé. L'ancien `FractionsGenerator` (ordonnancement) est supprimé.
2. **Frise chronologique en VERTICAL** : les emplacements sont empilés **de haut en bas**, avec un
   rail « temps » à **flèche descendante** — « Plus ancien » en haut, « Plus récent » en bas — pour
   matérialiser l'écoulement du temps. Variante d'affichage de la vue glisser-déposer (les autres
   jeux d'ordonnancement, ex. « Range les nombres », restent horizontaux).

## Justification pédagogique
Comparer deux fractions avec un signe est un attendu direct du programme (ordre des fractions) et plus
simple à lire qu'un rangement pour un premier contact. La frise verticale à flèche rend l'axe du temps
explicite (convention usuelle des frises « du plus ancien au plus récent »).

## Impact
- **Domaine** : `CompareFractionsGenerator` (bandit manchot) ; suppression de `FractionsGenerator`.
- **Bootstrap** : entrée `fractions-cm1` repasse en bandit manchot (`compare-fractions`).
- **Web** : `fragments/glisserdeposer.html` gagne un variant `frise-verticale` (activé quand l'entryId
  commence par « frise ») — rail « temps » + flèche ; CSS `app.css`. Aucun changement de mécanique.
- **Matrice** : fractions CM1 → « comparer avec <, >, = ».
- **Pas de nouvel ADR** (mécaniques existantes ; la frise verticale est une variante CSS).

## À confirmer en démo
- Comparaison de fractions : difficulté et proportion d'égalités adaptées au CM1.
- Frise verticale : lisibilité de la flèche/labels ; le glisser-déposer reste facile à la verticale.
