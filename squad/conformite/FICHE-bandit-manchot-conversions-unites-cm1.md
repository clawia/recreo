# Fiche de conformite - bandit-manchot-conversions-unites-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du
  catalogue).
- **Contenu(s)** : `adapters/adapter-content/src/main/resources/contenu/cm1-conversions-unites.yaml`
  (a creer, `type: question`, au moins 24 items), story `S12-2` (`docs/backlog/backlog.md`). Matiere
  premiere identifiee : `contenu-repris/quiz/cm1-longueurs.yml`, `cm1-masses.yml` et
  `cm1-contenances.yml` - une transposition directe des items de conversion vers `type: question` est
  possible (renommer `question`/`bonne`/`distracteurs` en `enonce`/`bonneReponse`/`distracteurs`,
  ajouter un `indice` par item, absent des trois fichiers source). Les items d'estimation d'ordre de
  grandeur et d'identification d'instrument, presents dans les trois fichiers, ne relevent pas du
  verbe "convertir" que cette ligne nomme et ne sont pas repris tels quels. Reprise = reprise de la
  matiere, pas de la structure (`contenu-repris/README.md`) : chaque item repasse devant `nadia-prof`
  comme un contenu neuf.
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
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Convertir des unites de longueur, masse, contenance dans le systeme metrique | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre exact, assume a l'ouverture** : cette ligne nomme trois grandeurs (longueur, masse,
contenance) et un seul verbe, "convertir". Un Done pedagogique accorde sur un paquet qui ne
travaillerait qu'une ou deux des trois grandeurs reproduirait le motif de couverture partielle deja
sanctionne sur ce produit (additions "jusqu'a 10 (puis 20)", homophones "principaux", conjugaison
"un temps sur trois", comparer sans ranger) ; les criteres ci-dessous l'excluent explicitement.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item demande de convertir une quantite d'une unite metrique vers une autre unite de la
  meme grandeur (longueur, masse ou contenance), avec une bonne reponse numeriquement exacte.
- [ ] Chacune des trois grandeurs (longueur, masse, contenance) represente au moins un quart des
  items du paquet.
- [ ] Aucun item n'est une question d'estimation d'ordre de grandeur ou d'identification
  d'instrument : chaque item teste une conversion, jamais une estimation ni une reconnaissance
  d'outil.
- [ ] Chaque item porte exactement trois ou quatre propositions : la bonne reponse et des
  distracteurs numeriques plausibles (confusion de facteur 10/100/1000), jamais un nombre
  arbitraire.
- [ ] Le paquet compte au moins 24 items, avec au moins 8 items par grandeur.
- [ ] Chaque item porte un indice non vide, exact et comprehensible par un enfant de CM1.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent.
