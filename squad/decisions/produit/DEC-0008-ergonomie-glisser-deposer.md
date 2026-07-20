# DEC-0008 — Ergonomie du « glisser-déposer » (Range les nombres)

- **Type** : Produit / Ergonomie
- **Date** : 2026-07-20
- **Auteur** : Camille (PO) — esquisse d'ergonomie de la 1ère mécanique à îlot JS.
- **Sprint** : s12
- **Compétence programme visée** : Mathématiques CP — ordonner des nombres (du plus petit au plus grand).
- **Statut de validation** : À VALIDER (démo s12 à venir)

## Contexte
1ère mécanique nécessitant une interaction JS riche (îlot JS, cf. ADR-0013). Jeu inaugural
« Range les nombres du plus petit au plus grand » (Maths CP).

## Ergonomie retenue (esquisse)
- Une consigne claire en haut (« Range les nombres du plus petit au plus grand »).
- Une rangée de **tuiles-nombres** mélangées (grosses cibles), que l'enfant **glisse** dans une
  rangée d'**emplacements** ordonnés de gauche à droite.
- Un bouton **Valider** soumet l'arrangement.
- **Arrangement correct** → « Bravo ! +10 » et on passe au puzzle suivant.
- **Arrangement faux** → un **message** invite à réessayer et on **reste sur le MÊME puzzle** (pas
  d'avance, la bonne réponse n'est pas révélée). **Feedback partiel (demande utilisatrice s12)** :
  les tuiles **déjà bien placées restent verrouillées** dans leur emplacement ; **seules les tuiles
  mal placées repartent en réserve**. L'enfant complète/corrige jusqu'à réussir. Techniquement : un
  helper pur `ArrangementBoard.afterAttempt(soumis, attendu)` (domaine) calcule, par position,
  verrouillé (soumis[i] == attendu[i]) vs à replacer ; le mapper web l'utilise pour re-rendre le
  plateau (l'ordre attendu n'est jamais affiché, seulement les positions réussies par l'enfant).
- Écran de fin mutualisé (score / score max) après N puzzles résolus. Réutilise le mode session
  (réglage « nombre de questions »).

## Justification pédagogique
Le geste « ranger » matérialise la relation d'ordre, plus concret pour un enfant de CP que choisir
une réponse. Ouvre une nouvelle famille d'interactions (ranger / associer / classer / frises).

## À confirmer en démo
- Le drag-and-drop est-il assez facile pour un enfant (souris/tactile) ?
- Lisibilité tuiles/emplacements ; clarté du feedback « le bon ordre était … ».
- **Réserve connue (ADR-0013)** : le drag-and-drop HTML5 natif est faible au doigt sur mobile ;
  un repli « taper pour placer » est à envisager en itération si besoin.
