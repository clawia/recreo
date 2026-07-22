# Fiche de conformité — Mécanique « Associer / Classer » (s17)

- **Mécanique** : Associer / Classer (`domain/mechanics/associer`, id `associer`) — 5e mécanique,
  **isolée**, délègue au `SessionEngine` partagé (ADR-0007). 1er coup d'appariement/catégorisation
  (`SubmitAssociations(Map)`, cf. ADR-0016), au-delà de l'ordonnancement (`SubmitArrangement`).
- **Contenus** : 2 jeux **externalisés en YAML dès le départ** (loader `AssocPackLoader`, chargés +
  validés au bootstrap ; convention `niveau-jeu.yml`).
- **Statut global** : ✅ **VALIDÉ (2026-07-22)** — Done pédagogique prononcé par l'utilisatrice
  (validation groupée s17 + s18). **Jokers (DEC-0013)** : sur erreur, retente + 1 cœur ;
  **verrouillage partiel** repris en s18 (record `AssociationBoard`, aligné DEC-0008 / DEC-0014).
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0014 (plateau d'appariement / catégorisation ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `anglais-associer-cm1` | Appariement FR↔EN | Anglais (LV) | CM1 | apparier un mot français à sa traduction anglaise (lexique) | ✅ validé (2026-07-22) |
| `vivant-non-vivant-cp` | Vivant / non-vivant | Questionner le monde | CP | distinguer le vivant du non-vivant (catégoriser) | ✅ validé (2026-07-22) |

## Critères pédagogiques
- [x] Chaque paire (appariement) ou chaque item→catégorie (classement) a une correspondance
      **correcte unique**, définie dans le YAML et vérifiée (correction par encodage canonique trié
      côté domaine).
- [x] Contenu de niveau raisonnable (CM1 : lexique anglais courant ; CP : vivant/non-vivant sur des
      exemples familiers et non ambigus).
- [x] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin ; partage le coup `SubmitAssociations` avec Memory.
- [x] Présentation distincte (plateau d'appariement / catégorisation) des autres mécaniques ; la
      réponse attendue n'est jamais révélée dans le DOM.
- [x] **Jokers** : un essai faux consomme 1 cœur et fait retenter le **plein plateau** ; à 0 cœur, un
      essai faux termine la session (cohérent DEC-0013).
- [x] **Réserve** : contenus initiaux volontairement restreints ; à relire/enrichir par
      l'utilisatrice directement dans les YAML (justesse des paires / catégories = point de vigilance
      principal de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-22 · **Validée** : Oui (VALIDÉ le 2026-07-22, groupée s17 + s18) ·
  **Commentaires** : appariement FR↔EN et catégorisation vivant/non-vivant validés ; verrouillage
  partiel repris en s18 (DEC-0014) ; jokers (DEC-0013) cohérents sur la mécanique.

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
