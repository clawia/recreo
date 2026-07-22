# Fiche de conformité — Mécanique « Associer / Classer » (s17)

- **Mécanique** : Associer / Classer (`domain/mechanics/associer`, id `associer`) — 5e mécanique,
  **isolée**, délègue au `SessionEngine` partagé (ADR-0007). 1er coup d'appariement/catégorisation
  (`SubmitAssociations(Map)`, cf. ADR-0016), au-delà de l'ordonnancement (`SubmitArrangement`).
- **Contenus** : 2 jeux **externalisés en YAML dès le départ** (loader `AssocPackLoader`, chargés +
  validés au bootstrap ; convention `niveau-jeu.yml`).
- **Statut global** : En cours — Done technique attendu (CI verte à confirmer) puis Done pédagogique
  (démo). **Jokers (DEC-0013)** : sur erreur, retente plein plateau + 1 cœur (pas de verrouillage
  partiel type DEC-0008 — scope différé assumé, cf. ADR-0016 / DEC-0014).
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0014 (plateau d'appariement / catégorisation ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `anglais-associer-cm1` | Appariement FR↔EN | Anglais (LV) | CM1 | apparier un mot français à sa traduction anglaise (lexique) | en cours |
| `vivant-non-vivant-cp` | Vivant / non-vivant | Questionner le monde | CP | distinguer le vivant du non-vivant (catégoriser) | en cours |

## Critères pédagogiques
- [ ] Chaque paire (appariement) ou chaque item→catégorie (classement) a une correspondance
      **correcte unique**, définie dans le YAML et vérifiée (correction par encodage canonique trié
      côté domaine).
- [ ] Contenu de niveau raisonnable (CM1 : lexique anglais courant ; CP : vivant/non-vivant sur des
      exemples familiers et non ambigus).
- [ ] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin ; partage le coup `SubmitAssociations` avec Memory.
- [ ] Présentation distincte (plateau d'appariement / catégorisation) des autres mécaniques ; la
      réponse attendue n'est jamais révélée dans le DOM.
- [ ] **Jokers** : un essai faux consomme 1 cœur et fait retenter le **plein plateau** ; à 0 cœur, un
      essai faux termine la session (cohérent DEC-0013).
- [ ] **Réserve** : contenus initiaux volontairement restreints ; à relire/enrichir par
      l'utilisatrice directement dans les YAML (justesse des paires / catégories = point de vigilance
      principal de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — · **Validée** : — · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
