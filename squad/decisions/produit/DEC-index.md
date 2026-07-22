# Index des DEC (décisions produit/pédago)

Tenu à jour par `camille-po` à chaque création ou changement de statut d'une DEC. Numéros à 4
chiffres, jamais réutilisés (voir `squad/README.md` §1). Une DEC = une ligne.

| N° | Titre | Compétence programme visée | Statut de validation | Date | Sprint |
|---|---|---|---|---|---|
| DEC-0001 | Présentation « machine à sous » du bandit manchot | Maths — calcul mental CP/CM1 | VALIDÉ (user, 2026-07-18) | 2026-07-18 | s01 |
| DEC-0002 | Français CP : exercice « dénombrer les syllabes » | Français CP — conscience phonologique | VALIDÉ (user, 2026-07-18) | 2026-07-18 | s02 |
| DEC-0003 | Paramétrage des jeux : adulte, session, non persisté | transverse (adaptation difficulté) | VALIDÉ (user, 2026-07-18) | 2026-07-18 | s03 |
| DEC-0004 | Ergonomie du « Texte à trous » (phrase à trou + boutons de mots) | Français CM1/CP, Maths CP | VALIDÉ (user, 2026-07-19) | 2026-07-19 | s06 |
| DEC-0005 | Ergonomie du « Quiz-aventure » (carte-question + réponses) | Questionner le monde CP ; Sciences/Histoire/Géo CM1 | VALIDÉ (user, 2026-07-19) | 2026-07-19 | s07 |
| DEC-0006 | Externaliser le contenu pédagogique hors du code (+ doublage des quiz en attendant) | transverse (contenu à liste finie) | RÉALISÉ (s09-s11 : quiz, mots, textes à trous, syllabes tous en YAML) | 2026-07-19 | s07 |
| DEC-0007 | Contenu s08 : « Le bon son » (phonologie CP) + ouverture de l'EMC (+ tables, Vrai/Faux) | Français CP décodage ; EMC CP/CM1 | VALIDÉ (user, 2026-07-20) | 2026-07-20 | s08 |
| DEC-0008 | Ergonomie du « glisser-déposer » (Range les nombres) | Maths CP — ordonner des nombres | VALIDÉ (user, 2026-07-21) | 2026-07-20 | s12 |
| DEC-0009 | Retours ergonomie s13 (complément=équation, sens tri/comparaison aléatoire, défaut 3) + jeu « Le bon symbole » | Maths CP — comparer/ordonner | VALIDÉ (user, 2026-07-21) | 2026-07-21 | s13 |
| DEC-0010 | Décomposition des nombres (CP/CM1) + déclinaisons glisser-déposer (frise chronologique CM1, range CM1) | Maths CP/CM1 valeur positionnelle ; Histoire CM1 frise | VALIDÉ (user, 2026-07-21) | 2026-07-21 | s14 |
| DEC-0011 | Toggle « inclure les centaines » (décomposition) + « Range les fractions » (CM1) | Maths CP/CM1 valeur positionnelle ; Maths CM1 fractions | VALIDÉ (user, 2026-07-21) | 2026-07-21 | s15 |
| DEC-0012 | « Compare les fractions » (< > =, remplace le rangement) + frise chronologique VERTICALE | Maths CM1 comparer fractions ; Histoire CM1 frise | VALIDÉ (user, 2026-07-21) | 2026-07-21 | s16 |
| DEC-0013 | Jokers : 3 cœurs, retente sur erreur (par clic faux, +10 plein), 0 cœur = fin de session | transverse (droit à l'erreur ; toutes mécaniques à clic) | VALIDÉ (user, 2026-07-22) | 2026-07-22 | s17 · raff. s18 |
| DEC-0014 | Mécaniques « Associer/Classer » & « Memory » + 6 jeux externalisés (YAML) | QLM CP vivant/non-vivant ; Anglais CM1 appariement ; Maths CP quantité↔chiffre ; Maths CM1 fractions↔représentation ; Français CP mot↔image | VALIDÉ (user, 2026-07-22) | 2026-07-22 | s17 · raff. s18 |

> **s18 — pas de nouvelle DEC.** Raffinements de s17 après démo utilisatrice, consignés en
> section « Raffinements s18 » dans DEC-0013 (cœur consommé aussi par le memory ; écran de fin
> « Essaye encore » quand la session s'arrête faute de cœurs ; correction du score max) et DEC-0014
> (verrouillage partiel d'associer/classer aligné sur DEC-0008 ; faces de dés pour le memory
> quantités CP ; images côté français pour le memory anglais CM1). Statut : **VALIDÉ (user,
> 2026-07-22)** — s17 + s18 validés ensemble.
