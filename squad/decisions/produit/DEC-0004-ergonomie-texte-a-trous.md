# DEC-0004 — Ergonomie du « Texte à trous »

- **Type** : Produit / Ergonomie
- **Date** : 2026-07-19
- **Auteur** : Camille (PO) — esquisse d'ergonomie *avant* implémentation (Definition of Ready
  front, action de rétro s02/s03).
- **Sprint** : s06
- **Compétence programme visée** : Français CM1 (conjugaison, accords), Français CP (mot manquant),
  Maths CP (sens de l'égalité).
- **Statut de validation** : À VALIDER (démo s06 à venir)

## Contexte
Le texte à trous est la 2e mécanique. Elle doit se **distinguer visuellement** du bandit manchot
(machine à sous) tout en réutilisant le mode session, les réglages et l'écran de fin.

## Ergonomie retenue (esquisse)
- Afficher **la phrase en entier avec un trou bien visible** (`___` stylé, gros, contrasté) —
  l'enfant lit la phrase et comprend ce qui manque.
- **Sous la phrase**, des **boutons de mots** (les options), grosses cibles tactiles ; l'enfant
  tape le mot qui va dans le trou.
- Après réponse : **message de résultat dans l'emplacement réservé** (comme le bandit manchot,
  sous les réponses, sans décaler la phrase).
- Réutiliser **le scoreboard, l'écran de fin (score X/Y, Rejouer, retour) et le réglage
  « nombre de questions »** — cohérence entre mécaniques (fragment commun).
- Pas de machine à sous ni de rouleaux ici : la mécanique doit être reconnaissable au premier
  coup d'œil comme « une phrase à compléter ».

## Justification pédagogique
Le format « phrase à compléter » est un exercice scolaire familier (conjugaison, accords, sens) ;
il met le mot en **contexte de phrase**, ce que le choix-rapide du bandit manchot ne fait pas.

## À confirmer en démo
- Lisibilité de la phrase + du trou pour un enfant ; taille des boutons de mots ; cohérence avec
  le reste de l'app.
