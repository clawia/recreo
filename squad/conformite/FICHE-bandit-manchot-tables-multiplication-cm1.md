# Fiche de conformite - bandit-manchot-tables-multiplication-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/
  cm1-tables-multiplication.yaml` (a creer, `type: question`, au moins 55 items), story `S12-3`
  (`docs/backlog/backlog.md`). **Aucune matiere premiere directement reutilisable** :
  `contenu-repris/cm1-tables-multiplication.yml` existe mais dans le schema de difficulte
  (`difficulty: { minOperand, maxOperand, operations }`, `bounds: { ... }`) que `round-two` ne charge
  pas - c'est exactement la configuration taillee pour l'arithmetique nommee comme dette n°2 de
  `CLAUDE.md`. Le contenu est donc integralement a ecrire en `type: question`, un item par couple de
  facteurs.
- **Statut global** : en construction. Aucun audit rendu a ce jour.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Ces deux points devront etre mesures sur
  ce jeu une fois son contenu et son rendu reels disponibles ; rien n'est mesure ici a l'ouverture de
  la fiche, faute d'increment a mesurer.
- **Point de vigilance de capacite** : l'espace canonique de cette competence est fini (55 couples
  distincts de facteurs entre 1 et 10). `DEC-0020` a deja rencontre cette contrainte sur "Les
  complements a 10" (11 valeurs) et abaisse `nombreDeManches` plutot que d'elargir artificiellement
  les formes d'items ; le meme arbitrage pourra devoir etre instruit ici si `nombreDeManches`
  declare au catalogue s'approche de 55.
- **Derniere mise a jour** : 2026-08-14 (ouverture)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Nombres et calculs | Calcul mental - tables de multiplication (jusqu'a x10), rappel automatise | `squad/programme/mathematiques/competences-cm1.md` |

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item est une multiplication de deux facteurs entiers, chacun compris entre 1 et 10
  inclus, avec une bonne reponse numeriquement exacte.
- [ ] Chacun des 55 couples distincts de facteurs (1 <= a <= b <= 10) est represente par au moins un
  item du paquet.
- [ ] Chaque item porte exactement trois propositions : la bonne reponse et deux distracteurs
  numeriques plausibles (erreur de lecture d'un facteur, somme au lieu du produit), jamais un
  nombre arbitraire.
- [ ] Le paquet compte au moins 55 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`), pas `additions-bornees` (specifique
  a l'addition), sans code Java neuf, sans gabarit ni feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
