# DEC-0007 — Contenu s08 : « Le bon son » (phonologie CP) + ouverture de l'EMC

- **Type** : Produit / Pédagogique
- **Date** : 2026-07-20
- **Auteur** : Camille (PO)
- **Sprint** : s08
- **Compétence programme visée** : Français CP — décodage / association graphème↔phonème ;
  EMC CP & CM1 — règles de vie, valeurs et symboles de la République.
- **Statut de validation** : VALIDÉ (user, 2026-07-20) — démo jouée, contenu jugé conforme ;
  plafond des tables porté à 20 sur demande.

## Contexte / besoin
Direction s08 : **élargir la couverture par du contenu**, sur des mécaniques existantes, sans
nouvelle mécanique ni ADR. Deux trous prioritaires de la matrice : le **décodage graphème/phonème
CP** (fondamental de la lecture, `non couvert`) et **l'EMC**, matière jusque-là entièrement absente.

## Décision
1. **« Le bon son »** (Français CP, mécanique **bandit manchot**) : un mot s'affiche sur le rouleau,
   la consigne est « Quel son entends-tu ? », l'enfant clique le bon son parmi trois. Contenu
   `LeBonSonGenerator` à partir d'une **liste vérifiée** (mot → son présent), sons ciblés :
   nasales [an]/[on]/[in] + [ou]/[oi]/[ch]. Choix clé : **les distracteurs sont des sons ABSENTS
   du mot, définis mot par mot** (jamais un son réellement présent). Aucune modification de mécanique.
2. **EMC** (mécanique **quiz-aventure**) : « Vivre ensemble » (CP — règles de classe, politesse,
   respect, sécurité) et « Citoyenneté & respect » (CM1 — devise, drapeau, hymne, valeurs, droits
   et devoirs). Ouvre la matière EMC aux deux niveaux.
3. **« Vrai ou Faux — sciences »** (CM1, quiz-aventure à **2 options**) : renfort de sciences, format
   affirmation vraie/fausse (le moteur et la vue gèrent déjà un nombre d'options quelconque).
4. **« Tables de multiplication »** (Maths CM1, bandit manchot) : entraînement dédié, **aucun code
   nouveau** — réutilise `CalculMentalGenerator` via un pack YAML borné à la seule multiplication
   (opérandes 1..10). Renforce une compétence déjà couverte.

## Justification pédagogique
Le décodage graphème/phonème est un pilier de l'apprentissage de la lecture au CP ; le format choix
multiple court, sans charge de lecture, y est adapté. L'EMC est au programme des deux cycles et se
prête naturellement au quiz. Le Vrai/Faux varie la sollicitation ; les tables offrent un
entraînement ciblé que le calcul mental général ne garantit pas.

## Impact
- **Domaine** : `LeBonSonGenerator`, `EmcCpGenerator`, `EmcCm1Generator`,
  `VraiFauxSciencesCm1Generator` (content/impl). Tables : **0 générateur** (YAML
  `tables-multiplication-cm1.yml`).
- **Catalogue** : 4 `defs.add(...)` + 1 `loader.load(...)` dans `BeansConfig`.
- **Matrice** : décodage CP → « en cours (s08) » ; EMC CP/CM1 → « en cours (s08) » ; tables CM1 et
  Vrai/Faux sciences → « en cours (s08) ».
- **Fiches** : `FICHE-le-bon-son-s08.md`, `FICHE-emc-s08.md`.
- **À confirmer en démo** : justesse pédagogique de la liste « Le bon son » (mot ↔ son) — point de
  vigilance principal — et pertinence des questions d'EMC.
