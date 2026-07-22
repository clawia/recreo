# Fiche de conformité — Mécanique « Memory » (s17)

- **Mécanique** : Memory (`domain/mechanics/memory`, id `memory`) — 6e mécanique, **isolée**, délègue
  au `SessionEngine` partagé (ADR-0007). **Îlot JS de flip** (cartes retournées, `static/js/memory.js`
  sur le patron `bandit.js` / `glisser-deposer.js`). **Réutilise** le coup `SubmitAssociations(Map)`
  (mêmes paires validées côté domaine que « Associer/Classer », cf. ADR-0016).
- **Contenus** : 4 jeux **externalisés en YAML dès le départ** (loader `MemoryPackLoader`, chargés +
  validés au bootstrap ; convention `niveau-jeu.yml`).
- **Statut global** : ✅ **VALIDÉ (2026-07-22)** — Done pédagogique prononcé par l'utilisatrice
  (validation groupée s17 + s18). **Jokers (DEC-0013)** : depuis s18, une mauvaise paire consomme
  1 cœur ; à 0 cœur, une mauvaise paire termine la session.
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0014 (cartes retournées ; flip CSS ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `memory-quantite-cp` | Quantité et chiffre | Mathématiques | CP | associer une quantité à son chiffre | ✅ validé (2026-07-22) |
| `memory-fraction-cm1` | Fractions | Mathématiques | CM1 | associer une fraction à sa représentation | ✅ validé (2026-07-22) |
| `memory-anglais-cm1` | Anglais | Anglais (LV) | CM1 | apparier un mot français à sa traduction anglaise | ✅ validé (2026-07-22) |
| `memory-mot-image-cp` | Mot et image | Français | CP | associer un mot écrit à son image (lecture) | ✅ validé (2026-07-22) |

## Critères pédagogiques
- [x] Chaque carte a **exactement une** carte partenaire ; la paire correcte est définie dans le YAML
      et vérifiée (correction par encodage canonique trié côté domaine, coup `SubmitAssociations`).
- [x] Contenu de niveau raisonnable (CP : quantités ≤ 10, mots↔images familiers ; CM1 :
      fractions↔représentations simples, lexique anglais courant).
- [x] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin.
- [x] Îlot JS de flip **sans dépendance externe** (cohérence fat jar / zéro-Node) ; la réponse
      attendue n'est jamais révélée dans le DOM avant le retournement.
- [x] **Jokers** : un essai faux consomme 1 cœur et fait retenter ; à 0 cœur, un essai faux termine
      la session (cohérent DEC-0013).
- [x] **Réserve** : contenus initiaux volontairement restreints ; à relire/enrichir par
      l'utilisatrice directement dans les YAML (justesse des paires = point de vigilance de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-22 · **Validée** : Oui (VALIDÉ le 2026-07-22, groupée s17 + s18) ·
  **Commentaires** : 4 jeux validés ; en s18 le memory consomme un cœur (DEC-0013), faces de dés
  pour quantités CP (6 paires) et images côté français pour l'anglais CM1 (DEC-0014).

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
