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
| [ADR-0010](ADR-0010-externalisation-contenu-mots.md) | Externalisation du contenu « mot → réponse » (générateur générique + modes explicit/pool) | Accepté | 2026-07-20 | s10 |
| [ADR-0011](ADR-0011-externalisation-contenu-textes-syllabes.md) | Externalisation « texte à trous » (réutilise QuizGenerator) + « syllabes » (schéma numérique dédié) | Accepté | 2026-07-20 | s11 |
| [ADR-0012](ADR-0012-convention-nommage-config.md) | Convention de nommage des fichiers de contenu : `niveau-jeu.yml` | Accepté | 2026-07-20 | s11 |
| [ADR-0013](ADR-0013-mecanique-glisser-deposer-ilot-js.md) | 1ère mécanique à îlot JS : glisser-déposer (variant `SubmitArrangement`, drag-and-drop HTML5 natif) | Accepté | 2026-07-20 | s12 |
| [ADR-0014](ADR-0014-reglage-choix-generique.md) | Défaut/plancher de questions par jeu (réglage « choix » retiré ; sens aléatoire — cf. DEC-0009) | Accepté (amendé) | 2026-07-21 | s13 |

> **s14 — pas de nouvel ADR.** « Décompose le nombre » réutilise le bandit manchot ; « Frise
> chronologique » et « Range les nombres CM1 » réutilisent le glisser-déposer (ADR-0013). Nouveaux
> générateurs dans `domain.content.impl`, mécaniques inchangées → ArchUnit 5/5. Aucune décision d'archi.

| [ADR-0015](ADR-0015-reglage-toggle.md) | Réglage « toggle » (case à cocher, ex. « inclure les centaines ») réglé par l'adulte | Accepté | 2026-07-21 | s15 |

> **s15 — « Range les fractions » (Maths CM1) : pas de nouvel ADR** — déclinaison glisser-déposer
> (ADR-0013), générateur `FractionsGenerator` dans `domain.content.impl`.

