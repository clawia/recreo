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
| DEC-0015 | Compréhension & grammaire CM1 + cœurs adaptés à la mécanique (memory 6 ❤️, dés agrandis) | Français CM1 — compréhension de texte ; Français CM1 — grammaire (nature des mots) ; transverse (droit à l'erreur) | VALIDÉ (user, 2026-07-22) | 2026-07-22 | s19 |
| DEC-0016 | Ouverture du domaine monnaie (Grandeurs et mesures) — pur contenu YAML | Maths CP — monnaie (compter/rendre) ; Maths CM1 — monnaie (rendre, conversions €/centimes) | VALIDÉ (user, 2026-07-22) | 2026-07-22 | s20 |
| DEC-0017 | Mécanique « Puzzle » (7e, reconstituer un mot) + jokers paramétrables via YAML + navigation par matière | Français CP écriture/lecture (reconstituer un mot) ; Français CM1 vocabulaire (reconstituer un mot) ; transverse (cœurs réglables via YAML) ; navigation (par matière) | VALIDÉ (user, 2026-07-23) — ⚠️ réglage adulte des cœurs partiellement annulé (2026-07-22, YAML uniquement) | 2026-07-22 | s21 |
| DEC-0018 | Externalisation frise + fractions (dette remboursée) + déclinaisons associer/classer Français CP (majuscule↔minuscule, le/la) | Français CP principe alphabétique (majuscule↔minuscule) ; Français CP genre du nom (le/la) ; transverse (technique — frise/fractions externalisées) | VALIDÉ (user, 2026-07-23) | 2026-07-22 | s21 |
| DEC-0019 | Filtres « Par jeu » (niveau/matière, boutons combinables/réinitialisables — IDEE-3) + mécanique « Droite graduée » (8e, réutilise `SpinAndPick`) | Maths CP situer/placer un nombre sur une droite graduée (0→20) ; Maths CM1 idem (0→100) ; transverse (ergonomie — filtres « Par jeu ») | VALIDÉ (user, 2026-07-23) | 2026-07-22 | s22 |
| DEC-0020 | Fraction sur la droite (droite graduée généralisée à une liste d'étiquettes `lineTicks` — entiers OU fractions, réutilise `SpinAndPick`) + ouverture matière **Géométrie** + longueurs / durées (Grandeurs et mesures) — pur contenu YAML | Maths CM1 situer une fraction sur une droite graduée (0→1) ; Géométrie CP reconnaître les formes (quiz + associer) ; Géométrie CM1 propriétés/angles/symétrie ; Maths CM1 longueurs (m/cm/km/mm) ; Maths CM1 durées (h/min/jour/semaine) | VALIDÉ (user, 2026-07-23) | 2026-07-22 | s23 |
| DEC-0021 | Géométrie & grandeurs/mesures approfondies (symétrie CP ; solides / périmètre / quadrilatères CM1 ; masses / contenances / lire l'heure CM1 ; comparer des grandeurs CP) + enrichissement des viviers existants — **100 % contenu YAML** sur mécaniques existantes ; aucune nouvelle mécanique/coup, aucun nouvel ADR | Géométrie CP symétrie ; Géométrie CM1 solides / périmètre / quadrilatères ; Maths CM1 Grandeurs et mesures masses / contenances / lire l'heure ; Maths CP comparer des grandeurs ; + enrichissement (sans nouvelle compétence) anglais / EMC / sciences / histoire / géo / QLM / décodage CP | ✅ VALIDÉ (user, 2026-07-25) | 2026-07-24 | s24 |
| DEC-0023 | Filtres tags (chips) sur les axes « par niveau » (filtrer par matière) et « par matière » (filtrer par niveau) — symétriques aux filtres « Par jeu » (DEC-0019) ; read-model / vue, aucun changement de domaine, pas d'ADR | transverse (ergonomie / navigation) — aucune compétence nouvelle | ⏳ À VALIDER (user) | 2026-07-25 | — (ergonomie) |
| DEC-0022 | Déclinaison puzzle **PHRASE** (« Remets la phrase dans l'ordre » — déclinaison de la mécanique Puzzle, réutilise `PuzzleMechanic` + coup `SubmitArrangement` ; `PhrasePuzzleGenerator` ordonne des mots + loader `content/phrase/*.yml`) + contenus additionnels sur mécaniques existantes (droite graduée câblée 0→50 / 0→1000, associer, memory, puzzle lettres) + aires CM1 + longueurs / durées au CP + enrichissement des viviers — aucune nouvelle mécanique/coup, aucun nouvel ADR | Français CP reconstituer une phrase ; Français CM1 reconstituer une phrase ; Géométrie CM1 aires ; Maths CP longueurs ; Maths CP durées ; Maths CP droite graduée 0→50 ; Maths CM1 droite graduée 0→1000 ; + contenus additionnels & enrichissement (sans nouvelle compétence) | ✅ VALIDÉ (user, 2026-07-25) | 2026-07-26 | s25 |

> **s18 — pas de nouvelle DEC.** Raffinements de s17 après démo utilisatrice, consignés en
> section « Raffinements s18 » dans DEC-0013 (cœur consommé aussi par le memory ; écran de fin
> « Essaye encore » quand la session s'arrête faute de cœurs ; correction du score max) et DEC-0014
> (verrouillage partiel d'associer/classer aligné sur DEC-0008 ; faces de dés pour le memory
> quantités CP ; images côté français pour le memory anglais CM1). Statut : **VALIDÉ (user,
> 2026-07-22)** — s17 + s18 validés ensemble.

> **s19 + s20 — validés ensemble.** DEC-0015 (s19 : compréhension & grammaire CM1 + cœurs adaptés à
> la mécanique — réalise IDEE-1/IDEE-2 du backlog) et DEC-0016 (s20 : ouverture du domaine monnaie,
> pur contenu YAML) ont été livrées sans démo intermédiaire. Statut : **VALIDÉ (user, 2026-07-22)** —
> Done pédagogique prononcé par l'utilisatrice, validation groupée s19 + s20. Une seule correction de
> contenu appliquée : le **5 € est un billet, pas une pièce** (monnaie CP, DEC-0016) ; le reste
> validé tel quel.
