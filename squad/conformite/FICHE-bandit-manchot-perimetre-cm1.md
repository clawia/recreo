# Fiche de conformite - bandit-manchot-perimetre-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-perimetre.yaml` (a
  creer, `type: question`, au moins 20 items), story `S12-1` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/quiz/cm1-perimetre.yml` (8 de ses 11 questions calculent un
  perimetre de carre ou de rectangle a partir de cotes donnes en texte ; 2 portent sur un triangle
  equilateral, hors du perimetre exact de cette ligne, non repris ; 2 sont definitionnelles,
  conservables mais ne comptent pas comme du calcul). Reprise = reprise de la matiere (enonces,
  valeurs), pas de la structure du fichier (`contenu-repris/README.md`) : chaque item repasse devant
  `nadia-prof` comme un contenu neuf, et chaque item doit porter un `indice`, absent du fichier
  source.
- **Statut global** : en construction. Aucun audit rendu a ce jour.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13, dont deux ont ete bloquantes pour d'autres jeux du catalogue - M4 (le message
  d'erreur doit etre vu sans defiler a 360 x 640) et la clause 1 de M13 (apres une reponse, la
  tabulation ne detruit jamais la partie sans confirmation). Ces deux points devront etre mesures sur
  ce jeu une fois son contenu et son rendu reels disponibles ; rien n'est mesure ici a l'ouverture de
  la fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-14 (ouverture)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Calculer un perimetre de figures usuelles (carre, rectangle) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture** : le referentiel nomme "carre" et "rectangle", pas de
triangle ni d'autre polygone. Un Done pedagogique accorde sur un paquet qui ne porterait que ces
deux figures couvre la ligne en entier, sans reste connu a ce jour - a la difference des lignes
partielles deja rencontrees sur ce produit (additions "jusqu'a 10 (puis 20)", homophones
"principaux", comparer/ranger).

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item sur un carre donne un seul cote (nombre entier, unite explicite) ; la bonne
  reponse vaut quatre fois ce cote, dans la meme unite.
- [ ] Chaque item sur un rectangle donne une longueur et une largeur (deux entiers distincts, meme
  unite) ; la bonne reponse vaut deux fois leur somme, dans la meme unite.
- [ ] Le paquet compte au moins autant d'items sur le carre que sur le rectangle, a un pres.
- [ ] Chaque item porte exactement trois propositions : la bonne reponse et deux distracteurs
  numeriques plausibles (confusion avec l'aire, moitie du perimetre), jamais un nombre arbitraire.
- [ ] Aucun item ne porte de figure autre que le carre ou le rectangle.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
