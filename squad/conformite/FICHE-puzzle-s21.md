# Fiche de conformité — Mécanique « Puzzle / assemblage » (s21)

- **Mécanique** : Puzzle / assemblage (`domain/mechanics/puzzle`, id `puzzle`) — 7e mécanique,
  **isolée**, délègue au `SessionEngine` partagé (ADR-0007). **Réutilise** le coup
  `SubmitArrangement(List)` — un ordonnancement — comme le glisser-déposer et ses déclinaisons (et
  comme le memory réutilise `SubmitAssociations` d'associer/classer, cf. ADR-0017). **Aucun nouveau
  coup.** Interaction propre : **îlot JS `static/js/puzzle.js`** (assemblage de lettres + indice, sur
  le patron `glisser-deposer.js` / `memory.js`).
- **Contenus** : 2 jeux **externalisés en YAML dès le départ** (loader puzzle sur le patron
  `QuizPackLoader`, chargés + validés au bootstrap ; convention `niveau-jeu.yml`). Générateur
  `MotPuzzleGenerator` (lettres à remettre dans l'ordre + indice image/définition).
- **Statut global** : ✅ **VALIDÉ (2026-07-23)** — Done pédagogique prononcé par l'utilisatrice après
  démo (validation groupée s21 + s22 + s23 ; DEC-0017 / ADR-0017). **Jokers (DEC-0013 / DEC-0017)** :
  un essai faux consomme 1 cœur et fait retenter ; à 0 cœur, un essai faux termine la session ;
  capital **réglable** (en-tête YAML `jokers` + réglage adulte).
- **Dernière mise à jour** : 2026-07-23, par camille-po
- **Ergonomie** : cf. DEC-0017 (reconstituer un mot : lettres mélangées à remettre dans l'ordre,
  indice image au CP / définition au CM1 ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `cp-mots-images` | Reconstitue le mot (images) | Français | CP | reconstituer un mot à partir de ses lettres (indice image) — écriture / lecture | ✅ validé (2026-07-23) |
| `cm1-mots` | Reconstitue le mot (définitions) | Français | CM1 | reconstituer un mot à partir d'une définition (indice définition) — vocabulaire / orthographe lexicale | ✅ validé (2026-07-23) |

## Critères pédagogiques
- [x] Chaque puzzle a un **ordre correct unique** (les lettres du mot cible), défini dans le YAML et
      vérifié (correction par encodage canonique d'arrangement côté domaine, coup `SubmitArrangement`).
- [x] Contenu de niveau raisonnable (CP : mots **courts** familiers + indice image lisible ; CM1 :
      mots de vocabulaire courant + définition claire et non ambiguë).
- [x] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin ; partage le coup `SubmitArrangement` avec le glisser-déposer.
- [x] Îlot JS d'assemblage **sans dépendance externe** (cohérence fat jar / zéro-Node) ; l'ordre
      attendu (le mot) n'est jamais révélé dans le DOM avant résolution.
- [x] **Jokers** : un essai faux consomme 1 cœur et fait retenter ; à 0 cœur, un essai faux termine la
      session (cohérent DEC-0013) ; capital réglable (DEC-0017).
- [ ] **Réserve** : contenus initiaux volontairement restreints ; à relire/enrichir par
      l'utilisatrice directement dans les YAML (justesse et niveau des mots / indices = point de
      vigilance principal de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-23 · **Validée** : Oui (VALIDÉ le 2026-07-23, groupée s21 + s22 + s23) ·
  **Commentaires** : mécanique Puzzle « reconstituer un mot » (indice image CP, définition CM1)
  validée ; jokers réglables (DEC-0013 / DEC-0017) cohérents. Contenus des 2 puzzles à
  relire/enrichir par l'utilisatrice dans les YAML.

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
