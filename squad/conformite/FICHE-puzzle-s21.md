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
- **Statut global** : ⏳ **À VALIDER** — Done pédagogique à prononcer par l'utilisatrice après démo
  (DEC-0017 / ADR-0017). **Jokers (DEC-0013 / DEC-0017)** : un essai faux consomme 1 cœur et fait
  retenter ; à 0 cœur, un essai faux termine la session ; capital **réglable** (en-tête YAML `jokers`
  + réglage adulte).
- **Dernière mise à jour** : 2026-07-22, par camille-po
- **Ergonomie** : cf. DEC-0017 (reconstituer un mot : lettres mélangées à remettre dans l'ordre,
  indice image au CP / définition au CM1 ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `cp-mots-images` | Reconstitue le mot (images) | Français | CP | reconstituer un mot à partir de ses lettres (indice image) — écriture / lecture | ⏳ à valider |
| `cm1-mots` | Reconstitue le mot (définitions) | Français | CM1 | reconstituer un mot à partir d'une définition (indice définition) — vocabulaire / orthographe lexicale | ⏳ à valider |

## Critères pédagogiques
- [ ] Chaque puzzle a un **ordre correct unique** (les lettres du mot cible), défini dans le YAML et
      vérifié (correction par encodage canonique d'arrangement côté domaine, coup `SubmitArrangement`).
- [ ] Contenu de niveau raisonnable (CP : mots **courts** familiers + indice image lisible ; CM1 :
      mots de vocabulaire courant + définition claire et non ambiguë).
- [ ] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin ; partage le coup `SubmitArrangement` avec le glisser-déposer.
- [ ] Îlot JS d'assemblage **sans dépendance externe** (cohérence fat jar / zéro-Node) ; l'ordre
      attendu (le mot) n'est jamais révélé dans le DOM avant résolution.
- [ ] **Jokers** : un essai faux consomme 1 cœur et fait retenter ; à 0 cœur, un essai faux termine la
      session (cohérent DEC-0013) ; capital réglable (DEC-0017).
- [ ] **Réserve** : contenus initiaux volontairement restreints ; à relire/enrichir par
      l'utilisatrice directement dans les YAML (justesse et niveau des mots / indices = point de
      vigilance principal de la démo).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : — · **Validée** : — · **Commentaires** : —

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
