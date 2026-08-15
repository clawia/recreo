# Fiche de conformite - bandit-manchot-problemes-durees-cm1

- **Mecanique** : bandit manchot (mecanique generique, partagee avec les autres jeux du catalogue).
- **Contenu(s)** : contenu neuf a creer (`type: question`, au moins 20 items), story `S13-3`
  (`docs/backlog/backlog.md`). Matiere premiere partielle identifiee : `contenu-repris/quiz/
  cm1-heure.yml` porte deux items reellement conformes ("de 14h a 16h, combien de temps s'est
  ecoule ?", le film de 8h30 a 10h) sur ses dix questions ; les autres relevent de la lecture de
  l'heure (competence CP) ou de la conversion d'unite de temps seule (hors de cette ligne), et aucun
  ne porte sur un "horaire d'arrivee". Reprise = reprise des deux items conformes, pas de la
  structure du fichier (`contenu-repris/README.md`) : ils repassent devant `nadia-prof` comme un
  contenu neuf.
- **Statut global** : **ouverte, aucun increment livre a ce jour.** Rien n'a encore ete joue.
- **Arbitrage prealable de `nadia-prof` demande et non encore rendu** (`DEC-0030`, section 5) : ce
  contenu n'a jamais ete instruit au niveau de detail des trois lignes du sprint 12. Les criteres
  ci-dessous, notamment les bornes (une seule journee, minutes rondes de 5 en 5), sont ceux de
  `camille-po` et n'ont pas valeur de jugement pedagogique tant que `nadia-prof` ne les a pas
  confirmes ou corriges.
- **Arbitrage prealable de mecanique en vigueur pour tout le catalogue** (`squad/conformite/
  FICHE-bandit-manchot-additions-cp.md`, branche `s10-nadia-mecanique` et audits suivants) :
  conditions M1 a M13. Rien n'est mesure ici a l'ouverture de la fiche, faute d'increment a mesurer.
- **Derniere mise a jour** : 2026-08-15 (ouverture par `camille-po`, `DEC-0030`)

## Competences visees

| Matiere | Niveau | Domaine | Competence | Reference au referentiel |
|---|---|---|---|---|
| mathematiques | CM1 (cycle 3) | Grandeurs et mesures | Resoudre des problemes de durees (calcul d'une duree, d'un horaire d'arrivee) | `squad/programme/mathematiques/competences-cm1.md` |

**Perimetre propose, sous reserve de l'arbitrage prealable de `nadia-prof`** : deux formes seulement,
"calcul d'une duree" (deux heures donnees, duree ecoulee a trouver) et "horaire d'arrivee" (heure de
depart et duree donnees, heure d'arrivee a trouver) - jamais une simple lecture de l'heure ni une
conversion d'unite de temps seule.

## Criteres pedagogiques attendus - camille-po

- [ ] Chaque item releve de l'une des deux formes exactes du referentiel (calcul d'une duree, ou
  horaire d'arrivee), jamais d'une lecture de l'heure ou d'une conversion d'unite de temps seule.
- [ ] Les deux formes sont chacune representees par au moins un tiers du paquet.
- [ ] Toutes les heures d'un meme item restent dans une seule journee (aucun item ne franchit
  minuit), exprimees en heures et minutes rondes de 5 en 5 minutes, sans seconde.
- [ ] Chaque item porte exactement trois propositions, dont deux distracteurs plausibles (erreur de
  report d'heure ou de minute, inversion du sens de l'operation, oubli de la retenue de 60 minutes),
  jamais deux propositions egales dans le meme item.
- [ ] Le paquet compte au moins 20 items.
- [ ] Chaque item porte un indice non vide qui donne la methode, pas le resultat.
- [ ] Le paquet valide le schema `type: question` (`ADR-0007`) sans code Java neuf, sans gabarit ni
  feuille de style neuve.

---

## Bloc de validation pedagogique - nadia-prof UNIQUEMENT

> Ce bloc n'est jamais pre-rempli ni anticipe par un autre agent. Il reste vide tant qu'aucun
> increment n'a ete livre et joue. L'arbitrage prealable demande par `DEC-0030` (formes admises,
> bornes horaires) sera rendu ici ou dans un fichier d'arbitrage dedie, avant tout code.
