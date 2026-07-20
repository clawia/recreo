# Index des ADR (décisions techniques)

Tenu à jour par `theo-archi` à chaque création ou changement de statut d'un ADR. Numéros à 4
chiffres, jamais réutilisés (voir `squad/README.md` §1). Un ADR = une ligne.

| N° | Titre | Statut | Date | Sprint |
|---|---|---|---|---|
| [ADR-0004](ADR-0004-tokens-rouleaux-generiques.md) | Tokens de rouleaux génériques (le contenu fournit ce qui s'affiche) | Accepté | 2026-07-18 | s02 |
| [ADR-0005](ADR-0005-catalogue-enrichi-parametres.md) | Catalogue enrichi (métadonnées) + paramètres de session | Accepté | 2026-07-18 | s03 |
| [ADR-0006](ADR-0006-mode-session-cle-item-exclusion.md) | Mode session (N questions, sans doublon des réussis) : clé d'item + exclusion + fin de session | Accepté | 2026-07-19 | s05 |
| [ADR-0007](ADR-0007-support-multi-mecaniques.md) | Support multi-mécaniques : moteur de session partagé + clé d'item + sélection de vue | Accepté | 2026-07-19 | s06 |
| [ADR-0008](ADR-0008-strategie-test-ui.md) | Stratégie de test UI : niveau HTML (MockMvc), pas de navigateur | Accepté | 2026-07-19 | s07 |

> **s08 — pas de nouvel ADR.** Sprint 100 % contenu sur mécaniques existantes : nouveaux
> générateurs dans `domain.content.impl` (ne dépendent d'aucune mécanique — ArchUnit 5/5), tables
> via pack YAML réutilisant `CalculMentalGenerator`. Matière/niveau restent des chaînes libres
> (« EMC » ajouté sans changement structurel). Aucune décision d'architecture requise (Théo).

| [ADR-0009](ADR-0009-externalisation-contenu-quiz.md) | Externalisation du contenu quiz dans des fichiers YAML éditables (chargés + validés en bootstrap) | Accepté | 2026-07-20 | s09 |
