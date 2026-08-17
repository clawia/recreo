# Fiche de conformite - barre d'entree par niveau (en-tete)

- **Increment** : deux boutons d'entree par niveau (CP / CM1) dans l'en-tete de recreo, toujours
  visibles y compris sur un ecran de jeu, story `S14-1` (`docs/backlog/backlog.md`, section
  "Sprint 14").
- **Statut global** : **en attente d'arbitrage prealable**. Aucun code n'est affecte au planning
  tant que cet arbitrage n'est pas rendu (`DEC-0032-perimetre-sprint-14-barre-entree-niveau-
  arbitrage-prealable-matiere-differee.md`, section 1).
- **Ce que cette story rouvre, et pourquoi cette fiche existe avant tout code** : elle ajoute deux
  elements visibles et actionnables dans l'en-tete pendant un ecran de jeu, la ou l'arbitrage de
  S6-9 / `ADR-0022` (2026-08-07) n'en laisse survivre qu'un seul, masque justement pour eviter
  qu'un enfant hesite entre plusieurs elements qui ressemblent a une sortie. La demande vient de
  l'utilisatrice, qui l'assume en connaissance de cet arbitrage anterieur - ce n'est pas une
  reouverture que `camille-po` propose de son propre chef, voir `DEC-0032` pour le detail.
- **Derniere mise a jour** : 2026-08-17 (ouverture par `camille-po`, bloc de validation vide).

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| transverse | - | - | pas de contenu pedagogique : navigation entre les jeux du catalogue par niveau | - |

Cette story n'ouvre, ne ferme et ne rejuge aucune ligne de `squad/programme/MATRICE-couverture.md`.

## Criteres pedagogiques attendus - camille-po

- [ ] Les boutons de niveau restent visibles dans l'en-tete sur un ecran de jeu, au meme titre qu'en
  dehors d'une partie.
- [ ] Un appui sur un bouton de niveau pendant une partie en cours ouvre l'ecran de confirmation
  existant avant tout changement ; la partie n'est pas modifiee avant confirmation.
- [ ] Un enfant qui continue sa partie depuis cet ecran de confirmation la retrouve exactement telle
  qu'elle etait avant l'appui sur le bouton de niveau.
- [ ] Le texte des boutons de niveau et de l'etat filtre est integralement accentue, sans faute
  d'orthographe ni de ponctuation (`DEC-0002`, amendement du 2026-08-04).
- [ ] Un enfant de CP ne confond pas un bouton de niveau avec une sortie de jeu sur un ecran de jeu.
- [ ] Le passage par l'ecran de confirmation est percu comme un signal de consequence sur la partie,
  et non comme un obstacle arbitraire.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> arbitrage prealable n'a ete rendu et qu'aucun increment n'a ete livre et joue.

### Arbitrage prealable au developpement - barre d'entree par niveau toujours visible (DEC-0032, s14)

> A remplir par `nadia-prof` avant toute affectation au planning. Question posee par `camille-po`
> dans `DEC-0032`, sans la trancher :
>
> Un enfant de CP retrouve-t-il la sortie et ne confond-il pas les boutons de niveau avec une sortie
> de jeu, si l'en-tete porte desormais trois elements actionnables visibles en permanence sur un
> ecran de jeu (le lien "Les jeux", habituellement masque, restant masque ; les deux boutons de
> niveau, nouvellement visibles) ? Le passage par l'ecran de confirmation deja existant est-il un
> signal suffisant pour qu'il comprenne qu'appuyer sur un bouton de niveau pendant une partie a une
> consequence sur cette partie ?
