# DEC-0016 — Ouverture du domaine monnaie (Grandeurs et mesures)

- **Type** : Produit / Pédagogique (pur contenu, réutilise les mécaniques existantes)
- **Date** : 2026-07-22
- **Auteur** : Camille (PO)
- **Sprint** : s20
- **Compétence programme visée** : Maths CP — Grandeurs et mesures — monnaie (compter des euros,
  petites sommes, rendre la monnaie) ; Maths CM1 — Grandeurs et mesures — monnaie (rendre la
  monnaie, conversions euros/centimes ; associer une somme en euros à sa valeur en centimes).
- **Statut de validation** : **VALIDÉ (utilisatrice, 2026-07-22)** — Done pédagogique prononcé par
  l'utilisatrice (validation groupée s19 + s20). Une seule correction de contenu appliquée à la
  validation : **le 5 € est un billet, pas une pièce** (monnaie CP) ; le reste validé tel quel.

## Contexte / besoin
Le domaine **Grandeurs et mesures** était jusqu'ici **entièrement absent** de la matrice
(cf. roadmap §C). La **monnaie** en est une porte d'entrée concrète et très demandée à l'école
élémentaire. s20 l'ouvre **par du pur contenu**, sur les mécaniques déjà livrées (quiz-aventure et
associer/classer), sans nouvelle mécanique ni nouvel ADR — chaque jeu est **externalisé en YAML et
auto-enregistré** par les loaders existants (aucun code).

## Décisions produit
1. **Monnaie CP** (S20-1, mécanique **quiz-aventure**) : `content/quiz/cp-monnaie.yml`
   (gameKey `monnaie`) — compter des euros, petites sommes, rendre la monnaie. Format
   question/réponses adapté au CP.
2. **Monnaie CM1** (S20-2, mécanique **quiz-aventure**) : `content/quiz/cm1-monnaie.yml`
   (gameKey `monnaie`) — rendre la monnaie, conversions euros/centimes.
3. **Associer une somme à sa valeur en centimes CM1** (S20-3, mécanique **associer/classer**) :
   `content/associer/cm1-monnaie-centimes.yml` (gameKey `associer`) — apparier une somme exprimée
   en euros à sa valeur en centimes (appariement, comme `anglais-associer-cm1`).

Tout est **externalisé YAML** et **auto-enregistré** par les loaders (`QuizPackLoader`,
`AssocPackLoader`) : **aucun code** n'est ajouté.

## Justification pédagogique
La monnaie relie les nombres à une grandeur du quotidien (payer, rendre la monnaie), ce qui donne
du sens au calcul. Elle se prête au quiz (CP : compter/rendre ; CM1 : rendre / convertir) et à
l'appariement (CM1 : euros ↔ centimes). Elle amorce le domaine **Grandeurs et mesures** sans
nouvelle mécanique, en réutilisant trois briques déjà validées.

## Impact
- **Domaine** : **aucun** — pur contenu.
- **Bootstrap** : 3 packs YAML auto-enregistrés (2 quiz, 1 associer). Aucune ligne de code.
- **Web** : rien de spécifique (vues quiz et plateau d'appariement existantes).
- **Matrice** : ouverture du domaine **Grandeurs et mesures — monnaie** ; Maths CP monnaie (quiz),
  Maths CM1 monnaie (quiz + associer) → `en cours (s20)`.
- **ADR** : **aucun nouvel ADR** (cf. note ADR-index s20) — pur contenu sur mécaniques existantes.

## À confirmer en démo
- Justesse des montants, des rendus de monnaie et des conversions euros/centimes (point de
  vigilance principal — contenus à relire/enrichir par l'utilisatrice dans les YAML).
- Niveau adapté (CP : petites sommes ; CM1 : conversions et rendus plus larges).
- Lisibilité de l'appariement euros ↔ centimes sur le plateau (CM1).

## Correction appliquée à la validation (2026-07-22)
- **Correction : le 5 € est un billet, pas une pièce** (jeu monnaie CP). Le contenu YAML a été
  ajusté en conséquence ; le reste du domaine monnaie a été validé tel quel.