> **s16 — pas de nouvel ADR.** « Compare les fractions » (retour utilisatrice) = bandit manchot
> (`CompareFractionsGenerator`, comme « Le bon symbole ») ; l'ancien `FractionsGenerator` d'ordonnancement
> est supprimé. Frise chronologique **verticale** = simple variante CSS de la vue glisser-déposer
> (`frise-verticale`, activée par l'entryId), sans changement de mécanique. ArchUnit 5/5.

| [ADR-0016](ADR-0016-coup-submitassociations-associer-memory.md) | Coup générique `SubmitAssociations(Map)` + mécaniques « Associer/Classer » (5e) & « Memory » (6e) — 6 jeux YAML ; isolation ArchUnit en slices dynamiques couvrant automatiquement les 2 paquets | Accepté | 2026-07-22 | s17 |

> **s18 — pas de nouvel ADR.** Raffinements UX de s17 après démo utilisatrice, aucune nouvelle
> mécanique ni nouveau coup. Seul ajout structurant : le record domaine pur `AssociationBoard`
> (calqué sur `ArrangementBoard`, aide de calcul pure pour le verrouillage partiel d'associer/classer),
> aligné sur ADR-0013 (îlot glisser-déposer) et DEC-0008 (feedback partiel : tuiles bien placées
> conservées). Les règles ArchUnit sont **inchangées** (5/5). Aucune décision d'architecture requise (Théo).

> **s19 — pas de nouvel ADR.** Sprint contenu + polish, aucune nouvelle mécanique ni nouveau coup
> (DEC-0015) : compréhension CM1 (quiz) et nature des mots CM1 (associer/classer) sont deux **packs
> YAML auto-enregistrés** par les loaders existants (`QuizPackLoader`, `AssocPackLoader`) — la nature
> des mots réutilise la catégorisation d'associer/classer (comme `vivant-non-vivant-cp`). **Seul
> ajout** : le paramètre `defaultJokers` de `SessionEngine` (`SessionEngine(mechanicId, defaultJokers)`)
> pour un capital de cœurs dépendant de la mécanique (`MemoryMechanic.JOKERS = 6`) — extension d'un
> service existant, pas de nouveau contrat. Les dés agrandis sont une variante CSS. ArchUnit **5/5**
> inchangé. Aucune décision d'architecture requise (Théo).

> **s20 — pas de nouvel ADR.** Sprint **pur contenu** (DEC-0016) : ouverture du domaine monnaie
> (Grandeurs et mesures) par 3 packs YAML **auto-enregistrés** (2 quiz `cp-monnaie` / `cm1-monnaie`,
> 1 associer `cm1-monnaie-centimes`) sur les mécaniques quiz-aventure et associer/classer existantes.
> **Aucun code, aucune nouvelle mécanique, aucun nouveau coup.** ArchUnit **5/5** inchangé. Aucune
> décision d'architecture requise (Théo).

| [ADR-0017](ADR-0017-mecanique-puzzle-assemblage.md) | Mécanique « Puzzle / assemblage » (7e) — **réutilise** le coup `SubmitArrangement` (comme memory réutilise `SubmitAssociations`) ; 1re déclinaison « reconstituer un mot » (`MotPuzzleGenerator`, lettres + indice), 2 jeux YAML, îlot JS `puzzle.js` ; isolation ArchUnit en slices dynamiques couvrant automatiquement `domain.mechanics.puzzle`, inchangée | Accepté | 2026-07-22 | s21 |

> **s21 — un seul nouvel ADR (ADR-0017, puzzle).** Les autres chantiers de s21 n'exigent **aucun
> ADR** (cf. §Note d'ADR-0017) : **jokers paramétrables via YAML + réglage adulte** = simple
> paramètre de settings (`jokers` optionnel en en-tête YAML lu par `SessionEngine`, exposé via
> `JokersSettingsView` / param POST `jokers` ; le capital 6 du memory vient désormais du YAML —
> aboutit la réserve DEC-0013 / roadmap A3) ; **navigation par matière** (3e axe) = nouveau
> **read-model** (`CatalogueQuery.matieres()` / `byMatiere()`, routes `/matieres`, `/matiere/{matiere}`),
> sans changement de domaine ; **externalisation frise + fractions** (`FriseChronologiqueGenerator`,
> `CompareFractionsGenerator` recevant leur liste par constructeur depuis `content/frise/*.yml` /
> `content/fractions/*.yml`) = même patron d'externalisation que ADR-0009→0011, **dette remboursée**.
> ArchUnit **5/5** inchangé sur tous ces chantiers.

| [ADR-0018](ADR-0018-mecanique-droite-graduee.md) | Mécanique « Droite graduée » (8e) — **réutilise** le coup `SpinAndPick` (comme puzzle réutilise `SubmitArrangement`, memory `SubmitAssociations`) ; place un nombre sur une droite 0→N par clic de graduation (jokers automatiques : tick faux = cœur perdu + tick grisé) ; générateur combinatoire `DroiteGradueeGenerator` (bornes câblées dans BeansConfig), 2 jeux `droite-graduee-cp` (0→20) / `droite-graduee-cm1` (0→100) ; îlot JS de vue `droitegraduee` ; isolation ArchUnit en slices dynamiques couvrant automatiquement `domain.mechanics.droitegraduee`, inchangée | Accepté | 2026-07-22 | s22 |

> **s22 — un seul nouvel ADR (ADR-0018, droite graduée).** L'autre livrable de s22 — les **filtres
> « Par jeu »** (IDEE-3 : filtrer l'écran `/jeux` par niveau/matière via boutons combinables et
> réinitialisables, filtrage serveur par query params `?niveau=&matiere=`) — **n'exige aucun ADR**
> (cf. §Note d'ADR-0018) : pur **read-model / vue** sur le catalogue enrichi (ADR-0005), **aucun
> changement de domaine** ni de règle d'architecture (même nature que la navigation « par matière »
> de s21). ArchUnit **5/5** inchangé.
