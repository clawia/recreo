# Fiche de conformité — Mécanique « Droite graduée » (s22)

- **Mécanique** : Droite graduée (`domain/mechanics/droitegraduee`, id `droitegraduee`) — 8e
  mécanique, **isolée**, délègue au `SessionEngine` partagé (ADR-0007). **Réutilise** le coup
  `SpinAndPick` — un choix parmi les graduations — comme le puzzle réutilise `SubmitArrangement` et le
  memory `SubmitAssociations` (cf. ADR-0018). **Aucun nouveau coup.** Interaction propre : **îlot JS
  de vue `droitegraduee`** (rendu de la droite 0 → N, clic de graduation, tick grisé, sur le patron
  `puzzle.js` / `memory.js` / `glisser-deposer.js`).
- **Contenus** : 2 jeux **combinatoires** — générateur `DroiteGradueeGenerator` (tire un nombre cible
  dans [0, N] et construit la droite 0 → N), **bornes câblées dans `BeansConfig`** (pas de YAML,
  contenu combinatoire, comme les autres générateurs combinatoires).
- **Statut global** : ✅ **VALIDÉ (2026-07-23)** — Done pédagogique prononcé par l'utilisatrice après
  démo (validation groupée s21 + s22 + s23 ; DEC-0019 / ADR-0018 pour la droite des entiers, DEC-0020
  pour la fraction sur la droite). **Jokers (DEC-0013 / DEC-0017) — automatiques** : un **tick faux
  consomme 1 cœur** et grise le tick (retente) ; à 0 cœur, un tick faux termine la session —
  comportement natif de `SpinAndPick`.
- **Dernière mise à jour** : 2026-07-23, par camille-po
- **Ergonomie** : cf. DEC-0019 (placer un nombre sur une droite graduée : clic sur la bonne
  graduation ; écrans communs réutilisés).

## Jeux et compétences visées

| gameKey | Jeu | Matière | Niveau | Compétence | Statut |
|---|---|---|---|---|---|
| `droite-graduee-cp` | Place le nombre (0 → 20) | Mathématiques | CP | situer / placer un nombre sur une droite graduée (0 → 20) — sens du nombre | ✅ validé (2026-07-23) |
| `droite-graduee-cm1` | Place le nombre (0 → 100) | Mathématiques | CM1 | situer / placer un nombre sur une droite graduée (0 → 100) — sens du nombre | ✅ validé (2026-07-23) |
| `droite-fraction-cm1` | Place la fraction (0 → 1) | Mathématiques | CM1 | situer / placer une **fraction** sur une droite graduée (0 → 1 : 0, 1/4, 1/2, 3/4, 1) — sens de la fraction | ✅ validé (2026-07-23) |

## Critères pédagogiques
- [x] Chaque question a une **graduation correcte unique** (la position du nombre cible sur la droite
      0 → N), définie par le générateur combinatoire et vérifiée (correction par choix côté domaine,
      coup `SpinAndPick`).
- [x] Contenu de niveau raisonnable (CP : droite **0 → 20** lisible ; CM1 : droite **0 → 100**) ;
      bornes câblées dans `BeansConfig` ; pas d'ambiguïté sur la graduation attendue.
- [x] Nouvelle mécanique **isolée** (ArchUnit — slices dynamiques) ; réutilise mode session, réglages
      et écran de fin ; partage le coup `SpinAndPick` avec le bandit manchot.
- [x] Îlot JS de vue **sans dépendance externe** (cohérence fat jar / zéro-Node) ; la bonne
      graduation n'est jamais révélée dans le DOM avant résolution.
- [x] **Jokers (automatiques)** : un tick faux consomme 1 cœur et grise le tick (retente) ; à 0 cœur,
      un tick faux termine la session (cohérent DEC-0013 / DEC-0017).
- [ ] **Réserve** : 1re déclinaison sur les **nombres entiers** ; les **fractions sur la droite**
      graduée sont la suite naturelle (roadmap) ; lisibilité de la droite pour de jeunes enfants =
      point de vigilance principal de la démo.

### Extension s23 — fraction sur la droite (`droite-fraction-cm1`, DEC-0020)
- [x] La mécanique droite graduée est **généralisée** : les graduations affichées proviennent d'une
      **liste d'étiquettes `lineTicks`** (entiers **OU fractions**) au lieu d'entiers recalculés —
      **évolution de la mécanique existante, aucun nouveau coup (`SpinAndPick`), aucun nouvel ADR.**
- [x] Jeu `droite-fraction-cm1` : droite **0 → 1** avec graduations **0, 1/4, 1/2, 3/4, 1** ; placer
      la fraction demandée ; graduation correcte unique ; bonne graduation non révélée dans le DOM.
- [x] Lisibilité des étiquettes fractionnaires et absence d'ambiguïté = point de vigilance de la démo
      s23 ; jokers automatiques inchangés (tick faux = 1 cœur perdu + tick grisé).

## Bloc validation utilisateur (Done pédagogique)
- **Démo jouée le** : 2026-07-23 · **Validée** : Oui (VALIDÉ le 2026-07-23, groupée s21 + s22 + s23) ·
  **Commentaires** : mécanique Droite graduée validée — placer un nombre entier (CP 0 → 20, CM1
  0 → 100) **et** placer une fraction (CM1, droite 0 → 1 : 0, 1/4, 1/2, 3/4, 1, généralisation
  `lineTicks`) ; jokers automatiques (tick faux = 1 cœur perdu + tick grisé) cohérents.

## Bloc playtest enfants
- **Session tenue le** : — (jalon) · **Observations** : —
