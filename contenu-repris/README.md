# contenu-repris - réserve de contenu pédagogique

Ces 61 fichiers YAML viennent de la branche `main` (première version de recreo). Ils ont déjà
servi en production et couvrent des compétences de CP et de CM1 en mathématiques et en français.

## Ce que ce dossier est

Une **réserve de matière première**. Quand la squad décide de couvrir une compétence, elle
regarde d'abord ici avant d'écrire du contenu neuf : le travail de rédaction des énoncés, des
distracteurs et des listes de mots est déjà fait, et ce contenu a été joué.

## Ce que ce dossier n'est pas

- **Pas un cahier des charges.** La squad repart des référentiels de compétences
  (`squad/programme/`), pas de cet inventaire. Rien n'oblige à reproduire les jeux de `main`.
- **Pas du contenu câblé.** Aucun de ces fichiers n'est chargé par l'application. Le format de
  `main` est lié à ses 9 familles de chargeurs YAML dupliqués, une dette que `round-two` ne
  reproduit pas. Reprendre un fichier veut dire en reprendre la **matière** (les énoncés, les
  réponses attendues), pas la structure.
- **Pas validé pédagogiquement pour round-two.** Un contenu repris doit repasser devant
  `nadia-prof` comme n'importe quel contenu neuf.

## Organisation

Les fichiers sont rangés par famille de jeu telle qu'elle existait sur `main` :
`associer/`, `quiz/`, `memory/`, `mots/`, `phrase/`, `puzzle/`, `frise/`, `fractions/`,
`syllabes/`, `textes-trous/`, plus trois paquets de calcul mental à la racine.

Ce découpage reflète l'ancienne architecture, pas une décision de `round-two`.
