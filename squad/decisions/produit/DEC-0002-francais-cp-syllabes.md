# DEC-0002 — Français CP : exercice « dénombrer les syllabes »

- **Type** : Produit / Pédagogique
- **Date** : 2026-07-18
- **Auteur** : Camille (PO)
- **Sprint** : s02
- **Compétence programme visée** : Français CP — conscience phonologique (dénombrer les syllabes
  orales d'un mot).
- **Statut de validation** : VALIDÉ (user, 2026-07-18) — après 2 retouches d'ergonomie (mot
  affiché en entier + consigne sortie de la machine)
- **Validé par** : utilisatrice (démo 2026-07-18)

## Contexte / besoin

Élargir la couverture au-delà des mathématiques (évolution 2 du sprint s02) en **réutilisant la
mécanique bandit manchot** pour une nouvelle matière, afin de prouver le découplage
mécanique/contenu sur une matière très différente.

## Décision

Premier jeu de français CP = **« Les syllabes »** : un mot s'affiche sur le rouleau, la consigne
est « Combien de syllabes ? », l'enfant clique le bon nombre parmi des options. Contenu produit
par `SyllabesCpGenerator` à partir d'une **liste de mots vérifiée** (mot → nombre de syllabes
orales). Aucune modification de `BanditManchotMechanic`.

## Justification pédagogique

La conscience syllabique est un prérequis fondamental de l'apprentissage de la lecture au CP.
L'exercice au format choix multiple est court, sans charge de lecture, adapté au niveau. La liste
de mots est volontairement restreinte et vérifiée pour éviter tout comptage ambigu.

## Impact

- **Domaine** : `SyllabesCpGenerator` (content/impl) ; enabler `reelSymbols`/`question` (ADR-0004).
- **Catalogue** : entrée `francais-cp` (câblée en code, cf. rapport s02).
- **Matrice** : `Français / CP / phonologie / dénombrer les syllabes` → « en cours (s02) ».
- **Fiche** : `FICHE-francais-cp-syllabes.md`.
- **À confirmer en démo** : lisibilité du mot sur un rouleau, compréhension de la consigne.
