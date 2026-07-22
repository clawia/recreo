# Fiche de conformité — Mécanique « Memory » (s17)

- **Mécanique** : Memory (`domain/mechanics/memory`, id `memory`) — 6e mécanique, **isolée**, délègue
  au `SessionEngine` partagé (ADR-0007). **Îlot JS de flip** (cartes retournées, `static/js/memory.js`
  sur le patron `bandit.js` / `glisser-deposer.js`). **Réutilise** le coup `SubmitAssociations(Map)`
  (mêmes paires validées côté domaine que « Associer/Classer », cf. ADR-0016).
- **Contenus** : 4 jeux **externalisés en YAML dès le départ** (loader `MemoryPackLoader`, chargés +
  validés au bootstrap ; convention `niveau-jeu.yml`).
- **Statut global** : En cours — Done technique attendu (CI verte à confirmer) puis Done pédagogique
  (démo). **Jokers (DEC-0013)** : sur erreur, retente + 1 cœur ; pas de verrouillage partiel type
  DEC-0008 (scope différé assumé).
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0014 (cartes retournées ; flip CSS ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `memory-quantite-cp` | Quantité et chiffre | Mathématiques | CP | associer une quantité à son chiffre | en cours |
| `memory-fraction-cm1` | Fractions | Mathématiques | CM1 | associer une fraction à sa représentation | en cours |
| `memory-anglais-cm1` | Anglais | Anglais (LV) | CM1 | apparier un mot français à sa traduction anglaise | en cours |
| `memory-mot-image-cp` | Mot et image | Français | CP | associer un mot écrit à son image (lecture) | en cours |

## Critères pédagogiques
- [ ] Chaque carte a **exactement une** carte partenaire ; la paire correcte est définie dans le YAML
      et vérifiée (correction par encodage canonique trié côté domaine, coup `SubmitAssociations`).
- [ ] Contenu de niveau raisonnable (CP : quantités ≤ 10, mots↔images familiers ; CM1 :
      fractions↔représentations simples, lexique anglais courant).
- [ ] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin.
- [ ] Îlot JS de flip **sans dépendance externe** (cohérence fat jar / zéro-Node) ; la réponse
      attendue n'est jamais révélée dans le DOM avant le retournement.
- [ ] **Jokers** : un essai faux consomme 1 cœur et fait retenter ; à 0 cœur, un essai faux termine
      la session (cohérent DEC-0013).
- [ ] **Réserve** : contenus initiaux volontairement restreints ; à relire/enrichir par
      l'utilisatrice directement dans les YAML (justesse des paires = point de vigilance de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — · **Validée** : — · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
